import org.example.studentCrud.Student;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.*;

public class StudentTest {

    private static final String TEST_FILE_PATH = "test_students.json";


    @BeforeClass
    public static void setup() throws IOException {
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @After
    public void cleanup() {
        // Clean up after each test
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testCreateStudent() throws Exception {
        Student student = new Student();
        student.setStudentId(1);
        student.setName("John Doe");
        student.setMajor("Computer Science");
        student.setAge(20);


        Student.createStudent(TEST_FILE_PATH, student);

        // Verify the student was created
        Student retrievedStudent = Student.readStudentById(TEST_FILE_PATH, 1);
        assertNotNull(retrievedStudent);
        assertEquals("John Doe", retrievedStudent.getName());
        assertEquals("Computer Science", retrievedStudent.getMajor());
        assertEquals(20, retrievedStudent.getAge());
    }


    @Test
    public void testCreateStudent_AlreadyExists() throws Exception {
        Student student = new Student();
        student.setStudentId(1);
        student.setName("John Doe");
        student.setMajor("Computer Science");
        student.setAge(20);

        // Create a student
        Student.createStudent(TEST_FILE_PATH, student);

        // Try to create the same student again
        try {
            Student.createStudent(TEST_FILE_PATH, student);
            fail("Expected an Exception to be thrown");
        } catch (Exception e) {
            assertEquals("Student Already exists", e.getMessage());
        }
    }

    @Test
    public void testReadStudentById_NotFound() {
        try {
            Student.readStudentById(TEST_FILE_PATH, 1);
            fail("Expected an Exception to be thrown");
        } catch (Exception e) {
            assertEquals("Student Not Found", e.getMessage());
        }
    }

    @Test
    public void testUpdateStudent() throws Exception {
        Student student = new Student();
        student.setStudentId(1);
        student.setName("John Doe");
        student.setMajor("Computer Science");
        student.setAge(20);

        // Create a student
        Student.createStudent(TEST_FILE_PATH, student);

        // Update the student's details
        student.setName("Johnathan Doe");
        student.setAge(21);
        Student.updateStudent(TEST_FILE_PATH, student);

        // Verify the student was updated
        Student retrievedStudent = Student.readStudentById(TEST_FILE_PATH, 1);
        assertNotNull(retrievedStudent);
        assertEquals("Johnathan Doe", retrievedStudent.getName());
        assertEquals(21, retrievedStudent.getAge());
    }

    @Test
    public void testUpdateStudent_NotFound() {
        Student student = new Student();
        student.setStudentId(1);
        student.setName("John Doe");
        student.setMajor("Computer Science");
        student.setAge(20);

        try {
            Student.updateStudent(TEST_FILE_PATH, student);
            fail("Expected an Exception to be thrown");
        } catch (Exception e) {
            assertEquals("Student with id 1 does not exist", e.getMessage());
        }
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Student student = new Student();
        student.setStudentId(1);
        student.setName("John Doe");
        student.setMajor("Computer Science");
        student.setAge(20);

        // Create a student
        Student.createStudent(TEST_FILE_PATH, student);

        // Verify if student was created
        Student retrievedStudent = Student.readStudentById(TEST_FILE_PATH, 1);
        assertNotNull(retrievedStudent);
        assertEquals("Johnathan Doe", retrievedStudent.getName());

        // Delete the student
        Student.deleteStudent(TEST_FILE_PATH, 1);

        // Verify the student was deleted
        try {
            Student.readStudentById(TEST_FILE_PATH, 1);
            fail("Expected an Exception to be thrown");
        } catch (Exception e) {
            assertEquals("Student Not Found", e.getMessage());
        }
    }

    @Test
    public void testDeleteStudent_NotFound() {
        try {
            Student.deleteStudent(TEST_FILE_PATH, 1);
            fail("Expected an Exception to be thrown");
        } catch (Exception e) {
            assertEquals("Student does not exist", e.getMessage());
        }
    }

    @Test
    public void testReadStudents_EmptyFile() throws IOException {
        Map<Integer, Student> students = Student.readStudents(TEST_FILE_PATH);
        assertTrue(students.isEmpty());
    }

}