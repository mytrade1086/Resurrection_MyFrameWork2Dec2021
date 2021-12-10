package Util;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

//package Util;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
//
//public class ExcelUtil {
//
//	private static Workbook book;
//	private static Sheet sheet;
//
//	private static String TEST_DATA_SHEET_PATH = "./src/main/java/com/hubspot/qa/testdata/HubSpot_TestData.xlsx";
//
//	public static Object[][] getTestData(String sheetName) {
//
//		Object data[][] = null;
//		try {
//			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
//			book = WorkbookFactory.create(ip);
//			sheet = book.getSheet(sheetName);
//
//			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
//
//			for (int i = 0; i < sheet.getLastRowNum(); i++) {
//
//				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
//
//					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
//
//				}
//
//			}
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (InvalidFormatException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return data;
//
//	}
//
//}


public class ExcelUtil {
 public static void main(String[] args) 
    {
        try
        {
            FileInputStream file = new FileInputStream(new File("./src/test/java/Data/POIWORK.xlsx"));
 
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
 
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
 
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) 
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                 
                while (cellIterator.hasNext()) 
                {
                    Cell cell = (Cell)cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType()) 
                    {
                    case STRING:
                        System.out.println(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                    	System.out.println(cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                    	System.out.println(cell.getBooleanCellValue());
                        break;
					default:
						System.out.println("APACHE data format missing in switch case");
						break;
                    }
                }
                System.out.println("");
            }
            file.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
 
 
 
 
 //
 
 public void test() {
	 
	 
	 
	

}
}
