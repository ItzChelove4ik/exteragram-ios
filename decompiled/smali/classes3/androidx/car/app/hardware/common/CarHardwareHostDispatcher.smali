.class public Landroidx/car/app/hardware/common/CarHardwareHostDispatcher;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final mHostDispatcher:Landroidx/car/app/HostDispatcher;

.field private mICarHardwareHost:Landroidx/car/app/hardware/ICarHardwareHost;


# direct methods
.method public static synthetic $r8$lambda$-uJPuf2SZKQG1LuTBzNl32gl8Cs(Landroidx/car/app/hardware/common/CarHardwareHostDispatcher;ILandroidx/car/app/serialization/Bundleable;Landroidx/car/app/hardware/ICarHardwareResult;)Ljava/lang/Object;
    .locals 0

    .line 98
    invoke-direct {p0}, Landroidx/car/app/hardware/common/CarHardwareHostDispatcher;->getHost()Landroidx/car/app/hardware/ICarHardwareHost;

    move-result-object p0

    .line 98
    invoke-interface {p0, p1, p2, p3}, Landroidx/car/app/hardware/ICarHardwareHost;->subscribeCarHardwareResult(ILandroidx/car/app/serialization/Bundleable;Landroidx/car/app/hardware/ICarHardwareResult;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public static synthetic $r8$lambda$J--K4g1XwTrYMz4UEkchHiRRUL8(Landroidx/car/app/ICarHost;)Landroidx/car/app/hardware/ICarHardwareHost;
    .locals 1

    .line 128
    const-string v0, "hardware"

    .line 129
    invoke-interface {p0, v0}, Landroidx/car/app/ICarHost;->getHost(Ljava/lang/String;)Landroid/os/IBinder;

    move-result-object p0

    .line 128
    invoke-static {p0}, Landroidx/car/app/hardware/ICarHardwareHost$Stub;->asInterface(Landroid/os/IBinder;)Landroidx/car/app/hardware/ICarHardwareHost;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic $r8$lambda$QGWz3y_oA7uH7F2k5epRAGxfhNo(Landroidx/car/app/hardware/common/CarHardwareHostDispatcher;ILandroidx/car/app/serialization/Bundleable;)Ljava/lang/Object;
    .locals 0

    .line 116
    invoke-direct {p0}, Landroidx/car/app/hardware/common/CarHardwareHostDispatcher;->getHost()Landroidx/car/app/hardware/ICarHardwareHost;

    move-result-object p0

    .line 116
    invoke-interface {p0, p1, p2}, Landroidx/car/app/hardware/ICarHardwareHost;->unsubscribeCarHardwareResult(ILandroidx/car/app/serialization/Bundleable;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public static synthetic $r8$lambda$RWB_Hd3Wpqr7Y3FVmYXjx-Lusow(Landroidx/car/app/hardware/common/CarHardwareHostDispatcher;ILandroidx/car/app/serialization/Bundleable;Landroidx/car/app/hardware/ICarHardwareResult;)Ljava/lang/Object;
    .locals 0

    .line 76
    invoke-direct {p0}, Landroidx/car/app/hardware/common/CarHardwareHostDispatcher;->getHost()Landroidx/car/app/hardware/ICarHardwareHost;

    move-result-object p0

    .line 76
    invoke-interface {p0, p1, p2, p3}, Landroidx/car/app/hardware/ICarHardwareHost;->getCarHardwareResult(ILandroidx/car/app/serialization/Bundleable;Landroidx/car/app/hardware/ICarHardwareResult;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public constructor <init>(Landroidx/car/app/HostDispatcher;)V
    .locals 0

    .line 58
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 59
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Landroidx/car/app/hardware/common/CarHardwareHostDispatcher;->mHostDispatcher:Landroidx/car/app/HostDispatcher;

    return-void
.end method

.method private getHost()Landroidx/car/app/hardware/ICarHardwareHost;
    .locals 4

    .line 123
    iget-object v0, p0, Landroidx/car/app/hardware/common/CarHardwareHostDispatcher;->mICarHardwareHost:Landroidx/car/app/hardware/ICarHardwareHost;

    if-nez v0, :cond_0

    .line 125
    iget-object v0, p0, Landroidx/car/app/hardware/common/CarHardwareHostDispatcher;->mHostDispatcher:Landroidx/car/app/HostDispatcher;

    new-instance v1, Landroidx/car/app/hardware/common/CarHardwareHostDispatcher$$ExternalSyntheticLambda3;

    invoke-direct {v1}, Landroidx/car/app/hardware/common/CarHardwareHostDispatcher$$ExternalSyntheticLambda3;-><init>()V

    const-string v2, "car"

    const-string v3, "getHost(CarHardware)"

    invoke-virtual {v0, v2, v3, v1}, Landroidx/car/app/HostDispatcher;->dispatchForResult(Ljava/lang/String;Ljava/lang/String;Landroidx/car/app/HostCall;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroidx/car/app/hardware/ICarHardwareHost;

    invoke-static {v0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    check-cast v0, Landroidx/car/app/hardware/ICarHardwareHost;

    .line 131
    iput-object v0, p0, Landroidx/car/app/hardware/common/CarHardwareHostDispatcher;->mICarHardwareHost:Landroidx/car/app/hardware/ICarHardwareHost;

    :cond_0
    return-object v0
.end method


# virtual methods
.method public dispatchGetCarHardwareResult(ILandroidx/car/app/serialization/Bundleable;Landroidx/car/app/hardware/ICarHardwareResult;)V
    .locals 1

    .line 73
    invoke-static {p3}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 74
    new-instance v0, Landroidx/car/app/hardware/common/CarHardwareHostDispatcher$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0, p1, p2, p3}, Landroidx/car/app/hardware/common/CarHardwareHostDispatcher$$ExternalSyntheticLambda0;-><init>(Landroidx/car/app/hardware/common/CarHardwareHostDispatcher;ILandroidx/car/app/serialization/Bundleable;Landroidx/car/app/hardware/ICarHardwareResult;)V

    const-string p0, "getCarHardwareResult"

    invoke-static {p0, v0}, Landroidx/car/app/utils/RemoteUtils;->dispatchCallToHost(Ljava/lang/String;Landroidx/car/app/utils/RemoteUtils$RemoteCall;)V

    return-void
.end method

.method public dispatchSubscribeCarHardwareResult(ILandroidx/car/app/serialization/Bundleable;Landroidx/car/app/hardware/ICarHardwareResult;)V
    .locals 1

    .line 95
    invoke-static {p3}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 96
    new-instance v0, Landroidx/car/app/hardware/common/CarHardwareHostDispatcher$$ExternalSyntheticLambda1;

    invoke-direct {v0, p0, p1, p2, p3}, Landroidx/car/app/hardware/common/CarHardwareHostDispatcher$$ExternalSyntheticLambda1;-><init>(Landroidx/car/app/hardware/common/CarHardwareHostDispatcher;ILandroidx/car/app/serialization/Bundleable;Landroidx/car/app/hardware/ICarHardwareResult;)V

    const-string/jumbo p0, "subscribeCarHardwareResult"

    invoke-static {p0, v0}, Landroidx/car/app/utils/RemoteUtils;->dispatchCallToHost(Ljava/lang/String;Landroidx/car/app/utils/RemoteUtils$RemoteCall;)V

    return-void
.end method

.method public dispatchUnsubscribeCarHardwareResult(ILandroidx/car/app/serialization/Bundleable;)V
    .locals 1

    .line 114
    new-instance v0, Landroidx/car/app/hardware/common/CarHardwareHostDispatcher$$ExternalSyntheticLambda2;

    invoke-direct {v0, p0, p1, p2}, Landroidx/car/app/hardware/common/CarHardwareHostDispatcher$$ExternalSyntheticLambda2;-><init>(Landroidx/car/app/hardware/common/CarHardwareHostDispatcher;ILandroidx/car/app/serialization/Bundleable;)V

    const-string/jumbo p0, "unsubscribeCarHardwareResult"

    invoke-static {p0, v0}, Landroidx/car/app/utils/RemoteUtils;->dispatchCallToHost(Ljava/lang/String;Landroidx/car/app/utils/RemoteUtils$RemoteCall;)V

    return-void
.end method
