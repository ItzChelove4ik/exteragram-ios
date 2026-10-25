package org.mvel2.compiler;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.mvel2.CompileException;
import org.mvel2.DataConversion;
import org.mvel2.ErrorDetail;
import org.mvel2.MVEL;
import org.mvel2.Operator;
import org.mvel2.ParserConfiguration;
import org.mvel2.ParserContext;
import org.mvel2.ast.ASTNode;
import org.mvel2.ast.Assignment;
import org.mvel2.ast.DeepOperativeAssignmentNode;
import org.mvel2.ast.LiteralNode;
import org.mvel2.ast.Negation$$ExternalSyntheticBUOutline0;
import org.mvel2.ast.NewObjectNode;
import org.mvel2.ast.OperatorNode;
import org.mvel2.ast.Sign$$ExternalSyntheticBUOutline0;
import org.mvel2.ast.Substatement;
import org.mvel2.ast.Union;
import org.mvel2.util.ASTLinkedList;
import org.mvel2.util.CompilerTools;
import org.mvel2.util.ErrorUtil;
import org.mvel2.util.ExecutionStack;
import org.mvel2.util.ParseTools;
import org.mvel2.util.StringAppender;

/* loaded from: classes5.dex */
public class ExpressionCompiler extends AbstractParser {
    private Class returnType;
    private boolean secondPassOptimization;
    private boolean verifyOnly;
    private boolean verifying;

    public ExpressionCompiler(String r3) {
        this.verifyOnly = false;
        this.verifying = true;
        this.secondPassOptimization = false;
        setExpression(r3);
    }

    public ExpressionCompiler(String r3, int r4, int r5, ParserContext r6) {
        this.verifyOnly = false;
        this.verifying = true;
        this.secondPassOptimization = false;
        this.expr = r3.toCharArray();
        this.start = r4;
        int r52 = r5 + r4;
        this.end = r52;
        int r32 = trimLeft(r52);
        this.end = r32;
        this.length = r32 - r4;
        this.pCtx = r6;
    }

    public ExpressionCompiler(String r3, ParserContext r4) {
        this.verifyOnly = false;
        this.verifying = true;
        this.secondPassOptimization = false;
        setExpression(r3);
        this.pCtx = r4;
    }

    public ExpressionCompiler(String r3, boolean r4) {
        this.verifyOnly = false;
        this.verifying = true;
        this.secondPassOptimization = false;
        setExpression(r3);
        this.verifying = r4;
    }

    public ExpressionCompiler(char[] r3) {
        this.verifyOnly = false;
        this.verifying = true;
        this.secondPassOptimization = false;
        setExpression(r3);
    }

    public ExpressionCompiler(char[] r3, int r4, int r5) {
        this.verifyOnly = false;
        this.verifying = true;
        this.secondPassOptimization = false;
        this.expr = r3;
        this.start = r4;
        int r52 = r5 + r4;
        this.end = r52;
        int r32 = trimLeft(r52);
        this.end = r32;
        this.length = r32 - r4;
    }

    public ExpressionCompiler(char[] r3, int r4, int r5, ParserContext r6) {
        this.verifyOnly = false;
        this.verifying = true;
        this.secondPassOptimization = false;
        this.expr = r3;
        this.start = r4;
        int r52 = r5 + r4;
        this.end = r52;
        int r32 = trimLeft(r52);
        this.end = r32;
        this.length = r32 - r4;
        this.pCtx = r6;
    }

    public ExpressionCompiler(char[] r3, ParserContext r4) {
        this.verifyOnly = false;
        this.verifying = true;
        this.secondPassOptimization = false;
        setExpression(r3);
        this.pCtx = r4;
    }

