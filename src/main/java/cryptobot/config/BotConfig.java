package cryptobot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;

@Configuration
public class BotConfig {
	@Autowired
	private Environment environment;

	@Bean
	public TelegramBot bot() {
		return TelegramBotAdapter.build(environment.getProperty("telegram.bot.token"));
	}
}
