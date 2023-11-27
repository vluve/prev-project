package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Util util = new Util();
        UserServiceImpl userService;
        userService  = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("vlu", "vkuve", (byte) 9);
        userService.getAllUsers().stream().forEach(System.out::println);
    }
}
