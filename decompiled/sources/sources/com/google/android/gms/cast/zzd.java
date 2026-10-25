package com.google.android.gms.cast;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zzd implements Parcelable.Creator {
    public zzd() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r15) {
        int r14 = SafeParcelReader.validateObjectHeader(r15);
        int r13 = 0;
        String r3 = null;
        String r4 = null;
        ArrayList r5 = null;
        ArrayList<String> r6 = null;
        String r7 = null;
        Uri r8 = null;
        String r9 = null;
        String r10 = null;
        Boolean r11 = null;
        Boolean r12 = null;
    L4:
        if (r15.dataPosition() >= r14) goto L19;
        int r0 = SafeParcelReader.readHeader(r15);
        switch(SafeParcelReader.getFieldId(r0)) {
            case 2: goto L18;
            case 3: goto L17;
            case 4: goto L16;
            case 5: goto L15;
            case 6: goto L14;
            case 7: goto L13;
            case 8: goto L12;
            case 9: goto L11;
            case 10: goto L10;
            case 11: goto L9;
            case 12: goto L8;
            default: goto L7;
        };
    L8:
        r13 = SafeParcelReader.readInt(r15, r0);
        goto L4
    L9:
        r12 = SafeParcelReader.readBooleanObject(r15, r0);
        goto L4
    L10:
        r11 = SafeParcelReader.readBooleanObject(r15, r0);
        goto L4
    L11:
        r10 = SafeParcelReader.createString(r15, r0);
        goto L4
    L12:
        r9 = SafeParcelReader.createString(r15, r0);
        goto L4
    L13:
        r8 = (Uri) SafeParcelReader.createParcelable(r15, r0, Uri.CREATOR);
        goto L4
    L14:
        r7 = SafeParcelReader.createString(r15, r0);
        goto L4
    L15:
        r6 = SafeParcelReader.createStringList(r15, r0);
        goto L4
    L16:
        r5 = SafeParcelReader.createTypedList(r15, r0, WebImage.CREATOR);
        goto L4
    L17:
        r4 = SafeParcelReader.createString(r15, r0);
        goto L4
    L18:
        r3 = SafeParcelReader.createString(r15, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r15, r0);
        goto L4
    L19:
        SafeParcelReader.ensureAtEnd(r15, r14);
        return new ApplicationMetadata(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new ApplicationMetadata[r1];
    }
}
