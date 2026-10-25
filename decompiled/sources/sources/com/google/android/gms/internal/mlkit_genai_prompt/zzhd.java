package com.google.android.gms.internal.mlkit_genai_prompt;

import android.graphics.Bitmap;
import android.net.Uri;

/* loaded from: classes4.dex */
final class zzhd extends zzhs {
    private final int zza;
    private final String zzb;
    private final Bitmap zzc;
    private final int zzd;

    public zzhd(int r1, String r2, Bitmap r3, Uri r4, Uri r5, zzhq r6, zzhr r7, int r8) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r8;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzhs) == false) goto L27;
        zzhs r52 = (zzhs) r5;
        if (this.zza != r52.zza()) goto L27;
        String r1 = this.zzb;
        if (r1 != null) goto L15;
        if (r52.zzb() != null) goto L27;
    L16:
        Bitmap r12 = this.zzc;
        if (r12 != null) goto L22;
        if (r52.zzc() != null) goto L27;
    L24:
        r52.zzd();
        r52.zze();
        r52.zzf();
        r52.zzg();
        if (this.zzd != r52.zzh()) goto L27;
        return true;
    L22:
        if (r12.equals(r52.zzc()) == true) goto L24;
    L15:
        if (r1.equals(r52.zzb()) == true) goto L16;
    L27:
        return false;
    }

    public final int hashCode() {
        String r0 = this.zzb;
        int r1 = 0;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        int r2 = this.zza;
        Bitmap r3 = this.zzc;
        if (r3 == null) goto L11;
        r1 = r3.hashCode();
    L11:
        return this.zzd ^ ((((r02 ^ ((r2 ^ 1000003) * 1000003)) * 1000003) ^ r1) * (-429739981));
    L5:
        r02 = r0.hashCode();
        goto L6
    }

    public final String toString() {
        String r0 = String.valueOf(this.zzc);
        int r1 = this.zza;
        int r2 = String.valueOf(r1).length();
        String r3 = this.zzb;
        int r4 = String.valueOf(r3).length();
        int r5 = r0.length();
        int r7 = this.zzd;
        int r22 = ((((r2 + 26) + r4) + 9) + r5) + 68;
        StringBuilder r42 = new StringBuilder((r22 + String.valueOf(r7).length()) + 1);
        r42.append("LlmMessage{role=");
        r42.append(r1);
        r42.append(", message=");
        r42.append(r3);
        r42.append(", bitmap=");
        r42.append(r0);
        r42.append(", imageEmbedding=null, uriToWrite=null, audio=null, blob=null, type=");
        r42.append(r7);
        r42.append("}");
        return r42.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhs
    public final int zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhs
    public final String zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhs
    public final Bitmap zzc() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhs
    public final Uri zzd() {
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhs
    public final Uri zze() {
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhs
    public final zzhq zzf() {
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhs
    public final zzhr zzg() {
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhs
    public final int zzh() {
        return this.zzd;
    }
}
