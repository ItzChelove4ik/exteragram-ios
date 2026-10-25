package com.exteragram.messenger.adblock;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import androidx.annotation.Keep;
import com.exteragram.messenger.adblock.AdBlockClient;
import com.exteragram.messenger.adblock.backend.AdBlockManager;
import com.exteragram.messenger.adblock.data.BlockResult;
import com.exteragram.messenger.adblock.interop.AdBlock;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;
import org.telegram.messenger.Utilities;

/* loaded from: classes4.dex */
public class WebAdBlocker {
    private volatile boolean allowBlockedPage;
    private final AtomicInteger blockedCount;
    private final String bridgeName;
    private volatile AdBlockClient.CosmeticHide cosmeticHide;
    private volatile boolean navigationPending;
    private volatile boolean pageBlocked;
    private volatile String pageUrl;
    private volatile int previousBlockedCount;
    private volatile String previousPageUrl;
    private final WebView webView;

    public WebAdBlocker(WebView r4) {
        this.blockedCount = new AtomicInteger();
        this.webView = r4;
        String r0 = "_" + Long.toHexString(Utilities.fastRandom.nextLong());
        this.bridgeName = r0;
        r4.addJavascriptInterface(this, r0);
        AdBlockManager.initialize();
    }

    private static String[] split(String r1) {
        if (TextUtils.isEmpty(r1) == false) goto L7;
        return new String[0];
    L7:
        return r1.split(" ");
    }

    public void allowBlockedPage() {
        this.allowBlockedPage = true;
    }

    public boolean consumePageBlocked() {
        boolean r0 = this.pageBlocked;
        this.pageBlocked = false;
        return r0;
    }

    public int getBlockedCount() {
        return this.blockedCount.get();
    }

    public void injectCosmetics(String r7) {
        if (r7 != null) goto L4;
        return;
    L4:
        if (r7.startsWith("http://") == true) goto L8;
        if (r7.startsWith("https://") == true) goto L8;
        return;
    L8:
        if (AdBlockManager.isActive() == false) goto L34;
        AdBlockClient.CosmeticHide r0 = this.cosmeticHide;
        if (r0 != null) goto L13;
    L15:
        AdBlockClient.CosmeticHide r72 = AdBlockClient.getCosmeticHide(r7);
        if (r72 == null) goto L36;
        this.cosmeticHide = r72;
        String r02 = r72.getHideCss();
        if (r02 == null) goto L21;
    L22:
        WebView r2 = this.webView;
        String r3 = "(function() {\n    var name = '$BRIDGE';\n    var bridge = window[name];\n    if (!bridge || window[name + '_c']) return;\n    Object.defineProperty(window, name + '_c', {value: true});\n    function addStyle(css) {\n        if (!css) return;\n        var style = document.createElement('style');\n        style.textContent = css;\n        (document.head || document.documentElement).appendChild(style);\n    }\n    addStyle($CSS);\n    if (!$OBSERVE) return;\n    var seenClasses = new Set(), seenIds = new Set(), classes = [], ids = [], scheduled = false;\n    var delay = window.setTimeout.bind(window);\n    function collect(element) {\n        var list = element.classList;\n        if (list) {\n            for (var i = 0; i < list.length; i++) {\n                if (!seenClasses.has(list[i])) {\n                    seenClasses.add(list[i]);\n                    classes.push(list[i]);\n                }\n            }\n        }\n        var id = element.id;\n        if (typeof id === 'string' && id && !seenIds.has(id)) {\n            seenIds.add(id);\n            ids.push(id);\n        }\n    }\n    function collectTree(node) {\n        if (!node || node.nodeType !== 1) return;\n        collect(node);\n        var elements = node.querySelectorAll('[class],[id]');\n        for (var i = 0; i < elements.length; i++) collect(elements[i]);\n    }\n    function flush() {\n        scheduled = false;\n        if (!classes.length && !ids.length) return;\n        var found = bridge.onElementsFound(classes.join(' '), ids.join(' '));\n        classes = [];\n        ids = [];\n        addStyle(found);\n    }\n    new MutationObserver(function(mutations) {\n        for (var i = 0; i < mutations.length; i++) {\n            var mutation = mutations[i];\n            if (mutation.type === 'attributes') {\n                collect(mutation.target);\n            } else {\n                for (var j = 0; j < mutation.addedNodes.length; j++) collectTree(mutation.addedNodes[j]);\n            }\n        }\n        if (!scheduled && (classes.length || ids.length)) {\n            scheduled = true;\n            delay(flush, 100);\n        }\n    }).observe(document, {childList: true, subtree: true, attributes: true, attributeFilter: ['class', 'id']});\n    collectTree(document.documentElement);\n    flush();\n})();\n".replace("$BRIDGE", this.bridgeName).replace("$OBSERVE", String.valueOf(!r72.isGenericHide()));
        if (r02 == null) goto L25;
        String r03 = JSONObject.quote(r02);
    L26:
        r2.evaluateJavascript(r3.replace("$CSS", r03), null);
    L27:
        String r73 = r72.getInjectedScript();
        if (TextUtils.isEmpty(r73) == true) goto L33;
        String r04 = "'" + this.bridgeName + "_s'";
        this.webView.evaluateJavascript("if (!window[" + r04 + "]) {\nObject.defineProperty(window, " + r04 + ", {value: true});\n" + r73 + "\n}", null);
        return;
    L33:
        return;
    L25:
        r03 = "null";
        goto L26
    L21:
        if (r72.isGenericHide() == true) goto L27;
    L36:
        return;
    L13:
        if (TextUtils.equals(r0.getUrl(), r7) == false) goto L15;
        return;
    }

