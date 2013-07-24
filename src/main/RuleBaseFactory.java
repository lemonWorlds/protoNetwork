package main;

import stuff.SchemaSingleton;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

import interfaces.RuleBase;

public class RuleBaseFactory {
	private static RuleBase base = null;
	private static Model schema = SchemaSingleton.getSchema();

	public static RuleBase getBase() {
		if (base == null) {
			base = new RuleBaseImpl();
			
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
			
			Model rules6 = ModelFactory.createDefaultModel();
			Resource ruleEvent7 = rules6.createResource("http://www.rules.org/ruleEvent7");
			Resource leaveEvent = schema.getResource("http://www.model.org/leave");
			ruleEvent7.addProperty(RDF.type, leaveEvent);
			Resource action7 = rules6.createResource("http://www.rules.org/action7");
			ruleEvent7.addProperty(hasAction, action7);
			
			Model rules7 = ModelFactory.createDefaultModel();
			Resource ruleEvent8 = rules7.createResource("http://www.rules.org/ruleEvent8");
			Resource create = schema.getResource("http://www.model.org/create");
			ruleEvent8.addProperty(RDF.type, create);
			Resource action8 = rules7.createResource("http://www.rules.org/action8");
			ruleEvent8.addProperty(hasAction, action8);
			
			Model rules8 = ModelFactory.createDefaultModel();
			Resource ruleEvent9 = rules8.createResource("http://www.rules.org/ruleEvent9");
			Resource delete = schema.getResource("http://www.model.org/delete");
			ruleEvent9.addProperty(RDF.type, delete);
			Resource action9 = rules8.createResource("http://www.rules.org/action9");
			ruleEvent9.addProperty(hasAction, action9);
			
			Model rules9 = ModelFactory.createDefaultModel();
			Resource ruleEvent10 = rules9.createResource("http://www.rules.org/ruleEvent10");
			ruleEvent10.addProperty(RDF.type, delete);
			Resource action10 = rules9.createResource("http://www.rules.org/action10");
			ruleEvent10.addProperty(hasAction, action10);
			
			Model rules10 = ModelFactory.createDefaultModel();
			Resource ruleEvent11 = rules10.createResource("http://www.rules.org/ruleEvent11");
			ruleEvent11.addProperty(RDF.type, memberEventClass);
			Resource action11 = rules10.createResource("http://www.rules.org/action11");
			ruleEvent11.addProperty(hasAction, action11);
			
			Model rules11 = ModelFactory.createDefaultModel();
			Resource ruleEvent12 = rules11.createResource("http://www.rules.org/ruleEvent12");
			ruleEvent12.addProperty(RDF.type, joinEvent);
			Resource action12 = rules11.createResource("http://www.rules.org/action12");
			ruleEvent12.addProperty(hasAction, action12);
			
			Model rules12 = ModelFactory.createDefaultModel();
			Resource ruleEvent13 = rules12.createResource("http://www.rules.org/ruleEvent13");
			ruleEvent13.addProperty(RDF.type, documentEventClass);
			Resource action13 = rules12.createResource("http://www.rules.org/action13");
			ruleEvent13.addProperty(hasAction, action13);
			
			Model rules13 = ModelFactory.createDefaultModel();
			Resource ruleEvent14 = rules13.createResource("http://www.rules.org/ruleEvent14");
			ruleEvent14.addProperty(RDF.type, leaveEvent);
			Resource action14 = rules13.createResource("http://www.rules.org/action14");
			ruleEvent14.addProperty(hasAction, action14);
			
			Model rules14 = ModelFactory.createDefaultModel();
			Resource ruleEvent15 = rules14.createResource("http://www.rules.org/ruleEvent15");
			ruleEvent15.addProperty(RDF.type, eventClass);
			Resource action15 = rules14.createResource("http://www.rules.org/action15");
			ruleEvent15.addProperty(hasAction, action15);
			
			Model rules15 = ModelFactory.createDefaultModel();
			Resource ruleEvent16 = rules15.createResource("http://www.rules.org/ruleEvent16");
			ruleEvent16.addProperty(RDF.type, documentEventClass);
			Resource action16 = rules15.createResource("http://www.rules.org/action16");
			ruleEvent16.addProperty(hasAction, action16);
			
			Model rules16 = ModelFactory.createDefaultModel();
			Resource ruleEvent17 = rules16.createResource("http://www.rules.org/ruleEvent17");
			ruleEvent17.addProperty(RDF.type, create);
			Resource action17 = rules16.createResource("http://www.rules.org/action17");
			ruleEvent17.addProperty(hasAction, action17);
			
			Model rules17 = ModelFactory.createDefaultModel();
			Resource ruleEvent18 = rules17.createResource("http://www.rules.org/ruleEvent18");
			ruleEvent18.addProperty(RDF.type, updateClass);
			Resource action18 = rules17.createResource("http://www.rules.org/action18");
			ruleEvent18.addProperty(hasAction, action18);

			Model rules18 = ModelFactory.createDefaultModel();
			Resource ruleEvent19 = rules18.createResource("http://www.rules.org/ruleEvent19");
			ruleEvent19.addProperty(RDF.type, documentEventClass);
			Resource action19 = rules18.createResource("http://www.rules.org/action19");
			ruleEvent19.addProperty(hasAction, action19);
			
			Model rules19 = ModelFactory.createDefaultModel();
			Resource ruleEvent20 = rules19.createResource("http://www.rules.org/ruleEvent20");
			ruleEvent20.addProperty(RDF.type, documentEventClass);
			Resource action20 = rules19.createResource("http://www.rules.org/action20");
			ruleEvent20.addProperty(hasAction, action20);
			
			base.addRules(rules,rules1,rules2,rules3,rules4,rules5,rules6,rules7,rules8,rules9,rules10,rules11,rules12,rules13,rules14,rules15,rules16,rules17,rules18,rules19);
		}
		return base;
	}
}
