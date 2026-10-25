.class public final Lcom/exteragram/messenger/icons/IconManager;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;,
        Lcom/exteragram/messenger/icons/IconManager$IntStringMap;,
        Lcom/exteragram/messenger/icons/IconManager$ResolvedIndex;,
        Lcom/exteragram/messenger/icons/IconManager$SourceCacheKey;,
        Lcom/exteragram/messenger/icons/IconManager$WhenMappings;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u00ae\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0002\n\u0002\u0010\u0008\n\u0002\u0008\u0002\n\u0002\u0010\t\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0008\u0002\n\u0002\u0010 \n\u0002\u0008\u0006\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0007\n\u0002\u0018\u0002\n\u0002\u0008\u0007\n\u0002\u0018\u0002\n\u0002\u0008\u001e\n\u0002\u0018\u0002\n\u0002\u0008\t\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0010\"\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0007\n\u0002\u0018\u0002\n\u0002\u0008\u0007*\u0002\u00a4\u0001\u0008\u00c6\u0002\u0018\u00002\u00020\u0001:\u0008\u00bd\u0001\u00be\u0001\u00bf\u0001\u00c0\u0001B\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u001f\u0010\u0008\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\u0008\u0008\u0010\tJ/\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000c2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\u0008\u000f\u0010\u0010J\u001f\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u000c2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\u0008\u0013\u0010\u0014J?\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u000c2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\u0008\u001a\u0010\u001bJ\u001d\u0010\u001e\u001a\u00020\u00192\u000c\u0010\u001d\u001a\u0008\u0012\u0004\u0012\u00020\n0\u001cH\u0002\u00a2\u0006\u0004\u0008\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\u0008 \u0010\u0003J\u000f\u0010!\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\u0008!\u0010\u0003J%\u0010$\u001a\u00020#2\u0006\u0010\u0015\u001a\u00020\u00072\u000c\u0010\"\u001a\u0008\u0012\u0004\u0012\u00020\n0\u001cH\u0002\u00a2\u0006\u0004\u0008$\u0010%J!\u0010(\u001a\u0004\u0018\u00010\'2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010&\u001a\u00020\u000cH\u0002\u00a2\u0006\u0004\u0008(\u0010)J\u0017\u0010*\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\u0008*\u0010+J)\u0010,\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\u0008,\u0010-JM\u00103\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u000c\u00100\u001a\u0008\u0018\u00010.R\u00020/2\n\u0008\u0002\u00101\u001a\u0004\u0018\u00010\u000c2\u0008\u0008\u0002\u00102\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\u00083\u00104J\u000f\u00106\u001a\u000205H\u0007\u00a2\u0006\u0004\u00086\u00107J\u000f\u00109\u001a\u000208H\u0007\u00a2\u0006\u0004\u00089\u0010:J\u0017\u0010=\u001a\u00020\u000c2\u0006\u0010<\u001a\u00020;H\u0002\u00a2\u0006\u0004\u0008=\u0010>J\u001f\u0010A\u001a\u00020\u00122\u0006\u0010@\u001a\u00020?2\u0006\u0010<\u001a\u00020;H\u0002\u00a2\u0006\u0004\u0008A\u0010BJ\u0015\u0010D\u001a\u00020\u00192\u0006\u0010C\u001a\u00020\u000c\u00a2\u0006\u0004\u0008D\u0010EJ\r\u0010F\u001a\u00020\u0012\u00a2\u0006\u0004\u0008F\u0010\u0003J-\u0010H\u001a\u0004\u0018\u00010G2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u000c\u00100\u001a\u0008\u0018\u00010.R\u00020/\u00a2\u0006\u0004\u0008H\u0010IJ\u0017\u0010J\u001a\u0004\u0018\u00010G2\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\u0008J\u0010KJ\u001f\u0010L\u001a\u0004\u0018\u00010G2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\u0008L\u0010MJ\u001f\u0010N\u001a\u0004\u0018\u00010G2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\u0008N\u0010MJ+\u0010Q\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u000c\u0010P\u001a\u0008\u0012\u0004\u0012\u00020G0O\u00a2\u0006\u0004\u0008Q\u0010RJ#\u0010S\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00042\u000c\u0010P\u001a\u0008\u0012\u0004\u0012\u00020G0O\u00a2\u0006\u0004\u0008S\u0010TJ5\u0010W\u001a\u0004\u0018\u00010\u00172\u0006\u0010U\u001a\u00020\u000c2\u0006\u0010V\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u000c\u00100\u001a\u0008\u0018\u00010.R\u00020/\u00a2\u0006\u0004\u0008W\u0010XJ/\u0010[\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u000c2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010Y\u001a\u00020\'2\u0008\u0010Z\u001a\u0004\u0018\u00010\u000c\u00a2\u0006\u0004\u0008[\u0010\\J\u001d\u0010]\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u000c2\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\u0008]\u0010\u0014J\u0015\u0010^\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\u0008^\u0010_J\u0017\u0010a\u001a\u00020\u00122\u0008\u0008\u0002\u0010`\u001a\u00020\u0019\u00a2\u0006\u0004\u0008a\u0010bJ\r\u0010c\u001a\u00020\u0012\u00a2\u0006\u0004\u0008c\u0010\u0003J\u0017\u0010d\u001a\u00020\u00122\u0008\u0010\u0011\u001a\u0004\u0018\u00010\u000c\u00a2\u0006\u0004\u0008d\u0010eJ\u0017\u0010f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0011\u001a\u00020\u000c\u00a2\u0006\u0004\u0008f\u0010gJ\u0017\u0010h\u001a\u0004\u0018\u00010\'2\u0006\u0010\u0011\u001a\u00020\u000c\u00a2\u0006\u0004\u0008h\u0010iJ\u0015\u0010k\u001a\u00020\u00192\u0006\u0010j\u001a\u00020\n\u00a2\u0006\u0004\u0008k\u0010lJ\u0015\u0010m\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u000c\u00a2\u0006\u0004\u0008m\u0010eJ\u0017\u0010p\u001a\u00020\u00192\u0008\u0010o\u001a\u0004\u0018\u00010n\u00a2\u0006\u0004\u0008p\u0010qJ\u001f\u0010p\u001a\u00020\u00192\u0006\u0010U\u001a\u00020\u000c2\u0008\u0010o\u001a\u0004\u0018\u00010n\u00a2\u0006\u0004\u0008p\u0010rJ\u001d\u0010s\u001a\u00020\u00122\u0006\u0010@\u001a\u00020?2\u0006\u0010o\u001a\u00020n\u00a2\u0006\u0004\u0008s\u0010tJ\u001d\u0010s\u001a\u00020\u00122\u0006\u0010@\u001a\u00020?2\u0006\u0010U\u001a\u00020\u000c\u00a2\u0006\u0004\u0008s\u0010uJ\'\u0010z\u001a\u00020\u00192\u0006\u0010v\u001a\u00020\u00042\u0006\u0010w\u001a\u00020\u00042\u0008\u0010y\u001a\u0004\u0018\u00010x\u00a2\u0006\u0004\u0008z\u0010{J7\u0010\u0081\u0001\u001a\u00020\u00122\u0006\u0010}\u001a\u00020|2\u0006\u0010~\u001a\u00020\u00192\u0015\u0010P\u001a\u0011\u0012\u0007\u0012\u0005\u0018\u00010\u0080\u0001\u0012\u0004\u0012\u00020\u00120\u007f\u00a2\u0006\u0006\u0008\u0081\u0001\u0010\u0082\u0001J0\u0010\u0085\u0001\u001a\u00020\u00122\u0008\u0010\u0084\u0001\u001a\u00030\u0083\u00012\u0006\u0010\u0005\u001a\u00020\u00042\n\u0008\u0002\u0010j\u001a\u0004\u0018\u00010\nH\u0007\u00a2\u0006\u0006\u0008\u0085\u0001\u0010\u0086\u0001J\u001a\u0010\u0089\u0001\u001a\u00020\u00192\u0008\u0010\u0088\u0001\u001a\u00030\u0087\u0001\u00a2\u0006\u0006\u0008\u0089\u0001\u0010\u008a\u0001R\u0018\u0010\u008c\u0001\u001a\u00030\u008b\u00018\u0002X\u0082\u0004\u00a2\u0006\u0008\n\u0006\u0008\u008c\u0001\u0010\u008d\u0001R\u0018\u0010\u008f\u0001\u001a\u00030\u008e\u00018\u0002X\u0082\u0004\u00a2\u0006\u0008\n\u0006\u0008\u008f\u0001\u0010\u0090\u0001R\u0018\u0010\u0091\u0001\u001a\u00030\u008e\u00018\u0002X\u0082\u0004\u00a2\u0006\u0008\n\u0006\u0008\u0091\u0001\u0010\u0090\u0001R\u0018\u0010\u0092\u0001\u001a\u00030\u008e\u00018\u0002X\u0082\u0004\u00a2\u0006\u0008\n\u0006\u0008\u0092\u0001\u0010\u0090\u0001R\u001e\u0010\u0094\u0001\u001a\t\u0012\u0004\u0012\u00020\u000c0\u0093\u00018\u0002X\u0082\u0004\u00a2\u0006\u0008\n\u0006\u0008\u0094\u0001\u0010\u0095\u0001R)\u0010\u0097\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u000c\u0012\u0004\u0012\u00020\u00040\u0096\u00018\u0006\u00a2\u0006\u0010\n\u0006\u0008\u0097\u0001\u0010\u0098\u0001\u001a\u0006\u0008\u0099\u0001\u0010\u009a\u0001R\u001c\u0010\u009c\u0001\u001a\u0005\u0018\u00010\u009b\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0008\n\u0006\u0008\u009c\u0001\u0010\u009d\u0001R\u0017\u0010\u009e\u0001\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0008\n\u0006\u0008\u009e\u0001\u0010\u009f\u0001R\u0017\u0010\u00a0\u0001\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0008\n\u0006\u0008\u00a0\u0001\u0010\u009f\u0001R\u0018\u0010\u00a2\u0001\u001a\u00030\u00a1\u00018\u0002X\u0082\u0004\u00a2\u0006\u0008\n\u0006\u0008\u00a2\u0001\u0010\u00a3\u0001R\u0018\u0010\u00a5\u0001\u001a\u00030\u00a4\u00018\u0002X\u0082\u0004\u00a2\u0006\u0008\n\u0006\u0008\u00a5\u0001\u0010\u00a6\u0001R\u001e\u0010\u00a8\u0001\u001a\t\u0012\u0004\u0012\u00020\n0\u00a7\u00018\u0002X\u0082\u0004\u00a2\u0006\u0008\n\u0006\u0008\u00a8\u0001\u0010\u00a9\u0001R$\u0010\u00aa\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u000c\u0012\u0004\u0012\u00020\n0\u0096\u00018\u0002X\u0082\u0004\u00a2\u0006\u0008\n\u0006\u0008\u00aa\u0001\u0010\u0098\u0001R\u001c\u0010\u00ac\u0001\u001a\u0005\u0018\u00010\u00ab\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0008\n\u0006\u0008\u00ac\u0001\u0010\u00ad\u0001R\u001c\u0010\u00af\u0001\u001a\u0005\u0018\u00010\u00ae\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0008\n\u0006\u0008\u00af\u0001\u0010\u00b0\u0001R\u001c\u0010\u00b1\u0001\u001a\u0005\u0018\u00010\u00ae\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0008\n\u0006\u0008\u00b1\u0001\u0010\u00b0\u0001R\u0018\u0010\u00b3\u0001\u001a\u00030\u00b2\u00018\u0002X\u0082\u0004\u00a2\u0006\u0008\n\u0006\u0008\u00b3\u0001\u0010\u00b4\u0001R\u0019\u0010\u00b5\u0001\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0008\n\u0006\u0008\u00b5\u0001\u0010\u00b6\u0001R\u0019\u0010\u00b7\u0001\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0008\n\u0006\u0008\u00b7\u0001\u0010\u00b6\u0001R\u0019\u0010\u00b8\u0001\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0008\n\u0006\u0008\u00b8\u0001\u0010\u00b9\u0001R-\u0010\u00bb\u0001\u001a\u0018\u0012\u0013\u0012\u0011\u0012\u0007\u0012\u0005\u0018\u00010\u0080\u0001\u0012\u0004\u0012\u00020\u00120\u007f0\u00ba\u00018\u0002X\u0082\u0004\u00a2\u0006\u0008\n\u0006\u0008\u00bb\u0001\u0010\u00bc\u0001\u00a8\u0006\u00c1\u0001"
    }
    d2 = {
        "Lcom/exteragram/messenger/icons/IconManager;",
        "",
        "<init>",
        "()V",
        "",
        "resId",
        "density",
        "",
        "resolvedCacheKey",
        "(II)J",
        "Lcom/exteragram/messenger/icons/IconPack;",
        "pack",
        "",
        "fileName",
        "Lcom/exteragram/messenger/icons/IconManager$SourceCacheKey;",
        "sourceCacheKey",
        "(Lcom/exteragram/messenger/icons/IconPack;Ljava/lang/String;II)Lcom/exteragram/messenger/icons/IconManager$SourceCacheKey;",
        "packId",
        "",
        "invalidateIconCaches",
        "(Ljava/lang/String;I)V",
        "generation",
        "resourceName",
        "Landroid/graphics/Bitmap;",
        "bitmap",
        "",
        "publishBitmap",
        "(JLjava/lang/String;Lcom/exteragram/messenger/icons/IconPack;IILandroid/graphics/Bitmap;)Z",
        "",
        "packs",
        "syncInstalledCustomPacks",
        "(Ljava/util/List;)Z",
        "rebuildOwnerMap",
        "rebuildBasePreinstalledMap",
        "newActivePacks",
        "Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;",
        "updateActivePacks",
        "(JLjava/util/List;)Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;",
        "iconFileName",
        "Ljava/io/File;",
        "resolvePackIconFile",
        "(Lcom/exteragram/messenger/icons/IconPack;Ljava/lang/String;)Ljava/io/File;",
        "launchPrewarm",
        "(J)V",
        "cachedPackIconBitmap",
        "(Lcom/exteragram/messenger/icons/IconPack;II)Landroid/graphics/Bitmap;",
        "Landroid/content/res/Resources$Theme;",
        "Landroid/content/res/Resources;",
        "theme",
        "knownResourceName",
        "cacheResult",
        "getPackIconBitmap",
        "(Lcom/exteragram/messenger/icons/IconPack;IILandroid/content/res/Resources$Theme;Ljava/lang/String;Z)Landroid/graphics/Bitmap;",
        "Landroidx/core/graphics/drawable/IconCompat;",
        "getNotificationIcon",
        "()Landroidx/core/graphics/drawable/IconCompat;",
        "Landroid/graphics/drawable/Icon;",
        "getNotificationSystemIcon",
        "()Landroid/graphics/drawable/Icon;",
        "Lcom/exteragram/messenger/icons/IconPackStorageError;",
        "error",
        "iconPackErrorText",
        "(Lcom/exteragram/messenger/icons/IconPackStorageError;)Ljava/lang/String;",
        "Lorg/telegram/ui/ActionBar/BaseFragment;",
        "baseFragment",
        "showIconPackError",
        "(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/icons/IconPackStorageError;)V",
        "name",
        "isBlacklisted",
        "(Ljava/lang/String;)Z",
        "prefetchCustomPacks",
        "Landroid/graphics/drawable/Drawable;",
        "getDrawable",
        "(IILandroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;",
        "getCachedDrawable",
        "(I)Landroid/graphics/drawable/Drawable;",
        "getPackIconDrawable",
        "(Lcom/exteragram/messenger/icons/IconPack;I)Landroid/graphics/drawable/Drawable;",
        "getCachedPackIconDrawable",
        "Lorg/telegram/messenger/Utilities$Callback;",
        "callback",
        "requestPackIconDrawable",
        "(Lcom/exteragram/messenger/icons/IconPack;ILorg/telegram/messenger/Utilities$Callback;)V",
        "requestDrawable",
        "(ILorg/telegram/messenger/Utilities$Callback;)V",
        "path",
        "originalResId",
        "createBitmapFromFile",
        "(Ljava/lang/String;IILandroid/content/res/Resources$Theme;)Landroid/graphics/Bitmap;",
        "tempFile",
        "originalName",
        "saveCustomIcon",
        "(Ljava/lang/String;ILjava/io/File;Ljava/lang/String;)V",
        "resetCustomIcon",
        "getIcon",
        "(I)I",
        "update",
        "initialize",
        "(Z)V",
        "awaitInitialization",
        "setActiveCustomPack",
        "(Ljava/lang/String;)V",
        "findPackById",
        "(Ljava/lang/String;)Lcom/exteragram/messenger/icons/IconPack;",
        "bundlePackBlocking",
        "(Ljava/lang/String;)Ljava/io/File;",
        "iconPack",
        "saveIconPackMetadata",
        "(Lcom/exteragram/messenger/icons/IconPack;)Z",
        "deletePack",
        "Lorg/telegram/messenger/MessageObject;",
        "messageObject",
        "isIconPack",
        "(Lorg/telegram/messenger/MessageObject;)Z",
        "(Ljava/lang/String;Lorg/telegram/messenger/MessageObject;)Z",
        "handleIconPack",
        "(Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/messenger/MessageObject;)V",
        "(Lorg/telegram/ui/ActionBar/BaseFragment;Ljava/lang/String;)V",
        "requestCode",
        "resultCode",
        "Landroid/content/Intent;",
        "data",
        "onActivityResult",
        "(IILandroid/content/Intent;)Z",
        "Landroid/app/Activity;",
        "activity",
        "selectFromFiles",
        "Lkotlin/Function1;",
        "Landroid/net/Uri;",
        "startIconPicker",
        "(Landroid/app/Activity;ZLkotlin/jvm/functions/Function1;)V",
        "Landroid/content/Context;",
        "context",
        "showReplaceAlert",
        "(Landroid/content/Context;ILcom/exteragram/messenger/icons/IconPack;)V",
        "Lcom/exteragram/messenger/IconPackType;",
        "basePackType",
        "isBasePackOnly",
        "(Lcom/exteragram/messenger/IconPackType;)Z",
        "Lkotlinx/coroutines/CoroutineScope;",
        "scope",
        "Lkotlinx/coroutines/CoroutineScope;",
        "Lkotlinx/coroutines/CoroutineDispatcher;",
        "mutationDispatcher",
        "Lkotlinx/coroutines/CoroutineDispatcher;",
        "decodeDispatcher",
        "prewarmDispatcher",
        "",
        "blacklistedIcons",
        "Ljava/util/Set;",
        "Ljava/util/concurrent/ConcurrentHashMap;",
        "systemIcons",
        "Ljava/util/concurrent/ConcurrentHashMap;",
        "getSystemIcons",
        "()Ljava/util/concurrent/ConcurrentHashMap;",
        "Lcom/exteragram/messenger/icons/IconManager$IntStringMap;",
        "systemNames",
        "Lcom/exteragram/messenger/icons/IconManager$IntStringMap;",
        "maxMemory",
        "I",
        "cacheSize",
        "Lcom/exteragram/messenger/icons/IconManager$ResolvedIndex;",
        "resolvedCache",
        "Lcom/exteragram/messenger/icons/IconManager$ResolvedIndex;",
        "com/exteragram/messenger/icons/IconManager$sourceCache$1",
        "sourceCache",
        "Lcom/exteragram/messenger/icons/IconManager$sourceCache$1;",
        "Ljava/util/concurrent/CopyOnWriteArrayList;",
        "activePacks",
        "Ljava/util/concurrent/CopyOnWriteArrayList;",
        "iconOwnerMap",
        "Landroid/util/SparseIntArray;",
        "basePreinstalledMap",
        "Landroid/util/SparseIntArray;",
        "Lkotlinx/coroutines/Job;",
        "initializationJob",
        "Lkotlinx/coroutines/Job;",
        "prewarmJob",
        "Ljava/util/concurrent/CountDownLatch;",
        "initialPacksLoaded",
        "Ljava/util/concurrent/CountDownLatch;",
        "activePacksPublished",
        "Z",
        "initializationTimedOut",
        "initializationGeneration",
        "J",
        "Landroid/util/SparseArray;",
        "resultCallbacks",
        "Landroid/util/SparseArray;",
        "IntStringMap",
        "SourceCacheKey",
        "ResolvedIndex",
        "ActivePacksUpdate",
        "TMessagesProj"
    }
    k = 0x1
    mv = {
        0x2,
        0x2,
        0x0
    }
    xi = 0x30
