package ru.croc.art.db;

import org.apache.derby.jdbc.EmbeddedDataSource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * класс для доступа к БД
 */
public class DataSourceProvider {
    /**
     * DataSource.
     */
    private EmbeddedDataSource dataSource;

    /**
     * Параметры конфигурации из файла application.properties.
     */
    private Map<String, String> properties = new HashMap<String, String>();

    /**
     * Консутркутор.
     *
     * @throws IOException ошибка инициализации
     */
    public DataSourceProvider() throws Exception {
        loadProperties();
    }

    /**
     * Метод загрузки настроек конфигурации в память.
     *
     * @throws IOException ошибка получения настроек
     */
    private void loadProperties() throws Exception {
        Properties properties = new Properties();
        try {
            properties.load(
                    Thread.currentThread().getContextClassLoader().
                            getResourceAsStream("properties/db.properties"));
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                this.properties.put((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (Exception e) {
            System.out.println("Error occurred during loading properties");
            throw e;
        }
    }

    /**
     * Метод получения экземпляра DataSource'а.
     *
     * @return data source object
     */
    public EmbeddedDataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new EmbeddedDataSource();
            dataSource.setUser(properties.get("dbUserName"));
            dataSource.setPassword(properties.get("dbPass"));
            dataSource.setDatabaseName(properties.get("dbName"));
            dataSource.setCreateDatabase("create");
        }

        return dataSource;
    }
}
