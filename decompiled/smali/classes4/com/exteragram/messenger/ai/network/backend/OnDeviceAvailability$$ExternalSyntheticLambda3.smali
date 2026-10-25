.class public final synthetic Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$$ExternalSyntheticLambda3;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Ljava/lang/Runnable;

.field public final synthetic f$1:Ljava/util/concurrent/ExecutorService;


# direct methods
.method public synthetic constructor <init>(Ljava/lang/Runnable;Ljava/util/concurrent/ExecutorService;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$$ExternalSyntheticLambda3;->f$0:Ljava/lang/Runnable;

    iput-object p2, p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$$ExternalSyntheticLambda3;->f$1:Ljava/util/concurrent/ExecutorService;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$$ExternalSyntheticLambda3;->f$0:Ljava/lang/Runnable;

    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$$ExternalSyntheticLambda3;->f$1:Ljava/util/concurrent/ExecutorService;

    invoke-static {v0, p0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->$r8$lambda$edA7BpxC7ENFv9jOjeLPcqoKwKs(Ljava/lang/Runnable;Ljava/util/concurrent/ExecutorService;)V

    return-void
.end method
