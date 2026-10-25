package com.google.mlkit.vision.segmentation.subject;

import android.graphics.Bitmap;
import java.nio.FloatBuffer;

/* loaded from: classes5.dex */
public class Subject {
    private final FloatBuffer zza;
    private final Bitmap zzb;
    private final int zzc;
    private final int zzd;
    private final int zze;
    private final int zzf;

    public Subject(FloatBuffer r1, Bitmap r2, int r3, int r4, int r5, int r6) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
    }

    public Bitmap getBitmap() {
        return this.zzb;
    }

    public int getHeight() {
        return this.zzd;
    }

    public int getStartX() {
        return this.zze;
    }

    public int getStartY() {
        return this.zzf;
    }

    public int getWidth() {
        return this.zzc;
    }
}
