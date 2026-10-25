package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMutableCollection;
import kotlin.jvm.internal.markers.KMutableSet;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001f\n\u0002\u0010#\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0012\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0012\u0004\u0012\u00028\u00000\u0003:\u0001EB\u0013\b\u0007\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\f\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0004¢\u0006\u0004\b\f\u0010\u0007J\u0018\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0013\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0016\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00020\u0004¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u001a\u0010\u0010J\u0017\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u001b\u0010\u0010J\u0015\u0010\u001c\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00020\u0004¢\u0006\u0004\b\u001c\u0010\u0017J\u0015\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u001d¢\u0006\u0004\b\u001e\u0010\u001fJ'\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00010\u001d\"\u0004\b\u0001\u0010 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00010\u001d¢\u0006\u0004\b\u001e\u0010\"J\u001a\u0010$\u001a\u00020\u000e2\b\u0010#\u001a\u0004\u0018\u00010\u0011H\u0096\u0002¢\u0006\u0004\b$\u0010\u0010J\u000f\u0010%\u001a\u00020\u0004H\u0016¢\u0006\u0004\b%\u0010&J\u000f\u0010(\u001a\u00020'H\u0016¢\u0006\u0004\b(\u0010)J\u0016\u0010+\u001a\b\u0012\u0004\u0012\u00028\u00000*H\u0096\u0002¢\u0006\u0004\b+\u0010,J\u001d\u0010/\u001a\u00020\u000e2\f\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00000-H\u0016¢\u0006\u0004\b/\u00100J\u001d\u00101\u001a\u00020\u000e2\f\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00000-H\u0016¢\u0006\u0004\b1\u00100J\u001d\u00102\u001a\u00020\u000e2\f\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00000-H\u0016¢\u0006\u0004\b2\u00100J\u001d\u00103\u001a\u00020\u000e2\f\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00000-H\u0016¢\u0006\u0004\b3\u00100R\"\u00105\u001a\u0002048\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b5\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R*\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u001d8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b!\u0010;\u001a\u0004\b<\u0010\u001f\"\u0004\b=\u0010>R\"\u0010?\u001a\u00020\u00048\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b?\u0010@\u001a\u0004\bA\u0010&\"\u0004\bB\u0010\u0007R\u0014\u0010D\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bC\u0010&¨\u0006F"}, d2 = {"Landroidx/collection/ArraySet;", "E", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "capacity", "<init>", "(I)V", _UrlKt.FRAGMENT_ENCODE_SET, "clear", "()V", "minimumCapacity", "ensureCapacity", "element", _UrlKt.FRAGMENT_ENCODE_SET, "contains", "(Ljava/lang/Object;)Z", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.KEY, "indexOf", "(Ljava/lang/Object;)I", "index", "valueAt", "(I)Ljava/lang/Object;", "isEmpty", "()Z", "add", "remove", "removeAt", _UrlKt.FRAGMENT_ENCODE_SET, "toArray", "()[Ljava/lang/Object;", "T", "array", "([Ljava/lang/Object;)[Ljava/lang/Object;", "other", "equals", "hashCode", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "iterator", "()Ljava/util/Iterator;", _UrlKt.FRAGMENT_ENCODE_SET, "elements", "containsAll", "(Ljava/util/Collection;)Z", "addAll", "removeAll", "retainAll", _UrlKt.FRAGMENT_ENCODE_SET, "hashes", "[I", "getHashes$collection", "()[I", "setHashes$collection", "([I)V", "[Ljava/lang/Object;", "getArray$collection", "setArray$collection", "([Ljava/lang/Object;)V", "_size", "I", "get_size$collection", "set_size$collection", "getSize", "size", "ElementIterator", "collection"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nArraySet.jvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ArraySet.jvm.kt\nandroidx/collection/ArraySet\n+ 2 ArraySet.kt\nandroidx/collection/ArraySetKt\n*L\n1#1,283:1\n288#2,10:284\n301#2,14:294\n318#2:308\n323#2:309\n328#2:310\n333#2:311\n338#2,61:312\n403#2,17:373\n423#2,6:390\n433#2,60:396\n501#2,9:456\n514#2,22:465\n540#2,7:487\n551#2,19:494\n574#2,6:513\n584#2,6:519\n594#2,5:525\n603#2,8:530\n*S KotlinDebug\n*F\n+ 1 ArraySet.jvm.kt\nandroidx/collection/ArraySet\n*L\n89#1:284,10\n98#1:294,14\n108#1:308\n118#1:309\n128#1:310\n133#1:311\n145#1:312,61\n155#1:373,17\n165#1:390,6\n176#1:396,60\n185#1:456,9\n210#1:465,22\n215#1:487,7\n223#1:494,19\n250#1:513,6\n259#1:519,6\n269#1:525,5\n280#1:530,8\n*E\n"})
/* loaded from: classes.dex */
public final class ArraySet<E> implements Collection<E>, Set<E>, KMutableCollection, KMutableSet {
    private int _size;
    private Object[] array;
    private int[] hashes;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00020\u0005H\u0014¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u0014¨\u0006\t"}, d2 = {"Landroidx/collection/ArraySet$ElementIterator;", "Landroidx/collection/IndexBasedArrayIterator;", "(Landroidx/collection/ArraySet;)V", "elementAt", "index", _UrlKt.FRAGMENT_ENCODE_SET, "(I)Ljava/lang/Object;", "removeAt", _UrlKt.FRAGMENT_ENCODE_SET, "collection"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class ElementIterator extends IndexBasedArrayIterator<E> {
        final /* synthetic */ ArraySet<E> this$0;

