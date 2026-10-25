package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcelable;
import java.util.Locale;
import java.util.function.Function;

/* loaded from: classes4.dex */
final /* synthetic */ class zzbs implements Function {
    static final /* synthetic */ zzbs zza = null;

    static {
        zza = new zzbs();
    }

    private /* synthetic */ zzbs() {
    }

    @Override // java.util.function.Function
    public final /* synthetic */ Object apply(Object r2) {
        zzjc r22 = (zzjc) r2;
        Parcelable.Creator<zzbt> r1 = zzbt.CREATOR;
        return String.format(Locale.getDefault(), "(%d, %d)", new Object[]{Integer.valueOf(r22.zza()), Integer.valueOf(r22.zzc())});
    }
}
