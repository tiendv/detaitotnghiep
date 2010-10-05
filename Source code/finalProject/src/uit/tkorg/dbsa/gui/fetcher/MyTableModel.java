package uit.tkorg.dbsa.gui.fetcher;

import javax.swing.table.AbstractTableModel;

class MyTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String [] columnNames = new String[] { "No.", "Title", "Authors", "Year", "Abstract", "Publisher", "Mark"};
	Object [][] obj = {
					{new Integer(1),"Ten bai bao",  "Tac gia", "nam cong bo",  "Tom tat", "To chuc", new Boolean(true)}, 
					{new Integer(2),"een bai bao",  "rac gia", "eam cong bo",  "gom tat", "To chuc", new Boolean(false)}};

        public final Object[] longValues = {"Jane", "Kathy",
                                            "None of the above",
                                            new Integer(20), Boolean.TRUE};

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return obj.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return obj[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        @SuppressWarnings("unchecked")
		public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
         
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
       

            obj[row][col] = value;
        

           
        }
	}