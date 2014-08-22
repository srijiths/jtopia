package com.sree.textbytes.jtopia.tagger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import com.sree.textbytes.jtopia.Configuration;
import com.sree.textbytes.jtopia.TermDocument;
import com.sree.textbytes.jtopia.container.TaggedTermsContainer;

/**
 * 
 * @author sree
 * 
 * openNLP POS Tagger
 *
 */

public class OpenNLPTagger extends DefaultTagger implements Tagger {
	POSTaggerME tagger = null;
	InputStream modelStream = null;

	/**
	 * Initialize the openNLP POS tagger
	 * 
	 * @param lexiconFileName
	 * @return
	 */
	public void initialize(String lexiconFileName) {
		try {
			modelStream = new FileInputStream(lexiconFileName);
			POSModel model = new POSModel(modelStream);
			tagger = new POSTaggerME(model);
		} catch (IOException e) {
			logger.error(e.toString(),e);
		} 
	}

	/**
	 * Tag the terms using openNLP POS Tagger
	 */
	public TermDocument tag(TermDocument termDocument) {
		initialize(Configuration.getModelFileLocation());
		TaggedTermsContainer taggedTermsContainer = new TaggedTermsContainer();
		for(String term : termDocument.getTerms()) {
			String tag = tagger.tag(term);
			// Since open NLP has tagged terms like establish/VB , we only need the POS tag
			// Some POS tags in openNLP has special charaters at end like their/PRP$
			try {
				taggedTermsContainer.addTaggedTerms(term, tag.split("/")[1].replaceAll("\\$", ""), term);
			}catch(Exception e) {
			}
			
		}
		
		termDocument.setTaggedContainer(taggedTermsContainer);
		termDocument = postTagProcess(termDocument);
		if(modelStream != null) {
			try {
				modelStream.close();
			} catch (IOException e) {
				logger.error(e.toString(),e);
			}
		}
		return termDocument;
	}

}
