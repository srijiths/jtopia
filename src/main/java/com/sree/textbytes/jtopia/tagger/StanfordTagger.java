package com.sree.textbytes.jtopia.tagger;

import com.sree.textbytes.jtopia.Configuration;
import com.sree.textbytes.jtopia.TermDocument;
import com.sree.textbytes.jtopia.container.TaggedTermsContainer;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class StanfordTagger extends DefaultTagger implements Tagger {

	MaxentTagger maxentTagger = null;
	
	public void initialize() {
		maxentTagger = new MaxentTagger(Configuration.getModelFileLocation());
	}
	
	public TermDocument tag(TermDocument termDocument) {
		initialize();
		TaggedTermsContainer taggedTermsContainer = new TaggedTermsContainer();
		for(String term : termDocument.getTerms()) {
			String tag = maxentTagger.tagString(term);
			// Since Stanford POS has tagged terms like establish_VB , we only need the POS tag
			// Some POS tags in Stanford has special charaters at end like their/PRP$
			try {
				taggedTermsContainer.addTaggedTerms(term, tag.split("_")[1].replaceAll("\\$", ""), term);
			}catch(Exception e) {
			}
		}
		
		termDocument.setTaggedContainer(taggedTermsContainer);
		termDocument = postTagProcess(termDocument);


		return termDocument;
	}

}
