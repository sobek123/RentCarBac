package pl.macieksob.rentCar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.macieksob.rentCar.model.Additional;

import java.util.Optional;

@Repository
public interface AdditionalRepository extends JpaRepository<Additional, Long> {
    Optional<Additional> findByName(String name);
}
