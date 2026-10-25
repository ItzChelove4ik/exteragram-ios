.class public abstract Landroidx/collection/IntSet;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0002\n\u0002\u0010\u0008\n\u0000\n\u0002\u0010\u000b\n\u0002\u0008\u0002\n\u0002\u0010\r\n\u0002\u0008\u0005\n\u0002\u0010\u000e\n\u0002\u0008\t\n\u0002\u0010\u0016\n\u0002\u0008\u0003\n\u0002\u0010\u0015\n\u0002\u0008\u0008\n\u0002\u0018\u0002\n\u0000\u00086\u0018\u00002\u00020\u0001B\t\u0008\u0004\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0086\u0002\u00a2\u0006\u0004\u0008\u0007\u0010\u0008JA\u0010\u0010\u001a\u00020\u000f2\u0008\u0008\u0002\u0010\n\u001a\u00020\t2\u0008\u0008\u0002\u0010\u000b\u001a\u00020\t2\u0008\u0008\u0002\u0010\u000c\u001a\u00020\t2\u0008\u0008\u0002\u0010\r\u001a\u00020\u00042\u0008\u0008\u0002\u0010\u000e\u001a\u00020\tH\u0007\u00a2\u0006\u0004\u0008\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\u0008\u0012\u0010\u0013J\u001a\u0010\u0015\u001a\u00020\u00062\u0008\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0096\u0002\u00a2\u0006\u0004\u0008\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u000fH\u0016\u00a2\u0006\u0004\u0008\u0017\u0010\u0018R\u001c\u0010\u001a\u001a\u00020\u00198\u0000@\u0000X\u0081\u000e\u00a2\u0006\u000c\n\u0004\u0008\u001a\u0010\u001b\u0012\u0004\u0008\u001c\u0010\u0003R\u001c\u0010\u001e\u001a\u00020\u001d8\u0000@\u0000X\u0081\u000e\u00a2\u0006\u000c\n\u0004\u0008\u001e\u0010\u001f\u0012\u0004\u0008 \u0010\u0003R\u0016\u0010!\u001a\u00020\u00048\u0000@\u0000X\u0081\u000e\u00a2\u0006\u0006\n\u0004\u0008!\u0010\"R\u0016\u0010#\u001a\u00020\u00048\u0000@\u0000X\u0081\u000e\u00a2\u0006\u0006\n\u0004\u0008#\u0010\"R\u0011\u0010%\u001a\u00020\u00048G\u00a2\u0006\u0006\u001a\u0004\u0008$\u0010\u0013\u0082\u0001\u0001&\u00a8\u0006\'"
    }
    d2 = {
        "Landroidx/collection/IntSet;",
        "",
        "<init>",
        "()V",
        "",
        "element",
        "",
        "contains",
        "(I)Z",
        "",
        "separator",
        "prefix",
        "postfix",
        "limit",
        "truncated",
        "",
        "joinToString",
        "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;)Ljava/lang/String;",
        "hashCode",
        "()I",
        "other",
        "equals",
        "(Ljava/lang/Object;)Z",
        "toString",
        "()Ljava/lang/String;",
        "",
        "metadata",
        "[J",
        "getMetadata$annotations",
        "",
        "elements",
        "[I",
        "getElements$annotations",
        "_capacity",
        "I",
        "_size",
        "getCapacity",
        "capacity",
        "Landroidx/collection/MutableIntSet;",
        "collection"
    }
    k = 0x1
    mv = {
        0x1,
        0x9,
        0x0
    }
    xi = 0x30
.end annotation

