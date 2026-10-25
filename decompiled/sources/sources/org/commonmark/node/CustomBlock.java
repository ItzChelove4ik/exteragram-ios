package org.commonmark.node;

/* loaded from: classes5.dex */
public abstract class CustomBlock extends Block {
    public CustomBlock() {
    }

    @Override // org.commonmark.node.Node
    public void accept(Visitor r1) {
        r1.visit(this);
    }
}
