package com.holub.database;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

public class HTMLExporter implements Table.Exporter {
    private final Writer out;

    public HTMLExporter( Writer out ) {
        this.out = out;
    }
    public void startTable() throws IOException {
        out.write("<html>\n");
        out.write("<body>\n");
    }
    public void storeMetadata( String tableName, int width, int height, Iterator columnNames ) throws IOException {
        out.write(String.format("\t<h1>%s</h1>\n", tableName));
        out.write("\t<table>\n");
        out.write("\t\t<tr>\n");
        while(columnNames.hasNext()){
            Object columnName = columnNames.next();
            out.write(String.format("\t\t\t<th>%s</th>\n", columnName));
        }
        out.write("\t\t</tr>\n");
    }

    public void storeRow( Iterator data ) throws IOException {
        out.write("\t\t<tr>\n");
        while( data.hasNext() ) {
            Object datum = data.next();
            out.write(String.format("\t\t\t<td>%s</td>\n", datum));
        }
        out.write("\t\t</tr>\n");
    }
    public void endTable()   throws IOException {
        out.write("\t</table>\n");
        out.write("</body>\n");
        out.write("</html>");
    }
}
