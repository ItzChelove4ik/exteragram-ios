package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzad implements Parcelable.Creator {
    public zzad() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r8) {
        int r7 = SafeParcelReader.validateObjectHeader(r8);
        float r0 = 0.0f;
        String r3 = null;
        int r2 = 0;
        float r1 = 0.0f;
    L4:
        if (r8.dataPosition() >= r7) goto L18;
        int r4 = SafeParcelReader.readHeader(r8);
        int r5 = SafeParcelReader.getFieldId(r4);
        if (r5 != 1) goto L8;
        r3 = SafeParcelReader.createString(r8, r4);
        goto L4
    L8:
        if (r5 != 2) goto L10;
        r0 = SafeParcelReader.readFloat(r8, r4);
        goto L4
    L10:
        if (r5 != 3) goto L12;
        r2 = SafeParcelReader.readInt(r8, r4);
        goto L4
    L12:
        if (r5 != 4) goto L13;
        r1 = SafeParcelReader.readFloat(r8, r4);
        goto L4
    L13:
        SafeParcelReader.skipUnknownField(r8, r4);
        goto L4
    L18:
        SafeParcelReader.ensureAtEnd(r8, r7);
        return new zzac(r3, r0, r2, r1);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzac[r1];
    }
}
