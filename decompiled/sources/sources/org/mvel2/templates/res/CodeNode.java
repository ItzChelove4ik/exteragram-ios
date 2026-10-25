package org.mvel2.templates.res;

import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.templates.TemplateRuntime;
import org.mvel2.templates.util.TemplateOutputStream;

/* loaded from: classes5.dex */
public class CodeNode extends Node {
    private int offset;
    private int start;

    public CodeNode() {
    }

    public CodeNode(int r1, String r2, char[] r3, int r4, int r5) {
        this.begin = r1;
        this.name = r2;
        this.contents = r3;
        this.start = r4;
        this.offset = (r5 - r4) - 1;
    }

    public CodeNode(int r1, String r2, char[] r3, int r4, int r5, Node r6) {
        this.name = r2;
        this.begin = r1;
        this.next = r6;
        this.start = r4;
        this.offset = (r5 - r4) - 1;
    }

    @Override // org.mvel2.templates.res.Node
    public boolean demarcate(Node r1, char[] r2) {
        return false;
    }

    @Override // org.mvel2.templates.res.Node
    public Object eval(TemplateRuntime r4, TemplateOutputStream r5, Object r6, VariableResolverFactory r7) {
        MVEL.eval(this.contents, this.start, this.offset, r6, r7);
        Node r3 = this.next;
        if (r3 != null) goto L5;
        return null;
    L5:
        return r3.eval(r4, r5, r6, r7);
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("CodeNode:");
        r0.append(this.name);
        r0.append("{");
        char[] r1 = this.contents;
        if (r1 != null) goto L5;
        String r12 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        r0.append(r12);
        r0.append("} (start=");
        r0.append(this.begin);
        r0.append(";end=");
        r0.append(this.end);
        r0.append(")");
        return r0.toString();
    L5:
        r12 = new String(r1);
        goto L6
    }
}
