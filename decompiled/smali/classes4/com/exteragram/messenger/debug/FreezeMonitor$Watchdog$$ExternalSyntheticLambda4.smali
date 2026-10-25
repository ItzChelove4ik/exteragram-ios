.class public final synthetic Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$$ExternalSyntheticLambda4;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;


# direct methods
.method public synthetic constructor <init>(Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$$ExternalSyntheticLambda4;->f$0:Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 0

    .line 0
    iget-object p0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$$ExternalSyntheticLambda4;->f$0:Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;

    invoke-static {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->$r8$lambda$7qpSQkuRdaiCfCudJ_cXY00_KCw(Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;)V

    return-void
.end method
