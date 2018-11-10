/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author tylerbates
 */
public class DvdLibraryDaoTest {
    
    private DvdLibraryDao dao;
    
    public DvdLibraryDaoTest() {
        ApplicationContext ctx = 
        new ClassPathXmlApplicationContext("applicationContext.xml");
    dao = ctx.getBean("dvdLibraryDao", DvdLibraryDaoImp.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        List<Dvd> dvdList = dao.getAllDvds();
        for (Dvd dvd: dvdList){
            dao.removeDvd(dvd.getTitle());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addDvd method, of class DvdLibraryDao.
     */
    @Test
    public void testAddDvd() throws Exception {
        Dvd dvd = new Dvd("Movie1");
        dvd.setDirector("Director1");
        dvd.setMpaa("PG-13");
        
        dvd.setStudio("Entertainment");
        dvd.setUserNote("Family fun");
        
        dao.addDvd(dvd.getTitle(), dvd);
        
        Dvd fromDao = dao.getDvd(dvd.getTitle());
        
        assertEquals(dvd, fromDao);
    }

    /**
     * Test of removeDvd method, of class DvdLibraryDao.
     */
    @Test
    public void testRemoveDvd() throws Exception {
        Dvd dvd = new Dvd("Movie1");
        dvd.setDirector("Director1");
        dvd.setMpaa("PG-13");
        
        dvd.setStudio("Entertainment");
        dvd.setUserNote("Family fun");
        
        dao.addDvd(dvd.getTitle(), dvd);
        
        Dvd dvd2 = new Dvd("Movie2");
        dvd.setDirector("Director2");
        dvd.setMpaa("PG-14");
        
        dvd.setStudio("Horror Entertainment");
        dvd.setUserNote("Just Funny");
        
        dao.addDvd(dvd2.getTitle(), dvd2);
        
        dao.removeDvd(dvd.getTitle());
        assertEquals(1, dao.getAllDvds().size());
        assertNull(dao.getDvd(dvd.getTitle()));
        
        dao.removeDvd(dvd2.getTitle());
        assertEquals(0, dao.getAllDvds().size());
        assertNull(dao.getDvd(dvd2.getTitle()));
    }

    /**
     * Test of getAllDvds method, of class DvdLibraryDao.
     */
    @Test
    public void testGetAllDvds() throws Exception {
        Dvd dvd = new Dvd("Movie1");
        dvd.setDirector("Director1");
        dvd.setMpaa("PG-13");
        
        dvd.setStudio("Entertainment");
        dvd.setUserNote("Family fun");
        
        dao.addDvd(dvd.getTitle(), dvd);
        
        Dvd dvd2 = new Dvd("Movie2");
        dvd.setDirector("Director2");
        dvd.setMpaa("PG-14");
        
        dvd.setStudio("Horror Entertainment");
        dvd.setUserNote("Just Funny");
        
        dao.addDvd(dvd2.getTitle(), dvd2);
        
        assertEquals(2, dao.getAllDvds().size());
    }

    /**
     * Test of editDvd method, of class DvdLibraryDao.
     */
    @Test
    public void testEditDvd() throws Exception {
    }

  
}
