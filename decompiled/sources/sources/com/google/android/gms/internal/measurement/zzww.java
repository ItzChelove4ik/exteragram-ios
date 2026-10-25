package com.google.android.gms.internal.measurement;

import android.text.TextUtils;
import com.google.common.collect.ImmutableList;
import java.util.UUID;

/* loaded from: classes4.dex */
public abstract class zzww {
    public zzww() {
    }

    public final String toString() {
        return TextUtils.join(" -> ", zza());
    }

    public abstract ImmutableList zza();

    public abstract ImmutableList zzb();

    public abstract UUID zzc();

    public abstract long zzd();
}
