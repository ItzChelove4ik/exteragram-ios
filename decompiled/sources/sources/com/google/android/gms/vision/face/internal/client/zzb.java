package com.google.android.gms.vision.face.internal.client;

import android.content.Context;
import android.graphics.PointF;
import android.media.Image;
import android.os.RemoteException;
import android.util.Log;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.vision.zzs;
import com.google.android.gms.internal.vision.zzt;
import com.google.android.gms.internal.vision.zzu;
import com.google.android.gms.vision.face.Contour;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public final class zzb extends zzt<zzh> {
    private final zzf zza;

    public zzb(Context r3, zzf r4) {
        super(r3, "FaceNativeHandle", "face");
        this.zza = r4;
        zzd();
    }

    private static Face zza(FaceParcel r17) {
        int r1 = r17.zza;
        PointF r2 = new PointF(r17.zzb, r17.zzc);
        float r3 = r17.zzd;
        float r4 = r17.zze;
        float r5 = r17.zzf;
        float r6 = r17.zzg;
        float r7 = r17.zzh;
        LandmarkParcel[] r9 = r17.zzi;
        if (r9 != null) goto L6;
        Landmark[] r92 = new Landmark[0];
    L5:
        int r16 = r1;
        zza[] r12 = r17.zzm;
        if (r12 != null) goto L14;
        Contour[] r13 = new Contour[0];
    L20:
        return new Face(r16, r2, r3, r4, r5, r6, r7, r92, r13, r17.zzj, r17.zzk, r17.zzl, r17.zzn);
    L14:
        int r10 = 0;
        Contour[] r11 = new Contour[r12.length];
    L16:
        if (r10 >= r12.length) goto L18;
        zza r122 = r12[r10];
        r11[r10] = new Contour(r122.zza, r122.zzb);
        r10 = r10 + 1;
        goto L16
    L18:
        r13 = r11;
        goto L20
    L6:
        Landmark[] r112 = new Landmark[r9.length];
        int r123 = 0;
    L8:
        if (r123 >= r9.length) goto L10;
        LandmarkParcel r132 = r9[r123];
        r112[r123] = new Landmark(new PointF(r132.zza, r132.zzb), r132.zzc);
        r123 = r123 + 1;
        r1 = r1;
        goto L8
    L10:
        r92 = r112;
        goto L5
    }

    @Override // com.google.android.gms.internal.vision.zzt
    public final /* synthetic */ zzh zza(DynamiteModule r2, Context r3) {
        if (zzu.zza(r3, "com.google.android.gms.vision.dynamite.face") == false) goto L5;
        zzi r22 = zzl.asInterface(r2.instantiate("com.google.android.gms.vision.face.NativeFaceDetectorV2Creator"));
    L6:
        if (r22 != null) goto L10;
        return null;
    L10:
        return r22.newFaceDetector(ObjectWrapper.wrap(r3), (zzf) Preconditions.checkNotNull(this.zza));
    L5:
        r22 = zzl.asInterface(r2.instantiate("com.google.android.gms.vision.face.ChimeraNativeFaceDetectorCreator"));
        goto L6
    }

    @Override // com.google.android.gms.internal.vision.zzt
    public final void zza() {
        ((zzh) Preconditions.checkNotNull(zzd())).zza();
    }

    public final Face[] zza(ByteBuffer r3, zzs r4) {
        int r1 = 0;
        if (zzb() == false) goto L5;
        IObjectWrapper r32 = ObjectWrapper.wrap(r3);     // Catch: RemoteException -> L12
        FaceParcel[] r2 = ((zzh) Preconditions.checkNotNull(zzd())).zza(r32, r4);     // Catch: RemoteException -> L12
        Face[] r33 = new Face[r2.length];
    L9:
        if (r1 >= r2.length) goto L11;
        r33[r1] = zza(r2[r1]);
        r1 = r1 + 1;
        goto L9
    L11:
        return r33;
    L12:
        e = move-exception;
        Log.e("FaceNativeHandle", "Could not call native face detector", e);
        return new Face[0];
    L5:
        return new Face[0];
    }

    public final Face[] zza(Image.Plane[] r16, zzs r17) {
        int r3 = 0;
        if (zzb() == true) goto L6;
        Log.e("FaceNativeHandle", "Native handle is not ready to be used.");
        return new Face[0];
    L6:
        if (r16 != null) goto L8;
    L21:
        FaceParcel[] r15 = ((zzh) Preconditions.checkNotNull(zzd())).zza(ObjectWrapper.wrap(r16[0].getBuffer()), ObjectWrapper.wrap(r16[1].getBuffer()), ObjectWrapper.wrap(r16[2].getBuffer()), r16[0].getPixelStride(), r16[1].getPixelStride(), r16[2].getPixelStride(), r16[0].getRowStride(), r16[1].getRowStride(), r16[2].getRowStride(), r17);     // Catch: RemoteException -> L18
        Face[] r0 = new Face[r15.length];
    L15:
        if (r3 >= r15.length) goto L17;
        r0[r3] = zza(r15[r3]);
        r3 = r3 + 1;
        goto L15
    L17:
        return r0;
    L18:
        e = move-exception;
        Log.e("FaceNativeHandle", "Could not call native face detector", e);
        return new Face[0];
    L8:
        if (r16.length == 3) goto L21;
        f$$ExternalSyntheticBUOutline1.m("Only android.graphics.ImageFormat#YUV_420_888 is supported which should have 3 planes.");
        return null;
    }
}
