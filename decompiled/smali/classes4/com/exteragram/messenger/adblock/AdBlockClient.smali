.class public abstract Lcom/exteragram/messenger/adblock/AdBlockClient;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;,
        Lcom/exteragram/messenger/adblock/AdBlockClient$BlockedInputStream;
    }
.end annotation


# static fields
.field private static final BLOCKED_EXCEPTION:Ljava/io/IOException;


# direct methods
.method public static bridge synthetic -$$Nest$sfgetBLOCKED_EXCEPTION()Ljava/io/IOException;
    .locals 1

    .line 0
    sget-object v0, Lcom/exteragram/messenger/adblock/AdBlockClient;->BLOCKED_EXCEPTION:Ljava/io/IOException;

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 2

    .line 19
    new-instance v0, Lcom/exteragram/messenger/adblock/AdBlockClient$1;

    const-string v1, "Blocked by filter"

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/adblock/AdBlockClient$1;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/exteragram/messenger/adblock/AdBlockClient;->BLOCKED_EXCEPTION:Ljava/io/IOException;

    return-void
.end method

.method public static createBlockedResponse(Ljava/lang/String;Lcom/exteragram/messenger/adblock/data/BlockResult;)Landroid/webkit/WebResourceResponse;
    .locals 10

    .line 47
    invoke-virtual {p1}, Lcom/exteragram/messenger/adblock/data/BlockResult;->getRedirect()Ljava/lang/String;

    move-result-object p1

    if-eqz p1, :cond_0

    .line 48
    const-string v0, "data:"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/16 v0, 0x3b

    .line 49
    invoke-virtual {p1, v0}, Ljava/lang/String;->indexOf(I)I

    move-result v0

    const/16 v1, 0x2c

    .line 50
    invoke-virtual {p1, v1}, Ljava/lang/String;->indexOf(I)I

    move-result v1

    const/4 v2, 0x5

    if-le v0, v2, :cond_0

    if-le v1, v0, :cond_0

    .line 53
    :try_start_0
    invoke-virtual {p1, v2, v0}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v4

    add-int/lit8 v1, v1, 0x1

    .line 54
    invoke-virtual {p1, v1}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p1

    const/4 v0, 0x0

    invoke-static {p1, v0}, Landroid/util/Base64;->decode(Ljava/lang/String;I)[B

    move-result-object p1

    .line 56
    new-instance v8, Ljava/util/HashMap;

    invoke-direct {v8}, Ljava/util/HashMap;-><init>()V

    .line 57
    const-string v0, "Content-Type"

    invoke-virtual {v8, v0, v4}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 58
    const-string v0, "Access-Control-Allow-Credentials"

    const-string v1, "true"

    invoke-virtual {v8, v0, v1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 59
    const-string v0, "Access-Control-Allow-Headers"

    const-string v1, "Cache-Control"

    invoke-virtual {v8, v0, v1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 60
    const-string v0, "Access-Control-Allow-Origin"

    const-string v1, "*"

    invoke-virtual {v8, v0, v1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 62
    new-instance v3, Landroid/webkit/WebResourceResponse;

    const-string v7, "OK"

    new-instance v9, Ljava/io/ByteArrayInputStream;

    invoke-direct {v9, p1}, Ljava/io/ByteArrayInputStream;-><init>([B)V

    const/4 v5, 0x0

    const/16 v6, 0xc8

    invoke-direct/range {v3 .. v9}, Landroid/webkit/WebResourceResponse;-><init>(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/util/Map;Ljava/io/InputStream;)V
    :try_end_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0

    return-object v3

    .line 67
    :catch_0
    :cond_0
    const-string p1, "sub_frame"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_1

    .line 68
    new-instance v0, Landroid/webkit/WebResourceResponse;

    const/4 v5, 0x0

    const/4 v6, 0x0

    const-string v1, "text/html"

    const-string v2, "utf-8"

    const/16 v3, 0x1f4

    const-string v4, "Internal Server Error"

    invoke-direct/range {v0 .. v6}, Landroid/webkit/WebResourceResponse;-><init>(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/util/Map;Ljava/io/InputStream;)V

    return-object v0

    .line 70
    :cond_1
    new-instance p0, Landroid/webkit/WebResourceResponse;

    new-instance p1, Lcom/exteragram/messenger/adblock/AdBlockClient$BlockedInputStream;

    const/4 v0, 0x0

    invoke-direct {p1, v0}, Lcom/exteragram/messenger/adblock/AdBlockClient$BlockedInputStream;-><init>(Lcom/exteragram/messenger/adblock/AdBlockClient-IA;)V

    const-string v0, "text/plain"

    const-string v1, "utf-8"

    invoke-direct {p0, v0, v1, p1}, Landroid/webkit/WebResourceResponse;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/io/InputStream;)V

    return-object p0
.end method

.method private static createHideCss([Ljava/lang/String;)Ljava/lang/String;
    .locals 6

    const/4 v0, 0x0

    if-eqz p0, :cond_3

    .line 215
    array-length v1, p0

    if-nez v1, :cond_0

    goto :goto_1

    .line 219
    :cond_0
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    .line 220
    array-length v2, p0

    const/4 v3, 0x0

    :goto_0
    if-ge v3, v2, :cond_2

    aget-object v4, p0, v3

    .line 221
    invoke-static {v4}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v5

    if-nez v5, :cond_1

    const/16 v5, 0x7b

    invoke-virtual {v4, v5}, Ljava/lang/String;->indexOf(I)I

    move-result v5

    if-gez v5, :cond_1

    const/16 v5, 0x7d

    invoke-virtual {v4, v5}, Ljava/lang/String;->indexOf(I)I

    move-result v5

    if-gez v5, :cond_1

    .line 222
    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v4, "{display:none!important}\n"

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_1
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 225
    :cond_2
    invoke-virtual {v1}, Ljava/lang/StringBuilder;->length()I

    move-result p0

    if-lez p0, :cond_3

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_3
    :goto_1
    return-object v0
.end method

.method public static getCosmeticHide(Ljava/lang/String;)Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;
    .locals 6

    .line 27
    invoke-static {p0}, Lcom/exteragram/messenger/adblock/interop/AdBlock;->getCosmeticResources(Ljava/lang/String;)Lcom/exteragram/messenger/adblock/data/UrlCosmeticResources;

    move-result-object v0

    if-nez v0, :cond_0

    const/4 p0, 0x0

    return-object p0

    :cond_0
    move-object v1, v0

    .line 31
    new-instance v0, Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;

    invoke-virtual {v1}, Lcom/exteragram/messenger/adblock/data/UrlCosmeticResources;->getHideSelectors()[Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Lcom/exteragram/messenger/adblock/AdBlockClient;->createHideCss([Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1}, Lcom/exteragram/messenger/adblock/data/UrlCosmeticResources;->getInjectedScript()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1}, Lcom/exteragram/messenger/adblock/data/UrlCosmeticResources;->getExceptions()[Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1}, Lcom/exteragram/messenger/adblock/data/UrlCosmeticResources;->isGenericHide()Z

    move-result v5

    move-object v1, p0

    invoke-direct/range {v0 .. v5}, Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Z)V

    return-object v0
.end method

.method public static getHiddenSelectorsCss(Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;[Ljava/lang/String;[Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    .line 35
    array-length v0, p1

    if-nez v0, :cond_0

    array-length v0, p2

    if-nez v0, :cond_0

    const/4 p0, 0x0

    return-object p0

    .line 38
    :cond_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;->getExceptions()[Ljava/lang/String;

    move-result-object p0

    invoke-static {p1, p2, p0}, Lcom/exteragram/messenger/adblock/interop/AdBlock;->getHiddenSelectors([Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;)[Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lcom/exteragram/messenger/adblock/AdBlockClient;->createHideCss([Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static getRequestExtension(Ljava/lang/String;)Ljava/lang/String;
    .locals 3

    const/4 v0, 0x0

    if-eqz p0, :cond_7

    .line 128
    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_1

    :cond_0
    const/16 v1, 0x3f

    .line 131
    invoke-virtual {p0, v1}, Ljava/lang/String;->indexOf(I)I

    move-result v1

    if-lez v1, :cond_1

    const/4 v2, 0x0

    .line 133
    invoke-virtual {p0, v2, v1}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p0

    :cond_1
    const/16 v1, 0x2f

    .line 135
    invoke-virtual {p0, v1}, Ljava/lang/String;->lastIndexOf(I)I

    move-result v1

    if-lez v1, :cond_2

    add-int/lit8 v1, v1, 0x1

    .line 137
    invoke-virtual {p0, v1}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p0

    :cond_2
    const/16 v1, 0x2e

    .line 139
    invoke-virtual {p0, v1}, Ljava/lang/String;->lastIndexOf(I)I

    move-result v1

    if-lez v1, :cond_6

    .line 140
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v2

    add-int/lit8 v2, v2, -0x1

    if-ne v1, v2, :cond_3

    goto :goto_0

    :cond_3
    add-int/lit8 v1, v1, 0x1

    .line 146
    invoke-virtual {p0, v1}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p0

    sget-object v1, Ljava/util/Locale;->ROOT:Ljava/util/Locale;

    invoke-virtual {p0, v1}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object p0

    .line 147
    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_4

    return-object v0

    .line 150
    :cond_4
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v1

    const/16 v2, 0x8

    if-le v1, v2, :cond_5

    return-object v0

    :cond_5
    return-object p0

    .line 141
    :cond_6
    :goto_0
    const-string v1, "js"

    invoke-virtual {p0, v1}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_7

    return-object v1

    :cond_7
    :goto_1
    return-object v0
.end method

.method private static getRequestMime(Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    .line 182
    const-string v0, "mhtml"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_4

    const-string v0, "mht"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_1

    .line 185
    :cond_0
    const-string v0, "json"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 186
    const-string p0, "application/json"

    return-object p0

    .line 188
    :cond_1
    invoke-static {}, Landroid/webkit/MimeTypeMap;->getSingleton()Landroid/webkit/MimeTypeMap;

    move-result-object v0

    invoke-virtual {v0, p0}, Landroid/webkit/MimeTypeMap;->getMimeTypeFromExtension(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    if-eqz p0, :cond_3

    .line 189
    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_2

    goto :goto_0

    :cond_2
    return-object p0

    :cond_3
    :goto_0
    const-string p0, "application/octet-stream"

    return-object p0

    .line 183
    :cond_4
    :goto_1
    const-string p0, "multipart/related"

    return-object p0
.end method

.method public static getRequestType(Landroid/webkit/WebResourceRequest;Ljava/lang/String;)Ljava/lang/String;
    .locals 3

    .line 74
    const-string v0, "OPTIONS"

    invoke-interface {p0}, Landroid/webkit/WebResourceRequest;->getMethod()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 75
    const-string p0, "beacon"

    return-object p0

    .line 78
    :cond_0
    invoke-interface {p0}, Landroid/webkit/WebResourceRequest;->getUrl()Landroid/net/Uri;

    move-result-object v0

    invoke-virtual {v0}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v0

    .line 79
    invoke-interface {p0}, Landroid/webkit/WebResourceRequest;->getRequestHeaders()Ljava/util/Map;

    move-result-object v1

    .line 81
    invoke-interface {p0}, Landroid/webkit/WebResourceRequest;->isForMainFrame()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-virtual {v0, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_1

    .line 82
    const-string p0, "main_frame"

    return-object p0

    .line 85
    :cond_1
    const-string p1, "ws"

    invoke-virtual {v0, p1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p1

    if-eqz p1, :cond_2

    .line 86
    const-string p0, "websocket"

    return-object p0

    :cond_2
    if-eqz v1, :cond_3

    .line 89
    const-string p1, "X-Requested-With"

    invoke-interface {v1, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    const-string v2, "XMLHttpRequest"

    invoke-virtual {v2, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_3

    .line 90
    const-string p0, "xhr"

    return-object p0

    :cond_3
    if-eqz v1, :cond_4

    .line 93
    const-string p1, "Accept"

    invoke-interface {v1, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;

    goto :goto_0

    :cond_4
    const/4 p1, 0x0

    .line 94
    :goto_0
    invoke-interface {p0}, Landroid/webkit/WebResourceRequest;->isForMainFrame()Z

    move-result p0

    if-nez p0, :cond_5

    if-eqz p1, :cond_5

    const-string p0, "text/html"

    invoke-virtual {p1, p0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_5

    .line 95
    const-string p0, "sub_frame"

    return-object p0

    .line 98
    :cond_5
    invoke-static {v0}, Lcom/exteragram/messenger/adblock/AdBlockClient;->getRequestExtension(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 99
    const-string v0, "js"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_6

    .line 100
    const-string p0, "script"

    return-object p0

    .line 102
    :cond_6
    const-string v0, "css"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_7

    .line 103
    const-string p0, "stylesheet"

    return-object p0

    .line 105
    :cond_7
    const-string v0, "otf"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_d

    const-string v0, "ttf"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_d

    const-string v0, "ttc"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_d

    const-string v0, "woff"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_d

    const-string v0, "woff2"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_8

    goto :goto_2

    .line 108
    :cond_8
    const-string v0, "php"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_9

    .line 109
    invoke-static {p0}, Lcom/exteragram/messenger/adblock/AdBlockClient;->getRequestMime(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 110
    const-string v0, "application/octet-stream"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_9

    .line 111
    invoke-static {p0}, Lcom/exteragram/messenger/adblock/AdBlockClient;->getRequestTypeFromMime(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 115
    :cond_9
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p0

    if-nez p0, :cond_c

    const-string p0, "*/*"

    invoke-virtual {p0, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_a

    goto :goto_1

    :cond_a
    const/16 p0, 0x2c

    .line 119
    invoke-virtual {p1, p0}, Ljava/lang/String;->indexOf(I)I

    move-result p0

    if-lez p0, :cond_b

    const/4 v0, 0x0

    .line 121
    invoke-virtual {p1, v0, p0}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    .line 124
    :cond_b
    invoke-static {p1}, Lcom/exteragram/messenger/adblock/AdBlockClient;->getRequestTypeFromMime(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 116
    :cond_c
    :goto_1
    const-string p0, "other"

    return-object p0

    .line 106
    :cond_d
    :goto_2
    const-string p0, "font"

    return-object p0
.end method

.method private static getRequestTypeFromMime(Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    .line 193
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const-string v1, "other"

    if-eqz v0, :cond_0

    return-object v1

    .line 196
    :cond_0
    const-string v0, "application/javascript"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_7

    const-string v0, "application/x-javascript"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_7

    const-string v0, "text/javascript"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_7

    const-string v0, "application/json"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    goto :goto_1

    .line 199
    :cond_1
    const-string v0, "text/css"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 200
    const-string p0, "stylesheet"

    return-object p0

    .line 202
    :cond_2
    const-string v0, "image/"

    invoke-virtual {p0, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_3

    .line 203
    const-string p0, "image"

    return-object p0

    .line 205
    :cond_3
    const-string v0, "video/"

    invoke-virtual {p0, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_6

    const-string v0, "audio/"

    invoke-virtual {p0, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_4

    goto :goto_0

    .line 208
    :cond_4
    const-string v0, "font/"

    invoke-virtual {p0, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_5

    .line 209
    const-string p0, "font"

    return-object p0

    :cond_5
    return-object v1

    .line 206
    :cond_6
    :goto_0
    const-string p0, "media"

    return-object p0

    .line 197
    :cond_7
    :goto_1
    const-string p0, "script"

    return-object p0
.end method

.method public static isAdRequest(Landroid/webkit/WebResourceRequest;Ljava/lang/String;)Lcom/exteragram/messenger/adblock/data/BlockResult;
    .locals 1

    .line 42
    invoke-static {p0, p1}, Lcom/exteragram/messenger/adblock/AdBlockClient;->getRequestType(Landroid/webkit/WebResourceRequest;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 43
    invoke-interface {p0}, Landroid/webkit/WebResourceRequest;->getUrl()Landroid/net/Uri;

    move-result-object p0

    invoke-virtual {p0}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0, p1, v0}, Lcom/exteragram/messenger/adblock/interop/AdBlock;->getBlockResult(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/exteragram/messenger/adblock/data/BlockResult;

    move-result-object p0

    return-object p0
.end method
