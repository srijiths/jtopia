package com.sree.textbytes.jtopia.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sree.textbytes.jtopia.helpers.string;

/**
 * Final terms filter according to term occurance and strength
 * 
 * @author sree
 * 
 */
public class TermsFilter {
	int singleStrengthMinOccur;
	int noLimitStrength;
	/**
	 * Default configuration singleStrenght =3 and noLimit = 2
	 * @param singleStrenght
	 * @param noLimit
	 */
	public TermsFilter(int singleStrenght,int noLimit) {
		singleStrengthMinOccur = singleStrenght;
		noLimitStrength = noLimit;
	}
	
	/**
	 * Filter the extracted terms
	 * @param extractedTerms
	 * @return
	 */
	public Map<String, ArrayList<Integer>> filterTerms(Map<String,Integer> extractedTerms) {
		Map<String, ArrayList<Integer>> filteredTerms = new HashMap<String, ArrayList<Integer>>();
		Set keySet = extractedTerms.keySet();
		for(Object key : keySet) {
			String term = (String)key;
			Integer count = extractedTerms.get(key);
			String[] wordCount = term.split(" ");
			int strength = wordCount.length;
			if(!string.isNullOrEmpty(term)) {
				if((strength == 1 && count >= singleStrengthMinOccur) || (strength >= noLimitStrength)) {
					ArrayList<Integer> values = new ArrayList<Integer>();
					values.add(count);
					values.add(strength);
					filteredTerms.put(term, values);
				}
			}
		}
		
		return filteredTerms;
	}
}
