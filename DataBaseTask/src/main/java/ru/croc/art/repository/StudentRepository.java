package ru.croc.art.repository;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.croc.art.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для доступа к таблице с данными о студентах (Student)
 */
public class StudentRepository {
    private static final String TABLE_NAME = "student";

    private EmbeddedDataSource dataSource;

    public StudentRepository(EmbeddedDataSource dataSource) {
        this.dataSource = dataSource;
        initTable();
    }
    /**
     * Метод инициализации БД.
     *
     */
    private void initTable() {
        System.out.println(String.format("Start initializing %s table", TABLE_NAME));
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            DatabaseMetaData databaseMetadata = connection.getMetaData();
            ResultSet resultSet = databaseMetadata.getTables(
                    null,
                    null,
                    TABLE_NAME.toUpperCase(),
                    new String[]{"TABLE"});
            if (resultSet.next()) {
                System.out.println("Table has already been initialized");
            } else {
                statement.executeUpdate(
                        "CREATE TABLE "
                                + TABLE_NAME
                                + " ("
                                + "id INTEGER PRIMARY KEY, "
                                + "name VARCHAR(15), "
                                + "averageMark FLOAT, "
                                + "closedSession BOOLEAN, "
                                + "startEducation DATE"
                                + ")");
                System.out.println("Table was successfully initialized");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred during table initializing: " + e.getMessage());
        } finally {
            System.out.println("=========================");
        }
    }

    /**
     * Метод поиска всех студентов в БД.
     *
     * @return список всех созданных студентов
     */
    public List<Student> allStudents() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            List<Student> studentList = new ArrayList<>();
            while (resultSet.next()) {
                studentList.add(new Student(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("averageMark"),
                        resultSet.getBoolean("closedSession"),
                        resultSet.getDate("startEducation")));
            }
            return studentList;
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Метод поиска студента по id
     * @param id - индивидуальный идентификатор студента
     * @return студента с выбранным id
     */
    public Student findStudent(Integer id) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1,id.toString());
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            Student student = null;
            while (resultSet.next()) {
                student = new Student(resultSet.getInt("id"),
                                            resultSet.getString("name"),
                                            resultSet.getFloat("averageMark"),
                                            resultSet.getBoolean("closedSession"),
                                            resultSet.getDate("startEducation"));
            }
            return student;
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return new Student();
    }


    /**
     * Метод создания записи в БД о новом студенте.
     *
     * @param student студент
     */
    public void createNewStudent(Student student) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, student.getId().toString());
            statement.setString(2, student.getName());
            statement.setString(3, student.getAverageMark().toString());
            statement.setString(4, student.isClosedSession().toString());
            statement.setString(5, student.getStartEducation().toString());
            statement.execute();
            System.out.println("1 row inserted");
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }

    /**
     * Метод обновления полей стдудента в БД .
     * @param id - идентификатор студента
     * @param columnName - атрибут, значение которого нужно изменить
     * @param object - значение выбранного атрибута
     */
    public void updateStudent(Integer id, String columnName, Object object) {
        String sqlQuery = "UPDATE " + TABLE_NAME + " SET " + columnName + " = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, object.toString());
            statement.setString(2, id.toString());
            statement.execute();
            System.out.println("1 row updated");
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }

    /**
     * Метод удаления студента по id
     * @param id - идентификатор студента
     */
    public void deleteStudent(Integer id) {
        String sqlQuery = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, id.toString());
            statement.execute();
            System.out.println("1 row deleted");
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }
}
