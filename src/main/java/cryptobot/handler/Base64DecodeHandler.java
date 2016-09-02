package cryptobot.handler;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.stereotype.Component;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;

@Component
public class Base64DecodeHandler extends AbstractCommandHandler {
	@Override
	public String command() {
		return "/base64decode";
	}

	@Override
	public BaseResponse handleCommand(TelegramBot bot, Update update, Message message, String... commands) {
		if (commands.length < 1) {
			return helpHandler.handleCommand(bot, update);
		}

		try {
			String plain = new String(Base64.decode(commands[0]));
			
			return bot.execute(new SendMessage(message.chat().id(), plain));
		} catch (Exception e) {
			String msg = StringUtils.join("\"", commands[0], "\" is not valid base64 string");
			
			return bot.execute(new SendMessage(message.chat().id(), msg));
		}
	}

}
