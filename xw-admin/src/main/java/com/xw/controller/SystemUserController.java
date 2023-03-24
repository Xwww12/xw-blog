package com.xw.controller;

import com.xw.domain.ResponseResult;
import com.xw.domain.dto.AddUserDto;
import com.xw.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/system/user")
public class SystemUserController {

    @Resource
    private UserService userService;

    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status) {
        return userService.pageList(pageNum, pageSize, userName, phonenumber, status);
    }

    @PostMapping()
    public ResponseResult addUser(@RequestBody AddUserDto userDto) {
        return userService.addUser(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public ResponseResult getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @PutMapping()
    public ResponseResult updateUser(@RequestBody AddUserDto userDto) {
        return userService.updateUser(userDto);
    }
}
