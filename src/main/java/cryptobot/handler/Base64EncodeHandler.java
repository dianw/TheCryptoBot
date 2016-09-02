package cryptobot.handler;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.stereotype.Component;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

@Component
public class Base64EncodeHandler extends AbstractCommandHandler {
	@Override
	public String command() {
		return "/base64encode";
	}

	@Override
	public SendResponse handleCommand(TelegramBot bot, Update update, Message message, String... commands) {
		if (commands.length < 1) {
			return helpHandler.handleCommand(bot, update);
		}

		String base64 = Base64.toBase64String(commands[0].getBytes());
		return bot.execute(new SendMessage(message.chat().id(), base64));
	}
}
