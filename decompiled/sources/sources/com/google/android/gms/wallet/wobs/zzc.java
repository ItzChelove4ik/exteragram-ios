package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public final class zzc implements Parcelable.Creator {
    public zzc() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r31) {
        int r1 = SafeParcelReader.validateObjectHeader(r31);
        ArrayList r2 = ArrayUtils.newArrayList();
        ArrayList r3 = ArrayUtils.newArrayList();
        ArrayList r4 = ArrayUtils.newArrayList();
        ArrayList r5 = ArrayUtils.newArrayList();
        ArrayList r20 = r2;
        ArrayList r22 = r3;
        ArrayList r25 = r4;
        ArrayList r27 = r5;
        ArrayList r28 = ArrayUtils.newArrayList();
        ArrayList r29 = ArrayUtils.newArrayList();
        int r19 = 0;
        boolean r26 = false;
        String r11 = null;
        String r12 = null;
        String r13 = null;
        String r14 = null;
        String r15 = null;
        String r16 = null;
        String r17 = null;
        String r18 = null;
        TimeInterval r21 = null;
        String r23 = null;
        String r24 = null;
    L4:
        if (r31.dataPosition() >= r1) goto L27;
        int r210 = SafeParcelReader.readHeader(r31);
        switch(SafeParcelReader.getFieldId(r210)) {
            case 2: goto L26;
            case 3: goto L25;
            case 4: goto L24;
            case 5: goto L23;
            case 6: goto L22;
            case 7: goto L21;
            case 8: goto L20;
            case 9: goto L19;
            case 10: goto L18;
            case 11: goto L17;
            case 12: goto L16;
            case 13: goto L15;
            case 14: goto L14;
            case 15: goto L13;
            case 16: goto L12;
            case 17: goto L11;
            case 18: goto L10;
            case 19: goto L9;
            case 20: goto L8;
            default: goto L7;
        };
    L8:
        r29 = SafeParcelReader.createTypedList(r31, r210, UriData.CREATOR);
        goto L4
    L9:
        r28 = SafeParcelReader.createTypedList(r31, r210, TextModuleData.CREATOR);
        goto L4
    L10:
        r27 = SafeParcelReader.createTypedList(r31, r210, UriData.CREATOR);
        goto L4
    L11:
        r26 = SafeParcelReader.readBoolean(r31, r210);
        goto L4
    L12:
        r25 = SafeParcelReader.createTypedList(r31, r210, LabelValueRow.CREATOR);
        goto L4
    L13:
        r24 = SafeParcelReader.createString(r31, r210);
        goto L4
    L14:
        r23 = SafeParcelReader.createString(r31, r210);
        goto L4
    L15:
        r22 = SafeParcelReader.createTypedList(r31, r210, LatLng.CREATOR);
        goto L4
    L16:
        r21 = (TimeInterval) SafeParcelReader.createParcelable(r31, r210, TimeInterval.CREATOR);
        goto L4
    L17:
        r20 = SafeParcelReader.createTypedList(r31, r210, WalletObjectMessage.CREATOR);
        goto L4
    L18:
        r19 = SafeParcelReader.readInt(r31, r210);
        goto L4
    L19:
        r18 = SafeParcelReader.createString(r31, r210);
        goto L4
    L20:
        r17 = SafeParcelReader.createString(r31, r210);
        goto L4
    L21:
        r16 = SafeParcelReader.createString(r31, r210);
        goto L4
    L22:
        r15 = SafeParcelReader.createString(r31, r210);
        goto L4
    L23:
        r14 = SafeParcelReader.createString(r31, r210);
        goto L4
    L24:
        r13 = SafeParcelReader.createString(r31, r210);
        goto L4
    L25:
        r12 = SafeParcelReader.createString(r31, r210);
        goto L4
    L26:
        r11 = SafeParcelReader.createString(r31, r210);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r31, r210);
        goto L4
    L27:
        SafeParcelReader.ensureAtEnd(r31, r1);
        return new CommonWalletObject(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new CommonWalletObject[r1];
    }
}
