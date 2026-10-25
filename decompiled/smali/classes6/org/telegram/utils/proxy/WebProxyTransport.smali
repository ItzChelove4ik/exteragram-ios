.class public final Lorg/telegram/utils/proxy/WebProxyTransport;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/ui/Components/ForegroundDetector$Listener;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/utils/proxy/WebProxyTransport$Address;,
        Lorg/telegram/utils/proxy/WebProxyTransport$ReadyCallback;,
        Lorg/telegram/utils/proxy/WebProxyTransport$Stream;
    }
.end annotation


# static fields
.field private static connectionTestInstance:Lorg/telegram/utils/proxy/WebProxyTransport;

.field private static instance:Lorg/telegram/utils/proxy/WebProxyTransport;

.field private static final staticLock:Ljava/lang/Object;


# instance fields
.field private final address:Ljava/lang/String;

.field private final androidNonce:Ljava/lang/String;

.field private final bridgePath:Ljava/lang/String;

.field private final bridgeUrl:Ljava/lang/String;

.field private carrierConnected:Z

.field private final carrierExecutor:Ljava/util/concurrent/ExecutorService;

.field private final host:Ljava/lang/String;

.field private final ioExecutor:Ljava/util/concurrent/ExecutorService;

.field private final lock:Ljava/lang/Object;

.field private final nextStreamId:Ljava/util/concurrent/atomic/AtomicInteger;

.field private final origin:Ljava/lang/String;

.field private final outbound:Ljava/util/ArrayDeque;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayDeque<",
            "[B>;"
        }
    .end annotation
.end field

.field private outboundBytes:I

.field private final path:Ljava/lang/String;

.field private readyCallback:Lorg/telegram/utils/proxy/WebProxyTransport$ReadyCallback;

.field private replyProxy:Landroidx/webkit/JavaScriptReplyProxy;

.field private restartScheduled:Z

.field private final secret:Ljava/lang/String;

.field private final serverSocket:Ljava/net/ServerSocket;

.field private stopped:Z

.field private final streams:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/Integer;",
            "Lorg/telegram/utils/proxy/WebProxyTransport$Stream;",
            ">;"
        }
    .end annotation
.end field

.field private webView:Landroid/webkit/WebView;


# direct methods
.method public static synthetic $r8$lambda$7c6xDs-p285ya7HtCzYwYzQ8xcc(Lorg/telegram/utils/proxy/WebProxyTransport;Landroid/webkit/WebView;Landroidx/webkit/WebMessageCompat;Landroid/net/Uri;ZLandroidx/webkit/JavaScriptReplyProxy;)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lorg/telegram/utils/proxy/WebProxyTransport;->onWebMessage(Landroid/webkit/WebView;Landroidx/webkit/WebMessageCompat;Landroid/net/Uri;ZLandroidx/webkit/JavaScriptReplyProxy;)V

    return-void
.end method

.method public static synthetic $r8$lambda$BXafk1NbrkLlFNU1AKiN047Ptyw(I)I
    .locals 2

    .line 364
    const v0, 0xffffff

    const/4 v1, 0x1

    if-lt p0, v0, :cond_0

    return v1

    :cond_0
    add-int/2addr p0, v1

    return p0
.end method

