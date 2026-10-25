package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Property;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.Keep;
import com.exteragram.messenger.plugins.PluginsConstants;
import j$.net.URLDecoder;
import j$.net.URLEncoder;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import okhttp3.internal.url._UrlKt;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.mvel2.asm.Opcodes;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.Bitmaps;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.AspectRatioFrameLayout;
import org.telegram.ui.Components.VideoPlayer;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes7.dex */
public class WebPlayerView extends ViewGroup implements VideoPlayer.VideoPlayerDelegate, AudioManager.OnAudioFocusChangeListener {
    private static final Pattern aparatFileListPattern = null;
    private static final Pattern aparatIdRegex = null;
    private static final Pattern coubIdRegex = null;
    private static final Pattern exprParensPattern = null;
    private static final Pattern jsPattern = null;
    private static int lastContainerId = 4001;
    private static final Pattern playerIdPattern = null;
    private static final Pattern sigPattern = null;
    private static final Pattern sigPattern2 = null;
    private static final Pattern stmtReturnPattern = null;
    private static final Pattern stmtVarPattern = null;
    private static final Pattern stsPattern = null;
    private static final Pattern twitchClipFilePattern = null;
    private static final Pattern twitchClipIdRegex = null;
    private static final Pattern twitchStreamIdRegex = null;
    private static final Pattern vimeoIdRegex = null;
    private static final Pattern youtubeIdRegex = null;
    private boolean allowInlineAnimation;
    private AspectRatioFrameLayout aspectRatioFrameLayout;
    private int audioFocus;
    private Paint backgroundPaint;
    private TextureView changedTextureView;
    private boolean changingTextureView;
    private ControlsView controlsView;
    private float currentAlpha;
    private Bitmap currentBitmap;
    private AsyncTask currentTask;
    private String currentYoutubeId;
    private WebPlayerViewDelegate delegate;
    private boolean drawImage;
    private boolean firstFrameRendered;
    private int fragment_container_id;
    private ImageView fullscreenButton;
    private boolean hasAudioFocus;
    private boolean inFullscreen;
    private boolean initied;
    private ImageView inlineButton;
    private String interfaceName;
    private boolean isAutoplay;
    private boolean isCompleted;
    private boolean isInline;
    private boolean isLoading;
    private boolean isStream;
    private long lastUpdateTime;
    private String playAudioType;
    private String playAudioUrl;
    private ImageView playButton;
    private String playVideoType;
    private String playVideoUrl;
    private AnimatorSet progressAnimation;
    private Runnable progressRunnable;
    private RadialProgressView progressView;
    private boolean resumeAudioOnFocusGain;
    private int seekToTime;
    private ImageView shareButton;
    private TextureView.SurfaceTextureListener surfaceTextureListener;
    private Runnable switchToInlineRunnable;
    private boolean switchingInlineMode;
    private ImageView textureImageView;
    private TextureView textureView;
    private ViewGroup textureViewContainer;
    private int videoHeight;
    private VideoPlayer videoPlayer;
    private int videoWidth;
    private int waitingForFirstTextureUpload;
    private WebView webView;

    /* renamed from: org.telegram.ui.Components.WebPlayerView$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ WebPlayerView this$0;

        public AnonymousClass1(WebPlayerView r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (WebPlayerView.m13609$$Nest$fgetvideoPlayer(this.this$0) != null) goto L5;
            return;
        L5:
            if (WebPlayerView.m13609$$Nest$fgetvideoPlayer(this.this$0).isPlaying() == false) goto L10;
            WebPlayerView.m13590$$Nest$fgetcontrolsView(this.this$0).setProgress((int) (WebPlayerView.m13609$$Nest$fgetvideoPlayer(this.this$0).getCurrentPosition() / 1000));
            WebPlayerView.m13590$$Nest$fgetcontrolsView(this.this$0).setBufferedProgress((int) (WebPlayerView.m13609$$Nest$fgetvideoPlayer(this.this$0).getBufferedPosition() / 1000));
            AndroidUtilities.runOnUIThread(WebPlayerView.m13603$$Nest$fgetprogressRunnable(this.this$0), 1000);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.WebPlayerView$2, reason: invalid class name */
    public class AnonymousClass2 implements TextureView.SurfaceTextureListener {
        final /* synthetic */ WebPlayerView this$0;

        /* renamed from: org.telegram.ui.Components.WebPlayerView$2$1, reason: invalid class name */
        public class AnonymousClass1 implements ViewTreeObserver.OnPreDrawListener {
            final /* synthetic */ AnonymousClass2 this$1;

            public static /* synthetic */ void $r8$lambda$FDBGe1Q0iQ35qE6ePXGC2hE_2nk(AnonymousClass1 r0) {
                r0.lambda$onPreDraw$0();
            }

            public AnonymousClass1(AnonymousClass2 r1) {
                this.this$1 = r1;
            }

            private /* synthetic */ void lambda$onPreDraw$0() {
                WebPlayerView.m13593$$Nest$fgetdelegate(this.this$1.this$0).onInlineSurfaceTextureReady();
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                WebPlayerView.m13588$$Nest$fgetchangedTextureView(this.this$1.this$0).getViewTreeObserver().removeOnPreDrawListener(this);
                if (WebPlayerView.m13605$$Nest$fgettextureImageView(this.this$1.this$0) == null) goto L7;
                WebPlayerView.m13605$$Nest$fgettextureImageView(this.this$1.this$0).setVisibility(4);
                WebPlayerView.m13605$$Nest$fgettextureImageView(this.this$1.this$0).setImageDrawable(null);
                if (WebPlayerView.m13592$$Nest$fgetcurrentBitmap(this.this$1.this$0) == null) goto L7;
                WebPlayerView.m13592$$Nest$fgetcurrentBitmap(this.this$1.this$0).recycle();
                WebPlayerView.m13616$$Nest$fputcurrentBitmap(this.this$1.this$0, null);
            L7:
                AndroidUtilities.runOnUIThread(new WebPlayerView$2$1$$ExternalSyntheticLambda0(this));
                WebPlayerView.m13627$$Nest$fputwaitingForFirstTextureUpload(this.this$1.this$0, 0);
                return true;
            }
        }

        public AnonymousClass2(WebPlayerView r1) {
            this.this$0 = r1;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture r1, int r2, int r3) {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture r3) {
            if (WebPlayerView.m13589$$Nest$fgetchangingTextureView(this.this$0) == true) goto L5;
            return true;
        L5:
            if (WebPlayerView.m13604$$Nest$fgetswitchingInlineMode(this.this$0) == false) goto L7;
            WebPlayerView.m13627$$Nest$fputwaitingForFirstTextureUpload(this.this$0, 2);
        L7:
            WebPlayerView.m13606$$Nest$fgettextureView(this.this$0).setSurfaceTexture(r3);
            WebPlayerView.m13606$$Nest$fgettextureView(this.this$0).setVisibility(0);
            WebPlayerView.m13614$$Nest$fputchangingTextureView(this.this$0, false);
            return false;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture r1, int r2, int r3) {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture r2) {
            if (WebPlayerView.m13611$$Nest$fgetwaitingForFirstTextureUpload(this.this$0) != 1) goto L6;
            WebPlayerView.m13588$$Nest$fgetchangedTextureView(this.this$0).getViewTreeObserver().addOnPreDrawListener(new AnonymousClass1(this));
            WebPlayerView.m13588$$Nest$fgetchangedTextureView(this.this$0).invalidate();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.WebPlayerView$3, reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ WebPlayerView this$0;

        public AnonymousClass3(WebPlayerView r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            WebPlayerView.m13626$$Nest$fputswitchingInlineMode(this.this$0, false);
            if (WebPlayerView.m13592$$Nest$fgetcurrentBitmap(this.this$0) == null) goto L5;
            WebPlayerView.m13592$$Nest$fgetcurrentBitmap(this.this$0).recycle();
            WebPlayerView.m13616$$Nest$fputcurrentBitmap(this.this$0, null);
        L5:
            WebPlayerView.m13614$$Nest$fputchangingTextureView(this.this$0, true);
            if (WebPlayerView.m13605$$Nest$fgettextureImageView(this.this$0) != null) goto L26;
        L18:
            WebPlayerView.m13618$$Nest$fputisInline(this.this$0, true);
            WebPlayerView.m13633$$Nest$mupdatePlayButton(this.this$0);
            WebPlayerView.m13634$$Nest$mupdateShareButton(this.this$0);
            WebPlayerView.m13631$$Nest$mupdateFullscreenButton(this.this$0);
            WebPlayerView.m13632$$Nest$mupdateInlineButton(this.this$0);
            ViewGroup r0 = (ViewGroup) WebPlayerView.m13590$$Nest$fgetcontrolsView(this.this$0).getParent();
            if (r0 == null) goto L21;
            r0.removeView(WebPlayerView.m13590$$Nest$fgetcontrolsView(this.this$0));
        L21:
            WebPlayerView r02 = this.this$0;
            WebPlayerView.m13613$$Nest$fputchangedTextureView(r02, WebPlayerView.m13593$$Nest$fgetdelegate(r02).onSwitchInlineMode(WebPlayerView.m13590$$Nest$fgetcontrolsView(this.this$0), WebPlayerView.m13599$$Nest$fgetisInline(this.this$0), WebPlayerView.m13610$$Nest$fgetvideoWidth(this.this$0), WebPlayerView.m13608$$Nest$fgetvideoHeight(this.this$0), WebPlayerView.m13587$$Nest$fgetaspectRatioFrameLayout(this.this$0).getVideoRotation(), WebPlayerView.m13586$$Nest$fgetallowInlineAnimation(this.this$0)));
            WebPlayerView.m13588$$Nest$fgetchangedTextureView(this.this$0).setVisibility(4);
            ViewGroup r03 = (ViewGroup) WebPlayerView.m13606$$Nest$fgettextureView(this.this$0).getParent();
            if (r03 == null) goto L24;
            r03.removeView(WebPlayerView.m13606$$Nest$fgettextureView(this.this$0));
        L24:
            WebPlayerView.m13590$$Nest$fgetcontrolsView(this.this$0).show(false, false);
            return;
        L26:
            WebPlayerView r04 = this.this$0;     // Catch: Throwable -> L9
            WebPlayerView.m13616$$Nest$fputcurrentBitmap(r04, Bitmaps.createBitmap(WebPlayerView.m13606$$Nest$fgettextureView(r04).getWidth(), WebPlayerView.m13606$$Nest$fgettextureView(this.this$0).getHeight(), Bitmap.Config.ARGB_8888));     // Catch: Throwable -> L9
            WebPlayerView.m13606$$Nest$fgettextureView(this.this$0).getBitmap(WebPlayerView.m13592$$Nest$fgetcurrentBitmap(this.this$0));     // Catch: Throwable -> L9
        L14:
            Bitmap r05 = WebPlayerView.m13592$$Nest$fgetcurrentBitmap(this.this$0);
            WebPlayerView r4 = this.this$0;
            if (r05 == null) goto L17;
            WebPlayerView.m13605$$Nest$fgettextureImageView(r4).setVisibility(0);
            WebPlayerView.m13605$$Nest$fgettextureImageView(this.this$0).setImageBitmap(WebPlayerView.m13592$$Nest$fgetcurrentBitmap(this.this$0));
            goto L18
        L17:
            WebPlayerView.m13605$$Nest$fgettextureImageView(r4).setImageDrawable(null);
        L9:
            th = move-exception;
            if (WebPlayerView.m13592$$Nest$fgetcurrentBitmap(this.this$0) == null) goto L13;
            WebPlayerView.m13592$$Nest$fgetcurrentBitmap(this.this$0).recycle();
            WebPlayerView.m13616$$Nest$fputcurrentBitmap(this.this$0, null);
        L13:
            FileLog.e(th);
            goto L14
        }
    }

    /* renamed from: org.telegram.ui.Components.WebPlayerView$4, reason: invalid class name */
    public class AnonymousClass4 extends AspectRatioFrameLayout {
        final /* synthetic */ WebPlayerView this$0;

