package stuff;

import java.io.InputStream;

import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.ReasonerVocabulary;

public class SchemaSingleton {
	private static InfModel infModel = null;
	
	private SchemaSingleton() {
	}
	
	public static Model getSchema() {
		if (infModel == null) {
			Model model = ModelFactory.createDefaultModel();
			InputStream in = FileManager.get().open("H:\\newWorkspace\\protoNetwork\\src\\stuff\\schema.rdf");
			model.read(in, null);
			Reasoner r = ReasonerRegistry.getRDFSReasoner();
			r.setParameter(ReasonerVocabulary.PROPsetRDFSLevel, ReasonerVocabulary.RDFS_SIMPLE);
			infModel = ModelFactory.createInfModel(r,model);
		}
		return infModel;
	}
}
