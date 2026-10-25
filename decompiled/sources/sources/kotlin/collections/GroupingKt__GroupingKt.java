package kotlin.collections;

import android.Manifest;
import android.R;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u009f\u0001\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052b\u0010\u0006\u001a^\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u0001H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u0002H\u00030\u0007H\u0087\u0088\u0004ø\u0001\u0000\u001a¸\u0001\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u00102b\u0010\u0006\u001a^\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u0001H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u0002H\u00030\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001aÀ\u0001\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u000526\u0010\u0015\u001a2\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u00162K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u0017H\u0087\u0088\u0004ø\u0001\u0000\u001aÙ\u0001\u0010\u0018\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u001026\u0010\u0015\u001a2\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u00162K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u0017H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a\u0080\u0001\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u001a\u001a\u0002H\u000326\u0010\u0006\u001a2\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u0016H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u001b\u001a\u0094\u0001\u0010\u0018\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u00102\u0006\u0010\u001a\u001a\u0002H\u000326\u0010\u0006\u001a2\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u0016H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a\u008c\u0001\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u001e0\u0001\"\u0004\b\u0000\u0010\u001e\"\b\b\u0001\u0010\u0004*\u0002H\u001e\"\u0004\b\u0002\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u001e¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u001e0\u0017H\u0087\u0088\u0004ø\u0001\u0000\u001a¥\u0001\u0010\u001f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u001e\"\b\b\u0001\u0010\u0004*\u0002H\u001e\"\u0004\b\u0002\u0010\u0002\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u001e0\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u00102K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u001e¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u001e0\u0017H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010 \u001aK\u0010!\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0016\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\"0\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u0010H\u0087\u0080\u0004¢\u0006\u0002\u0010#\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006$"}, d2 = {"aggregate", _UrlKt.FRAGMENT_ENCODE_SET, "K", "R", "T", "Lkotlin/collections/Grouping;", "operation", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "name", PluginsConstants.Settings.KEY, "accumulator", "element", _UrlKt.FRAGMENT_ENCODE_SET, "first", "aggregateTo", "M", _UrlKt.FRAGMENT_ENCODE_SET, "destination", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function4;)Ljava/util/Map;", "fold", "initialValueSelector", "Lkotlin/Function2;", "Lkotlin/Function3;", "foldTo", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;)Ljava/util/Map;", "initialValue", "(Lkotlin/collections/Grouping;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "(Lkotlin/collections/Grouping;Ljava/util/Map;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "reduce", "S", "reduceTo", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function3;)Ljava/util/Map;", "eachCountTo", _UrlKt.FRAGMENT_ENCODE_SET, "(Lkotlin/collections/Grouping;Ljava/util/Map;)Ljava/util/Map;", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/collections/GroupingKt")
@SourceDebugExtension({"SMAP\nGrouping.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Grouping.kt\nkotlin/collections/GroupingKt__GroupingKt\n*L\n1#1,291:1\n80#1,6:292\n53#1:298\n80#1,6:299\n80#1,6:305\n53#1:311\n80#1,6:312\n80#1,6:318\n53#1:324\n80#1,6:325\n80#1,6:331\n189#1:337\n80#1,6:338\n*S KotlinDebug\n*F\n+ 1 Grouping.kt\nkotlin/collections/GroupingKt__GroupingKt\n*L\n53#1:292,6\n112#1:298\n112#1:299,6\n143#1:305,6\n164#1:311\n164#1:312,6\n189#1:318,6\n211#1:324\n211#1:325,6\n239#1:331,6\n257#1:337\n257#1:338,6\n*E\n"})
/* loaded from: classes5.dex */
class GroupingKt__GroupingKt extends GroupingKt__GroupingJVMKt {
    public GroupingKt__GroupingKt() {
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object] */
    @SinceKotlin(version = "1.1")
    public static final <T, K, R> Map<K, R> aggregate(Grouping<T, ? extends K> r6, Function4<? super K, ? super R, ? super T, ? super Boolean, ? extends R> r7) {
        LinkedHashMap r0 = new LinkedHashMap();
        Iterator<T> r1 = r6.sourceIterator();
    L4:
        if (r1.hasNext() == false) goto L12;
        ?? r2 = r1.next();
        Object r3 = r6.keyOf(r2);
        Manifest.permission_group r4 = (Object) r0.get(r3);
        if (r4 == null) goto L8;
    L10:
        boolean r5 = false;
    L11:
        r0.put(r3, r7.invoke(r3, r4, r2, Boolean.valueOf(r5)));
        goto L4
    L8:
        if (r0.containsKey(r3) == true) goto L10;
        r5 = true;
        goto L11
    L12:
        return r0;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object] */
    @SinceKotlin(version = "1.1")
    public static final <T, K, R, M extends Map<? super K, R>> M aggregateTo(Grouping<T, ? extends K> r5, M r6, Function4<? super K, ? super R, ? super T, ? super Boolean, ? extends R> r7) {
        Iterator<T> r0 = r5.sourceIterator();
    L4:
        if (r0.hasNext() == false) goto L12;
        ?? r1 = r0.next();
        Object r2 = r5.keyOf(r1);
        Manifest.permission_group r3 = (Object) r6.get(r2);
        if (r3 == null) goto L8;
    L10:
        boolean r4 = false;
    L11:
        r6.put(r2, r7.invoke(r2, r3, r1, Boolean.valueOf(r4)));
        goto L4
    L8:
        if (r6.containsKey(r2) == true) goto L10;
        r4 = true;
        goto L11
    L12:
        return r6;
    }

