package okhttp3.internal;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.FileSystem;
import okio.Options;
import okio.Path;
import okio.Sink;
import org.telegram.messenger.NotificationBadge;

@Metadata(d1 = {"\u0000º\u0001\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\f\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001aG\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007*\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u001a\u0010\n\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\b0\u000bj\n\u0012\u0006\b\u0000\u0012\u00020\b`\fH\u0000¢\u0006\u0002\u0010\r\u001aC\u0010\u000e\u001a\u00020\u000f*\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u001a\u0010\n\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\b0\u000bj\n\u0012\u0006\b\u0000\u0012\u00020\b`\fH\u0000¢\u0006\u0002\u0010\u0010\u001a7\u0010\u0011\u001a\u00020\u0012*\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0013\u001a\u00020\b2\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\b0\u000bj\b\u0012\u0004\u0012\u00020\b`\fH\u0000¢\u0006\u0002\u0010\u0014\u001a%\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u0007*\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0013\u001a\u00020\bH\u0000¢\u0006\u0002\u0010\u0016\u001a \u0010\u0017\u001a\u00020\u0012*\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\u00122\b\b\u0002\u0010\u0019\u001a\u00020\u0012H\u0000\u001a \u0010\u001a\u001a\u00020\u0012*\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\u00122\b\b\u0002\u0010\u0019\u001a\u00020\u0012H\u0000\u001a\u001e\u0010\u001b\u001a\u00020\b*\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\u00122\b\b\u0002\u0010\u0019\u001a\u00020\u0012\u001a&\u0010\u001c\u001a\u00020\u0012*\u00020\b2\u0006\u0010\u001d\u001a\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\u00122\b\b\u0002\u0010\u0019\u001a\u00020\u0012\u001a&\u0010\u001c\u001a\u00020\u0012*\u00020\b2\u0006\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010\u0018\u001a\u00020\u00122\b\b\u0002\u0010\u0019\u001a\u00020\u0012\u001a\f\u0010 \u001a\u00020\u0012*\u00020\bH\u0000\u001a\u0010\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\bH\u0000\u001a\f\u0010#\u001a\u00020\u0012*\u00020\u001fH\u0000\u001a\u0015\u0010$\u001a\u00020\u0012*\u00020%2\u0006\u0010&\u001a\u00020\u0012H\u0080\u0004\u001a\u0015\u0010$\u001a\u00020\u0012*\u00020'2\u0006\u0010&\u001a\u00020\u0012H\u0080\u0004\u001a\u0015\u0010$\u001a\u00020(*\u00020\u00122\u0006\u0010&\u001a\u00020(H\u0080\u0004\u001a\u0014\u0010)\u001a\u00020**\u00020+2\u0006\u0010,\u001a\u00020\u0012H\u0000\u001a\f\u0010-\u001a\u00020\u0012*\u00020.H\u0000\u001a\u001a\u0010/\u001a\u00020*2\f\u00100\u001a\b\u0012\u0004\u0012\u00020*01H\u0080\bø\u0001\u0000\u001a\u0014\u00102\u001a\u00020\u0012*\u0002032\u0006\u00104\u001a\u00020%H\u0000\u001a\u0016\u00105\u001a\u00020\u0012*\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\u0012H\u0000\u001a\u0012\u00106\u001a\u00020(*\u00020\b2\u0006\u00107\u001a\u00020(\u001a\u0016\u00108\u001a\u00020\u0012*\u0004\u0018\u00010\b2\u0006\u00107\u001a\u00020\u0012H\u0000\u001a\u000e\u00109\u001a\u00020**\u00060:j\u0002`;\u001a\u0014\u0010<\u001a\u00020\u000f*\u00020=2\u0006\u0010>\u001a\u00020?H\u0000\u001a\u0014\u0010@\u001a\u00020**\u00020=2\u0006\u0010A\u001a\u00020?H\u0000\u001a\u0014\u0010B\u001a\u00020**\u00020=2\u0006\u0010C\u001a\u00020?H\u0000\u001a%\u0010D\u001a\u00020*\"\u0004\b\u0000\u0010E*\b\u0012\u0004\u0012\u0002HE0F2\u0006\u0010G\u001a\u0002HEH\u0000¢\u0006\u0002\u0010H\u001a\"\u0010I\u001a\u00020J*\u00060Kj\u0002`L2\u0010\u0010M\u001a\f\u0012\b\u0012\u00060Kj\u0002`L0NH\u0000\u001a;\u0010O\u001a\b\u0012\u0004\u0012\u0002HP0N\"\u0004\b\u0000\u0010P*\b\u0012\u0004\u0012\u0002HP0Q2\u0017\u0010R\u001a\u0013\u0012\u0004\u0012\u0002HP\u0012\u0004\u0012\u00020\u000f0S¢\u0006\u0002\bTH\u0080\bø\u0001\u0000\u001a \u0010V\u001a\u00020*2\u0006\u0010W\u001a\u00020(2\u0006\u0010X\u001a\u00020(2\u0006\u0010Y\u001a\u00020(H\u0000\u001a0\u0010Z\u001a\b\u0012\u0004\u0012\u0002HP0N\"\u0004\b\u0000\u0010P2\f\u0010[\u001a\b\u0012\u0004\u0012\u0002HP0Q2\f\u00104\u001a\b\u0012\u0004\u0012\u0002HP0QH\u0000\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u000e\u0010U\u001a\u00020\bX\u0080T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\\"}, d2 = {"EMPTY_BYTE_ARRAY", _UrlKt.FRAGMENT_ENCODE_SET, "UNICODE_BOMS", "Lokio/Options;", "getUNICODE_BOMS", "()Lokio/Options;", "intersect", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "other", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "([Ljava/lang/String;[Ljava/lang/String;Ljava/util/Comparator;)[Ljava/lang/String;", "hasIntersection", _UrlKt.FRAGMENT_ENCODE_SET, "([Ljava/lang/String;[Ljava/lang/String;Ljava/util/Comparator;)Z", "indexOf", _UrlKt.FRAGMENT_ENCODE_SET, "value", "([Ljava/lang/String;Ljava/lang/String;Ljava/util/Comparator;)I", "concat", "([Ljava/lang/String;Ljava/lang/String;)[Ljava/lang/String;", "indexOfFirstNonAsciiWhitespace", "startIndex", "endIndex", "indexOfLastNonAsciiWhitespace", "trimSubstring", "delimiterOffset", "delimiters", "delimiter", _UrlKt.FRAGMENT_ENCODE_SET, "indexOfControlOrNonAscii", "isSensitiveHeader", "name", "parseHexDigit", "and", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.MASK, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "writeMedium", _UrlKt.FRAGMENT_ENCODE_SET, "Lokio/BufferedSink;", "medium", "readMedium", "Lokio/BufferedSource;", "ignoreIoExceptions", "block", "Lkotlin/Function0;", "skipAll", "Lokio/Buffer;", "b", "indexOfNonWhitespace", "toLongOrDefault", "defaultValue", "toNonNegativeInt", "closeQuietly", "Ljava/io/Closeable;", "Lokio/Closeable;", "isCivilized", "Lokio/FileSystem;", "file", "Lokio/Path;", "deleteIfExists", "path", "deleteContents", "directory", "addIfAbsent", "E", _UrlKt.FRAGMENT_ENCODE_SET, "element", "(Ljava/util/List;Ljava/lang/Object;)V", "withSuppressed", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/lang/Exception;", "Lkotlin/Exception;", "suppressed", _UrlKt.FRAGMENT_ENCODE_SET, "filterList", "T", _UrlKt.FRAGMENT_ENCODE_SET, "predicate", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "USER_AGENT", "checkOffsetAndCount", "arrayLength", "offset", NotificationBadge.NewHtcHomeBadger.COUNT, "interleave", "a", "okhttp"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\n-UtilCommon.kt\nKotlin\n*S Kotlin\n*F\n+ 1 -UtilCommon.kt\nokhttp3/internal/_UtilCommonKt\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 Okio.kt\nokio/Okio__OkioKt\n*L\n1#1,400:1\n37#2,2:401\n1772#3,6:403\n72#4:409\n58#4,22:410\n*S KotlinDebug\n*F\n+ 1 -UtilCommon.kt\nokhttp3/internal/_UtilCommonKt\n*L\n68#1:401,2\n97#1:403,6\n303#1:409\n303#1:410,22\n*E\n"})
/* loaded from: classes.dex */
public final class _UtilCommonKt {

