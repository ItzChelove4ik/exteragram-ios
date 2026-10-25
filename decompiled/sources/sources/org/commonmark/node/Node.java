package org.commonmark.node;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public abstract class Node {
    private Node firstChild;
    private Node lastChild;
    private Node next;
    private Node parent;
    private Node prev;

    public Node() {
        this.parent = null;
        this.firstChild = null;
        this.lastChild = null;
        this.prev = null;
        this.next = null;
    }

    public abstract void accept(Visitor r1);

    public void appendChild(Node r2) {
        r2.unlink();
        r2.setParent(this);
        Node r0 = this.lastChild;
        if (r0 == null) goto L6;
        r0.next = r2;
        r2.prev = r0;
        this.lastChild = r2;
        return;
    L6:
        this.firstChild = r2;
        this.lastChild = r2;
    }

    public Node getFirstChild() {
        return this.firstChild;
    }

    public Node getLastChild() {
        return this.lastChild;
    }

    public Node getNext() {
        return this.next;
    }

    public Node getParent() {
        return this.parent;
    }

    public Node getPrevious() {
        return this.prev;
    }

    public void insertAfter(Node r2) {
        r2.unlink();
        Node r0 = this.next;
        r2.next = r0;
        if (r0 == null) goto L5;
        r0.prev = r2;
    L5:
        r2.prev = this;
        this.next = r2;
        Node r1 = this.parent;
        r2.parent = r1;
        if (r2.next != null) goto L9;
        r1.lastChild = r2;
        return;
    }

    public void insertBefore(Node r2) {
        r2.unlink();
        Node r0 = this.prev;
        r2.prev = r0;
        if (r0 == null) goto L5;
        r0.next = r2;
    L5:
        r2.next = this;
        this.prev = r2;
        Node r1 = this.parent;
        r2.parent = r1;
        if (r2.prev != null) goto L9;
        r1.firstChild = r2;
        return;
    }

    public void setParent(Node r1) {
        this.parent = r1;
    }

    public String toString() {
        return getClass().getSimpleName() + "{" + toStringAttributes() + "}";
    }

    public String toStringAttributes() {
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public void unlink() {
        Node r0 = this.prev;
        if (r0 == null) goto L5;
        r0.next = this.next;
    L8:
        Node r1 = this.next;
        if (r1 == null) goto L11;
        r1.prev = r0;
    L14:
        this.parent = null;
        this.next = null;
        this.prev = null;
        return;
    L11:
        Node r12 = this.parent;
        if (r12 == null) goto L14;
        r12.lastChild = r0;
        goto L14
    L5:
        Node r13 = this.parent;
        if (r13 == null) goto L8;
        r13.firstChild = this.next;
        goto L8
    }
}
