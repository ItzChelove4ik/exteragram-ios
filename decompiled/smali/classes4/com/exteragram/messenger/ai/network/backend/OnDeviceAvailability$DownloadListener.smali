.class public interface abstract Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x609
    name = "DownloadListener"
.end annotation


# virtual methods
.method public abstract onCompleted()V
.end method

.method public abstract onFailed(Ljava/lang/Exception;)V
.end method

.method public abstract onProgress(FJ)V
.end method

.method public abstract onStarted(J)V
.end method
