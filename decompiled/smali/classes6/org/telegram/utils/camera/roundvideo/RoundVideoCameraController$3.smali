.class Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;
.super Landroid/hardware/camera2/CameraCaptureSession$StateCallback;
.source "SourceFile"


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
.method public static synthetic $r8$lambda$kR5RFBkZkJg0TL-6sNcWwFHrUog(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V
    .locals 0

    .line 0
    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$mupdatePreviewTransform(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V

    return-void
.end method

.method public constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V
    .locals 0

    .line 593
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-direct {p0}, Landroid/hardware/camera2/CameraCaptureSession$StateCallback;-><init>()V

    return-void
.end method


# virtual methods
.method public onClosed(Landroid/hardware/camera2/CameraCaptureSession;)V
    .locals 1

    .line 660
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureSession(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/hardware/camera2/CameraCaptureSession;

    move-result-object v0

    if-eq v0, p1, :cond_0

    return-void

    .line 661
    :cond_0
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    const/4 v0, 0x0

    invoke-static {p1, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputcaptureSession(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Landroid/hardware/camera2/CameraCaptureSession;)V

    .line 662
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputrepeatingBuilder(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Landroid/hardware/camera2/CaptureRequest$Builder;)V

    .line 663
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    const/4 v0, 0x0

    invoke-static {p1, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputtorchVerificationPending(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Z)V

    .line 664
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetdiagnostics(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    move-result-object p0

    const-string p1, "capture session closed"

    invoke-virtual {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    return-void
.end method

.method public onConfigureFailed(Landroid/hardware/camera2/CameraCaptureSession;)V
    .locals 2

    .line 640
    invoke-virtual {p1}, Landroid/hardware/camera2/CameraCaptureSession;->close()V

    .line 641
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureSession(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/hardware/camera2/CameraCaptureSession;

    move-result-object v0

    const/4 v1, 0x0

    if-ne v0, p1, :cond_0

    .line 642
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputcaptureSession(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Landroid/hardware/camera2/CameraCaptureSession;)V

    .line 643
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputrepeatingBuilder(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Landroid/hardware/camera2/CaptureRequest$Builder;)V

    .line 645
    :cond_0
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetactive(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z

    move-result p1

    if-eqz p1, :cond_3

    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcameraDevice(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/hardware/camera2/CameraDevice;

    move-result-object p1

    if-eqz p1, :cond_3

    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetswitchingCamera(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z

    move-result p1

    if-nez p1, :cond_3

    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetstopping(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z

    move-result p1

    if-eqz p1, :cond_1

    goto :goto_0

    .line 649
    :cond_1
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetactiveFrameRate(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    move-result-object p1

    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->FPS_60:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    .line 652
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    if-ne p1, v0, :cond_2

    .line 650
    const-string p1, "60 fps session configuration failed"

    invoke-static {p0, p1, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$mfallbackTo30Fps(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Ljava/lang/String;Ljava/lang/Exception;)V

    return-void

    .line 652
    :cond_2
    new-instance p1, Ljava/lang/IllegalStateException;

    const-string v0, "Camera capture session configuration failed"

    invoke-direct {p1, v0}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    invoke-static {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$mreportError(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Ljava/lang/Exception;)V

    return-void

    .line 646
    :cond_3
    :goto_0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetdiagnostics(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    move-result-object p0

    const-string p1, "stale capture session configuration failure ignored"

    invoke-virtual {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    return-void
.end method

.method public onConfigured(Landroid/hardware/camera2/CameraCaptureSession;)V
    .locals 9

    .line 596
    const-string v0, ", switchElapsedMs="

    const-string v1, "capture session configured: facing="

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetactive(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z

    move-result v2

    if-eqz v2, :cond_5

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcameraDevice(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/hardware/camera2/CameraDevice;

    move-result-object v2

    if-nez v2, :cond_0

    goto/16 :goto_4

    .line 600
    :cond_0
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v2, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputcaptureSession(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Landroid/hardware/camera2/CameraCaptureSession;)V

    .line 602
    :try_start_0
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetselectedFacing(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    move-result-object v2

    invoke-static {p1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputactiveFacing(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V

    .line 603
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    const/4 v2, 0x1

    invoke-static {p1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$mcreateRepeatingRequestBuilder(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Z)Landroid/hardware/camera2/CaptureRequest$Builder;

    move-result-object v2

    invoke-static {p1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputrepeatingBuilder(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Landroid/hardware/camera2/CaptureRequest$Builder;)V

    .line 604
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetglProcessor(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

    move-result-object p1

    if-eqz p1, :cond_1

    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetglProcessor(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

    move-result-object p1

    invoke-virtual {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->onCameraSessionConfigured()V

    goto :goto_0

    :catch_0
    move-exception v0

    move-object p1, v0

    goto/16 :goto_2

    .line 605
    :cond_1
    :goto_0
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetnotifySwitchCompletion(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z

    move-result p1

    .line 606
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    const/4 v3, 0x0

    invoke-static {v2, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputnotifySwitchCompletion(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Z)V

    .line 607
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v2, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputawaitingSwitchPreviewFrame(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Z)V

    .line 608
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$msubmitRepeatingRequest(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V

    .line 609
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetdiagnostics(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    move-result-object v2

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetactiveFacing(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    move-result-object v1

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v1, ", fpsRange="

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetactiveFpsRange(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/util/Range;

    move-result-object v1

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v1, ", elapsedMs="

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureSessionRequestedNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v4

    .line 611
    invoke-static {v4, v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$smelapsedMs(J)J

    move-result-wide v4

    invoke-virtual {v3, v4, v5}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v1, ", segmentElapsedMs="

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetsegmentStartedNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v4

    .line 612
    invoke-static {v4, v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$smelapsedMs(J)J

    move-result-wide v4

    invoke-virtual {v3, v4, v5}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    if-eqz p1, :cond_2

    .line 614
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcameraSwitchStartedNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v4

    invoke-static {v4, v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$smelapsedMs(J)J

    move-result-wide v4

    invoke-virtual {v1, v4, v5}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    goto :goto_1

    :cond_2
    const-string v0, ""

    :goto_1
    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 609
    invoke-virtual {v2, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 615
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetpreviewView(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/view/TextureView;

    move-result-object v0

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    new-instance v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3$$ExternalSyntheticLambda0;

    invoke-direct {v2, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V

    invoke-virtual {v0, v2}, Landroid/view/View;->post(Ljava/lang/Runnable;)Z

    .line 616
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcallback(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;

    move-result-object v0

    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetactiveFacing(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    move-result-object v2

    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetactiveCameraResolution(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    move-result-object v3

    iget-object v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetactiveFrameRate(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    move-result-object v4

    iget-object v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetpreviewSize(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/util/Size;

    move-result-object v5

    iget-object v6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v6}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetrecordingSize(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/util/Size;

    move-result-object v6

    iget-object v7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v7}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetmaximumZoomRatio(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)F

    move-result v7

    iget-object v8, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    .line 624
    invoke-static {v8}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$misTorchAvailable(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z

    move-result v8

    invoke-direct/range {v1 .. v8}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;Landroid/util/Size;Landroid/util/Size;FZ)V

    .line 616
    invoke-interface {v0, v1, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;->onConfigured(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;Z)V

    .line 628
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetrequestedFacing(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    move-result-object p1

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetactiveFacing(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    move-result-object v0

    if-eq p1, v0, :cond_4

    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetrequestedFacing(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    move-result-object v0

    invoke-static {p1, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$mswitchCameraIfNeeded(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    .line 630
    :goto_2
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetactiveFrameRate(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    move-result-object v0

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->FPS_60:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    .line 633
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    if-ne v0, v1, :cond_3

    .line 631
    const-string v0, "60 fps request submission rejected"

    invoke-static {p0, v0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$mfallbackTo30Fps(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Ljava/lang/String;Ljava/lang/Exception;)V

    goto :goto_3

    .line 633
    :cond_3
    invoke-static {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$mreportError(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Ljava/lang/Exception;)V

    :cond_4
    :goto_3
    return-void

    .line 597
    :cond_5
    :goto_4
    invoke-virtual {p1}, Landroid/hardware/camera2/CameraCaptureSession;->close()V

    return-void
.end method
