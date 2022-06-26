package com.bgroup.mis.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity(name = "pupil")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fname", nullable = false, length = 110)
    private String firstName;
    @Column(name = "lname", nullable = false, length = 100)
    private String lastName;
    @Column(unique = true, length = 120)
    private String email;
    @ManyToOne
    @JoinColumn(name="depId")
    private Department department;
    @Column(nullable = false)
    private LocalDate dob;
    @Transient
    private int age;
    public Student() {

    }
    public Student(Long id, String firstName, String email, LocalDate dob, int age) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.email = email;
        this.dob = dob;
        this.age = age;
    }

    public Student(Long id, String firstName, String lastName, String email, LocalDate dob) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
    }

    public Student(String firstName, String lastName, String email, LocalDate dob,Department department) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.department = department;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getAge() {
        if (this.dob != null)
            return (Period.between(this.dob, LocalDate.now())).getYears();
        return 0;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", dob=" + dob + ", age=" + this.getAge() + "]";
    }

}
