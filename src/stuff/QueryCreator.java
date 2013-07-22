package stuff;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;

public class QueryCreator {
	private static final String FIND_EVENT_TYPE = "PREFIX ex: <http://www.model.org/> " +
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
            "SELECT ?type " +
            "WHERE {" +
            "      ?ev ex:occurredAt ?peer . " +
            "      ?ev rdf:type ?type" +
            "}";
	
	private static Query getQueryFromString(String str) {
		return QueryFactory.create(str);
	}
	
	public static Query getFindEventTypeQuery() {
		return getQueryFromString(FIND_EVENT_TYPE);
	}

}
