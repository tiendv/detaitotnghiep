package uit.tkorg.dbsa.cores.fetchers;

import java.util.ArrayList;
import java.util.List;

import net.sf.jabref.BibtexEntry;
import net.sf.jabref.BibtexEntryType;
import net.sf.jabref.Util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * parse atom XML file to Bibtex Entry
 * @author tiger
 * @modify tiendv
 *
 */
public class CiteSeerXAtomEntryHandler extends DefaultHandler {
	
	List<BibtexEntry> entries =null;
	
	BibtexEntry entry = null;

    String nextField = null;
    String nextValue = null;
    
    boolean nextAssign = false;


    
    public CiteSeerXAtomEntryHandler(List<BibtexEntry> entries){
    	if(entries == null){
    		this.entries = new ArrayList<BibtexEntry>();
    	}else {
    		this.entries = entries;
    	}
    }

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
        if (nextAssign == true) {
        	String target = new String(ch, start, length);
            if(nextField.equals("year")){
            	entry.setField(nextField, String.valueOf(target.substring(0, 4)));
            	nextAssign = false;
            } else if (nextField.equals("citeseerurl")) {
                entry.setField(nextField, target);
                nextAssign = false;
            } else {
            	if(!(target.equals("em")||
            			target.equals("<")||
            			target.equals(">")||
            			target.equals("/em"))){
            		nextValue = nextValue + target;
            	}   
            }            
        }
	}


	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		if (qName.equals("title")) {
            nextField = "title";
            nextValue = "";
            nextAssign = entry!=null;
        } else if (qName.equals("updated")) {
            nextField = "year";
            nextValue = "";
            nextAssign = entry!=null;
        } else if (qName.equals("id")) {
            //nextField = "citeseerurl";
            //nextAssign = entry!=null;
        } else if (qName.equals("name")){
        	nextField = "author";
        	nextValue = "";
        	nextAssign = entry!=null;
        } else if (qName.equals("summary")){
        	nextField = "abstract";
        	nextValue = "";
        	nextAssign = entry!=null;
        } else if (qName.equals("entry")){
        	entry = new BibtexEntry(Util.createNeutralId(), BibtexEntryType.getType("article"));
        	entries.add(entry);
        } else if (qName.equals("link")){
        	if(entry!=null){
        		entry.setField("citeseerurl",attributes.getValue("href"));
        	}
        } else if (qName.equals("em")){
        	nextField = "em";
        	nextAssign = true;
        }
		
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (nextAssign) {
			if (qName.equals("title")) {
				entry.setField("title", nextValue);				
			} else if (qName.equals("name")) {
				entry.setField("author", nextValue);				
			} else if (qName.equals("summary")) {
				entry.setField("abstract", nextValue);
			}
			
			nextAssign = false;
		}
		

	}

	public List<BibtexEntry> getAllEntries(){
		return entries;
	}

}
