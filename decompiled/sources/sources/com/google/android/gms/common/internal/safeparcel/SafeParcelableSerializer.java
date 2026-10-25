package com.google.android.gms.common.internal.safeparcel;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes4.dex */
public abstract class SafeParcelableSerializer {
    public static <T extends SafeParcelable> T deserializeFromBytes(byte[] r3, Parcelable.Creator<T> r4) {
        Preconditions.checkNotNull(r4);
        Parcel r0 = Parcel.obtain();
        r0.unmarshall(r3, 0, r3.length);
        r0.setDataPosition(0);
        T r32 = r4.createFromParcel(r0);
        r0.recycle();
        return r32;
    }

    public static <T extends SafeParcelable> T deserializeFromIntentExtra(Intent r0, String r1, Parcelable.Creator<T> r2) {
        byte[] r02 = r0.getByteArrayExtra(r1);
        if (r02 != null) goto L7;
        return null;
    L7:
        return (T) deserializeFromBytes(r02, r2);
    }

    public static <T extends SafeParcelable> byte[] serializeToBytes(T r2) {
        Parcel r0 = Parcel.obtain();
        r2.writeToParcel(r0, 0);
        byte[] r22 = r0.marshall();
        r0.recycle();
        return r22;
    }

    public static <T extends SafeParcelable> void serializeToIntentExtra(T r0, Intent r1, String r2) {
        r1.putExtra(r2, serializeToBytes(r0));
    }
}
