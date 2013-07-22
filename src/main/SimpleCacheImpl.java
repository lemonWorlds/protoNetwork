package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;

import stuff.SchemaSingleton;

import interfaces.SuperClassCache;

public class SimpleCacheImpl implements SuperClassCache {
	
	private Model schema = SchemaSingleton.getSchema();
	private Map<String, List<String>> cache = new HashMap<>();

	@Override
	public List<String> getSuperClasses(String eventType) {
		List<String> results = cache.get(eventType);
		if (results == null) {
			String query = QueryCreator.FIND_EVENT_SUPERCLASS_PT1 + eventType + QueryCreator.FIND_EVENT_SUPERCLASS_PT2;
			ResultSet types = QueryProcessor.processSelectQuery(QueryCreator.getEventSuperclassQuery(query), schema);
			results = new ArrayList<>();
			while (types.hasNext()) {
				results.add(types.next().getResource(QueryCreator.FIND_EVENT_SUPERCLASS_VAR).getURI());
			}
			cache.put(eventType, results);
		}
		return results;
	}

}
