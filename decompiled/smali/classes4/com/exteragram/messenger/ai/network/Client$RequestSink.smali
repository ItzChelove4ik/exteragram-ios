.class final Lcom/exteragram/messenger/ai/network/Client$RequestSink;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/exteragram/messenger/ai/network/backend/BackendSink;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/ai/network/Client;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x11
    name = "RequestSink"
.end annotation


# instance fields
.field private final callback:Lcom/exteragram/messenger/ai/network/GenerationCallback;

.field private final currentMessage:Lcom/exteragram/messenger/ai/data/Message;

.field private final requestId:Ljava/lang/String;

.field private final saveHistory:Z

.field final synthetic this$0:Lcom/exteragram/messenger/ai/network/Client;


# direct methods
.method public constructor <init>(Lcom/exteragram/messenger/ai/network/Client;Ljava/lang/String;Lcom/exteragram/messenger/ai/data/Message;ZLcom/exteragram/messenger/ai/network/GenerationCallback;)V
    .locals 0

    .line 365
    iput-object p1, p0, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->this$0:Lcom/exteragram/messenger/ai/network/Client;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 366
    iput-object p2, p0, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->requestId:Ljava/lang/String;

    .line 367
    iput-object p3, p0, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->currentMessage:Lcom/exteragram/messenger/ai/data/Message;

    .line 368
    iput-boolean p4, p0, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->saveHistory:Z

    .line 369
    iput-object p5, p0, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->callback:Lcom/exteragram/messenger/ai/network/GenerationCallback;

    return-void
.end method


# virtual methods
.method public isActive()Z
    .locals 1

    .line 374
    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->this$0:Lcom/exteragram/messenger/ai/network/Client;

    invoke-static {v0}, Lcom/exteragram/messenger/ai/network/Client;->-$$Nest$fgetactiveRequests(Lcom/exteragram/messenger/ai/network/Client;)Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v0

    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->requestId:Ljava/lang/String;

    invoke-virtual {v0, p0}, Ljava/util/concurrent/ConcurrentHashMap;->containsKey(Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method public onCancellable(Ljava/lang/Runnable;)V
    .locals 1

    if-eqz p1, :cond_0

    .line 380
    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->this$0:Lcom/exteragram/messenger/ai/network/Client;

    invoke-static {v0}, Lcom/exteragram/messenger/ai/network/Client;->-$$Nest$fgetactiveCancellables(Lcom/exteragram/messenger/ai/network/Client;)Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v0

    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->requestId:Ljava/lang/String;

    invoke-virtual {v0, p0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_0
    return-void
.end method

.method public onChunk(Ljava/lang/String;)V
    .locals 2

    .line 391
    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->this$0:Lcom/exteragram/messenger/ai/network/Client;

    iget-object v1, p0, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->requestId:Ljava/lang/String;

    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->callback:Lcom/exteragram/messenger/ai/network/GenerationCallback;

    invoke-static {v0, v1, p1, p0}, Lcom/exteragram/messenger/ai/network/Client;->-$$Nest$msendStreamChunk(Lcom/exteragram/messenger/ai/network/Client;Ljava/lang/String;Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;)V

    return-void
.end method

.method public onComplete(Ljava/lang/String;)V
    .locals 2

    .line 396
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->isActive()Z

    move-result v0

    if-nez v0, :cond_0

    return-void

    .line 399
    :cond_0
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_1

    const/16 p1, 0xcc

    .line 400
    const-string v0, "Response body is empty"

    invoke-virtual {p0, p1, v0}, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->onError(ILjava/lang/String;)V

    return-void

    .line 403
    :cond_1
    iget-boolean v0, p0, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->saveHistory:Z

    if-eqz v0, :cond_2

    .line 404
    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->this$0:Lcom/exteragram/messenger/ai/network/Client;

    iget-object v1, p0, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->currentMessage:Lcom/exteragram/messenger/ai/data/Message;

    invoke-static {v0, v1, p1}, Lcom/exteragram/messenger/ai/network/Client;->-$$Nest$msaveTurn(Lcom/exteragram/messenger/ai/network/Client;Lcom/exteragram/messenger/ai/data/Message;Ljava/lang/String;)V

    .line 406
    :cond_2
    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->this$0:Lcom/exteragram/messenger/ai/network/Client;

    iget-object v1, p0, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->requestId:Ljava/lang/String;

    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->callback:Lcom/exteragram/messenger/ai/network/GenerationCallback;

    invoke-static {v0, v1, p0, p1}, Lcom/exteragram/messenger/ai/network/Client;->-$$Nest$mnotifyResponseAndFinish(Lcom/exteragram/messenger/ai/network/Client;Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;Ljava/lang/String;)V

    return-void
.end method

.method public onError(ILjava/lang/String;)V
    .locals 2

    .line 411
    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->this$0:Lcom/exteragram/messenger/ai/network/Client;

    iget-object v1, p0, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->requestId:Ljava/lang/String;

    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->callback:Lcom/exteragram/messenger/ai/network/GenerationCallback;

    invoke-static {v0, v1, p0, p1, p2}, Lcom/exteragram/messenger/ai/network/Client;->-$$Nest$mnotifyErrorAndFinish(Lcom/exteragram/messenger/ai/network/Client;Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;ILjava/lang/String;)V

    return-void
.end method

.method public onThinking()V
    .locals 2

    .line 386
    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->this$0:Lcom/exteragram/messenger/ai/network/Client;

    iget-object v1, p0, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->requestId:Ljava/lang/String;

    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/Client$RequestSink;->callback:Lcom/exteragram/messenger/ai/network/GenerationCallback;

    invoke-static {v0, v1, p0}, Lcom/exteragram/messenger/ai/network/Client;->-$$Nest$mnotifyThinking(Lcom/exteragram/messenger/ai/network/Client;Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;)V

    return-void
.end method
