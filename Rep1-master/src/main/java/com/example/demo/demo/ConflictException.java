package com.example.demo.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

class ConflictException extends ResponseStatusException {
        public ConflictException() {
            super(HttpStatus.CONFLICT, "Conflict");
        }
    }
