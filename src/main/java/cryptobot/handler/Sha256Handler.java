package cryptobot.handler;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.springframework.stereotype.Component;

@Component
public class Sha256Handler extends MessageDigestHandler {
	public Sha256Handler() throws NoSuchAlgorithmException, NoSuchProviderException {
		super("SHA-256");
	}

	@Override
	public String command() {
		return "/sha256";
	}
}
