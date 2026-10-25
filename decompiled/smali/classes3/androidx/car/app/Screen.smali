.class public abstract Landroidx/car/app/Screen;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/lifecycle/LifecycleOwner;


# instance fields
.field private final mCarContext:Landroidx/car/app/CarContext;

.field private final mLifecycleRegistry:Landroidx/lifecycle/LifecycleRegistry;

.field private mOnScreenResultListener:Landroidx/car/app/OnScreenResultListener;

.field private mResult:Ljava/lang/Object;

.field private mTemplateWrapper:Landroidx/car/app/model/TemplateWrapper;

.field private mUseLastTemplateId:Z


# direct methods
.method public static synthetic $r8$lambda$1fy9bSyOIB5ckm4-u8XzevkC8wY(Landroidx/car/app/Screen;Landroidx/lifecycle/Lifecycle$Event;)V
    .locals 2

    .line 330
    iget-object v0, p0, Landroidx/car/app/Screen;->mLifecycleRegistry:Landroidx/lifecycle/LifecycleRegistry;

    .line 330
    invoke-virtual {v0}, Landroidx/lifecycle/LifecycleRegistry;->getCurrentState()Landroidx/lifecycle/Lifecycle$State;

    move-result-object v0

    .line 332
    sget-object v1, Landroidx/lifecycle/Lifecycle$State;->INITIALIZED:Landroidx/lifecycle/Lifecycle$State;

    invoke-virtual {v0, v1}, Landroidx/lifecycle/Lifecycle$State;->isAtLeast(Landroidx/lifecycle/Lifecycle$State;)Z

    move-result v0

    if-nez v0, :cond_0

    return-void

    .line 336
    :cond_0
    sget-object v0, Landroidx/lifecycle/Lifecycle$Event;->ON_DESTROY:Landroidx/lifecycle/Lifecycle$Event;

    if-ne p1, v0, :cond_1

    .line 337
    iget-object v0, p0, Landroidx/car/app/Screen;->mOnScreenResultListener:Landroidx/car/app/OnScreenResultListener;

    iget-object v1, p0, Landroidx/car/app/Screen;->mResult:Ljava/lang/Object;

    invoke-interface {v0, v1}, Landroidx/car/app/OnScreenResultListener;->onScreenResult(Ljava/lang/Object;)V

    .line 340
    :cond_1
    iget-object p0, p0, Landroidx/car/app/Screen;->mLifecycleRegistry:Landroidx/lifecycle/LifecycleRegistry;

    invoke-virtual {p0, p1}, Landroidx/lifecycle/LifecycleRegistry;->handleLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V

    return-void
.end method

.method public static synthetic $r8$lambda$zxVt3BlwxQf66mOTwWveM9jjlDw(Ljava/lang/Object;)V
    .locals 0

    .line 0
    return-void
.end method

.method public constructor <init>(Landroidx/car/app/CarContext;)V
    .locals 1

    .line 85
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 59
    new-instance v0, Landroidx/lifecycle/LifecycleRegistry;

    invoke-direct {v0, p0}, Landroidx/lifecycle/LifecycleRegistry;-><init>(Landroidx/lifecycle/LifecycleOwner;)V

    iput-object v0, p0, Landroidx/car/app/Screen;->mLifecycleRegistry:Landroidx/lifecycle/LifecycleRegistry;

    .line 62
    new-instance v0, Landroidx/car/app/Screen$$ExternalSyntheticLambda0;

    invoke-direct {v0}, Landroidx/car/app/Screen$$ExternalSyntheticLambda0;-><init>()V

    iput-object v0, p0, Landroidx/car/app/Screen;->mOnScreenResultListener:Landroidx/car/app/OnScreenResultListener;

    .line 86
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Landroidx/car/app/Screen;->mCarContext:Landroidx/car/app/CarContext;

    return-void
.end method

.method private static getLastTemplateInfo(Landroidx/car/app/model/TemplateWrapper;)Landroidx/car/app/model/TemplateInfo;
    .locals 2

    .line 399
    new-instance v0, Landroidx/car/app/model/TemplateInfo;

    invoke-virtual {p0}, Landroidx/car/app/model/TemplateWrapper;->getTemplate()Landroidx/car/app/model/Template;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    .line 400
    invoke-virtual {p0}, Landroidx/car/app/model/TemplateWrapper;->getId()Ljava/lang/String;

    move-result-object p0

    invoke-direct {v0, v1, p0}, Landroidx/car/app/model/TemplateInfo;-><init>(Ljava/lang/Class;Ljava/lang/String;)V

    return-object v0
.end method


# virtual methods
.method public dispatchLifecycleEvent(Landroidx/lifecycle/Lifecycle$Event;)V
    .locals 1

    .line 328
    new-instance v0, Landroidx/car/app/Screen$$ExternalSyntheticLambda1;

    invoke-direct {v0, p0, p1}, Landroidx/car/app/Screen$$ExternalSyntheticLambda1;-><init>(Landroidx/car/app/Screen;Landroidx/lifecycle/Lifecycle$Event;)V

    invoke-static {v0}, Landroidx/car/app/utils/ThreadUtils;->runOnMain(Ljava/lang/Runnable;)V

    return-void
.end method

.method public final getCarContext()Landroidx/car/app/CarContext;
    .locals 0

    .line 224
    iget-object p0, p0, Landroidx/car/app/Screen;->mCarContext:Landroidx/car/app/CarContext;

    return-object p0
.end method

