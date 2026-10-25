.class public Lcom/exteragram/messenger/adblock/WebAdBlocker;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private volatile allowBlockedPage:Z

.field private final blockedCount:Ljava/util/concurrent/atomic/AtomicInteger;

.field private final bridgeName:Ljava/lang/String;

.field private volatile cosmeticHide:Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;

.field private volatile navigationPending:Z

.field private volatile pageBlocked:Z

.field private volatile pageUrl:Ljava/lang/String;

.field private volatile previousBlockedCount:I

.field private volatile previousPageUrl:Ljava/lang/String;

.field private final webView:Landroid/webkit/WebView;


# direct methods
.method public constructor <init>(Landroid/webkit/WebView;)V
    .locals 3

    .line 98
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 89
    new-instance v0, Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-direct {v0}, Ljava/util/concurrent/atomic/AtomicInteger;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->blockedCount:Ljava/util/concurrent/atomic/AtomicInteger;

    .line 99
    iput-object p1, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->webView:Landroid/webkit/WebView;

    .line 100
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "_"

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    sget-object v1, Lorg/telegram/messenger/Utilities;->fastRandom:Ljava/util/Random;

    invoke-virtual {v1}, Ljava/util/Random;->nextLong()J

    move-result-wide v1

    invoke-static {v1, v2}, Ljava/lang/Long;->toHexString(J)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->bridgeName:Ljava/lang/String;

    .line 101
    invoke-virtual {p1, p0, v0}, Landroid/webkit/WebView;->addJavascriptInterface(Ljava/lang/Object;Ljava/lang/String;)V

    .line 102
    invoke-static {}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->initialize()V

    return-void
.end method

