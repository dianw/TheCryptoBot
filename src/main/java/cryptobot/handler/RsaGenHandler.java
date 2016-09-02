package cryptobot.handler;

import java.io.IOException;
import java.io.StringWriter;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.springframework.stereotype.Component;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;

@Component
public class RsaGenHandler extends AbstractCommandHandler {
	@Override
	public String command() {
		return "/rsagen";
	}
	
	@Override
	public BaseResponse handleCommand(TelegramBot bot, Update update, Message message, String... commands) {
		int keySize = 1024;
		
		if (commands.length > 0) {
			keySize = NumberUtils.toInt(commands[0], keySize);
		}
		
		try {
			BaseResponse response = bot.execute(new SendChatAction(message.chat().id(), "typing"));
			
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "BC");
			keyPairGenerator.initialize(keySize, new SecureRandom());

			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			bot.execute(new SendMessage(message.chat().id(), toPem(keyPair.getPrivate())));
			bot.execute(new SendMessage(message.chat().id(), toPem(keyPair.getPublic())));
			
			return response;
		} catch (Exception e) {
			return bot.execute(new SendMessage(message.chat().id(), StringUtils.join("Oops, ", e.getMessage())));
		}
	}

	private String toPem(Key key) throws IOException {
		StringWriter stringWriter = new StringWriter();
		JcaPEMWriter pemWriter = new JcaPEMWriter(stringWriter);
		pemWriter.writeObject(key);
		IOUtils.closeQuietly(pemWriter);
		
		return stringWriter.toString();
	}
}
