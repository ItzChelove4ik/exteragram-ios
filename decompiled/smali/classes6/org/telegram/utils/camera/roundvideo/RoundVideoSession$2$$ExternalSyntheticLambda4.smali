.class public final synthetic Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2$$ExternalSyntheticLambda4;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;

.field public final synthetic f$1:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;

.field public final synthetic f$2:Z


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;Z)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2$$ExternalSyntheticLambda4;->f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;

    iput-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2$$ExternalSyntheticLambda4;->f$1:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;

    iput-boolean p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2$$ExternalSyntheticLambda4;->f$2:Z

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 2

    .line 0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2$$ExternalSyntheticLambda4;->f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2$$ExternalSyntheticLambda4;->f$1:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;

    iget-boolean p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2$$ExternalSyntheticLambda4;->f$2:Z

    invoke-static {v0, v1, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;->$r8$lambda$iMiBbiM_nkZoK-GCXxlfYT5pidQ(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$2;Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;Z)V

    return-void
.end method
