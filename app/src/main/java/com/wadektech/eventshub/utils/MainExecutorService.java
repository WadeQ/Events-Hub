package com.wadektech.eventshub.utils;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainExecutorService {
    public Executor diskIO;
    public Executor networkIO;
    public Executor mainThreadIO;
    public static MainExecutorService eInstance;
    public static Object LOCK = new Object();

    public MainExecutorService(Executor diskIO, Executor networkIO, Executor mainThreadIO) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThreadIO = mainThreadIO;
    }

    public synchronized static MainExecutorService getInstance(){
        if (eInstance == null){
            synchronized (LOCK){
                if (eInstance == null){
                    //get the number of cores of android device
                    //the number of cores will be passed to limit thread pools
                    int cores = Runtime.getRuntime().availableProcessors();
                    eInstance = new MainExecutorService(
                            Executors.newSingleThreadExecutor(),
                            Executors.newFixedThreadPool(cores),
                            new MainEventsExecutorService()
                    );
                }
            }
        }
        return eInstance ;
    }

    public Executor getDiskIO() {
        return diskIO;
    }

    public Executor getNetworkIO(){
        return networkIO;
    }

    public Executor getMainThreadIO(){
        return mainThreadIO;
    }

    public static class MainEventsExecutorService implements Executor{
       public Handler handler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(Runnable command) {
         handler.post(command);
        }
    }
}
