package hu.gde.runners.controller;

import hu.gde.runners.model.Race;
import hu.gde.runners.model.Result;
import hu.gde.runners.repository.RaceRepository;
import hu.gde.runners.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RaceController {
    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private ResultRepository resultRepository;

    @GetMapping("/")
    public String listRaces(Model model) {
        model.addAttribute("races", raceRepository.findAll());
        return "races";
    }

    @GetMapping("/createRace")
    public String createRaceForm() {
        return "create-race";
    }

    @PostMapping("/addRace")
    public String addRace(@RequestParam String name, @RequestParam double distance) {
        Race race = new Race();
        race.setName(name);
        race.setDistance(distance);
        raceRepository.save(race);
        return "redirect:/";
    }

    @GetMapping("/raceDetails/{id}")
    public String getRaceDetails(@PathVariable Long id, Model model) {
        Race race = raceRepository.findById(id).orElse(null);
        List<Result> results = resultRepository.findByRaceIdOrderByTimeInMinutesAsc(id);
        model.addAttribute("race", race);
        model.addAttribute("results", results);
        return "race-details";
    }
}