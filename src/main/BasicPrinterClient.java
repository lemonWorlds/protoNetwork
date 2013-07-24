package main;

import interfaces.InputHandler;
import interfaces.RuleBase;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BasicPrinterClient {
	private InputHandler handler = InputHandlerFactory.getHandler();

	public static void main(String args[]) {
		new BasicPrinterClient().launch();
	}

	private void launch() {
		try {
			ServerSocket ss = new ServerSocket(6000);
			while (true) {
				System.out.println("Waiting for new client...");
				Socket socket = ss.accept();
				System.out.println("Connected to a new client:");
				handler.handleConnection(socket);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
}
