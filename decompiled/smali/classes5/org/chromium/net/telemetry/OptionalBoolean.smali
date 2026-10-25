.class public final enum Lorg/chromium/net/telemetry/OptionalBoolean;
.super Ljava/lang/Enum;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lorg/chromium/net/telemetry/OptionalBoolean;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lorg/chromium/net/telemetry/OptionalBoolean;

.field public static final enum FALSE:Lorg/chromium/net/telemetry/OptionalBoolean;

.field public static final enum TRUE:Lorg/chromium/net/telemetry/OptionalBoolean;

.field public static final enum UNSET:Lorg/chromium/net/telemetry/OptionalBoolean;


# instance fields
.field private final mValue:I


# direct methods
.method private static synthetic $values()[Lorg/chromium/net/telemetry/OptionalBoolean;
    .locals 3

    .line 13
    sget-object v0, Lorg/chromium/net/telemetry/OptionalBoolean;->UNSET:Lorg/chromium/net/telemetry/OptionalBoolean;

    sget-object v1, Lorg/chromium/net/telemetry/OptionalBoolean;->TRUE:Lorg/chromium/net/telemetry/OptionalBoolean;

    sget-object v2, Lorg/chromium/net/telemetry/OptionalBoolean;->FALSE:Lorg/chromium/net/telemetry/OptionalBoolean;

    filled-new-array {v0, v1, v2}, [Lorg/chromium/net/telemetry/OptionalBoolean;

    move-result-object v0

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 3

    .line 14
    new-instance v0, Lorg/chromium/net/telemetry/OptionalBoolean;

    const-string v1, "UNSET"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2, v2}, Lorg/chromium/net/telemetry/OptionalBoolean;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lorg/chromium/net/telemetry/OptionalBoolean;->UNSET:Lorg/chromium/net/telemetry/OptionalBoolean;

    .line 17
    new-instance v0, Lorg/chromium/net/telemetry/OptionalBoolean;

    const-string v1, "TRUE"

    const/4 v2, 0x1

    invoke-direct {v0, v1, v2, v2}, Lorg/chromium/net/telemetry/OptionalBoolean;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lorg/chromium/net/telemetry/OptionalBoolean;->TRUE:Lorg/chromium/net/telemetry/OptionalBoolean;

    .line 20
    new-instance v0, Lorg/chromium/net/telemetry/OptionalBoolean;

    const-string v1, "FALSE"

    const/4 v2, 0x2

    invoke-direct {v0, v1, v2, v2}, Lorg/chromium/net/telemetry/OptionalBoolean;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lorg/chromium/net/telemetry/OptionalBoolean;->FALSE:Lorg/chromium/net/telemetry/OptionalBoolean;

    .line 13
    invoke-static {}, Lorg/chromium/net/telemetry/OptionalBoolean;->$values()[Lorg/chromium/net/telemetry/OptionalBoolean;

    move-result-object v0

    sput-object v0, Lorg/chromium/net/telemetry/OptionalBoolean;->$VALUES:[Lorg/chromium/net/telemetry/OptionalBoolean;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;II)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I)V"
        }
    .end annotation

    .line 26
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    .line 27
    iput p3, p0, Lorg/chromium/net/telemetry/OptionalBoolean;->mValue:I

    return-void
.end method

.method public static fromBoolean(Ljava/lang/Boolean;)Lorg/chromium/net/telemetry/OptionalBoolean;
    .locals 0

    if-nez p0, :cond_0

    .line 36
    sget-object p0, Lorg/chromium/net/telemetry/OptionalBoolean;->UNSET:Lorg/chromium/net/telemetry/OptionalBoolean;

    return-object p0

    .line 39
    :cond_0
    invoke-virtual {p0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p0

    if-eqz p0, :cond_1

    sget-object p0, Lorg/chromium/net/telemetry/OptionalBoolean;->TRUE:Lorg/chromium/net/telemetry/OptionalBoolean;

    return-object p0

    :cond_1
    sget-object p0, Lorg/chromium/net/telemetry/OptionalBoolean;->FALSE:Lorg/chromium/net/telemetry/OptionalBoolean;

    return-object p0
.end method

.method public static valueOf(Ljava/lang/String;)Lorg/chromium/net/telemetry/OptionalBoolean;
    .locals 1

    .line 13
    const-class v0, Lorg/chromium/net/telemetry/OptionalBoolean;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lorg/chromium/net/telemetry/OptionalBoolean;

    return-object p0
.end method

.method public static values()[Lorg/chromium/net/telemetry/OptionalBoolean;
    .locals 1

    .line 13
    sget-object v0, Lorg/chromium/net/telemetry/OptionalBoolean;->$VALUES:[Lorg/chromium/net/telemetry/OptionalBoolean;

    invoke-virtual {v0}, [Lorg/chromium/net/telemetry/OptionalBoolean;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lorg/chromium/net/telemetry/OptionalBoolean;

    return-object v0
.end method


# virtual methods
.method public getValue()I
    .locals 0

    .line 31
    iget p0, p0, Lorg/chromium/net/telemetry/OptionalBoolean;->mValue:I

    return p0
.end method
