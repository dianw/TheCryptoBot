package cryptobot.task;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

import cryptobot.handler.CommandHandler;
import cryptobot.handler.CommandNotFoundHandler;

@Component
public class FetchUpdateTask {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TelegramBot bot;

	@Autowired
	private List<CommandHandler> commandHandlers;
	
	@Autowired
	private CommandNotFoundHandler notFoundHandler;

	private int offset = 0;

	@Scheduled(fixedRate = 500)
	public void getUpdates() {
		GetUpdatesResponse updateResponse = bot.execute(new GetUpdates().offset(offset + 1).limit(100));

		if (updateResponse.isOk()) {
			updateResponse.updates().forEach(update -> {
				offset = update.updateId();
				routeUpdate(update);
			});
		} else {
			log.error("Error occurred while getting updates, error code: {}", updateResponse.errorCode());
		}
	}

	public void routeUpdate(Update update) {
		Message message = update.message();
		if (message == null) message = update.editedMessage();
		
		String[] commands = StringUtils.split(message.text());
		
		log.debug("Routing command: {}, caption: {}", message.text(), message.caption());

		if (commands == null || commands.length < 1) {
			notFoundHandler.handleCommand(bot, update);
			return;
		}

		Optional<CommandHandler> handler = commandHandlers.stream()
			.filter(h -> StringUtils.equals(h.command(), commands[0]))
			.findFirst();

		if (handler.isPresent()) {
			CommandHandler commandHandler = handler.get();
			log.debug("Found handler: {}", commandHandler.command());
			commandHandler.handleCommand(bot, update);
		} else {
			log.debug("Couldn't find handler, returing default handler");
			notFoundHandler.handleCommand(bot, update);
		}
	}
}
