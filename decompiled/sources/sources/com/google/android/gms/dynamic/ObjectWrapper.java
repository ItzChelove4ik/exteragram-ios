package com.google.android.gms.dynamic;

import android.os.IBinder;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.internal.zzar$$ExternalSyntheticBUOutline1;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public final class ObjectWrapper<T> extends IObjectWrapper.Stub {
    private final Object zza;

    private ObjectWrapper(Object r1) {
        this.zza = r1;
    }

    public static <T> T unwrap(IObjectWrapper r8) {
        if ((r8 instanceof ObjectWrapper) == true) goto L5;
        IBinder r82 = r8.asBinder();
        Field[] r0 = r82.getClass().getDeclaredFields();
        int r1 = r0.length;
        int r3 = 0;
        Field r5 = null;
        int r4 = 0;
    L7:
        if (r3 >= r1) goto L13;
        Field r6 = r0[r3];
        if (r6.isSynthetic() == true) goto L11;
        r4 = r4 + 1;
        r5 = r6;
    L11:
        r3 = r3 + 1;
        goto L7
    L13:
        if (r4 != 1) goto L27;
        Preconditions.checkNotNull(r5);
        if (r5.isAccessible() == true) goto L25;
        r5.setAccessible(true);
        return (T) r5.get(r82);
    L19:
        e = move-exception;
        throw new IllegalArgumentException("Could not access the field in remoteBinder.", e);
    L22:
        e = move-exception;
        throw new IllegalArgumentException("Binder object is null.", e);
    L25:
        f$$ExternalSyntheticBUOutline1.m("IObjectWrapper declared field not private!");
        return null;
    L27:
        int r83 = r0.length;
        zzar$$ExternalSyntheticBUOutline1.m(String.valueOf(r83).length() + 53, "Unexpected number of IObjectWrapper declared fields: ", r83);
        return null;
    L5:
        return (T) ((ObjectWrapper) r8).zza;
    }

    public static <T> IObjectWrapper wrap(T r1) {
        return new ObjectWrapper(r1);
    }
}