.annotation build Lkotlin/jvm/internal/SourceDebugExtension;
    value = {
        "SMAP\nIntSet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IntSet.kt\nandroidx/collection/IntSet\n+ 2 ScatterMap.kt\nandroidx/collection/ScatterMapKt\n+ 3 IntSet.kt\nandroidx/collection/IntSetKt\n*L\n1#1,884:1\n255#1,4:885\n225#1,7:889\n236#1,3:897\n239#1,9:901\n259#1:910\n255#1,4:911\n225#1,7:915\n236#1,3:923\n239#1,9:927\n259#1:936\n225#1,7:939\n236#1,3:947\n239#1,9:951\n255#1,4:960\n225#1,7:964\n236#1,3:972\n239#1,9:976\n259#1:985\n255#1,4:986\n225#1,7:990\n236#1,3:998\n239#1,9:1002\n259#1:1011\n255#1,4:1012\n225#1,7:1016\n236#1,3:1024\n239#1,9:1028\n259#1:1037\n425#1:1038\n426#1:1042\n428#1,2:1044\n430#1,3:1047\n433#1:1053\n434#1:1057\n435#1:1059\n436#1,4:1062\n442#1:1067\n443#1,8:1069\n255#1,4:1077\n225#1,7:1081\n236#1,3:1089\n239#1,9:1093\n259#1:1102\n255#1,4:1103\n225#1,7:1107\n236#1,3:1115\n239#1,9:1119\n259#1:1128\n255#1,4:1129\n225#1,7:1133\n236#1,3:1141\n239#1,9:1145\n259#1:1154\n255#1,4:1155\n225#1,7:1159\n236#1,3:1167\n239#1,9:1171\n259#1:1180\n255#1,4:1181\n225#1,7:1185\n236#1,3:1193\n239#1,9:1197\n259#1:1206\n350#1,11:1223\n255#1,4:1234\n225#1,7:1238\n236#1,3:1246\n239#1,2:1250\n361#1,10:1252\n242#1,6:1262\n259#1:1268\n371#1,2:1269\n350#1,11:1271\n255#1,4:1282\n225#1,7:1286\n236#1,3:1294\n239#1,2:1298\n361#1,10:1300\n242#1,6:1310\n259#1:1316\n371#1,2:1317\n350#1,11:1319\n255#1,4:1330\n225#1,7:1334\n236#1,3:1342\n239#1,2:1346\n361#1,10:1348\n242#1,6:1358\n259#1:1364\n371#1,2:1365\n350#1,11:1367\n255#1,4:1378\n225#1,7:1382\n236#1,3:1390\n239#1,2:1394\n361#1,10:1396\n242#1,6:1406\n259#1:1412\n371#1,2:1413\n350#1,11:1415\n255#1,4:1426\n225#1,7:1430\n236#1,3:1438\n239#1,2:1442\n361#1,10:1444\n242#1,6:1454\n259#1:1460\n371#1,2:1461\n1399#2:896\n1270#2:900\n1399#2:922\n1270#2:926\n1399#2:937\n1270#2:938\n1399#2:946\n1270#2:950\n1399#2:971\n1270#2:975\n1399#2:997\n1270#2:1001\n1399#2:1023\n1270#2:1027\n1179#2:1043\n1175#2:1046\n1372#2,3:1050\n1386#2,3:1054\n1312#2:1058\n1303#2:1060\n1297#2:1061\n1309#2:1066\n1393#2:1068\n1399#2:1088\n1270#2:1092\n1399#2:1114\n1270#2:1118\n1399#2:1140\n1270#2:1144\n1399#2:1166\n1270#2:1170\n1399#2:1192\n1270#2:1196\n1179#2:1210\n1175#2:1211\n1372#2,3:1212\n1386#2,3:1215\n1312#2:1218\n1303#2:1219\n1297#2:1220\n1309#2:1221\n1393#2:1222\n1399#2:1245\n1270#2:1249\n1399#2:1293\n1270#2:1297\n1399#2:1341\n1270#2:1345\n1399#2:1389\n1270#2:1393\n1399#2:1437\n1270#2:1441\n880#3,3:1039\n880#3,3:1207\n*S KotlinDebug\n*F\n+ 1 IntSet.kt\nandroidx/collection/IntSet\n*L\n197#1:885,4\n197#1:889,7\n197#1:897,3\n197#1:901,9\n197#1:910\n218#1:911,4\n218#1:915,7\n218#1:923,3\n218#1:927,9\n218#1:936\n258#1:939,7\n258#1:947,3\n258#1:951,9\n269#1:960,4\n269#1:964,7\n269#1:972,3\n269#1:976,9\n269#1:985\n281#1:986,4\n281#1:990,7\n281#1:998,3\n281#1:1002,9\n281#1:1011\n298#1:1012,4\n298#1:1016,7\n298#1:1024,3\n298#1:1028,9\n298#1:1037\n307#1:1038\n307#1:1042\n307#1:1044,2\n307#1:1047,3\n307#1:1053\n307#1:1057\n307#1:1059\n307#1:1062,4\n307#1:1067\n307#1:1069,8\n327#1:1077,4\n327#1:1081,7\n327#1:1089,3\n327#1:1093,9\n327#1:1102\n360#1:1103,4\n360#1:1107,7\n360#1:1115,3\n360#1:1119,9\n360#1:1128\n360#1:1129,4\n360#1:1133,7\n360#1:1141,3\n360#1:1145,9\n360#1:1154\n381#1:1155,4\n381#1:1159,7\n381#1:1167,3\n381#1:1171,9\n381#1:1180\n405#1:1181,4\n405#1:1185,7\n405#1:1193,3\n405#1:1197,9\n405#1:1206\n-1#1:1223,11\n-1#1:1234,4\n-1#1:1238,7\n-1#1:1246,3\n-1#1:1250,2\n-1#1:1252,10\n-1#1:1262,6\n-1#1:1268\n-1#1:1269,2\n-1#1:1271,11\n-1#1:1282,4\n-1#1:1286,7\n-1#1:1294,3\n-1#1:1298,2\n-1#1:1300,10\n-1#1:1310,6\n-1#1:1316\n-1#1:1317,2\n-1#1:1319,11\n-1#1:1330,4\n-1#1:1334,7\n-1#1:1342,3\n-1#1:1346,2\n-1#1:1348,10\n-1#1:1358,6\n-1#1:1364\n-1#1:1365,2\n-1#1:1367,11\n-1#1:1378,4\n-1#1:1382,7\n-1#1:1390,3\n-1#1:1394,2\n-1#1:1396,10\n-1#1:1406,6\n-1#1:1412\n-1#1:1413,2\n-1#1:1415,11\n-1#1:1426,4\n-1#1:1430,7\n-1#1:1438,3\n-1#1:1442,2\n-1#1:1444,10\n-1#1:1454,6\n-1#1:1460\n-1#1:1461,2\n197#1:896\n197#1:900\n218#1:922\n218#1:926\n231#1:937\n238#1:938\n258#1:946\n258#1:950\n269#1:971\n269#1:975\n281#1:997\n281#1:1001\n298#1:1023\n298#1:1027\n307#1:1043\n307#1:1046\n307#1:1050,3\n307#1:1054,3\n307#1:1058\n307#1:1060\n307#1:1061\n307#1:1066\n307#1:1068\n327#1:1088\n327#1:1092\n360#1:1114\n360#1:1118\n360#1:1140\n360#1:1144\n381#1:1166\n381#1:1170\n405#1:1192\n405#1:1196\n426#1:1210\n429#1:1211\n432#1:1212,3\n433#1:1215,3\n434#1:1218\n435#1:1219\n435#1:1220\n439#1:1221\n442#1:1222\n-1#1:1245\n-1#1:1249\n-1#1:1293\n-1#1:1297\n-1#1:1341\n-1#1:1345\n-1#1:1389\n-1#1:1393\n-1#1:1437\n-1#1:1441\n307#1:1039,3\n425#1:1207,3\n*E\n"
    }
