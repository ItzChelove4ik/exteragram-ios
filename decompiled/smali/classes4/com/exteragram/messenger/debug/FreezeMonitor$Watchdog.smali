.class final Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/ui/Components/ForegroundDetector$Listener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/debug/FreezeMonitor;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Watchdog"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0008\u0002\n\u0002\u0010\u0002\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0008\u0003\u0008\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0008\u0010\u000f\u001a\u00020\u0010H\u0016J\u0008\u0010\u0011\u001a\u00020\u0010H\u0016J\u0006\u0010\u0012\u001a\u00020\u0010J\u0008\u0010\u0013\u001a\u00020\u0010H\u0002J\u0008\u0010\u0014\u001a\u00020\u0010H\u0002J\u001a\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u0016H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0008\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000c\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"
    }
    d2 = {
        "Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;",
        "Lorg/telegram/ui/Components/ForegroundDetector$Listener;",
        "<init>",
        "()V",
        "queue",
        "Lorg/telegram/messenger/DispatchQueue;",
        "mainHandler",
        "Landroid/os/Handler;",
        "mainThread",
        "Ljava/lang/Thread;",
        "checkRunnable",
        "Ljava/lang/Runnable;",
        "running",
        "",
        "stopped",
        "onBecameForeground",
        "",
        "onBecameBackground",
        "stop",
        "updateRunning",
        "check",
        "sample",
        "Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;",
        "ping",
        "Ljava/util/concurrent/CountDownLatch;",
        "posted",
        "",
        "record",
        "freeze",
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
        "SMAP\nFreezeMonitor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FreezeMonitor.kt\ncom/exteragram/messenger/debug/FreezeMonitor$Watchdog\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,512:1\n1#2:513\n1924#3,3:514\n1080#3:517\n777#3:518\n873#3:519\n874#3:522\n1915#3,2:523\n1080#3:525\n1915#3,2:526\n1080#3:528\n1915#3:529\n1915#3,2:530\n1916#3:532\n13213#4,2:520\n*S KotlinDebug\n*F\n+ 1 FreezeMonitor.kt\ncom/exteragram/messenger/debug/FreezeMonitor$Watchdog\n*L\n437#1:514,3\n479#1:517\n485#1:518\n485#1:519\n485#1:522\n488#1:523,2\n490#1:525\n491#1:526,2\n493#1:528\n493#1:529\n495#1:530,2\n493#1:532\n485#1:520,2\n*E\n"
    }
.end annotation


# instance fields
.field private final checkRunnable:Ljava/lang/Runnable;

.field private final mainHandler:Landroid/os/Handler;

.field private final mainThread:Ljava/lang/Thread;

.field private final queue:Lorg/telegram/messenger/DispatchQueue;

.field private volatile running:Z

.field private volatile stopped:Z


# direct methods
.method public static $r8$lambda$7qpSQkuRdaiCfCudJ_cXY00_KCw(Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;)V
    .locals 3

    .line 508
    sget-object v0, Lcom/exteragram/messenger/debug/FreezeMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/FreezeMonitor;

    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->getDurationMs()J

    move-result-wide v1

    invoke-static {v0, v1, v2}, Lcom/exteragram/messenger/debug/FreezeMonitor;->access$showFreezeBulletin(Lcom/exteragram/messenger/debug/FreezeMonitor;J)V

    return-void
.end method

.method public static $r8$lambda$S_pSjC2swheQoZFMbm_beXGVDIQ(Ljava/util/concurrent/CountDownLatch;)V
    .locals 0

    .line 396
    invoke-virtual {p0}, Ljava/util/concurrent/CountDownLatch;->countDown()V

    return-void
.end method

.method public static $r8$lambda$h1v9OQjRKZY3Ae1g5HeNBHyC0Xg(Ljava/util/Map$Entry;)Ljava/lang/CharSequence;
    .locals 2

    .line 479
    invoke-interface {p0}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v0

    invoke-interface {p0}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object p0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v0, " "

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$qrdnrFM8lNht_nQeViM9Po0aSSY(Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;)V
    .locals 0

    .line 355
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->check()V

    return-void
.end method

.method public static $r8$lambda$uyHhUaXyy6G60GDDS44GZP7uBiU(Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;)V
    .locals 2

    .line 384
    iget-object v0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->queue:Lorg/telegram/messenger/DispatchQueue;

    iget-object v1, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->checkRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/DispatchQueue;->cancelRunnable(Ljava/lang/Runnable;)V

    .line 385
    iget-boolean v0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->running:Z

    if-eqz v0, :cond_0

    .line 386
    iget-object v0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->queue:Lorg/telegram/messenger/DispatchQueue;

    iget-object p0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->checkRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, p0}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    :cond_0
    return-void
