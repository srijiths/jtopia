package com.sree.textbytes.jtopia.helpers;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DateUtils {
	public static final Set<String> DateOffsets;

	static {
		String elements[] = { "PM", "ET", "UST", "AM", "IST", "PDT", "AD" };
		DateOffsets = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(elements)));
	}
	
	public Set<String> getDateOffsets() {
		return this.DateOffsets;
	}

}
