package com.fullstack.app.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.fullstack.app.dto.EmployeeDto;

@Component
public class ReportUtils {

	public static final Logger logger = LoggerFactory.getLogger(ReportUtils.class);
			
	public boolean generateReport(HttpServletResponse response, List<EmployeeDto> list) {
		try {
			if (list != null && !list.isEmpty()) {
				response.setContentType("text/csv");
				DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy");
				String currentDateTime = dateFormatter.format(new Date());
				String headerKey = "Content-Disposition";
				String headerValue = "attachment; filename=EmployeeReport_" + currentDateTime + ".csv";
				response.setHeader(headerKey, headerValue);

				ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
				String[] csvHeader = { "First name", "Last Name", "Email id", "Mobile Number" };
				String[] nameMapping = { "firstName", "lastName", "emailId", "mobileNo" };

				csvWriter.writeHeader(csvHeader);

				for (EmployeeDto ob : list) {
					csvWriter.write(ob, nameMapping);
				}
				csvWriter.close();
			} else {
				return false;
			}
		} catch (IOException e) {
			logger.error("IOException while generating report :", e);
			return false;
		}
		return true;
	}
}
