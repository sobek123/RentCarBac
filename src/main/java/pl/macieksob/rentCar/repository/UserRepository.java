package pl.macieksob.rentCar.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.macieksob.rentCar.model.Role;
import pl.macieksob.rentCar.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository< User,Long > {

//    Optional<User> findByUsername(String username);

    @Query(nativeQuery = true,value = "select * from USERS c where c.name iLIKE  %:keyword%  or c.surname iLIKE  %:keyword% or c.email iLIKE  %:keyword% or c.date_Of_Birth iLIKE  %:keyword% " +
            "      or c.pesel iLIKE  %:keyword% or c.city iLIKE  %:keyword% or c.street iLIKE  %:keyword% or c.post_Code iLIKE  %:keyword% or c.number_Of_Street iLIKE  %:keyword% or c.number_Of_Flat iLIKE  %:keyword%")
    List<User> getByKeyword(String keyword);

    List<User> findAllByRolesIn(Set<Role> roles);

    User findByVerificationCode(String verificationCode);

    User findByResetPasswordToken(String token);

    @Query("UPDATE User U SET U.enabled = TRUE WHERE U.id = ?1")
    void setEnable(Long id);

    Optional<User> findByEmail(String email);

    List<User> findAllByEmail(String email);

    List<User> findAllByPesel(String pesel);

    List<User> findAllByName(String name, Sort by);

    List<User> findAllBySurname(String surname, Sort by);

    List<User> findAllByCity(String city, Sort by);

    boolean existsByEmail(String email);

    User findByPassword(String password);
}
