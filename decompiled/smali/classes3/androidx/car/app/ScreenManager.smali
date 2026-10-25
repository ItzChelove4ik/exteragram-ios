.class public Landroidx/car/app/ScreenManager;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/car/app/managers/Manager;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroidx/car/app/ScreenManager$LifecycleObserverImpl;
    }
.end annotation


# instance fields
.field private final mAppLifecycle:Landroidx/lifecycle/Lifecycle;

.field private final mCarContext:Landroidx/car/app/CarContext;

.field private final mScreenStack:Ljava/util/Deque;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Deque<",
            "Landroidx/car/app/Screen;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Landroidx/car/app/CarContext;Landroidx/lifecycle/Lifecycle;)V
    .locals 1

    .line 431
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 52
    new-instance v0, Ljava/util/ArrayDeque;

    invoke-direct {v0}, Ljava/util/ArrayDeque;-><init>()V

    iput-object v0, p0, Landroidx/car/app/ScreenManager;->mScreenStack:Ljava/util/Deque;

    .line 432
    iput-object p1, p0, Landroidx/car/app/ScreenManager;->mCarContext:Landroidx/car/app/CarContext;

    .line 433
    iput-object p2, p0, Landroidx/car/app/ScreenManager;->mAppLifecycle:Landroidx/lifecycle/Lifecycle;

    .line 434
    new-instance p1, Landroidx/car/app/ScreenManager$LifecycleObserverImpl;

    invoke-direct {p1, p0}, Landroidx/car/app/ScreenManager$LifecycleObserverImpl;-><init>(Landroidx/car/app/ScreenManager;)V

    invoke-virtual {p2, p1}, Landroidx/lifecycle/Lifecycle;->addObserver(Landroidx/lifecycle/LifecycleObserver;)V

    return-void
.end method

.method public static create(Landroidx/car/app/CarContext;Landroidx/lifecycle/Lifecycle;)Landroidx/car/app/ScreenManager;
    .locals 1

    .line 267
    new-instance v0, Landroidx/car/app/ScreenManager;

    invoke-direct {v0, p0, p1}, Landroidx/car/app/ScreenManager;-><init>(Landroidx/car/app/CarContext;Landroidx/lifecycle/Lifecycle;)V

    return-object v0
.end method

.method private moveToTop(Landroidx/car/app/Screen;)V
    .locals 2

    .line 414
    iget-object v0, p0, Landroidx/car/app/ScreenManager;->mScreenStack:Ljava/util/Deque;

    invoke-interface {v0}, Ljava/util/Deque;->peek()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroidx/car/app/Screen;

    if-eqz v0, :cond_1

    if-ne v0, p1, :cond_0

    goto :goto_0

    .line 420
    :cond_0
    iget-object v1, p0, Landroidx/car/app/ScreenManager;->mScreenStack:Ljava/util/Deque;

    invoke-interface {v1, p1}, Ljava/util/Deque;->remove(Ljava/lang/Object;)Z

    const/4 v1, 0x0

    .line 422
    invoke-direct {p0, p1, v1}, Landroidx/car/app/ScreenManager;->pushAndStart(Landroidx/car/app/Screen;Z)V

    .line 423
    invoke-direct {p0, v0, v1}, Landroidx/car/app/ScreenManager;->stop(Landroidx/car/app/Screen;Z)V

    .line 425
    iget-object p0, p0, Landroidx/car/app/ScreenManager;->mAppLifecycle:Landroidx/lifecycle/Lifecycle;

    invoke-virtual {p0}, Landroidx/lifecycle/Lifecycle;->getCurrentState()Landroidx/lifecycle/Lifecycle$State;

    move-result-object p0

    sget-object v0, Landroidx/lifecycle/Lifecycle$State;->RESUMED:Landroidx/lifecycle/Lifecycle$State;

    invoke-virtual {p0, v0}, Landroidx/lifecycle/Lifecycle$State;->isAtLeast(Landroidx/lifecycle/Lifecycle$State;)Z

    move-result p0

    if-eqz p0, :cond_1

    .line 426
    sget-object p0, Landroidx/lifecycle/Lifecycle$Event;->ON_RESUME:Landroidx/lifecycle/Lifecycle$Event;

    invoke-virtual {p1, p0}, Landroidx/car/app/Screen;->dispatchLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    :cond_1
    :goto_0
    return-void
.end method

