package org.example;

import java.sql.*;

public class DatabaseManager {
    private static final String DEFAULT_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String BASE_URL = "jdbc:postgresql://localhost:5432/";
    private static final String DBNAME = "consoleheroes";
    private static final String USER = "consoleheroes";
    private static final String PASSWORD = "password";

    private static Connection conn;

    private static boolean databaseExists() {
        String sql = "SELECT 1 FROM pg_database WHERE datname = ?";
        try (Connection tempConn = DriverManager.getConnection(DEFAULT_URL, USER, PASSWORD);
             PreparedStatement pstmt = tempConn.prepareStatement(sql)) {

            pstmt.setString(1, DBNAME);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("❌ Ошибка при проверке базы данных: " + e.getMessage());
            return false;
        }
    }

    public static void initializeDatabase() {
        if (!databaseExists()) {
            String sql = "CREATE DATABASE " + DBNAME + " WITH ENCODING 'UTF8' OWNER " + USER;
            try (Connection tempConn = DriverManager.getConnection(DEFAULT_URL, USER, PASSWORD);
                 Statement stmt = tempConn.createStatement()) {

                stmt.executeUpdate(sql);
                System.out.println("✅ База данных '" + DBNAME + "' успешно создана!");
            } catch (SQLException e) {
                System.err.println("❌ Ошибка при создании базы данных: " + e.getMessage());
                return;
            }
        }

        try {
            conn = DriverManager.getConnection(BASE_URL + DBNAME, USER, PASSWORD);
            System.out.println("🔗 Подключено к базе '" + DBNAME + "'.");
        } catch (SQLException e) {
            System.err.println("❌ Ошибка при подключении к базе: " + e.getMessage());
        }
    }

    public static void initializeTables() {
        String sql = "CREATE TABLE IF NOT EXISTS player_data (" +
                "file_name TEXT PRIMARY KEY, " +
                "player_name TEXT NOT NULL, " +
                "player_class TEXT NOT NULL, " +
                "health INTEGER NOT NULL, " +
                "location_id INTEGER NOT NULL, " +
                "save_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("✅ Таблица 'player_data' готова!");
        } catch (SQLException e) {
            System.err.println("❌ Ошибка при создании таблицы: " + e.getMessage());
        }
    }


    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("🔌 Соединение с базой закрыто.");
            }
        } catch (SQLException e) {
            System.err.println("⚠️ Ошибка при закрытии соединения: " + e.getMessage());
        }
    }

    public static void saveProgress(SaveData saveData, String fileName) {
        String sql = "INSERT INTO player_data (file_name, player_name, player_class, health, location_id, save_time) " +
                "VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP) " +
                "ON CONFLICT (file_name) DO UPDATE SET " +
                "player_name = EXCLUDED.player_name, " +
                "player_class = EXCLUDED.player_class, " +
                "health = EXCLUDED.health, " +
                "location_id = EXCLUDED.location_id, " +
                "save_time = CURRENT_TIMESTAMP";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fileName);
            pstmt.setString(2, saveData.getPlayerName());
            pstmt.setString(3, saveData.getPlayerClass());
            pstmt.setInt(4, saveData.getHealth());
            pstmt.setInt(5, saveData.getLocationId());

            pstmt.executeUpdate();
            System.out.println("✅ Прогресс сохранён: " + fileName);
        } catch (SQLException e) {
            System.err.println("❌ Ошибка при сохранении прогресса: " + e.getMessage());
        }
    }

    public static SaveData loadSaveData(String fileName) {
        String sql = "SELECT player_name, player_class, health, location_id FROM player_data WHERE file_name = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fileName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String playerName = rs.getString("player_name");
                String playerClass = rs.getString("player_class");
                int health = rs.getInt("health");
                int locationId = rs.getInt("location_id");

                Hero hero;
                switch (playerClass.toLowerCase()) {
                    case "warrior":
                        hero = new Warrior(playerName);
                        break;
                    case "mage":
                        hero = new Mage(playerName);
                        break;
                    case "archer":
                        hero = new Archer(playerName);
                        break;
                    default:
                        throw new IllegalArgumentException("❌ Неизвестный класс героя: " + playerClass);
                }

                hero.setHealth(health);
                return new SaveData(hero, locationId);
            } else {
                System.out.println("⚠️ Сохранение не найдено: " + fileName);
            }
        } catch (SQLException e) {
            System.err.println("❌ Ошибка при загрузке сохранения: " + e.getMessage());
        }

        return null;
    }

    public static void getAvailableSaveSlots() {
        String sql = "SELECT file_name, player_name, save_time FROM player_data ORDER BY save_time DESC LIMIT 10";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("📌 Последние 10 сохранений:");
            while (rs.next()) {
                System.out.println("Файл: " + rs.getString("file_name") +
                        " | Игрок: " + rs.getString("player_name") +
                        " | Дата: " + rs.getTimestamp("save_time"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Ошибка при получении списка сохранений: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        initializeDatabase();
        initializeTables();

        SaveData saveData = new SaveData(new Warrior("Hero1"), 1);
        saveProgress(saveData, "save_hero1");

        loadSaveData("save_hero1");

        getAvailableSaveSlots();

        closeConnection();
    }
}
