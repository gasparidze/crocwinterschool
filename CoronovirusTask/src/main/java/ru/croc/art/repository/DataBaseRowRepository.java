package ru.croc.art.repository;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.croc.art.model.DataBaseRow;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для доступа к таблице с данными о статистике заболеваний (StatisticsDTO)
 */
public class DataBaseRowRepository {

    private final String TABLE_NAME;

    private EmbeddedDataSource dataSource;

    public DataBaseRowRepository(EmbeddedDataSource dataSource) {
        TABLE_NAME = DataBaseRow.getTableName();
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
                                + ")"
                );
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
    public List<DataBaseRow> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            List<DataBaseRow> dataBaseRows = new ArrayList<>();
            while (resultSet.next()) {
                dataBaseRows.add(new DataBaseRow(resultSet.getInt("id"),
                        resultSet.getDate("date"),
                        resultSet.getInt("diseaseQuantity"),
                        resultSet.getInt("recoveryQuantity"),
                        resultSet.getInt("dischargedQuantity")));
            }
            return dataBaseRows;
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Метод создания записей в БД
     *
     * @param dataBaseRows распарсенные данные
     */
    public void save(List<DataBaseRow> dataBaseRows) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            //хранит список всех id в БД
            for (DataBaseRow dataBaseRow : dataBaseRows) {

                    statement.setString(1, dataBaseRow.getId().toString());
                    statement.setString(2, dataBaseRow.getDate().toString());
                    statement.setString(3, dataBaseRow.getDiseaseQuantity().toString());
                    statement.setString(4, dataBaseRow.getRecoveryQuantity().toString());
                    statement.setString(5, dataBaseRow.getDischargedQuantity().toString());
                    statement.execute();

            }
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }

    /**
     * *
     * Метод удаления всех записей из таблицы.
     *
     * */
    public void deleteAll() {
        String sqlQuery = "DELETE FROM " + TABLE_NAME;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.execute();
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса deleteAll locationMarks: " + e.getMessage());
        }
    }
}
