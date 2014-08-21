package com.sree.textbytes.jtopia;

/**
 * 
 * @author Sree
 * 
 * Configurations for different POS taggers, filter values
 * 
 *
 */
public class Configuration {
	
	static String taggerType = "";
	static int singleStrengthMinOccur;
	static int noLimitStrength;
	static String modelFileLocation = "";
	
	public static void setTaggerType(String type) {
		taggerType = type;
	}
	
	public static String getTaggerType() {
		return taggerType;
	}
	
	public static void setSingleStrength(int strength) {
		singleStrengthMinOccur = strength;
	}
	
	public static int getSingleStrength() {
		return singleStrengthMinOccur;
	}
	
	public static void setNoLimitStrength(int noLimit) {
		noLimitStrength = noLimit;
	}
	
	public static int getNoLimitStrength() {
		return noLimitStrength;
	}
	
	public static void setModelFileLocation(String modelFile) {
		modelFileLocation = modelFile;
	}
	
	public static String getModelFileLocation() {
		return modelFileLocation;
	}

	
}
