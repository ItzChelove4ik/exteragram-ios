package com.google.android.gms.identity.intents.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzb implements Parcelable.Creator<UserAddress> {
    public zzb() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ UserAddress createFromParcel(Parcel r21) {
        int r1 = SafeParcelReader.validateObjectHeader(r21);
        String r5 = null;
        String r6 = null;
        String r7 = null;
        String r8 = null;
        String r9 = null;
        String r10 = null;
        String r11 = null;
        String r12 = null;
        String r13 = null;
        String r14 = null;
        String r15 = null;
        String r16 = null;
        String r18 = null;
        String r19 = null;
        boolean r17 = false;
    L4:
        if (r21.dataPosition() >= r1) goto L23;
        int r2 = SafeParcelReader.readHeader(r21);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 2: goto L22;
            case 3: goto L21;
            case 4: goto L20;
            case 5: goto L19;
            case 6: goto L18;
            case 7: goto L17;
            case 8: goto L16;
            case 9: goto L15;
            case 10: goto L14;
            case 11: goto L13;
            case 12: goto L12;
            case 13: goto L11;
            case 14: goto L10;
            case 15: goto L9;
            case 16: goto L8;
            default: goto L7;
        };
    L8:
        r19 = SafeParcelReader.createString(r21, r2);
        goto L4
    L9:
        r18 = SafeParcelReader.createString(r21, r2);
        goto L4
    L10:
        r17 = SafeParcelReader.readBoolean(r21, r2);
        goto L4
    L11:
        r16 = SafeParcelReader.createString(r21, r2);
        goto L4
    L12:
        r15 = SafeParcelReader.createString(r21, r2);
        goto L4
    L13:
        r14 = SafeParcelReader.createString(r21, r2);
        goto L4
    L14:
        r13 = SafeParcelReader.createString(r21, r2);
        goto L4
    L15:
        r12 = SafeParcelReader.createString(r21, r2);
        goto L4
    L16:
        r11 = SafeParcelReader.createString(r21, r2);
        goto L4
    L17:
        r10 = SafeParcelReader.createString(r21, r2);
        goto L4
    L18:
        r9 = SafeParcelReader.createString(r21, r2);
        goto L4
    L19:
        r8 = SafeParcelReader.createString(r21, r2);
        goto L4
    L20:
        r7 = SafeParcelReader.createString(r21, r2);
        goto L4
    L21:
        r6 = SafeParcelReader.createString(r21, r2);
        goto L4
    L22:
        r5 = SafeParcelReader.createString(r21, r2);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r21, r2);
        goto L4
    L23:
        SafeParcelReader.ensureAtEnd(r21, r1);
        return new UserAddress(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ UserAddress[] newArray(int r1) {
        return new UserAddress[r1];
    }
}
