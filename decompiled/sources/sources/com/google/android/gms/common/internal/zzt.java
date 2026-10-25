package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes4.dex */
public final class zzt extends com.google.android.gms.internal.common.zza implements IAccountAccessor {
    public zzt(IBinder r2) {
        super(r2, "com.google.android.gms.common.internal.IAccountAccessor");
    }

    @Override // com.google.android.gms.common.internal.IAccountAccessor
    public final Account zzb() {
        Parcel r2 = zzB(2, zza());
        Account r0 = (Account) com.google.android.gms.internal.common.zzc.zzb(r2, Account.CREATOR);
        r2.recycle();
        return r0;
    }
}
