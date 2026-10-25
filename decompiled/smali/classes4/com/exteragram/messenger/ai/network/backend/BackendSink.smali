.class public interface abstract Lcom/exteragram/messenger/ai/network/backend/BackendSink;
.super Ljava/lang/Object;
.source "SourceFile"


# virtual methods
.method public abstract isActive()Z
.end method

.method public abstract onCancellable(Ljava/lang/Runnable;)V
.end method

.method public abstract onChunk(Ljava/lang/String;)V
.end method

.method public abstract onComplete(Ljava/lang/String;)V
.end method

.method public abstract onError(ILjava/lang/String;)V
.end method

.method public abstract onThinking()V
.end method
