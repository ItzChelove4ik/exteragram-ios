package org.mvel2.templates.res;

import java.io.Serializable;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.templates.TemplateRuntime;
import org.mvel2.templates.util.TemplateOutputStream;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public abstract class Node implements Serializable {
    protected int begin;
    protected int cEnd;
    protected int cStart;
    protected char[] contents;
    protected int end;
    protected String name;
    public Node next;
    protected Node terminus;

    public Node() {
    }

    public Node(int r1, String r2, char[] r3, int r4, int r5) {
        this.begin = r1;
        this.cStart = r4;
        this.cEnd = r5 - 1;
        this.end = r5;
        this.name = r2;
        this.contents = r3;
    }

    public Node(int r1, String r2, char[] r3, int r4, int r5, Node r6) {
        this.name = r2;
        this.begin = r1;
        this.cStart = r4;
        this.cEnd = r5 - 1;
        this.end = r5;
        this.contents = r3;
        this.next = r6;
    }

    public void calculateContents(char[] r3) {
        int r0 = this.cStart;
        this.contents = ParseTools.subset(r3, r0, this.end - r0);
    }

    public abstract boolean demarcate(Node r1, char[] r2);

    public abstract Object eval(TemplateRuntime r1, TemplateOutputStream r2, Object r3, VariableResolverFactory r4);

    public int getBegin() {
        return this.begin;
    }

    public int getCEnd() {
        return this.cEnd;
    }

    public int getCStart() {
        return this.cStart;
    }

    public char[] getContents() {
        return this.contents;
    }

    public int getEnd() {
        return this.end;
    }

    public int getLength() {
        return this.end - this.begin;
    }

    public String getName() {
        return this.name;
    }

    public Node getNext() {
        return this.next;
    }

    public Node getTerminus() {
        return this.terminus;
    }

    public boolean isOpenNode() {
        return false;
    }

    public void setBegin(int r1) {
        this.begin = r1;
    }

    public void setCEnd(int r1) {
        this.cEnd = r1;
    }

    public void setCStart(int r1) {
        this.cStart = r1;
    }

    public void setContents(char[] r1) {
        this.contents = r1;
    }

    public void setEnd(int r1) {
        this.end = r1;
    }

    public void setName(String r1) {
        this.name = r1;
    }

    public Node setNext(Node r1) {
        this.next = r1;
        return r1;
    }

    public void setTerminus(Node r1) {
        this.terminus = r1;
    }
}
