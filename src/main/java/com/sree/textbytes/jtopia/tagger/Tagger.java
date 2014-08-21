package com.sree.textbytes.jtopia.tagger;


import com.sree.textbytes.jtopia.TermDocument;

public interface Tagger {
	/**
	 * Tag the terms and post process the tagged terms. 
	 * Phase 1: Assign the tag from the lexicon. If the term is not found, it is assumed to be a default noun (NND).
	 * Phase 2: Run through some rules to improve the term tagging and normalized form.
	 * 
	 * @param termDocument
	 * @return
	 */
	public TermDocument tag(TermDocument termDocument);
}
