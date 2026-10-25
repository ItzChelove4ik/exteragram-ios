.class final Landroidx/car/app/CarAppBinder;
.super Landroidx/car/app/ICarApp$Stub;
.source "SourceFile"


# instance fields
.field private mCurrentSession:Landroidx/car/app/Session;

.field private final mCurrentSessionInfo:Landroidx/car/app/SessionInfo;

.field private mHandshakeInfo:Landroidx/car/app/HandshakeInfo;

.field private mHostValidator:Landroidx/car/app/validation/HostValidator;

.field private mService:Landroidx/car/app/CarAppService;


# direct methods
.method public static synthetic $r8$lambda$-NmrdC46ynLALOfGH0FYY9n6M6o(Landroidx/car/app/CarAppBinder;)Ljava/lang/Object;
    .locals 1

    .line 190
    iget-object p0, p0, Landroidx/car/app/CarAppBinder;->mCurrentSession:Landroidx/car/app/Session;

    .line 190
    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    sget-object v0, Landroidx/lifecycle/Lifecycle$Event;->ON_STOP:Landroidx/lifecycle/Lifecycle$Event;

    invoke-virtual {p0, v0}, Landroidx/car/app/Session;->handleLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public static synthetic $r8$lambda$7Uack6mc_KLNEQNXfnJY9SPsiFU(Landroidx/car/app/CarAppBinder;)Ljava/lang/Object;
    .locals 1

    .line 170
    iget-object p0, p0, Landroidx/car/app/CarAppBinder;->mCurrentSession:Landroidx/car/app/Session;

    .line 170
    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    sget-object v0, Landroidx/lifecycle/Lifecycle$Event;->ON_RESUME:Landroidx/lifecycle/Lifecycle$Event;

    invoke-virtual {p0, v0}, Landroidx/car/app/Session;->handleLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public static synthetic $r8$lambda$9yq-tiei5dqpkoINH-KvUxBxuvs(Landroidx/car/app/CarAppBinder;)Ljava/lang/Object;
    .locals 1

    .line 180
    iget-object p0, p0, Landroidx/car/app/CarAppBinder;->mCurrentSession:Landroidx/car/app/Session;

    .line 180
    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    sget-object v0, Landroidx/lifecycle/Lifecycle$Event;->ON_PAUSE:Landroidx/lifecycle/Lifecycle$Event;

    invoke-virtual {p0, v0}, Landroidx/car/app/Session;->handleLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public static synthetic $r8$lambda$IlVDu2UN1ozorozvq1aLAqao2a8(Landroidx/car/app/CarAppBinder;Ljava/lang/String;Landroidx/car/app/IOnDoneCallback;)V
    .locals 2

    .line 225
    iget-object p0, p0, Landroidx/car/app/CarAppBinder;->mCurrentSession:Landroidx/car/app/Session;

    .line 225
    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 226
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    const-string v0, "app"

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    const-string v1, "getManager"

    if-nez v0, :cond_1

    const-string/jumbo v0, "navigation"

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 242
    const-string p0, "%s is not a valid manager"

    invoke-virtual {p1, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    const-string v0, "CarApp"

    invoke-static {v0, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 243
    new-instance p0, Ljava/security/InvalidParameterException;

    const-string v0, " is not a valid manager type"

    invoke-virtual {p1, v0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/security/InvalidParameterException;-><init>(Ljava/lang/String;)V

    invoke-static {p2, v1, p0}, Landroidx/car/app/utils/RemoteUtils;->sendFailureResponseToHost(Landroidx/car/app/IOnDoneCallback;Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void

    .line 238
    :cond_0
    invoke-virtual {p0}, Landroidx/car/app/Session;->getCarContext()Landroidx/car/app/CarContext;

    move-result-object p0

    const-class p1, Landroidx/car/app/navigation/NavigationManager;

    invoke-virtual {p0, p1}, Landroidx/car/app/CarContext;->getCarService(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroidx/car/app/navigation/NavigationManager;

    .line 239
    invoke-virtual {p0}, Landroidx/car/app/navigation/NavigationManager;->getIInterface()Landroidx/car/app/navigation/INavigationManager$Stub;

    move-result-object p0

    .line 235
    invoke-static {p2, v1, p0}, Landroidx/car/app/utils/RemoteUtils;->sendSuccessResponseToHost(Landroidx/car/app/IOnDoneCallback;Ljava/lang/String;Ljava/lang/Object;)V

    return-void

    .line 231
    :cond_1
    invoke-virtual {p0}, Landroidx/car/app/Session;->getCarContext()Landroidx/car/app/CarContext;

    move-result-object p0

    const-class p1, Landroidx/car/app/AppManager;

    invoke-virtual {p0, p1}, Landroidx/car/app/CarContext;->getCarService(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroidx/car/app/AppManager;

    .line 232
    invoke-virtual {p0}, Landroidx/car/app/AppManager;->getIInterface()Landroidx/car/app/IAppManager$Stub;

    move-result-object p0

    .line 228
    invoke-static {p2, v1, p0}, Landroidx/car/app/utils/RemoteUtils;->sendSuccessResponseToHost(Landroidx/car/app/IOnDoneCallback;Ljava/lang/String;Ljava/lang/Object;)V

    return-void
.end method

.method public static synthetic $r8$lambda$PVFAimZMMIOaPhkV65sW3A2TlPU(Landroidx/car/app/CarAppBinder;)Ljava/lang/Object;
    .locals 1

    .line 160
    iget-object p0, p0, Landroidx/car/app/CarAppBinder;->mCurrentSession:Landroidx/car/app/Session;

    .line 160
    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    sget-object v0, Landroidx/lifecycle/Lifecycle$Event;->ON_START:Landroidx/lifecycle/Lifecycle$Event;

    invoke-virtual {p0, v0}, Landroidx/car/app/Session;->handleLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public static synthetic $r8$lambda$XEfwwvAnIshhGblecnxdG4sh6nQ(Landroidx/car/app/CarAppBinder;Landroid/content/res/Configuration;)Ljava/lang/Object;
    .locals 1

    .line 215
    iget-object v0, p0, Landroidx/car/app/CarAppBinder;->mCurrentSession:Landroidx/car/app/Session;

    .line 215
    invoke-static {v0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    invoke-direct {p0, v0, p1}, Landroidx/car/app/CarAppBinder;->onConfigurationChangedInternal(Landroidx/car/app/Session;Landroid/content/res/Configuration;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public static synthetic $r8$lambda$qV15wLbZchZqw7AYDWUTdB04thg(Landroidx/car/app/CarAppBinder;Landroidx/car/app/ICarHost;Landroid/content/res/Configuration;Landroid/content/Intent;)Ljava/lang/Object;
    .locals 6

    .line 111
    iget-object v1, p0, Landroidx/car/app/CarAppBinder;->mService:Landroidx/car/app/CarAppService;

    .line 111
    invoke-static {v1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 112
    iget-object v0, p0, Landroidx/car/app/CarAppBinder;->mCurrentSession:Landroidx/car/app/Session;

    if-eqz v0, :cond_0

    .line 114
    invoke-virtual {v0}, Landroidx/car/app/Session;->getLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object v2

    invoke-virtual {v2}, Landroidx/lifecycle/Lifecycle;->getCurrentState()Landroidx/lifecycle/Lifecycle$State;

    move-result-object v2

    sget-object v3, Landroidx/lifecycle/Lifecycle$State;->DESTROYED:Landroidx/lifecycle/Lifecycle$State;

    if-ne v2, v3, :cond_1

    .line 115
    :cond_0
    iget-object v0, p0, Landroidx/car/app/CarAppBinder;->mCurrentSessionInfo:Landroidx/car/app/SessionInfo;

    invoke-static {v0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    invoke-virtual {v1, v0}, Landroidx/car/app/CarAppService;->onCreateSession(Landroidx/car/app/SessionInfo;)Landroidx/car/app/Session;

    move-result-object v0

    .line 116
    iput-object v0, p0, Landroidx/car/app/CarAppBinder;->mCurrentSession:Landroidx/car/app/Session;

    .line 120
    :cond_1
    invoke-virtual {p0}, Landroidx/car/app/CarAppBinder;->getHandshakeInfo()Landroidx/car/app/HandshakeInfo;

    move-result-object v2

    invoke-static {v2}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 121
    invoke-virtual {v1}, Landroidx/car/app/CarAppService;->getHostInfo()Landroidx/car/app/HostInfo;

    move-result-object v3

    invoke-static {v3}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    move-object v4, p1

    move-object v5, p2

    .line 119
    invoke-virtual/range {v0 .. v5}, Landroidx/car/app/Session;->configure(Landroid/content/Context;Landroidx/car/app/HandshakeInfo;Landroidx/car/app/HostInfo;Landroidx/car/app/ICarHost;Landroid/content/res/Configuration;)V

    .line 127
    invoke-virtual {v0}, Landroidx/car/app/Session;->getLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object p1

    check-cast p1, Landroidx/lifecycle/LifecycleRegistry;

    .line 128
    invoke-virtual {p1}, Landroidx/lifecycle/LifecycleRegistry;->getCurrentState()Landroidx/lifecycle/Lifecycle$State;

    move-result-object p2

    .line 129
    invoke-virtual {v0}, Landroidx/car/app/Session;->getCarContext()Landroidx/car/app/CarContext;

    move-result-object v1

    const-class v2, Landroidx/car/app/ScreenManager;

    invoke-virtual {v1, v2}, Landroidx/car/app/CarContext;->getCarService(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroidx/car/app/ScreenManager;

    .line 130
    invoke-virtual {v1}, Landroidx/car/app/ScreenManager;->getScreenStackInternal()Ljava/util/Deque;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Deque;->size()I

    move-result v1

    .line 131
    sget-object v3, Landroidx/lifecycle/Lifecycle$State;->CREATED:Landroidx/lifecycle/Lifecycle$State;

    invoke-virtual {p2, v3}, Landroidx/lifecycle/Lifecycle$State;->isAtLeast(Landroidx/lifecycle/Lifecycle$State;)Z

    move-result p2

    const/4 v3, 0x3

    const-string v4, "CarApp"

    if-eqz p2, :cond_4

    const/4 p2, 0x1

    if-ge v1, p2, :cond_2

    goto :goto_0

    .line 142
    :cond_2
    invoke-static {v4, v3}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result p1

    if-eqz p1, :cond_3

    .line 143
    const-string/jumbo p1, "onAppCreate the app was already created"

    invoke-static {v4, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 145
    :cond_3
    invoke-direct {p0, v0, p3}, Landroidx/car/app/CarAppBinder;->onNewIntentInternal(Landroidx/car/app/Session;Landroid/content/Intent;)V

    goto :goto_1

    .line 132
    :cond_4
    :goto_0
    invoke-static {v4, v3}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result p0

    if-eqz p0, :cond_5

    .line 133
    new-instance p0, Ljava/lang/StringBuilder;

    const-string/jumbo p2, "onAppCreate the app was not yet created or the screen stack was empty state: "

    invoke-direct {p0, p2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    .line 135
    invoke-virtual {p1}, Landroidx/lifecycle/LifecycleRegistry;->getCurrentState()Landroidx/lifecycle/Lifecycle$State;

    move-result-object p1

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p1, ", stack size: "

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 133
    invoke-static {v4, p0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 138
    :cond_5
    sget-object p0, Landroidx/lifecycle/Lifecycle$Event;->ON_CREATE:Landroidx/lifecycle/Lifecycle$Event;

    invoke-virtual {v0, p0}, Landroidx/car/app/Session;->handleLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    .line 139
    invoke-virtual {v0}, Landroidx/car/app/Session;->getCarContext()Landroidx/car/app/CarContext;

    move-result-object p0

    invoke-virtual {p0, v2}, Landroidx/car/app/CarContext;->getCarService(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroidx/car/app/ScreenManager;

    .line 140
    invoke-virtual {v0, p3}, Landroidx/car/app/Session;->onCreateScreen(Landroid/content/Intent;)Landroidx/car/app/Screen;

    move-result-object p1

    .line 139
    invoke-virtual {p0, p1}, Landroidx/car/app/ScreenManager;->push(Landroidx/car/app/Screen;)V

    :goto_1
    const/4 p0, 0x0

    return-object p0
.end method

.method public static synthetic $r8$lambda$r2-GCoVHE6N83Ffmv4fYzhYrccc(Landroidx/car/app/CarAppBinder;Landroid/content/Intent;)Ljava/lang/Object;
    .locals 1

    .line 202
    iget-object v0, p0, Landroidx/car/app/CarAppBinder;->mCurrentSession:Landroidx/car/app/Session;

    .line 202
    invoke-static {v0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    invoke-direct {p0, v0, p1}, Landroidx/car/app/CarAppBinder;->onNewIntentInternal(Landroidx/car/app/Session;Landroid/content/Intent;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public constructor <init>(Landroidx/car/app/CarAppService;Landroidx/car/app/SessionInfo;)V
    .locals 0

    .line 63
    invoke-direct {p0}, Landroidx/car/app/ICarApp$Stub;-><init>()V

    .line 64
    iput-object p1, p0, Landroidx/car/app/CarAppBinder;->mService:Landroidx/car/app/CarAppService;

    .line 65
    iput-object p2, p0, Landroidx/car/app/CarAppBinder;->mCurrentSessionInfo:Landroidx/car/app/SessionInfo;

    return-void
.end method

.method private getCurrentLifecycle()Landroidx/lifecycle/Lifecycle;
    .locals 0

    .line 312
    iget-object p0, p0, Landroidx/car/app/CarAppBinder;->mCurrentSession:Landroidx/car/app/Session;

    if-nez p0, :cond_0

    const/4 p0, 0x0

    return-object p0

    :cond_0
    invoke-virtual {p0}, Landroidx/car/app/Session;->getLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object p0

    return-object p0
.end method

.method private getHostValidator()Landroidx/car/app/validation/HostValidator;
    .locals 1

    .line 316
    iget-object v0, p0, Landroidx/car/app/CarAppBinder;->mHostValidator:Landroidx/car/app/validation/HostValidator;

    if-nez v0, :cond_0

    .line 317
    iget-object v0, p0, Landroidx/car/app/CarAppBinder;->mService:Landroidx/car/app/CarAppService;

    invoke-static {v0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    invoke-virtual {v0}, Landroidx/car/app/CarAppService;->createHostValidator()Landroidx/car/app/validation/HostValidator;

    move-result-object v0

    iput-object v0, p0, Landroidx/car/app/CarAppBinder;->mHostValidator:Landroidx/car/app/validation/HostValidator;

    .line 319
    :cond_0
    iget-object p0, p0, Landroidx/car/app/CarAppBinder;->mHostValidator:Landroidx/car/app/validation/HostValidator;

    return-object p0
.end method

.method private onConfigurationChangedInternal(Landroidx/car/app/Session;Landroid/content/res/Configuration;)V
    .locals 2

    .line 336
    invoke-static {}, Landroidx/car/app/utils/ThreadUtils;->checkMainThread()V

    const/4 p0, 0x3

    .line 337
    const-string v0, "CarApp"

    invoke-static {v0, p0}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result p0

    if-eqz p0, :cond_0

    .line 338
    new-instance p0, Ljava/lang/StringBuilder;

    const-string/jumbo v1, "onCarConfigurationChanged configuration: "

    invoke-direct {p0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {v0, p0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 341
    :cond_0
    invoke-virtual {p1, p2}, Landroidx/car/app/Session;->onCarConfigurationChangedInternal(Landroid/content/res/Configuration;)V

    return-void
.end method

.method private onNewIntentInternal(Landroidx/car/app/Session;Landroid/content/Intent;)V
    .locals 0

    .line 326
    invoke-static {}, Landroidx/car/app/utils/ThreadUtils;->checkMainThread()V

    .line 327
    invoke-virtual {p1, p2}, Landroidx/car/app/Session;->onNewIntent(Landroid/content/Intent;)V

    return-void
.end method


# virtual methods
.method public destroy()V
    .locals 1

    .line 79
    invoke-virtual {p0}, Landroidx/car/app/CarAppBinder;->onDestroyLifecycle()V

    const/4 v0, 0x0

    .line 80
    iput-object v0, p0, Landroidx/car/app/CarAppBinder;->mCurrentSession:Landroidx/car/app/Session;

    .line 81
    iput-object v0, p0, Landroidx/car/app/CarAppBinder;->mHostValidator:Landroidx/car/app/validation/HostValidator;

    .line 82
    iput-object v0, p0, Landroidx/car/app/CarAppBinder;->mHandshakeInfo:Landroidx/car/app/HandshakeInfo;

    .line 83
    iput-object v0, p0, Landroidx/car/app/CarAppBinder;->mService:Landroidx/car/app/CarAppService;

    return-void
.end method

.method public getAppInfo(Landroidx/car/app/IOnDoneCallback;)V
    .locals 1

    .line 253
    const-string v0, "getAppInfo"

    :try_start_0
    iget-object p0, p0, Landroidx/car/app/CarAppBinder;->mService:Landroidx/car/app/CarAppService;

    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 255
    invoke-virtual {p0}, Landroidx/car/app/CarAppService;->getAppInfo()Landroidx/car/app/AppInfo;

    move-result-object p0

    .line 254
    invoke-static {p1, v0, p0}, Landroidx/car/app/utils/RemoteUtils;->sendSuccessResponseToHost(Landroidx/car/app/IOnDoneCallback;Ljava/lang/String;Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 258
    invoke-static {p1, v0, p0}, Landroidx/car/app/utils/RemoteUtils;->sendFailureResponseToHost(Landroidx/car/app/IOnDoneCallback;Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void
.end method

.method public getCurrentSession()Landroidx/car/app/Session;
    .locals 0

    .line 374
    iget-object p0, p0, Landroidx/car/app/CarAppBinder;->mCurrentSession:Landroidx/car/app/Session;

    return-object p0
.end method

.method public getCurrentSessionInfo()Landroidx/car/app/SessionInfo;
    .locals 0

    .line 379
    iget-object p0, p0, Landroidx/car/app/CarAppBinder;->mCurrentSessionInfo:Landroidx/car/app/SessionInfo;

    return-object p0
.end method

.method public getHandshakeInfo()Landroidx/car/app/HandshakeInfo;
    .locals 0

    .line 369
    iget-object p0, p0, Landroidx/car/app/CarAppBinder;->mHandshakeInfo:Landroidx/car/app/HandshakeInfo;

    return-object p0
.end method

.method public getManager(Ljava/lang/String;Landroidx/car/app/IOnDoneCallback;)V
    .locals 1

    .line 224
    new-instance v0, Landroidx/car/app/CarAppBinder$$ExternalSyntheticLambda5;

    invoke-direct {v0, p0, p1, p2}, Landroidx/car/app/CarAppBinder$$ExternalSyntheticLambda5;-><init>(Landroidx/car/app/CarAppBinder;Ljava/lang/String;Landroidx/car/app/IOnDoneCallback;)V

    invoke-static {v0}, Landroidx/car/app/utils/ThreadUtils;->runOnMain(Ljava/lang/Runnable;)V

    return-void
.end method

.method public onAppCreate(Landroidx/car/app/ICarHost;Landroid/content/Intent;Landroid/content/res/Configuration;Landroidx/car/app/IOnDoneCallback;)V
    .locals 4

    .line 106
    const-string v0, "CarApp"

    const/4 v1, 0x3

    invoke-static {v0, v1}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result v2

    if-eqz v2, :cond_0

    .line 107
    new-instance v2, Ljava/lang/StringBuilder;

    const-string/jumbo v3, "onAppCreate intent: "

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v0, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 110
    :cond_0
    new-instance v2, Landroidx/car/app/CarAppBinder$$ExternalSyntheticLambda4;

    invoke-direct {v2, p0, p1, p3, p2}, Landroidx/car/app/CarAppBinder$$ExternalSyntheticLambda4;-><init>(Landroidx/car/app/CarAppBinder;Landroidx/car/app/ICarHost;Landroid/content/res/Configuration;Landroid/content/Intent;)V

    const-string/jumbo p0, "onAppCreate"

    invoke-static {p4, p0, v2}, Landroidx/car/app/utils/RemoteUtils;->dispatchCallFromHost(Landroidx/car/app/IOnDoneCallback;Ljava/lang/String;Landroidx/car/app/utils/RemoteUtils$HostCall;)V

    .line 150
    invoke-static {v0, v1}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result p0

    if-eqz p0, :cond_1

    .line 151
    const-string/jumbo p0, "onAppCreate completed"

    invoke-static {v0, p0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    :cond_1
    return-void
.end method

.method public onAppPause(Landroidx/car/app/IOnDoneCallback;)V
    .locals 2

    .line 178
    invoke-direct {p0}, Landroidx/car/app/CarAppBinder;->getCurrentLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object v0

    new-instance v1, Landroidx/car/app/CarAppBinder$$ExternalSyntheticLambda1;

    invoke-direct {v1, p0}, Landroidx/car/app/CarAppBinder$$ExternalSyntheticLambda1;-><init>(Landroidx/car/app/CarAppBinder;)V

    .line 177
    const-string/jumbo p0, "onAppPause"

    invoke-static {v0, p1, p0, v1}, Landroidx/car/app/utils/RemoteUtils;->dispatchCallFromHost(Landroidx/lifecycle/Lifecycle;Landroidx/car/app/IOnDoneCallback;Ljava/lang/String;Landroidx/car/app/utils/RemoteUtils$HostCall;)V

    return-void
.end method

.method public onAppResume(Landroidx/car/app/IOnDoneCallback;)V
    .locals 2

    .line 168
    invoke-direct {p0}, Landroidx/car/app/CarAppBinder;->getCurrentLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object v0

    new-instance v1, Landroidx/car/app/CarAppBinder$$ExternalSyntheticLambda7;

    invoke-direct {v1, p0}, Landroidx/car/app/CarAppBinder$$ExternalSyntheticLambda7;-><init>(Landroidx/car/app/CarAppBinder;)V

    .line 167
    const-string/jumbo p0, "onAppResume"

    invoke-static {v0, p1, p0, v1}, Landroidx/car/app/utils/RemoteUtils;->dispatchCallFromHost(Landroidx/lifecycle/Lifecycle;Landroidx/car/app/IOnDoneCallback;Ljava/lang/String;Landroidx/car/app/utils/RemoteUtils$HostCall;)V

    return-void
.end method

.method public onAppStart(Landroidx/car/app/IOnDoneCallback;)V
    .locals 2

    .line 158
    invoke-direct {p0}, Landroidx/car/app/CarAppBinder;->getCurrentLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object v0

    new-instance v1, Landroidx/car/app/CarAppBinder$$ExternalSyntheticLambda2;

    invoke-direct {v1, p0}, Landroidx/car/app/CarAppBinder$$ExternalSyntheticLambda2;-><init>(Landroidx/car/app/CarAppBinder;)V

    .line 157
    const-string/jumbo p0, "onAppStart"

    invoke-static {v0, p1, p0, v1}, Landroidx/car/app/utils/RemoteUtils;->dispatchCallFromHost(Landroidx/lifecycle/Lifecycle;Landroidx/car/app/IOnDoneCallback;Ljava/lang/String;Landroidx/car/app/utils/RemoteUtils$HostCall;)V

    return-void
.end method

.method public onAppStop(Landroidx/car/app/IOnDoneCallback;)V
    .locals 2

    .line 188
    invoke-direct {p0}, Landroidx/car/app/CarAppBinder;->getCurrentLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object v0

    new-instance v1, Landroidx/car/app/CarAppBinder$$ExternalSyntheticLambda3;

    invoke-direct {v1, p0}, Landroidx/car/app/CarAppBinder$$ExternalSyntheticLambda3;-><init>(Landroidx/car/app/CarAppBinder;)V

    .line 187
    const-string/jumbo p0, "onAppStop"

    invoke-static {v0, p1, p0, v1}, Landroidx/car/app/utils/RemoteUtils;->dispatchCallFromHost(Landroidx/lifecycle/Lifecycle;Landroidx/car/app/IOnDoneCallback;Ljava/lang/String;Landroidx/car/app/utils/RemoteUtils$HostCall;)V

    return-void
.end method

.method public onAutoDriveEnabled()V
    .locals 1

    .line 345
    iget-object p0, p0, Landroidx/car/app/CarAppBinder;->mCurrentSession:Landroidx/car/app/Session;

    if-eqz p0, :cond_0

    .line 347
    invoke-virtual {p0}, Landroidx/car/app/Session;->getCarContext()Landroidx/car/app/CarContext;

    move-result-object p0

    const-class v0, Landroidx/car/app/navigation/NavigationManager;

    invoke-virtual {p0, v0}, Landroidx/car/app/CarContext;->getCarService(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroidx/car/app/navigation/NavigationManager;

    .line 348
    invoke-virtual {p0}, Landroidx/car/app/navigation/NavigationManager;->onAutoDriveEnabled()V

    :cond_0
    return-void
.end method

.method public onConfigurationChanged(Landroid/content/res/Configuration;Landroidx/car/app/IOnDoneCallback;)V
    .locals 2

    .line 211
    invoke-direct {p0}, Landroidx/car/app/CarAppBinder;->getCurrentLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object v0

    new-instance v1, Landroidx/car/app/CarAppBinder$$ExternalSyntheticLambda0;

    invoke-direct {v1, p0, p1}, Landroidx/car/app/CarAppBinder$$ExternalSyntheticLambda0;-><init>(Landroidx/car/app/CarAppBinder;Landroid/content/res/Configuration;)V

    .line 210
    const-string/jumbo p0, "onConfigurationChanged"

    invoke-static {v0, p2, p0, v1}, Landroidx/car/app/utils/RemoteUtils;->dispatchCallFromHost(Landroidx/lifecycle/Lifecycle;Landroidx/car/app/IOnDoneCallback;Ljava/lang/String;Landroidx/car/app/utils/RemoteUtils$HostCall;)V

    return-void
.end method

.method public onDestroyLifecycle()V
    .locals 2

    .line 87
    iget-object v0, p0, Landroidx/car/app/CarAppBinder;->mCurrentSession:Landroidx/car/app/Session;

    if-eqz v0, :cond_0

    .line 89
    sget-object v1, Landroidx/lifecycle/Lifecycle$Event;->ON_DESTROY:Landroidx/lifecycle/Lifecycle$Event;

    invoke-virtual {v0, v1}, Landroidx/car/app/Session;->handleLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    :cond_0
    const/4 v0, 0x0

    .line 91
    iput-object v0, p0, Landroidx/car/app/CarAppBinder;->mCurrentSession:Landroidx/car/app/Session;

    return-void
.end method

.method public onHandshakeCompleted(Landroidx/car/app/serialization/Bundleable;Landroidx/car/app/IOnDoneCallback;)V
    .locals 9

    .line 265
    const-string/jumbo v0, "onHandshakeCompleted"

    const-string v1, "Unknown host \'"

    iget-object v2, p0, Landroidx/car/app/CarAppBinder;->mService:Landroidx/car/app/CarAppService;

    invoke-static {v2}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    const/4 v3, 0x0

    .line 268
    :try_start_0
    invoke-virtual {p1}, Landroidx/car/app/serialization/Bundleable;->get()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroidx/car/app/HandshakeInfo;

    .line 269
    invoke-virtual {p1}, Landroidx/car/app/HandshakeInfo;->getHostPackageName()Ljava/lang/String;

    move-result-object v4

    .line 270
    invoke-static {}, Landroid/os/Binder;->getCallingUid()I

    move-result v5

    .line 271
    new-instance v6, Landroidx/car/app/HostInfo;

    invoke-direct {v6, v4, v5}, Landroidx/car/app/HostInfo;-><init>(Ljava/lang/String;I)V

    .line 272
    invoke-direct {p0}, Landroidx/car/app/CarAppBinder;->getHostValidator()Landroidx/car/app/validation/HostValidator;

    move-result-object v7

    invoke-virtual {v7, v6}, Landroidx/car/app/validation/HostValidator;->isValidHost(Landroidx/car/app/HostInfo;)Z

    move-result v7

    if-nez v7, :cond_0

    .line 273
    new-instance p0, Ljava/lang/IllegalArgumentException;

    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "\', uid:"

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    invoke-static {p2, v0, p0}, Landroidx/car/app/utils/RemoteUtils;->sendFailureResponseToHost(Landroidx/car/app/IOnDoneCallback;Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void

    :catch_0
    move-exception p0

    goto :goto_0

    :catch_1
    move-exception p0

    goto :goto_0

    .line 279
    :cond_0
    invoke-virtual {v2}, Landroidx/car/app/CarAppService;->getAppInfo()Landroidx/car/app/AppInfo;

    move-result-object v1

    .line 280
    invoke-virtual {v1}, Landroidx/car/app/AppInfo;->getMinCarAppApiLevel()I

    move-result v4

    .line 281
    invoke-virtual {v1}, Landroidx/car/app/AppInfo;->getLatestCarAppApiLevel()I

    move-result v1

    .line 282
    invoke-virtual {p1}, Landroidx/car/app/HandshakeInfo;->getHostCarAppApiLevel()I

    move-result v5
    :try_end_0
    .catch Landroidx/car/app/serialization/BundlerException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0

    .line 283
    const-string v7, ")"

    const-string v8, "Host API level ("

    if-le v4, v5, :cond_1

    .line 284
    :try_start_1
    new-instance p0, Ljava/lang/IllegalArgumentException;

    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1, v8}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, ") is less than the app\'s min API level ("

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p1, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    invoke-static {p2, v0, p0}, Landroidx/car/app/utils/RemoteUtils;->sendFailureResponseToHost(Landroidx/car/app/IOnDoneCallback;Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void

    :cond_1
    if-ge v1, v5, :cond_2

    .line 292
    new-instance p0, Ljava/lang/IllegalArgumentException;

    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1, v8}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v4, ") is greater than the app\'s max API level ("

    invoke-virtual {p1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p1, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    invoke-static {p2, v0, p0}, Landroidx/car/app/utils/RemoteUtils;->sendFailureResponseToHost(Landroidx/car/app/IOnDoneCallback;Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void

    .line 300
    :cond_2
    invoke-virtual {v2, v6}, Landroidx/car/app/CarAppService;->setHostInfo(Landroidx/car/app/HostInfo;)V

    .line 301
    iput-object p1, p0, Landroidx/car/app/CarAppBinder;->mHandshakeInfo:Landroidx/car/app/HandshakeInfo;

    .line 302
    invoke-static {p2, v0, v3}, Landroidx/car/app/utils/RemoteUtils;->sendSuccessResponseToHost(Landroidx/car/app/IOnDoneCallback;Ljava/lang/String;Ljava/lang/Object;)V
    :try_end_1
    .catch Landroidx/car/app/serialization/BundlerException; {:try_start_1 .. :try_end_1} :catch_1
    .catch Ljava/lang/IllegalArgumentException; {:try_start_1 .. :try_end_1} :catch_0

    return-void

    .line 305
    :goto_0
    invoke-virtual {v2, v3}, Landroidx/car/app/CarAppService;->setHostInfo(Landroidx/car/app/HostInfo;)V

    .line 306
    invoke-static {p2, v0, p0}, Landroidx/car/app/utils/RemoteUtils;->sendFailureResponseToHost(Landroidx/car/app/IOnDoneCallback;Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void
.end method

.method public onNewIntent(Landroid/content/Intent;Landroidx/car/app/IOnDoneCallback;)V
    .locals 2

    .line 198
    invoke-direct {p0}, Landroidx/car/app/CarAppBinder;->getCurrentLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object v0

    new-instance v1, Landroidx/car/app/CarAppBinder$$ExternalSyntheticLambda6;

    invoke-direct {v1, p0, p1}, Landroidx/car/app/CarAppBinder$$ExternalSyntheticLambda6;-><init>(Landroidx/car/app/CarAppBinder;Landroid/content/Intent;)V

    .line 197
    const-string/jumbo p0, "onNewIntent"

    invoke-static {v0, p2, p0, v1}, Landroidx/car/app/utils/RemoteUtils;->dispatchCallFromHost(Landroidx/lifecycle/Lifecycle;Landroidx/car/app/IOnDoneCallback;Ljava/lang/String;Landroidx/car/app/utils/RemoteUtils$HostCall;)V

    return-void
.end method

.method public setHandshakeInfo(Landroidx/car/app/HandshakeInfo;)V
    .locals 2

    .line 358
    invoke-virtual {p1}, Landroidx/car/app/HandshakeInfo;->getHostCarAppApiLevel()I

    move-result v0

    .line 359
    invoke-static {v0}, Landroidx/car/app/versioning/CarAppApiLevels;->isValid(I)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 363
    iput-object p1, p0, Landroidx/car/app/CarAppBinder;->mHandshakeInfo:Landroidx/car/app/HandshakeInfo;

    return-void

    .line 360
    :cond_0
    const-string p0, "Invalid Car App API level received: "

    invoke-static {p0, v0}, Lkotlin/CharCodeKt$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;I)V

    return-void
.end method
