package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

/* loaded from: classes5.dex */
public final class zzr extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzr> CREATOR = null;
    public final long zzA;
    public final String zzB;
    public final String zzC;
    public final long zzD;
    public final int zzE;
    public final long zzF;
    public final String zza;
    public final String zzb;
    public final String zzc;
    public final String zzd;
    public final long zze;
    public final long zzf;
    public final String zzg;
    public final boolean zzh;
    public final boolean zzi;
    public final long zzj;
    public final String zzk;
    public final long zzl;
    public final int zzm;
    public final boolean zzn;
    public final boolean zzo;
    public final Boolean zzp;
    public final long zzq;
    public final List zzr;
    public final String zzs;
    public final String zzt;
    public final String zzu;
    public final boolean zzv;
    public final long zzw;
    public final int zzx;
    public final String zzy;
    public final int zzz;

    static {
        CREATOR = new zzs();
    }

    public zzr(String r2, String r3, String r4, long r5, String r7, long r8, long r10, String r12, boolean r13, boolean r14, String r15, long r16, int r18, boolean r19, boolean r20, Boolean r21, long r22, List r24, String r25, String r26, String r27, boolean r28, long r29, int r31, String r32, int r33, long r34, String r36, String r37, long r38, int r40, long r41) {
        Preconditions.checkNotEmpty(r2);
        this.zza = r2;
        if (true != TextUtils.isEmpty(r3)) goto L5;
        r3 = null;
    L5:
        this.zzb = r3;
        this.zzc = r4;
        this.zzj = r5;
        this.zzd = r7;
        this.zze = r8;
        this.zzf = r10;
        this.zzg = r12;
        this.zzh = r13;
        this.zzi = r14;
        this.zzk = r15;
        this.zzl = r16;
        this.zzm = r18;
        this.zzn = r19;
        this.zzo = r20;
        this.zzp = r21;
        this.zzq = r22;
        this.zzr = r24;
        this.zzs = r25;
        this.zzt = r26;
        this.zzu = r27;
        this.zzv = r28;
        this.zzw = r29;
        this.zzx = r31;
        this.zzy = r32;
        this.zzz = r33;
        this.zzA = r34;
        this.zzB = r36;
        this.zzC = r37;
        this.zzD = r38;
        this.zzE = r40;
        this.zzF = r41;
    }

    public zzr(String r1, String r2, String r3, String r4, long r5, long r7, String r9, boolean r10, boolean r11, long r12, String r14, long r15, int r17, boolean r18, boolean r19, Boolean r20, long r21, List r23, String r24, String r25, String r26, boolean r27, long r28, int r30, String r31, int r32, long r33, String r35, String r36, long r37, int r39, long r40) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzj = r12;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r7;
        this.zzg = r9;
        this.zzh = r10;
        this.zzi = r11;
        this.zzk = r14;
        this.zzl = r15;
        this.zzm = r17;
        this.zzn = r18;
        this.zzo = r19;
        this.zzp = r20;
        this.zzq = r21;
        this.zzr = r23;
        this.zzs = r24;
        this.zzt = r25;
        this.zzu = r26;
        this.zzv = r27;
        this.zzw = r28;
        this.zzx = r30;
        this.zzy = r31;
        this.zzz = r32;
        this.zzA = r33;
        this.zzB = r35;
        this.zzC = r36;
        this.zzD = r37;
        this.zzE = r39;
        this.zzF = r40;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r6, int r7) {
        String r72 = this.zza;
        int r0 = SafeParcelWriter.beginObjectHeader(r6);
        SafeParcelWriter.writeString(r6, 2, r72, false);
        SafeParcelWriter.writeString(r6, 3, this.zzb, false);
        SafeParcelWriter.writeString(r6, 4, this.zzc, false);
        SafeParcelWriter.writeString(r6, 5, this.zzd, false);
        SafeParcelWriter.writeLong(r6, 6, this.zze);
        SafeParcelWriter.writeLong(r6, 7, this.zzf);
        SafeParcelWriter.writeString(r6, 8, this.zzg, false);
        SafeParcelWriter.writeBoolean(r6, 9, this.zzh);
        SafeParcelWriter.writeBoolean(r6, 10, this.zzi);
        SafeParcelWriter.writeLong(r6, 11, this.zzj);
        SafeParcelWriter.writeString(r6, 12, this.zzk, false);
        SafeParcelWriter.writeLong(r6, 14, this.zzl);
        SafeParcelWriter.writeInt(r6, 15, this.zzm);
        SafeParcelWriter.writeBoolean(r6, 16, this.zzn);
        SafeParcelWriter.writeBoolean(r6, 18, this.zzo);
        SafeParcelWriter.writeBooleanObject(r6, 21, this.zzp, false);
        SafeParcelWriter.writeLong(r6, 22, this.zzq);
        SafeParcelWriter.writeStringList(r6, 23, this.zzr, false);
        SafeParcelWriter.writeString(r6, 25, this.zzs, false);
        SafeParcelWriter.writeString(r6, 26, this.zzt, false);
        SafeParcelWriter.writeString(r6, 27, this.zzu, false);
        SafeParcelWriter.writeBoolean(r6, 28, this.zzv);
        SafeParcelWriter.writeLong(r6, 29, this.zzw);
        SafeParcelWriter.writeInt(r6, 30, this.zzx);
        SafeParcelWriter.writeString(r6, 31, this.zzy, false);
        SafeParcelWriter.writeInt(r6, 32, this.zzz);
        SafeParcelWriter.writeLong(r6, 34, this.zzA);
        SafeParcelWriter.writeString(r6, 35, this.zzB, false);
        SafeParcelWriter.writeString(r6, 36, this.zzC, false);
        SafeParcelWriter.writeLong(r6, 37, this.zzD);
        SafeParcelWriter.writeInt(r6, 38, this.zzE);
        SafeParcelWriter.writeLong(r6, 39, this.zzF);
        SafeParcelWriter.finishObjectHeader(r6, r0);
    }
}
