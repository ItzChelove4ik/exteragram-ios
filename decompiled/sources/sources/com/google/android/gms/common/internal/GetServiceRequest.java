package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* loaded from: classes4.dex */
public class GetServiceRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GetServiceRequest> CREATOR = null;
    static final Scope[] zza = null;
    static final Feature[] zzb = null;
    final int zzc;
    final int zzd;
    final int zze;
    String zzf;
    IBinder zzg;
    Scope[] zzh;
    Bundle zzi;
    Account zzj;
    Feature[] zzk;
    Feature[] zzl;
    final boolean zzm;
    final int zzn;
    boolean zzo;
    private final String zzp;

    static {
        CREATOR = new zzm();
        zza = new Scope[0];
        zzb = new Feature[0];
    }

    public GetServiceRequest(int r1, int r2, int r3, String r4, IBinder r5, Scope[] r6, Bundle r7, Account r8, Feature[] r9, Feature[] r10, boolean r11, int r12, boolean r13, String r14) {
        if (r6 != null) goto L5;
        r6 = zza;
    L5:
        if (r7 != null) goto L7;
        r7 = new Bundle();
    L7:
        if (r9 != null) goto L9;
        r9 = zzb;
    L9:
        if (r10 != null) goto L11;
        r10 = zzb;
    L11:
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        if ("com.google.android.gms".equals(r4) == false) goto L14;
        this.zzf = "com.google.android.gms";
    L16:
        if (r1 >= 2) goto L21;
        if (r5 == null) goto L19;
        Account r15 = AccountAccessor.getAccountBinderSafe(IAccountAccessor.Stub.asInterface(r5));
    L20:
        this.zzj = r15;
    L22:
        this.zzh = r6;
        this.zzi = r7;
        this.zzk = r9;
        this.zzl = r10;
        this.zzm = r11;
        this.zzn = r12;
        this.zzo = r13;
        this.zzp = r14;
        return;
    L19:
        r15 = null;
        goto L20
    L21:
        this.zzg = r5;
        this.zzj = r8;
        goto L22
    L14:
        this.zzf = r4;
        goto L16
    }

    public String getAttributionTag() {
        return this.zzp;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r1, int r2) {
        zzm.zza(this, r1, r2);
    }
}
