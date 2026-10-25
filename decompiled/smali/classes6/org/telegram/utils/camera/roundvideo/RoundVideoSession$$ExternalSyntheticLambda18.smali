.class public final synthetic Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda18;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

.field public final synthetic f$1:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;

.field public final synthetic f$2:J

.field public final synthetic f$3:J


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;JJ)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda18;->f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    iput-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda18;->f$1:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;

    iput-wide p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda18;->f$2:J

    iput-wide p5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda18;->f$3:J

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 6

    .line 0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda18;->f$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda18;->f$1:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;

    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda18;->f$2:J

    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$$ExternalSyntheticLambda18;->f$3:J

    invoke-static/range {v0 .. v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->$r8$lambda$OmyhblPyxezbuJ5LN-I2MSa3Gu8(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputGeneration;JJ)V

    return-void
.end method
