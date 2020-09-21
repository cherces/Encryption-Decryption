package com.company;

enum Arguments {
    ALGORITHM(null, "-alg"),
    MODE(null, "-mode"),
    DATA(null, "-data"),
    KEY(null, "-key"),
    IN(null, "-in"),
    OUT(null, "-out");

    private String value;
    private String operator;

    Arguments(String value, String operator) {
        this.value = value;
        this.operator = operator;
    }
    public String getValue() {
        return value;
    }
    public String getOperator() {
        return operator;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean notNull() {
        if (this.value != null)
            return true;
        return false;
    }
}
