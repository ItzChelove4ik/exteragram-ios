.class public final synthetic Landroidx/car/app/CarContext$$ExternalSyntheticLambda5;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/car/app/managers/ManagerFactory;


# instance fields
.field public final synthetic f$0:Landroidx/car/app/CarContext;


# direct methods
.method public synthetic constructor <init>(Landroidx/car/app/CarContext;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/car/app/CarContext$$ExternalSyntheticLambda5;->f$0:Landroidx/car/app/CarContext;

    return-void
.end method


# virtual methods
.method public final create()Landroidx/car/app/managers/Manager;
    .locals 0

    .line 0
    iget-object p0, p0, Landroidx/car/app/CarContext$$ExternalSyntheticLambda5;->f$0:Landroidx/car/app/CarContext;

    invoke-static {p0}, Landroidx/car/app/CarContext;->$r8$lambda$PLFyesAEudIcWeAhSdKCU9rg_lM(Landroidx/car/app/CarContext;)Landroidx/car/app/managers/ResultManager;

    const/4 p0, 0x0

    return-object p0
.end method
