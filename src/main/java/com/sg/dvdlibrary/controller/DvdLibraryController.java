
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryDaoException;
import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImp;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import java.util.List;
import java.util.Map;


/**
 *
 * @author tylerbates
 */
public class DvdLibraryController {
    
    DvdLibraryDao dao;
    DvdLibraryView view;
    private UserIO io = new UserIOConsoleImp();
    
    public DvdLibraryController(DvdLibraryDao dao, DvdLibraryView view) {
        this.dao = dao;
        this.view = view;
    }
    
    public void run(){
        boolean restart = true;
        int menuSelect = 0;
        try {
        while (restart) {
            
            menuSelect = getMenuSelection();
            
            switch (menuSelect) {
                    case 1:
                        listDvds();
                        break;
                    case 2:
                        viewDvd();
                        break;
                    case 3:
                        createDvd();
                        break;
                    case 4:
                        removeDvd();
                        break;
                    case 5:
                        editDvd();
                        break;
                    case 6:
                        getDvdsWithinYear();
                        break;
                    case 7:
                        dvdsByMpaa();
                        break;
                    case 8:
                        dvdsByDirector();
                        break;
                    case 9:
                        dvdsByStudio();
                        break;
                    case 10:
                        averageMovieAge();
                        break;
                    case 11:
                        findNewest();
                        break;
                    case 12:
                        findOldest();
                        break;
                    case 13:
                        findAverageNotes();
                        break;
                    case 14:
                        restart = false;
                        break;
                    default:
                        unknownCommand();                   
                }
        }
        } catch (DvdLibraryDaoException e){
            view.displayErrorMessage(e.getMessage());
        }
        exitMessage();
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void createDvd() throws DvdLibraryDaoException {
        view.displayCreateDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayCreateSuccessBanner();
    }
    
    private void listDvds() throws DvdLibraryDaoException {
        view.displayAllBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }
    
    private void viewDvd() throws DvdLibraryDaoException {
        view.displayDvdBanner();
        String title = view.getTitleChoice();
        Dvd dvd = dao.getDvd(title);
        view.displayDvd(dvd);
    }
    
    private void removeDvd() throws DvdLibraryDaoException {
        view.displayRemoveDvdBanner();
        String title = view.getTitleChoice();
        dao.removeDvd(title);
        view.displayRemoveSuccessBanner();
    }
    
    private void editDvd() throws DvdLibraryDaoException {
        view.editDvdBanner();
        String title = view.getTitleChoice();
        Dvd dvdToEdit = dao.getDvd(title);
        Dvd editedDvd = view.editDvd(title, dvdToEdit);
        dao.editDvd(title, editedDvd);
        view.editDvdSuccessBanner();
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void getDvdsWithinYear() {
        view.displayDvdWithinYearBanner();
        List<Dvd> dvdList = dao.getDvdsWithinNumYears(view.getNumOfYears());
        view.displayDvdsWithinYear(dvdList);
    }

    private void dvdsByMpaa() {
        view.displayDvdByMpaaBanner();
        List<Dvd> dvdList = dao.getDvdsByMpaa(view.getMpaaRating());
        view.displayMpaaListedMovies(dvdList);
    }

    private void dvdsByDirector() {
        view.displayDvdByDirectorBanner();
        Map<String, List<Dvd>> dvdList = dao.getDvdsByDirector(view.getDirector());
        view.displayDirectorListedMovies(dvdList);
    }

    private void dvdsByStudio() {
        view.displayDvdByStudioBanner();
        List<Dvd> dvdList = dao.getDvdsByStudio(view.getStudio());
        view.displayStudioListedMovies(dvdList);
    }

    private void averageMovieAge() {
        view.displayAverageMovieAgeBanner();
        view.averageMovieAge(dao.getAverageMovieAge());
    }

    private void findNewest() {
        view.displayFindNewestBanner();
        view.newestMovie(dao.findNewestDvd());
    }

    private void findOldest() {
        view.displayFindOldestBanner();
        view.oldestMovie(dao.findOldestDvd());
    }
    
    private void findAverageNotes() throws DvdLibraryDaoException{
        view.displayAverageNotesBanner();
        List<Dvd> dvdList = dao.findAverageNotes();
        double average = dvdList.size() / dao.getAllDvds().size();
        view.averageNotes(average);
    }

}
