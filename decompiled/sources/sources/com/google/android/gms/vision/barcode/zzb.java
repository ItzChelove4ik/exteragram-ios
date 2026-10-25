package com.google.android.gms.vision.barcode;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.vision.barcode.Barcode;

/* loaded from: classes5.dex */
public final class zzb implements Parcelable.Creator<Barcode> {
    public zzb() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Barcode createFromParcel(Parcel r22) {
        int r1 = SafeParcelReader.validateObjectHeader(r22);
        int r5 = 0;
        int r8 = 0;
        boolean r20 = false;
        String r6 = null;
        String r7 = null;
        Point[] r9 = null;
        Barcode.Email r10 = null;
        Barcode.Phone r11 = null;
        Barcode.Sms r12 = null;
        Barcode.WiFi r13 = null;
        Barcode.UrlBookmark r14 = null;
        Barcode.GeoPoint r15 = null;
        Barcode.CalendarEvent r16 = null;
        Barcode.ContactInfo r17 = null;
        Barcode.DriverLicense r18 = null;
        byte[] r19 = null;
    L4:
        if (r22.dataPosition() >= r1) goto L24;
        int r2 = SafeParcelReader.readHeader(r22);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 2: goto L23;
            case 3: goto L22;
            case 4: goto L21;
            case 5: goto L20;
            case 6: goto L19;
            case 7: goto L18;
            case 8: goto L17;
            case 9: goto L16;
            case 10: goto L15;
            case 11: goto L14;
            case 12: goto L13;
            case 13: goto L12;
            case 14: goto L11;
            case 15: goto L10;
            case 16: goto L9;
            case 17: goto L8;
            default: goto L7;
        };
    L8:
        r20 = SafeParcelReader.readBoolean(r22, r2);
        goto L4
    L9:
        r19 = SafeParcelReader.createByteArray(r22, r2);
        goto L4
    L10:
        r18 = (Barcode.DriverLicense) SafeParcelReader.createParcelable(r22, r2, Barcode.DriverLicense.CREATOR);
        goto L4
    L11:
        r17 = (Barcode.ContactInfo) SafeParcelReader.createParcelable(r22, r2, Barcode.ContactInfo.CREATOR);
        goto L4
    L12:
        r16 = (Barcode.CalendarEvent) SafeParcelReader.createParcelable(r22, r2, Barcode.CalendarEvent.CREATOR);
        goto L4
    L13:
        r15 = (Barcode.GeoPoint) SafeParcelReader.createParcelable(r22, r2, Barcode.GeoPoint.CREATOR);
        goto L4
    L14:
        r14 = (Barcode.UrlBookmark) SafeParcelReader.createParcelable(r22, r2, Barcode.UrlBookmark.CREATOR);
        goto L4
    L15:
        r13 = (Barcode.WiFi) SafeParcelReader.createParcelable(r22, r2, Barcode.WiFi.CREATOR);
        goto L4
    L16:
        r12 = (Barcode.Sms) SafeParcelReader.createParcelable(r22, r2, Barcode.Sms.CREATOR);
        goto L4
    L17:
        r11 = (Barcode.Phone) SafeParcelReader.createParcelable(r22, r2, Barcode.Phone.CREATOR);
        goto L4
    L18:
        r10 = (Barcode.Email) SafeParcelReader.createParcelable(r22, r2, Barcode.Email.CREATOR);
        goto L4
    L19:
        r9 = (Point[]) SafeParcelReader.createTypedArray(r22, r2, Point.CREATOR);
        goto L4
    L20:
        r8 = SafeParcelReader.readInt(r22, r2);
        goto L4
    L21:
        r7 = SafeParcelReader.createString(r22, r2);
        goto L4
    L22:
        r6 = SafeParcelReader.createString(r22, r2);
        goto L4
    L23:
        r5 = SafeParcelReader.readInt(r22, r2);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r22, r2);
        goto L4
    L24:
        SafeParcelReader.ensureAtEnd(r22, r1);
        return new Barcode(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Barcode[] newArray(int r1) {
        return new Barcode[r1];
    }
}
