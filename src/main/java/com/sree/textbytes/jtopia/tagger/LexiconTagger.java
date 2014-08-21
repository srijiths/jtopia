package com.sree.textbytes.jtopia.tagger;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import com.sree.textbytes.jtopia.TermDocument;
import com.sree.textbytes.jtopia.container.TaggedTermsContainer;

/**
 * 
 * Default POS tagger which uses model/default/english-lexicon.txt
 * 
 * @author sree
 *
 */

public class LexiconTagger extends DefaultTagger implements Tagger {
	/**
	 * Initializes the default POS tagger lexicon
	 * 
	 * @param lexiconFileName
	 * @return 
	 */
	public LinkedHashMap<String, String> initialize(String lexiconFileName) {
		LinkedHashMap<String, String> tagsByTerm = new LinkedHashMap<String, String>();
		logger.debug("Lexicon initialization started");
		FileInputStream fileInputStream = null;
		BufferedReader bufferedReader = null;
		try {
			fileInputStream = new FileInputStream(lexiconFileName);
		} catch (FileNotFoundException e) {
			logger.error(e.toString(), e);
		}

		DataInputStream dataInputStream = new DataInputStream(fileInputStream);
		bufferedReader = new BufferedReader(new InputStreamReader(
				dataInputStream));
		String line = "";
		try {
			while ((line = bufferedReader.readLine()) != null) {
				String[] terms = line.split(" ");
				tagsByTerm.put(terms[0], terms[1]);
			}
		} catch (IOException e) {
			logger.error(e.toString(), e);
		}

		logger.debug("Lexicon initialization completed with size "+ tagsByTerm.size());
		return tagsByTerm;

	}
	
	/**
	 * Tag the terms and post process the tagged terms. 
	 * Phase 1: Assign the tag from the lexicon. If the term is not found, it is assumed to be a default noun (NND).
	 * Phase 2: Run through some rules to improve the term tagging and normalized form.
	 * @param termDocument
	 * @return {@link TermDocument}
	 */

	public TermDocument tag(TermDocument termDocument) {
		TaggedTermsContainer taggedTermsContainer = new TaggedTermsContainer();
		for(String term : termDocument.getTerms()) {
			if(termDocument.getTagsByTerm().containsKey(term)) {
				String tag = termDocument.getTagsByTerm().get(term);
				taggedTermsContainer.addTaggedTerms(term, tag,term);
			} else {
				String tag = "NND";
				taggedTermsContainer.addTaggedTerms(term, tag,term);
			}
		}

		termDocument.setTaggedContainer(taggedTermsContainer);
		termDocument = super.postTagProcess(termDocument);
		return termDocument;
	}

}
