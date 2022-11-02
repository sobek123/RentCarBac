package pl.macieksob.rentCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.macieksob.rentCar.dto.RoleDTO;
import pl.macieksob.rentCar.service.RoleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/roles")
@CrossOrigin("http://localhost:3000")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/newRole",method=RequestMethod.POST)
    public void addRole(@RequestBody @Valid RoleDTO role){
        roleService.addRole(role);


    }

    @GetMapping
    public List<RoleDTO> getAllRoles(){
        return roleService.getRoles();
    }

    @RequestMapping(value = "/editRole/{id}",method=RequestMethod.DELETE)
    public void editRole(@PathVariable Long id, @RequestBody RoleDTO newRole){
        roleService.editRole(id, newRole);

    }

    @RequestMapping(value = "/deleteRole/{id}",method=RequestMethod.DELETE)
    public void deleteRole(@PathVariable Long id){
        roleService.deleteRoleById(id);


    }

    @RequestMapping(value = "/deleteRole",method=RequestMethod.DELETE)
    public void deleteRole(@RequestBody RoleDTO role){
        roleService.deleteRole(role);

    }

    @GetMapping("/{id}")
    public RoleDTO getRole(@PathVariable Long id){
        return roleService.getRole(id);


    }
}
