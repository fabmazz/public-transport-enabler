package de.schildbach.pte.live;

import de.schildbach.pte.TurinMatoProvider;
import de.schildbach.pte.dto.*;
import org.junit.Test;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class MatoProviderLiveTest extends AbstractProviderLiveTest {
    public MatoProviderLiveTest() {
        super(new TurinMatoProvider());
    }

    @Test
    public void testDeparture() throws IOException {
        final QueryDeparturesResult result = provider.queryDepartures("gtt:476",
                Date.from(Instant.now()), 5, false
                );
        for (StationDepartures stDep: result.stationDepartures){
            System.out.println(stDep.location);

            for(Departure dep: stDep.departures){
                System.out.println(dep);
            }
        }
    }

    @Test
    public void testDate(){
        final Date date = Date.from(Instant.ofEpochMilli(1792057500000L));

        assertEquals("2026-10-15", TurinMatoProvider.formatLocalDate(date));

        assertEquals("11:45:00", TurinMatoProvider.formatLocalTime(date));
    }

    @Test
    public void testTripResponse() throws IOException {
        final Location from = new Location(LocationType.ADDRESS, "idisa",Point.fromDouble(45.0381828, 7.6671760),
                "Torino","Corso Spezia 7, Torino"
                );
        final Location to = new Location(LocationType.POI, "myid",Point.fromDouble(45.096813,7.699549),
                "Torino","Ospedale Giovanni Bosco, Torino"
        );
        final QueryTripsResult result = provider.queryTrips(from,null, to,
                Date.from(Instant.now()),true, null
        );

    }
}
