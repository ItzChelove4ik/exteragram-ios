package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0016\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0086\u0002¢\u0006\u0004\b\u0007\u0010\bJA\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\tH\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\u0015\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0017\u0010\u0018R\u001c\u0010\u001a\u001a\u00020\u00198\u0000@\u0000X\u0081\u000e¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u0012\u0004\b\u001c\u0010\u0003R\u001c\u0010\u001e\u001a\u00020\u001d8\u0000@\u0000X\u0081\u000e¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u0012\u0004\b \u0010\u0003R\u0016\u0010!\u001a\u00020\u00048\u0000@\u0000X\u0081\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R\u0016\u0010#\u001a\u00020\u00048\u0000@\u0000X\u0081\u000e¢\u0006\u0006\n\u0004\b#\u0010\"R\u0011\u0010%\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b$\u0010\u0013\u0082\u0001\u0001&¨\u0006'"}, d2 = {"Landroidx/collection/IntSet;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "element", _UrlKt.FRAGMENT_ENCODE_SET, "contains", "(I)Z", _UrlKt.FRAGMENT_ENCODE_SET, "separator", "prefix", "postfix", "limit", "truncated", _UrlKt.FRAGMENT_ENCODE_SET, "joinToString", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;)Ljava/lang/String;", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "metadata", "[J", "getMetadata$annotations", _UrlKt.FRAGMENT_ENCODE_SET, "elements", "[I", "getElements$annotations", "_capacity", "I", "_size", "getCapacity", "capacity", "Landroidx/collection/MutableIntSet;", "collection"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nIntSet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IntSet.kt\nandroidx/collection/IntSet\n+ 2 ScatterMap.kt\nandroidx/collection/ScatterMapKt\n+ 3 IntSet.kt\nandroidx/collection/IntSetKt\n*L\n1#1,884:1\n255#1,4:885\n225#1,7:889\n236#1,3:897\n239#1,9:901\n259#1:910\n255#1,4:911\n225#1,7:915\n236#1,3:923\n239#1,9:927\n259#1:936\n225#1,7:939\n236#1,3:947\n239#1,9:951\n255#1,4:960\n225#1,7:964\n236#1,3:972\n239#1,9:976\n259#1:985\n255#1,4:986\n225#1,7:990\n236#1,3:998\n239#1,9:1002\n259#1:1011\n255#1,4:1012\n225#1,7:1016\n236#1,3:1024\n239#1,9:1028\n259#1:1037\n425#1:1038\n426#1:1042\n428#1,2:1044\n430#1,3:1047\n433#1:1053\n434#1:1057\n435#1:1059\n436#1,4:1062\n442#1:1067\n443#1,8:1069\n255#1,4:1077\n225#1,7:1081\n236#1,3:1089\n239#1,9:1093\n259#1:1102\n255#1,4:1103\n225#1,7:1107\n236#1,3:1115\n239#1,9:1119\n259#1:1128\n255#1,4:1129\n225#1,7:1133\n236#1,3:1141\n239#1,9:1145\n259#1:1154\n255#1,4:1155\n225#1,7:1159\n236#1,3:1167\n239#1,9:1171\n259#1:1180\n255#1,4:1181\n225#1,7:1185\n236#1,3:1193\n239#1,9:1197\n259#1:1206\n350#1,11:1223\n255#1,4:1234\n225#1,7:1238\n236#1,3:1246\n239#1,2:1250\n361#1,10:1252\n242#1,6:1262\n259#1:1268\n371#1,2:1269\n350#1,11:1271\n255#1,4:1282\n225#1,7:1286\n236#1,3:1294\n239#1,2:1298\n361#1,10:1300\n242#1,6:1310\n259#1:1316\n371#1,2:1317\n350#1,11:1319\n255#1,4:1330\n225#1,7:1334\n236#1,3:1342\n239#1,2:1346\n361#1,10:1348\n242#1,6:1358\n259#1:1364\n371#1,2:1365\n350#1,11:1367\n255#1,4:1378\n225#1,7:1382\n236#1,3:1390\n239#1,2:1394\n361#1,10:1396\n242#1,6:1406\n259#1:1412\n371#1,2:1413\n350#1,11:1415\n255#1,4:1426\n225#1,7:1430\n236#1,3:1438\n239#1,2:1442\n361#1,10:1444\n242#1,6:1454\n259#1:1460\n371#1,2:1461\n1399#2:896\n1270#2:900\n1399#2:922\n1270#2:926\n1399#2:937\n1270#2:938\n1399#2:946\n1270#2:950\n1399#2:971\n1270#2:975\n1399#2:997\n1270#2:1001\n1399#2:1023\n1270#2:1027\n1179#2:1043\n1175#2:1046\n1372#2,3:1050\n1386#2,3:1054\n1312#2:1058\n1303#2:1060\n1297#2:1061\n1309#2:1066\n1393#2:1068\n1399#2:1088\n1270#2:1092\n1399#2:1114\n1270#2:1118\n1399#2:1140\n1270#2:1144\n1399#2:1166\n1270#2:1170\n1399#2:1192\n1270#2:1196\n1179#2:1210\n1175#2:1211\n1372#2,3:1212\n1386#2,3:1215\n1312#2:1218\n1303#2:1219\n1297#2:1220\n1309#2:1221\n1393#2:1222\n1399#2:1245\n1270#2:1249\n1399#2:1293\n1270#2:1297\n1399#2:1341\n1270#2:1345\n1399#2:1389\n1270#2:1393\n1399#2:1437\n1270#2:1441\n880#3,3:1039\n880#3,3:1207\n*S KotlinDebug\n*F\n+ 1 IntSet.kt\nandroidx/collection/IntSet\n*L\n197#1:885,4\n197#1:889,7\n197#1:897,3\n197#1:901,9\n197#1:910\n218#1:911,4\n218#1:915,7\n218#1:923,3\n218#1:927,9\n218#1:936\n258#1:939,7\n258#1:947,3\n258#1:951,9\n269#1:960,4\n269#1:964,7\n269#1:972,3\n269#1:976,9\n269#1:985\n281#1:986,4\n281#1:990,7\n281#1:998,3\n281#1:1002,9\n281#1:1011\n298#1:1012,4\n298#1:1016,7\n298#1:1024,3\n298#1:1028,9\n298#1:1037\n307#1:1038\n307#1:1042\n307#1:1044,2\n307#1:1047,3\n307#1:1053\n307#1:1057\n307#1:1059\n307#1:1062,4\n307#1:1067\n307#1:1069,8\n327#1:1077,4\n327#1:1081,7\n327#1:1089,3\n327#1:1093,9\n327#1:1102\n360#1:1103,4\n360#1:1107,7\n360#1:1115,3\n360#1:1119,9\n360#1:1128\n360#1:1129,4\n360#1:1133,7\n360#1:1141,3\n360#1:1145,9\n360#1:1154\n381#1:1155,4\n381#1:1159,7\n381#1:1167,3\n381#1:1171,9\n381#1:1180\n405#1:1181,4\n405#1:1185,7\n405#1:1193,3\n405#1:1197,9\n405#1:1206\n-1#1:1223,11\n-1#1:1234,4\n-1#1:1238,7\n-1#1:1246,3\n-1#1:1250,2\n-1#1:1252,10\n-1#1:1262,6\n-1#1:1268\n-1#1:1269,2\n-1#1:1271,11\n-1#1:1282,4\n-1#1:1286,7\n-1#1:1294,3\n-1#1:1298,2\n-1#1:1300,10\n-1#1:1310,6\n-1#1:1316\n-1#1:1317,2\n-1#1:1319,11\n-1#1:1330,4\n-1#1:1334,7\n-1#1:1342,3\n-1#1:1346,2\n-1#1:1348,10\n-1#1:1358,6\n-1#1:1364\n-1#1:1365,2\n-1#1:1367,11\n-1#1:1378,4\n-1#1:1382,7\n-1#1:1390,3\n-1#1:1394,2\n-1#1:1396,10\n-1#1:1406,6\n-1#1:1412\n-1#1:1413,2\n-1#1:1415,11\n-1#1:1426,4\n-1#1:1430,7\n-1#1:1438,3\n-1#1:1442,2\n-1#1:1444,10\n-1#1:1454,6\n-1#1:1460\n-1#1:1461,2\n197#1:896\n197#1:900\n218#1:922\n218#1:926\n231#1:937\n238#1:938\n258#1:946\n258#1:950\n269#1:971\n269#1:975\n281#1:997\n281#1:1001\n298#1:1023\n298#1:1027\n307#1:1043\n307#1:1046\n307#1:1050,3\n307#1:1054,3\n307#1:1058\n307#1:1060\n307#1:1061\n307#1:1066\n307#1:1068\n327#1:1088\n327#1:1092\n360#1:1114\n360#1:1118\n360#1:1140\n360#1:1144\n381#1:1166\n381#1:1170\n405#1:1192\n405#1:1196\n426#1:1210\n429#1:1211\n432#1:1212,3\n433#1:1215,3\n434#1:1218\n435#1:1219\n435#1:1220\n439#1:1221\n442#1:1222\n-1#1:1245\n-1#1:1249\n-1#1:1293\n-1#1:1297\n-1#1:1341\n-1#1:1345\n-1#1:1389\n-1#1:1393\n-1#1:1437\n-1#1:1441\n307#1:1039,3\n425#1:1207,3\n*E\n"})
/* loaded from: classes3.dex */
public abstract class IntSet {

    @JvmField
    public int _capacity;

    @JvmField
    public int _size;

    @JvmField
    public int[] elements;

    @JvmField
    public long[] metadata;

    private IntSet() {
        this.metadata = ScatterMapKt.EmptyGroup;
        this.elements = IntSetKt.getEmptyIntArray();
    }

    public /* synthetic */ IntSet(DefaultConstructorMarker r1) {
        this();
    }

    public static /* synthetic */ String joinToString$default(IntSet r1, CharSequence r2, CharSequence r3, CharSequence r4, int r5, CharSequence r6, int r7, Object r8) {
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

    public final boolean contains(int r19) {
        int r1 = Integer.hashCode(r19) * (-862048943);
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
        if (this.elements[r10] == r19) goto L13;
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
        if ((r15 instanceof IntSet) == true) goto L8;
        return false;
    L8:
        IntSet r152 = (IntSet) r15;
        if (r152._size == this._size) goto L11;
        return false;
    L11:
        int[] r1 = this.elements;
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
        int[] r0 = this.elements;
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
        r4 = r4 + Integer.hashCode(r0[(r3 << 3) + r9]);
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
        int[] r2 = this.elements;
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
        int r12 = r2[(r5 << 3) + r11];
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
