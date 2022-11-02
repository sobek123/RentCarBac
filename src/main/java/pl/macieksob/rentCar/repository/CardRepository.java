package pl.macieksob.rentCar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.macieksob.rentCar.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {
}
