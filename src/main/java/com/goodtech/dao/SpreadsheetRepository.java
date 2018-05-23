package com.goodtech.dao;

public interface SpreadsheetRepository {

	public int createTable();

	public int alterTable(String tableColumn);

	public int insertData( int id,String data,String columnName);
}
