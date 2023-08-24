package mainengine;

import java.util.ArrayList;

import dataload.ILoader;
import dataload.Loader;
import datamodel.IResult;
import datamodel.MeasurementRecord;
import reporting.IResultReporter;
import reporting.ResultReporter;
import timeaggregation.Aggregator;
import timeaggregation.IAggregator;

public class MainEngine implements IMainEngine {

	private IResult result;
	
	@Override
	public int loadData(String fileName, String delimeter, Boolean hasHeaderLine, int numFields, ArrayList<MeasurementRecord> objCollection) {
		ILoader<MeasurementRecord> loader = new Loader();
		int i = loader.load(fileName, delimeter, hasHeaderLine, numFields, objCollection);
		return i;
	}

	@Override
	public IResult aggregateByTimeUnit(ArrayList<MeasurementRecord> inputMeasurements, String aggregatorType, String aggFunction, String description) {
		IAggregator aggregator = new Aggregator(aggregatorType);
		result = aggregator.aggregateByTimeUnit(inputMeasurements, aggFunction, description);
		return result;		
	}


	@Override
	public int reportResultInFile(IResult result, String reportType, String filename) {
		IResultReporter resultReporter = new ResultReporter(reportType);
		int i = resultReporter.reportResultInFile(result, filename);
		return i;
	}

}
