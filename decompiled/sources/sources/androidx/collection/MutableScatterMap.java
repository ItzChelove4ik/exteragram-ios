package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import androidx.collection.internal.RuntimeHelpersKt;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.ULong;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u001d\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003B\u0011\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\t\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u000b\u0010\u0007J\u000f\u0010\f\u001a\u00020\bH\u0002¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J \u0010\u0013\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00028\u0001H\u0086\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0015\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0011\u001a\u00028\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0019\u0010\u0018\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0017\u001a\u00020\u0004H\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00028\u0000H\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001d\u001a\u00020\bH\u0000¢\u0006\u0004\b\u001c\u0010\rJ\u000f\u0010\u001f\u001a\u00020\bH\u0000¢\u0006\u0004\b\u001e\u0010\rJ\u0017\u0010\"\u001a\u00020\b2\u0006\u0010 \u001a\u00020\u0004H\u0000¢\u0006\u0004\b!\u0010\u0007R\u0016\u0010#\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010$¨\u0006%"}, d2 = {"Landroidx/collection/MutableScatterMap;", "K", "V", "Landroidx/collection/ScatterMap;", _UrlKt.FRAGMENT_ENCODE_SET, "initialCapacity", "<init>", "(I)V", _UrlKt.FRAGMENT_ENCODE_SET, "initializeStorage", "capacity", "initializeMetadata", "initializeGrowth", "()V", "hash1", "findFirstAvailableSlot", "(I)I", PluginsConstants.Settings.KEY, "value", "set", "(Ljava/lang/Object;Ljava/lang/Object;)V", "remove", "(Ljava/lang/Object;)Ljava/lang/Object;", "index", "removeValueAt", "(I)Ljava/lang/Object;", "findInsertIndex", "(Ljava/lang/Object;)I", "adjustStorage$collection", "adjustStorage", "dropDeletes$collection", "dropDeletes", "newCapacity", "resizeStorage$collection", "resizeStorage", "growthLimit", "I", "collection"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nScatterMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ScatterMap.kt\nandroidx/collection/MutableScatterMap\n+ 2 RuntimeHelpers.kt\nandroidx/collection/internal/RuntimeHelpersKt\n+ 3 ScatterMap.kt\nandroidx/collection/ScatterMapKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 6 ScatterMap.kt\nandroidx/collection/ScatterMap\n+ 7 ScatterSet.kt\nandroidx/collection/ScatterSet\n+ 8 ObjectList.kt\nandroidx/collection/ObjectList\n*L\n1#1,1787:1\n59#2,5:1788\n1254#3,6:1793\n1399#3:1812\n1270#3:1816\n1165#3,3:1828\n1179#3:1832\n1175#3:1835\n1372#3,3:1840\n1386#3,3:1844\n1312#3:1848\n1303#3:1850\n1297#3:1851\n1309#3:1856\n1393#3:1858\n1165#3,3:1868\n1179#3:1872\n1175#3:1875\n1372#3,3:1880\n1386#3,3:1884\n1312#3:1888\n1303#3:1890\n1297#3:1891\n1309#3:1896\n1393#3:1898\n1399#3:1913\n1270#3:1917\n1399#3:1937\n1270#3:1941\n1230#3:1958\n1254#3,6:1959\n1242#3:1965\n1241#3,4:1966\n1254#3,6:1970\n1165#3,3:1976\n1175#3:1979\n1179#3:1980\n1372#3,3:1981\n1386#3,3:1984\n1312#3:1987\n1303#3:1988\n1297#3:1989\n1309#3:1990\n1393#3:1991\n1265#3:1992\n1220#3:1993\n1262#3:1994\n1220#3:1995\n1230#3:1996\n1254#3,6:1997\n1242#3:2003\n1241#3,4:2004\n1372#3,3:2008\n1399#3:2011\n1297#3:2012\n1144#3,14:2013\n1220#3:2027\n1165#3,3:2028\n1175#3:2031\n1179#3:2032\n1254#3,6:2033\n1220#3:2039\n1179#3:2040\n1254#3,6:2041\n1254#3,6:2047\n1179#3:2053\n1254#3,6:2054\n1268#3:2060\n1220#3:2061\n1165#3,3:2062\n1175#3:2065\n1179#3:2066\n1230#3:2067\n1254#3,6:2068\n1242#3:2074\n1241#3,4:2075\n1#4:1799\n215#5,2:1800\n357#6,4:1802\n329#6,6:1806\n339#6,3:1813\n342#6,9:1817\n361#6:1826\n555#6:1827\n556#6:1831\n558#6,2:1833\n560#6,4:1836\n564#6:1843\n565#6:1847\n566#6:1849\n567#6,4:1852\n573#6:1857\n574#6,8:1859\n555#6:1867\n556#6:1871\n558#6,2:1873\n560#6,4:1876\n564#6:1883\n565#6:1887\n566#6:1889\n567#6,4:1892\n573#6:1897\n574#6,8:1899\n329#6,6:1907\n339#6,3:1914\n342#6,9:1918\n231#7,3:1927\n200#7,7:1930\n211#7,3:1938\n214#7,9:1942\n234#7:1951\n287#8,6:1952\n*S KotlinDebug\n*F\n+ 1 ScatterMap.kt\nandroidx/collection/MutableScatterMap\n*L\n639#1:1788,5\n667#1:1793,6\n776#1:1812\n776#1:1816\n818#1:1828,3\n818#1:1832\n818#1:1835\n818#1:1840,3\n818#1:1844,3\n818#1:1848\n818#1:1850\n818#1:1851\n818#1:1856\n818#1:1858\n830#1:1868,3\n830#1:1872\n830#1:1875\n830#1:1880,3\n830#1:1884,3\n830#1:1888\n830#1:1890\n830#1:1891\n830#1:1896\n830#1:1898\n842#1:1913\n842#1:1917\n878#1:1937\n878#1:1941\n892#1:1958\n892#1:1959,6\n892#1:1965\n892#1:1966,4\n905#1:1970,6\n920#1:1976,3\n921#1:1979\n922#1:1980\n929#1:1981,3\n930#1:1984,3\n931#1:1987\n932#1:1988\n932#1:1989\n936#1:1990\n939#1:1991\n948#1:1992\n948#1:1993\n954#1:1994\n954#1:1995\n955#1:1996\n955#1:1997,6\n955#1:2003\n955#1:2004,4\n970#1:2008,3\n971#1:2011\n973#1:2012\n1019#1:2013,14\n1025#1:2027\n1039#1:2028,3\n1040#1:2031\n1051#1:2032\n1052#1:2033,6\n1061#1:2039\n1064#1:2040\n1065#1:2041,6\n1066#1:2047,6\n1076#1:2053\n1077#1:2054,6\n1116#1:2060\n1116#1:2061\n1118#1:2062,3\n1119#1:2065\n1121#1:2066\n1121#1:2067\n1121#1:2068,6\n1121#1:2074\n1121#1:2075,4\n771#1:1800,2\n776#1:1802,4\n776#1:1806,6\n776#1:1813,3\n776#1:1817,9\n776#1:1826\n818#1:1827\n818#1:1831\n818#1:1833,2\n818#1:1836,4\n818#1:1843\n818#1:1847\n818#1:1849\n818#1:1852,4\n818#1:1857\n818#1:1859,8\n830#1:1867\n830#1:1871\n830#1:1873,2\n830#1:1876,4\n830#1:1883\n830#1:1887\n830#1:1889\n830#1:1892,4\n830#1:1897\n830#1:1899,8\n842#1:1907,6\n842#1:1914,3\n842#1:1918,9\n878#1:1927,3\n878#1:1930,7\n878#1:1938,3\n878#1:1942,9\n878#1:1951\n883#1:1952,6\n*E\n"})
/* loaded from: classes3.dex */
public final class MutableScatterMap<K, V> extends ScatterMap<K, V> {
    private int growthLimit;

    public MutableScatterMap(int r2) {
        super(null);
        if (r2 < 0) goto L5;
        boolean r0 = true;
    L6:
        if (r0 == true) goto L8;
        RuntimeHelpersKt.throwIllegalArgumentException("Capacity must be a positive value.");
    L8:
        initializeStorage(ScatterMapKt.unloadedCapacity(r2));
        return;
    L5:
        r0 = false;
        goto L6
    }

    public /* synthetic */ MutableScatterMap(int r1, int r2, DefaultConstructorMarker r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = 6;
    L5:
        this(r1);
    }

    private final int findFirstAvailableSlot(int r10) {
        int r0 = this._capacity;
        int r102 = r10 & r0;
        int r1 = 0;
    L3:
        long[] r2 = this.metadata;
        int r3 = r102 >> 3;
        int r4 = (r102 & 7) << 3;
        long r22 = ((r2[r3 + 1] << (64 - r4)) & ((-r4) >> 63)) | (r2[r3] >>> r4);
        long r23 = (r22 & ((~r22) << 7)) & (-9187201950435737472L);
        if (r23 != 0) goto L6;
        r1 = r1 + 8;
        r102 = (r102 + r1) & r0;
        goto L3
    L6:
        return (r102 + (Long.numberOfTrailingZeros(r23) >> 3)) & r0;
    }

    private final void initializeGrowth() {
        this.growthLimit = ScatterMapKt.loadedCapacity(getCapacity()) - this._size;
    }

    private final void initializeMetadata(int r9) {
        if (r9 != 0) goto L4;
        long[] r92 = ScatterMapKt.EmptyGroup;
    L5:
        this.metadata = r92;
        initializeGrowth();
        return;
    L4:
        long[] r1 = new long[((r9 + 15) & (-8)) >> 3];
        ArraysKt.fill$default(r1, -9187201950435737472L, 0, 0, 6, null);
        int r0 = r9 >> 3;
        long r4 = 255 << ((r9 & 7) << 3);
        r1[r0] = (r1[r0] & (~r4)) | r4;
        r92 = r1;
        goto L5
    }

    private final void initializeStorage(int r2) {
        if (r2 <= 0) goto L4;
        int r22 = Math.max(7, ScatterMapKt.normalizeCapacity(r2));
    L5:
        this._capacity = r22;
        initializeMetadata(r22);
        if (r22 != 0) goto L8;
        Object[] r0 = ContainerHelpersKt.EMPTY_OBJECTS;
    L9:
        this.keys = r0;
        if (r22 != 0) goto L12;
        Object[] r23 = ContainerHelpersKt.EMPTY_OBJECTS;
    L13:
        this.values = r23;
        return;
    L12:
        r23 = new Object[r22];
        goto L13
    L8:
        r0 = new Object[r22];
        goto L9
    L4:
        r22 = 0;
        goto L5
    }

    public final void adjustStorage$collection() {
        if (this._capacity > 8) goto L5;
    L8:
        resizeStorage$collection(ScatterMapKt.nextCapacity(this._capacity));
        return;
    L5:
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(ULong.m2192constructorimpl(ULong.m2192constructorimpl(this._size) * 32), ULong.m2192constructorimpl(ULong.m2192constructorimpl(this._capacity) * 25)) > 0) goto L8;
        dropDeletes$collection();
    }

    public final void dropDeletes$collection() {
        long[] r1 = this.metadata;
        int r2 = this._capacity;
        Object[] r3 = this.keys;
        Object[] r4 = this.values;
        int r5 = (r2 + 7) >> 3;
        int r6 = 0;
        int r7 = 0;
    L3:
        if (r7 >= r5) goto L5;
        long r8 = r1[r7] & (-9187201950435737472L);
        r1[r7] = (-72340172838076674L) & ((~r8) + (r8 >>> 7));
        r7 = r7 + 1;
        goto L3
    L5:
        int r52 = ArraysKt.getLastIndex(r1);
        int r72 = r52 - 1;
        r1[r72] = (r1[r72] & 72057594037927935L) | (-72057594037927936L);
        r1[r52] = r1[0];
        int r53 = 0;
    L6:
        if (r53 == r2) goto L26;
        int r73 = r53 >> 3;
        int r10 = (r53 & 7) << 3;
        long r82 = (r1[r73] >> r10) & 255;
        if (r82 == 128) goto L9;
        if (r82 != 254) goto L9;
        Object r83 = r3[r53];
        if (r83 == null) goto L16;
        int r84 = r83.hashCode();
    L17:
        int r85 = r84 * (-862048943);
        int r9 = (r85 ^ (r85 << 16)) >>> 7;
        int r15 = findFirstAvailableSlot(r9);
        int r92 = r9 & r2;
        int r17 = r6;
        if ((((r15 - r92) & r2) / 8) != (((r53 - r92) & r2) / 8)) goto L21;
        long r86 = (r8 & 127) << r10;
        r1[r73] = r86 | ((~(255 << r10)) & r1[r73]);
        r1[ArraysKt.getLastIndex(r1)] = r1[r17];
    L20:
        r53 = r53 + 1;
        r6 = r17;
        goto L6
    L21:
        int r62 = r15 >> 3;
        long r18 = r1[r62];
        int r93 = (r15 & 7) << 3;
        if (((r18 >> r93) & 255) != 128) goto L24;
        r1[r62] = ((r8 & 127) << r93) | (r18 & (~(255 << r93)));
        r1[r73] = (r1[r73] & (~(255 << r10))) | (128 << r10);
        r3[r15] = r3[r53];
        r3[r53] = null;
        r4[r15] = r4[r53];
        r4[r53] = null;
    L25:
        r1[ArraysKt.getLastIndex(r1)] = r1[r17];
        goto L20
    L24:
        r1[r62] = ((r8 & 127) << r93) | (r18 & (~(255 << r93)));
        Object r63 = r3[r15];
        r3[r15] = r3[r53];
        r3[r53] = r63;
        Object r64 = r4[r15];
        r4[r15] = r4[r53];
        r4[r53] = r64;
        r53 = r53 - 1;
        goto L25
    L16:
        r84 = r6;
    L9:
        r53 = r53 + 1;
        goto L6
    L26:
        initializeGrowth();
    }

    @PublishedApi
    public final int findInsertIndex(K r21) {
        if (r21 == null) goto L5;
        int r3 = r21.hashCode();
    L6:
        int r32 = r3 * (-862048943);
        int r33 = r32 ^ (r32 << 16);
        int r4 = r33 >>> 7;
        int r34 = r33 & 127;
        int r5 = this._capacity;
        int r6 = r4 & r5;
        int r7 = 0;
    L7:
        long[] r8 = this.metadata;
        int r9 = r6 >> 3;
        int r10 = (r6 & 7) << 3;
        long r82 = ((r8[r9 + 1] << (64 - r10)) & ((-r10) >> 63)) | (r8[r9] >>> r10);
        long r102 = r34;
        int r18 = r34;
        long r2 = r82 ^ (r102 * 72340172838076673L);
        long r22 = ((~r2) & (r2 - 72340172838076673L)) & (-9187201950435737472L);
    L9:
        if (r22 == 0) goto L15;
        int r16 = (r6 + (Long.numberOfTrailingZeros(r22) >> 3)) & r5;
        if (Intrinsics.areEqual(this.keys[r16], r21) == true) goto L12;
        r22 = r22 & (r22 - 1);
        goto L9
    L12:
        return r16;
    L15:
        if (((((~r82) << 6) & r82) & (-9187201950435737472L)) != 0) goto L16;
        r7 = r7 + 8;
        r6 = (r6 + r7) & r5;
        r34 = r18;
        goto L7
    L16:
        int r1 = findFirstAvailableSlot(r4);
        if (this.growthLimit == 0) goto L19;
    L22:
        this._size++;
        int r23 = this.growthLimit;
        long[] r35 = this.metadata;
        int r42 = r1 >> 3;
        long r72 = r35[r42];
        int r92 = (r1 & 7) << 3;
        if (((r72 >> r92) & 255) != 128) goto L25;
        int r19 = 1;
    L26:
        this.growthLimit = r23 - r19;
        int r0 = this._capacity;
        long r52 = ((~(255 << r92)) & r72) | (r102 << r92);
        r35[r42] = r52;
        r35[(((r1 - 7) & r0) + (r0 & 7)) >> 3] = r52;
        return ~r1;
    L25:
        r19 = 0;
        goto L26
    L19:
        if (((this.metadata[r1 >> 3] >> ((r1 & 7) << 3)) & 255) == 254) goto L22;
        adjustStorage$collection();
        r1 = findFirstAvailableSlot(r4);
        goto L22
    L5:
        r3 = 0;
        goto L6
    }

    public final V remove(K r14) {
        int r0 = 0;
        if (r14 == null) goto L5;
        int r1 = r14.hashCode();
    L6:
        int r12 = r1 * (-862048943);
        int r13 = r12 ^ (r12 << 16);
        int r2 = r13 & 127;
        int r3 = this._capacity;
        int r15 = r13 >>> 7;
    L7:
        int r16 = r15 & r3;
        long[] r4 = this.metadata;
        int r5 = r16 >> 3;
        int r6 = (r16 & 7) << 3;
        long r42 = ((r4[r5 + 1] << (64 - r6)) & ((-r6) >> 63)) | (r4[r5] >>> r6);
        long r62 = (r2 * 72340172838076673L) ^ r42;
        long r63 = ((~r62) & (r62 - 72340172838076673L)) & (-9187201950435737472L);
    L9:
        if (r63 == 0) goto L15;
        int r10 = ((Long.numberOfTrailingZeros(r63) >> 3) + r16) & r3;
        if (Intrinsics.areEqual(this.keys[r10], r14) == true) goto L17;
        r63 = r63 & (r63 - 1);
    L17:
        if (r10 >= 0) goto L19;
        return null;
    L19:
        return removeValueAt(r10);
    L15:
        if (((r42 & ((~r42) << 6)) & (-9187201950435737472L)) != 0) goto L16;
        r0 = r0 + 8;
        r15 = r16 + r0;
        goto L7
    L16:
        r10 = -1;
        goto L17
    L5:
        r1 = 0;
        goto L6
    }

    @PublishedApi
    public final V removeValueAt(int r9) {
        this._size--;
        long[] r0 = this.metadata;
        int r1 = this._capacity;
        int r2 = r9 >> 3;
        int r3 = (r9 & 7) << 3;
        long r32 = (r0[r2] & (~(255 << r3))) | (254 << r3);
        r0[r2] = r32;
        r0[(((r9 - 7) & r1) + (r1 & 7)) >> 3] = r32;
        this.keys[r9] = null;
        Object[] r8 = this.values;
        V r02 = (V) r8[r9];
        r8[r9] = null;
        return r02;
    }

    public final void resizeStorage$collection(int r21) {
        long[] r1 = this.metadata;
        Object[] r2 = this.keys;
        Object[] r3 = this.values;
        int r4 = this._capacity;
        initializeStorage(r21);
        long[] r5 = this.metadata;
        Object[] r6 = this.keys;
        Object[] r7 = this.values;
        int r8 = this._capacity;
        int r10 = 0;
    L3:
        if (r10 >= r4) goto L13;
        if (((r1[r10 >> 3] >> ((r10 & 7) << 3)) & 255) >= 128) goto L11;
        Object r11 = r2[r10];
        if (r11 == null) goto L9;
        int r12 = r11.hashCode();
    L10:
        int r122 = r12 * (-862048943);
        int r123 = r122 ^ (r122 << 16);
        int r15 = findFirstAvailableSlot(r123 >>> 7);
        int r16 = r10;
        long r9 = r123 & 127;
        int r124 = r15 >> 3;
        int r17 = (r15 & 7) << 3;
        long r92 = (r9 << r17) | (r5[r124] & (~(255 << r17)));
        r5[r124] = r92;
        r5[(((r15 - 7) & r8) + (r8 & 7)) >> 3] = r92;
        r6[r15] = r11;
        r7[r15] = r3[r16];
    L12:
        r10 = r16 + 1;
        goto L3
    L9:
        r12 = 0;
        goto L10
    L11:
        r16 = r10;
        goto L12
    }

    public final void set(K r3, V r4) {
        int r0 = findInsertIndex(r3);
        if (r0 >= 0) goto L5;
        r0 = ~r0;
    L5:
        this.keys[r0] = r3;
        this.values[r0] = r4;
    }
}
