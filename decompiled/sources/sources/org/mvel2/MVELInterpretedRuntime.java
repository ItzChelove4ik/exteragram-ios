package org.mvel2;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.url._UrlKt;
import org.mvel2.ast.ASTNode;
import org.mvel2.ast.Sign$$ExternalSyntheticBUOutline0;
import org.mvel2.ast.Substatement;
import org.mvel2.compiler.AbstractParser;
import org.mvel2.compiler.BlankLiteral;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.ImmutableDefaultFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;
import org.mvel2.util.ErrorUtil;
import org.mvel2.util.ExecutionStack;

/* loaded from: classes5.dex */
public class MVELInterpretedRuntime extends AbstractParser {
    private static final Logger LOG = null;
    private Object holdOverRegister;

    static {
        LOG = Logger.getLogger(MVELInterpretedRuntime.class.getName());
    }

    public MVELInterpretedRuntime(String r1) {
        setExpression(r1);
        this.variableFactory = new ImmutableDefaultFactory();
    }

    public MVELInterpretedRuntime(String r1, Object r2) {
        setExpression(r1);
        this.ctx = r2;
        this.variableFactory = new ImmutableDefaultFactory();
    }

    public MVELInterpretedRuntime(String r1, Object r2, VariableResolverFactory r3) {
        setExpression(r1);
        this.ctx = r2;
        this.variableFactory = r3;
    }

    public MVELInterpretedRuntime(String r1, Object r2, VariableResolverFactory r3, ParserContext r4) {
        super(r4);
        setExpression(r1);
        this.ctx = r2;
        this.variableFactory = r3;
    }

    public MVELInterpretedRuntime(String r1, VariableResolverFactory r2) {
        setExpression(r1);
        this.variableFactory = r2;
        this.pCtx.initializeTables();
    }

    public MVELInterpretedRuntime(char[] r1) {
        this.expr = r1;
        int r12 = r1.length;
        this.end = r12;
        this.length = r12;
    }

    public MVELInterpretedRuntime(char[] r1, int r2, int r3, Object r4, VariableResolverFactory r5) {
        this.expr = r1;
        this.start = r2;
        int r32 = r3 + r2;
        this.end = r32;
        this.length = r32 - r2;
        this.ctx = r4;
        this.variableFactory = r5;
    }

    public MVELInterpretedRuntime(char[] r1, int r2, int r3, Object r4, VariableResolverFactory r5, ParserContext r6) {
        super(r6);
        this.expr = r1;
        this.start = r2;
        int r32 = r3 + r2;
        this.end = r32;
        this.length = r32 - r2;
        this.ctx = r4;
        this.variableFactory = r5;
    }

    public MVELInterpretedRuntime(char[] r1, Object r2) {
        this.expr = r1;
        this.length = r1.length;
        this.ctx = r2;
        this.variableFactory = new ImmutableDefaultFactory();
    }

    public MVELInterpretedRuntime(char[] r1, Object r2, Map<String, Object> r3) {
        this.expr = r1;
        this.length = r1.length;
        this.ctx = r2;
        this.variableFactory = new MapVariableResolverFactory(r3);
    }

    public MVELInterpretedRuntime(char[] r1, Object r2, VariableResolverFactory r3) {
        this.expr = r1;
        int r12 = r1.length;
        this.end = r12;
        this.length = r12;
        this.ctx = r2;
        this.variableFactory = r3;
    }

    private boolean hasMore() {
        if (this.cursor > this.end) goto L6;
        return true;
    L6:
        return false;
    }

