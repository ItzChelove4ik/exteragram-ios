.class Landroidx/car/app/AppManager$1;
.super Landroidx/car/app/IAppManager$Stub;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Landroidx/car/app/AppManager;-><init>(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;Landroidx/lifecycle/Lifecycle;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Landroidx/car/app/AppManager;

.field final synthetic val$carContext:Landroidx/car/app/CarContext;


# direct methods
.method public static synthetic $r8$lambda$0BYIOBGeQufkvO0GpWbLJJAjwE8(Landroidx/car/app/CarContext;)Ljava/lang/Object;
    .locals 0

    .line 286
    invoke-virtual {p0}, Landroidx/car/app/CarContext;->getOnBackPressedDispatcher()Landroidx/activity/OnBackPressedDispatcher;

    move-result-object p0

    invoke-virtual {p0}, Landroidx/activity/OnBackPressedDispatcher;->onBackPressed()V

    const/4 p0, 0x0

    return-object p0
.end method

.method public static synthetic $r8$lambda$GRoiwQQxcfwY7PBpRDnZMPJPkh8(Landroidx/car/app/CarContext;)Ljava/lang/Object;
    .locals 1

    .line 320
    const-class v0, Landroidx/car/app/AppManager;

    invoke-virtual {p0, v0}, Landroidx/car/app/CarContext;->getCarService(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroidx/car/app/AppManager;

    invoke-virtual {p0}, Landroidx/car/app/AppManager;->stopLocationUpdates()V

    const/4 p0, 0x0

    return-object p0
.end method

.method public static synthetic $r8$lambda$kiPel4e4C6L1n4qbWD8qVxWes9Y(Landroidx/car/app/CarContext;)Ljava/lang/Object;
    .locals 1

    .line 310
    const-class v0, Landroidx/car/app/AppManager;

    invoke-virtual {p0, v0}, Landroidx/car/app/CarContext;->getCarService(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroidx/car/app/AppManager;

    invoke-virtual {p0}, Landroidx/car/app/AppManager;->startLocationUpdates()V

    const/4 p0, 0x0

    return-object p0
.end method

.method public constructor <init>(Landroidx/car/app/AppManager;Landroidx/car/app/CarContext;)V
    .locals 0

    .line 274
    iput-object p1, p0, Landroidx/car/app/AppManager$1;->this$0:Landroidx/car/app/AppManager;

    iput-object p2, p0, Landroidx/car/app/AppManager$1;->val$carContext:Landroidx/car/app/CarContext;

    invoke-direct {p0}, Landroidx/car/app/IAppManager$Stub;-><init>()V

    return-void
.end method


# virtual methods
.method public getTemplate(Landroidx/car/app/IOnDoneCallback;)V
    .locals 2

    .line 277
    iget-object v0, p0, Landroidx/car/app/AppManager$1;->this$0:Landroidx/car/app/AppManager;

    invoke-virtual {v0}, Landroidx/car/app/AppManager;->getLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object v0

    iget-object p0, p0, Landroidx/car/app/AppManager$1;->val$carContext:Landroidx/car/app/CarContext;

    const-class v1, Landroidx/car/app/ScreenManager;

    .line 278
    invoke-virtual {p0, v1}, Landroidx/car/app/CarContext;->getCarService(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroidx/car/app/ScreenManager;

    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v1, Landroidx/car/app/AppManager$1$$ExternalSyntheticLambda1;

    invoke-direct {v1, p0}, Landroidx/car/app/AppManager$1$$ExternalSyntheticLambda1;-><init>(Landroidx/car/app/ScreenManager;)V

    .line 277
    const-string p0, "getTemplate"

    invoke-static {v0, p1, p0, v1}, Landroidx/car/app/utils/RemoteUtils;->dispatchCallFromHost(Landroidx/lifecycle/Lifecycle;Landroidx/car/app/IOnDoneCallback;Ljava/lang/String;Landroidx/car/app/utils/RemoteUtils$HostCall;)V

    return-void
.end method

.method public onBackPressed(Landroidx/car/app/IOnDoneCallback;)V
    .locals 2

    .line 283
    iget-object v0, p0, Landroidx/car/app/AppManager$1;->this$0:Landroidx/car/app/AppManager;

    invoke-virtual {v0}, Landroidx/car/app/AppManager;->getLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object v0

    iget-object p0, p0, Landroidx/car/app/AppManager$1;->val$carContext:Landroidx/car/app/CarContext;

    new-instance v1, Landroidx/car/app/AppManager$1$$ExternalSyntheticLambda0;

    invoke-direct {v1, p0}, Landroidx/car/app/AppManager$1$$ExternalSyntheticLambda0;-><init>(Landroidx/car/app/CarContext;)V

    const-string/jumbo p0, "onBackPressed"

    invoke-static {v0, p1, p0, v1}, Landroidx/car/app/utils/RemoteUtils;->dispatchCallFromHost(Landroidx/lifecycle/Lifecycle;Landroidx/car/app/IOnDoneCallback;Ljava/lang/String;Landroidx/car/app/utils/RemoteUtils$HostCall;)V

    return-void
.end method

.method public startLocationUpdates(Landroidx/car/app/IOnDoneCallback;)V
    .locals 7

    .line 293
    iget-object v0, p0, Landroidx/car/app/AppManager$1;->val$carContext:Landroidx/car/app/CarContext;

    invoke-virtual {v0}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v0

    .line 294
    iget-object v1, p0, Landroidx/car/app/AppManager$1;->val$carContext:Landroidx/car/app/CarContext;

    .line 296
    invoke-virtual {v1}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v1

    .line 295
    const-string v2, "android.permission.ACCESS_FINE_LOCATION"

    invoke-virtual {v0, v2, v1}, Landroid/content/pm/PackageManager;->checkPermission(Ljava/lang/String;Ljava/lang/String;)I

    move-result v1

    const/4 v2, 0x0

    const/4 v3, 0x1

    const/4 v4, -0x1

    if-ne v1, v4, :cond_0

    move v1, v3

    goto :goto_0

    :cond_0
    move v1, v2

    .line 297
    :goto_0
    iget-object v5, p0, Landroidx/car/app/AppManager$1;->val$carContext:Landroidx/car/app/CarContext;

    .line 299
    invoke-virtual {v5}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v5

    .line 298
    const-string v6, "android.permission.ACCESS_COARSE_LOCATION"

    invoke-virtual {v0, v6, v5}, Landroid/content/pm/PackageManager;->checkPermission(Ljava/lang/String;Ljava/lang/String;)I

    move-result v0

    if-ne v0, v4, :cond_1

    move v2, v3

    .line 301
    :cond_1
    const-string/jumbo v0, "startLocationUpdates"

    if-eqz v1, :cond_2

    if-eqz v2, :cond_2

    .line 302
    new-instance v1, Ljava/lang/SecurityException;

    const-string v2, "Location permission(s) not granted."

    invoke-direct {v1, v2}, Ljava/lang/SecurityException;-><init>(Ljava/lang/String;)V

    invoke-static {p1, v0, v1}, Landroidx/car/app/utils/RemoteUtils;->sendFailureResponseToHost(Landroidx/car/app/IOnDoneCallback;Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 307
    :cond_2
    iget-object v1, p0, Landroidx/car/app/AppManager$1;->this$0:Landroidx/car/app/AppManager;

    invoke-virtual {v1}, Landroidx/car/app/AppManager;->getLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object v1

    iget-object p0, p0, Landroidx/car/app/AppManager$1;->val$carContext:Landroidx/car/app/CarContext;

    new-instance v2, Landroidx/car/app/AppManager$1$$ExternalSyntheticLambda3;

    invoke-direct {v2, p0}, Landroidx/car/app/AppManager$1$$ExternalSyntheticLambda3;-><init>(Landroidx/car/app/CarContext;)V

    invoke-static {v1, p1, v0, v2}, Landroidx/car/app/utils/RemoteUtils;->dispatchCallFromHost(Landroidx/lifecycle/Lifecycle;Landroidx/car/app/IOnDoneCallback;Ljava/lang/String;Landroidx/car/app/utils/RemoteUtils$HostCall;)V

    return-void
.end method

.method public stopLocationUpdates(Landroidx/car/app/IOnDoneCallback;)V
    .locals 2

    .line 317
    iget-object v0, p0, Landroidx/car/app/AppManager$1;->this$0:Landroidx/car/app/AppManager;

    invoke-virtual {v0}, Landroidx/car/app/AppManager;->getLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object v0

    iget-object p0, p0, Landroidx/car/app/AppManager$1;->val$carContext:Landroidx/car/app/CarContext;

    new-instance v1, Landroidx/car/app/AppManager$1$$ExternalSyntheticLambda2;

    invoke-direct {v1, p0}, Landroidx/car/app/AppManager$1$$ExternalSyntheticLambda2;-><init>(Landroidx/car/app/CarContext;)V

    const-string/jumbo p0, "stopLocationUpdates"

    invoke-static {v0, p1, p0, v1}, Landroidx/car/app/utils/RemoteUtils;->dispatchCallFromHost(Landroidx/lifecycle/Lifecycle;Landroidx/car/app/IOnDoneCallback;Ljava/lang/String;Landroidx/car/app/utils/RemoteUtils$HostCall;)V

    return-void
.end method
