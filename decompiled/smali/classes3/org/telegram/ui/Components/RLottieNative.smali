.class public final Lorg/telegram/ui/Components/RLottieNative;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final mMetaData:[I

.field private mNativePtr:J

.field private final mRecycled:Ljava/util/concurrent/atomic/AtomicBoolean;


# direct methods
.method private constructor <init>(J[I)V
    .locals 2

    .line 38
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 32
    new-instance v0, Ljava/util/concurrent/atomic/AtomicBoolean;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Ljava/util/concurrent/atomic/AtomicBoolean;-><init>(Z)V

    iput-object v0, p0, Lorg/telegram/ui/Components/RLottieNative;->mRecycled:Ljava/util/concurrent/atomic/AtomicBoolean;

    .line 39
    iput-wide p1, p0, Lorg/telegram/ui/Components/RLottieNative;->mNativePtr:J

    .line 40
    iput-object p3, p0, Lorg/telegram/ui/Components/RLottieNative;->mMetaData:[I

    return-void
.end method

.method private checkNotRecycled()V
    .locals 0

    .line 198
    iget-object p0, p0, Lorg/telegram/ui/Components/RLottieNative;->mRecycled:Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-virtual {p0}, Ljava/util/concurrent/atomic/AtomicBoolean;->get()Z

    move-result p0

    if-nez p0, :cond_0

    return-void

    .line 199
    :cond_0
    const-string p0, "Called method on a recycled RLottie instance"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method

.method private static create(Ljava/lang/String;Ljava/lang/String;[I[IILjava/util/Map;)J
    .locals 9
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            "[I[II",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;)J"
        }
    .end annotation

    .line 204
    const-string v0, "RLottieNative#create"

    invoke-static {v0}, Landroid/os/Trace;->beginSection(Ljava/lang/String;)V

    const/4 v0, 0x0

    if-nez p5, :cond_0

    move-object v7, v0

    goto :goto_0

    .line 206
    :cond_0
    :try_start_0
    invoke-interface {p5}, Ljava/util/Map;->keySet()Ljava/util/Set;

    move-result-object v1

    const/4 v2, 0x0

    new-array v2, v2, [Ljava/lang/String;

    invoke-interface {v1, v2}, Ljava/util/Set;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;

    move-result-object v1

    check-cast v1, [Ljava/lang/String;

    move-object v7, v1

    :goto_0
    if-nez p5, :cond_1

    :goto_1
    move-object v2, p0

    move-object v3, p1

    move-object v4, p2

    move-object v5, p3

    move v6, p4

    move-object v8, v0

    goto :goto_2

    .line 207
    :cond_1
    invoke-static {v7, p5}, Lorg/telegram/ui/Components/RLottieNative;->layerNamesToColors([Ljava/lang/String;Ljava/util/Map;)[I

    move-result-object v0

    goto :goto_1

    .line 208
    :goto_2
    invoke-static/range {v2 .. v8}, Lorg/telegram/ui/Components/RLottieNative;->nCreate(Ljava/lang/String;Ljava/lang/String;[I[II[Ljava/lang/String;[I)J

    move-result-wide p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 210
    invoke-static {}, Landroid/os/Trace;->endSection()V

    return-wide p0

    :catchall_0
    move-exception v0

    move-object p0, v0

    invoke-static {}, Landroid/os/Trace;->endSection()V

    .line 211
    throw p0
.end method

.method public static createFromFile(Ljava/lang/String;)Lorg/telegram/ui/Components/RLottieNative;
    .locals 6

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v1, 0x0

    const/4 v2, 0x0

    const/4 v3, 0x0

    move-object v0, p0

    .line 73
    invoke-static/range {v0 .. v5}, Lorg/telegram/ui/Components/RLottieNative;->createFromFile(Ljava/lang/String;Ljava/lang/String;[I[IILjava/util/Map;)Lorg/telegram/ui/Components/RLottieNative;

    move-result-object p0

    return-object p0
.end method

.method public static createFromFile(Ljava/lang/String;Ljava/lang/String;[I[IILjava/util/Map;)Lorg/telegram/ui/Components/RLottieNative;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            "[I[II",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;)",
            "Lorg/telegram/ui/Components/RLottieNative;"
        }
    .end annotation

    const/4 v0, 0x3

    .line 61
    new-array v3, v0, [I

    move-object v1, p0

    move-object v2, p1

    move-object v4, p3

    move v5, p4

    move-object v6, p5

    .line 62
    invoke-static/range {v1 .. v6}, Lorg/telegram/ui/Components/RLottieNative;->create(Ljava/lang/String;Ljava/lang/String;[I[IILjava/util/Map;)J

    move-result-wide p0

    const-wide/16 p3, 0x0

    cmp-long p3, p0, p3

    if-nez p3, :cond_0

    const/4 p0, 0x0

    return-object p0

    :cond_0
    if-eqz p2, :cond_1

    .line 66
    array-length p3, p2

    if-ne p3, v0, :cond_1

    const/4 p3, 0x0

    .line 67
    invoke-static {v3, p3, p2, p3, v0}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 69
    :cond_1
    new-instance p2, Lorg/telegram/ui/Components/RLottieNative;

    invoke-direct {p2, p0, p1, v3}, Lorg/telegram/ui/Components/RLottieNative;-><init>(J[I)V

    return-object p2
.end method

.method public static createFromRawJson(Ljava/lang/String;)Lorg/telegram/ui/Components/RLottieNative;
    .locals 1

    const/4 v0, 0x0

    .line 105
    invoke-static {p0, v0, v0, v0}, Lorg/telegram/ui/Components/RLottieNative;->createFromRawJson(Ljava/lang/String;[I[ILjava/util/Map;)Lorg/telegram/ui/Components/RLottieNative;

    move-result-object p0

    return-object p0
.end method

.method public static createFromRawJson(Ljava/lang/String;[I)Lorg/telegram/ui/Components/RLottieNative;
    .locals 1

    const/4 v0, 0x0

    .line 109
    invoke-static {p0, p1, v0, v0}, Lorg/telegram/ui/Components/RLottieNative;->createFromRawJson(Ljava/lang/String;[I[ILjava/util/Map;)Lorg/telegram/ui/Components/RLottieNative;

    move-result-object p0

    return-object p0
.end method

.method public static createFromRawJson(Ljava/lang/String;[I[ILjava/util/Map;)Lorg/telegram/ui/Components/RLottieNative;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "[I[I",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;)",
            "Lorg/telegram/ui/Components/RLottieNative;"
        }
    .end annotation

    const/4 v0, 0x0

    if-eqz p0, :cond_5

    .line 88
    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_2

    :cond_0
    const/4 v1, 0x3

    .line 91
    new-array v2, v1, [I

    const/4 v3, 0x0

    if-nez p3, :cond_1

    move-object v4, v0

    goto :goto_0

    .line 92
    :cond_1
    invoke-interface {p3}, Ljava/util/Map;->keySet()Ljava/util/Set;

    move-result-object v4

    new-array v5, v3, [Ljava/lang/String;

    invoke-interface {v4, v5}, Ljava/util/Set;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;

    move-result-object v4

    check-cast v4, [Ljava/lang/String;

    :goto_0
    if-nez p3, :cond_2

    move-object p3, v0

    goto :goto_1

    .line 93
    :cond_2
    invoke-static {v4, p3}, Lorg/telegram/ui/Components/RLottieNative;->layerNamesToColors([Ljava/lang/String;Ljava/util/Map;)[I

    move-result-object p3

    .line 94
    :goto_1
    invoke-static {p0, v2, p2, v4, p3}, Lorg/telegram/ui/Components/RLottieNative;->createWithJson(Ljava/lang/String;[I[I[Ljava/lang/String;[I)J

    move-result-wide p2

    const-wide/16 v4, 0x0

    cmp-long p0, p2, v4

    if-nez p0, :cond_3

    return-object v0

    :cond_3
    if-eqz p1, :cond_4

    .line 98
    array-length p0, p1

    if-ne p0, v1, :cond_4

    .line 99
    invoke-static {v2, v3, p1, v3, v1}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 101
    :cond_4
    new-instance p0, Lorg/telegram/ui/Components/RLottieNative;

    invoke-direct {p0, p2, p3, v2}, Lorg/telegram/ui/Components/RLottieNative;-><init>(J[I)V

    return-object p0

    :cond_5
    :goto_2
    return-object v0
.end method

.method private static createWithJson(Ljava/lang/String;[I[I[Ljava/lang/String;[I)J
    .locals 1

    .line 219
    const-string v0, "RLottieNative#createWithJson"

    invoke-static {v0}, Landroid/os/Trace;->beginSection(Ljava/lang/String;)V

    .line 221
    :try_start_0
    invoke-static {p0, p1, p2, p3, p4}, Lorg/telegram/ui/Components/RLottieNative;->nCreateWithJson(Ljava/lang/String;[I[I[Ljava/lang/String;[I)J

    move-result-wide p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 223
    invoke-static {}, Landroid/os/Trace;->endSection()V

    return-wide p0

    :catchall_0
    move-exception p0

    invoke-static {}, Landroid/os/Trace;->endSection()V

    .line 224
    throw p0
.end method

.method private static destroy(J)V
    .locals 1

    .line 253
    const-string v0, "RLottieNative#destroy"

    invoke-static {v0}, Landroid/os/Trace;->beginSection(Ljava/lang/String;)V

    .line 255
    :try_start_0
    invoke-static {p0, p1}, Lorg/telegram/ui/Components/RLottieNative;->nDestroy(J)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 257
    invoke-static {}, Landroid/os/Trace;->endSection()V

    return-void

    :catchall_0
    move-exception p0

    invoke-static {}, Landroid/os/Trace;->endSection()V

    .line 258
    throw p0
.end method

.method public static getDuration(Ljava/lang/String;Ljava/lang/String;)D
    .locals 6

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v2, 0x0

    const/4 v3, 0x0

    move-object v0, p0

    move-object v1, p1

    .line 283
    invoke-static/range {v0 .. v5}, Lorg/telegram/ui/Components/RLottieNative;->createFromFile(Ljava/lang/String;Ljava/lang/String;[I[IILjava/util/Map;)Lorg/telegram/ui/Components/RLottieNative;

    move-result-object p0

    if-eqz p0, :cond_0

    .line 285
    invoke-virtual {p0}, Lorg/telegram/ui/Components/RLottieNative;->getFrameCount()I

    move-result p1

    .line 286
    invoke-virtual {p0}, Lorg/telegram/ui/Components/RLottieNative;->getFps()I

    move-result v0

    .line 287
    invoke-virtual {p0}, Lorg/telegram/ui/Components/RLottieNative;->recycle()V

    int-to-double p0, p1

    int-to-double v0, v0

    div-double/2addr p0, v0

    return-wide p0

    :cond_0
    const-wide/16 p0, 0x0

    return-wide p0
.end method

.method private static getFrame(JILandroid/graphics/Bitmap;Z)I
    .locals 1

    .line 232
    const-string v0, "RLottieNative#getFrame"

    invoke-static {v0}, Landroid/os/Trace;->beginSection(Ljava/lang/String;)V

    .line 234
    :try_start_0
    invoke-static {p0, p1, p2, p3, p4}, Lorg/telegram/ui/Components/RLottieNative;->nGetFrame(JILandroid/graphics/Bitmap;Z)I

    move-result p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 236
    invoke-static {}, Landroid/os/Trace;->endSection()V

    return p0

    :catchall_0
    move-exception p0

    invoke-static {}, Landroid/os/Trace;->endSection()V

    .line 237
    throw p0
.end method

.method public static getFramesCount(Ljava/lang/String;Ljava/lang/String;)J
    .locals 6

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v2, 0x0

    const/4 v3, 0x0

    move-object v0, p0

    move-object v1, p1

    .line 270
    invoke-static/range {v0 .. v5}, Lorg/telegram/ui/Components/RLottieNative;->createFromFile(Ljava/lang/String;Ljava/lang/String;[I[IILjava/util/Map;)Lorg/telegram/ui/Components/RLottieNative;

    move-result-object p0

    if-eqz p0, :cond_0

    .line 272
    invoke-virtual {p0}, Lorg/telegram/ui/Components/RLottieNative;->getFrameCount()I

    move-result p1

    .line 273
    invoke-virtual {p0}, Lorg/telegram/ui/Components/RLottieNative;->recycle()V

    int-to-long p0, p1

    return-wide p0

    :cond_0
    const-wide/16 p0, 0x0

    return-wide p0
.end method

.method private static layerNamesToColors([Ljava/lang/String;Ljava/util/Map;)[I
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "([",
            "Ljava/lang/String;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;)[I"
        }
    .end annotation

    .line 241
    array-length v0, p0

    new-array v0, v0, [I

    const/4 v1, 0x0

    .line 242
    :goto_0
    array-length v2, p0

    if-ge v1, v2, :cond_0

    .line 243
    aget-object v2, p0, v1

    invoke-interface {p1, v2}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Integer;

    invoke-virtual {v2}, Ljava/lang/Integer;->intValue()I

    move-result v2

    aput v2, v0, v1

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_0
    return-object v0
.end method

.method private static native nCreate(Ljava/lang/String;Ljava/lang/String;[I[II[Ljava/lang/String;[I)J
.end method

.method private static native nCreateWithJson(Ljava/lang/String;[I[I[Ljava/lang/String;[I)J
.end method

.method private static native nDestroy(J)V
.end method

.method private static native nGetFrame(JILandroid/graphics/Bitmap;Z)I
.end method


# virtual methods
.method public finalize()V
    .locals 1

    .line 185
    :try_start_0
    iget-object v0, p0, Lorg/telegram/ui/Components/RLottieNative;->mRecycled:Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicBoolean;->get()Z

    move-result v0

    if-nez v0, :cond_0

    .line 186
    invoke-virtual {p0}, Lorg/telegram/ui/Components/RLottieNative;->recycle()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception v0

    goto :goto_1

    .line 189
    :cond_0
    :goto_0
    invoke-super {p0}, Ljava/lang/Object;->finalize()V

    return-void

    :goto_1
    invoke-super {p0}, Ljava/lang/Object;->finalize()V

    .line 190
    throw v0
.end method

.method public getFps()I
    .locals 1

    .line 144
    iget-object p0, p0, Lorg/telegram/ui/Components/RLottieNative;->mMetaData:[I

    const/4 v0, 0x1

    aget p0, p0, v0

    return p0
.end method

.method public getFrame(ILandroid/graphics/Bitmap;Z)I
    .locals 2

    .line 129
    invoke-direct {p0}, Lorg/telegram/ui/Components/RLottieNative;->checkNotRecycled()V

    .line 130
    iget-wide v0, p0, Lorg/telegram/ui/Components/RLottieNative;->mNativePtr:J

    invoke-static {v0, v1, p1, p2, p3}, Lorg/telegram/ui/Components/RLottieNative;->getFrame(JILandroid/graphics/Bitmap;Z)I

    move-result p0

    return p0
.end method

.method public getFrameCount()I
    .locals 1

    .line 139
    iget-object p0, p0, Lorg/telegram/ui/Components/RLottieNative;->mMetaData:[I

    const/4 v0, 0x0

    aget p0, p0, v0

    return p0
.end method

.method public recycle()V
    .locals 4

    .line 169
    iget-object v0, p0, Lorg/telegram/ui/Components/RLottieNative;->mRecycled:Ljava/util/concurrent/atomic/AtomicBoolean;

    const/4 v1, 0x0

    const/4 v2, 0x1

    invoke-virtual {v0, v1, v2}, Ljava/util/concurrent/atomic/AtomicBoolean;->compareAndSet(ZZ)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 170
    iget-wide v0, p0, Lorg/telegram/ui/Components/RLottieNative;->mNativePtr:J

    const-wide/16 v2, 0x0

    .line 171
    iput-wide v2, p0, Lorg/telegram/ui/Components/RLottieNative;->mNativePtr:J

    cmp-long p0, v0, v2

    if-eqz p0, :cond_0

    .line 173
    invoke-static {v0, v1}, Lorg/telegram/ui/Components/RLottieNative;->destroy(J)V

    :cond_0
    return-void
.end method
