.class Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;
.super Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;
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

    .line 802
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-direct {p0}, Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;-><init>()V

    return-void
.end method


# virtual methods
.method public onCaptureCompleted(Landroid/hardware/camera2/CameraCaptureSession;Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/TotalCaptureResult;)V
    .locals 17

    move-object/from16 v0, p0

    move-object/from16 v1, p3

    .line 809
    iget-object v2, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    move-object/from16 v3, p2

    invoke-static {v2, v3, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$mverifyTorchResult(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/TotalCaptureResult;)V

    .line 810
    sget-object v2, Landroid/hardware/camera2/CaptureResult;->SENSOR_TIMESTAMP:Landroid/hardware/camera2/CaptureResult$Key;

    invoke-virtual {v1, v2}, Landroid/hardware/camera2/CaptureResult;->get(Landroid/hardware/camera2/CaptureResult$Key;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Long;

    if-eqz v1, :cond_8

    .line 811
    invoke-virtual {v1}, Ljava/lang/Long;->longValue()J

    move-result-wide v2

    iget-object v4, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureFpsLastTimestampNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v4

    cmp-long v2, v2, v4

    if-gtz v2, :cond_0

    goto/16 :goto_3

    .line 812
    :cond_0
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtimeNanos()J

    move-result-wide v2

    .line 813
    iget-object v4, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureFpsFirstTimestampNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v4

    const-wide/16 v6, 0x0

    cmp-long v4, v4, v6

    .line 817
    iget-object v5, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    const-wide/16 v8, 0x1

    if-nez v4, :cond_1

    .line 814
    invoke-virtual {v1}, Ljava/lang/Long;->longValue()J

    move-result-wide v10

    invoke-static {v5, v10, v11}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputcaptureFpsFirstTimestampNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V

    .line 815
    iget-object v4, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v4, v8, v9}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputcaptureFrames(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V

    goto :goto_0

    .line 817
    :cond_1
    invoke-virtual {v1}, Ljava/lang/Long;->longValue()J

    move-result-wide v10

    iget-object v4, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureFpsLastTimestampNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v12

    sub-long/2addr v10, v12

    invoke-static {v5, v10, v11}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$mrecordCaptureSensorInterval(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V

    .line 818
    iget-object v4, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureFrames(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v10

    add-long/2addr v10, v8

    invoke-static {v4, v10, v11}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputcaptureFrames(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V

    .line 820
    :goto_0
    iget-object v4, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureLastCallbackNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v4

    cmp-long v4, v4, v6

    if-eqz v4, :cond_2

    .line 821
    iget-object v4, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureLastCallbackNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v4

    sub-long v4, v2, v4

    .line 822
    iget-object v6, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v6}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureCallbackIntervalCount(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v10

    add-long/2addr v10, v8

    invoke-static {v6, v10, v11}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputcaptureCallbackIntervalCount(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V

    .line 823
    iget-object v6, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v6}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureCallbackIntervalTotalNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v10

    add-long/2addr v10, v4

    invoke-static {v6, v10, v11}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputcaptureCallbackIntervalTotalNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V

    .line 824
    iget-object v6, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v6}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureCallbackIntervalMaximumNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v10

    invoke-static {v10, v11, v4, v5}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v4

    invoke-static {v6, v4, v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputcaptureCallbackIntervalMaximumNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V

    .line 829
    :cond_2
    iget-object v4, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v4, v2, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputcaptureLastCallbackNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V

    .line 830
    iget-object v4, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-virtual {v1}, Ljava/lang/Long;->longValue()J

    move-result-wide v5

    invoke-static {v4, v5, v6}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputcaptureFpsLastTimestampNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V

    .line 831
    invoke-virtual {v1}, Ljava/lang/Long;->longValue()J

    move-result-wide v4

    iget-object v6, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v6}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureFpsFirstTimestampNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v6

    sub-long/2addr v4, v6

    const-wide v6, 0xb2d05e00L

    cmp-long v6, v4, v6

    if-gez v6, :cond_3

    goto/16 :goto_3

    .line 833
    :cond_3
    iget-object v6, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v6}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureFrames(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v6

    sub-long/2addr v6, v8

    long-to-float v6, v6

    const v7, 0x4e6e6b28    # 1.0E9f

    mul-float/2addr v6, v7

    long-to-float v4, v4

    div-float/2addr v6, v4

    .line 834
    iget-object v4, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetactiveFrameRate(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    move-result-object v4

    const/4 v5, 0x0

    if-nez v4, :cond_4

    move v4, v5

    goto :goto_1

    .line 836
    :cond_4
    iget-object v4, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetactiveFrameRate(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    move-result-object v4

    invoke-virtual {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->getValue()I

    move-result v4

    .line 837
    :goto_1
    iget-object v7, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v7}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetdiagnostics(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    move-result-object v7

    new-instance v10, Ljava/lang/StringBuilder;

    const-string v11, "camera capture rate: measuredFps="

    invoke-direct {v10, v11}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v10, v6}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string v11, ", requestedFps="

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v11, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v11}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetrequestedFrameRate(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    move-result-object v11

    .line 838
    invoke-virtual {v11}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->getValue()I

    move-result v11

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v11, ", activeFps="

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    if-nez v4, :cond_5

    .line 839
    const-string v11, "unknown"

    goto :goto_2

    :cond_5
    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v11

    :goto_2
    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v11, ", targetMet="

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    if-eqz v4, :cond_6

    int-to-float v4, v4

    const v11, 0x3f59999a    # 0.85f

    mul-float/2addr v4, v11

    cmpl-float v4, v6, v4

    if-ltz v4, :cond_7

    :cond_6
    const/4 v5, 0x1

    :cond_7
    invoke-virtual {v10, v5}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v4, ", fpsRange="

    invoke-virtual {v10, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v4, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetactiveFpsRange(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/util/Range;

    move-result-object v4

    invoke-virtual {v10, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v4, ", sensorIntervalMs={avg="

    invoke-virtual {v10, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v4, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureSensorIntervalTotalNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v4

    iget-object v6, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v6}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureSensorIntervalCount(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v11

    .line 844
    invoke-static {v4, v5, v11, v12}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$smaverageMs(JJ)F

    move-result v4

    invoke-virtual {v10, v4}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string v4, ", min="

    invoke-virtual {v10, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v4, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureSensorIntervalMinimumNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v4

    .line 845
    invoke-static {v4, v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$smnanosToMs(J)F

    move-result v4

    invoke-virtual {v10, v4}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string v4, ", max="

    invoke-virtual {v10, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v5, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureSensorIntervalMaximumNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v5

    .line 846
    invoke-static {v5, v6}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$smnanosToMs(J)F

    move-result v5

    invoke-virtual {v10, v5}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string v5, ", jitter="

    invoke-virtual {v10, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v5, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureSensorIntervalTotalNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v11

    iget-object v5, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureSensorIntervalSquaredTotalNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)D

    move-result-wide v13

    iget-object v5, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureSensorIntervalCount(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v15

    .line 847
    invoke-static/range {v11 .. v16}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$smstandardDeviationMs(JDJ)F

    move-result v5

    invoke-virtual {v10, v5}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string v5, "}, sensorGaps={over50ms="

    invoke-virtual {v10, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v5, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureSensorGapsOver50Ms(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v5

    invoke-virtual {v10, v5, v6}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v5, ", over100ms="

    invoke-virtual {v10, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v5, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureSensorGapsOver100Ms(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v5

    invoke-virtual {v10, v5, v6}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v5, "}, callbackIntervalMs={avg="

    invoke-virtual {v10, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v5, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureCallbackIntervalTotalNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v5

    iget-object v11, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v11}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureCallbackIntervalCount(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v11

    .line 854
    invoke-static {v5, v6, v11, v12}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$smaverageMs(JJ)F

    move-result v5

    invoke-virtual {v10, v5}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    invoke-virtual {v10, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v4, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fgetcaptureCallbackIntervalMaximumNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J

    move-result-wide v4

    .line 855
    invoke-static {v4, v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$smnanosToMs(J)F

    move-result v4

    invoke-virtual {v10, v4}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string v4, "}"

    invoke-virtual {v10, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v10}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    .line 837
    invoke-virtual {v7, v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 856
    iget-object v4, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$mresetCaptureFpsMeasurement(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V

    .line 857
    iget-object v4, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-virtual {v1}, Ljava/lang/Long;->longValue()J

    move-result-wide v5

    invoke-static {v4, v5, v6}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputcaptureFpsFirstTimestampNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V

    .line 858
    iget-object v4, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-virtual {v1}, Ljava/lang/Long;->longValue()J

    move-result-wide v5

    invoke-static {v4, v5, v6}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputcaptureFpsLastTimestampNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V

    .line 859
    iget-object v1, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v1, v8, v9}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputcaptureFrames(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V

    .line 860
    iget-object v0, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-static {v0, v2, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->-$$Nest$fputcaptureLastCallbackNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V

    :cond_8
    :goto_3
    return-void
.end method
