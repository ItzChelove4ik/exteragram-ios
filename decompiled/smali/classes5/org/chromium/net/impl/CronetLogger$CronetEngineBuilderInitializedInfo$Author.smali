.class public final enum Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;
.super Ljava/lang/Enum;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "Author"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;

.field public static final enum API:Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;

.field public static final enum IMPL:Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;


# direct methods
.method private static synthetic $values()[Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;
    .locals 2

    .line 65
    sget-object v0, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;->API:Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;

    sget-object v1, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;->IMPL:Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;

    filled-new-array {v0, v1}, [Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;

    move-result-object v0

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 3

    .line 66
    new-instance v0, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;

    const-string v1, "API"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;->API:Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;

    .line 67
    new-instance v0, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;

    const-string v1, "IMPL"

    const/4 v2, 0x1

    invoke-direct {v0, v1, v2}, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;->IMPL:Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;

    .line 65
    invoke-static {}, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;->$values()[Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;

    move-result-object v0

    sput-object v0, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;->$VALUES:[Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 65
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    return-void
.end method

.method public static valueOf(Ljava/lang/String;)Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;
    .locals 1

    .line 65
    const-class v0, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;

    return-object p0
.end method

.method public static values()[Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;
    .locals 1

    .line 65
    sget-object v0, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;->$VALUES:[Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;

    invoke-virtual {v0}, [Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;

    return-object v0
.end method
