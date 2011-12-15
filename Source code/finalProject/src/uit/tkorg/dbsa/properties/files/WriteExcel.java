/**
 * 
 */
package uit.tkorg.dbsa.properties.files;

/**
 * @author tiendv
 *
 */
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

import uit.tkorg.dbsa.model.DBSAPublication;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class WriteExcel {

	private WritableCellFormat timesBoldUnderline;
	private WritableCellFormat times;
	private String inputFile;
	
public void setOutputFile(String inputFile) {
	this.inputFile = inputFile;
	}

	public void write( ArrayList<DBSAPublication> listPublication) throws IOException, WriteException {
		File file = new File(inputFile);
		WorkbookSettings wbSettings = new WorkbookSettings();

		wbSettings.setLocale(new Locale("en", "EN"));

		WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
		workbook.createSheet("Report", 0);
		WritableSheet excelSheet = workbook.getSheet(0);
		createLabel(excelSheet);
		createContent(excelSheet,listPublication);

		workbook.write();
		workbook.close();
	}

	private void createLabel(WritableSheet sheet)
			throws WriteException {
		// Lets create a times font
		WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
		// Define the cell format
		times = new WritableCellFormat(times10pt);
		// Lets automatically wrap the cells
		times.setWrap(true);

		// Create create a bold font with unterlines
		WritableFont times10ptBoldUnderline = new WritableFont(
				WritableFont.TIMES, 10, WritableFont.BOLD, false,
				UnderlineStyle.SINGLE);
		timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
		// Lets automatically wrap the cells
		timesBoldUnderline.setWrap(true);

		CellView cv = new CellView();
		cv.setFormat(times);
		cv.setFormat(timesBoldUnderline);
		cv.setAutosize(true);

		// Write a few headers
		addCaption(sheet, 0, 0, "STT");
		addCaption(sheet, 1, 0, "Title");
		addCaption(sheet, 2, 0, "Abstract");
		

	}

	private void createContent(WritableSheet sheet, ArrayList<DBSAPublication> listPublication) throws WriteException,
			RowsExceededException {
		// Write a few number
		for (int i = 0; i < listPublication.size(); i++) {
			// First column
			addNumber(sheet, 0, i,i );
			// Second column
			addLabel(sheet, 1,i,listPublication.get(i).getTitle());
			
			// Third column
			
			addLabel(sheet, 2,i,listPublication.get(i).getAbstractPub());
			
			
		}
		// Lets calculate the sum of it
//		StringBuffer buf = new StringBuffer();
//		buf.append("SUM(A2:A10)");
//		Formula f = new Formula(0, 10, buf.toString());
//		sheet.addCell(f);
//		buf = new StringBuffer();
//		buf.append("SUM(B2:B10)");
//		f = new Formula(1, 10, buf.toString());
//		sheet.addCell(f);

		// Now a bit of text
//		for (int i = 12; i < 20; i++) {
//			// First column
//			addLabel(sheet, 0, i, "Boring text " + i);
//			// Second column
//			addLabel(sheet, 1, i, "Another text");
//		}
	}

	private void addCaption(WritableSheet sheet, int column, int row, String s)
			throws RowsExceededException, WriteException {
		Label label;
		label = new Label(column, row, s, timesBoldUnderline);
		sheet.addCell(label);
	}

	private void addNumber(WritableSheet sheet, int column, int row,
			Integer integer) throws WriteException, RowsExceededException {
		Number number;
		number = new Number(column, row, integer, times);
		sheet.addCell(number);
	}

	private void addLabel(WritableSheet sheet, int column, int row, String s)
			throws WriteException, RowsExceededException {
		Label label;
		label = new Label(column, row, s, times);
		sheet.addCell(label);
	}

//	public static void main(String[] args) throws WriteException, IOException {
//		WriteExcel test = new WriteExcel();
//		test.setOutputFile("c:/lars.xls");
//		test.write();
//		System.out
//				.println("Please check the result file under c:/temp/lars.xls ");
//	}
	
}
