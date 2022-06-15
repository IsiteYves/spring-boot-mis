package com.bgroup.mis;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import com.bgroup.mis.model.Student;
import com.bgroup.mis.repository.StudentRepository;

@SpringBootApplication
@RestController

public class MisApplication {

	public static void main(String[] args) {
		SpringApplication.run(MisApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
			String date1 = "13-Mar-2002";
			String date2 = "08-Jun-1988";
			String date3 = "08-Jun-1988";
			LocalDate localDate1 = LocalDate.parse(date1, formatter);
			LocalDate localDate2 = LocalDate.parse(date2, formatter);
			LocalDate localDate3 = LocalDate.parse(date3, formatter);

			Student student1 = new Student(1L, "Jane", "Mutoni", "janemutoni@gmail.com", localDate1);
			Student student2 = new Student("Yves", "Isite", "yvesisite@gmail.com", localDate2);
			Student student3 = new Student("Mugabo", "Javis", "javis@mailbox.org", localDate3);

			studentRepository.saveAll(List.of(student1, student2, student3));

			System.out.print("The number of inserted students is: " + studentRepository.count() + "\n");

			studentRepository.findAll().forEach(System.out::println);
			
			System.out.println("\nFind a student by id:|| ");
			studentRepository.findById(1L).ifPresent(System.out::println);
			
			Student student4 = new Student(3L, "Paul", "Gasana", "pgasana@gmail.com", localDate2);
			studentRepository.save(student4);
			studentRepository.deleteById(2L);
			System.out.println("AFTER DELETING WITH STUDENT ID 2L");
			List<Student> st=studentRepository.findAll();
			for(Student stud:st) {
				System.out.println(stud);
			}
		};
	}

//	@GetMapping
//	public static String helloWorld() {
//		return "Welcome to Springboot!";
//	}
//	
//	@GetMapping("api/routes/1")
//	public static String firstRoute() {
//		return "This is the first route!";
//	}

}
