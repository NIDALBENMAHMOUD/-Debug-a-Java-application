package com.hemebiotech.analytics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Nidal Ben Mahmoud 
 * get a list of symptoms. put the list in alphabetical order.
 * count the occurrences of each symptom in the list.
 * save the names of the symptoms, with the count in the file.
 */
public class AnalyticsCounter {

	/**
	 * Method Reading: get a list of symptoms
	 * 
	 * @param dataInput parameter of type class ISymptomReader to use the
	 *                  GetSymptoms()
	 * @return An instance of the ISymptomReader to import GetSymptoms () method.
	 * @see ISymptomReader
	 */

	public List<String> Reading(ISymptomReader dataInput) {
		return dataInput.GetSymptoms();
	}

	/**
	 * Method Sorting: put the list in alphabetical order count the occurrences of
	 * each symptom in the list.
	 * 
	 * @param list table type of list of symptoms not ordered with duplicates
	 * @return mapSorted: TreeMap including symptoms in alphabetical order and count
	 *         occurrences.
	 */
	public TreeMap<String, Integer> Sorting(List<String> list) {
		Set<String> noDuplicateSet = new HashSet<String>(list); // Delete duplicates
		List<String> noDuplicateList = new ArrayList<String>(noDuplicateSet); // new list without duplicates

		Map<String, Integer> symptomsOccurrence = new HashMap<String, Integer>(); // Temporary Map to get (symptoms,
																					// occurrence)

		for (String symptom : noDuplicateList) { // fill the Map with symptoms(key) and occurrences(value)
			symptomsOccurrence.put(symptom, Collections.frequency(list, symptom));
		}

		TreeMap<String, Integer> mapSorted = new TreeMap<String, Integer>(); // TreeMap to sort alphabetically
		mapSorted.putAll(symptomsOccurrence); // fill the TreeMap with our temporary Map
		return mapSorted;
	}

	/**
	 * Method Saving: save the names of the symptoms, with the count in the file
	 * 
	 * @param map TreeMap allow to write in a file the result of the sorting()
	 *            method.
	 *            <p>
	 *            If the file doesn't exist, a new file named "results.out" will be
	 *            created.
	 *            </p>
	 */
	public void saving(TreeMap<String, Integer> map) {

		File results = new File("result.out");
		// try with resources
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(results))) {

			for (Map.Entry<String, Integer> entry : map.entrySet()) { // run the TreeMap and write each line into the
																	// result.out

				writer.write(entry.getKey() + " = " + entry.getValue());
				writer.newLine();
				writer.flush();

			}

		} catch (FileNotFoundException e) {
			System.err.println("the file " + results + "  does not exist!");
		} catch (IOException e) {
			System.err.println("unable to write to file ! ");
		}

	}
}
