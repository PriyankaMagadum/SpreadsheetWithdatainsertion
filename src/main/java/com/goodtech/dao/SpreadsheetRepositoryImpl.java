package com.goodtech.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SpreadsheetRepositoryImpl implements SpreadsheetRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public int createTable() {
		jdbcTemplate.execute("DROP TABLE IF EXISTS bahubali");
		jdbcTemplate.execute("create table bahubali(Id int)");
		return 1;
	}

	@Override
	public int alterTable(String tableColumn) {
		jdbcTemplate.execute("ALTER TABLE bahubali ADD "+tableColumn+ " varchar(80)");
		System.out.println("gheun tak");
		return 1;
	}

	@Override
	public int insertData( int id,String data,String columnName) {
		jdbcTemplate.execute("INSERT INTO bahubali (Id,"+columnName+ ") VALUES ('"+id+"','"+data+"')");
		
		
		return 1;
	}

}
