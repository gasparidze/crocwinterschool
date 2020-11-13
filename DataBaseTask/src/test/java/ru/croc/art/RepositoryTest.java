package ru.croc.art;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import ru.croc.art.db.DataSourceProvider;
import ru.croc.art.model.Student;
import ru.croc.art.repository.StudentRepository;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс теста репозитория
 */
public class RepositoryTest {
    DataSourceProvider dataSourceProvider = null;
    @Before
    public void init() throws IOException {
        try {
            dataSourceProvider = new DataSourceProvider();
        } catch (IOException e) {
            System.out.println("Ошибка создания провайдера: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void repositoryTest() throws IOException {
        StudentRepository studentRepository = new StudentRepository(dataSourceProvider.getDataSource());
        Student arthur = new Student(1,"Arthur",4.5f,true,new Date(9191919191191L));
        Student maks = new Student(2, "Maks",4f,true, new Date(12121212121L));
        List<Student> students = new ArrayList<>();
        students.add(arthur);
        students.add(maks);
        //тест на добавление нового студента
        studentRepository.createNewStudent(arthur);
        studentRepository.createNewStudent(maks);
        Assertions.assertEquals(arthur.toString(),studentRepository.findStudent(1).toString());
        Assertions.assertEquals(maks.toString(),studentRepository.findStudent(2).toString());

        //тест на вывод всех студентов
        Assertions.assertEquals(students.toString(),studentRepository.allStudents().toString());

        //тест на обновление стдуента
        Student arthurChangedData = new Student(1,"Arthur",4.5f,true,
                new Date(5465498454654L));
        studentRepository.updateStudent(1,"startEducation", new Date(5465498454654L));
        Assertions.assertEquals(arthurChangedData.toString(), studentRepository.findStudent(1).toString());

        Student arthurChangedName = new Student(1,"Robert", 4.5f, true,
                new Date(5465498454654L));
        studentRepository.updateStudent(1,"name","Robert");
        Assertions.assertEquals(arthurChangedName.toString(),studentRepository.findStudent(1).toString());

        //тест на удаление студента
        students.remove(arthur);
        students.remove(maks);
        studentRepository.deleteStudent(1);
        studentRepository.deleteStudent(2);
        Assertions.assertEquals(students.toString(),studentRepository.allStudents().toString());
    }
}
