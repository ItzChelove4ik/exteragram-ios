.class public final synthetic Landroidx/car/app/CarAppService$$ExternalSyntheticLambda1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Landroidx/car/app/CarAppService;

.field public final synthetic f$1:Landroidx/car/app/SessionInfo;


# direct methods
.method public synthetic constructor <init>(Landroidx/car/app/CarAppService;Landroidx/car/app/SessionInfo;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/car/app/CarAppService$$ExternalSyntheticLambda1;->f$0:Landroidx/car/app/CarAppService;

    iput-object p2, p0, Landroidx/car/app/CarAppService$$ExternalSyntheticLambda1;->f$1:Landroidx/car/app/SessionInfo;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 0
    iget-object v0, p0, Landroidx/car/app/CarAppService$$ExternalSyntheticLambda1;->f$0:Landroidx/car/app/CarAppService;

    iget-object p0, p0, Landroidx/car/app/CarAppService$$ExternalSyntheticLambda1;->f$1:Landroidx/car/app/SessionInfo;

    invoke-static {v0, p0}, Landroidx/car/app/CarAppService;->$r8$lambda$BoF8lRhyLvMMyIqziQS6Yiscbqo(Landroidx/car/app/CarAppService;Landroidx/car/app/SessionInfo;)V

    return-void
.end method
