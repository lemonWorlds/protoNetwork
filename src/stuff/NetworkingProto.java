package stuff;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class NetworkingProto {
	
	private Model schema = SchemaSingleton.getSchema();

	public static void main(String[] args) {
		new NetworkingProto().launch();
	}

	private void launch() {
		//Create event
		Model createEv = ModelFactory.createDefaultModel();
		Resource event2 = createEv.createResource("http://www.test.org/event2");
		Resource create = schema.getResource("http://www.model.org/create");
		Property occurredAt = schema.getProperty("http://www.model.org/occurredAt");
		event2.addProperty(RDF.type,create);
		Resource peer2 = createEv.createResource("http://www.test.org/peer2");
		event2.addProperty(occurredAt, peer2);
		
		//Create another event
		Model memEv = ModelFactory.createDefaultModel();
		Resource event6 = memEv.createResource("http://www.test.org/event6");
		Resource memberEventClass = schema.getResource("http://www.model.org/memberEvent");
		event6.addProperty(RDF.type,memberEventClass);
		Resource peer6 = memEv.createResource("http://www.test.org/peer6");
		event6.addProperty(occurredAt, peer6);

		
		try {
			
			//Get data
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			createEv.write(baos,"RDF/XML");
			byte[] modelBytes = baos.toByteArray();
			
			//Create connection
			Socket socket = new Socket("127.0.0.1", 6000);
			OutputStream out = socket.getOutputStream();
			DataOutputStream dout = new DataOutputStream(out);
			
			//Write data
			dout.writeInt(modelBytes.length);
			dout.write(modelBytes,0,modelBytes.length);
			
			ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
			memEv.write(baos1,"RDF/XML");
			byte[] modelBytes1 = baos1.toByteArray();
			
			dout.writeInt(modelBytes1.length);
			dout.write(modelBytes1,0,modelBytes1.length);

			socket.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	

}
