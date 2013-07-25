package stuff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReliableMessageStore {
	private static Map<String,List<String>> storedMessages = null;
	
	private ReliableMessageStore() {
		
	}
	
	public static Map<String,List<String>> getMessageStore() {
		if (storedMessages == null) {
			storedMessages = new HashMap<String,List<String>>();
			/*
			 * For testing
			 */
			List<String> list = new ArrayList<>();
			list.add("Did this work?");
			list.add("Looks like it...");
			storedMessages.put("Clive", list);
		}
		return storedMessages;
	}
}
