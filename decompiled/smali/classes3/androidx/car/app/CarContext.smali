.class public Landroidx/car/app/CarContext;
.super Landroid/content/ContextWrapper;
.source "SourceFile"


# instance fields
.field private mCarAppApiLevel:I

.field private final mHostDispatcher:Landroidx/car/app/HostDispatcher;

.field private mHostInfo:Landroidx/car/app/HostInfo;

.field private final mLifecycle:Landroidx/lifecycle/Lifecycle;

.field private final mManagers:Landroidx/car/app/managers/ManagerCache;

.field private final mOnBackPressedDispatcher:Landroidx/activity/OnBackPressedDispatcher;


# direct methods
.method public static synthetic $r8$lambda$1IuE5DcaI6ei0ccTfAZBaHqwYWc(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;Landroidx/lifecycle/Lifecycle;)Landroidx/car/app/suggestion/SuggestionManager;
    .locals 0

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 741
    invoke-static {p0, p1, p2}, Landroidx/car/app/suggestion/SuggestionManager;->create(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;Landroidx/lifecycle/Lifecycle;)Landroidx/car/app/suggestion/SuggestionManager;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic $r8$lambda$7CC6CVHCHo4nEigSvYlAvcSVOH4(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;Landroidx/lifecycle/Lifecycle;)Landroidx/car/app/media/MediaPlaybackManager;
    .locals 0

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 743
    invoke-static {p0, p1, p2}, Landroidx/car/app/media/MediaPlaybackManager;->create(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;Landroidx/lifecycle/Lifecycle;)Landroidx/car/app/media/MediaPlaybackManager;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic $r8$lambda$Oge6vCqhd2R3YU4xmfvqs-0fkwo(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;Landroidx/lifecycle/Lifecycle;)Landroidx/car/app/AppManager;
    .locals 0

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 729
    invoke-static {p0, p1, p2}, Landroidx/car/app/AppManager;->create(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;Landroidx/lifecycle/Lifecycle;)Landroidx/car/app/AppManager;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic $r8$lambda$PLFyesAEudIcWeAhSdKCU9rg_lM(Landroidx/car/app/CarContext;)Landroidx/car/app/managers/ResultManager;
    .locals 0

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 739
    invoke-static {p0}, Landroidx/car/app/managers/ResultManager;->create(Landroidx/car/app/CarContext;)Landroidx/car/app/managers/ResultManager;

    const/4 p0, 0x0

    return-object p0
.end method

