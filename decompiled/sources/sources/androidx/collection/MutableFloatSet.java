package androidx.collection;

import androidx.collection.internal.RuntimeHelpersKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0007\u0010\u0005J\u0017\u0010\t\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\t\u0010\u0005J\u000f\u0010\n\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/collection/MutableFloatSet;", "Landroidx/collection/FloatSet;", _UrlKt.FRAGMENT_ENCODE_SET, "initialCapacity", "<init>", "(I)V", _UrlKt.FRAGMENT_ENCODE_SET, "initializeStorage", "capacity", "initializeMetadata", "initializeGrowth", "()V", "growthLimit", "I", "collection"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFloatSet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FloatSet.kt\nandroidx/collection/MutableFloatSet\n+ 2 RuntimeHelpers.kt\nandroidx/collection/internal/RuntimeHelpersKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 ScatterMap.kt\nandroidx/collection/ScatterMapKt\n+ 5 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 6 FloatSet.kt\nandroidx/collection/FloatSet\n+ 7 FloatSet.kt\nandroidx/collection/FloatSetKt\n*L\n1#1,886:1\n59#2,5:887\n1#3:892\n1254#4,6:893\n1399#4:912\n1270#4:916\n1179#4:932\n1175#4:935\n1372#4,3:939\n1386#4,3:943\n1312#4:947\n1303#4:949\n1297#4:950\n1309#4:955\n1393#4:957\n1179#4:971\n1175#4:974\n1372#4,3:978\n1386#4,3:982\n1312#4:986\n1303#4:988\n1297#4:989\n1309#4:994\n1393#4:996\n1399#4:1018\n1270#4:1022\n1230#4:1033\n1254#4,6:1034\n1242#4:1040\n1241#4,4:1041\n1254#4,6:1045\n1175#4:1054\n1179#4:1055\n1372#4,3:1056\n1386#4,3:1059\n1312#4:1062\n1303#4:1063\n1297#4:1064\n1309#4:1065\n1393#4:1066\n1265#4:1067\n1220#4:1068\n1262#4:1069\n1220#4:1070\n1230#4:1071\n1254#4,6:1072\n1242#4:1078\n1241#4,4:1079\n1372#4,3:1083\n1399#4:1086\n1297#4:1087\n1144#4,14:1088\n1220#4:1102\n1175#4:1106\n1179#4:1107\n1254#4,6:1108\n1220#4:1114\n1179#4:1115\n1254#4,6:1116\n1254#4,6:1122\n1179#4:1128\n1254#4,6:1129\n1268#4:1135\n1220#4:1136\n1175#4:1140\n1179#4:1141\n1230#4:1142\n1254#4,6:1143\n1242#4:1149\n1241#4,4:1150\n13344#5,2:899\n13344#5,2:1005\n257#6,4:901\n227#6,7:905\n238#6,3:913\n241#6,9:917\n261#6:926\n427#6:927\n428#6:931\n430#6,2:933\n432#6,3:936\n435#6:942\n436#6:946\n437#6:948\n438#6,4:951\n444#6:956\n445#6,8:958\n427#6:966\n428#6:970\n430#6,2:972\n432#6,3:975\n435#6:981\n436#6:985\n437#6:987\n438#6,4:990\n444#6:995\n445#6,8:997\n257#6,4:1007\n227#6,7:1011\n238#6,3:1019\n241#6,9:1023\n261#6:1032\n882#7,3:928\n882#7,3:967\n882#7,3:1051\n882#7,3:1103\n882#7,3:1137\n*S KotlinDebug\n*F\n+ 1 FloatSet.kt\nandroidx/collection/MutableFloatSet\n*L\n479#1:887,5\n506#1:893,6\n579#1:912\n579#1:916\n590#1:932\n590#1:935\n590#1:939,3\n590#1:943,3\n590#1:947\n590#1:949\n590#1:950\n590#1:955\n590#1:957\n604#1:971\n604#1:974\n604#1:978,3\n604#1:982,3\n604#1:986\n604#1:988\n604#1:989\n604#1:994\n604#1:996\n649#1:1018\n649#1:1022\n657#1:1033\n657#1:1034,6\n657#1:1040\n657#1:1041,4\n665#1:1045,6\n678#1:1054\n679#1:1055\n686#1:1056,3\n687#1:1059,3\n688#1:1062\n689#1:1063\n689#1:1064\n693#1:1065\n696#1:1066\n705#1:1067\n705#1:1068\n711#1:1069\n711#1:1070\n712#1:1071\n712#1:1072,6\n712#1:1078\n712#1:1079,4\n726#1:1083,3\n727#1:1086\n729#1:1087\n775#1:1088,14\n781#1:1102\n796#1:1106\n807#1:1107\n808#1:1108,6\n818#1:1114\n821#1:1115\n822#1:1116,6\n823#1:1122,6\n830#1:1128\n831#1:1129,6\n864#1:1135\n864#1:1136\n867#1:1140\n869#1:1141\n869#1:1142\n869#1:1143,6\n869#1:1149\n869#1:1150,4\n557#1:899,2\n628#1:1005,2\n579#1:901,4\n579#1:905,7\n579#1:913,3\n579#1:917,9\n579#1:926\n590#1:927\n590#1:931\n590#1:933,2\n590#1:936,3\n590#1:942\n590#1:946\n590#1:948\n590#1:951,4\n590#1:956\n590#1:958,8\n604#1:966\n604#1:970\n604#1:972,2\n604#1:975,3\n604#1:981\n604#1:985\n604#1:987\n604#1:990,4\n604#1:995\n604#1:997,8\n649#1:1007,4\n649#1:1011,7\n649#1:1019,3\n649#1:1023,9\n649#1:1032\n590#1:928,3\n604#1:967,3\n677#1:1051,3\n795#1:1103,3\n866#1:1137,3\n*E\n"})
/* loaded from: classes3.dex */
public final class MutableFloatSet extends FloatSet {
    private int growthLimit;

    public MutableFloatSet(int r2) {
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

    private final void initializeGrowth() {
        this.growthLimit = ScatterMapKt.loadedCapacity(getCapacity()) - this._size;
    }

    private final void initializeMetadata(int r9) {
        if (r9 != 0) goto L4;
        long[] r0 = ScatterMapKt.EmptyGroup;
    L5:
        this.metadata = r0;
        int r1 = r9 >> 3;
        long r4 = 255 << ((r9 & 7) << 3);
        r0[r1] = (r0[r1] & (~r4)) | r4;
        initializeGrowth();
        return;
    L4:
        long[] r12 = new long[((r9 + 15) & (-8)) >> 3];
        ArraysKt.fill$default(r12, -9187201950435737472L, 0, 0, 6, null);
        r0 = r12;
        goto L5
    }

    private final void initializeStorage(int r2) {
        if (r2 <= 0) goto L4;
        int r22 = Math.max(7, ScatterMapKt.normalizeCapacity(r2));
    L5:
        this._capacity = r22;
        initializeMetadata(r22);
        this.elements = new float[r22];
        return;
    L4:
        r22 = 0;
        goto L5
    }
}
