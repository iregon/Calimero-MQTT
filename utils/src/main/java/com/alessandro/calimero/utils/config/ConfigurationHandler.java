package com.alessandro.calimero.utils.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ConfigurationHandler {

    private ObjectMapper mapper = new ObjectMapper();
    private InstallationConfiguration configuration = null;

    public ConfigurationHandler(String filename) {
        loadConfiguration(filename);
    }

    private boolean loadConfiguration(String filename) {
        File file = new File(getClass().getClassLoader().getResource(filename).getFile());
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        try {
            configuration = mapper.readValue(file, InstallationConfiguration.class);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public InstallationConfiguration getInstallationConfiguration() {
        return configuration;
    }
}
