.class public final enum Lcom/exteragram/messenger/ai/data/Service$Kind;
.super Ljava/lang/Enum;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/ai/data/Service;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "Kind"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/exteragram/messenger/ai/data/Service$Kind;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/exteragram/messenger/ai/data/Service$Kind;

.field public static final enum ON_DEVICE:Lcom/exteragram/messenger/ai/data/Service$Kind;

.field public static final enum REST:Lcom/exteragram/messenger/ai/data/Service$Kind;


# direct methods
.method private static synthetic $values()[Lcom/exteragram/messenger/ai/data/Service$Kind;
    .locals 2

    .line 26
    sget-object v0, Lcom/exteragram/messenger/ai/data/Service$Kind;->REST:Lcom/exteragram/messenger/ai/data/Service$Kind;

    sget-object v1, Lcom/exteragram/messenger/ai/data/Service$Kind;->ON_DEVICE:Lcom/exteragram/messenger/ai/data/Service$Kind;

    filled-new-array {v0, v1}, [Lcom/exteragram/messenger/ai/data/Service$Kind;

    move-result-object v0

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 3

    .line 27
    new-instance v0, Lcom/exteragram/messenger/ai/data/Service$Kind;

    const-string v1, "REST"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/ai/data/Service$Kind;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/ai/data/Service$Kind;->REST:Lcom/exteragram/messenger/ai/data/Service$Kind;

    .line 28
    new-instance v0, Lcom/exteragram/messenger/ai/data/Service$Kind;

    const-string v1, "ON_DEVICE"

    const/4 v2, 0x1

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/ai/data/Service$Kind;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/exteragram/messenger/ai/data/Service$Kind;->ON_DEVICE:Lcom/exteragram/messenger/ai/data/Service$Kind;

    .line 26
    invoke-static {}, Lcom/exteragram/messenger/ai/data/Service$Kind;->$values()[Lcom/exteragram/messenger/ai/data/Service$Kind;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/ai/data/Service$Kind;->$VALUES:[Lcom/exteragram/messenger/ai/data/Service$Kind;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 26
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    return-void
.end method

.method public static valueOf(Ljava/lang/String;)Lcom/exteragram/messenger/ai/data/Service$Kind;
    .locals 1

    .line 26
    const-class v0, Lcom/exteragram/messenger/ai/data/Service$Kind;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/exteragram/messenger/ai/data/Service$Kind;

    return-object p0
.end method

.method public static values()[Lcom/exteragram/messenger/ai/data/Service$Kind;
    .locals 1

    .line 26
    sget-object v0, Lcom/exteragram/messenger/ai/data/Service$Kind;->$VALUES:[Lcom/exteragram/messenger/ai/data/Service$Kind;

    invoke-virtual {v0}, [Lcom/exteragram/messenger/ai/data/Service$Kind;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/exteragram/messenger/ai/data/Service$Kind;

    return-object v0
.end method
