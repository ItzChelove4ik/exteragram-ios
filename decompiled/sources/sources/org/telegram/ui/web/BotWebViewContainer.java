package org.telegram.ui.web;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Pair;
import android.util.Property;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SslErrorHandler;
import android.webkit.URLUtil;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.core.content.FileProvider;
import androidx.core.graphics.ColorUtils;
import androidx.core.util.Consumer;
import androidx.webkit.JavaScriptReplyProxy;
import androidx.webkit.ScriptHandler;
import androidx.webkit.WebMessageCompat;
import androidx.webkit.WebViewCompat;
import androidx.webkit.WebViewFeature;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.adblock.WebAdBlocker;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import j$.net.URLDecoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.IDN;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.mvel2.asm.signature.SignatureVisitor;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.messenger.browser.Browser;
import org.telegram.messenger.utils.tlutils.TLKeyboardHelper;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarLayout;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.BottomSheetTabs;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ArticleViewer;
import org.telegram.ui.CameraScanActivity;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedFileDrawable;
import org.telegram.ui.Components.AnimatedFileNative;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CreateBotAlert;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Paint.Views.LinkPreview;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.voip.CellFlickerDrawable;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.MultiContactsSelectorBottomSheet;
import org.telegram.ui.OAuthSheet;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.Stories.recorder.StoryEntry;
import org.telegram.ui.Stories.recorder.StoryRecorder;
import org.telegram.ui.TopicsFragment;
import org.telegram.ui.WrappedResourceProvider;
import org.telegram.ui.bots.BotBiometry;
import org.telegram.ui.bots.BotDownloads;
import org.telegram.ui.bots.BotLocation;
import org.telegram.ui.bots.BotSensors;
import org.telegram.ui.bots.BotShareSheet;
import org.telegram.ui.bots.BotStorage;
import org.telegram.ui.bots.BotWebViewSheet;
import org.telegram.ui.bots.ChatAttachAlertBotWebViewLayout;
import org.telegram.ui.bots.SetupEmojiStatusSheet;
import org.telegram.ui.bots.WebViewRequestProps;
import org.telegram.ui.web.BrowserHistory;
import org.telegram.ui.web.WebMetadataCache;

