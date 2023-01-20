package com.tomcat.calculate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class SearchFutureTask {
    static public Integer searchKeyword(String path, String keyword) throws ExecutionException, InterruptedException {
        Integer count = 0;
        File file = new File(path);
        File[] files = file.listFiles();
        List<FutureTask<Integer>> list = new ArrayList<>();
        for (File itemFile: files) {
            KeyWordCal keyWordCal = new KeyWordCal(itemFile, keyword);
            FutureTask<Integer> futureTask = new FutureTask(keyWordCal);
            list.add(futureTask);
            Thread thread = new Thread(futureTask);
            thread.start();
        }

        for (FutureTask<Integer> futureTask : list) {
            count += futureTask.get();
        }
        return count;
    }

    public static void main(String[] args) {
        String keyword = "Exception";
        String path = "/Users/suyuzhou/project/springboot/code-produce/logs/coder-auth";
        try {
            Integer count = SearchFutureTask.searchKeyword(path, keyword);
            System.out.println(count);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
