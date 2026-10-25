package com.google.mlkit.vision.common;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;
import android.os.SystemClock;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_vision_common.zzms;
import com.google.android.gms.internal.mlkit_vision_common.zzmu;
import com.google.mlkit.common.sdkinternal.MLTaskInput;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public class InputImage implements MLTaskInput {
    private volatile Bitmap zza;
    private volatile ByteBuffer zzb;
    private final int zzd;
    private final int zze;
    private final int zzf;
    private final int zzg;
    private final Matrix zzh;

    private InputImage(Bitmap r2, int r3) {
        this.zza = (Bitmap) Preconditions.checkNotNull(r2);
        this.zzd = r2.getWidth();
        this.zze = r2.getHeight();
        zza(r3);
        this.zzf = r3;
        this.zzg = -1;
        this.zzh = null;
    }

    public static InputImage fromBitmap(Bitmap r9, int r10) {
        long r2 = SystemClock.elapsedRealtime();
        InputImage r8 = new InputImage(r9, r10);
        zzc(-1, 1, r2, r9.getHeight(), r9.getWidth(), r9.getAllocationByteCount(), r10);
        return r8;
    }

    private static int zza(int r2) {
        boolean r0 = true;
        if (r2 != 0) goto L5;
    L12:
        Preconditions.checkArgument(r0, "Invalid rotation. Only 0, 90, 180, 270 are supported currently.");
        return r2;
    L5:
        if (r2 == 90) goto L12;
        if (r2 == 180) goto L12;
        if (r2 != 270) goto L11;
        r2 = 270;
        goto L12
    L11:
        r0 = false;
        goto L12
    }

    private static void zzc(int r10, int r11, long r12, int r14, int r15, int r16, int r17) {
        zzmu.zza(zzms.zzb("vision-common"), r10, r11, r12, r14, r15, r16, r17);
    }

    public Bitmap getBitmapInternal() {
        return this.zza;
    }

    public ByteBuffer getByteBuffer() {
        return this.zzb;
    }

    public int getFormat() {
        return this.zzg;
    }

    public int getHeight() {
        return this.zze;
    }

    public Image getMediaImage() {
        return null;
    }

    public Image.Plane[] getPlanes() {
        return null;
    }

    public int getRotationDegrees() {
        return this.zzf;
    }

    public int getWidth() {
        return this.zzd;
    }
}
