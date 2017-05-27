package de.wenzlaff.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.opensky.model.StateVector;

/**
 * Klasse zum ausgeben der Flughöhen
 * 
 * http://blog.wenzlaff.de/?p=8176
 * 
 * @author Thomas Wenzlaff
 *
 */
public class FlugAltitude {

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
	private static Collection<StateVector> sv10000Bis110000 = new ArrayList<>();
	private static Collection<StateVector> sv110000Bis12000 = new ArrayList<>();
	private static Collection<StateVector> sv120000Bis13000 = new ArrayList<>();
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
	}

	private static void ausgabe(Collection<StateVector> states) {

		System.out.println("Parken      ; " + svParken.size());
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
		System.out.println("10000-11000 ; " + sv10000Bis110000.size());
		System.out.println("11000-12000 ; " + sv110000Bis12000.size());
		System.out.println("12000-13000 ; " + sv120000Bis13000.size());
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
		} else if (hoehe >= 10000 && hoehe <= 11000) {
			sv10000Bis110000.add(vector);
		} else if (hoehe >= 11000 && hoehe <= 12000) {
			sv110000Bis12000.add(vector);
		} else if (hoehe >= 12000 && hoehe <= 13000) {
			sv120000Bis13000.add(vector);
		} else if (hoehe >= 13000) {
			svUeber13000.add(vector);
		}
	}

}
