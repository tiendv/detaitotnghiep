//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.2-b01-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.11.13 at 01:07:30 AM PST 
//


package de.unitrier.dblp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{}author" maxOccurs="unbounded"/>
 *         &lt;element ref="{}editor"/>
 *         &lt;element ref="{}title"/>
 *         &lt;element ref="{}booktitle"/>
 *         &lt;element ref="{}pages"/>
 *         &lt;element ref="{}year"/>
 *         &lt;element ref="{}address"/>
 *         &lt;element ref="{}journal"/>
 *         &lt;element ref="{}volume"/>
 *         &lt;element ref="{}number"/>
 *         &lt;element ref="{}month"/>
 *         &lt;element ref="{}url"/>
 *         &lt;element ref="{}ee"/>
 *         &lt;element ref="{}cdrom"/>
 *         &lt;element ref="{}cite" maxOccurs="unbounded"/>
 *         &lt;element ref="{}publisher"/>
 *         &lt;element ref="{}note"/>
 *         &lt;element ref="{}crossref"/>
 *         &lt;element ref="{}isbn"/>
 *         &lt;element ref="{}series"/>
 *         &lt;element ref="{}school"/>
 *         &lt;element ref="{}chapter"/>
 *       &lt;/choice>
 *       &lt;attribute name="key" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="mdate" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "author",
    "editor",
    "title",
    "booktitle",
    "pages",
    "year",
    "address",
    "journal",
    "volume",
    "number",
    "month",
    "url",
    "ee",
    "cdrom",
    "cite",
    "publisher",
    "note",
    "crossref",
    "isbn",
    "series",
    "school",
    "chapter"
})
@XmlRootElement(name = "incollection")
public class Incollection {

    protected List<Author> author;
    protected Editor editor;
    protected Title title;
    protected Booktitle booktitle;
    protected Pages pages;
    protected Year year;
    protected Address address;
    protected Journal journal;
    protected Volume volume;
    protected Number number;
    protected Month month;
    protected Url url;
    protected Ee ee;
    protected Cdrom cdrom;
    protected List<Cite> cite;
    protected Publisher publisher;
    protected Note note;
    protected Crossref crossref;
    protected Isbn isbn;
    protected Series series;
    protected School school;
    protected Chapter chapter;
    @XmlAttribute(required = true)
    protected String key;
    @XmlAttribute
    protected String mdate;

    /**
     * Gets the value of the author property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the author property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuthor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Author }
     * 
     * 
     */
    public List<Author> getAuthor() {
        if (author == null) {
            author = new ArrayList<Author>();
        }
        return this.author;
    }

    /**
     * Gets the value of the editor property.
     * 
     * @return
     *     possible object is
     *     {@link Editor }
     *     
     */
    public Editor getEditor() {
        return editor;
    }

