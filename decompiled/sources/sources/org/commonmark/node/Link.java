package org.commonmark.node;

/* loaded from: classes5.dex */
public class Link extends Node {
    private String destination;
    private String title;

    public Link(String r1, String r2) {
        this.destination = r1;
        this.title = r2;
    }

    @Override // org.commonmark.node.Node
    public void accept(Visitor r1) {
        r1.visit(this);
    }

    public String getDestination() {
        return this.destination;
    }

    @Override // org.commonmark.node.Node
    public String toStringAttributes() {
        return "destination=" + this.destination + ", title=" + this.title;
    }
}
