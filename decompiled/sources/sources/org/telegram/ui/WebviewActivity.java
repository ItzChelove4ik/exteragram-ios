package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.exteragram.messenger.plugins.PluginsConstants;
import j$.net.URLEncoder;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Components.ContextProgressView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ShareAlert;

/* loaded from: classes6.dex */
public class WebviewActivity extends BaseFragment {
    private String currentBot;
    private long currentDialogId;
    private String currentGame;
    private MessageObject currentMessageObject;
    private String currentUrl;
    private String linkToCopy;
    private boolean loadStats;
    private ActionBarMenuItem progressItem;
    private ContextProgressView progressView;
    private String short_param;
    private int type;
    public Runnable typingRunnable;
    private WebView webView;

    /* renamed from: org.telegram.ui.WebviewActivity$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ WebviewActivity this$0;

        public AnonymousClass1(WebviewActivity r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (WebviewActivity.m21292$$Nest$fgetcurrentMessageObject(this.this$0) != null) goto L5;
            return;
        L5:
            if (this.this$0.getParentActivity() == null) goto L12;
            WebviewActivity r0 = this.this$0;
            if (r0.typingRunnable == null) goto L13;
            MessagesController.getInstance(WebviewActivity.access$000(r0)).sendTyping(WebviewActivity.m21292$$Nest$fgetcurrentMessageObject(this.this$0).getDialogId(), 0, 6, 0);
            AndroidUtilities.runOnUIThread(this.this$0.typingRunnable, 25000);
            return;
        L13:
            return;
        }
    }

    /* renamed from: org.telegram.ui.WebviewActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ WebviewActivity this$0;

        public AnonymousClass2(WebviewActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r7) {
            if (r7 != (-1)) goto L7;
            this.this$0.finishFragment();
            return;
        L7:
            if (r7 != 1) goto L13;
            if (WebviewActivity.m21292$$Nest$fgetcurrentMessageObject(this.this$0) == null) goto L16;
            WebviewActivity.m21292$$Nest$fgetcurrentMessageObject(this.this$0).messageOwner.with_my_score = false;
            WebviewActivity r72 = this.this$0;
            r72.showDialog(ShareAlert.createShareAlert(r72.getParentActivity(), WebviewActivity.m21292$$Nest$fgetcurrentMessageObject(this.this$0), null, false, WebviewActivity.m21294$$Nest$fgetlinkToCopy(this.this$0), false));
            return;
        L16:
            return;
        L13:
            if (r7 != 2) goto L17;
            WebviewActivity.openGameInBrowser(WebviewActivity.m21293$$Nest$fgetcurrentUrl(this.this$0), WebviewActivity.m21292$$Nest$fgetcurrentMessageObject(this.this$0), this.this$0.getParentActivity(), WebviewActivity.m21297$$Nest$fgetshort_param(this.this$0), WebviewActivity.m21291$$Nest$fgetcurrentBot(this.this$0));
            return;
        }
    }

    /* renamed from: org.telegram.ui.WebviewActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends WebViewClient {
        final /* synthetic */ WebviewActivity this$0;

