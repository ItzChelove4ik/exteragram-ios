.class public Landroidx/car/app/navigation/NavigationManager;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/car/app/managers/Manager;


# instance fields
.field private final mCarContext:Landroidx/car/app/CarContext;

.field private final mHostDispatcher:Landroidx/car/app/HostDispatcher;

.field private mIsAutoDriveEnabled:Z

.field private mIsNavigating:Z

.field private final mNavigationManager:Landroidx/car/app/navigation/INavigationManager$Stub;

.field private mNavigationManagerCallbackExecutor:Ljava/util/concurrent/Executor;


# direct methods
.method public static synthetic $r8$lambda$fzM06aQJ0HuFwkHHIffvASccIEo(Landroidx/car/app/navigation/NavigationManager;)V
    .locals 0

    .line 0
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    return-void
.end method

.method public constructor <init>(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;Landroidx/lifecycle/Lifecycle;)V
    .locals 0

    .line 331
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 332
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Landroidx/car/app/navigation/NavigationManager;->mCarContext:Landroidx/car/app/CarContext;

    .line 333
    invoke-static {p2}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p2, p0, Landroidx/car/app/navigation/NavigationManager;->mHostDispatcher:Landroidx/car/app/HostDispatcher;

    .line 334
    new-instance p1, Landroidx/car/app/navigation/NavigationManager$1;

    invoke-direct {p1, p0, p3}, Landroidx/car/app/navigation/NavigationManager$1;-><init>(Landroidx/car/app/navigation/NavigationManager;Landroidx/lifecycle/Lifecycle;)V

    iput-object p1, p0, Landroidx/car/app/navigation/NavigationManager;->mNavigationManager:Landroidx/car/app/navigation/INavigationManager$Stub;

    .line 347
    new-instance p1, Landroidx/car/app/navigation/NavigationManager$2;

    invoke-direct {p1, p0, p3}, Landroidx/car/app/navigation/NavigationManager$2;-><init>(Landroidx/car/app/navigation/NavigationManager;Landroidx/lifecycle/Lifecycle;)V

    .line 354
    invoke-virtual {p3, p1}, Landroidx/lifecycle/Lifecycle;->addObserver(Landroidx/lifecycle/LifecycleObserver;)V

    return-void
.end method

.method public static create(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;Landroidx/lifecycle/Lifecycle;)Landroidx/car/app/navigation/NavigationManager;
    .locals 1

    .line 257
    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 258
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 259
    invoke-static {p2}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 261
    new-instance v0, Landroidx/car/app/navigation/NavigationManager;

    invoke-direct {v0, p0, p1, p2}, Landroidx/car/app/navigation/NavigationManager;-><init>(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;Landroidx/lifecycle/Lifecycle;)V

    return-object v0
.end method


# virtual methods
.method public getIInterface()Landroidx/car/app/navigation/INavigationManager$Stub;
    .locals 0

    .line 271
    iget-object p0, p0, Landroidx/car/app/navigation/NavigationManager;->mNavigationManager:Landroidx/car/app/navigation/INavigationManager$Stub;

    return-object p0
.end method

.method public onAutoDriveEnabled()V
    .locals 2

    .line 310
    invoke-static {}, Landroidx/car/app/utils/ThreadUtils;->checkMainThread()V

    const/4 v0, 0x3

    .line 311
    const-string v1, "CarApp.Nav"

    invoke-static {v1, v0}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 312
    const-string v0, "Executing onAutoDriveEnabled"

    invoke-static {v1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    :cond_0
    const/4 v0, 0x1

    .line 315
    iput-boolean v0, p0, Landroidx/car/app/navigation/NavigationManager;->mIsAutoDriveEnabled:Z

    .line 320
    const-string p0, "NavigationManagerCallback not set, skipping onAutoDriveEnabled"

    invoke-static {v1, p0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    return-void
.end method

.method public onStopNavigation()V
    .locals 2

    .line 281
    invoke-static {}, Landroidx/car/app/utils/ThreadUtils;->checkMainThread()V

    .line 282
    iget-boolean v0, p0, Landroidx/car/app/navigation/NavigationManager;->mIsNavigating:Z

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    .line 285
    iput-boolean v0, p0, Landroidx/car/app/navigation/NavigationManager;->mIsNavigating:Z

    .line 287
    iget-object v0, p0, Landroidx/car/app/navigation/NavigationManager;->mNavigationManagerCallbackExecutor:Ljava/util/concurrent/Executor;

    if-nez v0, :cond_1

    :goto_0
    return-void

    .line 291
    :cond_1
    new-instance v1, Landroidx/car/app/navigation/NavigationManager$$ExternalSyntheticLambda0;

    invoke-direct {v1, p0}, Landroidx/car/app/navigation/NavigationManager$$ExternalSyntheticLambda0;-><init>(Landroidx/car/app/navigation/NavigationManager;)V

    invoke-interface {v0, v1}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    return-void
.end method
