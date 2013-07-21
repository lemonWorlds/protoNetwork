package stuff;

import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class Driver {

	public static void main(String[] args) {
		new Driver().launch();
	}

	private void launch() {
		InfModel schema = SchemaSingleton.getSchema();
		Model model = ModelFactory.createDefaultModel();
		
		//Create event of type with occurrence at property 
		Resource event = model.createResource("http://www.test.org/event1");
		Resource eventClass = schema.getResource("http://www.model.org/memberEvent");
		event.addProperty(RDF.type,eventClass);
		Resource peer = model.createResource("http://www.test.org/peer1");
		Property occurredAt = schema.getProperty("http://www.model.org/occurredAt");
		event.addProperty(occurredAt, peer);
		
		//Extract event type from model
		EventExtractor extractor = new EventExtractor();
		String result = extractor.extract(model);
		System.out.println(result);
		
	}

}
