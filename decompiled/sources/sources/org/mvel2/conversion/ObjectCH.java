package org.mvel2.conversion;

import org.mvel2.ConversionHandler;

/* loaded from: classes5.dex */
public class ObjectCH implements ConversionHandler {
    public ObjectCH() {
    }

    @Override // org.mvel2.ConversionHandler
    public boolean canConvertFrom(Class r1) {
        return true;
    }

    @Override // org.mvel2.ConversionHandler
    public Object convertFrom(Object r1) {
        return r1;
    }
}
