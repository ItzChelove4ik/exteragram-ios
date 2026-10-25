package org.mvel2.ast;

import java.io.Serializable;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.mvel2.CompileException;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.ErrorUtil;
import org.mvel2.util.ParseTools;
import org.mvel2.util.PropertyTools;

/* loaded from: classes5.dex */
public class WithNode extends BlockNode implements NestedStatement {
    protected String nestParm;
    protected ParmValuePair[] withExpressions;

    public static final class ParmValuePair implements Serializable {
        private Serializable setExpression;
        private ExecutableStatement statement;

        public ParmValuePair(String r2, ExecutableStatement r3, Class r4, ParserContext r5) {
            if (r2 != null) goto L5;
        L10:
            this.statement = r3;
            return;
        L5:
            if (r2.length() == 0) goto L10;
            if (r4 == null) goto L8;
            Class<Object> r42 = PropertyTools.getReturnType(r4, r2, r5);
        L9:
            this.setExpression = MVEL.compileSetExpression(r2, r42, r5);
            goto L10
        L8:
            r42 = Object.class;
            goto L9
        }

        public void eval(Object r2, VariableResolverFactory r3) {
            Serializable r0 = this.setExpression;
            ExecutableStatement r1 = this.statement;
            if (r0 != null) goto L6;
            r1.getValue(r2, r3);
            return;
        L6:
            MVEL.executeSetExpression(r0, r2, r3, r1.getValue(r2, r3));
        }

        public Serializable getSetExpression() {
            return this.setExpression;
        }

        public ExecutableStatement getStatement() {
            return this.statement;
        }
    }

    public WithNode(char[] r7, int r8, int r9, int r10, int r11, int r12, ParserContext r13) {
        super(r13);
        this.expr = r7;
        this.start = r8;
        this.offset = r9;
        this.nestParm = ParseTools.createStringTrimmed(r7, r8, r9);
        this.blockStart = r10;
        this.blockOffset = r11;
        if ((r12 & 16) == 0) goto L6;
        r13.setBlockSymbols(true);
        ExecutableStatement r82 = (ExecutableStatement) ParseTools.subCompileExpression(r7, r8, r9, r13);
        this.compiledBlock = r82;
        Class r4 = r82.getKnownEgressType();
        this.egressType = r4;
        this.withExpressions = compileWithExpressions(r7, r10, r11, this.nestParm, r4, r13);
        r13.setBlockSymbols(false);
        return;
    }

