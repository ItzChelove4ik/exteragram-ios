package org.mvel2.util;

import java.util.LinkedHashMap;
import java.util.Map;
import org.mvel2.CompileException;
import org.mvel2.Operator;
import org.mvel2.ParserContext;
import org.mvel2.ast.ASTNode;
import org.mvel2.ast.And;
import org.mvel2.ast.BinaryOperation;
import org.mvel2.ast.BooleanNode;
import org.mvel2.ast.Contains;
import org.mvel2.ast.Convertable;
import org.mvel2.ast.DeclTypedVarNode;
import org.mvel2.ast.Function;
import org.mvel2.ast.Instance;
import org.mvel2.ast.Instance$$ExternalSyntheticBUOutline0;
import org.mvel2.ast.IntAdd;
import org.mvel2.ast.IntDiv;
import org.mvel2.ast.IntMult;
import org.mvel2.ast.IntOptimized;
import org.mvel2.ast.IntSub;
import org.mvel2.ast.LiteralNode;
import org.mvel2.ast.Or;
import org.mvel2.ast.RegExMatchNode;
import org.mvel2.ast.Sign$$ExternalSyntheticBUOutline0;
import org.mvel2.ast.Soundslike;
import org.mvel2.ast.Strsim;
import org.mvel2.compiler.Accessor;
import org.mvel2.compiler.BlankLiteral;
import org.mvel2.compiler.CompiledExpression;
import org.mvel2.compiler.ExecutableAccessor;
import org.mvel2.compiler.ExecutableLiteral;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.ClassImportResolverFactory;

/* loaded from: classes5.dex */
public class CompilerTools {
    public CompilerTools() {
    }

    private static BinaryOperation boOptimize(int r2, ASTNode r3, ASTNode r4, ParserContext r5) {
        if (r3.getEgressType() != Integer.class) goto L24;
        if (r4.getEgressType() != Integer.class) goto L24;
        if (r2 == 0) goto L22;
        if (r2 == 1) goto L20;
        if (r2 == 2) goto L18;
        if (r2 == 3) goto L16;
        return new BinaryOperation(r2, r3, r4, r5);
    L16:
        return new IntDiv(r3, r4, r5);
    L18:
        return new IntMult(r3, r4, r5);
    L20:
        return new IntSub(r3, r4, r5);
    L22:
        return new IntAdd(r3, r4, r5);
    L24:
        return new BinaryOperation(r2, r3, r4, r5);
    }

    public static void expectType(ParserContext r5, ASTNode r6, Class r7, boolean r8) {
        Class<?> r62 = ParseTools.boxPrimitive(r6.getEgressType());
        String r0 = "<Unknown>";
        if (r8 == false) goto L16;
        if (r62 == null) goto L8;
        if (ParseTools.boxPrimitive(r7).isAssignableFrom(r62) == false) goto L8;
        return;
    L8:
        if (Object.class.equals(r62) == false) goto L10;
        return;
    L10:
        if (r5.isStrictTypeEnforcement() == false) goto L29;
        StringBuilder r82 = new StringBuilder("was expecting type: ");
        r82.append(r7.getName());
        r82.append("; but found type: ");
        if (r62 == null) goto L14;
        r0 = r62.getName();
    L14:
        r82.append(r0);
        throw new CompileException(r82.toString(), new char[0], 0);
    L29:
        return;
    L16:
        if (r62 != null) goto L18;
    L23:
        StringBuilder r83 = new StringBuilder("was expecting type: ");
        r83.append(r7.getName());
        r83.append("; but found type: ");
        if (r62 == null) goto L26;
        r0 = r62.getName();
    L26:
        r83.append(r0);
        throw new CompileException(r83.toString(), new char[0], 0);
    L18:
        if (Object.class.equals(r62) == false) goto L20;
        return;
    L20:
        if (ParseTools.boxPrimitive(r7).isAssignableFrom(r62) == false) goto L23;
    }