.method public static synthetic $r8$lambda$OafH-_ZHsFga34Cl6E6IY54SB04(Lorg/telegram/utils/proxy/WebProxyTransport;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyTransport;->drainOutbound()V

    return-void
.end method

.method public static synthetic $r8$lambda$R0PF751RokEJyumMOKT2Iq5o0zo(Lorg/telegram/utils/proxy/WebProxyTransport;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyTransport;->lambda$failCarrier$4()V

    return-void
.end method

.method public static synthetic $r8$lambda$RC9hMGcOexZfSHIimGPiMfX37cc(Lorg/telegram/utils/proxy/WebProxyTransport;Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/proxy/WebProxyTransport;->lambda$acceptLoop$1(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)V

    return-void
.end method

.method public static synthetic $r8$lambda$iDj0f6b9HqC1X1XUL6A-bQ_VvzU(Lorg/telegram/utils/proxy/WebProxyTransport;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyTransport;->createWebView()V

    return-void
.end method

.method public static synthetic $r8$lambda$jBgePuFx1gWtE3jfj388ww3JlWo(Lorg/telegram/utils/proxy/WebProxyTransport;[B)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/proxy/WebProxyTransport;->lambda$onWebMessage$3([B)V

    return-void
.end method

.method public static synthetic $r8$lambda$oj7CwJCzcQESg1sRgOQT7sNLNC8(Lorg/telegram/utils/proxy/WebProxyTransport;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyTransport;->lambda$stopInternal$0()V

    return-void
.end method

.method public static synthetic $r8$lambda$xcOgU7AP9Ij-UQAVTMD6bQOcxcQ(Lorg/telegram/utils/proxy/WebProxyTransport;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyTransport;->acceptLoop()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mfailWebView(Lorg/telegram/utils/proxy/WebProxyTransport;Landroid/webkit/WebView;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/proxy/WebProxyTransport;->failWebView(Landroid/webkit/WebView;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$misAllowedNetworkRequest(Lorg/telegram/utils/proxy/WebProxyTransport;Landroid/net/Uri;)Z
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/proxy/WebProxyTransport;->isAllowedNetworkRequest(Landroid/net/Uri;)Z

    move-result p0

    return p0
.end method

.method public static bridge synthetic -$$Nest$misBridgeNavigation(Lorg/telegram/utils/proxy/WebProxyTransport;Landroid/net/Uri;)Z
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/proxy/WebProxyTransport;->isBridgeNavigation(Landroid/net/Uri;)Z

    move-result p0

    return p0
.end method

.method static constructor <clinit>()V
    .locals 1

    .line 73
    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    sput-object v0, Lorg/telegram/utils/proxy/WebProxyTransport;->staticLock:Ljava/lang/Object;

    return-void
.end method

.method private constructor <init>(Lorg/telegram/utils/proxy/WebProxyTransport$Address;Ljava/lang/String;[B)V
    .locals 5

    .line 260
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 77
    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    iput-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->lock:Ljava/lang/Object;

    .line 87
    invoke-static {}, Ljava/util/concurrent/Executors;->newCachedThreadPool()Ljava/util/concurrent/ExecutorService;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->ioExecutor:Ljava/util/concurrent/ExecutorService;

    .line 88
    invoke-static {}, Ljava/util/concurrent/Executors;->newSingleThreadExecutor()Ljava/util/concurrent/ExecutorService;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->carrierExecutor:Ljava/util/concurrent/ExecutorService;

    .line 89
    new-instance v0, Ljava/util/concurrent/atomic/AtomicInteger;

    const/4 v1, 0x1

    invoke-direct {v0, v1}, Ljava/util/concurrent/atomic/AtomicInteger;-><init>(I)V

    iput-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->nextStreamId:Ljava/util/concurrent/atomic/AtomicInteger;

    .line 90
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->streams:Ljava/util/Map;

    .line 91
    new-instance v0, Ljava/util/ArrayDeque;

    invoke-direct {v0}, Ljava/util/ArrayDeque;-><init>()V

    iput-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->outbound:Ljava/util/ArrayDeque;

    .line 261
    invoke-static {p1}, Lorg/telegram/utils/proxy/WebProxyTransport$Address;->-$$Nest$fgetvalue(Lorg/telegram/utils/proxy/WebProxyTransport$Address;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->address:Ljava/lang/String;

    .line 262
    invoke-static {p1}, Lorg/telegram/utils/proxy/WebProxyTransport$Address;->-$$Nest$fgethost(Lorg/telegram/utils/proxy/WebProxyTransport$Address;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->host:Ljava/lang/String;

    .line 263
    invoke-static {p1}, Lorg/telegram/utils/proxy/WebProxyTransport$Address;->-$$Nest$fgetpath(Lorg/telegram/utils/proxy/WebProxyTransport$Address;)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->path:Ljava/lang/String;

    .line 264
    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    const-string v2, "/"

    if-eqz v1, :cond_0

    goto :goto_0

    :cond_0
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    :goto_0
    iput-object v2, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->bridgePath:Ljava/lang/String;

    .line 265
    iput-object p2, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->secret:Ljava/lang/String;

    .line 266
    new-instance p2, Ljava/lang/StringBuilder;

    const-string v1, "https://"

    invoke-direct {p2, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    iput-object p2, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->origin:Ljava/lang/String;

    const/16 v1, 0x20

    .line 267
    invoke-static {v1}, Lorg/telegram/utils/proxy/WebProxyTransport;->randomToken(I)Ljava/lang/String;

    move-result-object v1

    iput-object v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->androidNonce:Ljava/lang/String;

    .line 268
    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-eqz v3, :cond_1

    .line 269
    new-instance p1, Ljava/lang/StringBuilder;

    const-string v3, "tdesktop-web-proxy-bridge-v1\n"

    invoke-direct {p1, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    goto :goto_1

    .line 270
    :cond_1
    new-instance v3, Ljava/lang/StringBuilder;

    const-string v4, "tdesktop-web-proxy-bridge-v2\n"

    invoke-direct {v3, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, "\n"

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    .line 271
    :goto_1
    const-string v0, "HmacSHA256"

    invoke-static {v0}, Ljavax/crypto/Mac;->getInstance(Ljava/lang/String;)Ljavax/crypto/Mac;

    move-result-object v3

    .line 272
    new-instance v4, Ljavax/crypto/spec/SecretKeySpec;

    invoke-direct {v4, p3, v0}, Ljavax/crypto/spec/SecretKeySpec;-><init>([BLjava/lang/String;)V

    invoke-virtual {v3, v4}, Ljavax/crypto/Mac;->init(Ljava/security/Key;)V

    .line 273
    sget-object p3, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    .line 274
    invoke-virtual {p1, p3}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object p1

    invoke-virtual {v3, p1}, Ljavax/crypto/Mac;->doFinal([B)[B

    move-result-object p1

    const/16 p3, 0xb

    .line 273
    invoke-static {p1, p3}, Landroid/util/Base64;->encodeToString([BI)Ljava/lang/String;

    move-result-object p1

    .line 276
    new-instance p3, Ljava/lang/StringBuilder;

    invoke-direct {p3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p3, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p2, "?bridge="

    invoke-virtual {p3, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "#android="

    invoke-virtual {p3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->bridgeUrl:Ljava/lang/String;

    .line 277
    new-instance p1, Ljava/net/ServerSocket;

    const-string p2, "127.0.0.1"

    invoke-static {p2}, Ljava/net/InetAddress;->getByName(Ljava/lang/String;)Ljava/net/InetAddress;

    move-result-object p2

    const/4 p3, 0x0

    const/16 v0, 0x40

    invoke-direct {p1, p3, v0, p2}, Ljava/net/ServerSocket;-><init>(IILjava/net/InetAddress;)V

    iput-object p1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->serverSocket:Ljava/net/ServerSocket;

    return-void
.end method

.method private acceptLoop()V
    .locals 7

    .line 331
    :goto_0
    :try_start_0
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->serverSocket:Ljava/net/ServerSocket;

    invoke-virtual {v0}, Ljava/net/ServerSocket;->accept()Ljava/net/Socket;

    move-result-object v0

    const/4 v1, 0x1

    .line 332
    invoke-virtual {v0, v1}, Ljava/net/Socket;->setTcpNoDelay(Z)V

    .line 334
    iget-object v2, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->lock:Ljava/lang/Object;

    monitor-enter v2
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 335
    :try_start_1
    iget-boolean v3, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->stopped:Z

    if-nez v3, :cond_2

    iget-object v3, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->streams:Ljava/util/Map;

    invoke-interface {v3}, Ljava/util/Map;->size()I

    move-result v3

    const/16 v4, 0x40

    if-lt v3, v4, :cond_0

    goto :goto_2

    .line 339
    :cond_0
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyTransport;->allocateStreamId()I

    move-result v3

    .line 340
    new-instance v4, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;

    const/4 v5, 0x0

    invoke-direct {v4, v3, v0, v5}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;-><init>(ILjava/net/Socket;Lorg/telegram/utils/proxy/WebProxyTransport-IA;)V

    .line 341
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->streams:Ljava/util/Map;

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    invoke-interface {v0, v6, v4}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 342
    iget-boolean v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->carrierConnected:Z

    if-eqz v0, :cond_1

    .line 343
    invoke-static {v4, v1}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fputopened(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;Z)V

    .line 344
    invoke-direct {p0, v1, v3, v5}, Lorg/telegram/utils/proxy/WebProxyTransport;->sendFrame(II[B)V

    goto :goto_1

    :catchall_0
    move-exception v0

    goto :goto_3

    .line 346
    :cond_1
    :goto_1
    monitor-exit v2
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 348
    :try_start_2
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->ioExecutor:Ljava/util/concurrent/ExecutorService;

    new-instance v1, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda4;

    invoke-direct {v1, p0, v4}, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda4;-><init>(Lorg/telegram/utils/proxy/WebProxyTransport;Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)V

    invoke-interface {v0, v1}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    goto :goto_4

    .line 336
    :cond_2
    :goto_2
    :try_start_3
    invoke-static {v0}, Lorg/telegram/utils/proxy/WebProxyTransport;->closeSocket(Ljava/net/Socket;)V

    .line 337
    monitor-exit v2

    goto :goto_0

    .line 346
    :goto_3
    monitor-exit v2
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    :try_start_4
    throw v0
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_0

    .line 350
    :goto_4
    iget-object v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->lock:Ljava/lang/Object;

    monitor-enter v1

    .line 351
    :try_start_5
    iget-boolean v2, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->stopped:Z

    if-eqz v2, :cond_3

    .line 352
    monitor-exit v1

    return-void

    :catchall_1
    move-exception p0

    goto :goto_5

    .line 354
    :cond_3
    monitor-exit v1
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_1

    .line 355
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 356
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyTransport;->failCarrier()V

    return-void

    .line 354
    :goto_5
    :try_start_6
    monitor-exit v1
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_1

    throw p0
.end method

.method private allocateStreamId()I
    .locals 3

    .line 364
    :cond_0
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->nextStreamId:Ljava/util/concurrent/atomic/AtomicInteger;

    new-instance v1, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda8;

    invoke-direct {v1}, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda8;-><init>()V

    invoke-virtual {v0, v1}, Ljava/util/concurrent/atomic/AtomicInteger;->getAndUpdate(Ljava/util/function/IntUnaryOperator;)I

    move-result v0

    if-eqz v0, :cond_0

    .line 365
    iget-object v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->streams:Ljava/util/Map;

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-interface {v1, v2}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_0

    return v0
.end method

.method private static closeSocket(Ljava/net/Socket;)V
    .locals 0

    .line 842
    :try_start_0
    invoke-virtual {p0}, Ljava/net/Socket;->close()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    return-void
.end method

.method private closeStream(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;Z)V
    .locals 3

    .line 692
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->lock:Ljava/lang/Object;

    monitor-enter v0

    .line 693
    :try_start_0
    iget-object v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->streams:Ljava/util/Map;

    invoke-static {p1}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fgetid(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-interface {v1, v2}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    if-eq v1, p1, :cond_0

    .line 694
    monitor-exit v0

    return-void

    :catchall_0
    move-exception p0

    goto :goto_1

    .line 696
    :cond_0
    iget-object v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->streams:Ljava/util/Map;

    invoke-static {p1}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fgetid(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-interface {v1, v2}, Ljava/util/Map;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    if-eqz p2, :cond_1

    .line 697
    iget-boolean p2, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->carrierConnected:Z

    if-eqz p2, :cond_1

    invoke-static {p1}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fgetopened(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)Z

    move-result p2

    if-eqz p2, :cond_1

    const/4 p2, 0x1

    goto :goto_0

    :cond_1
    const/4 p2, 0x0

    .line 698
    :goto_0
    iget-object v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->lock:Ljava/lang/Object;

    invoke-virtual {v1}, Ljava/lang/Object;->notifyAll()V

    .line 699
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 700
    invoke-static {p1}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fgetsocket(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)Ljava/net/Socket;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/utils/proxy/WebProxyTransport;->closeSocket(Ljava/net/Socket;)V

    if-eqz p2, :cond_2

    .line 702
    invoke-static {p1}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fgetid(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)I

    move-result p1

    const/4 p2, 0x0

    const/4 v0, 0x3

    invoke-direct {p0, v0, p1, p2}, Lorg/telegram/utils/proxy/WebProxyTransport;->sendFrame(II[B)V

    :cond_2
    return-void

    .line 699
    :goto_1
    :try_start_1
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p0
.end method

.method private createWebView()V
    .locals 5
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "SetJavaScriptEnabled"
        }
    .end annotation

    .line 412
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->lock:Ljava/lang/Object;

    monitor-enter v0

    .line 413
    :try_start_0
    iget-boolean v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->stopped:Z

    if-nez v1, :cond_3

    iget-object v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->webView:Landroid/webkit/WebView;

    if-eqz v1, :cond_0

    goto/16 :goto_0

    .line 416
    :cond_0
    invoke-static {}, Lorg/telegram/ui/Components/ForegroundDetector;->getInstance()Lorg/telegram/ui/Components/ForegroundDetector;

    move-result-object v1

    const/4 v2, 0x1

    if-eqz v1, :cond_1

    .line 417
    invoke-virtual {v1}, Lorg/telegram/ui/Components/ForegroundDetector;->isBackground()Z

    move-result v1

    if-eqz v1, :cond_1

    .line 418
    iput-boolean v2, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->restartScheduled:Z

    .line 419
    monitor-exit v0

    return-void

    :catchall_0
    move-exception p0

    goto :goto_1

    :cond_1
    const/4 v1, 0x0

    .line 421
    iput-boolean v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->restartScheduled:Z

    .line 422
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 423
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyTransport;->destroyWebView()V

    .line 426
    :try_start_1
    new-instance v0, Landroid/webkit/WebView;

    sget-object v3, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-direct {v0, v3}, Landroid/webkit/WebView;-><init>(Landroid/content/Context;)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    .line 432
    iput-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->webView:Landroid/webkit/WebView;

    .line 433
    invoke-virtual {v0, v1}, Landroid/webkit/WebView;->setBackgroundColor(I)V

    .line 434
    invoke-virtual {v0}, Landroid/webkit/WebView;->getSettings()Landroid/webkit/WebSettings;

    move-result-object v3

    .line 435
    invoke-virtual {v3, v2}, Landroid/webkit/WebSettings;->setJavaScriptEnabled(Z)V

    .line 436
    invoke-virtual {v3, v1}, Landroid/webkit/WebSettings;->setDomStorageEnabled(Z)V

    .line 437
    invoke-virtual {v3, v1}, Landroid/webkit/WebSettings;->setDatabaseEnabled(Z)V

    .line 438
    invoke-virtual {v3, v1}, Landroid/webkit/WebSettings;->setAllowFileAccess(Z)V

    .line 439
    invoke-virtual {v3, v1}, Landroid/webkit/WebSettings;->setAllowContentAccess(Z)V

    const/4 v4, 0x2

    .line 440
    invoke-virtual {v3, v4}, Landroid/webkit/WebSettings;->setCacheMode(I)V

    .line 441
    invoke-virtual {v3, v1}, Landroid/webkit/WebSettings;->setJavaScriptCanOpenWindowsAutomatically(Z)V

    .line 442
    invoke-virtual {v3, v1}, Landroid/webkit/WebSettings;->setSupportMultipleWindows(Z)V

    .line 443
    invoke-virtual {v3, v1}, Landroid/webkit/WebSettings;->setGeolocationEnabled(Z)V

    .line 444
    invoke-virtual {v3, v2}, Landroid/webkit/WebSettings;->setMediaPlaybackRequiresUserGesture(Z)V

    .line 445
    invoke-virtual {v3, v2}, Landroid/webkit/WebSettings;->setMixedContentMode(I)V

    .line 446
    sget v1, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v4, 0x1a

    if-lt v1, v4, :cond_2

    .line 447
    invoke-virtual {v3, v2}, Landroid/webkit/WebSettings;->setSafeBrowsingEnabled(Z)V

    .line 449
    :cond_2
    new-instance v1, Lorg/telegram/utils/proxy/WebProxyTransport$1;

    invoke-direct {v1, p0}, Lorg/telegram/utils/proxy/WebProxyTransport$1;-><init>(Lorg/telegram/utils/proxy/WebProxyTransport;)V

    invoke-virtual {v0, v1}, Landroid/webkit/WebView;->setWebViewClient(Landroid/webkit/WebViewClient;)V

    .line 492
    new-instance v1, Ljava/util/HashSet;

    invoke-direct {v1}, Ljava/util/HashSet;-><init>()V

    .line 493
    iget-object v2, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->origin:Ljava/lang/String;

    invoke-interface {v1, v2}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    .line 494
    const-string v2, "TelegramWebProxy"

    new-instance v3, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda3;

    invoke-direct {v3, p0}, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda3;-><init>(Lorg/telegram/utils/proxy/WebProxyTransport;)V

    invoke-static {v0, v2, v1, v3}, Landroidx/webkit/WebViewCompat;->addWebMessageListener(Landroid/webkit/WebView;Ljava/lang/String;Ljava/util/Set;Landroidx/webkit/WebViewCompat$WebMessageListener;)V

    .line 495
    iget-object p0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->bridgeUrl:Ljava/lang/String;

    invoke-virtual {v0, p0}, Landroid/webkit/WebView;->loadUrl(Ljava/lang/String;)V

    return-void

    :catch_0
    move-exception v0

    .line 428
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 429
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyTransport;->failCarrier()V

    return-void

    .line 414
    :cond_3
    :goto_0
    :try_start_2
    monitor-exit v0

    return-void

    .line 422
    :goto_1
    monitor-exit v0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw p0
.end method

.method private static decodeSecret(Ljava/lang/String;)[B
    .locals 8

    const/4 v0, 0x0

    if-nez p0, :cond_0

    return-object v0

    .line 810
    :cond_0
    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    .line 812
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v1

    const/16 v2, 0x20

    const/16 v3, 0x10

    const/4 v4, 0x0

    if-eq v1, v2, :cond_1

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v1

    const/16 v2, 0x22

    if-ne v1, v2, :cond_2

    :cond_1
    const-string v1, "[0-9a-fA-F]+"

    invoke-virtual {p0, v1}, Ljava/lang/String;->matches(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 813
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v1

    div-int/lit8 v1, v1, 0x2

    new-array v2, v1, [B

    move v5, v4

    :goto_0
    if-ge v5, v1, :cond_3

    mul-int/lit8 v6, v5, 0x2

    add-int/lit8 v7, v6, 0x2

    .line 815
    invoke-virtual {p0, v6, v7}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v6

    invoke-static {v6, v3}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;I)I

    move-result v6

    int-to-byte v6, v6

    aput-byte v6, v2, v5

    add-int/lit8 v5, v5, 0x1

    goto :goto_0

    :cond_2
    const/16 v1, 0xa

    .line 819
    :try_start_0
    invoke-static {p0, v1}, Landroid/util/Base64;->decode(Ljava/lang/String;I)[B

    move-result-object v2
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 824
    :cond_3
    array-length p0, v2

    if-eq p0, v3, :cond_5

    array-length p0, v2

    const/16 v1, 0x11

    if-ne p0, v1, :cond_4

    aget-byte p0, v2, v4

    and-int/lit16 p0, p0, 0xff

    const/16 v1, 0xdd

    if-ne p0, v1, :cond_4

    goto :goto_1

    :cond_4
    return-object v0

    :cond_5
    :goto_1
    return-object v2

    :catch_0
    return-object v0
.end method

.method private destroyWebView()V
    .locals 2

    .line 791
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->webView:Landroid/webkit/WebView;

    const/4 v1, 0x0

    .line 792
    iput-object v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->webView:Landroid/webkit/WebView;

    .line 793
    iput-object v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->replyProxy:Landroidx/webkit/JavaScriptReplyProxy;

    if-eqz v0, :cond_0

    .line 796
    :try_start_0
    invoke-virtual {v0}, Landroid/webkit/WebView;->stopLoading()V

    .line 797
    const-string p0, "about:blank"

    invoke-virtual {v0, p0}, Landroid/webkit/WebView;->loadUrl(Ljava/lang/String;)V

    .line 798
    invoke-virtual {v0}, Landroid/view/ViewGroup;->removeAllViews()V

    .line 799
    invoke-virtual {v0}, Landroid/webkit/WebView;->destroy()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 801
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    :cond_0
    return-void
.end method

.method private drainOutbound()V
    .locals 5

    .line 734
    :goto_0
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->lock:Ljava/lang/Object;

    monitor-enter v0

    .line 735
    :try_start_0
    iget-object v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->replyProxy:Landroidx/webkit/JavaScriptReplyProxy;

    .line 736
    iget-boolean v2, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->stopped:Z

    if-nez v2, :cond_1

    if-eqz v1, :cond_1

    iget-object v2, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->outbound:Ljava/util/ArrayDeque;

    invoke-virtual {v2}, Ljava/util/ArrayDeque;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_0

    goto :goto_1

    .line 739
    :cond_0
    iget-object v2, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->outbound:Ljava/util/ArrayDeque;

    invoke-virtual {v2}, Ljava/util/ArrayDeque;->removeFirst()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, [B

    .line 740
    iget v3, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->outboundBytes:I

    array-length v4, v2

    sub-int/2addr v3, v4

    iput v3, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->outboundBytes:I

    .line 741
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 743
    :try_start_1
    invoke-virtual {v1, v2}, Landroidx/webkit/JavaScriptReplyProxy;->postMessage([B)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    .line 745
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 746
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyTransport;->failCarrier()V

    return-void

    :catchall_0
    move-exception p0

    goto :goto_2

    .line 737
    :cond_1
    :goto_1
    :try_start_2
    monitor-exit v0

    return-void

    .line 741
    :goto_2
    monitor-exit v0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw p0
.end method

.method private failCarrier()V
    .locals 4

    .line 754
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->lock:Ljava/lang/Object;

    monitor-enter v0

    .line 755
    :try_start_0
    iget-boolean v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->stopped:Z

    if-nez v1, :cond_2

    iget-boolean v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->restartScheduled:Z

    if-eqz v1, :cond_0

    goto :goto_1

    :cond_0
    const/4 v1, 0x1

    .line 758
    iput-boolean v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->restartScheduled:Z

    const/4 v1, 0x0

    .line 759
    iput-boolean v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->carrierConnected:Z

    const/4 v2, 0x0

    .line 760
    iput-object v2, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->replyProxy:Landroidx/webkit/JavaScriptReplyProxy;

    .line 761
    iget-object v2, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->outbound:Ljava/util/ArrayDeque;

    invoke-virtual {v2}, Ljava/util/ArrayDeque;->clear()V

    .line 762
    iput v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->outboundBytes:I

    .line 763
    new-instance v2, Ljava/util/ArrayList;

    iget-object v3, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->streams:Ljava/util/Map;

    invoke-interface {v3}, Ljava/util/Map;->values()Ljava/util/Collection;

    move-result-object v3

    invoke-direct {v2, v3}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    .line 764
    iget-object v3, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->streams:Ljava/util/Map;

    invoke-interface {v3}, Ljava/util/Map;->clear()V

    .line 765
    iget-object v3, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->lock:Ljava/lang/Object;

    invoke-virtual {v3}, Ljava/lang/Object;->notifyAll()V

    .line 766
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 767
    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v0

    :goto_0
    if-ge v1, v0, :cond_1

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    add-int/lit8 v1, v1, 0x1

    check-cast v3, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;

    .line 768
    invoke-static {v3}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fgetsocket(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)Ljava/net/Socket;

    move-result-object v3

    invoke-static {v3}, Lorg/telegram/utils/proxy/WebProxyTransport;->closeSocket(Ljava/net/Socket;)V

    goto :goto_0

    .line 770
    :cond_1
    new-instance v0, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda6;

    invoke-direct {v0, p0}, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda6;-><init>(Lorg/telegram/utils/proxy/WebProxyTransport;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void

    :catchall_0
    move-exception p0

    goto :goto_2

    .line 756
    :cond_2
    :goto_1
    :try_start_1
    monitor-exit v0

    return-void

    .line 766
    :goto_2
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p0
.end method

.method private failWebView(Landroid/webkit/WebView;)V
    .locals 1

    .line 499
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->webView:Landroid/webkit/WebView;

    if-ne p1, v0, :cond_0

    .line 500
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyTransport;->failCarrier()V

    :cond_0
    return-void
.end method

.method private handleControl(Ljava/lang/String;Landroidx/webkit/JavaScriptReplyProxy;)V
    .locals 4

    .line 559
    :try_start_0
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, p1}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 560
    const-string p1, "t"

    invoke-virtual {v0, p1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    .line 561
    const-string v1, "tproxy-android-init"

    invoke-virtual {v1, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_2

    const-string v1, "v"

    .line 562
    invoke-virtual {v0, v1}, Lorg/json/JSONObject;->optInt(Ljava/lang/String;)I

    move-result v1

    const/4 v2, 0x1

    if-ne v1, v2, :cond_2

    iget-object v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->androidNonce:Ljava/lang/String;

    const-string v3, "nonce"

    .line 563
    invoke-virtual {v0, v3}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 564
    iget-object p1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->lock:Ljava/lang/Object;

    monitor-enter p1
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 565
    :try_start_1
    iget-boolean v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->stopped:Z

    if-nez v0, :cond_1

    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->replyProxy:Landroidx/webkit/JavaScriptReplyProxy;

    if-eqz v0, :cond_0

    goto :goto_0

    .line 568
    :cond_0
    iput-object p2, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->replyProxy:Landroidx/webkit/JavaScriptReplyProxy;

    .line 569
    monitor-exit p1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 570
    :try_start_2
    new-array p1, v2, [B

    const/4 p2, 0x0

    aput-byte v2, p1, p2

    const/16 v0, 0x10

    invoke-direct {p0, v0, p2, p1}, Lorg/telegram/utils/proxy/WebProxyTransport;->sendFrame(II[B)V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    return-void

    :catchall_0
    move-exception p0

    goto :goto_1

    .line 566
    :cond_1
    :goto_0
    :try_start_3
    monitor-exit p1

    return-void

    .line 569
    :goto_1
    monitor-exit p1
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    :try_start_4
    throw p0

    .line 573
    :cond_2
    const-string p2, "close"

    invoke-virtual {p2, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_3

    const-string p1, "failed"

    const-string p2, "state"

    invoke-virtual {v0, p2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_4

    .line 574
    :cond_3
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyTransport;->failCarrier()V
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_0

    :catch_0
    :cond_4
    return-void
.end method

.method private isAllowedNetworkRequest(Landroid/net/Uri;)Z
    .locals 3

    .line 509
    invoke-virtual {p1}, Landroid/net/Uri;->getPath()Ljava/lang/String;

    move-result-object v0

    .line 510
    const-string v1, "https"

    invoke-virtual {p1}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_1

    iget-object v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->host:Ljava/lang/String;

    .line 511
    invoke-virtual {p1}, Landroid/net/Uri;->getHost()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 512
    invoke-virtual {p1}, Landroid/net/Uri;->getUserInfo()Ljava/lang/String;

    move-result-object v1

    if-nez v1, :cond_1

    .line 513
    invoke-virtual {p1}, Landroid/net/Uri;->getPort()I

    move-result v1

    const/4 v2, -0x1

    if-eq v1, v2, :cond_0

    invoke-virtual {p1}, Landroid/net/Uri;->getPort()I

    move-result p1

    const/16 v1, 0x1bb

    if-ne p1, v1, :cond_1

    :cond_0
    if-eqz v0, :cond_1

    iget-object p0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->bridgePath:Ljava/lang/String;

    .line 515
    invoke-virtual {v0, p0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_1

    const/4 p0, 0x1

    return p0

    :cond_1
    const/4 p0, 0x0

    return p0
.end method

.method private isBridgeNavigation(Landroid/net/Uri;)Z
    .locals 0

    if-eqz p1, :cond_0

    .line 505
    iget-object p0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->bridgeUrl:Ljava/lang/String;

    invoke-virtual {p1}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private isCurrentBridgeDocument(Landroid/webkit/WebView;)Z
    .locals 3

    .line 519
    invoke-virtual {p1}, Landroid/webkit/WebView;->getUrl()Ljava/lang/String;

    move-result-object p1

    const/4 v0, 0x0

    if-nez p1, :cond_0

    return v0

    .line 523
    :cond_0
    invoke-static {p1}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object p1

    .line 524
    const-string v1, "https"

    invoke-virtual {p1}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_1

    iget-object v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->host:Ljava/lang/String;

    .line 525
    invoke-virtual {p1}, Landroid/net/Uri;->getHost()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 526
    invoke-virtual {p1}, Landroid/net/Uri;->getUserInfo()Ljava/lang/String;

    move-result-object v1

    if-nez v1, :cond_1

    .line 527
    invoke-virtual {p1}, Landroid/net/Uri;->getPort()I

    move-result v1

    const/4 v2, -0x1

    if-ne v1, v2, :cond_1

    iget-object p0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->bridgePath:Ljava/lang/String;

    .line 528
    invoke-virtual {p1}, Landroid/net/Uri;->getPath()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_1

    const/4 p0, 0x1

    return p0

    :cond_1
    return v0
.end method

.method public static isSupported()Z
    .locals 2

    const/4 v0, 0x0

    .line 184
    :try_start_0
    const-string v1, "WEB_MESSAGE_LISTENER"

    invoke-static {v1}, Landroidx/webkit/WebViewFeature;->isFeatureSupported(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_0

    const-string v1, "WEB_MESSAGE_ARRAY_BUFFER"

    .line 185
    invoke-static {v1}, Landroidx/webkit/WebViewFeature;->isFeatureSupported(Ljava/lang/String;)Z

    move-result v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v1, :cond_0

    const/4 v0, 0x1

    return v0

    :catchall_0
    move-exception v1

    goto :goto_0

    :cond_0
    return v0

    .line 187
    :goto_0
    invoke-static {v1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return v0
.end method

.method private static isValidPath(Ljava/lang/String;)Z
    .locals 8

    .line 235
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v0

    const/16 v1, 0x80

    const/4 v2, 0x0

    if-le v0, v1, :cond_0

    return v2

    .line 238
    :cond_0
    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    const/4 v1, 0x1

    if-eqz v0, :cond_1

    return v1

    .line 241
    :cond_1
    const-string v0, "/"

    const/4 v3, -0x1

    invoke-virtual {p0, v0, v3}, Ljava/lang/String;->split(Ljava/lang/String;I)[Ljava/lang/String;

    move-result-object p0

    .line 242
    array-length v0, p0

    move v3, v2

    :goto_0
    if-ge v3, v0, :cond_9

    aget-object v4, p0, v3

    .line 243
    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-nez v5, :cond_8

    invoke-virtual {v4, v2}, Ljava/lang/String;->charAt(I)C

    move-result v5

    invoke-static {v5}, Ljava/lang/Character;->isLetterOrDigit(C)Z

    move-result v5

    if-nez v5, :cond_2

    goto :goto_2

    :cond_2
    move v5, v2

    .line 246
    :goto_1
    invoke-virtual {v4}, Ljava/lang/String;->length()I

    move-result v6

    if-ge v5, v6, :cond_7

    .line 247
    invoke-virtual {v4, v5}, Ljava/lang/String;->charAt(I)C

    move-result v6

    const/16 v7, 0x41

    if-lt v6, v7, :cond_3

    const/16 v7, 0x5a

    if-le v6, v7, :cond_6

    :cond_3
    const/16 v7, 0x61

    if-lt v6, v7, :cond_4

    const/16 v7, 0x7a

    if-le v6, v7, :cond_6

    :cond_4
    const/16 v7, 0x30

    if-lt v6, v7, :cond_5

    const/16 v7, 0x39

    if-le v6, v7, :cond_6

    :cond_5
    const/16 v7, 0x5f

    if-eq v6, v7, :cond_6

    const/16 v7, 0x2d

    if-eq v6, v7, :cond_6

    return v2

    :cond_6
    add-int/lit8 v5, v5, 0x1

    goto :goto_1

    :cond_7
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_8
    :goto_2
    return v2

    :cond_9
    return v1
.end method

.method public static isValidSecret(Ljava/lang/String;)Z
    .locals 0

    .line 193
    invoke-static {p0}, Lorg/telegram/utils/proxy/WebProxyTransport;->decodeSecret(Ljava/lang/String;)[B

    move-result-object p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private synthetic lambda$acceptLoop$1(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)V
    .locals 0

    .line 348
    invoke-direct {p0, p1}, Lorg/telegram/utils/proxy/WebProxyTransport;->readLoop(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)V

    return-void
.end method

.method private synthetic lambda$failCarrier$4()V
    .locals 3

    .line 771
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyTransport;->destroyWebView()V

    .line 772
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->lock:Ljava/lang/Object;

    monitor-enter v0

    .line 773
    :try_start_0
    iget-boolean v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->stopped:Z

    if-eqz v1, :cond_0

    .line 774
    monitor-exit v0

    return-void

    :catchall_0
    move-exception p0

    goto :goto_0

    .line 776
    :cond_0
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 777
    new-instance v0, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda2;

    invoke-direct {v0, p0}, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda2;-><init>(Lorg/telegram/utils/proxy/WebProxyTransport;)V

    const-wide/16 v1, 0x3e8

    invoke-static {v0, v1, v2}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    return-void

    .line 776
    :goto_0
    :try_start_1
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p0
.end method

.method private synthetic lambda$onWebMessage$3([B)V
    .locals 0

    .line 553
    invoke-direct {p0, p1}, Lorg/telegram/utils/proxy/WebProxyTransport;->processFrames([B)V

    return-void
.end method

.method private synthetic lambda$stopInternal$0()V
    .locals 2

    .line 316
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->replyProxy:Landroidx/webkit/JavaScriptReplyProxy;

    if-eqz v0, :cond_0

    .line 318
    :try_start_0
    const-string v1, "{\"t\":\"close\"}"

    invoke-virtual {v0, v1}, Landroidx/webkit/JavaScriptReplyProxy;->postMessage(Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 322
    :catch_0
    :cond_0
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyTransport;->destroyWebView()V

    return-void
.end method

.method private static normalizeAddress(Ljava/lang/String;)Lorg/telegram/utils/proxy/WebProxyTransport$Address;
    .locals 3

    const/4 v0, 0x0

    if-nez p0, :cond_0

    return-object v0

    :cond_0
    const/16 v1, 0x2f

    .line 225
    invoke-virtual {p0, v1}, Ljava/lang/String;->indexOf(I)I

    move-result v1

    if-ltz v1, :cond_1

    const/4 v2, 0x0

    .line 226
    invoke-virtual {p0, v2, v1}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v2

    goto :goto_0

    :cond_1
    move-object v2, p0

    :goto_0
    invoke-static {v2}, Lorg/telegram/utils/proxy/WebProxyTransport;->normalizeHost(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    if-ltz v1, :cond_2

    add-int/lit8 v1, v1, 0x1

    .line 227
    invoke-virtual {p0, v1}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p0

    goto :goto_1

    :cond_2
    const-string p0, ""

    .line 228
    :goto_1
    invoke-static {v2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_4

    invoke-static {p0}, Lorg/telegram/utils/proxy/WebProxyTransport;->isValidPath(Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_3

    goto :goto_2

    .line 231
    :cond_3
    new-instance v1, Lorg/telegram/utils/proxy/WebProxyTransport$Address;

    invoke-direct {v1, v2, p0, v0}, Lorg/telegram/utils/proxy/WebProxyTransport$Address;-><init>(Ljava/lang/String;Ljava/lang/String;Lorg/telegram/utils/proxy/WebProxyTransport-IA;)V

    return-object v1

    :cond_4
    :goto_2
    return-object v0
.end method

.method public static normalizeHost(Ljava/lang/String;)Ljava/lang/String;
    .locals 7

    .line 197
    const-string v0, ""

    if-nez p0, :cond_0

    return-object v0

    .line 200
    :cond_0
    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    .line 201
    const-string v1, "."

    invoke-virtual {p0, v1}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v1

    const/4 v2, 0x0

    if-eqz v1, :cond_1

    .line 202
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v1

    add-int/lit8 v1, v1, -0x1

    invoke-virtual {p0, v2, v1}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p0

    :cond_1
    const/4 v1, 0x2

    .line 205
    :try_start_0
    invoke-static {p0, v1}, Ljava/net/IDN;->toASCII(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object p0

    sget-object v1, Ljava/util/Locale;->US:Ljava/util/Locale;

    invoke-virtual {p0, v1}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 209
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v1

    const/16 v3, 0xfd

    if-gt v1, v3, :cond_6

    const/16 v1, 0x2e

    invoke-virtual {p0, v1}, Ljava/lang/String;->indexOf(I)I

    move-result v1

    if-lez v1, :cond_6

    const-string v1, ":"

    invoke-virtual {p0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_6

    const-string v1, "[0-9.]+"

    invoke-virtual {p0, v1}, Ljava/lang/String;->matches(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_2

    goto :goto_2

    .line 212
    :cond_2
    const-string v1, "\\."

    const/4 v3, -0x1

    invoke-virtual {p0, v1, v3}, Ljava/lang/String;->split(Ljava/lang/String;I)[Ljava/lang/String;

    move-result-object v1

    .line 213
    array-length v3, v1

    :goto_0
    if-ge v2, v3, :cond_5

    aget-object v4, v1, v2

    .line 214
    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-nez v5, :cond_4

    invoke-virtual {v4}, Ljava/lang/String;->length()I

    move-result v5

    const/16 v6, 0x3f

    if-gt v5, v6, :cond_4

    const-string v5, "-"

    invoke-virtual {v4, v5}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v6

    if-nez v6, :cond_4

    invoke-virtual {v4, v5}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_3

    goto :goto_1

    :cond_3
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_4
    :goto_1
    return-object v0

    :cond_5
    return-object p0

    :catch_0
    :cond_6
    :goto_2
    return-object v0
.end method

.method private onWebMessage(Landroid/webkit/WebView;Landroidx/webkit/WebMessageCompat;Landroid/net/Uri;ZLandroidx/webkit/JavaScriptReplyProxy;)V
    .locals 1

    .line 537
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->webView:Landroid/webkit/WebView;

    if-ne p1, v0, :cond_4

    if-eqz p4, :cond_4

    if-eqz p3, :cond_4

    iget-object p4, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->origin:Ljava/lang/String;

    .line 540
    invoke-virtual {p3}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object p3

    invoke-virtual {p4, p3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p3

    if-eqz p3, :cond_4

    .line 541
    invoke-direct {p0, p1}, Lorg/telegram/utils/proxy/WebProxyTransport;->isCurrentBridgeDocument(Landroid/webkit/WebView;)Z

    move-result p1

    if-nez p1, :cond_0

    goto :goto_2

    .line 544
    :cond_0
    invoke-virtual {p2}, Landroidx/webkit/WebMessageCompat;->getType()I

    move-result p1

    if-nez p1, :cond_1

    .line 545
    invoke-virtual {p2}, Landroidx/webkit/WebMessageCompat;->getData()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1, p5}, Lorg/telegram/utils/proxy/WebProxyTransport;->handleControl(Ljava/lang/String;Landroidx/webkit/JavaScriptReplyProxy;)V

    return-void

    .line 546
    :cond_1
    invoke-virtual {p2}, Landroidx/webkit/WebMessageCompat;->getType()I

    move-result p1

    const/4 p3, 0x1

    if-ne p1, p3, :cond_4

    .line 547
    iget-object p1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->lock:Ljava/lang/Object;

    monitor-enter p1

    .line 548
    :try_start_0
    iget-boolean p3, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->stopped:Z

    if-nez p3, :cond_3

    iget-object p3, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->replyProxy:Landroidx/webkit/JavaScriptReplyProxy;

    if-eqz p3, :cond_3

    if-eq p3, p5, :cond_2

    goto :goto_0

    .line 551
    :cond_2
    monitor-exit p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 552
    invoke-virtual {p2}, Landroidx/webkit/WebMessageCompat;->getArrayBuffer()[B

    move-result-object p1

    .line 553
    iget-object p2, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->carrierExecutor:Ljava/util/concurrent/ExecutorService;

    new-instance p3, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda5;

    invoke-direct {p3, p0, p1}, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda5;-><init>(Lorg/telegram/utils/proxy/WebProxyTransport;[B)V

    invoke-interface {p2, p3}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    return-void

    :catchall_0
    move-exception p0

    goto :goto_1

    .line 549
    :cond_3
    :goto_0
    :try_start_1
    monitor-exit p1

    return-void

    .line 551
    :goto_1
    monitor-exit p1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p0

    :cond_4
    :goto_2
    return-void
.end method

.method private processFrame(II[B)Z
    .locals 9

    const/4 v0, 0x1

    const/4 v1, 0x0

    if-nez p2, :cond_7

    const/16 p2, 0x11

    if-ne p1, p2, :cond_4

    .line 612
    array-length p2, p3

    if-nez p2, :cond_4

    .line 615
    iget-object p2, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->lock:Ljava/lang/Object;

    monitor-enter p2

    .line 616
    :try_start_0
    iget-boolean p1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->stopped:Z

    if-nez p1, :cond_3

    iget-boolean p1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->carrierConnected:Z

    if-eqz p1, :cond_0

    goto :goto_1

    .line 619
    :cond_0
    iput-boolean v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->carrierConnected:Z

    .line 620
    new-instance p1, Ljava/util/ArrayList;

    iget-object p3, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->streams:Ljava/util/Map;

    invoke-interface {p3}, Ljava/util/Map;->values()Ljava/util/Collection;

    move-result-object p3

    invoke-direct {p1, p3}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    .line 621
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result p3

    :goto_0
    if-ge v1, p3, :cond_1

    invoke-virtual {p1, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    add-int/lit8 v1, v1, 0x1

    check-cast v2, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;

    .line 622
    invoke-static {v2, v0}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fputopened(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;Z)V

    .line 623
    invoke-static {v2}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fgetid(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)I

    move-result v2

    const/4 v3, 0x0

    invoke-direct {p0, v0, v2, v3}, Lorg/telegram/utils/proxy/WebProxyTransport;->sendFrame(II[B)V

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_2

    .line 625
    :cond_1
    iget-object p1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->readyCallback:Lorg/telegram/utils/proxy/WebProxyTransport$ReadyCallback;

    .line 626
    iget-object p0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->lock:Ljava/lang/Object;

    invoke-virtual {p0}, Ljava/lang/Object;->notifyAll()V

    .line 627
    monitor-exit p2
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz p1, :cond_2

    .line 629
    new-instance p0, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda9;

    invoke-direct {p0, p1}, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda9;-><init>(Lorg/telegram/utils/proxy/WebProxyTransport$ReadyCallback;)V

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    :cond_2
    return v0

    .line 617
    :cond_3
    :goto_1
    :try_start_1
    monitor-exit p2

    return v1

    .line 627
    :goto_2
    monitor-exit p2
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p0

    :cond_4
    const/4 p2, 0x5

    if-ne p1, p2, :cond_5

    .line 632
    array-length p2, p3

    const/16 v2, 0x40

    if-gt p2, v2, :cond_5

    const/4 p1, 0x6

    .line 633
    invoke-direct {p0, p1, v1, p3}, Lorg/telegram/utils/proxy/WebProxyTransport;->sendFrame(II[B)V

    return v0

    :cond_5
    const/16 p0, 0x1f

    if-ne p1, p0, :cond_6

    .line 635
    array-length p0, p3

    :cond_6
    return v1

    .line 641
    :cond_7
    iget-object v2, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->lock:Ljava/lang/Object;

    monitor-enter v2

    .line 642
    :try_start_2
    iget-object v3, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->streams:Ljava/util/Map;

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-interface {v3, p2}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;

    const/4 v3, 0x3

    const/4 v4, 0x2

    const/4 v5, 0x4

    if-nez p2, :cond_a

    if-eq p1, v4, :cond_9

    if-eq p1, v5, :cond_9

    if-ne p1, v3, :cond_8

    goto :goto_3

    :cond_8
    move v0, v1

    .line 644
    :cond_9
    :goto_3
    monitor-exit v2

    return v0

    :catchall_1
    move-exception p0

    goto/16 :goto_9

    .line 646
    :cond_a
    monitor-exit v2
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    if-ne p1, v4, :cond_e

    .line 648
    array-length p1, p3

    if-nez p1, :cond_b

    return v1

    .line 651
    :cond_b
    iget-object v2, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->lock:Ljava/lang/Object;

    monitor-enter v2

    .line 652
    :try_start_3
    invoke-static {p2}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fgetreceiveWindow(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)J

    move-result-wide v3

    array-length p1, p3

    int-to-long v6, p1

    cmp-long p1, v3, v6

    if-gez p1, :cond_c

    .line 653
    monitor-exit v2

    return v1

    :catchall_2
    move-exception p0

    goto :goto_7

    .line 655
    :cond_c
    invoke-static {p2}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fgetreceiveWindow(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)J

    move-result-wide v3

    array-length p1, p3

    int-to-long v6, p1

    sub-long/2addr v3, v6

    invoke-static {p2, v3, v4}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fputreceiveWindow(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;J)V

    .line 656
    monitor-exit v2
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_2

    .line 658
    :try_start_4
    invoke-static {p2}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fgetsocket(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)Ljava/net/Socket;

    move-result-object p1

    invoke-virtual {p1}, Ljava/net/Socket;->getOutputStream()Ljava/io/OutputStream;

    move-result-object p1

    .line 659
    invoke-virtual {p1, p3}, Ljava/io/OutputStream;->write([B)V

    .line 660
    iget-object p1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->lock:Ljava/lang/Object;

    monitor-enter p1
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_0

    .line 661
    :try_start_5
    iget-object v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->streams:Ljava/util/Map;

    invoke-static {p2}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fgetid(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-interface {v1, v2}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    if-ne v1, p2, :cond_d

    .line 662
    invoke-static {p2}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fgetreceiveWindow(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)J

    move-result-wide v1

    array-length v3, p3

    int-to-long v3, v3

    add-long/2addr v1, v3

    invoke-static {p2, v1, v2}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fputreceiveWindow(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;J)V

    .line 663
    invoke-static {p2}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fgetid(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)I

    move-result v1

    array-length p3, p3

    invoke-static {p3}, Lorg/telegram/utils/proxy/WebProxyTransport;->uint32(I)[B

    move-result-object p3

    invoke-direct {p0, v5, v1, p3}, Lorg/telegram/utils/proxy/WebProxyTransport;->sendFrame(II[B)V

    goto :goto_4

    :catchall_3
    move-exception p3

    goto :goto_5

    .line 665
    :cond_d
    :goto_4
    monitor-exit p1

    goto :goto_6

    :goto_5
    monitor-exit p1
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_3

    :try_start_6
    throw p3
    :try_end_6
    .catch Ljava/lang/Exception; {:try_start_6 .. :try_end_6} :catch_0

    .line 667
    :catch_0
    invoke-direct {p0, p2, v0}, Lorg/telegram/utils/proxy/WebProxyTransport;->closeStream(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;Z)V

    :goto_6
    return v0

    .line 656
    :goto_7
    :try_start_7
    monitor-exit v2
    :try_end_7
    .catchall {:try_start_7 .. :try_end_7} :catchall_2

    throw p0

    :cond_e
    if-ne p1, v5, :cond_11

    .line 670
    array-length v2, p3

    if-ne v2, v5, :cond_11

    .line 671
    invoke-static {p3}, Ljava/nio/ByteBuffer;->wrap([B)Ljava/nio/ByteBuffer;

    move-result-object p1

    invoke-virtual {p1}, Ljava/nio/ByteBuffer;->getInt()I

    move-result p1

    int-to-long v2, p1

    const-wide v4, 0xffffffffL

    and-long/2addr v2, v4

    const-wide/16 v6, 0x0

    cmp-long p1, v2, v6

    if-nez p1, :cond_f

    return v1

    .line 675
    :cond_f
    iget-object v6, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->lock:Ljava/lang/Object;

    monitor-enter v6

    .line 676
    :try_start_8
    invoke-static {p2}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fgetsendWindow(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)J

    move-result-wide v7

    sub-long/2addr v4, v2

    cmp-long p1, v7, v4

    if-lez p1, :cond_10

    .line 677
    monitor-exit v6

    return v1

    :catchall_4
    move-exception p0

    goto :goto_8

    .line 679
    :cond_10
    invoke-static {p2}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fgetsendWindow(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)J

    move-result-wide v4

    add-long/2addr v4, v2

    invoke-static {p2, v4, v5}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fputsendWindow(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;J)V

    .line 680
    iget-object p0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->lock:Ljava/lang/Object;

    invoke-virtual {p0}, Ljava/lang/Object;->notifyAll()V

    .line 681
    monitor-exit v6

    return v0

    :goto_8
    monitor-exit v6
    :try_end_8
    .catchall {:try_start_8 .. :try_end_8} :catchall_4

    throw p0

    :cond_11
    if-ne p1, v3, :cond_12

    .line 683
    array-length p1, p3

    if-nez p1, :cond_12

    .line 684
    invoke-direct {p0, p2, v1}, Lorg/telegram/utils/proxy/WebProxyTransport;->closeStream(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;Z)V

    return v0

    :cond_12
    return v1

    .line 646
    :goto_9
    :try_start_9
    monitor-exit v2
    :try_end_9
    .catchall {:try_start_9 .. :try_end_9} :catchall_1

    throw p0
.end method

.method private processFrames([B)V
    .locals 12

    const/4 v0, 0x0

    move v1, v0

    .line 582
    :goto_0
    array-length v2, p1

    if-ge v1, v2, :cond_4

    .line 583
    array-length v2, p1

    sub-int/2addr v2, v1

    const/16 v3, 0x8

    if-ge v2, v3, :cond_0

    .line 584
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyTransport;->failCarrier()V

    return-void

    .line 587
    :cond_0
    aget-byte v2, p1, v1

    and-int/lit16 v2, v2, 0xff

    add-int/lit8 v4, v1, 0x1

    .line 588
    aget-byte v4, p1, v4

    and-int/lit16 v4, v4, 0xff

    const/16 v5, 0x10

    shl-int/2addr v4, v5

    add-int/lit8 v6, v1, 0x2

    aget-byte v6, p1, v6

    and-int/lit16 v6, v6, 0xff

    shl-int/2addr v6, v3

    or-int/2addr v4, v6

    add-int/lit8 v6, v1, 0x3

    aget-byte v6, p1, v6

    and-int/lit16 v6, v6, 0xff

    or-int/2addr v4, v6

    add-int/lit8 v6, v1, 0x4

    .line 591
    aget-byte v6, p1, v6

    int-to-long v6, v6

    const-wide/16 v8, 0xff

    and-long/2addr v6, v8

    const/16 v10, 0x18

    shl-long/2addr v6, v10

    add-int/lit8 v10, v1, 0x5

    aget-byte v10, p1, v10

    int-to-long v10, v10

    and-long/2addr v10, v8

    shl-long/2addr v10, v5

    or-long v5, v6, v10

    add-int/lit8 v7, v1, 0x6

    aget-byte v7, p1, v7

    int-to-long v10, v7

    and-long/2addr v10, v8

    shl-long/2addr v10, v3

    or-long/2addr v5, v10

    add-int/lit8 v3, v1, 0x7

    aget-byte v3, p1, v3

    int-to-long v10, v3

    and-long v7, v10, v8

    or-long/2addr v5, v7

    add-int/lit8 v1, v1, 0x8

    int-to-long v7, v1

    add-long/2addr v7, v5

    const-wide/32 v9, 0x100000

    cmp-long v3, v5, v9

    if-gtz v3, :cond_3

    .line 596
    array-length v3, p1

    int-to-long v9, v3

    cmp-long v3, v7, v9

    if-lez v3, :cond_1

    goto :goto_1

    :cond_1
    long-to-int v3, v5

    .line 600
    new-array v5, v3, [B

    .line 601
    invoke-static {p1, v1, v5, v0, v3}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 602
    invoke-direct {p0, v2, v4, v5}, Lorg/telegram/utils/proxy/WebProxyTransport;->processFrame(II[B)Z

    move-result v1

    if-nez v1, :cond_2

    .line 603
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyTransport;->failCarrier()V

    return-void

    :cond_2
    long-to-int v1, v7

    goto :goto_0

    .line 597
    :cond_3
    :goto_1
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyTransport;->failCarrier()V

    :cond_4
    return-void
.end method

.method private static randomToken(I)Ljava/lang/String;
    .locals 1

    .line 831
    new-array p0, p0, [B

    .line 832
    new-instance v0, Ljava/security/SecureRandom;

    invoke-direct {v0}, Ljava/security/SecureRandom;-><init>()V

    invoke-virtual {v0, p0}, Ljava/security/SecureRandom;->nextBytes([B)V

    const/16 v0, 0xb

    .line 833
    invoke-static {p0, v0}, Landroid/util/Base64;->encodeToString([BI)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private readLoop(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)V
    .locals 10

    const/high16 v0, 0x10000

    .line 372
    new-array v0, v0, [B

    const/4 v1, 0x1

    .line 374
    :try_start_0
    invoke-static {p1}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fgetsocket(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)Ljava/net/Socket;

    move-result-object v2

    invoke-virtual {v2}, Ljava/net/Socket;->getInputStream()Ljava/io/InputStream;

    move-result-object v2

    .line 377
    :goto_0
    iget-object v3, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->lock:Ljava/lang/Object;

    monitor-enter v3
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 378
    :goto_1
    :try_start_1
    iget-boolean v4, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->stopped:Z

    if-nez v4, :cond_1

    iget-object v4, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->streams:Ljava/util/Map;

    invoke-static {p1}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fgetid(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)I

    move-result v5

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    invoke-interface {v4, v5}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    if-ne v4, p1, :cond_1

    iget-boolean v4, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->carrierConnected:Z

    if-eqz v4, :cond_0

    invoke-static {p1}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fgetopened(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)Z

    move-result v4

    if-eqz v4, :cond_0

    invoke-static {p1}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fgetsendWindow(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)J

    move-result-wide v4

    const-wide/16 v6, 0x0

    cmp-long v4, v4, v6

    if-nez v4, :cond_1

    goto :goto_2

    :catchall_0
    move-exception v0

    goto/16 :goto_6

    .line 380
    :cond_0
    :goto_2
    iget-object v4, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->lock:Ljava/lang/Object;

    invoke-virtual {v4}, Ljava/lang/Object;->wait()V

    goto :goto_1

    .line 382
    :cond_1
    iget-boolean v4, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->stopped:Z

    if-nez v4, :cond_7

    iget-object v4, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->streams:Ljava/util/Map;

    invoke-static {p1}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fgetid(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)I

    move-result v5

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    invoke-interface {v4, v5}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    if-eq v4, p1, :cond_2

    goto :goto_5

    .line 385
    :cond_2
    invoke-static {p1}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fgetsendWindow(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)J

    move-result-wide v4

    const-wide/32 v6, 0x10000

    invoke-static {v6, v7, v4, v5}, Ljava/lang/Math;->min(JJ)J

    move-result-wide v4

    long-to-int v4, v4

    .line 386
    monitor-exit v3
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    const/4 v3, 0x0

    .line 387
    :try_start_2
    invoke-virtual {v2, v0, v3, v4}, Ljava/io/InputStream;->read([BII)I

    move-result v4

    if-gez v4, :cond_3

    .line 389
    invoke-direct {p0, p1, v1}, Lorg/telegram/utils/proxy/WebProxyTransport;->closeStream(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;Z)V

    return-void

    :cond_3
    if-nez v4, :cond_4

    goto :goto_0

    .line 395
    :cond_4
    new-array v5, v4, [B

    .line 396
    invoke-static {v0, v3, v5, v3, v4}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 397
    iget-object v3, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->lock:Ljava/lang/Object;

    monitor-enter v3
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    .line 398
    :try_start_3
    iget-boolean v6, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->stopped:Z

    if-nez v6, :cond_6

    iget-object v6, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->streams:Ljava/util/Map;

    invoke-static {p1}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fgetid(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)I

    move-result v7

    invoke-static {v7}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v7

    invoke-interface {v6, v7}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    if-ne v6, p1, :cond_6

    iget-boolean v6, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->carrierConnected:Z

    if-nez v6, :cond_5

    goto :goto_3

    .line 401
    :cond_5
    invoke-static {p1}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fgetsendWindow(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)J

    move-result-wide v6

    int-to-long v8, v4

    sub-long/2addr v6, v8

    invoke-static {p1, v6, v7}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fputsendWindow(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;J)V

    .line 402
    invoke-static {p1}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fgetid(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)I

    move-result v4

    const/4 v6, 0x2

    invoke-direct {p0, v6, v4, v5}, Lorg/telegram/utils/proxy/WebProxyTransport;->sendFrame(II[B)V

    .line 403
    monitor-exit v3

    goto/16 :goto_0

    :catchall_1
    move-exception v0

    goto :goto_4

    .line 399
    :cond_6
    :goto_3
    monitor-exit v3

    return-void

    .line 403
    :goto_4
    monitor-exit v3
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    :try_start_4
    throw v0
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_0

    .line 383
    :cond_7
    :goto_5
    :try_start_5
    monitor-exit v3

    return-void

    .line 386
    :goto_6
    monitor-exit v3
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    :try_start_6
    throw v0
    :try_end_6
    .catch Ljava/lang/Exception; {:try_start_6 .. :try_end_6} :catch_0

    .line 406
    :catch_0
    invoke-direct {p0, p1, v1}, Lorg/telegram/utils/proxy/WebProxyTransport;->closeStream(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;Z)V

    return-void
.end method

.method private sendFrame(II[B)V
    .locals 2

    if-nez p3, :cond_0

    const/4 p3, 0x0

    .line 708
    new-array p3, p3, [B

    .line 710
    :cond_0
    array-length v0, p3

    add-int/lit8 v0, v0, 0x8

    invoke-static {v0}, Ljava/nio/ByteBuffer;->allocate(I)Ljava/nio/ByteBuffer;

    move-result-object v0

    int-to-byte p1, p1

    .line 711
    invoke-virtual {v0, p1}, Ljava/nio/ByteBuffer;->put(B)Ljava/nio/ByteBuffer;

    move-result-object p1

    shr-int/lit8 v0, p2, 0x10

    int-to-byte v0, v0

    .line 712
    invoke-virtual {p1, v0}, Ljava/nio/ByteBuffer;->put(B)Ljava/nio/ByteBuffer;

    move-result-object p1

    shr-int/lit8 v0, p2, 0x8

    int-to-byte v0, v0

    .line 713
    invoke-virtual {p1, v0}, Ljava/nio/ByteBuffer;->put(B)Ljava/nio/ByteBuffer;

    move-result-object p1

    int-to-byte p2, p2

    .line 714
    invoke-virtual {p1, p2}, Ljava/nio/ByteBuffer;->put(B)Ljava/nio/ByteBuffer;

    move-result-object p1

    array-length p2, p3

    .line 715
    invoke-virtual {p1, p2}, Ljava/nio/ByteBuffer;->putInt(I)Ljava/nio/ByteBuffer;

    move-result-object p1

    .line 716
    invoke-virtual {p1, p3}, Ljava/nio/ByteBuffer;->put([B)Ljava/nio/ByteBuffer;

    move-result-object p1

    .line 717
    invoke-virtual {p1}, Ljava/nio/ByteBuffer;->array()[B

    move-result-object p1

    .line 718
    iget-object p2, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->lock:Ljava/lang/Object;

    monitor-enter p2

    .line 719
    :try_start_0
    iget-boolean p3, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->stopped:Z

    if-nez p3, :cond_2

    iget-object p3, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->outbound:Ljava/util/ArrayDeque;

    invoke-virtual {p3}, Ljava/util/ArrayDeque;->size()I

    move-result p3

    const/16 v0, 0x2000

    if-ge p3, v0, :cond_2

    iget p3, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->outboundBytes:I

    array-length v0, p1

    const/high16 v1, 0x4000000

    sub-int/2addr v1, v0

    if-le p3, v1, :cond_1

    goto :goto_0

    .line 724
    :cond_1
    iget-object p3, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->outbound:Ljava/util/ArrayDeque;

    invoke-virtual {p3, p1}, Ljava/util/ArrayDeque;->add(Ljava/lang/Object;)Z

    .line 725
    iget p3, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->outboundBytes:I

    array-length p1, p1

    add-int/2addr p3, p1

    iput p3, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->outboundBytes:I

    .line 726
    monitor-exit p2
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 727
    new-instance p1, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda7;

    invoke-direct {p1, p0}, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda7;-><init>(Lorg/telegram/utils/proxy/WebProxyTransport;)V

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void

    :catchall_0
    move-exception p0

    goto :goto_1

    .line 721
    :cond_2
    :goto_0
    :try_start_1
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyTransport;->failCarrier()V

    .line 722
    monitor-exit p2

    return-void

    .line 726
    :goto_1
    monitor-exit p2
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p0
.end method

.method public static start(Ljava/lang/String;Ljava/lang/String;)I
    .locals 5

    .line 101
    invoke-static {p0}, Lorg/telegram/utils/proxy/WebProxyTransport;->normalizeAddress(Ljava/lang/String;)Lorg/telegram/utils/proxy/WebProxyTransport$Address;

    move-result-object p0

    .line 102
    invoke-static {p1}, Lorg/telegram/utils/proxy/WebProxyTransport;->decodeSecret(Ljava/lang/String;)[B

    move-result-object v0

    const/4 v1, 0x0

    if-eqz p0, :cond_4

    if-eqz v0, :cond_4

    .line 103
    invoke-static {}, Lorg/telegram/utils/proxy/WebProxyTransport;->isSupported()Z

    move-result v2

    if-nez v2, :cond_0

    goto :goto_1

    .line 106
    :cond_0
    sget-object v2, Lorg/telegram/utils/proxy/WebProxyTransport;->staticLock:Ljava/lang/Object;

    monitor-enter v2

    .line 107
    :try_start_0
    sget-object v3, Lorg/telegram/utils/proxy/WebProxyTransport;->instance:Lorg/telegram/utils/proxy/WebProxyTransport;

    if-eqz v3, :cond_1

    iget-object v3, v3, Lorg/telegram/utils/proxy/WebProxyTransport;->address:Ljava/lang/String;

    invoke-static {p0}, Lorg/telegram/utils/proxy/WebProxyTransport$Address;->-$$Nest$fgetvalue(Lorg/telegram/utils/proxy/WebProxyTransport$Address;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_1

    sget-object v3, Lorg/telegram/utils/proxy/WebProxyTransport;->instance:Lorg/telegram/utils/proxy/WebProxyTransport;

    iget-object v3, v3, Lorg/telegram/utils/proxy/WebProxyTransport;->secret:Ljava/lang/String;

    invoke-virtual {v3, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_1

    .line 108
    sget-object p0, Lorg/telegram/utils/proxy/WebProxyTransport;->instance:Lorg/telegram/utils/proxy/WebProxyTransport;

    iget-object p0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->serverSocket:Ljava/net/ServerSocket;

    invoke-virtual {p0}, Ljava/net/ServerSocket;->getLocalPort()I

    move-result p0

    monitor-exit v2

    return p0

    :catchall_0
    move-exception p0

    goto :goto_0

    .line 110
    :cond_1
    sget-object v3, Lorg/telegram/utils/proxy/WebProxyTransport;->instance:Lorg/telegram/utils/proxy/WebProxyTransport;

    const/4 v4, 0x0

    if-eqz v3, :cond_2

    .line 111
    invoke-direct {v3}, Lorg/telegram/utils/proxy/WebProxyTransport;->stopInternal()V

    .line 112
    sput-object v4, Lorg/telegram/utils/proxy/WebProxyTransport;->instance:Lorg/telegram/utils/proxy/WebProxyTransport;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 115
    :cond_2
    :try_start_1
    new-instance v3, Lorg/telegram/utils/proxy/WebProxyTransport;

    invoke-direct {v3, p0, p1, v0}, Lorg/telegram/utils/proxy/WebProxyTransport;-><init>(Lorg/telegram/utils/proxy/WebProxyTransport$Address;Ljava/lang/String;[B)V

    sput-object v3, Lorg/telegram/utils/proxy/WebProxyTransport;->instance:Lorg/telegram/utils/proxy/WebProxyTransport;

    .line 116
    invoke-direct {v3}, Lorg/telegram/utils/proxy/WebProxyTransport;->startInternal()V

    .line 117
    sget-object p0, Lorg/telegram/utils/proxy/WebProxyTransport;->instance:Lorg/telegram/utils/proxy/WebProxyTransport;

    iget-object p0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->serverSocket:Ljava/net/ServerSocket;

    invoke-virtual {p0}, Ljava/net/ServerSocket;->getLocalPort()I

    move-result p0
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    :try_start_2
    monitor-exit v2

    return p0

    :catch_0
    move-exception p0

    .line 119
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 120
    sget-object p0, Lorg/telegram/utils/proxy/WebProxyTransport;->instance:Lorg/telegram/utils/proxy/WebProxyTransport;

    if-eqz p0, :cond_3

    .line 121
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyTransport;->stopInternal()V

    .line 122
    sput-object v4, Lorg/telegram/utils/proxy/WebProxyTransport;->instance:Lorg/telegram/utils/proxy/WebProxyTransport;

    .line 124
    :cond_3
    monitor-exit v2

    return v1

    .line 126
    :goto_0
    monitor-exit v2
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw p0

    :cond_4
    :goto_1
    return v1
.end method

.method public static startConnectionCheck(Ljava/lang/String;Ljava/lang/String;Lorg/telegram/utils/proxy/WebProxyTransport$ReadyCallback;)I
    .locals 5

    .line 139
    invoke-static {p0}, Lorg/telegram/utils/proxy/WebProxyTransport;->normalizeAddress(Ljava/lang/String;)Lorg/telegram/utils/proxy/WebProxyTransport$Address;

    move-result-object p0

    .line 140
    invoke-static {p1}, Lorg/telegram/utils/proxy/WebProxyTransport;->decodeSecret(Ljava/lang/String;)[B

    move-result-object v0

    const/4 v1, 0x0

    if-eqz p0, :cond_3

    if-eqz v0, :cond_3

    .line 141
    invoke-static {}, Lorg/telegram/utils/proxy/WebProxyTransport;->isSupported()Z

    move-result v2

    if-nez v2, :cond_0

    goto :goto_2

    .line 144
    :cond_0
    sget-object v2, Lorg/telegram/utils/proxy/WebProxyTransport;->staticLock:Ljava/lang/Object;

    monitor-enter v2

    .line 145
    :try_start_0
    sget-object v3, Lorg/telegram/utils/proxy/WebProxyTransport;->connectionTestInstance:Lorg/telegram/utils/proxy/WebProxyTransport;

    const/4 v4, 0x0

    if-eqz v3, :cond_1

    .line 146
    invoke-direct {v3}, Lorg/telegram/utils/proxy/WebProxyTransport;->stopInternal()V

    .line 147
    sput-object v4, Lorg/telegram/utils/proxy/WebProxyTransport;->connectionTestInstance:Lorg/telegram/utils/proxy/WebProxyTransport;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    .line 150
    :cond_1
    :goto_0
    :try_start_1
    new-instance v3, Lorg/telegram/utils/proxy/WebProxyTransport;

    invoke-direct {v3, p0, p1, v0}, Lorg/telegram/utils/proxy/WebProxyTransport;-><init>(Lorg/telegram/utils/proxy/WebProxyTransport$Address;Ljava/lang/String;[B)V

    sput-object v3, Lorg/telegram/utils/proxy/WebProxyTransport;->connectionTestInstance:Lorg/telegram/utils/proxy/WebProxyTransport;

    .line 151
    iput-object p2, v3, Lorg/telegram/utils/proxy/WebProxyTransport;->readyCallback:Lorg/telegram/utils/proxy/WebProxyTransport$ReadyCallback;

    .line 152
    invoke-direct {v3}, Lorg/telegram/utils/proxy/WebProxyTransport;->startInternal()V

    .line 153
    sget-object p0, Lorg/telegram/utils/proxy/WebProxyTransport;->connectionTestInstance:Lorg/telegram/utils/proxy/WebProxyTransport;

    iget-object p0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->serverSocket:Ljava/net/ServerSocket;

    invoke-virtual {p0}, Ljava/net/ServerSocket;->getLocalPort()I

    move-result p0
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    :try_start_2
    monitor-exit v2

    return p0

    :catch_0
    move-exception p0

    .line 155
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 156
    sget-object p0, Lorg/telegram/utils/proxy/WebProxyTransport;->connectionTestInstance:Lorg/telegram/utils/proxy/WebProxyTransport;

    if-eqz p0, :cond_2

    .line 157
    invoke-direct {p0}, Lorg/telegram/utils/proxy/WebProxyTransport;->stopInternal()V

    .line 158
    sput-object v4, Lorg/telegram/utils/proxy/WebProxyTransport;->connectionTestInstance:Lorg/telegram/utils/proxy/WebProxyTransport;

    .line 160
    :cond_2
    monitor-exit v2

    return v1

    .line 162
    :goto_1
    monitor-exit v2
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw p0

    :cond_3
    :goto_2
    return v1
.end method

.method private startInternal()V
    .locals 2

    .line 281
    invoke-static {}, Lorg/telegram/ui/Components/ForegroundDetector;->getInstance()Lorg/telegram/ui/Components/ForegroundDetector;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 283
    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/ForegroundDetector;->addListener(Lorg/telegram/ui/Components/ForegroundDetector$Listener;)V

    .line 285
    :cond_0
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->ioExecutor:Ljava/util/concurrent/ExecutorService;

    new-instance v1, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda1;

    invoke-direct {v1, p0}, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda1;-><init>(Lorg/telegram/utils/proxy/WebProxyTransport;)V

    invoke-interface {v0, v1}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    .line 286
    new-instance v0, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda2;

    invoke-direct {v0, p0}, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda2;-><init>(Lorg/telegram/utils/proxy/WebProxyTransport;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static stop()V
    .locals 2

    .line 130
    sget-object v0, Lorg/telegram/utils/proxy/WebProxyTransport;->staticLock:Ljava/lang/Object;

    monitor-enter v0

    .line 131
    :try_start_0
    sget-object v1, Lorg/telegram/utils/proxy/WebProxyTransport;->instance:Lorg/telegram/utils/proxy/WebProxyTransport;

    if-eqz v1, :cond_0

    .line 132
    invoke-direct {v1}, Lorg/telegram/utils/proxy/WebProxyTransport;->stopInternal()V

    const/4 v1, 0x0

    .line 133
    sput-object v1, Lorg/telegram/utils/proxy/WebProxyTransport;->instance:Lorg/telegram/utils/proxy/WebProxyTransport;

    goto :goto_0

    :catchall_0
    move-exception v1

    goto :goto_1

    .line 135
    :cond_0
    :goto_0
    monitor-exit v0

    return-void

    :goto_1
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1
.end method

.method public static stopConnectionCheck()V
    .locals 2

    .line 166
    sget-object v0, Lorg/telegram/utils/proxy/WebProxyTransport;->staticLock:Ljava/lang/Object;

    monitor-enter v0

    .line 167
    :try_start_0
    sget-object v1, Lorg/telegram/utils/proxy/WebProxyTransport;->connectionTestInstance:Lorg/telegram/utils/proxy/WebProxyTransport;

    if-eqz v1, :cond_0

    .line 168
    invoke-direct {v1}, Lorg/telegram/utils/proxy/WebProxyTransport;->stopInternal()V

    const/4 v1, 0x0

    .line 169
    sput-object v1, Lorg/telegram/utils/proxy/WebProxyTransport;->connectionTestInstance:Lorg/telegram/utils/proxy/WebProxyTransport;

    goto :goto_0

    :catchall_0
    move-exception v1

    goto :goto_1

    .line 171
    :cond_0
    :goto_0
    monitor-exit v0

    return-void

    :goto_1
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1
.end method

.method private stopInternal()V
    .locals 4

    .line 291
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->lock:Ljava/lang/Object;

    monitor-enter v0

    .line 292
    :try_start_0
    iget-boolean v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->stopped:Z

    if-eqz v1, :cond_0

    .line 293
    monitor-exit v0

    return-void

    :catchall_0
    move-exception p0

    goto :goto_1

    :cond_0
    const/4 v1, 0x1

    .line 295
    iput-boolean v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->stopped:Z

    const/4 v1, 0x0

    .line 296
    iput-boolean v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->carrierConnected:Z

    .line 297
    iput-boolean v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->restartScheduled:Z

    .line 298
    new-instance v2, Ljava/util/ArrayList;

    iget-object v3, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->streams:Ljava/util/Map;

    invoke-interface {v3}, Ljava/util/Map;->values()Ljava/util/Collection;

    move-result-object v3

    invoke-direct {v2, v3}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    .line 299
    iget-object v3, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->streams:Ljava/util/Map;

    invoke-interface {v3}, Ljava/util/Map;->clear()V

    .line 300
    iget-object v3, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->outbound:Ljava/util/ArrayDeque;

    invoke-virtual {v3}, Ljava/util/ArrayDeque;->clear()V

    .line 301
    iput v1, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->outboundBytes:I

    .line 302
    iget-object v3, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->lock:Ljava/lang/Object;

    invoke-virtual {v3}, Ljava/lang/Object;->notifyAll()V

    .line 303
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 305
    :try_start_1
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->serverSocket:Ljava/net/ServerSocket;

    invoke-virtual {v0}, Ljava/net/ServerSocket;->close()V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    .line 308
    :catch_0
    invoke-static {}, Lorg/telegram/ui/Components/ForegroundDetector;->getInstance()Lorg/telegram/ui/Components/ForegroundDetector;

    move-result-object v0

    if-eqz v0, :cond_1

    .line 310
    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/ForegroundDetector;->removeListener(Lorg/telegram/ui/Components/ForegroundDetector$Listener;)V

    .line 312
    :cond_1
    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v0

    :goto_0
    if-ge v1, v0, :cond_2

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    add-int/lit8 v1, v1, 0x1

    check-cast v3, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;

    .line 313
    invoke-static {v3}, Lorg/telegram/utils/proxy/WebProxyTransport$Stream;->-$$Nest$fgetsocket(Lorg/telegram/utils/proxy/WebProxyTransport$Stream;)Ljava/net/Socket;

    move-result-object v3

    invoke-static {v3}, Lorg/telegram/utils/proxy/WebProxyTransport;->closeSocket(Ljava/net/Socket;)V

    goto :goto_0

    .line 315
    :cond_2
    new-instance v0, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0}, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/utils/proxy/WebProxyTransport;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    .line 324
    iget-object v0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->ioExecutor:Ljava/util/concurrent/ExecutorService;

    invoke-interface {v0}, Ljava/util/concurrent/ExecutorService;->shutdownNow()Ljava/util/List;

    .line 325
    iget-object p0, p0, Lorg/telegram/utils/proxy/WebProxyTransport;->carrierExecutor:Ljava/util/concurrent/ExecutorService;

    invoke-interface {p0}, Ljava/util/concurrent/ExecutorService;->shutdownNow()Ljava/util/List;

    return-void

    .line 303
    :goto_1
    :try_start_2
    monitor-exit v0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw p0
.end method

.method private static uint32(I)[B
    .locals 1

    const/4 v0, 0x4

    .line 837
    invoke-static {v0}, Ljava/nio/ByteBuffer;->allocate(I)Ljava/nio/ByteBuffer;

    move-result-object v0

    invoke-virtual {v0, p0}, Ljava/nio/ByteBuffer;->putInt(I)Ljava/nio/ByteBuffer;

    move-result-object p0

    invoke-virtual {p0}, Ljava/nio/ByteBuffer;->array()[B

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public onBecameBackground()V
    .locals 0

    return-void
.end method

.method public onBecameForeground()V
    .locals 1

    .line 783
    new-instance v0, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda2;

    invoke-direct {v0, p0}, Lorg/telegram/utils/proxy/WebProxyTransport$$ExternalSyntheticLambda2;-><init>(Lorg/telegram/utils/proxy/WebProxyTransport;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method
