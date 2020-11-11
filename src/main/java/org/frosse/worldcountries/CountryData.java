package org.frosse.worldcountries;

import java.util.List;

public class CountryData {
    private List<Country> countries;
    private List<Country> visited;
    private List<Country> notVisited;

    public CountryData() {
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public List<Country> getVisited() {
        return visited;
    }

    public void setVisited(List<Country> visited) {
        this.visited = visited;
    }

    public List<Country> getNotVisited() {
        return notVisited;
    }

    public void setNotVisited(List<Country> notVisited) {
        this.notVisited = notVisited;
    }
}
