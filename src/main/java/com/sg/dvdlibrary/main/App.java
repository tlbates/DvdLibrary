
package com.sg.dvdlibrary.main;

import com.sg.dvdlibrary.controller.DvdLibraryController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author tylerbates
 */
public class App {
    public static void main(String[] args) {
        /*UserIO myIo = new UserIOConsoleImp();
        DvdLibraryView myView = new DvdLibraryView(myIo);
        DvdLibraryDao myDao = new DvdLibraryDaoImp();
        DvdLibraryController controller = new DvdLibraryController(myDao, myView);
        controller.run();*/
        
        ApplicationContext context = 
                new ClassPathXmlApplicationContext("applicationContext.xml");
        DvdLibraryController controller = context.getBean("controller", DvdLibraryController.class);
        controller.run();
    }
}