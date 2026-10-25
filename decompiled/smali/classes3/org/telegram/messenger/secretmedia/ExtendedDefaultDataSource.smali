.class public final Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/media3/datasource/DataSource;


# static fields
.field private static final SCHEME_ASSET:Ljava/lang/String; = "asset"

.field private static final SCHEME_CONTENT:Ljava/lang/String; = "content"

.field private static final SCHEME_RAW:Ljava/lang/String; = "rawresource"

.field private static final SCHEME_RTMP:Ljava/lang/String; = "rtmp"

.field private static final TAG:Ljava/lang/String; = "ExtendedDefaultDataSource"


# instance fields
.field private assetDataSource:Landroidx/media3/datasource/DataSource;

.field private final baseDataSource:Landroidx/media3/datasource/DataSource;

.field private contentDataSource:Landroidx/media3/datasource/DataSource;

.field private final context:Landroid/content/Context;

.field private dataSchemeDataSource:Landroidx/media3/datasource/DataSource;

.field private dataSource:Landroidx/media3/datasource/DataSource;

.field private encryptedFileDataSource:Landroidx/media3/datasource/DataSource;

.field private fileDataSource:Landroidx/media3/datasource/DataSource;

.field private final mtprotoUris:Landroid/util/LongSparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/LongSparseArray<",
            "Landroid/net/Uri;",
            ">;"
        }
    .end annotation
.end field

.field private rawResourceDataSource:Landroidx/media3/datasource/DataSource;

.field private rtmpDataSource:Landroidx/media3/datasource/DataSource;

.field private streamLoadOperation:Lorg/telegram/messenger/FileStreamLoadOperation;

.field private final transferListeners:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Landroidx/media3/datasource/TransferListener;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroidx/media3/datasource/DataSource;Landroid/util/LongSparseArray;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            "Landroidx/media3/datasource/DataSource;",
            "Landroid/util/LongSparseArray<",
            "Landroid/net/Uri;",
            ">;)V"
        }
    .end annotation

    .line 124
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 125
    invoke-virtual {p1}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object p1

    iput-object p1, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->context:Landroid/content/Context;

    .line 126
    invoke-static {p2}, Landroidx/media3/common/util/Assertions;->checkNotNull(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroidx/media3/datasource/DataSource;

    iput-object p1, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->baseDataSource:Landroidx/media3/datasource/DataSource;

    .line 127
    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    iput-object p1, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->transferListeners:Ljava/util/List;

    .line 128
    iput-object p3, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->mtprotoUris:Landroid/util/LongSparseArray;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroidx/media3/datasource/TransferListener;Landroidx/media3/datasource/DataSource;Landroid/util/LongSparseArray;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            "Landroidx/media3/datasource/TransferListener;",
            "Landroidx/media3/datasource/DataSource;",
            "Landroid/util/LongSparseArray<",
            "Landroid/net/Uri;",
            ">;)V"
        }
    .end annotation

    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .line 147
    invoke-direct {p0, p1, p3, p4}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;-><init>(Landroid/content/Context;Landroidx/media3/datasource/DataSource;Landroid/util/LongSparseArray;)V

    if-eqz p2, :cond_0

    .line 149
    iget-object p0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->transferListeners:Ljava/util/List;

    invoke-interface {p0, p2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 150
    invoke-interface {p3, p2}, Landroidx/media3/datasource/DataSource;->addTransferListener(Landroidx/media3/datasource/TransferListener;)V

    :cond_0
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Ljava/lang/String;IIZ)V
    .locals 1

    .line 104
    new-instance v0, Landroidx/media3/datasource/DefaultHttpDataSource$Factory;

    invoke-direct {v0}, Landroidx/media3/datasource/DefaultHttpDataSource$Factory;-><init>()V

    .line 107
    invoke-virtual {v0, p2}, Landroidx/media3/datasource/DefaultHttpDataSource$Factory;->setUserAgent(Ljava/lang/String;)Landroidx/media3/datasource/DefaultHttpDataSource$Factory;

    move-result-object p2

    .line 108
    invoke-virtual {p2, p3}, Landroidx/media3/datasource/DefaultHttpDataSource$Factory;->setConnectTimeoutMs(I)Landroidx/media3/datasource/DefaultHttpDataSource$Factory;

    move-result-object p2

    .line 109
    invoke-virtual {p2, p4}, Landroidx/media3/datasource/DefaultHttpDataSource$Factory;->setReadTimeoutMs(I)Landroidx/media3/datasource/DefaultHttpDataSource$Factory;

    move-result-object p2

    .line 110
    invoke-virtual {p2, p5}, Landroidx/media3/datasource/DefaultHttpDataSource$Factory;->setAllowCrossProtocolRedirects(Z)Landroidx/media3/datasource/DefaultHttpDataSource$Factory;

    move-result-object p2

    .line 111
    invoke-virtual {p2}, Landroidx/media3/datasource/DefaultHttpDataSource$Factory;->createDataSource()Landroidx/media3/datasource/DefaultHttpDataSource;

    move-result-object p2

    const/4 p3, 0x0

    .line 104
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;-><init>(Landroid/content/Context;Landroidx/media3/datasource/DataSource;Landroid/util/LongSparseArray;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Ljava/lang/String;Z)V
    .locals 6

    const/16 v3, 0x1f40

    const/16 v4, 0x1f40

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move v5, p3

    .line 78
    invoke-direct/range {v0 .. v5}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;-><init>(Landroid/content/Context;Ljava/lang/String;IIZ)V

    return-void
