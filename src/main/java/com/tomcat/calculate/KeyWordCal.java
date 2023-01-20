package com.tomcat.calculate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class KeyWordCal implements Callable<Integer> {
    private File file;
    private Integer count;
    private String keyword;
    public KeyWordCal(File file, String keyword) {
        this.file = file;
        this.count = 0;
        this.keyword = keyword;
    }
    public KeyWordCal() {

    }

    @Override
    public Integer call() throws Exception {
        try(Scanner scanner = new Scanner(new FileInputStream(file))){
            while( scanner.hasNextLine()){
                if (scanner.nextLine().contains(keyword)) {
                    this.count++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return this.count;
    }
}
