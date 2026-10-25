.class interface abstract Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$Callback;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x609
    name = "Callback"
.end annotation


# virtual methods
.method public abstract onCameraSwitchFirstFrame()V
.end method

.method public abstract onCameraSwitchStarted(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V
.end method

.method public abstract onConfigured(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;Z)V
.end method

.method public abstract onError(Ljava/lang/Exception;)V
.end method

.method public abstract onRecordingStarted()V
.end method

.method public abstract onRecordingStopped()V
.end method
