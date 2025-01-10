package com.example.javaehuweb.controller.command;

import com.example.javaehuweb.controller.command.impl.*;

import java.util.Arrays;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand()),
    REGISTERCOFFEESHOP(new RegisterCoffeeshopCommand()),
    LISTCOFFEESHOPS(new ListCoffeeshopsCommand()),
    DEFAULT(new DefaultCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public static Command defineCommand(String command) {
        return Arrays.stream(CommandType.values())
                .filter(type -> type.name().equalsIgnoreCase(command))
                .findFirst()
                .orElse(CommandType.DEFAULT)
                .getCommand();
    }
}
