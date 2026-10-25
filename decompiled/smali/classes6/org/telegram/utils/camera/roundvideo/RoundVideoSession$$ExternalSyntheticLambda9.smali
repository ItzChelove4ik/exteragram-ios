.class public final synthetic Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda9;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

.field public final synthetic f$1:Z

.field public final synthetic f$2:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

.field public final synthetic f$3:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;

.field public final synthetic f$4:Ljava/io/File;


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;ZLorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Ljava/io/File;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda9;->f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    iput-boolean p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda9;->f$1:Z

    iput-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda9;->f$2:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    iput-object p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda9;->f$3:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;

    iput-object p5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda9;->f$4:Ljava/io/File;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 4

    .line 0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda9;->f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda9;->f$1:Z

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda9;->f$2:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda9;->f$3:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda9;->f$4:Ljava/io/File;

    invoke-static {v0, v1, v2, v3, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->$r8$lambda$xCjTDFhOAvgAgt__DxKVwDx15As(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;ZLorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;Ljava/io/File;)V

    return-void
.end method
