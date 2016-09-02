package cryptobot.handler;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Hex;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;

public abstract class MessageDigestHandler extends AbstractCommandHandler {
	private String algorithm;

	public MessageDigestHandler(String algorithm) {
		this.algorithm = algorithm;
	}
	
	@Override
	public BaseResponse handleCommand(TelegramBot bot, Update update, Message message, String... commands) {
		if (commands.length < 1) {
			return helpHandler.handleCommand(bot, update);
		}

		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm, "BC");
			messageDigest.reset();
			messageDigest.update(commands[0].getBytes());

			return bot.execute(new SendMessage(message.chat().id(), Hex.toHexString(messageDigest.digest())));
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			return bot.execute(new SendMessage(message.chat().id(), StringUtils.join("Oops, ", e.getMessage())));
		}
	}
}
