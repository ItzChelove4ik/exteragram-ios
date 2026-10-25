.class public final synthetic Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda7;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Ljava/lang/Object;

.field public final synthetic f$1:Landroid/app/Activity;


# direct methods
.method public synthetic constructor <init>(Ljava/lang/Object;Landroid/app/Activity;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda7;->f$0:Ljava/lang/Object;

    iput-object p2, p0, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda7;->f$1:Landroid/app/Activity;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda7;->f$0:Ljava/lang/Object;

    iget-object p0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$$ExternalSyntheticLambda7;->f$1:Landroid/app/Activity;

    invoke-static {v0, p0}, Lcom/exteragram/messenger/debug/LoadMonitor;->$r8$lambda$yclvq93EyksPCxohhOabqVuRpcY(Ljava/lang/Object;Landroid/app/Activity;)V

    return-void
.end method
