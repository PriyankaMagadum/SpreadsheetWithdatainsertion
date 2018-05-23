package com.goodtech.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodtech.dao.SpreadsheetRepository;

@Service
public class SpreadsheetServiceImpl implements SpreadsheetService {
	private static final String FILE_PATH = "C:\\Users\\Netsol\\Downloads\\Spreadsheet\\Work in Progress Template.xlsx";

	@Autowired
	private SpreadsheetRepository repository;

	public int getListFromExcel() {
		FileInputStream fis = null;
		
		List<String>columnList=new ArrayList<>();
		List<String>dataList=new ArrayList<>();
		System.out.println("before create table");
		repository.createTable();
		System.out.println("after create table");
		try {
			fis = new FileInputStream(FILE_PATH);
			Workbook workbook = new XSSFWorkbook(fis);
			int numberOfSheets = workbook.getNumberOfSheets();
			for (int i = 0; i < numberOfSheets; i++) {
				Sheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rowIterator = sheet.iterator();
				while (rowIterator.hasNext()) {
					Row row = (Row) rowIterator.next();
			
				System.out.println("row starts from="+	row.getRowNum());
				
				
				if(	row.getRowNum()==0)
				{

					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = (Cell) cellIterator.next();
						String cellValue;
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							cellValue = cell.getStringCellValue();
							columnList.add(cellValue);
							
							break;
						case Cell.CELL_TYPE_NUMERIC:
							cellValue = cell.getNumericCellValue() + "";
							columnList.add(cellValue);
							break;
						case Cell.CELL_TYPE_FORMULA:
							cellValue = cell.getCellFormula();
							columnList.add(cellValue);
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							cellValue = cell.getBooleanCellValue() + "";
							columnList.add(cellValue);
							break;
						default:
							cellValue = "";
							columnList.add(cellValue);
							break;
						}
						System.out.println("cell Column="+cellValue);
						repository.alterTable(cellValue);
						
					
				}
				}
				
				else {
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = (Cell) cellIterator.next();
					
						String cellValue;
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							cellValue = cell.getStringCellValue();
							dataList.add(cellValue);
							break;
						case Cell.CELL_TYPE_NUMERIC:
							cellValue = cell.getNumericCellValue() + "";
							dataList.add(cellValue);
							break;
						case Cell.CELL_TYPE_FORMULA:
							cellValue = cell.getCellFormula();
							dataList.add(cellValue);
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							cellValue = cell.getBooleanCellValue() + "";
							dataList.add(cellValue);
							break;
						default:
							cellValue = "";
							dataList.add(cellValue);
							break;
						}
						
						repository.insertData(row.getRowNum()+1,cellValue,columnList.get(cell.getColumnIndex()));
					System.out.println("data inserted");
					
				}
					
					
				}
				
				
				
				
				
				
				
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = (Cell) cellIterator.next();
						String cellValue;
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							cellValue = cell.getStringCellValue();
							break;
						case Cell.CELL_TYPE_NUMERIC:
							cellValue = cell.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_FORMULA:
							cellValue = cell.getCellFormula();
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							cellValue = cell.getBooleanCellValue() + "";
							break;
						default:
							cellValue = "";
							break;
						}
						System.out.println(cellValue);
					}
				}
			}
			
		
			
			
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1;
	}
}
