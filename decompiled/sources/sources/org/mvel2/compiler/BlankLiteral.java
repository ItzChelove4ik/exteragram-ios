package org.mvel2.compiler;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class BlankLiteral implements Serializable {
    public static final BlankLiteral INSTANCE = null;

    static {
        INSTANCE = new BlankLiteral();
    }

    public BlankLiteral() {
    }

    public boolean equals(Object r3) {
        if (r3 != null) goto L5;
    L33:
        return true;
    L5:
        if (String.valueOf(r3).trim().length() == 0) goto L33;
        if (ParseTools.isNumeric(r3) == false) goto L12;
        return MVEL.VERSION_SUB.equals(String.valueOf(r3));
    L12:
        if ((r3 instanceof Collection) == false) goto L18;
        if (((Collection) r3).size() != 0) goto L16;
        return true;
    L16:
        return false;
    L18:
        if (r3.getClass().isArray() == false) goto L24;
        if (Array.getLength(r3) != 0) goto L22;
        return true;
    L22:
        return false;
    L24:
        if ((r3 instanceof Boolean) == false) goto L28;
        return true ^ ((Boolean) r3).booleanValue();
    L28:
        if ((r3 instanceof Map) == true) goto L30;
    L32:
        return false;
    L30:
        if (((Map) r3).size() != 0) goto L32;
        return true;
    }

    public String toString() {
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }
}
