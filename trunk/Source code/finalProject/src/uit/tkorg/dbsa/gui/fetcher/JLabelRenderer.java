package uit.tkorg.dbsa.gui.fetcher;
import javax.swing.*;                          
import java.awt.Component;
import javax.swing.table.*;
/**
 * 
 * @author tiendv
 *
 */

public class JLabelRenderer extends JLabel implements TableCellRenderer
{
  private MyJTable myTable;
  /**
   * Creates a Custom JLabel Cell Renderer
   * @param t your JTable implmentation that holds the Hashtable to inquire for
   * rows and colors to paint.
   */
  public JLabelRenderer(MyJTable t)
  {
    this.myTable = t;
  }

  /**
   * Returns the component used for drawing the cell.  This method is
   * used to configure the renderer appropriately before drawing.
   * see TableCellRenderer.getTableCellRendererComponent(...); for more comments on the method
   */
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
  {
    setOpaque(true); //JLabel isn't opaque by default
    
    setText(value.toString());
    setFont(myTable.getFont());
    
    if(!isSelected)//if the row is not selected then use the custom color
    	setBackground(myTable.getRowToPaint(row));
    else //if the row is selected use the default selection color
    	setBackground(myTable.getSelectionBackground());

    //Foreground could be changed using another Hashtable...
    setForeground(myTable.getForeground());

    // Since the renderer is a component, return itself
    return this;
  }

  // The following methods override the defaults for performance reasons
  public void validate() {}
  public void revalidate() {}
  protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {}
  public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {}
}