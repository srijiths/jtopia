package com.sree.textbytes.jtopia;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class JtopiaUsage {
	public static Logger logger = Logger.getLogger(JtopiaUsage.class.getName());
	public static void main( String[] args )
    {
		//for default lexicon POS tags
		//Configuration.setTaggerType("default"); 
		// for openNLP POS tagger
		//Configuration.setTaggerType("openNLP");
		//for Stanford POS tagger
		Configuration.setTaggerType("stanford"); 
		Configuration.setSingleStrength(3);
		Configuration.setNoLimitStrength(2);
		// if tagger type is "openNLP" then give the openNLP POS tagger path
		//Configuration.setModelFileLocation("model/openNLP/en-pos-maxent.bin"); 
		// if tagger type is "default" then give the default POS lexicon file
		//Configuration.setModelFileLocation("model/default/english-lexicon.txt");
		// if tagger type is "stanford "
		Configuration.setModelFileLocation("model/stanford/english-left3words-distsim.tagger");
		
        TermsExtractor termExtractor = new TermsExtractor();
        TermDocument topiaDoc = new TermDocument();
        
        StringBuffer stringBuffer = new StringBuffer();
        
        FileInputStream fileInputStream = null;
		BufferedReader bufferedReader = null;
		try {
			fileInputStream = new FileInputStream("example.txt");
		} catch (FileNotFoundException e) {
		}

		DataInputStream dataInputStream = new DataInputStream(fileInputStream);
		bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
		String line = "";
		try {
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line+"\n");
			}
		} catch (IOException e) {
			
		}
        
		topiaDoc = termExtractor.extractTerms(stringBuffer.toString());
		logger.info("Extracted terms : "+topiaDoc.getExtractedTerms());
		logger.info("Final Filtered Terms : "+topiaDoc.getFinalFilteredTerms());
    }


}
