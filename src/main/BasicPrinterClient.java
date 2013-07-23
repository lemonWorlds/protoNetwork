package main;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BasicPrinterClient {
	public static void main(String args[]) {
		new BasicPrinterClient().launch();
	}

	private void launch() {
		try {
			ServerSocket ss = new ServerSocket(6000);
			Socket socket = ss.accept();
			System.out.println("client connected...");
			DataInputStream stream = new DataInputStream(socket.getInputStream());
			for (int i = 0; i < 40; i++) {
				int size = stream.readInt();
				System.out.println(size);
				byte[] data = new byte[size];
				stream.read(data,0,size);
					
				System.out.println("info recieved: " + data);
				String result = new String(data);
				System.out.println(result);
			}
			socket.close();
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