    @JvmField
    public static final byte[] EMPTY_BYTE_ARRAY = null;
    private static final Options UNICODE_BOMS = null;
    public static final String USER_AGENT = "okhttp/5.3.2";

    static {
        EMPTY_BYTE_ARRAY = new byte[0];
        Options.Companion r0 = Options.Companion;
        ByteString.Companion r1 = ByteString.Companion;
        UNICODE_BOMS = r0.of(new ByteString[]{r1.decodeHex("efbbbf"), r1.decodeHex("feff"), r1.decodeHex("fffe0000"), r1.decodeHex("fffe"), r1.decodeHex("0000feff")});
    }

    public static final <E> void addIfAbsent(List<E> r1, E r2) {
        if (r1.contains(r2) == true) goto L6;
        r1.add(r2);
        return;
    }

    public static final int and(byte r0, int r1) {
        return r0 & r1;
    }

    public static final int and(short r0, int r1) {
        return r0 & r1;
    }

    public static final long and(int r2, long r3) {
        return r2 & r3;
    }

    public static final void checkOffsetAndCount(long r4, long r6, long r8) {
        if ((r6 | r8) < 0) goto L10;
        if (r6 > r4) goto L10;
        if ((r4 - r6) < r8) goto L10;
        return;
    L10:
        throw new ArrayIndexOutOfBoundsException("length=" + r4 + ", offset=" + r6 + ", count=" + r6);
    }

