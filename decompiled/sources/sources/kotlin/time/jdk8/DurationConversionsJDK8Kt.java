package kotlin.time.jdk8;

import j$.time.Duration;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0014\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0014\u0010\u0006\u001a\u00020\u0000*\u00020\u0001H\u0087\b¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"j$/time/Duration", "Lkotlin/time/Duration;", "toKotlinDuration", "(Lj$/time/Duration;)J", "toJavaDuration-LRDsOJo", "(J)Lj$/time/Duration;", "toJavaDuration", "kotlin-stdlib-jdk8"}, k = 2, mv = {2, 3, 0}, xi = 48)
@JvmName(name = "DurationConversionsJDK8Kt")
@SourceDebugExtension({"SMAP\nDurationConversions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DurationConversions.kt\nkotlin/time/jdk8/DurationConversionsJDK8Kt\n+ 2 Duration.kt\nkotlin/time/Duration\n*L\n1#1,33:1\n620#2:34\n*S KotlinDebug\n*F\n+ 1 DurationConversions.kt\nkotlin/time/jdk8/DurationConversionsJDK8Kt\n*L\n33#1:34\n*E\n"})
/* loaded from: classes5.dex */
public final class DurationConversionsJDK8Kt {
    @SinceKotlin(version = "1.6")
    @InlineOnly
    /* renamed from: toJavaDuration-LRDsOJo, reason: not valid java name */
    private static final Duration m3516toJavaDurationLRDsOJo(long r2) {
        return Duration.ofSeconds(kotlin.time.Duration.m3389getInWholeSecondsimpl(r2), kotlin.time.Duration.m3391getNanosecondsComponentimpl(r2));
    }

    @SinceKotlin(version = "1.6")
    @InlineOnly
    private static final long toKotlinDuration(Duration r4) {
        return kotlin.time.Duration.m3404plusLRDsOJo(DurationKt.toDuration(r4.getSeconds(), DurationUnit.SECONDS), DurationKt.toDuration(r4.getNano(), DurationUnit.NANOSECONDS));
    }
}
