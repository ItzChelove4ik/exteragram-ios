package org.commonmark.node;

/* loaded from: classes5.dex */
public class OrderedList extends ListBlock {
    private char delimiter;
    private int startNumber;

    public OrderedList() {
    }

    @Override // org.commonmark.node.Node
    public void accept(Visitor r1) {
        r1.visit(this);
    }

    public char getDelimiter() {
        return this.delimiter;
    }

    public int getStartNumber() {
        return this.startNumber;
    }

    public void setDelimiter(char r1) {
        this.delimiter = r1;
    }

    public void setStartNumber(int r1) {
        this.startNumber = r1;
    }
}
