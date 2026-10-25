package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Property;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BringAppForegroundService;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.WebPlayerView;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PhotoViewer;

@SuppressLint({"WrongConstant"})
/* loaded from: classes7.dex */
public class EmbedBottomSheet extends BottomSheet {

    @SuppressLint({"StaticFieldLeak"})
    private static EmbedBottomSheet instance;
    private boolean animationInProgress;
    private FrameLayout containerLayout;
    private TextView copyTextButton;
    private View customView;
    private WebChromeClient.CustomViewCallback customViewCallback;
    private String embedUrl;
    private FrameLayout fullscreenVideoContainer;
    private boolean fullscreenedByButton;
    private boolean hasDescription;
    private int height;
    private LinearLayout imageButtonsContainer;
    private boolean isYouTube;
    private int lastOrientation;
    private DialogInterface.OnShowListener onShowListener;
    private String openUrl;
    private OrientationEventListener orientationEventListener;
    private Activity parentActivity;
    private ImageView pipButton;
    private int[] position;
    private int prevOrientation;
    private RadialProgressView progressBar;
    private View progressBarBlackBackground;
    private int seekTimeOverride;
    private WebPlayerView videoView;
    private int waitingForDraw;
    private boolean wasInLandscape;
    private WebView webView;
    private int width;
    private final String youtubeFrame;

    /* renamed from: org.telegram.ui.Components.EmbedBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 implements DialogInterface.OnShowListener {
        final /* synthetic */ EmbedBottomSheet this$0;

        /* renamed from: org.telegram.ui.Components.EmbedBottomSheet$1$1, reason: invalid class name and collision with other inner class name */
        public class ViewTreeObserverOnPreDrawListenerC00521 implements ViewTreeObserver.OnPreDrawListener {
            final /* synthetic */ AnonymousClass1 this$1;

            public ViewTreeObserverOnPreDrawListenerC00521(AnonymousClass1 r1) {
                this.this$1 = r1;
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$1.this$0).getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        }

        public AnonymousClass1(EmbedBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // android.content.DialogInterface.OnShowListener
        public void onShow(DialogInterface r2) {
            if (PipVideoOverlay.isVisible() == true) goto L5;
            return;
        L5:
            if (EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).isInline() == false) goto L9;
            EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserverOnPreDrawListenerC00521(this));
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.EmbedBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        final /* synthetic */ EmbedBottomSheet this$0;

