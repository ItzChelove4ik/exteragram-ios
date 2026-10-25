.class Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;
.super Landroid/webkit/WebViewClient;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/components/ReverseImageSearchSheet;-><init>(Landroid/content/Context;Ljava/io/File;Lcom/exteragram/messenger/components/ReverseImageSearchSheet$Provider;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/exteragram/messenger/components/ReverseImageSearchSheet;

.field final synthetic val$provider:Lcom/exteragram/messenger/components/ReverseImageSearchSheet$Provider;


# direct methods
.method public constructor <init>(Lcom/exteragram/messenger/components/ReverseImageSearchSheet;Lcom/exteragram/messenger/components/ReverseImageSearchSheet$Provider;)V
    .locals 0

    .line 118
    iput-object p1, p0, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;->this$0:Lcom/exteragram/messenger/components/ReverseImageSearchSheet;

    iput-object p2, p0, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;->val$provider:Lcom/exteragram/messenger/components/ReverseImageSearchSheet$Provider;

    invoke-direct {p0}, Landroid/webkit/WebViewClient;-><init>()V

    return-void
.end method


# virtual methods
.method public doUpdateVisitedHistory(Landroid/webkit/WebView;Ljava/lang/String;Z)V
    .locals 0

    .line 174
    invoke-super {p0, p1, p2, p3}, Landroid/webkit/WebViewClient;->doUpdateVisitedHistory(Landroid/webkit/WebView;Ljava/lang/String;Z)V

    .line 175
    iget-object p3, p0, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;->this$0:Lcom/exteragram/messenger/components/ReverseImageSearchSheet;

    invoke-static {p3, p1}, Lcom/exteragram/messenger/components/ReverseImageSearchSheet;->-$$Nest$mhideProviderAds(Lcom/exteragram/messenger/components/ReverseImageSearchSheet;Landroid/webkit/WebView;)V

    .line 176
    iget-object p1, p0, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;->this$0:Lcom/exteragram/messenger/components/ReverseImageSearchSheet;

    invoke-static {p1, p2}, Lcom/exteragram/messenger/components/ReverseImageSearchSheet;->-$$Nest$monUrlChanged(Lcom/exteragram/messenger/components/ReverseImageSearchSheet;Ljava/lang/String;)V

    .line 177
    iget-object p1, p0, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;->val$provider:Lcom/exteragram/messenger/components/ReverseImageSearchSheet$Provider;

    sget-object p3, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$Provider;->TINEYE:Lcom/exteragram/messenger/components/ReverseImageSearchSheet$Provider;

    if-ne p1, p3, :cond_0

    iget-object p1, p0, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;->this$0:Lcom/exteragram/messenger/components/ReverseImageSearchSheet;

    invoke-static {p1}, Lcom/exteragram/messenger/components/ReverseImageSearchSheet;->-$$Nest$fgetuploadInjected(Lcom/exteragram/messenger/components/ReverseImageSearchSheet;)Z

    move-result p1

    if-eqz p1, :cond_0

    if-eqz p2, :cond_0

    .line 180
    :try_start_0
    invoke-static {p2}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object p1

    invoke-virtual {p1}, Landroid/net/Uri;->getPath()Ljava/lang/String;

    move-result-object p1
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    const/4 p1, 0x0

    :goto_0
    if-eqz p1, :cond_0

    .line 184
    const-string p2, "/search"

    invoke-virtual {p1, p2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p1

    if-eqz p1, :cond_0

    .line 185
    iget-object p0, p0, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;->this$0:Lcom/exteragram/messenger/components/ReverseImageSearchSheet;

    invoke-static {p0}, Lcom/exteragram/messenger/components/ReverseImageSearchSheet;->-$$Nest$mreveal(Lcom/exteragram/messenger/components/ReverseImageSearchSheet;)V

    :cond_0
    return-void
.end method

.method public onPageFinished(Landroid/webkit/WebView;Ljava/lang/String;)V
    .locals 2

    .line 154
    invoke-super {p0, p1, p2}, Landroid/webkit/WebViewClient;->onPageFinished(Landroid/webkit/WebView;Ljava/lang/String;)V

    .line 155
    iget-object v0, p0, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;->this$0:Lcom/exteragram/messenger/components/ReverseImageSearchSheet;

    invoke-static {v0, p2}, Lcom/exteragram/messenger/components/ReverseImageSearchSheet;->-$$Nest$monUrlChanged(Lcom/exteragram/messenger/components/ReverseImageSearchSheet;Ljava/lang/String;)V

    .line 156
    iget-object v0, p0, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;->this$0:Lcom/exteragram/messenger/components/ReverseImageSearchSheet;

    invoke-static {v0}, Lcom/exteragram/messenger/components/ReverseImageSearchSheet;->-$$Nest$fgetuploadInjected(Lcom/exteragram/messenger/components/ReverseImageSearchSheet;)Z

    move-result v0

    if-nez v0, :cond_1

    if-eqz p2, :cond_2

    .line 157
    const-string v0, "http://"

    invoke-virtual {p2, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_0

    const-string v0, "https://"

    invoke-virtual {p2, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 158
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;->this$0:Lcom/exteragram/messenger/components/ReverseImageSearchSheet;

    const/4 v1, 0x1

    invoke-static {v0, v1}, Lcom/exteragram/messenger/components/ReverseImageSearchSheet;->-$$Nest$fputuploadInjected(Lcom/exteragram/messenger/components/ReverseImageSearchSheet;Z)V

    .line 159
    iget-object v0, p0, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;->this$0:Lcom/exteragram/messenger/components/ReverseImageSearchSheet;

    invoke-static {v0}, Lcom/exteragram/messenger/components/ReverseImageSearchSheet;->-$$Nest$fgetpageStartCount(Lcom/exteragram/messenger/components/ReverseImageSearchSheet;)I

    move-result v1

    invoke-static {v0, v1}, Lcom/exteragram/messenger/components/ReverseImageSearchSheet;->-$$Nest$fputinjectedAtStartCount(Lcom/exteragram/messenger/components/ReverseImageSearchSheet;I)V

    .line 160
    iget-object v0, p0, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;->this$0:Lcom/exteragram/messenger/components/ReverseImageSearchSheet;

    invoke-static {v0}, Lcom/exteragram/messenger/components/ReverseImageSearchSheet;->-$$Nest$fgetpendingScript(Lcom/exteragram/messenger/components/ReverseImageSearchSheet;)Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_2

    .line 161
    iget-object v0, p0, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;->this$0:Lcom/exteragram/messenger/components/ReverseImageSearchSheet;

    invoke-static {v0}, Lcom/exteragram/messenger/components/ReverseImageSearchSheet;->-$$Nest$fgetpendingScript(Lcom/exteragram/messenger/components/ReverseImageSearchSheet;)Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {p1, v0, v1}, Landroid/webkit/WebView;->evaluateJavascript(Ljava/lang/String;Landroid/webkit/ValueCallback;)V

    .line 162
    iget-object v0, p0, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;->this$0:Lcom/exteragram/messenger/components/ReverseImageSearchSheet;

    invoke-static {v0, v1}, Lcom/exteragram/messenger/components/ReverseImageSearchSheet;->-$$Nest$fputpendingScript(Lcom/exteragram/messenger/components/ReverseImageSearchSheet;Ljava/lang/String;)V

    goto :goto_0

    .line 165
    :cond_1
    iget-object v0, p0, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;->this$0:Lcom/exteragram/messenger/components/ReverseImageSearchSheet;

    invoke-static {v0}, Lcom/exteragram/messenger/components/ReverseImageSearchSheet;->-$$Nest$fgetpageStartCount(Lcom/exteragram/messenger/components/ReverseImageSearchSheet;)I

    move-result v0

    iget-object v1, p0, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;->this$0:Lcom/exteragram/messenger/components/ReverseImageSearchSheet;

    invoke-static {v1}, Lcom/exteragram/messenger/components/ReverseImageSearchSheet;->-$$Nest$fgetinjectedAtStartCount(Lcom/exteragram/messenger/components/ReverseImageSearchSheet;)I

    move-result v1

    if-le v0, v1, :cond_2

    .line 166
    iget-object v0, p0, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;->this$0:Lcom/exteragram/messenger/components/ReverseImageSearchSheet;

    invoke-static {v0}, Lcom/exteragram/messenger/components/ReverseImageSearchSheet;->-$$Nest$mreveal(Lcom/exteragram/messenger/components/ReverseImageSearchSheet;)V

    .line 168
    :cond_2
    :goto_0
    iget-object v0, p0, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;->this$0:Lcom/exteragram/messenger/components/ReverseImageSearchSheet;

    invoke-static {v0}, Lcom/exteragram/messenger/components/ReverseImageSearchSheet;->-$$Nest$fgetadBlocker(Lcom/exteragram/messenger/components/ReverseImageSearchSheet;)Lcom/exteragram/messenger/adblock/WebAdBlocker;

    move-result-object v0

    invoke-virtual {v0, p2}, Lcom/exteragram/messenger/adblock/WebAdBlocker;->injectCosmetics(Ljava/lang/String;)V

    .line 169
    iget-object p0, p0, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;->this$0:Lcom/exteragram/messenger/components/ReverseImageSearchSheet;

    invoke-static {p0, p1}, Lcom/exteragram/messenger/components/ReverseImageSearchSheet;->-$$Nest$mhideProviderAds(Lcom/exteragram/messenger/components/ReverseImageSearchSheet;Landroid/webkit/WebView;)V

    return-void
.end method

.method public onPageStarted(Landroid/webkit/WebView;Ljava/lang/String;Landroid/graphics/Bitmap;)V
    .locals 0

    .line 146
    invoke-super {p0, p1, p2, p3}, Landroid/webkit/WebViewClient;->onPageStarted(Landroid/webkit/WebView;Ljava/lang/String;Landroid/graphics/Bitmap;)V

    .line 147
    iget-object p1, p0, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;->this$0:Lcom/exteragram/messenger/components/ReverseImageSearchSheet;

    invoke-static {p1}, Lcom/exteragram/messenger/components/ReverseImageSearchSheet;->-$$Nest$fgetpageStartCount(Lcom/exteragram/messenger/components/ReverseImageSearchSheet;)I

    move-result p3

    add-int/lit8 p3, p3, 0x1

    invoke-static {p1, p3}, Lcom/exteragram/messenger/components/ReverseImageSearchSheet;->-$$Nest$fputpageStartCount(Lcom/exteragram/messenger/components/ReverseImageSearchSheet;I)V

    .line 148
    iget-object p1, p0, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;->this$0:Lcom/exteragram/messenger/components/ReverseImageSearchSheet;

    invoke-static {p1, p2}, Lcom/exteragram/messenger/components/ReverseImageSearchSheet;->-$$Nest$monUrlChanged(Lcom/exteragram/messenger/components/ReverseImageSearchSheet;Ljava/lang/String;)V

    .line 149
    iget-object p0, p0, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;->this$0:Lcom/exteragram/messenger/components/ReverseImageSearchSheet;

    invoke-static {p0}, Lcom/exteragram/messenger/components/ReverseImageSearchSheet;->-$$Nest$fgetadBlocker(Lcom/exteragram/messenger/components/ReverseImageSearchSheet;)Lcom/exteragram/messenger/adblock/WebAdBlocker;

    move-result-object p0

    invoke-virtual {p0, p2}, Lcom/exteragram/messenger/adblock/WebAdBlocker;->onPageStarted(Ljava/lang/String;)V

    return-void
.end method

.method public shouldInterceptRequest(Landroid/webkit/WebView;Landroid/webkit/WebResourceRequest;)Landroid/webkit/WebResourceResponse;
    .locals 1

    if-eqz p2, :cond_0

    .line 121
    invoke-interface {p2}, Landroid/webkit/WebResourceRequest;->isForMainFrame()Z

    move-result v0

    if-nez v0, :cond_0

    .line 122
    iget-object v0, p0, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;->this$0:Lcom/exteragram/messenger/components/ReverseImageSearchSheet;

    invoke-static {v0}, Lcom/exteragram/messenger/components/ReverseImageSearchSheet;->-$$Nest$fgetadBlocker(Lcom/exteragram/messenger/components/ReverseImageSearchSheet;)Lcom/exteragram/messenger/adblock/WebAdBlocker;

    move-result-object v0

    invoke-virtual {v0, p2}, Lcom/exteragram/messenger/adblock/WebAdBlocker;->interceptRequest(Landroid/webkit/WebResourceRequest;)Landroid/webkit/WebResourceResponse;

    move-result-object v0

    if-eqz v0, :cond_0

    return-object v0

    .line 127
    :cond_0
    invoke-super {p0, p1, p2}, Landroid/webkit/WebViewClient;->shouldInterceptRequest(Landroid/webkit/WebView;Landroid/webkit/WebResourceRequest;)Landroid/webkit/WebResourceResponse;

    move-result-object p0

    return-object p0
.end method

.method public shouldOverrideUrlLoading(Landroid/webkit/WebView;Landroid/webkit/WebResourceRequest;)Z
    .locals 2

    const/4 p1, 0x0

    if-eqz p2, :cond_2

    .line 132
    invoke-interface {p2}, Landroid/webkit/WebResourceRequest;->isForMainFrame()Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_1

    .line 135
    :cond_0
    invoke-interface {p2}, Landroid/webkit/WebResourceRequest;->getUrl()Landroid/net/Uri;

    move-result-object p2

    if-eqz p2, :cond_1

    .line 136
    invoke-virtual {p2}, Landroid/net/Uri;->getHost()Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    :goto_0
    if-eqz v0, :cond_2

    .line 137
    iget-object v1, p0, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;->val$provider:Lcom/exteragram/messenger/components/ReverseImageSearchSheet$Provider;

    invoke-static {v1, v0}, Lcom/exteragram/messenger/components/ReverseImageSearchSheet;->-$$Nest$smisProviderHost(Lcom/exteragram/messenger/components/ReverseImageSearchSheet$Provider;Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_2

    .line 138
    iget-object p0, p0, Lcom/exteragram/messenger/components/ReverseImageSearchSheet$2;->this$0:Lcom/exteragram/messenger/components/ReverseImageSearchSheet;

    invoke-virtual {p0}, Landroid/app/Dialog;->getContext()Landroid/content/Context;

    move-result-object p0

    invoke-virtual {p2}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p0, p1}, Lorg/telegram/messenger/browser/Browser;->openUrlInSystemBrowser(Landroid/content/Context;Ljava/lang/String;)V

    const/4 p0, 0x1

    return p0

    :cond_2
    :goto_1
    return p1
.end method
