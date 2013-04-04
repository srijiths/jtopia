package com.sree.textbytes.jtopia.cleaner;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.sree.textbytes.StringHelpers.string;

/**
 * 
 * @author : sree
 *
 * Normalizes and tokenizes the input text
 */

public class TextCleaner {
	
	public static Logger logger = Logger.getLogger(TextCleaner.class.getName());
	
	String matchPettern = "([^a-zA-Z]*)([a-zA-Z-\\.]*[a-zA-Z])([^a-zA-Z]*[a-zA-Z]*)";

	/**
	 * Normalizing the new line spaces in input text
	 * @param text
	 * @return
	 */
	public String normalizeText(String text) {
		logger.debug("Input to normalize text : "+text);
		if(!string.isNullOrEmpty(text)) {
			text = replaceAll(text, "\\n", " . ");
		}
		logger.debug("Input text normalized : "+text);
		return text.trim();
	}
	
	/** 
	 * Tokenizing the text using regex
	 * 
	 * @param text
	 * @return
	 */
	public List<String> tokenizeText(String text) {
		List<String> tokenizedWords = new ArrayList<String>();
		String[] words = text.split("\\s");
		Pattern pattern = Pattern.compile(matchPettern,Pattern.DOTALL | Pattern.MULTILINE);
		
		for(String word : words) {
            // If the term is empty, skip it, since we probably just have multiple whitespace characters.
			if(string.isNullOrEmpty(word)) 
				continue;
            // Now, a word can be preceded or succeeded by symbols, so let's split those out
			Matcher matcher = pattern.matcher(word);
			if(!matcher.find()) {
				tokenizedWords.add(word);
				continue;
			}
			for(int i=1;i<=matcher.groupCount();i++) {
				if(!string.isNullOrEmpty(matcher.group(i))) {
					logger.debug("Matcher group : "+i + " text :  "+matcher.group(i));
					tokenizedWords.add(matcher.group(i));
				}
			}
		}
		
		return tokenizedWords;
		
	}
	
	/**
	 * Replace all the occurances of a regex with replacement string
	 * 
	 * @param text
	 * @param regex
	 * @param replacement
	 * @return
	 */
	
	private String replaceAll(String text,String regex,String replacement) {
		text = text.replaceAll(regex, replacement);
		return text;
	}

}
