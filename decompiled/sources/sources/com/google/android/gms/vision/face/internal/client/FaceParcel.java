package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.apps.common.proguard.UsedByNative;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

@UsedByNative("wrapper.cc")
/* loaded from: classes5.dex */
public class FaceParcel extends AbstractSafeParcelable {

    @RecentlyNonNull
    public static final Parcelable.Creator<FaceParcel> CREATOR = null;
    public final int zza;
    public final float zzb;
    public final float zzc;
    public final float zzd;
    public final float zze;
    public final float zzf;
    public final float zzg;
    public final float zzh;

    @RecentlyNonNull
    public final LandmarkParcel[] zzi;
    public final float zzj;
    public final float zzk;
    public final float zzl;
    public final zza[] zzm;
    public final float zzn;
    private final int zzo;

    static {
        CREATOR = new zzd();
    }

    public FaceParcel(int r1, int r2, float r3, float r4, float r5, float r6, float r7, float r8, float r9, LandmarkParcel[] r10, float r11, float r12, float r13, zza[] r14, float r15) {
        this.zzo = r1;
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
        this.zze = r6;
        this.zzf = r7;
        this.zzg = r8;
        this.zzh = r9;
        this.zzi = r10;
        this.zzj = r11;
        this.zzk = r12;
        this.zzl = r13;
        this.zzm = r14;
        this.zzn = r15;
    }

    @UsedByNative("wrapper.cc")
    public FaceParcel(int r18, int r19, float r20, float r21, float r22, float r23, float r24, float r25, @RecentlyNonNull LandmarkParcel[] r26, float r27, float r28, float r29) {
        this(r18, r19, r20, r21, r22, r23, r24, r25, 0.0f, r26, r27, r28, r29, new zza[0], -1.0f);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@RecentlyNonNull Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeInt(r5, 1, this.zzo);
        SafeParcelWriter.writeInt(r5, 2, this.zza);
        SafeParcelWriter.writeFloat(r5, 3, this.zzb);
        SafeParcelWriter.writeFloat(r5, 4, this.zzc);
        SafeParcelWriter.writeFloat(r5, 5, this.zzd);
        SafeParcelWriter.writeFloat(r5, 6, this.zze);
        SafeParcelWriter.writeFloat(r5, 7, this.zzf);
        SafeParcelWriter.writeFloat(r5, 8, this.zzg);
        SafeParcelWriter.writeTypedArray(r5, 9, this.zzi, r6, false);
        SafeParcelWriter.writeFloat(r5, 10, this.zzj);
        SafeParcelWriter.writeFloat(r5, 11, this.zzk);
        SafeParcelWriter.writeFloat(r5, 12, this.zzl);
        SafeParcelWriter.writeTypedArray(r5, 13, this.zzm, r6, false);
        SafeParcelWriter.writeFloat(r5, 14, this.zzh);
        SafeParcelWriter.writeFloat(r5, 15, this.zzn);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
