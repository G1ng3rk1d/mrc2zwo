package ca.gkwb.mrc2zwo.bo;

import ca.gkwb.mrc2zwo.exception.ConvertException;

public class WorkoutConverterBOImpl implements WorkoutConverterBO {

	@Override
	public String convertMRCtoZWO(String inputFile) throws ConvertException {
		return inputFile;
	}

}