    public static void expectType(ParserContext r6, Accessor r7, Class r8, boolean r9) {
        Class r72 = r7.getKnownEgressType();
        String r0 = "<Unknown>";
        if (r9 == false) goto L16;
        if (r72 == null) goto L8;
        if (ParseTools.boxPrimitive(r8).isAssignableFrom(ParseTools.boxPrimitive(r72)) == false) goto L8;
        return;
    L8:
        if (Object.class.equals(r72) == true) goto L10;
    L11:
        StringBuilder r92 = new StringBuilder("was expecting type: ");
        r92.append(r8.getName());
        r92.append("; but found type: ");
        if (r72 == null) goto L14;
        r0 = r72.getName();
    L14:
        r92.append(r0);
        throw new CompileException(r92.toString(), new char[0], 0);
    L10:
        if (r6.isStrictTypeEnforcement() == true) goto L11;
        return;
    L16:
        if (r72 != null) goto L18;
    L23:
        StringBuilder r93 = new StringBuilder("was expecting type: ");
        r93.append(r8.getName());
        r93.append("; but found type: ");
        if (r72 == null) goto L26;
        r0 = r72.getName();
    L26:
        r93.append(r0);
        throw new CompileException(r93.toString(), new char[0], 0);
    L18:
        if (Object.class.equals(r72) == false) goto L20;
        return;
    L20:
        if (ParseTools.boxPrimitive(r8).isAssignableFrom(ParseTools.boxPrimitive(r72)) == false) goto L23;
    }

    public static Accessor extractAccessor(ASTNode r2) {
        if ((r2 instanceof LiteralNode) == false) goto L7;
        return new ExecutableLiteral(r2.getLiteralValue());
    L7:
        return new ExecutableAccessor(r2, r2.getEgressType());
    }

    public static Map<String, Function> extractAllDeclaredFunctions(CompiledExpression r3) {
        LinkedHashMap r0 = new LinkedHashMap();
        ASTLinkedList r1 = new ASTLinkedList(r3.getFirstNode());
    L4:
        if (r1.hasMoreNodes() == false) goto L8;
        ASTNode r32 = r1.nextNode();
        if ((r32 instanceof Function) == false) goto L4;
        r0.put(r32.getName(), (Function) r32);
        goto L4
    L8:
        return r0;
    }

