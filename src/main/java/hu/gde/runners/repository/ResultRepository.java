package hu.gde.runners.repository;

import hu.gde.runners.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByRaceIdOrderByTimeInMinutesAsc(Long raceId);
    List<Result> findByRaceId(Long raceId);
}
