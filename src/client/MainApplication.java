package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import datamodel.IResult;
import datamodel.MeasurementRecord;
import mainengine.IMainEngine;
import mainengine.MainEngineFactory;

public class MainApplication {

	public static void main(String[] args) {
		MainEngineFactory mainEngineFactory = new MainEngineFactory();
		IMainEngine mainEngine = mainEngineFactory.createMainEngine("MainEngine");
		String choice = "0";
		ArrayList<MeasurementRecord> objCollection = null;
		ArrayList<String[]> metadataList = new ArrayList<String[]>();

		String filename = "";
		String delimeter = "";
		String hasHeaderLineAsAString = "";
		Boolean hasHeaderLine = false;
		String aggregatorType = "";
		String aggFunction = "";
		String description = "";
		String filenameOutput = "";
		String reportType = "";

		Boolean loadCompleted = false;
		Boolean aggregationCompleted = false;

		IResult results = null;
		
		System.out.println("Power Analysis Reporter Application");
		do 
		{
			Scanner input = new Scanner(System.in);
			System.out.println("What do you want to do?\n\n1  Load Data\n2  Aggregate Data\n3  Export Results\n4  Show History\n5  Exit");
			choice = input.next();

			switch (choice) {

			case "1":
				loadCompleted = true;
				objCollection = new ArrayList<MeasurementRecord>();
				System.out.println("Enter Filepath");
				filename = input.next(); 
				Scanner inputStream = null;
				boolean flag = true;
				while (flag) {
					try {
						inputStream = new Scanner(new FileInputStream(filename));
						flag = false;
					} catch (FileNotFoundException e) {
						System.out.println("File not found");
						inputStream = new Scanner(System.in);
						System.out.println("Enter a valid path");
						filename = input.next(); 
					}
				}
				inputStream.close();
				System.out.println("Select Delimeter: Tab/Semicolon");

				while (true) {
					delimeter = input.next();
					if (delimeter.equals("Tab") || delimeter.equals("TAB") || delimeter.equals("tab")) {
						delimeter = "\t";
						break;
					} else if (delimeter.equals("Semicolon") || delimeter.equals("SEMICOLON") || delimeter.equals("semicolon")) {
						delimeter = ";";
						break;
					}
					System.out.println("\nEnter a valid delimeter");
					System.out.println("Select Delimeter: Tab/Semicolon");
				}

				System.out.println("Does the input file have header line? y/n");


				while (true) {
					hasHeaderLineAsAString = input.next();
					if (hasHeaderLineAsAString.equals("y") || hasHeaderLineAsAString.equals("Y") ||
							hasHeaderLineAsAString.equals("yes") || hasHeaderLineAsAString.equals("YES") ||
							hasHeaderLineAsAString.equals("Yes")) {
						hasHeaderLine = true;
						break;
					} else if (hasHeaderLineAsAString.equals("n") || hasHeaderLineAsAString.equals("N") ||
							hasHeaderLineAsAString.equals("no") || hasHeaderLineAsAString.equals("NO") ||
							hasHeaderLineAsAString.equals("No")) {
						hasHeaderLine = false;
						break;
					}
					System.out.println("\nPlease enter a valid option");
					System.out.println("Does the input file have header line? y/n");
				}
				int numFields = 9;
				System.out.println("Lines Loaded: " + mainEngine.loadData(filename, delimeter, hasHeaderLine, numFields, objCollection) + "\n\n");

				break;

			case "2":
				if (!loadCompleted) {
					System.out.println("You have to load data first!");
					break;
				}
				aggregationCompleted = true;
				// We had some problems with Scanner methods next() and nextLine() while reading spaces
				// and we used that instead
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Enter a description");
				try {
					description = reader.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}

				System.out.println("Type of aggregation: season/month/day of week/period of day?");

				while (true) {
					try {
						aggregatorType = reader.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (aggregatorType.equals("season") || aggregatorType.equals("Season") || aggregatorType.equals("SEASON")) {
						aggregatorType = "season";
						break;
					} else if (aggregatorType.equals("month") || aggregatorType.equals("Month") || aggregatorType.equals("MONTH")) {
						aggregatorType = "month";
						break;
					} else if (aggregatorType.equals("day of week") || aggregatorType.equals("Day of week") || aggregatorType.equals("DAY OF WEEK")) {
						aggregatorType = "dayofweek";
						break;
					} else if (aggregatorType.equals("period of day") || aggregatorType.equals("Period of day") || aggregatorType.equals("PERIOD OF DAY")) {
						aggregatorType = "periodofday";
						break;
					}
					System.out.println("\nPlease enter a valid option");
					System.out.println("Type of aggregation: season/month/day of week/period of day?");
				}

				System.out.println("Aggregation Function: avg/sum?");

				while (true) {
					aggFunction = input.next();
					if (aggFunction.equals("avg") || aggFunction.equals("Avg") || aggFunction.equals("AVG") || 
							aggFunction.equals("average") || aggFunction.equals("Average") || aggFunction.equals("AVERAGE")) {
						aggFunction = "avg";
						break;
					} else if (aggFunction.equals("sum") || aggFunction.equals("Sum") || aggFunction.equals("SUM")) {
						aggFunction = "sum";
						break;
					}
					System.out.println("\nPlease enter a valid option");
					System.out.println("Aggregation Function: avg/sum?");
				}
				results = mainEngine.aggregateByTimeUnit(objCollection, aggregatorType, aggFunction, description);
				break;

			case "3": 
				if (!loadCompleted) {
					System.out.println("You have to load data first!");
					break;
				} else if (!aggregationCompleted) {
					System.out.println("You have to aggregate data first!");
					break;
				}

				System.out.println("Select file format for the report: html/txt/md"); 
				while (true) {
					reportType = input.next();
					if (reportType.equals("html") || reportType.equals("Html") || reportType.equals("HTML")) {
						reportType = "html";
						break;
					} else if (reportType.equals("txt") || reportType.equals("TXT") || reportType.equals("Txt") ||
							reportType.equals("text") || reportType.equals("TEXT") || reportType.equals("Text")) {
						reportType = "txt";
						break;
					} else if (reportType.equals("md") || reportType.equals("MD") || reportType.equals("Md") ||
							reportType.equals("markdown") || reportType.equals("MARKDOWN") || reportType.equals("Markdown") ||
							reportType.equals("MarkDown")) {
						reportType = "md";
						break;
					}
					System.out.println("\nPlease enter a valid option");
					System.out.println("Select file format for the report: html/txt/md"); 
				}

				System.out.println("Enter Output Directory Filepath (with a / (for unix OSes) or \\ (for Windows) at the end)");
				filenameOutput = input.next();
				File f = new File(filenameOutput);
				while (!f.isDirectory()) {
					System.out.println("Enter a valid directory filepath");
					filenameOutput = input.next();
					f = new File(filenameOutput);
				} 
				filenameOutput += "FinalReport." + reportType;
				System.out.println(mainEngine.reportResultInFile(results, reportType, filenameOutput));
				System.out.println("File saved at " + filenameOutput);

				// Adds all the valid parameters to an array to show a history report 
				String[] metadata = {filename,delimeter.toString(),hasHeaderLine.toString(),aggregatorType,aggFunction,description.toString(),filenameOutput,reportType};
				String[] metadataCopy = Arrays.copyOf(metadata, 8);
				metadataList.add(metadataCopy);
				break;
				
			case "4":
				if (metadataList.isEmpty()) {
					System.out.println("\nYou haven't made a report yet :) \n");
				} else {
					System.out.println("Input file, Delimeter, Has Header Line, Aggregator Type, Aggregator Function, Description, Output File, Type of Output File");
					for (String[] i: metadataList) {
						System.out.println(Arrays.deepToString(i));
					}
				}
				break;

			case "5":
				input.close();
				System.out.println("\nThank you for using our application, hope you liked it :D");
				System.exit(0);
				
			default:
				System.out.println("\nPlease select one of the following options");
			}

		} while (true);
	}
}
