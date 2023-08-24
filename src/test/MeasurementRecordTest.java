package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import datamodel.MeasurementRecord;

public class MeasurementRecordTest {
	private static MeasurementRecord record;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		record = new MeasurementRecord("1/7/2007","13:15:00","10","20","30");
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testGetDate() {
		assertEquals("","2007-07-01",record.getDate().toString());
	}

	@Test
	public final void testGetTime() {
		assertEquals("","13:15",record.getTime().toString());
	}

	@Test
	public final void testGetKitchen() {
		assertEquals("",10.00,record.getKitchen(), 2);
	}

	@Test
	public final void testGetLaundry() {
		assertEquals("",20.00,record.getLaundry(), 2);
	}

	@Test
	public final void testGetAc() {
		assertEquals("",30.00,record.getAc(), 2);
	}

}
