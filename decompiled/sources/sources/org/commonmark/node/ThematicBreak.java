package org.commonmark.node;

/* loaded from: classes5.dex */
public class ThematicBreak extends Block {
    public ThematicBreak() {
    }

    @Override // org.commonmark.node.Node
    public void accept(Visitor r1) {
        r1.visit(this);
    }
}
