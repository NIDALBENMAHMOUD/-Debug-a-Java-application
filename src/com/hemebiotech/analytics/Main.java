package com.hemebiotech.analytics;

import java.util.List;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) {
	
		AnalyticsCounter analyticsCounter = new AnalyticsCounter();
		ISymptomReader fileSymptomReader = new ReadSymptomDataFromFile("symptoms.txt");
		
	   List<String> smptoms=  analyticsCounter.Reading(fileSymptomReader);
	   
	   TreeMap<String, Integer> treeSymptoms= analyticsCounter.Sorting(smptoms);
	   
	   analyticsCounter.saving(treeSymptoms);
	    
	}

}
