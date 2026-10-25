package kotlin.collections;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a,\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0006\u0012\u0002\b\u00030\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0086\u0080\u0004\u001aE\u0010\u0006\u001a\u0002H\u0007\"\u0010\b\u0000\u0010\u0007*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\b\"\u0004\b\u0001\u0010\u0002*\u0006\u0012\u0002\b\u00030\u00032\u0006\u0010\t\u001a\u0002H\u00072\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0087\u0080\b¢\u0006\u0002\u0010\n\u001a\u001a\u0010\u000b\u001a\u00020\f\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u000eH\u0086\u0080\u0004\u001a*\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0010\"\u000e\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u0011*\b\u0012\u0004\u0012\u0002H\r0\u0003H\u0086\u0080\u0004\u001a<\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0010\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u00032\u001a\u0010\u0012\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\r0\u0013j\n\u0012\u0006\b\u0000\u0012\u0002H\r`\u0014H\u0086\u0080\u0004\u001a\u001b\u0010\u0015\u001a\u0004\u0018\u00010\u0016*\b\u0012\u0004\u0012\u00020\u00160\u0003H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0017\u001a\u001b\u0010\u0015\u001a\u0004\u0018\u00010\u0018*\b\u0012\u0004\u0012\u00020\u00180\u0003H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0019\u001a+\u0010\u0015\u001a\u0004\u0018\u0001H\r\"\u000e\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u0011*\b\u0012\u0004\u0012\u0002H\r0\u0003H\u0087\u0080\u0004¢\u0006\u0002\u0010\u001a\u001aH\u0010\u001b\u001a\u0004\u0018\u0001H\r\"\u0004\b\u0000\u0010\r\"\u000e\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0011*\b\u0012\u0004\u0012\u0002H\r0\u00032\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u00020\u001dH\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001a=\u0010\u001f\u001a\u0004\u0018\u0001H\r\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u00032\u001a\u0010\u0012\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\r0\u0013j\n\u0012\u0006\b\u0000\u0012\u0002H\r`\u0014H\u0087\u0080\u0004¢\u0006\u0002\u0010 \u001a\u001b\u0010!\u001a\u0004\u0018\u00010\u0016*\b\u0012\u0004\u0012\u00020\u00160\u0003H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0017\u001a\u001b\u0010!\u001a\u0004\u0018\u00010\u0018*\b\u0012\u0004\u0012\u00020\u00180\u0003H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0019\u001a+\u0010!\u001a\u0004\u0018\u0001H\r\"\u000e\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u0011*\b\u0012\u0004\u0012\u0002H\r0\u0003H\u0087\u0080\u0004¢\u0006\u0002\u0010\u001a\u001aH\u0010\"\u001a\u0004\u0018\u0001H\r\"\u0004\b\u0000\u0010\r\"\u000e\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0011*\b\u0012\u0004\u0012\u0002H\r0\u00032\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u00020\u001dH\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001a=\u0010#\u001a\u0004\u0018\u0001H\r\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u00032\u001a\u0010\u0012\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\r0\u0013j\n\u0012\u0006\b\u0000\u0012\u0002H\r`\u0014H\u0087\u0080\u0004¢\u0006\u0002\u0010 \u001a6\u0010$\u001a\u00020%\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u00032\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u00020%0\u001dH\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\b&\u001a6\u0010$\u001a\u00020'\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u00032\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u00020'0\u001dH\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\b(\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006)"}, d2 = {"filterIsInstance", _UrlKt.FRAGMENT_ENCODE_SET, "R", _UrlKt.FRAGMENT_ENCODE_SET, "klass", "Ljava/lang/Class;", "filterIsInstanceTo", "C", _UrlKt.FRAGMENT_ENCODE_SET, "destination", "(Ljava/lang/Iterable;Ljava/util/Collection;Ljava/lang/Class;)Ljava/util/Collection;", "reverse", _UrlKt.FRAGMENT_ENCODE_SET, "T", _UrlKt.FRAGMENT_ENCODE_SET, "toSortedSet", "Ljava/util/SortedSet;", _UrlKt.FRAGMENT_ENCODE_SET, "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "max", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/Iterable;)Ljava/lang/Double;", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/Iterable;)Ljava/lang/Float;", "(Ljava/lang/Iterable;)Ljava/lang/Comparable;", "maxBy", PluginsConstants.Settings.TYPE_SELECTOR, "Lkotlin/Function1;", "(Ljava/lang/Iterable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "maxWith", "(Ljava/lang/Iterable;Ljava/util/Comparator;)Ljava/lang/Object;", "min", "minBy", "minWith", "sumOf", "Ljava/math/BigDecimal;", "sumOfBigDecimal", "Ljava/math/BigInteger;", "sumOfBigInteger", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/collections/CollectionsKt")
@SourceDebugExtension({"SMAP\n_CollectionsJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _CollectionsJvm.kt\nkotlin/collections/CollectionsKt___CollectionsJvmKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,169:1\n2045#2,14:170\n2469#2,14:184\n*S KotlinDebug\n*F\n+ 1 _CollectionsJvm.kt\nkotlin/collections/CollectionsKt___CollectionsJvmKt\n*L\n90#1:170,14\n127#1:184,14\n*E\n"})
/* loaded from: classes.dex */
class CollectionsKt___CollectionsJvmKt extends CollectionsKt__ReversedViewsKt {
    public CollectionsKt___CollectionsJvmKt() {
    }

    public static final <R> List<R> filterIsInstance(Iterable<?> r1, Class<R> r2) {
        return (List) filterIsInstanceTo(r1, new ArrayList(), r2);
    }

    @IgnorableReturnValue
    public static final <C extends Collection<? super R>, R> C filterIsInstanceTo(Iterable<?> r2, C r3, Class<R> r4) {
        Iterator<?> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L8;
        Object r0 = r22.next();
        if (r4.isInstance(r0) == false) goto L4;
        r3.add(r0);
        goto L4
    L8:
        return r3;
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Comparable max(Iterable r0) {
        return CollectionsKt___CollectionsKt.maxOrNull(r0);
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @SinceKotlin(version = "1.1")
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: max, reason: collision with other method in class */
    public static final /* synthetic */ Double m2449max(Iterable r0) {
        return CollectionsKt___CollectionsKt.m2457maxOrNull(r0);
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @SinceKotlin(version = "1.1")
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: max, reason: collision with other method in class */
    public static final /* synthetic */ Float m2450max(Iterable r0) {
        return CollectionsKt___CollectionsKt.m2458maxOrNull(r0);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [T] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ <T, R extends Comparable<? super R>> T maxBy(Iterable<? extends T> r5, Function1<? super T, ? extends R> r6) {
        Iterator<? extends T> r52 = r5.iterator();
        if (r52.hasNext() == true) goto L6;
        return null;
    L6:
        T r0 = r52.next();
        if (r52.hasNext() == true) goto L9;
        return r0;
    L9:
        R r1 = r6.invoke(r0);
    L10:
        T r2 = r52.next();
        R r3 = r6.invoke(r2);
        r0 = r0;
        if (r1.compareTo(r3) >= 0) goto L14;
        r1 = r3;
        r0 = r2;
    L14:
        if (r52.hasNext() == true) goto L10;
        return (T) r0;
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Object maxWith(Iterable r0, Comparator r1) {
        return CollectionsKt___CollectionsKt.maxWithOrNull(r0, r1);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Comparable min(Iterable r0) {
        return CollectionsKt___CollectionsKt.minOrNull(r0);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @SinceKotlin(version = "1.1")
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: min, reason: collision with other method in class */
    public static final /* synthetic */ Double m2451min(Iterable r0) {
        return CollectionsKt___CollectionsKt.m2465minOrNull(r0);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @SinceKotlin(version = "1.1")
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: min, reason: collision with other method in class */
    public static final /* synthetic */ Float m2452min(Iterable r0) {
        return CollectionsKt___CollectionsKt.m2466minOrNull(r0);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [T] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ <T, R extends Comparable<? super R>> T minBy(Iterable<? extends T> r5, Function1<? super T, ? extends R> r6) {
        Iterator<? extends T> r52 = r5.iterator();
        if (r52.hasNext() == true) goto L6;
        return null;
    L6:
        T r0 = r52.next();
        if (r52.hasNext() == true) goto L9;
        return r0;
    L9:
        R r1 = r6.invoke(r0);
    L10:
        T r2 = r52.next();
        R r3 = r6.invoke(r2);
        r0 = r0;
        if (r1.compareTo(r3) <= 0) goto L14;
        r1 = r3;
        r0 = r2;
    L14:
        if (r52.hasNext() == true) goto L10;
        return (T) r0;
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Object minWith(Iterable r0, Comparator r1) {
        return CollectionsKt___CollectionsKt.minWithOrNull(r0, r1);
    }

    public static <T> void reverse(List<T> r0) {
        Collections.reverse(r0);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfBigDecimal")
    @OverloadResolutionByLambdaReturnType
    private static final <T> BigDecimal sumOfBigDecimal(Iterable<? extends T> r2, Function1<? super T, ? extends BigDecimal> r3) {
        BigDecimal r0 = BigDecimal.valueOf(0);
        Iterator<? extends T> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        r0 = r0.add(r3.invoke(r22.next()));
        goto L4
    L6:
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfBigInteger")
    @OverloadResolutionByLambdaReturnType
    private static final <T> BigInteger sumOfBigInteger(Iterable<? extends T> r2, Function1<? super T, ? extends BigInteger> r3) {
        BigInteger r0 = BigInteger.valueOf(0);
        Iterator<? extends T> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        r0 = r0.add(r3.invoke(r22.next()));
        goto L4
    L6:
        return r0;
    }

    public static final <T extends Comparable<? super T>> SortedSet<T> toSortedSet(Iterable<? extends T> r1) {
        return (SortedSet) CollectionsKt___CollectionsKt.toCollection(r1, new TreeSet());
    }

    public static final <T> SortedSet<T> toSortedSet(Iterable<? extends T> r1, Comparator<? super T> r2) {
        return (SortedSet) CollectionsKt___CollectionsKt.toCollection(r1, new TreeSet(r2));
    }
}