    public static ASTLinkedList finalizePayload(ASTLinkedList r18, boolean r19, ParserContext r20) {
        ParserContext r5 = r20;
        ASTLinkedList r4 = new ASTLinkedList();
    L4:
        if (r18.hasMoreNodes() == false) goto L133;
        ASTNode r0 = r18.nextNode();
        if (r0.getFields() == (-1)) goto L7;
        if (r18.hasMoreNodes() == true) goto L10;
        r4.addTokenNode(r0);
        r5 = r5;
        goto L4
    L10:
        ASTNode r6 = r18.nextNode();
        if (r6.getFields() == (-1)) goto L12;
        if (r6.isOperator() == false) goto L109;
        if (r6.getOperator().intValue() >= 21) goto L109;
        int r7 = r6.getOperator().intValue();
        if (r7 == (-1)) goto L106;
        ASTNode r8 = r18.nextNode();
        if (r0.getEgressType() == Integer.class) goto L22;
    L24:
        boolean r10 = r6.isOperator(1);
        if (r0.isLiteral() == true) goto L27;
    L29:
        boolean r11 = false;
    L30:
        boolean r13 = r11;
        boolean r14 = false;
        boolean r112 = r10;
        ASTNode r102 = r8;
        BinaryOperation r82 = null;
    L31:
        if (r13 == false) goto L61;
        ASTNode r15 = r18.nextNode();
        ASTNode r9 = r18.nextNode();
        if (r9 == null) goto L61;
        int r152 = r15.getOperator().intValue();
        if (r112 == false) goto L38;
        ASTNode r2 = new LiteralNode(signNumber(r102.getLiteralValue()), r5);
    L39:
        Object r22 = new BinaryOperation(r152, r2, r9, r5).getReducedValueAccelerated(null, null, null);
        if (r18.hasMoreNodes() == true) goto L45;
        if (BlankLiteral.INSTANCE.equals(r22) == false) goto L45;
        r4.addTokenNode(r0);
    L45:
        if (r18.hasMoreNodes() == true) goto L47;
    L51:
        r13 = false;
    L52:
        if (r112 == false) goto L54;
        r14 = true;
    L54:
        if (r13 == true) goto L59;
        int r3 = r6.getOperator().intValue();
        if (r14 == false) goto L58;
        r22 = signNumber(r22);
    L58:
        r82 = new BinaryOperation(r3, r0, new LiteralNode(r22, r5), r5);
    L60:
        r112 = false;
        goto L31
    L59:
        r102 = new LiteralNode(r22, r5);
        goto L60
    L47:
        if (reducacbleOperator(r18.peekNode().getOperator().intValue()) == false) goto L51;
        if (r18.peekNext().isLiteral() == false) goto L51;
        r13 = true;
        goto L52
    L38:
        r2 = r102;
    L61:
        if (r82 != null) goto L63;
        BinaryOperation r02 = new BinaryOperation(r7, r0, r102, r5);
    L64:
        BinaryOperation r1 = r02;
        ASTNode r03 = null;
    L66:
        if (r18.hasMoreNodes() == false) goto L100;
        r03 = r18.nextNode();
        if (r03.isOperator() == false) goto L100;
        if (r03.getFields() == (-1)) goto L100;
        int r23 = r03.getOperator().intValue();
        if (r23 == (-1)) goto L100;
        if (r23 >= 21) goto L100;
        int[] r32 = Operator.PTABLE;
        if (r32[r23] <= r32[r7]) goto L82;
        BinaryOperation r33 = boOptimize(r23, r1.getRightMost(), r18.nextNode(), r5);
        if (isIntOptimizationviolation(r1, r33) == false) goto L80;
        r1 = new BinaryOperation(r1.getOperation(), r1.getLeft(), r33, r5);
    L99:
        r6 = r03;
        r7 = r23;
        goto L66
    L80:
        r1.setRightMost(r33);
        goto L99
    L82:
        if (r1.getOperation() == r23) goto L93;
        if (r32[r7] != r32[r23]) goto L93;
        if (r32[r1.getOperation()] != r32[r23]) goto L88;
        r1 = boOptimize(r23, r1, r18.nextNode(), r5);
        goto L99
    L88:
        ASTNode r34 = r18.nextNode();
        if (isIntOptimizationviolation(r1, r34) == false) goto L91;
        r1 = new BinaryOperation(r1.getOperation(), r1.getLeft(), r1.getRight(), r5);
    L91:
        r1.setRight(new BinaryOperation(r23, r1.getRight(), r34, r5));
    L93:
        if (r32[r1.getOperation()] < r32[r23]) goto L95;
        r1 = new BinaryOperation(r23, r1, r18.nextNode(), r5);
        goto L99
    L95:
        ASTNode r35 = r18.nextNode();
        if (isIntOptimizationviolation(r1, r35) == false) goto L98;
        r1 = new BinaryOperation(r1.getOperation(), r1.getLeft(), r1.getRight(), r5);
    L98:
        r1.setRight(new BinaryOperation(r23, r1.getRight(), r35, r5));
    L100:
        ASTNode r24 = r03;
        if (r24 == null) goto L104;
        if (r24 == r6) goto L104;
        optimizeOperator(r24.getOperator().intValue(), r1, r24, r18, r4, r5);
    L105:
        r5 = r20;
    L104:
        r4.addTokenNode(r1);
        goto L105
    L63:
        r02 = r82;
        goto L64
    L27:
        if (isReductionOpportunity(r6, r8) == false) goto L29;
        r11 = true;
        goto L30
    L22:
        if (r8.getEgressType() != Integer.class) goto L24;
        r02 = boOptimize(r7, r0, r8, r5);
        goto L64
    L106:
        Sign$$ExternalSyntheticBUOutline0.m("illegal use of operator: " + r6.getName(), r6.getExpr(), r0.getStart());
        return null;
    L109:
        if (r6.isOperator() == true) goto L110;
        ASTLinkedList r83 = r4;
        if (r6.isAssignment() == false) goto L114;
    L119:
        ParserContext r72 = r20;
        if (r6.isAssignment() == false) goto L125;
        if ((r0.getLiteralValue() instanceof Class) == false) goto L125;
        r0.discard();
        r83.addTokenNode(r6);
    L118:
        r5 = r72;
        r4 = r83;
    L125:
        if (r18.hasMoreNodes() == true) goto L127;
    L131:
        r18.back();
        r83.addTokenNode(r0);
        goto L118
    L127:
        if ((r6.getLiteralValue() instanceof Class) == false) goto L131;
        if (r18.peekNode().isAssignment() == false) goto L131;
        r6.discard();
        r83.addTokenNode(r0, r18.nextNode());
        goto L118
    L114:
        if (r6.isOperator() == true) goto L119;
        if ((r0.getLiteralValue() instanceof Class) == false) goto L119;
        r72 = r20;
        r83.addTokenNode(new DeclTypedVarNode(r6.getName(), r6.getExpr(), r6.getStart(), r0.getOffset(), (Class) r0.getLiteralValue(), 0, r72));
        goto L118
    L110:
        r5 = r20;
        optimizeOperator(r6.getOperator().intValue(), r0, r6, r18, r4, r5);
        goto L4
    L12:
        r4.addTokenNode(r0, r6);
        goto L4
    L7:
        r4.addTokenNode(r0);
        goto L4
    L133:
        ASTLinkedList r84 = r4;
        ParserContext r73 = r5;
        if (r19 == false) goto L179;
        r84.reset();
        ASTLinkedList r04 = new ASTLinkedList();
    L137:
        if (r84.hasMoreNodes() == false) goto L178;
        ASTNode r25 = r84.nextNode();
        if (r25.getFields() == (-1)) goto L140;
        if (r84.hasMoreNodes() == true) goto L143;
        r04.addTokenNode(r25);
        goto L137
    L143:
        ASTNode r36 = r84.nextNode();
        if (r36.getFields() == (-1)) goto L145;
        if (r36.isOperator() == false) goto L176;
        if (r36.getOperator().intValue() == 21) goto L153;
        if (r36.getOperator().intValue() != 22) goto L176;
    L153:
        if (r36.getOperator().intValue() != 21) goto L156;
        BooleanNode r52 = new And(r25, r84.nextNode(), r73.isStrongTyping(), r73);
    L155:
        ASTNode r26 = null;
    L158:
        if (r84.hasMoreNodes() == false) goto L171;
        r26 = r84.nextNode();
        if (r26.isOperator() == false) goto L171;
        if (r26.isOperator(21) == true) goto L166;
        if (r26.isOperator(22) == false) goto L171;
    L166:
        if (r26.getOperator().intValue() != 21) goto L169;
        r52.setRightMost(new And(r52.getRightMost(), r84.nextNode(), r73.isStrongTyping(), r73));
    L168:
        r36 = r26;
        goto L158
    L169:
        r52 = new Or(r52, r84.nextNode(), r73.isStrongTyping(), r73);
    L171:
        r04.addTokenNode(r52);
        if (r26 == null) goto L137;
        if (r26 == r36) goto L137;
        r04.addTokenNode(r26);
        goto L137
    L156:
        r52 = new Or(r25, r84.nextNode(), r73.isStrongTyping(), r73);
    L176:
        r04.addTokenNode(r25, r36);
        goto L137
    L145:
        r04.addTokenNode(r25, r36);
        goto L137
    L140:
        r04.addTokenNode(r25);
        goto L137
    L178:
        return r04;
    L179:
        return r84;
    }

