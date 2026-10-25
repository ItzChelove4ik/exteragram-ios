package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003B\t\b\u0004¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\n\u001a\u0004\u0018\u00018\u00012\u0006\u0010\t\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\f\u001a\u00020\u00062\u0006\u0010\t\u001a\u00028\u0000¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0012\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003H\u0096\u0002¢\u0006\u0004\b\u0012\u0010\rJ\u000f\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0017\u001a\u00020\u00168\u0000@\u0000X\u0081\u000e¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u0012\u0004\b\u0019\u0010\u0005R$\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u001a8\u0000@\u0000X\u0081\u000e¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u0012\u0004\b\u001d\u0010\u0005R$\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u001a8\u0000@\u0000X\u0081\u000e¢\u0006\f\n\u0004\b\u001e\u0010\u001c\u0012\u0004\b\u001f\u0010\u0005R\u0016\u0010 \u001a\u00020\u000e8\u0000@\u0000X\u0081\u000e¢\u0006\u0006\n\u0004\b \u0010!R\u0016\u0010\"\u001a\u00020\u000e8\u0000@\u0000X\u0081\u000e¢\u0006\u0006\n\u0004\b\"\u0010!R\u0011\u0010$\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b#\u0010\u0010R\u0011\u0010&\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b%\u0010\u0010\u0082\u0001\u0001'¨\u0006("}, d2 = {"Landroidx/collection/ScatterMap;", "K", "V", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "isEmpty", "()Z", PluginsConstants.Settings.KEY, "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "containsKey", "(Ljava/lang/Object;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "metadata", "[J", "getMetadata$annotations", _UrlKt.FRAGMENT_ENCODE_SET, "keys", "[Ljava/lang/Object;", "getKeys$annotations", "values", "getValues$annotations", "_capacity", "I", "_size", "getCapacity", "capacity", "getSize", "size", "Landroidx/collection/MutableScatterMap;", "collection"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nScatterMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ScatterMap.kt\nandroidx/collection/ScatterMap\n+ 2 ScatterMap.kt\nandroidx/collection/ScatterMapKt\n*L\n1#1,1787:1\n555#1:1788\n556#1:1792\n558#1,2:1794\n560#1,4:1797\n564#1:1804\n565#1:1808\n566#1:1810\n567#1,4:1813\n573#1:1818\n574#1,8:1820\n555#1:1828\n556#1:1832\n558#1,2:1834\n560#1,4:1837\n564#1:1844\n565#1:1848\n566#1:1850\n567#1,4:1853\n573#1:1858\n574#1,8:1860\n329#1,6:1870\n339#1,3:1877\n342#1,9:1881\n329#1,6:1890\n339#1,3:1897\n342#1,9:1901\n329#1,6:1910\n339#1,3:1917\n342#1,9:1921\n357#1,4:1930\n329#1,6:1934\n339#1,3:1941\n342#1,9:1945\n361#1:1954\n357#1,4:1955\n329#1,6:1959\n339#1,3:1966\n342#1,9:1970\n361#1:1979\n357#1,4:1980\n329#1,6:1984\n339#1,3:1991\n342#1,9:1995\n361#1:2004\n555#1:2005\n556#1:2009\n558#1,2:2011\n560#1,4:2014\n564#1:2021\n565#1:2025\n566#1:2027\n567#1,4:2030\n573#1:2035\n574#1,8:2037\n555#1:2045\n556#1:2049\n558#1,2:2051\n560#1,4:2054\n564#1:2061\n565#1:2065\n566#1:2067\n567#1,4:2070\n573#1:2075\n574#1,8:2077\n372#1,3:2085\n329#1,6:2088\n339#1,3:2095\n342#1,9:2099\n375#1:2108\n357#1,4:2109\n329#1,6:2113\n339#1,3:2120\n342#1,9:2124\n361#1:2133\n357#1,4:2134\n329#1,6:2138\n339#1,3:2145\n342#1,9:2149\n361#1:2158\n357#1,4:2159\n329#1,6:2163\n339#1,3:2170\n342#1,9:2174\n361#1:2183\n357#1,4:2184\n329#1,6:2188\n339#1,3:2195\n342#1,9:2199\n361#1:2208\n1165#2,3:1789\n1179#2:1793\n1175#2:1796\n1372#2,3:1801\n1386#2,3:1805\n1312#2:1809\n1303#2:1811\n1297#2:1812\n1309#2:1817\n1393#2:1819\n1165#2,3:1829\n1179#2:1833\n1175#2:1836\n1372#2,3:1841\n1386#2,3:1845\n1312#2:1849\n1303#2:1851\n1297#2:1852\n1309#2:1857\n1393#2:1859\n1399#2:1868\n1270#2:1869\n1399#2:1876\n1270#2:1880\n1399#2:1896\n1270#2:1900\n1399#2:1916\n1270#2:1920\n1399#2:1940\n1270#2:1944\n1399#2:1965\n1270#2:1969\n1399#2:1990\n1270#2:1994\n1165#2,3:2006\n1179#2:2010\n1175#2:2013\n1372#2,3:2018\n1386#2,3:2022\n1312#2:2026\n1303#2:2028\n1297#2:2029\n1309#2:2034\n1393#2:2036\n1165#2,3:2046\n1179#2:2050\n1175#2:2053\n1372#2,3:2058\n1386#2,3:2062\n1312#2:2066\n1303#2:2068\n1297#2:2069\n1309#2:2074\n1393#2:2076\n1399#2:2094\n1270#2:2098\n1399#2:2119\n1270#2:2123\n1399#2:2144\n1270#2:2148\n1399#2:2169\n1270#2:2173\n1399#2:2194\n1270#2:2198\n1220#2:2209\n1165#2,3:2210\n1179#2:2213\n1175#2:2214\n1372#2,3:2215\n1386#2,3:2218\n1312#2:2221\n1303#2:2222\n1297#2:2223\n1309#2:2224\n1393#2:2225\n*S KotlinDebug\n*F\n+ 1 ScatterMap.kt\nandroidx/collection/ScatterMap\n*L\n299#1:1788\n299#1:1792\n299#1:1794,2\n299#1:1797,4\n299#1:1804\n299#1:1808\n299#1:1810\n299#1:1813,4\n299#1:1818\n299#1:1820,8\n308#1:1828\n308#1:1832\n308#1:1834,2\n308#1:1837,4\n308#1:1844\n308#1:1848\n308#1:1850\n308#1:1853,4\n308#1:1858\n308#1:1860,8\n360#1:1870,6\n360#1:1877,3\n360#1:1881,9\n367#1:1890,6\n367#1:1897,3\n367#1:1901,9\n374#1:1910,6\n374#1:1917,3\n374#1:1921,9\n379#1:1930,4\n379#1:1934,6\n379#1:1941,3\n379#1:1945,9\n379#1:1954\n385#1:1955,4\n385#1:1959,6\n385#1:1966,3\n385#1:1970,9\n385#1:1979\n395#1:1980,4\n395#1:1984,6\n395#1:1991,3\n395#1:1995,9\n395#1:2004\n400#1:2005\n400#1:2009\n400#1:2011,2\n400#1:2014,4\n400#1:2021\n400#1:2025\n400#1:2027\n400#1:2030,4\n400#1:2035\n400#1:2037,8\n403#1:2045\n403#1:2049\n403#1:2051,2\n403#1:2054,4\n403#1:2061\n403#1:2065\n403#1:2067\n403#1:2070,4\n403#1:2075\n403#1:2077,8\n407#1:2085,3\n407#1:2088,6\n407#1:2095,3\n407#1:2099,9\n407#1:2108\n432#1:2109,4\n432#1:2113,6\n432#1:2120,3\n432#1:2124,9\n432#1:2133\n459#1:2134,4\n459#1:2138,6\n459#1:2145,3\n459#1:2149,9\n459#1:2158\n485#1:2159,4\n485#1:2163,6\n485#1:2170,3\n485#1:2174,9\n485#1:2183\n510#1:2184,4\n510#1:2188,6\n510#1:2195,3\n510#1:2199,9\n510#1:2208\n299#1:1789,3\n299#1:1793\n299#1:1796\n299#1:1801,3\n299#1:1805,3\n299#1:1809\n299#1:1811\n299#1:1812\n299#1:1817\n299#1:1819\n308#1:1829,3\n308#1:1833\n308#1:1836\n308#1:1841,3\n308#1:1845,3\n308#1:1849\n308#1:1851\n308#1:1852\n308#1:1857\n308#1:1859\n334#1:1868\n341#1:1869\n360#1:1876\n360#1:1880\n367#1:1896\n367#1:1900\n374#1:1916\n374#1:1920\n379#1:1940\n379#1:1944\n385#1:1965\n385#1:1969\n395#1:1990\n395#1:1994\n400#1:2006,3\n400#1:2010\n400#1:2013\n400#1:2018,3\n400#1:2022,3\n400#1:2026\n400#1:2028\n400#1:2029\n400#1:2034\n400#1:2036\n403#1:2046,3\n403#1:2050\n403#1:2053\n403#1:2058,3\n403#1:2062,3\n403#1:2066\n403#1:2068\n403#1:2069\n403#1:2074\n403#1:2076\n407#1:2094\n407#1:2098\n432#1:2119\n432#1:2123\n459#1:2144\n459#1:2148\n485#1:2169\n485#1:2173\n510#1:2194\n510#1:2198\n527#1:2209\n555#1:2210,3\n556#1:2213\n559#1:2214\n563#1:2215,3\n564#1:2218,3\n565#1:2221\n566#1:2222\n566#1:2223\n570#1:2224\n573#1:2225\n*E\n"})
/* loaded from: classes3.dex */
public abstract class ScatterMap<K, V> {

    @JvmField
    public int _capacity;

    @JvmField
    public int _size;

    @JvmField
    public Object[] keys;

    @JvmField
    public long[] metadata;

    @JvmField
    public Object[] values;

    private ScatterMap() {
        this.metadata = ScatterMapKt.EmptyGroup;
        Object[] r0 = ContainerHelpersKt.EMPTY_OBJECTS;
        this.keys = r0;
        this.values = r0;
    }

    public /* synthetic */ ScatterMap(DefaultConstructorMarker r1) {
        this();
    }

    public final boolean containsKey(K r18) {
        if (r18 == null) goto L5;
        int r3 = r18.hashCode();
    L6:
        int r32 = r3 * (-862048943);
        int r33 = r32 ^ (r32 << 16);
        int r4 = r33 & 127;
        int r5 = this._capacity;
        int r34 = (r33 >>> 7) & r5;
        int r6 = 0;
    L7:
        long[] r7 = this.metadata;
        int r8 = r34 >> 3;
        int r9 = (r34 & 7) << 3;
        long r72 = ((r7[r8 + 1] << (64 - r9)) & ((-r9) >> 63)) | (r7[r8] >>> r9);
        long r92 = (r4 * 72340172838076673L) ^ r72;
        long r93 = ((~r92) & (r92 - 72340172838076673L)) & (-9187201950435737472L);
    L9:
        if (r93 == 0) goto L15;
        int r11 = ((Long.numberOfTrailingZeros(r93) >> 3) + r34) & r5;
        if (Intrinsics.areEqual(this.keys[r11], r18) == true) goto L17;
        r93 = r93 & (r93 - 1);
    L17:
        if (r11 < 0) goto L19;
        return true;
    L19:
        return false;
    L15:
        if (((r72 & ((~r72) << 6)) & (-9187201950435737472L)) != 0) goto L16;
        r6 = r6 + 8;
        r34 = (r34 + r6) & r5;
        goto L7
    L16:
        r11 = -1;
        goto L17
    L5:
        r3 = 0;
        goto L6
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean equals(Object r18) {
        if (r18 != this) goto L6;
        return true;
    L6:
        if ((r18 instanceof ScatterMap) == true) goto L8;
        return false;
    L8:
        ScatterMap r1 = (ScatterMap) r18;
        if (r1.getSize() == getSize()) goto L11;
        return false;
    L11:
        Object[] r3 = this.keys;
        Object[] r5 = this.values;
        long[] r0 = this.metadata;
        int r6 = r0.length - 2;
        if (r6 < 0) goto L34;
        int r7 = 0;
    L14:
        long r8 = r0[r7];
        if (((((~r8) << 7) & r8) & (-9187201950435737472L)) == (-9187201950435737472L)) goto L32;
        int r10 = 8 - ((~(r7 - r6)) >>> 31);
        int r12 = 0;
    L17:
        if (r12 >= r10) goto L31;
        if ((255 & r8) >= 128) goto L30;
        int r13 = (r7 << 3) + r12;
        Object r14 = r3[r13];
        Object r132 = r5[r13];
        if (r132 != null) goto L28;
        if (r1.get(r14) != null) goto L26;
        if (r1.containsKey(r14) == true) goto L30;
    L26:
        return false;
    L28:
        if (Intrinsics.areEqual(r132, r1.get(r14)) == true) goto L30;
        return false;
    L30:
        r8 = r8 >> 8;
        r12 = r12 + 1;
        goto L17
    L31:
        if (r10 != 8) goto L34;
    L32:
        if (r7 == r6) goto L34;
        r7 = r7 + 1;
    L34:
        return true;
    }

    public final V get(K r14) {
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
        return (V) this.values[r10];
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

    public final int getCapacity() {
        return this._capacity;
    }

    public final int getSize() {
        return this._size;
    }

    public int hashCode() {
        Object[] r0 = this.keys;
        Object[] r1 = this.values;
        long[] r15 = this.metadata;
        int r2 = r15.length - 2;
        if (r2 < 0) goto L26;
        int r4 = 0;
        int r5 = 0;
    L5:
        long r6 = r15[r4];
        if (((((~r6) << 7) & r6) & (-9187201950435737472L)) == (-9187201950435737472L)) goto L23;
        int r8 = 8 - ((~(r4 - r2)) >>> 31);
        int r10 = 0;
    L8:
        if (r10 >= r8) goto L20;
        if ((255 & r6) >= 128) goto L19;
        int r11 = (r4 << 3) + r10;
        Object r12 = r0[r11];
        Object r112 = r1[r11];
        if (r12 == null) goto L14;
        int r122 = r12.hashCode();
    L15:
        if (r112 == null) goto L17;
        int r113 = r112.hashCode();
    L18:
        r5 = r5 + (r113 ^ r122);
        goto L19
    L17:
        r113 = 0;
        goto L18
    L14:
        r122 = 0;
    L19:
        r6 = r6 >> 8;
        r10 = r10 + 1;
        goto L8
    L20:
        if (r8 == 8) goto L23;
        return r5;
    L23:
        if (r4 == r2) goto L25;
        r4 = r4 + 1;
        goto L5
    L25:
        return r5;
    L26:
        return 0;
    }

    public final boolean isEmpty() {
        if (this._size != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public String toString() {
        if (isEmpty() == false) goto L6;
        return "{}";
    L6:
        StringBuilder r1 = new StringBuilder("{");
        Object[] r2 = this.keys;
        Object[] r3 = this.values;
        long[] r4 = this.metadata;
        int r5 = r4.length - 2;
        if (r5 < 0) goto L28;
        int r7 = 0;
        int r8 = 0;
    L9:
        long r9 = r4[r7];
        if (((((~r9) << 7) & r9) & (-9187201950435737472L)) == (-9187201950435737472L)) goto L26;
        int r11 = 8 - ((~(r7 - r5)) >>> 31);
        int r13 = 0;
    L12:
        if (r13 >= r11) goto L25;
        if ((255 & r9) >= 128) goto L24;
        int r14 = (r7 << 3) + r13;
        Object r15 = r2[r14];
        Object r142 = r3[r14];
        if (r15 != this) goto L18;
        r15 = "(this)";
    L18:
        r1.append(r15);
        r1.append("=");
        if (r142 != this) goto L21;
        r142 = "(this)";
    L21:
        r1.append(r142);
        r8 = r8 + 1;
        if (r8 >= this._size) goto L24;
        r1.append(", ");
    L24:
        r9 = r9 >> 8;
        r13 = r13 + 1;
        goto L12
    L25:
        if (r11 != 8) goto L28;
    L26:
        if (r7 == r5) goto L28;
        r7 = r7 + 1;
    L28:
        r1.append('}');
        return r1.toString();
    }
}
