package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public final class zzc implements Parcelable.Creator {
    public zzc() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r19) {
        int r1 = SafeParcelReader.validateObjectHeader(r19);
        LatLng r8 = null;
        ArrayList r17 = null;
        int r12 = 0;
        int r13 = 0;
        boolean r15 = false;
        boolean r16 = false;
        float r11 = 0.0f;
        float r14 = 0.0f;
        double r9 = 0.0d;
    L4:
        if (r19.dataPosition() >= r1) goto L17;
        int r2 = SafeParcelReader.readHeader(r19);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 2: goto L16;
            case 3: goto L15;
            case 4: goto L14;
            case 5: goto L13;
            case 6: goto L12;
            case 7: goto L11;
            case 8: goto L10;
            case 9: goto L9;
            case 10: goto L8;
            default: goto L7;
        };
    L8:
        r17 = SafeParcelReader.createTypedList(r19, r2, PatternItem.CREATOR);
        goto L4
    L9:
        r16 = SafeParcelReader.readBoolean(r19, r2);
        goto L4
    L10:
        r15 = SafeParcelReader.readBoolean(r19, r2);
        goto L4
    L11:
        r14 = SafeParcelReader.readFloat(r19, r2);
        goto L4
    L12:
        r13 = SafeParcelReader.readInt(r19, r2);
        goto L4
    L13:
        r12 = SafeParcelReader.readInt(r19, r2);
        goto L4
    L14:
        r11 = SafeParcelReader.readFloat(r19, r2);
        goto L4
    L15:
        r9 = SafeParcelReader.readDouble(r19, r2);
        goto L4
    L16:
        r8 = (LatLng) SafeParcelReader.createParcelable(r19, r2, LatLng.CREATOR);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r19, r2);
        goto L4
    L17:
        SafeParcelReader.ensureAtEnd(r19, r1);
        return new CircleOptions(r8, r9, r11, r12, r13, r14, r15, r16, r17);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new CircleOptions[r1];
    }
}
