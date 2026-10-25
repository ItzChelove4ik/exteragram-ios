package okio.internal;

import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.ArrayList;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.ByteString;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u000f\u001a\u0013\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0002¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0013\u0010\u0005\u001a\u00020\u0004*\u00020\u0000H\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a#\u0010\t\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u001b\u0010\f\u001a\u00020\u0000*\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\f\u0010\r\u001a\u001b\u0010\u000f\u001a\u00020\u0000*\u00020\u000e2\u0006\u0010\b\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u0013\u0010\u0012\u001a\u00020\u0011*\u00020\u000bH\u0002¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u0013\u0010\u0012\u001a\u00020\u0011*\u00020\u0014H\u0002¢\u0006\u0004\b\u0012\u0010\u0015\u001a\u001b\u0010\u0017\u001a\u00020\u0004*\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0017\u0010\u0018\"\u0014\u0010\u0019\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a\"\u0014\u0010\u001b\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001a\"\u0014\u0010\u001c\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001a\"\u0014\u0010\u001d\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001a\"\u0014\u0010\u001e\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001a\"\u0018\u0010 \u001a\u00020\u0001*\u00020\u00008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0003\"\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u0011*\u00020\u00008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"¨\u0006#"}, d2 = {"Lokio/Path;", _UrlKt.FRAGMENT_ENCODE_SET, "rootLength", "(Lokio/Path;)I", _UrlKt.FRAGMENT_ENCODE_SET, "lastSegmentIsDotDot", "(Lokio/Path;)Z", "child", "normalize", "commonResolve", "(Lokio/Path;Lokio/Path;Z)Lokio/Path;", _UrlKt.FRAGMENT_ENCODE_SET, "commonToPath", "(Ljava/lang/String;Z)Lokio/Path;", "Lokio/Buffer;", "toPath", "(Lokio/Buffer;Z)Lokio/Path;", "Lokio/ByteString;", "toSlash", "(Ljava/lang/String;)Lokio/ByteString;", _UrlKt.FRAGMENT_ENCODE_SET, "(B)Lokio/ByteString;", "slash", "startsWithVolumeLetterAndColon", "(Lokio/Buffer;Lokio/ByteString;)Z", "SLASH", "Lokio/ByteString;", "BACKSLASH", "ANY_SLASH", "DOT", "DOT_DOT", "getIndexOfLastSlash", "indexOfLastSlash", "getSlash", "(Lokio/Path;)Lokio/ByteString;", "okio"}, k = 2, mv = {2, 2, 0}, xi = 48)
@JvmName(name = "-Path")
@SourceDebugExtension({"SMAP\nPath.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Path.kt\nokio/internal/-Path\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,405:1\n53#1,22:406\n203#1:432\n203#1:433\n1563#2:428\n1634#2,3:429\n*S KotlinDebug\n*F\n+ 1 Path.kt\nokio/internal/-Path\n*L\n47#1:406,22\n193#1:432\n198#1:433\n47#1:428\n47#1:429,3\n*E\n"})
/* renamed from: okio.internal.-Path, reason: invalid class name */
/* loaded from: classes5.dex */
public abstract class Path {
    private static final ByteString ANY_SLASH = null;
    private static final ByteString BACKSLASH = null;
    private static final ByteString DOT = null;
    private static final ByteString DOT_DOT = null;
    private static final ByteString SLASH = null;

    static {
        ByteString.Companion r0 = ByteString.Companion;
        SLASH = r0.encodeUtf8("/");
        BACKSLASH = r0.encodeUtf8("\\");
        ANY_SLASH = r0.encodeUtf8("/\\");
        DOT = r0.encodeUtf8(".");
        DOT_DOT = r0.encodeUtf8("..");
    }

    public static final /* synthetic */ ByteString access$getBACKSLASH$p() {
        return BACKSLASH;
    }

    public static final /* synthetic */ ByteString access$getDOT$p() {
        return DOT;
    }

    public static final /* synthetic */ ByteString access$getDOT_DOT$p() {
        return DOT_DOT;
    }

    public static final /* synthetic */ int access$getIndexOfLastSlash(okio.Path r0) {
        return getIndexOfLastSlash(r0);
    }

    public static final /* synthetic */ ByteString access$getSLASH$p() {
        return SLASH;
    }

    public static final /* synthetic */ ByteString access$getSlash(okio.Path r0) {
        return getSlash(r0);
    }

