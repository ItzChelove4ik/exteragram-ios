.class Lorg/telegram/utils/proxy/WebProxyTransport$1;
.super Landroid/webkit/WebViewClient;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/telegram/utils/proxy/WebProxyTransport;->createWebView()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/telegram/utils/proxy/WebProxyTransport;


# direct methods
.method public constructor <init>(Lorg/telegram/utils/proxy/WebProxyTransport;)V
    .locals 0

    .line 449
    iput-object p1, p0, Lorg/telegram/utils/proxy/WebProxyTransport$1;->this$0:Lorg/telegram/utils/proxy/WebProxyTransport;

    invoke-direct {p0}, Landroid/webkit/WebViewClient;-><init>()V

    return-void
.end method


# virtual methods
.method public onReceivedError(Landroid/webkit/WebView;Landroid/webkit/WebResourceRequest;Landroid/webkit/WebResourceError;)V
    .locals 0

    .line 474
    invoke-interface {p2}, Landroid/webkit/WebResourceRequest;->isForMainFrame()Z

    move-result p2

    if-eqz p2, :cond_0

    .line 475
    iget-object p0, p0, Lorg/telegram/utils/proxy/WebProxyTransport$1;->this$0:Lorg/telegram/utils/proxy/WebProxyTransport;

    invoke-static {p0, p1}, Lorg/telegram/utils/proxy/WebProxyTransport;->-$$Nest$mfailWebView(Lorg/telegram/utils/proxy/WebProxyTransport;Landroid/webkit/WebView;)V

    :cond_0
    return-void
.end method

.method public onReceivedHttpError(Landroid/webkit/WebView;Landroid/webkit/WebResourceRequest;Landroid/webkit/WebResourceResponse;)V
    .locals 0

    .line 481
    invoke-interface {p2}, Landroid/webkit/WebResourceRequest;->isForMainFrame()Z

    move-result p2

    if-eqz p2, :cond_0

    .line 482
    iget-object p0, p0, Lorg/telegram/utils/proxy/WebProxyTransport$1;->this$0:Lorg/telegram/utils/proxy/WebProxyTransport;

    invoke-static {p0, p1}, Lorg/telegram/utils/proxy/WebProxyTransport;->-$$Nest$mfailWebView(Lorg/telegram/utils/proxy/WebProxyTransport;Landroid/webkit/WebView;)V

    :cond_0
    return-void
.end method

.method public onReceivedSslError(Landroid/webkit/WebView;Landroid/webkit/SslErrorHandler;Landroid/net/http/SslError;)V
    .locals 0

    .line 468
    invoke-virtual {p2}, Landroid/webkit/SslErrorHandler;->cancel()V

    .line 469
    iget-object p0, p0, Lorg/telegram/utils/proxy/WebProxyTransport$1;->this$0:Lorg/telegram/utils/proxy/WebProxyTransport;

    invoke-static {p0, p1}, Lorg/telegram/utils/proxy/WebProxyTransport;->-$$Nest$mfailWebView(Lorg/telegram/utils/proxy/WebProxyTransport;Landroid/webkit/WebView;)V

    return-void
.end method

.method public onRenderProcessGone(Landroid/webkit/WebView;Landroid/webkit/RenderProcessGoneDetail;)Z
    .locals 0

    .line 488
    iget-object p0, p0, Lorg/telegram/utils/proxy/WebProxyTransport$1;->this$0:Lorg/telegram/utils/proxy/WebProxyTransport;

    invoke-static {p0, p1}, Lorg/telegram/utils/proxy/WebProxyTransport;->-$$Nest$mfailWebView(Lorg/telegram/utils/proxy/WebProxyTransport;Landroid/webkit/WebView;)V

    const/4 p0, 0x1

    return p0
.end method

.method public shouldInterceptRequest(Landroid/webkit/WebView;Landroid/webkit/WebResourceRequest;)Landroid/webkit/WebResourceResponse;
    .locals 1

    .line 457
    invoke-interface {p2}, Landroid/webkit/WebResourceRequest;->getUrl()Landroid/net/Uri;

    move-result-object p1

    .line 458
    const-string p2, "http"

    invoke-virtual {p1}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p2, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result p2

    if-nez p2, :cond_0

    const-string p2, "https"

    invoke-virtual {p1}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p2, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result p2

    if-eqz p2, :cond_1

    :cond_0
    iget-object p0, p0, Lorg/telegram/utils/proxy/WebProxyTransport$1;->this$0:Lorg/telegram/utils/proxy/WebProxyTransport;

    .line 459
    invoke-static {p0, p1}, Lorg/telegram/utils/proxy/WebProxyTransport;->-$$Nest$misAllowedNetworkRequest(Lorg/telegram/utils/proxy/WebProxyTransport;Landroid/net/Uri;)Z

    move-result p0

    if-nez p0, :cond_1

    .line 460
    new-instance p0, Landroid/webkit/WebResourceResponse;

    new-instance p1, Ljava/io/ByteArrayInputStream;

    const/4 p2, 0x0

    new-array p2, p2, [B

    invoke-direct {p1, p2}, Ljava/io/ByteArrayInputStream;-><init>([B)V

    const-string p2, "text/plain"

    const-string v0, "UTF-8"

    invoke-direct {p0, p2, v0, p1}, Landroid/webkit/WebResourceResponse;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/io/InputStream;)V

    return-object p0

    :cond_1
    const/4 p0, 0x0

    return-object p0
.end method

.method public shouldOverrideUrlLoading(Landroid/webkit/WebView;Landroid/webkit/WebResourceRequest;)Z
    .locals 0

    .line 452
    invoke-interface {p2}, Landroid/webkit/WebResourceRequest;->isForMainFrame()Z

    move-result p1

    if-eqz p1, :cond_1

    iget-object p0, p0, Lorg/telegram/utils/proxy/WebProxyTransport$1;->this$0:Lorg/telegram/utils/proxy/WebProxyTransport;

    invoke-interface {p2}, Landroid/webkit/WebResourceRequest;->getUrl()Landroid/net/Uri;

    move-result-object p1

    invoke-static {p0, p1}, Lorg/telegram/utils/proxy/WebProxyTransport;->-$$Nest$misBridgeNavigation(Lorg/telegram/utils/proxy/WebProxyTransport;Landroid/net/Uri;)Z

    move-result p0

    if-nez p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x1

    return p0
.end method
