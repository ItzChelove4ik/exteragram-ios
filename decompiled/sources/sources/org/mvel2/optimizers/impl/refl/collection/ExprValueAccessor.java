package org.mvel2.optimizers.impl.refl.collection;

import org.mvel2.DataConversion;
import org.mvel2.ParserContext;
import org.mvel2.compiler.Accessor;
import org.mvel2.compiler.ExecutableLiteral;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.ParseTools;
import org.mvel2.util.ReflectionUtil;

/* loaded from: classes5.dex */
public class ExprValueAccessor implements Accessor {
    public ExecutableStatement stmt;

    public ExprValueAccessor(String r3, Class r4, Object r5, VariableResolverFactory r6, ParserContext r7) {
        this.stmt = (ExecutableStatement) ParseTools.subCompileExpression(r3.toCharArray(), r7);
        Class r32 = ParseTools.getSubComponentType(r4);
        Class r42 = this.stmt.getKnownEgressType();
        if (this.stmt.getKnownEgressType() != null) goto L5;
        return;
    L5:
        if (ReflectionUtil.isAssignableFrom(r32, r42) == false) goto L7;
        return;
    L7:
        if ((this.stmt instanceof ExecutableLiteral) == true) goto L9;
    L12:
        if (r7 != null) goto L14;
        return;
    L14:
        if (r7.isStrongTyping() == false) goto L27;
        StringBuilder r52 = new StringBuilder("was expecting type: ");
        r52.append(r32);
        r52.append("; but found type: ");
        if (r42 != null) goto L18;
        String r33 = "null";
    L19:
        r52.append(r33);
        throw new RuntimeException(r52.toString());
    L18:
        r33 = r42.getName();
        goto L19
    L27:
        return;
    L9:
        if (DataConversion.canConvert(r42, r32) == false) goto L12;
        this.stmt = new ExecutableLiteral(DataConversion.convert(this.stmt.getValue(r5, r6), r32));     // Catch: IllegalArgumentException -> L22
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return this.stmt.getKnownEgressType();
    }

    public ExecutableStatement getStmt() {
        return this.stmt;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r1, Object r2, VariableResolverFactory r3) {
        return this.stmt.getValue(r2, r3);
    }

    public void setStmt(ExecutableStatement r1) {
        this.stmt = r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r1, Object r2, VariableResolverFactory r3, Object r4) {
        return null;
    }
}
