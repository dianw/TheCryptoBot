package cryptobot.handler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.response.BaseResponse;

public interface CommandHandler {
	String command();

	BaseResponse handleCommand(TelegramBot bot, Update update);
}
