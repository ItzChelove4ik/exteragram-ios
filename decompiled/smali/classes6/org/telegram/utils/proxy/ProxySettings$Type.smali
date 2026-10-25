.class public final enum Lorg/telegram/utils/proxy/ProxySettings$Type;
.super Ljava/lang/Enum;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/proxy/ProxySettings;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "Type"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lorg/telegram/utils/proxy/ProxySettings$Type;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lorg/telegram/utils/proxy/ProxySettings$Type;

.field public static final enum MTPROTO:Lorg/telegram/utils/proxy/ProxySettings$Type;

.field public static final enum SOCKS5:Lorg/telegram/utils/proxy/ProxySettings$Type;

.field public static final enum WEB:Lorg/telegram/utils/proxy/ProxySettings$Type;


# direct methods
.method private static synthetic $values()[Lorg/telegram/utils/proxy/ProxySettings$Type;
    .locals 3

    .line 27
    sget-object v0, Lorg/telegram/utils/proxy/ProxySettings$Type;->SOCKS5:Lorg/telegram/utils/proxy/ProxySettings$Type;

    sget-object v1, Lorg/telegram/utils/proxy/ProxySettings$Type;->MTPROTO:Lorg/telegram/utils/proxy/ProxySettings$Type;

    sget-object v2, Lorg/telegram/utils/proxy/ProxySettings$Type;->WEB:Lorg/telegram/utils/proxy/ProxySettings$Type;

    filled-new-array {v0, v1, v2}, [Lorg/telegram/utils/proxy/ProxySettings$Type;

    move-result-object v0

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 3

    .line 28
    new-instance v0, Lorg/telegram/utils/proxy/ProxySettings$Type;

    const-string v1, "SOCKS5"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/proxy/ProxySettings$Type;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/telegram/utils/proxy/ProxySettings$Type;->SOCKS5:Lorg/telegram/utils/proxy/ProxySettings$Type;

    .line 29
    new-instance v0, Lorg/telegram/utils/proxy/ProxySettings$Type;

    const-string v1, "MTPROTO"

    const/4 v2, 0x1

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/proxy/ProxySettings$Type;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/telegram/utils/proxy/ProxySettings$Type;->MTPROTO:Lorg/telegram/utils/proxy/ProxySettings$Type;

    .line 30
    new-instance v0, Lorg/telegram/utils/proxy/ProxySettings$Type;

    const-string v1, "WEB"

    const/4 v2, 0x2

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/proxy/ProxySettings$Type;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lorg/telegram/utils/proxy/ProxySettings$Type;->WEB:Lorg/telegram/utils/proxy/ProxySettings$Type;

    .line 27
    invoke-static {}, Lorg/telegram/utils/proxy/ProxySettings$Type;->$values()[Lorg/telegram/utils/proxy/ProxySettings$Type;

    move-result-object v0

    sput-object v0, Lorg/telegram/utils/proxy/ProxySettings$Type;->$VALUES:[Lorg/telegram/utils/proxy/ProxySettings$Type;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 27
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    return-void
.end method

.method public static valueOf(Ljava/lang/String;)Lorg/telegram/utils/proxy/ProxySettings$Type;
    .locals 1

    .line 27
    const-class v0, Lorg/telegram/utils/proxy/ProxySettings$Type;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lorg/telegram/utils/proxy/ProxySettings$Type;

    return-object p0
.end method

.method public static values()[Lorg/telegram/utils/proxy/ProxySettings$Type;
    .locals 1

    .line 27
    sget-object v0, Lorg/telegram/utils/proxy/ProxySettings$Type;->$VALUES:[Lorg/telegram/utils/proxy/ProxySettings$Type;

    invoke-virtual {v0}, [Lorg/telegram/utils/proxy/ProxySettings$Type;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lorg/telegram/utils/proxy/ProxySettings$Type;

    return-object v0
.end method
