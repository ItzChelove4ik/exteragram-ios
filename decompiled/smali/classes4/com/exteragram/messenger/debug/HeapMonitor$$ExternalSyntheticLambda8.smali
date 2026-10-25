.class public final synthetic Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda8;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:I

.field public final synthetic f$1:Lorg/telegram/ui/ActionBar/AlertDialog;


# direct methods
.method public synthetic constructor <init>(ILorg/telegram/ui/ActionBar/AlertDialog;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput p1, p0, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda8;->f$0:I

    iput-object p2, p0, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda8;->f$1:Lorg/telegram/ui/ActionBar/AlertDialog;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 0
    iget v0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda8;->f$0:I

    iget-object p0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda8;->f$1:Lorg/telegram/ui/ActionBar/AlertDialog;

    invoke-static {v0, p0}, Lcom/exteragram/messenger/debug/HeapMonitor;->$r8$lambda$5zrQMeJT7ZjO2ogkU3UeAGxPFBY(ILorg/telegram/ui/ActionBar/AlertDialog;)V

    return-void
.end method
