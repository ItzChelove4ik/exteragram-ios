package org.mvel2.ast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.mvel2.CompileException;
import org.mvel2.ParserContext;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.DefaultLocalVariableResolverFactory;
import org.mvel2.integration.impl.ItemResolverFactory;
import org.mvel2.util.CompilerTools;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class Fold extends ASTNode {
    private ExecutableStatement constraintEx;
    private ExecutableStatement dataEx;
    private ExecutableStatement subEx;

    public Fold(char[] r6, int r7, int r8, int r9, ParserContext r10) {
        super(r10);
        this.expr = r6;
        this.start = r7;
        this.offset = r8;
        int r82 = r8 + r7;
        int r0 = r7;
    L3:
        boolean r2 = true;
        if (r0 >= r82) goto L19;
        if (ParseTools.isWhitespace(r6[r0]) == false) goto L18;
    L7:
        if (r0 >= r82) goto L12;
        if (ParseTools.isWhitespace(r6[r0]) == false) goto L12;
        r0 = r0 + 1;
    L12:
        if (r6[r0] != 'i') goto L18;
        if (r6[r0 + 1] != 'n') goto L18;
        if (ParseTools.isJunct(r6[r0 + 2]) == true) goto L19;
    L18:
        r0 = r0 + 1;
    L19:
        this.subEx = (ExecutableStatement) ParseTools.subCompileExpression(r6, r7, (r0 - r7) - 1, r10);
        int r02 = r0 + 2;
        int r72 = r02;
    L20:
        if (r72 >= r82) goto L35;
        if (ParseTools.isWhitespace(r6[r72]) == false) goto L34;
    L23:
        if (r72 >= r82) goto L28;
        if (ParseTools.isWhitespace(r6[r72]) == false) goto L28;
        r72 = r72 + 1;
    L28:
        if (r6[r72] != 'i') goto L34;
        if (r6[r72 + 1] != 'f') goto L34;
        int r3 = r72 + 2;
        if (ParseTools.isJunct(r6[r3]) == false) goto L34;
        this.constraintEx = (ExecutableStatement) ParseTools.subCompileExpression(r6, r3, r82 - r3, r10);
    L34:
        r72 = r72 + 1;
    L35:
        if (r72 > r82) goto L39;
        r82 = r72;
    L39:
        if (ParseTools.isWhitespace(r6[r82]) == false) goto L41;
        r82 = r82 - 1;
        goto L39
    L41:
        ExecutableStatement r62 = (ExecutableStatement) ParseTools.subCompileExpression(r6, r02, r82 - r02, r10);
        this.dataEx = r62;
        if ((r9 & 16) != 0) goto L45;
        r2 = false;
    L45:
        CompilerTools.expectType(r10, r62, Collection.class, r2);
    }

    @Override // org.mvel2.ast.ASTNode
    public Class getEgressType() {
        return Collection.class;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r7, Object r8, VariableResolverFactory r9) {
        ItemResolverFactory.ItemResolver r0 = new ItemResolverFactory.ItemResolver("$");
        ItemResolverFactory r1 = new ItemResolverFactory(r0, new DefaultLocalVariableResolverFactory(r9));
        ExecutableStatement r2 = this.constraintEx;
        ExecutableStatement r3 = this.dataEx;
        String r5 = "null";
        if (r2 == null) goto L19;
        Object r92 = r3.getValue(r7, r8, r9);
        if ((r92 instanceof Collection) == false) goto L7;
        Collection r93 = (Collection) r92;
        ArrayList r22 = new ArrayList(r93.size());
        Iterator r94 = r93.iterator();
    L14:
        if (r94.hasNext() == false) goto L18;
        Object r32 = r94.next();
        r0.value = r32;
        if (((Boolean) this.constraintEx.getValue(r7, r8, r1)).booleanValue() == false) goto L14;
        r22.add(this.subEx.getValue(r32, r8, r1));
        goto L14
    L18:
        return r22;
    L7:
        if (r92 == null) goto L11;
        r5 = r92.getClass().getName();
    L11:
        throw new CompileException("was expecting type: Collection; but found type: ".concat(r5), this.expr, this.start);
    L19:
        Object r72 = r3.getValue(r7, r8, r9);
        if ((r72 instanceof Collection) == false) goto L22;
        Collection r73 = (Collection) r72;
        ArrayList r95 = new ArrayList(r73.size());
        Iterator r74 = r73.iterator();
    L29:
        if (r74.hasNext() == false) goto L31;
        Object r23 = r74.next();
        ExecutableStatement r33 = this.subEx;
        r0.value = r23;
        r95.add(r33.getValue(r23, r8, r1));
        goto L29
    L31:
        return r95;
    L22:
        if (r72 == null) goto L26;
        r5 = r72.getClass().getName();
    L26:
        throw new CompileException("was expecting type: Collection; but found type: ".concat(r5), this.expr, this.start);
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r6, Object r7, VariableResolverFactory r8) {
        ItemResolverFactory.ItemResolver r0 = new ItemResolverFactory.ItemResolver("$");
        ItemResolverFactory r1 = new ItemResolverFactory(r0, new DefaultLocalVariableResolverFactory(r8));
        ExecutableStatement r2 = this.constraintEx;
        ExecutableStatement r3 = this.dataEx;
        if (r2 == null) goto L11;
        Collection r82 = (Collection) r3.getValue(r6, r7, r8);
        ArrayList r22 = new ArrayList(r82.size());
        Iterator r83 = r82.iterator();
    L6:
        if (r83.hasNext() == false) goto L10;
        Object r32 = r83.next();
        r0.value = r32;
        if (((Boolean) this.constraintEx.getValue(r6, r7, r1)).booleanValue() == false) goto L6;
        r22.add(this.subEx.getValue(r32, r7, r1));
        goto L6
    L10:
        return r22;
    L11:
        Collection r62 = (Collection) r3.getValue(r6, r7, r8);
        ArrayList r84 = new ArrayList(r62.size());
        Iterator r63 = r62.iterator();
    L13:
        if (r63.hasNext() == false) goto L15;
        Object r23 = r63.next();
        ExecutableStatement r33 = this.subEx;
        r0.value = r23;
        r84.add(r33.getValue(r23, r7, r1));
        goto L13
    L15:
        return r84;
    }
}
