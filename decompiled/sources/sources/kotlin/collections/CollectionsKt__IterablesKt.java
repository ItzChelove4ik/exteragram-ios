package kotlin.collections;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.PublishedApi;
import kotlin.TuplesKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a/\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0014\b\u0004\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004H\u0087\u0088\u0004ø\u0001\u0000\u001a!\u0010\u0006\u001a\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0081\u0080\u0004¢\u0006\u0002\u0010\b\u001a\"\u0010\t\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\n\u001a\u00020\u0007H\u0081\u0080\u0004\u001a&\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\f\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0001H\u0086\u0080\u0004\u001aD\u0010\r\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000f0\f0\u000e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u000f*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u000f0\u000e0\u0001H\u0086\u0080\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0010"}, d2 = {"Iterable", _UrlKt.FRAGMENT_ENCODE_SET, "T", "iterator", "Lkotlin/Function0;", _UrlKt.FRAGMENT_ENCODE_SET, "collectionSizeOrNull", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/Iterable;)Ljava/lang/Integer;", "collectionSizeOrDefault", PluginsConstants.Settings.DEFAULT, "flatten", _UrlKt.FRAGMENT_ENCODE_SET, "unzip", "Lkotlin/Pair;", "R", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/collections/CollectionsKt")
/* loaded from: classes.dex */
class CollectionsKt__IterablesKt extends CollectionsKt__CollectionsKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0010\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0082\u0004¨\u0006\u0004"}, d2 = {"kotlin/collections/CollectionsKt__IterablesKt$Iterable$1", _UrlKt.FRAGMENT_ENCODE_SET, "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 176)
    @SourceDebugExtension({"SMAP\nIterables.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Iterables.kt\nkotlin/collections/CollectionsKt__IterablesKt$Iterable$1\n*L\n1#1,70:1\n*E\n"})
    /* renamed from: kotlin.collections.CollectionsKt__IterablesKt$Iterable$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass1<T> implements Iterable<T>, KMappedMarker {
        final /* synthetic */ Function0<Iterator<T>> $iterator;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Function0<? extends Iterator<? extends T>> r1) {
            this.$iterator = r1;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return this.$iterator.invoke();
        }
    }

    public CollectionsKt__IterablesKt() {
    }

    @InlineOnly
    private static final <T> Iterable<T> Iterable(Function0<? extends Iterator<? extends T>> r1) {
        return new AnonymousClass1(r1);
    }

    @PublishedApi
    public static <T> int collectionSizeOrDefault(Iterable<? extends T> r1, int r2) {
        if ((r1 instanceof Collection) == true) goto L5;
        return r2;
    L5:
        return ((Collection) r1).size();
    }

    @PublishedApi
    public static final <T> Integer collectionSizeOrNull(Iterable<? extends T> r1) {
        if ((r1 instanceof Collection) == true) goto L5;
        return null;
    L5:
        return Integer.valueOf(((Collection) r1).size());
    }

    public static final <T> List<T> flatten(Iterable<? extends Iterable<? extends T>> r2) {
        ArrayList r0 = new ArrayList();
        Iterator<? extends Iterable<? extends T>> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        CollectionsKt__MutableCollectionsKt.addAll(r0, r22.next());
        goto L4
    L6:
        return r0;
    }

    public static final <T, R> Pair<List<T>, List<R>> unzip(Iterable<? extends Pair<? extends T, ? extends R>> r4) {
        int r0 = collectionSizeOrDefault(r4, 10);
        ArrayList r1 = new ArrayList(r0);
        ArrayList r2 = new ArrayList(r0);
        Iterator<? extends Pair<? extends T, ? extends R>> r42 = r4.iterator();
    L4:
        if (r42.hasNext() == false) goto L7;
        Pair<? extends T, ? extends R> r02 = r42.next();
        r1.add(r02.getFirst());
        r2.add(r02.getSecond());
        goto L4
    L7:
        return TuplesKt.to(r1, r2);
    }
}
