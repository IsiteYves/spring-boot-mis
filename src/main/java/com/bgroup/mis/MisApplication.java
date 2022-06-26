package com.bgroup.mis;

import com.bgroup.mis.model.Department;
import com.bgroup.mis.model.Student;
import com.bgroup.mis.repository.DepartmentRepository;
import com.bgroup.mis.repository.StudentRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootApplication
@RestController

public class MisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MisApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, DepartmentRepository depRepo) {
        return args -> {
            depRepo.saveAll(List.of(new Department("Software Engineering"), new Department("Civil Engineering"), new Department("Welding")));
            Faker faker = new Faker();
            for (int i = 0; i < 50; i++) {
                String firstName = faker.name().firstName();
                String lastName = faker.name().lastName();
                String email = firstName + "." + lastName + "@gmail.com";
                int day = faker.number().numberBetween(10, 25);
                int month = faker.number().numberBetween(10, 12);
                int years = faker.number().numberBetween(2000, 2010);
                int dp = faker.number().numberBetween(1, 3);
                Department dpt = depRepo.findById((long) dp).get();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                String date = day + "/" + month + "/" + years;
                LocalDate dob = LocalDate.parse(date, formatter);
                Student student = new Student(firstName, lastName, email, dob, dpt);
                studentRepository.save(student);
            }
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
//			String date1 = "13-Mar-2002";
//			String date2 = "08-Jun-1988";
//			String date3 = "08-Jun-1988";
//			LocalDate localDate1 = LocalDate.parse(date1, formatter);
//			LocalDate localDate2 = LocalDate.parse(date2, formatter);
//			LocalDate localDate3 = LocalDate.parse(date3, formatter);
//
//			Student student1 = new Student(1L, "Jane", "Mutoni", "janemutoni@gmail.com", localDate1);
//			Student student2 = new Student("Yves", "Isite", "yvesisite@gmail.com", localDate2);
//			Student student3 = new Student("Mugabo", "Javis", "javis@mailbox.org", localDate3);
//
//			studentRepository.saveAll(List.of(student1, student2, student3));
//
//			System.out.print("The number of inserted students is: " + studentRepository.count() + "\n");
//
//			studentRepository.findAll().forEach(System.out::println);
//
//			System.out.println("\nFind a student by id:|| ");
//			studentRepository.findById(2L).ifPresent(System.out::println);
//
//			Student student4 = new Student(4L, "Paul", "Gasana", "pgasana@gmail.com", localDate2);
//			studentRepository.save(student4);
//			studentRepository.deleteById(3L);
//			System.out.println("RECORDS AFTER DELETING WITH STUDENT ID 2L: ");
//			List<Student> st=studentRepository.findAll();
//			for(Student stud:st) {
//				System.out.println(stud);
//			}
        };
    }
}
