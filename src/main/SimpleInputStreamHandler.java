package main;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import interfaces.InputStreamHandler;

public class SimpleInputStreamHandler implements InputStreamHandler, Runnable {
	private DataInputStream stream = null;
	
	public SimpleInputStreamHandler(InputStream in) {
		stream = new DataInputStream(in);
	}

	@Override
	public void run() {
		try {
			while (true) {
				Model model = getDataModel(stream);
				StmtIterator iter = model.listStatements();
				while (iter.hasNext()) {
					System.out.println(iter.next());
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
	
	private String getInputData(DataInputStream stream) throws IOException {		
		int size = stream.readInt();
		byte[] data = new byte[size];
		stream.read(data,0,size);
		return new String(data);
	}
	
	private Model getDataModel(DataInputStream stream) throws IOException {
		String data = getInputData(stream);
		Model model = ModelFactory.createDefaultModel();
		return model.read(new StringReader(data),null);
	}

}
