package com.google.android.gms.vision.face;

import android.graphics.PointF;
import androidx.annotation.RecentlyNonNull;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes5.dex */
public class Face {
    private int zza;
    private PointF zzb;
    private float zzc;
    private float zzd;
    private float zze;
    private float zzf;
    private float zzg;
    private List<Landmark> zzh;
    private final List<Contour> zzi;
    private float zzj;
    private float zzk;
    private float zzl;
    private final float zzm;

    public Face(int r1, @RecentlyNonNull PointF r2, float r3, float r4, float r5, float r6, float r7, @RecentlyNonNull Landmark[] r8, @RecentlyNonNull Contour[] r9, float r10, float r11, float r12, float r13) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
        this.zzg = r7;
        this.zzh = Arrays.asList(r8);
        this.zzi = Arrays.asList(r9);
        this.zzj = zza(r10);
        this.zzk = zza(r11);
        this.zzl = zza(r12);
        this.zzm = zza(r13);
    }

    private static float zza(float r1) {
        if (r1 >= 0.0f) goto L5;
        return -1.0f;
    L5:
        if (r1 > 1.0f) goto L9;
        return r1;
    L9:
        return -1.0f;
    }

    public int getId() {
        return this.zza;
    }

    @RecentlyNonNull
    public List<Landmark> getLandmarks() {
        return this.zzh;
    }
}
