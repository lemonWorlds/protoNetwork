package stuff;

import java.io.FileOutputStream;


import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;


public class WriteSchema {

	public static void main(String[] args) {
		new WriteSchema().launch();		
	}

	private void launch() {
		try {
			Model model = ModelFactory.createDefaultModel();
			Resource eventClass = model.createResource("http://www.model.org/event");
			Resource peerClass = model.createResource("http://www.model.org/peer");
			Property occurredAt = model.createProperty("http://www.model.org/occurredAt");
			Resource document = model.createResource("http://www.model.org/document");
			Resource membershipEvent = model.createResource("http://www.model.org/memberEvent");
			document.addProperty(RDF.type,RDFS.Class);
			Property appliedTo = model.createProperty("http://www.model.org/appliedTo");
			appliedTo.addProperty(RDFS.domain, eventClass);
			appliedTo.addProperty(RDFS.range,document);
			occurredAt.addProperty(RDFS.domain, eventClass);
			occurredAt.addProperty(RDFS.range, peerClass);
			peerClass.addProperty(RDF.type,RDFS.Class);
			eventClass.addProperty(RDF.type, RDFS.Class);
			Resource updateEvent = model.createResource("http://www.model.org/update");
			Resource documentEvent = model.createResource("http://www.model.org/documentEvent");
			updateEvent.addProperty(RDFS.subClassOf, documentEvent);
			documentEvent.addProperty(RDFS.subClassOf, eventClass);
			Resource deleteEvent = model.createResource("http://www.model.org/delete");
			deleteEvent.addProperty(RDFS.subClassOf, documentEvent);
			Resource createEvent = model.createResource("http://www.model.org/create");
			createEvent.addProperty(RDFS.subClassOf, documentEvent);
			membershipEvent.addProperty(RDFS.subClassOf, eventClass);
			Resource joinEvent = model.createResource("http://www.model.org/join");
			joinEvent.addProperty(RDFS.subClassOf, membershipEvent);
			Resource leaveEvent = model.createResource("http://www.model.org/leave");
			leaveEvent.addProperty(RDFS.subClassOf, membershipEvent);
			FileOutputStream out = new FileOutputStream("H:\\newWorkspace\\protoNetwork\\src\\stuff\\schema.rdf");
			FileOutputStream out1 = new FileOutputStream("H:\\newWorkspace\\protoNetwork\\src\\stuff\\schemaNice.rdf");
			
			Resource action = model.createResource("http://www.model.org/action");
			action.addProperty(RDF.type, RDFS.Class);
			Property hasAction = model.createProperty("http://www.model.org/hasAction");
			hasAction.addProperty(RDFS.domain, eventClass);
			hasAction.addProperty(RDFS.range, action);
			
			model.write(out);
			model.write(out1, "N-TRIPLE");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
