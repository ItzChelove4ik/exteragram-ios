.class Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/TextureView$SurfaceTextureListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;


# direct methods
.method public constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V
    .locals 0

    .line 342
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onSurfaceTextureAvailable(Landroid/graphics/SurfaceTexture;II)V
    .locals 2

    .line 349
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetdiagnostics(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    move-result-object p1

    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "preview surface available: view="

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p2, "x"

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 350
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$mmaybeOpenCamera(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V

    return-void
.end method

.method public onSurfaceTextureDestroyed(Landroid/graphics/SurfaceTexture;)Z
    .locals 2

    .line 365
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetdiagnostics(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    move-result-object p1

    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "preview surface destroyed: active="

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetactive(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z

    move-result v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 366
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetactive(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z

    move-result p1

    if-eqz p1, :cond_0

    .line 367
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    new-instance p1, Ljava/lang/IllegalStateException;

    const-string v0, "Preview SurfaceTexture was destroyed"

    invoke-direct {p1, v0}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    invoke-static {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$mreportError(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Ljava/lang/Exception;)V

    :cond_0
    const/4 p0, 0x1

    return p0
.end method

.method public onSurfaceTextureSizeChanged(Landroid/graphics/SurfaceTexture;II)V
    .locals 2

    .line 359
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetdiagnostics(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    move-result-object p1

    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "preview surface size changed: view="

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p2, "x"

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 360
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$mupdatePreviewTransform(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V

    return-void
.end method

.method public onSurfaceTextureUpdated(Landroid/graphics/SurfaceTexture;)V
    .locals 10

    .line 374
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetawaitingSwitchPreviewFrame(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 375
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputawaitingSwitchPreviewFrame(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Z)V

    .line 376
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetdiagnostics(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    move-result-object v0

    const-string v1, "camera switch first preview frame"

    invoke-virtual {v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 377
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcallback(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;

    move-result-object v0

    invoke-interface {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;->onCameraSwitchFirstFrame()V

    .line 379
    :cond_0
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtimeNanos()J

    move-result-wide v0

    .line 380
    invoke-virtual {p1}, Landroid/graphics/SurfaceTexture;->getTimestamp()J

    move-result-wide v2

    .line 381
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetpreviewFpsStartedNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v4

    const-wide/16 v6, 0x0

    cmp-long p1, v4, v6

    if-nez p1, :cond_2

    .line 382
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1, v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputpreviewFpsStartedNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V

    .line 383
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetdiagnostics(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    move-result-object p1

    new-instance v4, Ljava/lang/StringBuilder;

    const-string v5, "preview frame delivery started: thread="

    invoke-direct {v4, v5}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    .line 384
    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/Thread;->getName()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, ", view="

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetpreviewView(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/view/TextureView;

    move-result-object v5

    .line 385
    invoke-virtual {v5}, Landroid/view/View;->getWidth()I

    move-result v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v5, "x"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetpreviewView(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/view/TextureView;

    move-result-object v5

    invoke-virtual {v5}, Landroid/view/View;->getHeight()I

    move-result v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v5, ", buffer="

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetpreviewSize(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/util/Size;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v5, ", attached="

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetpreviewView(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/view/TextureView;

    move-result-object v5

    .line 387
    invoke-virtual {v5}, Landroid/view/View;->isAttachedToWindow()Z

    move-result v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v5, ", shown="

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetpreviewView(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/view/TextureView;

    move-result-object v5

    .line 388
    invoke-virtual {v5}, Landroid/view/View;->isShown()Z

    move-result v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v5, ", alpha="

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetpreviewView(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/view/TextureView;

    move-result-object v5

    .line 389
    invoke-virtual {v5}, Landroid/view/View;->getAlpha()F

    move-result v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string v5, ", hardwareAccelerated="

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetpreviewView(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/view/TextureView;

    move-result-object v5

    .line 390
    invoke-virtual {v5}, Landroid/view/View;->isHardwareAccelerated()Z

    move-result v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v5, ", displayRefreshRate="

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 391
    iget-object v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetpreviewView(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/view/TextureView;

    move-result-object v5

    invoke-virtual {v5}, Landroid/view/View;->getDisplay()Landroid/view/Display;

    move-result-object v5

    if-nez v5, :cond_1

    .line 392
    const-string v5, "unknown"

    goto :goto_0

    :cond_1
    iget-object v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetpreviewView(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/view/TextureView;

    move-result-object v5

    invoke-virtual {v5}, Landroid/view/View;->getDisplay()Landroid/view/Display;

    move-result-object v5

    invoke-virtual {v5}, Landroid/view/Display;->getRefreshRate()F

    move-result v5

    invoke-static {v5}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v5

    :goto_0
    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    .line 383
    invoke-virtual {p1, v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 394
    :cond_2
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetpreviewLastCallbackNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v4

    cmp-long p1, v4, v6

    if-eqz p1, :cond_3

    .line 395
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetpreviewLastCallbackNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v4

    sub-long v4, v0, v4

    invoke-static {p1, v4, v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$mrecordPreviewInterval(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V

    .line 397
    :cond_3
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1, v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputpreviewLastCallbackNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V

    .line 398
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetpreviewLastTimestampNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v4

    cmp-long p1, v2, v4

    .line 402
    iget-object v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    const-wide/16 v8, 0x1

    if-lez p1, :cond_5

    .line 399
    invoke-static {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetpreviewTimestampFirstNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v4

    cmp-long p1, v4, v6

    if-nez p1, :cond_4

    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1, v2, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputpreviewTimestampFirstNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V

    .line 400
    :cond_4
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1, v2, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputpreviewTimestampLastNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V

    .line 401
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetpreviewTimestampFrames(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v4

    add-long/2addr v4, v8

    invoke-static {p1, v4, v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputpreviewTimestampFrames(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V

    goto :goto_1

    .line 402
    :cond_5
    invoke-static {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetpreviewLastTimestampNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v4

    cmp-long p1, v4, v6

    if-eqz p1, :cond_6

    .line 403
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetpreviewTimestampNonMonotonic(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v4

    add-long/2addr v4, v8

    invoke-static {p1, v4, v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputpreviewTimestampNonMonotonic(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V

    .line 405
    :cond_6
    :goto_1
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1, v2, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputpreviewLastTimestampNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V

    .line 406
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetpreviewFrames(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v2

    add-long/2addr v2, v8

    invoke-static {p1, v2, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputpreviewFrames(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V

    .line 407
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetpreviewFpsStartedNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v2

    sub-long v2, v0, v2

    const-wide v4, 0x12a05f200L

    cmp-long p1, v2, v4

    if-ltz p1, :cond_7

    .line 409
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p0, v0, v1, v2, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$mlogPreviewFrameMetrics(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;JJ)V

    :cond_7
    return-void
.end method
