package cryptobot.handler;

import org.springframework.stereotype.Component;

@Component
public class CommandNotFoundHandler extends HelpHandler {
	@Override
	public String command() {
		return "/notfound";
	}
}
