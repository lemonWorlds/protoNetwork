package main;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import stuff.ReliableMessageStore;
import stuff.SchemaSingleton;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.RDF;

import interfaces.InputStreamHandler;

public class SimpleInputStreamHandler implements InputStreamHandler, Runnable {
	
	private Model schema = SchemaSingleton.getSchema(); //FOR TESTING

	private DataInputStream stream = null;
	private BlockingQueue<String> queue = null;
	
	public SimpleInputStreamHandler(InputStream in,BlockingQueue<String> queue) {
		stream = new DataInputStream(in);
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			while (true) {
				/*
				Model model = getDataModel(stream);
				StmtIterator iter = model.listStatements();
				while (iter.hasNext()) {
					System.out.println(iter.next());
				}
				*/
				queue.add(getInputData(stream));
			}
		} catch (IOException ex) {
			ex.printStackTrace(); //HERE!!!!!!!!!!!!
			/*
			 * FOR TESTING!!!
			 */
			Resource updateClass = schema.getResource("http://www.model.org/update");
			Model updateEv = ModelFactory.createDefaultModel();
			Resource event1 = updateEv.createResource("http://www.test.org/event1");
			event1.addProperty(RDF.type,updateClass);
			Resource peer1 = updateEv.createResource("http://www.test.org/peer1");
			Property occurredAt = schema.getProperty("http://www.model.org/occurredAt");
			event1.addProperty(occurredAt, peer1);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			updateEv.write(baos, "RDF/XML");
			byte[] bytes = baos.toByteArray();
			String spikeModel = new String(bytes);

			queue.add(spikeModel);
		}
		
	}
	
	private String getInputData(DataInputStream stream) throws IOException {		
		int size = stream.readInt();
		byte[] data = new byte[size];
		stream.read(data,0,size);
		return new String(data);
	}
	/*
	private Model getDataModel(DataInputStream stream) throws IOException {
		String data = getInputData(stream);
		Model model = ModelFactory.createDefaultModel();
		return model.read(new StringReader(data),null);
	}*/

}
