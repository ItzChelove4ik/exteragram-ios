package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
import org.mvel2.asm.Opcodes;

/* loaded from: classes.dex */
public abstract class SafeParcelWriter {
    public static int beginObjectHeader(Parcel r1) {
        return zzb(r1, 20293);
    }

    public static void finishObjectHeader(Parcel r0, int r1) {
        zzc(r0, r1);
    }

    public static void writeBoolean(Parcel r1, int r2, boolean r3) {
        zza(r1, r2, 4);
        r1.writeInt(r3 ? 1 : 0);
    }

    public static void writeBooleanObject(Parcel r0, int r1, Boolean r2, boolean r3) {
        if (r2 != null) goto L6;
        if (r3 == false) goto L8;
        zza(r0, r1, 0);
        return;
    L8:
        return;
    L6:
        zza(r0, r1, 4);
        r0.writeInt(r2.booleanValue() ? 1 : 0);
    }

    public static void writeBundle(Parcel r0, int r1, Bundle r2, boolean r3) {
        if (r2 != null) goto L6;
        if (r3 == false) goto L8;
        zza(r0, r1, 0);
        return;
    L8:
        return;
    L6:
        int r12 = zzb(r0, r1);
        r0.writeBundle(r2);
        zzc(r0, r12);
    }

    public static void writeByte(Parcel r1, int r2, byte r3) {
        zza(r1, r2, 4);
        r1.writeInt(r3);
    }

    public static void writeByteArray(Parcel r0, int r1, byte[] r2, boolean r3) {
        if (r2 != null) goto L6;
        if (r3 == false) goto L8;
        zza(r0, r1, 0);
        return;
    L8:
        return;
    L6:
        int r12 = zzb(r0, r1);
        r0.writeByteArray(r2);
        zzc(r0, r12);
    }

    public static void writeByteArrayArray(Parcel r2, int r3, byte[][] r4, boolean r5) {
        int r0 = 0;
        if (r4 != null) goto L7;
        if (r5 == false) goto L13;
        zza(r2, r3, 0);
        return;
    L13:
        return;
    L7:
        int r32 = zzb(r2, r3);
        int r52 = r4.length;
        r2.writeInt(r52);
    L8:
        if (r0 >= r52) goto L10;
        r2.writeByteArray(r4[r0]);
        r0 = r0 + 1;
        goto L8
    L10:
        zzc(r2, r32);
    }

    public static void writeDouble(Parcel r1, int r2, double r3) {
        zza(r1, r2, 8);
        r1.writeDouble(r3);
    }

    public static void writeDoubleObject(Parcel r0, int r1, Double r2, boolean r3) {
        if (r2 != null) goto L6;
        if (r3 == false) goto L8;
        zza(r0, r1, 0);
        return;
    L8:
        return;
    L6:
        zza(r0, r1, 8);
        r0.writeDouble(r2.doubleValue());
    }

    public static void writeFloat(Parcel r1, int r2, float r3) {
        zza(r1, r2, 4);
        r1.writeFloat(r3);
    }

    public static void writeFloatArray(Parcel r0, int r1, float[] r2, boolean r3) {
        if (r2 != null) goto L6;
        if (r3 == false) goto L8;
        zza(r0, r1, 0);
        return;
    L8:
        return;
    L6:
        int r12 = zzb(r0, r1);
        r0.writeFloatArray(r2);
        zzc(r0, r12);
    }

    public static void writeFloatList(Parcel r2, int r3, List<Float> r4, boolean r5) {
        int r0 = 0;
        if (r4 != null) goto L7;
        if (r5 == false) goto L13;
        zza(r2, r3, 0);
        return;
    L13:
        return;
    L7:
        int r32 = zzb(r2, r3);
        int r52 = r4.size();
        r2.writeInt(r52);
    L8:
        if (r0 >= r52) goto L10;
        r2.writeFloat(r4.get(r0).floatValue());
        r0 = r0 + 1;
        goto L8
    L10:
        zzc(r2, r32);
    }

    public static void writeFloatObject(Parcel r0, int r1, Float r2, boolean r3) {
        if (r2 != null) goto L6;
        if (r3 == false) goto L8;
        zza(r0, r1, 0);
        return;
    L8:
        return;
    L6:
        zza(r0, r1, 4);
        r0.writeFloat(r2.floatValue());
    }

    public static void writeIBinder(Parcel r0, int r1, IBinder r2, boolean r3) {
        if (r2 != null) goto L6;
        if (r3 == false) goto L8;
        zza(r0, r1, 0);
        return;
    L8:
        return;
    L6:
        int r12 = zzb(r0, r1);
        r0.writeStrongBinder(r2);
        zzc(r0, r12);
    }

    public static void writeInt(Parcel r1, int r2, int r3) {
        zza(r1, r2, 4);
        r1.writeInt(r3);
    }

    public static void writeIntArray(Parcel r0, int r1, int[] r2, boolean r3) {
        if (r2 != null) goto L6;
        if (r3 == false) goto L8;
        zza(r0, r1, 0);
        return;
    L8:
        return;
    L6:
        int r12 = zzb(r0, r1);
        r0.writeIntArray(r2);
        zzc(r0, r12);
    }

