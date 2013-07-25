package main;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import stuff.ReliableMessageStore;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import interfaces.OutputStreamHandler;
import interfaces.RuleBase;

public class ServerSideOutputHandler implements OutputStreamHandler {
	private Map<String,List<String>> storedMessages = ReliableMessageStore.getMessageStore();
	private DataOutputStream dout = null;
	private BlockingQueue<String> queue = null;
	private RuleBase base = RuleBaseFactory.getBase();
	
	public ServerSideOutputHandler(OutputStream out, BlockingQueue<String> queue) {
		dout = new DataOutputStream(out);
		this.queue = queue;
	}
	
	@Override
	public void run() {
		String name = null;
		try {
			//Sees whether messages have been stored for sending
			name = queue.take();
			List<String> messages = storedMessages.get(name);
			if (messages != null) {
				sendList(messages);
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace(); //THIS NEEDS TO BE DEALT WITH!!!!!!!!!!!!!!!!!!
		}
		
		storedMessages.remove(name);
		System.out.println("Printing messages map: " + storedMessages);
		//clears old messages to that peer that have just been sent
		while (true) {
			try {
				Model model = getModelFromString(queue.take());
				List<String> list = base.matchEventToRules(model);
				sendList(list);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Model getModelFromString(String data) {
		Model model = ModelFactory.createDefaultModel();
		return model.read(new StringReader(data),null);
	}
	
	private void sendList(List<String> list) {
		System.out.println("This list has size: " + list.size());
		for (String next: list) {
			byte[] arr = next.getBytes();
			try {
				dout.writeInt(arr.length);
				dout.write(arr,0,arr.length);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
