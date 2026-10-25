package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zza implements Parcelable.Creator {
    public zza() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r10) {
        int r9 = SafeParcelReader.validateObjectHeader(r10);
        boolean r7 = false;
        boolean r8 = false;
        String r3 = null;
        String r4 = null;
        IBinder r5 = null;
        NotificationOptions r6 = null;
    L4:
        if (r10.dataPosition() >= r9) goto L14;
        int r0 = SafeParcelReader.readHeader(r10);
        switch(SafeParcelReader.getFieldId(r0)) {
            case 2: goto L13;
            case 3: goto L12;
            case 4: goto L11;
            case 5: goto L10;
            case 6: goto L9;
            case 7: goto L8;
            default: goto L7;
        };
    L8:
        r8 = SafeParcelReader.readBoolean(r10, r0);
        goto L4
    L9:
        r7 = SafeParcelReader.readBoolean(r10, r0);
        goto L4
    L10:
        r6 = (NotificationOptions) SafeParcelReader.createParcelable(r10, r0, NotificationOptions.CREATOR);
        goto L4
    L11:
        r5 = SafeParcelReader.readIBinder(r10, r0);
        goto L4
    L12:
        r4 = SafeParcelReader.createString(r10, r0);
        goto L4
    L13:
        r3 = SafeParcelReader.createString(r10, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r10, r0);
        goto L4
    L14:
        SafeParcelReader.ensureAtEnd(r10, r9);
        return new CastMediaOptions(r3, r4, r5, r6, r7, r8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new CastMediaOptions[r1];
    }
}
