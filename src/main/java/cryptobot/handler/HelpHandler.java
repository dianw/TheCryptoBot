package cryptobot.handler;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

@Component
public class HelpHandler implements CommandHandler {
	protected String helpText = "";
	
	@Autowired
	public void init(ResourceLoader resourceLoader) throws IOException {
		InputStream help = resourceLoader.getResource("classpath:/help.txt").getInputStream();
		helpText = IOUtils.toString(help, StandardCharsets.UTF_8);
	}

	@Override
	public String command() {
		return "/help";
	}

	@Override
	public SendResponse handleCommand(TelegramBot bot, Update update) {
		SendMessage sendMessage = new SendMessage(update.message().chat().id(), helpText);
		
		return bot.execute(sendMessage);
	}
}
