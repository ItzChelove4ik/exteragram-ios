package org.mvel2.optimizers.impl.refl.nodes;

import org.mvel2.ParserContext;
import org.mvel2.compiler.Accessor;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.optimizers.OptimizerFactory;

/* loaded from: classes5.dex */
public class NullSafe implements AccessorNode {
    private char[] expr;
    private AccessorNode nextNode;
    private int offset;
    private ParserContext pCtx;
    private int start;
    private boolean topLevel;

    /* renamed from: org.mvel2.optimizers.impl.refl.nodes.NullSafe$1, reason: invalid class name */
    public class AnonymousClass1 implements AccessorNode {
        final /* synthetic */ NullSafe this$0;
        final /* synthetic */ Accessor val$a;

        public AnonymousClass1(NullSafe r1, Accessor r2) {
            this.this$0 = r1;
            this.val$a = r2;
        }

        @Override // org.mvel2.compiler.Accessor
        public Class getKnownEgressType() {
            return this.val$a.getKnownEgressType();
        }

        @Override // org.mvel2.compiler.AccessorNode
        public AccessorNode getNextNode() {
            return null;
        }

        @Override // org.mvel2.compiler.Accessor
        public Object getValue(Object r1, Object r2, VariableResolverFactory r3) {
            return this.val$a.getValue(r1, r2, r3);
        }

        @Override // org.mvel2.compiler.AccessorNode
        public AccessorNode setNextNode(AccessorNode r1) {
            return null;
        }

        @Override // org.mvel2.compiler.Accessor
        public Object setValue(Object r1, Object r2, VariableResolverFactory r3, Object r4) {
            return this.val$a.setValue(r1, r2, r3, r4);
        }
    }

    public NullSafe(char[] r1, int r2, int r3, ParserContext r4, boolean r5) {
        this.expr = r1;
        this.start = r2;
        this.offset = r3;
        this.pCtx = r4;
        this.topLevel = r5;
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return Object.class;
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode getNextNode() {
        return this.nextNode;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r13, Object r14, VariableResolverFactory r15) {
        if (r13 != null) goto L6;
        return null;
    L6:
        if (this.nextNode != null) goto L8;
        Object r6 = r13;
        Object r7 = r14;
        VariableResolverFactory r8 = r15;
        this.nextNode = new AnonymousClass1(this, OptimizerFactory.getAccessorCompiler(OptimizerFactory.SAFE_REFLECTIVE).optimizeAccessor(this.pCtx, this.expr, this.start, this.offset, r6, r7, r8, true, r13.getClass(), this.topLevel));
    L10:
        return this.nextNode.getValue(r6, r7, r8);
    L8:
        r6 = r13;
        r7 = r14;
        r8 = r15;
        goto L10
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode setNextNode(AccessorNode r1) {
        this.nextNode = r1;
        return r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r1, Object r2, VariableResolverFactory r3, Object r4) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return this.nextNode.setValue(r1, r2, r3, r4);
    }
}
