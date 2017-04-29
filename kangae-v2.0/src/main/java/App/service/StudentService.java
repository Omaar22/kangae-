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

    public void incrementScore(long id) {
        Student s = studentRepository.findOne(id);
        s.incrementScore();
        studentRepository.save(s);
    }

}
