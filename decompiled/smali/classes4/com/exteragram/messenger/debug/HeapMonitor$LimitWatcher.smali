.class final Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/ui/Components/ForegroundDetector$Listener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/debug/HeapMonitor;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "LimitWatcher"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\u0008\u0002\n\u0002\u0010\u0002\n\u0002\u0008\u0008\u0008\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0008\u0010\r\u001a\u00020\u000eH\u0016J\u0008\u0010\u000f\u001a\u00020\u000eH\u0016J\u0006\u0010\u0010\u001a\u00020\u000eJ\u0006\u0010\u0011\u001a\u00020\u000eJ\u000e\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000bJ\u0008\u0010\u0014\u001a\u00020\u000eH\u0002J\u0008\u0010\u0015\u001a\u00020\u000eH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0008\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000c\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"
    }
    d2 = {
        "Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;",
        "Lorg/telegram/ui/Components/ForegroundDetector$Listener;",
        "<init>",
        "()V",
        "queue",
        "Lorg/telegram/messenger/DispatchQueue;",
        "checkRunnable",
        "Ljava/lang/Runnable;",
        "running",
        "",
        "nextPromptBytes",
        "",
        "lastCollectTime",
        "onBecameForeground",
        "",
        "onBecameBackground",
        "stop",
        "resetPrompt",
        "postponePrompt",
        "usedBytes",
        "updateRunning",
        "check",
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


# instance fields
.field private final checkRunnable:Ljava/lang/Runnable;

.field private lastCollectTime:J

.field private nextPromptBytes:J

.field private final queue:Lorg/telegram/messenger/DispatchQueue;

.field private volatile running:Z


# direct methods
.method public static $r8$lambda$1eef_Xx9_DcRVhqCQaqT1pw9pr8(Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;)V
    .locals 0

    .line 237
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->check()V

    return-void
.end method

.method public static $r8$lambda$Glvfehde9YHk6gQtUreR-r-Mb9g(JJ)V
    .locals 1

    .line 299
    sget-object v0, Lcom/exteragram/messenger/debug/HeapMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/HeapMonitor;

    invoke-static {v0, p0, p1, p2, p3}, Lcom/exteragram/messenger/debug/HeapMonitor;->access$showLimitAlert(Lcom/exteragram/messenger/debug/HeapMonitor;JJ)V

    return-void
.end method

.method public static $r8$lambda$TOO-MnxGNhTNPbLmNjNZLVNYFM8(Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;)V
    .locals 3

    .line 278
    iget-object v0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->queue:Lorg/telegram/messenger/DispatchQueue;

    iget-object v1, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->checkRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/DispatchQueue;->cancelRunnable(Ljava/lang/Runnable;)V

    .line 279
    iget-boolean v0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->running:Z

    if-eqz v0, :cond_0

    .line 280
    iget-object v0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->queue:Lorg/telegram/messenger/DispatchQueue;

    iget-object p0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->checkRunnable:Ljava/lang/Runnable;

    const-wide/16 v1, 0x1388

    invoke-virtual {v0, p0, v1, v2}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;J)Z

    :cond_0
    return-void
.end method

.method public static $r8$lambda$i0uVHpCZaV6c-3J4CwvOvyrTDcQ(Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;J)V
    .locals 4

    .line 269
    iget-wide v0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->nextPromptBytes:J

    sget-object v2, Lcom/exteragram/messenger/debug/HeapMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/HeapMonitor;

    invoke-virtual {v2}, Lcom/exteragram/messenger/debug/HeapMonitor;->getPromptStepBytes()J

    move-result-wide v2

    add-long/2addr p1, v2

    invoke-static {v0, v1, p1, p2}, Ljava/lang/Math;->max(JJ)J

    move-result-wide p1

    iput-wide p1, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->nextPromptBytes:J

    return-void
.end method

.method public static $r8$lambda$sR_UlDrroxxv8iXPrMFg1erNAWw(Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;)V
    .locals 2

    const-wide/16 v0, 0x0

    .line 262
    iput-wide v0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->nextPromptBytes:J

    .line 263
    iput-wide v0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->lastCollectTime:J

    return-void
.end method

.method public constructor <init>()V
    .locals 2

    .line 234
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 236
    new-instance v0, Lorg/telegram/messenger/DispatchQueue;

    const-string v1, "heapMonitorQueue"

    invoke-direct {v0, v1}, Lorg/telegram/messenger/DispatchQueue;-><init>(Ljava/lang/String;)V

    iput-object v0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->queue:Lorg/telegram/messenger/DispatchQueue;

    .line 237
    new-instance v0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;)V

    iput-object v0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->checkRunnable:Ljava/lang/Runnable;

    .line 245
    invoke-static {}, Lorg/telegram/ui/Components/ForegroundDetector;->getInstance()Lorg/telegram/ui/Components/ForegroundDetector;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/ForegroundDetector;->addListener(Lorg/telegram/ui/Components/ForegroundDetector$Listener;)V

    .line 246
    :cond_0
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->updateRunning()V

    return-void
.end method