    @SinceKotlin(version = "1.1")
    public static final <T, K, M extends Map<? super K, Integer>> M eachCountTo(Grouping<T, ? extends K> r7, M r8) {
        Iterator<T> r2 = r7.sourceIterator();
    L4:
        if (r2.hasNext() == false) goto L14;
        K r3 = r7.keyOf(r2.next());
        Object r4 = r8.get(r3);
        if (r4 == null) goto L8;
    L10:
        boolean r6 = false;
    L11:
        if (r6 == false) goto L13;
        r4 = 0;
    L13:
        r8.put(r3, Integer.valueOf(((Number) r4).intValue() + 1));
        goto L4
    L8:
        if (r8.containsKey(r3) == true) goto L10;
        r6 = true;
        goto L11
    L14:
        return r8;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object] */
    @SinceKotlin(version = "1.1")
    public static final <T, K, R> Map<K, R> fold(Grouping<T, ? extends K> r6, R r7, Function2<? super R, ? super T, ? extends R> r8) {
        LinkedHashMap r0 = new LinkedHashMap();
        Iterator<T> r1 = r6.sourceIterator();
    L4:
        if (r1.hasNext() == false) goto L14;
        ?? r2 = r1.next();
        K r3 = r6.keyOf(r2);
        R r4 = (Object) r0.get(r3);
        if (r4 == null) goto L8;
    L10:
        boolean r5 = false;
    L11:
        if (r5 == false) goto L13;
        r4 = (Object) r7;
    L13:
        r0.put(r3, r8.invoke(r4, r2));
        goto L4
    L8:
        if (r0.containsKey(r3) == true) goto L10;
        r5 = true;
        goto L11
    L14:
        return r0;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object] */
    @SinceKotlin(version = "1.1")
    public static final <T, K, R> Map<K, R> fold(Grouping<T, ? extends K> r6, Function2<? super K, ? super T, ? extends R> r7, Function3<? super K, ? super R, ? super T, ? extends R> r8) {
        LinkedHashMap r0 = new LinkedHashMap();
        Iterator<T> r1 = r6.sourceIterator();
    L4:
        if (r1.hasNext() == false) goto L14;
        ?? r2 = r1.next();
        Object r3 = r6.keyOf(r2);
        R r4 = (Object) r0.get(r3);
        if (r4 == null) goto L8;
    L10:
        boolean r5 = false;
    L11:
        if (r5 == false) goto L13;
        r4 = r7.invoke(r3, r2);
    L13:
        r0.put(r3, r8.invoke(r3, r4, r2));
        goto L4
    L8:
        if (r0.containsKey(r3) == true) goto L10;
        r5 = true;
        goto L11
    L14:
        return r0;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object] */
    @SinceKotlin(version = "1.1")
    public static final <T, K, R, M extends Map<? super K, R>> M foldTo(Grouping<T, ? extends K> r5, M r6, R r7, Function2<? super R, ? super T, ? extends R> r8) {
        Iterator<T> r0 = r5.sourceIterator();
    L4:
        if (r0.hasNext() == false) goto L14;
        ?? r1 = r0.next();
        K r2 = r5.keyOf(r1);
        R r3 = (Object) r6.get(r2);
        if (r3 == null) goto L8;
    L10:
        boolean r4 = false;
    L11:
        if (r4 == false) goto L13;
        r3 = (Object) r7;
    L13:
        r6.put(r2, r8.invoke(r3, r1));
        goto L4
    L8:
        if (r6.containsKey(r2) == true) goto L10;
        r4 = true;
        goto L11
    L14:
        return r6;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object] */
    @SinceKotlin(version = "1.1")
    public static final <T, K, R, M extends Map<? super K, R>> M foldTo(Grouping<T, ? extends K> r5, M r6, Function2<? super K, ? super T, ? extends R> r7, Function3<? super K, ? super R, ? super T, ? extends R> r8) {
        Iterator<T> r0 = r5.sourceIterator();
    L4:
        if (r0.hasNext() == false) goto L14;
        ?? r1 = r0.next();
        Object r2 = r5.keyOf(r1);
        R r3 = (Object) r6.get(r2);
        if (r3 == null) goto L8;
    L10:
        boolean r4 = false;
    L11:
        if (r4 == false) goto L13;
        r3 = r7.invoke(r2, r1);
    L13:
        r6.put(r2, r8.invoke(r2, r3, r1));
        goto L4
    L8:
        if (r6.containsKey(r2) == true) goto L10;
        r4 = true;
        goto L11
    L14:
        return r6;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.1")
    public static final <S, T extends S, K> Map<K, S> reduce(Grouping<T, ? extends K> r6, Function3<? super K, ? super S, ? super T, ? extends S> r7) {
        LinkedHashMap r0 = new LinkedHashMap();
        Iterator r1 = r6.sourceIterator();
    L4:
        if (r1.hasNext() == false) goto L15;
        S r2 = (Object) r1.next();
        Object r3 = r6.keyOf(r2);
        Manifest.permission_group r4 = (Object) r0.get(r3);
        if (r4 == null) goto L8;
    L10:
        boolean r5 = false;
    L11:
        if (r5 == true) goto L14;
        r2 = r7.invoke(r3, r4, r2);
    L14:
        r0.put(r3, r2);
        goto L4
    L8:
        if (r0.containsKey(r3) == true) goto L10;
        r5 = true;
        goto L11
    L15:
        return r0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.1")
    public static final <S, T extends S, K, M extends Map<? super K, S>> M reduceTo(Grouping<T, ? extends K> r5, M r6, Function3<? super K, ? super S, ? super T, ? extends S> r7) {
        Iterator r0 = r5.sourceIterator();
    L4:
        if (r0.hasNext() == false) goto L15;
        S r1 = (Object) r0.next();
        Object r2 = r5.keyOf(r1);
        Manifest.permission_group r3 = (Object) r6.get(r2);
        if (r3 == null) goto L8;
    L10:
        boolean r4 = false;
    L11:
        if (r4 == true) goto L14;
        r1 = r7.invoke(r2, r3, r1);
    L14:
        r6.put(r2, r1);
        goto L4
    L8:
        if (r6.containsKey(r2) == true) goto L10;
        r4 = true;
        goto L11
    L15:
        return r6;
    }
}
