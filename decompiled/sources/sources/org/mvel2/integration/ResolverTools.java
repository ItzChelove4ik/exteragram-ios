package org.mvel2.integration;

/* loaded from: classes5.dex */
public class ResolverTools {
    public ResolverTools() {
    }

    public static <T extends VariableResolverFactory> T appendFactory(VariableResolverFactory r1, T r2) {
        if (r1.getNextFactory() != null) goto L7;
        r1.setNextFactory(r2);
        return r2;
    L7:
        if (r1.getNextFactory() == null) goto L9;
        r1 = r1.getNextFactory();
        goto L7
    L9:
        r1.setNextFactory(r2);
        return r2;
    }

    public static <T extends VariableResolverFactory> T insertFactory(VariableResolverFactory r1, T r2) {
        if (r1.getNextFactory() != null) goto L6;
        r1.setNextFactory(r2);
        return r2;
    L6:
        r2.setNextFactory(r1.getNextFactory());
        r1.setNextFactory(r2);
        return r2;
    }
}
