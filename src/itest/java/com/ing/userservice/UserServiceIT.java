package com.ing.userservice;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/itest/resources/",
    format = {"pretty", "json:target/cucumber.json",
        "lv.ctco.cukes.core.formatter.CukesJsonFormatter:target/cucumber2.json"},
    glue = {"lv.ctco.cukes", "src/itest/resources"},
    tags = {"~@ignore", "~@debug"})
public class UserServiceIT {

    @BeforeClass
    public static void setup() {

        UserServiceApplication.main(new String[0]);
    }
}