    public static final /* synthetic */ boolean access$lastSegmentIsDotDot(okio.Path r0) {
        return lastSegmentIsDotDot(r0);
    }

    public static final /* synthetic */ int access$rootLength(okio.Path r0) {
        return rootLength(r0);
    }

    public static final /* synthetic */ ByteString access$toSlash(String r0) {
        return toSlash(r0);
    }

    public static final okio.Path commonResolve(okio.Path r6, okio.Path r7, boolean r8) {
        if (r7.isAbsolute() == false) goto L5;
    L17:
        return r7;
    L5:
        if (r7.volumeLetter() != null) goto L17;
        ByteString r0 = getSlash(r6);
        if (r0 != null) goto L12;
        r0 = getSlash(r7);
        if (r0 != null) goto L12;
        r0 = toSlash(okio.Path.DIRECTORY_SEPARATOR);
    L12:
        Buffer r1 = new Buffer();
        r1.write(r6.getBytes$okio());
        if (r1.size() <= 0) goto L15;
        r1.write(r0);
    L15:
        r1.write(r7.getBytes$okio());
        return toPath(r1, r8);
    }

    public static final okio.Path commonToPath(String r1, boolean r2) {
        return toPath(new Buffer().writeUtf8(r1), r2);
    }

    private static final int getIndexOfLastSlash(okio.Path r5) {
        int r0 = ByteString.lastIndexOf$default(r5.getBytes$okio(), SLASH, 0, 2, null);
        if (r0 == (-1)) goto L6;
        return r0;
    L6:
        return ByteString.lastIndexOf$default(r5.getBytes$okio(), BACKSLASH, 0, 2, null);
    }

    private static final ByteString getSlash(okio.Path r6) {
        ByteString r0 = r6.getBytes$okio();
        ByteString r1 = SLASH;
        if (ByteString.indexOf$default(r0, r1, 0, 2, null) == (-1)) goto L5;
        return r1;
    L5:
        ByteString r62 = r6.getBytes$okio();
        ByteString r02 = BACKSLASH;
        if (ByteString.indexOf$default(r62, r02, 0, 2, null) == (-1)) goto L8;
        return r02;
    L8:
        return null;
    }

    private static final boolean lastSegmentIsDotDot(okio.Path r5) {
        if (r5.getBytes$okio().endsWith(DOT_DOT) == true) goto L5;
    L13:
        return false;
    L5:
        if (r5.getBytes$okio().size() != 2) goto L8;
        return true;
    L8:
        if (r5.getBytes$okio().rangeEquals(r5.getBytes$okio().size() - 3, SLASH, 0, 1) == false) goto L11;
        return true;
    L11:
        if (r5.getBytes$okio().rangeEquals(r5.getBytes$okio().size() - 3, BACKSLASH, 0, 1) == false) goto L13;
        return true;
    }

    private static final int rootLength(okio.Path r6) {
        if (r6.getBytes$okio().size() != 0) goto L6;
        return -1;
    L6:
        if (r6.getBytes$okio().getByte(0) != 47) goto L9;
        return 1;
    L9:
        if (r6.getBytes$okio().getByte(0) != 92) goto L21;
        if (r6.getBytes$okio().size() > 2) goto L13;
    L19:
        return 1;
    L13:
        if (r6.getBytes$okio().getByte(1) != 92) goto L19;
        int r0 = r6.getBytes$okio().indexOf(BACKSLASH, 2);
        if (r0 == (-1)) goto L17;
        return r0;
    L17:
        return r6.getBytes$okio().size();
    L21:
        if (r6.getBytes$okio().size() > 2) goto L23;
    L37:
        return -1;
    L23:
        if (r6.getBytes$okio().getByte(1) != 58) goto L37;
        if (r6.getBytes$okio().getByte(2) != 92) goto L37;
        char r62 = (char) r6.getBytes$okio().getByte(0);
        if ('a' > r62) goto L32;
        if (r62 >= '{') goto L32;
        return 3;
    L32:
        if ('A' > r62) goto L37;
        if (r62 >= '[') goto L37;
        return 3;
    }

    private static final boolean startsWithVolumeLetterAndColon(Buffer r5, ByteString r6) {
        if (Intrinsics.areEqual(r6, BACKSLASH) == true) goto L6;
        return false;
    L6:
        if (r5.size() >= 2) goto L9;
        return false;
    L9:
        if (r5.getByte(1) == 58) goto L11;
        return false;
    L11:
        char r52 = (char) r5.getByte(0);
        if ('a' > r52) goto L17;
        if (r52 >= '{') goto L17;
        return true;
    L17:
        if ('A' <= r52) goto L19;
    L22:
        return false;
    L19:
        if (r52 >= '[') goto L22;
        return true;
    }

