package org.nasdanika.demo.drawio.semanticmapping.tests;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.ExecutionException;
import org.nasdanika.html.model.app.gen.SemanticSiteGenerator;

public class TestDrawioSemanticMappingGenerator {
	
	@Test
	public void generate() throws Exception {
		//new DrawioSemanticMappingGeneratorRefactored().generate();

		URI semanticModelURI = URI.createFileURI(new File("model/high-level-architecture.drawio").getAbsolutePath());
			
		String rootActionResource = "model/root-action.yml";
		URI rootActionURI = URI.createFileURI(new File(rootActionResource).getAbsolutePath());//.appendFragment("/");
		
		String pageTemplateResource = "model/page-template.yml";
		URI pageTemplateURI = URI.createFileURI(new File(pageTemplateResource).getAbsolutePath());//.appendFragment("/");
		
		String siteMapDomain = "https://docs.nasdanika.org/demo-drawio-semantic-mapping";		
		
		SemanticSiteGenerator siteGenerator = new SemanticSiteGenerator();
		
		Map<String, Collection<String>> errors = siteGenerator.generate(
				semanticModelURI,
				rootActionURI,
				pageTemplateURI,
				siteMapDomain,
				new File("docs"),							
				new File("target/model-doc"),
				false);
				
		int errorCount = 0;
		for (Entry<String, Collection<String>> ee: errors.entrySet()) {
			System.err.println(ee.getKey());
			for (String error: ee.getValue()) {
				System.err.println("\t" + error);
				++errorCount;
			}
		}
		
		System.out.println("There are " + errorCount + " site errors");
		
		if (!errors.isEmpty()) {
			throw new ExecutionException("There are problems with pages: " + errorCount);
		};
		
	}

}
