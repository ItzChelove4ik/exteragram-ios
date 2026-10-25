package kotlin.time;

import c.f$$ExternalSyntheticBUOutline1;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.comparisons.ComparisonsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import okio.Utf8$$ExternalSyntheticBUOutline1;
import org.mvel2.asm.signature.SignatureVisitor;

@SinceKotlin(version = "1.6")
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0006\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087@\u0018\u0000 \u0089\u00012\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002\u0089\u0001B\u0011\bA\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\f\u001a\u00020\rH\u0082\u0080\u0004¢\u0006\u0004\b\u000e\u0010\u000fJ\u0011\u0010\u0010\u001a\u00020\rH\u0082\u0080\u0004¢\u0006\u0004\b\u0011\u0010\u000fJ\u0011\u0010\u0016\u001a\u00020\u0000H\u0086\u0082\u0004¢\u0006\u0004\b\u0017\u0010\u0005J\u0019\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0000H\u0086\u0082\u0004¢\u0006\u0004\b\u001a\u0010\u001bJ!\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u0003H\u0082\u0080\u0004¢\u0006\u0004\b\u001f\u0010 J\u0019\u0010!\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0000H\u0086\u0082\u0004¢\u0006\u0004\b\"\u0010\u001bJ\u0019\u0010#\u001a\u00020\u00002\u0006\u0010$\u001a\u00020\tH\u0086\u0082\u0004¢\u0006\u0004\b%\u0010&J\u0019\u0010#\u001a\u00020\u00002\u0006\u0010$\u001a\u00020'H\u0086\u0082\u0004¢\u0006\u0004\b%\u0010(J\u0019\u0010)\u001a\u00020\u00002\u0006\u0010$\u001a\u00020\tH\u0086\u0082\u0004¢\u0006\u0004\b*\u0010&J\u0019\u0010)\u001a\u00020\u00002\u0006\u0010$\u001a\u00020'H\u0086\u0082\u0004¢\u0006\u0004\b*\u0010(J\u0019\u0010)\u001a\u00020'2\u0006\u0010\u0019\u001a\u00020\u0000H\u0086\u0082\u0004¢\u0006\u0004\b+\u0010,J\u0019\u0010-\u001a\u00020\u00002\u0006\u0010.\u001a\u00020\u0013H\u0080\u0080\u0004¢\u0006\u0004\b/\u00100J\u0011\u00101\u001a\u00020\rH\u0086\u0080\u0004¢\u0006\u0004\b2\u0010\u000fJ\u0011\u00103\u001a\u00020\rH\u0086\u0080\u0004¢\u0006\u0004\b4\u0010\u000fJ\u0011\u00105\u001a\u00020\rH\u0086\u0080\u0004¢\u0006\u0004\b6\u0010\u000fJ\u0011\u00107\u001a\u00020\rH\u0086\u0080\u0004¢\u0006\u0004\b8\u0010\u000fJ\u0019\u0010;\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u0000H\u0096\u0082\u0004¢\u0006\u0004\b<\u0010=J\u009e\u0001\u0010>\u001a\u0002H?\"\u0004\b\u0000\u0010?2u\u0010@\u001aq\u0012\u0013\u0012\u00110\u0003¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(D\u0012\u0013\u0012\u00110\t¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(E\u0012\u0013\u0012\u00110\t¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(F\u0012\u0013\u0012\u00110\t¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(G\u0012\u0013\u0012\u00110\t¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(H\u0012\u0004\u0012\u0002H?0AH\u0086\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\bI\u0010JJ\u0089\u0001\u0010>\u001a\u0002H?\"\u0004\b\u0000\u0010?2`\u0010@\u001a\\\u0012\u0013\u0012\u00110\u0003¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(E\u0012\u0013\u0012\u00110\t¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(F\u0012\u0013\u0012\u00110\t¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(G\u0012\u0013\u0012\u00110\t¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(H\u0012\u0004\u0012\u0002H?0KH\u0086\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\bI\u0010LJt\u0010>\u001a\u0002H?\"\u0004\b\u0000\u0010?2K\u0010@\u001aG\u0012\u0013\u0012\u00110\u0003¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(F\u0012\u0013\u0012\u00110\t¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(G\u0012\u0013\u0012\u00110\t¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(H\u0012\u0004\u0012\u0002H?0MH\u0086\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\bI\u0010NJ_\u0010>\u001a\u0002H?\"\u0004\b\u0000\u0010?26\u0010@\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(G\u0012\u0013\u0012\u00110\t¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(H\u0012\u0004\u0012\u0002H?0OH\u0086\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\bI\u0010PJ\u0019\u0010^\u001a\u00020'2\u0006\u0010.\u001a\u00020\u0013H\u0086\u0080\u0004¢\u0006\u0004\b_\u0010`J\u0019\u0010a\u001a\u00020\u00032\u0006\u0010.\u001a\u00020\u0013H\u0086\u0080\u0004¢\u0006\u0004\bb\u00100J\u0019\u0010c\u001a\u00020\t2\u0006\u0010.\u001a\u00020\u0013H\u0086\u0080\u0004¢\u0006\u0004\bd\u0010eJ\u0011\u0010t\u001a\u00020uH\u0096\u0080\u0004¢\u0006\u0004\bv\u0010wJC\u0010x\u001a\u00020y*\u00060zj\u0002`{2\u0006\u0010|\u001a\u00020\t2\u0006\u0010}\u001a\u00020\t2\u0006\u0010~\u001a\u00020\t2\u0006\u0010.\u001a\u00020u2\u0006\u0010\u007f\u001a\u00020\rH\u0082\u0080\u0004¢\u0006\u0006\b\u0080\u0001\u0010\u0081\u0001J%\u0010t\u001a\u00020u2\u0006\u0010.\u001a\u00020\u00132\t\b\u0002\u0010\u0082\u0001\u001a\u00020\tH\u0086\u0080\u0004¢\u0006\u0005\bv\u0010\u0083\u0001J\u0013\u0010\u0084\u0001\u001a\u00020uH\u0086\u0080\u0004¢\u0006\u0005\b\u0085\u0001\u0010wJ\u0016\u0010\u0086\u0001\u001a\u00020\r2\t\u0010\u0019\u001a\u0005\u0018\u00010\u0087\u0001HÖ\u0083\u0004J\u000b\u0010\u0088\u0001\u001a\u00020\tHÖ\u0081\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\u0006\u001a\u00020\u00038BX\u0082\u0084\b¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0016\u0010\b\u001a\u00020\t8Â\u0002X\u0082\u0084\b¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\b¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0015\u00109\u001a\u00020\u00008FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b:\u0010\u0005R\u001b\u0010Q\u001a\u00020\t8@X\u0081\u0084\b¢\u0006\f\u0012\u0004\bR\u0010S\u001a\u0004\bT\u0010\u000bR\u001b\u0010U\u001a\u00020\t8@X\u0081\u0084\b¢\u0006\f\u0012\u0004\bV\u0010S\u001a\u0004\bW\u0010\u000bR\u001b\u0010X\u001a\u00020\t8@X\u0081\u0084\b¢\u0006\f\u0012\u0004\bY\u0010S\u001a\u0004\bZ\u0010\u000bR\u001b\u0010[\u001a\u00020\t8@X\u0081\u0084\b¢\u0006\f\u0012\u0004\b\\\u0010S\u001a\u0004\b]\u0010\u000bR\u0015\u0010f\u001a\u00020\u00038FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\bg\u0010\u0005R\u0015\u0010h\u001a\u00020\u00038FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\bi\u0010\u0005R\u0015\u0010j\u001a\u00020\u00038FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\bk\u0010\u0005R\u0015\u0010l\u001a\u00020\u00038FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\bm\u0010\u0005R\u0015\u0010n\u001a\u00020\u00038FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\bo\u0010\u0005R\u0015\u0010p\u001a\u00020\u00038FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\bq\u0010\u0005R\u0015\u0010r\u001a\u00020\u00038FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\bs\u0010\u0005\u0088\u0001\u0002\u0092\u0001\u00020\u0003\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u008a\u0001"}, d2 = {"Lkotlin/time/Duration;", _UrlKt.FRAGMENT_ENCODE_SET, "rawValue", _UrlKt.FRAGMENT_ENCODE_SET, "constructor-impl", "(J)J", "value", "getValue-impl", "unitDiscriminator", _UrlKt.FRAGMENT_ENCODE_SET, "getUnitDiscriminator-impl", "(J)I", "isInNanos", _UrlKt.FRAGMENT_ENCODE_SET, "isInNanos-impl", "(J)Z", "isInMillis", "isInMillis-impl", "storageUnit", "Lkotlin/time/DurationUnit;", "getStorageUnit-impl", "(J)Lkotlin/time/DurationUnit;", "unaryMinus", "unaryMinus-UwyO8pc", "plus", "other", "plus-LRDsOJo", "(JJ)J", "addValuesMixedRanges", "thisMillis", "otherNanos", "addValuesMixedRanges-UwyO8pc", "(JJJ)J", "minus", "minus-LRDsOJo", "times", "scale", "times-UwyO8pc", "(JI)J", _UrlKt.FRAGMENT_ENCODE_SET, "(JD)J", "div", "div-UwyO8pc", "div-LRDsOJo", "(JJ)D", "truncateTo", "unit", "truncateTo-UwyO8pc$kotlin_stdlib", "(JLkotlin/time/DurationUnit;)J", "isNegative", "isNegative-impl", "isPositive", "isPositive-impl", "isInfinite", "isInfinite-impl", "isFinite", "isFinite-impl", "absoluteValue", "getAbsoluteValue-UwyO8pc", "compareTo", "compareTo-LRDsOJo", "(JJ)I", "toComponents", "T", "action", "Lkotlin/Function5;", "Lkotlin/ParameterName;", "name", "days", "hours", "minutes", "seconds", "nanoseconds", "toComponents-impl", "(JLkotlin/jvm/functions/Function5;)Ljava/lang/Object;", "Lkotlin/Function4;", "(JLkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "Lkotlin/Function3;", "(JLkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "Lkotlin/Function2;", "(JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "hoursComponent", "getHoursComponent$annotations", "()V", "getHoursComponent-impl", "minutesComponent", "getMinutesComponent$annotations", "getMinutesComponent-impl", "secondsComponent", "getSecondsComponent$annotations", "getSecondsComponent-impl", "nanosecondsComponent", "getNanosecondsComponent$annotations", "getNanosecondsComponent-impl", "toDouble", "toDouble-impl", "(JLkotlin/time/DurationUnit;)D", "toLong", "toLong-impl", "toInt", "toInt-impl", "(JLkotlin/time/DurationUnit;)I", "inWholeDays", "getInWholeDays-impl", "inWholeHours", "getInWholeHours-impl", "inWholeMinutes", "getInWholeMinutes-impl", "inWholeSeconds", "getInWholeSeconds-impl", "inWholeMilliseconds", "getInWholeMilliseconds-impl", "inWholeMicroseconds", "getInWholeMicroseconds-impl", "inWholeNanoseconds", "getInWholeNanoseconds-impl", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(J)Ljava/lang/String;", "appendFractional", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "whole", "fractional", "fractionalSize", "isoZeroes", "appendFractional-impl", "(JLjava/lang/StringBuilder;IIILjava/lang/String;Z)V", "decimals", "(JLkotlin/time/DurationUnit;I)Ljava/lang/String;", "toIsoString", "toIsoString-impl", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@JvmInline
@SourceDebugExtension({"SMAP\nDuration.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Duration.kt\nkotlin/time/Duration\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1613:1\n41#1:1614\n41#1:1615\n41#1:1616\n41#1:1617\n41#1:1618\n572#1:1619\n589#1:1627\n173#2,6:1620\n1#3:1626\n*S KotlinDebug\n*F\n+ 1 Duration.kt\nkotlin/time/Duration\n*L\n42#1:1614\n43#1:1615\n353#1:1616\n362#1:1617\n546#1:1618\n847#1:1619\n938#1:1627\n889#1:1620,6\n*E\n"})
/* loaded from: classes.dex */
public final class Duration implements Comparable<Duration> {
    public static final Companion Companion = null;
    private static final long INFINITE = 0;
    private static final long INVALID = 0;
    public static final long INVALID_RAW_VALUE = 9223372036854759646L;
    private static final long NEG_INFINITE = 0;
    private static final long ZERO = 0;
    private final long rawValue;

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001a\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0080\u0080\u0004¢\u0006\u0004\b\b\u0010\tJ\"\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0087\u0080\u0004J\u0019\u00108\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u000209H\u0086\u0080\u0004¢\u0006\u0004\b:\u0010;J\u0019\u0010<\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u000209H\u0086\u0080\u0004¢\u0006\u0004\b=\u0010;J\u0019\u0010>\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0019\u001a\u000209H\u0086\u0080\u0004¢\u0006\u0002\b?J\u0019\u0010@\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0019\u001a\u000209H\u0086\u0080\u0004¢\u0006\u0002\bAR\u001d\u0010\n\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\u0010\n\u0002\u0010\u000e\u0012\u0004\b\u000b\u0010\u0003\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0010\u0010\rR\u0017\u0010\u0011\u001a\u00020\u0005X\u0080\u0084\b¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0012\u0010\rR\u000f\u0010\u0013\u001a\u00020\u0007X\u0080Ô\b¢\u0006\u0002\n\u0000R\u001d\u0010\u0014\u001a\u00020\u0005X\u0080\u0084\b¢\u0006\u0010\n\u0002\u0010\u000e\u0012\u0004\b\u0015\u0010\u0003\u001a\u0004\b\u0016\u0010\rR \u0010\u001d\u001a\u00020\u0005*\u00020\u001e8Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R \u0010\u001d\u001a\u00020\u0005*\u00020\u00078Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b\u001f\u0010#\u001a\u0004\b!\u0010\tR \u0010\u001d\u001a\u00020\u0005*\u00020\u00188Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b\u001f\u0010$\u001a\u0004\b!\u0010%R \u0010&\u001a\u00020\u0005*\u00020\u001e8Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b'\u0010 \u001a\u0004\b(\u0010\"R \u0010&\u001a\u00020\u0005*\u00020\u00078Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b'\u0010#\u001a\u0004\b(\u0010\tR \u0010&\u001a\u00020\u0005*\u00020\u00188Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b'\u0010$\u001a\u0004\b(\u0010%R \u0010)\u001a\u00020\u0005*\u00020\u001e8Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b*\u0010 \u001a\u0004\b+\u0010\"R \u0010)\u001a\u00020\u0005*\u00020\u00078Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b*\u0010#\u001a\u0004\b+\u0010\tR \u0010)\u001a\u00020\u0005*\u00020\u00188Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b*\u0010$\u001a\u0004\b+\u0010%R \u0010,\u001a\u00020\u0005*\u00020\u001e8Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b-\u0010 \u001a\u0004\b.\u0010\"R \u0010,\u001a\u00020\u0005*\u00020\u00078Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b-\u0010#\u001a\u0004\b.\u0010\tR \u0010,\u001a\u00020\u0005*\u00020\u00188Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b-\u0010$\u001a\u0004\b.\u0010%R \u0010/\u001a\u00020\u0005*\u00020\u001e8Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b0\u0010 \u001a\u0004\b1\u0010\"R \u0010/\u001a\u00020\u0005*\u00020\u00078Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b0\u0010#\u001a\u0004\b1\u0010\tR \u0010/\u001a\u00020\u0005*\u00020\u00188Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b0\u0010$\u001a\u0004\b1\u0010%R \u00102\u001a\u00020\u0005*\u00020\u001e8Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b3\u0010 \u001a\u0004\b4\u0010\"R \u00102\u001a\u00020\u0005*\u00020\u00078Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b3\u0010#\u001a\u0004\b4\u0010\tR \u00102\u001a\u00020\u0005*\u00020\u00188Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b3\u0010$\u001a\u0004\b4\u0010%R \u00105\u001a\u00020\u0005*\u00020\u001e8Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b6\u0010 \u001a\u0004\b7\u0010\"R \u00105\u001a\u00020\u0005*\u00020\u00078Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b6\u0010#\u001a\u0004\b7\u0010\tR \u00105\u001a\u00020\u0005*\u00020\u00188Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b6\u0010$\u001a\u0004\b7\u0010%¨\u0006B"}, d2 = {"Lkotlin/time/Duration$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "fromRawValue", "Lkotlin/time/Duration;", "rawValue", _UrlKt.FRAGMENT_ENCODE_SET, "fromRawValue-UwyO8pc$kotlin_stdlib", "(J)J", "ZERO", "getZERO-UwyO8pc$annotations", "getZERO-UwyO8pc", "()J", "J", "INFINITE", "getINFINITE-UwyO8pc", "NEG_INFINITE", "getNEG_INFINITE-UwyO8pc$kotlin_stdlib", "INVALID_RAW_VALUE", "INVALID", "getINVALID-UwyO8pc$kotlin_stdlib$annotations", "getINVALID-UwyO8pc$kotlin_stdlib", "convert", _UrlKt.FRAGMENT_ENCODE_SET, "value", "sourceUnit", "Lkotlin/time/DurationUnit;", "targetUnit", "nanoseconds", _UrlKt.FRAGMENT_ENCODE_SET, "getNanoseconds-UwyO8pc$annotations", "(I)V", "getNanoseconds-UwyO8pc", "(I)J", "(J)V", "(D)V", "(D)J", "microseconds", "getMicroseconds-UwyO8pc$annotations", "getMicroseconds-UwyO8pc", "milliseconds", "getMilliseconds-UwyO8pc$annotations", "getMilliseconds-UwyO8pc", "seconds", "getSeconds-UwyO8pc$annotations", "getSeconds-UwyO8pc", "minutes", "getMinutes-UwyO8pc$annotations", "getMinutes-UwyO8pc", "hours", "getHours-UwyO8pc$annotations", "getHours-UwyO8pc", "days", "getDays-UwyO8pc$annotations", "getDays-UwyO8pc", "parse", _UrlKt.FRAGMENT_ENCODE_SET, "parse-UwyO8pc", "(Ljava/lang/String;)J", "parseIsoString", "parseIsoString-UwyO8pc", "parseOrNull", "parseOrNull-FghU774", "parseIsoStringOrNull", "parseIsoStringOrNull-FghU774", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nDuration.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Duration.kt\nkotlin/time/Duration$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Duration.kt\nkotlin/time/DurationKt\n*L\n1#1,1613:1\n1#2:1614\n1449#3:1615\n1449#3:1616\n*S KotlinDebug\n*F\n+ 1 Duration.kt\nkotlin/time/Duration$Companion\n*L\n337#1:1615\n347#1:1616\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        /* renamed from: getDays-UwyO8pc, reason: not valid java name */
        private final long m3422getDaysUwyO8pc(double r1) {
            return DurationKt.toDuration(r1, DurationUnit.DAYS);
        }

        /* renamed from: getDays-UwyO8pc, reason: not valid java name */
        private final long m3423getDaysUwyO8pc(int r1) {
            return DurationKt.toDuration(r1, DurationUnit.DAYS);
        }

        /* renamed from: getDays-UwyO8pc, reason: not valid java name */
        private final long m3424getDaysUwyO8pc(long r1) {
            return DurationKt.toDuration(r1, DurationUnit.DAYS);
        }

        @InlineOnly
        /* renamed from: getDays-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m3425getDaysUwyO8pc$annotations(double r0) {
        }

        @InlineOnly
        /* renamed from: getDays-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m3426getDaysUwyO8pc$annotations(int r0) {
        }

        @InlineOnly
        /* renamed from: getDays-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m3427getDaysUwyO8pc$annotations(long r0) {
        }

        /* renamed from: getHours-UwyO8pc, reason: not valid java name */
        private final long m3428getHoursUwyO8pc(double r1) {
            return DurationKt.toDuration(r1, DurationUnit.HOURS);
        }

        /* renamed from: getHours-UwyO8pc, reason: not valid java name */
        private final long m3429getHoursUwyO8pc(int r1) {
            return DurationKt.toDuration(r1, DurationUnit.HOURS);
        }

        /* renamed from: getHours-UwyO8pc, reason: not valid java name */
        private final long m3430getHoursUwyO8pc(long r1) {
            return DurationKt.toDuration(r1, DurationUnit.HOURS);
        }

        @InlineOnly
        /* renamed from: getHours-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m3431getHoursUwyO8pc$annotations(double r0) {
        }

        @InlineOnly
        /* renamed from: getHours-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m3432getHoursUwyO8pc$annotations(int r0) {
        }

        @InlineOnly
        /* renamed from: getHours-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m3433getHoursUwyO8pc$annotations(long r0) {
        }

        /* renamed from: getINVALID-UwyO8pc$kotlin_stdlib$annotations, reason: not valid java name */
        public static /* synthetic */ void m3434getINVALIDUwyO8pc$kotlin_stdlib$annotations() {
        }

        /* renamed from: getMicroseconds-UwyO8pc, reason: not valid java name */
        private final long m3435getMicrosecondsUwyO8pc(double r1) {
            return DurationKt.toDuration(r1, DurationUnit.MICROSECONDS);
        }

        /* renamed from: getMicroseconds-UwyO8pc, reason: not valid java name */
        private final long m3436getMicrosecondsUwyO8pc(int r1) {
            return DurationKt.toDuration(r1, DurationUnit.MICROSECONDS);
        }

        /* renamed from: getMicroseconds-UwyO8pc, reason: not valid java name */
        private final long m3437getMicrosecondsUwyO8pc(long r1) {
            return DurationKt.toDuration(r1, DurationUnit.MICROSECONDS);
        }

        @InlineOnly
        /* renamed from: getMicroseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m3438getMicrosecondsUwyO8pc$annotations(double r0) {
        }

        @InlineOnly
        /* renamed from: getMicroseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m3439getMicrosecondsUwyO8pc$annotations(int r0) {
        }

        @InlineOnly
        /* renamed from: getMicroseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m3440getMicrosecondsUwyO8pc$annotations(long r0) {
        }

        /* renamed from: getMilliseconds-UwyO8pc, reason: not valid java name */
        private final long m3441getMillisecondsUwyO8pc(double r1) {
            return DurationKt.toDuration(r1, DurationUnit.MILLISECONDS);
        }

        /* renamed from: getMilliseconds-UwyO8pc, reason: not valid java name */
        private final long m3442getMillisecondsUwyO8pc(int r1) {
            return DurationKt.toDuration(r1, DurationUnit.MILLISECONDS);
        }

        /* renamed from: getMilliseconds-UwyO8pc, reason: not valid java name */
        private final long m3443getMillisecondsUwyO8pc(long r1) {
            return DurationKt.toDuration(r1, DurationUnit.MILLISECONDS);
        }

        @InlineOnly
        /* renamed from: getMilliseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m3444getMillisecondsUwyO8pc$annotations(double r0) {
        }

        @InlineOnly
        /* renamed from: getMilliseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m3445getMillisecondsUwyO8pc$annotations(int r0) {
        }

        @InlineOnly
        /* renamed from: getMilliseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m3446getMillisecondsUwyO8pc$annotations(long r0) {
        }

        /* renamed from: getMinutes-UwyO8pc, reason: not valid java name */
        private final long m3447getMinutesUwyO8pc(double r1) {
            return DurationKt.toDuration(r1, DurationUnit.MINUTES);
        }

        /* renamed from: getMinutes-UwyO8pc, reason: not valid java name */
        private final long m3448getMinutesUwyO8pc(int r1) {
            return DurationKt.toDuration(r1, DurationUnit.MINUTES);
        }

        /* renamed from: getMinutes-UwyO8pc, reason: not valid java name */
        private final long m3449getMinutesUwyO8pc(long r1) {
            return DurationKt.toDuration(r1, DurationUnit.MINUTES);
        }

        @InlineOnly
        /* renamed from: getMinutes-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m3450getMinutesUwyO8pc$annotations(double r0) {
        }

        @InlineOnly
        /* renamed from: getMinutes-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m3451getMinutesUwyO8pc$annotations(int r0) {
        }

        @InlineOnly
        /* renamed from: getMinutes-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m3452getMinutesUwyO8pc$annotations(long r0) {
        }

        /* renamed from: getNanoseconds-UwyO8pc, reason: not valid java name */
        private final long m3453getNanosecondsUwyO8pc(double r1) {
            return DurationKt.toDuration(r1, DurationUnit.NANOSECONDS);
        }

        /* renamed from: getNanoseconds-UwyO8pc, reason: not valid java name */
        private final long m3454getNanosecondsUwyO8pc(int r1) {
            return DurationKt.toDuration(r1, DurationUnit.NANOSECONDS);
        }

        /* renamed from: getNanoseconds-UwyO8pc, reason: not valid java name */
        private final long m3455getNanosecondsUwyO8pc(long r1) {
            return DurationKt.toDuration(r1, DurationUnit.NANOSECONDS);
        }

        @InlineOnly
        /* renamed from: getNanoseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m3456getNanosecondsUwyO8pc$annotations(double r0) {
        }

        @InlineOnly
        /* renamed from: getNanoseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m3457getNanosecondsUwyO8pc$annotations(int r0) {
        }

        @InlineOnly
        /* renamed from: getNanoseconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m3458getNanosecondsUwyO8pc$annotations(long r0) {
        }

        /* renamed from: getSeconds-UwyO8pc, reason: not valid java name */
        private final long m3459getSecondsUwyO8pc(double r1) {
            return DurationKt.toDuration(r1, DurationUnit.SECONDS);
        }

        /* renamed from: getSeconds-UwyO8pc, reason: not valid java name */
        private final long m3460getSecondsUwyO8pc(int r1) {
            return DurationKt.toDuration(r1, DurationUnit.SECONDS);
        }

        /* renamed from: getSeconds-UwyO8pc, reason: not valid java name */
        private final long m3461getSecondsUwyO8pc(long r1) {
            return DurationKt.toDuration(r1, DurationUnit.SECONDS);
        }

        @InlineOnly
        /* renamed from: getSeconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m3462getSecondsUwyO8pc$annotations(double r0) {
        }

        @InlineOnly
        /* renamed from: getSeconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m3463getSecondsUwyO8pc$annotations(int r0) {
        }

        @InlineOnly
        /* renamed from: getSeconds-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m3464getSecondsUwyO8pc$annotations(long r0) {
        }

        /* renamed from: getZERO-UwyO8pc$annotations, reason: not valid java name */
        public static /* synthetic */ void m3465getZEROUwyO8pc$annotations() {
        }

        @ExperimentalTime
        public final double convert(double r1, DurationUnit r3, DurationUnit r4) {
            return DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(r1, r3, r4);
        }

        /* renamed from: fromRawValue-UwyO8pc$kotlin_stdlib, reason: not valid java name */
        public final long m3466fromRawValueUwyO8pc$kotlin_stdlib(long r7) {
            long r6 = Duration.m3375constructorimpl(r7);
            if (DurationJvmKt.getDurationAssertionsEnabled() == true) goto L5;
        L32:
            return r6;
        L5:
            if (Duration.m3370access$isInNanosimpl(r6) == false) goto L13;
            long r0 = Duration.m3369access$getValueimpl(r6);
            if ((-4611686018426999999L) > r0) goto L12;
            if (r0 >= 4611686018427000000L) goto L12;
            return r6;
        L12:
            throw new AssertionError(Duration.m3369access$getValueimpl(r6) + " ns is out of nanoseconds range");
        L13:
            long r02 = Duration.m3369access$getValueimpl(r6);
            if ((-4611686018427387903L) < r02) goto L16;
        L18:
            long r03 = Duration.m3369access$getValueimpl(r6);
            if (r03 != DurationKt.MAX_MILLIS) goto L21;
        L25:
            long r04 = Duration.m3369access$getValueimpl(r6);
            if ((-4611686018426L) > r04) goto L32;
            if (r04 < 4611686018427L) goto L31;
            return r6;
        L31:
            throw new AssertionError(Duration.m3369access$getValueimpl(r6) + " ms is denormalized");
        L21:
            if (r03 == (-4611686018427387903L)) goto L25;
            throw new AssertionError(Duration.m3369access$getValueimpl(r6) + " ms is out of milliseconds range");
        L16:
            if (r02 >= DurationKt.MAX_MILLIS) goto L18;
            goto L18
        }

        /* renamed from: getINFINITE-UwyO8pc, reason: not valid java name */
        public final long m3467getINFINITEUwyO8pc() {
            return Duration.access$getINFINITE$cp();
        }

        /* renamed from: getINVALID-UwyO8pc$kotlin_stdlib, reason: not valid java name */
        public final long m3468getINVALIDUwyO8pc$kotlin_stdlib() {
            return Duration.access$getINVALID$cp();
        }

        /* renamed from: getNEG_INFINITE-UwyO8pc$kotlin_stdlib, reason: not valid java name */
        public final long m3469getNEG_INFINITEUwyO8pc$kotlin_stdlib() {
            return Duration.access$getNEG_INFINITE$cp();
        }

        /* renamed from: getZERO-UwyO8pc, reason: not valid java name */
        public final long m3470getZEROUwyO8pc() {
            return Duration.access$getZERO$cp();
        }

        /* renamed from: parse-UwyO8pc, reason: not valid java name */
        public final long m3471parseUwyO8pc(String r5) {
            long r0 = DurationKt.parseDuration$default(r5, false, false, 4, null);     // Catch: IllegalArgumentException -> L8
            if (Duration.m3380equalsimpl0(r0, Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib()) == true) goto L7;
            return r0;
        L7:
            throw new IllegalStateException("invariant failed");     // Catch: IllegalArgumentException -> L8
        L8:
            e = move-exception;
            throw new IllegalArgumentException("Invalid duration string format: '" + r5 + "'.", e);
        }

        /* renamed from: parseIsoString-UwyO8pc, reason: not valid java name */
        public final long m3472parseIsoStringUwyO8pc(String r5) {
            long r0 = DurationKt.parseDuration$default(r5, true, false, 4, null);     // Catch: IllegalArgumentException -> L8
            if (Duration.m3380equalsimpl0(r0, Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib()) == true) goto L7;
            return r0;
        L7:
            throw new IllegalStateException("invariant failed");     // Catch: IllegalArgumentException -> L8
        L8:
            e = move-exception;
            throw new IllegalArgumentException("Invalid ISO duration string format: '" + r5 + "'.", e);
        }

        /* renamed from: parseIsoStringOrNull-FghU774, reason: not valid java name */
        public final Duration m3473parseIsoStringOrNullFghU774(String r3) {
            long r2 = DurationKt.access$parseDuration(r3, true, false);
            if (Duration.m3380equalsimpl0(r2, Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib()) == false) goto L7;
            return null;
        L7:
            return Duration.m3373boximpl(r2);
        }

        /* renamed from: parseOrNull-FghU774, reason: not valid java name */
        public final Duration m3474parseOrNullFghU774(String r3) {
            long r2 = DurationKt.access$parseDuration(r3, false, false);
            if (Duration.m3380equalsimpl0(r2, Duration.Companion.m3468getINVALIDUwyO8pc$kotlin_stdlib()) == false) goto L7;
            return null;
        L7:
            return Duration.m3373boximpl(r2);
        }
    }

    static {
        Companion = new Companion(null);
        ZERO = m3375constructorimpl(0);
        INFINITE = DurationKt.access$durationOfMillis(DurationKt.MAX_MILLIS);
        NEG_INFINITE = DurationKt.access$durationOfMillis(-4611686018427387903L);
        INVALID = m3375constructorimpl(INVALID_RAW_VALUE);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Don't call this constructor directly.")
    private /* synthetic */ Duration(long r1) {
        this.rawValue = r1;
    }

    public static final /* synthetic */ long access$getINFINITE$cp() {
        return INFINITE;
    }

    public static final /* synthetic */ long access$getINVALID$cp() {
        return INVALID;
    }

    public static final /* synthetic */ long access$getNEG_INFINITE$cp() {
        return NEG_INFINITE;
    }

    /* renamed from: access$getValue-impl, reason: not valid java name */
    public static final /* synthetic */ long m3369access$getValueimpl(long r0) {
        return m3395getValueimpl(r0);
    }

    public static final /* synthetic */ long access$getZERO$cp() {
        return ZERO;
    }

    /* renamed from: access$isInNanos-impl, reason: not valid java name */
    public static final /* synthetic */ boolean m3370access$isInNanosimpl(long r0) {
        return m3399isInNanosimpl(r0);
    }

    /* renamed from: addValuesMixedRanges-UwyO8pc, reason: not valid java name */
    private static final long m3371addValuesMixedRangesUwyO8pc(long r2, long r4, long r6) {
        long r22 = DurationKt.access$nanosToMillis(r6);
        long r42 = DurationKt.access$addMillisWithoutOverflow(r4, r22);
        if ((-4611686018426L) > r42) goto L9;
        if (r42 >= 4611686018427L) goto L9;
        long r62 = r6 - DurationKt.access$millisToNanos(r22);
        return DurationKt.access$durationOfNanos(DurationKt.access$millisToNanos(r42) + r62);
    L9:
        return DurationKt.access$durationOfMillis(r42);
    }

    /* renamed from: appendFractional-impl, reason: not valid java name */
    private static final void m3372appendFractionalimpl(long r1, StringBuilder r3, int r4, int r5, int r6, String r7, boolean r8) {
        r3.append(r4);
        if (r5 == 0) goto L17;
        r3.append('.');
        String r12 = StringsKt.padStart(String.valueOf(r5), r6, '0');
        int r52 = -1;
        int r42 = r12.length() - 1;
        if (r42 < 0) goto L12;
    L6:
        int r62 = r42 - 1;
        if (r12.charAt(r42) != '0') goto L8;
        if (r62 < 0) goto L12;
        r42 = r62;
        goto L6
    L8:
        r52 = r42;
    L12:
        int r2 = r52 + 1;
        if (r8 == true) goto L16;
        if (r2 >= 3) goto L16;
        r3.append(r12, 0, r2);
    L16:
        r3.append(r12, 0, ((r52 + 3) / 3) * 3);
    L17:
        r3.append(r7);
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Duration m3373boximpl(long r1) {
        return new Duration(r1);
    }

    /* renamed from: compareTo-LRDsOJo, reason: not valid java name */
    public static int m3374compareToLRDsOJo(long r4, long r6) {
        long r0 = r4 ^ r6;
        if (r0 < 0) goto L13;
        if ((((int) r0) & 1) == 0) goto L13;
        int r02 = (((int) r4) & 1) - (((int) r6) & 1);
        if (m3401isNegativeimpl(r4) == true) goto L10;
        return r02;
    L10:
        return -r02;
    L13:
        return Intrinsics.compare(r4, r6);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Don't call this constructor directly.")
    /* renamed from: constructor-impl, reason: not valid java name */
    public static long m3375constructorimpl(long r0) {
        return r0;
    }

    /* renamed from: div-LRDsOJo, reason: not valid java name */
    public static final double m3376divLRDsOJo(long r2, long r4) {
        DurationUnit r0 = (DurationUnit) ComparisonsKt.maxOf(m3393getStorageUnitimpl(r2), m3393getStorageUnitimpl(r4));
        return m3411toDoubleimpl(r2, r0) / m3411toDoubleimpl(r4, r0);
    }

    /* renamed from: div-UwyO8pc, reason: not valid java name */
    public static final long m3377divUwyO8pc(long r3, double r5) {
        int r0 = MathKt.roundToInt(r5);
        if (r0 != r5) goto L7;
        if (r0 == 0) goto L7;
        return m3378divUwyO8pc(r3, r0);
    L7:
        DurationUnit r02 = m3393getStorageUnitimpl(r3);
        return DurationKt.toDuration(m3411toDoubleimpl(r3, r02) / r5, r02);
    }

    /* renamed from: div-UwyO8pc, reason: not valid java name */
    public static final long m3378divUwyO8pc(long r6, int r8) {
        if (r8 != 0) goto L14;
        if (m3402isPositiveimpl(r6) == false) goto L8;
        return INFINITE;
    L8:
        if (m3401isNegativeimpl(r6) == true) goto L10;
        f$$ExternalSyntheticBUOutline1.m("Dividing zero duration by zero yields an undefined result.");
        return 0;
    L10:
        return NEG_INFINITE;
    L14:
        if (m3399isInNanosimpl(r6) == false) goto L18;
        return DurationKt.access$durationOfNanos(m3395getValueimpl(r6) / r8);
    L18:
        if (m3400isInfiniteimpl(r6) == true) goto L20;
        long r2 = r8;
        long r0 = m3395getValueimpl(r6) / r2;
        if ((-4611686018426L) > r0) goto L28;
        if (r0 >= 4611686018427L) goto L28;
        return DurationKt.access$durationOfNanos(DurationKt.access$millisToNanos(r0) + (DurationKt.access$millisToNanos(m3395getValueimpl(r6) - (r0 * r2)) / r2));
    L28:
        return DurationKt.access$durationOfMillis(r0);
    L20:
        return m3406timesUwyO8pc(r6, MathKt.getSign(r8));
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m3379equalsimpl(long r4, Object r6) {
        if ((r6 instanceof Duration) == true) goto L6;
        return false;
    L6:
        if (r4 == ((Duration) r6).m3421unboximpl()) goto L8;
        return false;
    L8:
        return true;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m3380equalsimpl0(long r0, long r2) {
        if (r0 != r2) goto L6;
        return true;
    L6:
        return false;
    }

    /* renamed from: getAbsoluteValue-UwyO8pc, reason: not valid java name */
    public static final long m3381getAbsoluteValueUwyO8pc(long r1) {
        if (m3401isNegativeimpl(r1) == true) goto L5;
        return r1;
    L5:
        return m3419unaryMinusUwyO8pc(r1);
    }

    @PublishedApi
    public static /* synthetic */ void getHoursComponent$annotations() {
    }

    /* renamed from: getHoursComponent-impl, reason: not valid java name */
    public static final int m3382getHoursComponentimpl(long r2) {
        if (m3400isInfiniteimpl(r2) == false) goto L7;
        return 0;
    L7:
        return (int) (m3384getInWholeHoursimpl(r2) % 24);
    }

    /* renamed from: getInWholeDays-impl, reason: not valid java name */
    public static final long m3383getInWholeDaysimpl(long r1) {
        return m3414toLongimpl(r1, DurationUnit.DAYS);
    }

    /* renamed from: getInWholeHours-impl, reason: not valid java name */
    public static final long m3384getInWholeHoursimpl(long r1) {
        return m3414toLongimpl(r1, DurationUnit.HOURS);
    }

    /* renamed from: getInWholeMicroseconds-impl, reason: not valid java name */
    public static final long m3385getInWholeMicrosecondsimpl(long r1) {
        return m3414toLongimpl(r1, DurationUnit.MICROSECONDS);
    }

    /* renamed from: getInWholeMilliseconds-impl, reason: not valid java name */
    public static final long m3386getInWholeMillisecondsimpl(long r1) {
        if (m3398isInMillisimpl(r1) == false) goto L9;
        if (m3397isFiniteimpl(r1) == false) goto L9;
        return m3395getValueimpl(r1);
    L9:
        return m3414toLongimpl(r1, DurationUnit.MILLISECONDS);
    }

    /* renamed from: getInWholeMinutes-impl, reason: not valid java name */
    public static final long m3387getInWholeMinutesimpl(long r1) {
        return m3414toLongimpl(r1, DurationUnit.MINUTES);
    }

    /* renamed from: getInWholeNanoseconds-impl, reason: not valid java name */
    public static final long m3388getInWholeNanosecondsimpl(long r2) {
        long r0 = m3395getValueimpl(r2);
        if (m3399isInNanosimpl(r2) == false) goto L6;
        return r0;
    L6:
        if (r0 <= 9223372036854L) goto L10;
        return LongCompanionObject.MAX_VALUE;
    L10:
        if (r0 >= (-9223372036854L)) goto L14;
        return Long.MIN_VALUE;
    L14:
        return DurationKt.access$millisToNanos(r0);
    }

    /* renamed from: getInWholeSeconds-impl, reason: not valid java name */
    public static final long m3389getInWholeSecondsimpl(long r1) {
        return m3414toLongimpl(r1, DurationUnit.SECONDS);
    }

    @PublishedApi
    public static /* synthetic */ void getMinutesComponent$annotations() {
    }

    /* renamed from: getMinutesComponent-impl, reason: not valid java name */
    public static final int m3390getMinutesComponentimpl(long r2) {
        if (m3400isInfiniteimpl(r2) == false) goto L7;
        return 0;
    L7:
        return (int) (m3387getInWholeMinutesimpl(r2) % 60);
    }

    @PublishedApi
    public static /* synthetic */ void getNanosecondsComponent$annotations() {
    }

    /* renamed from: getNanosecondsComponent-impl, reason: not valid java name */
    public static final int m3391getNanosecondsComponentimpl(long r2) {
        if (m3400isInfiniteimpl(r2) == false) goto L7;
        return 0;
    L7:
        if (m3398isInMillisimpl(r2) == false) goto L11;
        long r22 = DurationKt.access$millisToNanos(m3395getValueimpl(r2) % 1000);
    L10:
        return (int) r22;
    L11:
        r22 = m3395getValueimpl(r2) % 1000000000;
        goto L10
    }

    @PublishedApi
    public static /* synthetic */ void getSecondsComponent$annotations() {
    }

    /* renamed from: getSecondsComponent-impl, reason: not valid java name */
    public static final int m3392getSecondsComponentimpl(long r2) {
        if (m3400isInfiniteimpl(r2) == false) goto L7;
        return 0;
    L7:
        return (int) (m3389getInWholeSecondsimpl(r2) % 60);
    }

    /* renamed from: getStorageUnit-impl, reason: not valid java name */
    private static final DurationUnit m3393getStorageUnitimpl(long r0) {
        if (m3399isInNanosimpl(r0) == false) goto L7;
        return DurationUnit.NANOSECONDS;
    L7:
        return DurationUnit.MILLISECONDS;
    }

    /* renamed from: getUnitDiscriminator-impl, reason: not valid java name */
    private static final int m3394getUnitDiscriminatorimpl(long r0) {
        return ((int) r0) & 1;
    }

    /* renamed from: getValue-impl, reason: not valid java name */
    private static final long m3395getValueimpl(long r1) {
        return r1 >> 1;
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m3396hashCodeimpl(long r0) {
        return Long.hashCode(r0);
    }

    /* renamed from: isFinite-impl, reason: not valid java name */
    public static final boolean m3397isFiniteimpl(long r0) {
        return !m3400isInfiniteimpl(r0);
    }

    /* renamed from: isInMillis-impl, reason: not valid java name */
    private static final boolean m3398isInMillisimpl(long r0) {
        if ((((int) r0) & 1) != 1) goto L5;
        return true;
    L5:
        return false;
    }

    /* renamed from: isInNanos-impl, reason: not valid java name */
    private static final boolean m3399isInNanosimpl(long r0) {
        if ((((int) r0) & 1) != 0) goto L5;
        return true;
    L5:
        return false;
    }

    /* renamed from: isInfinite-impl, reason: not valid java name */
    public static final boolean m3400isInfiniteimpl(long r2) {
        if (r2 != INFINITE) goto L5;
        return true;
    L5:
        if (r2 == NEG_INFINITE) goto L11;
        return false;
    L11:
        return true;
    }

    /* renamed from: isNegative-impl, reason: not valid java name */
    public static final boolean m3401isNegativeimpl(long r2) {
        if (r2 >= 0) goto L6;
        return true;
    L6:
        return false;
    }

    /* renamed from: isPositive-impl, reason: not valid java name */
    public static final boolean m3402isPositiveimpl(long r2) {
        if (r2 <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    /* renamed from: minus-LRDsOJo, reason: not valid java name */
    public static final long m3403minusLRDsOJo(long r0, long r2) {
        return m3404plusLRDsOJo(r0, m3419unaryMinusUwyO8pc(r2));
    }

    /* renamed from: plus-LRDsOJo, reason: not valid java name */
    public static final long m3404plusLRDsOJo(long r7, long r9) {
        if ((((int) r7) & 1) != (((int) r9) & 1)) goto L22;
        if (m3399isInNanosimpl(r7) == true) goto L7;
        long r72 = DurationKt.access$addMillisWithoutOverflow(m3395getValueimpl(r7), m3395getValueimpl(r9));
        if (r72 != INVALID_RAW_VALUE) goto L11;
        f$$ExternalSyntheticBUOutline1.m("Summing infinite durations of different signs yields an undefined result.");
        return 0;
    L11:
        if (r72 == DurationKt.MAX_MILLIS) goto L18;
        if (r72 == (-4611686018427387903L)) goto L18;
        return DurationKt.access$durationOfMillisNormalized(r72);
    L18:
        return DurationKt.access$durationOfMillis(r72);
    L7:
        return DurationKt.access$durationOfNanosNormalized(m3395getValueimpl(r7) + m3395getValueimpl(r9));
    L22:
        if (m3398isInMillisimpl(r7) == false) goto L26;
        return m3371addValuesMixedRangesUwyO8pc(r7, m3395getValueimpl(r7), m3395getValueimpl(r9));
    L26:
        return m3371addValuesMixedRangesUwyO8pc(r7, m3395getValueimpl(r9), m3395getValueimpl(r7));
    }

    /* renamed from: times-UwyO8pc, reason: not valid java name */
    public static final long m3405timesUwyO8pc(long r3, double r5) {
        int r0 = MathKt.roundToInt(r5);
        if (r0 == r5) goto L5;
        DurationUnit r02 = m3393getStorageUnitimpl(r3);
        return DurationKt.toDuration(m3411toDoubleimpl(r3, r02) * r5, r02);
    L5:
        return m3406timesUwyO8pc(r3, r0);
    }

    /* renamed from: times-UwyO8pc, reason: not valid java name */
    public static final long m3406timesUwyO8pc(long r12, int r14) {
        if (m3400isInfiniteimpl(r12) == false) goto L11;
        if (r14 == 0) goto L9;
        if (r14 <= 0) goto L8;
        return r12;
    L8:
        return m3419unaryMinusUwyO8pc(r12);
    L9:
        f$$ExternalSyntheticBUOutline1.m("Multiplying infinite duration by zero yields an undefined result.");
        return 0;
    L11:
        if (r14 == 0) goto L13;
        long r0 = m3395getValueimpl(r12);
        long r2 = r14;
        long r4 = r0 * r2;
        if (m3399isInNanosimpl(r12) == false) goto L39;
        if ((-2147483647L) > r0) goto L23;
        if (r0 >= 2147483648L) goto L23;
        return DurationKt.access$durationOfNanos(r4);
    L23:
        if ((r4 / r2) == r0) goto L25;
        long r122 = DurationKt.access$nanosToMillis(r0);
        long r10 = r122 * r2;
        long r42 = DurationKt.access$nanosToMillis((r0 - DurationKt.access$millisToNanos(r122)) * r2) + r10;
        if ((r10 / r2) != r122) goto L33;
        if ((r42 ^ r10) < 0) goto L33;
        return DurationKt.access$durationOfMillis(RangesKt.coerceIn(r42, new LongRange(-4611686018427387903L, DurationKt.MAX_MILLIS)));
    L33:
        if ((MathKt.getSign(r0) * MathKt.getSign(r14)) <= 0) goto L37;
        return INFINITE;
    L37:
        return NEG_INFINITE;
    L25:
        return DurationKt.access$durationOfNanosNormalized(r4);
    L39:
        if ((r4 / r2) != r0) goto L43;
        return DurationKt.access$durationOfMillis(RangesKt.coerceIn(r4, new LongRange(-4611686018427387903L, DurationKt.MAX_MILLIS)));
    L43:
        if ((MathKt.getSign(r0) * MathKt.getSign(r14)) <= 0) goto L47;
        return INFINITE;
    L47:
        return NEG_INFINITE;
    L13:
        return ZERO;
    }

    /* renamed from: toComponents-impl, reason: not valid java name */
    public static final <T> T m3407toComponentsimpl(long r2, Function2<? super Long, ? super Integer, ? extends T> r4) {
        return r4.invoke(Long.valueOf(m3389getInWholeSecondsimpl(r2)), Integer.valueOf(m3391getNanosecondsComponentimpl(r2)));
    }

    /* renamed from: toComponents-impl, reason: not valid java name */
    public static final <T> T m3408toComponentsimpl(long r2, Function3<? super Long, ? super Integer, ? super Integer, ? extends T> r4) {
        return r4.invoke(Long.valueOf(m3387getInWholeMinutesimpl(r2)), Integer.valueOf(m3392getSecondsComponentimpl(r2)), Integer.valueOf(m3391getNanosecondsComponentimpl(r2)));
    }

    /* renamed from: toComponents-impl, reason: not valid java name */
    public static final <T> T m3409toComponentsimpl(long r3, Function4<? super Long, ? super Integer, ? super Integer, ? super Integer, ? extends T> r5) {
        return r5.invoke(Long.valueOf(m3384getInWholeHoursimpl(r3)), Integer.valueOf(m3390getMinutesComponentimpl(r3)), Integer.valueOf(m3392getSecondsComponentimpl(r3)), Integer.valueOf(m3391getNanosecondsComponentimpl(r3)));
    }

    /* renamed from: toComponents-impl, reason: not valid java name */
    public static final <T> T m3410toComponentsimpl(long r8, Function5<? super Long, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> r10) {
        return r10.invoke(Long.valueOf(m3383getInWholeDaysimpl(r8)), Integer.valueOf(m3382getHoursComponentimpl(r8)), Integer.valueOf(m3390getMinutesComponentimpl(r8)), Integer.valueOf(m3392getSecondsComponentimpl(r8)), Integer.valueOf(m3391getNanosecondsComponentimpl(r8)));
    }

    /* renamed from: toDouble-impl, reason: not valid java name */
    public static final double m3411toDoubleimpl(long r2, DurationUnit r4) {
        if (r2 != INFINITE) goto L7;
        return Double.POSITIVE_INFINITY;
    L7:
        if (r2 != NEG_INFINITE) goto L11;
        return Double.NEGATIVE_INFINITY;
    L11:
        return DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(m3395getValueimpl(r2), m3393getStorageUnitimpl(r2), r4);
    }

    /* renamed from: toInt-impl, reason: not valid java name */
    public static final int m3412toIntimpl(long r6, DurationUnit r8) {
        return (int) RangesKt.coerceIn(m3414toLongimpl(r6, r8), -2147483648L, 2147483647L);
    }

    /* renamed from: toIsoString-impl, reason: not valid java name */
    public static final String m3413toIsoStringimpl(long r10) {
        StringBuilder r2 = new StringBuilder();
        if (m3401isNegativeimpl(r10) == false) goto L5;
        r2.append(SignatureVisitor.SUPER);
    L5:
        r2.append("PT");
        long r0 = m3381getAbsoluteValueUwyO8pc(r10);
        long r3 = m3384getInWholeHoursimpl(r0);
        int r5 = m3390getMinutesComponentimpl(r0);
        int r32 = m3392getSecondsComponentimpl(r0);
        int r4 = m3391getNanosecondsComponentimpl(r0);
        if (m3400isInfiniteimpl(r10) == false) goto L8;
        long r02 = 9999999999999L;
    L9:
        boolean r7 = false;
        if (r02 == 0) goto L12;
        boolean r6 = true;
    L13:
        if (r32 != 0) goto L17;
        if (r4 != 0) goto L17;
        boolean r9 = false;
    L18:
        if (r5 != 0) goto L21;
        if (r9 == false) goto L22;
        if (r6 == true) goto L21;
    L22:
        if (r6 == false) goto L24;
        r2.append(r02);
        r2.append('H');
    L24:
        if (r7 == false) goto L26;
        r2.append(r5);
        r2.append('M');
    L26:
        if (r9 == true) goto L29;
        if (r6 == true) goto L31;
        if (r7 == false) goto L29;
    L31:
        return r2.toString();
    L29:
        m3372appendFractionalimpl(r10, r2, r32, r4, 9, "S", true);
    L21:
        r7 = true;
    L17:
        r9 = true;
        goto L18
    L12:
        r6 = false;
        goto L13
    L8:
        r02 = r3;
        goto L9
    }

    /* renamed from: toLong-impl, reason: not valid java name */
    public static final long m3414toLongimpl(long r2, DurationUnit r4) {
        if (r2 != INFINITE) goto L7;
        return LongCompanionObject.MAX_VALUE;
    L7:
        if (r2 != NEG_INFINITE) goto L11;
        return Long.MIN_VALUE;
    L11:
        return DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(m3395getValueimpl(r2), m3393getStorageUnitimpl(r2), r4);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m3415toStringimpl(long r14) {
        if (r14 != 0) goto L7;
        return "0s";
    L7:
        if (r14 != INFINITE) goto L11;
        return "Infinity";
    L11:
        if (r14 != NEG_INFINITE) goto L14;
        return "-Infinity";
    L14:
        boolean r2 = m3401isNegativeimpl(r14);
        StringBuilder r5 = new StringBuilder();
        if (r2 == false) goto L17;
        r5.append(SignatureVisitor.SUPER);
    L17:
        long r3 = m3381getAbsoluteValueUwyO8pc(r14);
        long r6 = m3383getInWholeDaysimpl(r3);
        int r8 = m3382getHoursComponentimpl(r3);
        int r9 = m3390getMinutesComponentimpl(r3);
        int r62 = m3392getSecondsComponentimpl(r3);
        int r7 = m3391getNanosecondsComponentimpl(r3);
        int r1 = 0;
        if (r6 == 0) goto L20;
        boolean r0 = true;
    L21:
        if (r8 == 0) goto L23;
        boolean r32 = true;
    L24:
        if (r9 == 0) goto L26;
        boolean r4 = true;
    L27:
        if (r62 != 0) goto L31;
        if (r7 != 0) goto L31;
        boolean r13 = false;
    L32:
        if (r0 == false) goto L35;
        r5.append(r6);
        r5.append('d');
        r1 = 1;
    L35:
        if (r32 == true) goto L39;
        if (r0 == false) goto L43;
        if (r4 == true) goto L39;
        if (r13 == true) goto L39;
    L43:
        if (r4 == true) goto L47;
        if (r13 == false) goto L51;
        if (r32 == true) goto L47;
        if (r0 == true) goto L47;
    L51:
        if (r13 == false) goto L69;
        int r11 = r1 + 1;
        if (r1 <= 0) goto L55;
        r5.append(' ');
    L55:
        if (r62 != 0) goto L67;
        if (r0 == true) goto L67;
        if (r32 == true) goto L67;
        if (r4 == true) goto L67;
        if (r7 < 1000000) goto L64;
        m3372appendFractionalimpl(r14, r5, r7 / DurationKt.NANOS_IN_MILLIS, r7 % DurationKt.NANOS_IN_MILLIS, 6, "ms", false);
    L68:
        r1 = r11;
        goto L69
    L64:
        if (r7 < 1000) goto L66;
        m3372appendFractionalimpl(r14, r5, r7 / 1000, r7 % 1000, 3, "us", false);
        goto L68
    L66:
        r5.append(r7);
        r5.append("ns");
    L67:
        m3372appendFractionalimpl(r14, r5, r62, r7, 9, "s", false);
    L69:
        if (r2 == false) goto L73;
        if (r1 <= 1) goto L73;
        r5.insert(1, '(').append(')');
    L73:
        return r5.toString();
    L47:
        int r82 = r1 + 1;
        if (r1 <= 0) goto L50;
        r5.append(' ');
    L50:
        r5.append(r9);
        r5.append('m');
        r1 = r82;
    L39:
        int r112 = r1 + 1;
        if (r1 <= 0) goto L42;
        r5.append(' ');
    L42:
        r5.append(r8);
        r5.append('h');
        r1 = r112;
    L31:
        r13 = true;
        goto L32
    L26:
        r4 = false;
        goto L27
    L23:
        r32 = false;
        goto L24
    L20:
        r0 = false;
        goto L21
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static final String m3416toStringimpl(long r2, DurationUnit r4, int r5) {
        if (r5 < 0) goto L9;
        double r22 = m3411toDoubleimpl(r2, r4);
        if (Double.isInfinite(r22) == false) goto L8;
        return String.valueOf(r22);
    L8:
        return DurationJvmKt.formatToExactDecimals(r22, RangesKt.coerceAtMost(r5, 12)) + DurationUnitKt__DurationUnitKt.shortName(r4);
    L9:
        Utf8$$ExternalSyntheticBUOutline1.m("decimals must be not negative, but was ", r5);
        return null;
    }

    /* renamed from: toString-impl$default, reason: not valid java name */
    public static /* synthetic */ String m3417toStringimpl$default(long r0, DurationUnit r2, int r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r3 = 0;
    L6:
        return m3416toStringimpl(r0, r2, r3);
    }

    /* renamed from: truncateTo-UwyO8pc$kotlin_stdlib, reason: not valid java name */
    public static final long m3418truncateToUwyO8pc$kotlin_stdlib(long r5, DurationUnit r7) {
        DurationUnit r0 = m3393getStorageUnitimpl(r5);
        if (r7.compareTo(r0) > 0) goto L5;
        return r5;
    L5:
        if (m3400isInfiniteimpl(r5) == true) goto L10;
        long r1 = DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(1, r7, r0);
        return DurationKt.toDuration(m3395getValueimpl(r5) - (m3395getValueimpl(r5) % r1), r0);
    L10:
        return r5;
    }

    /* renamed from: unaryMinus-UwyO8pc, reason: not valid java name */
    public static final long m3419unaryMinusUwyO8pc(long r2) {
        return DurationKt.access$durationOf(-m3395getValueimpl(r2), ((int) r2) & 1);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Duration r3) {
        return m3420compareToLRDsOJo(r3.m3421unboximpl());
    }

    /* renamed from: compareTo-LRDsOJo, reason: not valid java name */
    public int m3420compareToLRDsOJo(long r3) {
        return m3374compareToLRDsOJo(this.rawValue, r3);
    }

    public boolean equals(Object r3) {
        return m3379equalsimpl(this.rawValue, r3);
    }

    public int hashCode() {
        return m3396hashCodeimpl(this.rawValue);
    }

    public String toString() {
        return m3415toStringimpl(this.rawValue);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m3421unboximpl() {
        return this.rawValue;
    }
}
