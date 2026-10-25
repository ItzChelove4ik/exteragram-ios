package org.mvel2.asm;

/* loaded from: classes5.dex */
final class Edge {
    static final int EXCEPTION = Integer.MAX_VALUE;
    static final int JUMP = 0;
    final int info;
    Edge nextEdge;
    final Label successor;

    public Edge(int r1, Label r2, Edge r3) {
        this.info = r1;
        this.successor = r2;
        this.nextEdge = r3;
    }
}
