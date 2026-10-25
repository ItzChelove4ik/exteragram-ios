.class public final enum Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;
.super Ljava/lang/Enum;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "CameraResolution"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

.field public static final enum HIGH:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

.field public static final enum LOW:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

.field public static final enum MEDIUM:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;


# direct methods
.method private static synthetic $values()[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;
    .locals 3

    .line 54
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;->HIGH:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;->MEDIUM:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    sget-object v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;->LOW:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    filled-new-array {v0, v1, v2}, [Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    move-result-object v0

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 3

    .line 56
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    const-string v1, "HIGH"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;->HIGH:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    .line 58
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    const-string v1, "MEDIUM"

    const/4 v2, 0x1

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;->MEDIUM:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    .line 60
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    const-string v1, "LOW"

    const/4 v2, 0x2

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;->LOW:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    .line 54
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;->$values()[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    move-result-object v0

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;->$VALUES:[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 54
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    return-void
.end method

.method public static valueOf(Ljava/lang/String;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;
    .locals 1

    .line 54
    const-class v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    return-object p0
.end method

.method public static values()[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;
    .locals 1

    .line 54
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;->$VALUES:[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    invoke-virtual {v0}, [Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    return-object v0
.end method
