package kotlin.uuid;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0012\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0080\u0080\u0004\u001a\u0012\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0081\u0080\u0004\u001a\u0016\u0010\b\u001a\u00020\t*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0081\u0080\u0004\u001a.\u0010\f\u001a\u00020\u0001*\u00020\t2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000bH\u0081\u0080\u0004\u001a\u001e\u0010\u0011\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\tH\u0081\u0080\u0004\u001a\u0012\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0015H\u0081\u0080\u0004\u001a\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0014\u001a\u00020\u0015H\u0081\u0080\u0004\u001a\u0012\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0015H\u0081\u0080\u0004\u001a\u0014\u0010\u0019\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0018\u001a\u00020\u0015H\u0081\u0080\u0004\u001a\u000e\u0010\u001a\u001a\u00020\u0007*\u00020\u001bH\u0087\u0088\u0004\u001a\u000e\u0010\u001c\u001a\u00020\u001b*\u00020\u0007H\u0087\u0088\u0004\u001a\u000e\u0010\u001d\u001a\u00020\u0007*\u00020\u001eH\u0087\u0080\u0004\u001a\u0016\u0010\u001d\u001a\u00020\u0007*\u00020\u001e2\u0006\u0010\n\u001a\u00020\u000bH\u0087\u0080\u0004\u001a\u0016\u0010\u001f\u001a\u00020\u001e*\u00020\u001e2\u0006\u0010\u0006\u001a\u00020\u0007H\u0087\u0080\b\u001a\u001e\u0010\u001f\u001a\u00020\u001e*\u00020\u001e2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u0087\u0080\b\u001a\u000e\u0010 \u001a\u00020\t*\u00020\tH\u0080\u0088\u0004¨\u0006!"}, d2 = {"secureRandomBytes", _UrlKt.FRAGMENT_ENCODE_SET, "destination", _UrlKt.FRAGMENT_ENCODE_SET, "serializedUuid", _UrlKt.FRAGMENT_ENCODE_SET, "uuid", "Lkotlin/uuid/Uuid;", "getLongAt", _UrlKt.FRAGMENT_ENCODE_SET, "index", _UrlKt.FRAGMENT_ENCODE_SET, "formatBytesInto", "dst", "dstOffset", "startIndex", "endIndex", "setLongAt", "value", "uuidParseHexDash", "hexDashString", _UrlKt.FRAGMENT_ENCODE_SET, "uuidParseHexDashOrNull", "uuidParseHex", "hexString", "uuidParseHexOrNull", "toKotlinUuid", "Ljava/util/UUID;", "toJavaUuid", "getUuid", "Ljava/nio/ByteBuffer;", "putUuid", "reverseBytes", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/uuid/UuidKt")
@SourceDebugExtension({"SMAP\nUuidJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UuidJVM.kt\nkotlin/uuid/UuidKt__UuidJVMKt\n*L\n1#1,285:1\n284#1:286\n284#1:287\n284#1:288\n284#1:289\n284#1:290\n284#1:291\n284#1:292\n284#1:293\n*S KotlinDebug\n*F\n+ 1 UuidJVM.kt\nkotlin/uuid/UuidKt__UuidJVMKt\n*L\n144#1:286\n145#1:287\n189#1:288\n190#1:289\n230#1:290\n231#1:291\n278#1:292\n279#1:293\n*E\n"})
/* loaded from: classes5.dex */
class UuidKt__UuidJVMKt {
    public UuidKt__UuidJVMKt() {
    }

    @ExperimentalUuidApi
    public static final void formatBytesInto(long r0, byte[] r2, int r3, int r4, int r5) {
        UuidKt__UuidKt.formatBytesIntoCommonImpl(r0, r2, r3, r4, r5);
    }

    @ExperimentalUuidApi
    public static final long getLongAt(byte[] r0, int r1) {
        return UuidKt__UuidKt.getLongAtCommonImpl(r0, r1);
    }

    @SinceKotlin(version = "2.0")
    @ExperimentalUuidApi
    public static final Uuid getUuid(ByteBuffer r5) {
        if ((r5.position() + 15) >= r5.limit()) goto L10;
        long r0 = r5.getLong();
        long r2 = r5.getLong();
        if (Intrinsics.areEqual(r5.order(), ByteOrder.LITTLE_ENDIAN) == false) goto L8;
        r0 = Long.reverseBytes(r0);
        r2 = Long.reverseBytes(r2);
    L8:
        return Uuid.Companion.fromLongs(r0, r2);
    L10:
        throw new BufferUnderflowException();
    }

