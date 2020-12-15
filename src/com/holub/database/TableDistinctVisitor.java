package com.holub.database;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;

public class TableDistinctVisitor implements TableVisitor{
    public void visit(ConcreteTable table){
        HashSet<String> duplicated = new HashSet<>();

        ListIterator iter = table.getRowSet().listIterator();
        while(iter.hasNext()){
            Object row = iter.next();
            StringBuilder key = new StringBuilder();
            for(Object column:(Object[])row){
                key.append(column);
            }
            if(duplicated.contains(key.toString())){
                iter.remove();
            } else {
                duplicated.add(key.toString());
            }
        }
    }
    public void visit(UnmodifiableTable table){
        visit((ConcreteTable)table.extract());
    }
}
