package peer;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XMLtoMethodCall {

	public static void main(String[] args) {
		new XMLtoMethodCall().launch("H:\\newWorkspace\\protoNetwork\\src\\peer\\testRPC.xml");
	}

	private void launch(String fileName) {
		try {
			File file = new File(fileName);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			document.getDocumentElement().normalize();
			
			String methodName = getMethodName(document);
			String[] params = getStringParams(document);
			System.out.println("Call " + methodName + " with " + params);
			
			String[] classAndMethod = methodName.split("\\.");

			Class aClass = Class.forName(classAndMethod[0]+ "." +classAndMethod[1]);
			Method aMethod = aClass.getMethod(classAndMethod[2],new Class[]{String[].class});
			aMethod.invoke(aClass.newInstance(), new Object[]{params});
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private String getMethodName(Document document) {
		NodeList nodeList = document.getElementsByTagName("methodName");
		if (nodeList.getLength() != 1) {
			throw new IllegalArgumentException();
		}
		return nodeList.item(0).getTextContent();
	}
	
	private String[] getStringParams(Document document) {
		NodeList nodeList = document.getElementsByTagName("param");
		String[] params = new String[nodeList.getLength()];
		for (int i = 0; i < nodeList.getLength(); i++) {
			params[i] = nodeList.item(i).getTextContent();
		}
		return params;
	}
	
	
	
	

}
