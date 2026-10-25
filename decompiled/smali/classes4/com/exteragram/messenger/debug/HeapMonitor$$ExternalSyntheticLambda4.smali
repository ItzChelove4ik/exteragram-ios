.class public final synthetic Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda4;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function1;


# instance fields
.field public final synthetic f$0:Lorg/telegram/ui/ActionBar/AlertDialog;


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/ui/ActionBar/AlertDialog;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda4;->f$0:Lorg/telegram/ui/ActionBar/AlertDialog;

    return-void
.end method


# virtual methods
.method public final invoke(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 0
    iget-object p0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$$ExternalSyntheticLambda4;->f$0:Lorg/telegram/ui/ActionBar/AlertDialog;

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    invoke-static {p0, p1}, Lcom/exteragram/messenger/debug/HeapMonitor;->$r8$lambda$ONRMsNbfuk3xDA6TyEiuMcLOfCY(Lorg/telegram/ui/ActionBar/AlertDialog;I)Lkotlin/Unit;

    move-result-object p0

    return-object p0
.end method
