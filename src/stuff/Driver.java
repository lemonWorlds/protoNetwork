package stuff;

import java.util.List;

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
		Model schema = SchemaSingleton.getSchema();
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
		//System.out.println(result);
		
		//New rule base
		RuleBaseTree base = new RuleBaseTree();
		
		//Create a model of some rules
		Model rules = ModelFactory.createDefaultModel();
		Resource ruleEvent1 = rules.createResource("http://www.rules.org/ruleEvent1");
		Resource eventClass = schema.getResource("http://www.model.org/event");
		ruleEvent1.addProperty(RDF.type, eventClass);
		Resource action1 = rules.createResource("http://www.rules.org/action1");
		Property hasAction = schema.getProperty("http://www.model.org/hasAction");
		ruleEvent1.addProperty(hasAction, action1);
		/*
		//Create more rules
		Resource ruleEvent2 = rules.createResource("http://www.rules.org/ruleEvent2");
		Resource memberEventClass = schema.getResource("http://www.model.org/memberEvent");
		ruleEvent2.addProperty(RDF.type, memberEventClass);
		Resource action2 = rules.createResource("http://www.rules.org/action2");
		ruleEvent2.addProperty(hasAction,action2);
		
		Resource ruleEvent3 = rules.createResource("http://www.rules.org/ruleEvent3");
		Resource updateClass = schema.getResource("http://www.model.org/update");
		ruleEvent3.addProperty(RDF.type, updateClass);
		Resource action3 = rules.createResource("http://www.rules.org/action3");
		ruleEvent3.addProperty(hasAction,action3);
		
		Resource ruleEvent4 = rules.createResource("http://www.rules.org/ruleEvent4");
		Resource documentEventClass = schema.getResource("http://www.model.org/documentEvent");
		ruleEvent4.addProperty(RDF.type, documentEventClass);
		Resource action4 = rules.createResource("http://www.rules.org/action4");
		ruleEvent4.addProperty(hasAction,action4);
		
		Resource ruleEvent5 = rules.createResource("http://www.rules.org/ruleEvent5");
		Resource joinEvent = schema.getResource("http://www.model.org/join");
		ruleEvent5.addProperty(RDF.type, joinEvent);
		Resource action5 = rules.createResource("http://www.rules.org/action5");
		ruleEvent5.addProperty(hasAction,action5);
		*/
		//Add rules to base
		base.addRule(rules);
		/*
		List<String> listResults = base.matchEventToActions(result);
		for (String each: listResults) {
			System.out.println(each);
		}*/
	}

}
