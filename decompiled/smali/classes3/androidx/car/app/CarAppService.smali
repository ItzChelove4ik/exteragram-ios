.class public abstract Landroidx/car/app/CarAppService;
.super Landroid/app/Service;
.source "SourceFile"


# instance fields
.field private mAppInfo:Landroidx/car/app/AppInfo;

.field private final mBinders:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Landroidx/car/app/SessionInfo;",
            "Landroidx/car/app/CarAppBinder;",
            ">;"
        }
    .end annotation
.end field

.field private mHostInfo:Landroidx/car/app/HostInfo;


# direct methods
.method public static synthetic $r8$lambda$BoF8lRhyLvMMyIqziQS6Yiscbqo(Landroidx/car/app/CarAppService;Landroidx/car/app/SessionInfo;)V
    .locals 1

    .line 220
    iget-object v0, p0, Landroidx/car/app/CarAppService;->mBinders:Ljava/util/Map;

    .line 220
    monitor-enter v0

    .line 221
    :try_start_0
    iget-object p0, p0, Landroidx/car/app/CarAppService;->mBinders:Ljava/util/Map;

    invoke-interface {p0, p1}, Ljava/util/Map;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroidx/car/app/CarAppBinder;

    if-eqz p0, :cond_0

    .line 223
    invoke-virtual {p0}, Landroidx/car/app/CarAppBinder;->onDestroyLifecycle()V

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    .line 225
    :cond_0
    :goto_0
    monitor-exit v0

    return-void

    :goto_1
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method public static synthetic $r8$lambda$qvBH8jOIRq-ebk_oQomZTKGMWMw(Landroidx/car/app/CarAppService;)V
    .locals 5

    .line 322
    iget-object v0, p0, Landroidx/car/app/CarAppService;->mBinders:Ljava/util/Map;

    .line 322
    monitor-enter v0

    .line 323
    :try_start_0
    iget-object p0, p0, Landroidx/car/app/CarAppService;->mBinders:Ljava/util/Map;

    invoke-interface {p0}, Ljava/util/Map;->values()Ljava/util/Collection;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroidx/car/app/CarAppBinder;

    .line 324
    const-string v2, "CarApp"

    const/4 v3, 0x3

    invoke-static {v2, v3}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result v2

    if-eqz v2, :cond_0

    .line 325
    const-string v2, "CarApp"

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "Executing onAutoDriveEnabled for "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 326
    invoke-virtual {v1}, Landroidx/car/app/CarAppBinder;->getCurrentSessionInfo()Landroidx/car/app/SessionInfo;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    .line 325
    invoke-static {v2, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_1

    :catchall_0
    move-exception p0

    goto :goto_2

    .line 329
    :cond_0
    :goto_1
    invoke-virtual {v1}, Landroidx/car/app/CarAppBinder;->onAutoDriveEnabled()V

    goto :goto_0

    .line 331
    :cond_1
    monitor-exit v0

    return-void

    :goto_2
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method public constructor <init>()V
    .locals 1

    .line 80
    invoke-direct {p0}, Landroid/app/Service;-><init>()V

    .line 161
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Landroidx/car/app/CarAppService;->mBinders:Ljava/util/Map;

    return-void
.end method


# virtual methods
.method public abstract createHostValidator()Landroidx/car/app/validation/HostValidator;
.end method

.method public final dump(Ljava/io/FileDescriptor;Ljava/io/PrintWriter;[Ljava/lang/String;)V
    .locals 2

    .line 317
    invoke-super {p0, p1, p2, p3}, Landroid/app/Service;->dump(Ljava/io/FileDescriptor;Ljava/io/PrintWriter;[Ljava/lang/String;)V

    .line 319
    array-length p1, p3

    const/4 p2, 0x0

    :goto_0
    if-ge p2, p1, :cond_1

    aget-object v0, p3, p2

    .line 320
    const-string v1, "AUTO_DRIVE"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 321
    new-instance v0, Landroidx/car/app/CarAppService$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0}, Landroidx/car/app/CarAppService$$ExternalSyntheticLambda0;-><init>(Landroidx/car/app/CarAppService;)V

    invoke-static {v0}, Landroidx/car/app/utils/ThreadUtils;->runOnMain(Ljava/lang/Runnable;)V

    :cond_0
    add-int/lit8 p2, p2, 0x1

    goto :goto_0

    :cond_1
    return-void
.end method

.method public getAppInfo()Landroidx/car/app/AppInfo;
    .locals 1

    .line 390
    iget-object v0, p0, Landroidx/car/app/CarAppService;->mAppInfo:Landroidx/car/app/AppInfo;

    if-nez v0, :cond_0

    .line 392
    invoke-static {p0}, Landroidx/car/app/AppInfo;->create(Landroid/content/Context;)Landroidx/car/app/AppInfo;

    move-result-object v0

    iput-object v0, p0, Landroidx/car/app/CarAppService;->mAppInfo:Landroidx/car/app/AppInfo;

    .line 394
    :cond_0
    iget-object p0, p0, Landroidx/car/app/CarAppService;->mAppInfo:Landroidx/car/app/AppInfo;

    return-object p0
.end method

.method public final getHostInfo()Landroidx/car/app/HostInfo;
    .locals 0

    .line 344
    iget-object p0, p0, Landroidx/car/app/CarAppService;->mHostInfo:Landroidx/car/app/HostInfo;

    return-object p0
.end method

.method public final onBind(Landroid/content/Intent;)Landroid/os/IBinder;
    .locals 3

    .line 193
    invoke-static {p1}, Landroidx/car/app/SessionInfoIntentEncoder;->containsSessionInfo(Landroid/content/Intent;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 194
    invoke-static {p1}, Landroidx/car/app/SessionInfoIntentEncoder;->decode(Landroid/content/Intent;)Landroidx/car/app/SessionInfo;

    move-result-object p1

    goto :goto_0

    .line 195
    :cond_0
    sget-object p1, Landroidx/car/app/SessionInfo;->DEFAULT_SESSION_INFO:Landroidx/car/app/SessionInfo;

    .line 197
    :goto_0
    iget-object v0, p0, Landroidx/car/app/CarAppService;->mBinders:Ljava/util/Map;

    monitor-enter v0

    .line 198
    :try_start_0
    iget-object v1, p0, Landroidx/car/app/CarAppService;->mBinders:Ljava/util/Map;

    invoke-interface {v1, p1}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_1

    .line 199
    iget-object v1, p0, Landroidx/car/app/CarAppService;->mBinders:Ljava/util/Map;

    new-instance v2, Landroidx/car/app/CarAppBinder;

    invoke-direct {v2, p0, p1}, Landroidx/car/app/CarAppBinder;-><init>(Landroidx/car/app/CarAppService;Landroidx/car/app/SessionInfo;)V

    invoke-interface {v1, p1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_1

    :catchall_0
    move-exception p0

    goto :goto_2

    .line 201
    :cond_1
    :goto_1
    iget-object p0, p0, Landroidx/car/app/CarAppService;->mBinders:Ljava/util/Map;

    invoke-interface {p0, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroidx/car/app/CarAppBinder;

    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    check-cast p0, Landroid/os/IBinder;

    monitor-exit v0

    return-object p0

    .line 202
    :goto_2
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method public onCreateSession()Landroidx/car/app/Session;
    .locals 1

    .line 288
    new-instance p0, Ljava/lang/RuntimeException;

    const-string v0, "Please override and implement CarAppService#onCreateSession(SessionInfo)."

    invoke-direct {p0, v0}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method public onCreateSession(Landroidx/car/app/SessionInfo;)Landroidx/car/app/Session;
    .locals 0

    .line 310
    invoke-virtual {p0}, Landroidx/car/app/CarAppService;->onCreateSession()Landroidx/car/app/Session;

    move-result-object p0

    return-object p0
.end method

.method public onDestroy()V
    .locals 3

    .line 173
    iget-object v0, p0, Landroidx/car/app/CarAppService;->mBinders:Ljava/util/Map;

    monitor-enter v0

    .line 174
    :try_start_0
    iget-object v1, p0, Landroidx/car/app/CarAppService;->mBinders:Ljava/util/Map;

    invoke-interface {v1}, Ljava/util/Map;->values()Ljava/util/Collection;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_0

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroidx/car/app/CarAppBinder;

    .line 175
    invoke-virtual {v2}, Landroidx/car/app/CarAppBinder;->destroy()V

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    .line 177
    :cond_0
    iget-object p0, p0, Landroidx/car/app/CarAppService;->mBinders:Ljava/util/Map;

    invoke-interface {p0}, Ljava/util/Map;->clear()V

    .line 178
    monitor-exit v0

    return-void

    :goto_1
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method public final onUnbind(Landroid/content/Intent;)Z
    .locals 4

    .line 212
    const-string v0, "CarApp"

    const/4 v1, 0x3

    invoke-static {v0, v1}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result v2

    if-eqz v2, :cond_0

    .line 213
    new-instance v2, Ljava/lang/StringBuilder;

    const-string/jumbo v3, "onUnbind intent: "

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v0, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 216
    :cond_0
    invoke-static {p1}, Landroidx/car/app/SessionInfoIntentEncoder;->containsSessionInfo(Landroid/content/Intent;)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 217
    invoke-static {p1}, Landroidx/car/app/SessionInfoIntentEncoder;->decode(Landroid/content/Intent;)Landroidx/car/app/SessionInfo;

    move-result-object p1

    goto :goto_0

    .line 218
    :cond_1
    sget-object p1, Landroidx/car/app/SessionInfo;->DEFAULT_SESSION_INFO:Landroidx/car/app/SessionInfo;

    .line 219
    :goto_0
    new-instance v2, Landroidx/car/app/CarAppService$$ExternalSyntheticLambda1;

    invoke-direct {v2, p0, p1}, Landroidx/car/app/CarAppService$$ExternalSyntheticLambda1;-><init>(Landroidx/car/app/CarAppService;Landroidx/car/app/SessionInfo;)V

    invoke-static {v2}, Landroidx/car/app/utils/ThreadUtils;->runOnMain(Ljava/lang/Runnable;)V

    .line 228
    invoke-static {v0, v1}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result p0

    if-eqz p0, :cond_2

    .line 229
    const-string/jumbo p0, "onUnbind completed"

    invoke-static {v0, p0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    :cond_2
    const/4 p0, 0x1

    return p0
.end method

.method public setHostInfo(Landroidx/car/app/HostInfo;)V
    .locals 0

    .line 348
    iput-object p1, p0, Landroidx/car/app/CarAppService;->mHostInfo:Landroidx/car/app/HostInfo;

    return-void
.end method
