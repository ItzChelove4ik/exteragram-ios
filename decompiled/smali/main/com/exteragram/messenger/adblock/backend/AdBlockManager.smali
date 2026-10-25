.class public abstract Lcom/exteragram/messenger/adblock/backend/AdBlockManager;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final FILTERS:[Ljava/lang/String;

.field private static loading:Z

.field private static queue:Lorg/telegram/messenger/DispatchQueue;

.field private static final readyCallbacks:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Ljava/lang/Runnable;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public static synthetic $r8$lambda$NX8OXkxyVjTNyOUDKguiPVa3dS8()V
    .locals 1

    const/4 v0, 0x0

    .line 59
    invoke-static {v0}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->load(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static synthetic $r8$lambda$SRj9nQUD4lLiPK5Ln8H1-nPUIPI()V
    .locals 1

    const/4 v0, 0x0

    .line 53
    invoke-static {v0}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->load(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static synthetic $r8$lambda$VkMrvF4u0rpTiyBLPhtCni5XDeE(ZLjava/lang/Runnable;)V
    .locals 0

    if-eqz p0, :cond_0

    .line 67
    invoke-static {p1}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->load(Ljava/lang/Runnable;)V

    return-void

    .line 69
    :cond_0
    sget-object p0, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->readyCallbacks:Ljava/util/ArrayList;

    invoke-virtual {p0}, Ljava/util/ArrayList;->clear()V

    .line 70
    invoke-static {}, Lcom/exteragram/messenger/adblock/interop/AdBlock;->destroy()V

    return-void
.end method

.method public static synthetic $r8$lambda$VvvmAH8sWhBT5SVzdcMZl989bxQ(Ljava/util/ArrayList;)V
    .locals 3

    .line 122
    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result v0

    const/4 v1, 0x0

    :goto_0
    if-ge v1, v0, :cond_0

    invoke-virtual {p0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    add-int/lit8 v1, v1, 0x1

    check-cast v2, Ljava/lang/Runnable;

    .line 123
    invoke-interface {v2}, Ljava/lang/Runnable;->run()V

    goto :goto_0

    :cond_0
    return-void
.end method

.method public static synthetic $r8$lambda$aZ6pyyx_-VLqumhb1lWfnqqAtkI(Ljava/lang/Boolean;)V
    .locals 1

    .line 98
    invoke-static {}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->getQueue()Lorg/telegram/messenger/DispatchQueue;

    move-result-object p0

    new-instance v0, Lcom/exteragram/messenger/adblock/backend/AdBlockManager$$ExternalSyntheticLambda6;

    invoke-direct {v0}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager$$ExternalSyntheticLambda6;-><init>()V

    invoke-virtual {p0, v0}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public static synthetic $r8$lambda$mAUomQJn7qBCLzHdA6TkvE_lh94()V
    .locals 2

    .line 106
    invoke-static {}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->getQueue()Lorg/telegram/messenger/DispatchQueue;

    move-result-object v0

    new-instance v1, Lcom/exteragram/messenger/adblock/backend/AdBlockManager$$ExternalSyntheticLambda4;

    invoke-direct {v1}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager$$ExternalSyntheticLambda4;-><init>()V

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public static synthetic $r8$lambda$q9EOL701yIVVnq6u5SQnHEOAri8()V
    .locals 0

    .line 0
    invoke-static {}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->build()V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 10

    .line 17
    const-string/jumbo v8, "https://cdn.jsdelivr.net/gh/uBlockOrigin/uAssetsCDN@main/thirdparties/easyprivacy.txt"

    const-string/jumbo v9, "https://cdn.jsdelivr.net/gh/dimisa-RUAdList/RUAdListCDN@main/lists/ruadlist.ubo.min.txt"

    const-string/jumbo v0, "https://ublockorigin.github.io/uAssetsCDN/filters/filters.min.txt"

    const-string/jumbo v1, "https://ublockorigin.github.io/uAssetsCDN/filters/badware.min.txt"

    const-string/jumbo v2, "https://ublockorigin.github.io/uAssetsCDN/filters/privacy.min.txt"

    const-string/jumbo v3, "https://ublockorigin.github.io/uAssetsCDN/filters/unbreak.min.txt"

    const-string/jumbo v4, "https://ublockorigin.github.io/uAssetsCDN/filters/quick-fixes.min.txt"

    const-string/jumbo v5, "https://filters.adtidy.org/extension/ublock/filters/11.txt"

    const-string/jumbo v6, "https://filters.adtidy.org/extension/ublock/filters/2_without_easylist.txt"

    const-string/jumbo v7, "https://cdn.jsdelivr.net/gh/uBlockOrigin/uAssetsCDN@main/thirdparties/easylist.txt"

    filled-new-array/range {v0 .. v9}, [Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->FILTERS:[Ljava/lang/String;

    .line 32
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->readyCallbacks:Ljava/util/ArrayList;

    return-void
.end method

.method private static build()V
    .locals 2

    const/4 v0, 0x0

    .line 111
    sput-boolean v0, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->loading:Z

    .line 112
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getEnableAdBlock()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 113
    invoke-static {}, Lcom/exteragram/messenger/adblock/interop/AdBlock;->build()V

    .line 115
    :cond_0
    sget-object v0, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->readyCallbacks:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_1

    goto :goto_0

    .line 118
    :cond_1
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1, v0}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    .line 119
    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 120
    invoke-static {}, Lcom/exteragram/messenger/adblock/interop/AdBlock;->isReady()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 121
    new-instance v0, Lcom/exteragram/messenger/adblock/backend/AdBlockManager$$ExternalSyntheticLambda5;

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager$$ExternalSyntheticLambda5;-><init>(Ljava/util/ArrayList;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    :cond_2
    :goto_0
    return-void
.end method

.method private static declared-synchronized getQueue()Lorg/telegram/messenger/DispatchQueue;
    .locals 3

    const-class v0, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;

    monitor-enter v0

    .line 37
    :try_start_0
    sget-object v1, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->queue:Lorg/telegram/messenger/DispatchQueue;

    if-nez v1, :cond_0

    .line 38
    new-instance v1, Lorg/telegram/messenger/DispatchQueue;

    const-string v2, "AdBlockManager"

    invoke-direct {v1, v2}, Lorg/telegram/messenger/DispatchQueue;-><init>(Ljava/lang/String;)V

    sput-object v1, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->queue:Lorg/telegram/messenger/DispatchQueue;

    goto :goto_0

    :catchall_0
    move-exception v1

    goto :goto_1

    .line 40
    :cond_0
    :goto_0
    sget-object v1, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->queue:Lorg/telegram/messenger/DispatchQueue;
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

.method public static initialize()V
    .locals 2

    .line 52
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getEnableAdBlock()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 53
    invoke-static {}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->getQueue()Lorg/telegram/messenger/DispatchQueue;

    move-result-object v0

    new-instance v1, Lcom/exteragram/messenger/adblock/backend/AdBlockManager$$ExternalSyntheticLambda1;

    invoke-direct {v1}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager$$ExternalSyntheticLambda1;-><init>()V

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    :cond_0
    return-void
.end method

.method public static isActive()Z
    .locals 1

    .line 48
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getEnableAdBlock()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-static {}, Lcom/exteragram/messenger/adblock/interop/AdBlock;->isReady()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    return v0

    :cond_0
    const/4 v0, 0x0

    return v0
.end method

.method public static isAvailable()Z
    .locals 2

    .line 44
    const-string/jumbo v0, "use_adblock"

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->getBooleanConfigValue(Ljava/lang/String;Z)Ljava/lang/Boolean;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-static {}, Lcom/exteragram/messenger/adblock/interop/NativeAdBlock;->isLoadFailed()Z

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    return v0

    :cond_0
    return v1
.end method

.method private static load(Ljava/lang/Runnable;)V
    .locals 2

    .line 76
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getEnableAdBlock()Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 79
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/adblock/interop/AdBlock;->isReady()Z

    move-result v0

    if-eqz v0, :cond_1

    if-eqz p0, :cond_7

    .line 81
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void

    .line 85
    :cond_1
    const-string/jumbo v0, "use_adblock"

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->getBooleanConfigValue(Ljava/lang/String;Z)Ljava/lang/Boolean;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    if-eqz v0, :cond_7

    invoke-static {}, Lcom/exteragram/messenger/adblock/interop/NativeAdBlock;->loadLibraries()Z

    move-result v0

    if-nez v0, :cond_2

    goto :goto_0

    :cond_2
    if-eqz p0, :cond_3

    .line 89
    sget-object v0, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->readyCallbacks:Ljava/util/ArrayList;

    invoke-virtual {v0, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 91
    :cond_3
    sget-boolean p0, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->loading:Z

    if-eqz p0, :cond_4

    goto :goto_0

    :cond_4
    const/4 p0, 0x1

    .line 94
    sput-boolean p0, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->loading:Z

    .line 96
    invoke-static {}, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->getInstance()Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;

    move-result-object p0

    .line 97
    invoke-virtual {p0}, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->isDownloaded()Z

    move-result v0

    if-nez v0, :cond_5

    .line 98
    new-instance v0, Lcom/exteragram/messenger/adblock/backend/AdBlockManager$$ExternalSyntheticLambda2;

    invoke-direct {v0}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager$$ExternalSyntheticLambda2;-><init>()V

    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->download(Lorg/telegram/messenger/Utilities$Callback;)V

    .line 101
    :cond_5
    invoke-static {}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->getInstance()Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;

    move-result-object p0

    .line 102
    invoke-virtual {p0}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->hasFilters()Z

    move-result v0

    if-eqz v0, :cond_6

    .line 103
    invoke-static {}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->build()V

    .line 104
    sget-object v0, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->FILTERS:[Ljava/lang/String;

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->update([Ljava/lang/String;Ljava/lang/Runnable;)V

    return-void

    .line 106
    :cond_6
    sget-object v0, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->FILTERS:[Ljava/lang/String;

    new-instance v1, Lcom/exteragram/messenger/adblock/backend/AdBlockManager$$ExternalSyntheticLambda3;

    invoke-direct {v1}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager$$ExternalSyntheticLambda3;-><init>()V

    invoke-virtual {p0, v0, v1}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->update([Ljava/lang/String;Ljava/lang/Runnable;)V

    :cond_7
    :goto_0
    return-void
.end method

.method public static preload()V
    .locals 4

    .line 58
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getEnableAdBlock()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 59
    invoke-static {}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->getQueue()Lorg/telegram/messenger/DispatchQueue;

    move-result-object v0

    new-instance v1, Lcom/exteragram/messenger/adblock/backend/AdBlockManager$$ExternalSyntheticLambda0;

    invoke-direct {v1}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager$$ExternalSyntheticLambda0;-><init>()V

    const-wide/16 v2, 0x2710

    invoke-virtual {v0, v1, v2, v3}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;J)Z

    :cond_0
    return-void
.end method

.method public static setEnabled(ZLjava/lang/Runnable;)V
    .locals 2

    .line 64
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setEnableAdBlock(Z)V

    .line 65
    invoke-static {}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->getQueue()Lorg/telegram/messenger/DispatchQueue;

    move-result-object v0

    new-instance v1, Lcom/exteragram/messenger/adblock/backend/AdBlockManager$$ExternalSyntheticLambda7;

    invoke-direct {v1, p0, p1}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager$$ExternalSyntheticLambda7;-><init>(ZLjava/lang/Runnable;)V

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method
