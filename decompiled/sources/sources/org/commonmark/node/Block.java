package org.commonmark.node;

import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public abstract class Block extends Node {
    public Block() {
    }

    @Override // org.commonmark.node.Node
    public Block getParent() {
        return (Block) super.getParent();
    }

    @Override // org.commonmark.node.Node
    public void setParent(Node r2) {
        if ((r2 instanceof Block) == false) goto L6;
        super.setParent(r2);
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Parent of block must also be block (can not be inline)");
    }
}
