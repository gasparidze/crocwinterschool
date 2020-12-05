package ru.croc.art.repository;

import org.junit.jupiter.api.*;
import ru.croc.art.db.DataSourceProvider;
import ru.croc.art.model.DataBaseRow;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DisplayName("Тест на проверку методов репозитория")
public class DataBaseRowRepositoryTest {
    /**
     * подлкюченик к БД
     */
    private DataSourceProvider dataSourceProvider = null;
    /**
     * репозиторий для работы с БД
     */
    private DataBaseRowRepository repository;

    private List<DataBaseRow> rowsExpected;
    private DataBaseRow dataBaseRow1;
    private DataBaseRow dataBaseRow2;
    private DataBaseRow dataBaseRow3;

    @BeforeEach
    void init() throws IOException {

        dataBaseRow1 = new DataBaseRow(1, Date.valueOf("2020-11-23"),10,7,5);
        dataBaseRow2 = new DataBaseRow(2, Date.valueOf("2020-11-24"),9,8,2);
        dataBaseRow3 = new DataBaseRow(3, Date.valueOf("2020-11-25"),8,4,1);
        rowsExpected = new ArrayList<>(Arrays.asList(dataBaseRow1, dataBaseRow2, dataBaseRow3));

        try {
            dataSourceProvider = new DataSourceProvider();
        } catch (IOException e) {
            System.out.println("Ошибка создания провайдера: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository = new DataBaseRowRepository(dataSourceProvider.getDataSource());
        repository.deleteAll();
    }

    @DisplayName("Проверка добавления записей в БД")
    @Test
    public void testFindAll(){
        repository.save(rowsExpected);
        List<DataBaseRow> rowsActual = repository.findAll();
        Assertions.assertEquals(rowsExpected, rowsActual);
    }

    @DisplayName("Проверка добавления записей в БД")
    @Test
    public void testSave(){
        repository.save(rowsExpected);
        DataBaseRow row = repository.findAll().get(0);
        Assertions.assertEquals(row, dataBaseRow1);
    }

    @AfterEach
    @DisplayName("Очистка объектов")
    public void cleaning(){
        dataBaseRow1 = null;
        dataBaseRow2 = null;
        dataBaseRow3 = null;
        rowsExpected = null;
    }
}