    public static final void closeQuietly(Closeable r0) {
        r0.close();     // Catch: Exception -> L6 RuntimeException -> L4
        return;
    L4:
        e = move-exception;
        throw e;
    }

    public static final String[] concat(String[] r1, String r2) {
        String[] r12 = (String[]) Arrays.copyOf(r1, r1.length + 1);
        r12[ArraysKt.getLastIndex(r12)] = r2;
        return r12;
    }

    public static final void deleteContents(FileSystem r3, Path r4) {
        Iterator<Path> r42 = r3.list(r4).iterator();
        IOException r0 = null;
    L5:
        if (r42.hasNext() == false) goto L16;
        Path r1 = r42.next();
        if (r3.metadata(r1).isDirectory() == false) goto L12;
        deleteContents(r3, r1);     // Catch: IOException -> L10
    L12:
        r3.delete(r1);     // Catch: IOException -> L10
    L10:
        e = move-exception;
        if (r0 != null) goto L5;
        r0 = e;
        goto L5
    L16:
        if (r0 != null) goto L18;
        return;
    L18:
        throw r0;
    }

    public static final void deleteIfExists(FileSystem r0, Path r1) {
        r0.delete(r1);     // Catch: FileNotFoundException -> L4
        return;
    }

    public static final int delimiterOffset(String r1, char r2, int r3, int r4) {
    L2:
        if (r3 >= r4) goto L7;
        if (r1.charAt(r3) == r2) goto L5;
        r3 = r3 + 1;
        goto L2
    L5:
        return r3;
    L7:
        return r4;
    }

    public static final int delimiterOffset(String r4, String r5, int r6, int r7) {
    L2:
        if (r6 >= r7) goto L7;
        if (StringsKt.contains$default(r5, r4.charAt(r6), false, 2, null) == true) goto L5;
        r6 = r6 + 1;
        goto L2
    L5:
        return r6;
    L7:
        return r7;
    }

    public static /* synthetic */ int delimiterOffset$default(String r0, char r1, int r2, int r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = 0;
    L6:
        if ((r4 & 4) == 0) goto L9;
        r3 = r0.length();
    L9:
        return delimiterOffset(r0, r1, r2, r3);
    }

    public static /* synthetic */ int delimiterOffset$default(String r0, String r1, int r2, int r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = 0;
    L6:
        if ((r4 & 4) == 0) goto L9;
        r3 = r0.length();
    L9:
        return delimiterOffset(r0, r1, r2, r3);
    }

    public static final <T> List<T> filterList(Iterable<? extends T> r3, Function1<? super T, Boolean> r4) {
        List<T> r0 = CollectionsKt.emptyList();
        Iterator<? extends T> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L11;
        T r1 = r32.next();
        if (r4.invoke(r1).booleanValue() == false) goto L4;
        if (r0.isEmpty() == false) goto L10;
        r0 = new ArrayList();
    L10:
        TypeIntrinsics.asMutableList(r0).add(r1);
        goto L4
    L11:
        return r0;
    }

    public static final Options getUNICODE_BOMS() {
        return UNICODE_BOMS;
    }

