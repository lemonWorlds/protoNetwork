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
		Resource event = model.createResource("http://www.test.org/event1");
		Resource anotherEvent = model.createResource("http://www.check.com/event65");
		Resource eventClass = schema.getResource("http://www.model.org/memberEvent");
		Resource update = schema.getResource("http://www.model.org/update");
		anotherEvent.addProperty(RDF.type, update);
		anotherEvent.addProperty(RDF.type, eventClass);
		event.addProperty(RDF.type,eventClass);
		Resource peer = model.createResource("http://www.test.org/peer1");
		Property occurredAt = schema.getProperty("http://www.model.org/occurredAt");
		event.addProperty(occurredAt, peer);
		anotherEvent.addProperty(occurredAt, peer);
		EventExtractor extractor = new EventExtractor();
		extractor.extract(model);
	}

}
