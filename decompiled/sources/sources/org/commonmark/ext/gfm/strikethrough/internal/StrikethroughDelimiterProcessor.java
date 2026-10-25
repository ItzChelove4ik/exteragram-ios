package org.commonmark.ext.gfm.strikethrough.internal;

import org.commonmark.ext.gfm.strikethrough.Strikethrough;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.delimiter.DelimiterProcessor;
import org.commonmark.parser.delimiter.DelimiterRun;

/* loaded from: classes5.dex */
public class StrikethroughDelimiterProcessor implements DelimiterProcessor {
    public StrikethroughDelimiterProcessor() {
    }

    @Override // org.commonmark.parser.delimiter.DelimiterProcessor
    public char getClosingCharacter() {
        return '~';
    }

    @Override // org.commonmark.parser.delimiter.DelimiterProcessor
    public int getDelimiterUse(DelimiterRun r1, DelimiterRun r2) {
        if (r1.length() >= 2) goto L5;
        return 0;
    L5:
        if (r2.length() < 2) goto L9;
        return 2;
    L9:
        return 0;
    }

    @Override // org.commonmark.parser.delimiter.DelimiterProcessor
    public int getMinLength() {
        return 2;
    }

    @Override // org.commonmark.parser.delimiter.DelimiterProcessor
    public char getOpeningCharacter() {
        return '~';
    }

    @Override // org.commonmark.parser.delimiter.DelimiterProcessor
    public void process(Text r2, Text r3, int r4) {
        Strikethrough r1 = new Strikethrough();
        Node r42 = r2.getNext();
    L3:
        if (r42 == null) goto L6;
        if (r42 == r3) goto L6;
        Node r0 = r42.getNext();
        r1.appendChild(r42);
        r42 = r0;
    L6:
        r2.insertAfter(r1);
    }
}
