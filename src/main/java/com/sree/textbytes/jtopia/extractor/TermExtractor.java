package com.sree.textbytes.jtopia.extractor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sree.textbytes.jtopia.TermDocument;
import com.sree.textbytes.jtopia.container.TaggedTerms;
import com.sree.textbytes.jtopia.container.TaggedTermsContainer;
import com.sree.textbytes.StringHelpers.string;

/**
 * Extractes the terms from the tagged terms
 * 
 * @author sree
 * 
 *
 */
public class TermExtractor {
	
	int search = 0;
	int noun = 1;
	
	public static Logger logger = Logger.getLogger(TermExtractor.class.getName());
	Map<String,Integer> terms = new HashMap<String, Integer>();
	List<String> multiTerm = new ArrayList<String>();
	
	public Map<String,Integer> extractTerms(TaggedTermsContainer taggedContainer) {
		int state = search;
		for(int i=0;i<taggedContainer.taggedTerms.size();i++) {
			TaggedTerms taggedTerms = taggedContainer.taggedTerms.get(i);
			String term = taggedTerms.getTerm();
			String tag = taggedTerms.getTag();
			String norm = taggedTerms.getNorm();
			if(state == search && tag.startsWith("N")) {
				state = noun;
				addTerms(term);
			}else if((state == search) && (tag.equals("JJ")) && (Character.isUpperCase(term.charAt(0)))) {
				state = noun;
				addTerms(term);
			}else if(state == noun && tag.startsWith("N")) {
				addTerms(term);
			}/*else if(state == noun && (Character.isUpperCase(term.charAt(0)))) {
				addTerms(term);
			}*/else if(state == noun && !tag.startsWith("N")) {
				state = search;
				if(multiTerm.size() > 1)
				{
					String multiWord = null;
					for(String multi : multiTerm)
					{
						if(multiWord == null) {
							multiWord = multi;
						}else{
							multiWord = multiWord +" "+multi;
							//multiWord = multiWord.trim();
						}
					}
					if(terms.containsKey(multiWord)) {
						Integer count = terms.get(multiWord);
						count += 1;
						terms.put(multiWord, count);
					}else {
						terms.put(multiWord, 1);
					}
				}
				multiTerm.clear();
			}
		}
		
		return terms;
	}
	
	private void addTerms(String term) {
		multiTerm.add(term);
		if(terms.containsKey(term)) {
			Integer count = terms.get(term);
			count = count + 1;
			terms.put(term, count);
		}else {
			terms.put(term, 1);
		}
	}

}
