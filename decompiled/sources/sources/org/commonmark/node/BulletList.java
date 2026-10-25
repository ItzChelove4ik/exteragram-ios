package org.commonmark.node;

/* loaded from: classes5.dex */
public class BulletList extends ListBlock {
    private char bulletMarker;

    public BulletList() {
    }

    @Override // org.commonmark.node.Node
    public void accept(Visitor r1) {
        r1.visit(this);
    }

    public char getBulletMarker() {
        return this.bulletMarker;
    }

    public void setBulletMarker(char r1) {
        this.bulletMarker = r1;
    }
}
