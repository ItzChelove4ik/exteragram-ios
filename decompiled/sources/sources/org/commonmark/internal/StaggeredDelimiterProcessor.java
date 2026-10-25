package org.commonmark.internal;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import org.commonmark.node.Text;
import org.commonmark.parser.delimiter.DelimiterProcessor;
import org.commonmark.parser.delimiter.DelimiterRun;

/* loaded from: classes5.dex */
class StaggeredDelimiterProcessor implements DelimiterProcessor {
    private final char delim;
    private int minLength;
    private LinkedList<DelimiterProcessor> processors;

    public StaggeredDelimiterProcessor(char r2) {
        this.minLength = 0;
        this.processors = new LinkedList();
        this.delim = r2;
    }

    private DelimiterProcessor findProcessor(int r4) {
        Iterator<DelimiterProcessor> r0 = this.processors.iterator();
    L4:
        if (r0.hasNext() == false) goto L9;
        DelimiterProcessor r1 = r0.next();
        if (r1.getMinLength() > r4) goto L4;
        return r1;
    L9:
        return this.processors.getFirst();
    }

    public void add(DelimiterProcessor r4) {
        int r0 = r4.getMinLength();
        ListIterator<DelimiterProcessor> r1 = this.processors.listIterator();
    L4:
        if (r1.hasNext() == false) goto L13;
        int r2 = r1.next().getMinLength();
        if (r0 > r2) goto L7;
        if (r0 != r2) goto L4;
        throw new IllegalArgumentException("Cannot add two delimiter processors for char '" + this.delim + "' and minimum length " + r0);
    L7:
        r1.previous();
        r1.add(r4);
        return;
    L13:
        this.processors.add(r4);
        this.minLength = r0;
    }

    @Override // org.commonmark.parser.delimiter.DelimiterProcessor
    public char getClosingCharacter() {
        return this.delim;
    }

    @Override // org.commonmark.parser.delimiter.DelimiterProcessor
    public int getDelimiterUse(DelimiterRun r2, DelimiterRun r3) {
        return findProcessor(r2.length()).getDelimiterUse(r2, r3);
    }

    @Override // org.commonmark.parser.delimiter.DelimiterProcessor
    public int getMinLength() {
        return this.minLength;
    }

    @Override // org.commonmark.parser.delimiter.DelimiterProcessor
    public char getOpeningCharacter() {
        return this.delim;
    }

    @Override // org.commonmark.parser.delimiter.DelimiterProcessor
    public void process(Text r1, Text r2, int r3) {
        findProcessor(r3).process(r1, r2, r3);
    }
}
