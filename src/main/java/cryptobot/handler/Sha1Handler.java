package cryptobot.handler;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.springframework.stereotype.Component;

@Component
public class Sha1Handler extends MessageDigestHandler {
	public Sha1Handler() throws NoSuchAlgorithmException, NoSuchProviderException {
		super("SHA-1");
	}

	@Override
	public String command() {
		return "/sha1";
	}
}
