package androidx.core.util;

import android.annotation.SuppressLint;

/* loaded from: classes3.dex */
public abstract class TypedValueCompat {
    @SuppressLint({"WrongConstant"})
    public static int getUnitFromComplexDimension(int r0) {
        return r0 & 15;
    }
}
