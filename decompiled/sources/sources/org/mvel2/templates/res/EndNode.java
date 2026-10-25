package org.mvel2.templates.res;

import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.templates.TemplateRuntime;
import org.mvel2.templates.util.TemplateOutputStream;

/* loaded from: classes5.dex */
public class EndNode extends Node {
    public EndNode() {
    }

    @Override // org.mvel2.templates.res.Node
    public boolean demarcate(Node r1, char[] r2) {
        return false;
    }

    @Override // org.mvel2.templates.res.Node
    public Object eval(TemplateRuntime r1, TemplateOutputStream r2, Object r3, VariableResolverFactory r4) {
        return r2.toString();
    }

    public String toString() {
        return "EndNode";
    }
}
