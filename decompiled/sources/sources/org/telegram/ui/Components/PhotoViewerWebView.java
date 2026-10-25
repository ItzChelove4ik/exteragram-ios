package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.exteragram.messenger.ExteraConfig;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.json.JSONObject;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BringAppForegroundService;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.PhotoViewer;

/* loaded from: classes7.dex */
public abstract class PhotoViewerWebView extends FrameLayout {
    private float bufferedPosition;
    private int currentAccount;
    private int currentPosition;
    private TLRPC.WebPage currentWebpage;
    private String currentYoutubeId;
    private TextView errorButton;
    private LinearLayout errorLayout;
    private TextView errorMessage;
    private boolean isPlaying;
    private boolean isTouchDisabled;
    private boolean isYouTube;
    private ActionBarMenuItem menuItem;
    private PhotoViewer photoViewer;
    private View pipItem;
    private float playbackSpeed;
    private RadialProgressView progressBar;
    private View progressBarBlackBackground;
    private Runnable progressRunnable;
    private boolean setPlaybackSpeed;
    private int videoDuration;
    private WebView webView;
    private List<String> youtubeStoryboards;
    private String youtubeStoryboardsSpecUrl;

    /* renamed from: org.telegram.ui.Components.PhotoViewerWebView$1, reason: invalid class name */
    public class AnonymousClass1 extends WebView {
        final /* synthetic */ PhotoViewerWebView this$0;
        final /* synthetic */ Context val$context;

        public AnonymousClass1(PhotoViewerWebView r1, Context r2, Context r3) {
            this.this$0 = r1;
            this.val$context = r3;
            super(r2);
        }

        @Override // android.view.View
        public void draw(Canvas r3) {
            super.draw(r3);
            if (PipVideoOverlay.getInnerView() == this) goto L5;
            return;
        L5:
            if (PhotoViewerWebView.m11766$$Nest$fgetprogressBarBlackBackground(this.this$0).getVisibility() != 0) goto L9;
            r3.drawColor(-16777216);
            this.this$0.drawBlackBackground(r3, getWidth(), getHeight());
            return;
        }

        @Override // android.webkit.WebView, android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            AndroidUtilities.checkAndroidTheme(this.val$context, true);
            super.onAttachedToWindow();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            AndroidUtilities.checkAndroidTheme(this.val$context, false);
            super.onDetachedFromWindow();
        }