        /* renamed from: org.telegram.ui.WebviewActivity$3$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass3 this$1;

            public AnonymousClass1(AnonymousClass3 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                int r22 = WebviewActivity.m21298$$Nest$fgettype(this.this$1.this$0);
                AnonymousClass3 r1 = this.this$1;
                if (r22 != 1) goto L6;
                WebviewActivity.m21295$$Nest$fgetprogressItem(r1.this$0).setVisibility(8);
                return;
            L6:
                WebviewActivity.m21296$$Nest$fgetprogressView(r1.this$0).setVisibility(4);
            }
        }

        public AnonymousClass3(WebviewActivity r1) {
            this.this$0 = r1;
        }

        private boolean isInternalUrl(String r5) {
            if (TextUtils.isEmpty(r5) == false) goto L5;
            return false;
        L5:
            Uri r0 = Uri.parse(r5);
            if ("tg".equals(r0.getScheme()) == true) goto L8;
            return false;
        L8:
            if (WebviewActivity.m21298$$Nest$fgettype(this.this$0) == 1) goto L22;
            this.this$0.finishFragment(false);
            Intent r4 = new Intent("android.intent.action.VIEW", r0);     // Catch: Exception -> L16
            r4.setComponent(new ComponentName(ApplicationLoader.applicationContext.getPackageName(), LaunchActivity.class.getName()));     // Catch: Exception -> L16
            r4.putExtra("com.android.browser.application_id", ApplicationLoader.applicationContext.getPackageName());     // Catch: Exception -> L16
            ApplicationLoader.applicationContext.startActivity(r4);     // Catch: Exception -> L16
        L18:
            return true;
        L16:
            e = move-exception;
            FileLog.e(e);
            goto L18
        L22:
            Uri r52 = Uri.parse(r5.replace("tg:statsrefresh", "tg://telegram.org"));     // Catch: Throwable -> L11
            WebviewActivity.m21299$$Nest$mreloadStats(this.this$0, r52.getQueryParameter(PluginsConstants.PARAMS));     // Catch: Throwable -> L11
        L11:
            th = move-exception;
            FileLog.e(th);
            goto L18
        }

        @Override // android.webkit.WebViewClient
        public void onLoadResource(WebView r2, String r3) {
            if (isInternalUrl(r3) == false) goto L5;
            return;
        L5:
            super.onLoadResource(r2, r3);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView r12, String r13) {
            super.onPageFinished(r12, r13);
            if (WebviewActivity.m21296$$Nest$fgetprogressView(this.this$0) != null) goto L5;
            return;
        L5:
            if (WebviewActivity.m21296$$Nest$fgetprogressView(this.this$0).getVisibility() != 0) goto L13;
            AnimatorSet r122 = new AnimatorSet();
            int r132 = WebviewActivity.m21298$$Nest$fgettype(this.this$0);
            WebviewActivity r0 = this.this$0;
            if (r132 != 0) goto L9;
            WebviewActivity.m21295$$Nest$fgetprogressItem(r0).getContentView().setVisibility(0);
            WebviewActivity.m21295$$Nest$fgetprogressItem(this.this$0).setEnabled(true);
            r122.playTogether(new Animator[]{ObjectAnimator.ofFloat(WebviewActivity.m21296$$Nest$fgetprogressView(this.this$0), "scaleX", new float[]{1.0f, 0.1f}), ObjectAnimator.ofFloat(WebviewActivity.m21296$$Nest$fgetprogressView(this.this$0), "scaleY", new float[]{1.0f, 0.1f}), ObjectAnimator.ofFloat(WebviewActivity.m21296$$Nest$fgetprogressView(this.this$0), "alpha", new float[]{1.0f, 0.0f}), ObjectAnimator.ofFloat(WebviewActivity.m21295$$Nest$fgetprogressItem(this.this$0).getContentView(), "scaleX", new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(WebviewActivity.m21295$$Nest$fgetprogressItem(this.this$0).getContentView(), "scaleY", new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(WebviewActivity.m21295$$Nest$fgetprogressItem(this.this$0).getContentView(), "alpha", new float[]{0.0f, 1.0f})});
        L10:
            r122.addListener(new AnonymousClass1(this));
            r122.setDuration(150);
            r122.start();
            return;
        L9:
            r122.playTogether(new Animator[]{ObjectAnimator.ofFloat(WebviewActivity.m21296$$Nest$fgetprogressView(r0), "scaleX", new float[]{1.0f, 0.1f}), ObjectAnimator.ofFloat(WebviewActivity.m21296$$Nest$fgetprogressView(this.this$0), "scaleY", new float[]{1.0f, 0.1f}), ObjectAnimator.ofFloat(WebviewActivity.m21296$$Nest$fgetprogressView(this.this$0), "alpha", new float[]{1.0f, 0.0f})});
            goto L10
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView r2, String r3) {
            if (isInternalUrl(r3) == false) goto L5;
            return true;
        L5:
            if (super.shouldOverrideUrlLoading(r2, r3) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    public class TelegramWebviewProxy {
        final /* synthetic */ WebviewActivity this$0;

