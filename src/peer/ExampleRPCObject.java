package peer;

import java.util.List;

public class ExampleRPCObject {
	public void aMethod(String[] listOfPeers) {
		System.out.println("Sent updates to:");
		for (String next: listOfPeers) {
			System.out.println(next);
		}
	}
}
