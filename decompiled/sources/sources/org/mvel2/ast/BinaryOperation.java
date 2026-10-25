package org.mvel2.ast;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.mvel2.CompileException;
import org.mvel2.DataConversion;
import org.mvel2.ParserContext;
import org.mvel2.ScriptRuntimeException;
import org.mvel2.debug.DebugTools;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.math.MathProcessor;
import org.mvel2.util.CompatibilityStrategy;
import org.mvel2.util.CompilerTools;
import org.mvel2.util.NullType;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class BinaryOperation extends BooleanNode {
    private int lType;
    private final int operation;
    private int rType;

    public BinaryOperation(int r1, ParserContext r2) {
        super(r2);
        this.lType = -1;
        this.rType = -1;
        this.operation = r1;
    }

    public BinaryOperation(int r3, ASTNode r4, ASTNode r5, ParserContext r6) {
        super(r6);
        this.lType = -1;
        this.rType = -1;
        this.operation = r3;
        this.left = r4;
        if (r4 == null) goto L63;
        this.right = r5;
        if (r5 == null) goto L61;
        if (r3 == 0) goto L9;
    L13:
        this.egressType = CompilerTools.getReturnTypeFromOp(r3, this.left.egressType, this.right.egressType);
        if (r6.isStrongTyping() == false) goto L47;
        boolean r62 = r4.getEgressType().isAssignableFrom(r5.getEgressType());
        boolean r0 = r5.getEgressType().isAssignableFrom(r4.getEgressType());
        if (r62 == true) goto L47;
        if (r0 == true) goto L47;
        boolean r63 = doesRequireConversion(r4.getEgressType(), r5.getEgressType(), r3);
        if (r5.isLiteral() == false) goto L30;
        if (r63 == false) goto L30;
        if (DataConversion.canConvert(r4.getEgressType(), r5.getEgressType()) == false) goto L30;
        if (isAritmeticOperation(r3) == false) goto L27;
        Class r32 = this.egressType;
    L28:
        this.right = new LiteralNode(DataConversion.convert(r5.getReducedValueAccelerated(null, null, null), r32), r32, this.pCtx);
        goto L47
    L27:
        r32 = r4.getEgressType();
    L30:
        if (areCompatible(r4.getEgressType(), r5.getEgressType()) == true) goto L47;
        if (r3 == 18) goto L36;
        if (r3 == 19) goto L36;
    L37:
        String r33 = "incompatible types in statement: " + r5.getEgressType() + " (compared from: " + r4.getEgressType() + ")";
        if (r4.getExpr() == null) goto L40;
        char[] r64 = r4.getExpr();
    L42:
        if (r4.getExpr() == null) goto L44;
        int r42 = r4.getStart();
    L46:
        throw new CompileException(r33, r64, r42);
    L44:
        r42 = r5.getStart();
        goto L46
    L40:
        r64 = r5.getExpr();
    L36:
        if (CompatibilityStrategy.areEqualityCompatible(r4.getEgressType(), r5.getEgressType()) == false) goto L37;
    L47:
        ASTNode r34 = this.left;
        if (r34.egressType != this.right.egressType) goto L52;
        int r35 = getOperandType(r34);
        this.rType = r35;
        this.lType = r35;
        return;
    L52:
        if (r34.isLiteral() == false) goto L55;
        this.lType = getOperandType(this.left);
    L55:
        if (this.right.isLiteral() == false) goto L64;
        this.rType = getOperandType(this.right);
        return;
    L64:
        return;
    L9:
        if (r4.getEgressType() != String.class) goto L11;
    L58:
        this.egressType = String.class;
        this.lType = ParseTools.__resolveType(r4.egressType);
        this.rType = ParseTools.__resolveType(r5.egressType);
        return;
    L11:
        if (r5.getEgressType() != String.class) goto L13;
    L61:
        throw new ScriptRuntimeException("not a statement");
    L63:
        throw new ScriptRuntimeException("not a statement");
    }

    private boolean areCompatible(Class<?> r2, Class<?> r3) {
        if (r2.equals(NullType.class) == false) goto L5;
        return true;
    L5:
        if (r3.equals(NullType.class) == false) goto L7;
        return true;
    L7:
        if (Number.class.isAssignableFrom(r3) == false) goto L11;
        if (Number.class.isAssignableFrom(r2) == false) goto L11;
        return true;
    L11:
        if (r3.isPrimitive() == true) goto L15;
        if (r2.isPrimitive() == true) goto L15;
        return false;
    L15:
        if (DataConversion.canConvert(ParseTools.boxPrimitive(r2), ParseTools.boxPrimitive(r3)) == true) goto L24;
        return false;
    L24:
        return true;
    }

    private boolean doesRequireConversion(Class r1, Class r2, int r3) {
        if (r1 == Short.class) goto L17;
        if (r1 == Short.TYPE) goto L17;
        if (r1 == Integer.class) goto L17;
        if (r1 == Integer.TYPE) goto L17;
        if (r1 == Long.class) goto L17;
        if (r1 == Long.TYPE) goto L17;
        if (r1 == BigInteger.class) goto L17;
        return true;
    L17:
        if (r2 != Float.class) goto L19;
        return false;
    L19:
        if (r2 != Float.TYPE) goto L21;
        return false;
    L21:
        if (r2 != Double.class) goto L23;
        return false;
    L23:
        if (r2 != Double.TYPE) goto L25;
        return false;
    L25:
        if (r2 == BigDecimal.class) goto L35;
        return true;
    L35:
        return false;
    }

    private int getOperandType(ASTNode r1) {
        Class r0 = r1.egressType;
        if (r0 != null) goto L5;
        return -1;
    L5:
        if (r0 != Object.class) goto L8;
        return -1;
    L8:
        return ParseTools.__resolveType(r0);
    }

    private boolean isAritmeticOperation(int r1) {
        if (r1 > 5) goto L6;
        return true;
    L6:
        return false;
    }

    public int getOperation() {
        return this.operation;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r1, Object r2, VariableResolverFactory r3) {
        throw new RuntimeException("unsupported AST operation");
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r5, Object r6, VariableResolverFactory r7) {
        return MathProcessor.doOperations(this.lType, this.left.getReducedValueAccelerated(r5, r6, r7), this.operation, this.rType, this.right.getReducedValueAccelerated(r5, r6, r7));
    }

    @Override // org.mvel2.ast.BooleanNode
    public ASTNode getRightMost() {
    L2:
        ASTNode r1 = this.right;
        if (r1 == null) goto L7;
        if ((r1 instanceof BinaryOperation) == false) goto L7;
        BinaryOperation this = (BinaryOperation) r1;
    L7:
        return r1;
    }

    @Override // org.mvel2.ast.ASTNode
    public boolean isLiteral() {
        return false;
    }

    @Override // org.mvel2.ast.BooleanNode
    public void setRightMost(ASTNode r4) {
        BinaryOperation r0 = this;
    L3:
        ASTNode r1 = r0.right;
        if (r1 == null) goto L8;
        if ((r1 instanceof BinaryOperation) == false) goto L8;
        r0 = (BinaryOperation) r1;
    L8:
        r0.right = r4;
        if (r0 != this) goto L16;
        int r42 = ParseTools.__resolveType(r4.getEgressType());
        this.rType = r42;
        if (r42 != 0) goto L17;
        this.rType = -1;
        return;
    L17:
        return;
    }

    @Override // org.mvel2.ast.ASTNode
    public String toString() {
        return "(" + this.left + " " + DebugTools.getOperatorSymbol(this.operation) + " " + this.right + ")";
    }
}
