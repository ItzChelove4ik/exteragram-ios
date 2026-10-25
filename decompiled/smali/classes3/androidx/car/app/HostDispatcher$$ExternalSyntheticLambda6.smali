.class public final synthetic Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda6;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/car/app/utils/RemoteUtils$RemoteCall;


# instance fields
.field public final synthetic f$0:Landroidx/car/app/HostDispatcher;


# direct methods
.method public synthetic constructor <init>(Landroidx/car/app/HostDispatcher;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda6;->f$0:Landroidx/car/app/HostDispatcher;

    return-void
.end method


# virtual methods
.method public final call()Ljava/lang/Object;
    .locals 0

    .line 0
    iget-object p0, p0, Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda6;->f$0:Landroidx/car/app/HostDispatcher;

    invoke-static {p0}, Landroidx/car/app/HostDispatcher;->$r8$lambda$JbnOiXNnUGgRwrRQRy5aIgSrvN0(Landroidx/car/app/HostDispatcher;)Landroidx/car/app/navigation/INavigationHost;

    move-result-object p0

    return-object p0
.end method
