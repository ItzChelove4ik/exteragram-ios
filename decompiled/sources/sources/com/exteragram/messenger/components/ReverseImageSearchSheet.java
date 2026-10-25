package com.exteragram.messenger.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.exteragram.messenger.adblock.WebAdBlocker;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import java.io.ByteArrayOutputStream;
import java.io.File;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes4.dex */
public class ReverseImageSearchSheet extends BottomSheet {
    private final WebAdBlocker adBlocker;
    private volatile String currentUrl;
    private int injectedAtStartCount;
    private int pageStartCount;
    private String pendingScript;
    private final Provider provider;
    private Runnable revealTimeout;
    private boolean revealed;
    private final CircularProgressIndicator spinner;
    private boolean uploadInjected;
    private WebView webView;

    /* renamed from: com.exteragram.messenger.components.ReverseImageSearchSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ ReverseImageSearchSheet this$0;

        public AnonymousClass1(ReverseImageSearchSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30));
        }
    }

    /* renamed from: com.exteragram.messenger.components.ReverseImageSearchSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends WebViewClient {
        final /* synthetic */ ReverseImageSearchSheet this$0;
        final /* synthetic */ Provider val$provider;

        public AnonymousClass2(ReverseImageSearchSheet r1, Provider r2) {
            this.this$0 = r1;
            this.val$provider = r2;
        }

