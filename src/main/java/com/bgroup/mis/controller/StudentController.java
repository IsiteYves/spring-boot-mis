package com.bgroup.mis.controller;

import com.bgroup.mis.model.Student;
import com.bgroup.mis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/students")
@CrossOrigin("http://localhost:8000")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Student> listAllStudents() {
        return studentService.listStudents();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void addNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @GetMapping("{studentid}")
    @ResponseStatus(HttpStatus.OK)
    public Student findStudentById(@PathVariable("studentid") Long id) {
        Student s = studentService.getStudentById(id);
        if (s != null) {
            return s;
        } else {
            return null;
        }
    }

    @PutMapping("{studentid}")
    @ResponseStatus(HttpStatus.OK)
    public void updateStudent(@RequestBody Student student, @PathVariable("studentid") Long id) {
        Student s = studentService.getStudentById(id);
        s.setFirstName(student.getFirstName());
        s.setLastName(student.getLastName());
        studentService.addNewStudent(s);
    }

    @DeleteMapping("{studentid}")
    public void deleteStudent(@PathVariable("studentid") Long id) {
        studentService.deleteStudentById(id);
    }

    @GetMapping("finder")
    public Optional<Student> getStudentByEmail(@RequestParam String email) {
        return studentService.getStudentByEmail(email);
    }

    @GetMapping("pages")
    @ResponseStatus(HttpStatus.OK)
    public Page<Student> getPages(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        return studentService.getAllStudent(pageNo, pageSize, sortBy);
    }
}
