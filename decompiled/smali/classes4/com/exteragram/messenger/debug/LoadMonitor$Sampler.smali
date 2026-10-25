.class final Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/ui/Components/ForegroundDetector$Listener;
.implements Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/debug/LoadMonitor;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Sampler"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0007\n\u0002\u0010\u0008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0016\n\u0002\u0008\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0010\u0002\n\u0002\u0008\u0006\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\u0008\n\n\u0002\u0010$\n\u0002\u0008\u0005\u0008\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\u00a2\u0006\u0004\u0008\u0003\u0010\u0004J\u0006\u0010.\u001a\u00020/J\u0008\u00100\u001a\u00020/H\u0016J\u0008\u00101\u001a\u00020/H\u0016J5\u00102\u001a\u00020/2\u0006\u00103\u001a\u00020\u00182\u0006\u00104\u001a\u00020\u00182\u0016\u00105\u001a\u000c\u0012\u0008\u0008\u0001\u0012\u0004\u0018\u00010706\"\u0004\u0018\u000107H\u0016\u00a2\u0006\u0002\u00108J\u0008\u00109\u001a\u00020/H\u0002J\u0008\u0010:\u001a\u00020/H\u0002J\u0008\u0010;\u001a\u00020/H\u0002J<\u0010<\u001a\u00020/2\u0006\u0010=\u001a\u00020\u000c2\u0006\u0010>\u001a\u00020\u000c2\u0006\u0010?\u001a\u00020\u000c2\u0006\u0010@\u001a\u00020\u000c2\u0012\u0010A\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u001b0BH\u0002J\n\u0010C\u001a\u0004\u0018\u00010\u000eH\u0002J\u000e\u0010D\u001a\u00020\u00102\u0006\u0010E\u001a\u00020%J\u000e\u0010F\u001a\u00020\u00102\u0006\u0010E\u001a\u00020%R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0007\u0010\u0008R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u000cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R*\u0010\u0019\u001a\u001e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u001b0\u001aj\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u001b`\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020 X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020 X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020 X\u0082\u0004\u00a2\u0006\u0002\n\u0000R*\u0010$\u001a\u001e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020 0\u001aj\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020 `\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010&\u001a\u0008\u0012\u0004\u0012\u00020\u00100\'X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\"\u0010+\u001a\u0004\u0018\u00010%2\u0008\u0010*\u001a\u0004\u0018\u00010%@BX\u0086\u000e\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008,\u0010-\u00a8\u0006G"
    }
    d2 = {
        "Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;",
        "Lorg/telegram/ui/Components/ForegroundDetector$Listener;",
        "Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;",
        "<init>",
        "()V",
        "queue",
        "Lorg/telegram/messenger/DispatchQueue;",
        "getQueue",
        "()Lorg/telegram/messenger/DispatchQueue;",
        "sampleRunnable",
        "Ljava/lang/Runnable;",
        "startedAt",
        "",
        "startHealth",
        "Landroid/os/health/HealthStats;",
        "startBattery",
        "Lorg/json/JSONObject;",
        "recentHealth",
        "recentHealthRealtime",
        "lastRealtime",
        "lastCpuMs",
        "lastRxBytes",
        "lastTxBytes",
        "lastState",
        "",
        "threads",
        "Ljava/util/HashMap;",
        "Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;",
        "Lkotlin/collections/HashMap;",
        "episode",
        "Lcom/exteragram/messenger/debug/LoadMonitor$Episode;",
        "wallMs",
        "",
        "cpuMs",
        "rxBytes",
        "txBytes",
        "threadMs",
        "",
        "samples",
        "Lkotlin/collections/ArrayDeque;",
        "observers",
        "Lorg/telegram/messenger/NotificationCenter$ObserversGroup;",
        "value",
        "summary",
        "getSummary",
        "()Ljava/lang/String;",
        "stop",
        "",
        "onBecameForeground",
        "onBecameBackground",
        "didReceivedNotification",
        "id",
        "account",
        "args",
        "",
        "",
        "(II[Ljava/lang/Object;)V",
        "scheduleNext",
        "sample",
        "updateState",
        "checkEpisode",
        "now",
        "cpu",
        "rx",
        "tx",
        "current",
        "",
        "recentHealthStats",
        "captureReport",
        "trigger",
        "buildReport",
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
        "SMAP\nLoadMonitor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LoadMonitor.kt\ncom/exteragram/messenger/debug/LoadMonitor$Sampler\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,696:1\n777#2:697\n873#2,2:698\n1586#2:700\n1661#2,3:701\n1080#2:712\n410#3,7:704\n1#4:711\n*S KotlinDebug\n*F\n+ 1 LoadMonitor.kt\ncom/exteragram/messenger/debug/LoadMonitor$Sampler\n*L\n567#1:697\n567#1:698,2\n567#1:700\n567#1:701,3\n663#1:712\n574#1:704,7\n*E\n"
    }
.end annotation


# instance fields
.field private final cpuMs:[J

.field private episode:Lcom/exteragram/messenger/debug/LoadMonitor$Episode;

.field private lastCpuMs:J

.field private lastRealtime:J

.field private lastRxBytes:J

.field private lastState:I

.field private lastTxBytes:J

.field private observers:Lorg/telegram/messenger/NotificationCenter$ObserversGroup;

.field private final queue:Lorg/telegram/messenger/DispatchQueue;

.field private recentHealth:Landroid/os/health/HealthStats;

.field private recentHealthRealtime:J

.field private final rxBytes:[J

.field private final sampleRunnable:Ljava/lang/Runnable;

.field private final samples:Lkotlin/collections/ArrayDeque;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlin/collections/ArrayDeque<",
            "Lorg/json/JSONObject;",
            ">;"
        }
    .end annotation
.end field

.field private startBattery:Lorg/json/JSONObject;

.field private startHealth:Landroid/os/health/HealthStats;

.field private final startedAt:J

.field private volatile summary:Ljava/lang/String;

.field private final threadMs:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "[J>;"
        }
    .end annotation