    @SinceKotlin(version = "2.0")
    @ExperimentalUuidApi
    public static final Uuid getUuid(ByteBuffer r4, int r5) {
        if (r5 < 0) goto L13;
        if ((r5 + 15) >= r4.limit()) goto L11;
        long r0 = r4.getLong(r5);
        long r2 = r4.getLong(r5 + 8);
        if (Intrinsics.areEqual(r4.order(), ByteOrder.LITTLE_ENDIAN) == false) goto L9;
        r0 = Long.reverseBytes(r0);
        r2 = Long.reverseBytes(r2);
    L9:
        return Uuid.Companion.fromLongs(r0, r2);
    L11:
        throw new IndexOutOfBoundsException("Not enough bytes to read a uuid at index: " + r5 + ", with limit: " + r4.limit() + ' ');
    L13:
        throw new IndexOutOfBoundsException("Negative index: " + r5);
    }

    @SinceKotlin(version = "2.0")
    @IgnorableReturnValue
    @ExperimentalUuidApi
    public static final ByteBuffer putUuid(ByteBuffer r5, int r6, Uuid r7) {
        long r0 = r7.getMostSignificantBits();
        long r2 = r7.getLeastSignificantBits();
        if (r6 < 0) goto L14;
        if ((r6 + 15) >= r5.limit()) goto L12;
        if (Intrinsics.areEqual(r5.order(), ByteOrder.BIG_ENDIAN) == false) goto L9;
        r5.putLong(r6, r0);
        return r5.putLong(r6 + 8, r2);
    L9:
        r5.putLong(r6, Long.reverseBytes(r0));
        return r5.putLong(r6 + 8, Long.reverseBytes(r2));
    L12:
        throw new IndexOutOfBoundsException("Not enough capacity to write a uuid at index: " + r6 + ", with limit: " + r5.limit() + ' ');
    L14:
        throw new IndexOutOfBoundsException("Negative index: " + r6);
    }

    @SinceKotlin(version = "2.0")
    @IgnorableReturnValue
    @ExperimentalUuidApi
    public static final ByteBuffer putUuid(ByteBuffer r5, Uuid r6) {
        long r0 = r6.getMostSignificantBits();
        long r2 = r6.getLeastSignificantBits();
        if ((r5.position() + 15) >= r5.limit()) goto L10;
        if (Intrinsics.areEqual(r5.order(), ByteOrder.BIG_ENDIAN) == false) goto L7;
        r5.putLong(r0);
        return r5.putLong(r2);
    L7:
        r5.putLong(Long.reverseBytes(r0));
        return r5.putLong(Long.reverseBytes(r2));
    L10:
        throw new BufferOverflowException();
    }

    public static final long reverseBytes(long r0) {
        return Long.reverseBytes(r0);
    }

    public static final void secureRandomBytes(byte[] r1) {
        SecureRandomHolder.INSTANCE.getInstance().nextBytes(r1);
    }

    @ExperimentalUuidApi
    public static final Object serializedUuid(Uuid r5) {
        return new UuidSerialized(r5.getMostSignificantBits(), r5.getLeastSignificantBits());
    }

    @ExperimentalUuidApi
    public static final void setLongAt(byte[] r0, int r1, long r2) {
        UuidKt__UuidKt.setLongAtCommonImpl(r0, r1, r2);
    }

    @SinceKotlin(version = "2.0")
    @ExperimentalUuidApi
    public static final UUID toJavaUuid(Uuid r4) {
        return new UUID(r4.getMostSignificantBits(), r4.getLeastSignificantBits());
    }

    @SinceKotlin(version = "2.0")
    @ExperimentalUuidApi
    public static final Uuid toKotlinUuid(UUID r5) {
        return Uuid.Companion.fromLongs(r5.getMostSignificantBits(), r5.getLeastSignificantBits());
    }

    @ExperimentalUuidApi
    public static final Uuid uuidParseHex(String r0) {
        return UuidKt__UuidKt.uuidParseHexCommonImpl(r0);
    }

    @ExperimentalUuidApi
    public static final Uuid uuidParseHexDash(String r0) {
        return UuidKt__UuidKt.uuidParseHexDashCommonImpl(r0);
    }

    @ExperimentalUuidApi
    public static final Uuid uuidParseHexDashOrNull(String r0) {
        return UuidKt__UuidKt.uuidParseHexDashOrNullCommonImpl(r0);
    }

    @ExperimentalUuidApi
    public static final Uuid uuidParseHexOrNull(String r0) {
        return UuidKt__UuidKt.uuidParseHexOrNullCommonImpl(r0);
    }
}
