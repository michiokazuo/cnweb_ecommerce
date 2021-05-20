package com.http.service_database;

import java.util.ArrayList;

public class ContentValues {

    private final ArrayList<Pair> mList;

    public ContentValues() {
        mList = new ArrayList<>();
    }

    public void put(String key, String value) { mList.add(new Pair(key, value)); }

    public void putAll(ContentValues other) { mList.addAll(other.mList); }

    public void clear() { mList.clear(); }

    public int size() { return mList.size(); }

    public boolean isEmpty() { return mList.isEmpty(); }

    public ArrayList<String> getValues() {
        ArrayList<String> rs = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            rs.add(mList.get(i).value);
        }

        return rs;
    }

    public String getValue(int index) {
        if (index < 0 || index >= size()) return null;
        return mList.get(index).value;
    }

    public String getKey(int index) {
        if (index < 0 || index >= size()) return null;
        return mList.get(index).key;
    }

    public String toUpdateSQL() {
        StringBuilder sb = new StringBuilder();
        int mSize = size();
        if (mSize <= 0) return "";
        if (mSize == 1) {
            sb.append(mList.get(0).key).append(" = ").append(" ? ");
            return sb.toString();
        }
        for (int i = 0; i < mSize - 1; i++) {
            sb.append(mList.get(i).key).append(" = ").append("?, ");
        }
        sb.append(mList.get(mSize - 1).key).append(" = ").append(" ? ");
        return sb.toString();
    }

    public String toColumnInsertSQL() {
        StringBuilder sb = new StringBuilder();
        int mSize = size();

        if (mSize <= 0) return "";
        if (mSize == 1) {
            sb.append(mList.get(0).key);
            return sb.toString();
        }

        for (int i = 0; i < mSize - 1; i++) {
            sb.append(mList.get(i).key).append(",");
        }
        sb.append(mList.get(mSize - 1).key);
        return sb.toString();
    }

    public String toValuesInsertSQL() {
        StringBuilder sb = new StringBuilder();
        int mSize = size();
        if (mSize <= 0) return "";
        if (mSize == 1) {
            sb.append("?");
            return sb.toString();
        }

        for (int i = 0; i < mSize - 1; i++) {
            sb.append("?,");
        }
        sb.append("?");
        return sb.toString();
    }

    private class Pair {
        String key;
        String value;

        Pair(String key, String value)  {
            this.key = key;
            this.value = value;
        }
    }
}
