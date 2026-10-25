.class public final synthetic Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda4;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

.field public final synthetic f$1:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

.field public final synthetic f$2:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

.field public final synthetic f$3:Landroid/os/Handler;


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Landroid/os/Handler;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda4;->f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

    iput-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda4;->f$1:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    iput-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda4;->f$2:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    iput-object p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda4;->f$3:Landroid/os/Handler;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 3

    .line 0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda4;->f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda4;->f$1:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda4;->f$2:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda4;->f$3:Landroid/os/Handler;

    invoke-static {v0, v1, v2, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->$r8$lambda$Is280eD-ff6QwTALnH_N7zZF4UQ(Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Landroid/os/Handler;)V

    return-void
.end method
