.class final Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraCandidate;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;
    }
.end annotation


# instance fields
.field private volatile active:Z

.field private activeCameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

.field private activeFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

.field private activeFpsRange:Landroid/util/Range;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/Range<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private activeFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

.field private final applyZoomRunnable:Ljava/lang/Runnable;

.field private volatile awaitingSwitchPreviewFrame:Z

.field private backConfigurationLogged:Z

.field private final callback:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;

.field private cameraDevice:Landroid/hardware/camera2/CameraDevice;

.field private cameraHandler:Landroid/os/Handler;

.field private cameraId:Ljava/lang/String;

.field private final cameraManager:Landroid/hardware/camera2/CameraManager;

.field private cameraOpenRequestedNs:J

.field private cameraSwitchStartedNs:J

.field private cameraThread:Landroid/os/HandlerThread;

.field private final captureCallback:Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;

.field private captureCallbackIntervalCount:J

.field private captureCallbackIntervalMaximumNs:J

.field private captureCallbackIntervalTotalNs:J

.field private captureFpsFirstTimestampNs:J

.field private captureFpsLastTimestampNs:J

.field private captureFrames:J

.field private captureLastCallbackNs:J

.field private captureSensorGapsOver100Ms:J

.field private captureSensorGapsOver50Ms:J

.field private captureSensorIntervalCount:J

.field private captureSensorIntervalMaximumNs:J

.field private captureSensorIntervalMinimumNs:J

.field private captureSensorIntervalSquaredTotalNs:D

.field private captureSensorIntervalTotalNs:J

.field private captureSession:Landroid/hardware/camera2/CameraCaptureSession;

.field private captureSessionRequestedNs:J

.field private characteristics:Landroid/hardware/camera2/CameraCharacteristics;

.field private commonStartRecorder:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

.field private final commonStartTimeoutRunnable:Ljava/lang/Runnable;

.field private final compositionEnabled:Z

.field private final context:Landroid/content/Context;

.field private final deviceCallback:Landroid/hardware/camera2/CameraDevice$StateCallback;

.field private final diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

.field private force30Fps:Z

.field private frameRateFallbackAttempted:Z

.field private frameRatePolicyResolved:Z

.field private frontConfigurationLogged:Z

.field private glProcessor:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

.field private final layoutChangeListener:Landroid/view/View$OnLayoutChangeListener;

.field private maximumZoomRatio:F

.field private notifySwitchCompletion:Z

.field private opening:Z

.field private final outputResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

.field private final outputSize:I

.field private previewFpsStartedNs:J

.field private previewFrames:J

.field private previewGapsOver100Ms:J

.field private previewGapsOver50Ms:J

.field private previewIntervalCount:J

.field private previewIntervalMaximumNs:J

.field private previewIntervalMinimumNs:J

.field private previewIntervalSquaredTotalNs:D

.field private previewIntervalTotalNs:J

.field private previewLastCallbackNs:J

.field private previewLastTimestampNs:J

.field private final previewListener:Landroid/view/TextureView$SurfaceTextureListener;

.field private previewSize:Landroid/util/Size;

.field private previewSurface:Landroid/view/Surface;

.field private previewTimestampFirstNs:J

.field private previewTimestampFrames:J

.field private previewTimestampLastNs:J

.field private previewTimestampNonMonotonic:J

.field private volatile previewTransformEnabled:Z

.field private final previewView:Landroid/view/TextureView;

.field private recorder:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

.field private volatile recordingSize:Landroid/util/Size;

.field private recordingSurface:Landroid/view/Surface;

.field private regularFallbackPair:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;

.field private reopenAfterCameraClose:Z

.field private repeatingBuilder:Landroid/hardware/camera2/CaptureRequest$Builder;

.field private requestGeneration:I

.field private final requestedCameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

.field private volatile requestedFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

.field private final requestedFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

.field private segmentStartedNs:J

.field private selectedFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

.field private final sessionCallback:Landroid/hardware/camera2/CameraCaptureSession$StateCallback;

.field private volatile sourceCropSize:I

.field private volatile stopping:Z

.field private switchingCamera:Z

.field private timelineOffsetUs:J

.field private torchEnabled:Z

.field private torchVerificationFrames:I

.field private torchVerificationGeneration:I

.field private torchVerificationPending:Z

.field private torchVerificationRetries:I

.field private final videoBitrate:I

.field private writer:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

.field private final zoomCropRect:Landroid/graphics/Rect;

.field private volatile zoomProgress:F


# direct methods
.method public static synthetic $r8$lambda$2Fc_dJ5DcjI31A5Ft7DmVhwYjbY(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Landroid/view/View;IIIIIIII)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p9}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->lambda$new$0(Landroid/view/View;IIIIIIII)V

    return-void
.end method

