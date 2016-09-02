package cryptobot.handler;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.springframework.stereotype.Component;

@Component
public class Sha512Handler extends MessageDigestHandler {
	public Sha512Handler() throws NoSuchAlgorithmException, NoSuchProviderException {
		super("SHA-512");
	}

	@Override
	public String command() {
		return "/sha512";
	}
}
