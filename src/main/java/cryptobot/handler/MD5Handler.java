package cryptobot.handler;

import org.springframework.stereotype.Component;

@Component
public class MD5Handler extends MessageDigestHandler {
	public MD5Handler() {
		super("MD5");
	}

	@Override
	public String command() {
		return "/md5";
	}

}
