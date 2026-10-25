package org.mvel2.optimizers.impl.refl.nodes;

import java.lang.reflect.Array;
import org.mvel2.DataConversion;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class ArrayAccessorNest implements AccessorNode {
    private Class baseComponentType;
    private ExecutableStatement index;
    private AccessorNode nextNode;
    private boolean requireConversion;

    public ArrayAccessorNest() {
    }

    public ArrayAccessorNest(String r1) {
        this.index = (ExecutableStatement) ParseTools.subCompileExpression(r1.toCharArray());
    }

    public ArrayAccessorNest(ExecutableStatement r1) {
        this.index = r1;
    }

    public ExecutableStatement getIndex() {
        return this.index;
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return this.baseComponentType;
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode getNextNode() {
        return this.nextNode;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r3, Object r4, VariableResolverFactory r5) {
        AccessorNode r0 = this.nextNode;
        if (r0 == null) goto L7;
        return r0.getValue(((Object[]) r3)[((Integer) this.index.getValue(r3, r4, r5)).intValue()], r4, r5);
    L7:
        return ((Object[]) r3)[((Integer) this.index.getValue(r3, r4, r5)).intValue()];
    }

    public void setIndex(ExecutableStatement r1) {
        this.index = r1;
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode setNextNode(AccessorNode r1) {
        this.nextNode = r1;
        return r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r3, Object r4, VariableResolverFactory r5, Object r6) {
        AccessorNode r0 = this.nextNode;
        if (r0 == null) goto L7;
        return r0.setValue(((Object[]) r3)[((Integer) this.index.getValue(r3, r4, r5)).intValue()], r4, r5, r6);
    L7:
        if (this.baseComponentType != null) goto L16;
        Class<?> r02 = ParseTools.getBaseComponentType(r3.getClass());
        this.baseComponentType = r02;
        if (r02 != r6.getClass()) goto L11;
    L13:
        boolean r03 = false;
    L14:
        this.requireConversion = r03;
        goto L16
    L11:
        if (this.baseComponentType.isAssignableFrom(r6.getClass()) == true) goto L13;
        r03 = true;
    L16:
        if (this.requireConversion == false) goto L19;
        Object r62 = DataConversion.convert(r6, this.baseComponentType);
        Array.set(r3, ((Integer) this.index.getValue(r3, r4, r5)).intValue(), r62);
        return r62;
    L19:
        Array.set(r3, ((Integer) this.index.getValue(r3, r4, r5)).intValue(), r6);
        return r6;
    }

    public String toString() {
        return "Array Accessor -> [" + this.index + "]";
    }
}
