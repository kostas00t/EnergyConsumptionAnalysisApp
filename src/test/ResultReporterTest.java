package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import datamodel.IResult;
import datamodel.Result;
import reporting.ResultReporter;

public class ResultReporterTest {
	
	private static ResultReporter reporter;
	private static IResult result;
	private static String filename = "";
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reporter = new ResultReporter("txt");
		result = new Result("description", "sum");
	}

	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testReportResultInFile() {
		assertEquals(-1, reporter.reportResultInFile(result, filename));
	}

}
