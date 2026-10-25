package org.mvel2.templates.res;

import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.templates.TemplateRuntime;
import org.mvel2.templates.util.TemplateOutputStream;

/* loaded from: classes5.dex */
public class TerminalNode extends Node {
    public TerminalNode() {
    }

    public TerminalNode(int r1, int r2) {
        this.begin = r1;
        this.end = r2;
    }

    @Override // org.mvel2.templates.res.Node
    public boolean demarcate(Node r1, char[] r2) {
        return false;
    }

    @Override // org.mvel2.templates.res.Node
    public Object eval(TemplateRuntime r1, TemplateOutputStream r2, Object r3, VariableResolverFactory r4) {
        Node r0 = this.next;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.eval(r1, r2, r3, r4);
    }
}
