.class public final enum Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;
.super Ljava/lang/Enum;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "OutputResolution"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

.field public static final enum P360:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

.field public static final enum P480:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;


# instance fields
.field private final size:I


# direct methods
.method private static synthetic $values()[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;
    .locals 2

    .line 35
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;->P480:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;->P360:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    filled-new-array {v0, v1}, [Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    move-result-object v0

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 4

    .line 37
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    const/4 v1, 0x0

    const/16 v2, 0x1e0

    const-string v3, "P480"

    invoke-direct {v0, v3, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;->P480:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    .line 39
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    const/4 v1, 0x1

    const/16 v2, 0x168

    const-string v3, "P360"

    invoke-direct {v0, v3, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;->P360:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    .line 35
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;->$values()[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    move-result-object v0

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;->$VALUES:[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;II)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I)V"
        }
    .end annotation

    .line 43
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    .line 44
    iput p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;->size:I

    return-void
.end method

.method public static valueOf(Ljava/lang/String;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;
    .locals 1

    .line 35
    const-class v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    return-object p0
.end method

.method public static values()[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;
    .locals 1

    .line 35
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;->$VALUES:[Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    invoke-virtual {v0}, [Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;

    return-object v0
.end method


# virtual methods
.method public getSize()I
    .locals 0

    .line 49
    iget p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$OutputResolution;->size:I

    return p0
.end method
