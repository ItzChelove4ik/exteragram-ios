package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.1")
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b'\u0018\u0000 \f*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\fB\t\bD¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0096\u0082\u0004J\n\u0010\n\u001a\u00020\u000bH\u0096\u0080\u0004¨\u0006\r"}, d2 = {"Lkotlin/collections/AbstractSet;", "E", "Lkotlin/collections/AbstractCollection;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public abstract class AbstractSet<E> extends AbstractCollection<E> implements Set<E>, KMappedMarker {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0010\"\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0080\u0080\u0004¢\u0006\u0002\b\bJ'\u0010\t\u001a\u00020\n2\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u000b2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0080\u0080\u0004¢\u0006\u0002\b\r¨\u0006\u000e"}, d2 = {"Lkotlin/collections/AbstractSet$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "unorderedHashCode", _UrlKt.FRAGMENT_ENCODE_SET, "c", _UrlKt.FRAGMENT_ENCODE_SET, "unorderedHashCode$kotlin_stdlib", "setEquals", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "other", "setEquals$kotlin_stdlib", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final boolean setEquals$kotlin_stdlib(Set<?> r2, Set<?> r3) {
            if (r2.size() == r3.size()) goto L7;
            return false;
        L7:
            return r2.containsAll(r3);
        }

        public final int unorderedHashCode$kotlin_stdlib(Collection<?> r3) {
            Iterator<?> r2 = r3.iterator();
            int r0 = 0;
        L4:
            if (r2.hasNext() == false) goto L10;
            Object r1 = r2.next();
            if (r1 == null) goto L8;
            int r12 = r1.hashCode();
        L9:
            r0 = r0 + r12;
            goto L4
        L8:
            r12 = 0;
            goto L9
        L10:
            return r0;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public AbstractSet() {
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof Set) == true) goto L10;
        return false;
    L10:
        return Companion.setEquals$kotlin_stdlib(this, (Set) r2);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return Companion.unorderedHashCode$kotlin_stdlib(this);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
