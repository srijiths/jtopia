package com.sree.textbytes.jtopia;

import org.apache.log4j.Logger;
import com.sree.textbytes.jtopia.cleaner.TextCleaner;
import com.sree.textbytes.jtopia.tagger.LexiconTagger;
import com.sree.textbytes.jtopia.tagger.OpenNLPTagger;
import com.sree.textbytes.jtopia.tagger.StanfordTagger;
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
	public TermDocument extractTerms(String text) {
		return performTermExtraction(text);
	}
	
	private TermDocument performTermExtraction(String text) {
		if(!string.isNullOrEmpty(text) && !string.isNullOrEmpty(Configuration.getModelFileLocation())) {
			logger.debug("Input String and lexicon is not null / empty");
			TermDocument termDocument = new TermDocument();
			TextCleaner textCleaner = new TextCleaner();
			
			termDocument.setNormalizedText(textCleaner.normalizeText(text));
			termDocument.setTerms(textCleaner.tokenizeText(termDocument.getNormalizedText()));
			//termDocument.setTerms(textCleaner.tokenizeText(text));

			if(Configuration.taggerType.equalsIgnoreCase("default")) {
				logger.info("Using English Lexicon POS tagger..");
				LexiconTagger lexiconTagger = new LexiconTagger();
				termDocument.setTagsByTerm(lexiconTagger.initialize(Configuration.getModelFileLocation()));
				termDocument = lexiconTagger.tag(termDocument);
			}else if(Configuration.taggerType.equalsIgnoreCase("openNLP")) {
				logger.info("Using openNLP POS tagger..");
				OpenNLPTagger openNLPTagger = new OpenNLPTagger();
				termDocument = openNLPTagger.tag(termDocument);
			}else if(Configuration.taggerType.equalsIgnoreCase("stanford")) {
				logger.info("Using Stanford POS tagger..");
				StanfordTagger stanfordTagger = new StanfordTagger();
				termDocument = stanfordTagger.tag(termDocument);
			}
			
			TermExtractor termExtractor = new TermExtractor();
			termDocument.setExtractedTerms(termExtractor.extractTerms(termDocument.getTaggedContainer()));
			
			TermsFilter termsFilter = new TermsFilter(Configuration.getSingleStrength(),Configuration.getNoLimitStrength());
			termDocument.setFinalFilteredTerms(termsFilter.filterTerms(termDocument.getExtractedTerms()));
			
			return termDocument;
		}else {
			logger.debug("Input text / lexicon is null or empty..exiting..");
			return null;
		}
	}
	
}