.method public static synthetic $r8$lambda$SCaUC9o5VLrmY9U6_6ZkDF4fiEA(Landroidx/car/app/CarContext;)V
    .locals 1

    .line 746
    const-class v0, Landroidx/car/app/ScreenManager;

    .line 746
    invoke-virtual {p0, v0}, Landroidx/car/app/CarContext;->getCarService(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p0

    .line 746
    check-cast p0, Landroidx/car/app/ScreenManager;

    invoke-virtual {p0}, Landroidx/car/app/ScreenManager;->pop()V

    return-void
.end method

.method public static synthetic $r8$lambda$VoMvO9zmKiD220_sh3u0fxFT3G0(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;Landroidx/lifecycle/Lifecycle;)Landroidx/car/app/navigation/NavigationManager;
    .locals 0

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 731
    invoke-static {p0, p1, p2}, Landroidx/car/app/navigation/NavigationManager;->create(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;Landroidx/lifecycle/Lifecycle;)Landroidx/car/app/navigation/NavigationManager;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic $r8$lambda$oRslYWvJSzlVhA1P74n6gPzrVNw(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;)Landroidx/car/app/hardware/CarHardwareManager;
    .locals 0

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 737
    invoke-static {p0, p1}, Landroidx/car/app/hardware/CarHardwareManager;->create(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;)Landroidx/car/app/hardware/CarHardwareManager;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic $r8$lambda$rWkxYj29lgUN5flLv3OH1oavxkw(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;)Landroidx/car/app/constraints/ConstraintManager;
    .locals 0

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 735
    invoke-static {p0, p1}, Landroidx/car/app/constraints/ConstraintManager;->create(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;)Landroidx/car/app/constraints/ConstraintManager;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic $r8$lambda$uI7jlReeLpvE9gQT_cKxXLPvj4w(Landroidx/car/app/CarContext;Landroidx/lifecycle/Lifecycle;)Landroidx/car/app/ScreenManager;
    .locals 0

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 733
    invoke-static {p0, p1}, Landroidx/car/app/ScreenManager;->create(Landroidx/car/app/CarContext;Landroidx/lifecycle/Lifecycle;)Landroidx/car/app/ScreenManager;

    move-result-object p0

    return-object p0
.end method

.method public constructor <init>(Landroidx/lifecycle/Lifecycle;Landroidx/car/app/HostDispatcher;)V
    .locals 5

    const/4 v0, 0x0

    .line 725
    invoke-direct {p0, v0}, Landroid/content/ContextWrapper;-><init>(Landroid/content/Context;)V

    .line 182
    new-instance v1, Landroidx/car/app/managers/ManagerCache;

    invoke-direct {v1}, Landroidx/car/app/managers/ManagerCache;-><init>()V

    iput-object v1, p0, Landroidx/car/app/CarContext;->mManagers:Landroidx/car/app/managers/ManagerCache;

    const/4 v2, 0x0

    .line 185
    iput v2, p0, Landroidx/car/app/CarContext;->mCarAppApiLevel:I

    .line 188
    iput-object v0, p0, Landroidx/car/app/CarContext;->mHostInfo:Landroidx/car/app/HostInfo;

    .line 727
    iput-object p2, p0, Landroidx/car/app/CarContext;->mHostDispatcher:Landroidx/car/app/HostDispatcher;

    .line 728
    new-instance v2, Landroidx/car/app/CarContext$$ExternalSyntheticLambda0;

    invoke-direct {v2, p0, p2, p1}, Landroidx/car/app/CarContext$$ExternalSyntheticLambda0;-><init>(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;Landroidx/lifecycle/Lifecycle;)V

    const-class v3, Landroidx/car/app/AppManager;

    const-string v4, "app"

    invoke-virtual {v1, v3, v4, v2}, Landroidx/car/app/managers/ManagerCache;->addFactory(Ljava/lang/Class;Ljava/lang/String;Landroidx/car/app/managers/ManagerFactory;)V

    .line 730
    new-instance v2, Landroidx/car/app/CarContext$$ExternalSyntheticLambda1;

    invoke-direct {v2, p0, p2, p1}, Landroidx/car/app/CarContext$$ExternalSyntheticLambda1;-><init>(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;Landroidx/lifecycle/Lifecycle;)V

    const-class v3, Landroidx/car/app/navigation/NavigationManager;

    const-string/jumbo v4, "navigation"

    invoke-virtual {v1, v3, v4, v2}, Landroidx/car/app/managers/ManagerCache;->addFactory(Ljava/lang/Class;Ljava/lang/String;Landroidx/car/app/managers/ManagerFactory;)V

    .line 732
    new-instance v2, Landroidx/car/app/CarContext$$ExternalSyntheticLambda2;

    invoke-direct {v2, p0, p1}, Landroidx/car/app/CarContext$$ExternalSyntheticLambda2;-><init>(Landroidx/car/app/CarContext;Landroidx/lifecycle/Lifecycle;)V

    const-class v3, Landroidx/car/app/ScreenManager;

    const-string/jumbo v4, "screen"

    invoke-virtual {v1, v3, v4, v2}, Landroidx/car/app/managers/ManagerCache;->addFactory(Ljava/lang/Class;Ljava/lang/String;Landroidx/car/app/managers/ManagerFactory;)V

    .line 734
    new-instance v2, Landroidx/car/app/CarContext$$ExternalSyntheticLambda3;

    invoke-direct {v2, p0, p2}, Landroidx/car/app/CarContext$$ExternalSyntheticLambda3;-><init>(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;)V

    const-class v3, Landroidx/car/app/constraints/ConstraintManager;

    const-string v4, "constraints"

    invoke-virtual {v1, v3, v4, v2}, Landroidx/car/app/managers/ManagerCache;->addFactory(Ljava/lang/Class;Ljava/lang/String;Landroidx/car/app/managers/ManagerFactory;)V

    .line 736
    new-instance v2, Landroidx/car/app/CarContext$$ExternalSyntheticLambda4;

    invoke-direct {v2, p0, p2}, Landroidx/car/app/CarContext$$ExternalSyntheticLambda4;-><init>(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;)V

    const-class v3, Landroidx/car/app/hardware/CarHardwareManager;

    const-string v4, "hardware"

    invoke-virtual {v1, v3, v4, v2}, Landroidx/car/app/managers/ManagerCache;->addFactory(Ljava/lang/Class;Ljava/lang/String;Landroidx/car/app/managers/ManagerFactory;)V

    .line 738
    new-instance v2, Landroidx/car/app/CarContext$$ExternalSyntheticLambda5;

    invoke-direct {v2, p0}, Landroidx/car/app/CarContext$$ExternalSyntheticLambda5;-><init>(Landroidx/car/app/CarContext;)V

    const-class v3, Landroidx/car/app/managers/ResultManager;

    invoke-virtual {v1, v3, v0, v2}, Landroidx/car/app/managers/ManagerCache;->addFactory(Ljava/lang/Class;Ljava/lang/String;Landroidx/car/app/managers/ManagerFactory;)V

    .line 740
    new-instance v0, Landroidx/car/app/CarContext$$ExternalSyntheticLambda6;

    invoke-direct {v0, p0, p2, p1}, Landroidx/car/app/CarContext$$ExternalSyntheticLambda6;-><init>(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;Landroidx/lifecycle/Lifecycle;)V

    const-class v2, Landroidx/car/app/suggestion/SuggestionManager;

    const-string/jumbo v3, "suggestion"

    invoke-virtual {v1, v2, v3, v0}, Landroidx/car/app/managers/ManagerCache;->addFactory(Ljava/lang/Class;Ljava/lang/String;Landroidx/car/app/managers/ManagerFactory;)V

    .line 742
    new-instance v0, Landroidx/car/app/CarContext$$ExternalSyntheticLambda7;

    invoke-direct {v0, p0, p2, p1}, Landroidx/car/app/CarContext$$ExternalSyntheticLambda7;-><init>(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;Landroidx/lifecycle/Lifecycle;)V

    const-class v2, Landroidx/car/app/media/MediaPlaybackManager;

    const-string v3, "media_playback"

    invoke-virtual {v1, v2, v3, v0}, Landroidx/car/app/managers/ManagerCache;->addFactory(Ljava/lang/Class;Ljava/lang/String;Landroidx/car/app/managers/ManagerFactory;)V

    .line 745
    new-instance v0, Landroidx/activity/OnBackPressedDispatcher;

    new-instance v1, Landroidx/car/app/CarContext$$ExternalSyntheticLambda8;

    invoke-direct {v1, p0}, Landroidx/car/app/CarContext$$ExternalSyntheticLambda8;-><init>(Landroidx/car/app/CarContext;)V

    invoke-direct {v0, v1}, Landroidx/activity/OnBackPressedDispatcher;-><init>(Ljava/lang/Runnable;)V

    iput-object v0, p0, Landroidx/car/app/CarContext;->mOnBackPressedDispatcher:Landroidx/activity/OnBackPressedDispatcher;

    .line 747
    iput-object p1, p0, Landroidx/car/app/CarContext;->mLifecycle:Landroidx/lifecycle/Lifecycle;

    .line 749
    new-instance v0, Landroidx/car/app/CarContext$2;

    invoke-direct {v0, p0, p2}, Landroidx/car/app/CarContext$2;-><init>(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;)V

    .line 757
    invoke-virtual {p1, v0}, Landroidx/lifecycle/Lifecycle;->addObserver(Landroidx/lifecycle/LifecycleObserver;)V

    return-void
.end method

.method public static create(Landroidx/lifecycle/Lifecycle;)Landroidx/car/app/CarContext;
    .locals 2

    .line 194
    new-instance v0, Landroidx/car/app/CarContext;

    new-instance v1, Landroidx/car/app/HostDispatcher;

    invoke-direct {v1}, Landroidx/car/app/HostDispatcher;-><init>()V

    invoke-direct {v0, p0, v1}, Landroidx/car/app/CarContext;-><init>(Landroidx/lifecycle/Lifecycle;Landroidx/car/app/HostDispatcher;)V

    return-object v0
.end method


# virtual methods
.method public attachBaseContext(Landroid/content/Context;Landroid/content/res/Configuration;)V
    .locals 8

    .line 686
    invoke-static {}, Landroidx/car/app/utils/ThreadUtils;->checkMainThread()V

    .line 690
    invoke-virtual {p0}, Landroid/content/ContextWrapper;->getBaseContext()Landroid/content/Context;

    move-result-object v0

    if-nez v0, :cond_0

    .line 692
    const-string v0, "display"

    .line 694
    invoke-virtual {p1, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    .line 693
    invoke-static {v0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    move-object v1, v0

    check-cast v1, Landroid/hardware/display/DisplayManager;

    iget v3, p2, Landroid/content/res/Configuration;->screenWidthDp:I

    iget v4, p2, Landroid/content/res/Configuration;->screenHeightDp:I

    iget v5, p2, Landroid/content/res/Configuration;->densityDpi:I

    const/4 v6, 0x0

    const/16 v7, 0x8

    .line 695
    const-string v2, "CarAppService"

    invoke-virtual/range {v1 .. v7}, Landroid/hardware/display/DisplayManager;->createVirtualDisplay(Ljava/lang/String;IIILandroid/view/Surface;I)Landroid/hardware/display/VirtualDisplay;

    move-result-object v0

    .line 705
    invoke-virtual {v0}, Landroid/hardware/display/VirtualDisplay;->getDisplay()Landroid/view/Display;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/content/Context;->createDisplayContext(Landroid/view/Display;)Landroid/content/Context;

    move-result-object p1

    .line 706
    invoke-virtual {p1, p2}, Landroid/content/Context;->createConfigurationContext(Landroid/content/res/Configuration;)Landroid/content/Context;

    move-result-object p1

    .line 703
    invoke-virtual {p0, p1}, Landroid/content/ContextWrapper;->attachBaseContext(Landroid/content/Context;)V

    .line 709
    :cond_0
    invoke-virtual {p0, p2}, Landroidx/car/app/CarContext;->onCarConfigurationChanged(Landroid/content/res/Configuration;)V

    return-void
.end method

.method public getCarAppApiLevel()I
    .locals 0

    .line 500
    iget p0, p0, Landroidx/car/app/CarContext;->mCarAppApiLevel:I

    if-eqz p0, :cond_0

    return p0

    .line 501
    :cond_0
    const-string p0, "Car App API level hasn\'t been established yet"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return p0
.end method

.method public getCarService(Ljava/lang/Class;)Ljava/lang/Object;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Ljava/lang/Class<",
            "TT;>;)TT;"
        }
    .end annotation

    .line 255
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 256
    iget-object p0, p0, Landroidx/car/app/CarContext;->mManagers:Landroidx/car/app/managers/ManagerCache;

    invoke-virtual {p0, p1}, Landroidx/car/app/managers/ManagerCache;->getOrCreate(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public getOnBackPressedDispatcher()Landroidx/activity/OnBackPressedDispatcher;
    .locals 0

    .line 467
    iget-object p0, p0, Landroidx/car/app/CarContext;->mOnBackPressedDispatcher:Landroidx/activity/OnBackPressedDispatcher;

    return-object p0
.end method

.method public onCarConfigurationChanged(Landroid/content/res/Configuration;)V
    .locals 3

    .line 639
    invoke-static {}, Landroidx/car/app/utils/ThreadUtils;->checkMainThread()V

    const/4 v0, 0x3

    .line 641
    const-string v1, "CarApp"

    invoke-static {v1, v0}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 642
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v2, "Car configuration changed, configuration: "

    invoke-direct {v0, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v2, ", displayMetrics: "

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 645
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    invoke-virtual {v2}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 642
    invoke-static {v1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 648
    :cond_0
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    .line 649
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 650
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    invoke-virtual {p0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object p0

    .line 649
    invoke-virtual {v0, p1, p0}, Landroid/content/res/Resources;->updateConfiguration(Landroid/content/res/Configuration;Landroid/util/DisplayMetrics;)V

    return-void
.end method

.method public setCarHost(Landroidx/car/app/ICarHost;)V
    .locals 0

    .line 626
    invoke-static {}, Landroidx/car/app/utils/ThreadUtils;->checkMainThread()V

    .line 627
    iget-object p0, p0, Landroidx/car/app/CarContext;->mHostDispatcher:Landroidx/car/app/HostDispatcher;

    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    check-cast p1, Landroidx/car/app/ICarHost;

    invoke-virtual {p0, p1}, Landroidx/car/app/HostDispatcher;->setCarHost(Landroidx/car/app/ICarHost;)V

    return-void
.end method

.method public updateHandshakeInfo(Landroidx/car/app/HandshakeInfo;)V
    .locals 0

    .line 659
    invoke-virtual {p1}, Landroidx/car/app/HandshakeInfo;->getHostCarAppApiLevel()I

    move-result p1

    iput p1, p0, Landroidx/car/app/CarContext;->mCarAppApiLevel:I

    return-void
.end method

.method public updateHostInfo(Landroidx/car/app/HostInfo;)V
    .locals 0

    .line 669
    iput-object p1, p0, Landroidx/car/app/CarContext;->mHostInfo:Landroidx/car/app/HostInfo;

    return-void
.end method
