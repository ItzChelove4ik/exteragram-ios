.class abstract synthetic Lorg/telegram/utils/proxy/ProxySettings$1;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/proxy/ProxySettings;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1009
    name = null
.end annotation


# static fields
.field static final synthetic $SwitchMap$org$telegram$utils$proxy$ProxySettings$Type:[I


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .line 104
    invoke-static {}, Lorg/telegram/utils/proxy/ProxySettings$Type;->values()[Lorg/telegram/utils/proxy/ProxySettings$Type;

    move-result-object v0

    array-length v0, v0

    new-array v0, v0, [I

    sput-object v0, Lorg/telegram/utils/proxy/ProxySettings$1;->$SwitchMap$org$telegram$utils$proxy$ProxySettings$Type:[I

    :try_start_0
    sget-object v1, Lorg/telegram/utils/proxy/ProxySettings$Type;->MTPROTO:Lorg/telegram/utils/proxy/ProxySettings$Type;

    invoke-virtual {v1}, Ljava/lang/Enum;->ordinal()I

    move-result v1

    const/4 v2, 0x1

    aput v2, v0, v1
    :try_end_0
    .catch Ljava/lang/NoSuchFieldError; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    :try_start_1
    sget-object v0, Lorg/telegram/utils/proxy/ProxySettings$1;->$SwitchMap$org$telegram$utils$proxy$ProxySettings$Type:[I

    sget-object v1, Lorg/telegram/utils/proxy/ProxySettings$Type;->WEB:Lorg/telegram/utils/proxy/ProxySettings$Type;

    invoke-virtual {v1}, Ljava/lang/Enum;->ordinal()I

    move-result v1

    const/4 v2, 0x2

    aput v2, v0, v1
    :try_end_1
    .catch Ljava/lang/NoSuchFieldError; {:try_start_1 .. :try_end_1} :catch_1

    :catch_1
    :try_start_2
    sget-object v0, Lorg/telegram/utils/proxy/ProxySettings$1;->$SwitchMap$org$telegram$utils$proxy$ProxySettings$Type:[I

    sget-object v1, Lorg/telegram/utils/proxy/ProxySettings$Type;->SOCKS5:Lorg/telegram/utils/proxy/ProxySettings$Type;

    invoke-virtual {v1}, Ljava/lang/Enum;->ordinal()I

    move-result v1

    const/4 v2, 0x3

    aput v2, v0, v1
    :try_end_2
    .catch Ljava/lang/NoSuchFieldError; {:try_start_2 .. :try_end_2} :catch_2

    :catch_2
    return-void
.end method
