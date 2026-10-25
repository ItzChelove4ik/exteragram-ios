package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import kotlin.UByte;
import kotlin.UShort;

/* loaded from: classes.dex */
final class RegularImmutableMap<K, V> extends ImmutableMap<K, V> {
    static final ImmutableMap<Object, Object> EMPTY = null;
    final transient Object[] alternatingKeysAndValues;
    private final transient Object hashTable;
    private final transient int size;

    /* loaded from: classes5.dex */
    public static final class EntrySet<K, V> extends ImmutableSet<Map.Entry<K, V>> {
        private final transient Object[] alternatingKeysAndValues;
        private final transient int keyOffset;
        private final transient ImmutableMap<K, V> map;
        private final transient int size;

        /* renamed from: com.google.common.collect.RegularImmutableMap$EntrySet$1, reason: invalid class name */
        public class AnonymousClass1 extends ImmutableList<Map.Entry<K, V>> {
            final /* synthetic */ EntrySet this$0;

            public AnonymousClass1(EntrySet r1) {
                this.this$0 = r1;
            }

            @Override // java.util.List
            public /* bridge */ /* synthetic */ Object get(int r1) {
                return get(r1);
            }

            @Override // java.util.List
            public Map.Entry<K, V> get(int r3) {
                Preconditions.checkElementIndex(r3, EntrySet.access$000(this.this$0));
                int r32 = r3 * 2;
                Object r0 = EntrySet.access$100(this.this$0)[EntrySet.access$200(this.this$0) + r32];
                Objects.requireNonNull(r0);
                Object r2 = EntrySet.access$100(this.this$0)[r32 + (EntrySet.access$200(this.this$0) ^ 1)];
                Objects.requireNonNull(r2);
                return new AbstractMap.SimpleImmutableEntry(r0, r2);
            }

            @Override // com.google.common.collect.ImmutableCollection
            public boolean isPartialView() {
                return true;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return EntrySet.access$000(this.this$0);
            }
        }

        public EntrySet(ImmutableMap<K, V> r1, Object[] r2, int r3, int r4) {
            this.map = r1;
            this.alternatingKeysAndValues = r2;
            this.keyOffset = r3;
            this.size = r4;
        }

        public static /* synthetic */ int access$000(EntrySet r0) {
            return r0.size;
        }

        public static /* synthetic */ Object[] access$100(EntrySet r0) {
            return r0.alternatingKeysAndValues;
        }

