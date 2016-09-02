package cryptobot.handler;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.springframework.stereotype.Component;

@Component
public class Sha384Handler extends MessageDigestHandler {
	public Sha384Handler() throws NoSuchAlgorithmException, NoSuchProviderException {
		super("SHA-384");
	}

	@Override
	public String command() {
		return "/sha384";
	}
}
