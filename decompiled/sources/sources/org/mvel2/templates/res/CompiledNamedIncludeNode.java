package org.mvel2.templates.res;

import java.io.Serializable;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.StackDelimiterResolverFactory;
import org.mvel2.templates.CompiledTemplate;
import org.mvel2.templates.TemplateError;
import org.mvel2.templates.TemplateRuntime;
import org.mvel2.templates.util.TemplateOutputStream;
import org.mvel2.templates.util.TemplateTools;

/* loaded from: classes5.dex */
public class CompiledNamedIncludeNode extends Node {
    private Serializable cIncludeExpression;
    private Serializable cPreExpression;

    public CompiledNamedIncludeNode(int r1, String r2, char[] r3, int r4, int r5, ParserContext r6) {
        this.begin = r1;
        this.name = r2;
        this.contents = r3;
        this.cStart = r4;
        this.cEnd = r5 - 1;
        this.end = r5;
        int r12 = TemplateTools.captureToEOS(r3, r4);
        char[] r22 = this.contents;
        int r32 = this.cStart;
        this.cIncludeExpression = MVEL.compileExpression(r22, r32, r12 - r32, r6);
        char[] r23 = this.contents;
        if (r12 == r23.length) goto L6;
        int r13 = r12 + 1;
        this.cPreExpression = MVEL.compileExpression(r23, r13, this.cEnd - r13, r6);
        return;
    }

    @Override // org.mvel2.templates.res.Node
    public boolean demarcate(Node r1, char[] r2) {
        return false;
    }

    @Override // org.mvel2.templates.res.Node
    public Object eval(TemplateRuntime r3, TemplateOutputStream r4, Object r5, VariableResolverFactory r6) {
        StackDelimiterResolverFactory r0 = new StackDelimiterResolverFactory(r6);
        Serializable r62 = this.cPreExpression;
        if (r62 == null) goto L6;
        MVEL.executeExpression(r62, r5, r0);
    L6:
        if (this.next == null) goto L14;
        String r63 = (String) MVEL.executeExpression(this.cIncludeExpression, r5, r0, String.class);
        CompiledTemplate r1 = r3.getNamedTemplateRegistry().getNamedTemplate(r63);
        if (r1 == null) goto L12;
        return this.next.eval(r3, r4.append(String.valueOf(TemplateRuntime.execute(r1, r5, r0, r3.getNamedTemplateRegistry()))), r5, r0);
    L12:
        throw new TemplateError("named template does not exist: " + r63);
    L14:
        return r4.append(String.valueOf(TemplateRuntime.execute(r3.getNamedTemplateRegistry().getNamedTemplate((String) MVEL.executeExpression(this.cIncludeExpression, r5, r0, String.class)), r5, r0, r3.getNamedTemplateRegistry())));
    }
}