    public static ParmValuePair[] compileWithExpressions(char[] r19, int r20, int r21, String r22, Class r23, ParserContext r24) {
        ArrayList r5 = new ArrayList();
        int r6 = r20 + r21;
        String r7 = _UrlKt.FRAGMENT_ENCODE_SET;
        int r9 = r20;
        int r10 = r9;
        String r11 = _UrlKt.FRAGMENT_ENCODE_SET;
        int r12 = -1;
    L4:
        if (r9 >= r6) goto L89;
        char r13 = r19[r9];
        if (r13 == '\"') goto L86;
        String r18 = r7;
        if (r13 != '%') goto L10;
    L81:
        int r8 = r9 + 1;
        if (r8 >= r6) goto L88;
        if (r19[r8] != '=') goto L88;
        r12 = ParseTools.opLookup(r13);
    L88:
        r9 = r9 + 1;
        r7 = r18;
        goto L4
    L10:
        if (r13 == '/') goto L55;
        if (r13 != '=') goto L13;
        int r72 = r9 - r10;
        if (r12 == (-1)) goto L52;
        int r82 = 1;
    L53:
        r11 = ParseTools.createStringTrimmed(r19, r10, r72 - r82);
        r10 = r9 + 1;
        goto L88
    L52:
        r82 = 0;
        goto L53
    L13:
        if (r13 != '[') goto L15;
    L87:
        r9 = ParseTools.balancedCapture(r19, r9, r6, r13);
        goto L88
    L15:
        if (r13 == '{') goto L87;
        if (r13 == '\'') goto L87;
        if (r13 == '(') goto L87;
        switch(r13) {
            case 42: goto L81;
            case 43: goto L81;
            case 44: goto L22;
            case 45: goto L81;
            default: goto L88;
        };
    L22:
        if (r11 != null) goto L24;
    L39:
        if (r22 == null) goto L116;
        String r73 = r22 + '.' + new String(r19, r10, r9 - r10);     // Catch: CompileException -> L41
    L44:
        r5.add(new ParmValuePair(null, (ExecutableStatement) ParseTools.subCompileExpression(r73, r24), r23, r24));     // Catch: CompileException -> L41
        r9 = r9 + 1;
    L46:
        r10 = r9;
        r12 = -1;
    L41:
        e = move-exception;
        e.setCursor(r10 + (e.getCursor() - (e.getExpr().length - r21)));
        e.setExpr(r19);
        throw e;
    L116:
        r73 = new String(r19, r10, r9 - r10);     // Catch: CompileException -> L41
        goto L44
    L24:
        if (r11.length() == 0) goto L39;
        if (r22 == null) goto L37;
        if (r12 == (-1)) goto L32;
        ExecutableStatement r83 = (ExecutableStatement) ParseTools.subCompileExpression(ParseTools.createShortFormOperativeAssignment(r22 + "." + r11, r19, r10, r9 - r10, r12), r24);     // Catch: CompileException -> L30
    L33:
        r5.add(new ParmValuePair(r11, r83, r23, r24));     // Catch: CompileException -> L30
        r9 = r9 + 1;
        r11 = null;
        goto L46
    L32:
        r83 = (ExecutableStatement) ParseTools.subCompileExpression(r19, r10, r9 - r10, r24);     // Catch: CompileException -> L30
    L30:
        e = move-exception;
        e.setCursor(r10 + (e.getCursor() - (e.getExpr().length - r21)));
        e.setExpr(r19);
        throw e;
    L37:
        Sign$$ExternalSyntheticBUOutline0.m("operative assignment not possible here", r19, r20);
        return null;
    L55:
        if (r9 < r6) goto L57;
    L64:
        if (r9 < r6) goto L66;
    L77:
        if (r9 >= r6) goto L88;
        if (r19[r9 + 1] != '=') goto L88;
        r12 = 3;
        goto L88
    L66:
        if (r19[r9 + 1] != '*') goto L77;
        int r74 = r6 - 1;
    L68:
        if (r9 >= r74) goto L74;
        if (r19[r9] != '*') goto L73;
        if (r19[r9 + 1] == '/') goto L74;
    L73:
        r19[r9] = ' ';
        r9 = r9 + 1;
    L74:
        int r75 = r9 + 1;
        r19[r9] = ' ';
        r9 = r9 + 2;
        r19[r75] = ' ';
        if (r11 != null) goto L88;
    L63:
        r10 = r9;
        goto L88
    L57:
        if (r19[r9 + 1] != '/') goto L64;
    L58:
        if (r9 >= r6) goto L62;
        if (r19[r9] == '\n') goto L62;
        r19[r9] = ' ';
        r9 = r9 + 1;
    L62:
        if (r11 != null) goto L88;
    L86:
        r18 = r7;
        goto L87
    L89:
        String r182 = r7;
        if (r10 == r6) goto L113;
        if (r11 != null) goto L115;
    L106:
        if (r22 != null) goto L108;
        String r0 = new String(r19, r10, r6 - r10);     // Catch: CompileException -> L100
    L109:
        r5.add(new ParmValuePair(null, (ExecutableStatement) ParseTools.subCompileExpression(r0, r24), r23, r24));     // Catch: CompileException -> L100
        goto L113
    L108:
        r0 = r22 + '.' + new String(r19, r10, r6 - r10);     // Catch: CompileException -> L100
        goto L109
    L115:
    L100:
        e = move-exception;
        throw ErrorUtil.rewriteIfNeeded(e, r19, r10);
    L94:
        if (r182.equals(r11) == true) goto L106;
        if (r22 == null) goto L105;
        if (r12 == (-1)) goto L102;
        ExecutableStatement r2 = (ExecutableStatement) ParseTools.subCompileExpression(ParseTools.createShortFormOperativeAssignment(r22 + "." + r11, r19, r10, r6 - r10, r12), r24);     // Catch: CompileException -> L100
    L103:
        r5.add(new ParmValuePair(r11, r2, r23, r24));     // Catch: CompileException -> L100
        goto L113
    L102:
        r2 = (ExecutableStatement) ParseTools.subCompileExpression(r19, r10, r6 - r10, r24);     // Catch: CompileException -> L100
        goto L103
    L105:
        throw new CompileException("operative assignment not possible here", r19, r20);     // Catch: CompileException -> L100
    L113:
        ParmValuePair[] r02 = new ParmValuePair[r5.size()];
        r5.toArray(r02);
        return r02;
    }

    @Override // org.mvel2.ast.NestedStatement
    public ExecutableStatement getNestedStatement() {
        return this.compiledBlock;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r7, Object r8, VariableResolverFactory r9) {
        String r0 = this.nestParm;
        char[] r1 = this.expr;
        int r2 = this.blockStart;
        int r3 = this.blockOffset;
        Object r4 = MVEL.eval(r1, this.start, this.offset, r7, r9);
        ParseTools.parseWithExpressions(r0, r1, r2, r3, r4, r9);
        return r4;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r3, Object r4, VariableResolverFactory r5) {
        Object r32 = this.compiledBlock.getValue(r3, r4, r5);
        if (r32 == null) goto L8;
        ParmValuePair[] r2 = this.withExpressions;
        int r42 = r2.length;
        int r0 = 0;
    L5:
        if (r0 >= r42) goto L7;
        r2[r0].eval(r32, r5);
        r0 = r0 + 1;
        goto L5
    L7:
        return r32;
    L8:
        Sign$$ExternalSyntheticBUOutline0.m("with-block against null pointer", this.expr, this.start);
        return null;
    }

    public ParmValuePair[] getWithExpressions() {
        return this.withExpressions;
    }
}
