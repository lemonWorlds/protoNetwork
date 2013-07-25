package main;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import interfaces.InputStreamHandler;

public class ClientSideInputHandler implements InputStreamHandler, Runnable {
	private DataInputStream stream = null;

	public ClientSideInputHandler(InputStream in) {
		stream = new DataInputStream(in);
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				System.out.println(getInputData(stream));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private String getInputData(DataInputStream stream) throws IOException {		
		int size = stream.readInt();
		byte[] data = new byte[size];
		stream.read(data,0,size);
		return new String(data);
	}

}
