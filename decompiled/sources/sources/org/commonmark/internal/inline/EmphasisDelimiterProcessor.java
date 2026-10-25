package org.commonmark.internal.inline;

import org.commonmark.node.Emphasis;
import org.commonmark.node.Node;
import org.commonmark.node.StrongEmphasis;
import org.commonmark.node.Text;
import org.commonmark.parser.delimiter.DelimiterProcessor;
import org.commonmark.parser.delimiter.DelimiterRun;

/* loaded from: classes5.dex */
public abstract class EmphasisDelimiterProcessor implements DelimiterProcessor {
    private final char delimiterChar;

    public EmphasisDelimiterProcessor(char r1) {
        this.delimiterChar = r1;
    }

    @Override // org.commonmark.parser.delimiter.DelimiterProcessor
    public char getClosingCharacter() {
        return this.delimiterChar;
    }

    @Override // org.commonmark.parser.delimiter.DelimiterProcessor
    public int getDelimiterUse(DelimiterRun r2, DelimiterRun r3) {
        if (r2.canClose() == true) goto L7;
        if (r3.canOpen() == true) goto L7;
    L13:
        if (r2.length() >= 2) goto L15;
        return 1;
    L15:
        if (r3.length() < 2) goto L19;
        return 2;
    L19:
        return 1;
    L7:
        if ((r3.originalLength() % 3) == 0) goto L13;
        if (((r2.originalLength() + r3.originalLength()) % 3) != 0) goto L13;
        return 0;
    }

    @Override // org.commonmark.parser.delimiter.DelimiterProcessor
    public int getMinLength() {
        return 1;
    }

    @Override // org.commonmark.parser.delimiter.DelimiterProcessor
    public char getOpeningCharacter() {
        return this.delimiterChar;
    }

    @Override // org.commonmark.parser.delimiter.DelimiterProcessor
    public void process(Text r2, Text r3, int r4) {
        String r1 = String.valueOf(getOpeningCharacter());
        if (r4 != 1) goto L5;
        Node r42 = new Emphasis(r1);
    L6:
        Node r12 = r2.getNext();
    L7:
        if (r12 == null) goto L10;
        if (r12 == r3) goto L10;
        Node r0 = r12.getNext();
        r42.appendChild(r12);
        r12 = r0;
    L10:
        r2.insertAfter(r42);
        return;
    L5:
        r42 = new StrongEmphasis(r1 + r1);
        goto L6
    }
}