.method private popInternal(Ljava/util/List;)V
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Landroidx/car/app/Screen;",
            ">;)V"
        }
    .end annotation

    .line 344
    invoke-virtual {p0}, Landroidx/car/app/ScreenManager;->getTop()Landroidx/car/app/Screen;

    move-result-object v0

    const/4 v1, 0x1

    .line 349
    invoke-virtual {v0, v1}, Landroidx/car/app/Screen;->setUseLastTemplateId(Z)V

    .line 353
    iget-object v2, p0, Landroidx/car/app/ScreenManager;->mCarContext:Landroidx/car/app/CarContext;

    const-class v3, Landroidx/car/app/AppManager;

    invoke-virtual {v2, v3}, Landroidx/car/app/CarContext;->getCarService(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroidx/car/app/AppManager;

    invoke-virtual {v2}, Landroidx/car/app/AppManager;->invalidate()V

    .line 355
    iget-object v2, p0, Landroidx/car/app/ScreenManager;->mAppLifecycle:Landroidx/lifecycle/Lifecycle;

    invoke-virtual {v2}, Landroidx/lifecycle/Lifecycle;->getCurrentState()Landroidx/lifecycle/Lifecycle$State;

    move-result-object v2

    sget-object v3, Landroidx/lifecycle/Lifecycle$State;->STARTED:Landroidx/lifecycle/Lifecycle$State;

    invoke-virtual {v2, v3}, Landroidx/lifecycle/Lifecycle$State;->isAtLeast(Landroidx/lifecycle/Lifecycle$State;)Z

    move-result v2

    if-eqz v2, :cond_0

    .line 356
    sget-object v2, Landroidx/lifecycle/Lifecycle$Event;->ON_START:Landroidx/lifecycle/Lifecycle$Event;

    invoke-virtual {v0, v2}, Landroidx/car/app/Screen;->dispatchLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    .line 360
    :cond_0
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    const/4 v3, 0x3

    const-string v4, "CarApp"

    if-eqz v2, :cond_2

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroidx/car/app/Screen;

    .line 361
    invoke-static {v4, v3}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result v3

    if-eqz v3, :cond_1

    .line 362
    new-instance v3, Ljava/lang/StringBuilder;

    const-string v5, "Popping screen "

    invoke-direct {v3, v5}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v5, " off the screen stack"

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v4, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 364
    :cond_1
    invoke-direct {p0, v2, v1}, Landroidx/car/app/ScreenManager;->stop(Landroidx/car/app/Screen;Z)V

    goto :goto_0

    .line 367
    :cond_2
    invoke-static {v4, v3}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result p1

    if-eqz p1, :cond_3

    .line 368
    new-instance p1, Ljava/lang/StringBuilder;

    const-string v1, "Screen "

    invoke-direct {p1, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v1, " is at the top of the screen stack"

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {v4, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 370
    :cond_3
    iget-object p1, p0, Landroidx/car/app/ScreenManager;->mAppLifecycle:Landroidx/lifecycle/Lifecycle;

    invoke-virtual {p1}, Landroidx/lifecycle/Lifecycle;->getCurrentState()Landroidx/lifecycle/Lifecycle$State;

    move-result-object p1

    sget-object v1, Landroidx/lifecycle/Lifecycle$State;->RESUMED:Landroidx/lifecycle/Lifecycle$State;

    invoke-virtual {p1, v1}, Landroidx/lifecycle/Lifecycle$State;->isAtLeast(Landroidx/lifecycle/Lifecycle$State;)Z

    move-result p1

    if-eqz p1, :cond_4

    .line 371
    iget-object p0, p0, Landroidx/car/app/ScreenManager;->mScreenStack:Ljava/util/Deque;

    invoke-interface {p0, v0}, Ljava/util/Deque;->contains(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_4

    .line 375
    sget-object p0, Landroidx/lifecycle/Lifecycle$Event;->ON_RESUME:Landroidx/lifecycle/Lifecycle$Event;

    invoke-virtual {v0, p0}, Landroidx/car/app/Screen;->dispatchLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    :cond_4
    return-void
.end method

.method private pushAndStart(Landroidx/car/app/Screen;Z)V
    .locals 1

    .line 381
    iget-object v0, p0, Landroidx/car/app/ScreenManager;->mScreenStack:Ljava/util/Deque;

    invoke-interface {v0, p1}, Ljava/util/Deque;->push(Ljava/lang/Object;)V

    if-eqz p2, :cond_0

    .line 382
    iget-object p2, p0, Landroidx/car/app/ScreenManager;->mAppLifecycle:Landroidx/lifecycle/Lifecycle;

    invoke-virtual {p2}, Landroidx/lifecycle/Lifecycle;->getCurrentState()Landroidx/lifecycle/Lifecycle$State;

    move-result-object p2

    sget-object v0, Landroidx/lifecycle/Lifecycle$State;->CREATED:Landroidx/lifecycle/Lifecycle$State;

    invoke-virtual {p2, v0}, Landroidx/lifecycle/Lifecycle$State;->isAtLeast(Landroidx/lifecycle/Lifecycle$State;)Z

    move-result p2

    if-eqz p2, :cond_0

    .line 383
    sget-object p2, Landroidx/lifecycle/Lifecycle$Event;->ON_CREATE:Landroidx/lifecycle/Lifecycle$Event;

    invoke-virtual {p1, p2}, Landroidx/car/app/Screen;->dispatchLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    .line 386
    :cond_0
    invoke-virtual {p1}, Landroidx/car/app/Screen;->getLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object p2

    invoke-virtual {p2}, Landroidx/lifecycle/Lifecycle;->getCurrentState()Landroidx/lifecycle/Lifecycle$State;

    move-result-object p2

    sget-object v0, Landroidx/lifecycle/Lifecycle$State;->CREATED:Landroidx/lifecycle/Lifecycle$State;

    invoke-virtual {p2, v0}, Landroidx/lifecycle/Lifecycle$State;->isAtLeast(Landroidx/lifecycle/Lifecycle$State;)Z

    move-result p2

    if-nez p2, :cond_1

    goto :goto_0

    .line 391
    :cond_1
    iget-object p2, p0, Landroidx/car/app/ScreenManager;->mAppLifecycle:Landroidx/lifecycle/Lifecycle;

    invoke-virtual {p2}, Landroidx/lifecycle/Lifecycle;->getCurrentState()Landroidx/lifecycle/Lifecycle$State;

    move-result-object p2

    sget-object v0, Landroidx/lifecycle/Lifecycle$State;->STARTED:Landroidx/lifecycle/Lifecycle$State;

    invoke-virtual {p2, v0}, Landroidx/lifecycle/Lifecycle$State;->isAtLeast(Landroidx/lifecycle/Lifecycle$State;)Z

    move-result p2

    if-eqz p2, :cond_2

    .line 392
    iget-object p0, p0, Landroidx/car/app/ScreenManager;->mCarContext:Landroidx/car/app/CarContext;

    const-class p2, Landroidx/car/app/AppManager;

    invoke-virtual {p0, p2}, Landroidx/car/app/CarContext;->getCarService(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroidx/car/app/AppManager;

    invoke-virtual {p0}, Landroidx/car/app/AppManager;->invalidate()V

    .line 393
    sget-object p0, Landroidx/lifecycle/Lifecycle$Event;->ON_START:Landroidx/lifecycle/Lifecycle$Event;

    invoke-virtual {p1, p0}, Landroidx/car/app/Screen;->dispatchLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    :cond_2
    :goto_0
    return-void
.end method

.method private pushInternal(Landroidx/car/app/Screen;)V
    .locals 3

    const/4 v0, 0x3

    .line 316
    const-string v1, "CarApp"

    invoke-static {v1, v0}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 317
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v2, "Pushing screen "

    invoke-direct {v0, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v2, " to the top of the screen stack"

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 320
    :cond_0
    iget-object v0, p0, Landroidx/car/app/ScreenManager;->mScreenStack:Ljava/util/Deque;

    invoke-interface {v0, p1}, Ljava/util/Deque;->contains(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 321
    invoke-direct {p0, p1}, Landroidx/car/app/ScreenManager;->moveToTop(Landroidx/car/app/Screen;)V

    return-void

    .line 325
    :cond_1
    iget-object v0, p0, Landroidx/car/app/ScreenManager;->mScreenStack:Ljava/util/Deque;

    invoke-interface {v0}, Ljava/util/Deque;->peek()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroidx/car/app/Screen;

    const/4 v1, 0x1

    .line 327
    invoke-direct {p0, p1, v1}, Landroidx/car/app/ScreenManager;->pushAndStart(Landroidx/car/app/Screen;Z)V

    .line 329
    iget-object v1, p0, Landroidx/car/app/ScreenManager;->mScreenStack:Ljava/util/Deque;

    invoke-interface {v1, p1}, Ljava/util/Deque;->contains(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_2

    goto :goto_0

    :cond_2
    if-eqz v0, :cond_3

    const/4 v1, 0x0

    .line 335
    invoke-direct {p0, v0, v1}, Landroidx/car/app/ScreenManager;->stop(Landroidx/car/app/Screen;Z)V

    .line 338
    :cond_3
    iget-object p0, p0, Landroidx/car/app/ScreenManager;->mAppLifecycle:Landroidx/lifecycle/Lifecycle;

    invoke-virtual {p0}, Landroidx/lifecycle/Lifecycle;->getCurrentState()Landroidx/lifecycle/Lifecycle$State;

    move-result-object p0

    sget-object v0, Landroidx/lifecycle/Lifecycle$State;->RESUMED:Landroidx/lifecycle/Lifecycle$State;

    invoke-virtual {p0, v0}, Landroidx/lifecycle/Lifecycle$State;->isAtLeast(Landroidx/lifecycle/Lifecycle$State;)Z

    move-result p0

    if-eqz p0, :cond_4

    .line 339
    sget-object p0, Landroidx/lifecycle/Lifecycle$Event;->ON_RESUME:Landroidx/lifecycle/Lifecycle$Event;

    invoke-virtual {p1, p0}, Landroidx/car/app/Screen;->dispatchLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    :cond_4
    :goto_0
    return-void
.end method

.method private stop(Landroidx/car/app/Screen;Z)V
    .locals 1

    .line 398
    invoke-virtual {p1}, Landroidx/car/app/Screen;->getLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object p0

    invoke-virtual {p0}, Landroidx/lifecycle/Lifecycle;->getCurrentState()Landroidx/lifecycle/Lifecycle$State;

    move-result-object p0

    .line 400
    sget-object v0, Landroidx/lifecycle/Lifecycle$State;->RESUMED:Landroidx/lifecycle/Lifecycle$State;

    invoke-virtual {p0, v0}, Landroidx/lifecycle/Lifecycle$State;->isAtLeast(Landroidx/lifecycle/Lifecycle$State;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 401
    sget-object v0, Landroidx/lifecycle/Lifecycle$Event;->ON_PAUSE:Landroidx/lifecycle/Lifecycle$Event;

    invoke-virtual {p1, v0}, Landroidx/car/app/Screen;->dispatchLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    .line 404
    :cond_0
    sget-object v0, Landroidx/lifecycle/Lifecycle$State;->STARTED:Landroidx/lifecycle/Lifecycle$State;

    invoke-virtual {p0, v0}, Landroidx/lifecycle/Lifecycle$State;->isAtLeast(Landroidx/lifecycle/Lifecycle$State;)Z

    move-result p0

    if-eqz p0, :cond_1

    .line 405
    sget-object p0, Landroidx/lifecycle/Lifecycle$Event;->ON_STOP:Landroidx/lifecycle/Lifecycle$Event;

    invoke-virtual {p1, p0}, Landroidx/car/app/Screen;->dispatchLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    :cond_1
    if-eqz p2, :cond_2

    .line 409
    sget-object p0, Landroidx/lifecycle/Lifecycle$Event;->ON_DESTROY:Landroidx/lifecycle/Lifecycle$Event;

    invoke-virtual {p1, p0}, Landroidx/car/app/Screen;->dispatchLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    :cond_2
    return-void
.end method


# virtual methods
.method public destroyAndClearScreenStack()V
    .locals 3

    .line 292
    new-instance v0, Ljava/util/ArrayDeque;

    iget-object v1, p0, Landroidx/car/app/ScreenManager;->mScreenStack:Ljava/util/Deque;

    invoke-direct {v0, v1}, Ljava/util/ArrayDeque;-><init>(Ljava/util/Collection;)V

    .line 293
    invoke-interface {v0}, Ljava/util/Deque;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroidx/car/app/Screen;

    const/4 v2, 0x1

    .line 294
    invoke-direct {p0, v1, v2}, Landroidx/car/app/ScreenManager;->stop(Landroidx/car/app/Screen;Z)V

    goto :goto_0

    .line 296
    :cond_0
    iget-object p0, p0, Landroidx/car/app/ScreenManager;->mScreenStack:Ljava/util/Deque;

    invoke-interface {p0}, Ljava/util/Collection;->clear()V

    return-void
.end method

.method public getScreenStackInternal()Ljava/util/Deque;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Deque<",
            "Landroidx/car/app/Screen;",
            ">;"
        }
    .end annotation

    .line 302
    iget-object p0, p0, Landroidx/car/app/ScreenManager;->mScreenStack:Ljava/util/Deque;

    return-object p0
.end method

.method public getTop()Landroidx/car/app/Screen;
    .locals 0

    .line 67
    invoke-static {}, Landroidx/car/app/utils/ThreadUtils;->checkMainThread()V

    .line 68
    iget-object p0, p0, Landroidx/car/app/ScreenManager;->mScreenStack:Ljava/util/Deque;

    invoke-interface {p0}, Ljava/util/Deque;->peek()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroidx/car/app/Screen;

    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    return-object p0
.end method

.method public getTopTemplate()Landroidx/car/app/model/TemplateWrapper;
    .locals 4

    .line 273
    invoke-static {}, Landroidx/car/app/utils/ThreadUtils;->checkMainThread()V

    .line 275
    invoke-virtual {p0}, Landroidx/car/app/ScreenManager;->getTop()Landroidx/car/app/Screen;

    move-result-object v0

    const/4 v1, 0x3

    .line 276
    const-string v2, "CarApp"

    invoke-static {v2, v1}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 277
    new-instance v1, Ljava/lang/StringBuilder;

    const-string v3, "Requesting template from Screen "

    invoke-direct {v1, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v2, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 280
    :cond_0
    invoke-virtual {v0}, Landroidx/car/app/Screen;->getTemplateWrapper()Landroidx/car/app/model/TemplateWrapper;

    move-result-object v0

    .line 282
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    .line 283
    iget-object p0, p0, Landroidx/car/app/ScreenManager;->mScreenStack:Ljava/util/Deque;

    invoke-interface {p0}, Ljava/util/Deque;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroidx/car/app/Screen;

    .line 284
    invoke-virtual {v2}, Landroidx/car/app/Screen;->getLastTemplateInfo()Landroidx/car/app/model/TemplateInfo;

    move-result-object v2

    invoke-interface {v1, v2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 287
    :cond_1
    invoke-virtual {v0, v1}, Landroidx/car/app/model/TemplateWrapper;->setTemplateInfosForScreenStack(Ljava/util/List;)V

    return-object v0
.end method

.method public pop()V
    .locals 2

    .line 139
    invoke-static {}, Landroidx/car/app/utils/ThreadUtils;->checkMainThread()V

    .line 140
    iget-object v0, p0, Landroidx/car/app/ScreenManager;->mAppLifecycle:Landroidx/lifecycle/Lifecycle;

    invoke-virtual {v0}, Landroidx/lifecycle/Lifecycle;->getCurrentState()Landroidx/lifecycle/Lifecycle$State;

    move-result-object v0

    sget-object v1, Landroidx/lifecycle/Lifecycle$State;->DESTROYED:Landroidx/lifecycle/Lifecycle$State;

    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 p0, 0x3

    .line 141
    const-string v0, "CarApp"

    invoke-static {v0, p0}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result p0

    if-eqz p0, :cond_1

    .line 142
    const-string p0, "Popping screens after the DESTROYED state is a no-op"

    invoke-static {v0, p0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return-void

    .line 147
    :cond_0
    iget-object v0, p0, Landroidx/car/app/ScreenManager;->mScreenStack:Ljava/util/Deque;

    invoke-interface {v0}, Ljava/util/Deque;->size()I

    move-result v0

    const/4 v1, 0x1

    if-le v0, v1, :cond_1

    .line 148
    iget-object v0, p0, Landroidx/car/app/ScreenManager;->mScreenStack:Ljava/util/Deque;

    invoke-interface {v0}, Ljava/util/Deque;->pop()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroidx/car/app/Screen;

    invoke-static {v0}, Ljava/util/Collections;->singletonList(Ljava/lang/Object;)Ljava/util/List;

    move-result-object v0

    invoke-direct {p0, v0}, Landroidx/car/app/ScreenManager;->popInternal(Ljava/util/List;)V

    :cond_1
    return-void
.end method

.method public push(Landroidx/car/app/Screen;)V
    .locals 2

    .line 84
    invoke-static {}, Landroidx/car/app/utils/ThreadUtils;->checkMainThread()V

    .line 85
    iget-object v0, p0, Landroidx/car/app/ScreenManager;->mAppLifecycle:Landroidx/lifecycle/Lifecycle;

    invoke-virtual {v0}, Landroidx/lifecycle/Lifecycle;->getCurrentState()Landroidx/lifecycle/Lifecycle$State;

    move-result-object v0

    sget-object v1, Landroidx/lifecycle/Lifecycle$State;->DESTROYED:Landroidx/lifecycle/Lifecycle$State;

    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    const/4 p0, 0x3

    .line 86
    const-string p1, "CarApp"

    invoke-static {p1, p0}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result p0

    if-eqz p0, :cond_0

    .line 87
    const-string p0, "Pushing screens after the DESTROYED state is a no-op"

    invoke-static {p1, p0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    :cond_0
    return-void

    .line 91
    :cond_1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    invoke-direct {p0, p1}, Landroidx/car/app/ScreenManager;->pushInternal(Landroidx/car/app/Screen;)V

    return-void
.end method
