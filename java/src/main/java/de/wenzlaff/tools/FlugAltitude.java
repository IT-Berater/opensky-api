package de.wenzlaff.tools;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import org.opensky.model.StateVector;

/**
 * Klasse zum ausgeben der Flughöhen
 * 
 * http://blog.wenzlaff.de/?p=8198
 * 
 * @author Thomas Wenzlaff
 *
 */
public class FlugAltitude {

	/** Trennkennzeichen. */
	private static final String T = ";";

	private static Collection<StateVector> svParken = new ArrayList<>();
	private static Collection<StateVector> sv1Bis500 = new ArrayList<>();
	private static Collection<StateVector> sv500Bis1000 = new ArrayList<>();
	private static Collection<StateVector> sv1000Bis2000 = new ArrayList<>();
	private static Collection<StateVector> sv2000Bis3000 = new ArrayList<>();
	private static Collection<StateVector> sv3000Bis4000 = new ArrayList<>();
	private static Collection<StateVector> sv4000Bis5000 = new ArrayList<>();
	private static Collection<StateVector> sv5000Bis6000 = new ArrayList<>();
	private static Collection<StateVector> sv6000Bis7000 = new ArrayList<>();
	private static Collection<StateVector> sv7000Bis8000 = new ArrayList<>();
	private static Collection<StateVector> sv8000Bis9000 = new ArrayList<>();
	private static Collection<StateVector> sv9000Bis10000 = new ArrayList<>();
	private static Collection<StateVector> sv10000Bis10500 = new ArrayList<>();
	private static Collection<StateVector> sv10500Bis11000 = new ArrayList<>();
	private static Collection<StateVector> sv11000Bis11500 = new ArrayList<>();
	private static Collection<StateVector> sv11500Bis12000 = new ArrayList<>();
	private static Collection<StateVector> sv12000Bis13000 = new ArrayList<>();
	private static Collection<StateVector> svUeber13000 = new ArrayList<>();

	/**
	 * Print der Flughöhen.
	 * 
	 * @param states
	 */
	public static void printFlugHoehen(Collection<StateVector> states) {

		for (StateVector stateVector : states) {
			setAltitude(stateVector);
		}

		ausgabe(states);
		addToFile(states);
	}

	private static void addToFile(Collection<StateVector> states) {

		Path path = Paths.get("flug-data.csv");

		try {
			if (!Files.exists(path)) {
				Files.createFile(path);
				String header = "UUID" + T + "Summme" + T + "Zeitpunkt" + T + "Parken 0" + T + "1-500 " + T + "500-1000" + T + "1000-2000" + T + "2000-3000" + T + "3000-4000" + T
						+ "4000-5000" + T + "5000-6000" + T + "6000-7000" + T + "7000-8000" + T + "8000-9000" + T + "9000-10000" + T + "10000-10500" + T + "10500-11000" + T
						+ "11000-11500" + T + "11500-12000" + T + "12000-13000" + T + "über 13000" + System.lineSeparator();
				Files.write(path, header.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
			}

			String data = UUID.randomUUID() + T + states.size() + T + new Date() + T + svParken.size() + T + sv1Bis500.size() + T + sv500Bis1000.size() + T + sv1000Bis2000.size()
					+ T + sv2000Bis3000.size() + T + sv3000Bis4000.size() + T + sv4000Bis5000.size() + T + sv5000Bis6000.size() + T + sv6000Bis7000.size() + T
					+ sv7000Bis8000.size() + T + sv8000Bis9000.size() + T + sv9000Bis10000.size() + T + sv10000Bis10500.size() + T + sv10500Bis11000.size() + T
					+ sv11000Bis11500.size() + T + sv11500Bis12000.size() + T + sv12000Bis13000.size() + T + svUeber13000.size() + System.lineSeparator();

			System.out.println(data);

			Files.write(path, data.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	private static void ausgabe(Collection<StateVector> states) {

		System.out.println("Flugbereich in Meter ; Flughöhe in Meter ");
		System.out.println("Parken 0    ; " + svParken.size());
		System.out.println("   1-500    ; " + sv1Bis500.size());
		System.out.println(" 500-1000   ; " + sv500Bis1000.size());
		System.out.println("1000-2000   ; " + sv1000Bis2000.size());
		System.out.println("2000-3000   ; " + sv2000Bis3000.size());
		System.out.println("3000-4000   ; " + sv3000Bis4000.size());
		System.out.println("4000-5000   ; " + sv4000Bis5000.size());
		System.out.println("5000-6000   ; " + sv5000Bis6000.size());
		System.out.println("6000-7000   ; " + sv6000Bis7000.size());
		System.out.println("7000-8000   ; " + sv7000Bis8000.size());
		System.out.println("8000-9000   ; " + sv8000Bis9000.size());
		System.out.println("9000-10000  ; " + sv9000Bis10000.size());
		System.out.println("10000-10500 ; " + sv10000Bis10500.size());
		System.out.println("10500-11000 ; " + sv10500Bis11000.size());
		System.out.println("11000-11500 ; " + sv11000Bis11500.size());
		System.out.println("11500-12000 ; " + sv11500Bis12000.size());
		System.out.println("12000-13000 ; " + sv12000Bis13000.size());
		System.out.println("über 13000  ; " + svUeber13000.size());

		System.out.println("Summme      ; " + states.size());
		System.out.println("Zeitpunkt   ; " + new Date());
		System.out.println("Quelle      ; The OpenSky Network, http://www.opensky-network.org");
	}

	private static void setAltitude(StateVector vector) {

		Double hoehe = vector.getAltitude();

		if (hoehe == null || hoehe <= 0) {
			svParken.add(vector);
		} else if (hoehe >= 1 && hoehe <= 500) {
			sv1Bis500.add(vector);
		} else if (hoehe >= 500 && hoehe <= 1000) {
			sv500Bis1000.add(vector);
		} else if (hoehe >= 1000 && hoehe <= 2000) {
			sv1000Bis2000.add(vector);
		} else if (hoehe >= 2000 && hoehe <= 3000) {
			sv2000Bis3000.add(vector);
		} else if (hoehe >= 3000 && hoehe <= 4000) {
			sv3000Bis4000.add(vector);
		} else if (hoehe >= 4000 && hoehe <= 5000) {
			sv4000Bis5000.add(vector);
		} else if (hoehe >= 5000 && hoehe <= 6000) {
			sv5000Bis6000.add(vector);
		} else if (hoehe >= 6000 && hoehe <= 7000) {
			sv6000Bis7000.add(vector);
		} else if (hoehe >= 7000 && hoehe <= 8000) {
			sv7000Bis8000.add(vector);
		} else if (hoehe >= 8000 && hoehe <= 9000) {
			sv8000Bis9000.add(vector);
		} else if (hoehe >= 9000 && hoehe <= 10000) {
			sv9000Bis10000.add(vector);
		} else if (hoehe >= 10000 && hoehe <= 10500) {
			sv10000Bis10500.add(vector);
		} else if (hoehe >= 10500 && hoehe <= 11000) {
			sv10500Bis11000.add(vector);
		} else if (hoehe >= 11000 && hoehe <= 11500) {
			sv11000Bis11500.add(vector);
		} else if (hoehe >= 11500 && hoehe <= 12000) {
			sv11500Bis12000.add(vector);
		} else if (hoehe >= 12000 && hoehe <= 13000) {
			sv12000Bis13000.add(vector);
		} else if (hoehe >= 13000) {
			svUeber13000.add(vector);
		}
	}

}
