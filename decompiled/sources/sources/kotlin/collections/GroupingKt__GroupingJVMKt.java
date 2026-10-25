package kotlin.collections;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.TypeIntrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0000\u001a2\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u0005H\u0087\u0080\u0004\u001a[\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\t\"\u0004\b\u0002\u0010\b*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\u00072\u001e\u0010\n\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\f\u0012\u0004\u0012\u0002H\b0\u000bH\u0081\u0088\u0004ø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\r"}, d2 = {"eachCount", _UrlKt.FRAGMENT_ENCODE_SET, "K", _UrlKt.FRAGMENT_ENCODE_SET, "T", "Lkotlin/collections/Grouping;", "mapValuesInPlace", _UrlKt.FRAGMENT_ENCODE_SET, "R", "V", "f", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/collections/GroupingKt")
@SourceDebugExtension({"SMAP\nGroupingJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GroupingJVM.kt\nkotlin/collections/GroupingKt__GroupingJVMKt\n+ 2 Grouping.kt\nkotlin/collections/GroupingKt__GroupingKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,52:1\n143#2:53\n80#2,4:54\n85#2:59\n1#3:58\n1915#4,2:60\n*S KotlinDebug\n*F\n+ 1 GroupingJVM.kt\nkotlin/collections/GroupingKt__GroupingJVMKt\n*L\n22#1:53\n22#1:54,4\n22#1:59\n48#1:60,2\n*E\n"})
/* loaded from: classes5.dex */
class GroupingKt__GroupingJVMKt {
    public GroupingKt__GroupingJVMKt() {
    }

    @SinceKotlin(version = "1.1")
    public static final <T, K> Map<K, Integer> eachCount(Grouping<T, ? extends K> r6) {
        LinkedHashMap r0 = new LinkedHashMap();
        Iterator<T> r1 = r6.sourceIterator();
    L4:
        if (r1.hasNext() == false) goto L14;
        K r2 = r6.keyOf(r1.next());
        Object r3 = r0.get(r2);
        if (r3 == null) goto L8;
    L10:
        boolean r5 = false;
    L11:
        if (r5 == false) goto L13;
        r3 = new Ref.IntRef();
    L13:
        Ref.IntRef r32 = (Ref.IntRef) r3;
        r32.element++;
        r0.put(r2, r32);
        goto L4
    L8:
        if (r0.containsKey(r2) == true) goto L10;
        r5 = true;
        goto L11
    L14:
        Iterator<T> r62 = r0.entrySet().iterator();
    L16:
        if (r62.hasNext() == false) goto L19;
        Map.Entry r12 = (Map.Entry) r62.next();
        TypeIntrinsics.asMutableMapEntry(r12).setValue(Integer.valueOf(((Ref.IntRef) r12.getValue()).element));
        goto L16
    L19:
        return TypeIntrinsics.asMutableMap(r0);
    }

    @PublishedApi
    @InlineOnly
    private static final <K, V, R> Map<K, R> mapValuesInPlace(Map<K, V> r3, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> r4) {
        Iterator<T> r0 = r3.entrySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L7;
        Map.Entry r1 = (Map.Entry) r0.next();
        TypeIntrinsics.asMutableMapEntry(r1).setValue(r4.invoke(r1));
        goto L4
    L7:
        return TypeIntrinsics.asMutableMap(r3);
    }
}
