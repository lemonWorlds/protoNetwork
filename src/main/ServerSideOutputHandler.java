package main;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import interfaces.OutputStreamHandler;
import interfaces.RuleBase;

public class ServerSideOutputHandler implements OutputStreamHandler {
	
	private DataOutputStream dout = null;
	private BlockingQueue<String> queue = null;
	private RuleBase base = RuleBaseFactory.getBase();
	
	public ServerSideOutputHandler(OutputStream out, BlockingQueue<String> queue) {
		dout = new DataOutputStream(out);
		this.queue = queue;
	}
	
	@Override
	public void run() {
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
