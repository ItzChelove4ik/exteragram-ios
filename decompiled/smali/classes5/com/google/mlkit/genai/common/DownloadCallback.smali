.class public interface abstract Lcom/google/mlkit/genai/common/DownloadCallback;
.super Ljava/lang/Object;
.source "SourceFile"


# virtual methods
.method public onDownloadCompleted()V
    .locals 0

    return-void
.end method

.method public abstract onDownloadFailed(Lcom/google/mlkit/genai/common/GenAiException;)V
.end method

.method public abstract onDownloadProgress(J)V
.end method

.method public abstract onDownloadStarted(J)V
.end method
