package taxPerson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataReader 
{
	private static XSSFSheet ExcelWorkSheet;
	private static XSSFWorkbook ExcelWorkBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;

	public static final String Path_TestData = "Resource/";

	/**
	 * This method is to set the File path and to open the Excel file, Pass
	 * Excel Path and Sheetname as Arguments to this method
	 **/
	public static void setExcelFile(String Path, String SheetName)
			throws Exception {
		try {        // Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);
			// Access the required test data sheet
			ExcelWorkBook = new XSSFWorkbook(ExcelFile);

			ExcelWorkSheet = ExcelWorkBook.getSheet(SheetName); // tab name
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * This method is to read the test data from the Excel cell, in this we are
	 * passing parameters as Row number and Column number
	 **/
	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			Cell = ExcelWorkSheet.getRow(RowNum).getCell(ColNum);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * This method is to write in the Excel cell, Row num and Col num are the
	 * parameters
	 **/
	public static void setCellData(String Result, int RowNum, int ColNum)
			throws Exception {
		try {
			Row = ExcelWorkSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} else {
				Cell.setCellValue(Result);
			}
			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(
					Constant.Path_TestData + Constant.File_TestData);
			ExcelWorkBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * This method is to read the test data from the Excel cell. Here no need to
	 * mention Row / Column number. Data is read row by row.
	 **/
	public List<ArrayList<String>> getData() {
		String Path = Constant.Path_TestData + Constant.File_TestData;
		String ExcelSheetTabName = "Sheet1";
		List<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();

		try {
			FileInputStream ExcelFile = new FileInputStream(Path);
			ExcelWorkBook = new XSSFWorkbook(ExcelFile);
			ExcelWorkSheet = ExcelWorkBook.getSheet(ExcelSheetTabName);
			java.util.Iterator rows = ExcelWorkSheet.rowIterator();

			while (rows.hasNext()) {
				XSSFRow row = ((XSSFRow) rows.next());
				// int r=row.getRowNum();
				java.util.Iterator cells = row.cellIterator();
				// int i = 0;
				ArrayList<String> testData = new ArrayList<String>();
				while (cells.hasNext()) {
					XSSFCell cell = (XSSFCell) cells.next();
					cell.setCellType(1);
					String value = cell.getStringCellValue();
					if (!value.equals(null)) {
						testData.add(value);
						// i++;
					}
				}
				dataList.add(testData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}
/**
 * Overloaded method with Spreadsheet and tab name
 **/
	public List<ArrayList<String>> getData(String SpreadSheetName, String SpreadsheetTabName) {
		String spreadSheetPath = Path_TestData + SpreadSheetName ;
		String ExcelSheetTabName = SpreadsheetTabName;
		List<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();

		try {
			FileInputStream ExcelFile = new FileInputStream(spreadSheetPath);
			ExcelWorkBook = new XSSFWorkbook(ExcelFile);
			ExcelWorkSheet = ExcelWorkBook.getSheet(ExcelSheetTabName);
			java.util.Iterator rows = ExcelWorkSheet.rowIterator();

			while (rows.hasNext()) {
				XSSFRow row = ((XSSFRow) rows.next());
				// int r=row.getRowNum();
				java.util.Iterator cells = row.cellIterator();
				// int i = 0;
				ArrayList<String> testData = new ArrayList<String>();
				while (cells.hasNext()) {
					XSSFCell cell = (XSSFCell) cells.next();
					cell.setCellType(1);
					String value = cell.getStringCellValue();
					if (!value.equals(null)) {
						testData.add(value);
						// i++;
					}
				}
				dataList.add(testData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}
} 