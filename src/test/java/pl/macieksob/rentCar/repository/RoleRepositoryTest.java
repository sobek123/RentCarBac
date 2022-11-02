package pl.macieksob.rentCar.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.macieksob.rentCar.model.Role;
import pl.macieksob.rentCar.repository.RoleRepository;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void saveRoleTest(){
        Role role = new Role(4L, "ROLE_ADMIN");

        roleRepository.save(role);

        assertThat(role.getId()).isGreaterThan(0);
    }

    @Test
    public void deleteRoleTest(){
        Role role = roleRepository.findById(1L).get();

        roleRepository.delete(role);

        Role role2 = null;

        Optional<Role> opRole =  roleRepository.findByName("ROLE_ADMIN");

        if(opRole.isPresent()){
            role2 = opRole.get();
        }

        assertThat(role2).isNull();
    }

    @Test
    public void getRoleTest(){
        Role role = roleRepository.findById(1L).get();

        assertThat(role.getId()).isEqualTo(1L);
    }

    @Test
    public void getRoleListTest(){
        List<Role> all = roleRepository.findAll();

        assertThat(all.size()).isGreaterThan(0);
    }

    @Test
    public void updateRoleTest(){
        Role role = roleRepository.findById(1L).get();

        role.setName("ROLE_ADMIN");

        Role save = roleRepository.save(role);

        assertThat(save.getName()).isEqualTo("ROLE_ADMIN");
    }
}