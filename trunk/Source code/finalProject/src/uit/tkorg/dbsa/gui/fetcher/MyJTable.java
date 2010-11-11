package uit.tkorg.dbsa.gui.fetcher;
import javax.swing.JTable;    
import javax.swing.table.*;
import java.util.Hashtable;
import java.awt.Color;

public class MyJTable extends JTable
{
	
  Hashtable rowsToPaint = new Hashtable(1);

  /**
   * Default Constructor
   */
  public MyJTable()
  {
    super();
  }
  public MyJTable(DefaultTableModel md)
  {
    super(md);
  }

  /**
   * Set the TableModel and then render each column with a custom cell renderer
   * @param tm TableModel
   */
  public void setModel(TableModel tm)
  {
    super.setModel(tm);
    renderColumns(new JLabelRenderer(this));
  }

  /**
   * Add a new entry indicating:
   * @param row the row to paint - the first row = 0;
   * @param bgColor background color
   */
  public void addRowToPaint(int row, Color bgColor)
  {
    rowsToPaint.put(new Integer(row), bgColor);
    this.repaint();// you need to repaint the table for each you put in the hashtable.
  }

  /**
   * Returns the user selected BG Color or default BG Color.
   * @param row the row to paint
   * @return Color BG Color selected by the user for the row
   */
  public Color getRowToPaint(int row)
  {
    Color bgColor = (Color)rowsToPaint.get(new Integer(row));
    return (bgColor != null)?bgColor:getBackground();
  }

  /**
   * Render all columns with the specified cell renderer
   * @param cellRender TableCellRenderer
   */
  public  void renderColumns(TableCellRenderer cellRender)
  {
    for(int i=0; i<this.getModel().getColumnCount(); i++)
    {
      renderColumn(this.getColumnModel().getColumn(i), cellRender);
    }
  }

  /**
   * Render a TableColumn with the sepecified Cell Renderer
   * @param col TableColumn
   * @param cellRender TableCellRenderer
   */
  public void renderColumn(TableColumn col, TableCellRenderer cellRender)
  {
    try{
          col.setCellRenderer(cellRender);
        }catch(Exception e){System.err.println("Error rendering column: [HeaderValue]: "+col.getHeaderValue().toString()+" [Identifier]: "+col.getIdentifier().toString());}
  }
}
