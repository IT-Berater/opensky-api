package de.wenzlaff.tools;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

import org.junit.Test;
import org.opensky.api.OpenSkyApi;
import org.opensky.model.OpenSkyStates;
import org.opensky.model.StateVector;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	ObjectMapper mapper = new ObjectMapper();
	JsonNode root = mapper.readTree(Path.of("credentials.json").toFile());

	String clientId = root.get("clientId").asText();
	String clientSecret = root.get("clientSecret").asText();

	OpenSkyApi api = new OpenSkyApi(clientId, clientSecret);
	OpenSkyStates states = api.getStates(0, null);

	Collection<StateVector> stateVector = states.getStates();

	FlugAltitude.printFlugHoehen(stateVector);
    }

    /**
     * 
     * <pre>
     Ein rechteckiger Bereich von 40 km rund um den Flughafen bedeutet, dass die Ausdehnung in jede Himmelsrichtung (Norden, Süden, Osten und Westen) 20 km beträgt. Um diesen Bereich zu berechnen, müssen wir die geografischen Koordinaten des Flughafens um diese Distanz erweitern.
    
    Da der Abstand in Längengrad und Breitengrad abhängig von der geografischen Position ist, müssen wir den Längengrad und Breitengrad entsprechend umrechnen.
    
    1 Breitengrad ≈ 111 km  
    1 Längengrad ≈ 111 km × cos(Breitengrad)  
    
    Für den Flughafen Hannover-Langenhagen (Breitengrad: 52.4611°) ergibt sich:
    
    1 Längengrad ≈ 111 km × cos(52.4611°) ≈ 67,7 km
    
    Die 20 km in jede Richtung sind:
    
    Breitengrad:  
    20 km / 111 km ≈ 0.18018°  
    
    Längengrad:  
    20 km / 67.7 km ≈ 0.29548°  
    
    ### Berechnung der Ecken des Rechtecks:
    1. **Nordwest (NW):**  
       Breitengrad: 52.4611 + 0.18018 = 52.64128  
       Längengrad: 9.6947 - 0.29548 = 9.39922  
    
    2. **Nordost (NE):**  
       Breitengrad: 52.64128  
       Längengrad: 9.6947 + 0.29548 = 9.99018  
    
    3. **Südwest (SW):**  
       Breitengrad: 52.4611 - 0.18018 = 52.28092  
       Längengrad: 9.39922  
    
    4. **Südost (SE):**  
       Breitengrad: 52.28092  
       Längengrad: 9.99018  
    
    ### Zusammenfassung der Ecken:
    Richtung: Breitengrad, Längengrad
    Richtung: Langithude, Longithude
    Nordwest: 52.64128, 9.39922  
    Nordost: 52.64128, 9.99018  
    Südwest: 52.28092, 9.39922  
    Südost: 52.28092, 9.99018  
     
     
     Die Dezimalwerte für die Grenzen des Rechtecks rund um den Flughafen Hannover-Langenhagen sind:
    
    minLat: 52.2809  
    maxLat: 52.6413  
    minLon: 9.3992  
    maxLon: 9.9902  
    
    Diese Werte geben den minimalen und maximalen Breitengrad (Latitude) sowie den minimalen und maximalen Längengrad (Longitude) des rechteckigen Bereichs an.
     * </pre>
     * 
     * @throws Exception
     */
    @Test
    public void testAlleFlugzeuge20KmUmHannoverAltitude() throws Exception {

	OpenSkyApi api = new OpenSkyApi();
	OpenSkyStates os = api.getStates(0, null, new OpenSkyApi.BoundingBox(52.2809, 52.6413, 9.3992, 9.9902));
	Collection<StateVector> states = os.getStates();

	FlugAltitude.printFlugInfos(states);
    }
}
