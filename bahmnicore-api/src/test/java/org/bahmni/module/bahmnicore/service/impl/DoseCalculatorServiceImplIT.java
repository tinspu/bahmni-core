package org.bahmni.module.bahmnicore.service.impl;

import org.bahmni.module.bahmnicore.BaseIntegrationTest;
import org.bahmni.module.bahmnicore.service.DoseCalculatorService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DoseCalculatorServiceImplIT extends BaseIntegrationTest {

    @Autowired
    private DoseCalculatorService doseCalculatorService;

    @Before
    public void setUp() throws Exception {
        executeDataSet("doseCalculatorTestData.xml");
    }

    @Test
    public void shouldGetCalculatedDoseForAGivenRule() throws Exception {
        Double dosage = doseCalculatorService.calculateDose("person_1024_uuid", 5.0, DoseCalculatorFactory.DoseUnit.mg_per_m2);
        assertEquals(8.65,dosage,0.01);

        dosage = doseCalculatorService.calculateDose("person_1024_uuid", 5.0, DoseCalculatorFactory.DoseUnit.mg_per_kg);
        assertEquals(350.0,dosage,0.01);
    }

    @Test
    public void shouldGetCalculatedDoseForTheLatestObservations() throws Exception{
        Double dosage = doseCalculatorService.calculateDose("person_1030_uuid", 5.0, DoseCalculatorFactory.DoseUnit.mg_per_m2);
        assertEquals(9.58,dosage,0.01);

        dosage = doseCalculatorService.calculateDose("person_1030_uuid", 5.0, DoseCalculatorFactory.DoseUnit.mg_per_kg);
        assertEquals(400.0,dosage,0.01);
    }

}