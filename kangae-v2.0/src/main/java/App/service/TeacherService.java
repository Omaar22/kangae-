package App.service;

import App.model.Teacher;
import App.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    public Iterable<Teacher> getAll() {
        return teacherRepository.findAll();
    }
}