    private Object parseAndExecuteInterpreted() {
        this.lastWasIdentifier = false;
        ASTNode r1 = null;
    L76:
        r1 = nextToken();     // Catch: NullPointerException -> L10 CompileException -> L12
        if (r1 == null) goto L63;
        this.holdOverRegister = null;     // Catch: NullPointerException -> L10 CompileException -> L12
        if (this.lastWasIdentifier == false) goto L15;
        if (this.lastNode.isDiscard() == false) goto L15;
        this.stk.discard();     // Catch: NullPointerException -> L10 CompileException -> L12
    L15:
        if (this.stk.isEmpty() == false) goto L34;
        int r2 = r1.fields & 4194304;
        ExecutionStack r4 = this.stk;
        if (r2 == 0) goto L22;
        r4.push(r1.getReducedValue(r4, this.ctx, this.variableFactory));     // Catch: NullPointerException -> L10 CompileException -> L12
        Object r22 = this.stk.peek();     // Catch: NullPointerException -> L10 CompileException -> L12
        if ((r22 instanceof Integer) == false) goto L24;
        arithmeticFunctionReduction(((Integer) r22).intValue());     // Catch: NullPointerException -> L10 CompileException -> L12
    L24:
        if ((r1 instanceof Substatement) == false) goto L76;
        r1 = nextToken();     // Catch: NullPointerException -> L10 CompileException -> L12
        if (r1 == null) goto L76;
        Integer r23 = r1.getOperator();     // Catch: NullPointerException -> L10 CompileException -> L12
        int r42 = r23.intValue();     // Catch: NullPointerException -> L10 CompileException -> L12
        if (AbstractParser.isArithmeticOperator(r42) == false) goto L34;
        ExecutionStack r5 = this.stk;     // Catch: NullPointerException -> L10 CompileException -> L12
        ASTNode r6 = nextToken();     // Catch: NullPointerException -> L10 CompileException -> L12
        Object r7 = this.ctx;     // Catch: NullPointerException -> L10 CompileException -> L12
        r5.push(r6.getReducedValue(r7, r7, this.variableFactory), r23);     // Catch: NullPointerException -> L10 CompileException -> L12
        if (procBooleanOperator(arithmeticFunctionReduction(r42)) != (-1)) goto L76;
        return this.stk.peek();
    L22:
        Object r24 = this.ctx;     // Catch: NullPointerException -> L10 CompileException -> L12
        r4.push(r1.getReducedValue(r24, r24, this.variableFactory));     // Catch: NullPointerException -> L10 CompileException -> L12
    L34:
        if (this.variableFactory.tiltFlag() == true) goto L36;
        Integer r25 = r1.getOperator();     // Catch: NullPointerException -> L10 CompileException -> L12
        int r43 = r25.intValue();     // Catch: NullPointerException -> L10 CompileException -> L12
        int r52 = procBooleanOperator(r43);     // Catch: NullPointerException -> L10 CompileException -> L12
        if (r52 != (-2)) goto L39;
        if (r1.isOperator() == true) goto L76;
        if ((this.stk.peek() instanceof Class) == false) goto L62;
        this.variableFactory.createVariable(r1.getName(), null, (Class) this.stk.peek());     // Catch: NullPointerException -> L10 CompileException -> L12
        goto L76
    L62:
        throw new CompileException("unexpected token or unknown identifier:" + r1.getName(), this.expr, this.st);     // Catch: NullPointerException -> L10 CompileException -> L12
    L39:
        if (r52 == (-1)) goto L55;
        if (r52 == 0) goto L76;
        if (r52 == 99) goto L52;
        ExecutionStack r53 = this.stk;     // Catch: NullPointerException -> L10 CompileException -> L12
        ASTNode r62 = nextToken();     // Catch: NullPointerException -> L10 CompileException -> L12
        Object r72 = this.ctx;     // Catch: NullPointerException -> L10 CompileException -> L12
        r53.push(r62.getReducedValue(r72, r72, this.variableFactory), r25);     // Catch: NullPointerException -> L10 CompileException -> L12
        int r26 = arithmeticFunctionReduction(r43);     // Catch: NullPointerException -> L10 CompileException -> L12
        if (r26 == (-1)) goto L51;
        if (r26 == 0) goto L76;
        if (procBooleanOperator(r26) != (-1)) goto L76;
        return this.stk.peek();
    L51:
        return this.stk.peek();
    L52:
        this.variableFactory.setTiltFlag(true);     // Catch: NullPointerException -> L10 CompileException -> L12
        return this.stk.pop();
    L55:
        return this.stk.peek();
    L36:
        return this.stk.pop();
    L63:
        Object r0 = this.holdOverRegister;     // Catch: NullPointerException -> L10 CompileException -> L12
        if (r0 == null) goto L67;
        return r0;
    L67:
        return this.stk.peek();
    L10:
        e = move-exception;
        if (r1 != null) goto L70;
    L73:
        throw e;
    L70:
        if (r1.isOperator() == false) goto L73;
        CompileException r27 = new CompileException("incomplete statement: " + r1.getName() + " (possible use of reserved keyword as identifier: " + r1.getName() + ")", this.expr, this.st, e);
        r27.setExpr(this.expr);
        r27.setLineNumber(this.line);
        r27.setCursor(this.cursor);
        throw r27;
    L12:
        e = move-exception;
        throw ErrorUtil.rewriteIfNeeded(e, this.expr, this.start);
    }

