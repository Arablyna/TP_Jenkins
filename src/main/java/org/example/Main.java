package org.example;

import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        // Instead of System.out.println, use logger.info
        logger.info("Hello world!");
    }
}