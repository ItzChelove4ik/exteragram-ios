package com.google.android.gms.flags;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public abstract class zzd extends com.google.android.gms.internal.flags.zzb implements zze {
    public zzd() {
        super("com.google.android.gms.flags.IFlagProvider");
    }

    public static zze asInterface(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r0 = r2.queryLocalInterface("com.google.android.gms.flags.IFlagProvider");
        if ((r0 instanceof zze) == false) goto L10;
        return (zze) r0;
    L10:
        return new zzc(r2);
    }

    @Override // com.google.android.gms.internal.flags.zzb
    public final boolean zza(int r3, Parcel r4, Parcel r5, int r6) {
        if (r3 != 1) goto L5;
        init(IObjectWrapper.Stub.asInterface(r4.readStrongBinder()));
        r5.writeNoException();
    L19:
        return true;
    L5:
        if (r3 != 2) goto L7;
        boolean r2 = getBooleanFlagValue(r4.readString(), com.google.android.gms.internal.flags.zzc.zzc(r4), r4.readInt());
        r5.writeNoException();
        com.google.android.gms.internal.flags.zzc.zza(r5, r2);
        goto L19
    L7:
        if (r3 != 3) goto L9;
        int r22 = getIntFlagValue(r4.readString(), r4.readInt(), r4.readInt());
        r5.writeNoException();
        r5.writeInt(r22);
        goto L19
    L9:
        if (r3 != 4) goto L11;
        long r23 = getLongFlagValue(r4.readString(), r4.readLong(), r4.readInt());
        r5.writeNoException();
        r5.writeLong(r23);
        goto L19
    L11:
        if (r3 == 5) goto L14;
        return false;
    L14:
        String r24 = getStringFlagValue(r4.readString(), r4.readString(), r4.readInt());
        r5.writeNoException();
        r5.writeString(r24);
        goto L19
    }
}
