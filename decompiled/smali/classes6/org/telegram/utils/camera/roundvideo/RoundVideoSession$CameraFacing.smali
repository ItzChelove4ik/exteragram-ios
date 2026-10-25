.class public final enum Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;
.super Ljava/lang/Enum;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "CameraFacing"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

.field public static final enum BACK:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

.field public static final enum FRONT:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;


# direct methods
.method private static synthetic $values()[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;
    .locals 2

    .line 64
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;->FRONT:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;->BACK:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    filled-new-array {v0, v1}, [Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    move-result-object v0

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 3

    .line 66
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    const-string v1, "FRONT"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;->FRONT:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    .line 68
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    const-string v1, "BACK"

    const/4 v2, 0x1

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;->BACK:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    .line 64
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;->$values()[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    move-result-object v0

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;->$VALUES:[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 64
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    return-void
.end method

.method public static valueOf(Ljava/lang/String;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;
    .locals 1

    .line 64
    const-class v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    return-object p0
.end method

.method public static values()[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;
    .locals 1

    .line 64
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;->$VALUES:[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    invoke-virtual {v0}, [Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    return-object v0
.end method
