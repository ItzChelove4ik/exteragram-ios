.class public abstract Landroidx/media3/common/util/BackgroundExecutor;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static staticInstance:Ljava/util/concurrent/Executor;


# direct methods
.method public static declared-synchronized get()Ljava/util/concurrent/Executor;
    .locals 2

    const-class v0, Landroidx/media3/common/util/BackgroundExecutor;

    monitor-enter v0

    .line 38
    :try_start_0
    sget-object v1, Landroidx/media3/common/util/BackgroundExecutor;->staticInstance:Ljava/util/concurrent/Executor;

    if-nez v1, :cond_0

    .line 39
    const-string v1, "ExoPlayer:BackgroundExecutor"

    invoke-static {v1}, Landroidx/media3/common/util/Util;->newSingleThreadExecutor(Ljava/lang/String;)Ljava/util/concurrent/ExecutorService;

    move-result-object v1

    sput-object v1, Landroidx/media3/common/util/BackgroundExecutor;->staticInstance:Ljava/util/concurrent/Executor;

    goto :goto_0

    :catchall_0
    move-exception v1

    goto :goto_1

    .line 41
    :cond_0
    :goto_0
    sget-object v1, Landroidx/media3/common/util/BackgroundExecutor;->staticInstance:Ljava/util/concurrent/Executor;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit v0

    return-object v1

    :goto_1
    :try_start_1
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v1
.end method