        public AnonymousClass4(WebPlayerView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.AspectRatioFrameLayout, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            if (WebPlayerView.m13607$$Nest$fgettextureViewContainer(this.this$0) == null) goto L8;
            ViewGroup.LayoutParams r12 = WebPlayerView.m13606$$Nest$fgettextureView(this.this$0).getLayoutParams();
            r12.width = getMeasuredWidth();
            r12.height = getMeasuredHeight();
            if (WebPlayerView.m13605$$Nest$fgettextureImageView(this.this$0) == null) goto L9;
            ViewGroup.LayoutParams r13 = WebPlayerView.m13605$$Nest$fgettextureImageView(this.this$0).getLayoutParams();
            r13.width = getMeasuredWidth();
            r13.height = getMeasuredHeight();
            return;
        L9:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.WebPlayerView$5, reason: invalid class name */
    public class AnonymousClass5 extends WebView {
        final /* synthetic */ WebPlayerView this$0;
        final /* synthetic */ Context val$context;

        public AnonymousClass5(WebPlayerView r1, Context r2, Context r3) {
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
    }

    /* renamed from: org.telegram.ui.Components.WebPlayerView$6, reason: invalid class name */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ WebPlayerView this$0;

        public AnonymousClass6(WebPlayerView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            WebPlayerView.m13625$$Nest$fputprogressAnimation(this.this$0, null);
        }
    }

    public class AparatVideoTask extends AsyncTask<Void, Void, String> {
        private boolean canRetry;
        private String[] results;
        final /* synthetic */ WebPlayerView this$0;
        private String videoId;

        public AparatVideoTask(WebPlayerView r1, String r2) {
            this.this$0 = r1;
            this.canRetry = true;
            this.results = new String[2];
            this.videoId = r2;
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ String doInBackground(Void[] r1) {
            return doInBackground2(r1);
        }

        /* renamed from: doInBackground, reason: avoid collision after fix types in other method */
        public String doInBackground2(Void... r8) {
            String r0 = this.this$0.downloadUrlContent(this, String.format(Locale.US, "http://www.aparat.com/video/video/embed/vt/frame/showvideo/yes/videohash/%s", new Object[]{this.videoId}));
            if (isCancelled() == false) goto L26;
            return null;
        L26:
            Matcher r02 = WebPlayerView.m13635$$Nest$sfgetaparatFileListPattern().matcher(r0);     // Catch: Exception -> L19
            if (r02.find() == false) goto L22;
            JSONArray r4 = new JSONArray(r02.group(1));     // Catch: Exception -> L19
            int r03 = 0;
        L9:
            if (r03 >= r4.length()) goto L22;
            JSONArray r5 = r4.getJSONArray(r03);     // Catch: Exception -> L19
            if (r5.length() == 0) goto L18;
            JSONObject r52 = r5.getJSONObject(0);     // Catch: Exception -> L19
            if (r52.has("file") == false) goto L18;
            this.results[0] = r52.getString("file");     // Catch: Exception -> L19
            this.results[1] = "other";     // Catch: Exception -> L19
        L18:
            r03 = r03 + 1;
        L22:
            if (isCancelled() == false) goto L25;
            return null;
        L25:
            return this.results[0];
        L19:
            e = move-exception;
            FileLog.e(e);
            goto L22
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ void onPostExecute(String r1) {
            onPostExecute2(r1);
        }

        /* renamed from: onPostExecute, reason: avoid collision after fix types in other method */
        public void onPostExecute2(String r3) {
            if (r3 == null) goto L9;
            WebPlayerView.m13617$$Nest$fputinitied(this.this$0, true);
            WebPlayerView.m13624$$Nest$fputplayVideoUrl(this.this$0, r3);
            WebPlayerView.m13623$$Nest$fputplayVideoType(this.this$0, this.results[1]);
            if (WebPlayerView.m13598$$Nest$fgetisAutoplay(this.this$0) == false) goto L6;
            WebPlayerView.m13629$$Nest$mpreparePlayer(this.this$0);
        L6:
            WebPlayerView.m13630$$Nest$mshowProgress(this.this$0, false, true);
            WebPlayerView.m13590$$Nest$fgetcontrolsView(this.this$0).show(true, true);
            return;
        L9:
            if (isCancelled() == true) goto L12;
            WebPlayerView.m13628$$Nest$monInitFailed(this.this$0);
            return;
        }
    }

    public interface CallJavaResultInterface {
        void jsCallFinished(String r1);
    }

    public class ControlsView extends FrameLayout {
        private int bufferedPosition;
        private AnimatorSet currentAnimation;
        private int currentProgressX;
        private int duration;
        private StaticLayout durationLayout;
        private int durationWidth;
        private Runnable hideRunnable;
        private ImageReceiver imageReceiver;
        private boolean isVisible;
        private int lastProgressX;
        private int progress;
        private Paint progressBufferedPaint;
        private Paint progressInnerPaint;
        private StaticLayout progressLayout;
        private Paint progressPaint;
        private boolean progressPressed;
        private TextPaint textPaint;
        final /* synthetic */ WebPlayerView this$0;

        /* renamed from: org.telegram.ui.Components.WebPlayerView$ControlsView$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ ControlsView this$1;

            public AnonymousClass1(ControlsView r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                ControlsView.m13646$$Nest$fputcurrentAnimation(this.this$1, null);
            }
        }

        /* renamed from: org.telegram.ui.Components.WebPlayerView$ControlsView$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ ControlsView this$1;

            public AnonymousClass2(ControlsView r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                ControlsView.m13646$$Nest$fputcurrentAnimation(this.this$1, null);
            }
        }

        public static /* synthetic */ void $r8$lambda$MYx_bAv_0s9abRiOfOVmKdM9NUA(ControlsView r0) {
            r0.lambda$new$0();
        }

        /* renamed from: -$$Nest$fgetimageReceiver, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ImageReceiver m13645$$Nest$fgetimageReceiver(ControlsView r0) {
            return r0.imageReceiver;
        }

        /* renamed from: -$$Nest$fputcurrentAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13646$$Nest$fputcurrentAnimation(ControlsView r0, AnimatorSet r1) {
            r0.currentAnimation = r1;
        }

        /* renamed from: -$$Nest$mcheckNeedHide, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13647$$Nest$mcheckNeedHide(ControlsView r0) {
            r0.checkNeedHide();
        }

        public ControlsView(WebPlayerView r3, Context r4) {
            this.this$0 = r3;
            super(r4);
            this.isVisible = true;
            this.hideRunnable = new WebPlayerView$ControlsView$$ExternalSyntheticLambda0(this);
            setWillNotDraw(false);
            TextPaint r42 = new TextPaint(1);
            this.textPaint = r42;
            r42.setColor(-1);
            this.textPaint.setTextSize(AndroidUtilities.dp(12.0f));
            Paint r43 = new Paint(1);
            this.progressPaint = r43;
            r43.setColor(-15095832);
            Paint r44 = new Paint();
            this.progressInnerPaint = r44;
            r44.setColor(-6975081);
            Paint r45 = new Paint(1);
            this.progressBufferedPaint = r45;
            r45.setColor(-1);
            this.imageReceiver = new ImageReceiver(this);
        }

        private void checkNeedHide() {
            AndroidUtilities.cancelRunOnUIThread(this.hideRunnable);
            if (this.isVisible == true) goto L5;
            return;
        L5:
            if (WebPlayerView.m13609$$Nest$fgetvideoPlayer(this.this$0).isPlaying() == false) goto L9;
            AndroidUtilities.runOnUIThread(this.hideRunnable, 3000);
            return;
        }

        private /* synthetic */ void lambda$new$0() {
            show(false, true);
        }

        @Override // android.view.View
        public void onDraw(Canvas r15) {
            if (WebPlayerView.m13594$$Nest$fgetdrawImage(this.this$0) == false) goto L14;
            if (WebPlayerView.m13595$$Nest$fgetfirstFrameRendered(this.this$0) == true) goto L7;
        L12:
            this.imageReceiver.setAlpha(WebPlayerView.m13591$$Nest$fgetcurrentAlpha(this.this$0));
            this.imageReceiver.draw(r15);
            goto L14
        L7:
            if (WebPlayerView.m13591$$Nest$fgetcurrentAlpha(this.this$0) == 0.0f) goto L12;
            long r4 = System.currentTimeMillis();
            long r6 = r4 - WebPlayerView.m13601$$Nest$fgetlastUpdateTime(this.this$0);
            WebPlayerView.m13620$$Nest$fputlastUpdateTime(this.this$0, r4);
            WebPlayerView r2 = this.this$0;
            WebPlayerView.m13615$$Nest$fputcurrentAlpha(r2, WebPlayerView.m13591$$Nest$fgetcurrentAlpha(r2) - (r6 / 150.0f));
            if (WebPlayerView.m13591$$Nest$fgetcurrentAlpha(this.this$0) >= 0.0f) goto L11;
            WebPlayerView.m13615$$Nest$fputcurrentAlpha(this.this$0, 0.0f);
        L11:
            invalidate();
        L14:
            if (WebPlayerView.m13609$$Nest$fgetvideoPlayer(this.this$0).isPlayerPrepared() == true) goto L16;
            return;
        L16:
            if (WebPlayerView.m13600$$Nest$fgetisStream(this.this$0) == true) goto L69;
            int r22 = getMeasuredWidth();
            int r3 = getMeasuredHeight();
            if (WebPlayerView.m13599$$Nest$fgetisInline(this.this$0) == true) goto L33;
            int r5 = 10;
            if (this.durationLayout == null) goto L27;
            r15.save();
            float r42 = (r22 - AndroidUtilities.dp(58.0f)) - this.durationWidth;
            if (WebPlayerView.m13596$$Nest$fgetinFullscreen(this.this$0) == false) goto L24;
            int r7 = 6;
        L25:
            r15.translate(r42, r3 - AndroidUtilities.dp(r7 + 29));
            this.durationLayout.draw(r15);
            r15.restore();
            goto L27
        L24:
            r7 = 10;
        L27:
            if (this.progressLayout == null) goto L33;
            r15.save();
            float r43 = AndroidUtilities.dp(18.0f);
            if (WebPlayerView.m13596$$Nest$fgetinFullscreen(this.this$0) == false) goto L31;
            r5 = 6;
        L31:
            r15.translate(r43, r3 - AndroidUtilities.dp(r5 + 29));
            this.progressLayout.draw(r15);
            r15.restore();
        L33:
            if (this.duration == 0) goto L70;
            float r72 = 7.0f;
            int r52 = 0;
            if (WebPlayerView.m13599$$Nest$fgetisInline(this.this$0) == false) goto L39;
            int r44 = r3 - AndroidUtilities.dp(3.0f);
            int r62 = AndroidUtilities.dp(7.0f);
        L37:
            int r32 = r3 - r62;
            int r9 = r22;
            int r11 = r44;
            int r12 = r52;
            if (WebPlayerView.m13596$$Nest$fgetinFullscreen(this.this$0) == false) goto L46;
            r15.drawRect(r12, r11, r9, AndroidUtilities.dp(3.0f) + r11, this.progressInnerPaint);
        L46:
            if (this.progressPressed == false) goto L49;
            int r1 = this.currentProgressX;
        L48:
            int r13 = r1;
            int r14 = this.bufferedPosition;
            if (r14 == 0) goto L60;
            int r23 = this.duration;
            if (r23 == 0) goto L60;
            float r33 = r12;
            float r34 = r11;
            float r16 = r33 + ((r9 - r12) * (r14 / r23));
            float r53 = AndroidUtilities.dp(3.0f) + r11;
            if (WebPlayerView.m13596$$Nest$fgetinFullscreen(this.this$0) == false) goto L58;
            Paint r24 = this.progressBufferedPaint;
        L59:
            r15.drawRect(r33, r34, r16, r53, r24);
            goto L60
        L58:
            r24 = this.progressInnerPaint;
        L60:
            float r45 = r13;
            r15.drawRect(r12, r11, r45, r11 + AndroidUtilities.dp(3.0f), this.progressPaint);
            if (WebPlayerView.m13599$$Nest$fgetisInline(this.this$0) == true) goto L71;
            float r25 = r32;
            if (this.progressPressed == true) goto L66;
            r72 = 5.0f;
        L66:
            r15.drawCircle(r45, r25, AndroidUtilities.dp(r72), this.progressPaint);
            return;
        L71:
            return;
        L49:
            r1 = ((int) ((r9 - r12) * (this.progress / this.duration))) + r12;
            goto L48
        L39:
            if (WebPlayerView.m13596$$Nest$fgetinFullscreen(this.this$0) == false) goto L41;
            r44 = r3 - AndroidUtilities.dp(29.0f);
            r52 = AndroidUtilities.dp(36.0f) + this.durationWidth;
            r22 = (r22 - AndroidUtilities.dp(76.0f)) - this.durationWidth;
            r62 = AndroidUtilities.dp(28.0f);
            goto L37
        L41:
            r44 = r3 - AndroidUtilities.dp(13.0f);
            r62 = AndroidUtilities.dp(12.0f);
            goto L37
        L70:
            return;
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r2) {
            if (r2.getAction() != 0) goto L11;
            if (this.isVisible == true) goto L8;
            show(true, true);
            return true;
        L8:
            onTouchEvent(r2);
            return this.progressPressed;
        L11:
            return super.onInterceptTouchEvent(r2);
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r8) {
            if (WebPlayerView.m13596$$Nest$fgetinFullscreen(this.this$0) == false) goto L5;
            int r0 = AndroidUtilities.dp(36.0f) + this.durationWidth;
            int r2 = (getMeasuredWidth() - AndroidUtilities.dp(76.0f)) - this.durationWidth;
            int r3 = getMeasuredHeight() - AndroidUtilities.dp(28.0f);
        L6:
            int r4 = this.duration;
            if (r4 == 0) goto L9;
            int r42 = (int) ((r2 - r0) * (this.progress / r4));
        L10:
            int r43 = r42 + r0;
            if (r8.getAction() != 0) goto L32;
            if (this.isVisible == true) goto L15;
        L29:
            show(true, true);
        L30:
            AndroidUtilities.cancelRunOnUIThread(this.hideRunnable);
        L56:
            super.onTouchEvent(r8);
            return true;
        L15:
            if (WebPlayerView.m13599$$Nest$fgetisInline(this.this$0) == true) goto L29;
            if (WebPlayerView.m13600$$Nest$fgetisStream(this.this$0) == true) goto L29;
            if (this.duration == 0) goto L30;
            int r02 = (int) r8.getX();
            int r1 = (int) r8.getY();
            if (r02 < (r43 - AndroidUtilities.dp(10.0f))) goto L30;
            if (r02 > (AndroidUtilities.dp(10.0f) + r43)) goto L30;
            if (r1 < (r3 - AndroidUtilities.dp(10.0f))) goto L30;
            if (r1 > (r3 + AndroidUtilities.dp(10.0f))) goto L30;
            this.progressPressed = true;
            this.lastProgressX = r02;
            this.currentProgressX = r43;
            getParent().requestDisallowInterceptTouchEvent(true);
            invalidate();
            goto L30
        L32:
            if (r8.getAction() == 1) goto L47;
            if (r8.getAction() == 3) goto L47;
            if (r8.getAction() != 2) goto L56;
            if (this.progressPressed == false) goto L56;
            int r12 = (int) r8.getX();
            int r32 = this.currentProgressX - (this.lastProgressX - r12);
            this.currentProgressX = r32;
            this.lastProgressX = r12;
            if (r32 >= r0) goto L43;
            this.currentProgressX = r0;
        L45:
            setProgress((int) ((this.duration * 1000) * ((this.currentProgressX - r0) / (r2 - r0))));
            invalidate();
            goto L56
        L43:
            if (r32 <= r2) goto L45;
            this.currentProgressX = r2;
        L47:
            if (WebPlayerView.m13597$$Nest$fgetinitied(this.this$0) == false) goto L52;
            if (WebPlayerView.m13609$$Nest$fgetvideoPlayer(this.this$0).isPlaying() == false) goto L52;
            AndroidUtilities.runOnUIThread(this.hideRunnable, 3000);
        L52:
            if (this.progressPressed == false) goto L56;
            this.progressPressed = false;
            if (WebPlayerView.m13597$$Nest$fgetinitied(this.this$0) == false) goto L56;
            this.progress = (int) (this.duration * ((this.currentProgressX - r0) / (r2 - r0)));
            WebPlayerView.m13609$$Nest$fgetvideoPlayer(this.this$0).seekTo(this.progress * 1000);
            goto L56
        L9:
            r42 = 0;
            goto L10
        L5:
            r2 = getMeasuredWidth();
            r3 = getMeasuredHeight() - AndroidUtilities.dp(12.0f);
            r0 = 0;
            goto L6
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public void requestDisallowInterceptTouchEvent(boolean r1) {
            super.requestDisallowInterceptTouchEvent(r1);
            checkNeedHide();
        }

        public void setBufferedProgress(int r1) {
            this.bufferedPosition = r1;
            invalidate();
        }

        public void setDuration(int r10) {
            if (this.duration == r10) goto L13;
            if (r10 >= 0) goto L6;
            return;
        L6:
            if (WebPlayerView.m13600$$Nest$fgetisStream(this.this$0) == true) goto L15;
            this.duration = r10;
            StaticLayout r1 = new StaticLayout(AndroidUtilities.formatShortDuration(this.duration), this.textPaint, AndroidUtilities.dp(1000.0f), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            this.durationLayout = r1;
            if (r1.getLineCount() <= 0) goto L11;
            this.durationWidth = (int) Math.ceil(this.durationLayout.getLineWidth(0));
        L11:
            invalidate();
            return;
        L15:
            return;
        }

        public void setProgress(int r10) {
            if (this.progressPressed == true) goto L10;
            if (r10 >= 0) goto L6;
            return;
        L6:
            if (WebPlayerView.m13600$$Nest$fgetisStream(this.this$0) == true) goto L12;
            this.progress = r10;
            this.progressLayout = new StaticLayout(AndroidUtilities.formatShortDuration(this.progress), this.textPaint, AndroidUtilities.dp(1000.0f), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            invalidate();
            return;
        L12:
            return;
        }

        public void show(boolean r7, boolean r8) {
            if (this.isVisible != r7) goto L5;
            return;
        L5:
            this.isVisible = r7;
            AnimatorSet r72 = this.currentAnimation;
            if (r72 == null) goto L8;
            r72.cancel();
        L8:
            boolean r73 = this.isVisible;
            Property r4 = View.ALPHA;
            if (r73 == false) goto L15;
            if (r8 == false) goto L13;
            AnimatorSet r82 = new AnimatorSet();
            this.currentAnimation = r82;
            r82.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, r4, new float[]{1.0f})});
            this.currentAnimation.setDuration(150);
            this.currentAnimation.addListener(new AnonymousClass1(this));
            this.currentAnimation.start();
        L18:
            checkNeedHide();
            return;
        L13:
            setAlpha(1.0f);
            goto L18
        L15:
            if (r8 == false) goto L17;
            AnimatorSet r83 = new AnimatorSet();
            this.currentAnimation = r83;
            r83.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, r4, new float[]{0.0f})});
            this.currentAnimation.setDuration(150);
            this.currentAnimation.addListener(new AnonymousClass2(this));
            this.currentAnimation.start();
            goto L18
        L17:
            setAlpha(0.0f);
            goto L18
        }
    }

    public class CoubVideoTask extends AsyncTask<Void, Void, String> {
        private boolean canRetry;
        private String[] results;
        final /* synthetic */ WebPlayerView this$0;
        private String videoId;

