package org.commonmark.node;

/* loaded from: classes5.dex */
public class HardLineBreak extends Node {
    public HardLineBreak() {
    }

    @Override // org.commonmark.node.Node
    public void accept(Visitor r1) {
        r1.visit(this);
    }
}