.end method

.method public constructor <init>()V
    .locals 2

    .line 350
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 352
    new-instance v0, Lorg/telegram/messenger/DispatchQueue;

    const-string v1, "freezeMonitorQueue"

    invoke-direct {v0, v1}, Lorg/telegram/messenger/DispatchQueue;-><init>(Ljava/lang/String;)V

    iput-object v0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->queue:Lorg/telegram/messenger/DispatchQueue;

    .line 353
    new-instance v0, Landroid/os/Handler;

    invoke-static {}, Landroid/os/Looper;->getMainLooper()Landroid/os/Looper;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    iput-object v0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->mainHandler:Landroid/os/Handler;

    .line 354
    invoke-static {}, Landroid/os/Looper;->getMainLooper()Landroid/os/Looper;

    move-result-object v0

    invoke-virtual {v0}, Landroid/os/Looper;->getThread()Ljava/lang/Thread;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->mainThread:Ljava/lang/Thread;

    .line 355
    new-instance v0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$$ExternalSyntheticLambda1;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;)V

    iput-object v0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->checkRunnable:Ljava/lang/Runnable;

    .line 363
    invoke-static {}, Lorg/telegram/ui/Components/ForegroundDetector;->getInstance()Lorg/telegram/ui/Components/ForegroundDetector;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/ForegroundDetector;->addListener(Lorg/telegram/ui/Components/ForegroundDetector$Listener;)V

    .line 364
    :cond_0
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->updateRunning()V

    return-void
.end method

