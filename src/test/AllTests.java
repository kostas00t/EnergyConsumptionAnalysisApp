package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AggregatorTest.class, LoaderTest.class, MainEngineTest.class, MeasurementRecordTest.class,
		ResultReporterTest.class, ResultTest.class })
public class AllTests {

}
