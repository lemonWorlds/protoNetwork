package main;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;

public class QueryProcessor {
	public static ResultSet processSelectQuery(Query query, Model model) {
		QueryExecution execution = QueryExecutionFactory.create(query,model);
		return execution.execSelect();
	}
}
