package kotlin.collections.builders;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.InvalidObjectException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.AbstractMutableSet;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMutableSet;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0004\b\u0000\u0018\u0000 (*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00060\u0004j\u0002`\u0005:\u0001(B\u001b\b@\u0012\u0010\u0010\u0006\u001a\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u0007¢\u0006\u0004\b\b\u0010\tB\t\bV¢\u0006\u0004\b\b\u0010\nB\u0011\bV\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\b\u0010\rJ\u0010\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0086\u0080\u0004J\n\u0010\u0010\u001a\u00020\u0011H\u0082\u0080\u0004J\u0012\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0082\u0080\u0004J\n\u0010\u0019\u001a\u00020\u001aH\u0096\u0080\u0004J\u0017\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u001dJ\n\u0010\u001e\u001a\u00020\u0013H\u0096\u0080\u0004J\u0017\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00028\u0000H\u0096\u0080\b¢\u0006\u0002\u0010\u001dJ\u0017\u0010 \u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00028\u0000H\u0096\u0080\b¢\u0006\u0002\u0010\u001dJ\u0010\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\"H\u0096\u0082\u0004J\u0018\u0010#\u001a\u00020\u001a2\f\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000%H\u0096\u0080\bJ\u0018\u0010&\u001a\u00020\u001a2\f\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000%H\u0096\u0080\bJ\u0018\u0010'\u001a\u00020\u001a2\f\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000%H\u0096\u0080\bR\u0019\u0010\u0006\u001a\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\u0016\u001a\u00020\f8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006)"}, d2 = {"Lkotlin/collections/builders/SetBuilder;", "E", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/collections/AbstractMutableSet;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "backing", "Lkotlin/collections/builders/MapBuilder;", "<init>", "(Lkotlin/collections/builders/MapBuilder;)V", "()V", "initialCapacity", _UrlKt.FRAGMENT_ENCODE_SET, "(I)V", "build", _UrlKt.FRAGMENT_ENCODE_SET, "writeReplace", _UrlKt.FRAGMENT_ENCODE_SET, "readObject", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.TYPE_INPUT, "Ljava/io/ObjectInputStream;", "size", "getSize", "()I", "isEmpty", _UrlKt.FRAGMENT_ENCODE_SET, "contains", "element", "(Ljava/lang/Object;)Z", "clear", "add", "remove", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "addAll", "elements", _UrlKt.FRAGMENT_ENCODE_SET, "removeAll", "retainAll", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SetBuilder<E> extends AbstractMutableSet<E> implements Set<E>, Serializable, KMutableSet {
    private static final Companion Companion = null;
    private static final SetBuilder Empty = null;
    private final MapBuilder<E, ?> backing;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lkotlin/collections/builders/SetBuilder$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Empty", "Lkotlin/collections/builders/SetBuilder;", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
        Empty = new SetBuilder(MapBuilder.Companion.getEmpty$kotlin_stdlib());
    }

    public SetBuilder() {
        this(new MapBuilder());
    }

    public SetBuilder(int r2) {
        this(new MapBuilder(r2));
    }

    public SetBuilder(MapBuilder<E, ?> r1) {
        this.backing = r1;
    }

    private final void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization is supported via proxy only");
    }

    private final Object writeReplace() {
        if (this.backing.isReadOnly$kotlin_stdlib() == false) goto L7;
        return new SerializedCollection(this, 1);
    L7:
        throw new NotSerializableException("The set cannot be serialized while it is being built.");
    }

    @Override // kotlin.collections.AbstractMutableSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(E r1) {
        if (this.backing.addKey$kotlin_stdlib(r1) < 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> r2) {
        this.backing.checkIsMutable$kotlin_stdlib();
        return super.addAll(r2);
    }

    public final Set<E> build() {
        this.backing.build();
        if (size() <= 0) goto L6;
        return this;
    L6:
        return Empty;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.backing.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object r1) {
        return this.backing.containsKey(r1);
    }

    @Override // kotlin.collections.AbstractMutableSet
    public int getSize() {
        return this.backing.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.backing.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return this.backing.keysIterator$kotlin_stdlib();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object r1) {
        return this.backing.removeKey$kotlin_stdlib(r1);
    }

    @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean removeAll(Collection<?> r2) {
        this.backing.checkIsMutable$kotlin_stdlib();
        return super.removeAll(r2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean retainAll(Collection<?> r2) {
        this.backing.checkIsMutable$kotlin_stdlib();
        return super.retainAll(r2);
    }
}