    private boolean compileReduce(int r7, ASTLinkedList r8) {
        int r72 = arithmeticFunctionReduction(r7);
        if (r72 != (-3)) goto L5;
        ASTNode r73 = (ASTNode) this.stk.pop();
        Integer r0 = (Integer) this.stk.pop();
        r8.addTokenNode(new LiteralNode(getStackValueResult(), this.pCtx));
        r8.addTokenNode(new OperatorNode(r0, this.expr, this.st, this.pCtx), verify(this.pCtx, r73));
        return false;
    L5:
        if (r72 != (-2)) goto L7;
        LiteralNode r74 = new LiteralNode(this.stk.pop(), this.pCtx);
        OperatorNode r02 = new OperatorNode((Integer) this.stk.pop(), this.expr, this.st, this.pCtx);
        r8.addTokenNode(new LiteralNode(this.stk.pop(), this.pCtx), r02);
        r8.addTokenNode(r74, (OperatorNode) this.splitAccumulator.pop());
        r8.addTokenNode(verify(this.pCtx, (ASTNode) this.splitAccumulator.pop()));
        return false;
    L7:
        if (r72 == (-1)) goto L10;
        return true;
    L10:
        this.stk.xswap_op();
        r8.addTokenNode(new LiteralNode(this.stk.pop(), this.pCtx));
        r8.addTokenNode((OperatorNode) this.splitAccumulator.pop(), verify(this.pCtx, (ASTNode) this.splitAccumulator.pop()));
        return false;
    }

    private Object getStackValueResult() {
        int r0 = this.fields & 524288;
        Object r2 = this.stk.pop();
        if (r0 != 0) goto L6;
        return r2;
    L6:
        return CompilerTools.signNumber(r2);
    }

    private static boolean isBooleanOperator(int r1) {
        if (r1 != 21) goto L5;
        return true;
    L5:
        if (r1 != 22) goto L7;
        return true;
    L7:
        if (r1 != 29) goto L9;
        return true;
    L9:
        if (r1 == 30) goto L17;
        return false;
    L17:
        return true;
    }

