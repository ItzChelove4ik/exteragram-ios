package kotlin.collections;

import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.protobuf.ByteString$$ExternalSyntheticBUOutline2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.BuilderInference;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.comparisons.ComparisonsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt__StringsKt$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u008e\u0001\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\u001a1\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0080\u0080\u0004¢\u0006\u0002\u0010\u0006\u001a\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\"\u0004\b\u0000\u0010\u0002H\u0086\u0080\u0004\u001a/\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\"\u0004\b\u0000\u0010\u00022\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003\"\u0002H\u0002H\u0086\u0080\u0004¢\u0006\u0002\u0010\u000b\u001a\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\"\u0004\b\u0000\u0010\u0002H\u0087\u0088\u0004\u001a\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\r\"\u0004\b\u0000\u0010\u0002H\u0087\u0088\u0004\u001a%\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u000fj\b\u0012\u0004\u0012\u0002H\u0002`\u0010\"\u0004\b\u0000\u0010\u0002H\u0087\u0088\u0004¢\u0006\u0002\u0010\u0011\u001a/\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\r\"\u0004\b\u0000\u0010\u00022\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003\"\u0002H\u0002H\u0086\u0080\u0004¢\u0006\u0002\u0010\u000b\u001a9\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u000fj\b\u0012\u0004\u0012\u0002H\u0002`\u0010\"\u0004\b\u0000\u0010\u00022\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003\"\u0002H\u0002H\u0086\u0080\u0004¢\u0006\u0002\u0010\u0012\u001a)\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\"\b\b\u0000\u0010\u0002*\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u0001H\u0002H\u0086\u0080\u0004¢\u0006\u0002\u0010\u0016\u001a7\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\"\b\b\u0000\u0010\u0002*\u00020\u00142\u0016\u0010\n\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u0001H\u00020\u0003\"\u0004\u0018\u0001H\u0002H\u0086\u0080\u0004¢\u0006\u0002\u0010\u000b\u001aD\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0018\u001a\u00020\u00192!\u0010\u001a\u001a\u001d\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u0002H\u00020\u001bH\u0087\u0088\u0004ø\u0001\u0000\u001aD\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00020\r\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0018\u001a\u00020\u00192!\u0010\u001a\u001a\u001d\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u0002H\u00020\u001bH\u0087\u0088\u0004ø\u0001\u0000\u001aG\u0010 \u001a\b\u0012\u0004\u0012\u0002H!0\b\"\u0004\b\u0000\u0010!2\u001f\b\u0001\u0010\"\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H!0\r\u0012\u0004\u0012\u00020#0\u001b¢\u0006\u0002\b$H\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001aO\u0010 \u001a\b\u0012\u0004\u0012\u0002H!0\b\"\u0004\b\u0000\u0010!2\u0006\u0010%\u001a\u00020\u00192\u001f\b\u0001\u0010\"\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H!0\r\u0012\u0004\u0012\u00020#0\u001b¢\u0006\u0002\b$H\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u001a\u001a\u0010-\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0087\u0088\u0004\u001a-\u0010.\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001H\u0087\u0088\u0004\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u001a\"\u0010/\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001H\u0087\u0088\u0004\u001a\"\u0010/\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\bH\u0087\u0088\u0004\u001aI\u00100\u001a\u0002H1\"\u0010\b\u0000\u00102*\u0006\u0012\u0002\b\u00030\u0001*\u0002H1\"\u0004\b\u0001\u00101*\u0002H22\f\u00103\u001a\b\u0012\u0004\u0012\u0002H104H\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u00105\u001a-\u00106\u001a\u00020\u0005\"\t\b\u0000\u0010\u0002¢\u0006\u0002\b7*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0087\u0088\u0004\u001a(\u00108\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u0002092\u0006\u0010:\u001a\u00020;H\u0087\u0080\u0004\u001a \u0010<\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\bH\u0080\u0080\u0004\u001aI\u0010=\u001a\u00020\u0019\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020>*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\b2\b\u0010\u0015\u001a\u0004\u0018\u0001H\u00022\b\b\u0002\u0010?\u001a\u00020\u00192\b\b\u0002\u0010@\u001a\u00020\u0019H\u0086\u0080\u0004¢\u0006\u0002\u0010A\u001aW\u0010=\u001a\u00020\u0019\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\b2\u0006\u0010\u0015\u001a\u0002H\u00022\u001a\u0010B\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020Cj\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`D2\b\b\u0002\u0010?\u001a\u00020\u00192\b\b\u0002\u0010@\u001a\u00020\u0019H\u0086\u0080\u0004¢\u0006\u0002\u0010E\u001ah\u0010F\u001a\u00020\u0019\"\u0004\b\u0000\u0010\u0002\"\u000e\b\u0001\u0010G*\b\u0012\u0004\u0012\u0002HG0>*\b\u0012\u0004\u0012\u0002H\u00020\b2\b\u0010H\u001a\u0004\u0018\u0001HG2\b\b\u0002\u0010?\u001a\u00020\u00192\b\b\u0002\u0010@\u001a\u00020\u00192\u0016\b\u0004\u0010I\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0006\u0012\u0004\u0018\u0001HG0\u001bH\u0086\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010J\u001aB\u0010=\u001a\u00020\u0019\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\b2\b\b\u0002\u0010?\u001a\u00020\u00192\b\b\u0002\u0010@\u001a\u00020\u00192\u0012\u0010K\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00190\u001bH\u0086\u0080\u0004\u001a'\u0010L\u001a\u00020#2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010?\u001a\u00020\u00192\u0006\u0010@\u001a\u00020\u0019H\u0082\u0080\u0004¢\u0006\u0002\bM\u001a\n\u0010N\u001a\u00020#H\u0081\u0080\u0004\u001a\n\u0010O\u001a\u00020#H\u0081\u0080\u0004\u001a#\u0010P\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u00032\n\u0010Q\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0080\u0080\u0004¢\u0006\u0002\u0010R\u001a5\u0010P\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u00022\n\u0010Q\u001a\u0006\u0012\u0002\b\u00030\u00012\f\u0010S\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0080\u0080\u0004¢\u0006\u0002\u0010T\"\u001d\u0010&\u001a\u00020'*\u0006\u0012\u0002\b\u00030\u00018FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b(\u0010)\"%\u0010*\u001a\u00020\u0019\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\b8FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b+\u0010,\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006U"}, d2 = {"asCollection", _UrlKt.FRAGMENT_ENCODE_SET, "T", _UrlKt.FRAGMENT_ENCODE_SET, "isVarargs", _UrlKt.FRAGMENT_ENCODE_SET, "([Ljava/lang/Object;Z)Ljava/util/Collection;", "emptyList", _UrlKt.FRAGMENT_ENCODE_SET, "listOf", "elements", "([Ljava/lang/Object;)Ljava/util/List;", "mutableListOf", _UrlKt.FRAGMENT_ENCODE_SET, "arrayListOf", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "()Ljava/util/ArrayList;", "([Ljava/lang/Object;)Ljava/util/ArrayList;", "listOfNotNull", _UrlKt.FRAGMENT_ENCODE_SET, "element", "(Ljava/lang/Object;)Ljava/util/List;", "List", "size", _UrlKt.FRAGMENT_ENCODE_SET, "init", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "index", "MutableList", "buildList", "E", "builderAction", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "capacity", "indices", "Lkotlin/ranges/IntRange;", "getIndices", "(Ljava/util/Collection;)Lkotlin/ranges/IntRange;", "lastIndex", "getLastIndex", "(Ljava/util/List;)I", "isNotEmpty", "isNullOrEmpty", "orEmpty", "ifEmpty", "R", "C", "defaultValue", "Lkotlin/Function0;", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "containsAll", "Lkotlin/internal/OnlyInputTypes;", "shuffled", _UrlKt.FRAGMENT_ENCODE_SET, "random", "Lkotlin/random/Random;", "optimizeReadOnlyList", "binarySearch", _UrlKt.FRAGMENT_ENCODE_SET, "fromIndex", "toIndex", "(Ljava/util/List;Ljava/lang/Comparable;II)I", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/util/List;Ljava/lang/Object;Ljava/util/Comparator;II)I", "binarySearchBy", "K", PluginsConstants.Settings.KEY, PluginsConstants.Settings.TYPE_SELECTOR, "(Ljava/util/List;Ljava/lang/Comparable;IILkotlin/jvm/functions/Function1;)I", "comparison", "rangeCheck", "rangeCheck$CollectionsKt__CollectionsKt", "throwIndexOverflow", "throwCountOverflow", "collectionToArrayCommonImpl", "collection", "(Ljava/util/Collection;)[Ljava/lang/Object;", "array", "(Ljava/util/Collection;[Ljava/lang/Object;)[Ljava/lang/Object;", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/collections/CollectionsKt")
@SourceDebugExtension({"SMAP\nCollections.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Collections.kt\nkotlin/collections/CollectionsKt__CollectionsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,529:1\n1#2:530\n*E\n"})
/* loaded from: classes.dex */
class CollectionsKt__CollectionsKt extends CollectionsKt__CollectionsJVMKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 176)
    @SourceDebugExtension({"SMAP\nCollections.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Collections.kt\nkotlin/collections/CollectionsKt__CollectionsKt$binarySearchBy$1\n*L\n1#1,529:1\n*E\n"})
    /* renamed from: kotlin.collections.CollectionsKt__CollectionsKt$binarySearchBy$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass1<T> implements Function1<T, Integer> {

        /* JADX INFO: Incorrect field signature: TK; */
        final /* synthetic */ Comparable $key;
        final /* synthetic */ Function1<T, K> $selector;

        /* JADX WARN: Incorrect types in method signature: (Lkotlin/jvm/functions/Function1<-TT;+TK;>;TK;)V */
        public AnonymousClass1(Function1 r1, Comparable r2) {
            this.$selector = r1;
            this.$key = r2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final Integer invoke(T r2) {
            return Integer.valueOf(ComparisonsKt.compareValues((Comparable) this.$selector.invoke(r2), this.$key));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Integer invoke(Object r1) {
            return invoke(r1);
        }
    }

    public CollectionsKt__CollectionsKt() {
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> List<T> List(int r3, Function1<? super Integer, ? extends T> r4) {
        ArrayList r0 = new ArrayList(r3);
        int r1 = 0;
    L3:
        if (r1 >= r3) goto L5;
        r0.add(r4.invoke(Integer.valueOf(r1)));
        r1 = r1 + 1;
        goto L3
    L5:
        return r0;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> List<T> MutableList(int r3, Function1<? super Integer, ? extends T> r4) {
        ArrayList r0 = new ArrayList(r3);
        int r1 = 0;
    L3:
        if (r1 >= r3) goto L5;
        r0.add(r4.invoke(Integer.valueOf(r1)));
        r1 = r1 + 1;
        goto L3
    L5:
        return r0;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> ArrayList<T> arrayListOf() {
        return new ArrayList();
    }

    public static final <T> ArrayList<T> arrayListOf(T... r2) {
        if (r2.length != 0) goto L7;
        return new ArrayList();
    L7:
        return new ArrayList(asCollection(r2, true));
    }

    public static final <T> Collection<T> asCollection(T[] r1, boolean r2) {
        return new ArrayAsCollection(r1, r2);
    }

    public static /* synthetic */ Collection asCollection$default(Object[] r0, boolean r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = false;
    L6:
        return asCollection(r0, r1);
    }

    public static final <T> int binarySearch(List<? extends T> r2, int r3, int r4, Function1<? super T, Integer> r5) {
        rangeCheck$CollectionsKt__CollectionsKt(r2.size(), r3, r4);
        int r42 = r4 - 1;
    L3:
        if (r3 > r42) goto L11;
        int r0 = (r3 + r42) >>> 1;
        int r1 = r5.invoke(r2.get(r0)).intValue();
        if (r1 < 0) goto L6;
        if (r1 <= 0) goto L9;
        r42 = r0 - 1;
        goto L3
    L9:
        return r0;
    L6:
        r3 = r0 + 1;
        goto L3
    L11:
        return -(r3 + 1);
    }

    public static final <T extends Comparable<? super T>> int binarySearch(List<? extends T> r2, T r3, int r4, int r5) {
        rangeCheck$CollectionsKt__CollectionsKt(r2.size(), r4, r5);
        int r52 = r5 - 1;
    L3:
        if (r4 > r52) goto L11;
        int r0 = (r4 + r52) >>> 1;
        int r1 = ComparisonsKt.compareValues(r2.get(r0), r3);
        if (r1 < 0) goto L6;
        if (r1 <= 0) goto L9;
        r52 = r0 - 1;
        goto L3
    L9:
        return r0;
    L6:
        r4 = r0 + 1;
        goto L3
    L11:
        return -(r4 + 1);
    }

    public static final <T> int binarySearch(List<? extends T> r2, T r3, Comparator<? super T> r4, int r5, int r6) {
        rangeCheck$CollectionsKt__CollectionsKt(r2.size(), r5, r6);
        int r62 = r6 - 1;
    L3:
        if (r5 > r62) goto L11;
        int r0 = (r5 + r62) >>> 1;
        int r1 = r4.compare(r2.get(r0), r3);
        if (r1 < 0) goto L6;
        if (r1 <= 0) goto L9;
        r62 = r0 - 1;
        goto L3
    L9:
        return r0;
    L6:
        r5 = r0 + 1;
        goto L3
    L11:
        return -(r5 + 1);
    }

    public static /* synthetic */ int binarySearch$default(List r0, int r1, int r2, Function1 r3, int r4, Object r5) {
        if ((r4 & 1) == 0) goto L6;
        r1 = 0;
    L6:
        if ((r4 & 2) == 0) goto L9;
        r2 = r0.size();
    L9:
        return binarySearch(r0, r1, r2, r3);
    }

    public static /* synthetic */ int binarySearch$default(List r0, Comparable r1, int r2, int r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = 0;
    L6:
        if ((r4 & 4) == 0) goto L9;
        r3 = r0.size();
    L9:
        return binarySearch(r0, r1, r2, r3);
    }

    public static /* synthetic */ int binarySearch$default(List r0, Object r1, Comparator r2, int r3, int r4, int r5, Object r6) {
        if ((r5 & 4) == 0) goto L6;
        r3 = 0;
    L6:
        if ((r5 & 8) == 0) goto L9;
        r4 = r0.size();
    L9:
        return binarySearch(r0, r1, r2, r3, r4);
    }

    public static final <T, K extends Comparable<? super K>> int binarySearchBy(List<? extends T> r1, K r2, int r3, int r4, Function1<? super T, ? extends K> r5) {
        return binarySearch(r1, r3, r4, new AnonymousClass1(r5, r2));
    }

    public static /* synthetic */ int binarySearchBy$default(List r0, Comparable r1, int r2, int r3, Function1 r4, int r5, Object r6) {
        if ((r5 & 2) == 0) goto L6;
        r2 = 0;
    L6:
        if ((r5 & 4) == 0) goto L9;
        r3 = r0.size();
    L9:
        return binarySearch(r0, r2, r3, new AnonymousClass1(r4, r1));
    }

    @SinceKotlin(version = "1.6")
    @InlineOnly
    private static final <E> List<E> buildList(int r0, @BuilderInference Function1<? super List<E>, Unit> r1) {
        List r02 = CollectionsKt__CollectionsJVMKt.createListBuilder(r0);
        r1.invoke(r02);
        return CollectionsKt__CollectionsJVMKt.build(r02);
    }

    @SinceKotlin(version = "1.6")
    @InlineOnly
    private static final <E> List<E> buildList(@BuilderInference Function1<? super List<E>, Unit> r1) {
        List r0 = CollectionsKt__CollectionsJVMKt.createListBuilder();
        r1.invoke(r0);
        return CollectionsKt__CollectionsJVMKt.build(r0);
    }

    public static final Object[] collectionToArrayCommonImpl(Collection<?> r4) {
        int r1 = 0;
        if (r4.isEmpty() == true) goto L5;
        Object[] r0 = new Object[r4.size()];
        Iterator<?> r42 = r4.iterator();
    L8:
        if (r42.hasNext() == false) goto L10;
        r0[r1] = r42.next();
        r1 = r1 + 1;
        goto L8
    L10:
        return r0;
    L5:
        return new Object[0];
    }

    public static final <T> T[] collectionToArrayCommonImpl(Collection<?> r4, T[] r5) {
        int r1 = 0;
        if (r4.isEmpty() == true) goto L5;
        int r0 = r5.length;
        Object[] r52 = r5;
        if (r0 >= r4.size()) goto L9;
        r52 = (T[]) ArraysKt__ArraysJVMKt.arrayOfNulls(r5, r4.size());
    L9:
        Iterator<?> r02 = r4.iterator();
    L11:
        if (r02.hasNext() == false) goto L14;
        r52[r1] = r02.next();
        r1 = r1 + 1;
        goto L11
    L14:
        return (T[]) CollectionsKt__CollectionsJVMKt.terminateCollectionToArray(r4.size(), r52);
    L5:
        return (T[]) CollectionsKt__CollectionsJVMKt.terminateCollectionToArray(0, r5);
    }

    @InlineOnly
    private static final <T> boolean containsAll(Collection<? extends T> r0, Collection<? extends T> r1) {
        return r0.containsAll(r1);
    }

    public static <T> List<T> emptyList() {
        return EmptyList.INSTANCE;
    }

    public static IntRange getIndices(Collection<?> r2) {
        return new IntRange(0, r2.size() - 1);
    }

    public static <T> int getLastIndex(List<? extends T> r0) {
        return r0.size() - 1;
    }

    /* JADX WARN: Incorrect types in method signature: <C::Ljava/util/Collection<*>;:TR;R:Ljava/lang/Object;>(TC;Lkotlin/jvm/functions/Function0<+TR;>;)TR; */
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final Object ifEmpty(Collection r1, Function0 r2) {
        if (r1.isEmpty() == true) goto L5;
        return r1;
    L5:
        return r2.invoke();
    }

    @InlineOnly
    private static final <T> boolean isNotEmpty(Collection<? extends T> r0) {
        return !r0.isEmpty();
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <T> boolean isNullOrEmpty(Collection<? extends T> r0) {
        if (r0 != null) goto L4;
        return true;
    L4:
        if (r0.isEmpty() == true) goto L10;
        return false;
    L10:
        return true;
    }

    @InlineOnly
    private static final <T> List<T> listOf() {
        return emptyList();
    }

    public static <T> List<T> listOf(T... r1) {
        if (r1.length <= 0) goto L7;
        return ArraysKt___ArraysJvmKt.asList(r1);
    L7:
        return emptyList();
    }

    public static <T> List<T> listOfNotNull(T r0) {
        if (r0 == null) goto L6;
        return CollectionsKt__CollectionsJVMKt.listOf(r0);
    L6:
        return emptyList();
    }

    public static <T> List<T> listOfNotNull(T... r0) {
        return ArraysKt___ArraysKt.filterNotNull(r0);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> List<T> mutableListOf() {
        return new ArrayList();
    }

    public static <T> List<T> mutableListOf(T... r2) {
        if (r2.length != 0) goto L7;
        return new ArrayList();
    L7:
        return new ArrayList(asCollection(r2, true));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> List<T> optimizeReadOnlyList(List<? extends T> r2) {
        int r0 = r2.size();
        if (r0 == 0) goto L10;
        if (r0 == 1) goto L8;
        return r2;
    L8:
        return CollectionsKt__CollectionsJVMKt.listOf(r2.get(0));
    L10:
        return emptyList();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    private static final <T> Collection<T> orEmpty(Collection<? extends T> r0) {
        if (r0 == 0) goto L4;
        return r0;
    L4:
        return emptyList();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    private static final <T> List<T> orEmpty(List<? extends T> r0) {
        if (r0 == 0) goto L4;
        return r0;
    L4:
        return emptyList();
    }

    private static final void rangeCheck$CollectionsKt__CollectionsKt(int r2, int r3, int r4) {
        if (r3 > r4) goto L12;
        if (r3 < 0) goto L9;
        if (r4 > r2) goto L7;
        return;
    L7:
        StringsKt__StringsKt$$ExternalSyntheticBUOutline0.m("toIndex (", r4, ") is greater than size (", r2);
        return;
    L9:
        ByteString$$ExternalSyntheticBUOutline2.m("fromIndex (", r3, ") is less than zero.");
        return;
    L12:
        throw new IllegalArgumentException("fromIndex (" + r3 + ") is greater than toIndex (" + r4 + ").");
    }

    @SinceKotlin(version = "1.3")
    public static final <T> List<T> shuffled(Iterable<? extends T> r0, Random r1) {
        List<T> r02 = CollectionsKt___CollectionsKt.toMutableList(r0);
        CollectionsKt___CollectionsKt.shuffle(r02, r1);
        return r02;
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    public static void throwCountOverflow() {
        throw new ArithmeticException("Count overflow has happened.");
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    public static void throwIndexOverflow() {
        throw new ArithmeticException("Index overflow has happened.");
    }
}
