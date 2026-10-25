.class public final Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputListener;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$ScreenFlashController;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraCapabilities;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Result;
    }
.end annotation


# instance fields
.field private activeCameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

.field private activeFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

.field private activeFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

.field private final cameraCallback:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;

.field private final cameraController:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

.field private cameraStopPending:Z

.field private cameraSwitchCount:I

.field private cameraSwitching:Z

.field private cancelAfterCameraStops:Z

.field private volatile cancelRequested:Z

.field private cleanupStarted:Z

.field private final compositionEnabled:Z

.field private final context:Landroid/content/Context;

.field private final diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

.field private final durationLimitRunnable:Ljava/lang/Runnable;

.field private final fileExecutor:Ljava/util/concurrent/ExecutorService;

.field private finishAfterCameraStops:Z

.field private finishWithAudio:Z

.field private flashEnabled:Z

.field private flashType:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

.field private final identityPreviewTransform:Landroid/graphics/Matrix;

.field private final listener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;

.field private final mainHandler:Landroid/os/Handler;

.field private final maximumDurationMs:J

.field private maximumZoomRatio:F

.field private nextOutputId:J

.field private final outputDirectory:Ljava/io/File;

.field private final outputExecutor:Ljava/util/concurrent/ExecutorService;

.field private volatile outputGeneration:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;

.field private final outputListener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputListener;

.field private final outputLock:Ljava/lang/Object;

.field private final outputResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

.field private pauseCount:I

.field private player:Landroidx/media3/exoplayer/ExoPlayer;

.field private final playerListener:Landroidx/media3/common/Player$Listener;

.field private volatile previewFile:Ljava/io/File;

.field private previewPlaying:Z

.field private final previewProgressRunnable:Ljava/lang/Runnable;

.field private previewSnapshotStartedNs:J

.field private previewSourceDurationMs:J

.field private final previewView:Landroid/view/TextureView;

.field private recordedDurationMs:J

.field private recordingStartedAtRealtimeMs:J

.field private final requestedCameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

.field private requestedFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

.field private final requestedFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

.field private resumeCount:I

.field private final screenFlashController:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$ScreenFlashController;

.field private screenFlashEnabled:Z

.field private state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

.field private summaryLogged:Z

.field private trimEndMs:J

.field private trimStartMs:J

.field private final videoBitrate:I

.field private volatile writer:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

.field private zoomProgress:F


# direct methods
.method public static synthetic $r8$lambda$-4zJsc9a0ObivEm5TA2R2OP6rRE(Ljava/lang/Runnable;)Ljava/lang/Thread;
    .locals 2

    .line 511
    new-instance v0, Ljava/lang/Thread;

    const-string v1, "RoundVideoOutput"

    invoke-direct {v0, p0, v1}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;Ljava/lang/String;)V

    return-object v0
.end method

.method public static synthetic $r8$lambda$-G0cSHt5vb9YdKr_eUKl-83lUD0(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;J)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->lambda$createPreviewSnapshot$3(J)V

    return-void
.end method