        public static /* synthetic */ void $r8$lambda$AEJy9oEOx0aS3LQZ4mw5qhXF_CE(TelegramWebviewProxy r0, String r1) {
            r0.lambda$postEvent$0(r1);
        }

        private TelegramWebviewProxy(WebviewActivity r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ TelegramWebviewProxy(WebviewActivity r1, WebviewActivityIA r2) {
            this(r1);
        }

        private /* synthetic */ void lambda$postEvent$0(String r7) {
            if (this.this$0.getParentActivity() != null) goto L6;
            return;
        L6:
            if (BuildVars.LOGS_ENABLED == false) goto L8;
            FileLog.d(r7);
        L8:
            r7.getClass();
            if (r7.equals("share_game") == false) goto L11;
            WebviewActivity.m21292$$Nest$fgetcurrentMessageObject(this.this$0).messageOwner.with_my_score = false;
        L15:
            WebviewActivity r72 = this.this$0;
            r72.showDialog(ShareAlert.createShareAlert(r72.getParentActivity(), WebviewActivity.m21292$$Nest$fgetcurrentMessageObject(this.this$0), null, false, WebviewActivity.m21294$$Nest$fgetlinkToCopy(this.this$0), false));
            return;
        L11:
            if (r7.equals("share_score") == false) goto L15;
            WebviewActivity.m21292$$Nest$fgetcurrentMessageObject(this.this$0).messageOwner.with_my_score = true;
            goto L15
        }

        @JavascriptInterface
        public void postEvent(String r1, String r2) {
            AndroidUtilities.runOnUIThread(new WebviewActivity$TelegramWebviewProxy$$ExternalSyntheticLambda0(this, r1));
        }
    }

    /* renamed from: $r8$lambda$-iphXjOdWlHcKrXy1t-RPZyWxi4, reason: not valid java name */
    public static /* synthetic */ void m21290$r8$lambda$iphXjOdWlHcKrXy1tRPZyWxi4(WebviewActivity r0, TLObject r1) {
        r0.lambda$reloadStats$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$FvTLqIqA4HivH3WwVkMLsrDIJZU(WebviewActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$reloadStats$1(r1, r2);
    }

    /* renamed from: -$$Nest$fgetcurrentBot, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m21291$$Nest$fgetcurrentBot(WebviewActivity r0) {
        return r0.currentBot;
    }

    /* renamed from: -$$Nest$fgetcurrentMessageObject, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageObject m21292$$Nest$fgetcurrentMessageObject(WebviewActivity r0) {
        return r0.currentMessageObject;
    }

    /* renamed from: -$$Nest$fgetcurrentUrl, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m21293$$Nest$fgetcurrentUrl(WebviewActivity r0) {
        return r0.currentUrl;
    }

    /* renamed from: -$$Nest$fgetlinkToCopy, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m21294$$Nest$fgetlinkToCopy(WebviewActivity r0) {
        return r0.linkToCopy;
    }

    /* renamed from: -$$Nest$fgetprogressItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m21295$$Nest$fgetprogressItem(WebviewActivity r0) {
        return r0.progressItem;
    }

    /* renamed from: -$$Nest$fgetprogressView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ContextProgressView m21296$$Nest$fgetprogressView(WebviewActivity r0) {
        return r0.progressView;
    }

    /* renamed from: -$$Nest$fgetshort_param, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m21297$$Nest$fgetshort_param(WebviewActivity r0) {
        return r0.short_param;
    }

    /* renamed from: -$$Nest$fgettype, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21298$$Nest$fgettype(WebviewActivity r0) {
        return r0.type;
    }

    /* renamed from: -$$Nest$mreloadStats, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21299$$Nest$mreloadStats(WebviewActivity r0, String r1) {
        r0.reloadStats(r1);
    }

    public WebviewActivity(String r2, String r3, String r4, String r5, MessageObject r6) {
        this.typingRunnable = new AnonymousClass1(this);
        this.currentUrl = r2;
        this.currentBot = r3;
        this.currentGame = r4;
        this.currentMessageObject = r6;
        this.short_param = r5;
        StringBuilder r22 = new StringBuilder("https://");
        r22.append(MessagesController.getInstance(this.currentAccount).linkPrefix);
        r22.append("/");
        r22.append(this.currentBot);
        if (TextUtils.isEmpty(r5) == false) goto L5;
        String r32 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        r22.append(r32);
        this.linkToCopy = r22.toString();
        this.type = 0;
        return;
    L5:
        r32 = "?game=" + r5;
        goto L6
    }

    public static /* synthetic */ int access$000(WebviewActivity r0) {
        return r0.currentAccount;
    }

    private /* synthetic */ void lambda$reloadStats$0(TLObject r2) {
        this.loadStats = false;
        if (r2 == null) goto L6;
        WebView r0 = this.webView;
        String r22 = ((TLRPC.TL_statsURL) r2).url;
        this.currentUrl = r22;
        r0.loadUrl(r22);
        return;
    }

    private /* synthetic */ void lambda$reloadStats$1(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new WebviewActivity$$ExternalSyntheticLambda1(this, r1));
    }

