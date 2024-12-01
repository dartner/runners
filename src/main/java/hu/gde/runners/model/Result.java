package hu.gde.runners.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Runner runner;
    @ManyToOne
    private Race race;
    private int timeInMinutes;

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    public Runner getRunner() {
        return runner;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Race getRace() {
        return race;
    }

    public Integer getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(Integer timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }
}