    public CompiledExpression _compile() {
        this.cursor = this.start;
        ASTLinkedList r0 = new ASTLinkedList();
        this.stk = new ExecutionStack();
        this.dStack = new ExecutionStack();
        this.compileMode = true;
    L6:
        th = move-exception;
        if ((th instanceof RuntimeException) == true) goto L137;
        Negation$$ExternalSyntheticBUOutline0.m(th.getMessage(), this.expr, this.st, th);
        return null;
    L137:
        throw th;
    L10:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("not a statement, or badly formed structure", this.expr, this.st, e);
        return null;
    L8:
        e = move-exception;
        throw ErrorUtil.rewriteIfNeeded(e, this.expr, this.st);
    L4:
        if (this.verifying == false) goto L12;
        this.pCtx.initializeTables();     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
    L12:
        this.fields |= 16;
        int r5 = -1;
    L13:
        ASTNode r6 = nextToken();     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        if (r6 == null) goto L107;
        if (r6.fields == (-1)) goto L17;
        this.returnType = r6.getEgressType();     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        if ((r6 instanceof Substatement) == false) goto L25;
        String r8 = new String(this.expr, r6.getStart(), r6.getOffset());     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        Map<String, CompiledExpression> r9 = this.pCtx.getCompiledExpressionCache();     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        Map<String, Class> r10 = this.pCtx.getReturnTypeCache();     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        CompiledExpression r11 = r9.get(r8);     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        Class r12 = r10.get(r8);     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        if (r11 != null) goto L23;
        ExpressionCompiler r112 = new ExpressionCompiler(this.expr, r6.getStart(), r6.getOffset(), this.pCtx);     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        CompiledExpression r122 = r112._compile();     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        Class r113 = r112.getReturnType();     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        r9.put(r8, r122);     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        r10.put(r8, r113);     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        r12 = r113;
        r11 = r122;
    L23:
        r6.setAccessor(r11);     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        this.returnType = r12;     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
    L25:
        if (this.verifyOnly == true) goto L103;
        if (r6.isLiteral() == false) goto L103;
        if (this.literalOnly != (-1)) goto L31;
        this.literalOnly = 1;     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
    L31:
        ASTNode r82 = nextTokenSkipSymbols();     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        if (r82 == null) goto L91;
        if (r82.isOperator() == false) goto L91;
        if (r82.isOperator(29) == true) goto L91;
        if (r82.isOperator(30) == true) goto L91;
        ASTNode r92 = nextTokenSkipSymbols();     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        if (r92 == null) goto L88;
        if (r92.isLiteral() == false) goto L88;
        if (isBooleanOperator(r82.getOperator().intValue()) == true) goto L88;
        if (r82.getOperator().intValue() >= 34) goto L88;
        if (r5 == (-1)) goto L52;
        int[] r102 = Operator.PTABLE;     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        if (r5 >= r102.length) goto L88;
        if (r102[r5] >= r102[r82.getOperator().intValue()]) goto L88;
    L52:
        ExecutionStack r103 = this.stk;     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        Object r62 = r6.getLiteralValue();     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        Object r93 = r92.getLiteralValue();     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        Integer r83 = r82.getOperator();     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        int r114 = r83.intValue();     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        r103.push(r62, r93, r83);     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        if (AbstractParser.isArithmeticOperator(r114) == false) goto L57;
        if (compileReduce(r114, r0) == false) goto L13;
    L58:
        boolean r63 = true;
    L59:
        ASTNode r84 = nextTokenSkipSymbols();     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        if (r84 == null) goto L86;
        if (isBooleanOperator(r84.getOperator().intValue()) == true) goto L63;
        ASTNode r94 = nextTokenSkipSymbols();     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        if (r94 == null) goto L80;
        boolean r64 = r94.isLiteral();     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        ExecutionStack r104 = this.stk;
        if (r64 == false) goto L77;
        Object r65 = r94.getLiteralValue();     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        Integer r85 = r84.getOperator();     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        int r95 = r85.intValue();     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        r104.push(r65, r85);     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        if (AbstractParser.isArithmeticOperator(r95) == true) goto L72;
        reduce();     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
    L75:
        this.literalOnly = 0;     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        r63 = false;
        goto L59
    L72:
        if (compileReduce(r95, r0) == true) goto L75;
    L77:
        if (r104.isEmpty() == true) goto L79;
        r0.addTokenNode(new LiteralNode(getStackValueResult(), this.pCtx));     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
    L79:
        r0.addTokenNode(new OperatorNode(r84.getOperator(), this.expr, this.st, this.pCtx), verify(this.pCtx, r94));     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        goto L86
    L80:
        if (r63 == false) goto L82;
        r0.addTokenNode(new LiteralNode(getStackValueResult(), this.pCtx));     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        goto L86
    L82:
        r0.addTokenNode(new LiteralNode(getStackValueResult(), this.pCtx), r84);     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        if (r94 == null) goto L86;
        r0.addTokenNode(verify(this.pCtx, r94));     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        goto L86
    L63:
        r0.addTokenNode(new LiteralNode(this.stk.pop(), this.pCtx), verify(this.pCtx, r84));     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
    L86:
        if (this.stk.isEmpty() == true) goto L13;
        r0.addTokenNode(new LiteralNode(getStackValueResult(), this.pCtx));     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        goto L13
    L57:
        reduce();     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
    L88:
        r0.addTokenNode(verify(this.pCtx, r6), verify(this.pCtx, r82));     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        if (r92 == null) goto L13;
        r0.addTokenNode(verify(this.pCtx, r92));     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
    L91:
        if (r82 != null) goto L93;
    L99:
        this.literalOnly = 0;     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        r0.addTokenNode(verify(this.pCtx, r6));     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        if (r82 == null) goto L13;
        r0.addTokenNode(verify(this.pCtx, r82));     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        goto L13
    L93:
        if (r82.isOperator() == true) goto L99;
        if ((r6.getLiteralValue() instanceof Class) == true) goto L99;
        throw new CompileException("unexpected token: " + r82.getName(), this.expr, r82.getStart());     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
    L103:
        if (r6.isOperator() == false) goto L105;
        r5 = r6.getOperator().intValue();     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
    L106:
        r0.addTokenNode(verify(this.pCtx, r6));     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        goto L13
    L105:
        this.literalOnly = 0;     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        goto L106
    L17:
        r0.addTokenNode(r6);     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        goto L13
    L107:
        r0.finish();     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
        if (this.verifying == false) goto L113;
        if (this.verifyOnly == true) goto L113;
        this.pCtx.processTables();     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
    L113:
        if (this.stk.isEmpty() == false) goto L131;
        if (this.verifyOnly == true) goto L144;
        ASTLinkedList r96 = CompilerTools.finalizePayload(r0, this.secondPassOptimization, this.pCtx);     // Catch: Throwable -> L6 RuntimeException -> L122
        String r105 = this.pCtx.getSourceFile();     // Catch: Throwable -> L6 RuntimeException -> L122
        Class r115 = this.returnType;     // Catch: Throwable -> L6 RuntimeException -> L122
        ParserConfiguration r123 = this.pCtx.getParserConfiguration();     // Catch: Throwable -> L6 RuntimeException -> L122
        if (this.literalOnly != 1) goto L119;
        boolean r13 = true;
    L120:
        return new CompiledExpression(r96, r105, r115, r123, r13);
    L119:
        r13 = false;
    L122:
        e = move-exception;
        throw new CompileException(e.getMessage(), this.expr, this.st, e);     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
    L144:
        this.returnType = CompilerTools.getReturnType(r0, this.pCtx.isStrongTyping());     // Catch: Throwable -> L6 RuntimeException -> L127
        return null;
    L127:
        e = move-exception;
        throw new CompileException(e.getMessage(), this.expr, this.st, e);     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
    L131:
        throw new CompileException("COMPILE ERROR: non-empty stack after compileShared.", this.expr, this.cursor);     // Catch: Throwable -> L6 CompileException -> L8 NullPointerException -> L10
    }