.end method

.method private addListenersToDataSource(Landroidx/media3/datasource/DataSource;)V
    .locals 2

    const/4 v0, 0x0

    .line 313
    :goto_0
    iget-object v1, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->transferListeners:Ljava/util/List;

    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v1

    if-ge v0, v1, :cond_0

    .line 314
    iget-object v1, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->transferListeners:Ljava/util/List;

    invoke-interface {v1, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroidx/media3/datasource/TransferListener;

    invoke-interface {p1, v1}, Landroidx/media3/datasource/DataSource;->addTransferListener(Landroidx/media3/datasource/TransferListener;)V

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_0
    return-void
.end method

.method private getAssetDataSource()Landroidx/media3/datasource/DataSource;
    .locals 2

    .line 242
    iget-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->assetDataSource:Landroidx/media3/datasource/DataSource;

    if-nez v0, :cond_0

    .line 243
    new-instance v0, Landroidx/media3/datasource/AssetDataSource;

    iget-object v1, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->context:Landroid/content/Context;

    invoke-direct {v0, v1}, Landroidx/media3/datasource/AssetDataSource;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->assetDataSource:Landroidx/media3/datasource/DataSource;

    .line 244
    invoke-direct {p0, v0}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->addListenersToDataSource(Landroidx/media3/datasource/DataSource;)V

    .line 246
    :cond_0
    iget-object p0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->assetDataSource:Landroidx/media3/datasource/DataSource;

    return-object p0
.end method

.method private getContentDataSource()Landroidx/media3/datasource/DataSource;
    .locals 2

    .line 267
    iget-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->contentDataSource:Landroidx/media3/datasource/DataSource;

    if-nez v0, :cond_0

    .line 268
    new-instance v0, Landroidx/media3/datasource/ContentDataSource;

    iget-object v1, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->context:Landroid/content/Context;

    invoke-direct {v0, v1}, Landroidx/media3/datasource/ContentDataSource;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->contentDataSource:Landroidx/media3/datasource/DataSource;

    .line 269
    invoke-direct {p0, v0}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->addListenersToDataSource(Landroidx/media3/datasource/DataSource;)V

    .line 271
    :cond_0
    iget-object p0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->contentDataSource:Landroidx/media3/datasource/DataSource;

    return-object p0
.end method

.method private getDataSchemeDataSource()Landroidx/media3/datasource/DataSource;
    .locals 1

    .line 297
    iget-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->dataSchemeDataSource:Landroidx/media3/datasource/DataSource;

    if-nez v0, :cond_0

    .line 298
    new-instance v0, Landroidx/media3/datasource/DataSchemeDataSource;

    invoke-direct {v0}, Landroidx/media3/datasource/DataSchemeDataSource;-><init>()V

    iput-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->dataSchemeDataSource:Landroidx/media3/datasource/DataSource;

    .line 299
    invoke-direct {p0, v0}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->addListenersToDataSource(Landroidx/media3/datasource/DataSource;)V

    .line 301
    :cond_0
    iget-object p0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->dataSchemeDataSource:Landroidx/media3/datasource/DataSource;

    return-object p0
.end method

.method private getEncryptedFileDataSource()Landroidx/media3/datasource/DataSource;
    .locals 1

    .line 250
    iget-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->encryptedFileDataSource:Landroidx/media3/datasource/DataSource;

    if-nez v0, :cond_0

    .line 251
    new-instance v0, Lorg/telegram/messenger/secretmedia/EncryptedFileDataSource;

    invoke-direct {v0}, Lorg/telegram/messenger/secretmedia/EncryptedFileDataSource;-><init>()V

    iput-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->encryptedFileDataSource:Landroidx/media3/datasource/DataSource;

    .line 252
    invoke-direct {p0, v0}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->addListenersToDataSource(Landroidx/media3/datasource/DataSource;)V

    .line 254
    :cond_0
    iget-object p0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->encryptedFileDataSource:Landroidx/media3/datasource/DataSource;

    return-object p0
.end method

.method private getFileDataSource()Landroidx/media3/datasource/DataSource;
    .locals 1

    .line 234
    iget-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->fileDataSource:Landroidx/media3/datasource/DataSource;

    if-nez v0, :cond_0

    .line 235
    new-instance v0, Landroidx/media3/datasource/FileDataSource;

    invoke-direct {v0}, Landroidx/media3/datasource/FileDataSource;-><init>()V

    iput-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->fileDataSource:Landroidx/media3/datasource/DataSource;

    .line 236
    invoke-direct {p0, v0}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->addListenersToDataSource(Landroidx/media3/datasource/DataSource;)V

    .line 238
    :cond_0
    iget-object p0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->fileDataSource:Landroidx/media3/datasource/DataSource;

    return-object p0
.end method

.method private getRawResourceDataSource()Landroidx/media3/datasource/DataSource;
    .locals 2

    .line 305
    iget-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->rawResourceDataSource:Landroidx/media3/datasource/DataSource;

    if-nez v0, :cond_0

    .line 306
    new-instance v0, Landroidx/media3/datasource/RawResourceDataSource;

    iget-object v1, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->context:Landroid/content/Context;

    invoke-direct {v0, v1}, Landroidx/media3/datasource/RawResourceDataSource;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->rawResourceDataSource:Landroidx/media3/datasource/DataSource;

    .line 307
    invoke-direct {p0, v0}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->addListenersToDataSource(Landroidx/media3/datasource/DataSource;)V

    .line 309
    :cond_0
    iget-object p0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->rawResourceDataSource:Landroidx/media3/datasource/DataSource;

    return-object p0
.end method

.method private getRtmpDataSource()Landroidx/media3/datasource/DataSource;
    .locals 2

    .line 275
    iget-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->rtmpDataSource:Landroidx/media3/datasource/DataSource;

    if-nez v0, :cond_0

    const/4 v0, 0x0

    .line 278
    :try_start_0
    const-string v1, "com.google.android.exoplayer2.ext.rtmp.RtmpDataSource"

    invoke-static {v1}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v1

    .line 279
    invoke-virtual {v1, v0}, Ljava/lang/Class;->getConstructor([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;

    move-result-object v1

    invoke-virtual {v1, v0}, Ljava/lang/reflect/Constructor;->newInstance([Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroidx/media3/datasource/DataSource;

    iput-object v1, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->rtmpDataSource:Landroidx/media3/datasource/DataSource;

    .line 281
    invoke-direct {p0, v1}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->addListenersToDataSource(Landroidx/media3/datasource/DataSource;)V
    :try_end_0
    .catch Ljava/lang/ClassNotFoundException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p0

    .line 287
    const-string v1, "Error instantiating RTMP extension"

    invoke-static {v1, p0}, Lorg/mvel2/util/Make$Map$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-object v0

    .line 284
    :catch_1
    const-string v0, "ExtendedDefaultDataSource"

    const-string v1, "Attempting to play RTMP stream without depending on the RTMP extension"

    invoke-static {v0, v1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 289
    :goto_0
    iget-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->rtmpDataSource:Landroidx/media3/datasource/DataSource;

    if-nez v0, :cond_0

    .line 290
    iget-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->baseDataSource:Landroidx/media3/datasource/DataSource;

    iput-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->rtmpDataSource:Landroidx/media3/datasource/DataSource;

    .line 293
    :cond_0
    iget-object p0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->rtmpDataSource:Landroidx/media3/datasource/DataSource;

    return-object p0
.end method

.method private getStreamDataSource()Landroidx/media3/datasource/DataSource;
    .locals 1

    .line 259
    iget-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->streamLoadOperation:Lorg/telegram/messenger/FileStreamLoadOperation;

    if-nez v0, :cond_0

    .line 260
    new-instance v0, Lorg/telegram/messenger/FileStreamLoadOperation;

    invoke-direct {v0}, Lorg/telegram/messenger/FileStreamLoadOperation;-><init>()V

    iput-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->streamLoadOperation:Lorg/telegram/messenger/FileStreamLoadOperation;

    .line 261
    invoke-direct {p0, v0}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->addListenersToDataSource(Landroidx/media3/datasource/DataSource;)V

    .line 263
    :cond_0
    iget-object p0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->streamLoadOperation:Lorg/telegram/messenger/FileStreamLoadOperation;

    return-object p0
.end method

.method private maybeAddListenerToDataSource(Landroidx/media3/datasource/DataSource;Landroidx/media3/datasource/TransferListener;)V
    .locals 0

    if-eqz p1, :cond_0

    .line 321
    invoke-interface {p1, p2}, Landroidx/media3/datasource/DataSource;->addTransferListener(Landroidx/media3/datasource/TransferListener;)V

    :cond_0
    return-void
.end method


# virtual methods
.method public addTransferListener(Landroidx/media3/datasource/TransferListener;)V
    .locals 1

    .line 156
    iget-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->baseDataSource:Landroidx/media3/datasource/DataSource;

    invoke-interface {v0, p1}, Landroidx/media3/datasource/DataSource;->addTransferListener(Landroidx/media3/datasource/TransferListener;)V

    .line 157
    iget-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->transferListeners:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 158
    iget-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->fileDataSource:Landroidx/media3/datasource/DataSource;

    invoke-direct {p0, v0, p1}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->maybeAddListenerToDataSource(Landroidx/media3/datasource/DataSource;Landroidx/media3/datasource/TransferListener;)V

    .line 159
    iget-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->assetDataSource:Landroidx/media3/datasource/DataSource;

    invoke-direct {p0, v0, p1}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->maybeAddListenerToDataSource(Landroidx/media3/datasource/DataSource;Landroidx/media3/datasource/TransferListener;)V

    .line 160
    iget-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->contentDataSource:Landroidx/media3/datasource/DataSource;

    invoke-direct {p0, v0, p1}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->maybeAddListenerToDataSource(Landroidx/media3/datasource/DataSource;Landroidx/media3/datasource/TransferListener;)V

    .line 161
    iget-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->rtmpDataSource:Landroidx/media3/datasource/DataSource;

    invoke-direct {p0, v0, p1}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->maybeAddListenerToDataSource(Landroidx/media3/datasource/DataSource;Landroidx/media3/datasource/TransferListener;)V

    .line 162
    iget-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->dataSchemeDataSource:Landroidx/media3/datasource/DataSource;

    invoke-direct {p0, v0, p1}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->maybeAddListenerToDataSource(Landroidx/media3/datasource/DataSource;Landroidx/media3/datasource/TransferListener;)V

    .line 163
    iget-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->rawResourceDataSource:Landroidx/media3/datasource/DataSource;

    invoke-direct {p0, v0, p1}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->maybeAddListenerToDataSource(Landroidx/media3/datasource/DataSource;Landroidx/media3/datasource/TransferListener;)V

    return-void
.end method

.method public close()V
    .locals 2

    .line 224
    iget-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->dataSource:Landroidx/media3/datasource/DataSource;

    if-eqz v0, :cond_0

    const/4 v1, 0x0

    .line 226
    :try_start_0
    invoke-interface {v0}, Landroidx/media3/datasource/DataSource;->close()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 228
    iput-object v1, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->dataSource:Landroidx/media3/datasource/DataSource;

    return-void

    :catchall_0
    move-exception v0

    iput-object v1, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->dataSource:Landroidx/media3/datasource/DataSource;

    .line 229
    throw v0

    :cond_0
    return-void
.end method

.method public getResponseHeaders()Ljava/util/Map;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;>;"
        }
    .end annotation

    .line 219
    iget-object p0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->dataSource:Landroidx/media3/datasource/DataSource;

    if-nez p0, :cond_0

    sget-object p0, Ljava/util/Collections;->EMPTY_MAP:Ljava/util/Map;

    return-object p0

    :cond_0
    invoke-interface {p0}, Landroidx/media3/datasource/DataSource;->getResponseHeaders()Ljava/util/Map;

    move-result-object p0

    return-object p0
.end method

.method public getUri()Landroid/net/Uri;
    .locals 0

    .line 214
    iget-object p0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->dataSource:Landroidx/media3/datasource/DataSource;

    if-nez p0, :cond_0

    const/4 p0, 0x0

    return-object p0

    :cond_0
    invoke-interface {p0}, Landroidx/media3/datasource/DataSource;->getUri()Landroid/net/Uri;

    move-result-object p0

    return-object p0
.end method

.method public open(Landroidx/media3/datasource/DataSpec;)J
    .locals 3

    .line 168
    iget-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->dataSource:Landroidx/media3/datasource/DataSource;

    if-nez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    invoke-static {v0}, Landroidx/media3/common/util/Assertions;->checkState(Z)V

    .line 169
    iget-object v0, p1, Landroidx/media3/datasource/DataSpec;->uri:Landroid/net/Uri;

    .line 170
    const-string/jumbo v1, "mtproto"

    invoke-virtual {v0}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 171
    iget-object v0, p1, Landroidx/media3/datasource/DataSpec;->uri:Landroid/net/Uri;

    invoke-virtual {v0}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v0

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v0

    .line 172
    iget-object v2, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->mtprotoUris:Landroid/util/LongSparseArray;

    invoke-virtual {v2, v0, v1}, Landroid/util/LongSparseArray;->get(J)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/net/Uri;

    .line 173
    invoke-virtual {p1}, Landroidx/media3/datasource/DataSpec;->buildUpon()Landroidx/media3/datasource/DataSpec$Builder;

    move-result-object p1

    invoke-virtual {p1, v0}, Landroidx/media3/datasource/DataSpec$Builder;->setUri(Landroid/net/Uri;)Landroidx/media3/datasource/DataSpec$Builder;

    move-result-object p1

    invoke-virtual {p1}, Landroidx/media3/datasource/DataSpec$Builder;->build()Landroidx/media3/datasource/DataSpec;

    move-result-object p1

    .line 176
    :cond_1
    invoke-virtual {v0}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object v1

    .line 177
    invoke-static {v0}, Landroidx/media3/common/util/Util;->isLocalFileUri(Landroid/net/Uri;)Z

    move-result v2

    if-eqz v2, :cond_4

    .line 178
    invoke-virtual {v0}, Landroid/net/Uri;->getPath()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_2

    .line 179
    const-string v2, "/android_asset/"

    invoke-virtual {v1, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 180
    invoke-direct {p0}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->getAssetDataSource()Landroidx/media3/datasource/DataSource;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->dataSource:Landroidx/media3/datasource/DataSource;

    goto/16 :goto_1

    .line 182
    :cond_2
    invoke-virtual {v0}, Landroid/net/Uri;->getPath()Ljava/lang/String;

    move-result-object v0

    const-string v1, ".enc"

    invoke-virtual {v0, v1}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_3

    .line 183
    invoke-direct {p0}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->getEncryptedFileDataSource()Landroidx/media3/datasource/DataSource;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->dataSource:Landroidx/media3/datasource/DataSource;

    goto :goto_1

    .line 185
    :cond_3
    invoke-direct {p0}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->getFileDataSource()Landroidx/media3/datasource/DataSource;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->dataSource:Landroidx/media3/datasource/DataSource;

    goto :goto_1

    .line 188
    :cond_4
    const-string/jumbo v0, "tg"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_5

    .line 189
    invoke-direct {p0}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->getStreamDataSource()Landroidx/media3/datasource/DataSource;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->dataSource:Landroidx/media3/datasource/DataSource;

    goto :goto_1

    .line 190
    :cond_5
    const-string v0, "asset"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_6

    .line 191
    invoke-direct {p0}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->getAssetDataSource()Landroidx/media3/datasource/DataSource;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->dataSource:Landroidx/media3/datasource/DataSource;

    goto :goto_1

    .line 192
    :cond_6
    const-string v0, "content"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_7

    .line 193
    invoke-direct {p0}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->getContentDataSource()Landroidx/media3/datasource/DataSource;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->dataSource:Landroidx/media3/datasource/DataSource;

    goto :goto_1

    .line 194
    :cond_7
    const-string/jumbo v0, "rtmp"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_8

    .line 195
    invoke-direct {p0}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->getRtmpDataSource()Landroidx/media3/datasource/DataSource;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->dataSource:Landroidx/media3/datasource/DataSource;

    goto :goto_1

    .line 196
    :cond_8
    const-string v0, "data"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_9

    .line 197
    invoke-direct {p0}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->getDataSchemeDataSource()Landroidx/media3/datasource/DataSource;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->dataSource:Landroidx/media3/datasource/DataSource;

    goto :goto_1

    .line 198
    :cond_9
    const-string/jumbo v0, "rawresource"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_a

    .line 199
    invoke-direct {p0}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->getRawResourceDataSource()Landroidx/media3/datasource/DataSource;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->dataSource:Landroidx/media3/datasource/DataSource;

    goto :goto_1

    .line 201
    :cond_a
    iget-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->baseDataSource:Landroidx/media3/datasource/DataSource;

    iput-object v0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->dataSource:Landroidx/media3/datasource/DataSource;

    .line 204
    :goto_1
    iget-object p0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->dataSource:Landroidx/media3/datasource/DataSource;

    invoke-interface {p0, p1}, Landroidx/media3/datasource/DataSource;->open(Landroidx/media3/datasource/DataSpec;)J

    move-result-wide p0

    return-wide p0
.end method

.method public read([BII)I
    .locals 0

    .line 209
    iget-object p0, p0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSource;->dataSource:Landroidx/media3/datasource/DataSource;

    invoke-static {p0}, Landroidx/media3/common/util/Assertions;->checkNotNull(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroidx/media3/datasource/DataSource;

    invoke-interface {p0, p1, p2, p3}, Landroidx/media3/common/DataReader;->read([BII)I

    move-result p0

    return p0
.end method
