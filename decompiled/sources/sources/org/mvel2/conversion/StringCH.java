package org.mvel2.conversion;

import org.mvel2.ConversionHandler;
import org.mvel2.compiler.BlankLiteral;

/* loaded from: classes5.dex */
public class StringCH implements ConversionHandler {
    public StringCH() {
    }

    @Override // org.mvel2.ConversionHandler
    public boolean canConvertFrom(Class r1) {
        if (r1 == BlankLiteral.class) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.mvel2.ConversionHandler
    public Object convertFrom(Object r1) {
        return String.valueOf(r1);
    }
}
