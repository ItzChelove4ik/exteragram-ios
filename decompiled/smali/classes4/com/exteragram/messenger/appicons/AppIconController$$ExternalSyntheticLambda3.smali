.class public final synthetic Lcom/exteragram/messenger/appicons/AppIconController$$ExternalSyntheticLambda3;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lcom/exteragram/messenger/appicons/AppIcon;

.field public final synthetic f$1:Ljava/util/List;


# direct methods
.method public synthetic constructor <init>(Lcom/exteragram/messenger/appicons/AppIcon;Ljava/util/List;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/appicons/AppIconController$$ExternalSyntheticLambda3;->f$0:Lcom/exteragram/messenger/appicons/AppIcon;

    iput-object p2, p0, Lcom/exteragram/messenger/appicons/AppIconController$$ExternalSyntheticLambda3;->f$1:Ljava/util/List;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/AppIconController$$ExternalSyntheticLambda3;->f$0:Lcom/exteragram/messenger/appicons/AppIcon;

    iget-object p0, p0, Lcom/exteragram/messenger/appicons/AppIconController$$ExternalSyntheticLambda3;->f$1:Ljava/util/List;

    invoke-static {v0, p0}, Lcom/exteragram/messenger/appicons/AppIconController;->$r8$lambda$e50DBscmx5OEPuYBDGfVTLU4Vmo(Lcom/exteragram/messenger/appicons/AppIcon;Ljava/util/List;)V

    return-void
.end method
