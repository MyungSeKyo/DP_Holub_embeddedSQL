package com.holub.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLImporter implements Table.Importer{
    private final ArrayList<String> columnNames = new ArrayList<>();
    private String tableName = "anonymous";
    private final BufferedReader in;

    public XMLImporter(Reader in) {
        this.in = in instanceof BufferedReader ? (BufferedReader)in : new BufferedReader(in);
    }
    public void startTable() throws IOException {
        in.readLine(); // Drop xml header
        // Parse table name
        Pattern p = Pattern.compile("<(.+?)>");
        Matcher m = p.matcher(in.readLine());
        if(m.find()) {
            tableName =  m.group(1);
        }
        // Parse column names
        p = Pattern.compile("<item>(.+?)</item>");
        m = p.matcher(in.readLine());
        while(m.find()){
            columnNames.add(m.group(1));
        }
    }
    public String loadTableName() throws IOException {
        return tableName;
    }
    public int loadWidth() throws IOException {
        return columnNames.size();
    }
    public Iterator loadColumnNames() throws IOException {
        return columnNames.iterator();
    }
    public Iterator loadRow() throws IOException {
        String row = in.readLine();
        ArrayList<String> values = new ArrayList<>();
        for (String columnName : columnNames) {
            Pattern p = Pattern.compile(String.format("<%s>(.+?)</%s>", columnName, columnName));
            Matcher m = p.matcher(row);
            if (m.find()) {
                values.add(m.group(1));
            }
        }
        if(values.size() == loadWidth()){
            return values.iterator();
        }
        return null;
    }
    public void endTable() throws IOException {}
}
