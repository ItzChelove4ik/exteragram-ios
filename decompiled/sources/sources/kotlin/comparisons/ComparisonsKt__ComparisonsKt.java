package kotlin.comparisons;

import c.f$$ExternalSyntheticBUOutline1;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0007\u001a]\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\u0006\u0010\u0004\u001a\u0002H\u000226\u0010\u0005\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\b0\u00070\u0006\"\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\b0\u0007H\u0086\u0080\u0004¢\u0006\u0002\u0010\t\u001aI\u0010\n\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\u0006\u0010\u0004\u001a\u0002H\u00022 \u0010\u0005\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\b0\u00070\u0006H\u0082\u0080\u0004¢\u0006\u0004\b\u000b\u0010\t\u001aB\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\u0006\u0010\u0004\u001a\u0002H\u00022\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\b0\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\r\u001a^\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u000e2\u0006\u0010\u0003\u001a\u0002H\u00022\u0006\u0010\u0004\u001a\u0002H\u00022\u001a\u0010\u000f\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000e0\u0010j\n\u0012\u0006\b\u0000\u0012\u0002H\u000e`\u00112\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u000e0\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a1\u0010\u0013\u001a\u00020\u0001\"\f\b\u0000\u0010\u0002*\u0006\u0012\u0002\b\u00030\b2\b\u0010\u0003\u001a\u0004\u0018\u0001H\u00022\b\u0010\u0004\u001a\u0004\u0018\u0001H\u0002H\u0086\u0080\u0004¢\u0006\u0002\u0010\u0014\u001a]\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0010j\b\u0012\u0004\u0012\u0002H\u0002`\u0011\"\u0004\b\u0000\u0010\u000226\u0010\u0005\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\b0\u00070\u0006\"\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\b0\u0007H\u0086\u0080\u0004¢\u0006\u0002\u0010\u0016\u001aD\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0010j\b\u0012\u0004\u0012\u0002H\u0002`\u0011\"\u0004\b\u0000\u0010\u00022\u001a\b\u0004\u0010\f\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\b0\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001a`\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0010j\b\u0012\u0004\u0012\u0002H\u0002`\u0011\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u000e2\u001a\u0010\u000f\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000e0\u0010j\n\u0012\u0006\b\u0000\u0012\u0002H\u000e`\u00112\u0014\b\u0004\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u000e0\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001aD\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0010j\b\u0012\u0004\u0012\u0002H\u0002`\u0011\"\u0004\b\u0000\u0010\u00022\u001a\b\u0004\u0010\f\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\b0\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001a`\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0010j\b\u0012\u0004\u0012\u0002H\u0002`\u0011\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u000e2\u001a\u0010\u000f\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000e0\u0010j\n\u0012\u0006\b\u0000\u0012\u0002H\u000e`\u00112\u0014\b\u0004\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u000e0\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001aX\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0010j\b\u0012\u0004\u0012\u0002H\u0002`\u0011\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0010j\b\u0012\u0004\u0012\u0002H\u0002`\u00112\u001a\b\u0004\u0010\f\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\b0\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001at\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0010j\b\u0012\u0004\u0012\u0002H\u0002`\u0011\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u000e*\u0012\u0012\u0004\u0012\u0002H\u00020\u0010j\b\u0012\u0004\u0012\u0002H\u0002`\u00112\u001a\u0010\u000f\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000e0\u0010j\n\u0012\u0006\b\u0000\u0012\u0002H\u000e`\u00112\u0014\b\u0004\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u000e0\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u001b\u001aX\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0010j\b\u0012\u0004\u0012\u0002H\u0002`\u0011\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0010j\b\u0012\u0004\u0012\u0002H\u0002`\u00112\u001a\b\u0004\u0010\f\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\b0\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001at\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0010j\b\u0012\u0004\u0012\u0002H\u0002`\u0011\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u000e*\u0012\u0012\u0004\u0012\u0002H\u00020\u0010j\b\u0012\u0004\u0012\u0002H\u0002`\u00112\u001a\u0010\u000f\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000e0\u0010j\n\u0012\u0006\b\u0000\u0012\u0002H\u000e`\u00112\u0014\b\u0004\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u000e0\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u001b\u001av\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0010j\b\u0012\u0004\u0012\u0002H\u0002`\u0011\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0010j\b\u0012\u0004\u0012\u0002H\u0002`\u001128\b\u0004\u0010\u001e\u001a2\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\u0003\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\u00010\u001fH\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\"\u001aU\u0010#\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0010j\b\u0012\u0004\u0012\u0002H\u0002`\u0011\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0010j\b\u0012\u0004\u0012\u0002H\u0002`\u00112\u001a\u0010\u000f\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0010j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0011H\u0086\u0084\u0004¢\u0006\u0002\u0010$\u001aU\u0010%\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0010j\b\u0012\u0004\u0012\u0002H\u0002`\u0011\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0010j\b\u0012\u0004\u0012\u0002H\u0002`\u00112\u001a\u0010\u000f\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0010j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0011H\u0086\u0084\u0004¢\u0006\u0002\u0010$\u001aI\u0010&\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0010j\n\u0012\u0006\u0012\u0004\u0018\u0001H\u0002`\u0011\"\b\b\u0000\u0010\u0002*\u00020'2\u001a\u0010\u000f\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0010j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0011H\u0086\u0080\u0004¢\u0006\u0002\u0010(\u001a3\u0010&\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0010j\n\u0012\u0006\u0012\u0004\u0018\u0001H\u0002`\u0011\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\bH\u0087\u0088\u0004¢\u0006\u0002\u0010)\u001aI\u0010*\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0010j\n\u0012\u0006\u0012\u0004\u0018\u0001H\u0002`\u0011\"\b\b\u0000\u0010\u0002*\u00020'2\u001a\u0010\u000f\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0010j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0011H\u0086\u0080\u0004¢\u0006\u0002\u0010(\u001a3\u0010*\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0010j\n\u0012\u0006\u0012\u0004\u0018\u0001H\u0002`\u0011\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\bH\u0087\u0088\u0004¢\u0006\u0002\u0010)\u001a/\u0010+\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0010j\b\u0012\u0004\u0012\u0002H\u0002`\u0011\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\bH\u0086\u0080\u0004¢\u0006\u0002\u0010)\u001a/\u0010,\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0010j\b\u0012\u0004\u0012\u0002H\u0002`\u0011\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\bH\u0086\u0080\u0004¢\u0006\u0002\u0010)\u001a9\u0010-\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0010j\b\u0012\u0004\u0012\u0002H\u0002`\u0011\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0010j\b\u0012\u0004\u0012\u0002H\u0002`\u0011H\u0086\u0080\u0004¢\u0006\u0002\u0010(\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006."}, d2 = {"compareValuesBy", _UrlKt.FRAGMENT_ENCODE_SET, "T", "a", "b", "selectors", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/Object;Ljava/lang/Object;[Lkotlin/jvm/functions/Function1;)I", "compareValuesByImpl", "compareValuesByImpl$ComparisonsKt__ComparisonsKt", PluginsConstants.Settings.TYPE_SELECTOR, "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)I", "K", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Comparator;Lkotlin/jvm/functions/Function1;)I", "compareValues", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)I", "compareBy", "([Lkotlin/jvm/functions/Function1;)Ljava/util/Comparator;", "(Lkotlin/jvm/functions/Function1;)Ljava/util/Comparator;", "(Ljava/util/Comparator;Lkotlin/jvm/functions/Function1;)Ljava/util/Comparator;", "compareByDescending", "thenBy", "(Ljava/util/Comparator;Ljava/util/Comparator;Lkotlin/jvm/functions/Function1;)Ljava/util/Comparator;", "thenByDescending", "thenComparator", "comparison", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "(Ljava/util/Comparator;Lkotlin/jvm/functions/Function2;)Ljava/util/Comparator;", "then", "(Ljava/util/Comparator;Ljava/util/Comparator;)Ljava/util/Comparator;", "thenDescending", "nullsFirst", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/util/Comparator;)Ljava/util/Comparator;", "()Ljava/util/Comparator;", "nullsLast", "naturalOrder", "reverseOrder", "reversed", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/comparisons/ComparisonsKt")
/* loaded from: classes.dex */
class ComparisonsKt__ComparisonsKt {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 176)
    @SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2\n*L\n1#1,328:1\n*E\n"})
    /* renamed from: kotlin.comparisons.ComparisonsKt__ComparisonsKt$compareBy$2, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass2<T> implements Comparator {
        final /* synthetic */ Function1<T, Comparable<?>> $selector;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(Function1<? super T, ? extends Comparable<?>> r1) {
            this.$selector = r1;
        }

        @Override // java.util.Comparator
        public final int compare(T r1, T r2) {
            Function1<T, Comparable<?>> r0 = this.$selector;
            return ComparisonsKt__ComparisonsKt.compareValues(r0.invoke(r1), r0.invoke(r2));
        }
    }

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 176)
    @SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$3\n*L\n1#1,328:1\n*E\n"})
    /* renamed from: kotlin.comparisons.ComparisonsKt__ComparisonsKt$compareBy$3, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass3<T> implements Comparator {
        final /* synthetic */ Comparator<? super K> $comparator;
        final /* synthetic */ Function1<T, K> $selector;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(Comparator<? super K> r1, Function1<? super T, ? extends K> r2) {
            this.$comparator = r1;
            this.$selector = r2;
        }

        @Override // java.util.Comparator
        public final int compare(T r2, T r3) {
            Comparator<? super K> r0 = this.$comparator;
            Function1<T, K> r1 = this.$selector;
            return r0.compare(r1.invoke(r2), r1.invoke(r3));
        }
    }

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 176)
    @SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareByDescending$1\n*L\n1#1,328:1\n*E\n"})
    /* renamed from: kotlin.comparisons.ComparisonsKt__ComparisonsKt$compareByDescending$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass1<T> implements Comparator {
        final /* synthetic */ Function1<T, Comparable<?>> $selector;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Function1<? super T, ? extends Comparable<?>> r1) {
            this.$selector = r1;
        }

        @Override // java.util.Comparator
        public final int compare(T r1, T r2) {
            Function1<T, Comparable<?>> r0 = this.$selector;
            return ComparisonsKt__ComparisonsKt.compareValues(r0.invoke(r2), r0.invoke(r1));
        }
    }

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 176)
    @SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareByDescending$2\n*L\n1#1,328:1\n*E\n"})
    /* renamed from: kotlin.comparisons.ComparisonsKt__ComparisonsKt$compareByDescending$2, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes5.dex */
    public static final class C01682<T> implements Comparator {
        final /* synthetic */ Comparator<? super K> $comparator;
        final /* synthetic */ Function1<T, K> $selector;

        /* JADX WARN: Multi-variable type inference failed */
        public C01682(Comparator<? super K> r1, Function1<? super T, ? extends K> r2) {
            this.$comparator = r1;
            this.$selector = r2;
        }

        @Override // java.util.Comparator
        public final int compare(T r2, T r3) {
            Comparator<? super K> r0 = this.$comparator;
            Function1<T, K> r1 = this.$selector;
            return r0.compare(r1.invoke(r3), r1.invoke(r2));
        }
    }

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 176)
    @SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$thenBy$1\n*L\n1#1,328:1\n*E\n"})
    /* renamed from: kotlin.comparisons.ComparisonsKt__ComparisonsKt$thenBy$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes5.dex */
    public static final class C01691<T> implements Comparator {
        final /* synthetic */ Function1<T, Comparable<?>> $selector;
        final /* synthetic */ Comparator<T> $this_thenBy;

        /* JADX WARN: Multi-variable type inference failed */
        public C01691(Comparator<T> r1, Function1<? super T, ? extends Comparable<?>> r2) {
            this.$this_thenBy = r1;
            this.$selector = r2;
        }

        @Override // java.util.Comparator
        public final int compare(T r2, T r3) {
            int r0 = this.$this_thenBy.compare(r2, r3);
            if (r0 == 0) goto L5;
            return r0;
        L5:
            Function1<T, Comparable<?>> r1 = this.$selector;
            return ComparisonsKt__ComparisonsKt.compareValues(r1.invoke(r2), r1.invoke(r3));
        }
    }

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 176)
    @SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$thenBy$2\n*L\n1#1,328:1\n*E\n"})
    /* renamed from: kotlin.comparisons.ComparisonsKt__ComparisonsKt$thenBy$2, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes5.dex */
    public static final class C01702<T> implements Comparator {
        final /* synthetic */ Comparator<? super K> $comparator;
        final /* synthetic */ Function1<T, K> $selector;
        final /* synthetic */ Comparator<T> $this_thenBy;

        /* JADX WARN: Multi-variable type inference failed */
        public C01702(Comparator<T> r1, Comparator<? super K> r2, Function1<? super T, ? extends K> r3) {
            this.$this_thenBy = r1;
            this.$comparator = r2;
            this.$selector = r3;
        }

        @Override // java.util.Comparator
        public final int compare(T r2, T r3) {
            int r0 = this.$this_thenBy.compare(r2, r3);
            if (r0 == 0) goto L5;
            return r0;
        L5:
            Comparator<? super K> r02 = this.$comparator;
            Function1<T, K> r1 = this.$selector;
            return r02.compare(r1.invoke(r2), r1.invoke(r3));
        }
    }

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 176)
    @SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$thenByDescending$1\n*L\n1#1,328:1\n*E\n"})
    /* renamed from: kotlin.comparisons.ComparisonsKt__ComparisonsKt$thenByDescending$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes5.dex */
    public static final class C01711<T> implements Comparator {
        final /* synthetic */ Function1<T, Comparable<?>> $selector;
        final /* synthetic */ Comparator<T> $this_thenByDescending;

        /* JADX WARN: Multi-variable type inference failed */
        public C01711(Comparator<T> r1, Function1<? super T, ? extends Comparable<?>> r2) {
            this.$this_thenByDescending = r1;
            this.$selector = r2;
        }

        @Override // java.util.Comparator
        public final int compare(T r2, T r3) {
            int r0 = this.$this_thenByDescending.compare(r2, r3);
            if (r0 == 0) goto L5;
            return r0;
        L5:
            Function1<T, Comparable<?>> r1 = this.$selector;
            return ComparisonsKt__ComparisonsKt.compareValues(r1.invoke(r3), r1.invoke(r2));
        }
    }

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 176)
    @SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$thenByDescending$2\n*L\n1#1,328:1\n*E\n"})
    /* renamed from: kotlin.comparisons.ComparisonsKt__ComparisonsKt$thenByDescending$2, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes5.dex */
    public static final class C01722<T> implements Comparator {
        final /* synthetic */ Comparator<? super K> $comparator;
        final /* synthetic */ Function1<T, K> $selector;
        final /* synthetic */ Comparator<T> $this_thenByDescending;

        /* JADX WARN: Multi-variable type inference failed */
        public C01722(Comparator<T> r1, Comparator<? super K> r2, Function1<? super T, ? extends K> r3) {
            this.$this_thenByDescending = r1;
            this.$comparator = r2;
            this.$selector = r3;
        }

        @Override // java.util.Comparator
        public final int compare(T r2, T r3) {
            int r0 = this.$this_thenByDescending.compare(r2, r3);
            if (r0 == 0) goto L5;
            return r0;
        L5:
            Comparator<? super K> r02 = this.$comparator;
            Function1<T, K> r1 = this.$selector;
            return r02.compare(r1.invoke(r3), r1.invoke(r2));
        }
    }

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 176)
    @SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$thenComparator$1\n*L\n1#1,328:1\n*E\n"})
    /* renamed from: kotlin.comparisons.ComparisonsKt__ComparisonsKt$thenComparator$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes5.dex */
    public static final class C01731<T> implements Comparator {
        final /* synthetic */ Function2<T, T, Integer> $comparison;
        final /* synthetic */ Comparator<T> $this_thenComparator;

        /* JADX WARN: Multi-variable type inference failed */
        public C01731(Comparator<T> r1, Function2<? super T, ? super T, Integer> r2) {
            this.$this_thenComparator = r1;
            this.$comparison = r2;
        }

        @Override // java.util.Comparator
        public final int compare(T r2, T r3) {
            int r0 = this.$this_thenComparator.compare(r2, r3);
            if (r0 == 0) goto L6;
            return r0;
        L6:
            return this.$comparison.invoke(r2, r3).intValue();
        }
    }

    /* renamed from: $r8$lambda$-MbX4z8CFx5TpRwNxE9nSXf5OwI, reason: not valid java name */
    public static /* synthetic */ int m3209$r8$lambda$MbX4z8CFx5TpRwNxE9nSXf5OwI(Comparator r0, Object r1, Object r2) {
        return nullsFirst$lambda$0$ComparisonsKt__ComparisonsKt(r0, r1, r2);
    }

    public static /* synthetic */ int $r8$lambda$1mcY3I3psryq10EiJDhzZ3FmDqQ(Comparator r0, Comparator r1, Object r2, Object r3) {
        return thenDescending$lambda$0$ComparisonsKt__ComparisonsKt(r0, r1, r2, r3);
    }

    /* renamed from: $r8$lambda$32O0jCX-4SgDnV0fJ9Osa-rSqVE, reason: not valid java name */
    public static /* synthetic */ int m3210$r8$lambda$32O0jCX4SgDnV0fJ9OsarSqVE(Comparator r0, Comparator r1, Object r2, Object r3) {
        return then$lambda$0$ComparisonsKt__ComparisonsKt(r0, r1, r2, r3);
    }

    public static /* synthetic */ int $r8$lambda$W8AWSBCuy65sMGWfMo6pJ3RDjRw(Function1[] r0, Object r1, Object r2) {
        return compareBy$lambda$0$ComparisonsKt__ComparisonsKt(r0, r1, r2);
    }

    public static /* synthetic */ int $r8$lambda$YRWd_6HkL2oB1v97rx6oiJ1kPMk(Comparator r0, Object r1, Object r2) {
        return nullsLast$lambda$0$ComparisonsKt__ComparisonsKt(r0, r1, r2);
    }

    public ComparisonsKt__ComparisonsKt() {
    }

    @InlineOnly
    private static final <T, K> Comparator<T> compareBy(Comparator<? super K> r1, Function1<? super T, ? extends K> r2) {
        return new AnonymousClass3(r1, r2);
    }

    @InlineOnly
    private static final <T> Comparator<T> compareBy(Function1<? super T, ? extends Comparable<?>> r1) {
        return new AnonymousClass2(r1);
    }

    public static final <T> Comparator<T> compareBy(Function1<? super T, ? extends Comparable<?>>... r1) {
        if (r1.length > 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
        return null;
    L5:
        return new ComparisonsKt__ComparisonsKt$$ExternalSyntheticLambda3(r1);
    }

    private static final int compareBy$lambda$0$ComparisonsKt__ComparisonsKt(Function1[] r0, Object r1, Object r2) {
        return compareValuesByImpl$ComparisonsKt__ComparisonsKt(r1, r2, r0);
    }

    @InlineOnly
    private static final <T, K> Comparator<T> compareByDescending(Comparator<? super K> r1, Function1<? super T, ? extends K> r2) {
        return new C01682(r1, r2);
    }

    @InlineOnly
    private static final <T> Comparator<T> compareByDescending(Function1<? super T, ? extends Comparable<?>> r1) {
        return new AnonymousClass1(r1);
    }

    public static <T extends Comparable<?>> int compareValues(T r0, T r1) {
        if (r0 != r1) goto L5;
        return 0;
    L5:
        if (r0 != null) goto L8;
        return -1;
    L8:
        if (r1 != null) goto L12;
        return 1;
    L12:
        return r0.compareTo(r1);
    }

    @InlineOnly
    private static final <T, K> int compareValuesBy(T r0, T r1, Comparator<? super K> r2, Function1<? super T, ? extends K> r3) {
        return r2.compare(r3.invoke(r0), r3.invoke(r1));
    }

    @InlineOnly
    private static final <T> int compareValuesBy(T r0, T r1, Function1<? super T, ? extends Comparable<?>> r2) {
        return compareValues(r2.invoke(r0), r2.invoke(r1));
    }

    public static final <T> int compareValuesBy(T r1, T r2, Function1<? super T, ? extends Comparable<?>>... r3) {
        if (r3.length > 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
        return 0;
    L5:
        return compareValuesByImpl$ComparisonsKt__ComparisonsKt(r1, r2, r3);
    }

    private static final <T> int compareValuesByImpl$ComparisonsKt__ComparisonsKt(T r5, T r6, Function1<? super T, ? extends Comparable<?>>[] r7) {
        int r0 = r7.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        Function1<? super T, ? extends Comparable<?>> r3 = r7[r2];
        int r32 = compareValues(r3.invoke(r5), r3.invoke(r6));
        if (r32 != 0) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return r32;
    L8:
        return 0;
    }

    public static <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
        return NaturalOrderComparator.INSTANCE;
    }

    @InlineOnly
    private static final <T extends Comparable<? super T>> Comparator<T> nullsFirst() {
        return nullsFirst(naturalOrder());
    }

    public static final <T> Comparator<T> nullsFirst(Comparator<? super T> r1) {
        return new ComparisonsKt__ComparisonsKt$$ExternalSyntheticLambda4(r1);
    }

    private static final int nullsFirst$lambda$0$ComparisonsKt__ComparisonsKt(Comparator r0, Object r1, Object r2) {
        if (r1 != r2) goto L5;
        return 0;
    L5:
        if (r1 != null) goto L8;
        return -1;
    L8:
        if (r2 != null) goto L12;
        return 1;
    L12:
        return r0.compare(r1, r2);
    }

    @InlineOnly
    private static final <T extends Comparable<? super T>> Comparator<T> nullsLast() {
        return nullsLast(naturalOrder());
    }

    public static final <T> Comparator<T> nullsLast(Comparator<? super T> r1) {
        return new ComparisonsKt__ComparisonsKt$$ExternalSyntheticLambda0(r1);
    }

    private static final int nullsLast$lambda$0$ComparisonsKt__ComparisonsKt(Comparator r0, Object r1, Object r2) {
        if (r1 != r2) goto L5;
        return 0;
    L5:
        if (r1 != null) goto L8;
        return 1;
    L8:
        if (r2 != null) goto L12;
        return -1;
    L12:
        return r0.compare(r1, r2);
    }

    public static <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
        return ReverseOrderComparator.INSTANCE;
    }

    public static final <T> Comparator<T> reversed(Comparator<T> r2) {
        if ((r2 instanceof ReversedComparator) == true) goto L5;
        NaturalOrderComparator r0 = NaturalOrderComparator.INSTANCE;
        if (Intrinsics.areEqual(r2, r0) == false) goto L11;
        return ReverseOrderComparator.INSTANCE;
    L11:
        if (Intrinsics.areEqual(r2, ReverseOrderComparator.INSTANCE) == false) goto L14;
        return r0;
    L14:
        return new ReversedComparator(r2);
    L5:
        return ((ReversedComparator) r2).getComparator();
    }

    public static final <T> Comparator<T> then(Comparator<T> r1, Comparator<? super T> r2) {
        return new ComparisonsKt__ComparisonsKt$$ExternalSyntheticLambda2(r1, r2);
    }

    private static final int then$lambda$0$ComparisonsKt__ComparisonsKt(Comparator r0, Comparator r1, Object r2, Object r3) {
        int r02 = r0.compare(r2, r3);
        if (r02 == 0) goto L6;
        return r02;
    L6:
        return r1.compare(r2, r3);
    }

    @InlineOnly
    private static final <T, K> Comparator<T> thenBy(Comparator<T> r1, Comparator<? super K> r2, Function1<? super T, ? extends K> r3) {
        return new C01702(r1, r2, r3);
    }

    @InlineOnly
    private static final <T> Comparator<T> thenBy(Comparator<T> r1, Function1<? super T, ? extends Comparable<?>> r2) {
        return new C01691(r1, r2);
    }

    @InlineOnly
    private static final <T, K> Comparator<T> thenByDescending(Comparator<T> r1, Comparator<? super K> r2, Function1<? super T, ? extends K> r3) {
        return new C01722(r1, r2, r3);
    }

    @InlineOnly
    private static final <T> Comparator<T> thenByDescending(Comparator<T> r1, Function1<? super T, ? extends Comparable<?>> r2) {
        return new C01711(r1, r2);
    }

    @InlineOnly
    private static final <T> Comparator<T> thenComparator(Comparator<T> r1, Function2<? super T, ? super T, Integer> r2) {
        return new C01731(r1, r2);
    }

    public static final <T> Comparator<T> thenDescending(Comparator<T> r1, Comparator<? super T> r2) {
        return new ComparisonsKt__ComparisonsKt$$ExternalSyntheticLambda1(r1, r2);
    }

    private static final int thenDescending$lambda$0$ComparisonsKt__ComparisonsKt(Comparator r0, Comparator r1, Object r2, Object r3) {
        int r02 = r0.compare(r2, r3);
        if (r02 == 0) goto L6;
        return r02;
    L6:
        return r1.compare(r3, r2);
    }
}