        public CoubVideoTask(WebPlayerView r1, String r2) {
            this.this$0 = r1;
            this.canRetry = true;
            this.results = new String[4];
            this.videoId = r2;
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ String doInBackground(Void[] r1) {
            return doInBackground2(r1);
        }

        /* renamed from: doInBackground, reason: avoid collision after fix types in other method */
        public String doInBackground2(Void... r6) {
            String r0 = this.this$0.downloadUrlContent(this, String.format(Locale.US, "https://coub.com/api/v2/coubs/%s.json", new Object[]{this.videoId}));
            if (isCancelled() == false) goto L18;
            return null;
        L18:
            JSONObject r02 = new JSONObject(r0).getJSONObject("file_versions").getJSONObject("mobile");     // Catch: Exception -> L11
            String r3 = r02.getString(MediaStreamTrack.VIDEO_TRACK_KIND);     // Catch: Exception -> L11
            String r03 = r02.getJSONArray(MediaStreamTrack.AUDIO_TRACK_KIND).getString(0);     // Catch: Exception -> L11
            if (r3 == null) goto L14;
            if (r03 == null) goto L14;
            String[] r4 = this.results;     // Catch: Exception -> L11
            r4[0] = r3;     // Catch: Exception -> L11
            r4[1] = "other";     // Catch: Exception -> L11
            r4[2] = r03;     // Catch: Exception -> L11
            r4[3] = "other";     // Catch: Exception -> L11
        L14:
            if (isCancelled() == false) goto L17;
            return null;
        L17:
            return this.results[0];
        L11:
            e = move-exception;
            FileLog.e(e);
            goto L14
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ void onPostExecute(String r1) {
            onPostExecute2(r1);
        }

        /* renamed from: onPostExecute, reason: avoid collision after fix types in other method */
        public void onPostExecute2(String r4) {
            if (r4 == null) goto L9;
            WebPlayerView.m13617$$Nest$fputinitied(this.this$0, true);
            WebPlayerView.m13624$$Nest$fputplayVideoUrl(this.this$0, r4);
            WebPlayerView.m13623$$Nest$fputplayVideoType(this.this$0, this.results[1]);
            WebPlayerView.m13622$$Nest$fputplayAudioUrl(this.this$0, this.results[2]);
            WebPlayerView.m13621$$Nest$fputplayAudioType(this.this$0, this.results[3]);
            if (WebPlayerView.m13598$$Nest$fgetisAutoplay(this.this$0) == false) goto L6;
            WebPlayerView.m13629$$Nest$mpreparePlayer(this.this$0);
        L6:
            WebPlayerView.m13630$$Nest$mshowProgress(this.this$0, false, true);
            WebPlayerView.m13590$$Nest$fgetcontrolsView(this.this$0).show(true, true);
            return;
        L9:
            if (isCancelled() == true) goto L12;
            WebPlayerView.m13628$$Nest$monInitFailed(this.this$0);
            return;
        }
    }

    public static class JSExtractor {
        private String[] assign_operators;
        ArrayList<String> codeLines;
        private String jsCode;
        private String[] operators;

        /* renamed from: -$$Nest$mextractFunction, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m13648$$Nest$mextractFunction(JSExtractor r0, String r1) {
            return r0.extractFunction(r1);
        }

        public JSExtractor(String r13) {
            this.codeLines = new ArrayList();
            this.operators = new String[]{"|", "^", "&", ">>", "<<", "-", "+", "%", "/", "*"};
            this.assign_operators = new String[]{"|=", "^=", "&=", ">>=", "<<=", "-=", "+=", "%=", "/=", "*=", "="};
            this.jsCode = r13;
        }

        private void buildFunction(String[] r6, String r7) {
            HashMap<String, String> r0 = new HashMap();
            int r2 = 0;
        L4:
            if (r2 >= r6.length) goto L6;
            r0.put(r6[r2], _UrlKt.FRAGMENT_ENCODE_SET);
            r2 = r2 + 1;
            goto L4
        L6:
            String[] r62 = r7.split(";");
            boolean[] r72 = new boolean[1];
            int r22 = 0;
        L8:
            if (r22 >= r62.length) goto L13;
            interpretStatement(r62[r22], r0, r72, 100);
            if (r72[0] == true) goto L17;
            r22 = r22 + 1;
            goto L8
        L17:
            return;
        }

        private String extractFunction(String r4) {
            String r42 = Pattern.quote(r4);     // Catch: Exception -> L7
            Matcher r43 = Pattern.compile(String.format(Locale.US, "(?x)(?:function\\s+%s|[{;,]\\s*%s\\s*=\\s*function|var\\s+%s\\s*=\\s*function)\\s*\\(([^)]*)\\)\\s*\\{([^}]+)\\}", new Object[]{r42, r42, r42})).matcher(this.jsCode);     // Catch: Exception -> L7
            if (r43.find() == false) goto L13;
            String r0 = r43.group();     // Catch: Exception -> L7
            if (this.codeLines.contains(r0) == true) goto L9;
            this.codeLines.add(r0 + ";");     // Catch: Exception -> L7
        L9:
            buildFunction(r43.group(1).split(","), r43.group(2));     // Catch: Exception -> L7
        L13:
            return TextUtils.join(_UrlKt.FRAGMENT_ENCODE_SET, this.codeLines);
        L7:
            e = move-exception;
            this.codeLines.clear();
            FileLog.e(e);
            goto L13
        }

        private HashMap<String, Object> extractObject(String r7) {
            HashMap<String, Object> r0 = new HashMap();
            Matcher r72 = Pattern.compile(String.format(Locale.US, "(?:var\\s+)?%s\\s*=\\s*\\{\\s*((%s\\s*:\\s*function\\(.*?\\)\\s*\\{.*?\\}(?:,\\s*)?)*)\\}\\s*;", new Object[]{Pattern.quote(r7), "(?:[a-zA-Z$0-9]+|\"[a-zA-Z$0-9]+\"|'[a-zA-Z$0-9]+')"})).matcher(this.jsCode);
            String r1 = null;
        L4:
            if (r72.find() == false) goto L12;
            String r12 = r72.group();
            String r3 = r72.group(2);
            if (TextUtils.isEmpty(r3) == false) goto L9;
            r1 = r3;
            goto L4
        L9:
            if (this.codeLines.contains(r12) == true) goto L11;
            this.codeLines.add(r72.group());
        L11:
            r1 = r3;
        L12:
            Matcher r73 = Pattern.compile(String.format("(%s)\\s*:\\s*function\\(([a-z,]+)\\)\\{([^}]+)\\}", new Object[]{"(?:[a-zA-Z$0-9]+|\"[a-zA-Z$0-9]+\"|'[a-zA-Z$0-9]+')"})).matcher(r1);
        L14:
            if (r73.find() == false) goto L16;
            buildFunction(r73.group(2).split(","), r73.group(3));
            goto L14
        L16:
            return r0;
        }

        private void interpretExpression(String r11, HashMap<String, String> r12, int r13) {
            String r112 = r11.trim();
            if (TextUtils.isEmpty(r112) == true) goto L113;
            int r0 = 0;
            if (r112.charAt(0) != '(') goto L22;
            Matcher r1 = WebPlayerView.m13636$$Nest$sfgetexprParensPattern().matcher(r112);
            int r4 = 0;
        L9:
            if (r1.find() == false) goto L18;
            if (r1.group(0).indexOf(48) == 40) goto L12;
            r4 = r4 - 1;
            if (r4 != 0) goto L9;
            interpretExpression(r112.substring(1, r1.start()), r12, r13);
            r112 = r112.substring(r1.end()).trim();
            if (TextUtils.isEmpty(r112) == false) goto L18;
            return;
        L12:
            r4 = r4 + 1;
        L18:
            if (r4 == 0) goto L22;
            throw new Exception(String.format("Premature end of parens in %s", new Object[]{r112}));
        L22:
            int r14 = 0;
        L23:
            String[] r2 = this.assign_operators;
            if (r14 >= r2.length) goto L95;
            Matcher r22 = Pattern.compile(String.format(Locale.US, "(?x)(%s)(?:\\[([^\\]]+?)\\])?\\s*%s(.*)$", new Object[]{"[a-zA-Z_$][a-zA-Z_$0-9]*", Pattern.quote(r2[r14])})).matcher(r112);
            if (r22.find() == true) goto L28;
            r14 = r14 + 1;
            goto L23
        L28:
            interpretExpression(r22.group(3), r12, r13 - 1);
            String r113 = r22.group(2);
            if (TextUtils.isEmpty(r113) == true) goto L32;
            interpretExpression(r113, r12, r13);
            return;
        L32:
            r12.put(r22.group(1), _UrlKt.FRAGMENT_ENCODE_SET);
            return;
        L95:
            Integer.parseInt(r112);     // Catch: Exception -> L36
            return;
        L37:
            if (Pattern.compile(String.format(Locale.US, "(?!if|return|true|false)(%s)$", new Object[]{"[a-zA-Z_$][a-zA-Z_$0-9]*"})).matcher(r112).find() == false) goto L40;
            return;
        L40:
            if (r112.charAt(0) == '\"') goto L42;
        L97:
            new JSONObject(r112).toString();     // Catch: Exception -> L46
            return;
        L46:
            Locale r15 = Locale.US;
            Matcher r23 = Pattern.compile(String.format(r15, "(%s)\\[(.+)\\]$", new Object[]{"[a-zA-Z_$][a-zA-Z_$0-9]*"})).matcher(r112);
            if (r23.find() == false) goto L50;
            r23.group(1);
            interpretExpression(r23.group(2), r12, r13 - 1);
            return;
        L50:
            Matcher r24 = Pattern.compile(String.format(r15, "(%s)(?:\\.([^(]+)|\\[([^]]+)\\])\\s*(?:\\(+([^()]*)\\))?$", new Object[]{"[a-zA-Z_$][a-zA-Z_$0-9]*"})).matcher(r112);
            if (r24.find() == false) goto L71;
            String r16 = r24.group(1);
            String r42 = r24.group(2);
            String r6 = r24.group(3);
            if (TextUtils.isEmpty(r42) == false) goto L55;
            r42 = r6;
        L55:
            r42.replace("\"", _UrlKt.FRAGMENT_ENCODE_SET);
            String r25 = r24.group(4);
            if (r12.get(r16) != null) goto L58;
            extractObject(r16);
        L58:
            if (r25 != null) goto L61;
            return;
        L61:
            if (r112.charAt(r112.length() - 1) != ')') goto L70;
            if (r25.length() == 0) goto L68;
            String[] r114 = r25.split(",");
        L66:
            if (r0 >= r114.length) goto L119;
            interpretExpression(r114[r0], r12, r13);
            r0 = r0 + 1;
            goto L66
        L119:
            return;
        L68:
            return;
        L70:
            throw new Exception("last char not ')'");
        L71:
            Matcher r17 = Pattern.compile(String.format(r15, "(%s)\\[(.+)\\]$", new Object[]{"[a-zA-Z_$][a-zA-Z_$0-9]*"})).matcher(r112);
            if (r17.find() == false) goto L75;
            r12.get(r17.group(1));
            interpretExpression(r17.group(2), r12, r13 - 1);
            return;
        L75:
            int r18 = 0;
        L76:
            String[] r26 = this.operators;
            if (r18 >= r26.length) goto L90;
            String r27 = r26[r18];
            Matcher r43 = Pattern.compile(String.format(Locale.US, "(.+?)%s(.+)", new Object[]{Pattern.quote(r27)})).matcher(r112);
            if (r43.find() == false) goto L85;
            boolean[] r5 = new boolean[1];
            int r9 = r13 - 1;
            interpretStatement(r43.group(1), r12, r5, r9);
            if (r5[0] == true) goto L89;
            interpretStatement(r43.group(2), r12, r5, r9);
            if (r5[0] == false) goto L85;
            throw new Exception(String.format("Premature right-side return of %s in %s", new Object[]{r27, r112}));
        L89:
            throw new Exception(String.format("Premature left-side return of %s in %s", new Object[]{r27, r112}));
        L85:
            r18 = r18 + 1;
            goto L76
        L90:
            Matcher r122 = Pattern.compile(String.format(Locale.US, "^(%s)\\(([a-zA-Z0-9_$,]*)\\)$", new Object[]{"[a-zA-Z_$][a-zA-Z_$0-9]*"})).matcher(r112);
            if (r122.find() == false) goto L94;
            extractFunction(r122.group(1));
        L94:
            throw new Exception(String.format("Unsupported JS expression %s", new Object[]{r112}));
        L42:
            if (r112.charAt(r112.length() - 1) != '\"') goto L97;
            return;
        }

        private void interpretStatement(String r4, HashMap<String, String> r5, boolean[] r6, int r7) {
            if (r7 < 0) goto L12;
            r6[0] = false;
            String r42 = r4.trim();
            Matcher r1 = WebPlayerView.m13642$$Nest$sfgetstmtVarPattern().matcher(r42);
            if (r1.find() == false) goto L6;
            r42 = r42.substring(r1.group(0).length());
        L9:
            interpretExpression(r42, r5, r7);
            return;
        L6:
            Matcher r12 = WebPlayerView.m13641$$Nest$sfgetstmtReturnPattern().matcher(r42);
            if (r12.find() == false) goto L9;
            r42 = r42.substring(r12.group(0).length());
            r6[0] = true;
            goto L9
        L12:
            throw new Exception("recursion limit reached");
        }
    }

    public static class JavaScriptInterface {
        private final CallJavaResultInterface callJavaResultInterface;

        public JavaScriptInterface(CallJavaResultInterface r1) {
            this.callJavaResultInterface = r1;
        }

        @JavascriptInterface
        @Keep
        public void returnResultToJava(String r1) {
            this.callJavaResultInterface.jsCallFinished(r1);
        }
    }

    public class TwitchClipVideoTask extends AsyncTask<Void, Void, String> {
        private boolean canRetry;
        private String currentUrl;
        private String[] results;
        final /* synthetic */ WebPlayerView this$0;
        private String videoId;

        public TwitchClipVideoTask(WebPlayerView r1, String r2, String r3) {
            this.this$0 = r1;
            this.canRetry = true;
            this.results = new String[2];
            this.videoId = r3;
            this.currentUrl = r2;
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ String doInBackground(Void[] r1) {
            return doInBackground2(r1);
        }

        /* renamed from: doInBackground, reason: avoid collision after fix types in other method */
        public String doInBackground2(Void... r6) {
            String r62 = this.this$0.downloadUrlContent(this, this.currentUrl, null, false);
            if (isCancelled() == false) goto L16;
            return null;
        L16:
            Matcher r63 = WebPlayerView.m13644$$Nest$sfgettwitchClipFilePattern().matcher(r62);     // Catch: Exception -> L9
            if (r63.find() == false) goto L12;
            JSONObject r64 = new JSONObject(r63.group(1)).getJSONArray("quality_options").getJSONObject(0);     // Catch: Exception -> L9
            this.results[0] = r64.getString("source");     // Catch: Exception -> L9
            this.results[1] = "other";     // Catch: Exception -> L9
        L12:
            if (isCancelled() == false) goto L15;
            return null;
        L15:
            return this.results[0];
        L9:
            e = move-exception;
            FileLog.e(e);
            goto L12
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ void onPostExecute(String r1) {
            onPostExecute2(r1);
        }

        /* renamed from: onPostExecute, reason: avoid collision after fix types in other method */
        public void onPostExecute2(String r3) {
            if (r3 == null) goto L9;
            WebPlayerView.m13617$$Nest$fputinitied(this.this$0, true);
            WebPlayerView.m13624$$Nest$fputplayVideoUrl(this.this$0, r3);
            WebPlayerView.m13623$$Nest$fputplayVideoType(this.this$0, this.results[1]);
            if (WebPlayerView.m13598$$Nest$fgetisAutoplay(this.this$0) == false) goto L6;
            WebPlayerView.m13629$$Nest$mpreparePlayer(this.this$0);
        L6:
            WebPlayerView.m13630$$Nest$mshowProgress(this.this$0, false, true);
            WebPlayerView.m13590$$Nest$fgetcontrolsView(this.this$0).show(true, true);
            return;
        L9:
            if (isCancelled() == true) goto L12;
            WebPlayerView.m13628$$Nest$monInitFailed(this.this$0);
            return;
        }
    }

    public class TwitchStreamVideoTask extends AsyncTask<Void, Void, String> {
        private boolean canRetry;
        private String currentUrl;
        private String[] results;
        final /* synthetic */ WebPlayerView this$0;
        private String videoId;

        public TwitchStreamVideoTask(WebPlayerView r1, String r2, String r3) {
            this.this$0 = r1;
            this.canRetry = true;
            this.results = new String[2];
            this.videoId = r3;
            this.currentUrl = r2;
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ String doInBackground(Void[] r1) {
            return doInBackground2(r1);
        }

        /* renamed from: doInBackground, reason: avoid collision after fix types in other method */
        public String doInBackground2(Void... r11) {
            HashMap<String, String> r2 = new HashMap();
            r2.put("Client-ID", "jzkbprff40iqj646a697cyrvl0zt2m6");
            int r3 = this.videoId.indexOf(38);
            if (r3 <= 0) goto L5;
            this.videoId = this.videoId.substring(0, r3);
        L5:
            WebPlayerView r32 = this.this$0;
            Locale r5 = Locale.US;
            String r33 = r32.downloadUrlContent(this, String.format(r5, "https://api.twitch.tv/kraken/streams/%s?stream_type=all", new Object[]{this.videoId}), r2, false);
            if (isCancelled() == false) goto L17;
            return null;
        L17:
            new JSONObject(r33).getJSONObject("stream");     // Catch: Exception -> L10
            JSONObject r34 = new JSONObject(this.this$0.downloadUrlContent(this, String.format(r5, "https://api.twitch.tv/api/channels/%s/access_token", new Object[]{this.videoId}), r2, false));     // Catch: Exception -> L10
            String r22 = URLEncoder.encode(r34.getString("sig"), "UTF-8");     // Catch: Exception -> L10
            String r35 = URLEncoder.encode(r34.getString("token"), "UTF-8");     // Catch: Exception -> L10
            URLEncoder.encode("https://youtube.googleapis.com/v/" + this.videoId, "UTF-8");     // Catch: Exception -> L10
            String r112 = String.format(r5, "https://usher.ttvnw.net/api/channel/hls/%s.m3u8?%s", new Object[]{this.videoId, "allow_source=true&allow_audio_only=true&allow_spectre=true&player=twitchweb&segment_preference=4&p=" + ((int) (Math.random() * 1.0E7d)) + "&sig=" + r22 + "&token=" + r35});     // Catch: Exception -> L10
            String[] r0 = this.results;     // Catch: Exception -> L10
            r0[0] = r112;     // Catch: Exception -> L10
            r0[1] = "hls";     // Catch: Exception -> L10
        L13:
            if (isCancelled() == false) goto L16;
            return null;
        L16:
            return this.results[0];
        L10:
            e = move-exception;
            FileLog.e(e);
            goto L13
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ void onPostExecute(String r1) {
            onPostExecute2(r1);
        }

        /* renamed from: onPostExecute, reason: avoid collision after fix types in other method */
        public void onPostExecute2(String r3) {
            if (r3 == null) goto L9;
            WebPlayerView.m13617$$Nest$fputinitied(this.this$0, true);
            WebPlayerView.m13624$$Nest$fputplayVideoUrl(this.this$0, r3);
            WebPlayerView.m13623$$Nest$fputplayVideoType(this.this$0, this.results[1]);
            if (WebPlayerView.m13598$$Nest$fgetisAutoplay(this.this$0) == false) goto L6;
            WebPlayerView.m13629$$Nest$mpreparePlayer(this.this$0);
        L6:
            WebPlayerView.m13630$$Nest$mshowProgress(this.this$0, false, true);
            WebPlayerView.m13590$$Nest$fgetcontrolsView(this.this$0).show(true, true);
            return;
        L9:
            if (isCancelled() == true) goto L12;
            WebPlayerView.m13628$$Nest$monInitFailed(this.this$0);
            return;
        }
    }

    public class VimeoVideoTask extends AsyncTask<Void, Void, String> {
        private boolean canRetry;
        private String[] results;
        final /* synthetic */ WebPlayerView this$0;
        private String videoId;

        public VimeoVideoTask(WebPlayerView r1, String r2) {
            this.this$0 = r1;
            this.canRetry = true;
            this.results = new String[2];
            this.videoId = r2;
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ String doInBackground(Void[] r1) {
            return doInBackground2(r1);
        }

        /* renamed from: doInBackground, reason: avoid collision after fix types in other method */
        public String doInBackground2(Void... r8) {
            String r1 = this.this$0.downloadUrlContent(this, String.format(Locale.US, "https://player.vimeo.com/video/%s/config", new Object[]{this.videoId}));
            if (isCancelled() == false) goto L28;
            return null;
        L28:
            JSONObject r12 = new JSONObject(r1).getJSONObject(PluginsConstants.REQUEST).getJSONObject("files");     // Catch: Exception -> L14
            if (r12.has("hls") == false) goto L17;
            JSONObject r82 = r12.getJSONObject("hls");     // Catch: Exception -> L14
            this.results[0] = r82.getString("url");     // Catch: Exception -> L12
        L13:
            this.results[1] = "hls";     // Catch: Exception -> L14
        L22:
            if (isCancelled() == false) goto L25;
            return null;
        L25:
            return this.results[0];
        L12:
            String r13 = r82.getString("default_cdn");     // Catch: Exception -> L14
            JSONObject r83 = r82.getJSONObject("cdns").getJSONObject(r13);     // Catch: Exception -> L14
            this.results[0] = r83.getString("url");     // Catch: Exception -> L14
            goto L13
        L17:
            if (r12.has("progressive") == false) goto L22;
            this.results[1] = "other";     // Catch: Exception -> L14
            JSONObject r84 = r12.getJSONArray("progressive").getJSONObject(0);     // Catch: Exception -> L14
            this.results[0] = r84.getString("url");     // Catch: Exception -> L14
        L14:
            e = move-exception;
            FileLog.e(e);
            goto L22
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ void onPostExecute(String r1) {
            onPostExecute2(r1);
        }

        /* renamed from: onPostExecute, reason: avoid collision after fix types in other method */
        public void onPostExecute2(String r3) {
            if (r3 == null) goto L9;
            WebPlayerView.m13617$$Nest$fputinitied(this.this$0, true);
            WebPlayerView.m13624$$Nest$fputplayVideoUrl(this.this$0, r3);
            WebPlayerView.m13623$$Nest$fputplayVideoType(this.this$0, this.results[1]);
            if (WebPlayerView.m13598$$Nest$fgetisAutoplay(this.this$0) == false) goto L6;
            WebPlayerView.m13629$$Nest$mpreparePlayer(this.this$0);
        L6:
            WebPlayerView.m13630$$Nest$mshowProgress(this.this$0, false, true);
            WebPlayerView.m13590$$Nest$fgetcontrolsView(this.this$0).show(true, true);
            return;
        L9:
            if (isCancelled() == true) goto L12;
            WebPlayerView.m13628$$Nest$monInitFailed(this.this$0);
            return;
        }
    }

    public interface WebPlayerViewDelegate {
        boolean checkInlinePermissions();

        ViewGroup getTextureViewContainer();

        void onInitFailed();

        void onInlineSurfaceTextureReady();

        void onPlayStateChanged(WebPlayerView r1, boolean r2);

        void onSharePressed();

        TextureView onSwitchInlineMode(View r1, boolean r2, int r3, int r4, int r5, boolean r6);

        TextureView onSwitchToFullscreen(View r1, boolean r2, float r3, int r4, boolean r5);

        void onVideoSizeChanged(float r1, int r2);

        void prepareToSwitchInlineMode(boolean r1, Runnable r2, float r3, boolean r4);
    }

    public class YoutubeVideoTask extends AsyncTask<Void, Void, String[]> {
        private boolean canRetry;
        private CountDownLatch countDownLatch;
        private String[] result;
        private String sig;
        final /* synthetic */ WebPlayerView this$0;
        private String videoId;

        public static /* synthetic */ void $r8$lambda$ZresQrqD5RYsMcNZCZmmRhOumzc(YoutubeVideoTask r0, String r1) {
            r0.lambda$doInBackground$0(r1);
        }

        /* renamed from: $r8$lambda$gCpwYw9nIrxs-laFqP-3C-XLNbY, reason: not valid java name */
        public static /* synthetic */ void m13649$r8$lambda$gCpwYw9nIrxslaFqP3CXLNbY(YoutubeVideoTask r0, String r1) {
            r0.lambda$doInBackground$1(r1);
        }

        /* renamed from: -$$Nest$monInterfaceResult, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13650$$Nest$monInterfaceResult(YoutubeVideoTask r0, String r1) {
            r0.onInterfaceResult(r1);
        }

        public YoutubeVideoTask(WebPlayerView r2, String r3) {
            this.this$0 = r2;
            this.canRetry = true;
            this.countDownLatch = new CountDownLatch(1);
            this.result = new String[2];
            this.videoId = r3;
        }

        private /* synthetic */ void lambda$doInBackground$0(String r7) {
            String[] r0 = this.result;
            r0[0] = r0[0].replace(this.sig, "/signature/".concat(r7.substring(1, r7.length() - 1)));
            this.countDownLatch.countDown();
        }

        private /* synthetic */ void lambda$doInBackground$1(String r3) {
            WebPlayerView.m13612$$Nest$fgetwebView(this.this$0).evaluateJavascript(r3, new WebPlayerView$YoutubeVideoTask$$ExternalSyntheticLambda1(this));
        }

        private void onInterfaceResult(String r7) {
            String[] r0 = this.result;
            r0[0] = r0[0].replace(this.sig, "/signature/" + r7);
            this.countDownLatch.countDown();
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ String[] doInBackground(Void[] r1) {
            return doInBackground2(r1);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: doInBackground, reason: avoid collision after fix types in other method */
        public String[] doInBackground2(Void... r25) {
            String r3 = this.this$0.downloadUrlContent(this, "https://www.youtube.com/embed/" + this.videoId);
            String[] r5 = null;
            if (isCancelled() == false) goto L5;
            return null;
        L5:
            String r4 = "video_id=" + this.videoId + "&ps=default&gl=US&hl=en";
            StringBuilder r6 = new StringBuilder();     // Catch: Exception -> L8
            r6.append(r4);     // Catch: Exception -> L8
            r6.append("&eurl=");     // Catch: Exception -> L8
            r6.append(URLEncoder.encode("https://youtube.googleapis.com/v/" + this.videoId, "UTF-8"));     // Catch: Exception -> L8
            r4 = r6.toString();     // Catch: Exception -> L8
        L10:
            if (r3 == null) goto L15;
            Matcher r0 = WebPlayerView.m13643$$Nest$sfgetstsPattern().matcher(r3);
            if (r0.find() == false) goto L14;
            r4 = r4 + "&sts=" + r3.substring(r0.start() + 6, r0.end());
            goto L15
        L14:
            r4 = r4.concat("&sts=");
        L15:
            int r7 = 1;
            this.result[1] = "dash";
            String[] r62 = {_UrlKt.FRAGMENT_ENCODE_SET, "&el=leanback", "&el=embedded", "&el=detailpage", "&el=vevo"};
            int r8 = 0;
            Object r10 = null;
            int r02 = 0;
            int r9 = 0;
        L16:
            int r12 = 2;
            if (r9 >= 5) goto L105;
            String r13 = this.this$0.downloadUrlContent(this, "https://www.youtube.com/get_video_info?" + r4 + r62[r9]);
            if (isCancelled() == true) goto L20;
            if (r13 == null) goto L93;
            String[] r132 = r13.split("&");
            Object r16 = r5;
            int r15 = r8;
            int r17 = r15;
            int r18 = r17;
            Object r14 = r10;
            int r102 = r02;
        L24:
            if (r15 >= r132.length) goto L91;
            String[] r252 = r5;
            if (r132[r15].startsWith("dashmpd") == false) goto L34;
            String[] r03 = r132[r15].split("=");
            if (r03.length == r12) goto L186;
        L33:
            String r23 = r4;
            r18 = r7;
            int r20 = r18;
            int r19 = r8;
        L90:
            r15 = r15 + 1;
            r5 = r252;
            r8 = r19;
            r7 = r20;
            r4 = r23;
            r12 = 2;
            goto L24
        L186:
            this.result[r8] = URLDecoder.decode(r03[r7], "UTF-8");     // Catch: Exception -> L31
        L31:
            e = move-exception;
            FileLog.e(e);
            goto L33
        L34:
            r19 = r8;
            if (r132[r15].startsWith("url_encoded_fmt_stream_map") == false) goto L68;
            String[] r04 = r132[r15].split("=");
            if (r04.length == r12) goto L198;
            r23 = r4;
            r20 = r7;
            goto L90
        L198:
            String[] r05 = URLDecoder.decode(r04[r7], "UTF-8").split("[&,]");     // Catch: Exception -> L64
            Object r82 = r252;
            r20 = r7;
            int r72 = r19;
            int r21 = r72;
        L192:
            if (r72 >= r05.length) goto L63;
            String[] r122 = r05[r72].split("=");     // Catch: Exception -> L61
            String[] r22 = r05;
            r23 = r4;
            if (r122[r19].startsWith("type") == false) goto L52;
            if (URLDecoder.decode(r122[r20], "UTF-8").contains("video/mp4") == false) goto L57;
            r21 = r20;
        L57:
            if (r21 == 0) goto L60;
            if (r82 == null) goto L60;
            r14 = r82;
        L60:
            r72 = r72 + 1;
            r05 = r22;
            r4 = r23;
            goto L192
        L52:
            if (r122[r19].startsWith("url") == false) goto L55;
            r82 = URLDecoder.decode(r122[r20], "UTF-8");     // Catch: Exception -> L49
            goto L57
        L55:
            if (r122[r19].startsWith("itag") == false) goto L57;
            r82 = r252;
            r21 = r19;
        L49:
            e = e;
        L66:
            FileLog.e(e);
            goto L90
        L63:
            r23 = r4;
        L61:
            e = e;
            r23 = r4;
        L64:
            e = e;
            r23 = r4;
            r20 = r7;
            goto L66
        L68:
            r23 = r4;
            r20 = r7;
            if (r132[r15].startsWith("use_cipher_signature") == false) goto L76;
            String[] r06 = r132[r15].split("=");
            if (r06.length != 2) goto L90;
            if (r06[r20].toLowerCase().equals("true") == false) goto L90;
            r102 = r20;
            goto L90
        L76:
            if (r132[r15].startsWith("hlsvp") == false) goto L84;
            String[] r07 = r132[r15].split("=");
            if (r07.length != 2) goto L90;
            r16 = URLDecoder.decode(r07[r20], "UTF-8");     // Catch: Exception -> L81
        L81:
            e = move-exception;
            FileLog.e(e);
            goto L90
        L84:
            if (r132[r15].startsWith("livestream") == false) goto L90;
            String[] r08 = r132[r15].split("=");
            if (r08.length != 2) goto L90;
            if (r08[r20].toLowerCase().equals("1") == false) goto L90;
            r17 = r20;
            goto L90
        L91:
            String r232 = r4;
            String[] r253 = r5;
            int r192 = r8;
            r02 = r102;
            r10 = r14;
            String r42 = r16;
        L92:
            int r202 = r7;
            if (r17 == 0) goto L102;
            if (r42 == 0) goto L101;
            if (r02 != 0) goto L101;
            if (r42.contains("/s/") == true) goto L101;
            String[] r52 = this.result;
            r52[r192] = r42;
            r52[r202] = "hls";
        L101:
            return r253;
        L102:
            if (r18 != 0) goto L106;
            r9 = r9 + 1;
            r5 = r253;
            r8 = r192;
            r7 = r202;
            r4 = r232;
        L106:
            String[] r2 = this.result;
            if (r2[r192] != 0) goto L110;
            if (r10 == null) goto L110;
            r2[r192] = r10;
            r2[r202] = "other";
        L110:
            String r24 = r2[r192];
            if (r24 == 0) goto L116;
            if (r02 == 0) goto L114;
        L117:
            if (r3 == null) goto L116;
            int r09 = this.result[r192].indexOf("/s/");
            int r26 = this.result[r192].indexOf(47, r09 + 10);
            if (r09 == (-1)) goto L177;
            if (r26 != (-1)) goto L122;
            r26 = this.result[r192].length();
        L122:
            this.sig = this.result[r192].substring(r09, r26);
            Matcher r010 = WebPlayerView.m13637$$Nest$sfgetjsPattern().matcher(r3);
            if (r010.find() == true) goto L196;
        L130:
            String r011 = r253;
        L131:
            if (r011 == 0) goto L176;
            Matcher r27 = WebPlayerView.m13638$$Nest$sfgetplayerIdPattern().matcher(r011);
            if (r27.find() == false) goto L135;
            String r28 = r27.group(1) + r27.group(2);
        L136:
            int r53 = r192;
            SharedPreferences r32 = ApplicationLoader.applicationContext.getSharedPreferences("youtubecode", r53);
            if (r28 == 0) goto L139;
            String r63 = r253;
            String r73 = r32.getString(r28, r63);
            String r83 = r32.getString(r28.concat("n"), r63);
            String r64 = r73;
        L140:
            if (r64 == null) goto L142;
            String[] r74 = null;
        L167:
            int r103 = 1;
        L170:
            if (TextUtils.isEmpty(r64) == false) goto L200;
        L178:
            r02 = r103;
        L180:
            if (isCancelled() == true) goto L185;
            if (r02 != 0) goto L185;
            return this.result;
        L185:
            return r74;
        L200:
            AndroidUtilities.runOnUIThread(new WebPlayerView$YoutubeVideoTask$$ExternalSyntheticLambda0(this, r64 + r83 + "('" + this.sig.substring(3) + "');"));     // Catch: Exception -> L174
            this.countDownLatch.await();     // Catch: Exception -> L174
            r02 = r53;
        L174:
            e = move-exception;
            FileLog.e(e);
            goto L178
        L142:
            if (r011.startsWith("//") == false) goto L144;
            String r012 = "https:".concat(r011);
        L147:
            String r013 = this.this$0.downloadUrlContent(this, r012);
            if (isCancelled() == false) goto L151;
            return null;
        L151:
            r74 = null;
            if (r013 == null) goto L167;
            Matcher r92 = WebPlayerView.m13639$$Nest$sfgetsigPattern().matcher(r013);
            if (r92.find() == false) goto L156;
            r103 = 1;
            r83 = r92.group(1);
        L159:
            if (r83 == null) goto L170;
            r64 = JSExtractor.m13648$$Nest$mextractFunction(new JSExtractor(r013), r83);     // Catch: Exception -> L165
            if (TextUtils.isEmpty(r64) == true) goto L170;
            if (r28 == 0) goto L170;
            r32.edit().putString(r28, r64).putString(r28 + "n", r83).apply();     // Catch: Exception -> L165
        L165:
            e = move-exception;
            FileLog.e(e);
            goto L170
        L156:
            r103 = 1;
            Matcher r93 = WebPlayerView.m13640$$Nest$sfgetsigPattern2().matcher(r013);
            if (r93.find() == false) goto L159;
            r83 = r93.group(1);
            goto L159
        L144:
            boolean r75 = r011.startsWith("/");
            r012 = r011;
            if (r75 == false) goto L147;
            r012 = "https://www.youtube.com".concat(r011);
            goto L147
        L139:
            r64 = null;
            r83 = null;
            goto L140
        L135:
            r28 = r253;
            goto L136
        L176:
            r74 = r253;
            r103 = 1;
            goto L178
        L196:
            Object r014 = new JSONTokener(r010.group(r202)).nextValue();     // Catch: Exception -> L128
            if ((r014 instanceof String) == false) goto L130;
            r011 = (String) r014;     // Catch: Exception -> L128
        L128:
            e = move-exception;
            FileLog.e(e);
            goto L130
        L177:
            r74 = r253;
            r103 = r202;
            goto L178
        L114:
            if (r24.contains("/s/") == true) goto L117;
        L116:
            r74 = r253;
            goto L180
        L93:
            r232 = r4;
            r253 = r5;
            r192 = r8;
            r42 = r253;
            r17 = r192;
            r18 = r17;
            goto L92
        L20:
            return r5;
        L105:
            r253 = r5;
            r202 = r7;
            r192 = r8;
        L8:
            e = move-exception;
            FileLog.e(e);
            goto L10
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ void onPostExecute(String[] r1) {
            onPostExecute2(r1);
        }

        /* renamed from: onPostExecute, reason: avoid collision after fix types in other method */
        public void onPostExecute2(String[] r5) {
            if (r5[0] == null) goto L16;
            if (BuildVars.LOGS_ENABLED == false) goto L7;
            FileLog.d("start play youtube video " + r5[1] + " " + r5[0]);
        L7:
            WebPlayerView.m13617$$Nest$fputinitied(this.this$0, true);
            WebPlayerView.m13624$$Nest$fputplayVideoUrl(this.this$0, r5[0]);
            WebPlayerView.m13623$$Nest$fputplayVideoType(this.this$0, r5[1]);
            if (WebPlayerView.m13602$$Nest$fgetplayVideoType(this.this$0).equals("hls") == false) goto L11;
            WebPlayerView.m13619$$Nest$fputisStream(this.this$0, true);
        L11:
            if (WebPlayerView.m13598$$Nest$fgetisAutoplay(this.this$0) == false) goto L13;
            WebPlayerView.m13629$$Nest$mpreparePlayer(this.this$0);
        L13:
            WebPlayerView.m13630$$Nest$mshowProgress(this.this$0, false, true);
            WebPlayerView.m13590$$Nest$fgetcontrolsView(this.this$0).show(true, true);
            return;
        L16:
            if (isCancelled() == true) goto L19;
            WebPlayerView.m13628$$Nest$monInitFailed(this.this$0);
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$IpGSB0lRzqK7zaZKWKScHo20hZE(WebPlayerView r0, View r1) {
        r0.lambda$new$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$KBOikSQK6YqOpqM53kpY4V5ztp4(WebPlayerView r0, String r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$nqJcmIw7CA8096inR7Xvtyl78LU(WebPlayerView r0, View r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$qHmEAhoIqUaJXmumzz8SW4WfY3E(WebPlayerView r0, View r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: $r8$lambda$srp8F-H4e2lKMJpoIpppGYVN4No, reason: not valid java name */
    public static /* synthetic */ void m13585$r8$lambda$srp8FH4e2lKMJpoIpppGYVN4No(WebPlayerView r0, int r1) {
        r0.lambda$onAudioFocusChange$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$tO9NeGRxs7AM6VhJyW5PTG681Lw(WebPlayerView r0, View r1) {
        r0.lambda$new$3(r1);
    }

    /* renamed from: -$$Nest$fgetallowInlineAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13586$$Nest$fgetallowInlineAnimation(WebPlayerView r0) {
        return r0.allowInlineAnimation;
    }

    /* renamed from: -$$Nest$fgetaspectRatioFrameLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AspectRatioFrameLayout m13587$$Nest$fgetaspectRatioFrameLayout(WebPlayerView r0) {
        return r0.aspectRatioFrameLayout;
    }

    /* renamed from: -$$Nest$fgetchangedTextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextureView m13588$$Nest$fgetchangedTextureView(WebPlayerView r0) {
        return r0.changedTextureView;
    }

    /* renamed from: -$$Nest$fgetchangingTextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13589$$Nest$fgetchangingTextureView(WebPlayerView r0) {
        return r0.changingTextureView;
    }

    /* renamed from: -$$Nest$fgetcontrolsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ControlsView m13590$$Nest$fgetcontrolsView(WebPlayerView r0) {
        return r0.controlsView;
    }

    /* renamed from: -$$Nest$fgetcurrentAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13591$$Nest$fgetcurrentAlpha(WebPlayerView r0) {
        return r0.currentAlpha;
    }

    /* renamed from: -$$Nest$fgetcurrentBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Bitmap m13592$$Nest$fgetcurrentBitmap(WebPlayerView r0) {
        return r0.currentBitmap;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WebPlayerViewDelegate m13593$$Nest$fgetdelegate(WebPlayerView r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetdrawImage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13594$$Nest$fgetdrawImage(WebPlayerView r0) {
        return r0.drawImage;
    }

    /* renamed from: -$$Nest$fgetfirstFrameRendered, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13595$$Nest$fgetfirstFrameRendered(WebPlayerView r0) {
        return r0.firstFrameRendered;
    }

    /* renamed from: -$$Nest$fgetinFullscreen, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13596$$Nest$fgetinFullscreen(WebPlayerView r0) {
        return r0.inFullscreen;
    }

    /* renamed from: -$$Nest$fgetinitied, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13597$$Nest$fgetinitied(WebPlayerView r0) {
        return r0.initied;
    }

    /* renamed from: -$$Nest$fgetisAutoplay, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13598$$Nest$fgetisAutoplay(WebPlayerView r0) {
        return r0.isAutoplay;
    }

    /* renamed from: -$$Nest$fgetisInline, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13599$$Nest$fgetisInline(WebPlayerView r0) {
        return r0.isInline;
    }

    /* renamed from: -$$Nest$fgetisStream, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13600$$Nest$fgetisStream(WebPlayerView r0) {
        return r0.isStream;
    }

    /* renamed from: -$$Nest$fgetlastUpdateTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m13601$$Nest$fgetlastUpdateTime(WebPlayerView r2) {
        return r2.lastUpdateTime;
    }

    /* renamed from: -$$Nest$fgetplayVideoType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m13602$$Nest$fgetplayVideoType(WebPlayerView r0) {
        return r0.playVideoType;
    }

    /* renamed from: -$$Nest$fgetprogressRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m13603$$Nest$fgetprogressRunnable(WebPlayerView r0) {
        return r0.progressRunnable;
    }

    /* renamed from: -$$Nest$fgetswitchingInlineMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13604$$Nest$fgetswitchingInlineMode(WebPlayerView r0) {
        return r0.switchingInlineMode;
    }

    /* renamed from: -$$Nest$fgettextureImageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m13605$$Nest$fgettextureImageView(WebPlayerView r0) {
        return r0.textureImageView;
    }

    /* renamed from: -$$Nest$fgettextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextureView m13606$$Nest$fgettextureView(WebPlayerView r0) {
        return r0.textureView;
    }

    /* renamed from: -$$Nest$fgettextureViewContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewGroup m13607$$Nest$fgettextureViewContainer(WebPlayerView r0) {
        return r0.textureViewContainer;
    }

    /* renamed from: -$$Nest$fgetvideoHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13608$$Nest$fgetvideoHeight(WebPlayerView r0) {
        return r0.videoHeight;
    }

    /* renamed from: -$$Nest$fgetvideoPlayer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VideoPlayer m13609$$Nest$fgetvideoPlayer(WebPlayerView r0) {
        return r0.videoPlayer;
    }

    /* renamed from: -$$Nest$fgetvideoWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13610$$Nest$fgetvideoWidth(WebPlayerView r0) {
        return r0.videoWidth;
    }

    /* renamed from: -$$Nest$fgetwaitingForFirstTextureUpload, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13611$$Nest$fgetwaitingForFirstTextureUpload(WebPlayerView r0) {
        return r0.waitingForFirstTextureUpload;
    }

    /* renamed from: -$$Nest$fgetwebView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WebView m13612$$Nest$fgetwebView(WebPlayerView r0) {
        return r0.webView;
    }

    /* renamed from: -$$Nest$fputchangedTextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13613$$Nest$fputchangedTextureView(WebPlayerView r0, TextureView r1) {
        r0.changedTextureView = r1;
    }

    /* renamed from: -$$Nest$fputchangingTextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13614$$Nest$fputchangingTextureView(WebPlayerView r0, boolean r1) {
        r0.changingTextureView = r1;
    }

    /* renamed from: -$$Nest$fputcurrentAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13615$$Nest$fputcurrentAlpha(WebPlayerView r0, float r1) {
        r0.currentAlpha = r1;
    }

    /* renamed from: -$$Nest$fputcurrentBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13616$$Nest$fputcurrentBitmap(WebPlayerView r0, Bitmap r1) {
        r0.currentBitmap = r1;
    }

    /* renamed from: -$$Nest$fputinitied, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13617$$Nest$fputinitied(WebPlayerView r0, boolean r1) {
        r0.initied = r1;
    }

    /* renamed from: -$$Nest$fputisInline, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13618$$Nest$fputisInline(WebPlayerView r0, boolean r1) {
        r0.isInline = r1;
    }

    /* renamed from: -$$Nest$fputisStream, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13619$$Nest$fputisStream(WebPlayerView r0, boolean r1) {
        r0.isStream = r1;
    }

    /* renamed from: -$$Nest$fputlastUpdateTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13620$$Nest$fputlastUpdateTime(WebPlayerView r0, long r1) {
        r0.lastUpdateTime = r1;
    }

    /* renamed from: -$$Nest$fputplayAudioType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13621$$Nest$fputplayAudioType(WebPlayerView r0, String r1) {
        r0.playAudioType = r1;
    }

    /* renamed from: -$$Nest$fputplayAudioUrl, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13622$$Nest$fputplayAudioUrl(WebPlayerView r0, String r1) {
        r0.playAudioUrl = r1;
    }

    /* renamed from: -$$Nest$fputplayVideoType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13623$$Nest$fputplayVideoType(WebPlayerView r0, String r1) {
        r0.playVideoType = r1;
    }

    /* renamed from: -$$Nest$fputplayVideoUrl, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13624$$Nest$fputplayVideoUrl(WebPlayerView r0, String r1) {
        r0.playVideoUrl = r1;
    }

    /* renamed from: -$$Nest$fputprogressAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13625$$Nest$fputprogressAnimation(WebPlayerView r0, AnimatorSet r1) {
        r0.progressAnimation = r1;
    }

    /* renamed from: -$$Nest$fputswitchingInlineMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13626$$Nest$fputswitchingInlineMode(WebPlayerView r0, boolean r1) {
        r0.switchingInlineMode = r1;
    }

    /* renamed from: -$$Nest$fputwaitingForFirstTextureUpload, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13627$$Nest$fputwaitingForFirstTextureUpload(WebPlayerView r0, int r1) {
        r0.waitingForFirstTextureUpload = r1;
    }

    /* renamed from: -$$Nest$monInitFailed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13628$$Nest$monInitFailed(WebPlayerView r0) {
        r0.onInitFailed();
    }

    /* renamed from: -$$Nest$mpreparePlayer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13629$$Nest$mpreparePlayer(WebPlayerView r0) {
        r0.preparePlayer();
    }

    /* renamed from: -$$Nest$mshowProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13630$$Nest$mshowProgress(WebPlayerView r0, boolean r1, boolean r2) {
        r0.showProgress(r1, r2);
    }

    /* renamed from: -$$Nest$mupdateFullscreenButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13631$$Nest$mupdateFullscreenButton(WebPlayerView r0) {
        r0.updateFullscreenButton();
    }

    /* renamed from: -$$Nest$mupdateInlineButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13632$$Nest$mupdateInlineButton(WebPlayerView r0) {
        r0.updateInlineButton();
    }

    /* renamed from: -$$Nest$mupdatePlayButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13633$$Nest$mupdatePlayButton(WebPlayerView r0) {
        r0.updatePlayButton();
    }

    /* renamed from: -$$Nest$mupdateShareButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13634$$Nest$mupdateShareButton(WebPlayerView r0) {
        r0.updateShareButton();
    }

    /* renamed from: -$$Nest$sfgetaparatFileListPattern, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Pattern m13635$$Nest$sfgetaparatFileListPattern() {
        return aparatFileListPattern;
    }

    /* renamed from: -$$Nest$sfgetexprParensPattern, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Pattern m13636$$Nest$sfgetexprParensPattern() {
        return exprParensPattern;
    }

    /* renamed from: -$$Nest$sfgetjsPattern, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Pattern m13637$$Nest$sfgetjsPattern() {
        return jsPattern;
    }

    /* renamed from: -$$Nest$sfgetplayerIdPattern, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Pattern m13638$$Nest$sfgetplayerIdPattern() {
        return playerIdPattern;
    }

    /* renamed from: -$$Nest$sfgetsigPattern, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Pattern m13639$$Nest$sfgetsigPattern() {
        return sigPattern;
    }

    /* renamed from: -$$Nest$sfgetsigPattern2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Pattern m13640$$Nest$sfgetsigPattern2() {
        return sigPattern2;
    }

    /* renamed from: -$$Nest$sfgetstmtReturnPattern, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Pattern m13641$$Nest$sfgetstmtReturnPattern() {
        return stmtReturnPattern;
    }

    /* renamed from: -$$Nest$sfgetstmtVarPattern, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Pattern m13642$$Nest$sfgetstmtVarPattern() {
        return stmtVarPattern;
    }

    /* renamed from: -$$Nest$sfgetstsPattern, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Pattern m13643$$Nest$sfgetstsPattern() {
        return stsPattern;
    }

    /* renamed from: -$$Nest$sfgettwitchClipFilePattern, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Pattern m13644$$Nest$sfgettwitchClipFilePattern() {
        return twitchClipFilePattern;
    }

    static {
        youtubeIdRegex = Pattern.compile("(?:youtube(?:-nocookie)?\\.com/(?:[^/\\n\\s]+/\\S+/|(?:v|e(?:mbed)?)/|\\S*?[?&]v=)|youtu\\.be/)([a-zA-Z0-9_-]{11})");
        vimeoIdRegex = Pattern.compile("https?://(?:(?:www|(player))\\.)?vimeo(pro)?\\.com/(?!(?:channels|album)/[^/?#]+/?(?:$|[?#])|[^/]+/review/|ondemand/)(?:.*?/)?(?:(?:play_redirect_hls|moogaloop\\.swf)\\?clip_id=)?(?:videos?/)?([0-9]+)(?:/[\\da-f]+)?/?(?:[?&].*)?(?:[#].*)?$");
        coubIdRegex = Pattern.compile("(?:coub:|https?://(?:coub\\.com/(?:view|embed|coubs)/|c-cdn\\.coub\\.com/fb-player\\.swf\\?.*\\bcoub(?:ID|id)=))([\\da-z]+)");
        aparatIdRegex = Pattern.compile("^https?://(?:www\\.)?aparat\\.com/(?:v/|video/video/embed/videohash/)([a-zA-Z0-9]+)");
        twitchClipIdRegex = Pattern.compile("https?://clips\\.twitch\\.tv/(?:[^/]+/)*([^/?#&]+)");
        twitchStreamIdRegex = Pattern.compile("https?://(?:(?:www\\.)?twitch\\.tv/|player\\.twitch\\.tv/\\?.*?\\bchannel=)([^/#?]+)");
        aparatFileListPattern = Pattern.compile("fileList\\s*=\\s*JSON\\.parse\\('([^']+)'\\)");
        twitchClipFilePattern = Pattern.compile("clipInfo\\s*=\\s*(\\{[^']+\\});");
        stsPattern = Pattern.compile("\"sts\"\\s*:\\s*(\\d+)");
        jsPattern = Pattern.compile("\"assets\":.+?\"js\":\\s*(\"[^\"]+\")");
        sigPattern = Pattern.compile("\\.sig\\|\\|([a-zA-Z0-9$]+)\\(");
        sigPattern2 = Pattern.compile("[\"']signature[\"']\\s*,\\s*([a-zA-Z0-9$]+)\\(");
        stmtVarPattern = Pattern.compile("var\\s");
        stmtReturnPattern = Pattern.compile("return(?:\\s+|$)");
        exprParensPattern = Pattern.compile("[()]");
        playerIdPattern = Pattern.compile(".*?-([a-zA-Z0-9_-]+)(?:/watch_as3|/html5player(?:-new)?|(?:/[a-z]{2}_[A-Z]{2})?/base)?\\.([a-z]+)$");
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    public WebPlayerView(Context r12, boolean r13, boolean r14, WebPlayerViewDelegate r15) {
        super(r12);
        int r0 = lastContainerId;
        lastContainerId = r0 + 1;
        this.fragment_container_id = r0;
        this.allowInlineAnimation = true;
        this.backgroundPaint = new Paint();
        this.progressRunnable = new AnonymousClass1(this);
        this.surfaceTextureListener = new AnonymousClass2(this);
        this.switchToInlineRunnable = new AnonymousClass3(this);
        setWillNotDraw(false);
        this.delegate = r15;
        this.backgroundPaint.setColor(-16777216);
        AnonymousClass4 r152 = new AnonymousClass4(this, r12);
        this.aspectRatioFrameLayout = r152;
        addView(r152, LayoutHelper.createFrame(-1, -1, 17));
        this.interfaceName = "JavaScriptInterface";
        AnonymousClass5 r153 = new AnonymousClass5(this, r12, r12);
        this.webView = r153;
        r153.addJavascriptInterface(new JavaScriptInterface(new WebPlayerView$$ExternalSyntheticLambda0(this)), this.interfaceName);
        WebSettings r154 = this.webView.getSettings();
        r154.setJavaScriptEnabled(true);
        r154.setDefaultTextEncodingName("utf-8");
        this.textureViewContainer = this.delegate.getTextureViewContainer();
        TextureView r155 = new TextureView(r12);
        this.textureView = r155;
        r155.setPivotX(0.0f);
        this.textureView.setPivotY(0.0f);
        ViewGroup r156 = this.textureViewContainer;
        if (r156 == null) goto L5;
        r156.addView(this.textureView);
    L7:
        if (this.allowInlineAnimation == true) goto L9;
    L11:
        VideoPlayer r157 = new VideoPlayer();
        this.videoPlayer = r157;
        r157.setDelegate(this);
        this.videoPlayer.setTextureView(this.textureView);
        ControlsView r158 = new ControlsView(this, r12);
        this.controlsView = r158;
        ViewGroup r02 = this.textureViewContainer;
        if (r02 == null) goto L14;
        r02.addView(r158);
    L15:
        RadialProgressView r159 = new RadialProgressView(r12);
        this.progressView = r159;
        r159.setProgressColor(-1);
        addView(this.progressView, LayoutHelper.createFrame(48, 48, 17));
        ImageView r1510 = new ImageView(r12);
        this.fullscreenButton = r1510;
        ImageView.ScaleType r1 = ImageView.ScaleType.CENTER;
        r1510.setScaleType(r1);
        this.controlsView.addView(this.fullscreenButton, LayoutHelper.createFrame(56, 56.0f, 85, 0.0f, 0.0f, 0.0f, 5.0f));
        this.fullscreenButton.setOnClickListener(new WebPlayerView$$ExternalSyntheticLambda1(this));
        ImageView r1511 = new ImageView(r12);
        this.playButton = r1511;
        r1511.setScaleType(r1);
        this.controlsView.addView(this.playButton, LayoutHelper.createFrame(48, 48, 17));
        this.playButton.setOnClickListener(new WebPlayerView$$ExternalSyntheticLambda2(this));
        if (r13 == false) goto L18;
        ImageView r132 = new ImageView(r12);
        this.inlineButton = r132;
        r132.setScaleType(r1);
        this.controlsView.addView(this.inlineButton, LayoutHelper.createFrame(56, 48, 53));
        this.inlineButton.setOnClickListener(new WebPlayerView$$ExternalSyntheticLambda3(this));
    L18:
        if (r14 == false) goto L20;
        ImageView r133 = new ImageView(r12);
        this.shareButton = r133;
        r133.setScaleType(r1);
        this.shareButton.setImageResource(R.drawable.ic_share_video);
        this.controlsView.addView(this.shareButton, LayoutHelper.createFrame(56, 48, 53));
        this.shareButton.setOnClickListener(new WebPlayerView$$ExternalSyntheticLambda4(this));
    L20:
        updatePlayButton();
        updateFullscreenButton();
        updateInlineButton();
        updateShareButton();
        return;
    L14:
        addView(r158, LayoutHelper.createFrame(-1, -1.0f));
        goto L15
    L9:
        if (this.textureViewContainer == null) goto L11;
        ImageView r1512 = new ImageView(r12);
        this.textureImageView = r1512;
        r1512.setBackgroundColor(Opcodes.V_PREVIEW);
        this.textureImageView.setPivotX(0.0f);
        this.textureImageView.setPivotY(0.0f);
        this.textureImageView.setVisibility(4);
        this.textureViewContainer.addView(this.textureImageView);
        goto L11
    L5:
        this.aspectRatioFrameLayout.addView(this.textureView, LayoutHelper.createFrame(-1, -1, 17));
        goto L7
    }

    private void checkAudioFocus() {
        if (this.hasAudioFocus == true) goto L8;
        AudioManager r0 = (AudioManager) ApplicationLoader.applicationContext.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        this.hasAudioFocus = true;
        if (r0.requestAudioFocus(this, 3, 1) != 1) goto L9;
        this.audioFocus = 2;
        return;
    L9:
        return;
    }

    private View getControlView() {
        return this.controlsView;
    }

    private View getProgressView() {
        return this.progressView;
    }

    public static String getYouTubeVideoId(String r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        Matcher r22 = youtubeIdRegex.matcher(r2);
        if (r22.find() == true) goto L9;
        return null;
    L9:
        return r22.group(1);
    }

    private /* synthetic */ void lambda$new$0(String r2) {
        AsyncTask r0 = this.currentTask;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isCancelled() == true) goto L11;
        AsyncTask r1 = this.currentTask;
        if ((r1 instanceof YoutubeVideoTask) == false) goto L12;
        YoutubeVideoTask.m13650$$Nest$monInterfaceResult((YoutubeVideoTask) r1, r2);
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$new$1(View r2) {
        if (this.initied == true) goto L5;
        return;
    L5:
        if (this.changingTextureView == false) goto L7;
        return;
    L7:
        if (this.switchingInlineMode == false) goto L9;
        return;
    L9:
        if (this.firstFrameRendered == false) goto L16;
        this.inFullscreen = !this.inFullscreen;
        updateFullscreenState(true);
        return;
    }

    private /* synthetic */ void lambda$new$2(View r1) {
        if (this.initied == true) goto L5;
        return;
    L5:
        if (this.playVideoUrl != null) goto L8;
        return;
    L8:
        if (this.videoPlayer.isPlayerPrepared() == true) goto L11;
        preparePlayer();
    L11:
        if (this.videoPlayer.isPlaying() == false) goto L13;
        this.videoPlayer.pause();
    L14:
        updatePlayButton();
        return;
    L13:
        this.isCompleted = false;
        this.videoPlayer.play();
        goto L14
    }

    private /* synthetic */ void lambda$new$3(View r7) {
        if (this.textureView != null) goto L5;
        return;
    L5:
        if (this.delegate.checkInlinePermissions() == true) goto L7;
        return;
    L7:
        if (this.changingTextureView == false) goto L9;
        return;
    L9:
        if (this.switchingInlineMode == false) goto L11;
        return;
    L11:
        if (this.firstFrameRendered == false) goto L43;
        this.switchingInlineMode = true;
        if (this.isInline == true) goto L17;
        this.inFullscreen = false;
        this.delegate.prepareToSwitchInlineMode(true, this.switchToInlineRunnable, this.aspectRatioFrameLayout.getAspectRatio(), this.allowInlineAnimation);
        return;
    L17:
        ViewGroup r0 = (ViewGroup) this.aspectRatioFrameLayout.getParent();
        if (r0 == this) goto L22;
        if (r0 == null) goto L21;
        r0.removeView(this.aspectRatioFrameLayout);
    L21:
        addView(this.aspectRatioFrameLayout, 0, LayoutHelper.createFrame(-1, -1, 17));
        this.aspectRatioFrameLayout.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() - AndroidUtilities.dp(10.0f), TLObject.FLAG_30));
    L22:
        Bitmap r02 = this.currentBitmap;
        if (r02 == null) goto L25;
        r02.recycle();
        this.currentBitmap = null;
    L25:
        this.changingTextureView = true;
        this.isInline = false;
        updatePlayButton();
        updateShareButton();
        updateFullscreenButton();
        updateInlineButton();
        this.textureView.setVisibility(4);
        ViewGroup r03 = this.textureViewContainer;
        if (r03 == null) goto L28;
        r03.addView(this.textureView);
    L29:
        ViewGroup r04 = (ViewGroup) this.controlsView.getParent();
        if (r04 == this) goto L37;
        if (r04 == null) goto L33;
        r04.removeView(this.controlsView);
    L33:
        ViewGroup r05 = this.textureViewContainer;
        ControlsView r3 = this.controlsView;
        if (r05 == null) goto L36;
        r05.addView(r3);
        goto L37
    L36:
        addView(r3, 1);
    L37:
        this.controlsView.show(false, false);
        this.delegate.prepareToSwitchInlineMode(false, null, this.aspectRatioFrameLayout.getAspectRatio(), this.allowInlineAnimation);
        return;
    L28:
        this.aspectRatioFrameLayout.addView(this.textureView);
        goto L29
    }

    private /* synthetic */ void lambda$new$4(View r1) {
        WebPlayerViewDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onSharePressed();
        return;
    }

    private /* synthetic */ void lambda$onAudioFocusChange$5(int r4) {
        if (r4 != (-1)) goto L10;
        if (this.videoPlayer.isPlaying() == false) goto L7;
        this.videoPlayer.pause();
        updatePlayButton();
    L7:
        this.hasAudioFocus = false;
        this.audioFocus = 0;
        return;
    L10:
        if (r4 != 1) goto L16;
        this.audioFocus = 2;
        if (this.resumeAudioOnFocusGain == false) goto L25;
        this.resumeAudioOnFocusGain = false;
        this.videoPlayer.play();
        return;
    L25:
        return;
    L16:
        if (r4 != (-3)) goto L20;
        this.audioFocus = 1;
        return;
    L20:
        if (r4 != (-2)) goto L26;
        this.audioFocus = 0;
        if (this.videoPlayer.isPlaying() == false) goto L27;
        this.resumeAudioOnFocusGain = true;
        this.videoPlayer.pause();
        updatePlayButton();
        return;
    L27:
        return;
    }

    private void onInitFailed() {
        if (this.controlsView.getParent() == this) goto L5;
        this.controlsView.setVisibility(8);
    L5:
        this.delegate.onInitFailed();
    }

    private void preparePlayer() {
        String r0 = this.playVideoUrl;
        if (r0 == null) goto L19;
        if (r0 != null) goto L7;
    L9:
        this.videoPlayer.preparePlayer(Uri.parse(r0), this.playVideoType);
    L10:
        this.videoPlayer.setPlayWhenReady(this.isAutoplay);
        this.isLoading = false;
        long r1 = this.videoPlayer.getDuration();
        ControlsView r2 = this.controlsView;
        if (r1 == (-9223372036854775807L)) goto L13;
        r2.setDuration((int) (this.videoPlayer.getDuration() / 1000));
    L14:
        updateFullscreenButton();
        updateShareButton();
        updateInlineButton();
        this.controlsView.invalidate();
        if (this.seekToTime == (-1)) goto L18;
        this.videoPlayer.seekTo(r0 * 1000);
        return;
    L18:
        return;
    L13:
        r2.setDuration(0);
        goto L14
    L7:
        if (this.playAudioUrl == null) goto L9;
        this.videoPlayer.preparePlayerLoop(Uri.parse(r0), this.playVideoType, Uri.parse(this.playAudioUrl), this.playAudioType);
        goto L10
    }

    private void showProgress(boolean r5, boolean r6) {
        float r0 = 0.0f;
        if (r6 == false) goto L12;
        AnimatorSet r62 = this.progressAnimation;
        if (r62 == null) goto L7;
        r62.cancel();
    L7:
        AnimatorSet r63 = new AnimatorSet();
        this.progressAnimation = r63;
        RadialProgressView r2 = this.progressView;
        if (r5 == false) goto L10;
        r0 = 1.0f;
    L10:
        r63.playTogether(new Animator[]{ObjectAnimator.ofFloat(r2, "alpha", new float[]{r0})});
        this.progressAnimation.setDuration(150);
        this.progressAnimation.addListener(new AnonymousClass6(this));
        this.progressAnimation.start();
        return;
    L12:
        RadialProgressView r4 = this.progressView;
        if (r5 == false) goto L15;
        r0 = 1.0f;
    L15:
        r4.setAlpha(r0);
    }

    private void updateFullscreenButton() {
        if (this.videoPlayer.isPlayerPrepared() == true) goto L5;
    L13:
        this.fullscreenButton.setVisibility(8);
        return;
    L5:
        if (this.isInline == true) goto L13;
        this.fullscreenButton.setVisibility(0);
        boolean r0 = this.inFullscreen;
        ImageView r1 = this.fullscreenButton;
        if (r0 == true) goto L11;
        r1.setImageResource(R.drawable.ic_gofullscreen);
        this.fullscreenButton.setLayoutParams(LayoutHelper.createFrame(56, 56.0f, 85, 0.0f, 0.0f, 0.0f, 5.0f));
        return;
    L11:
        r1.setImageResource(R.drawable.ic_outfullscreen);
        this.fullscreenButton.setLayoutParams(LayoutHelper.createFrame(56, 56.0f, 85, 0.0f, 0.0f, 0.0f, 1.0f));
    }

    private void updateFullscreenState(boolean r10) {
        if (this.textureView != null) goto L5;
        return;
    L5:
        updateFullscreenButton();
        ViewGroup r0 = this.textureViewContainer;
        if (r0 != null) goto L34;
        this.changingTextureView = true;
        if (this.inFullscreen == true) goto L12;
        if (r0 == null) goto L11;
        r0.addView(this.textureView);
        goto L12
    L11:
        this.aspectRatioFrameLayout.addView(this.textureView);
    L12:
        boolean r02 = this.inFullscreen;
        ControlsView r2 = this.controlsView;
        if (r02 == false) goto L17;
        ViewGroup r03 = (ViewGroup) r2.getParent();
        if (r03 == null) goto L25;
        r03.removeView(this.controlsView);
    L25:
        TextureView r102 = this.delegate.onSwitchToFullscreen(this.controlsView, this.inFullscreen, this.aspectRatioFrameLayout.getAspectRatio(), this.aspectRatioFrameLayout.getVideoRotation(), r10);
        this.changedTextureView = r102;
        r102.setVisibility(4);
        if (this.inFullscreen == true) goto L28;
    L32:
        ControlsView.m13647$$Nest$mcheckNeedHide(this.controlsView);
        return;
    L28:
        if (this.changedTextureView == null) goto L32;
        ViewGroup r103 = (ViewGroup) this.textureView.getParent();
        if (r103 == null) goto L32;
        r103.removeView(this.textureView);
        goto L32
    L17:
        ViewGroup r04 = (ViewGroup) r2.getParent();
        if (r04 == this) goto L25;
        if (r04 == null) goto L21;
        r04.removeView(this.controlsView);
    L21:
        ViewGroup r05 = this.textureViewContainer;
        ControlsView r22 = this.controlsView;
        if (r05 == null) goto L24;
        r05.addView(r22);
        goto L25
    L24:
        addView(r22, 1);
        goto L25
    L34:
        boolean r104 = this.inFullscreen;
        AspectRatioFrameLayout r06 = this.aspectRatioFrameLayout;
        if (r104 == false) goto L39;
        ViewGroup r105 = (ViewGroup) r06.getParent();
        if (r105 == null) goto L44;
        r105.removeView(this.aspectRatioFrameLayout);
    L44:
        this.delegate.onSwitchToFullscreen(this.controlsView, this.inFullscreen, this.aspectRatioFrameLayout.getAspectRatio(), this.aspectRatioFrameLayout.getVideoRotation(), r10);
        return;
    L39:
        ViewGroup r106 = (ViewGroup) r06.getParent();
        if (r106 == this) goto L44;
        if (r106 == null) goto L43;
        r106.removeView(this.aspectRatioFrameLayout);
    L43:
        addView(this.aspectRatioFrameLayout, 0);
        goto L44
    }

    private void updateInlineButton() {
        ImageView r0 = this.inlineButton;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.isInline == false) goto L8;
        int r1 = R.drawable.ic_goinline;
    L9:
        r0.setImageResource(r1);
        ImageView r02 = this.inlineButton;
        if (this.videoPlayer.isPlayerPrepared() == false) goto L12;
        int r12 = 0;
    L13:
        r02.setVisibility(r12);
        boolean r03 = this.isInline;
        ImageView r3 = this.inlineButton;
        if (r03 == false) goto L17;
        r3.setLayoutParams(LayoutHelper.createFrame(40, 40, 53));
        return;
    L17:
        r3.setLayoutParams(LayoutHelper.createFrame(56, 50, 53));
        return;
    L12:
        r12 = 8;
        goto L13
    L8:
        r1 = R.drawable.ic_outinline;
        goto L9
    }

    private void updatePlayButton() {
        ControlsView.m13647$$Nest$mcheckNeedHide(this.controlsView);
        AndroidUtilities.cancelRunOnUIThread(this.progressRunnable);
        if (this.videoPlayer.isPlaying() == true) goto L18;
        boolean r0 = this.isCompleted;
        ImageView r1 = this.playButton;
        if (r0 == false) goto L13;
        if (this.isInline == false) goto L9;
        int r3 = R.drawable.ic_againinline;
    L10:
        r1.setImageResource(r3);
        return;
    L9:
        r3 = R.drawable.ic_again;
        goto L10
    L13:
        if (this.isInline == false) goto L15;
        int r32 = R.drawable.ic_playinline;
    L16:
        r1.setImageResource(r32);
        return;
    L15:
        r32 = R.drawable.ic_play;
        goto L16
    L18:
        ImageView r02 = this.playButton;
        if (this.isInline == false) goto L21;
        int r12 = R.drawable.ic_pauseinline;
    L22:
        r02.setImageResource(r12);
        AndroidUtilities.runOnUIThread(this.progressRunnable, 500);
        checkAudioFocus();
        return;
    L21:
        r12 = R.drawable.ic_pause;
        goto L22
    }

    private void updateShareButton() {
        ImageView r0 = this.shareButton;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.isInline == false) goto L8;
    L11:
        int r2 = 8;
    L12:
        r0.setVisibility(r2);
        return;
    L8:
        if (this.videoPlayer.isPlayerPrepared() == false) goto L11;
        r2 = 0;
        goto L12
    }

    public boolean canHandleUrl(String r4) {
        if (r4 != null) goto L4;
        return false;
    L4:
        if (r4.endsWith(".mp4") == false) goto L6;
        return true;
    L6:
        String r3 = null;
        Matcher r1 = youtubeIdRegex.matcher(r4);     // Catch: Exception -> L11
        if (r1.find() == false) goto L13;
        String r12 = r1.group(1);     // Catch: Exception -> L11
    L14:
        if (r12 == null) goto L70;
        return true;
    L70:
        Matcher r13 = vimeoIdRegex.matcher(r4);     // Catch: Exception -> L21
        if (r13.find() == false) goto L23;
        String r14 = r13.group(3);     // Catch: Exception -> L21
    L24:
        if (r14 == null) goto L76;
        return true;
    L76:
        Matcher r15 = aparatIdRegex.matcher(r4);     // Catch: Exception -> L31
        if (r15.find() == false) goto L33;
        String r16 = r15.group(1);     // Catch: Exception -> L31
    L34:
        if (r16 == null) goto L68;
        return true;
    L68:
        Matcher r17 = twitchClipIdRegex.matcher(r4);     // Catch: Exception -> L41
        if (r17.find() == false) goto L43;
        String r18 = r17.group(1);     // Catch: Exception -> L41
    L44:
        if (r18 == null) goto L72;
        return true;
    L72:
        Matcher r19 = twitchStreamIdRegex.matcher(r4);     // Catch: Exception -> L51
        if (r19.find() == false) goto L53;
        String r110 = r19.group(1);     // Catch: Exception -> L51
    L54:
        if (r110 == null) goto L74;
        return true;
    L74:
        Matcher r42 = coubIdRegex.matcher(r4);     // Catch: Exception -> L62
        if (r42.find() == false) goto L60;
        r3 = r42.group(1);     // Catch: Exception -> L62
    L60:
        if (r3 == null) goto L79;
        return true;
    L79:
        return false;
    L62:
        e = move-exception;
        FileLog.e(e);
        return false;
    L53:
        r110 = null;
    L51:
        e = move-exception;
        FileLog.e(e);
        goto L74
    L43:
        r18 = null;
    L41:
        e = move-exception;
        FileLog.e(e);
        goto L72
    L33:
        r16 = null;
    L31:
        e = move-exception;
        FileLog.e(e);
        goto L68
    L23:
        r14 = null;
    L21:
        e = move-exception;
        FileLog.e(e);
        goto L76
    L13:
        r12 = null;
    L11:
        e = move-exception;
        FileLog.e(e);
        goto L70
    }

    public void destroy() {
        this.videoPlayer.releasePlayer(false);
        AsyncTask r0 = this.currentTask;
        if (r0 == null) goto L5;
        r0.cancel(true);
        this.currentTask = null;
    L5:
        this.webView.stopLoading();
    }

    public String downloadUrlContent(AsyncTask r3, String r4) {
        return downloadUrlContent(r3, r4, null, true);
    }

    public String downloadUrlContent(AsyncTask r19, String r20, HashMap<String, String> r21, boolean r22) {
        boolean r8 = true;
        URL r11 = new URL(r20);     // Catch: Throwable -> L40
        URLConnection r12 = r11.openConnection();     // Catch: Throwable -> L40
        r12.addRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:10.0) Gecko/20150101 Firefox/47.0 (Chrome)");     // Catch: Throwable -> L8
        if (r22 == false) goto L10;
        r12.addRequestProperty("Accept-Encoding", "gzip, deflate");     // Catch: Throwable -> L8
    L10:
        r12.addRequestProperty("Accept-Language", "en-us,en;q=0.5");     // Catch: Throwable -> L8
        r12.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");     // Catch: Throwable -> L8
        r12.addRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");     // Catch: Throwable -> L8
        if (r21 == null) goto L16;
        Iterator<Map.Entry<String, String>> r15 = r21.entrySet().iterator();     // Catch: Throwable -> L8
    L14:
        if (r15.hasNext() == false) goto L16;
        Map.Entry<String, String> r16 = r15.next();     // Catch: Throwable -> L8
        r12.addRequestProperty(r16.getKey(), r16.getValue());     // Catch: Throwable -> L8
    L16:
        r12.setConnectTimeout(5000);     // Catch: Throwable -> L8
        r12.setReadTimeout(5000);     // Catch: Throwable -> L8
        if ((r12 instanceof HttpURLConnection) == false) goto L33;
        HttpURLConnection r9 = (HttpURLConnection) r12;     // Catch: Throwable -> L8
        r9.setInstanceFollowRedirects(true);     // Catch: Throwable -> L8
        int r10 = r9.getResponseCode();     // Catch: Throwable -> L8
        if (r10 != 302) goto L21;
    L24:
        String r102 = r9.getHeaderField("Location");     // Catch: Throwable -> L8
        String r92 = r9.getHeaderField("Set-Cookie");     // Catch: Throwable -> L8
        r11 = new URL(r102);     // Catch: Throwable -> L8
        r12 = r11.openConnection();     // Catch: Throwable -> L8
        r12.setRequestProperty("Cookie", r92);     // Catch: Throwable -> L8
        r12.addRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:10.0) Gecko/20150101 Firefox/47.0 (Chrome)");     // Catch: Throwable -> L8
        if (r22 == false) goto L27;
        r12.addRequestProperty("Accept-Encoding", "gzip, deflate");     // Catch: Throwable -> L8
    L27:
        r12.addRequestProperty("Accept-Language", "en-us,en;q=0.5");     // Catch: Throwable -> L8
        r12.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");     // Catch: Throwable -> L8
        r12.addRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");     // Catch: Throwable -> L8
        if (r21 == null) goto L33;
        Iterator<Map.Entry<String, String>> r0 = r21.entrySet().iterator();     // Catch: Throwable -> L8
    L31:
        if (r0.hasNext() == false) goto L33;
        Map.Entry<String, String> r1 = r0.next();     // Catch: Throwable -> L8
        r12.addRequestProperty(r1.getKey(), r1.getValue());     // Catch: Throwable -> L8
        goto L31
    L21:
        if (r10 == 301) goto L24;
        if (r10 == 303) goto L24;
    L33:
        r12.connect();     // Catch: Throwable -> L8
        if (r22 == true) goto L129;
        InputStream r02 = r12.getInputStream();     // Catch: Throwable -> L8
    L39:
        InputStream r13 = r02;
        boolean r03 = true;
    L62:
        if (r03 == true) goto L125;
        boolean r103 = false;
        StringBuilder r2 = null;
    L109:
        if (r103 == true) goto L136;
        return null;
    L136:
        return r2.toString();
    L125:
    L67:
        e = move-exception;
        FileLog.e(e);
    L69:
        if (r13 != null) goto L123;
        r103 = false;
        r2 = null;
    L103:
        if (r13 == null) goto L109;
        r13.close();     // Catch: Throwable -> L106
    L106:
        th = move-exception;
        FileLog.e(th);
        goto L109
    L123:
        byte[] r04 = new byte[32768];     // Catch: Throwable -> L99
        r2 = null;
    L119:
        if (r19.isCancelled() == true) goto L75;
        int r3 = r13.read(r04);     // Catch: Throwable -> L80 Exception -> L82
        if (r3 <= 0) goto L92;
        if (r2 != null) goto L85;
        r2 = new StringBuilder();     // Catch: Throwable -> L80 Exception -> L82
    L85:
        boolean r6 = false;
        r2.append(new String(r04, 0, r3, "UTF-8"));     // Catch: Throwable -> L88 Exception -> L90
    L90:
        e = e;
    L96:
        FileLog.e(e);     // Catch: Throwable -> L88
    L95:
        r8 = r6;
    L98:
        r103 = r8;
    L88:
        th = th;
    L101:
        FileLog.e(th);
        r103 = r6;
        goto L103
    L92:
        r6 = false;
        if (r3 != (-1)) goto L95;
    L82:
        e = e;
        r6 = false;
        goto L96
    L75:
        r6 = false;
    L80:
        th = th;
        r6 = false;
    L99:
        th = th;
        r6 = false;
        r2 = null;
        goto L101
    L64:
        if ((r12 instanceof HttpURLConnection) == false) goto L69;
        ((HttpURLConnection) r12).getResponseCode();     // Catch: Exception -> L67
        goto L69
    L129:
        r02 = new GZIPInputStream(r12.getInputStream());     // Catch: Throwable -> L8 Exception -> L37
    L37:
        r12 = r11.openConnection();     // Catch: Throwable -> L8
        r12.connect();     // Catch: Throwable -> L8
        r02 = r12.getInputStream();     // Catch: Throwable -> L8
    L8:
        th = th;
    L43:
        if ((th instanceof SocketTimeoutException) == false) goto L48;
        if (ApplicationLoader.isNetworkOnline() == false) goto L60;
    L46:
        boolean r14 = false;
    L61:
        FileLog.e(th);
        r03 = r14;
        r13 = null;
    L60:
        r14 = true;
        goto L61
    L48:
        if ((th instanceof UnknownHostException) == true) goto L46;
        if ((th instanceof SocketException) == false) goto L58;
        if (th.getMessage() == null) goto L60;
        if (th.getMessage().contains("ECONNRESET") == false) goto L60;
    L58:
        if ((th instanceof FileNotFoundException) == false) goto L60;
    L40:
        th = th;
        r12 = null;
        goto L43
    }

    public void enterFullscreen() {
        if (this.inFullscreen == false) goto L5;
        return;
    L5:
        this.inFullscreen = true;
        updateInlineButton();
        updateFullscreenState(false);
    }

    public void exitFullscreen() {
        if (this.inFullscreen == true) goto L5;
        return;
    L5:
        this.inFullscreen = false;
        updateInlineButton();
        updateFullscreenState(false);
    }

    public View getAspectRatioView() {
        return this.aspectRatioFrameLayout;
    }

    public View getControlsView() {
        return this.controlsView;
    }

    public String getCoubId(String r2) {
        if (TextUtils.isEmpty(r2) == false) goto L16;
        return null;
    L16:
        Matcher r1 = coubIdRegex.matcher(r2);     // Catch: Exception -> L9
        if (r1.find() == false) goto L11;
        String r12 = r1.group(1);     // Catch: Exception -> L9
    L12:
        if (r12 == null) goto L15;
        return r12;
    L15:
        return null;
    L11:
        r12 = null;
    L9:
        e = move-exception;
        FileLog.e(e);
        goto L15
    }

    public ImageView getTextureImageView() {
        return this.textureImageView;
    }

    public TextureView getTextureView() {
        return this.textureView;
    }

    public String getYoutubeId() {
        return this.currentYoutubeId;
    }

    public boolean isInFullscreen() {
        return this.inFullscreen;
    }

    public boolean isInitied() {
        return this.initied;
    }

    public boolean isInline() {
        if (this.isInline == false) goto L5;
        return true;
    L5:
        if (this.switchingInlineMode == true) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean loadVideo(String r26, TLRPC.Photo r27, Object r28, String r29, boolean r30) {
        String r5 = getCoubId(r26);
        if (r5 != null) goto L5;
        r5 = getCoubId(r29);
    L5:
        this.seekToTime = -1;
        if (r5 != null) goto L105;
        if (r26 == null) goto L105;
        if (r26.endsWith(".mp4") == false) goto L161;
        String r0 = r26;
        String r4 = null;
    L11:
        String r9 = r4;
        String r10 = r9;
        String r11 = r10;
        String r12 = r11;
    L106:
        this.initied = false;
        this.isCompleted = false;
        this.isAutoplay = r30;
        this.playVideoUrl = null;
        this.playAudioUrl = null;
        destroy();
        this.firstFrameRendered = false;
        this.currentAlpha = 1.0f;
        AsyncTask r13 = this.currentTask;
        if (r13 == null) goto L109;
        r13.cancel(true);
        this.currentTask = null;
    L109:
        updateFullscreenButton();
        updateShareButton();
        updateInlineButton();
        updatePlayButton();
        if (r27 == null) goto L114;
        TLRPC.PhotoSize r132 = FileLoader.getClosestPhotoSizeWithSize(r27.sizes, 80, true);
        if (r132 == null) goto L115;
        ControlsView.m13645$$Nest$fgetimageReceiver(this.controlsView).setImage(null, null, ImageLocation.getForPhoto(r132, r27), "80_80_b", 0, null, r28, 1);
        this.drawImage = true;
    L115:
        AnimatorSet r3 = this.progressAnimation;
        if (r3 == null) goto L118;
        r3.cancel();
        this.progressAnimation = null;
    L118:
        this.isLoading = true;
        this.controlsView.setProgress(0);
        if (r4 == null) goto L121;
        this.currentYoutubeId = r4;
        r4 = null;
    L121:
        if (r0 == null) goto L126;
        this.initied = true;
        this.playVideoUrl = r0;
        this.playVideoType = "other";
        if (this.isAutoplay == false) goto L125;
        preparePlayer();
    L125:
        showProgress(false, false);
        this.controlsView.show(true, true);
    L139:
        if (r4 != null) goto L149;
        if (r9 != null) goto L149;
        if (r5 != null) goto L149;
        if (r10 != null) goto L149;
        if (r0 != null) goto L149;
        if (r11 != null) goto L149;
        if (r12 != null) goto L149;
        this.controlsView.setVisibility(8);
        return false;
    L149:
        this.controlsView.setVisibility(0);
        return true;
    L126:
        if (r4 == null) goto L128;
        YoutubeVideoTask r2 = new YoutubeVideoTask(this, r4);
        r2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[]{null, null, null});
        this.currentTask = r2;
    L138:
        this.controlsView.show(false, false);
        showProgress(true, false);
        goto L139
    L128:
        if (r9 == null) goto L130;
        VimeoVideoTask r22 = new VimeoVideoTask(this, r9);
        r22.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[]{null, null, null});
        this.currentTask = r22;
        goto L138
    L130:
        if (r5 == null) goto L132;
        CoubVideoTask r23 = new CoubVideoTask(this, r5);
        r23.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[]{null, null, null});
        this.currentTask = r23;
        this.isStream = true;
        goto L138
    L132:
        if (r10 == null) goto L134;
        AparatVideoTask r24 = new AparatVideoTask(this, r10);
        r24.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[]{null, null, null});
        this.currentTask = r24;
        goto L138
    L134:
        if (r11 == null) goto L136;
        TwitchClipVideoTask r32 = new TwitchClipVideoTask(this, r26, r11);
        r32.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[]{null, null, null});
        this.currentTask = r32;
        goto L138
    L136:
        if (r12 == null) goto L138;
        TwitchStreamVideoTask r33 = new TwitchStreamVideoTask(this, r26, r12);
        r33.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[]{null, null, null});
        this.currentTask = r33;
        this.isStream = true;
        goto L138
    L114:
        this.drawImage = false;
        goto L115
    L161:
        if (r29 != null) goto L163;
    L27:
        Matcher r02 = youtubeIdRegex.matcher(r26);     // Catch: Exception -> L25
        if (r02.find() == false) goto L31;
        String r03 = r02.group(1);     // Catch: Exception -> L25
    L32:
        if (r03 != null) goto L35;
        r03 = null;
    L35:
        r4 = r03;
    L37:
        if (r4 == null) goto L159;
    L50:
        r9 = null;
    L51:
        if (r9 == null) goto L157;
    L64:
        r10 = null;
    L65:
        if (r10 == null) goto L155;
    L78:
        r11 = null;
    L79:
        if (r11 == null) goto L153;
    L92:
        r12 = null;
    L93:
        if (r12 == null) goto L151;
    L103:
        r0 = null;
        goto L106
    L151:
        Matcher r04 = coubIdRegex.matcher(r26);     // Catch: Exception -> L98
        if (r04.find() == false) goto L100;
        String r05 = r04.group(1);     // Catch: Exception -> L98
    L101:
        if (r05 == null) goto L103;
        r5 = r05;
        goto L103
    L100:
        r05 = null;
    L98:
        e = move-exception;
        FileLog.e(e);
        goto L103
    L153:
        Matcher r06 = twitchStreamIdRegex.matcher(r26);     // Catch: Exception -> L84
        if (r06.find() == false) goto L86;
        String r07 = r06.group(1);     // Catch: Exception -> L84
    L87:
        if (r07 != null) goto L90;
        r07 = null;
    L90:
        r12 = r07;
        goto L93
    L86:
        r07 = null;
    L84:
        e = move-exception;
        FileLog.e(e);
        goto L92
    L155:
        Matcher r08 = twitchClipIdRegex.matcher(r26);     // Catch: Exception -> L70
        if (r08.find() == false) goto L72;
        String r09 = r08.group(1);     // Catch: Exception -> L70
    L73:
        if (r09 != null) goto L76;
        r09 = null;
    L76:
        r11 = r09;
        goto L79
    L72:
        r09 = null;
    L70:
        e = move-exception;
        FileLog.e(e);
        goto L78
    L157:
        Matcher r010 = aparatIdRegex.matcher(r26);     // Catch: Exception -> L56
        if (r010.find() == false) goto L58;
        String r011 = r010.group(1);     // Catch: Exception -> L56
    L59:
        if (r011 != null) goto L62;
        r011 = null;
    L62:
        r10 = r011;
        goto L65
    L58:
        r011 = null;
    L56:
        e = move-exception;
        FileLog.e(e);
        goto L64
    L159:
        Matcher r012 = vimeoIdRegex.matcher(r26);     // Catch: Exception -> L42
        if (r012.find() == false) goto L44;
        String r013 = r012.group(3);     // Catch: Exception -> L42
    L45:
        if (r013 != null) goto L48;
        r013 = null;
    L48:
        r9 = r013;
        goto L51
    L44:
        r013 = null;
    L42:
        e = move-exception;
        FileLog.e(e);
        goto L50
    L31:
        r03 = null;
        goto L32
    L163:
        Uri r014 = Uri.parse(r29);     // Catch: Exception -> L16
        String r92 = r014.getQueryParameter("t");     // Catch: Exception -> L16
        if (r92 != null) goto L18;
        r92 = r014.getQueryParameter("time_continue");     // Catch: Exception -> L16
    L18:
        if (r92 == null) goto L27;
        if (r92.contains("m") == false) goto L22;
        String[] r015 = r92.split("m");     // Catch: Exception -> L16
        this.seekToTime = (Utilities.parseInt(r015[0]) * 60) + Utilities.parseInt(r015[1]);     // Catch: Exception -> L16
        goto L27
    L22:
        this.seekToTime = Utilities.parseInt(r92);     // Catch: Exception -> L16
    L16:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L25
    L25:
        e = move-exception;
        FileLog.e(e);
        r4 = null;
    L105:
        r0 = null;
        r4 = null;
        goto L11
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(int r2) {
        AndroidUtilities.runOnUIThread(new WebPlayerView$$ExternalSyntheticLambda5(this, r2));
    }

    @Override // android.view.View
    public void onDraw(Canvas r8) {
        r8.drawRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight() - AndroidUtilities.dp(10.0f), this.backgroundPaint);
    }

    @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
    public void onError(VideoPlayer r1, Exception r2) {
        FileLog.e(r2);
        onInitFailed();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r4, int r5, int r6, int r7, int r8) {
        int r72 = r7 - r5;
        int r42 = (r72 - this.aspectRatioFrameLayout.getMeasuredWidth()) / 2;
        int r82 = r8 - r6;
        int r62 = ((r82 - AndroidUtilities.dp(10.0f)) - this.aspectRatioFrameLayout.getMeasuredHeight()) / 2;
        AspectRatioFrameLayout r0 = this.aspectRatioFrameLayout;
        r0.layout(r42, r62, r0.getMeasuredWidth() + r42, this.aspectRatioFrameLayout.getMeasuredHeight() + r62);
        if (this.controlsView.getParent() != this) goto L5;
        ControlsView r43 = this.controlsView;
        r43.layout(0, 0, r43.getMeasuredWidth(), this.controlsView.getMeasuredHeight());
    L5:
        int r73 = (r72 - this.progressView.getMeasuredWidth()) / 2;
        int r83 = (r82 - this.progressView.getMeasuredHeight()) / 2;
        RadialProgressView r44 = this.progressView;
        r44.layout(r73, r83, r44.getMeasuredWidth() + r73, this.progressView.getMeasuredHeight() + r83);
        ControlsView.m13645$$Nest$fgetimageReceiver(this.controlsView).setImageCoords(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight() - AndroidUtilities.dp(10.0f));
    }

    @Override // android.view.View
    public void onMeasure(int r5, int r6) {
        int r52 = View.MeasureSpec.getSize(r5);
        int r62 = View.MeasureSpec.getSize(r6);
        this.aspectRatioFrameLayout.measure(View.MeasureSpec.makeMeasureSpec(r52, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r62 - AndroidUtilities.dp(10.0f), TLObject.FLAG_30));
        if (this.controlsView.getParent() != this) goto L5;
        this.controlsView.measure(View.MeasureSpec.makeMeasureSpec(r52, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r62, TLObject.FLAG_30));
    L5:
        this.progressView.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(44.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(44.0f), TLObject.FLAG_30));
        setMeasuredDimension(r52, r62);
    }

    @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
    public void onRenderedFirstFrame() {
        this.firstFrameRendered = true;
        this.lastUpdateTime = System.currentTimeMillis();
        this.controlsView.invalidate();
    }

    @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
    public void onStateChanged(boolean r7, int r8) {
        if (r8 == 2) goto L9;
        long r1 = this.videoPlayer.getDuration();
        ControlsView r12 = this.controlsView;
        if (r1 == (-9223372036854775807L)) goto L7;
        r12.setDuration((int) (this.videoPlayer.getDuration() / 1000));
        goto L9
    L7:
        r12.setDuration(0);
    L9:
        if (r8 == 4) goto L14;
        if (r8 == 1) goto L14;
        if (this.videoPlayer.isPlaying() == false) goto L14;
        this.delegate.onPlayStateChanged(this, true);
    L16:
        if (this.videoPlayer.isPlaying() == false) goto L20;
        if (r8 == 4) goto L20;
        updatePlayButton();
        return;
    L20:
        if (r8 != 4) goto L23;
        this.isCompleted = true;
        this.videoPlayer.pause();
        this.videoPlayer.seekTo(0);
        updatePlayButton();
        this.controlsView.show(true, true);
        return;
    L23:
        return;
    L14:
        this.delegate.onPlayStateChanged(this, false);
        goto L16
    }

    @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
    public boolean onSurfaceDestroyed(SurfaceTexture r4) {
        if (this.changingTextureView == false) goto L13;
        this.changingTextureView = false;
        if (this.inFullscreen == true) goto L9;
        if (this.isInline == false) goto L13;
    L9:
        if (this.isInline == false) goto L11;
        this.waitingForFirstTextureUpload = 1;
    L11:
        this.changedTextureView.setSurfaceTexture(r4);
        this.changedTextureView.setSurfaceTextureListener(this.surfaceTextureListener);
        this.changedTextureView.setVisibility(0);
        return true;
    L13:
        return false;
    }

    @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
    public void onSurfaceTextureUpdated(SurfaceTexture r8) {
        if (this.waitingForFirstTextureUpload != 2) goto L11;
        ImageView r82 = this.textureImageView;
        if (r82 == null) goto L9;
        r82.setVisibility(4);
        this.textureImageView.setImageDrawable(null);
        Bitmap r83 = this.currentBitmap;
        if (r83 == null) goto L9;
        r83.recycle();
        this.currentBitmap = null;
    L9:
        this.switchingInlineMode = false;
        this.delegate.onSwitchInlineMode(this.controlsView, false, this.videoWidth, this.videoHeight, this.aspectRatioFrameLayout.getVideoRotation(), this.allowInlineAnimation);
        this.waitingForFirstTextureUpload = 0;
        return;
    }

    @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
    public void onVideoSizeChanged(int r4, int r5, int r6, float r7) {
        AspectRatioFrameLayout r0 = this.aspectRatioFrameLayout;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r6 != 90) goto L7;
    L8:
        r5 = r4;
        r4 = r5;
    L9:
        float r42 = r4 * r7;
        this.videoWidth = (int) r42;
        this.videoHeight = r5;
        if (r5 != 0) goto L12;
        float r43 = 1.0f;
    L13:
        r0.setAspectRatio(r43, r6);
        if (this.inFullscreen == false) goto L18;
        this.delegate.onVideoSizeChanged(r43, r6);
        return;
    L18:
        return;
    L12:
        r43 = r42 / r5;
        goto L13
    L7:
        if (r6 != 270) goto L9;
        goto L8
    }

    public void pause() {
        this.videoPlayer.pause();
        updatePlayButton();
        this.controlsView.show(true, true);
    }

    public void updateTextureImageView() {
        if (this.textureImageView != null) goto L18;
        return;
    L18:
        Bitmap r1 = Bitmaps.createBitmap(this.textureView.getWidth(), this.textureView.getHeight(), Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L8
        this.currentBitmap = r1;     // Catch: Throwable -> L8
        this.changedTextureView.getBitmap(r1);     // Catch: Throwable -> L8
    L13:
        Bitmap r12 = this.currentBitmap;
        ImageView r2 = this.textureImageView;
        if (r12 == null) goto L16;
        r2.setVisibility(0);
        this.textureImageView.setImageBitmap(this.currentBitmap);
        return;
    L16:
        r2.setImageDrawable(null);
        return;
    L8:
        th = move-exception;
        Bitmap r22 = this.currentBitmap;
        if (r22 == null) goto L12;
        r22.recycle();
        this.currentBitmap = null;
    L12:
        FileLog.e(th);
        goto L13
    }

    public void willHandle() {
        this.controlsView.setVisibility(4);
        this.controlsView.show(false, false);
        showProgress(true, false);
    }
}