.end annotation


# instance fields
.field public _capacity:I
    .annotation build Lkotlin/jvm/JvmField;
    .end annotation
.end field

.field public _size:I
    .annotation build Lkotlin/jvm/JvmField;
    .end annotation
.end field

.field public elements:[I
    .annotation build Lkotlin/jvm/JvmField;
    .end annotation
.end field

.field public metadata:[J
    .annotation build Lkotlin/jvm/JvmField;
    .end annotation
.end field


# direct methods
.method private constructor <init>()V
    .locals 1

    .line 149
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 154
    sget-object v0, Landroidx/collection/ScatterMapKt;->EmptyGroup:[J

    iput-object v0, p0, Landroidx/collection/IntSet;->metadata:[J

    .line 156
    invoke-static {}, Landroidx/collection/IntSetKt;->getEmptyIntArray()[I

    move-result-object v0

    iput-object v0, p0, Landroidx/collection/IntSet;->elements:[I

    return-void
.end method

.method public synthetic constructor <init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Landroidx/collection/IntSet;-><init>()V

    return-void
.end method

.method public static synthetic joinToString$default(Landroidx/collection/IntSet;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;ILjava/lang/Object;)Ljava/lang/String;
    .locals 1

    if-nez p7, :cond_5

    and-int/lit8 p7, p6, 0x1

    if-eqz p7, :cond_0

    .line 319
    const-string p1, ", "

    :cond_0
    and-int/lit8 p7, p6, 0x2

    .line 318
    const-string v0, ""

    if-eqz p7, :cond_1

    move-object p2, v0

    :cond_1
    and-int/lit8 p7, p6, 0x4

    if-eqz p7, :cond_2

    move-object p3, v0

    :cond_2
    and-int/lit8 p7, p6, 0x8

    if-eqz p7, :cond_3

    const/4 p4, -0x1

    :cond_3
    and-int/lit8 p6, p6, 0x10

    if-eqz p6, :cond_4

    .line 323
    const-string p5, "..."

    :cond_4
    move p6, p4

    move-object p7, p5

    move-object p4, p2

    move-object p5, p3

    move-object p2, p0

    move-object p3, p1

    .line 318
    invoke-virtual/range {p2 .. p7}, Landroidx/collection/IntSet;->joinToString(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_5
    const-string p0, "Super calls with default arguments not supported in this target, function: joinToString"

    invoke-static {p0}, Lokio/ByteString$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method


# virtual methods
.method public final contains(I)Z
    .locals 18

    move-object/from16 v0, p0

    .line 880
    invoke-static/range {p1 .. p1}, Ljava/lang/Integer;->hashCode(I)I

    move-result v1

    const v2, -0x3361d2af    # -8.293031E7f

    mul-int/2addr v1, v2

    shl-int/lit8 v2, v1, 0x10

    xor-int/2addr v1, v2

    and-int/lit8 v2, v1, 0x7f

    .line 428
    iget v3, v0, Landroidx/collection/IntSet;->_capacity:I

    ushr-int/lit8 v1, v1, 0x7

    and-int/2addr v1, v3

    const/4 v4, 0x0

    move v5, v4

    .line 432
    :goto_0
    iget-object v6, v0, Landroidx/collection/IntSet;->metadata:[J

    shr-int/lit8 v7, v1, 0x3

    and-int/lit8 v8, v1, 0x7

    shl-int/lit8 v8, v8, 0x3

    .line 1374
    aget-wide v9, v6, v7

    ushr-long/2addr v9, v8

    const/4 v11, 0x1

    add-int/2addr v7, v11

    aget-wide v12, v6, v7

    rsub-int/lit8 v6, v8, 0x40

    shl-long v6, v12, v6

    int-to-long v12, v8

    neg-long v12, v12

    const/16 v8, 0x3f

    shr-long/2addr v12, v8

    and-long/2addr v6, v12

    or-long/2addr v6, v9

    int-to-long v8, v2

    const-wide v12, 0x101010101010101L

    mul-long/2addr v8, v12

    xor-long/2addr v8, v6

    sub-long v12, v8, v12

    not-long v8, v8

    and-long/2addr v8, v12

    const-wide v12, -0x7f7f7f7f7f7f7f80L    # -2.937446524422997E-306

    and-long/2addr v8, v12

    :goto_1
    const-wide/16 v14, 0x0

    cmp-long v10, v8, v14

    if-eqz v10, :cond_1

    .line 1297
    invoke-static {v8, v9}, Ljava/lang/Long;->numberOfTrailingZeros(J)I

    move-result v10

    shr-int/lit8 v10, v10, 0x3

    add-int/2addr v10, v1

    and-int/2addr v10, v3

    .line 436
    iget-object v14, v0, Landroidx/collection/IntSet;->elements:[I

    aget v14, v14, v10

    move/from16 v15, p1

    if-ne v14, v15, :cond_0

    goto :goto_2

    :cond_0
    const-wide/16 v16, 0x1

    sub-long v16, v8, v16

    and-long v8, v8, v16

    goto :goto_1

    :cond_1
    not-long v8, v6

    const/4 v10, 0x6

    shl-long/2addr v8, v10

    and-long/2addr v6, v8

    and-long/2addr v6, v12

    cmp-long v6, v6, v14

    if-eqz v6, :cond_3

    const/4 v10, -0x1

    :goto_2
    if-ltz v10, :cond_2

    return v11

    :cond_2
    return v4

    :cond_3
    add-int/lit8 v5, v5, 0x8

    add-int/2addr v1, v5

    and-int/2addr v1, v3

    goto :goto_0
.end method

.method public equals(Ljava/lang/Object;)Z
    .locals 14

    const/4 v0, 0x1

    if-ne p1, p0, :cond_0

    return v0

    .line 398
    :cond_0
    instance-of v1, p1, Landroidx/collection/IntSet;

    const/4 v2, 0x0

    if-nez v1, :cond_1

    return v2

    .line 401
    :cond_1
    check-cast p1, Landroidx/collection/IntSet;

    iget v1, p1, Landroidx/collection/IntSet;->_size:I

    iget v3, p0, Landroidx/collection/IntSet;->_size:I

    if-eq v1, v3, :cond_2

    return v2

    .line 256
    :cond_2
    iget-object v1, p0, Landroidx/collection/IntSet;->elements:[I

    .line 226
    iget-object p0, p0, Landroidx/collection/IntSet;->metadata:[J

    .line 227
    array-length v3, p0

    add-int/lit8 v3, v3, -0x2

    if-ltz v3, :cond_6

    move v4, v2

    .line 230
    :goto_0
    aget-wide v5, p0, v4

    not-long v7, v5

    const/4 v9, 0x7

    shl-long/2addr v7, v9

    and-long/2addr v7, v5

    const-wide v9, -0x7f7f7f7f7f7f7f80L    # -2.937446524422997E-306

    and-long/2addr v7, v9

    cmp-long v7, v7, v9

    if-eqz v7, :cond_5

    sub-int v7, v4, v3

    not-int v7, v7

    ushr-int/lit8 v7, v7, 0x1f

    const/16 v8, 0x8

    rsub-int/lit8 v7, v7, 0x8

    move v9, v2

    :goto_1
    if-ge v9, v7, :cond_4

    const-wide/16 v10, 0xff

    and-long/2addr v10, v5

    const-wide/16 v12, 0x80

    cmp-long v10, v10, v12

    if-gez v10, :cond_3

    shl-int/lit8 v10, v4, 0x3

    add-int/2addr v10, v9

    .line 258
    aget v10, v1, v10

    .line 406
    invoke-virtual {p1, v10}, Landroidx/collection/IntSet;->contains(I)Z

    move-result v10

    if-nez v10, :cond_3

    return v2

    :cond_3
    shr-long/2addr v5, v8

    add-int/lit8 v9, v9, 0x1

    goto :goto_1

    :cond_4
    if-ne v7, v8, :cond_6

    :cond_5
    if-eq v4, v3, :cond_6

    add-int/lit8 v4, v4, 0x1

    goto :goto_0

    :cond_6
    return v0
.end method

.method public final getCapacity()I
    .locals 0

    .line 168
    iget p0, p0, Landroidx/collection/IntSet;->_capacity:I

    return p0
.end method

.method public hashCode()I
    .locals 14

    .line 256
    iget-object v0, p0, Landroidx/collection/IntSet;->elements:[I

    .line 226
    iget-object p0, p0, Landroidx/collection/IntSet;->metadata:[J

    .line 227
    array-length v1, p0

    add-int/lit8 v1, v1, -0x2

    const/4 v2, 0x0

    if-ltz v1, :cond_5

    move v3, v2

    move v4, v3

    .line 230
    :goto_0
    aget-wide v5, p0, v3

    not-long v7, v5

    const/4 v9, 0x7

    shl-long/2addr v7, v9

    and-long/2addr v7, v5

    const-wide v9, -0x7f7f7f7f7f7f7f80L    # -2.937446524422997E-306

    and-long/2addr v7, v9

    cmp-long v7, v7, v9

    if-eqz v7, :cond_3

    sub-int v7, v3, v1

    not-int v7, v7

    ushr-int/lit8 v7, v7, 0x1f

    const/16 v8, 0x8

    rsub-int/lit8 v7, v7, 0x8

    move v9, v2

    :goto_1
    if-ge v9, v7, :cond_1

    const-wide/16 v10, 0xff

    and-long/2addr v10, v5

    const-wide/16 v12, 0x80

    cmp-long v10, v10, v12

    if-gez v10, :cond_0

    shl-int/lit8 v10, v3, 0x3

    add-int/2addr v10, v9

    .line 258
    aget v10, v0, v10

    .line 381
    invoke-static {v10}, Ljava/lang/Integer;->hashCode(I)I

    move-result v10

    add-int/2addr v4, v10

    :cond_0
    shr-long/2addr v5, v8

    add-int/lit8 v9, v9, 0x1

    goto :goto_1

    :cond_1
    if-ne v7, v8, :cond_2

    goto :goto_2

    :cond_2
    return v4

    :cond_3
    :goto_2
    if-eq v3, v1, :cond_4

    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_4
    return v4

    :cond_5
    return v2
.end method

.method public final joinToString(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;)Ljava/lang/String;
    .locals 16
    .annotation build Lkotlin/jvm/JvmOverloads;
    .end annotation

    move-object/from16 v0, p0

    .line 324
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    move-object/from16 v2, p2

    .line 325
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/CharSequence;)Ljava/lang/StringBuilder;

    .line 256
    iget-object v2, v0, Landroidx/collection/IntSet;->elements:[I

    .line 226
    iget-object v0, v0, Landroidx/collection/IntSet;->metadata:[J

    .line 227
    array-length v3, v0

    add-int/lit8 v3, v3, -0x2

    if-ltz v3, :cond_4

    const/4 v4, 0x0

    move v5, v4

    move v6, v5

    .line 230
    :goto_0
    aget-wide v7, v0, v5

    not-long v9, v7

    const/4 v11, 0x7

    shl-long/2addr v9, v11

    and-long/2addr v9, v7

    const-wide v11, -0x7f7f7f7f7f7f7f80L    # -2.937446524422997E-306

    and-long/2addr v9, v11

    cmp-long v9, v9, v11

    if-eqz v9, :cond_5

    sub-int v9, v5, v3

    not-int v9, v9

    ushr-int/lit8 v9, v9, 0x1f

    const/16 v10, 0x8

    rsub-int/lit8 v9, v9, 0x8

    move v11, v4

    :goto_1
    if-ge v11, v9, :cond_3

    const-wide/16 v12, 0xff

    and-long/2addr v12, v7

    const-wide/16 v14, 0x80

    cmp-long v12, v12, v14

    if-gez v12, :cond_2

    shl-int/lit8 v12, v5, 0x3

    add-int/2addr v12, v11

    .line 258
    aget v12, v2, v12

    move/from16 v13, p4

    if-ne v6, v13, :cond_0

    move-object/from16 v14, p5

    .line 329
    invoke-virtual {v1, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/CharSequence;)Ljava/lang/StringBuilder;

    goto :goto_5

    :cond_0
    move-object/from16 v14, p5

    move-object/from16 v15, p1

    if-eqz v6, :cond_1

    .line 333
    invoke-virtual {v1, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/CharSequence;)Ljava/lang/StringBuilder;

    .line 335
    :cond_1
    invoke-virtual {v1, v12}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    add-int/lit8 v6, v6, 0x1

    goto :goto_2

    :cond_2
    move-object/from16 v15, p1

    move/from16 v13, p4

    move-object/from16 v14, p5

    :goto_2
    shr-long/2addr v7, v10

    add-int/lit8 v11, v11, 0x1

    goto :goto_1

    :cond_3
    move-object/from16 v15, p1

    move/from16 v13, p4

    move-object/from16 v14, p5

    if-ne v9, v10, :cond_4

    goto :goto_3

    :cond_4
    move-object/from16 v0, p3

    goto :goto_4

    :cond_5
    move-object/from16 v15, p1

    move/from16 v13, p4

    move-object/from16 v14, p5

    :goto_3
    if-eq v5, v3, :cond_4

    add-int/lit8 v5, v5, 0x1

    goto :goto_0

    .line 338
    :goto_4
    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/CharSequence;)Ljava/lang/StringBuilder;

    .line 324
    :goto_5
    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public toString()Ljava/lang/String;
    .locals 8

    const/16 v6, 0x19

    const/4 v7, 0x0

    const/4 v1, 0x0

    .line 418
    const-string v2, "["

    const-string v3, "]"

    const/4 v4, 0x0

    const/4 v5, 0x0

    move-object v0, p0

    invoke-static/range {v0 .. v7}, Landroidx/collection/IntSet;->joinToString$default(Landroidx/collection/IntSet;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
