package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzaf extends zzp {
    public static final Parcelable.Creator<zzaf> CREATOR = null;
    private final zzml zza;
    private final float zzb;
    private final int zzc;
    private final int zzd;
    private final int zze;
    private final zzbb zzf;
    private final zzml zzg;
    private final int zzh;
    private final int zzi;
    private final String zzj;
    private final String zzk;
    private final String zzl;

    @Deprecated
    private final String zzm;
    private final int zzn;
    private final int zzo;
    private final int zzp;
    private final zzbc zzq;

    static {
        CREATOR = new zzag();
    }

    public zzaf(zzml r1, float r2, int r3, int r4, zzbc r5, int r6, zzbb r7, zzml r8, int r9, int r10, String r11, String r12, String r13, String r14, int r15, int r16, int r17) {
        this.zza = r1;
        this.zzb = 0.0f;
        this.zzc = r3;
        this.zzd = r4;
        this.zzq = null;
        this.zze = r6;
        this.zzf = r7;
        this.zzg = r8;
        this.zzh = 0;
        this.zzi = 0;
        this.zzj = null;
        this.zzk = r12;
        this.zzl = r13;
        this.zzm = null;
        this.zzn = r15;
        this.zzo = 0;
        this.zzp = r17;
    }

    public zzaf(List r1, float r2, int r3, int r4, IBinder r5, int r6, IBinder r7, List r8, int r9, int r10, String r11, String r12, String r13, String r14, int r15, int r16, int r17) {
        this.zza = zzml.zzq(r1);
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        zzbb r18 = null;
        if (r5 != null) goto L5;
        zzbc r22 = null;
    L9:
        this.zzq = r22;
        this.zze = r6;
        if (r7 == null) goto L16;
        IInterface r19 = r7.queryLocalInterface("com.google.android.apps.aicore.aidl.ICortanaStateCallback");
        if ((r19 instanceof zzbb) == false) goto L15;
        r18 = (zzbb) r19;
        goto L16
    L15:
        r18 = new zzaz(r7);
    L16:
        this.zzf = r18;
        this.zzg = zzml.zzq(r8);
        this.zzh = r9;
        this.zzi = r10;
        this.zzj = r11;
        this.zzk = r12;
        this.zzl = r13;
        this.zzm = r14;
        this.zzn = r15;
        this.zzo = r16;
        this.zzp = r17;
        return;
    L5:
        IInterface r23 = r5.queryLocalInterface("com.google.android.apps.aicore.aidl.ICortanaStreamingCallback");
        if ((r23 instanceof zzbc) == false) goto L8;
        r22 = (zzbc) r23;
        goto L9
    L8:
        r22 = new zzbc(r5);
        goto L9
    }

    public static zzae zza() {
        zzs r0 = new zzs();
        r0.zza(zzml.zzk());
        r0.zzb(0.0f);
        r0.zzc(1);
        r0.zzd(MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);
        r0.zzh(0);
        r0.zzi(0);
        r0.zzm(0);
        r0.zzg(zzml.zzk());
        r0.zze(1);
        r0.zzl(-1);
        r0.zzn(1);
        r0.zzj("cortana_type_unspecified");
        return r0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        zzml r62 = this.zza;
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeTypedList(r5, 1, r62, false);
        SafeParcelWriter.writeFloat(r5, 2, this.zzb);
        SafeParcelWriter.writeInt(r5, 3, this.zzc);
        SafeParcelWriter.writeInt(r5, 4, this.zzd);
        zzbc r63 = this.zzq;
        IBinder r1 = null;
        if (r63 != null) goto L5;
        IBinder r64 = null;
    L6:
        SafeParcelWriter.writeIBinder(r5, 5, r64, false);
        SafeParcelWriter.writeInt(r5, 6, this.zze);
        zzbb r65 = this.zzf;
        if (r65 == null) goto L10;
        r1 = r65.asBinder();
    L10:
        SafeParcelWriter.writeIBinder(r5, 7, r1, false);
        SafeParcelWriter.writeTypedList(r5, 8, this.zzg, false);
        SafeParcelWriter.writeInt(r5, 9, this.zzh);
        SafeParcelWriter.writeInt(r5, 10, this.zzi);
        SafeParcelWriter.writeString(r5, 11, this.zzj, false);
        SafeParcelWriter.writeString(r5, 12, this.zzk, false);
        SafeParcelWriter.writeString(r5, 13, this.zzl, false);
        SafeParcelWriter.writeString(r5, 14, this.zzm, false);
        SafeParcelWriter.writeInt(r5, 15, this.zzn);
        SafeParcelWriter.writeInt(r5, 16, this.zzo);
        SafeParcelWriter.writeInt(r5, 17, this.zzp);
        SafeParcelWriter.finishObjectHeader(r5, r0);
        return;
    L5:
        r64 = r63.asBinder();
        goto L6
    }

    public final void zzb() {
        zzml r0 = this.zza;
        int r1 = r0.size();
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L11;
        zzaa r4 = (zzaa) r0.get(r3);
        ParcelFileDescriptor r5 = r4.zza();
        if (r5 == null) goto L7;
        r5.close();
    L7:
        ParcelFileDescriptor r42 = r4.zzb();
        if (r42 == null) goto L10;
        r42.close();
    L10:
        r3 = r3 + 1;
        goto L3
    L11:
        zzml r6 = this.zzg;
        if (r6 == null) goto L19;
        int r02 = r6.size();
    L14:
        if (r2 >= r02) goto L26;
        ParcelFileDescriptor r12 = (ParcelFileDescriptor) r6.get(r2);
        if (r12 == null) goto L18;
        r12.close();
    L18:
        r2 = r2 + 1;
        goto L14
    L26:
        return;
    }
}
