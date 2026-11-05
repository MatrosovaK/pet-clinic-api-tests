package org.example.steps;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("Начинаем сценарий: " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("Завершён сценарий: " + scenario.getName());
        System.out.println("Результат: " + scenario.getStatus());
        System.out.println("--------------------------------");
    }
}
