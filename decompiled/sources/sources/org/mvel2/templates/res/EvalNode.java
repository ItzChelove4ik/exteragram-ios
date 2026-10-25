package org.mvel2.templates.res;

import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.templates.TemplateRuntime;
import org.mvel2.templates.util.TemplateOutputStream;

/* loaded from: classes5.dex */
public class EvalNode extends Node {
    public EvalNode() {
    }

    public EvalNode(int r1, String r2, char[] r3, int r4, int r5) {
        this.begin = r1;
        this.name = r2;
        this.contents = r3;
        this.cStart = r4;
        this.cEnd = r5 - 1;
        this.end = r5;
    }

    public EvalNode(int r1, String r2, char[] r3, int r4, int r5, Node r6) {
        this.name = r2;
        this.begin = r1;
        this.contents = r3;
        this.cStart = r4;
        this.cEnd = r5 - 1;
        this.end = r5;
        this.next = r6;
    }

    @Override // org.mvel2.templates.res.Node
    public boolean demarcate(Node r1, char[] r2) {
        return false;
    }

    @Override // org.mvel2.templates.res.Node
    public Object eval(TemplateRuntime r4, TemplateOutputStream r5, Object r6, VariableResolverFactory r7) {
        char[] r0 = this.contents;
        int r1 = this.cStart;
        r5.append(String.valueOf(TemplateRuntime.eval(String.valueOf(MVEL.eval(r0, r1, this.cEnd - r1, r6, r7)), r6, r7)));
        Node r3 = this.next;
        if (r3 != null) goto L5;
        return null;
    L5:
        return r3.eval(r4, r5, r6, r7);
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("EvalNode:");
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
        int r3 = this.cStart;
        r12 = new String(r1, r3, this.cEnd - r3);
        goto L6
    }
}
