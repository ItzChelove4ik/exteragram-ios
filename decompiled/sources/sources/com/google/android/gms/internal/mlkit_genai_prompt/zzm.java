package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public abstract class zzm {
    public static final /* synthetic */ int $r8$clinit = 0;

    static {
        zzm.class.getClassLoader();
    }

    public static Parcelable zza(Parcel r1, Parcelable.Creator r2) {
        if (r1.readInt() != 0) goto L7;
        return null;
    L7:
        return (Parcelable) r2.createFromParcel(r1);
    }

    public static void zzb(Parcel r1, Parcelable r2) {
        r1.writeInt(1);
        r2.writeToParcel(r1, 0);
    }

    public static void zzc(Parcel r3) {
        int r32 = r3.dataAvail();
        if (r32 > 0) goto L5;
        return;
    L5:
        StringBuilder r2 = new StringBuilder(String.valueOf(r32).length() + 45);
        r2.append("Parcel data not fully consumed, unread size: ");
        r2.append(r32);
        throw new BadParcelableException(r2.toString());
    }
}
