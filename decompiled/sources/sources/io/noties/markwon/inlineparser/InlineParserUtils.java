package io.noties.markwon.inlineparser;

import org.commonmark.node.Node;
import org.commonmark.node.Text;

/* loaded from: classes5.dex */
public abstract class InlineParserUtils {
    public static void mergeChildTextNodes(Node r2) {
        if (r2.getFirstChild() != r2.getLastChild()) goto L5;
        return;
    L5:
        mergeTextNodesInclusive(r2.getFirstChild(), r2.getLastChild());
    }

    public static void mergeIfNeeded(Text r2, Text r3, int r4) {
        if (r2 == null) goto L11;
        if (r3 == null) goto L12;
        if (r2 == r3) goto L13;
        StringBuilder r0 = new StringBuilder(r4);
        r0.append(r2.getLiteral());
        Node r42 = r2.getNext();
        Node r32 = r3.getNext();
    L6:
        if (r42 == r32) goto L8;
        r0.append(((Text) r42).getLiteral());
        Node r1 = r42.getNext();
        r42.unlink();
        r42 = r1;
        goto L6
    L8:
        r2.setLiteral(r0.toString());
        return;
    L13:
        return;
    L12:
        return;
    }

    public static void mergeTextNodesBetweenExclusive(Node r1, Node r2) {
        if (r1 != r2) goto L4;
        return;
    L4:
        if (r1.getNext() == r2) goto L9;
        mergeTextNodesInclusive(r1.getNext(), r2.getPrevious());
        return;
    }

    public static void mergeTextNodesInclusive(Node r6, Node r7) {
        Text r2 = null;
        Text r3 = null;
        int r4 = 0;
    L3:
        if (r6 == null) goto L14;
        if ((r6 instanceof Text) == false) goto L10;
        r3 = (Text) r6;
        if (r2 != null) goto L9;
        r2 = r3;
    L9:
        r4 = r4 + r3.getLiteral().length();
    L11:
        if (r6 == r7) goto L14;
        r6 = r6.getNext();
        goto L3
    L10:
        mergeIfNeeded(r2, r3, r4);
        r2 = null;
        r3 = null;
        r4 = 0;
    L14:
        mergeIfNeeded(r2, r3, r4);
    }
}
