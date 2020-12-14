package com.holub.database;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;

public class XMLExporter implements Table.Exporter {
    private final Writer out;
    private String tableName;
    private ArrayList<String> columnNames = new ArrayList<String>();

    public XMLExporter( Writer out ) {
        this.out = out;
    }
    public void startTable() throws IOException {
        out.write("<?xml version=\"1.0\"?>\n");
    }
    public void storeMetadata( String tableName, int width, int height, Iterator columnNames ) throws IOException {
        this.tableName = tableName;
        while(columnNames.hasNext()){
            this.columnNames.add(columnNames.next().toString());
        }
        out.write("<" + tableName + ">\n");
    }

    public void storeRow( Iterator data ) throws IOException {
        out.write("<item>");

        int i = 0;
        while(data.hasNext()) {
            Object datum = data.next();
            Object columnName = columnNames.get(i);
            out.write(String.format("<%s>%s</%s>", columnName, datum, columnName));
            i++;
        }
        out.write("</item>\n");
    }
    public void endTable()   throws IOException {
        out.write("</" + tableName + ">\n");
    }
}
