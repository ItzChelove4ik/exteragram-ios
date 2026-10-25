package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;

/* loaded from: classes4.dex */
public final class zzyr implements Comparator {
    public zzyr() {
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return ComparisonsKt.compareValues(Float.valueOf(((zzhv) r2).zzb()), Float.valueOf(((zzhv) r1).zzb()));
    }
}
