.class public final synthetic Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda13;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$Listener;


# instance fields
.field public final synthetic f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

.field public final synthetic f$1:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda13;->f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    iput-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda13;->f$1:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;

    return-void
.end method


# virtual methods
.method public final onBytesAvailable(J)V
    .locals 1

    .line 0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda13;->f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda13;->f$1:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;

    invoke-static {v0, p0, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->$r8$lambda$wwoBa-FBoOXS-IzBqMsXt5hoPsc(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;J)V

    return-void
.end method