        @Override // android.webkit.WebView, android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            this.this$0.processTouch(r2);
            return super.onTouchEvent(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.PhotoViewerWebView$2, reason: invalid class name */
    public class AnonymousClass2 extends WebChromeClient {
        final /* synthetic */ PhotoViewerWebView this$0;

        public AnonymousClass2(PhotoViewerWebView r1) {
            this.this$0 = r1;
        }

        @Override // android.webkit.WebChromeClient
        public Bitmap getDefaultVideoPoster() {
            return Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
        }
    }

    /* renamed from: org.telegram.ui.Components.PhotoViewerWebView$3, reason: invalid class name */
    public class AnonymousClass3 extends WebViewClient {
        final /* synthetic */ PhotoViewerWebView this$0;

        /* renamed from: $r8$lambda$uSRQ_k2tZv-6LBfVig9KcsO8tXQ, reason: not valid java name */
        public static /* synthetic */ void m11780$r8$lambda$uSRQ_k2tZv6LBfVig9KcsO8tXQ(AnonymousClass3 r0, String r1, WebResourceRequest r2) {
            r0.lambda$shouldInterceptRequest$0(r1, r2);
        }

        public AnonymousClass3(PhotoViewerWebView r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$shouldInterceptRequest$0(String r10, WebResourceRequest r11) {
            HttpURLConnection r102 = (HttpURLConnection) new URL(r10).openConnection();     // Catch: Exception -> L28
            r102.setRequestMethod("POST");     // Catch: Exception -> L28
            Iterator<Map.Entry<String, String>> r1 = r11.getRequestHeaders().entrySet().iterator();     // Catch: Exception -> L28
        L5:
            if (r1.hasNext() == false) goto L7;
            Map.Entry<String, String> r2 = r1.next();     // Catch: Exception -> L28
            r102.addRequestProperty(r2.getKey(), r2.getValue());     // Catch: Exception -> L28
            goto L5
        L7:
            r102.setDoOutput(true);     // Catch: Exception -> L28
            OutputStream r12 = r102.getOutputStream();     // Catch: Exception -> L28
            r12.write(new JSONObject().put("context", new JSONObject().put("client", new JSONObject().put("userAgent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36,gzip(gfe)").put("clientName", "WEB").put("clientVersion", r11.getRequestHeaders().get("X-Youtube-Client-Version")).put("osName", "Windows").put("osVersion", "10.0").put("originalUrl", "https://www.youtube.com/watch?v=" + PhotoViewerWebView.m11757$$Nest$fgetcurrentYoutubeId(this.this$0)).put("platform", "DESKTOP"))).put("videoId", PhotoViewerWebView.m11757$$Nest$fgetcurrentYoutubeId(this.this$0)).toString().getBytes("UTF-8"));     // Catch: Exception -> L28
            r12.close();     // Catch: Exception -> L28
            if (r102.getResponseCode() != 200) goto L10;
            InputStream r103 = r102.getInputStream();     // Catch: Exception -> L28
        L11:
            byte[] r112 = new byte[10240];     // Catch: Exception -> L28
            ByteArrayOutputStream r13 = new ByteArrayOutputStream();     // Catch: Exception -> L28
        L12:
            int r22 = r103.read(r112);     // Catch: Exception -> L28
            if (r22 == (-1)) goto L15;
            r13.write(r112, 0, r22);     // Catch: Exception -> L28
            goto L12
        L15:
            r13.close();     // Catch: Exception -> L28
            r103.close();     // Catch: Exception -> L28
            JSONObject r104 = new JSONObject(r13.toString("UTF-8")).optJSONObject("storyboards");     // Catch: Exception -> L28
            if (r104 == null) goto L34;
            JSONObject r105 = r104.optJSONObject("playerStoryboardSpecRenderer");     // Catch: Exception -> L28
            if (r105 == null) goto L35;
            String r106 = r105.optString("spec");     // Catch: Exception -> L28
            if (r106 == null) goto L36;
            int r113 = PhotoViewerWebView.m11768$$Nest$fgetvideoDuration(this.this$0);     // Catch: Exception -> L28
            PhotoViewerWebView r9 = this.this$0;
            if (r113 != 0) goto L26;
            PhotoViewerWebView.m11776$$Nest$fputyoutubeStoryboardsSpecUrl(r9, r106);     // Catch: Exception -> L28
            return;
        L26:
            PhotoViewerWebView.m11778$$Nest$mprocessYoutubeStoryboards(r9, r106);     // Catch: Exception -> L28
            return;
        L36:
            return;
        L35:
            return;
        L34:
            return;
        L10:
            r103 = r102.getErrorStream();     // Catch: Exception -> L28
        L28:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView r1, String r2) {
            super.onPageFinished(r1, r2);
            if (PhotoViewerWebView.m11762$$Nest$fgetisYouTube(this.this$0) == true) goto L6;
            PhotoViewerWebView.m11765$$Nest$fgetprogressBar(this.this$0).setVisibility(4);
            PhotoViewerWebView.m11766$$Nest$fgetprogressBarBlackBackground(this.this$0).setVisibility(4);
            PhotoViewerWebView.m11779$$Nest$msetPipVisibility(this.this$0, true);
            return;
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView r3, WebResourceRequest r4) {
            String r32 = r4.getUrl().toString();
            if (PhotoViewerWebView.m11762$$Nest$fgetisYouTube(this.this$0) == true) goto L5;
            return null;
        L5:
            if (r32.startsWith("https://www.youtube.com/youtubei/v1/player?key=") == false) goto L10;
            Utilities.externalNetworkQueue.postRunnable(new PhotoViewerWebView$3$$ExternalSyntheticLambda0(this, r32, r4));
            return null;
        L10:
            return null;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView r2, String r3) {
            if (PhotoViewerWebView.m11762$$Nest$fgetisYouTube(this.this$0) == false) goto L7;
            Browser.openUrl(r2.getContext(), r3);
            return true;
        L7:
            return super.shouldOverrideUrlLoading(r2, r3);
        }
    }

    /* renamed from: org.telegram.ui.Components.PhotoViewerWebView$4, reason: invalid class name */
    public class AnonymousClass4 extends View {
        final /* synthetic */ PhotoViewerWebView this$0;

        public AnonymousClass4(PhotoViewerWebView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r3) {
            super.onDraw(r3);
            this.this$0.drawBlackBackground(r3, getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public class YoutubeProxy {
        final /* synthetic */ PhotoViewerWebView this$0;

        /* renamed from: org.telegram.ui.Components.PhotoViewerWebView$YoutubeProxy$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ YoutubeProxy this$1;

            public AnonymousClass1(YoutubeProxy r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                PhotoViewerWebView.m11765$$Nest$fgetprogressBar(this.this$1.this$0).setVisibility(8);
            }
        }

        /* renamed from: org.telegram.ui.Components.PhotoViewerWebView$YoutubeProxy$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ YoutubeProxy this$1;

            public AnonymousClass2(YoutubeProxy r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                PhotoViewerWebView.m11766$$Nest$fgetprogressBarBlackBackground(this.this$1.this$0).setVisibility(8);
            }
        }

        /* renamed from: $r8$lambda$9VEFS-PZfRJEoRuKKLofBJbjwbo, reason: not valid java name */
        public static /* synthetic */ void m11781$r8$lambda$9VEFSPZfRJEoRuKKLofBJbjwbo(YoutubeProxy r0, boolean r1, int r2) {
            r0.lambda$onPlayerStateChange$4(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$COqCXVQDcxe8nHs4GqDIlVauDTs(YoutubeProxy r0) {
            r0.lambda$onPlayerStateChange$3();
        }

        public static /* synthetic */ void $r8$lambda$OhqoLXKnwt0ukrW1M17CaRiMCQg(YoutubeProxy r0) {
            r0.lambda$onPlayerLoaded$0();
        }

        public static /* synthetic */ void $r8$lambda$Ukm24SkQH51HiaQwGH2scTtP_wk(YoutubeProxy r0, View r1) {
            r0.lambda$onPlayerError$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$mT0TQ6AOQSmUEJ_bjr9z0D8BDrQ(YoutubeProxy r0, int r1) {
            r0.lambda$onPlayerError$2(r1);
        }

        private YoutubeProxy(PhotoViewerWebView r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ YoutubeProxy(PhotoViewerWebView r1, PhotoViewerWebViewIA r2) {
            this(r1);
        }

        private /* synthetic */ void lambda$onPlayerError$1(View r3) {
            r3.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(PhotoViewerWebView.m11756$$Nest$fgetcurrentWebpage(this.this$0).url)));
        }

        private /* synthetic */ void lambda$onPlayerError$2(int r8) {
            PhotoViewerWebView.m11758$$Nest$fgeterrorButton(this.this$0).setVisibility(8);
            PhotoViewerWebView.m11769$$Nest$fgetwebView(this.this$0).setVisibility(8);
            if (PhotoViewerWebView.m11759$$Nest$fgeterrorLayout(this.this$0).getVisibility() != 8) goto L6;
            PhotoViewerWebView.m11759$$Nest$fgeterrorLayout(this.this$0).setVisibility(0);
            PhotoViewerWebView.m11759$$Nest$fgeterrorLayout(this.this$0).animate().cancel();
            PhotoViewerWebView.m11759$$Nest$fgeterrorLayout(this.this$0).animate().alpha(1.0f).setDuration(150).start();
        L6:
            if (PhotoViewerWebView.m11765$$Nest$fgetprogressBar(this.this$0).getAlpha() != 1.0f) goto L9;
            PhotoViewerWebView.m11765$$Nest$fgetprogressBar(this.this$0).animate().cancel();
            PhotoViewerWebView.m11765$$Nest$fgetprogressBar(this.this$0).animate().alpha(0.0f).setDuration(150).setListener(new AnonymousClass1(this));
        L9:
            if (PhotoViewerWebView.m11766$$Nest$fgetprogressBarBlackBackground(this.this$0).getAlpha() != 1.0f) goto L12;
            PhotoViewerWebView.m11766$$Nest$fgetprogressBarBlackBackground(this.this$0).animate().cancel();
            PhotoViewerWebView.m11766$$Nest$fgetprogressBarBlackBackground(this.this$0).animate().alpha(0.0f).setDuration(150).setListener(new AnonymousClass2(this));
        L12:
            if (r8 != 2) goto L14;
            PhotoViewerWebView.m11760$$Nest$fgeterrorMessage(this.this$0).setText(LocaleController.getString(R.string.YouTubeVideoErrorInvalid));
            return;
        L14:
            if (r8 != 5) goto L16;
            PhotoViewerWebView.m11760$$Nest$fgeterrorMessage(this.this$0).setText(LocaleController.getString(R.string.YouTubeVideoErrorHTML));
            return;
        L16:
            if (r8 != 150) goto L18;
        L24:
            PhotoViewerWebView.m11760$$Nest$fgeterrorMessage(this.this$0).setText(LocaleController.getString(R.string.YouTubeVideoErrorNotAvailableInApp));
            PhotoViewerWebView.m11758$$Nest$fgeterrorButton(this.this$0).setText(LocaleController.getString(R.string.YouTubeVideoErrorOpenExternal));
            PhotoViewerWebView.m11758$$Nest$fgeterrorButton(this.this$0).setVisibility(0);
            PhotoViewerWebView.m11758$$Nest$fgeterrorButton(this.this$0).setOnClickListener(new PhotoViewerWebView$YoutubeProxy$$ExternalSyntheticLambda4(this));
            return;
        L18:
            if (r8 != 100) goto L20;
            PhotoViewerWebView.m11760$$Nest$fgeterrorMessage(this.this$0).setText(LocaleController.getString(R.string.YouTubeVideoErrorNotFound));
            return;
        L20:
            if (r8 == 101) goto L24;
        }

        private /* synthetic */ void lambda$onPlayerLoaded$0() {
            PhotoViewerWebView.m11765$$Nest$fgetprogressBar(this.this$0).setVisibility(4);
            if (PhotoViewerWebView.m11767$$Nest$fgetsetPlaybackSpeed(this.this$0) == false) goto L5;
            PhotoViewerWebView.m11774$$Nest$fputsetPlaybackSpeed(this.this$0, false);
            PhotoViewerWebView r0 = this.this$0;
            r0.setPlaybackSpeed(PhotoViewerWebView.m11764$$Nest$fgetplaybackSpeed(r0));
        L5:
            PhotoViewerWebView.m11779$$Nest$msetPipVisibility(this.this$0, true);
            if (PhotoViewerWebView.m11763$$Nest$fgetphotoViewer(this.this$0) == null) goto L9;
            PhotoViewerWebView.m11763$$Nest$fgetphotoViewer(this.this$0).checkFullscreenButton();
            return;
        }

        private /* synthetic */ void lambda$onPlayerStateChange$3() {
            PhotoViewerWebView.m11766$$Nest$fgetprogressBarBlackBackground(this.this$0).setVisibility(4);
        }

        private /* synthetic */ void lambda$onPlayerStateChange$4(boolean r1, int r2) {
            PhotoViewerWebView.m11763$$Nest$fgetphotoViewer(this.this$0).updateWebPlayerState(r1, r2);
        }

        @JavascriptInterface
        public void onPlayerError(String r2) {
            AndroidUtilities.runOnUIThread(new PhotoViewerWebView$YoutubeProxy$$ExternalSyntheticLambda0(this, Integer.parseInt(r2)));
        }

        @JavascriptInterface
        public void onPlayerLoaded() {
            AndroidUtilities.runOnUIThread(new PhotoViewerWebView$YoutubeProxy$$ExternalSyntheticLambda1(this));
        }

        @JavascriptInterface
        public void onPlayerNotifyBufferedPosition(float r1) {
            PhotoViewerWebView.m11771$$Nest$fputbufferedPosition(this.this$0, r1);
        }

        @JavascriptInterface
        public void onPlayerNotifyCurrentPosition(int r1) {
            PhotoViewerWebView.m11772$$Nest$fputcurrentPosition(this.this$0, r1 * 1000);
        }

        @JavascriptInterface
        public void onPlayerNotifyDuration(int r2) {
            PhotoViewerWebView.m11775$$Nest$fputvideoDuration(this.this$0, r2 * 1000);
            if (PhotoViewerWebView.m11770$$Nest$fgetyoutubeStoryboardsSpecUrl(this.this$0) == null) goto L6;
            PhotoViewerWebView r22 = this.this$0;
            PhotoViewerWebView.m11778$$Nest$mprocessYoutubeStoryboards(r22, PhotoViewerWebView.m11770$$Nest$fgetyoutubeStoryboardsSpecUrl(r22));
            PhotoViewerWebView.m11776$$Nest$fputyoutubeStoryboardsSpecUrl(this.this$0, null);
            return;
        }

        @JavascriptInterface
        public void onPlayerStateChange(String r7) {
            int r72 = Integer.parseInt(r7);
            boolean r0 = PhotoViewerWebView.m11761$$Nest$fgetisPlaying(this.this$0);
            PhotoViewerWebView r1 = this.this$0;
            boolean r2 = false;
            int r4 = 1;
            if (r72 == 1) goto L7;
            if (r72 == 3) goto L7;
            boolean r5 = false;
        L8:
            PhotoViewerWebView.m11773$$Nest$fputisPlaying(r1, r5);
            PhotoViewerWebView.m11777$$Nest$mcheckPlayingPoll(this.this$0, r0);
            if (r72 == 0) goto L18;
            if (r72 != 1) goto L12;
            r2 = true;
        L16:
            r4 = 3;
        L19:
            if (r4 == 3) goto L21;
        L23:
            AndroidUtilities.runOnUIThread(new PhotoViewerWebView$YoutubeProxy$$ExternalSyntheticLambda3(this, r2, r4));
            return;
        L21:
            if (PhotoViewerWebView.m11766$$Nest$fgetprogressBarBlackBackground(this.this$0).getVisibility() == 4) goto L23;
            AndroidUtilities.runOnUIThread(new PhotoViewerWebView$YoutubeProxy$$ExternalSyntheticLambda2(this), 300);
            goto L23
        L12:
            if (r72 == 2) goto L16;
            if (r72 != 3) goto L19;
            r2 = true;
            r4 = 2;
            goto L19
        L18:
            r4 = 4;
        L7:
            r5 = true;
            goto L8
        }
    }

    public static /* synthetic */ void $r8$lambda$N2JnsHnbZBqjJ_XObZbOFXbCUdM(PhotoViewerWebView r0, long r1, boolean r3) {
        r0.lambda$seekTo$1(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$j4E50re73ZDLn8M8hHLmZK74KMI(PhotoViewerWebView r0) {
        r0.lambda$new$0();
    }

    /* renamed from: -$$Nest$fgetcurrentWebpage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.WebPage m11756$$Nest$fgetcurrentWebpage(PhotoViewerWebView r0) {
        return r0.currentWebpage;
    }

    /* renamed from: -$$Nest$fgetcurrentYoutubeId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m11757$$Nest$fgetcurrentYoutubeId(PhotoViewerWebView r0) {
        return r0.currentYoutubeId;
    }

    /* renamed from: -$$Nest$fgeterrorButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m11758$$Nest$fgeterrorButton(PhotoViewerWebView r0) {
        return r0.errorButton;
    }

    /* renamed from: -$$Nest$fgeterrorLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m11759$$Nest$fgeterrorLayout(PhotoViewerWebView r0) {
        return r0.errorLayout;
    }

    /* renamed from: -$$Nest$fgeterrorMessage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m11760$$Nest$fgeterrorMessage(PhotoViewerWebView r0) {
        return r0.errorMessage;
    }

    /* renamed from: -$$Nest$fgetisPlaying, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11761$$Nest$fgetisPlaying(PhotoViewerWebView r0) {
        return r0.isPlaying;
    }

    /* renamed from: -$$Nest$fgetisYouTube, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11762$$Nest$fgetisYouTube(PhotoViewerWebView r0) {
        return r0.isYouTube;
    }

    /* renamed from: -$$Nest$fgetphotoViewer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoViewer m11763$$Nest$fgetphotoViewer(PhotoViewerWebView r0) {
        return r0.photoViewer;
    }

    /* renamed from: -$$Nest$fgetplaybackSpeed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11764$$Nest$fgetplaybackSpeed(PhotoViewerWebView r0) {
        return r0.playbackSpeed;
    }

    /* renamed from: -$$Nest$fgetprogressBar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RadialProgressView m11765$$Nest$fgetprogressBar(PhotoViewerWebView r0) {
        return r0.progressBar;
    }

    /* renamed from: -$$Nest$fgetprogressBarBlackBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m11766$$Nest$fgetprogressBarBlackBackground(PhotoViewerWebView r0) {
        return r0.progressBarBlackBackground;
    }

    /* renamed from: -$$Nest$fgetsetPlaybackSpeed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11767$$Nest$fgetsetPlaybackSpeed(PhotoViewerWebView r0) {
        return r0.setPlaybackSpeed;
    }

    /* renamed from: -$$Nest$fgetvideoDuration, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11768$$Nest$fgetvideoDuration(PhotoViewerWebView r0) {
        return r0.videoDuration;
    }

    /* renamed from: -$$Nest$fgetwebView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WebView m11769$$Nest$fgetwebView(PhotoViewerWebView r0) {
        return r0.webView;
    }

    /* renamed from: -$$Nest$fgetyoutubeStoryboardsSpecUrl, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m11770$$Nest$fgetyoutubeStoryboardsSpecUrl(PhotoViewerWebView r0) {
        return r0.youtubeStoryboardsSpecUrl;
    }

    /* renamed from: -$$Nest$fputbufferedPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11771$$Nest$fputbufferedPosition(PhotoViewerWebView r0, float r1) {
        r0.bufferedPosition = r1;
    }

    /* renamed from: -$$Nest$fputcurrentPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11772$$Nest$fputcurrentPosition(PhotoViewerWebView r0, int r1) {
        r0.currentPosition = r1;
    }

    /* renamed from: -$$Nest$fputisPlaying, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11773$$Nest$fputisPlaying(PhotoViewerWebView r0, boolean r1) {
        r0.isPlaying = r1;
    }

    /* renamed from: -$$Nest$fputsetPlaybackSpeed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11774$$Nest$fputsetPlaybackSpeed(PhotoViewerWebView r0, boolean r1) {
        r0.setPlaybackSpeed = r1;
    }

    /* renamed from: -$$Nest$fputvideoDuration, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11775$$Nest$fputvideoDuration(PhotoViewerWebView r0, int r1) {
        r0.videoDuration = r1;
    }

    /* renamed from: -$$Nest$fputyoutubeStoryboardsSpecUrl, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11776$$Nest$fputyoutubeStoryboardsSpecUrl(PhotoViewerWebView r0, String r1) {
        r0.youtubeStoryboardsSpecUrl = r1;
    }

    /* renamed from: -$$Nest$mcheckPlayingPoll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11777$$Nest$mcheckPlayingPoll(PhotoViewerWebView r0, boolean r1) {
        r0.checkPlayingPoll(r1);
    }

    /* renamed from: -$$Nest$mprocessYoutubeStoryboards, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11778$$Nest$mprocessYoutubeStoryboards(PhotoViewerWebView r0, String r1) {
        r0.processYoutubeStoryboards(r1);
    }

    /* renamed from: -$$Nest$msetPipVisibility, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11779$$Nest$msetPipVisibility(PhotoViewerWebView r0, boolean r1) {
        r0.setPipVisibility(r1);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public PhotoViewerWebView(PhotoViewer r13, Context r14, View r15) {
        super(r14);
        this.currentAccount = UserConfig.selectedAccount;
        this.youtubeStoryboards = new ArrayList();
        this.progressRunnable = new PhotoViewerWebView$$ExternalSyntheticLambda1(this);
        this.photoViewer = r13;
        this.pipItem = r15;
        AnonymousClass1 r132 = new AnonymousClass1(this, r14, r14);
        this.webView = r132;
        r132.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setDomStorageEnabled(true);
        this.webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        this.webView.getSettings().setMixedContentMode(0);
        CookieManager.getInstance().setAcceptThirdPartyCookies(this.webView, true);
        this.webView.setWebChromeClient(new AnonymousClass2(this));
        this.webView.setWebViewClient(new AnonymousClass3(this));
        addView(this.webView, LayoutHelper.createFrame(-1, -1, 51));
        LinearLayout r133 = new LinearLayout(r14);
        this.errorLayout = r133;
        r133.setOrientation(1);
        this.errorLayout.setGravity(17);
        this.errorLayout.setVisibility(8);
        addView(this.errorLayout, LayoutHelper.createFrame(-2, -2, 17));
        TextView r134 = new TextView(r14);
        this.errorMessage = r134;
        r134.setTextSize(1, 16.0f);
        this.errorMessage.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
        this.errorMessage.setGravity(17);
        this.errorLayout.addView(this.errorMessage, LayoutHelper.createLinear(-2, -2, 1));
        TextView r135 = new TextView(r14);
        this.errorButton = r135;
        r135.setTextSize(1, 16.0f);
        TextView r136 = this.errorButton;
        int r5 = Theme.key_windowBackgroundWhiteBlueText;
        r136.setTextColor(Theme.getColor(r5));
        this.errorButton.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(8.0f));
        this.errorButton.setBackground(Theme.AdaptiveRipple.rectByKey(r5, new float[]{12.0f}));
        this.errorButton.setVisibility(8);
        this.errorLayout.addView(this.errorButton, LayoutHelper.createLinear(-2, -2, 1, 0, 8, 0, 0));
        AnonymousClass4 r137 = new AnonymousClass4(this, r14);
        this.progressBarBlackBackground = r137;
        r137.setBackgroundColor(-16777216);
        this.progressBarBlackBackground.setVisibility(4);
        addView(this.progressBarBlackBackground, LayoutHelper.createFrame(-1, -1.0f));
        RadialProgressView r138 = new RadialProgressView(r14);
        this.progressBar = r138;
        r138.setVisibility(4);
        addView(this.progressBar, LayoutHelper.createFrame(-2, -2, 17));
    }

    private void checkPlayingPoll(boolean r3) {
        if (r3 == false) goto L4;
    L7:
        if (r3 == true) goto L9;
        return;
    L9:
        if (this.isPlaying == true) goto L13;
        AndroidUtilities.cancelRunOnUIThread(this.progressRunnable);
        return;
    L13:
        return;
    L4:
        if (this.isPlaying == false) goto L7;
        AndroidUtilities.runOnUIThread(this.progressRunnable, 500);
    }

    private /* synthetic */ void lambda$new$0() {
        if (this.isYouTube == false) goto L6;
        runJsCode("pollPosition();");
    L6:
        if (this.isPlaying == false) goto L9;
        AndroidUtilities.runOnUIThread(this.progressRunnable, 500);
        return;
    }

    private /* synthetic */ void lambda$seekTo$1(long r3, boolean r5) {
        runJsCode("seekTo(" + Math.round(r3 / 1000.0f) + ", " + r5 + ");");
        AndroidUtilities.runOnUIThread(new PhotoViewerWebView$$ExternalSyntheticLambda3(this), 100);
    }

    private void processYoutubeStoryboards(String r11) {
        int r0 = getVideoDuration() / 1000;
        this.youtubeStoryboards.clear();
        if (r0 <= 15) goto L27;
        String[] r112 = r11.split("\\|");
        StringBuilder r2 = new StringBuilder();
        int r3 = 0;
        r2.append(r112[0].split("\\$")[0]);
        r2.append("2/");
        String r22 = r2.toString();
        String r4 = r112[0].split("\\$N")[1];
        if (r112.length != 3) goto L9;
        String r113 = r112[2].split("M#")[1];
    L13:
        if (r0 > 100) goto L17;
        double r02 = Math.ceil(r0 / 25.0f);
    L15:
        int r03 = (int) r02;
    L25:
        if (r3 >= r03) goto L29;
        this.youtubeStoryboards.add(String.format(Locale.ROOT, "%sM%d%s&sigh=%s", new Object[]{r22, Integer.valueOf(r3), r4, r113}));
        r3 = r3 + 1;
        goto L25
    L29:
        return;
    L17:
        if (r0 > 250) goto L20;
        r02 = Math.ceil((r0 / 2.0f) / 25.0f);
        goto L15
    L20:
        if (r0 > 500) goto L22;
        r02 = Math.ceil((r0 / 4.0f) / 25.0f);
        goto L15
    L22:
        if (r0 > 1000) goto L24;
        r02 = Math.ceil((r0 / 5.0f) / 25.0f);
        goto L15
    L24:
        r02 = Math.ceil((r0 / 10.0f) / 25.0f);
        goto L15
    L9:
        if (r112.length != 2) goto L11;
        r113 = r112[1].split("t#")[1];
        goto L13
    L11:
        r113 = r112[3].split("M#")[1];
        goto L13
    }

    private void runJsCode(String r2) {
        this.webView.evaluateJavascript(r2, null);
    }

    private void setPipVisibility(boolean r3) {
        this.pipItem.setEnabled(r3);
        View r0 = this.pipItem;
        if (r3 == false) goto L5;
        float r1 = 1.0f;
    L6:
        r0.setAlpha(r1);
        if (this.menuItem != null) goto L9;
        return;
    L9:
        if (ExteraConfig.getCenterTitle() == false) goto L17;
        ActionBarMenuItem r2 = this.menuItem;
        if (r3 == false) goto L14;
        r2.showSubItem(21);
        return;
    L14:
        r2.hideSubItem(21);
        return;
    L17:
        return;
    L5:
        r1 = 0.5f;
        goto L6
    }

    public boolean checkInlinePermissions() {
        if (Settings.canDrawOverlays(getContext()) == false) goto L6;
        return true;
    L6:
        AlertsCreator.createDrawOverlayPermissionDialog((Activity) getContext(), null);
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r2) {
        if (this.isTouchDisabled == false) goto L7;
        return false;
    L7:
        return super.dispatchTouchEvent(r2);
    }

    public abstract void drawBlackBackground(Canvas r1, int r2, int r3);

    public void exitFromPip() {
        if (this.webView != null) goto L6;
        return;
    L6:
        if (ApplicationLoader.mainInterfacePaused == true) goto L16;
    L11:
        this.progressBarBlackBackground.setVisibility(0);
        ViewGroup r0 = (ViewGroup) this.webView.getParent();
        if (r0 == null) goto L14;
        r0.removeView(this.webView);
    L14:
        addView(this.webView, 0, LayoutHelper.createFrame(-1, -1, 51));
        PipVideoOverlay.dismiss();
        return;
    L16:
        getContext().startService(new Intent(ApplicationLoader.applicationContext, BringAppForegroundService.class));     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        FileLog.e(th);
        goto L11
    }

    public float getBufferedPosition() {
        return this.bufferedPosition;
    }

    public int getCurrentPosition() {
        return this.currentPosition;
    }

    public int getVideoDuration() {
        return this.videoDuration;
    }

    public WebView getWebView() {
        return this.webView;
    }

    public String getYoutubeStoryboard(int r5) {
        int r0 = getVideoDuration() / 1000;
        if (r0 > 100) goto L7;
        float r52 = r5;
    L5:
        int r53 = (int) (r52 / 25.0f);
    L16:
        if (r53 < this.youtubeStoryboards.size()) goto L18;
        return null;
    L18:
        return this.youtubeStoryboards.get(r53);
    L7:
        if (r0 > 250) goto L10;
        r53 = ((int) (r5 / 2.0f)) / 25;
        goto L16
    L10:
        if (r0 > 500) goto L12;
        r53 = ((int) (r5 / 4.0f)) / 25;
        goto L16
    L12:
        if (r0 > 1000) goto L14;
        r53 = ((int) (r5 / 5.0f)) / 25;
        goto L16
    L14:
        r52 = r5 / 10.0f;
        goto L5
    }

    public int getYoutubeStoryboardImageCount(int r5) {
        int r52 = this.youtubeStoryboards.indexOf(getYoutubeStoryboard(r5));
        if (r52 != (-1)) goto L5;
        return 0;
    L5:
        if (r52 != (this.youtubeStoryboards.size() - 1)) goto L21;
        int r53 = getVideoDuration() / 1000;
        if (r53 > 100) goto L11;
        double r2 = Math.ceil(r53);
    L20:
        return Math.min(25, (((int) r2) - ((this.youtubeStoryboards.size() - 1) * 25)) + 1);
    L11:
        if (r53 > 250) goto L14;
        r2 = Math.ceil(r53 / 2.0f);
        goto L20
    L14:
        if (r53 > 500) goto L16;
        r2 = Math.ceil(r53 / 4.0f);
        goto L20
    L16:
        if (r53 > 1000) goto L18;
        r2 = Math.ceil(r53 / 5.0f);
        goto L20
    L18:
        r2 = Math.ceil(r53 / 10.0f);
        goto L20
    L21:
        return 25;
    }

    public int getYoutubeStoryboardImageIndex(int r3) {
        int r2 = getVideoDuration() / 1000;
        if (r2 > 100) goto L7;
        return ((int) Math.ceil(r3)) % 25;
    L7:
        if (r2 > 250) goto L11;
        return ((int) Math.ceil(r3 / 2.0f)) % 25;
    L11:
        if (r2 <= 500) goto L13;
        if (r2 > 1000) goto L18;
        return ((int) Math.ceil(r3 / 5.0f)) % 25;
    L18:
        return ((int) Math.ceil(r3 / 10.0f)) % 25;
    L13:
        return ((int) Math.ceil(r3 / 4.0f)) % 25;
    }

    public boolean hasYoutubeStoryboards() {
        return !this.youtubeStoryboards.isEmpty();
    }

    public void hideControls() {
    }

    @SuppressLint({"AddJavascriptInterface"})
    public void init(int r12, TLRPC.WebPage r13) {
        this.currentWebpage = r13;
        this.currentYoutubeId = WebPlayerView.getYouTubeVideoId(r13.embed_url);
        String r2 = r13.url;
        requestLayout();
    L23:
        e = move-exception;
        FileLog.e(e);
    L34:
        setPipVisibility(false);
        this.progressBar.setVisibility(0);
        if (this.currentYoutubeId == null) goto L37;
        this.progressBarBlackBackground.setVisibility(0);
    L37:
        this.webView.setVisibility(0);
        this.webView.setKeepScreenOn(true);
        if (this.currentYoutubeId != null) goto L40;
        return;
    L40:
        if ("disabled".equals(MessagesController.getInstance(this.currentAccount).youtubePipType) == false) goto L47;
        setPipVisibility(false);
        this.pipItem.setVisibility(8);
        return;
    L47:
        return;
    L4:
        if (this.currentYoutubeId == null) goto L31;
        this.progressBarBlackBackground.setVisibility(0);     // Catch: Exception -> L23
        this.isYouTube = true;     // Catch: Exception -> L23
        String r6 = null;
        this.webView.addJavascriptInterface(new YoutubeProxy(this, null), "YoutubeProxy");     // Catch: Exception -> L23
        if (r2 != null) goto L44;
    L25:
        int r132 = 0;
    L26:
        InputStream r122 = getContext().getAssets().open("youtube_embed.html");     // Catch: Exception -> L23
        ByteArrayOutputStream r0 = new ByteArrayOutputStream();     // Catch: Exception -> L23
        byte[] r1 = new byte[10240];     // Catch: Exception -> L23
    L27:
        int r22 = r122.read(r1);     // Catch: Exception -> L23
        if (r22 == (-1)) goto L30;
        r0.write(r1, 0, r22);     // Catch: Exception -> L23
        goto L27
    L30:
        r0.close();     // Catch: Exception -> L23
        r122.close();     // Catch: Exception -> L23
        this.webView.loadDataWithBaseURL("https://messenger.telegram.org/", String.format(Locale.US, r0.toString("UTF-8"), new Object[]{this.currentYoutubeId, Integer.valueOf(r132)}), "text/html", "UTF-8", "https://youtube.com");     // Catch: Exception -> L23
        goto L34
    L44:
        Uri r133 = Uri.parse(r2);     // Catch: Exception -> L10
        if (r12 <= 0) goto L12;
        r6 = _UrlKt.FRAGMENT_ENCODE_SET + r12;     // Catch: Exception -> L10
    L12:
        if (r6 != null) goto L16;
        r6 = r133.getQueryParameter("t");     // Catch: Exception -> L10
        if (r6 != null) goto L16;
        r6 = r133.getQueryParameter("time_continue");     // Catch: Exception -> L10
    L16:
        if (r6 == null) goto L25;
        if (r6.contains("m") == false) goto L20;
        String[] r123 = r6.split("m");     // Catch: Exception -> L10
        r132 = (Utilities.parseInt(r123[0]) * 60) + Utilities.parseInt(r123[1]);     // Catch: Exception -> L10
        goto L26
    L20:
        r132 = Utilities.parseInt(r6);     // Catch: Exception -> L10
    L10:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L23
        goto L25
    L31:
        HashMap r124 = new HashMap();     // Catch: Exception -> L23
        r124.put("Referer", "messenger.telegram.org");     // Catch: Exception -> L23
        this.webView.loadUrl(r13.embed_url, r124);     // Catch: Exception -> L23
        goto L34
    }

    public boolean isControllable() {
        return isYouTube();
    }

    public boolean isInAppOnly() {
        if (this.isYouTube == true) goto L5;
        return false;
    L5:
        if ("inapp".equals(MessagesController.getInstance(this.currentAccount).youtubePipType) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isLoaded() {
        if (this.progressBar.getVisibility() == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public boolean isYouTube() {
        return this.isYouTube;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r7, int r8) {
        if (this.webView.getParent() != this) goto L12;
        TLRPC.WebPage r0 = this.currentWebpage;
        int r1 = r0.embed_width;
        int r2 = 100;
        if (r1 != 0) goto L8;
        r1 = 100;
    L8:
        int r02 = r0.embed_height;
        if (r02 == 0) goto L11;
        r2 = r02;
    L11:
        int r03 = View.MeasureSpec.getSize(r7);
        int r3 = View.MeasureSpec.getSize(r8);
        float r12 = r1;
        float r22 = r2;
        float r4 = Math.min(r03 / r12, r3 / r22);
        FrameLayout.LayoutParams r5 = (FrameLayout.LayoutParams) this.webView.getLayoutParams();
        int r13 = (int) (r12 * r4);
        r5.width = r13;
        int r23 = (int) (r22 * r4);
        r5.height = r23;
        r5.topMargin = (r3 - r23) / 2;
        r5.leftMargin = (r03 - r13) / 2;
    L12:
        super.onMeasure(r7, r8);
    }

    public boolean openInPip() {
        boolean r0 = isInAppOnly();
        if (r0 == true) goto L8;
        if (checkInlinePermissions() == true) goto L8;
        return false;
    L8:
        if (this.progressBar.getVisibility() != 0) goto L11;
        return false;
    L11:
        if (PipVideoOverlay.isVisible() == false) goto L14;
        PipVideoOverlay.dismiss();
        AndroidUtilities.runOnUIThread(new PhotoViewerWebView$$ExternalSyntheticLambda2(this), 300);
        return true;
    L14:
        this.progressBarBlackBackground.setVisibility(0);
        Activity r1 = (Activity) getContext();
        WebView r3 = this.webView;
        TLRPC.WebPage r2 = this.currentWebpage;
        if (PipVideoOverlay.show(r0, r1, this, r3, r2.embed_width, r2.embed_height, false) == false) goto L17;
        PipVideoOverlay.setPhotoViewer(PhotoViewer.getInstance());
    L17:
        return true;
    }

    public void pauseVideo() {
        if (this.isPlaying == true) goto L5;
        return;
    L5:
        if (isControllable() == false) goto L10;
        runJsCode("pauseVideo();");
        this.isPlaying = false;
        checkPlayingPoll(true);
        return;
    }

    public void playVideo() {
        if (this.isPlaying == false) goto L5;
        return;
    L5:
        if (isControllable() == false) goto L10;
        runJsCode("playVideo();");
        this.isPlaying = true;
        checkPlayingPoll(false);
        return;
    }

    public abstract void processTouch(MotionEvent r1);

    public void release() {
        this.webView.stopLoading();
        this.webView.loadUrl("about:blank");
        this.webView.destroy();
        this.videoDuration = 0;
        this.currentPosition = 0;
        AndroidUtilities.cancelRunOnUIThread(this.progressRunnable);
    }

    public void seekTo(long r2) {
        seekTo(r2, true);
    }

    public void seekTo(long r3, boolean r5) {
        boolean r0 = this.isPlaying;
        this.currentPosition = (int) r3;
        if (r0 == false) goto L5;
        pauseVideo();
    L5:
        if (r0 == false) goto L8;
        AndroidUtilities.runOnUIThread(new PhotoViewerWebView$$ExternalSyntheticLambda0(this, r3, r5), 100);
        return;
    L8:
        runJsCode("seekTo(" + Math.round(r3 / 1000.0f) + ", " + r5 + ");");
    }

    public void setMenuItem(ActionBarMenuItem r1) {
        this.menuItem = r1;
    }

    public void setPlaybackSpeed(float r3) {
        this.playbackSpeed = r3;
        if (this.progressBar.getVisibility() != 0) goto L5;
        this.setPlaybackSpeed = true;
        return;
    L5:
        if (this.isYouTube == false) goto L10;
        runJsCode("setPlaybackSpeed(" + r3 + ");");
        return;
    }

    public void setTouchDisabled(boolean r1) {
        this.isTouchDisabled = r1;
    }

    public void showControls() {
    }
}
