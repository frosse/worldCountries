package org.frosse.worldcountries;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;

public class CountryDataHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CountryData.class);
    private static final String URL = "https://www.google.com/maps/place/";

    private final String filename;
    private final ObjectMapper objectMapper;
    private CountryData countryData;
    private Random random;

    public CountryDataHandler(String filename) {
        this.filename = filename;
        objectMapper = new ObjectMapper();
        random = new Random();
        try {
            countryData = readDataFromFileToObject(filename);
            initializeCountryData();
        } catch (IOException exception) {
            LOG.error(exception.getMessage());
        }
    }

    public void run() {
        try {
            Country country = countryData.getNotVisited().get(random.nextInt(countryData.getNotVisited().size()));
            countryData.getVisited().add(country);
            countryData.getNotVisited().remove(country);

            openInDefaultBrowser(country.getName());
            writeToJSONFile();
        } catch (IOException exception) {
            LOG.error(exception.getMessage());
        } catch (NullPointerException e) {
            LOG.error("Failed to load country data");
        }
    }

    private void initializeCountryData() {
        if (countryData.getNotVisited().size() < 1) {
            countryData.setNotVisited(countryData.getCountries());
        }
    }

    private CountryData readDataFromFileToObject(String filename) throws IOException {
        return objectMapper.readValue(Paths.get((filename)).toFile(), CountryData.class);
    }

    private void openInDefaultBrowser(String country) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        country = country.replace(" ", "+");
        LOG.info("Open " + URL + country + " in browser");
        runtime.exec("xdg-open " + URL + country);
    }

    private void writeToJSONFile() throws IOException {
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(Paths.get(filename).toFile(), countryData);
        LOG.info("Wrote data to file: " + filename);

    }
}
