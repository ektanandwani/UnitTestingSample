package org.example.studentCrud;


import java.io.IOException;
import java.util.Map;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        final String filePath = "C:\\Users\\Ekta Nandwani\\IdeaProjects\\UnitTestingSample\\src\\main\\resources\\students.json";
        try {

            // create student
            Student s = new Student(10, "Bruce Lee", "Marial Arts", 54);
            Student.createStudent(filePath, s);

            // read student
            Student x = Student.readStudentById(filePath, 2);
            System.out.println(x);

            // update student
            x.setName("Sania Mirza");
            Student.updateStudent(filePath, x);
            System.out.println(x);

            // delete student
            Student.deleteStudent(filePath, 1);
            Map<Integer, Student> studentList = Student.readStudents(filePath);

            System.out.println(studentList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}