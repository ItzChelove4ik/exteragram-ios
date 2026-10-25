package com.google.android.gms.internal.measurement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes4.dex */
public @interface zzyw {
    public static final zzyl zza = null;

    static {
        zza = zzyl.zzc("android_log_tag", String.class);
    }
}
