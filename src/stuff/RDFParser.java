package stuff;

import java.io.ByteArrayOutputStream;
import java.io.Reader;
import java.io.StringReader;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.RDF;

public class RDFParser {
	private Model schema = SchemaSingleton.getSchema();
	private Model updateEv;

	public static void main(String[] args) {
		new RDFParser().launch();
	}

	private void launch() {
		//Create event model
		Resource updateClass = schema.getResource("http://www.model.org/update");
		updateEv = ModelFactory.createDefaultModel();
		Resource event1 = updateEv.createResource("http://www.test.org/event1");
		event1.addProperty(RDF.type,updateClass);
		Resource peer1 = updateEv.createResource("http://www.test.org/peer1");
		Property occurredAt = schema.getProperty("http://www.model.org/occurredAt");
		event1.addProperty(occurredAt, peer1);
		
		//turn into byte-stream and then string
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		updateEv.write(baos, "RDF/XML");
		byte[] arr = baos.toByteArray();

		String data = new String(arr);
		
		//Create reader and read into model again
		Reader reader = new StringReader(data);
		
		
		
		Model myModel = ModelFactory.createDefaultModel();
		
		/*
		myModel.read(reader,null);
		*/
		myModel.read(reader, null);
		StmtIterator iter = myModel.listStatements();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

}
