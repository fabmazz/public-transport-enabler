package de.schildbach.pte.live;

import de.schildbach.pte.NetworkProvider;
import de.schildbach.pte.TurinMatoProvider;
import de.schildbach.pte.dto.Departure;
import de.schildbach.pte.dto.QueryDeparturesResult;
import de.schildbach.pte.dto.StationDepartures;
import org.junit.Test;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

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
}
