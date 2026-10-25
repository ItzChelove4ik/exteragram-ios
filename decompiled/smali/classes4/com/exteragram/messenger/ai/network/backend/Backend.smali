.class public interface abstract Lcom/exteragram/messenger/ai/network/backend/Backend;
.super Ljava/lang/Object;
.source "SourceFile"


# virtual methods
.method public abstract execute(Lcom/exteragram/messenger/ai/network/backend/BackendRequest;Lcom/exteragram/messenger/ai/network/backend/BackendSink;)V
.end method

.method public abstract getHistoryCharsLimit()I
.end method

.method public abstract getHistoryMessagesLimit()I
.end method

.method public abstract getMaxOutputTokens()I
.end method

.method public abstract getMaxTemperature()F
.end method

.method public abstract supportsImages()Z
.end method
