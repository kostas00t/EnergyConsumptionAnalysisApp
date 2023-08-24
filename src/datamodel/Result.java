package datamodel;

import java.util.ArrayList;
import java.util.HashMap;



public class Result implements IResult {
	private String description;
	private String aggregateFunction;
	private HashMap<String, ArrayList<MeasurementRecord>> detailedResults;
	private HashMap<String, Double> aggregateMeterKitchen;
	private HashMap<String, Double> aggregateMeterLaundry;
	private HashMap<String, Double> aggregateMeterAC;
	
	
	public Result(String description, String aggregateFunction) {
		this.description = description;
		this.aggregateFunction = aggregateFunction;
	}



	public Result(String description, String aggregateFunction,
			HashMap<String, ArrayList<MeasurementRecord>> detailedResults,
			HashMap<String, Double> aggregateMeterKitchen, HashMap<String, Double> aggregateMeterLaundry,
			HashMap<String, Double> aggregateMeterAC) {
		this.description = description;
		this.aggregateFunction = aggregateFunction;
		this.detailedResults = detailedResults;
		this.aggregateMeterKitchen = aggregateMeterKitchen;
		this.aggregateMeterLaundry = aggregateMeterLaundry;
		this.aggregateMeterAC = aggregateMeterAC;
	}



	@Override
	public int add(String timeUnit, MeasurementRecord record) {
		int size = 0;
		
		for (HashMap.Entry<String, ArrayList<MeasurementRecord>> entry : detailedResults.entrySet()) {
			ArrayList<MeasurementRecord> arrayWithData = entry.getValue();
			size += arrayWithData.size();
		}
		return size;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public HashMap<String, ArrayList<MeasurementRecord>> getDetailedResults() {
		return this.detailedResults;
	}

	@Override
	public HashMap<String, Double> getAggregateMeterKitchen() {
		return this.aggregateMeterKitchen;
	}

	@Override
	public HashMap<String, Double> getAggregateMeterLaundry() {
		return this.aggregateMeterLaundry;
	}

	@Override
	public HashMap<String, Double> getAggregateMeterAC() {
		return this.aggregateMeterAC;
	}

	@Override
	public String getAggregateFunction() {
		return this.aggregateFunction;
	}



	
}
