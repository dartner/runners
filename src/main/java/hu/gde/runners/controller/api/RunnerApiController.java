package hu.gde.runners.controller.api;

import hu.gde.runners.model.Runner;
import hu.gde.runners.repository.RunnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RunnerApiController {
    @Autowired
    private RunnerRepository runnerRepository;

    @GetMapping("/getRunners")
    public List<Runner> getRunners() {
        return runnerRepository.findAll();
    }

    @PostMapping("/addRunner")
    public Runner addRunner(@RequestBody Runner runner) {
        return runnerRepository.save(runner);
    }

}