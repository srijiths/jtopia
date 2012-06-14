package com.sree.textbytes.jtopia.helpers;

/**
 * Created by IntelliJ IDEA.
 *
 * @user 	: sree
 * 
 */

public class string {
	private string(){}
	public static final String empty = "";
	public static final String[] emptyArray = new String[] {empty};
	public static boolean isNullOrEmpty(String input) {
		if (input == null) return true;
		if (input.length() == 0) return true;
		return false;
	}

}

