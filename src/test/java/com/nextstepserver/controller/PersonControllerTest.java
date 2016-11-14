package com.nextstepserver.controller;

import com.google.common.collect.ImmutableList;
import com.nextstepserver.dao.PersonDAO;
import com.nextstepserver.datatest.TestData;
import com.nextstepserver.entity.PersonEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonControllerTest {

    @Mock private PersonDAO personDAO;

    @InjectMocks PersonController personController;
    TestData testData = null;
    @Before
    public void setUp(){

        try {
            testData = new TestData();
            for(int i = 0; i < testData.listPerson.size(); i++){
                personDAO.saveObject(testData.listPerson.get(i));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetAllPerson() {
        //prepare

        when(personDAO.getAllPerson()).thenReturn(ImmutableList.of());
        List<PersonEntity> list = personController.getAllPerson();
        System.out.println(testData.listPerson);
        System.out.println(list);
        verify(personDAO).getAllPerson();
    }

}