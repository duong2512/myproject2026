package com.myproject.global.util;

import org.springframework.core.env.Environment;

public class PropertiesEx {
    public static final Environment ENV = (Environment) BeanUtils.getBean(Environment.class);
    public static final String APP_CODE;
    public static final String API_URL_FDS;
    public static final String API_KEY_FDS;
    public static final String API_URL_FILE;
    public static final String API_KEY_FILE;
    public static final String API_ACTION_CODE_FILE;

    private PropertiesEx() {
    }

    static {
        APP_CODE = ENV.getProperty("app.current-app.app-code");
        API_URL_FDS = ENV.getProperty("app.restapi.api-url-fds");
        API_KEY_FDS = ENV.getProperty("app.restapi.api-key-fds");
        API_URL_FILE = ENV.getProperty("app.restapi.api-url-file");
        API_KEY_FILE = ENV.getProperty("app.restapi.api-key-file");
        API_ACTION_CODE_FILE = ENV.getProperty("app.restapi.api-action-code-file");
    }
}