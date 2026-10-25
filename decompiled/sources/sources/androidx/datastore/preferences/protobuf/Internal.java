package androidx.datastore.preferences.protobuf;

import c.f$$ExternalSyntheticBUOutline2;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes.dex */
public abstract class Internal {
    public static final byte[] EMPTY_BYTE_ARRAY = null;
    public static final ByteBuffer EMPTY_BYTE_BUFFER = null;
    public static final CodedInputStream EMPTY_CODED_INPUT_STREAM = null;
    static final Charset ISO_8859_1 = null;
    static final Charset US_ASCII = null;
    static final Charset UTF_8 = null;

    public interface EnumVerifier {
        boolean isInRange(int r1);
    }

    public interface ProtobufList<E> extends List<E>, RandomAccess {
        boolean isModifiable();

        void makeImmutable();

        ProtobufList<E> mutableCopyWithCapacity(int r1);
    }

    static {
        US_ASCII = Charset.forName("US-ASCII");
        UTF_8 = Charset.forName("UTF-8");
        ISO_8859_1 = Charset.forName("ISO-8859-1");
        byte[] r0 = new byte[0];
        EMPTY_BYTE_ARRAY = r0;
        EMPTY_BYTE_BUFFER = ByteBuffer.wrap(r0);
        EMPTY_CODED_INPUT_STREAM = CodedInputStream.newInstance(r0);
    }

    public static <T> T checkNotNull(T r0) {
        r0.getClass();
        return r0;
    }

    public static <T> T checkNotNull(T r0, String r1) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        f$$ExternalSyntheticBUOutline2.m(r1);
        return null;
    }

    public static int hashBoolean(boolean r0) {
        if (r0 == false) goto L5;
        return 1231;
    L5:
        return 1237;
    }

    public static int hashCode(byte[] r2) {
        return hashCode(r2, 0, r2.length);
    }

    public static int hashCode(byte[] r0, int r1, int r2) {
        int r02 = partialHash(r2, r0, r1, r2);
        if (r02 != 0) goto L6;
        return 1;
    L6:
        return r02;
    }

    public static int hashLong(long r2) {
        return (int) (r2 ^ (r2 >>> 32));
    }

    public static int partialHash(int r2, byte[] r3, int r4, int r5) {
        int r0 = r4;
    L4:
        if (r0 >= (r4 + r5)) goto L6;
        r2 = (r2 * 31) + r3[r0];
        r0 = r0 + 1;
        goto L4
    L6:
        return r2;
    }
}
