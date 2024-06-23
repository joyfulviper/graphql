package com.asd.asdfasdfasdfa.global;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class Hello {

    private final ServletWebServerApplicationContext webServerAppCtxt;

    @ShellMethod(key = "hello", value = "This is echo your message")
    public String hello(@ShellOption(defaultValue = "world") String message) {
        return "Hello, " + message + "!";
    }

    @ShellMethod(key = "java-version", value = "echo version")
    public String version() {
        return System.getProperty("java.version");
    }

    @ShellMethod(key = "port", value = "echo port")
    public String port() {
        return String.valueOf(webServerAppCtxt.getWebServer().getPort());
    }

    @ShellMethod(key = "pid", value = "echo pid")
    public String pid() {
        return String.valueOf(ProcessHandle.current().pid());
    }
}
