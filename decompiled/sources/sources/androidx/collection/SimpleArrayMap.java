package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import androidx.collection.internal.RuntimeHelpersKt;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Arrays;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.signature.SignatureVisitor;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b$\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003B\u0013\b\u0007\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B#\b\u0016\u0012\u0018\u0010\b\u001a\u0014\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0006\b\u0001\u0012\u00028\u0001\u0018\u00010\u0000¢\u0006\u0004\b\u0006\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u000e\u0010\u0007J\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00028\u0001H\u0001¢\u0006\u0004\b\u0016\u0010\u0014J\u0017\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u0018\u0010\u0012J\u001a\u0010\u0019\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u000f\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ!\u0010\u001c\u001a\u00028\u00012\b\u0010\u000f\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u001b\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001f\u001a\u00028\u00002\u0006\u0010\u001e\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010!\u001a\u00028\u00012\u0006\u0010\u001e\u001a\u00020\u0004H\u0016¢\u0006\u0004\b!\u0010 J\u001f\u0010\"\u001a\u00028\u00012\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\u0010H\u0016¢\u0006\u0004\b$\u0010%J!\u0010&\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00028\u0001H\u0016¢\u0006\u0004\b&\u0010\u001dJ'\u0010'\u001a\u00020\n2\u0016\u0010\b\u001a\u0012\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0006\b\u0001\u0012\u00028\u00010\u0000H\u0016¢\u0006\u0004\b'\u0010\tJ!\u0010(\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00028\u0001H\u0016¢\u0006\u0004\b(\u0010\u001dJ\u0019\u0010)\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u000f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b)\u0010\u001aJ\u001f\u0010)\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00028\u0001H\u0016¢\u0006\u0004\b)\u0010*J\u0017\u0010+\u001a\u00028\u00012\u0006\u0010\u001e\u001a\u00020\u0004H\u0016¢\u0006\u0004\b+\u0010 J!\u0010,\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00028\u0001H\u0016¢\u0006\u0004\b,\u0010\u001dJ'\u0010,\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010-\u001a\u00028\u00012\u0006\u0010.\u001a\u00028\u0001H\u0016¢\u0006\u0004\b,\u0010/J\u000f\u00100\u001a\u00020\u0004H\u0016¢\u0006\u0004\b0\u00101J\u001a\u00103\u001a\u00020\u00102\b\u00102\u001a\u0004\u0018\u00010\u0003H\u0096\u0002¢\u0006\u0004\b3\u0010\u0012J\u000f\u00104\u001a\u00020\u0004H\u0016¢\u0006\u0004\b4\u00101J\u000f\u00106\u001a\u000205H\u0016¢\u0006\u0004\b6\u00107J\u001f\u00109\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u00108\u001a\u00020\u0004H\u0002¢\u0006\u0004\b9\u0010:J\u000f\u0010;\u001a\u00020\u0004H\u0002¢\u0006\u0004\b;\u00101R\u0016\u0010=\u001a\u00020<8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b=\u0010>R\u001e\u0010@\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030?8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b@\u0010AR\u0016\u00100\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b0\u0010B¨\u0006C"}, d2 = {"Landroidx/collection/SimpleArrayMap;", "K", "V", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "capacity", "<init>", "(I)V", "map", "(Landroidx/collection/SimpleArrayMap;)V", _UrlKt.FRAGMENT_ENCODE_SET, "clear", "()V", "minimumCapacity", "ensureCapacity", PluginsConstants.Settings.KEY, _UrlKt.FRAGMENT_ENCODE_SET, "containsKey", "(Ljava/lang/Object;)Z", "indexOfKey", "(Ljava/lang/Object;)I", "value", "__restricted$indexOfValue", "indexOfValue", "containsValue", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "defaultValue", "getOrDefault", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "index", "keyAt", "(I)Ljava/lang/Object;", "valueAt", "setValueAt", "(ILjava/lang/Object;)Ljava/lang/Object;", "isEmpty", "()Z", "put", "putAll", "putIfAbsent", "remove", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "removeAt", "replace", "oldValue", "newValue", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z", "size", "()I", "other", "equals", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "hash", "indexOf", "(Ljava/lang/Object;I)I", "indexOfNull", _UrlKt.FRAGMENT_ENCODE_SET, "hashes", "[I", _UrlKt.FRAGMENT_ENCODE_SET, "array", "[Ljava/lang/Object;", "I", "collection"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSimpleArrayMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SimpleArrayMap.kt\nandroidx/collection/SimpleArrayMap\n+ 2 RuntimeHelpers.kt\nandroidx/collection/internal/RuntimeHelpersKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,761:1\n299#1,5:762\n299#1,5:767\n59#2,5:772\n59#2,5:777\n59#2,5:782\n59#2,5:788\n1#3:787\n*S KotlinDebug\n*F\n+ 1 SimpleArrayMap.kt\nandroidx/collection/SimpleArrayMap\n*L\n278#1:762,5\n294#1:767,5\n315#1:772,5\n330#1:777,5\n346#1:782,5\n512#1:788,5\n*E\n"})
/* loaded from: classes.dex */
public class SimpleArrayMap<K, V> {
    private Object[] array;
    private int[] hashes;
    private int size;