    public WebResourceResponse interceptRequest(WebResourceRequest r13) {
        String r0 = r13.getUrl().toString();
        if (r13.isForMainFrame() == false) goto L19;
        if (this.navigationPending == true) goto L7;
        this.navigationPending = true;
        this.previousPageUrl = this.pageUrl;
        this.previousBlockedCount = this.blockedCount.getAndSet(0);
    L7:
        this.pageUrl = r0;
        boolean r1 = this.allowBlockedPage;
        this.allowBlockedPage = false;
        if (r1 == false) goto L10;
    L17:
        return null;
    L10:
        if (AdBlockManager.isActive() == false) goto L17;
        BlockResult r132 = AdBlockClient.isAdRequest(r13, r0);
        if (r132 == null) goto L17;
        if (r132.isMatched() == false) goto L17;
        this.pageBlocked = true;
        return new WebResourceResponse("plain/text", "utf-8", 590, "Page blocked", null, null);
    L19:
        if (AdBlockManager.isActive() == true) goto L21;
        return null;
    L21:
        String r12 = this.pageUrl;
        if (TextUtils.isEmpty(r12) == false) goto L24;
        r12 = r0;
    L24:
        String r133 = AdBlockClient.getRequestType(r13, r12);
        BlockResult r02 = AdBlock.getBlockResult(r0, r12, r133);
        if (r02 != null) goto L27;
    L31:
        return null;
    L27:
        if (r02.isMatched() == false) goto L31;
        this.blockedCount.incrementAndGet();
        return AdBlockClient.createBlockedResponse(r133, r02);
    }

    public void onDownloadStart() {
        if (this.navigationPending == false) goto L6;
        this.navigationPending = false;
        this.pageUrl = this.previousPageUrl;
        this.previousPageUrl = null;
        this.blockedCount.addAndGet(this.previousBlockedCount);
        return;
    }

    @JavascriptInterface
    @Keep
    public String onElementsFound(String r2, String r3) {
        AdBlockClient.CosmeticHide r1 = this.cosmeticHide;
        if (r1 != null) goto L5;
        return null;
    L5:
        if (r1.isGenericHide() == false) goto L7;
        return null;
    L7:
        if (AdBlockManager.isActive() == true) goto L10;
        return null;
    L10:
        return AdBlockClient.getHiddenSelectorsCss(r1, split(r2), split(r3));
    }

    public void onPageStarted(String r2) {
        this.navigationPending = false;
        this.previousPageUrl = null;
        this.pageUrl = r2;
        this.cosmeticHide = null;
    }

    public void reload() {
        this.webView.reload();
    }
}