.method public getLastTemplateInfo()Landroidx/car/app/model/TemplateInfo;
    .locals 2

    .line 390
    iget-object v0, p0, Landroidx/car/app/Screen;->mTemplateWrapper:Landroidx/car/app/model/TemplateWrapper;

    if-nez v0, :cond_0

    .line 391
    invoke-virtual {p0}, Landroidx/car/app/Screen;->onGetTemplate()Landroidx/car/app/model/Template;

    move-result-object v0

    invoke-static {v0}, Landroidx/car/app/model/TemplateWrapper;->wrap(Landroidx/car/app/model/Template;)Landroidx/car/app/model/TemplateWrapper;

    move-result-object v0

    iput-object v0, p0, Landroidx/car/app/Screen;->mTemplateWrapper:Landroidx/car/app/model/TemplateWrapper;

    .line 393
    :cond_0
    new-instance v0, Landroidx/car/app/model/TemplateInfo;

    iget-object v1, p0, Landroidx/car/app/Screen;->mTemplateWrapper:Landroidx/car/app/model/TemplateWrapper;

    invoke-virtual {v1}, Landroidx/car/app/model/TemplateWrapper;->getTemplate()Landroidx/car/app/model/Template;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    iget-object p0, p0, Landroidx/car/app/Screen;->mTemplateWrapper:Landroidx/car/app/model/TemplateWrapper;

    .line 394
    invoke-virtual {p0}, Landroidx/car/app/model/TemplateWrapper;->getId()Ljava/lang/String;

    move-result-object p0

    invoke-direct {v0, v1, p0}, Landroidx/car/app/model/TemplateInfo;-><init>(Ljava/lang/Class;Ljava/lang/String;)V

    return-object v0
.end method

.method public final getLifecycle()Landroidx/lifecycle/Lifecycle;
    .locals 0

    .line 218
    iget-object p0, p0, Landroidx/car/app/Screen;->mLifecycleRegistry:Landroidx/lifecycle/LifecycleRegistry;

    return-object p0
.end method

.method public final getScreenManager()Landroidx/car/app/ScreenManager;
    .locals 1

    .line 230
    iget-object p0, p0, Landroidx/car/app/Screen;->mCarContext:Landroidx/car/app/CarContext;

    const-class v0, Landroidx/car/app/ScreenManager;

    invoke-virtual {p0, v0}, Landroidx/car/app/CarContext;->getCarService(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroidx/car/app/ScreenManager;

    return-object p0
.end method

.method public getTemplateWrapper()Landroidx/car/app/model/TemplateWrapper;
    .locals 5

    .line 359
    invoke-virtual {p0}, Landroidx/car/app/Screen;->onGetTemplate()Landroidx/car/app/model/Template;

    move-result-object v0

    .line 362
    iget-boolean v1, p0, Landroidx/car/app/Screen;->mUseLastTemplateId:Z

    if-eqz v1, :cond_0

    iget-object v1, p0, Landroidx/car/app/Screen;->mTemplateWrapper:Landroidx/car/app/model/TemplateWrapper;

    if-eqz v1, :cond_0

    .line 365
    invoke-static {v1}, Landroidx/car/app/Screen;->getLastTemplateInfo(Landroidx/car/app/model/TemplateWrapper;)Landroidx/car/app/model/TemplateInfo;

    move-result-object v1

    invoke-virtual {v1}, Landroidx/car/app/model/TemplateInfo;->getTemplateId()Ljava/lang/String;

    move-result-object v1

    .line 364
    invoke-static {v0, v1}, Landroidx/car/app/model/TemplateWrapper;->wrap(Landroidx/car/app/model/Template;Ljava/lang/String;)Landroidx/car/app/model/TemplateWrapper;

    move-result-object v1

    goto :goto_0

    .line 367
    :cond_0
    invoke-static {v0}, Landroidx/car/app/model/TemplateWrapper;->wrap(Landroidx/car/app/model/Template;)Landroidx/car/app/model/TemplateWrapper;

    move-result-object v1

    :goto_0
    const/4 v2, 0x0

    .line 369
    iput-boolean v2, p0, Landroidx/car/app/Screen;->mUseLastTemplateId:Z

    .line 371
    iput-object v1, p0, Landroidx/car/app/Screen;->mTemplateWrapper:Landroidx/car/app/model/TemplateWrapper;

    const/4 v2, 0x3

    .line 373
    const-string v3, "CarApp"

    invoke-static {v3, v2}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 374
    new-instance v2, Ljava/lang/StringBuilder;

    const-string v4, "Returning "

    invoke-direct {v2, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v0, " from screen "

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {v3, p0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    :cond_1
    return-object v1
.end method

.method public final invalidate()V
    .locals 2

    .line 105
    invoke-virtual {p0}, Landroidx/car/app/Screen;->getLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object v0

    invoke-virtual {v0}, Landroidx/lifecycle/Lifecycle;->getCurrentState()Landroidx/lifecycle/Lifecycle$State;

    move-result-object v0

    sget-object v1, Landroidx/lifecycle/Lifecycle$State;->STARTED:Landroidx/lifecycle/Lifecycle$State;

    invoke-virtual {v0, v1}, Landroidx/lifecycle/Lifecycle$State;->isAtLeast(Landroidx/lifecycle/Lifecycle$State;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 106
    iget-object p0, p0, Landroidx/car/app/Screen;->mCarContext:Landroidx/car/app/CarContext;

    const-class v0, Landroidx/car/app/AppManager;

    invoke-virtual {p0, v0}, Landroidx/car/app/CarContext;->getCarService(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroidx/car/app/AppManager;

    invoke-virtual {p0}, Landroidx/car/app/AppManager;->invalidate()V

    :cond_0
    return-void
.end method

.method public abstract onGetTemplate()Landroidx/car/app/model/Template;
.end method

.method public setUseLastTemplateId(Z)V
    .locals 0

    .line 412
    iput-boolean p1, p0, Landroidx/car/app/Screen;->mUseLastTemplateId:Z

    return-void
.end method