    @JvmOverloads
    public SimpleArrayMap() {
        this(0, 1, null);
    }

    @JvmOverloads
    public SimpleArrayMap(int r2) {
        if (r2 != 0) goto L5;
        int[] r0 = ContainerHelpersKt.EMPTY_INTS;
    L6:
        this.hashes = r0;
        if (r2 != 0) goto L9;
        Object[] r22 = ContainerHelpersKt.EMPTY_OBJECTS;
    L10:
        this.array = r22;
        return;
    L9:
        r22 = new Object[r2 << 1];
        goto L10
    L5:
        r0 = new int[r2];
        goto L6
    }

    public /* synthetic */ SimpleArrayMap(int r1, int r2, DefaultConstructorMarker r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = 0;
    L5:
        this(r1);
    }

    public SimpleArrayMap(SimpleArrayMap<? extends K, ? extends V> r4) {
        this(0, 1, null);
        if (r4 == null) goto L6;
        putAll(r4);
        return;
    }

    private final int indexOf(K r6, int r7) {
        int r0 = this.size;
        if (r0 != 0) goto L6;
        return -1;
    L6:
        int r1 = ContainerHelpersKt.binarySearch(this.hashes, r0, r7);
        if (r1 >= 0) goto L10;
    L11:
        return r1;
    L10:
        if (Intrinsics.areEqual(r6, this.array[r1 << 1]) == true) goto L11;
        int r2 = r1 + 1;
    L13:
        if (r2 >= r0) goto L20;
        if (this.hashes[r2] != r7) goto L20;
        if (Intrinsics.areEqual(r6, this.array[r2 << 1]) == true) goto L18;
        r2 = r2 + 1;
        goto L13
    L18:
        return r2;
    L20:
        int r12 = r1 - 1;
    L21:
        if (r12 < 0) goto L29;
        if (this.hashes[r12] != r7) goto L29;
        if (Intrinsics.areEqual(r6, this.array[r12 << 1]) == true) goto L26;
        r12 = r12 - 1;
        goto L21
    L26:
        return r12;
    L29:
        return ~r2;
    }

    private final int indexOfNull() {
        int r0 = this.size;
        if (r0 != 0) goto L6;
        return -1;
    L6:
        int r1 = ContainerHelpersKt.binarySearch(this.hashes, r0, 0);
        if (r1 >= 0) goto L10;
    L11:
        return r1;
    L10:
        if (this.array[r1 << 1] == null) goto L11;
        int r2 = r1 + 1;
    L13:
        if (r2 >= r0) goto L20;
        if (this.hashes[r2] != 0) goto L20;
        if (this.array[r2 << 1] == null) goto L18;
        r2 = r2 + 1;
        goto L13
    L18:
        return r2;
    L20:
        int r12 = r1 - 1;
    L21:
        if (r12 < 0) goto L29;
        if (this.hashes[r12] != 0) goto L29;
        if (this.array[r12 << 1] == null) goto L26;
        r12 = r12 - 1;
        goto L21
    L26:
        return r12;
    L29:
        return ~r2;
    }

    @JvmName(name = "__restricted$indexOfValue")
    public final int __restricted$indexOfValue(V r5) {
        int r0 = this.size * 2;
        Object[] r4 = this.array;
        if (r5 != null) goto L11;
        int r52 = 1;
    L5:
        if (r52 >= r0) goto L18;
        if (r4[r52] == null) goto L9;
        r52 = r52 + 2;
        goto L5
    L9:
        return r52 >> 1;
    L18:
        return -1;
    L11:
        int r2 = 1;
    L12:
        if (r2 >= r0) goto L24;
        if (Intrinsics.areEqual(r5, r4[r2]) == true) goto L16;
        r2 = r2 + 2;
        goto L12
    L16:
        return r2 >> 1;
    L24:
        return -1;
    }

