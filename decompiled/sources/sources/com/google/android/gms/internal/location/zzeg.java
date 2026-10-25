package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.location.LocationRequest;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zzeg implements Parcelable.Creator {
    public zzeg() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r15) {
        int r14 = SafeParcelReader.validateObjectHeader(r15);
        long r12 = Long.MAX_VALUE;
        LocationRequest r5 = null;
        ArrayList r6 = null;
        String r11 = null;
        boolean r7 = false;
        boolean r8 = false;
        boolean r9 = false;
        boolean r10 = false;
    L4:
        if (r15.dataPosition() >= r14) goto L23;
        int r0 = SafeParcelReader.readHeader(r15);
        int r1 = SafeParcelReader.getFieldId(r0);
        if (r1 != 1) goto L8;
        r5 = (LocationRequest) SafeParcelReader.createParcelable(r15, r0, LocationRequest.CREATOR);
        goto L4
    L8:
        if (r1 != 5) goto L10;
        r6 = SafeParcelReader.createTypedList(r15, r0, ClientIdentity.CREATOR);
        goto L4
    L10:
        if (r1 != 8) goto L12;
        r7 = SafeParcelReader.readBoolean(r15, r0);
        goto L4
    L12:
        if (r1 != 9) goto L13;
        r8 = SafeParcelReader.readBoolean(r15, r0);
        goto L4
    L13:
        switch(r1) {
            case 11: goto L18;
            case 12: goto L17;
            case 13: goto L16;
            case 14: goto L15;
            default: goto L14;
        };
    L15:
        r12 = SafeParcelReader.readLong(r15, r0);
        goto L4
    L16:
        r11 = SafeParcelReader.createString(r15, r0);
        goto L4
    L17:
        r10 = SafeParcelReader.readBoolean(r15, r0);
        goto L4
    L18:
        r9 = SafeParcelReader.readBoolean(r15, r0);
        goto L4
    L14:
        SafeParcelReader.skipUnknownField(r15, r0);
        goto L4
    L23:
        SafeParcelReader.ensureAtEnd(r15, r14);
        return new zzef(r5, r6, r7, r8, r9, r10, r11, r12);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzef[r1];
    }
}
