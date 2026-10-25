.class public final synthetic Landroidx/car/app/AppManager$1$$ExternalSyntheticLambda1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/car/app/utils/RemoteUtils$HostCall;


# instance fields
.field public final synthetic f$0:Landroidx/car/app/ScreenManager;


# direct methods
.method public synthetic constructor <init>(Landroidx/car/app/ScreenManager;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/car/app/AppManager$1$$ExternalSyntheticLambda1;->f$0:Landroidx/car/app/ScreenManager;

    return-void
.end method


# virtual methods
.method public final dispatch()Ljava/lang/Object;
    .locals 0

    .line 0
    iget-object p0, p0, Landroidx/car/app/AppManager$1$$ExternalSyntheticLambda1;->f$0:Landroidx/car/app/ScreenManager;

    invoke-virtual {p0}, Landroidx/car/app/ScreenManager;->getTopTemplate()Landroidx/car/app/model/TemplateWrapper;

    move-result-object p0

    return-object p0
.end method
