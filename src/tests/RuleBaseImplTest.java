package tests;

import java.util.List;

import interfaces.RuleBase;

import main.RuleBaseImpl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import stuff.SchemaSingleton;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class RuleBaseImplTest {
	private RuleBase base = null;
	private static Model schema = null;
	private static Model rules;
	private static Model rules1;
	private static Model rules2;
	private static Model rules3;
	private static Model rules4;
	private static Model rules5;
	private static Model rules6;
	private static Model rules7;
	private static Model rules8;
	private static Model rules9;
	private static Model rules10;
	private static Model rules11;
	private static Model rules12;
	private static Model rules13;
	private static Model rules14;
	private static Model rules15;
	private static Model rules16;
	private static Model rules17;
	private static Model rules18;
	private static Model rules19;
	
	private static Model updateEv;
	private static Model createEv;
	private static Model deleteEv;
	private static Model documentEv;
	private static Model evEv;
	private static Model memEv;
	private static Model joinEv;
	private static Model leaveEv;

	@BeforeClass
	public static void build() {
		schema = SchemaSingleton.getSchema();
		
		rules = ModelFactory.createDefaultModel();
		Resource ruleEvent1 = rules.createResource("http://www.rules.org/ruleEvent1");
		Resource eventClass = schema.getResource("http://www.model.org/event");
		ruleEvent1.addProperty(RDF.type, eventClass);
		Resource action1 = rules.createResource("http://www.rules.org/action1");
		Property hasAction = schema.getProperty("http://www.model.org/hasAction");
		ruleEvent1.addProperty(hasAction, action1);
		
		rules1 = ModelFactory.createDefaultModel();
		Resource ruleEvent2 = rules1.createResource("http://www.rules.org/ruleEvent2");
		Resource memberEventClass = schema.getResource("http://www.model.org/memberEvent");
		ruleEvent2.addProperty(RDF.type, memberEventClass);
		Resource action2 = rules1.createResource("http://www.rules.org/action2");
		ruleEvent2.addProperty(hasAction,action2);
		
		rules2 = ModelFactory.createDefaultModel();
		Resource ruleEvent3 = rules2.createResource("http://www.rules.org/ruleEvent3");
		Resource updateClass = schema.getResource("http://www.model.org/update");
		ruleEvent3.addProperty(RDF.type, updateClass);
		Resource action3 = rules2.createResource("http://www.rules.org/action3");
		ruleEvent3.addProperty(hasAction,action3);
		
		rules3 = ModelFactory.createDefaultModel();
		Resource ruleEvent4 = rules3.createResource("http://www.rules.org/ruleEvent4");
		Resource documentEventClass = schema.getResource("http://www.model.org/documentEvent");
		ruleEvent4.addProperty(RDF.type, documentEventClass);
		Resource action4 = rules3.createResource("http://www.rules.org/action4");
		ruleEvent4.addProperty(hasAction,action4);
		
		rules4 = ModelFactory.createDefaultModel();
		Resource ruleEvent5 = rules4.createResource("http://www.rules.org/ruleEvent5");
		Resource joinEvent = schema.getResource("http://www.model.org/join");
		ruleEvent5.addProperty(RDF.type, joinEvent);
		Resource action5 = rules4.createResource("http://www.rules.org/action5");
		ruleEvent5.addProperty(hasAction,action5);
		
		rules5 = ModelFactory.createDefaultModel();
		Resource ruleEvent6 = rules5.createResource("http://www.rules.org/ruleEvent6");
		ruleEvent6.addProperty(RDF.type, eventClass);
		Resource action6 = rules5.createResource("http://www.rules.org/action6");
		ruleEvent6.addProperty(hasAction, action6);
		
		rules6 = ModelFactory.createDefaultModel();
		Resource ruleEvent7 = rules6.createResource("http://www.rules.org/ruleEvent7");
		Resource leaveEvent = schema.getResource("http://www.model.org/leave");
		ruleEvent7.addProperty(RDF.type, leaveEvent);
		Resource action7 = rules6.createResource("http://www.rules.org/action7");
		ruleEvent7.addProperty(hasAction, action7);
		
		rules7 = ModelFactory.createDefaultModel();
		Resource ruleEvent8 = rules7.createResource("http://www.rules.org/ruleEvent8");
		Resource create = schema.getResource("http://www.model.org/create");
		ruleEvent8.addProperty(RDF.type, create);
		Resource action8 = rules7.createResource("http://www.rules.org/action8");
		ruleEvent8.addProperty(hasAction, action8);
		
		rules8 = ModelFactory.createDefaultModel();
		Resource ruleEvent9 = rules8.createResource("http://www.rules.org/ruleEvent9");
		Resource delete = schema.getResource("http://www.model.org/delete");
		ruleEvent9.addProperty(RDF.type, delete);
		Resource action9 = rules8.createResource("http://www.rules.org/action9");
		ruleEvent9.addProperty(hasAction, action9);
		
		rules9 = ModelFactory.createDefaultModel();
		Resource ruleEvent10 = rules9.createResource("http://www.rules.org/ruleEvent10");
		ruleEvent10.addProperty(RDF.type, delete);
		Resource action10 = rules9.createResource("http://www.rules.org/action10");
		ruleEvent10.addProperty(hasAction, action10);
		
		rules10 = ModelFactory.createDefaultModel();
		Resource ruleEvent11 = rules10.createResource("http://www.rules.org/ruleEvent11");
		ruleEvent11.addProperty(RDF.type, memberEventClass);
		Resource action11 = rules10.createResource("http://www.rules.org/action11");
		ruleEvent11.addProperty(hasAction, action11);
		
		rules11 = ModelFactory.createDefaultModel();
		Resource ruleEvent12 = rules11.createResource("http://www.rules.org/ruleEvent12");
		ruleEvent12.addProperty(RDF.type, joinEvent);
		Resource action12 = rules11.createResource("http://www.rules.org/action12");
		ruleEvent12.addProperty(hasAction, action12);
		
		rules12 = ModelFactory.createDefaultModel();
		Resource ruleEvent13 = rules12.createResource("http://www.rules.org/ruleEvent13");
		ruleEvent13.addProperty(RDF.type, documentEventClass);
		Resource action13 = rules12.createResource("http://www.rules.org/action13");
		ruleEvent13.addProperty(hasAction, action13);
		
		rules13 = ModelFactory.createDefaultModel();
		Resource ruleEvent14 = rules13.createResource("http://www.rules.org/ruleEvent14");
		ruleEvent14.addProperty(RDF.type, leaveEvent);
		Resource action14 = rules13.createResource("http://www.rules.org/action14");
		ruleEvent14.addProperty(hasAction, action14);
		
		rules14 = ModelFactory.createDefaultModel();
		Resource ruleEvent15 = rules14.createResource("http://www.rules.org/ruleEvent15");
		ruleEvent15.addProperty(RDF.type, eventClass);
		Resource action15 = rules14.createResource("http://www.rules.org/action15");
		ruleEvent15.addProperty(hasAction, action15);
		
		rules15 = ModelFactory.createDefaultModel();
		Resource ruleEvent16 = rules15.createResource("http://www.rules.org/ruleEvent16");
		ruleEvent16.addProperty(RDF.type, documentEventClass);
		Resource action16 = rules15.createResource("http://www.rules.org/action16");
		ruleEvent16.addProperty(hasAction, action16);
		
		rules16 = ModelFactory.createDefaultModel();
		Resource ruleEvent17 = rules16.createResource("http://www.rules.org/ruleEvent17");
		ruleEvent17.addProperty(RDF.type, create);
		Resource action17 = rules16.createResource("http://www.rules.org/action17");
		ruleEvent17.addProperty(hasAction, action17);
		
		rules17 = ModelFactory.createDefaultModel();
		Resource ruleEvent18 = rules17.createResource("http://www.rules.org/ruleEvent18");
		ruleEvent18.addProperty(RDF.type, updateClass);
		Resource action18 = rules17.createResource("http://www.rules.org/action18");
		ruleEvent18.addProperty(hasAction, action18);

		rules18 = ModelFactory.createDefaultModel();
		Resource ruleEvent19 = rules18.createResource("http://www.rules.org/ruleEvent19");
		ruleEvent19.addProperty(RDF.type, documentEventClass);
		Resource action19 = rules18.createResource("http://www.rules.org/action19");
		ruleEvent19.addProperty(hasAction, action19);
		
		rules19 = ModelFactory.createDefaultModel();
		Resource ruleEvent20 = rules19.createResource("http://www.rules.org/ruleEvent20");
		ruleEvent20.addProperty(RDF.type, documentEventClass);
		Resource action20 = rules19.createResource("http://www.rules.org/action20");
		ruleEvent20.addProperty(hasAction, action20);
		
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
	
	@Before
	public void setUp() {
		base = new RuleBaseImpl();
	}
	
	@After
	public void tearUp() {
		base = null;
	}

	@Test
	public void testSingleRuleWithRepeat() {	
		base.addRules(rules);
		List<String> result = base.matchEventToRules(evEv);
		Assert.assertEquals(result.size(),1);
		String uri = result.get(0);
		Assert.assertEquals(uri, "http://www.rules.org/action1");
		
		List<String> result1 = base.matchEventToRules(documentEv);
		Assert.assertEquals(result1.size(),1);
		String uri1 = result1.get(0);
		Assert.assertEquals(uri1, "http://www.rules.org/action1");
		
		List<String> result2 = base.matchEventToRules(joinEv);
		Assert.assertEquals(result2.size(),1);
		String uri2 = result2.get(0);
		Assert.assertEquals(uri2, "http://www.rules.org/action1");
		
		List<String> result3 = base.matchEventToRules(evEv);
		Assert.assertEquals(result3.size(),1);
		String uri3 = result3.get(0);
		Assert.assertEquals(uri3, "http://www.rules.org/action1");
		
		List<String> result4 = base.matchEventToRules(joinEv);
		Assert.assertEquals(result4.size(),1);
		String uri4 = result4.get(0);
		Assert.assertEquals(uri4, "http://www.rules.org/action1");
		
		List<String> result5 = base.matchEventToRules(joinEv);
		Assert.assertEquals(result5.size(),1);
		String uri5 = result5.get(0);
		Assert.assertEquals(uri5, "http://www.rules.org/action1");
		
		List<String> result6 = base.matchEventToRules(joinEv);
		Assert.assertEquals(result6.size(),1);
		String uri6 = result6.get(0);
		Assert.assertEquals(uri6, "http://www.rules.org/action1");
	}
	
	@Test 
	public void testSeveralRulesWithRepeat() {
		base.addRules(rules,rules1,rules2,rules3,rules4,rules5,rules6);
		List<String> result1 = base.matchEventToRules(documentEv);
		Assert.assertEquals(result1.size(),3);
		Assert.assertTrue(result1.contains("http://www.rules.org/action1") &&
				                            result1.contains("http://www.rules.org/action4") 
				                            && result1.contains("http://www.rules.org/action6"));
		Assert.assertFalse(result1.contains("http://www.rules.org/action2"));
		Assert.assertFalse(result1.contains("http://www.rules.org/action5"));
		Assert.assertFalse(result1.contains("http://www.rules.org/action7"));
		Assert.assertFalse(result1.contains("http://www.rules.org/action3"));
		
		List<String> result2 = base.matchEventToRules(documentEv);
		Assert.assertEquals(result2.size(),3);
		Assert.assertTrue(result2.contains("http://www.rules.org/action1") &&
				                            result1.contains("http://www.rules.org/action4") 
				                            && result1.contains("http://www.rules.org/action6"));
		Assert.assertFalse(result2.contains("http://www.rules.org/action2"));
		Assert.assertFalse(result2.contains("http://www.rules.org/action5"));
		Assert.assertFalse(result2.contains("http://www.rules.org/action7"));
		Assert.assertFalse(result2.contains("http://www.rules.org/action3"));
		
		List<String> result3 = base.matchEventToRules(documentEv);
		Assert.assertEquals(result3.size(),3);
		Assert.assertTrue(result3.contains("http://www.rules.org/action1") &&
				                            result1.contains("http://www.rules.org/action4") 
				                            && result1.contains("http://www.rules.org/action6"));
		Assert.assertFalse(result3.contains("http://www.rules.org/action2"));
		Assert.assertFalse(result3.contains("http://www.rules.org/action5"));
		Assert.assertFalse(result3.contains("http://www.rules.org/action7"));
		Assert.assertFalse(result3.contains("http://www.rules.org/action3"));
		
		List<String> result4 = base.matchEventToRules(joinEv);
		Assert.assertEquals(result4.size(),4);
		Assert.assertTrue(result4.contains("http://www.rules.org/action1") &&
				                            result4.contains("http://www.rules.org/action5") && result4.contains("http://www.rules.org/action2")
				                            && result4.contains("http://www.rules.org/action6"));
		Assert.assertFalse(result4.contains("http://www.rules.org/action3"));
		Assert.assertFalse(result4.contains("http://www.rules.org/action4"));
		Assert.assertFalse(result4.contains("http://www.rules.org/action7"));
		
		List<String> result5 = base.matchEventToRules(evEv);
		Assert.assertEquals(result5.size(),2);
	}
	
	public void testTwentyRules() {
		base.addRules(rules,rules1,rules2,rules3,rules4,rules5,rules6,rules7,rules8,rules9,rules10,rules11,rules12,rules13,rules14,rules15,rules16,rules17,rules18,rules19);
		List<String> result1 = base.matchEventToRules(evEv);
		Assert.assertEquals(result1.size(), 3);
		List<String> result2 = base.matchEventToRules(leaveEv);
		Assert.assertEquals(result2.size(),7);
	}

}
