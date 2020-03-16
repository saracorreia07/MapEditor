package org.academiadecodigo.mapeditor;

import java.io.*;

public class File {


    public void save(String file, String text) throws IOException{

        FileWriter writer = new FileWriter(file);

        BufferedWriter bWriter = new BufferedWriter(writer);

        bWriter.write(text);

        bWriter.close();
    }

    public String load(String file) throws  IOException{

        FileReader reader = new FileReader(file);

        BufferedReader bReader = new BufferedReader(reader);

        String line = "";
        String result = "";

        while((line = bReader.readLine()) != null) {
            result += line + "\n";
        }

        bReader.close();

        return result;
    }
}
