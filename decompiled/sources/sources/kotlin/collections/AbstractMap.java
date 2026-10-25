package kotlin.collections;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.signature.SignatureVisitor;

@SinceKotlin(version = "1.1")
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010&\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0007\b'\u0018\u0000 **\u0004\b\u0000\u0010\u0001*\u0006\b\u0001\u0010\u0002 \u00012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003:\u0001*B\t\bD¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\tJ\u0017\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00028\u0001H\u0096\u0080\u0004¢\u0006\u0002\u0010\tJ!\u0010\f\u001a\u00020\u00072\u0010\u0010\r\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\u000eH\u0080\u0080\u0004¢\u0006\u0002\b\u000fJ\u0014\u0010\u0010\u001a\u00020\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0096\u0082\u0004J\u0019\u0010\u0013\u001a\u0004\u0018\u00018\u00012\u0006\u0010\b\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0014J\n\u0010\u0015\u001a\u00020\u0016H\u0096\u0080\u0004J\n\u0010\u0017\u001a\u00020\u0007H\u0096\u0080\u0004J\n\u0010 \u001a\u00020!H\u0096\u0080\u0004J\u001e\u0010 \u001a\u00020!2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000eH\u0082\u0080\u0004J\u0014\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0012H\u0082\u0080\u0004J%\u0010(\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000e2\u0006\u0010\b\u001a\u00028\u0000H\u0082\u0080\u0004¢\u0006\u0002\u0010)R\u0015\u0010\u0018\u001a\u00020\u00168VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001c8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u001f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u001cX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u001b\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00010$8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0017\u0010'\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010$X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lkotlin/collections/AbstractMap;", "K", "V", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "containsKey", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.KEY, "(Ljava/lang/Object;)Z", "containsValue", "value", "containsEntry", "entry", _UrlKt.FRAGMENT_ENCODE_SET, "containsEntry$kotlin_stdlib", "equals", "other", _UrlKt.FRAGMENT_ENCODE_SET, "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "isEmpty", "size", "getSize", "()I", "keys", _UrlKt.FRAGMENT_ENCODE_SET, "getKeys", "()Ljava/util/Set;", "_keys", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "o", "values", _UrlKt.FRAGMENT_ENCODE_SET, "getValues", "()Ljava/util/Collection;", "_values", "implFindEntry", "(Ljava/lang/Object;)Ljava/util/Map$Entry;", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAbstractMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractMap.kt\nkotlin/collections/AbstractMap\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,153:1\n1807#2,3:154\n1786#2,3:157\n296#2,2:160\n*S KotlinDebug\n*F\n+ 1 AbstractMap.kt\nkotlin/collections/AbstractMap\n*L\n28#1:154,3\n60#1:157,3\n141#1:160,2\n*E\n"})
/* loaded from: classes5.dex */
public abstract class AbstractMap<K, V> implements Map<K, V>, KMappedMarker {
    public static final Companion Companion = null;
    private volatile Set<? extends K> _keys;
    private volatile Collection<? extends V> _values;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010&\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0007H\u0080\u0080\u0004¢\u0006\u0002\b\bJ\u001f\u0010\t\u001a\u00020\n2\u000e\u0010\u0006\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0007H\u0080\u0080\u0004¢\u0006\u0002\b\u000bJ)\u0010\f\u001a\u00020\r2\u000e\u0010\u0006\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u0080\u0080\u0004¢\u0006\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lkotlin/collections/AbstractMap$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "entryHashCode", _UrlKt.FRAGMENT_ENCODE_SET, "e", _UrlKt.FRAGMENT_ENCODE_SET, "entryHashCode$kotlin_stdlib", "entryToString", _UrlKt.FRAGMENT_ENCODE_SET, "entryToString$kotlin_stdlib", "entryEquals", _UrlKt.FRAGMENT_ENCODE_SET, "other", "entryEquals$kotlin_stdlib", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nAbstractMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractMap.kt\nkotlin/collections/AbstractMap$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,153:1\n1#2:154\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final boolean entryEquals$kotlin_stdlib(Map.Entry<?, ?> r3, Object r4) {
            if ((r4 instanceof Map.Entry) == true) goto L5;
            return false;
        L5:
            Map.Entry r42 = (Map.Entry) r4;
            if (Intrinsics.areEqual(r3.getKey(), r42.getKey()) == true) goto L8;
        L11:
            return false;
        L8:
            if (Intrinsics.areEqual(r3.getValue(), r42.getValue()) == false) goto L11;
            return true;
        }

        public final int entryHashCode$kotlin_stdlib(Map.Entry<?, ?> r2) {
            Object r1 = r2.getKey();
            int r0 = 0;
            if (r1 == null) goto L5;
            int r12 = r1.hashCode();
        L6:
            Object r22 = r2.getValue();
            if (r22 == null) goto L10;
            r0 = r22.hashCode();
        L10:
            return r12 ^ r0;
        L5:
            r12 = 0;
            goto L6
        }

        public final String entryToString$kotlin_stdlib(Map.Entry<?, ?> r2) {
            StringBuilder r1 = new StringBuilder();
            r1.append(r2.getKey());
            r1.append(SignatureVisitor.INSTANCEOF);
            r1.append(r2.getValue());
            return r1.toString();
        }
    }

    @Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010(\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0017\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0096\u0082\u0004R\u0015\u0010\b\u001a\u00020\t8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"kotlin/collections/AbstractMap$values$1", "Lkotlin/collections/AbstractCollection;", "contains", _UrlKt.FRAGMENT_ENCODE_SET, "element", "(Ljava/lang/Object;)Z", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "size", _UrlKt.FRAGMENT_ENCODE_SET, "getSize", "()I", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    /* renamed from: kotlin.collections.AbstractMap$values$1, reason: invalid class name */
    public static final class AnonymousClass1 extends AbstractCollection<V> {
        final /* synthetic */ AbstractMap<K, V> this$0;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(AbstractMap<K, ? extends V> r1) {
            this.this$0 = r1;
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object r1) {
            return this.this$0.containsValue(r1);
        }

        @Override // kotlin.collections.AbstractCollection
        public int getSize() {
            return this.this$0.size();
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new AbstractMap$values$1$iterator$1(this.this$0.entrySet().iterator());
        }
    }

    public static CharSequence $r8$lambda$OmxMrod7RczKA15m1AWrs64BmJo(AbstractMap r0, Map.Entry r1) {
        return r0.toString(r1);
    }

    static {
        Companion = new Companion(null);
    }

    public AbstractMap() {
    }

    private final Map.Entry<K, V> implFindEntry(K r3) {
        Iterator<T> r2 = entrySet().iterator();
    L4:
        if (r2.hasNext() == false) goto L8;
        Object r0 = r2.next();
        if (Intrinsics.areEqual(((Map.Entry) r0).getKey(), r3) == false) goto L4;
    L10:
        return (Map.Entry) r0;
    L8:
        r0 = null;
        goto L10
    }

    private final String toString(Object r1) {
        if (r1 != this) goto L6;
        return "(this Map)";
    L6:
        return String.valueOf(r1);
    }

    private final String toString(Map.Entry<? extends K, ? extends V> r3) {
        return toString(r3.getKey()) + SignatureVisitor.INSTANCEOF + toString(r3.getValue());
    }

    @Override // java.util.Map
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final boolean containsEntry$kotlin_stdlib(Map.Entry<?, ?> r4) {
        if (r4 != null) goto L5;
        return false;
    L5:
        Object r1 = r4.getKey();
        Object r42 = r4.getValue();
        V r2 = get(r1);
        if (Intrinsics.areEqual(r42, r2) == true) goto L8;
        return false;
    L8:
        if (r2 == null) goto L10;
        return true;
    L10:
        if (containsKey(r1) == true) goto L14;
        return false;
    L14:
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public boolean containsKey(Object r1) {
        if (implFindEntry(r1) == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Map
    public boolean containsValue(Object r3) {
        Set<Map.Entry<K, V>> r2 = entrySet();
        if (r2 != null) goto L5;
    L7:
        Iterator<T> r22 = r2.iterator();
    L9:
        if (r22.hasNext() == false) goto L14;
        if (Intrinsics.areEqual(((Map.Entry) r22.next()).getValue(), r3) == false) goto L9;
        return true;
    L14:
        return false;
    L5:
        if (r2.isEmpty() == false) goto L7;
        return false;
    }

    @Override // java.util.Map
    public final /* bridge */ Set<Map.Entry<K, V>> entrySet() {
        return getEntries();
    }

    @Override // java.util.Map
    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof Map) == true) goto L8;
        return false;
    L8:
        Map r52 = (Map) r5;
        if (size() == r52.size()) goto L11;
        return false;
    L11:
        Set<Map.Entry<K, V>> r53 = r52.entrySet();
        if (r53 != null) goto L14;
    L16:
        Iterator<T> r54 = r53.iterator();
    L18:
        if (r54.hasNext() == false) goto L22;
        if (containsEntry$kotlin_stdlib((Map.Entry) r54.next()) == true) goto L18;
        return false;
    L22:
        return true;
    L14:
        if (r53.isEmpty() == false) goto L16;
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public V get(Object r1) {
        Map.Entry<K, V> r0 = implFindEntry(r1);
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getValue();
    }

    public abstract Set<Map.Entry<K, V>> getEntries();

    public Set<K> getKeys() {
        if (this._keys != null) goto L6;
        this._keys = new AbstractMap$keys$1(this);
    L6:
        return this._keys;
    }

    public int getSize() {
        return entrySet().size();
    }

    public Collection<V> getValues() {
        if (this._values != null) goto L6;
        this._values = new AnonymousClass1(this);
    L6:
        return this._values;
    }

    @Override // java.util.Map
    public int hashCode() {
        return entrySet().hashCode();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        if (size() != 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Map
    public final /* bridge */ Set<K> keySet() {
        return getKeys();
    }

    @Override // java.util.Map
    public V put(K r1, V r2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> r1) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public V remove(Object r1) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    public String toString() {
        return CollectionsKt___CollectionsKt.joinToString$default(entrySet(), ", ", "{", "}", 0, null, new AbstractMap$$ExternalSyntheticLambda0(this), 24, null);
    }

    @Override // java.util.Map
    public final /* bridge */ Collection<V> values() {
        return getValues();
    }
}
