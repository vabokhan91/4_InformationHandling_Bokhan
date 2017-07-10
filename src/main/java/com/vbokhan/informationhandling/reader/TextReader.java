package com.vbokhan.informationhandling.reader;

import com.vbokhan.informationhandling.exception.NoFileException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class TextReader {
    private static final Logger LOGGER = LogManager.getLogger();

    public String readDataFromFile(String fileName) throws NoFileException {
        if (fileName == null || fileName.isEmpty()) {
            throw new NoFileException(String.format("File %s not found", fileName));
        }
        String dataFromFile = null;
        try {
            dataFromFile = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, String.format("Error with file %s ", e.toString()));
        }
        return dataFromFile;
    }
}

