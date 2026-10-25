.class Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/mlkit/genai/common/DownloadCallback;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->runDownload(ILcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic val$listener:Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;

.field final synthetic val$modelKey:I

.field final synthetic val$total:[J


# direct methods
.method public constructor <init>([JLcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 431
    iput-object p1, p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$1;->val$total:[J

    iput-object p2, p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$1;->val$listener:Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;

    iput p3, p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$1;->val$modelKey:I

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onDownloadFailed(Lcom/google/mlkit/genai/common/GenAiException;)V
    .locals 0

    .line 452
    const-string p0, "AI_ON_DEVICE_DOWNLOAD_FAILED"

    invoke-static {p0, p1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void
.end method

.method public onDownloadProgress(J)V
    .locals 5

    .line 440
    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$1;->val$total:[J

    const/4 v1, 0x0

    aget-wide v1, v0, v1

    const-wide/16 v3, 0x0

    cmp-long v0, v1, v3

    if-lez v0, :cond_0

    long-to-float v0, p1

    long-to-float v1, v1

    div-float/2addr v0, v1

    const/high16 v1, 0x3f800000    # 1.0f

    invoke-static {v1, v0}, Ljava/lang/Math;->min(FF)F

    move-result v0

    goto :goto_0

    :cond_0
    const/high16 v0, -0x40800000    # -1.0f

    :goto_0
    const/4 v1, 0x0

    cmpg-float v1, v0, v1

    if-gez v1, :cond_1

    const/4 v1, -0x1

    goto :goto_1

    :cond_1
    const/high16 v1, 0x42c80000    # 100.0f

    mul-float/2addr v1, v0

    float-to-int v1, v1

    .line 442
    :goto_1
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->-$$Nest$sfgetdownloadPercents()Ljava/util/Map;

    move-result-object v2

    iget v3, p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$1;->val$modelKey:I

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-interface {v2, v3, v4}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Integer;

    if-eqz v2, :cond_2

    .line 443
    invoke-virtual {v2}, Ljava/lang/Integer;->intValue()I

    move-result v2

    if-ne v2, v1, :cond_2

    return-void

    .line 446
    :cond_2
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->-$$Nest$smnotifyServicesUpdated()V

    .line 447
    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$1;->val$listener:Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;

    invoke-interface {p0, v0, p1, p2}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;->onProgress(FJ)V

    return-void
.end method

.method public onDownloadStarted(J)V
    .locals 2

    .line 434
    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$1;->val$total:[J

    const/4 v1, 0x0

    aput-wide p1, v0, v1

    .line 435
    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$1;->val$listener:Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;

    invoke-interface {p0, p1, p2}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;->onStarted(J)V

    return-void
.end method
