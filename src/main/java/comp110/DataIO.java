package comp110;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataIO {

	static Week parseWeek(String jsonPath, String scenario) throws IOException {
		Week week = new Week(scenario);
		ArrayList<String> days = new ArrayList<>(Arrays.asList("Sun", "Mon", "Tue", "Wed", "Thu", "Fri"));

		Scanner jsonScanner = new Scanner(new File(jsonPath));
		String content = jsonScanner.useDelimiter("\\Z").next();
		jsonScanner.close();

		JSONArray masterSchedule = new JSONArray(content);
		for (int i = 0; i < masterSchedule.length(); i++) {
			JSONObject json = masterSchedule.getJSONObject(i);

			// Only set shifts for the given week
			String weekStartDate = json.getString("weekStartDate");
			if (! weekStartDate.equals(scenario)) continue;

			int numPeople = json.getInt("numPeople");
			int hour = json.getInt("hour");
			String dayString = json.getString("day");
			int day = days.indexOf(dayString);
			if (day == -1) {
				System.out.println("Invalid day: " + dayString);
				System.exit(-1);
			}
			week.getShifts()[day][hour] = new Shift(day, hour, numPeople);
		}

		return week;
	}

	static Staff parseStaff(String jsonPath, String scenario) throws IOException {
		Staff staff = new Staff();
		ArrayList<String> days = new ArrayList<>(Arrays.asList("Sun", "Mon", "Tue", "Wed", "Thu", "Fri"));

		Scanner jsonScanner = new Scanner(new File(jsonPath));
		String content = jsonScanner.useDelimiter("\\Z").next();
		jsonScanner.close();

		JSONArray staffAvailability = new JSONArray(content);
		for (int i = 0; i < staffAvailability.length(); i++) {
			JSONObject json = staffAvailability.getJSONObject(i);

			// Only set shifts for the given week
			String weekStartDate = json.getString("weekStartDate");
			if (! weekStartDate.equals(scenario)) continue;

			String name = json.getString("name");
			String gender = json.getString("gender");
			int capacity = json.getInt("hoursCapacity");
			int level = json.getInt("experienceLevel");

			Employee employee = staff.getEmployeeByName(name);
			int[][] availability;
			if (employee == null) {
				// Create new employee
				availability = new int[7][24];
				employee = new Employee(name, capacity, gender.equals("male") ? false : true, level, availability);
				staff.add(employee);
			}

			// Update employee availability
			availability = employee.getAvailability();
			String dayString = json.getString("day");
			int day = days.indexOf(dayString);
			if (day == -1) {
				System.out.println("Invalid day: " + dayString);
				System.exit(-1);
			}
			int start = json.getInt("start");
			int end = json.getInt("end");
			for (int hour = start; hour < end; hour++) {
				availability[day][hour] = 1;
			}
			employee.setAvailability(availability);
		}

		return staff;
	}
}