/* loaded from: classes3.dex */
public abstract class BotWebViewContainer extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    public static boolean firstWebView = true;
    private static HashMap<String, String> rotatedTONHosts;
    private static int tags;
    private BotBiometry biometry;
    private long blockedDialogsUntil;
    public final boolean bot;
    private TLRPC.User botUser;
    private BotWebViewProxy botWebViewProxy;
    private String buttonData;
    private BottomSheet cameraBottomSheet;
    private int currentAccount;
    private AlertDialog currentDialog;
    private String currentPaymentSlug;
    private Delegate delegate;
    private int dialogSequentialOpenTimes;
    private long documentGeneration;
    private BotDownloads downloads;
    private final CellFlickerDrawable flickerDrawable;
    private BackupImageView flickerView;
    private int flickerViewColor;
    private boolean flickerViewColorOverriden;
    private SvgHelper.SvgDrawable flickerViewDrawable;
    private int forceHeight;
    private boolean hasQRPending;
    private boolean hasUserPermissions;
    private boolean isBackButtonVisible;
    private boolean isFlickeringCenter;
    private boolean isPageLoaded;
    private boolean isRequestingPageOpen;
    private boolean isSettingsButtonVisible;
    private boolean isViewPortByMeasureSuppressed;
    private boolean keyboardFocusable;
    private int lastButtonColor;
    private String lastButtonText;
    private int lastButtonTextColor;
    private long lastClickMs;
    private long lastDialogClosed;
    private long lastDialogCooldownTime;
    private int lastDialogType;
    private boolean lastExpanded;
    private final Rect lastInsets;
    private int lastInsetsTopMargin;
    private long lastPostStoryMs;
    private String lastQrText;
    private int lastSecondaryButtonColor;
    private String lastSecondaryButtonPosition;
    private String lastSecondaryButtonText;
    private int lastSecondaryButtonTextColor;
    private int lastViewportHeightReported;
    private boolean lastViewportIsExpanded;
    private boolean lastViewportStateStable;
    private BotLocation location;
    private WebRequestContext locationRequestContext;
    private ValueCallback<Uri[]> mFilePathCallback;
    private String mUrl;
    private final Runnable notifyLocationChecked;
    private Runnable onCloseListener;
    private Runnable onPermissionsRequestResultCallback;
    private Utilities.Callback4<Boolean, Double, String, Double> onVerifiedAge;
    private MyWebView opener;
    private Activity parentActivity;
    private boolean preserving;
    private Theme.ResourcesProvider resourcesProvider;
    private boolean restrictBridgeToOrigin;
    private String secondaryButtonData;
    private BotStorage secureStorage;
    private BotSensors sensors;
    private int shownDialogsCount;
    private BotStorage storage;
    private final int tag;
    private String trustedOrigin;
    private float viewPortHeightOffset;
    private boolean wasFocusable;
    private WebViewRequestProps wasOpenedByBot;
    private boolean wasOpenedByLinkIntent;
    private MyWebView webView;
    private boolean webViewNotAvailable;
    private TextView webViewNotAvailableText;
    private Consumer<Float> webViewProgressListener;
    private WebViewProxy webViewProxy;
    private WebViewScrollListener webViewScrollListener;

    /* renamed from: org.telegram.ui.web.BotWebViewContainer$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 extends BackupImageView {
        final /* synthetic */ BotWebViewContainer this$0;

        /* renamed from: org.telegram.ui.web.BotWebViewContainer$1$1, reason: invalid class name and collision with other inner class name */
        public class C00691 extends ImageReceiver {
            final /* synthetic */ AnonymousClass1 this$1;

            public static /* synthetic */ void $r8$lambda$Sv1ysRcR6s1pcFae5T_yo8_OwCU(C00691 r0, ValueAnimator r1) {
                r0.lambda$setImageBitmapByKey$0(r1);
            }

            public C00691(AnonymousClass1 r1, View r2) {
                this.this$1 = r1;
                super(r2);
            }

            private /* synthetic */ void lambda$setImageBitmapByKey$0(ValueAnimator r2) {
                AnonymousClass1.access$000(this.this$1).setAlpha(((Float) r2.getAnimatedValue()).floatValue());
                invalidate();
            }

            @Override // org.telegram.messenger.ImageReceiver
            public boolean setImageBitmapByKey(Drawable r1, String r2, int r3, boolean r4, int r5) {
                boolean r12 = super.setImageBitmapByKey(r1, r2, r3, r4, r5);
                ValueAnimator r22 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(300);
                r22.addUpdateListener(new BotWebViewContainer$1$1$$ExternalSyntheticLambda0(this));
                r22.start();
                return r12;
            }
        }

        public AnonymousClass1(BotWebViewContainer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.imageReceiver = new C00691(this, this);
        }

        public static /* synthetic */ ImageReceiver access$000(AnonymousClass1 r0) {
            return r0.imageReceiver;
        }

        @Override // org.telegram.ui.Components.BackupImageView, android.view.View
        public void onDraw(Canvas r6) {
            if (BotWebViewContainer.m21981$$Nest$fgetisFlickeringCenter(this.this$0) == false) goto L7;
            super.onDraw(r6);
            return;
        L7:
            if (this.imageReceiver.getDrawable() == null) goto L10;
            this.imageReceiver.setImageCoords(0.0f, 0.0f, getWidth(), r0.getIntrinsicHeight() * (getWidth() / r0.getIntrinsicWidth()));
            this.imageReceiver.draw(r6);
            return;
        }
    }

    /* renamed from: org.telegram.ui.web.BotWebViewContainer$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ BotWebViewContainer this$0;

        public AnonymousClass2(BotWebViewContainer r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            BotWebViewContainer.m21980$$Nest$fgetflickerView(this.this$0).setVisibility(8);
        }
    }

    /* renamed from: org.telegram.ui.web.BotWebViewContainer$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 implements Bulletin.Delegate {
        final /* synthetic */ BotWebViewContainer this$0;

        public AnonymousClass3(BotWebViewContainer r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getBottomOffset(int r2) {
            if ((this.this$0.getParent() instanceof ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer) == false) goto L6;
            ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer r1 = (ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer) this.this$0.getParent();
            return (int) ((r1.getOffsetY() + r1.getSwipeOffsetY()) - r1.getTopActionBarOffsetY());
        L6:
            return 0;
        }
    }

    /* renamed from: org.telegram.ui.web.BotWebViewContainer$4, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass4 implements NotificationCenter.NotificationCenterDelegate {
        final /* synthetic */ BotWebViewContainer this$0;
        final /* synthetic */ WebRequestContext val$requestContext;

        public AnonymousClass4(BotWebViewContainer r1, WebRequestContext r2) {
            this.this$0 = r1;
            this.val$requestContext = r2;
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r3, int r4, Object... r5) {
            int r42 = NotificationCenter.onRequestPermissionResultReceived;
            if (r3 != r42) goto L12;
            int r0 = ((Integer) r5[0]).intValue();
            int[] r52 = (int[]) r5[2];
            if (r0 != 5000) goto L13;
            NotificationCenter.getGlobalInstance().removeObserver(this, r42);
            int r32 = r52[0];
            BotWebViewContainer r43 = this.this$0;
            if (r32 != 0) goto L10;
            BotWebViewContainer.m22000$$Nest$mopenQrScanActivity(r43);
            return;
        L10:
            BotWebViewContainer.m21996$$Nest$mnotifyEvent(r43, this.val$requestContext, "scan_qr_popup_closed", new JSONObject());
            return;
        L13:
            return;
        }
    }

    /* renamed from: org.telegram.ui.web.BotWebViewContainer$5, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass5 extends BaseFragment {
        final /* synthetic */ BotWebViewContainer this$0;

        /* renamed from: org.telegram.ui.web.BotWebViewContainer$5$1, reason: invalid class name */
        public class AnonymousClass1 extends WrappedResourceProvider {
            final /* synthetic */ AnonymousClass5 this$1;

            public AnonymousClass1(AnonymousClass5 r1, Theme.ResourcesProvider r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.WrappedResourceProvider
            public void appendColors() {
                this.sparseIntArray.append(Theme.key_dialogBackground, -14803426);
                this.sparseIntArray.append(Theme.key_windowBackgroundGray, -16777216);
            }
        }

        public AnonymousClass5(BotWebViewContainer r1) {
            this.this$0 = r1;
            this.currentAccount = BotWebViewContainer.m21977$$Nest$fgetcurrentAccount(r1);
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Activity getParentActivity() {
            return BotWebViewContainer.m21985$$Nest$fgetparentActivity(this.this$0);
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Theme.ResourcesProvider getResourceProvider() {
            return new AnonymousClass1(this, BotWebViewContainer.m21986$$Nest$fgetresourcesProvider(this.this$0));
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public boolean isLightStatusBar() {
            return false;
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Dialog showDialog(Dialog r1) {
            r1.show();
            return r1;
        }
    }

    /* renamed from: org.telegram.ui.web.BotWebViewContainer$6, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass6 extends ChatActivity {
        private boolean shownToast;
        final /* synthetic */ BotWebViewContainer this$0;
        final /* synthetic */ long val$managerId;
        final /* synthetic */ TLRPC.User val$newBot;

        public static /* synthetic */ void $r8$lambda$sm11p_WMcETgUJE3YyJSC8TA4gQ(AnonymousClass6 r0, long r1) {
            r0.lambda$onBecomeFullyVisible$0(r1);
        }

        public AnonymousClass6(BotWebViewContainer r1, Bundle r2, TLRPC.User r3, long r4) {
            this.this$0 = r1;
            this.val$newBot = r3;
            this.val$managerId = r4;
            super(r2);
        }

        private /* synthetic */ void lambda$onBecomeFullyVisible$0(long r1) {
            presentFragment(ChatActivity.of(r1));
        }

        @Override // org.telegram.ui.ChatActivity, org.telegram.ui.ActionBar.BaseFragment
        public void onBecomeFullyVisible() {
            super.onBecomeFullyVisible();
            if (this.shownToast == true) goto L6;
            this.shownToast = true;
            BulletinFactory.of(this).createSimpleBulletin(R.raw.contact_check, LocaleController.formatString(R.string.CreateManagedBotCreatedTitle, new Object[]{UserObject.getUserName(this.val$newBot)}), AndroidUtilities.replaceSingleTag(LocaleController.formatString(R.string.CreateManagedBotCreatedText, new Object[]{UserObject.getUserName(BotWebViewContainer.m21976$$Nest$fgetbotUser(this.this$0))}), new BotWebViewContainer$6$$ExternalSyntheticLambda0(this, this.val$managerId))).show();
            return;
        }
    }

    /* renamed from: org.telegram.ui.web.BotWebViewContainer$7, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass7 extends DialogsActivity {
        final /* synthetic */ BotWebViewContainer this$0;
        final /* synthetic */ WebRequestContext val$requestContext;
        final /* synthetic */ boolean[] val$sent;

        public AnonymousClass7(BotWebViewContainer r1, Bundle r2, boolean[] r3, WebRequestContext r4) {
            this.this$0 = r1;
            this.val$sent = r3;
            this.val$requestContext = r4;
            super(r2);
        }

        @Override // org.telegram.ui.DialogsActivity, org.telegram.ui.ActionBar.BaseFragment
        public void onFragmentDestroy() {
            super.onFragmentDestroy();
            boolean[] r0 = this.val$sent;
            if (r0[0] == true) goto L6;
            r0[0] = true;
            BotWebViewContainer.m21996$$Nest$mnotifyEvent(this.this$0, this.val$requestContext, "requested_chat_failed", BotWebViewContainer.obj());
            return;
        }
    }

    /* renamed from: org.telegram.ui.web.BotWebViewContainer$8, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass8 implements CameraScanActivity.CameraScanActivityDelegate {
        final /* synthetic */ BotWebViewContainer this$0;

        public AnonymousClass8(BotWebViewContainer r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.CameraScanActivity.CameraScanActivityDelegate
        public void didFindQr(String r4) {
            BotWebViewContainer.m21991$$Nest$fputlastClickMs(this.this$0, System.currentTimeMillis());     // Catch: JSONException -> L4
            this.this$0.notifyEvent("qr_text_received", new JSONObject().put("data", r4));     // Catch: JSONException -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // org.telegram.ui.CameraScanActivity.CameraScanActivityDelegate
        public String getSubtitleText() {
            return BotWebViewContainer.m21983$$Nest$fgetlastQrText(this.this$0);
        }

        @Override // org.telegram.ui.CameraScanActivity.CameraScanActivityDelegate
        public void onDismiss() {
            this.this$0.notifyEvent("scan_qr_popup_closed", null);
            BotWebViewContainer.m21989$$Nest$fputhasQRPending(this.this$0, false);
        }
    }

    /* loaded from: classes6.dex */
    public static class BotWebViewProxy {
        public BotWebViewContainer container;

        public static /* synthetic */ void $r8$lambda$tB_lzr12CHb0AMAQec2uJ2GNWsc(BotWebViewProxy r0, String r1, String r2) {
            r0.lambda$postEvent$0(r1, r2);
        }

        public BotWebViewProxy(BotWebViewContainer r1) {
            this.container = r1;
        }

        private /* synthetic */ void lambda$postEvent$0(String r3, String r4) {
            BotWebViewContainer r0 = this.container;     // Catch: Exception -> L7
            if (r0 != null) goto L5;
            return;
        L5:
            BotWebViewContainer.m21997$$Nest$monEventReceived(r0, this, BotWebViewContainer.m21993$$Nest$mcreateRequestContext(r0), r3, r4);     // Catch: Exception -> L7
            return;
        L7:
            e = move-exception;
            FileLog.e(e);
        }

        @JavascriptInterface
        @Keep
        public void postEvent(String r2, String r3) {
        L8:
            e = move-exception;
            FileLog.e(e);
            return;
        L3:
            if (this.container != null) goto L6;
            FileLog.d("webviewproxy.postEvent: no container");     // Catch: Exception -> L8
            return;
        L6:
            AndroidUtilities.runOnUIThread(new BotWebViewContainer$BotWebViewProxy$$ExternalSyntheticLambda0(this, r2, r3));     // Catch: Exception -> L8
        }

        public void setContainer(BotWebViewContainer r1) {
            this.container = r1;
        }
    }

    /* loaded from: classes6.dex */
    public interface Delegate {
        default BotSensors getBotSensors() {
            return null;
        }

        default boolean isClipboardAvailable() {
            return false;
        }

        void onCloseRequested(Runnable r1);

        default void onCloseToTabs() {
            onCloseRequested(null);
        }

        default void onEmojiStatusGranted(boolean r1) {
        }

        default void onEmojiStatusSet(TLRPC.Document r1) {
        }

        default String onFullscreenRequested(boolean r1, boolean r2) {
            return "UNSUPPORTED";
        }

        default void onInstantClose() {
            onCloseRequested(null);
        }

        default void onLocationGranted(boolean r1) {
        }

        default void onOpenBackFromTabs() {
        }

        default void onOrientationLockChanged(boolean r1) {
        }

        default void onSendWebViewData(String r1) {
        }

        void onSetBackButtonVisible(boolean r1);

        void onSetSettingsButtonVisible(boolean r1);

        void onSetupMainButton(boolean r1, boolean r2, String r3, long r4, int r6, int r7, boolean r8, boolean r9);

        void onSetupSecondaryButton(boolean r1, boolean r2, String r3, long r4, int r6, int r7, boolean r8, boolean r9, String r10);

        default void onSharedTo(ArrayList<Long> r1) {
        }

        default void onWebAppBackgroundChanged(boolean r1, int r2) {
        }

        void onWebAppExpand();

        void onWebAppOpenInvoice(TLRPC.InputInvoice r1, String r2, TLObject r3);

        default void onWebAppReady() {
        }

        void onWebAppSetActionBarColor(int r1, int r2, boolean r3);

        void onWebAppSetBackgroundColor(int r1);

        default void onWebAppSetNavigationBarColor(int r1) {
        }

        void onWebAppSetupClosingBehavior(boolean r1);

        void onWebAppSwipingBehavior(boolean r1);

        void onWebAppSwitchInlineQuery(TLRPC.User r1, String r2, List<String> r3);
    }

    /* loaded from: classes6.dex */
    public static class MyWebView extends WebView {
        public final WebAdBlocker adBlocker;
        public final boolean bot;
        private boolean botWebMessageListenerAdded;
        private ScriptHandler botWebMessageShimHandler;
        private BotWebViewContainer botWebViewContainer;
        private BrowserHistory.Entry currentHistoryEntry;
        private BottomSheet currentSheet;
        private String currentUrl;
        public boolean dangerousUrl;
        public boolean errorShown;
        public String errorShownAt;
        public boolean injectedJS;
        private boolean isPageLoaded;
        public int lastActionBarColor;
        public boolean lastActionBarColorGot;
        public int lastBackgroundColor;
        public boolean lastBackgroundColorGot;
        public Bitmap lastFavicon;
        public boolean lastFaviconGot;
        private String lastFaviconUrl;
        private HashMap<String, Bitmap> lastFavicons;
        public String lastSiteName;
        public String lastTitle;
        public boolean lastTitleGot;
        private String lastUrl;
        private Runnable onCloseListener;
        private String openedByUrl;
        public MyWebView opener;
        private int prevScrollX;
        private int prevScrollY;
        private int searchCount;
        private int searchIndex;
        private Runnable searchListener;
        private boolean searchLoading;
        private final int tag;
        public String urlFallback;
        private WebViewScrollListener webViewScrollListener;
        private Runnable whenPageLoaded;

        /* renamed from: org.telegram.ui.web.BotWebViewContainer$MyWebView$1, reason: invalid class name */
        public class AnonymousClass1 implements View.OnLongClickListener {
            final /* synthetic */ MyWebView this$0;

            /* renamed from: $r8$lambda$73-hd_iVI5IAjXJaedb2WoBczJo, reason: not valid java name */
            public static /* synthetic */ void m22030$r8$lambda$73hd_iVI5IAjXJaedb2WoBczJo(AnonymousClass1 r0, String r1, DialogInterface r2, int r3) {
                r0.lambda$onLongClick$2(r1, r2, r3);
            }

            public static /* synthetic */ void $r8$lambda$OAfUR3wMHe_qhLc5ArJRICEjo5s(AnonymousClass1 r0, String r1) {
                r0.lambda$onLongClick$3(r1);
            }

            /* renamed from: $r8$lambda$g8Vl9r8-QH1if2ftXYbdmMC1e2I, reason: not valid java name */
            public static /* synthetic */ void m22031$r8$lambda$g8Vl9r8QH1if2ftXYbdmMC1e2I(AnonymousClass1 r0, String r1, DialogInterface r2, int r3) {
                r0.lambda$onLongClick$0(r1, r2, r3);
            }

            public static /* synthetic */ void $r8$lambda$yaRgXkACiSscrCiJ18VmOqs1MkQ(AnonymousClass1 r0, String r1) {
                r0.lambda$onLongClick$1(r1);
            }

            public AnonymousClass1(MyWebView r1) {
                this.this$0 = r1;
            }

            private /* synthetic */ void lambda$onLongClick$0(String r3, DialogInterface r4, int r5) {
                if (r5 != 0) goto L6;
                this.this$0.loadUrl(r3);
                return;
            L6:
                if (r5 != 1) goto L13;
                Intent r52 = new Intent("android.intent.action.VIEW", Uri.parse(r3));     // Catch: Exception -> L9
                r52.putExtra("create_new_tab", true);     // Catch: Exception -> L9
                r52.putExtra("com.android.browser.application_id", this.this$0.getContext().getPackageName());     // Catch: Exception -> L9
                this.this$0.getContext().startActivity(r52);     // Catch: Exception -> L9
                return;
            L9:
                e = move-exception;
                FileLog.e(e);
                this.this$0.loadUrl(r3);
                return;
            L13:
                if (r5 != 2) goto L20;
                AndroidUtilities.addToClipboard(r3);
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) == null) goto L21;
                MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0).showLinkCopiedBulletin();
                return;
            L21:
                return;
            }

            private /* synthetic */ void lambda$onLongClick$1(String r8) {
                BottomSheet.Builder r0 = new BottomSheet.Builder(this.this$0.getContext(), false, null);
                Uri r1 = Uri.parse(r8);     // Catch: Exception -> L9
                if (r1 != null) goto L6;
            L11:
                String r12 = r8;
            L21:
                r12 = URLDecoder.decode(r12.replaceAll("\\+", "%2b"), "UTF-8");     // Catch: Exception -> L14
            L19:
                r0.setTitleMultipleLines(true);
                r0.setTitle(r12);
                r0.setItems(new CharSequence[]{LocaleController.getString(R.string.OpenInTelegramBrowser), LocaleController.getString(R.string.OpenInSystemBrowser), LocaleController.getString(R.string.Copy)}, new BotWebViewContainer$MyWebView$1$$ExternalSyntheticLambda3(this, r8));
                MyWebView.m22020$$Nest$fputcurrentSheet(this.this$0, r0.show());
                return;
            L14:
                e = e;
            L18:
                FileLog.e(e);
                goto L19
            L6:
                if (r1.getScheme().equalsIgnoreCase("data") == true) goto L11;
                r12 = Browser.replaceHostname(r1, Browser.IDN_toUnicode(r1.getHost()), null);     // Catch: Exception -> L9
            L9:
                e = move-exception;
                FileLog.e(e);     // Catch: Exception -> L16
            L16:
                e = e;
                r12 = r8;
                goto L18
            }

            private /* synthetic */ void lambda$onLongClick$2(String r3, DialogInterface r4, int r5) {
                if (r5 == 0) goto L28;
                if (r5 != 1) goto L23;
                String r52 = URLUtil.guessFileName(r3, null, "image/*");     // Catch: Exception -> L20
                if (r52 != null) goto L13;
                r52 = "image.png";
            L13:
                DownloadManager.Request r1 = new DownloadManager.Request(Uri.parse(r3));     // Catch: Exception -> L20
                r1.setMimeType("image/*");     // Catch: Exception -> L20
                r1.setDescription(LocaleController.getString(R.string.WebDownloading));     // Catch: Exception -> L20
                r1.setNotificationVisibility(1);     // Catch: Exception -> L20
                r1.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, r52);     // Catch: Exception -> L20
                DownloadManager r32 = (DownloadManager) this.this$0.getContext().getSystemService("download");     // Catch: Exception -> L20
                if (r32 == null) goto L17;
                r32.enqueue(r1);     // Catch: Exception -> L20
            L17:
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) == null) goto L33;
                BulletinFactory.of(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0), BotWebViewContainer.m21986$$Nest$fgetresourcesProvider(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0))).createSimpleBulletin(R.raw.ic_download, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.WebDownloadingFile, new Object[]{r52}))).show(true);     // Catch: Exception -> L20
                return;
            L33:
                return;
            L20:
                e = move-exception;
                FileLog.e(e);
                return;
            L23:
                if (r5 != 2) goto L35;
                AndroidUtilities.addToClipboard(r3);
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) == null) goto L36;
                MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0).showLinkCopiedBulletin();
                return;
            L36:
                return;
            L35:
                return;
            L28:
                Intent r42 = new Intent("android.intent.action.VIEW", Uri.parse(r3));     // Catch: Exception -> L6
                r42.putExtra("create_new_tab", true);     // Catch: Exception -> L6
                r42.putExtra("com.android.browser.application_id", this.this$0.getContext().getPackageName());     // Catch: Exception -> L6
                this.this$0.getContext().startActivity(r42);     // Catch: Exception -> L6
                return;
            L6:
                e = move-exception;
                FileLog.e(e);
                this.this$0.loadUrl(r3);
            }

            private /* synthetic */ void lambda$onLongClick$3(String r8) {
                BottomSheet.Builder r0 = new BottomSheet.Builder(this.this$0.getContext(), false, null);
                Uri r1 = Uri.parse(r8);     // Catch: Exception -> L5
                String r12 = Browser.replaceHostname(r1, Browser.IDN_toUnicode(r1.getHost()), null);     // Catch: Exception -> L5
            L21:
                r12 = URLDecoder.decode(r12.replaceAll("\\+", "%2b"), "UTF-8");     // Catch: Exception -> L10
            L15:
                r0.setTitleMultipleLines(true);
                r0.setTitle(r12);
                r0.setItems(new CharSequence[]{LocaleController.getString(R.string.OpenInSystemBrowser), LocaleController.getString(R.string.AccActionDownload), LocaleController.getString(R.string.CopyLink)}, new BotWebViewContainer$MyWebView$1$$ExternalSyntheticLambda2(this, r8));
                MyWebView.m22020$$Nest$fputcurrentSheet(this.this$0, r0.show());
                return;
            L10:
                e = e;
            L14:
                FileLog.e(e);
            L5:
                e = move-exception;
                FileLog.e(e);     // Catch: Exception -> L12
                r12 = r8;
            L12:
                e = e;
                r12 = r8;
                goto L14
            }

            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View r4) {
                WebView.HitTestResult r42 = this.this$0.getHitTestResult();
                if (r42.getType() != 7) goto L7;
                AndroidUtilities.runOnUIThread(new BotWebViewContainer$MyWebView$1$$ExternalSyntheticLambda0(this, r42.getExtra()));
                return true;
            L7:
                if (r42.getType() != 5) goto L10;
                AndroidUtilities.runOnUIThread(new BotWebViewContainer$MyWebView$1$$ExternalSyntheticLambda1(this, r42.getExtra()));
                return true;
            L10:
                return false;
            }
        }

        /* renamed from: org.telegram.ui.web.BotWebViewContainer$MyWebView$2, reason: invalid class name */
        public class AnonymousClass2 extends WebViewClient {
            private boolean firstRequest;
            private final Runnable resetErrorRunnable;
            final /* synthetic */ MyWebView this$0;
            final /* synthetic */ boolean val$bot;
            final /* synthetic */ Context val$context;

            public static /* synthetic */ void $r8$lambda$0nJhpqUvbRvyENAZv40h0XgxXBo(AnonymousClass2 r0) {
                r0.lambda$shouldInterceptRequest$0();
            }

            public static /* synthetic */ void $r8$lambda$950nr_kiWjglSA7WapNKt5D6Oy8(AnonymousClass2 r0) {
                r0.lambda$onRenderProcessGone$1();
            }

            public static /* synthetic */ void $r8$lambda$Eg5Zt1PkSeuriaO0JKyx4x8R7So(AnonymousClass2 r0) {
                r0.lambda$$3();
            }

            /* renamed from: $r8$lambda$WEaZ-aYRdqzp8Zj5hDat0EcvSuw, reason: not valid java name */
            public static /* synthetic */ void m22032$r8$lambda$WEaZaYRdqzp8Zj5hDat0EcvSuw(AnonymousClass2 r0, DialogInterface r1) {
                r0.lambda$onRenderProcessGone$2(r1);
            }

            public AnonymousClass2(MyWebView r1, boolean r2, Context r3) {
                this.this$0 = r1;
                this.val$bot = r2;
                this.val$context = r3;
                this.firstRequest = true;
                this.resetErrorRunnable = new BotWebViewContainer$MyWebView$2$$ExternalSyntheticLambda0(this);
            }

            private /* synthetic */ void lambda$$3() {
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) == null) goto L6;
                BotWebViewContainer r0 = MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0);
                this.this$0.errorShown = false;
                r0.onErrorShown(false, 0, null);
                return;
            }

            private /* synthetic */ void lambda$onRenderProcessGone$1() {
                Browser.openUrl(this.this$0.getContext(), "https://play.google.com/store/apps/details?id=com.google.android.webview");
            }

            private /* synthetic */ void lambda$onRenderProcessGone$2(DialogInterface r1) {
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) != null) goto L5;
                return;
            L5:
                if (BotWebViewContainer.m21978$$Nest$fgetdelegate(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0)) == null) goto L9;
                BotWebViewContainer.m21978$$Nest$fgetdelegate(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0)).onCloseRequested(null);
                return;
            }

            private /* synthetic */ void lambda$shouldInterceptRequest$0() {
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) == null) goto L6;
                MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0).onURLChanged(this.this$0.urlFallback, !r1.canGoBack(), !this.this$0.canGoForward());
                return;
            }

            @Override // android.webkit.WebViewClient
            public void doUpdateVisitedHistory(WebView r5, String r6, boolean r7) {
                if (this.val$bot == false) goto L5;
            L9:
                this.this$0.d("doUpdateVisitedHistory " + r6 + " " + r7);
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) == null) goto L16;
                BotWebViewContainer r0 = MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0);
                MyWebView r1 = this.this$0;
                if (r1.dangerousUrl == false) goto L14;
                String r2 = r1.urlFallback;
            L15:
                r0.onURLChanged(r2, !r1.canGoBack(), !this.this$0.canGoForward());
                goto L16
            L14:
                r2 = r6;
            L16:
                super.doUpdateVisitedHistory(r5, r6, r7);
                return;
            L5:
                if (MyWebView.m22009$$Nest$fgetcurrentHistoryEntry(this.this$0) != null) goto L7;
            L8:
                MyWebView.m22019$$Nest$fputcurrentHistoryEntry(this.this$0, new BrowserHistory.Entry());
                MyWebView.m22009$$Nest$fgetcurrentHistoryEntry(this.this$0).id = Utilities.fastRandom.nextLong();
                MyWebView.m22009$$Nest$fgetcurrentHistoryEntry(this.this$0).time = System.currentTimeMillis();
                MyWebView.m22009$$Nest$fgetcurrentHistoryEntry(this.this$0).url = BotWebViewContainer.magic2tonsite(this.this$0.getUrl());
                MyWebView.m22009$$Nest$fgetcurrentHistoryEntry(this.this$0).meta = WebMetadataCache.WebMetadata.from(this.this$0);
                BrowserHistory.pushHistory(MyWebView.m22009$$Nest$fgetcurrentHistoryEntry(this.this$0));
                goto L9
            L7:
                if (TextUtils.equals(MyWebView.m22009$$Nest$fgetcurrentHistoryEntry(this.this$0).url, r6) == true) goto L9;
                goto L8
            }

            @Override // android.webkit.WebViewClient
            public void onPageCommitVisible(WebView r6, String r7) {
                if (MyWebView.m22016$$Nest$fgetwhenPageLoaded(this.this$0) == null) goto L5;
                Runnable r0 = MyWebView.m22016$$Nest$fgetwhenPageLoaded(this.this$0);
                MyWebView.m22028$$Nest$fputwhenPageLoaded(this.this$0, null);
                r0.run();
            L5:
                this.this$0.d("onPageCommitVisible " + r7);
                if (this.val$bot == true) goto L8;
            L14:
                boolean r02 = this.val$bot;
                MyWebView r1 = this.this$0;
                if (r02 == true) goto L17;
                r1.injectedJS = true;
                r1.evaluateJS(AndroidUtilities.readRes(R.raw.webview_ext).replace("$DEBUG$", "false"));
                this.this$0.evaluateJS(AndroidUtilities.readRes(R.raw.webview_share));
            L18:
                WebAdBlocker r03 = this.this$0.adBlocker;
                if (r03 == null) goto L21;
                r03.injectCosmetics(r7);
            L21:
                super.onPageCommitVisible(r6, r7);
                return;
            L17:
                r1.injectedJS = true;
                r1.evaluateJS(AndroidUtilities.readRes(R.raw.webview_app_ext).replace("$DEBUG$", "false"));
                goto L18
            L8:
                if (WebViewFeature.isFeatureSupported("DOCUMENT_START_SCRIPT") == true) goto L14;
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) == null) goto L14;
                if (BotWebViewContainer.m21994$$Nest$misTrustedDocumentCurrent(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0)) == false) goto L14;
                this.this$0.evaluateJS("window.TelegramWebviewProxy={postEvent:function(eventType,eventData){window.TelegramWebviewProxyMessage.postMessage(JSON.stringify({eventType:eventType,eventData:eventData}));}};");
                goto L14
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView r6, String r7) {
                MyWebView.m22022$$Nest$fputisPageLoaded(this.this$0, true);
                if (MyWebView.m22016$$Nest$fgetwhenPageLoaded(this.this$0) == null) goto L5;
                Runnable r62 = MyWebView.m22016$$Nest$fgetwhenPageLoaded(this.this$0);
                MyWebView.m22028$$Nest$fputwhenPageLoaded(this.this$0, null);
                r62.run();
                boolean r63 = false;
            L6:
                this.this$0.d("onPageFinished");
                if (this.val$bot == true) goto L9;
            L15:
                BotWebViewContainer r1 = MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0);
                MyWebView r2 = this.this$0;
                if (r1 == null) goto L18;
                MyWebView.m22008$$Nest$fgetbotWebViewContainer(r2).setPageLoaded(r7, r63);
            L19:
                boolean r64 = this.val$bot;
                MyWebView r12 = this.this$0;
                if (r64 == true) goto L22;
                r12.injectedJS = true;
                r12.evaluateJS(AndroidUtilities.readRes(R.raw.webview_ext).replace("$DEBUG$", _UrlKt.FRAGMENT_ENCODE_SET + BuildVars.DEBUG_VERSION));
                this.this$0.evaluateJS(AndroidUtilities.readRes(R.raw.webview_share));
            L23:
                MyWebView.m22029$$Nest$msaveHistory(this.this$0);
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) == null) goto L30;
                BotWebViewContainer r65 = MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0);
                MyWebView r13 = this.this$0;
                if (r13.dangerousUrl == false) goto L28;
                String r14 = r13.urlFallback;
            L29:
                r65.onURLChanged(r14, !this.this$0.canGoBack(), true ^ this.this$0.canGoForward());
                goto L30
            L28:
                r14 = r13.getUrl();
            L30:
                WebAdBlocker r5 = this.this$0.adBlocker;
                if (r5 == null) goto L34;
                r5.injectCosmetics(r7);
                return;
            L34:
                return;
            L22:
                r12.injectedJS = true;
                r12.evaluateJS(AndroidUtilities.readRes(R.raw.webview_app_ext).replace("$DEBUG$", _UrlKt.FRAGMENT_ENCODE_SET + BuildVars.DEBUG_VERSION));
                goto L23
            L18:
                r2.d("onPageFinished: no container");
                goto L19
            L9:
                if (WebViewFeature.isFeatureSupported("DOCUMENT_START_SCRIPT") == true) goto L15;
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) == null) goto L15;
                if (BotWebViewContainer.m21994$$Nest$misTrustedDocumentCurrent(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0)) == false) goto L15;
                this.this$0.evaluateJS("window.TelegramWebviewProxy={postEvent:function(eventType,eventData){window.TelegramWebviewProxyMessage.postMessage(JSON.stringify({eventType:eventType,eventData:eventData}));}};");
                goto L15
            L5:
                r63 = true;
                goto L6
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView r7, String r8, Bitmap r9) {
                if (this.val$bot == false) goto L8;
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) == null) goto L8;
                BotWebViewContainer r0 = MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0);
                BotWebViewContainer.m21988$$Nest$fputdocumentGeneration(r0, BotWebViewContainer.m21979$$Nest$fgetdocumentGeneration(r0) + 1);
            L8:
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) != null) goto L10;
            L11:
                this.this$0.getSettings().setMediaPlaybackRequiresUserGesture(true);
            L13:
                if (MyWebView.m22010$$Nest$fgetcurrentSheet(this.this$0) == null) goto L15;
                MyWebView.m22010$$Nest$fgetcurrentSheet(this.this$0).dismiss();
                MyWebView.m22020$$Nest$fputcurrentSheet(this.this$0, null);
            L15:
                MyWebView.m22019$$Nest$fputcurrentHistoryEntry(this.this$0, null);
                MyWebView.m22021$$Nest$fputcurrentUrl(this.this$0, r8);
                WebAdBlocker r02 = this.this$0.adBlocker;
                if (r02 == null) goto L18;
                r02.onPageStarted(r8);
            L18:
                MyWebView r03 = this.this$0;
                r03.lastSiteName = null;
                r03.lastActionBarColorGot = false;
                r03.lastBackgroundColorGot = false;
                r03.lastFaviconGot = false;
                r03.d("onPageStarted " + r8);
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) == null) goto L34;
                MyWebView r04 = this.this$0;
                if (r04.errorShown == false) goto L34;
                String r05 = r04.errorShownAt;
                if (r05 != null) goto L25;
            L26:
                WebAdBlocker r06 = this.this$0.adBlocker;
                if (r06 != null) goto L29;
            L31:
                int r07 = 40;
            L32:
                AndroidUtilities.runOnUIThread(this.resetErrorRunnable, r07);
                goto L34
            L29:
                if (r06.consumePageBlocked() == false) goto L31;
                r07 = 540;
                goto L32
            L25:
                if (TextUtils.equals(r05, r8) == false) goto L26;
            L34:
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) == null) goto L40;
                BotWebViewContainer r08 = MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0);
                MyWebView r3 = this.this$0;
                if (r3.dangerousUrl == false) goto L38;
                String r4 = r3.urlFallback;
            L39:
                r08.onURLChanged(r4, !r3.canGoBack(), true ^ this.this$0.canGoForward());
                goto L40
            L38:
                r4 = r8;
            L40:
                super.onPageStarted(r7, r8, r9);
                this.this$0.injectedJS = false;
                return;
            L10:
                if (BotWebViewContainer.m21995$$Nest$misVerifyingAge(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0)) == true) goto L13;
                goto L11
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView r4, int r5, String r6, String r7) {
                this.this$0.d("onReceivedError: " + r5 + " " + r6 + " url=" + r7);
                super.onReceivedError(r4, r5, r6, r7);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView r6, WebResourceRequest r7, WebResourceError r8) {
                this.this$0.d("onReceivedError: " + r8.getErrorCode() + " " + r8.getDescription());
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) == null) goto L19;
                if (r7 != null) goto L6;
            L7:
                AndroidUtilities.cancelRunOnUIThread(this.resetErrorRunnable);
                MyWebView r0 = this.this$0;
                String r1 = null;
                r0.lastSiteName = null;
                r0.lastActionBarColorGot = false;
                r0.lastBackgroundColorGot = false;
                r0.lastFaviconGot = false;
                r0.lastTitleGot = false;
                if (r7 != null) goto L10;
            L13:
                String r2 = this.this$0.getUrl();
            L14:
                r0.errorShownAt = r2;
                BotWebViewContainer r02 = MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0);
                this.this$0.lastTitle = null;
                r02.onTitleChanged(null);
                BotWebViewContainer r03 = MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0);
                this.this$0.lastFavicon = null;
                r03.onFaviconChanged(null);
                BotWebViewContainer r04 = MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0);
                this.this$0.errorShown = true;
                int r22 = r8.getErrorCode();
                if (r8.getDescription() == null) goto L18;
                r1 = r8.getDescription().toString();
            L18:
                r04.onErrorShown(true, r22, r1);
                goto L19
            L10:
                if (r7.getUrl() == null) goto L13;
                r2 = r7.getUrl().toString();
                goto L14
            L6:
                if (r7.isForMainFrame() == true) goto L7;
            L19:
                super.onReceivedError(r6, r7, r8);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedHttpError(WebView r4, WebResourceRequest r5, WebResourceResponse r6) {
                super.onReceivedHttpError(r4, r5, r6);
                MyWebView r42 = this.this$0;
                StringBuilder r0 = new StringBuilder("onReceivedHttpError: statusCode=");
                if (r6 != null) goto L5;
                Integer r2 = null;
            L6:
                r0.append(r2);
                r0.append(" request=");
                if (r5 != null) goto L9;
                Uri r22 = null;
            L10:
                r0.append(r22);
                r42.d(r0.toString());
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) == null) goto L27;
                if (r5 != null) goto L14;
            L15:
                if (r6 != null) goto L17;
                return;
            L17:
                if (TextUtils.isEmpty(r6.getMimeType()) == false) goto L30;
                AndroidUtilities.cancelRunOnUIThread(this.resetErrorRunnable);
                MyWebView r43 = this.this$0;
                r43.lastSiteName = null;
                r43.lastActionBarColorGot = false;
                r43.lastBackgroundColorGot = false;
                r43.lastFaviconGot = false;
                r43.lastTitleGot = false;
                if (r5 != null) goto L21;
            L24:
                String r52 = this.this$0.getUrl();
            L25:
                r43.errorShownAt = r52;
                BotWebViewContainer r44 = MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0);
                this.this$0.lastTitle = null;
                r44.onTitleChanged(null);
                BotWebViewContainer r45 = MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0);
                this.this$0.lastFavicon = null;
                r45.onFaviconChanged(null);
                BotWebViewContainer r46 = MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0);
                this.this$0.errorShown = true;
                r46.onErrorShown(true, r6.getStatusCode(), r6.getReasonPhrase());
                return;
            L21:
                if (r5.getUrl() == null) goto L24;
                r52 = r5.getUrl().toString();
                goto L25
            L30:
                return;
            L14:
                if (r5.isForMainFrame() == true) goto L15;
                return;
            L27:
                return;
            L9:
                r22 = r5.getUrl();
                goto L10
            L5:
                r2 = Integer.valueOf(r6.getStatusCode());
                goto L6
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedSslError(WebView r4, SslErrorHandler r5, SslError r6) {
                MyWebView r0 = this.this$0;
                StringBuilder r1 = new StringBuilder("onReceivedSslError: error=");
                r1.append(r6);
                r1.append(" url=");
                if (r6 != null) goto L5;
                String r2 = null;
            L6:
                r1.append(r2);
                r0.d(r1.toString());
                r5.cancel();
                super.onReceivedSslError(r4, r5, r6);
                return;
            L5:
                r2 = r6.getUrl();
                goto L6
            }

            @Override // android.webkit.WebViewClient
            public boolean onRenderProcessGone(WebView r4, RenderProcessGoneDetail r5) {
                int r42 = Build.VERSION.SDK_INT;
                MyWebView r0 = this.this$0;
                if (r42 < 26) goto L13;
                StringBuilder r43 = new StringBuilder("onRenderProcessGone priority=");
                if (r5 != null) goto L7;
                Integer r2 = null;
            L8:
                r43.append(r2);
                r43.append(" didCrash=");
                if (r5 != null) goto L11;
                Boolean r52 = null;
            L12:
                r43.append(r52);
                r0.d(r43.toString());
            L26:
            L23:
                e = move-exception;
                FileLog.e(e);
                return false;
            L15:
                if (AndroidUtilities.isSafeToShow(this.this$0.getContext()) == true) goto L17;
                return true;
            L17:
                Context r02 = this.this$0.getContext();     // Catch: Exception -> L23
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) != null) goto L20;
                Theme.ResourcesProvider r22 = null;
            L21:
                new AlertDialog.Builder(r02, r22).setTitle(LocaleController.getString(R.string.ChromeCrashTitle)).setMessage(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.ChromeCrashMessage), new BotWebViewContainer$MyWebView$2$$ExternalSyntheticLambda2(this))).setPositiveButton(LocaleController.getString(R.string.OK), null).setOnDismissListener(new BotWebViewContainer$MyWebView$2$$ExternalSyntheticLambda3(this)).show();     // Catch: Exception -> L23
                return true;
            L20:
                r22 = BotWebViewContainer.m21986$$Nest$fgetresourcesProvider(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0));     // Catch: Exception -> L23
                goto L21
            L11:
                r52 = Boolean.valueOf(r5.didCrash());
                goto L12
            L7:
                r2 = Integer.valueOf(r5.rendererPriorityAtExit());
                goto L8
            L13:
                r0.d("onRenderProcessGone");
                goto L26
            }

            @Override // android.webkit.WebViewClient
            public WebResourceResponse shouldInterceptRequest(WebView r12, WebResourceRequest r13) {
                MyWebView r1 = this.this$0;
                StringBuilder r2 = new StringBuilder("shouldInterceptRequest ");
                HttpURLConnection r3 = null;
                if (r13 != null) goto L5;
                Uri r4 = null;
            L6:
                r2.append(r4);
                r1.d(r2.toString());
                if (r13 == null) goto L13;
                if (BotWebViewContainer.isTonsite(r13.getUrl()) == false) goto L13;
                this.this$0.d("proxying ton");
                this.firstRequest = false;
                return BotWebViewContainer.proxyTON(r13);
            L13:
                if (this.val$bot == false) goto L15;
            L67:
                this.firstRequest = false;
                WebAdBlocker r0 = this.this$0.adBlocker;
                if (r0 == null) goto L74;
                if (r13 == null) goto L74;
                WebResourceResponse r02 = r0.interceptRequest(r13);
                if (r02 == null) goto L74;
                return r02;
            L74:
                return super.shouldInterceptRequest(r12, r13);
            L15:
                if (this.this$0.opener == null) goto L67;
                if (this.firstRequest == false) goto L67;
                HttpURLConnection r22 = (HttpURLConnection) new URL(r13.getUrl().toString()).openConnection();     // Catch: Exception -> L63
                r22.setRequestMethod(r13.getMethod());     // Catch: Exception -> L25
                if (r13.getRequestHeaders() == null) goto L27;
                Iterator<Map.Entry<String, String>> r32 = r13.getRequestHeaders().entrySet().iterator();     // Catch: Exception -> L25
            L23:
                if (r32.hasNext() == false) goto L27;
                Map.Entry<String, String> r42 = r32.next();     // Catch: Exception -> L25
                r22.setRequestProperty(r42.getKey(), r42.getValue());     // Catch: Exception -> L25
            L27:
                r22.connect();     // Catch: Exception -> L25
                HashMap r8 = new HashMap();     // Catch: Exception -> L25
                Iterator<Map.Entry<String, List<String>>> r33 = r22.getHeaderFields().entrySet().iterator();     // Catch: Exception -> L25
            L28:
                int r5 = 1;
                if (r33.hasNext() == false) goto L50;
                Map.Entry<String, List<String>> r43 = r33.next();     // Catch: Exception -> L25
                String r6 = r43.getKey();     // Catch: Exception -> L25
                if (r6 == null) goto L28;
                r8.put(r6, TextUtils.join(", ", r43.getValue()));     // Catch: Exception -> L25
                if (this.this$0.dangerousUrl == true) goto L28;
                if ("cross-origin-resource-policy".equals(r6.toLowerCase()) == true) goto L39;
                if ("cross-origin-embedder-policy".equals(r6.toLowerCase()) == false) goto L28;
            L39:
                Iterator<String> r44 = r43.getValue().iterator();     // Catch: Exception -> L25
            L41:
                if (r44.hasNext() == false) goto L28;
                String r7 = r44.next();     // Catch: Exception -> L25
                if (r7 == null) goto L41;
                if ("unsafe-none".equals(r7.toLowerCase()) == true) goto L41;
                if ("same-site".equals(r7.toLowerCase()) == true) goto L41;
                this.this$0.d("<!> dangerous header CORS policy: " + r6 + ": " + r7 + " from " + r13.getMethod() + " " + r13.getUrl());     // Catch: Exception -> L25
                this.this$0.dangerousUrl = true;     // Catch: Exception -> L25
                AndroidUtilities.runOnUIThread(new BotWebViewContainer$MyWebView$2$$ExternalSyntheticLambda1(this));     // Catch: Exception -> L25
                goto L28
            L50:
                String r34 = r22.getContentType();     // Catch: Exception -> L25
                String r45 = r22.getContentEncoding();     // Catch: Exception -> L25
                if (r34.indexOf("; ") < 0) goto L61;
                String[] r03 = r34.split("; ");     // Catch: Exception -> L25
                if (TextUtils.isEmpty(r03[0]) == true) goto L56;
                r34 = r03[0];     // Catch: Exception -> L25
            L56:
                if (r5 >= r03.length) goto L61;
                if (r03[r5].startsWith("charset=") == false) goto L60;
                r45 = r03[r5].substring(8);     // Catch: Exception -> L25
            L60:
                r5 = r5 + 1;     // Catch: Exception -> L25
            L61:
                String r52 = r45;
                this.firstRequest = false;     // Catch: Exception -> L25
                return new WebResourceResponse(r34, r52, r22.getResponseCode(), r22.getResponseMessage(), r8, r22.getInputStream());
            L25:
                e = e;
                r3 = r22;
            L64:
                FileLog.e(e);
                if (r3 == null) goto L67;
                r3.disconnect();
            L63:
                e = e;
                goto L64
            L5:
                r4 = r13.getUrl();
                goto L6
            }

            @Override // android.webkit.WebViewClient
            public WebResourceResponse shouldInterceptRequest(WebView r4, String r5) {
                this.this$0.d("shouldInterceptRequest " + r5);
                if (BotWebViewContainer.isTonsite(r5) == false) goto L7;
                this.this$0.d("proxying ton");
                return BotWebViewContainer.proxyTON("GET", r5, null);
            L7:
                return super.shouldInterceptRequest(r4, r5);
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView r8, String r9) {
                if (r9 != null) goto L5;
                return false;
            L5:
                BotWebViewContainer r0 = MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0);
                if (r9.trim().startsWith("sms:") == false) goto L9;
                return false;
            L9:
                if (r9.trim().startsWith("tel:") == true) goto L11;
                Uri r1 = Uri.parse(r9);
                if (this.val$bot == false) goto L24;
            L61:
                if (r0 != null) goto L63;
            L91:
                if (r1 == null) goto L93;
                MyWebView.m22021$$Nest$fputcurrentUrl(this.this$0, r1.toString());
            L93:
                this.this$0.d("shouldOverrideUrlLoading(" + r9 + ") = false");
                return false;
            L63:
                if (Browser.isInternalUri(r1, null) == false) goto L91;
                if (this.val$bot == true) goto L72;
                if ("1".equals(r1.getQueryParameter("embed")) == false) goto L72;
                if ("t.me".equals(r1.getAuthority()) == false) goto L72;
                return false;
            L72:
                if (MessagesController.getInstance(BotWebViewContainer.m21977$$Nest$fgetcurrentAccount(r0)).webAppAllowedProtocols != null) goto L74;
            L89:
                this.this$0.d("shouldOverrideUrlLoading(" + r9 + ") = true");
                return true;
            L74:
                if (MessagesController.getInstance(BotWebViewContainer.m21977$$Nest$fgetcurrentAccount(r0)).webAppAllowedProtocols.contains(r1.getScheme()) == false) goto L89;
                if (this.this$0.opener != null) goto L78;
            L88:
                BotWebViewContainer.m21998$$Nest$monOpenUri(r0, r1);
                goto L89
            L78:
                if (BotWebViewContainer.m21978$$Nest$fgetdelegate(r0) == null) goto L81;
                BotWebViewContainer.m21978$$Nest$fgetdelegate(r0).onInstantClose();
            L84:
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0.opener) == null) goto L88;
                if (BotWebViewContainer.m21978$$Nest$fgetdelegate(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0.opener)) == null) goto L88;
                BotWebViewContainer.m21978$$Nest$fgetdelegate(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0.opener)).onCloseToTabs();
                goto L88
            L81:
                if (MyWebView.m22014$$Nest$fgetonCloseListener(this.this$0) == null) goto L84;
                MyWebView.m22014$$Nest$fgetonCloseListener(this.this$0).run();
                MyWebView.m22024$$Nest$fputonCloseListener(this.this$0, null);
                goto L84
            L24:
                if (Browser.openInExternalApp(this.val$context, r9, true) == false) goto L38;
                this.this$0.d("shouldOverrideUrlLoading(" + r9 + ") = true (openInExternalBrowser)");
                if (MyWebView.m22011$$Nest$fgetisPageLoaded(this.this$0) == false) goto L28;
            L36:
                return true;
            L28:
                if (this.this$0.canGoBack() == true) goto L36;
                if (r0 == null) goto L34;
                if (BotWebViewContainer.m21978$$Nest$fgetdelegate(r0) == null) goto L34;
                BotWebViewContainer.m21978$$Nest$fgetdelegate(r0).onInstantClose();
            L34:
                if (MyWebView.m22014$$Nest$fgetonCloseListener(this.this$0) == null) goto L36;
                MyWebView.m22014$$Nest$fgetonCloseListener(this.this$0).run();
                MyWebView.m22024$$Nest$fputonCloseListener(this.this$0, null);
                goto L36
            L38:
                if (r9.startsWith("intent://") == true) goto L95;
                if (r1 != null) goto L41;
            L50:
                if (r1 == null) goto L61;
                if (r1.getScheme() == null) goto L61;
                if ("https".equals(r1.getScheme()) == true) goto L61;
                if ("http".equals(r1.getScheme()) == true) goto L61;
                if ("tonsite".equals(r1.getScheme()) == true) goto L61;
                this.this$0.d("shouldOverrideUrlLoading(" + r9 + ") = true (browser open)");
                Browser.openUrl(this.this$0.getContext(), r1);
                return true;
            L41:
                if (r1.getScheme() == null) goto L50;
                if (r1.getScheme().equalsIgnoreCase("intent") == false) goto L50;
            L95:
                String r4 = Intent.parseUri(r1.toString(), 1).getStringExtra("browser_fallback_url");     // Catch: Exception -> L48
                if (TextUtils.isEmpty(r4) == true) goto L50;
                this.this$0.loadUrl(r4);     // Catch: Exception -> L48
                return true;
            L48:
                e = move-exception;
                FileLog.e(e);
                goto L50
            L11:
                if (this.this$0.opener == null) goto L19;
                if (r0 == null) goto L17;
                if (BotWebViewContainer.m21978$$Nest$fgetdelegate(r0) == null) goto L17;
                BotWebViewContainer.m21978$$Nest$fgetdelegate(r0).onInstantClose();
            L17:
                if (MyWebView.m22014$$Nest$fgetonCloseListener(this.this$0) == null) goto L19;
                MyWebView.m22014$$Nest$fgetonCloseListener(this.this$0).run();
                MyWebView.m22024$$Nest$fputonCloseListener(this.this$0, null);
            L19:
                Browser.openUrl(this.val$context, r9);
                return true;
            }
        }

        /* renamed from: org.telegram.ui.web.BotWebViewContainer$MyWebView$3, reason: invalid class name */
        public class AnonymousClass3 extends WebChromeClient {
            private Dialog lastPermissionsDialog;
            final /* synthetic */ MyWebView this$0;
            final /* synthetic */ boolean val$bot;
            final /* synthetic */ long val$botId;
            final /* synthetic */ Context val$context;

            /* renamed from: org.telegram.ui.web.BotWebViewContainer$MyWebView$3$1, reason: invalid class name */
            public class AnonymousClass1 implements TextView.OnEditorActionListener {
                final /* synthetic */ AnonymousClass3 this$1;
                final /* synthetic */ AlertDialog val$dialog;
                final /* synthetic */ boolean[] val$done;
                final /* synthetic */ EditTextCaption val$editText;
                final /* synthetic */ JsPromptResult val$result;

                public AnonymousClass1(AnonymousClass3 r1, boolean[] r2, JsPromptResult r3, EditTextCaption r4, AlertDialog r5) {
                    this.this$1 = r1;
                    this.val$done = r2;
                    this.val$result = r3;
                    this.val$editText = r4;
                    this.val$dialog = r5;
                }

                @Override // android.widget.TextView.OnEditorActionListener
                public boolean onEditorAction(TextView r2, int r3, KeyEvent r4) {
                    if (r3 != 6) goto L8;
                    boolean[] r22 = this.val$done;
                    if (r22[0] == true) goto L7;
                    r22[0] = true;
                    this.val$result.confirm(this.val$editText.getText().toString());
                    this.val$dialog.dismiss();
                L7:
                    return true;
                L8:
                    return false;
                }
            }

            /* renamed from: org.telegram.ui.web.BotWebViewContainer$MyWebView$3$2, reason: invalid class name */
            public class AnonymousClass2 extends WebViewClient {
                final /* synthetic */ AnonymousClass3 this$1;
                final /* synthetic */ WebView val$newWebView;

                public static /* synthetic */ void $r8$lambda$ndKKF9dAeaS5_y1ghzqhk6unVTs(AnonymousClass2 r0, DialogInterface r1) {
                    r0.lambda$onRenderProcessGone$1(r1);
                }

                public static /* synthetic */ void $r8$lambda$yEPH0U0besoI0he3OJVHIQnPB48(AnonymousClass2 r0) {
                    r0.lambda$onRenderProcessGone$0();
                }

                public AnonymousClass2(AnonymousClass3 r1, WebView r2) {
                    this.this$1 = r1;
                    this.val$newWebView = r2;
                }

                private /* synthetic */ void lambda$onRenderProcessGone$0() {
                    Browser.openUrl(this.this$1.this$0.getContext(), "https://play.google.com/store/apps/details?id=com.google.android.webview");
                }

                private /* synthetic */ void lambda$onRenderProcessGone$1(DialogInterface r1) {
                    if (BotWebViewContainer.m21978$$Nest$fgetdelegate(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$1.this$0)) == null) goto L6;
                    BotWebViewContainer.m21978$$Nest$fgetdelegate(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$1.this$0)).onCloseRequested(null);
                    return;
                }

                @Override // android.webkit.WebViewClient
                public boolean onRenderProcessGone(WebView r4, RenderProcessGoneDetail r5) {
                    int r42 = Build.VERSION.SDK_INT;
                    AnonymousClass3 r0 = this.this$1;
                    if (r42 < 26) goto L13;
                    MyWebView r43 = r0.this$0;
                    StringBuilder r02 = new StringBuilder("newWebView.onRenderProcessGone priority=");
                    if (r5 != null) goto L7;
                    Integer r2 = null;
                L8:
                    r02.append(r2);
                    r02.append(" didCrash=");
                    if (r5 != null) goto L11;
                    Boolean r52 = null;
                L12:
                    r02.append(r52);
                    r43.d(r02.toString());
                L26:
                L23:
                    e = move-exception;
                    FileLog.e(e);
                    return false;
                L15:
                    if (AndroidUtilities.isSafeToShow(this.this$1.this$0.getContext()) == true) goto L17;
                    return true;
                L17:
                    Context r03 = this.this$1.this$0.getContext();     // Catch: Exception -> L23
                    if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$1.this$0) != null) goto L20;
                    Theme.ResourcesProvider r22 = null;
                L21:
                    new AlertDialog.Builder(r03, r22).setTitle(LocaleController.getString(R.string.ChromeCrashTitle)).setMessage(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.ChromeCrashMessage), new BotWebViewContainer$MyWebView$3$2$$ExternalSyntheticLambda0(this))).setPositiveButton(LocaleController.getString(R.string.OK), null).setOnDismissListener(new BotWebViewContainer$MyWebView$3$2$$ExternalSyntheticLambda1(this)).show();     // Catch: Exception -> L23
                    return true;
                L20:
                    r22 = BotWebViewContainer.m21986$$Nest$fgetresourcesProvider(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$1.this$0));     // Catch: Exception -> L23
                    goto L21
                L11:
                    r52 = Boolean.valueOf(r5.didCrash());
                    goto L12
                L7:
                    r2 = Integer.valueOf(r5.rendererPriorityAtExit());
                    goto L8
                L13:
                    r0.this$0.d("newWebView.onRenderProcessGone");
                    goto L26
                }

                @Override // android.webkit.WebViewClient
                public boolean shouldOverrideUrlLoading(WebView r1, String r2) {
                    if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$1.this$0) == null) goto L7;
                    BotWebViewContainer.m21998$$Nest$monOpenUri(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$1.this$0), Uri.parse(r2));
                    this.val$newWebView.destroy();
                    return true;
                L7:
                    return true;
                }
            }

            public static /* synthetic */ void $r8$lambda$2yWw2BLNVDPE9RgDuTMCL9FnW_0(boolean[] r0, JsResult r1, AlertDialog r2, int r3) {
                if (r0[0] == true) goto L6;
                r0[0] = true;
                r1.cancel();
                return;
            }

            public static /* synthetic */ void $r8$lambda$46yWweFpbnX7LDYh2i48Aea3vk4(boolean[] r1, JsPromptResult r2, DialogInterface r3) {
                if (r1[0] == true) goto L6;
                r1[0] = true;
                r2.cancel();
                return;
            }

            public static /* synthetic */ void $r8$lambda$7PCV5e5hCA51YuoeBzteUPjB_EM(AnonymousClass3 r0, PermissionRequest r1, String r2, Boolean r3) {
                r0.lambda$onPermissionRequest$15(r1, r2, r3);
            }

            /* renamed from: $r8$lambda$8K_cVxUfdXQ-NueSTSJanwrHgAM, reason: not valid java name */
            public static /* synthetic */ void m22033$r8$lambda$8K_cVxUfdXQNueSTSJanwrHgAM(AnonymousClass3 r0, GeolocationPermissions.Callback r1, String r2, Boolean r3) {
                r0.lambda$onGeolocationPermissionsShowPrompt$10(r1, r2, r3);
            }

            public static /* synthetic */ void $r8$lambda$FlsajW65O1qximWu9YktPhKFj1I(boolean[] r0, JsPromptResult r1, EditTextCaption r2, AlertDialog r3, int r4) {
                if (r0[0] == true) goto L6;
                r0[0] = true;
                r1.confirm(r2.getText().toString());
                return;
            }

            public static /* synthetic */ void $r8$lambda$IC0Fb5OtyqFbCxZwBQSp3JraDd0(AnonymousClass3 r0, PermissionRequest r1, String[] r2, Boolean r3) {
                r0.lambda$onPermissionRequest$17(r1, r2, r3);
            }

            /* renamed from: $r8$lambda$Ioa-wrM0cMNwKMCMmI1x9HeQaBk, reason: not valid java name */
            public static /* synthetic */ void m22034$r8$lambda$IoawrM0cMNwKMCMmI1x9HeQaBk(AnonymousClass3 r0, GeolocationPermissions.Callback r1, String r2, Boolean r3) {
                r0.lambda$onGeolocationPermissionsShowPrompt$11(r1, r2, r3);
            }

            public static /* synthetic */ void $r8$lambda$JIRgPyLkvZvw66mMqbAAjH2Ayd8(boolean[] r0, JsResult r1, AlertDialog r2, int r3) {
                if (r0[0] == true) goto L6;
                r0[0] = true;
                r1.confirm();
                return;
            }

            public static /* synthetic */ void $r8$lambda$K1Afog_syJZf2IitVkUD71GBLv4(boolean[] r1, JsResult r2, DialogInterface r3) {
                if (r1[0] == true) goto L6;
                r1[0] = true;
                r2.cancel();
                return;
            }

            /* renamed from: $r8$lambda$LMHOtTZ1WIWS-ZO19WLo-8NoNkI, reason: not valid java name */
            public static /* synthetic */ void m22035$r8$lambda$LMHOtTZ1WIWSZO19WLo8NoNkI(boolean[] r0, JsPromptResult r1, AlertDialog r2, int r3) {
                if (r0[0] == true) goto L6;
                r0[0] = true;
                r1.cancel();
                return;
            }

            public static /* synthetic */ void $r8$lambda$LbrLC539usybksXm7pXYylqEpbM(EditTextCaption r2, Runnable r3) {
                AndroidUtilities.hideKeyboard(r2);
                AndroidUtilities.runOnUIThread(r3, 80);
            }

            /* renamed from: $r8$lambda$R0Kpxaui4vP-zYr1rro_d6_1xc4, reason: not valid java name */
            public static /* synthetic */ void m22036$r8$lambda$R0Kpxaui4vPzYr1rro_d6_1xc4(boolean[] r0, JsResult r1, AlertDialog r2, int r3) {
                if (r0[0] == true) goto L6;
                r0[0] = true;
                r1.confirm();
                return;
            }

            /* renamed from: $r8$lambda$Rq-FVApM7pJCUDA7L0k7bKb6TDo, reason: not valid java name */
            public static /* synthetic */ void m22037$r8$lambda$RqFVApM7pJCUDA7L0k7bKb6TDo(AnonymousClass3 r0, PermissionRequest r1, String[] r2, Boolean r3) {
                r0.lambda$onPermissionRequest$16(r1, r2, r3);
            }

            public static /* synthetic */ void $r8$lambda$SmteFhEXrNzOwaAdmJfF9eO0W6U(EditTextCaption r0) {
                r0.requestFocus();
            }

            public static /* synthetic */ void $r8$lambda$_7Kla_nw6y9QMdB_QtfCn5wldig(AnonymousClass3 r0, PermissionRequest r1, String r2, Boolean r3) {
                r0.lambda$onPermissionRequest$14(r1, r2, r3);
            }

            public static /* synthetic */ void $r8$lambda$io3EJ7eiBtyg5Jwe4zT5o_y4Yrg(boolean[] r1, JsResult r2, DialogInterface r3) {
                if (r1[0] == true) goto L6;
                r1[0] = true;
                r2.cancel();
                return;
            }

            public static /* synthetic */ void $r8$lambda$nx_bSCDdTOwVbN5ZZstRZCs18D0(AnonymousClass3 r0, PermissionRequest r1, String r2, Boolean r3) {
                r0.lambda$onPermissionRequest$12(r1, r2, r3);
            }

            public static /* synthetic */ void $r8$lambda$yiv7hwT0rmYAY5XC94zAJXRFkDA(AnonymousClass3 r0, PermissionRequest r1, String r2, Boolean r3) {
                r0.lambda$onPermissionRequest$13(r1, r2, r3);
            }

            public AnonymousClass3(MyWebView r1, Context r2, boolean r3, long r4) {
                this.this$0 = r1;
                this.val$context = r2;
                this.val$bot = r3;
                this.val$botId = r4;
            }

            private /* synthetic */ void lambda$onGeolocationPermissionsShowPrompt$10(GeolocationPermissions.Callback r3, String r4, Boolean r5) {
                r3.invoke(r4, r5.booleanValue(), false);
                if (r5.booleanValue() == false) goto L6;
                BotWebViewContainer.m21990$$Nest$fputhasUserPermissions(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0), true);
                return;
            }

            private /* synthetic */ void lambda$onGeolocationPermissionsShowPrompt$11(GeolocationPermissions.Callback r3, String r4, Boolean r5) {
                if (this.lastPermissionsDialog == null) goto L10;
                this.lastPermissionsDialog = null;
                if (r5.booleanValue() == false) goto L8;
                BotWebViewContainer.m22001$$Nest$mrunWithPermissions(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0), new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}, new BotWebViewContainer$MyWebView$3$$ExternalSyntheticLambda16(this, r3, r4));
                return;
            L8:
                r3.invoke(r4, false, false);
                return;
            }

            private /* synthetic */ void lambda$onPermissionRequest$12(PermissionRequest r1, String r2, Boolean r3) {
                if (r3.booleanValue() == false) goto L6;
                r1.grant(new String[]{r2});
                BotWebViewContainer.m21990$$Nest$fputhasUserPermissions(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0), true);
                return;
            L6:
                r1.deny();
            }

            private /* synthetic */ void lambda$onPermissionRequest$13(PermissionRequest r3, String r4, Boolean r5) {
                if (this.lastPermissionsDialog == null) goto L10;
                this.lastPermissionsDialog = null;
                if (r5.booleanValue() == false) goto L8;
                BotWebViewContainer.m22001$$Nest$mrunWithPermissions(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0), new String[]{"android.permission.RECORD_AUDIO"}, new BotWebViewContainer$MyWebView$3$$ExternalSyntheticLambda15(this, r3, r4));
                return;
            L8:
                r3.deny();
                return;
            }

            private /* synthetic */ void lambda$onPermissionRequest$14(PermissionRequest r1, String r2, Boolean r3) {
                if (r3.booleanValue() == false) goto L6;
                r1.grant(new String[]{r2});
                BotWebViewContainer.m21990$$Nest$fputhasUserPermissions(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0), true);
                return;
            L6:
                r1.deny();
            }

            private /* synthetic */ void lambda$onPermissionRequest$15(PermissionRequest r3, String r4, Boolean r5) {
                if (this.lastPermissionsDialog == null) goto L10;
                this.lastPermissionsDialog = null;
                if (r5.booleanValue() == false) goto L8;
                BotWebViewContainer.m22001$$Nest$mrunWithPermissions(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0), new String[]{"android.permission.CAMERA"}, new BotWebViewContainer$MyWebView$3$$ExternalSyntheticLambda17(this, r3, r4));
                return;
            L8:
                r3.deny();
                return;
            }

            private /* synthetic */ void lambda$onPermissionRequest$16(PermissionRequest r2, String[] r3, Boolean r4) {
                if (r4.booleanValue() == false) goto L6;
                r2.grant(new String[]{r3[0], r3[1]});
                BotWebViewContainer.m21990$$Nest$fputhasUserPermissions(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0), true);
                return;
            L6:
                r2.deny();
            }

            private /* synthetic */ void lambda$onPermissionRequest$17(PermissionRequest r3, String[] r4, Boolean r5) {
                if (this.lastPermissionsDialog == null) goto L10;
                this.lastPermissionsDialog = null;
                if (r5.booleanValue() == false) goto L8;
                BotWebViewContainer.m22001$$Nest$mrunWithPermissions(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0), new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO"}, new BotWebViewContainer$MyWebView$3$$ExternalSyntheticLambda14(this, r3, r4));
                return;
            L8:
                r3.deny();
                return;
            }

            @Override // android.webkit.WebChromeClient
            public Bitmap getDefaultVideoPoster() {
                return Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
            }

            @Override // android.webkit.WebChromeClient
            public void onCloseWindow(WebView r4) {
                this.this$0.d("onCloseWindow " + r4);
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) == null) goto L8;
                if (BotWebViewContainer.m21978$$Nest$fgetdelegate(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0)) == null) goto L8;
                BotWebViewContainer.m21978$$Nest$fgetdelegate(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0)).onCloseRequested(null);
            L10:
                super.onCloseWindow(r4);
                return;
            L8:
                if (MyWebView.m22014$$Nest$fgetonCloseListener(this.this$0) == null) goto L10;
                MyWebView.m22014$$Nest$fgetonCloseListener(this.this$0).run();
                MyWebView.m22024$$Nest$fputonCloseListener(this.this$0, null);
                goto L10
            }

            @Override // android.webkit.WebChromeClient
            public boolean onCreateWindow(WebView r4, boolean r5, boolean r6, Message r7) {
                this.this$0.d("onCreateWindow isDialog=" + r5 + " isUserGesture=" + r6 + " resultMsg=" + r7);
                String r52 = this.this$0.getUrl();
                if (MessagesController.getInstance(UserConfig.selectedAccount).isWebBrowserInAppEnabled() == true) goto L5;
                WebView r53 = new WebView(r4.getContext());
                r53.setWebViewClient(new AnonymousClass2(this, r53));
                ((WebView.WebViewTransport) r7.obj).setWebView(r53);
                r7.sendToTarget();
                return true;
            L5:
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) != null) goto L7;
                return false;
            L7:
                BaseFragment r42 = LaunchActivity.getSafeLastFragment();
                if (r42 != null) goto L11;
                return false;
            L11:
                if ((r42.getParentLayout() instanceof ActionBarLayout) == false) goto L13;
                r42 = ((ActionBarLayout) r42.getParentLayout()).getSheetFragment();
            L13:
                ArticleViewer r43 = r42.createArticleViewer(true);
                r43.setOpener(this.this$0);
                r43.open(null);
                MyWebView r1 = r43.getLastWebView();
                if (TextUtils.isEmpty(r52) == true) goto L16;
                r1.urlFallback = r52;
            L16:
                this.this$0.d("onCreateWindow: newWebView=" + r1);
                if (r1 == null) goto L20;
                ((WebView.WebViewTransport) r7.obj).setWebView(r1);
                r7.sendToTarget();
                return true;
            L20:
                r43.close(true, true);
                return false;
            }

            @Override // android.webkit.WebChromeClient
            public void onGeolocationPermissionsHidePrompt() {
                Dialog r0 = this.lastPermissionsDialog;
                MyWebView r1 = this.this$0;
                if (r0 == null) goto L6;
                r1.d("onGeolocationPermissionsHidePrompt: dialog.dismiss");
                this.lastPermissionsDialog.dismiss();
                this.lastPermissionsDialog = null;
                return;
            L6:
                r1.d("onGeolocationPermissionsHidePrompt: no dialog");
            }

            @Override // android.webkit.WebChromeClient
            public void onGeolocationPermissionsShowPrompt(String r10, GeolocationPermissions.Callback r11) {
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) != null) goto L5;
            L21:
                this.this$0.d("onGeolocationPermissionsShowPrompt: no container");
                r11.invoke(r10, false, false);
                return;
            L5:
                if (BotWebViewContainer.m21985$$Nest$fgetparentActivity(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0)) == null) goto L21;
                this.this$0.d("onGeolocationPermissionsShowPrompt " + r10);
                boolean r0 = this.val$bot;
                MyWebView r1 = this.this$0;
                if (r0 == false) goto L10;
                String r02 = UserObject.getUserName(BotWebViewContainer.m21976$$Nest$fgetbotUser(MyWebView.m22008$$Nest$fgetbotWebViewContainer(r1)));
            L11:
                Activity r2 = BotWebViewContainer.m21985$$Nest$fgetparentActivity(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0));
                Theme.ResourcesProvider r3 = BotWebViewContainer.m21986$$Nest$fgetresourcesProvider(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0));
                String[] r4 = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
                int r5 = R.raw.permission_request_location;
                if (this.val$bot == false) goto L14;
                int r12 = R.string.BotWebViewRequestGeolocationPermission;
            L15:
                String r6 = LocaleController.formatString(r12, new Object[]{r02});
                if (this.val$bot == false) goto L18;
                int r13 = R.string.BotWebViewRequestGeolocationPermissionWithHint;
            L19:
                Dialog r102 = AlertsCreator.createWebViewPermissionsRequestDialog(r2, r3, r4, r5, r6, LocaleController.formatString(r13, new Object[]{r02}), new BotWebViewContainer$MyWebView$3$$ExternalSyntheticLambda13(this, r11, r10));
                this.lastPermissionsDialog = r102;
                r102.show();
                return;
            L18:
                r13 = R.string.WebViewRequestGeolocationPermissionWithHint;
                goto L19
            L14:
                r12 = R.string.WebViewRequestGeolocationPermission;
                goto L15
            L10:
                r02 = AndroidUtilities.getHostAuthority(r1.getUrl());
                goto L11
            }

            @Override // android.webkit.WebChromeClient
            public boolean onJsAlert(WebView r5, String r6, String r7, JsResult r8) {
                boolean[] r0 = {false};
                Context r2 = this.val$context;
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) != null) goto L5;
                Theme.ResourcesProvider r3 = null;
            L6:
                AlertDialog.Builder r1 = new AlertDialog.Builder(r2, r3);
                if (this.val$bot == false) goto L9;
                String r4 = DialogObject.getName(this.val$botId);
            L10:
                r1.setTitle(r4).setMessage(r7).setPositiveButton(LocaleController.getString(R.string.OK), new BotWebViewContainer$MyWebView$3$$ExternalSyntheticLambda8(r0, r8)).setOnDismissListener(new BotWebViewContainer$MyWebView$3$$ExternalSyntheticLambda9(r0, r8)).show();
                return true;
            L9:
                r4 = LocaleController.formatString(R.string.WebsiteSays, new Object[]{r6});
                goto L10
            L5:
                r3 = BotWebViewContainer.m21986$$Nest$fgetresourcesProvider(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0));
                goto L6
            }

            @Override // android.webkit.WebChromeClient
            public boolean onJsConfirm(WebView r5, String r6, String r7, JsResult r8) {
                boolean[] r0 = {false};
                Context r2 = this.val$context;
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) != null) goto L5;
                Theme.ResourcesProvider r3 = null;
            L6:
                AlertDialog.Builder r1 = new AlertDialog.Builder(r2, r3);
                if (this.val$bot == false) goto L9;
                String r4 = DialogObject.getName(this.val$botId);
            L10:
                r1.setTitle(r4).setMessage(r7).setNegativeButton(LocaleController.getString(R.string.Cancel), new BotWebViewContainer$MyWebView$3$$ExternalSyntheticLambda5(r0, r8)).setPositiveButton(LocaleController.getString(R.string.OK), new BotWebViewContainer$MyWebView$3$$ExternalSyntheticLambda6(r0, r8)).setOnDismissListener(new BotWebViewContainer$MyWebView$3$$ExternalSyntheticLambda7(r0, r8)).show();
                return true;
            L9:
                r4 = LocaleController.formatString(R.string.WebsiteSays, new Object[]{r6});
                goto L10
            L5:
                r3 = BotWebViewContainer.m21986$$Nest$fgetresourcesProvider(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0));
                goto L6
            }

            @Override // android.webkit.WebChromeClient
            public boolean onJsPrompt(WebView r15, String r16, String r17, String r18, JsPromptResult r19) {
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) != null) goto L5;
                Theme.ResourcesProvider r0 = null;
            L6:
                boolean[] r2 = {false};
                AlertDialog.Builder r5 = new AlertDialog.Builder(this.val$context, r0);
                if (this.val$bot == false) goto L9;
                String r7 = DialogObject.getName(this.val$botId);
            L10:
                AlertDialog.Builder r52 = r5.setTitle(r7).setMessage(r17);
                EditTextCaption r72 = new EditTextCaption(this.val$context, r0);
                r72.lineYFix = true;
                r72.setTextSize(1, 18.0f);
                r72.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r0));
                r72.setHintColor(Theme.getColor(Theme.key_groupcreate_hintText, r0));
                r72.setFocusable(true);
                r72.setInputType(147457);
                r72.setLineColors(Theme.getColor(Theme.key_windowBackgroundWhiteInputField, r0), Theme.getColor(Theme.key_windowBackgroundWhiteInputFieldActivated, r0), Theme.getColor(Theme.key_text_RedRegular, r0));
                r72.setImeOptions(6);
                r72.setBackgroundDrawable(null);
                r72.setPadding(0, AndroidUtilities.dp(6.0f), 0, AndroidUtilities.dp(6.0f));
                r72.setText(r18);
                LinearLayout r02 = new LinearLayout(this.val$context);
                r02.setOrientation(1);
                r02.addView(r72, LayoutHelper.createLinear(-1, -2, 24.0f, 0.0f, 24.0f, 10.0f));
                r52.makeCustomMaxHeight();
                r52.setView(r02);
                r52.setWidth(AndroidUtilities.dp(292.0f));
                r52.setNegativeButton(LocaleController.getString(R.string.Cancel), new BotWebViewContainer$MyWebView$3$$ExternalSyntheticLambda0(r2, r19));
                r52.setPositiveButton(LocaleController.getString(R.string.OK), new BotWebViewContainer$MyWebView$3$$ExternalSyntheticLambda1(r2, r19, r72));
                r52.setOnDismissListener(new BotWebViewContainer$MyWebView$3$$ExternalSyntheticLambda2(r2, r19));
                r52.overrideDismissListener(new BotWebViewContainer$MyWebView$3$$ExternalSyntheticLambda3(r72));
                r72.setOnEditorActionListener(new AnonymousClass1(this, r2, r19, r72, r52.show()));
                AndroidUtilities.runOnUIThread(new BotWebViewContainer$MyWebView$3$$ExternalSyntheticLambda4(r72));
                return true;
            L9:
                r7 = LocaleController.formatString(R.string.WebsiteSays, new Object[]{r16});
                goto L10
            L5:
                r0 = BotWebViewContainer.m21986$$Nest$fgetresourcesProvider(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0));
                goto L6
            }

            @Override // android.webkit.WebChromeClient
            public void onPermissionRequest(PermissionRequest r15) {
                Dialog r0 = this.lastPermissionsDialog;
                if (r0 == null) goto L5;
                r0.dismiss();
                this.lastPermissionsDialog = null;
            L5:
                BotWebViewContainer r02 = MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0);
                MyWebView r1 = this.this$0;
                if (r02 != null) goto L9;
                r1.d("onPermissionRequest: no container");
                r15.deny();
                return;
            L9:
                r1.d("onPermissionRequest " + r15);
                boolean r03 = this.val$bot;
                MyWebView r12 = this.this$0;
                if (r03 == false) goto L12;
                String r04 = UserObject.getUserName(BotWebViewContainer.m21976$$Nest$fgetbotUser(MyWebView.m22008$$Nest$fgetbotWebViewContainer(r12)));
            L13:
                String[] r13 = r15.getResources();
                if (r13.length != 1) goto L49;
                String r2 = r13[0];
                if (BotWebViewContainer.m21985$$Nest$fgetparentActivity(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0)) != null) goto L20;
                r15.deny();
                return;
            L20:
                if (BotWebViewContainer.m21995$$Nest$misVerifyingAge(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0)) == false) goto L23;
                r15.grant(r13);
                return;
            L23:
                r2.getClass();
                if (r2.equals("android.webkit.resource.VIDEO_CAPTURE") == false) goto L26;
                Activity r6 = BotWebViewContainer.m21985$$Nest$fgetparentActivity(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0));
                Theme.ResourcesProvider r7 = BotWebViewContainer.m21986$$Nest$fgetresourcesProvider(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0));
                String[] r8 = {"android.permission.CAMERA"};
                int r9 = R.raw.permission_request_camera;
                if (this.val$bot == false) goto L41;
                int r14 = R.string.BotWebViewRequestCameraPermission;
            L42:
                String r10 = LocaleController.formatString(r14, new Object[]{r04});
                if (this.val$bot == false) goto L45;
                int r16 = R.string.BotWebViewRequestCameraPermissionWithHint;
            L46:
                Dialog r152 = AlertsCreator.createWebViewPermissionsRequestDialog(r6, r7, r8, r9, r10, LocaleController.formatString(r16, new Object[]{r04}), new BotWebViewContainer$MyWebView$3$$ExternalSyntheticLambda11(this, r15, r2));
                this.lastPermissionsDialog = r152;
                r152.show();
                return;
            L45:
                r16 = R.string.WebViewRequestCameraPermissionWithHint;
                goto L46
            L41:
                r14 = R.string.WebViewRequestCameraPermission;
                goto L42
            L26:
                if (r2.equals("android.webkit.resource.AUDIO_CAPTURE") == false) goto L71;
                Activity r72 = BotWebViewContainer.m21985$$Nest$fgetparentActivity(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0));
                Theme.ResourcesProvider r82 = BotWebViewContainer.m21986$$Nest$fgetresourcesProvider(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0));
                String[] r92 = {"android.permission.RECORD_AUDIO"};
                int r102 = R.raw.permission_request_microphone;
                if (this.val$bot == false) goto L31;
                int r17 = R.string.BotWebViewRequestMicrophonePermission;
            L32:
                String r11 = LocaleController.formatString(r17, new Object[]{r04});
                if (this.val$bot == false) goto L35;
                int r18 = R.string.BotWebViewRequestMicrophonePermissionWithHint;
            L36:
                Dialog r153 = AlertsCreator.createWebViewPermissionsRequestDialog(r72, r82, r92, r102, r11, LocaleController.formatString(r18, new Object[]{r04}), new BotWebViewContainer$MyWebView$3$$ExternalSyntheticLambda10(this, r15, r2));
                this.lastPermissionsDialog = r153;
                r153.show();
                return;
            L35:
                r18 = R.string.WebViewRequestMicrophonePermissionWithHint;
                goto L36
            L31:
                r17 = R.string.WebViewRequestMicrophonePermission;
                goto L32
            L71:
                return;
            L49:
                if (r13.length == 2) goto L51;
                return;
            L51:
                if ("android.webkit.resource.AUDIO_CAPTURE".equals(r13[0]) == true) goto L55;
                if ("android.webkit.resource.VIDEO_CAPTURE".equals(r13[0]) == true) goto L55;
                return;
            L55:
                if ("android.webkit.resource.AUDIO_CAPTURE".equals(r13[1]) == false) goto L57;
            L58:
                Activity r73 = BotWebViewContainer.m21985$$Nest$fgetparentActivity(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0));
                Theme.ResourcesProvider r83 = BotWebViewContainer.m21986$$Nest$fgetresourcesProvider(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0));
                String[] r93 = {"android.permission.CAMERA", "android.permission.RECORD_AUDIO"};
                int r103 = R.raw.permission_request_camera;
                if (this.val$bot == false) goto L61;
                int r22 = R.string.BotWebViewRequestCameraMicPermission;
            L62:
                String r112 = LocaleController.formatString(r22, new Object[]{r04});
                if (this.val$bot == false) goto L65;
                int r23 = R.string.BotWebViewRequestCameraMicPermissionWithHint;
            L66:
                Dialog r154 = AlertsCreator.createWebViewPermissionsRequestDialog(r73, r83, r93, r103, r112, LocaleController.formatString(r23, new Object[]{r04}), new BotWebViewContainer$MyWebView$3$$ExternalSyntheticLambda12(this, r15, r13));
                this.lastPermissionsDialog = r154;
                r154.show();
                return;
            L65:
                r23 = R.string.WebViewRequestCameraMicPermissionWithHint;
                goto L66
            L61:
                r22 = R.string.WebViewRequestCameraMicPermission;
                goto L62
            L57:
                if ("android.webkit.resource.VIDEO_CAPTURE".equals(r13[1]) == true) goto L58;
                return;
            L12:
                r04 = AndroidUtilities.getHostAuthority(r12.getUrl());
                goto L13
            }

            @Override // android.webkit.WebChromeClient
            public void onPermissionRequestCanceled(PermissionRequest r2) {
                Dialog r22 = this.lastPermissionsDialog;
                MyWebView r0 = this.this$0;
                if (r22 == null) goto L6;
                r0.d("onPermissionRequestCanceled: dialog.dismiss");
                this.lastPermissionsDialog.dismiss();
                this.lastPermissionsDialog = null;
                return;
            L6:
                r0.d("onPermissionRequestCanceled: no dialog");
            }

            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView r3, int r4) {
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) != null) goto L5;
            L8:
                this.this$0.d("onProgressChanged " + r4 + "%: no container");
                return;
            L5:
                if (BotWebViewContainer.m21987$$Nest$fgetwebViewProgressListener(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0)) == null) goto L8;
                this.this$0.d("onProgressChanged " + r4 + "%");
                BotWebViewContainer.m21987$$Nest$fgetwebViewProgressListener(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0)).accept(Float.valueOf(((float) r4) / 100.0f));
            }

            @Override // android.webkit.WebChromeClient
            public void onReceivedIcon(WebView r4, Bitmap r5) {
                MyWebView r0 = this.this$0;
                if (r5 != null) goto L5;
                String r1 = "null";
            L6:
                r0.d("onReceivedIcon favicon=".concat(r1));
                if (r5 != null) goto L9;
            L15:
                Bitmap r02 = (Bitmap) MyWebView.m22013$$Nest$fgetlastFavicons(this.this$0).get(this.this$0.getUrl());
                if (r5 == null) goto L22;
                if (r02 != null) goto L19;
            L20:
                MyWebView.m22013$$Nest$fgetlastFavicons(this.this$0).put(this.this$0.getUrl(), r5);
                goto L22
            L19:
                if (r02.getWidth() < r5.getWidth()) goto L20;
            L22:
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) == null) goto L24;
                MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0).onFaviconChanged(r5);
            L24:
                super.onReceivedIcon(r4, r5);
                return;
            L9:
                if (TextUtils.equals(this.this$0.getUrl(), MyWebView.m22012$$Nest$fgetlastFaviconUrl(this.this$0)) == true) goto L11;
            L14:
                MyWebView r03 = this.this$0;
                r03.lastFavicon = r5;
                MyWebView.m22023$$Nest$fputlastFaviconUrl(r03, r03.getUrl());
                MyWebView r04 = this.this$0;
                r04.lastFaviconGot = true;
                MyWebView.m22029$$Nest$msaveHistory(r04);
                goto L15
            L11:
                if (this.this$0.lastFavicon == null) goto L14;
                if (r5.getWidth() <= this.this$0.lastFavicon.getWidth()) goto L15;
            L5:
                r1 = r5.getWidth() + "x" + r5.getHeight();
                goto L6
            }

            @Override // android.webkit.WebChromeClient
            public void onReceivedTitle(WebView r4, String r5) {
                this.this$0.d("onReceivedTitle title=" + r5);
                MyWebView r0 = this.this$0;
                if (r0.errorShown == true) goto L6;
                r0.lastTitleGot = true;
                r0.lastTitle = r5;
            L6:
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(r0) == null) goto L8;
                MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0).onTitleChanged(r5);
            L8:
                super.onReceivedTitle(r4, r5);
            }

            @Override // android.webkit.WebChromeClient
            public void onReceivedTouchIconUrl(WebView r4, String r5, boolean r6) {
                this.this$0.d("onReceivedTouchIconUrl url=" + r5 + " precomposed=" + r6);
                super.onReceivedTouchIconUrl(r4, r5, r6);
            }

            @Override // android.webkit.WebChromeClient
            public boolean onShowFileChooser(WebView r4, ValueCallback<Uri[]> r5, WebChromeClient.FileChooserParams r6) {
                Activity r42 = AndroidUtilities.findActivity(this.this$0.getContext());
                MyWebView r0 = this.this$0;
                boolean r1 = false;
                if (r42 != null) goto L6;
                r0.d("onShowFileChooser: no activity, false");
                return false;
            L6:
                BotWebViewContainer r02 = MyWebView.m22008$$Nest$fgetbotWebViewContainer(r0);
                MyWebView r2 = this.this$0;
                if (r02 != null) goto L11;
                r2.d("onShowFileChooser: no container, false");
                return false;
            L11:
                if (BotWebViewContainer.m21984$$Nest$fgetmFilePathCallback(MyWebView.m22008$$Nest$fgetbotWebViewContainer(r2)) == null) goto L13;
                BotWebViewContainer.m21984$$Nest$fgetmFilePathCallback(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0)).onReceiveValue(null);
            L13:
                BotWebViewContainer.m21992$$Nest$fputmFilePathCallback(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0), r5);
                if (r6.getMode() != 1) goto L16;
                r1 = true;
            L16:
                Intent r52 = r6.createIntent();
                if (r1 == false) goto L19;
                r52.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
            L19:
                r42.startActivityForResult(r52, 3000);
                this.this$0.d("onShowFileChooser: true");
                return true;
            }
        }

        /* renamed from: org.telegram.ui.web.BotWebViewContainer$MyWebView$4, reason: invalid class name */
        public class AnonymousClass4 implements WebView.FindListener {
            final /* synthetic */ MyWebView this$0;

            public AnonymousClass4(MyWebView r1) {
                this.this$0 = r1;
            }

            @Override // android.webkit.WebView.FindListener
            public void onFindResultReceived(int r2, int r3, boolean r4) {
                MyWebView.m22026$$Nest$fputsearchIndex(this.this$0, r2);
                MyWebView.m22025$$Nest$fputsearchCount(this.this$0, r3);
                MyWebView.m22027$$Nest$fputsearchLoading(this.this$0, !r4);
                if (MyWebView.m22015$$Nest$fgetsearchListener(this.this$0) == null) goto L6;
                MyWebView.m22015$$Nest$fgetsearchListener(this.this$0).run();
                return;
            }
        }

        /* renamed from: org.telegram.ui.web.BotWebViewContainer$MyWebView$5, reason: invalid class name */
        public class AnonymousClass5 implements DownloadListener {
            final /* synthetic */ MyWebView this$0;

            public static /* synthetic */ void $r8$lambda$eZOJDSSLSO__mrdHJyr8bLXUG0E(Runnable r0, AlertDialog r1, int r2) {
                r0.run();
            }

            public static /* synthetic */ void $r8$lambda$h1GC787vAtrinjmEnjq8FO8QRvQ(AnonymousClass5 r0, String r1, String r2, String r3, String r4) {
                r0.lambda$onDownloadStart$0(r1, r2, r3, r4);
            }

            public AnonymousClass5(MyWebView r1) {
                this.this$0 = r1;
            }

            private String getFilename(String r2, String r3, String r4) {
                String r1 = Uri.parse(r2).getPathSegments().get(r1.size() - 1);     // Catch: Exception -> L9
                int r0 = r1.lastIndexOf(".");     // Catch: Exception -> L9
                if (r0 <= 0) goto L8;
                if (TextUtils.isEmpty(r1.substring(r0 + 1)) == true) goto L8;
                return r1;
            L8:
                return URLUtil.guessFileName(r2, r3, r4);
            }

            private /* synthetic */ void lambda$onDownloadStart$0(String r2, String r3, String r4, String r5) {
                DownloadManager.Request r0 = new DownloadManager.Request(Uri.parse(r2));     // Catch: Exception -> L9
                r0.setMimeType(r3);     // Catch: Exception -> L9
                r0.addRequestHeader("User-Agent", r4);     // Catch: Exception -> L9
                r0.setDescription(LocaleController.getString(R.string.WebDownloading));     // Catch: Exception -> L9
                r0.setTitle(r5);     // Catch: Exception -> L9
                r0.setNotificationVisibility(1);     // Catch: Exception -> L9
                r0.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, r5);     // Catch: Exception -> L9
                DownloadManager r32 = (DownloadManager) this.this$0.getContext().getSystemService("download");     // Catch: Exception -> L9
                if (r32 == null) goto L6;
                r32.enqueue(r0);     // Catch: Exception -> L9
            L6:
                if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0) == null) goto L14;
                BulletinFactory.of(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0), BotWebViewContainer.m21986$$Nest$fgetresourcesProvider(MyWebView.m22008$$Nest$fgetbotWebViewContainer(this.this$0))).createSimpleBulletin(R.raw.ic_download, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.WebDownloadingFile, new Object[]{r5}))).show(true);     // Catch: Exception -> L9
                return;
            L14:
                return;
            L9:
                e = move-exception;
                FileLog.e(e);
            }

            @Override // android.webkit.DownloadListener
            public void onDownloadStart(String r7, String r8, String r9, String r10, long r11) {
                this.this$0.d("onDownloadStart " + r7 + " " + r8 + " " + r9 + " " + r10 + " " + r11);
                WebAdBlocker r0 = this.this$0.adBlocker;
                if (r0 == null) goto L23;
                r0.onDownloadStart();
            L23:
            L20:
                e = move-exception;
                FileLog.e(e);
                return;
            L6:
                if (r7.startsWith("blob:") == true) goto L24;
                String r5 = AndroidUtilities.escape(getFilename(r7, r9, r10));     // Catch: Exception -> L20
                BotWebViewContainer$MyWebView$5$$ExternalSyntheticLambda0 r02 = new BotWebViewContainer$MyWebView$5$$ExternalSyntheticLambda0(this, r7, r10, r8, r5);     // Catch: Exception -> L20
                if (DownloadController.getInstance(UserConfig.selectedAccount).canDownloadMedia(8, r11) == true) goto L18;
                AlertDialog.Builder r6 = new AlertDialog.Builder(this.this$0.getContext());     // Catch: Exception -> L20
                r6.setTitle(LocaleController.getString(R.string.WebDownloadAlertTitle));     // Catch: Exception -> L20
                if (r11 <= 0) goto L13;
                String r72 = LocaleController.formatString(R.string.WebDownloadAlertInfoWithSize, new Object[]{r5, AndroidUtilities.formatFileSize(r11)});     // Catch: Exception -> L20
            L14:
                r6.setMessage(AndroidUtilities.replaceTags(r72));     // Catch: Exception -> L20
                r6.setPositiveButton(LocaleController.getString(R.string.WebDownloadAlertYes), new BotWebViewContainer$MyWebView$5$$ExternalSyntheticLambda1(r02));     // Catch: Exception -> L20
                r6.setNegativeButton(LocaleController.getString(R.string.Cancel), null);     // Catch: Exception -> L20
                TextView r62 = (TextView) r6.show().getButton(-2);     // Catch: Exception -> L20
                if (r62 == null) goto L25;
                r62.setTextColor(Theme.getColor(Theme.key_text_RedBold));     // Catch: Exception -> L20
                return;
            L25:
                return;
            L13:
                r72 = LocaleController.formatString(R.string.WebDownloadAlertInfo, new Object[]{r5});     // Catch: Exception -> L20
                goto L14
            L18:
                r02.run();     // Catch: Exception -> L20
                return;
            }
        }

        /* renamed from: $r8$lambda$n4G84VuBJd3zHi0noWbm8zvFC-g, reason: not valid java name */
        public static /* synthetic */ void m22005$r8$lambda$n4G84VuBJd3zHi0noWbm8zvFCg(String r0) {
        }

        /* renamed from: -$$Nest$fgetbotWebMessageListenerAdded, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m22006$$Nest$fgetbotWebMessageListenerAdded(MyWebView r0) {
            return r0.botWebMessageListenerAdded;
        }

        /* renamed from: -$$Nest$fgetbotWebMessageShimHandler, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ScriptHandler m22007$$Nest$fgetbotWebMessageShimHandler(MyWebView r0) {
            return r0.botWebMessageShimHandler;
        }

        /* renamed from: -$$Nest$fgetbotWebViewContainer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BotWebViewContainer m22008$$Nest$fgetbotWebViewContainer(MyWebView r0) {
            return r0.botWebViewContainer;
        }

        /* renamed from: -$$Nest$fgetcurrentHistoryEntry, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BrowserHistory.Entry m22009$$Nest$fgetcurrentHistoryEntry(MyWebView r0) {
            return r0.currentHistoryEntry;
        }

        /* renamed from: -$$Nest$fgetcurrentSheet, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BottomSheet m22010$$Nest$fgetcurrentSheet(MyWebView r0) {
            return r0.currentSheet;
        }

        /* renamed from: -$$Nest$fgetisPageLoaded, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m22011$$Nest$fgetisPageLoaded(MyWebView r0) {
            return r0.isPageLoaded;
        }

        /* renamed from: -$$Nest$fgetlastFaviconUrl, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m22012$$Nest$fgetlastFaviconUrl(MyWebView r0) {
            return r0.lastFaviconUrl;
        }

        /* renamed from: -$$Nest$fgetlastFavicons, reason: not valid java name */
        public static /* bridge */ /* synthetic */ HashMap m22013$$Nest$fgetlastFavicons(MyWebView r0) {
            return r0.lastFavicons;
        }

        /* renamed from: -$$Nest$fgetonCloseListener, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m22014$$Nest$fgetonCloseListener(MyWebView r0) {
            return r0.onCloseListener;
        }

        /* renamed from: -$$Nest$fgetsearchListener, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m22015$$Nest$fgetsearchListener(MyWebView r0) {
            return r0.searchListener;
        }

        /* renamed from: -$$Nest$fgetwhenPageLoaded, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m22016$$Nest$fgetwhenPageLoaded(MyWebView r0) {
            return r0.whenPageLoaded;
        }

        /* renamed from: -$$Nest$fputbotWebMessageListenerAdded, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m22017$$Nest$fputbotWebMessageListenerAdded(MyWebView r0, boolean r1) {
            r0.botWebMessageListenerAdded = r1;
        }

        /* renamed from: -$$Nest$fputbotWebMessageShimHandler, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m22018$$Nest$fputbotWebMessageShimHandler(MyWebView r0, ScriptHandler r1) {
            r0.botWebMessageShimHandler = r1;
        }

        /* renamed from: -$$Nest$fputcurrentHistoryEntry, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m22019$$Nest$fputcurrentHistoryEntry(MyWebView r0, BrowserHistory.Entry r1) {
            r0.currentHistoryEntry = r1;
        }

        /* renamed from: -$$Nest$fputcurrentSheet, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m22020$$Nest$fputcurrentSheet(MyWebView r0, BottomSheet r1) {
            r0.currentSheet = r1;
        }

        /* renamed from: -$$Nest$fputcurrentUrl, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m22021$$Nest$fputcurrentUrl(MyWebView r0, String r1) {
            r0.currentUrl = r1;
        }

        /* renamed from: -$$Nest$fputisPageLoaded, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m22022$$Nest$fputisPageLoaded(MyWebView r0, boolean r1) {
            r0.isPageLoaded = r1;
        }

        /* renamed from: -$$Nest$fputlastFaviconUrl, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m22023$$Nest$fputlastFaviconUrl(MyWebView r0, String r1) {
            r0.lastFaviconUrl = r1;
        }

        /* renamed from: -$$Nest$fputonCloseListener, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m22024$$Nest$fputonCloseListener(MyWebView r0, Runnable r1) {
            r0.onCloseListener = r1;
        }

        /* renamed from: -$$Nest$fputsearchCount, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m22025$$Nest$fputsearchCount(MyWebView r0, int r1) {
            r0.searchCount = r1;
        }

        /* renamed from: -$$Nest$fputsearchIndex, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m22026$$Nest$fputsearchIndex(MyWebView r0, int r1) {
            r0.searchIndex = r1;
        }

        /* renamed from: -$$Nest$fputsearchLoading, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m22027$$Nest$fputsearchLoading(MyWebView r0, boolean r1) {
            r0.searchLoading = r1;
        }

        /* renamed from: -$$Nest$fputwhenPageLoaded, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m22028$$Nest$fputwhenPageLoaded(MyWebView r0, Runnable r1) {
            r0.whenPageLoaded = r1;
        }

        /* renamed from: -$$Nest$msaveHistory, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m22029$$Nest$msaveHistory(MyWebView r0) {
            r0.saveHistory();
        }

        public MyWebView(Context r8, boolean r9, long r10) {
            super(r8);
            int r0 = BotWebViewContainer.m22002$$Nest$sfgettags();
            BotWebViewContainer.m22003$$Nest$sfputtags(r0 + 1);
            this.tag = r0;
            this.urlFallback = "about:blank";
            this.lastFavicons = new HashMap();
            this.bot = r9;
            d("created new webview " + this);
            if (r9 == false) goto L5;
            WebAdBlocker r02 = null;
        L6:
            this.adBlocker = r02;
            setOnLongClickListener(new AnonymousClass1(this));
            setWebViewClient(new AnonymousClass2(this, r9, r8));
            setWebChromeClient(new AnonymousClass3(this, r8, r9, r10));
            setFindListener(new AnonymousClass4(this));
            if (r9 == true) goto L10;
            setDownloadListener(new AnonymousClass5(this));
            return;
        L10:
            return;
        L5:
            r02 = new WebAdBlocker(this);
            goto L6
        }

        private void saveHistory() {
            if (this.bot == true) goto L12;
            WebMetadataCache.WebMetadata r0 = WebMetadataCache.WebMetadata.from(this);
            WebMetadataCache.getInstance().save(r0);
            BrowserHistory.Entry r2 = this.currentHistoryEntry;
            if (r2 == null) goto L10;
            if (r0 == null) goto L11;
            r2.meta = r0;
            BrowserHistory.pushHistory(r2);
            return;
        L11:
            return;
        L10:
            return;
        }

        public boolean applyCachedMeta(WebMetadataCache.WebMetadata r6) {
            boolean r0 = false;
            if (r6 != null) goto L5;
            return false;
        L5:
            BotWebViewContainer r1 = this.botWebViewContainer;
            if (r1 != null) goto L8;
        L25:
            if (r0 == true) goto L29;
            setTitle(null);
            BotWebViewContainer r5 = this.botWebViewContainer;
            if (r5 == null) goto L29;
            r5.onTitleChanged(null);
        L29:
            return true;
        L8:
            if (BotWebViewContainer.m21978$$Nest$fgetdelegate(r1) == null) goto L25;
            if (r6.actionBarColor == 0) goto L12;
            BotWebViewContainer.m21978$$Nest$fgetdelegate(this.botWebViewContainer).onWebAppBackgroundChanged(true, r6.actionBarColor);
            this.lastActionBarColorGot = true;
        L12:
            int r12 = r6.backgroundColor;
            if (r12 == 0) goto L15;
            BotWebViewContainer.m21978$$Nest$fgetdelegate(this.botWebViewContainer).onWebAppBackgroundChanged(false, r6.backgroundColor);
            this.lastBackgroundColorGot = true;
        L16:
            Bitmap r3 = r6.favicon;
            if (r3 == null) goto L20;
            BotWebViewContainer r4 = this.botWebViewContainer;
            this.lastFavicon = r3;
            r4.onFaviconChanged(r3);
            this.lastFaviconGot = true;
        L20:
            if (TextUtils.isEmpty(r6.sitename) == true) goto L23;
            String r62 = r6.sitename;
            this.lastSiteName = r62;
            BotWebViewContainer r02 = this.botWebViewContainer;
            this.lastTitle = r62;
            r02.onTitleChanged(r62);
            r0 = true;
        L23:
            if (SharedConfig.adaptableColorInBrowser == false) goto L25;
            setBackgroundColor(r12);
            goto L25
        L15:
            r12 = -1;
            goto L16
        }

        @Override // android.webkit.WebView
        public boolean canGoBack() {
            if (super.canGoBack() == false) goto L6;
            return true;
        L6:
            return false;
        }

        public void checkCachedMetaProperties(String r2) {
            if (this.bot == false) goto L5;
            return;
        L5:
            String r22 = AndroidUtilities.getHostAuthority(r2, true);
            applyCachedMeta(WebMetadataCache.getInstance().get(r22));
        }

        @Override // android.webkit.WebView
        public void clearHistory() {
            d("clearHistory");
            super.clearHistory();
        }

        public void d(String r3) {
            FileLog.d("[webview] #" + this.tag + " " + r3);
        }

        @Override // android.webkit.WebView
        public void destroy() {
            d("destroy");
            super.destroy();
        }

        @Override // android.webkit.WebView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r1) {
            super.dispatchDraw(r1);
        }

        @Override // android.view.View
        public void draw(Canvas r1) {
            super.draw(r1);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r1, View r2, long r3) {
            return super.drawChild(r1, r2, r3);
        }

        public void evaluateJS(String r2) {
            evaluateJavascript(r2, new BotWebViewContainer$MyWebView$$ExternalSyntheticLambda0());
        }

        @Override // android.webkit.WebView
        public Bitmap getFavicon() {
            if (this.errorShown == false) goto L7;
            return null;
        L7:
            return this.lastFavicon;
        }

        public Bitmap getFavicon(String r1) {
            return this.lastFavicons.get(r1);
        }

        public String getOpenURL() {
            return this.openedByUrl;
        }

        public float getScrollProgress() {
            float r0 = Math.max(1, computeVerticalScrollRange() - computeVerticalScrollExtent());
            if (r0 > getHeight()) goto L7;
            return 0.0f;
        L7:
            return Utilities.clamp01(getScrollY() / r0);
        }

        public int getSearchCount() {
            return this.searchCount;
        }

        public int getSearchIndex() {
            return this.searchIndex;
        }

        @Override // android.webkit.WebView
        public String getTitle() {
            return this.lastTitle;
        }

        @Override // android.webkit.WebView
        public String getUrl() {
            if (this.dangerousUrl == true) goto L5;
            String r0 = super.getUrl();
            this.lastUrl = r0;
            return r0;
        L5:
            return this.urlFallback;
        }

        @Override // android.webkit.WebView
        public void goBack() {
            d("goBack");
            super.goBack();
        }

        @Override // android.webkit.WebView
        public void goForward() {
            d("goForward");
            super.goForward();
        }

        public boolean isPageLoaded() {
            return this.isPageLoaded;
        }

        public boolean isUrlDangerous() {
            if (this.dangerousUrl == true) goto L6;
            return false;
        L6:
            return true;
        }

        @Override // android.webkit.WebView
        public void loadData(String r3, String r4, String r5) {
            this.openedByUrl = null;
            d("loadData " + r3 + " " + r4 + " " + r5);
            super.loadData(r3, r4, r5);
        }

        @Override // android.webkit.WebView
        public void loadDataWithBaseURL(String r3, String r4, String r5, String r6, String r7) {
            this.openedByUrl = null;
            d("loadDataWithBaseURL " + r3 + " " + r4 + " " + r5 + " " + r6 + " " + r7);
            super.loadDataWithBaseURL(r3, r4, r5, r6, r7);
        }

        @Override // android.webkit.WebView
        public void loadUrl(String r3) {
            BottomSheet r0 = this.currentSheet;
            if (r0 == null) goto L5;
            r0.dismiss();
            this.currentSheet = null;
        L5:
            checkCachedMetaProperties(r3);
            this.openedByUrl = r3;
            String r32 = BotWebViewContainer.m22004$$Nest$smtonsite2magic(r3);
            this.currentUrl = r32;
            d("loadUrl " + r32);
            super.loadUrl(r32);
            BotWebViewContainer r02 = this.botWebViewContainer;
            if (r02 != null) goto L8;
            return;
        L8:
            if (this.dangerousUrl == false) goto L10;
            r32 = this.urlFallback;
        L10:
            r02.onURLChanged(r32, !canGoBack(), !canGoForward());
        }

        @Override // android.webkit.WebView
        public void loadUrl(String r3, Map<String, String> r4) {
            BottomSheet r0 = this.currentSheet;
            if (r0 == null) goto L5;
            r0.dismiss();
            this.currentSheet = null;
        L5:
            checkCachedMetaProperties(r3);
            this.openedByUrl = r3;
            String r32 = BotWebViewContainer.m22004$$Nest$smtonsite2magic(r3);
            this.currentUrl = r32;
            d("loadUrl " + r32 + " " + r4);
            super.loadUrl(r32, r4);
            BotWebViewContainer r42 = this.botWebViewContainer;
            if (r42 != null) goto L8;
            return;
        L8:
            if (this.dangerousUrl == false) goto L10;
            r32 = this.urlFallback;
        L10:
            r42.onURLChanged(r32, !canGoBack(), !canGoForward());
        }

        public void loadUrl(String r2, WebMetadataCache.WebMetadata r3) {
            BottomSheet r0 = this.currentSheet;
            if (r0 == null) goto L5;
            r0.dismiss();
            this.currentSheet = null;
        L5:
            applyCachedMeta(r3);
            this.openedByUrl = r2;
            String r22 = BotWebViewContainer.m22004$$Nest$smtonsite2magic(r2);
            this.currentUrl = r22;
            d("loadUrl " + r22 + " with cached meta");
            super.loadUrl(r22);
            BotWebViewContainer r32 = this.botWebViewContainer;
            if (r32 != null) goto L8;
            return;
        L8:
            if (this.dangerousUrl == false) goto L10;
            r22 = this.urlFallback;
        L10:
            r32.onURLChanged(r22, !canGoBack(), !canGoForward());
        }

        @Override // android.webkit.WebView, android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            d("attached");
            AndroidUtilities.checkAndroidTheme(getContext(), true);
            super.onAttachedToWindow();
        }

        @Override // android.webkit.WebView, android.view.View
        public boolean onCheckIsTextEditor() {
            BotWebViewContainer r0 = this.botWebViewContainer;
            if (r0 != null) goto L6;
            d("onCheckIsTextEditor: no container");
            return false;
        L6:
            boolean r02 = r0.isFocusable();
            d("onCheckIsTextEditor: " + r02);
            return r02;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            d("detached");
            AndroidUtilities.checkAndroidTheme(getContext(), false);
            super.onDetachedFromWindow();
        }

        @Override // android.webkit.WebView, android.view.View
        public void onDraw(Canvas r1) {
            super.onDraw(r1);
        }

        @Override // android.webkit.WebView, android.widget.AbsoluteLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30));
        }

        @Override // android.webkit.WebView
        public void onPause() {
            d("onPause");
            super.onPause();
        }

        @Override // android.webkit.WebView
        public void onResume() {
            d("onResume");
            super.onResume();
        }

        @Override // android.webkit.WebView, android.view.View
        public void onScrollChanged(int r1, int r2, int r3, int r4) {
            super.onScrollChanged(r1, r2, r3, r4);
            WebViewScrollListener r12 = this.webViewScrollListener;
            if (r12 == null) goto L5;
            r12.onWebViewScrolled(this, getScrollX() - this.prevScrollX, getScrollY() - this.prevScrollY);
        L5:
            this.prevScrollX = getScrollX();
            this.prevScrollY = getScrollY();
        }

        @Override // android.webkit.WebView, android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent r4) {
            if (this.botWebViewContainer == null) goto L10;
            if (r4.getAction() != 0) goto L10;
            BotWebViewContainer.m21991$$Nest$fputlastClickMs(this.botWebViewContainer, System.currentTimeMillis());
            if (BotWebViewContainer.m21995$$Nest$misVerifyingAge(this.botWebViewContainer) == true) goto L10;
            getSettings().setMediaPlaybackRequiresUserGesture(false);
        L10:
            return super.onTouchEvent(r4);
        }

        @Override // android.webkit.WebView
        public void pauseTimers() {
            d("pauseTimers");
            super.pauseTimers();
        }

        @Override // android.webkit.WebView
        public void postUrl(String r3, byte[] r4) {
            d("postUrl " + r3 + " " + r4);
            super.postUrl(r3, r4);
        }

        @Override // android.webkit.WebView
        public void reload() {
            CookieManager.getInstance().flush();
            d("reload");
            super.reload();
        }

        @Override // android.webkit.WebView
        public void resumeTimers() {
            d("resumeTimers");
            super.resumeTimers();
        }

        public void search(String r2, Runnable r3) {
            this.searchLoading = true;
            this.searchListener = r3;
            findAllAsync(r2);
        }

        public void setCloseListener(Runnable r1) {
            this.onCloseListener = r1;
        }

        public void setContainers(BotWebViewContainer r3, WebViewScrollListener r4) {
            d("setContainers(" + r3 + ", " + r4 + ")");
            if (this.botWebViewContainer != null) goto L6;
            if (r3 == null) goto L6;
            boolean r0 = true;
        L7:
            this.botWebViewContainer = r3;
            this.webViewScrollListener = r4;
            if (r0 == false) goto L11;
            evaluateJS("window.__tg__postBackgroundChange()");
            return;
        L11:
            return;
        L6:
            r0 = false;
            goto L7
        }

        @Override // android.view.View
        public void setFocusable(int r3) {
            d("setFocusable " + r3);
            super.setFocusable(r3);
        }

        @Override // android.view.View
        public void setFocusable(boolean r3) {
            d("setFocusable " + r3);
            super.setFocusable(r3);
        }

        @Override // android.view.View
        public void setFocusableInTouchMode(boolean r3) {
            d("setFocusableInTouchMode " + r3);
            super.setFocusableInTouchMode(r3);
        }

        @Override // android.view.View
        public void setFocusedByDefault(boolean r3) {
            d("setFocusedByDefault " + r3);
            super.setFocusedByDefault(r3);
        }

        public void setScrollProgress(float r3) {
            setScrollY((int) (r3 * Math.max(1, computeVerticalScrollRange() - computeVerticalScrollExtent())));
        }

        @Override // android.view.View
        public void setScrollX(int r1) {
            super.setScrollX(r1);
            this.prevScrollX = r1;
        }

        @Override // android.view.View
        public void setScrollY(int r1) {
            super.setScrollY(r1);
            this.prevScrollY = r1;
        }

        public void setTitle(String r1) {
            this.lastTitle = r1;
        }

        @Override // android.webkit.WebView
        public void stopLoading() {
            d("stopLoading");
            super.stopLoading();
        }

        @Override // android.view.View
        public void stopNestedScroll() {
            d("stopNestedScroll");
            super.stopNestedScroll();
        }
    }

    /* loaded from: classes6.dex */
    public static final class PopupButton {
        public String id;
        public String text;
        public int textColorKey;

        public PopupButton(JSONObject r3) {
            this.textColorKey = -1;
            this.id = r3.getString("id");
            String r0 = r3.getString("type");
            switch(r0.hashCode()) {
                case -1829997182: goto L19;
                case -1367724422: goto L15;
                case 3548: goto L11;
                case 94756344: goto L7;
                case 1544803905: goto L5;
                default: goto L21;
            };
        L5:
            r0.equals(PluginsConstants.Settings.DEFAULT);
        L21:
            this.text = r3.getString("text");
            return;
        L7:
            if (r0.equals("close") == false) goto L21;
            this.text = LocaleController.getString(R.string.Close);
            return;
        L11:
            if (r0.equals("ok") == false) goto L21;
            this.text = LocaleController.getString(R.string.OK);
            return;
        L15:
            if (r0.equals("cancel") == false) goto L21;
            this.text = LocaleController.getString(R.string.Cancel);
            return;
        L19:
            if (r0.equals("destructive") == false) goto L21;
            this.textColorKey = Theme.key_text_RedBold;
            goto L21
        }
    }

    /* loaded from: classes6.dex */
    public static final class WebRequestContext {
        private final long documentGeneration;
        private final String origin;
        private final BotWebViewContainer owner;
        private final boolean restrictToOrigin;
        private final MyWebView webView;

        /* renamed from: -$$Nest$fgetdocumentGeneration, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m22038$$Nest$fgetdocumentGeneration(WebRequestContext r2) {
            return r2.documentGeneration;
        }

        /* renamed from: -$$Nest$fgetorigin, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m22039$$Nest$fgetorigin(WebRequestContext r0) {
            return r0.origin;
        }

        /* renamed from: -$$Nest$fgetowner, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BotWebViewContainer m22040$$Nest$fgetowner(WebRequestContext r0) {
            return r0.owner;
        }

        /* renamed from: -$$Nest$fgetrestrictToOrigin, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m22041$$Nest$fgetrestrictToOrigin(WebRequestContext r0) {
            return r0.restrictToOrigin;
        }

        /* renamed from: -$$Nest$fgetwebView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ MyWebView m22042$$Nest$fgetwebView(WebRequestContext r0) {
            return r0.webView;
        }

        private WebRequestContext(BotWebViewContainer r1, MyWebView r2, long r3, boolean r5, String r6) {
            this.owner = r1;
            this.webView = r2;
            this.documentGeneration = r3;
            this.restrictToOrigin = r5;
            this.origin = r6;
        }

        public /* synthetic */ WebRequestContext(BotWebViewContainer r1, MyWebView r2, long r3, boolean r5, String r6, BotWebViewContainerIA r7) {
            this(r1, r2, r3, r5, r6);
        }
    }

    /* loaded from: classes6.dex */
    public static class WebViewProxy {
        public BotWebViewContainer container;
        public final MyWebView webView;

        public static /* synthetic */ void $r8$lambda$8eY8ETGZQZLjXo5lV3in0UH3d0I(WebViewProxy r0, Boolean r1) {
            r0.lambda$resolveShare$1(r1);
        }

        /* renamed from: $r8$lambda$cL1mGEkzblWtJSUIU6L-2Vlkbq8, reason: not valid java name */
        public static /* synthetic */ void m22043$r8$lambda$cL1mGEkzblWtJSUIU6L2Vlkbq8(WebViewProxy r0, String r1, String r2) {
            r0.lambda$postEvent$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$qLCTATRWjAdFVBfno8Pwz04xMAI(WebViewProxy r0, String r1, byte[] r2, String r3, String r4) {
            r0.lambda$resolveShare$2(r1, r2, r3, r4);
        }

        public WebViewProxy(MyWebView r1, BotWebViewContainer r2) {
            this.webView = r1;
            this.container = r2;
        }

        private /* synthetic */ void lambda$postEvent$0(String r1, String r2) {
            BotWebViewContainer r0 = this.container;
            if (r0 != null) goto L5;
            return;
        L5:
            BotWebViewContainer.m21999$$Nest$monWebEventReceived(r0, r1, r2);
        }

        private /* synthetic */ void lambda$resolveShare$1(Boolean r3) {
            MyWebView r2 = this.webView;
            StringBuilder r0 = new StringBuilder("window.navigator.__share__receive(");
            if (r3.booleanValue() == false) goto L5;
            String r32 = _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            r0.append(r32);
            r0.append(")");
            r2.evaluateJS(r0.toString());
            return;
        L5:
            r32 = "'abort'";
            goto L6
        }

        private /* synthetic */ void lambda$resolveShare$2(String r10, byte[] r11, String r12, String r13) {
            if (this.container != null) goto L6;
            return;
        L6:
            if ((System.currentTimeMillis() - BotWebViewContainer.m21982$$Nest$fgetlastClickMs(this.container)) <= NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L9;
            this.webView.evaluateJS("window.navigator.__share__receive(\"security\")");
            return;
        L9:
            BotWebViewContainer.m21991$$Nest$fputlastClickMs(this.container, 0);
            Context r1 = this.webView.getContext();
            Activity r3 = AndroidUtilities.findActivity(r1);
            if (r3 != null) goto L14;
            LaunchActivity r4 = LaunchActivity.instance;
            if (r4 == null) goto L14;
            r3 = r4;
        L14:
            if (r1 == null) goto L84;
            if (r3 == null) goto L84;
            if ((r3 instanceof LaunchActivity) == false) goto L84;
            if (r3.isFinishing() == true) goto L84;
            if (this.webView.isAttachedToWindow() == false) goto L84;
            LaunchActivity r32 = (LaunchActivity) r3;
            File r14 = null;
            JSONObject r2 = new JSONObject(r10);     // Catch: Exception -> L32
            String r102 = r2.optString("url", null);     // Catch: Exception -> L32
            String r42 = r2.optString("text", null);     // Catch: Exception -> L30
            String r22 = r2.optString("title", null);     // Catch: Exception -> L28
        L35:
            StringBuilder r5 = new StringBuilder();
            if (r22 == null) goto L39;
            r5.append(r22);
        L39:
            if (r42 != null) goto L41;
        L44:
            if (r102 != null) goto L46;
        L49:
            Intent r103 = new Intent("android.intent.action.SEND");
            r103.putExtra("android.intent.extra.TEXT", r5.toString());
            if (r11 == null) goto L81;
            int r43 = 0;
        L52:
            if (r14 != null) goto L54;
        L72:
            File r52 = FileLoader.getDirectory(4);
            StringBuilder r6 = new StringBuilder();
            if (r12 != null) goto L75;
            String r7 = "file";
        L76:
            r6.append(FileLoader.fixFileName(r7));
            if (r43 <= 0) goto L79;
            String r72 = " (" + r43 + ")";
        L80:
            r6.append(r72);
            r14 = new File(r52, r6.toString());
            r43 = r43 + 1;
            goto L52
        L79:
            r72 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L80
        L75:
            r7 = r12;
            goto L76
        L54:
            if (r14.exists() == true) goto L72;
            FileOutputStream r44 = new FileOutputStream(r14);     // Catch: Exception -> L58
            r44.write(r11);     // Catch: Exception -> L58
            r44.close();     // Catch: Exception -> L58
        L96:
            if (r13 != null) goto L64;
            r103.setType("text/plain");     // Catch: Exception -> L62
        L65:
            if (r12 == null) goto L86;
            r103.putExtra("android.intent.extra.TITLE", r12);     // Catch: Exception -> L62
        L86:
            r103.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(r32, ApplicationLoader.getApplicationId() + ".provider", r14));     // Catch: Exception -> L69
            r103.setFlags(1);     // Catch: Exception -> L69
        L82:
            r32.whenWebviewShareAPIDone(new BotWebViewContainer$WebViewProxy$$ExternalSyntheticLambda2(this));
            r32.startActivityForResult(Intent.createChooser(r103, LocaleController.getString(R.string.ShareFile)), 521);
            return;
        L69:
            r103.putExtra("android.intent.extra.STREAM", Uri.fromFile(r14));     // Catch: Exception -> L62
            goto L82
        L64:
            r103.setType(r13);     // Catch: Exception -> L62
        L62:
            e = move-exception;
            FileLog.e(e);
        L58:
            e = move-exception;
            FileLog.e(e);
            goto L96
        L81:
            r103.setType("text/plain");
            goto L82
        L46:
            if (r5.length() <= 0) goto L48;
            r5.append("\n");
        L48:
            r5.append(r102);
            goto L49
        L41:
            if (r5.length() <= 0) goto L43;
            r5.append("\n");
        L43:
            r5.append(r42);
        L28:
            e = e;
        L34:
            FileLog.e(e);
            r22 = null;
        L30:
            e = e;
            r42 = null;
        L32:
            e = e;
            r102 = null;
            r42 = null;
        L84:
            this.webView.evaluateJS("window.navigator.__share__receive(\"security\")");
        }

        @JavascriptInterface
        @Keep
        public void postEvent(String r2, String r3) {
            if (this.container != null) goto L5;
            return;
        L5:
            AndroidUtilities.runOnUIThread(new BotWebViewContainer$WebViewProxy$$ExternalSyntheticLambda1(this, r2, r3));
        }

        @JavascriptInterface
        @Keep
        public void resolveShare(String r7, byte[] r8, String r9, String r10) {
            AndroidUtilities.runOnUIThread(new BotWebViewContainer$WebViewProxy$$ExternalSyntheticLambda0(this, r7, r8, r9, r10));
        }

        public void setContainer(BotWebViewContainer r1) {
            this.container = r1;
        }
    }

    /* loaded from: classes6.dex */
    public interface WebViewScrollListener {
        void onWebViewScrolled(WebView r1, int r2, int r3);
    }

    /* renamed from: $r8$lambda$0xXA-tfesGsfVmYkCtQ9Sk9tez0, reason: not valid java name */
    public static /* synthetic */ void m21952$r8$lambda$0xXAtfesGsfVmYkCtQ9Sk9tez0(BotWebViewContainer r0, WebRequestContext r1, PopupButton r2, AtomicBoolean r3, AlertDialog r4, int r5) {
        r0.lambda$onEventReceived$9(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$0yjXzIN7PcjxhlSWY_7fV0AXBoI(BotWebViewContainer r0, TLObject r1, TLRPC.TL_messages_requestUrlAuth r2, String r3, TLRPC.TL_error r4, String r5) {
        r0.lambda$onWebEventReceived$5(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$1PCFrs4SY0M8O3LxuN_X5iT8mwY(File r2, int[] r3, Runnable r4) {
        AnimatedFileNative.getVideoInfo(r2.getAbsolutePath(), r3, 0);
        AndroidUtilities.runOnUIThread(r4);
    }

    public static /* synthetic */ void $r8$lambda$1qyV4yNdi7hA88yIyjUXChkiRi4(String[] r3, int r4, MyWebView r5, WebRequestContext r6) {
        if (r3[0] != null) goto L10;
        return;
    L10:
        JSONObject r1 = new JSONObject();     // Catch: Exception -> L7
        r1.put("status", r3[0]);     // Catch: Exception -> L7
        notifyEvent(r4, r5, r6, "phone_requested", r1);     // Catch: Exception -> L7
        return;
    L7:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$6zZmFYch9MQ7bG7epOhTgH8fHSw(BotWebViewContainer r0, String r1, WebRequestContext r2, Boolean r3) {
        r0.lambda$onEventReceived$34(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$7Lk9r_2_fzNDn3CmD4UFfkS3iVY(BotWebViewContainer r0, WebRequestContext r1) {
        r0.lambda$onEventReceived$28(r1);
    }

    public static /* synthetic */ void $r8$lambda$8NDPmdVMVYESF97_vL5FBVZDv4w(BotWebViewContainer r0, boolean[] r1, String r2, TL_keyboard.TL_buttonTypeRequestPeer r3, WebRequestContext r4, List r5) {
        r0.lambda$onEventReceived$55(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$8V2lWUyaoZIqhdJas8Yl3GzNqag(BotWebViewContainer r0, WebRequestContext r1, Boolean r2, String r3) {
        r0.lambda$onEventReceived$41(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$8p07neB3AFKR34Ayg9l-QHO7GG8, reason: not valid java name */
    public static /* synthetic */ void m21953$r8$lambda$8p07neB3AFKR34Ayg9lQHO7GG8(BotWebViewContainer r0, boolean r1, String r2) {
        r0.lambda$evaluateJs$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$8rqha2ksac_Jzqm9H90maCdksR4(Runnable[] r1, DialogInterface r2) {
        Runnable r0 = r1[0];
        if (r0 == null) goto L6;
        r0.run();
        r1[0] = null;
        return;
    }

    public static /* synthetic */ void $r8$lambda$A58zimWnw9sgr5RazfaT9wk2Z7o(BotWebViewContainer r0) {
        r0.lambda$reload$1();
    }

    /* renamed from: $r8$lambda$AvO0D7sxJYyGrH-gDMs6RLXi-UU, reason: not valid java name */
    public static /* synthetic */ void m21954$r8$lambda$AvO0D7sxJYyGrHgDMs6RLXiUU(BotWebViewContainer r0, WebRequestContext r1, String r2, String r3, BotStorage r4, String r5, String r6, String r7) {
        r0.lambda$restoreStorageKey$60(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$BJI_VGyiuJvL7FYUUN0FMPZYAFc(BotWebViewContainer r0, String r1, TLObject r2, TLRPC.TL_error r3, int r4, MyWebView r5, WebRequestContext r6) {
        r0.lambda$onEventReceived$22(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$F5ikMaWrcNfUsXB12gJkNg6WGbU(BotWebViewContainer r0, TLObject r1, int r2, MyWebView r3, WebRequestContext r4, TLRPC.TL_error r5) {
        r0.lambda$onEventReceived$20(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$F7VjKe3XFKtk6ZiK_vub3sr3bbE(BotWebViewContainer r0, WebRequestContext r1, String r2, String r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$onEventReceived$47(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$GqRfJ9GoZUth5VkSQmXaRAyqouo(BotWebViewContainer r0) {
        r0.lambda$onEventReceived$48();
    }

    public static /* synthetic */ void $r8$lambda$HGS9h3ajtC5j4N1YZmg75AV3hfQ(BotWebViewContainer r0, Runnable[] r1, WebRequestContext r2, AlertDialog r3, int r4) {
        r0.lambda$onEventReceived$30(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$HknNJkVURwhtMVCsBnHTRB2mxZk(BotWebViewContainer r0, WebRequestContext r1, String r2, TLRPC.Document r3) {
        r0.lambda$onEventReceived$40(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$I_aUGAFGzhVOfath5hs-cjCzZjk, reason: not valid java name */
    public static /* synthetic */ void m21955$r8$lambda$I_aUGAFGzhVOfath5hscjCzZjk(BotWebViewContainer r0, File r1, AlertDialog r2, String r3, String r4, String r5) {
        r0.lambda$onEventReceived$37(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$J9qr9gLAdQ5wU81HYfMsniA0JKs(BotWebViewContainer r0, Consumer r1, String[] r2) {
        r0.lambda$runWithPermissions$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$JrH7COzs1n74R4yEvdR0iM7Rffs(BotWebViewContainer r0, WebRequestContext r1, BotWebViewProxy r2, String r3, ArrayList r4) {
        r0.lambda$onEventReceived$50(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$KSXQ2maG6p6gPJnCo9G2ihQTogw(BotWebViewContainer r0, WebRequestContext r1, String r2, TLRPC.Updates r3, TLRPC.TL_error r4) {
        r0.lambda$onEventReceived$57(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$LGRuy-ph4w60BFo6oRQxQ0YEEcc, reason: not valid java name */
    public static /* synthetic */ void m21956$r8$lambda$LGRuyph4w60BFo6oRQxQ0YEEcc(BotWebViewContainer r0, WebRequestContext r1, String r2, TLRPC.User r3, TLRPC.Updates r4, TLRPC.TL_error r5) {
        r0.lambda$onEventReceived$52(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$MWiOlblEDaV-Pj5-Sa8foj8qi98, reason: not valid java name */
    public static /* synthetic */ void m21957$r8$lambda$MWiOlblEDaVPj5Sa8foj8qi98(BotWebViewContainer r0, String r1, TLRPC.TL_inputInvoiceSlug r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$onEventReceived$14(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$MqavG0gbGiHEGu6PudB-aunDB54, reason: not valid java name */
    public static /* synthetic */ void m21958$r8$lambda$MqavG0gbGiHEGu6PudBaunDB54(BotWebViewContainer r0, String r1, int r2, MyWebView r3, WebRequestContext r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$onEventReceived$23(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$NztNqmje239wA8btEO2bgwzWicc(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$PVzAyGg1RLqSJpyp_NnI82Cdnm4(BotWebViewContainer r0, int r1, MyWebView r2, WebRequestContext r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$onEventReceived$21(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$PyuWomtfMrQMsnfwR5r-X-BmhO0, reason: not valid java name */
    public static /* synthetic */ void m21959$r8$lambda$PyuWomtfMrQMsnfwR5rXBmhO0(BotWebViewContainer r0) {
        r0.lambda$new$61();
    }

    public static /* synthetic */ void $r8$lambda$QfJ0rjH9pLPfBrn5ybx6IsBj5r4(BotWebViewContainer r0, WebRequestContext r1, Boolean r2, String r3) {
        r0.lambda$onEventReceived$33(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$SW-TVE8uKPxMUjyKC90X2Zgo6JM, reason: not valid java name */
    public static /* synthetic */ void m21960$r8$lambda$SWTVE8uKPxMUjyKC90X2Zgo6JM(BotWebViewContainer r0, AlertDialog r1, String r2, String r3, String r4, File r5) {
        r0.lambda$onEventReceived$38(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$UoVc4DYS1eoKDCTnpnGxYcHim-4, reason: not valid java name */
    public static /* synthetic */ void m21961$r8$lambda$UoVc4DYS1eoKDCTnpnGxYcHim4(BotWebViewProxy r0, ArrayList r1) {
        if (r0 == null) goto L9;
        BotWebViewContainer r02 = r0.container;
        if (r02 == null) goto L10;
        Delegate r03 = r02.delegate;
        if (r03 == null) goto L11;
        r03.onSharedTo(r1);
        return;
    L11:
        return;
    L10:
        return;
    }

    /* renamed from: $r8$lambda$VAwKPo-PAUrbx1DWtLBTjFPsMzY, reason: not valid java name */
    public static /* synthetic */ void m21962$r8$lambda$VAwKPoPAUrbx1DWtLBTjFPsMzY(BotWebViewContainer r0, int[] r1, File r2, AlertDialog r3, String r4, String r5, String r6) {
        r0.lambda$onEventReceived$35(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$WM1cflUWlwXm_J8ggOi2TM50gm4(BotWebViewContainer r0, WebRequestContext r1, String r2, TL_keyboard.KeyboardButton r3, TLRPC.TL_error r4) {
        r0.lambda$onEventReceived$59(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$WsDXKJRSSs-BX-7vtPIIg6jMH7M, reason: not valid java name */
    public static /* synthetic */ boolean m21963$r8$lambda$WsDXKJRSSsBX7vtPIIg6jMH7M(BotWebViewContainer r0, boolean[] r1, String r2, TL_keyboard.TL_buttonTypeRequestPeer r3, WebRequestContext r4, DialogsActivity r5, ArrayList r6, CharSequence r7, boolean r8, boolean r9, int r10, int r11, TopicsFragment r12) {
        return r0.lambda$onEventReceived$58(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
    }

    public static /* synthetic */ void $r8$lambda$Y3ua4OBDuREC2Q91YS_YpuBsFxU(BotWebViewContainer r0, Runnable r1, DialogInterface r2) {
        r0.lambda$showDialog$62(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$YaOp6gEY4IbSd4_Nzeq__GWre7s(BotWebViewContainer r0, boolean[] r1, WebRequestContext r2, String r3, DialogInterface r4) {
        r0.lambda$onEventReceived$56(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$YetVadcB9fdX70tFhwsqwfMmGmg(String[] r3, int r4, MyWebView r5, WebRequestContext r6) {
        JSONObject r0 = new JSONObject();     // Catch: Exception -> L4
        r0.put("status", r3[0]);     // Catch: Exception -> L4
        notifyEvent(r4, r5, r6, "write_access_requested", r0);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    /* renamed from: $r8$lambda$ZRjCv3V3Qax8gXmiIgn-qLA3x8w, reason: not valid java name */
    public static /* synthetic */ void m21964$r8$lambda$ZRjCv3V3Qax8gXmiIgnqLA3x8w(BotWebViewContainer r0, AtomicBoolean r1, WebRequestContext r2, DialogInterface r3) {
        r0.lambda$onEventReceived$12(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$_lVrYXF0nZPDbQHa_1TELn0pyAM(BotWebViewContainer r0, TLRPC.TL_messages_requestUrlAuth r1, String r2, String r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$onWebEventReceived$6(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$aMEF3aDzXzgqDn-1MUwGV8sBllE, reason: not valid java name */
    public static /* synthetic */ void m21965$r8$lambda$aMEF3aDzXzgqDn1MUwGV8sBllE(BotWebViewContainer r0, boolean r1, double r2, String r4, double r5) {
        r0.lambda$onEventReceived$51(r1, r2, r4, r5);
    }

    /* renamed from: $r8$lambda$b7gZsdIdfwuiB2Ah-9ZWu2gPUgo, reason: not valid java name */
    public static /* synthetic */ void m21966$r8$lambda$b7gZsdIdfwuiB2Ah9ZWu2gPUgo(BotWebViewContainer r0, WebRequestContext r1, PopupButton r2, AtomicBoolean r3, AlertDialog r4, int r5) {
        r0.lambda$onEventReceived$10(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$e-zt8Hi95Mzku_2MsScFPNSrfvw, reason: not valid java name */
    public static /* synthetic */ void m21967$r8$lambda$ezt8Hi95Mzku_2MsScFPNSrfvw(BotWebViewContainer r0, String[] r1, AlertDialog r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$onEventReceived$16(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$e6enTNZ47xTJMNJO9nplAg-gIFM, reason: not valid java name */
    public static /* synthetic */ void m21968$r8$lambda$e6enTNZ47xTJMNJO9nplAggIFM(BotWebViewContainer r0, WebRequestContext r1, Boolean r2) {
        r0.lambda$onEventReceived$39(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$fMzyHdeDmfgvULRkWJty0ZkYkxk(BotWebViewContainer r0, TLRPC.TL_error r1, String r2, TLRPC.TL_inputInvoiceSlug r3, TLObject r4) {
        r0.lambda$onEventReceived$13(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$hHXmpWTLgly3M84Bh7S8P-Il0mQ, reason: not valid java name */
    public static /* synthetic */ void m21969$r8$lambda$hHXmpWTLgly3M84Bh7S8PIl0mQ(BotWebViewContainer r0, int r1, MyWebView r2, WebRequestContext r3) {
        r0.lambda$onEventReceived$24(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$kMoqJtZZzJCsv_34FZMnkNRyF0E(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$kRmiOPggHs0ri1qY9Fv2bOXMbFI(BotWebViewContainer r0, WebRequestContext r1, String r2, String r3, Boolean r4) {
        r0.lambda$onEventReceived$45(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$kUPvh4nnoofKjwICwTePg40h3AE(BotWebViewContainer r0, String[] r1, AlertDialog r2, int r3) {
        r0.lambda$onEventReceived$17(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$kui9aoopQkD1JxPGdKz_aWMxegI(BotWebViewContainer r0, String[] r1, boolean r2, int r3, MyWebView r4, WebRequestContext r5, AlertDialog r6, int r7) {
        r0.lambda$onEventReceived$25(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$kzVCxsSbWX2YAXvSwYoSp0uUrug(BotWebViewContainer r0, WebRequestContext r1, String r2, TL_keyboard.TL_buttonTypeRequestPeer r3, TLRPC.User r4) {
        r0.lambda$onEventReceived$53(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$oGp1DOAVggxjGKoJroyWsj6ZzSI(BotWebViewContainer r0, WebRequestContext r1, PopupButton r2, AtomicBoolean r3, AlertDialog r4, int r5) {
        r0.lambda$onEventReceived$11(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$rkG1IvKf4jhz0YFmTMpEIA-Vvi0, reason: not valid java name */
    public static /* synthetic */ void m21970$r8$lambda$rkG1IvKf4jhz0YFmTMpEIAVvi0(MyWebView r1, WebRequestContext r2, String r3, JSONObject r4) {
        if (r1.bot == true) goto L5;
    L10:
        r1.evaluateJS("window.Telegram.WebView.receiveEvent('" + r3 + "', " + r4 + ");");
        return;
    L5:
        if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(r1) != null) goto L7;
    L8:
        FileLog.d("notifyEvent " + r3 + " dropped after document change");
        return;
    L7:
        if (MyWebView.m22008$$Nest$fgetbotWebViewContainer(r1).isRequestContextCurrent(r2) == true) goto L10;
        goto L8
    }

    /* renamed from: $r8$lambda$scuqqZoPDSoL06nEiRnY-fcJVM8, reason: not valid java name */
    public static /* synthetic */ void m21971$r8$lambda$scuqqZoPDSoL06nEiRnYfcJVM8(BotWebViewContainer r0, WebView r1, WebRequestContext r2, String r3, String r4) {
        r0.lambda$onBotWebMessage$8(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$tmWmT3eWq1tUXEB-3R05pu_8d0s, reason: not valid java name */
    public static /* synthetic */ void m21972$r8$lambda$tmWmT3eWq1tUXEB3R05pu_8d0s(BotWebViewContainer r0, String r1) {
        r0.lambda$loadUrl$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$u3erE8n02i9w5UrUHHieWXEu87A(BotWebViewContainer r0, Runnable[] r1, WebRequestContext r2, AlertDialog r3, int r4) {
        r0.lambda$onEventReceived$31(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$ueKMEashOQsH5Jm2BwK_4Z1JZ20(BotWebViewContainer r0, WebRequestContext r1, String r2, TLRPC.Updates r3, TLRPC.TL_error r4) {
        r0.lambda$onEventReceived$54(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$ui6KwASw-ccztVa-yJe1Os-VARQ, reason: not valid java name */
    public static /* synthetic */ void m21973$r8$lambda$ui6KwASwccztVayJe1OsVARQ(BotWebViewContainer r0, TLObject r1, WebRequestContext r2, String r3, String r4) {
        r0.lambda$onEventReceived$46(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$vBDEnGumSpvVLyyyzo2YGiz63Bk(BotWebViewContainer r0, WebRequestContext r1, Boolean r2, Boolean r3) {
        r0.lambda$onEventReceived$43(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$v_1LcymqnDRkLB1FjL3K34MvFcQ(BotWebViewContainer r0, TLObject r1, String[] r2, TLRPC.TL_error r3, AlertDialog r4) {
        r0.lambda$onEventReceived$15(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$wmobJxTSHzowRqeC-JvCc6breNc, reason: not valid java name */
    public static /* synthetic */ void m21974$r8$lambda$wmobJxTSHzowRqeCJvCc6breNc(BotWebViewContainer r0, WebRequestContext r1, Boolean r2, String r3) {
        r0.lambda$onEventReceived$29(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$xHlzo8XElH7c8w9-gsDckDJI5XE, reason: not valid java name */
    public static /* synthetic */ void m21975$r8$lambda$xHlzo8XElH7c8w9gsDckDJI5XE(BotWebViewContainer r0, WebRequestContext r1, JSONObject r2) {
        r0.lambda$onEventReceived$42(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$x_5pHPcuK_VVNirD7rBSB3842Iw(MyWebView r0, WebView r1, WebMessageCompat r2, Uri r3, boolean r4, JavaScriptReplyProxy r5) {
        BotWebViewContainer r42 = MyWebView.m22008$$Nest$fgetbotWebViewContainer(r0);
        if (r1 != r0) goto L7;
        if (r42 == null) goto L8;
        r42.onBotWebMessage(r1, r2, r3);
        return;
    L8:
        return;
    }

    public static /* synthetic */ void $r8$lambda$z8xOhAOilDkdM4BRRCxQdZxjT28(BotWebViewContainer r0, WebRequestContext r1, JSONObject r2) {
        r0.lambda$onEventReceived$44(r1, r2);
    }

    /* renamed from: -$$Nest$fgetbotUser, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.User m21976$$Nest$fgetbotUser(BotWebViewContainer r0) {
        return r0.botUser;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21977$$Nest$fgetcurrentAccount(BotWebViewContainer r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Delegate m21978$$Nest$fgetdelegate(BotWebViewContainer r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetdocumentGeneration, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m21979$$Nest$fgetdocumentGeneration(BotWebViewContainer r2) {
        return r2.documentGeneration;
    }

    /* renamed from: -$$Nest$fgetflickerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BackupImageView m21980$$Nest$fgetflickerView(BotWebViewContainer r0) {
        return r0.flickerView;
    }

    /* renamed from: -$$Nest$fgetisFlickeringCenter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21981$$Nest$fgetisFlickeringCenter(BotWebViewContainer r0) {
        return r0.isFlickeringCenter;
    }

    /* renamed from: -$$Nest$fgetlastClickMs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m21982$$Nest$fgetlastClickMs(BotWebViewContainer r2) {
        return r2.lastClickMs;
    }

    /* renamed from: -$$Nest$fgetlastQrText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m21983$$Nest$fgetlastQrText(BotWebViewContainer r0) {
        return r0.lastQrText;
    }

    /* renamed from: -$$Nest$fgetmFilePathCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueCallback m21984$$Nest$fgetmFilePathCallback(BotWebViewContainer r0) {
        return r0.mFilePathCallback;
    }

    /* renamed from: -$$Nest$fgetparentActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Activity m21985$$Nest$fgetparentActivity(BotWebViewContainer r0) {
        return r0.parentActivity;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m21986$$Nest$fgetresourcesProvider(BotWebViewContainer r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetwebViewProgressListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Consumer m21987$$Nest$fgetwebViewProgressListener(BotWebViewContainer r0) {
        return r0.webViewProgressListener;
    }

    /* renamed from: -$$Nest$fputdocumentGeneration, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21988$$Nest$fputdocumentGeneration(BotWebViewContainer r0, long r1) {
        r0.documentGeneration = r1;
    }

    /* renamed from: -$$Nest$fputhasQRPending, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21989$$Nest$fputhasQRPending(BotWebViewContainer r0, boolean r1) {
        r0.hasQRPending = r1;
    }

    /* renamed from: -$$Nest$fputhasUserPermissions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21990$$Nest$fputhasUserPermissions(BotWebViewContainer r0, boolean r1) {
        r0.hasUserPermissions = r1;
    }

    /* renamed from: -$$Nest$fputlastClickMs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21991$$Nest$fputlastClickMs(BotWebViewContainer r0, long r1) {
        r0.lastClickMs = r1;
    }

    /* renamed from: -$$Nest$fputmFilePathCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21992$$Nest$fputmFilePathCallback(BotWebViewContainer r0, ValueCallback r1) {
        r0.mFilePathCallback = r1;
    }

    /* renamed from: -$$Nest$mcreateRequestContext, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WebRequestContext m21993$$Nest$mcreateRequestContext(BotWebViewContainer r0) {
        return r0.createRequestContext();
    }

    /* renamed from: -$$Nest$misTrustedDocumentCurrent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21994$$Nest$misTrustedDocumentCurrent(BotWebViewContainer r0) {
        return r0.isTrustedDocumentCurrent();
    }

    /* renamed from: -$$Nest$misVerifyingAge, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21995$$Nest$misVerifyingAge(BotWebViewContainer r0) {
        return r0.isVerifyingAge();
    }

    /* renamed from: -$$Nest$mnotifyEvent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21996$$Nest$mnotifyEvent(BotWebViewContainer r0, WebRequestContext r1, String r2, JSONObject r3) {
        r0.notifyEvent(r1, r2, r3);
    }

    /* renamed from: -$$Nest$monEventReceived, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21997$$Nest$monEventReceived(BotWebViewContainer r0, BotWebViewProxy r1, WebRequestContext r2, String r3, String r4) {
        r0.onEventReceived(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$monOpenUri, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21998$$Nest$monOpenUri(BotWebViewContainer r0, Uri r1) {
        r0.onOpenUri(r1);
    }

    /* renamed from: -$$Nest$monWebEventReceived, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21999$$Nest$monWebEventReceived(BotWebViewContainer r0, String r1, String r2) {
        r0.onWebEventReceived(r1, r2);
    }

    /* renamed from: -$$Nest$mopenQrScanActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22000$$Nest$mopenQrScanActivity(BotWebViewContainer r0) {
        r0.openQrScanActivity();
    }

    /* renamed from: -$$Nest$mrunWithPermissions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22001$$Nest$mrunWithPermissions(BotWebViewContainer r0, String[] r1, Consumer r2) {
        r0.runWithPermissions(r1, r2);
    }

    /* renamed from: -$$Nest$sfgettags, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m22002$$Nest$sfgettags() {
        return tags;
    }

    /* renamed from: -$$Nest$sfputtags, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22003$$Nest$sfputtags(int r0) {
        tags = r0;
    }

    /* renamed from: -$$Nest$smtonsite2magic, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m22004$$Nest$smtonsite2magic(String r0) {
        return tonsite2magic(r0);
    }

    static {
    }

    public BotWebViewContainer(Context r6, Theme.ResourcesProvider r7, int r8, boolean r9) {
        super(r6);
        CellFlickerDrawable r0 = new CellFlickerDrawable();
        this.flickerDrawable = r0;
        int r1 = Theme.key_featuredStickers_addButton;
        this.lastButtonColor = getColor(r1);
        int r2 = Theme.key_featuredStickers_buttonText;
        this.lastButtonTextColor = getColor(r2);
        this.lastButtonText = _UrlKt.FRAGMENT_ENCODE_SET;
        this.lastSecondaryButtonColor = getColor(r1);
        this.lastSecondaryButtonTextColor = getColor(r2);
        this.lastSecondaryButtonText = _UrlKt.FRAGMENT_ENCODE_SET;
        this.lastSecondaryButtonPosition = _UrlKt.FRAGMENT_ENCODE_SET;
        this.currentAccount = UserConfig.selectedAccount;
        this.forceHeight = -1;
        this.lastInsets = new Rect(0, 0, 0, 0);
        this.lastInsetsTopMargin = 0;
        this.notifyLocationChecked = new BotWebViewContainer$$ExternalSyntheticLambda1(this);
        this.lastDialogType = -1;
        this.shownDialogsCount = 0;
        int r22 = tags;
        tags = r22 + 1;
        this.tag = r22;
        this.bot = r9;
        this.resourcesProvider = r7;
        d("created new webview container");
        if ((r6 instanceof Activity) == false) goto L5;
        this.parentActivity = (Activity) r6;
    L5:
        r0.drawFrame = false;
        r0.setColors(r8, 153, Opcodes.SUB_DOUBLE_2ADDR);
        AnonymousClass1 r72 = new AnonymousClass1(this, r6);
        this.flickerView = r72;
        int r92 = getColor(Theme.key_bot_loadingIcon);
        this.flickerViewColor = r92;
        r72.setColorFilter(new PorterDuffColorFilter(r92, PorterDuff.Mode.SRC_IN));
        this.flickerView.getImageReceiver().setAspectFit(true);
        addView(this.flickerView, LayoutHelper.createFrame(-1, -2, 48));
        TextView r73 = new TextView(r6);
        this.webViewNotAvailableText = r73;
        r73.setText(LocaleController.getString(R.string.BotWebViewNotAvailablePlaceholder));
        this.webViewNotAvailableText.setTextColor(getColor(Theme.key_windowBackgroundWhiteGrayText));
        this.webViewNotAvailableText.setTextSize(1, 15.0f);
        this.webViewNotAvailableText.setGravity(17);
        this.webViewNotAvailableText.setVisibility(8);
        int r62 = AndroidUtilities.dp(16.0f);
        this.webViewNotAvailableText.setPadding(r62, r62, r62, r62);
        addView(this.webViewNotAvailableText, LayoutHelper.createFrame(-1, -2, 17));
        setFocusable(false);
    }

    private JSONObject buildThemeParams() {
        JSONObject r2 = BotWebViewSheet.makeThemeParams(this.resourcesProvider, true);     // Catch: Exception -> L6
        if (r2 == null) goto L9;
        return new JSONObject().put("theme_params", r2);
    L9:
        return new JSONObject();
    L6:
        e = move-exception;
        FileLog.e(e);
        goto L9
    }

    private static String capitalizeFirst(String r3) {
        if (r3 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        if (r3.length() > 1) goto L10;
        return r3.toUpperCase();
    L10:
        return r3.substring(0, 1).toUpperCase() + r3.substring(1).toLowerCase();
    }

    private boolean checkPermissions(String[] r6) {
        int r0 = r6.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        if (getContext().checkSelfPermission(r6[r2]) != 0) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return false;
    L8:
        return true;
    }

    private void clearStorageKey(WebRequestContext r4, BotStorage r5, String r6, String r7, String r8) {
        if (r5 != null) goto L5;
        return;
    L5:
        if (this.botUser == null) goto L25;
        String r62 = new JSONObject(r6).getString("req_id");     // Catch: Exception -> L14
        r5.clear();     // Catch: RuntimeException -> L11
        notifyEvent(r4, r7, obj("req_id", r62));
        return;
    L11:
        e = move-exception;
        notifyEvent(r4, r8, obj("req_id", r62, "error", e.getMessage()));
        return;
    L14:
        e = move-exception;
        FileLog.e(e);
        if (TextUtils.isEmpty(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L24;
        notifyEvent(r4, r8, obj("req_id", _UrlKt.FRAGMENT_ENCODE_SET, "error", "UNKNOWN_ERROR"));
        return;
    L24:
        return;
    }

    private void createBiometry() {
        if (this.botUser != null) goto L5;
        return;
    L5:
        BotBiometry r0 = this.biometry;
        if (r0 != null) goto L9;
        this.biometry = BotBiometry.get(getContext(), this.currentAccount, this.botUser.id);
        return;
    L9:
        r0.load();
    }

    private WebRequestContext createRequestContext() {
        return new WebRequestContext(this, this.webView, this.documentGeneration, this.restrictBridgeToOrigin, this.trustedOrigin, null);
    }

    private void error(String r2) {
        BulletinFactory.of(this, this.resourcesProvider).createSimpleBulletin(R.raw.error, r2).show();
    }

    private int getColor(int r1) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L7;
        return r0.getColor(r1);
    L7:
        return Theme.getColor(r1);
    }

    public static int getMainButtonRippleColor(int r4) {
        if (ColorUtils.calculateLuminance(r4) < 0.30000001192092896d) goto L6;
        return 301989888;
    L6:
        return 385875967;
    }

    public static Drawable getMainButtonRippleDrawable(int r1) {
        return Theme.createSelectorWithBackgroundDrawable(r1, getMainButtonRippleColor(r1));
    }

    public static String getOriginHost(String r4) {
        if (r4 != null) goto L5;
    L24:
        return null;
    L5:
        if (r4.isEmpty() == true) goto L24;
        Uri r42 = Uri.parse(r4);
        String r1 = r42.getScheme();
        String r2 = r42.getHost();
        int r43 = r42.getPort();
        if (r1 == null) goto L24;
        if (r2 == null) goto L24;
        StringBuilder r0 = new StringBuilder();
        r0.append(r1);
        r0.append("://");
        r0.append(r2);
        if (r43 == (-1)) goto L23;
        if (r1.equalsIgnoreCase("http") == false) goto L18;
        if (r43 == 80) goto L23;
    L18:
        if (r1.equalsIgnoreCase("https") == true) goto L20;
    L21:
        r0.append(":");
        r0.append(r43);
        goto L23
    L20:
        if (r43 != 443) goto L21;
    L23:
        return r0.toString();
    }

    private void getStorageKey(WebRequestContext r11, BotStorage r12, String r13, String r14, String r15) {
        if (r12 != null) goto L5;
        return;
    L5:
        if (this.botUser == null) goto L38;
        JSONObject r3 = new JSONObject(r13);     // Catch: Exception -> L26
        String r5 = r3.getString("req_id");     // Catch: Exception -> L26
        String r132 = r3.optString(PluginsConstants.Settings.KEY);     // Catch: Exception -> L24
        if (r132 != null) goto L31;
        notifyEvent(r11, r15, obj("req_id", r5, "error", "KEY_INVALID"));
        return;
    L31:
        Pair<String, Boolean> r133 = r12.getKey(r132);     // Catch: RuntimeException -> L18
        if (r12.secured == false) goto L20;
        Object r7 = r133.first;     // Catch: RuntimeException -> L18
        if (r7 != null) goto L20;
        notifyEvent(r11, r14, obj("req_id", r5, "value", r7, "can_restore", r133.second));     // Catch: RuntimeException -> L18
        return;
    L20:
        notifyEvent(r11, r14, obj("req_id", r5, "value", r133.first));     // Catch: RuntimeException -> L18
        return;
    L18:
        e = move-exception;
        notifyEvent(r11, r15, obj("req_id", r5, "error", e.getMessage()));
        return;
    L24:
        notifyEvent(r11, r15, obj("req_id", r5, "error", "KEY_INVALID"));
        return;
    L26:
        e = move-exception;
        FileLog.e(e);
        if (TextUtils.isEmpty(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L37;
        notifyEvent(r11, r15, obj("req_id", _UrlKt.FRAGMENT_ENCODE_SET, "error", "UNKNOWN_ERROR"));
        return;
    L37:
        return;
    }

    private boolean ignoreDialog(int r8) {
        if (this.currentDialog == null) goto L6;
        return true;
    L6:
        if (this.blockedDialogsUntil <= 0) goto L11;
        if (System.currentTimeMillis() >= this.blockedDialogsUntil) goto L11;
        return true;
    L11:
        if (this.lastDialogType == r8) goto L13;
    L16:
        return false;
    L13:
        if (this.shownDialogsCount <= 3) goto L16;
        this.blockedDialogsUntil = System.currentTimeMillis() + 3000;
        this.shownDialogsCount = 0;
        return true;
    }

    private boolean isRequestContextCurrent(WebRequestContext r5) {
        if (r5 != null) goto L4;
        return false;
    L4:
        if (WebRequestContext.m22040$$Nest$fgetowner(r5) == this) goto L6;
        return false;
    L6:
        if (WebRequestContext.m22042$$Nest$fgetwebView(r5) == this.webView) goto L8;
        return false;
    L8:
        if (WebRequestContext.m22038$$Nest$fgetdocumentGeneration(r5) == this.documentGeneration) goto L10;
        return false;
    L10:
        if (WebRequestContext.m22041$$Nest$fgetrestrictToOrigin(r5) == this.restrictBridgeToOrigin) goto L12;
        return false;
    L12:
        if (WebRequestContext.m22041$$Nest$fgetrestrictToOrigin(r5) == true) goto L14;
        return true;
    L14:
        if (TextUtils.isEmpty(WebRequestContext.m22039$$Nest$fgetorigin(r5)) == false) goto L16;
        return false;
    L16:
        if (TextUtils.equals(WebRequestContext.m22039$$Nest$fgetorigin(r5), this.trustedOrigin) == true) goto L18;
        return false;
    L18:
        if (TextUtils.equals(WebRequestContext.m22039$$Nest$fgetorigin(r5), getOriginHost()) == false) goto L30;
        return true;
    L30:
        return false;
    }

    public static boolean isTonsite(Uri r3) {
        if ("tonsite".equals(r3.getScheme()) == false) goto L5;
        return true;
    L5:
        String r0 = r3.getAuthority();
        if (r0 == null) goto L8;
    L10:
        if (r0 != null) goto L12;
        return false;
    L12:
        if (r0.endsWith(".ton") == false) goto L14;
    L15:
        return true;
    L14:
        if (r0.endsWith(".adnl") == true) goto L15;
        return false;
    L8:
        if (r3.getScheme() != null) goto L10;
        r0 = Uri.parse("http://" + r3.toString()).getAuthority();
        goto L10
    }

    public static boolean isTonsite(String r0) {
        if (r0 != null) goto L4;
        return false;
    L4:
        if (isTonsite(Uri.parse(r0)) == false) goto L9;
        return true;
    L9:
        return false;
    }

    private boolean isTrustedDocumentCurrent() {
        if (this.webView != null) goto L6;
        return false;
    L6:
        if (this.restrictBridgeToOrigin == true) goto L8;
        return true;
    L8:
        if (TextUtils.isEmpty(this.trustedOrigin) == false) goto L10;
    L12:
        return false;
    L10:
        if (TextUtils.equals(this.trustedOrigin, getOriginHost()) == false) goto L12;
        return true;
    }

    private boolean isVerifyingAge() {
        if (this.onVerifiedAge == null) goto L6;
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$evaluateJs$3(boolean r1, String r2) {
        if (r1 == false) goto L4;
        checkCreateWebView();
    L4:
        MyWebView r0 = this.webView;
        if (r0 != null) goto L7;
        return;
    L7:
        r0.evaluateJS(r2);
    }

    private /* synthetic */ void lambda$loadUrl$2(String r4) {
        this.isPageLoaded = false;
        this.lastClickMs = 0;
        this.hasUserPermissions = false;
        this.mUrl = r4;
        checkCreateWebView();
        MyWebView r0 = this.webView;
        if (r0 == null) goto L5;
        r0.onResume();
        this.webView.loadUrl(r4);
    L5:
        updateKeyboardFocusable();
    }

    private /* synthetic */ void lambda$new$61() {
        notifyEvent(this.locationRequestContext, "location_checked", this.location.checkObject());
    }

    private /* synthetic */ void lambda$onBotWebMessage$8(WebView r2, WebRequestContext r3, String r4, String r5) {
        if (r2 == this.webView) goto L5;
    L9:
        d("onBotWebMessage ignored after document change");
        return;
    L5:
        if (isRequestContextCurrent(r3) == false) goto L9;
        onEventReceived(this.botWebViewProxy, r3, r4, r5);
    }

    private /* synthetic */ void lambda$onEventReceived$10(WebRequestContext r2, PopupButton r3, AtomicBoolean r4, AlertDialog r5, int r6) {
        r5.dismiss();
        this.lastClickMs = System.currentTimeMillis();     // Catch: JSONException -> L5
        notifyEvent(r2, "popup_closed", new JSONObject().put("button_id", r3.id));     // Catch: JSONException -> L5
        r4.set(true);     // Catch: JSONException -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$onEventReceived$11(WebRequestContext r2, PopupButton r3, AtomicBoolean r4, AlertDialog r5, int r6) {
        r5.dismiss();
        this.lastClickMs = System.currentTimeMillis();     // Catch: JSONException -> L5
        notifyEvent(r2, "popup_closed", new JSONObject().put("button_id", r3.id));     // Catch: JSONException -> L5
        r4.set(true);     // Catch: JSONException -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$onEventReceived$12(AtomicBoolean r1, WebRequestContext r2, DialogInterface r3) {
        if (r1.get() == true) goto L5;
        notifyEvent(r2, "popup_closed", new JSONObject());
    L5:
        this.currentDialog = null;
        this.lastDialogClosed = System.currentTimeMillis();
    }

    private /* synthetic */ void lambda$onEventReceived$13(TLRPC.TL_error r1, String r2, TLRPC.TL_inputInvoiceSlug r3, TLObject r4) {
        if (r1 == null) goto L5;
        onInvoiceStatusUpdate(r2, "failed");
        return;
    L5:
        this.delegate.onWebAppOpenInvoice(r3, r2, r4);
    }

    private /* synthetic */ void lambda$onEventReceived$14(String r7, TLRPC.TL_inputInvoiceSlug r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new BotWebViewContainer$$ExternalSyntheticLambda39(this, r10, r7, r8, r9));
    }

    private /* synthetic */ void lambda$onEventReceived$15(TLObject r3, String[] r4, TLRPC.TL_error r5, AlertDialog r6) {
        if (r3 == null) goto L6;
        r4[0] = "allowed";
        if ((r3 instanceof TLRPC.Updates) == false) goto L6;
        MessagesController.getInstance(this.currentAccount).processUpdates((TLRPC.Updates) r3, false);
    L6:
        if (r5 == null) goto L8;
        unknownError(r5.text);
    L8:
        r6.dismiss();
    }

    private /* synthetic */ void lambda$onEventReceived$16(String[] r7, AlertDialog r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new BotWebViewContainer$$ExternalSyntheticLambda62(this, r9, r7, r10, r8));
    }

    private /* synthetic */ void lambda$onEventReceived$17(String[] r3, AlertDialog r4, int r5) {
        TL_bots.allowSendMessage r52 = new TL_bots.allowSendMessage();
        r52.bot = MessagesController.getInstance(this.currentAccount).getInputUser(this.botUser);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r52, new BotWebViewContainer$$ExternalSyntheticLambda61(this, r3, r4));
    }

    private /* synthetic */ void lambda$onEventReceived$20(TLObject r3, int r4, MyWebView r5, WebRequestContext r6, TLRPC.TL_error r7) {
        if ((r3 instanceof TLRPC.TL_boolTrue) == true) goto L14;
        if (r7 == null) goto L12;
        unknownError(r7.text);
        return;
    L12:
        String[] r32 = {"cancelled"};
        showDialog(3, new AlertDialog.Builder(getContext()).setTitle(LocaleController.getString(R.string.BotWebViewRequestWriteTitle)).setMessage(LocaleController.getString(R.string.BotWebViewRequestWriteMessage)).setPositiveButton(LocaleController.getString(R.string.BotWebViewRequestAllow), new BotWebViewContainer$$ExternalSyntheticLambda57(this, r32)).setNegativeButton(LocaleController.getString(R.string.BotWebViewRequestDontAllow), new BotWebViewContainer$$ExternalSyntheticLambda58()).create(), new BotWebViewContainer$$ExternalSyntheticLambda59(r32, r4, r5, r6));
        return;
    L14:
        JSONObject r2 = new JSONObject();     // Catch: Exception -> L6
        r2.put("status", "allowed");     // Catch: Exception -> L6
        notifyEvent(r4, r5, r6, "write_access_requested", r2);     // Catch: Exception -> L6
        return;
    L6:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$onEventReceived$21(int r8, MyWebView r9, WebRequestContext r10, TLObject r11, TLRPC.TL_error r12) {
        AndroidUtilities.runOnUIThread(new BotWebViewContainer$$ExternalSyntheticLambda47(this, r11, r8, r9, r10, r12));
    }

    private /* synthetic */ void lambda$onEventReceived$22(String r3, TLObject r4, TLRPC.TL_error r5, int r6, MyWebView r7, WebRequestContext r8) {
        JSONObject r0 = new JSONObject();     // Catch: Exception -> L5
        r0.put("req_id", r3);     // Catch: Exception -> L5
        if ((r4 instanceof TLRPC.TL_dataJSON) == false) goto L7;
        r0.put("result", new JSONTokener(((TLRPC.TL_dataJSON) r4).data).nextValue());     // Catch: Exception -> L5
    L9:
        notifyEvent(r6, r7, r8, "custom_method_invoked", r0);     // Catch: Exception -> L5
        return;
    L7:
        if (r5 == null) goto L9;
        r0.put("error", r5.text);     // Catch: Exception -> L5
    L5:
        e = move-exception;
        FileLog.e(e);
        unknownError();
    }

    private /* synthetic */ void lambda$onEventReceived$23(String r9, int r10, MyWebView r11, WebRequestContext r12, TLObject r13, TLRPC.TL_error r14) {
        AndroidUtilities.runOnUIThread(new BotWebViewContainer$$ExternalSyntheticLambda48(this, r9, r13, r14, r10, r11, r12));
    }

    private /* synthetic */ void lambda$onEventReceived$24(int r13, MyWebView r14, WebRequestContext r15) {
        SendMessagesHelper.getInstance(this.currentAccount).sendMessage(SendMessagesHelper.SendMessageParams.of(UserConfig.getInstance(this.currentAccount).getCurrentUser(), this.botUser.id, null, null, null, null, true, 0, 0));
        JSONObject r12 = new JSONObject();     // Catch: Exception -> L5
        r12.put("status", "sent");     // Catch: Exception -> L5
        notifyEvent(r13, r14, r15, "phone_requested", r12);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$onEventReceived$25(String[] r17, boolean r18, int r19, MyWebView r20, WebRequestContext r21, AlertDialog r22, int r23) {
        r17[0] = null;
        r22.dismiss();
        int r4 = this.currentAccount;
        if (r18 == false) goto L6;
        MessagesController.getInstance(r4).unblockPeer(this.botUser.id, new BotWebViewContainer$$ExternalSyntheticLambda50(this, r19, r20, r21));
        return;
    L6:
        SendMessagesHelper.getInstance(r4).sendMessage(SendMessagesHelper.SendMessageParams.of(UserConfig.getInstance(this.currentAccount).getCurrentUser(), this.botUser.id, null, null, null, null, true, 0, 0));
        JSONObject r0 = new JSONObject();     // Catch: Exception -> L9
        r0.put("status", "sent");     // Catch: Exception -> L9
        notifyEvent(r19, r20, r21, "phone_requested", r0);     // Catch: Exception -> L9
        return;
    L9:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$onEventReceived$28(WebRequestContext r3) {
        BotBiometry r0 = this.biometry;
        r0.access_requested = true;
        r0.save();
        notifyBiometryReceived(r3);
    }

    private /* synthetic */ void lambda$onEventReceived$29(WebRequestContext r1, Boolean r2, String r3) {
        if (r2.booleanValue() == false) goto L5;
        BotBiometry r22 = this.biometry;
        r22.access_granted = true;
        r22.save();
    L5:
        notifyBiometryReceived(r1);
    }

    private /* synthetic */ void lambda$onEventReceived$30(Runnable[] r2, WebRequestContext r3, AlertDialog r4, int r5) {
        if (r2[0] == null) goto L5;
        r2[0] = null;
    L5:
        BotBiometry r22 = this.biometry;
        r22.access_requested = true;
        r22.save();
        this.biometry.requestToken(null, new BotWebViewContainer$$ExternalSyntheticLambda41(this, r3));
    }

    private /* synthetic */ void lambda$onEventReceived$31(Runnable[] r1, WebRequestContext r2, AlertDialog r3, int r4) {
        if (r1[0] == null) goto L5;
        r1[0] = null;
    L5:
        BotBiometry r12 = this.biometry;
        r12.access_requested = true;
        r12.disabled = true;
        r12.save();
        notifyBiometryReceived(r2);
    }

    private /* synthetic */ void lambda$onEventReceived$33(WebRequestContext r3, Boolean r4, String r5) {
        if (r4.booleanValue() == false) goto L14;
        this.biometry.access_granted = true;
    L14:
        JSONObject r0 = new JSONObject();     // Catch: Exception -> L11
        if (r4.booleanValue() == false) goto L8;
        String r42 = "authorized";
    L9:
        r0.put("status", r42);     // Catch: Exception -> L11
        r0.put("token", r5);     // Catch: Exception -> L11
        notifyEvent(r3, "biometry_auth_requested", r0);     // Catch: Exception -> L11
        return;
    L8:
        r42 = "failed";
    L11:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$onEventReceived$34(String r3, WebRequestContext r4, Boolean r5) {
        JSONObject r0 = new JSONObject();     // Catch: Exception -> L11
        if (r5.booleanValue() == true) goto L5;
        String r32 = "failed";
    L9:
        r0.put("status", r32);     // Catch: Exception -> L11
        notifyEvent(r4, "biometry_token_updated", r0);     // Catch: Exception -> L11
        return;
    L5:
        if (TextUtils.isEmpty(r3) == false) goto L7;
        r32 = "removed";
        goto L9
    L7:
        r32 = "updated";
    L11:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$onEventReceived$35(int[] r25, File r26, AlertDialog r27, String r28, String r29, String r30) {
        if (r25[4] <= 0) goto L23;
        int r3 = r25[1];
        int r4 = r25[2];
        if (r3 <= AndroidUtilities.getPhotoSize()) goto L7;
        int r15 = AndroidUtilities.getPhotoSize();
    L9:
        if (r4 <= AndroidUtilities.getPhotoSize()) goto L11;
        int r16 = AndroidUtilities.getPhotoSize();
    L12:
        File r0 = StoryEntry.makeCacheFile(UserConfig.selectedAccount, "jpg");
        StoryRecorder.SourceView r1 = null;
        AnimatedFileDrawable r2 = new AnimatedFileDrawable(r26, true, 0, 0, null, null, null, 0, UserConfig.selectedAccount, true, r15, r16, null);
        Bitmap r42 = r2.getFirstFrame(null);
        r2.recycle();
        if (r42 != null) goto L48;
    L18:
        File r22 = r0;
    L19:
        if (r22 != null) goto L21;
        String r23 = null;
    L22:
        StoryEntry r02 = StoryEntry.fromVideoShoot(r26, r23, r25[4]);
        r02.width = r3;
        r02.height = r4;
        r02.setupMatrix();
    L25:
        if (r02.width <= 0) goto L46;
        if (r02.height <= 0) goto L46;
        if (r28 == null) goto L33;
        r02.caption = r28;
    L33:
        if (TextUtils.isEmpty(r29) == true) goto L43;
        BotWebViewContainer r24 = this;
        if (UserConfig.getInstance(r24.currentAccount).isPremium() == true) goto L37;
    L44:
        StoryRecorder.getInstance(r24.parentActivity, UserConfig.selectedAccount).openRepost(r1, r02);
        r27.dismissUnless(500);
        return;
    L37:
        if (r02.mediaEntities != null) goto L39;
        r02.mediaEntities = new ArrayList();
    L39:
        VideoEditedInfo.MediaEntity r5 = new VideoEditedInfo.MediaEntity();
        r5.type = 7;
        r5.subType = -1;
        r5.color = -1;
        LinkPreview.WebPagePreview r6 = new LinkPreview.WebPagePreview();
        r5.linkSettings = r6;
        r6.url = r29;
        if (r30 == null) goto L42;
        r6.flags |= 2;
        r6.name = r30;
    L42:
        r02.mediaEntities.add(r5);
        goto L44
    L43:
        r24 = this;
    L46:
        r27.dismissUnless(500);
        return;
    L21:
        r23 = r22.getAbsolutePath();
        goto L22
    L48:
        r42.compress(Bitmap.CompressFormat.JPEG, 80, new FileOutputStream(r0));     // Catch: Exception -> L16
    L16:
        e = move-exception;
        FileLog.e(e);
        r22 = null;
        goto L19
    L11:
        r16 = r4;
        goto L12
    L7:
        r15 = r3;
        goto L9
    L23:
        r1 = null;
        r02 = StoryEntry.fromPhotoShoot(r26, ((Integer) AndroidUtilities.getImageOrientation(r26).first).intValue());
        goto L25
    }

    private /* synthetic */ void lambda$onEventReceived$37(File r10, AlertDialog r11, String r12, String r13, String r14) {
        if (r10 != null) goto L5;
        r11.dismissUnless(500);
        return;
    L5:
        int[] r3 = new int[11];
        BotWebViewContainer$$ExternalSyntheticLambda52 r1 = new BotWebViewContainer$$ExternalSyntheticLambda52(this, r3, r10, r11, r12, r13, r14);
        Utilities.globalQueue.postRunnable(new BotWebViewContainer$$ExternalSyntheticLambda53(r10, r3, r1));
    }

    private /* synthetic */ void lambda$onEventReceived$38(AlertDialog r8, String r9, String r10, String r11, File r12) {
        AndroidUtilities.runOnUIThread(new BotWebViewContainer$$ExternalSyntheticLambda38(this, r12, r8, r9, r10, r11));
    }

    private /* synthetic */ void lambda$onEventReceived$39(WebRequestContext r2, Boolean r3) {
        if (r3.booleanValue() == false) goto L6;
        notifyEvent(r2, "home_screen_added", null);
        return;
    L6:
        notifyEvent(r2, "home_screen_failed", obj("error", "UNSUPPORTED"));
    }

    private /* synthetic */ void lambda$onEventReceived$40(WebRequestContext r2, String r3, TLRPC.Document r4) {
        if (r3 != null) goto L7;
        notifyEvent(r2, "emoji_status_set", null);
        Delegate r1 = this.delegate;
        if (r1 == null) goto L9;
        r1.onEmojiStatusSet(r4);
        return;
    L9:
        return;
    L7:
        notifyEvent(r2, "emoji_status_failed", obj("error", r3));
    }

    private /* synthetic */ void lambda$onEventReceived$41(WebRequestContext r1, Boolean r2, String r3) {
        notifyEmojiStatusAccess(r1, r3);
        if (r2.booleanValue() == true) goto L5;
        return;
    L5:
        if ("allowed".equalsIgnoreCase(r3) == false) goto L11;
        Delegate r0 = this.delegate;
        if (r0 == null) goto L12;
        r0.onEmojiStatusGranted(true);
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$onEventReceived$42(WebRequestContext r2, JSONObject r3) {
        notifyEvent(r2, "location_requested", r3);
    }

    private /* synthetic */ void lambda$onEventReceived$43(WebRequestContext r2, Boolean r3, Boolean r4) {
        if (this.delegate != null) goto L5;
    L7:
        this.location.requestObject(new BotWebViewContainer$$ExternalSyntheticLambda34(this, r2));
        return;
    L5:
        if (r3.booleanValue() == false) goto L7;
        this.delegate.onLocationGranted(r4.booleanValue());
        goto L7
    }

    private /* synthetic */ void lambda$onEventReceived$44(WebRequestContext r2, JSONObject r3) {
        notifyEvent(r2, "location_requested", r3);
    }

    private /* synthetic */ void lambda$onEventReceived$45(WebRequestContext r3, String r4, String r5, Boolean r6) {
        if (r6.booleanValue() == true) goto L6;
        notifyEvent(r3, "file_download_requested", obj("status", "cancelled"));
        return;
    L6:
        this.downloads.download(r4, r5);
        notifyEvent(r3, "file_download_requested", obj("status", "downloading"));
    }

    private /* synthetic */ void lambda$onEventReceived$46(TLObject r3, WebRequestContext r4, String r5, String r6) {
        if ((r3 instanceof TLRPC.TL_boolTrue) == true) goto L6;
        notifyEvent(r4, "file_download_requested", obj("status", "cancelled"));
        return;
    L6:
        BotDownloads.showAlert(getContext(), r5, r6, UserObject.getUserName(this.botUser), new BotWebViewContainer$$ExternalSyntheticLambda60(this, r4, r5, r6));
    }

    private /* synthetic */ void lambda$onEventReceived$47(WebRequestContext r2, String r3, String r4, TLObject r5, TLRPC.TL_error r6) {
        AndroidUtilities.runOnUIThread(new BotWebViewContainer$$ExternalSyntheticLambda42(this, r5, r2, r3, r4));
    }

    private /* synthetic */ void lambda$onEventReceived$48() {
        Delegate r0 = this.delegate;
        if (r0 == null) goto L5;
        r0.onCloseToTabs();
    L5:
        LaunchActivity.dismissAllWeb();
    }

    private /* synthetic */ void lambda$onEventReceived$50(WebRequestContext r2, BotWebViewProxy r3, String r4, ArrayList r5) {
        if (TextUtils.isEmpty(r4) == false) goto L9;
        notifyEvent(r2, "prepared_message_sent", null);
        Delegate r1 = this.delegate;
        if (r1 == null) goto L7;
        r1.onOpenBackFromTabs();
    L7:
        AndroidUtilities.runOnUIThread(new BotWebViewContainer$$ExternalSyntheticLambda35(r3, r5), 500);
        return;
    L9:
        notifyEvent(r2, "prepared_message_failed", obj("error", r4));
    }

    private /* synthetic */ void lambda$onEventReceived$51(boolean r1, double r2, String r4, double r5) {
        this.onVerifiedAge.run(Boolean.valueOf(r1), Double.valueOf(r2), r4, Double.valueOf(r5));
    }

    private /* synthetic */ void lambda$onEventReceived$52(WebRequestContext r7, String r8, TLRPC.User r9, TLRPC.Updates r10, TLRPC.TL_error r11) {
        if (r10 == null) goto L11;
        MessagesController.getInstance(this.currentAccount).processUpdates(r10, false);
        notifyEvent(r7, "requested_chat_sent", obj("req_id", r8));
        long r4 = this.botUser.id;
        Bundle r2 = new Bundle();
        r2.putLong("user_id", r9.id);
        AnonymousClass6 r0 = new AnonymousClass6(this, r2, r9, r4);
        BaseFragment r6 = LaunchActivity.getSafeLastFragment();
        if (r6 == null) goto L7;
        r6.presentFragment(r0);
    L7:
        Delegate r62 = this.delegate;
        if (r62 == null) goto L17;
        r62.onCloseToTabs();
        return;
    L17:
        return;
    L11:
        Theme.ResourcesProvider r63 = this.resourcesProvider;
        if (r11 == null) goto L15;
        BulletinFactory.of(this, r63).showForError(r11);
        notifyEvent(r7, "requested_chat_failed", obj("req_id", r8));
        return;
    L15:
        BulletinFactory.of(this, r63).showForError("UNKNOWN_BUTTON");
        notifyEvent(r7, "requested_chat_failed", obj("req_id", r8));
    }

    private /* synthetic */ void lambda$onEventReceived$53(WebRequestContext r5, String r6, TL_keyboard.TL_buttonTypeRequestPeer r7, TLRPC.User r8) {
        if (r8 != null) goto L6;
        notifyEvent(r5, "requested_chat_failed", obj("req_id", r6));
        return;
    L6:
        TLRPC.TL_messages_sendBotRequestedPeer r1 = new TLRPC.TL_messages_sendBotRequestedPeer();
        r1.peer = MessagesController.getInputPeer(this.botUser);
        r1.webapp_req_id = r6;
        r1.button_id = r7.button_id;
        r1.requested_peers.add(MessagesController.getInputPeer(r8));
        ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r1, new AiTonesController$$ExternalSyntheticLambda0(), new BotWebViewContainer$$ExternalSyntheticLambda55(this, r5, r6, r8));
        notifyEvent(r5, "requested_chat_sent", obj("req_id", r6));
    }

    private /* synthetic */ void lambda$onEventReceived$54(WebRequestContext r3, String r4, TLRPC.Updates r5, TLRPC.TL_error r6) {
        if (r5 == null) goto L6;
        MessagesController.getInstance(this.currentAccount).processUpdates(r5, false);
        notifyEvent(r3, "requested_chat_sent", obj("req_id", r4));
        return;
    L6:
        Theme.ResourcesProvider r52 = this.resourcesProvider;
        if (r6 == null) goto L10;
        BulletinFactory.of(this, r52).showForError(r6);
        notifyEvent(r3, "requested_chat_failed", obj("req_id", r4));
        return;
    L10:
        BulletinFactory.of(this, r52).showForError("UNKNOWN_BUTTON");
        notifyEvent(r3, "requested_chat_failed", obj("req_id", r4));
    }

    private /* synthetic */ void lambda$onEventReceived$55(boolean[] r5, String r6, TL_keyboard.TL_buttonTypeRequestPeer r7, WebRequestContext r8, List r9) {
        if (r9 != null) goto L4;
        return;
    L4:
        if (r9.isEmpty() == true) goto L13;
        r5[0] = true;
        TLRPC.TL_messages_sendBotRequestedPeer r52 = new TLRPC.TL_messages_sendBotRequestedPeer();
        MessagesController.getInstance(this.currentAccount);
        r52.peer = MessagesController.getInputPeer(this.botUser);
        r52.webapp_req_id = r6;
        r52.button_id = r7.button_id;
        Iterator r72 = r9.iterator();
    L7:
        if (r72.hasNext() == false) goto L9;
        Long r92 = (Long) r72.next();
        r52.requested_peers.add(MessagesController.getInstance(this.currentAccount).getInputPeer(r92.longValue()));
        goto L7
    L9:
        ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r52, new AiTonesController$$ExternalSyntheticLambda0(), new BotWebViewContainer$$ExternalSyntheticLambda56(this, r8, r6));
        return;
    }

    private /* synthetic */ void lambda$onEventReceived$56(boolean[] r2, WebRequestContext r3, String r4, DialogInterface r5) {
        if (r2[0] == true) goto L6;
        r2[0] = true;
        notifyEvent(r3, "requested_chat_failed", obj("req_id", r4));
        return;
    }

    private /* synthetic */ void lambda$onEventReceived$57(WebRequestContext r3, String r4, TLRPC.Updates r5, TLRPC.TL_error r6) {
        if (r5 == null) goto L6;
        MessagesController.getInstance(this.currentAccount).processUpdates(r5, false);
        notifyEvent(r3, "requested_chat_sent", obj("req_id", r4));
        return;
    L6:
        Theme.ResourcesProvider r52 = this.resourcesProvider;
        if (r6 == null) goto L10;
        BulletinFactory.of(this, r52).showForError(r6);
        notifyEvent(r3, "requested_chat_failed", obj("req_id", r4));
        return;
    L10:
        BulletinFactory.of(this, r52).showForError("UNKNOWN_BUTTON");
        notifyEvent(r3, "requested_chat_failed", obj("req_id", r4));
    }

    private /* synthetic */ boolean lambda$onEventReceived$58(boolean[] r1, String r2, TL_keyboard.TL_buttonTypeRequestPeer r3, WebRequestContext r4, DialogsActivity r5, ArrayList r6, CharSequence r7, boolean r8, boolean r9, int r10, int r11, TopicsFragment r12) {
        if (r6 != null) goto L5;
    L14:
        r5.finishFragment();
        return true;
    L5:
        if (r6.isEmpty() == true) goto L14;
        int r82 = 0;
        r1[0] = true;
        TLRPC.TL_messages_sendBotRequestedPeer r13 = new TLRPC.TL_messages_sendBotRequestedPeer();
        MessagesController.getInstance(this.currentAccount);
        r13.peer = MessagesController.getInputPeer(this.botUser);
        r13.webapp_req_id = r2;
        r13.button_id = r3.button_id;
        HashSet r32 = new HashSet();
        int r92 = r6.size();
    L7:
        if (r82 >= r92) goto L9;
        Object r102 = r6.get(r82);
        r82 = r82 + 1;
        r32.add(Long.valueOf(((MessagesStorage.TopicKey) r102).dialogId));
        goto L7
    L9:
        Iterator r33 = r32.iterator();
    L11:
        if (r33.hasNext() == false) goto L13;
        long r83 = ((Long) r33.next()).longValue();
        r13.requested_peers.add(MessagesController.getInstance(this.currentAccount).getInputPeer(r83));
        goto L11
    L13:
        ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r13, new AiTonesController$$ExternalSyntheticLambda0(), new BotWebViewContainer$$ExternalSyntheticLambda51(this, r4, r2));
        goto L14
    }

    private /* synthetic */ void lambda$onEventReceived$59(WebRequestContext r15, String r16, TL_keyboard.KeyboardButton r17, TLRPC.TL_error r18) {
        TL_keyboard.TL_buttonTypeRequestPeer r4 = (TL_keyboard.TL_buttonTypeRequestPeer) TLKeyboardHelper.getType(r17, TL_keyboard.TL_buttonTypeRequestPeer.class);
        if (r4 == null) goto L26;
        TLRPC.RequestPeerType r0 = r4.peer_type;
        if ((r0 instanceof TLRPC.TL_requestPeerTypeCreateBot) == false) goto L9;
        Context r5 = getContext();
        int r6 = this.currentAccount;
        TLRPC.User r7 = this.botUser;
        BotWebViewContainer$$ExternalSyntheticLambda43 r10 = new BotWebViewContainer$$ExternalSyntheticLambda43(this, r15, r16, r4);
        Theme.ResourcesProvider r11 = this.resourcesProvider;
        CreateBotAlert.show(r5, r6, r7, (TLRPC.TL_requestPeerTypeCreateBot) r0, false, r10, r11, BulletinFactory.of(this, r11), true);
        return;
    L9:
        if ((r0 instanceof TLRPC.TL_requestPeerTypeUser) == false) goto L16;
        int r72 = r4.max_quantity;
        if (r72 <= 1) goto L16;
        TLRPC.TL_requestPeerTypeUser r02 = (TLRPC.TL_requestPeerTypeUser) r0;
        boolean[] r2 = new boolean[1];
        MultiContactsSelectorBottomSheet r03 = MultiContactsSelectorBottomSheet.open(r02.bot, r02.premium, r72, new BotWebViewContainer$$ExternalSyntheticLambda44(this, r2, r16, r4, r15));
        if (r03 == null) goto L23;
        r03.setOnDismissListener(new BotWebViewContainer$$ExternalSyntheticLambda45(this, r2, r15, r16));
        return;
    L23:
        return;
    L16:
        Bundle r22 = new Bundle();
        r22.putBoolean("onlySelect", true);
        r22.putInt("dialogsType", 15);
        r22.putLong("requestPeerBotId", this.botUser.id);
        SerializedData r04 = new SerializedData(r4.peer_type.getObjectSize());     // Catch: Exception -> L19
        r4.peer_type.serializeToStream(r04);     // Catch: Exception -> L19
        r22.putByteArray("requestPeerType", r04.toByteArray());     // Catch: Exception -> L19
        r04.cleanup();     // Catch: Exception -> L19
    L21:
        boolean[] r05 = new boolean[1];
        AnonymousClass7 r73 = new AnonymousClass7(this, r22, r05, r15);
        r73.setDelegate(new BotWebViewContainer$$ExternalSyntheticLambda46(this, r05, r16, r4, r15));
        BaseFragment r14 = LaunchActivity.getSafeLastFragment();
        if (r14 == null) goto L34;
        BaseFragment.BottomSheetParams r152 = new BaseFragment.BottomSheetParams();
        r152.transitionFromLeft = true;
        r152.allowNestedScroll = false;
        r14.showAsSheet(r73, r152);
        return;
    L34:
        return;
    L19:
        e = move-exception;
        FileLog.e(e);
        goto L21
    L26:
        Theme.ResourcesProvider r23 = this.resourcesProvider;
        if (r18 == null) goto L30;
        BulletinFactory.of(this, r23).showForError(r18);
        notifyEvent(r15, "requested_chat_failed", obj("req_id", r16));
        return;
    L30:
        BulletinFactory.of(this, r23).showForError("UNKNOWN_BUTTON");
        notifyEvent(r15, "requested_chat_failed", obj("req_id", r16));
    }

    private /* synthetic */ void lambda$onEventReceived$9(WebRequestContext r2, PopupButton r3, AtomicBoolean r4, AlertDialog r5, int r6) {
        r5.dismiss();
        this.lastClickMs = System.currentTimeMillis();     // Catch: JSONException -> L5
        notifyEvent(r2, "popup_closed", new JSONObject().put("button_id", r3.id));     // Catch: JSONException -> L5
        r4.set(true);     // Catch: JSONException -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$onWebEventReceived$5(TLObject r12, TLRPC.TL_messages_requestUrlAuth r13, String r14, TLRPC.TL_error r15, String r16) {
        if (r12 != null) goto L4;
        if (r15 == null) goto L23;
        boolean r0 = "URL_EXPIRED".equalsIgnoreCase(r15.text);
        Theme.ResourcesProvider r2 = this.resourcesProvider;
        if (r0 == false) goto L20;
        BulletinFactory.of(this, r2).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.BotAuthLoggedInFailTitle), AndroidUtilities.replaceSingleLinkBold(LocaleController.formatString(R.string.BotAuthLoggedInFail, new Object[]{r16}), Theme.getColor(Theme.key_undo_cancelColor, this.resourcesProvider))).show();
        return;
    L20:
        BulletinFactory.of(this, r2).showForError(r15);
        return;
    L23:
        return;
    L4:
        if ((r12 instanceof TLRPC.TL_urlAuthResultRequest) == false) goto L8;
        OAuthSheet.handle(false, this.currentAccount, r13, (TLRPC.TL_urlAuthResultRequest) r12, null, null, null, false, this);
        return;
    L8:
        if ((r12 instanceof TLRPC.TL_urlAuthResultAccepted) == false) goto L12;
        OAuthSheet.handle(false, this.currentAccount, r13, (TLRPC.TL_urlAuthResultAccepted) r12, null, null, null, false, this);
        return;
    L12:
        if ((r12 instanceof TLRPC.TL_urlAuthResultDefault) == false) goto L22;
        AlertsCreator.showOpenUrlAlert(getContext(), r14, false, true, true, false, 0, null, null);
        return;
    }

    private /* synthetic */ void lambda$onWebEventReceived$6(TLRPC.TL_messages_requestUrlAuth r8, String r9, String r10, TLObject r11, TLRPC.TL_error r12) {
        AndroidUtilities.runOnUIThread(new BotWebViewContainer$$ExternalSyntheticLambda37(this, r11, r8, r9, r12, r10));
    }

    private /* synthetic */ void lambda$reload$1() {
        if (this.isSettingsButtonVisible == false) goto L7;
        this.isSettingsButtonVisible = false;
        Delegate r0 = this.delegate;
        if (r0 == null) goto L7;
        r0.onSetSettingsButtonVisible(false);
    L7:
        checkCreateWebView();
        this.isPageLoaded = false;
        this.lastClickMs = 0;
        this.hasUserPermissions = false;
        MyWebView r02 = this.webView;
        if (r02 == null) goto L10;
        r02.onResume();
        this.webView.reload();
    L10:
        updateKeyboardFocusable();
        BotSensors r4 = this.sensors;
        if (r4 == null) goto L14;
        r4.stopAll();
        return;
    }

    private /* synthetic */ void lambda$restoreStorageKey$60(WebRequestContext r3, String r4, String r5, BotStorage r6, String r7, String r8, String r9) {
        if (r9 != null) goto L12;
        notifyEvent(r3, r4, obj("req_id", r5, "error", "RESTORE_CANCELLED"));
        return;
    L12:
        r6.restoreFrom(r9);     // Catch: Exception -> L9
        notifyEvent(r3, r8, obj("req_id", r5, "value", (String) r6.getKey(r7).first));
        return;
    L9:
        e = move-exception;
        notifyEvent(r3, r4, obj("req_id", r5, "error", e.getMessage()));
    }

    private /* synthetic */ void lambda$runWithPermissions$0(Consumer r1, String[] r2) {
        r1.accept(Boolean.valueOf(checkPermissions(r2)));
    }

    private /* synthetic */ void lambda$showDialog$62(Runnable r1, DialogInterface r2) {
        if (r1 == null) goto L4;
        r1.run();
    L4:
        this.currentDialog = null;
    }

    public static String magic2tonsite(String r3) {
        if (rotatedTONHosts == null) goto L6;
        if (r3 == null) goto L6;
        String r0 = AndroidUtilities.getHostAuthority(r3);
        if (r0 != null) goto L10;
        return r3;
    L10:
        if (r0.endsWith("." + MessagesController.getInstance(UserConfig.selectedAccount).tonProxyAddress) == false) goto L18;
        String r02 = rotatedTONHosts.get(r0);
        if (r02 != null) goto L16;
        return r3;
    L16:
        return Browser.replace(Uri.parse(r3), "tonsite", null, r02, null);
    L18:
        return r3;
    L6:
        return r3;
    }

    private void notifyBiometryReceived(WebRequestContext r3) {
        if (this.botUser == null) goto L15;
        createBiometry();
        BotBiometry r0 = this.biometry;
        if (r0 != null) goto L13;
        return;
    L13:
        notifyEvent(r3, "biometry_info_received", r0.getStatus());     // Catch: Exception -> L10
        return;
    L10:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    private void notifyEmojiStatusAccess(WebRequestContext r2, String r3) {
        notifyEvent(r2, "emoji_status_access_requested", obj("status", r3));
    }

    private static void notifyEvent(int r1, MyWebView r2, WebRequestContext r3, String r4, JSONObject r5) {
        if (r2 != null) goto L4;
        return;
    L4:
        NotificationCenter.getInstance(r1).doOnIdle(new BotWebViewContainer$$ExternalSyntheticLambda40(r2, r3, r4, r5));
    }

    private void notifyEvent(WebRequestContext r1, String r2, JSONObject r3) {
        if (isRequestContextCurrent(r1) == true) goto L6;
        d("notifyEvent " + r2 + " dropped after document change");
        return;
    L6:
        notifyEvent(r2, r3);
    }

    private void notifyEvent_fast(String r3, String r4) {
        if (this.bot == true) goto L5;
    L8:
        evaluateJs("window.Telegram.WebView.receiveEvent('" + r3 + "', " + r4 + ");", false);
        return;
    L5:
        if (isTrustedDocumentCurrent() == true) goto L8;
        d("notifyEvent " + r3 + " dropped for untrusted document");
    }

    public static JSONObject obj() {
        return new JSONObject();
    L4:
        return null;
    }

    public static JSONObject obj(String r1, Object r2) {
        JSONObject r0 = new JSONObject();     // Catch: Exception -> L4
        r0.put(r1, r2);     // Catch: Exception -> L4
        return r0;
    L4:
        return null;
    }

    public static JSONObject obj(String r1, Object r2, String r3, Object r4) {
        JSONObject r0 = new JSONObject();     // Catch: Exception -> L4
        r0.put(r1, r2);     // Catch: Exception -> L4
        r0.put(r3, r4);     // Catch: Exception -> L4
        return r0;
    L4:
        return null;
    }

    public static JSONObject obj(String r1, Object r2, String r3, Object r4, String r5, Object r6) {
        JSONObject r0 = new JSONObject();     // Catch: Exception -> L4
        r0.put(r1, r2);     // Catch: Exception -> L4
        r0.put(r3, r4);     // Catch: Exception -> L4
        r0.put(r5, r6);     // Catch: Exception -> L4
        return r0;
    L4:
        return null;
    }

    public static JSONObject obj(String r1, Object r2, String r3, Object r4, String r5, Object r6, String r7, Object r8) {
        JSONObject r0 = new JSONObject();     // Catch: Exception -> L4
        r0.put(r1, r2);     // Catch: Exception -> L4
        r0.put(r3, r4);     // Catch: Exception -> L4
        r0.put(r5, r6);     // Catch: Exception -> L4
        r0.put(r7, r8);     // Catch: Exception -> L4
        return r0;
    L4:
        return null;
    }

    private void onBotWebMessage(WebView r7, WebMessageCompat r8, Uri r9) {
        if (r7 != this.webView) goto L42;
        if (r8 == null) goto L42;
        if (r8.getType() != 0) goto L42;
        if (r9 != null) goto L11;
        String r92 = null;
    L12:
        String r93 = getOriginHost(r92);
        if (this.restrictBridgeToOrigin == true) goto L15;
    L22:
        WebRequestContext r3 = createRequestContext();
        String r82 = r8.getData();     // Catch: JSONException -> L39
        if (r82 == null) goto L37;
        if (r82.length() > 1048576) goto L37;
        JSONObject r94 = new JSONObject(r82);     // Catch: JSONException -> L39
        String r4 = r94.getString("eventType");     // Catch: JSONException -> L39
        if (r4.isEmpty() == true) goto L35;
        if (r4.length() > 128) goto L35;
        AndroidUtilities.runOnUIThread(new BotWebViewContainer$$ExternalSyntheticLambda49(this, r7, r3, r4, r94.optString("eventData", null)));     // Catch: JSONException -> L39
        return;
    L35:
        d("onBotWebMessage ignored: invalid event type");     // Catch: JSONException -> L39
        return;
    L37:
        d("onBotWebMessage ignored: invalid payload length");     // Catch: JSONException -> L39
        return;
    L39:
        e = move-exception;
        FileLog.e(e);
        return;
    L15:
        if (TextUtils.isEmpty(this.trustedOrigin) == false) goto L17;
    L20:
        d("onBotWebMessage ignored: untrusted origin");
        return;
    L17:
        if (TextUtils.equals(this.trustedOrigin, r93) == false) goto L20;
        if (TextUtils.equals(this.trustedOrigin, getOriginHost()) == true) goto L22;
    L11:
        r92 = r9.toString();
    L42:
        d("onBotWebMessage ignored: invalid source or payload");
    }

    private void onEventReceived(BotWebViewProxy r45, WebRequestContext r46, String r47, String r48) {
        if (this.bot == true) goto L6;
        return;
    L6:
        if (this.webView != null) goto L8;
    L1065:
        d("onEventReceived " + r47 + ": no webview or delegate!");
        return;
    L8:
        if (this.delegate == null) goto L1065;
        if (isRequestContextCurrent(r46) == true) goto L14;
        d("onEventReceived ignore " + r47 + " after document change");
        return;
    L14:
        d("onEventReceived " + r47);
        r47.getClass();
        switch(r47.hashCode()) {
            case -2016939055: goto L270;
            case -1898902656: goto L266;
            case -1736707758: goto L262;
            case -1717314938: goto L258;
            case -1715704462: goto L254;
            case -1693280352: goto L250;
            case -1390641887: goto L246;
            case -1385387727: goto L242;
            case -1353432696: goto L238;
            case -1341039673: goto L234;
            case -1309122684: goto L230;
            case -1263619595: goto L226;
            case -1259935152: goto L222;
            case -1229296877: goto L218;
            case -1183558219: goto L214;
            case -1093591555: goto L210;
            case -921083201: goto L206;
            case -907261345: goto L202;
            case -620103109: goto L198;
            case -585008607: goto L194;
            case -581005326: goto L190;
            case -512688845: goto L186;
            case -498118340: goto L182;
            case -474676372: goto L178;
            case -439770054: goto L174;
            case -293897269: goto L170;
            case -244584646: goto L166;
            case -216725042: goto L162;
            case -111186465: goto L158;
            case -71726289: goto L154;
            case -58095910: goto L150;
            case 22015443: goto L146;
            case 127735931: goto L142;
            case 189207985: goto L138;
            case 267846314: goto L134;
            case 348967753: goto L130;
            case 420328489: goto L126;
            case 475603707: goto L122;
            case 622108947: goto L118;
            case 668142772: goto L114;
            case 671811520: goto L110;
            case 721956751: goto L106;
            case 748864404: goto L102;
            case 751292356: goto L98;
            case 792789792: goto L94;
            case 796110323: goto L90;
            case 880626018: goto L86;
            case 909476449: goto L82;
            case 1011447167: goto L78;
            case 1210129967: goto L74;
            case 1273834781: goto L70;
            case 1398490221: goto L66;
            case 1453051298: goto L62;
            case 1455972419: goto L58;
            case 1495787980: goto L54;
            case 1759965681: goto L50;
            case 1812395469: goto L46;
            case 1882780382: goto L42;
            case 1899078473: goto L38;
            case 1917103703: goto L34;
            case 1937068806: goto L30;
            case 2001330488: goto L26;
            case 2036090717: goto L22;
            case 2139805763: goto L18;
            default: goto L16;
        };
    L16:
        char r7 = 65535;
    L273:
        long r29 = 1000;
        BotWebViewVibrationEffect r8 = null;
        String r82 = null;
        String r83 = null;
        String r84 = null;
        String r85 = null;
        BottomSheetTabs.WebTabData r86 = null;
        r8 = null;
        r8 = null;
        r8 = null;
        r8 = null;
        r8 = null;
        r8 = null;
        r8 = null;
        r8 = null;
        r8 = null;
        r8 = null;
        r8 = null;
        r8 = null;
        r8 = null;
        switch(r7) {
            case 0: goto L1054;
            case 1: goto L1048;
            case 2: goto L1045;
            case 3: goto L1138;
            case 4: goto L1017;
            case 5: goto L1089;
            case 6: goto L1129;
            case 7: goto L938;
            case 8: goto L1146;
            case 9: goto L1156;
            case 10: goto L1080;
            case 11: goto L861;
            case 12: goto L858;
            case 13: goto L851;
            case 14: goto L846;
            case 15: goto L830;
            case 16: goto L821;
            case 17: goto L812;
            case 18: goto L803;
            case 19: goto L796;
            case 20: goto L788;
            case 21: goto L1160;
            case 22: goto L1081;
            case 23: goto L1075;
            case 24: goto L1148;
            case 25: goto L748;
            case 26: goto L713;
            case 27: goto L699;
            case 28: goto L690;
            case 29: goto L1125;
            case 30: goto L653;
            case 31: goto L1077;
            case 32: goto L631;
            case 33: goto L623;
            case 34: goto L616;
            case 35: goto L608;
            case 36: goto L599;
            case 37: goto L591;
            case 38: goto L575;
            case 39: goto L1158;
            case 40: goto L567;
            case 41: goto L555;
            case 42: goto L1152;
            case 43: goto L1109;
            case 44: goto L527;
            case 45: goto L520;
            case 46: goto L513;
            case 47: goto L497;
            case 48: goto L1090;
            case 49: goto L1164;
            case 50: goto L1092;
            case 51: goto L1119;
            case 52: goto L1083;
            case 53: goto L1127;
            case 54: goto L362;
            case 55: goto L1085;
            case 56: goto L344;
            case 57: goto L1099;
            case 58: goto L1101;
            case 59: goto L1096;
            case 60: goto L292;
            case 61: goto L1134;
            case 62: goto L280;
            case 63: goto L277;
            default: goto L275;
        };
    L275:
        FileLog.d("unknown webapp event ".concat(r47));
        return;
    L277:
        this.delegate.onWebAppExpand();
        return;
    L292:
        reportSafeInsets(this.lastInsets, true);
        return;
    L344:
        BotSensors r0 = this.delegate.getBotSensors();
        JSONObject r5 = new JSONObject(r48);     // Catch: Exception -> L347
        r29 = r5.getLong("refresh_rate");     // Catch: Exception -> L347
        boolean r14 = r5.optBoolean("need_absolute", false);     // Catch: Exception -> L347
    L348:
        long r3 = Utilities.clamp(r29, 1000, 20);
        if (r0 != null) goto L351;
    L353:
        notifyEvent(r46, "device_orientation_failed", obj("error", "UNSUPPORTED"));
        return;
    L351:
        if (r0.startOrientation(r14, r3) == false) goto L353;
        notifyEvent(r46, "device_orientation_started", null);
        return;
    L347:
        r14 = false;
        goto L348
    L520:
        String r02 = this.delegate.onFullscreenRequested(false, true);
        if (r02 != null) goto L524;
        notifyEvent(r46, "fullscreen_changed", obj("is_fullscreen", Boolean.FALSE));
        return;
    L524:
        notifyEvent(r46, "fullscreen_failed", obj("error", r02));
        return;
    L567:
        reportSafeContentInsets(this.lastInsetsTopMargin, true);
        return;
    L591:
        BotSensors r03 = this.delegate.getBotSensors();
        if (r03 != null) goto L594;
    L597:
        notifyEvent(r46, "accelerometer_failed", obj("error", "UNSUPPORTED"));
        return;
    L594:
        if (r03.stopAccelerometer() == false) goto L597;
        notifyEvent(r46, "accelerometer_stopped", null);
        return;
    L599:
        BotSensors r52 = this.delegate.getBotSensors();
        r29 = new JSONObject(r48).getLong("refresh_rate");     // Catch: Exception -> L1069
    L601:
        long r9 = Utilities.clamp(r29, 1000, 20);
        if (r52 != null) goto L604;
    L606:
        notifyEvent(r46, "accelerometer_failed", obj("error", "UNSUPPORTED"));
        return;
    L604:
        if (r52.startAccelerometer(r9) == false) goto L606;
        notifyEvent(r46, "accelerometer_started", null);
        return;
    L623:
        BotSensors r04 = this.delegate.getBotSensors();
        if (r04 != null) goto L626;
    L629:
        notifyEvent(r46, "gyroscope_failed", obj("error", "UNSUPPORTED"));
        return;
    L626:
        if (r04.stopGyroscope() == false) goto L629;
        notifyEvent(r46, "gyroscope_stopped", null);
        return;
    L631:
        Activity r05 = AndroidUtilities.findActivity(getContext());
        if (r05 != null) goto L634;
        r05 = LaunchActivity.instance;
    L634:
        if (r05 == null) goto L1210;
        AndroidUtilities.hideKeyboard(r05.getCurrentFocus());
        return;
    L1210:
        return;
    L653:
        setPageLoaded(this.webView.getUrl(), true);
        return;
    L690:
        BotSensors r06 = this.delegate.getBotSensors();
        r29 = new JSONObject(r48).getLong("refresh_rate");     // Catch: Exception -> L1070
    L692:
        long r32 = Utilities.clamp(r29, 1000, 20);
        if (r06 != null) goto L695;
    L697:
        notifyEvent(r46, "gyroscope_failed", obj("error", "UNSUPPORTED"));
        return;
    L695:
        if (r06.startGyroscope(r32) == false) goto L697;
        notifyEvent(r46, "gyroscope_started", null);
        return;
    L803:
        BotSensors r07 = this.delegate.getBotSensors();
        if (r07 != null) goto L806;
    L809:
        notifyEvent(r46, "device_orientation_failed", obj("error", "UNSUPPORTED"));
        return;
    L806:
        if (r07.stopOrientation() == false) goto L809;
        notifyEvent(r46, "device_orientation_stopped", null);
        return;
    L858:
        notifyThemeChanged();
        return;
    L1045:
        notifyBiometryReceived(r46);
        return;
    L1075:
        JSONArray r08 = new JSONArray(r48);     // Catch: Exception -> L765
        boolean r2 = r08.optBoolean(0, true);     // Catch: Exception -> L765
        boolean r15 = r08.optBoolean(1, true);     // Catch: Exception -> L1071
    L767:
        d("allowScroll " + r2 + " " + r15);
        if ((getParent() instanceof ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer) == false) goto L1228;
        ((ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer) getParent()).allowThisScroll(r2, r15);
        return;
    L1228:
        return;
    L766:
        r15 = true;
    L765:
        r2 = true;
        goto L766
    L1081:
        boolean r142 = new JSONObject(r48).getBoolean("locked");     // Catch: Exception -> L772
    L773:
        Delegate r09 = this.delegate;
        if (r09 == null) goto L1230;
        r09.onOrientationLockChanged(r142);
        return;
    L1230:
        return;
    L772:
        r142 = false;
        goto L773
    L1083:
        this.delegate.onWebAppSwipingBehavior(new JSONObject(r48).optBoolean("allow_vertical_swipe"));     // Catch: JSONException -> L380
        return;
    L380:
        e = move-exception;
        FileLog.e(e);
        return;
    L1085:
        r85 = new JSONObject(r48).optString("req_id");     // Catch: Exception -> L356
    L358:
        if (r85 == null) goto L1267;
        TL_bots.getRequestedWebViewButton r010 = new TL_bots.getRequestedWebViewButton();
        r010.bot = MessagesController.getInstance(this.currentAccount).getInputUser(this.botUser);
        r010.webapp_req_id = r85;
        ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r010, new AiTonesController$$ExternalSyntheticLambda0(), new BotWebViewContainer$$ExternalSyntheticLambda22(this, r46, r85));
        return;
    L1267:
        return;
    L356:
        e = move-exception;
        FileLog.e(e);
        goto L358
    L1092:
        JSONObject r011 = new JSONObject(r48);     // Catch: Exception -> L469
        String r1 = r011.optString("type");     // Catch: Exception -> L469
        int r22 = r1.hashCode();     // Catch: Exception -> L469
        if (r22 == (-1184809658)) goto L447;
        if (r22 == 193071555) goto L444;
        if (r22 == 595233003) goto L426;
    L466:
        if (r8 == null) goto L1189;
        r8.vibrate();     // Catch: Exception -> L469
        return;
    L1189:
        return;
    L426:
        if (r1.equals("notification") == false) goto L466;
        String r012 = r011.optString("notification_type");     // Catch: Exception -> L469
        int r12 = r012.hashCode();     // Catch: Exception -> L469
        if (r12 == (-1867169789)) goto L441;
        if (r12 == 96784904) goto L438;
        if (r12 != 1124446108) goto L466;
        if (r012.equals("warning") == false) goto L466;
        r8 = BotWebViewVibrationEffect.NOTIFICATION_WARNING;     // Catch: Exception -> L469
        goto L466
    L438:
        if (r012.equals("error") == false) goto L466;
        r8 = BotWebViewVibrationEffect.NOTIFICATION_ERROR;     // Catch: Exception -> L469
        goto L466
    L441:
        if (r012.equals("success") == false) goto L466;
        r8 = BotWebViewVibrationEffect.NOTIFICATION_SUCCESS;     // Catch: Exception -> L469
        goto L466
    L444:
        if (r1.equals("selection_change") == false) goto L466;
        r8 = BotWebViewVibrationEffect.SELECTION_CHANGE;     // Catch: Exception -> L469
        goto L466
    L447:
        if (r1.equals("impact") == false) goto L466;
        String r013 = r011.optString("impact_style");     // Catch: Exception -> L469
        switch(r013.hashCode()) {
            case -1078030475: goto L464;
            case 3535914: goto L461;
            case 99152071: goto L458;
            case 102970646: goto L455;
            case 108511787: goto L452;
            default: goto L466;
        };     // Catch: Exception -> L469
    L452:
        if (r013.equals("rigid") == false) goto L466;
        r8 = BotWebViewVibrationEffect.IMPACT_RIGID;     // Catch: Exception -> L469
        goto L466
    L455:
        if (r013.equals("light") == false) goto L466;
        r8 = BotWebViewVibrationEffect.IMPACT_LIGHT;     // Catch: Exception -> L469
        goto L466
    L458:
        if (r013.equals("heavy") == false) goto L466;
        r8 = BotWebViewVibrationEffect.IMPACT_HEAVY;     // Catch: Exception -> L469
        goto L466
    L461:
        if (r013.equals("soft") == false) goto L466;
        r8 = BotWebViewVibrationEffect.IMPACT_SOFT;     // Catch: Exception -> L469
        goto L466
    L464:
        if (r013.equals("medium") == false) goto L466;
        r8 = BotWebViewVibrationEffect.IMPACT_MEDIUM;     // Catch: Exception -> L469
    L469:
        e = move-exception;
        FileLog.e(e);
        return;
    L1096:
        JSONObject r014 = new JSONObject(r48);     // Catch: Exception -> L315
        String r23 = r014.optString("color", null);     // Catch: Exception -> L315
        if (TextUtils.isEmpty(r23) == true) goto L300;
        int r015 = Color.parseColor(r23);     // Catch: Exception -> L315
        if (r015 == 0) goto L1173;
        this.delegate.onWebAppSetActionBarColor(-1, r015, true);     // Catch: Exception -> L315
        return;
    L1173:
        return;
    L300:
        String r016 = r014.optString("color_key");     // Catch: Exception -> L315
        int r24 = r016.hashCode();     // Catch: Exception -> L315
        if (r24 == (-1265068311)) goto L309;
        if (r24 == (-210781868)) goto L306;
    L311:
        int r11 = -1;
    L312:
        if (r11 < 0) goto L1174;
        this.delegate.onWebAppSetActionBarColor(r11, Theme.getColor(r11, this.resourcesProvider), false);     // Catch: Exception -> L315
        return;
    L1174:
        return;
    L306:
        if (r016.equals("secondary_bg_color") == false) goto L311;
        r11 = Theme.key_windowBackgroundGray;     // Catch: Exception -> L315
        goto L312
    L309:
        if (r016.equals("bg_color") == false) goto L311;
        r11 = Theme.key_windowBackgroundWhite;     // Catch: Exception -> L315
    L315:
        e = move-exception;
        FileLog.e(e);
        return;
    L1099:
        JSONObject r017 = new JSONObject(r48);     // Catch: Exception -> L339
        String r33 = r017.getString("token");     // Catch: Exception -> L339
        r84 = r017.getString("reason");     // Catch: Exception -> L1067
    L329:
        createBiometry();
        BotBiometry r018 = this.biometry;
        if (r018 != null) goto L333;
        return;
    L333:
        if (r018.access_granted == false) goto L1130;
        r018.updateToken(r84, r33, new BotWebViewContainer$$ExternalSyntheticLambda10(this, r33, r46));
        return;
    L1130:
        JSONObject r019 = new JSONObject();     // Catch: Exception -> L336
        r019.put("status", "failed");     // Catch: Exception -> L336
        notifyEvent(r46, "biometry_token_updated", r019);     // Catch: Exception -> L336
        return;
    L336:
        e = move-exception;
        FileLog.e(e);
        return;
    L339:
        e = move-exception;
        FileLog.e(e);
        if ((e instanceof JSONException) == false) goto L343;
        error("JSON Parse error");
        return;
    L343:
        unknownError();
        return;
    L1101:
        String r020 = new JSONObject(r48).optString("color", null);     // Catch: Exception -> L325
        if (TextUtils.isEmpty(r020) == false) goto L320;
        int r021 = Theme.getColor(Theme.key_windowBackgroundGray, this.resourcesProvider);     // Catch: Exception -> L325
    L321:
        Delegate r13 = this.delegate;     // Catch: Exception -> L325
        if (r13 == null) goto L1176;
        r13.onWebAppSetNavigationBarColor(r021);     // Catch: Exception -> L325
        return;
    L1176:
        return;
    L320:
        r021 = Color.parseColor(r020);     // Catch: Exception -> L325
    L325:
        e = move-exception;
        FileLog.e(e);
        return;
    L1127:
        boolean r022 = new JSONObject(r48).optBoolean("is_visible");     // Catch: JSONException -> L376
        if (r022 == this.isSettingsButtonVisible) goto L1185;
        this.isSettingsButtonVisible = r022;     // Catch: JSONException -> L376
        this.delegate.onSetSettingsButtonVisible(r022);     // Catch: JSONException -> L376
        return;
    L1185:
        return;
    L376:
        e = move-exception;
        FileLog.e(e);
        return;
    L1134:
        this.delegate.onWebAppSetBackgroundColor(Color.parseColor(new JSONObject(r48).optString("color", "#ffffff")) | (-16777216));     // Catch: Exception -> L290
        return;
    L290:
        e = move-exception;
        FileLog.e(e);
        return;
    L1148:
        JSONObject r023 = new JSONObject(r48);     // Catch: JSONException -> L760
        String r16 = r023.optString("path_full");     // Catch: JSONException -> L760
        boolean r6 = r023.optBoolean("force_request", false);     // Catch: JSONException -> L760
        if (r16.startsWith("/") == false) goto L758;
        r16 = r16.substring(1);     // Catch: JSONException -> L760
    L758:
        onOpenUri(Uri.parse("https://t.me/".concat(r16)), null, false, true, r6);     // Catch: JSONException -> L760
        return;
    L760:
        e = move-exception;
        FileLog.e(e);
        return;
    L1160:
        r83 = new JSONObject(r48).getString("reason");     // Catch: Exception -> L1074
    L777:
        createBiometry();
        BotBiometry r024 = this.biometry;
        if (r024 != null) goto L781;
        return;
    L781:
        if (r024.access_granted == false) goto L1105;
        r024.requestToken(r83, new BotWebViewContainer$$ExternalSyntheticLambda9(this, r46));
        return;
    L1105:
        JSONObject r025 = new JSONObject();     // Catch: Exception -> L784
        r025.put("status", "failed");     // Catch: Exception -> L784
        notifyEvent(r46, "biometry_auth_requested", r025);     // Catch: Exception -> L784
        return;
    L784:
        e = move-exception;
        FileLog.e(e);
        return;
    L1164:
        r82 = new JSONObject(r48).getString("reason");     // Catch: Exception -> L1073
    L472:
        createBiometry();
        BotBiometry r026 = this.biometry;
        if (r026 == null) goto L1269;
        boolean r34 = r026.access_requested;
        if (r34 == false) goto L480;
        notifyBiometryReceived(r46);
        return;
    L480:
        if (r026.access_granted == true) goto L486;
        Runnable[] r35 = {new BotWebViewContainer$$ExternalSyntheticLambda5(this, r46)};
        AlertDialog.Builder r027 = new AlertDialog.Builder(getContext(), this.resourcesProvider);
        if (TextUtils.isEmpty(r82) == false) goto L484;
        r027.setTitle(LocaleController.getString(R.string.BotAllowBiometryTitle));
        r027.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.BotAllowBiometryMessage, new Object[]{UserObject.getUserName(this.botUser)})));
    L485:
        r027.setPositiveButton(LocaleController.getString(R.string.Allow), new BotWebViewContainer$$ExternalSyntheticLambda6(this, r35, r46));
        r027.setNegativeButton(LocaleController.getString(R.string.Cancel), new BotWebViewContainer$$ExternalSyntheticLambda7(this, r35, r46));
        r027.setOnDismissListener(new BotWebViewContainer$$ExternalSyntheticLambda8(r35));
        r027.show();
        return;
    L484:
        r027.setTitle(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.BotAllowBiometryMessage, new Object[]{UserObject.getUserName(this.botUser)})));
        r027.setMessage(r82);
        goto L485
    L486:
        if (r34 == true) goto L488;
        r026.access_requested = true;
        r026.save();
    L488:
        notifyBiometryReceived(r46);
        return;
    L1269:
        return;
    L280:
        if (ignoreDialog(3) == true) goto L1142;
        int r028 = this.currentAccount;
        MyWebView r36 = this.webView;
        TL_bots.canSendMessage r4 = new TL_bots.canSendMessage();
        r4.bot = MessagesController.getInstance(this.currentAccount).getInputUser(this.botUser);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r4, new BotWebViewContainer$$ExternalSyntheticLambda26(this, r028, r36, r46));
        return;
    L1142:
        JSONObject r029 = new JSONObject();     // Catch: Exception -> L283
        r029.put("status", "cancelled");     // Catch: Exception -> L283
        notifyEvent(r46, "write_access_requested", r029);     // Catch: Exception -> L283
        return;
    L283:
        e = move-exception;
        FileLog.e(e);
        return;
    L362:
        if (this.botUser != null) goto L364;
    L369:
        String r030 = "unsupported";
    L370:
        notifyEvent(r46, "home_screen_checked", obj("status", r030));
        return;
    L364:
        if (Build.VERSION.SDK_INT < 26) goto L369;
        if (MediaDataController.getInstance(this.currentAccount).isShortcutAdded(this.botUser.id, MediaDataController.SHORTCUT_TYPE_ATTACHED_BOT) == false) goto L368;
        r030 = "added";
        goto L370
    L368:
        r030 = "missed";
        goto L370
    L1119:
        JSONObject r031 = new JSONObject(r48);     // Catch: Exception -> L416
        boolean r242 = r031.optBoolean("is_active", false);     // Catch: Exception -> L416
        String r37 = r031.optString("text", this.lastButtonText).trim();     // Catch: Exception -> L416
        if (r031.optBoolean("is_visible", false) == true) goto L386;
    L388:
        boolean r232 = false;
    L390:
        if (r031.has("color") == false) goto L393;
        int r25 = Color.parseColor(r031.optString("color"));     // Catch: Exception -> L416
    L395:
        if (r031.has("text_color") == false) goto L398;
        int r53 = Color.parseColor(r031.optString("text_color"));     // Catch: Exception -> L416
    L400:
        if (r031.optBoolean("is_progress_visible", false) == false) goto L404;
        if (r232 == false) goto L404;
        boolean r30 = true;
    L406:
        if (r031.optBoolean("has_shine_effect", false) == false) goto L410;
        if (r232 == false) goto L410;
        boolean r31 = true;
    L1154:
        long r26 = Long.parseLong(r031.getString("icon_custom_emoji_id"));     // Catch: Throwable -> L413
    L414:
        this.lastButtonColor = r25;     // Catch: Exception -> L416
        this.lastButtonTextColor = r53;     // Catch: Exception -> L416
        this.lastButtonText = r37;     // Catch: Exception -> L416
        this.buttonData = r48;     // Catch: Exception -> L416
        this.delegate.onSetupMainButton(r232, r242, r37, r26, r25, r53, r30, r31);     // Catch: Exception -> L416
        return;
    L413:
        r26 = 0;
    L410:
        r31 = false;
    L404:
        r30 = false;
        goto L406
    L398:
        r53 = this.lastButtonTextColor;     // Catch: Exception -> L416
        goto L400
    L393:
        r25 = this.lastButtonColor;     // Catch: Exception -> L416
        goto L395
    L386:
        if (TextUtils.isEmpty(r37) == true) goto L388;
        r232 = true;
    L416:
        e = move-exception;
        FileLog.e(e);
        return;
    L1090:
        boolean r032 = new JSONObject(r48).optBoolean("is_visible");     // Catch: JSONException -> L494
        if (r032 == this.isBackButtonVisible) goto L1193;
        this.isBackButtonVisible = r032;     // Catch: JSONException -> L494
        this.delegate.onSetBackButtonVisible(r032);     // Catch: JSONException -> L494
        return;
    L1193:
        return;
    L494:
        e = move-exception;
        FileLog.e(e);
        return;
    L497:
        if (this.isRequestingPageOpen == false) goto L499;
        return;
    L499:
        if (this.botUser != null) goto L501;
        return;
    L501:
        if ((System.currentTimeMillis() - this.lastClickMs) > NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L1270;
        this.lastClickMs = 0;
        BaseFragment r033 = LaunchActivity.getSafeLastFragment();
        if (r033 != null) goto L506;
        return;
    L506:
        if (r033.getParentLayout() == null) goto L1271;
        INavigationLayout r27 = r033.getParentLayout();
        r033.presentFragment(ProfileActivity.of(this.botUser.id));
        AndroidUtilities.scrollToFragmentRow(r27, "botPermissionLocation");
        Delegate r034 = this.delegate;
        if (r034 == null) goto L1198;
        r034.onCloseToTabs();
        return;
    L1198:
        return;
    L1271:
        return;
    L1270:
        return;
    L513:
        if (this.onVerifiedAge == null) goto L1199;
        JSONObject r035 = new JSONObject(r48);     // Catch: Exception -> L517
        AndroidUtilities.runOnUIThread(new BotWebViewContainer$$ExternalSyntheticLambda21(this, r035.getBoolean("passed"), r035.getDouble("age"), r035.optString("gender"), r035.optDouble("genderProbability")));
        return;
    L517:
        e = move-exception;
        FileLog.e(e);
        return;
    L1199:
        return;
    L527:
        if (this.botUser != null) goto L530;
        return;
    L530:
        if (this.secureStorage != null) goto L532;
        Context r62 = getContext();
        int r72 = this.currentAccount;
        this.secureStorage = new BotStorage(r62, r72, UserConfig.getInstance(r72).getClientUserId(), this.botUser.id, true);
    L532:
        setStorageKey(r46, this.secureStorage, r48, "secure_storage_key_saved", "secure_storage_failed");
        return;
    L1109:
        JSONObject r28 = new JSONObject(r48);     // Catch: JSONException -> L541
        ArrayList r036 = new ArrayList();     // Catch: JSONException -> L541
        JSONArray r38 = r28.getJSONArray("chat_types");     // Catch: JSONException -> L541
        int r143 = 0;
    L537:
        if (r143 >= r38.length()) goto L539;
        r036.add(r38.getString(r143));     // Catch: JSONException -> L541
        r143 = r143 + 1;     // Catch: JSONException -> L541
        goto L537
    L539:
        this.delegate.onWebAppSwitchInlineQuery(this.botUser, r28.getString("query"), r036);     // Catch: JSONException -> L541
        return;
    L541:
        e = move-exception;
        FileLog.e(e);
        return;
    L1152:
        boolean r87 = true;
        boolean r037 = new JSONObject(r48).optBoolean("blur", true);     // Catch: Exception -> L1068
    L550:
        String r63 = this.delegate.onFullscreenRequested(r87, r037);
        if (r63 != null) goto L553;
        notifyEvent(r46, "fullscreen_changed", obj("is_fullscreen", Boolean.TRUE, "blur_enabled", Boolean.valueOf(r037)));
        return;
    L553:
        notifyEvent(r46, "fullscreen_failed", obj("error", r63));
        return;
    L549:
        r037 = r87;
    L548:
        r87 = true;
        goto L549
    L555:
        if (this.isRequestingPageOpen == false) goto L557;
        return;
    L557:
        if (this.botUser != null) goto L559;
        return;
    L559:
        if ((System.currentTimeMillis() - this.lastClickMs) <= NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L562;
        return;
    L562:
        if (MediaDataController.getInstance(this.currentAccount).isShortcutAdded(this.botUser.id, MediaDataController.SHORTCUT_TYPE_ATTACHED_BOT) == false) goto L565;
        notifyEvent(r46, "home_screen_added", null);
        return;
    L565:
        MediaDataController.getInstance(this.currentAccount).installShortcut(this.botUser.id, MediaDataController.SHORTCUT_TYPE_ATTACHED_BOT, new BotWebViewContainer$$ExternalSyntheticLambda12(this, r46));
        return;
    L1158:
        this.delegate.onSendWebViewData(new JSONObject(r48).optString("data"));     // Catch: JSONException -> L572
        return;
    L572:
        e = move-exception;
        FileLog.e(e);
        return;
    L575:
        if (this.isRequestingPageOpen == false) goto L577;
        return;
    L577:
        if (this.botUser != null) goto L579;
        return;
    L579:
        if ((System.currentTimeMillis() - this.lastClickMs) > NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L1274;
        String r122 = new JSONObject(r48).getString("id");     // Catch: Exception -> L588
        if (TextUtils.isEmpty(r122) == false) goto L586;
        notifyEvent(r46, "prepared_message_failed", obj("error", "MESSAGE_EXPIRED"));
        return;
    L586:
        BotShareSheet.share(getContext(), this.currentAccount, this.botUser.id, r122, this.resourcesProvider, new BotWebViewContainer$$ExternalSyntheticLambda19(this), new BotWebViewContainer$$ExternalSyntheticLambda20(this, r46, r45));
        return;
    L588:
        e = move-exception;
        FileLog.e(e);
        notifyEvent(r46, "prepared_message_failed", obj("error", "MESSAGE_EXPIRED"));
        return;
    L1274:
        return;
    L608:
        if (this.botUser != null) goto L611;
        return;
    L611:
        if (this.storage != null) goto L613;
        Context r54 = getContext();
        int r64 = this.currentAccount;
        this.storage = new BotStorage(r54, r64, UserConfig.getInstance(r64).getClientUserId(), this.botUser.id, false);
    L613:
        clearStorageKey(r46, this.storage, r48, "device_storage_cleared", "device_storage_failed");
        return;
    L616:
        if (this.botUser != null) goto L619;
        return;
    L619:
        if (this.secureStorage != null) goto L621;
        Context r39 = getContext();
        int r42 = this.currentAccount;
        this.secureStorage = new BotStorage(r39, r42, UserConfig.getInstance(r42).getClientUserId(), this.botUser.id, true);
    L621:
        clearStorageKey(r46, this.secureStorage, r48, "secure_storage_cleared", "secure_storage_cleared");
        return;
    L1077:
        String r038 = new JSONObject(r48).getString("req_id");     // Catch: JSONException -> L651
        if (this.delegate.isClipboardAvailable() == true) goto L641;
    L649:
        notifyEvent(r46, "clipboard_text_received", new JSONObject().put("req_id", r038));     // Catch: JSONException -> L651
        return;
    L641:
        if ((System.currentTimeMillis() - this.lastClickMs) > NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L649;
        CharSequence r310 = ((ClipboardManager) getContext().getSystemService("clipboard")).getText();     // Catch: JSONException -> L651
        if (r310 == null) goto L646;
        String r311 = r310.toString();     // Catch: JSONException -> L651
    L647:
        notifyEvent(r46, "clipboard_text_received", new JSONObject().put("req_id", r038).put("data", r311));     // Catch: JSONException -> L651
        return;
    L646:
        r311 = _UrlKt.FRAGMENT_ENCODE_SET;
    L651:
        e = move-exception;
        FileLog.e(e);
        return;
    L1125:
        boolean r039 = new JSONObject(r48).optBoolean("return_back");     // Catch: Exception -> L658
    L660:
        this.delegate.onCloseRequested(null);
        if (r039 == true) goto L663;
        return;
    L663:
        if (this.wasOpenedByLinkIntent == false) goto L674;
        if (LaunchActivity.instance == null) goto L674;
        Activity r040 = AndroidUtilities.findActivity(getContext());
        if (r040 != null) goto L669;
        r040 = LaunchActivity.instance;
    L669:
        if (r040 != null) goto L671;
        return;
    L671:
        if (r040.isFinishing() == true) goto L1214;
        r040.moveTaskToBack(true);
        return;
    L1214:
        return;
    L674:
        if (this.wasOpenedByBot == null) goto L1216;
        LaunchActivity r041 = LaunchActivity.instance;
        if (r041 != null) goto L678;
        return;
    L678:
        if (r041.getBottomSheetTabs() == null) goto L1218;
        BottomSheetTabs r042 = LaunchActivity.instance.getBottomSheetTabs();
        ArrayList<BottomSheetTabs.WebTabData> r17 = r042.getTabs();
        int r144 = 0;
    L681:
        if (r144 >= r17.size()) goto L688;
        BottomSheetTabs.WebTabData r312 = r17.get(r144);
        if (this.wasOpenedByBot.equals(r312.props) == false) goto L687;
        if (r312.webView == this.webView) goto L687;
        r86 = r312;
    L687:
        r144 = r144 + 1;
    L688:
        if (r86 == null) goto L1219;
        r042.openTab(r86);
        return;
    L1219:
        return;
    L1218:
        return;
    L1216:
        return;
    L658:
        e = move-exception;
        FileLog.e(e);
        r039 = false;
        goto L660
    L699:
        if (this.isRequestingPageOpen == false) goto L701;
        return;
    L701:
        if (this.botUser != null) goto L704;
        return;
    L704:
        if (this.location != null) goto L706;
        BotLocation r043 = BotLocation.get(getContext(), this.currentAccount, this.botUser.id);
        this.location = r043;
        r043.listen(this.notifyLocationChecked);
    L706:
        this.locationRequestContext = r46;
        boolean r044 = this.location.granted();
        BotLocation r18 = this.location;
        if (r044 == true) goto L710;
        r18.request(new BotWebViewContainer$$ExternalSyntheticLambda16(this, r46));
        return;
    L710:
        r18.requestObject(new BotWebViewContainer$$ExternalSyntheticLambda17(this, r46));
        return;
    L713:
        if (this.isRequestingPageOpen == false) goto L715;
        return;
    L715:
        if ((System.currentTimeMillis() - this.lastClickMs) <= NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L717;
        return;
    L717:
        if ((System.currentTimeMillis() - this.lastPostStoryMs) < 2000) goto L1278;
        this.lastClickMs = 0;
        this.lastPostStoryMs = System.currentTimeMillis();
        JSONObject r045 = new JSONObject(r48);     // Catch: Exception -> L737
        String r19 = r045.optString("media_url");     // Catch: Exception -> L737
        String r313 = r045.optString("text");     // Catch: Exception -> L734
        JSONObject r046 = r045.optJSONObject("widget_link");     // Catch: Exception -> L729
        if (r046 == null) goto L731;
        String r43 = r046.optString("url");     // Catch: Exception -> L729
        String r047 = r046.optString("name");     // Catch: Exception -> L727
    L732:
        String r55 = r047;
    L733:
        String r65 = r19;
        if (r65 != null) goto L743;
        return;
    L743:
        if (MessagesController.getInstance(this.currentAccount).storiesEnabled() == true) goto L746;
        new PremiumFeatureBottomSheet(new AnonymousClass5(this), 14, true).show();
        return;
    L746:
        AlertDialog r048 = new AlertDialog(this.parentActivity, 3);
        new HttpGetFileTask(new BotWebViewContainer$$ExternalSyntheticLambda11(this, r048, r313, r43, r55), null).execute(new String[]{r65});
        r048.showDelayed(250);
        return;
    L727:
        e = e;
    L739:
        FileLog.e(e);
        r55 = null;
        goto L733
    L731:
        r047 = null;
        r43 = null;
    L729:
        e = e;
        r43 = null;
    L734:
        e = e;
        r313 = null;
    L736:
        r43 = r313;
    L737:
        e = e;
        r19 = null;
        r313 = null;
        goto L736
    L1278:
        return;
    L748:
        if (this.botUser != null) goto L751;
        return;
    L751:
        if (this.secureStorage != null) goto L753;
        Context r73 = getContext();
        int r88 = this.currentAccount;
        this.secureStorage = new BotStorage(r73, r88, UserConfig.getInstance(r88).getClientUserId(), this.botUser.id, true);
    L753:
        restoreStorageKey(r46, this.secureStorage, r48, "secure_storage_key_restored", "secure_storage_failed");
        return;
    L788:
        if (this.botUser != null) goto L791;
        return;
    L791:
        if (this.storage != null) goto L793;
        Context r66 = getContext();
        int r74 = this.currentAccount;
        this.storage = new BotStorage(r66, r74, UserConfig.getInstance(r74).getClientUserId(), this.botUser.id, false);
    L793:
        getStorageKey(r46, this.storage, r48, "device_storage_key_received", "device_storage_failed");
        return;
    L796:
        if (this.botUser != null) goto L799;
        return;
    L799:
        if (this.storage != null) goto L801;
        Context r314 = getContext();
        int r44 = this.currentAccount;
        this.storage = new BotStorage(r314, r44, UserConfig.getInstance(r44).getClientUserId(), this.botUser.id, false);
    L801:
        setStorageKey(r46, this.storage, r48, "device_storage_key_saved", "device_storage_failed");
        return;
    L812:
        if (this.isRequestingPageOpen == false) goto L814;
        return;
    L814:
        if (this.botUser != null) goto L816;
        return;
    L816:
        if ((System.currentTimeMillis() - this.lastClickMs) > NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L1285;
        SetupEmojiStatusSheet.askPermission(this.currentAccount, this.botUser.id, new BotWebViewContainer$$ExternalSyntheticLambda14(this, r46));
        return;
    L1285:
        return;
    L821:
        if ((getParent() instanceof ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer) == true) goto L823;
    L826:
        boolean r145 = false;
    L827:
        invalidateViewPortHeight(!r145, true);
        return;
    L823:
        if (((ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer) getParent()).isSwipeInProgress() == false) goto L826;
        r145 = true;
        goto L827
    L830:
        if (this.isRequestingPageOpen == false) goto L832;
        return;
    L832:
        if (this.botUser != null) goto L834;
        return;
    L834:
        if ((System.currentTimeMillis() - this.lastClickMs) > NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L1286;
        this.lastClickMs = 0;
        BaseFragment r049 = LaunchActivity.getSafeLastFragment();
        if (r049 != null) goto L839;
        return;
    L839:
        if (r049.getParentLayout() == null) goto L1287;
        INavigationLayout r210 = r049.getParentLayout();
        r049.presentFragment(ProfileActivity.of(this.botUser.id));
        AndroidUtilities.scrollToFragmentRow(r210, "botPermissionBiometry");
        Delegate r050 = this.delegate;
        if (r050 == null) goto L1239;
        r050.onCloseToTabs();
        return;
    L1239:
        return;
    L1287:
        return;
    L1286:
        return;
    L846:
        if (this.location != null) goto L848;
        BotLocation r051 = BotLocation.get(getContext(), this.currentAccount, this.botUser.id);
        this.location = r051;
        r051.listen(this.notifyLocationChecked);
    L848:
        this.locationRequestContext = r46;
        this.notifyLocationChecked.run();
        return;
    L851:
        if (this.botUser != null) goto L854;
        return;
    L854:
        if (this.secureStorage != null) goto L856;
        Context r49 = getContext();
        int r56 = this.currentAccount;
        this.secureStorage = new BotStorage(r49, r56, UserConfig.getInstance(r56).getClientUserId(), this.botUser.id, true);
    L856:
        getStorageKey(r46, this.secureStorage, r48, "secure_storage_key_received", "secure_storage_failed");
        return;
    L861:
        if (ignoreDialog(4) == true) goto L1144;
        int r410 = this.currentAccount;
        MyWebView r57 = this.webView;
        String[] r211 = {"cancelled"};
        AlertDialog.Builder r75 = new AlertDialog.Builder(getContext(), this.resourcesProvider);
        r75.setTitle(LocaleController.getString(R.string.ShareYouPhoneNumberTitle));
        SpannableStringBuilder r052 = new SpannableStringBuilder();
        String r315 = UserObject.getUserName(this.botUser);
        if (TextUtils.isEmpty(r315) == true) goto L870;
        r052.append(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.AreYouSureShareMyContactInfoWebapp, new Object[]{r315})));
    L872:
        if (MessagesController.getInstance(this.currentAccount).blockePeers.indexOfKey(this.botUser.id) < 0) goto L874;
        boolean r316 = true;
    L875:
        if (r316 == false) goto L877;
        r052.append("\n\n");
        r052.append(LocaleController.getString(R.string.AreYouSureShareMyContactInfoBotUnblock));
    L877:
        r75.setMessage(r052);
        r75.setPositiveButton(LocaleController.getString(R.string.ShareContact), new BotWebViewContainer$$ExternalSyntheticLambda28(this, r211, r316, r410, r57, r46));
        r75.setNegativeButton(LocaleController.getString(R.string.Cancel), new BotWebViewContainer$$ExternalSyntheticLambda29());
        showDialog(4, r75.create(), new BotWebViewContainer$$ExternalSyntheticLambda30(r211, r410, r57, r46));
        return;
    L874:
        r316 = false;
        goto L875
    L870:
        r052.append(AndroidUtilities.replaceTags(LocaleController.getString(R.string.AreYouSureShareMyContactInfoBot)));
        goto L872
    L1144:
        JSONObject r053 = new JSONObject();     // Catch: Exception -> L864
        r053.put("status", "cancelled");     // Catch: Exception -> L864
        notifyEvent(r46, "phone_requested", r053);     // Catch: Exception -> L864
        return;
    L864:
        e = move-exception;
        FileLog.e(e);
        return;
    L1080:
    L891:
        e = move-exception;
        FileLog.e(e);
        return;
    L881:
        if (this.hasQRPending == false) goto L883;
        return;
    L883:
        if (this.parentActivity == null) goto L1241;
        this.lastQrText = new JSONObject(r48).optString("text");     // Catch: JSONException -> L891
        this.hasQRPending = true;     // Catch: JSONException -> L891
        if (this.parentActivity.checkSelfPermission("android.permission.CAMERA") == 0) goto L889;
        NotificationCenter.getGlobalInstance().addObserver(new AnonymousClass4(this, r46), NotificationCenter.onRequestPermissionResultReceived);     // Catch: JSONException -> L891
        this.parentActivity.requestPermissions(new String[]{"android.permission.CAMERA"}, 5000);     // Catch: JSONException -> L891
        return;
    L889:
        openQrScanActivity();     // Catch: JSONException -> L891
        return;
    L1241:
        return;
    L1156:
        this.delegate.onWebAppSetupClosingBehavior(new JSONObject(r48).optBoolean("need_confirmation"));     // Catch: JSONException -> L896
        return;
    L896:
        e = move-exception;
        FileLog.e(e);
        return;
    L1146:
        JSONObject r411 = new JSONObject(r48);     // Catch: Exception -> L935
        boolean r243 = r411.optBoolean("is_active", false);     // Catch: Exception -> L935
        String r317 = r411.optString("text", this.lastSecondaryButtonText).trim();     // Catch: Exception -> L935
        if (r411.optBoolean("is_visible", false) == true) goto L902;
    L904:
        boolean r233 = false;
    L906:
        if (r411.has("color") == false) goto L908;
        int r212 = Color.parseColor(r411.optString("color"));     // Catch: Exception -> L935
    L910:
        if (r411.has("text_color") == false) goto L913;
        int r58 = Color.parseColor(r411.optString("text_color"));     // Catch: Exception -> L935
    L915:
        if (r411.optBoolean("is_progress_visible", false) == false) goto L918;
        if (r233 == false) goto L918;
        boolean r302 = true;
    L920:
        if (r411.optBoolean("has_shine_effect", false) == false) goto L923;
        if (r233 == false) goto L923;
        boolean r318 = true;
    L925:
        if (r411.has("position") == false) goto L927;
        String r67 = r411.optString("position");     // Catch: Exception -> L935
    L928:
        if (r67 != null) goto L1117;
        r67 = "left";
    L1117:
        long r262 = Long.parseLong(r411.getString("icon_custom_emoji_id"));     // Catch: Throwable -> L932
    L933:
        this.lastSecondaryButtonColor = r212;     // Catch: Exception -> L935
        this.lastSecondaryButtonTextColor = r58;     // Catch: Exception -> L935
        this.lastSecondaryButtonText = r317;     // Catch: Exception -> L935
        this.lastSecondaryButtonPosition = r67;     // Catch: Exception -> L935
        this.secondaryButtonData = r48;     // Catch: Exception -> L935
        this.delegate.onSetupSecondaryButton(r233, r243, r317, r262, r212, r58, r302, r318, r67);     // Catch: Exception -> L935
        return;
    L932:
        r262 = 0;
        goto L933
    L927:
        r67 = this.lastSecondaryButtonPosition;     // Catch: Exception -> L935
    L923:
        r318 = false;
    L918:
        r302 = false;
        goto L920
    L913:
        r58 = this.lastSecondaryButtonTextColor;     // Catch: Exception -> L935
        goto L915
    L908:
        r212 = this.lastSecondaryButtonColor;     // Catch: Exception -> L935
        goto L910
    L902:
        if (TextUtils.isEmpty(r317) == true) goto L904;
        r233 = true;
    L935:
        e = move-exception;
        FileLog.e(e);
        return;
    L938:
        if (this.isRequestingPageOpen == false) goto L940;
        return;
    L940:
        if (this.botUser != null) goto L942;
        return;
    L942:
        if ((System.currentTimeMillis() - this.lastClickMs) > NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L1290;
        JSONObject r319 = new JSONObject(r48);     // Catch: Exception -> L947
        long r132 = Long.parseLong(r319.getString("custom_emoji_id"));     // Catch: Exception -> L947
        int r412 = r319.getInt("duration");     // Catch: Exception -> L1072
    L949:
        TLRPC.User r054 = this.botUser;
        if (r054 != null) goto L953;
        notifyEvent(r46, "emoji_status_failed", obj("error", "UNKNOWN_ERROR"));
        return;
    L953:
        SetupEmojiStatusSheet.show(this.currentAccount, r054, r132, r412, new BotWebViewContainer$$ExternalSyntheticLambda13(this, r46));
        return;
    L948:
        r412 = 0;
    L947:
        r132 = 0;
        goto L948
    L1290:
        return;
    L1129:
        String r055 = new JSONObject(r48).optString("slug");     // Catch: JSONException -> L961
        if (this.currentPaymentSlug == null) goto L959;
        onInvoiceStatusUpdate(r055, "cancelled", true);     // Catch: JSONException -> L961
        return;
    L959:
        this.currentPaymentSlug = r055;     // Catch: JSONException -> L961
        TLRPC.TL_payments_getPaymentForm r213 = new TLRPC.TL_payments_getPaymentForm();     // Catch: JSONException -> L961
        TLRPC.TL_inputInvoiceSlug r320 = new TLRPC.TL_inputInvoiceSlug();     // Catch: JSONException -> L961
        r320.slug = r055;     // Catch: JSONException -> L961
        r213.invoice = r320;     // Catch: JSONException -> L961
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r213, new BotWebViewContainer$$ExternalSyntheticLambda25(this, r055, r320));     // Catch: JSONException -> L961
        return;
    L961:
        e = move-exception;
        FileLog.e(e);
        return;
    L1089:
    L1014:
        e = move-exception;
        FileLog.e(e);
        return;
    L965:
        if (this.currentDialog == null) goto L968;
        return;
    L968:
        if ((System.currentTimeMillis() - this.lastDialogClosed) > 150) goto L974;
        int r321 = this.dialogSequentialOpenTimes + 1;     // Catch: JSONException -> L1014
        this.dialogSequentialOpenTimes = r321;     // Catch: JSONException -> L1014
        if (r321 < 3) goto L974;
        this.dialogSequentialOpenTimes = 0;     // Catch: JSONException -> L1014
        this.lastDialogCooldownTime = System.currentTimeMillis();     // Catch: JSONException -> L1014
        return;
    L974:
        if ((System.currentTimeMillis() - this.lastDialogCooldownTime) <= 3000) goto L1250;
        JSONObject r322 = new JSONObject(r48);     // Catch: JSONException -> L1014
        String r056 = r322.optString("title", null);     // Catch: JSONException -> L1014
        String r413 = r322.getString("message");     // Catch: JSONException -> L1014
        JSONArray r323 = r322.getJSONArray("buttons");     // Catch: JSONException -> L1014
        AlertDialog.Builder r057 = new AlertDialog.Builder(getContext()).setTitle(r056).setMessage(r413);     // Catch: JSONException -> L1014
        ArrayList r414 = new ArrayList();     // Catch: JSONException -> L1014
        int r59 = 0;
    L978:
        if (r59 >= r323.length()) goto L981;
        r414.add(new PopupButton(r323.getJSONObject(r59)));     // Catch: JSONException -> L1014
        r59 = r59 + 1;     // Catch: JSONException -> L1014
        goto L978
    L981:
        if (r414.size() > 3) goto L1251;
        AtomicBoolean r324 = new AtomicBoolean();     // Catch: JSONException -> L1014
        if (r414.size() < 1) goto L987;
        PopupButton r510 = (PopupButton) r414.get(0);     // Catch: JSONException -> L1014
        r057.setPositiveButton(r510.text, new BotWebViewContainer$$ExternalSyntheticLambda4(this, r46, r510, r324));     // Catch: JSONException -> L1014
    L987:
        if (r414.size() < 2) goto L990;
        PopupButton r511 = (PopupButton) r414.get(1);     // Catch: JSONException -> L1014
        r057.setNegativeButton(r511.text, new BotWebViewContainer$$ExternalSyntheticLambda15(this, r46, r511, r324));     // Catch: JSONException -> L1014
    L990:
        if (r414.size() != 3) goto L992;
        PopupButton r512 = (PopupButton) r414.get(2);     // Catch: JSONException -> L1014
        r057.setNeutralButton(r512.text, new BotWebViewContainer$$ExternalSyntheticLambda23(this, r46, r512, r324));     // Catch: JSONException -> L1014
    L992:
        r057.setOnDismissListener(new BotWebViewContainer$$ExternalSyntheticLambda24(this, r324, r46));     // Catch: JSONException -> L1014
        this.currentDialog = r057.show();     // Catch: JSONException -> L1014
        if (r414.size() < 1) goto L1000;
        PopupButton r058 = (PopupButton) r414.get(0);     // Catch: JSONException -> L1014
        if (r058.textColorKey < 0) goto L1000;
        TextView r214 = (TextView) this.currentDialog.getButton(-1);     // Catch: JSONException -> L1014
        if (r214 == null) goto L1000;
        r214.setTextColor(getColor(r058.textColorKey));     // Catch: JSONException -> L1014
    L1000:
        if (r414.size() < 2) goto L1007;
        PopupButton r059 = (PopupButton) r414.get(1);     // Catch: JSONException -> L1014
        if (r059.textColorKey < 0) goto L1007;
        TextView r215 = (TextView) this.currentDialog.getButton(-2);     // Catch: JSONException -> L1014
        if (r215 == null) goto L1007;
        r215.setTextColor(getColor(r059.textColorKey));     // Catch: JSONException -> L1014
    L1007:
        if (r414.size() != 3) goto L1252;
        PopupButton r060 = (PopupButton) r414.get(2);     // Catch: JSONException -> L1014
        if (r060.textColorKey < 0) goto L1253;
        TextView r216 = (TextView) this.currentDialog.getButton(-3);     // Catch: JSONException -> L1014
        if (r216 == null) goto L1254;
        r216.setTextColor(getColor(r060.textColorKey));     // Catch: JSONException -> L1014
        return;
    L1254:
        return;
    L1253:
        return;
    L1252:
        return;
    L1251:
        return;
    L1250:
        return;
    L1017:
        if (this.isRequestingPageOpen == false) goto L1019;
        return;
    L1019:
        if (this.botUser != null) goto L1021;
        return;
    L1021:
        if ((System.currentTimeMillis() - this.lastClickMs) <= NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L1024;
        return;
    L1024:
        if (this.downloads != null) goto L1162;
        this.downloads = BotDownloads.get(getContext(), this.currentAccount, this.botUser.id);
    L1162:
        JSONObject r325 = new JSONObject(r48);     // Catch: Exception -> L1033
        String r061 = r325.getString("url");     // Catch: Exception -> L1033
        String r326 = r325.getString("file_name");     // Catch: Exception -> L1033
        if (this.downloads.getCached(r061) == null) goto L1031;
        this.downloads.download(r061, r326);
        notifyEvent(r46, "file_download_requested", obj("status", "downloading"));
        return;
    L1031:
        TL_bots.checkDownloadFileParams r415 = new TL_bots.checkDownloadFileParams();
        r415.bot = MessagesController.getInstance(this.currentAccount).getInputUser(this.botUser);
        r415.file_name = r326;
        r415.url = r061;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r415, new BotWebViewContainer$$ExternalSyntheticLambda18(this, r46, r061, r326));
        return;
    L1033:
        e = move-exception;
        FileLog.e(e);
        notifyEvent(r46, "file_download_requested", obj("status", "cancelled"));
        return;
    L1138:
        JSONObject r217 = new JSONObject(r48);     // Catch: Exception -> L1043
        Uri r062 = Uri.parse(r217.optString("url"));     // Catch: Exception -> L1043
        String r327 = r217.optString("try_browser");     // Catch: Exception -> L1043
        if (MessagesController.getInstance(this.currentAccount).webAppAllowedProtocols != null) goto L1040;
        return;
    L1040:
        if (MessagesController.getInstance(this.currentAccount).webAppAllowedProtocols.contains(r062.getScheme()) == false) goto L1259;
        onOpenUri(r062, r327, r217.optBoolean("try_instant_view"), true, false);     // Catch: Exception -> L1043
        return;
    L1259:
        return;
    L1043:
        e = move-exception;
        FileLog.e(e);
        return;
    L1048:
        if (this.hasQRPending == false) goto L1261;
        BottomSheet r063 = this.cameraBottomSheet;
        if (r063 == null) goto L1262;
        r063.dismiss();
        return;
    L1262:
        return;
    L1261:
        return;
    L1054:
        if (this.botUser == null) goto L1292;
        JSONObject r328 = new JSONObject(r48);     // Catch: Exception -> L1059
        String r064 = r328.getString("req_id");     // Catch: Exception -> L1059
        String r416 = r328.getString("method");     // Catch: Exception -> L1059
        String r329 = r328.get(PluginsConstants.PARAMS).toString();     // Catch: Exception -> L1059
        int r513 = this.currentAccount;
        MyWebView r68 = this.webView;
        TL_bots.invokeWebViewCustomMethod r76 = new TL_bots.invokeWebViewCustomMethod();
        r76.bot = MessagesController.getInstance(r513).getInputUser(this.botUser.id);
        r76.custom_method = r416;
        TLRPC.TL_dataJSON r417 = new TLRPC.TL_dataJSON();
        r76.params = r417;
        r417.data = r329;
        ConnectionsManager.getInstance(r513).sendRequest(r76, new BotWebViewContainer$$ExternalSyntheticLambda27(this, r064, r513, r68, r46));
        return;
    L1059:
        e = move-exception;
        FileLog.e(e);
        if ((e instanceof JSONException) == false) goto L1063;
        error("JSON Parse error");
        return;
    L1063:
        unknownError();
        return;
    L1292:
        return;
    L18:
        if (r47.equals("web_app_expand") == false) goto L16;
        r7 = '?';
        goto L273
    L22:
        if (r47.equals("web_app_request_write_access") == false) goto L16;
        r7 = Typography.greater;
        goto L273
    L26:
        if (r47.equals("web_app_set_background_color") == false) goto L16;
        r7 = SignatureVisitor.INSTANCEOF;
        goto L273
    L30:
        if (r47.equals("web_app_request_safe_area") == false) goto L16;
        r7 = Typography.less;
        goto L273
    L34:
        if (r47.equals("web_app_set_header_color") == false) goto L16;
        r7 = ';';
        goto L273
    L38:
        if (r47.equals("web_app_set_bottom_bar_color") == false) goto L16;
        r7 = ':';
        goto L273
    L42:
        if (r47.equals("web_app_biometry_update_token") == false) goto L16;
        r7 = '9';
        goto L273
    L46:
        if (r47.equals("web_app_start_device_orientation") == false) goto L16;
        r7 = '8';
        goto L273
    L50:
        if (r47.equals("web_app_request_chat") == false) goto L16;
        r7 = '7';
        goto L273
    L54:
        if (r47.equals("web_app_check_home_screen") == false) goto L16;
        r7 = '6';
        goto L273
    L58:
        if (r47.equals("web_app_setup_settings_button") == false) goto L16;
        r7 = '5';
        goto L273
    L62:
        if (r47.equals("web_app_setup_swipe_behavior") == false) goto L16;
        r7 = '4';
        goto L273
    L66:
        if (r47.equals("web_app_setup_main_button") == false) goto L16;
        r7 = '3';
        goto L273
    L70:
        if (r47.equals("web_app_trigger_haptic_feedback") == false) goto L16;
        r7 = '2';
        goto L273
    L74:
        if (r47.equals("web_app_biometry_request_access") == false) goto L16;
        r7 = '1';
        goto L273
    L78:
        if (r47.equals("web_app_setup_back_button") == false) goto L16;
        r7 = '0';
        goto L273
    L82:
        if (r47.equals("web_app_open_location_settings") == false) goto L16;
        r7 = '/';
        goto L273
    L86:
        if (r47.equals("web_app_verify_age") == false) goto L16;
        r7 = '.';
        goto L273
    L90:
        if (r47.equals("web_app_exit_fullscreen") == false) goto L16;
        r7 = SignatureVisitor.SUPER;
        goto L273
    L94:
        if (r47.equals("web_app_secure_storage_save_key") == false) goto L16;
        r7 = ',';
        goto L273
    L98:
        if (r47.equals("web_app_switch_inline_query") == false) goto L16;
        r7 = SignatureVisitor.EXTENDS;
        goto L273
    L102:
        if (r47.equals("web_app_request_fullscreen") == false) goto L16;
        r7 = '*';
        goto L273
    L106:
        if (r47.equals("web_app_add_to_home_screen") == false) goto L16;
        r7 = ')';
        goto L273
    L110:
        if (r47.equals("web_app_request_content_safe_area") == false) goto L16;
        r7 = '(';
        goto L273
    L114:
        if (r47.equals("web_app_data_send") == false) goto L16;
        r7 = '\'';
        goto L273
    L118:
        if (r47.equals("web_app_send_prepared_message") == false) goto L16;
        r7 = Typography.amp;
        goto L273
    L122:
        if (r47.equals("web_app_stop_accelerometer") == false) goto L16;
        r7 = '%';
        goto L273
    L126:
        if (r47.equals("web_app_start_accelerometer") == false) goto L16;
        r7 = Typography.dollar;
        goto L273
    L130:
        if (r47.equals("web_app_device_storage_clear") == false) goto L16;
        r7 = '#';
        goto L273
    L134:
        if (r47.equals("web_app_secure_storage_clear") == false) goto L16;
        r7 = Typography.quote;
        goto L273
    L138:
        if (r47.equals("web_app_stop_gyroscope") == false) goto L16;
        r7 = '!';
        goto L273
    L142:
        if (r47.equals("web_app_hide_keyboard") == false) goto L16;
        r7 = ' ';
        goto L273
    L146:
        if (r47.equals("web_app_read_text_from_clipboard") == false) goto L16;
        r7 = 31;
        goto L273
    L150:
        if (r47.equals("web_app_ready") == false) goto L16;
        r7 = 30;
        goto L273
    L154:
        if (r47.equals("web_app_close") == false) goto L16;
        r7 = 29;
        goto L273
    L158:
        if (r47.equals("web_app_start_gyroscope") == false) goto L16;
        r7 = 28;
        goto L273
    L162:
        if (r47.equals("web_app_request_location") == false) goto L16;
        r7 = 27;
        goto L273
    L166:
        if (r47.equals("web_app_share_to_story") == false) goto L16;
        r7 = 26;
        goto L273
    L170:
        if (r47.equals("web_app_secure_storage_restore_key") == false) goto L16;
        r7 = 25;
        goto L273
    L174:
        if (r47.equals("web_app_open_tg_link") == false) goto L16;
        r7 = 24;
        goto L273
    L178:
        if (r47.equals("web_app_allow_scroll") == false) goto L16;
        r7 = 23;
        goto L273
    L182:
        if (r47.equals("web_app_toggle_orientation_lock") == false) goto L16;
        r7 = 22;
        goto L273
    L186:
        if (r47.equals("web_app_biometry_request_auth") == false) goto L16;
        r7 = 21;
        goto L273
    L190:
        if (r47.equals("web_app_device_storage_get_key") == false) goto L16;
        r7 = 20;
        goto L273
    L194:
        if (r47.equals("web_app_device_storage_save_key") == false) goto L16;
        r7 = 19;
        goto L273
    L198:
        if (r47.equals("web_app_stop_device_orientation") == false) goto L16;
        r7 = 18;
        goto L273
    L202:
        if (r47.equals("web_app_request_emoji_status_access") == false) goto L16;
        r7 = 17;
        goto L273
    L206:
        if (r47.equals("web_app_request_viewport") == false) goto L16;
        r7 = 16;
        goto L273
    L210:
        if (r47.equals("web_app_biometry_open_settings") == false) goto L16;
        r7 = 15;
        goto L273
    L214:
        if (r47.equals("web_app_check_location") == false) goto L16;
        r7 = 14;
        goto L273
    L218:
        if (r47.equals("web_app_secure_storage_get_key") == false) goto L16;
        r7 = '\r';
        goto L273
    L222:
        if (r47.equals("web_app_request_theme") == false) goto L16;
        r7 = '\f';
        goto L273
    L226:
        if (r47.equals("web_app_request_phone") == false) goto L16;
        r7 = 11;
        goto L273
    L230:
        if (r47.equals("web_app_open_scan_qr_popup") == false) goto L16;
        r7 = '\n';
        goto L273
    L234:
        if (r47.equals("web_app_setup_closing_behavior") == false) goto L16;
        r7 = '\t';
        goto L273
    L238:
        if (r47.equals("web_app_setup_secondary_button") == false) goto L16;
        r7 = '\b';
        goto L273
    L242:
        if (r47.equals("web_app_set_emoji_status") == false) goto L16;
        r7 = 7;
        goto L273
    L246:
        if (r47.equals("web_app_open_invoice") == false) goto L16;
        r7 = 6;
        goto L273
    L250:
        if (r47.equals("web_app_open_popup") == false) goto L16;
        r7 = 5;
        goto L273
    L254:
        if (r47.equals("web_app_request_file_download") == false) goto L16;
        r7 = 4;
        goto L273
    L258:
        if (r47.equals("web_app_open_link") == false) goto L16;
        r7 = 3;
        goto L273
    L262:
        if (r47.equals("web_app_biometry_get_info") == false) goto L16;
        r7 = 2;
        goto L273
    L266:
        if (r47.equals("web_app_close_scan_qr_popup") == false) goto L16;
        r7 = 1;
        goto L273
    L270:
        if (r47.equals("web_app_invoke_custom_method") == false) goto L16;
        r7 = 0;
        goto L273
    }

    private void onOpenUri(Uri r8) {
        onOpenUri(r8, null, !this.bot, false, false);
    }

    private void onOpenUri(Uri r13, String r14, boolean r15, boolean r16, boolean r17) {
        if (this.isRequestingPageOpen == false) goto L5;
        return;
    L5:
        if ((System.currentTimeMillis() - this.lastClickMs) <= NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L8;
        if (r16 == false) goto L8;
        return;
    L8:
        this.lastClickMs = 0;
        boolean[] r0 = {false};
        if (Browser.isInternalUri(r13, r0) == true) goto L11;
    L15:
        Browser.openUrl(getContext(), r13, true, r15, false, null, r14, false, true, r17);
        return;
    L11:
        if (r0[0] == true) goto L15;
        if (this.delegate == null) goto L15;
        setKeyboardFocusable(false);
        goto L15
    }

    private void onWebEventReceived(String r9, String r10) {
        if (this.bot == false) goto L6;
        return;
    L6:
        if (this.delegate != null) goto L9;
        return;
    L9:
        if (this.trustedOrigin != null) goto L11;
    L14:
        d("onWebEventReceived " + r9 + " " + r10);
        r9.getClass();
        boolean r5 = true;
        char r6 = 65535;
        switch(r9.hashCode()) {
            case -1695046810: goto L34;
            case -462720700: goto L30;
            case 479731943: goto L26;
            case 675009138: goto L22;
            case 997530486: goto L18;
            default: goto L37;
        };
    L37:
        switch(r6) {
            case 0: goto L72;
            case 1: goto L72;
            case 2: goto L50;
            case 3: goto L46;
            case 4: goto L78;
            default: goto L87;
        };
    L46:
        d("siteName " + r10);
        MyWebView r8 = this.webView;
        if (r8 == null) goto L82;
        r8.lastSiteName = r10;
        MyWebView.m22029$$Nest$msaveHistory(r8);
        return;
    L82:
        return;
    L50:
        d("oauth_request " + r10);
        if (this.webView == null) goto L88;
        String r92 = getOriginHost();
        if (TextUtils.isEmpty(r92) == true) goto L89;
        String r102 = new JSONObject(r10).optString("url");     // Catch: Exception -> L60
        notifyEvent("oauth_supported", obj("version", 1));     // Catch: Exception -> L60
        if (TextUtils.isEmpty(r102) == true) goto L83;
        TLRPC.TL_messages_requestUrlAuth r0 = new TLRPC.TL_messages_requestUrlAuth();     // Catch: Exception -> L60
        r0.url = r102;     // Catch: Exception -> L60
        int r1 = r0.flags;     // Catch: Exception -> L60
        r0.in_app_origin = r92;     // Catch: Exception -> L60
        r0.flags = r1 | 12;     // Catch: Exception -> L60
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new BotWebViewContainer$$ExternalSyntheticLambda32(this, r0, r102, r92), 2);     // Catch: Exception -> L60
        return;
    L83:
        return;
    L60:
        e = move-exception;
        FileLog.e(e);
        return;
    L89:
        return;
    L88:
        return;
    L72:
        JSONArray r02 = new JSONArray(r10);     // Catch: Exception -> L71
        boolean r93 = TextUtils.equals(r9, "actionBarColor");     // Catch: Exception -> L71
        int r103 = Color.argb((int) Math.round(r02.optDouble(3, 1.0d) * 255.0d), (int) Math.round(r02.optDouble(0)), (int) Math.round(r02.optDouble(1)), (int) Math.round(r02.optDouble(2)));     // Catch: Exception -> L71
        MyWebView r03 = this.webView;     // Catch: Exception -> L71
        if (r03 == null) goto L68;
        if (r93 == false) goto L66;
        r03.lastActionBarColorGot = true;     // Catch: Exception -> L71
        r03.lastActionBarColor = r103;     // Catch: Exception -> L71
    L67:
        MyWebView.m22029$$Nest$msaveHistory(r03);     // Catch: Exception -> L71
        goto L68
    L66:
        r03.lastBackgroundColorGot = true;     // Catch: Exception -> L71
        r03.lastBackgroundColor = r103;     // Catch: Exception -> L71
    L68:
        this.delegate.onWebAppBackgroundChanged(r93, r103);     // Catch: Exception -> L71
        return;
    L90:
        return;
    L78:
        JSONArray r94 = new JSONArray(r10);     // Catch: Exception -> L42
        boolean r104 = r94.optBoolean(0, true);     // Catch: Exception -> L42
        r5 = r94.optBoolean(1, true);     // Catch: Exception -> L70
    L44:
        if ((getParent() instanceof ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer) == false) goto L80;
        ((ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer) getParent()).allowThisScroll(r104, r5);
        return;
    L80:
        return;
    L42:
        r104 = true;
        goto L44
    L87:
        return;
    L18:
        if (r9.equals("allowScroll") == false) goto L37;
        r6 = 4;
        goto L37
    L22:
        if (r9.equals("siteName") == false) goto L37;
        r6 = 3;
        goto L37
    L26:
        if (r9.equals("oauth_request") == false) goto L37;
        r6 = 2;
        goto L37
    L30:
        if (r9.equals("navigationBarColor") == false) goto L37;
        r6 = 1;
        goto L37
    L34:
        if (r9.equals("actionBarColor") == false) goto L37;
        r6 = 0;
        goto L37
    L11:
        if (TextUtils.equals(getOriginHost(), this.trustedOrigin) == true) goto L14;
        d("onWebEventReceived ignore " + r9);
    }

    private void openQrScanActivity() {
        Activity r0 = this.parentActivity;
        if (r0 != null) goto L5;
        return;
    L5:
        this.cameraBottomSheet = CameraScanActivity.showAsSheet(r0, false, 3, new AnonymousClass8(this));
    }

    public static WebResourceResponse proxyTON(WebResourceRequest r2) {
        return proxyTON(r2.getMethod(), r2.getUrl().toString(), r2.getRequestHeaders());
    }

    public static WebResourceResponse proxyTON(String r2, String r3, Map<String, String> r4) {
        HttpURLConnection r32 = (HttpURLConnection) new URL(Browser.replaceHostname(Uri.parse(r3), rotateTONHost(AndroidUtilities.getHostAuthority(r3)), "https")).openConnection();     // Catch: Exception -> L10
        r32.setRequestMethod(r2);     // Catch: Exception -> L10
        if (r4 == null) goto L8;
        Iterator<Map.Entry<String, String>> r22 = r4.entrySet().iterator();     // Catch: Exception -> L10
    L6:
        if (r22.hasNext() == false) goto L8;
        Map.Entry<String, String> r42 = r22.next();     // Catch: Exception -> L10
        r32.addRequestProperty(r42.getKey(), r42.getValue());     // Catch: Exception -> L10
    L8:
        r32.connect();     // Catch: Exception -> L10
        InputStream r23 = r32.getInputStream();     // Catch: Exception -> L10
        return new WebResourceResponse(r32.getContentType().split(";", 2)[0], r32.getContentEncoding(), r23);
    L10:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    private void reportSafeContentInsets(int r10, boolean r11) {
        if (r11 == false) goto L5;
    L7:
        notifyEvent("content_safe_area_changed", obj("left", 0, "top", Float.valueOf(r10 / AndroidUtilities.density), "right", 0, "bottom", 0));
        this.lastInsetsTopMargin = r10;
        return;
    L5:
        if (r10 != this.lastInsetsTopMargin) goto L7;
    }

    private void reportSafeInsets(Rect r9, boolean r10) {
        if (r9 == null) goto L9;
        if (r10 == false) goto L5;
    L7:
        notifyEvent("safe_area_changed", obj("left", Float.valueOf(r9.left / AndroidUtilities.density), "top", Float.valueOf(r9.top / AndroidUtilities.density), "right", Float.valueOf(r9.right / AndroidUtilities.density), "bottom", Float.valueOf(r9.bottom / AndroidUtilities.density)));
        this.lastInsets.set(r9);
        return;
    L5:
        if (this.lastInsets.equals(r9) == false) goto L7;
        return;
    }

    private void restoreStorageKey(WebRequestContext r11, BotStorage r12, String r13, String r14, String r15) {
        if (r12 != null) goto L5;
        return;
    L5:
        if (this.botUser == null) goto L37;
        JSONObject r4 = new JSONObject(r13);     // Catch: Exception -> L24
        String r132 = r4.getString("req_id");     // Catch: Exception -> L24
        String r6 = r4.optString(PluginsConstants.Settings.KEY);     // Catch: Exception -> L22
        if (r6 != null) goto L29;
        notifyEvent(r11, r15, obj("req_id", r132, "error", "KEY_INVALID"));
        return;
    L29:
        List<BotStorage.StorageConfig> r8 = r12.getStoragesWithKey(r6);     // Catch: Exception -> L19
        if (r8.isEmpty() == false) goto L17;
        notifyEvent(r11, r15, obj("req_id", r132, "error", "RESTORE_UNAVAILABLE"));
        return;
    L17:
        r12.showChooseStorage(getContext(), r8, new BotWebViewContainer$$ExternalSyntheticLambda33(this, r11, r15, r132, r12, r6, r14));
        return;
    L19:
        e = move-exception;
        notifyEvent(r11, r15, obj("req_id", r132, "error", e.getMessage()));
        return;
    L22:
        notifyEvent(r11, r15, obj("req_id", r132, "error", "KEY_INVALID"));
        return;
    L24:
        e = move-exception;
        FileLog.e(e);
        if (TextUtils.isEmpty(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L36;
        notifyEvent(r11, r15, obj("req_id", _UrlKt.FRAGMENT_ENCODE_SET, "error", "UNKNOWN_ERROR"));
        return;
    L36:
        return;
    }

    public static String rotateTONHost(String r5) {
        r5 = IDN.toASCII(r5, 1);     // Catch: Exception -> L5
    L7:
        String[] r52 = r5.split("\\.");
        StringBuilder r0 = new StringBuilder();
        int r1 = 0;
    L9:
        if (r1 >= r52.length) goto L13;
        if (r1 <= 0) goto L12;
        r0.append("-d");
    L12:
        r0.append(r52[r1].replaceAll("\\-", "-h"));
        r1 = r1 + 1;
        goto L9
    L13:
        r0.append(".");
        r0.append(MessagesController.getInstance(UserConfig.selectedAccount).tonProxyAddress);
        return r0.toString();
    L5:
        e = move-exception;
        FileLog.e(e);
        goto L7
    }

    private void runWithPermissions(String[] r2, Consumer<Boolean> r3) {
        if (checkPermissions(r2) == false) goto L6;
        r3.accept(Boolean.TRUE);
        return;
    L6:
        this.onPermissionsRequestResultCallback = new BotWebViewContainer$$ExternalSyntheticLambda54(this, r3, r2);
        Activity r1 = this.parentActivity;
        if (r1 == null) goto L10;
        r1.requestPermissions(r2, 4000);
        return;
    }

    private void setStorageKey(WebRequestContext r6, BotStorage r7, String r8, String r9, String r10) {
        if (r7 != null) goto L5;
        return;
    L5:
        if (this.botUser == null) goto L38;
        JSONObject r3 = new JSONObject(r8);     // Catch: Exception -> L23
        String r82 = r3.getString("req_id");     // Catch: Exception -> L23
        String r4 = r3.optString(PluginsConstants.Settings.KEY);     // Catch: Exception -> L21
        if (r4 != null) goto L32;
        notifyEvent(r6, r10, obj("req_id", r82, "error", "KEY_INVALID"));
        return;
    L32:
        r7.setKey(r4, r3.optString("value"));     // Catch: RuntimeException -> L16
        notifyEvent(r6, r9, obj("req_id", r82));
        return;
    L16:
        e = move-exception;
        notifyEvent(r6, r10, obj("req_id", r82, "error", e.getMessage()));
        return;
    L19:
        notifyEvent(r6, r10, obj("req_id", r82, "error", "VALUE_INVALID"));
        return;
    L21:
        notifyEvent(r6, r10, obj("req_id", r82, "error", "KEY_INVALID"));
        return;
    L23:
        e = move-exception;
        FileLog.e(e);
        if (TextUtils.isEmpty(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L37;
        notifyEvent(r6, r10, obj("req_id", _UrlKt.FRAGMENT_ENCODE_SET, "error", "UNKNOWN_ERROR"));
        return;
    L37:
        return;
    }

    private void setupBotWebMessageBridge() {
        MyWebView r0 = this.webView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.bot == false) goto L23;
        r0.removeJavascriptInterface("TelegramWebviewProxy");
        if (WebViewFeature.isFeatureSupported("WEB_MESSAGE_LISTENER") == true) goto L11;
        d("Bot WebMessageListener is unsupported; native bridge disabled");
        return;
    L11:
        Set r02 = Collections.singleton("*");
        if (MyWebView.m22006$$Nest$fgetbotWebMessageListenerAdded(this.webView) == true) goto L15;
        MyWebView r1 = this.webView;
        WebViewCompat.addWebMessageListener(r1, "TelegramWebviewProxyMessage", r02, new BotWebViewContainer$$ExternalSyntheticLambda3(r1));
        MyWebView.m22017$$Nest$fputbotWebMessageListenerAdded(this.webView, true);
    L15:
        if (WebViewFeature.isFeatureSupported("DOCUMENT_START_SCRIPT") == true) goto L17;
        return;
    L17:
        if (MyWebView.m22007$$Nest$fgetbotWebMessageShimHandler(this.webView) != null) goto L22;
        MyWebView r4 = this.webView;
        MyWebView.m22018$$Nest$fputbotWebMessageShimHandler(r4, WebViewCompat.addDocumentStartJavaScript(r4, "window.TelegramWebviewProxy={postEvent:function(eventType,eventData){window.TelegramWebviewProxyMessage.postMessage(JSON.stringify({eventType:eventType,eventData:eventData}));}};", r02));
        return;
    L22:
        return;
    }

    private void setupFlickerParams(boolean r3) {
        this.isFlickeringCenter = r3;
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) this.flickerView.getLayoutParams();
        if (r3 == false) goto L5;
        int r1 = 17;
    L6:
        r0.gravity = r1;
        if (r3 == false) goto L9;
        int r32 = AndroidUtilities.dp(100.0f);
        r0.height = r32;
        r0.width = r32;
    L10:
        this.flickerView.requestLayout();
        return;
    L9:
        r0.width = -1;
        r0.height = -2;
        goto L10
    L5:
        r1 = 48;
        goto L6
    }

    private void setupWebView(MyWebView r2) {
        setupWebView(r2, null);
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    private void setupWebView(MyWebView r11, Object r12) {
        MyWebView r2 = this.webView;
        if (r2 == null) goto L5;
        r2.destroy();
        removeView(this.webView);
    L5:
        if (r11 == null) goto L91;
        AndroidUtilities.removeFromParent(r11);
    L91:
    L13:
        e = move-exception;
        FileLog.e(e);
    L19:
        if (r11 != null) goto L27;
        Context r5 = getContext();
        boolean r6 = this.bot;
        long r7 = 0;
        if (r6 == false) goto L26;
        TLRPC.User r9 = this.botUser;
        if (r9 == null) goto L26;
        r7 = r9.id;
    L26:
        MyWebView r4 = new MyWebView(r5, r6, r7);
    L28:
        this.webView = r4;
        if (this.bot == true) goto L31;
        CookieManager r42 = CookieManager.getInstance();
        r42.setAcceptCookie(true);
        r42.setAcceptThirdPartyCookies(this.webView, true);
        CookieManager.getInstance().flush();
        this.webView.opener = this.opener;
    L33:
        if (MessagesController.getInstance(this.currentAccount).disableBotFullscreenBlur == true) goto L35;
        this.webView.setLayerType(2, null);
    L35:
        this.webView.setContainers(this, this.webViewScrollListener);
        this.webView.setCloseListener(this.onCloseListener);
        WebSettings r43 = this.webView.getSettings();
        r43.setJavaScriptEnabled(true);
        r43.setGeolocationEnabled(true);
        r43.setDomStorageEnabled(true);
        r43.setDatabaseEnabled(true);
        r43.setSupportMultipleWindows(true);
        r43.setAllowFileAccess(false);
        r43.setAllowContentAccess(false);
        r43.setAllowFileAccessFromFileURLs(false);
        r43.setAllowUniversalAccessFromFileURLs(false);
        if (this.bot == true) goto L41;
        r43.setRenderPriority(WebSettings.RenderPriority.HIGH);
        r43.setCacheMode(-1);
        r43.setSaveFormData(true);
        r43.setSavePassword(true);
        r43.setSupportZoom(true);
        r43.setBuiltInZoomControls(true);
        r43.setDisplayZoomControls(false);
        r43.setUseWideViewPort(true);
        r43.setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT < 26) goto L41;
        r43.setSafeBrowsingEnabled(true);
    L41:
        if (isVerifyingAge() == false) goto L89;
        r43.setMediaPlaybackRequiresUserGesture(false);
    L89:
        String r52 = r43.getUserAgentString().replace("; wv)", ")");     // Catch: Exception -> L48
        StringBuilder r72 = new StringBuilder("(Linux; Android ");     // Catch: Exception -> L48
        String r1 = Build.VERSION.RELEASE;     // Catch: Exception -> L48
        r72.append(r1);     // Catch: Exception -> L48
        r72.append("; K)");     // Catch: Exception -> L48
        String r53 = r52.replaceAll("\\(Linux; Android.+;[^)]+\\)", r72.toString()).replaceAll("Version/[\\d\\.]+ ", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Exception -> L48
        if (this.bot == false) goto L54;
        PackageInfo r62 = ApplicationLoader.applicationContext.getPackageManager().getPackageInfo(ApplicationLoader.applicationContext.getPackageName(), 0);     // Catch: Exception -> L48
        int r73 = SharedConfig.getDevicePerformanceClass();     // Catch: Exception -> L48
        if (r73 != 0) goto L50;
        String r3 = "LOW";
    L53:
        r53 = r53 + " Telegram-Android/" + r62.versionName + " (" + capitalizeFirst(Build.MANUFACTURER) + " " + Build.MODEL + "; Android " + r1 + "; SDK " + Build.VERSION.SDK_INT + "; " + r3 + ")";     // Catch: Exception -> L48
        goto L54
    L50:
        if (r73 != 1) goto L52;
        r3 = "AVERAGE";
        goto L53
    L52:
        r3 = "HIGH";
    L54:
        r43.setUserAgentString(r53);     // Catch: Exception -> L48
    L57:
        r43.setTextSize(WebSettings.TextSize.NORMAL);
        File r0 = new File(ApplicationLoader.getFilesDirFixed(), "webview_database");
        if (r0.exists() == false) goto L62;
        if (r0.isDirectory() == false) goto L62;
    L63:
        r43.setDatabasePath(r0.getAbsolutePath());
    L64:
        GeolocationPermissions.getInstance().clearAll();
        this.webView.setVerticalScrollBarEnabled(false);
        if (r11 == null) goto L67;
    L69:
        addView(this.webView);
        if (this.bot == false) goto L79;
        if ((r12 instanceof BotWebViewProxy) == false) goto L75;
        this.botWebViewProxy = (BotWebViewProxy) r12;
    L75:
        if (this.botWebViewProxy != null) goto L77;
        this.botWebViewProxy = new BotWebViewProxy(this);
    L77:
        this.botWebViewProxy.setContainer(this);
        setupBotWebMessageBridge();
    L87:
        onWebViewCreated(this.webView);
        firstWebView = false;
        return;
    L79:
        if ((r12 instanceof WebViewProxy) == false) goto L81;
        this.webViewProxy = (WebViewProxy) r12;
    L81:
        WebViewProxy r122 = this.webViewProxy;
        if (r122 != null) goto L84;
        WebViewProxy r112 = new WebViewProxy(this.webView, this);
        this.webViewProxy = r112;
        this.webView.addJavascriptInterface(r112, "TelegramWebviewProxy");
    L86:
        this.webViewProxy.setContainer(this);
        goto L87
    L84:
        if (r11 != null) goto L86;
        this.webView.addJavascriptInterface(r122, "TelegramWebviewProxy");
        goto L86
    L67:
        if (this.bot == false) goto L69;
        this.webView.setAlpha(0.0f);
    L62:
        if (r0.mkdirs() == false) goto L64;
    L48:
        e = move-exception;
        FileLog.e(e);
        goto L57
    L31:
        r4.setBackgroundColor(getColor(Theme.key_windowBackgroundWhite));
        goto L33
    L27:
        r4 = r11;
        goto L28
    L9:
        if (SharedConfig.debugWebView == true) goto L11;
    L15:
        boolean r44 = false;
    L16:
        WebView.setWebContentsDebuggingEnabled(r44);     // Catch: Exception -> L13
        goto L19
    L11:
        if (isVerifyingAge() == true) goto L15;
        r44 = true;
        goto L16
    }

    private boolean showDialog(int r3, AlertDialog r4, Runnable r5) {
        if (r4 != null) goto L5;
    L12:
        return false;
    L5:
        if (ignoreDialog(r3) == true) goto L12;
        r4.setOnDismissListener(new BotWebViewContainer$$ExternalSyntheticLambda36(this, r5));
        this.currentDialog = r4;
        r4.setDismissDialogByButtons(false);
        this.currentDialog.show();
        if (this.lastDialogType == r3) goto L10;
        this.lastDialogType = r3;
        this.shownDialogsCount = 0;
        this.blockedDialogsUntil = 0;
    L10:
        this.shownDialogsCount++;
        return true;
    }

    private static String tonsite2magic(String r3) {
        if (r3 != null) goto L5;
        return r3;
    L5:
        if (isTonsite(Uri.parse(r3)) == false) goto L16;
        String r0 = AndroidUtilities.getHostAuthority(r3);
        r0 = IDN.toASCII(r0, 1);     // Catch: Exception -> L13
    L8:
        String r1 = rotateTONHost(r0);
        if (rotatedTONHosts != null) goto L11;
        rotatedTONHosts = new HashMap();
    L11:
        rotatedTONHosts.put(r1, r0);
        return Browser.replaceHostname(Uri.parse(r3), r1, "https");
    L16:
        return r3;
    }

    private void unknownError() {
        unknownError(null);
    }

    private void unknownError(String r4) {
        StringBuilder r0 = new StringBuilder();
        r0.append(LocaleController.getString("UnknownError", R.string.UnknownError));
        if (r4 == null) goto L5;
        String r42 = ": ".concat(r4);
    L6:
        r0.append(r42);
        error(r0.toString());
        return;
    L5:
        r42 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    private void updateKeyboardFocusable() {
        if (this.wasFocusable == false) goto L8;
        setDescendantFocusability(org.mvel2.asm.Opcodes.ASM6);
        setFocusable(false);
        MyWebView r2 = this.webView;
        if (r2 == null) goto L7;
        r2.setDescendantFocusability(org.mvel2.asm.Opcodes.ASM6);
        this.webView.clearFocus();
    L7:
        AndroidUtilities.hideKeyboard(this);
    L8:
        this.wasFocusable = false;
    }

    public void checkCreateWebView() {
        if (this.webView == null) goto L5;
        return;
    L5:
        if (this.webViewNotAvailable == false) goto L14;
        return;
    L14:
        setupWebView(null);     // Catch: Throwable -> L9
        return;
    L9:
        th = move-exception;
        FileLog.e(th);
        this.flickerView.setVisibility(8);
        this.webViewNotAvailable = true;
        this.webViewNotAvailableText.setVisibility(0);
        if (this.webView == null) goto L18;
        removeView(this.webView);
        return;
    }

    public void d(String r3) {
        FileLog.d("[webviewcontainer] #" + this.tag + " " + r3);
    }

    public void destroyWebView() {
        d("destroyWebView preserving=" + this.preserving);
        MyWebView r0 = this.webView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getParent() == null) goto L8;
        removeView(this.webView);
    L8:
        if (this.preserving == true) goto L10;
        this.webView.destroy();
        onWebViewDestroyed(this.webView);
    L10:
        this.isPageLoaded = false;
        updateKeyboardFocusable();
        if (this.biometry == null) goto L14;
        this.biometry = null;
    L14:
        if (this.storage == null) goto L17;
        this.storage = null;
    L17:
        if (this.secureStorage == null) goto L19;
        this.secureStorage = null;
    L19:
        BotLocation r02 = this.location;
        if (r02 == null) goto L24;
        r02.unlisten(this.notifyLocationChecked);
        this.location = null;
        this.locationRequestContext = null;
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r4, int r5, Object... r6) {
        if (r4 != NotificationCenter.didSetNewTheme) goto L16;
        MyWebView r42 = this.webView;
        if (r42 == null) goto L8;
        r42.setBackgroundColor(getColor(Theme.key_windowBackgroundWhite));
    L8:
        if (this.flickerViewColorOverriden == true) goto L13;
        BackupImageView r43 = this.flickerView;
        int r62 = Theme.key_bot_loadingIcon;
        int r1 = getColor(r62);
        this.flickerViewColor = r1;
        r43.setColorFilter(new PorterDuffColorFilter(r1, PorterDuff.Mode.SRC_IN));
        SvgHelper.SvgDrawable r44 = this.flickerViewDrawable;
        if (r44 == null) goto L12;
        r44.setColor(this.flickerViewColor);
        this.flickerViewDrawable.setupGradient(r62, this.resourcesProvider, 1.0f, false);
    L12:
        this.flickerView.invalidate();
    L13:
        notifyThemeChanged();
        return;
    L16:
        if (r4 != NotificationCenter.onActivityResultReceived) goto L20;
        onActivityResult(((Integer) r6[0]).intValue(), ((Integer) r6[1]).intValue(), (Intent) r6[2]);
        return;
    L20:
        if (r4 != NotificationCenter.onRequestPermissionResultReceived) goto L23;
        onRequestPermissionsResult(((Integer) r6[0]).intValue(), (String[]) r6[1], (int[]) r6[2]);
        return;
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r5, View r6, long r7) {
        if (r6 != this.flickerView) goto L15;
        if (this.isFlickeringCenter == false) goto L7;
        r5.save();
        r5.translate(0.0f, (ActionBar.getCurrentActionBarHeight() - ((View) getParent()).getTranslationY()) / 2.0f);
    L7:
        boolean r62 = super.drawChild(r5, r6, r7);
        if (this.isFlickeringCenter == false) goto L11;
        r5.restore();
    L11:
        if (this.isFlickeringCenter == true) goto L13;
        RectF r72 = AndroidUtilities.rectTmp;
        r72.set(0.0f, 0.0f, getWidth(), getHeight());
        this.flickerDrawable.draw(r5, r72, 0.0f, this);
        invalidate();
    L13:
        return r62;
    L15:
        if (r6 != this.webViewNotAvailableText) goto L19;
        r5.save();
        r5.translate(0.0f, (ActionBar.getCurrentActionBarHeight() - ((View) getParent()).getTranslationY()) / 2.0f);
        boolean r4 = super.drawChild(r5, r6, r7);
        r5.restore();
        return r4;
    L19:
        if (r6 != this.webView) goto L29;
        if (AndroidUtilities.makingGlobalBlurBitmap == false) goto L23;
        return true;
    L23:
        if (getLayerType() != 2) goto L29;
        if (r5.isHardwareAccelerated() == true) goto L29;
        return true;
    L29:
        return super.drawChild(r5, r6, r7);
    }

    public void evaluateJs(String r3, boolean r4) {
        NotificationCenter.getInstance(this.currentAccount).doOnIdle(new BotWebViewContainer$$ExternalSyntheticLambda0(this, r4, r3));
    }

    public BotWebViewProxy getBotProxy() {
        return this.botWebViewProxy;
    }

    public int getMinHeight() {
        if ((getParent() instanceof ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer) == false) goto L8;
        ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer r0 = (ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer) getParent();
        if (r0.isFullSize() == true) goto L7;
        return 0;
    L7:
        return (int) ((r0.getMeasuredHeight() - r0.getOffsetY()) + this.viewPortHeightOffset);
    L8:
        return 0;
    }

    public String getOriginHost() {
        MyWebView r0 = this.webView;
        if (r0 != null) goto L7;
        return null;
    L7:
        return getOriginHost(r0.getUrl());
    }

    public WebViewProxy getProxy() {
        return this.webViewProxy;
    }

    public String getTrustedOrigin() {
        return this.trustedOrigin;
    }

    public String getUrlLoaded() {
        return this.mUrl;
    }

    public MyWebView getWebView() {
        return this.webView;
    }

    public boolean hasUserPermissions() {
        return this.hasUserPermissions;
    }

    public void invalidateViewPortHeight() {
        invalidateViewPortHeight(false);
    }

    public void invalidateViewPortHeight(boolean r2) {
        invalidateViewPortHeight(r2, false);
    }

    public void invalidateViewPortHeight(boolean r5, boolean r6) {
        invalidate();
        if (this.isPageLoaded == true) goto L6;
        if (r6 == true) goto L6;
        return;
    L6:
        if (this.bot == true) goto L9;
        return;
    L9:
        if ((getParent() instanceof ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer) == false) goto L28;
        ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer r0 = (ChatAttachAlertBotWebViewLayout.WebViewSwipeContainer) getParent();
        if (r5 == true) goto L13;
    L17:
        int r02 = Math.max(getMinHeight(), (int) ((((r0.getMeasuredHeight() - r0.getOffsetY()) - r0.getSwipeOffsetY()) + r0.getTopActionBarOffsetY()) + this.viewPortHeightOffset));
        if (r6 == false) goto L20;
    L25:
        this.lastViewportHeightReported = r02;
        this.lastViewportStateStable = r5;
        this.lastViewportIsExpanded = this.lastExpanded;
        notifyEvent_fast("viewport_changed", "{height:" + (r02 / AndroidUtilities.density) + ",is_state_stable:" + r5 + ",is_expanded:" + this.lastExpanded + "}");
        return;
    L20:
        if (r02 != this.lastViewportHeightReported) goto L25;
        if (this.lastViewportStateStable != r5) goto L25;
        if (this.lastViewportIsExpanded != this.lastExpanded) goto L25;
        return;
    L13:
        if (r0.getSwipeOffsetY() != ((-r0.getOffsetY()) + r0.getTopActionBarOffsetY())) goto L15;
        boolean r1 = true;
    L16:
        this.lastExpanded = r1;
        goto L17
    L15:
        r1 = false;
        goto L16
    }

    public boolean isBackButtonVisible() {
        return this.isBackButtonVisible;
    }

    public boolean isBridgeRestrictedToOrigin() {
        return this.restrictBridgeToOrigin;
    }

    public boolean isPageLoaded() {
        return this.isPageLoaded;
    }

    public void loadFlickerAndSettingsItem(int r10, long r11, ActionBarMenuSubItem r13) {
        TLRPC.User r132 = MessagesController.getInstance(r10).getUser(Long.valueOf(r11));
        TLRPC.UserFull r0 = MessagesController.getInstance(r10).getUserFull(r11);
        String r133 = UserObject.getPublicUsername(r132);
        if (r133 != null) goto L5;
    L8:
        ArrayList<TLRPC.TL_attachMenuBot> r102 = MediaDataController.getInstance(r10).getAttachMenuBots().bots;
        int r134 = r102.size();
        int r4 = 0;
    L9:
        if (r4 >= r134) goto L13;
        TLRPC.TL_attachMenuBot r5 = r102.get(r4);
        r4 = r4 + 1;
        TLRPC.TL_attachMenuBot r52 = r5;
        if (r52.bot_id != r11) goto L9;
    L14:
        boolean r103 = true;
        if (r52 == null) goto L24;
        TLRPC.TL_attachMenuBotIcon r112 = MediaDataController.getPlaceholderStaticAttachMenuBotIcon(r52);
        if (r112 != null) goto L19;
        r112 = MediaDataController.getStaticAttachMenuBotIcon(r52);
    L20:
        if (r112 == null) goto L44;
        this.flickerView.setVisibility(0);
        this.flickerView.setAlpha(1.0f);
        this.flickerView.setImage(ImageLocation.getForDocument(r112.icon), null, null, r52);
        setupFlickerParams(r103);
        return;
    L44:
        return;
    L19:
        r103 = false;
        goto L20
    L24:
        if (r0 == null) goto L36;
        TL_bots.BotInfo r12 = r0.bot_info;
        if (r12 == null) goto L36;
        TL_bots.botAppSettings r122 = r12.app_settings;
        if (r122 == null) goto L36;
        if (r122.placeholder_svg_path == null) goto L36;
        this.flickerView.setVisibility(0);
        this.flickerView.setAlpha(1.0f);
        SvgHelper.SvgDrawable r113 = SvgHelper.getDrawableByPath(r0.bot_info.app_settings.placeholder_svg_path, 512, 512);
        this.flickerViewDrawable = r113;
        if (r113 == null) goto L34;
        r113.setColor(this.flickerViewColor);
        this.flickerViewDrawable.setupGradient(Theme.key_bot_loadingIcon, this.resourcesProvider, 1.0f, false);
    L34:
        this.flickerView.setImage(null, null, this.flickerViewDrawable);
        setupFlickerParams(true);
        return;
    L36:
        Path r123 = new Path();
        RectF r135 = AndroidUtilities.rectTmp;
        r135.set(106.66499f, 106.66499f, 240.355f, 240.355f);
        Path.Direction r53 = Path.Direction.CW;
        r123.addRoundRect(r135, 18.0f, 18.0f, r53);
        r135.set(271.645f, 106.66499f, 405.335f, 240.355f);
        r123.addRoundRect(r135, 18.0f, 18.0f, r53);
        r135.set(106.66499f, 271.645f, 240.355f, 405.335f);
        r123.addRoundRect(r135, 18.0f, 18.0f, r53);
        r135.set(271.645f, 271.645f, 405.335f, 405.335f);
        r123.addRoundRect(r135, 18.0f, 18.0f, r53);
        this.flickerView.setVisibility(0);
        this.flickerView.setAlpha(1.0f);
        SvgHelper.SvgDrawable r114 = SvgHelper.getDrawableByPath(r123, 512, 512);
        this.flickerViewDrawable = r114;
        if (r114 == null) goto L39;
        r114.setColor(this.flickerViewColor);
        this.flickerViewDrawable.setupGradient(Theme.key_bot_loadingIcon, this.resourcesProvider, 1.0f, false);
    L39:
        this.flickerView.setImage(null, null, this.flickerViewDrawable);
        setupFlickerParams(true);
        return;
    L13:
        r52 = null;
        goto L14
    L5:
        if (r133.equals("DurgerKingBot") == false) goto L8;
        this.flickerView.setVisibility(0);
        this.flickerView.setAlpha(1.0f);
        this.flickerView.setImage(null, null, SvgHelper.getDrawable(R.raw.durgerking_placeholder, Integer.valueOf(getColor(Theme.key_windowBackgroundGray))));
        setupFlickerParams(false);
    }

    public void loadUrl(int r5, String r6, boolean r7) {
        this.currentAccount = r5;
        if (this.bot == false) goto L11;
        this.documentGeneration++;
        this.restrictBridgeToOrigin = r7;
        if (r7 == false) goto L7;
        String r72 = getOriginHost(r6);
    L8:
        this.trustedOrigin = r72;
        if (this.webView == null) goto L11;
        setupBotWebMessageBridge();
        goto L11
    L7:
        r72 = null;
    L11:
        NotificationCenter.getInstance(r5).doOnIdle(new BotWebViewContainer$$ExternalSyntheticLambda2(this, r6));
    }

    public void notifyEmojiStatusAccess(String r2) {
        notifyEvent("emoji_status_access_requested", obj("status", r2));
    }

    public void notifyEvent(String r3, JSONObject r4) {
        if (this.bot == true) goto L5;
    L8:
        d("notifyEvent " + r3);
        evaluateJs("window.Telegram.WebView.receiveEvent('" + r3 + "', " + r4 + ");", false);
        return;
    L5:
        if (isTrustedDocumentCurrent() == true) goto L8;
        d("notifyEvent " + r3 + " dropped for untrusted document");
    }

    public void notifyThemeChanged() {
        notifyEvent("theme_changed", buildThemeParams());
    }

    public void onActivityResult(int r3, int r4, Intent r5) {
        if (r3 == 3000) goto L5;
        return;
    L5:
        if (this.mFilePathCallback != null) goto L7;
        return;
    L7:
        if (r4 != (-1)) goto L18;
        if (r5 == null) goto L18;
        int r42 = 0;
        if (r5.getClipData() == null) goto L16;
        ClipData r32 = r5.getClipData();
        Uri[] r52 = new Uri[r32.getItemCount()];
    L13:
        if (r42 >= r32.getItemCount()) goto L19;
        r52[r42] = r32.getItemAt(r42).getUri();
        r42 = r42 + 1;
    L19:
        this.mFilePathCallback.onReceiveValue(r52);
        this.mFilePathCallback = null;
        return;
    L16:
        if (r5.getData() == null) goto L18;
        r52 = new Uri[]{r5.getData()};
    L18:
        r52 = null;
        goto L19
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        d("attached");
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didSetNewTheme);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.onActivityResultReceived);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.onRequestPermissionResultReceived);
        Bulletin.addDelegate(this, new AnonymousClass3(this));
    }

    public boolean onBackPressed() {
        if (this.webView != null) goto L6;
        return false;
    L6:
        if (this.isBackButtonVisible == false) goto L9;
        notifyEvent("back_button_pressed", null);
        return true;
    L9:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        d("detached");
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didSetNewTheme);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.onActivityResultReceived);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.onRequestPermissionResultReceived);
        Bulletin.removeDelegate(this);
    }

    public void onErrorShown(boolean r1, int r2, String r3) {
    }

    public void onFaviconChanged(Bitmap r1) {
    }

    public void onInvoiceStatusUpdate(String r2, String r3) {
        onInvoiceStatusUpdate(r2, r3, false);
    }

    public void onInvoiceStatusUpdate(String r4, String r5, boolean r6) {
        JSONObject r1 = new JSONObject();     // Catch: JSONException -> L9
        r1.put("slug", r4);     // Catch: JSONException -> L9
        r1.put("status", r5);     // Catch: JSONException -> L9
        notifyEvent("invoice_closed", r1);     // Catch: JSONException -> L9
        FileLog.d("invoice_closed " + r1);     // Catch: JSONException -> L9
        if (r6 == false) goto L6;
        return;
    L6:
        if (Objects.equals(this.currentPaymentSlug, r4) == false) goto L15;
        this.currentPaymentSlug = null;     // Catch: JSONException -> L9
        return;
    L15:
        return;
    L9:
        e = move-exception;
        FileLog.e(e);
    }

    public void onMainButtonPressed() {
        this.lastClickMs = System.currentTimeMillis();
        notifyEvent("main_button_pressed", null);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        int r0 = this.forceHeight;
        if (r0 < 0) goto L5;
        r3 = View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30);
    L5:
        super.onMeasure(r2, r3);
        this.flickerDrawable.setParentWidth(getMeasuredWidth());
    }

    public void onRequestPermissionsResult(int r1, String[] r2, int[] r3) {
        if (r1 != 4000) goto L8;
        Runnable r12 = this.onPermissionsRequestResultCallback;
        if (r12 == null) goto L9;
        r12.run();
        this.onPermissionsRequestResultCallback = null;
        return;
    L9:
        return;
    }

    public void onSecondaryButtonPressed() {
        this.lastClickMs = System.currentTimeMillis();
        notifyEvent("secondary_button_pressed", null);
    }

    public void onSettingsButtonPressed() {
        this.lastClickMs = System.currentTimeMillis();
        notifyEvent("settings_button_pressed", null);
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        if (this.isViewPortByMeasureSuppressed == true) goto L6;
        invalidateViewPortHeight(true);
        return;
    }

    public void onTitleChanged(String r1) {
    }

    public void onURLChanged(String r1, boolean r2, boolean r3) {
    }

    public void onWebViewCreated(MyWebView r1) {
    }

    public void onWebViewDestroyed(MyWebView r1) {
    }

    public void preserveWebView() {
        d("preserveWebView");
        this.preserving = true;
        if (this.bot == false) goto L5;
        notifyEvent("visibility_changed", obj("is_visible", Boolean.FALSE));
        this.documentGeneration++;
    L5:
        MyWebView r0 = this.webView;
        if (r0 == null) goto L8;
        r0.setContainers(null, null);
        this.webView.setCloseListener(null);
    L8:
        BotWebViewProxy r02 = this.botWebViewProxy;
        if (r02 == null) goto L11;
        r02.setContainer(null);
    L11:
        WebViewProxy r4 = this.webViewProxy;
        if (r4 == null) goto L15;
        r4.setContainer(null);
        return;
    }

    public void reload() {
        NotificationCenter.getInstance(this.currentAccount).doOnIdle(new BotWebViewContainer$$ExternalSyntheticLambda31(this));
    }

    public void replaceWebView(int r5, MyWebView r6, Object r7, String r8, boolean r9) {
        this.currentAccount = r5;
        this.preserving = false;
        if (this.bot == false) goto L9;
        this.documentGeneration++;
        this.restrictBridgeToOrigin = r9;
        if (r9 == false) goto L7;
        String r52 = getOriginHost(r8);
    L8:
        this.trustedOrigin = r52;
        goto L9
    L7:
        r52 = null;
    L9:
        setupWebView(r6, r7);
        if (this.bot == false) goto L13;
        notifyEvent("visibility_changed", obj("is_visible", Boolean.TRUE));
        return;
    }

    public void reportSafeInsets(Rect r2, int r3) {
        reportSafeInsets(r2, false);
        reportSafeContentInsets(r3, false);
    }

    public void resetWebView() {
        this.webView = null;
    }

    public void restoreButtonData() {
    L9:
        e = move-exception;
        FileLog.e(e);
        return;
    L3:
        if (this.buttonData == null) goto L6;
        onEventReceived(this.botWebViewProxy, createRequestContext(), "web_app_setup_main_button", this.buttonData);     // Catch: Exception -> L9
    L6:
        if (this.secondaryButtonData == null) goto L14;
        onEventReceived(this.botWebViewProxy, createRequestContext(), "web_app_setup_secondary_button", this.secondaryButtonData);     // Catch: Exception -> L9
        return;
    }

    public void setBotUser(TLRPC.User r1) {
        this.botUser = r1;
    }

    public void setDelegate(Delegate r1) {
        this.delegate = r1;
    }

    public void setFlickerViewColor(int r5) {
        if (AndroidUtilities.computePerceivedBrightness(r5) <= 0.7f) goto L5;
        int r52 = Theme.adaptHSV(r5, 0.0f, -0.15f);
    L7:
        if (this.flickerViewColor != r52) goto L9;
        return;
    L9:
        BackupImageView r0 = this.flickerView;
        this.flickerViewColor = r52;
        r0.setColorFilter(new PorterDuffColorFilter(r52, PorterDuff.Mode.SRC_IN));
        SvgHelper.SvgDrawable r53 = this.flickerViewDrawable;
        if (r53 == null) goto L12;
        r53.setColor(this.flickerViewColor);
        this.flickerViewDrawable.setupGradient(Theme.key_bot_loadingIcon, this.resourcesProvider, 1.0f, false);
    L12:
        this.flickerViewColorOverriden = true;
        this.flickerView.invalidate();
        invalidate();
        return;
    L5:
        r52 = Theme.adaptHSV(r5, 0.025f, 0.15f);
        goto L7
    }

    public void setForceHeight(int r2) {
        if (this.forceHeight != r2) goto L5;
        return;
    L5:
        this.forceHeight = r2;
        requestLayout();
    }

    public void setIsBackButtonVisible(boolean r1) {
        this.isBackButtonVisible = r1;
    }

    public void setKeyboardFocusable(boolean r1) {
        this.keyboardFocusable = r1;
        updateKeyboardFocusable();
    }

    public void setOnCloseRequestedListener(Runnable r1) {
        this.onCloseListener = r1;
        MyWebView r0 = this.webView;
        if (r0 == null) goto L6;
        r0.setCloseListener(r1);
        return;
    }

    public void setOnVerifiedAge(Utilities.Callback4<Boolean, Double, String, Double> r1) {
        this.onVerifiedAge = r1;
    }

    public void setOpener(MyWebView r2) {
        this.opener = r2;
        if (this.bot == true) goto L8;
        MyWebView r1 = this.webView;
        if (r1 == null) goto L9;
        r1.opener = r2;
        return;
    L9:
        return;
    }

    public void setPageLoaded(String r8, boolean r9) {
        MyWebView r0 = this.webView;
        if (r0 != null) goto L5;
    L7:
        String r1 = r8;
    L9:
        if (r0 != null) goto L11;
    L14:
        boolean r02 = true;
    L15:
        MyWebView r4 = this.webView;
        if (r4 != null) goto L18;
    L21:
        boolean r42 = true;
    L22:
        onURLChanged(r1, r02, r42);
        MyWebView r03 = this.webView;
        if (r03 == null) goto L26;
        MyWebView.m22022$$Nest$fputisPageLoaded(r03, true);
        updateKeyboardFocusable();
    L26:
        if (this.isPageLoaded == false) goto L30;
        d("setPageLoaded: already loaded");
        return;
    L30:
        if (r9 == true) goto L32;
    L36:
        MyWebView r92 = this.webView;
        if (r92 == null) goto L39;
        r92.setAlpha(1.0f);
    L39:
        BackupImageView r93 = this.flickerView;
        if (r93 == null) goto L42;
        r93.setAlpha(0.0f);
        this.flickerView.setVisibility(8);
    L42:
        this.mUrl = r8;
        d("setPageLoaded: isPageLoaded = true!");
        this.isPageLoaded = true;
        updateKeyboardFocusable();
        this.delegate.onWebAppReady();
        return;
    L32:
        if (this.webView == null) goto L36;
        if (this.flickerView == null) goto L36;
        AnimatorSet r94 = new AnimatorSet();
        Property r12 = View.ALPHA;
        r94.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.webView, r12, new float[]{1.0f}), ObjectAnimator.ofFloat(this.flickerView, r12, new float[]{0.0f})});
        r94.addListener(new AnonymousClass2(this));
        r94.start();
        goto L42
    L18:
        if (r4.canGoForward() == false) goto L21;
        r42 = false;
        goto L22
    L11:
        if (r0.canGoBack() == false) goto L14;
        r02 = false;
        goto L15
    L5:
        if (r0.dangerousUrl == false) goto L7;
        r1 = r0.urlFallback;
        goto L9
    }

    public void setParentActivity(Activity r1) {
        this.parentActivity = r1;
    }

    public void setState(boolean r3, String r4) {
        d("setState(" + r3 + ", " + r4 + ")");
        this.isPageLoaded = r3;
        this.mUrl = r4;
        updateKeyboardFocusable();
    }

    public void setViewPortByMeasureSuppressed(boolean r1) {
        this.isViewPortByMeasureSuppressed = r1;
    }

    public void setViewPortHeightOffset(float r1) {
        this.viewPortHeightOffset = r1;
    }

    public void setWasOpenedByBot(WebViewRequestProps r1) {
        this.wasOpenedByBot = r1;
    }

    public void setWasOpenedByLinkIntent(boolean r1) {
        this.wasOpenedByLinkIntent = r1;
    }

    public void setWebViewProgressListener(Consumer<Float> r1) {
        this.webViewProgressListener = r1;
    }

    public void setWebViewScrollListener(WebViewScrollListener r2) {
        this.webViewScrollListener = r2;
        MyWebView r0 = this.webView;
        if (r0 == null) goto L6;
        r0.setContainers(this, r2);
        return;
    }

    public void showLinkCopiedBulletin() {
        BulletinFactory.of(this, this.resourcesProvider).createCopyLinkBulletin().show(true);
    }

    public void updateFlickerBackgroundColor(int r3) {
        this.flickerDrawable.setColors(r3, 153, Opcodes.SUB_DOUBLE_2ADDR);
    }
}
