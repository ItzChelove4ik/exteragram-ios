.class public abstract Lorg/chromium/net/impl/CronetLoggerFactory;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final TAG:Ljava/lang/String; = "CronetLoggerFactory"

.field private static sLogger:Lorg/chromium/net/impl/CronetLogger;


# direct methods
.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method public static createLogger(Landroid/content/Context;Lorg/chromium/net/impl/CronetLogger$CronetSource;)Lorg/chromium/net/impl/CronetLogger;
    .locals 3

    .line 31
    const-class v0, Lorg/chromium/net/impl/CronetLoggerFactory;

    monitor-enter v0

    .line 32
    :try_start_0
    sget-object v1, Lorg/chromium/net/impl/CronetLoggerFactory;->sLogger:Lorg/chromium/net/impl/CronetLogger;

    if-nez v1, :cond_0

    sget v1, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v2, 0x1e

    if-lt v1, v2, :cond_0

    .line 35
    invoke-static {p0, p1}, Lorg/chromium/net/impl/CronetManifest;->isAppOptedInForTelemetry(Landroid/content/Context;Lorg/chromium/net/impl/CronetLogger$CronetSource;)Z

    move-result p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz p0, :cond_0

    .line 37
    :try_start_1
    new-instance p0, Lorg/chromium/net/telemetry/CronetLoggerImpl;

    const/4 p1, 0x1

    invoke-direct {p0, p1}, Lorg/chromium/net/telemetry/CronetLoggerImpl;-><init>(I)V

    sput-object p0, Lorg/chromium/net/impl/CronetLoggerFactory;->sLogger:Lorg/chromium/net/impl/CronetLogger;
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    :catch_0
    move-exception p0

    .line 41
    :try_start_2
    sget-object p1, Lorg/chromium/net/impl/CronetLoggerFactory;->TAG:Ljava/lang/String;

    const-string v1, "Exception creating an instance of CronetLoggerImpl"

    invoke-static {p1, v1, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 44
    :cond_0
    :goto_0
    sget-object p0, Lorg/chromium/net/impl/CronetLoggerFactory;->sLogger:Lorg/chromium/net/impl/CronetLogger;

    if-nez p0, :cond_1

    new-instance p0, Lorg/chromium/net/impl/NoOpLogger;

    invoke-direct {p0}, Lorg/chromium/net/impl/NoOpLogger;-><init>()V

    sput-object p0, Lorg/chromium/net/impl/CronetLoggerFactory;->sLogger:Lorg/chromium/net/impl/CronetLogger;

    .line 45
    :cond_1
    sget-object p0, Lorg/chromium/net/impl/CronetLoggerFactory;->sLogger:Lorg/chromium/net/impl/CronetLogger;

    monitor-exit v0

    return-object p0

    .line 46
    :goto_1
    monitor-exit v0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw p0
.end method
