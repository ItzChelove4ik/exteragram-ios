package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;

/* loaded from: classes4.dex */
public final class zzaae implements Comparator {
    public zzaae() {
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return ComparisonsKt.compareValues(Long.valueOf(((zzaab) r1).zzd()), Long.valueOf(((zzaab) r2).zzd()));
    }
}
