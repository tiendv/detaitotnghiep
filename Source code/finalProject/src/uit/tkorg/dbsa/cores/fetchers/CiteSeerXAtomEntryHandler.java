package uit.tkorg.dbsa.cores.fetchers;

import java.util.ArrayList;
import java.util.List;

import net.sf.jabref.BibtexEntry;
import net.sf.jabref.BibtexEntryType;
import net.sf.jabref.Util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import uit.tkorg.dbsa.properties.files.DBSAApplicationConst;


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
            if(nextField.equals(DBSAApplicationConst.YEAR)){
            	entry.setField(nextField, String.valueOf(target));
            	nextAssign = false;
            } else if (nextField.equals("citeseerurl")) {
                entry.setField(nextField, target);
                nextAssign = false;
            } else {
            	if(!(target.equals(DBSAApplicationConst.EM)||
            			target.equals("<")||
            			target.equals(">")||
            			target.equals(DBSAApplicationConst.EM))){
            		nextValue = nextValue + target;
            	}   
            }            
        }
	}


	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		if (qName.equals(DBSAApplicationConst.TITLE)) {
            nextField = DBSAApplicationConst.TITLE;
            nextValue = "";
            nextAssign = entry!=null;
        } else if (qName.equals(DBSAApplicationConst.UPDATED)) {
            nextField = DBSAApplicationConst.YEAR;
            nextValue = "";
            nextAssign = entry!=null;
        } else if (qName.equals(DBSAApplicationConst.ID)) {
            //nextField = "citeseerurl";
            //nextAssign = entry!=null;
        } else if (qName.equals(DBSAApplicationConst.NAME)){
        	nextField = DBSAApplicationConst.AUTHOR;
        	nextValue = "";
        	nextAssign = entry!=null;
        } else if (qName.equals(DBSAApplicationConst.SUMMARY)){
        	nextField = DBSAApplicationConst.ABSTRACT;
        	nextValue = "";
        	nextAssign = entry!=null;
        } else if (qName.equals(DBSAApplicationConst.ENTRY)){
        	entry = new BibtexEntry(Util.createNeutralId(), BibtexEntryType.getType(DBSAApplicationConst.ARTICLE));
        	entries.add(entry);
        } else if (qName.equals(DBSAApplicationConst.LINK)){
        	if(entry!=null){
        		entry.setField(DBSAApplicationConst.CITESEERURL,attributes.getValue(DBSAApplicationConst.HREF));
        	}
        } else if (qName.equals(DBSAApplicationConst.EM)){
        	nextField = DBSAApplicationConst.EM;
        	nextAssign = true;
        }
		
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (nextAssign) {
			if (qName.equals(DBSAApplicationConst.TITLE)) {
				entry.setField(DBSAApplicationConst.TITLE, nextValue);				
			} else if (qName.equals(DBSAApplicationConst.NAME)) {
				entry.setField(DBSAApplicationConst.AUTHOR, nextValue);				
			} else if (qName.equals(DBSAApplicationConst.SUMMARY)) {
				entry.setField(DBSAApplicationConst.ABSTRACT, nextValue);
			}
			
			nextAssign = false;
		}
		

	}

	public List<BibtexEntry> getAllEntries(){
		return entries;
	}

}
