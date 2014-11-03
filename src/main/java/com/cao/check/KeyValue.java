package com.cao.check;

public  class KeyValue {
    private String  key;
    private Integer num;
    private Double  value;
    private Double  sumValue;


    KeyValue(String key, Integer num, Double sumValue) {
        this.key = key;
        this.num = num;
        this.value = sumValue / num;
        this.sumValue = sumValue;
    }

    public String getKey() {
        return key;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getValue() {
        return value;
    }

    public Double getSumValue() {
        return sumValue;
    }
}
