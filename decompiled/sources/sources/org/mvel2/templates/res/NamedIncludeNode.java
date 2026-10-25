package org.mvel2.templates.res;

import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.templates.TemplateRuntime;
import org.mvel2.templates.util.TemplateOutputStream;
import org.mvel2.templates.util.TemplateTools;

/* loaded from: classes5.dex */
public class NamedIncludeNode extends Node {
    int includeOffset;
    int includeStart;
    int preOffset;
    int preStart;

    public NamedIncludeNode(int r1, String r2, char[] r3, int r4, int r5) {
        this.begin = r1;
        this.name = r2;
        this.contents = r3;
        this.cStart = r4;
        this.cEnd = r5 - 1;
        this.end = r5;
        int r12 = TemplateTools.captureToEOS(r3, 0);
        int r22 = this.cStart;
        this.includeStart = r22;
        this.includeOffset = r12 - r22;
        int r13 = r12 + 1;
        this.preStart = r13;
        this.preOffset = this.cEnd - r13;
    }

    @Override // org.mvel2.templates.res.Node
    public boolean demarcate(Node r1, char[] r2) {
        return false;
    }

    @Override // org.mvel2.templates.res.Node
    public Object eval(TemplateRuntime r9, TemplateOutputStream r10, Object r11, VariableResolverFactory r12) {
        int r0 = this.preOffset;
        if (r0 == 0) goto L5;
        MVEL.eval(this.contents, this.preStart, r0, r11, r12);
    L5:
        Node r02 = this.next;
        if (r02 == null) goto L10;
        return r02.eval(r9, r10.append(String.valueOf(TemplateRuntime.execute(r9.getNamedTemplateRegistry().getNamedTemplate((String) MVEL.eval(this.contents, this.includeStart, this.includeOffset, r11, r12, String.class)), r11, r12))), r11, r12);
    L10:
        return r10.append(String.valueOf(TemplateRuntime.execute(r9.getNamedTemplateRegistry().getNamedTemplate((String) MVEL.eval(this.contents, this.includeStart, this.includeOffset, r11, r12, String.class)), r11, r12)));
    }
}
