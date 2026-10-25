package com.google.mlkit.nl.languageid;

import com.google.android.apps.common.proguard.UsedByNative;
import com.google.android.gms.internal.mlkit_language_id_common.zze;
import com.google.android.gms.internal.mlkit_language_id_common.zzf;
import com.google.android.gms.internal.mlkit_language_id_common.zzg;
import java.util.Arrays;

@UsedByNative("language_id_jni.cc")
/* loaded from: classes5.dex */
public final class IdentifiedLanguage {
    private final String zza;
    private final float zzb;

    @UsedByNative("language_id_jni.cc")
    public IdentifiedLanguage(String r1, float r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof IdentifiedLanguage) == true) goto L8;
        return false;
    L8:
        IdentifiedLanguage r52 = (IdentifiedLanguage) r5;
        if (Float.compare(r52.zzb, this.zzb) == 0) goto L11;
    L13:
        return false;
    L11:
        if (zzg.zza(this.zza, r52.zza) == false) goto L13;
        return true;
    }

    public float getConfidence() {
        return this.zzb;
    }

    public String getLanguageTag() {
        return this.zza;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, Float.valueOf(this.zzb)});
    }

    public String toString() {
        zze r0 = zzf.zza(this);
        r0.zzb("languageTag", this.zza);
        r0.zza("confidence", this.zzb);
        return r0.toString();
    }
}
