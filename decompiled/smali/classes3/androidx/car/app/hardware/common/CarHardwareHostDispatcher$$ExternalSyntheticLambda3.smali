.class public final synthetic Landroidx/car/app/hardware/common/CarHardwareHostDispatcher$$ExternalSyntheticLambda3;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/car/app/HostCall;


# direct methods
.method public synthetic constructor <init>()V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final dispatch(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 0
    check-cast p1, Landroidx/car/app/ICarHost;

    invoke-static {p1}, Landroidx/car/app/hardware/common/CarHardwareHostDispatcher;->$r8$lambda$J--K4g1XwTrYMz4UEkchHiRRUL8(Landroidx/car/app/ICarHost;)Landroidx/car/app/hardware/ICarHardwareHost;

    move-result-object p0

    return-object p0
.end method
