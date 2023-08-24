package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import datamodel.IResult;
import datamodel.MeasurementRecord;
import datamodel.Result;
import timeaggregation.Aggregator;

public class AggregatorTest {
	private static IResult resultAvg;
	private static IResult resultSum;
	private static ArrayList<MeasurementRecord> objCollection = new ArrayList<MeasurementRecord>();
	private static Aggregator aggr;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		resultAvg = new Result("description1", "avg");
		resultSum = new Result("description2", "sum");
		aggr = new Aggregator("season");
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testAggregateByTimeUnit() {
		resultAvg = aggr.aggregateByTimeUnit(objCollection,"avg","description1");
		resultSum = aggr.aggregateByTimeUnit(objCollection,"sum","description2");
		assertEquals(resultAvg.getDescription(),"description1");
		assertEquals(resultSum.getDescription(),"description2");
	}

	@Test
	public final void testGetTimeUnitType() {
		assertEquals(aggr.getTimeUnitType(),"season");
		}

}
