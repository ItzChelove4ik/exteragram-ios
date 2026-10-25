package org.telegram.ui.web;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import androidx.annotation.Keep;
import com.google.android.gms.cast.MediaTrack;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.Timer;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.web.BotWebViewContainer;
import org.telegram.ui.web.MHTML;

/* loaded from: classes3.dex */
public class WebInstantView {
    public static final HashMap<TLRPC.WebPage, WebInstantView> instants = null;
    private static HashMap<String, ArrayList<Pair<ImageReceiver, Runnable>>> loadingPhotos;
    public final HashMap<String, Bitmap> loadedPhotos;
    public MHTML mhtml;
    public String url;
    public TLRPC.WebPage webpage;

    /* renamed from: org.telegram.ui.web.WebInstantView$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ WebInstantView this$0;

        public AnonymousClass1(WebInstantView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r1) {
            return false;
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r1, View r2, long r3) {
            return false;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(500.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(500.0f), TLObject.FLAG_30));
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r1) {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.web.WebInstantView$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 extends WebViewClient {
        private boolean firstLoad;
        private boolean streamLoaded;
        final /* synthetic */ WebInstantView this$0;
        final /* synthetic */ InputStream val$stream;

        public AnonymousClass2(WebInstantView r1, InputStream r2) {
            this.this$0 = r1;
            this.val$stream = r2;
            this.firstLoad = true;
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView r9, String r10) {
            if (this.firstLoad == false) goto L7;
            this.firstLoad = false;
            return new WebResourceResponse("text/html", "UTF-8", new ByteArrayInputStream(("<script>\n" + AndroidUtilities.readRes(R.raw.instant).replace("$DEBUG$", _UrlKt.FRAGMENT_ENCODE_SET + BuildVars.DEBUG_VERSION) + "\n</script>").getBytes(StandardCharsets.UTF_8)));
        L7:
            if (r10 != null) goto L9;
        L25:
            MHTML r8 = this.this$0.mhtml;
            if (r8 == null) goto L28;
            MHTML.Entry r82 = r8.entriesByLocation.get(r10);
        L29:
            if (r82 == null) goto L31;
            String r102 = r82.getType();
            if ("text/html".equalsIgnoreCase(r102) == false) goto L35;
        L47:
            InputStream r83 = r82.getInputStream();     // Catch: IOException -> L42
            String r0 = r102;
        L41:
            return new WebResourceResponse(r0, null, r83);
        L42:
            e = move-exception;
            FileLog.e(e);
            return new WebResourceResponse("text/plain", "utf-8", 503, "Server error", null, null);
        L35:
            if ("text/css".equalsIgnoreCase(r102) == true) goto L47;
            return new WebResourceResponse("text/plain", "utf-8", 404, "Not Found", null, null);
        L31:
            return new WebResourceResponse("text/plain", "utf-8", 404, "Not Found", null, null);
        L28:
            r82 = null;
            goto L29
        L9:
            if (r10.endsWith("/index.html") == false) goto L25;
            r0 = "application/octet-stream";
            if (this.streamLoaded == false) goto L24;
            MHTML r84 = this.this$0.mhtml;
            if (r84 == null) goto L15;
            MHTML.Entry r85 = r84.entries.get(0);
        L16:
            if (r85 == null) goto L18;
            r83 = r85.getInputStream();     // Catch: IOException -> L21
        L21:
            e = move-exception;
            FileLog.e(e);
            return new WebResourceResponse("text/plain", "utf-8", 503, "Server error", null, null);
        L18:
            return new WebResourceResponse("text/plain", "utf-8", 404, "Not Found", null, null);
        L15:
            r85 = null;
            goto L16
        L24:
            InputStream r103 = this.val$stream;
            this.streamLoaded = true;
            r83 = r103;
            goto L41
        }
    }

    /* renamed from: org.telegram.ui.web.WebInstantView$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends WebChromeClient {
        final /* synthetic */ WebInstantView this$0;

        public AnonymousClass3(WebInstantView r1) {
            this.this$0 = r1;
        }
    }

    /* renamed from: org.telegram.ui.web.WebInstantView$4, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass4 {
        final /* synthetic */ WebInstantView this$0;
        final /* synthetic */ boolean[] val$done;
        final /* synthetic */ WebView val$webView;
        final /* synthetic */ FrameLayout val$webViewContainer;
        final /* synthetic */ Utilities.Callback val$whenDone;

        public static /* synthetic */ void $r8$lambda$K36wTf6YNhOLbQXYDc9HGQ7ErYU(boolean[] r2, WebView r3, FrameLayout r4, String r5, Utilities.Callback r6) {
            if (r2[0] == false) goto L5;
            return;
        L5:
            r2[0] = true;
            if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L14;
            r3.onPause();
            r3.destroy();
            AndroidUtilities.removeFromParent(r3);
            AndroidUtilities.removeFromParent(r4);
        L14:
            JSONObject r22 = new JSONObject(r5);     // Catch: Exception -> L10
        L12:
            r6.run(r22);
            return;
        L10:
            e = move-exception;
            FileLog.e(e);
            r22 = null;
            goto L12
        }

        public AnonymousClass4(WebInstantView r1, boolean[] r2, WebView r3, FrameLayout r4, Utilities.Callback r5) {
            this.this$0 = r1;
            this.val$done = r2;
            this.val$webView = r3;
            this.val$webViewContainer = r4;
            this.val$whenDone = r5;
        }

        @JavascriptInterface
        @Keep
        public void done(String r7) {
            AndroidUtilities.runOnUIThread(new WebInstantView$4$$ExternalSyntheticLambda0(this.val$done, this.val$webView, this.val$webViewContainer, r7, this.val$whenDone));
        }
    }

    /* loaded from: classes6.dex */
    public static class Loader {
        private Runnable cancelLocal;
        private boolean cancelled;
        private final int currentAccount;
        public boolean currentIsLoaded;
        public float currentProgress;
        public String currentUrl;
        private boolean gotLocal;
        private boolean gotRemote;
        private final ArrayList<Runnable> listeners;
        private TLRPC.WebPage localPage;
        private TLRPC.WebPage remotePage;
        private int reqId;
        private boolean started;

        public static /* synthetic */ void $r8$lambda$RI9yTZzUCcpgMB1v_dBgt1Upiss(Loader r0, WebInstantView r1) {
            r0.lambda$retryLocal$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$gMrfhly7Cw1lBqn7ZSkKGrJ0MIA(Loader r0, Runnable r1) {
            r0.lambda$listen$4(r1);
        }

        public static /* synthetic */ void $r8$lambda$iAdeFCBLkILBMg6Uf9nRpZe4OZo(Loader r0, WebInstantView r1) {
            r0.lambda$start$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$wwmfgbhnLy3gDBY_PgSI6qWa9dw(Loader r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$start$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$zxKXsVkOqzCeWSs1mMYlQF2GCAI(Loader r0, TLObject r1) {
            r0.lambda$start$2(r1);
        }

        public Loader(int r2) {
            this.listeners = new ArrayList();
            this.currentAccount = r2;
        }

        private /* synthetic */ void lambda$listen$4(Runnable r1) {
            this.listeners.remove(r1);
        }

        private /* synthetic */ void lambda$retryLocal$0(WebInstantView r2) {
            this.cancelLocal = null;
            this.gotLocal = true;
            TLRPC.WebPage r0 = this.localPage;
            if (r0 == null) goto L5;
            WebInstantView.recycle(r0);
        L5:
            this.localPage = r2.webpage;
            notifyUpdate();
        }

        private /* synthetic */ void lambda$start$1(WebInstantView r2) {
            this.cancelLocal = null;
            this.gotLocal = true;
            TLRPC.WebPage r0 = this.localPage;
            if (r0 == null) goto L5;
            WebInstantView.recycle(r0);
        L5:
            this.localPage = r2.webpage;
            notifyUpdate();
        }

        private /* synthetic */ void lambda$start$2(TLObject r5) {
            this.gotRemote = true;
            if ((r5 instanceof TLRPC.TL_messages_webPage) == false) goto L6;
            TLRPC.TL_messages_webPage r52 = (TLRPC.TL_messages_webPage) r5;
            MessagesController.getInstance(this.currentAccount).putUsers(r52.users, false);
            MessagesController.getInstance(this.currentAccount).putChats(r52.chats, false);
            this.remotePage = r52.webpage;
        L11:
            TLRPC.WebPage r53 = this.remotePage;
            if (r53 == null) goto L17;
            if (r53.cached_page != null) goto L17;
            this.remotePage = null;
        L17:
            if (SharedConfig.onlyLocalInstantView == false) goto L19;
        L23:
            notifyUpdate();
            return;
        L19:
            if (this.remotePage == null) goto L23;
            Runnable r54 = this.cancelLocal;
            if (r54 == null) goto L23;
            r54.run();
            goto L23
        L6:
            if ((r5 instanceof TLRPC.TL_webPage) == false) goto L10;
            TLRPC.TL_webPage r55 = (TLRPC.TL_webPage) r5;
            if ((r55.cached_page instanceof TL_iv.TL_page) == false) goto L10;
            this.remotePage = r55;
        L10:
            this.remotePage = null;
            goto L11
        }

        private /* synthetic */ void lambda$start$3(TLObject r1, TLRPC.TL_error r2) {
            AndroidUtilities.runOnUIThread(new WebInstantView$Loader$$ExternalSyntheticLambda3(this, r1));
        }

        private void notifyUpdate() {
            ArrayList<Runnable> r3 = this.listeners;
            int r0 = r3.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            Runnable r2 = r3.get(r1);
            r1 = r1 + 1;
            r2.run();
            goto L3
        }

        public void cancel() {
            if (this.cancelled == true) goto L16;
            this.cancelled = true;
            if (this.gotRemote == true) goto L9;
            ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.reqId, true);
        L9:
            if (this.gotLocal == true) goto L14;
            Runnable r3 = this.cancelLocal;
            if (r3 == null) goto L15;
            r3.run();
            return;
        L15:
            return;
        L14:
            return;
        }

        public TLRPC.WebPage getWebPage() {
            if (SharedConfig.onlyLocalInstantView == true) goto L7;
            TLRPC.WebPage r0 = this.remotePage;
            if (r0 == null) goto L7;
            return r0;
        L7:
            TLRPC.WebPage r1 = this.localPage;
            if (r1 == null) goto L10;
            return r1;
        L10:
            return null;
        }

        public boolean isDone() {
            if (this.gotRemote == false) goto L7;
            if (this.gotLocal == false) goto L7;
            return true;
        L7:
            if (this.remotePage == null) goto L9;
            return true;
        L9:
            if (this.localPage == null) goto L11;
            return true;
        L11:
            if (this.cancelled == true) goto L19;
            return false;
        L19:
            return true;
        }

        public Runnable listen(Runnable r2) {
            this.listeners.add(r2);
            return new WebInstantView$Loader$$ExternalSyntheticLambda4(this, r2);
        }

        public void recycle() {
            TLRPC.WebPage r0 = this.localPage;
            if (r0 == null) goto L6;
            WebInstantView.recycle(r0);
            this.localPage = null;
            return;
        }

        public void retryLocal(BotWebViewContainer.MyWebView r3) {
            if (this.cancelled == false) goto L5;
            return;
        L5:
            TLRPC.WebPage r0 = this.localPage;
            if (r0 == null) goto L8;
            WebInstantView.recycle(r0);
            this.localPage = null;
        L8:
            this.gotLocal = false;
            this.currentUrl = r3.getUrl();
            this.currentProgress = r3.getProgress();
            this.currentIsLoaded = r3.isPageLoaded();
            Runnable r1 = this.cancelLocal;
            if (r1 == null) goto L11;
            r1.run();
        L11:
            this.cancelLocal = WebInstantView.generate(r3, false, new WebInstantView$Loader$$ExternalSyntheticLambda2(this));
        }

        public void start(BotWebViewContainer.MyWebView r3) {
            if (this.started == false) goto L5;
            return;
        L5:
            this.started = true;
            this.currentUrl = r3.getUrl();
            this.currentProgress = r3.getProgress();
            this.currentIsLoaded = r3.isPageLoaded();
            this.cancelLocal = WebInstantView.generate(r3, false, new WebInstantView$Loader$$ExternalSyntheticLambda0(this));
            TLRPC.TL_messages_getWebPage r32 = new TLRPC.TL_messages_getWebPage();
            r32.url = this.currentUrl;
            r32.hash = 0;
            this.reqId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r32, new WebInstantView$Loader$$ExternalSyntheticLambda1(this));
        }
    }

    /* loaded from: classes6.dex */
    public class WebPhoto extends TLRPC.Photo {
        public int h;
        public TL_iv.textImage inlineImage;
        public WebInstantView instantView;
        final /* synthetic */ WebInstantView this$0;
        public String url;
        public HashSet<String> urls;
        public int w;

        public WebPhoto(WebInstantView r1) {
            this.this$0 = r1;
            this.urls = new HashSet();
        }
    }

    public static /* synthetic */ void $r8$lambda$0ruKIo8_vXjpYnAehd1yTWWahAY(WebInstantView r0, WebView r1, File r2, Utilities.Callback r3, String r4) {
        r0.lambda$getHTML$7(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$5OKLd3KNjqeekGH8Do5VRgOXNAo(boolean[] r2) {
        r2[0] = true;
    }

    public static /* synthetic */ void $r8$lambda$5eCdyRlTVedsQRAWARfZXK7zwEo(Utilities.Callback r2, String r3) {
        JsonReader r0 = new JsonReader(new StringReader(r3));     // Catch: Exception -> L4
        r0.setLenient(true);     // Catch: Exception -> L4
        String r32 = r0.nextString();     // Catch: Exception -> L4
        r0.close();     // Catch: Exception -> L4
        r2.run(new ByteArrayInputStream(r32.getBytes(StandardCharsets.UTF_8)));     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        r2.run(null);
    }

    /* renamed from: $r8$lambda$8ciLZdT3_99Fw-TX3vjpVThD_Gc, reason: not valid java name */
    public static /* synthetic */ void m22064$r8$lambda$8ciLZdT3_99FwTX3vjpVThD_Gc(WebInstantView r0, WebView r1, File r2, Utilities.Callback r3, String r4) {
        r0.lambda$getHTML$8(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$Us0B0feOaqe1AirXDilnz7RNinI(String r0) {
    }

    public static /* synthetic */ void $r8$lambda$Yh_rHIiOF3DpDgaZCNIA7rZXO0Q(Timer.Task r1, boolean[] r2, Timer r3, WebInstantView r4, Utilities.Callback r5, JSONObject r6) {
        Timer.done(r1);
        if (r2[0] == false) goto L5;
        return;
    L5:
        Timer.Task r12 = Timer.start(r3, "parseJSON");
        r4.webpage = r4.parseJSON(r4.url, r6);     // Catch: Exception -> L8
    L10:
        Timer.done(r12);
        r5.run(r4);
        TLRPC.WebPage r13 = r4.webpage;
        if (r13 == null) goto L13;
        instants.put(r13, r4);
    L13:
        Timer.finish(r3);
        return;
    L8:
        e = move-exception;
        Timer.log(r3, "error: " + e);
        FileLog.e(e);
        goto L10
    }

    public static /* synthetic */ void $r8$lambda$YlCjfT0mhFdTUR2hES1ATvDObGA(Timer.Task r6, boolean[] r7, Timer r8, WebInstantView r9, Utilities.Callback r10, InputStream r11) {
        Timer.done(r6);
        if (r7[0] == false) goto L5;
        return;
    L5:
        Timer.Task r1 = Timer.start(r8, "readHTML");
        r9.readHTML(r9.url, r11, new WebInstantView$$ExternalSyntheticLambda6(r1, r7, r8, r9, r10));
    }

    public static /* synthetic */ void $r8$lambda$ZxGbwS_Ov01x4aNA2NENDWSdrrU(WebInstantView r0, WebPhoto r1, Bitmap r2) {
        r0.lambda$loadPhotoInternal$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$cKfcsLyPmtqnvZUj0W22TR0zTw4(WebInstantView r0, WebPhoto r1, Bitmap r2) {
        r0.lambda$loadPhotoInternal$3(r1, r2);
    }

    static {
        instants = new HashMap();
    }

    public WebInstantView() {
        this.loadedPhotos = new HashMap();
    }

    public static TL_iv.RichText addLastSpace(TL_iv.RichText r3) {
        if (r3 != null) goto L4;
        return r3;
    L4:
        TL_iv.RichText r0 = r3.text;
        if (r0 == null) goto L9;
        addLastSpace(r0);
        return r3;
    L9:
        if (r3.texts.isEmpty() == true) goto L13;
        addLastSpace(r3.texts.get(r0.size() - 1));
        return r3;
    L13:
        if ((r3 instanceof TL_iv.textPlain) == false) goto L19;
        TL_iv.textPlain r02 = (TL_iv.textPlain) r3;
        String r1 = r02.text;
        if (r1 == null) goto L19;
        if (r1.endsWith(" ") == true) goto L19;
        r02.text += ' ';
    L19:
        return r3;
    }

    public static TL_iv.RichText addNewLine(TL_iv.RichText r3) {
        if (r3 != null) goto L4;
        return r3;
    L4:
        TL_iv.RichText r0 = r3.text;
        if (r0 == null) goto L9;
        addNewLine(r0);
        return r3;
    L9:
        if (r3.texts.isEmpty() == true) goto L13;
        addNewLine(r3.texts.get(r0.size() - 1));
        return r3;
    L13:
        if ((r3 instanceof TL_iv.textPlain) == false) goto L15;
        StringBuilder r02 = new StringBuilder();
        TL_iv.textPlain r1 = (TL_iv.textPlain) r3;
        r02.append(r1.text);
        r02.append('\n');
        r1.text = r02.toString();
    L15:
        return r3;
    }

    public static TL_iv.RichText applyAnchor(TL_iv.RichText r1, JSONObject r2) {
        if (r2 == null) goto L6;
        String r22 = r2.optString("id");
        if (TextUtils.isEmpty(r22) == true) goto L6;
        TL_iv.textAnchor r0 = new TL_iv.textAnchor();
        r0.text = r1;
        r0.name = r22;
        return r0;
    L6:
        return r1;
    }

    public static void cancelLoadPhoto(ImageReceiver r5) {
        HashMap<String, ArrayList<Pair<ImageReceiver, Runnable>>> r0 = loadingPhotos;
        if (r0 == null) goto L24;
        Iterator<Map.Entry<String, ArrayList<Pair<ImageReceiver, Runnable>>>> r02 = r0.entrySet().iterator();
    L7:
        if (r02.hasNext() == false) goto L25;
        Map.Entry<String, ArrayList<Pair<ImageReceiver, Runnable>>> r1 = r02.next();
        String r2 = r1.getKey();
        ArrayList<Pair<ImageReceiver, Runnable>> r12 = r1.getValue();
        int r3 = 0;
    L10:
        if (r3 >= r12.size()) goto L16;
        if (r12.get(r3).first == r5) goto L13;
        r3 = r3 + 1;
        goto L10
    L13:
        r12.remove(r3);
    L16:
        if (r12.isEmpty() == false) goto L7;
        loadingPhotos.remove(r2);
        return;
    L25:
        return;
    }

    public static TL_iv.RichText filterRecursiveAnchorLinks(TL_iv.RichText r4, String r5, String r6) {
        if (r4 != null) goto L5;
        return r4;
    L5:
        if ((r4 instanceof TL_iv.textConcat) == false) goto L15;
        TL_iv.textConcat r42 = (TL_iv.textConcat) r4;
        TL_iv.textConcat r0 = new TL_iv.textConcat();
        int r1 = 0;
    L8:
        if (r1 >= r42.texts.size()) goto L13;
        TL_iv.RichText r2 = filterRecursiveAnchorLinks(r42.texts.get(r1), r5, r6);
        if (r2 == null) goto L12;
        r0.texts.add(r2);
    L12:
        r1 = r1 + 1;
        goto L8
    L13:
        return r0;
    L15:
        if ((r4 instanceof TL_iv.textUrl) == false) goto L27;
        TL_iv.textUrl r02 = (TL_iv.textUrl) r4;
        String r12 = r02.url;
        if (r12 != null) goto L19;
        return r4;
    L19:
        if (r12.toLowerCase().equals("#" + r6) == false) goto L21;
    L22:
        return null;
    L21:
        if (TextUtils.equals(r02.url.toLowerCase(), r5 + "#" + r6) == true) goto L22;
        return r4;
    L27:
        return r4;
    }

    public static Runnable generate(WebView r7, boolean r8, Utilities.Callback<WebInstantView> r9) {
        if (r9 != null) goto L5;
        return null;
    L5:
        if (r7 != null) goto L8;
        r9.run(null);
        return null;
    L8:
        boolean[] r3 = {false};
        WebInstantView r5 = new WebInstantView();
        r5.url = r7.getUrl();
        Timer r4 = Timer.create("WebInstantView");
        r5.getHTML(r7, r8, new WebInstantView$$ExternalSyntheticLambda0(Timer.start(r4, "getHTML"), r3, r4, r5, r9));
        return new WebInstantView$$ExternalSyntheticLambda1(r3);
    }

    private /* synthetic */ void lambda$getHTML$7(WebView r3, File r4, Utilities.Callback r5, String r6) {
        r3.evaluateJavascript(AndroidUtilities.readRes(R.raw.open_collapsed).replace("$OPEN$", "false"), new WebInstantView$$ExternalSyntheticLambda8());
        MHTML r32 = new MHTML(r4);     // Catch: Exception -> L7
        this.mhtml = r32;     // Catch: Exception -> L7
        if (r32.entries.isEmpty() == true) goto L9;
        r5.run(this.mhtml.entries.get(0).getInputStream());     // Catch: Exception -> L7
        return;
    L9:
        r5.run(null);
        return;
    L7:
        e = move-exception;
        FileLog.e(e);
        goto L9
    }

    private /* synthetic */ void lambda$getHTML$8(WebView r2, File r3, Utilities.Callback r4, String r5) {
        r2.saveWebArchive(r3.getAbsolutePath(), false, new WebInstantView$$ExternalSyntheticLambda7(this, r2, r3, r4));
    }

    private /* synthetic */ void lambda$loadPhotoInternal$3(WebPhoto r5, Bitmap r6) {
        if (loadingPhotos == null) goto L38;
        int r1 = 0;
        if (r5.w > 0) goto L8;
    L9:
        if (r6 == null) goto L11;
        boolean r0 = true;
    L12:
        if (r6 == null) goto L28;
        this.loadedPhotos.put(r5.url, r6);
        if (r0 == false) goto L28;
        int r4 = r5.w;
        if (r4 == 0) goto L18;
    L20:
        if (r4 != 0) goto L23;
        r5.w = (int) ((r6.getWidth() / r6.getHeight()) * r5.h);
    L25:
        TL_iv.textImage r42 = r5.inlineImage;
        if (r42 == null) goto L28;
        r42.w = r5.w;
        r42.h = r5.h;
        goto L28
    L23:
        if (r5.h != 0) goto L25;
        r5.h = (int) ((r6.getHeight() / r6.getWidth()) * r5.w);
        goto L25
    L18:
        if (r5.h != 0) goto L20;
        r5.w = r6.getWidth();
        r5.h = r6.getHeight();
    L28:
        ArrayList<Pair<ImageReceiver, Runnable>> r43 = loadingPhotos.remove(r5.url);
        if (r43 == null) goto L46;
        int r52 = r43.size();
    L32:
        if (r1 >= r52) goto L47;
        Pair<ImageReceiver, Runnable> r2 = r43.get(r1);
        r1 = r1 + 1;
        Pair<ImageReceiver, Runnable> r22 = r2;
        ((ImageReceiver) r22.first).setImageBitmap(r6);
        if (r0 == false) goto L32;
        Object r23 = r22.second;
        if (r23 == null) goto L32;
        ((Runnable) r23).run();
        goto L32
    L47:
        return;
    L46:
        return;
    L11:
        r0 = false;
        goto L12
    L8:
        if (r5.h > 0) goto L11;
    }

    private /* synthetic */ void lambda$loadPhotoInternal$4(WebPhoto r2, Bitmap r3) {
        AndroidUtilities.runOnUIThread(new WebInstantView$$ExternalSyntheticLambda3(this, r2, r3));
    }

    public static void loadPhoto(WebPhoto r1, ImageReceiver r2, Runnable r3) {
        if (r1 == null) goto L8;
        WebInstantView r0 = r1.instantView;
        if (r0 == null) goto L9;
        r0.loadPhotoInternal(r1, r2, r3);
        return;
    L9:
        return;
    }

    private void loadPhotoInternal(WebPhoto r5, ImageReceiver r6, Runnable r7) {
    L63:
        e = move-exception;
        FileLog.e(e);
        return;
    L3:
        if (this.mhtml == null) goto L10;
        Iterator<String> r0 = r5.urls.iterator();     // Catch: Exception -> L63
        MHTML.Entry r2 = null;
    L6:
        if (r0.hasNext() == false) goto L11;
        r2 = this.mhtml.entriesByLocation.get(r0.next());     // Catch: Exception -> L63
        if (r2 == null) goto L6;
    L11:
        int r02 = 0;
        if (r2 == null) goto L45;
        if (r2.getType().contains("svg") == false) goto L23;
        if (r5.w > 0) goto L18;
        return;
    L18:
        if (r5.h <= 0) goto L72;
        Bitmap r4 = SvgHelper.getBitmap(r2.getInputStream(), AndroidUtilities.dp(r5.w), AndroidUtilities.dp(r5.h), false);     // Catch: Exception -> L63
    L42:
        r6.setImageBitmap(r4);     // Catch: Exception -> L63
        return;
    L72:
        return;
    L23:
        if (r5.w > 0) goto L25;
    L26:
        BitmapFactory.Options r42 = new BitmapFactory.Options();     // Catch: Exception -> L63
        r42.inJustDecodeBounds = true;     // Catch: Exception -> L63
        BitmapFactory.decodeStream(r2.getInputStream(), null, r42);     // Catch: Exception -> L63
        int r03 = r5.w;     // Catch: Exception -> L63
        if (r03 == 0) goto L29;
    L31:
        if (r03 != 0) goto L34;
        r5.w = (int) ((r42.outWidth / r42.outHeight) * r5.h);     // Catch: Exception -> L63
    L36:
        TL_iv.textImage r43 = r5.inlineImage;     // Catch: Exception -> L63
        if (r43 == null) goto L39;
        r43.w = r5.w;     // Catch: Exception -> L63
        r43.h = r5.h;     // Catch: Exception -> L63
    L39:
        if (r7 == null) goto L41;
        r7.run();     // Catch: Exception -> L63
    L41:
        r4 = BitmapFactory.decodeStream(r2.getInputStream());     // Catch: Exception -> L63
        goto L42
    L34:
        if (r5.h != 0) goto L36;
        r5.h = (int) ((r42.outHeight / r42.outWidth) * r03);     // Catch: Exception -> L63
        goto L36
    L29:
        if (r5.h != 0) goto L31;
        r5.w = r42.outWidth;     // Catch: Exception -> L63
        r5.h = r42.outHeight;     // Catch: Exception -> L63
        goto L36
    L25:
        if (r5.h > 0) goto L41;
    L45:
        if (this.loadedPhotos.containsKey(r5.url) == false) goto L49;
        r6.setImageBitmap(this.loadedPhotos.get(r5.url));     // Catch: Exception -> L63
        return;
    L49:
        if (loadingPhotos != null) goto L51;
        loadingPhotos = new HashMap();     // Catch: Exception -> L63
    L51:
        ArrayList<Pair<ImageReceiver, Runnable>> r1 = loadingPhotos.get(r5.url);     // Catch: Exception -> L63
        if (r1 != null) goto L54;
        loadingPhotos.put(r5.url, new ArrayList());     // Catch: Exception -> L63
        new HttpGetBitmapTask(new WebInstantView$$ExternalSyntheticLambda2(this, r5)).execute(new String[]{r5.url});     // Catch: Exception -> L63
        return;
    L54:
        if (r02 >= r1.size()) goto L59;
        if (r1.get(r02).first == r6) goto L57;
        r02 = r02 + 1;     // Catch: Exception -> L63
        goto L54
    L57:
        return;
    L59:
        r1.add(new Pair(r6, r7));     // Catch: Exception -> L63
        return;
    L10:
        r2 = null;
        goto L11
    }

    public static TL_iv.RichText parseRichText(String r1) {
        TL_iv.textPlain r0 = new TL_iv.textPlain();
        r0.text = r1;
        return r0;
    }

    public static void recycle(TLRPC.WebPage r1) {
        WebInstantView r12 = instants.remove(r1);
        if (r12 == null) goto L6;
        r12.recycle();
        return;
    }

    public static TL_iv.RichText trim(TL_iv.RichText r4) {
        if (r4 != null) goto L4;
        return r4;
    L4:
        TL_iv.RichText r0 = r4.text;
        if (r0 == null) goto L8;
        trim(r0);
        return r4;
    L8:
        int r02 = r4.texts.size();
        ArrayList<TL_iv.RichText> r1 = r4.texts;
        if (r02 != 1) goto L13;
        trim(r1.get(0));
        return r4;
    L13:
        if (r1.isEmpty() == true) goto L17;
        trimStart(r4.texts.get(0));
        ArrayList<TL_iv.RichText> r03 = r4.texts;
        trimEnd(r03.get(r03.size() - 1));
        return r4;
    L17:
        if ((r4 instanceof TL_iv.textPlain) == false) goto L21;
        TL_iv.textPlain r04 = (TL_iv.textPlain) r4;
        String r12 = r04.text;
        if (r12 == null) goto L21;
        r04.text = r12.trim();
    L21:
        return r4;
    }

    public static TL_iv.RichText trimEnd(TL_iv.RichText r4) {
        if (r4 != null) goto L4;
        return r4;
    L4:
        TL_iv.RichText r0 = r4.text;
        if (r0 == null) goto L9;
        trimEnd(r0);
        return r4;
    L9:
        if (r4.texts.isEmpty() == true) goto L13;
        trimEnd(r4.texts.get(r0.size() - 1));
        return r4;
    L13:
        if ((r4 instanceof TL_iv.textPlain) == false) goto L17;
        TL_iv.textPlain r02 = (TL_iv.textPlain) r4;
        String r1 = r02.text;
        if (r1 == null) goto L17;
        r02.text = r1.replaceAll("\\s+$", _UrlKt.FRAGMENT_ENCODE_SET);
    L17:
        return r4;
    }

    public static TL_iv.RichText trimStart(TL_iv.RichText r4) {
        if (r4 != null) goto L4;
        return r4;
    L4:
        TL_iv.RichText r0 = r4.text;
        if (r0 == null) goto L9;
        trimStart(r0);
        return r4;
    L9:
        if (r4.texts.isEmpty() == true) goto L13;
        trimStart(r4.texts.get(0));
        return r4;
    L13:
        if ((r4 instanceof TL_iv.textPlain) == false) goto L17;
        TL_iv.textPlain r02 = (TL_iv.textPlain) r4;
        String r1 = r02.text;
        if (r1 == null) goto L17;
        r02.text = r1.replaceAll("^\\s+", _UrlKt.FRAGMENT_ENCODE_SET);
    L17:
        return r4;
    }

    public void getHTML(WebView r4, boolean r5, Utilities.Callback<InputStream> r6) {
        if (r6 != null) goto L4;
        return;
    L4:
        if (r4 != null) goto L7;
        r6.run(null);
        return;
    L7:
        if (r5 == false) goto L10;
        r4.evaluateJavascript("document.documentElement.outerHTML", new WebInstantView$$ExternalSyntheticLambda4(r6));
        return;
    L10:
        System.currentTimeMillis();
        File r52 = new File(AndroidUtilities.getCacheDir(), "archive.mht");
        r4.evaluateJavascript(AndroidUtilities.readRes(R.raw.open_collapsed).replace("$OPEN$", "true"), new WebInstantView$$ExternalSyntheticLambda5(this, r4, r52, r6));
    }

    public boolean isInline(JSONArray r12) {
        List r0 = Arrays.asList(new String[]{"b", "strong", "span", "img", "i", "s", "a", "code", "mark", "sub", "sup"});
        int r2 = 0;
    L4:
        if (r2 >= r12.length()) goto L19;
        Object r3 = r12.get(r2);
        if ((r3 instanceof String) == true) goto L12;
        if ((r3 instanceof JSONObject) == false) goto L18;
        JSONObject r32 = (JSONObject) r3;
        String r4 = r32.optString("tag");
        if (r0.contains(r4) == true) goto L12;
        if ("div".equalsIgnoreCase(r4) == false) goto L16;
    L17:
        isInline(r32.optJSONArray("content"));
        goto L18
    L16:
        if ("span".equalsIgnoreCase(r4) == true) goto L17;
    L18:
        return false;
    L12:
        r2 = r2 + 1;
        goto L4
    L19:
        return true;
    }

    public TL_iv.pageBlockDetails parseDetails(String r7, JSONObject r8, TL_iv.TL_page r9) {
        TL_iv.pageBlockDetails r0 = new TL_iv.pageBlockDetails();
        JSONArray r1 = r8.optJSONArray("content");
        if (r1 != null) goto L6;
        return null;
    L6:
        int r2 = 0;
    L8:
        if (r2 >= r1.length()) goto L15;
        Object r3 = r1.get(r2);
        if ((r3 instanceof JSONObject) == false) goto L14;
        JSONObject r32 = (JSONObject) r3;
        if ("summary".equals(r32.optString("tag")) == false) goto L14;
        r0.title = trim(parseRichText(r32, r9));
        r1.remove(r2);
    L14:
        r2 = r2 + 1;
    L15:
        r0.blocks.addAll(parsePageBlocks(r7, r1, r9));
        r0.open = r8.has("open");
        return r0;
    }

    public TL_iv.pageBlockPhoto parseFigure(JSONObject r11, TL_iv.TL_page r12) {
        JSONArray r112 = r11.optJSONArray("content");
        ArrayList r0 = new ArrayList();
        WebPhoto r1 = null;
        int r2 = 0;
        TL_iv.pageBlockPhoto r4 = null;
        TL_iv.RichText r5 = null;
        int r3 = 0;
    L4:
        if (r3 >= r112.length()) goto L28;
        Object r6 = r112.get(r3);
        if ((r6 instanceof JSONObject) == false) goto L27;
        JSONObject r62 = (JSONObject) r6;
        String r7 = r62.optString("tag");
        if ("figurecaption".equalsIgnoreCase(r7) == false) goto L10;
    L26:
        r5 = trim(parseRichText(r62, r12));
        goto L27
    L10:
        if (MediaTrack.ROLE_CAPTION.equalsIgnoreCase(r7) == true) goto L26;
        if ("img".equalsIgnoreCase(r7) == false) goto L16;
        r4 = parseImage(r62, r12);
        goto L27
    L16:
        if ("source".equalsIgnoreCase(r7) == false) goto L27;
        String r72 = r62.optString("src");
        if (TextUtils.isEmpty(r72) == true) goto L20;
        r0.add(r72);
        goto L27
    L20:
        String r63 = r62.optString("srcset");
        if (TextUtils.isEmpty(r63) == true) goto L27;
        String[] r64 = r63.split(",");
        int r73 = 0;
    L24:
        if (r73 >= r64.length) goto L27;
        r0.add(r64[r73].trim().split(" ")[0].trim());
        r73 = r73 + 1;
    L27:
        r3 = r3 + 1;
        goto L4
    L28:
        if (r4 != null) goto L30;
        return null;
    L30:
        if (r5 == null) goto L33;
        TL_iv.PageCaption r10 = new TL_iv.PageCaption();
        r4.caption = r10;
        r10.text = r5;
        r10.credit = new TL_iv.textEmpty();
    L33:
        if (r2 >= r12.photos.size()) goto L40;
        if ((r12.photos.get(r2) instanceof WebPhoto) == false) goto L39;
        if (r12.photos.get(r2).id != r4.photo_id) goto L39;
        r1 = (WebPhoto) r12.photos.get(r2);
    L39:
        r2 = r2 + 1;
    L40:
        if (r1 == null) goto L42;
        r1.urls.addAll(r0);
    L42:
        return r4;
    }

    public TL_iv.pageBlockPhoto parseImage(JSONObject r6, TL_iv.TL_page r7) {
        TL_iv.pageBlockPhoto r0 = new TL_iv.pageBlockPhoto();
        r0.caption = new TL_iv.PageCaption();
        String r1 = r6.optString("alt");
        if (r1 == null) goto L5;
        r0.caption.text = trim(parseRichText(r1));
        r0.caption.credit = trim(parseRichText(_UrlKt.FRAGMENT_ENCODE_SET));
    L5:
        String r12 = r6.optString("src");
        if (r12 != null) goto L9;
        return null;
    L9:
        WebPhoto r2 = new WebPhoto(this);
        r2.instantView = this;
        r2.id = (-1) - r7.photos.size();
        r2.url = r12;
        r2.urls.add(r12);
        r2.w = Integer.parseInt(r6.optString("width"));     // Catch: Exception -> L20
    L24:
        r2.h = Integer.parseInt(r6.optString("height"));     // Catch: Exception -> L21
    L13:
        if (r2.w != 0) goto L16;
        r2.w = r2.h;
    L16:
        if (r2.h != 0) goto L18;
        r2.h = r2.w;
    L18:
        r0.photo_id = r2.id;
        r0.url = r12;
        r7.photos.add(r2);
        return r0;
    }

    public TL_iv.textImage parseInlineImage(JSONObject r8, TL_iv.TL_page r9) {
        TL_iv.textImage r2 = new TL_iv.textImage();
        String r3 = r8.optString("src");
        if (r3 != null) goto L6;
        return null;
    L6:
        WebPhoto r4 = new WebPhoto(this);
        r4.instantView = this;
        r4.id = (-1) - r9.photos.size();
        r4.url = r3;
        r4.urls.add(r3);
        r4.w = Integer.parseInt(r8.optString("width"));     // Catch: Exception -> L25
    L35:
        r4.h = Integer.parseInt(r8.optString("height"));     // Catch: Exception -> L26
    L9:
        r2.url = r3;
        r9.photos.add(r4);
        if (r4.w != 0) goto L13;
        r4.w = r4.h;
    L13:
        if (r4.h != 0) goto L33;
        r4.h = r4.w;
    L33:
        r2.w = Integer.parseInt(r8.optString("width"));     // Catch: Exception -> L27
    L29:
        r2.h = Integer.parseInt(r8.optString("height"));     // Catch: Exception -> L28
    L18:
        if (r2.w != 0) goto L21;
        r2.w = r2.h;
    L21:
        if (r2.h != 0) goto L23;
        r2.h = r2.w;
    L23:
        r2.photo_id = r4.id;
        return r2;
    }

    public TLRPC.TL_webPage parseJSON(String r5, JSONObject r6) {
        TLRPC.TL_webPage r0 = new TLRPC.TL_webPage();
        r0.id = 0;
        r0.url = r5;
        r0.display_url = r5;
        String r1 = r6.getString("siteName");
        if (r1 != null) goto L5;
    L7:
        String r12 = r6.optString("title");
        if (r12 != null) goto L10;
    L12:
        String r13 = r6.optString("byline");
        if (r13 != null) goto L15;
    L19:
        String r14 = r6.optString("excerpt");
        if (r14 != null) goto L22;
    L24:
        JSONArray r15 = r6.optJSONArray("content");
        if (r15 != null) goto L27;
    L29:
        return r0;
    L27:
        if ("null".equals(r15) == true) goto L29;
        r0.flags |= 1024;
        r0.cached_page = parsePage(r5, r6);
        goto L29
    L22:
        if ("null".equals(r14) == true) goto L24;
        r0.flags |= 8;
        r0.description = r14;
        goto L24
    L15:
        if ("null".equals(r13) == true) goto L19;
        if ("by".equalsIgnoreCase(r13) == true) goto L19;
        r0.flags |= 256;
        r0.author = r13;
        goto L19
    L10:
        if ("null".equals(r12) == true) goto L12;
        r0.flags |= 4;
        r0.title = r12;
        goto L12
    L5:
        if ("null".equals(r1) == true) goto L7;
        r0.flags |= 2;
        r0.site_name = r1;
        goto L7
    }

    public TL_iv.PageBlock parseList(String r9, JSONObject r10, TL_iv.TL_page r11) {
        int r3 = 0;
        if ("ol".equals(r10.optString("tag")) == false) goto L19;
        TL_iv.pageBlockOrderedList r1 = new TL_iv.pageBlockOrderedList();
        JSONArray r102 = r10.getJSONArray("content");
    L6:
        if (r3 >= r102.length()) goto L18;
        Object r5 = r102.get(r3);
        if ((r5 instanceof JSONObject) == false) goto L17;
        JSONObject r52 = (JSONObject) r5;
        if ("li".equals(r52.optString("tag")) == false) goto L17;
        JSONArray r53 = r52.optJSONArray("content");
        if (isInline(r53) == false) goto L16;
        TL_iv.TL_pageListOrderedItemText r6 = new TL_iv.TL_pageListOrderedItemText();
        r6.text = parseRichText(r53, r11);
        r1.items.add(r6);
        goto L17
    L16:
        TL_iv.TL_pageListOrderedItemBlocks r62 = new TL_iv.TL_pageListOrderedItemBlocks();
        r62.blocks.addAll(parsePageBlocks(r9, r53, r11));
        r1.items.add(r62);
    L17:
        r3 = r3 + 1;
        goto L6
    L18:
        return r1;
    L19:
        TL_iv.pageBlockList r12 = new TL_iv.pageBlockList();
        JSONArray r103 = r10.getJSONArray("content");
    L21:
        if (r3 >= r103.length()) goto L33;
        Object r54 = r103.get(r3);
        if ((r54 instanceof JSONObject) == false) goto L32;
        JSONObject r55 = (JSONObject) r54;
        if ("li".equals(r55.optString("tag")) == false) goto L32;
        JSONArray r56 = r55.optJSONArray("content");
        if (isInline(r56) == false) goto L31;
        TL_iv.TL_pageListItemText r63 = new TL_iv.TL_pageListItemText();
        r63.text = parseRichText(r56, r11);
        r12.items.add(r63);
        goto L32
    L31:
        TL_iv.TL_pageListItemBlocks r64 = new TL_iv.TL_pageListItemBlocks();
        r64.blocks.addAll(parsePageBlocks(r9, r56, r11));
        r12.items.add(r64);
    L32:
        r3 = r3 + 1;
        goto L21
    L33:
        return r12;
    }

    public TL_iv.TL_page parsePage(String r4, JSONObject r5) {
        String r0 = r5.optString("title");
        if ("null".equals(r0) == false) goto L5;
        r0 = null;
    L5:
        "null".equals(r5.optString("publishedTime"));
        JSONArray r52 = r5.optJSONArray("content");
        TL_iv.TL_page r1 = new TL_iv.TL_page();
        r1.web = true;
        r1.url = r4;
        r1.blocks.addAll(parsePageBlocks(r4, r52, r1));
        if (r1.blocks.isEmpty() == false) goto L8;
    L11:
        TL_iv.pageBlockTitle r3 = new TL_iv.pageBlockTitle();
        r3.text = trim(parseRichText(r0));
        r1.blocks.add(0, r3);
        return r1;
    L8:
        if ((r1.blocks.get(0) instanceof TL_iv.pageBlockHeader) == false) goto L11;
        return r1;
    }

    public ArrayList<TL_iv.PageBlock> parsePageBlocks(String r9, JSONArray r10, TL_iv.TL_page r11) {
        ArrayList<TL_iv.PageBlock> r0 = new ArrayList();
        int r2 = 0;
    L4:
        if (r2 >= r10.length()) goto L147;
        Object r3 = r10.get(r2);
        if ((r3 instanceof String) == false) goto L9;
        TL_iv.pageBlockParagraph r4 = new TL_iv.pageBlockParagraph();
        r4.text = parseRichText((String) r3);
        r0.add(r4);
    L146:
        r2 = r2 + 1;
        goto L4
    L9:
        if ((r3 instanceof JSONObject) == false) goto L146;
        JSONObject r32 = (JSONObject) r3;
        String r42 = r32.optString("tag");
        JSONArray r5 = r32.optJSONArray("content");
        r42.getClass();
        char r7 = 65535;
        switch(r42.hashCode()) {
            case -1274639644: goto L118;
            case -891980137: goto L114;
            case -577741570: goto L110;
            case 97: goto L106;
            case 98: goto L102;
            case 105: goto L98;
            case 112: goto L94;
            case 115: goto L90;
            case 3273: goto L86;
            case 3274: goto L82;
            case 3275: goto L78;
            case 3276: goto L74;
            case 3277: goto L70;
            case 3278: goto L66;
            case 3338: goto L62;
            case 3549: goto L58;
            case 3735: goto L54;
            case 104387: goto L50;
            case 111267: goto L46;
            case 114240: goto L42;
            case 114254: goto L38;
            case 3059181: goto L34;
            case 3344077: goto L30;
            case 3536714: goto L26;
            case 110115790: goto L22;
            case 1303202319: goto L18;
            case 1557721666: goto L14;
            default: goto L121;
        };
    L121:
        switch(r7) {
            case 0: goto L143;
            case 1: goto L142;
            case 2: goto L143;
            case 3: goto L142;
            case 4: goto L142;
            case 5: goto L142;
            case 6: goto L141;
            case 7: goto L142;
            case 8: goto L140;
            case 9: goto L139;
            case 10: goto L138;
            case 11: goto L137;
            case 12: goto L136;
            case 13: goto L135;
            case 14: goto L134;
            case 15: goto L133;
            case 16: goto L133;
            case 17: goto L130;
            case 18: goto L129;
            case 19: goto L142;
            case 20: goto L142;
            case 21: goto L142;
            case 22: goto L142;
            case 23: goto L142;
            case 24: goto L128;
            case 25: goto L127;
            case 26: goto L124;
            default: goto L122;
        };
    L122:
        if (r5 == null) goto L146;
        r0.addAll(parsePageBlocks(r9, r5, r11));
        goto L146
    L124:
        TL_iv.pageBlockDetails r33 = parseDetails(r9, r32, r11);
        if (r33 == null) goto L146;
        r0.add(r33);
        goto L146
    L127:
        TL_iv.pageBlockBlockquote r43 = new TL_iv.pageBlockBlockquote();
        r43.text = trim(parseRichText(r32, r11));
        TL_iv.textItalic r34 = new TL_iv.textItalic();
        r34.text = r43.text;
        r43.text = r34;
        r0.add(r43);
        goto L146
    L128:
        r0.add(parseTable(r9, r32, r11));
        goto L146
    L129:
        TL_iv.pageBlockPreformatted r44 = new TL_iv.pageBlockPreformatted();
        TL_iv.textFixed r52 = new TL_iv.textFixed();
        r52.text = trim(parseRichText(r32, r11));
        r44.text = r52;
        r44.language = _UrlKt.FRAGMENT_ENCODE_SET;
        r0.add(r44);
        goto L146
    L130:
        TL_iv.pageBlockPhoto r35 = parseImage(r32, r11);
        if (r35 == null) goto L146;
        r0.add(r35);
        goto L146
    L133:
        r0.add(parseList(r9, r32, r11));
        goto L146
    L134:
        r0.add(new TL_iv.pageBlockDivider());
        goto L146
    L135:
        TL_iv.pageBlockHeading6 r45 = new TL_iv.pageBlockHeading6();
        r45.text = trim(parseRichText(r32, r11));
        r0.add(r45);
        goto L146
    L136:
        TL_iv.pageBlockHeading5 r46 = new TL_iv.pageBlockHeading5();
        r46.text = trim(parseRichText(r32, r11));
        r0.add(r46);
        goto L146
    L137:
        TL_iv.pageBlockHeading4 r47 = new TL_iv.pageBlockHeading4();
        r47.text = trim(parseRichText(r32, r11));
        r0.add(r47);
        goto L146
    L138:
        TL_iv.pageBlockHeading3 r48 = new TL_iv.pageBlockHeading3();
        r48.text = trim(parseRichText(r32, r11));
        r0.add(r48);
        goto L146
    L139:
        TL_iv.pageBlockHeading2 r49 = new TL_iv.pageBlockHeading2();
        r49.text = trim(parseRichText(r32, r11));
        r0.add(r49);
        goto L146
    L140:
        TL_iv.pageBlockHeading1 r410 = new TL_iv.pageBlockHeading1();
        r410.text = trim(parseRichText(r32, r11));
        r0.add(r410);
        goto L146
    L141:
        TL_iv.pageBlockParagraph r411 = new TL_iv.pageBlockParagraph();
        r411.text = trim(parseRichText(r32, r11));
        r0.add(r411);
        goto L146
    L142:
        JSONArray r412 = new JSONArray();
        r412.put(r32);
        TL_iv.pageBlockParagraph r36 = new TL_iv.pageBlockParagraph();
        r36.text = parseRichText(r412, r11);
        r0.add(r36);
        goto L146
    L143:
        TL_iv.pageBlockPhoto r37 = parseFigure(r32, r11);
        if (r37 == null) goto L146;
        r0.add(r37);
        goto L146
    L14:
        if (r42.equals("details") == false) goto L121;
        r7 = 26;
        goto L121
    L18:
        if (r42.equals("blockquote") == false) goto L121;
        r7 = 25;
        goto L121
    L22:
        if (r42.equals("table") == false) goto L121;
        r7 = 24;
        goto L121
    L26:
        if (r42.equals("span") == false) goto L121;
        r7 = 23;
        goto L121
    L30:
        if (r42.equals("mark") == false) goto L121;
        r7 = 22;
        goto L121
    L34:
        if (r42.equals("code") == false) goto L121;
        r7 = 21;
        goto L121
    L38:
        if (r42.equals("sup") == false) goto L121;
        r7 = 20;
        goto L121
    L42:
        if (r42.equals("sub") == false) goto L121;
        r7 = 19;
        goto L121
    L46:
        if (r42.equals("pre") == false) goto L121;
        r7 = 18;
        goto L121
    L50:
        if (r42.equals("img") == false) goto L121;
        r7 = 17;
        goto L121
    L54:
        if (r42.equals("ul") == false) goto L121;
        r7 = 16;
        goto L121
    L58:
        if (r42.equals("ol") == false) goto L121;
        r7 = 15;
        goto L121
    L62:
        if (r42.equals("hr") == false) goto L121;
        r7 = 14;
        goto L121
    L66:
        if (r42.equals("h6") == false) goto L121;
        r7 = '\r';
        goto L121
    L70:
        if (r42.equals("h5") == false) goto L121;
        r7 = '\f';
        goto L121
    L74:
        if (r42.equals("h4") == false) goto L121;
        r7 = 11;
        goto L121
    L78:
        if (r42.equals("h3") == false) goto L121;
        r7 = '\n';
        goto L121
    L82:
        if (r42.equals("h2") == false) goto L121;
        r7 = '\t';
        goto L121
    L86:
        if (r42.equals("h1") == false) goto L121;
        r7 = '\b';
        goto L121
    L90:
        if (r42.equals("s") == false) goto L121;
        r7 = 7;
        goto L121
    L94:
        if (r42.equals("p") == false) goto L121;
        r7 = 6;
        goto L121
    L98:
        if (r42.equals("i") == false) goto L121;
        r7 = 5;
        goto L121
    L102:
        if (r42.equals("b") == false) goto L121;
        r7 = 4;
        goto L121
    L106:
        if (r42.equals("a") == false) goto L121;
        r7 = 3;
        goto L121
    L110:
        if (r42.equals("picture") == false) goto L121;
        r7 = 2;
        goto L121
    L114:
        if (r42.equals("strong") == false) goto L121;
        r7 = 1;
        goto L121
    L118:
        if (r42.equals("figure") == false) goto L121;
        r7 = 0;
        goto L121
    L147:
        return r0;
    }

    public TL_iv.RichText parseRichText(JSONArray r11, TL_iv.TL_page r12) {
        ArrayList<TL_iv.RichText> r0 = new ArrayList();
        int r2 = 0;
    L4:
        if (r2 >= r11.length()) goto L99;
        Object r3 = r11.get(r2);
        if ((r3 instanceof String) == false) goto L8;
        r0.add(parseRichText((String) r3));
    L97:
        r2 = r2 + 1;
        goto L4
    L8:
        JSONObject r32 = (JSONObject) r3;
        String r5 = r32.optString("tag");
        r5.getClass();
        char r9 = 65535;
        switch(r5.hashCode()) {
            case -891980137: goto L60;
            case 97: goto L56;
            case 98: goto L52;
            case 105: goto L48;
            case 112: goto L44;
            case 115: goto L40;
            case 3152: goto L36;
            case 104387: goto L32;
            case 111267: goto L28;
            case 114240: goto L24;
            case 114254: goto L20;
            case 3059181: goto L16;
            case 3344077: goto L12;
            default: goto L63;
        };
    L63:
        switch(r9) {
            case 0: goto L94;
            case 1: goto L83;
            case 2: goto L94;
            case 3: goto L82;
            case 4: goto L79;
            case 5: goto L77;
            case 6: goto L74;
            case 7: goto L70;
            case 8: goto L68;
            case 9: goto L67;
            case 10: goto L66;
            case 11: goto L68;
            case 12: goto L65;
            default: goto L64;
        };
    L64:
        TL_iv.RichText r4 = parseRichText(r32, r12);
    L95:
        if (r4 == null) goto L97;
        r0.add(applyAnchor(r4, r32));
        goto L97
    L65:
        r4 = new TL_iv.textMarked();
        r4.text = parseRichText(r32, r12);
        goto L95
    L66:
        r4 = new TL_iv.textSuperscript();
        r4.text = parseRichText(r32, r12);
        goto L95
    L67:
        r4 = new TL_iv.textSubscript();
        r4.text = parseRichText(r32, r12);
        goto L95
    L68:
        r4 = new TL_iv.textFixed();
        r4.text = parseRichText(r32, r12);
        goto L95
    L77:
        r4 = new TL_iv.textStrike();
        r4.text = parseRichText(r32, r12);
        goto L95
    L82:
        r4 = new TL_iv.textItalic();
        r4.text = parseRichText(r32, r12);
        goto L95
    L83:
        String r42 = r32.optString("href");
        if (r42 != null) goto L87;
        r4 = parseRichText(r32, r12);
        goto L95
    L87:
        if (r42.startsWith("tel:") == false) goto L91;
        TL_iv.textPhone r52 = new TL_iv.textPhone();
        r52.phone = r42.substring(4);
        r52.text = parseRichText(r32, r12);
        TL_iv.RichText r53 = r52;
    L89:
        r4 = r53;
        goto L95
    L91:
        if (r42.startsWith("mailto:") == false) goto L93;
        TL_iv.textEmail r54 = new TL_iv.textEmail();
        r54.email = r42.substring(7);
        r54.text = parseRichText(r32, r12);
        r53 = r54;
        goto L89
    L93:
        TL_iv.textUrl r55 = new TL_iv.textUrl();
        r55.url = r42;
        r55.text = parseRichText(r32, r12);
        r53 = r55;
        goto L89
    L94:
        r4 = new TL_iv.textBold();
        r4.text = parseRichText(r32, r12);
        goto L95
    L70:
        if (r0.isEmpty() == true) goto L72;
        addLastSpace(r0.get(r0.size() - 1));
    L72:
        r4 = parseInlineImage(r32, r12);
        goto L95
    L74:
        if (r0.isEmpty() == true) goto L76;
        addNewLine(r0.get(r0.size() - 1));
    L76:
        r4 = null;
        goto L95
    L79:
        if (r0.isEmpty() == true) goto L81;
        addNewLine(r0.get(r0.size() - 1));
    L81:
        r4 = parseRichText(r32, r12);
        goto L95
    L12:
        if (r5.equals("mark") == false) goto L63;
        r9 = '\f';
        goto L63
    L16:
        if (r5.equals("code") == false) goto L63;
        r9 = 11;
        goto L63
    L20:
        if (r5.equals("sup") == false) goto L63;
        r9 = '\n';
        goto L63
    L24:
        if (r5.equals("sub") == false) goto L63;
        r9 = '\t';
        goto L63
    L28:
        if (r5.equals("pre") == false) goto L63;
        r9 = '\b';
        goto L63
    L32:
        if (r5.equals("img") == false) goto L63;
        r9 = 7;
        goto L63
    L36:
        if (r5.equals("br") == false) goto L63;
        r9 = 6;
        goto L63
    L40:
        if (r5.equals("s") == false) goto L63;
        r9 = 5;
        goto L63
    L44:
        if (r5.equals("p") == false) goto L63;
        r9 = 4;
        goto L63
    L48:
        if (r5.equals("i") == false) goto L63;
        r9 = 3;
        goto L63
    L52:
        if (r5.equals("b") == false) goto L63;
        r9 = 2;
        goto L63
    L56:
        if (r5.equals("a") == false) goto L63;
        r9 = 1;
        goto L63
    L60:
        if (r5.equals("strong") == false) goto L63;
        r9 = 0;
        goto L63
    L99:
        if (r0.isEmpty() == false) goto L103;
        return new TL_iv.textEmpty();
    L103:
        if (r0.size() == 1) goto L105;
        TL_iv.textConcat r10 = new TL_iv.textConcat();
        r10.texts = r0;
        return r10;
    L105:
        return r0.get(0);
    }

    public TL_iv.RichText parseRichText(JSONObject r2, TL_iv.TL_page r3) {
        TL_iv.RichText r1 = applyAnchor(parseRichText(r2.getJSONArray("content"), r3), r2);
        if (r2.has("bold") == false) goto L6;
        TL_iv.textBold r32 = new TL_iv.textBold();
        r32.text = r1;
        r1 = r32;
    L6:
        if (r2.has("italic") == false) goto L9;
        TL_iv.textItalic r22 = new TL_iv.textItalic();
        r22.text = r1;
        return r22;
    L9:
        return r1;
    }

    public TL_iv.pageBlockTable parseTable(String r3, JSONObject r4, TL_iv.TL_page r5) {
        TL_iv.pageBlockTable r0 = new TL_iv.pageBlockTable();
        r0.bordered = true;
        r0.striped = true;
        String r1 = r4.optString("title");
        if (r1 != null) goto L5;
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        r0.title = trim(applyAnchor(parseRichText(r1), r4));
        JSONArray r42 = r4.getJSONArray("content");
        r0.rows.addAll(parseTableRows(r3, r42, r5));
        return r0;
    }

    public TL_iv.pageTableRow parseTableRow(String r7, JSONObject r8, TL_iv.TL_page r9) {
        TL_iv.pageTableRow r72 = new TL_iv.pageTableRow();
        JSONArray r82 = r8.getJSONArray("content");
        int r1 = 0;
    L4:
        if (r1 >= r82.length()) goto L28;
        Object r2 = r82.get(r1);
        if ((r2 instanceof JSONObject) == false) goto L27;
        JSONObject r22 = (JSONObject) r2;
        String r3 = r22.optString("tag");
        if (r3 == null) goto L27;
        if ("td".equals(r3) == false) goto L13;
    L15:
        TL_iv.pageTableCell r4 = new TL_iv.pageTableCell();
        r4.header = "th".equals(r3);
        r4.colspan = Integer.parseInt(r22.optString("colspan"));     // Catch: Exception -> L29
        r4.flags |= 2;
    L31:
        r4.rowspan = Integer.parseInt(r22.optString("rowspan"));     // Catch: Exception -> L30
        r4.flags |= 4;
    L18:
        r4.text = trim(parseRichText(r22.getJSONArray("content"), r9));
        if (r22.has("bold") == false) goto L21;
    L22:
        TL_iv.textBold r32 = new TL_iv.textBold();
        r32.text = r4.text;
        r4.text = r32;
    L24:
        if (r22.has("italic") == false) goto L26;
        TL_iv.textItalic r33 = new TL_iv.textItalic();
        r33.text = r4.text;
        r4.text = r33;
    L26:
        r4.align_center = r22.has("xcenter");
        r72.cells.add(r4);
        goto L27
    L21:
        if (r4.header == false) goto L24;
    L13:
        if ("th".equals(r3) == true) goto L15;
    L27:
        r1 = r1 + 1;
        goto L4
    L28:
        return r72;
    }

    public ArrayList<TL_iv.pageTableRow> parseTableRows(String r6, JSONArray r7, TL_iv.TL_page r8) {
        ArrayList<TL_iv.pageTableRow> r0 = new ArrayList();
        new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= r7.length()) goto L15;
        Object r2 = r7.get(r1);
        if ((r2 instanceof JSONObject) == false) goto L14;
        JSONObject r22 = (JSONObject) r2;
        if ("tr".equals(r22.optString("tag")) == false) goto L11;
        r0.add(parseTableRow(r6, r22, r8));
        goto L14
    L11:
        JSONArray r23 = r22.optJSONArray("content");
        if (r23 == null) goto L14;
        r0.addAll(parseTableRows(r6, r23, r8));
    L14:
        r1 = r1 + 1;
        goto L4
    L15:
        return r0;
    }

    public void readHTML(String r10, InputStream r11, Utilities.Callback<JSONObject> r12) {
        if (r12 != null) goto L5;
        return;
    L5:
        if (r11 != null) goto L8;
        r12.run(null);
        return;
    L8:
        Context r1 = LaunchActivity.instance;
        if (r1 != null) goto L11;
        r1 = ApplicationLoader.applicationContext;
    L11:
        Activity r2 = AndroidUtilities.findActivity(r1);
        if (r2 != null) goto L15;
        r12.run(null);
        return;
    L15:
        View r22 = r2.findViewById(android.R.id.content).getRootView();
        if ((r22 instanceof ViewGroup) == true) goto L19;
        r12.run(null);
        return;
    L19:
        AnonymousClass1 r7 = new AnonymousClass1(this, r1);
        ((ViewGroup) r22).addView(r7);
        WebView r6 = new WebView(r1);
        WebSettings r0 = r6.getSettings();
        r0.setAllowContentAccess(false);
        r0.setDatabaseEnabled(false);
        r0.setAllowFileAccess(false);
        r0.setJavaScriptEnabled(true);
        r0.setSaveFormData(false);
        r0.setGeolocationEnabled(false);
        r0.setDomStorageEnabled(false);
        r0.setAllowFileAccessFromFileURLs(false);
        r0.setAllowUniversalAccessFromFileURLs(false);
        r6.setWebViewClient(new AnonymousClass2(this, r11));
        r6.setWebChromeClient(new AnonymousClass3(this));
        r7.addView(r6, LayoutHelper.createFrame(-1, -1.0f));
        r6.addJavascriptInterface(new AnonymousClass4(this, new boolean[]{false}, r6, r7, r12), "Instant");
        r6.loadUrl(r10);
    }

    public void recycle() {
        instants.remove(this.webpage);
        Iterator<Map.Entry<String, Bitmap>> r0 = this.loadedPhotos.entrySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        AndroidUtilities.recycleBitmap(r0.next().getValue());
        goto L4
    L6:
        this.loadedPhotos.clear();
        TLRPC.WebPage r4 = this.webpage;
        if (r4 == null) goto L19;
        TL_iv.Page r42 = r4.cached_page;
        if (r42 == null) goto L28;
        ArrayList<TLRPC.Photo> r43 = r42.photos;
        if (r43 == null) goto L29;
        int r02 = r43.size();
        int r1 = 0;
    L13:
        if (r1 >= r02) goto L30;
        TLRPC.Photo r2 = r43.get(r1);
        r1 = r1 + 1;
        TLRPC.Photo r22 = r2;
        if ((r22 instanceof WebPhoto) == false) goto L13;
        WebPhoto r23 = (WebPhoto) r22;
        HashMap<String, ArrayList<Pair<ImageReceiver, Runnable>>> r3 = loadingPhotos;
        if (r3 == null) goto L13;
        r3.remove(r23.url);
        goto L13
    L30:
        return;
    L29:
        return;
    L28:
        return;
    }
}
