package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzcg extends zzp {
    public static final Parcelable.Creator<zzcg> CREATOR = null;
    public static final zzml zza = null;
    private final zzml zzb;
    private final float zzc;
    private final int zzd;
    private final zzml zze;
    private final int zzf;
    private final int zzg;
    private final zzbl zzh;
    private final boolean zzi;
    private final int zzj;
    private final int zzk;
    private final int zzl;
    private final int zzm;
    private final float zzn;
    private final String zzo;
    private final Bundle zzp;
    private final int zzq;
    private final zzcw zzr;
    private final int zzs;
    private final zzcb zzt;
    private final zzcp zzu;
    private final boolean zzv;
    private final zzdf zzw;
    private final zzbf zzx;

    static {
        CREATOR = new zzch();
        zza = zzml.zzn("auxiliary_lora_file", "auxiliary_drafter_lora_file", "auxiliary_session_state_file");
    }

    public zzcg(zzml r1, float r2, int r3, zzml r4, int r5, int r6, zzbl r7, boolean r8, int r9, zzbf r10, int r11, int r12, String r13, Bundle r14, int r15, zzcw r16, int r17, zzcb r18, int r19, float r20, zzcp r21, boolean r22, zzdf r23) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zzn = r20;
        this.zze = r4;
        this.zzf = r5;
        this.zzg = r6;
        this.zzh = r7;
        this.zzi = r8;
        this.zzj = r9;
        this.zzx = null;
        this.zzk = r11;
        this.zzl = r12;
        this.zzo = null;
        this.zzp = r14;
        this.zzq = r15;
        if (r16 != null) goto L5;
        zzcw r110 = zzcw.zza;
    L6:
        this.zzr = r110;
        this.zzs = 0;
        this.zzt = r18;
        this.zzm = 0;
        this.zzu = r21;
        this.zzv = r22;
        this.zzw = r23;
        return;
    L5:
        r110 = r16;
        goto L6
    }

    public zzcg(List r1, float r2, int r3, List r4, int r5, int r6, IBinder r7, boolean r8, int r9, IBinder r10, int r11, int r12, String r13, Bundle r14, int r15, zzcw r16, int r17, zzcb r18, int r19, float r20, zzcp r21, boolean r22, zzdf r23) {
        this.zzb = zzml.zzq(r1);
        this.zzc = r2;
        this.zzd = r3;
        this.zzn = r20;
        this.zze = zzml.zzq(r4);
        this.zzf = r5;
        this.zzg = r6;
        zzbf r110 = null;
        if (r7 != null) goto L5;
        zzbl r24 = null;
    L9:
        this.zzh = r24;
        this.zzi = r8;
        this.zzj = r9;
        if (r10 == null) goto L16;
        IInterface r111 = r10.queryLocalInterface("com.google.android.apps.aicore.aidl.IImageEmbeddingCallback");
        if ((r111 instanceof zzbf) == false) goto L15;
        r110 = (zzbf) r111;
        goto L16
    L15:
        r110 = new zzbf(r10);
    L16:
        this.zzx = r110;
        this.zzk = r11;
        this.zzl = r12;
        this.zzo = r13;
        this.zzp = r14;
        this.zzq = r15;
        if (r16 != null) goto L19;
        zzcw r112 = zzcw.zza;
    L20:
        this.zzr = r112;
        this.zzs = r17;
        this.zzt = r18;
        this.zzm = r19;
        this.zzu = r21;
        this.zzv = r22;
        this.zzw = r23;
        return;
    L19:
        r112 = r16;
        goto L20
    L5:
        IInterface r25 = r7.queryLocalInterface("com.google.android.apps.aicore.aidl.ILLMStreamingCallback");
        if ((r25 instanceof zzbl) == false) goto L8;
        r24 = (zzbl) r25;
        goto L9
    L8:
        r24 = new zzbj(r7);
        goto L9
    }

    public static zzcf zza() {
        zzt r0 = new zzt();
        r0.zzj(0);
        r0.zzk(0);
        r0.zzq(0);
        r0.zzd(zzml.zzk());
        r0.zzm(123);
        r0.zzn(zzcw.zza);
        r0.zzo(0);
        r0.zzr(1.0f);
        r0.zzt(false);
        r0.zzu(null);
        return r0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r6, int r7) {
        zzml r0 = this.zzb;
        int r1 = SafeParcelWriter.beginObjectHeader(r6);
        SafeParcelWriter.writeTypedList(r6, 1, r0, false);
        SafeParcelWriter.writeFloat(r6, 2, this.zzc);
        SafeParcelWriter.writeInt(r6, 3, this.zzd);
        SafeParcelWriter.writeStringList(r6, 4, this.zze, false);
        SafeParcelWriter.writeInt(r6, 5, this.zzf);
        SafeParcelWriter.writeInt(r6, 6, this.zzg);
        zzbl r02 = this.zzh;
        IBinder r2 = null;
        if (r02 != null) goto L5;
        IBinder r03 = null;
    L6:
        SafeParcelWriter.writeIBinder(r6, 7, r03, false);
        SafeParcelWriter.writeBoolean(r6, 8, this.zzi);
        SafeParcelWriter.writeInt(r6, 9, this.zzj);
        zzbf r04 = this.zzx;
        if (r04 == null) goto L10;
        r2 = r04.asBinder();
    L10:
        SafeParcelWriter.writeIBinder(r6, 10, r2, false);
        SafeParcelWriter.writeInt(r6, 11, this.zzk);
        SafeParcelWriter.writeInt(r6, 12, this.zzl);
        SafeParcelWriter.writeString(r6, 13, this.zzo, false);
        SafeParcelWriter.writeBundle(r6, 14, this.zzp, false);
        SafeParcelWriter.writeInt(r6, 15, this.zzq);
        SafeParcelWriter.writeParcelable(r6, 16, this.zzr, r7, false);
        SafeParcelWriter.writeInt(r6, 17, this.zzs);
        SafeParcelWriter.writeParcelable(r6, 18, this.zzt, r7, false);
        SafeParcelWriter.writeInt(r6, 19, this.zzm);
        SafeParcelWriter.writeFloat(r6, 20, this.zzn);
        SafeParcelWriter.writeParcelable(r6, 21, this.zzu, r7, false);
        SafeParcelWriter.writeBoolean(r6, 22, this.zzv);
        SafeParcelWriter.writeParcelable(r6, 23, this.zzw, r7, false);
        SafeParcelWriter.finishObjectHeader(r6, r1);
        return;
    L5:
        r03 = r02.asBinder();
        goto L6
    }

    public final void zzb() {
        zzml r0 = this.zzb;
        int r1 = r0.size();
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L13;
        zzbz r4 = (zzbz) r0.get(r3);
        ParcelFileDescriptor r5 = r4.zza();
        if (r5 == null) goto L7;
        r5.close();
    L7:
        zzbv r42 = r4.zzb();
        if (r42 == null) goto L12;
        if (r42.zza() == null) goto L12;
        ParcelFileDescriptor r43 = r42.zza();
        r43.getClass();
        r43.close();
    L12:
        r3 = r3 + 1;
        goto L3
    L13:
        Bundle r02 = this.zzp;
        if (r02 == null) goto L21;
        zzml r12 = zza;
        int r32 = r12.size();
    L16:
        if (r2 >= r32) goto L21;
        ParcelFileDescriptor r44 = (ParcelFileDescriptor) r02.getParcelable((String) r12.get(r2), ParcelFileDescriptor.class);
        if (r44 == null) goto L20;
        r44.close();
    L20:
        r2 = r2 + 1;
    L21:
        zzcb r6 = this.zzt;
        if (r6 == null) goto L34;
        ParcelFileDescriptor r62 = r6.zza();
        if (r62 == null) goto L35;
        r62.close();
        return;
    L35:
        return;
    }
}
