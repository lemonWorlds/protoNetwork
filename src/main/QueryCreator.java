package main;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;

public class QueryCreator {
	
	public static final String FIND_EVENT_TYPE = "PREFIX ex: <http://www.model.org/> " +
											     "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
											     "SELECT ?type " +
											     "WHERE {" +
											      "      ?ev ex:occurredAt ?peer . " +
											      "      ?ev rdf:type ?type" +
											      "}";
	
	public static final String FIND_EVENT_TYPE_VAR = "type";
	
	public static final String FIND_RULE_EVENT_CLASS = "PREFIX ex: <http://www.model.org/> " +
													   "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
											           "SELECT ?type " +
											           "WHERE {" +
											           "      ?ev ex:hasAction ?action . " +
											           "      ?ev rdf:type ?type" +
											           "}";
	
	public static final String FIND_RULE_EVENT_CLASS_VAR = "type";
	
	
	public static final String FIND_EVENT_SUPERCLASS_PT1 = "PREFIX ex: <http://www.model.org/> " +
												           "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
															"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
															"SELECT DISTINCT ?class " +
															"WHERE {" +
															"      ?class rdfs:subClassOf ex:event . " +
															"      ex:";

	public static final String FIND_EVENT_SUPERCLASS_PT2 = " rdfs:subClassOf ?class . " +
				                                            "}";
	
	
	public static final String FIND_EVENT_SUPERCLASS_VAR = "class";
	
	public static final String FIND_RULE_ACTION = "PREFIX ex: <http://www.model.org/> " +
												  "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
										          "SELECT ?action " +
										          "WHERE {" +
										          "      ?ev ex:hasAction ?action . " +
										          "}";
	
	public static final String FIND_RULE_ACTION_VAR = "action";
	
	private static Query createQueryFromString(String string) {
		return QueryFactory.create(string);
	}
	
	public static Query getRuleEventQuery() {
		return createQueryFromString(FIND_RULE_EVENT_CLASS);
	}
	
	public static Query getEventSuperclassQuery(String event) {
		return createQueryFromString(FIND_EVENT_SUPERCLASS_PT1 + event + FIND_EVENT_SUPERCLASS_PT2);
	}
	
	public static Query getEventType() {
		return createQueryFromString(FIND_EVENT_TYPE);
	}
	
	public static Query getActionQuery() {
		return createQueryFromString(FIND_RULE_ACTION);
	}
}
