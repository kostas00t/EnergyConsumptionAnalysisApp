package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import datamodel.MeasurementRecord;
import datamodel.Result;

public class ResultTest {

	private static Result result;
	private static String description = "testDescription";
	private static String aggregateFunction = "avg";
	private static HashMap<String, ArrayList<MeasurementRecord>> detailedResults;
	private static HashMap<String, Double> aggregateMeterKitchen;
	private static HashMap<String, Double> aggregateMeterLaundry;
	private static HashMap<String, Double> aggregateMeterAC;
	private static MeasurementRecord record;
	private static ArrayList<MeasurementRecord> arrayWithRecords;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		result = new Result(description, aggregateFunction);
		detailedResults = new HashMap<String, ArrayList<MeasurementRecord>>();
		aggregateMeterKitchen = new HashMap<String, Double>();
		aggregateMeterLaundry = new HashMap<String, Double>();
		aggregateMeterAC = new HashMap<String, Double>();
		record = new MeasurementRecord("1/7/2007","13:15:00","10","20","30");
		arrayWithRecords = new ArrayList<MeasurementRecord>();
		arrayWithRecords.add(record);
		detailedResults.put("07", arrayWithRecords);
		aggregateMeterKitchen.put("07", 10.0);
		aggregateMeterLaundry.put("07", 20.0);
		aggregateMeterAC.put("07", 30.0);

	}

	@Before
	public void setUp() throws Exception {
		result = new Result(description, aggregateFunction, detailedResults, aggregateMeterKitchen, aggregateMeterLaundry, aggregateMeterAC);
	}

	@Test
	public final void testAdd() {
		assertEquals(1, result.add("--", record));
	}

	@Test
	public final void testGetDescription() {
		assertEquals("testDescription", result.getDescription());
	}

	@Test
	public final void testGetDetailedResults() {
		assertEquals(detailedResults, result.getDetailedResults());
	}

	@Test
	public final void testGetAggregateMeterKitchen() {
		assertEquals(aggregateMeterKitchen, result.getAggregateMeterKitchen());
	}

	@Test
	public final void testGetAggregateMeterLaundry() {
		assertEquals(aggregateMeterLaundry, result.getAggregateMeterLaundry());	
	}

	@Test
	public final void testGetAggregateMeterAC() {
		assertEquals(aggregateMeterAC, result.getAggregateMeterAC());	
	}

	@Test
	public final void testGetAggregateFunction() {
		assertEquals(aggregateFunction, result.getAggregateFunction());	
	}
}


