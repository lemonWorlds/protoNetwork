package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import interfaces.RuleBase;
import interfaces.SuperClassCache;

public class RuleBaseImpl implements RuleBase {

	private Map<String, Set<Model>> ruleSet = new HashMap<>();
	public static final int SIZE_OF_NAMESPACE_PREFIX = 21;
	private SuperClassCache cache = new SimpleCacheImpl();

	@Override
	public void addRules(Model... model) {
		for (Model next: model) {
			ResultSet results = QueryProcessor.processSelectQuery(QueryCreator.getRuleEventQuery(), next);
			String resultURI = ensureSingleResult(results, QueryCreator.FIND_RULE_EVENT_CLASS_VAR);
			if (!ruleSet.containsKey(resultURI)) {
				Set<Model> emptyModelSet  = new HashSet<>();
				emptyModelSet.add(next);
				ruleSet.put(resultURI, emptyModelSet);
			} else {
				Set<Model> modelSet = ruleSet.get(resultURI);
				modelSet.add(next);
			}
			System.out.println(ruleSet);
		}
	}

	@Override
	public List<String> matchEventToRules(Model model) {
		//Create final results list
		List<String> actionsList = new ArrayList<String>();
		
		//Get event type from event model
		ResultSet results = QueryProcessor.processSelectQuery(QueryCreator.getEventType(), model);
		String resultURI = ensureSingleResult(results, QueryCreator.FIND_EVENT_TYPE_VAR);
		String eventType = resultURI.substring(SIZE_OF_NAMESPACE_PREFIX);
		//Get event superclasses
		List<String> superClasses = cache.getSuperClasses(eventType);
		
		//Find rules that match superclasses in base
		for (String next: superClasses) {
			Set<Model> modelSet = ruleSet.get(next);
			if (modelSet != null) {
				for (Model each: modelSet) {
					ResultSet action = QueryProcessor.processSelectQuery(QueryCreator.getActionQuery(), each);
					String actionURI = ensureSingleResult(action, QueryCreator.FIND_RULE_ACTION_VAR);
					actionsList.add(actionURI);
				}
			}
		}
		return actionsList;
	}
	
	private String ensureSingleResult(ResultSet results, String variable) {
		String result = null;
		if (results.hasNext()) {
			result = results.next().getResource(variable).getURI();
		}
		if (results.hasNext() || result == null) {
			throw new IllegalArgumentException();
		}
		return result;
	}

}
