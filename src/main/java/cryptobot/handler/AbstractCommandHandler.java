package cryptobot.handler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.response.BaseResponse;

public abstract class AbstractCommandHandler implements CommandHandler {
	@Autowired
	protected HelpHandler helpHandler;

	@Override
	public BaseResponse handleCommand(TelegramBot bot, Update update) {
		Message message = update.message();
		if (message == null) message = update.editedMessage();
		
		String[] commands = StringUtils.split(StringUtils.substringAfter(message.text(), command()));
		
		return handleCommand(bot, update, message, commands);
	}
	
	public abstract BaseResponse handleCommand(TelegramBot bot, Update update, Message message, String... commands);
}
