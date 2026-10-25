package org.commonmark.node;

/* loaded from: classes5.dex */
public class Image extends Node {
    private String destination;
    private String title;

    public Image(String r1, String r2) {
        this.destination = r1;
        this.title = r2;
    }

    @Override // org.commonmark.node.Node
    public void accept(Visitor r1) {
        r1.visit(this);
    }

    @Override // org.commonmark.node.Node
    public String toStringAttributes() {
        return "destination=" + this.destination + ", title=" + this.title;
    }
}
