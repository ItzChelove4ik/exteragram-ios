.class public final synthetic Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher$$ExternalSyntheticLambda4;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;

.field public final synthetic f$1:J


# direct methods
.method public synthetic constructor <init>(Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;J)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher$$ExternalSyntheticLambda4;->f$0:Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;

    iput-wide p2, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher$$ExternalSyntheticLambda4;->f$1:J

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 3

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher$$ExternalSyntheticLambda4;->f$0:Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;

    iget-wide v1, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher$$ExternalSyntheticLambda4;->f$1:J

    invoke-static {v0, v1, v2}, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->$r8$lambda$i0uVHpCZaV6c-3J4CwvOvyrTDcQ(Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;J)V

    return-void
.end method
