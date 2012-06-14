package com.sree.textbytes.jtopia.container;

/**
 * Tagged terms class
 * 
 * @author sree
 *
 */

public class TaggedTerms {
	public String term;
	public String tag;
	public String norm;
	
	public TaggedTerms(String term,String tag,String norm) {
		this.term = term;
		this.tag = tag;
		this.norm = norm;
	}
	
	/**
	 * get term
	 * @return
	 */
	public String getTerm() {
		return this.term;
	}
	
	/**
	 * set term
	 * @param term
	 */
	public void setTerm(String term) {
		this.term = term;
	}
	
	/**
	 * get tag
	 * @return
	 */
	public String getTag() {
		return this.tag;
	}
	
	/**
	 * set tag
	 * @param tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	/**
	 * set norm
	 * @param norm
	 */
	
	public void setNorm(String norm) {
		this.norm = norm;
	}
	
	/**
	 * get norm
	 * @return
	 */
	public String getNorm() {
		return this.norm;
	}
}
