package org.commonmark.node;

/* loaded from: classes5.dex */
public class Emphasis extends Node {
    private String delimiter;

    public Emphasis(String r1) {
        this.delimiter = r1;
    }

    @Override // org.commonmark.node.Node
    public void accept(Visitor r1) {
        r1.visit(this);
    }
}
