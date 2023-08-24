package dataload;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import datamodel.MeasurementRecord;

public class Loader implements ILoader<MeasurementRecord> {
	public Loader() {}
	String fileName = "";

	@Override
	public int load(String fileName, String delimeter, boolean hasHeaderLine, int numFields, ArrayList<MeasurementRecord> objCollection) {
		Scanner inputStream = null;
		boolean flag = true;
		while (flag) {
			try {
				inputStream = new Scanner(new FileInputStream(fileName));
				flag = false;
			} catch (FileNotFoundException e) {
				System.out.println("File not found");
				Scanner input = new Scanner(System.in);
				System.out.println("Enter a valid path");
				fileName = input.next(); 
				this.fileName = fileName;
			}
		}
		while (inputStream.hasNextLine()) {
			if (!hasHeaderLine) {
				try {
					String[] lineOfData = inputStream.nextLine().split(delimeter);
					MeasurementRecord validData = new MeasurementRecord(lineOfData[0], lineOfData[1], lineOfData[6], lineOfData[7], lineOfData[8]);
					objCollection.add(validData);
				} catch (Exception e) {
					//System.out.println("Invalid Data");
				}
			} else {
				try {
					inputStream.nextLine().split(delimeter);
					hasHeaderLine = false;
				} catch (Exception e) {
					//System.out.println("Invalid Data");
				}
			}
		}
		
		return objCollection.size();
	}
}