.end annotation

.annotation build Lkotlin/jvm/internal/SourceDebugExtension;
    value = {
        "SMAP\nIconManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 SparseIntArray.kt\nandroidx/core/util/SparseIntArrayKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 BitmapDrawable.kt\nandroidx/core/graphics/drawable/BitmapDrawableKt\n+ 6 Bitmap.kt\nandroidx/core/graphics/BitmapKt\n+ 7 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,1058:1\n777#2:1059\n873#2,2:1060\n1915#2,2:1062\n296#2,2:1084\n296#2,2:1086\n2792#2,3:1088\n25#3:1064\n56#3:1065\n1#4:1066\n27#5:1067\n27#5:1068\n27#5:1069\n27#5:1070\n27#5:1071\n27#5:1072\n83#6,6:1073\n71#6:1079\n184#7,2:1080\n184#7,2:1082\n*S KotlinDebug\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager\n*L\n285#1:1059\n285#1:1060,2\n286#1:1062,2\n1045#1:1084,2\n1047#1:1086,2\n1055#1:1088,3\n355#1:1064\n362#1:1065\n422#1:1067\n437#1:1068\n448#1:1069\n453#1:1070\n464#1:1071\n470#1:1072\n547#1:1073,6\n584#1:1079\n721#1:1080,2\n749#1:1082,2\n*E\n"
    }
.end annotation


# static fields
.field public static final INSTANCE:Lcom/exteragram/messenger/icons/IconManager;

.field private static final activePacks:Ljava/util/concurrent/CopyOnWriteArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/CopyOnWriteArrayList<",
            "Lcom/exteragram/messenger/icons/IconPack;",
            ">;"
        }
    .end annotation
.end field

.field private static activePacksPublished:Z

.field private static volatile basePreinstalledMap:Landroid/util/SparseIntArray;

.field private static final blacklistedIcons:Ljava/util/Set;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Set<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private static final cacheSize:I

.field private static final decodeDispatcher:Lkotlinx/coroutines/CoroutineDispatcher;

.field private static final iconOwnerMap:Ljava/util/concurrent/ConcurrentHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/String;",
            "Lcom/exteragram/messenger/icons/IconPack;",
            ">;"
        }
    .end annotation
.end field

.field private static final initialPacksLoaded:Ljava/util/concurrent/CountDownLatch;

.field private static volatile initializationGeneration:J

.field private static initializationJob:Lkotlinx/coroutines/Job;

.field private static volatile initializationTimedOut:Z

.field private static final maxMemory:I

.field private static final mutationDispatcher:Lkotlinx/coroutines/CoroutineDispatcher;

.field private static final prewarmDispatcher:Lkotlinx/coroutines/CoroutineDispatcher;

.field private static prewarmJob:Lkotlinx/coroutines/Job;

.field private static final resolvedCache:Lcom/exteragram/messenger/icons/IconManager$ResolvedIndex;

.field private static final resultCallbacks:Landroid/util/SparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/SparseArray<",
            "Lkotlin/jvm/functions/Function1<",
            "Landroid/net/Uri;",
            "Lkotlin/Unit;",
            ">;>;"
        }
    .end annotation
.end field

.field private static final scope:Lkotlinx/coroutines/CoroutineScope;

.field private static final sourceCache:Lcom/exteragram/messenger/icons/IconManager$sourceCache$1;

.field private static final systemIcons:Ljava/util/concurrent/ConcurrentHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private static volatile systemNames:Lcom/exteragram/messenger/icons/IconManager$IntStringMap;


# direct methods
.method public static $r8$lambda$8195jxe_T9ZAmG347S-81JjIZH0(Ljava/lang/String;)Z
    .locals 4

    const/4 v0, 0x2

    const/4 v1, 0x0

    .line 719
    const-string v2, "base."

    const/4 v3, 0x0

    invoke-static {p0, v2, v3, v0, v1}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method public static $r8$lambda$E6tDhC_y1b8zP0tKCJrvjcsgNzw(Ljava/lang/String;)Lcom/exteragram/messenger/icons/IconPack;
    .locals 1

    .line 720
    sget-object v0, Lcom/exteragram/messenger/icons/IconPackStorage;->INSTANCE:Lcom/exteragram/messenger/icons/IconPackStorage;

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/icons/IconPackStorage;->findPackById(Ljava/lang/String;)Lcom/exteragram/messenger/icons/IconPack;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$HH0pVZuhCCTzorOy7KzrzVfUcNw(Landroid/util/SparseIntArray;)I
    .locals 2

    .line 748
    sget v0, Lorg/telegram/messenger/R$drawable;->notification:I

    const/4 v1, -0x1

    invoke-virtual {p0, v0, v1}, Landroid/util/SparseIntArray;->get(II)I

    move-result p0

    return p0
.end method