    public static void openGameInBrowser(String r10, MessageObject r11, Activity r12, String r13, String r14) {
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        SharedPreferences r2 = ApplicationLoader.applicationContext.getSharedPreferences("botshare", 0);     // Catch: Exception -> L32
        String r3 = r2.getString(_UrlKt.FRAGMENT_ENCODE_SET + r11.getId(), null);     // Catch: Exception -> L32
        if (r3 == null) goto L6;
        String r6 = r3;
    L7:
        StringBuilder r5 = new StringBuilder(r6);     // Catch: Exception -> L32
        StringBuilder r62 = new StringBuilder("tgShareScoreUrl=" + URLEncoder.encode("tgb://share_game_score?hash=", "UTF-8"));     // Catch: Exception -> L32
        if (r3 != null) goto L13;
        char[] r0 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();     // Catch: Exception -> L32
        int r32 = 0;
    L11:
        if (r32 >= 20) goto L13;
        r5.append(r0[Utilities.random.nextInt(r0.length)]);     // Catch: Exception -> L32
        r32 = r32 + 1;     // Catch: Exception -> L32
    L13:
        r62.append(r5);     // Catch: Exception -> L32
        int r02 = r10.indexOf(35);     // Catch: Exception -> L32
        if (r02 >= 0) goto L16;
        String r102 = r10 + "#" + r62;     // Catch: Exception -> L32
    L26:
        SharedPreferences.Editor r03 = r2.edit();     // Catch: Exception -> L32
        r03.putInt(r5 + "_date", (int) (System.currentTimeMillis() / 1000));     // Catch: Exception -> L32
        SerializedData r22 = new SerializedData(r11.messageOwner.getObjectSize());     // Catch: Exception -> L32
        r11.messageOwner.serializeToStream(r22);     // Catch: Exception -> L32
        r03.putString(r5 + "_m", Utilities.bytesToHex(r22.toByteArray()));     // Catch: Exception -> L32
        String r33 = r5 + "_link";     // Catch: Exception -> L32
        StringBuilder r52 = new StringBuilder();     // Catch: Exception -> L32
        r52.append("https://");     // Catch: Exception -> L32
        r52.append(MessagesController.getInstance(r11.currentAccount).linkPrefix);     // Catch: Exception -> L32
        r52.append("/");     // Catch: Exception -> L32
        r52.append(r14);     // Catch: Exception -> L32
        if (TextUtils.isEmpty(r13) == true) goto L30;
        r1 = "?game=" + r13;     // Catch: Exception -> L32
    L30:
        r52.append(r1);     // Catch: Exception -> L32
        r03.putString(r33, r52.toString());     // Catch: Exception -> L32
        r03.apply();     // Catch: Exception -> L32
        Browser.openUrl(r12, r102, false);     // Catch: Exception -> L32
        r22.cleanup();     // Catch: Exception -> L32
        return;
    L16:
        String r04 = r10.substring(r02 + 1);     // Catch: Exception -> L32
        if (r04.indexOf(61) < 0) goto L19;
    L25:
        r102 = r10 + "&" + r62;     // Catch: Exception -> L32
        goto L26
    L19:
        if (r04.indexOf(63) >= 0) goto L25;
        if (r04.length() <= 0) goto L24;
        r102 = r10 + "?" + r62;     // Catch: Exception -> L32
        goto L26
    L24:
        r102 = r10 + r62;     // Catch: Exception -> L32
        goto L26
    L6:
        r6 = _UrlKt.FRAGMENT_ENCODE_SET;
    L32:
        e = move-exception;
        FileLog.e(e);
    }

