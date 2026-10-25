.class public final synthetic Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

.field public final synthetic f$1:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda1;->f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    iput-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda1;->f$1:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda1;->f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$$ExternalSyntheticLambda1;->f$1:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;

    invoke-static {v0, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;->$r8$lambda$xNvqkK3mGMoZUtsNbXP3nFtsmRg(Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V

    return-void
.end method
