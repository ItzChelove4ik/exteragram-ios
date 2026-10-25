.class public final synthetic Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher$$ExternalSyntheticLambda3;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:J

.field public final synthetic f$1:J


# direct methods
.method public synthetic constructor <init>(JJ)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-wide p1, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher$$ExternalSyntheticLambda3;->f$0:J

    iput-wide p3, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher$$ExternalSyntheticLambda3;->f$1:J

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 4

    .line 0
    iget-wide v0, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher$$ExternalSyntheticLambda3;->f$0:J

    iget-wide v2, p0, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher$$ExternalSyntheticLambda3;->f$1:J

    invoke-static {v0, v1, v2, v3}, Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;->$r8$lambda$Glvfehde9YHk6gQtUreR-r-Mb9g(JJ)V

    return-void
.end method
