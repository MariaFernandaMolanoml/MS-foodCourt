package com.example.msFoodCourt.domain.utils.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    //Roles names
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_OWNER = "PROPIETARIO";
    public static final String ROLE_EMPLOYEE = "EMPLEADO";
    public static final String ROLE_CLIENT = "CLIENTE";

    //JWT
    public  static  final String DOCUMENT = "document";
    public  static  final String EMAIL = "email";
    public  static  final String ROLE = "role";
    public  static  final String BIRTHDATE = "birthDate";
    public  static  final String AUTHORIZATION = "Authorization";
    public  static  final String BEARER = "Bearer ";
    public  static  final String INVALIDTOKEN = "Token inválido o expirado";
    public  static  final String NOTOKEN = "Falta token de autorización";

}

