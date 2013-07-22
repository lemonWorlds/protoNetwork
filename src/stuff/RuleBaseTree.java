package stuff;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;

public class RuleBaseTree {
	private Model schema = SchemaSingleton.getSchema();
	
	private static final int SIZE_OF_NAMESPACE_PREFIX = 21;
	
	private static final String FIND_EVENT_SUPERCLASS_PT1 = "PREFIX ex: <http://www.model.org/> " +
			                  				              "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
						                  				  "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
						                  				  "SELECT DISTINCT ?class " +
						                  				  "WHERE {" +
						                  				  "      ?class rdfs:subClassOf ex:event . " +
						                  				  "      ex:";
	
	private static final String FIND_EVENT_SUPERCLASS_PT2 = " rdfs:subClassOf ?class . " +
															"}";
	
	private static final String FIND_RULE_EVENT_CLASS = "PREFIX ex: <http://www.model.org/> " +
											            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
											            "SELECT ?type " +
											            "WHERE {" +
											            "      ?ev ex:hasAction ?action . " +
											            "      ?ev rdf:type ?type" +
											            "}";
	
	private Map<String,Model> rules = new HashMap<String,Model>();
											
	public void addRule(Model model) {
		System.out.println(rules);
		Query query = QueryFactory.create(FIND_RULE_EVENT_CLASS);
		QueryExecution execution = QueryExecutionFactory.create(query,model);
		ResultSet results = execution.execSelect();
		String resultURI = null;
		if (results.hasNext()) {
			resultURI = results.next().getResource("type").getURI();
		}
		if (results.hasNext() || resultURI == null) {
			throw new IllegalArgumentException();
		}
		System.out.println(resultURI);
		rules.put(resultURI, model);
		System.out.println(rules);
	}
	
	public List<String> matchEventToActions(String eventType) {
		Query query = QueryFactory.create(FIND_EVENT_SUPERCLASS_PT1 + eventType.substring(SIZE_OF_NAMESPACE_PREFIX) + FIND_EVENT_SUPERCLASS_PT2);
		QueryExecution execution = QueryExecutionFactory.create(query,schema);
		ResultSet results = execution.execSelect();
		while (results.hasNext()) {
			System.out.println(results.next());
		}
		return null;
	}
	
}
