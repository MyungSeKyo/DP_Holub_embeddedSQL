package com.holub.database;

public interface TableVisitor {
    void visit(ConcreteTable table);
    void visit(UnmodifiableTable table);
}
