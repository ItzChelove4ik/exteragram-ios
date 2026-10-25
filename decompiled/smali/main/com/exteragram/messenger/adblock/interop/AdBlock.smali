.class public Lcom/exteragram/messenger/adblock/interop/AdBlock;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static volatile enginePtr:J

.field private static final lock:Ljava/util/concurrent/locks/ReentrantReadWriteLock;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 12
    new-instance v0, Ljava/util/concurrent/locks/ReentrantReadWriteLock;

    invoke-direct {v0}, Ljava/util/concurrent/locks/ReentrantReadWriteLock;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/adblock/interop/AdBlock;->lock:Ljava/util/concurrent/locks/ReentrantReadWriteLock;

    const-wide/16 v0, 0x0

    .line 13
    sput-wide v0, Lcom/exteragram/messenger/adblock/interop/AdBlock;->enginePtr:J

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .line 11
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static applyResources()V
    .locals 6

    .line 40
    invoke-static {}, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->getInstance()Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->iterScriptlets()Ljava/util/Collection;

    move-result-object v0

    .line 41
    sget-object v1, Lcom/exteragram/messenger/adblock/interop/AdBlock;->lock:Ljava/util/concurrent/locks/ReentrantReadWriteLock;

    invoke-virtual {v1}, Ljava/util/concurrent/locks/ReentrantReadWriteLock;->writeLock()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;

    move-result-object v2

    invoke-virtual {v2}, Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;->lock()V

    .line 43
    :try_start_0
    sget-wide v2, Lcom/exteragram/messenger/adblock/interop/AdBlock;->enginePtr:J

    const-wide/16 v4, 0x0

    cmp-long v2, v2, v4

    if-eqz v2, :cond_0

    .line 44
    sget-wide v2, Lcom/exteragram/messenger/adblock/interop/AdBlock;->enginePtr:J

    invoke-static {v2, v3, v0}, Lcom/exteragram/messenger/adblock/interop/AdBlock;->useResources(JLjava/util/Collection;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 47
    :cond_0
    invoke-virtual {v1}, Ljava/util/concurrent/locks/ReentrantReadWriteLock;->writeLock()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;->unlock()V

    return-void

    :catchall_0
    move-exception v0

    sget-object v1, Lcom/exteragram/messenger/adblock/interop/AdBlock;->lock:Ljava/util/concurrent/locks/ReentrantReadWriteLock;

    invoke-virtual {v1}, Ljava/util/concurrent/locks/ReentrantReadWriteLock;->writeLock()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;

    move-result-object v1

    invoke-virtual {v1}, Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;->unlock()V

    .line 48
    throw v0
.end method

.method public static build()V
    .locals 4

    .line 20
    invoke-static {}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->getInstance()Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->getSubscriptionFilePaths()Ljava/util/List;

    move-result-object v0

    .line 21
    invoke-interface {v0}, Ljava/util/List;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    return-void

    :cond_0
    const/4 v1, 0x0

    .line 25
    new-array v1, v1, [Ljava/lang/String;

    invoke-static {v1}, Lcom/exteragram/messenger/adblock/interop/NativeAdBlock;->createFilterSet([Ljava/lang/String;)J

    move-result-wide v1

    .line 26
    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    .line 27
    invoke-static {v1, v2, v3}, Lcom/exteragram/messenger/adblock/interop/NativeAdBlock;->addFilters(JLjava/lang/String;)Lcom/exteragram/messenger/adblock/data/FilterListMetadata;

    goto :goto_0

    .line 30
    :cond_1
    invoke-static {v1, v2}, Lcom/exteragram/messenger/adblock/interop/NativeAdBlock;->createEngine(J)J

    move-result-wide v0

    .line 31
    invoke-static {}, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->getInstance()Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;

    move-result-object v2

    invoke-virtual {v2}, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->iterScriptlets()Ljava/util/Collection;

    move-result-object v2

    invoke-static {v0, v1, v2}, Lcom/exteragram/messenger/adblock/interop/AdBlock;->useResources(JLjava/util/Collection;)V

    .line 32
    invoke-static {v0, v1}, Lcom/exteragram/messenger/adblock/interop/AdBlock;->swap(J)V

    return-void
.end method

.method public static destroy()V
    .locals 2

    const-wide/16 v0, 0x0

    .line 36
    invoke-static {v0, v1}, Lcom/exteragram/messenger/adblock/interop/AdBlock;->swap(J)V

    return-void
.end method

.method public static getBlockResult(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/exteragram/messenger/adblock/data/BlockResult;
    .locals 5

    .line 85
    sget-object v0, Lcom/exteragram/messenger/adblock/interop/AdBlock;->lock:Ljava/util/concurrent/locks/ReentrantReadWriteLock;

    invoke-virtual {v0}, Ljava/util/concurrent/locks/ReentrantReadWriteLock;->readLock()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;

    move-result-object v1

    invoke-virtual {v1}, Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;->lock()V

    .line 87
    :try_start_0
    sget-wide v1, Lcom/exteragram/messenger/adblock/interop/AdBlock;->enginePtr:J

    const-wide/16 v3, 0x0

    cmp-long v1, v1, v3

    if-eqz v1, :cond_0

    sget-wide v1, Lcom/exteragram/messenger/adblock/interop/AdBlock;->enginePtr:J

    invoke-static {v1, v2, p0, p1, p2}, Lcom/exteragram/messenger/adblock/interop/NativeAdBlock;->shouldBlock(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/exteragram/messenger/adblock/data/BlockResult;

    move-result-object p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    .line 89
    :goto_0
    invoke-virtual {v0}, Ljava/util/concurrent/locks/ReentrantReadWriteLock;->readLock()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;

    move-result-object p1

    invoke-virtual {p1}, Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;->unlock()V

    return-object p0

    :catchall_0
    move-exception p0

    sget-object p1, Lcom/exteragram/messenger/adblock/interop/AdBlock;->lock:Ljava/util/concurrent/locks/ReentrantReadWriteLock;

    invoke-virtual {p1}, Ljava/util/concurrent/locks/ReentrantReadWriteLock;->readLock()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;

    move-result-object p1

    invoke-virtual {p1}, Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;->unlock()V

    .line 90
    throw p0
.end method

.method public static getCosmeticResources(Ljava/lang/String;)Lcom/exteragram/messenger/adblock/data/UrlCosmeticResources;
    .locals 5

    .line 94
    sget-object v0, Lcom/exteragram/messenger/adblock/interop/AdBlock;->lock:Ljava/util/concurrent/locks/ReentrantReadWriteLock;

    invoke-virtual {v0}, Ljava/util/concurrent/locks/ReentrantReadWriteLock;->writeLock()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;

    move-result-object v1

    invoke-virtual {v1}, Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;->lock()V

    .line 96
    :try_start_0
    sget-wide v1, Lcom/exteragram/messenger/adblock/interop/AdBlock;->enginePtr:J

    const-wide/16 v3, 0x0

    cmp-long v1, v1, v3

    if-eqz v1, :cond_0

    sget-wide v1, Lcom/exteragram/messenger/adblock/interop/AdBlock;->enginePtr:J

    invoke-static {v1, v2, p0}, Lcom/exteragram/messenger/adblock/interop/NativeAdBlock;->getCosmeticResources(JLjava/lang/String;)Lcom/exteragram/messenger/adblock/data/UrlCosmeticResources;

    move-result-object p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    .line 98
    :goto_0
    invoke-virtual {v0}, Ljava/util/concurrent/locks/ReentrantReadWriteLock;->writeLock()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;->unlock()V

    return-object p0

    :catchall_0
    move-exception p0

    sget-object v0, Lcom/exteragram/messenger/adblock/interop/AdBlock;->lock:Ljava/util/concurrent/locks/ReentrantReadWriteLock;

    invoke-virtual {v0}, Ljava/util/concurrent/locks/ReentrantReadWriteLock;->writeLock()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;->unlock()V

    .line 99
    throw p0
.end method

.method public static getHiddenSelectors([Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;)[Ljava/lang/String;
    .locals 5

    .line 103
    sget-object v0, Lcom/exteragram/messenger/adblock/interop/AdBlock;->lock:Ljava/util/concurrent/locks/ReentrantReadWriteLock;

    invoke-virtual {v0}, Ljava/util/concurrent/locks/ReentrantReadWriteLock;->writeLock()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;

    move-result-object v1

    invoke-virtual {v1}, Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;->lock()V

    .line 105
    :try_start_0
    sget-wide v1, Lcom/exteragram/messenger/adblock/interop/AdBlock;->enginePtr:J

    const-wide/16 v3, 0x0

    cmp-long v1, v1, v3

    if-eqz v1, :cond_0

    sget-wide v1, Lcom/exteragram/messenger/adblock/interop/AdBlock;->enginePtr:J

    invoke-static {v1, v2, p0, p1, p2}, Lcom/exteragram/messenger/adblock/interop/NativeAdBlock;->getHiddenSelectors(J[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;)[Ljava/lang/String;

    move-result-object p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    .line 107
    :goto_0
    invoke-virtual {v0}, Ljava/util/concurrent/locks/ReentrantReadWriteLock;->writeLock()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;

    move-result-object p1

    invoke-virtual {p1}, Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;->unlock()V

    return-object p0

    :catchall_0
    move-exception p0

    sget-object p1, Lcom/exteragram/messenger/adblock/interop/AdBlock;->lock:Ljava/util/concurrent/locks/ReentrantReadWriteLock;

    invoke-virtual {p1}, Ljava/util/concurrent/locks/ReentrantReadWriteLock;->writeLock()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;

    move-result-object p1

    invoke-virtual {p1}, Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;->unlock()V

    .line 108
    throw p0
.end method

.method public static isReady()Z
    .locals 4

    .line 16
    sget-wide v0, Lcom/exteragram/messenger/adblock/interop/AdBlock;->enginePtr:J

    const-wide/16 v2, 0x0

    cmp-long v0, v0, v2

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    return v0

    :cond_0
    const/4 v0, 0x0

    return v0
.end method

.method private static swap(J)V
    .locals 3

    .line 53
    sget-object v0, Lcom/exteragram/messenger/adblock/interop/AdBlock;->lock:Ljava/util/concurrent/locks/ReentrantReadWriteLock;

    invoke-virtual {v0}, Ljava/util/concurrent/locks/ReentrantReadWriteLock;->writeLock()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;

    move-result-object v1

    invoke-virtual {v1}, Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;->lock()V

    .line 55
    :try_start_0
    sget-wide v1, Lcom/exteragram/messenger/adblock/interop/AdBlock;->enginePtr:J

    .line 56
    sput-wide p0, Lcom/exteragram/messenger/adblock/interop/AdBlock;->enginePtr:J
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 58
    invoke-virtual {v0}, Ljava/util/concurrent/locks/ReentrantReadWriteLock;->writeLock()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;

    move-result-object p0

    invoke-virtual {p0}, Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;->unlock()V

    const-wide/16 p0, 0x0

    cmp-long p0, v1, p0

    if-eqz p0, :cond_0

    .line 61
    invoke-static {v1, v2}, Lcom/exteragram/messenger/adblock/interop/NativeAdBlock;->destroyEngine(J)V

    :cond_0
    return-void

    :catchall_0
    move-exception p0

    .line 58
    sget-object p1, Lcom/exteragram/messenger/adblock/interop/AdBlock;->lock:Ljava/util/concurrent/locks/ReentrantReadWriteLock;

    invoke-virtual {p1}, Ljava/util/concurrent/locks/ReentrantReadWriteLock;->writeLock()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;

    move-result-object p1

    invoke-virtual {p1}, Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;->unlock()V

    .line 59
    throw p0
.end method

.method private static useResources(JLjava/util/Collection;)V
    .locals 9
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(J",
            "Ljava/util/Collection<",
            "Lcom/exteragram/messenger/adblock/backend/ScriptletsManager$Scriptlet;",
            ">;)V"
        }
    .end annotation

    .line 66
    invoke-interface {p2}, Ljava/util/Collection;->size()I

    move-result v0

    .line 67
    new-array v3, v0, [Ljava/lang/String;

    .line 68
    new-array v4, v0, [[Ljava/lang/String;

    .line 69
    new-array v5, v0, [Ljava/lang/String;

    .line 70
    new-array v6, v0, [Ljava/lang/String;

    .line 73
    invoke-interface {p2}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object p2

    const/4 v0, 0x0

    move v1, v0

    :goto_0
    invoke-interface {p2}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {p2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager$Scriptlet;

    .line 74
    iget-object v7, v2, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager$Scriptlet;->filename:Ljava/lang/String;

    aput-object v7, v3, v1

    .line 75
    iget-object v7, v2, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager$Scriptlet;->aliases:Ljava/util/List;

    if-eqz v7, :cond_0

    new-array v8, v0, [Ljava/lang/String;

    invoke-interface {v7, v8}, Ljava/util/List;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;

    move-result-object v7

    check-cast v7, [Ljava/lang/String;

    goto :goto_1

    :cond_0
    new-array v7, v0, [Ljava/lang/String;

    :goto_1
    aput-object v7, v4, v1

    .line 76
    iget-object v7, v2, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager$Scriptlet;->filename:Ljava/lang/String;

    invoke-static {v7}, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->getExtension(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    aput-object v7, v5, v1

    .line 77
    iget-object v2, v2, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager$Scriptlet;->content:Ljava/lang/String;

    aput-object v2, v6, v1

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_1
    move-wide v1, p0

    .line 81
    invoke-static/range {v1 .. v6}, Lcom/exteragram/messenger/adblock/interop/NativeAdBlock;->useResources(J[Ljava/lang/String;[[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;)V

    return-void
.end method