    public static final okio.Path toPath(Buffer r16, boolean r17) {
        Buffer r1 = new Buffer();
        ByteString r2 = null;
        int r3 = 0;
        int r4 = 0;
    L4:
        if (r16.rangeEquals(0, SLASH) == true) goto L75;
        ByteString r5 = BACKSLASH;
        if (r16.rangeEquals(0, r5) == true) goto L75;
        if (r4 >= 2) goto L11;
    L13:
        boolean r52 = false;
    L15:
        if (r52 == false) goto L17;
        r1.write(r2);
        r1.write(r2);
    L33:
        if (r1.size() <= 0) goto L35;
        boolean r42 = true;
    L36:
        ArrayList r8 = new ArrayList();
    L38:
        if (r16.exhausted() == true) goto L65;
        long r12 = r16.indexOfElement(ANY_SLASH);
        if (r12 != (-1)) goto L42;
        ByteString r122 = r16.readByteString();
    L43:
        ByteString r13 = DOT_DOT;
        if (Intrinsics.areEqual(r122, r13) == true) goto L45;
        if (Intrinsics.areEqual(r122, DOT) == true) goto L38;
        if (Intrinsics.areEqual(r122, ByteString.EMPTY) == true) goto L38;
        r8.add(r122);
        goto L38
    L45:
        if (r42 == false) goto L48;
        if (r8.isEmpty() == true) goto L38;
    L48:
        if (r17 == false) goto L59;
        if (r42 == true) goto L55;
        if (r8.isEmpty() == true) goto L59;
        if (Intrinsics.areEqual(CollectionsKt.last(r8), r13) == true) goto L59;
    L55:
        if (r52 == false) goto L58;
        if (r8.size() == 1) goto L38;
    L58:
        CollectionsKt.removeLastOrNull(r8);
    L59:
        r8.add(r122);
        goto L38
    L42:
        r122 = r16.readByteString(r12);
        r16.readByte();
        goto L43
    L65:
        int r0 = r8.size();
    L66:
        if (r3 >= r0) goto L71;
        if (r3 <= 0) goto L69;
        r1.write(r2);
    L69:
        r1.write((ByteString) r8.get(r3));
        r3 = r3 + 1;
        goto L66
    L71:
        if (r1.size() != 0) goto L74;
        r1.write(DOT);
    L74:
        return new okio.Path(r1.readByteString());
    L35:
        r42 = false;
        goto L36
    L17:
        if (r4 <= 0) goto L19;
        r1.write(r2);
        goto L33
    L19:
        long r123 = r16.indexOfElement(ANY_SLASH);
        if (r2 != null) goto L26;
        if (r123 != (-1)) goto L24;
        r2 = toSlash(okio.Path.DIRECTORY_SEPARATOR);
        goto L26
    L24:
        r2 = toSlash(r16.getByte(r123));
    L26:
        if (startsWithVolumeLetterAndColon(r16, r2) == true) goto L28;
    L31:
        Unit r43 = Unit.INSTANCE;
        goto L33
    L28:
        if (r123 != 2) goto L30;
        r1.write(r16, 3);
        goto L31
    L30:
        r1.write(r16, 2);
        goto L31
    L11:
        if (Intrinsics.areEqual(r2, r5) == false) goto L13;
        r52 = true;
    L75:
        byte r53 = r16.readByte();
        if (r2 != null) goto L78;
        r2 = toSlash(r53);
    L78:
        r4 = r4 + 1;
        goto L4
    }

    private static final ByteString toSlash(byte r1) {
        if (r1 == 47) goto L11;
        if (r1 == 92) goto L7;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("not a directory separator: ", r1);
        return null;
    L7:
        return BACKSLASH;
    L11:
        return SLASH;
    }

    private static final ByteString toSlash(String r1) {
        if (Intrinsics.areEqual(r1, "/") == false) goto L7;
        return SLASH;
    L7:
        if (Intrinsics.areEqual(r1, "\\") == true) goto L9;
        Native$$ExternalSyntheticBUOutline5.m("not a directory separator: ", r1);
        return null;
    L9:
        return BACKSLASH;
    }
}