        @Override // android.webkit.WebViewClient
        public void doUpdateVisitedHistory(WebView r1, String r2, boolean r3) {
            super.doUpdateVisitedHistory(r1, r2, r3);
            ReverseImageSearchSheet.m1226$$Nest$mhideProviderAds(this.this$0, r1);
            ReverseImageSearchSheet.m1227$$Nest$monUrlChanged(this.this$0, r2);
            if (this.val$provider == Provider.TINEYE) goto L5;
            return;
        L5:
            if (ReverseImageSearchSheet.m1221$$Nest$fgetuploadInjected(this.this$0) == false) goto L18;
            if (r2 == null) goto L19;
            String r12 = Uri.parse(r2).getPath();     // Catch: Exception -> L9
        L10:
            if (r12 != null) goto L12;
            return;
        L12:
            if (r12.startsWith("/search") == false) goto L21;
            ReverseImageSearchSheet.m1228$$Nest$mreveal(this.this$0);
            return;
        L21:
            return;
        L9:
            r12 = null;
            goto L10
        L19:
            return;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView r3, String r4) {
            super.onPageFinished(r3, r4);
            ReverseImageSearchSheet.m1227$$Nest$monUrlChanged(this.this$0, r4);
            if (ReverseImageSearchSheet.m1221$$Nest$fgetuploadInjected(this.this$0) == true) goto L13;
            if (r4 != null) goto L6;
        L15:
            ReverseImageSearchSheet.m1216$$Nest$fgetadBlocker(this.this$0).injectCosmetics(r4);
            ReverseImageSearchSheet.m1226$$Nest$mhideProviderAds(this.this$0, r3);
            return;
        L6:
            if (r4.startsWith("http://") == false) goto L8;
        L9:
            ReverseImageSearchSheet.m1225$$Nest$fputuploadInjected(this.this$0, true);
            ReverseImageSearchSheet r0 = this.this$0;
            ReverseImageSearchSheet.m1222$$Nest$fputinjectedAtStartCount(r0, ReverseImageSearchSheet.m1219$$Nest$fgetpageStartCount(r0));
            if (ReverseImageSearchSheet.m1220$$Nest$fgetpendingScript(this.this$0) == null) goto L15;
            r3.evaluateJavascript(ReverseImageSearchSheet.m1220$$Nest$fgetpendingScript(this.this$0), null);
            ReverseImageSearchSheet.m1224$$Nest$fputpendingScript(this.this$0, null);
            goto L15
        L8:
            if (r4.startsWith("https://") == false) goto L15;
        L13:
            if (ReverseImageSearchSheet.m1219$$Nest$fgetpageStartCount(this.this$0) <= ReverseImageSearchSheet.m1218$$Nest$fgetinjectedAtStartCount(this.this$0)) goto L15;
            ReverseImageSearchSheet.m1228$$Nest$mreveal(this.this$0);
            goto L15
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView r1, String r2, Bitmap r3) {
            super.onPageStarted(r1, r2, r3);
            ReverseImageSearchSheet r12 = this.this$0;
            ReverseImageSearchSheet.m1223$$Nest$fputpageStartCount(r12, ReverseImageSearchSheet.m1219$$Nest$fgetpageStartCount(r12) + 1);
            ReverseImageSearchSheet.m1227$$Nest$monUrlChanged(this.this$0, r2);
            ReverseImageSearchSheet.m1216$$Nest$fgetadBlocker(this.this$0).onPageStarted(r2);
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView r2, WebResourceRequest r3) {
            if (r3 == null) goto L9;
            if (r3.isForMainFrame() == true) goto L9;
            WebResourceResponse r0 = ReverseImageSearchSheet.m1216$$Nest$fgetadBlocker(this.this$0).interceptRequest(r3);
            if (r0 == null) goto L9;
            return r0;
        L9:
            return super.shouldInterceptRequest(r2, r3);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView r3, WebResourceRequest r4) {
            if (r4 != null) goto L5;
        L16:
            return false;
        L5:
            if (r4.isForMainFrame() == false) goto L16;
            Uri r42 = r4.getUrl();
            if (r42 == null) goto L10;
            String r0 = r42.getHost();
        L11:
            if (r0 == null) goto L16;
            if (ReverseImageSearchSheet.m1229$$Nest$smisProviderHost(this.val$provider, r0) == true) goto L16;
            Browser.openUrlInSystemBrowser(this.this$0.getContext(), r42.toString());
            return true;
        L10:
            r0 = null;
            goto L11
        }
    }

    /* renamed from: com.exteragram.messenger.components.ReverseImageSearchSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ReverseImageSearchSheet this$0;

        public AnonymousClass3(ReverseImageSearchSheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L7;
            this.this$0.dismiss();
            return;
        L7:
            if (r2 == 1) goto L9;
            return;
        L9:
            if (TextUtils.isEmpty(ReverseImageSearchSheet.m1217$$Nest$fgetcurrentUrl(this.this$0)) == true) goto L13;
            Browser.openUrlInSystemBrowser(this.this$0.getContext(), ReverseImageSearchSheet.m1217$$Nest$fgetcurrentUrl(this.this$0));
            return;
        }
    }

    /* renamed from: com.exteragram.messenger.components.ReverseImageSearchSheet$4, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$exteragram$messenger$components$ReverseImageSearchSheet$Provider = null;

        static {
            int[] r0 = new int[Provider.values().length];
            $SwitchMap$com$exteragram$messenger$components$ReverseImageSearchSheet$Provider = r0;
            r0[Provider.GOOGLE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$com$exteragram$messenger$components$ReverseImageSearchSheet$Provider[Provider.BING.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L14:
            $SwitchMap$com$exteragram$messenger$components$ReverseImageSearchSheet$Provider[Provider.TINEYE.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L18:
            $SwitchMap$com$exteragram$messenger$components$ReverseImageSearchSheet$Provider[Provider.YANDEX.ordinal()] = 4;     // Catch: NoSuchFieldError -> L11
            return;
        }
    }

    public enum Provider extends Enum<Provider> {
        private static final /* synthetic */ Provider[] $VALUES = null;
        public static final Provider BING = null;
        public static final Provider GOOGLE = null;
        public static final Provider TINEYE = null;
        public static final Provider YANDEX = null;
        public final String landingUrl;
        public final String title;

        private static /* synthetic */ Provider[] $values() {
            return new Provider[]{YANDEX, GOOGLE, BING, TINEYE};
        }

        static {
            YANDEX = new Provider("YANDEX", 0, "Yandex", "https://yandex.com/images/");
            GOOGLE = new Provider("GOOGLE", 1, "Google", "https://www.google.com/");
            BING = new Provider("BING", 2, "Bing", "https://www.bing.com/images");
            TINEYE = new Provider("TINEYE", 3, "TinEye", "https://tineye.com/");
            $VALUES = $values();
        }

        Provider(String r1, int r2, String r3, String r4) {
            this.title = r3;
            this.landingUrl = r4;
        }

        public static Provider valueOf(String r1) {
            return (Provider) Enum.valueOf(Provider.class, r1);
        }

        public static Provider[] values() {
            return (Provider[]) $VALUES.clone();
        }
    }

    public static /* synthetic */ void $r8$lambda$7s3sLHP6VIIw2BJb1tO5doC6f0I(ReverseImageSearchSheet r0) {
        r0.reveal();
    }

    /* renamed from: $r8$lambda$DAQYPGi0rCY-PxHSj6d0fQR-_FA, reason: not valid java name */
    public static /* synthetic */ void m1215$r8$lambda$DAQYPGi0rCYPxHSj6d0fQR_FA(ReverseImageSearchSheet r0, String r1, Provider r2) {
        r0.lambda$new$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$DZNZENVW3i4uM2AWHzeE_zgG0K4(ReverseImageSearchSheet r0, File r1, Provider r2) {
        r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$i2n7dcPsliCpnXrFejeRyoNmw3A(ReverseImageSearchSheet r0) {
        r0.lambda$reveal$2();
    }

    /* renamed from: -$$Nest$fgetadBlocker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WebAdBlocker m1216$$Nest$fgetadBlocker(ReverseImageSearchSheet r0) {
        return r0.adBlocker;
    }

    /* renamed from: -$$Nest$fgetcurrentUrl, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m1217$$Nest$fgetcurrentUrl(ReverseImageSearchSheet r0) {
        return r0.currentUrl;
    }

    /* renamed from: -$$Nest$fgetinjectedAtStartCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1218$$Nest$fgetinjectedAtStartCount(ReverseImageSearchSheet r0) {
        return r0.injectedAtStartCount;
    }

    /* renamed from: -$$Nest$fgetpageStartCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1219$$Nest$fgetpageStartCount(ReverseImageSearchSheet r0) {
        return r0.pageStartCount;
    }

    /* renamed from: -$$Nest$fgetpendingScript, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m1220$$Nest$fgetpendingScript(ReverseImageSearchSheet r0) {
        return r0.pendingScript;
    }

    /* renamed from: -$$Nest$fgetuploadInjected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1221$$Nest$fgetuploadInjected(ReverseImageSearchSheet r0) {
        return r0.uploadInjected;
    }

    /* renamed from: -$$Nest$fputinjectedAtStartCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1222$$Nest$fputinjectedAtStartCount(ReverseImageSearchSheet r0, int r1) {
        r0.injectedAtStartCount = r1;
    }

    /* renamed from: -$$Nest$fputpageStartCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1223$$Nest$fputpageStartCount(ReverseImageSearchSheet r0, int r1) {
        r0.pageStartCount = r1;
    }

    /* renamed from: -$$Nest$fputpendingScript, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1224$$Nest$fputpendingScript(ReverseImageSearchSheet r0, String r1) {
        r0.pendingScript = r1;
    }

    /* renamed from: -$$Nest$fputuploadInjected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1225$$Nest$fputuploadInjected(ReverseImageSearchSheet r0, boolean r1) {
        r0.uploadInjected = r1;
    }

    /* renamed from: -$$Nest$mhideProviderAds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1226$$Nest$mhideProviderAds(ReverseImageSearchSheet r0, WebView r1) {
        r0.hideProviderAds(r1);
    }

    /* renamed from: -$$Nest$monUrlChanged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1227$$Nest$monUrlChanged(ReverseImageSearchSheet r0, String r1) {
        r0.onUrlChanged(r1);
    }

    /* renamed from: -$$Nest$mreveal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1228$$Nest$mreveal(ReverseImageSearchSheet r0) {
        r0.reveal();
    }

    /* renamed from: -$$Nest$smisProviderHost, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1229$$Nest$smisProviderHost(Provider r0, String r1) {
        return isProviderHost(r0, r1);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public ReverseImageSearchSheet(Context r21, File r22, Provider r23, Theme.ResourcesProvider r24) {
        super(r21, false, r24);
        this.injectedAtStartCount = -1;
        this.provider = r23;
        setApplyTopPadding(false);
        setApplyBottomPadding(false);
        this.useBackgroundTopPadding = false;
        setCanDismissWithSwipe(false);
        int r6 = Theme.key_windowBackgroundWhite;
        fixNavigationBar(getThemedColor(r6));
        int r7 = ActionBar.getCurrentActionBarHeight() + AndroidUtilities.statusBarHeight;
        AnonymousClass1 r8 = new AnonymousClass1(this, r21);
        r8.setBackgroundColor(getThemedColor(r6));
        WebView r9 = new WebView(r21);
        this.webView = r9;
        r9.setVisibility(4);
        this.webView.setHorizontalScrollBarEnabled(false);
        this.webView.setVerticalScrollBarEnabled(false);
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setDomStorageEnabled(true);
        this.webView.getSettings().setDatabaseEnabled(true);
        this.webView.getSettings().setCacheMode(-1);
        this.webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        this.webView.getSettings().setMixedContentMode(0);
        File r92 = new File(ApplicationLoader.getFilesDirFixed(), "webview_database");
        if (r92.exists() == false) goto L7;
        if (r92.isDirectory() == false) goto L7;
    L8:
        this.webView.getSettings().setDatabasePath(r92.getAbsolutePath());
    L9:
        CookieManager.getInstance().setAcceptThirdPartyCookies(this.webView, true);
        this.adBlocker = new WebAdBlocker(this.webView);
        this.webView.setWebViewClient(new AnonymousClass2(this, r23));
        float r72 = r7;
        r8.addView(this.webView, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, r72 / AndroidUtilities.density, 0.0f, 0.0f));
        View r93 = new View(r21);
        r93.setBackgroundColor(getThemedColor(Theme.key_divider));
        float r11 = AndroidUtilities.density;
        r8.addView(r93, LayoutHelper.createFrame(-1, 1.0f / r11, 51, 0.0f, r72 / r11, 0.0f, 0.0f));
        ActionBar r94 = new ActionBar(r21, r24);
        r94.setOccupyStatusBar(true);
        r94.setBackgroundColor(getThemedColor(r6));
        int r3 = Theme.key_windowBackgroundWhiteBlackText;
        r94.setTitleColor(getThemedColor(r3));
        r94.setItemsColor(getThemedColor(r3), false);
        r94.setItemsBackgroundColor(getThemedColor(Theme.key_actionBarWhiteSelector), false);
        r94.setBackButtonImage(R.drawable.ic_close_white);
        r94.setTitle(r23.title);
        r94.setActionBarMenuOnItemClick(new AnonymousClass3(this));
        if (r23 != Provider.YANDEX) goto L12;
        r94.createMenu().addItem(1, R.drawable.msg_openin);
    L12:
        r8.addView(r94, LayoutHelper.createFrame(-1, r72 / AndroidUtilities.density));
        CircularProgressIndicator r32 = new CircularProgressIndicator(r21);
        this.spinner = r32;
        r32.setIndeterminate(true);
        r32.setIndicatorColor(new int[]{getThemedColor(Theme.key_windowBackgroundWhiteBlueText)});
        r32.setTrackColor(getThemedColor(Theme.key_windowBackgroundWhiteInputField));
        r32.setIndicatorSize(AndroidUtilities.dp(48.0f));
        r32.setTrackThickness(AndroidUtilities.dp(4.0f));
        r32.setTrackCornerRadius(AndroidUtilities.dp(2.0f));
        r32.setIndicatorTrackGapSize(AndroidUtilities.dp(3.0f));
        r8.addView(r32, LayoutHelper.createFrame(-2, -2, 17));
        setCustomView(r8);
        Utilities.globalQueue.postRunnable(new ReverseImageSearchSheet$$ExternalSyntheticLambda0(this, r22, r23));
        return;
    L7:
        if (r92.mkdirs() == false) goto L9;
        goto L8
    }

    private static String buildUploadScript(Provider r2, String r3) {
        int r22 = AnonymousClass4.$SwitchMap$com$exteragram$messenger$components$ReverseImageSearchSheet$Provider[r2.ordinal()];
        if (r22 == 1) goto L15;
        if (r22 == 2) goto L13;
        if (r22 == 4) goto L11;
        return "(function(){try{" + bytesFromBase64(r3) + "var file=new File([a],'image.jpg',{type:'image/jpeg'});var n=0;var t=setInterval(function(){var i=document.querySelector('input#upload-box');if(i){clearInterval(t);try{var dt=new DataTransfer();dt.items.add(file);i.files=dt.files;i.dispatchEvent(new Event('change',{bubbles:true}));}catch(e){}}else if(++n>24){clearInterval(t);}},250);}catch(e){}})();";
    L11:
        return "(function(){try{" + bytesFromBase64(r3) + "var o=location.protocol+'//'+location.host;var blob=new Blob([a],{type:'image/jpeg'});var d=new FormData();d.append('upfile',blob,'image.jpg');var u=o+'/images/touch/search?rpt=imageview&format=json&request='+encodeURIComponent('{\"blocks\":[{\"block\":\"cbir-uploader__get-cbir-id\"}]}');fetch(u,{method:'POST',credentials:'include',headers:{'X-Requested-With':'XMLHttpRequest','Accept':'application/json, text/javascript, */*; q=0.01'},body:d}).then(function(r){return r.json();}).then(function(j){var p=j.blocks[0].params;if(p&&p.cbirId){location.href=o+'/images/search?cbir_id='+encodeURIComponent(p.cbirId)+'&rpt=imageview&tabInt=1&url='+encodeURIComponent(p.originalImageUrl||'');}}).catch(function(e){});}catch(e){}})();";
    L13:
        return "(function(){try{var f=document.createElement('form');f.method='POST';f.enctype='multipart/form-data';f.action='https://www.bing.com/images/search?view=detailv2&iss=sbiupload&FORM=SBIHMP&sbifnm=image.jpg';var i=document.createElement('input');i.type='hidden';i.name='imageBin';i.value='" + r3 + "';f.appendChild(i);document.body.appendChild(f);f.submit();}catch(e){}})();";
    L15:
        return "(function(){try{" + bytesFromBase64(r3) + "var file=new File([a],'image.jpg',{type:'image/jpeg'});var f=document.createElement('form');f.method='POST';f.enctype='multipart/form-data';f.action='https://lens.google.com/v3/upload';var i=document.createElement('input');i.type='file';i.name='encoded_image';f.appendChild(i);document.body.appendChild(f);var dt=new DataTransfer();dt.items.add(file);i.files=dt.files;f.submit();}catch(e){}})();";
    }

    private static String bytesFromBase64(String r2) {
        return "var b='" + r2 + "';var bin=atob(b);var a=new Uint8Array(bin.length);for(var k=0;k<bin.length;k++)a[k]=bin.charCodeAt(k);";
    }

    private static String encodeImage(File r7) {
        if (r7 != null) goto L5;
    L24:
        return null;
    L5:
        if (r7.exists() == false) goto L24;
        BitmapFactory.Options r1 = new BitmapFactory.Options();     // Catch: Throwable -> L19
        r1.inJustDecodeBounds = true;     // Catch: Throwable -> L19
        BitmapFactory.decodeFile(r7.getAbsolutePath(), r1);     // Catch: Throwable -> L19
        BitmapFactory.Options r3 = new BitmapFactory.Options();     // Catch: Throwable -> L19
        int r4 = 1;
    L9:
        if ((Math.max(r1.outWidth, r1.outHeight) / r4) <= 2560) goto L11;
        r4 = r4 * 2;     // Catch: Throwable -> L19
        goto L9
    L11:
        r3.inSampleSize = r4;     // Catch: Throwable -> L19
        Bitmap r72 = BitmapFactory.decodeFile(r7.getAbsolutePath(), r3);     // Catch: Throwable -> L19
        if (r72 != null) goto L14;
        return null;
    L14:
        int r12 = r72.getWidth();     // Catch: Throwable -> L19
        int r32 = r72.getHeight();     // Catch: Throwable -> L19
        int r42 = Math.max(r12, r32);     // Catch: Throwable -> L19
        if (r42 <= 1280) goto L21;
        float r5 = 1280.0f / r42;     // Catch: Throwable -> L19
        Bitmap r13 = Bitmap.createScaledBitmap(r72, Math.round(r12 * r5), Math.round(r32 * r5), true);     // Catch: Throwable -> L19
        if (r13 == r72) goto L21;
        r72.recycle();     // Catch: Throwable -> L19
        r72 = r13;
    L21:
        ByteArrayOutputStream r14 = new ByteArrayOutputStream();     // Catch: Throwable -> L19
        r72.compress(Bitmap.CompressFormat.JPEG, 90, r14);     // Catch: Throwable -> L19
        r72.recycle();     // Catch: Throwable -> L19
        return Base64.encodeToString(r14.toByteArray(), 2);
    L19:
        th = move-exception;
        FileLog.e(th);
        goto L24
    }

    private void hideProviderAds(WebView r2) {
        if (this.provider == Provider.YANDEX) goto L5;
        return;
    L5:
        r2.evaluateJavascript("(function(){try{if(!document.getElementById('__ayu_adcleanup')){var s=document.createElement('style');s.id='__ayu_adcleanup';s.textContent='.DistributionPopup,.Smartbanner{display:none!important}';(document.head||document.documentElement).appendChild(s);}}catch(e){}})();", null);
    }

    private static boolean isProviderHost(Provider r3, String r4) {
        String r42 = r4.toLowerCase();
        int r32 = AnonymousClass4.$SwitchMap$com$exteragram$messenger$components$ReverseImageSearchSheet$Provider[r3.ordinal()];
        if (r32 == 1) goto L31;
        if (r32 == 2) goto L20;
        if (r32 == 4) goto L11;
        return r42.endsWith("tineye.com");
    L11:
        if (r42.contains("yandex.") == false) goto L13;
    L18:
        return true;
    L13:
        if (r42.endsWith("ya.ru") == true) goto L18;
        if (r42.contains("yastatic.") == true) goto L18;
        return false;
    L20:
        if (r42.endsWith("bing.com") == false) goto L22;
    L29:
        return true;
    L22:
        if (r42.endsWith("bingapis.com") == true) goto L29;
        if (r42.endsWith("live.com") == true) goto L29;
        if (r42.endsWith("microsoft.com") == true) goto L29;
        return false;
    L31:
        if (r42.contains("google.") == false) goto L33;
    L38:
        return true;
    L33:
        if (r42.endsWith("gstatic.com") == true) goto L38;
        if (r42.endsWith("googleusercontent.com") == true) goto L38;
        return false;
    }

    private /* synthetic */ void lambda$new$0(String r3, Provider r4) {
        if (this.webView != null) goto L5;
        return;
    L5:
        if (r3 != null) goto L8;
        dismiss();
        return;
    L8:
        seedConsentCookies(r4);
        this.pendingScript = buildUploadScript(r4, r3);
        this.webView.loadUrl(r4.landingUrl);
        ReverseImageSearchSheet$$ExternalSyntheticLambda3 r32 = new ReverseImageSearchSheet$$ExternalSyntheticLambda3(this);
        this.revealTimeout = r32;
        AndroidUtilities.runOnUIThread(r32, NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
    }

    private /* synthetic */ void lambda$new$1(File r2, Provider r3) {
        AndroidUtilities.runOnUIThread(new ReverseImageSearchSheet$$ExternalSyntheticLambda1(this, encodeImage(r2), r3));
    }

    private /* synthetic */ void lambda$reveal$2() {
        this.spinner.setVisibility(8);
    }

    private void onUrlChanged(String r1) {
        this.currentUrl = r1;
    }

    private void reveal() {
        if (this.revealed == false) goto L5;
        return;
    L5:
        if (this.webView == null) goto L16;
        this.revealed = true;
        Runnable r0 = this.revealTimeout;
        if (r0 == null) goto L10;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.revealTimeout = null;
    L10:
        this.webView.setAlpha(0.0f);
        this.webView.setVisibility(0);
        this.webView.animate().alpha(1.0f).setDuration(150).start();
        CircularProgressIndicator r02 = this.spinner;
        if (r02 == null) goto L15;
        r02.animate().alpha(0.0f).setDuration(150).withEndAction(new ReverseImageSearchSheet$$ExternalSyntheticLambda2(this)).start();
        return;
    L15:
        return;
    }

    private static void seedConsentCookies(Provider r7) {
        CookieManager r0 = CookieManager.getInstance();
        r0.setAcceptCookie(true);
        int r72 = AnonymousClass4.$SwitchMap$com$exteragram$messenger$components$ReverseImageSearchSheet$Provider[r7.ordinal()];
        if (r72 != 1) goto L5;
        r0.setCookie("https://www.google.com", "SOCS=CAISHAgBEhJnd3NfMjAyNjA2MjYtMF9SQzEaAmVuIAEaBgiAjozSBg; Domain=.google.com; Path=/; Secure; SameSite=Lax");
    L21:
        r0.flush();
        return;
    L5:
        if (r72 != 2) goto L7;
        r0.setCookie("https://www.bing.com", "BCP=AD=1&AL=1&SM=1; Domain=.bing.com; Path=/; Secure");
        goto L21
    L7:
        if (r72 != 3) goto L9;
        r0.setCookie("https://tineye.com", "cookie_consent=accepted; Path=/");
        goto L21
    L9:
        if (r72 != 4) goto L21;
        String[] r73 = {"https://yandex.com", "https://yandex.ru"};
        int r2 = 0;
    L12:
        if (r2 >= 2) goto L21;
        String r3 = r73[r2];
        if (r3.endsWith(".ru") == false) goto L16;
        String r4 = ".yandex.ru";
    L17:
        r0.setCookie(r3, "gdpr=0; Domain=" + r4 + "; Path=/; Secure");
        r2 = r2 + 1;
        goto L12
    L16:
        r4 = ".yandex.com";
        goto L17
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        Runnable r0 = this.revealTimeout;
        if (r0 == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.revealTimeout = null;
    L5:
        WebView r02 = this.webView;
        if (r02 != null) goto L14;
    L12:
        super.dismiss();
        return;
    L14:
        r02.stopLoading();     // Catch: Exception -> L9
        this.webView.loadUrl("about:blank");     // Catch: Exception -> L9
        this.webView.destroy();     // Catch: Exception -> L9
    L11:
        this.webView = null;
    L9:
        e = move-exception;
        FileLog.e(e);
        goto L11
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void onBackPressed() {
        WebView r0 = this.webView;
        if (r0 != null) goto L5;
    L8:
        super.onBackPressed();
        return;
    L5:
        if (r0.canGoBack() == false) goto L8;
        this.webView.goBack();
    }
}
