package com.google.android.gms.vision;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import c.f$$ExternalSyntheticBUOutline1;
import java.nio.ByteBuffer;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public class Frame {
    private final Metadata zza;
    private ByteBuffer zzb;
    private Bitmap zzd;

    public static class Builder {
        private final Frame zza;

        public Builder() {
            this.zza = new Frame(null);
        }

        @RecentlyNonNull
        public Frame build() {
            if (Frame.zza(this.zza) != null) goto L10;
            if (Frame.zzb(this.zza) != null) goto L10;
            Frame.zzc(this.zza);
            Segment$$ExternalSyntheticBUOutline1.m("Missing image data.  Call either setBitmap or setImageData to specify the image");
            return null;
        L10:
            return this.zza;
        }

        @RecentlyNonNull
        public Builder setBitmap(@RecentlyNonNull Bitmap r4) {
            int r0 = r4.getWidth();
            int r1 = r4.getHeight();
            Frame.zza(this.zza, r4);
            Metadata r42 = this.zza.getMetadata();
            Metadata.zza(r42, r0);
            Metadata.zzb(r42, r1);
            return this;
        }

        @RecentlyNonNull
        public Builder setImageData(@RecentlyNonNull ByteBuffer r4, int r5, int r6, int r7) {
            if (r4 != null) goto L5;
            f$$ExternalSyntheticBUOutline1.m("Null image data supplied.");
            return null;
        L5:
            if (r4.capacity() >= (r5 * r6)) goto L7;
            f$$ExternalSyntheticBUOutline1.m("Invalid image data size.");
            return null;
        L7:
            if (r7 != 16) goto L9;
        L15:
            Frame.zza(this.zza, r4);
            Metadata r42 = this.zza.getMetadata();
            Metadata.zza(r42, r5);
            Metadata.zzb(r42, r6);
            Metadata.zzc(r42, r7);
            return this;
        L9:
            if (r7 == 17) goto L15;
            if (r7 == 842094169) goto L15;
            Frame$Builder$$ExternalSyntheticBUOutline0.m(37, "Unsupported image format: ", r7);
            return null;
        }

        @RecentlyNonNull
        public Builder setRotation(int r2) {
            Metadata.zze(this.zza.getMetadata(), r2);
            return this;
        }
    }

    public static class Metadata {
        private int zza;
        private int zzb;
        private int zzc;
        private long zzd;
        private int zze;
        private int zzf;

        public Metadata() {
            this.zzf = -1;
        }

        public static /* synthetic */ int zza(Metadata r0, int r1) {
            r0.zza = r1;
            return r1;
        }

        public static /* synthetic */ int zzb(Metadata r0, int r1) {
            r0.zzb = r1;
            return r1;
        }

        public static /* synthetic */ int zzc(Metadata r0, int r1) {
            r0.zzf = r1;
            return r1;
        }

        public static /* synthetic */ int zze(Metadata r0, int r1) {
            r0.zze = r1;
            return r1;
        }

        public int getHeight() {
            return this.zzb;
        }

        public int getId() {
            return this.zzc;
        }

        public int getRotation() {
            return this.zze;
        }

        public long getTimestampMillis() {
            return this.zzd;
        }

        public int getWidth() {
            return this.zza;
        }
    }

    public static class zza {
    }

    private Frame() {
        this.zza = new Metadata();
        this.zzb = null;
        this.zzd = null;
    }

    public /* synthetic */ Frame(zzb r1) {
        this();
    }

    public static /* synthetic */ Bitmap zza(Frame r0, Bitmap r1) {
        r0.zzd = r1;
        return r1;
    }

    public static /* synthetic */ ByteBuffer zza(Frame r0) {
        return r0.zzb;
    }

    public static /* synthetic */ ByteBuffer zza(Frame r0, ByteBuffer r1) {
        r0.zzb = r1;
        return r1;
    }

    public static /* synthetic */ Bitmap zzb(Frame r0) {
        return r0.zzd;
    }

    public static /* synthetic */ zza zzc(Frame r0) {
        r0.getClass();
        return null;
    }

    @RecentlyNullable
    public Bitmap getBitmap() {
        return this.zzd;
    }

    @RecentlyNullable
    public ByteBuffer getGrayscaleImageData() {
        Bitmap r0 = this.zzd;
        if (r0 == null) goto L13;
        if (r0 != null) goto L7;
        return null;
    L7:
        int r3 = r0.getWidth();
        int r7 = this.zzd.getHeight();
        int r8 = r3 * r7;
        this.zzd.getPixels(new int[r8], 0, r3, 0, 0, r3, r7);
        byte[] r9 = new byte[r8];
        int r02 = 0;
    L8:
        if (r02 >= r8) goto L11;
        r9[r02] = (byte) (((Color.red(r1[r02]) * 0.299f) + (Color.green(r1[r02]) * 0.587f)) + (Color.blue(r1[r02]) * 0.114f));
        r02 = r02 + 1;
        goto L8
    L11:
        return ByteBuffer.wrap(r9);
    L13:
        return this.zzb;
    }

    @RecentlyNonNull
    public Metadata getMetadata() {
        return this.zza;
    }

    @RecentlyNullable
    public Image.Plane[] getPlanes() {
        return null;
    }
}
