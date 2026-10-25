.class public final synthetic Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda5;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/telegram/ui/ActionBar/AlertDialog;

.field public final synthetic f$1:Ljava/lang/Object;

.field public final synthetic f$2:Landroid/app/Activity;


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/ui/ActionBar/AlertDialog;Ljava/lang/Object;Landroid/app/Activity;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda5;->f$0:Lorg/telegram/ui/ActionBar/AlertDialog;

    iput-object p2, p0, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda5;->f$1:Ljava/lang/Object;

    iput-object p3, p0, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda5;->f$2:Landroid/app/Activity;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 2

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda5;->f$0:Lorg/telegram/ui/ActionBar/AlertDialog;

    iget-object v1, p0, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda5;->f$1:Ljava/lang/Object;

    iget-object p0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda5;->f$2:Landroid/app/Activity;

    invoke-static {v0, v1, p0}, Lcom/exteragram/messenger/debug/HeapMonitor;->$r8$lambda$euY017UpxHSPivzCOWIKbGRnYdQ(Lorg/telegram/ui/ActionBar/AlertDialog;Ljava/lang/Object;Landroid/app/Activity;)V

    return-void
.end method
