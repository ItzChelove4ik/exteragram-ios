package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzcn implements Parcelable.Creator {
    public zzcn() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r10) {
        int r9 = SafeParcelReader.validateObjectHeader(r10);
        float r6 = 0.0f;
        float r7 = 0.0f;
        float r8 = 0.0f;
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
    L4:
        if (r10.dataPosition() >= r9) goto L14;
        int r0 = SafeParcelReader.readHeader(r10);
        switch(SafeParcelReader.getFieldId(r0)) {
            case 1: goto L13;
            case 2: goto L12;
            case 3: goto L11;
            case 4: goto L10;
            case 5: goto L9;
            case 6: goto L8;
            default: goto L7;
        };
    L8:
        r8 = SafeParcelReader.readFloat(r10, r0);
        goto L4
    L9:
        r7 = SafeParcelReader.readFloat(r10, r0);
        goto L4
    L10:
        r6 = SafeParcelReader.readFloat(r10, r0);
        goto L4
    L11:
        r5 = SafeParcelReader.readInt(r10, r0);
        goto L4
    L12:
        r4 = SafeParcelReader.readInt(r10, r0);
        goto L4
    L13:
        r3 = SafeParcelReader.readInt(r10, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r10, r0);
        goto L4
    L14:
        SafeParcelReader.ensureAtEnd(r10, r9);
        return new zzcm(r3, r4, r5, r6, r7, r8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzcm[r1];
    }
}