.method public static synthetic $r8$lambda$491wkZAaqRfQ4e43SWw65HL10QY(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->createCaptureSession()V

    return-void
.end method

.method public static synthetic $r8$lambda$7TauQyEVoIpaEFpaE-gpAsD65AM(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Landroid/os/HandlerThread;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->lambda$release$5(Landroid/os/HandlerThread;)V

    return-void
.end method

.method public static synthetic $r8$lambda$7kOLPySoiDBJ197Uw451eev3I-w(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->lambda$new$1()V

    return-void
.end method

.method public static synthetic $r8$lambda$DyDSGFKIkdC1_iX6FEu7G5wFEIw(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->lambda$startRecordingAfterFirstFrame$8(Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V

    return-void
.end method

.method public static synthetic $r8$lambda$GA1T_6Loc9gjMbQEr2KC-FL9bwo(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->openCameraIfReady()V

    return-void
.end method

.method public static synthetic $r8$lambda$LCYS0D_udTsi3TJvozL9qNX2HkE(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Z)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->lambda$setTorchEnabled$3(Z)V

    return-void
.end method

.method public static synthetic $r8$lambda$LJXXjE8NW82dJzXI6GPQJhqbUwY(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->lambda$startRecordingAfterFirstFrame$7(Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V

    return-void
.end method

.method public static synthetic $r8$lambda$VGjG-koRa4MfvLUjp0_NlY4X5yw(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->lambda$stopSegment$4()V

    return-void
.end method

.method public static synthetic $r8$lambda$_drZ5gYJBLzP3hzpZWkD0-riH5E(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->lambda$createRecordingPipeline$6()V

    return-void
.end method

.method public static synthetic $r8$lambda$aSm8yikJu4WMAwTEMwNUopkCafk(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->startRecordingAfterFirstFrame()V

    return-void
.end method

.method public static synthetic $r8$lambda$l2dTEe0M919_mmWFgBaYy3z37oI(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->lambda$setCameraFacing$2(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V

    return-void
.end method

.method public static synthetic $r8$lambda$lidkUBM-2dWqsRYCtzL0nTp-H9o(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->lambda$startRecordingAfterFirstFrame$9(Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V

    return-void
.end method

.method public static synthetic $r8$lambda$nsMRqNCOPwBW1KVpysXaqJ4ub_8(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->applyRepeatingRequest()V

    return-void
.end method

.method public static synthetic $r8$lambda$xNvqkK3mGMoZUtsNbXP3nFtsmRg(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->lambda$startRecordingAfterFirstFrame$10(Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$fgetactive(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->active:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetactiveCameraResolution(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeCameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetactiveFacing(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetactiveFpsRange(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/util/Range;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeFpsRange:Landroid/util/Range;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetactiveFrameRate(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetawaitingSwitchPreviewFrame(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->awaitingSwitchPreviewFrame:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetcallback(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->callback:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetcameraDevice(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/hardware/camera2/CameraDevice;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraDevice:Landroid/hardware/camera2/CameraDevice;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetcameraOpenRequestedNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraOpenRequestedNs:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetcameraSwitchStartedNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraSwitchStartedNs:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetcaptureCallbackIntervalCount(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureCallbackIntervalCount:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetcaptureCallbackIntervalMaximumNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureCallbackIntervalMaximumNs:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetcaptureCallbackIntervalTotalNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureCallbackIntervalTotalNs:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetcaptureFpsFirstTimestampNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureFpsFirstTimestampNs:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetcaptureFpsLastTimestampNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureFpsLastTimestampNs:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetcaptureFrames(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureFrames:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetcaptureLastCallbackNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureLastCallbackNs:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetcaptureSensorGapsOver100Ms(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorGapsOver100Ms:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetcaptureSensorGapsOver50Ms(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorGapsOver50Ms:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetcaptureSensorIntervalCount(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorIntervalCount:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetcaptureSensorIntervalMaximumNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorIntervalMaximumNs:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetcaptureSensorIntervalMinimumNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorIntervalMinimumNs:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetcaptureSensorIntervalSquaredTotalNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)D
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorIntervalSquaredTotalNs:D

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetcaptureSensorIntervalTotalNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorIntervalTotalNs:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetcaptureSession(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/hardware/camera2/CameraCaptureSession;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSession:Landroid/hardware/camera2/CameraCaptureSession;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetcaptureSessionRequestedNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSessionRequestedNs:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetdiagnostics(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetglProcessor(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->glProcessor:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetmaximumZoomRatio(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)F
    .locals 0

    .line 0
    iget p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->maximumZoomRatio:F

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetnotifySwitchCompletion(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->notifySwitchCompletion:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetpreviewFpsStartedNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewFpsStartedNs:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetpreviewFrames(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewFrames:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetpreviewLastCallbackNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewLastCallbackNs:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetpreviewLastTimestampNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewLastTimestampNs:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetpreviewSize(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/util/Size;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewSize:Landroid/util/Size;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetpreviewTimestampFirstNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewTimestampFirstNs:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetpreviewTimestampFrames(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewTimestampFrames:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetpreviewTimestampNonMonotonic(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewTimestampNonMonotonic:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetpreviewView(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/view/TextureView;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewView:Landroid/view/TextureView;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetrecordingSize(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Landroid/util/Size;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recordingSize:Landroid/util/Size;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetreopenAfterCameraClose(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->reopenAfterCameraClose:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetrequestedFacing(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->requestedFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetrequestedFrameRate(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->requestedFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetsegmentStartedNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->segmentStartedNs:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetselectedFacing(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->selectedFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetstopping(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->stopping:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetswitchingCamera(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->switchingCamera:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fputactiveFacing(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V
    .locals 0

    .line 0
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputawaitingSwitchPreviewFrame(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Z)V
    .locals 0

    .line 0
    iput-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->awaitingSwitchPreviewFrame:Z

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputcameraDevice(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Landroid/hardware/camera2/CameraDevice;)V
    .locals 0

    .line 0
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraDevice:Landroid/hardware/camera2/CameraDevice;

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputcaptureCallbackIntervalCount(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V
    .locals 0

    .line 0
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureCallbackIntervalCount:J

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputcaptureCallbackIntervalMaximumNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V
    .locals 0

    .line 0
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureCallbackIntervalMaximumNs:J

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputcaptureCallbackIntervalTotalNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V
    .locals 0

    .line 0
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureCallbackIntervalTotalNs:J

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputcaptureFpsFirstTimestampNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V
    .locals 0

    .line 0
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureFpsFirstTimestampNs:J

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputcaptureFpsLastTimestampNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V
    .locals 0

    .line 0
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureFpsLastTimestampNs:J

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputcaptureFrames(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V
    .locals 0

    .line 0
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureFrames:J

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputcaptureLastCallbackNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V
    .locals 0

    .line 0
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureLastCallbackNs:J

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputcaptureSession(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Landroid/hardware/camera2/CameraCaptureSession;)V
    .locals 0

    .line 0
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSession:Landroid/hardware/camera2/CameraCaptureSession;

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputnotifySwitchCompletion(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Z)V
    .locals 0

    .line 0
    iput-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->notifySwitchCompletion:Z

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputopening(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Z)V
    .locals 0

    .line 0
    iput-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->opening:Z

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputpreviewFpsStartedNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V
    .locals 0

    .line 0
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewFpsStartedNs:J

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputpreviewFrames(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V
    .locals 0

    .line 0
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewFrames:J

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputpreviewLastCallbackNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V
    .locals 0

    .line 0
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewLastCallbackNs:J

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputpreviewLastTimestampNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V
    .locals 0

    .line 0
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewLastTimestampNs:J

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputpreviewTimestampFirstNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V
    .locals 0

    .line 0
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewTimestampFirstNs:J

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputpreviewTimestampFrames(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V
    .locals 0

    .line 0
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewTimestampFrames:J

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputpreviewTimestampLastNs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V
    .locals 0

    .line 0
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewTimestampLastNs:J

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputpreviewTimestampNonMonotonic(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V
    .locals 0

    .line 0
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewTimestampNonMonotonic:J

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputreopenAfterCameraClose(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Z)V
    .locals 0

    .line 0
    iput-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->reopenAfterCameraClose:Z

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputrepeatingBuilder(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Landroid/hardware/camera2/CaptureRequest$Builder;)V
    .locals 0

    .line 0
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->repeatingBuilder:Landroid/hardware/camera2/CaptureRequest$Builder;

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputswitchingCamera(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Z)V
    .locals 0

    .line 0
    iput-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->switchingCamera:Z

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputtorchVerificationPending(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Z)V
    .locals 0

    .line 0
    iput-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchVerificationPending:Z

    return-void
.end method

.method public static bridge synthetic -$$Nest$mcloseCaptureSession(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->closeCaptureSession()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mcreateCaptureSession(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->createCaptureSession()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mcreateRepeatingRequestBuilder(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Z)Landroid/hardware/camera2/CaptureRequest$Builder;
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->createRepeatingRequestBuilder(Z)Landroid/hardware/camera2/CaptureRequest$Builder;

    move-result-object p0

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$mfallbackTo30Fps(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Ljava/lang/String;Ljava/lang/Exception;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->fallbackTo30Fps(Ljava/lang/String;Ljava/lang/Exception;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$misTorchAvailable(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)Z
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->isTorchAvailable()Z

    move-result p0

    return p0
.end method

.method public static bridge synthetic -$$Nest$mlogPreviewFrameMetrics(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;JJ)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3, p4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->logPreviewFrameMetrics(JJ)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mmaybeOpenCamera(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->maybeOpenCamera()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mopenCameraIfReady(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->openCameraIfReady()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mrecordCaptureSensorInterval(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recordCaptureSensorInterval(J)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mrecordPreviewInterval(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;J)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recordPreviewInterval(J)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mreportError(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Ljava/lang/Exception;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->reportError(Ljava/lang/Exception;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mresetCaptureFpsMeasurement(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->resetCaptureFpsMeasurement()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$msubmitRepeatingRequest(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->submitRepeatingRequest()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mswitchCameraIfNeeded(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->switchCameraIfNeeded(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mupdatePreviewTransform(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->updatePreviewTransform()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mverifyTorchResult(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/TotalCaptureResult;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->verifyTorchResult(Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/TotalCaptureResult;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$smaverageMs(JJ)F
    .locals 0

    .line 0
    invoke-static {p0, p1, p2, p3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->averageMs(JJ)F

    move-result p0

    return p0
.end method

.method public static bridge synthetic -$$Nest$smcameraErrorName(I)Ljava/lang/String;
    .locals 0

    .line 0
    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraErrorName(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$smelapsedMs(J)J
    .locals 0

    .line 0
    invoke-static {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->elapsedMs(J)J

    move-result-wide p0

    return-wide p0
.end method

.method public static bridge synthetic -$$Nest$smnanosToMs(J)F
    .locals 0

    .line 0
    invoke-static {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->nanosToMs(J)F

    move-result p0

    return p0
.end method

.method public static bridge synthetic -$$Nest$smstandardDeviationMs(JDJ)F
    .locals 0

    .line 0
    invoke-static/range {p0 .. p5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->standardDeviationMs(JDJ)F

    move-result p0

    return p0
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/view/TextureView;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;ILorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;ZLorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;)V
    .locals 2

    .line 208
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 104
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->zoomCropRect:Landroid/graphics/Rect;

    const/high16 v0, 0x3f800000    # 1.0f

    .line 131
    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->maximumZoomRatio:F

    .line 185
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda4;

    invoke-direct {v0, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda4;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->layoutChangeListener:Landroid/view/View$OnLayoutChangeListener;

    .line 189
    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda5;

    invoke-direct {v1, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda5;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->commonStartTimeoutRunnable:Ljava/lang/Runnable;

    .line 339
    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda6;

    invoke-direct {v1, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda6;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->applyZoomRunnable:Ljava/lang/Runnable;

    .line 341
    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;

    invoke-direct {v1, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$1;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewListener:Landroid/view/TextureView$SurfaceTextureListener;

    .line 514
    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;

    invoke-direct {v1, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$2;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->deviceCallback:Landroid/hardware/camera2/CameraDevice$StateCallback;

    .line 592
    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;

    invoke-direct {v1, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$3;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->sessionCallback:Landroid/hardware/camera2/CameraCaptureSession$StateCallback;

    .line 801
    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;

    invoke-direct {v1, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$4;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureCallback:Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;

    .line 209
    invoke-virtual {p1}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object v1

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->context:Landroid/content/Context;

    .line 210
    const-string v1, "camera"

    invoke-virtual {p1, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/hardware/camera2/CameraManager;

    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraManager:Landroid/hardware/camera2/CameraManager;

    .line 211
    iput-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewView:Landroid/view/TextureView;

    .line 212
    iput-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->outputResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    .line 213
    invoke-virtual {p3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;->getSize()I

    move-result p1

    iput p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->outputSize:I

    .line 214
    iput p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->videoBitrate:I

    .line 215
    iput-object p5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->requestedCameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    .line 216
    iput-object p6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->requestedFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    .line 217
    iput-boolean p7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->compositionEnabled:Z

    .line 218
    iput-object p8, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    .line 219
    iput-object p9, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->callback:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;

    .line 220
    invoke-virtual {p2, v0}, Landroid/view/View;->addOnLayoutChangeListener(Landroid/view/View$OnLayoutChangeListener;)V

    return-void
.end method

.method private announceRecordingStarted(Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V
    .locals 3

    .line 710
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->active:Z

    if-eqz v0, :cond_1

    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->stopping:Z

    if-nez v0, :cond_1

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recorder:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

    if-eq v0, p1, :cond_0

    goto :goto_0

    .line 711
    :cond_0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cancelCommonStartWait()V

    .line 712
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "common A/V start completed: segmentElapsedMs="

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-wide v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->segmentStartedNs:J

    .line 713
    invoke-static {v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->elapsedMs(J)J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 712
    invoke-virtual {p1, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 714
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->callback:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;

    invoke-interface {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;->onRecordingStarted()V

    :cond_1
    :goto_0
    return-void
.end method

.method private applyRepeatingRequest()V
    .locals 3

    .line 729
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSession:Landroid/hardware/camera2/CameraCaptureSession;

    .line 730
    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->active:Z

    if-eqz v1, :cond_2

    if-eqz v0, :cond_2

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraDevice:Landroid/hardware/camera2/CameraDevice;

    if-nez v1, :cond_0

    goto :goto_2

    :cond_0
    const/4 v1, 0x0

    .line 732
    :try_start_0
    invoke-direct {p0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->createRepeatingRequestBuilder(Z)Landroid/hardware/camera2/CaptureRequest$Builder;

    move-result-object v1

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->repeatingBuilder:Landroid/hardware/camera2/CaptureRequest$Builder;

    .line 733
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->submitRepeatingRequest()V
    :try_end_0
    .catch Ljava/lang/IllegalStateException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Landroid/hardware/camera2/CameraAccessException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception v0

    goto :goto_0

    :catch_1
    move-exception v0

    goto :goto_0

    :catch_2
    move-exception v1

    goto :goto_1

    .line 737
    :goto_0
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    sget-object v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->FPS_60:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    if-ne v1, v2, :cond_1

    .line 738
    const-string v1, "60 fps updated request rejected"

    invoke-direct {p0, v1, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->fallbackTo30Fps(Ljava/lang/String;Ljava/lang/Exception;)V

    goto :goto_2

    .line 740
    :cond_1
    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->reportError(Ljava/lang/Exception;)V

    goto :goto_2

    .line 735
    :goto_1
    invoke-direct {p0, v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recoverFromClosedCamera(Landroid/hardware/camera2/CameraCaptureSession;Ljava/lang/IllegalStateException;)V

    :cond_2
    :goto_2
    return-void
.end method

.method private applyTorch(Landroid/hardware/camera2/CaptureRequest$Builder;)V
    .locals 5

    .line 1001
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchEnabled:Z

    const/4 v1, 0x0

    const/4 v2, 0x1

    if-eqz v0, :cond_0

    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->isTorchAvailable()Z

    move-result v0

    if-eqz v0, :cond_0

    move v0, v2

    goto :goto_0

    :cond_0
    move v0, v1

    .line 1002
    :goto_0
    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->characteristics:Landroid/hardware/camera2/CameraCharacteristics;

    if-nez v3, :cond_1

    const/4 v3, 0x0

    goto :goto_1

    .line 1004
    :cond_1
    sget-object v4, Landroid/hardware/camera2/CameraCharacteristics;->CONTROL_AE_AVAILABLE_MODES:Landroid/hardware/camera2/CameraCharacteristics$Key;

    invoke-virtual {v3, v4}, Landroid/hardware/camera2/CameraCharacteristics;->get(Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, [I

    .line 1005
    :goto_1
    invoke-static {v3, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->contains([II)Z

    move-result v3

    if-eqz v3, :cond_2

    .line 1006
    sget-object v3, Landroid/hardware/camera2/CaptureRequest;->CONTROL_AE_MODE:Landroid/hardware/camera2/CaptureRequest$Key;

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {p1, v3, v2}, Landroid/hardware/camera2/CaptureRequest$Builder;->set(Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V

    .line 1008
    :cond_2
    sget-object v2, Landroid/hardware/camera2/CaptureRequest;->FLASH_MODE:Landroid/hardware/camera2/CaptureRequest$Key;

    if-eqz v0, :cond_3

    const/4 v1, 0x2

    .line 1010
    :cond_3
    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    .line 1008
    invoke-virtual {p1, v2, v1}, Landroid/hardware/camera2/CaptureRequest$Builder;->set(Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V

    .line 1014
    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchVerificationPending:Z

    if-eqz v1, :cond_4

    .line 1015
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v3, Ljava/lang/StringBuilder;

    const-string v4, "torch request configured: requested="

    invoke-direct {v3, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-boolean v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchEnabled:Z

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v4, ", applied="

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v0, ", aeMode="

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v0, Landroid/hardware/camera2/CaptureRequest;->CONTROL_AE_MODE:Landroid/hardware/camera2/CaptureRequest$Key;

    .line 1017
    invoke-virtual {p1, v0}, Landroid/hardware/camera2/CaptureRequest$Builder;->get(Landroid/hardware/camera2/CaptureRequest$Key;)Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v0, ", flashMode="

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1018
    invoke-virtual {p1, v2}, Landroid/hardware/camera2/CaptureRequest$Builder;->get(Landroid/hardware/camera2/CaptureRequest$Key;)Ljava/lang/Object;

    move-result-object p1

    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p1, ", cameraId="

    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraId:Ljava/lang/String;

    invoke-virtual {v3, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 1015
    invoke-virtual {v1, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    :cond_4
    return-void
.end method

.method private applyZoom(Landroid/hardware/camera2/CaptureRequest$Builder;)V
    .locals 5

    .line 978
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->zoomProgress:F

    iget v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->maximumZoomRatio:F

    const/high16 v2, 0x3f800000    # 1.0f

    sub-float/2addr v1, v2

    mul-float/2addr v0, v1

    add-float/2addr v0, v2

    .line 979
    sget v1, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v2, 0x1e

    const/4 v3, 0x0

    if-lt v1, v2, :cond_1

    .line 980
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->characteristics:Landroid/hardware/camera2/CameraCharacteristics;

    if-nez v1, :cond_0

    move-object v1, v3

    goto :goto_0

    .line 982
    :cond_0
    invoke-static {}, Landroidx/camera/camera2/compat/workaround/CameraMetadataSafeGetterKt$$ExternalSyntheticApiModelOutline0;->m()Landroid/hardware/camera2/CameraCharacteristics$Key;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroid/hardware/camera2/CameraCharacteristics;->get(Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/util/Range;

    :goto_0
    if-eqz v1, :cond_1

    .line 984
    invoke-static {}, Landroidx/camera/camera2/compat/AndroidRZoomCompat$$ExternalSyntheticApiModelOutline0;->m()Landroid/hardware/camera2/CaptureRequest$Key;

    move-result-object p0

    invoke-virtual {v1}, Landroid/util/Range;->getLower()Ljava/lang/Comparable;

    move-result-object v2

    check-cast v2, Ljava/lang/Float;

    invoke-virtual {v2}, Ljava/lang/Float;->floatValue()F

    move-result v2

    invoke-virtual {v1}, Landroid/util/Range;->getUpper()Ljava/lang/Comparable;

    move-result-object v1

    check-cast v1, Ljava/lang/Float;

    invoke-virtual {v1}, Ljava/lang/Float;->floatValue()F

    move-result v1

    invoke-static {v0, v2, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->clamp(FFF)F

    move-result v0

    invoke-static {v0}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v0

    invoke-virtual {p1, p0, v0}, Landroid/hardware/camera2/CaptureRequest$Builder;->set(Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V

    return-void

    .line 988
    :cond_1
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->characteristics:Landroid/hardware/camera2/CameraCharacteristics;

    if-nez v1, :cond_2

    goto :goto_1

    .line 990
    :cond_2
    sget-object v2, Landroid/hardware/camera2/CameraCharacteristics;->SENSOR_INFO_ACTIVE_ARRAY_SIZE:Landroid/hardware/camera2/CameraCharacteristics$Key;

    invoke-virtual {v1, v2}, Landroid/hardware/camera2/CameraCharacteristics;->get(Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;

    move-result-object v1

    move-object v3, v1

    check-cast v3, Landroid/graphics/Rect;

    :goto_1
    if-nez v3, :cond_3

    return-void

    .line 992
    :cond_3
    invoke-virtual {v3}, Landroid/graphics/Rect;->width()I

    move-result v1

    int-to-float v1, v1

    div-float/2addr v1, v0

    invoke-static {v1}, Ljava/lang/Math;->round(F)I

    move-result v1

    const/4 v2, 0x1

    invoke-static {v2, v1}, Ljava/lang/Math;->max(II)I

    move-result v1

    .line 993
    invoke-virtual {v3}, Landroid/graphics/Rect;->height()I

    move-result v4

    int-to-float v4, v4

    div-float/2addr v4, v0

    invoke-static {v4}, Ljava/lang/Math;->round(F)I

    move-result v0

    invoke-static {v2, v0}, Ljava/lang/Math;->max(II)I

    move-result v0

    .line 994
    invoke-virtual {v3}, Landroid/graphics/Rect;->centerX()I

    move-result v2

    div-int/lit8 v4, v1, 0x2

    sub-int/2addr v2, v4

    .line 995
    invoke-virtual {v3}, Landroid/graphics/Rect;->centerY()I

    move-result v3

    div-int/lit8 v4, v0, 0x2

    sub-int/2addr v3, v4

    .line 996
    iget-object v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->zoomCropRect:Landroid/graphics/Rect;

    add-int/2addr v1, v2

    add-int/2addr v0, v3

    invoke-virtual {v4, v2, v3, v1, v0}, Landroid/graphics/Rect;->set(IIII)V

    .line 997
    sget-object v0, Landroid/hardware/camera2/CaptureRequest;->SCALER_CROP_REGION:Landroid/hardware/camera2/CaptureRequest$Key;

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->zoomCropRect:Landroid/graphics/Rect;

    invoke-virtual {p1, v0, p0}, Landroid/hardware/camera2/CaptureRequest$Builder;->set(Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V

    return-void
.end method

.method private static area(Landroid/util/Size;)J
    .locals 4

    .line 1765
    invoke-virtual {p0}, Landroid/util/Size;->getWidth()I

    move-result v0

    int-to-long v0, v0

    invoke-virtual {p0}, Landroid/util/Size;->getHeight()I

    move-result p0

    int-to-long v2, p0

    mul-long/2addr v0, v2

    return-wide v0
.end method

.method private static averageMs(JJ)F
    .locals 2

    const-wide/16 v0, 0x0

    cmp-long v0, p2, v0

    if-nez v0, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    long-to-float p0, p0

    long-to-float p1, p2

    div-float/2addr p0, p1

    const p1, 0x49742400    # 1000000.0f

    div-float/2addr p0, p1

    return p0
.end method

.method private static cameraErrorName(I)Ljava/lang/String;
    .locals 1

    const/4 v0, 0x1

    if-eq p0, v0, :cond_4

    const/4 v0, 0x2

    if-eq p0, v0, :cond_3

    const/4 v0, 0x3

    if-eq p0, v0, :cond_2

    const/4 v0, 0x4

    if-eq p0, v0, :cond_1

    const/4 v0, 0x5

    if-eq p0, v0, :cond_0

    .line 1608
    const-string p0, "UNKNOWN"

    return-object p0

    .line 1606
    :cond_0
    const-string p0, "CAMERA_SERVICE"

    return-object p0

    .line 1604
    :cond_1
    const-string p0, "CAMERA_DEVICE"

    return-object p0

    .line 1602
    :cond_2
    const-string p0, "CAMERA_DISABLED"

    return-object p0

    .line 1600
    :cond_3
    const-string p0, "MAX_CAMERAS_IN_USE"

    return-object p0

    .line 1598
    :cond_4
    const-string p0, "CAMERA_IN_USE"

    return-object p0
.end method

.method private cancelCommonStartWait()V
    .locals 2

    .line 723
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraHandler:Landroid/os/Handler;

    if-eqz v0, :cond_0

    .line 724
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->commonStartTimeoutRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    :cond_0
    const/4 v0, 0x0

    .line 725
    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->commonStartRecorder:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

    return-void
.end method

.method private static chooseFallbackRecordingSize([Landroid/util/Size;I)Landroid/util/Size;
    .locals 10

    .line 1681
    array-length v0, p0

    const/4 v1, 0x0

    const/4 v2, 0x0

    move-object v4, v1

    move v3, v2

    :goto_0
    if-ge v3, v0, :cond_3

    aget-object v5, p0, v3

    .line 1682
    invoke-static {v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->isWithinAbsoluteLimit(Landroid/util/Size;)Z

    move-result v6

    if-eqz v6, :cond_2

    invoke-static {v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->shortSide(Landroid/util/Size;)I

    move-result v6

    if-ge v6, p1, :cond_0

    goto :goto_1

    :cond_0
    if-eqz v4, :cond_1

    .line 1683
    invoke-static {v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->area(Landroid/util/Size;)J

    move-result-wide v6

    invoke-static {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->area(Landroid/util/Size;)J

    move-result-wide v8

    cmp-long v6, v6, v8

    if-gez v6, :cond_2

    :cond_1
    move-object v4, v5

    :cond_2
    :goto_1
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_3
    if-eqz v4, :cond_4

    return-object v4

    .line 1686
    :cond_4
    array-length p1, p0

    :goto_2
    if-ge v2, p1, :cond_8

    aget-object v0, p0, v2

    .line 1687
    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->isWithinAbsoluteLimit(Landroid/util/Size;)Z

    move-result v3

    if-nez v3, :cond_5

    goto :goto_3

    :cond_5
    if-eqz v4, :cond_6

    .line 1689
    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->shortSide(Landroid/util/Size;)I

    move-result v3

    invoke-static {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->shortSide(Landroid/util/Size;)I

    move-result v5

    if-gt v3, v5, :cond_6

    .line 1690
    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->shortSide(Landroid/util/Size;)I

    move-result v3

    invoke-static {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->shortSide(Landroid/util/Size;)I

    move-result v5

    if-ne v3, v5, :cond_7

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->area(Landroid/util/Size;)J

    move-result-wide v5

    invoke-static {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->area(Landroid/util/Size;)J

    move-result-wide v7

    cmp-long v3, v5, v7

    if-gez v3, :cond_7

    :cond_6
    move-object v4, v0

    :cond_7
    :goto_3
    add-int/lit8 v2, v2, 0x1

    goto :goto_2

    :cond_8
    if-eqz v4, :cond_9

    return-object v4

    .line 1695
    :cond_9
    const-string p0, "Camera has no output at or below the bandwidth cap"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v1
.end method

.method private static chooseOutputPair([Landroid/util/Size;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;)Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;
    .locals 2

    .line 1624
    invoke-static {p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->getSourceCropSize(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;)I

    move-result v0

    .line 1625
    invoke-static {p0, v0, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->chooseTierPair([Landroid/util/Size;ILorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;)Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;

    move-result-object v0

    if-eqz v0, :cond_0

    return-object v0

    .line 1632
    :cond_0
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;->P360:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    if-ne p1, v0, :cond_1

    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;->MEDIUM:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    if-ne p2, v0, :cond_1

    const/16 v1, 0x1e0

    .line 1634
    invoke-static {p0, v1, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->chooseTierPair([Landroid/util/Size;ILorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;)Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;

    move-result-object v0

    if-eqz v0, :cond_1

    return-object v0

    .line 1642
    :cond_1
    invoke-virtual {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;->getSize()I

    move-result v0

    .line 1643
    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;->LOW:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    if-eq p2, v1, :cond_2

    .line 1644
    invoke-static {p0, v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->chooseTierPair([Landroid/util/Size;ILorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;)Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;

    move-result-object p2

    if-eqz p2, :cond_2

    return-object p2

    .line 1652
    :cond_2
    invoke-virtual {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;->getSize()I

    move-result p1

    invoke-static {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->chooseFallbackRecordingSize([Landroid/util/Size;I)Landroid/util/Size;

    move-result-object p1

    .line 1653
    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->shortSide(Landroid/util/Size;)I

    move-result p2

    const/16 v0, 0x440

    invoke-static {p2, v0}, Ljava/lang/Math;->min(II)I

    move-result p2

    .line 1654
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;

    .line 1655
    invoke-static {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->choosePreviewSize([Landroid/util/Size;Landroid/util/Size;)Landroid/util/Size;

    move-result-object p0

    invoke-direct {v0, p0, p1, v1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;-><init>(Landroid/util/Size;Landroid/util/Size;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;I)V

    return-object v0
.end method

.method private static choosePreviewSize([Landroid/util/Size;Landroid/util/Size;)Landroid/util/Size;
    .locals 5

    .line 1714
    array-length v0, p0

    const/4 v1, 0x0

    move-object v2, p1

    :goto_0
    if-ge v1, v0, :cond_1

    aget-object v3, p0, v1

    .line 1715
    invoke-static {v3, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->hasSameAspectRatio(Landroid/util/Size;Landroid/util/Size;)Z

    move-result v4

    if-eqz v4, :cond_0

    invoke-static {v3, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->comparePreviewSizes(Landroid/util/Size;Landroid/util/Size;)I

    move-result v4

    if-gez v4, :cond_0

    move-object v2, v3

    :cond_0
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_1
    return-object v2
.end method

.method private static chooseTierPair([Landroid/util/Size;ILorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;)Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;
    .locals 6

    .line 1669
    array-length v0, p0

    const/4 v1, 0x0

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v0, :cond_3

    aget-object v3, p0, v2

    .line 1670
    invoke-static {v3, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->isValidTierSize(Landroid/util/Size;I)Z

    move-result v4

    if-nez v4, :cond_0

    goto :goto_1

    .line 1671
    :cond_0
    invoke-static {p0, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->choosePreviewSize([Landroid/util/Size;Landroid/util/Size;)Landroid/util/Size;

    move-result-object v4

    .line 1672
    new-instance v5, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;

    invoke-direct {v5, v4, v3, p2, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;-><init>(Landroid/util/Size;Landroid/util/Size;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;I)V

    if-eqz v1, :cond_1

    .line 1673
    invoke-static {v5, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->compareOutputPairs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;)I

    move-result v3

    if-gez v3, :cond_2

    :cond_1
    move-object v1, v5

    :cond_2
    :goto_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_3
    return-object v1
.end method

.method private static clamp(FFF)F
    .locals 0

    .line 1775
    invoke-static {p2, p0}, Ljava/lang/Math;->min(FF)F

    move-result p0

    invoke-static {p1, p0}, Ljava/lang/Math;->max(FF)F

    move-result p0

    return p0
.end method

.method private closeCameraAndSurfaces()V
    .locals 2

    .line 1500
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cancelCommonStartWait()V

    const/4 v0, 0x0

    .line 1501
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->opening:Z

    .line 1502
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->switchingCamera:Z

    .line 1503
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->reopenAfterCameraClose:Z

    .line 1504
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewTransformEnabled:Z

    .line 1505
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->notifySwitchCompletion:Z

    .line 1506
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->awaitingSwitchPreviewFrame:Z

    const/4 v1, 0x0

    .line 1507
    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->repeatingBuilder:Landroid/hardware/camera2/CaptureRequest$Builder;

    .line 1508
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchVerificationPending:Z

    .line 1509
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->closeCaptureSession()V

    .line 1510
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraDevice:Landroid/hardware/camera2/CameraDevice;

    if-eqz v0, :cond_0

    .line 1511
    invoke-virtual {v0}, Landroid/hardware/camera2/CameraDevice;->close()V

    .line 1512
    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraDevice:Landroid/hardware/camera2/CameraDevice;

    .line 1514
    :cond_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewSurface:Landroid/view/Surface;

    if-eqz v0, :cond_1

    .line 1515
    invoke-virtual {v0}, Landroid/view/Surface;->release()V

    .line 1516
    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewSurface:Landroid/view/Surface;

    .line 1518
    :cond_1
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->glProcessor:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

    if-eqz v0, :cond_2

    .line 1519
    invoke-virtual {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->stop()V

    .line 1520
    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->glProcessor:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

    .line 1522
    :cond_2
    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recordingSurface:Landroid/view/Surface;

    return-void
.end method

.method private closeCaptureSession()V
    .locals 2

    .line 1526
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSession:Landroid/hardware/camera2/CameraCaptureSession;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    .line 1527
    invoke-virtual {v0}, Landroid/hardware/camera2/CameraCaptureSession;->close()V

    .line 1528
    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSession:Landroid/hardware/camera2/CameraCaptureSession;

    .line 1530
    :cond_0
    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->repeatingBuilder:Landroid/hardware/camera2/CaptureRequest$Builder;

    return-void
.end method

.method private static compareOutputPairs(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;)I
    .locals 6

    .line 1729
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;->preview:Landroid/util/Size;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->shortSide(Landroid/util/Size;)I

    move-result v0

    add-int/lit16 v0, v0, -0x2d0

    invoke-static {v0}, Ljava/lang/Math;->abs(I)I

    move-result v0

    .line 1730
    iget-object v1, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;->preview:Landroid/util/Size;

    invoke-static {v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->shortSide(Landroid/util/Size;)I

    move-result v1

    add-int/lit16 v1, v1, -0x2d0

    invoke-static {v1}, Ljava/lang/Math;->abs(I)I

    move-result v1

    if-eq v0, v1, :cond_0

    .line 1731
    invoke-static {v0, v1}, Ljava/lang/Integer;->compare(II)I

    move-result p0

    return p0

    .line 1732
    :cond_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;->preview:Landroid/util/Size;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->area(Landroid/util/Size;)J

    move-result-wide v0

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;->recording:Landroid/util/Size;

    invoke-static {v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->area(Landroid/util/Size;)J

    move-result-wide v2

    add-long/2addr v0, v2

    .line 1733
    iget-object v2, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;->preview:Landroid/util/Size;

    invoke-static {v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->area(Landroid/util/Size;)J

    move-result-wide v2

    iget-object v4, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;->recording:Landroid/util/Size;

    invoke-static {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->area(Landroid/util/Size;)J

    move-result-wide v4

    add-long/2addr v2, v4

    cmp-long v4, v0, v2

    if-eqz v4, :cond_1

    .line 1735
    invoke-static {v0, v1, v2, v3}, Ljava/lang/Long;->compare(JJ)I

    move-result p0

    return p0

    .line 1736
    :cond_1
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;->recording:Landroid/util/Size;

    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->area(Landroid/util/Size;)J

    move-result-wide v0

    iget-object p0, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;->recording:Landroid/util/Size;

    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->area(Landroid/util/Size;)J

    move-result-wide p0

    invoke-static {v0, v1, p0, p1}, Ljava/lang/Long;->compare(JJ)I

    move-result p0

    return p0
.end method

.method private static comparePreviewSizes(Landroid/util/Size;Landroid/util/Size;)I
    .locals 2

    .line 1721
    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->shortSide(Landroid/util/Size;)I

    move-result v0

    add-int/lit16 v0, v0, -0x2d0

    invoke-static {v0}, Ljava/lang/Math;->abs(I)I

    move-result v0

    .line 1722
    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->shortSide(Landroid/util/Size;)I

    move-result v1

    add-int/lit16 v1, v1, -0x2d0

    invoke-static {v1}, Ljava/lang/Math;->abs(I)I

    move-result v1

    if-eq v0, v1, :cond_0

    .line 1724
    invoke-static {v0, v1}, Ljava/lang/Integer;->compare(II)I

    move-result p0

    return p0

    .line 1725
    :cond_0
    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->area(Landroid/util/Size;)J

    move-result-wide v0

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->area(Landroid/util/Size;)J

    move-result-wide p0

    invoke-static {v0, v1, p0, p1}, Ljava/lang/Long;->compare(JJ)I

    move-result p0

    return p0
.end method

.method private static contains([II)Z
    .locals 4

    const/4 v0, 0x0

    if-nez p0, :cond_0

    return v0

    .line 1770
    :cond_0
    array-length v1, p0

    move v2, v0

    :goto_0
    if-ge v2, v1, :cond_2

    aget v3, p0, v2

    if-ne v3, p1, :cond_1

    const/4 p0, 0x1

    return p0

    :cond_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_2
    return v0
.end method

.method private createCaptureSession()V
    .locals 4

    .line 571
    const-string v0, "capture session requested: preview="

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraDevice:Landroid/hardware/camera2/CameraDevice;

    if-eqz v1, :cond_2

    .line 572
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewSurface:Landroid/view/Surface;

    if-eqz v2, :cond_2

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recordingSurface:Landroid/view/Surface;

    if-nez v2, :cond_0

    goto :goto_1

    .line 574
    :cond_0
    :try_start_0
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtimeNanos()J

    move-result-wide v2

    iput-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSessionRequestedNs:J

    .line 575
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewSize:Landroid/util/Size;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v0, ", recording="

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recordingSize:Landroid/util/Size;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v0, ", fpsRange="

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeFpsRange:Landroid/util/Range;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v2, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 578
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewSurface:Landroid/view/Surface;

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recordingSurface:Landroid/view/Surface;

    filled-new-array {v0, v2}, [Landroid/view/Surface;

    move-result-object v0

    .line 579
    invoke-static {v0}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object v0

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->sessionCallback:Landroid/hardware/camera2/CameraCaptureSession$StateCallback;

    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraHandler:Landroid/os/Handler;

    .line 578
    invoke-virtual {v1, v0, v2, v3}, Landroid/hardware/camera2/CameraDevice;->createCaptureSession(Ljava/util/List;Landroid/hardware/camera2/CameraCaptureSession$StateCallback;Landroid/os/Handler;)V
    :try_end_0
    .catch Landroid/hardware/camera2/CameraAccessException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception v0

    goto :goto_0

    :catch_1
    move-exception v0

    .line 584
    :goto_0
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    sget-object v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->FPS_60:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    if-ne v1, v2, :cond_1

    .line 585
    const-string v1, "60 fps session creation rejected"

    invoke-direct {p0, v1, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->fallbackTo30Fps(Ljava/lang/String;Ljava/lang/Exception;)V

    goto :goto_1

    .line 587
    :cond_1
    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->reportError(Ljava/lang/Exception;)V

    :cond_2
    :goto_1
    return-void
.end method

.method private createRecordingPipeline()V
    .locals 13

    .line 463
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->writer:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->timelineOffsetUs:J

    iget v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->outputSize:I

    iget v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->videoBitrate:I

    iget-object v6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    .line 468
    invoke-virtual {v6}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->getValue()I

    move-result v6

    iget-object v7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    iget-object v8, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->callback:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;

    .line 470
    invoke-static {v8}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    move-object v9, v8

    new-instance v8, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda13;

    invoke-direct {v8, v9}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda13;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;)V

    invoke-direct/range {v0 .. v8}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;JIIILorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$ErrorListener;)V

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recorder:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

    .line 472
    invoke-virtual {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->prepare()Landroid/view/Surface;

    move-result-object v3

    .line 473
    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recordingSize:Landroid/util/Size;

    iget v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->outputSize:I

    iget v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->sourceCropSize:I

    .line 478
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->isCameraTimestampRealtime()Z

    move-result v6

    iget-boolean v7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->compositionEnabled:Z

    iget-object v8, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    iget-object v11, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recorder:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->callback:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;

    .line 483
    invoke-static {v0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v12, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda14;

    invoke-direct {v12, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda14;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;)V

    const-wide/16 v9, 0x0

    invoke-direct/range {v1 .. v12}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;-><init>(Landroid/util/Size;Landroid/view/Surface;IIZZLorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;JLorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$FrameTimingListener;Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$ErrorListener;)V

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->glProcessor:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

    .line 485
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda15;

    invoke-direct {v0, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda15;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V

    invoke-virtual {v1, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->setFirstFrameListener(Ljava/lang/Runnable;)V

    .line 489
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->glProcessor:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

    invoke-virtual {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->start()Landroid/view/Surface;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recordingSurface:Landroid/view/Surface;

    return-void
.end method

.method private createRepeatingRequestBuilder(Z)Landroid/hardware/camera2/CaptureRequest$Builder;
    .locals 5

    .line 771
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraDevice:Landroid/hardware/camera2/CameraDevice;

    if-eqz v0, :cond_1

    .line 772
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewSurface:Landroid/view/Surface;

    if-eqz v1, :cond_1

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recordingSurface:Landroid/view/Surface;

    if-eqz v1, :cond_1

    const/4 v1, 0x3

    .line 775
    invoke-virtual {v0, v1}, Landroid/hardware/camera2/CameraDevice;->createCaptureRequest(I)Landroid/hardware/camera2/CaptureRequest$Builder;

    move-result-object v0

    .line 776
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewSurface:Landroid/view/Surface;

    invoke-virtual {v0, v2}, Landroid/hardware/camera2/CaptureRequest$Builder;->addTarget(Landroid/view/Surface;)V

    .line 777
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recordingSurface:Landroid/view/Surface;

    invoke-virtual {v0, v2}, Landroid/hardware/camera2/CaptureRequest$Builder;->addTarget(Landroid/view/Surface;)V

    .line 778
    sget-object v2, Landroid/hardware/camera2/CaptureRequest;->CONTROL_MODE:Landroid/hardware/camera2/CaptureRequest$Key;

    const/4 v3, 0x1

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-virtual {v0, v2, v4}, Landroid/hardware/camera2/CaptureRequest$Builder;->set(Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V

    .line 779
    sget-object v2, Landroid/hardware/camera2/CaptureRequest;->CONTROL_CAPTURE_INTENT:Landroid/hardware/camera2/CaptureRequest$Key;

    .line 781
    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    .line 779
    invoke-virtual {v0, v2, v1}, Landroid/hardware/camera2/CaptureRequest$Builder;->set(Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V

    .line 783
    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->setContinuousVideoAf(Landroid/hardware/camera2/CaptureRequest$Builder;)V

    .line 784
    invoke-direct {p0, v0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->setPreferredFps(Landroid/hardware/camera2/CaptureRequest$Builder;Z)V

    .line 785
    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->applyZoom(Landroid/hardware/camera2/CaptureRequest$Builder;)V

    .line 786
    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->applyTorch(Landroid/hardware/camera2/CaptureRequest$Builder;)V

    .line 787
    iget p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->requestGeneration:I

    add-int/2addr p1, v3

    iput p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->requestGeneration:I

    .line 788
    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {v0, p1}, Landroid/hardware/camera2/CaptureRequest$Builder;->setTag(Ljava/lang/Object;)V

    .line 789
    iget-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchVerificationPending:Z

    if-eqz p1, :cond_0

    iget p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->requestGeneration:I

    iput p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchVerificationGeneration:I

    :cond_0
    return-object v0

    .line 773
    :cond_1
    const-string p0, "Camera request surfaces are unavailable"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method private static elapsedMs(J)J
    .locals 2

    const-wide/16 v0, 0x0

    cmp-long v0, p0, v0

    if-nez v0, :cond_0

    const-wide/16 p0, -0x1

    return-wide p0

    .line 1615
    :cond_0
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtimeNanos()J

    move-result-wide v0

    sub-long/2addr v0, p0

    const-wide/32 p0, 0xf4240

    div-long/2addr v0, p0

    return-wide v0
.end method

.method private ensureThread()V
    .locals 2

    .line 333
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraThread:Landroid/os/HandlerThread;

    if-eqz v0, :cond_0

    return-void

    .line 334
    :cond_0
    new-instance v0, Landroid/os/HandlerThread;

    const-string v1, "RoundVideoCamera2"

    invoke-direct {v0, v1}, Landroid/os/HandlerThread;-><init>(Ljava/lang/String;)V

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraThread:Landroid/os/HandlerThread;

    .line 335
    invoke-virtual {v0}, Ljava/lang/Thread;->start()V

    .line 336
    new-instance v0, Landroid/os/Handler;

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraThread:Landroid/os/HandlerThread;

    invoke-virtual {v1}, Landroid/os/HandlerThread;->getLooper()Landroid/os/Looper;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraHandler:Landroid/os/Handler;

    return-void
.end method

.method private fallbackTo30Fps(Ljava/lang/String;Ljava/lang/Exception;)V
    .locals 4

    .line 1346
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->frameRateFallbackAttempted:Z

    if-eqz v0, :cond_1

    if-eqz p2, :cond_0

    goto :goto_0

    .line 1349
    :cond_0
    new-instance p2, Ljava/lang/IllegalStateException;

    const-string p1, "30 fps fallback session failed"

    invoke-direct {p2, p1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    .line 1347
    :goto_0
    invoke-direct {p0, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->reportError(Ljava/lang/Exception;)V

    return-void

    :cond_1
    const/4 v0, 0x1

    .line 1352
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->frameRateFallbackAttempted:Z

    .line 1353
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->closeCaptureSession()V

    .line 1354
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->regularFallbackPair:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;

    if-nez v0, :cond_2

    .line 1356
    new-instance p1, Ljava/lang/IllegalStateException;

    const-string v0, "Regular camera fallback is unavailable"

    invoke-direct {p1, v0, p2}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->reportError(Ljava/lang/Exception;)V

    return-void

    .line 1359
    :cond_2
    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->FPS_30:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    .line 1360
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->characteristics:Landroid/hardware/camera2/CameraCharacteristics;

    const/4 v2, 0x0

    if-nez v1, :cond_3

    move-object v1, v2

    goto :goto_1

    .line 1362
    :cond_3
    sget-object v3, Landroid/hardware/camera2/CameraCharacteristics;->CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES:Landroid/hardware/camera2/CameraCharacteristics$Key;

    invoke-virtual {v1, v3}, Landroid/hardware/camera2/CameraCharacteristics;->get(Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, [Landroid/util/Range;

    :goto_1
    const/16 v3, 0x1e

    .line 1363
    invoke-static {v1, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->findBestFpsRange([Landroid/util/Range;I)Landroid/util/Range;

    move-result-object v1

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeFpsRange:Landroid/util/Range;

    .line 1364
    iget-object v1, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;->preview:Landroid/util/Size;

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewSize:Landroid/util/Size;

    .line 1365
    iget-object v1, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;->recording:Landroid/util/Size;

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recordingSize:Landroid/util/Size;

    .line 1366
    iget v1, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;->cropSize:I

    iput v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->sourceCropSize:I

    .line 1367
    iget-object v0, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;->cameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeCameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    .line 1368
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewView:Landroid/view/TextureView;

    invoke-virtual {v0}, Landroid/view/TextureView;->getSurfaceTexture()Landroid/graphics/SurfaceTexture;

    move-result-object v0

    if-eqz v0, :cond_4

    .line 1370
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewSize:Landroid/util/Size;

    invoke-virtual {v1}, Landroid/util/Size;->getWidth()I

    move-result v1

    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewSize:Landroid/util/Size;

    invoke-virtual {v3}, Landroid/util/Size;->getHeight()I

    move-result v3

    invoke-virtual {v0, v1, v3}, Landroid/graphics/SurfaceTexture;->setDefaultBufferSize(II)V

    .line 1372
    :cond_4
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recorder:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

    if-eqz v0, :cond_5

    invoke-virtual {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->isStarted()Z

    move-result v0

    if-eqz v0, :cond_5

    .line 1373
    new-instance p1, Ljava/lang/IllegalStateException;

    const-string v0, "Unable to change encoder frame rate after recording started"

    invoke-direct {p1, v0, p2}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->reportError(Ljava/lang/Exception;)V

    return-void

    .line 1379
    :cond_5
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->glProcessor:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

    if-eqz v0, :cond_6

    .line 1380
    invoke-virtual {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->stop()V

    .line 1381
    iput-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->glProcessor:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

    .line 1383
    :cond_6
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recorder:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

    if-eqz v0, :cond_7

    .line 1384
    invoke-virtual {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->stop()V

    .line 1385
    iput-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recorder:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

    .line 1387
    :cond_7
    iput-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recordingSurface:Landroid/view/Surface;

    .line 1389
    :try_start_0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->createRecordingPipeline()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 1394
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->resetCaptureFpsMeasurement()V

    .line 1395
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "60 fps fallback: reason="

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    if-nez p2, :cond_8

    .line 1396
    const-string p1, ""

    goto :goto_2

    :cond_8
    new-instance p1, Ljava/lang/StringBuilder;

    const-string v2, ", error="

    invoke-direct {p1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    :goto_2
    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, ", preview="

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewSize:Landroid/util/Size;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p1, ", recording="

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recordingSize:Landroid/util/Size;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p1, ", crop="

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->sourceCropSize:I

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p1, ", fpsRange="

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeFpsRange:Landroid/util/Range;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    .line 1395
    invoke-virtual {v0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 1401
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraHandler:Landroid/os/Handler;

    .line 1402
    iget-boolean p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->active:Z

    if-eqz p2, :cond_9

    if-eqz p1, :cond_9

    new-instance p2, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda12;

    invoke-direct {p2, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda12;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V

    invoke-virtual {p1, p2}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    :cond_9
    return-void

    :catch_0
    move-exception p1

    .line 1391
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->reportError(Ljava/lang/Exception;)V

    return-void
.end method

.method private static filterSizesForFrameRate(Landroid/hardware/camera2/params/StreamConfigurationMap;[Landroid/util/Size;I)[Landroid/util/Size;
    .locals 6

    .line 1430
    new-instance v0, Ljava/util/ArrayList;

    array-length v1, p1

    invoke-direct {v0, v1}, Ljava/util/ArrayList;-><init>(I)V

    .line 1431
    array-length v1, p1

    const/4 v2, 0x0

    move v3, v2

    :goto_0
    if-ge v3, v1, :cond_1

    aget-object v4, p1, v3

    .line 1432
    invoke-static {p0, v4, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->supportsFrameDuration(Landroid/hardware/camera2/params/StreamConfigurationMap;Landroid/util/Size;I)Z

    move-result v5

    if-eqz v5, :cond_0

    invoke-virtual {v0, v4}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_0
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 1434
    :cond_1
    new-array p0, v2, [Landroid/util/Size;

    invoke-virtual {v0, p0}, Ljava/util/ArrayList;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;

    move-result-object p0

    check-cast p0, [Landroid/util/Size;

    return-object p0
.end method

.method private static findBestFpsRange([Landroid/util/Range;I)Landroid/util/Range;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "([",
            "Landroid/util/Range<",
            "Ljava/lang/Integer;",
            ">;I)",
            "Landroid/util/Range<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation

    const/4 v0, 0x0

    if-nez p0, :cond_0

    return-object v0

    .line 1412
    :cond_0
    array-length v1, p0

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v1, :cond_4

    aget-object v3, p0, v2

    .line 1413
    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-virtual {v3, v4}, Landroid/util/Range;->contains(Ljava/lang/Comparable;)Z

    move-result v4

    if-nez v4, :cond_1

    goto :goto_1

    :cond_1
    if-eqz v0, :cond_2

    .line 1415
    invoke-virtual {v3}, Landroid/util/Range;->getLower()Ljava/lang/Comparable;

    move-result-object v4

    check-cast v4, Ljava/lang/Integer;

    invoke-virtual {v4}, Ljava/lang/Integer;->intValue()I

    move-result v4

    invoke-virtual {v0}, Landroid/util/Range;->getLower()Ljava/lang/Comparable;

    move-result-object v5

    check-cast v5, Ljava/lang/Integer;

    invoke-virtual {v5}, Ljava/lang/Integer;->intValue()I

    move-result v5

    if-gt v4, v5, :cond_2

    .line 1416
    invoke-virtual {v3}, Landroid/util/Range;->getLower()Ljava/lang/Comparable;

    move-result-object v4

    check-cast v4, Ljava/lang/Integer;

    invoke-virtual {v0}, Landroid/util/Range;->getLower()Ljava/lang/Comparable;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/Integer;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_3

    .line 1417
    invoke-virtual {v3}, Landroid/util/Range;->getUpper()Ljava/lang/Comparable;

    move-result-object v4

    check-cast v4, Ljava/lang/Integer;

    invoke-virtual {v4}, Ljava/lang/Integer;->intValue()I

    move-result v4

    invoke-virtual {v0}, Landroid/util/Range;->getUpper()Ljava/lang/Comparable;

    move-result-object v5

    check-cast v5, Ljava/lang/Integer;

    invoke-virtual {v5}, Ljava/lang/Integer;->intValue()I

    move-result v5

    if-ge v4, v5, :cond_3

    :cond_2
    move-object v0, v3

    :cond_3
    :goto_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_4
    return-object v0
.end method

.method private static getMinimumFrameDurationNs(Landroid/hardware/camera2/CameraCharacteristics;Landroid/util/Size;)J
    .locals 3

    .line 1317
    sget-object v0, Landroid/hardware/camera2/CameraCharacteristics;->SCALER_STREAM_CONFIGURATION_MAP:Landroid/hardware/camera2/CameraCharacteristics$Key;

    invoke-virtual {p0, v0}, Landroid/hardware/camera2/CameraCharacteristics;->get(Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/hardware/camera2/params/StreamConfigurationMap;

    const-wide/16 v0, -0x1

    if-nez p0, :cond_0

    return-wide v0

    .line 1322
    :cond_0
    :try_start_0
    const-class v2, Landroid/graphics/SurfaceTexture;

    invoke-virtual {p0, v2, p1}, Landroid/hardware/camera2/params/StreamConfigurationMap;->getOutputMinFrameDuration(Ljava/lang/Class;Landroid/util/Size;)J

    move-result-wide p0
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    return-wide p0

    :catch_0
    return-wide v0
.end method

.method private static getSourceCropSize(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;)I
    .locals 2

    .line 1702
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;->LOW:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    if-ne p1, v0, :cond_0

    .line 1703
    invoke-virtual {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;->getSize()I

    move-result p0

    return p0

    .line 1705
    :cond_0
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;->P480:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    const/16 v1, 0x2d0

    if-ne p0, v0, :cond_2

    .line 1706
    sget-object p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;->HIGH:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    if-ne p1, p0, :cond_1

    const/16 p0, 0x3c0

    return p0

    :cond_1
    return v1

    .line 1708
    :cond_2
    sget-object p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;->HIGH:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    if-ne p1, p0, :cond_3

    return v1

    :cond_3
    const/16 p0, 0x21c

    return p0
.end method

.method private static hardwareLevelName(Ljava/lang/Integer;)Ljava/lang/String;
    .locals 2

    if-nez p0, :cond_0

    .line 1582
    const-string p0, "unknown"

    return-object p0

    .line 1583
    :cond_0
    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    const/4 v1, 0x2

    if-ne v0, v1, :cond_1

    const-string p0, "LEGACY"

    return-object p0

    .line 1584
    :cond_1
    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    if-nez v0, :cond_2

    const-string p0, "LIMITED"

    return-object p0

    .line 1585
    :cond_2
    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    const/4 v1, 0x1

    if-ne v0, v1, :cond_3

    const-string p0, "FULL"

    return-object p0

    .line 1586
    :cond_3
    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    const/4 v1, 0x3

    if-ne v0, v1, :cond_4

    const-string p0, "LEVEL_3"

    return-object p0

    .line 1587
    :cond_4
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x1c

    if-lt v0, v1, :cond_5

    .line 1588
    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    const/4 v1, 0x4

    if-ne v0, v1, :cond_5

    .line 1589
    const-string p0, "EXTERNAL"

    return-object p0

    .line 1591
    :cond_5
    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static hasFlashUnit(Landroid/hardware/camera2/CameraCharacteristics;)Z
    .locals 2

    .line 1226
    sget-object v0, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    sget-object v1, Landroid/hardware/camera2/CameraCharacteristics;->FLASH_INFO_AVAILABLE:Landroid/hardware/camera2/CameraCharacteristics$Key;

    .line 1227
    invoke-virtual {p0, v1}, Landroid/hardware/camera2/CameraCharacteristics;->get(Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;

    move-result-object p0

    .line 1226
    invoke-virtual {v0, p0}, Ljava/lang/Boolean;->equals(Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method private static hasSameAspectRatio(Landroid/util/Size;Landroid/util/Size;)Z
    .locals 4

    .line 1740
    invoke-virtual {p0}, Landroid/util/Size;->getWidth()I

    move-result v0

    int-to-long v0, v0

    invoke-virtual {p1}, Landroid/util/Size;->getHeight()I

    move-result v2

    int-to-long v2, v2

    mul-long/2addr v0, v2

    .line 1741
    invoke-virtual {p0}, Landroid/util/Size;->getHeight()I

    move-result p0

    int-to-long v2, p0

    invoke-virtual {p1}, Landroid/util/Size;->getWidth()I

    move-result p0

    int-to-long p0, p0

    mul-long/2addr v2, p0

    cmp-long p0, v0, v2

    if-nez p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private isCameraTimestampRealtime()Z
    .locals 2

    .line 1209
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->characteristics:Landroid/hardware/camera2/CameraCharacteristics;

    const/4 v0, 0x0

    if-nez p0, :cond_0

    return v0

    .line 1210
    :cond_0
    sget-object v1, Landroid/hardware/camera2/CameraCharacteristics;->SENSOR_INFO_TIMESTAMP_SOURCE:Landroid/hardware/camera2/CameraCharacteristics$Key;

    invoke-virtual {p0, v1}, Landroid/hardware/camera2/CameraCharacteristics;->get(Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Integer;

    if-eqz p0, :cond_1

    .line 1212
    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    const/4 v1, 0x1

    if-ne p0, v1, :cond_1

    return v1

    :cond_1
    return v0
.end method

.method private isOutputRotationSwapped()Z
    .locals 3

    .line 1491
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->characteristics:Landroid/hardware/camera2/CameraCharacteristics;

    const/4 v1, 0x0

    if-eqz v0, :cond_4

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewView:Landroid/view/TextureView;

    invoke-virtual {v0}, Landroid/view/View;->getDisplay()Landroid/view/Display;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_1

    .line 1492
    :cond_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->characteristics:Landroid/hardware/camera2/CameraCharacteristics;

    sget-object v2, Landroid/hardware/camera2/CameraCharacteristics;->SENSOR_ORIENTATION:Landroid/hardware/camera2/CameraCharacteristics$Key;

    invoke-virtual {v0, v2}, Landroid/hardware/camera2/CameraCharacteristics;->get(Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    if-nez v0, :cond_1

    return v1

    .line 1494
    :cond_1
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewView:Landroid/view/TextureView;

    invoke-virtual {p0}, Landroid/view/View;->getDisplay()Landroid/view/Display;

    move-result-object p0

    invoke-virtual {p0}, Landroid/view/Display;->getRotation()I

    move-result p0

    const/16 v2, 0x5a

    mul-int/2addr p0, v2

    .line 1495
    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    sub-int/2addr v0, p0

    add-int/lit16 v0, v0, 0x168

    rem-int/lit16 v0, v0, 0x168

    if-eq v0, v2, :cond_3

    const/16 p0, 0x10e

    if-ne v0, p0, :cond_2

    goto :goto_0

    :cond_2
    return v1

    :cond_3
    :goto_0
    const/4 p0, 0x1

    return p0

    :cond_4
    :goto_1
    return v1
.end method

.method private static isPreferredCameraCandidate(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraCandidate;Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraCandidate;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)Z
    .locals 1

    .line 1220
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;->BACK:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    if-ne p2, v0, :cond_0

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraCandidate;->characteristics:Landroid/hardware/camera2/CameraCharacteristics;

    .line 1221
    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->hasFlashUnit(Landroid/hardware/camera2/CameraCharacteristics;)Z

    move-result p0

    if-eqz p0, :cond_0

    iget-object p0, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraCandidate;->characteristics:Landroid/hardware/camera2/CameraCharacteristics;

    .line 1222
    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->hasFlashUnit(Landroid/hardware/camera2/CameraCharacteristics;)Z

    move-result p0

    if-nez p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private isTorchAvailable()Z
    .locals 2

    .line 1203
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;->BACK:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    if-ne v0, v1, :cond_0

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->characteristics:Landroid/hardware/camera2/CameraCharacteristics;

    if-eqz p0, :cond_0

    .line 1205
    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->hasFlashUnit(Landroid/hardware/camera2/CameraCharacteristics;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private static isValidTierSize(Landroid/util/Size;I)Z
    .locals 4

    mul-int/lit8 v0, p1, 0xf

    .line 1745
    div-int/lit8 v0, v0, 0x64

    add-int/2addr v0, p1

    mul-int/lit8 v1, p1, 0x2

    const/16 v2, 0x780

    .line 1747
    invoke-static {v2, v1}, Ljava/lang/Math;->min(II)I

    move-result v1

    .line 1748
    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->shortSide(Landroid/util/Size;)I

    move-result v2

    .line 1749
    invoke-virtual {p0}, Landroid/util/Size;->getWidth()I

    move-result v3

    invoke-virtual {p0}, Landroid/util/Size;->getHeight()I

    move-result p0

    invoke-static {v3, p0}, Ljava/lang/Math;->max(II)I

    move-result p0

    if-lt v2, p1, :cond_0

    if-gt v2, v0, :cond_0

    if-gt p0, v1, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private static isWithinAbsoluteLimit(Landroid/util/Size;)Z
    .locals 2

    .line 1756
    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->shortSide(Landroid/util/Size;)I

    move-result v0

    const/16 v1, 0x440

    if-gt v0, v1, :cond_0

    .line 1757
    invoke-virtual {p0}, Landroid/util/Size;->getWidth()I

    move-result v0

    invoke-virtual {p0}, Landroid/util/Size;->getHeight()I

    move-result p0

    invoke-static {v0, p0}, Ljava/lang/Math;->max(II)I

    move-result p0

    const/16 v0, 0x780

    if-gt p0, v0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private synthetic lambda$createRecordingPipeline$6()V
    .locals 2

    .line 486
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraHandler:Landroid/os/Handler;

    if-eqz v0, :cond_0

    .line 487
    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda16;

    invoke-direct {v1, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda16;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    :cond_0
    return-void
.end method

.method private synthetic lambda$new$0(Landroid/view/View;IIIIIIII)V
    .locals 0

    .line 187
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->updatePreviewTransform()V

    return-void
.end method

.method private synthetic lambda$new$1()V
    .locals 2

    .line 190
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->commonStartRecorder:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

    .line 191
    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->active:Z

    if-eqz v1, :cond_1

    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->stopping:Z

    if-nez v1, :cond_1

    if-eqz v0, :cond_1

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recorder:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

    if-eq v1, v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    .line 192
    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->commonStartRecorder:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

    .line 193
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Timed out waiting for synchronized audio and video start"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->reportError(Ljava/lang/Exception;)V

    :cond_1
    :goto_0
    return-void
.end method

.method private synthetic lambda$release$5(Landroid/os/HandlerThread;)V
    .locals 5

    .line 316
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cancelCommonStartWait()V

    .line 317
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recorder:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

    if-eqz v0, :cond_0

    .line 318
    invoke-virtual {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->requestStop()J

    move-result-wide v0

    .line 319
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->glProcessor:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

    if-eqz v2, :cond_0

    const-wide v3, 0x7fffffffffffffffL

    cmp-long v3, v0, v3

    if-eqz v3, :cond_0

    .line 320
    invoke-virtual {v2, v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->stopRecordingAt(J)V

    .line 323
    :cond_0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->closeCameraAndSurfaces()V

    .line 324
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recorder:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

    if-eqz v0, :cond_1

    .line 325
    invoke-virtual {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->stop()V

    const/4 v0, 0x0

    .line 326
    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recorder:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

    .line 328
    :cond_1
    invoke-virtual {p1}, Landroid/os/HandlerThread;->quitSafely()Z

    return-void
.end method

.method private synthetic lambda$setCameraFacing$2(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V
    .locals 0

    .line 250
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->switchCameraIfNeeded(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V

    return-void
.end method

.method private synthetic lambda$setTorchEnabled$3(Z)V
    .locals 3

    .line 267
    iput-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchEnabled:Z

    .line 268
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->isTorchAvailable()Z

    move-result v0

    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchVerificationPending:Z

    const/4 v0, 0x0

    .line 269
    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchVerificationFrames:I

    .line 270
    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchVerificationRetries:I

    .line 271
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "torch requested: enabled="

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string p1, ", available="

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 272
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->isTorchAvailable()Z

    move-result p1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string p1, ", cameraId="

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraId:Ljava/lang/String;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, ", facing="

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    .line 271
    invoke-virtual {v0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 275
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->applyRepeatingRequest()V

    return-void
.end method

.method private synthetic lambda$startRecordingAfterFirstFrame$10(Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V
    .locals 1

    .line 682
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda3;

    invoke-direct {v0, p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda3;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->postToCameraThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method private synthetic lambda$startRecordingAfterFirstFrame$7(Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V
    .locals 0

    .line 680
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->startVideoAfterAudioReady(Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V

    return-void
.end method

.method private synthetic lambda$startRecordingAfterFirstFrame$8(Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V
    .locals 1

    .line 679
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda2;

    invoke-direct {v0, p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda2;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->postToCameraThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method private synthetic lambda$startRecordingAfterFirstFrame$9(Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V
    .locals 0

    .line 683
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->announceRecordingStarted(Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V

    return-void
.end method

.method private synthetic lambda$stopSegment$4()V
    .locals 6

    const/4 v0, 0x0

    .line 287
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->active:Z

    .line 288
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cancelCommonStartWait()V

    .line 289
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recorder:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

    if-eqz v1, :cond_0

    .line 290
    invoke-virtual {v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->requestStop()J

    move-result-wide v1

    .line 291
    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->glProcessor:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

    if-eqz v3, :cond_0

    const-wide v4, 0x7fffffffffffffffL

    cmp-long v4, v1, v4

    if-eqz v4, :cond_0

    .line 292
    invoke-virtual {v3, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->stopRecordingAt(J)V

    .line 295
    :cond_0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->closeCameraAndSurfaces()V

    .line 296
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recorder:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

    if-eqz v1, :cond_1

    .line 297
    invoke-virtual {v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->stop()V

    const/4 v1, 0x0

    .line 298
    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recorder:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

    .line 300
    :cond_1
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->stopping:Z

    .line 301
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->callback:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;

    invoke-interface {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;->onRecordingStopped()V

    return-void
.end method

.method private logCameraConfigurationOnce(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Ljava/lang/String;Landroid/hardware/camera2/CameraCharacteristics;[Landroid/util/Size;)V
    .locals 7

    .line 1544
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;->FRONT:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    const/4 v1, 0x1

    if-ne p1, v0, :cond_1

    .line 1545
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->frontConfigurationLogged:Z

    if-eqz v0, :cond_0

    goto :goto_0

    .line 1546
    :cond_0
    iput-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->frontConfigurationLogged:Z

    goto :goto_1

    .line 1548
    :cond_1
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->backConfigurationLogged:Z

    if-eqz v0, :cond_2

    :goto_0
    return-void

    .line 1549
    :cond_2
    iput-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->backConfigurationLogged:Z

    .line 1551
    :goto_1
    sget-object v0, Landroid/hardware/camera2/CameraCharacteristics;->INFO_SUPPORTED_HARDWARE_LEVEL:Landroid/hardware/camera2/CameraCharacteristics$Key;

    invoke-virtual {p3, v0}, Landroid/hardware/camera2/CameraCharacteristics;->get(Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    .line 1554
    sget-object v1, Landroid/hardware/camera2/CameraCharacteristics;->SENSOR_ORIENTATION:Landroid/hardware/camera2/CameraCharacteristics$Key;

    invoke-virtual {p3, v1}, Landroid/hardware/camera2/CameraCharacteristics;->get(Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Integer;

    .line 1557
    sget-object v2, Landroid/hardware/camera2/CameraCharacteristics;->CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES:Landroid/hardware/camera2/CameraCharacteristics$Key;

    invoke-virtual {p3, v2}, Landroid/hardware/camera2/CameraCharacteristics;->get(Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, [Landroid/util/Range;

    .line 1560
    sget-object v3, Landroid/hardware/camera2/CameraCharacteristics;->CONTROL_AE_AVAILABLE_MODES:Landroid/hardware/camera2/CameraCharacteristics$Key;

    invoke-virtual {p3, v3}, Landroid/hardware/camera2/CameraCharacteristics;->get(Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, [I

    .line 1563
    sget-object v4, Landroid/hardware/camera2/CameraCharacteristics;->FLASH_INFO_AVAILABLE:Landroid/hardware/camera2/CameraCharacteristics$Key;

    invoke-virtual {p3, v4}, Landroid/hardware/camera2/CameraCharacteristics;->get(Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/Boolean;

    .line 1566
    sget-object v5, Landroid/hardware/camera2/CameraCharacteristics;->SENSOR_INFO_ACTIVE_ARRAY_SIZE:Landroid/hardware/camera2/CameraCharacteristics$Key;

    invoke-virtual {p3, v5}, Landroid/hardware/camera2/CameraCharacteristics;->get(Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;

    move-result-object p3

    check-cast p3, Landroid/graphics/Rect;

    .line 1569
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v5, Ljava/lang/StringBuilder;

    const-string v6, "camera capabilities: id="

    invoke-direct {v5, v6}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v5, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p2, ", facing="

    invoke-virtual {v5, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p1, ", hardwareLevel="

    invoke-virtual {v5, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1571
    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->hardwareLevelName(Ljava/lang/Integer;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v5, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, ", sensorOrientation="

    invoke-virtual {v5, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p1, ", activeArray="

    invoke-virtual {v5, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p1, ", flashAvailable="

    invoke-virtual {v5, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p1, ", aeModes="

    invoke-virtual {v5, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1575
    invoke-static {v3}, Ljava/util/Arrays;->toString([I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v5, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, ", fpsRanges="

    invoke-virtual {v5, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1576
    invoke-static {v2}, Ljava/util/Arrays;->toString([Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v5, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, ", outputSizes="

    invoke-virtual {v5, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1577
    invoke-static {p4}, Ljava/util/Arrays;->toString([Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v5, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    .line 1569
    invoke-virtual {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    return-void
.end method

.method private logPreviewFrameMetrics(JJ)V
    .locals 8

    .line 921
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewTimestampLastNs:J

    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewTimestampFirstNs:J

    sub-long/2addr v0, v2

    const-wide/16 v2, 0x0

    cmp-long v2, v0, v2

    const v3, 0x4e6e6b28    # 1.0E9f

    if-lez v2, :cond_0

    .line 922
    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewTimestampFrames:J

    const-wide/16 v6, 0x1

    cmp-long v2, v4, v6

    if-lez v2, :cond_0

    sub-long/2addr v4, v6

    long-to-float v2, v4

    mul-float/2addr v2, v3

    long-to-float v0, v0

    div-float/2addr v2, v0

    goto :goto_0

    :cond_0
    const/4 v2, 0x0

    .line 925
    :goto_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v4, "preview frame delivery: callbackFps="

    invoke-direct {v1, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewFrames:J

    long-to-float v4, v4

    mul-float/2addr v4, v3

    long-to-float p3, p3

    div-float/2addr v4, p3

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string p3, ", timestampFps="

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string p3, ", callbackIntervalMs={avg="

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewIntervalTotalNs:J

    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewIntervalCount:J

    .line 929
    invoke-static {p3, p4, v2, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->averageMs(JJ)F

    move-result p3

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string p3, ", min="

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewIntervalMinimumNs:J

    .line 930
    invoke-static {p3, p4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->nanosToMs(J)F

    move-result p3

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string p3, ", max="

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewIntervalMaximumNs:J

    .line 931
    invoke-static {p3, p4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->nanosToMs(J)F

    move-result p3

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string p3, ", jitter="

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewIntervalTotalNs:J

    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewIntervalSquaredTotalNs:D

    iget-wide v6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewIntervalCount:J

    .line 932
    invoke-static/range {v2 .. v7}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->standardDeviationMs(JDJ)F

    move-result p3

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string p3, "}, gaps={over50ms="

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewGapsOver50Ms:J

    invoke-virtual {v1, p3, p4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p3, ", over100ms="

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewGapsOver100Ms:J

    invoke-virtual {v1, p3, p4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p3, "}, nonMonotonicTimestamps="

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewTimestampNonMonotonic:J

    invoke-virtual {v1, p3, p4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p3, ", viewState={shown="

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewView:Landroid/view/TextureView;

    .line 939
    invoke-virtual {p3}, Landroid/view/View;->isShown()Z

    move-result p3

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string p3, ", alpha="

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewView:Landroid/view/TextureView;

    .line 940
    invoke-virtual {p3}, Landroid/view/View;->getAlpha()F

    move-result p3

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string p3, ", windowVisibility="

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewView:Landroid/view/TextureView;

    .line 941
    invoke-virtual {p3}, Landroid/view/View;->getWindowVisibility()I

    move-result p3

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p3, "}"

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p3

    .line 925
    invoke-virtual {v0, p3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 942
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->resetPreviewFrameMetrics()V

    .line 943
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewFpsStartedNs:J

    return-void
.end method

.method private maybeOpenCamera()V
    .locals 2

    .line 415
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraHandler:Landroid/os/Handler;

    .line 416
    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->active:Z

    if-eqz v1, :cond_1

    if-eqz v0, :cond_1

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewView:Landroid/view/TextureView;

    invoke-virtual {v1}, Landroid/view/TextureView;->isAvailable()Z

    move-result v1

    if-nez v1, :cond_0

    goto :goto_0

    .line 417
    :cond_0
    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda7;

    invoke-direct {v1, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda7;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    :cond_1
    :goto_0
    return-void
.end method

.method private static nanosToMs(J)F
    .locals 0

    long-to-float p0, p0

    const p1, 0x49742400    # 1000000.0f

    div-float/2addr p0, p1

    return p0
.end method

.method private openCameraIfReady()V
    .locals 5
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "MissingPermission"
        }
    .end annotation

    .line 422
    const-string v0, "camera open requested: id="

    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->active:Z

    if-eqz v1, :cond_7

    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->opening:Z

    if-nez v1, :cond_7

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraDevice:Landroid/hardware/camera2/CameraDevice;

    if-eqz v1, :cond_0

    goto/16 :goto_3

    .line 423
    :cond_0
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->context:Landroid/content/Context;

    const-string v2, "android.permission.CAMERA"

    invoke-static {v1, v2}, Landroidx/core/content/ContextCompat;->checkSelfPermission(Landroid/content/Context;Ljava/lang/String;)I

    move-result v1

    if-eqz v1, :cond_1

    .line 425
    new-instance v0, Ljava/lang/SecurityException;

    const-string v1, "Camera permission is not granted"

    invoke-direct {v0, v1}, Ljava/lang/SecurityException;-><init>(Ljava/lang/String;)V

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->reportError(Ljava/lang/Exception;)V

    return-void

    .line 428
    :cond_1
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->context:Landroid/content/Context;

    const-string v2, "android.permission.RECORD_AUDIO"

    invoke-static {v1, v2}, Landroidx/core/content/ContextCompat;->checkSelfPermission(Landroid/content/Context;Ljava/lang/String;)I

    move-result v1

    if-eqz v1, :cond_2

    .line 430
    new-instance v0, Ljava/lang/SecurityException;

    const-string v1, "Audio recording permission is not granted"

    invoke-direct {v0, v1}, Ljava/lang/SecurityException;-><init>(Ljava/lang/String;)V

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->reportError(Ljava/lang/Exception;)V

    return-void

    .line 434
    :cond_2
    :try_start_0
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->requestedFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    invoke-direct {p0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->selectCameraAndSizes(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V

    .line 435
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewView:Landroid/view/TextureView;

    invoke-virtual {v1}, Landroid/view/TextureView;->getSurfaceTexture()Landroid/graphics/SurfaceTexture;

    move-result-object v1

    if-nez v1, :cond_3

    goto/16 :goto_3

    .line 437
    :cond_3
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewSize:Landroid/util/Size;

    invoke-virtual {v2}, Landroid/util/Size;->getWidth()I

    move-result v2

    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewSize:Landroid/util/Size;

    invoke-virtual {v3}, Landroid/util/Size;->getHeight()I

    move-result v3

    invoke-virtual {v1, v2, v3}, Landroid/graphics/SurfaceTexture;->setDefaultBufferSize(II)V

    .line 438
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewSurface:Landroid/view/Surface;

    if-nez v2, :cond_4

    new-instance v2, Landroid/view/Surface;

    invoke-direct {v2, v1}, Landroid/view/Surface;-><init>(Landroid/graphics/SurfaceTexture;)V

    iput-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewSurface:Landroid/view/Surface;

    goto :goto_0

    :catch_0
    move-exception v0

    goto :goto_2

    .line 439
    :cond_4
    :goto_0
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recorder:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

    if-nez v1, :cond_5

    .line 440
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->createRecordingPipeline()V

    goto :goto_1

    .line 441
    :cond_5
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->glProcessor:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

    if-eqz v1, :cond_6

    .line 442
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recordingSize:Landroid/util/Size;

    iget v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->sourceCropSize:I

    .line 445
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->isCameraTimestampRealtime()Z

    move-result v4

    .line 442
    invoke-virtual {v1, v2, v3, v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->updateInputConfiguration(Landroid/util/Size;IZ)V

    .line 447
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->glProcessor:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

    invoke-virtual {v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->getCameraSurface()Landroid/view/Surface;

    move-result-object v1

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recordingSurface:Landroid/view/Surface;

    :cond_6
    :goto_1
    const/4 v1, 0x1

    .line 449
    iput-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->opening:Z

    .line 450
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtimeNanos()J

    move-result-wide v1

    iput-wide v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraOpenRequestedNs:J

    .line 451
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraId:Ljava/lang/String;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ", preview="

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewSize:Landroid/util/Size;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v0, ", recording="

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recordingSize:Landroid/util/Size;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v0, ", crop="

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->sourceCropSize:I

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 455
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraManager:Landroid/hardware/camera2/CameraManager;

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraId:Ljava/lang/String;

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->deviceCallback:Landroid/hardware/camera2/CameraDevice$StateCallback;

    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraHandler:Landroid/os/Handler;

    invoke-virtual {v0, v1, v2, v3}, Landroid/hardware/camera2/CameraManager;->openCamera(Ljava/lang/String;Landroid/hardware/camera2/CameraDevice$StateCallback;Landroid/os/Handler;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :goto_2
    const/4 v1, 0x0

    .line 457
    iput-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->opening:Z

    .line 458
    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->reportError(Ljava/lang/Exception;)V

    :cond_7
    :goto_3
    return-void
.end method

.method private postToCameraThread(Ljava/lang/Runnable;)V
    .locals 0

    .line 718
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraHandler:Landroid/os/Handler;

    if-eqz p0, :cond_0

    .line 719
    invoke-virtual {p0, p1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    :cond_0
    return-void
.end method

.method private readMaximumZoomRatio(Landroid/hardware/camera2/CameraCharacteristics;)F
    .locals 2

    .line 1192
    sget p0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v0, 0x1e

    const/high16 v1, 0x3f800000    # 1.0f

    if-lt p0, v0, :cond_0

    .line 1193
    invoke-static {}, Landroidx/camera/camera2/compat/workaround/CameraMetadataSafeGetterKt$$ExternalSyntheticApiModelOutline0;->m()Landroid/hardware/camera2/CameraCharacteristics$Key;

    move-result-object p0

    invoke-virtual {p1, p0}, Landroid/hardware/camera2/CameraCharacteristics;->get(Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/util/Range;

    if-eqz p0, :cond_1

    .line 1194
    invoke-virtual {p0}, Landroid/util/Range;->getUpper()Ljava/lang/Comparable;

    move-result-object p0

    check-cast p0, Ljava/lang/Float;

    invoke-virtual {p0}, Ljava/lang/Float;->floatValue()F

    move-result p0

    goto :goto_0

    .line 1196
    :cond_0
    sget-object p0, Landroid/hardware/camera2/CameraCharacteristics;->SCALER_AVAILABLE_MAX_DIGITAL_ZOOM:Landroid/hardware/camera2/CameraCharacteristics$Key;

    invoke-virtual {p1, p0}, Landroid/hardware/camera2/CameraCharacteristics;->get(Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Float;

    if-eqz p0, :cond_1

    .line 1197
    invoke-virtual {p0}, Ljava/lang/Float;->floatValue()F

    move-result p0

    goto :goto_0

    :cond_1
    move p0, v1

    :goto_0
    const/high16 p1, 0x40800000    # 4.0f

    .line 1199
    invoke-static {p0, v1, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->clamp(FFF)F

    move-result p0

    return p0
.end method

.method private recordCaptureSensorInterval(J)V
    .locals 6

    .line 947
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorIntervalCount:J

    const-wide/16 v2, 0x1

    add-long/2addr v0, v2

    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorIntervalCount:J

    .line 948
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorIntervalTotalNs:J

    add-long/2addr v0, p1

    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorIntervalTotalNs:J

    .line 949
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorIntervalSquaredTotalNs:D

    long-to-double v4, p1

    mul-double/2addr v4, v4

    add-double/2addr v0, v4

    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorIntervalSquaredTotalNs:D

    .line 950
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorIntervalMinimumNs:J

    const-wide/16 v4, 0x0

    cmp-long v4, v0, v4

    if-eqz v4, :cond_0

    cmp-long v0, p1, v0

    if-gez v0, :cond_1

    .line 952
    :cond_0
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorIntervalMinimumNs:J

    .line 954
    :cond_1
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorIntervalMaximumNs:J

    invoke-static {v0, v1, p1, p2}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v0

    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorIntervalMaximumNs:J

    const-wide/32 v0, 0x2faf080

    cmp-long v0, p1, v0

    if-lez v0, :cond_2

    .line 955
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorGapsOver50Ms:J

    add-long/2addr v0, v2

    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorGapsOver50Ms:J

    :cond_2
    const-wide/32 v0, 0x5f5e100

    cmp-long p1, p1, v0

    if-lez p1, :cond_3

    .line 956
    iget-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorGapsOver100Ms:J

    add-long/2addr p1, v2

    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorGapsOver100Ms:J

    :cond_3
    return-void
.end method

.method private recordPreviewInterval(J)V
    .locals 6

    .line 909
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewIntervalCount:J

    const-wide/16 v2, 0x1

    add-long/2addr v0, v2

    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewIntervalCount:J

    .line 910
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewIntervalTotalNs:J

    add-long/2addr v0, p1

    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewIntervalTotalNs:J

    .line 911
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewIntervalSquaredTotalNs:D

    long-to-double v4, p1

    mul-double/2addr v4, v4

    add-double/2addr v0, v4

    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewIntervalSquaredTotalNs:D

    .line 912
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewIntervalMinimumNs:J

    const-wide/16 v4, 0x0

    cmp-long v4, v0, v4

    if-eqz v4, :cond_0

    cmp-long v0, p1, v0

    if-gez v0, :cond_1

    .line 913
    :cond_0
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewIntervalMinimumNs:J

    .line 915
    :cond_1
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewIntervalMaximumNs:J

    invoke-static {v0, v1, p1, p2}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v0

    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewIntervalMaximumNs:J

    const-wide/32 v0, 0x2faf080

    cmp-long v0, p1, v0

    if-lez v0, :cond_2

    .line 916
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewGapsOver50Ms:J

    add-long/2addr v0, v2

    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewGapsOver50Ms:J

    :cond_2
    const-wide/32 v0, 0x5f5e100

    cmp-long p1, p1, v0

    if-lez p1, :cond_3

    .line 917
    iget-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewGapsOver100Ms:J

    add-long/2addr p1, v2

    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewGapsOver100Ms:J

    :cond_3
    return-void
.end method

.method private recoverFromClosedCamera(Landroid/hardware/camera2/CameraCaptureSession;Ljava/lang/IllegalStateException;)V
    .locals 2

    .line 749
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->active:Z

    if-eqz v0, :cond_2

    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->stopping:Z

    if-nez v0, :cond_2

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSession:Landroid/hardware/camera2/CameraCaptureSession;

    if-eq p1, v0, :cond_0

    goto :goto_0

    .line 754
    :cond_0
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "repeating request rejected by closed camera; reopening device: "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    .line 755
    invoke-virtual {p2}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    .line 754
    invoke-virtual {p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 756
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->closeCaptureSession()V

    .line 757
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraDevice:Landroid/hardware/camera2/CameraDevice;

    const/4 p2, 0x0

    .line 758
    iput-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraDevice:Landroid/hardware/camera2/CameraDevice;

    const/4 p2, 0x0

    .line 759
    iput-boolean p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->opening:Z

    if-eqz p1, :cond_1

    const/4 p2, 0x1

    .line 761
    iput-boolean p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->reopenAfterCameraClose:Z

    .line 762
    invoke-virtual {p1}, Landroid/hardware/camera2/CameraDevice;->close()V

    return-void

    .line 764
    :cond_1
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->openCameraIfReady()V

    return-void

    .line 750
    :cond_2
    :goto_0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance p1, Ljava/lang/StringBuilder;

    const-string v0, "stale repeating request rejection ignored: "

    invoke-direct {p1, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    return-void
.end method

.method private reportError(Ljava/lang/Exception;)V
    .locals 2

    .line 1534
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    const-string v1, "camera error"

    invoke-virtual {v0, v1, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->error(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 1535
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->callback:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;

    invoke-interface {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;->onError(Ljava/lang/Exception;)V

    return-void
.end method

.method private resetCaptureFpsMeasurement()V
    .locals 4

    const-wide/16 v0, 0x0

    .line 1438
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureFpsFirstTimestampNs:J

    .line 1439
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureFpsLastTimestampNs:J

    .line 1440
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureFrames:J

    .line 1441
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureLastCallbackNs:J

    .line 1442
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorIntervalCount:J

    .line 1443
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorIntervalTotalNs:J

    const-wide/16 v2, 0x0

    .line 1444
    iput-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorIntervalSquaredTotalNs:D

    .line 1445
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorIntervalMinimumNs:J

    .line 1446
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorIntervalMaximumNs:J

    .line 1447
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorGapsOver50Ms:J

    .line 1448
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSensorGapsOver100Ms:J

    .line 1449
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureCallbackIntervalCount:J

    .line 1450
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureCallbackIntervalTotalNs:J

    .line 1451
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureCallbackIntervalMaximumNs:J

    return-void
.end method

.method private resetPreviewFrameMetrics()V
    .locals 4

    const-wide/16 v0, 0x0

    .line 960
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewFpsStartedNs:J

    .line 961
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewFrames:J

    .line 962
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewLastCallbackNs:J

    .line 963
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewLastTimestampNs:J

    .line 964
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewIntervalCount:J

    .line 965
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewIntervalTotalNs:J

    const-wide/16 v2, 0x0

    .line 966
    iput-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewIntervalSquaredTotalNs:D

    .line 967
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewIntervalMinimumNs:J

    .line 968
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewIntervalMaximumNs:J

    .line 969
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewGapsOver50Ms:J

    .line 970
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewGapsOver100Ms:J

    .line 971
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewTimestampFrames:J

    .line 972
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewTimestampFirstNs:J

    .line 973
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewTimestampLastNs:J

    .line 974
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewTimestampNonMonotonic:J

    return-void
.end method

.method private resolveFrameRate(Ljava/lang/String;Landroid/hardware/camera2/CameraCharacteristics;Landroid/hardware/camera2/params/StreamConfigurationMap;[Landroid/util/Size;Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;)Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;
    .locals 9

    .line 1248
    sget-object v0, Landroid/hardware/camera2/CameraCharacteristics;->CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES:Landroid/hardware/camera2/CameraCharacteristics$Key;

    invoke-virtual {p2, v0}, Landroid/hardware/camera2/CameraCharacteristics;->get(Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, [Landroid/util/Range;

    .line 1251
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->requestedFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->FPS_30:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    const/16 v2, 0x1e

    const-string v3, ", mode=REGULAR, range="

    const-string v4, ", requested="

    const-string v5, "fps selection: id="

    if-eq v0, v1, :cond_2

    iget-boolean v6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->force30Fps:Z

    if-eqz v6, :cond_0

    goto/16 :goto_1

    .line 1263
    :cond_0
    invoke-virtual {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->getValue()I

    move-result v0

    .line 1264
    invoke-static {p2, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->findBestFpsRange([Landroid/util/Range;I)Landroid/util/Range;

    move-result-object v1

    .line 1265
    const-string v6, ", regular "

    if-eqz v1, :cond_1

    .line 1266
    invoke-static {p3, p4, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->filterSizesForFrameRate(Landroid/hardware/camera2/params/StreamConfigurationMap;[Landroid/util/Size;I)[Landroid/util/Size;

    move-result-object p3

    .line 1268
    :try_start_0
    iget-object p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->outputResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    iget-object v7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->requestedCameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    invoke-static {p3, p4, v7}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->chooseOutputPair([Landroid/util/Size;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;)Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;

    move-result-object p4

    .line 1273
    iget-object v7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8, v5}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v8, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v3, ", compatibleSizes="

    invoke-virtual {v8, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1276
    invoke-static {p3}, Ljava/util/Arrays;->toString([Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v8, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    .line 1273
    invoke-virtual {v7, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 1277
    new-instance v3, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;

    sget-object v7, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->FPS_60:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    invoke-direct {v3, v7, v1, p4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;Landroid/util/Range;Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;)V
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    return-object v3

    .line 1283
    :catch_0
    iget-object p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1, v5}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v3, " fps rejected: no compatible output pair, compatibleSizes="

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1285
    invoke-static {p3}, Ljava/util/Arrays;->toString([Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p3

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p3

    .line 1283
    invoke-virtual {p4, p3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    goto :goto_0

    .line 1288
    :cond_1
    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance p4, Ljava/lang/StringBuilder;

    invoke-direct {p4, v5}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p4, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p4, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, " fps rejected: advertisedRanges="

    invoke-virtual {p4, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1290
    invoke-static {p2}, Ljava/util/Arrays;->toString([Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p4, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p4

    .line 1288
    invoke-virtual {p3, p4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 1293
    :goto_0
    invoke-static {p2, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->findBestFpsRange([Landroid/util/Range;I)Landroid/util/Range;

    move-result-object p2

    .line 1294
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance p3, Ljava/lang/StringBuilder;

    invoke-direct {p3, v5}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p1, ", fallback=30, range="

    invoke-virtual {p3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {p3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 1297
    new-instance p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;

    sget-object p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->FPS_30:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    invoke-direct {p0, p1, p2, p5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;Landroid/util/Range;Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;)V

    return-object p0

    .line 1252
    :cond_2
    :goto_1
    invoke-static {p2, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->findBestFpsRange([Landroid/util/Range;I)Landroid/util/Range;

    move-result-object p2

    .line 1253
    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance p4, Ljava/lang/StringBuilder;

    invoke-direct {p4, v5}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p4, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->requestedFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    .line 1254
    invoke-virtual {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->getValue()I

    move-result p1

    invoke-virtual {p4, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p4, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p4, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    .line 1256
    iget-boolean p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->force30Fps:Z

    if-eqz p0, :cond_3

    const-string p0, ", reason=session-wide fallback"

    goto :goto_2

    :cond_3
    const-string p0, ""

    :goto_2
    invoke-virtual {p4, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 1253
    invoke-virtual {p3, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 1257
    new-instance p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;

    invoke-direct {p0, v1, p2, p5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;Landroid/util/Range;Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;)V

    return-object p0
.end method

.method private resolveSessionFrameRatePolicy()V
    .locals 5

    .line 1148
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->frameRatePolicyResolved:Z

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x1

    .line 1149
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->frameRatePolicyResolved:Z

    .line 1150
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->requestedFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    sget-object v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->FPS_60:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    if-eq v1, v2, :cond_1

    :goto_0
    return-void

    .line 1151
    :cond_1
    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;->FRONT:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    invoke-direct {p0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->supports60FpsForFacing(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)Z

    move-result v1

    .line 1152
    sget-object v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;->BACK:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    invoke-direct {p0, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->supports60FpsForFacing(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)Z

    move-result v2

    if-eqz v1, :cond_3

    if-nez v2, :cond_2

    goto :goto_1

    :cond_2
    const/4 v0, 0x0

    .line 1153
    :cond_3
    :goto_1
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->force30Fps:Z

    .line 1154
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v3, Ljava/lang/StringBuilder;

    const-string v4, "60 fps session capability: front="

    invoke-direct {v3, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v1, ", back="

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v1, ", selected="

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1156
    iget-boolean p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->force30Fps:Z

    if-eqz p0, :cond_4

    const/16 p0, 0x1e

    goto :goto_2

    :cond_4
    const/16 p0, 0x3c

    :goto_2
    invoke-virtual {v3, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 1154
    invoke-virtual {v0, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    return-void
.end method

.method private selectCameraAndSizes(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V
    .locals 23

    move-object/from16 v1, p0

    move-object/from16 v7, p1

    .line 1025
    invoke-direct {v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->resolveSessionFrameRatePolicy()V

    .line 1026
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;->FRONT:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    if-ne v7, v0, :cond_0

    const/4 v9, 0x0

    goto :goto_0

    :cond_0
    const/4 v0, 0x1

    move v9, v0

    .line 1031
    :goto_0
    iget-object v0, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraManager:Landroid/hardware/camera2/CameraManager;

    invoke-virtual {v0}, Landroid/hardware/camera2/CameraManager;->getCameraIdList()[Ljava/lang/String;

    move-result-object v10

    array-length v11, v10

    const/4 v0, 0x0

    move-object v12, v0

    move-object v13, v12

    const/4 v14, 0x0

    :goto_1
    const-string v0, ", recording="

    const-string v15, ", preview="

    const-string v2, ", fps="

    if-ge v14, v11, :cond_b

    move-object v3, v2

    aget-object v2, v10, v14

    .line 1032
    iget-object v4, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraManager:Landroid/hardware/camera2/CameraManager;

    invoke-virtual {v4, v2}, Landroid/hardware/camera2/CameraManager;->getCameraCharacteristics(Ljava/lang/String;)Landroid/hardware/camera2/CameraCharacteristics;

    move-result-object v4

    .line 1033
    sget-object v5, Landroid/hardware/camera2/CameraCharacteristics;->LENS_FACING:Landroid/hardware/camera2/CameraCharacteristics$Key;

    invoke-virtual {v4, v5}, Landroid/hardware/camera2/CameraCharacteristics;->get(Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/lang/Integer;

    if-eqz v5, :cond_1

    .line 1034
    invoke-virtual {v5}, Ljava/lang/Integer;->intValue()I

    move-result v5

    if-eq v5, v9, :cond_2

    :cond_1
    :goto_2
    move/from16 v22, v9

    move-object/from16 v16, v10

    goto/16 :goto_6

    .line 1035
    :cond_2
    sget-object v5, Landroid/hardware/camera2/CameraCharacteristics;->SCALER_STREAM_CONFIGURATION_MAP:Landroid/hardware/camera2/CameraCharacteristics$Key;

    invoke-virtual {v4, v5}, Landroid/hardware/camera2/CameraCharacteristics;->get(Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Landroid/hardware/camera2/params/StreamConfigurationMap;

    .line 1038
    const-string v6, "camera candidate rejected: id="

    if-nez v5, :cond_3

    .line 1039
    iget-object v0, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3, v6}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, ", reason=no stream map"

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    goto :goto_2

    .line 1042
    :cond_3
    const-class v8, Landroid/graphics/SurfaceTexture;

    invoke-virtual {v5, v8}, Landroid/hardware/camera2/params/StreamConfigurationMap;->getOutputSizes(Ljava/lang/Class;)[Landroid/util/Size;

    move-result-object v8

    if-eqz v8, :cond_4

    move-object/from16 v17, v2

    .line 1043
    array-length v2, v8

    if-nez v2, :cond_5

    move-object/from16 v2, v17

    :cond_4
    move/from16 v22, v9

    move-object/from16 v16, v10

    move-object v9, v6

    goto/16 :goto_5

    .line 1049
    :cond_5
    :try_start_0
    iget-object v2, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->outputResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    move-object/from16 v16, v3

    iget-object v3, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->requestedCameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    invoke-static {v8, v2, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->chooseOutputPair([Landroid/util/Size;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;)Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;

    move-result-object v20
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_3

    move-object v3, v4

    move-object v4, v5

    move-object v5, v8

    move/from16 v22, v9

    move-object/from16 v8, v16

    move-object/from16 v2, v17

    move-object v9, v6

    move-object/from16 v6, v20

    .line 1054
    :try_start_1
    invoke-direct/range {v1 .. v6}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->resolveFrameRate(Ljava/lang/String;Landroid/hardware/camera2/CameraCharacteristics;Landroid/hardware/camera2/params/StreamConfigurationMap;[Landroid/util/Size;Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;)Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;

    move-result-object v21
    :try_end_1
    .catch Ljava/lang/RuntimeException; {:try_start_1 .. :try_end_1} :catch_1

    move-object/from16 v17, v2

    move-object/from16 v18, v3

    move-object/from16 v19, v5

    move-object/from16 v20, v6

    .line 1061
    :try_start_2
    new-instance v16, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraCandidate;

    invoke-direct/range {v16 .. v21}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraCandidate;-><init>(Ljava/lang/String;Landroid/hardware/camera2/CameraCharacteristics;[Landroid/util/Size;Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;)V
    :try_end_2
    .catch Ljava/lang/RuntimeException; {:try_start_2 .. :try_end_2} :catch_2

    move-object/from16 v4, v16

    move-object/from16 v2, v17

    move-object/from16 v3, v21

    .line 1068
    :try_start_3
    iget-object v5, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V
    :try_end_3
    .catch Ljava/lang/RuntimeException; {:try_start_3 .. :try_end_3} :catch_1

    move-object/from16 v16, v10

    :try_start_4
    const-string v10, "camera candidate accepted: id="

    invoke-virtual {v6, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v10, ", facing="

    invoke-virtual {v6, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v10, ", flashAvailable="

    invoke-virtual {v6, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1070
    invoke-static/range {v18 .. v18}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->hasFlashUnit(Landroid/hardware/camera2/CameraCharacteristics;)Z

    move-result v10

    invoke-virtual {v6, v10}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v8, v3, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;->frameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    .line 1071
    invoke-virtual {v8}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->getValue()I

    move-result v8

    invoke-virtual {v6, v8}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v8, v3, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;->outputPair:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;

    iget-object v8, v8, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;->preview:Landroid/util/Size;

    invoke-virtual {v6, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v0, v3, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;->outputPair:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;

    iget-object v0, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;->recording:Landroid/util/Size;

    invoke-virtual {v6, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 1068
    invoke-virtual {v5, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    if-eqz v13, :cond_6

    .line 1075
    invoke-static {v4, v13, v7}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->isPreferredCameraCandidate(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraCandidate;Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraCandidate;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)Z

    move-result v0

    if-eqz v0, :cond_7

    goto :goto_3

    :catch_0
    move-exception v0

    goto :goto_4

    :cond_6
    :goto_3
    move-object v13, v4

    .line 1082
    :cond_7
    iget-object v0, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->requestedFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    sget-object v5, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->FPS_30:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    if-eq v0, v5, :cond_8

    iget-object v3, v3, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;->frameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    if-ne v3, v0, :cond_a

    :cond_8
    if-eqz v12, :cond_9

    .line 1085
    invoke-static {v4, v12, v7}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->isPreferredCameraCandidate(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraCandidate;Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraCandidate;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)Z

    move-result v0
    :try_end_4
    .catch Ljava/lang/RuntimeException; {:try_start_4 .. :try_end_4} :catch_0

    if-eqz v0, :cond_a

    :cond_9
    move-object v12, v4

    goto :goto_6

    :catch_1
    move-exception v0

    move-object/from16 v16, v10

    goto :goto_4

    :catch_2
    move-exception v0

    move-object/from16 v16, v10

    move-object/from16 v2, v17

    goto :goto_4

    :catch_3
    move-exception v0

    move/from16 v22, v9

    move-object/from16 v16, v10

    move-object/from16 v2, v17

    move-object v9, v6

    .line 1094
    :goto_4
    iget-object v3, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4, v9}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, ", reason="

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v3, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    goto :goto_6

    .line 1044
    :goto_5
    iget-object v0, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3, v9}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, ", reason=no SurfaceTexture outputs"

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    :cond_a
    :goto_6
    add-int/lit8 v14, v14, 0x1

    move-object/from16 v10, v16

    move/from16 v9, v22

    goto/16 :goto_1

    :cond_b
    move-object v8, v2

    if-nez v12, :cond_c

    move-object v12, v13

    :cond_c
    if-eqz v12, :cond_10

    .line 1102
    iget-object v2, v12, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraCandidate;->id:Ljava/lang/String;

    .line 1103
    iget-object v3, v12, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraCandidate;->characteristics:Landroid/hardware/camera2/CameraCharacteristics;

    .line 1104
    iget-object v4, v12, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraCandidate;->sizes:[Landroid/util/Size;

    .line 1105
    iget-object v5, v12, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraCandidate;->regularPair:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;

    .line 1106
    iget-object v6, v12, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraCandidate;->frameRatePlan:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;

    .line 1107
    iget-object v9, v6, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;->outputPair:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;

    .line 1108
    iput-object v2, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraId:Ljava/lang/String;

    .line 1109
    iput-object v7, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->selectedFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    .line 1110
    iput-object v3, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->characteristics:Landroid/hardware/camera2/CameraCharacteristics;

    .line 1111
    iget-object v10, v6, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;->frameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    iput-object v10, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    .line 1112
    iget-object v6, v6, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;->fpsRange:Landroid/util/Range;

    iput-object v6, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeFpsRange:Landroid/util/Range;

    const/4 v6, 0x0

    .line 1113
    iput-boolean v6, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->frameRateFallbackAttempted:Z

    .line 1114
    iput-object v5, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->regularFallbackPair:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;

    .line 1115
    invoke-direct {v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->resetCaptureFpsMeasurement()V

    .line 1116
    iget-object v5, v9, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;->preview:Landroid/util/Size;

    iput-object v5, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewSize:Landroid/util/Size;

    .line 1117
    iget-object v5, v9, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;->recording:Landroid/util/Size;

    iput-object v5, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recordingSize:Landroid/util/Size;

    .line 1118
    iget v5, v9, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;->cropSize:I

    iput v5, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->sourceCropSize:I

    .line 1119
    iget-object v5, v9, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;->cameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    iput-object v5, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeCameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    .line 1120
    invoke-direct {v1, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->readMaximumZoomRatio(Landroid/hardware/camera2/CameraCharacteristics;)F

    move-result v5

    iput v5, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->maximumZoomRatio:F

    .line 1121
    iget-object v5, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewSize:Landroid/util/Size;

    invoke-static {v3, v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->getMinimumFrameDurationNs(Landroid/hardware/camera2/CameraCharacteristics;Landroid/util/Size;)J

    move-result-wide v5

    .line 1125
    iget-object v9, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recordingSize:Landroid/util/Size;

    invoke-static {v3, v9}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->getMinimumFrameDurationNs(Landroid/hardware/camera2/CameraCharacteristics;Landroid/util/Size;)J

    move-result-wide v9

    .line 1129
    iget-object v11, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v12, Ljava/lang/StringBuilder;

    const-string v13, "camera outputs selected: output="

    invoke-direct {v12, v13}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget v13, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->outputSize:I

    invoke-virtual {v12, v13}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v13, "x"

    invoke-virtual {v12, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v13, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->outputSize:I

    invoke-virtual {v12, v13}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v13, ", cameraMode="

    invoke-virtual {v12, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v13, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeCameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    invoke-virtual {v12, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    .line 1131
    iget-object v13, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeCameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    iget-object v14, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->requestedCameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    const-string v16, ""

    move-object/from16 v17, v2

    const-string v2, ")"

    move-object/from16 v18, v3

    const-string v3, " (fallback from "

    if-eq v13, v14, :cond_d

    .line 1132
    new-instance v13, Ljava/lang/StringBuilder;

    invoke-direct {v13, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v14, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->requestedCameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    invoke-virtual {v13, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v13, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v13}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v13

    goto :goto_7

    :cond_d
    move-object/from16 v13, v16

    :goto_7
    invoke-virtual {v12, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v13, ", crop="

    invoke-virtual {v12, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v13, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->sourceCropSize:I

    invoke-virtual {v12, v13}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v12, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v13, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewSize:Landroid/util/Size;

    invoke-virtual {v12, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v12, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v0, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recordingSize:Landroid/util/Size;

    invoke-virtual {v12, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v0, ", previewMinFrameDurationNs="

    invoke-virtual {v12, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v12, v5, v6}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, ", recordingMinFrameDurationNs="

    invoke-virtual {v12, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v12, v9, v10}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v12, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v0, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    .line 1138
    invoke-virtual {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->getValue()I

    move-result v0

    invoke-virtual {v12, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v0, ", fpsRange="

    invoke-virtual {v12, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v0, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeFpsRange:Landroid/util/Range;

    invoke-virtual {v12, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v0, ", timestampSource="

    invoke-virtual {v12, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1141
    invoke-direct {v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->isCameraTimestampRealtime()Z

    move-result v0

    if-eqz v0, :cond_e

    const-string v0, "REALTIME"

    goto :goto_8

    :cond_e
    const-string v0, "UNKNOWN"

    :goto_8
    invoke-virtual {v12, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1142
    iget-object v0, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    iget-object v5, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->requestedFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    if-eq v0, v5, :cond_f

    .line 1143
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v3, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->requestedFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    invoke-virtual {v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->getValue()I

    move-result v3

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v16

    :cond_f
    move-object/from16 v0, v16

    invoke-virtual {v12, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v12}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 1129
    invoke-virtual {v11, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    move-object/from16 v0, v17

    move-object/from16 v2, v18

    .line 1144
    invoke-direct {v1, v7, v0, v2, v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->logCameraConfigurationOnce(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Ljava/lang/String;Landroid/hardware/camera2/CameraCharacteristics;[Landroid/util/Size;)V

    return-void

    .line 1100
    :cond_10
    const-string v0, "Requested camera is not available"

    invoke-static {v0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method

.method private setContinuousVideoAf(Landroid/hardware/camera2/CaptureRequest$Builder;)V
    .locals 1

    .line 1232
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->characteristics:Landroid/hardware/camera2/CameraCharacteristics;

    if-nez p0, :cond_0

    const/4 p0, 0x0

    goto :goto_0

    .line 1234
    :cond_0
    sget-object v0, Landroid/hardware/camera2/CameraCharacteristics;->CONTROL_AF_AVAILABLE_MODES:Landroid/hardware/camera2/CameraCharacteristics$Key;

    invoke-virtual {p0, v0}, Landroid/hardware/camera2/CameraCharacteristics;->get(Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, [I

    :goto_0
    const/4 v0, 0x3

    .line 1235
    invoke-static {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->contains([II)Z

    move-result p0

    if-eqz p0, :cond_1

    .line 1236
    sget-object p0, Landroid/hardware/camera2/CaptureRequest;->CONTROL_AF_MODE:Landroid/hardware/camera2/CaptureRequest$Key;

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {p1, p0, v0}, Landroid/hardware/camera2/CaptureRequest$Builder;->set(Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V

    :cond_1
    return-void
.end method

.method private setPreferredFps(Landroid/hardware/camera2/CaptureRequest$Builder;Z)V
    .locals 2

    .line 1332
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeFpsRange:Landroid/util/Range;

    if-eqz v0, :cond_0

    .line 1333
    sget-object v1, Landroid/hardware/camera2/CaptureRequest;->CONTROL_AE_TARGET_FPS_RANGE:Landroid/hardware/camera2/CaptureRequest$Key;

    invoke-virtual {p1, v1, v0}, Landroid/hardware/camera2/CaptureRequest$Builder;->set(Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V

    if-eqz p2, :cond_1

    .line 1335
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance p2, Ljava/lang/StringBuilder;

    const-string v0, "capture FPS range selected: "

    invoke-direct {p2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeFpsRange:Landroid/util/Range;

    invoke-virtual {p2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p1, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    return-void

    :cond_0
    if-eqz p2, :cond_1

    .line 1338
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    const-string p1, "capture FPS range unavailable; HAL default will be used"

    invoke-virtual {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    :cond_1
    return-void
.end method

.method private static shortSide(Landroid/util/Size;)I
    .locals 1

    .line 1761
    invoke-virtual {p0}, Landroid/util/Size;->getWidth()I

    move-result v0

    invoke-virtual {p0}, Landroid/util/Size;->getHeight()I

    move-result p0

    invoke-static {v0, p0}, Ljava/lang/Math;->min(II)I

    move-result p0

    return p0
.end method

.method private static standardDeviationMs(JDJ)F
    .locals 2

    const-wide/16 v0, 0x0

    cmp-long v0, p4, v0

    if-nez v0, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    long-to-double p0, p0

    long-to-double p4, p4

    div-double/2addr p0, p4

    div-double/2addr p2, p4

    mul-double/2addr p0, p0

    sub-double/2addr p2, p0

    const-wide/16 p0, 0x0

    .line 1465
    invoke-static {p0, p1, p2, p3}, Ljava/lang/Math;->max(DD)D

    move-result-wide p0

    .line 1466
    invoke-static {p0, p1}, Ljava/lang/Math;->sqrt(D)D

    move-result-wide p0

    const-wide p2, 0x412e848000000000L    # 1000000.0

    div-double/2addr p0, p2

    double-to-float p0, p0

    return p0
.end method

.method private startRecordingAfterFirstFrame()V
    .locals 6

    .line 669
    const-string v0, "first camera frame received; audio startup requested: segmentElapsedMs="

    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->active:Z

    if-eqz v1, :cond_2

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSession:Landroid/hardware/camera2/CameraCaptureSession;

    if-eqz v1, :cond_2

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recorder:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

    if-eqz v1, :cond_2

    invoke-virtual {v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->isStarted()Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_2

    .line 671
    :cond_0
    :try_start_0
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recorder:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

    .line 672
    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->commonStartRecorder:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

    .line 673
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraHandler:Landroid/os/Handler;

    if-eqz v2, :cond_1

    .line 675
    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->commonStartTimeoutRunnable:Ljava/lang/Runnable;

    invoke-virtual {v2, v3}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 676
    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->commonStartTimeoutRunnable:Ljava/lang/Runnable;

    const-wide/16 v4, 0xbb8

    invoke-virtual {v2, v3, v4, v5}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    goto :goto_0

    :catch_0
    move-exception v0

    goto :goto_1

    .line 678
    :cond_1
    :goto_0
    new-instance v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda0;

    invoke-direct {v2, p0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V

    new-instance v3, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda1;

    invoke-direct {v3, p0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda1;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V

    invoke-virtual {v1, v2, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->startRecording(Ljava/lang/Runnable;Ljava/lang/Runnable;)V

    .line 686
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-wide v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->segmentStartedNs:J

    .line 687
    invoke-static {v3, v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->elapsedMs(J)J

    move-result-wide v3

    invoke-virtual {v2, v3, v4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 686
    invoke-virtual {v1, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    .line 689
    :goto_1
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cancelCommonStartWait()V

    .line 690
    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->reportError(Ljava/lang/Exception;)V

    :cond_2
    :goto_2
    return-void
.end method

.method private startVideoAfterAudioReady(Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V
    .locals 4

    .line 695
    const-string v0, "common A/V start armed; waiting for next camera frame: segmentElapsedMs="

    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->active:Z

    if-eqz v1, :cond_1

    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->stopping:Z

    if-nez v1, :cond_1

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recorder:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

    if-ne v1, p1, :cond_1

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSession:Landroid/hardware/camera2/CameraCaptureSession;

    if-eqz v1, :cond_1

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->glProcessor:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

    if-nez v1, :cond_0

    goto :goto_0

    .line 700
    :cond_0
    :try_start_0
    invoke-virtual {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->getTimeOriginNs()J

    move-result-wide v2

    invoke-virtual {v1, v2, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->startRecording(J)V

    .line 701
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->segmentStartedNs:J

    .line 702
    invoke-static {v2, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->elapsedMs(J)J

    move-result-wide v2

    invoke-virtual {v1, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 701
    invoke-virtual {p1, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p1

    .line 704
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cancelCommonStartWait()V

    .line 705
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->reportError(Ljava/lang/Exception;)V

    :cond_1
    :goto_0
    return-void
.end method

.method private submitRepeatingRequest()V
    .locals 3

    .line 794
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureSession:Landroid/hardware/camera2/CameraCaptureSession;

    .line 795
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->repeatingBuilder:Landroid/hardware/camera2/CaptureRequest$Builder;

    if-eqz v0, :cond_1

    if-nez v1, :cond_0

    goto :goto_0

    .line 797
    :cond_0
    invoke-virtual {v1}, Landroid/hardware/camera2/CaptureRequest$Builder;->build()Landroid/hardware/camera2/CaptureRequest;

    move-result-object v1

    .line 798
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->captureCallback:Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraHandler:Landroid/os/Handler;

    invoke-virtual {v0, v1, v2, p0}, Landroid/hardware/camera2/CameraCaptureSession;->setRepeatingRequest(Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;Landroid/os/Handler;)I

    :cond_1
    :goto_0
    return-void
.end method

.method private supports60FpsForFacing(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)Z
    .locals 11

    .line 1161
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;->FRONT:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    const/4 v1, 0x1

    const/4 v2, 0x0

    if-ne p1, v0, :cond_0

    move p1, v2

    goto :goto_0

    :cond_0
    move p1, v1

    .line 1164
    :goto_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraManager:Landroid/hardware/camera2/CameraManager;

    invoke-virtual {v0}, Landroid/hardware/camera2/CameraManager;->getCameraIdList()[Ljava/lang/String;

    move-result-object v0

    array-length v3, v0

    move v4, v2

    :goto_1
    if-ge v4, v3, :cond_6

    aget-object v6, v0, v4

    .line 1165
    iget-object v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraManager:Landroid/hardware/camera2/CameraManager;

    invoke-virtual {v5, v6}, Landroid/hardware/camera2/CameraManager;->getCameraCharacteristics(Ljava/lang/String;)Landroid/hardware/camera2/CameraCharacteristics;

    move-result-object v7

    .line 1166
    sget-object v5, Landroid/hardware/camera2/CameraCharacteristics;->LENS_FACING:Landroid/hardware/camera2/CameraCharacteristics$Key;

    invoke-virtual {v7, v5}, Landroid/hardware/camera2/CameraCharacteristics;->get(Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/lang/Integer;

    if-eqz v5, :cond_1

    .line 1167
    invoke-virtual {v5}, Ljava/lang/Integer;->intValue()I

    move-result v5

    if-eq v5, p1, :cond_2

    :catch_0
    :cond_1
    :goto_2
    move-object v5, p0

    goto :goto_3

    .line 1168
    :cond_2
    sget-object v5, Landroid/hardware/camera2/CameraCharacteristics;->SCALER_STREAM_CONFIGURATION_MAP:Landroid/hardware/camera2/CameraCharacteristics$Key;

    invoke-virtual {v7, v5}, Landroid/hardware/camera2/CameraCharacteristics;->get(Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;

    move-result-object v5

    move-object v8, v5

    check-cast v8, Landroid/hardware/camera2/params/StreamConfigurationMap;

    if-nez v8, :cond_3

    goto :goto_2

    .line 1172
    :cond_3
    const-class v5, Landroid/graphics/SurfaceTexture;

    invoke-virtual {v8, v5}, Landroid/hardware/camera2/params/StreamConfigurationMap;->getOutputSizes(Ljava/lang/Class;)[Landroid/util/Size;

    move-result-object v9

    if-eqz v9, :cond_1

    .line 1173
    array-length v5, v9

    if-nez v5, :cond_4

    goto :goto_2

    .line 1175
    :cond_4
    :try_start_0
    iget-object v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->outputResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    iget-object v10, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->requestedCameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    invoke-static {v9, v5, v10}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->chooseOutputPair([Landroid/util/Size;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;)Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;

    move-result-object v10
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    move-object v5, p0

    .line 1180
    :try_start_1
    invoke-direct/range {v5 .. v10}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->resolveFrameRate(Ljava/lang/String;Landroid/hardware/camera2/CameraCharacteristics;Landroid/hardware/camera2/params/StreamConfigurationMap;[Landroid/util/Size;Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;)Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;->frameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    sget-object v6, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->FPS_60:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;
    :try_end_1
    .catch Ljava/lang/RuntimeException; {:try_start_1 .. :try_end_1} :catch_1

    if-ne p0, v6, :cond_5

    return v1

    :catch_1
    :cond_5
    :goto_3
    add-int/lit8 v4, v4, 0x1

    move-object p0, v5

    goto :goto_1

    :cond_6
    return v2
.end method

.method private static supportsFrameDuration(Landroid/hardware/camera2/params/StreamConfigurationMap;Landroid/util/Size;I)Z
    .locals 4

    .line 1309
    const-class v0, Landroid/graphics/SurfaceTexture;

    invoke-virtual {p0, v0, p1}, Landroid/hardware/camera2/params/StreamConfigurationMap;->getOutputMinFrameDuration(Ljava/lang/Class;Landroid/util/Size;)J

    move-result-wide p0

    const-wide/16 v0, 0x0

    cmp-long v0, p0, v0

    if-lez v0, :cond_1

    const-wide/32 v0, 0x3b9aca00

    int-to-long v2, p2

    .line 1310
    div-long/2addr v0, v2

    cmp-long p0, p0, v0

    if-gtz p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x1

    return p0
.end method

.method private switchCameraIfNeeded(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V
    .locals 4

    .line 493
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->active:Z

    if-eqz v0, :cond_3

    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->switchingCamera:Z

    if-nez v0, :cond_3

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    if-ne p1, v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x1

    .line 494
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->switchingCamera:Z

    .line 495
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->notifySwitchCompletion:Z

    const/4 v0, 0x0

    .line 496
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchEnabled:Z

    .line 497
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchVerificationPending:Z

    const/4 v1, 0x0

    .line 498
    iput v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->zoomProgress:F

    .line 499
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->callback:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;

    invoke-interface {v1, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;->onCameraSwitchStarted(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V

    .line 500
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtimeNanos()J

    move-result-wide v1

    iput-wide v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraSwitchStartedNs:J

    .line 501
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "camera device switch started: from="

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v3, ", to="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 502
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->glProcessor:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

    if-eqz v1, :cond_1

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->activeFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    invoke-virtual {v1, v2, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->beginCameraSwitch(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V

    .line 503
    :cond_1
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->closeCaptureSession()V

    .line 504
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraDevice:Landroid/hardware/camera2/CameraDevice;

    if-eqz p1, :cond_2

    const/4 v0, 0x0

    .line 506
    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraDevice:Landroid/hardware/camera2/CameraDevice;

    .line 507
    invoke-virtual {p1}, Landroid/hardware/camera2/CameraDevice;->close()V

    return-void

    .line 508
    :cond_2
    iget-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->opening:Z

    if-nez p1, :cond_3

    .line 509
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->switchingCamera:Z

    .line 510
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->openCameraIfReady()V

    :cond_3
    :goto_0
    return-void
.end method

.method private updatePreviewTransform()V
    .locals 8

    .line 1470
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewTransformEnabled:Z

    if-nez v0, :cond_0

    goto/16 :goto_2

    .line 1471
    :cond_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->recordingSize:Landroid/util/Size;

    if-eqz v0, :cond_4

    .line 1472
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewView:Landroid/view/TextureView;

    invoke-virtual {v1}, Landroid/view/View;->getWidth()I

    move-result v1

    if-eqz v1, :cond_4

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewView:Landroid/view/TextureView;

    invoke-virtual {v1}, Landroid/view/View;->getHeight()I

    move-result v1

    if-nez v1, :cond_1

    goto/16 :goto_2

    .line 1473
    :cond_1
    iget v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->sourceCropSize:I

    invoke-virtual {v0}, Landroid/util/Size;->getWidth()I

    move-result v2

    invoke-virtual {v0}, Landroid/util/Size;->getHeight()I

    move-result v3

    invoke-static {v2, v3}, Ljava/lang/Math;->min(II)I

    move-result v2

    invoke-static {v1, v2}, Ljava/lang/Math;->min(II)I

    move-result v1

    .line 1474
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->isOutputRotationSwapped()Z

    move-result v2

    if-eqz v2, :cond_2

    .line 1475
    invoke-virtual {v0}, Landroid/util/Size;->getHeight()I

    move-result v3

    goto :goto_0

    :cond_2
    invoke-virtual {v0}, Landroid/util/Size;->getWidth()I

    move-result v3

    :goto_0
    int-to-float v3, v3

    int-to-float v4, v1

    div-float/2addr v3, v4

    if-eqz v2, :cond_3

    .line 1476
    invoke-virtual {v0}, Landroid/util/Size;->getWidth()I

    move-result v5

    goto :goto_1

    :cond_3
    invoke-virtual {v0}, Landroid/util/Size;->getHeight()I

    move-result v5

    :goto_1
    int-to-float v5, v5

    div-float/2addr v5, v4

    .line 1477
    iget-object v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewView:Landroid/view/TextureView;

    invoke-virtual {v4}, Landroid/view/View;->getWidth()I

    move-result v4

    int-to-float v4, v4

    const/high16 v6, 0x3f000000    # 0.5f

    mul-float/2addr v4, v6

    .line 1478
    iget-object v7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewView:Landroid/view/TextureView;

    invoke-virtual {v7}, Landroid/view/View;->getHeight()I

    move-result v7

    int-to-float v7, v7

    mul-float/2addr v7, v6

    .line 1479
    new-instance v6, Landroid/graphics/Matrix;

    invoke-direct {v6}, Landroid/graphics/Matrix;-><init>()V

    .line 1480
    invoke-virtual {v6, v3, v5, v4, v7}, Landroid/graphics/Matrix;->setScale(FFFF)V

    .line 1481
    iget-object v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewView:Landroid/view/TextureView;

    invoke-virtual {v4, v6}, Landroid/view/TextureView;->setTransform(Landroid/graphics/Matrix;)V

    .line 1482
    iget-object v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v6, Ljava/lang/StringBuilder;

    const-string v7, "preview transform: view="

    invoke-direct {v6, v7}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewView:Landroid/view/TextureView;

    invoke-virtual {v7}, Landroid/view/View;->getWidth()I

    move-result v7

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v7, "x"

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewView:Landroid/view/TextureView;

    .line 1483
    invoke-virtual {p0}, Landroid/view/View;->getHeight()I

    move-result p0

    invoke-virtual {v6, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p0, ", source="

    invoke-virtual {v6, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p0, ", crop="

    invoke-virtual {v6, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p0, ", axesSwapped="

    invoke-virtual {v6, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v2}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string p0, ", scale="

    invoke-virtual {v6, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v3}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v5}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 1482
    invoke-virtual {v4, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    :cond_4
    :goto_2
    return-void
.end method

.method private verifyTorchResult(Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/TotalCaptureResult;)V
    .locals 8

    .line 868
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchVerificationPending:Z

    if-nez v0, :cond_0

    goto/16 :goto_4

    .line 869
    :cond_0
    invoke-virtual {p1}, Landroid/hardware/camera2/CaptureRequest;->getTag()Ljava/lang/Object;

    move-result-object p1

    .line 870
    instance-of v0, p1, Ljava/lang/Integer;

    if-eqz v0, :cond_9

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchVerificationGeneration:I

    if-eq p1, v0, :cond_1

    goto/16 :goto_4

    .line 871
    :cond_1
    iget p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchVerificationFrames:I

    const/4 v0, 0x1

    add-int/2addr p1, v0

    iput p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchVerificationFrames:I

    .line 872
    sget-object p1, Landroid/hardware/camera2/CaptureResult;->FLASH_MODE:Landroid/hardware/camera2/CaptureResult$Key;

    invoke-virtual {p2, p1}, Landroid/hardware/camera2/CaptureResult;->get(Landroid/hardware/camera2/CaptureResult$Key;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Integer;

    .line 873
    sget-object v1, Landroid/hardware/camera2/CaptureResult;->FLASH_STATE:Landroid/hardware/camera2/CaptureResult$Key;

    invoke-virtual {p2, v1}, Landroid/hardware/camera2/CaptureResult;->get(Landroid/hardware/camera2/CaptureResult$Key;)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/lang/Integer;

    .line 874
    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchEnabled:Z

    const/4 v2, 0x0

    if-eqz v1, :cond_4

    if-eqz p1, :cond_3

    .line 875
    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result v1

    const/4 v3, 0x2

    if-ne v1, v3, :cond_3

    :cond_2
    :goto_0
    move v1, v0

    goto :goto_1

    :cond_3
    move v1, v2

    goto :goto_1

    :cond_4
    if-eqz p1, :cond_2

    .line 876
    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result v1

    if-nez v1, :cond_3

    goto :goto_0

    .line 877
    :goto_1
    iget-boolean v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchEnabled:Z

    const/4 v4, 0x3

    if-eqz v3, :cond_6

    if-eqz p2, :cond_6

    .line 879
    invoke-virtual {p2}, Ljava/lang/Integer;->intValue()I

    move-result v3

    if-eq v3, v4, :cond_6

    .line 880
    invoke-virtual {p2}, Ljava/lang/Integer;->intValue()I

    move-result v3

    const/4 v5, 0x4

    if-ne v3, v5, :cond_5

    goto :goto_2

    :cond_5
    move v3, v2

    goto :goto_3

    :cond_6
    :goto_2
    move v3, v0

    .line 881
    :goto_3
    const-string v5, ", retries="

    const-string v6, ", flashState="

    const-string v7, ", resultMode="

    if-eqz v1, :cond_7

    if-eqz v3, :cond_7

    .line 882
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v3, "torch result confirmed: enabled="

    invoke-direct {v1, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-boolean v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchEnabled:Z

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p1, ", frames="

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchVerificationFrames:I

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchVerificationRetries:I

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 887
    iput-boolean v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchVerificationPending:Z

    return-void

    .line 890
    :cond_7
    iget v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchVerificationFrames:I

    if-ne v1, v4, :cond_8

    iget v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchVerificationRetries:I

    if-nez v3, :cond_8

    add-int/2addr v3, v0

    .line 892
    iput v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchVerificationRetries:I

    .line 893
    iput v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchVerificationFrames:I

    .line 894
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "torch result not applied; rebuilding request: enabled="

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-boolean v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchEnabled:Z

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 898
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->applyRepeatingRequest()V

    return-void

    :cond_8
    const/4 v0, 0x6

    if-lt v1, v0, :cond_9

    .line 900
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v3, "torch result failed: enabled="

    invoke-direct {v1, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-boolean v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchEnabled:Z

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchVerificationRetries:I

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 904
    iput-boolean v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->torchVerificationPending:Z

    :cond_9
    :goto_4
    return-void
.end method


# virtual methods
.method public release()V
    .locals 3

    const/4 v0, 0x0

    .line 307
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->active:Z

    .line 308
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewView:Landroid/view/TextureView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/view/TextureView;->setSurfaceTextureListener(Landroid/view/TextureView$SurfaceTextureListener;)V

    .line 309
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewView:Landroid/view/TextureView;

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->layoutChangeListener:Landroid/view/View$OnLayoutChangeListener;

    invoke-virtual {v0, v2}, Landroid/view/View;->removeOnLayoutChangeListener(Landroid/view/View$OnLayoutChangeListener;)V

    .line 310
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraHandler:Landroid/os/Handler;

    .line 311
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraThread:Landroid/os/HandlerThread;

    .line 312
    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraHandler:Landroid/os/Handler;

    .line 313
    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraThread:Landroid/os/HandlerThread;

    if-eqz v0, :cond_1

    if-nez v2, :cond_0

    goto :goto_0

    .line 315
    :cond_0
    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda10;

    invoke-direct {v1, p0, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda10;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Landroid/os/HandlerThread;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    :cond_1
    :goto_0
    return-void
.end method

.method public setCameraFacing(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)Z
    .locals 2

    .line 247
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->requestedFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    .line 248
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraHandler:Landroid/os/Handler;

    .line 249
    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->active:Z

    if-eqz v1, :cond_1

    if-nez v0, :cond_0

    goto :goto_0

    .line 250
    :cond_0
    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda8;

    invoke-direct {v1, p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda8;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    const/4 p0, 0x1

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x0

    return p0
.end method

.method public setTorchEnabled(Z)Z
    .locals 2

    .line 264
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraHandler:Landroid/os/Handler;

    .line 265
    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->active:Z

    if-eqz v1, :cond_1

    if-nez v0, :cond_0

    goto :goto_0

    .line 266
    :cond_0
    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda9;

    invoke-direct {v1, p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda9;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Z)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    const/4 p0, 0x1

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x0

    return p0
.end method

.method public setZoom(F)Z
    .locals 2

    const/4 v0, 0x0

    const/high16 v1, 0x3f800000    # 1.0f

    .line 255
    invoke-static {p1, v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->clamp(FFF)F

    move-result p1

    iput p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->zoomProgress:F

    .line 256
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraHandler:Landroid/os/Handler;

    .line 257
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->active:Z

    if-eqz v0, :cond_1

    if-nez p1, :cond_0

    goto :goto_0

    .line 258
    :cond_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->applyZoomRunnable:Ljava/lang/Runnable;

    invoke-virtual {p1, v0}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 259
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->applyZoomRunnable:Ljava/lang/Runnable;

    invoke-virtual {p1, p0}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    const/4 p0, 0x1

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x0

    return p0
.end method

.method public startSegment(Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;JLorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V
    .locals 2

    .line 228
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->ensureThread()V

    .line 229
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->writer:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    .line 230
    iput-wide p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->timelineOffsetUs:J

    .line 231
    iput-object p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->requestedFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    const/4 p1, 0x1

    .line 232
    iput-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->active:Z

    const/4 v0, 0x0

    .line 233
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->stopping:Z

    const/4 v0, 0x0

    .line 234
    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->commonStartRecorder:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

    .line 235
    iput-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewTransformEnabled:Z

    .line 236
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtimeNanos()J

    move-result-wide v0

    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->segmentStartedNs:J

    .line 237
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->resetPreviewFrameMetrics()V

    .line 238
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->resetCaptureFpsMeasurement()V

    .line 239
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "camera segment start: facing="

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p4}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p4, ", timelineOffsetUs="

    invoke-virtual {v0, p4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p2, p3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p2, ", textureAvailable="

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewView:Landroid/view/TextureView;

    .line 241
    invoke-virtual {p2}, Landroid/view/TextureView;->isAvailable()Z

    move-result p2

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    .line 239
    invoke-virtual {p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 242
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewView:Landroid/view/TextureView;

    iget-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewListener:Landroid/view/TextureView$SurfaceTextureListener;

    invoke-virtual {p1, p2}, Landroid/view/TextureView;->setSurfaceTextureListener(Landroid/view/TextureView$SurfaceTextureListener;)V

    .line 243
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->maybeOpenCamera()V

    return-void
.end method

.method public stopSegment()Z
    .locals 4

    .line 281
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->cameraHandler:Landroid/os/Handler;

    .line 282
    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->active:Z

    const/4 v2, 0x0

    if-eqz v1, :cond_1

    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->stopping:Z

    if-nez v1, :cond_1

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v1, 0x1

    .line 283
    iput-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->stopping:Z

    .line 284
    iput-boolean v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->previewTransformEnabled:Z

    .line 285
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    const-string v3, "camera segment stop requested"

    invoke-virtual {v2, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 286
    new-instance v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda11;

    invoke-direct {v2, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda11;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;)V

    invoke-virtual {v0, v2}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    return v1

    :cond_1
    :goto_0
    return v2
.end method
