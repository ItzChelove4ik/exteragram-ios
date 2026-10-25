.class Landroidx/car/app/Session$LifecycleObserverImpl;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/lifecycle/DefaultLifecycleObserver;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/car/app/Session;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = "LifecycleObserverImpl"
.end annotation


# instance fields
.field final synthetic this$0:Landroidx/car/app/Session;


# direct methods
.method public constructor <init>(Landroidx/car/app/Session;)V
    .locals 0

    .line 247
    iput-object p1, p0, Landroidx/car/app/Session$LifecycleObserverImpl;->this$0:Landroidx/car/app/Session;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onCreate(Landroidx/lifecycle/LifecycleOwner;)V
    .locals 0

    .line 250
    iget-object p0, p0, Landroidx/car/app/Session$LifecycleObserverImpl;->this$0:Landroidx/car/app/Session;

    iget-object p0, p0, Landroidx/car/app/Session;->mRegistryPublic:Landroidx/lifecycle/LifecycleRegistry;

    sget-object p1, Landroidx/lifecycle/Lifecycle$Event;->ON_CREATE:Landroidx/lifecycle/Lifecycle$Event;

    invoke-virtual {p0, p1}, Landroidx/lifecycle/LifecycleRegistry;->handleLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    return-void
.end method

.method public onDestroy(Landroidx/lifecycle/LifecycleOwner;)V
    .locals 2

    .line 275
    iget-object v0, p0, Landroidx/car/app/Session$LifecycleObserverImpl;->this$0:Landroidx/car/app/Session;

    iget-object v0, v0, Landroidx/car/app/Session;->mRegistryPublic:Landroidx/lifecycle/LifecycleRegistry;

    sget-object v1, Landroidx/lifecycle/Lifecycle$Event;->ON_DESTROY:Landroidx/lifecycle/Lifecycle$Event;

    invoke-virtual {v0, v1}, Landroidx/lifecycle/LifecycleRegistry;->handleLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    .line 276
    invoke-interface {p1}, Landroidx/lifecycle/LifecycleOwner;->getLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object p1

    invoke-virtual {p1, p0}, Landroidx/lifecycle/Lifecycle;->removeObserver(Landroidx/lifecycle/LifecycleObserver;)V

    return-void
.end method

.method public onPause(Landroidx/lifecycle/LifecycleOwner;)V
    .locals 0

    .line 265
    iget-object p0, p0, Landroidx/car/app/Session$LifecycleObserverImpl;->this$0:Landroidx/car/app/Session;

    iget-object p0, p0, Landroidx/car/app/Session;->mRegistryPublic:Landroidx/lifecycle/LifecycleRegistry;

    sget-object p1, Landroidx/lifecycle/Lifecycle$Event;->ON_PAUSE:Landroidx/lifecycle/Lifecycle$Event;

    invoke-virtual {p0, p1}, Landroidx/lifecycle/LifecycleRegistry;->handleLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    return-void
.end method

.method public onResume(Landroidx/lifecycle/LifecycleOwner;)V
    .locals 0

    .line 260
    iget-object p0, p0, Landroidx/car/app/Session$LifecycleObserverImpl;->this$0:Landroidx/car/app/Session;

    iget-object p0, p0, Landroidx/car/app/Session;->mRegistryPublic:Landroidx/lifecycle/LifecycleRegistry;

    sget-object p1, Landroidx/lifecycle/Lifecycle$Event;->ON_RESUME:Landroidx/lifecycle/Lifecycle$Event;

    invoke-virtual {p0, p1}, Landroidx/lifecycle/LifecycleRegistry;->handleLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    return-void
.end method

.method public onStart(Landroidx/lifecycle/LifecycleOwner;)V
    .locals 0

    .line 255
    iget-object p0, p0, Landroidx/car/app/Session$LifecycleObserverImpl;->this$0:Landroidx/car/app/Session;

    iget-object p0, p0, Landroidx/car/app/Session;->mRegistryPublic:Landroidx/lifecycle/LifecycleRegistry;

    sget-object p1, Landroidx/lifecycle/Lifecycle$Event;->ON_START:Landroidx/lifecycle/Lifecycle$Event;

    invoke-virtual {p0, p1}, Landroidx/lifecycle/LifecycleRegistry;->handleLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    return-void
.end method

.method public onStop(Landroidx/lifecycle/LifecycleOwner;)V
    .locals 0

    .line 270
    iget-object p0, p0, Landroidx/car/app/Session$LifecycleObserverImpl;->this$0:Landroidx/car/app/Session;

    iget-object p0, p0, Landroidx/car/app/Session;->mRegistryPublic:Landroidx/lifecycle/LifecycleRegistry;

    sget-object p1, Landroidx/lifecycle/Lifecycle$Event;->ON_STOP:Landroidx/lifecycle/Lifecycle$Event;

    invoke-virtual {p0, p1}, Landroidx/lifecycle/LifecycleRegistry;->handleLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    return-void
.end method
