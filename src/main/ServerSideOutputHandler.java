package main;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
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
	private String name = null;
	
	public ServerSideOutputHandler(OutputStream out, BlockingQueue<String> queue) {
		dout = new DataOutputStream(out);
		this.queue = queue;
	}
	
	@Override
	public void run() {
		try {
			//Sees whether messages have been stored for sending
			name = queue.take();
			List<String> messages = storedMessages.get(name);
			if (messages != null) {
				sendList(messages);
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
			Thread.currentThread().interrupt(); //Propagates the issue to the running Thread; deal with it there.
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
				Thread.currentThread().interrupt();
			}
		}
	}
	
	private Model getModelFromString(String data) {
		Model model = ModelFactory.createDefaultModel();
		return model.read(new StringReader(data),null);
	}
	
	private void sendList(List<String> list) throws InterruptedException {
		System.out.println("This list has size: " + list.size());
		int i = 0;
		try {
			for (; i < list.size();i++) {
				byte[] arr = list.get(i).getBytes();
				dout.writeInt(arr.length);
				dout.write(arr,0,arr.length);
			}
		} catch (IOException ex) {
			System.out.println("Lost connection with peer; storing message backlog...");
			List<String> messages = storedMessages.get(name);
			if (messages == null) {
				messages = new ArrayList<String>();
			}
			messages.addAll(0,queue);
			for (; i < list.size(); i++) {
				messages.add(list.get(i));
			}
			storedMessages.put(name, messages);
			System.out.println("All unsent messages now added to backlog store. Contents as follows: ");
			System.out.println(messages);
			throw new InterruptedException();
		}
	}

}
