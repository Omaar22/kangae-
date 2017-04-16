package App.service;

import App.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import App.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public Iterable<Student> getAll() {
        return studentRepository.findAll();
    }
}
