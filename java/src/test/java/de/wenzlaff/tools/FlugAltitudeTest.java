package de.wenzlaff.tools;

import java.io.IOException;
import java.util.Collection;

import org.junit.Test;
import org.opensky.api.OpenSkyApi;
import org.opensky.model.OpenSkyStates;
import org.opensky.model.StateVector;

/**
 * Klasse ausgeben der Flughöhen.
 * 
 * http://blog.wenzlaff.de/?p=8198
 * 
 * @author Thomas Wenzlaff
 *
 */
public class FlugAltitudeTest {

	@Test
	public void testPrintFlugAltitude() throws Exception {

		getData();
	}

	private void getData() throws IOException {

		OpenSkyApi api = new OpenSkyApi();
		OpenSkyStates os = api.getStates(0, null);
		Collection<StateVector> states = os.getStates();

		FlugAltitude.printFlugHoehen(states);
	}

}
