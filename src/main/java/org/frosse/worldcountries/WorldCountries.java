package org.frosse.worldcountries;

public class WorldCountries {
    public static void main(String[] args) {
        CountryDataHandler countryDataHandler = new CountryDataHandler("countries.json");
        countryDataHandler.run();
    }
}