    private int procBooleanOperator(int r6) {
        if (r6 != (-1)) goto L5;
        return -2;
    L5:
        if (r6 == 37) goto L53;
        if (r6 == 99) goto L51;
        int r4 = 1;
        if (r6 == 29) goto L38;
        if (r6 == 30) goto L35;
        switch(r6) {
            case 21: goto L26;
            case 22: goto L17;
            case 23: goto L14;
            default: goto L16;
        };
    L17:
        reduceRight();
        if (this.stk.peekBoolean().booleanValue() == true) goto L20;
        this.stk.discard();
        return 0;
    L20:
        if (unwindStatement(r6) == false) goto L22;
        return -1;
    L22:
        this.stk.clear();
        return 0;
    L26:
        reduceRight();
        if (this.stk.peekBoolean().booleanValue() == false) goto L29;
        this.stk.discard();
        return 0;
    L29:
        if (unwindStatement(r6) == false) goto L31;
        return -1;
    L31:
        this.stk.clear();
        return 0;
    L16:
        return 1;
    L14:
        if (BlankLiteral.INSTANCE.equals(this.stk.peek()) == true) goto L16;
        return -1;
    L35:
        captureToEOS();
        return 0;
    L38:
        if (this.stk.popBoolean().booleanValue() == true) goto L50;
        this.stk.clear();
    L40:
        if (r4 <= 0) goto L50;
        ASTNode r62 = nextToken();
        if (r62 == null) goto L50;
        if (r62.isOperator(30) == false) goto L48;
        r4 = r4 - 1;
    L48:
        if (r62.isOperator(29) == false) goto L40;
        r4 = r4 + 1;
    L50:
        return 0;
    L51:
        return 99;
    L53:
        if (hasMore() == false) goto L55;
        this.holdOverRegister = this.stk.pop();
        this.stk.clear();
    L55:
        return 0;
    }

    private void reduceRight() {
        if (this.dStack.isEmpty() == false) goto L5;
        return;
    L5:
        Object r0 = this.stk.pop();
        this.stk.push(this.dStack.pop(), r0, this.dStack.pop());
        reduce();
    }

    private boolean unwindStatement(int r3) {
        if (r3 == 21) goto L9;
    L4:
        ASTNode r32 = nextToken();
        if (r32 == null) goto L16;
        if (r32.isOperator(37) == false) goto L4;
    L16:
        if (r32 != null) goto L19;
        return true;
    L19:
        return false;
    L9:
        r32 = nextToken();
        if (r32 == null) goto L16;
        if (r32.isOperator(37) == true) goto L16;
        if (r32.isOperator(22) == true) goto L16;
        goto L16
    }

    public Object parse() {
        this.stk = new ExecutionStack();     // Catch: CompileException -> L5 NullPointerException -> L7 ArrayIndexOutOfBoundsException -> L9
        this.dStack = new ExecutionStack();     // Catch: CompileException -> L5 NullPointerException -> L7 ArrayIndexOutOfBoundsException -> L9
        this.variableFactory.setTiltFlag(false);     // Catch: CompileException -> L5 NullPointerException -> L7 ArrayIndexOutOfBoundsException -> L9
        this.cursor = this.start;     // Catch: CompileException -> L5 NullPointerException -> L7 ArrayIndexOutOfBoundsException -> L9
        return parseAndExecuteInterpreted();
    L9:
        e = move-exception;
        LOG.log(Level.WARNING, _UrlKt.FRAGMENT_ENCODE_SET, e);
        Sign$$ExternalSyntheticBUOutline0.m("unexpected end of statement", this.expr, this.length);
        return null;
    L7:
        e = move-exception;
        LOG.log(Level.WARNING, _UrlKt.FRAGMENT_ENCODE_SET, e);
        int r1 = this.cursor;
        int r3 = this.length;
        if (r1 < r3) goto L18;
        Sign$$ExternalSyntheticBUOutline0.m("unexpected end of statement", this.expr, r3);
        return null;
    L18:
        throw e;
    L5:
        e = move-exception;
        throw ErrorUtil.rewriteIfNeeded(e, this.expr, this.cursor);
    }
}
