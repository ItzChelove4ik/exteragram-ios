.class public final enum Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;
.super Ljava/lang/Enum;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "FlashType"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

.field public static final enum NONE:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

.field public static final enum SCREEN:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

.field public static final enum TORCH:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;


# direct methods
.method private static synthetic $values()[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;
    .locals 3

    .line 115
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;->NONE:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;->TORCH:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    sget-object v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;->SCREEN:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    filled-new-array {v0, v1, v2}, [Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    move-result-object v0

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 3

    .line 117
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    const-string v1, "NONE"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;->NONE:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    .line 119
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    const-string v1, "TORCH"

    const/4 v2, 0x1

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;->TORCH:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    .line 121
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    const-string v1, "SCREEN"

    const/4 v2, 0x2

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;->SCREEN:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    .line 115
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;->$values()[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    move-result-object v0

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;->$VALUES:[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 115
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    return-void
.end method

.method public static valueOf(Ljava/lang/String;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;
    .locals 1

    .line 115
    const-class v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    return-object p0
.end method

.method public static values()[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;
    .locals 1

    .line 115
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;->$VALUES:[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    invoke-virtual {v0}, [Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    return-object v0
.end method
