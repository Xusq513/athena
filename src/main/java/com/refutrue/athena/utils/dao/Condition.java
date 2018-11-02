package com.refutrue.athena.utils.dao;

/**
 * @Auther: Michael Xu
 * @Date: 2018/11/2 11:33
 * @Description:
 */
public class Condition {

    private String fieldName;

    private String operation;

    private String fieldValue;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }
}
