.class public abstract Landroidx/car/app/Session;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/lifecycle/LifecycleOwner;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroidx/car/app/Session$LifecycleObserverImpl;
    }
.end annotation


# instance fields
.field private mCarContext:Landroidx/car/app/CarContext;

.field private final mLifecycleObserver:Landroidx/lifecycle/LifecycleObserver;

.field private mRegistry:Landroidx/lifecycle/LifecycleRegistry;

.field final mRegistryPublic:Landroidx/lifecycle/LifecycleRegistry;


# direct methods
.method public constructor <init>()V
    .locals 2

    .line 55
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 53
    new-instance v0, Landroidx/car/app/Session$LifecycleObserverImpl;

    invoke-direct {v0, p0}, Landroidx/car/app/Session$LifecycleObserverImpl;-><init>(Landroidx/car/app/Session;)V

    iput-object v0, p0, Landroidx/car/app/Session;->mLifecycleObserver:Landroidx/lifecycle/LifecycleObserver;

    .line 56
    new-instance v1, Landroidx/lifecycle/LifecycleRegistry;

    invoke-direct {v1, p0}, Landroidx/lifecycle/LifecycleRegistry;-><init>(Landroidx/lifecycle/LifecycleOwner;)V

    iput-object v1, p0, Landroidx/car/app/Session;->mRegistry:Landroidx/lifecycle/LifecycleRegistry;

    .line 57
    new-instance v1, Landroidx/lifecycle/LifecycleRegistry;

    invoke-direct {v1, p0}, Landroidx/lifecycle/LifecycleRegistry;-><init>(Landroidx/lifecycle/LifecycleOwner;)V

    iput-object v1, p0, Landroidx/car/app/Session;->mRegistryPublic:Landroidx/lifecycle/LifecycleRegistry;

    .line 63
    iget-object v1, p0, Landroidx/car/app/Session;->mRegistry:Landroidx/lifecycle/LifecycleRegistry;

    invoke-virtual {v1, v0}, Landroidx/lifecycle/LifecycleRegistry;->addObserver(Landroidx/lifecycle/LifecycleObserver;)V

    .line 64
    iget-object v0, p0, Landroidx/car/app/Session;->mRegistry:Landroidx/lifecycle/LifecycleRegistry;

    invoke-static {v0}, Landroidx/car/app/CarContext;->create(Landroidx/lifecycle/Lifecycle;)Landroidx/car/app/CarContext;

    move-result-object v0

    iput-object v0, p0, Landroidx/car/app/Session;->mCarContext:Landroidx/car/app/CarContext;

    return-void
.end method


# virtual methods
.method public configure(Landroid/content/Context;Landroidx/car/app/HandshakeInfo;Landroidx/car/app/HostInfo;Landroidx/car/app/ICarHost;Landroid/content/res/Configuration;)V
    .locals 1

    .line 231
    iget-object v0, p0, Landroidx/car/app/Session;->mCarContext:Landroidx/car/app/CarContext;

    invoke-virtual {v0, p2}, Landroidx/car/app/CarContext;->updateHandshakeInfo(Landroidx/car/app/HandshakeInfo;)V

    .line 232
    iget-object p2, p0, Landroidx/car/app/Session;->mCarContext:Landroidx/car/app/CarContext;

    invoke-virtual {p2, p3}, Landroidx/car/app/CarContext;->updateHostInfo(Landroidx/car/app/HostInfo;)V

    .line 233
    iget-object p2, p0, Landroidx/car/app/Session;->mCarContext:Landroidx/car/app/CarContext;

    invoke-virtual {p2, p1, p5}, Landroidx/car/app/CarContext;->attachBaseContext(Landroid/content/Context;Landroid/content/res/Configuration;)V

    .line 234
    iget-object p0, p0, Landroidx/car/app/Session;->mCarContext:Landroidx/car/app/CarContext;

    invoke-virtual {p0, p4}, Landroidx/car/app/CarContext;->setCarHost(Landroidx/car/app/ICarHost;)V

    return-void
.end method

.method public final getCarContext()Landroidx/car/app/CarContext;
    .locals 0

    .line 210
    iget-object p0, p0, Landroidx/car/app/Session;->mCarContext:Landroidx/car/app/CarContext;

    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    return-object p0
.end method

.method public getLifecycle()Landroidx/lifecycle/Lifecycle;
    .locals 0

    .line 167
    iget-object p0, p0, Landroidx/car/app/Session;->mRegistryPublic:Landroidx/lifecycle/LifecycleRegistry;

    return-object p0
.end method

.method public handleLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V
    .locals 0

    .line 187
    iget-object p0, p0, Landroidx/car/app/Session;->mRegistry:Landroidx/lifecycle/LifecycleRegistry;

    invoke-virtual {p0, p1}, Landroidx/lifecycle/LifecycleRegistry;->handleLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    return-void
.end method

.method public onCarConfigurationChanged(Landroid/content/res/Configuration;)V
    .locals 0

    return-void
.end method

.method public onCarConfigurationChangedInternal(Landroid/content/res/Configuration;)V
    .locals 1

    .line 242
    iget-object v0, p0, Landroidx/car/app/Session;->mCarContext:Landroidx/car/app/CarContext;

    invoke-virtual {v0, p1}, Landroidx/car/app/CarContext;->onCarConfigurationChanged(Landroid/content/res/Configuration;)V

    .line 243
    iget-object p1, p0, Landroidx/car/app/Session;->mCarContext:Landroidx/car/app/CarContext;

    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    invoke-virtual {p1}, Landroid/content/res/Resources;->getConfiguration()Landroid/content/res/Configuration;

    move-result-object p1

    invoke-virtual {p0, p1}, Landroidx/car/app/Session;->onCarConfigurationChanged(Landroid/content/res/Configuration;)V

    return-void
.end method

.method public abstract onCreateScreen(Landroid/content/Intent;)Landroidx/car/app/Screen;
.end method

.method public onNewIntent(Landroid/content/Intent;)V
    .locals 0

    return-void
.end method
