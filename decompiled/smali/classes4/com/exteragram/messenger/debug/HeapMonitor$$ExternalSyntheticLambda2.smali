.class public final synthetic Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda2;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function0;


# instance fields
.field public final synthetic f$0:Lorg/telegram/ui/ActionBar/AlertDialog;

.field public final synthetic f$1:Landroid/app/Activity;


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/ui/ActionBar/AlertDialog;Landroid/app/Activity;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda2;->f$0:Lorg/telegram/ui/ActionBar/AlertDialog;

    iput-object p2, p0, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda2;->f$1:Landroid/app/Activity;

    return-void
.end method


# virtual methods
.method public final invoke()Ljava/lang/Object;
    .locals 1

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda2;->f$0:Lorg/telegram/ui/ActionBar/AlertDialog;

    iget-object p0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda2;->f$1:Landroid/app/Activity;

    invoke-static {v0, p0}, Lcom/exteragram/messenger/debug/HeapMonitor;->$r8$lambda$4bQhVXYDNdN6H_VjUQxdVfUhGBo(Lorg/telegram/ui/ActionBar/AlertDialog;Landroid/app/Activity;)Lkotlin/Unit;

    move-result-object p0

    return-object p0
.end method