        public AnonymousClass2(EmbedBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
        L19:
            e = move-exception;
            FileLog.e(e);
            return;
        L4:
            if (PipVideoOverlay.isVisible() == false) goto L8;
            if (EmbedBottomSheet.m10197$$Nest$fgetwebView(this.this$0).getVisibility() != 0) goto L8;
        L11:
            if (EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).isInline() == false) goto L13;
            return;
        L13:
            if (PipVideoOverlay.isVisible() == false) goto L15;
            return;
        L15:
            if (EmbedBottomSheet.m10207$$Nest$sfgetinstance() != this.this$0) goto L17;
            EmbedBottomSheet.m10208$$Nest$sfputinstance(null);     // Catch: Exception -> L19
        L17:
            EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).destroy();     // Catch: Exception -> L19
            return;
        L8:
            if (EmbedBottomSheet.m10197$$Nest$fgetwebView(this.this$0).getParent() == null) goto L11;
            removeView(EmbedBottomSheet.m10197$$Nest$fgetwebView(this.this$0));     // Catch: Exception -> L19
            EmbedBottomSheet.m10197$$Nest$fgetwebView(this.this$0).stopLoading();     // Catch: Exception -> L19
            EmbedBottomSheet.m10197$$Nest$fgetwebView(this.this$0).loadUrl("about:blank");     // Catch: Exception -> L19
            EmbedBottomSheet.m10197$$Nest$fgetwebView(this.this$0).destroy();     // Catch: Exception -> L19
            goto L11
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            int r32 = (int) Math.min(EmbedBottomSheet.m10182$$Nest$fgetheight(this.this$0) / (EmbedBottomSheet.m10198$$Nest$fgetwidth(this.this$0) / View.MeasureSpec.getSize(r2)), AndroidUtilities.displaySize.y / 2);
            if (EmbedBottomSheet.m10181$$Nest$fgethasDescription(this.this$0) == false) goto L5;
            int r0 = 22;
        L6:
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec((r32 + AndroidUtilities.dp(r0 + 84)) + 1, TLObject.FLAG_30));
            return;
        L5:
            r0 = 0;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.EmbedBottomSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends WebView {
        final /* synthetic */ EmbedBottomSheet this$0;
        final /* synthetic */ Context val$context;

        public AnonymousClass3(EmbedBottomSheet r1, Context r2, Context r3) {
            this.this$0 = r1;
            this.val$context = r3;
            super(r2);
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
        public boolean onTouchEvent(MotionEvent r3) {
            boolean r0 = super.onTouchEvent(r3);
            if (r0 == false) goto L9;
            int r32 = r3.getAction();
            EmbedBottomSheet r2 = this.this$0;
            if (r32 != 1) goto L8;
            r2.setDisableScroll(false);
            return r0;
        L8:
            r2.setDisableScroll(true);
        L9:
            return r0;
        }
    }

    /* renamed from: org.telegram.ui.Components.EmbedBottomSheet$4, reason: invalid class name */
    public class AnonymousClass4 extends WebChromeClient {
        final /* synthetic */ EmbedBottomSheet this$0;

        public AnonymousClass4(EmbedBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // android.webkit.WebChromeClient
        public Bitmap getDefaultVideoPoster() {
            return Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
        }

        @Override // android.webkit.WebChromeClient
        public void onHideCustomView() {
            super.onHideCustomView();
            if (EmbedBottomSheet.m10176$$Nest$fgetcustomView(this.this$0) != null) goto L5;
            return;
        L5:
            this.this$0.getSheetContainer().setVisibility(0);
            EmbedBottomSheet.m10179$$Nest$fgetfullscreenVideoContainer(this.this$0).setVisibility(4);
            EmbedBottomSheet.m10179$$Nest$fgetfullscreenVideoContainer(this.this$0).removeView(EmbedBottomSheet.m10176$$Nest$fgetcustomView(this.this$0));
            if (EmbedBottomSheet.m10177$$Nest$fgetcustomViewCallback(this.this$0) != null) goto L8;
        L10:
            EmbedBottomSheet.m10200$$Nest$fputcustomView(this.this$0, null);
            return;
        L8:
            if (EmbedBottomSheet.m10177$$Nest$fgetcustomViewCallback(this.this$0).getClass().getName().contains(".chromium.") == true) goto L10;
            EmbedBottomSheet.m10177$$Nest$fgetcustomViewCallback(this.this$0).onCustomViewHidden();
            goto L10
        }

        @Override // android.webkit.WebChromeClient
        public void onShowCustomView(View r1, int r2, WebChromeClient.CustomViewCallback r3) {
            onShowCustomView(r1, r3);
        }

        @Override // android.webkit.WebChromeClient
        public void onShowCustomView(View r4, WebChromeClient.CustomViewCallback r5) {
            if (EmbedBottomSheet.m10176$$Nest$fgetcustomView(this.this$0) == null) goto L5;
        L9:
            r5.onCustomViewHidden();
            return;
        L5:
            if (PipVideoOverlay.isVisible() == true) goto L9;
            this.this$0.exitFromPip();
            EmbedBottomSheet.m10200$$Nest$fputcustomView(this.this$0, r4);
            this.this$0.getSheetContainer().setVisibility(4);
            EmbedBottomSheet.m10179$$Nest$fgetfullscreenVideoContainer(this.this$0).setVisibility(0);
            EmbedBottomSheet.m10179$$Nest$fgetfullscreenVideoContainer(this.this$0).addView(r4, LayoutHelper.createFrame(-1, -1.0f));
            EmbedBottomSheet.m10201$$Nest$fputcustomViewCallback(this.this$0, r5);
        }
    }

    /* renamed from: org.telegram.ui.Components.EmbedBottomSheet$5, reason: invalid class name */
    public class AnonymousClass5 extends WebViewClient {
        final /* synthetic */ EmbedBottomSheet this$0;

        public static /* synthetic */ void $r8$lambda$xXMykftRwxsLbGgALPcww585wGo(AnonymousClass5 r0) {
            r0.lambda$onRenderProcessGone$0();
        }

        public AnonymousClass5(EmbedBottomSheet r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onRenderProcessGone$0() {
            Browser.openUrl(this.this$0.getContext(), "https://play.google.com/store/apps/details?id=com.google.android.webview");
        }

        @Override // android.webkit.WebViewClient
        public void onLoadResource(WebView r1, String r2) {
            super.onLoadResource(r1, r2);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView r1, String r2) {
            super.onPageFinished(r1, r2);
            if (EmbedBottomSheet.m10184$$Nest$fgetisYouTube(this.this$0) == true) goto L6;
            EmbedBottomSheet.m10192$$Nest$fgetprogressBar(this.this$0).setVisibility(4);
            EmbedBottomSheet.m10193$$Nest$fgetprogressBarBlackBackground(this.this$0).setVisibility(4);
            EmbedBottomSheet.m10189$$Nest$fgetpipButton(this.this$0).setEnabled(true);
            EmbedBottomSheet.m10189$$Nest$fgetpipButton(this.this$0).setAlpha(1.0f);
            return;
        }

        @Override // android.webkit.WebViewClient
        public boolean onRenderProcessGone(WebView r3, RenderProcessGoneDetail r4) {
        L7:
            e = move-exception;
            FileLog.e(e);
            return false;
        L3:
            if (AndroidUtilities.isSafeToShow(this.this$0.getContext()) == true) goto L5;
            return true;
        L5:
            new AlertDialog.Builder(this.this$0.getContext(), EmbedBottomSheet.access$000(this.this$0)).setTitle(LocaleController.getString(R.string.ChromeCrashTitle)).setMessage(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.ChromeCrashMessage), new EmbedBottomSheet$5$$ExternalSyntheticLambda0(this))).setPositiveButton(LocaleController.getString(R.string.OK), null).show();     // Catch: Exception -> L7
            return true;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView r2, String r3) {
            if (EmbedBottomSheet.m10184$$Nest$fgetisYouTube(this.this$0) == false) goto L7;
            Browser.openUrl(r2.getContext(), r3);
            return true;
        L7:
            return super.shouldOverrideUrlLoading(r2, r3);
        }
    }

    /* renamed from: org.telegram.ui.Components.EmbedBottomSheet$6, reason: invalid class name */
    public class AnonymousClass6 implements WebPlayerView.WebPlayerViewDelegate {
        final /* synthetic */ EmbedBottomSheet this$0;

        /* renamed from: org.telegram.ui.Components.EmbedBottomSheet$6$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass6 this$1;
            final /* synthetic */ Runnable val$switchInlineModeRunnable;

            public AnonymousClass1(AnonymousClass6 r1, Runnable r2) {
                this.this$1 = r1;
                this.val$switchInlineModeRunnable = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                if (EmbedBottomSheet.m10179$$Nest$fgetfullscreenVideoContainer(this.this$1.this$0).getVisibility() != 0) goto L5;
                EmbedBottomSheet.m10179$$Nest$fgetfullscreenVideoContainer(this.this$1.this$0).setAlpha(1.0f);
                EmbedBottomSheet.m10179$$Nest$fgetfullscreenVideoContainer(this.this$1.this$0).setVisibility(4);
            L5:
                this.val$switchInlineModeRunnable.run();
            }
        }

        /* renamed from: org.telegram.ui.Components.EmbedBottomSheet$6$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass6 this$1;

            public AnonymousClass2(AnonymousClass6 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                EmbedBottomSheet.m10199$$Nest$fputanimationInProgress(this.this$1.this$0, false);
            }
        }

        public AnonymousClass6(EmbedBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.WebPlayerView.WebPlayerViewDelegate
        public boolean checkInlinePermissions() {
            return this.this$0.checkInlinePermissions();
        }

        @Override // org.telegram.ui.Components.WebPlayerView.WebPlayerViewDelegate
        public ViewGroup getTextureViewContainer() {
            return this.this$0.container;
        }

        @Override // org.telegram.ui.Components.WebPlayerView.WebPlayerViewDelegate
        public void onInitFailed() {
            EmbedBottomSheet.m10197$$Nest$fgetwebView(this.this$0).setVisibility(0);
            EmbedBottomSheet.m10183$$Nest$fgetimageButtonsContainer(this.this$0).setVisibility(0);
            EmbedBottomSheet.m10175$$Nest$fgetcopyTextButton(this.this$0).setVisibility(4);
            EmbedBottomSheet.m10197$$Nest$fgetwebView(this.this$0).setKeepScreenOn(true);
            EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).setVisibility(4);
            EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).getControlsView().setVisibility(4);
            EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).getTextureView().setVisibility(4);
            if (EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).getTextureImageView() == null) goto L5;
            EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).getTextureImageView().setVisibility(4);
        L5:
            EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).loadVideo(null, null, null, null, false);
            HashMap r0 = new HashMap();
            r0.put("Referer", "messenger.telegram.org");
            EmbedBottomSheet.m10197$$Nest$fgetwebView(this.this$0).loadUrl(EmbedBottomSheet.m10178$$Nest$fgetembedUrl(this.this$0), r0);     // Catch: Exception -> L8
            return;
        L8:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // org.telegram.ui.Components.WebPlayerView.WebPlayerViewDelegate
        public void onInlineSurfaceTextureReady() {
            if (EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).isInline() == false) goto L6;
            this.this$0.dismissInternal();
            return;
        }

        @Override // org.telegram.ui.Components.WebPlayerView.WebPlayerViewDelegate
        public void onPlayStateChanged(WebPlayerView r1, boolean r2) {
            EmbedBottomSheet r0 = this.this$0;
            if (r2 == true) goto L15;
            EmbedBottomSheet.m10188$$Nest$fgetparentActivity(r0).getWindow().clearFlags(128);     // Catch: Exception -> L10
            return;
        L10:
            e = move-exception;
            FileLog.e(e);
            return;
        L15:
            EmbedBottomSheet.m10188$$Nest$fgetparentActivity(r0).getWindow().addFlags(128);     // Catch: Exception -> L6
            return;
        L6:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // org.telegram.ui.Components.WebPlayerView.WebPlayerViewDelegate
        public void onSharePressed() {
        }

        @Override // org.telegram.ui.Components.WebPlayerView.WebPlayerViewDelegate
        public TextureView onSwitchInlineMode(View r18, boolean r19, int r20, int r21, int r22, boolean r23) {
            if (r19 == false) goto L9;
            r18.setTranslationY(0.0f);
            TextureView r2 = new TextureView(EmbedBottomSheet.m10188$$Nest$fgetparentActivity(this.this$0));
            if (PipVideoOverlay.show(false, EmbedBottomSheet.m10188$$Nest$fgetparentActivity(this.this$0), r2, r20, r21) == false) goto L8;
            PipVideoOverlay.setParentSheet(this.this$0);
            return r2;
        L8:
            return null;
        L9:
            EmbedBottomSheet r4 = this.this$0;
            if (r23 == false) goto L12;
            EmbedBottomSheet.m10199$$Nest$fputanimationInProgress(r4, true);
            EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).getAspectRatioView().getLocationInWindow(EmbedBottomSheet.m10190$$Nest$fgetposition(this.this$0));
            int[] r42 = EmbedBottomSheet.m10190$$Nest$fgetposition(this.this$0);
            r42[0] = r42[0] - this.this$0.getLeftInset();
            EmbedBottomSheet.m10190$$Nest$fgetposition(this.this$0)[1] = (int) (r4[1] - EmbedBottomSheet.access$1300(this.this$0).getTranslationY());
            TextureView r43 = EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).getTextureView();
            ImageView r6 = EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).getTextureImageView();
            AnimatorSet r7 = new AnimatorSet();
            Property r8 = View.SCALE_X;
            ObjectAnimator r9 = ObjectAnimator.ofFloat(r6, r8, new float[]{1.0f});
            Property r11 = View.SCALE_Y;
            ObjectAnimator r12 = ObjectAnimator.ofFloat(r6, r11, new float[]{1.0f});
            Property r13 = View.TRANSLATION_X;
            ObjectAnimator r14 = ObjectAnimator.ofFloat(r6, r13, new float[]{EmbedBottomSheet.m10190$$Nest$fgetposition(this.this$0)[0]});
            Property r15 = View.TRANSLATION_Y;
            r7.playTogether(new Animator[]{r9, r12, r14, ObjectAnimator.ofFloat(r6, r15, new float[]{EmbedBottomSheet.m10190$$Nest$fgetposition(this.this$0)[1]}), ObjectAnimator.ofFloat(r43, r8, new float[]{1.0f}), ObjectAnimator.ofFloat(r43, r11, new float[]{1.0f}), ObjectAnimator.ofFloat(r43, r13, new float[]{EmbedBottomSheet.m10190$$Nest$fgetposition(this.this$0)[0]}), ObjectAnimator.ofFloat(r43, r15, new float[]{EmbedBottomSheet.m10190$$Nest$fgetposition(this.this$0)[1]}), ObjectAnimator.ofFloat(EmbedBottomSheet.access$1400(this.this$0), r15, new float[]{0.0f}), ObjectAnimator.ofInt(EmbedBottomSheet.access$1500(this.this$0), AnimationProperties.COLOR_DRAWABLE_ALPHA, new int[]{51})});
            r7.setInterpolator(new DecelerateInterpolator());
            r7.setDuration(250);
            r7.addListener(new AnonymousClass2(this));
            r7.start();
            return null;
        L12:
            EmbedBottomSheet.access$1600(r4).setTranslationY(0.0f);
            return null;
        }

        @Override // org.telegram.ui.Components.WebPlayerView.WebPlayerViewDelegate
        public TextureView onSwitchToFullscreen(View r1, boolean r2, float r3, int r4, boolean r5) {
            EmbedBottomSheet r12 = this.this$0;
            if (r2 == false) goto L17;
            EmbedBottomSheet.m10179$$Nest$fgetfullscreenVideoContainer(r12).setVisibility(0);
            EmbedBottomSheet.m10179$$Nest$fgetfullscreenVideoContainer(this.this$0).setAlpha(1.0f);
            EmbedBottomSheet.m10179$$Nest$fgetfullscreenVideoContainer(this.this$0).addView(EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).getAspectRatioView());
            EmbedBottomSheet.m10206$$Nest$fputwasInLandscape(this.this$0, false);
            EmbedBottomSheet.m10202$$Nest$fputfullscreenedByButton(this.this$0, r5);
            if (EmbedBottomSheet.m10188$$Nest$fgetparentActivity(this.this$0) == null) goto L29;
            EmbedBottomSheet r13 = this.this$0;     // Catch: Exception -> L15
            EmbedBottomSheet.m10204$$Nest$fputprevOrientation(r13, EmbedBottomSheet.m10188$$Nest$fgetparentActivity(r13).getRequestedOrientation());     // Catch: Exception -> L15
            if (r5 == false) goto L13;
            int r14 = ((WindowManager) EmbedBottomSheet.m10188$$Nest$fgetparentActivity(this.this$0).getSystemService("window")).getDefaultDisplay().getRotation();     // Catch: Exception -> L15
            EmbedBottomSheet r22 = this.this$0;
            if (r14 != 3) goto L12;
            EmbedBottomSheet.m10188$$Nest$fgetparentActivity(r22).setRequestedOrientation(8);     // Catch: Exception -> L15
            goto L13
        L12:
            EmbedBottomSheet.m10188$$Nest$fgetparentActivity(r22).setRequestedOrientation(0);     // Catch: Exception -> L15
        L13:
            EmbedBottomSheet.access$100(this.this$0).setSystemUiVisibility(1028);     // Catch: Exception -> L15
            return null;
        L15:
            e = move-exception;
            FileLog.e(e);
            return null;
        L29:
            return null;
        L17:
            EmbedBottomSheet.m10179$$Nest$fgetfullscreenVideoContainer(r12).setVisibility(4);
            EmbedBottomSheet.m10202$$Nest$fputfullscreenedByButton(this.this$0, false);
            if (EmbedBottomSheet.m10188$$Nest$fgetparentActivity(this.this$0) == null) goto L31;
            EmbedBottomSheet.access$200(this.this$0).setSystemUiVisibility(0);     // Catch: Exception -> L21
            EmbedBottomSheet.m10188$$Nest$fgetparentActivity(this.this$0).setRequestedOrientation(EmbedBottomSheet.m10191$$Nest$fgetprevOrientation(this.this$0));     // Catch: Exception -> L21
            return null;
        L21:
            e = move-exception;
            FileLog.e(e);
            return null;
        L31:
            return null;
        }

        @Override // org.telegram.ui.Components.WebPlayerView.WebPlayerViewDelegate
        public void onVideoSizeChanged(float r1, int r2) {
        }

        @Override // org.telegram.ui.Components.WebPlayerView.WebPlayerViewDelegate
        public void prepareToSwitchInlineMode(boolean r20, Runnable r21, float r22, boolean r23) {
            if (r20 == false) goto L23;
            if (EmbedBottomSheet.m10188$$Nest$fgetparentActivity(this.this$0) == null) goto L13;
            EmbedBottomSheet.access$300(this.this$0).setSystemUiVisibility(0);     // Catch: Exception -> L10
            if (EmbedBottomSheet.m10191$$Nest$fgetprevOrientation(this.this$0) == (-2)) goto L13;
            EmbedBottomSheet.m10188$$Nest$fgetparentActivity(this.this$0).setRequestedOrientation(EmbedBottomSheet.m10191$$Nest$fgetprevOrientation(this.this$0));     // Catch: Exception -> L10
        L10:
            e = move-exception;
            FileLog.e(e);
        L13:
            if (EmbedBottomSheet.m10179$$Nest$fgetfullscreenVideoContainer(this.this$0).getVisibility() != 0) goto L15;
            EmbedBottomSheet.access$500(this.this$0).setTranslationY(EmbedBottomSheet.access$400(this.this$0).getMeasuredHeight() + AndroidUtilities.dp(10.0f));
            EmbedBottomSheet.access$600(this.this$0).setAlpha(0);
        L15:
            this.this$0.setOnShowListener(null);
            EmbedBottomSheet r0 = this.this$0;
            if (r23 == false) goto L19;
            TextureView r02 = EmbedBottomSheet.m10195$$Nest$fgetvideoView(r0).getTextureView();
            View r7 = EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).getControlsView();
            ImageView r8 = EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).getTextureImageView();
            RectOld r2 = PipVideoOverlay.getPipRect(true, r22);
            float r9 = r2.width / r02.getWidth();
            AnimatorSet r10 = new AnimatorSet();
            Property r11 = View.SCALE_X;
            ObjectAnimator r12 = ObjectAnimator.ofFloat(r8, r11, new float[]{r9});
            Property r13 = View.SCALE_Y;
            ObjectAnimator r14 = ObjectAnimator.ofFloat(r8, r13, new float[]{r9});
            Property r15 = View.TRANSLATION_X;
            ObjectAnimator r5 = ObjectAnimator.ofFloat(r8, r15, new float[]{r2.x});
            Property r6 = View.TRANSLATION_Y;
            ObjectAnimator r3 = ObjectAnimator.ofFloat(r8, r6, new float[]{r2.y});
            ObjectAnimator r82 = ObjectAnimator.ofFloat(r02, r11, new float[]{r9});
            ObjectAnimator r92 = ObjectAnimator.ofFloat(r02, r13, new float[]{r9});
            ObjectAnimator r112 = ObjectAnimator.ofFloat(r02, r15, new float[]{r2.x});
            ObjectAnimator r03 = ObjectAnimator.ofFloat(r02, r6, new float[]{r2.y});
            ObjectAnimator r24 = ObjectAnimator.ofFloat(EmbedBottomSheet.access$700(this.this$0), r6, new float[]{EmbedBottomSheet.access$800(this.this$0).getMeasuredHeight() + AndroidUtilities.dp(10.0f)});
            ObjectAnimator r62 = ObjectAnimator.ofInt(EmbedBottomSheet.access$900(this.this$0), AnimationProperties.COLOR_DRAWABLE_ALPHA, new int[]{0});
            FrameLayout r132 = EmbedBottomSheet.m10179$$Nest$fgetfullscreenVideoContainer(this.this$0);
            Property r152 = View.ALPHA;
            r10.playTogether(new Animator[]{r12, r14, r5, r3, r82, r92, r112, r03, r24, r62, ObjectAnimator.ofFloat(r132, r152, new float[]{0.0f}), ObjectAnimator.ofFloat(r7, r152, new float[]{0.0f})});
            r10.setInterpolator(new DecelerateInterpolator());
            r10.setDuration(250);
            r10.addListener(new AnonymousClass1(this, r21));
            r10.start();
            return;
        L19:
            if (EmbedBottomSheet.m10179$$Nest$fgetfullscreenVideoContainer(r0).getVisibility() != 0) goto L21;
            EmbedBottomSheet.m10179$$Nest$fgetfullscreenVideoContainer(this.this$0).setAlpha(1.0f);
            EmbedBottomSheet.m10179$$Nest$fgetfullscreenVideoContainer(this.this$0).setVisibility(4);
        L21:
            r21.run();
            this.this$0.dismissInternal();
            return;
        L23:
            if (ApplicationLoader.mainInterfacePaused == true) goto L35;
        L28:
            if (r23 == false) goto L30;
            EmbedBottomSheet r04 = this.this$0;
            r04.setOnShowListener(EmbedBottomSheet.m10185$$Nest$fgetonShowListener(r04));
            RectOld r05 = PipVideoOverlay.getPipRect(false, r22);
            TextureView r25 = EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).getTextureView();
            ImageView r32 = EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).getTextureImageView();
            float r52 = r05.width / r25.getLayoutParams().width;
            r32.setScaleX(r52);
            r32.setScaleY(r52);
            r32.setTranslationX(r05.x);
            r32.setTranslationY(r05.y);
            r25.setScaleX(r52);
            r25.setScaleY(r52);
            r25.setTranslationX(r05.x);
            r25.setTranslationY(r05.y);
        L31:
            this.this$0.setShowWithoutAnimation(true);
            this.this$0.show();
            if (r23 == false) goto L41;
            EmbedBottomSheet.m10205$$Nest$fputwaitingForDraw(this.this$0, 4);
            EmbedBottomSheet.access$1000(this.this$0).setAlpha(1);
            EmbedBottomSheet.access$1200(this.this$0).setTranslationY(EmbedBottomSheet.access$1100(this.this$0).getMeasuredHeight() + AndroidUtilities.dp(10.0f));
            return;
        L41:
            return;
        L30:
            PipVideoOverlay.dismiss();
            goto L31
        L35:
            EmbedBottomSheet.m10188$$Nest$fgetparentActivity(this.this$0).startService(new Intent(ApplicationLoader.applicationContext, BringAppForegroundService.class));     // Catch: Throwable -> L26
        L26:
            th = move-exception;
            FileLog.e(th);
            goto L28
        }
    }

    /* renamed from: org.telegram.ui.Components.EmbedBottomSheet$8, reason: invalid class name */
    public class AnonymousClass8 extends BottomSheet.BottomSheetDelegate {
        final /* synthetic */ EmbedBottomSheet this$0;
        final /* synthetic */ boolean val$canHandleUrl;

        public AnonymousClass8(EmbedBottomSheet r1, boolean r2) {
            this.this$0 = r1;
            this.val$canHandleUrl = r2;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet.BottomSheetDelegate, org.telegram.ui.ActionBar.BottomSheet.BottomSheetDelegateInterface
        public boolean canDismiss() {
            boolean r0 = EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).isInFullscreen();
            EmbedBottomSheet r1 = this.this$0;
            if (r0 == false) goto L12;
            EmbedBottomSheet.m10195$$Nest$fgetvideoView(r1).exitFullscreen();
            return false;
        L12:
            EmbedBottomSheet.m10188$$Nest$fgetparentActivity(r1).getWindow().clearFlags(128);     // Catch: Exception -> L8
            return true;
        L8:
            e = move-exception;
            FileLog.e(e);
            return true;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet.BottomSheetDelegate, org.telegram.ui.ActionBar.BottomSheet.BottomSheetDelegateInterface
        public void onOpenAnimationEnd() {
            int r3 = 0;
            if (this.val$canHandleUrl == true) goto L5;
        L8:
            EmbedBottomSheet.m10192$$Nest$fgetprogressBar(this.this$0).setVisibility(0);
            EmbedBottomSheet.m10197$$Nest$fgetwebView(this.this$0).setVisibility(0);
            EmbedBottomSheet.m10183$$Nest$fgetimageButtonsContainer(this.this$0).setVisibility(0);
            EmbedBottomSheet.m10175$$Nest$fgetcopyTextButton(this.this$0).setVisibility(4);
            EmbedBottomSheet.m10197$$Nest$fgetwebView(this.this$0).setKeepScreenOn(true);
            EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).setVisibility(4);
            EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).getControlsView().setVisibility(4);
            EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).getTextureView().setVisibility(4);
            if (EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).getTextureImageView() == null) goto L11;
            EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).getTextureImageView().setVisibility(4);
        L11:
            EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).loadVideo(null, null, null, null, false);
            HashMap r2 = new HashMap();
            r2.put("Referer", "messenger.telegram.org");
            String r4 = EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).getYoutubeId();     // Catch: Exception -> L36
            EmbedBottomSheet r6 = this.this$0;
            if (r4 == null) goto L34;
            EmbedBottomSheet.m10193$$Nest$fgetprogressBarBlackBackground(r6).setVisibility(0);     // Catch: Exception -> L36
            EmbedBottomSheet.m10203$$Nest$fputisYouTube(this.this$0, true);     // Catch: Exception -> L36
            String r8 = null;
            EmbedBottomSheet.m10197$$Nest$fgetwebView(this.this$0).addJavascriptInterface(new YoutubeProxy(this.this$0, null), "YoutubeProxy");     // Catch: Exception -> L36
            if (EmbedBottomSheet.m10186$$Nest$fgetopenUrl(this.this$0) != null) goto L39;
        L33:
            EmbedBottomSheet.m10197$$Nest$fgetwebView(this.this$0).loadDataWithBaseURL("https://messenger.telegram.org/", String.format(Locale.US, "<!DOCTYPE html><html><head><style>body { margin: 0; width:100%%; height:100%%;  background-color:#000; }html { width:100%%; height:100%%; background-color:#000; }.embed-container iframe,.embed-container object,   .embed-container embed {       position: absolute;       top: 0;       left: 0;       width: 100%% !important;       height: 100%% !important;   }   </style></head><body>   <div class=\"embed-container\">       <div id=\"player\"></div>   </div>   <script src=\"https://www.youtube.com/iframe_api\"></script>   <script>   var player;   var observer;   var videoEl;   var playing;   var posted = false;   YT.ready(function() {       player = new YT.Player(\"player\", {                              \"width\" : \"100%%\",                              \"events\" : {                              \"onReady\" : \"onReady\",                              \"onError\" : \"onError\",                              \"onStateChange\" : \"onStateChange\",                              },                              \"videoId\" : \"%1$s\",                              \"height\" : \"100%%\",                              \"playerVars\" : {                              \"start\" : %2$d,                              \"rel\" : 1,                              \"showinfo\" : 0,                              \"modestbranding\" : 0,                              \"iv_load_policy\" : 3,                              \"autohide\" : 1,                              \"autoplay\" : 1,                              \"cc_load_policy\" : 1,                              \"playsinline\" : 1,                              \"controls\" : 1                              }                            });        player.setSize(window.innerWidth, window.innerHeight);    });    function hideControls() {        playing = !videoEl.paused;       videoEl.controls = 0;       observer.observe(videoEl, {attributes: true});    }    function showControls() {        playing = !videoEl.paused;       observer.disconnect();       videoEl.controls = 1;    }    function onError(event) {       if (!posted) {            if (window.YoutubeProxy !== undefined) {                   YoutubeProxy.postEvent(\"loaded\", null);             }            posted = true;       }    }    function onStateChange(event) {       if (event.data == YT.PlayerState.PLAYING && !posted) {            if (window.YoutubeProxy !== undefined) {                   YoutubeProxy.postEvent(\"loaded\", null);             }            posted = true;       }    }    function onReady(event) {       player.playVideo();    }    window.onresize = function() {       player.setSize(window.innerWidth, window.innerHeight);       player.playVideo();    }    </script></body></html>", new Object[]{r4, Integer.valueOf(r3)}), "text/html", "UTF-8", "https://youtube.com");     // Catch: Exception -> L36
            return;
        L39:
            Uri r22 = Uri.parse(EmbedBottomSheet.m10186$$Nest$fgetopenUrl(this.this$0));     // Catch: Exception -> L20
            if (EmbedBottomSheet.m10194$$Nest$fgetseekTimeOverride(this.this$0) <= 0) goto L22;
            r8 = _UrlKt.FRAGMENT_ENCODE_SET + EmbedBottomSheet.m10194$$Nest$fgetseekTimeOverride(this.this$0);     // Catch: Exception -> L20
        L22:
            if (r8 != null) goto L26;
            r8 = r22.getQueryParameter("t");     // Catch: Exception -> L20
            if (r8 != null) goto L26;
            r8 = r22.getQueryParameter("time_continue");     // Catch: Exception -> L20
        L26:
            if (r8 == null) goto L33;
            if (r8.contains("m") == false) goto L30;
            String[] r0 = r8.split("m");     // Catch: Exception -> L20
            r3 = (Utilities.parseInt(r0[0]) * 60) + Utilities.parseInt(r0[1]);     // Catch: Exception -> L20
            goto L33
        L30:
            r3 = Utilities.parseInt(r8);     // Catch: Exception -> L20
        L20:
            e = move-exception;
            FileLog.e(e);     // Catch: Exception -> L36
            goto L33
        L34:
            EmbedBottomSheet.m10197$$Nest$fgetwebView(r6).loadUrl(EmbedBottomSheet.m10178$$Nest$fgetembedUrl(this.this$0), r2);     // Catch: Exception -> L36
            return;
        L36:
            e = move-exception;
            FileLog.e(e);
            return;
        L5:
            if (EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).loadVideo(EmbedBottomSheet.m10178$$Nest$fgetembedUrl(this.this$0), null, null, EmbedBottomSheet.m10186$$Nest$fgetopenUrl(this.this$0), true) == false) goto L8;
            EmbedBottomSheet.m10192$$Nest$fgetprogressBar(this.this$0).setVisibility(4);
            EmbedBottomSheet.m10197$$Nest$fgetwebView(this.this$0).setVisibility(4);
            EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).setVisibility(0);
        }
    }

    /* renamed from: org.telegram.ui.Components.EmbedBottomSheet$9, reason: invalid class name */
    public class AnonymousClass9 extends OrientationEventListener {
        final /* synthetic */ EmbedBottomSheet this$0;

        public AnonymousClass9(EmbedBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.OrientationEventListener
        public void onOrientationChanged(int r2) {
            if (EmbedBottomSheet.m10187$$Nest$fgetorientationEventListener(this.this$0) != null) goto L5;
            return;
        L5:
            if (EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).getVisibility() == 0) goto L8;
            return;
        L8:
            if (EmbedBottomSheet.m10188$$Nest$fgetparentActivity(this.this$0) != null) goto L10;
            return;
        L10:
            if (EmbedBottomSheet.m10195$$Nest$fgetvideoView(this.this$0).isInFullscreen() == true) goto L12;
            return;
        L12:
            if (EmbedBottomSheet.m10180$$Nest$fgetfullscreenedByButton(this.this$0) == true) goto L14;
            return;
        L14:
            if (r2 < 240) goto L20;
            if (r2 > 300) goto L20;
            EmbedBottomSheet.m10206$$Nest$fputwasInLandscape(this.this$0, true);
            return;
        L20:
            if (EmbedBottomSheet.m10196$$Nest$fgetwasInLandscape(this.this$0) == false) goto L32;
            if (r2 > 0) goto L23;
            return;
        L23:
            if (r2 < 330) goto L25;
        L26:
            EmbedBottomSheet.m10188$$Nest$fgetparentActivity(this.this$0).setRequestedOrientation(EmbedBottomSheet.m10191$$Nest$fgetprevOrientation(this.this$0));
            EmbedBottomSheet.m10202$$Nest$fputfullscreenedByButton(this.this$0, false);
            EmbedBottomSheet.m10206$$Nest$fputwasInLandscape(this.this$0, false);
            return;
        L25:
            if (r2 <= 30) goto L26;
            return;
        }
    }

    public class YoutubeProxy {
        final /* synthetic */ EmbedBottomSheet this$0;

        /* renamed from: $r8$lambda$vfnazZPFhTHJ-Va3C-wL04zBERc, reason: not valid java name */
        public static /* synthetic */ void m10209$r8$lambda$vfnazZPFhTHJVa3CwL04zBERc(YoutubeProxy r0) {
            r0.lambda$postEvent$0();
        }

        private YoutubeProxy(EmbedBottomSheet r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ YoutubeProxy(EmbedBottomSheet r1, EmbedBottomSheetIA r2) {
            this(r1);
        }

        private /* synthetic */ void lambda$postEvent$0() {
            EmbedBottomSheet.m10192$$Nest$fgetprogressBar(this.this$0).setVisibility(4);
            EmbedBottomSheet.m10193$$Nest$fgetprogressBarBlackBackground(this.this$0).setVisibility(4);
            EmbedBottomSheet.m10189$$Nest$fgetpipButton(this.this$0).setEnabled(true);
            EmbedBottomSheet.m10189$$Nest$fgetpipButton(this.this$0).setAlpha(1.0f);
        }

        @JavascriptInterface
        public void postEvent(String r1, String r2) {
            if ("loaded".equals(r1) == false) goto L6;
            AndroidUtilities.runOnUIThread(new EmbedBottomSheet$YoutubeProxy$$ExternalSyntheticLambda0(this));
            return;
        }
    }

    /* renamed from: $r8$lambda$KNMIvqPVq0-zd0qOlxA7Qyswtk4, reason: not valid java name */
    public static /* synthetic */ void m10174$r8$lambda$KNMIvqPVq0zd0qOlxA7Qyswtk4(EmbedBottomSheet r0, View r1) {
        r0.lambda$new$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$QCvQJ2x5kGzfoWuduK2rc1trtn8(EmbedBottomSheet r0, View r1) {
        r0.lambda$new$4(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$TYGq2_Sm_y4Rvgth4JDN1AR7Az8(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$WFqkmX6OY1JAMtwrtyuo1arVPts(EmbedBottomSheet r0, View r1) {
        r0.lambda$new$5(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$ZkrgZlkVSzB6qtOfyLrMh7Sv2xk(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$wtecxePrpGppa1298wPmEGdoaxU(EmbedBottomSheet r0, View r1) {
        r0.lambda$new$2(r1);
    }

    /* renamed from: -$$Nest$fgetcopyTextButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m10175$$Nest$fgetcopyTextButton(EmbedBottomSheet r0) {
        return r0.copyTextButton;
    }

    /* renamed from: -$$Nest$fgetcustomView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m10176$$Nest$fgetcustomView(EmbedBottomSheet r0) {
        return r0.customView;
    }

    /* renamed from: -$$Nest$fgetcustomViewCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WebChromeClient.CustomViewCallback m10177$$Nest$fgetcustomViewCallback(EmbedBottomSheet r0) {
        return r0.customViewCallback;
    }

    /* renamed from: -$$Nest$fgetembedUrl, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m10178$$Nest$fgetembedUrl(EmbedBottomSheet r0) {
        return r0.embedUrl;
    }

    /* renamed from: -$$Nest$fgetfullscreenVideoContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m10179$$Nest$fgetfullscreenVideoContainer(EmbedBottomSheet r0) {
        return r0.fullscreenVideoContainer;
    }

    /* renamed from: -$$Nest$fgetfullscreenedByButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10180$$Nest$fgetfullscreenedByButton(EmbedBottomSheet r0) {
        return r0.fullscreenedByButton;
    }

    /* renamed from: -$$Nest$fgethasDescription, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10181$$Nest$fgethasDescription(EmbedBottomSheet r0) {
        return r0.hasDescription;
    }

    /* renamed from: -$$Nest$fgetheight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10182$$Nest$fgetheight(EmbedBottomSheet r0) {
        return r0.height;
    }

    /* renamed from: -$$Nest$fgetimageButtonsContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m10183$$Nest$fgetimageButtonsContainer(EmbedBottomSheet r0) {
        return r0.imageButtonsContainer;
    }

    /* renamed from: -$$Nest$fgetisYouTube, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10184$$Nest$fgetisYouTube(EmbedBottomSheet r0) {
        return r0.isYouTube;
    }

    /* renamed from: -$$Nest$fgetonShowListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DialogInterface.OnShowListener m10185$$Nest$fgetonShowListener(EmbedBottomSheet r0) {
        return r0.onShowListener;
    }

    /* renamed from: -$$Nest$fgetopenUrl, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m10186$$Nest$fgetopenUrl(EmbedBottomSheet r0) {
        return r0.openUrl;
    }

    /* renamed from: -$$Nest$fgetorientationEventListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ OrientationEventListener m10187$$Nest$fgetorientationEventListener(EmbedBottomSheet r0) {
        return r0.orientationEventListener;
    }

    /* renamed from: -$$Nest$fgetparentActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Activity m10188$$Nest$fgetparentActivity(EmbedBottomSheet r0) {
        return r0.parentActivity;
    }

    /* renamed from: -$$Nest$fgetpipButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m10189$$Nest$fgetpipButton(EmbedBottomSheet r0) {
        return r0.pipButton;
    }

    /* renamed from: -$$Nest$fgetposition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m10190$$Nest$fgetposition(EmbedBottomSheet r0) {
        return r0.position;
    }

    /* renamed from: -$$Nest$fgetprevOrientation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10191$$Nest$fgetprevOrientation(EmbedBottomSheet r0) {
        return r0.prevOrientation;
    }

    /* renamed from: -$$Nest$fgetprogressBar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RadialProgressView m10192$$Nest$fgetprogressBar(EmbedBottomSheet r0) {
        return r0.progressBar;
    }

    /* renamed from: -$$Nest$fgetprogressBarBlackBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m10193$$Nest$fgetprogressBarBlackBackground(EmbedBottomSheet r0) {
        return r0.progressBarBlackBackground;
    }

    /* renamed from: -$$Nest$fgetseekTimeOverride, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10194$$Nest$fgetseekTimeOverride(EmbedBottomSheet r0) {
        return r0.seekTimeOverride;
    }

    /* renamed from: -$$Nest$fgetvideoView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WebPlayerView m10195$$Nest$fgetvideoView(EmbedBottomSheet r0) {
        return r0.videoView;
    }

    /* renamed from: -$$Nest$fgetwasInLandscape, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10196$$Nest$fgetwasInLandscape(EmbedBottomSheet r0) {
        return r0.wasInLandscape;
    }

    /* renamed from: -$$Nest$fgetwebView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WebView m10197$$Nest$fgetwebView(EmbedBottomSheet r0) {
        return r0.webView;
    }

    /* renamed from: -$$Nest$fgetwidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10198$$Nest$fgetwidth(EmbedBottomSheet r0) {
        return r0.width;
    }

    /* renamed from: -$$Nest$fputanimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10199$$Nest$fputanimationInProgress(EmbedBottomSheet r0, boolean r1) {
        r0.animationInProgress = r1;
    }

    /* renamed from: -$$Nest$fputcustomView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10200$$Nest$fputcustomView(EmbedBottomSheet r0, View r1) {
        r0.customView = r1;
    }

    /* renamed from: -$$Nest$fputcustomViewCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10201$$Nest$fputcustomViewCallback(EmbedBottomSheet r0, WebChromeClient.CustomViewCallback r1) {
        r0.customViewCallback = r1;
    }

    /* renamed from: -$$Nest$fputfullscreenedByButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10202$$Nest$fputfullscreenedByButton(EmbedBottomSheet r0, boolean r1) {
        r0.fullscreenedByButton = r1;
    }

    /* renamed from: -$$Nest$fputisYouTube, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10203$$Nest$fputisYouTube(EmbedBottomSheet r0, boolean r1) {
        r0.isYouTube = r1;
    }

    /* renamed from: -$$Nest$fputprevOrientation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10204$$Nest$fputprevOrientation(EmbedBottomSheet r0, int r1) {
        r0.prevOrientation = r1;
    }

    /* renamed from: -$$Nest$fputwaitingForDraw, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10205$$Nest$fputwaitingForDraw(EmbedBottomSheet r0, int r1) {
        r0.waitingForDraw = r1;
    }

    /* renamed from: -$$Nest$fputwasInLandscape, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10206$$Nest$fputwasInLandscape(EmbedBottomSheet r0, boolean r1) {
        r0.wasInLandscape = r1;
    }

    /* renamed from: -$$Nest$sfgetinstance, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmbedBottomSheet m10207$$Nest$sfgetinstance() {
        return instance;
    }

    /* renamed from: -$$Nest$sfputinstance, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10208$$Nest$sfputinstance(EmbedBottomSheet r0) {
        instance = r0;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private EmbedBottomSheet(Context r27, String r28, String r29, String r30, String r31, int r32, int r33, int r34) {
        super(r27, false);
        this.position = new int[2];
        this.lastOrientation = -1;
        this.prevOrientation = -2;
        this.youtubeFrame = "<!DOCTYPE html><html><head><style>body { margin: 0; width:100%%; height:100%%;  background-color:#000; }html { width:100%%; height:100%%; background-color:#000; }.embed-container iframe,.embed-container object,   .embed-container embed {       position: absolute;       top: 0;       left: 0;       width: 100%% !important;       height: 100%% !important;   }   </style></head><body>   <div class=\"embed-container\">       <div id=\"player\"></div>   </div>   <script src=\"https://www.youtube.com/iframe_api\"></script>   <script>   var player;   var observer;   var videoEl;   var playing;   var posted = false;   YT.ready(function() {       player = new YT.Player(\"player\", {                              \"width\" : \"100%%\",                              \"events\" : {                              \"onReady\" : \"onReady\",                              \"onError\" : \"onError\",                              \"onStateChange\" : \"onStateChange\",                              },                              \"videoId\" : \"%1$s\",                              \"height\" : \"100%%\",                              \"playerVars\" : {                              \"start\" : %2$d,                              \"rel\" : 1,                              \"showinfo\" : 0,                              \"modestbranding\" : 0,                              \"iv_load_policy\" : 3,                              \"autohide\" : 1,                              \"autoplay\" : 1,                              \"cc_load_policy\" : 1,                              \"playsinline\" : 1,                              \"controls\" : 1                              }                            });        player.setSize(window.innerWidth, window.innerHeight);    });    function hideControls() {        playing = !videoEl.paused;       videoEl.controls = 0;       observer.observe(videoEl, {attributes: true});    }    function showControls() {        playing = !videoEl.paused;       observer.disconnect();       videoEl.controls = 1;    }    function onError(event) {       if (!posted) {            if (window.YoutubeProxy !== undefined) {                   YoutubeProxy.postEvent(\"loaded\", null);             }            posted = true;       }    }    function onStateChange(event) {       if (event.data == YT.PlayerState.PLAYING && !posted) {            if (window.YoutubeProxy !== undefined) {                   YoutubeProxy.postEvent(\"loaded\", null);             }            posted = true;       }    }    function onReady(event) {       player.playVideo();    }    window.onresize = function() {       player.setSize(window.innerWidth, window.innerHeight);       player.playVideo();    }    </script></body></html>";
        this.onShowListener = new AnonymousClass1(this);
        this.fullWidth = true;
        setApplyTopPadding(false);
        setApplyBottomPadding(false);
        this.seekTimeOverride = r34;
        if ((r27 instanceof Activity) == false) goto L5;
        this.parentActivity = (Activity) r27;
    L5:
        this.embedUrl = r31;
        if (r29 != null) goto L8;
    L10:
        boolean r11 = false;
    L11:
        this.hasDescription = r11;
        this.openUrl = r30;
        this.width = r32;
        this.height = r33;
        if (r32 == 0) goto L14;
        if (r33 == 0) goto L14;
    L15:
        FrameLayout r4 = new FrameLayout(r27);
        this.fullscreenVideoContainer = r4;
        r4.setKeepScreenOn(true);
        this.fullscreenVideoContainer.setBackgroundColor(-16777216);
        this.fullscreenVideoContainer.setFitsSystemWindows(true);
        this.fullscreenVideoContainer.setOnTouchListener(new EmbedBottomSheet$$ExternalSyntheticLambda0());
        this.container.addView(this.fullscreenVideoContainer, LayoutHelper.createFrame(-1, -1.0f));
        this.fullscreenVideoContainer.setVisibility(4);
        AnonymousClass2 r42 = new AnonymousClass2(this, r27);
        this.containerLayout = r42;
        r42.setOnTouchListener(new EmbedBottomSheet$$ExternalSyntheticLambda1());
        setCustomView(this.containerLayout);
        AnonymousClass3 r43 = new AnonymousClass3(this, r27, r27);
        this.webView = r43;
        r43.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setDomStorageEnabled(true);
        this.webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        this.webView.getSettings().setMixedContentMode(0);
        CookieManager.getInstance().setAcceptThirdPartyCookies(this.webView, true);
        this.webView.setWebChromeClient(new AnonymousClass4(this));
        this.webView.setWebViewClient(new AnonymousClass5(this));
        FrameLayout r44 = this.containerLayout;
        WebView r12 = this.webView;
        int r14 = 22;
        if (this.hasDescription == false) goto L18;
        int r13 = 22;
    L19:
        r44.addView(r12, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 0.0f, r13 + 84));
        WebPlayerView r45 = new WebPlayerView(r27, true, false, new AnonymousClass6(this));
        this.videoView = r45;
        r45.setVisibility(4);
        FrameLayout r46 = this.containerLayout;
        WebPlayerView r122 = this.videoView;
        if (this.hasDescription == false) goto L22;
        int r132 = 22;
    L23:
        r46.addView(r122, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 0.0f, r132 + 74));
        View r47 = new View(r27);
        this.progressBarBlackBackground = r47;
        r47.setBackgroundColor(-16777216);
        this.progressBarBlackBackground.setVisibility(4);
        FrameLayout r48 = this.containerLayout;
        View r5 = this.progressBarBlackBackground;
        if (this.hasDescription == false) goto L26;
        int r123 = 22;
    L27:
        r48.addView(r5, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 0.0f, r123 + 84));
        RadialProgressView r49 = new RadialProgressView(r27);
        this.progressBar = r49;
        r49.setVisibility(4);
        FrameLayout r410 = this.containerLayout;
        RadialProgressView r52 = this.progressBar;
        if (this.hasDescription == true) goto L31;
        r14 = 0;
    L31:
        r410.addView(r52, LayoutHelper.createFrame(-2, -2.0f, 17, 0.0f, 0.0f, 0.0f, (r14 + 84) / 2));
        if (this.hasDescription == false) goto L34;
        TextView r411 = new TextView(r27);
        r411.setTextSize(1, 16.0f);
        r411.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r411.setText(r29);
        r411.setSingleLine(true);
        r411.setTypeface(AndroidUtilities.bold());
        r411.setEllipsize(TextUtils.TruncateAt.END);
        r411.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
        this.containerLayout.addView(r411, LayoutHelper.createFrame(-1, -2.0f, 83, 0.0f, 0.0f, 0.0f, 77.0f));
    L34:
        TextView r2 = new TextView(r27);
        r2.setTextSize(1, 14.0f);
        r2.setTextColor(Theme.getColor(Theme.key_dialogTextGray));
        r2.setText(r28);
        r2.setSingleLine(true);
        TextUtils.TruncateAt r7 = TextUtils.TruncateAt.END;
        r2.setEllipsize(r7);
        r2.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
        this.containerLayout.addView(r2, LayoutHelper.createFrame(-1, -2.0f, 83, 0.0f, 0.0f, 0.0f, 57.0f));
        View r22 = new View(r27);
        r22.setBackgroundColor(Theme.getColor(Theme.key_dialogGrayLine));
        this.containerLayout.addView(r22, new FrameLayout.LayoutParams(-1, 1, 83));
        ((FrameLayout.LayoutParams) r22.getLayoutParams()).bottomMargin = AndroidUtilities.dp(48.0f);
        FrameLayout r23 = new FrameLayout(r27);
        r23.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground));
        this.containerLayout.addView(r23, LayoutHelper.createFrame(-1, 48, 83));
        LinearLayout r124 = new LinearLayout(r27);
        r124.setOrientation(0);
        r124.setWeightSum(1.0f);
        r23.addView(r124, LayoutHelper.createFrame(-2, -1, 53));
        TextView r142 = new TextView(r27);
        r142.setTextSize(1, 14.0f);
        int r15 = Theme.key_dialogTextBlue4;
        r142.setTextColor(Theme.getColor(r15));
        r142.setGravity(17);
        r142.setSingleLine(true);
        r142.setEllipsize(r7);
        int r16 = Theme.key_dialogButtonSelector;
        r142.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.getColor(r16), 0));
        r142.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
        r142.setText(LocaleController.getString(R.string.Close).toUpperCase());
        r142.setTypeface(AndroidUtilities.bold());
        r23.addView(r142, LayoutHelper.createLinear(-2, -1, 51));
        r142.setOnClickListener(new EmbedBottomSheet$$ExternalSyntheticLambda2(this));
        LinearLayout r10 = new LinearLayout(r27);
        this.imageButtonsContainer = r10;
        r10.setVisibility(4);
        r23.addView(this.imageButtonsContainer, LayoutHelper.createFrame(-2, -1, 17));
        ImageView r24 = new ImageView(r27);
        this.pipButton = r24;
        ImageView.ScaleType r102 = ImageView.ScaleType.CENTER;
        r24.setScaleType(r102);
        this.pipButton.setImageResource(R.drawable.ic_goinline);
        this.pipButton.setContentDescription(LocaleController.getString(R.string.AccDescrPipMode));
        this.pipButton.setEnabled(false);
        this.pipButton.setAlpha(0.5f);
        ImageView r25 = this.pipButton;
        int r112 = Theme.getColor(r15);
        PorterDuff.Mode r8 = PorterDuff.Mode.MULTIPLY;
        r25.setColorFilter(new PorterDuffColorFilter(r112, r8));
        this.pipButton.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.getColor(r16), 0));
        this.imageButtonsContainer.addView(this.pipButton, LayoutHelper.createFrame(48, 48.0f, 51, 0.0f, 0.0f, 4.0f, 0.0f));
        this.pipButton.setOnClickListener(new EmbedBottomSheet$$ExternalSyntheticLambda3(this));
        EmbedBottomSheet$$ExternalSyntheticLambda4 r26 = new EmbedBottomSheet$$ExternalSyntheticLambda4(this);
        ImageView r113 = new ImageView(r27);
        r113.setScaleType(r102);
        r113.setImageResource(R.drawable.msg_copy);
        r113.setContentDescription(LocaleController.getString(R.string.CopyLink));
        r113.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r15), r8));
        r113.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.getColor(r16), 0));
        this.imageButtonsContainer.addView(r113, LayoutHelper.createFrame(48, 48, 51));
        r113.setOnClickListener(r26);
        TextView r82 = new TextView(r27);
        this.copyTextButton = r82;
        r82.setTextSize(1, 14.0f);
        this.copyTextButton.setTextColor(Theme.getColor(r15));
        this.copyTextButton.setGravity(17);
        this.copyTextButton.setSingleLine(true);
        this.copyTextButton.setEllipsize(r7);
        this.copyTextButton.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.getColor(r16), 0));
        this.copyTextButton.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
        this.copyTextButton.setText(LocaleController.getString(R.string.Copy).toUpperCase());
        this.copyTextButton.setTypeface(AndroidUtilities.bold());
        r124.addView(this.copyTextButton, LayoutHelper.createFrame(-2, -1, 51));
        this.copyTextButton.setOnClickListener(r26);
        TextView r210 = new TextView(r27);
        r210.setTextSize(1, 14.0f);
        r210.setTextColor(Theme.getColor(r15));
        r210.setGravity(17);
        r210.setSingleLine(true);
        r210.setEllipsize(r7);
        r210.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.getColor(r16), 0));
        r210.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
        r210.setText(LocaleController.getString(R.string.OpenInBrowser).toUpperCase());
        r210.setTypeface(AndroidUtilities.bold());
        r124.addView(r210, LayoutHelper.createFrame(-2, -1, 51));
        r210.setOnClickListener(new EmbedBottomSheet$$ExternalSyntheticLambda5(this));
        if (this.videoView.canHandleUrl(this.embedUrl) == false) goto L37;
    L40:
        boolean r114 = true;
    L41:
        WebPlayerView r1 = this.videoView;
        if (r114 == false) goto L44;
        int r211 = 0;
    L45:
        r1.setVisibility(r211);
        if (r114 == false) goto L48;
        this.videoView.willHandle();
    L48:
        setDelegate(new AnonymousClass8(this, r114));
        this.orientationEventListener = new AnonymousClass9(this, ApplicationLoader.applicationContext);
        String r17 = WebPlayerView.getYouTubeVideoId(this.embedUrl);
        if (r17 != null) goto L51;
        if (r114 == false) goto L51;
    L61:
        boolean r18 = this.orientationEventListener.canDetectOrientation();
        OrientationEventListener r212 = this.orientationEventListener;
        if (r18 == false) goto L64;
        r212.enable();
    L65:
        instance = this;
        return;
    L64:
        r212.disable();
        this.orientationEventListener = null;
    L51:
        this.progressBar.setVisibility(0);
        this.webView.setVisibility(0);
        this.imageButtonsContainer.setVisibility(0);
        if (r17 == null) goto L54;
        this.progressBarBlackBackground.setVisibility(0);
    L54:
        this.copyTextButton.setVisibility(4);
        this.webView.setKeepScreenOn(true);
        this.videoView.setVisibility(4);
        this.videoView.getControlsView().setVisibility(4);
        this.videoView.getTextureView().setVisibility(4);
        if (this.videoView.getTextureImageView() == null) goto L57;
        this.videoView.getTextureImageView().setVisibility(4);
    L57:
        if (r17 == null) goto L61;
        if ("disabled".equals(MessagesController.getInstance(this.currentAccount).youtubePipType) == false) goto L61;
        this.pipButton.setVisibility(8);
        goto L61
    L44:
        r211 = 4;
        goto L45
    L37:
        if (this.videoView.canHandleUrl(r30) == true) goto L40;
        r114 = false;
        goto L41
    L26:
        r123 = 0;
        goto L27
    L22:
        r132 = 0;
        goto L23
    L18:
        r13 = 0;
    L14:
        Point r412 = AndroidUtilities.displaySize;
        this.width = r412.x;
        this.height = r412.y / 2;
        goto L15
    L8:
        if (r29.length() <= 0) goto L10;
        r11 = true;
        goto L11
    }

    public static /* synthetic */ Theme.ResourcesProvider access$000(EmbedBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ ViewGroup access$100(EmbedBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ BottomSheet.SheetBackDrawable access$1000(EmbedBottomSheet r0) {
        return r0.backDrawable;
    }

    public static /* synthetic */ ViewGroup access$1100(EmbedBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$1200(EmbedBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$1300(EmbedBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$1400(EmbedBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ BottomSheet.SheetBackDrawable access$1500(EmbedBottomSheet r0) {
        return r0.backDrawable;
    }

    public static /* synthetic */ ViewGroup access$1600(EmbedBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$200(EmbedBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$300(EmbedBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$400(EmbedBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$500(EmbedBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ BottomSheet.SheetBackDrawable access$600(EmbedBottomSheet r0) {
        return r0.backDrawable;
    }

    public static /* synthetic */ ViewGroup access$700(EmbedBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$800(EmbedBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ BottomSheet.SheetBackDrawable access$900(EmbedBottomSheet r0) {
        return r0.backDrawable;
    }

    public static EmbedBottomSheet getInstance() {
        return instance;
    }

    private /* synthetic */ void lambda$new$2(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$new$3(View r5) {
        if (PipVideoOverlay.isVisible() == false) goto L7;
        PipVideoOverlay.dismiss();
        Objects.requireNonNull(r5);
        AndroidUtilities.runOnUIThread(new EmbedBottomSheet$$ExternalSyntheticLambda6(r5), 300);
        return;
    L7:
        if (this.isYouTube == true) goto L9;
    L11:
        boolean r52 = false;
    L12:
        if (r52 == true) goto L17;
        if (checkInlinePermissions() == true) goto L17;
        return;
    L17:
        if (this.progressBar.getVisibility() != 0) goto L20;
        return;
    L20:
        if (PipVideoOverlay.show(r52, this.parentActivity, this.webView, this.width, this.height) == false) goto L23;
        PipVideoOverlay.setParentSheet(this);
    L23:
        if (this.isYouTube == false) goto L25;
        runJsCode("hideControls();");
    L25:
        this.containerView.setTranslationY(0.0f);
        dismissInternal();
        return;
    L9:
        if ("inapp".equals(MessagesController.getInstance(this.currentAccount).youtubePipType) == false) goto L11;
        r52 = true;
        goto L12
    }

    private /* synthetic */ void lambda$new$4(View r3) {
        ((ClipboardManager) ApplicationLoader.applicationContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", this.openUrl));     // Catch: Exception -> L4
    L6:
        Activity r32 = this.parentActivity;
        if ((r32 instanceof LaunchActivity) == false) goto L9;
        ((LaunchActivity) r32).showBulletin(new EmbedBottomSheet$$ExternalSyntheticLambda7());
    L9:
        dismiss();
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L6
    }

    private /* synthetic */ void lambda$new$5(View r2) {
        Browser.openUrl(this.parentActivity, this.openUrl);
        dismiss();
    }

    private void runJsCode(String r2) {
        this.webView.evaluateJavascript(r2, null);
    }

    public static void show(BaseFragment r19, MessageObject r20, PhotoViewer.PhotoViewerProvider r21, String r22, String r23, String r24, String r25, int r26, int r27, int r28, boolean r29) {
        EmbedBottomSheet r0 = instance;
        if (r0 == null) goto L5;
        r0.destroy();
    L5:
        if (r20 == null) goto L11;
        TLRPC.MessageMedia r02 = r20.messageOwner.media;
        if (r02 == null) goto L11;
        if (r02.webpage == null) goto L11;
        String r03 = WebPlayerView.getYouTubeVideoId(r25);
    L12:
        if (r03 == null) goto L15;
        PhotoViewer.getInstance().setParentActivity(r19);
        PhotoViewer.getInstance().openPhoto(r20, r28, null, 0, 0, 0, r21);
        return;
    L15:
        EmbedBottomSheet r10 = new EmbedBottomSheet(r19.getParentActivity(), r22, r23, r24, r25, r26, r27, r28);
        r10.setCalcMandatoryInsets(r29);
        r10.show();
        return;
    L11:
        r03 = null;
        goto L12
    }

    public static void show(BaseFragment r11, MessageObject r12, PhotoViewer.PhotoViewerProvider r13, String r14, String r15, String r16, String r17, int r18, int r19, boolean r20) {
        show(r11, r12, r13, r14, r15, r16, r17, r18, r19, -1, r20);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        if (this.videoView.getVisibility() == 0) goto L5;
        return true;
    L5:
        if (this.videoView.isInFullscreen() == false) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithTouchOutside() {
        if (this.fullscreenVideoContainer.getVisibility() == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean checkInlinePermissions() {
        Activity r0 = this.parentActivity;
        if (r0 != null) goto L6;
        return false;
    L6:
        if (Settings.canDrawOverlays(r0) == false) goto L9;
        return true;
    L9:
        AlertsCreator.createDrawOverlayPermissionDialog(this.parentActivity, null);
        return false;
    }

    public void destroy() {
        WebView r0 = this.webView;
        if (r0 != null) goto L5;
    L7:
        PipVideoOverlay.dismiss();
        WebPlayerView r02 = this.videoView;
        if (r02 == null) goto L10;
        r02.destroy();
    L10:
        instance = null;
        dismissInternal();
        return;
    L5:
        if (r0.getVisibility() != 0) goto L7;
        this.containerLayout.removeView(this.webView);
        this.webView.stopLoading();
        this.webView.loadUrl("about:blank");
        this.webView.destroy();
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissInternal() {
        super.dismissInternal();
        OrientationEventListener r0 = this.orientationEventListener;
        if (r0 == null) goto L6;
        r0.disable();
        this.orientationEventListener = null;
        return;
    }

    public void exitFromPip() {
        if (this.webView != null) goto L5;
        return;
    L5:
        if (PipVideoOverlay.isVisible() == true) goto L8;
        return;
    L8:
        if (ApplicationLoader.mainInterfacePaused == false) goto L14;
        this.parentActivity.startService(new Intent(ApplicationLoader.applicationContext, BringAppForegroundService.class));     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        FileLog.e(th);
    L14:
        if (this.isYouTube == false) goto L16;
        runJsCode("showControls();");
    L16:
        ViewGroup r0 = (ViewGroup) this.webView.getParent();
        if (r0 == null) goto L19;
        r0.removeView(this.webView);
    L19:
        FrameLayout r02 = this.containerLayout;
        WebView r1 = this.webView;
        if (this.hasDescription == false) goto L22;
        int r2 = 22;
    L23:
        r02.addView(r1, 0, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 0.0f, r2 + 84));
        setShowWithoutAnimation(true);
        show();
        PipVideoOverlay.dismiss(true);
        return;
    L22:
        r2 = 0;
        goto L23
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onConfigurationChanged(Configuration r3) {
        if (this.videoView.getVisibility() == 0) goto L5;
        return;
    L5:
        if (this.videoView.isInitied() == true) goto L7;
        return;
    L7:
        if (this.videoView.isInline() == true) goto L20;
        int r32 = r3.orientation;
        WebPlayerView r0 = this.videoView;
        if (r32 != 2) goto L15;
        if (r0.isInFullscreen() == true) goto L21;
        this.videoView.enterFullscreen();
        return;
    L21:
        return;
    L15:
        if (r0.isInFullscreen() == false) goto L22;
        this.videoView.exitFullscreen();
        return;
    L22:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onContainerDraw(Canvas r1) {
        int r12 = this.waitingForDraw;
        if (r12 == 0) goto L10;
        int r13 = r12 - 1;
        this.waitingForDraw = r13;
        if (r13 != 0) goto L8;
        this.videoView.updateTextureImageView();
        PipVideoOverlay.dismiss();
        return;
    L8:
        this.container.invalidate();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onContainerTranslationYChanged(float r1) {
        updateTextureViewPosition();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean onCustomLayout(View r1, int r2, int r3, int r4, int r5) {
        if (r1 != this.videoView.getControlsView()) goto L7;
        updateTextureViewPosition();
        return false;
    L7:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean onCustomMeasure(View r1, int r2, int r3) {
        if (r1 != this.videoView.getControlsView()) goto L9;
        ViewGroup.LayoutParams r12 = r1.getLayoutParams();
        r12.width = this.videoView.getMeasuredWidth();
        int r22 = this.videoView.getAspectRatioView().getMeasuredHeight();
        if (this.videoView.isInFullscreen() == false) goto L7;
        int r0 = 0;
    L8:
        r12.height = r22 + r0;
        goto L9
    L7:
        r0 = AndroidUtilities.dp(10.0f);
    L9:
        return false;
    }

    public void pause() {
        WebPlayerView r0 = this.videoView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isInitied() == false) goto L9;
        this.videoView.pause();
        return;
    }

    public void updateTextureViewPosition() {
        this.videoView.getAspectRatioView().getLocationInWindow(this.position);
        int[] r0 = this.position;
        r0[0] = r0[0] - getLeftInset();
        if (this.videoView.isInline() == false) goto L5;
    L9:
        View r02 = this.videoView.getControlsView();
        if (r02.getParent() != this.container) goto L13;
        r02.setTranslationY(this.position[1]);
        return;
    L13:
        r02.setTranslationY(0.0f);
        return;
    L5:
        if (this.animationInProgress == true) goto L9;
        TextureView r03 = this.videoView.getTextureView();
        r03.setTranslationX(this.position[0]);
        r03.setTranslationY(this.position[1]);
        ImageView r04 = this.videoView.getTextureImageView();
        if (r04 == null) goto L9;
        r04.setTranslationX(this.position[0]);
        r04.setTranslationY(this.position[1]);
        goto L9
    }
}