.method public static $r8$lambda$Vs1GV53ha9TZG53mOuPSdSA1wwU(Ljava/lang/String;)Landroid/util/SparseIntArray;
    .locals 1

    .line 747
    sget-object v0, Lcom/exteragram/messenger/icons/BaseIconPacks;->INSTANCE:Lcom/exteragram/messenger/icons/BaseIconPacks;

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/icons/BaseIconPacks;->getBasePack(Ljava/lang/String;)Lcom/exteragram/messenger/icons/IconPack;

    move-result-object p0

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Lcom/exteragram/messenger/icons/IconPack;->getPreinstalledMap()Landroid/util/SparseIntArray;

    move-result-object p0

    return-object p0

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public static $r8$lambda$iEQWJ4hI0MeHcC2H1Bp3U61udlc(Ljava/lang/String;)Z
    .locals 4

    const/4 v0, 0x2

    const/4 v1, 0x0

    .line 746
    const-string v2, "base."

    const/4 v3, 0x0

    invoke-static {p0, v2, v3, v0, v1}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method static constructor <clinit>()V
    .locals 108

    new-instance v0, Lcom/exteragram/messenger/icons/IconManager;

    invoke-direct {v0}, Lcom/exteragram/messenger/icons/IconManager;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/icons/IconManager;->INSTANCE:Lcom/exteragram/messenger/icons/IconManager;

    .line 71
    invoke-static {}, Lkotlinx/coroutines/Dispatchers;->getIO()Lkotlinx/coroutines/CoroutineDispatcher;

    move-result-object v1

    const/4 v2, 0x0

    const/4 v3, 0x1

    invoke-static {v2, v3, v2}, Lkotlinx/coroutines/SupervisorKt;->SupervisorJob$default(Lkotlinx/coroutines/Job;ILjava/lang/Object;)Lkotlinx/coroutines/CompletableJob;

    move-result-object v4

    invoke-virtual {v1, v4}, Lkotlin/coroutines/AbstractCoroutineContextElement;->plus(Lkotlin/coroutines/CoroutineContext;)Lkotlin/coroutines/CoroutineContext;

    move-result-object v1

    invoke-static {v1}, Lkotlinx/coroutines/CoroutineScopeKt;->CoroutineScope(Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/CoroutineScope;

    move-result-object v1

    sput-object v1, Lcom/exteragram/messenger/icons/IconManager;->scope:Lkotlinx/coroutines/CoroutineScope;

    .line 72
    invoke-static {}, Lkotlinx/coroutines/Dispatchers;->getIO()Lkotlinx/coroutines/CoroutineDispatcher;

    move-result-object v1

    const/4 v4, 0x2

    invoke-static {v1, v3, v2, v4, v2}, Lkotlinx/coroutines/CoroutineDispatcher;->limitedParallelism$default(Lkotlinx/coroutines/CoroutineDispatcher;ILjava/lang/String;ILjava/lang/Object;)Lkotlinx/coroutines/CoroutineDispatcher;

    move-result-object v1

    sput-object v1, Lcom/exteragram/messenger/icons/IconManager;->mutationDispatcher:Lkotlinx/coroutines/CoroutineDispatcher;

    .line 73
    invoke-static {}, Lkotlinx/coroutines/Dispatchers;->getIO()Lkotlinx/coroutines/CoroutineDispatcher;

    move-result-object v1

    invoke-static {v1, v4, v2, v4, v2}, Lkotlinx/coroutines/CoroutineDispatcher;->limitedParallelism$default(Lkotlinx/coroutines/CoroutineDispatcher;ILjava/lang/String;ILjava/lang/Object;)Lkotlinx/coroutines/CoroutineDispatcher;

    move-result-object v1

    sput-object v1, Lcom/exteragram/messenger/icons/IconManager;->decodeDispatcher:Lkotlinx/coroutines/CoroutineDispatcher;

    .line 74
    invoke-static {}, Lkotlinx/coroutines/Dispatchers;->getIO()Lkotlinx/coroutines/CoroutineDispatcher;

    move-result-object v1

    invoke-static {v1, v3, v2, v4, v2}, Lkotlinx/coroutines/CoroutineDispatcher;->limitedParallelism$default(Lkotlinx/coroutines/CoroutineDispatcher;ILjava/lang/String;ILjava/lang/Object;)Lkotlinx/coroutines/CoroutineDispatcher;

    move-result-object v1

    sput-object v1, Lcom/exteragram/messenger/icons/IconManager;->prewarmDispatcher:Lkotlinx/coroutines/CoroutineDispatcher;

    .line 107
    const-string/jumbo v106, "ton_icon"

    const-string v107, "diamond"

    const-string/jumbo v4, "system"

    const-string/jumbo v5, "smiles_popup"

    const-string v6, "camera_btn"

    const-string v7, "cancel_big"

    const-string v8, "chats_archive_box"

    const-string v9, "chats_archive_arrow"

    const-string v10, "chats_archive_muted"

    const-string v11, "chats_archive_pin"

    const-string v12, "chats_widget_preview"

    const-string v13, "circle_big"

    const-string v14, "contacts_widget_preview"

    const-string v15, "etg_splash"

    const-string v16, "ev_minus"

    const-string v17, "ev_plus"

    const-string v18, "filled_chatlink_large"

    const-string v19, "field_carret_empty"

    const-string v20, "dino_pic"

    const-string v21, "circle"

    const-string/jumbo v22, "widgets_light_badgebg"

    const-string/jumbo v23, "greydivider"

    const-string/jumbo v24, "greydivider_bottom"

    const-string/jumbo v25, "greydivider_top"

    const-string/jumbo v26, "groups_limit1"

    const-string/jumbo v27, "ic_ab_new"

    const-string/jumbo v28, "ic_foreground"

    const-string/jumbo v29, "ic_foreground_monet"

    const-string/jumbo v30, "ic_foreground_solid"

    const-string/jumbo v31, "ic_player"

    const-string/jumbo v32, "ic_reply_icon"

    const-string/jumbo v33, "icon_background_clip"

    const-string/jumbo v34, "icon_background_clip_round"

    const-string/jumbo v35, "icon_plane"

    const-string/jumbo v36, "icplaceholder"

    const-string/jumbo v37, "large_ads_info"

    const-string/jumbo v38, "large_away"

    const-string/jumbo v39, "large_greeting"

    const-string/jumbo v40, "large_log_actions"

    const-string/jumbo v41, "large_monetize"

    const-string/jumbo v42, "large_quickreplies"

    const-string/jumbo v43, "list_selector_ex"

    const-string/jumbo v44, "livepin"

    const-string/jumbo v45, "load_big"

    const-string/jumbo v46, "location_empty"

    const-string/jumbo v47, "login_arrow1"

    const-string/jumbo v48, "login_phone1"

    const-string/jumbo v49, "logo_middle"

    const-string/jumbo v50, "map_pin"

    const-string/jumbo v51, "map_pin2"

    const-string/jumbo v52, "map_pin3"

    const-string/jumbo v53, "map_pin_circle"

    const-string/jumbo v54, "map_pin_cone2"

    const-string/jumbo v55, "map_pin_photo"

    const-string/jumbo v56, "msg_media_gallery"

    const-string/jumbo v57, "music_empty"

    const-string/jumbo v58, "no_passport"

    const-string/jumbo v59, "no_password"

    const-string/jumbo v60, "nophotos"

    const-string/jumbo v61, "nophotos3"

    const-string/jumbo v62, "paint_elliptical_brush"

    const-string/jumbo v63, "paint_neon_brush"

    const-string/jumbo v64, "paint_radial_brush"

    const-string/jumbo v65, "phone_activate"

    const-string/jumbo v66, "photo_placeholder_in"

    const-string/jumbo v67, "photo_tooltip2"

    const-string/jumbo v68, "photoview_placeholder"

    const-string/jumbo v69, "screencast_big"

    const-string/jumbo v70, "scrollbar_vertical_thumb"

    const-string/jumbo v71, "scrollbar_vertical_thumb_inset"

    const-string/jumbo v72, "newmsg_divider"

    const-string/jumbo v73, "ic_launcher_dr"

    const-string/jumbo v74, "smiles_info"

    const-string/jumbo v75, "sms_bubble"

    const-string/jumbo v76, "sms_devices"

    const-string/jumbo v77, "stats_tooltip"

    const-string/jumbo v78, "sticker"

    const-string/jumbo v79, "story_camera"

    const-string/jumbo v80, "theme_preview_image"

    const-string/jumbo v81, "transparent"

    const-string/jumbo v82, "venue_tooltip"

    const-string/jumbo v83, "videopreview"

    const-string v84, "bluecounter"

    const-string/jumbo v85, "photobadge"

    const-string/jumbo v86, "photos_rounded"

    const-string v87, "calendar_date"

    const-string/jumbo v88, "menu_copy"

    const-string/jumbo v89, "redcircle"

    const-string/jumbo v90, "tooltip_arrow"

    const-string/jumbo v91, "tooltip_arrow_up"

    const-string/jumbo v92, "newyear"

    const-string v93, "cards_chat"

    const-string v94, "community_cards"

    const-string/jumbo v95, "ic_call_notification_answer"

    const-string/jumbo v96, "ic_call_notification_decline"

    const-string/jumbo v97, "shortcut_compose"

    const-string/jumbo v98, "shortcut_user"

    const-string/jumbo v99, "slide_dot_big"

    const-string/jumbo v100, "slide_dot_small"

    const-string v101, "cocoon_logo"

    const-string v102, "cocoon_text"

    const-string/jumbo v103, "telegram_logo"

    const-string/jumbo v104, "telegram_logo_2"

    const-string/jumbo v105, "mastercard_icon"

    filled-new-array/range {v4 .. v107}, [Ljava/lang/String;

    move-result-object v1

    .line 76
    invoke-static {v1}, Lkotlin/collections/SetsKt;->setOf([Ljava/lang/Object;)Ljava/util/Set;

    move-result-object v1

    sput-object v1, Lcom/exteragram/messenger/icons/IconManager;->blacklistedIcons:Ljava/util/Set;

    .line 205
    new-instance v1, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v1}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    sput-object v1, Lcom/exteragram/messenger/icons/IconManager;->systemIcons:Ljava/util/concurrent/ConcurrentHashMap;

    .line 216
    invoke-static {}, Ljava/lang/Runtime;->getRuntime()Ljava/lang/Runtime;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Runtime;->maxMemory()J

    move-result-wide v4

    const-wide/16 v6, 0x400

    div-long/2addr v4, v6

    long-to-int v1, v4

    sput v1, Lcom/exteragram/messenger/icons/IconManager;->maxMemory:I

    .line 217
    div-int/lit8 v1, v1, 0x8

    const/16 v4, 0x400

    invoke-static {v4, v1}, Ljava/lang/Math;->max(II)I

    move-result v1

    sput v1, Lcom/exteragram/messenger/icons/IconManager;->cacheSize:I

    .line 253
    new-instance v4, Lcom/exteragram/messenger/icons/IconManager$ResolvedIndex;

    const/16 v5, 0x800

    invoke-direct {v4, v5}, Lcom/exteragram/messenger/icons/IconManager$ResolvedIndex;-><init>(I)V

    sput-object v4, Lcom/exteragram/messenger/icons/IconManager;->resolvedCache:Lcom/exteragram/messenger/icons/IconManager$ResolvedIndex;

    .line 255
    new-instance v4, Lcom/exteragram/messenger/icons/IconManager$sourceCache$1;

    invoke-direct {v4, v1}, Lcom/exteragram/messenger/icons/IconManager$sourceCache$1;-><init>(I)V

    sput-object v4, Lcom/exteragram/messenger/icons/IconManager;->sourceCache:Lcom/exteragram/messenger/icons/IconManager$sourceCache$1;

    .line 261
    new-instance v1, Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-direct {v1}, Ljava/util/concurrent/CopyOnWriteArrayList;-><init>()V

    sput-object v1, Lcom/exteragram/messenger/icons/IconManager;->activePacks:Ljava/util/concurrent/CopyOnWriteArrayList;

    .line 262
    new-instance v1, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v1}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    sput-object v1, Lcom/exteragram/messenger/icons/IconManager;->iconOwnerMap:Ljava/util/concurrent/ConcurrentHashMap;

    .line 267
    new-instance v1, Ljava/util/concurrent/CountDownLatch;

    invoke-direct {v1, v3}, Ljava/util/concurrent/CountDownLatch;-><init>(I)V

    sput-object v1, Lcom/exteragram/messenger/icons/IconManager;->initialPacksLoaded:Ljava/util/concurrent/CountDownLatch;

    const/4 v1, 0x0

    .line 314
    invoke-static {v0, v1, v3, v2}, Lcom/exteragram/messenger/icons/IconManager;->initialize$default(Lcom/exteragram/messenger/icons/IconManager;ZILjava/lang/Object;)V

    .line 1011
    new-instance v0, Landroid/util/SparseArray;

    invoke-direct {v0}, Landroid/util/SparseArray;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/icons/IconManager;->resultCallbacks:Landroid/util/SparseArray;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 70
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static final synthetic access$getActivePacks$p()Ljava/util/concurrent/CopyOnWriteArrayList;
    .locals 1

    .line 70
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->activePacks:Ljava/util/concurrent/CopyOnWriteArrayList;

    return-object v0
.end method

.method public static final synthetic access$getIconOwnerMap$p()Ljava/util/concurrent/ConcurrentHashMap;
    .locals 1

    .line 70
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->iconOwnerMap:Ljava/util/concurrent/ConcurrentHashMap;

    return-object v0
.end method

.method public static final synthetic access$getInitialPacksLoaded$p()Ljava/util/concurrent/CountDownLatch;
    .locals 1

    .line 70
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->initialPacksLoaded:Ljava/util/concurrent/CountDownLatch;

    return-object v0
.end method

.method public static final synthetic access$getInitializationGeneration$p()J
    .locals 2

    .line 70
    sget-wide v0, Lcom/exteragram/messenger/icons/IconManager;->initializationGeneration:J

    return-wide v0
.end method

.method public static final synthetic access$getInitializationTimedOut$p()Z
    .locals 1

    .line 70
    sget-boolean v0, Lcom/exteragram/messenger/icons/IconManager;->initializationTimedOut:Z

    return v0
.end method

.method public static final synthetic access$getPackIconBitmap(Lcom/exteragram/messenger/icons/IconManager;Lcom/exteragram/messenger/icons/IconPack;IILandroid/content/res/Resources$Theme;Ljava/lang/String;Z)Landroid/graphics/Bitmap;
    .locals 0

    .line 70
    invoke-direct/range {p0 .. p6}, Lcom/exteragram/messenger/icons/IconManager;->getPackIconBitmap(Lcom/exteragram/messenger/icons/IconPack;IILandroid/content/res/Resources$Theme;Ljava/lang/String;Z)Landroid/graphics/Bitmap;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$getResolvedCache$p()Lcom/exteragram/messenger/icons/IconManager$ResolvedIndex;
    .locals 1

    .line 70
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->resolvedCache:Lcom/exteragram/messenger/icons/IconManager$ResolvedIndex;

    return-object v0
.end method

.method public static final synthetic access$getScope$p()Lkotlinx/coroutines/CoroutineScope;
    .locals 1

    .line 70
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->scope:Lkotlinx/coroutines/CoroutineScope;

    return-object v0
.end method

.method public static final synthetic access$getSourceCache$p()Lcom/exteragram/messenger/icons/IconManager$sourceCache$1;
    .locals 1

    .line 70
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->sourceCache:Lcom/exteragram/messenger/icons/IconManager$sourceCache$1;

    return-object v0
.end method

.method public static final synthetic access$invalidateIconCaches(Lcom/exteragram/messenger/icons/IconManager;Ljava/lang/String;I)V
    .locals 0

    .line 70
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/icons/IconManager;->invalidateIconCaches(Ljava/lang/String;I)V

    return-void
.end method

.method public static final synthetic access$launchPrewarm(Lcom/exteragram/messenger/icons/IconManager;J)V
    .locals 0

    .line 70
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/icons/IconManager;->launchPrewarm(J)V

    return-void
.end method

.method public static final synthetic access$publishBitmap(Lcom/exteragram/messenger/icons/IconManager;JLjava/lang/String;Lcom/exteragram/messenger/icons/IconPack;IILandroid/graphics/Bitmap;)Z
    .locals 0

    .line 70
    invoke-direct/range {p0 .. p7}, Lcom/exteragram/messenger/icons/IconManager;->publishBitmap(JLjava/lang/String;Lcom/exteragram/messenger/icons/IconPack;IILandroid/graphics/Bitmap;)Z

    move-result p0

    return p0
.end method

.method public static final synthetic access$rebuildOwnerMap(Lcom/exteragram/messenger/icons/IconManager;)V
    .locals 0

    .line 70
    invoke-direct {p0}, Lcom/exteragram/messenger/icons/IconManager;->rebuildOwnerMap()V

    return-void
.end method

.method public static final synthetic access$resolvePackIconFile(Lcom/exteragram/messenger/icons/IconManager;Lcom/exteragram/messenger/icons/IconPack;Ljava/lang/String;)Ljava/io/File;
    .locals 0

    .line 70
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/icons/IconManager;->resolvePackIconFile(Lcom/exteragram/messenger/icons/IconPack;Ljava/lang/String;)Ljava/io/File;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$setSystemNames$p(Lcom/exteragram/messenger/icons/IconManager$IntStringMap;)V
    .locals 0

    .line 70
    sput-object p0, Lcom/exteragram/messenger/icons/IconManager;->systemNames:Lcom/exteragram/messenger/icons/IconManager$IntStringMap;

    return-void
.end method

.method public static final synthetic access$showIconPackError(Lcom/exteragram/messenger/icons/IconManager;Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/icons/IconPackStorageError;)V
    .locals 0

    .line 70
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/icons/IconManager;->showIconPackError(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/icons/IconPackStorageError;)V

    return-void
.end method

.method public static final synthetic access$syncInstalledCustomPacks(Lcom/exteragram/messenger/icons/IconManager;Ljava/util/List;)Z
    .locals 0

    .line 70
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/icons/IconManager;->syncInstalledCustomPacks(Ljava/util/List;)Z

    move-result p0

    return p0
.end method

.method public static final synthetic access$updateActivePacks(Lcom/exteragram/messenger/icons/IconManager;JLjava/util/List;)Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;
    .locals 0

    .line 70
    invoke-direct {p0, p1, p2, p3}, Lcom/exteragram/messenger/icons/IconManager;->updateActivePacks(JLjava/util/List;)Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;

    move-result-object p0

    return-object p0
.end method

.method private final cachedPackIconBitmap(Lcom/exteragram/messenger/icons/IconPack;II)Landroid/graphics/Bitmap;
    .locals 3

    .line 457
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->systemNames:Lcom/exteragram/messenger/icons/IconManager$IntStringMap;

    const/4 v1, 0x0

    if-eqz v0, :cond_2

    invoke-virtual {v0, p2}, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->get(I)Ljava/lang/String;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 458
    :cond_0
    invoke-virtual {p1}, Lcom/exteragram/messenger/icons/IconPack;->getIcons()Ljava/util/Map;

    move-result-object v2

    invoke-interface {v2, v0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    if-nez v0, :cond_1

    return-object v1

    .line 459
    :cond_1
    sget-object v1, Lcom/exteragram/messenger/icons/IconManager;->sourceCache:Lcom/exteragram/messenger/icons/IconManager$sourceCache$1;

    invoke-direct {p0, p1, v0, p2, p3}, Lcom/exteragram/messenger/icons/IconManager;->sourceCacheKey(Lcom/exteragram/messenger/icons/IconPack;Ljava/lang/String;II)Lcom/exteragram/messenger/icons/IconManager$SourceCacheKey;

    move-result-object p0

    invoke-virtual {v1, p0}, Landroidx/collection/LruCache;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/graphics/Bitmap;

    return-object p0

    :cond_2
    :goto_0
    return-object v1
.end method

.method public static final getNotificationIcon()Landroidx/core/graphics/drawable/IconCompat;
    .locals 10
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 714
    :try_start_0
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->loadConfig()V

    .line 716
    const-string/jumbo v5, "notification"

    .line 717
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksLayout()Ljava/util/ArrayList;

    move-result-object v0

    .line 718
    invoke-static {v0}, Lkotlin/collections/CollectionsKt;->asSequence(Ljava/lang/Iterable;)Lkotlin/sequences/Sequence;

    move-result-object v0

    new-instance v1, Lcom/exteragram/messenger/icons/IconManager$$ExternalSyntheticLambda0;

    invoke-direct {v1}, Lcom/exteragram/messenger/icons/IconManager$$ExternalSyntheticLambda0;-><init>()V

    .line 719
    invoke-static {v0, v1}, Lkotlin/sequences/SequencesKt;->filterNot(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)Lkotlin/sequences/Sequence;

    move-result-object v0

    new-instance v1, Lcom/exteragram/messenger/icons/IconManager$$ExternalSyntheticLambda1;

    invoke-direct {v1}, Lcom/exteragram/messenger/icons/IconManager$$ExternalSyntheticLambda1;-><init>()V

    .line 720
    invoke-static {v0, v1}, Lkotlin/sequences/SequencesKt;->mapNotNull(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)Lkotlin/sequences/Sequence;

    move-result-object v0

    .line 184
    invoke-interface {v0}, Lkotlin/sequences/Sequence;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    const/4 v9, 0x0

    if-eqz v1, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    move-object v2, v1

    check-cast v2, Lcom/exteragram/messenger/icons/IconPack;

    .line 721
    invoke-virtual {v2}, Lcom/exteragram/messenger/icons/IconPack;->getIcons()Ljava/util/Map;

    move-result-object v2

    invoke-interface {v2, v5}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_0

    goto :goto_0

    :cond_1
    move-object v1, v9

    :goto_0
    check-cast v1, Lcom/exteragram/messenger/icons/IconPack;

    if-eqz v1, :cond_4

    .line 723
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v2, 0x1f

    if-lt v0, v2, :cond_3

    .line 724
    sget-object v0, Lcom/exteragram/messenger/icons/IconPackProvider;->Companion:Lcom/exteragram/messenger/icons/IconPackProvider$Companion;

    invoke-virtual {v1}, Lcom/exteragram/messenger/icons/IconPack;->getId()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1, v5}, Lcom/exteragram/messenger/icons/IconPackProvider$Companion;->getIconUri(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v1
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1

    if-eqz v1, :cond_2

    .line 727
    :try_start_1
    sget-object v0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    .line 728
    const-string v2, "com.android.systemui"

    const/4 v3, 0x1

    .line 727
    invoke-virtual {v0, v2, v1, v3}, Landroid/content/Context;->grantUriPermission(Ljava/lang/String;Landroid/net/Uri;I)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_1

    :catch_0
    move-exception v0

    .line 733
    :try_start_2
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 735
    :goto_1
    invoke-static {v1}, Landroidx/core/graphics/drawable/IconCompat;->createWithContentUri(Landroid/net/Uri;)Landroidx/core/graphics/drawable/IconCompat;

    move-result-object v0

    goto :goto_2

    :cond_2
    move-object v0, v9

    goto :goto_2

    .line 738
    :cond_3
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->INSTANCE:Lcom/exteragram/messenger/icons/IconManager;

    sget v2, Lorg/telegram/messenger/R$drawable;->notification:I

    sget-object v3, Lorg/telegram/messenger/AndroidUtilities;->displayMetrics:Landroid/util/DisplayMetrics;

    iget v3, v3, Landroid/util/DisplayMetrics;->densityDpi:I

    const/16 v7, 0x20

    const/4 v8, 0x0

    const/4 v4, 0x0

    const/4 v6, 0x0

    invoke-static/range {v0 .. v8}, Lcom/exteragram/messenger/icons/IconManager;->getPackIconBitmap$default(Lcom/exteragram/messenger/icons/IconManager;Lcom/exteragram/messenger/icons/IconPack;IILandroid/content/res/Resources$Theme;Ljava/lang/String;ZILjava/lang/Object;)Landroid/graphics/Bitmap;

    move-result-object v0

    if-eqz v0, :cond_2

    .line 739
    invoke-static {v0}, Landroidx/core/graphics/drawable/IconCompat;->createWithBitmap(Landroid/graphics/Bitmap;)Landroidx/core/graphics/drawable/IconCompat;

    move-result-object v0

    :goto_2
    if-eqz v0, :cond_4

    return-object v0

    .line 744
    :cond_4
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksLayout()Ljava/util/ArrayList;

    move-result-object v0

    .line 745
    invoke-static {v0}, Lkotlin/collections/CollectionsKt;->asSequence(Ljava/lang/Iterable;)Lkotlin/sequences/Sequence;

    move-result-object v0

    new-instance v1, Lcom/exteragram/messenger/icons/IconManager$$ExternalSyntheticLambda2;

    invoke-direct {v1}, Lcom/exteragram/messenger/icons/IconManager$$ExternalSyntheticLambda2;-><init>()V

    .line 746
    invoke-static {v0, v1}, Lkotlin/sequences/SequencesKt;->filter(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)Lkotlin/sequences/Sequence;

    move-result-object v0

    new-instance v1, Lcom/exteragram/messenger/icons/IconManager$$ExternalSyntheticLambda3;

    invoke-direct {v1}, Lcom/exteragram/messenger/icons/IconManager$$ExternalSyntheticLambda3;-><init>()V

    .line 747
    invoke-static {v0, v1}, Lkotlin/sequences/SequencesKt;->mapNotNull(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)Lkotlin/sequences/Sequence;

    move-result-object v0

    new-instance v1, Lcom/exteragram/messenger/icons/IconManager$$ExternalSyntheticLambda4;

    invoke-direct {v1}, Lcom/exteragram/messenger/icons/IconManager$$ExternalSyntheticLambda4;-><init>()V

    .line 748
    invoke-static {v0, v1}, Lkotlin/sequences/SequencesKt;->map(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)Lkotlin/sequences/Sequence;

    move-result-object v0

    .line 184
    invoke-interface {v0}, Lkotlin/sequences/Sequence;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_5
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_6

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    move-object v2, v1

    check-cast v2, Ljava/lang/Number;

    invoke-virtual {v2}, Ljava/lang/Number;->intValue()I

    move-result v2

    const/4 v3, -0x1

    if-eq v2, v3, :cond_5

    move-object v9, v1

    .line 749
    :cond_6
    check-cast v9, Ljava/lang/Integer;

    if-eqz v9, :cond_7

    .line 744
    invoke-virtual {v9}, Ljava/lang/Integer;->intValue()I

    move-result v0

    goto :goto_3

    .line 750
    :cond_7
    sget v0, Lorg/telegram/messenger/R$drawable;->notification:I

    .line 751
    :goto_3
    sget-object v1, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-static {v1, v0}, Landroidx/core/graphics/drawable/IconCompat;->createWithResource(Landroid/content/Context;I)Landroidx/core/graphics/drawable/IconCompat;

    move-result-object v0
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_1

    goto :goto_4

    :catch_1
    move-exception v0

    .line 753
    const-string v1, "Failed to resolve notification icon"

    invoke-static {v1, v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 754
    sget-object v0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    sget v1, Lorg/telegram/messenger/R$drawable;->notification:I

    invoke-static {v0, v1}, Landroidx/core/graphics/drawable/IconCompat;->createWithResource(Landroid/content/Context;I)Landroidx/core/graphics/drawable/IconCompat;

    move-result-object v0

    :goto_4
    return-object v0
.end method

.method public static final getNotificationSystemIcon()Landroid/graphics/drawable/Icon;
    .locals 2
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 760
    invoke-static {}, Lcom/exteragram/messenger/icons/IconManager;->getNotificationIcon()Landroidx/core/graphics/drawable/IconCompat;

    move-result-object v0

    sget-object v1, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-virtual {v0, v1}, Landroidx/core/graphics/drawable/IconCompat;->toIcon(Landroid/content/Context;)Landroid/graphics/drawable/Icon;

    move-result-object v0

    return-object v0
.end method

.method private final getPackIconBitmap(Lcom/exteragram/messenger/icons/IconPack;IILandroid/content/res/Resources$Theme;Ljava/lang/String;Z)Landroid/graphics/Bitmap;
    .locals 4

    const/4 v0, 0x0

    if-nez p5, :cond_1

    .line 502
    sget-object p5, Lcom/exteragram/messenger/icons/IconManager;->systemNames:Lcom/exteragram/messenger/icons/IconManager$IntStringMap;

    if-eqz p5, :cond_0

    invoke-virtual {p5, p2}, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->get(I)Ljava/lang/String;

    move-result-object p5

    goto :goto_0

    :cond_0
    move-object p5, v0

    :goto_0
    if-nez p5, :cond_1

    .line 503
    :try_start_0
    sget-object p5, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-virtual {p5}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p5

    invoke-virtual {p5, p2}, Landroid/content/res/Resources;->getResourceEntryName(I)Ljava/lang/String;

    move-result-object p5
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_1

    :catch_0
    return-object v0

    .line 508
    :cond_1
    :goto_1
    invoke-virtual {p1}, Lcom/exteragram/messenger/icons/IconPack;->getIcons()Ljava/util/Map;

    move-result-object v1

    invoke-interface {v1, p5}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p5

    check-cast p5, Ljava/lang/String;

    if-nez p5, :cond_2

    return-object v0

    .line 509
    :cond_2
    invoke-direct {p0, p1, p5, p2, p3}, Lcom/exteragram/messenger/icons/IconManager;->sourceCacheKey(Lcom/exteragram/messenger/icons/IconPack;Ljava/lang/String;II)Lcom/exteragram/messenger/icons/IconManager$SourceCacheKey;

    move-result-object v1

    .line 510
    sget-object v2, Lcom/exteragram/messenger/icons/IconManager;->sourceCache:Lcom/exteragram/messenger/icons/IconManager$sourceCache$1;

    invoke-virtual {v2, v1}, Landroidx/collection/LruCache;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Landroid/graphics/Bitmap;

    if-eqz v3, :cond_3

    return-object v3

    .line 512
    :cond_3
    invoke-direct {p0, p1, p5}, Lcom/exteragram/messenger/icons/IconManager;->resolvePackIconFile(Lcom/exteragram/messenger/icons/IconPack;Ljava/lang/String;)Ljava/io/File;

    move-result-object p1

    if-nez p1, :cond_4

    return-object v0

    .line 514
    :cond_4
    invoke-virtual {p1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1, p2, p3, p4}, Lcom/exteragram/messenger/icons/IconManager;->createBitmapFromFile(Ljava/lang/String;IILandroid/content/res/Resources$Theme;)Landroid/graphics/Bitmap;

    move-result-object p0

    if-eqz p0, :cond_5

    if-eqz p6, :cond_5

    .line 516
    invoke-virtual {v2, v1, p0}, Landroidx/collection/LruCache;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_5
    return-object p0
.end method

.method public static synthetic getPackIconBitmap$default(Lcom/exteragram/messenger/icons/IconManager;Lcom/exteragram/messenger/icons/IconPack;IILandroid/content/res/Resources$Theme;Ljava/lang/String;ZILjava/lang/Object;)Landroid/graphics/Bitmap;
    .locals 7

    and-int/lit8 p8, p7, 0x10

    if-eqz p8, :cond_0

    const/4 p5, 0x0

    :cond_0
    move-object v5, p5

    and-int/lit8 p5, p7, 0x20

    if-eqz p5, :cond_1

    const/4 p6, 0x1

    :cond_1
    move-object v0, p0

    move-object v1, p1

    move v2, p2

    move v3, p3

    move-object v4, p4

    move v6, p6

    .line 494
    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/icons/IconManager;->getPackIconBitmap(Lcom/exteragram/messenger/icons/IconPack;IILandroid/content/res/Resources$Theme;Ljava/lang/String;Z)Landroid/graphics/Bitmap;

    move-result-object p0

    return-object p0
.end method

.method private final iconPackErrorText(Lcom/exteragram/messenger/icons/IconPackStorageError;)Ljava/lang/String;
    .locals 0

    .line 951
    sget-object p0, Lcom/exteragram/messenger/icons/IconManager$WhenMappings;->$EnumSwitchMapping$0:[I

    invoke-virtual {p1}, Ljava/lang/Enum;->ordinal()I

    move-result p1

    aget p0, p0, p1

    packed-switch p0, :pswitch_data_0

    invoke-static {}, Lkotlin/LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;->m()V

    const/4 p0, 0x0

    return-object p0

    .line 961
    :pswitch_0
    sget p0, Lorg/telegram/messenger/R$string;->UnknownError:I

    goto :goto_0

    .line 960
    :pswitch_1
    sget p0, Lorg/telegram/messenger/R$string;->IconPackErrorStorage:I

    goto :goto_0

    .line 959
    :pswitch_2
    sget p0, Lorg/telegram/messenger/R$string;->IconPackErrorCompressionRatioTooHigh:I

    goto :goto_0

    .line 958
    :pswitch_3
    sget p0, Lorg/telegram/messenger/R$string;->IconPackErrorFileTooLarge:I

    goto :goto_0

    .line 957
    :pswitch_4
    sget p0, Lorg/telegram/messenger/R$string;->IconPackErrorArchiveTooLarge:I

    goto :goto_0

    .line 956
    :pswitch_5
    sget p0, Lorg/telegram/messenger/R$string;->IconPackErrorTooManyFiles:I

    goto :goto_0

    .line 955
    :pswitch_6
    sget p0, Lorg/telegram/messenger/R$string;->IconPackErrorInvalidMetadata:I

    goto :goto_0

    .line 954
    :pswitch_7
    sget p0, Lorg/telegram/messenger/R$string;->IconPackErrorMetadataTooLarge:I

    goto :goto_0

    .line 953
    :pswitch_8
    sget p0, Lorg/telegram/messenger/R$string;->IconPackErrorMissingMetadata:I

    goto :goto_0

    .line 952
    :pswitch_9
    sget p0, Lorg/telegram/messenger/R$string;->IconPackErrorInvalidArchive:I

    .line 950
    :goto_0
    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public static synthetic initialize$default(Lcom/exteragram/messenger/icons/IconManager;ZILjava/lang/Object;)V
    .locals 0

    and-int/lit8 p2, p2, 0x1

    if-eqz p2, :cond_0

    const/4 p1, 0x0

    .line 762
    :cond_0
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/icons/IconManager;->initialize(Z)V

    return-void
.end method

.method private final invalidateIconCaches(Ljava/lang/String;I)V
    .locals 4

    .line 283
    sget-object p0, Lcom/exteragram/messenger/icons/IconManager;->resolvedCache:Lcom/exteragram/messenger/icons/IconManager$ResolvedIndex;

    invoke-virtual {p0, p2}, Lcom/exteragram/messenger/icons/IconManager$ResolvedIndex;->removeByResId(I)V

    .line 284
    sget-object p0, Lcom/exteragram/messenger/icons/IconManager;->sourceCache:Lcom/exteragram/messenger/icons/IconManager$sourceCache$1;

    invoke-virtual {p0}, Landroidx/collection/LruCache;->snapshot()Ljava/util/Map;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/Map;->keySet()Ljava/util/Set;

    move-result-object p0

    .line 777
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 873
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_0
    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    move-object v2, v1

    check-cast v2, Lcom/exteragram/messenger/icons/IconManager$SourceCacheKey;

    .line 285
    invoke-virtual {v2}, Lcom/exteragram/messenger/icons/IconManager$SourceCacheKey;->getPackId()Ljava/lang/String;

    move-result-object v3

    invoke-static {v3, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_0

    invoke-virtual {v2}, Lcom/exteragram/messenger/icons/IconManager$SourceCacheKey;->getResId()I

    move-result v2

    if-ne v2, p2, :cond_0

    .line 873
    invoke-interface {v0, v1}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 1915
    :cond_1
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result p0

    const/4 p1, 0x0

    :goto_1
    if-ge p1, p0, :cond_2

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    add-int/lit8 p1, p1, 0x1

    check-cast p2, Lcom/exteragram/messenger/icons/IconManager$SourceCacheKey;

    .line 286
    sget-object v1, Lcom/exteragram/messenger/icons/IconManager;->sourceCache:Lcom/exteragram/messenger/icons/IconManager$sourceCache$1;

    invoke-virtual {v1, p2}, Landroidx/collection/LruCache;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_1

    :cond_2
    return-void
.end method

.method private final declared-synchronized launchPrewarm(J)V
    .locals 6

    monitor-enter p0

    .line 398
    :try_start_0
    sget-wide v0, Lcom/exteragram/messenger/icons/IconManager;->initializationGeneration:J
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    cmp-long v0, p1, v0

    if-eqz v0, :cond_0

    monitor-exit p0

    return-void

    .line 399
    :cond_0
    :try_start_1
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->prewarmJob:Lkotlinx/coroutines/Job;

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    const/4 v2, 0x1

    invoke-static {v0, v1, v2, v1}, Lkotlinx/coroutines/Job;->cancel$default(Lkotlinx/coroutines/Job;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V

    goto :goto_0

    :catchall_0
    move-exception v0

    move-object p1, v0

    goto :goto_1

    .line 400
    :cond_1
    :goto_0
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->scope:Lkotlinx/coroutines/CoroutineScope;

    move-object v2, v1

    sget-object v1, Lcom/exteragram/messenger/icons/IconManager;->prewarmDispatcher:Lkotlinx/coroutines/CoroutineDispatcher;

    new-instance v3, Lcom/exteragram/messenger/icons/IconManager$launchPrewarm$1;

    invoke-direct {v3, p1, p2, v2}, Lcom/exteragram/messenger/icons/IconManager$launchPrewarm$1;-><init>(JLkotlin/coroutines/Continuation;)V

    const/4 v4, 0x2

    const/4 v5, 0x0

    const/4 v2, 0x0

    invoke-static/range {v0 .. v5}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    move-result-object p1

    sput-object p1, Lcom/exteragram/messenger/icons/IconManager;->prewarmJob:Lkotlinx/coroutines/Job;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 414
    monitor-exit p0

    return-void

    :goto_1
    :try_start_2
    monitor-exit p0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw p1
.end method

.method private final declared-synchronized publishBitmap(JLjava/lang/String;Lcom/exteragram/messenger/icons/IconPack;IILandroid/graphics/Bitmap;)Z
    .locals 2

    monitor-enter p0

    .line 298
    :try_start_0
    sget-wide v0, Lcom/exteragram/messenger/icons/IconManager;->initializationGeneration:J

    cmp-long p1, p1, v0

    const/4 p2, 0x0

    if-nez p1, :cond_2

    sget-object p1, Lcom/exteragram/messenger/icons/IconManager;->iconOwnerMap:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p1, p3}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    invoke-static {p1, p4}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_0

    goto :goto_0

    .line 299
    :cond_0
    invoke-virtual {p4}, Lcom/exteragram/messenger/icons/IconPack;->getIcons()Ljava/util/Map;

    move-result-object p1

    invoke-interface {p1, p3}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-nez p1, :cond_1

    monitor-exit p0

    return p2

    .line 300
    :cond_1
    :try_start_1
    invoke-direct {p0, p4, p1, p5, p6}, Lcom/exteragram/messenger/icons/IconManager;->sourceCacheKey(Lcom/exteragram/messenger/icons/IconPack;Ljava/lang/String;II)Lcom/exteragram/messenger/icons/IconManager$SourceCacheKey;

    move-result-object p1

    .line 301
    sget-object p2, Lcom/exteragram/messenger/icons/IconManager;->sourceCache:Lcom/exteragram/messenger/icons/IconManager$sourceCache$1;

    invoke-virtual {p2, p1, p7}, Landroidx/collection/LruCache;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 302
    sget-object p2, Lcom/exteragram/messenger/icons/IconManager;->resolvedCache:Lcom/exteragram/messenger/icons/IconManager$ResolvedIndex;

    invoke-direct {p0, p5, p6}, Lcom/exteragram/messenger/icons/IconManager;->resolvedCacheKey(II)J

    move-result-wide p3

    invoke-virtual {p2, p3, p4, p1}, Lcom/exteragram/messenger/icons/IconManager$ResolvedIndex;->put(JLcom/exteragram/messenger/icons/IconManager$SourceCacheKey;)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 303
    monitor-exit p0

    const/4 p0, 0x1

    return p0

    :catchall_0
    move-exception p1

    goto :goto_1

    .line 298
    :cond_2
    :goto_0
    monitor-exit p0

    return p2

    :goto_1
    :try_start_2
    monitor-exit p0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw p1
.end method

.method private final rebuildBasePreinstalledMap()V
    .locals 8

    .line 350
    new-instance p0, Landroid/util/SparseIntArray;

    invoke-direct {p0}, Landroid/util/SparseIntArray;-><init>()V

    .line 351
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->activePacks:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-interface {v0}, Ljava/util/Collection;->size()I

    move-result v0

    const/4 v1, 0x0

    move v2, v1

    :goto_0
    if-ge v2, v0, :cond_3

    .line 352
    sget-object v3, Lcom/exteragram/messenger/icons/IconManager;->activePacks:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v3, v2}, Ljava/util/concurrent/CopyOnWriteArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/exteragram/messenger/icons/IconPack;

    .line 353
    invoke-virtual {v3}, Lcom/exteragram/messenger/icons/IconPack;->getPreinstalledMap()Landroid/util/SparseIntArray;

    move-result-object v4

    if-nez v4, :cond_0

    goto :goto_2

    .line 354
    :cond_0
    invoke-virtual {v3}, Lcom/exteragram/messenger/icons/IconPack;->isBase()Z

    move-result v3

    if-eqz v3, :cond_2

    .line 25
    invoke-virtual {v4}, Landroid/util/SparseIntArray;->size()I

    move-result v3

    move v5, v1

    :goto_1
    if-ge v5, v3, :cond_2

    .line 356
    invoke-virtual {v4, v5}, Landroid/util/SparseIntArray;->keyAt(I)I

    move-result v6

    .line 357
    invoke-virtual {p0, v6}, Landroid/util/SparseIntArray;->indexOfKey(I)I

    move-result v7

    if-gez v7, :cond_1

    .line 358
    invoke-virtual {v4, v5}, Landroid/util/SparseIntArray;->valueAt(I)I

    move-result v7

    invoke-virtual {p0, v6, v7}, Landroid/util/SparseIntArray;->put(II)V

    :cond_1
    add-int/lit8 v5, v5, 0x1

    goto :goto_1

    :cond_2
    :goto_2
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 56
    :cond_3
    invoke-virtual {p0}, Landroid/util/SparseIntArray;->size()I

    move-result v0

    if-nez v0, :cond_4

    const/4 p0, 0x0

    .line 362
    :cond_4
    sput-object p0, Lcom/exteragram/messenger/icons/IconManager;->basePreinstalledMap:Landroid/util/SparseIntArray;

    return-void
.end method

.method private final rebuildOwnerMap()V
    .locals 5

    .line 338
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->iconOwnerMap:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0}, Ljava/util/concurrent/ConcurrentHashMap;->clear()V

    .line 339
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->activePacks:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-interface {v0}, Ljava/util/Collection;->size()I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    if-ltz v0, :cond_2

    :goto_0
    add-int/lit8 v1, v0, -0x1

    .line 340
    sget-object v2, Lcom/exteragram/messenger/icons/IconManager;->activePacks:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v2, v0}, Ljava/util/concurrent/CopyOnWriteArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/exteragram/messenger/icons/IconPack;

    .line 341
    invoke-virtual {v0}, Lcom/exteragram/messenger/icons/IconPack;->isBase()Z

    move-result v2

    if-nez v2, :cond_0

    .line 342
    invoke-virtual {v0}, Lcom/exteragram/messenger/icons/IconPack;->getIcons()Ljava/util/Map;

    move-result-object v2

    invoke-interface {v2}, Ljava/util/Map;->keySet()Ljava/util/Set;

    move-result-object v2

    invoke-interface {v2}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_1
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_0

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    .line 343
    sget-object v4, Lcom/exteragram/messenger/icons/IconManager;->iconOwnerMap:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-interface {v4, v3, v0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_1

    :cond_0
    if-gez v1, :cond_1

    goto :goto_2

    :cond_1
    move v0, v1

    goto :goto_0

    .line 346
    :cond_2
    :goto_2
    invoke-direct {p0}, Lcom/exteragram/messenger/icons/IconManager;->rebuildBasePreinstalledMap()V

    return-void
.end method

.method private final resolvePackIconFile(Lcom/exteragram/messenger/icons/IconPack;Ljava/lang/String;)Ljava/io/File;
    .locals 2

    .line 387
    invoke-virtual {p1}, Lcom/exteragram/messenger/icons/IconPack;->getLocation()Ljava/io/File;

    move-result-object p0

    if-nez p0, :cond_0

    new-instance p0, Ljava/io/File;

    sget-object v0, Lcom/exteragram/messenger/icons/IconPackStorage;->INSTANCE:Lcom/exteragram/messenger/icons/IconPackStorage;

    invoke-virtual {v0}, Lcom/exteragram/messenger/icons/IconPackStorage;->getIconPacksDirectory()Ljava/io/File;

    move-result-object v0

    invoke-virtual {p1}, Lcom/exteragram/messenger/icons/IconPack;->getId()Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v0, v1}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 389
    :cond_0
    :try_start_0
    invoke-static {p0}, Lorg/simplifiles/SimpliFiles;->directory(Ljava/io/File;)Lorg/simplifiles/files/SimpliDirectory;

    move-result-object p0

    invoke-virtual {p0, p2}, Lorg/simplifiles/files/SimpliDirectory;->file(Ljava/lang/String;)Lorg/simplifiles/files/SimpliFile;

    move-result-object p0

    invoke-virtual {p0}, Lorg/simplifiles/files/SimpliFile;->getFile()Ljava/io/File;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception p0

    .line 391
    invoke-virtual {p1}, Lcom/exteragram/messenger/icons/IconPack;->getId()Ljava/lang/String;

    move-result-object p1

    new-instance p2, Ljava/lang/StringBuilder;

    const-string v0, "Failed to resolve icon file for pack "

    invoke-direct {p2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method private final resolvedCacheKey(II)J
    .locals 4

    int-to-long p0, p1

    const/16 v0, 0x20

    shl-long/2addr p0, v0

    int-to-long v0, p2

    const-wide v2, 0xffffffffL

    and-long/2addr v0, v2

    or-long/2addr p0, v0

    return-wide p0
.end method

.method private final showIconPackError(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/icons/IconPackStorageError;)V
    .locals 1

    .line 967
    invoke-static {p1}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p1

    sget v0, Lorg/telegram/messenger/R$raw;->error:I

    invoke-direct {p0, p2}, Lcom/exteragram/messenger/icons/IconManager;->iconPackErrorText(Lcom/exteragram/messenger/icons/IconPackStorageError;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p1, v0, p0}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void
.end method

.method public static synthetic showReplaceAlert$default(Lcom/exteragram/messenger/icons/IconManager;Landroid/content/Context;ILcom/exteragram/messenger/icons/IconPack;ILjava/lang/Object;)V
    .locals 0

    and-int/lit8 p4, p4, 0x4

    if-eqz p4, :cond_0

    const/4 p3, 0x0

    .line 1042
    :cond_0
    invoke-virtual {p0, p1, p2, p3}, Lcom/exteragram/messenger/icons/IconManager;->showReplaceAlert(Landroid/content/Context;ILcom/exteragram/messenger/icons/IconPack;)V

    return-void
.end method

.method private final sourceCacheKey(Lcom/exteragram/messenger/icons/IconPack;Ljava/lang/String;II)Lcom/exteragram/messenger/icons/IconManager$SourceCacheKey;
    .locals 8

    .line 279
    new-instance v0, Lcom/exteragram/messenger/icons/IconManager$SourceCacheKey;

    sget-wide v1, Lcom/exteragram/messenger/icons/IconManager;->initializationGeneration:J

    invoke-virtual {p1}, Lcom/exteragram/messenger/icons/IconPack;->getId()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {p1}, Lcom/exteragram/messenger/icons/IconPack;->getLocation()Ljava/io/File;

    move-result-object p0

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object p0

    :goto_0
    move-object v4, p0

    move-object v5, p2

    move v6, p3

    move v7, p4

    goto :goto_1

    :cond_0
    const/4 p0, 0x0

    goto :goto_0

    :goto_1
    invoke-direct/range {v0 .. v7}, Lcom/exteragram/messenger/icons/IconManager$SourceCacheKey;-><init>(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V

    return-object v0
.end method

.method private final declared-synchronized syncInstalledCustomPacks(Ljava/util/List;)Z
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/icons/IconPack;",
            ">;)Z"
        }
    .end annotation

    monitor-enter p0

    .line 326
    :try_start_0
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    const/4 v0, 0x0

    :cond_0
    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/icons/IconPack;

    .line 327
    invoke-virtual {v1}, Lcom/exteragram/messenger/icons/IconPack;->getId()Ljava/lang/String;

    move-result-object v1

    .line 328
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksLayout()Ljava/util/ArrayList;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_0

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksHidden()Ljava/util/ArrayList;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_0

    .line 329
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksHidden()Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    const/4 v0, 0x1

    goto :goto_0

    :catchall_0
    move-exception p1

    goto :goto_1

    :cond_1
    if-eqz v0, :cond_2

    .line 333
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->saveIconPacksLayout()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 334
    :cond_2
    monitor-exit p0

    return v0

    :goto_1
    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method private final declared-synchronized updateActivePacks(JLjava/util/List;)Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(J",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/icons/IconPack;",
            ">;)",
            "Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;"
        }
    .end annotation

    monitor-enter p0

    .line 367
    :try_start_0
    sget-wide v0, Lcom/exteragram/messenger/icons/IconManager;->initializationGeneration:J

    cmp-long p1, p1, v0

    if-eqz p1, :cond_0

    .line 368
    sget-object p1, Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;->STALE:Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object p1

    :catchall_0
    move-exception p1

    goto :goto_1

    .line 370
    :cond_0
    :try_start_1
    sget-boolean p1, Lcom/exteragram/messenger/icons/IconManager;->activePacksPublished:Z

    const/4 p2, 0x1

    .line 371
    sput-boolean p2, Lcom/exteragram/messenger/icons/IconManager;->activePacksPublished:Z

    if-eqz p1, :cond_1

    .line 372
    sget-object p2, Lcom/exteragram/messenger/icons/IconManager;->activePacks:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-static {p2, p3}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p2

    if-eqz p2, :cond_1

    .line 373
    sget-object p1, Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;->UNCHANGED:Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    monitor-exit p0

    return-object p1

    .line 376
    :cond_1
    :try_start_2
    sget-object p2, Lcom/exteragram/messenger/icons/IconManager;->activePacks:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {p2}, Ljava/util/concurrent/CopyOnWriteArrayList;->clear()V

    .line 377
    check-cast p3, Ljava/util/Collection;

    invoke-virtual {p2, p3}, Ljava/util/concurrent/CopyOnWriteArrayList;->addAll(Ljava/util/Collection;)Z

    .line 378
    invoke-direct {p0}, Lcom/exteragram/messenger/icons/IconManager;->rebuildOwnerMap()V

    .line 380
    sget-object p2, Lcom/exteragram/messenger/icons/IconManager;->resolvedCache:Lcom/exteragram/messenger/icons/IconManager$ResolvedIndex;

    invoke-virtual {p2}, Lcom/exteragram/messenger/icons/IconManager$ResolvedIndex;->clear()V

    .line 381
    sget-object p2, Lcom/exteragram/messenger/icons/IconManager;->sourceCache:Lcom/exteragram/messenger/icons/IconManager$sourceCache$1;

    invoke-virtual {p2}, Landroidx/collection/LruCache;->evictAll()V

    if-nez p1, :cond_2

    .line 383
    sget-object p1, Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;->INITIAL:Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;

    goto :goto_0

    :cond_2
    sget-object p1, Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;->CHANGED:Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    :goto_0
    monitor-exit p0

    return-object p1

    :goto_1
    :try_start_3
    monitor-exit p0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    throw p1
.end method


# virtual methods
.method public final awaitInitialization()V
    .locals 3

    .line 879
    :try_start_0
    sget-object p0, Lcom/exteragram/messenger/icons/IconManager;->initialPacksLoaded:Ljava/util/concurrent/CountDownLatch;

    sget-object v0, Ljava/util/concurrent/TimeUnit;->MILLISECONDS:Ljava/util/concurrent/TimeUnit;

    const-wide/16 v1, 0x3e8

    invoke-virtual {p0, v1, v2, v0}, Ljava/util/concurrent/CountDownLatch;->await(JLjava/util/concurrent/TimeUnit;)Z

    move-result p0

    if-nez p0, :cond_0

    const/4 p0, 0x1

    .line 880
    sput-boolean p0, Lcom/exteragram/messenger/icons/IconManager;->initializationTimedOut:Z

    .line 881
    const-string p0, "Icon packs were not loaded in 1000ms"

    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/InterruptedException; {:try_start_0 .. :try_end_0} :catch_0

    :cond_0
    return-void

    .line 884
    :catch_0
    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/Thread;->interrupt()V

    return-void
.end method

.method public final bundlePackBlocking(Ljava/lang/String;)Ljava/io/File;
    .locals 0

    .line 900
    sget-object p0, Lcom/exteragram/messenger/icons/IconPackStorage;->INSTANCE:Lcom/exteragram/messenger/icons/IconPackStorage;

    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/icons/IconPackStorage;->bundlePackBlocking(Ljava/lang/String;)Ljava/io/File;

    move-result-object p0

    return-object p0
.end method

.method public final createBitmapFromFile(Ljava/lang/String;IILandroid/content/res/Resources$Theme;)Landroid/graphics/Bitmap;
    .locals 20

    move-object/from16 v1, p1

    move/from16 v0, p2

    move/from16 v6, p3

    const/4 v8, 0x0

    .line 529
    :try_start_0
    sget-object v2, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-virtual {v2}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    instance-of v3, v2, Lcom/exteragram/messenger/icons/ExteraResources;

    if-eqz v3, :cond_0

    check-cast v2, Lcom/exteragram/messenger/icons/ExteraResources;

    goto :goto_0

    :catch_0
    move-exception v0

    move-object/from16 p0, v8

    goto/16 :goto_8

    :catch_1
    move-exception v0

    move-object/from16 p0, v8

    goto/16 :goto_9

    :cond_0
    move-object v2, v8

    :goto_0
    if-eqz v2, :cond_1

    invoke-virtual {v2, v0}, Lcom/exteragram/messenger/icons/ExteraResources;->getOriginalDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v2

    if-nez v2, :cond_2

    .line 533
    :cond_1
    sget-object v2, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-virtual {v2}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    move-object/from16 v3, p4

    .line 532
    invoke-static {v2, v0, v6, v3}, Landroidx/core/content/res/ResourcesCompat;->getDrawableForDensity(Landroid/content/res/Resources;IILandroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;

    move-result-object v2

    :cond_2
    const/high16 v0, 0x41c00000    # 24.0f

    if-eqz v2, :cond_3

    .line 539
    invoke-virtual {v2}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v3

    goto :goto_1

    :cond_3
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    :goto_1
    const/4 v4, 0x1

    invoke-static {v4, v3}, Ljava/lang/Math;->max(II)I

    move-result v3

    if-eqz v2, :cond_4

    .line 540
    invoke-virtual {v2}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v0

    goto :goto_2

    :cond_4
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    :goto_2
    invoke-static {v4, v0}, Ljava/lang/Math;->max(II)I

    move-result v5

    .line 542
    new-instance v0, Ljava/io/File;

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 543
    sget-object v2, Lcom/exteragram/messenger/icons/DecodedIconStore;->INSTANCE:Lcom/exteragram/messenger/icons/DecodedIconStore;

    invoke-virtual {v2, v0, v3, v5, v6}, Lcom/exteragram/messenger/icons/DecodedIconStore;->get(Ljava/io/File;III)Landroid/graphics/Bitmap;

    move-result-object v7

    if-eqz v7, :cond_5

    return-object v7

    .line 545
    :cond_5
    const-string v7, ".svg"

    invoke-static {v1, v7, v4}, Lkotlin/text/StringsKt;->endsWith(Ljava/lang/String;Ljava/lang/String;Z)Z

    move-result v7

    if-eqz v7, :cond_6

    .line 546
    new-instance v4, Ljava/io/FileInputStream;

    invoke-direct {v4, v1}, Ljava/io/FileInputStream;-><init>(Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/OutOfMemoryError; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :try_start_1
    invoke-static {v4}, Lcom/caverock/androidsvg/SVG;->getFromInputStream(Ljava/io/InputStream;)Lcom/caverock/androidsvg/SVG;

    move-result-object v7
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    :try_start_2
    invoke-static {v4, v8}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    .line 86
    sget-object v4, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    .line 88
    invoke-static {v3, v5, v4}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v4

    .line 548
    new-instance v9, Landroid/graphics/Canvas;

    invoke-direct {v9, v4}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    int-to-float v10, v3

    .line 549
    invoke-virtual {v7, v10}, Lcom/caverock/androidsvg/SVG;->setDocumentWidth(F)V

    int-to-float v10, v5

    .line 550
    invoke-virtual {v7, v10}, Lcom/caverock/androidsvg/SVG;->setDocumentHeight(F)V

    .line 551
    invoke-virtual {v7, v9}, Lcom/caverock/androidsvg/SVG;->renderToCanvas(Landroid/graphics/Canvas;)V

    .line 552
    invoke-virtual {v4, v6}, Landroid/graphics/Bitmap;->setDensity(I)V

    move-object v7, v4

    move v4, v3

    move-object v3, v0

    .line 553
    invoke-virtual/range {v2 .. v7}, Lcom/exteragram/messenger/icons/DecodedIconStore;->put(Ljava/io/File;IIILandroid/graphics/Bitmap;)V
    :try_end_2
    .catch Ljava/lang/OutOfMemoryError; {:try_start_2 .. :try_end_2} :catch_1
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    return-object v7

    :catchall_0
    move-exception v0

    move-object v2, v0

    .line 546
    :try_start_3
    throw v2
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    :catchall_1
    move-exception v0

    :try_start_4
    invoke-static {v4, v2}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    throw v0

    :cond_6
    move/from16 v19, v3

    move-object v3, v0

    move/from16 v0, v19

    .line 557
    new-instance v2, Landroid/graphics/BitmapFactory$Options;

    invoke-direct {v2}, Landroid/graphics/BitmapFactory$Options;-><init>()V

    .line 558
    iput-boolean v4, v2, Landroid/graphics/BitmapFactory$Options;->inJustDecodeBounds:Z

    .line 559
    invoke-static {v1, v2}, Landroid/graphics/BitmapFactory;->decodeFile(Ljava/lang/String;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;

    .line 560
    iget v7, v2, Landroid/graphics/BitmapFactory$Options;->outWidth:I

    if-lez v7, :cond_7

    iget v9, v2, Landroid/graphics/BitmapFactory$Options;->outHeight:I

    if-lez v9, :cond_7

    int-to-long v10, v7

    int-to-long v12, v9

    mul-long/2addr v10, v12

    const-wide/32 v12, 0x5f5e100

    cmp-long v10, v10, v12

    if-lez v10, :cond_8

    :cond_7
    move-object/from16 p0, v8

    goto/16 :goto_7

    .line 563
    :cond_8
    iput v4, v2, Landroid/graphics/BitmapFactory$Options;->inSampleSize:I

    if-gt v9, v5, :cond_9

    if-le v7, v0, :cond_a

    .line 565
    :cond_9
    div-int/lit8 v9, v9, 0x2

    .line 566
    div-int/lit8 v7, v7, 0x2

    .line 567
    :goto_3
    iget v10, v2, Landroid/graphics/BitmapFactory$Options;->inSampleSize:I

    div-int v11, v9, v10

    if-lt v11, v5, :cond_a

    div-int v11, v7, v10

    if-lt v11, v0, :cond_a

    mul-int/lit8 v10, v10, 0x2

    .line 568
    iput v10, v2, Landroid/graphics/BitmapFactory$Options;->inSampleSize:I

    goto :goto_3

    :cond_a
    int-to-long v9, v0

    int-to-long v11, v5

    mul-long/2addr v9, v11

    const-wide/32 v11, 0x100000

    .line 571
    invoke-static {v9, v10, v11, v12}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v9

    .line 573
    :goto_4
    iget v7, v2, Landroid/graphics/BitmapFactory$Options;->outWidth:I

    int-to-long v11, v7

    iget v7, v2, Landroid/graphics/BitmapFactory$Options;->inSampleSize:I
    :try_end_4
    .catch Ljava/lang/OutOfMemoryError; {:try_start_4 .. :try_end_4} :catch_1
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_0

    int-to-long v13, v7

    add-long/2addr v11, v13

    const-wide/16 v13, 0x1

    sub-long/2addr v11, v13

    move-object/from16 p0, v8

    move-wide v15, v9

    int-to-long v8, v7

    :try_start_5
    div-long/2addr v11, v8

    .line 574
    iget v8, v2, Landroid/graphics/BitmapFactory$Options;->outHeight:I

    int-to-long v8, v8

    move-wide/from16 v17, v13

    int-to-long v13, v7

    add-long/2addr v8, v13

    sub-long v8, v8, v17

    int-to-long v13, v7

    div-long/2addr v8, v13

    mul-long/2addr v11, v8

    cmp-long v8, v11, v15

    if-lez v8, :cond_b

    mul-int/lit8 v7, v7, 0x2

    .line 576
    iput v7, v2, Landroid/graphics/BitmapFactory$Options;->inSampleSize:I

    move-object/from16 v8, p0

    move-wide v9, v15

    goto :goto_4

    :catch_2
    move-exception v0

    goto :goto_8

    :catch_3
    move-exception v0

    goto :goto_9

    :cond_b
    const/4 v7, 0x0

    .line 578
    iput-boolean v7, v2, Landroid/graphics/BitmapFactory$Options;->inJustDecodeBounds:Z

    .line 579
    sget-object v7, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    iput-object v7, v2, Landroid/graphics/BitmapFactory$Options;->inPreferredConfig:Landroid/graphics/Bitmap$Config;

    .line 581
    invoke-static {v1, v2}, Landroid/graphics/BitmapFactory;->decodeFile(Ljava/lang/String;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;

    move-result-object v2

    if-nez v2, :cond_c

    return-object p0

    .line 583
    :cond_c
    invoke-virtual {v2}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v7

    if-ne v7, v0, :cond_e

    invoke-virtual {v2}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v7

    if-eq v7, v5, :cond_d

    goto :goto_5

    .line 591
    :cond_d
    invoke-virtual {v2, v6}, Landroid/graphics/Bitmap;->setDensity(I)V

    move-object v7, v2

    goto :goto_6

    .line 71
    :cond_e
    :goto_5
    invoke-static {v2, v0, v5, v4}, Landroid/graphics/Bitmap;->createScaledBitmap(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;

    move-result-object v4

    .line 585
    invoke-static {v4, v2}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_f

    .line 586
    invoke-virtual {v2}, Landroid/graphics/Bitmap;->recycle()V

    .line 588
    :cond_f
    invoke-virtual {v4, v6}, Landroid/graphics/Bitmap;->setDensity(I)V

    move-object v7, v4

    .line 594
    :goto_6
    sget-object v2, Lcom/exteragram/messenger/icons/DecodedIconStore;->INSTANCE:Lcom/exteragram/messenger/icons/DecodedIconStore;

    move v4, v0

    invoke-virtual/range {v2 .. v7}, Lcom/exteragram/messenger/icons/DecodedIconStore;->put(Ljava/io/File;IIILandroid/graphics/Bitmap;)V
    :try_end_5
    .catch Ljava/lang/OutOfMemoryError; {:try_start_5 .. :try_end_5} :catch_3
    .catch Ljava/lang/Exception; {:try_start_5 .. :try_end_5} :catch_2

    return-object v7

    :goto_7
    return-object p0

    .line 600
    :goto_8
    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "Error loading icon bitmap: "

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1, v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_a

    .line 597
    :goto_9
    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "Out of memory loading icon bitmap: "

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1, v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    :goto_a
    return-object p0
.end method

.method public final deletePack(Ljava/lang/String;)V
    .locals 6

    .line 926
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->scope:Lkotlinx/coroutines/CoroutineScope;

    sget-object v1, Lcom/exteragram/messenger/icons/IconManager;->mutationDispatcher:Lkotlinx/coroutines/CoroutineDispatcher;

    new-instance v3, Lcom/exteragram/messenger/icons/IconManager$deletePack$1;

    const/4 p0, 0x0

    invoke-direct {v3, p1, p0}, Lcom/exteragram/messenger/icons/IconManager$deletePack$1;-><init>(Ljava/lang/String;Lkotlin/coroutines/Continuation;)V

    const/4 v4, 0x2

    const/4 v5, 0x0

    const/4 v2, 0x0

    invoke-static/range {v0 .. v5}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    return-void
.end method

.method public final findPackById(Ljava/lang/String;)Lcom/exteragram/messenger/icons/IconPack;
    .locals 0

    .line 898
    sget-object p0, Lcom/exteragram/messenger/icons/IconPackStorage;->INSTANCE:Lcom/exteragram/messenger/icons/IconPackStorage;

    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/icons/IconPackStorage;->findPackById(Ljava/lang/String;)Lcom/exteragram/messenger/icons/IconPack;

    move-result-object p0

    return-object p0
.end method

.method public final getCachedDrawable(I)Landroid/graphics/drawable/Drawable;
    .locals 3

    .line 445
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->iconOwnerMap:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0}, Ljava/util/concurrent/ConcurrentHashMap;->isEmpty()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    return-object v1

    .line 446
    :cond_0
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->resolvedCache:Lcom/exteragram/messenger/icons/IconManager$ResolvedIndex;

    sget-object v2, Lorg/telegram/messenger/AndroidUtilities;->displayMetrics:Landroid/util/DisplayMetrics;

    iget v2, v2, Landroid/util/DisplayMetrics;->densityDpi:I

    invoke-direct {p0, p1, v2}, Lcom/exteragram/messenger/icons/IconManager;->resolvedCacheKey(II)J

    move-result-wide p0

    invoke-virtual {v0, p0, p1}, Lcom/exteragram/messenger/icons/IconManager$ResolvedIndex;->get(J)Lcom/exteragram/messenger/icons/IconManager$SourceCacheKey;

    move-result-object p0

    if-eqz p0, :cond_1

    .line 447
    sget-object p1, Lcom/exteragram/messenger/icons/IconManager;->sourceCache:Lcom/exteragram/messenger/icons/IconManager$sourceCache$1;

    invoke-virtual {p1, p0}, Landroidx/collection/LruCache;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/graphics/Bitmap;

    if-eqz p0, :cond_1

    .line 448
    sget-object p1, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    .line 27
    new-instance v0, Landroid/graphics/drawable/BitmapDrawable;

    invoke-direct {v0, p1, p0}, Landroid/graphics/drawable/BitmapDrawable;-><init>(Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V

    return-object v0

    :cond_1
    return-object v1
.end method

.method public final getCachedPackIconDrawable(Lcom/exteragram/messenger/icons/IconPack;I)Landroid/graphics/drawable/Drawable;
    .locals 1

    .line 463
    sget-object v0, Lorg/telegram/messenger/AndroidUtilities;->displayMetrics:Landroid/util/DisplayMetrics;

    iget v0, v0, Landroid/util/DisplayMetrics;->densityDpi:I

    invoke-direct {p0, p1, p2, v0}, Lcom/exteragram/messenger/icons/IconManager;->cachedPackIconBitmap(Lcom/exteragram/messenger/icons/IconPack;II)Landroid/graphics/Bitmap;

    move-result-object p0

    if-eqz p0, :cond_0

    .line 464
    sget-object p1, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    .line 27
    new-instance p2, Landroid/graphics/drawable/BitmapDrawable;

    invoke-direct {p2, p1, p0}, Landroid/graphics/drawable/BitmapDrawable;-><init>(Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V

    return-object p2

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public final getDrawable(IILandroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;
    .locals 11

    .line 417
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->iconOwnerMap:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0}, Ljava/util/concurrent/ConcurrentHashMap;->isEmpty()Z

    move-result v1

    const/4 v2, 0x0

    if-eqz v1, :cond_0

    return-object v2

    :cond_0
    if-nez p2, :cond_1

    .line 418
    sget-object p2, Lorg/telegram/messenger/AndroidUtilities;->displayMetrics:Landroid/util/DisplayMetrics;

    iget p2, p2, Landroid/util/DisplayMetrics;->densityDpi:I

    :cond_1
    move v6, p2

    .line 419
    invoke-direct {p0, p1, v6}, Lcom/exteragram/messenger/icons/IconManager;->resolvedCacheKey(II)J

    move-result-wide v3

    .line 420
    sget-object p2, Lcom/exteragram/messenger/icons/IconManager;->resolvedCache:Lcom/exteragram/messenger/icons/IconManager$ResolvedIndex;

    invoke-virtual {p2, v3, v4}, Lcom/exteragram/messenger/icons/IconManager$ResolvedIndex;->get(J)Lcom/exteragram/messenger/icons/IconManager$SourceCacheKey;

    move-result-object p2

    if-eqz p2, :cond_2

    sget-object v1, Lcom/exteragram/messenger/icons/IconManager;->sourceCache:Lcom/exteragram/messenger/icons/IconManager$sourceCache$1;

    invoke-virtual {v1, p2}, Landroidx/collection/LruCache;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Landroid/graphics/Bitmap;

    goto :goto_0

    :cond_2
    move-object p2, v2

    :goto_0
    if-eqz p2, :cond_3

    .line 422
    sget-object p0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    .line 27
    new-instance p1, Landroid/graphics/drawable/BitmapDrawable;

    invoke-direct {p1, p0, p2}, Landroid/graphics/drawable/BitmapDrawable;-><init>(Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V

    return-object p1

    .line 425
    :cond_3
    sget-object p2, Lcom/exteragram/messenger/icons/IconManager;->systemNames:Lcom/exteragram/messenger/icons/IconManager$IntStringMap;

    if-eqz p2, :cond_5

    invoke-virtual {p2, p1}, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->get(I)Ljava/lang/String;

    move-result-object p2

    if-nez p2, :cond_4

    goto :goto_2

    :cond_4
    :goto_1
    move-object v8, p2

    goto :goto_3

    .line 426
    :cond_5
    :goto_2
    :try_start_0
    sget-object p2, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-virtual {p2}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p2

    invoke-virtual {p2, p1}, Landroid/content/res/Resources;->getResourceEntryName(I)Ljava/lang/String;

    move-result-object p2
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_1

    .line 431
    :goto_3
    invoke-virtual {v0, v8}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p2

    move-object v4, p2

    check-cast v4, Lcom/exteragram/messenger/icons/IconPack;

    if-eqz v4, :cond_6

    .line 433
    sget-wide v0, Lcom/exteragram/messenger/icons/IconManager;->initializationGeneration:J

    const/4 v9, 0x0

    move-object v3, p0

    move v5, p1

    move-object v7, p3

    .line 434
    invoke-direct/range {v3 .. v9}, Lcom/exteragram/messenger/icons/IconManager;->getPackIconBitmap(Lcom/exteragram/messenger/icons/IconPack;IILandroid/content/res/Resources$Theme;Ljava/lang/String;Z)Landroid/graphics/Bitmap;

    move-result-object v10

    if-eqz v10, :cond_6

    move-object v7, v4

    move v9, v6

    move-object v6, v8

    move v8, v5

    move-wide v4, v0

    .line 436
    invoke-direct/range {v3 .. v10}, Lcom/exteragram/messenger/icons/IconManager;->publishBitmap(JLjava/lang/String;Lcom/exteragram/messenger/icons/IconPack;IILandroid/graphics/Bitmap;)Z

    .line 437
    sget-object p0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    .line 27
    new-instance p1, Landroid/graphics/drawable/BitmapDrawable;

    invoke-direct {p1, p0, v10}, Landroid/graphics/drawable/BitmapDrawable;-><init>(Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V

    return-object p1

    :catch_0
    :cond_6
    return-object v2
.end method

.method public final getIcon(I)I
    .locals 1

    .line 706
    sget-object p0, Lcom/exteragram/messenger/icons/IconManager;->basePreinstalledMap:Landroid/util/SparseIntArray;

    if-nez p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, -0x1

    .line 707
    invoke-virtual {p0, p1, v0}, Landroid/util/SparseIntArray;->get(II)I

    move-result p0

    if-eq p0, v0, :cond_1

    return p0

    :cond_1
    :goto_0
    return p1
.end method

.method public final getPackIconDrawable(Lcom/exteragram/messenger/icons/IconPack;I)Landroid/graphics/drawable/Drawable;
    .locals 10

    .line 452
    sget-object v0, Lorg/telegram/messenger/AndroidUtilities;->displayMetrics:Landroid/util/DisplayMetrics;

    iget v4, v0, Landroid/util/DisplayMetrics;->densityDpi:I

    const/16 v8, 0x30

    const/4 v9, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    move-object v1, p0

    move-object v2, p1

    move v3, p2

    invoke-static/range {v1 .. v9}, Lcom/exteragram/messenger/icons/IconManager;->getPackIconBitmap$default(Lcom/exteragram/messenger/icons/IconManager;Lcom/exteragram/messenger/icons/IconPack;IILandroid/content/res/Resources$Theme;Ljava/lang/String;ZILjava/lang/Object;)Landroid/graphics/Bitmap;

    move-result-object p0

    if-eqz p0, :cond_0

    .line 453
    sget-object p1, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    .line 27
    new-instance p2, Landroid/graphics/drawable/BitmapDrawable;

    invoke-direct {p2, p1, p0}, Landroid/graphics/drawable/BitmapDrawable;-><init>(Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V

    return-object p2

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public final getSystemIcons()Ljava/util/concurrent/ConcurrentHashMap;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation

    .line 205
    sget-object p0, Lcom/exteragram/messenger/icons/IconManager;->systemIcons:Ljava/util/concurrent/ConcurrentHashMap;

    return-object p0
.end method

.method public final handleIconPack(Lorg/telegram/ui/ActionBar/BaseFragment;Ljava/lang/String;)V
    .locals 6

    .line 971
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->scope:Lkotlinx/coroutines/CoroutineScope;

    new-instance v3, Lcom/exteragram/messenger/icons/IconManager$handleIconPack$1;

    const/4 p0, 0x0

    invoke-direct {v3, p2, p1, p0}, Lcom/exteragram/messenger/icons/IconManager$handleIconPack$1;-><init>(Ljava/lang/String;Lorg/telegram/ui/ActionBar/BaseFragment;Lkotlin/coroutines/Continuation;)V

    const/4 v4, 0x3

    const/4 v5, 0x0

    const/4 v1, 0x0

    const/4 v2, 0x0

    invoke-static/range {v0 .. v5}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    return-void
.end method

.method public final handleIconPack(Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/messenger/MessageObject;)V
    .locals 1

    .line 946
    invoke-static {}, Lcom/exteragram/messenger/utils/chats/ChatUtils;->getInstance()Lcom/exteragram/messenger/utils/chats/ChatUtils;

    move-result-object v0

    invoke-virtual {v0, p2}, Lcom/exteragram/messenger/utils/chats/ChatUtils;->getPathToMessage(Lorg/telegram/messenger/MessageObject;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/icons/IconManager;->handleIconPack(Lorg/telegram/ui/ActionBar/BaseFragment;Ljava/lang/String;)V

    return-void
.end method

.method public final declared-synchronized initialize(Z)V
    .locals 9

    monitor-enter p0

    .line 764
    :try_start_0
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->initializationJob:Lkotlinx/coroutines/Job;

    const/4 v1, 0x1

    if-eqz v0, :cond_0

    invoke-interface {v0}, Lkotlinx/coroutines/Job;->isActive()Z

    move-result v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-ne v0, v1, :cond_0

    if-nez p1, :cond_0

    monitor-exit p0

    return-void

    :catchall_0
    move-exception v0

    move-object p1, v0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    if-eqz p1, :cond_1

    .line 766
    :try_start_1
    sget-object v2, Lcom/exteragram/messenger/icons/IconManager;->initializationJob:Lkotlinx/coroutines/Job;

    if-eqz v2, :cond_1

    invoke-static {v2, v0, v1, v0}, Lkotlinx/coroutines/Job;->cancel$default(Lkotlinx/coroutines/Job;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V

    .line 768
    :cond_1
    sget-object v2, Lcom/exteragram/messenger/icons/IconManager;->prewarmJob:Lkotlinx/coroutines/Job;

    if-eqz v2, :cond_2

    invoke-static {v2, v0, v1, v0}, Lkotlinx/coroutines/Job;->cancel$default(Lkotlinx/coroutines/Job;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V

    .line 769
    :cond_2
    sget-wide v1, Lcom/exteragram/messenger/icons/IconManager;->initializationGeneration:J

    const-wide/16 v3, 0x1

    add-long/2addr v1, v3

    sput-wide v1, Lcom/exteragram/messenger/icons/IconManager;->initializationGeneration:J

    sget-wide v1, Lcom/exteragram/messenger/icons/IconManager;->initializationGeneration:J

    .line 771
    sget-object v3, Lcom/exteragram/messenger/icons/IconManager;->scope:Lkotlinx/coroutines/CoroutineScope;

    new-instance v6, Lcom/exteragram/messenger/icons/IconManager$initialize$1;

    invoke-direct {v6, v1, v2, p1, v0}, Lcom/exteragram/messenger/icons/IconManager$initialize$1;-><init>(JZLkotlin/coroutines/Continuation;)V

    const/4 v7, 0x3

    const/4 v8, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    invoke-static/range {v3 .. v8}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    move-result-object p1

    sput-object p1, Lcom/exteragram/messenger/icons/IconManager;->initializationJob:Lkotlinx/coroutines/Job;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 875
    monitor-exit p0

    return-void

    :goto_0
    :try_start_2
    monitor-exit p0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw p1
.end method

.method public final isBasePackOnly(Lcom/exteragram/messenger/IconPackType;)Z
    .locals 0

    .line 1055
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPack()Lcom/exteragram/messenger/IconPackType;

    move-result-object p0

    if-ne p0, p1, :cond_3

    sget-object p0, Lcom/exteragram/messenger/icons/IconManager;->activePacks:Ljava/util/concurrent/CopyOnWriteArrayList;

    if-eqz p0, :cond_0

    .line 2792
    invoke-interface {p0}, Ljava/util/Collection;->isEmpty()Z

    move-result p1

    if-eqz p1, :cond_0

    goto :goto_0

    .line 2793
    :cond_0
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_1
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p1

    if-eqz p1, :cond_2

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/exteragram/messenger/icons/IconPack;

    .line 1055
    invoke-virtual {p1}, Lcom/exteragram/messenger/icons/IconPack;->isBase()Z

    move-result p1

    if-nez p1, :cond_1

    goto :goto_1

    :cond_2
    :goto_0
    const/4 p0, 0x1

    return p0

    :cond_3
    :goto_1
    const/4 p0, 0x0

    return p0
.end method

.method public final isBlacklisted(Ljava/lang/String;)Z
    .locals 3

    .line 110
    sget-object p0, Lcom/exteragram/messenger/icons/IconManager;->blacklistedIcons:Ljava/util/Set;

    invoke-interface {p0, p1}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 111
    const-string p0, "avd"

    const/4 v0, 0x0

    const/4 v1, 0x2

    const/4 v2, 0x0

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 112
    const-string p0, "_solar"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->endsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 113
    const-string p0, "_remix"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->endsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 114
    const-string p0, "$"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 115
    const-string p0, "animationpin"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 116
    const-string/jumbo p0, "googlepay"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 117
    const-string/jumbo p0, "shadow"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 118
    const-string/jumbo p0, "ic_monochrome"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 119
    const-string/jumbo p0, "nocover"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 120
    const-string/jumbo p0, "gradient_"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 121
    const-string/jumbo p0, "stickers_back_"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 122
    const-string/jumbo p0, "media_doc_"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 123
    const-string/jumbo p0, "loading_animation"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 124
    const-string/jumbo p0, "intro_"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 125
    const-string/jumbo p0, "minibubble_"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 126
    const-string p0, "book_"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 127
    const-string p0, "call_"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 128
    const-string p0, "dice"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 129
    const-string/jumbo p0, "msg_other_new_filled"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 130
    const-string/jumbo p0, "profile_level"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 131
    const-string/jumbo p0, "widget_"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 132
    const-string/jumbo p0, "zoom_slide"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 133
    const-string/jumbo p0, "zoom_round"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 134
    const-string/jumbo p0, "popup_fixed_alert"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 135
    const-string/jumbo p0, "search_dark"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 136
    const-string p0, "bar_selector"

    invoke-static {p1, p0, v0, v1, v2}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    goto :goto_0

    :cond_0
    return v0

    :cond_1
    :goto_0
    const/4 p0, 0x1

    return p0
.end method

.method public final isIconPack(Ljava/lang/String;Lorg/telegram/messenger/MessageObject;)Z
    .locals 2

    const/4 p0, 0x0

    if-eqz p2, :cond_0

    .line 943
    invoke-virtual {p2}, Lorg/telegram/messenger/MessageObject;->getDocumentName()Ljava/lang/String;

    move-result-object p2

    goto :goto_0

    :cond_0
    move-object p2, p0

    :goto_0
    const/4 v0, 0x0

    if-eqz p2, :cond_1

    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result p2

    if-lez p2, :cond_1

    const-string p2, ".icons"

    const/4 v1, 0x2

    invoke-static {p1, p2, v0, v1, p0}, Lkotlin/text/StringsKt;->endsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_1

    const/4 p0, 0x1

    return p0

    :cond_1
    return v0
.end method

.method public final isIconPack(Lorg/telegram/messenger/MessageObject;)Z
    .locals 1

    .line 940
    invoke-static {}, Lcom/exteragram/messenger/utils/chats/ChatUtils;->getInstance()Lcom/exteragram/messenger/utils/chats/ChatUtils;

    move-result-object v0

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/utils/chats/ChatUtils;->getPathToMessage(Lorg/telegram/messenger/MessageObject;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0, p1}, Lcom/exteragram/messenger/icons/IconManager;->isIconPack(Ljava/lang/String;Lorg/telegram/messenger/MessageObject;)Z

    move-result p0

    return p0
.end method

.method public final onActivityResult(IILandroid/content/Intent;)Z
    .locals 0

    .line 1014
    sget-object p0, Lcom/exteragram/messenger/icons/IconManager;->resultCallbacks:Landroid/util/SparseArray;

    invoke-virtual {p0, p1}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lkotlin/jvm/functions/Function1;

    if-nez p2, :cond_0

    const/4 p0, 0x0

    return p0

    .line 1015
    :cond_0
    invoke-virtual {p0, p1}, Landroid/util/SparseArray;->remove(I)V

    if-eqz p3, :cond_1

    .line 1016
    invoke-virtual {p3}, Landroid/content/Intent;->getData()Landroid/net/Uri;

    move-result-object p0

    goto :goto_0

    :cond_1
    const/4 p0, 0x0

    :goto_0
    invoke-interface {p2, p0}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    const/4 p0, 0x1

    return p0
.end method

.method public final prefetchCustomPacks()V
    .locals 6

    .line 318
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->scope:Lkotlinx/coroutines/CoroutineScope;

    new-instance v3, Lcom/exteragram/messenger/icons/IconManager$prefetchCustomPacks$1;

    const/4 p0, 0x0

    invoke-direct {v3, p0}, Lcom/exteragram/messenger/icons/IconManager$prefetchCustomPacks$1;-><init>(Lkotlin/coroutines/Continuation;)V

    const/4 v4, 0x3

    const/4 v5, 0x0

    const/4 v1, 0x0

    const/4 v2, 0x0

    invoke-static/range {v0 .. v5}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    return-void
.end method

.method public final requestDrawable(ILorg/telegram/messenger/Utilities$Callback;)V
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I",
            "Lorg/telegram/messenger/Utilities$Callback<",
            "Landroid/graphics/drawable/Drawable;",
            ">;)V"
        }
    .end annotation

    .line 482
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->iconOwnerMap:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0}, Ljava/util/concurrent/ConcurrentHashMap;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    return-void

    .line 483
    :cond_0
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/icons/IconManager;->getCachedDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object p0

    if-eqz p0, :cond_1

    .line 484
    invoke-interface {p2, p0}, Lorg/telegram/messenger/Utilities$Callback;->run(Ljava/lang/Object;)V

    return-void

    .line 487
    :cond_1
    sget-object p0, Lorg/telegram/messenger/AndroidUtilities;->displayMetrics:Landroid/util/DisplayMetrics;

    iget p0, p0, Landroid/util/DisplayMetrics;->densityDpi:I

    .line 488
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->scope:Lkotlinx/coroutines/CoroutineScope;

    sget-object v1, Lcom/exteragram/messenger/icons/IconManager;->decodeDispatcher:Lkotlinx/coroutines/CoroutineDispatcher;

    new-instance v3, Lcom/exteragram/messenger/icons/IconManager$requestDrawable$2;

    const/4 v2, 0x0

    invoke-direct {v3, p1, p0, p2, v2}, Lcom/exteragram/messenger/icons/IconManager$requestDrawable$2;-><init>(IILorg/telegram/messenger/Utilities$Callback;Lkotlin/coroutines/Continuation;)V

    const/4 v4, 0x2

    const/4 v5, 0x0

    invoke-static/range {v0 .. v5}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    return-void
.end method

.method public final requestPackIconDrawable(Lcom/exteragram/messenger/icons/IconPack;ILorg/telegram/messenger/Utilities$Callback;)V
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/exteragram/messenger/icons/IconPack;",
            "I",
            "Lorg/telegram/messenger/Utilities$Callback<",
            "Landroid/graphics/drawable/Drawable;",
            ">;)V"
        }
    .end annotation

    .line 468
    sget-object v0, Lorg/telegram/messenger/AndroidUtilities;->displayMetrics:Landroid/util/DisplayMetrics;

    iget v4, v0, Landroid/util/DisplayMetrics;->densityDpi:I

    .line 469
    invoke-direct {p0, p1, p2, v4}, Lcom/exteragram/messenger/icons/IconManager;->cachedPackIconBitmap(Lcom/exteragram/messenger/icons/IconPack;II)Landroid/graphics/Bitmap;

    move-result-object p0

    if-eqz p0, :cond_0

    .line 470
    sget-object p1, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    .line 27
    new-instance p2, Landroid/graphics/drawable/BitmapDrawable;

    invoke-direct {p2, p1, p0}, Landroid/graphics/drawable/BitmapDrawable;-><init>(Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V

    .line 470
    invoke-interface {p3, p2}, Lorg/telegram/messenger/Utilities$Callback;->run(Ljava/lang/Object;)V

    return-void

    .line 473
    :cond_0
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->scope:Lkotlinx/coroutines/CoroutineScope;

    sget-object p0, Lcom/exteragram/messenger/icons/IconManager;->decodeDispatcher:Lkotlinx/coroutines/CoroutineDispatcher;

    new-instance v1, Lcom/exteragram/messenger/icons/IconManager$requestPackIconDrawable$2;

    const/4 v6, 0x0

    move-object v2, p1

    move v3, p2

    move-object v5, p3

    invoke-direct/range {v1 .. v6}, Lcom/exteragram/messenger/icons/IconManager$requestPackIconDrawable$2;-><init>(Lcom/exteragram/messenger/icons/IconPack;IILorg/telegram/messenger/Utilities$Callback;Lkotlin/coroutines/Continuation;)V

    const/4 v4, 0x2

    const/4 v5, 0x0

    const/4 v2, 0x0

    move-object v3, v1

    move-object v1, p0

    invoke-static/range {v0 .. v5}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    return-void
.end method

.method public final resetCustomIcon(Ljava/lang/String;I)V
    .locals 6

    .line 668
    sget-object p0, Lcom/exteragram/messenger/icons/IconManager;->systemNames:Lcom/exteragram/messenger/icons/IconManager$IntStringMap;

    if-eqz p0, :cond_0

    invoke-virtual {p0, p2}, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->get(I)Ljava/lang/String;

    move-result-object p0

    if-nez p0, :cond_1

    .line 669
    :cond_0
    :try_start_0
    sget-object p0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    invoke-virtual {p0, p2}, Landroid/content/res/Resources;->getResourceEntryName(I)Ljava/lang/String;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 674
    :cond_1
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->scope:Lkotlinx/coroutines/CoroutineScope;

    sget-object v1, Lcom/exteragram/messenger/icons/IconManager;->mutationDispatcher:Lkotlinx/coroutines/CoroutineDispatcher;

    new-instance v3, Lcom/exteragram/messenger/icons/IconManager$resetCustomIcon$1;

    const/4 v2, 0x0

    invoke-direct {v3, p1, p0, p2, v2}, Lcom/exteragram/messenger/icons/IconManager$resetCustomIcon$1;-><init>(Ljava/lang/String;Ljava/lang/String;ILkotlin/coroutines/Continuation;)V

    const/4 v4, 0x2

    const/4 v5, 0x0

    invoke-static/range {v0 .. v5}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    :catch_0
    return-void
.end method

.method public final saveCustomIcon(Ljava/lang/String;ILjava/io/File;Ljava/lang/String;)V
    .locals 11

    .line 606
    sget-object p0, Lcom/exteragram/messenger/icons/IconManager;->systemNames:Lcom/exteragram/messenger/icons/IconManager$IntStringMap;

    if-eqz p0, :cond_1

    invoke-virtual {p0, p2}, Lcom/exteragram/messenger/icons/IconManager$IntStringMap;->get(I)Ljava/lang/String;

    move-result-object p0

    if-nez p0, :cond_0

    goto :goto_1

    :cond_0
    :goto_0
    move-object v4, p0

    goto :goto_2

    .line 607
    :cond_1
    :goto_1
    :try_start_0
    sget-object p0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    invoke-virtual {p0, p2}, Landroid/content/res/Resources;->getResourceEntryName(I)Ljava/lang/String;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    .line 612
    :goto_2
    sget-object p0, Lcom/exteragram/messenger/icons/IconManager;->scope:Lkotlinx/coroutines/CoroutineScope;

    sget-object v7, Lcom/exteragram/messenger/icons/IconManager;->mutationDispatcher:Lkotlinx/coroutines/CoroutineDispatcher;

    new-instance v0, Lcom/exteragram/messenger/icons/IconManager$saveCustomIcon$1;

    const/4 v6, 0x0

    move-object v1, p1

    move v5, p2

    move-object v2, p3

    move-object v3, p4

    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/icons/IconManager$saveCustomIcon$1;-><init>(Ljava/lang/String;Ljava/io/File;Ljava/lang/String;Ljava/lang/String;ILkotlin/coroutines/Continuation;)V

    const/4 v9, 0x2

    const/4 v10, 0x0

    move-object v6, v7

    const/4 v7, 0x0

    move-object v5, p0

    move-object v8, v0

    invoke-static/range {v5 .. v10}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    :catch_0
    return-void
.end method

.method public final saveIconPackMetadata(Lcom/exteragram/messenger/icons/IconPack;)Z
    .locals 6

    .line 905
    sget-object p0, Lcom/exteragram/messenger/icons/IconManager;->mutationDispatcher:Lkotlinx/coroutines/CoroutineDispatcher;

    new-instance v0, Lcom/exteragram/messenger/icons/IconManager$saveIconPackMetadata$savedPack$1;

    const/4 v1, 0x0

    invoke-direct {v0, p1, v1}, Lcom/exteragram/messenger/icons/IconManager$saveIconPackMetadata$savedPack$1;-><init>(Lcom/exteragram/messenger/icons/IconPack;Lkotlin/coroutines/Continuation;)V

    invoke-static {p0, v0}, Lkotlinx/coroutines/BuildersKt;->runBlockingK(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/exteragram/messenger/icons/IconPack;

    if-nez p0, :cond_0

    const/4 p0, 0x0

    return p0

    .line 911
    :cond_0
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->scope:Lkotlinx/coroutines/CoroutineScope;

    move-object p1, v1

    invoke-static {}, Lkotlinx/coroutines/Dispatchers;->getMain()Lkotlinx/coroutines/MainCoroutineDispatcher;

    move-result-object v1

    new-instance v3, Lcom/exteragram/messenger/icons/IconManager$saveIconPackMetadata$1;

    invoke-direct {v3, p0, p1}, Lcom/exteragram/messenger/icons/IconManager$saveIconPackMetadata$1;-><init>(Lcom/exteragram/messenger/icons/IconPack;Lkotlin/coroutines/Continuation;)V

    const/4 v4, 0x2

    const/4 v5, 0x0

    const/4 v2, 0x0

    invoke-static/range {v0 .. v5}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    const/4 p0, 0x1

    return p0
.end method

.method public final setActiveCustomPack(Ljava/lang/String;)V
    .locals 1

    if-nez p1, :cond_0

    goto :goto_0

    .line 890
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksLayout()Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 891
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksLayout()Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 892
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getIconPacksHidden()Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    .line 893
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->saveIconPacksLayout()V

    const/4 p1, 0x1

    .line 894
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/icons/IconManager;->initialize(Z)V

    :cond_1
    :goto_0
    return-void
.end method

.method public final showReplaceAlert(Landroid/content/Context;I)V
    .locals 6
    .annotation build Lkotlin/jvm/JvmOverloads;
    .end annotation

    .line 0
    const/4 v4, 0x4

    const/4 v5, 0x0

    const/4 v3, 0x0

    move-object v0, p0

    move-object v1, p1

    move v2, p2

    invoke-static/range {v0 .. v5}, Lcom/exteragram/messenger/icons/IconManager;->showReplaceAlert$default(Lcom/exteragram/messenger/icons/IconManager;Landroid/content/Context;ILcom/exteragram/messenger/icons/IconPack;ILjava/lang/Object;)V

    return-void
.end method

.method public final showReplaceAlert(Landroid/content/Context;ILcom/exteragram/messenger/icons/IconPack;)V
    .locals 3
    .annotation build Lkotlin/jvm/JvmOverloads;
    .end annotation

    if-nez p3, :cond_5

    .line 1044
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getEditingIconPackId()Ljava/lang/String;

    move-result-object p0

    const/4 p3, 0x0

    if-eqz p0, :cond_2

    .line 1045
    sget-object p0, Lcom/exteragram/messenger/icons/IconManager;->activePacks:Ljava/util/concurrent/CopyOnWriteArrayList;

    .line 296
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    move-object v1, v0

    check-cast v1, Lcom/exteragram/messenger/icons/IconPack;

    .line 1045
    invoke-virtual {v1}, Lcom/exteragram/messenger/icons/IconPack;->getId()Ljava/lang/String;

    move-result-object v1

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getEditingIconPackId()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_0

    move-object p3, v0

    :cond_1
    check-cast p3, Lcom/exteragram/messenger/icons/IconPack;

    goto :goto_0

    .line 1047
    :cond_2
    sget-object p0, Lcom/exteragram/messenger/icons/IconManager;->activePacks:Ljava/util/concurrent/CopyOnWriteArrayList;

    .line 296
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_3
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_4

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    move-object v1, v0

    check-cast v1, Lcom/exteragram/messenger/icons/IconPack;

    .line 1047
    invoke-virtual {v1}, Lcom/exteragram/messenger/icons/IconPack;->isBase()Z

    move-result v1

    if-nez v1, :cond_3

    move-object p3, v0

    :cond_4
    check-cast p3, Lcom/exteragram/messenger/icons/IconPack;

    :goto_0
    if-nez p3, :cond_5

    goto :goto_1

    .line 1050
    :cond_5
    invoke-static {}, Lorg/telegram/ui/LaunchActivity;->getSafeLastFragment()Lorg/telegram/ui/ActionBar/BaseFragment;

    move-result-object p0

    if-nez p0, :cond_6

    :goto_1
    return-void

    .line 1051
    :cond_6
    new-instance v0, Lcom/exteragram/messenger/icons/ui/components/ReplaceIconBottomSheet;

    invoke-direct {v0, p1, p2, p3}, Lcom/exteragram/messenger/icons/ui/components/ReplaceIconBottomSheet;-><init>(Landroid/content/Context;ILcom/exteragram/messenger/icons/IconPack;)V

    invoke-virtual {p0, v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->showDialog(Landroid/app/Dialog;)Landroid/app/Dialog;

    return-void
.end method

.method public final startIconPicker(Landroid/app/Activity;ZLkotlin/jvm/functions/Function1;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/app/Activity;",
            "Z",
            "Lkotlin/jvm/functions/Function1<",
            "-",
            "Landroid/net/Uri;",
            "Lkotlin/Unit;",
            ">;)V"
        }
    .end annotation

    .line 1022
    sget-object p0, Lcom/exteragram/messenger/icons/IconManager;->resultCallbacks:Landroid/util/SparseArray;

    const/16 v0, 0x2b

    invoke-virtual {p0, v0, p3}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 1024
    sget-object p0, Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia;->Companion:Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$Companion;

    invoke-virtual {p0, p1}, Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$Companion;->isPhotoPickerAvailable(Landroid/content/Context;)Z

    move-result p0

    if-eqz p0, :cond_0

    if-nez p2, :cond_0

    .line 1025
    new-instance p0, Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia;

    invoke-direct {p0}, Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia;-><init>()V

    .line 1027
    sget-object p2, Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$ImageOnly;->INSTANCE:Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$ImageOnly;

    invoke-static {p2}, Landroidx/activity/result/PickVisualMediaRequestKt;->PickVisualMediaRequest(Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$VisualMediaType;)Landroidx/activity/result/PickVisualMediaRequest;

    move-result-object p2

    .line 1025
    invoke-virtual {p0, p1, p2}, Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia;->createIntent(Landroid/content/Context;Landroidx/activity/result/PickVisualMediaRequest;)Landroid/content/Intent;

    move-result-object p0

    goto :goto_1

    :cond_0
    if-eqz p2, :cond_1

    .line 1030
    const-string p0, "android.intent.action.OPEN_DOCUMENT"

    goto :goto_0

    :cond_1
    const-string p0, "android.intent.action.GET_CONTENT"

    .line 1031
    :goto_0
    new-instance p3, Landroid/content/Intent;

    invoke-direct {p3, p0}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    .line 1032
    const-string p0, "*/*"

    invoke-virtual {p3, p0}, Landroid/content/Intent;->setType(Ljava/lang/String;)Landroid/content/Intent;

    .line 1033
    const-string/jumbo p0, "image/*"

    const-string/jumbo v1, "image/svg+xml"

    filled-new-array {p0, v1}, [Ljava/lang/String;

    move-result-object p0

    const-string v1, "android.intent.extra.MIME_TYPES"

    invoke-virtual {p3, v1, p0}, Landroid/content/Intent;->putExtra(Ljava/lang/String;[Ljava/lang/String;)Landroid/content/Intent;

    if-eqz p2, :cond_2

    .line 1035
    const-string p0, "android.intent.category.OPENABLE"

    invoke-virtual {p3, p0}, Landroid/content/Intent;->addCategory(Ljava/lang/String;)Landroid/content/Intent;

    :cond_2
    move-object p0, p3

    .line 1039
    :goto_1
    invoke-virtual {p1, p0, v0}, Landroid/app/Activity;->startActivityForResult(Landroid/content/Intent;I)V

    return-void
.end method
