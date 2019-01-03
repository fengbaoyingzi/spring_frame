package com.example.controller;

import com.example.dao.StudentDao;
import com.example.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private StudentDao studentDao;

    @RequestMapping("/hello")
    public String hello() {
        return "hello spring";
    }

    @RequestMapping("/getStudent")
    public Student getStudent(String id){
        return studentDao.getStudent(id);
    }

    @RequestMapping("/addStudent")
    public int getStudent(Student student){
        return studentDao.addStudent(student);
    }
}
