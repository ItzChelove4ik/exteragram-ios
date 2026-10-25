.class public Lorg/telegram/messenger/ProxyRotationController;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;


# static fields
.field public static final DEFAULT_TIMEOUT_INDEX:I = 0x1

.field private static final INSTANCE:Lorg/telegram/messenger/ProxyRotationController;

.field public static final ROTATION_TIMEOUTS:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private checkProxyAndSwitchRunnable:Ljava/lang/Runnable;

.field private isCurrentlyChecking:Z


# direct methods
.method public static synthetic $r8$lambda$1qJ21Y6U4pSijLu67isAkLum5lk(Lorg/telegram/messenger/SharedConfig$ProxyInfo;Lorg/telegram/messenger/SharedConfig$ProxyInfo;)I
    .locals 2

    .line 69
    iget-wide v0, p0, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->ping:J

    iget-wide p0, p1, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->ping:J

    invoke-static {v0, v1, p0, p1}, Ljava/lang/Long;->compare(JJ)I

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$FJmGRCN67bPTU1pXgzMUZvFLhhE(Lorg/telegram/messenger/ProxyRotationController;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/messenger/ProxyRotationController;->lambda$new$2()V

    return-void
.end method

.method public static synthetic $r8$lambda$eWTdX8ujv-si5gV2KcZj1iGeQgI(Lorg/telegram/messenger/SharedConfig$ProxyInfo;J)V
    .locals 1

    .line 36
    new-instance v0, Lorg/telegram/messenger/ProxyRotationController$$ExternalSyntheticLambda3;

    invoke-direct {v0, p0, p1, p2}, Lorg/telegram/messenger/ProxyRotationController$$ExternalSyntheticLambda3;-><init>(Lorg/telegram/messenger/SharedConfig$ProxyInfo;J)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static synthetic $r8$lambda$y4AyqLfFAqbbC1e8rcgYYgTc97s(Lorg/telegram/messenger/SharedConfig$ProxyInfo;J)V
    .locals 3

    .line 37
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v0

    iput-wide v0, p0, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->availableCheckTime:J

    const/4 v0, 0x0

    .line 38
    iput-boolean v0, p0, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->checking:Z

    const-wide/16 v1, -0x1

    cmp-long v1, p1, v1

    if-nez v1, :cond_0

    .line 40
    iput-boolean v0, p0, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->available:Z

    const-wide/16 p1, 0x0

    .line 41
    iput-wide p1, p0, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->ping:J

    goto :goto_0

    .line 43
    :cond_0
    iput-wide p1, p0, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->ping:J

    const/4 p1, 0x1

    .line 44
    iput-boolean p1, p0, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->available:Z

    .line 46
    :goto_0
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    sget p2, Lorg/telegram/messenger/NotificationCenter;->proxyCheckDone:I

    filled-new-array {p0}, [Ljava/lang/Object;

    move-result-object p0

    invoke-virtual {p1, p2, p0}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 5

    .line 16
    new-instance v0, Lorg/telegram/messenger/ProxyRotationController;

    invoke-direct {v0}, Lorg/telegram/messenger/ProxyRotationController;-><init>()V

    sput-object v0, Lorg/telegram/messenger/ProxyRotationController;->INSTANCE:Lorg/telegram/messenger/ProxyRotationController;

    const/4 v0, 0x5

    .line 20
    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const/16 v1, 0xa

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    const/16 v2, 0xf

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    const/16 v3, 0x1e

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    const/16 v4, 0x3c

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    filled-new-array {v0, v1, v2, v3, v4}, [Ljava/lang/Integer;

    move-result-object v0

    .line 19
    invoke-static {v0}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object v0

    sput-object v0, Lorg/telegram/messenger/ProxyRotationController;->ROTATION_TIMEOUTS:Ljava/util/List;

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .line 15
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 24
    new-instance v0, Lorg/telegram/messenger/ProxyRotationController$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0}, Lorg/telegram/messenger/ProxyRotationController$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/messenger/ProxyRotationController;)V

    iput-object v0, p0, Lorg/telegram/messenger/ProxyRotationController;->checkProxyAndSwitchRunnable:Ljava/lang/Runnable;

    return-void
.end method

.method public static init()V
    .locals 1

    .line 57
    sget-object v0, Lorg/telegram/messenger/ProxyRotationController;->INSTANCE:Lorg/telegram/messenger/ProxyRotationController;

    invoke-direct {v0}, Lorg/telegram/messenger/ProxyRotationController;->initInternal()V

    return-void
.end method

.method private initInternal()V
    .locals 3

    const/4 v0, 0x0

    :goto_0
    const/16 v1, 0x10

    if-ge v0, v1, :cond_0

    .line 90
    invoke-static {v0}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v1

    sget v2, Lorg/telegram/messenger/NotificationCenter;->didUpdateConnectionState:I

    invoke-virtual {v1, p0, v2}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 92
    :cond_0
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->proxyCheckDone:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 93
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->proxySettingsChanged:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    return-void
.end method

.method private synthetic lambda$new$2()V
    .locals 10

    const/4 v0, 0x1

    .line 25
    iput-boolean v0, p0, Lorg/telegram/messenger/ProxyRotationController;->isCurrentlyChecking:Z

    .line 27
    sget v1, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    const/4 v2, 0x0

    move v3, v2

    move v4, v3

    .line 29
    :goto_0
    sget-object v5, Lorg/telegram/messenger/SharedConfig;->proxyList:Ljava/util/ArrayList;

    invoke-virtual {v5}, Ljava/util/ArrayList;->size()I

    move-result v5

    if-ge v3, v5, :cond_2

    .line 30
    sget-object v5, Lorg/telegram/messenger/SharedConfig;->proxyList:Ljava/util/ArrayList;

    invoke-virtual {v5, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    .line 31
    iget-boolean v6, v5, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->checking:Z

    if-nez v6, :cond_1

    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v6

    iget-wide v8, v5, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->availableCheckTime:J

    sub-long/2addr v6, v8

    const-wide/32 v8, 0x1d4c0

    cmp-long v6, v6, v8

    if-gez v6, :cond_0

    goto :goto_1

    .line 35
    :cond_0
    iput-boolean v0, v5, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->checking:Z

    .line 36
    invoke-static {v1}, Lorg/telegram/tgnet/ConnectionsManager;->getInstance(I)Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object v4

    iget-object v6, v5, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    new-instance v7, Lorg/telegram/messenger/ProxyRotationController$$ExternalSyntheticLambda2;

    invoke-direct {v7, v5}, Lorg/telegram/messenger/ProxyRotationController$$ExternalSyntheticLambda2;-><init>(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)V

    invoke-virtual {v4, v6, v7}, Lorg/telegram/tgnet/ConnectionsManager;->checkProxy(Lorg/telegram/utils/proxy/ProxySettings;Lorg/telegram/tgnet/RequestTimeDelegate;)J

    move v4, v0

    :cond_1
    :goto_1
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_2
    if-nez v4, :cond_3

    .line 51
    iput-boolean v2, p0, Lorg/telegram/messenger/ProxyRotationController;->isCurrentlyChecking:Z

    .line 52
    invoke-direct {p0}, Lorg/telegram/messenger/ProxyRotationController;->switchToAvailable()V

    :cond_3
    return-void
.end method

.method private switchToAvailable()V
    .locals 5

    const/4 v0, 0x0

    .line 62
    iput-boolean v0, p0, Lorg/telegram/messenger/ProxyRotationController;->isCurrentlyChecking:Z

    .line 64
    sget-boolean p0, Lorg/telegram/messenger/SharedConfig;->proxyRotationEnabled:Z

    if-nez p0, :cond_0

    goto :goto_1

    .line 68
    :cond_0
    new-instance p0, Ljava/util/ArrayList;

    sget-object v1, Lorg/telegram/messenger/SharedConfig;->proxyList:Ljava/util/ArrayList;

    invoke-direct {p0, v1}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    .line 69
    new-instance v1, Lorg/telegram/messenger/ProxyRotationController$$ExternalSyntheticLambda1;

    invoke-direct {v1}, Lorg/telegram/messenger/ProxyRotationController$$ExternalSyntheticLambda1;-><init>()V

    invoke-static {p0, v1}, Ljava/util/Collections;->sort(Ljava/util/List;Ljava/util/Comparator;)V

    .line 70
    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result v1

    move v2, v0

    :cond_1
    :goto_0
    if-ge v2, v1, :cond_3

    invoke-virtual {p0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    add-int/lit8 v2, v2, 0x1

    check-cast v3, Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    .line 71
    sget-object v4, Lorg/telegram/messenger/SharedConfig;->currentProxy:Lorg/telegram/messenger/SharedConfig$ProxyInfo;

    if-eq v3, v4, :cond_1

    iget-boolean v4, v3, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->checking:Z

    if-nez v4, :cond_1

    iget-boolean v4, v3, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->available:Z

    if-nez v4, :cond_2

    goto :goto_0

    .line 75
    :cond_2
    invoke-static {}, Lorg/telegram/messenger/MessagesController;->getGlobalMainSettings()Landroid/content/SharedPreferences;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    .line 76
    const-string/jumbo v1, "proxy_enabled"

    const/4 v2, 0x1

    invoke-interface {p0, v1, v2}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 77
    iget-object v1, v3, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-virtual {v1, p0}, Lorg/telegram/utils/proxy/ProxySettings;->toSharedPreferences(Landroid/content/SharedPreferences$Editor;)V

    .line 78
    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 80
    invoke-static {}, Lcom/exteragram/messenger/proxy/ProxyController;->getInstance()Lcom/exteragram/messenger/proxy/ProxyController;

    move-result-object p0

    invoke-virtual {p0, v3}, Lcom/exteragram/messenger/proxy/ProxyController;->setCurrentProxy(Lorg/telegram/messenger/SharedConfig$ProxyInfo;)V

    .line 81
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->proxySettingsChanged:I

    new-array v4, v0, [Ljava/lang/Object;

    invoke-virtual {p0, v1, v4}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    .line 82
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->proxyChangedByRotation:I

    new-array v0, v0, [Ljava/lang/Object;

    invoke-virtual {p0, v1, v0}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    .line 83
    iget-object p0, v3, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    invoke-static {v2, p0}, Lorg/telegram/tgnet/ConnectionsManager;->setProxySettings(ZLorg/telegram/utils/proxy/ProxySettings;)V

    :cond_3
    :goto_1
    return-void
.end method


# virtual methods
.method public varargs didReceivedNotification(II[Ljava/lang/Object;)V
    .locals 2

    .line 98
    sget p3, Lorg/telegram/messenger/NotificationCenter;->proxyCheckDone:I

    const/4 v0, 0x1

    if-ne p1, p3, :cond_1

    .line 99
    invoke-static {}, Lorg/telegram/messenger/SharedConfig;->isProxyEnabled()Z

    move-result p1

    if-eqz p1, :cond_5

    sget-boolean p1, Lorg/telegram/messenger/SharedConfig;->proxyRotationEnabled:Z

    if-eqz p1, :cond_5

    sget-object p1, Lorg/telegram/messenger/SharedConfig;->proxyList:Ljava/util/ArrayList;

    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result p1

    if-le p1, v0, :cond_5

    iget-boolean p1, p0, Lorg/telegram/messenger/ProxyRotationController;->isCurrentlyChecking:Z

    if-nez p1, :cond_0

    goto :goto_0

    .line 103
    :cond_0
    invoke-direct {p0}, Lorg/telegram/messenger/ProxyRotationController;->switchToAvailable()V

    return-void

    .line 104
    :cond_1
    sget p3, Lorg/telegram/messenger/NotificationCenter;->proxySettingsChanged:I

    if-ne p1, p3, :cond_2

    .line 105
    iget-object p0, p0, Lorg/telegram/messenger/ProxyRotationController;->checkProxyAndSwitchRunnable:Ljava/lang/Runnable;

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->cancelRunOnUIThread(Ljava/lang/Runnable;)V

    return-void

    .line 106
    :cond_2
    sget p3, Lorg/telegram/messenger/NotificationCenter;->didUpdateConnectionState:I

    if-ne p1, p3, :cond_5

    sget p1, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    if-ne p2, p1, :cond_5

    .line 107
    invoke-static {}, Lorg/telegram/messenger/SharedConfig;->isProxyEnabled()Z

    move-result p1

    if-eqz p1, :cond_5

    sget-boolean p1, Lorg/telegram/messenger/SharedConfig;->proxyRotationEnabled:Z

    if-eqz p1, :cond_5

    sget-object p1, Lorg/telegram/messenger/SharedConfig;->proxyList:Ljava/util/ArrayList;

    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result p1

    if-gt p1, v0, :cond_3

    goto :goto_0

    .line 111
    :cond_3
    invoke-static {p2}, Lorg/telegram/tgnet/ConnectionsManager;->getInstance(I)Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object p1

    invoke-virtual {p1}, Lorg/telegram/tgnet/ConnectionsManager;->getConnectionState()I

    move-result p1

    const/4 p2, 0x4

    if-ne p1, p2, :cond_4

    .line 114
    iget-boolean p1, p0, Lorg/telegram/messenger/ProxyRotationController;->isCurrentlyChecking:Z

    if-nez p1, :cond_5

    .line 115
    iget-object p0, p0, Lorg/telegram/messenger/ProxyRotationController;->checkProxyAndSwitchRunnable:Ljava/lang/Runnable;

    sget-object p1, Lorg/telegram/messenger/ProxyRotationController;->ROTATION_TIMEOUTS:Ljava/util/List;

    sget p2, Lorg/telegram/messenger/SharedConfig;->proxyRotationTimeout:I

    invoke-interface {p1, p2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    int-to-long p1, p1

    const-wide/16 v0, 0x3e8

    mul-long/2addr p1, v0

    invoke-static {p0, p1, p2}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    return-void

    .line 118
    :cond_4
    iget-object p0, p0, Lorg/telegram/messenger/ProxyRotationController;->checkProxyAndSwitchRunnable:Ljava/lang/Runnable;

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->cancelRunOnUIThread(Ljava/lang/Runnable;)V

    :cond_5
    :goto_0
    return-void
.end method
