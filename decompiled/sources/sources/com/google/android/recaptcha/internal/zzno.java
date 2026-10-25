package com.google.android.recaptcha.internal;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Iterator;
import java.util.Map;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* loaded from: classes5.dex */
public final class zzno {
    private final SharedPreferences zza;

    public zzno(Context r3) {
        this.zza = r3.getSharedPreferences("_GRECAPTCHA", 0);
    }

    public final Object zza(Map r2, Continuation r3) {
        SharedPreferences.Editor r1 = this.zza.edit();
        Iterator r22 = r2.entrySet().iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        Map.Entry r32 = (Map.Entry) r22.next();
        r1.putString((String) r32.getKey(), (String) r32.getValue());
        goto L4
    L6:
        r1.commit();
        return Unit.INSTANCE;
    }

    public final Object zzb(String r1, Continuation r2) {
        return this.zza.getString(r1, null);
    }
}
