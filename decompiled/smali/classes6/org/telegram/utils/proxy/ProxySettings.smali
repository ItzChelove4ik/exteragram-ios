.class public final Lorg/telegram/utils/proxy/ProxySettings;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/utils/proxy/ProxySettings$Builder;,
        Lorg/telegram/utils/proxy/ProxySettings$Type;
    }
.end annotation


# static fields
.field public static final EMPTY:Lorg/telegram/utils/proxy/ProxySettings;

.field private static final WEB_PROXY_MARKED_SECRET_PATTERN:Ljava/util/regex/Pattern;

.field private static final WEB_PROXY_PATH_PATTERN:Ljava/util/regex/Pattern;


# instance fields
.field private final address:Ljava/lang/String;

.field private final password:Ljava/lang/String;

.field private final port:I

.field private final secret:Ljava/lang/String;

.field private final type:Lorg/telegram/utils/proxy/ProxySettings$Type;

.field private final user:Ljava/lang/String;


# direct methods
.method public static bridge synthetic -$$Nest$smdecodeMarkedWebProxySecret(Ljava/lang/String;)Ljava/lang/String;
    .locals 0

    .line 0
    invoke-static {p0}, Lorg/telegram/utils/proxy/ProxySettings;->decodeMarkedWebProxySecret(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$smhasWebProxyPath(Ljava/lang/String;)Z
    .locals 0

    .line 0
    invoke-static {p0}, Lorg/telegram/utils/proxy/ProxySettings;->hasWebProxyPath(Ljava/lang/String;)Z

    move-result p0

    return p0
.end method

.method public static bridge synthetic -$$Nest$smnormalizeWebAddress(Ljava/lang/String;)Ljava/lang/String;
    .locals 0

    .line 0
    invoke-static {p0}, Lorg/telegram/utils/proxy/ProxySettings;->normalizeWebAddress(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method static constructor <clinit>()V
    .locals 1

    .line 22
    const-string v0, "[A-Za-z0-9][A-Za-z0-9_-]*(/[A-Za-z0-9][A-Za-z0-9_-]*)*"

    invoke-static {v0}, Ljava/util/regex/Pattern;->compile(Ljava/lang/String;)Ljava/util/regex/Pattern;

    move-result-object v0

    sput-object v0, Lorg/telegram/utils/proxy/ProxySettings;->WEB_PROXY_PATH_PATTERN:Ljava/util/regex/Pattern;

    .line 24
    const-string v0, "[A-Za-z0-9_-]+"

    invoke-static {v0}, Ljava/util/regex/Pattern;->compile(Ljava/lang/String;)Ljava/util/regex/Pattern;

    move-result-object v0

    sput-object v0, Lorg/telegram/utils/proxy/ProxySettings;->WEB_PROXY_MARKED_SECRET_PATTERN:Ljava/util/regex/Pattern;

    .line 26
    invoke-static {}, Lorg/telegram/utils/proxy/ProxySettings;->builder()Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->build()Lorg/telegram/utils/proxy/ProxySettings;

    move-result-object v0

    sput-object v0, Lorg/telegram/utils/proxy/ProxySettings;->EMPTY:Lorg/telegram/utils/proxy/ProxySettings;

    return-void
.end method

.method private constructor <init>(Lorg/telegram/utils/proxy/ProxySettings$Builder;)V
    .locals 3

    .line 40
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 41
    invoke-static {p1}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->-$$Nest$fgettype(Lorg/telegram/utils/proxy/ProxySettings$Builder;)Lorg/telegram/utils/proxy/ProxySettings$Type;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/utils/proxy/ProxySettings;->type:Lorg/telegram/utils/proxy/ProxySettings$Type;

    .line 42
    invoke-static {p1}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->-$$Nest$fgetaddress(Lorg/telegram/utils/proxy/ProxySettings$Builder;)Ljava/lang/String;

    move-result-object v1

    iput-object v1, p0, Lorg/telegram/utils/proxy/ProxySettings;->address:Ljava/lang/String;

    .line 44
    sget-object v1, Lorg/telegram/utils/proxy/ProxySettings$Type;->WEB:Lorg/telegram/utils/proxy/ProxySettings$Type;

    const-string v2, ""

    if-ne v0, v1, :cond_0

    .line 45
    invoke-static {p1}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->-$$Nest$fgetsecret(Lorg/telegram/utils/proxy/ProxySettings$Builder;)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lorg/telegram/utils/proxy/ProxySettings;->secret:Ljava/lang/String;

    const/4 p1, 0x0

    .line 46
    iput p1, p0, Lorg/telegram/utils/proxy/ProxySettings;->port:I

    .line 47
    iput-object v2, p0, Lorg/telegram/utils/proxy/ProxySettings;->user:Ljava/lang/String;

    .line 48
    iput-object v2, p0, Lorg/telegram/utils/proxy/ProxySettings;->password:Ljava/lang/String;

    return-void

    .line 49
    :cond_0
    sget-object v1, Lorg/telegram/utils/proxy/ProxySettings$Type;->MTPROTO:Lorg/telegram/utils/proxy/ProxySettings$Type;

    if-ne v0, v1, :cond_1

    .line 50
    invoke-static {p1}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->-$$Nest$fgetsecret(Lorg/telegram/utils/proxy/ProxySettings$Builder;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/utils/proxy/ProxySettings;->secret:Ljava/lang/String;

    .line 51
    invoke-static {p1}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->-$$Nest$fgetport(Lorg/telegram/utils/proxy/ProxySettings$Builder;)I

    move-result p1

    iput p1, p0, Lorg/telegram/utils/proxy/ProxySettings;->port:I

    .line 52
    iput-object v2, p0, Lorg/telegram/utils/proxy/ProxySettings;->user:Ljava/lang/String;

    .line 53
    iput-object v2, p0, Lorg/telegram/utils/proxy/ProxySettings;->password:Ljava/lang/String;

    return-void

    .line 54
    :cond_1
    sget-object v1, Lorg/telegram/utils/proxy/ProxySettings$Type;->SOCKS5:Lorg/telegram/utils/proxy/ProxySettings$Type;

    if-ne v0, v1, :cond_2

    .line 55
    iput-object v2, p0, Lorg/telegram/utils/proxy/ProxySettings;->secret:Ljava/lang/String;

    .line 56
    invoke-static {p1}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->-$$Nest$fgetport(Lorg/telegram/utils/proxy/ProxySettings$Builder;)I

    move-result v0

    iput v0, p0, Lorg/telegram/utils/proxy/ProxySettings;->port:I

    .line 57
    invoke-static {p1}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->-$$Nest$fgetuser(Lorg/telegram/utils/proxy/ProxySettings$Builder;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/utils/proxy/ProxySettings;->user:Ljava/lang/String;

    .line 58
    invoke-static {p1}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->-$$Nest$fgetpassword(Lorg/telegram/utils/proxy/ProxySettings$Builder;)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lorg/telegram/utils/proxy/ProxySettings;->password:Ljava/lang/String;

    return-void

    .line 60
    :cond_2
    invoke-static {}, Lokio/Segment$$ExternalSyntheticBUOutline0;->m()V

    const/4 p0, 0x0

    throw p0
.end method

.method public synthetic constructor <init>(Lorg/telegram/utils/proxy/ProxySettings$Builder;Lorg/telegram/utils/proxy/ProxySettings-IA;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/proxy/ProxySettings;-><init>(Lorg/telegram/utils/proxy/ProxySettings$Builder;)V

    return-void
.end method

.method public static builder()Lorg/telegram/utils/proxy/ProxySettings$Builder;
    .locals 2

    .line 162
    new-instance v0, Lorg/telegram/utils/proxy/ProxySettings$Builder;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lorg/telegram/utils/proxy/ProxySettings$Builder;-><init>(Lorg/telegram/utils/proxy/ProxySettings-IA;)V

    return-object v0
.end method

.method private static bytesToHex([BI)Ljava/lang/String;
    .locals 6

    .line 507
    array-length v0, p0

    sub-int/2addr v0, p1

    mul-int/lit8 v0, v0, 0x2

    new-array v0, v0, [C

    .line 508
    const-string v1, "0123456789abcdef"

    invoke-virtual {v1}, Ljava/lang/String;->toCharArray()[C

    move-result-object v1

    move v2, p1

    .line 509
    :goto_0
    array-length v3, p0

    if-ge v2, v3, :cond_0

    .line 510
    aget-byte v3, p0, v2

    and-int/lit16 v4, v3, 0xff

    sub-int v5, v2, p1

    mul-int/lit8 v5, v5, 0x2

    ushr-int/lit8 v4, v4, 0x4

    .line 512
    aget-char v4, v1, v4

    aput-char v4, v0, v5

    add-int/lit8 v5, v5, 0x1

    and-int/lit8 v3, v3, 0xf

    .line 513
    aget-char v3, v1, v3

    aput-char v3, v0, v5

    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 515
    :cond_0
    new-instance p0, Ljava/lang/String;

    invoke-direct {p0, v0}, Ljava/lang/String;-><init>([C)V

    return-object p0
.end method

.method private static decodeMarkedWebProxySecret(Ljava/lang/String;)Ljava/lang/String;
    .locals 5

    .line 460
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_3

    sget-object v0, Lorg/telegram/utils/proxy/ProxySettings;->WEB_PROXY_MARKED_SECRET_PATTERN:Ljava/util/regex/Pattern;

    .line 461
    invoke-virtual {v0, p0}, Ljava/util/regex/Pattern;->matcher(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/regex/Matcher;->matches()Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    const/16 v0, 0xb

    .line 466
    :try_start_0
    invoke-static {p0, v0}, Landroid/util/Base64;->decode(Ljava/lang/String;I)[B

    move-result-object v2

    .line 467
    array-length v3, v2

    const/16 v4, 0x11

    if-lt v3, v4, :cond_3

    const/4 v3, 0x0

    aget-byte v3, v2, v3

    and-int/lit16 v3, v3, 0xff

    const/16 v4, 0x70

    if-eq v3, v4, :cond_1

    goto :goto_0

    :cond_1
    const/4 v3, 0x1

    .line 471
    invoke-static {v2, v3}, Lorg/telegram/utils/proxy/ProxySettings;->bytesToHex([BI)Ljava/lang/String;

    move-result-object v3

    .line 472
    invoke-static {v3}, Lorg/telegram/utils/proxy/ProxySettings;->isValidWebProxySecret(Ljava/lang/String;)Z

    move-result v4

    if-nez v4, :cond_2

    return-object v1

    .line 477
    :cond_2
    invoke-static {v2, v0}, Landroid/util/Base64;->encodeToString([BI)Ljava/lang/String;

    move-result-object v0

    .line 479
    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0
    :try_end_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0

    if-eqz p0, :cond_3

    return-object v3

    :catch_0
    :cond_3
    :goto_0
    return-object v1
.end method

.method private static encodeMarkedWebProxySecret(Ljava/lang/String;)Ljava/lang/String;
    .locals 4

    .line 486
    invoke-static {p0}, Lorg/telegram/utils/proxy/ProxySettings;->isValidWebProxySecret(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_0

    return-object p0

    .line 490
    :cond_0
    invoke-static {p0}, Lorg/telegram/utils/proxy/ProxySettings;->hexToBytes(Ljava/lang/String;)[B

    move-result-object p0

    .line 491
    array-length v0, p0

    const/4 v1, 0x1

    add-int/2addr v0, v1

    new-array v0, v0, [B

    const/16 v2, 0x70

    const/4 v3, 0x0

    .line 492
    aput-byte v2, v0, v3

    .line 493
    array-length v2, p0

    invoke-static {p0, v3, v0, v1, v2}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    const/16 p0, 0xb

    .line 494
    invoke-static {v0, p0}, Landroid/util/Base64;->encodeToString([BI)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static fromSharedPreferences(Landroid/content/SharedPreferences;)Lorg/telegram/utils/proxy/ProxySettings;
    .locals 7

    .line 166
    const-string v0, "proxy_ip"

    const-string v1, ""

    invoke-interface {p0, v0, v1}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 167
    const-string v2, "proxy_user"

    invoke-interface {p0, v2, v1}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 168
    const-string v3, "proxy_pass"

    invoke-interface {p0, v3, v1}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    .line 169
    const-string v4, "proxy_secret"

    invoke-interface {p0, v4, v1}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 170
    const-string v4, "proxy_port"

    const/16 v5, 0x438

    invoke-interface {p0, v4, v5}, Landroid/content/SharedPreferences;->getInt(Ljava/lang/String;I)I

    move-result v4

    .line 171
    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v5

    if-eqz v5, :cond_0

    .line 172
    sget-object v5, Lorg/telegram/utils/proxy/ProxySettings$Type;->SOCKS5:Lorg/telegram/utils/proxy/ProxySettings$Type;

    goto :goto_0

    .line 173
    :cond_0
    sget-object v5, Lorg/telegram/utils/proxy/ProxySettings$Type;->MTPROTO:Lorg/telegram/utils/proxy/ProxySettings$Type;

    .line 171
    :goto_0
    invoke-static {v5}, Lorg/telegram/utils/proxy/ProxySettings;->typeToInt(Lorg/telegram/utils/proxy/ProxySettings$Type;)I

    move-result v5

    const-string v6, "proxy_type"

    invoke-interface {p0, v6, v5}, Landroid/content/SharedPreferences;->getInt(Ljava/lang/String;I)I

    move-result p0

    invoke-static {p0}, Lorg/telegram/utils/proxy/ProxySettings;->intToType(I)Lorg/telegram/utils/proxy/ProxySettings$Type;

    move-result-object p0

    .line 175
    invoke-static {}, Lorg/telegram/utils/proxy/ProxySettings;->builder()Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object v5

    .line 176
    invoke-virtual {v5, v0}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->setAddress(Ljava/lang/String;)Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object v0

    .line 177
    invoke-virtual {v0, v2}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->setUser(Ljava/lang/String;)Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object v0

    .line 178
    invoke-virtual {v0, v3}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->setPassword(Ljava/lang/String;)Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object v0

    .line 179
    invoke-virtual {v0, v1}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->setSecret(Ljava/lang/String;)Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object v0

    .line 180
    invoke-virtual {v0, v4}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->setPort(I)Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object v0

    .line 181
    invoke-virtual {v0, p0}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->setType(Lorg/telegram/utils/proxy/ProxySettings$Type;)Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object p0

    .line 182
    invoke-virtual {p0}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->build()Lorg/telegram/utils/proxy/ProxySettings;

    move-result-object p0

    return-object p0
.end method

.method public static fromUri(Landroid/net/Uri;)Lorg/telegram/utils/proxy/ProxySettings;
    .locals 7

    .line 219
    const-string v0, "tg://telegram.org/"

    const/4 v1, 0x0

    if-nez p0, :cond_0

    return-object v1

    .line 224
    :cond_0
    :try_start_0
    invoke-virtual {p0}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object v2

    if-nez v2, :cond_1

    return-object v1

    .line 231
    :cond_1
    const-string v3, "http"

    invoke-virtual {v2, v3}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v3

    if-nez v3, :cond_b

    const-string v3, "https"

    invoke-virtual {v2, v3}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_2

    goto :goto_4

    .line 256
    :cond_2
    const-string v3, "tg"

    invoke-virtual {v2, v3}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_a

    .line 257
    invoke-virtual {p0}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object p0

    .line 259
    const-string v2, "tg://socks"

    invoke-virtual {p0, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v2

    if-nez v2, :cond_8

    const-string v2, "tg:socks"

    invoke-virtual {p0, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_3

    goto :goto_2

    .line 261
    :cond_3
    const-string v2, "tg://proxy"

    invoke-virtual {p0, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v2

    if-nez v2, :cond_7

    const-string v2, "tg:proxy"

    invoke-virtual {p0, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_4

    goto :goto_1

    .line 263
    :cond_4
    const-string v2, "tg://webproxy"

    invoke-virtual {p0, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v2

    if-nez v2, :cond_6

    const-string v2, "tg:webproxy"

    invoke-virtual {p0, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_5

    goto :goto_0

    :cond_5
    return-object v1

    .line 264
    :cond_6
    :goto_0
    sget-object v2, Lorg/telegram/utils/proxy/ProxySettings$Type;->WEB:Lorg/telegram/utils/proxy/ProxySettings$Type;

    goto :goto_3

    .line 262
    :cond_7
    :goto_1
    sget-object v2, Lorg/telegram/utils/proxy/ProxySettings$Type;->MTPROTO:Lorg/telegram/utils/proxy/ProxySettings$Type;

    goto :goto_3

    .line 260
    :cond_8
    :goto_2
    sget-object v2, Lorg/telegram/utils/proxy/ProxySettings$Type;->SOCKS5:Lorg/telegram/utils/proxy/ProxySettings$Type;

    :goto_3
    const/16 v3, 0x3f

    .line 269
    invoke-virtual {p0, v3}, Ljava/lang/String;->indexOf(I)I

    move-result v3

    if-gez v3, :cond_9

    return-object v1

    .line 274
    :cond_9
    invoke-virtual {p0, v3}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object p0

    goto :goto_6

    :cond_a
    return-object v1

    .line 232
    :cond_b
    :goto_4
    invoke-virtual {p0}, Landroid/net/Uri;->getHost()Ljava/lang/String;

    move-result-object v0

    if-nez v0, :cond_c

    return-object v1

    .line 237
    :cond_c
    invoke-virtual {v0}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v0

    .line 238
    const-string v2, "telegram.me"

    invoke-virtual {v0, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_d

    const-string v2, "t.me"

    invoke-virtual {v0, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_d

    const-string v2, "telegram.dog"

    invoke-virtual {v0, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_d

    return-object v1

    .line 242
    :cond_d
    invoke-virtual {p0}, Landroid/net/Uri;->getPath()Ljava/lang/String;

    move-result-object v0

    if-nez v0, :cond_e

    return-object v1

    .line 247
    :cond_e
    const-string v2, "/socks"

    invoke-virtual {v0, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_f

    .line 248
    sget-object v0, Lorg/telegram/utils/proxy/ProxySettings$Type;->SOCKS5:Lorg/telegram/utils/proxy/ProxySettings$Type;

    :goto_5
    move-object v2, v0

    goto :goto_6

    .line 249
    :cond_f
    const-string v2, "/proxy"

    invoke-virtual {v0, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_10

    .line 250
    sget-object v0, Lorg/telegram/utils/proxy/ProxySettings$Type;->MTPROTO:Lorg/telegram/utils/proxy/ProxySettings$Type;

    goto :goto_5

    .line 251
    :cond_10
    const-string v2, "/webproxy"

    invoke-virtual {v0, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_17

    .line 252
    sget-object v0, Lorg/telegram/utils/proxy/ProxySettings$Type;->WEB:Lorg/telegram/utils/proxy/ProxySettings$Type;

    goto :goto_5

    .line 279
    :goto_6
    const-string v0, "server"

    invoke-virtual {p0, v0}, Landroid/net/Uri;->getQueryParameter(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    if-nez v0, :cond_11

    .line 281
    const-string v0, "host"

    invoke-virtual {p0, v0}, Landroid/net/Uri;->getQueryParameter(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 283
    :cond_11
    sget-object v3, Lorg/telegram/utils/proxy/ProxySettings$Type;->WEB:Lorg/telegram/utils/proxy/ProxySettings$Type;

    if-eq v2, v3, :cond_12

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->checkHostForPunycode(Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_12

    const/4 v3, 0x1

    .line 284
    invoke-static {v0, v3}, Ljava/net/IDN;->toASCII(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    .line 288
    :cond_12
    const-string v3, "port"

    invoke-virtual {p0, v3}, Landroid/net/Uri;->getQueryParameter(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    .line 289
    invoke-static {v3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v4
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1

    if-nez v4, :cond_13

    .line 291
    :try_start_1
    invoke-static {v3}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v3
    :try_end_1
    .catch Ljava/lang/NumberFormatException; {:try_start_1 .. :try_end_1} :catch_0
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    goto :goto_7

    :catch_0
    :cond_13
    const/4 v3, 0x0

    .line 295
    :goto_7
    :try_start_2
    const-string v4, "secret"

    invoke-virtual {p0, v4}, Landroid/net/Uri;->getQueryParameter(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 296
    sget-object v5, Lorg/telegram/utils/proxy/ProxySettings$Type;->WEB:Lorg/telegram/utils/proxy/ProxySettings$Type;

    if-ne v2, v5, :cond_15

    .line 297
    invoke-static {v0}, Lorg/telegram/utils/proxy/ProxySettings;->hasWebProxyPath(Ljava/lang/String;)Z

    move-result v6

    if-eqz v6, :cond_14

    .line 299
    invoke-static {v4}, Lorg/telegram/utils/proxy/ProxySettings;->decodeMarkedWebProxySecret(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    if-nez v4, :cond_15

    return-object v1

    .line 303
    :cond_14
    invoke-static {v4}, Lorg/telegram/utils/proxy/ProxySettings;->isValidWebProxySecret(Ljava/lang/String;)Z

    move-result v6

    if-nez v6, :cond_15

    return-object v1

    .line 308
    :cond_15
    invoke-static {}, Lorg/telegram/utils/proxy/ProxySettings;->builder()Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object v6

    .line 309
    invoke-virtual {v6, v2}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->setType(Lorg/telegram/utils/proxy/ProxySettings$Type;)Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object v6

    .line 310
    invoke-virtual {v6, v0}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->setAddress(Ljava/lang/String;)Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object v0

    .line 311
    invoke-virtual {v0, v3}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->setPort(I)Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object v0

    const-string v3, "user"

    .line 312
    invoke-virtual {p0, v3}, Landroid/net/Uri;->getQueryParameter(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0, v3}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->setUser(Ljava/lang/String;)Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object v0

    const-string v3, "pass"

    .line 313
    invoke-virtual {p0, v3}, Landroid/net/Uri;->getQueryParameter(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->setPassword(Ljava/lang/String;)Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object p0

    .line 314
    invoke-virtual {p0, v4}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->setSecret(Ljava/lang/String;)Lorg/telegram/utils/proxy/ProxySettings$Builder;

    move-result-object p0

    .line 315
    invoke-virtual {p0}, Lorg/telegram/utils/proxy/ProxySettings$Builder;->build()Lorg/telegram/utils/proxy/ProxySettings;

    move-result-object p0

    if-ne v2, v5, :cond_16

    .line 316
    invoke-virtual {p0}, Lorg/telegram/utils/proxy/ProxySettings;->isValid()Z

    move-result v0
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_1

    if-eqz v0, :cond_17

    :cond_16
    move-object v1, p0

    :catch_1
    :cond_17
    return-object v1
.end method

.method private static hasWebProxyPath(Ljava/lang/String;)Z
    .locals 1

    if-eqz p0, :cond_0

    const/16 v0, 0x2f

    .line 431
    invoke-virtual {p0, v0}, Ljava/lang/String;->indexOf(I)I

    move-result p0

    if-ltz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private static hexToBytes(Ljava/lang/String;)[B
    .locals 6

    .line 498
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v0

    div-int/lit8 v0, v0, 0x2

    new-array v1, v0, [B

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v0, :cond_0

    mul-int/lit8 v3, v2, 0x2

    .line 500
    invoke-virtual {p0, v3}, Ljava/lang/String;->charAt(I)C

    move-result v4

    const/16 v5, 0x10

    invoke-static {v4, v5}, Ljava/lang/Character;->digit(CI)I

    move-result v4

    shl-int/lit8 v4, v4, 0x4

    add-int/lit8 v3, v3, 0x1

    .line 501
    invoke-virtual {p0, v3}, Ljava/lang/String;->charAt(I)C

    move-result v3

    invoke-static {v3, v5}, Ljava/lang/Character;->digit(CI)I

    move-result v3

    or-int/2addr v3, v4

    int-to-byte v3, v3

    aput-byte v3, v1, v2

    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_0
    return-object v1
.end method

.method public static intToType(I)Lorg/telegram/utils/proxy/ProxySettings$Type;
    .locals 1

    if-eqz p0, :cond_2

    const/4 v0, 0x1

    if-eq p0, v0, :cond_1

    const/4 v0, 0x2

    if-eq p0, v0, :cond_0

    .line 539
    sget-object p0, Lorg/telegram/utils/proxy/ProxySettings$Type;->SOCKS5:Lorg/telegram/utils/proxy/ProxySettings$Type;

    return-object p0

    .line 537
    :cond_0
    sget-object p0, Lorg/telegram/utils/proxy/ProxySettings$Type;->WEB:Lorg/telegram/utils/proxy/ProxySettings$Type;

    return-object p0

    .line 535
    :cond_1
    sget-object p0, Lorg/telegram/utils/proxy/ProxySettings$Type;->MTPROTO:Lorg/telegram/utils/proxy/ProxySettings$Type;

    return-object p0

    .line 533
    :cond_2
    sget-object p0, Lorg/telegram/utils/proxy/ProxySettings$Type;->SOCKS5:Lorg/telegram/utils/proxy/ProxySettings$Type;

    return-object p0
.end method

.method private static isHex(Ljava/lang/String;I)Z
    .locals 2

    .line 451
    :goto_0
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v0

    if-ge p1, v0, :cond_1

    .line 452
    invoke-virtual {p0, p1}, Ljava/lang/String;->charAt(I)C

    move-result v0

    const/16 v1, 0x10

    invoke-static {v0, v1}, Ljava/lang/Character;->digit(CI)I

    move-result v0

    if-gez v0, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    :cond_1
    const/4 p0, 0x1

    return p0
.end method

.method private static isValidWebAddress(Ljava/lang/String;)Z
    .locals 3

    .line 408
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    return v1

    :cond_0
    const/16 v0, 0x2f

    .line 412
    invoke-virtual {p0, v0}, Ljava/lang/String;->indexOf(I)I

    move-result v0

    if-ltz v0, :cond_1

    .line 413
    invoke-virtual {p0, v1, v0}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v2

    goto :goto_0

    :cond_1
    move-object v2, p0

    :goto_0
    if-ltz v0, :cond_2

    add-int/lit8 v0, v0, 0x1

    .line 414
    invoke-virtual {p0, v0}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p0

    goto :goto_1

    :cond_2
    const/4 p0, 0x0

    .line 415
    :goto_1
    invoke-static {v2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_4

    const/16 v0, 0x3a

    .line 416
    invoke-virtual {v2, v0}, Ljava/lang/String;->indexOf(I)I

    move-result v0

    if-gez v0, :cond_4

    const/16 v0, 0x3f

    .line 417
    invoke-virtual {v2, v0}, Ljava/lang/String;->indexOf(I)I

    move-result v0

    if-gez v0, :cond_4

    const/16 v0, 0x23

    .line 418
    invoke-virtual {v2, v0}, Ljava/lang/String;->indexOf(I)I

    move-result v0

    if-gez v0, :cond_4

    if-eqz p0, :cond_3

    .line 419
    invoke-static {p0}, Lorg/telegram/utils/proxy/ProxySettings;->isValidWebProxyPath(Ljava/lang/String;)Z

    move-result p0

    if-nez p0, :cond_3

    goto :goto_2

    :cond_3
    const/4 p0, 0x3

    .line 424
    :try_start_0
    invoke-static {v2, p0}, Ljava/net/IDN;->toASCII(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object p0

    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    invoke-virtual {p0, v0}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v2, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0
    :try_end_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0

    return p0

    :catch_0
    :cond_4
    :goto_2
    return v1
.end method

.method private static isValidWebProxyPath(Ljava/lang/String;)Z
    .locals 2

    .line 435
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v0

    const/16 v1, 0x80

    if-gt v0, v1, :cond_0

    sget-object v0, Lorg/telegram/utils/proxy/ProxySettings;->WEB_PROXY_PATH_PATTERN:Ljava/util/regex/Pattern;

    invoke-virtual {v0, p0}, Ljava/util/regex/Pattern;->matcher(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;

    move-result-object p0

    invoke-virtual {p0}, Ljava/util/regex/Matcher;->matches()Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private static isValidWebProxySecret(Ljava/lang/String;)Z
    .locals 9

    const/4 v0, 0x0

    if-nez p0, :cond_0

    return v0

    .line 442
    :cond_0
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v1

    const/16 v2, 0x20

    if-ne v1, v2, :cond_1

    .line 443
    invoke-static {p0, v0}, Lorg/telegram/utils/proxy/ProxySettings;->isHex(Ljava/lang/String;I)Z

    move-result p0

    return p0

    .line 445
    :cond_1
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v1

    const/16 v2, 0x22

    if-ne v1, v2, :cond_2

    const/4 v7, 0x0

    const/4 v8, 0x2

    const/4 v4, 0x1

    const/4 v5, 0x0

    .line 446
    const-string v6, "dd"

    move-object v3, p0

    invoke-virtual/range {v3 .. v8}, Ljava/lang/String;->regionMatches(ZILjava/lang/String;II)Z

    move-result p0

    if-eqz p0, :cond_2

    const/4 p0, 0x2

    .line 447
    invoke-static {v3, p0}, Lorg/telegram/utils/proxy/ProxySettings;->isHex(Ljava/lang/String;I)Z

    move-result p0

    if-eqz p0, :cond_2

    const/4 p0, 0x1

    return p0

    :cond_2
    return v0
.end method

.method private static normalizeWebAddress(Ljava/lang/String;)Ljava/lang/String;
    .locals 4

    .line 384
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 385
    const-string p0, ""

    return-object p0

    :cond_0
    const/16 v0, 0x2f

    .line 388
    invoke-virtual {p0, v0}, Ljava/lang/String;->indexOf(I)I

    move-result v1

    if-ltz v1, :cond_1

    const/4 v2, 0x0

    .line 389
    invoke-virtual {p0, v2, v1}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v2

    goto :goto_0

    :cond_1
    move-object v2, p0

    :goto_0
    if-ltz v1, :cond_2

    add-int/lit8 v1, v1, 0x1

    .line 390
    invoke-virtual {p0, v1}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v1

    goto :goto_1

    :cond_2
    const/4 v1, 0x0

    .line 391
    :goto_1
    invoke-static {v2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3

    if-nez v3, :cond_5

    const/16 v3, 0x3a

    .line 392
    invoke-virtual {v2, v3}, Ljava/lang/String;->indexOf(I)I

    move-result v3

    if-gez v3, :cond_5

    const/16 v3, 0x3f

    .line 393
    invoke-virtual {v2, v3}, Ljava/lang/String;->indexOf(I)I

    move-result v3

    if-gez v3, :cond_5

    const/16 v3, 0x23

    .line 394
    invoke-virtual {v2, v3}, Ljava/lang/String;->indexOf(I)I

    move-result v3

    if-gez v3, :cond_5

    if-eqz v1, :cond_3

    .line 395
    invoke-static {v1}, Lorg/telegram/utils/proxy/ProxySettings;->isValidWebProxyPath(Ljava/lang/String;)Z

    move-result v3

    if-nez v3, :cond_3

    goto :goto_2

    :cond_3
    const/4 v3, 0x3

    .line 400
    :try_start_0
    invoke-static {v2, v3}, Ljava/net/IDN;->toASCII(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v2

    sget-object v3, Ljava/util/Locale;->US:Ljava/util/Locale;

    invoke-virtual {v2, v3}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object v2

    if-nez v1, :cond_4

    return-object v2

    .line 401
    :cond_4
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    :cond_5
    :goto_2
    return-object p0
.end method

.method public static typeToInt(Lorg/telegram/utils/proxy/ProxySettings$Type;)I
    .locals 1

    .line 519
    sget-object v0, Lorg/telegram/utils/proxy/ProxySettings$1;->$SwitchMap$org$telegram$utils$proxy$ProxySettings$Type:[I

    invoke-virtual {p0}, Ljava/lang/Enum;->ordinal()I

    move-result p0

    aget p0, v0, p0

    const/4 v0, 0x1

    if-eq p0, v0, :cond_0

    const/4 v0, 0x2

    if-eq p0, v0, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    return v0
.end method


# virtual methods
.method public equals(Ljava/lang/Object;)Z
    .locals 4

    const/4 v0, 0x1

    if-ne p0, p1, :cond_0

    return v0

    .line 143
    :cond_0
    instance-of v1, p1, Lorg/telegram/utils/proxy/ProxySettings;

    const/4 v2, 0x0

    if-nez v1, :cond_1

    return v2

    .line 147
    :cond_1
    check-cast p1, Lorg/telegram/utils/proxy/ProxySettings;

    .line 148
    iget v1, p0, Lorg/telegram/utils/proxy/ProxySettings;->port:I

    iget v3, p1, Lorg/telegram/utils/proxy/ProxySettings;->port:I

    if-ne v1, v3, :cond_2

    iget-object v1, p0, Lorg/telegram/utils/proxy/ProxySettings;->type:Lorg/telegram/utils/proxy/ProxySettings$Type;

    iget-object v3, p1, Lorg/telegram/utils/proxy/ProxySettings;->type:Lorg/telegram/utils/proxy/ProxySettings$Type;

    if-ne v1, v3, :cond_2

    iget-object v1, p0, Lorg/telegram/utils/proxy/ProxySettings;->address:Ljava/lang/String;

    iget-object v3, p1, Lorg/telegram/utils/proxy/ProxySettings;->address:Ljava/lang/String;

    .line 150
    invoke-static {v1, v3}, Ljava/util/Objects;->equals(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_2

    iget-object v1, p0, Lorg/telegram/utils/proxy/ProxySettings;->user:Ljava/lang/String;

    iget-object v3, p1, Lorg/telegram/utils/proxy/ProxySettings;->user:Ljava/lang/String;

    .line 151
    invoke-static {v1, v3}, Ljava/util/Objects;->equals(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_2

    iget-object v1, p0, Lorg/telegram/utils/proxy/ProxySettings;->password:Ljava/lang/String;

    iget-object v3, p1, Lorg/telegram/utils/proxy/ProxySettings;->password:Ljava/lang/String;

    .line 152
    invoke-static {v1, v3}, Ljava/util/Objects;->equals(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_2

    iget-object p0, p0, Lorg/telegram/utils/proxy/ProxySettings;->secret:Ljava/lang/String;

    iget-object p1, p1, Lorg/telegram/utils/proxy/ProxySettings;->secret:Ljava/lang/String;

    .line 153
    invoke-static {p0, p1}, Ljava/util/Objects;->equals(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_2

    return v0

    :cond_2
    return v2
.end method

.method public getAddress()Ljava/lang/String;
    .locals 0

    .line 71
    iget-object p0, p0, Lorg/telegram/utils/proxy/ProxySettings;->address:Ljava/lang/String;

    return-object p0
.end method

.method public getLink()Ljava/lang/String;
    .locals 4

    .line 104
    const-string v0, "UTF-8"

    sget-object v1, Lorg/telegram/utils/proxy/ProxySettings$1;->$SwitchMap$org$telegram$utils$proxy$ProxySettings$Type:[I

    iget-object v2, p0, Lorg/telegram/utils/proxy/ProxySettings;->type:Lorg/telegram/utils/proxy/ProxySettings$Type;

    invoke-virtual {v2}, Ljava/lang/Enum;->ordinal()I

    move-result v2

    aget v1, v1, v2

    const/4 v2, 0x1

    if-eq v1, v2, :cond_1

    const/4 v2, 0x2

    if-eq v1, v2, :cond_0

    .line 113
    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "https://t.me/socks?"

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    goto :goto_0

    .line 109
    :cond_0
    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "https://t.me/webproxy?"

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    goto :goto_0

    .line 106
    :cond_1
    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "https://t.me/proxy?"

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    .line 118
    :goto_0
    :try_start_0
    const-string v2, "server="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, p0, Lorg/telegram/utils/proxy/ProxySettings;->address:Ljava/lang/String;

    invoke-static {v2, v0}, Lj$/net/URLEncoder;->encode(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 119
    iget-object v2, p0, Lorg/telegram/utils/proxy/ProxySettings;->type:Lorg/telegram/utils/proxy/ProxySettings$Type;

    sget-object v3, Lorg/telegram/utils/proxy/ProxySettings$Type;->WEB:Lorg/telegram/utils/proxy/ProxySettings$Type;

    if-eq v2, v3, :cond_2

    .line 120
    const-string v2, "&port="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v2, p0, Lorg/telegram/utils/proxy/ProxySettings;->port:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    .line 122
    :cond_2
    iget-object v2, p0, Lorg/telegram/utils/proxy/ProxySettings;->user:Ljava/lang/String;

    invoke-static {v2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_3

    .line 123
    const-string v2, "&user="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, p0, Lorg/telegram/utils/proxy/ProxySettings;->user:Ljava/lang/String;

    invoke-static {v2, v0}, Lj$/net/URLEncoder;->encode(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 125
    :cond_3
    iget-object v2, p0, Lorg/telegram/utils/proxy/ProxySettings;->password:Ljava/lang/String;

    invoke-static {v2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_4

    .line 126
    const-string v2, "&pass="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, p0, Lorg/telegram/utils/proxy/ProxySettings;->password:Ljava/lang/String;

    invoke-static {v2, v0}, Lj$/net/URLEncoder;->encode(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 128
    :cond_4
    iget-object v2, p0, Lorg/telegram/utils/proxy/ProxySettings;->secret:Ljava/lang/String;

    invoke-static {v2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_6

    .line 129
    iget-object v2, p0, Lorg/telegram/utils/proxy/ProxySettings;->type:Lorg/telegram/utils/proxy/ProxySettings$Type;

    if-ne v2, v3, :cond_5

    iget-object v2, p0, Lorg/telegram/utils/proxy/ProxySettings;->address:Ljava/lang/String;

    invoke-static {v2}, Lorg/telegram/utils/proxy/ProxySettings;->hasWebProxyPath(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_5

    .line 130
    iget-object p0, p0, Lorg/telegram/utils/proxy/ProxySettings;->secret:Ljava/lang/String;

    invoke-static {p0}, Lorg/telegram/utils/proxy/ProxySettings;->encodeMarkedWebProxySecret(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    goto :goto_1

    .line 131
    :cond_5
    iget-object p0, p0, Lorg/telegram/utils/proxy/ProxySettings;->secret:Ljava/lang/String;

    .line 132
    :goto_1
    const-string v2, "&secret="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {p0, v0}, Lj$/net/URLEncoder;->encode(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
    :try_end_0
    .catch Ljava/io/UnsupportedEncodingException; {:try_start_0 .. :try_end_0} :catch_0

    .line 135
    :catch_0
    :cond_6
    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public getPassword()Ljava/lang/String;
    .locals 0

    .line 85
    iget-object p0, p0, Lorg/telegram/utils/proxy/ProxySettings;->password:Ljava/lang/String;

    return-object p0
.end method

.method public getPort()I
    .locals 0

    .line 75
    iget p0, p0, Lorg/telegram/utils/proxy/ProxySettings;->port:I

    return p0
.end method

.method public getSecret()Ljava/lang/String;
    .locals 0

    .line 90
    iget-object p0, p0, Lorg/telegram/utils/proxy/ProxySettings;->secret:Ljava/lang/String;

    return-object p0
.end method

.method public getType()Lorg/telegram/utils/proxy/ProxySettings$Type;
    .locals 0

    .line 66
    iget-object p0, p0, Lorg/telegram/utils/proxy/ProxySettings;->type:Lorg/telegram/utils/proxy/ProxySettings$Type;

    return-object p0
.end method

.method public getUser()Ljava/lang/String;
    .locals 0

    .line 80
    iget-object p0, p0, Lorg/telegram/utils/proxy/ProxySettings;->user:Ljava/lang/String;

    return-object p0
.end method

.method public hashCode()I
    .locals 6

    .line 158
    iget-object v0, p0, Lorg/telegram/utils/proxy/ProxySettings;->type:Lorg/telegram/utils/proxy/ProxySettings$Type;

    iget-object v1, p0, Lorg/telegram/utils/proxy/ProxySettings;->address:Ljava/lang/String;

    iget v2, p0, Lorg/telegram/utils/proxy/ProxySettings;->port:I

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    iget-object v3, p0, Lorg/telegram/utils/proxy/ProxySettings;->user:Ljava/lang/String;

    iget-object v4, p0, Lorg/telegram/utils/proxy/ProxySettings;->password:Ljava/lang/String;

    iget-object v5, p0, Lorg/telegram/utils/proxy/ProxySettings;->secret:Ljava/lang/String;

    filled-new-array/range {v0 .. v5}, [Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Ljava/util/Objects;->hash([Ljava/lang/Object;)I

    move-result p0

    return p0
.end method

.method public isValid()Z
    .locals 4

    .line 94
    iget-object v0, p0, Lorg/telegram/utils/proxy/ProxySettings;->address:Ljava/lang/String;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    return v1

    .line 97
    :cond_0
    iget-object v0, p0, Lorg/telegram/utils/proxy/ProxySettings;->type:Lorg/telegram/utils/proxy/ProxySettings$Type;

    sget-object v2, Lorg/telegram/utils/proxy/ProxySettings$Type;->WEB:Lorg/telegram/utils/proxy/ProxySettings$Type;

    const/4 v3, 0x1

    if-ne v0, v2, :cond_2

    .line 98
    iget-object v0, p0, Lorg/telegram/utils/proxy/ProxySettings;->address:Ljava/lang/String;

    invoke-static {v0}, Lorg/telegram/utils/proxy/ProxySettings;->isValidWebAddress(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object p0, p0, Lorg/telegram/utils/proxy/ProxySettings;->secret:Ljava/lang/String;

    invoke-static {p0}, Lorg/telegram/utils/proxy/ProxySettings;->isValidWebProxySecret(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_1

    return v3

    :cond_1
    return v1

    .line 99
    :cond_2
    iget p0, p0, Lorg/telegram/utils/proxy/ProxySettings;->port:I

    if-lez p0, :cond_3

    return v3

    :cond_3
    return v1
.end method

.method public toSharedPreferences(Landroid/content/SharedPreferences$Editor;)V
    .locals 6

    .line 186
    iget-object v0, p0, Lorg/telegram/utils/proxy/ProxySettings;->type:Lorg/telegram/utils/proxy/ProxySettings$Type;

    invoke-static {v0}, Lorg/telegram/utils/proxy/ProxySettings;->typeToInt(Lorg/telegram/utils/proxy/ProxySettings$Type;)I

    move-result v0

    const-string v1, "proxy_type"

    invoke-interface {p1, v1, v0}, Landroid/content/SharedPreferences$Editor;->putInt(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;

    .line 187
    const-string v0, "proxy_ip"

    iget-object v1, p0, Lorg/telegram/utils/proxy/ProxySettings;->address:Ljava/lang/String;

    invoke-interface {p1, v0, v1}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 188
    sget-object v0, Lorg/telegram/utils/proxy/ProxySettings$1;->$SwitchMap$org$telegram$utils$proxy$ProxySettings$Type:[I

    iget-object v1, p0, Lorg/telegram/utils/proxy/ProxySettings;->type:Lorg/telegram/utils/proxy/ProxySettings$Type;

    invoke-virtual {v1}, Ljava/lang/Enum;->ordinal()I

    move-result v1

    aget v0, v0, v1

    const/4 v1, 0x1

    const-string v2, "proxy_secret"

    const-string v3, "proxy_port"

    const-string v4, "proxy_user"

    const-string v5, "proxy_pass"

    if-eq v0, v1, :cond_4

    const/4 v1, 0x2

    if-eq v0, v1, :cond_3

    const/4 v1, 0x3

    if-eq v0, v1, :cond_0

    return-void

    .line 190
    :cond_0
    iget v0, p0, Lorg/telegram/utils/proxy/ProxySettings;->port:I

    invoke-interface {p1, v3, v0}, Landroid/content/SharedPreferences$Editor;->putInt(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;

    .line 191
    invoke-interface {p1, v2}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 192
    iget-object v0, p0, Lorg/telegram/utils/proxy/ProxySettings;->password:Ljava/lang/String;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 193
    invoke-interface {p1, v5}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    goto :goto_0

    .line 195
    :cond_1
    iget-object v0, p0, Lorg/telegram/utils/proxy/ProxySettings;->password:Ljava/lang/String;

    invoke-interface {p1, v5, v0}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 197
    :goto_0
    iget-object v0, p0, Lorg/telegram/utils/proxy/ProxySettings;->user:Ljava/lang/String;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 198
    invoke-interface {p1, v4}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    return-void

    .line 200
    :cond_2
    iget-object p0, p0, Lorg/telegram/utils/proxy/ProxySettings;->user:Ljava/lang/String;

    invoke-interface {p1, v4, p0}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    return-void

    .line 210
    :cond_3
    iget-object p0, p0, Lorg/telegram/utils/proxy/ProxySettings;->secret:Ljava/lang/String;

    invoke-interface {p1, v2, p0}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 211
    invoke-interface {p1, v3}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 212
    invoke-interface {p1, v5}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 213
    invoke-interface {p1, v4}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    return-void

    .line 204
    :cond_4
    iget-object v0, p0, Lorg/telegram/utils/proxy/ProxySettings;->secret:Ljava/lang/String;

    invoke-interface {p1, v2, v0}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 205
    iget p0, p0, Lorg/telegram/utils/proxy/ProxySettings;->port:I

    invoke-interface {p1, v3, p0}, Landroid/content/SharedPreferences$Editor;->putInt(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;

    .line 206
    invoke-interface {p1, v5}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 207
    invoke-interface {p1, v4}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    return-void
.end method
