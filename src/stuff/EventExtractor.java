package stuff;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;

public class EventExtractor {
	//private static final InfModel schema = SchemaSingleton.getSchema();
	private static final String queryStr = "PREFIX ex: <http://www.model.org/> " +
			                  "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
			                  "SELECT ?type " +
			                  "WHERE {" +
			                  "      ?ev ex:occurredAt ?peer . " +
			                  "      ?ev rdf:type ?type" +
			                  "}";
	private static Query query = null;
	
	public EventExtractor() {
		query = QueryFactory.create(queryStr);
	}
	
	public String extract(Model model) {
		QueryExecution execution = QueryExecutionFactory.create(query,model);
		ResultSet results = execution.execSelect();
		while (results.hasNext()) {
			System.out.println(results.next());
		}
		return null;
	}
}
