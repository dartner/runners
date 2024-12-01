package hu.gde.runners.repository;

import hu.gde.runners.model.Runner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunnerRepository extends JpaRepository<Runner, Long> {
}
