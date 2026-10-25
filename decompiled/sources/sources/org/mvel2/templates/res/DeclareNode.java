package org.mvel2.templates.res;

import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.templates.CompiledTemplate;
import org.mvel2.templates.SimpleTemplateRegistry;
import org.mvel2.templates.TemplateRegistry;
import org.mvel2.templates.TemplateRuntime;
import org.mvel2.templates.util.TemplateOutputStream;

/* loaded from: classes5.dex */
public class DeclareNode extends Node {
    private Node nestedNode;

    public DeclareNode(int r1, String r2, char[] r3, int r4, int r5) {
        this.begin = r1;
        this.name = r2;
        this.contents = r3;
        this.cStart = r4;
        this.cEnd = r5 - 1;
        this.end = r5;
    }

    @Override // org.mvel2.templates.res.Node
    public boolean demarcate(Node r1, char[] r2) {
        Node r12 = this.next;
        this.nestedNode = r12;
    L4:
        if (r12.getNext() == null) goto L6;
        r12 = r12.next;
        goto L4
    L6:
        r12.next = new EndNode();
        this.next = this.terminus;
        return false;
    }

    @Override // org.mvel2.templates.res.Node
    public Object eval(TemplateRuntime r8, TemplateOutputStream r9, Object r10, VariableResolverFactory r11) {
        if (r8.getNamedTemplateRegistry() != null) goto L5;
        r8.setNamedTemplateRegistry(new SimpleTemplateRegistry());
    L5:
        TemplateRegistry r0 = r8.getNamedTemplateRegistry();
        char[] r1 = this.contents;
        int r2 = this.cStart;
        r0.addNamedTemplate((String) MVEL.eval(r1, r2, this.cEnd - r2, r10, r11, String.class), new CompiledTemplate(r8.getTemplate(), this.nestedNode));
        Node r7 = this.next;
        if (r7 != null) goto L8;
        return null;
    L8:
        return r7.eval(r8, r9, r10, r11);
    }
}
