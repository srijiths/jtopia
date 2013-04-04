package com.sree.textbytes.jtopia;

import org.apache.log4j.Logger;
import com.sree.textbytes.jtopia.cleaner.TextCleaner;
import com.sree.textbytes.jtopia.tagger.DefaultTagger;
import com.sree.textbytes.jtopia.extractor.TermExtractor;
import com.sree.textbytes.jtopia.filter.TermsFilter;
import com.sree.textbytes.StringHelpers.string;

/**
 * 
 * @author sree
 *
 */

public class TermsExtractor {
	public static Logger logger = Logger.getLogger(TermsExtractor.class.getName());
	public TermDocument extractTerms(String text,String lexicon) {
		return performTermExtraction(text,lexicon);
	}
	
	private TermDocument performTermExtraction(String text,String lexicon) {
		if(!string.isNullOrEmpty(text) && !string.isNullOrEmpty(lexicon)) {
			logger.debug("Input String and lexicon is not null / empty");
			TermDocument termDocument = new TermDocument();
			TextCleaner textCleaner = new TextCleaner();
			
			DefaultTagger defaultTagger = new DefaultTagger();
			termDocument.setTagsByTerm(defaultTagger.initializeLexicon(lexicon));
			termDocument.setNormalizedText(textCleaner.normalizeText(text));
			termDocument.setTerms(textCleaner.tokenizeText(termDocument.getNormalizedText()));
			termDocument.setTerms(textCleaner.tokenizeText(text));

			termDocument = defaultTagger.tag(termDocument);
			
			TermExtractor termExtractor = new TermExtractor();
			termDocument.setExtractedTerms(termExtractor.extractTerms(termDocument.getTaggedContainer()));
			
			TermsFilter termsFilter = new TermsFilter(3, 2);
			termDocument.setFinalFilteredTerms(termsFilter.filterTerms(termDocument.getExtractedTerms()));
			
			return termDocument;
		}else {
			logger.debug("Input text / lexicon is null or empty..exiting..");
			return null;
		}
	}
	
}
