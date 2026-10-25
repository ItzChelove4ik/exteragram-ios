.class final enum Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;
.super Ljava/lang/Enum;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/chromium/net/impl/CronetEngineBuilderImpl;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "HttpCacheMode"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

.field public static final enum DISABLED:Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

.field public static final enum DISK:Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

.field public static final enum DISK_NO_HTTP:Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

.field public static final enum MEMORY:Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;


# instance fields
.field private final mContentCacheEnabled:Z

.field private final mType:I


# direct methods
.method private static synthetic $values()[Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;
    .locals 4

    .line 73
    sget-object v0, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;->DISABLED:Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

    sget-object v1, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;->DISK:Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

    sget-object v2, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;->DISK_NO_HTTP:Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

    sget-object v3, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;->MEMORY:Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

    filled-new-array {v0, v1, v2, v3}, [Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

    move-result-object v0

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 5

    .line 75
    new-instance v0, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

    const-string v1, "DISABLED"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2, v2, v2}, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;-><init>(Ljava/lang/String;IIZ)V

    sput-object v0, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;->DISABLED:Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

    .line 76
    new-instance v0, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

    const-string v1, "DISK"

    const/4 v3, 0x1

    invoke-direct {v0, v1, v3, v3, v3}, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;-><init>(Ljava/lang/String;IIZ)V

    sput-object v0, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;->DISK:Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

    .line 77
    new-instance v0, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

    const-string v1, "DISK_NO_HTTP"

    const/4 v4, 0x2

    invoke-direct {v0, v1, v4, v3, v2}, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;-><init>(Ljava/lang/String;IIZ)V

    sput-object v0, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;->DISK_NO_HTTP:Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

    .line 78
    new-instance v0, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

    const-string v1, "MEMORY"

    const/4 v2, 0x3

    invoke-direct {v0, v1, v2, v4, v3}, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;-><init>(Ljava/lang/String;IIZ)V

    sput-object v0, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;->MEMORY:Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

    .line 73
    invoke-static {}, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;->$values()[Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

    move-result-object v0

    sput-object v0, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;->$VALUES:[Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;IIZ)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(IZ)V"
        }
    .end annotation

    .line 83
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    .line 84
    iput-boolean p4, p0, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;->mContentCacheEnabled:Z

    .line 85
    iput p3, p0, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;->mType:I

    return-void
.end method

.method public static fromPublicBuilderCacheMode(I)Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;
    .locals 1

    if-eqz p0, :cond_3

    const/4 v0, 0x1

    if-eq p0, v0, :cond_2

    const/4 v0, 0x2

    if-eq p0, v0, :cond_1

    const/4 v0, 0x3

    if-ne p0, v0, :cond_0

    .line 120
    sget-object p0, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;->DISK:Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

    return-object p0

    .line 124
    :cond_0
    const-string p0, "Unknown public builder cache mode"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    .line 118
    :cond_1
    sget-object p0, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;->DISK_NO_HTTP:Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

    return-object p0

    .line 122
    :cond_2
    sget-object p0, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;->MEMORY:Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

    return-object p0

    .line 116
    :cond_3
    sget-object p0, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;->DISABLED:Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

    return-object p0
.end method

.method public static valueOf(Ljava/lang/String;)Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;
    .locals 1

    .line 73
    const-class v0, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

    return-object p0
.end method

.method public static values()[Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;
    .locals 1

    .line 73
    sget-object v0, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;->$VALUES:[Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

    invoke-virtual {v0}, [Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;

    return-object v0
.end method


# virtual methods
.method public getType()I
    .locals 0

    .line 89
    iget p0, p0, Lorg/chromium/net/impl/CronetEngineBuilderImpl$HttpCacheMode;->mType:I

    return p0
.end method

.method public toPublicBuilderCacheMode()I
    .locals 3

    .line 98
    invoke-virtual {p0}, Ljava/lang/Enum;->ordinal()I

    move-result p0

    if-eqz p0, :cond_3

    const/4 v0, 0x3

    const/4 v1, 0x1

    if-eq p0, v1, :cond_2

    const/4 v2, 0x2

    if-eq p0, v2, :cond_1

    if-ne p0, v0, :cond_0

    return v1

    .line 108
    :cond_0
    const-string p0, "Unknown internal builder cache mode"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return p0

    :cond_1
    return v2

    :cond_2
    return v0

    :cond_3
    const/4 p0, 0x0

    return p0
.end method