    public CompiledExpression compile() {
        this.debugSymbols = this.pCtx.isDebugSymbols();     // Catch: Throwable -> L16
        CompiledExpression r0 = _compile();     // Catch: Throwable -> L16
        if (this.pCtx.isFatalError() == false) goto L15;
        StringAppender r02 = new StringAppender();
        Iterator<ErrorDetail> r9 = this.pCtx.getErrorList().iterator();
    L8:
        if (r9.hasNext() == false) goto L14;
        ErrorDetail r10 = ErrorUtil.rewriteIfNeeded(r9.next(), this.expr, this.cursor);
        if (r10.getExpr() != this.expr) goto L11;
        r02.append("\n - ").append("(").append(Integer.valueOf(r10.getLineNumber())).append(",").append(Integer.valueOf(r10.getColumn())).append(")").append(" ").append(r10.getMessage());
        goto L8
    L11:
        r9.remove();
        goto L8
    L14:
        throw new CompileException("Failed to compileShared: " + this.pCtx.getErrorList().size() + " compilation error(s): " + r02.toString(), this.pCtx.getErrorList(), this.expr, this.cursor, this.pCtx);
    L15:
        return r0;
    L16:
        th = move-exception;
        if (this.pCtx.isFatalError() == false) goto L28;
        StringAppender r03 = new StringAppender();
        Iterator<ErrorDetail> r92 = this.pCtx.getErrorList().iterator();
    L21:
        if (r92.hasNext() == false) goto L27;
        ErrorDetail r102 = ErrorUtil.rewriteIfNeeded(r92.next(), this.expr, this.cursor);
        if (r102.getExpr() != this.expr) goto L24;
        r03.append("\n - ").append("(").append(Integer.valueOf(r102.getLineNumber())).append(",").append(Integer.valueOf(r102.getColumn())).append(")").append(" ").append(r102.getMessage());
        goto L21
    L24:
        r92.remove();
        goto L21
    L27:
        throw new CompileException("Failed to compileShared: " + this.pCtx.getErrorList().size() + " compilation error(s): " + r03.toString(), this.pCtx.getErrorList(), this.expr, this.cursor, this.pCtx);
    L28:
        throw th;
    }

    public ParserContext getParserContextState() {
        return this.pCtx;
    }

    public Class getReturnType() {
        return this.returnType;
    }

