package com.example.cinema.controller.management;

import com.example.cinema.bl.management.RoleService;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.RoleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @PostMapping("/add")
    public ResponseVO addRole(@RequestBody RoleForm roleForm){
        return roleService.addRole(roleForm);
    }

    @PostMapping("/delete")
    public ResponseVO deleteRole(@RequestParam String username){
        return roleService.deleteRoleByUsername(username);
    }

    @GetMapping("/get/all")
    public ResponseVO getAllManagerAndStaff(){
        return roleService.searchAllManagerAndStaff();
    }

    @GetMapping("/get/name/{username}")
    public ResponseVO getUserByUsername(@PathVariable String username){
        return roleService.searchUserByUserName(username);
    }

    @GetMapping("get/{userId}")
    public ResponseVO getUserById(@PathVariable int userId){
        return roleService.searchUserById(userId);
    }
    @PostMapping("/update")
    public ResponseVO updateUserType(@RequestParam int userId,@RequestParam int userType){
        return roleService.changeUserType(userId,userType);
    }
}