        public static /* synthetic */ int access$200(EntrySet r0) {
            return r0.keyOffset;
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object r3) {
            if ((r3 instanceof Map.Entry) == false) goto L10;
            Map.Entry r32 = (Map.Entry) r3;
            Object r0 = r32.getKey();
            Object r33 = r32.getValue();
            if (r33 == null) goto L10;
            if (r33.equals(this.map.get(r0)) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public int copyIntoArray(Object[] r1, int r2) {
            return asList().copyIntoArray(r1, r2);
        }

        @Override // com.google.common.collect.ImmutableSet
        public ImmutableList<Map.Entry<K, V>> createAsList() {
            return new AnonymousClass1(this);
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
            return asList().iterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public /* bridge */ /* synthetic */ Iterator iterator() {
            return iterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.size;
        }
    }

    /* loaded from: classes5.dex */
    public static final class KeySet<K> extends ImmutableSet<K> {
        private final transient ImmutableList<K> list;
        private final transient ImmutableMap<K, ?> map;

        public KeySet(ImmutableMap<K, ?> r1, ImmutableList<K> r2) {
            this.map = r1;
            this.list = r2;
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        public ImmutableList<K> asList() {
            return this.list;
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object r1) {
            if (this.map.get(r1) == null) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public int copyIntoArray(Object[] r1, int r2) {
            return asList().copyIntoArray(r1, r2);
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public UnmodifiableIterator<K> iterator() {
            return asList().iterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public /* bridge */ /* synthetic */ Iterator iterator() {
            return iterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.map.size();
        }
    }

    /* loaded from: classes5.dex */
    public static final class KeysOrValuesAsList extends ImmutableList<Object> {
        private final transient Object[] alternatingKeysAndValues;
        private final transient int offset;
        private final transient int size;

        public KeysOrValuesAsList(Object[] r1, int r2, int r3) {
            this.alternatingKeysAndValues = r1;
            this.offset = r2;
            this.size = r3;
        }

        @Override // java.util.List
        public Object get(int r2) {
            Preconditions.checkElementIndex(r2, this.size);
            Object r1 = this.alternatingKeysAndValues[(r2 * 2) + this.offset];
            Objects.requireNonNull(r1);
            return r1;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.size;
        }
    }

    static {
        EMPTY = new RegularImmutableMap(null, new Object[0], 0);
    }

    private RegularImmutableMap(Object r1, Object[] r2, int r3) {
        this.hashTable = r1;
        this.alternatingKeysAndValues = r2;
        this.size = r3;
    }

    public static <K, V> RegularImmutableMap<K, V> create(int r1, Object[] r2) {
        return create(r1, r2, null);
    }

    public static <K, V> RegularImmutableMap<K, V> create(int r4, Object[] r5, ImmutableMap.Builder<K, V> r6) {
        if (r4 != 0) goto L6;
        return (RegularImmutableMap) EMPTY;
    L6:
        if (r4 != 1) goto L9;
        Object r42 = r5[0];
        Objects.requireNonNull(r42);
        Object r62 = r5[1];
        Objects.requireNonNull(r62);
        CollectPreconditions.checkEntryNotNull(r42, r62);
        return new RegularImmutableMap(null, r5, 1);
    L9:
        Preconditions.checkPositionIndex(r4, r5.length >> 1);
        Object r2 = createHashTable(r5, r4, ImmutableSet.chooseTableSize(r4), 0);
        if ((r2 instanceof Object[]) == false) goto L17;
        Object[] r22 = (Object[]) r2;
        ImmutableMap.Builder.DuplicateKey r43 = (ImmutableMap.Builder.DuplicateKey) r22[2];
        if (r6 == null) goto L15;
        r6.duplicateKey = r43;
        Object r44 = r22[0];
        int r63 = ((Integer) r22[1]).intValue();
        r5 = Arrays.copyOf(r5, r63 * 2);
        r2 = r44;
        r4 = r63;
        goto L17
    L15:
        throw r43.exception();
    L17:
        return new RegularImmutableMap(r2, r5, r4);
    }

    private static Object createHashTable(Object[] r17, int r18, int r19, int r20) {
        ImmutableMap.Builder.DuplicateKey r2 = null;
        int r3 = 1;
        if (r18 != 1) goto L6;
        Object r0 = r17[r20];
        Objects.requireNonNull(r0);
        Object r1 = r17[r20 ^ 1];
        Objects.requireNonNull(r1);
        CollectPreconditions.checkEntryNotNull(r0, r1);
        return null;
    L6:
        int r4 = r19 - 1;
        if (r19 > 128) goto L27;
        byte[] r12 = new byte[r19];
        Arrays.fill(r12, (byte) -1);
        int r5 = 0;
        int r7 = 0;
    L9:
        if (r5 >= r18) goto L22;
        int r10 = (r5 * 2) + r20;
        int r11 = (r7 * 2) + r20;
        Object r122 = r17[r10];
        Objects.requireNonNull(r122);
        Object r102 = r17[r10 ^ 1];
        Objects.requireNonNull(r102);
        CollectPreconditions.checkEntryNotNull(r122, r102);
        int r13 = Hashing.smear(r122.hashCode());
    L11:
        int r132 = r13 & r4;
        int r14 = r12[r132] & UByte.MAX_VALUE;
        if (r14 == 255) goto L13;
        if (r122.equals(r17[r14]) == true) goto L19;
        r13 = r132 + 1;
        goto L11
    L19:
        int r112 = r14 ^ 1;
        Object r133 = r17[r112];
        Objects.requireNonNull(r133);
        r2 = new ImmutableMap.Builder.DuplicateKey(r122, r102, r133);
        r17[r112] = r102;
    L20:
        r5 = r5 + 1;
        goto L9
    L13:
        r12[r132] = (byte) r11;
        if (r7 >= r5) goto L16;
        r17[r11] = r122;
        r17[r11 ^ 1] = r102;
    L16:
        r7 = r7 + 1;
        goto L20
    L22:
        if (r7 != r18) goto L25;
        return r12;
    L25:
        return new Object[]{r12, Integer.valueOf(r7), r2};
    L27:
        if (r19 > 32768) goto L46;
        short[] r15 = new short[r19];
        Arrays.fill(r15, -1);
        int r52 = 0;
        int r72 = 0;
    L29:
        if (r52 >= r18) goto L42;
        int r103 = (r52 * 2) + r20;
        int r113 = (r72 * 2) + r20;
        Object r123 = r17[r103];
        Objects.requireNonNull(r123);
        Object r104 = r17[r103 ^ 1];
        Objects.requireNonNull(r104);
        CollectPreconditions.checkEntryNotNull(r123, r104);
        int r134 = Hashing.smear(r123.hashCode());
    L31:
        int r135 = r134 & r4;
        int r142 = r15[r135] & UShort.MAX_VALUE;
        if (r142 == 65535) goto L33;
        if (r123.equals(r17[r142]) == true) goto L39;
        r134 = r135 + 1;
        goto L31
    L39:
        int r114 = r142 ^ 1;
        Object r136 = r17[r114];
        Objects.requireNonNull(r136);
        r2 = new ImmutableMap.Builder.DuplicateKey(r123, r104, r136);
        r17[r114] = r104;
    L40:
        r52 = r52 + 1;
        goto L29
    L33:
        r15[r135] = (short) r113;
        if (r72 >= r52) goto L36;
        r17[r113] = r123;
        r17[r113 ^ 1] = r104;
    L36:
        r72 = r72 + 1;
        goto L40
    L42:
        if (r72 != r18) goto L45;
        return r15;
    L45:
        return new Object[]{r15, Integer.valueOf(r72), r2};
    L46:
        int[] r16 = new int[r19];
        Arrays.fill(r16, -1);
        int r53 = 0;
        int r105 = 0;
    L47:
        if (r53 >= r18) goto L60;
        int r115 = (r53 * 2) + r20;
        int r124 = (r105 * 2) + r20;
        Object r137 = r17[r115];
        Objects.requireNonNull(r137);
        Object r116 = r17[r115 ^ r3];
        Objects.requireNonNull(r116);
        CollectPreconditions.checkEntryNotNull(r137, r116);
        int r143 = Hashing.smear(r137.hashCode());
    L49:
        int r144 = r143 & r4;
        int r152 = r16[r144];
        if (r152 == (-1)) goto L51;
        int r162 = r3;
        if (r137.equals(r17[r152]) == true) goto L57;
        r143 = r144 + 1;
        r3 = r162;
        goto L49
    L57:
        int r32 = r152 ^ 1;
        Object r125 = r17[r32];
        Objects.requireNonNull(r125);
        r2 = new ImmutableMap.Builder.DuplicateKey(r137, r116, r125);
        r17[r32] = r116;
    L58:
        r53 = r53 + 1;
        r3 = r162;
        goto L47
    L51:
        r16[r144] = r124;
        if (r105 >= r53) goto L54;
        r17[r124] = r137;
        r17[r124 ^ 1] = r116;
    L54:
        r105 = r105 + 1;
        r162 = r3;
        goto L58
    L60:
        int r163 = r3;
        if (r105 != r18) goto L63;
        return r16;
    L63:
        Object[] r02 = new Object[3];
        r02[0] = r16;
        r02[r163] = Integer.valueOf(r105);
        r02[2] = r2;
        return r02;
    }

    public static Object createHashTableOrThrow(Object[] r0, int r1, int r2, int r3) {
        Object r02 = createHashTable(r0, r1, r2, r3);
        if ((r02 instanceof Object[]) == true) goto L6;
        return r02;
    L6:
        throw ((ImmutableMap.Builder.DuplicateKey) ((Object[]) r02)[2]).exception();
    }

    public static Object get(Object r4, Object[] r5, int r6, int r7, Object r8) {
        if (r8 != null) goto L6;
        return null;
    L6:
        if (r6 != 1) goto L12;
        Object r42 = r5[r7];
        Objects.requireNonNull(r42);
        if (r42.equals(r8) == false) goto L11;
        Object r43 = r5[r7 ^ 1];
        Objects.requireNonNull(r43);
        return r43;
    L11:
        return null;
    L12:
        if (r4 != null) goto L15;
        return null;
    L15:
        if ((r4 instanceof byte[]) == false) goto L26;
        byte[] r62 = (byte[]) r4;
        int r72 = r62.length - 1;
        int r44 = Hashing.smear(r8.hashCode());
    L17:
        int r45 = r44 & r72;
        int r2 = r62[r45] & UByte.MAX_VALUE;
        if (r2 == 255) goto L19;
        if (r8.equals(r5[r2]) == true) goto L23;
        r44 = r45 + 1;
        goto L17
    L23:
        return r5[r2 ^ 1];
    L19:
        return null;
    L26:
        if ((r4 instanceof short[]) == false) goto L36;
        short[] r63 = (short[]) r4;
        int r73 = r63.length - 1;
        int r46 = Hashing.smear(r8.hashCode());
    L28:
        int r47 = r46 & r73;
        int r22 = r63[r47] & UShort.MAX_VALUE;
        if (r22 == 65535) goto L30;
        if (r8.equals(r5[r22]) == true) goto L34;
        r46 = r47 + 1;
        goto L28
    L34:
        return r5[r22 ^ 1];
    L30:
        return null;
    L36:
        int[] r48 = (int[]) r4;
        int r64 = r48.length - 1;
        int r74 = Hashing.smear(r8.hashCode());
    L37:
        int r75 = r74 & r64;
        int r23 = r48[r75];
        if (r23 == (-1)) goto L39;
        if (r8.equals(r5[r23]) == true) goto L43;
        r74 = r75 + 1;
        goto L37
    L43:
        return r5[r23 ^ 1];
    L39:
        return null;
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public ImmutableSet<Map.Entry<K, V>> entrySet() {
        return new EntrySet(this, this.alternatingKeysAndValues, 0, this.size);
    }

    @Override // java.util.Map
    public /* bridge */ /* synthetic */ Set entrySet() {
        return entrySet();
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public V get(Object r4) {
        V r3 = (V) get(this.hashTable, this.alternatingKeysAndValues, this.size, 0, r4);
        if (r3 != null) goto L6;
        return null;
    L6:
        return r3;
    }

    @Override // com.google.common.collect.ImmutableMap
    public boolean isPartialView() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public ImmutableSet<K> keySet() {
        return new KeySet(this, new KeysOrValuesAsList(this.alternatingKeysAndValues, 0, this.size));
    }

    @Override // java.util.Map
    public /* bridge */ /* synthetic */ Set keySet() {
        return keySet();
    }

    @Override // java.util.Map
    public int size() {
        return this.size;
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public ImmutableCollection<V> values() {
        return new KeysOrValuesAsList(this.alternatingKeysAndValues, 1, this.size);
    }

    @Override // java.util.Map
    public /* bridge */ /* synthetic */ Collection values() {
        return values();
    }
}
