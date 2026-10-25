.class public final synthetic Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$$ExternalSyntheticLambda2;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Ljava/util/concurrent/CountDownLatch;


# direct methods
.method public synthetic constructor <init>(Ljava/util/concurrent/CountDownLatch;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$$ExternalSyntheticLambda2;->f$0:Ljava/util/concurrent/CountDownLatch;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 0

    .line 0
    iget-object p0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$$ExternalSyntheticLambda2;->f$0:Ljava/util/concurrent/CountDownLatch;

    invoke-static {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->$r8$lambda$S_pSjC2swheQoZFMbm_beXGVDIQ(Ljava/util/concurrent/CountDownLatch;)V

    return-void
.end method
