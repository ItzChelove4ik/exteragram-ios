.class Lcom/exteragram/messenger/ai/ui/OnDeviceModelDialogs$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/ai/ui/OnDeviceModelDialogs;->createListener(I)Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic val$modelKey:I


# direct methods
.method public constructor <init>(I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 51
    iput p1, p0, Lcom/exteragram/messenger/ai/ui/OnDeviceModelDialogs$1;->val$modelKey:I

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onCompleted()V
    .locals 0

    .line 64
    iget p0, p0, Lcom/exteragram/messenger/ai/ui/OnDeviceModelDialogs$1;->val$modelKey:I

    invoke-static {p0}, Lcom/exteragram/messenger/ai/ui/OnDeviceModelNotifications;->showCompleted(I)V

    return-void
.end method

.method public onFailed(Ljava/lang/Exception;)V
    .locals 1

    .line 69
    const-string v0, "AI_ON_DEVICE_DOWNLOAD_FAILED"

    invoke-static {v0, p1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 70
    iget p0, p0, Lcom/exteragram/messenger/ai/ui/OnDeviceModelDialogs$1;->val$modelKey:I

    invoke-static {p0}, Lcom/exteragram/messenger/ai/ui/OnDeviceModelNotifications;->cancel(I)V

    return-void
.end method

.method public onProgress(FJ)V
    .locals 0

    .line 59
    iget p0, p0, Lcom/exteragram/messenger/ai/ui/OnDeviceModelDialogs$1;->val$modelKey:I

    const/4 p2, 0x0

    cmpg-float p2, p1, p2

    if-gez p2, :cond_0

    const/4 p1, -0x1

    goto :goto_0

    :cond_0
    const/high16 p2, 0x42c80000    # 100.0f

    mul-float/2addr p1, p2

    float-to-int p1, p1

    :goto_0
    invoke-static {p0, p1}, Lcom/exteragram/messenger/ai/ui/OnDeviceModelNotifications;->showProgress(II)V

    return-void
.end method

.method public onStarted(J)V
    .locals 0

    .line 54
    iget p0, p0, Lcom/exteragram/messenger/ai/ui/OnDeviceModelDialogs$1;->val$modelKey:I

    const/4 p1, -0x1

    invoke-static {p0, p1}, Lcom/exteragram/messenger/ai/ui/OnDeviceModelNotifications;->showProgress(II)V

    return-void
.end method
