.class public final synthetic Lcom/exteragram/messenger/appicons/AppIconController$$ExternalSyntheticLambda0;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Ljava/util/List;

.field public final synthetic f$1:Lcom/exteragram/messenger/appicons/AppIcon;


# direct methods
.method public synthetic constructor <init>(Ljava/util/List;Lcom/exteragram/messenger/appicons/AppIcon;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/appicons/AppIconController$$ExternalSyntheticLambda0;->f$0:Ljava/util/List;

    iput-object p2, p0, Lcom/exteragram/messenger/appicons/AppIconController$$ExternalSyntheticLambda0;->f$1:Lcom/exteragram/messenger/appicons/AppIcon;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/AppIconController$$ExternalSyntheticLambda0;->f$0:Ljava/util/List;

    iget-object p0, p0, Lcom/exteragram/messenger/appicons/AppIconController$$ExternalSyntheticLambda0;->f$1:Lcom/exteragram/messenger/appicons/AppIcon;

    invoke-static {v0, p0}, Lcom/exteragram/messenger/appicons/AppIconController;->$r8$lambda$5uIc0UfuYc-xsOt72Pr_KfKeTK8(Ljava/util/List;Lcom/exteragram/messenger/appicons/AppIcon;)V

    return-void
.end method