    public static final boolean hasIntersection(String[] r7, String[] r8, Comparator<? super String> r9) {
        if (r7.length == 0) goto L19;
        if (r8 == null) goto L19;
        if (r8.length == 0) goto L19;
        int r0 = r7.length;
        int r2 = 0;
    L10:
        if (r2 >= r0) goto L19;
        String r3 = r7[r2];
        int r4 = r8.length;
        int r5 = 0;
    L12:
        if (r5 >= r4) goto L18;
        if (r9.compare(r3, r8[r5]) == 0) goto L15;
        r5 = r5 + 1;
        goto L12
    L15:
        return true;
    L18:
        r2 = r2 + 1;
    L19:
        return false;
    }

    public static final void ignoreIoExceptions(Function0<Unit> r0) {
        r0.invoke();     // Catch: IOException -> L4
        return;
    }

    public static final int indexOf(String[] r3, String r4, Comparator<String> r5) {
        int r0 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        if (r5.compare(r3[r1], r4) == 0) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return r1;
    L8:
        return -1;
    }

    public static final int indexOfControlOrNonAscii(String r4) {
        int r0 = r4.length();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L11;
        char r2 = r4.charAt(r1);
        if (Intrinsics.compare(r2, 31) <= 0) goto L10;
        if (Intrinsics.compare(r2, 127) >= 0) goto L10;
        r1 = r1 + 1;
    L10:
        return r1;
    L11:
        return -1;
    }

    public static final int indexOfFirstNonAsciiWhitespace(String r2, int r3, int r4) {
    L2:
        if (r3 >= r4) goto L15;
        char r0 = r2.charAt(r3);
        if (r0 == '\t') goto L14;
        if (r0 == '\n') goto L14;
        if (r0 == '\f') goto L14;
        if (r0 == '\r') goto L14;
        if (r0 == ' ') goto L14;
        return r3;
    L14:
        r3 = r3 + 1;
        goto L2
    L15:
        return r4;
    }

    public static /* synthetic */ int indexOfFirstNonAsciiWhitespace$default(String r0, int r1, int r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r1 = 0;
    L6:
        if ((r3 & 2) == 0) goto L9;
        r2 = r0.length();
    L9:
        return indexOfFirstNonAsciiWhitespace(r0, r1, r2);
    }

    public static final int indexOfLastNonAsciiWhitespace(String r2, int r3, int r4) {
        int r42 = r4 - 1;
        if (r3 > r42) goto L18;
    L4:
        char r0 = r2.charAt(r42);
        if (r0 != '\t') goto L7;
    L16:
        if (r42 == r3) goto L18;
        r42 = r42 - 1;
        goto L4
    L7:
        if (r0 == '\n') goto L16;
        if (r0 == '\f') goto L16;
        if (r0 == '\r') goto L16;
        if (r0 == ' ') goto L16;
        return r42 + 1;
    L18:
        return r3;
    }

    public static /* synthetic */ int indexOfLastNonAsciiWhitespace$default(String r0, int r1, int r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r1 = 0;
    L6:
        if ((r3 & 2) == 0) goto L9;
        r2 = r0.length();
    L9:
        return indexOfLastNonAsciiWhitespace(r0, r1, r2);
    }

    public static final int indexOfNonWhitespace(String r3, int r4) {
        int r0 = r3.length();
    L3:
        if (r4 >= r0) goto L11;
        char r1 = r3.charAt(r4);
        if (r1 == ' ') goto L9;
        if (r1 == '\t') goto L9;
        return r4;
    L9:
        r4 = r4 + 1;
        goto L3
    L11:
        return r3.length();
    }

    public static /* synthetic */ int indexOfNonWhitespace$default(String r0, int r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = 0;
    L6:
        return indexOfNonWhitespace(r0, r1);
    }

    public static final <T> List<T> interleave(Iterable<? extends T> r2, Iterable<? extends T> r3) {
        Iterator<? extends T> r22 = r2.iterator();
        Iterator<? extends T> r32 = r3.iterator();
        List r0 = CollectionsKt.createListBuilder();
    L4:
        if (r22.hasNext() == true) goto L11;
        if (r32.hasNext() == true) goto L11;
        return CollectionsKt.build(r0);
    L11:
        if (r22.hasNext() == false) goto L14;
        r0.add(r22.next());
    L14:
        if (r32.hasNext() == false) goto L4;
        r0.add(r32.next());
        goto L4
    }

