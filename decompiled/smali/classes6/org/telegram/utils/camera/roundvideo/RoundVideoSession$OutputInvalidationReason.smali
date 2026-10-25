.class public final enum Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;
.super Ljava/lang/Enum;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "OutputInvalidationReason"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

.field public static final enum AUDIO_REMOVED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

.field public static final enum CANCELLED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

.field public static final enum ERROR:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

.field public static final enum TRIMMED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;


# direct methods
.method private static synthetic $values()[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;
    .locals 4

    .line 125
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;->TRIMMED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;->AUDIO_REMOVED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    sget-object v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;->CANCELLED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    sget-object v3, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;->ERROR:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    filled-new-array {v0, v1, v2, v3}, [Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    move-result-object v0

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 3

    .line 127
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    const-string v1, "TRIMMED"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;->TRIMMED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    .line 129
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    const-string v1, "AUDIO_REMOVED"

    const/4 v2, 0x1

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;->AUDIO_REMOVED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    .line 131
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    const-string v1, "CANCELLED"

    const/4 v2, 0x2

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;->CANCELLED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    .line 133
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    const-string v1, "ERROR"

    const/4 v2, 0x3

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;->ERROR:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    .line 125
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;->$values()[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    move-result-object v0

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;->$VALUES:[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 125
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    return-void
.end method

.method public static valueOf(Ljava/lang/String;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;
    .locals 1

    .line 125
    const-class v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    return-object p0
.end method

.method public static values()[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;
    .locals 1

    .line 125
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;->$VALUES:[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    invoke-virtual {v0}, [Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputInvalidationReason;

    return-object v0
.end method
