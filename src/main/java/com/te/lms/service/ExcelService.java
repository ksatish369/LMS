package com.te.lms.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.te.lms.entity.Contact;
import com.te.lms.entity.Employee;
import com.te.lms.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExcelService {

	private final EmployeeRepository employeeRepository;

	public void downloadEmployeeData(HttpServletResponse httpServletResponse) throws IOException {
		List<Employee> employees = employeeRepository.findAll();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet xssfSheet = workbook.createSheet("Employee Details");
		XSSFRow xssfRow = xssfSheet.createRow(0);
		xssfRow.createCell(0).setCellValue("Sl.No");
		xssfRow.createCell(1).setCellValue("EmployeeId");
		xssfRow.createCell(2).setCellValue("EmployeeName");
		xssfRow.createCell(3).setCellValue("emailId");
		xssfRow.createCell(4).setCellValue("phoneNumber");

		int row = 1;
		int slNo = 1;
		for (Employee e : employees) {
			XSSFRow excelRow = xssfSheet.createRow(row++);
			excelRow.createCell(0).setCellValue(slNo++);
			excelRow.createCell(1).setCellValue(e.getEmployeeId());
			excelRow.createCell(2).setCellValue(e.getEmployeeName());
			excelRow.createCell(3).setCellValue(e.getEmailId());
			excelRow.createCell(4).setCellValue(e.getContacts().get(0).getContactNumber());
		}
		ServletOutputStream outputStream = httpServletResponse.getOutputStream();
		workbook.write(outputStream);
		outputStream.close();
		workbook.close();
	}

	public static boolean checkExcelFromat(MultipartFile file) {
		String type = file.getContentType();
		if (type.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		}
		return false;
	}

	public static List<Employee> convertExcelToList(InputStream is) {
		List list = new ArrayList<>();
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet = workbook.getSheet("Employee Details");
			int rownum = 0;
			Iterator<Row> rows = sheet.iterator();
			while (rows.hasNext()) {
				Row currentRow = rows.next();
				if (rownum == 0) {
					rownum++;
					continue;
				}
				Iterator<Cell> cellsInRow = currentRow.iterator();
				Employee employee = new Employee();
				int cellId = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();
					switch (cellId) {
					case 1:
						employee.setEmployeeId(currentCell.getStringCellValue());
						break;
					case 2:
						employee.setEmployeeName(currentCell.getStringCellValue());
						break;
					case 3:
						employee.setEmailId(currentCell.getStringCellValue());
						break;
					case 4:
						employee.getContacts()
								.add(Contact.builder().contactNumber((long) currentCell.getNumericCellValue()).build());
						break;
					default:
						break;
					}
					cellId++;

				}
				list.add(employee);
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