.method public static synthetic $r8$lambda$2rHjWRgndDPIRM1ZaoCctkv2D-E(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->lambda$cancelOutput$21(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;)V

    return-void
.end method

.method public static synthetic $r8$lambda$7DGL3MRituUx_WYDgIfA18LWgPQ(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;J)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->lambda$rebuildForResume$6(J)V

    return-void
.end method

.method public static synthetic $r8$lambda$9Mniw4QCjZDo-vGcr9mPESmBNXE(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;JLjava/io/File;Z)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->lambda$completeOutput$13(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;JLjava/io/File;Z)V

    return-void
.end method

.method public static synthetic $r8$lambda$Ep_927YGU-cbXTPS7IDyOO_sLgs(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Ljava/lang/Exception;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->lambda$rebuildForResume$7(Ljava/lang/Exception;)V

    return-void
.end method

.method public static synthetic $r8$lambda$FUST4X2EdqFdZmDiSwtm2IOY8YI(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;ZLjava/io/File;ZLorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p6}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->lambda$finishFromPreview$12(Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;ZLjava/io/File;ZLorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;)V

    return-void
.end method

.method public static synthetic $r8$lambda$OmyhblPyxezbuJ5LN-I2MSa3Gu8(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;JJ)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->lambda$createOutputGenerationOnFileThread$15(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;JJ)V

    return-void
.end method

.method public static synthetic $r8$lambda$Pw2yy79EWxz3r9JycAYsvfaXvyI(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;ZLorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->lambda$finishActiveRecording$10(Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;ZLorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;)V

    return-void
.end method

.method public static synthetic $r8$lambda$Q3S91HDJXqVYU2sJYvvD9Dq6H3E(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Ljava/lang/Exception;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->lambda$finishActiveRecording$9(Ljava/lang/Exception;)V

    return-void
.end method

.method public static synthetic $r8$lambda$RAyFSHN-Gwx35He8gSwYqTvaN9A(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Ljava/lang/Exception;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->lambda$finishFromPreview$11(Ljava/lang/Exception;)V

    return-void
.end method

.method public static synthetic $r8$lambda$T3XtlSYKN8vIdwjAGCbxys5LaKQ(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;Ljava/io/File;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->lambda$createPreviewSnapshot$5(Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;Ljava/io/File;)V

    return-void
.end method

.method public static synthetic $r8$lambda$VL-TMtiUqwlCi_8GNmUtrXxO8EM(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Ljava/io/File;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->lambda$createOutputGenerationOnFileThread$17(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Ljava/io/File;)V

    return-void
.end method

.method public static synthetic $r8$lambda$XCDForUIE35_Ic-4IVQureFncQo(Ljava/lang/Runnable;)Ljava/lang/Thread;
    .locals 2

    .line 508
    new-instance v0, Ljava/lang/Thread;

    const-string v1, "RoundVideoFiles"

    invoke-direct {v0, p0, v1}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;Ljava/lang/String;)V

    return-object v0
.end method

.method public static synthetic $r8$lambda$bBhtwnBWZ8OKT45xvGCcE_bOFF8(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Ljava/io/File;JZ)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->lambda$completeOutput$14(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Ljava/io/File;JZ)V

    return-void
.end method

.method public static synthetic $r8$lambda$d3OubgcYQYKA7K7kDr5g3dY8rKA(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->lambda$invalidateOutput$18(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;)V

    return-void
.end method

.method public static synthetic $r8$lambda$p6piDDaiFe9Onkck4iaM_SebefE(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->lambda$deleteMediaFiles$20()V

    return-void
.end method

.method public static synthetic $r8$lambda$uQoRsaj34mIlUcf7GdAtdW3Wr1s(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->lambda$new$2()V

    return-void
.end method

.method public static synthetic $r8$lambda$ueyj_D1RCRJeuEocRULWX4c_rvs(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Ljava/lang/Exception;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->lambda$createPreviewSnapshot$4(Ljava/lang/Exception;)V

    return-void
.end method

.method public static synthetic $r8$lambda$vrNmUXteg7JyaSrkCdzQyChNBIg(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Ljava/lang/Exception;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->lambda$fail$19(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Ljava/lang/Exception;)V

    return-void
.end method

.method public static synthetic $r8$lambda$wwoBa-FBoOXS-IzBqMsXt5hoPsc(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;J)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->lambda$createOutputGenerationOnFileThread$16(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;J)V

    return-void
.end method

.method public static synthetic $r8$lambda$xCjTDFhOAvgAgt__DxKVwDx15As(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;ZLorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Ljava/io/File;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3, p4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->lambda$rebuildForResume$8(ZLorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Ljava/io/File;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$fgetcameraSwitchCount(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)I
    .locals 0

    .line 0
    iget p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraSwitchCount:I

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetdiagnostics(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetlistener(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->listener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetmainHandler(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Landroid/os/Handler;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->mainHandler:Landroid/os/Handler;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetplayer(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Landroidx/media3/exoplayer/ExoPlayer;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->player:Landroidx/media3/exoplayer/ExoPlayer;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetpreviewPlaying(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewPlaying:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetrecordedDurationMs(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordedDurationMs:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetstate(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgettrimEndMs(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimEndMs:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgettrimStartMs(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimStartMs:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fputcameraSwitchCount(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;I)V
    .locals 0

    .line 0
    iput p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraSwitchCount:I

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputcameraSwitching(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Z)V
    .locals 0

    .line 0
    iput-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraSwitching:Z

    return-void
.end method

.method public static bridge synthetic -$$Nest$mfail(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Ljava/lang/Exception;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->fail(Ljava/lang/Exception;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mhandleCameraConfigured(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;Z)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->handleCameraConfigured(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;Z)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mhandleRecordingStarted(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->handleRecordingStarted()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mhandleRecordingStopped(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->handleRecordingStopped()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mnotifyState(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->notifyState()V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mupdatePreviewPlaying(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Z)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->updatePreviewPlaying(Z)V

    return-void
.end method

.method private constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)V
    .locals 12

    .line 574
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 504
    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputLock:Ljava/lang/Object;

    .line 505
    new-instance v0, Landroid/graphics/Matrix;

    invoke-direct {v0}, Landroid/graphics/Matrix;-><init>()V

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->identityPreviewTransform:Landroid/graphics/Matrix;

    .line 506
    new-instance v0, Landroid/os/Handler;

    invoke-static {}, Landroid/os/Looper;->getMainLooper()Landroid/os/Looper;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->mainHandler:Landroid/os/Handler;

    .line 507
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda10;

    invoke-direct {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda10;-><init>()V

    invoke-static {v0}, Ljava/util/concurrent/Executors;->newSingleThreadExecutor(Ljava/util/concurrent/ThreadFactory;)Ljava/util/concurrent/ExecutorService;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->fileExecutor:Ljava/util/concurrent/ExecutorService;

    .line 510
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda11;

    invoke-direct {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda11;-><init>()V

    invoke-static {v0}, Ljava/util/concurrent/Executors;->newSingleThreadExecutor(Ljava/util/concurrent/ThreadFactory;)Ljava/util/concurrent/ExecutorService;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputExecutor:Ljava/util/concurrent/ExecutorService;

    .line 522
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->IDLE:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    .line 527
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;->NONE:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->flashType:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    const/high16 v0, 0x3f800000    # 1.0f

    .line 528
    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->maximumZoomRatio:F

    const-wide/16 v0, 0x1

    .line 544
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->nextOutputId:J

    .line 556
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda12;

    invoke-direct {v0, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda12;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)V

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->durationLimitRunnable:Ljava/lang/Runnable;

    .line 560
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$1;

    invoke-direct {v0, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$1;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)V

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewProgressRunnable:Ljava/lang/Runnable;

    .line 879
    new-instance v10, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;

    invoke-direct {v10, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)V

    iput-object v10, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraCallback:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;

    .line 1027
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$3;

    invoke-direct {v0, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$3;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)V

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->playerListener:Landroidx/media3/common/Player$Listener;

    .line 575
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requireMainThread()V

    .line 576
    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->-$$Nest$fgetcontext(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)Landroid/content/Context;

    move-result-object v0

    .line 577
    invoke-virtual {v0}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object v1

    if-nez v1, :cond_0

    move-object v2, v0

    goto :goto_0

    :cond_0
    move-object v2, v1

    .line 578
    :goto_0
    iput-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->context:Landroid/content/Context;

    .line 579
    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->-$$Nest$fgetpreviewView(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)Landroid/view/TextureView;

    move-result-object v3

    iput-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewView:Landroid/view/TextureView;

    .line 580
    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->-$$Nest$fgetoutputDirectory(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)Ljava/io/File;

    move-result-object v0

    if-nez v0, :cond_1

    .line 581
    invoke-virtual {v2}, Landroid/content/Context;->getCacheDir()Ljava/io/File;

    move-result-object v0

    goto :goto_1

    .line 582
    :cond_1
    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->-$$Nest$fgetoutputDirectory(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)Ljava/io/File;

    move-result-object v0

    :goto_1
    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputDirectory:Ljava/io/File;

    .line 583
    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->-$$Nest$fgetinitialFacing(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requestedFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    .line 584
    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->-$$Nest$fgetoutputResolution(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    move-result-object v4

    iput-object v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    .line 585
    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->-$$Nest$fgetvideoBitrate(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)I

    move-result v5

    iput v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->videoBitrate:I

    .line 586
    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->-$$Nest$fgetcameraResolution(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    move-result-object v6

    iput-object v6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requestedCameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    .line 587
    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->-$$Nest$fgetframeRate(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    move-result-object v7

    iput-object v7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requestedFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    .line 588
    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->-$$Nest$fgetmaximumDurationMs(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)J

    move-result-wide v0

    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->maximumDurationMs:J

    .line 589
    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->-$$Nest$fgetcompositionEnabled(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)Z

    move-result v8

    iput-boolean v8, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->compositionEnabled:Z

    .line 590
    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->-$$Nest$fgetlistener(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;

    move-result-object v9

    iput-object v9, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->listener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;

    .line 591
    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->-$$Nest$fgetoutputListener(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputListener;

    move-result-object v9

    iput-object v9, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputListener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputListener;

    .line 592
    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->-$$Nest$fgetscreenFlashController(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$ScreenFlashController;

    move-result-object p1

    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->screenFlashController:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$ScreenFlashController;

    .line 593
    new-instance v9, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    invoke-direct {v9}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;-><init>()V

    iput-object v9, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    .line 594
    new-instance p1, Ljava/lang/StringBuilder;

    const-string v11, "session created: device="

    invoke-direct {p1, v11}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    sget-object v11, Landroid/os/Build;->MANUFACTURER:Ljava/lang/String;

    invoke-virtual {p1, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v11, " "

    invoke-virtual {p1, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v11, Landroid/os/Build;->MODEL:Ljava/lang/String;

    invoke-virtual {p1, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v11, ", sdk="

    invoke-virtual {p1, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget v11, Landroid/os/Build$VERSION;->SDK_INT:I

    invoke-virtual {p1, v11}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v11, ", output="

    invoke-virtual {p1, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 596
    invoke-virtual {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;->getSize()I

    move-result v11

    invoke-virtual {p1, v11}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v11, "x"

    invoke-virtual {p1, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;->getSize()I

    move-result v11

    invoke-virtual {p1, v11}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v11, ", bitrate="

    invoke-virtual {p1, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v11, ", cameraMode="

    invoke-virtual {p1, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v11, ", fps="

    invoke-virtual {p1, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 599
    invoke-virtual {v7}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->getValue()I

    move-result v11

    invoke-virtual {p1, v11}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v11, ", composition="

    invoke-virtual {p1, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1, v8}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v11, ", facing="

    invoke-virtual {p1, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v11, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requestedFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    invoke-virtual {p1, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v11, ", maxDurationMs="

    invoke-virtual {p1, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    .line 594
    invoke-virtual {v9, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 603
    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-direct/range {v1 .. v10}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;-><init>(Landroid/content/Context;Landroid/view/TextureView;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;ILorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;ZLorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;)V

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraController:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    return-void
.end method

.method public synthetic constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession-IA;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)V

    return-void
.end method

.method private static asException(Ljava/lang/Exception;)Ljava/lang/Exception;
    .locals 0

    return-object p0
.end method

.method private cancelOutput(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;)Z
    .locals 8

    .line 1484
    const-string v0, "output generation invalidated: id="

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputLock:Ljava/lang/Object;

    monitor-enter v1

    .line 1485
    :try_start_0
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputGeneration:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;

    if-eqz v2, :cond_0

    .line 1486
    iget-boolean v3, v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->completed:Z

    if-eqz v3, :cond_0

    const/4 p0, 0x0

    monitor-exit v1

    return p0

    :catchall_0
    move-exception p0

    goto :goto_0

    :cond_0
    const/4 v3, 0x1

    .line 1487
    iput-boolean v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cancelRequested:Z

    if-eqz v2, :cond_1

    .line 1488
    iget-boolean v4, v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->invalidated:Z

    if-nez v4, :cond_1

    .line 1489
    iput-boolean v3, v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->invalidated:Z

    .line 1490
    iget-object v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-wide v6, v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->id:J

    invoke-virtual {v5, v6, v7}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, ", reason="

    invoke-virtual {v5, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v0, ", availableSize="

    invoke-virtual {v5, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v6, v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->availableSize:J

    invoke-virtual {v5, v6, v7}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v4, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 1493
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputExecutor:Ljava/util/concurrent/ExecutorService;

    new-instance v4, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda6;

    invoke-direct {v4, p0, v2, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda6;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;)V

    invoke-interface {v0, v4}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    .line 1498
    :cond_1
    monitor-exit v1

    return v3

    .line 1499
    :goto_0
    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method private static clamp(FFF)F
    .locals 0

    .line 1507
    invoke-static {p2, p0}, Ljava/lang/Math;->min(FF)F

    move-result p0

    invoke-static {p1, p0}, Ljava/lang/Math;->max(FF)F

    move-result p0

    return p0
.end method

.method private static clamp(JJJ)J
    .locals 0

    .line 1503
    invoke-static {p4, p5, p0, p1}, Ljava/lang/Math;->min(JJ)J

    move-result-wide p0

    invoke-static {p2, p3, p0, p1}, Ljava/lang/Math;->max(JJ)J

    move-result-wide p0

    return-wide p0
.end method

.method private completeOutput(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Ljava/io/File;JZ)V
    .locals 8

    .line 1241
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputExecutor:Ljava/util/concurrent/ExecutorService;

    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda20;

    move-object v2, p0

    move-object v3, p1

    move-object v4, p2

    move-wide v5, p3

    move v7, p5

    invoke-direct/range {v1 .. v7}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda20;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Ljava/io/File;JZ)V

    invoke-interface {v0, v1}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    return-void
.end method

.method private createOutputGeneration(Z)V
    .locals 0

    .line 1273
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->createOutputGenerationOnFileThread(Z)V

    return-void
.end method

.method private createOutputGenerationOnFileThread(Z)V
    .locals 9

    .line 1277
    const-string v0, "output generation started: id="

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputLock:Ljava/lang/Object;

    monitor-enter v1

    .line 1278
    :try_start_0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->ensureNotCancelled()V

    .line 1279
    const-string v2, "round_video_"

    invoke-direct {p0, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->createTemporaryFile(Ljava/lang/String;)Ljava/io/File;

    move-result-object v4

    .line 1280
    new-instance v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;

    iget-wide v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->nextOutputId:J

    const-wide/16 v7, 0x1

    add-long/2addr v7, v5

    iput-wide v7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->nextOutputId:J

    invoke-direct {v2, v5, v6, v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;-><init>(JLjava/io/File;)V

    .line 1281
    iput-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputGeneration:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;

    .line 1282
    new-instance v3, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    iget-object v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    .line 1284
    invoke-virtual {v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;->getSize()I

    move-result v5

    iget-object v7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v8, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda13;

    invoke-direct {v8, p0, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda13;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;)V

    move v6, p1

    invoke-direct/range {v3 .. v8}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;-><init>(Ljava/io/File;IZLorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$Listener;)V

    iput-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->writer:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    .line 1307
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-wide v7, v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->id:J

    invoke-virtual {v3, v7, v8}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, ", includeAudio="

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v6}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v0, ", file="

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1309
    invoke-virtual {v4}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 1307
    invoke-virtual {p1, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 1310
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputExecutor:Ljava/util/concurrent/ExecutorService;

    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda14;

    invoke-direct {v0, p0, v2, v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda14;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Ljava/io/File;)V

    invoke-interface {p1, v0}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    .line 1311
    monitor-exit v1

    return-void

    :catchall_0
    move-exception v0

    move-object p0, v0

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method private createPreviewSnapshot()V
    .locals 4

    .line 980
    :try_start_0
    const-string v0, "round_video_preview_"

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->createTemporaryFile(Ljava/lang/String;)Ljava/io/File;

    move-result-object v0
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 985
    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewFile:Ljava/io/File;

    .line 986
    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v1

    iput-wide v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewSnapshotStartedNs:J

    .line 987
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "preview snapshot started: file="

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 988
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->writer:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    .line 989
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->fileExecutor:Ljava/util/concurrent/ExecutorService;

    new-instance v3, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda0;

    invoke-direct {v3, p0, v1, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;Ljava/io/File;)V

    invoke-interface {v2, v3}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    return-void

    :catch_0
    move-exception v0

    .line 982
    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->fail(Ljava/lang/Exception;)V

    return-void
.end method

.method private createStateInfo()Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;
    .locals 18

    move-object/from16 v0, p0

    .line 1347
    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;

    move-object v2, v1

    iget-object v1, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    move-object v4, v2

    iget-wide v2, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordedDurationMs:J

    move-object v6, v4

    iget-wide v4, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordingStartedAtRealtimeMs:J

    move-object v8, v6

    iget-wide v6, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->maximumDurationMs:J

    move-object v10, v8

    iget-wide v8, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimStartMs:J

    move-object v12, v10

    iget-wide v10, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimEndMs:J

    move-object v13, v12

    iget-boolean v12, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewPlaying:Z

    move-object v14, v13

    iget-boolean v13, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraSwitching:Z

    move-object v15, v14

    iget-boolean v14, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->flashEnabled:Z

    iget v0, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->zoomProgress:F

    const/16 v16, 0x0

    move-object/from16 v17, v15

    move v15, v0

    move-object/from16 v0, v17

    invoke-direct/range {v0 .. v16}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;JJJJJZZZFLorg/telegram/utils/camera/roundvideo/RoundVideoSession-IA;)V

    move-object v15, v0

    return-object v15
.end method

.method private createTemporaryFile(Ljava/lang/String;)Ljava/io/File;
    .locals 2

    .line 1471
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputDirectory:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v0

    .line 1476
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputDirectory:Ljava/io/File;

    if-eqz v0, :cond_1

    .line 1472
    invoke-virtual {v1}, Ljava/io/File;->isDirectory()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 1473
    :cond_0
    const-string p1, "Round-video output path is not a directory: "

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputDirectory:Ljava/io/File;

    invoke-static {p1, p0}, Lokio/internal/ZipFilesKt$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;Ljava/lang/Object;)V

    const/4 p0, 0x0

    return-object p0

    .line 1476
    :cond_1
    invoke-virtual {v1}, Ljava/io/File;->mkdirs()Z

    move-result v0

    if-nez v0, :cond_3

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputDirectory:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->isDirectory()Z

    move-result v0

    if-eqz v0, :cond_2

    goto :goto_0

    .line 1477
    :cond_2
    const-string p1, "Cannot create round-video output directory: "

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputDirectory:Ljava/io/File;

    invoke-static {p1, p0}, Lokio/internal/ZipFilesKt$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;Ljava/lang/Object;)V

    const/4 p0, 0x0

    return-object p0

    .line 1480
    :cond_3
    :goto_0
    const-string v0, ".mp4"

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputDirectory:Ljava/io/File;

    invoke-static {p1, v0, p0}, Ljava/io/File;->createTempFile(Ljava/lang/String;Ljava/lang/String;Ljava/io/File;)Ljava/io/File;

    move-result-object p0

    return-object p0
.end method

.method private deleteMediaFiles()V
    .locals 2

    .line 1437
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->fileExecutor:Ljava/util/concurrent/ExecutorService;

    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda5;

    invoke-direct {v1, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda5;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)V

    invoke-interface {v0, v1}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    return-void
.end method

.method private disableCameraEffects()V
    .locals 3

    const/4 v0, 0x0

    .line 1375
    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->zoomProgress:F

    const/4 v1, 0x0

    .line 1376
    iput-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->flashEnabled:Z

    .line 1377
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraController:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-virtual {v2, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->setZoom(F)Z

    .line 1378
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraController:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-virtual {v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->setTorchEnabled(Z)Z

    .line 1379
    invoke-direct {p0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->setScreenFlashEnabled(Z)V

    return-void
.end method

.method private static elapsedMs(J)J
    .locals 2

    .line 1527
    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v0

    sub-long/2addr v0, p0

    const-wide/32 p0, 0xf4240

    div-long/2addr v0, p0

    return-wide v0
.end method

.method private ensureNotCancelled()V
    .locals 0

    .line 1467
    iget-boolean p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cancelRequested:Z

    if-nez p0, :cond_0

    return-void

    :cond_0
    const-string p0, "Round-video operation was cancelled"

    invoke-static {p0}, Lorg/vosk/Model$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return-void
.end method

.method private enterPreview(J)V
    .locals 7

    .line 1005
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->PAUSING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-eq v0, v1, :cond_0

    return-void

    .line 1006
    :cond_0
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewSourceDurationMs:J

    .line 1007
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->maximumDurationMs:J

    invoke-static {v0, v1, p1, p2}, Ljava/lang/Math;->min(JJ)J

    move-result-wide p1

    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordedDurationMs:J

    const-wide/16 v0, 0x0

    .line 1008
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimStartMs:J

    .line 1009
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimEndMs:J

    .line 1010
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewView:Landroid/view/TextureView;

    const/4 p2, 0x0

    invoke-virtual {p1, p2}, Landroid/view/TextureView;->setSurfaceTextureListener(Landroid/view/TextureView$SurfaceTextureListener;)V

    .line 1011
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewView:Landroid/view/TextureView;

    iget-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->identityPreviewTransform:Landroid/graphics/Matrix;

    invoke-virtual {p1, p2}, Landroid/view/TextureView;->setTransform(Landroid/graphics/Matrix;)V

    .line 1012
    new-instance p1, Landroidx/media3/exoplayer/ExoPlayer$Builder;

    iget-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->context:Landroid/content/Context;

    invoke-direct {p1, p2}, Landroidx/media3/exoplayer/ExoPlayer$Builder;-><init>(Landroid/content/Context;)V

    invoke-virtual {p1}, Landroidx/media3/exoplayer/ExoPlayer$Builder;->build()Landroidx/media3/exoplayer/ExoPlayer;

    move-result-object p1

    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->player:Landroidx/media3/exoplayer/ExoPlayer;

    .line 1013
    iget-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewView:Landroid/view/TextureView;

    invoke-interface {p1, p2}, Landroidx/media3/common/Player;->setVideoTextureView(Landroid/view/TextureView;)V

    .line 1014
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->player:Landroidx/media3/exoplayer/ExoPlayer;

    iget-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewFile:Ljava/io/File;

    invoke-static {p2}, Landroid/net/Uri;->fromFile(Ljava/io/File;)Landroid/net/Uri;

    move-result-object p2

    invoke-static {p2}, Landroidx/media3/common/MediaItem;->fromUri(Landroid/net/Uri;)Landroidx/media3/common/MediaItem;

    move-result-object p2

    invoke-interface {p1, p2}, Landroidx/media3/common/Player;->setMediaItem(Landroidx/media3/common/MediaItem;)V

    .line 1015
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->player:Landroidx/media3/exoplayer/ExoPlayer;

    const/4 p2, 0x0

    invoke-interface {p1, p2}, Landroidx/media3/common/Player;->setRepeatMode(I)V

    .line 1016
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->player:Landroidx/media3/exoplayer/ExoPlayer;

    const/high16 p2, 0x3f800000    # 1.0f

    invoke-interface {p1, p2}, Landroidx/media3/common/Player;->setVolume(F)V

    .line 1017
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->player:Landroidx/media3/exoplayer/ExoPlayer;

    iget-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->playerListener:Landroidx/media3/common/Player$Listener;

    invoke-interface {p1, p2}, Landroidx/media3/common/Player;->addListener(Landroidx/media3/common/Player$Listener;)V

    .line 1018
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {p1}, Landroidx/media3/common/Player;->prepare()V

    .line 1019
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->player:Landroidx/media3/exoplayer/ExoPlayer;

    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimStartMs:J

    invoke-interface {p1, v0, v1}, Landroidx/media3/common/Player;->seekTo(J)V

    .line 1020
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance p2, Ljava/lang/StringBuilder;

    const-string v0, "preview player prepared: durationMs="

    invoke-direct {p2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordedDurationMs:J

    invoke-virtual {p2, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, ", trim="

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimStartMs:J

    invoke-virtual {p2, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, ".."

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimEndMs:J

    invoke-virtual {p2, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 1022
    sget-object p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->PREVIEWING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->setState(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;)V

    .line 1023
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->listener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;

    iget-wide v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordedDurationMs:J

    iget-wide v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimStartMs:J

    iget-wide v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimEndMs:J

    invoke-interface/range {v0 .. v6}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;->onPreviewReady(JJJ)V

    .line 1024
    invoke-virtual {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->playPreview()Z

    return-void
.end method

.method private fail(Ljava/lang/Exception;)V
    .locals 4

    .line 1415
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->ERROR:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-eq v0, v1, :cond_5

    sget-object v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->RELEASED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-ne v0, v2, :cond_0

    goto :goto_1

    .line 1416
    :cond_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "fatal error in state="

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->error(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 1417
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;->ERROR:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cancelOutput(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;)Z

    move-result v0

    if-nez v0, :cond_1

    goto :goto_1

    .line 1418
    :cond_1
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->disableCameraEffects()V

    .line 1419
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->releasePlayer()V

    .line 1420
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->mainHandler:Landroid/os/Handler;

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->durationLimitRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v2}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 1421
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputGeneration:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;

    if-eqz v0, :cond_2

    .line 1423
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputExecutor:Ljava/util/concurrent/ExecutorService;

    new-instance v3, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda7;

    invoke-direct {v3, p0, v0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda7;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Ljava/lang/Exception;)V

    invoke-interface {v2, v3}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    .line 1425
    :cond_2
    invoke-direct {p0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->setState(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;)V

    .line 1426
    const-string v0, "error"

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->logSummary(Ljava/lang/String;)V

    .line 1427
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->listener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;

    invoke-interface {v0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;->onError(Ljava/lang/Exception;)V

    .line 1428
    iget-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraStopPending:Z

    if-nez p1, :cond_4

    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraController:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-virtual {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->stopSegment()Z

    move-result p1

    if-eqz p1, :cond_3

    goto :goto_0

    .line 1432
    :cond_3
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->finishCancellation()V

    return-void

    :cond_4
    :goto_0
    const/4 p1, 0x1

    .line 1429
    iput-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraStopPending:Z

    .line 1430
    iput-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cancelAfterCameraStops:Z

    :cond_5
    :goto_1
    return-void
.end method

.method private finishActiveRecording(Z)V
    .locals 4

    .line 1128
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->writer:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    .line 1129
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputGeneration:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;

    .line 1130
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->fileExecutor:Ljava/util/concurrent/ExecutorService;

    new-instance v3, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda1;

    invoke-direct {v3, p0, v0, p1, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda1;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;ZLorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;)V

    invoke-interface {v2, v3}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    return-void
.end method

.method private finishCancellation()V
    .locals 1

    .line 1452
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cleanupStarted:Z

    if-eqz v0, :cond_0

    return-void

    :cond_0
    const/4 v0, 0x1

    .line 1453
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cleanupStarted:Z

    .line 1454
    const-string v0, "cancelled"

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->logSummary(Ljava/lang/String;)V

    .line 1455
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->deleteMediaFiles()V

    .line 1456
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraController:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-virtual {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->release()V

    .line 1457
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->shutdownExecutors()V

    return-void
.end method

.method private finishFromPreview(Z)V
    .locals 9

    .line 1165
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->isTrimChanged()Z

    move-result v0

    if-nez v0, :cond_1

    if-nez p1, :cond_0

    goto :goto_1

    :cond_0
    const/4 v0, 0x0

    :goto_0
    move v4, v0

    goto :goto_2

    :cond_1
    :goto_1
    const/4 v0, 0x1

    goto :goto_0

    .line 1166
    :goto_2
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->isTrimChanged()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 1167
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;->TRIMMED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    :goto_3
    move-object v7, v0

    goto :goto_4

    .line 1168
    :cond_2
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;->AUDIO_REMOVED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    goto :goto_3

    .line 1169
    :goto_4
    iget-object v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewFile:Ljava/io/File;

    .line 1170
    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->writer:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    .line 1171
    iget-object v8, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputGeneration:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;

    .line 1172
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->fileExecutor:Ljava/util/concurrent/ExecutorService;

    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda8;

    move-object v2, p0

    move v6, p1

    invoke-direct/range {v1 .. v8}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda8;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;ZLjava/io/File;ZLorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;)V

    invoke-interface {v0, v1}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    return-void
.end method

.method private handleCameraConfigured(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;Z)V
    .locals 3

    .line 925
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->isTerminalState()Z

    move-result v0

    if-eqz v0, :cond_0

    goto/16 :goto_1

    .line 926
    :cond_0
    iget-object v0, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;->facing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->activeFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    .line 927
    iget-object v1, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;->cameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->activeCameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    .line 928
    iget-object v1, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;->frameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->activeFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    .line 929
    iget v1, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;->maximumZoomRatio:F

    iput v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->maximumZoomRatio:F

    .line 930
    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;->FRONT:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    if-ne v0, v1, :cond_2

    .line 931
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->screenFlashController:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$ScreenFlashController;

    if-nez v0, :cond_1

    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;->NONE:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    goto :goto_0

    :cond_1
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;->SCREEN:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    goto :goto_0

    .line 932
    :cond_2
    iget-boolean v0, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;->torchAvailable:Z

    if-eqz v0, :cond_3

    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;->TORCH:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    goto :goto_0

    :cond_3
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;->NONE:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    :goto_0
    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->flashType:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    .line 933
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->notifyCapabilities()V

    .line 934
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "camera configured: facing="

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v2, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;->facing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v2, ", mode="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;->cameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v2, ", preview="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;->previewSize:Landroid/util/Size;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v2, ", recording="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;->recordingSize:Landroid/util/Size;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v2, ", fps="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;->frameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    .line 938
    invoke-virtual {v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->getValue()I

    move-result v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v2, ", maxZoom="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->maximumZoomRatio:F

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string v2, ", flash="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->flashType:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v2, ", switch="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 934
    invoke-virtual {v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    if-nez p2, :cond_5

    .line 942
    iget-boolean p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraSwitching:Z

    if-eqz p2, :cond_4

    goto :goto_2

    :cond_4
    :goto_1
    return-void

    :cond_5
    :goto_2
    const/4 p2, 0x0

    .line 943
    iput-boolean p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraSwitching:Z

    .line 944
    iget-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->listener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;

    iget-object p1, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;->facing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    invoke-interface {p2, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;->onCameraSwitchCompleted(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V

    .line 945
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->notifyState()V

    return-void
.end method

.method private handleRecordingStarted()V
    .locals 4

    .line 950
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->STARTING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-eq v0, v1, :cond_0

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->RESUMING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-eq v0, v1, :cond_0

    return-void

    .line 951
    :cond_0
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v0

    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordingStartedAtRealtimeMs:J

    .line 952
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "recording started: retainedDurationMs="

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordedDurationMs:J

    invoke-virtual {v1, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 953
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->RECORDING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->setState(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;)V

    .line 954
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->maximumDurationMs:J

    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordedDurationMs:J

    sub-long/2addr v0, v2

    const-wide/16 v2, 0x0

    cmp-long v2, v0, v2

    if-gtz v2, :cond_1

    .line 955
    invoke-virtual {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->pauseRecording()Z

    return-void

    .line 956
    :cond_1
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->mainHandler:Landroid/os/Handler;

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->durationLimitRunnable:Ljava/lang/Runnable;

    invoke-virtual {v2, p0, v0, v1}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    return-void
.end method

.method private handleRecordingStopped()V
    .locals 5

    const/4 v0, 0x0

    .line 960
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraStopPending:Z

    .line 961
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "recording segment stopped: state="

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v3, ", retainedDurationMs="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordedDurationMs:J

    invoke-virtual {v2, v3, v4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 963
    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cancelAfterCameraStops:Z

    if-eqz v1, :cond_0

    .line 964
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cancelAfterCameraStops:Z

    .line 965
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->finishCancellation()V

    return-void

    .line 968
    :cond_0
    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->finishAfterCameraStops:Z

    if-eqz v1, :cond_1

    .line 969
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->finishAfterCameraStops:Z

    .line 970
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->finishWithAudio:Z

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->finishActiveRecording(Z)V

    return-void

    .line 973
    :cond_1
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->PAUSING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-eq v0, v1, :cond_2

    return-void

    .line 974
    :cond_2
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->createPreviewSnapshot()V

    return-void
.end method

.method private invalidateOutput(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;)V
    .locals 6

    .line 1323
    const-string v0, "output generation invalidated: id="

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputLock:Ljava/lang/Object;

    monitor-enter v1

    .line 1324
    :try_start_0
    iget-boolean v2, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->invalidated:Z

    if-nez v2, :cond_1

    iget-boolean v2, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->completed:Z

    if-eqz v2, :cond_0

    goto :goto_0

    :cond_0
    const/4 v2, 0x1

    .line 1325
    iput-boolean v2, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->invalidated:Z

    .line 1326
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-wide v4, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->id:J

    invoke-virtual {v3, v4, v5}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, ", reason="

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v0, ", availableSize="

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v4, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->availableSize:J

    invoke-virtual {v3, v4, v5}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v2, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 1329
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputExecutor:Ljava/util/concurrent/ExecutorService;

    new-instance v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda19;

    invoke-direct {v2, p0, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda19;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;)V

    invoke-interface {v0, v2}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    .line 1330
    monitor-exit v1

    return-void

    :catchall_0
    move-exception p0

    goto :goto_1

    .line 1324
    :cond_1
    :goto_0
    monitor-exit v1

    return-void

    .line 1330
    :goto_1
    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method private isTerminalState()Z
    .locals 1

    .line 1408
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->FINISHING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-eq p0, v0, :cond_1

    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->COMPLETED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-eq p0, v0, :cond_1

    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->ERROR:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-eq p0, v0, :cond_1

    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->RELEASED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-ne p0, v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x1

    return p0
.end method

.method private isTrimChanged()Z
    .locals 6

    .line 1399
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewSourceDurationMs:J

    const-wide/16 v2, 0x0

    cmp-long v4, v0, v2

    if-lez v4, :cond_0

    goto :goto_0

    .line 1401
    :cond_0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordedDurationMs:J

    .line 1402
    :goto_0
    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->maximumDurationMs:J

    cmp-long v4, v0, v4

    if-gtz v4, :cond_2

    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimStartMs:J

    cmp-long v2, v4, v2

    if-gtz v2, :cond_2

    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimEndMs:J

    const-wide/16 v4, 0xa

    add-long/2addr v2, v4

    cmp-long p0, v2, v0

    if-gez p0, :cond_1

    goto :goto_1

    :cond_1
    const/4 p0, 0x0

    return p0

    :cond_2
    :goto_1
    const/4 p0, 0x1

    return p0
.end method

.method private synthetic lambda$cancelOutput$21(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;)V
    .locals 2

    .line 1493
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputListener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputListener;

    iget-wide v0, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->id:J

    invoke-interface {p0, v0, v1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputListener;->onOutputInvalidated(JLorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;)V

    return-void
.end method

.method private synthetic lambda$completeOutput$13(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;JLjava/io/File;Z)V
    .locals 10

    .line 1254
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->RELEASED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-eq v0, v1, :cond_2

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->ERROR:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-ne v0, v1, :cond_0

    goto :goto_0

    .line 1255
    :cond_0
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->COMPLETED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->setState(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;)V

    .line 1256
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "output completed: generation="

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-wide v2, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->id:J

    invoke-virtual {v1, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v2, ", durationMs="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2, p3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v2, ", size="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1258
    invoke-virtual {p4}, Ljava/io/File;->length()J

    move-result-wide v2

    invoke-virtual {v1, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v2, ", hasAudio="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p5}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 1256
    invoke-virtual {v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 1260
    const-string v0, "completed"

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->logSummary(Ljava/lang/String;)V

    .line 1261
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->listener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;

    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Result;

    iget-wide v2, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->id:J

    .line 1266
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->activeFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    if-nez p0, :cond_1

    sget-object p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->FPS_30:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    :cond_1
    move-object v8, p0

    const/4 v9, 0x0

    move-wide v5, p2

    move-object v4, p4

    move v7, p5

    invoke-direct/range {v1 .. v9}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Result;-><init>(JLjava/io/File;JZLorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession-IA;)V

    .line 1261
    invoke-interface {v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;->onCompleted(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Result;)V

    :cond_2
    :goto_0
    return-void
.end method

.method private synthetic lambda$completeOutput$14(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Ljava/io/File;JZ)V
    .locals 13

    .line 1242
    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputLock:Ljava/lang/Object;

    monitor-enter v3

    .line 1243
    :try_start_0
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cancelRequested:Z

    if-nez v0, :cond_1

    iget-boolean v0, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->invalidated:Z

    if-nez v0, :cond_1

    iget-boolean v0, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->completed:Z

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x1

    .line 1244
    iput-boolean v0, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->completed:Z

    .line 1245
    monitor-exit v3
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 1246
    iget-object v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputListener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputListener;

    iget-wide v5, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->id:J

    .line 1249
    invoke-virtual {p2}, Ljava/io/File;->length()J

    move-result-wide v8

    move-object v7, p2

    move-wide/from16 v10, p3

    move/from16 v12, p5

    .line 1246
    invoke-interface/range {v4 .. v12}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputListener;->onOutputCompleted(JLjava/io/File;JJZ)V

    .line 1253
    iget-object v7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->mainHandler:Landroid/os/Handler;

    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda21;

    move-object v1, p0

    move-object v2, p1

    move-object v5, p2

    move-wide/from16 v3, p3

    move/from16 v6, p5

    invoke-direct/range {v0 .. v6}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda21;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;JLjava/io/File;Z)V

    invoke-virtual {v7, v0}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    return-void

    :catchall_0
    move-exception v0

    goto :goto_1

    .line 1243
    :cond_1
    :goto_0
    :try_start_1
    monitor-exit v3

    return-void

    .line 1245
    :goto_1
    monitor-exit v3
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method

.method private synthetic lambda$createOutputGenerationOnFileThread$15(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;JJ)V
    .locals 10

    .line 1294
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputLock:Ljava/lang/Object;

    monitor-enter v1

    .line 1295
    :try_start_0
    iget-boolean v0, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->invalidated:Z

    if-nez v0, :cond_1

    iget-boolean v0, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->completed:Z

    if-eqz v0, :cond_0

    goto :goto_0

    .line 1296
    :cond_0
    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 1297
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputListener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputListener;

    iget-wide v3, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->id:J

    iget-object v5, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->file:Ljava/io/File;

    move-wide v6, p2

    move-wide v8, p4

    invoke-interface/range {v2 .. v9}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputListener;->onBytesAvailable(JLjava/io/File;JJ)V

    return-void

    :catchall_0
    move-exception v0

    move-object p0, v0

    goto :goto_1

    .line 1295
    :cond_1
    :goto_0
    :try_start_1
    monitor-exit v1

    return-void

    .line 1296
    :goto_1
    monitor-exit v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p0
.end method

.method private synthetic lambda$createOutputGenerationOnFileThread$16(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;J)V
    .locals 9

    .line 1288
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputLock:Ljava/lang/Object;

    monitor-enter v1

    .line 1289
    :try_start_0
    iget-wide v5, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->availableSize:J

    sub-long v7, p2, v5

    const-wide/16 v2, 0x0

    cmp-long v0, v7, v2

    if-lez v0, :cond_1

    .line 1291
    iget-boolean v0, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->invalidated:Z

    if-eqz v0, :cond_0

    goto :goto_0

    .line 1292
    :cond_0
    iput-wide p2, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->availableSize:J

    .line 1293
    iget-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputExecutor:Ljava/util/concurrent/ExecutorService;

    new-instance v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda18;

    move-object v3, p0

    move-object v4, p1

    invoke-direct/range {v2 .. v8}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda18;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;JJ)V

    invoke-interface {p2, v2}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    .line 1304
    monitor-exit v1

    return-void

    :catchall_0
    move-exception v0

    move-object p0, v0

    goto :goto_1

    .line 1291
    :cond_1
    :goto_0
    monitor-exit v1

    return-void

    .line 1304
    :goto_1
    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method private synthetic lambda$createOutputGenerationOnFileThread$17(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Ljava/io/File;)V
    .locals 2

    .line 1310
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputListener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputListener;

    iget-wide v0, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->id:J

    invoke-interface {p0, v0, v1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputListener;->onOutputStarted(JLjava/io/File;)V

    return-void
.end method

.method private synthetic lambda$createPreviewSnapshot$3(J)V
    .locals 0

    .line 997
    invoke-direct {p0, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->enterPreview(J)V

    return-void
.end method

.method private synthetic lambda$createPreviewSnapshot$4(Ljava/lang/Exception;)V
    .locals 0

    .line 999
    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->asException(Ljava/lang/Exception;)Ljava/lang/Exception;

    move-result-object p1

    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->fail(Ljava/lang/Exception;)V

    return-void
.end method

.method private synthetic lambda$createPreviewSnapshot$5(Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;Ljava/io/File;)V
    .locals 6

    .line 991
    const-string v0, "preview snapshot completed: durationMs="

    :try_start_0
    invoke-virtual {p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->createPreview(Ljava/io/File;)V

    .line 992
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->ensureNotCancelled()V

    .line 993
    invoke-static {p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->getDurationMs(Ljava/io/File;)J

    move-result-wide v1

    .line 994
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v1, v2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, ", size="

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 995
    invoke-virtual {p2}, Ljava/io/File;->length()J

    move-result-wide v4

    invoke-virtual {v3, v4, v5}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p2, ", elapsedMs="

    invoke-virtual {v3, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewSnapshotStartedNs:J

    .line 996
    invoke-static {v4, v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->elapsedMs(J)J

    move-result-wide v4

    invoke-virtual {v3, v4, v5}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    .line 994
    invoke-virtual {p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 997
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->mainHandler:Landroid/os/Handler;

    new-instance p2, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda3;

    invoke-direct {p2, p0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda3;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;J)V

    invoke-virtual {p1, p2}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p1

    .line 999
    iget-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->mainHandler:Landroid/os/Handler;

    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda4;

    invoke-direct {v0, p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda4;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Ljava/lang/Exception;)V

    invoke-virtual {p2, v0}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    return-void
.end method

.method private synthetic lambda$deleteMediaFiles$20()V
    .locals 1

    .line 1438
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->writer:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    .line 1439
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewFile:Ljava/io/File;

    if-eqz v0, :cond_0

    .line 1442
    :try_start_0
    invoke-virtual {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->closeForReplacement()V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 1445
    :catch_0
    invoke-virtual {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->getFile()Ljava/io/File;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->erase(Ljava/io/File;)V

    :cond_0
    if-eqz p0, :cond_1

    .line 1447
    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->erase(Ljava/io/File;)V

    :cond_1
    return-void
.end method

.method private synthetic lambda$fail$19(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Ljava/lang/Exception;)V
    .locals 2

    .line 1423
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputListener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputListener;

    iget-wide v0, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->id:J

    invoke-interface {p0, v0, v1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputListener;->onOutputError(JLjava/lang/Exception;)V

    return-void
.end method

.method private synthetic lambda$finishActiveRecording$10(Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;ZLorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;)V
    .locals 10

    .line 1131
    const-string v0, "active output finalized: size="

    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v2

    .line 1133
    :try_start_0
    invoke-virtual {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->finish()V

    .line 1134
    invoke-virtual {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->getFile()Ljava/io/File;

    move-result-object v4

    invoke-static {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->getDurationMs(Ljava/io/File;)J

    move-result-wide v4

    .line 1135
    iget-object v6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->getFile()Ljava/io/File;

    move-result-object v0

    invoke-virtual {v0}, Ljava/io/File;->length()J

    move-result-wide v8

    invoke-virtual {v7, v8, v9}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, ", durationMs="

    invoke-virtual {v7, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v4, v5}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, ", elapsedMs="

    invoke-virtual {v7, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1137
    invoke-static {v2, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->elapsedMs(J)J

    move-result-wide v2

    invoke-virtual {v7, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 1135
    invoke-virtual {v6, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 1138
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->ensureNotCancelled()V

    .line 1139
    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->maximumDurationMs:J

    cmp-long v0, v4, v2

    if-lez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    if-eqz p2, :cond_1

    if-nez v0, :cond_1

    .line 1141
    invoke-virtual {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->getFile()Ljava/io/File;

    move-result-object v3

    const/4 v6, 0x1

    move-object v1, p0

    move-object v2, p3

    invoke-direct/range {v1 .. v6}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->completeOutput(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Ljava/io/File;JZ)V

    return-void

    :catch_0
    move-exception v0

    goto :goto_4

    .line 1143
    :cond_1
    invoke-virtual {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->getFile()Ljava/io/File;

    move-result-object v2
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 1145
    :try_start_1
    iget-wide v6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->maximumDurationMs:J

    .line 1148
    invoke-static {v6, v7, v4, v5}, Ljava/lang/Math;->min(JJ)J

    move-result-wide v5

    if-eqz v0, :cond_2

    .line 1151
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;->TRIMMED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    :goto_1
    move-object v8, v0

    goto :goto_2

    :catchall_0
    move-exception v0

    goto :goto_3

    .line 1152
    :cond_2
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;->AUDIO_REMOVED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    goto :goto_1

    :goto_2
    const-wide/16 v3, 0x0

    move-object v1, p0

    move v7, p2

    .line 1145
    invoke-direct/range {v1 .. v8}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->replaceWithFinalRange(Ljava/io/File;JJZLorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 1155
    :try_start_2
    invoke-static {v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->erase(Ljava/io/File;)V

    return-void

    :goto_3
    invoke-static {v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->erase(Ljava/io/File;)V

    .line 1156
    throw v0
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    .line 1159
    :goto_4
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->mainHandler:Landroid/os/Handler;

    new-instance v3, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda2;

    invoke-direct {v3, p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda2;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Ljava/lang/Exception;)V

    invoke-virtual {v2, v3}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    return-void
.end method

.method private synthetic lambda$finishActiveRecording$9(Ljava/lang/Exception;)V
    .locals 0

    .line 1159
    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->asException(Ljava/lang/Exception;)Ljava/lang/Exception;

    move-result-object p1

    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->fail(Ljava/lang/Exception;)V

    return-void
.end method

.method private synthetic lambda$finishFromPreview$11(Ljava/lang/Exception;)V
    .locals 0

    .line 1194
    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->asException(Ljava/lang/Exception;)Ljava/lang/Exception;

    move-result-object p1

    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->fail(Ljava/lang/Exception;)V

    return-void
.end method

.method private synthetic lambda$finishFromPreview$12(Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;ZLjava/io/File;ZLorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;)V
    .locals 9

    .line 1173
    const-string v0, "preview output finalized: size="

    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v2

    .line 1175
    :try_start_0
    invoke-virtual {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->finish()V

    .line 1176
    iget-object v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->getFile()Ljava/io/File;

    move-result-object v0

    invoke-virtual {v0}, Ljava/io/File;->length()J

    move-result-wide v6

    invoke-virtual {v5, v6, v7}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, ", replace="

    invoke-virtual {v5, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, p2}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v0, ", elapsedMs="

    invoke-virtual {v5, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1178
    invoke-static {v2, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->elapsedMs(J)J

    move-result-wide v2

    invoke-virtual {v5, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 1176
    invoke-virtual {v4, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 1179
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->ensureNotCancelled()V

    if-eqz p2, :cond_0

    .line 1181
    iget-wide v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimStartMs:J

    iget-wide v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimEndMs:J

    move-object v1, p0

    move-object v2, p3

    move v7, p4

    move-object v8, p5

    invoke-direct/range {v1 .. v8}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->replaceWithFinalRange(Ljava/io/File;JJZLorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;)V

    goto :goto_1

    :catchall_0
    move-exception v0

    goto :goto_3

    :catch_0
    move-exception v0

    goto :goto_2

    .line 1185
    :cond_0
    invoke-virtual {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->getFile()Ljava/io/File;

    move-result-object v3

    .line 1186
    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewSourceDurationMs:J

    const-wide/16 v6, 0x0

    cmp-long v0, v4, v6

    if-lez v0, :cond_1

    goto :goto_0

    .line 1188
    :cond_1
    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordedDurationMs:J

    :goto_0
    const/4 v6, 0x1

    move-object v1, p0

    move-object v2, p6

    .line 1183
    invoke-direct/range {v1 .. v6}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->completeOutput(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Ljava/io/File;JZ)V

    :goto_1
    const/4 v0, 0x0

    .line 1192
    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewFile:Ljava/io/File;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz p3, :cond_2

    .line 1196
    invoke-static {p3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->erase(Ljava/io/File;)V

    :cond_2
    if-eqz p2, :cond_4

    .line 1197
    invoke-virtual {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->getFile()Ljava/io/File;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->erase(Ljava/io/File;)V

    return-void

    .line 1194
    :goto_2
    :try_start_1
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->mainHandler:Landroid/os/Handler;

    new-instance v3, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda15;

    invoke-direct {v3, p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda15;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Ljava/lang/Exception;)V

    invoke-virtual {v2, v3}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    if-eqz p3, :cond_3

    .line 1196
    invoke-static {p3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->erase(Ljava/io/File;)V

    :cond_3
    if-eqz p2, :cond_4

    .line 1197
    invoke-virtual {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->getFile()Ljava/io/File;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->erase(Ljava/io/File;)V

    :cond_4
    return-void

    :goto_3
    if-eqz p3, :cond_5

    .line 1196
    invoke-static {p3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->erase(Ljava/io/File;)V

    :cond_5
    if-eqz p2, :cond_6

    .line 1197
    invoke-virtual {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->getFile()Ljava/io/File;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->erase(Ljava/io/File;)V

    .line 1198
    :cond_6
    throw v0
.end method

.method private synthetic lambda$invalidateOutput$18(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;)V
    .locals 2

    .line 1329
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputListener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputListener;

    iget-wide v0, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->id:J

    invoke-interface {p0, v0, v1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputListener;->onOutputInvalidated(JLorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;)V

    return-void
.end method

.method private synthetic lambda$new$2()V
    .locals 2

    .line 557
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->RECORDING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-ne v0, v1, :cond_0

    invoke-virtual {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->pauseRecording()Z

    :cond_0
    return-void
.end method

.method private synthetic lambda$rebuildForResume$6(J)V
    .locals 4

    .line 1106
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->RESUMING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-eq v0, v1, :cond_0

    return-void

    .line 1107
    :cond_0
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordedDurationMs:J

    const-wide/16 v0, 0x0

    .line 1108
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewSourceDurationMs:J

    .line 1109
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimStartMs:J

    .line 1110
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimEndMs:J

    .line 1111
    sget-object p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->STARTING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->setState(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;)V

    .line 1112
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraController:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    iget-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->writer:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordedDurationMs:J

    const-wide/16 v2, 0x3e8

    mul-long/2addr v0, v2

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requestedFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    invoke-virtual {p1, p2, v0, v1, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->startSegment(Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;JLorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V

    return-void
.end method

.method private synthetic lambda$rebuildForResume$7(Ljava/lang/Exception;)V
    .locals 0

    .line 1119
    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->asException(Ljava/lang/Exception;)Ljava/lang/Exception;

    move-result-object p1

    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->fail(Ljava/lang/Exception;)V

    return-void
.end method

.method private synthetic lambda$rebuildForResume$8(ZLorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Ljava/io/File;)V
    .locals 16

    move-object/from16 v1, p0

    .line 1071
    const-string v0, "resume prepared without remux: retainedDurationMs="

    const-string v2, "resume trim remux completed: requested="

    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v3

    const/4 v5, 0x0

    .line 1074
    :try_start_0
    invoke-direct {v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->ensureNotCancelled()V

    .line 1075
    iget-wide v6, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordedDurationMs:J
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 1076
    const-string v8, ", elapsedMs="

    if-eqz p1, :cond_0

    .line 1077
    :try_start_1
    invoke-virtual/range {p2 .. p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->closeForReplacement()V

    .line 1078
    invoke-direct {v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->ensureNotCancelled()V

    .line 1079
    sget-object v6, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;->TRIMMED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    move-object/from16 v7, p3

    invoke-direct {v1, v7, v6}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->invalidateOutput(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;)V

    .line 1080
    invoke-virtual/range {p2 .. p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->getFile()Ljava/io/File;

    move-result-object v6
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    const/4 v7, 0x1

    .line 1082
    :try_start_2
    invoke-direct {v1, v7}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->createOutputGenerationOnFileThread(Z)V

    .line 1083
    iget-object v10, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->writer:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    iget-wide v11, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimStartMs:J

    iget-wide v13, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimEndMs:J

    const/4 v15, 0x1

    move-object/from16 v9, p4

    invoke-static/range {v9 .. v15}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->copyRange(Ljava/io/File;Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;JJZ)Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer$Result;

    move-result-object v7

    .line 1090
    iget-wide v9, v7, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer$Result;->durationMs:J

    .line 1091
    iget-object v11, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v12, Ljava/lang/StringBuilder;

    invoke-direct {v12, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-wide v13, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimStartMs:J

    invoke-virtual {v12, v13, v14}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v2, ".."

    invoke-virtual {v12, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v13, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimEndMs:J

    invoke-virtual {v12, v13, v14}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v2, ", actualStartMs="

    invoke-virtual {v12, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v13, v7, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer$Result;->startMs:J

    invoke-virtual {v12, v13, v14}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v2, ", retainedDurationMs="

    invoke-virtual {v12, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v12, v9, v10}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v2, ", outputSize="

    invoke-virtual {v12, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->writer:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    .line 1095
    invoke-virtual {v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->getFile()Ljava/io/File;

    move-result-object v2

    invoke-virtual {v2}, Ljava/io/File;->length()J

    move-result-wide v13

    invoke-virtual {v12, v13, v14}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v12, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1096
    invoke-static {v3, v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->elapsedMs(J)J

    move-result-wide v13

    invoke-virtual {v12, v13, v14}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v12}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    .line 1091
    invoke-virtual {v11, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    move-object v2, v6

    move-wide v6, v9

    goto :goto_0

    :catchall_0
    move-exception v0

    move-object v5, v6

    goto :goto_3

    :catch_0
    move-exception v0

    move-object v5, v6

    goto :goto_2

    :catchall_1
    move-exception v0

    goto :goto_3

    :catch_1
    move-exception v0

    goto :goto_2

    :cond_0
    move-object v2, v5

    .line 1098
    :goto_0
    :try_start_3
    invoke-direct {v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->ensureNotCancelled()V

    .line 1099
    iput-object v5, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewFile:Ljava/io/File;

    if-nez p1, :cond_1

    .line 1102
    iget-object v5, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v9, Ljava/lang/StringBuilder;

    invoke-direct {v9, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v9, v6, v7}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v9, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1103
    invoke-static {v3, v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->elapsedMs(J)J

    move-result-wide v3

    invoke-virtual {v9, v3, v4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v9}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 1102
    invoke-virtual {v5, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    goto :goto_1

    :catchall_2
    move-exception v0

    move-object v5, v2

    goto :goto_3

    :catch_2
    move-exception v0

    move-object v5, v2

    goto :goto_2

    .line 1105
    :cond_1
    :goto_1
    iget-object v0, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->mainHandler:Landroid/os/Handler;

    new-instance v3, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda16;

    invoke-direct {v3, v1, v6, v7}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda16;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;J)V

    invoke-virtual {v0, v3}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_2
    .catchall {:try_start_3 .. :try_end_3} :catchall_2

    if-eqz p4, :cond_2

    .line 1121
    invoke-static/range {p4 .. p4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->erase(Ljava/io/File;)V

    :cond_2
    if-eqz v2, :cond_4

    .line 1122
    invoke-static {v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->erase(Ljava/io/File;)V

    return-void

    .line 1119
    :goto_2
    :try_start_4
    iget-object v2, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->mainHandler:Landroid/os/Handler;

    new-instance v3, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda17;

    invoke-direct {v3, v1, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda17;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Ljava/lang/Exception;)V

    invoke-virtual {v2, v3}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_1

    if-eqz p4, :cond_3

    .line 1121
    invoke-static/range {p4 .. p4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->erase(Ljava/io/File;)V

    :cond_3
    if-eqz v5, :cond_4

    .line 1122
    invoke-static {v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->erase(Ljava/io/File;)V

    :cond_4
    return-void

    :goto_3
    if-eqz p4, :cond_5

    .line 1121
    invoke-static/range {p4 .. p4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->erase(Ljava/io/File;)V

    :cond_5
    if-eqz v5, :cond_6

    .line 1122
    invoke-static {v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->erase(Ljava/io/File;)V

    .line 1123
    :cond_6
    throw v0
.end method

.method private logSummary(Ljava/lang/String;)V
    .locals 5

    .line 1511
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->summaryLogged:Z

    if-eqz v0, :cond_0

    return-void

    :cond_0
    const/4 v0, 0x1

    .line 1512
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->summaryLogged:Z

    .line 1513
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputGeneration:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;

    .line 1514
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "session summary: terminal="

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, ", state="

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p1, ", durationMs="

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1516
    invoke-virtual {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->getRecordedDurationMs()J

    move-result-wide v3

    invoke-virtual {v2, v3, v4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p1, ", pauses="

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->pauseCount:I

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p1, ", resumes="

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->resumeCount:I

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p1, ", cameraSwitches="

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraSwitchCount:I

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p1, ", facing="

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->activeFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p1, ", cameraMode="

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->activeCameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p0, ", generation="

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-wide/16 p0, 0x0

    if-nez v0, :cond_1

    move-wide v3, p0

    goto :goto_0

    .line 1522
    :cond_1
    iget-wide v3, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->id:J

    :goto_0
    invoke-virtual {v2, v3, v4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v3, ", availableSize="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    if-nez v0, :cond_2

    goto :goto_1

    .line 1523
    :cond_2
    iget-wide p0, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;->availableSize:J

    :goto_1
    invoke-virtual {v2, p0, p1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 1514
    invoke-virtual {v1, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    return-void
.end method

.method private notifyCapabilities()V
    .locals 11

    .line 1362
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->listener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;

    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraCapabilities;

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requestedFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->activeFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    iget-object v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requestedCameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    iget-object v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->activeCameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    iget-object v6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requestedFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    iget-object v7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->activeFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    iget-object v8, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->flashType:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    iget v9, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->maximumZoomRatio:F

    const/4 v10, 0x0

    invoke-direct/range {v1 .. v10}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraCapabilities;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;FLorg/telegram/utils/camera/roundvideo/RoundVideoSession-IA;)V

    invoke-interface {v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;->onCameraCapabilitiesChanged(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraCapabilities;)V

    return-void
.end method

.method private notifyState()V
    .locals 1

    .line 1342
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->listener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;

    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->createStateInfo()Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;

    move-result-object p0

    invoke-interface {v0, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;->onStateChanged(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;)V

    return-void
.end method

.method private rebuildForResume()V
    .locals 7

    .line 1066
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->isTrimChanged()Z

    move-result v2

    .line 1067
    iget-object v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewFile:Ljava/io/File;

    .line 1068
    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->writer:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    .line 1069
    iget-object v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputGeneration:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;

    .line 1070
    iget-object v6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->fileExecutor:Ljava/util/concurrent/ExecutorService;

    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda9;

    move-object v1, p0

    invoke-direct/range {v0 .. v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda9;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;ZLorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Ljava/io/File;)V

    invoke-interface {v6, v0}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    return-void
.end method

.method private releasePlayer()V
    .locals 2

    .line 1389
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->mainHandler:Landroid/os/Handler;

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewProgressRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    const/4 v0, 0x0

    .line 1390
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewPlaying:Z

    .line 1391
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-nez v0, :cond_0

    return-void

    .line 1392
    :cond_0
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->playerListener:Landroidx/media3/common/Player$Listener;

    invoke-interface {v0, v1}, Landroidx/media3/common/Player;->removeListener(Landroidx/media3/common/Player$Listener;)V

    .line 1393
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->player:Landroidx/media3/exoplayer/ExoPlayer;

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewView:Landroid/view/TextureView;

    invoke-interface {v0, v1}, Landroidx/media3/common/Player;->clearVideoTextureView(Landroid/view/TextureView;)V

    .line 1394
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {v0}, Landroidx/media3/exoplayer/ExoPlayer;->release()V

    const/4 v0, 0x0

    .line 1395
    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->player:Landroidx/media3/exoplayer/ExoPlayer;

    return-void
.end method

.method private replaceWithFinalRange(Ljava/io/File;JJZLorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;)V
    .locals 5

    .line 1209
    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v0

    .line 1210
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v3, Ljava/lang/StringBuilder;

    const-string v4, "final range remux started: range="

    invoke-direct {v3, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, p2, p3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v4, ".."

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, p4, p5}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v4, ", includeAudio="

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, p6}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v4, ", reason="

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, p7}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 1212
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->ensureNotCancelled()V

    .line 1213
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputGeneration:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;

    .line 1214
    invoke-direct {p0, v2, p7}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->invalidateOutput(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;)V

    .line 1215
    invoke-direct {p0, p6}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->createOutputGenerationOnFileThread(Z)V

    .line 1216
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->ensureNotCancelled()V

    move p7, p6

    move-wide p5, p4

    move-wide p3, p2

    .line 1217
    iget-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->writer:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    invoke-static/range {p1 .. p7}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->copyRange(Ljava/io/File;Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;JJZ)Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer$Result;

    move-result-object p1

    .line 1224
    iget-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->writer:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    invoke-virtual {p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->finish()V

    .line 1225
    iget-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->writer:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    invoke-virtual {p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->getFile()Ljava/io/File;

    move-result-object p2

    invoke-static {p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer;->getDurationMs(Ljava/io/File;)J

    move-result-wide p3

    .line 1226
    iget-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance p5, Ljava/lang/StringBuilder;

    const-string p6, "final range remux completed: durationMs="

    invoke-direct {p5, p6}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p5, p3, p4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p6, ", requestedDurationMs="

    invoke-virtual {p5, p6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v2, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer$Result;->durationMs:J

    invoke-virtual {p5, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p6, ", actualStartMs="

    invoke-virtual {p5, p6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v2, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoRemuxer$Result;->startMs:J

    invoke-virtual {p5, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p1, ", size="

    invoke-virtual {p5, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->writer:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    .line 1229
    invoke-virtual {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->getFile()Ljava/io/File;

    move-result-object p1

    invoke-virtual {p1}, Ljava/io/File;->length()J

    move-result-wide v2

    invoke-virtual {p5, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p1, ", elapsedMs="

    invoke-virtual {p5, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1230
    invoke-static {v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->elapsedMs(J)J

    move-result-wide v0

    invoke-virtual {p5, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {p5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    .line 1226
    invoke-virtual {p2, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 1231
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->ensureNotCancelled()V

    .line 1232
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputGeneration:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;

    iget-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->writer:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    invoke-virtual {p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->getFile()Ljava/io/File;

    move-result-object p2

    move p5, p7

    invoke-direct/range {p0 .. p5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->completeOutput(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Ljava/io/File;JZ)V

    return-void
.end method

.method private static requireMainThread()V
    .locals 2

    .line 1549
    invoke-static {}, Landroid/os/Looper;->myLooper()Landroid/os/Looper;

    move-result-object v0

    invoke-static {}, Landroid/os/Looper;->getMainLooper()Landroid/os/Looper;

    move-result-object v1

    if-ne v0, v1, :cond_0

    return-void

    .line 1550
    :cond_0
    const-string v0, "RoundVideoSession must be used from the main thread"

    invoke-static {v0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method

.method private setScreenFlashEnabled(Z)V
    .locals 1

    .line 1383
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->screenFlashEnabled:Z

    if-ne v0, p1, :cond_0

    goto :goto_0

    .line 1384
    :cond_0
    iput-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->screenFlashEnabled:Z

    .line 1385
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->screenFlashController:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$ScreenFlashController;

    if-eqz p0, :cond_1

    invoke-interface {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$ScreenFlashController;->setScreenFlashEnabled(Z)V

    :cond_1
    :goto_0
    return-void
.end method

.method private setState(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;)V
    .locals 5

    .line 1334
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    .line 1335
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    .line 1336
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "state: "

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v0, " -> "

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p1, ", durationMs="

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1337
    invoke-virtual {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->getRecordedDurationMs()J

    move-result-wide v3

    invoke-virtual {v2, v3, v4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    .line 1336
    invoke-virtual {v1, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 1338
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->notifyState()V

    return-void
.end method

.method private shutdownExecutors()V
    .locals 2

    .line 1461
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->mainHandler:Landroid/os/Handler;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacksAndMessages(Ljava/lang/Object;)V

    .line 1462
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->fileExecutor:Ljava/util/concurrent/ExecutorService;

    invoke-interface {v0}, Ljava/util/concurrent/ExecutorService;->shutdown()V

    .line 1463
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->outputExecutor:Ljava/util/concurrent/ExecutorService;

    invoke-interface {p0}, Ljava/util/concurrent/ExecutorService;->shutdown()V

    return-void
.end method

.method private updatePreviewPlaying(Z)V
    .locals 2

    .line 1057
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewPlaying:Z

    if-ne v0, p1, :cond_0

    return-void

    .line 1058
    :cond_0
    iput-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewPlaying:Z

    .line 1059
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->mainHandler:Landroid/os/Handler;

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewProgressRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    if-eqz p1, :cond_1

    .line 1060
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->mainHandler:Landroid/os/Handler;

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewProgressRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 1061
    :cond_1
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->listener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;

    invoke-interface {v0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;->onPreviewPlaybackChanged(Z)V

    .line 1062
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->notifyState()V

    return-void
.end method


# virtual methods
.method public cancel()Z
    .locals 5

    .line 797
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requireMainThread()V

    .line 798
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->RELEASED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    const/4 v2, 0x0

    if-eq v0, v1, :cond_4

    sget-object v3, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->COMPLETED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-ne v0, v3, :cond_0

    goto :goto_2

    .line 799
    :cond_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v3, Ljava/lang/StringBuilder;

    const-string v4, "cancel requested: state="

    invoke-direct {v3, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 800
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;->CANCELLED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cancelOutput(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;)Z

    move-result v0

    if-nez v0, :cond_1

    return v2

    .line 801
    :cond_1
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->disableCameraEffects()V

    .line 802
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->releasePlayer()V

    .line 803
    invoke-direct {p0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->setState(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;)V

    .line 804
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraStopPending:Z

    const/4 v1, 0x1

    if-nez v0, :cond_3

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraController:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-virtual {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->stopSegment()Z

    move-result v0

    if-eqz v0, :cond_2

    goto :goto_0

    .line 808
    :cond_2
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->finishCancellation()V

    goto :goto_1

    .line 805
    :cond_3
    :goto_0
    iput-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraStopPending:Z

    .line 806
    iput-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cancelAfterCameraStops:Z

    :goto_1
    return v1

    :cond_4
    :goto_2
    return v2
.end method

.method public finish(Z)Z
    .locals 5

    .line 771
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requireMainThread()V

    .line 772
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->RECORDING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-eq v0, v1, :cond_0

    sget-object v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->PREVIEWING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-eq v0, v2, :cond_0

    const/4 p0, 0x0

    return p0

    .line 773
    :cond_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "finish requested: state="

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v3, ", includeAudio="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v3, ", durationMs="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 775
    invoke-virtual {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->getRecordedDurationMs()J

    move-result-wide v3

    invoke-virtual {v2, v3, v4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v3, ", trim="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimStartMs:J

    invoke-virtual {v2, v3, v4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v3, ".."

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimEndMs:J

    invoke-virtual {v2, v3, v4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    .line 773
    invoke-virtual {v0, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 777
    iput-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->finishWithAudio:Z

    .line 778
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    const/4 v2, 0x1

    if-ne v0, v1, :cond_1

    .line 779
    invoke-virtual {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->getRecordedDurationMs()J

    move-result-wide v0

    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordedDurationMs:J

    .line 780
    iput-boolean v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->finishAfterCameraStops:Z

    .line 781
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->disableCameraEffects()V

    .line 782
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->mainHandler:Landroid/os/Handler;

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->durationLimitRunnable:Ljava/lang/Runnable;

    invoke-virtual {p1, v0}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 783
    sget-object p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->FINISHING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->setState(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;)V

    .line 784
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraController:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-virtual {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->stopSegment()Z

    move-result p1

    iput-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraStopPending:Z

    if-nez p1, :cond_2

    .line 785
    new-instance p1, Ljava/lang/IllegalStateException;

    const-string v0, "Unable to stop the camera segment"

    invoke-direct {p1, v0}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->fail(Ljava/lang/Exception;)V

    goto :goto_0

    .line 787
    :cond_1
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->releasePlayer()V

    .line 788
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->FINISHING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->setState(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;)V

    .line 789
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->finishFromPreview(Z)V

    :cond_2
    :goto_0
    return v2
.end method

.method public getActiveFrameRate()Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;
    .locals 0

    .line 865
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->activeFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    return-object p0
.end method

.method public getPreviewFile()Ljava/io/File;
    .locals 0

    .line 844
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requireMainThread()V

    .line 845
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->previewFile:Ljava/io/File;

    return-object p0
.end method

.method public getRecordedDurationMs()J
    .locals 6

    .line 871
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requireMainThread()V

    .line 872
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->RECORDING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-eq v0, v1, :cond_0

    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordedDurationMs:J

    return-wide v0

    .line 873
    :cond_0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->maximumDurationMs:J

    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordedDurationMs:J

    .line 875
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v4

    add-long/2addr v2, v4

    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordingStartedAtRealtimeMs:J

    sub-long/2addr v2, v4

    .line 873
    invoke-static {v0, v1, v2, v3}, Ljava/lang/Math;->min(JJ)J

    move-result-wide v0

    return-wide v0
.end method

.method public pausePreview()Z
    .locals 5

    .line 696
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requireMainThread()V

    .line 697
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->PREVIEWING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    const/4 v2, 0x0

    if-ne v0, v1, :cond_1

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-nez v0, :cond_0

    goto :goto_0

    .line 698
    :cond_0
    invoke-interface {v0}, Landroidx/media3/common/Player;->pause()V

    .line 699
    invoke-direct {p0, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->updatePreviewPlaying(Z)V

    .line 700
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->listener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {v1}, Landroidx/media3/common/Player;->getCurrentPosition()J

    move-result-wide v1

    iget-wide v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordedDurationMs:J

    invoke-interface {v0, v1, v2, v3, v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;->onPreviewProgress(JJ)V

    const/4 p0, 0x1

    return p0

    :cond_1
    :goto_0
    return v2
.end method

.method public pauseRecording()Z
    .locals 5

    .line 636
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requireMainThread()V

    .line 637
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->RECORDING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-eq v0, v1, :cond_0

    const/4 p0, 0x0

    return p0

    .line 638
    :cond_0
    invoke-virtual {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->getRecordedDurationMs()J

    move-result-wide v0

    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordedDurationMs:J

    .line 639
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->pauseCount:I

    const/4 v1, 0x1

    add-int/2addr v0, v1

    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->pauseCount:I

    .line 640
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "pause requested: durationMs="

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-wide v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordedDurationMs:J

    invoke-virtual {v2, v3, v4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 641
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->disableCameraEffects()V

    .line 642
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->mainHandler:Landroid/os/Handler;

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->durationLimitRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v2}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 643
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->PAUSING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->setState(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;)V

    .line 644
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraController:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-virtual {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->stopSegment()Z

    move-result v0

    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraStopPending:Z

    if-nez v0, :cond_1

    .line 645
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v2, "Unable to stop the camera segment"

    invoke-direct {v0, v2}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->fail(Ljava/lang/Exception;)V

    :cond_1
    return v1
.end method

.method public playPreview()Z
    .locals 6

    .line 684
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requireMainThread()V

    .line 685
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->PREVIEWING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-ne v0, v1, :cond_3

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-nez v0, :cond_0

    goto :goto_0

    .line 686
    :cond_0
    invoke-interface {v0}, Landroidx/media3/common/Player;->getCurrentPosition()J

    move-result-wide v0

    .line 687
    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimStartMs:J

    cmp-long v4, v0, v2

    if-ltz v4, :cond_1

    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimEndMs:J

    cmp-long v0, v0, v4

    if-ltz v0, :cond_2

    :cond_1
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {v0, v2, v3}, Landroidx/media3/common/Player;->seekTo(J)V

    .line 688
    :cond_2
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {v0}, Landroidx/media3/common/Player;->play()V

    const/4 v0, 0x1

    .line 689
    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->updatePreviewPlaying(Z)V

    return v0

    :cond_3
    :goto_0
    const/4 p0, 0x0

    return p0
.end method

.method public release()V
    .locals 3

    .line 816
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requireMainThread()V

    .line 817
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->RELEASED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-ne v0, v1, :cond_1

    .line 818
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cleanupStarted:Z

    if-nez v0, :cond_0

    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->finishCancellation()V

    :cond_0
    return-void

    .line 821
    :cond_1
    sget-object v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->COMPLETED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-eq v0, v2, :cond_2

    .line 822
    invoke-virtual {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cancel()Z

    return-void

    .line 825
    :cond_2
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->disableCameraEffects()V

    .line 826
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->releasePlayer()V

    .line 827
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraController:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-virtual {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->release()V

    const/4 v0, 0x1

    .line 828
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cleanupStarted:Z

    .line 829
    invoke-direct {p0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->setState(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;)V

    .line 830
    const-string v0, "released"

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->logSummary(Ljava/lang/String;)V

    .line 831
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->shutdownExecutors()V

    return-void
.end method

.method public resumeRecording()Z
    .locals 5

    .line 652
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requireMainThread()V

    .line 653
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->PREVIEWING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-ne v0, v1, :cond_1

    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimEndMs:J

    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimStartMs:J

    sub-long/2addr v0, v2

    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->maximumDurationMs:J

    cmp-long v0, v0, v2

    if-ltz v0, :cond_0

    goto :goto_0

    .line 654
    :cond_0
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->resumeCount:I

    const/4 v1, 0x1

    add-int/2addr v0, v1

    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->resumeCount:I

    .line 655
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "resume requested: trim="

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-wide v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimStartMs:J

    invoke-virtual {v2, v3, v4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v3, ".."

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimEndMs:J

    invoke-virtual {v2, v3, v4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v3, ", sourceDurationMs="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordedDurationMs:J

    invoke-virtual {v2, v3, v4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 657
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->releasePlayer()V

    .line 658
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->RESUMING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->setState(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;)V

    .line 659
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->rebuildForResume()V

    return v1

    :cond_1
    :goto_0
    const/4 p0, 0x0

    return p0
.end method

.method public seekPreview(J)Z
    .locals 7

    .line 716
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requireMainThread()V

    .line 717
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->PREVIEWING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-ne v0, v1, :cond_1

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-nez v0, :cond_0

    goto :goto_0

    .line 718
    :cond_0
    iget-wide v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimStartMs:J

    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimEndMs:J

    const-wide/16 v5, 0x1

    sub-long/2addr v0, v5

    invoke-static {v3, v4, v0, v1}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v5

    move-wide v1, p1

    invoke-static/range {v1 .. v6}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->clamp(JJJ)J

    move-result-wide p1

    .line 719
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {v0, p1, p2}, Landroidx/media3/common/Player;->seekTo(J)V

    .line 720
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->listener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;

    iget-wide v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordedDurationMs:J

    invoke-interface {v0, p1, p2, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;->onPreviewProgress(JJ)V

    const/4 p0, 0x1

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x0

    return p0
.end method

.method public setCameraFacing(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)Z
    .locals 4

    .line 727
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requireMainThread()V

    .line 728
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->isTerminalState()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 p0, 0x0

    return p0

    .line 729
    :cond_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requestedFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    const/4 v1, 0x1

    if-ne v0, p1, :cond_1

    return v1

    .line 730
    :cond_1
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "camera facing requested: "

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requestedFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v3, " -> "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v3, ", state="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 732
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requestedFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    .line 733
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->RECORDING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-eq v0, v2, :cond_2

    sget-object v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->STARTING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-ne v0, v2, :cond_3

    .line 734
    :cond_2
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->disableCameraEffects()V

    .line 735
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraController:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-virtual {v0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->setCameraFacing(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)Z

    move-result p1

    iput-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraSwitching:Z

    .line 737
    :cond_3
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->notifyCapabilities()V

    .line 738
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->notifyState()V

    return v1
.end method

.method public setFlashEnabled(Z)Z
    .locals 3

    .line 754
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requireMainThread()V

    .line 755
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->RECORDING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    const/4 v2, 0x0

    if-ne v0, v1, :cond_2

    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraSwitching:Z

    if-nez v0, :cond_2

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->flashType:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;->NONE:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    if-ne v0, v1, :cond_0

    goto :goto_1

    .line 756
    :cond_0
    iput-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->flashEnabled:Z

    .line 757
    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;->SCREEN:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    if-ne v0, v1, :cond_1

    .line 758
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->setScreenFlashEnabled(Z)V

    .line 759
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraController:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-virtual {p1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->setTorchEnabled(Z)Z

    goto :goto_0

    .line 761
    :cond_1
    invoke-direct {p0, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->setScreenFlashEnabled(Z)V

    .line 762
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraController:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-virtual {v0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->setTorchEnabled(Z)Z

    .line 764
    :goto_0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->notifyState()V

    const/4 p0, 0x1

    return p0

    :cond_2
    :goto_1
    return v2
.end method

.method public setPreviewMuted(Z)Z
    .locals 2

    .line 707
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requireMainThread()V

    .line 708
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->PREVIEWING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-ne v0, v1, :cond_2

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-nez p0, :cond_0

    goto :goto_1

    :cond_0
    if-eqz p1, :cond_1

    const/4 p1, 0x0

    goto :goto_0

    :cond_1
    const/high16 p1, 0x3f800000    # 1.0f

    .line 709
    :goto_0
    invoke-interface {p0, p1}, Landroidx/media3/common/Player;->setVolume(F)V

    const/4 p0, 0x1

    return p0

    :cond_2
    :goto_1
    const/4 p0, 0x0

    return p0
.end method

.method public setTrimRange(JJ)Z
    .locals 16

    move-object/from16 v0, p0

    .line 666
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requireMainThread()V

    .line 667
    iget-object v1, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->PREVIEWING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    const/4 v3, 0x0

    if-eq v1, v2, :cond_0

    return v3

    :cond_0
    const-wide/16 v6, 0x0

    .line 668
    iget-wide v8, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordedDurationMs:J

    move-wide/from16 v4, p1

    invoke-static/range {v4 .. v9}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->clamp(JJJ)J

    move-result-wide v12

    .line 669
    iget-wide v14, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordedDurationMs:J

    move-wide/from16 v10, p3

    invoke-static/range {v10 .. v15}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->clamp(JJJ)J

    move-result-wide v1

    sub-long v4, v1, v12

    const-wide/16 v6, 0x320

    .line 670
    iget-wide v8, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordedDurationMs:J

    invoke-static {v6, v7, v8, v9}, Ljava/lang/Math;->min(JJ)J

    move-result-wide v6

    cmp-long v4, v4, v6

    if-gez v4, :cond_1

    return v3

    .line 673
    :cond_1
    iput-wide v12, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimStartMs:J

    .line 674
    iput-wide v1, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimEndMs:J

    .line 675
    iget-object v1, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->player:Landroidx/media3/exoplayer/ExoPlayer;

    if-eqz v1, :cond_2

    invoke-interface {v1, v12, v13}, Landroidx/media3/common/Player;->seekTo(J)V

    .line 676
    :cond_2
    iget-object v1, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->listener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;

    iget-wide v2, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->trimStartMs:J

    iget-wide v4, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->recordedDurationMs:J

    invoke-interface {v1, v2, v3, v4, v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;->onPreviewProgress(JJ)V

    .line 677
    invoke-direct {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->notifyState()V

    const/4 v0, 0x1

    return v0
.end method

.method public setZoom(F)Z
    .locals 2

    .line 745
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requireMainThread()V

    .line 746
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->RECORDING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-ne v0, v1, :cond_1

    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraSwitching:Z

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    const/high16 v1, 0x3f800000    # 1.0f

    .line 747
    invoke-static {p1, v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->clamp(FFF)F

    move-result p1

    iput p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->zoomProgress:F

    .line 748
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraController:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    invoke-virtual {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->setZoom(F)Z

    move-result p0

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x0

    return p0
.end method

.method public start()Z
    .locals 7

    .line 619
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requireMainThread()V

    .line 620
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->IDLE:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    const/4 v2, 0x0

    if-eq v0, v1, :cond_0

    return v2

    .line 622
    :cond_0
    :try_start_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    const-string v1, "start requested"

    invoke-virtual {v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    const/4 v0, 0x1

    .line 623
    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->createOutputGeneration(Z)V

    .line 624
    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->STARTING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    invoke-direct {p0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->setState(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;)V

    .line 625
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->cameraController:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->writer:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    iget-object v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->requestedFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    const-wide/16 v5, 0x0

    invoke-virtual {v1, v3, v5, v6, v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->startSegment(Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;JLorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return v0

    :catch_0
    move-exception v0

    .line 628
    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->asException(Ljava/lang/Exception;)Ljava/lang/Exception;

    move-result-object v0

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->fail(Ljava/lang/Exception;)V

    return v2
.end method
