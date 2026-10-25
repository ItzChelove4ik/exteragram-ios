.class public final synthetic Landroidx/car/app/AppManager$$ExternalSyntheticLambda3;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/car/app/HostCall;


# instance fields
.field public final synthetic f$0:Landroid/location/Location;


# direct methods
.method public synthetic constructor <init>(Landroid/location/Location;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/car/app/AppManager$$ExternalSyntheticLambda3;->f$0:Landroid/location/Location;

    return-void
.end method


# virtual methods
.method public final dispatch(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 0
    iget-object p0, p0, Landroidx/car/app/AppManager$$ExternalSyntheticLambda3;->f$0:Landroid/location/Location;

    check-cast p1, Landroidx/car/app/IAppHost;

    invoke-static {p0, p1}, Landroidx/car/app/AppManager;->$r8$lambda$cN4HC8fuyfXod6lQ1qmuMXVVog4(Landroid/location/Location;Landroidx/car/app/IAppHost;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
