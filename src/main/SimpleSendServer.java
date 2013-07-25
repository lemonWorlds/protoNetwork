package main;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import interfaces.InputHandler;
import interfaces.InputStreamHandler;
import interfaces.OutputStreamHandler;

public class SimpleSendServer {
	
	public static void main(String[] args) {
		new SimpleSendServer().launch();
	}

	private void launch() {
		try { 
			Socket socket = new Socket("127.0.0.1",6000);
			OutputStream out = socket.getOutputStream();
			InputStream in = socket.getInputStream();
			InputStreamHandler inHandler = new ClientSideInputHandler(in);
			OutputStreamHandler outHandler = new SimpleOutputStreamHandler(out);
			new Thread(inHandler).start();
			new Thread(outHandler).start();
			while (true) {
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
