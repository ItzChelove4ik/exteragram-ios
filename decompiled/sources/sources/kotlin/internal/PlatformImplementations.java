package kotlin.internal;

import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.MatchResult;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.FallbackThreadLocalRandom;
import kotlin.random.Random;
import kotlin.text.MatchGroup;
import kotlin.time.Clock;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001:\u0001\u0015B\t\bF¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0096\u0080\u0004J\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n2\u0006\u0010\b\u001a\u00020\u0007H\u0096\u0080\u0004J\u001c\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004J\n\u0010\u0013\u001a\u00020\u0014H\u0096\u0080\u0004¨\u0006\u0016"}, d2 = {"Lkotlin/internal/PlatformImplementations;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "addSuppressed", _UrlKt.FRAGMENT_ENCODE_SET, "cause", _UrlKt.FRAGMENT_ENCODE_SET, "exception", "getSuppressed", _UrlKt.FRAGMENT_ENCODE_SET, "getMatchResultNamedGroup", "Lkotlin/text/MatchGroup;", "matchResult", "Ljava/util/regex/MatchResult;", "name", _UrlKt.FRAGMENT_ENCODE_SET, "defaultPlatformRandom", "Lkotlin/random/Random;", "getSystemClock", "Lkotlin/time/Clock;", "ReflectThrowable", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPlatformImplementations.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PlatformImplementations.kt\nkotlin/internal/PlatformImplementations\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,85:1\n1#2:86\n*E\n"})
/* loaded from: classes.dex */
public class PlatformImplementations {

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0084\b¢\u0006\u0002\n\u0000R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lkotlin/internal/PlatformImplementations$ReflectThrowable;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "addSuppressed", "Ljava/lang/reflect/Method;", "getSuppressed", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nPlatformImplementations.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PlatformImplementations.kt\nkotlin/internal/PlatformImplementations$ReflectThrowable\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,85:1\n1#2:86\n*E\n"})
    /* loaded from: classes5.dex */
    public static final class ReflectThrowable {
        public static final ReflectThrowable INSTANCE = null;

        @JvmField
        public static final Method addSuppressed = null;

        @JvmField
        public static final Method getSuppressed = null;

        static {
            INSTANCE = new ReflectThrowable();
            Method[] r1 = Throwable.class.getMethods();
            int r2 = r1.length;
            int r3 = 0;
            int r4 = 0;
        L3:
            Method r5 = null;
            if (r4 >= r2) goto L11;
            Method r6 = r1[r4];
            if (Intrinsics.areEqual(r6.getName(), "addSuppressed") == false) goto L10;
            if (Intrinsics.areEqual(ArraysKt.singleOrNull(r6.getParameterTypes()), Throwable.class) == false) goto L10;
        L12:
            addSuppressed = r6;
            int r0 = r1.length;
        L13:
            if (r3 >= r0) goto L18;
            Method r22 = r1[r3];
            if (Intrinsics.areEqual(r22.getName(), "getSuppressed") == true) goto L16;
            r3 = r3 + 1;
            goto L13
        L16:
            r5 = r22;
        L18:
            getSuppressed = r5;
            return;
        L10:
            r4 = r4 + 1;
            goto L3
        L11:
            r6 = null;
            goto L12
        }

        private ReflectThrowable() {
        }
    }

    public PlatformImplementations() {
    }

    public void addSuppressed(Throwable r1, Throwable r2) {
        Method r0 = ReflectThrowable.addSuppressed;
        if (r0 == null) goto L6;
        r0.invoke(r1, new Object[]{r2});
        return;
    }

    public Random defaultPlatformRandom() {
        return new FallbackThreadLocalRandom();
    }

    public MatchGroup getMatchResultNamedGroup(MatchResult r1, String r2) {
        throw new UnsupportedOperationException("Retrieving groups by name is not supported on this platform.");
    }

    public List<Throwable> getSuppressed(Throwable r2) {
        Method r1 = ReflectThrowable.getSuppressed;
        if (r1 == null) goto L11;
        Object r12 = r1.invoke(r2, null);
        if (r12 == null) goto L11;
        List<Throwable> r13 = ArraysKt.asList((Throwable[]) r12);
        if (r13 == null) goto L11;
        return r13;
    L11:
        return CollectionsKt.emptyList();
    }

    public Clock getSystemClock() {
        throw new UnsupportedOperationException("getSystemClock should not be called on the base PlatformImplementations.");
    }
}
