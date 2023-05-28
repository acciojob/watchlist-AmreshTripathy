package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Amresh Tripathy
 */
public class MovieRepository {
    private Map<String, Movie> moviesData;
    private Map<String, Director> directorsData;
    private Map<String, List<String>> moviesWithDirectorMap;

    public MovieRepository() {
        moviesData = new HashMap<>();
        directorsData = new HashMap<>();
        moviesWithDirectorMap = new HashMap<>();
    }

    public void addMovie(Movie movie) {
        moviesData.put(movie.getName(), movie);
    }

    public void addDirector(Director director) {
        directorsData.put(director.getName(), director);
    }

    public void addMovieDirectorPair(String movie, String director) {
        List<String> temp = moviesWithDirectorMap.getOrDefault(director, new ArrayList<>());
        temp.add(movie);
        moviesWithDirectorMap.put(director, temp);
    }

    public Movie getMovieByName(String name) {
        return moviesData.get(name);
    }

    public Director getDirectorByName(String name) {
        return directorsData.get(name);
    }

    public List<String> getMoviesByDirectorName(String director) {
        return moviesWithDirectorMap.getOrDefault(director, new ArrayList<>());
    }

    public List<Movie> findAllMovies() {
        return new ArrayList<>(moviesData.values());
    }

    public void deleteDirectorByName(String director) {
        for (String movie : moviesWithDirectorMap.getOrDefault(director, new ArrayList<>())) {
            moviesData.remove(movie);
        }
        moviesWithDirectorMap.remove(director);
        directorsData.remove(director);
    }

    public void deleteAllDirectors() {
        Set<String> directors = directorsData.keySet();
        for (String director : directors) {
            deleteDirectorByName(director);
        }
        directors.clear();;
        moviesWithDirectorMap.clear();
     }
}
