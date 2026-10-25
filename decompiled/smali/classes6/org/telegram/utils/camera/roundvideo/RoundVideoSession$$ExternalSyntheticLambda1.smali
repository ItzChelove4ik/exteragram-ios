.class public final synthetic Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

.field public final synthetic f$1:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

.field public final synthetic f$2:Z

.field public final synthetic f$3:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;ZLorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda1;->f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    iput-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda1;->f$1:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    iput-boolean p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda1;->f$2:Z

    iput-object p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda1;->f$3:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 3

    .line 0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda1;->f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda1;->f$1:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    iget-boolean v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda1;->f$2:Z

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda1;->f$3:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;

    invoke-static {v0, v1, v2, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->$r8$lambda$Pw2yy79EWxz3r9JycAYsvfaXvyI(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;ZLorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;)V

    return-void
.end method
