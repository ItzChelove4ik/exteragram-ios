.class public final Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Builder"
.end annotation


# instance fields
.field private cameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

.field private compositionEnabled:Z

.field private final context:Landroid/content/Context;

.field private frameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

.field private initialFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

.field private listener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;

.field private maximumDurationMs:J

.field private outputDirectory:Ljava/io/File;

.field private outputListener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputListener;

.field private outputResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

.field private final previewView:Landroid/view/TextureView;

.field private screenFlashController:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$ScreenFlashController;

.field private videoBitrate:I


# direct methods
.method public static bridge synthetic -$$Nest$fgetcameraResolution(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->cameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetcompositionEnabled(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->compositionEnabled:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetcontext(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)Landroid/content/Context;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->context:Landroid/content/Context;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetframeRate(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->frameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetinitialFacing(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->initialFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetlistener(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->listener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetmaximumDurationMs(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)J
    .locals 2

    .line 0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->maximumDurationMs:J

    return-wide v0
.end method

.method public static bridge synthetic -$$Nest$fgetoutputDirectory(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)Ljava/io/File;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->outputDirectory:Ljava/io/File;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetoutputListener(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputListener;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->outputListener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputListener;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetoutputResolution(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->outputResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetpreviewView(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)Landroid/view/TextureView;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->previewView:Landroid/view/TextureView;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetscreenFlashController(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$ScreenFlashController;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->screenFlashController:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$ScreenFlashController;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetvideoBitrate(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;)I
    .locals 0

    .line 0
    iget p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->videoBitrate:I

    return p0
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/view/TextureView;)V
    .locals 2

    .line 227
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const-wide/32 v0, 0xea60

    .line 220
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->maximumDurationMs:J

    const/4 v0, 0x1

    .line 221
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->compositionEnabled:Z

    .line 228
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->context:Landroid/content/Context;

    .line 229
    iput-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->previewView:Landroid/view/TextureView;

    return-void
.end method


# virtual methods
.method public build()Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;
    .locals 4

    .line 300
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->initialFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    if-eqz v0, :cond_7

    .line 301
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->outputResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    if-eqz v0, :cond_6

    .line 302
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->videoBitrate:I

    if-lez v0, :cond_5

    .line 303
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->cameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    if-eqz v0, :cond_4

    .line 304
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->frameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    if-eqz v0, :cond_3

    .line 305
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->maximumDurationMs:J

    const-wide/16 v2, 0x0

    cmp-long v0, v0, v2

    if-lez v0, :cond_2

    .line 306
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->listener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;

    if-eqz v0, :cond_1

    .line 307
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->outputListener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputListener;

    if-eqz v0, :cond_0

    .line 308
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession-IA;)V

    return-object v0

    .line 307
    :cond_0
    const-string p0, "Output listener is required"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    :goto_0
    const/4 p0, 0x0

    return-object p0

    .line 306
    :cond_1
    const-string p0, "Session listener is required"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    goto :goto_0

    .line 305
    :cond_2
    const-string p0, "Invalid duration limit"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    goto :goto_0

    .line 304
    :cond_3
    const-string p0, "Frame rate is required"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    goto :goto_0

    .line 303
    :cond_4
    const-string p0, "Camera resolution is required"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    goto :goto_0

    .line 302
    :cond_5
    const-string p0, "Video bitrate is required"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    goto :goto_0

    .line 301
    :cond_6
    const-string p0, "Output resolution is required"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    goto :goto_0

    .line 300
    :cond_7
    const-string p0, "Initial camera is required"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    goto :goto_0
.end method

.method public setCameraResolution(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;
    .locals 0

    .line 252
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->cameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    return-object p0
.end method

.method public setCompositionEnabled(Z)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;
    .locals 0

    .line 270
    iput-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->compositionEnabled:Z

    return-object p0
.end method

.method public setFrameRate(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;
    .locals 0

    .line 258
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->frameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    return-object p0
.end method

.method public setInitialFacing(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;
    .locals 0

    .line 234
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->initialFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    return-object p0
.end method

.method public setListener(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;
    .locals 0

    .line 282
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->listener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;

    return-object p0
.end method

.method public setOutputDirectory(Ljava/io/File;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;
    .locals 0

    .line 276
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->outputDirectory:Ljava/io/File;

    return-object p0
.end method

.method public setOutputListener(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputListener;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;
    .locals 0

    .line 288
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->outputListener:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputListener;

    return-object p0
.end method

.method public setOutputResolution(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;
    .locals 0

    .line 240
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->outputResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    return-object p0
.end method

.method public setScreenFlashController(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$ScreenFlashController;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;
    .locals 0

    .line 294
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->screenFlashController:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$ScreenFlashController;

    return-object p0
.end method

.method public setVideoBitrate(I)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;
    .locals 0

    .line 246
    iput p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Builder;->videoBitrate:I

    return-object p0
.end method
