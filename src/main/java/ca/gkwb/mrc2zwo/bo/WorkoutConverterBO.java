package ca.gkwb.mrc2zwo.bo;

import ca.gkwb.mrc2zwo.exception.ConvertException;

public interface WorkoutConverterBO {
	
	public String convertMRCtoZWO(String inputFile) throws ConvertException;
}
