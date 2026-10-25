.class public interface abstract Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x609
    name = "Listener"
.end annotation


# virtual methods
.method public abstract onCameraCapabilitiesChanged(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraCapabilities;)V
.end method

.method public abstract onCameraSwitchCompleted(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V
.end method

.method public abstract onCameraSwitchFirstFrame()V
.end method

.method public abstract onCameraSwitchStarted(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V
.end method

.method public abstract onCompleted(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Result;)V
.end method

.method public abstract onError(Ljava/lang/Exception;)V
.end method

.method public abstract onPreviewFirstFrame()V
.end method

.method public onPreviewPlaybackChanged(Z)V
    .locals 0

    return-void
.end method

.method public onPreviewProgress(JJ)V
    .locals 0

    return-void
.end method

.method public abstract onPreviewReady(JJJ)V
.end method

.method public abstract onStateChanged(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;)V
.end method
