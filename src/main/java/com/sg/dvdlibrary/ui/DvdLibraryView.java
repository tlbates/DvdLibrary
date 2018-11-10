package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.Dvd;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author tylerbates
 */
public class DvdLibraryView {

    UserIO io;
    
    public DvdLibraryView(UserIO io){
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List collection of DVD's");
        io.print("2. Display a specific DVD");
        io.print("3. Add a DVD to collection");
        io.print("4. Remove a DVD from collection");
        io.print("5. Edit Info of a DVD");
        io.print("6. List DVDs from within the past number of years");
        io.print("7. List DVDs by a specific MPAA rating");
        io.print("8. List DVDs from a specific director");
        io.print("9. List DVDs from a specific studio");
        io.print("10. Get Average Movie Age");
        io.print("11. Find Newest DVD");
        io.print("12. Find Oldest DVD");
        io.print("13. Find the average notes in all the DVD's");
        io.print("14. EXIT PROGRAM");

        return io.readInt("Please select from the above choices.", 1, 14);
    }

    public Dvd getNewDvdInfo() {
        String title = io.readString("Please enter DVD title");
        String releaseDate = io.readString("Please enter the release date. (yyyy-MM-dd)");
        String mpaa = io.readString("Please enter the MPAA rating");
        String director = io.readString("Please enter the director' name");
        String studio = io.readString("Please enter the studio in which the movie was made");
        String userNote = io.readString("Please enter your note about the movie");
        Dvd currentDvd = new Dvd(title);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setDirector(director);
        currentDvd.setMpaa(mpaa);
        currentDvd.setStudio(studio);
        currentDvd.setUserNote(userNote);
        return currentDvd;
    }

    public void displayDvdList(List<Dvd> dvdList) {
        for (Dvd currentDvd : dvdList) {
            io.print(currentDvd.getTitle());
        }

        io.readString("Please hit enter to continue");
    }

    public void displayDvd(Dvd dvd) {
        if (dvd != null) {
            io.print("----- " + dvd.getTitle() + " -----");
            io.print("Release date: " + dvd.getReleaseDate());
            io.print("MPAA Rating: " + dvd.getMpaa());
            io.print("Director: " + dvd.getDirector());
            io.print("Studio: " + dvd.getStudio());
            io.print("User Note: " + dvd.getUserNote());
        }
        io.readString("Please hit enter to continue.");
    }

    public Dvd editDvd(String title, Dvd dvd) {
        String releaseDate = io.readString("Please enter the release date");
        String mpaa = io.readString("Please enter the MPAA rating");
        String director = io.readString("Please enter the director' name");
        String studio = io.readString("Please enter the studio in which the movie was made");
        String userNote = io.readString("Please enter your note about the movie");
        Dvd currentDvd = new Dvd(title);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setDirector(director);
        currentDvd.setMpaa(mpaa);
        currentDvd.setStudio(studio);
        currentDvd.setUserNote(userNote);
        return currentDvd;
    }
    
    public int getNumOfYears(){
        return io.readInt("We are going to list all the movies in the past "
                + "how many years?");
    }
    
    public void displayDvdsWithinYear(List<Dvd> dvdList){
        for (Dvd currentDvd : dvdList) {
            io.print("Title: " + currentDvd.getTitle());
            io.print("Release Date: " + currentDvd.getReleaseDate().toString());
            String dvdAge = Long.toString(currentDvd.getMovieAge());
            io.print("DVD Age: " + dvdAge);
            io.print("-------------------------");
        }

        io.readString("Please hit enter to continue");
    }
    
    public String getMpaaRating(){
        return io.readString("What kind of MPAA rated movies would you list?");
    }
    
    public void displayMpaaListedMovies(List<Dvd> dvdList){
        for (Dvd currentDvd: dvdList){
            io.print("Title: " + currentDvd.getTitle());
            io.print("MPAA Rating: " + currentDvd.getMpaa());
            io.print("-------------------------");
        }
        
        io.readString("Please hit enter to continue.");
    }
    
    public String getDirector(){
        return io.readString("What director would you like to list movies from?");
    }
    
    public void displayDirectorListedMovies(Map<String, List<Dvd>> dvdList){
        Set<String> director = dvdList.keySet();
        
        director.stream()
                .forEach(mpaa -> {  System.out.println("=====================================");
                                    System.out.println("MPAA Rating: " + mpaa);
                                    dvdList.get(mpaa).stream().forEach(d -> System.out.println(d.getTitle()));
                                });
    }
    
    public String getStudio(){
        return io.readString("What studio would you like to list movies from?");
    }
    
    public void displayStudioListedMovies(List<Dvd> dvdList){
        for (Dvd currentDvd: dvdList){
            io.print("Title: " + currentDvd.getTitle());
            io.print("Studio: " + currentDvd.getStudio());
            io.print("-------------------------");
        }
        
        io.readString("Please hit enter to continue.");
    }
    
    public void averageMovieAge(double average){
        io.print("The average Movie age of all the movies are: " + average);
        io.print("----------------------------------------------------");
        io.readString("Please hit enter to continue.");
    }
    
    public void newestMovie(Dvd dvd){
        io.print("Title: " + dvd.getTitle());
        io.print("Release Date: " + dvd.getReleaseDate());
        io.print("Movie Age: " + dvd.getMovieAge());
        io.print("-------------------------");
        io.readString("Please hit enter to continue.");
    }
    
    public void oldestMovie(Dvd dvd){
        io.print("Title: " + dvd.getTitle());
        io.print("Release Date: " + dvd.getReleaseDate());
        io.print("Movie Age: " + dvd.getMovieAge());
        io.print("-------------------------");
        io.readString("Please hit enter to continue.");
    }
    
    public void averageNotes(double average){
        io.print("The average number of notes in all the movies are: " + average);
        io.print("----------------------------------------------------");
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveDvdBanner() {
        io.print("----- REMOVE DVD -----");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("Dvd successfully removed. Please hit enter to continue");
    }

    public void displayAllBanner() {
        io.print("----- DISPLAY ALL DVD's -----");
    }

    public void displayDvdBanner() {
        io.print("----- DISPLAY DVD -----");
    }

    public String getTitleChoice() {
        return io.readString("Please enter the Movie Title");
    }

    public void displayCreateDvdBanner() {
        io.print("----- CREATE DVD -----");
    }

    public void displayCreateSuccessBanner() {
        io.readString("DVD successfully created. Please hit enter to continue");
    }

    public void editDvdBanner() {
        io.print("----- EDIT DVD -----");
    }

    public void editDvdSuccessBanner() {
        io.readString("DVD successfully edited. Please hit enter to continue");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage(String error){
        io.print("----- ERROR -----");
        io.print(error);
    }
    
    public void displayDvdWithinYearBanner(){
        io.print("----- DVDS WITHIN YEAR LIMIT -----");
    }
    
    public void displayDvdByMpaaBanner(){
        io.print("----- DVDS BY MPAA -----");
    }
    
    public void displayDvdByDirectorBanner(){
        io.print("----- DVDS BY DIRECTOR -----");
    }
    
    public void displayDvdByStudioBanner(){
        io.print("----- DVDS BY STUDIO -----");
    }
    
    public void displayAverageMovieAgeBanner(){
        io.print("----- AVERAGE MOVIE AGE -----");
    }
    
    public void displayFindNewestBanner(){
        io.print("----- FIND NEWEST DVD -----");
    }
    
    public void displayFindOldestBanner(){
        io.print("----- FIND OLDEST DVD -----");
    }
    
    public void displayAverageNotesBanner(){
        io.print("----- FIND OLDEST DVD -----");
    }
}
