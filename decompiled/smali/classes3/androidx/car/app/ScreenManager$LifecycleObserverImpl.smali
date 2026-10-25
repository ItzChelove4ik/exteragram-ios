.class Landroidx/car/app/ScreenManager$LifecycleObserverImpl;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/lifecycle/DefaultLifecycleObserver;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/car/app/ScreenManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = "LifecycleObserverImpl"
.end annotation


# instance fields
.field final synthetic this$0:Landroidx/car/app/ScreenManager;


# direct methods
.method public constructor <init>(Landroidx/car/app/ScreenManager;)V
    .locals 0

    .line 438
    iput-object p1, p0, Landroidx/car/app/ScreenManager$LifecycleObserverImpl;->this$0:Landroidx/car/app/ScreenManager;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onCreate(Landroidx/lifecycle/LifecycleOwner;)V
    .locals 0

    return-void
.end method

.method public onDestroy(Landroidx/lifecycle/LifecycleOwner;)V
    .locals 1

    .line 485
    iget-object v0, p0, Landroidx/car/app/ScreenManager$LifecycleObserverImpl;->this$0:Landroidx/car/app/ScreenManager;

    invoke-virtual {v0}, Landroidx/car/app/ScreenManager;->destroyAndClearScreenStack()V

    .line 486
    invoke-interface {p1}, Landroidx/lifecycle/LifecycleOwner;->getLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object p1

    invoke-virtual {p1, p0}, Landroidx/lifecycle/Lifecycle;->removeObserver(Landroidx/lifecycle/LifecycleObserver;)V

    return-void
.end method

.method public onPause(Landroidx/lifecycle/LifecycleOwner;)V
    .locals 0

    .line 465
    iget-object p0, p0, Landroidx/car/app/ScreenManager$LifecycleObserverImpl;->this$0:Landroidx/car/app/ScreenManager;

    invoke-virtual {p0}, Landroidx/car/app/ScreenManager;->getScreenStackInternal()Ljava/util/Deque;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/Deque;->peek()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroidx/car/app/Screen;

    if-nez p0, :cond_0

    .line 467
    const-string p0, "CarApp"

    const-string p1, "Screen stack was empty during lifecycle onPause"

    invoke-static {p0, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    return-void

    .line 470
    :cond_0
    sget-object p1, Landroidx/lifecycle/Lifecycle$Event;->ON_PAUSE:Landroidx/lifecycle/Lifecycle$Event;

    invoke-virtual {p0, p1}, Landroidx/car/app/Screen;->dispatchLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    return-void
.end method

.method public onResume(Landroidx/lifecycle/LifecycleOwner;)V
    .locals 0

    .line 455
    iget-object p0, p0, Landroidx/car/app/ScreenManager$LifecycleObserverImpl;->this$0:Landroidx/car/app/ScreenManager;

    invoke-virtual {p0}, Landroidx/car/app/ScreenManager;->getScreenStackInternal()Ljava/util/Deque;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/Deque;->peek()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroidx/car/app/Screen;

    if-nez p0, :cond_0

    .line 457
    const-string p0, "CarApp"

    const-string p1, "Screen stack was empty during lifecycle onResume"

    invoke-static {p0, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    return-void

    .line 460
    :cond_0
    sget-object p1, Landroidx/lifecycle/Lifecycle$Event;->ON_RESUME:Landroidx/lifecycle/Lifecycle$Event;

    invoke-virtual {p0, p1}, Landroidx/car/app/Screen;->dispatchLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    return-void
.end method

.method public onStart(Landroidx/lifecycle/LifecycleOwner;)V
    .locals 0

    .line 445
    iget-object p0, p0, Landroidx/car/app/ScreenManager$LifecycleObserverImpl;->this$0:Landroidx/car/app/ScreenManager;

    invoke-virtual {p0}, Landroidx/car/app/ScreenManager;->getScreenStackInternal()Ljava/util/Deque;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/Deque;->peek()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroidx/car/app/Screen;

    if-nez p0, :cond_0

    .line 447
    const-string p0, "CarApp"

    const-string p1, "Screen stack was empty during lifecycle onStart"

    invoke-static {p0, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    return-void

    .line 450
    :cond_0
    sget-object p1, Landroidx/lifecycle/Lifecycle$Event;->ON_START:Landroidx/lifecycle/Lifecycle$Event;

    invoke-virtual {p0, p1}, Landroidx/car/app/Screen;->dispatchLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    return-void
.end method

.method public onStop(Landroidx/lifecycle/LifecycleOwner;)V
    .locals 0

    .line 475
    iget-object p0, p0, Landroidx/car/app/ScreenManager$LifecycleObserverImpl;->this$0:Landroidx/car/app/ScreenManager;

    invoke-virtual {p0}, Landroidx/car/app/ScreenManager;->getScreenStackInternal()Ljava/util/Deque;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/Deque;->peek()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroidx/car/app/Screen;

    if-nez p0, :cond_0

    .line 477
    const-string p0, "CarApp"

    const-string p1, "Screen stack was empty during lifecycle onStop"

    invoke-static {p0, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    return-void

    .line 480
    :cond_0
    sget-object p1, Landroidx/lifecycle/Lifecycle$Event;->ON_STOP:Landroidx/lifecycle/Lifecycle$Event;

    invoke-virtual {p0, p1}, Landroidx/car/app/Screen;->dispatchLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    return-void
.end method
