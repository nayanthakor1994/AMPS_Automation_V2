package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadPropertyFile {
	Properties configProperty;

	public ReadPropertyFile() {
		File src = new File(System.getProperty("user.dir") + "/config.properties");
//		File src1 = new File(System.getProperty("user.dir") + "/Configuration/testdata.properties");

		try {
			FileInputStream fis = new FileInputStream(src);
			configProperty = new Properties();
			configProperty.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is" + e.getMessage());
		}
	}

	// Get Extent Report Path
//	public String getReportPath() {
//		String extentReport = configProperty.getProperty("EXTENT_REPORT_PATH");
//		return extentReport;
//	}

	public String getExcelFileName() {
		String sheetOne = configProperty.getProperty("excel_file");
		return sheetOne;
	}

}
