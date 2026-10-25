package kotlin.internal.jdk8;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.internal.jdk7.JDK7PlatformImplementations;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.Random;
import kotlin.random.jdk8.PlatformThreadLocalRandom;
import kotlin.ranges.IntRange;
import kotlin.text.MatchGroup;
import kotlin.time.Clock;
import kotlin.time.Instant;
import kotlin.time.jdk8.InstantConversionsJDK8Kt;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001:\u0001\u0012B\t\bF¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0082\u0080\u0004J\u001c\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0096\u0080\u0004J\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004J\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004¨\u0006\u0013"}, d2 = {"Lkotlin/internal/jdk8/JDK8PlatformImplementations;", "Lkotlin/internal/jdk7/JDK7PlatformImplementations;", "<init>", "()V", "sdkIsNullOrAtLeast", _UrlKt.FRAGMENT_ENCODE_SET, "version", _UrlKt.FRAGMENT_ENCODE_SET, "getMatchResultNamedGroup", "Lkotlin/text/MatchGroup;", "matchResult", "Ljava/util/regex/MatchResult;", "name", _UrlKt.FRAGMENT_ENCODE_SET, "defaultPlatformRandom", "Lkotlin/random/Random;", "getSystemClock", "Lkotlin/time/Clock;", "ReflectSdkVersion", "kotlin-stdlib-jdk8"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public class JDK8PlatformImplementations extends JDK7PlatformImplementations {

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0084\b¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/internal/jdk8/JDK8PlatformImplementations$ReflectSdkVersion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "sdkVersion", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/lang/Integer;", "kotlin-stdlib-jdk8"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nJDK8PlatformImplementations.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JDK8PlatformImplementations.kt\nkotlin/internal/jdk8/JDK8PlatformImplementations$ReflectSdkVersion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,75:1\n1#2:76\n*E\n"})
    public static final class ReflectSdkVersion {
        public static final ReflectSdkVersion INSTANCE = null;

        @JvmField
        public static final Integer sdkVersion = null;

        static {
            INSTANCE = new ReflectSdkVersion();
            Integer r0 = null;
            Object r1 = Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);     // Catch: Throwable -> L14
            if ((r1 instanceof Integer) == false) goto L7;
            Integer r12 = (Integer) r1;     // Catch: Throwable -> L14
        L8:
            if (r12 != null) goto L10;
        L12:
            sdkVersion = r0;
            return;
        L10:
            if (r12.intValue() <= 0) goto L12;
            r0 = r12;
        L7:
            r12 = null;
            goto L8
        }

        private ReflectSdkVersion() {
        }
    }

    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u00020\u0003H\u0096\u0080\u0004¨\u0006\u0004"}, d2 = {"kotlin/internal/jdk8/JDK8PlatformImplementations$getSystemClock$1", "Lkotlin/time/Clock;", "now", "Lkotlin/time/Instant;", "kotlin-stdlib-jdk8"}, k = 1, mv = {2, 3, 0}, xi = 48)
    /* renamed from: kotlin.internal.jdk8.JDK8PlatformImplementations$getSystemClock$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass1 implements Clock {
        public AnonymousClass1() {
        }

        @Override // kotlin.time.Clock
        public Instant now() {
            return InstantConversionsJDK8Kt.toKotlinInstant(j$.time.Instant.now());
        }
    }

    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u00020\u0003H\u0096\u0080\u0004¨\u0006\u0004"}, d2 = {"kotlin/internal/jdk8/JDK8PlatformImplementations$getSystemClock$2", "Lkotlin/time/Clock;", "now", "Lkotlin/time/Instant;", "kotlin-stdlib-jdk8"}, k = 1, mv = {2, 3, 0}, xi = 48)
    /* renamed from: kotlin.internal.jdk8.JDK8PlatformImplementations$getSystemClock$2, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass2 implements Clock {
        public AnonymousClass2() {
        }

        @Override // kotlin.time.Clock
        public Instant now() {
            return Instant.Companion.fromEpochMilliseconds(System.currentTimeMillis());
        }
    }

    public JDK8PlatformImplementations() {
    }

    private final boolean sdkIsNullOrAtLeast(int r1) {
        Integer r0 = ReflectSdkVersion.sdkVersion;
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r0.intValue() >= r1) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // kotlin.internal.PlatformImplementations
    public Random defaultPlatformRandom() {
        if (sdkIsNullOrAtLeast(34) == false) goto L7;
        return new PlatformThreadLocalRandom();
    L7:
        return super.defaultPlatformRandom();
    }

    @Override // kotlin.internal.PlatformImplementations
    public MatchGroup getMatchResultNamedGroup(MatchResult r4, String r5) {
        if ((r4 instanceof Matcher) == false) goto L5;
        Matcher r42 = (Matcher) r4;
    L6:
        if (r42 == null) goto L11;
        IntRange r3 = new IntRange(r42.start(r5), r42.end(r5) - 1);
        if (r3.getStart().intValue() >= 0) goto L10;
        return null;
    L10:
        return new MatchGroup(r42.group(r5), r3);
    L11:
        ByteString$$ExternalSyntheticBUOutline0.m("Retrieving groups by name is not supported on this platform.");
        return null;
    L5:
        r42 = null;
        goto L6
    }

    @Override // kotlin.internal.PlatformImplementations
    public Clock getSystemClock() {
        if (sdkIsNullOrAtLeast(26) == false) goto L7;
        return new AnonymousClass1();
    L7:
        return new AnonymousClass2();
    }
}
