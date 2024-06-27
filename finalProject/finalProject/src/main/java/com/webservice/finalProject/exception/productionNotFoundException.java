package com.webservice.finalProject.exception;


    public class productionNotFoundException extends RuntimeException {
        public productionNotFoundException(Long empId) {
            super("Employee Not Found ->" + empId);
        }
    }
