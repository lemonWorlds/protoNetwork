package stuff;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;

public class RuleBase {
	private Model model = SchemaSingleton.getSchema();
	
	private static final String QUERY_PT_1 = "PREFIX ex: <http://www.model.org/> " +
			                  				 "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
			                  				 "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
			                  				 "SELECT DISTINCT ?action " +
			                  				 "WHERE {" +
			                  				 "     ?instance rdf:type ?ev . " +
			                  				 "     ?ev rdfs:subClassOf ex:event . " +
			                  				 "     ex:";
			                  				 
	private static final String QUERY_PT_2 = " rdfs:subClassOf ?ev . " +
			                                 "?instance ex:hasAction ?action . " +
											 "}";

	public void addRule(Model model) {
		this.model.add(model);
		this.model.write(System.out);
	}
	
	public List<String> matchEventToActions(String eventType) {
		Query query = QueryFactory.create(QUERY_PT_1 + eventType + QUERY_PT_2);
		QueryExecution execution = QueryExecutionFactory.create(query,model);
		ResultSet results = execution.execSelect();
		List<String> actions = new ArrayList<String>();
		while (results.hasNext()) {
			actions.add(results.next().getResource("action").getURI());
		}
		return actions;
	}

}
