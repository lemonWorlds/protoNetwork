package stuff;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerProto {


	public static void main(String[] args) {
		new ServerProto().launch();
	}

	private void launch() {
		try {
			ServerSocket ss = new ServerSocket(6000);
			Socket socket = ss.accept();
			System.out.println("client connected...");
			DataInputStream stream = new DataInputStream(socket.getInputStream());

			for (int i = 0; i<2; i++) {
				int size = stream.readInt();
				System.out.println(size);
				byte[] data = new byte[size];
				stream.read(data,0,size);
					
				System.out.println("info recieved: " + data);
				String result = new String(data);
				System.out.println(result);
			}	
			ss.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	

}
