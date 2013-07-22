package main;

import java.util.List;

import org.apache.log4j.BasicConfigurator;

import stuff.SchemaSingleton;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

import interfaces.RuleBase;

public class Driver {

	public static void main(String[] args) {
		new Driver().launch();
	}

	private void launch() {
		
		//create base, empty model and schema
		RuleBase base = new RuleBaseImpl();
		Model schema = SchemaSingleton.getSchema();
		Model model = ModelFactory.createDefaultModel();
		
		//Create event of type with occurrence at peer 
		Resource event = model.createResource("http://www.test.org/event1");
		Resource update = schema.getResource("http://www.model.org/update");
		event.addProperty(RDF.type,update);
		Resource peer = model.createResource("http://www.test.org/peer1");
		Property occurredAt = schema.getProperty("http://www.model.org/occurredAt");
		event.addProperty(occurredAt, peer);
		
		//Create a rule
		Model rules = ModelFactory.createDefaultModel();
		Resource ruleEvent1 = rules.createResource("http://www.rules.org/ruleEvent1");
		Resource eventClass = schema.getResource("http://www.model.org/event");
		ruleEvent1.addProperty(RDF.type, eventClass);
		Resource action1 = rules.createResource("http://www.rules.org/action1");
		Property hasAction = schema.getProperty("http://www.model.org/hasAction");
		ruleEvent1.addProperty(hasAction, action1);
		
		Model rules1 = ModelFactory.createDefaultModel();
		Resource ruleEvent2 = rules1.createResource("http://www.rules.org/ruleEvent2");
		Resource memberEventClass = schema.getResource("http://www.model.org/memberEvent");
		ruleEvent2.addProperty(RDF.type, memberEventClass);
		Resource action2 = rules1.createResource("http://www.rules.org/action2");
		ruleEvent2.addProperty(hasAction,action2);
		
		Model rules2 = ModelFactory.createDefaultModel();
		Resource ruleEvent3 = rules2.createResource("http://www.rules.org/ruleEvent3");
		Resource updateClass = schema.getResource("http://www.model.org/update");
		ruleEvent3.addProperty(RDF.type, updateClass);
		Resource action3 = rules2.createResource("http://www.rules.org/action3");
		ruleEvent3.addProperty(hasAction,action3);
		
		Model rules3 = ModelFactory.createDefaultModel();
		Resource ruleEvent4 = rules3.createResource("http://www.rules.org/ruleEvent4");
		Resource documentEventClass = schema.getResource("http://www.model.org/documentEvent");
		ruleEvent4.addProperty(RDF.type, documentEventClass);
		Resource action4 = rules3.createResource("http://www.rules.org/action4");
		ruleEvent4.addProperty(hasAction,action4);
		
		Model rules4 = ModelFactory.createDefaultModel();
		Resource ruleEvent5 = rules4.createResource("http://www.rules.org/ruleEvent5");
		Resource joinEvent = schema.getResource("http://www.model.org/join");
		ruleEvent5.addProperty(RDF.type, joinEvent);
		Resource action5 = rules4.createResource("http://www.rules.org/action5");
		ruleEvent5.addProperty(hasAction,action5);
		
		Model rules5 = ModelFactory.createDefaultModel();
		Resource ruleEvent6 = rules5.createResource("http://www.rules.org/ruleEvent6");
		ruleEvent6.addProperty(RDF.type, eventClass);
		Resource action6 = rules5.createResource("http://www.rules.org/action6");
		ruleEvent6.addProperty(hasAction, action6);
		
		base.addRules(rules,rules1,rules2,rules3,rules4,rules5);
		
		List<String> result = base.matchEventToRules(model);
		for (String next: result) {
			System.out.println(next);
		}
	}

}
