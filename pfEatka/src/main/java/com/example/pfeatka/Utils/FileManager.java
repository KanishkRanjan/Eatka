package com.example.pfeatka.Utils;

import java.io.*;

public class FileManager<type>{
    public void writeFile(String loc, type data) {
        try(FileOutputStream fileOut = new FileOutputStream(loc)){
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(data);
            objOut.close();
            System.out.println("file writed");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public type readFile(String loc) {
        type tmp = null;
        try(FileInputStream fileIn = new FileInputStream(loc)){
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            tmp = (type) objIn.readObject();
            objIn.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return  tmp;
    }
}
