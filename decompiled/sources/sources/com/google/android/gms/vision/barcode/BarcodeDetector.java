package com.google.android.gms.vision.barcode;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.SparseArray;
import androidx.annotation.RecentlyNonNull;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.vision.zzs;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public final class BarcodeDetector extends Detector<Barcode> {
    private final com.google.android.gms.internal.vision.zzm zza;

    public static class Builder {
        private Context zza;
        private com.google.android.gms.internal.vision.zzk zzb;

        public Builder(@RecentlyNonNull Context r1) {
            this.zza = r1;
            this.zzb = new com.google.android.gms.internal.vision.zzk();
        }

        @RecentlyNonNull
        public BarcodeDetector build() {
            return new BarcodeDetector(new com.google.android.gms.internal.vision.zzm(this.zza, this.zzb), null);
        }

        @RecentlyNonNull
        public Builder setBarcodeFormats(int r2) {
            this.zzb.zza = r2;
            return this;
        }
    }

    private BarcodeDetector(com.google.android.gms.internal.vision.zzm r1) {
        this.zza = r1;
    }

    public /* synthetic */ BarcodeDetector(com.google.android.gms.internal.vision.zzm r1, zzc r2) {
        this(r1);
    }

    @RecentlyNonNull
    public final SparseArray<Barcode> detect(@RecentlyNonNull Frame r12) {
        if (r12 == null) goto L19;
        zzs r1 = zzs.zza(r12);
        int r3 = 0;
        if (r12.getBitmap() == null) goto L12;
        Barcode[] r11 = this.zza.zza((Bitmap) Preconditions.checkNotNull(r12.getBitmap()), r1);
        if (r11 != null) goto L15;
        f$$ExternalSyntheticBUOutline1.m("Internal barcode detector error; check logcat output.");
        return null;
    L15:
        SparseArray<Barcode> r122 = new SparseArray(r11.length);
        int r0 = r11.length;
    L16:
        if (r3 >= r0) goto L18;
        Barcode r13 = r11[r3];
        r122.append(r13.rawValue.hashCode(), r13);
        r3 = r3 + 1;
        goto L16
    L18:
        return r122;
    L12:
        if (r12.getPlanes() == null) goto L14;
        ByteBuffer r02 = ((Image.Plane[]) Preconditions.checkNotNull(r12.getPlanes()))[0].getBuffer();
        zzs r4 = new zzs(((Image.Plane[]) Preconditions.checkNotNull(r12.getPlanes()))[0].getRowStride(), r1.zzb, r1.zzc, r1.zzd, r1.zze);
        r11 = this.zza.zza((ByteBuffer) Preconditions.checkNotNull(r02), r4);
        goto L15
    L14:
        ByteBuffer r123 = r12.getGrayscaleImageData();
        r11 = this.zza.zza((ByteBuffer) Preconditions.checkNotNull(r123), r1);
        goto L15
    L19:
        f$$ExternalSyntheticBUOutline1.m("No frame supplied.");
        return null;
    }

    public final boolean isOperational() {
        return this.zza.zzb();
    }

    @Override // com.google.android.gms.vision.Detector
    public final void release() {
        super.release();
        this.zza.zzc();
    }
}
