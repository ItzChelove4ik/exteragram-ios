package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zzcc implements Parcelable.Creator {
    public zzcc() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r6) {
        int r5 = SafeParcelReader.validateObjectHeader(r6);
        ArrayList r0 = null;
        ParcelFileDescriptor r1 = null;
    L4:
        if (r6.dataPosition() >= r5) goto L12;
        int r2 = SafeParcelReader.readHeader(r6);
        int r3 = SafeParcelReader.getFieldId(r2);
        if (r3 != 1) goto L8;
        r0 = SafeParcelReader.createTypedList(r6, r2, zzbz.CREATOR);
        goto L4
    L8:
        if (r3 != 2) goto L9;
        r1 = (ParcelFileDescriptor) SafeParcelReader.createParcelable(r6, r2, ParcelFileDescriptor.CREATOR);
        goto L4
    L9:
        SafeParcelReader.skipUnknownField(r6, r2);
        goto L4
    L12:
        SafeParcelReader.ensureAtEnd(r6, r5);
        return new zzcb(r0, r1);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzcb[r1];
    }
}
