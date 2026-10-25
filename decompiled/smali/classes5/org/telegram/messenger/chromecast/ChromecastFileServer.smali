.class public Lorg/telegram/messenger/chromecast/ChromecastFileServer;
.super Lfi/iki/elonen/NanoHTTPD;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/messenger/chromecast/ChromecastFileServer$Range;,
        Lorg/telegram/messenger/chromecast/ChromecastFileServer$DataSourceInputStream;
    }
.end annotation


# static fields
.field public static final ASSET_FALLBACK_FILE:Lorg/telegram/messenger/chromecast/ChromecastMedia;

.field private static final ASSET_FILES:[Lorg/telegram/messenger/chromecast/ChromecastMedia;

.field private static final ASSET_FILES_MAP:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Lorg/telegram/messenger/chromecast/ChromecastMedia;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private final assetDataSourceFactory:Landroidx/media3/datasource/DataSource$Factory;

.field private final castedFiles:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Lorg/telegram/messenger/chromecast/ChromecastMedia;",
            ">;"
        }
    .end annotation
.end field

.field private coverFile:Landroid/util/Pair;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/Pair<",
            "Ljava/lang/String;",
            "Ljava/io/File;",
            ">;"
        }
    .end annotation
.end field

.field private final fileDataSourceFactory:Landroidx/media3/datasource/DataSource$Factory;

.field private final mediaDataSourceFactory:Landroidx/media3/datasource/DataSource$Factory;

.field private final reqId:Ljava/util/concurrent/atomic/AtomicInteger;

.field private started:Z


