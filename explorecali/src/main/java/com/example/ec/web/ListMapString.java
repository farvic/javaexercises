package com.example.ec.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListMapString {
    private List<Map<String, String>> listMapString;

    public List<Map<String, String>> getListMapString() {
        return listMapString;
    }

    public void setListMapString(List<Map<String, String>> listMapString) {
        this.listMapString = listMapString;
    }

    public int size() {
        return listMapString.size();
    }

    private ListMapString() {
        listMapString = new ArrayList<Map<String, String>>();
    }
}