.method private static split(Ljava/lang/String;)[Ljava/lang/String;
    .locals 1

    .line 211
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 p0, 0x0

    new-array p0, p0, [Ljava/lang/String;

    return-object p0

    :cond_0
    const-string v0, " "

    invoke-virtual {p0, v0}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public allowBlockedPage()V
    .locals 1

    const/4 v0, 0x1

    .line 110
    iput-boolean v0, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->allowBlockedPage:Z

    return-void
.end method

.method public consumePageBlocked()Z
    .locals 2

    .line 114
    iget-boolean v0, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->pageBlocked:Z

    const/4 v1, 0x0

    .line 115
    iput-boolean v1, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->pageBlocked:Z

    return v0
.end method

.method public getBlockedCount()I
    .locals 0

    .line 106
    iget-object p0, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->blockedCount:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {p0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result p0

    return p0
.end method

.method public injectCosmetics(Ljava/lang/String;)V
    .locals 6

    if-eqz p1, :cond_7

    .line 174
    const-string v0, "http://"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_0

    const-string v0, "https://"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_7

    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->isActive()Z

    move-result v0

    if-nez v0, :cond_1

    goto/16 :goto_1

    .line 177
    :cond_1
    iget-object v0, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->cosmeticHide:Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;

    if-eqz v0, :cond_2

    .line 178
    invoke-virtual {v0}, Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;->getUrl()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0, p1}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_2

    goto/16 :goto_1

    .line 181
    :cond_2
    invoke-static {p1}, Lcom/exteragram/messenger/adblock/AdBlockClient;->getCosmeticHide(Ljava/lang/String;)Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;

    move-result-object p1

    if-nez p1, :cond_3

    goto/16 :goto_1

    .line 185
    :cond_3
    iput-object p1, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->cosmeticHide:Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;

    .line 186
    invoke-virtual {p1}, Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;->getHideCss()Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x0

    if-nez v0, :cond_4

    .line 187
    invoke-virtual {p1}, Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;->isGenericHide()Z

    move-result v2

    if-nez v2, :cond_6

    .line 188
    :cond_4
    iget-object v2, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->webView:Landroid/webkit/WebView;

    const-string v3, "$BRIDGE"

    iget-object v4, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->bridgeName:Ljava/lang/String;

    .line 189
    const-string v5, "(function() {\n    var name = \'$BRIDGE\';\n    var bridge = window[name];\n    if (!bridge || window[name + \'_c\']) return;\n    Object.defineProperty(window, name + \'_c\', {value: true});\n    function addStyle(css) {\n        if (!css) return;\n        var style = document.createElement(\'style\');\n        style.textContent = css;\n        (document.head || document.documentElement).appendChild(style);\n    }\n    addStyle($CSS);\n    if (!$OBSERVE) return;\n    var seenClasses = new Set(), seenIds = new Set(), classes = [], ids = [], scheduled = false;\n    var delay = window.setTimeout.bind(window);\n    function collect(element) {\n        var list = element.classList;\n        if (list) {\n            for (var i = 0; i < list.length; i++) {\n                if (!seenClasses.has(list[i])) {\n                    seenClasses.add(list[i]);\n                    classes.push(list[i]);\n                }\n            }\n        }\n        var id = element.id;\n        if (typeof id === \'string\' && id && !seenIds.has(id)) {\n            seenIds.add(id);\n            ids.push(id);\n        }\n    }\n    function collectTree(node) {\n        if (!node || node.nodeType !== 1) return;\n        collect(node);\n        var elements = node.querySelectorAll(\'[class],[id]\');\n        for (var i = 0; i < elements.length; i++) collect(elements[i]);\n    }\n    function flush() {\n        scheduled = false;\n        if (!classes.length && !ids.length) return;\n        var found = bridge.onElementsFound(classes.join(\' \'), ids.join(\' \'));\n        classes = [];\n        ids = [];\n        addStyle(found);\n    }\n    new MutationObserver(function(mutations) {\n        for (var i = 0; i < mutations.length; i++) {\n            var mutation = mutations[i];\n            if (mutation.type === \'attributes\') {\n                collect(mutation.target);\n            } else {\n                for (var j = 0; j < mutation.addedNodes.length; j++) collectTree(mutation.addedNodes[j]);\n            }\n        }\n        if (!scheduled && (classes.length || ids.length)) {\n            scheduled = true;\n            delay(flush, 100);\n        }\n    }).observe(document, {childList: true, subtree: true, attributes: true, attributeFilter: [\'class\', \'id\']});\n    collectTree(document.documentElement);\n    flush();\n})();\n"

    invoke-virtual {v5, v3, v4}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v3

    .line 190
    invoke-virtual {p1}, Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;->isGenericHide()Z

    move-result v4

    xor-int/lit8 v4, v4, 0x1

    invoke-static {v4}, Ljava/lang/String;->valueOf(Z)Ljava/lang/String;

    move-result-object v4

    const-string v5, "$OBSERVE"

    invoke-virtual {v3, v5, v4}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v3

    if-eqz v0, :cond_5

    .line 191
    invoke-static {v0}, Lorg/json/JSONObject;->quote(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    :cond_5
    const-string v0, "null"

    :goto_0
    const-string v4, "$CSS"

    invoke-virtual {v3, v4, v0}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v0

    .line 188
    invoke-virtual {v2, v0, v1}, Landroid/webkit/WebView;->evaluateJavascript(Ljava/lang/String;Landroid/webkit/ValueCallback;)V

    .line 193
    :cond_6
    invoke-virtual {p1}, Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;->getInjectedScript()Ljava/lang/String;

    move-result-object p1

    .line 194
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_7

    .line 195
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v2, "\'"

    invoke-direct {v0, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v2, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->bridgeName:Ljava/lang/String;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "_s\'"

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 196
    iget-object p0, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->webView:Landroid/webkit/WebView;

    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "if (!window["

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "]) {\nObject.defineProperty(window, "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ", {value: true});\n"

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "\n}"

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1, v1}, Landroid/webkit/WebView;->evaluateJavascript(Ljava/lang/String;Landroid/webkit/ValueCallback;)V

    :cond_7
    :goto_1
    return-void
.end method

.method public interceptRequest(Landroid/webkit/WebResourceRequest;)Landroid/webkit/WebResourceResponse;
    .locals 12

    .line 124
    invoke-interface {p1}, Landroid/webkit/WebResourceRequest;->getUrl()Landroid/net/Uri;

    move-result-object v0

    invoke-virtual {v0}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v0

    .line 125
    invoke-interface {p1}, Landroid/webkit/WebResourceRequest;->isForMainFrame()Z

    move-result v1

    const/4 v2, 0x0

    if-eqz v1, :cond_2

    .line 126
    iget-boolean v1, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->navigationPending:Z

    const/4 v3, 0x0

    const/4 v4, 0x1

    if-nez v1, :cond_0

    .line 127
    iput-boolean v4, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->navigationPending:Z

    .line 128
    iget-object v1, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->pageUrl:Ljava/lang/String;

    iput-object v1, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->previousPageUrl:Ljava/lang/String;

    .line 129
    iget-object v1, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->blockedCount:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v1, v3}, Ljava/util/concurrent/atomic/AtomicInteger;->getAndSet(I)I

    move-result v1

    iput v1, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->previousBlockedCount:I

    .line 131
    :cond_0
    iput-object v0, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->pageUrl:Ljava/lang/String;

    .line 132
    iget-boolean v1, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->allowBlockedPage:Z

    .line 133
    iput-boolean v3, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->allowBlockedPage:Z

    if-nez v1, :cond_1

    .line 134
    invoke-static {}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->isActive()Z

    move-result v1

    if-eqz v1, :cond_1

    .line 135
    invoke-static {p1, v0}, Lcom/exteragram/messenger/adblock/AdBlockClient;->isAdRequest(Landroid/webkit/WebResourceRequest;Ljava/lang/String;)Lcom/exteragram/messenger/adblock/data/BlockResult;

    move-result-object p1

    if-eqz p1, :cond_1

    .line 136
    invoke-virtual {p1}, Lcom/exteragram/messenger/adblock/data/BlockResult;->isMatched()Z

    move-result p1

    if-eqz p1, :cond_1

    .line 137
    iput-boolean v4, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->pageBlocked:Z

    .line 138
    new-instance v5, Landroid/webkit/WebResourceResponse;

    const/4 v10, 0x0

    const/4 v11, 0x0

    const-string v6, "plain/text"

    const-string v7, "utf-8"

    const/16 v8, 0x24e

    const-string v9, "Page blocked"

    invoke-direct/range {v5 .. v11}, Landroid/webkit/WebResourceResponse;-><init>(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/util/Map;Ljava/io/InputStream;)V

    return-object v5

    :cond_1
    return-object v2

    .line 143
    :cond_2
    invoke-static {}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->isActive()Z

    move-result v1

    if-nez v1, :cond_3

    return-object v2

    .line 146
    :cond_3
    iget-object v1, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->pageUrl:Ljava/lang/String;

    .line 147
    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3

    if-eqz v3, :cond_4

    move-object v1, v0

    .line 148
    :cond_4
    invoke-static {p1, v1}, Lcom/exteragram/messenger/adblock/AdBlockClient;->getRequestType(Landroid/webkit/WebResourceRequest;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    .line 149
    invoke-static {v0, v1, p1}, Lcom/exteragram/messenger/adblock/interop/AdBlock;->getBlockResult(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/exteragram/messenger/adblock/data/BlockResult;

    move-result-object v0

    if-eqz v0, :cond_6

    .line 150
    invoke-virtual {v0}, Lcom/exteragram/messenger/adblock/data/BlockResult;->isMatched()Z

    move-result v1

    if-nez v1, :cond_5

    goto :goto_0

    .line 153
    :cond_5
    iget-object p0, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->blockedCount:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {p0}, Ljava/util/concurrent/atomic/AtomicInteger;->incrementAndGet()I

    .line 154
    invoke-static {p1, v0}, Lcom/exteragram/messenger/adblock/AdBlockClient;->createBlockedResponse(Ljava/lang/String;Lcom/exteragram/messenger/adblock/data/BlockResult;)Landroid/webkit/WebResourceResponse;

    move-result-object p0

    return-object p0

    :cond_6
    :goto_0
    return-object v2
.end method

.method public onDownloadStart()V
    .locals 1

    .line 165
    iget-boolean v0, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->navigationPending:Z

    if-eqz v0, :cond_0

    const/4 v0, 0x0

    .line 166
    iput-boolean v0, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->navigationPending:Z

    .line 167
    iget-object v0, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->previousPageUrl:Ljava/lang/String;

    iput-object v0, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->pageUrl:Ljava/lang/String;

    const/4 v0, 0x0

    .line 168
    iput-object v0, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->previousPageUrl:Ljava/lang/String;

    .line 169
    iget-object v0, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->blockedCount:Ljava/util/concurrent/atomic/AtomicInteger;

    iget p0, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->previousBlockedCount:I

    invoke-virtual {v0, p0}, Ljava/util/concurrent/atomic/AtomicInteger;->addAndGet(I)I

    :cond_0
    return-void
.end method

.method public onElementsFound(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 1
    .annotation runtime Landroid/webkit/JavascriptInterface;
    .end annotation

    .annotation build Landroidx/annotation/Keep;
    .end annotation

    .line 203
    iget-object p0, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->cosmeticHide:Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;

    if-eqz p0, :cond_1

    .line 204
    invoke-virtual {p0}, Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;->isGenericHide()Z

    move-result v0

    if-nez v0, :cond_1

    invoke-static {}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->isActive()Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 207
    :cond_0
    invoke-static {p1}, Lcom/exteragram/messenger/adblock/WebAdBlocker;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object p1

    invoke-static {p2}, Lcom/exteragram/messenger/adblock/WebAdBlocker;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object p2

    invoke-static {p0, p1, p2}, Lcom/exteragram/messenger/adblock/AdBlockClient;->getHiddenSelectorsCss(Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;[Ljava/lang/String;[Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_1
    :goto_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public onPageStarted(Ljava/lang/String;)V
    .locals 1

    const/4 v0, 0x0

    .line 158
    iput-boolean v0, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->navigationPending:Z

    const/4 v0, 0x0

    .line 159
    iput-object v0, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->previousPageUrl:Ljava/lang/String;

    .line 160
    iput-object p1, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->pageUrl:Ljava/lang/String;

    .line 161
    iput-object v0, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->cosmeticHide:Lcom/exteragram/messenger/adblock/AdBlockClient$CosmeticHide;

    return-void
.end method

.method public reload()V
    .locals 0

    .line 120
    iget-object p0, p0, Lcom/exteragram/messenger/adblock/WebAdBlocker;->webView:Landroid/webkit/WebView;

    invoke-virtual {p0}, Landroid/webkit/WebView;->reload()V

    return-void
.end method
