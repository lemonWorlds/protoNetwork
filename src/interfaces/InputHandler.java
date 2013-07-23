package interfaces;

import java.net.Socket;

public interface InputHandler {
	void handleConnection(Socket socket);
}
