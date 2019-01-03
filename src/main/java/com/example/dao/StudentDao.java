package com.example.dao;

import com.example.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Student getStudent(String id) {
        String sql="select * from student where id=?";
        Object []params=new Object[]{id};
        //传入两个参数
        List<Student> list =  jdbcTemplate.query(sql,new StudentRowMpper(),params);
        return list.get(0);
    }

    public int addStudent(Student student) {
        String sql="insert into student values(?,?,?)";
        //因为Spring的jdbc对原生jdbc进行了封装，在这里给出的是数组方式，当然其底层也是需要将数组进行遍历
        Object []params=new Object[]{student.getId(),student.getName(),student.getAge()};
        return jdbcTemplate.update(sql,params);
    }

    public class StudentRowMpper implements RowMapper{

        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Student student = new Student();
            student.setId(resultSet.getString("id"));
            student.setAge(resultSet.getInt("age"));
            student.setName(resultSet.getString("name"));
            return student;
        }
    }
}
