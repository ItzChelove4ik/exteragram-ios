.class public Landroidx/car/app/AppManager;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/car/app/managers/Manager;


# instance fields
.field private final mAppManager:Landroidx/car/app/IAppManager$Stub;

.field private final mCarContext:Landroidx/car/app/CarContext;

.field private final mHostDispatcher:Landroidx/car/app/HostDispatcher;

.field private final mLifecycle:Landroidx/lifecycle/Lifecycle;

.field private final mLocationListener:Landroidx/core/location/LocationListenerCompat;

.field final mLocationUpdateHandlerThread:Landroid/os/HandlerThread;


# direct methods
.method public static synthetic $r8$lambda$-eRdGEweqivF9t3_swK7uQbdXz4(Landroidx/car/app/media/OpenMicrophoneRequest;Landroidx/car/app/IAppHost;)Landroidx/car/app/media/OpenMicrophoneResponse;
    .locals 2

    const/4 v0, 0x0

    .line 205
    :try_start_0
    invoke-static {p0}, Landroidx/car/app/serialization/Bundleable;->create(Ljava/lang/Object;)Landroidx/car/app/serialization/Bundleable;

    move-result-object p0

    invoke-interface {p1, p0}, Landroidx/car/app/IAppHost;->openMicrophone(Landroidx/car/app/serialization/Bundleable;)Landroidx/car/app/serialization/Bundleable;

    move-result-object p0

    if-nez p0, :cond_0

    return-object v0

    .line 207
    :cond_0
    invoke-virtual {p0}, Landroidx/car/app/serialization/Bundleable;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroidx/car/app/media/OpenMicrophoneResponse;
    :try_end_0
    .catch Landroidx/car/app/serialization/BundlerException; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception p0

    .line 209
    const-string p1, "CarApp"

    const-string v1, "Cannot open microphone"

    invoke-static {p1, v1, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    return-object v0
.end method

.method public static synthetic $r8$lambda$VtVWtpIxURiz0OeBU2n0ZIAdpM0(Landroidx/car/app/AppManager;Landroid/location/Location;)V
    .locals 2

    .line 327
    iget-object p0, p0, Landroidx/car/app/AppManager;->mHostDispatcher:Landroidx/car/app/HostDispatcher;

    .line 327
    new-instance v0, Landroidx/car/app/AppManager$$ExternalSyntheticLambda3;

    invoke-direct {v0, p1}, Landroidx/car/app/AppManager$$ExternalSyntheticLambda3;-><init>(Landroid/location/Location;)V

    const-string p1, "app"

    const-string/jumbo v1, "sendLocation"

    invoke-virtual {p0, p1, v1, v0}, Landroidx/car/app/HostDispatcher;->dispatch(Ljava/lang/String;Ljava/lang/String;Landroidx/car/app/HostCall;)V

    return-void
.end method

.method public static synthetic $r8$lambda$cN4HC8fuyfXod6lQ1qmuMXVVog4(Landroid/location/Location;Landroidx/car/app/IAppHost;)Ljava/lang/Object;
    .locals 0

    .line 330
    invoke-interface {p1, p0}, Landroidx/car/app/IAppHost;->sendLocation(Landroid/location/Location;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public static synthetic $r8$lambda$m3Q_6T8MfMcf0F5Std_z_SjF1jA(Landroidx/car/app/IAppHost;)Ljava/lang/Object;
    .locals 0

    .line 116
    invoke-interface {p0}, Landroidx/car/app/IAppHost;->invalidate()V

    const/4 p0, 0x0

    return-object p0
.end method

.method public constructor <init>(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;Landroidx/lifecycle/Lifecycle;)V
    .locals 0

    .line 270
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 271
    iput-object p1, p0, Landroidx/car/app/AppManager;->mCarContext:Landroidx/car/app/CarContext;

    .line 272
    iput-object p2, p0, Landroidx/car/app/AppManager;->mHostDispatcher:Landroidx/car/app/HostDispatcher;

    .line 273
    iput-object p3, p0, Landroidx/car/app/AppManager;->mLifecycle:Landroidx/lifecycle/Lifecycle;

    .line 274
    new-instance p2, Landroidx/car/app/AppManager$1;

    invoke-direct {p2, p0, p1}, Landroidx/car/app/AppManager$1;-><init>(Landroidx/car/app/AppManager;Landroidx/car/app/CarContext;)V

    iput-object p2, p0, Landroidx/car/app/AppManager;->mAppManager:Landroidx/car/app/IAppManager$Stub;

    .line 326
    new-instance p1, Landroid/os/HandlerThread;

    const-string p2, "LocationUpdateThread"

    invoke-direct {p1, p2}, Landroid/os/HandlerThread;-><init>(Ljava/lang/String;)V

    iput-object p1, p0, Landroidx/car/app/AppManager;->mLocationUpdateHandlerThread:Landroid/os/HandlerThread;

    .line 327
    new-instance p1, Landroidx/car/app/AppManager$$ExternalSyntheticLambda2;

    invoke-direct {p1, p0}, Landroidx/car/app/AppManager$$ExternalSyntheticLambda2;-><init>(Landroidx/car/app/AppManager;)V

    iput-object p1, p0, Landroidx/car/app/AppManager;->mLocationListener:Landroidx/core/location/LocationListenerCompat;

    return-void
.end method

.method public static create(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;Landroidx/lifecycle/Lifecycle;)Landroidx/car/app/AppManager;
    .locals 1

    .line 261
    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 262
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 263
    invoke-static {p2}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 265
    new-instance v0, Landroidx/car/app/AppManager;

    invoke-direct {v0, p0, p1, p2}, Landroidx/car/app/AppManager;-><init>(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;Landroidx/lifecycle/Lifecycle;)V

    return-object v0
.end method


# virtual methods
.method public getIInterface()Landroidx/car/app/IAppManager$Stub;
    .locals 0

    .line 222
    iget-object p0, p0, Landroidx/car/app/AppManager;->mAppManager:Landroidx/car/app/IAppManager$Stub;

    return-object p0
.end method

.method public getLifecycle()Landroidx/lifecycle/Lifecycle;
    .locals 0

    .line 227
    iget-object p0, p0, Landroidx/car/app/AppManager;->mLifecycle:Landroidx/lifecycle/Lifecycle;

    return-object p0
.end method

.method public invalidate()V
    .locals 3

    .line 113
    iget-object p0, p0, Landroidx/car/app/AppManager;->mHostDispatcher:Landroidx/car/app/HostDispatcher;

    new-instance v0, Landroidx/car/app/AppManager$$ExternalSyntheticLambda1;

    invoke-direct {v0}, Landroidx/car/app/AppManager$$ExternalSyntheticLambda1;-><init>()V

    const-string v1, "app"

    const-string v2, "invalidate"

    invoke-virtual {p0, v1, v2, v0}, Landroidx/car/app/HostDispatcher;->dispatch(Ljava/lang/String;Ljava/lang/String;Landroidx/car/app/HostCall;)V

    return-void
.end method

.method public openMicrophone(Landroidx/car/app/media/OpenMicrophoneRequest;)Landroidx/car/app/media/OpenMicrophoneResponse;
    .locals 3

    .line 200
    :try_start_0
    iget-object p0, p0, Landroidx/car/app/AppManager;->mHostDispatcher:Landroidx/car/app/HostDispatcher;

    const-string v0, "app"

    const-string/jumbo v1, "openMicrophone"

    new-instance v2, Landroidx/car/app/AppManager$$ExternalSyntheticLambda0;

    invoke-direct {v2, p1}, Landroidx/car/app/AppManager$$ExternalSyntheticLambda0;-><init>(Landroidx/car/app/media/OpenMicrophoneRequest;)V

    invoke-virtual {p0, v0, v1, v2}, Landroidx/car/app/HostDispatcher;->dispatchForResult(Ljava/lang/String;Ljava/lang/String;Landroidx/car/app/HostCall;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroidx/car/app/media/OpenMicrophoneResponse;
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception p0

    .line 215
    const-string p1, "CarApp"

    const-string v0, "Error getting microphone bytes from host"

    invoke-static {p1, v0, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    const/4 p0, 0x0

    return-object p0
.end method

.method public startLocationUpdates()V
    .locals 8
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "MissingPermission"
        }
    .end annotation

    .line 239
    invoke-virtual {p0}, Landroidx/car/app/AppManager;->stopLocationUpdates()V

    .line 240
    iget-object v0, p0, Landroidx/car/app/AppManager;->mCarContext:Landroidx/car/app/CarContext;

    const-string v1, "location"

    .line 241
    invoke-virtual {v0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    move-object v1, v0

    check-cast v1, Landroid/location/LocationManager;

    .line 242
    iget-object v6, p0, Landroidx/car/app/AppManager;->mLocationListener:Landroidx/core/location/LocationListenerCompat;

    iget-object p0, p0, Landroidx/car/app/AppManager;->mLocationUpdateHandlerThread:Landroid/os/HandlerThread;

    .line 246
    invoke-virtual {p0}, Landroid/os/HandlerThread;->getLooper()Landroid/os/Looper;

    move-result-object v7

    .line 242
    const-string v2, "fused"

    const-wide/16 v3, 0x3e8

    const/high16 v5, 0x3f800000    # 1.0f

    invoke-virtual/range {v1 .. v7}, Landroid/location/LocationManager;->requestLocationUpdates(Ljava/lang/String;JFLandroid/location/LocationListener;Landroid/os/Looper;)V

    return-void
.end method

.method public stopLocationUpdates()V
    .locals 2

    .line 253
    iget-object v0, p0, Landroidx/car/app/AppManager;->mCarContext:Landroidx/car/app/CarContext;

    const-string v1, "location"

    .line 254
    invoke-virtual {v0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/location/LocationManager;

    .line 255
    iget-object p0, p0, Landroidx/car/app/AppManager;->mLocationListener:Landroidx/core/location/LocationListenerCompat;

    invoke-virtual {v0, p0}, Landroid/location/LocationManager;->removeUpdates(Landroid/location/LocationListener;)V

    return-void
.end method