    /**
     * Sets the value of the editor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Editor }
     *     
     */
    public void setEditor(Editor value) {
        this.editor = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link Title }
     *     
     */
    public Title getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link Title }
     *     
     */
    public void setTitle(Title value) {
        this.title = value;
    }

    /**
     * Gets the value of the booktitle property.
     * 
     * @return
     *     possible object is
     *     {@link Booktitle }
     *     
     */
    public Booktitle getBooktitle() {
        return booktitle;
    }

    /**
     * Sets the value of the booktitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Booktitle }
     *     
     */
    public void setBooktitle(Booktitle value) {
        this.booktitle = value;
    }

    /**
     * Gets the value of the pages property.
     * 
     * @return
     *     possible object is
     *     {@link Pages }
     *     
     */
    public Pages getPages() {
        return pages;
    }

    /**
     * Sets the value of the pages property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pages }
     *     
     */
    public void setPages(Pages value) {
        this.pages = value;
    }

    /**
     * Gets the value of the year property.
     * 
     * @return
     *     possible object is
     *     {@link Year }
     *     
     */
    public Year getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     * 
     * @param value
     *     allowed object is
     *     {@link Year }
     *     
     */
    public void setYear(Year value) {
        this.year = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setAddress(Address value) {
        this.address = value;
    }

    /**
     * Gets the value of the journal property.
     * 
     * @return
     *     possible object is
     *     {@link Journal }
     *     
     */
    public Journal getJournal() {
        return journal;
    }

    /**
     * Sets the value of the journal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Journal }
     *     
     */
    public void setJournal(Journal value) {
        this.journal = value;
    }

    /**
     * Gets the value of the volume property.
     * 
     * @return
     *     possible object is
     *     {@link Volume }
     *     
     */
    public Volume getVolume() {
        return volume;
    }

    /**
     * Sets the value of the volume property.
     * 
     * @param value
     *     allowed object is
     *     {@link Volume }
     *     
     */
    public void setVolume(Volume value) {
        this.volume = value;
    }

    /**
     * Gets the value of the number property.
     * 
     * @return
     *     possible object is
     *     {@link Number }
     *     
     */
    public Number getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     * @param value
     *     allowed object is
     *     {@link Number }
     *     
     */
    public void setNumber(Number value) {
        this.number = value;
    }

    /**
     * Gets the value of the month property.
     * 
     * @return
     *     possible object is
     *     {@link Month }
     *     
     */
    public Month getMonth() {
        return month;
    }

    /**
     * Sets the value of the month property.
     * 
     * @param value
     *     allowed object is
     *     {@link Month }
     *     
     */
    public void setMonth(Month value) {
        this.month = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link Url }
     *     
     */
    public Url getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link Url }
     *     
     */
    public void setUrl(Url value) {
        this.url = value;
    }

    /**
     * Gets the value of the ee property.
     * 
     * @return
     *     possible object is
     *     {@link Ee }
     *     
     */
    public Ee getEe() {
        return ee;
    }

    /**
     * Sets the value of the ee property.
     * 
     * @param value
     *     allowed object is
     *     {@link Ee }
     *     
     */
    public void setEe(Ee value) {
        this.ee = value;
    }

    /**
     * Gets the value of the cdrom property.
     * 
     * @return
     *     possible object is
     *     {@link Cdrom }
     *     
     */
    public Cdrom getCdrom() {
        return cdrom;
    }

    /**
     * Sets the value of the cdrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link Cdrom }
     *     
     */
    public void setCdrom(Cdrom value) {
        this.cdrom = value;
    }

    /**
     * Gets the value of the cite property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cite property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCite().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Cite }
     * 
     * 
     */
    public List<Cite> getCite() {
        if (cite == null) {
            cite = new ArrayList<Cite>();
        }
        return this.cite;
    }

    /**
     * Gets the value of the publisher property.
     * 
     * @return
     *     possible object is
     *     {@link Publisher }
     *     
     */
    public Publisher getPublisher() {
        return publisher;
    }

    /**
     * Sets the value of the publisher property.
     * 
     * @param value
     *     allowed object is
     *     {@link Publisher }
     *     
     */
    public void setPublisher(Publisher value) {
        this.publisher = value;
    }

    /**
     * Gets the value of the note property.
     * 
     * @return
     *     possible object is
     *     {@link Note }
     *     
     */
    public Note getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     * @param value
     *     allowed object is
     *     {@link Note }
     *     
     */
    public void setNote(Note value) {
        this.note = value;
    }

    /**
     * Gets the value of the crossref property.
     * 
     * @return
     *     possible object is
     *     {@link Crossref }
     *     
     */
    public Crossref getCrossref() {
        return crossref;
    }

    /**
     * Sets the value of the crossref property.
     * 
     * @param value
     *     allowed object is
     *     {@link Crossref }
     *     
     */
    public void setCrossref(Crossref value) {
        this.crossref = value;
    }

    /**
     * Gets the value of the isbn property.
     * 
     * @return
     *     possible object is
     *     {@link Isbn }
     *     
     */
    public Isbn getIsbn() {
        return isbn;
    }

    /**
     * Sets the value of the isbn property.
     * 
     * @param value
     *     allowed object is
     *     {@link Isbn }
     *     
     */
    public void setIsbn(Isbn value) {
        this.isbn = value;
    }

    /**
     * Gets the value of the series property.
     * 
     * @return
     *     possible object is
     *     {@link Series }
     *     
     */
    public Series getSeries() {
        return series;
    }

    /**
     * Sets the value of the series property.
     * 
     * @param value
     *     allowed object is
     *     {@link Series }
     *     
     */
    public void setSeries(Series value) {
        this.series = value;
    }

    /**
     * Gets the value of the school property.
     * 
     * @return
     *     possible object is
     *     {@link School }
     *     
     */
    public School getSchool() {
        return school;
    }

    /**
     * Sets the value of the school property.
     * 
     * @param value
     *     allowed object is
     *     {@link School }
     *     
     */
    public void setSchool(School value) {
        this.school = value;
    }

    /**
     * Gets the value of the chapter property.
     * 
     * @return
     *     possible object is
     *     {@link Chapter }
     *     
     */
    public Chapter getChapter() {
        return chapter;
    }

    /**
     * Sets the value of the chapter property.
     * 
     * @param value
     *     allowed object is
     *     {@link Chapter }
     *     
     */
    public void setChapter(Chapter value) {
        this.chapter = value;
    }

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

    /**
     * Gets the value of the mdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMdate() {
        return mdate;
    }

    /**
     * Sets the value of the mdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMdate(String value) {
        this.mdate = value;
    }

}