    public static Map<String, Object> getInjectedImports(VariableResolverFactory r2) {
        if (r2 != null) goto L6;
        return null;
    L6:
        if ((r2 instanceof ClassImportResolverFactory) == true) goto L8;
        r2 = r2.getNextFactory();
        if (r2 != null) goto L6;
        return null;
    L8:
        return ((ClassImportResolverFactory) r2).getImportedClasses();
    }

    public static Class getReturnType(ASTIterator r3, boolean r4) {
        ASTNode r0 = r3.firstNode();
        if (r0 != null) goto L7;
        return Object.class;
    L7:
        if (r3.size() != 1) goto L11;
        return r0.getEgressType();
    L11:
        return ASTBinaryTree.buildTree(r3).getReturnType(r4);
    }

    public static Class getReturnTypeFromOp(int r1, Class r2, Class r3) {
        if (r1 != 26) goto L5;
        return Boolean.class;
    L5:
        if (r1 != 36) goto L7;
        return Boolean.class;
    L7:
        switch(r1) {
            case 0: goto L13;
            case 1: goto L16;
            case 2: goto L16;
            case 3: goto L16;
            case 4: goto L16;
            case 5: goto L16;
            case 6: goto L11;
            case 7: goto L11;
            case 8: goto L11;
            case 9: goto L11;
            case 10: goto L11;
            case 11: goto L11;
            case 12: goto L11;
            case 13: goto L11;
            case 14: goto L27;
            case 15: goto L27;
            case 16: goto L27;
            case 17: goto L27;
            case 18: goto L27;
            case 19: goto L27;
            case 20: goto L10;
            case 21: goto L27;
            case 22: goto L27;
            default: goto L8;
        };
    L8:
        return null;
    L10:
        return String.class;
    L11:
        return Integer.class;
    L13:
        if (r2 != String.class) goto L16;
        return String.class;
    L27:
        return Boolean.class;
    L16:
        if (r2 == Object.class) goto L23;
        if (r3 == Object.class) goto L23;
        if (ParseTools.__resolveType(ParseTools.boxPrimitive(r2)) >= ParseTools.__resolveType(ParseTools.boxPrimitive(r3))) goto L22;
        return r3;
    L22:
        return r2;
    L23:
        return Object.class;
    }

