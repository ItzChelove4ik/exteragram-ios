package com.google.mlkit.vision.label;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.mlkit_vision_label_common.zzd;
import com.google.android.gms.internal.mlkit_vision_label_common.zze;
import com.google.android.gms.internal.mlkit_vision_label_common.zzi;

/* loaded from: classes5.dex */
public class ImageLabel {
    private final String zza;
    private final float zzb;
    private final int zzc;
    private final String zzd;

    public ImageLabel(String r1, float r2, int r3, String r4) {
        this.zza = zzi.zza(r1);
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof ImageLabel) == true) goto L8;
        return false;
    L8:
        ImageLabel r52 = (ImageLabel) r5;
        if (Objects.equal(this.zza, r52.getText()) == true) goto L11;
    L17:
        return false;
    L11:
        if (Float.compare(this.zzb, r52.getConfidence()) != 0) goto L17;
        if (this.zzc != r52.getIndex()) goto L17;
        if (Objects.equal(this.zzd, r52.zzd) == false) goto L17;
        return true;
    }

    public float getConfidence() {
        return this.zzb;
    }

    public int getIndex() {
        return this.zzc;
    }

    public String getText() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, Float.valueOf(this.zzb), Integer.valueOf(this.zzc), this.zzd});
    }

    public String toString() {
        zzd r0 = zze.zza(this);
        r0.zzc("text", this.zza);
        r0.zza("confidence", this.zzb);
        r0.zzb("index", this.zzc);
        r0.zzc("mid", this.zzd);
        return r0.toString();
    }
}
