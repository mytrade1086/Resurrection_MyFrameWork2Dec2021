package dummy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class dummy {

	@Test
	public void Das() throws IOException {

		String workBookpath = "./src/test/java/Data/POIWORK.xlsx";

		try {
			List<Map<String, Object>> lstExcel = ReadExcelInListofMap(workBookpath, "Sheet1", 2);
			System.out.println();
			String un = (String) lstExcel.get(1).get("username");
			Double sal1 = (Double) lstExcel.get(1).get("salary");
			Double sal2 = (Double) lstExcel.get(2).get("salary");

			Object dob = lstExcel.get(1).get("dob");
			Object dob2 = lstExcel.get(2).get("dob");

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

//		try {
//			WriteToColumn(workBookpath, "Sheet1", "skill", 1, "by code again");
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}

//		String[] temp=null;
//		try {
//			temp = ReadColumnData(0, workBookpath, "Sheet1", "skill");
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		
		// System.out.println(Arrays.toString(temp));
	}

	public String[] ReadColumnData(int iStartpos, String workbookPath, String sheetName, String colName)
			throws Exception {
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
		int ifirstLastRowNum = sh1.getLastRowNum();// 0 based. else -1 .*** Be careful. Might give null pointer if data
													// was present before

		// check if Sheet is having row and Row Number we are passing is less tha
		if (ifirstRowNum > -1 && ifirstRowNum <= iStartpos) {
			aData = new String[ifirstLastRowNum + 1];
			for (int i = iStartpos; i <= ifirstLastRowNum; i++) {
				Cell cc = sh1.getRow(i).getCell(colIndex);
				String data = "";
				if (cc.getCellType() == CellType.STRING) {
					data = cc.getStringCellValue().toString();
				} else if (cc.getCellType() == CellType.NUMERIC) {

					data = cc.getStringCellValue().toString();
				} else if (cc.getCellType() == CellType.BLANK) {

					System.out.println("xx");
				} else if (cc.getCellType() == CellType.BOOLEAN) {
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

	public void WriteToColumn(String workbookPath, String sheetName, String colName, int rowNum, String value)
			throws Exception {
		int colIndex = getColumnIndexFromName(workbookPath, sheetName, colName);
		Workbook wb = null;
		try {

			FileInputStream inputStream = new FileInputStream(new File(workbookPath));
			wb = WorkbookFactory.create(inputStream);
		} catch (Exception ex) {
			throw new Exception("Problem while Opening the workbook ");
		}
		Sheet sh1 = wb.getSheet(sheetName);
		if (String.valueOf(sh1) == null) {
			throw new Exception("Problem while reading sheet=>" + sheetName);
		}

		int ifirstRowNum = sh1.getFirstRowNum();// 0 based index. -1 if no row
		int ifirstLastRowNum = sh1.getLastRowNum();// 0 based. else -1 .*** Be careful. Might give null pointer if data
													// was present before
		sh1.getRow(rowNum).getCell(colIndex).setCellValue(value);
		// Write the output to a file
		try (OutputStream fileOut = new FileOutputStream(workbookPath)) {
			wb.write(fileOut);
			wb.close();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Map<String, Object>> ReadExcelInListofMap(String workbookPath, String sheetName, int startRowIndex)
			throws Exception {
		Workbook wb = null;
		Sheet sh1 = null;
		List<Map<String, Object>> rows = new LinkedList<Map<String, Object>>();
		try {
			wb = WorkbookFactory.create(new File(workbookPath));
		} catch (Exception ex) {
			throw new Exception("Problem while reading ");
		}
		sh1 = wb.getSheet(sheetName);
		if (sh1 == null) {
			throw new Exception("Problem while reading sheet=> " + sheetName);
		}

		// *****int iTotalColsinFirstRowj=sh1.getRow(0).getLastCellNum(); //1 based
		// 4 hence make 3

		if (String.valueOf(sh1) == null) {
			throw new Exception("Problem while reading sheet=>" + sheetName);
		}
		int ifirstRowNum = sh1.getFirstRowNum();// 0 based index. -1 if no row
		int ifirstLastRowNum = sh1.getLastRowNum();// 0 based. else -1 .*** Be careful. Might give null pointer if data
													// was present before
		if (ifirstRowNum < 0) {
			throw new Exception("Problem while reading rows. Please check Sheet has rows");
		}
		int iTotalColsinFirstRow = sh1.getRow(ifirstRowNum).getLastCellNum(); // 0 based
		if (ifirstRowNum < 0) {
			throw new Exception("Sheet does not have rows");
		} else {
			ifirstRowNum = startRowIndex;
			if(ifirstRowNum>ifirstLastRowNum)throw new Exception("Start Row=>"+ifirstRowNum +" LastRow=>"+ifirstLastRowNum+ "_Start row index can not be bigger than last row.");
			for (int i = ifirstRowNum; i <= ifirstLastRowNum; i++) {
				Map<String, Object> hm = new LinkedHashMap<String, Object>();
				String val = "";
				Object data = "";
				for (int c = 0; c < iTotalColsinFirstRow; c++) {

					if (i == 0) {// Making Header
						hm.put(sh1.getRow(i).getCell(c).getStringCellValue(), "");
					} else {

						Cell cc = sh1.getRow(i).getCell(c);
						if (cc.getCellType() == CellType.STRING) {
							data = cc.getStringCellValue().toString();
						} else if (cc.getCellType() == CellType.NUMERIC) {

							if (DateUtil.isCellDateFormatted(cc)) {
								System.out.println(cc.getDateCellValue());
								data = cc.getDateCellValue();
							} else {
								System.out.println(cc.getNumericCellValue());
								data = cc.getNumericCellValue();
							}

						} else if (cc.getCellType() == CellType.BLANK) {
							data = cc.getStringCellValue();
							System.out.println(data);
						} else if (cc.getCellType() == CellType.BOOLEAN) {
							data = cc.getBooleanCellValue();
						}
						hm.put(sh1.getRow(0).getCell(c).getStringCellValue(), data);
					}
				}
				rows.add(hm);
			}
			wb.close();
		}
		return rows;
	}

	public static String[][] ReadDataColumns(String workbookPath, String sheetName, int start, String[] aCols)
			throws Exception {
		String[][] data = new String[aCols.length][];
		boolean found = false;
		Workbook wb = null;

		try {
			wb = WorkbookFactory.create(new File(workbookPath));
		} catch (Exception ex) {
			throw new Exception("Problem while reading in WorkBookFactory Method ");
		}
		Sheet sh1 = wb.getSheet(sheetName);
		if (String.valueOf(sh1) == null) {
			throw new Exception("Problem while reading sheet=>" + sheetName);
		}
		int ifirstRowNum = sh1.getFirstRowNum();// 0 based index. -1 if no row
		int iLastRowNum = sh1.getLastRowNum();// 0 based index. -1 if no row

		// Example. If first row is index 6.. then start=6
		// lastrow=8 then data is in 6,7,8
		// count of data=3 8-6+1 array should be of this size

		if (start != 0) {
			ifirstRowNum = start;
		}
		for (int iCol = 0; iCol < aCols.length; iCol++) {
			String[] tempData = new String[iLastRowNum + 1 - ifirstRowNum];
			int i = 0;
			Iterator<Cell> cells = sh1.getRow(ifirstRowNum).cellIterator();

			while (cells.hasNext()) {
				Cell cell = cells.next();
				String tempcolName = cell.getStringCellValue().trim();
				System.out.println(tempcolName);
				if (String.valueOf(tempcolName).trim().equals(aCols[iCol].trim())) {
					found = true;
					break;
				}
				i++;
			}
			if (found) {
				for (int realdata = ifirstRowNum, col = 0; realdata <= iLastRowNum; realdata++, col++) {

					System.out.println("Printing realdata" + realdata + " i=>" + i);
					String sDataweneed = sh1.getRow(realdata).getCell(i).getStringCellValue();
					System.out.println(sDataweneed);
					tempData[col] = sDataweneed;
				}
			} else {
				throw new Exception(aCols[iCol] + " not found in Worbook ");
			}
			data[iCol] = tempData;
		}
		wb.close();
		return data;
	}

}
