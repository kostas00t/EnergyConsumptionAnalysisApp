package datamodel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MeasurementRecord {
	private LocalDate date;
	private LocalTime time;
	private double kitchen;
	private double laundry;
	private double ac;
	
	public MeasurementRecord(String date, String time, String kitchen, String laundry, String ac) {
		this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("d/M/yyyy"));
		this.time = LocalTime.parse(time, DateTimeFormatter.ofPattern("H:mm:ss"));
		this.kitchen = Double.parseDouble(kitchen);
		this.laundry = Double.parseDouble(laundry);
		this.ac = Double.parseDouble(ac);
	}

	public LocalDate getDate() {
		return date;
	}

	public LocalTime getTime() {
		return time;
	}

	public double getKitchen() {
		return kitchen;
	}

	public double getLaundry() {
		return laundry;
	}

	public double getAc() {
		return ac;
	}
	
}
