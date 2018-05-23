package com.goodtech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goodtech.service.SpreadsheetService;

@RestController
@RequestMapping("/api")
public class SpreadsheetController {

	@Autowired
	private SpreadsheetService service;

	@GetMapping(value = "/myfirsturl")
	public Integer getdata() {
		return service.getListFromExcel();
	}

}
