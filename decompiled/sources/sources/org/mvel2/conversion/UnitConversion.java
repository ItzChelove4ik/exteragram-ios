package org.mvel2.conversion;

import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.url._UrlKt;
import org.mvel2.ConversionHandler;
import org.mvel2.Unit;

/* loaded from: classes5.dex */
public class UnitConversion implements ConversionHandler {
    private static final Logger LOG = null;

    static {
        LOG = Logger.getLogger(UnitConversion.class.getName());
    }

    public UnitConversion() {
    }

    @Override // org.mvel2.ConversionHandler
    public boolean canConvertFrom(Class r3) {
        if (Unit.class.isAssignableFrom(r3) == false) goto L5;
    L16:
        return ((Unit) Unit.class.newInstance()).canConvertFrom(r3);
    L8:
        e = move-exception;
        LOG.log(Level.SEVERE, _UrlKt.FRAGMENT_ENCODE_SET, e);
        return false;
    L10:
        e = move-exception;
        LOG.log(Level.SEVERE, _UrlKt.FRAGMENT_ENCODE_SET, e);
        return false;
    L5:
        if (Number.class.isAssignableFrom(r3) == true) goto L16;
        return false;
    }

    @Override // org.mvel2.ConversionHandler
    public Object convertFrom(Object r3) {
        return ((Unit) Unit.class.newInstance()).convertFrom(r3);
    L5:
        e = move-exception;
        LOG.log(Level.SEVERE, _UrlKt.FRAGMENT_ENCODE_SET, e);
        return null;
    L7:
        e = move-exception;
        LOG.log(Level.SEVERE, _UrlKt.FRAGMENT_ENCODE_SET, e);
        return null;
    }
}
