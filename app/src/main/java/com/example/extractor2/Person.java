package com.example.extractor2;

public class Person {
    private String random_id;
    private String ans;
    private String json_data;

    public Person(String random_id, String ans, String json_data) {
        this.random_id = random_id;
        this.ans = ans;
        this.json_data = json_data;
    }

    public String getRandom_id() {
        return random_id;
    }

    public void setRandom_id(String random_id) {
        this.random_id = random_id;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getJson_data() {
        return json_data;
    }

    public void setJson_data(String json_data) {
        this.json_data = json_data;
    }
}
