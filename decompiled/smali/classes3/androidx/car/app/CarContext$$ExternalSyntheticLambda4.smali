.class public final synthetic Landroidx/car/app/CarContext$$ExternalSyntheticLambda4;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/car/app/managers/ManagerFactory;


# instance fields
.field public final synthetic f$0:Landroidx/car/app/CarContext;

.field public final synthetic f$1:Landroidx/car/app/HostDispatcher;


# direct methods
.method public synthetic constructor <init>(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/car/app/CarContext$$ExternalSyntheticLambda4;->f$0:Landroidx/car/app/CarContext;

    iput-object p2, p0, Landroidx/car/app/CarContext$$ExternalSyntheticLambda4;->f$1:Landroidx/car/app/HostDispatcher;

    return-void
.end method


# virtual methods
.method public final create()Landroidx/car/app/managers/Manager;
    .locals 1

    .line 0
    iget-object v0, p0, Landroidx/car/app/CarContext$$ExternalSyntheticLambda4;->f$0:Landroidx/car/app/CarContext;

    iget-object p0, p0, Landroidx/car/app/CarContext$$ExternalSyntheticLambda4;->f$1:Landroidx/car/app/HostDispatcher;

    invoke-static {v0, p0}, Landroidx/car/app/CarContext;->$r8$lambda$oRslYWvJSzlVhA1P74n6gPzrVNw(Landroidx/car/app/CarContext;Landroidx/car/app/HostDispatcher;)Landroidx/car/app/hardware/CarHardwareManager;

    move-result-object p0

    return-object p0
.end method
