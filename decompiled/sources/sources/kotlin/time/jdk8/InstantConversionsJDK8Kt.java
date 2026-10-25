package kotlin.time.jdk8;

import j$.time.Instant;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.JvmName;
import kotlin.time.ExperimentalTime;
import org.mvel2.MVEL;

@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0013\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0007¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0013\u0010\u0004\u001a\u00020\u0000*\u00020\u0001H\u0007¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lkotlin/time/Instant;", "j$/time/Instant", "toJavaInstant", "(Lkotlin/time/Instant;)Lj$/time/Instant;", "toKotlinInstant", "(Lj$/time/Instant;)Lkotlin/time/Instant;", "kotlin-stdlib-jdk8"}, k = 2, mv = {2, 3, 0}, xi = 48)
@JvmName(name = "InstantConversionsJDK8Kt")
/* loaded from: classes5.dex */
public final class InstantConversionsJDK8Kt {
    @SinceKotlin(version = MVEL.VERSION)
    @WasExperimental(markerClass = {ExperimentalTime.class})
    public static final Instant toJavaInstant(kotlin.time.Instant r4) {
        return Instant.ofEpochSecond(r4.getEpochSeconds(), r4.getNanosecondsOfSecond());
    }

    @SinceKotlin(version = MVEL.VERSION)
    @WasExperimental(markerClass = {ExperimentalTime.class})
    public static final kotlin.time.Instant toKotlinInstant(Instant r3) {
        return kotlin.time.Instant.Companion.fromEpochSeconds(r3.getEpochSecond(), r3.getNano());
    }
}
