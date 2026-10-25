.class public final synthetic Landroidx/car/app/Screen$$ExternalSyntheticLambda1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Landroidx/car/app/Screen;

.field public final synthetic f$1:Landroidx/lifecycle/Lifecycle$Event;


# direct methods
.method public synthetic constructor <init>(Landroidx/car/app/Screen;Landroidx/lifecycle/Lifecycle$Event;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/car/app/Screen$$ExternalSyntheticLambda1;->f$0:Landroidx/car/app/Screen;

    iput-object p2, p0, Landroidx/car/app/Screen$$ExternalSyntheticLambda1;->f$1:Landroidx/lifecycle/Lifecycle$Event;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 0
    iget-object v0, p0, Landroidx/car/app/Screen$$ExternalSyntheticLambda1;->f$0:Landroidx/car/app/Screen;

    iget-object p0, p0, Landroidx/car/app/Screen$$ExternalSyntheticLambda1;->f$1:Landroidx/lifecycle/Lifecycle$Event;

    invoke-static {v0, p0}, Landroidx/car/app/Screen;->$r8$lambda$1fy9bSyOIB5ckm4-u8XzevkC8wY(Landroidx/car/app/Screen;Landroidx/lifecycle/Lifecycle$Event;)V

    return-void
.end method
