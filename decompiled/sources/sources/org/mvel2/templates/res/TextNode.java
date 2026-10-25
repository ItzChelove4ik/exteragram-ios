package org.mvel2.templates.res;

import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.templates.TemplateRuntime;
import org.mvel2.templates.util.TemplateOutputStream;

/* loaded from: classes5.dex */
public class TextNode extends Node {
    public TextNode(int r1, int r2) {
        this.begin = r1;
        this.end = r2;
    }

    public TextNode(int r1, int r2, ExpressionNode r3) {
        this.begin = r1;
        this.end = r2;
        this.next = r3;
    }

    @Override // org.mvel2.templates.res.Node
    public void calculateContents(char[] r1) {
    }

    @Override // org.mvel2.templates.res.Node
    public boolean demarcate(Node r1, char[] r2) {
        return false;
    }

    @Override // org.mvel2.templates.res.Node
    public Object eval(TemplateRuntime r5, TemplateOutputStream r6, Object r7, VariableResolverFactory r8) {
        int r0 = this.end - this.begin;
        if (r0 == 0) goto L5;
        r6.append(new String(r5.getTemplate(), this.begin, r0));
    L5:
        Node r4 = this.next;
        if (r4 != null) goto L8;
        return null;
    L8:
        return r4.eval(r5, r6, r7, r8);
    }

    public String toString() {
        return "TextNode(" + this.begin + "," + this.end + ")";
    }
}
