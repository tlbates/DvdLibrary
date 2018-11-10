
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author tylerbates
 */
public class DvdLibraryDaoImp implements DvdLibraryDao {
    
    public static final String DVDS_FILE = "dvds.txt";
    public static final String DELIMITER = "--";
    private Map<String, Dvd> dvds = new HashMap<>();
    
    @Override
    public Dvd addDvd(String title, Dvd dvd) throws DvdLibraryDaoException {
        Dvd newDvd = dvds.put(title, dvd);
        writeDvd();
        return newDvd;
    }

    @Override
    public Dvd removeDvd(String title) throws DvdLibraryDaoException {
        Dvd removedDvd = dvds.remove(title);
        writeDvd();
        return removedDvd;
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException {
        loadDvds();
        return new ArrayList<Dvd>(dvds.values());
    }

    @Override
    public Dvd getDvd(String title) throws DvdLibraryDaoException {
        return dvds.get(title);
    }

    @Override
    public Dvd editDvd(String title, Dvd dvd) throws DvdLibraryDaoException {
        Dvd editedDvd = dvds.put(title, dvd);
        writeDvd();
        return editedDvd;
    }
    
    
    @Override
    public List<Dvd> getDvdsWithinNumYears(int years) {
        return dvds.values()
                .stream()
                .filter(d -> d.getMovieAge() < years)
                .collect(Collectors.toList());
    }

    @Override
    public List<Dvd> getDvdsByMpaa(String mpaa) {
        return dvds.values()
                .stream()
                .filter(d -> d.getMpaa().equalsIgnoreCase(mpaa))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Dvd>> getDvdsByDirector(String director) {
        return dvds.values()
                .stream()
                .filter(d -> d.getDirector().equalsIgnoreCase(director))
                .collect(Collectors.groupingBy(d -> d.getMpaa()));
    }

    @Override
    public List<Dvd> getDvdsByStudio(String Studio) {
        return dvds.values()
                .stream()
                .filter(d -> d.getStudio().equalsIgnoreCase(Studio))
                .collect(Collectors.toList());
    }

    @Override
    public double getAverageMovieAge() {
        return dvds.values()
                .stream()
                .mapToLong(Dvd::getMovieAge)
                .average()
                .getAsDouble();
    }

    @Override
    public Dvd findNewestDvd() {
        Dvd dvd = Collections.min(dvds.values(), Comparator.comparing(d -> d.getMovieAge()));
        return dvd;
    }

    @Override
    public Dvd findOldestDvd() {
        Dvd dvd = Collections.max(dvds.values(), Comparator.comparing(d -> d.getMovieAge()));
        return dvd;
    }
    
    @Override
    public List<Dvd> findAverageNotes() {
        return dvds.values()
                .stream()
                .filter(d -> !d.getUserNote().equalsIgnoreCase(""))
                .collect(Collectors.toList());
                
    }
    
    
    private void loadDvds() throws DvdLibraryDaoException {
        Scanner scanner;
        
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(DVDS_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException("Could not load DVD data into memory.",e);
        }
        
        String currentLine;
        String currentTokens[];
        
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Dvd currentDvd = new Dvd(currentTokens[0]);
            currentDvd.setReleaseDate(currentTokens[1]);
            currentDvd.setDirector(currentTokens[2]);
            currentDvd.setMpaa(currentTokens[3]);
            currentDvd.setStudio(currentTokens[4]);
            currentDvd.setUserNote(currentTokens[5]);
            
            dvds.put(currentDvd.getTitle(), currentDvd);
        }
        scanner.close();
    }
    
    private void writeDvd() throws DvdLibraryDaoException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(DVDS_FILE));
        } catch (IOException e) {
            throw new DvdLibraryDaoException("Could not save DVD data.", e);
        }
        
        List<Dvd> dvdList = this.getAllDvds();
        for (Dvd currentDvd : dvdList){
            out.println(currentDvd.getTitle() + DELIMITER
            + currentDvd.getReleaseDate() + DELIMITER
            + currentDvd.getDirector() + DELIMITER
            + currentDvd.getMpaa() + DELIMITER
            + currentDvd.getStudio() + DELIMITER
            + currentDvd.getUserNote());
            
            out.flush();
        }
        
        out.close();
    }

}
