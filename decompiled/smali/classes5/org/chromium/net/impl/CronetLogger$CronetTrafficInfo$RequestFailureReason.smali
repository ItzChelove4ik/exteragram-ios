.class public final enum Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;
.super Ljava/lang/Enum;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "RequestFailureReason"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;

.field public static final enum NETWORK:Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;

.field public static final enum OTHER:Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;

.field public static final enum UNKNOWN:Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;


# direct methods
.method private static synthetic $values()[Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;
    .locals 3

    .line 201
    sget-object v0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;->UNKNOWN:Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;

    sget-object v1, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;->NETWORK:Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;

    sget-object v2, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;->OTHER:Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;

    filled-new-array {v0, v1, v2}, [Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;

    move-result-object v0

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 3

    .line 202
    new-instance v0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;

    const-string v1, "UNKNOWN"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;->UNKNOWN:Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;

    .line 203
    new-instance v0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;

    const-string v1, "NETWORK"

    const/4 v2, 0x1

    invoke-direct {v0, v1, v2}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;->NETWORK:Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;

    .line 204
    new-instance v0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;

    const-string v1, "OTHER"

    const/4 v2, 0x2

    invoke-direct {v0, v1, v2}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;->OTHER:Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;

    .line 201
    invoke-static {}, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;->$values()[Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;

    move-result-object v0

    sput-object v0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;->$VALUES:[Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 201
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    return-void
.end method

.method public static valueOf(Ljava/lang/String;)Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;
    .locals 1

    .line 201
    const-class v0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;

    return-object p0
.end method

.method public static values()[Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;
    .locals 1

    .line 201
    sget-object v0, Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;->$VALUES:[Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;

    invoke-virtual {v0}, [Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo$RequestFailureReason;

    return-object v0
.end method