    public void clear() {
        if (this.size <= 0) goto L6;
        this.hashes = ContainerHelpersKt.EMPTY_INTS;
        this.array = ContainerHelpersKt.EMPTY_OBJECTS;
        this.size = 0;
    L6:
        if (this.size > 0) goto L8;
        return;
    L8:
        ArraySet$$ExternalSyntheticBUOutline0.m();
    }

    public boolean containsKey(K r1) {
        if (indexOfKey(r1) < 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean containsValue(V r1) {
        if (__restricted$indexOfValue(r1) < 0) goto L6;
        return true;
    L6:
        return false;
    }

    public void ensureCapacity(int r4) {
        int r0 = this.size;
        int[] r1 = this.hashes;
        if (r1.length >= r4) goto L6;
        this.hashes = Arrays.copyOf(r1, r4);
        this.array = Arrays.copyOf(this.array, r4 * 2);
    L6:
        if (this.size != r0) goto L8;
        return;
    L8:
        ArraySet$$ExternalSyntheticBUOutline0.m();
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L44;
        return true;
    L44:
        if ((r8 instanceof SimpleArrayMap) == false) goto L25;
        if (size() == ((SimpleArrayMap) r8).size()) goto L11;
        return false;
    L11:
        SimpleArrayMap r82 = (SimpleArrayMap) r8;     // Catch: Throwable -> L43
        int r2 = this.size;     // Catch: Throwable -> L43
        int r3 = 0;
    L12:
        if (r3 >= r2) goto L23;
        K r4 = keyAt(r3);     // Catch: Throwable -> L43
        V r5 = valueAt(r3);     // Catch: Throwable -> L43
        Object r6 = r82.get(r4);     // Catch: Throwable -> L43
        if (r5 != null) goto L20;
        if (r6 != null) goto L18;
        if (r82.containsKey(r4) == false) goto L18;
    L22:
        r3 = r3 + 1;     // Catch: Throwable -> L43
    L18:
        return false;
    L20:
        if (Intrinsics.areEqual(r5, r6) == true) goto L22;
        return false;
    L23:
        return true;
    L25:
        if ((r8 instanceof Map) == false) goto L42;
        if (size() == ((Map) r8).size()) goto L29;
        return false;
    L29:
        int r22 = this.size;     // Catch: Throwable -> L43
        int r32 = 0;
    L30:
        if (r32 >= r22) goto L41;
        K r42 = keyAt(r32);     // Catch: Throwable -> L43
        V r52 = valueAt(r32);     // Catch: Throwable -> L43
        Object r62 = ((Map) r8).get(r42);     // Catch: Throwable -> L43
        if (r52 != null) goto L38;
        if (r62 != null) goto L36;
        if (((Map) r8).containsKey(r42) == false) goto L36;
    L40:
        r32 = r32 + 1;
    L36:
        return false;
    L38:
        if (Intrinsics.areEqual(r52, r62) == true) goto L40;
        return false;
    L41:
        return true;
    L42:
        return false;
    }

    public V get(K r1) {
        int r12 = indexOfKey(r1);
        if (r12 >= 0) goto L5;
        return null;
    L5:
        return (V) this.array[(r12 << 1) + 1];
    }

    /* JADX WARN: Multi-variable type inference failed */
    public V getOrDefault(Object r1, V r2) {
        int r12 = indexOfKey(r1);
        if (r12 >= 0) goto L5;
        return r2;
    L5:
        return (V) this.array[(r12 << 1) + 1];
    }

    public int hashCode() {
        int[] r0 = this.hashes;
        Object[] r1 = this.array;
        int r8 = this.size;
        int r3 = 1;
        int r4 = 0;
        int r5 = 0;
    L3:
        if (r4 >= r8) goto L9;
        Object r6 = r1[r3];
        int r7 = r0[r4];
        if (r6 == null) goto L7;
        int r62 = r6.hashCode();
    L8:
        r5 = r5 + (r62 ^ r7);
        r4 = r4 + 1;
        r3 = r3 + 2;
        goto L3
    L7:
        r62 = 0;
        goto L8
    L9:
        return r5;
    }

    public int indexOfKey(K r2) {
        if (r2 != null) goto L6;
        return indexOfNull();
    L6:
        return indexOf(r2, r2.hashCode());
    }

    public boolean isEmpty() {
        if (this.size > 0) goto L6;
        return true;
    L6:
        return false;
    }

    public K keyAt(int r4) {
        boolean r0 = false;
        if (r4 >= 0) goto L5;
    L7:
        if (r0 == true) goto L10;
        RuntimeHelpersKt.throwIllegalArgumentException("Expected index to be within 0..size()-1, but was " + r4);
    L10:
        return (K) this.array[r4 << 1];
    L5:
        if (r4 >= this.size) goto L7;
        r0 = true;
        goto L7
    }

    public V put(K r9, V r10) {
        int r0 = this.size;
        if (r9 == null) goto L5;
        int r1 = r9.hashCode();
    L6:
        if (r9 == null) goto L8;
        int r2 = indexOf(r9, r1);
    L9:
        if (r2 < 0) goto L12;
        int r92 = (r2 << 1) + 1;
        Object[] r8 = this.array;
        V r02 = (V) r8[r92];
        r8[r92] = r10;
        return r02;
    L12:
        int r22 = ~r2;
        int[] r3 = this.hashes;
        if (r0 < r3.length) goto L26;
        int r4 = 8;
        if (r0 < 8) goto L18;
        r4 = (r0 >> 1) + r0;
    L21:
        this.hashes = Arrays.copyOf(r3, r4);
        this.array = Arrays.copyOf(this.array, r4 << 1);
        if (r0 == this.size) goto L26;
        ArraySet$$ExternalSyntheticBUOutline0.m();
        return null;
    L18:
        if (r0 >= 4) goto L21;
        r4 = 4;
    L26:
        if (r22 >= r0) goto L28;
        int[] r32 = this.hashes;
        int r42 = r22 + 1;
        ArraysKt.copyInto(r32, r32, r42, r22, r0);
        Object[] r33 = this.array;
        ArraysKt.copyInto(r33, r33, r42 << 1, r22 << 1, this.size << 1);
    L28:
        int r34 = this.size;
        if (r0 != r34) goto L34;
        int[] r03 = this.hashes;
        if (r22 >= r03.length) goto L34;
        r03[r22] = r1;
        Object[] r04 = this.array;
        int r12 = r22 << 1;
        r04[r12] = r9;
        r04[r12 + 1] = r10;
        this.size = r34 + 1;
        return null;
    L34:
        ArraySet$$ExternalSyntheticBUOutline0.m();
        return null;
    L8:
        r2 = indexOfNull();
        goto L9
    L5:
        r1 = 0;
        goto L6
    }

    public void putAll(SimpleArrayMap<? extends K, ? extends V> r5) {
        int r0 = r5.size;
        ensureCapacity(this.size + r0);
        int r2 = 0;
        if (this.size != 0) goto L7;
        if (r0 <= 0) goto L9;
        ArraysKt.copyInto(r5.hashes, this.hashes, 0, 0, r0);
        ArraysKt.copyInto(r5.array, this.array, 0, 0, r0 << 1);
        this.size = r0;
        return;
    L9:
        return;
    L7:
        if (r2 >= r0) goto L11;
        put(r5.keyAt(r2), r5.valueAt(r2));
        r2 = r2 + 1;
        goto L7
    }

    public V putIfAbsent(K r2, V r3) {
        V r0 = get(r2);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return put(r2, r3);
    }

    public V remove(K r1) {
        int r12 = indexOfKey(r1);
        if (r12 >= 0) goto L5;
        return null;
    L5:
        return removeAt(r12);
    }

    public boolean remove(K r2, V r3) {
        int r22 = indexOfKey(r2);
        if (r22 >= 0) goto L5;
        return false;
    L5:
        if (Intrinsics.areEqual(r3, valueAt(r22)) == false) goto L10;
        removeAt(r22);
        return true;
    L10:
        return false;
    }

    public V removeAt(int r12) {
        if (r12 >= 0) goto L5;
    L7:
        boolean r2 = false;
    L8:
        if (r2 == true) goto L10;
        RuntimeHelpersKt.throwIllegalArgumentException("Expected index to be within 0..size()-1, but was " + r12);
    L10:
        Object[] r22 = this.array;
        int r3 = r12 << 1;
        V r4 = (V) r22[r3 + 1];
        int r5 = this.size;
        if (r5 > 1) goto L14;
        clear();
        return r4;
    L14:
        int r6 = r5 - 1;
        int[] r7 = this.hashes;
        int r10 = 8;
        if (r7.length > 8) goto L17;
    L28:
        if (r12 >= r6) goto L30;
        int r0 = r12 + 1;
        ArraysKt.copyInto(r7, r7, r12, r0, r5);
        Object[] r122 = this.array;
        ArraysKt.copyInto(r122, r122, r3, r0 << 1, r5 << 1);
    L30:
        Object[] r123 = this.array;
        int r02 = r6 << 1;
        r123[r02] = null;
        r123[r02 + 1] = null;
    L32:
        if (r5 != this.size) goto L35;
        this.size = r6;
        return r4;
    L35:
        ArraySet$$ExternalSyntheticBUOutline0.m();
        return null;
    L17:
        if (r5 >= (r7.length / 3)) goto L28;
        if (r5 <= 8) goto L20;
        r10 = r5 + (r5 >> 1);
    L20:
        this.hashes = Arrays.copyOf(r7, r10);
        this.array = Arrays.copyOf(this.array, r10 << 1);
        if (r5 != this.size) goto L26;
        if (r12 <= 0) goto L24;
        ArraysKt.copyInto(r7, this.hashes, 0, 0, r12);
        ArraysKt.copyInto(r22, this.array, 0, 0, r3);
    L24:
        if (r12 >= r6) goto L32;
        int r8 = r12 + 1;
        ArraysKt.copyInto(r7, this.hashes, r12, r8, r5);
        ArraysKt.copyInto(r22, this.array, r3, r8 << 1, r5 << 1);
        goto L32
    L26:
        ArraySet$$ExternalSyntheticBUOutline0.m();
        return null;
    L5:
        if (r12 >= this.size) goto L7;
        r2 = true;
        goto L8
    }

    public V replace(K r1, V r2) {
        int r12 = indexOfKey(r1);
        if (r12 >= 0) goto L5;
        return null;
    L5:
        return setValueAt(r12, r2);
    }

    public boolean replace(K r2, V r3, V r4) {
        int r22 = indexOfKey(r2);
        if (r22 >= 0) goto L5;
        return false;
    L5:
        if (Intrinsics.areEqual(r3, valueAt(r22)) == false) goto L10;
        setValueAt(r22, r4);
        return true;
    L10:
        return false;
    }

    public V setValueAt(int r4, V r5) {
        boolean r0 = false;
        if (r4 >= 0) goto L5;
    L7:
        if (r0 == true) goto L9;
        RuntimeHelpersKt.throwIllegalArgumentException("Expected index to be within 0..size()-1, but was " + r4);
    L9:
        int r42 = (r4 << 1) + 1;
        Object[] r3 = this.array;
        V r02 = (V) r3[r42];
        r3[r42] = r5;
        return r02;
    L5:
        if (r4 >= this.size) goto L7;
        r0 = true;
        goto L7
    }

    public int size() {
        return this.size;
    }

    public String toString() {
        if (isEmpty() == false) goto L6;
        return "{}";
    L6:
        StringBuilder r1 = new StringBuilder(this.size * 28);
        r1.append('{');
        int r0 = this.size;
        int r2 = 0;
    L7:
        if (r2 >= r0) goto L19;
        if (r2 <= 0) goto L10;
        r1.append(", ");
    L10:
        K r3 = keyAt(r2);
        if (r3 == r1) goto L13;
        r1.append(r3);
    L14:
        r1.append(SignatureVisitor.INSTANCEOF);
        V r32 = valueAt(r2);
        if (r32 == r1) goto L17;
        r1.append(r32);
    L18:
        r2 = r2 + 1;
        goto L7
    L17:
        r1.append("(this Map)");
        goto L18
    L13:
        r1.append("(this Map)");
        goto L14
    L19:
        r1.append('}');
        return r1.toString();
    }

    public V valueAt(int r4) {
        boolean r0 = false;
        if (r4 >= 0) goto L5;
    L7:
        if (r0 == true) goto L10;
        RuntimeHelpersKt.throwIllegalArgumentException("Expected index to be within 0..size()-1, but was " + r4);
    L10:
        return (V) this.array[(r4 << 1) + 1];
    L5:
        if (r4 >= this.size) goto L7;
        r0 = true;
        goto L7
    }
}
