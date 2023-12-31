package com.example.demo.di4;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource("classpath:/setting.properties")
public class SysInfo {

    @Value("#{systemProperties['user.timezone']}")
    String timeZone;
    @Value("#{systemEnvironment['USERNAME']}")
    String userName;


    @Value("${autosaveDir}")
    String autosaveDir;
    @Value("${autosaveInterval}")
    int autosaveInterval;
    @Value("${autosave}")
    boolean autosave;


    @Override
    public String toString() {
        return "SysInfo{" +
                "timeZone='" + timeZone + '\'' +
                ", userName='" + userName + '\'' +
                ", autosaveDir='" + autosaveDir + '\'' +
                ", autosaveInterval=" + autosaveInterval +
                ", autosave=" + autosave +
                '}';
    }
}
