package com.example.javaehuweb.controller.command;

import com.example.javaehuweb.controller.command.impl.DefaultCommand;
import com.example.javaehuweb.controller.command.impl.LoginCommand;
import com.example.javaehuweb.controller.command.impl.LogoutCommand;
import com.example.javaehuweb.controller.command.impl.RegistrationCommand;

import java.util.Arrays;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand()),
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