    private static boolean isIntOptimizationviolation(BooleanNode r0, ASTNode r1) {
        if ((r0 instanceof IntOptimized) == true) goto L5;
        return false;
    L5:
        if (r1.getEgressType() == Integer.class) goto L10;
        return true;
    L10:
        return false;
    }

    private static boolean isReductionOpportunity(ASTNode r2, ASTNode r3) {
        if (r3 != null) goto L4;
        return false;
    L4:
        if (r3.isLiteral() == false) goto L21;
        ASTNode r32 = r3.nextASTNode;
        if (r32 != null) goto L8;
        return false;
    L8:
        if (reducacbleOperator(r32.getOperator().intValue()) == false) goto L23;
        int[] r0 = Operator.PTABLE;
        if (r0[r2.getOperator().intValue()] > r0[r32.getOperator().intValue()]) goto L24;
        ASTNode r22 = r32.nextASTNode;
        if (r22 != null) goto L14;
        return false;
    L14:
        if (r22.isLiteral() == true) goto L16;
        return false;
    L16:
        if ((r22.getLiteralValue() instanceof Number) == false) goto L27;
        return true;
    L27:
        return false;
    L24:
        return false;
    L23:
        return false;
    L21:
        return false;
    }

    private static void optimizeOperator(int r1, ASTNode r2, ASTNode r3, ASTLinkedList r4, ASTLinkedList r5, ParserContext r6) {
        if (r1 == 36) goto L27;
        switch(r1) {
            case 24: goto L25;
            case 25: goto L23;
            case 26: goto L21;
            case 27: goto L19;
            case 28: goto L17;
            case 29: goto L7;
            default: goto L15;
        };
    L17:
        r5.addTokenNode(new Strsim(r2, r4.nextNode(), r6));
        return;
    L19:
        r5.addTokenNode(new Soundslike(r2, r4.nextNode(), r6));
        return;
    L21:
        r5.addTokenNode(new Contains(r2, r4.nextNode(), r6));
        return;
    L23:
        r5.addTokenNode(new Instance(r2, r4.nextNode(), r6));
        return;
    L25:
        r5.addTokenNode(new RegExMatchNode(r2, r4.nextNode(), r6));
        return;
    L15:
        r5.addTokenNode(r2, r3);
        return;
    L7:
        if (r6.isStrongTyping() == false) goto L15;
        if (r2.getEgressType() == Boolean.class) goto L15;
        if (r2.getEgressType() == Boolean.TYPE) goto L15;
        Instance$$ExternalSyntheticBUOutline0.m("Condition of ternary operator is not of type boolean. Found ", r2.getEgressType());
        return;
    L27:
        r5.addTokenNode(new Convertable(r2, r4.nextNode(), r6));
    }

    private static boolean reducacbleOperator(int r1) {
        if (r1 == 0) goto L7;
        if (r1 == 1) goto L7;
        return false;
    L7:
        return true;
    }

    public static Number signNumber(Object r2) {
        if ((r2 instanceof Integer) == false) goto L7;
        return Integer.valueOf(-((Integer) r2).intValue());
    L7:
        if ((r2 instanceof Double) == false) goto L11;
        return Double.valueOf(-((Double) r2).doubleValue());
    L11:
        if ((r2 instanceof Float) == false) goto L15;
        return Float.valueOf(-((Float) r2).floatValue());
    L15:
        if ((r2 instanceof Short) == true) goto L17;
        Sign$$ExternalSyntheticBUOutline0.m("expected a numeric type but found: ".concat(r2.getClass().getName()), new char[0], 0);
        return null;
    L17:
        return Integer.valueOf(-((Short) r2).shortValue());
    }
}