        public ElementIterator(ArraySet r1) {
            this.this$0 = r1;
            super(r1.get_size$collection());
        }

        @Override // androidx.collection.IndexBasedArrayIterator
        public E elementAt(int r1) {
            return this.this$0.valueAt(r1);
        }

        @Override // androidx.collection.IndexBasedArrayIterator
        public void removeAt(int r1) {
            this.this$0.removeAt(r1);
        }
    }

    @JvmOverloads
    public ArraySet() {
        this(0, 1, null);
    }

    @JvmOverloads
    public ArraySet(int r2) {
        this.hashes = ContainerHelpersKt.EMPTY_INTS;
        this.array = ContainerHelpersKt.EMPTY_OBJECTS;
        if (r2 <= 0) goto L6;
        ArraySetKt.allocArrays(this, r2);
        return;
    }

    public /* synthetic */ ArraySet(int r1, int r2, DefaultConstructorMarker r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = 0;
    L5:
        this(r1);
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(E r18) {
        int r2 = get_size$collection();
        if (r18 != null) goto L5;
        int r4 = ArraySetKt.indexOfNull(this);
        int r5 = 0;
    L6:
        if (r4 < 0) goto L8;
        return false;
    L8:
        int r42 = ~r4;
        if (r2 < getHashes$collection().length) goto L27;
        int r6 = 8;
        if (r2 < 8) goto L14;
        r6 = (r2 >> 1) + r2;
    L17:
        int[] r8 = getHashes$collection();
        Object[] r15 = getArray$collection();
        ArraySetKt.allocArrays(this, r6);
        if (r2 == get_size$collection()) goto L20;
        ArraySet$$ExternalSyntheticBUOutline0.m();
        return false;
    L20:
        if (getHashes$collection().length != 0) goto L22;
        boolean r62 = true;
    L23:
        if (r62 == true) goto L27;
        ArraysKt.copyInto$default(r8, getHashes$collection(), 0, 0, r8.length, 6, null);
        ArraysKt.copyInto$default(r15, getArray$collection(), 0, 0, r15.length, 6, null);
        goto L27
    L22:
        r62 = false;
        goto L23
    L14:
        if (r2 >= 4) goto L17;
        r6 = 4;
    L27:
        if (r42 >= r2) goto L30;
        int r9 = r42 + 1;
        ArraysKt.copyInto(getHashes$collection(), getHashes$collection(), r9, r42, r2);
        ArraysKt.copyInto(getArray$collection(), getArray$collection(), r9, r42, r2);
    L30:
        if (r2 == get_size$collection()) goto L32;
    L35:
        ArraySet$$ExternalSyntheticBUOutline0.m();
        return false;
    L32:
        if (r42 >= getHashes$collection().length) goto L35;
        getHashes$collection()[r42] = r5;
        getArray$collection()[r42] = r18;
        set_size$collection(get_size$collection() + 1);
        return true;
    L5:
        int r43 = r18.hashCode();
        r5 = r43;
        r4 = ArraySetKt.indexOf(this, r18, r43);
        goto L6
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> r3) {
        ensureCapacity(get_size$collection() + r3.size());
        Iterator<? extends E> r32 = r3.iterator();
        boolean r0 = false;
    L4:
        if (r32.hasNext() == false) goto L6;
        r0 = r0 | add(r32.next());
        goto L4
    L6:
        return r0;
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        if (get_size$collection() == 0) goto L6;
        setHashes$collection(ContainerHelpersKt.EMPTY_INTS);
        setArray$collection(ContainerHelpersKt.EMPTY_OBJECTS);
        set_size$collection(0);
    L6:
        if (get_size$collection() != 0) goto L8;
        return;
    L8:
        ArraySet$$ExternalSyntheticBUOutline0.m();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean contains(Object r1) {
        if (indexOf(r1) < 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(Collection<? extends Object> r2) {
        Iterator<? extends Object> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L9;
        if (contains(r22.next()) == true) goto L4;
        return false;
    L9:
        return true;
    }

    public final void ensureCapacity(int r11) {
        int r0 = get_size$collection();
        if (getHashes$collection().length >= r11) goto L8;
        int[] r2 = getHashes$collection();
        Object[] r1 = getArray$collection();
        ArraySetKt.allocArrays(this, r11);
        if (get_size$collection() <= 0) goto L8;
        ArraysKt.copyInto$default(r2, getHashes$collection(), 0, 0, get_size$collection(), 6, null);
        ArraysKt.copyInto$default(r1, getArray$collection(), 0, 0, get_size$collection(), 6, null);
    L8:
        if (get_size$collection() != r0) goto L10;
        return;
    L10:
        ArraySet$$ExternalSyntheticBUOutline0.m();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object r7) {
        if (this != r7) goto L6;
        return true;
    L6:
        if ((r7 instanceof Set) == true) goto L8;
    L17:
        return false;
    L8:
        if (size() == ((Set) r7).size()) goto L19;
        return false;
    L19:
        int r1 = get_size$collection();     // Catch: Throwable -> L18
        int r3 = 0;
    L11:
        if (r3 >= r1) goto L16;
        if (((Set) r7).contains(valueAt(r3)) == false) goto L14;
        r3 = r3 + 1;
        goto L11
    L14:
        return false;
    L16:
        return true;
    }

    public final Object[] getArray$collection() {
        return this.array;
    }

    public final int[] getHashes$collection() {
        return this.hashes;
    }

    public int getSize() {
        return this._size;
    }

    public final int get_size$collection() {
        return this._size;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int[] r0 = getHashes$collection();
        int r4 = get_size$collection();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r4) goto L5;
        r2 = r2 + r0[r1];
        r1 = r1 + 1;
        goto L3
    L5:
        return r2;
    }

    public final int indexOf(Object r2) {
        if (r2 != null) goto L6;
        return ArraySetKt.indexOfNull(this);
    L6:
        return ArraySetKt.indexOf(this, r2, r2.hashCode());
    }

    @Override // java.util.Collection, java.util.Set
    public boolean isEmpty() {
        if (get_size$collection() > 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return new ElementIterator(this);
    }

    @Override // java.util.Collection, java.util.Set
    public boolean remove(Object r1) {
        int r12 = indexOf(r1);
        if (r12 < 0) goto L6;
        removeAt(r12);
        return true;
    L6:
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(Collection<? extends Object> r3) {
        Iterator<? extends Object> r32 = r3.iterator();
        boolean r0 = false;
    L4:
        if (r32.hasNext() == false) goto L6;
        r0 = r0 | remove(r32.next());
        goto L4
    L6:
        return r0;
    }

    public final E removeAt(int r15) {
        int r0 = get_size$collection();
        E r1 = (E) getArray$collection()[r15];
        if (r0 > 1) goto L6;
        clear();
        return r1;
    L6:
        int r3 = r0 - 1;
        int r5 = 8;
        if (getHashes$collection().length <= 8) goto L20;
        if (get_size$collection() >= (getHashes$collection().length / 3)) goto L20;
        if (get_size$collection() <= 8) goto L13;
        r5 = get_size$collection() + (get_size$collection() >> 1);
    L13:
        int[] r6 = getHashes$collection();
        Object[] r2 = getArray$collection();
        ArraySetKt.allocArrays(this, r5);
        if (r15 <= 0) goto L16;
        ArraysKt.copyInto$default(r6, getHashes$collection(), 0, 0, r15, 6, null);
        Object[] r7 = r2;
        ArraysKt.copyInto$default(r7, getArray$collection(), 0, 0, r15, 6, null);
        int r10 = r15;
    L17:
        if (r10 >= r3) goto L24;
        int r22 = r10 + 1;
        ArraysKt.copyInto(r6, getHashes$collection(), r10, r22, r0);
        ArraysKt.copyInto(r7, getArray$collection(), r10, r22, r0);
    L24:
        if (r0 != get_size$collection()) goto L27;
        set_size$collection(r3);
        return r1;
    L27:
        ArraySet$$ExternalSyntheticBUOutline0.m();
        return null;
    L16:
        r10 = r15;
        r7 = r2;
    L20:
        if (r15 >= r3) goto L22;
        int r4 = r15 + 1;
        ArraysKt.copyInto(getHashes$collection(), getHashes$collection(), r15, r4, r0);
        ArraysKt.copyInto(getArray$collection(), getArray$collection(), r15, r4, r0);
    L22:
        getArray$collection()[r3] = null;
        goto L24
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(Collection<? extends Object> r6) {
        int r0 = get_size$collection() - 1;
        boolean r2 = false;
    L4:
        if ((-1) >= r0) goto L9;
        if (CollectionsKt.contains(r6, getArray$collection()[r0]) == true) goto L8;
        removeAt(r0);
        r2 = true;
    L8:
        r0 = r0 - 1;
        goto L4
    L9:
        return r2;
    }

    public final void setArray$collection(Object[] r1) {
        this.array = r1;
    }

    public final void setHashes$collection(int[] r1) {
        this.hashes = r1;
    }

    public final void set_size$collection(int r1) {
        this._size = r1;
    }

    @Override // java.util.Collection, java.util.Set
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray() {
        return ArraysKt.copyOfRange(this.array, 0, this._size);
    }

    @Override // java.util.Collection, java.util.Set
    public final <T> T[] toArray(T[] r3) {
        T[] r32 = (T[]) ArraySetJvmUtil.resizeForToArray(r3, this._size);
        ArraysKt.copyInto(this.array, r32, 0, 0, this._size);
        return r32;
    }

    public String toString() {
        if (isEmpty() == false) goto L6;
        return "{}";
    L6:
        StringBuilder r1 = new StringBuilder(get_size$collection() * 14);
        r1.append('{');
        int r0 = get_size$collection();
        int r2 = 0;
    L7:
        if (r2 >= r0) goto L15;
        if (r2 <= 0) goto L10;
        r1.append(", ");
    L10:
        E r3 = valueAt(r2);
        if (r3 == this) goto L13;
        r1.append(r3);
    L14:
        r2 = r2 + 1;
        goto L7
    L13:
        r1.append("(this Set)");
        goto L14
    L15:
        r1.append('}');
        return r1.toString();
    }

    public final E valueAt(int r1) {
        return (E) getArray$collection()[r1];
    }
}
