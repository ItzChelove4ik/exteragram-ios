package kotlin.time;

import c.f$$ExternalSyntheticBUOutline1;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.time.Duration;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;

@SinceKotlin(version = MVEL.VERSION)
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 '2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00060\u0002j\u0002`\u0003:\u0001'B\u0019\b@\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\n\u0010\u000e\u001a\u00020\u0005H\u0086\u0080\u0004J\u0019\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0011H\u0086\u0082\u0004¢\u0006\u0004\b\u0012\u0010\u0013J\u0019\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0011H\u0086\u0082\u0004¢\u0006\u0004\b\u0015\u0010\u0013J\u0019\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0000H\u0086\u0082\u0004¢\u0006\u0004\b\u0017\u0010\u0018J\u0012\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0000H\u0096\u0082\u0004J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u001cH\u0096\u0082\u0004J\n\u0010\u001d\u001a\u00020\u0007H\u0096\u0080\u0004J\n\u0010\u001e\u001a\u00020\u001fH\u0096\u0080\u0004J\n\u0010 \u001a\u00020\u001cH\u0082\u0080\u0004J\u001b\u0010!\u001a\u00020\"2\n\u0010#\u001a\u00060$j\u0002`%H\u0082\u0080\u0004¢\u0006\u0002\u0010&R\u0015\u0010\u0004\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0006\u001a\u00020\u0007X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006("}, d2 = {"Lkotlin/time/Instant;", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "epochSeconds", _UrlKt.FRAGMENT_ENCODE_SET, "nanosecondsOfSecond", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(JI)V", "getEpochSeconds", "()J", "getNanosecondsOfSecond", "()I", "toEpochMilliseconds", "plus", "duration", "Lkotlin/time/Duration;", "plus-LRDsOJo", "(J)Lkotlin/time/Instant;", "minus", "minus-LRDsOJo", "other", "minus-UwyO8pc", "(Lkotlin/time/Instant;)J", "compareTo", "equals", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "writeReplace", "readObject", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.TYPE_INPUT, "Ljava/io/ObjectInputStream;", "Lkotlin/internal/ReadObjectParameterType;", "(Ljava/io/ObjectInputStream;)V", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@WasExperimental(markerClass = {ExperimentalTime.class})
@SourceDebugExtension({"SMAP\nInstant.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Instant.kt\nkotlin/time/Instant\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Instant.kt\nkotlin/time/InstantKt\n+ 4 Duration.kt\nkotlin/time/Duration\n*L\n1#1,871:1\n1#2:872\n810#3,14:873\n793#3,6:887\n810#3,14:893\n793#3,6:907\n793#3,6:914\n620#4:913\n*S KotlinDebug\n*F\n+ 1 Instant.kt\nkotlin/time/Instant\n*L\n150#1:873,14\n153#1:887,6\n161#1:893,14\n164#1:907,6\n188#1:914,6\n184#1:913\n*E\n"})
/* loaded from: classes5.dex */
public final class Instant implements Comparable<Instant>, Serializable {
    public static final Companion Companion = null;
    private static final Instant MAX = null;
    private static final Instant MIN = null;
    private final long epochSeconds;
    private final int nanosecondsOfSecond;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005H\u0087\u0080\u0004J\u0012\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0086\u0080\u0004J\u001c\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\bH\u0086\u0080\u0004J\u001a\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0086\u0080\u0004J\u0012\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000fH\u0086\u0080\u0004J\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000e\u001a\u00020\u000fH\u0086\u0080\u0004R\u0015\u0010\u0011\u001a\u00020\u00058FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\u0014\u001a\u00020\u00058FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0015\u0010\u0016\u001a\u00020\u0005X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0015\u0010\u0018\u001a\u00020\u0005X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013¨\u0006\u001a"}, d2 = {"Lkotlin/time/Instant$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "now", "Lkotlin/time/Instant;", "fromEpochMilliseconds", "epochMilliseconds", _UrlKt.FRAGMENT_ENCODE_SET, "fromEpochSeconds", "epochSeconds", "nanosecondAdjustment", _UrlKt.FRAGMENT_ENCODE_SET, "parse", PluginsConstants.Settings.TYPE_INPUT, _UrlKt.FRAGMENT_ENCODE_SET, "parseOrNull", "DISTANT_PAST", "getDISTANT_PAST", "()Lkotlin/time/Instant;", "DISTANT_FUTURE", "getDISTANT_FUTURE", "MIN", "getMIN$kotlin_stdlib", "MAX", "getMAX$kotlin_stdlib", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nInstant.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Instant.kt\nkotlin/time/Instant$Companion\n+ 2 Instant.kt\nkotlin/time/InstantKt\n*L\n1#1,871:1\n793#2,6:872\n*S KotlinDebug\n*F\n+ 1 Instant.kt\nkotlin/time/Instant$Companion\n*L\n320#1:872,6\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static /* synthetic */ Instant fromEpochSeconds$default(Companion r0, long r1, long r3, int r5, Object r6) {
            if ((r5 & 2) == 0) goto L6;
            r3 = 0;
        L6:
            return r0.fromEpochSeconds(r1, r3);
        }

        public final Instant fromEpochMilliseconds(long r9) {
            long r2 = r9 / 1000;
            if ((r9 ^ 1000) < 0) goto L5;
        L7:
            long r92 = r9 % 1000;
            int r93 = (int) ((r92 + (1000 & (((r92 ^ 1000) & ((-r92) | r92)) >> 63))) * 1000000);
            if (r2 >= (-31557014167219200L)) goto L12;
            return getMIN$kotlin_stdlib();
        L12:
            if (r2 <= 31556889864403199L) goto L16;
            return getMAX$kotlin_stdlib();
        L16:
            return fromEpochSeconds(r2, r93);
        L5:
            if ((r2 * 1000) == r9) goto L7;
            r2 = r2 - 1;
            goto L7
        }

        public final Instant fromEpochSeconds(long r3, int r5) {
            return fromEpochSeconds(r3, r5);
        }

        public final Instant fromEpochSeconds(long r11, long r13) {
            long r2 = r13 / 1000000000;
            if ((r13 ^ 1000000000) < 0) goto L5;
        L7:
            long r4 = r11 + r2;
            if ((r11 ^ r4) >= 0) goto L18;
            if ((r2 ^ r11) < 0) goto L18;
            if (r11 <= 0) goto L16;
            return Instant.Companion.getMAX$kotlin_stdlib();
        L16:
            return Instant.Companion.getMIN$kotlin_stdlib();
        L18:
            if (r4 >= (-31557014167219200L)) goto L22;
            return getMIN$kotlin_stdlib();
        L22:
            if (r4 > 31556889864403199L) goto L24;
            long r132 = r13 % 1000000000;
            return new Instant(r4, (int) (r132 + ((((r132 ^ 1000000000) & ((-r132) | r132)) >> 63) & 1000000000)));
        L24:
            return getMAX$kotlin_stdlib();
        L5:
            if ((r2 * 1000000000) == r13) goto L7;
            r2 = r2 - 1;
            goto L7
        }

        public final Instant getDISTANT_FUTURE() {
            return fromEpochSeconds(3093527980800L, 0);
        }

        public final Instant getDISTANT_PAST() {
            return fromEpochSeconds(-3217862419201L, 999999999);
        }

        public final Instant getMAX$kotlin_stdlib() {
            return Instant.access$getMAX$cp();
        }

        public final Instant getMIN$kotlin_stdlib() {
            return Instant.access$getMIN$cp();
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Use Clock.System.now() instead", replaceWith = @ReplaceWith(expression = "Clock.System.now()", imports = {"kotlin.time.Clock"}))
        public final Instant now() {
            throw new NotImplementedError(null, 1, null);
        }

        public final Instant parse(CharSequence r1) {
            return InstantKt.access$parseIso(r1).toInstant();
        }

        public final Instant parseOrNull(CharSequence r1) {
            return InstantKt.access$parseIso(r1).toInstantOrNull();
        }
    }

    static {
        Companion = new Companion(null);
        MIN = new Instant(-31557014167219200L, 0);
        MAX = new Instant(31556889864403199L, 999999999);
    }

    public Instant(long r3, int r5) {
        this.epochSeconds = r3;
        this.nanosecondsOfSecond = r5;
        if ((-31557014167219200L) <= r3) goto L5;
    L7:
        f$$ExternalSyntheticBUOutline1.m("Instant exceeds minimum or maximum instant");
        throw null;
    L5:
        if (r3 >= 31556889864403200L) goto L7;
    }

    public static final /* synthetic */ Instant access$getMAX$cp() {
        return MAX;
    }

    public static final /* synthetic */ Instant access$getMIN$cp() {
        return MIN;
    }

    private final void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization is supported via proxy only");
    }

    private final Object writeReplace() {
        return InstantJvmKt.serializedInstant(this);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Instant r1) {
        return compareTo2(r1);
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(Instant r5) {
        int r0 = Intrinsics.compare(this.epochSeconds, r5.epochSeconds);
        if (r0 == 0) goto L6;
        return r0;
    L6:
        return Intrinsics.compare(this.nanosecondsOfSecond, r5.nanosecondsOfSecond);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L4;
        return true;
    L4:
        if ((r5 instanceof Instant) == false) goto L10;
        Instant r52 = (Instant) r5;
        if (this.epochSeconds == r52.epochSeconds) goto L8;
        return false;
    L8:
        if (this.nanosecondsOfSecond == r52.nanosecondsOfSecond) goto L16;
        return false;
    L16:
        return true;
    L10:
        return false;
    }

    public final long getEpochSeconds() {
        return this.epochSeconds;
    }

    public final int getNanosecondsOfSecond() {
        return this.nanosecondsOfSecond;
    }

    public int hashCode() {
        return Long.hashCode(this.epochSeconds) + (this.nanosecondsOfSecond * 51);
    }

    /* renamed from: minus-LRDsOJo, reason: not valid java name */
    public final Instant m3478minusLRDsOJo(long r1) {
        return m3480plusLRDsOJo(Duration.m3419unaryMinusUwyO8pc(r1));
    }

    /* renamed from: minus-UwyO8pc, reason: not valid java name */
    public final long m3479minusUwyO8pc(Instant r5) {
        Duration.Companion r0 = Duration.Companion;
        return Duration.m3404plusLRDsOJo(DurationKt.toDuration(this.epochSeconds - r5.epochSeconds, DurationUnit.SECONDS), DurationKt.toDuration(this.nanosecondsOfSecond - r5.nanosecondsOfSecond, DurationUnit.NANOSECONDS));
    }

    /* renamed from: plus-LRDsOJo, reason: not valid java name */
    public final Instant m3480plusLRDsOJo(long r12) {
        long r0 = Duration.m3389getInWholeSecondsimpl(r12);
        int r2 = Duration.m3391getNanosecondsComponentimpl(r12);
        if (r0 != 0) goto L6;
        if (r2 != 0) goto L6;
        return this;
    L6:
        long r5 = this.epochSeconds;
        long r7 = r5 + r0;
        if ((r5 ^ r7) < 0) goto L9;
    L16:
        int r11 = this.nanosecondsOfSecond + r2;
        return Companion.fromEpochSeconds(r7, r11);
    L9:
        if ((r0 ^ r5) < 0) goto L16;
        if (Duration.m3402isPositiveimpl(r12) == false) goto L15;
        return MAX;
    L15:
        return MIN;
    }

    public final long toEpochMilliseconds() {
        long r0 = this.epochSeconds;
        long r6 = 1000;
        if (r0 >= 0) goto L5;
        long r02 = r0 + 1;
        if (r02 != 1) goto L24;
    L30:
        long r03 = (this.nanosecondsOfSecond / DurationKt.NANOS_IN_MILLIS) - 1000;
        long r4 = r6 + r03;
        if ((r6 ^ r4) < 0) goto L33;
    L35:
        return r4;
    L33:
        if ((r03 ^ r6) < 0) goto L35;
        return Long.MIN_VALUE;
    L24:
        if (r02 == 0) goto L29;
        long r10 = r02 * 1000;
        if ((r10 / 1000) != r02) goto L28;
        r6 = r10;
        goto L30
    L28:
        return Long.MIN_VALUE;
    L29:
        r6 = 0;
        goto L30
    L5:
        if (r0 != 1) goto L8;
    L14:
        long r04 = this.nanosecondsOfSecond / DurationKt.NANOS_IN_MILLIS;
        long r42 = r6 + r04;
        if ((r6 ^ r42) < 0) goto L17;
    L19:
        return r42;
    L17:
        if ((r04 ^ r6) < 0) goto L19;
        return LongCompanionObject.MAX_VALUE;
    L8:
        if (r0 == 0) goto L13;
        long r102 = r0 * 1000;
        if ((r102 / 1000) != r0) goto L12;
        r6 = r102;
        goto L14
    L12:
        return LongCompanionObject.MAX_VALUE;
    L13:
        r6 = 0;
        goto L14
    }

    public String toString() {
        return InstantKt.access$formatIso(this);
    }
}
