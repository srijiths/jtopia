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
		bufferedReader = new BufferedReader(new InputStreamReader(
				dataInputStream));
		String line = "";
		try {
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				
			}
		} catch (IOException e) {
			
		}
        
		topiaDoc = termExtractor.extractTerms(stringBuffer.toString(),"model/english-lexicon.txt");
		System.out.println("Extracted terms : "+topiaDoc.getExtractedTerms());
		System.out.println(topiaDoc.getFinalFilteredTerms());
    }


}
