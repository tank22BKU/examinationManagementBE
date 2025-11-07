package com.examManagementBE.common.constants;

public class EndpointConstants {
    public static final String ACTUATOR = "/actuator";
    public static final String SWAGGER_ICO = "/favicon.ico";
    public static final String SWAGGER_UI = "/swagger-ui";
    public static final String SWAGGER_VER = "/v3";
    public static final String SWAGGER_API_DOCS = SWAGGER_VER + "/api-docs";
    public static final String SWAGGER_CONFIG = "/swagger-config";
    public static final String API = "/api";
    public static final String ADMIN = "/admin";
    public static final String ID = "/{id}";
    public static final String DETAILS = "/details";
    public static final String STUDENT = "/student";
    public static final String ALL_STUDENTS = "/students";

    // 🔹 Auth Endpoints
    public static final String AUTH = "/auth";
    public static final String SIGN_IN = "/sign-in";
    public static final String SIGN_UP = "/sign-up";
    public static final String CHANGE_PASSWORD = "/change-password";
    public static final String ACTIVE = "/activate/{id}";

    // Test Endpoints
    public static final String TEST = "/test";
    public static final String ALL_TEST = "/tests";
    public static final String HISTORY = "/history";
}
