package com.srscons.aydin.controller;

import com.srscons.aydin.entity.Student;
import com.srscons.aydin.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*") // разрешает все источники !!luboy domennen zapros gele biler
@RestController
@RequestMapping("/students")
public class StudentController {

       private final StudentRepository studentRepository;

       public StudentController(StudentRepository studentRepository) {
              this.studentRepository = studentRepository;
       }

       // CREATE
       @PostMapping
       public ResponseEntity<Student> createStudent(@RequestBody Student student) {
              Student savedStudent = studentRepository.save(student);
              return ResponseEntity.ok(savedStudent);
       }

       // READ ALL
       @GetMapping
       public ResponseEntity<List<Student>> getAllStudents() {
              return ResponseEntity.ok(studentRepository.findAll());
       }

       // READ ONE
       @GetMapping("/{id}")
       public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
              Optional<Student> student = studentRepository.findById(id);
              return student.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
       }

       @GetMapping("/search")
       public ResponseEntity<List<Student>> searchByName(@RequestParam String name) {
              List<Student> students = studentRepository.findByNameContainingIgnoreCase(name);
              return ResponseEntity.ok(students);
       }


       // UPDATE
       @PutMapping("/{id}")
       public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
              return studentRepository.findById(id)
                      .map(student -> {
                             student.setName(updatedStudent.getName());
                             student.setSurname(updatedStudent.getSurname());
                             student.setEmail(updatedStudent.getEmail());
                             student.setAge(updatedStudent.getAge());
                             student.setUniversity(updatedStudent.getUniversity());
                             studentRepository.save(student);
                             return ResponseEntity.ok(student);
                      })
                      .orElse(ResponseEntity.notFound().build());
       }

       // DELETE
       @DeleteMapping("/{id}")
       public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
              if (studentRepository.existsById(id)) {
                     studentRepository.deleteById(id);
                     return ResponseEntity.noContent().build();
              } else {
                     return ResponseEntity.notFound().build();
              }
       }
}
