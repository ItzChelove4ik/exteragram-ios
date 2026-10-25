.class public final synthetic Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda9;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function1;


# instance fields
.field public final synthetic f$0:[J

.field public final synthetic f$1:[Ljava/lang/Class;


# direct methods
.method public synthetic constructor <init>([J[Ljava/lang/Class;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda9;->f$0:[J

    iput-object p2, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda9;->f$1:[Ljava/lang/Class;

    return-void
.end method


# virtual methods
.method public final invoke(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 1

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda9;->f$0:[J

    iget-object p0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$$ExternalSyntheticLambda9;->f$1:[Ljava/lang/Class;

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    invoke-static {v0, p0, p1}, Lcom/exteragram/messenger/debug/FreezeMonitor;->$r8$lambda$_4ZBAwpZNPPivCMaGPNiCgkjd9c([J[Ljava/lang/Class;I)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0
.end method
