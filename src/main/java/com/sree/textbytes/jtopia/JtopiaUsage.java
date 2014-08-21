package com.sree.textbytes.jtopia;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class JtopiaUsage {
	public static void main( String[] args )
    {
		//for default lexicon POS tags
		//Configuration.setTaggerType("default"); 
		// for openNLP POS tagger
		Configuration.setTaggerType("openNLP"); 
		Configuration.setSingleStrength(3);
		Configuration.setNoLimitStrength(2);
		// if tagger type is "openNLP" then give the openNLP POS tagger path
		Configuration.setModelFileLocation("model/openNLP/en-pos-maxent.bin"); 
		// if tagger type is "default" then give the default POS lexicon file
		//Configuration.setModelFileLocation("model/default/english-lexicon.txt");
		
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
		System.out.println("Extracted terms : "+topiaDoc.getExtractedTerms());
		System.out.println(topiaDoc.getFinalFilteredTerms());
    }


}
