package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import org.mvel2.asm.Opcodes;

/* loaded from: classes4.dex */
public abstract class SafeParcelReader {

    public static class ParseException extends RuntimeException {
        public ParseException(String r5, Parcel r6) {
            int r0 = r6.dataPosition();
            int r62 = r6.dataSize();
            int r1 = String.valueOf(r5).length();
            int r2 = String.valueOf(r0).length();
            int r12 = ((r1 + 13) + r2) + 6;
            StringBuilder r22 = new StringBuilder(r12 + String.valueOf(r62).length());
            r22.append(r5);
            r22.append(" Parcel: pos=");
            r22.append(r0);
            r22.append(" size=");
            r22.append(r62);
            super(r22.toString());
        }
    }

    public static Bundle createBundle(Parcel r2, int r3) {
        int r32 = readSize(r2, r3);
        int r0 = r2.dataPosition();
        if (r32 != 0) goto L6;
        return null;
    L6:
        Bundle r1 = r2.readBundle();
        r2.setDataPosition(r0 + r32);
        return r1;
    }

    public static byte[] createByteArray(Parcel r2, int r3) {
        int r32 = readSize(r2, r3);
        int r0 = r2.dataPosition();
        if (r32 != 0) goto L6;
        return null;
    L6:
        byte[] r1 = r2.createByteArray();
        r2.setDataPosition(r0 + r32);
        return r1;
    }

    public static byte[][] createByteArrayArray(Parcel r5, int r6) {
        int r62 = readSize(r5, r6);
        int r0 = r5.dataPosition();
        if (r62 != 0) goto L6;
        return null;
    L6:
        int r1 = r5.readInt();
        byte[][] r2 = new byte[r1][];
        int r3 = 0;
    L7:
        if (r3 >= r1) goto L9;
        r2[r3] = r5.createByteArray();
        r3 = r3 + 1;
        goto L7
    L9:
        r5.setDataPosition(r0 + r62);
        return r2;
    }

    public static float[] createFloatArray(Parcel r2, int r3) {
        int r32 = readSize(r2, r3);
        int r0 = r2.dataPosition();
        if (r32 != 0) goto L6;
        return null;
    L6:
        float[] r1 = r2.createFloatArray();
        r2.setDataPosition(r0 + r32);
        return r1;
    }

    public static ArrayList<Float> createFloatList(Parcel r5, int r6) {
        int r62 = readSize(r5, r6);
        int r0 = r5.dataPosition();
        if (r62 != 0) goto L6;
        return null;
    L6:
        ArrayList<Float> r1 = new ArrayList();
        int r2 = r5.readInt();
        int r3 = 0;
    L7:
        if (r3 >= r2) goto L9;
        r1.add(Float.valueOf(r5.readFloat()));
        r3 = r3 + 1;
        goto L7
    L9:
        r5.setDataPosition(r0 + r62);
        return r1;
    }

    public static int[] createIntArray(Parcel r2, int r3) {
        int r32 = readSize(r2, r3);
        int r0 = r2.dataPosition();
        if (r32 != 0) goto L6;
        return null;
    L6:
        int[] r1 = r2.createIntArray();
        r2.setDataPosition(r0 + r32);
        return r1;
    }

    public static ArrayList<Integer> createIntegerList(Parcel r5, int r6) {
        int r62 = readSize(r5, r6);
        int r0 = r5.dataPosition();
        if (r62 != 0) goto L6;
        return null;
    L6:
        ArrayList<Integer> r1 = new ArrayList();
        int r2 = r5.readInt();
        int r3 = 0;
    L7:
        if (r3 >= r2) goto L9;
        r1.add(Integer.valueOf(r5.readInt()));
        r3 = r3 + 1;
        goto L7
    L9:
        r5.setDataPosition(r0 + r62);
        return r1;
    }

    public static long[] createLongArray(Parcel r2, int r3) {
        int r32 = readSize(r2, r3);
        int r0 = r2.dataPosition();
        if (r32 != 0) goto L6;
        return null;
    L6:
        long[] r1 = r2.createLongArray();
        r2.setDataPosition(r0 + r32);
        return r1;
    }

