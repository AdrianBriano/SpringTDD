package com.adr.web;

import com.adr.service.ClinicService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-text-config.xml", "classpath:spring/mvc-core-config.xml"})
class OwnerControllerTest {
    
    @Autowired
    OwnerController ownerController;
    
    @Autowired
    ClinicService clinicService;

    @Test
    void tempTest() {
    }
}