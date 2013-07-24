package main;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import stuff.SchemaSingleton;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

import interfaces.OutputStreamHandler;

public class SimpleOutputStreamHandler implements OutputStreamHandler, Runnable {
	
	private List<Model> modelList = new ArrayList<>();
	private DataOutputStream dout = null;
	private Model schema = SchemaSingleton.getSchema();
	private Model updateEv;
	private Model createEv;
	private Model deleteEv;
	private Model documentEv;
	private Model evEv;
	private Model memEv;
	private Model joinEv;
	private Model leaveEv;
	
	public SimpleOutputStreamHandler(OutputStream out) {
		dout = new DataOutputStream(out);
		initModels();
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			byte[] bytes1 = getBytesFromModel(updateEv);
			byte[] bytes2 = getBytesFromModel(createEv);
			byte[] bytes3 = getBytesFromModel(deleteEv);
			byte[] bytes4 = getBytesFromModel(documentEv);
			byte[] bytes5 = getBytesFromModel(evEv);
			byte[] bytes6 = getBytesFromModel(memEv);
			byte[] bytes7 = getBytesFromModel(joinEv);
			byte[] bytes8 = getBytesFromModel(leaveEv);
			writeBytesToStream(bytes1);
			writeBytesToStream(bytes2);
			writeBytesToStream(bytes3);
			writeBytesToStream(bytes4);
			writeBytesToStream(bytes5);
			writeBytesToStream(bytes6);
			writeBytesToStream(bytes7);
			writeBytesToStream(bytes8);
		}
		while (true) {
			
		}
	}
	
	private void initModels() {
		Resource eventClass = schema.getResource("http://www.model.org/event");
		Resource memberEventClass = schema.getResource("http://www.model.org/memberEvent");
		Resource updateClass = schema.getResource("http://www.model.org/update");
		Resource documentEventClass = schema.getResource("http://www.model.org/documentEvent");
		Resource joinEvent = schema.getResource("http://www.model.org/join");
		Resource leaveEvent = schema.getResource("http://www.model.org/leave");
		Resource create = schema.getResource("http://www.model.org/create");
		Resource delete = schema.getResource("http://www.model.org/delete");
		
		updateEv = ModelFactory.createDefaultModel();
		Resource event1 = updateEv.createResource("http://www.test.org/event1");
		event1.addProperty(RDF.type,updateClass);
		Resource peer1 = updateEv.createResource("http://www.test.org/peer1");
		Property occurredAt = schema.getProperty("http://www.model.org/occurredAt");
		event1.addProperty(occurredAt, peer1);
		
		createEv = ModelFactory.createDefaultModel();
		Resource event2 = createEv.createResource("http://www.test.org/event2");
		event2.addProperty(RDF.type,create);
		Resource peer2 = createEv.createResource("http://www.test.org/peer2");
		event2.addProperty(occurredAt, peer2);
		
		deleteEv = ModelFactory.createDefaultModel();
		Resource event3 = deleteEv.createResource("http://www.test.org/event3");
		event3.addProperty(RDF.type,delete);
		Resource peer3 = deleteEv.createResource("http://www.test.org/peer3");
		event3.addProperty(occurredAt, peer3);
		
		documentEv = ModelFactory.createDefaultModel();
		Resource event4 = documentEv.createResource("http://www.test.org/event4");
		event4.addProperty(RDF.type,documentEventClass);
		Resource peer4 = documentEv.createResource("http://www.test.org/peer4");
		event4.addProperty(occurredAt, peer4);
		
		evEv = ModelFactory.createDefaultModel();
		Resource event5 = evEv.createResource("http://www.test.org/event5");
		event5.addProperty(RDF.type,eventClass);
		Resource peer5 = evEv.createResource("http://www.test.org/peer5");
		event5.addProperty(occurredAt, peer5);
		
		memEv = ModelFactory.createDefaultModel();
		Resource event6 = memEv.createResource("http://www.test.org/event6");
		event6.addProperty(RDF.type,memberEventClass);
		Resource peer6 = memEv.createResource("http://www.test.org/peer6");
		event6.addProperty(occurredAt, peer6);	
		
		joinEv = ModelFactory.createDefaultModel();
		Resource event7 = joinEv.createResource("http://www.test.org/event7");
		event7.addProperty(RDF.type,joinEvent);
		Resource peer7 = joinEv.createResource("http://www.test.org/peer7");
		event7.addProperty(occurredAt, peer7);	
		
		leaveEv = ModelFactory.createDefaultModel();
		Resource event8 = leaveEv.createResource("http://www.test.org/event8");
		event8.addProperty(RDF.type,leaveEvent);
		Resource peer8 = leaveEv.createResource("http://www.test.org/peer8");
		event8.addProperty(occurredAt, peer8);
	}
	
	private byte[] getBytesFromModel(Model model) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		model.write(baos, "RDF/XML");
		return baos.toByteArray();
	}
	
	private void writeBytesToStream(byte[] arr) {
		try {
			dout.writeInt(arr.length);
			dout.write(arr,0,arr.length);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
