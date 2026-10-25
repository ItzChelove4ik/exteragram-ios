package com.google.android.recaptcha.internal;

import kotlin.text.Regex;

/* loaded from: classes5.dex */
public abstract class zzja {
    private static final Regex zzb = null;

    static {
        zzb = new Regex("^[a-zA-Z0-9/_]{1,100}$");
    }

    public static final Regex zza() {
        return zzb;
    }
}