    public static <T extends Parcelable> T createParcelable(Parcel r1, int r2, Parcelable.Creator<T> r3) {
        int r22 = readSize(r1, r2);
        int r0 = r1.dataPosition();
        if (r22 != 0) goto L6;
        return null;
    L6:
        T r32 = r3.createFromParcel(r1);
        r1.setDataPosition(r0 + r22);
        return r32;
    }

    public static String createString(Parcel r2, int r3) {
        int r32 = readSize(r2, r3);
        int r0 = r2.dataPosition();
        if (r32 != 0) goto L6;
        return null;
    L6:
        String r1 = r2.readString();
        r2.setDataPosition(r0 + r32);
        return r1;
    }

    public static String[] createStringArray(Parcel r2, int r3) {
        int r32 = readSize(r2, r3);
        int r0 = r2.dataPosition();
        if (r32 != 0) goto L6;
        return null;
    L6:
        String[] r1 = r2.createStringArray();
        r2.setDataPosition(r0 + r32);
        return r1;
    }

    public static ArrayList<String> createStringList(Parcel r2, int r3) {
        int r32 = readSize(r2, r3);
        int r0 = r2.dataPosition();
        if (r32 != 0) goto L6;
        return null;
    L6:
        ArrayList<String> r1 = r2.createStringArrayList();
        r2.setDataPosition(r0 + r32);
        return r1;
    }

    public static <T> T[] createTypedArray(Parcel r1, int r2, Parcelable.Creator<T> r3) {
        int r22 = readSize(r1, r2);
        int r0 = r1.dataPosition();
        if (r22 != 0) goto L6;
        return null;
    L6:
        T[] r32 = (T[]) r1.createTypedArray(r3);
        r1.setDataPosition(r0 + r22);
        return r32;
    }

    public static <T> ArrayList<T> createTypedList(Parcel r1, int r2, Parcelable.Creator<T> r3) {
        int r22 = readSize(r1, r2);
        int r0 = r1.dataPosition();
        if (r22 != 0) goto L6;
        return null;
    L6:
        ArrayList<T> r32 = r1.createTypedArrayList(r3);
        r1.setDataPosition(r0 + r22);
        return r32;
    }

    public static void ensureAtEnd(Parcel r3, int r4) {
        if (r3.dataPosition() != r4) goto L5;
        return;
    L5:
        StringBuilder r2 = new StringBuilder(String.valueOf(r4).length() + 26);
        r2.append("Overread allowed size end=");
        r2.append(r4);
        throw new ParseException(r2.toString(), r3);
    }

    public static int getFieldId(int r0) {
        return (char) r0;
    }

