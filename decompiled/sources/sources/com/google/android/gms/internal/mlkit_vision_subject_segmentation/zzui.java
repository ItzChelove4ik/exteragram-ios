package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zzui implements Parcelable.Creator {
    public zzui() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r8) {
        int r7 = SafeParcelReader.validateObjectHeader(r8);
        ArrayList r0 = null;
        float[] r1 = null;
        Bitmap r2 = null;
        ArrayList<Float> r3 = null;
    L4:
        if (r8.dataPosition() >= r7) goto L18;
        int r4 = SafeParcelReader.readHeader(r8);
        int r5 = SafeParcelReader.getFieldId(r4);
        if (r5 != 1) goto L8;
        r0 = SafeParcelReader.createTypedList(r8, r4, zzuf.CREATOR);
        goto L4
    L8:
        if (r5 != 2) goto L10;
        r1 = SafeParcelReader.createFloatArray(r8, r4);
        goto L4
    L10:
        if (r5 != 3) goto L12;
        r2 = (Bitmap) SafeParcelReader.createParcelable(r8, r4, Bitmap.CREATOR);
        goto L4
    L12:
        if (r5 != 4) goto L13;
        r3 = SafeParcelReader.createFloatList(r8, r4);
        goto L4
    L13:
        SafeParcelReader.skipUnknownField(r8, r4);
        goto L4
    L18:
        SafeParcelReader.ensureAtEnd(r8, r7);
        return new zzuh(r0, r1, r2, r3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzuh[r1];
    }
}
