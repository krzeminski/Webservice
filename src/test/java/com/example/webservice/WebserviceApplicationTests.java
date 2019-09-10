package com.example.webservice;

import com.example.webservice.controller.NotesController;
import com.example.webservice.service.NotesService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebserviceApplicationTests {

    @Autowired
    NotesController notesController;


    @Test
    public void contextLoads() {
        Assert.assertNotNull(notesController);
    }

}
