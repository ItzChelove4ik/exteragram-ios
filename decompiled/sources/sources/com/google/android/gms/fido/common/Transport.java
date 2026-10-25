package com.google.android.gms.fido.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.fido.zzhu;
import com.google.android.gms.internal.fido.zzhx;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.json.JSONArray;

/* loaded from: classes4.dex */
public enum Transport extends Enum<Transport> implements ReflectedParcelable {
    public static final Transport BLUETOOTH_CLASSIC = null;
    public static final Transport BLUETOOTH_LOW_ENERGY = null;
    public static final Parcelable.Creator<Transport> CREATOR = null;
    public static final Transport HYBRID = null;
    public static final Transport INTERNAL = null;
    public static final Transport NFC = null;
    public static final Transport USB = null;
    public static final Transport zza = null;
    private static final /* synthetic */ Transport[] zzb = null;
    private final String zzc;

    public static class UnsupportedTransportException extends Exception {
        public UnsupportedTransportException(String r1) {
            super(r1);
        }
    }

    static {
        Transport r0 = new Transport("BLUETOOTH_CLASSIC", 0, "bt");
        BLUETOOTH_CLASSIC = r0;
        Transport r1 = new Transport("BLUETOOTH_LOW_ENERGY", 1, "ble");
        BLUETOOTH_LOW_ENERGY = r1;
        Transport r2 = new Transport("NFC", 2, "nfc");
        NFC = r2;
        Transport r3 = new Transport("USB", 3, "usb");
        USB = r3;
        Transport r4 = new Transport("INTERNAL", 4, "internal");
        INTERNAL = r4;
        Transport r5 = new Transport("HYBRID", 5, "cable");
        HYBRID = r5;
        Transport r6 = new Transport("HYBRID_V2", 6, "hybrid");
        zza = r6;
        zzb = new Transport[]{r0, r1, r2, r3, r4, r5, r6};
        CREATOR = new zza();
    }

    Transport(String r1, int r2, String r3) {
        this.zzc = r3;
    }

    public static Transport fromString(String r5) {
        if (r5.equals("hybrid") == true) goto L5;
    L11:
        Transport[] r0 = values();
        int r1 = r0.length;
        int r2 = 0;
    L12:
        if (r2 >= r1) goto L18;
        Transport r3 = r0[r2];
        if (r5.equals(r3.zzc) == true) goto L15;
        r2 = r2 + 1;
        goto L12
    L15:
        return r3;
    L18:
        throw new UnsupportedTransportException(String.format("Transport %s not supported", new Object[]{r5}));
    L5:
        if (zzhu.zzb() == false) goto L10;
        if (zzhx.zzb() == true) goto L11;
    L10:
        return HYBRID;
    }

    public static List<Transport> parseTransports(JSONArray r4) {
        if (r4 != null) goto L5;
        return null;
    L5:
        HashSet r0 = new HashSet(r4.length());
        int r1 = 0;
    L7:
        if (r1 >= r4.length()) goto L17;
        String r2 = r4.getString(r1);
        if (r2 == null) goto L15;
        if (r2.isEmpty() == true) goto L15;
        r0.add(fromString(r2));     // Catch: UnsupportedTransportException -> L14
    L14:
        Log.w("Transport", "Ignoring unrecognized transport ".concat(r2));
    L15:
        r1 = r1 + 1;
        goto L7
    L17:
        return new ArrayList(r0);
    }

    public static Transport valueOf(String r1) {
        return (Transport) Enum.valueOf(Transport.class, r1);
    }

    public static Transport[] values() {
        return (Transport[]) zzb.clone();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.zzc;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        r1.writeString(this.zzc);
    }
}
