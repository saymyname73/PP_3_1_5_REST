package ru.kata.spring.boot_security.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

    public static void main(String[] args) {

//        !!! таблицы создаются при запуске спринга !!!
//        !!! добавить первого юзера с любой ролью можно по ссылке http://localhost:8080/auth/reg !!!

//        -----------------------------------------------------------------------------------


        //тут создаём две таблицы для юзеров и ролей, роли уже добавлены
        //добавлять юзеров вручную через форму регистрации
        //у всех новых юзеров по умолчанию роли USER
        //для добавления новому юзеру роли ADMIN разкоменти 27 строку в классе RegService
        //перезапусти спринг и зарегестрируй нового пользователя


//        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_web", "root", "root");
//             Statement statement = connection.createStatement()) {
//
//            statement.execute("CREATE TABLE `my_web`.`users` (\n" +
//                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
//                    "  `name` VARCHAR(45) NULL DEFAULT NULL,\n" +
//                    "  `surname` VARCHAR(45) NULL DEFAULT NULL,\n" +
//                    "  `email` VARCHAR(45) NULL DEFAULT NULL,\n" +
//                    "  `age` INT NULL DEFAULT NULL,\n" +
//                    "  `password` VARCHAR(255) NULL DEFAULT NULL,\n" +
//                    "  PRIMARY KEY (`id`));;");
//
//            statement.execute("CREATE TABLE `my_web`.`roles` (\n" +
//                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
//                    "  `name` VARCHAR(45) NULL DEFAULT NULL,\n" +
//                    "  PRIMARY KEY (`id`));;");
//            statement.execute("INSERT INTO roles(name) VALUES ('ROLE_USER');");
//            statement.execute("INSERT INTO roles(name) VALUES ('ROLE_ADMIN');");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}
