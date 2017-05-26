package de.wenzlaff.tools;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;
import org.opensky.api.OpenSkyApi;
import org.opensky.model.OpenSkyStates;
import org.opensky.model.StateVector;

/**
 * Test-Klasse zum umwandeln ins KML Format.
 * 
 * http://blog.wenzlaff.de/?p=8176
 * 
 * @author Thomas Wenzlaff
 *
 */
public class KmlTest {

	@Test
	public void testFormatStateVectorToKml() throws Exception {

		OpenSkyApi api = new OpenSkyApi();
		OpenSkyStates os = api.getStates(0, null);
		Collection<StateVector> states = os.getStates();

		String ergebnis = Kml.formatStateVectorToKml(states);

		System.out.println(ergebnis);

		System.out.println("Anzahl Flugzeuge: " + states.size());
		assertTrue("Ups, es werden keine Flugdaten geliefert!", states.size() > 1);
	}

}
