package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes4.dex */
public final class zzal extends zzk implements zzan {
    public zzal(IBinder r2) {
        super(r2, "com.google.android.apps.aicore.aidl.IAICoreService");
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzan
    public final zzn zzd(int r1, int r2) {
        Parcel r22 = zza();
        r22.writeInt(r1);
        r22.writeInt(-1);
        Parcel r0 = zzb(32, r22);
        zzn r12 = (zzn) zzm.zza(r0, zzn.CREATOR);
        r0.recycle();
        return r12;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzan
    public final int zze(zzn r2) {
        Parcel r0 = zza();
        zzm.zzb(r0, r2);
        Parcel r1 = zzb(3, r0);
        int r22 = r1.readInt();
        r1.recycle();
        return r22;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzan
    public final int zzf(zzn r2, zzbe r3) {
        Parcel r0 = zza();
        zzm.zzb(r0, r2);
        r0.writeStrongBinder(r3);
        Parcel r1 = zzb(12, r0);
        int r22 = r1.readInt();
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzan
    public final int zzg() {
        Parcel r2 = zzb(11, zza());
        int r0 = r2.readInt();
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzan
    public final int zzh(zzn r2) {
        Parcel r0 = zza();
        zzm.zzb(r0, r2);
        Parcel r1 = zzb(39, r0);
        int r22 = r1.readInt();
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzan
    public final zzbi zzi(zzn r3) {
        Parcel r0 = zza();
        zzm.zzb(r0, r3);
        Parcel r2 = zzb(5, r0);
        IBinder r32 = r2.readStrongBinder();
        if (r32 != null) goto L5;
        zzbi r33 = null;
    L9:
        r2.recycle();
        return r33;
    L5:
        IInterface r02 = r32.queryLocalInterface("com.google.android.apps.aicore.aidl.ILLMService");
        if ((r02 instanceof zzbi) == false) goto L8;
        r33 = (zzbi) r02;
        goto L9
    L8:
        r33 = new zzbi(r32);
        goto L9
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzan
    public final zzay zzj(zzn r3) {
        Parcel r0 = zza();
        zzm.zzb(r0, r3);
        Parcel r2 = zzb(35, r0);
        IBinder r32 = r2.readStrongBinder();
        if (r32 != null) goto L5;
        zzay r33 = null;
    L9:
        r2.recycle();
        return r33;
    L5:
        IInterface r02 = r32.queryLocalInterface("com.google.android.apps.aicore.aidl.ICortanaService");
        if ((r02 instanceof zzay) == false) goto L8;
        r33 = (zzay) r02;
        goto L9
    L8:
        r33 = new zzay(r32);
        goto L9
    }
}
