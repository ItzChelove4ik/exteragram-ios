package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.LabelValueRow;
import com.google.android.gms.wallet.wobs.LoyaltyPoints;
import com.google.android.gms.wallet.wobs.TextModuleData;
import com.google.android.gms.wallet.wobs.TimeInterval;
import com.google.android.gms.wallet.wobs.UriData;
import com.google.android.gms.wallet.wobs.WalletObjectMessage;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public final class zzs implements Parcelable.Creator {
    public zzs() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r34) {
        int r1 = SafeParcelReader.validateObjectHeader(r34);
        ArrayList r2 = ArrayUtils.newArrayList();
        ArrayList r3 = ArrayUtils.newArrayList();
        ArrayList r4 = ArrayUtils.newArrayList();
        ArrayList r5 = ArrayUtils.newArrayList();
        ArrayList r22 = r2;
        ArrayList r24 = r3;
        ArrayList r27 = r4;
        ArrayList r29 = r5;
        ArrayList r30 = ArrayUtils.newArrayList();
        ArrayList r31 = ArrayUtils.newArrayList();
        String r11 = null;
        String r12 = null;
        String r13 = null;
        String r14 = null;
        String r15 = null;
        String r16 = null;
        String r17 = null;
        String r18 = null;
        String r19 = null;
        String r20 = null;
        TimeInterval r23 = null;
        String r25 = null;
        String r26 = null;
        LoyaltyPoints r32 = null;
        int r21 = 0;
        boolean r28 = false;
    L4:
        if (r34.dataPosition() >= r1) goto L30;
        int r210 = SafeParcelReader.readHeader(r34);
        switch(SafeParcelReader.getFieldId(r210)) {
            case 2: goto L29;
            case 3: goto L28;
            case 4: goto L27;
            case 5: goto L26;
            case 6: goto L25;
            case 7: goto L24;
            case 8: goto L23;
            case 9: goto L22;
            case 10: goto L21;
            case 11: goto L20;
            case 12: goto L19;
            case 13: goto L18;
            case 14: goto L17;
            case 15: goto L16;
            case 16: goto L15;
            case 17: goto L14;
            case 18: goto L13;
            case 19: goto L12;
            case 20: goto L11;
            case 21: goto L10;
            case 22: goto L9;
            case 23: goto L8;
            default: goto L7;
        };
    L8:
        r32 = (LoyaltyPoints) SafeParcelReader.createParcelable(r34, r210, LoyaltyPoints.CREATOR);
        goto L4
    L9:
        r31 = SafeParcelReader.createTypedList(r34, r210, UriData.CREATOR);
        goto L4
    L10:
        r30 = SafeParcelReader.createTypedList(r34, r210, TextModuleData.CREATOR);
        goto L4
    L11:
        r29 = SafeParcelReader.createTypedList(r34, r210, UriData.CREATOR);
        goto L4
    L12:
        r28 = SafeParcelReader.readBoolean(r34, r210);
        goto L4
    L13:
        r27 = SafeParcelReader.createTypedList(r34, r210, LabelValueRow.CREATOR);
        goto L4
    L14:
        r26 = SafeParcelReader.createString(r34, r210);
        goto L4
    L15:
        r25 = SafeParcelReader.createString(r34, r210);
        goto L4
    L16:
        r24 = SafeParcelReader.createTypedList(r34, r210, LatLng.CREATOR);
        goto L4
    L17:
        r23 = (TimeInterval) SafeParcelReader.createParcelable(r34, r210, TimeInterval.CREATOR);
        goto L4
    L18:
        r22 = SafeParcelReader.createTypedList(r34, r210, WalletObjectMessage.CREATOR);
        goto L4
    L19:
        r21 = SafeParcelReader.readInt(r34, r210);
        goto L4
    L20:
        r20 = SafeParcelReader.createString(r34, r210);
        goto L4
    L21:
        r19 = SafeParcelReader.createString(r34, r210);
        goto L4
    L22:
        r18 = SafeParcelReader.createString(r34, r210);
        goto L4
    L23:
        r17 = SafeParcelReader.createString(r34, r210);
        goto L4
    L24:
        r16 = SafeParcelReader.createString(r34, r210);
        goto L4
    L25:
        r15 = SafeParcelReader.createString(r34, r210);
        goto L4
    L26:
        r14 = SafeParcelReader.createString(r34, r210);
        goto L4
    L27:
        r13 = SafeParcelReader.createString(r34, r210);
        goto L4
    L28:
        r12 = SafeParcelReader.createString(r34, r210);
        goto L4
    L29:
        r11 = SafeParcelReader.createString(r34, r210);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r34, r210);
        goto L4
    L30:
        SafeParcelReader.ensureAtEnd(r34, r1);
        return new LoyaltyWalletObject(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new LoyaltyWalletObject[r1];
    }
}
