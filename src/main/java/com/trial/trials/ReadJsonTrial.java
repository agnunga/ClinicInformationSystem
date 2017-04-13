/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trial.trials;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;

/**
 *
 * @author agunga
 */
public class ReadJsonTrial {

    JsonReader jsonReader;

    public ReadJsonTrial() {
        try {
            jsonReader = Json.createReader(new FileReader("jsondata.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadJsonTrial.class.getName()).log(Level.SEVERE, null, ex);
        }

        JsonStructure structure = jsonReader.read();
    }

}