    public static boolean readBoolean(Parcel r1, int r2) {
        zza(r1, r2, 4);
        if (r1.readInt() == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static Boolean readBooleanObject(Parcel r2, int r3) {
        int r0 = readSize(r2, r3);
        if (r0 != 0) goto L6;
        return null;
    L6:
        zzb(r2, r3, r0, 4);
        if (r2.readInt() == 0) goto L9;
        boolean r22 = true;
    L11:
        return Boolean.valueOf(r22);
    L9:
        r22 = false;
        goto L11
    }

    public static byte readByte(Parcel r1, int r2) {
        zza(r1, r2, 4);
        return (byte) r1.readInt();
    }

    public static double readDouble(Parcel r1, int r2) {
        zza(r1, r2, 8);
        return r1.readDouble();
    }

    public static Double readDoubleObject(Parcel r2, int r3) {
        int r0 = readSize(r2, r3);
        if (r0 != 0) goto L6;
        return null;
    L6:
        zzb(r2, r3, r0, 8);
        return Double.valueOf(r2.readDouble());
    }

    public static float readFloat(Parcel r1, int r2) {
        zza(r1, r2, 4);
        return r1.readFloat();
    }

    public static Float readFloatObject(Parcel r2, int r3) {
        int r0 = readSize(r2, r3);
        if (r0 != 0) goto L6;
        return null;
    L6:
        zzb(r2, r3, r0, 4);
        return Float.valueOf(r2.readFloat());
    }

    public static int readHeader(Parcel r0) {
        return r0.readInt();
    }

    public static IBinder readIBinder(Parcel r2, int r3) {
        int r32 = readSize(r2, r3);
        int r0 = r2.dataPosition();
        if (r32 != 0) goto L6;
        return null;
    L6:
        IBinder r1 = r2.readStrongBinder();
        r2.setDataPosition(r0 + r32);
        return r1;
    }

    public static int readInt(Parcel r1, int r2) {
        zza(r1, r2, 4);
        return r1.readInt();
    }

    public static Integer readIntegerObject(Parcel r2, int r3) {
        int r0 = readSize(r2, r3);
        if (r0 != 0) goto L6;
        return null;
    L6:
        zzb(r2, r3, r0, 4);
        return Integer.valueOf(r2.readInt());
    }

    public static long readLong(Parcel r1, int r2) {
        zza(r1, r2, 8);
        return r1.readLong();
    }

    public static Long readLongObject(Parcel r2, int r3) {
        int r0 = readSize(r2, r3);
        if (r0 != 0) goto L6;
        return null;
    L6:
        zzb(r2, r3, r0, 8);
        return Long.valueOf(r2.readLong());
    }

    public static short readShort(Parcel r1, int r2) {
        zza(r1, r2, 4);
        return (short) r1.readInt();
    }

    public static int readSize(Parcel r2, int r3) {
        if ((r3 & Opcodes.V_PREVIEW) == (-65536)) goto L7;
        return (char) (r3 >> 16);
    L7:
        return r2.readInt();
    }

    public static void skipUnknownField(Parcel r1, int r2) {
        int r22 = readSize(r1, r2);
        r1.setDataPosition(r1.dataPosition() + r22);
    }

    public static int validateObjectHeader(Parcel r5) {
        int r0 = readHeader(r5);
        int r1 = readSize(r5, r0);
        int r2 = getFieldId(r0);
        int r3 = r5.dataPosition();
        if (r2 != 20293) goto L12;
        int r12 = r1 + r3;
        if (r12 >= r3) goto L7;
    L9:
        int r22 = String.valueOf(r3).length();
        StringBuilder r4 = new StringBuilder((r22 + 32) + String.valueOf(r12).length());
        r4.append("Size read is invalid start=");
        r4.append(r3);
        r4.append(" end=");
        r4.append(r12);
        throw new ParseException(r4.toString(), r5);
    L7:
        if (r12 > r5.dataSize()) goto L9;
        return r12;
    L12:
        throw new ParseException("Expected object header. Got 0x".concat(String.valueOf(Integer.toHexString(r0))), r5);
    }

    private static void zza(Parcel r5, int r6, int r7) {
        int r62 = readSize(r5, r6);
        if (r62 != r7) goto L5;
        return;
    L5:
        String r1 = Integer.toHexString(r62);
        int r2 = String.valueOf(r7).length();
        int r3 = String.valueOf(r62).length();
        StringBuilder r32 = new StringBuilder(((((r2 + 19) + r3) + 4) + String.valueOf(r1).length()) + 1);
        r32.append("Expected size ");
        r32.append(r7);
        r32.append(" got ");
        r32.append(r62);
        r32.append(" (0x");
        r32.append(r1);
        r32.append(")");
        throw new ParseException(r32.toString(), r5);
    }

    private static void zzb(Parcel r4, int r5, int r6, int r7) {
        if (r6 != r7) goto L4;
        return;
    L4:
        String r0 = Integer.toHexString(r6);
        int r1 = String.valueOf(r7).length();
        int r2 = String.valueOf(r6).length();
        StringBuilder r22 = new StringBuilder(((((r1 + 19) + r2) + 4) + String.valueOf(r0).length()) + 1);
        r22.append("Expected size ");
        r22.append(r7);
        r22.append(" got ");
        r22.append(r6);
        r22.append(" (0x");
        r22.append(r0);
        r22.append(")");
        throw new ParseException(r22.toString(), r4);
    }
}