.end field

.field private threads:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/Integer;",
            "Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;",
            ">;"
        }
    .end annotation
.end field

.field private final txBytes:[J

.field private final wallMs:[J


# direct methods
.method public static $r8$lambda$8INNKAQux6GXlf2aMK87Ll_uRVI(Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;)V
    .locals 0

    .line 537
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->sample()V

    return-void
.end method

.method public static $r8$lambda$E-VTY6NAYOMQOkky9ZN8dYzdoOw(Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;)V
    .locals 1

    .line 524
    iget-object v0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->observers:Lorg/telegram/messenger/NotificationCenter$ObserversGroup;

    if-eqz v0, :cond_0

    invoke-interface {v0}, Lorg/telegram/messenger/NotificationCenter$ObserversGroup;->removeAllObservers()V

    :cond_0
    const/4 v0, 0x0

    .line 525
    iput-object v0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->observers:Lorg/telegram/messenger/NotificationCenter$ObserversGroup;

    return-void
.end method

.method public static $r8$lambda$NjQid2hKNihO_YcjNN6UibHKtD0()V
    .locals 1

    .line 533
    sget-object v0, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-static {v0}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$showPendingAlert(Lcom/exteragram/messenger/debug/LoadMonitor;)V

    return-void
.end method

.method public static $r8$lambda$R-MRcxRWS30DFE1iO256K0agQLQ(Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;)V
    .locals 2

    .line 506
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    invoke-virtual {v0, p0}, Lorg/telegram/messenger/NotificationCenter;->createObserversGroup(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;)Lorg/telegram/messenger/NotificationCenter$ObserversGroup;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->screenStateChanged:I

    invoke-interface {v0, v1}, Lorg/telegram/messenger/NotificationCenter$ObserversGroup;->add(I)Lorg/telegram/messenger/NotificationCenter$ObserversGroup;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->observers:Lorg/telegram/messenger/NotificationCenter$ObserversGroup;

    return-void
.end method

.method public static $r8$lambda$glfliRZC8QH3MoG1_ZtmW_f9wTg(Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;)V
    .locals 3

    .line 509
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->recentHealthStats()Landroid/os/health/HealthStats;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->startHealth:Landroid/os/health/HealthStats;

    .line 510
    sget-object v0, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-static {v0}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$batteryJson(Lcom/exteragram/messenger/debug/LoadMonitor;)Lorg/json/JSONObject;

    move-result-object v1

    iput-object v1, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->startBattery:Lorg/json/JSONObject;

    .line 511
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v1

    iput-wide v1, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->lastRealtime:J

    .line 512
    invoke-static {}, Landroid/os/Process;->getElapsedCpuTime()J

    move-result-wide v1

    iput-wide v1, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->lastCpuMs:J

    .line 513
    invoke-static {v0}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$uidRxBytes(Lcom/exteragram/messenger/debug/LoadMonitor;)J

    move-result-wide v1

    iput-wide v1, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->lastRxBytes:J

    .line 514
    invoke-static {v0}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$uidTxBytes(Lcom/exteragram/messenger/debug/LoadMonitor;)J

    move-result-wide v1

    iput-wide v1, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->lastTxBytes:J

    .line 515
    invoke-static {v0}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$readThreads(Lcom/exteragram/messenger/debug/LoadMonitor;)Ljava/util/HashMap;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->threads:Ljava/util/HashMap;

    .line 516
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->updateState()V

    .line 517
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->scheduleNext()V

    return-void
.end method

.method public static $r8$lambda$kogUcZhXgMzzA6XabLRzcV5MgzI(Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;)V
    .locals 0

    .line 474
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->sample()V

    .line 475
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->scheduleNext()V

    return-void
.end method

.method public static $r8$lambda$skf2wH9h9ICrpnKHS9Si8q7MxEE(Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;)V
    .locals 0

    .line 532
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->sample()V

    return-void
.end method

.method public static $r8$lambda$ux4TTTD-F0B74u88CV9Sz2UU4ko()V
    .locals 1

    .line 634
    sget-object v0, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-static {v0}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$showPendingAlert(Lcom/exteragram/messenger/debug/LoadMonitor;)V

    return-void
.end method

.method public static $r8$lambda$xrMe0cjs6B9Bs_6qQChp3wImB6I(Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;)V
    .locals 0

    .line 542
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->sample()V

    return-void
.end method

.method public constructor <init>()V
    .locals 3

    .line 470
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 472
    new-instance v0, Lorg/telegram/messenger/DispatchQueue;

    const-string v1, "loadMonitorQueue"

    invoke-direct {v0, v1}, Lorg/telegram/messenger/DispatchQueue;-><init>(Ljava/lang/String;)V

    iput-object v0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->queue:Lorg/telegram/messenger/DispatchQueue;

    .line 473
    new-instance v1, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler$$ExternalSyntheticLambda3;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;)V

    iput-object v1, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->sampleRunnable:Ljava/lang/Runnable;

    .line 478
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    iput-wide v1, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->startedAt:J

    const-wide/16 v1, -0x1

    .line 485
    iput-wide v1, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->lastRxBytes:J

    .line 486
    iput-wide v1, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->lastTxBytes:J

    .line 488
    new-instance v1, Ljava/util/HashMap;

    invoke-direct {v1}, Ljava/util/HashMap;-><init>()V

    iput-object v1, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->threads:Ljava/util/HashMap;

    .line 491
    invoke-static {}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$getSTATE_NAMES$p()[Ljava/lang/String;

    move-result-object v1

    array-length v1, v1

    new-array v1, v1, [J

    iput-object v1, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->wallMs:[J

    .line 492
    invoke-static {}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$getSTATE_NAMES$p()[Ljava/lang/String;

    move-result-object v1

    array-length v1, v1

    new-array v1, v1, [J

    iput-object v1, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->cpuMs:[J

    .line 493
    invoke-static {}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$getSTATE_NAMES$p()[Ljava/lang/String;

    move-result-object v1

    array-length v1, v1

    new-array v1, v1, [J

    iput-object v1, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->rxBytes:[J

    .line 494
    invoke-static {}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$getSTATE_NAMES$p()[Ljava/lang/String;

    move-result-object v1

    array-length v1, v1

    new-array v1, v1, [J

    iput-object v1, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->txBytes:[J

    .line 495
    new-instance v1, Ljava/util/HashMap;

    invoke-direct {v1}, Ljava/util/HashMap;-><init>()V

    iput-object v1, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->threadMs:Ljava/util/HashMap;

    .line 496
    new-instance v1, Lkotlin/collections/ArrayDeque;

    invoke-direct {v1}, Lkotlin/collections/ArrayDeque;-><init>()V

    iput-object v1, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->samples:Lkotlin/collections/ArrayDeque;

    .line 504
    invoke-static {}, Lorg/telegram/ui/Components/ForegroundDetector;->getInstance()Lorg/telegram/ui/Components/ForegroundDetector;

    move-result-object v1

    if-eqz v1, :cond_0

    invoke-virtual {v1, p0}, Lorg/telegram/ui/Components/ForegroundDetector;->addListener(Lorg/telegram/ui/Components/ForegroundDetector$Listener;)V

    .line 505
    :cond_0
    new-instance v1, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler$$ExternalSyntheticLambda4;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler$$ExternalSyntheticLambda4;-><init>(Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;)V

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    .line 508
    new-instance v1, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler$$ExternalSyntheticLambda5;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler$$ExternalSyntheticLambda5;-><init>(Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;)V

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method

.method private final checkEpisode(JJJJLjava/util/Map;)V
    .locals 16
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(JJJJ",
            "Ljava/util/Map<",
            "Ljava/lang/Integer;",
            "Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;",
            ">;)V"
        }
    .end annotation

    move-object/from16 v0, p0

    .line 620
    const-string v1, "background"

    iget-object v2, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->episode:Lcom/exteragram/messenger/debug/LoadMonitor$Episode;

    if-nez v2, :cond_0

    goto/16 :goto_2

    .line 621
    :cond_0
    invoke-virtual {v2}, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->getStartRealtime()J

    move-result-wide v3

    sub-long v3, p1, v3

    .line 622
    invoke-virtual {v2}, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->getReported()Z

    move-result v5

    if-nez v5, :cond_8

    const-wide/32 v5, 0x493e0

    cmp-long v5, v3, v5

    if-gez v5, :cond_1

    goto/16 :goto_2

    .line 623
    :cond_1
    invoke-virtual {v2}, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->getCpuMs()J

    move-result-wide v5

    sub-long v5, p3, v5

    const-wide/16 v7, 0x64

    mul-long/2addr v7, v5

    .line 624
    sget-object v9, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-virtual {v9}, Lcom/exteragram/messenger/debug/LoadMonitor;->getCpuPercent()I

    move-result v10

    int-to-long v10, v10

    mul-long/2addr v10, v3

    cmp-long v7, v7, v10

    if-gez v7, :cond_2

    goto/16 :goto_2

    :cond_2
    const/4 v7, 0x1

    .line 625
    invoke-virtual {v2, v7}, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->setReported(Z)V

    .line 626
    :try_start_0
    sget-object v8, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->buildReport(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object v0

    invoke-static {v9, v0, v1}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$writeReport(Lcom/exteragram/messenger/debug/LoadMonitor;Lorg/json/JSONObject;Ljava/lang/String;)Ljava/io/File;

    move-result-object v0

    invoke-static {v0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception v0

    sget-object v1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {v0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object v0

    invoke-static {v0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 627
    :goto_0
    invoke-static {v0}, Lkotlin/Result;->exceptionOrNull-impl(Ljava/lang/Object;)Ljava/lang/Throwable;

    move-result-object v1

    if-eqz v1, :cond_3

    invoke-static {v1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 628
    :cond_3
    invoke-static {v0}, Lkotlin/Result;->isFailure-impl(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_4

    const/4 v0, 0x0

    :cond_4
    check-cast v0, Ljava/io/File;

    if-nez v0, :cond_5

    goto :goto_2

    :cond_5
    const-wide/16 v8, 0x0

    cmp-long v1, p5, v8

    const-wide/16 v10, -0x1

    if-ltz v1, :cond_6

    .line 629
    invoke-virtual {v2}, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->getRxBytes()J

    move-result-wide v12

    cmp-long v1, v12, v8

    if-ltz v1, :cond_6

    invoke-virtual {v2}, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->getRxBytes()J

    move-result-wide v12

    sub-long v12, p5, v12

    goto :goto_1

    :cond_6
    move-wide v12, v10

    :goto_1
    cmp-long v1, p7, v8

    if-ltz v1, :cond_7

    .line 630
    invoke-virtual {v2}, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->getTxBytes()J

    move-result-wide v14

    cmp-long v1, v14, v8

    if-ltz v1, :cond_7

    invoke-virtual {v2}, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->getTxBytes()J

    move-result-wide v8

    sub-long v10, p7, v8

    .line 631
    :cond_7
    sget-object v1, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-virtual {v2}, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->getThreads()Ljava/util/Map;

    move-result-object v2

    move-object/from16 v8, p9

    invoke-static {v1, v2, v8}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$threadDeltas(Lcom/exteragram/messenger/debug/LoadMonitor;Ljava/util/Map;Ljava/util/Map;)Ljava/util/HashMap;

    move-result-object v2

    const/4 v8, 0x3

    invoke-static {v1, v2, v8}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$topThreads(Lcom/exteragram/messenger/debug/LoadMonitor;Ljava/util/Map;I)Ljava/util/List;

    move-result-object v2

    .line 632
    new-instance v8, Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;

    move-object/from16 p0, v1

    move-object/from16 p9, v2

    move-wide/from16 p1, v3

    move-wide/from16 p3, v5

    move-wide/from16 p7, v10

    move-wide/from16 p5, v12

    invoke-static/range {p0 .. p9}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$describeEpisode(Lcom/exteragram/messenger/debug/LoadMonitor;JJJJLjava/util/List;)Ljava/lang/String;

    move-result-object v1

    invoke-direct {v8, v0, v1}, Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;-><init>(Ljava/io/File;Ljava/lang/String;)V

    invoke-static {v8}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$setPendingAlert$p(Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;)V

    .line 633
    invoke-static {}, Lorg/telegram/ui/Components/ForegroundDetector;->getInstance()Lorg/telegram/ui/Components/ForegroundDetector;

    move-result-object v0

    if-eqz v0, :cond_8

    invoke-virtual {v0}, Lorg/telegram/ui/Components/ForegroundDetector;->isForeground()Z

    move-result v0

    if-ne v0, v7, :cond_8

    .line 634
    new-instance v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler$$ExternalSyntheticLambda8;

    invoke-direct {v0}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler$$ExternalSyntheticLambda8;-><init>()V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    :cond_8
    :goto_2
    return-void
.end method

.method private final recentHealthStats()Landroid/os/health/HealthStats;
    .locals 6

    .line 639
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v0

    .line 640
    iget-object v2, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->recentHealth:Landroid/os/health/HealthStats;

    if-eqz v2, :cond_0

    iget-wide v2, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->recentHealthRealtime:J

    sub-long v2, v0, v2

    const-wide/32 v4, 0xea60

    cmp-long v2, v2, v4

    if-lez v2, :cond_1

    .line 641
    :cond_0
    sget-object v2, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-static {v2}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$takeHealthStats(Lcom/exteragram/messenger/debug/LoadMonitor;)Landroid/os/health/HealthStats;

    move-result-object v2

    iput-object v2, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->recentHealth:Landroid/os/health/HealthStats;

    .line 642
    iput-wide v0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->recentHealthRealtime:J

    .line 644
    :cond_1
    iget-object p0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->recentHealth:Landroid/os/health/HealthStats;

    return-object p0
.end method

.method private final sample()V
    .locals 29

    move-object/from16 v0, p0

    .line 552
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v1

    .line 553
    invoke-static {}, Landroid/os/Process;->getElapsedCpuTime()J

    move-result-wide v3

    .line 554
    sget-object v5, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-static {v5}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$uidRxBytes(Lcom/exteragram/messenger/debug/LoadMonitor;)J

    move-result-wide v6

    .line 555
    invoke-static {v5}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$uidTxBytes(Lcom/exteragram/messenger/debug/LoadMonitor;)J

    move-result-wide v8

    .line 556
    invoke-static {v5}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$readThreads(Lcom/exteragram/messenger/debug/LoadMonitor;)Ljava/util/HashMap;

    move-result-object v10

    .line 557
    iget v11, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->lastState:I

    .line 558
    iget-wide v12, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->lastRealtime:J

    sub-long v12, v1, v12

    .line 559
    iget-wide v14, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->lastCpuMs:J

    sub-long v14, v3, v14

    const-wide/16 v16, 0x0

    cmp-long v18, v6, v16

    if-ltz v18, :cond_0

    move-wide/from16 v18, v8

    .line 560
    iget-wide v8, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->lastRxBytes:J

    cmp-long v20, v8, v16

    if-ltz v20, :cond_1

    sub-long v8, v6, v8

    goto :goto_0

    :cond_0
    move-wide/from16 v18, v8

    :cond_1
    move-wide/from16 v8, v16

    :goto_0
    cmp-long v20, v18, v16

    if-ltz v20, :cond_2

    move-wide/from16 v20, v6

    .line 561
    iget-wide v6, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->lastTxBytes:J

    cmp-long v22, v6, v16

    if-ltz v22, :cond_3

    sub-long v6, v18, v6

    :goto_1
    move/from16 v22, v11

    goto :goto_2

    :cond_2
    move-wide/from16 v20, v6

    :cond_3
    move-wide/from16 v6, v16

    goto :goto_1

    .line 562
    :goto_2
    iget-object v11, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->threads:Ljava/util/HashMap;

    invoke-static {v5, v11, v10}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$threadDeltas(Lcom/exteragram/messenger/debug/LoadMonitor;Ljava/util/Map;Ljava/util/Map;)Ljava/util/HashMap;

    move-result-object v11

    .line 563
    invoke-virtual {v11}, Ljava/util/HashMap;->values()Ljava/util/Collection;

    move-result-object v23

    check-cast v23, Ljava/lang/Iterable;

    invoke-static/range {v23 .. v23}, Lkotlin/collections/CollectionsKt;->sumOfLong(Ljava/lang/Iterable;)J

    move-result-wide v23

    sub-long v23, v14, v23

    cmp-long v16, v23, v16

    if-lez v16, :cond_4

    move-object/from16 v16, v10

    .line 565
    const-string v10, "<unattributed>"

    move-wide/from16 v25, v3

    invoke-static/range {v23 .. v24}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v3

    invoke-interface {v11, v10, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_3

    :cond_4
    move-wide/from16 v25, v3

    move-object/from16 v16, v10

    :goto_3
    const/4 v3, 0x3

    .line 567
    invoke-static {v5, v11, v3}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$topThreads(Lcom/exteragram/messenger/debug/LoadMonitor;Ljava/util/Map;I)Ljava/util/List;

    move-result-object v4

    check-cast v4, Ljava/lang/Iterable;

    .line 777
    new-instance v10, Ljava/util/ArrayList;

    invoke-direct {v10}, Ljava/util/ArrayList;-><init>()V

    .line 873
    invoke-interface {v4}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v4

    :goto_4
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v17

    if-eqz v17, :cond_6

    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    move-object/from16 v23, v3

    check-cast v23, Lkotlin/Pair;

    .line 567
    invoke-virtual/range {v23 .. v23}, Lkotlin/Pair;->getSecond()Ljava/lang/Object;

    move-result-object v23

    check-cast v23, Ljava/lang/Number;

    invoke-virtual/range {v23 .. v23}, Ljava/lang/Number;->longValue()J

    move-result-wide v23

    const-wide/16 v27, 0xc8

    cmp-long v23, v23, v27

    if-ltz v23, :cond_5

    .line 873
    invoke-interface {v10, v3}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    :cond_5
    const/4 v3, 0x3

    goto :goto_4

    .line 1586
    :cond_6
    new-instance v3, Ljava/util/ArrayList;

    const/16 v4, 0xa

    invoke-static {v10, v4}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v4

    invoke-direct {v3, v4}, Ljava/util/ArrayList;-><init>(I)V

    .line 1661
    invoke-virtual {v10}, Ljava/util/ArrayList;->size()I

    move-result v4

    move-wide/from16 v23, v1

    const/4 v2, 0x0

    :goto_5
    if-ge v2, v4, :cond_7

    invoke-virtual {v10, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v27

    add-int/lit8 v2, v2, 0x1

    .line 1662
    check-cast v27, Lkotlin/Pair;

    .line 567
    invoke-virtual/range {v27 .. v27}, Lkotlin/Pair;->getFirst()Ljava/lang/Object;

    move-result-object v27

    move-object/from16 v1, v27

    check-cast v1, Ljava/lang/String;

    .line 1662
    invoke-interface {v3, v1}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_5

    .line 567
    :cond_7
    invoke-static {v5, v3}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$resolveOrigins(Lcom/exteragram/messenger/debug/LoadMonitor;Ljava/util/List;)V

    .line 569
    iget-object v1, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->wallMs:[J

    aget-wide v2, v1, v22

    add-long/2addr v2, v12

    aput-wide v2, v1, v22

    .line 570
    iget-object v1, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->cpuMs:[J

    aget-wide v2, v1, v22

    add-long/2addr v2, v14

    aput-wide v2, v1, v22

    .line 571
    iget-object v1, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->rxBytes:[J

    aget-wide v2, v1, v22

    add-long/2addr v2, v8

    aput-wide v2, v1, v22

    .line 572
    iget-object v1, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->txBytes:[J

    aget-wide v2, v1, v22

    add-long/2addr v2, v6

    aput-wide v2, v1, v22

    .line 573
    invoke-interface {v11}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_6
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_9

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/Map$Entry;

    invoke-interface {v2}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    invoke-interface {v2}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Number;

    invoke-virtual {v2}, Ljava/lang/Number;->longValue()J

    move-result-wide v4

    .line 574
    iget-object v2, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->threadMs:Ljava/util/HashMap;

    .line 410
    invoke-interface {v2, v3}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v10

    if-nez v10, :cond_8

    .line 574
    invoke-static {}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$getSTATE_NAMES$p()[Ljava/lang/String;

    move-result-object v10

    array-length v10, v10

    new-array v10, v10, [J

    .line 413
    invoke-interface {v2, v3, v10}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 574
    :cond_8
    check-cast v10, [J

    aget-wide v2, v10, v22

    add-long/2addr v2, v4

    aput-wide v2, v10, v22

    goto :goto_6

    :cond_9
    const-wide/16 v1, 0x3e8

    cmp-long v1, v12, v1

    if-ltz v1, :cond_a

    .line 577
    iget-object v1, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->samples:Lkotlin/collections/ArrayDeque;

    new-instance v2, Lorg/json/JSONObject;

    invoke-direct {v2}, Lorg/json/JSONObject;-><init>()V

    .line 578
    const-string v3, "t"

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v4

    invoke-virtual {v2, v3, v4, v5}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    move-result-object v2

    .line 579
    invoke-static {}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$getSTATE_NAMES$p()[Ljava/lang/String;

    move-result-object v3

    aget-object v3, v3, v22

    const-string v4, "state"

    invoke-virtual {v2, v4, v3}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v2

    .line 580
    const-string v3, "wallMs"

    invoke-virtual {v2, v3, v12, v13}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    move-result-object v2

    .line 581
    const-string v3, "cpuMs"

    invoke-virtual {v2, v3, v14, v15}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    move-result-object v2

    .line 582
    const-string v3, "rxBytes"

    invoke-virtual {v2, v3, v8, v9}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    move-result-object v2

    .line 583
    const-string v3, "txBytes"

    invoke-virtual {v2, v3, v6, v7}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    move-result-object v2

    .line 584
    sget-object v3, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-static {v3}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$isPlugged(Lcom/exteragram/messenger/debug/LoadMonitor;)Ljava/lang/Boolean;

    move-result-object v4

    const-string v5, "plugged"

    invoke-virtual {v2, v5, v4}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v2

    const/4 v4, 0x3

    .line 585
    invoke-static {v3, v11, v4}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$topThreads(Lcom/exteragram/messenger/debug/LoadMonitor;Ljava/util/Map;I)Ljava/util/List;

    move-result-object v4

    const/4 v5, 0x2

    const/4 v6, 0x0

    const/4 v7, 0x0

    invoke-static {v3, v4, v7, v5, v6}, Lcom/exteragram/messenger/debug/LoadMonitor;->threadsJson$default(Lcom/exteragram/messenger/debug/LoadMonitor;Ljava/util/List;ZILjava/lang/Object;)Lorg/json/JSONArray;

    move-result-object v3

    const-string v4, "threads"

    invoke-virtual {v2, v4, v3}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v2

    .line 577
    invoke-virtual {v1, v2}, Lkotlin/collections/ArrayDeque;->addLast(Ljava/lang/Object;)V

    .line 586
    :goto_7
    iget-object v1, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->samples:Lkotlin/collections/ArrayDeque;

    invoke-virtual {v1}, Lkotlin/collections/AbstractMutableList;->size()I

    move-result v1

    const/16 v2, 0xf0

    if-le v1, v2, :cond_a

    .line 587
    iget-object v1, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->samples:Lkotlin/collections/ArrayDeque;

    invoke-virtual {v1}, Lkotlin/collections/ArrayDeque;->removeFirst()Ljava/lang/Object;

    goto :goto_7

    :cond_a
    move-wide/from16 v1, v23

    .line 591
    iput-wide v1, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->lastRealtime:J

    move-wide/from16 v3, v25

    .line 592
    iput-wide v3, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->lastCpuMs:J

    move-wide/from16 v5, v20

    .line 593
    iput-wide v5, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->lastRxBytes:J

    move-wide/from16 v7, v18

    .line 594
    iput-wide v7, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->lastTxBytes:J

    move-object/from16 v9, v16

    .line 595
    iput-object v9, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->threads:Ljava/util/HashMap;

    .line 596
    sget-object v10, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    iget-object v11, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->cpuMs:[J

    invoke-static {v11}, Lkotlin/collections/ArraysKt;->sum([J)J

    move-result-wide v11

    invoke-static {v10, v11, v12}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$formatDuration(Lcom/exteragram/messenger/debug/LoadMonitor;J)Ljava/lang/String;

    move-result-object v10

    iget-object v11, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->rxBytes:[J

    invoke-static {v11}, Lkotlin/collections/ArraysKt;->sum([J)J

    move-result-wide v11

    iget-object v13, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->txBytes:[J

    invoke-static {v13}, Lkotlin/collections/ArraysKt;->sum([J)J

    move-result-wide v13

    add-long/2addr v11, v13

    invoke-static {v11, v12}, Lorg/telegram/messenger/AndroidUtilities;->formatFileSize(J)Ljava/lang/String;

    move-result-object v11

    new-instance v12, Ljava/lang/StringBuilder;

    invoke-direct {v12}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v12, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v10, " CPU, "

    invoke-virtual {v12, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v12, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v12}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v10

    iput-object v10, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->summary:Ljava/lang/String;

    .line 598
    invoke-direct/range {v0 .. v9}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->checkEpisode(JJJJLjava/util/Map;)V

    .line 599
    invoke-direct/range {p0 .. p0}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->updateState()V

    return-void
.end method

.method private final scheduleNext()V
    .locals 3

    .line 547
    iget-object v0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->queue:Lorg/telegram/messenger/DispatchQueue;

    iget-object v1, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->sampleRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/DispatchQueue;->cancelRunnable(Ljava/lang/Runnable;)V

    .line 548
    iget-object v0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->queue:Lorg/telegram/messenger/DispatchQueue;

    iget-object p0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->sampleRunnable:Ljava/lang/Runnable;

    const-wide/32 v1, 0xea60

    invoke-virtual {v0, p0, v1, v2}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;J)Z

    return-void
.end method

.method private final updateState()V
    .locals 14

    .line 603
    sget-object v0, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-static {v0}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$currentState(Lcom/exteragram/messenger/debug/LoadMonitor;)I

    move-result v0

    iput v0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->lastState:I

    if-nez v0, :cond_0

    const/4 v0, 0x0

    .line 605
    iput-object v0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->episode:Lcom/exteragram/messenger/debug/LoadMonitor$Episode;

    return-void

    .line 606
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->episode:Lcom/exteragram/messenger/debug/LoadMonitor$Episode;

    if-nez v0, :cond_1

    .line 607
    new-instance v1, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;

    .line 608
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    .line 609
    iget-wide v4, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->lastRealtime:J

    .line 610
    iget-wide v6, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->lastCpuMs:J

    .line 611
    iget-wide v8, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->lastRxBytes:J

    .line 612
    iget-wide v10, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->lastTxBytes:J

    .line 613
    iget-object v12, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->threads:Ljava/util/HashMap;

    .line 614
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->recentHealthStats()Landroid/os/health/HealthStats;

    move-result-object v13

    .line 607
    invoke-direct/range {v1 .. v13}, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;-><init>(JJJJJLjava/util/Map;Landroid/os/health/HealthStats;)V

    iput-object v1, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->episode:Lcom/exteragram/messenger/debug/LoadMonitor$Episode;

    :cond_1
    return-void
.end method


# virtual methods
.method public final buildReport(Ljava/lang/String;)Lorg/json/JSONObject;
    .locals 21

    move-object/from16 v0, p0

    .line 653
    sget-object v1, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    move-object/from16 v2, p1

    invoke-static {v1, v2}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$baseReport(Lcom/exteragram/messenger/debug/LoadMonitor;Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object v1

    .line 654
    new-instance v2, Lorg/json/JSONObject;

    invoke-direct {v2}, Lorg/json/JSONObject;-><init>()V

    .line 655
    invoke-static {}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$getSTATE_NAMES$p()[Ljava/lang/String;

    move-result-object v3

    array-length v3, v3

    const/4 v5, 0x0

    :goto_0
    const-string v6, "txBytes"

    const-string v7, "rxBytes"

    const-string v8, "wallMs"

    const-string v9, "cpuMs"

    if-ge v5, v3, :cond_0

    .line 656
    invoke-static {}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$getSTATE_NAMES$p()[Ljava/lang/String;

    move-result-object v10

    aget-object v10, v10, v5

    new-instance v11, Lorg/json/JSONObject;

    invoke-direct {v11}, Lorg/json/JSONObject;-><init>()V

    .line 657
    iget-object v12, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->wallMs:[J

    aget-wide v13, v12, v5

    invoke-virtual {v11, v8, v13, v14}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    move-result-object v8

    .line 658
    iget-object v11, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->cpuMs:[J

    aget-wide v12, v11, v5

    invoke-virtual {v8, v9, v12, v13}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    move-result-object v8

    .line 659
    iget-object v9, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->rxBytes:[J

    aget-wide v11, v9, v5

    invoke-virtual {v8, v7, v11, v12}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    move-result-object v7

    .line 660
    iget-object v8, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->txBytes:[J

    aget-wide v11, v8, v5

    invoke-virtual {v7, v6, v11, v12}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    move-result-object v6

    .line 656
    invoke-virtual {v2, v10, v6}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    add-int/lit8 v5, v5, 0x1

    goto :goto_0

    .line 662
    :cond_0
    new-instance v3, Lorg/json/JSONArray;

    invoke-direct {v3}, Lorg/json/JSONArray;-><init>()V

    .line 663
    iget-object v5, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->threadMs:Ljava/util/HashMap;

    invoke-virtual {v5}, Ljava/util/HashMap;->entrySet()Ljava/util/Set;

    move-result-object v5

    .line 1080
    new-instance v10, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler$buildReport$$inlined$sortedByDescending$1;

    invoke-direct {v10}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler$buildReport$$inlined$sortedByDescending$1;-><init>()V

    invoke-static {v5, v10}, Lkotlin/collections/CollectionsKt;->sortedWith(Ljava/lang/Iterable;Ljava/util/Comparator;)Ljava/util/List;

    move-result-object v5

    check-cast v5, Ljava/lang/Iterable;

    const/16 v10, 0x1e

    .line 663
    invoke-static {v5, v10}, Lkotlin/collections/CollectionsKt;->take(Ljava/lang/Iterable;I)Ljava/util/List;

    move-result-object v5

    invoke-interface {v5}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v5

    :goto_1
    invoke-interface {v5}, Ljava/util/Iterator;->hasNext()Z

    move-result v11

    if-eqz v11, :cond_2

    invoke-interface {v5}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v11

    check-cast v11, Ljava/util/Map$Entry;

    invoke-interface {v11}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v12

    check-cast v12, Ljava/lang/String;

    invoke-interface {v11}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v11

    check-cast v11, [J

    .line 664
    new-instance v13, Lorg/json/JSONObject;

    invoke-direct {v13}, Lorg/json/JSONObject;-><init>()V

    const-string v14, "name"

    invoke-virtual {v13, v14, v12}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v13

    invoke-static {v11}, Lkotlin/collections/ArraysKt;->sum([J)J

    move-result-wide v14

    invoke-virtual {v13, v9, v14, v15}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    move-result-object v13

    sget-object v14, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-static {v14, v12}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$originOf(Lcom/exteragram/messenger/debug/LoadMonitor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v12

    const-string v14, "at"

    invoke-virtual {v13, v14, v12}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v12

    .line 665
    invoke-static {}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$getSTATE_NAMES$p()[Ljava/lang/String;

    move-result-object v13

    array-length v13, v13

    const/4 v14, 0x0

    :goto_2
    if-ge v14, v13, :cond_1

    .line 666
    invoke-static {}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$getSTATE_NAMES$p()[Ljava/lang/String;

    move-result-object v15

    aget-object v15, v15, v14

    move-object/from16 v16, v5

    aget-wide v4, v11, v14

    invoke-virtual {v12, v15, v4, v5}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    add-int/lit8 v14, v14, 0x1

    move-object/from16 v5, v16

    goto :goto_2

    :cond_1
    move-object/from16 v16, v5

    .line 668
    invoke-virtual {v3, v12}, Lorg/json/JSONArray;->put(Ljava/lang/Object;)Lorg/json/JSONArray;

    goto :goto_1

    .line 670
    :cond_2
    new-instance v4, Lorg/json/JSONObject;

    invoke-direct {v4}, Lorg/json/JSONObject;-><init>()V

    .line 671
    iget-wide v11, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->startedAt:J

    const-string v5, "startedAt"

    invoke-virtual {v4, v5, v11, v12}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    move-result-object v4

    .line 672
    const-string v11, "sampleIntervalMs"

    const-wide/32 v12, 0xea60

    invoke-virtual {v4, v11, v12, v13}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    move-result-object v4

    .line 673
    const-string v11, "batteryAtStart"

    iget-object v12, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->startBattery:Lorg/json/JSONObject;

    invoke-virtual {v4, v11, v12}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v4

    .line 674
    const-string v11, "states"

    invoke-virtual {v4, v11, v2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v2

    .line 675
    const-string v4, "threads"

    invoke-virtual {v2, v4, v3}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v2

    .line 676
    new-instance v3, Lorg/json/JSONArray;

    iget-object v11, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->samples:Lkotlin/collections/ArrayDeque;

    invoke-direct {v3, v11}, Lorg/json/JSONArray;-><init>(Ljava/util/Collection;)V

    const-string v11, "samples"

    invoke-virtual {v2, v11, v3}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v2

    .line 670
    const-string v3, "monitor"

    invoke-virtual {v1, v3, v2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 677
    iget-object v2, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->episode:Lcom/exteragram/messenger/debug/LoadMonitor$Episode;

    if-eqz v2, :cond_5

    .line 678
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v11

    .line 679
    invoke-static {}, Landroid/os/Process;->getElapsedCpuTime()J

    move-result-wide v13

    .line 680
    sget-object v3, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-static {v3}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$uidRxBytes(Lcom/exteragram/messenger/debug/LoadMonitor;)J

    move-result-wide v15

    .line 681
    invoke-static {v3}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$uidTxBytes(Lcom/exteragram/messenger/debug/LoadMonitor;)J

    move-result-wide v17

    .line 682
    new-instance v10, Lorg/json/JSONObject;

    invoke-direct {v10}, Lorg/json/JSONObject;-><init>()V

    move-wide/from16 v19, v11

    .line 683
    invoke-virtual {v2}, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->getStartedAt()J

    move-result-wide v11

    invoke-virtual {v10, v5, v11, v12}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    move-result-object v5

    .line 684
    invoke-virtual {v2}, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->getStartRealtime()J

    move-result-wide v10

    sub-long v11, v19, v10

    invoke-virtual {v5, v8, v11, v12}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    move-result-object v5

    .line 685
    invoke-virtual {v2}, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->getCpuMs()J

    move-result-wide v10

    sub-long/2addr v13, v10

    invoke-virtual {v5, v9, v13, v14}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    move-result-object v5

    const-wide/16 v8, 0x0

    cmp-long v10, v15, v8

    const-wide/16 v11, -0x1

    if-ltz v10, :cond_3

    .line 686
    invoke-virtual {v2}, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->getRxBytes()J

    move-result-wide v13

    cmp-long v10, v13, v8

    if-ltz v10, :cond_3

    invoke-virtual {v2}, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->getRxBytes()J

    move-result-wide v13

    sub-long/2addr v15, v13

    move-wide v13, v15

    goto :goto_3

    :cond_3
    move-wide v13, v11

    :goto_3
    invoke-virtual {v5, v7, v13, v14}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    move-result-object v5

    cmp-long v7, v17, v8

    if-ltz v7, :cond_4

    .line 687
    invoke-virtual {v2}, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->getTxBytes()J

    move-result-wide v13

    cmp-long v7, v13, v8

    if-ltz v7, :cond_4

    invoke-virtual {v2}, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->getTxBytes()J

    move-result-wide v7

    sub-long v11, v17, v7

    :cond_4
    invoke-virtual {v5, v6, v11, v12}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    move-result-object v5

    .line 688
    invoke-virtual {v2}, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->getThreads()Ljava/util/Map;

    move-result-object v6

    invoke-static {v3}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$readThreads(Lcom/exteragram/messenger/debug/LoadMonitor;)Ljava/util/HashMap;

    move-result-object v7

    invoke-static {v3, v6, v7}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$threadDeltas(Lcom/exteragram/messenger/debug/LoadMonitor;Ljava/util/Map;Ljava/util/Map;)Ljava/util/HashMap;

    move-result-object v6

    const/16 v7, 0x1e

    invoke-static {v3, v6, v7}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$topThreads(Lcom/exteragram/messenger/debug/LoadMonitor;Ljava/util/Map;I)Ljava/util/List;

    move-result-object v6

    const/4 v7, 0x1

    invoke-static {v3, v6, v7}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$threadsJson(Lcom/exteragram/messenger/debug/LoadMonitor;Ljava/util/List;Z)Lorg/json/JSONArray;

    move-result-object v6

    invoke-virtual {v5, v4, v6}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v4

    .line 682
    const-string v5, "episode"

    invoke-virtual {v1, v5, v4}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 689
    invoke-virtual {v2}, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->getHealth()Landroid/os/health/HealthStats;

    move-result-object v2

    if-eqz v2, :cond_5

    const-string v4, "healthEpisodeStart"

    invoke-static {v3, v2}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$healthJson(Lcom/exteragram/messenger/debug/LoadMonitor;Landroid/os/health/HealthStats;)Lorg/json/JSONObject;

    move-result-object v2

    invoke-virtual {v1, v4, v2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 691
    :cond_5
    iget-object v0, v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->startHealth:Landroid/os/health/HealthStats;

    if-eqz v0, :cond_6

    sget-object v2, Lcom/exteragram/messenger/debug/LoadMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/LoadMonitor;

    invoke-static {v2, v0}, Lcom/exteragram/messenger/debug/LoadMonitor;->access$healthJson(Lcom/exteragram/messenger/debug/LoadMonitor;Landroid/os/health/HealthStats;)Lorg/json/JSONObject;

    move-result-object v0

    const-string v2, "healthStart"

    invoke-virtual {v1, v2, v0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    :cond_6
    return-object v1
.end method

.method public final captureReport(Ljava/lang/String;)Lorg/json/JSONObject;
    .locals 0

    .line 648
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->sample()V

    .line 649
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->buildReport(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object p0

    return-object p0
.end method

.method public varargs didReceivedNotification(II[Ljava/lang/Object;)V
    .locals 0

    .line 541
    sget p2, Lorg/telegram/messenger/NotificationCenter;->screenStateChanged:I

    if-ne p1, p2, :cond_0

    .line 542
    iget-object p1, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->queue:Lorg/telegram/messenger/DispatchQueue;

    new-instance p2, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler$$ExternalSyntheticLambda6;

    invoke-direct {p2, p0}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler$$ExternalSyntheticLambda6;-><init>(Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;)V

    invoke-virtual {p1, p2}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    :cond_0
    return-void
.end method

.method public final getQueue()Lorg/telegram/messenger/DispatchQueue;
    .locals 0

    .line 472
    iget-object p0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->queue:Lorg/telegram/messenger/DispatchQueue;

    return-object p0
.end method

.method public final getSummary()Ljava/lang/String;
    .locals 0

    .line 499
    iget-object p0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->summary:Ljava/lang/String;

    return-object p0
.end method

.method public onBecameBackground()V
    .locals 2

    .line 537
    iget-object v0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->queue:Lorg/telegram/messenger/DispatchQueue;

    new-instance v1, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler$$ExternalSyntheticLambda2;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;)V

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public onBecameForeground()V
    .locals 2

    .line 532
    iget-object v0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->queue:Lorg/telegram/messenger/DispatchQueue;

    new-instance v1, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler$$ExternalSyntheticLambda0;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;)V

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    .line 533
    new-instance p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler$$ExternalSyntheticLambda1;

    invoke-direct {p0}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler$$ExternalSyntheticLambda1;-><init>()V

    const-wide/16 v0, 0x3e8

    invoke-static {p0, v0, v1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    return-void
.end method

.method public final stop()V
    .locals 1

    .line 522
    invoke-static {}, Lorg/telegram/ui/Components/ForegroundDetector;->getInstance()Lorg/telegram/ui/Components/ForegroundDetector;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/ForegroundDetector;->removeListener(Lorg/telegram/ui/Components/ForegroundDetector$Listener;)V

    .line 523
    :cond_0
    new-instance v0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler$$ExternalSyntheticLambda7;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler$$ExternalSyntheticLambda7;-><init>(Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    .line 527
    iget-object v0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->queue:Lorg/telegram/messenger/DispatchQueue;

    invoke-virtual {v0}, Lorg/telegram/messenger/DispatchQueue;->cleanupQueue()V

    .line 528
    iget-object p0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Sampler;->queue:Lorg/telegram/messenger/DispatchQueue;

    invoke-virtual {p0}, Lorg/telegram/messenger/DispatchQueue;->recycle()V

    return-void
.end method
