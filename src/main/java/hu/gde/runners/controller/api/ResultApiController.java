package hu.gde.runners.controller.api;

import hu.gde.runners.model.Race;
import hu.gde.runners.model.Result;
import hu.gde.runners.model.Runner;
import hu.gde.runners.repository.RaceRepository;
import hu.gde.runners.repository.ResultRepository;
import hu.gde.runners.repository.RunnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ResultApiController {
    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private RunnerRepository runnerRepository;

    @Autowired
    private RaceRepository raceRepository;

    @PostMapping("/addResult")
    public ResponseEntity<Result> addResult(@RequestBody Result newResult) {
        Optional<Runner> runnerOptional = runnerRepository.findById(newResult.getRunner().getId());
        Optional<Race> raceOptional = raceRepository.findById(newResult.getRace().getId());

        if (runnerOptional.isPresent() && raceOptional.isPresent()) {
            newResult.setRunner(runnerOptional.get());
            newResult.setRace(raceOptional.get());
            Result savedResult = resultRepository.save(newResult);
            return ResponseEntity.ok(savedResult);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/getRaceRunners/{id}")
    public List<Result> getRaceRunners(@PathVariable Long id) {
        return resultRepository.findByRaceIdOrderByTimeInMinutesAsc(id);
    }

    @GetMapping("/getAverageTime/{raceId}")
    public double getAverageTime(@PathVariable Long raceId) {
        List<Result> results = resultRepository.findByRaceId(raceId);
        return results.stream().mapToInt(Result::getTimeInMinutes).average().orElse(0);
    }

}

