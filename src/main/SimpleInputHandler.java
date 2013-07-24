package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import interfaces.InputHandler;
import interfaces.InputStreamHandler;
import interfaces.OutputStreamHandler;

public class SimpleInputHandler implements InputHandler {

	@Override
	public void handleConnection(Socket socket) {
		try {
			OutputStream out = socket.getOutputStream();
			InputStream in = socket.getInputStream();
			InputStreamHandler inHandler = new SimpleInputStreamHandler(in);
			OutputStreamHandler outHandler = new SimpleOutputStreamHandler(out);
			new Thread(inHandler).start();
			new Thread(outHandler).start();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
