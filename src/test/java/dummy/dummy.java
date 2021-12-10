package dummy;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class dummy {

	@Test
	public void Das() throws IOException {

		String workBookpath="./src/test/java/Data/POIWORK.xlsx";
		String[] temp=null;
		try {
			temp = ReadColumnData(0, workBookpath, "Sheet1", "skill");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		System.out.println(Arrays.toString(temp));
	}

	public String[] ReadColumnData(int iStartpos, String workbookPath, String sheetName, String colName) throws Exception {
		int colIndex = getColumnIndexFromName(workbookPath, sheetName, colName);
		Workbook wb = null;
		String[] aData = null;
		try {
			wb = WorkbookFactory.create(new File(workbookPath));
		} catch (Exception ex) {
			throw new Exception("Problem while reading ");
		}
		Sheet sh1 = wb.getSheet(sheetName);
		if (String.valueOf(sh1) == null) {
			throw new Exception("Problem while reading sheet=>" + sheetName);
		}
		int ifirstRowNum = sh1.getFirstRowNum();// 0 based index. -1 if no row
		int ifirstLastRowNum = sh1.getLastRowNum();//0 based. else -1 .*** Be careful. Might give null pointer if data was present before


		// check if Sheet is having row and Row Number we are passing is less tha
		if (ifirstRowNum > -1 && ifirstRowNum <= iStartpos) {
			aData = new String[ifirstLastRowNum + 1];
			for (int i = iStartpos; i <= ifirstLastRowNum; i++) {
				Cell cc = sh1.getRow(i).getCell(colIndex);
				String data = "";
				if (cc.getCellType() == CellType.STRING) {
					//System.out.println(cc.getStringCellValue());
					data = cc.getStringCellValue().toString();
				} else if (cc.getCellType() == CellType.NUMERIC) {
					//System.out.println(cc.getNumericCellValue());
					data = cc.getStringCellValue().toString();
				} else if (cc.getCellType() == CellType.BLANK) {
					//data = cc.getStringCellValue().toString();
					System.out.println("xx");
				} else if (cc.getCellType() == CellType.BOOLEAN) {
					//System.out.println(cc.getBooleanCellValue());
					data = cc.getStringCellValue().toString();
				}
				aData[i] = data;
			}
		}

		else {
			throw new Exception(sheetName + " does not have any rows");
		}
		wb.close();
		return aData;
	}

	public int getColumnIndexFromName(String workBook, String sheetName, String colName) throws Exception {
		boolean found = false;
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(new File(workBook));	
		} catch (Exception ex) {
			throw new Exception("Problem while reading ");
		}

		Sheet sh1 = wb.getSheet(sheetName);
		if (String.valueOf(sh1) == null) {
			throw new Exception("Problem while reading sheet=>" + sheetName);
		}
		int ifirstRowNum = sh1.getFirstRowNum();// 0 based index. -1 if no row
		Iterator<Cell> cells = sh1.getRow(ifirstRowNum).cellIterator();
		int i = 0;
		while (cells.hasNext()) {
			Cell cell = cells.next();
			String tempcolName = cell.getStringCellValue().trim();
			System.out.println(tempcolName);
			if (String.valueOf(tempcolName).trim().equals(colName.trim())) {
				found = true;
				wb.close();
				return i;
			}
			i++;
		}
		wb.close();
		return -1;

	}

}
