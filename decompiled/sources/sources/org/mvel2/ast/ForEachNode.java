package org.mvel2.ast;

import java.lang.reflect.Array;
import java.util.Iterator;
import org.mvel2.CompileException;
import org.mvel2.DataConversion;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.DefaultLocalVariableResolverFactory;
import org.mvel2.integration.impl.ItemResolverFactory;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class ForEachNode extends BlockNode {
    private static final int ARRAY = 1;
    private static final int CHARSEQUENCE = 2;
    private static final int INTEGER = 3;
    private static final int ITERABLE = 0;
    protected ExecutableStatement condition;
    protected String item;
    protected Class itemType;
    private int type;

    public ForEachNode(char[] r8, int r9, int r10, int r11, int r12, int r13, ParserContext r14) {
        super(r14);
        this.type = -1;
        this.expr = r8;
        this.start = r9;
        this.offset = r10;
        this.fields = r13;
        handleCond(r8, r9, r10, r13, r14);
        this.blockStart = r11;
        this.blockOffset = r12;
        if ((r13 & 16) != 0) goto L5;
        return;
    L5:
        if (r14.isStrictTypeEnforcement() == true) goto L7;
    L9:
        ParserContext r142 = r14;
    L10:
        r142.pushVariableScope();
        r142.makeVisible(this.item);
        this.compiledBlock = (ExecutableStatement) ParseTools.subCompileExpression(r8, r11, r12, r142);
        r142.popVariableScope();
        return;
    L7:
        if (this.itemType == null) goto L9;
        r142 = r14.createSubcontext();
        r142.addInput(this.item, this.itemType);
        goto L10
    }

    private void determineIterType(Class r2) {
        if (Iterable.class.isAssignableFrom(r2) == false) goto L7;
        this.type = 0;
        return;
    L7:
        if (r2.isArray() == false) goto L11;
        this.type = 1;
        return;
    L11:
        if (CharSequence.class.isAssignableFrom(r2) == false) goto L15;
        this.type = 2;
        return;
    L15:
        if (Integer.class.isAssignableFrom(r2) == false) goto L18;
        this.type = 3;
        return;
    L18:
        Sign$$ExternalSyntheticBUOutline0.m("non-iterable type: ".concat(r2.getName()), this.expr, this.start);
    }

    private void enforceTypeSafety(Class r3, Class r4) {
        if (r3.isAssignableFrom(r4) == false) goto L5;
        return;
    L5:
        if (DataConversion.canConvert(r4, r3) == true) goto L10;
        Sign$$ExternalSyntheticBUOutline0.m("type mismatch in foreach: expected: " + r3.getName() + "; but found: " + ParseTools.getBaseComponentType(r4), this.expr, this.start);
        return;
    }

    private void handleCond(char[] r7, int r8, int r9, int r10, ParserContext r11) {
        int r0 = r8 + r9;
        int r1 = r8;
    L4:
        if (r1 >= r0) goto L8;
        if (r7[r1] == ':') goto L8;
        r1 = r1 + 1;
    L8:
        if (r1 != r0) goto L10;
    L30:
        Sign$$ExternalSyntheticBUOutline0.m("expected : in foreach", r7, r1);
        return;
    L10:
        if (r7[r1] != ':') goto L30;
        int r02 = r1 - r8;
        String r2 = ParseTools.createStringTrimmed(r7, r8, r02);
        this.item = r2;
        int r22 = r2.indexOf(32);
        if (r22 == (-1)) goto L18;
        String r23 = this.item.substring(0, r22);
        this.itemType = ParseTools.findClass(null, r23, r11);     // Catch: ClassNotFoundException -> L16
        String r4 = this.item;     // Catch: ClassNotFoundException -> L16
        this.item = r4.substring(r4.lastIndexOf(32) + 1, this.item.length());     // Catch: ClassNotFoundException -> L16
    L16:
        Sign$$ExternalSyntheticBUOutline0.m("cannot resolve identifier: ".concat(r23), r7, r8);
        return;
    L18:
        int r12 = r1 + 1;
        this.start = r12;
        int r92 = (r9 - r02) - 1;
        this.offset = r92;
        if ((r10 & 16) == 0) goto L36;
        ExecutableStatement r72 = (ExecutableStatement) ParseTools.subCompileExpression(this.expr, r12, r92, r11);
        this.condition = r72;
        Class r73 = r72.getKnownEgressType();
        if (this.itemType == null) goto L27;
        if (r73.isArray() == false) goto L27;
        enforceTypeSafety(this.itemType, ParseTools.getBaseComponentType(this.condition.getKnownEgressType()));
        return;
    L27:
        if (r11.isStrongTyping() == false) goto L37;
        determineIterType(r73);
        return;
    L37:
        return;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r7, Object r8, VariableResolverFactory r9) {
        ItemResolverFactory.ItemResolver r0 = new ItemResolverFactory.ItemResolver(this.item);
        ItemResolverFactory r1 = new ItemResolverFactory(r0, new DefaultLocalVariableResolverFactory(r9));
        Object r92 = MVEL.eval(this.expr, this.start, this.offset, r8, r9);
        Class r2 = this.itemType;
        if (r2 != null) goto L5;
    L7:
        this.compiledBlock = (ExecutableStatement) ParseTools.subCompileExpression(this.expr, this.blockStart, this.blockOffset, this.pCtx);
        if ((r92 instanceof Iterable) == false) goto L15;
        Iterator r93 = ((Iterable) r92).iterator();
    L11:
        if (r93.hasNext() == false) goto L41;
        r0.setValue(r93.next());
        Object r22 = this.compiledBlock.getValue(r7, r8, r1);
        if (r1.tiltFlag() == false) goto L11;
        return r22;
    L41:
        return null;
    L15:
        int r23 = 0;
        if (r92 == null) goto L26;
        if (r92.getClass().isArray() == false) goto L26;
        int r3 = Array.getLength(r92);
    L20:
        if (r23 >= r3) goto L58;
        r0.setValue(Array.get(r92, r23));
        Object r4 = this.compiledBlock.getValue(r7, r8, r1);
        if (r1.tiltFlag() == true) goto L23;
        r23 = r23 + 1;
        goto L20
    L23:
        return r4;
    L58:
        return null;
    L26:
        if ((r92 instanceof CharSequence) == false) goto L34;
        char[] r94 = r92.toString().toCharArray();
        int r32 = r94.length;
    L28:
        if (r23 >= r32) goto L59;
        r0.setValue(Character.valueOf(r94[r23]));
        Object r42 = this.compiledBlock.getValue(r7, r8, r1);
        if (r1.tiltFlag() == true) goto L31;
        r23 = r23 + 1;
        goto L28
    L31:
        return r42;
    L59:
        return null;
    L34:
        if ((r92 instanceof Integer) == false) goto L44;
        int r24 = 1;
        int r95 = ((Integer) r92).intValue() + 1;
    L36:
        if (r24 == r95) goto L60;
        r0.setValue(Integer.valueOf(r24));
        Object r33 = this.compiledBlock.getValue(r7, r8, r1);
        if (r1.tiltFlag() == true) goto L39;
        r24 = r24 + 1;
        goto L36
    L39:
        return r33;
    L60:
        return null;
    L44:
        if (r92 == null) goto L46;
        String r82 = r92.getClass().getName();
    L48:
        throw new CompileException("non-iterable type: ".concat(r82), this.expr, this.start);
    L46:
        r82 = "null";
        goto L48
    L5:
        if (r2.isArray() == false) goto L7;
        enforceTypeSafety(this.itemType, ParseTools.getBaseComponentType(r92.getClass()));
        goto L7
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r7, Object r8, VariableResolverFactory r9) {
        ItemResolverFactory.ItemResolver r0 = new ItemResolverFactory.ItemResolver(this.item);
        ItemResolverFactory r1 = new ItemResolverFactory(r0, new DefaultLocalVariableResolverFactory(r9));
        Object r92 = this.condition.getValue(r7, r8, r9);
        if (this.type != (-1)) goto L5;
        determineIterType(r92.getClass());
    L5:
        int r2 = this.type;
        if (r2 == 0) goto L32;
        int r3 = 0;
        int r4 = 1;
        if (r2 != 1) goto L10;
        int r22 = Array.getLength(r92);
    L27:
        if (r3 >= r22) goto L51;
        r0.setValue(Array.get(r92, r3));
        Object r42 = this.compiledBlock.getValue(r7, r8, r1);
        if (r1.tiltFlag() == true) goto L30;
        r3 = r3 + 1;
        goto L27
    L30:
        return r42;
    L51:
        return null;
    L10:
        if (r2 != 2) goto L12;
        char[] r93 = r92.toString().toCharArray();
        int r23 = r93.length;
    L21:
        if (r3 >= r23) goto L50;
        r0.setValue(Character.valueOf(r93[r3]));
        Object r43 = this.compiledBlock.getValue(r7, r8, r1);
        if (r1.tiltFlag() == true) goto L24;
        r3 = r3 + 1;
        goto L21
    L24:
        return r43;
    L50:
        return null;
    L12:
        if (r2 != 3) goto L38;
        int r94 = ((Integer) r92).intValue() + 1;
    L15:
        if (r4 == r94) goto L49;
        r0.setValue(Integer.valueOf(r4));
        Object r24 = this.compiledBlock.getValue(r7, r8, r1);
        if (r1.tiltFlag() == true) goto L18;
        r4 = r4 + 1;
        goto L15
    L18:
        return r24;
    L49:
        return null;
    L38:
        return null;
    L32:
        Iterator r95 = ((Iterable) r92).iterator();
    L34:
        if (r95.hasNext() == false) goto L52;
        r0.setValue(r95.next());
        Object r25 = this.compiledBlock.getValue(r7, r8, r1);
        if (r1.tiltFlag() == false) goto L34;
        return r25;
    L52:
        return null;
    }
}
