package client;

import java.awt.HeadlessException;
import java.io.IOException;

import clientUi.LogIn;

public class LoginMain {
	public static void main(String[] args) {
		try {
			new LogIn().start();
		} catch (HeadlessException e) {
		} catch (IOException e) {
		}
	}
}
