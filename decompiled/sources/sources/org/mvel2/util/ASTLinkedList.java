package org.mvel2.util;

import org.mvel2.ast.ASTNode;

/* loaded from: classes5.dex */
public class ASTLinkedList implements ASTIterator {
    private ASTNode current;
    private ASTNode firstASTNode;
    private ASTNode last;
    private int size;

    public ASTLinkedList() {
    }

    public ASTLinkedList(ASTNode r1) {
        this.firstASTNode = r1;
        this.current = r1;
    }

    public ASTLinkedList(ASTNode r1, int r2) {
        this.firstASTNode = r1;
        this.current = r1;
        this.size = r2;
    }

    public ASTLinkedList(ASTIterator r1) {
        ASTNode r12 = r1.firstNode();
        this.firstASTNode = r12;
        this.current = r12;
    }

    @Override // org.mvel2.util.ASTIterator
    public void addTokenNode(ASTNode r2) {
        this.size++;
        if (this.firstASTNode != null) goto L6;
        this.current = r2;
        this.firstASTNode = r2;
        return;
    L6:
        this.current.nextASTNode = r2;
        this.current = r2;
        this.last = r2;
    }

    @Override // org.mvel2.util.ASTIterator
    public void addTokenNode(ASTNode r2, ASTNode r3) {
        this.size += 2;
        if (this.firstASTNode != null) goto L6;
        this.firstASTNode = r2;
        r2.nextASTNode = r3;
        this.current = r3;
        this.last = r3;
        return;
    L6:
        this.current.nextASTNode = r2;
        r2.nextASTNode = r3;
        this.current = r3;
        this.last = r3;
    }

    @Override // org.mvel2.util.ASTIterator
    public void back() {
        this.current = this.last;
    }

    @Override // org.mvel2.util.ASTIterator
    public void finish() {
        reset();
        ASTNode r0 = null;
    L4:
        if (hasMoreNodes() == false) goto L14;
        ASTNode r1 = nextNode();
        if (r1.isDiscard() == true) goto L7;
        if (hasMoreNodes() == false) goto L14;
        r0 = r1;
        goto L4
    L7:
        if (r0 == null) goto L8;
        r0.nextASTNode = nextNode();
        goto L4
    L8:
        r0 = nextNode();
        this.firstASTNode = r0;
    L14:
        this.last = r0;
        reset();
    }

    @Override // org.mvel2.util.ASTIterator
    public ASTNode firstNode() {
        return this.firstASTNode;
    }

    public ASTNode firstNonSymbol() {
        ASTNode r2 = this.firstASTNode;
        if (r2.fields == (-1)) goto L5;
        return r2;
    L5:
        return r2.nextASTNode;
    }

    @Override // org.mvel2.util.ASTIterator
    public boolean hasMoreNodes() {
        if (this.current == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.mvel2.util.ASTIterator
    public int index() {
        return -1;
    }

    public boolean isSingleNode() {
        int r0 = this.size;
        if (r0 != 1) goto L5;
    L11:
        return true;
    L5:
        if (r0 == 2) goto L7;
        return false;
    L7:
        if (this.firstASTNode.fields == (-1)) goto L11;
        return false;
    }

    @Override // org.mvel2.util.ASTIterator
    public ASTNode nextNode() {
        ASTNode r0 = this.current;
        if (r0 != null) goto L6;
        return null;
    L6:
        this.last = r0;
        this.current = r0.nextASTNode;
        return r0;
    }

    @Override // org.mvel2.util.ASTIterator
    public ASTNode nodesAhead(int r4) {
        if (this.current != null) goto L5;
        return null;
    L5:
        int r0 = 0;
        ASTNode r2 = null;
    L6:
        if (r0 >= r4) goto L11;
        r2 = this.current.nextASTNode;
        if (r2 == null) goto L9;
        r0 = r0 + 1;
        goto L6
    L9:
        return null;
    L11:
        return r2;
    }

    @Override // org.mvel2.util.ASTIterator
    public ASTNode nodesBack(int r1) {
        throw new RuntimeException("unimplemented");
    }

    @Override // org.mvel2.util.ASTIterator
    public ASTNode peekLast() {
        return this.last;
    }

    @Override // org.mvel2.util.ASTIterator
    public ASTNode peekNext() {
        ASTNode r0 = this.current;
        if (r0 == null) goto L7;
        ASTNode r02 = r0.nextASTNode;
        if (r02 == null) goto L9;
        return r02;
    L9:
        return null;
    L7:
        return null;
    }

    @Override // org.mvel2.util.ASTIterator
    public ASTNode peekNode() {
        ASTNode r0 = this.current;
        if (r0 != null) goto L6;
        return null;
    L6:
        return r0;
    }

    public void removeToken() {
        ASTNode r0 = this.current;
        if (r0 == null) goto L6;
        this.current = r0.nextASTNode;
        return;
    }

    @Override // org.mvel2.util.ASTIterator
    public void reset() {
        this.current = this.firstASTNode;
    }

    public void setCurrentNode(ASTNode r1) {
        this.current = r1;
    }

    @Override // org.mvel2.util.ASTIterator
    public String showNodeChain() {
        throw new RuntimeException("unimplemented");
    }

    @Override // org.mvel2.util.ASTIterator
    public int size() {
        return this.size;
    }

    @Override // org.mvel2.util.ASTIterator
    public void skipNode() {
        ASTNode r0 = this.current;
        if (r0 == null) goto L6;
        this.current = r0.nextASTNode;
        return;
    }
}
