package com.example.controller;

import com.example.dao.StudentDao;
import com.example.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

@Controller
@ResponseBody
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private StudentDao studentDao;

    @RequestMapping(value = { "hello" } ,produces = "text/plain; charset=utf-8")
    public String hello(String a,String c,int b,String dd) {
        System.out.println("11111");
        return a+"|"+c+"|"+b+"|"+dd;
    }
    @RequestMapping(value = { "hello2" } ,produces = "text/plain; charset=utf-8")
    public String hello(String a,String c,int b) {
        System.out.println("11111");
        return a+"|"+c+"|"+b+"|";
    }

    @RequestMapping("/getStudent")
    public Student getStudent(String id){
        return studentDao.getStudent(id);
    }

    @RequestMapping("/addStudent")
    public int getStudent(Student student){
        return studentDao.addStudent(student);
    }

    @RequestMapping(value = { "writeText" } ,produces = "text/plain; charset=utf-8")
    public String writeText(String path) {
        String pageName="D:\\TMP\\haha.txt";
        return getPageSource(pageName,"UTF-8");
    }

    public String getPageSource(String pageName,String encoding) {
        StringBuffer sb = new StringBuffer();
        try {
            File file = new File(pageName);
            //使用openStream得到一输入流并由此构造一个BufferedReader对象
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
            String line;
            //读取www资源
            while ((line = in.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            in.close();
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return sb.toString();
    }
}
