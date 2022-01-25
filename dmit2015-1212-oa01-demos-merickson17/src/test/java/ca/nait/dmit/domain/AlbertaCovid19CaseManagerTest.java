package ca.nait.dmit.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AlbertaCovid19CaseManagerTest {

    AlbertaCovid19CaseManager caseManager;

    @BeforeEach // to make our code more efficient, create the case manager before each test like this!
    void beforeEach() throws IOException {
        caseManager = AlbertaCovid19CaseManager.getInstance();
    }


    @Test
    void getAlbertaCovid19CaseList() {
        // AlbertaCovid19CaseManager caseManager = new AlbertaCovid19CaseManager();
        //AlbertaCovid19CaseManager caseManager = AlbertaCovid19CaseManager.getInstance();
        assertEquals(436495, caseManager.getAlbertaCovid19CaseList().size());
    }


    @Test
    void countTotalActiveCases() {
        //AlbertaCovid19CaseManager caseManager = AlbertaCovid19CaseManager.getInstance();
        assertEquals(64_129, caseManager.countTotalActiveCases()); //can use underscores to space out long numbers for readability
    }

    @Test
    void countActiveCasesByAhsZone() {
        assertEquals(29_544, caseManager.countActiveCasesByAhsZone("Calgary Zone"));
        assertEquals(24_062, caseManager.countActiveCasesByAhsZone("Edmonton Zone"));
    }

    @Test
    void distinctAhsZone() {
        List<String> ahsZoneList = caseManager.distinctAhsZone();
        ahsZoneList.forEach(System.out::println);
        assertEquals(6, ahsZoneList.size());
    }

    @Test
    @DisplayName("FindById")
    void findById() {
        Optional<AlbertaCovid19Case> optionalResult = caseManager.findById(1);
        assertTrue(optionalResult.isPresent());
        AlbertaCovid19Case result = optionalResult.get();
        assertEquals(1, result.getId());
        assertEquals("Edmonton Zone", result.getAhsZone());
        assertEquals("Recovered", result.getCaseStatus());
        assertEquals("Confirmed", result.getCaseType());
        assertEquals("30-39 years", result.getAgeGroup());

        Optional<AlbertaCovid19Case> noOptionalResult = caseManager.findById(-1);
        assertTrue(noOptionalResult.isEmpty());
    }

//    @Test
//    @DisplayName("ActiveCasesAgeGroup")
//    void activeCasesAgeGroup() {
//
//    }

}