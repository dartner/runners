package hu.gde.runners.controller.api;

import hu.gde.runners.model.Race;
import hu.gde.runners.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RaceApiController {
    @Autowired
    private RaceRepository raceRepository;

    @PostMapping("/updateRace")
    public ResponseEntity<Race> updateRace(@RequestBody Race updatedRace) {
        Optional<Race> raceOptional = raceRepository.findById(updatedRace.getId());
        if (raceOptional.isPresent()) {
            Race race = raceOptional.get();
            race.setName(updatedRace.getName());
            race.setDistance(updatedRace.getDistance());
            raceRepository.save(race);
            return ResponseEntity.ok(race);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
