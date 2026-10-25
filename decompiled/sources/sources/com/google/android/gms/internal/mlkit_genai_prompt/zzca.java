package com.google.android.gms.internal.mlkit_genai_prompt;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzca implements Parcelable.Creator {
    public zzca() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r12) {
        int r11 = SafeParcelReader.validateObjectHeader(r12);
        String r4 = null;
        Bitmap r5 = null;
        ParcelFileDescriptor r7 = null;
        zzbv r9 = null;
        zzbx r10 = null;
        int r3 = 0;
        int r6 = 0;
        boolean r8 = false;
    L4:
        if (r12.dataPosition() >= r11) goto L16;
        int r0 = SafeParcelReader.readHeader(r12);
        switch(SafeParcelReader.getFieldId(r0)) {
            case 1: goto L15;
            case 2: goto L14;
            case 3: goto L13;
            case 4: goto L12;
            case 5: goto L11;
            case 6: goto L10;
            case 7: goto L9;
            case 8: goto L8;
            default: goto L7;
        };
    L8:
        r10 = (zzbx) SafeParcelReader.createParcelable(r12, r0, zzbx.CREATOR);
        goto L4
    L9:
        r9 = (zzbv) SafeParcelReader.createParcelable(r12, r0, zzbv.CREATOR);
        goto L4
    L10:
        r8 = SafeParcelReader.readBoolean(r12, r0);
        goto L4
    L11:
        r7 = (ParcelFileDescriptor) SafeParcelReader.createParcelable(r12, r0, ParcelFileDescriptor.CREATOR);
        goto L4
    L12:
        r6 = SafeParcelReader.readInt(r12, r0);
        goto L4
    L13:
        r5 = (Bitmap) SafeParcelReader.createParcelable(r12, r0, Bitmap.CREATOR);
        goto L4
    L14:
        r4 = SafeParcelReader.createString(r12, r0);
        goto L4
    L15:
        r3 = SafeParcelReader.readInt(r12, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r12, r0);
        goto L4
    L16:
        SafeParcelReader.ensureAtEnd(r12, r11);
        return new zzbz(r3, r4, r5, r6, r7, r8, r9, r10);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzbz[r1];
    }
}
