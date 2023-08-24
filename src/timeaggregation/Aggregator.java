package timeaggregation;

import java.util.ArrayList;
import java.util.HashMap;

import datamodel.IResult;
import datamodel.MeasurementRecord;
import datamodel.Result;

public class Aggregator implements IAggregator {

	private IResult result;
	private String timeUnitType;
	private HashMap<String, ArrayList<MeasurementRecord>> detailedResults;
	private HashMap<String, Double> aggregateMeterKitchen;
	private HashMap<String, Double> aggregateMeterLaundry;
	private HashMap<String, Double> aggregateMeterAC;

	public Aggregator() {}

	public Aggregator(String timeUnitType) {
		this.timeUnitType = timeUnitType;
	}

	@Override
	public IResult aggregateByTimeUnit(ArrayList<MeasurementRecord> inputMeasurements, String aggFunction, String description) {

		// Making the HashMap and the ArrayLists that we need (we tried to make the ArrayList with switch-case 
		// but didn't worked as intended, so we made them all)
		detailedResults = new HashMap<String, ArrayList<MeasurementRecord>>();

		ArrayList<MeasurementRecord> winter = new ArrayList<MeasurementRecord>();
		ArrayList<MeasurementRecord> spring = new ArrayList<MeasurementRecord>();
		ArrayList<MeasurementRecord> summer = new ArrayList<MeasurementRecord>();
		ArrayList<MeasurementRecord> autumn = new ArrayList<MeasurementRecord>();

		ArrayList<MeasurementRecord> jan = new ArrayList<MeasurementRecord>();
		ArrayList<MeasurementRecord> feb = new ArrayList<MeasurementRecord>();
		ArrayList<MeasurementRecord> mar = new ArrayList<MeasurementRecord>();
		ArrayList<MeasurementRecord> apr = new ArrayList<MeasurementRecord>();
		ArrayList<MeasurementRecord> may = new ArrayList<MeasurementRecord>();
		ArrayList<MeasurementRecord> jun = new ArrayList<MeasurementRecord>();
		ArrayList<MeasurementRecord> jul = new ArrayList<MeasurementRecord>();
		ArrayList<MeasurementRecord> aug = new ArrayList<MeasurementRecord>();
		ArrayList<MeasurementRecord> sep = new ArrayList<MeasurementRecord>();
		ArrayList<MeasurementRecord> oct = new ArrayList<MeasurementRecord>();
		ArrayList<MeasurementRecord> nov = new ArrayList<MeasurementRecord>();
		ArrayList<MeasurementRecord> dec = new ArrayList<MeasurementRecord>();			

		ArrayList<MeasurementRecord> mon = new ArrayList<MeasurementRecord>();
		ArrayList<MeasurementRecord> tue = new ArrayList<MeasurementRecord>();
		ArrayList<MeasurementRecord> wed = new ArrayList<MeasurementRecord>();
		ArrayList<MeasurementRecord> thu = new ArrayList<MeasurementRecord>();
		ArrayList<MeasurementRecord> fri = new ArrayList<MeasurementRecord>();
		ArrayList<MeasurementRecord> sat = new ArrayList<MeasurementRecord>();
		ArrayList<MeasurementRecord> sun = new ArrayList<MeasurementRecord>();

		ArrayList<MeasurementRecord> night = new ArrayList<MeasurementRecord>();
		ArrayList<MeasurementRecord> earlyMorning = new ArrayList<MeasurementRecord>();
		ArrayList<MeasurementRecord> morning = new ArrayList<MeasurementRecord>();
		ArrayList<MeasurementRecord> afternoon = new ArrayList<MeasurementRecord>();
		ArrayList<MeasurementRecord> evening = new ArrayList<MeasurementRecord>();

		// Runs through the inputMeasurements ArrayList and adds the record the appropriate ArrayList
		// based on the timeUnitType requested. Then puts the ArrayLists to the detailedResults HashMap
		for (int i = 0; i < inputMeasurements.size(); i++) {
			MeasurementRecord record = inputMeasurements.get(i);
			switch (timeUnitType) {
			case "season": 
				switch (record.getDate().getMonthValue()) {
				case 1: winter.add(record); break;
				case 2: winter.add(record); break;
				case 3: spring.add(record); break;
				case 4: spring.add(record); break;
				case 5: spring.add(record); break;
				case 6: summer.add(record); break;
				case 7: summer.add(record); break;
				case 8: summer.add(record); break;
				case 9: autumn.add(record); break;
				case 10: autumn.add(record); break;
				case 11: autumn.add(record); break;					
				case 12: winter.add(record); break;
				}
				detailedResults.put("Winter", winter);
				detailedResults.put("Spring", spring);
				detailedResults.put("Summer", summer);
				detailedResults.put("Autumn", autumn);
				break;
			case "month":
				switch (record.getDate().getMonthValue()) {
				case 1: jan.add(record); break;
				case 2: feb.add(record); break;
				case 3: mar.add(record); break;
				case 4: apr.add(record); break;
				case 5: may.add(record); break;
				case 6: jun.add(record); break;
				case 7: jul.add(record); break;
				case 8: aug.add(record); break;
				case 9: sep.add(record); break;
				case 10: oct.add(record); break;
				case 11: nov.add(record); break;
				case 12: dec.add(record); break;
				}
				detailedResults.put("01", jan);
				detailedResults.put("02", feb);
				detailedResults.put("03", mar);
				detailedResults.put("04", apr);
				detailedResults.put("05", may);
				detailedResults.put("06", jun);
				detailedResults.put("07", jul);
				detailedResults.put("08", aug);
				detailedResults.put("09", sep);
				detailedResults.put("10", oct);
				detailedResults.put("11", nov);
				detailedResults.put("12", dec);
				break;
			case "dayofweek":
				switch (record.getDate().getDayOfWeek().getValue()) {
				case 1: mon.add(record); break;
				case 2: tue.add(record); break;
				case 3: wed.add(record); break;
				case 4: thu.add(record); break;
				case 5: fri.add(record); break;
				case 6: sat.add(record); break;
				case 7: sun.add(record); break;
				}
				detailedResults.put("01", mon);
				detailedResults.put("02", tue);
				detailedResults.put("03", wed);
				detailedResults.put("04", thu);
				detailedResults.put("05", fri);
				detailedResults.put("06", sat);
				detailedResults.put("07", sun);
				break;
			case "periodofday":
				switch (record.getTime().getHour()) {
				case 0: night.add(record); break;
				case 1: night.add(record); break;
				case 2: night.add(record); break;
				case 3: night.add(record); break;
				case 4: night.add(record); break;
				case 5: earlyMorning.add(record); break;
				case 6: earlyMorning.add(record); break;
				case 7: earlyMorning.add(record); break;
				case 8: earlyMorning.add(record); break;
				case 9: morning.add(record); break;
				case 10: morning.add(record); break;
				case 11: morning.add(record); break;
				case 12: morning.add(record); break;
				case 13: afternoon.add(record); break;
				case 14: afternoon.add(record); break;
				case 15: afternoon.add(record); break;
				case 16: afternoon.add(record); break;
				case 17: evening.add(record); break;
				case 18: evening.add(record); break;
				case 19: evening.add(record); break;
				case 20: evening.add(record); break;
				case 21: night.add(record); break;
				case 22: night.add(record); break;
				case 23: night.add(record); break;
				}
				detailedResults.put("Night", night);
				detailedResults.put("Early Morning", earlyMorning);
				detailedResults.put("Morning", morning);
				detailedResults.put("Afternoon", afternoon);
				detailedResults.put("Evening", evening);
			}
		}

		// Making the HashMaps with the aggregated results, running the detailedResults HashMap
		// to get the final results with the aggregation function requested
		aggregateMeterKitchen = new HashMap<String, Double>();
		aggregateMeterLaundry = new HashMap<String, Double>();
		aggregateMeterAC = new HashMap<String, Double>();
		for (HashMap.Entry<String, ArrayList<MeasurementRecord>> entry : detailedResults.entrySet()) {
			String aggregationType = entry.getKey(); //e.g. for season: winter/summer/etc
			ArrayList<MeasurementRecord> arrayWithData = entry.getValue();
			double kitchen = 0;
			double laundry = 0;
			double ac = 0;
			if (aggFunction.equals("sum")) {
				for (int i = 0; i < arrayWithData.size(); i++) {
					kitchen += arrayWithData.get(i).getKitchen();
					laundry += arrayWithData.get(i).getLaundry();
					ac += arrayWithData.get(i).getAc();
				}
			} else if (aggFunction.equals("avg")) {
				for (int i = 0; i < arrayWithData.size(); i++) {
					kitchen += arrayWithData.get(i).getKitchen();
					laundry += arrayWithData.get(i).getLaundry();
					ac += arrayWithData.get(i).getAc();
				}
				kitchen = kitchen/arrayWithData.size();
				laundry = laundry/arrayWithData.size();
				ac = ac/arrayWithData.size();
			}
			aggregateMeterKitchen.put(aggregationType, kitchen);
			aggregateMeterLaundry.put(aggregationType, laundry);
			aggregateMeterAC.put(aggregationType, ac);
		}
		this.result = new Result(description, aggFunction, detailedResults, aggregateMeterKitchen, aggregateMeterLaundry, aggregateMeterAC);
		return this.result;
	}

	@Override
	public String getTimeUnitType() {
		return this.timeUnitType;
	}

}
