package stuff;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;

public class EventExtractor {
	private static final String QUERYSTR = "PREFIX ex: <http://www.model.org/> " +
			                  "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
			                  "SELECT ?type " +
			                  "WHERE {" +
			                  "      ?ev ex:occurredAt ?peer . " +
			                  "      ?ev rdf:type ?type" +
			                  "}";
	private static Query query = null;
	
	public EventExtractor() {
		query = QueryFactory.create(QUERYSTR);
	}
	
	public String extract(Model model) {
		QueryExecution execution = QueryExecutionFactory.create(query,model);
		ResultSet results = execution.execSelect();
		String result = null;
		if (results.hasNext()) {
			result = results.next().getResource("type").getURI();
		}
		if (results.hasNext() || result == null) {
			throw new IllegalArgumentException();
		}
		return result;
	}
}
