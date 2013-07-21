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
		Resource update = schema.getResource("http://www.model.org/update");
		event.addProperty(RDF.type,update);
		Resource peer = model.createResource("http://www.test.org/peer1");
		Property occurredAt = schema.getProperty("http://www.model.org/occurredAt");
		event.addProperty(occurredAt, peer);
		
		//Extract event type from model
		EventExtractor extractor = new EventExtractor();
		String result = extractor.extract(model);
		System.out.println(result);
		
		//New rule base
		RuleBase base = new RuleBase();
		
		//Create a model of some rules
		Model rules = ModelFactory.createDefaultModel();
		Resource ruleEvent1 = rules.createResource("http://www.rules.org/ruleEvent1");
		Resource eventClass = schema.getResource("http://www.model.org/event");
		ruleEvent1.addProperty(RDF.type, eventClass);
		Resource action1 = rules.createResource("http://www.rules.org/action1");
		Property hasAction = schema.getProperty("http://www.model.org/hasAction");
		ruleEvent1.addProperty(hasAction, action1);
		
		//Add rules to base
		base.addRule(rules);
		
		base.matchEventToActions(result);
	}

}
