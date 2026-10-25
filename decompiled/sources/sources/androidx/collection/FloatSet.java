package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0016\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0086\u0002¢\u0006\u0004\b\u0007\u0010\bJA\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\tH\u0007¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u001a\u0010\u0016\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001b\u001a\u00020\u001a8\u0000@\u0000X\u0081\u000e¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u0012\u0004\b\u001d\u0010\u0003R\u001c\u0010\u001f\u001a\u00020\u001e8\u0000@\u0000X\u0081\u000e¢\u0006\f\n\u0004\b\u001f\u0010 \u0012\u0004\b!\u0010\u0003R\u0016\u0010\"\u001a\u00020\r8\u0000@\u0000X\u0081\u000e¢\u0006\u0006\n\u0004\b\"\u0010#R\u0016\u0010$\u001a\u00020\r8\u0000@\u0000X\u0081\u000e¢\u0006\u0006\n\u0004\b$\u0010#R\u0011\u0010&\u001a\u00020\r8G¢\u0006\u0006\u001a\u0004\b%\u0010\u0014\u0082\u0001\u0001'¨\u0006("}, d2 = {"Landroidx/collection/FloatSet;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "element", _UrlKt.FRAGMENT_ENCODE_SET, "contains", "(F)Z", _UrlKt.FRAGMENT_ENCODE_SET, "separator", "prefix", "postfix", _UrlKt.FRAGMENT_ENCODE_SET, "limit", "truncated", _UrlKt.FRAGMENT_ENCODE_SET, "joinToString", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;)Ljava/lang/String;", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "metadata", "[J", "getMetadata$annotations", _UrlKt.FRAGMENT_ENCODE_SET, "elements", "[F", "getElements$annotations", "_capacity", "I", "_size", "getCapacity", "capacity", "Landroidx/collection/MutableFloatSet;", "collection"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFloatSet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FloatSet.kt\nandroidx/collection/FloatSet\n+ 2 ScatterMap.kt\nandroidx/collection/ScatterMapKt\n+ 3 FloatSet.kt\nandroidx/collection/FloatSetKt\n*L\n1#1,886:1\n257#1,4:887\n227#1,7:891\n238#1,3:899\n241#1,9:903\n261#1:912\n257#1,4:913\n227#1,7:917\n238#1,3:925\n241#1,9:929\n261#1:938\n227#1,7:941\n238#1,3:949\n241#1,9:953\n257#1,4:962\n227#1,7:966\n238#1,3:974\n241#1,9:978\n261#1:987\n257#1,4:988\n227#1,7:992\n238#1,3:1000\n241#1,9:1004\n261#1:1013\n257#1,4:1014\n227#1,7:1018\n238#1,3:1026\n241#1,9:1030\n261#1:1039\n427#1:1040\n428#1:1044\n430#1,2:1046\n432#1,3:1049\n435#1:1055\n436#1:1059\n437#1:1061\n438#1,4:1064\n444#1:1069\n445#1,8:1071\n257#1,4:1079\n227#1,7:1083\n238#1,3:1091\n241#1,9:1095\n261#1:1104\n257#1,4:1105\n227#1,7:1109\n238#1,3:1117\n241#1,9:1121\n261#1:1130\n257#1,4:1131\n227#1,7:1135\n238#1,3:1143\n241#1,9:1147\n261#1:1156\n257#1,4:1157\n227#1,7:1161\n238#1,3:1169\n241#1,9:1173\n261#1:1182\n257#1,4:1183\n227#1,7:1187\n238#1,3:1195\n241#1,9:1199\n261#1:1208\n352#1,11:1225\n257#1,4:1236\n227#1,7:1240\n238#1,3:1248\n241#1,2:1252\n363#1,10:1254\n244#1,6:1264\n261#1:1270\n373#1,2:1271\n352#1,11:1273\n257#1,4:1284\n227#1,7:1288\n238#1,3:1296\n241#1,2:1300\n363#1,10:1302\n244#1,6:1312\n261#1:1318\n373#1,2:1319\n352#1,11:1321\n257#1,4:1332\n227#1,7:1336\n238#1,3:1344\n241#1,2:1348\n363#1,10:1350\n244#1,6:1360\n261#1:1366\n373#1,2:1367\n352#1,11:1369\n257#1,4:1380\n227#1,7:1384\n238#1,3:1392\n241#1,2:1396\n363#1,10:1398\n244#1,6:1408\n261#1:1414\n373#1,2:1415\n352#1,11:1417\n257#1,4:1428\n227#1,7:1432\n238#1,3:1440\n241#1,2:1444\n363#1,10:1446\n244#1,6:1456\n261#1:1462\n373#1,2:1463\n1399#2:898\n1270#2:902\n1399#2:924\n1270#2:928\n1399#2:939\n1270#2:940\n1399#2:948\n1270#2:952\n1399#2:973\n1270#2:977\n1399#2:999\n1270#2:1003\n1399#2:1025\n1270#2:1029\n1179#2:1045\n1175#2:1048\n1372#2,3:1052\n1386#2,3:1056\n1312#2:1060\n1303#2:1062\n1297#2:1063\n1309#2:1068\n1393#2:1070\n1399#2:1090\n1270#2:1094\n1399#2:1116\n1270#2:1120\n1399#2:1142\n1270#2:1146\n1399#2:1168\n1270#2:1172\n1399#2:1194\n1270#2:1198\n1179#2:1212\n1175#2:1213\n1372#2,3:1214\n1386#2,3:1217\n1312#2:1220\n1303#2:1221\n1297#2:1222\n1309#2:1223\n1393#2:1224\n1399#2:1247\n1270#2:1251\n1399#2:1295\n1270#2:1299\n1399#2:1343\n1270#2:1347\n1399#2:1391\n1270#2:1395\n1399#2:1439\n1270#2:1443\n882#3,3:1041\n882#3,3:1209\n*S KotlinDebug\n*F\n+ 1 FloatSet.kt\nandroidx/collection/FloatSet\n*L\n199#1:887,4\n199#1:891,7\n199#1:899,3\n199#1:903,9\n199#1:912\n220#1:913,4\n220#1:917,7\n220#1:925,3\n220#1:929,9\n220#1:938\n260#1:941,7\n260#1:949,3\n260#1:953,9\n271#1:962,4\n271#1:966,7\n271#1:974,3\n271#1:978,9\n271#1:987\n283#1:988,4\n283#1:992,7\n283#1:1000,3\n283#1:1004,9\n283#1:1013\n300#1:1014,4\n300#1:1018,7\n300#1:1026,3\n300#1:1030,9\n300#1:1039\n309#1:1040\n309#1:1044\n309#1:1046,2\n309#1:1049,3\n309#1:1055\n309#1:1059\n309#1:1061\n309#1:1064,4\n309#1:1069\n309#1:1071,8\n329#1:1079,4\n329#1:1083,7\n329#1:1091,3\n329#1:1095,9\n329#1:1104\n362#1:1105,4\n362#1:1109,7\n362#1:1117,3\n362#1:1121,9\n362#1:1130\n362#1:1131,4\n362#1:1135,7\n362#1:1143,3\n362#1:1147,9\n362#1:1156\n383#1:1157,4\n383#1:1161,7\n383#1:1169,3\n383#1:1173,9\n383#1:1182\n407#1:1183,4\n407#1:1187,7\n407#1:1195,3\n407#1:1199,9\n407#1:1208\n-1#1:1225,11\n-1#1:1236,4\n-1#1:1240,7\n-1#1:1248,3\n-1#1:1252,2\n-1#1:1254,10\n-1#1:1264,6\n-1#1:1270\n-1#1:1271,2\n-1#1:1273,11\n-1#1:1284,4\n-1#1:1288,7\n-1#1:1296,3\n-1#1:1300,2\n-1#1:1302,10\n-1#1:1312,6\n-1#1:1318\n-1#1:1319,2\n-1#1:1321,11\n-1#1:1332,4\n-1#1:1336,7\n-1#1:1344,3\n-1#1:1348,2\n-1#1:1350,10\n-1#1:1360,6\n-1#1:1366\n-1#1:1367,2\n-1#1:1369,11\n-1#1:1380,4\n-1#1:1384,7\n-1#1:1392,3\n-1#1:1396,2\n-1#1:1398,10\n-1#1:1408,6\n-1#1:1414\n-1#1:1415,2\n-1#1:1417,11\n-1#1:1428,4\n-1#1:1432,7\n-1#1:1440,3\n-1#1:1444,2\n-1#1:1446,10\n-1#1:1456,6\n-1#1:1462\n-1#1:1463,2\n199#1:898\n199#1:902\n220#1:924\n220#1:928\n233#1:939\n240#1:940\n260#1:948\n260#1:952\n271#1:973\n271#1:977\n283#1:999\n283#1:1003\n300#1:1025\n300#1:1029\n309#1:1045\n309#1:1048\n309#1:1052,3\n309#1:1056,3\n309#1:1060\n309#1:1062\n309#1:1063\n309#1:1068\n309#1:1070\n329#1:1090\n329#1:1094\n362#1:1116\n362#1:1120\n362#1:1142\n362#1:1146\n383#1:1168\n383#1:1172\n407#1:1194\n407#1:1198\n428#1:1212\n431#1:1213\n434#1:1214,3\n435#1:1217,3\n436#1:1220\n437#1:1221\n437#1:1222\n441#1:1223\n444#1:1224\n-1#1:1247\n-1#1:1251\n-1#1:1295\n-1#1:1299\n-1#1:1343\n-1#1:1347\n-1#1:1391\n-1#1:1395\n-1#1:1439\n-1#1:1443\n309#1:1041,3\n427#1:1209,3\n*E\n"})
/* loaded from: classes3.dex */
public abstract class FloatSet {

    @JvmField
    public int _capacity;

    @JvmField
    public int _size;

    @JvmField
    public float[] elements;

    @JvmField
    public long[] metadata;

    private FloatSet() {
        this.metadata = ScatterMapKt.EmptyGroup;
        this.elements = FloatSetKt.getEmptyFloatArray();
    }

    public /* synthetic */ FloatSet(DefaultConstructorMarker r1) {
        this();
    }

    public static /* synthetic */ String joinToString$default(FloatSet r1, CharSequence r2, CharSequence r3, CharSequence r4, int r5, CharSequence r6, int r7, Object r8) {
        if (r8 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: joinToString");
        return null;
    L4:
        if ((r7 & 1) == 0) goto L7;
        r2 = ", ";
    L7:
        if ((r7 & 2) == 0) goto L10;
        r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        if ((r7 & 4) == 0) goto L13;
        r4 = _UrlKt.FRAGMENT_ENCODE_SET;
    L13:
        if ((r7 & 8) == 0) goto L16;
        r5 = -1;
    L16:
        if ((r7 & 16) == 0) goto L18;
        r6 = "...";
    L18:
        int r72 = r5;
        CharSequence r82 = r6;
        CharSequence r62 = r4;
        CharSequence r42 = r2;
        return r1.joinToString(r42, r3, r62, r72, r82);
    }

    public final boolean contains(float r17) {
        int r1 = Float.hashCode(r17) * (-862048943);
        int r12 = r1 ^ (r1 << 16);
        int r2 = r12 & 127;
        int r3 = this._capacity;
        int r13 = (r12 >>> 7) & r3;
        int r5 = 0;
    L3:
        long[] r6 = this.metadata;
        int r7 = r13 >> 3;
        int r8 = (r13 & 7) << 3;
        long r62 = ((r6[r7 + 1] << (64 - r8)) & ((-r8) >> 63)) | (r6[r7] >>> r8);
        long r82 = (r2 * 72340172838076673L) ^ r62;
        long r83 = ((~r82) & (r82 - 72340172838076673L)) & (-9187201950435737472L);
    L5:
        if (r83 == 0) goto L11;
        int r10 = ((Long.numberOfTrailingZeros(r83) >> 3) + r13) & r3;
        if (this.elements[r10] == r17) goto L13;
        r83 = r83 & (r83 - 1);
    L13:
        if (r10 < 0) goto L15;
        return true;
    L15:
        return false;
    L11:
        if (((r62 & ((~r62) << 6)) & (-9187201950435737472L)) != 0) goto L12;
        r5 = r5 + 8;
        r13 = (r13 + r5) & r3;
        goto L3
    L12:
        r10 = -1;
        goto L13
    }

    public boolean equals(Object r15) {
        if (r15 != this) goto L6;
        return true;
    L6:
        if ((r15 instanceof FloatSet) == true) goto L8;
        return false;
    L8:
        FloatSet r152 = (FloatSet) r15;
        if (r152._size == this._size) goto L11;
        return false;
    L11:
        float[] r1 = this.elements;
        long[] r14 = this.metadata;
        int r3 = r14.length - 2;
        if (r3 < 0) goto L27;
        int r4 = 0;
    L14:
        long r5 = r14[r4];
        if (((((~r5) << 7) & r5) & (-9187201950435737472L)) == (-9187201950435737472L)) goto L25;
        int r7 = 8 - ((~(r4 - r3)) >>> 31);
        int r9 = 0;
    L17:
        if (r9 >= r7) goto L24;
        if ((255 & r5) >= 128) goto L23;
        if (r152.contains(r1[(r4 << 3) + r9]) == true) goto L23;
        return false;
    L23:
        r5 = r5 >> 8;
        r9 = r9 + 1;
        goto L17
    L24:
        if (r7 != 8) goto L27;
    L25:
        if (r4 == r3) goto L27;
        r4 = r4 + 1;
    L27:
        return true;
    }

    public final int getCapacity() {
        return this._capacity;
    }

    public int hashCode() {
        float[] r0 = this.elements;
        long[] r14 = this.metadata;
        int r1 = r14.length - 2;
        if (r1 < 0) goto L19;
        int r3 = 0;
        int r4 = 0;
    L5:
        long r5 = r14[r3];
        if (((((~r5) << 7) & r5) & (-9187201950435737472L)) == (-9187201950435737472L)) goto L16;
        int r7 = 8 - ((~(r3 - r1)) >>> 31);
        int r9 = 0;
    L8:
        if (r9 >= r7) goto L13;
        if ((255 & r5) >= 128) goto L12;
        r4 = r4 + Float.hashCode(r0[(r3 << 3) + r9]);
    L12:
        r5 = r5 >> 8;
        r9 = r9 + 1;
        goto L8
    L13:
        if (r7 == 8) goto L16;
        return r4;
    L16:
        if (r3 == r1) goto L18;
        r3 = r3 + 1;
        goto L5
    L18:
        return r4;
    L19:
        return 0;
    }

    @JvmOverloads
    public final String joinToString(CharSequence r17, CharSequence r18, CharSequence r19, int r20, CharSequence r21) {
        StringBuilder r1 = new StringBuilder();
        r1.append(r18);
        float[] r2 = this.elements;
        long[] r0 = this.metadata;
        int r3 = r0.length - 2;
        if (r3 < 0) goto L27;
        int r5 = 0;
        int r6 = 0;
    L5:
        long r7 = r0[r5];
        if (((((~r7) << 7) & r7) & (-9187201950435737472L)) == (-9187201950435737472L)) goto L25;
        int r9 = 8 - ((~(r5 - r3)) >>> 31);
        int r11 = 0;
    L8:
        if (r11 >= r9) goto L21;
        if ((255 & r7) >= 128) goto L19;
        float r12 = r2[(r5 << 3) + r11];
        if (r6 == r20) goto L13;
        if (r6 == 0) goto L17;
        r1.append(r17);
    L17:
        r1.append(r12);
        r6 = r6 + 1;
        goto L19
    L13:
        r1.append(r21);
    L29:
        return r1.toString();
    L19:
        r7 = r7 >> 8;
        r11 = r11 + 1;
        goto L8
    L21:
        if (r9 != 8) goto L27;
    L25:
        if (r5 == r3) goto L27;
        r5 = r5 + 1;
    L27:
        r1.append(r19);
        goto L29
    }

    public String toString() {
        return joinToString$default(this, null, "[", "]", 0, null, 25, null);
    }
}