.method private final check()V
    .locals 6

    .line 392
    iget-boolean v0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->running:Z

    if-nez v0, :cond_0

    goto :goto_1

    .line 394
    :cond_0
    :try_start_0
    new-instance v0, Ljava/util/concurrent/CountDownLatch;

    const/4 v1, 0x1

    invoke-direct {v0, v1}, Ljava/util/concurrent/CountDownLatch;-><init>(I)V

    .line 395
    invoke-static {}, Landroid/os/SystemClock;->uptimeMillis()J

    move-result-wide v1

    .line 396
    iget-object v3, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->mainHandler:Landroid/os/Handler;

    new-instance v4, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$$ExternalSyntheticLambda2;

    invoke-direct {v4, v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$$ExternalSyntheticLambda2;-><init>(Ljava/util/concurrent/CountDownLatch;)V

    invoke-virtual {v3, v4}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    move-result v3

    if-eqz v3, :cond_1

    sget-object v3, Ljava/util/concurrent/TimeUnit;->MILLISECONDS:Ljava/util/concurrent/TimeUnit;

    const-wide/16 v4, 0x64

    invoke-virtual {v0, v4, v5, v3}, Ljava/util/concurrent/CountDownLatch;->await(JLjava/util/concurrent/TimeUnit;)Z

    move-result v3

    if-nez v3, :cond_1

    .line 397
    invoke-direct {p0, v0, v1, v2}, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->sample(Ljava/util/concurrent/CountDownLatch;J)Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;

    move-result-object v0

    if-eqz v0, :cond_1

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->record(Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    .line 400
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 402
    :cond_1
    :goto_0
    iget-boolean v0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->running:Z

    if-eqz v0, :cond_2

    .line 403
    iget-object v0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->queue:Lorg/telegram/messenger/DispatchQueue;

    iget-object p0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->checkRunnable:Ljava/lang/Runnable;

    const-wide/16 v1, 0xc8

    invoke-virtual {v0, p0, v1, v2}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;J)Z

    :cond_2
    :goto_1
    return-void
.end method

.method private final record(Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;)V
    .locals 17

    move-object/from16 v0, p1

    .line 462
    sget-object v1, Lcom/exteragram/messenger/debug/FreezeMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/FreezeMonitor;

    invoke-static {v1}, Lcom/exteragram/messenger/debug/FreezeMonitor;->access$gcStats(Lcom/exteragram/messenger/debug/FreezeMonitor;)[J

    move-result-object v2

    .line 463
    invoke-static {}, Lcom/exteragram/messenger/debug/FreezeMonitor;->access$getFreezes$p()I

    move-result v3

    const/4 v4, 0x1

    add-int/2addr v3, v4

    invoke-static {v3}, Lcom/exteragram/messenger/debug/FreezeMonitor;->access$setFreezes$p(I)V

    invoke-static {}, Lcom/exteragram/messenger/debug/FreezeMonitor;->access$getFreezes$p()I

    move-result v3

    .line 464
    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->getSamples()I

    move-result v5

    .line 465
    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    .line 466
    new-instance v7, Ljava/text/SimpleDateFormat;

    const-string v8, "yyyy-MM-dd HH:mm:ss"

    sget-object v9, Ljava/util/Locale;->US:Ljava/util/Locale;

    invoke-direct {v7, v8, v9}, Ljava/text/SimpleDateFormat;-><init>(Ljava/lang/String;Ljava/util/Locale;)V

    new-instance v8, Ljava/util/Date;

    invoke-direct {v8}, Ljava/util/Date;-><init>()V

    invoke-virtual {v7, v8}, Ljava/text/DateFormat;->format(Ljava/util/Date;)Ljava/lang/String;

    move-result-object v7

    new-instance v8, Ljava/lang/StringBuilder;

    const-string v9, "\n=== Freeze #"

    invoke-direct {v8, v9}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v8, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v3, " at "

    invoke-virtual {v8, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, ": "

    invoke-virtual {v8, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v6, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 467
    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->getDurationMs()J

    move-result-wide v7

    new-instance v3, Ljava/lang/StringBuilder;

    const-string v9, "main thread blocked "

    invoke-direct {v3, v9}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v7, v8}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v7, " ms\n"

    invoke-virtual {v3, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v6, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 468
    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->getThresholdMs()I

    move-result v3

    new-instance v7, Ljava/lang/StringBuilder;

    const-string v8, "threshold "

    invoke-direct {v7, v8}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v7, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v3, " ms, "

    invoke-virtual {v7, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v3, " samples every 20 ms from 100 ms\n"

    invoke-virtual {v7, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v6, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 469
    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->getMaxGapMs()J

    move-result-wide v7

    const-wide/16 v9, 0x7d0

    cmp-long v3, v7, v9

    if-lez v3, :cond_0

    .line 470
    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->getMaxGapMs()J

    move-result-wide v7

    new-instance v3, Ljava/lang/StringBuilder;

    const-string v9, "WARNING: the monitor thread itself stalled for "

    invoke-direct {v3, v9}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v7, v8}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v7, " ms, the system probably froze the whole process; this record may be bogus\n"

    invoke-virtual {v3, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v6, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 472
    :cond_0
    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->getScreen()Ljava/lang/String;

    move-result-object v3

    new-instance v7, Ljava/lang/StringBuilder;

    const-string v8, "screen at start: "

    invoke-direct {v7, v8}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v7, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "\n"

    invoke-virtual {v7, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 473
    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->getFragmentStack()Ljava/lang/String;

    move-result-object v7

    new-instance v8, Ljava/lang/StringBuilder;

    const-string v9, "fragment stack "

    invoke-direct {v8, v9}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v8, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/4 v7, 0x0

    .line 474
    aget-wide v8, v2, v7

    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->getGcBefore()[J

    move-result-object v10

    aget-wide v11, v10, v7

    sub-long/2addr v8, v11

    aget-wide v10, v2, v4

    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->getGcBefore()[J

    move-result-object v12

    aget-wide v13, v12, v4

    sub-long/2addr v10, v13

    new-instance v4, Ljava/lang/StringBuilder;

    const-string v12, "GC during freeze: blocking "

    invoke-direct {v4, v12}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v4, v8, v9}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v8, " ("

    invoke-virtual {v4, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v10, v11}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v9, " ms), "

    invoke-virtual {v4, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v6, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/4 v4, 0x2

    .line 475
    aget-wide v9, v2, v4

    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->getGcBefore()[J

    move-result-object v11

    aget-wide v12, v11, v4

    sub-long/2addr v9, v12

    const/4 v11, 0x3

    aget-wide v12, v2, v11

    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->getGcBefore()[J

    move-result-object v2

    aget-wide v14, v2, v11

    sub-long/2addr v12, v14

    new-instance v2, Ljava/lang/StringBuilder;

    const-string v11, "all "

    invoke-direct {v2, v11}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, v9, v10}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v12, v13}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v8, " ms)\n"

    invoke-virtual {v2, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v6, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 476
    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->getHeapBefore()Ljava/lang/String;

    move-result-object v2

    new-instance v8, Ljava/lang/StringBuilder;

    const-string v9, "heap before: "

    invoke-direct {v8, v9}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v8, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v6, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 477
    invoke-static {v1}, Lcom/exteragram/messenger/debug/FreezeMonitor;->access$heapLine(Lcom/exteragram/messenger/debug/FreezeMonitor;)Ljava/lang/String;

    move-result-object v1

    new-instance v2, Ljava/lang/StringBuilder;

    const-string v8, "heap after:  "

    invoke-direct {v2, v8}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v6, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 478
    const-string v1, "main thread states: "

    invoke-virtual {v6, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 479
    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->getStates()Ljava/util/Map;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v1

    .line 1080
    new-instance v2, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$record$$inlined$sortedByDescending$1;

    invoke-direct {v2}, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$record$$inlined$sortedByDescending$1;-><init>()V

    invoke-static {v1, v2}, Lkotlin/collections/CollectionsKt;->sortedWith(Ljava/lang/Iterable;Ljava/util/Comparator;)Ljava/util/List;

    move-result-object v1

    move-object v8, v1

    check-cast v8, Ljava/lang/Iterable;

    .line 479
    new-instance v14, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$$ExternalSyntheticLambda3;

    invoke-direct {v14}, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$$ExternalSyntheticLambda3;-><init>()V

    const/16 v15, 0x1e

    const/16 v16, 0x0

    const-string v9, ", "

    const/4 v10, 0x0

    const/4 v11, 0x0

    const/4 v12, 0x0

    const/4 v13, 0x0

    invoke-static/range {v8 .. v16}, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lkotlin/jvm/functions/Function1;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v6, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/16 v1, 0xa

    .line 480
    invoke-virtual {v6, v1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    if-lez v5, :cond_7

    .line 483
    const-string v2, "\n-- hot app frames (share of samples, outermost first) --\n"

    invoke-virtual {v6, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 484
    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->getHot()Ljava/util/Map;

    move-result-object v2

    invoke-interface {v2}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v2

    .line 777
    new-instance v8, Ljava/util/ArrayList;

    invoke-direct {v8}, Ljava/util/ArrayList;-><init>()V

    .line 873
    invoke-interface {v2}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v9

    if-eqz v9, :cond_3

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v9

    move-object v10, v9

    check-cast v10, Ljava/util/Map$Entry;

    .line 485
    invoke-interface {v10}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v11

    check-cast v11, Ljava/lang/Number;

    invoke-virtual {v11}, Ljava/lang/Number;->intValue()I

    move-result v11

    mul-int/lit8 v11, v11, 0x64

    mul-int/lit8 v12, v5, 0x5

    if-lt v11, v12, :cond_1

    invoke-static {}, Lcom/exteragram/messenger/debug/FreezeMonitor;->access$getAPP_FRAME_PREFIXES$p()[Ljava/lang/String;

    move-result-object v11

    .line 13213
    array-length v12, v11

    move v13, v7

    :goto_1
    if-ge v13, v12, :cond_1

    aget-object v14, v11, v13

    .line 485
    invoke-interface {v10}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v15

    check-cast v15, Ljava/lang/String;

    const/4 v1, 0x0

    invoke-static {v15, v14, v7, v4, v1}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 873
    invoke-interface {v8, v9}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    :cond_1
    const/16 v1, 0xa

    goto :goto_0

    :cond_2
    add-int/lit8 v13, v13, 0x1

    const/16 v1, 0xa

    goto :goto_1

    .line 486
    :cond_3
    new-instance v1, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$record$$inlined$compareByDescending$1;

    invoke-direct {v1}, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$record$$inlined$compareByDescending$1;-><init>()V

    new-instance v2, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$record$$inlined$thenByDescending$1;

    invoke-direct {v2, v1, v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$record$$inlined$thenByDescending$1;-><init>(Ljava/util/Comparator;Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;)V

    invoke-static {v8, v2}, Lkotlin/collections/CollectionsKt;->sortedWith(Ljava/lang/Iterable;Ljava/util/Comparator;)Ljava/util/List;

    move-result-object v1

    check-cast v1, Ljava/lang/Iterable;

    const/16 v2, 0x3c

    .line 487
    invoke-static {v1, v2}, Lkotlin/collections/CollectionsKt;->take(Ljava/lang/Iterable;I)Ljava/util/List;

    move-result-object v1

    check-cast v1, Ljava/lang/Iterable;

    .line 1915
    invoke-interface {v1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_2
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    const-string v4, "%  "

    if-eqz v2, :cond_4

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/Map$Entry;

    .line 488
    sget-object v7, Lcom/exteragram/messenger/debug/FreezeMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/FreezeMonitor;

    invoke-interface {v2}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Ljava/lang/Number;

    invoke-virtual {v8}, Ljava/lang/Number;->intValue()I

    move-result v8

    invoke-static {v7, v8, v5}, Lcom/exteragram/messenger/debug/FreezeMonitor;->access$percent(Lcom/exteragram/messenger/debug/FreezeMonitor;II)Ljava/lang/String;

    move-result-object v7

    invoke-interface {v2}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v2

    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v8, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v6, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_2

    .line 489
    :cond_4
    const-string v1, "\n-- top of stack --\n"

    invoke-virtual {v6, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 490
    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->getTopFrames()Ljava/util/Map;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v1

    .line 1080
    new-instance v2, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$record$$inlined$sortedByDescending$2;

    invoke-direct {v2}, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$record$$inlined$sortedByDescending$2;-><init>()V

    invoke-static {v1, v2}, Lkotlin/collections/CollectionsKt;->sortedWith(Ljava/lang/Iterable;Ljava/util/Comparator;)Ljava/util/List;

    move-result-object v1

    check-cast v1, Ljava/lang/Iterable;

    const/16 v2, 0xa

    .line 490
    invoke-static {v1, v2}, Lkotlin/collections/CollectionsKt;->take(Ljava/lang/Iterable;I)Ljava/util/List;

    move-result-object v1

    check-cast v1, Ljava/lang/Iterable;

    .line 1915
    invoke-interface {v1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_3
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_5

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/Map$Entry;

    .line 491
    sget-object v7, Lcom/exteragram/messenger/debug/FreezeMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/FreezeMonitor;

    invoke-interface {v2}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Ljava/lang/Number;

    invoke-virtual {v8}, Ljava/lang/Number;->intValue()I

    move-result v8

    invoke-static {v7, v8, v5}, Lcom/exteragram/messenger/debug/FreezeMonitor;->access$percent(Lcom/exteragram/messenger/debug/FreezeMonitor;II)Ljava/lang/String;

    move-result-object v7

    invoke-interface {v2}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v2

    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v8, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v6, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_3

    .line 492
    :cond_5
    const-string v1, "\n-- most frequent stacks --\n"

    invoke-virtual {v6, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 493
    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->getStacks()Ljava/util/Map;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v1

    .line 1080
    new-instance v2, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$record$$inlined$sortedByDescending$3;

    invoke-direct {v2}, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$record$$inlined$sortedByDescending$3;-><init>()V

    invoke-static {v1, v2}, Lkotlin/collections/CollectionsKt;->sortedWith(Ljava/lang/Iterable;Ljava/util/Comparator;)Ljava/util/List;

    move-result-object v1

    check-cast v1, Ljava/lang/Iterable;

    const/4 v2, 0x5

    .line 493
    invoke-static {v1, v2}, Lkotlin/collections/CollectionsKt;->take(Ljava/lang/Iterable;I)Ljava/util/List;

    move-result-object v1

    check-cast v1, Ljava/lang/Iterable;

    .line 1915
    invoke-interface {v1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_6
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_7

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/Map$Entry;

    invoke-interface {v2}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/util/List;

    invoke-interface {v2}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Number;

    invoke-virtual {v2}, Ljava/lang/Number;->intValue()I

    move-result v2

    mul-int/lit8 v4, v2, 0x64

    .line 494
    div-int/2addr v4, v5

    new-instance v7, Ljava/lang/StringBuilder;

    const-string v8, "["

    invoke-direct {v7, v8}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v7, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v2, " samples, "

    invoke-virtual {v7, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v2, "%]\n"

    invoke-virtual {v7, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v6, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 495
    check-cast v3, Ljava/lang/Iterable;

    .line 1915
    invoke-interface {v3}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_4
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_6

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    .line 495
    const-string v4, "    at "

    invoke-virtual {v6, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/16 v3, 0xa

    invoke-virtual {v6, v3}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    goto :goto_4

    .line 498
    :cond_7
    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->getOtherThreads()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_8

    .line 499
    const-string v1, "\n-- other busy threads at the threshold --\n"

    invoke-virtual {v6, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->getOtherThreads()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v6, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 501
    :cond_8
    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->getObservers()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_9

    .line 502
    const-string v1, "\n-- NotificationCenter observers at the threshold --\n"

    invoke-virtual {v6, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->getObservers()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v6, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_9
    const/16 v2, 0xa

    .line 504
    invoke-virtual {v6, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    sget-object v1, Lcom/exteragram/messenger/debug/FreezeMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/FreezeMonitor;

    invoke-static {v1}, Lcom/exteragram/messenger/debug/FreezeMonitor;->access$environment(Lcom/exteragram/messenger/debug/FreezeMonitor;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v6, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 505
    const-string v2, "\n-- live objects (after GC) --\n"

    invoke-virtual {v6, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {v1}, Lcom/exteragram/messenger/debug/FreezeMonitor;->access$liveObjects(Lcom/exteragram/messenger/debug/FreezeMonitor;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v6, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 506
    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Lcom/exteragram/messenger/debug/FreezeMonitor;->access$appendReport(Lcom/exteragram/messenger/debug/FreezeMonitor;Ljava/lang/String;)V

    .line 508
    new-instance v1, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$$ExternalSyntheticLambda4;

    invoke-direct {v1, v0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$$ExternalSyntheticLambda4;-><init>(Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;)V

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method private final sample(Ljava/util/concurrent/CountDownLatch;J)Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;
    .locals 30

    move-object/from16 v0, p0

    .line 408
    sget-object v1, Lcom/exteragram/messenger/debug/FreezeMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/FreezeMonitor;

    invoke-virtual {v1}, Lcom/exteragram/messenger/debug/FreezeMonitor;->getThresholdMs()I

    move-result v5

    .line 409
    invoke-static {v1}, Lcom/exteragram/messenger/debug/FreezeMonitor;->access$gcStats(Lcom/exteragram/messenger/debug/FreezeMonitor;)[J

    move-result-object v11

    .line 410
    invoke-static {v1}, Lcom/exteragram/messenger/debug/FreezeMonitor;->access$heapLine(Lcom/exteragram/messenger/debug/FreezeMonitor;)Ljava/lang/String;

    move-result-object v12

    .line 411
    invoke-static {v1}, Lcom/exteragram/messenger/debug/FreezeMonitor;->access$screenName(Lcom/exteragram/messenger/debug/FreezeMonitor;)Ljava/lang/String;

    move-result-object v9

    .line 412
    invoke-static {v1}, Lcom/exteragram/messenger/debug/FreezeMonitor;->access$fragmentStack(Lcom/exteragram/messenger/debug/FreezeMonitor;)Ljava/lang/String;

    move-result-object v10

    .line 413
    new-instance v13, Ljava/util/HashMap;

    invoke-direct {v13}, Ljava/util/HashMap;-><init>()V

    .line 414
    new-instance v14, Ljava/util/HashMap;

    invoke-direct {v14}, Ljava/util/HashMap;-><init>()V

    .line 415
    new-instance v15, Ljava/util/HashMap;

    invoke-direct {v15}, Ljava/util/HashMap;-><init>()V

    .line 416
    new-instance v1, Ljava/util/HashMap;

    invoke-direct {v1}, Ljava/util/HashMap;-><init>()V

    .line 417
    new-instance v2, Ljava/util/HashMap;

    invoke-direct {v2}, Ljava/util/HashMap;-><init>()V

    const-wide/16 v6, 0x0

    move-wide/from16 v16, p2

    const/4 v8, 0x0

    const/16 v18, 0x0

    const/16 v19, 0x0

    const/16 v20, 0x0

    .line 424
    :goto_0
    iget-boolean v3, v0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->stopped:Z

    if-eqz v3, :cond_0

    return-object v20

    .line 425
    :cond_0
    invoke-static {}, Landroid/os/SystemClock;->uptimeMillis()J

    move-result-wide v21

    move v3, v5

    sub-long v4, v21, v16

    .line 426
    invoke-static {v6, v7, v4, v5}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v4

    sub-long v6, v21, p2

    const-wide/32 v16, 0x1d4c0

    cmp-long v16, v6, v16

    move/from16 v17, v3

    move-wide/from16 v24, v4

    if-gez v16, :cond_c

    .line 429
    iget-object v5, v0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->mainThread:Ljava/lang/Thread;

    invoke-virtual {v5}, Ljava/lang/Thread;->getState()Ljava/lang/Thread$State;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/Enum;->name()Ljava/lang/String;

    move-result-object v5

    const-wide/16 v26, 0x14

    .line 430
    sget-object v3, Lcom/exteragram/messenger/debug/FreezeMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/FreezeMonitor;

    iget-object v4, v0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->mainThread:Ljava/lang/Thread;

    invoke-virtual {v4}, Ljava/lang/Thread;->getStackTrace()[Ljava/lang/StackTraceElement;

    move-result-object v4

    move-wide/from16 v28, v6

    const/16 v6, 0x96

    invoke-static {v3, v4, v6}, Lcom/exteragram/messenger/debug/FreezeMonitor;->access$frameNames(Lcom/exteragram/messenger/debug/FreezeMonitor;[Ljava/lang/StackTraceElement;I)Ljava/util/List;

    move-result-object v3

    add-int/lit8 v8, v8, 0x1

    .line 432
    invoke-virtual {v13, v5}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/Integer;

    if-eqz v4, :cond_1

    invoke-virtual {v4}, Ljava/lang/Integer;->intValue()I

    move-result v4

    goto :goto_1

    :cond_1
    const/4 v4, 0x0

    :goto_1
    add-int/lit8 v4, v4, 0x1

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-interface {v13, v5, v4}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 433
    move-object v4, v3

    check-cast v4, Ljava/util/Collection;

    invoke-interface {v4}, Ljava/util/Collection;->isEmpty()Z

    move-result v4

    if-nez v4, :cond_9

    const/4 v4, 0x0

    .line 434
    invoke-interface {v3, v4}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v5

    invoke-interface {v3, v4}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v6

    invoke-virtual {v1, v6}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/Integer;

    if-eqz v6, :cond_2

    invoke-virtual {v6}, Ljava/lang/Integer;->intValue()I

    move-result v6

    goto :goto_2

    :cond_2
    move v6, v4

    :goto_2
    add-int/lit8 v6, v6, 0x1

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    invoke-interface {v1, v5, v6}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 435
    check-cast v3, Ljava/lang/Iterable;

    const/16 v5, 0x28

    invoke-static {v3, v5}, Lkotlin/collections/CollectionsKt;->take(Ljava/lang/Iterable;I)Ljava/util/List;

    move-result-object v5

    .line 436
    invoke-virtual {v2, v5}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/Integer;

    if-eqz v6, :cond_3

    invoke-virtual {v6}, Ljava/lang/Integer;->intValue()I

    move-result v6

    goto :goto_3

    :cond_3
    move v6, v4

    :goto_3
    add-int/lit8 v6, v6, 0x1

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    invoke-interface {v2, v5, v6}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 1925
    invoke-interface {v3}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v5

    move v6, v4

    :goto_4
    invoke-interface {v5}, Ljava/util/Iterator;->hasNext()Z

    move-result v7

    if-eqz v7, :cond_7

    invoke-interface {v5}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v7

    add-int/lit8 v16, v6, 0x1

    if-gez v6, :cond_4

    invoke-static {}, Lkotlin/collections/CollectionsKt;->throwIndexOverflow()V

    :cond_4
    check-cast v7, Ljava/lang/String;

    .line 438
    invoke-virtual {v15, v7}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v23

    check-cast v23, Ljava/lang/Integer;

    if-eqz v23, :cond_5

    invoke-virtual/range {v23 .. v23}, Ljava/lang/Integer;->intValue()I

    move-result v23

    :goto_5
    move/from16 v4, v23

    goto :goto_6

    :cond_5
    const/16 v23, -0x1

    goto :goto_5

    :goto_6
    if-le v6, v4, :cond_6

    .line 439
    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-interface {v15, v7, v4}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_6
    move/from16 v6, v16

    const/4 v4, 0x0

    goto :goto_4

    .line 442
    :cond_7
    invoke-static {v3}, Lkotlin/collections/CollectionsKt;->toHashSet(Ljava/lang/Iterable;)Ljava/util/HashSet;

    move-result-object v3

    invoke-virtual {v3}, Ljava/util/HashSet;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :goto_7
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_9

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    .line 443
    invoke-virtual {v14, v4}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/lang/Integer;

    if-eqz v5, :cond_8

    invoke-virtual {v5}, Ljava/lang/Integer;->intValue()I

    move-result v5

    goto :goto_8

    :cond_8
    const/4 v5, 0x0

    :goto_8
    add-int/lit8 v5, v5, 0x1

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    invoke-interface {v14, v4, v5}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_7

    :cond_9
    if-nez v18, :cond_b

    add-long v6, v28, v26

    move/from16 v3, v17

    int-to-long v4, v3

    cmp-long v4, v6, v4

    if-ltz v4, :cond_a

    .line 447
    sget-object v4, Lcom/exteragram/messenger/debug/FreezeMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/FreezeMonitor;

    iget-object v5, v0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->mainThread:Ljava/lang/Thread;

    invoke-static {v4, v5}, Lcom/exteragram/messenger/debug/FreezeMonitor;->access$otherThreads(Lcom/exteragram/messenger/debug/FreezeMonitor;Ljava/lang/Thread;)Ljava/lang/String;

    move-result-object v18

    .line 448
    invoke-static {v4}, Lcom/exteragram/messenger/debug/FreezeMonitor;->access$observers(Lcom/exteragram/messenger/debug/FreezeMonitor;)Ljava/lang/String;

    move-result-object v19

    :cond_a
    :goto_9
    move v6, v8

    goto :goto_a

    :cond_b
    move/from16 v3, v17

    goto :goto_9

    :cond_c
    move/from16 v3, v17

    const-wide/16 v26, 0x14

    goto :goto_9

    .line 451
    :goto_a
    sget-object v4, Ljava/util/concurrent/TimeUnit;->MILLISECONDS:Ljava/util/concurrent/TimeUnit;

    move-object/from16 v5, p1

    move-wide/from16 v7, v26

    invoke-virtual {v5, v7, v8, v4}, Ljava/util/concurrent/CountDownLatch;->await(JLjava/util/concurrent/TimeUnit;)Z

    move-result v4

    if-eqz v4, :cond_e

    .line 453
    invoke-static {}, Landroid/os/SystemClock;->uptimeMillis()J

    move-result-wide v4

    sub-long v4, v4, p2

    int-to-long v7, v3

    cmp-long v0, v4, v7

    if-gez v0, :cond_d

    return-object v20

    :cond_d
    move-object/from16 v17, v2

    .line 455
    new-instance v2, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;

    move-wide v7, v4

    move v5, v3

    move-wide v3, v7

    move-object/from16 v16, v1

    move-wide/from16 v7, v24

    invoke-direct/range {v2 .. v19}, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;-><init>(JIIJLjava/lang/String;Ljava/lang/String;[JLjava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;)V

    return-object v2

    :cond_e
    move-wide/from16 v7, v24

    move-wide/from16 v16, v7

    move v8, v6

    move-wide/from16 v6, v16

    move v5, v3

    move-wide/from16 v16, v21

    goto/16 :goto_0
.end method

.method private final updateRunning()V
    .locals 2

    .line 380
    iget-boolean v0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->stopped:Z

    if-nez v0, :cond_0

    invoke-static {}, Lorg/telegram/ui/Components/ForegroundDetector;->getInstance()Lorg/telegram/ui/Components/ForegroundDetector;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lorg/telegram/ui/Components/ForegroundDetector;->isForeground()Z

    move-result v0

    const/4 v1, 0x1

    if-ne v0, v1, :cond_0

    goto :goto_0

    :cond_0
    const/4 v1, 0x0

    .line 381
    :goto_0
    iget-boolean v0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->running:Z

    if-ne v0, v1, :cond_1

    return-void

    .line 382
    :cond_1
    iput-boolean v1, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->running:Z

    .line 383
    iget-object v0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->queue:Lorg/telegram/messenger/DispatchQueue;

    new-instance v1, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$$ExternalSyntheticLambda0;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;)V

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method


# virtual methods
.method public onBecameBackground()V
    .locals 0

    .line 369
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->updateRunning()V

    return-void
.end method

.method public onBecameForeground()V
    .locals 0

    .line 367
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->updateRunning()V

    return-void
.end method

.method public final stop()V
    .locals 1

    .line 372
    invoke-static {}, Lorg/telegram/ui/Components/ForegroundDetector;->getInstance()Lorg/telegram/ui/Components/ForegroundDetector;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/ForegroundDetector;->removeListener(Lorg/telegram/ui/Components/ForegroundDetector$Listener;)V

    :cond_0
    const/4 v0, 0x1

    .line 373
    iput-boolean v0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->stopped:Z

    const/4 v0, 0x0

    .line 374
    iput-boolean v0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->running:Z

    .line 375
    iget-object v0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->queue:Lorg/telegram/messenger/DispatchQueue;

    invoke-virtual {v0}, Lorg/telegram/messenger/DispatchQueue;->cleanupQueue()V

    .line 376
    iget-object p0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->queue:Lorg/telegram/messenger/DispatchQueue;

    invoke-virtual {p0}, Lorg/telegram/messenger/DispatchQueue;->recycle()V

    return-void
.end method
