package ca.gkwb.mrc2zwo.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.gkwb.mrc2zwo.exception.ConvertException;

public class WorkoutConverterBOImpl implements WorkoutConverterBO {

	private String HEADER = "HEADER";
	private String DATA = "DATA";
	private String PERFPRO = "PERFPRO";
	private String PERFDESC = "PERFDESC";
	
	@Override
	public String convertMRCtoZWO(String inputFile) throws ConvertException {
		
		Map<String, String> mrcMap = generateMrcMap(inputFile);
		
		Map<String, String> headerMap = parseHeader(mrcMap.get(HEADER));
		List<Map<String, String>> workoutList = parseWorkout(mrcMap.get(DATA));
		return workoutList.toString();
	}
	
	public Map<String, String> generateMrcMap(String inputFile) {
			
		Map<String, String> resultMap = new HashMap<String,String>();
		resultMap.put(HEADER, getSection("[COURSE HEADER]", "[END COURSE HEADER]", inputFile));
		resultMap.put(DATA, getSection("[COURSE DATA]", "[END COURSE DATA]", inputFile));
		resultMap.put(PERFPRO, getSection("[PERFPRO COURSE DATA]", "[END PERFPRO COURSE DATA]", inputFile));
		resultMap.put(PERFDESC, getSection("[PERFPRO DESCRIPTIONS]", "[END PERFPRO DESCRIPTIONS]", inputFile));
		return resultMap;
	}
	
	public String getSection(String start, String end, String targetStr) {
		int startIndex = targetStr.indexOf(start);
		int endIndex = targetStr.indexOf(end);
		return targetStr.substring(startIndex+start.length(), endIndex);				
	}
	
	public Map<String, String> parseHeader(String header) {
		Map<String, String> headerMap = new HashMap<String, String>();
		
		String[] headerLines = header.split("\n");
		for (String headerLine : headerLines) {
			if (headerLine.contains("=")) {
				String[] headerLineItem = headerLine.split("=");
				headerMap.put(headerLineItem[0].trim(), headerLineItem[1].trim());
			}
		}
		return headerMap;
	}
	
	public List<Map<String, String>> parseWorkout(String workout) {
		List<Map<String,String>> workoutList = new ArrayList<Map<String, String>>();
		
		String[] workoutLines = workout.split("\n");
		for (String workoutLine : workoutLines) {
			
			Map<String, String> workoutMap = new HashMap<String, String>();
			
				//ignore null lines
				if (workoutLine.trim().length() == 0) continue;
				
				String[] workoutLineItem = workoutLine.split("\t");
				workoutMap.put("Seconds", convertMinutes(workoutLineItem[0].trim()));
				workoutMap.put("Power", convertPower(workoutLineItem[1].trim()));
				
				System.out.println(workoutLineItem[0] +"|"+workoutLineItem[1]);
				
				workoutList.add(workoutMap);
		}
		return workoutList;
	}
	
	public String convertMinutes(String inputMinutes) {
		Double minutes = new Double(inputMinutes);
		return (minutes * 60)+"";
	}
	
	public String convertPower(String inputPower) {
		Double power = new Double(inputPower);
		return (power / 100)+"";
	}	
}