# direct methods
.method public static synthetic $r8$lambda$RD0N-hA4v7zqKIfRfrKJ6IhXPzA()Landroidx/media3/datasource/DataSource;
    .locals 2

    .line 63
    new-instance v0, Landroidx/media3/datasource/AssetDataSource;

    sget-object v1, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-direct {v0, v1}, Landroidx/media3/datasource/AssetDataSource;-><init>(Landroid/content/Context;)V

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 6

    .line 45
    const-string v0, "file:///android_asset/cast/default.png"

    invoke-static {v0}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v0

    const-string v1, "/assets/default"

    const-string v2, "image/png"

    invoke-static {v0, v1, v2}, Lorg/telegram/messenger/chromecast/ChromecastMedia$Builder;->fromUri(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;)Lorg/telegram/messenger/chromecast/ChromecastMedia$Builder;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/messenger/chromecast/ChromecastMedia$Builder;->build()Lorg/telegram/messenger/chromecast/ChromecastMedia;

    move-result-object v0

    sput-object v0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->ASSET_FALLBACK_FILE:Lorg/telegram/messenger/chromecast/ChromecastMedia;

    .line 47
    filled-new-array {v0}, [Lorg/telegram/messenger/chromecast/ChromecastMedia;

    move-result-object v0

    sput-object v0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->ASSET_FILES:[Lorg/telegram/messenger/chromecast/ChromecastMedia;

    .line 48
    new-instance v1, Ljava/util/HashMap;

    invoke-direct {v1}, Ljava/util/HashMap;-><init>()V

    sput-object v1, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->ASSET_FILES_MAP:Ljava/util/HashMap;

    .line 50
    array-length v1, v0

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v1, :cond_0

    aget-object v3, v0, v2

    .line 51
    sget-object v4, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->ASSET_FILES_MAP:Ljava/util/HashMap;

    iget-object v5, v3, Lorg/telegram/messenger/chromecast/ChromecastMedia;->externalPath:Ljava/lang/String;

    invoke-virtual {v4, v5, v3}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_0
    return-void
.end method

.method public constructor <init>()V
    .locals 3

    const v0, 0xf08a

    .line 61
    invoke-direct {p0, v0}, Lfi/iki/elonen/NanoHTTPD;-><init>(I)V

    .line 68
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->castedFiles:Ljava/util/HashMap;

    const/4 v0, 0x0

    .line 69
    iput-object v0, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->coverFile:Landroid/util/Pair;

    const/4 v0, 0x0

    .line 72
    iput-boolean v0, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->started:Z

    .line 128
    new-instance v0, Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-direct {v0}, Ljava/util/concurrent/atomic/AtomicInteger;-><init>()V

    iput-object v0, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->reqId:Ljava/util/concurrent/atomic/AtomicInteger;

    .line 63
    new-instance v0, Lorg/telegram/messenger/chromecast/ChromecastFileServer$$ExternalSyntheticLambda0;

    invoke-direct {v0}, Lorg/telegram/messenger/chromecast/ChromecastFileServer$$ExternalSyntheticLambda0;-><init>()V

    iput-object v0, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->assetDataSourceFactory:Landroidx/media3/datasource/DataSource$Factory;

    .line 64
    new-instance v0, Landroidx/media3/datasource/FileDataSource$Factory;

    invoke-direct {v0}, Landroidx/media3/datasource/FileDataSource$Factory;-><init>()V

    iput-object v0, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->fileDataSourceFactory:Landroidx/media3/datasource/DataSource$Factory;

    .line 65
    new-instance v0, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;

    sget-object v1, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    const-string v2, "Mozilla/5.0 (X11; Linux x86_64; rv:10.0) Gecko/20150101 Firefox/47.0 (Chrome)"

    invoke-direct {v0, v1, v2}, Lorg/telegram/messenger/secretmedia/ExtendedDefaultDataSourceFactory;-><init>(Landroid/content/Context;Ljava/lang/String;)V

    iput-object v0, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->mediaDataSourceFactory:Landroidx/media3/datasource/DataSource$Factory;

    return-void
.end method

.method private static addCorsHeaders(Lfi/iki/elonen/NanoHTTPD$Response;)Lfi/iki/elonen/NanoHTTPD$Response;
    .locals 3

    .line 291
    const-string v0, "Access-Control-Allow-Origin"

    const-string v1, "*"

    invoke-virtual {p0, v0, v1}, Lfi/iki/elonen/NanoHTTPD$Response;->addHeader(Ljava/lang/String;Ljava/lang/String;)V

    .line 292
    const-string v0, "Access-Control-Max-Age"

    const-string v2, "3628800"

    invoke-virtual {p0, v0, v2}, Lfi/iki/elonen/NanoHTTPD$Response;->addHeader(Ljava/lang/String;Ljava/lang/String;)V

    .line 293
    const-string v0, "Access-Control-Allow-Methods"

    invoke-virtual {p0, v0, v1}, Lfi/iki/elonen/NanoHTTPD$Response;->addHeader(Ljava/lang/String;Ljava/lang/String;)V

    .line 294
    const-string v0, "Access-Control-Allow-Headers"

    invoke-virtual {p0, v0, v1}, Lfi/iki/elonen/NanoHTTPD$Response;->addHeader(Ljava/lang/String;Ljava/lang/String;)V

    return-object p0
.end method

.method private check()V
    .locals 2

    .line 111
    iget-object v0, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->castedFiles:Ljava/util/HashMap;

    invoke-virtual {v0}, Ljava/util/HashMap;->isEmpty()Z

    move-result v0

    .line 117
    iget-boolean v1, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->started:Z

    if-eqz v0, :cond_0

    if-eqz v1, :cond_1

    .line 113
    invoke-virtual {p0}, Lfi/iki/elonen/NanoHTTPD;->stop()V

    const/4 v0, 0x0

    .line 114
    iput-boolean v0, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->started:Z

    return-void

    :cond_0
    if-nez v1, :cond_1

    const/16 v0, 0x1388

    const/4 v1, 0x1

    .line 119
    :try_start_0
    invoke-virtual {p0, v0, v1}, Lfi/iki/elonen/NanoHTTPD;->start(IZ)V

    .line 120
    iput-boolean v1, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->started:Z
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 122
    invoke-static {p0}, Lokhttp3/HttpUrl$$ExternalSyntheticBUOutline0;->m(Ljava/lang/Throwable;)V

    :cond_1
    return-void
.end method

.method private static fixHlsManifest(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    .line 300
    const-string v0, "/mtproto_"

    invoke-static {p1, v0}, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->getUrlToSource(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    const-string v0, "mtproto:"

    invoke-virtual {p0, v0, p1}, Ljava/lang/String;->replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static formatIp4(I)Ljava/lang/String;
    .locals 3

    .line 346
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    and-int/lit16 v1, p0, 0xff

    invoke-static {v1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/16 v1, 0x2e

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    shr-int/lit8 v2, p0, 0x8

    and-int/lit16 v2, v2, 0xff

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    shr-int/lit8 v2, p0, 0x10

    and-int/lit16 v2, v2, 0xff

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    shr-int/lit8 p0, p0, 0x18

    and-int/lit16 p0, p0, 0xff

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private getDataSourceFactory(Lorg/telegram/messenger/chromecast/ChromecastMedia;)Landroidx/media3/datasource/DataSource$Factory;
    .locals 2

    .line 273
    iget-object v0, p1, Lorg/telegram/messenger/chromecast/ChromecastMedia;->internalUri:Landroid/net/Uri;

    invoke-virtual {v0}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v0

    .line 275
    const-string v1, "file://"

    invoke-virtual {v0, v1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 276
    iget-object p1, p1, Lorg/telegram/messenger/chromecast/ChromecastMedia;->internalUri:Landroid/net/Uri;

    invoke-virtual {p1}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object p1

    const-string v0, "file:///android_asset/"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p1

    if-eqz p1, :cond_0

    .line 277
    iget-object p0, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->assetDataSourceFactory:Landroidx/media3/datasource/DataSource$Factory;

    return-object p0

    .line 280
    :cond_0
    iget-object p0, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->fileDataSourceFactory:Landroidx/media3/datasource/DataSource$Factory;

    return-object p0

    .line 283
    :cond_1
    iget-object p0, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->mediaDataSourceFactory:Landroidx/media3/datasource/DataSource$Factory;

    return-object p0
.end method

.method private getFile(Ljava/lang/String;)Lorg/telegram/messenger/chromecast/ChromecastMedia;
    .locals 1

    .line 265
    sget-object v0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->ASSET_FILES_MAP:Ljava/util/HashMap;

    invoke-virtual {v0, p1}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/telegram/messenger/chromecast/ChromecastMedia;

    if-nez v0, :cond_0

    .line 267
    iget-object p0, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->castedFiles:Ljava/util/HashMap;

    invoke-virtual {p0, p1}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/telegram/messenger/chromecast/ChromecastMedia;

    return-object p0

    :cond_0
    return-object v0
.end method

.method public static getHost()Ljava/lang/String;
    .locals 2

    .line 341
    invoke-static {}, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->getMyLocalIp()I

    move-result v0

    .line 342
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-static {v0}, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->formatIp4(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ":61578"

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method private static getMyLocalIp()I
    .locals 6

    .line 353
    sget-object v0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    const-string/jumbo v1, "wifi"

    invoke-virtual {v0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/net/wifi/WifiManager;

    .line 354
    invoke-virtual {v0}, Landroid/net/wifi/WifiManager;->getConnectionInfo()Landroid/net/wifi/WifiInfo;

    move-result-object v0

    invoke-virtual {v0}, Landroid/net/wifi/WifiInfo;->getIpAddress()I

    move-result v0

    if-nez v0, :cond_3

    .line 358
    :try_start_0
    invoke-static {}, Ljava/net/NetworkInterface;->getNetworkInterfaces()Ljava/util/Enumeration;

    move-result-object v1

    :cond_0
    invoke-interface {v1}, Ljava/util/Enumeration;->hasMoreElements()Z

    move-result v2

    if-eqz v2, :cond_2

    .line 359
    invoke-interface {v1}, Ljava/util/Enumeration;->nextElement()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/net/NetworkInterface;

    .line 360
    invoke-virtual {v2}, Ljava/net/NetworkInterface;->getInetAddresses()Ljava/util/Enumeration;

    move-result-object v2

    :cond_1
    :goto_0
    invoke-interface {v2}, Ljava/util/Enumeration;->hasMoreElements()Z

    move-result v3

    if-eqz v3, :cond_0

    .line 361
    invoke-interface {v2}, Ljava/util/Enumeration;->nextElement()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/net/InetAddress;

    .line 363
    invoke-virtual {v3}, Ljava/net/InetAddress;->isSiteLocalAddress()Z

    move-result v4

    if-eqz v4, :cond_1

    .line 364
    invoke-virtual {v3}, Ljava/net/InetAddress;->getAddress()[B

    move-result-object v3

    const/4 v4, 0x0

    .line 365
    aget-byte v4, v3, v4

    add-int/lit16 v4, v4, 0x100

    rem-int/lit16 v4, v4, 0x100

    const/4 v5, 0x1

    aget-byte v5, v3, v5

    add-int/lit16 v5, v5, 0x100

    rem-int/lit16 v5, v5, 0x100

    shl-int/lit8 v5, v5, 0x8

    add-int/2addr v4, v5

    const/4 v5, 0x2

    aget-byte v5, v3, v5

    add-int/lit16 v5, v5, 0x100

    rem-int/lit16 v5, v5, 0x100

    shl-int/lit8 v5, v5, 0x10

    add-int/2addr v4, v5

    const/4 v5, 0x3

    aget-byte v3, v3, v5

    add-int/lit16 v3, v3, 0x100

    rem-int/lit16 v3, v3, 0x100
    :try_end_0
    .catch Ljava/net/SocketException; {:try_start_0 .. :try_end_0} :catch_0

    shl-int/lit8 v0, v3, 0x18

    add-int/2addr v0, v4

    goto :goto_0

    :catch_0
    move-exception v1

    goto :goto_1

    :cond_2
    return v0

    .line 373
    :goto_1
    invoke-static {v1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    :cond_3
    return v0
.end method

.method public static getUrlToSource(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    .line 337
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "http://"

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static parseRangeHeader(Ljava/lang/String;J)Lorg/telegram/messenger/chromecast/ChromecastFileServer$Range;
    .locals 7

    .line 304
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 p0, 0x0

    return-object p0

    .line 309
    :cond_0
    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    const/4 v0, 0x6

    invoke-virtual {p0, v0}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p0

    .line 311
    const-string v0, "-"

    invoke-virtual {p0, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v1

    const/4 v2, 0x1

    const-wide/16 v3, 0x1

    if-eqz v1, :cond_1

    sub-long v0, p1, v3

    .line 313
    invoke-virtual {p0, v2}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v5

    sub-long v5, v0, v5

    goto :goto_0

    .line 315
    :cond_1
    invoke-virtual {p0, v0}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object p0

    const/4 v0, 0x0

    .line 316
    aget-object v0, p0, v0

    invoke-static {v0}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v5

    .line 317
    array-length v0, p0

    if-le v0, v2, :cond_2

    aget-object p0, p0, v2

    invoke-static {p0}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v0

    goto :goto_0

    :cond_2
    sub-long v0, p1, v3

    :goto_0
    sub-long/2addr p1, v3

    cmp-long p0, v0, p1

    if-lez p0, :cond_3

    move-wide v0, p1

    .line 323
    :cond_3
    new-instance p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer$Range;

    invoke-direct {p0, v5, v6, v0, v1}, Lorg/telegram/messenger/chromecast/ChromecastFileServer$Range;-><init>(JJ)V

    return-object p0
.end method

.method private serveAvailableRoutes(Ljava/lang/String;)Lfi/iki/elonen/NanoHTTPD$Response;
    .locals 7

    .line 173
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    .line 175
    iget-object v1, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->coverFile:Landroid/util/Pair;

    const/16 v2, 0xa

    if-eqz v1, :cond_1

    .line 176
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->length()I

    move-result v1

    if-lez v1, :cond_0

    .line 177
    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 179
    :cond_0
    iget-object v1, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->coverFile:Landroid/util/Pair;

    iget-object v1, v1, Landroid/util/Pair;->first:Ljava/lang/Object;

    check-cast v1, Ljava/lang/String;

    invoke-static {p1, v1}, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->getUrlToSource(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_1
    const/4 v1, 0x0

    :goto_0
    const/4 v3, 0x2

    if-ge v1, v3, :cond_8

    if-nez v1, :cond_2

    .line 182
    sget-object v3, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->ASSET_FILES_MAP:Ljava/util/HashMap;

    goto :goto_1

    :cond_2
    iget-object v3, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->castedFiles:Ljava/util/HashMap;

    .line 183
    :goto_1
    invoke-interface {v3}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v3

    invoke-interface {v3}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :cond_3
    :goto_2
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_7

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/util/Map$Entry;

    .line 184
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->length()I

    move-result v5

    if-lez v5, :cond_4

    .line 185
    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 187
    :cond_4
    invoke-interface {v4}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/lang/String;

    invoke-static {p1, v5}, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->getUrlToSource(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 189
    invoke-interface {v4}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lorg/telegram/messenger/chromecast/ChromecastMedia;

    iget-object v4, v4, Lorg/telegram/messenger/chromecast/ChromecastMedia;->mediaMetadata:Lcom/google/android/gms/cast/MediaMetadata;

    if-nez v4, :cond_5

    goto :goto_2

    .line 192
    :cond_5
    const-string v5, "com.google.android.gms.cast.metadata.TITLE"

    invoke-virtual {v4, v5}, Lcom/google/android/gms/cast/MediaMetadata;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    .line 193
    const-string v6, "com.google.android.gms.cast.metadata.SUBTITLE"

    invoke-virtual {v4, v6}, Lcom/google/android/gms/cast/MediaMetadata;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    if-eqz v5, :cond_6

    const/16 v6, 0x20

    .line 195
    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 196
    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_6
    if-eqz v4, :cond_3

    .line 199
    const-string v5, " ["

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 200
    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/16 v4, 0x5d

    .line 201
    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    goto :goto_2

    :cond_7
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 205
    :cond_8
    sget-object p0, Lfi/iki/elonen/NanoHTTPD$Response$Status;->OK:Lfi/iki/elonen/NanoHTTPD$Response$Status;

    const-string/jumbo p1, "text/plain"

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {p0, p1, v0}, Lfi/iki/elonen/NanoHTTPD;->newFixedLengthResponse(Lfi/iki/elonen/NanoHTTPD$Response$IStatus;Ljava/lang/String;Ljava/lang/String;)Lfi/iki/elonen/NanoHTTPD$Response;

    move-result-object p0

    return-object p0
.end method

.method private serveFileImpl(Lfi/iki/elonen/NanoHTTPD$IHTTPSession;Ljava/io/File;)Lfi/iki/elonen/NanoHTTPD$Response;
    .locals 2

    .line 261
    sget-object p0, Lfi/iki/elonen/NanoHTTPD$Response$Status;->OK:Lfi/iki/elonen/NanoHTTPD$Response$Status;

    new-instance p1, Ljava/io/BufferedInputStream;

    new-instance v0, Ljava/io/FileInputStream;

    invoke-direct {v0, p2}, Ljava/io/FileInputStream;-><init>(Ljava/io/File;)V

    invoke-direct {p1, v0}, Ljava/io/BufferedInputStream;-><init>(Ljava/io/InputStream;)V

    invoke-virtual {p2}, Ljava/io/File;->length()J

    move-result-wide v0

    const-string p2, "image/jpeg"

    invoke-static {p0, p2, p1, v0, v1}, Lfi/iki/elonen/NanoHTTPD;->newFixedLengthResponse(Lfi/iki/elonen/NanoHTTPD$Response$IStatus;Ljava/lang/String;Ljava/io/InputStream;J)Lfi/iki/elonen/NanoHTTPD$Response;

    move-result-object p0

    return-object p0
.end method

.method private serveFileImpl(Lfi/iki/elonen/NanoHTTPD$IHTTPSession;Lorg/telegram/messenger/chromecast/ChromecastMedia;)Lfi/iki/elonen/NanoHTTPD$Response;
    .locals 10

    .line 209
    invoke-interface {p1}, Lfi/iki/elonen/NanoHTTPD$IHTTPSession;->getHeaders()Ljava/util/Map;

    move-result-object v0

    .line 210
    const-string v1, "host"

    invoke-interface {v0, v1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 212
    iget-object v1, p2, Lorg/telegram/messenger/chromecast/ChromecastMedia;->internalUri:Landroid/net/Uri;

    invoke-virtual {v1}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v1

    const-string v2, "data:application/x-mpegurl;base64,"

    invoke-virtual {v1, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v1

    const/4 v2, 0x0

    if-eqz v1, :cond_0

    .line 213
    iget-object p0, p2, Lorg/telegram/messenger/chromecast/ChromecastMedia;->internalUri:Landroid/net/Uri;

    invoke-virtual {p0}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object p0

    const/16 p1, 0x22

    invoke-virtual {p0, p1}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p0

    .line 214
    invoke-static {p0, v2}, Landroid/util/Base64;->decode(Ljava/lang/String;I)[B

    move-result-object p0

    .line 216
    sget-object p1, Lfi/iki/elonen/NanoHTTPD$Response$Status;->OK:Lfi/iki/elonen/NanoHTTPD$Response$Status;

    iget-object p2, p2, Lorg/telegram/messenger/chromecast/ChromecastMedia;->mimeType:Ljava/lang/String;

    new-instance v1, Ljava/lang/String;

    invoke-direct {v1, p0}, Ljava/lang/String;-><init>([B)V

    invoke-static {v1, v0}, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->fixHlsManifest(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p1, p2, p0}, Lfi/iki/elonen/NanoHTTPD;->newFixedLengthResponse(Lfi/iki/elonen/NanoHTTPD$Response$IStatus;Ljava/lang/String;Ljava/lang/String;)Lfi/iki/elonen/NanoHTTPD$Response;

    move-result-object p0

    return-object p0

    .line 220
    :cond_0
    invoke-direct {p0, p2}, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->getDataSourceFactory(Lorg/telegram/messenger/chromecast/ChromecastMedia;)Landroidx/media3/datasource/DataSource$Factory;

    move-result-object p0

    invoke-interface {p0}, Landroidx/media3/datasource/DataSource$Factory;->createDataSource()Landroidx/media3/datasource/DataSource;

    move-result-object p0

    .line 221
    new-instance v1, Landroidx/media3/datasource/DataSpec$Builder;

    invoke-direct {v1}, Landroidx/media3/datasource/DataSpec$Builder;-><init>()V

    iget-object v3, p2, Lorg/telegram/messenger/chromecast/ChromecastMedia;->internalUri:Landroid/net/Uri;

    invoke-virtual {v1, v3}, Landroidx/media3/datasource/DataSpec$Builder;->setUri(Landroid/net/Uri;)Landroidx/media3/datasource/DataSpec$Builder;

    move-result-object v1

    .line 222
    invoke-virtual {v1}, Landroidx/media3/datasource/DataSpec$Builder;->build()Landroidx/media3/datasource/DataSpec;

    move-result-object v3

    invoke-interface {p0, v3}, Landroidx/media3/datasource/DataSource;->open(Landroidx/media3/datasource/DataSpec;)J

    move-result-wide v3

    .line 223
    invoke-interface {p0}, Landroidx/media3/datasource/DataSource;->close()V

    .line 225
    iget-object v5, p2, Lorg/telegram/messenger/chromecast/ChromecastMedia;->mimeType:Ljava/lang/String;

    const-string v6, "application/x-mpegURL"

    invoke-static {v5, v6}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v5

    if-nez v5, :cond_1

    .line 226
    invoke-interface {p1}, Lfi/iki/elonen/NanoHTTPD$IHTTPSession;->getHeaders()Ljava/util/Map;

    move-result-object p1

    const-string v6, "range"

    invoke-interface {p1, v6}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;

    invoke-static {p1, v3, v4}, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->parseRangeHeader(Ljava/lang/String;J)Lorg/telegram/messenger/chromecast/ChromecastFileServer$Range;

    move-result-object p1

    goto :goto_0

    :cond_1
    const/4 p1, 0x0

    :goto_0
    if-eqz p1, :cond_2

    .line 227
    iget-wide v6, p1, Lorg/telegram/messenger/chromecast/ChromecastFileServer$Range;->end:J

    iget-wide v8, p1, Lorg/telegram/messenger/chromecast/ChromecastFileServer$Range;->start:J

    sub-long/2addr v6, v8

    const-wide/16 v8, 0x1

    add-long/2addr v6, v8

    goto :goto_1

    :cond_2
    move-wide v6, v3

    :goto_1
    if-eqz p1, :cond_3

    .line 230
    iget-wide v8, p1, Lorg/telegram/messenger/chromecast/ChromecastFileServer$Range;->start:J

    invoke-virtual {v1, v8, v9}, Landroidx/media3/datasource/DataSpec$Builder;->setPosition(J)Landroidx/media3/datasource/DataSpec$Builder;

    .line 231
    invoke-virtual {v1, v6, v7}, Landroidx/media3/datasource/DataSpec$Builder;->setLength(J)Landroidx/media3/datasource/DataSpec$Builder;

    :cond_3
    if-eqz v5, :cond_4

    long-to-int p1, v6

    .line 237
    new-array v3, p1, [B

    .line 238
    invoke-virtual {v1}, Landroidx/media3/datasource/DataSpec$Builder;->build()Landroidx/media3/datasource/DataSpec;

    move-result-object v1

    invoke-interface {p0, v1}, Landroidx/media3/datasource/DataSource;->open(Landroidx/media3/datasource/DataSpec;)J

    .line 239
    invoke-interface {p0, v3, v2, p1}, Landroidx/media3/common/DataReader;->read([BII)I

    .line 240
    invoke-interface {p0}, Landroidx/media3/datasource/DataSource;->close()V

    .line 242
    sget-object p0, Lfi/iki/elonen/NanoHTTPD$Response$Status;->OK:Lfi/iki/elonen/NanoHTTPD$Response$Status;

    iget-object p1, p2, Lorg/telegram/messenger/chromecast/ChromecastMedia;->mimeType:Ljava/lang/String;

    new-instance p2, Ljava/lang/String;

    invoke-direct {p2, v3}, Ljava/lang/String;-><init>([B)V

    invoke-static {p2, v0}, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->fixHlsManifest(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    invoke-static {p0, p1, p2}, Lfi/iki/elonen/NanoHTTPD;->newFixedLengthResponse(Lfi/iki/elonen/NanoHTTPD$Response$IStatus;Ljava/lang/String;Ljava/lang/String;)Lfi/iki/elonen/NanoHTTPD$Response;

    move-result-object p0

    return-object p0

    :cond_4
    const-wide/16 v8, 0x0

    cmp-long v0, v6, v8

    if-eqz v0, :cond_6

    .line 247
    new-instance v0, Lorg/telegram/messenger/chromecast/ChromecastFileServer$DataSourceInputStream;

    invoke-virtual {v1}, Landroidx/media3/datasource/DataSpec$Builder;->build()Landroidx/media3/datasource/DataSpec;

    move-result-object v1

    invoke-direct {v0, p0, v1}, Lorg/telegram/messenger/chromecast/ChromecastFileServer$DataSourceInputStream;-><init>(Landroidx/media3/datasource/DataSource;Landroidx/media3/datasource/DataSpec;)V

    if-eqz p1, :cond_5

    .line 248
    sget-object p0, Lfi/iki/elonen/NanoHTTPD$Response$Status;->PARTIAL_CONTENT:Lfi/iki/elonen/NanoHTTPD$Response$Status;

    goto :goto_2

    :cond_5
    sget-object p0, Lfi/iki/elonen/NanoHTTPD$Response$Status;->OK:Lfi/iki/elonen/NanoHTTPD$Response$Status;

    :goto_2
    iget-object p2, p2, Lorg/telegram/messenger/chromecast/ChromecastMedia;->mimeType:Ljava/lang/String;

    invoke-static {p0, p2, v0, v6, v7}, Lfi/iki/elonen/NanoHTTPD;->newFixedLengthResponse(Lfi/iki/elonen/NanoHTTPD$Response$IStatus;Ljava/lang/String;Ljava/io/InputStream;J)Lfi/iki/elonen/NanoHTTPD$Response;

    move-result-object p0

    goto :goto_3

    .line 250
    :cond_6
    sget-object p0, Lfi/iki/elonen/NanoHTTPD$Response$Status;->NO_CONTENT:Lfi/iki/elonen/NanoHTTPD$Response$Status;

    iget-object p2, p2, Lorg/telegram/messenger/chromecast/ChromecastMedia;->mimeType:Ljava/lang/String;

    const-string v0, ""

    invoke-static {p0, p2, v0}, Lfi/iki/elonen/NanoHTTPD;->newFixedLengthResponse(Lfi/iki/elonen/NanoHTTPD$Response$IStatus;Ljava/lang/String;Ljava/lang/String;)Lfi/iki/elonen/NanoHTTPD$Response;

    move-result-object p0

    :goto_3
    if-eqz p1, :cond_7

    .line 254
    new-instance p2, Ljava/lang/StringBuilder;

    const-string v0, "bytes "

    invoke-direct {p2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-wide v0, p1, Lorg/telegram/messenger/chromecast/ChromecastFileServer$Range;->start:J

    invoke-virtual {p2, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, "-"

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v0, p1, Lorg/telegram/messenger/chromecast/ChromecastFileServer$Range;->end:J

    invoke-virtual {p2, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p1, "/"

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2, v3, v4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const-string p2, "Content-Range"

    invoke-virtual {p0, p2, p1}, Lfi/iki/elonen/NanoHTTPD$Response;->addHeader(Ljava/lang/String;Ljava/lang/String;)V

    :cond_7
    return-object p0
.end method

.method private serveImpl(Lfi/iki/elonen/NanoHTTPD$IHTTPSession;)Lfi/iki/elonen/NanoHTTPD$Response;
    .locals 4

    .line 145
    invoke-interface {p1}, Lfi/iki/elonen/NanoHTTPD$IHTTPSession;->getHeaders()Ljava/util/Map;

    move-result-object v0

    .line 146
    const-string v1, "host"

    invoke-interface {v0, v1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 147
    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "http://"

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-interface {p1}, Lfi/iki/elonen/NanoHTTPD$IHTTPSession;->getUri()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v1

    .line 148
    invoke-virtual {v1}, Landroid/net/Uri;->getPath()Ljava/lang/String;

    move-result-object v1

    .line 151
    sget-object v2, Lfi/iki/elonen/NanoHTTPD$Method;->OPTIONS:Lfi/iki/elonen/NanoHTTPD$Method;

    invoke-interface {p1}, Lfi/iki/elonen/NanoHTTPD$IHTTPSession;->getMethod()Lfi/iki/elonen/NanoHTTPD$Method;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v2

    const-string/jumbo v3, "text/plain"

    if-eqz v2, :cond_0

    .line 152
    sget-object p0, Lfi/iki/elonen/NanoHTTPD$Response$Status;->OK:Lfi/iki/elonen/NanoHTTPD$Response$Status;

    const-string p1, ""

    invoke-static {p0, v3, p1}, Lfi/iki/elonen/NanoHTTPD;->newFixedLengthResponse(Lfi/iki/elonen/NanoHTTPD$Response$IStatus;Ljava/lang/String;Ljava/lang/String;)Lfi/iki/elonen/NanoHTTPD$Response;

    move-result-object p0

    return-object p0

    .line 155
    :cond_0
    const-string v2, "/"

    invoke-static {v1, v2}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 156
    invoke-direct {p0, v0}, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->serveAvailableRoutes(Ljava/lang/String;)Lfi/iki/elonen/NanoHTTPD$Response;

    move-result-object p0

    return-object p0

    .line 159
    :cond_1
    invoke-direct {p0, v1}, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->getFile(Ljava/lang/String;)Lorg/telegram/messenger/chromecast/ChromecastMedia;

    move-result-object v0

    if-eqz v0, :cond_2

    .line 161
    invoke-direct {p0, p1, v0}, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->serveFileImpl(Lfi/iki/elonen/NanoHTTPD$IHTTPSession;Lorg/telegram/messenger/chromecast/ChromecastMedia;)Lfi/iki/elonen/NanoHTTPD$Response;

    move-result-object p0

    return-object p0

    .line 164
    :cond_2
    iget-object v0, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->coverFile:Landroid/util/Pair;

    if-eqz v0, :cond_3

    iget-object v0, v0, Landroid/util/Pair;->first:Ljava/lang/Object;

    check-cast v0, Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_3

    iget-object v0, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->coverFile:Landroid/util/Pair;

    iget-object v0, v0, Landroid/util/Pair;->second:Ljava/lang/Object;

    check-cast v0, Ljava/io/File;

    goto :goto_0

    :cond_3
    const/4 v0, 0x0

    :goto_0
    if-eqz v0, :cond_4

    .line 166
    invoke-direct {p0, p1, v0}, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->serveFileImpl(Lfi/iki/elonen/NanoHTTPD$IHTTPSession;Ljava/io/File;)Lfi/iki/elonen/NanoHTTPD$Response;

    move-result-object p0

    return-object p0

    .line 169
    :cond_4
    sget-object p0, Lfi/iki/elonen/NanoHTTPD$Response$Status;->NOT_FOUND:Lfi/iki/elonen/NanoHTTPD$Response$Status;

    const-string p1, "file not found"

    invoke-static {p0, v3, p1}, Lfi/iki/elonen/NanoHTTPD;->newFixedLengthResponse(Lfi/iki/elonen/NanoHTTPD$Response$IStatus;Ljava/lang/String;Ljava/lang/String;)Lfi/iki/elonen/NanoHTTPD$Response;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public addFileToCast(Lorg/telegram/messenger/chromecast/ChromecastMedia;)V
    .locals 2

    .line 75
    iget-object v0, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->castedFiles:Ljava/util/HashMap;

    iget-object v1, p1, Lorg/telegram/messenger/chromecast/ChromecastMedia;->externalPath:Ljava/lang/String;

    invoke-virtual {v0, v1, p1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 76
    invoke-direct {p0}, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->check()V

    return-void
.end method

.method public getCoverFile()Ljava/io/File;
    .locals 0

    .line 99
    iget-object p0, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->coverFile:Landroid/util/Pair;

    if-nez p0, :cond_0

    const/4 p0, 0x0

    return-object p0

    .line 101
    :cond_0
    iget-object p0, p0, Landroid/util/Pair;->second:Ljava/lang/Object;

    check-cast p0, Ljava/io/File;

    return-object p0
.end method

.method public getCoverPath()Ljava/lang/String;
    .locals 0

    .line 105
    iget-object p0, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->coverFile:Landroid/util/Pair;

    if-nez p0, :cond_0

    const/4 p0, 0x0

    return-object p0

    .line 107
    :cond_0
    iget-object p0, p0, Landroid/util/Pair;->first:Ljava/lang/Object;

    check-cast p0, Ljava/lang/String;

    return-object p0
.end method

.method public removeFileFromCast(Lorg/telegram/messenger/chromecast/ChromecastMedia;)V
    .locals 1

    .line 80
    iget-object v0, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->castedFiles:Ljava/util/HashMap;

    iget-object p1, p1, Lorg/telegram/messenger/chromecast/ChromecastMedia;->externalPath:Ljava/lang/String;

    invoke-virtual {v0, p1}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 81
    invoke-direct {p0}, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->check()V

    return-void
.end method

.method public serve(Lfi/iki/elonen/NanoHTTPD$IHTTPSession;)Lfi/iki/elonen/NanoHTTPD$Response;
    .locals 4

    .line 132
    iget-object v0, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->reqId:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->incrementAndGet()I

    move-result v0

    .line 134
    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "Request "

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v2, " "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-interface {p1}, Lfi/iki/elonen/NanoHTTPD$IHTTPSession;->getMethod()Lfi/iki/elonen/NanoHTTPD$Method;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-interface {p1}, Lfi/iki/elonen/NanoHTTPD$IHTTPSession;->getUri()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-interface {p1}, Lfi/iki/elonen/NanoHTTPD$IHTTPSession;->getHeaders()Ljava/util/Map;

    move-result-object v2

    const-string v3, "range"

    invoke-interface {v2, v3}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    const-string v2, "CAST_SERVER"

    invoke-static {v2, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 137
    :try_start_0
    invoke-direct {p0, p1}, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->serveImpl(Lfi/iki/elonen/NanoHTTPD$IHTTPSession;)Lfi/iki/elonen/NanoHTTPD$Response;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->addCorsHeaders(Lfi/iki/elonen/NanoHTTPD$Response;)Lfi/iki/elonen/NanoHTTPD$Response;

    move-result-object p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    return-object p0

    .line 139
    :catchall_0
    new-instance p0, Ljava/lang/StringBuilder;

    const-string p1, "Error "

    invoke-direct {p0, p1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {v2, p0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 140
    sget-object p0, Lfi/iki/elonen/NanoHTTPD$Response$Status;->INTERNAL_ERROR:Lfi/iki/elonen/NanoHTTPD$Response$Status;

    const-string/jumbo p1, "text/plain"

    const-string v0, "Error reading file"

    invoke-static {p0, p1, v0}, Lfi/iki/elonen/NanoHTTPD;->newFixedLengthResponse(Lfi/iki/elonen/NanoHTTPD$Response$IStatus;Ljava/lang/String;Ljava/lang/String;)Lfi/iki/elonen/NanoHTTPD$Response;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->addCorsHeaders(Lfi/iki/elonen/NanoHTTPD$Response;)Lfi/iki/elonen/NanoHTTPD$Response;

    move-result-object p0

    return-object p0
.end method

.method public setCoverFile(Ljava/lang/String;Ljava/io/File;)V
    .locals 1

    if-eqz p1, :cond_1

    if-nez p2, :cond_0

    goto :goto_0

    .line 93
    :cond_0
    new-instance v0, Landroid/util/Pair;

    invoke-direct {v0, p1, p2}, Landroid/util/Pair;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    iput-object v0, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->coverFile:Landroid/util/Pair;

    goto :goto_1

    .line 86
    :cond_1
    :goto_0
    iget-object p1, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->coverFile:Landroid/util/Pair;

    if-eqz p1, :cond_2

    iget-object p1, p1, Landroid/util/Pair;->second:Ljava/lang/Object;

    check-cast p1, Ljava/io/File;

    invoke-virtual {p1}, Ljava/io/File;->exists()Z

    move-result p1

    if-eqz p1, :cond_2

    .line 88
    :try_start_0
    iget-object p1, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->coverFile:Landroid/util/Pair;

    iget-object p1, p1, Landroid/util/Pair;->second:Ljava/lang/Object;

    check-cast p1, Ljava/io/File;

    invoke-virtual {p1}, Ljava/io/File;->delete()Z
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    :cond_2
    const/4 p1, 0x0

    .line 91
    iput-object p1, p0, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->coverFile:Landroid/util/Pair;

    .line 95
    :goto_1
    invoke-direct {p0}, Lorg/telegram/messenger/chromecast/ChromecastFileServer;->check()V

    return-void
.end method
