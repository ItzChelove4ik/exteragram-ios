package org.commonmark.node;

/* loaded from: classes5.dex */
public class Text extends Node {
    private String literal;

    public Text(String r1) {
        this.literal = r1;
    }

    @Override // org.commonmark.node.Node
    public void accept(Visitor r1) {
        r1.visit(this);
    }

    public String getLiteral() {
        return this.literal;
    }

    public void setLiteral(String r1) {
        this.literal = r1;
    }

    @Override // org.commonmark.node.Node
    public String toStringAttributes() {
        return "literal=" + this.literal;
    }
}
