.class public final synthetic Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda8;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

.field public final synthetic f$1:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

.field public final synthetic f$2:Z

.field public final synthetic f$3:Ljava/io/File;

.field public final synthetic f$4:Z

.field public final synthetic f$5:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

.field public final synthetic f$6:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;ZLjava/io/File;ZLorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda8;->f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    iput-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda8;->f$1:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    iput-boolean p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda8;->f$2:Z

    iput-object p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda8;->f$3:Ljava/io/File;

    iput-boolean p5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda8;->f$4:Z

    iput-object p6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda8;->f$5:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    iput-object p7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda8;->f$6:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 7

    .line 0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda8;->f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda8;->f$1:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    iget-boolean v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda8;->f$2:Z

    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda8;->f$3:Ljava/io/File;

    iget-boolean v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda8;->f$4:Z

    iget-object v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda8;->f$5:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    iget-object v6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda8;->f$6:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;

    invoke-static/range {v0 .. v6}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->$r8$lambda$FUST4X2EdqFdZmDiSwtm2IOY8YI(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;ZLjava/io/File;ZLorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;)V

    return-void
.end method
