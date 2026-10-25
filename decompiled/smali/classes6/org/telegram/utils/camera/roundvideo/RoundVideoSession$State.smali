.class public final enum Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;
.super Ljava/lang/Enum;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "State"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

.field public static final enum COMPLETED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

.field public static final enum ERROR:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

.field public static final enum FINISHING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

.field public static final enum IDLE:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

.field public static final enum PAUSING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

.field public static final enum PREVIEWING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

.field public static final enum RECORDING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

.field public static final enum RELEASED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

.field public static final enum RESUMING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

.field public static final enum STARTING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;


# direct methods
.method private static synthetic $values()[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;
    .locals 10

    .line 91
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->IDLE:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->STARTING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->RECORDING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v3, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->PAUSING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v4, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->PREVIEWING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v5, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->RESUMING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v6, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->FINISHING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v7, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->COMPLETED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v8, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->ERROR:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v9, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->RELEASED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    filled-new-array/range {v0 .. v9}, [Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    move-result-object v0

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 3

    .line 93
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    const-string v1, "IDLE"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->IDLE:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    .line 95
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    const-string v1, "STARTING"

    const/4 v2, 0x1

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->STARTING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    .line 97
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    const-string v1, "RECORDING"

    const/4 v2, 0x2

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->RECORDING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    .line 99
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    const-string v1, "PAUSING"

    const/4 v2, 0x3

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->PAUSING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    .line 101
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    const-string v1, "PREVIEWING"

    const/4 v2, 0x4

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->PREVIEWING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    .line 103
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    const-string v1, "RESUMING"

    const/4 v2, 0x5

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->RESUMING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    .line 105
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    const-string v1, "FINISHING"

    const/4 v2, 0x6

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->FINISHING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    .line 107
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    const-string v1, "COMPLETED"

    const/4 v2, 0x7

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->COMPLETED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    .line 109
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    const-string v1, "ERROR"

    const/16 v2, 0x8

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->ERROR:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    .line 111
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    const-string v1, "RELEASED"

    const/16 v2, 0x9

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->RELEASED:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    .line 91
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->$values()[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    move-result-object v0

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->$VALUES:[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 91
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    return-void
.end method

.method public static valueOf(Ljava/lang/String;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;
    .locals 1

    .line 91
    const-class v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    return-object p0
.end method

.method public static values()[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;
    .locals 1

    .line 91
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->$VALUES:[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    invoke-virtual {v0}, [Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    return-object v0
.end method