    private void reloadStats(String r5) {
        if (this.loadStats == false) goto L5;
        return;
    L5:
        this.loadStats = true;
        TLRPC.TL_messages_getStatsURL r0 = new TLRPC.TL_messages_getStatsURL();
        r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.currentDialogId);
        if (r5 != null) goto L9;
        r5 = _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        r0.params = r5;
        r0.dark = Theme.getCurrentTheme().isDark();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new WebviewActivity$$ExternalSyntheticLambda0(this));
    }

    public static boolean supportWebview() {
        String r0 = Build.MANUFACTURER;
        String r1 = Build.MODEL;
        if ("samsung".equals(r0) == true) goto L5;
        return true;
    L5:
        if ("GT-I9500".equals(r1) == false) goto L10;
        return false;
    L10:
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    public View createView(Context r9) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass2(this));
        ActionBarMenu r0 = this.actionBar.createMenu();
        this.progressItem = r0.addItemWithWidth(1, R.drawable.share, AndroidUtilities.dp(54.0f));
        int r2 = this.type;
        if (r2 != 0) goto L5;
        r0.addItem(0, R.drawable.ic_ab_other).addSubItem(2, R.drawable.msg_openin, LocaleController.getString(R.string.OpenInExternalApp));
        this.actionBar.setTitle(this.currentGame);
        this.actionBar.setSubtitle("@" + this.currentBot);
        ContextProgressView r02 = new ContextProgressView(r9, 1);
        this.progressView = r02;
        this.progressItem.addView(r02, LayoutHelper.createFrame(-1, -1.0f));
        this.progressView.setAlpha(0.0f);
        this.progressView.setScaleX(0.1f);
        this.progressView.setScaleY(0.1f);
        this.progressView.setVisibility(4);
    L7:
        AndroidUtilities.checkAndroidTheme(r9, true);
        WebView r03 = new WebView(r9);
        this.webView = r03;
        r03.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setDomStorageEnabled(true);
        FrameLayout r04 = new FrameLayout(r9);
        this.fragmentView = r04;
        this.webView.setLayerType(2, null);
        this.webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        this.webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        this.webView.getSettings().setMixedContentMode(0);
        CookieManager.getInstance().setAcceptThirdPartyCookies(this.webView, true);
        if (this.type != 0) goto L10;
        this.webView.addJavascriptInterface(new TelegramWebviewProxy(this, null), "TelegramWebviewProxy");
    L10:
        this.webView.setWebViewClient(new AnonymousClass3(this));
        r04.addView(this.webView, LayoutHelper.createFrame(-1, -1.0f));
        return this.fragmentView;
    L5:
        if (r2 != 1) goto L7;
        this.actionBar.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground));
        ActionBar r05 = this.actionBar;
        int r22 = Theme.key_player_actionBarItems;
        r05.setItemsColor(Theme.getColor(r22), false);
        this.actionBar.setItemsColor(Theme.getColor(r22), true);
        this.actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_player_actionBarSelector), false);
        this.actionBar.setTitleColor(Theme.getColor(Theme.key_player_actionBarTitle));
        this.actionBar.setSubtitleColor(Theme.getColor(Theme.key_player_actionBarSubtitle));
        this.actionBar.setTitle(LocaleController.getString(R.string.Statistics));
        ContextProgressView r06 = new ContextProgressView(r9, 3);
        this.progressView = r06;
        this.progressItem.addView(r06, LayoutHelper.createFrame(-1, -1.0f));
        this.progressView.setAlpha(1.0f);
        this.progressView.setScaleX(1.0f);
        this.progressView.setScaleY(1.0f);
        this.progressView.setVisibility(0);
        this.progressItem.getContentView().setVisibility(8);
        this.progressItem.setEnabled(false);
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        if (this.type != 0) goto L6;
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUBACKGROUND, null, null, null, null, Theme.key_actionBarDefaultSubmenuBackground));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUITEM, null, null, null, null, Theme.key_actionBarDefaultSubmenuItem));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUITEM | ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_actionBarDefaultSubmenuItemIcon));
        r1.add(new ThemeDescription(this.progressView, 0, null, null, null, null, Theme.key_contextProgressInner2));
        r1.add(new ThemeDescription(this.progressView, 0, null, null, null, null, Theme.key_contextProgressOuter2));
        return r1;
    L6:
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_dialogBackground));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_player_actionBarItems));
        ActionBar r8 = this.actionBar;
        int r9 = ThemeDescription.FLAG_AB_TITLECOLOR;
        int r17 = Theme.key_player_actionBarTitle;
        r1.add(new ThemeDescription(r8, r9, null, null, null, null, r17));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBTITLECOLOR, null, null, null, null, r17));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_player_actionBarSelector));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUBACKGROUND, null, null, null, null, Theme.key_actionBarDefaultSubmenuBackground));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUITEM, null, null, null, null, Theme.key_actionBarDefaultSubmenuItem));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUITEM | ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_actionBarDefaultSubmenuItemIcon));
        r1.add(new ThemeDescription(this.progressView, 0, null, null, null, null, Theme.key_contextProgressInner4));
        r1.add(new ThemeDescription(this.progressView, 0, null, null, null, null, Theme.key_contextProgressOuter4));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSwipeBackEnabled(MotionEvent r1) {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        AndroidUtilities.checkAndroidTheme(getContext(), false);
        AndroidUtilities.cancelRunOnUIThread(this.typingRunnable);
        this.webView.setLayerType(0, null);
        this.typingRunnable = null;
        ViewParent r0 = this.webView.getParent();     // Catch: Exception -> L8
        if (r0 == null) goto L6;
        ((FrameLayout) r0).removeView(this.webView);     // Catch: Exception -> L8
    L6:
        this.webView.stopLoading();     // Catch: Exception -> L8
        this.webView.loadUrl("about:blank");     // Catch: Exception -> L8
        this.webView.destroy();     // Catch: Exception -> L8
        this.webView = null;     // Catch: Exception -> L8
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        AndroidUtilities.cancelRunOnUIThread(this.typingRunnable);
        this.typingRunnable.run();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r1, boolean r2) {
        if (r1 == false) goto L8;
        if (r2 == true) goto L9;
        WebView r12 = this.webView;
        if (r12 == null) goto L10;
        r12.loadUrl(this.currentUrl);
        return;
    L10:
        return;
    L9:
        return;
    }
}
