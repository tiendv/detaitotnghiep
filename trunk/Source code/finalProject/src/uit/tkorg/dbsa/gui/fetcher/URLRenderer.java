package uit.tkorg.dbsa.gui.fetcher;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class URLRenderer extends DefaultTableCellRenderer implements
		MouseListener, MouseMotionListener {

	private int row = -1;
	private int col = -1;

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, false,
				row, column);
		
		setText("<html><body><a href='" + "'>"+  value.toString() + "</><hr></body></html>" );
		
		return this;
	}

	public void mouseMoved(MouseEvent e) {
		JTable table = (JTable) e.getSource();
		Point pt = e.getPoint();
		row = table.rowAtPoint(pt);
		col = table.columnAtPoint(pt);
		if (row < 0 || col < 0) {
			row = -1;
			col = -1;
		}
		table.repaint();
	}

	public void mouseExited(MouseEvent e) {
		JTable table = (JTable) e.getSource();
		row = -1;
		col = -1;
		table.repaint();
	}

	public void mouseClicked(MouseEvent e) {
		JOptionPane.showMessageDialog(null, "12345");
		JTable table = (JTable) e.getSource();
		Point pt = e.getPoint();
		int crow = table.rowAtPoint(pt);
		int ccol = table.columnAtPoint(pt);
		// if(table.convertColumnIndexToModel(ccol) == 2)
		if (table.getColumnClass(ccol).equals(URL.class)) {
			System.out.println(table.getValueAt(crow, ccol));
			// try{
			// Desktop.getDesktop().browse(url.toURI());
			// }catch(Exception ex) {
			// ex.printStackTrace();
			// }
		}
	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

}
