package com.google.android.gms.common.moduleinstall.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zac implements Parcelable.Creator {
    public zac() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r8) {
        int r7 = SafeParcelReader.validateObjectHeader(r8);
        ArrayList r0 = null;
        String r2 = null;
        boolean r3 = false;
        String r1 = null;
    L4:
        if (r8.dataPosition() >= r7) goto L18;
        int r4 = SafeParcelReader.readHeader(r8);
        int r5 = SafeParcelReader.getFieldId(r4);
        if (r5 != 1) goto L8;
        r0 = SafeParcelReader.createTypedList(r8, r4, Feature.CREATOR);
        goto L4
    L8:
        if (r5 != 2) goto L10;
        r3 = SafeParcelReader.readBoolean(r8, r4);
        goto L4
    L10:
        if (r5 != 3) goto L12;
        r1 = SafeParcelReader.createString(r8, r4);
        goto L4
    L12:
        if (r5 != 4) goto L13;
        r2 = SafeParcelReader.createString(r8, r4);
        goto L4
    L13:
        SafeParcelReader.skipUnknownField(r8, r4);
        goto L4
    L18:
        SafeParcelReader.ensureAtEnd(r8, r7);
        return new ApiFeatureRequest(r0, r3, r1, r2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new ApiFeatureRequest[r1];
    }
}