    public static void writeIntegerList(Parcel r2, int r3, List<Integer> r4, boolean r5) {
        int r0 = 0;
        if (r4 != null) goto L7;
        if (r5 == false) goto L13;
        zza(r2, r3, 0);
        return;
    L13:
        return;
    L7:
        int r32 = zzb(r2, r3);
        int r52 = r4.size();
        r2.writeInt(r52);
    L8:
        if (r0 >= r52) goto L10;
        r2.writeInt(r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L8
    L10:
        zzc(r2, r32);
    }

    public static void writeIntegerObject(Parcel r0, int r1, Integer r2, boolean r3) {
        if (r2 != null) goto L6;
        if (r3 == false) goto L8;
        zza(r0, r1, 0);
        return;
    L8:
        return;
    L6:
        zza(r0, r1, 4);
        r0.writeInt(r2.intValue());
    }

    public static void writeLong(Parcel r1, int r2, long r3) {
        zza(r1, r2, 8);
        r1.writeLong(r3);
    }

    public static void writeLongArray(Parcel r0, int r1, long[] r2, boolean r3) {
        if (r2 != null) goto L6;
        if (r3 == false) goto L8;
        zza(r0, r1, 0);
        return;
    L8:
        return;
    L6:
        int r12 = zzb(r0, r1);
        r0.writeLongArray(r2);
        zzc(r0, r12);
    }

    public static void writeLongObject(Parcel r0, int r1, Long r2, boolean r3) {
        if (r2 != null) goto L6;
        if (r3 == false) goto L8;
        zza(r0, r1, 0);
        return;
    L8:
        return;
    L6:
        zza(r0, r1, 8);
        r0.writeLong(r2.longValue());
    }

    public static void writeParcelable(Parcel r0, int r1, Parcelable r2, int r3, boolean r4) {
        if (r2 != null) goto L6;
        if (r4 == false) goto L8;
        zza(r0, r1, 0);
        return;
    L8:
        return;
    L6:
        int r12 = zzb(r0, r1);
        r2.writeToParcel(r0, r3);
        zzc(r0, r12);
    }

    public static void writeShort(Parcel r1, int r2, short r3) {
        zza(r1, r2, 4);
        r1.writeInt(r3);
    }

    public static void writeString(Parcel r0, int r1, String r2, boolean r3) {
        if (r2 != null) goto L6;
        if (r3 == false) goto L8;
        zza(r0, r1, 0);
        return;
    L8:
        return;
    L6:
        int r12 = zzb(r0, r1);
        r0.writeString(r2);
        zzc(r0, r12);
    }

    public static void writeStringArray(Parcel r0, int r1, String[] r2, boolean r3) {
        if (r2 != null) goto L6;
        if (r3 == false) goto L8;
        zza(r0, r1, 0);
        return;
    L8:
        return;
    L6:
        int r12 = zzb(r0, r1);
        r0.writeStringArray(r2);
        zzc(r0, r12);
    }

    public static void writeStringList(Parcel r0, int r1, List<String> r2, boolean r3) {
        if (r2 != null) goto L6;
        if (r3 == false) goto L8;
        zza(r0, r1, 0);
        return;
    L8:
        return;
    L6:
        int r12 = zzb(r0, r1);
        r0.writeStringList(r2);
        zzc(r0, r12);
    }

    public static <T extends Parcelable> void writeTypedArray(Parcel r3, int r4, T[] r5, int r6, boolean r7) {
        if (r5 != null) goto L7;
        if (r7 == false) goto L19;
        zza(r3, r4, 0);
        return;
    L19:
        return;
    L7:
        int r42 = zzb(r3, r4);
        int r72 = r5.length;
        r3.writeInt(r72);
        int r1 = 0;
    L8:
        if (r1 >= r72) goto L14;
        T r2 = r5[r1];
        if (r2 != null) goto L12;
        r3.writeInt(0);
    L13:
        r1 = r1 + 1;
        goto L8
    L12:
        zzd(r3, r2, r6);
        goto L13
    L14:
        zzc(r3, r42);
    }

    public static <T extends Parcelable> void writeTypedList(Parcel r3, int r4, List<T> r5, boolean r6) {
        if (r5 != null) goto L7;
        if (r6 == false) goto L19;
        zza(r3, r4, 0);
        return;
    L19:
        return;
    L7:
        int r42 = zzb(r3, r4);
        int r62 = r5.size();
        r3.writeInt(r62);
        int r1 = 0;
    L8:
        if (r1 >= r62) goto L14;
        T r2 = r5.get(r1);
        if (r2 != null) goto L12;
        r3.writeInt(0);
    L13:
        r1 = r1 + 1;
        goto L8
    L12:
        zzd(r3, r2, 0);
        goto L13
    L14:
        zzc(r3, r42);
    }

    private static void zza(Parcel r0, int r1, int r2) {
        r0.writeInt(r1 | (r2 << 16));
    }

    private static int zzb(Parcel r1, int r2) {
        r1.writeInt(r2 | Opcodes.V_PREVIEW);
        r1.writeInt(0);
        return r1.dataPosition();
    }

    private static void zzc(Parcel r2, int r3) {
        int r0 = r2.dataPosition();
        r2.setDataPosition(r3 - 4);
        r2.writeInt(r0 - r3);
        r2.setDataPosition(r0);
    }

    private static void zzd(Parcel r2, Parcelable r3, int r4) {
        int r0 = r2.dataPosition();
        r2.writeInt(1);
        int r1 = r2.dataPosition();
        r3.writeToParcel(r2, r4);
        int r32 = r2.dataPosition();
        r2.setDataPosition(r0);
        r2.writeInt(r32 - r1);
        r2.setDataPosition(r32);
    }
}