    public boolean isLiteralOnly() {
        if (this.literalOnly != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public boolean isVerifyOnly() {
        return this.verifyOnly;
    }

    public boolean isVerifying() {
        return this.verifying;
    }

    public void setReturnType(Class r1) {
        this.returnType = r1;
    }

    public void setVerifyOnly(boolean r1) {
        this.verifyOnly = r1;
    }

    public void setVerifying(boolean r1) {
        this.verifying = r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ASTNode verify(ParserContext r7, ASTNode r8) {
        if (r8.isOperator() == false) goto L10;
        if (r8.getOperator().equals(21) == false) goto L7;
    L8:
        this.secondPassOptimization = true;
        goto L10
    L7:
        if (r8.getOperator().equals(22) == true) goto L8;
    L10:
        if (r8.isDiscard() == false) goto L12;
    L105:
        return r8;
    L12:
        if (r8.isOperator() == true) goto L105;
        if (r8.isLiteral() == false) goto L23;
        if ((this.fields & 16) == 0) goto L105;
        if (r8.getClass() != ASTNode.class) goto L105;
        return new LiteralNode(r8.getLiteralValue(), r7);
    L23:
        if (this.verifying == false) goto L96;
        if (r8.isIdentifier() == false) goto L59;
        PropertyVerifier r0 = new PropertyVerifier(this.expr, r8.getStart(), r8.getOffset(), r7);
        if ((r8 instanceof Union) == false) goto L29;
        r0.setCtx(((Union) r8).getLeftEgressType());
        Class r02 = r0.analyze();
        this.returnType = r02;
        r8.setEgressType(r02);
    L94:
        this.returnType = r8.getEgressType();
        goto L96
    L29:
        Class r3 = r0.analyze();
        this.returnType = r3;
        r8.setEgressType(r3);
        if (r0.isFqcn() == false) goto L33;
        r8.setAsFQCNReference();
    L33:
        if (r0.isClassLiteral() == false) goto L37;
        return new LiteralNode(this.returnType, r7);
    L37:
        if (r0.isInput() == false) goto L44;
        String r32 = r8.getAbsoluteName();
        if (r0.isDeepProperty() == false) goto L41;
        Class<Object> r4 = Object.class;
    L42:
        r7.addInput(r32, r4);
        goto L44
    L41:
        r4 = this.returnType;
    L44:
        if (r0.isMethodCall() == true) goto L94;
        if (this.returnType.isEnum() == true) goto L94;
        if (r7.isOptimizerNotified() == true) goto L94;
        if (r7.isStrongTyping() == false) goto L94;
        if (r7.isVariableVisible(r8.getAbsoluteName()) == true) goto L94;
        if (r8.isFQCN() == true) goto L94;
        Sign$$ExternalSyntheticBUOutline0.m("no such identifier: " + r8.getAbsoluteName(), this.expr, r8.getStart());
        return null;
    L59:
        if (r8.isAssignment() == false) goto L87;
        Assignment r03 = (Assignment) r8;
        if (r03.getAssignmentVar() == null) goto L94;
        PropertyVerifier r33 = new PropertyVerifier(r03.getAssignmentVar(), r7);
        Class r42 = r33.analyze();
        this.returnType = r42;
        r8.setEgressType(r42);
        if (r03.isNewDeclaration() == false) goto L65;
    L67:
        ExecutableStatement r34 = (ExecutableStatement) ParseTools.subCompileExpression(this.expr, r8.getStart(), r8.getOffset(), r7);
        if (r7.isStrictTypeEnforcement() == false) goto L94;
        if (this.returnType.isAssignableFrom(r34.getKnownEgressType()) == true) goto L94;
        if (r34.isLiteralOnly() == false) goto L94;
        if (DataConversion.canConvert(r34.getKnownEgressType(), this.returnType) == false) goto L80;
        if ((r03 instanceof DeepOperativeAssignmentNode) == true) goto L105;
        r03.setValueStatement(new ExecutableLiteral(DataConversion.convert(r34.getValue(null, null), this.returnType)));     // Catch: Exception -> L106
        return r8;
    L84:
        Sign$$ExternalSyntheticBUOutline0.m("cannot assign type " + r34.getKnownEgressType().getName() + " to " + this.returnType.getName(), this.expr, this.st);
        return null;
    L80:
        if (this.returnType.isPrimitive() == false) goto L84;
        if (ParseTools.unboxPrimitive(r34.getKnownEgressType()).equals(this.returnType) == false) goto L84;
    L65:
        if (r33.isResolvedExternally() == false) goto L67;
        r7.addInput(r8.getAbsoluteName(), this.returnType);
        goto L67
    L87:
        if ((r8 instanceof NewObjectNode) == false) goto L94;
        NewObjectNode r04 = (NewObjectNode) r8;
        List<char[]> r05 = ParseTools.parseMethodOrConstructor(r8.getNameAsArray());
        if (r05 == null) goto L94;
        Iterator<char[]> r06 = r05.iterator();
    L92:
        if (r06.hasNext() == false) goto L94;
        MVEL.analyze(r06.next(), r7);
    L96:
        if (r8.isLiteral() == true) goto L105;
        if (r8.getClass() != ASTNode.class) goto L105;
        if ((r8.getFields() & 67108864) != 0) goto L105;
        if (r7.isStrongTyping() == false) goto L104;
        r8.strongTyping();
    L104:
        r8.storePctx();
        r8.storeInLiteralRegister(r7);
        goto L105
    }
}