.method private final check()V
    .locals 7

    .line 286
    iget-boolean v0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->running:Z

    if-nez v0, :cond_0

    goto :goto_1

    .line 287
    :cond_0
    sget-object v0, Lcom/exteragram/messenger/debug/HeapMonitor;->INSTANCE:Lcom/exteragram/messenger/debug/HeapMonitor;

    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/HeapMonitor;->getLimitMb()I

    move-result v1

    int-to-long v1, v1

    const/16 v3, 0x14

    shl-long/2addr v1, v3

    .line 288
    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/HeapMonitor;->getUsedBytes()J

    move-result-wide v3

    cmp-long v5, v3, v1

    if-gez v5, :cond_1

    const-wide/16 v0, 0x0

    .line 290
    iput-wide v0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->nextPromptBytes:J

    goto :goto_0

    .line 291
    :cond_1
    iget-wide v5, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->nextPromptBytes:J

    cmp-long v3, v3, v5

    if-ltz v3, :cond_2

    invoke-static {}, Lcom/exteragram/messenger/debug/HeapMonitor;->access$getDumping$p()Z

    move-result v3

    if-nez v3, :cond_2

    sget-boolean v3, Lorg/telegram/messenger/ApplicationLoader;->mainInterfacePaused:Z

    if-nez v3, :cond_2

    .line 292
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v3

    iget-wide v5, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->lastCollectTime:J

    sub-long/2addr v3, v5

    const-wide/16 v5, 0x7530

    cmp-long v3, v3, v5

    if-ltz v3, :cond_2

    .line 294
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v3

    iput-wide v3, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->lastCollectTime:J

    .line 295
    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/HeapMonitor;->collectGarbage$TMessagesProj()V

    .line 296
    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/HeapMonitor;->getUsedBytes()J

    move-result-wide v3

    .line 297
    iget-wide v5, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->nextPromptBytes:J

    invoke-static {v1, v2, v5, v6}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v5

    cmp-long v5, v3, v5

    if-ltz v5, :cond_2

    .line 298
    invoke-virtual {v0}, Lcom/exteragram/messenger/debug/HeapMonitor;->getPromptStepBytes()J

    move-result-wide v5

    add-long/2addr v5, v3

    iput-wide v5, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->nextPromptBytes:J

    .line 299
    new-instance v0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher$$ExternalSyntheticLambda3;

    invoke-direct {v0, v3, v4, v1, v2}, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher$$ExternalSyntheticLambda3;-><init>(JJ)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    .line 302
    :cond_2
    :goto_0
    iget-boolean v0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->running:Z

    if-eqz v0, :cond_3

    .line 303
    iget-object v0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->queue:Lorg/telegram/messenger/DispatchQueue;

    iget-object p0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->checkRunnable:Ljava/lang/Runnable;

    const-wide/16 v1, 0x1388

    invoke-virtual {v0, p0, v1, v2}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;J)Z

    :cond_3
    :goto_1
    return-void
.end method

.method private final updateRunning()V
    .locals 3

    .line 274
    invoke-static {}, Lorg/telegram/ui/Components/ForegroundDetector;->getInstance()Lorg/telegram/ui/Components/ForegroundDetector;

    move-result-object v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lorg/telegram/ui/Components/ForegroundDetector;->isForeground()Z

    move-result v0

    const/4 v2, 0x1

    if-ne v0, v2, :cond_0

    move v1, v2

    .line 275
    :cond_0
    iget-boolean v0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->running:Z

    if-ne v0, v1, :cond_1

    return-void

    .line 276
    :cond_1
    iput-boolean v1, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->running:Z

    .line 277
    iget-object v0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->queue:Lorg/telegram/messenger/DispatchQueue;

    new-instance v1, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher$$ExternalSyntheticLambda1;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;)V

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method


# virtual methods
.method public onBecameBackground()V
    .locals 0

    .line 251
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->updateRunning()V

    return-void
.end method

.method public onBecameForeground()V
    .locals 0

    .line 249
    invoke-direct {p0}, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->updateRunning()V

    return-void
.end method

.method public final postponePrompt(J)V
    .locals 2

    .line 268
    iget-object v0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->queue:Lorg/telegram/messenger/DispatchQueue;

    new-instance v1, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher$$ExternalSyntheticLambda4;

    invoke-direct {v1, p0, p1, p2}, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher$$ExternalSyntheticLambda4;-><init>(Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;J)V

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public final resetPrompt()V
    .locals 2

    .line 261
    iget-object v0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->queue:Lorg/telegram/messenger/DispatchQueue;

    new-instance v1, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher$$ExternalSyntheticLambda2;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;)V

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public final stop()V
    .locals 1

    .line 254
    invoke-static {}, Lorg/telegram/ui/Components/ForegroundDetector;->getInstance()Lorg/telegram/ui/Components/ForegroundDetector;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/ForegroundDetector;->removeListener(Lorg/telegram/ui/Components/ForegroundDetector$Listener;)V

    :cond_0
    const/4 v0, 0x0

    .line 255
    iput-boolean v0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->running:Z

    .line 256
    iget-object v0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->queue:Lorg/telegram/messenger/DispatchQueue;

    invoke-virtual {v0}, Lorg/telegram/messenger/DispatchQueue;->cleanupQueue()V

    .line 257
    iget-object p0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->queue:Lorg/telegram/messenger/DispatchQueue;

    invoke-virtual {p0}, Lorg/telegram/messenger/DispatchQueue;->recycle()V

    return-void
.end method
