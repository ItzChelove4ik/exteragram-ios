package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zzcj implements Parcelable.Creator {
    public zzcj() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r8) {
        int r7 = SafeParcelReader.validateObjectHeader(r8);
        ArrayList r0 = null;
        zzbt r1 = null;
        zzck r2 = null;
        ArrayList r3 = null;
    L4:
        if (r8.dataPosition() >= r7) goto L18;
        int r4 = SafeParcelReader.readHeader(r8);
        int r5 = SafeParcelReader.getFieldId(r4);
        if (r5 != 1) goto L8;
        r0 = SafeParcelReader.createTypedList(r8, r4, zzcd.CREATOR);
        goto L4
    L8:
        if (r5 != 2) goto L10;
        r1 = (zzbt) SafeParcelReader.createParcelable(r8, r4, zzbt.CREATOR);
        goto L4
    L10:
        if (r5 != 3) goto L12;
        r2 = (zzck) SafeParcelReader.createParcelable(r8, r4, zzck.CREATOR);
        goto L4
    L12:
        if (r5 != 4) goto L13;
        r3 = SafeParcelReader.createTypedList(r8, r4, zzcd.CREATOR);
        goto L4
    L13:
        SafeParcelReader.skipUnknownField(r8, r4);
        goto L4
    L18:
        SafeParcelReader.ensureAtEnd(r8, r7);
        return new zzci(r0, r1, r2, r3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzci[r1];
    }
}
