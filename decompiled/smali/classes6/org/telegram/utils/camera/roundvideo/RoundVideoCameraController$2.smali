.class Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;
.super Landroid/hardware/camera2/CameraDevice$StateCallback;
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
.method public constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V
    .locals 0

    .line 514
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-direct {p0}, Landroid/hardware/camera2/CameraDevice$StateCallback;-><init>()V

    return-void
.end method


# virtual methods
.method public onClosed(Landroid/hardware/camera2/CameraDevice;)V
    .locals 2

    .line 560
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetswitchingCamera(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z

    move-result p1

    .line 563
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    const/4 v1, 0x0

    if-eqz p1, :cond_0

    .line 561
    invoke-static {v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputswitchingCamera(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Z)V

    .line 562
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetactive(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z

    move-result p1

    if-eqz p1, :cond_1

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$mopenCameraIfReady(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V

    return-void

    .line 563
    :cond_0
    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetreopenAfterCameraClose(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z

    move-result p1

    if-eqz p1, :cond_1

    .line 564
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputreopenAfterCameraClose(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Z)V

    .line 565
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetactive(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z

    move-result p1

    if-eqz p1, :cond_1

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$mopenCameraIfReady(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V

    :cond_1
    return-void
.end method

.method public onDisconnected(Landroid/hardware/camera2/CameraDevice;)V
    .locals 4

    .line 530
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputopening(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Z)V

    .line 531
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetdiagnostics(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    move-result-object v0

    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "camera disconnected: id="

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1}, Landroid/hardware/camera2/CameraDevice;->getId()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 532
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetactive(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetstopping(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetswitchingCamera(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetreopenAfterCameraClose(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z

    move-result v0

    if-nez v0, :cond_0

    const/4 v1, 0x1

    .line 536
    :cond_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcameraDevice(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/hardware/camera2/CameraDevice;

    move-result-object v0

    if-ne v0, p1, :cond_1

    .line 537
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$mcloseCaptureSession(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V

    .line 538
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    const/4 v2, 0x0

    invoke-static {v0, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputcameraDevice(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Landroid/hardware/camera2/CameraDevice;)V

    .line 540
    :cond_1
    invoke-virtual {p1}, Landroid/hardware/camera2/CameraDevice;->close()V

    if-eqz v1, :cond_2

    .line 542
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    new-instance p1, Ljava/lang/IllegalStateException;

    const-string v0, "Camera device disconnected"

    invoke-direct {p1, v0}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    invoke-static {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$mreportError(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Ljava/lang/Exception;)V

    :cond_2
    return-void
.end method

.method public onError(Landroid/hardware/camera2/CameraDevice;I)V
    .locals 2

    .line 548
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputopening(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Z)V

    .line 549
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcameraDevice(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/hardware/camera2/CameraDevice;

    move-result-object v0

    if-ne v0, p1, :cond_0

    .line 550
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$mcloseCaptureSession(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V

    .line 551
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputcameraDevice(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Landroid/hardware/camera2/CameraDevice;)V

    .line 553
    :cond_0
    invoke-virtual {p1}, Landroid/hardware/camera2/CameraDevice;->close()V

    .line 554
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    new-instance p1, Ljava/lang/IllegalStateException;

    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "Camera device error: "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    .line 555
    invoke-static {p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$smcameraErrorName(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, " ("

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p2, ")"

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-direct {p1, p2}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    .line 554
    invoke-static {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$mreportError(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Ljava/lang/Exception;)V

    return-void
.end method

.method public onOpened(Landroid/hardware/camera2/CameraDevice;)V
    .locals 4

    .line 517
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputopening(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Z)V

    .line 518
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetdiagnostics(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    move-result-object v0

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "camera opened: id="

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1}, Landroid/hardware/camera2/CameraDevice;->getId()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, ", elapsedMs="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcameraOpenRequestedNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v2

    .line 519
    invoke-static {v2, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$smelapsedMs(J)J

    move-result-wide v2

    invoke-virtual {v1, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 518
    invoke-virtual {v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 520
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetactive(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetswitchingCamera(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 524
    :cond_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputcameraDevice(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Landroid/hardware/camera2/CameraDevice;)V

    .line 525
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$mcreateCaptureSession(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V

    return-void

    .line 521
    :cond_1
    :goto_0
    invoke-virtual {p1}, Landroid/hardware/camera2/CameraDevice;->close()V

    return-void
.end method
