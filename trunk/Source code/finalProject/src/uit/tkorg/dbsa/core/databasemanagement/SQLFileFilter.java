package uit.tkorg.dbsa.core.databasemanagement;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class SQLFileFilter extends FileFilter{
	  //Accept all directories and all .sql files.
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f);
        if (extension != null) {
            if (extension.equals(Utils.sql)){
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    //The description of this filter
    public String getDescription() {
        return "Just File SQL";
    }


}
