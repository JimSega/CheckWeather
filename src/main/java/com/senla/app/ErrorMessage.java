package com.senla.app;

public enum ErrorMessage {
    NOT_CONTAIN_RECORDS("The database does not contain records (records for the required period)"),
    WRONG_FORMAT("Wrong format date!(YYYY-MM-dd)"),
    WRONG_PERIOD("Wrong period!");
    final String error;
    ErrorMessage(String error) {
        this.error = error;
    }
    @Override
    public String toString() {
        return this.error;
    }
}
