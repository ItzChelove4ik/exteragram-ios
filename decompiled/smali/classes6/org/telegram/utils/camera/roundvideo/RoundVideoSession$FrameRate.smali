.class public final enum Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;
.super Ljava/lang/Enum;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "FrameRate"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

.field public static final enum FPS_30:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

.field public static final enum FPS_60:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;


# instance fields
.field private final value:I


# direct methods
.method private static synthetic $values()[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;
    .locals 2

    .line 72
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->FPS_30:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->FPS_60:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    filled-new-array {v0, v1}, [Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    move-result-object v0

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 4

    .line 74
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    const/4 v1, 0x0

    const/16 v2, 0x1e

    const-string v3, "FPS_30"

    invoke-direct {v0, v3, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->FPS_30:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    .line 76
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    const/4 v1, 0x1

    const/16 v2, 0x3c

    const-string v3, "FPS_60"

    invoke-direct {v0, v3, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->FPS_60:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    .line 72
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->$values()[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    move-result-object v0

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->$VALUES:[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;II)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I)V"
        }
    .end annotation

    .line 80
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    .line 81
    iput p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->value:I

    return-void
.end method

.method public static valueOf(Ljava/lang/String;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;
    .locals 1

    .line 72
    const-class v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    return-object p0
.end method

.method public static values()[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;
    .locals 1

    .line 72
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->$VALUES:[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    invoke-virtual {v0}, [Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    return-object v0
.end method


# virtual methods
.method public getValue()I
    .locals 0

    .line 86
    iget p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;->value:I

    return p0
.end method
