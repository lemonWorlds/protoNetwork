package main;

import java.net.Socket;

import interfaces.InputHandler;

public class SimpleSendServer {
	
	public static void main(String[] args) {
		new SimpleSendServer().launch();
	}

	private void launch() {
		try { 
			Socket socket = new Socket("127.0.0.1",6000);
			InputHandler handler = InputHandlerFactory.getHandler();
			handler.handleConnection(socket);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
