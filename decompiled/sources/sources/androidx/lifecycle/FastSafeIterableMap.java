package androidx.lifecycle;

import androidx.collection.MutableScatterMap;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.UIntArray$Iterator$$ExternalSyntheticBUOutline0;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010&\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002:\u0001!B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0002\u0010\u000eJ\u001d\u0010\u000f\u001a\u0004\u0018\u00018\u00012\u0006\u0010\r\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00028\u0001¢\u0006\u0002\u0010\u0011J\u0015\u0010\u0012\u001a\u0004\u0018\u00018\u00012\u0006\u0010\r\u001a\u00028\u0000¢\u0006\u0002\u0010\u0013J!\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00152\u0006\u0010\r\u001a\u00028\u0000¢\u0006\u0002\u0010\u0016J\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0015J\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0015J\u0014\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0015J\u0006\u0010\u001a\u001a\u00020\u001bJ&\u0010\u001c\u001a\u00020\u001d2\u001e\u0010\u001e\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0015\u0012\u0004\u0012\u00020\u001d0\u001fJ&\u0010 \u001a\u00020\u001d2\u001e\u0010\u001e\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0015\u0012\u0004\u0012\u00020\u001d0\u001fR&\u0010\u0006\u001a\u001a\u0012\u0004\u0012\u00028\u0000\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Landroidx/lifecycle/FastSafeIterableMap;", "K", _UrlKt.FRAGMENT_ENCODE_SET, "V", "<init>", "()V", "map", "Landroidx/collection/MutableScatterMap;", "Landroidx/lifecycle/FastSafeIterableMap$Entry;", "head", "tail", "contains", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.KEY, "(Ljava/lang/Object;)Z", "putIfAbsent", "value", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "remove", "(Ljava/lang/Object;)Ljava/lang/Object;", "ceil", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/Object;)Ljava/util/Map$Entry;", "first", "last", "lastOrNull", "size", _UrlKt.FRAGMENT_ENCODE_SET, "forEachWithAdditions", _UrlKt.FRAGMENT_ENCODE_SET, "action", "Lkotlin/Function1;", "forEachReversed", "Entry", "lifecycle-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FastSafeIterableMap<K, V> {
    private Entry<K, V> head;
    private final MutableScatterMap<K, Entry<K, V>> map;
    private Entry<K, V> tail;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010&\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\b\u0082\b\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0003B\u0017\u0012\u0006\u0010\u0004\u001a\u00028\u0002\u0012\u0006\u0010\u0005\u001a\u00028\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\t\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0004\u001a\u00028\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0005\u001a\u00028\u00038\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0016\u001a\u0004\b\u0019\u0010\u0018R0\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u0003\u0018\u00010\u00008\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR0\u0010 \u001a\u0010\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u0003\u0018\u00010\u00008\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b \u0010\u001b\u001a\u0004\b!\u0010\u001d\"\u0004\b\"\u0010\u001fR$\u0010#\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u00138\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b#\u0010%¨\u0006&"}, d2 = {"Landroidx/lifecycle/FastSafeIterableMap$Entry;", "K", "V", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.KEY, "value", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;)V", _UrlKt.FRAGMENT_ENCODE_SET, "markRemoved", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/Object;", "getKey", "()Ljava/lang/Object;", "getValue", "next", "Landroidx/lifecycle/FastSafeIterableMap$Entry;", "getNext", "()Landroidx/lifecycle/FastSafeIterableMap$Entry;", "setNext", "(Landroidx/lifecycle/FastSafeIterableMap$Entry;)V", "prev", "getPrev", "setPrev", "isRemoved", "Z", "()Z", "lifecycle-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Entry<K, V> implements Map.Entry<K, V>, KMappedMarker {
        private boolean isRemoved;
        private final K key;
        private Entry<K, V> next;
        private Entry<K, V> prev;
        private final V value;

        public Entry(K r1, V r2) {
            this.key = r1;
            this.value = r2;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof Entry) == true) goto L8;
            return false;
        L8:
            Entry r52 = (Entry) r5;
            if (Intrinsics.areEqual(this.key, r52.key) == true) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.value, r52.value) == true) goto L14;
            return false;
        L14:
            return true;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        public final Entry<K, V> getNext() {
            return this.next;
        }

        public final Entry<K, V> getPrev() {
            return this.prev;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K r0 = this.key;
            int r1 = 0;
            if (r0 != null) goto L5;
            int r02 = 0;
        L6:
            int r03 = r02 * 31;
            V r2 = this.value;
            if (r2 == null) goto L11;
            r1 = r2.hashCode();
        L11:
            return r03 + r1;
        L5:
            r02 = r0.hashCode();
            goto L6
        }

        public final boolean isRemoved() {
            return this.isRemoved;
        }

        public final void markRemoved() {
            this.isRemoved = true;
        }

        public final void setNext(Entry<K, V> r1) {
            this.next = r1;
        }

        public final void setPrev(Entry<K, V> r1) {
            this.prev = r1;
        }

        @Override // java.util.Map.Entry
        public V setValue(V r1) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public String toString() {
            return "Entry(key=" + this.key + ", value=" + this.value + ")";
        }
    }

    public FastSafeIterableMap() {
        this.map = new MutableScatterMap(0, 1, null);
    }

    public final Map.Entry<K, V> ceil(K r1) {
        Entry<K, V> r0 = this.map.get(r1);
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getPrev();
    }

    public final boolean contains(K r1) {
        return this.map.containsKey(r1);
    }

    public final Map.Entry<K, V> first() {
        Entry<K, V> r0 = this.head;
        if (r0 == null) goto L5;
        return r0;
    L5:
        UIntArray$Iterator$$ExternalSyntheticBUOutline0.m("Collection is empty.");
        return null;
    }

    public final void forEachReversed(Function1<? super Map.Entry<? extends K, ? extends V>, Unit> r2) {
        Entry<K, V> r1 = this.tail;
    L3:
        if (r1 == null) goto L8;
        if (r1.isRemoved() == true) goto L7;
        r2.invoke(r1);
    L7:
        r1 = r1.getPrev();
        goto L3
    }

    public final void forEachWithAdditions(Function1<? super Map.Entry<? extends K, ? extends V>, Unit> r2) {
        Entry<K, V> r1 = this.head;
    L3:
        if (r1 == null) goto L8;
        if (r1.isRemoved() == true) goto L7;
        r2.invoke(r1);
    L7:
        r1 = r1.getNext();
        goto L3
    }

    public final Map.Entry<K, V> last() {
        Entry<K, V> r0 = this.tail;
        if (r0 == null) goto L5;
        return r0;
    L5:
        UIntArray$Iterator$$ExternalSyntheticBUOutline0.m("Collection is empty.");
        return null;
    }

    public final Map.Entry<K, V> lastOrNull() {
        return this.tail;
    }

    public final V putIfAbsent(K r2, V r3) {
        Entry<K, V> r0 = this.map.get(r2);
        if (r0 != null) goto L5;
        Entry<K, V> r02 = new Entry(r2, r3);
        this.map.set(r2, r02);
        Entry<K, V> r22 = this.tail;
        if (r22 != null) goto L9;
        this.head = r02;
        this.tail = r02;
        return null;
    L9:
        if (r22 == null) goto L11;
        r22.setNext(r02);
    L11:
        r02.setPrev(this.tail);
        this.tail = r02;
        return null;
    L5:
        return r0.getValue();
    }

    public final V remove(K r3) {
        Entry<K, V> r32 = this.map.remove(r3);
        if (r32 != null) goto L7;
        return null;
    L7:
        if (r32.getPrev() != null) goto L9;
        this.head = r32.getNext();
    L13:
        if (r32.getNext() != null) goto L15;
        this.tail = r32.getPrev();
    L18:
        r32.markRemoved();
        return r32.getValue();
    L15:
        Entry<K, V> r2 = r32.getNext();
        if (r2 == null) goto L18;
        r2.setPrev(r32.getPrev());
        goto L18
    L9:
        Entry<K, V> r0 = r32.getPrev();
        if (r0 == null) goto L13;
        r0.setNext(r32.getNext());
        goto L13
    }

    public final int size() {
        return this.map.getSize();
    }
}
