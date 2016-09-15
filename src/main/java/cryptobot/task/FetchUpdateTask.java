package cryptobot.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

@Component
public class FetchUpdateTask {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TelegramBot bot;
	
	@Autowired
	private UpdateRouter updateRouter;

	private int offset = 0;

	@Scheduled(fixedRate = 500)
	public void getUpdates() {
		GetUpdatesResponse updateResponse = bot.execute(new GetUpdates().offset(offset + 1).limit(100));
		log.debug("Update response is ok? {}", updateResponse.isOk());
		
		if (updateResponse.isOk()) {
			log.debug("Offset: {}", offset);
			log.debug("Number of updates: {}", updateResponse.updates().size());
			
			updateResponse.updates().forEach(update -> {
				offset = update.updateId();
				updateRouter.routeUpdate(update);
			});
		} else {
			log.error("Error occurred while getting updates, error code: {}", updateResponse.errorCode());
		}
	}
}
