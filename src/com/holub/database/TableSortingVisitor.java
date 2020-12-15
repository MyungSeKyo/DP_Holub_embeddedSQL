package com.holub.database;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;

public class TableSortingVisitor implements TableVisitor {
    private HashMap<String, Boolean> orderingKeys;
    public TableSortingVisitor(HashMap<String, Boolean> orderingKeys){
        this.orderingKeys = orderingKeys;
    }
    public void visit(ConcreteTable table){

    }
    public void visit(UnmodifiableTable table){
        visit((ConcreteTable)table.extract());
    }
}
