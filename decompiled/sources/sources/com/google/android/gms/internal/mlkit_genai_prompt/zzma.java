package com.google.android.gms.internal.mlkit_genai_prompt;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
final class zzma extends zzmb {
    private static final zzma zzb = null;

    static {
        zzb = new zzma();
    }

    private zzma() {
        super(_UrlKt.FRAGMENT_ENCODE_SET);
    }

    public static /* synthetic */ zzma zzd() {
        return zzb;
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object r1) {
        return zzc((zzmb) r1);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmb
    public final int hashCode() {
        return System.identityHashCode(this);
    }

    public final String toString() {
        return "-∞";
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmb
    public final void zza(StringBuilder r1) {
        r1.append("(-∞");
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmb
    public final void zzb(StringBuilder r1) {
        throw new AssertionError();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzmb
    public final int zzc(zzmb r1) {
        if (r1 != this) goto L5;
        return 0;
    L5:
        return -1;
    }
}
