package org.mvel2.optimizers.impl.refl.nodes;

import java.util.Map;
import org.mvel2.DataConversion;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class MapAccessorNest implements AccessorNode {
    private Class conversionType;
    private AccessorNode nextNode;
    private ExecutableStatement property;

    public MapAccessorNest() {
    }

    public MapAccessorNest(String r1, Class r2) {
        this.property = (ExecutableStatement) ParseTools.subCompileExpression(r1.toCharArray());
        this.conversionType = r2;
    }

    public MapAccessorNest(ExecutableStatement r1, Class r2) {
        this.property = r1;
        this.conversionType = r2;
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return Object.class;
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode getNextNode() {
        return this.nextNode;
    }

    public ExecutableStatement getProperty() {
        return this.property;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r2, Object r3, VariableResolverFactory r4) {
        AccessorNode r0 = this.nextNode;
        if (r0 == null) goto L7;
        return r0.getValue(((Map) r2).get(this.property.getValue(r3, r3, r4)), r3, r4);
    L7:
        return ((Map) r2).get(this.property.getValue(r3, r3, r4));
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode setNextNode(AccessorNode r1) {
        this.nextNode = r1;
        return r1;
    }

    public void setProperty(ExecutableStatement r1) {
        this.property = r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r3, Object r4, VariableResolverFactory r5, Object r6) {
        AccessorNode r0 = this.nextNode;
        if (r0 == null) goto L7;
        return r0.setValue(((Map) r3).get(this.property.getValue(r3, r4, r5)), r4, r5, r6);
    L7:
        if (this.conversionType == null) goto L10;
        Map r02 = (Map) r3;
        Object r32 = this.property.getValue(r3, r4, r5);
        Object r2 = DataConversion.convert(r6, this.conversionType);
        r02.put(r32, r2);
        return r2;
    L10:
        ((Map) r3).put(this.property.getValue(r3, r4, r5), r6);
        return r6;
    }

    public String toString() {
        return "Map Accessor -> [" + this.property + "]";
    }
}
