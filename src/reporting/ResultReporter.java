package reporting;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import datamodel.IResult;

public class ResultReporter implements IResultReporter {

	private String reportType;
	
	public ResultReporter(String reportType) {
		this.reportType = reportType;
	}
	
	@Override
	public int reportResultInFile(IResult result, String filename) {
		
			// For the text report
		if (reportType.equals("txt")) {
			PrintWriter outputStream = null;
			try {
				outputStream = new PrintWriter(new FileOutputStream(filename)); 
			}
			catch(FileNotFoundException e){
				System.out.println("Problem opening files.");
				return -1;
			}
			outputStream.println(result.getDescription());
			outputStream.println("=======================================");
			outputStream.println(result.getAggregateFunction() + " consumption (watt-hours) over (a) Kitchen, (b) Laundry, (c) A/C\n");
			outputStream.println("Kitchen");
			outputStream.println("--------------");
			HashMap<String, Double> kitchenResults = result.getAggregateMeterKitchen();
			for (HashMap.Entry<String, Double> entry : kitchenResults.entrySet()) {
				String key = entry.getKey(); //e.g. for season: winter/summer/etc
				Double value = entry.getValue();  // value for the time period
				outputStream.println("* " + key + "\t" + value);
			}
			outputStream.println();
			outputStream.println("Laundry");
			outputStream.println("--------------");
			HashMap<String, Double> laundryResults = result.getAggregateMeterLaundry();
			for (HashMap.Entry<String, Double> entry : laundryResults.entrySet()) {
				String key = entry.getKey(); //e.g. for season: winter/summer/etc
				Double value = entry.getValue();  // value for the time period
				outputStream.println("* " + key + "\t" + value);
			}
			outputStream.println();
			outputStream.println("A/C");
			outputStream.println("--------------");
			HashMap<String, Double> acResults = result.getAggregateMeterAC();
			for (HashMap.Entry<String, Double> entry : acResults.entrySet()) {
				String key = entry.getKey(); //e.g. for season: winter/summer/etc
				Double value = entry.getValue();  // value for the time period
				outputStream.println("* " + key + "\t" + value); 
			}
			outputStream.close();
			
			// For the HTML report 
		} else if (reportType.equals("html")) {
			PrintWriter outputStream = null;
			try {
				outputStream = new PrintWriter(new FileOutputStream(filename)); 
			}
			catch(FileNotFoundException e){
				System.out.println("Problem opening files.");
				return -1;
			}
			outputStream.println("<HTML>\n");
			outputStream.println("<BODY>");
			outputStream.println("<H1>" + result.getDescription() + "</H1>");
			outputStream.println("<p>" + result.getAggregateFunction() + " consumption (watt-hours) over (a) Kitchen, (b) Laundry, (c) A/C" + "</p>");
			HashMap<String, Double> kitchenResults = result.getAggregateMeterKitchen();
			outputStream.println("<H2>" + "Kitchen" + "</H2>" + "<ul>");
			for (HashMap.Entry<String, Double> entry : kitchenResults.entrySet()) {
				String key = entry.getKey(); //e.g. for season: winter/summer/etc
				Double value = entry.getValue();  // value for the time period
				outputStream.println("<li>" + key + " " + value + "</li>");
			}
			outputStream.println("</ul>");
			HashMap<String, Double> laundryResults = result.getAggregateMeterLaundry();
			outputStream.println("<H2>" + "Laundry" + "</H2>" + "<ul>");
			for (HashMap.Entry<String, Double> entry : laundryResults.entrySet()) {
				String key = entry.getKey(); //e.g. for season: winter/summer/etc
				Double value = entry.getValue();  // value for the time period
				outputStream.println("<li>" + key + " " + value + "</li>");
			}
			outputStream.println("</ul>");
			outputStream.println("<H2>" + "A/C" + "</H2>" + "<ul>");
			HashMap<String, Double> acResults = result.getAggregateMeterAC();
			for (HashMap.Entry<String, Double> entry : acResults.entrySet()) {
				String key = entry.getKey(); //e.g. for season: winter/summer/etc
				Double value = entry.getValue();  // value for the time period
				outputStream.println("<li>" + key + " " + value + "</li>");
			}
			outputStream.println("</ul>");
			outputStream.println("</BODY>\n" + "</HTML>");
			outputStream.close();
			
			// For the Markdown report
		} else if (reportType.equals("md")) { 
			PrintWriter outputStream = null;
			try {
				outputStream = new PrintWriter(new FileOutputStream(filename)); 
			}
			catch(FileNotFoundException e){
				System.out.println("Problem opening files.");
				return -1;
			}
			outputStream.println("# " + result.getDescription() + "\n");
			outputStream.println(result.getAggregateFunction() + " consumption (watt-hours) over (a) Kitchen, (b) Laundry, (c) A/C\n");
			outputStream.println("## Kitchen\n");
			HashMap<String, Double> kitchenResults = result.getAggregateMeterKitchen();
			for (HashMap.Entry<String, Double> entry : kitchenResults.entrySet()) {
				String key = entry.getKey(); //e.g. for season: winter/summer/etc
				Double value = entry.getValue();  // value for the time period
				outputStream.println("* " + key + "\t" + value);
			}
			outputStream.println();
			outputStream.println("## Laundry\n");
			HashMap<String, Double> laundryResults = result.getAggregateMeterLaundry();
			for (HashMap.Entry<String, Double> entry : laundryResults.entrySet()) {
				String key = entry.getKey(); //e.g. for season: winter/summer/etc
				Double value = entry.getValue();  // value for the time period
				outputStream.println("* " + key + "\t" + value);
			}
			outputStream.println();
			outputStream.println("## A/C\n");
			HashMap<String, Double> acResults = result.getAggregateMeterAC();
			for (HashMap.Entry<String, Double> entry : acResults.entrySet()) {
				String key = entry.getKey(); //e.g. for season: winter/summer/etc
				Double value = entry.getValue();  // value for the time period
				outputStream.println("* " + key + "\t" + value);
			}
			outputStream.close();
		}
		return 0;
	}
}