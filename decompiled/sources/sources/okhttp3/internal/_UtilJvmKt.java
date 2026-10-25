package okhttp3.internal;

import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.MediaStatus;
import j$.util.DesugarTimeZone;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.reflect.Field;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlin.time.Duration;
import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.EventListener;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.Response$Builder$$ExternalSyntheticBUOutline0;
import okhttp3.internal.http2.Header;
import okhttp3.internal.url._UrlKt;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;

@Metadata(d1 = {"\u0000´\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010$\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\u001a\u0016\u0010\b\u001a\u00020\u0005*\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0007H\u0000\u001a)\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0012\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000e0\r\"\u00020\u000eH\u0000¢\u0006\u0002\u0010\u000f\u001a\u0014\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0011H\u0000\u001a \u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0000\u001a\u001f\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u001aH\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u0012\u0010\u001d\u001a\u00020\u001e*\b\u0012\u0004\u0012\u00020 0\u001fH\u0000\u001a\u0012\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u001f*\u00020\u001eH\u0000\u001a\u0014\u0010\"\u001a\u00020\u0007*\u00020\t2\u0006\u0010#\u001a\u00020\tH\u0000\u001a\f\u0010$\u001a\u00020%*\u00020&H\u0000\u001a\u001c\u0010'\u001a\u00020\u0007*\u00020(2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010)\u001a\u00020\u0019H\u0000\u001a\f\u0010'\u001a\u00020**\u00020\u0012H\u0000\u001a\u001c\u0010+\u001a\u00020\u0007*\u00020(2\u0006\u0010,\u001a\u00020\u00152\u0006\u0010)\u001a\u00020\u0019H\u0000\u001a\u0014\u0010-\u001a\u00020\u0007*\u00020.2\u0006\u0010/\u001a\u00020\u0012H\u0000\u001a\"\u00100\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020\u00052\f\u00101\u001a\b\u0012\u0004\u0012\u00020*02H\u0080\bø\u0001\u0000\u001a\f\u00103\u001a\u00020\u0017*\u000204H\u0000\u001a\u001f\u00105\u001a\b\u0012\u0004\u0012\u0002H60\u001f\"\u0004\b\u0000\u00106*\b\u0012\u0004\u0012\u0002H60\u001fH\u0080\b\u001a\u001f\u00105\u001a\b\u0012\u0004\u0012\u0002H607\"\u0004\b\u0000\u00106*\b\u0012\u0004\u0012\u0002H607H\u0080\b\u001a1\u00105\u001a\u000e\u0012\u0004\u0012\u0002H9\u0012\u0004\u0012\u0002H:08\"\u0004\b\u0000\u00109\"\u0004\b\u0001\u0010:*\u000e\u0012\u0004\u0012\u0002H9\u0012\u0004\u0012\u0002H:08H\u0080\b\u001a\u001e\u0010;\u001a\b\u0012\u0004\u0012\u0002H60\u001f\"\u0004\b\u0000\u00106*\b\u0012\u0004\u0012\u0002H60\u001fH\u0000\u001a-\u0010<\u001a\b\u0012\u0004\u0012\u0002H60\u001f\"\u0004\b\u0000\u001062\u0012\u0010=\u001a\n\u0012\u0006\b\u0001\u0012\u0002H60\r\"\u0002H6H\u0001¢\u0006\u0002\u0010>\u001a'\u0010;\u001a\b\u0012\u0004\u0012\u0002H60\u001f\"\u0004\b\u0000\u00106*\f\u0012\u0006\b\u0001\u0012\u0002H6\u0018\u00010\rH\u0000¢\u0006\u0002\u0010>\u001a\f\u0010?\u001a\u00020**\u00020.H\u0000\u001a\f\u0010?\u001a\u00020**\u00020@H\u0000\u001a\f\u0010A\u001a\u00020\u0005*\u00020\u0017H\u0000\u001a\f\u0010A\u001a\u00020\u0005*\u00020\u0015H\u0000\u001a3\u0010B\u001a\u0004\u0018\u0001H6\"\u0004\b\u0000\u001062\u0006\u0010C\u001a\u00020\u000e2\f\u0010D\u001a\b\u0012\u0004\u0012\u0002H60E2\u0006\u0010F\u001a\u00020\u0005H\u0000¢\u0006\u0002\u0010G\u001a\f\u0010I\u001a\u00020**\u00020JH\u0000\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010H\u001a\u00020\u00078\u0000X\u0081\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010K\u001a\u00020\u00058\u0000X\u0081\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006L"}, d2 = {"UTC", "Ljava/util/TimeZone;", "threadFactory", "Ljava/util/concurrent/ThreadFactory;", "name", _UrlKt.FRAGMENT_ENCODE_SET, "daemon", _UrlKt.FRAGMENT_ENCODE_SET, "toHostHeader", "Lokhttp3/HttpUrl;", "includeDefaultPort", "format", "args", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "readBomAsCharset", "Ljava/nio/charset/Charset;", "Lokio/BufferedSource;", PluginsConstants.Settings.DEFAULT, "checkDuration", _UrlKt.FRAGMENT_ENCODE_SET, "duration", _UrlKt.FRAGMENT_ENCODE_SET, "unit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/Duration;", "checkDuration-HG0u8IE", "(Ljava/lang/String;J)I", "toHeaders", "Lokhttp3/Headers;", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/internal/http2/Header;", "toHeaderList", "canReuseConnectionFor", "other", "asFactory", "Lokhttp3/EventListener$Factory;", "Lokhttp3/EventListener;", "skipAll", "Lokio/Source;", "timeUnit", _UrlKt.FRAGMENT_ENCODE_SET, "discard", "timeout", "isHealthy", "Ljava/net/Socket;", "source", "threadName", "block", "Lkotlin/Function0;", "headersContentLength", "Lokhttp3/Response;", "unmodifiable", "T", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "K", "V", "toImmutableList", "immutableListOf", "elements", "([Ljava/lang/Object;)Ljava/util/List;", "closeQuietly", "Ljava/net/ServerSocket;", "toHexString", "readFieldOrNull", "instance", "fieldType", "Ljava/lang/Class;", "fieldName", "(Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;", "assertionsEnabled", "assertLockNotHeld", "Lokhttp3/Dispatcher;", "okHttpName", "okhttp"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\n-UtilJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 -UtilJvm.kt\nokhttp3/internal/_UtilJvmKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,355:1\n242#1:361\n242#1:362\n1#2:356\n1563#3:357\n1634#3,3:358\n*S KotlinDebug\n*F\n+ 1 -UtilJvm.kt\nokhttp3/internal/_UtilJvmKt\n*L\n260#1:361\n272#1:362\n131#1:357\n131#1:358,3\n*E\n"})
/* loaded from: classes.dex */
public final class _UtilJvmKt {

    @JvmField
    public static final TimeZone UTC = null;

    @JvmField
    public static final boolean assertionsEnabled = false;

    @JvmField
    public static final String okHttpName = null;

    /* renamed from: $r8$lambda$-_J-6QpgacuLmRBBtfmidf27cGU, reason: not valid java name */
    public static Thread m3744$r8$lambda$_J6QpgacuLmRBBtfmidf27cGU(String r1, boolean r2, Runnable r3) {
        Thread r0 = new Thread(r3, r1);
        r0.setDaemon(r2);
        return r0;
    }

    public static EventListener $r8$lambda$K99WxJwzokotbMqA93v0Qgw0Pe0(EventListener r0, Call r1) {
        return r0;
    }

    static {
        UTC = DesugarTimeZone.getTimeZone("GMT");
        assertionsEnabled = false;
        okHttpName = StringsKt.removeSuffix(StringsKt.removePrefix(OkHttpClient.class.getName(), "okhttp3."), "Client");
    }

    public static final EventListener.Factory asFactory(EventListener r1) {
        return new _UtilJvmKt$$ExternalSyntheticLambda2(r1);
    }

    public static final void assertLockNotHeld(Dispatcher r2) {
        if (assertionsEnabled == true) goto L5;
        return;
    L5:
        if (Thread.holdsLock(r2) == false) goto L10;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST NOT hold lock on ", r2);
        return;
    }

    public static final boolean canReuseConnectionFor(HttpUrl r2, HttpUrl r3) {
        if (Intrinsics.areEqual(r2.host(), r3.host()) == true) goto L5;
        return false;
    L5:
        if (r2.port() == r3.port()) goto L7;
        return false;
    L7:
        if (Intrinsics.areEqual(r2.scheme(), r3.scheme()) == false) goto L13;
        return true;
    L13:
        return false;
    }

    public static final int checkDuration(String r5, long r6, TimeUnit r8) {
        if (r6 < 0) goto L16;
        long r62 = r8.toMillis(r6);
        if (r62 <= 2147483647L) goto L7;
        Response$Builder$$ExternalSyntheticBUOutline0.m(r5, " too large");
        return 0;
    L7:
        if (r62 != 0) goto L14;
        if (r6 <= 0) goto L14;
        Response$Builder$$ExternalSyntheticBUOutline0.m(r5, " too small");
        return 0;
    L14:
        return (int) r62;
    L16:
        _UtilJvmKt$$ExternalSyntheticBUOutline0.m(r5, " < 0");
        return 0;
    }

    /* renamed from: checkDuration-HG0u8IE, reason: not valid java name */
    public static final int m3745checkDurationHG0u8IE(String r4, long r5) {
        if (Duration.m3401isNegativeimpl(r5) == true) goto L17;
        long r0 = Duration.m3386getInWholeMillisecondsimpl(r5);
        if (r0 <= 2147483647L) goto L7;
        Response$Builder$$ExternalSyntheticBUOutline0.m(r4, " too large");
        return 0;
    L7:
        if (r0 != 0) goto L15;
        if (Duration.m3402isPositiveimpl(r5) == false) goto L15;
        Response$Builder$$ExternalSyntheticBUOutline0.m(r4, " too small");
        return 0;
    L15:
        return (int) r0;
    L17:
        _UtilJvmKt$$ExternalSyntheticBUOutline0.m(r4, " < 0");
        return 0;
    }

    public static final void closeQuietly(ServerSocket r0) {
        r0.close();     // Catch: Exception -> L6 RuntimeException -> L4
        return;
    L4:
        e = move-exception;
        throw e;
    }

    public static final void closeQuietly(Socket r2) {
        r2.close();     // Catch: Exception -> L11 RuntimeException -> L4 AssertionError -> L9
        return;
    L9:
        e = move-exception;
        throw e;
    L4:
        e = move-exception;
        if (Intrinsics.areEqual(e.getMessage(), "bio == null") == false) goto L8;
        return;
    L8:
        throw e;
    }

    public static final boolean discard(Source r0, int r1, TimeUnit r2) {
        return skipAll(r0, r1, r2);
    L4:
        return false;
    }

    public static final String format(String r2, Object... r3) {
        StringCompanionObject r0 = StringCompanionObject.INSTANCE;
        Locale r02 = Locale.US;
        Object[] r32 = Arrays.copyOf(r3, r3.length);
        return String.format(r02, r2, Arrays.copyOf(r32, r32.length));
    }

    public static final long headersContentLength(Response r2) {
        String r22 = r2.headers().get("Content-Length");
        if (r22 != null) goto L5;
        return -1;
    L5:
        return _UtilCommonKt.toLongOrDefault(r22, -1);
    }

    @SafeVarargs
    public static final <T> List<T> immutableListOf(T... r0) {
        return toImmutableList(r0);
    }

    public static final boolean isHealthy(Socket r2, BufferedSource r3) {
        int r1 = r2.getSoTimeout();     // Catch: IOException -> L11 SocketTimeoutException -> L13
        r2.setSoTimeout(1);     // Catch: Throwable -> L8
        boolean r32 = !r3.exhausted();
        r2.setSoTimeout(r1);     // Catch: IOException -> L11 SocketTimeoutException -> L13
        return r32;
    L8:
        th = move-exception;
        r2.setSoTimeout(r1);     // Catch: IOException -> L11 SocketTimeoutException -> L13
        throw th;     // Catch: IOException -> L11 SocketTimeoutException -> L13
    L11:
        return false;
    L17:
        return true;
    }

    public static final Charset readBomAsCharset(BufferedSource r1, Charset r2) {
        int r12 = r1.select(_UtilCommonKt.getUNICODE_BOMS());
        if (r12 == (-1)) goto L25;
        if (r12 == 0) goto L24;
        if (r12 == 1) goto L22;
        if (r12 == 2) goto L20;
        if (r12 == 3) goto L18;
        if (r12 == 4) goto L14;
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return null;
    L14:
        return Charsets.INSTANCE.UTF32_BE();
    L18:
        return Charsets.UTF_16LE;
    L20:
        return Charsets.INSTANCE.UTF32_LE();
    L22:
        return Charsets.UTF_16BE;
    L24:
        return Charsets.UTF_8;
    L25:
        return r2;
    }

    public static final <T> T readFieldOrNull(Object r4, Class<T> r5, String r6) {
        Class<?> r0 = r4.getClass();
    L3:
        T r3 = null;
        if (Intrinsics.areEqual(r0, Object.class) == true) goto L12;
        Field r1 = r0.getDeclaredField(r6);     // Catch: NoSuchFieldException -> L10
        r1.setAccessible(true);     // Catch: NoSuchFieldException -> L10
        Object r12 = r1.get(r4);     // Catch: NoSuchFieldException -> L10
        if (r5.isInstance(r12) == false) goto L9;
        r3 = r5.cast(r12);     // Catch: NoSuchFieldException -> L10
    L9:
        return r3;
    L10:
        r0 = r0.getSuperclass();
        goto L3
    L12:
        if (Intrinsics.areEqual(r6, "delegate") == true) goto L17;
        Object r42 = readFieldOrNull(r4, Object.class, "delegate");
        if (r42 == null) goto L17;
        return (T) readFieldOrNull(r42, r5, r6);
    L17:
        return null;
    }

    public static final void skipAll(BufferedSource r2) {
    L3:
        if (r2.exhausted() == true) goto L5;
        r2.skip(r2.getBuffer().size());
        goto L3
    }

    public static final boolean skipAll(Source r11, int r12, TimeUnit r13) {
        long r0 = System.nanoTime();
        if (r11.timeout().hasDeadline() == false) goto L5;
        long r5 = r11.timeout().deadlineNanoTime() - r0;
    L6:
        r11.timeout().deadlineNanoTime(Math.min(r5, r13.toNanos(r12)) + r0);
        Buffer r122 = new Buffer();     // Catch: Throwable -> L12 InterruptedIOException -> L25
    L8:
        if (r11.read(r122, MediaStatus.COMMAND_PLAYBACK_RATE) == (-1)) goto L15;
        r122.clear();     // Catch: Throwable -> L12 InterruptedIOException -> L25
        goto L8
    L15:
        if (r5 != LongCompanionObject.MAX_VALUE) goto L18;
        r11.timeout().clearDeadline();
        return true;
    L18:
        r11.timeout().deadlineNanoTime(r0 + r5);
        return true;
    L12:
        th = move-exception;
        if (r5 != LongCompanionObject.MAX_VALUE) goto L23;
        r11.timeout().clearDeadline();
    L24:
        throw th;
    L23:
        r11.timeout().deadlineNanoTime(r0 + r5);
    L26:
        if (r5 != LongCompanionObject.MAX_VALUE) goto L28;
        r11.timeout().clearDeadline();
        return false;
    L28:
        r11.timeout().deadlineNanoTime(r0 + r5);
        return false;
    L5:
        r5 = Long.MAX_VALUE;
        goto L6
    }

    public static final ThreadFactory threadFactory(String r1, boolean r2) {
        return new _UtilJvmKt$$ExternalSyntheticLambda3(r1, r2);
    }

    public static final void threadName(String r2, Function0<Unit> r3) {
        Thread r0 = Thread.currentThread();
        String r1 = r0.getName();
        r0.setName(r2);
        r3.invoke();     // Catch: Throwable -> L6
        InlineMarker.finallyStart(1);
        r0.setName(r1);
        InlineMarker.finallyEnd(1);
        return;
    L6:
        th = move-exception;
        InlineMarker.finallyStart(1);
        r0.setName(r1);
        InlineMarker.finallyEnd(1);
        throw th;
    }

    public static final List<Header> toHeaderList(Headers r5) {
        IntRange r0 = RangesKt.until(0, r5.size());
        ArrayList r1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r0, 10));
        Iterator<Integer> r02 = r0.iterator();
    L4:
        if (r02.hasNext() == false) goto L6;
        int r2 = ((IntIterator) r02).nextInt();
        r1.add(new Header(r5.name(r2), r5.value(r2)));
        goto L4
    L6:
        return r1;
    }

    public static final Headers toHeaders(List<Header> r3) {
        Headers.Builder r0 = new Headers.Builder();
        Iterator<Header> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L7;
        Header r1 = r32.next();
        ByteString r2 = r1.component1();
        ByteString r12 = r1.component2();
        r0.addLenient$okhttp(r2.utf8(), r12.utf8());
        goto L4
    L7:
        return r0.build();
    }

    public static final String toHexString(int r0) {
        return Integer.toHexString(r0);
    }

    public static final String toHexString(long r0) {
        return Long.toHexString(r0);
    }

    public static final String toHostHeader(HttpUrl r5, boolean r6) {
        if (StringsKt.contains$default(r5.host(), ":", false, 2, null) == false) goto L5;
        String r0 = "[" + r5.host() + ']';
    L6:
        if (r6 == true) goto L12;
        if (r5.port() != HttpUrl.Companion.defaultPort(r5.scheme())) goto L12;
        return r0;
    L12:
        return r0 + ':' + r5.port();
    L5:
        r0 = r5.host();
        goto L6
    }

    public static /* synthetic */ String toHostHeader$default(HttpUrl r0, boolean r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = false;
    L6:
        return toHostHeader(r0, r1);
    }

    public static final <T> List<T> toImmutableList(List<? extends T> r2) {
        if (r2.isEmpty() == false) goto L7;
        return CollectionsKt.emptyList();
    L7:
        if (r2.size() != 1) goto L11;
        return Collections.singletonList(r2.get(0));
    L11:
        return Collections.unmodifiableList(ArraysKt.asList(r2.toArray()));
    }

    public static final <T> List<T> toImmutableList(T[] r2) {
        if (r2 == null) goto L13;
        if (r2.length == 0) goto L13;
        if (r2.length != 1) goto L11;
        return Collections.singletonList(r2[0]);
    L11:
        return Collections.unmodifiableList(ArraysKt.asList((Object[]) r2.clone()));
    L13:
        return CollectionsKt.emptyList();
    }

    public static final <T> List<T> unmodifiable(List<? extends T> r0) {
        return Collections.unmodifiableList(r0);
    }

    public static final <K, V> Map<K, V> unmodifiable(Map<K, ? extends V> r0) {
        return Collections.unmodifiableMap(r0);
    }

    public static final <T> Set<T> unmodifiable(Set<? extends T> r0) {
        return Collections.unmodifiableSet(r0);
    }
}
