package ru.croc.art.Repository;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.croc.art.model.Row;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для доступа к таблице с данными о статистике заболеваний (Statistics)
 */
public class Repository {
    private static final String TABLE_NAME = "statistics";

    private EmbeddedDataSource dataSource;

    public Repository(EmbeddedDataSource dataSource) {
        this.dataSource = dataSource;
        initTable();
    }

    /**
     * Метод инициализации БД.
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
                                + "date DATE,"
                                + "diseaseQuantity INTEGER, "
                                + "recoveryQuantity INTEGER, "
                                + "dischargedQuantity INTEGER "
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
     * Метод поиска всех записей в БД.
     *
     * @return список всех созданных записей
     */
    public List<Row> allRows() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            List<Row> rows = new ArrayList<>();
            while (resultSet.next()) {
                rows.add(new Row(resultSet.getInt("id"),
                        resultSet.getDate("date"),
                        resultSet.getInt("diseaseQuantity"),
                        resultSet.getInt("recoveryQuantity"),
                        resultSet.getInt("dischargedQuantity")));
            }
            return rows;
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * класс, возвращающий список всех id
     *
     * @return List<Integer> -список всех id
     */
    private List<Integer> getIdList() {
        List<Integer> list;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(resultSet.getInt("id"));
            }
            return list;
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Метод создания записей в БД
     *
     * @param rows распарсенные данные
     */
    public void createNewRows(List<Row> rows) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            //хранит список всех id в БД
            List<Integer> idList = getIdList();
            for (Row row : rows) {
                //если такой записи с данным id еще нет, то добавляем
                //т.к. при при кол-во запусков программы n>1
                //возникает ошибка о попытке добавить запись с уже существующем id
                if (!idList.contains(row.getId())) {
                    statement.setString(1, row.getId().toString());
                    statement.setString(2, row.getDate().toString());
                    statement.setString(3, row.getDiseaseQuantity().toString());
                    statement.setString(4, row.getRecoveryQuantity().toString());
                    statement.setString(5, row.getDischargedQuantity().toString());
                    statement.execute();
                    System.out.println("1 row inserted");
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }
}
