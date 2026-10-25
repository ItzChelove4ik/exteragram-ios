package com.google.android.gms.vision.face;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.RecentlyNonNull;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.vision.zzs;
import com.google.android.gms.internal.vision.zzw;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.Frame$Builder$$ExternalSyntheticBUOutline0;
import com.google.android.gms.vision.face.internal.client.zzb;
import com.google.android.gms.vision.face.internal.client.zzf;
import com.google.android.gms.vision.zzc;
import java.nio.ByteBuffer;
import java.util.HashSet;

/* loaded from: classes5.dex */
public final class FaceDetector extends Detector<Face> {
    private final zzc zza;
    private final zzb zzb;
    private final Object zzc;
    private boolean zzd;

    public static class Builder {
        private final Context zza;
        private int zzb;
        private boolean zzc;
        private int zzd;
        private boolean zze;
        private int zzf;
        private float zzg;

        public Builder(@RecentlyNonNull Context r3) {
            this.zzb = 0;
            this.zzc = false;
            this.zzd = 0;
            this.zze = true;
            this.zzf = 0;
            this.zzg = -1.0f;
            this.zza = r3;
        }

        @RecentlyNonNull
        public FaceDetector build() {
            zzf r0 = new zzf();
            r0.zza = this.zzf;
            r0.zzb = this.zzb;
            r0.zzc = this.zzd;
            r0.zzd = this.zzc;
            r0.zze = this.zze;
            r0.zzf = this.zzg;
            if (FaceDetector.zza(r0) == true) goto L5;
            f$$ExternalSyntheticBUOutline1.m("Invalid build options");
            return null;
        L5:
            return new FaceDetector(new zzb(this.zza, r0), null);
        }

        @RecentlyNonNull
        public Builder setLandmarkType(int r2) {
            if (r2 != 0) goto L4;
        L10:
            this.zzb = r2;
            return this;
        L4:
            if (r2 == 1) goto L10;
            if (r2 == 2) goto L10;
            Frame$Builder$$ExternalSyntheticBUOutline0.m(34, "Invalid landmark type: ", r2);
            return null;
        }

        @RecentlyNonNull
        public Builder setMode(int r2) {
            if (r2 != 0) goto L4;
        L10:
            this.zzf = r2;
            return this;
        L4:
            if (r2 == 1) goto L10;
            if (r2 == 2) goto L10;
            Frame$Builder$$ExternalSyntheticBUOutline0.m(25, "Invalid mode: ", r2);
            return null;
        }

        @RecentlyNonNull
        public Builder setTrackingEnabled(boolean r1) {
            this.zze = r1;
            return this;
        }
    }

    private FaceDetector(zzb r2) {
        this.zza = new zzc();
        this.zzc = new Object();
        this.zzd = true;
        this.zzb = r2;
    }

    public /* synthetic */ FaceDetector(zzb r1, zza r2) {
        this(r1);
    }

    public static /* synthetic */ boolean zza(zzf r0) {
        return zzb(r0);
    }

    private static boolean zzb(zzf r6) {
        if (r6.zza != 2) goto L5;
    L7:
        boolean r0 = true;
    L9:
        if (r6.zzb == 2) goto L11;
    L14:
        return r0;
    L11:
        if (r6.zzc != 1) goto L14;
        Log.e("FaceDetector", "Classification is not supported with contour.");
        return false;
    L5:
        if (r6.zzb != 2) goto L7;
        Log.e("FaceDetector", "Contour is not supported for non-SELFIE mode.");
        r0 = false;
        goto L9
    }

    @RecentlyNonNull
    public final SparseArray<Face> detect(@RecentlyNonNull Frame r9) {
        if (r9 != null) goto L4;
        f$$ExternalSyntheticBUOutline1.m("No frame supplied.");
        return null;
    L4:
        if (r9.getPlanes() == null) goto L21;
        if (((Image.Plane[]) Preconditions.checkNotNull(r9.getPlanes())).length != 3) goto L21;
        Object r0 = this.zzc;
        monitor-enter(r0);
    L14:
        th = move-exception;
        throw th;
    L10:
        if (this.zzd == false) goto L17;
        Face[] r92 = this.zzb.zza((Image.Plane[]) Preconditions.checkNotNull(r9.getPlanes()), zzs.zza(r9));     // Catch: Throwable -> L14
        monitor-exit(r0);     // Catch: Throwable -> L14
    L30:
        HashSet r02 = new HashSet();
        SparseArray<Face> r1 = new SparseArray(r92.length);
        int r2 = r92.length;
        int r3 = 0;
        int r4 = 0;
    L31:
        if (r3 >= r2) goto L36;
        Face r5 = r92[r3];
        int r6 = r5.getId();
        r4 = Math.max(r4, r6);
        if (r02.contains(Integer.valueOf(r6)) == false) goto L35;
        r6 = r4 + 1;
        r4 = r6;
    L35:
        r02.add(Integer.valueOf(r6));
        r1.append(this.zza.zza(r6), r5);
        r3 = r3 + 1;
        goto L31
    L36:
        return r1;
    L17:
        throw new IllegalStateException("Cannot use detector after release()");     // Catch: Throwable -> L14
    L21:
        if (r9.getBitmap() == null) goto L23;
        ByteBuffer r03 = zzw.zza((Bitmap) Preconditions.checkNotNull(r9.getBitmap()), true);
    L24:
        Object r12 = this.zzc;
        monitor-enter(r12);
    L37:
        th = move-exception;
        throw th;
    L27:
        if (this.zzd == false) goto L40;
        r92 = this.zzb.zza((ByteBuffer) Preconditions.checkNotNull(r03), zzs.zza(r9));     // Catch: Throwable -> L37
        monitor-exit(r12);     // Catch: Throwable -> L37
        goto L30
    L40:
        throw new IllegalStateException("Cannot use detector after release()");     // Catch: Throwable -> L37
    L23:
        r03 = r9.getGrayscaleImageData();
        goto L24
    }

    public final void finalize() {
        Object r0 = this.zzc;     // Catch: Throwable -> L14
        monitor-enter(r0);     // Catch: Throwable -> L14
    L7:
        th = move-exception;
        throw th;     // Catch: Throwable -> L14
    L5:
        if (this.zzd == false) goto L9;
        Log.w("FaceDetector", "FaceDetector was not released with FaceDetector.release()");     // Catch: Throwable -> L7
        release();     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        super.finalize();
        return;
    L14:
        th = move-exception;
        super.finalize();
        throw th;
    }

    public final boolean isOperational() {
        return this.zzb.zzb();
    }

    @Override // com.google.android.gms.vision.Detector
    public final void release() {
        super.release();
        Object r0 = this.zzc;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.zzd == true) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        this.zzb.zzc();     // Catch: Throwable -> L8
        this.zzd = false;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
    }
}
