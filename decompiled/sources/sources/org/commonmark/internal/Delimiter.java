package org.commonmark.internal;

import org.commonmark.node.Text;
import org.commonmark.parser.delimiter.DelimiterRun;

/* loaded from: classes5.dex */
public class Delimiter implements DelimiterRun {
    public final boolean canClose;
    public final boolean canOpen;
    public final char delimiterChar;
    public int length;
    public Delimiter next;
    public final Text node;
    public int originalLength;
    public Delimiter previous;

    public Delimiter(Text r2, char r3, boolean r4, boolean r5, Delimiter r6) {
        this.length = 1;
        this.originalLength = 1;
        this.node = r2;
        this.delimiterChar = r3;
        this.canOpen = r4;
        this.canClose = r5;
        this.previous = r6;
    }

    @Override // org.commonmark.parser.delimiter.DelimiterRun
    public boolean canClose() {
        return this.canClose;
    }

    @Override // org.commonmark.parser.delimiter.DelimiterRun
    public boolean canOpen() {
        return this.canOpen;
    }

    @Override // org.commonmark.parser.delimiter.DelimiterRun
    public int length() {
        return this.length;
    }

    @Override // org.commonmark.parser.delimiter.DelimiterRun
    public int originalLength() {
        return this.originalLength;
    }
}
