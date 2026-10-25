package org.mvel2.util;

import java.util.Set;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;

/* loaded from: classes5.dex */
public class VariableSpaceCompiler {
    private static final Object[] EMPTY_OBJ = null;

    static {
        EMPTY_OBJ = new Object[0];
    }

    public VariableSpaceCompiler() {
    }

    public static SimpleVariableSpaceModel compile(String r3, ParserContext r4) {
        String[] r0 = r4.getIndexedVarNames();
        ParserContext r1 = ParserContext.create();
        r1.setIndexAllocation(true);
        MVEL.analysisCompile(r3, r1);
        Set<String> r32 = r1.getVariables().keySet();
        r4.addIndexedLocals(r32);
        String[] r33 = (String[]) r32.toArray(new String[r32.size()]);
        String[] r42 = new String[r0.length + r33.length];
        System.arraycopy(r0, 0, r42, 0, r0.length);
        System.arraycopy(r33, 0, r42, r0.length, r33.length);
        return new SimpleVariableSpaceModel(r42);
    }

    public static SharedVariableSpaceModel compileShared(String r1, ParserContext r2) {
        return compileShared(r1, r2, EMPTY_OBJ);
    }

    public static SharedVariableSpaceModel compileShared(String r3, ParserContext r4, Object[] r5) {
        String[] r0 = r4.getIndexedVarNames();
        ParserContext r1 = ParserContext.create();
        r1.setIndexAllocation(true);
        MVEL.analysisCompile(r3, r1);
        Set<String> r32 = r1.getVariables().keySet();
        r4.addIndexedLocals(r32);
        String[] r33 = (String[]) r32.toArray(new String[r32.size()]);
        String[] r42 = new String[r0.length + r33.length];
        System.arraycopy(r0, 0, r42, 0, r0.length);
        System.arraycopy(r33, 0, r42, r0.length, r33.length);
        return new SharedVariableSpaceModel(r42, r5);
    }
}