    public static final String[] intersect(String[] r8, String[] r9, Comparator<? super String> r10) {
        ArrayList r0 = new ArrayList();
        int r1 = r8.length;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L12;
        String r4 = r8[r3];
        int r5 = r9.length;
        int r6 = 0;
    L5:
        if (r6 >= r5) goto L10;
        if (r10.compare(r4, r9[r6]) == 0) goto L8;
        r6 = r6 + 1;
        goto L5
    L8:
        r0.add(r4);
    L10:
        r3 = r3 + 1;
        goto L3
    L12:
        return (String[]) r0.toArray(new String[0]);
    }

    public static final boolean isCivilized(FileSystem r2, Path r3) {
        Sink r0 = r2.sink(r3);
        r2.delete(r3);     // Catch: Throwable -> L8 IOException -> L10
    L5:
        if (r0 != null) goto L34;
    L7:
        return true;
    L34:
        r0.close();     // Catch: Throwable -> L27
    L10:
        Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L8
        if (r0 != null) goto L30;
    L16:
        Throwable th = null;
    L23:
        if (th != null) goto L26;
        r2.delete(r3);
        return false;
    L26:
        throw th;
    L30:
        r0.close();     // Catch: Throwable -> L14
    L14:
        th = th;
    L8:
        th = move-exception;
        if (r0 != null) goto L28;
    L22:
        th = th;
        goto L23
    L28:
        r0.close();     // Catch: Throwable -> L20
    L20:
        th = move-exception;
        ExceptionsKt.addSuppressed(th, th);
        goto L22
    }

    public static final boolean isSensitiveHeader(String r2) {
        if (StringsKt.equals(r2, "Authorization", true) == false) goto L5;
    L13:
        return true;
    L5:
        if (StringsKt.equals(r2, "Cookie", true) == true) goto L13;
        if (StringsKt.equals(r2, "Proxy-Authorization", true) == true) goto L13;
        if (StringsKt.equals(r2, "Set-Cookie", true) == true) goto L13;
        return false;
    }

    public static final int parseHexDigit(char r2) {
        if ('0' > r2) goto L9;
        if (r2 >= ':') goto L9;
        return r2 - '0';
    L9:
        if ('a' > r2) goto L15;
        if (r2 >= 'g') goto L15;
        return r2 - 'W';
    L15:
        if ('A' <= r2) goto L17;
        return -1;
    L17:
        if (r2 < 'G') goto L19;
        return -1;
    L19:
        return r2 - '7';
    }

    public static final int readMedium(BufferedSource r3) {
        int r0 = (and(r3.readByte(), 255) << 16) | (and(r3.readByte(), 255) << 8);
        return and(r3.readByte(), 255) | r0;
    }

    public static final int skipAll(Buffer r3, byte r4) {
        int r0 = 0;
    L4:
        if (r3.exhausted() == true) goto L8;
        if (r3.getByte(0) != r4) goto L8;
        r0 = r0 + 1;
        r3.readByte();
    L8:
        return r0;
    }

    public static final long toLongOrDefault(String r0, long r1) {
        return Long.parseLong(r0);
    L4:
        return r1;
    }

    public static final int toNonNegativeInt(String r2, int r3) {
        if (r2 != null) goto L16;
    L14:
        return r3;
    L16:
        long r22 = Long.parseLong(r2);     // Catch: NumberFormatException -> L15
        if (r22 <= 2147483647L) goto L9;
        return Integer.MAX_VALUE;
    L9:
        if (r22 >= 0) goto L13;
        return 0;
    L13:
        return (int) r22;
    }

    public static final String trimSubstring(String r0, int r1, int r2) {
        int r12 = indexOfFirstNonAsciiWhitespace(r0, r1, r2);
        return r0.substring(r12, indexOfLastNonAsciiWhitespace(r0, r12, r2));
    }

    public static /* synthetic */ String trimSubstring$default(String r0, int r1, int r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r1 = 0;
    L6:
        if ((r3 & 2) == 0) goto L9;
        r2 = r0.length();
    L9:
        return trimSubstring(r0, r1, r2);
    }

    public static final Throwable withSuppressed(Exception r1, List<? extends Exception> r2) {
        Iterator<? extends Exception> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        ExceptionsKt.addSuppressed(r1, r22.next());
        goto L4
    L6:
        return r1;
    }

    public static final void writeMedium(BufferedSink r1, int r2) {
        r1.writeByte((r2 >>> 16) & 255);
        r1.writeByte((r2 >>> 8) & 255);
        r1.writeByte(r2 & 255);
    }
}
