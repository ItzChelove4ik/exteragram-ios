package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zzbw implements Parcelable.Creator {
    public zzbw() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r12) {
        int r11 = SafeParcelReader.validateObjectHeader(r12);
        double r9 = 0.0d;
        String r6 = null;
        ArrayList r7 = null;
        ArrayList r8 = null;
        int r5 = 0;
    L4:
        if (r12.dataPosition() >= r11) goto L21;
        int r0 = SafeParcelReader.readHeader(r12);
        int r1 = SafeParcelReader.getFieldId(r0);
        if (r1 != 2) goto L8;
        r5 = SafeParcelReader.readInt(r12, r0);
        goto L4
    L8:
        if (r1 != 3) goto L10;
        r6 = SafeParcelReader.createString(r12, r0);
        goto L4
    L10:
        if (r1 != 4) goto L12;
        r7 = SafeParcelReader.createTypedList(r12, r0, MediaMetadata.CREATOR);
        goto L4
    L12:
        if (r1 != 5) goto L14;
        r8 = SafeParcelReader.createTypedList(r12, r0, WebImage.CREATOR);
        goto L4
    L14:
        if (r1 != 6) goto L15;
        r9 = SafeParcelReader.readDouble(r12, r0);
        goto L4
    L15:
        SafeParcelReader.skipUnknownField(r12, r0);
        goto L4
    L21:
        SafeParcelReader.ensureAtEnd(r12, r11);
        return new MediaQueueContainerMetadata(r5, r6, r7, r8, r9);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new MediaQueueContainerMetadata[r1];
    }
}
