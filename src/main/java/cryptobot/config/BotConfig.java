package cryptobot.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;

@Configuration
public class BotConfig {
	@Bean
	public TelegramBot bot(@Value("${telegram.bot.token}") String token) {
		if (StringUtils.isBlank(token)) throw new NullPointerException("Token cannot be empty or null");
		
		return TelegramBotAdapter.build(token);
	}
}
