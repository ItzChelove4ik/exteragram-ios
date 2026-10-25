package org.mvel2.ast;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mvel2.CompileException;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.ExecutionStack;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class Stacklang extends BlockNode {
    static final Map<String, Integer> opcodes = null;
    List<Instruction> instructionList;
    ParserContext pCtx;

    /* renamed from: org.mvel2.ast.Stacklang$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class Instruction {
        Object cache;
        String expr;
        int opcode;

        private Instruction() {
        }

        public /* synthetic */ Instruction(AnonymousClass1 r1) {
            this();
        }
    }

    static {
        HashMap r0 = new HashMap();
        opcodes = r0;
        r0.put("push", 102);
        r0.put("pop", 103);
        r0.put("load", 104);
        r0.put("ldtype", 105);
        r0.put("invoke", 106);
        r0.put("store", 109);
        r0.put("getfield", 107);
        r0.put("storefield", 108);
        r0.put("dup", 110);
        r0.put("jump", 112);
        r0.put("jumpif", 113);
        r0.put("label", 111);
        r0.put("eq", 18);
        r0.put("ne", 19);
        r0.put("reduce", 114);
        r0.put("xswap", 116);
        r0.put("swap", 115);
    }

    public Stacklang(char[] r2, int r3, int r4, int r5, ParserContext r6) {
        super(r6);
        this.expr = r2;
        this.blockStart = r3;
        this.blockOffset = r4;
        this.fields = r5 | 4194304;
        String[] r22 = new String(r2, r3, r4).split(";");
        this.instructionList = new ArrayList(r22.length);
        int r32 = r22.length;
        int r42 = 0;
    L3:
        if (r42 >= r32) goto L5;
        String r52 = r22[r42];
        this.instructionList.add(parseInstruction(r52.trim()));
        r42 = r42 + 1;
        goto L3
    L5:
        this.pCtx = r6;
    }

    private static Instruction parseInstruction(String r5) {
        int r0 = r5.indexOf(32);
        Instruction r1 = new Instruction(null);
        if (r0 != (-1)) goto L5;
        String r2 = r5;
    L6:
        Map<String, Integer> r3 = opcodes;
        if (r3.containsKey(r2) == false) goto L9;
        r1.opcode = r3.get(r2).intValue();
    L9:
        if (r2 == r5) goto L11;
        r1.expr = r5.substring(r0 + 1);
    L11:
        return r1;
    L5:
        r2 = r5.substring(0, r0);
        goto L6
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r10, Object r11, VariableResolverFactory r12) {
        ExecutionStack r112 = (ExecutionStack) r10;
        int r0 = this.instructionList.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L119;
        Instruction r3 = this.instructionList.get(r2);
        System.out.println(r112.toString() + " >> " + r3.opcode + ":" + r3.expr);
        int r4 = r3.opcode;
        if (r4 != 18) goto L7;
        r112.push(Boolean.valueOf(r112.pop().equals(r112.pop())));
    L117:
        r2 = r2 + 1;
        goto L3
    L7:
        if (r4 != 19) goto L9;
        r112.push(Boolean.valueOf(!r112.pop().equals(r112.pop())));
        goto L117
    L9:
        switch(r4) {
            case 102: goto L112;
            case 103: goto L110;
            case 104: goto L107;
            case 105: goto L126;
            case 106: goto L61;
            case 107: goto L128;
            case 108: goto L122;
            case 109: goto L31;
            case 110: goto L30;
            default: goto L10;
        };
    L10:
        switch(r4) {
            case 112: goto L18;
            case 113: goto L16;
            case 114: goto L14;
            case 115: goto L13;
            case 116: goto L12;
            default: goto L117;
        };
    L12:
        r112.xswap2();
        goto L117
    L13:
        Object r32 = r112.pop();
        Object r42 = r112.pop();
        r112.push(r32);
        r112.push(r42);
        goto L117
    L14:
        r112.op();
    L18:
        Object r43 = r3.cache;
        if (r43 == null) goto L21;
        r2 = ((Integer) r43).intValue();
        goto L117
    L21:
        int r44 = 0;
    L23:
        if (r44 >= this.instructionList.size()) goto L117;
        Instruction r5 = this.instructionList.get(r44);
        if (r5.opcode != 111) goto L29;
        if (r3.expr.equals(r5.expr) == false) goto L29;
        r3.cache = Integer.valueOf(r44);
        r2 = r44;
    L29:
        r44 = r44 + 1;
        goto L23
    L16:
        if (r112.popBoolean().booleanValue() == true) goto L18;
    L30:
        r112.dup();
        goto L117
    L31:
        Object r45 = r3.cache;
        if (r45 != null) goto L34;
        r3.cache = r12.createVariable(r3.expr, r112.peek());
        goto L117
    L34:
        ((VariableResolver) r45).setValue(r112.peek());
        goto L117
    L61:
        ExecutionStack r46 = new ExecutionStack();
    L63:
        if (r112.isEmpty() == true) goto L68;
        if ((r112.peek() instanceof Class) == true) goto L68;
        r46.push(r112.pop());
    L68:
        if (r112.isEmpty() == true) goto L95;
        Object[] r6 = new Object[r46.size()];
        int r7 = 0;
    L71:
        if (r46.isEmpty() == true) goto L73;
        r6[r7] = r46.pop();
        r7 = r7 + 1;
        goto L71
    L73:
        boolean r47 = "<init>".equals(r3.expr);
        Object r72 = r3.cache;
        if (r47 == false) goto L83;
        if (r72 != null) goto L77;
        Constructor r48 = ParseTools.getBestConstructorCandidate(r6, (Class) r112.pop(), false);
        r3.cache = r48;
    L120:
        r112.push(r48.newInstance(r6));     // Catch: Exception -> L80
    L80:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("instantiation error", this.expr, this.blockStart, e);
        return null;
    L77:
        r48 = (Constructor) r72;
        goto L120
    L83:
        if (r72 != null) goto L85;
        Class r49 = (Class) r112.pop();
        Method r410 = ParseTools.getBestCandidate(r6, r3.expr, r49, r49.getDeclaredMethods(), false);
        r3.cache = r410;
    L124:
        if (r112.isEmpty() == false) goto L89;
        Object r33 = null;
    L90:
        r112.push(r410.invoke(r33, r6));     // Catch: Exception -> L92
        goto L117
    L89:
        r33 = r112.pop();     // Catch: Exception -> L92
    L92:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("invokation error", this.expr, this.blockStart, e);
        return null;
    L85:
        r112.discard();
        r410 = (Method) r3.cache;
        goto L124
    L95:
        Sign$$ExternalSyntheticBUOutline0.m("invoke without class", this.expr, this.blockStart);
        return null;
    L110:
        r112.pop();
        goto L117
    L122:
    L40:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("field access error", this.expr, this.blockStart, e);
        return null;
    L36:
        if (r112.isEmpty() == true) goto L43;
        if ((r112.peek() instanceof Class) == false) goto L43;
        Class r411 = (Class) r112.pop();     // Catch: Exception -> L40
        Object r73 = r112.pop();     // Catch: Exception -> L40
        r411.getField(r3.expr).set(r112.pop(), r73);     // Catch: Exception -> L40
        r112.push(r73);     // Catch: Exception -> L40
    L43:
        throw new CompileException("storefield without class", this.expr, this.blockStart);     // Catch: Exception -> L40
    L126:
        if (r3.cache != null) goto L102;
        r3.cache = ParseTools.createClass(r3.expr, this.pCtx);     // Catch: ClassNotFoundException -> L100
    L102:
        r112.push(r3.cache);     // Catch: ClassNotFoundException -> L100
    L100:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("error", this.expr, this.blockStart, e);
        return null;
    L128:
    L53:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("field access error", this.expr, this.blockStart, e);
        return null;
    L47:
        if (r112.isEmpty() == true) goto L58;
        if ((r112.peek() instanceof Class) == false) goto L58;
        if (r3.cache != null) goto L55;
        Field r412 = ((Class) r112.pop()).getField(r3.expr);     // Catch: Exception -> L53
        r3.cache = r412;     // Catch: Exception -> L53
    L56:
        r112.push(r412.get(r112.pop()));     // Catch: Exception -> L53
        goto L117
    L55:
        r112.discard();     // Catch: Exception -> L53
        r412 = (Field) r3.cache;     // Catch: Exception -> L53
    L58:
        throw new CompileException("getfield without class", this.expr, this.blockStart);     // Catch: Exception -> L53
    L107:
        if (r3.cache != null) goto L109;
        r3.cache = r12.getVariableResolver(r3.expr);
    L109:
        r112.push(((VariableResolver) r3.cache).getValue());
        goto L117
    L112:
        if (r3.cache != null) goto L114;
        r3.cache = MVEL.eval(r3.expr, r10, r12);
    L114:
        r112.push(r3.cache);
        goto L117
    L119:
        return r112.pop();
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r1, Object r2, VariableResolverFactory r3) {
        ExecutionStack r12 = new ExecutionStack();
        r12.push(getReducedValue(r12, r2, r3));
        if (r12.isReduceable() == false) goto L8;
    L4:
        r12.op();
        if (r12.isReduceable() == false) goto L8;
        r12.xswap();
    L8:
        return r12.peek();
    }
}
