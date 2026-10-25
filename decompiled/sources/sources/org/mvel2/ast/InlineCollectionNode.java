package org.mvel2.ast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.optimizers.AccessorOptimizer;
import org.mvel2.optimizers.OptimizerFactory;
import org.mvel2.util.CollectionParser;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class InlineCollectionNode extends ASTNode {
    private Object collectionGraph;
    int trailingOffset;
    int trailingStart;

    public InlineCollectionNode(char[] r12, int r13, int r14, int r15, Class r16, ParserContext r17) {
        super(r12, r13, r14, r15 | 1024, r17);
        this.egressType = r16;
        if ((r15 & 16) != 0) goto L12;
        return;
    L12:
        parseGraph(true, r16, r17);     // Catch: Throwable -> L8
        AccessorOptimizer r1 = OptimizerFactory.getThreadAccessorOptimizer();     // Catch: Throwable -> L8
        this.accessor = r1.optimizeCollection(r17, this.collectionGraph, this.egressType, r12, this.trailingStart, this.trailingOffset, null, null, null);     // Catch: Throwable -> L8
        this.egressType = r1.getEgressType();     // Catch: Throwable -> L8
        OptimizerFactory.clearThreadAccessorOptimizer();
        return;
    L8:
        th = move-exception;
        OptimizerFactory.clearThreadAccessorOptimizer();
        throw th;
    }

    public InlineCollectionNode(char[] r12, int r13, int r14, int r15, ParserContext r16) {
        super(r12, r13, r14, r15 | 1024, r16);
        if ((r15 & 16) == 0) goto L11;
        parseGraph(true, null, r16);
        AccessorOptimizer r1 = OptimizerFactory.getThreadAccessorOptimizer();     // Catch: Throwable -> L8
        this.accessor = r1.optimizeCollection(r16, this.collectionGraph, this.egressType, r12, this.trailingStart, this.trailingOffset, null, null, null);     // Catch: Throwable -> L8
        this.egressType = r1.getEgressType();     // Catch: Throwable -> L8
        OptimizerFactory.clearThreadAccessorOptimizer();
        return;
    L8:
        th = move-exception;
        OptimizerFactory.clearThreadAccessorOptimizer();
        throw th;
    }

    private Object execGraph(Object r9, Class r10, Object r11, VariableResolverFactory r12) {
        if ((r9 instanceof List) == false) goto L10;
        List r92 = (List) r9;
        ArrayList r0 = new ArrayList(r92.size());
        Iterator r93 = r92.iterator();
    L6:
        if (r93.hasNext() == false) goto L8;
        r0.add(execGraph(r93.next(), r10, r11, r12));
        goto L6
    L8:
        return r0;
    L10:
        if ((r9 instanceof Map) == false) goto L17;
        HashMap r02 = new HashMap();
        Map r94 = (Map) r9;
        Iterator r1 = r94.keySet().iterator();
    L13:
        if (r1.hasNext() == false) goto L15;
        Object r2 = r1.next();
        r02.put(execGraph(r2, r10, r11, r12), execGraph(r94.get(r2), r10, r11, r12));
        goto L13
    L15:
        return r02;
    L17:
        if ((r9 instanceof Object[]) == false) goto L43;
        int r13 = 0;
        if (r10 == null) goto L24;
        int r4 = 0;
    L22:
        if (r10.getName().charAt(r4) != '[') goto L25;
        r4 = r4 + 1;
    L25:
        Object r3 = Array.newInstance(ParseTools.getSubComponentType(r10), ((Object[]) r9).length);
        if (r4 > 1) goto L48;
        Class r03 = r10;
    L33:
        Object[] r95 = (Object[]) r9;     // Catch: ClassNotFoundException -> L28 IllegalArgumentException -> L30
        int r22 = r95.length;     // Catch: ClassNotFoundException -> L28 IllegalArgumentException -> L30
        int r42 = 0;
    L34:
        if (r13 >= r22) goto L37;
        int r7 = r42 + 1;     // Catch: ClassNotFoundException -> L28 IllegalArgumentException -> L30
        Array.set(r3, r42, execGraph(r95[r13], r03, r11, r12));     // Catch: ClassNotFoundException -> L28 IllegalArgumentException -> L30
        r13 = r13 + 1;
        r42 = r7;
        goto L34
    L28:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("this error should never throw:".concat(ParseTools.getBaseComponentType(r10).getName()), e);
        return null;
    L30:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("type mismatch in array", this.expr, this.start, e);
        return null;
    L37:
        return r3;
    L48:
        r03 = ParseTools.findClass(null, ParseTools.repeatChar('[', r4 - 1) + "L" + ParseTools.getBaseComponentType(r10).getName() + ";", this.pCtx);     // Catch: ClassNotFoundException -> L28 IllegalArgumentException -> L30
        goto L33
    L24:
        r10 = Object[].class;
        r4 = 1;
        goto L25
    L43:
        if (r10.isArray() == false) goto L47;
        return MVEL.eval((String) r9, r11, r12, ParseTools.getBaseComponentType(r10));
    L47:
        return MVEL.eval((String) r9, r11, r12);
    }

    private void parseGraph(boolean r9, Class r10, ParserContext r11) {
        CollectionParser r0 = new CollectionParser();
        char[] r1 = this.expr;
        if (r10 != null) goto L5;
        this.collectionGraph = ((List) r0.parseCollection(r1, this.start, this.offset, r9, r11)).get(0);
    L6:
        int r92 = r0.getCursor() + 2;
        this.trailingStart = r92;
        this.trailingOffset = this.offset - (r92 - this.start);
        if (this.egressType != null) goto L10;
        this.egressType = this.collectionGraph.getClass();
        return;
    L10:
        return;
    L5:
        this.collectionGraph = ((List) r0.parseCollection(r1, this.start, this.offset, r9, r10, r11)).get(0);
        goto L6
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r3, Object r4, VariableResolverFactory r5) {
        parseGraph(false, this.egressType, this.pCtx);
        return execGraph(this.collectionGraph, this.egressType, r3, r5);
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r11, Object r12, VariableResolverFactory r13) {
        if (this.accessor != null) goto L5;
        AccessorOptimizer r0 = OptimizerFactory.getThreadAccessorOptimizer();     // Catch: Throwable -> L12
        if (this.collectionGraph != null) goto L9;
        parseGraph(true, null, null);     // Catch: Throwable -> L12
    L9:
        this.accessor = r0.optimizeCollection(this.pCtx, this.collectionGraph, this.egressType, this.expr, this.trailingStart, this.trailingOffset, r11, r12, r13);     // Catch: Throwable -> L12
        this.egressType = r0.getEgressType();     // Catch: Throwable -> L12
        Object r10 = this.accessor.getValue(r11, r12, r13);     // Catch: Throwable -> L12
        OptimizerFactory.clearThreadAccessorOptimizer();
        return r10;
    L12:
        th = move-exception;
        OptimizerFactory.clearThreadAccessorOptimizer();
        throw th;
    L5:
        return this.accessor.getValue(r11, r12, r13);
    }
}
