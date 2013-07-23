package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import interfaces.InputHandler;

public class InputHandlerFactory {
	private static String handlerClass = null;
	
	public static InputHandler getHandler() {
		InputHandler handler = null;
		if (handlerClass == null) {
			Properties properties = new Properties();
			try {
				properties.load(new FileInputStream("H:\\newWorkspace\\protoNetwork\\src\\stuff\\InputHandlerFactory.properties"));
				handlerClass = properties.getProperty("handler");
				handler = (InputHandler) Class.forName(handlerClass).newInstance();
			} catch (IOException | InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		}
		return handler;
	}
	
	public static void resetInputHandlerClass() {
		handlerClass = null;
	}
	
}
