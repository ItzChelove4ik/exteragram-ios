package org.mvel2.optimizers.impl.refl.nodes;

import java.util.List;
import org.mvel2.DataConversion;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class ListAccessorNest implements AccessorNode {
    private Class conversionType;
    private ExecutableStatement index;
    private AccessorNode nextNode;

    public ListAccessorNest() {
    }

    public ListAccessorNest(String r1, Class r2) {
        this.index = (ExecutableStatement) ParseTools.subCompileExpression(r1.toCharArray());
        this.conversionType = r2;
    }

    public ListAccessorNest(ExecutableStatement r1, Class r2) {
        this.index = r1;
        this.conversionType = r2;
    }

    public ExecutableStatement getIndex() {
        return this.index;
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
    public Object getValue(Object r3, Object r4, VariableResolverFactory r5) {
        AccessorNode r0 = this.nextNode;
        if (r0 == null) goto L7;
        return r0.getValue(((List) r3).get(((Integer) this.index.getValue(r3, r4, r5)).intValue()), r4, r5);
    L7:
        return ((List) r3).get(((Integer) this.index.getValue(r3, r4, r5)).intValue());
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
        return r0.setValue(((List) r3).get(((Integer) this.index.getValue(r3, r4, r5)).intValue()), r4, r5, r6);
    L7:
        if (this.conversionType == null) goto L10;
        List r02 = (List) r3;
        int r32 = ((Integer) this.index.getValue(r3, r4, r5)).intValue();
        Object r2 = DataConversion.convert(r6, this.conversionType);
        r02.set(r32, r2);
        return r2;
    L10:
        ((List) r3).set(((Integer) this.index.getValue(r3, r4, r5)).intValue(), r6);
        return r6;
    }

    public String toString() {
        return "Array Accessor -> [" + this.index + "]";
    }
}
