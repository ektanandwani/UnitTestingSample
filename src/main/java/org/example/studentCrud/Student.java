package org.example.studentCrud;

import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.stream.Collectors;

public class Student {

    @JsonProperty("studentId")
    private int studentId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("major")
    private String major;

    @JsonProperty("age")
    private int age;

    public Student() {

    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", major='" + major + '\'' +
                '}';
    }

    public Student(int studentId, String name, String major, int age) {
        this.studentId = studentId;
        this.name = name;
        this.major = major;
        this.age = age;
    }

    public static void updateStudent(String filePath, Student updatedStudent) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<Integer, Student> students = readStudents(filePath);
        if(!students.containsKey(updatedStudent.getStudentId())) {
            throw new Exception("Student with id " + updatedStudent.getStudentId() + " does not exist");
        }
        students.put(updatedStudent.getStudentId(), updatedStudent);
        mapper.writeValue(new File(filePath), students);
    }


    public static void deleteStudent(String filePath, int studentId) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<Integer, Student> students = readStudents(filePath);
        if(students.containsKey(studentId)) {
            students.remove(studentId);
            mapper.writeValue(new File(filePath), students);
        } else {
            throw new Exception("Student does not exist");
        }
    }

    public static void createStudent(String filePath, Student student) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<Integer, Student> students = readStudents(filePath);
        if(students.containsKey(student.getStudentId())) {
            throw new Exception("Student Already exists");
        }
        students.put(student.getStudentId(), student);
        mapper.writeValue(new File(filePath), students);
    }

    public static Student readStudentById(String filePath, int studentId) throws Exception {
        Map<Integer, Student> students = readStudents(filePath);
        Student student = students.get(studentId);
        if(Objects.nonNull(student))
            return student;
        else
            throw new Exception("Student Not Found");
    }

    public static Map<Integer, Student> readStudents(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filePath);
        if (file.exists()) {
            return mapper.readValue(file, new TypeReference<Map<Integer, Student>>(){});
        } else {
            return new HashMap<>();
        }
    }

}
