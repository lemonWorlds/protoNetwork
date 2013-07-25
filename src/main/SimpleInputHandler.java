package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import interfaces.InputHandler;
import interfaces.InputStreamHandler;
import interfaces.OutputStreamHandler;

public class SimpleInputHandler implements InputHandler {

	@Override
	public void handleConnection(Socket socket) {
		try {
			OutputStream out = socket.getOutputStream();
			InputStream in = socket.getInputStream();
			BlockingQueue<String> sharedQueue = new LinkedBlockingQueue<>();
			InputStreamHandler inHandler = new SimpleInputStreamHandler(in,sharedQueue);
			OutputStreamHandler outHandler = new ServerSideOutputHandler(out,sharedQueue);
			new Thread(inHandler).start();
			new Thread(outHandler).start();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
