package androidx.media3.extractor.mp4;

import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import java.nio.ByteBuffer;
import java.util.UUID;

/* loaded from: classes4.dex */
public abstract class PsshAtomUtil {

    public static final class PsshAtom {
        public final UUID[] keyIds;
        public final byte[] schemeData;
        public final UUID uuid;
        public final int version;

        public PsshAtom(UUID r1, int r2, byte[] r3, UUID[] r4) {
            this.uuid = r1;
            this.version = r2;
            this.schemeData = r3;
            this.keyIds = r4;
        }
    }

    public static byte[] buildPsshAtom(UUID r1, byte[] r2) {
        return buildPsshAtom(r1, null, r2);
    }

    public static byte[] buildPsshAtom(UUID r6, UUID[] r7, byte[] r8) {
        if (r8 == null) goto L5;
        int r1 = r8.length;
    L6:
        int r12 = r1 + 32;
        if (r7 == null) goto L9;
        r12 = r12 + ((r7.length * 16) + 4);
    L9:
        ByteBuffer r2 = ByteBuffer.allocate(r12);
        r2.putInt(r12);
        r2.putInt(1886614376);
        if (r7 == null) goto L12;
        int r13 = 16777216;
    L13:
        r2.putInt(r13);
        r2.putLong(r6.getMostSignificantBits());
        r2.putLong(r6.getLeastSignificantBits());
        if (r7 == null) goto L18;
        r2.putInt(r7.length);
        int r62 = r7.length;
        int r14 = 0;
    L16:
        if (r14 >= r62) goto L18;
        UUID r3 = r7[r14];
        r2.putLong(r3.getMostSignificantBits());
        r2.putLong(r3.getLeastSignificantBits());
        r14 = r14 + 1;
    L18:
        if (r8 != null) goto L20;
    L22:
        r2.putInt(0);
    L24:
        return r2.array();
    L20:
        if (r8.length == 0) goto L22;
        r2.putInt(r8.length);
        r2.put(r8);
        goto L24
    L12:
        r13 = 0;
        goto L13
    L5:
        r1 = 0;
        goto L6
    }

    public static boolean isPsshAtom(byte[] r0) {
        if (parsePsshAtom(r0) == null) goto L6;
        return true;
    L6:
        return false;
    }

    public static PsshAtom parsePsshAtom(byte[] r13) {
        ParsableByteArray r0 = new ParsableByteArray(r13);
        if (r0.limit() >= 32) goto L5;
        return null;
    L5:
        r0.setPosition(0);
        int r1 = r0.bytesLeft();
        int r3 = r0.readInt();
        if (r3 == r1) goto L9;
        Log.w("PsshAtomUtil", "Advertised atom size (" + r3 + ") does not match buffer size: " + r1);
        return null;
    L9:
        int r12 = r0.readInt();
        if (r12 == 1886614376) goto L13;
        Log.w("PsshAtomUtil", "Atom type is not pssh: " + r12);
        return null;
    L13:
        int r14 = BoxParser.parseFullBoxVersion(r0.readInt());
        if (r14 <= 1) goto L17;
        Log.w("PsshAtomUtil", "Unsupported pssh version: " + r14);
        return null;
    L17:
        UUID r5 = new UUID(r0.readLong(), r0.readLong());
        if (r14 != 1) goto L22;
        int r32 = r0.readUnsignedIntToInt();
        UUID[] r6 = new UUID[r32];
        int r7 = 0;
    L20:
        if (r7 >= r32) goto L23;
        r6[r7] = new UUID(r0.readLong(), r0.readLong());
        r7 = r7 + 1;
    L23:
        int r33 = r0.readUnsignedIntToInt();
        int r72 = r0.bytesLeft();
        if (r33 == r72) goto L27;
        Log.w("PsshAtomUtil", "Atom data size (" + r33 + ") does not match the bytes left: " + r72);
        return null;
    L27:
        byte[] r2 = new byte[r33];
        r0.readBytes(r2, 0, r33);
        return new PsshAtom(r5, r14, r2, r6);
    L22:
        r6 = null;
        goto L23
    }

    public static byte[] parseSchemeSpecificData(byte[] r3, UUID r4) {
        PsshAtom r32 = parsePsshAtom(r3);
        if (r32 != null) goto L6;
        return null;
    L6:
        if (r4.equals(r32.uuid) == true) goto L10;
        Log.w("PsshAtomUtil", "UUID mismatch. Expected: " + r4 + ", got: " + r32.uuid + ".");
        return null;
    L10:
        return r32.schemeData;
    }

    public static UUID parseUuid(byte[] r0) {
        PsshAtom r02 = parsePsshAtom(r0);
        if (r02 != null) goto L7;
        return null;
    L7:
        return r02.uuid;
    }

    public static int parseVersion(byte[] r0) {
        PsshAtom r02 = parsePsshAtom(r0);
        if (r02 != null) goto L7;
        return -1;
    L7:
        return r02.version;
    }
}
