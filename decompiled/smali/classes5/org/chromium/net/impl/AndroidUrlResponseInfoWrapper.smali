.class Lorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;
.super Lorg/chromium/net/UrlResponseInfo;
.source "SourceFile"


# instance fields
.field private final mBackend:Landroid/net/http/UrlResponseInfo;

.field private final mProxyServerCompat:Ljava/lang/String;


# direct methods
.method private constructor <init>(Landroid/net/http/UrlResponseInfo;Ljava/lang/String;)V
    .locals 0

    .line 28
    invoke-direct {p0}, Lorg/chromium/net/UrlResponseInfo;-><init>()V

    .line 29
    iput-object p1, p0, Lorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;->mBackend:Landroid/net/http/UrlResponseInfo;

    .line 30
    iput-object p2, p0, Lorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;->mProxyServerCompat:Ljava/lang/String;

    return-void
.end method

.method public static createForUrlRequest(Landroid/net/http/UrlResponseInfo;)Lorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;
    .locals 2

    .line 36
    invoke-static {p0}, Lorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;->isResponseInfoNull(Landroid/net/http/UrlResponseInfo;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 p0, 0x0

    return-object p0

    .line 38
    :cond_0
    new-instance v0, Lorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;

    const-string v1, ":0"

    invoke-direct {v0, p0, v1}, Lorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;-><init>(Landroid/net/http/UrlResponseInfo;Ljava/lang/String;)V

    return-object v0
.end method

.method private static isResponseInfoNull(Landroid/net/http/UrlResponseInfo;)Z
    .locals 1

    const/4 v0, 0x1

    if-nez p0, :cond_0

    return v0

    .line 59
    :cond_0
    :try_start_0
    invoke-virtual {p0}, Landroid/net/http/UrlResponseInfo;->getUrl()Ljava/lang/String;
    :try_end_0
    .catch Ljava/lang/NullPointerException; {:try_start_0 .. :try_end_0} :catch_0

    const/4 p0, 0x0

    return p0

    :catch_0
    return v0
.end method


# virtual methods
.method public getHttpStatusCode()I
    .locals 0

    .line 78
    iget-object p0, p0, Lorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;->mBackend:Landroid/net/http/UrlResponseInfo;

    invoke-virtual {p0}, Landroid/net/http/UrlResponseInfo;->getHttpStatusCode()I

    move-result p0

    return p0
.end method

.method public getUrl()Ljava/lang/String;
    .locals 0

    .line 68
    iget-object p0, p0, Lorg/chromium/net/impl/AndroidUrlResponseInfoWrapper;->mBackend:Landroid/net/http/UrlResponseInfo;

    invoke-virtual {p0}, Landroid/net/http/UrlResponseInfo;->getUrl()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
