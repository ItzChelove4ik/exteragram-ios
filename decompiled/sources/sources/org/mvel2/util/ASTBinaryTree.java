package org.mvel2.util;

import org.mvel2.MVEL$$ExternalSyntheticBUOutline0;
import org.mvel2.Operator;
import org.mvel2.ast.ASTNode;
import org.mvel2.ast.EndOfStatement;
import org.mvel2.ast.OperatorNode;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public class ASTBinaryTree {
    private ASTBinaryTree left;
    private ASTBinaryTree right;
    private final ASTNode root;

    public ASTBinaryTree(ASTNode r1) {
        this.root = r1;
    }

    public static ASTBinaryTree buildTree(ASTIterator r3) {
        ASTLinkedList r0 = new ASTLinkedList(r3.firstNode());
        ASTBinaryTree r32 = new ASTBinaryTree(r0.nextNode());
    L4:
        if (r0.hasMoreNodes() == false) goto L11;
        ASTNode r1 = r0.nextNode();
        if ((r1 instanceof EndOfStatement) == true) goto L8;
        r32 = r32.append(r1);
        goto L4
    L8:
        if (r0.hasMoreNodes() == false) goto L4;
        r32 = new ASTBinaryTree(r0.nextNode());
        goto L4
    L11:
        return r32;
    }

    private int comparePrecedence(ASTNode r2, ASTNode r3) {
        boolean r1 = r2 instanceof OperatorNode;
        if (r1 == false) goto L5;
    L8:
        if (r1 == true) goto L10;
    L13:
        if (r1 == false) goto L16;
        return -1;
    L16:
        return 1;
    L10:
        if ((r3 instanceof OperatorNode) == false) goto L13;
        int[] r12 = Operator.PTABLE;
        return r12[r2.getOperator().intValue()] - r12[r3.getOperator().intValue()];
    L5:
        if ((r3 instanceof OperatorNode) == true) goto L8;
        return 0;
    }

    public ASTBinaryTree append(ASTNode r2) {
        if (comparePrecedence(this.root, r2) < 0) goto L7;
        ASTBinaryTree r0 = new ASTBinaryTree(r2);
        r0.left = this;
        return r0;
    L7:
        if (this.left == null) goto L14;
        ASTBinaryTree r02 = this.right;
        if (r02 != null) goto L12;
        this.right = new ASTBinaryTree(r2);
        return this;
    L12:
        this.right = r02.append(r2);
        return this;
    L14:
        GlShader$$ExternalSyntheticBUOutline1.m("Missing left node");
        return null;
    }

    public Class<?> getReturnType(boolean r9) {
        ASTNode r0 = this.root;
        if ((r0 instanceof OperatorNode) == false) goto L5;
        ASTBinaryTree r02 = this.left;
        if (r02 != null) goto L9;
    L97:
        GlShader$$ExternalSyntheticBUOutline1.m("Malformed expression");
        return null;
    L9:
        if (this.right == null) goto L97;
        Class<?> r03 = r02.getReturnType(r9);
        Class<?> r2 = this.right.getReturnType(r9);
        int r3 = this.root.getOperator().intValue();
        if (r3 == 0) goto L79;
        if (r3 != 1) goto L15;
    L83:
        if (r9 == true) goto L85;
    L89:
        if (r03 == r2) goto L91;
    L95:
        return Double.class;
    L91:
        if (r03.isPrimitive() == false) goto L93;
    L94:
        return r03;
    L93:
        if (Number.class.isAssignableFrom(r03) == false) goto L95;
    L85:
        if (CompatibilityStrategy.areEqualityCompatible(r03, r2) == true) goto L89;
        ASTBinaryTree$$ExternalSyntheticBUOutline0.m("Associative operation requires compatible types. Found ", r03, r2);
        return null;
    L15:
        if (r3 == 2) goto L83;
        if (r3 == 3) goto L83;
        if (r3 == 4) goto L70;
        Class<?> r6 = Boolean.TYPE;
        if (r3 != 21) goto L23;
    L58:
        if (r9 == false) goto L69;
        if (r03 == Boolean.class) goto L64;
        if (r03 == r6) goto L64;
        MVEL$$ExternalSyntheticBUOutline0.m("Left side of logical operation is not of type boolean. Found ", r03);
        return null;
    L64:
        if (r2 == Boolean.class) goto L69;
        if (r2 == r6) goto L69;
        MVEL$$ExternalSyntheticBUOutline0.m("Right side of logical operation is not of type boolean. Found ", r2);
        return null;
    L69:
        return Boolean.class;
    L23:
        if (r3 == 22) goto L58;
        switch(r3) {
            case 14: goto L51;
            case 15: goto L51;
            case 16: goto L51;
            case 17: goto L51;
            case 18: goto L44;
            case 19: goto L44;
            default: goto L26;
        };
    L26:
        switch(r3) {
            case 24: goto L43;
            case 25: goto L43;
            case 26: goto L43;
            case 27: goto L43;
            case 28: goto L43;
            case 29: goto L36;
            case 30: goto L29;
            default: goto L28;
        };
    L29:
        if (r9 == true) goto L31;
    L35:
        return r03;
    L31:
        if (CompatibilityStrategy.areEqualityCompatible(r03, r2) == true) goto L35;
        ASTBinaryTree$$ExternalSyntheticBUOutline0.m("Associative operation requires compatible types. Found ", r03, r2);
        return null;
    L36:
        if (r9 == false) goto L42;
        if (r03 == Boolean.class) goto L42;
        if (r03 == r6) goto L42;
        MVEL$$ExternalSyntheticBUOutline0.m("Condition of ternary operator is not of type boolean. Found ", r03);
        return null;
    L42:
        return r2;
    L43:
        return Boolean.class;
    L28:
        return this.root.getEgressType();
    L44:
        if (r9 == true) goto L46;
    L50:
        return Boolean.class;
    L46:
        if (CompatibilityStrategy.areEqualityCompatible(r03, r2) == true) goto L50;
        ASTBinaryTree$$ExternalSyntheticBUOutline0.m("Comparison operation requires compatible types. Found ", r03, r2);
        return null;
    L51:
        if (r9 == true) goto L53;
    L57:
        return Boolean.class;
    L53:
        if (CompatibilityStrategy.areComparisonCompatible(r03, r2) == true) goto L57;
        ASTBinaryTree$$ExternalSyntheticBUOutline0.m("Comparison operation requires compatible types. Found ", r03, r2);
        return null;
    L70:
        if (r9 == true) goto L72;
        return Integer.class;
    L72:
        if (CompatibilityStrategy.areEqualityCompatible(r03, r2) == true) goto L101;
        ASTBinaryTree$$ExternalSyntheticBUOutline0.m("Associative operation requires compatible types. Found ", r03, r2);
        return null;
    L101:
        return Integer.class;
    L79:
        if (r03.equals(String.class) == false) goto L81;
        return String.class;
    L81:
        if (r2.equals(String.class) == false) goto L83;
        return String.class;
    L5:
        return r0.getEgressType();
    }
}
