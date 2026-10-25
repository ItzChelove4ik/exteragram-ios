package org.mvel2.optimizers.impl.asm;

import org.mvel2.asm.MethodVisitor;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public interface ProducesBytecode {
    void produceBytecodeGet(MethodVisitor r1, String r2, VariableResolverFactory r3);

    void produceBytecodePut(MethodVisitor r1, String r2, VariableResolverFactory r3);
}
