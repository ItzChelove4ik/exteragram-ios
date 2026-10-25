package org.commonmark.node;

/* loaded from: classes5.dex */
public class Heading extends Block {
    private int level;

    public Heading() {
    }

    @Override // org.commonmark.node.Node
    public void accept(Visitor r1) {
        r1.visit(this);
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int r1) {
        this.level = r1;
    }
}
