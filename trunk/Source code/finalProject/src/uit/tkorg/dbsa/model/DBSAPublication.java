/**
 * 
 */
package uit.tkorg.dbsa.model;

import java.net.URL;

/**
 * @author tiendv
 *
 */
public class DBSAPublication {
	private int id;
	private int sbj_id;
	private String abstractPub;
	private String title;
	private int year;
	private String publisher;
	private String authors;
	private String links;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSbj_id() {
		return sbj_id;
	}
	public void setSbj_id(int sbj_id) {
		this.sbj_id = sbj_id;
	}
	public String getAbstractPub() {
		return abstractPub;
	}
	public void setAbstractPub(String abstractPub) {
		this.abstractPub = abstractPub;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int i) {
		this.year = i;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public String getLinks() {
		return links;
	}
	public void setLinks(String string) {
		links = string;
		
	}

}

