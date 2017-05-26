package de.wenzlaff.tools;

import java.util.Collection;
import java.util.Iterator;

import org.opensky.model.StateVector;

/**
 * Klasse zum umwandeln ins KML Format.
 * 
 * http://blog.wenzlaff.de/?p=8176
 * 
 * @author Thomas Wenzlaff
 *
 */
public class Kml {

	private static final String HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document>";

	private static final String FOOTER = "</Document></kml>";

	/**
	 * 
	 * https://github.com/openskynetwork/opensky-api
	 * 
	 * arbeitet nur mit SDK >= 1.8.0_131
	 * 
	 * <pre>
	 
	   <?xml version="1.0" encoding="UTF-8"?>
		  <kml xmlns="http: www.opengis.net/kml/2.2">
		  <Document>
		 
		  <Placemark>
		  <name>Zürich</name>
		  <description>Zürich</description>
		  <Point>
		  <coordinates>8.55,47.3666667,0</coordinates>
		  </Point>
		  </Placemark>
		 
		  </Document>
		  </kml>
	 * 
	 * </pre>
	 */
	public static String formatStateVectorToKml(Collection<StateVector> states) {

		StringBuffer buffer = new StringBuffer(HEADER);

		for (Iterator<StateVector> iterator = states.iterator(); iterator.hasNext();) {
			StateVector s = iterator.next();
			buffer.append("<Placemark><name>");
			buffer.append(s.getCallsign());
			buffer.append("</name><description>");
			buffer.append("Callsign: " + s.getCallsign() + " Icao24: " + s.getIcao24() + " Land: " + s.getOriginCountry());
			buffer.append("</description><Point>");
			buffer.append(" <coordinates>" + s.getLongitude() + "," + s.getLatitude() + "</coordinates></Point></Placemark>");

			System.out.println(s);
		}
		buffer.append(FOOTER);
		return buffer.toString();
	}

}
