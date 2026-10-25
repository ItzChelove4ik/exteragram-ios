package org.telegram.messenger;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AppOpsManager;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.app.PictureInPictureParams;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentUris;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.PowerManager;
import android.os.Process;
import android.os.SystemClock;
import android.os.Vibrator;
import android.provider.CalendarContract;
import android.provider.CallLog;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.system.ErrnoException;
import android.system.OsConstants;
import android.telephony.TelephonyManager;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.util.StateSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.PixelCopy;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.view.inspector.WindowInspector;
import android.webkit.MimeTypeMap;
import android.widget.EdgeEffect;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.Insets;
import androidx.core.math.MathUtils;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.NestedScrollView;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.util.IntList$$ExternalSyntheticBUOutline0;
import com.exteragram.messenger.DividerStyle;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.proxy.ProxyController;
import com.exteragram.messenger.utils.ui.FontUtils;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaTrack;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;
import kotlin.text.Typography;
import kotlin.time.DurationKt;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;
import me.vkryl.core.BitwiseUtils;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.mvel2.asm.Opcodes;
import org.mvel2.asm.signature.SignatureVisitor;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.messenger.utils.CustomHtml;
import org.telegram.messenger.utils.DebugRecordingCanvas;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.ChatBackgroundDrawable;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.BackgroundGradientDrawable;
import org.telegram.ui.Components.ButtonSpan;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.ForegroundColorSpanThemable;
import org.telegram.ui.Components.ForegroundDetector;
import org.telegram.ui.Components.HideViewAfterAnimation;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MotionBackgroundDrawable;
import org.telegram.ui.Components.PipRoundVideoView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ShareAlert;
import org.telegram.ui.Components.TableView;
import org.telegram.ui.Components.TextHelper;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.URLSpanReplacement;
import org.telegram.ui.Components.UndoView;
import org.telegram.ui.Components.spoilers.SpoilersTextView;
import org.telegram.ui.DebugRecordingCanvasReplayFragment;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stories.PeerStoriesView;
import org.telegram.ui.Stories.StoryMediaAreasView;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.ThemePreviewActivity;
import org.telegram.ui.WallpapersListActivity;
import org.telegram.utils.proxy.ProxySettings;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes.dex */
public class AndroidUtilities {
    public static Pattern BAD_CHARS_MESSAGE_LONG_PATTERN = null;
    public static Pattern BAD_CHARS_MESSAGE_PATTERN = null;
    public static Pattern BAD_CHARS_PATTERN = null;
    public static final int FLAG_TAG_ALL = 11;
    public static final int FLAG_TAG_BOLD = 2;
    public static final int FLAG_TAG_BR = 1;
    public static final int FLAG_TAG_COLOR = 4;
    public static final int FLAG_TAG_URL = 8;
    private static final Pattern IPV4_URL = null;
    private static final ExecutorService LINK_EXECUTOR = null;
    public static Pattern LONG_BAD_CHARS_PATTERN = null;
    public static Pattern REMOVE_MULTIPLE_DIACRITICS = null;
    public static Pattern REMOVE_RTL = null;
    public static final int REPLACING_TAG_TYPE_BOLD = 1;
    public static final int REPLACING_TAG_TYPE_LINK = 0;
    public static final int REPLACING_TAG_TYPE_LINKBOLD = 2;
    public static final int REPLACING_TAG_TYPE_LINK_NBSP = 3;
    public static final int REPLACING_TAG_TYPE_UNDERLINE = 4;
    public static final String STICKERS_PLACEHOLDER_PACK_NAME = "tg_placeholders_android";
    public static final String STICKERS_PLACEHOLDER_PACK_NAME_2 = "tg_superplaceholders_android_2";
    public static final String TYPEFACE_MERRIWEATHER_BOLD = "fonts/mw_bold.ttf";
    public static final String TYPEFACE_NUNITO_EXTRABOLD = "fonts/nunito_extrabold.ttf";
    public static final String TYPEFACE_ROBOTO_CONDENSED_BOLD = "fonts/rcondensedbold.ttf";
    public static final String TYPEFACE_ROBOTO_EXTRA_BOLD = "fonts/rextrabold.ttf";
    public static final String TYPEFACE_ROBOTO_ITALIC = "fonts/ritalic.ttf";
    public static final String TYPEFACE_ROBOTO_MEDIUM = "fonts/rmedium.ttf";
    public static final String TYPEFACE_ROBOTO_MEDIUM_ITALIC = "fonts/rmediumitalic.ttf";
    public static final String TYPEFACE_ROBOTO_MONO = "fonts/rmono.ttf";
    public static final String TYPEFACE_ROBOTO_REGULAR = "fonts/rregular.ttf";
    public static Pattern WEB_URL;
    public static AccelerateInterpolator accelerateInterpolator;
    private static AccessibilityManager accessibilityManager;
    private static int adjustOwnerClassGuid;
    private static int altFocusableClassGuid;
    public static ThreadLocal<byte[]> bufferLocal;
    private static final Object callLock = null;
    private static CallReceiver callReceiver;
    private static final char[] characters = null;
    private static HashSet<Character> charactersMap;
    public static DecelerateInterpolator decelerateInterpolator;
    public static float density;
    public static DisplayMetrics displayMetrics;
    public static Point displaySize;
    private static final int[] documentIcons = null;
    private static final int[] documentMediaIcons = null;
    public static boolean firstConfigurationWas;
    private static SimpleDateFormat generatingVideoPathFormat;
    private static final boolean hasCallPermissions = false;
    public static Integer highQualityPhotoSize;
    public static boolean incorrectDisplaySizeFix;
    private static Boolean isHonor;
    public static boolean isInMultiwindow;
    private static Boolean isSmallScreen;
    private static Boolean isTablet;
    public static int leftBaseline;
    private static Pattern linksPattern;
    private static Field mAttachInfoField;
    private static Field mStableInsetsField;
    public static boolean makingGlobalBlurBitmap;
    public static Typeface mediumTypeface;
    private static final Paint navbarProtactionPaint = null;
    private static HashMap<Window, ValueAnimator> navigationBarColorAnimators;

    @Deprecated
    public static int navigationBarHeight;
    public static final String[] numbersSignatureArray = null;
    public static OvershootInterpolator overshootInterpolator;
    public static Integer photoSize;
    public static final int[] pointTmp2 = null;
    private static int prevOrientation;
    public static ThreadLocal<byte[]> readBufferLocal;
    public static final RectF rectTmp = null;
    public static final Rect rectTmp2 = null;
    public static int roundMessageInset;
    public static int roundMessageSize;
    public static int roundPlayingMessageSize;
    public static int roundSidePlayingMessageSize;
    public static final Linkify.MatchFilter sUrlMatchFilter = null;
    public static float screenMaxRefreshRate;
    public static float screenRefreshRate;
    public static float screenRefreshTime;
    private static Pattern singleTagPatter;
    private static final Object smsLock = null;

    @Deprecated
    public static int statusBarHeight;
    public static final Paint strokeBottom = null;
    public static final Paint strokeTop = null;
    private static final float[] tempFloats = null;
    private static final float[] tempFloats2 = null;
    public static float touchSlop;
    private static final ConcurrentHashMap<String, Typeface> typefaceCache = null;
    private static Pattern uriParse;
    public static boolean usingHardwareInput;
    private static Vibrator vibrator;
    private static boolean waitingForCall;
    private static boolean waitingForSms;
    private static Boolean wasTablet;

    /* renamed from: org.telegram.messenger.AndroidUtilities$1, reason: invalid class name */
    public class AnonymousClass1 extends ClickableSpan {
        final /* synthetic */ int val$colorKey;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;
        final /* synthetic */ Runnable val$runnable;
        final /* synthetic */ int val$type;

        public AnonymousClass1(int r1, int r2, Theme.ResourcesProvider r3, Runnable r4) {
            this.val$type = r1;
            this.val$colorKey = r2;
            this.val$resourcesProvider = r3;
            this.val$runnable = r4;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r1) {
            Runnable r0 = this.val$runnable;
            if (r0 == null) goto L6;
            r0.run();
            return;
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r3) {
            super.updateDrawState(r3);
            if (this.val$type != 4) goto L5;
            boolean r0 = true;
        L6:
            r3.setUnderlineText(r0);
            int r02 = this.val$colorKey;
            if (r02 < 0) goto L10;
            r3.setColor(Theme.getColor(r02, this.val$resourcesProvider));
        L10:
            if (this.val$type != 2) goto L13;
            r3.setTypeface(AndroidUtilities.bold());
            return;
        L13:
            return;
        L5:
            r0 = false;
            goto L6
        }
    }

    /* renamed from: org.telegram.messenger.AndroidUtilities$10, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass10 implements View.OnLayoutChangeListener {
        final /* synthetic */ Runnable val$runnable;
        final /* synthetic */ View val$view;

        public AnonymousClass10(View r1, Runnable r2) {
            this.val$view = r1;
            this.val$runnable = r2;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
            this.val$view.removeOnLayoutChangeListener(this);
            this.val$runnable.run();
        }
    }

    /* renamed from: org.telegram.messenger.AndroidUtilities$11, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass11 extends ClickableSpan {
        final /* synthetic */ Runnable val$onLinkClick;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;
        final /* synthetic */ String val$url;

        public AnonymousClass11(Runnable r1, String r2, Theme.ResourcesProvider r3) {
            this.val$onLinkClick = r1;
            this.val$url = r2;
            this.val$resourcesProvider = r3;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r1) {
            Runnable r12 = this.val$onLinkClick;
            if (r12 == null) goto L5;
            r12.run();
        L5:
            Browser.openUrl(ApplicationLoader.applicationContext, this.val$url);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r2) {
            r2.setColor(Theme.getColor(Theme.key_chat_messageLinkIn, this.val$resourcesProvider));
            r2.setUnderlineText(false);
        }
    }

    /* renamed from: org.telegram.messenger.AndroidUtilities$12, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass12 extends AnimatorListenerAdapter {
        final /* synthetic */ View val$view;

        public AnonymousClass12(View r1) {
            this.val$view = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.val$view.setTranslationX(0.0f);
        }
    }

    /* renamed from: org.telegram.messenger.AndroidUtilities$13, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass13 extends AnimatorListenerAdapter {
        final /* synthetic */ Window val$window;

        public AnonymousClass13(Window r1) {
            this.val$window = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (AndroidUtilities.m3875$$Nest$sfgetnavigationBarColorAnimators() == null) goto L6;
            AndroidUtilities.m3875$$Nest$sfgetnavigationBarColorAnimators().remove(this.val$window);
            return;
        }
    }

    /* renamed from: org.telegram.messenger.AndroidUtilities$14, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass14 extends ClickableSpan {
        final /* synthetic */ Runnable val$onClick;

        public AnonymousClass14(Runnable r1) {
            this.val$onClick = r1;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r1) {
            Runnable r0 = this.val$onClick;
            if (r0 == null) goto L6;
            r0.run();
            return;
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            r1.setUnderlineText(false);
            r1.setColor(r1.linkColor);
        }
    }

    /* renamed from: org.telegram.messenger.AndroidUtilities$15, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass15 implements Interpolator {
        final /* synthetic */ double val$A;
        final /* synthetic */ double val$B;
        final /* synthetic */ double val$w0;
        final /* synthetic */ double val$wd;
        final /* synthetic */ double val$zeta;

        public AnonymousClass15(double r1, double r3, double r5, double r7, double r9) {
            this.val$zeta = r1;
            this.val$w0 = r3;
            this.val$A = r5;
            this.val$wd = r7;
            this.val$B = r9;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float r11) {
            double r0 = this.val$zeta;
            if (r0 >= 1.0d) goto L7;
            double r8 = r11;
            double r02 = Math.exp(((-r11) * r0) * this.val$w0) * ((this.val$A * Math.cos(this.val$wd * r8)) + (this.val$B * Math.sin(this.val$wd * r8)));
        L6:
            return (float) (1.0d - r02);
        L7:
            r02 = (this.val$A + (this.val$B * r11)) * Math.exp((-r11) * this.val$w0);
            goto L6
        }
    }

    /* renamed from: org.telegram.messenger.AndroidUtilities$16, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass16 implements Interpolator {
        final /* synthetic */ double val$omega;
        final /* synthetic */ double val$zeta;

        public AnonymousClass16(double r1, double r3) {
            this.val$zeta = r1;
            this.val$omega = r3;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float r14) {
            double r0 = this.val$zeta;
            double r5 = this.val$omega;
            if (r0 >= 1.0d) goto L7;
            double r52 = r5 * Math.sqrt(1.0d - (r0 * r0));
            double r7 = r14;
            double r02 = Math.exp(((-this.val$zeta) * this.val$omega) * r7);
            double r72 = r7 * r52;
            double r2 = 1.0d - (r02 * (Math.cos(r72) + (((this.val$zeta * this.val$omega) / r52) * Math.sin(r72))));
        L6:
            return (float) r2;
        L7:
            double r03 = ((-r0) * r5) * r14;
            r2 = 1.0d - ((r03 + 1.0d) * Math.exp(r03));
            goto L6
        }
    }

    /* renamed from: org.telegram.messenger.AndroidUtilities$2, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass2 extends CharacterStyle {
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public AnonymousClass2(Theme.ResourcesProvider r1) {
            this.val$resourcesProvider = r1;
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r3) {
            r3.setTypeface(AndroidUtilities.bold());
            int r0 = r3.getAlpha();
            r3.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText, this.val$resourcesProvider));
            r3.setAlpha(r0);
        }
    }

    /* renamed from: org.telegram.messenger.AndroidUtilities$3, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass3 extends ClickableSpan {
        final /* synthetic */ Runnable val$runnable;
        final /* synthetic */ int val$type;

        public AnonymousClass3(int r1, Runnable r2) {
            this.val$type = r1;
            this.val$runnable = r2;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r1) {
            Runnable r0 = this.val$runnable;
            if (r0 == null) goto L6;
            r0.run();
            return;
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r3) {
            super.updateDrawState(r3);
            if (this.val$type != 4) goto L5;
            boolean r0 = true;
        L6:
            r3.setUnderlineText(r0);
            if (this.val$type != 2) goto L10;
            r3.setTypeface(AndroidUtilities.bold());
            return;
        L10:
            return;
        L5:
            r0 = false;
            goto L6
        }
    }

    /* renamed from: org.telegram.messenger.AndroidUtilities$4, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass4 extends CharacterStyle {
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public AnonymousClass4(Theme.ResourcesProvider r1) {
            this.val$resourcesProvider = r1;
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r3) {
            r3.setTypeface(AndroidUtilities.bold());
            int r0 = r3.getAlpha();
            r3.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText, this.val$resourcesProvider));
            r3.setAlpha(r0);
        }
    }

    /* renamed from: org.telegram.messenger.AndroidUtilities$5, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass5 extends ClickableSpan {
        final /* synthetic */ Runnable val$runnable;

        public AnonymousClass5(Runnable r1) {
            this.val$runnable = r1;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r1) {
            Runnable r0 = this.val$runnable;
            if (r0 == null) goto L6;
            r0.run();
            return;
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            super.updateDrawState(r1);
            r1.setUnderlineText(false);
        }
    }

    /* renamed from: org.telegram.messenger.AndroidUtilities$6, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass6 extends ClickableSpan {
        final /* synthetic */ int val$color;
        final /* synthetic */ Runnable val$onClick;

        public AnonymousClass6(int r1, Runnable r2) {
            this.val$color = r1;
            this.val$onClick = r2;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r1) {
            Runnable r0 = this.val$onClick;
            if (r0 == null) goto L6;
            r0.run();
            return;
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r2) {
            super.updateDrawState(r2);
            r2.setUnderlineText(false);
            r2.setColor(this.val$color);
        }
    }

    /* renamed from: org.telegram.messenger.AndroidUtilities$7, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass7 extends CharacterStyle {
        final /* synthetic */ int val$color;

        public AnonymousClass7(int r1) {
            this.val$color = r1;
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r2) {
            r2.setUnderlineText(false);
            r2.setColor(this.val$color);
        }
    }

    /* renamed from: org.telegram.messenger.AndroidUtilities$8, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass8 extends ClickableSpan {
        final /* synthetic */ int val$color;
        final /* synthetic */ Runnable val$onClick;

        public AnonymousClass8(int r1, Runnable r2) {
            this.val$color = r1;
            this.val$onClick = r2;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r1) {
            Runnable r0 = this.val$onClick;
            if (r0 == null) goto L6;
            r0.run();
            return;
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r2) {
            super.updateDrawState(r2);
            r2.setUnderlineText(false);
            r2.setTypeface(AndroidUtilities.bold());
            r2.setColor(this.val$color);
        }
    }

    /* renamed from: org.telegram.messenger.AndroidUtilities$9, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass9 extends CharacterStyle {
        final /* synthetic */ int val$color;

        public AnonymousClass9(int r1) {
            this.val$color = r1;
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r2) {
            r2.setUnderlineText(false);
            r2.setTypeface(AndroidUtilities.bold());
            r2.setColor(this.val$color);
        }
    }

    public interface IntColorCallback {
        void run(int r1);
    }

    /* loaded from: classes5.dex */
    public static class LinkMovementMethodMy extends LinkMovementMethod {
        public LinkMovementMethodMy() {
        }

        @Override // android.text.method.LinkMovementMethod, android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod, android.text.method.MovementMethod
        public boolean onTouchEvent(TextView r2, Spannable r3, MotionEvent r4) {
            boolean r1 = super.onTouchEvent(r2, r3, r4);     // Catch: Exception -> L10
            if (r4.getAction() != 1) goto L5;
        L8:
            Selection.removeSelection(r3);     // Catch: Exception -> L10
            return r1;
        L5:
            if (r4.getAction() == 3) goto L8;
            return r1;
        L10:
            e = move-exception;
            FileLog.e(e);
            return false;
        }
    }

    public static class LinkSpec {
        int end;
        int start;
        String url;

        private LinkSpec() {
        }

        public /* synthetic */ LinkSpec(AndroidUtilitiesIA r1) {
            this();
        }
    }

    /* loaded from: classes5.dex */
    public static class VcardData {
        String name;
        ArrayList<String> phones;
        StringBuilder vcard;

        private VcardData() {
            this.phones = new ArrayList();
            this.vcard = new StringBuilder();
        }

        public /* synthetic */ VcardData(AndroidUtilitiesIA r1) {
            this();
        }
    }

    /* loaded from: classes5.dex */
    public static class VcardItem {
        public boolean checked;
        public String fullData;
        public int type;
        public ArrayList<String> vcardData;

        public VcardItem() {
            this.vcardData = new ArrayList();
            this.fullData = _UrlKt.FRAGMENT_ENCODE_SET;
            this.checked = true;
        }

        public String getRawType(boolean r6) {
            int r0 = this.fullData.indexOf(58);
            if (r0 >= 0) goto L5;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L5:
            int r3 = 0;
            String r02 = this.fullData.substring(0, r0);
            if (this.type != 20) goto L16;
            String[] r5 = r02.substring(2).split(";");
            if (r6 == false) goto L12;
            return r5[0];
        L12:
            if (r5.length > 1) goto L14;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L14:
            return r5[r5.length - 1];
        L16:
            String[] r52 = r02.split(";");
            int r62 = r52.length;
        L17:
            if (r3 >= r62) goto L23;
            String r1 = r52[r3];
            if (r1.indexOf(61) >= 0) goto L22;
            r02 = r1;
        L22:
            r3 = r3 + 1;
            goto L17
        L23:
            return r02;
        }

        public String[] getRawValue() {
            int r0 = this.fullData.indexOf(58);
            int r1 = 0;
            if (r0 < 0) goto L5;
            String r2 = this.fullData.substring(0, r0);
            String r11 = this.fullData.substring(r0 + 1);
            String[] r22 = r2.split(";");
            int r4 = r22.length;
            String r5 = null;
            String r6 = "UTF-8";
            int r7 = 0;
        L7:
            if (r7 >= r4) goto L18;
            String[] r8 = r22[r7].split("=");
            if (r8.length != 2) goto L17;
            if (r8[0].equals("CHARSET") == false) goto L15;
            r6 = r8[1];
            goto L17
        L15:
            if (r8[0].equals("ENCODING") == false) goto L17;
            r5 = r8[1];
        L17:
            r7 = r7 + 1;
            goto L7
        L18:
            String[] r112 = r11.split(";");
        L20:
            if (r1 >= r112.length) goto L33;
            if (TextUtils.isEmpty(r112[r1]) == true) goto L32;
            if (r5 == null) goto L32;
            if (r5.equalsIgnoreCase("QUOTED-PRINTABLE") == false) goto L32;
            byte[] r02 = AndroidUtilities.decodeQuotedPrintable(AndroidUtilities.getStringBytes(r112[r1]));
            if (r02 == null) goto L32;
            if (r02.length == 0) goto L32;
            r112[r1] = new String(r02, r6);     // Catch: Exception -> L34
        L32:
            r1 = r1 + 1;
            goto L20
        L33:
            return r112;
        L5:
            return new String[0];
        }

        public String getType() {
            int r0 = this.type;
            char r1 = 4;
            if (r0 != 4) goto L7;
            return LocaleController.getString(R.string.ContactNote);
        L7:
            if (r0 != 3) goto L11;
            return LocaleController.getString(R.string.ContactUrl);
        L11:
            if (r0 != 5) goto L15;
            return LocaleController.getString(R.string.ContactBirthday);
        L15:
            if (r0 == 6) goto L17;
            int r02 = this.fullData.indexOf(58);
            if (r02 >= 0) goto L26;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L26:
            String r03 = this.fullData.substring(0, r02);
            if (this.type != 20) goto L29;
            String r11 = r03.substring(2).split(";")[0];
        L74:
            return r11.substring(0, 1).toUpperCase() + r11.substring(1).toLowerCase();
        L29:
            String[] r112 = r03.split(";");
            int r4 = r112.length;
            int r7 = 0;
        L30:
            if (r7 >= r4) goto L37;
            String r9 = r112[r7];
            if (r9.indexOf(61) >= 0) goto L35;
            r03 = r9;
        L35:
            r7 = r7 + 1;
            goto L30
        L37:
            if (r03.startsWith("X-") == false) goto L39;
            r11 = r03.substring(2);
        L41:
            switch(r11.hashCode()) {
                case -2015525726: goto L63;
                case 2064738: goto L59;
                case 2223327: goto L55;
                case 2464291: goto L51;
                case 2670353: goto L48;
                case 75532016: goto L44;
                default: goto L42;
            };
        L42:
            r1 = 65535;
        L66:
            switch(r1) {
                case 0: goto L72;
                case 1: goto L72;
                case 2: goto L71;
                case 3: goto L70;
                case 4: goto L69;
                case 5: goto L68;
                default: goto L74;
            };
        L68:
            r11 = LocaleController.getString(R.string.PhoneOther);
            goto L74
        L69:
            r11 = LocaleController.getString(R.string.PhoneWork);
            goto L74
        L70:
            r11 = LocaleController.getString(R.string.PhoneMain);
            goto L74
        L71:
            r11 = LocaleController.getString(R.string.PhoneHome);
            goto L74
        L72:
            r11 = LocaleController.getString(R.string.PhoneMobile);
            goto L74
        L44:
            if (r11.equals("OTHER") == false) goto L42;
            r1 = 5;
            goto L66
        L48:
            if (r11.equals("WORK") == true) goto L66;
        L51:
            if (r11.equals("PREF") == false) goto L42;
            r1 = 3;
            goto L66
        L55:
            if (r11.equals("HOME") == false) goto L42;
            r1 = 2;
            goto L66
        L59:
            if (r11.equals("CELL") == false) goto L42;
            r1 = 1;
            goto L66
        L63:
            if (r11.equals("MOBILE") == false) goto L42;
            r1 = 0;
            goto L66
        L39:
            r11 = r03;
            goto L41
        L17:
            if ("ORG".equalsIgnoreCase(getRawType(true)) == false) goto L21;
            return LocaleController.getString(R.string.ContactJob);
        L21:
            return LocaleController.getString(R.string.ContactJobTitle);
        }

        public String getValue(boolean r14) {
            StringBuilder r0 = new StringBuilder();
            int r1 = this.fullData.indexOf(58);
            if (r1 >= 0) goto L7;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L7:
            if (r0.length() <= 0) goto L9;
            r0.append(", ");
        L9:
            String r2 = this.fullData.substring(0, r1);
            String r12 = this.fullData.substring(r1 + 1);
            String[] r22 = r2.split(";");
            int r6 = r22.length;
            String r7 = null;
            String r8 = "UTF-8";
            int r9 = 0;
        L11:
            if (r9 >= r6) goto L22;
            String[] r11 = r22[r9].split("=");
            if (r11.length != 2) goto L21;
            if (r11[0].equals("CHARSET") == false) goto L19;
            r8 = r11[1];
            goto L21
        L19:
            if (r11[0].equals("ENCODING") == false) goto L21;
            r7 = r11[1];
        L21:
            r9 = r9 + 1;
            goto L11
        L22:
            String[] r13 = r12.split(";");
            int r23 = 0;
            boolean r4 = false;
        L24:
            if (r23 >= r13.length) goto L47;
            if (TextUtils.isEmpty(r13[r23]) == true) goto L46;
            if (r7 != null) goto L30;
        L36:
            if (r4 == true) goto L38;
        L40:
            r0.append(r13[r23]);
            if (r4 == true) goto L46;
            if (r13[r23].length() <= 0) goto L45;
            r4 = true;
            goto L46
        L45:
            r4 = false;
            goto L46
        L38:
            if (r0.length() <= 0) goto L40;
            r0.append(" ");
            goto L40
        L30:
            if (r7.equalsIgnoreCase("QUOTED-PRINTABLE") == false) goto L36;
            byte[] r62 = AndroidUtilities.decodeQuotedPrintable(AndroidUtilities.getStringBytes(r13[r23]));
            if (r62 == null) goto L36;
            if (r62.length == 0) goto L36;
            r13[r23] = new String(r62, r8);     // Catch: Exception -> L62
        L46:
            r23 = r23 + 1;
            goto L24
        L47:
            if (r14 == false) goto L61;
            int r132 = this.type;
            if (r132 != 0) goto L53;
            return PhoneFormat.getInstance().format(r0.toString());
        L53:
            if (r132 != 5) goto L61;
            String[] r133 = r0.toString().split("T");
            if (r133.length <= 0) goto L61;
            String[] r134 = r133[0].split("-");
            if (r134.length != 3) goto L61;
            Calendar r02 = Calendar.getInstance();
            r02.set(1, Utilities.parseInt(r134[0]));
            r02.set(2, Utilities.parseInt(r134[1]) - 1);
            r02.set(5, Utilities.parseInt(r134[2]));
            return LocaleController.getInstance().getFormatterYearMax().format(r02.getTime());
        L61:
            return r0.toString();
        }
    }

    /* renamed from: $r8$lambda$-KhNaete-puZm8ZT4fOcBgykfoI, reason: not valid java name */
    public static /* synthetic */ int m3869$r8$lambda$KhNaetepuZm8ZT4fOcBgykfoI(LinkSpec r2, LinkSpec r3) {
        int r0 = r2.start;
        int r1 = r3.start;
        if (r0 < r1) goto L17;
        if (r0 > r1) goto L16;
        int r22 = r2.end;
        int r32 = r3.end;
        if (r22 >= r32) goto L11;
        return 1;
    L11:
        if (r22 <= r32) goto L14;
        return -1;
    L14:
        return 0;
    L16:
        return 1;
    L17:
        return -1;
    }

    /* renamed from: $r8$lambda$0F4KjwN-tH7e-KBB5ZrPUMWlqPs, reason: not valid java name */
    public static /* synthetic */ void m3870$r8$lambda$0F4KjwNtH7eKBB5ZrPUMWlqPs(String r2, BaseFragment r3, AlertDialog r4, int r5) {
        r3.getParentActivity().startActivityForResult(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + r2)), MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ String $r8$lambda$0skUUed100ckKpApvQctKsZKXSg(Integer r0) {
        return "%s";
    }

    public static /* synthetic */ void $r8$lambda$114_tsQ3Yj3FepwwLiuAukKWoYc(View r7, ValueAnimator r8) {
        r7.setTranslationX((float) ((((r8 * 4.0f) * (1.0f - r8)) * Math.sin((((Float) r8.getAnimatedValue()).floatValue() * 3.141592653589793d) * 4.0d)) * dp(4.0f)));
    }

    public static /* synthetic */ void $r8$lambda$5nhaQy2PXxEWeSNnFoicKyQz_A8(boolean[] r2, ButtonSpan.TextViewButtons[] r3, ProxySettings r4, Activity r5) {
        if (r2[0] == false) goto L5;
        return;
    L5:
        AndroidUtilities$$ExternalSyntheticLambda9 r1 = new AndroidUtilities$$ExternalSyntheticLambda9(r2, r3, r4);
        SharedPreferences r22 = MessagesController.getGlobalMainSettings();
        if (r22.getBoolean("proxycheckstatusip", false) == false) goto L9;
        r1.run();
        return;
    L9:
        new AlertDialog.Builder(r5).setTitle(LocaleController.getString(R.string.ProxyBottomSheetCheckWarning)).setMessage(LocaleController.getString(R.string.ProxyBottomSheetCheckWarningText)).setPositiveButton(LocaleController.getString(R.string.Proceed), new AndroidUtilities$$ExternalSyntheticLambda10(r22, r1)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).show();
    }

    public static /* synthetic */ void $r8$lambda$8blcPb9f6MqaWryNz2wNlTWnvlY(RecyclerView r1) {
        if (r1.getAdapter() == null) goto L6;
        r1.getAdapter().notifyDataSetChanged();
        return;
    }

    /* renamed from: $r8$lambda$AXWadX_SG4DsFFzs-oANjNwcQcI, reason: not valid java name */
    public static /* synthetic */ void m3871$r8$lambda$AXWadX_SG4DsFFzsoANjNwcQcI(ProxySettings r3, ProxyController r4, SharedConfig.ProxyInfo r5, Activity r6, Runnable r7, View r8) {
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putBoolean("proxy_enabled", true);
        r3.toSharedPreferences(r0);
        r0.commit();
        r4.setCurrentProxy(r4.saveProxy(r5, null, null));
        ConnectionsManager.setProxySettings(true, r3);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.proxySettingsChanged, new Object[0]);
        if ((r6 instanceof LaunchActivity) == false) goto L10;
        BaseFragment r32 = ((LaunchActivity) r6).getActionBarLayout().getLastFragment();
        if ((r32 instanceof ChatActivity) == false) goto L9;
        UndoView r33 = ((ChatActivity) r32).getUndoView();
        if (r33 == null) goto L9;
        r33.showWithAction(0, 87, null);
    L11:
        r7.run();
        return;
    L9:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{6, LocaleController.getString(R.string.ProxyAddedSuccess)});
        goto L11
    L10:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{6, LocaleController.getString(R.string.ProxyAddedSuccess)});
        goto L11
    }

    public static /* synthetic */ void $r8$lambda$AxkaJ2J73z4mS3JZ7ehTEo5XrWI(Runnable r0, View r1, float r2, DynamicAnimation r3, boolean r4, float r5, float r6) {
        if (r0 == null) goto L4;
        r0.run();
    L4:
        r1.setTranslationX(r2);
        if ((r1.getParent() instanceof View) == false) goto L7;
        ((View) r1.getParent()).invalidate();
    L7:
        r1.setTag(R.id.spring_tag, null);
        r1.setTag(R.id.spring_was_translation_x_tag, null);
    }

    public static /* synthetic */ int $r8$lambda$BrP4SeISaW43Msst27syoBjfafI(BaseFragment r2, String r3, RecyclerListView r4) {
        int r0 = -1;
        Field r32 = r2.getClass().getDeclaredField(r3);     // Catch: Throwable -> L5
        r32.setAccessible(true);     // Catch: Throwable -> L5
        LinearLayoutManager r42 = (LinearLayoutManager) r4.getLayoutManager();     // Catch: Throwable -> L5
        r0 = r32.getInt(r2);     // Catch: Throwable -> L5
        r42.scrollToPositionWithOffset(r0, dp(60.0f));     // Catch: Throwable -> L5
        r32.setAccessible(false);     // Catch: Throwable -> L5
        return r0;
    L8:
        return r0;
    }

    public static /* synthetic */ Boolean $r8$lambda$CEcQZmOn3RwbTaUHT0SBERSJNjU(Utilities.Callback0Return r0) {
        return (Boolean) r0.run();
    L4:
        e = move-exception;
        FileLog.e(e);
        return Boolean.FALSE;
    }

    public static /* synthetic */ void $r8$lambda$GVblqDyrKZC295RoPlor3ZZo1sk(ArrayList r2) {
        Utilities.globalQueue.postRunnable(new AndroidUtilities$$ExternalSyntheticLambda21(r2));
    }

    public static /* synthetic */ void $r8$lambda$IvmkrQPgAHXbIzKYk9WrbgZPdvI(Runnable r0, int r1) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$J4uGjwnOr5qIdWYhIX4Og12v1ts(Void r0) {
        if (BuildVars.DEBUG_VERSION == false) goto L6;
        FileLog.d("sms listener registered");
        return;
    }

    /* renamed from: $r8$lambda$OPhqWPGY2c-8XMtBOhpyaiXR8Rs, reason: not valid java name */
    public static /* synthetic */ Boolean m3872$r8$lambda$OPhqWPGY2c8XMtBOhpyaiXR8Rs(SpannableStringBuilder r0, String r1, int r2, boolean r3, boolean r4) {
        return Boolean.valueOf(addLinks(r0, r1, r2, r3, r4));
    }

    /* renamed from: $r8$lambda$TqylQ7mqSnaj2Z10Afg6KQFAD-I, reason: not valid java name */
    public static /* synthetic */ boolean m3873$r8$lambda$TqylQ7mqSnaj2Z10Afg6KQFADI(CharSequence r0, int r1, int r2) {
        if (r1 != 0) goto L6;
    L7:
        return true;
    L6:
        if (r0.charAt(r1 - 1) != '@') goto L7;
        return false;
    }

    public static /* synthetic */ Typeface $r8$lambda$U1asOTL621l4z8g5RIfymaYl5pg(String r3) {
    L6:
        e = move-exception;
        FileLog.e("Could not get typeface '" + r3 + "' because " + e.getMessage());
        return null;
    L3:
        if (ExteraConfig.getUseSystemFonts() == false) goto L5;
        Typeface r0 = FontUtils.getSystemTypeface(r3);     // Catch: Exception -> L6
        if (r0 == null) goto L12;
        return r0;
    L12:
        return FontUtils.getFontFromAssets(r3);
    L5:
        return FontUtils.getFontFromAssets(r3);
    }

    /* renamed from: $r8$lambda$U1mjz0c6pNX--EGrZtp2rsOmuR0, reason: not valid java name */
    public static /* synthetic */ void m3874$r8$lambda$U1mjz0c6pNXEGrZtp2rsOmuR0(boolean[] r1, CountDownLatch r2, int r3) {
        if (r3 != 0) goto L5;
        boolean r32 = true;
    L6:
        r1[0] = r32;
        r2.countDown();
        return;
    L5:
        r32 = false;
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$YNeOyFI20rZ0iTOmA2utyLFP_fE(WeakReference r1, String r2) {
        ButtonSpan.TextViewButtons r12 = (ButtonSpan.TextViewButtons) r1.get();
        if (r12 != null) goto L6;
        return;
    L6:
        if (TextUtils.isEmpty(r2) == false) goto L8;
        r2 = LocaleController.getString(R.string.Unknown);
    L8:
        r12.setText(r2);
    }

    public static /* synthetic */ void $r8$lambda$ZBP_rGQSuvWa9xzbu82YSSoGfZY(ImageView r2, AtomicBoolean r3, Drawable r4, ValueAnimator r5) {
        float r52 = ((Float) r5.getAnimatedValue()).floatValue();
        float r1 = Math.abs(r52 - 0.5f) + 0.5f;
        r2.setScaleX(r1);
        r2.setScaleY(r1);
        if (r52 >= 0.5f) goto L5;
        return;
    L5:
        if (r3.get() == true) goto L9;
        r3.set(true);
        r2.setImageDrawable(r4);
        return;
    }

    public static /* synthetic */ void $r8$lambda$_sl6RH9eOaKRW1FdZTeZIJdcpr0(boolean[] r4, ButtonSpan.TextViewButtons[] r5, ProxySettings r6) {
        if (r4[0] == false) goto L5;
        return;
    L5:
        r4[0] = true;
        r5[0].setText(LocaleController.getString(R.string.ProxyBottomSheetChecking) + "...");
        r5[0].clear();
        ConnectionsManager.getInstance(UserConfig.selectedAccount).checkProxy(r6, new AndroidUtilities$$ExternalSyntheticLambda16(r4, r5));     // Catch: NumberFormatException -> L8
        return;
    L8:
        r4[0] = false;
        r5[0].setText(LocaleController.getString(R.string.Unavailable));
        r5[0].setTextColor(Theme.getColor(Theme.key_text_RedRegular));
    }

    public static /* synthetic */ void $r8$lambda$_zd1mJRiI1A9lDjV1VcY_jLZI5A(View r0, DynamicAnimation r1, float r2, float r3) {
        if ((r0.getParent() instanceof View) == false) goto L6;
        ((View) r0.getParent()).invalidate();
        return;
    }

    public static /* synthetic */ void $r8$lambda$aGRwEkVibMbJIvp3dF5n8rdo7tg(SharedPreferences r0, Runnable r1, AlertDialog r2, int r3) {
        r0.edit().putBoolean("proxycheckstatusip", true).apply();
        r1.run();
    }

    public static /* synthetic */ void $r8$lambda$adO3eTQHaoFbg8HbDUVYPWuzABQ(boolean[] r1, ButtonSpan.TextViewButtons[] r2, long r3) {
        runOnUIThread(new AndroidUtilities$$ExternalSyntheticLambda3(r1, r3, r2));
    }

    public static /* synthetic */ void $r8$lambda$amnX1yho5S08voUPtAyQzCgIt9E(boolean[] r3, long r4, ButtonSpan.TextViewButtons[] r6) {
        r3[0] = false;
        if (r4 != (-1)) goto L6;
        r6[0].setText(LocaleController.getString(R.string.Unavailable));
        r6[0].setTextColor(Theme.getColor(Theme.key_text_RedRegular));
        return;
    L6:
        r6[0].setText(LocaleController.formatString(R.string.Ping2, new Object[]{Long.valueOf(r4)}));
        r6[0].setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGreenText));
    }

    public static /* synthetic */ void $r8$lambda$dbJ_kwONwNyQCcwH5Wpjk9vjteY(CountDownLatch r0, int r1) {
        r0.countDown();
    }

    public static /* synthetic */ String $r8$lambda$hJbusTAKAma5Rxwp0wAWN6xJJDg(Integer r2) {
        return "%" + (r2.intValue() + 1) + "$s";
    }

    public static /* synthetic */ void $r8$lambda$i21hdvLSrVyXTv5vepryqyfSj6U(IntColorCallback r0, Window r1, ValueAnimator r2) {
        int r22 = ((Integer) r2.getAnimatedValue()).intValue();
        if (r0 == null) goto L8;
        r0.run(r22);
    L8:
        r1.setNavigationBarColor(r22);     // Catch: Exception -> L7
        return;
    }

    public static /* synthetic */ boolean $r8$lambda$nDdac_H_x5E6XpTEYUCiwboDsTU(ViewTreeObserver r2, ViewTreeObserver.OnPreDrawListener[] r3, boolean[] r4, Runnable r5) {
        if (r2.isAlive() == false) goto L6;
        r2.removeOnPreDrawListener(r3[0]);
    L6:
        if (r4[0] == true) goto L8;
        r4[0] = true;
        r5.run();
    L8:
        return true;
    }

    public static /* synthetic */ void $r8$lambda$rLoPqu4Dd4z4V6K51HBx8QtDBvM(CountDownLatch r0, int r1) {
        r0.countDown();
    }

    public static /* synthetic */ void $r8$lambda$xUpIG0xWvQrEWNwHNYj7d5nwyYU(Intent r18) {
        int r1 = UserConfig.selectedAccount;     // Catch: Exception -> L18
        ApplicationLoader.postInitApplication();     // Catch: Exception -> L18
        if (needShowPasscode() == false) goto L6;
        return;
    L6:
        if (SharedConfig.isWaitingForPasscodeEnter == true) goto L24;
        String r3 = r18.getStringExtra("android.intent.extra.TEXT");     // Catch: Exception -> L18
        if (TextUtils.isEmpty(r3) == true) goto L25;
        String r2 = r18.getStringExtra("com.google.android.voicesearch.extra.RECIPIENT_CONTACT_URI");     // Catch: Exception -> L18
        long r4 = Long.parseLong(r18.getStringExtra("com.google.android.voicesearch.extra.RECIPIENT_CONTACT_CHAT_ID"));     // Catch: Exception -> L18
        TLRPC.User r0 = MessagesController.getInstance(r1).getUser(Long.valueOf(r4));     // Catch: Exception -> L18
        if (r0 != null) goto L15;
        r0 = MessagesStorage.getInstance(r1).getUserSync(r4);     // Catch: Exception -> L18
        if (r0 == null) goto L15;
        MessagesController.getInstance(r1).putUser(r0, true);     // Catch: Exception -> L18
    L15:
        if (r0 == null) goto L26;
        ContactsController.getInstance(r1).markAsContacted(r2);     // Catch: Exception -> L18
        SendMessagesHelper.getInstance(r1).sendMessage(SendMessagesHelper.SendMessageParams.of(r3, r0.id, null, null, null, true, null, null, null, true, 0, 0, null, false));     // Catch: Exception -> L18
        return;
    L26:
        return;
    L25:
        return;
    L24:
        return;
    L18:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$zrWNCtpqYtQ17xrk0FlGhXkHpvI(ArrayList r3) {
        int r0 = 0;
    L4:
        if (r0 >= r3.size()) goto L14;
        Bitmap r1 = (Bitmap) ((WeakReference) r3.get(r0)).get();
        ((WeakReference) r3.get(r0)).clear();
        if (r1 == null) goto L13;
        if (r1.isRecycled() == true) goto L13;
        r1.recycle();     // Catch: Exception -> L11
    L11:
        e = move-exception;
        FileLog.e(e);
    L13:
        r0 = r0 + 1;
        goto L4
    }

    /* renamed from: -$$Nest$sfgetnavigationBarColorAnimators, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m3875$$Nest$sfgetnavigationBarColorAnimators() {
        return navigationBarColorAnimators;
    }

    static {
        readBufferLocal = new ThreadLocal();
        bufferLocal = new ThreadLocal();
        typefaceCache = new ConcurrentHashMap();
        prevOrientation = -10;
        waitingForSms = false;
        waitingForCall = false;
        smsLock = new Object();
        callLock = new Object();
        statusBarHeight = 0;
        navigationBarHeight = 0;
        density = 1.0f;
        displaySize = new Point();
        screenRefreshRate = 60.0f;
        screenMaxRefreshRate = 60.0f;
        screenRefreshTime = 1000.0f / 60.0f;
        photoSize = null;
        highQualityPhotoSize = null;
        displayMetrics = new DisplayMetrics();
        decelerateInterpolator = new DecelerateInterpolator();
        accelerateInterpolator = new AccelerateInterpolator();
        overshootInterpolator = new OvershootInterpolator();
        isTablet = null;
        wasTablet = null;
        isSmallScreen = null;
        adjustOwnerClassGuid = 0;
        altFocusableClassGuid = 0;
        rectTmp = new RectF();
        rectTmp2 = new Rect();
        pointTmp2 = new int[2];
        WEB_URL = null;
        BAD_CHARS_PATTERN = null;
        LONG_BAD_CHARS_PATTERN = null;
        BAD_CHARS_MESSAGE_PATTERN = null;
        BAD_CHARS_MESSAGE_LONG_PATTERN = null;
        REMOVE_MULTIPLE_DIACRITICS = null;
        REMOVE_RTL = null;
        singleTagPatter = null;
        strokeTop = new Paint(1);
        strokeBottom = new Paint(1);
        BAD_CHARS_PATTERN = Pattern.compile("[─-◿]");     // Catch: Exception -> L5
        LONG_BAD_CHARS_PATTERN = Pattern.compile("[一-鿿]");     // Catch: Exception -> L5
        BAD_CHARS_MESSAGE_LONG_PATTERN = Pattern.compile("[̀-ͯ\u2066-\u2067]");     // Catch: Exception -> L5
        BAD_CHARS_MESSAGE_PATTERN = Pattern.compile("[\u2066-\u2067]+");     // Catch: Exception -> L5
        REMOVE_MULTIPLE_DIACRITICS = Pattern.compile("([\\u0300-\\u036f]{1,2})[\\u0300-\\u036f]+");     // Catch: Exception -> L5
        WEB_URL = Pattern.compile("((?:(http|https|Http|Https|ton|tg|tonsite):\\/\\/(?:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@)?)?(?:" + Pattern.compile("(([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef]([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef\\-]{0,61}[a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef]){0,1}\\.)+[a-zA-Z -\ud7ff豈-﷏ﷰ-\uffef]{2,63}|" + Pattern.compile("((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9]))") + ")") + ")(?:\\:\\d{1,5})?)(\\/(?:(?:[a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef\\;\\/\\?\\:\\@\\&\\=\\#\\~\\-\\.\\+\\!\\*\\'\\(\\)\\,\\_])|(?:\\%[a-fA-F0-9]{2}))*)?(?:\\b|$)");     // Catch: Exception -> L5
    L8:
        if (isTablet() == false) goto L10;
        int r0 = 80;
    L11:
        leftBaseline = r0;
        checkDisplaySize(ApplicationLoader.applicationContext, null);
        documentIcons = new int[]{R.drawable.media_doc_blue, R.drawable.media_doc_green, R.drawable.media_doc_red, R.drawable.media_doc_yellow};
        documentMediaIcons = new int[]{R.drawable.media_doc_blue_b, R.drawable.media_doc_green_b, R.drawable.media_doc_red_b, R.drawable.media_doc_yellow_b};
        IPV4_URL = Pattern.compile("(?:\\d{1,3}\\.){3}\\d{1,3}(?:[:/?#]|$)");
        sUrlMatchFilter = new AndroidUtilities$$ExternalSyntheticLambda24();
        int r7 = ConnectionsManager.CPU_COUNT;
        LINK_EXECUTOR = new ThreadPoolExecutor(r7, r7, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(100), new ThreadPoolExecutor.DiscardPolicy());
        hasCallPermissions = true;
        numbersSignatureArray = new String[]{_UrlKt.FRAGMENT_ENCODE_SET, "K", "M", "B", "T", "P"};
        tempFloats = new float[9];
        tempFloats2 = new float[9];
        characters = new char[]{Typography.nbsp, ' ', '!', Typography.quote, '#', '%', Typography.amp, '\'', '(', ')', '*', ',', SignatureVisitor.SUPER, '.', '/', ':', ';', '?', '@', '[', '\\', ']', '_', '{', '}', 161, Typography.section, 171, Typography.paragraph, Typography.middleDot, 187, 191, 894, 903, 1370, 1371, 1372, 1373, 1374, 1375, 1417, 1418, 1470, 1472, 1475, 1478, 1523, 1524, 1545, 1546, 1548, 1549, 1563, 1566, 1567, 1642, 1643, 1644, 1645, 1748, 1792, 1793, 1794, 1795, 1796, 1797, 1798, 1799, 1800, 1801, 1802, 1803, 1804, 1805, 2039, 2040, 2041, 2096, 2097, 2098, 2099, 2100, 2101, 2102, 2103, 2104, 2105, 2106, 2107, 2108, 2109, 2110, 2142, 2404, 2405, 2416, 2557, 2678, 2800, 3191, 3204, 3572, 3663, 3674, 3675, 3844, 3845, 3846, 3847, 3848, 3849, 3850, 3851, 3852, 3853, 3854, 3855, 3856, 3857, 3858, 3860, 3898, 3899, 3900, 3901, 3973, 4048, 4049, 4050, 4051, 4052, 4057, 4058, 4170, 4171, 4172, 4173, 4174, 4175, 4347, 4960, 4961, 4962, 4963, 4964, 4965, 4966, 4967, 4968, 5120, 5742, 5787, 5788, 5867, 5868, 5869, 5941, 5942, 6100, 6101, 6102, 6104, 6105, 6106, 6144, 6145, 6146, 6147, 6148, 6149, 6150, 6151, 6152, 6153, 6154, 6468, 6469, 6686, 6687, 6816, 6817, 6818, 6819, 6820, 6821, 6822, 6824, 6825, 6826, 6827, 6828, 6829, 7002, 7003, 7004, 7005, 7006, 7007, 7008, 7164, 7165, 7166, 7167, 7227, 7228, 7229, 7230, 7231, 7294, 7295, 7360, 7361, 7362, 7363, 7364, 7365, 7366, 7367, 7379, 8208, 8209, 8210, Typography.ndash, Typography.mdash, 8213, 8214, 8215, Typography.leftSingleQuote, Typography.rightSingleQuote, Typography.lowSingleQuote, 8219, Typography.leftDoubleQuote, Typography.rightDoubleQuote, Typography.lowDoubleQuote, 8223, Typography.f498dagger, Typography.doubleDagger, Typography.bullet, 8227, 8228, 8229, Typography.ellipsis, 8231, 8240, 8241, Typography.prime, Typography.doublePrime, 8244, 8245, 8246, 8247, 8248, 8249, 8250, 8251, 8252, 8253, 8254, 8255, 8256, 8257, 8258, 8259, 8261, 8262, 8263, 8264, 8265, 8266, 8267, 8268, 8269, 8270, 8271, 8272, 8273, 8275, 8276, 8277, 8278, 8279, 8280, 8281, 8282, 8283, 8284, 8285, 8286, 8317, 8318, 8333, 8334, 8968, 8969, 8970, 8971, 9001, 9002, 10088, 10089, 10090, 10091, 10092, 10093, 10094, 10095, 10096, 10097, 10098, 10099, 10100, 10101, 10181, 10182, 10214, 10215, 10216, 10217, 10218, 10219, 10220, 10221, 10222, 10223, 10627, 10628, 10629, 10630, 10631, 10632, 10633, 10634, 10635, 10636, 10637, 10638, 10639, 10640, 10641, 10642, 10643, 10644, 10645, 10646, 10647, 10648, 10712, 10713, 10714, 10715, 10748, 10749, 11513, 11514, 11515, 11516, 11518, 11519, 11632, 11776, 11777, 11778, 11779, 11780, 11781, 11782, 11783, 11784, 11785, 11786, 11787, 11788, 11789, 11790, 11791, 11792, 11793, 11794, 11795, 11796, 11797, 11798, 11799, 11800, 11801, 11802, 11803, 11804, 11805, 11806, 11807, 11808, 11809, 11810, 11811, 11812, 11813, 11814, 11815, 11816, 11817, 11818, 11819, 11820, 11821, 11822, 11824, 11825, 11826, 11827, 11828, 11829, 11830, 11831, 11832, 11833, 11834, 11835, 11836, 11837, 11838, 11839, 11840, 11841, 11842, 11843, 11844, 11845, 11846, 11847, 11848, 11849, 11850, 11851, 11852, 11853, 11854, 11855, 12289, 12290, 12291, 12296, 12297, 12298, 12299, 12300, 12301, 12302, 12303, 12304, 12305, 12308, 12309, 12310, 12311, 12312, 12313, 12314, 12315, 12316, 12317, 12318, 12319, 12336, 12349, 12448, 12539, 42238, 42239, 42509, 42510, 42511, 42611, 42622, 42738, 42739, 42740, 42741, 42742, 42743, 43124, 43125, 43126, 43127, 43214, 43215, 43256, 43257, 43258, 43260, 43310, 43311, 43359, 43457, 43458, 43459, 43460, 43461, 43462, 43463, 43464, 43465, 43466, 43467, 43468, 43469, 43486, 43487, 43612, 43613, 43614, 43615, 43742, 43743, 43760, 43761, 44011, 64830, 64831, 65040, 65041, 65042, 65043, 65044, 65045, 65046, 65047, 65048, 65049, 65072, 65073, 65074, 65075, 65076, 65077, 65078, 65079, 65080, 65081, 65082, 65083, 65084, 65085, 65086, 65087, 65088, 65089, 65090, 65091, 65092, 65093, 65094, 65095, 65096, 65097, 65098, 65099, 65100, 65101, 65102, 65103, 65104, 65105, 65106, 65108, 65109, 65110, 65111, 65112, 65113, 65114, 65115, 65116, 65117, 65118, 65119, 65120, 65121, 65123, 65128, 65130, 65131, 65281, 65282, 65283, 65285, 65286, 65287, 65288, 65289, 65290, 65292, 65293, 65294, 65295, 65306, 65307, 65311, 65312, 65339, 65340, 65341, 65343, 65371, 65373, 65375, 65376, 65377, 65378, 65379, 65380, 65381};
        navbarProtactionPaint = new Paint(1);
        return;
    L10:
        r0 = 72;
    L5:
        e = move-exception;
        FileLog.e(e);
        goto L8
    }

    public AndroidUtilities() {
    }

    public static int HSBtoRGB(float r6, float r7, float r8) {
        if (r7 != 0.0f) goto L6;
        int r62 = (int) ((r8 * 255.0f) + 0.5f);
    L5:
        int r72 = r62;
        int r82 = r72;
    L28:
        return ((((r62 & 255) << 16) | (-16777216)) | ((r72 & 255) << 8)) | (r82 & 255);
    L6:
        float r63 = (r6 - ((float) Math.floor(r6))) * 6.0f;
        float r0 = r63 - ((float) Math.floor(r63));
        float r4 = (1.0f - r7) * r8;
        float r5 = (1.0f - (r7 * r0)) * r8;
        float r3 = (1.0f - (r7 * (1.0f - r0))) * r8;
        int r64 = (int) r63;
        if (r64 != 0) goto L9;
        r62 = (int) ((r8 * 255.0f) + 0.5f);
        r72 = (int) ((r3 * 255.0f) + 0.5f);
    L25:
        r82 = (int) ((r4 * 255.0f) + 0.5f);
        goto L28
    L9:
        if (r64 != 1) goto L11;
        r62 = (int) ((r5 * 255.0f) + 0.5f);
        r72 = (int) ((r8 * 255.0f) + 0.5f);
        goto L25
    L11:
        if (r64 != 2) goto L13;
        r62 = (int) ((r4 * 255.0f) + 0.5f);
        r72 = (int) ((r8 * 255.0f) + 0.5f);
        r82 = (int) ((r3 * 255.0f) + 0.5f);
        goto L28
    L13:
        if (r64 != 3) goto L15;
        r62 = (int) ((r4 * 255.0f) + 0.5f);
        r72 = (int) ((r5 * 255.0f) + 0.5f);
    L21:
        r82 = (int) ((r8 * 255.0f) + 0.5f);
        goto L28
    L15:
        if (r64 != 4) goto L17;
        r62 = (int) ((r3 * 255.0f) + 0.5f);
        r72 = (int) ((r4 * 255.0f) + 0.5f);
        goto L21
    L17:
        if (r64 == 5) goto L19;
        r62 = 0;
        goto L5
    L19:
        r62 = (int) ((r8 * 255.0f) + 0.5f);
        r72 = (int) ((r4 * 255.0f) + 0.5f);
        r82 = (int) ((r5 * 255.0f) + 0.5f);
        goto L28
    }

    public static float[] RGBtoHSB(int r7, int r8, int r9) {
        int r0 = Math.max(r7, r8);
        if (r9 <= r0) goto L5;
        r0 = r9;
    L5:
        int r1 = Math.min(r7, r8);
        if (r9 >= r1) goto L8;
        r1 = r9;
    L8:
        float r2 = r0;
        float r3 = r2 / 255.0f;
        float r4 = 0.0f;
        if (r0 == 0) goto L11;
        float r5 = (r0 - r1) / r2;
    L13:
        if (r5 == 0.0f) goto L26;
        float r12 = r0 - r1;
        float r22 = (r0 - r7) / r12;
        float r6 = (r0 - r8) / r12;
        float r92 = (r0 - r9) / r12;
        if (r7 != r0) goto L18;
        float r93 = r92 - r6;
    L21:
        float r94 = r93 / 6.0f;
        if (r94 >= 0.0f) goto L24;
        r4 = r94 + 1.0f;
        goto L26
    L24:
        r4 = r94;
        goto L26
    L18:
        if (r8 != r0) goto L20;
        r93 = (r22 + 2.0f) - r92;
        goto L21
    L20:
        r93 = (r6 + 4.0f) - r22;
    L26:
        return new float[]{r4, r5, r3};
    L11:
        r5 = 0.0f;
        goto L13
    }

    @Deprecated
    public static boolean addLinks(Spannable r1, int r2) {
        return addLinks(r1, r2, false);
    }

    @Deprecated
    public static boolean addLinks(Spannable r1, int r2, boolean r3) {
        return addLinks(r1, r2, r3, true);
    }

    @Deprecated
    public static boolean addLinks(Spannable r1, int r2, boolean r3, boolean r4) {
        return addLinks(r1, getPlainText(r1), r2, r3, r4);
    }

    private static boolean addLinks(Spannable r11, String r12, int r13, boolean r14, boolean r15) {
        if (r11 == null) goto L49;
        if (r12 == null) goto L49;
        if (containsUnsupportedCharacters(r12) == true) goto L49;
        if (r13 == 0) goto L49;
        URLSpan[] r1 = (URLSpan[]) r11.getSpans(0, r11.length(), URLSpan.class);
        int r3 = r1.length - 1;
    L10:
        if (r3 < 0) goto L16;
        URLSpan r5 = r1[r3];
        if ((r5 instanceof URLSpanReplacement) == false) goto L14;
        if (r15 == true) goto L14;
    L15:
        r3 = r3 - 1;
    L14:
        r11.removeSpan(r5);
        goto L15
    L16:
        ArrayList r52 = new ArrayList();
        if (r14 == true) goto L22;
        if ((r13 & 4) == 0) goto L22;
        Linkify.addLinks(r11, 4);
    L22:
        if ((r13 & 1) == 0) goto L24;
        gatherLinks(r52, r12, LinkifyPort.WEB_URL, new String[]{"http://", "https://", "tg://", "tonsite://"}, sUrlMatchFilter, r14);
    L24:
        pruneOverlaps(r52);
        if (r52.size() != 0) goto L27;
        return false;
    L27:
        int r122 = r52.size();
        int r132 = 0;
    L28:
        if (r132 >= r122) goto L48;
        LinkSpec r142 = (LinkSpec) r52.get(r132);
        URLSpan[] r16 = (URLSpan[]) r11.getSpans(r142.start, r142.end, URLSpan.class);
        if (r16 != null) goto L32;
    L40:
        String r17 = r142.url;
        if (r17 == null) goto L44;
        r17 = r17.replaceAll("∕|⁄|%E2%81%84|%E2%88%95", "/");
    L44:
        if (Browser.isTonsitePunycode(r17) == true) goto L47;
        r11.setSpan(new URLSpan(r17), r142.start, r142.end, 33);
    L47:
        r132 = r132 + 1;
        goto L28
    L32:
        if (r16.length <= 0) goto L40;
        int r32 = r16.length;
        int r6 = 0;
    L34:
        if (r6 >= r32) goto L40;
        URLSpan r7 = r16[r6];
        r11.removeSpan(r7);
        if ((r7 instanceof URLSpanReplacement) == false) goto L38;
        if (r15 == true) goto L38;
    L39:
        r6 = r6 + 1;
    L38:
        r11.removeSpan(r7);
        goto L39
    L48:
        return true;
    L49:
        return false;
    }

    public static boolean addLinksSafe(Spannable r7, int r8, boolean r9, boolean r10) {
        int r0 = 0;
        if (r7 != null) goto L5;
        return false;
    L5:
        SpannableStringBuilder r2 = new SpannableStringBuilder(r7);
        String r3 = getPlainText(r2);
        if (r3 != null) goto L8;
    L22:
        return false;
    L8:
        if (containsUnsupportedCharacters(r3) == true) goto L22;
        if (r8 == 0) goto L22;
        boolean r82 = doSafe(new AndroidUtilities$$ExternalSyntheticLambda18(r2, r3, r8, r9, r10));
        if (r82 == false) goto L21;
        URLSpan[] r92 = (URLSpan[]) r7.getSpans(0, r7.length(), URLSpan.class);
        int r1 = 0;
    L15:
        if (r1 >= r92.length) goto L17;
        r7.removeSpan(r92[r1]);
        r1 = r1 + 1;
        goto L15
    L17:
        URLSpan[] r93 = (URLSpan[]) r2.getSpans(0, r2.length(), URLSpan.class);
    L19:
        if (r0 >= r93.length) goto L21;
        URLSpan r102 = r93[r0];
        r7.setSpan(r102, r2.getSpanStart(r102), r2.getSpanEnd(r93[r0]), 33);
        r0 = r0 + 1;
    L21:
        return r82;
    }

    public static void addMediaToGallery(File r2) {
        Uri r22 = Uri.fromFile(r2);
        if (r22 != null) goto L10;
        return;
    L10:
        Intent r0 = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");     // Catch: Exception -> L7
        r0.setData(r22);     // Catch: Exception -> L7
        ApplicationLoader.applicationContext.sendBroadcast(r0);     // Catch: Exception -> L7
        return;
    L7:
        e = move-exception;
        FileLog.e(e);
    }

    public static void addMediaToGallery(String r1) {
        if (r1 != null) goto L4;
        return;
    L4:
        addMediaToGallery(new File(r1));
    }

    public static boolean addToClipboard(CharSequence r4) {
        ClipboardManager r0 = (ClipboardManager) ApplicationLoader.applicationContext.getSystemService("clipboard");     // Catch: Exception -> L9
        if ((r4 instanceof Spanned) == false) goto L7;
        r0.setPrimaryClip(ClipData.newHtmlText("label", r4, CustomHtml.toHtml((Spanned) r4)));     // Catch: Exception -> L9
        return true;
    L7:
        r0.setPrimaryClip(ClipData.newPlainText("label", r4));     // Catch: Exception -> L9
        return true;
    L9:
        e = move-exception;
        FileLog.e(e);
        return false;
    }

    public static boolean addToClipboard(CharSequence r2, String r3) {
        if (r3 == null) goto L4;
        ((ClipboardManager) ApplicationLoader.applicationContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newHtmlText("label", r2, r3));     // Catch: Exception -> L8
        return true;
    L8:
        e = move-exception;
        FileLog.e(e);
        return false;
    L4:
        return addToClipboard(r2);
    }

    public static void adjustBrightnessColorMatrix(ColorMatrix r5, float r6) {
        if (r5 != null) goto L4;
        return;
    L4:
        float r62 = r6 * 255.0f;
        r5.postConcat(new ColorMatrix(new float[]{1.0f, 0.0f, 0.0f, 0.0f, r62, 0.0f, 1.0f, 0.0f, 0.0f, r62, 0.0f, 0.0f, 1.0f, 0.0f, r62, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
    }

    public static void adjustHueColorMatrix(ColorMatrix r14, float r15) {
        float r152 = (cleanValue(r15, 180.0f) / 180.0f) * 3.1415927f;
        if (r152 != 0.0f) goto L5;
        return;
    L5:
        double r1 = r152;
        float r153 = (float) Math.cos(r1);
        float r12 = (float) Math.sin(r1);
        float r6 = (r153 * (-0.715f)) + 0.715f;
        float r8 = ((-0.072f) * r153) + 0.072f;
        float r4 = ((-0.213f) * r153) + 0.213f;
        r14.postConcat(new ColorMatrix(new float[]{((0.787f * r153) + 0.213f) + (r12 * (-0.213f)), ((-0.715f) * r12) + r6, (r12 * 0.928f) + r8, 0.0f, 0.0f, (0.143f * r12) + r4, ((0.28500003f * r153) + 0.715f) + (0.14f * r12), r8 + ((-0.283f) * r12), 0.0f, 0.0f, r4 + ((-0.787f) * r12), r6 + (0.715f * r12), ((r153 * 0.928f) + 0.072f) + (r12 * 0.072f), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f}));
    }

    public static void adjustSaturationColorMatrix(ColorMatrix r9, float r10) {
        if (r9 != null) goto L4;
        return;
    L4:
        float r102 = r10 + 1.0f;
        float r2 = 1.0f - r102;
        float r3 = 0.3086f * r2;
        float r5 = 0.6094f * r2;
        float r22 = r2 * 0.082f;
        r9.postConcat(new ColorMatrix(new float[]{r3 + r102, r5, r22, 0.0f, 0.0f, r3, r5 + r102, r22, 0.0f, 0.0f, r3, r5, r102 + r22, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
    }

    public static List<View> allGlobalViews() {
    L7:
        e = move-exception;
        FileLog.e("allGlobalViews()", e);
        return null;
    L4:
        if (Build.VERSION.SDK_INT >= 29) goto L6;
        Class<?> r1 = Class.forName("android.view.WindowManagerGlobal");     // Catch: Exception -> L7
        Object r2 = r1.getMethod("getInstance", null).invoke(null, null);     // Catch: Exception -> L7
        Method r3 = r1.getMethod("getViewRootNames", null);     // Catch: Exception -> L7
        Method r12 = r1.getMethod("getRootView", new Class[]{String.class});     // Catch: Exception -> L7
        String[] r32 = (String[]) r3.invoke(r2, null);     // Catch: Exception -> L7
        ArrayList r4 = new ArrayList();     // Catch: Exception -> L7
        int r5 = r32.length;     // Catch: Exception -> L7
        int r6 = 0;
    L10:
        if (r6 >= r5) goto L13;
        r4.add((View) r12.invoke(r2, new Object[]{r32[r6]}));     // Catch: Exception -> L7
        r6 = r6 + 1;
        goto L10
    L13:
        return r4;
    L6:
        return WindowInspector.getGlobalWindowViews();
    }

    public static boolean allowScreenCapture() {
        if (SharedConfig.passcodeHash.length() != 0) goto L5;
        return true;
    L5:
        if (SharedConfig.allowScreenCapture == true) goto L11;
        return false;
    L11:
        return true;
    }

    public static int applyColorMatrix(int r8, ColorMatrix r9) {
        float[] r92 = r9.getArray();
        int r0 = Color.alpha(r8);
        int r1 = Color.red(r8);
        int r2 = Color.green(r8);
        float r12 = r1;
        float r22 = r2;
        float r82 = Color.blue(r8);
        float r02 = r0;
        float r4 = ((((r92[0] * r12) + (r92[1] * r22)) + (r92[2] * r82)) + (r92[3] * r02)) + r92[4];
        float r5 = ((((r92[5] * r12) + (r92[6] * r22)) + (r92[7] * r82)) + (r92[8] * r02)) + r92[9];
        float r6 = ((((r92[10] * r12) + (r92[11] * r22)) + (r92[12] * r82)) + (r92[13] * r02)) + r92[14];
        float r7 = ((((r92[15] * r12) + (r92[16] * r22)) + (r92[17] * r82)) + (r92[18] * r02)) + r92[19];
        return Color.argb(MathUtils.clamp(Math.round(r7), 0, 255), MathUtils.clamp(Math.round(r4), 0, 255), MathUtils.clamp(Math.round(r5), 0, 255), MathUtils.clamp(Math.round(r6), 0, 255));
    }

    public static Bitmap applyColorMatrix(Bitmap r3, ColorMatrix r4) {
        Paint r0 = new Paint();
        r0.setColorFilter(new ColorMatrixColorFilter(r4));
        r0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        Bitmap r42 = Bitmap.createBitmap(r3.getWidth(), r3.getHeight(), Bitmap.Config.ARGB_8888);
        new Canvas(r42).drawBitmap(r3, 0.0f, 0.0f, r0);
        return r42;
    }

    public static void applyEdgeToEdgeLayoutParams(WindowManager.LayoutParams r2) {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 >= 28) goto L5;
        return;
    L5:
        if (r0 < 30) goto L7;
        int r02 = 3;
    L8:
        AndroidUtilities$$ExternalSyntheticApiModelOutline1.m(r2, r02);
        return;
    L7:
        r02 = 1;
        goto L8
    }

    public static void applySpring(Animator r9, double r10, double r12) {
        applySpring(r9, r10, r12, 1.0d, 0.0d);
    }

    public static void applySpring(Animator r9, double r10, double r12, double r14) {
        applySpring(r9, r10, r12, r14, 0.0d);
    }

    public static void applySpring(Animator r16, double r17, double r19, double r21, double r23) {
        double r8 = Math.sqrt(r17 / r21);
        double r6 = r19 / (Math.sqrt(r17 * r21) * 2.0d);
        if (r6 >= 1.0d) goto L6;
        double r3 = Math.sqrt(1.0d - (r6 * r6)) * r8;
        double r10 = ((r6 * r8) + (-r23)) / r3;
    L5:
        double r12 = r3;
        double r14 = r10;
        r16.setDuration((long) ((Math.log(0.0025d) / ((-r6) * r8)) * 1000.0d));
        r16.setInterpolator(new AnonymousClass15(r6, r8, 1.0d, r12, r14));
        return;
    L6:
        r10 = (-r23) + r8;
        r3 = 0.0d;
        goto L5
    }

    public static void applySpring(Animator r6, float r7, float r8, float r9, long r10) {
        double r0 = r8 / (Math.sqrt(r7 * r9) * 2.0d);
        double r72 = Math.sqrt(r7 / r9);
        r6.setDuration(r10);
        r6.setInterpolator(new AnonymousClass16(r0, r72));
    }

    public static Typeface bold() {
        if (mediumTypeface != null) goto L11;
        if (SharedConfig.useSystemBoldFont == true) goto L7;
    L9:
        mediumTypeface = getTypeface(TYPEFACE_ROBOTO_MEDIUM);
        goto L11
    L7:
        if (Build.VERSION.SDK_INT < 28) goto L9;
        mediumTypeface = FontUtils.getSystemTypeface(TYPEFACE_ROBOTO_MEDIUM);
    L11:
        return mediumTypeface;
    }

    public static byte[] calcAuthKeyHash(byte[] r3) {
        byte[] r1 = new byte[16];
        System.arraycopy(Utilities.computeSHA1(r3), 0, r1, 0, 16);
        return r1;
    }

    public static int calcBitmapColor(Bitmap r3) {
        if (r3 != null) goto L17;
        return 0;
    L17:
        Bitmap r1 = Bitmaps.createScaledBitmap(r3, 1, 1, true);     // Catch: Exception -> L12
        if (r1 == null) goto L16;
        int r2 = r1.getPixel(0, 0);     // Catch: Exception -> L12
        if (r3 == r1) goto L14;
        r1.recycle();     // Catch: Exception -> L12
        return r2;
    L14:
        return r2;
    L16:
        return 0;
    L12:
        e = move-exception;
        FileLog.e(e);
        goto L16
    }

    public static int[] calcDrawableColor(Drawable r19) {
        if ((r19 instanceof ChatBackgroundDrawable) == true) goto L5;
        int[] r1 = new int[4];
        int r6 = -16777216;
    L10:
        e = move-exception;
        FileLog.e(e);
    L30:
        double[] r0 = rgbToHsv((r6 >> 16) & 255, (r6 >> 8) & 255, r6 & 255);
        double r62 = r0[1];
        r0[1] = Math.min(1.0d, (0.05d + r62) + ((1.0d - r62) * 0.1d));
        double r14 = Math.max(0.0d, r0[2] * 0.65d);
        int[] r63 = hsvToRgb(r0[0], r0[1], r14);
        r1[0] = Color.argb(102, r63[0], r63[1], r63[2]);
        r1[1] = Color.argb(136, r63[0], r63[1], r63[2]);
        double r17 = Math.max(0.0d, r0[2] * 0.72d);
        int[] r02 = hsvToRgb(r0[0], r0[1], r17);
        r1[2] = Color.argb(102, r02[0], r02[1], r02[2]);
        r1[3] = Color.argb(136, r02[0], r02[1], r02[2]);
        return r1;
    L8:
        if ((r19 instanceof BitmapDrawable) == false) goto L13;
        r6 = calcBitmapColor(((BitmapDrawable) r19).getBitmap());     // Catch: Exception -> L10
        goto L30
    L13:
        if ((r19 instanceof ColorDrawable) == false) goto L16;
        r6 = ((ColorDrawable) r19).getColor();     // Catch: Exception -> L10
        goto L30
    L16:
        if ((r19 instanceof BackgroundGradientDrawable) == false) goto L26;
        int[] r03 = ((BackgroundGradientDrawable) r19).getColorsList();     // Catch: Exception -> L10
        if (r03 == null) goto L30;
        if (r03.length <= 1) goto L23;
        r6 = getAverageColor(r03[0], r03[1]);     // Catch: Exception -> L10
        goto L30
    L23:
        if (r03.length <= 0) goto L30;
        r6 = r03[0];     // Catch: Exception -> L10
        goto L30
    L26:
        if ((r19 instanceof MotionBackgroundDrawable) == false) goto L30;
        int r04 = Color.argb(45, 0, 0, 0);     // Catch: Exception -> L10
        r1[2] = r04;     // Catch: Exception -> L10
        r1[0] = r04;     // Catch: Exception -> L10
        int r05 = Color.argb(61, 0, 0, 0);     // Catch: Exception -> L10
        r1[3] = r05;     // Catch: Exception -> L10
        r1[1] = r05;     // Catch: Exception -> L10
        return r1;
    L5:
        return calcDrawableColor(((ChatBackgroundDrawable) r19).getDrawable(true));
    }

    public static void cancelRunOnUIThread(Runnable r1) {
        if (ApplicationLoader.applicationHandler != null) goto L5;
        return;
    L5:
        ApplicationLoader.applicationHandler.removeCallbacks(r1);
    }

    public static float cascade(float r3, float r4, float r5, float r6) {
        if (r5 > 0.0f) goto L5;
        return r3;
    L5:
        float r2 = (1.0f / r5) * Math.min(r6, r5);
        return MathUtils.clamp((r3 - ((r4 / r5) * (1.0f - r2))) / r2, 0.0f, 1.0f);
    }

    private static void changeSetSystemUiVisibility(View r1, int r2, boolean r3) {
        int r0 = r1.getSystemUiVisibility();
        int r22 = BitwiseUtils.setFlag(r0, r2, r3);
        if (r0 == r22) goto L6;
        r1.setSystemUiVisibility(r22);
        return;
    }

    public static boolean charSequenceContains(CharSequence r0, CharSequence r1) {
        if (charSequenceIndexOf(r0, r1) == (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    public static int charSequenceIndexOf(CharSequence r1, CharSequence r2) {
        return charSequenceIndexOf(r1, r2, 0);
    }

    public static int charSequenceIndexOf(CharSequence r4, CharSequence r5, int r6) {
        if (r5 != null) goto L5;
    L17:
        return -1;
    L5:
        if (r5.length() <= 0) goto L17;
    L8:
        if (r6 > (r4.length() - r5.length())) goto L17;
        int r1 = 0;
    L11:
        if (r1 >= r5.length()) goto L16;
        if (r5.charAt(r1) != r4.charAt(r6 + r1)) goto L14;
        r1 = r1 + 1;
        goto L11
    L14:
        r6 = r6 + 1;
        goto L8
    L16:
        return r6;
    }

    public static void checkAndroidTheme(Context r1, boolean r2) {
        if (r1 != null) goto L5;
        return;
    L5:
        if (Theme.isCurrentThemeDark() == false) goto L8;
        if (r2 == false) goto L8;
        int r22 = R.style.Theme_TMessages_Dark;
    L9:
        r1.setTheme(r22);
        return;
    L8:
        r22 = R.style.Theme_TMessages;
        goto L9
    }

    public static void checkDisplaySize(Context r6, Configuration r7) {
        float r1 = density;     // Catch: Exception -> L52
        density = r6.getResources().getDisplayMetrics().density;     // Catch: Exception -> L52
        if (firstConfigurationWas == true) goto L6;
    L8:
        firstConfigurationWas = true;     // Catch: Exception -> L52
        if (r7 != null) goto L11;
        r7 = r6.getResources().getConfiguration();     // Catch: Exception -> L52
    L11:
        int r3 = 0;
        if (r7.keyboard != 1) goto L14;
    L16:
        boolean r2 = false;
    L17:
        usingHardwareInput = r2;     // Catch: Exception -> L52
        WindowManager r22 = (WindowManager) r6.getSystemService("window");     // Catch: Exception -> L52
        if (r22 == null) goto L31;
        Display r23 = r22.getDefaultDisplay();     // Catch: Exception -> L52
        if (r23 == null) goto L31;
        r23.getMetrics(displayMetrics);     // Catch: Exception -> L52
        r23.getSize(displaySize);     // Catch: Exception -> L52
        float r4 = r23.getRefreshRate();     // Catch: Exception -> L52
        screenRefreshRate = r4;     // Catch: Exception -> L52
        screenMaxRefreshRate = r4;     // Catch: Exception -> L52
        float[] r24 = r23.getSupportedRefreshRates();     // Catch: Exception -> L52
        if (r24 != null) goto L24;
    L29:
        screenRefreshTime = 1000.0f / screenRefreshRate;     // Catch: Exception -> L52
    L24:
        if (r3 >= r24.length) goto L29;
        float r42 = r24[r3];     // Catch: Exception -> L52
        if (r42 <= screenMaxRefreshRate) goto L28;
        screenMaxRefreshRate = r42;     // Catch: Exception -> L52
    L28:
        r3 = r3 + 1;     // Catch: Exception -> L52
    L31:
        if (r7.screenWidthDp == 0) goto L36;
        int r25 = (int) Math.ceil(r2 * density);     // Catch: Exception -> L52
        if (Math.abs(displaySize.x - r25) <= 3) goto L36;
        displaySize.x = r25;     // Catch: Exception -> L52
    L36:
        if (r7.screenHeightDp == 0) goto L41;
        int r26 = (int) Math.ceil(r2 * density);     // Catch: Exception -> L52
        if (Math.abs(displaySize.y - r26) <= 3) goto L41;
        displaySize.y = r26;     // Catch: Exception -> L52
    L41:
        if (roundMessageSize == 0) goto L43;
    L47:
        fillStatusBarHeight(r6, true);     // Catch: Exception -> L52
        if (BuildVars.LOGS_ENABLED == false) goto L50;
        FileLog.e("density = " + density + " display size = " + displaySize.x + " " + displaySize.y + " " + displayMetrics.xdpi + "x" + displayMetrics.ydpi + ", screen layout: " + r7.screenLayout + ", statusbar height: " + statusBarHeight + ", navbar height: " + navigationBarHeight);     // Catch: Exception -> L52
    L50:
        touchSlop = ViewConfiguration.get(r6).getScaledTouchSlop();     // Catch: Exception -> L52
        isSmallScreen = null;     // Catch: Exception -> L52
        return;
    L43:
        if (isTablet() == false) goto L45;
        roundMessageSize = (int) (getMinTabletSide() * 0.6f);     // Catch: Exception -> L52
        roundPlayingMessageSize = getMinTabletSide() - dp(28.0f);     // Catch: Exception -> L52
        roundSidePlayingMessageSize = getMinTabletSide() - dp(92.0f);     // Catch: Exception -> L52
    L46:
        roundMessageInset = dp(2.0f);     // Catch: Exception -> L52
        goto L47
    L45:
        Point r27 = displaySize;     // Catch: Exception -> L52
        roundMessageSize = (int) (Math.min(r27.x, r27.y) * 0.6f);     // Catch: Exception -> L52
        Point r28 = displaySize;     // Catch: Exception -> L52
        roundPlayingMessageSize = Math.min(r28.x, r28.y) - dp(28.0f);     // Catch: Exception -> L52
        roundSidePlayingMessageSize = Math.min(displaySize.x - dp(64.0f), displaySize.y) - dp(28.0f);     // Catch: Exception -> L52
        goto L46
    L14:
        if (r7.hardKeyboardHidden != 1) goto L16;
        r2 = true;
        goto L17
    L6:
        if (Math.abs(r1 - r2) <= 0.001d) goto L8;
        Theme.reloadAllResources(r6);     // Catch: Exception -> L52
    L52:
        e = move-exception;
        FileLog.e(e);
    }

    public static boolean checkHostForPunycode(String r8) {
        if (r8 != null) goto L46;
        return false;
    L46:
        int r2 = r8.length();     // Catch: Exception -> L39
        int r3 = 0;
        boolean r4 = false;
        boolean r5 = false;
    L8:
        if (r3 >= r2) goto L42;
        char r6 = r8.charAt(r3);     // Catch: Exception -> L37
        if (r6 == '.') goto L36;
        if (r6 == '-') goto L36;
        if (r6 == '/') goto L36;
        if (r6 == '+') goto L36;
        if (r6 < '0') goto L24;
        if (r6 <= '9') goto L36;
    L24:
        if (r6 < 'a') goto L28;
        if (r6 > 'z') goto L28;
    L31:
        r4 = true;
    L33:
        if (r4 == false) goto L36;
        if (r5 == false) goto L36;
    L28:
        if (r6 >= 'A') goto L30;
    L32:
        r5 = true;
        goto L33
    L30:
        if (r6 > 'Z') goto L32;
    L36:
        r3 = r3 + 1;
    L37:
        e = e;
    L41:
        FileLog.e(e);
    L42:
        if (r4 == false) goto L59;
        if (r5 == true) goto L45;
        return false;
    L45:
        return true;
    L59:
        return false;
    L39:
        e = e;
        r4 = false;
        r5 = false;
        goto L41
    }

    public static boolean checkInlinePermissions(Context r0) {
        return Settings.canDrawOverlays(r0);
    }

    public static boolean checkPhonePattern(String r7, String r8) {
        if (TextUtils.isEmpty(r7) == false) goto L5;
    L16:
        return true;
    L5:
        if (r7.equals("*") == true) goto L16;
        String[] r72 = r7.split("\\*");
        String r82 = PhoneFormat.stripExceptNumbers(r8);
        int r0 = r72.length;
        int r3 = 0;
        int r4 = 0;
    L8:
        if (r3 >= r0) goto L16;
        String r5 = r72[r3];
        if (TextUtils.isEmpty(r5) == true) goto L15;
        int r42 = r82.indexOf(r5, r4);
        if (r42 == (-1)) goto L13;
        r4 = r42 + r5.length();
        goto L15
    L13:
        return false;
    L15:
        r3 = r3 + 1;
        goto L8
    }

    public static boolean checkPipPermissions(Context r4) {
        if (r4.getPackageManager().hasSystemFeature("android.software.picture_in_picture") == true) goto L6;
        return false;
    L6:
        if (((AppOpsManager) r4.getSystemService("appops")).checkOpNoThrow("android:picture_in_picture", Process.myUid(), r4.getPackageName()) != 0) goto L9;
        return true;
    L9:
        return false;
    }

    public static float cleanValue(float r1, float r2) {
        return Math.min(r2, Math.max(-r2, r1));
    }

    public static void clearDrawableAnimation(View r1) {
        if (r1 != null) goto L5;
        return;
    L5:
        if ((r1 instanceof ListView) == false) goto L10;
        Drawable r12 = ((ListView) r1).getSelector();
        if (r12 == null) goto L14;
        r12.setState(StateSet.NOTHING);
        return;
    L14:
        return;
    L10:
        Drawable r13 = r1.getBackground();
        if (r13 == null) goto L15;
        r13.setState(StateSet.NOTHING);
        r13.jumpToCurrentState();
        return;
    }

    public static void clearTypefaceCache() {
        ConcurrentHashMap<String, Typeface> r0 = typefaceCache;
        monitor-enter(r0);
        r0.clear();     // Catch: Throwable -> L7
        mediumTypeface = null;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public static ByteBuffer cloneByteBuffer(ByteBuffer r3) {
        if (r3 != null) goto L8;
        return null;
    L8:
        ByteBuffer r1 = ByteBuffer.allocate(r3.capacity());     // Catch: OutOfMemoryError -> L7
        int r2 = r3.position();     // Catch: OutOfMemoryError -> L7
        r3.rewind();     // Catch: OutOfMemoryError -> L7
        r1.put(r3);     // Catch: OutOfMemoryError -> L7
        r3.rewind();     // Catch: OutOfMemoryError -> L7
        r1.flip();     // Catch: OutOfMemoryError -> L7
        r1.position(r2);     // Catch: OutOfMemoryError -> L7
        return r1;
    L7:
        return null;
    }

    public static int compare(int r0, int r1) {
        if (r0 != r1) goto L5;
        return 0;
    L5:
        if (r0 <= r1) goto L8;
        return 1;
    L8:
        return -1;
    }

    public static int compare(long r0, long r2) {
        if (r0 != r2) goto L6;
        return 0;
    L6:
        if (r0 <= r2) goto L9;
        return 1;
    L9:
        return -1;
    }

    public static float computeDampingRatio(float r2, float r3, float r4) {
        return r3 / (((float) Math.sqrt(r4 * r2)) * 2.0f);
    }

    public static float computePerceivedBrightness(int r3) {
        return (((Color.red(r3) * 0.2126f) + (Color.green(r3) * 0.7152f)) + (Color.blue(r3) * 0.0722f)) / 255.0f;
    }

    public static CharSequence concat(CharSequence... r4) {
        if (r4.length != 0) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        int r1 = 0;
        if (r4.length == 1) goto L9;
        int r0 = r4.length;
        int r2 = 0;
    L11:
        if (r2 >= r0) goto L23;
        if ((r4[r2] instanceof Spanned) == true) goto L14;
        r2 = r2 + 1;
        goto L11
    L14:
        SpannableStringBuilder r02 = new SpannableStringBuilder();
        int r22 = r4.length;
    L15:
        if (r1 >= r22) goto L21;
        CharSequence r3 = r4[r1];
        if (r3 != null) goto L19;
        r3 = "null";
    L19:
        r02.append(r3);
        r1 = r1 + 1;
        goto L15
    L21:
        return new SpannedString(r02);
    L23:
        StringBuilder r03 = new StringBuilder();
        int r23 = r4.length;
    L24:
        if (r1 >= r23) goto L27;
        r03.append(r4[r1]);
        r1 = r1 + 1;
        goto L24
    L27:
        return r03.toString();
    L9:
        return r4[0];
    }

    private static boolean containsUnsupportedCharacters(String r2) {
        if (r2.contains("\u202c") == false) goto L6;
        return true;
    L6:
        if (r2.contains("\u202d") == false) goto L9;
        return true;
    L9:
        if (r2.contains("\u202e") == false) goto L17;
        return true;
    L17:
        if (BAD_CHARS_PATTERN.matcher(r2).find() == false) goto L14;
        return true;
    L14:
        return false;
    L16:
        return true;
    }

    public static boolean copyFile(File r9, File r10) {
        if (r9.equals(r10) == false) goto L6;
        return true;
    L6:
        if (r10.exists() == true) goto L35;
        r10.createNewFile();
    L35:
        FileInputStream r2 = new FileInputStream(r9);     // Catch: Exception -> L30
        FileOutputStream r92 = new FileOutputStream(r10);     // Catch: Throwable -> L14
        r92.getChannel().transferFrom(r2.getChannel(), 0, r2.getChannel().size());     // Catch: Throwable -> L16
        r92.close();     // Catch: Throwable -> L14
        r2.close();     // Catch: Exception -> L30
        return true;
    L16:
        th = move-exception;
        r92.close();     // Catch: Throwable -> L20
    L41:
        throw th;     // Catch: Throwable -> L14
    L20:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L14
        throw th;     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        r2.close();     // Catch: Throwable -> L26
    L42:
        throw th;     // Catch: Exception -> L30
    L26:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L30
        throw th;     // Catch: Exception -> L30
    L30:
        e = move-exception;
        FileLog.e(e);
        return false;
    }

    public static boolean copyFile(InputStream r1, File r2) {
        return copyFile(r1, new FileOutputStream(r2));
    }

    public static boolean copyFile(InputStream r3, OutputStream r4) {
        byte[] r0 = new byte[4096];
    L3:
        int r1 = r3.read(r0);
        if (r1 <= 0) goto L6;
        Thread.yield();
        r4.write(r0, 0, r1);
        goto L3
    L6:
        r4.close();
        return true;
    }

    public static boolean copyFileSafe(File r0, File r1) {
        return copyFile(r0, r1);
    L4:
        e = move-exception;
        FileLog.e(e);
        return false;
    }

    public static void createCalendarEvent(Activity r4, long r5, String r7, String r8, boolean r9) {
        if (r9 == false) goto L5;
        Calendar r0 = Calendar.getInstance();
        r0.setTimeInMillis(r5);
        r0.set(11, 0);
        r0.set(12, 0);
        r0.set(13, 0);
        r0.set(14, 0);
        r5 = r0.getTimeInMillis();
        long r02 = DurationKt.MILLIS_IN_DAY;
    L4:
        long r03 = r02 + r5;
        Intent r52 = new Intent("android.intent.action.INSERT").setData(CalendarContract.Events.CONTENT_URI).putExtra("beginTime", r5).putExtra("endTime", r03).putExtra("allDay", r9);
        if (TextUtils.isEmpty(r7) == true) goto L10;
        r52.putExtra("title", r7);
    L10:
        if (TextUtils.isEmpty(r8) == true) goto L17;
        r52.putExtra(MediaTrack.ROLE_DESCRIPTION, r8);
    L17:
        r4.startActivity(r52);     // Catch: Throwable -> L14
        return;
    L14:
        th = move-exception;
        FileLog.e(th);
        return;
    L5:
        r02 = 600000;
        goto L4
    }

    public static void createEmptyFile(File r1) {
    L7:
        th = move-exception;
        FileLog.e(th);
        return;
    L3:
        if (r1.exists() == false) goto L5;
        return;
    L5:
        FileWriter r0 = new FileWriter(r1);     // Catch: Throwable -> L7
        r0.flush();     // Catch: Throwable -> L7
        r0.close();     // Catch: Throwable -> L7
    }

    public static byte[] decodeQuotedPrintable(byte[] r6) {
        if (r6 != null) goto L5;
        return null;
    L5:
        ByteArrayOutputStream r1 = new ByteArrayOutputStream();
        int r2 = 0;
    L7:
        if (r2 >= r6.length) goto L18;
        byte r3 = r6[r2];
        if (r3 == 61) goto L24;
        r1.write(r3);
    L17:
        r2 = r2 + 1;
        goto L7
    L24:
        int r32 = Character.digit((char) r6[r2 + 1], 16);     // Catch: Exception -> L13
        r2 = r2 + 2;     // Catch: Exception -> L13
        int r33 = r32 << 4;     // Catch: Exception -> L13
        r1.write((char) (r33 + Character.digit((char) r6[r2], 16)));     // Catch: Exception -> L13
    L13:
        e = move-exception;
        FileLog.e(e);
        return null;
    L18:
        byte[] r62 = r1.toByteArray();
        r1.close();     // Catch: Exception -> L21
        return r62;
    L21:
        e = move-exception;
        FileLog.e(e);
        return r62;
    }

    public static String defaultUrlScheme(CharSequence r1) {
        if (r1 != null) goto L4;
        return "https://";
    L4:
        if (IPV4_URL.matcher(r1).lookingAt() == false) goto L9;
        return "http://";
    L9:
        return "https://";
    }

    public static float distance(float r0, float r1, float r2, float r3) {
        float r22 = r2 - r0;
        float r32 = r3 - r1;
        return (float) Math.sqrt((r22 * r22) + (r32 * r32));
    }

    public static float distanceInfluenceForSnapDuration(float r2) {
        return (float) Math.sin((r2 - 0.5f) * 0.47123894f);
    }

    public static void doOnLayout(View r1, Runnable r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r1 != null) goto L7;
        r2.run();
        return;
    L7:
        r1.addOnLayoutChangeListener(new AnonymousClass10(r1, r2));
    }

    public static void doOnPreDraw(View r3, Runnable r4) {
        ViewTreeObserver r32 = r3.getViewTreeObserver();
        AndroidUtilities$$ExternalSyntheticLambda5 r2 = new AndroidUtilities$$ExternalSyntheticLambda5(r32, new ViewTreeObserver.OnPreDrawListener[]{r2}, new boolean[1], r4);
        r32.addOnPreDrawListener(r2);
    }

    public static boolean doSafe(Utilities.Callback0Return<Boolean> r1) {
        return doSafe(r1, 200);
    }

    public static boolean doSafe(Utilities.Callback0Return<Boolean> r2, int r3) {
        AndroidUtilities$$ExternalSyntheticLambda19 r0 = new AndroidUtilities$$ExternalSyntheticLambda19(r2);
        Future r22 = null;
        return ((Boolean) LINK_EXECUTOR.submit(r0).get(r3, TimeUnit.MILLISECONDS)).booleanValue();
    L7:
        if (0 == 0) goto L14;
        r22.cancel(true);
        return false;
    L14:
        return false;
    L5:
        e = move-exception;
        FileLog.e(e);
        return false;
    }

    public static int dp(float r2) {
        if (r2 != 0.0f) goto L7;
        return 0;
    L7:
        return (int) Math.ceil(density * r2);
    }

    public static int dp2(float r2) {
        if (r2 != 0.0f) goto L7;
        return 0;
    L7:
        return (int) Math.floor(density * r2);
    }

    public static float dpf2(float r2) {
        if (r2 != 0.0f) goto L6;
        return 0.0f;
    L6:
        return density * r2;
    }

    public static int dpr(float r1) {
        if (r1 != 0.0f) goto L7;
        return 0;
    L7:
        return Math.round(density * r1);
    }

    public static void drawNavigationBarProtection(Canvas r1, View r2, int r3, int r4) {
        drawNavigationBarProtection(r1, r2, r3, r4, 1.0f);
    }

    public static void drawNavigationBarProtection(Canvas r6, View r7, int r8, int r9, float r10) {
        Paint r5 = navbarProtactionPaint;
        r5.setColor(Theme.multAlpha(r8, r10 * getNavigationBarThirdButtonsFactor(0.0f, 0.75f, r9)));
        r6.drawRect(0.0f, (r7.getY() + r7.getMeasuredHeight()) - r9, r7.getMeasuredWidth(), r7.getY() + r7.getMeasuredHeight(), r5);
    }

    public static void drawStroke(Canvas r1, RectF r2, float r3) {
        drawStroke(r1, r2, r3, 1.0f);
    }

    public static void drawStroke(Canvas r15, RectF r16, float r17, float r18) {
        if (r18 > 0.0f) goto L5;
        return;
    L5:
        float r12 = Math.min(r17, Math.min(r16.width(), r16.height()) / 2.0f);
        float r1 = dpf2(1.0f);
        float r2 = r1 / 2.0f;
        float r5 = Math.max(0.0f, r12 - r1);
        Paint r7 = strokeTop;
        Paint.Style r13 = Paint.Style.STROKE;
        r7.setStyle(r13);
        r7.setStrokeWidth(r1);
        r7.setColor(Theme.multAlpha(570425343, r18));
        r15.save();
        float r14 = r16.left - r2;
        float r3 = r16.top;
        if (r15.clipRect(r14, r3, r16.right + r2, MathUtils.clamp(r3 + r5, r3, r16.bottom)) == false) goto L8;
        r15.drawRoundRect(r16.left, r16.top + r2, r16.right, r2 + r16.bottom, r5, r5, r7);
    L8:
        r15.restore();
        float r19 = dpf2(0.6666667f);
        float r22 = r19 / 2.0f;
        float r52 = Math.max(0.0f, r12 - r19);
        Paint r72 = strokeBottom;
        r72.setStyle(r13);
        r72.setStrokeWidth(r19);
        r72.setColor(Theme.multAlpha(385875967, r18));
        r15.save();
        float r110 = r16.left - r22;
        float r32 = r16.bottom;
        if (r15.clipRect(r110, MathUtils.clamp(r32 - r52, r16.top, r32), r16.right + r22, r16.bottom) == false) goto L11;
        r15.drawRoundRect(r16.left, r16.top - r22, r16.right, r16.bottom - r22, r52, r52, r72);
    L11:
        r15.restore();
    }

    public static void dumpCanvas(View r3) {
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L5;
        return;
    L5:
        DebugRecordingCanvas r1 = new DebugRecordingCanvas(Bitmap.createBitmap(r3.getWidth(), r3.getHeight(), Bitmap.Config.ARGB_8888));
        r3.draw(r1);
        r1.logCommands();
        LaunchActivity.instance.presentFragment(new DebugRecordingCanvasReplayFragment(r1));
    }

    public static StackTraceElement[] dumpStackTrace() {
        return Thread.currentThread().getStackTrace();
    }

    public static CharSequence ellipsizeCenterEnd(CharSequence r11, String r12, int r13, TextPaint r14, int r15) {
        int r1 = r11.length();     // Catch: Exception -> L7
        int r122 = r11.toString().toLowerCase().indexOf(r12);     // Catch: Exception -> L7
        if (r1 <= r15) goto L6;
        r11 = r11.subSequence(Math.max(0, r122 - (r15 / 2)), Math.min(r1, (r15 / 2) + r122));     // Catch: Exception -> L7
        r122 = r122 - Math.max(0, r122 - (r15 / 2));     // Catch: Exception -> L7
        r11.length();     // Catch: Exception -> L7
    L6:
        CharSequence r4 = r11;
        StaticLayout r3 = new StaticLayout(r4, r14, Integer.MAX_VALUE, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);     // Catch: Exception -> L18
        float r112 = r3.getLineWidth(0);     // Catch: Exception -> L18
        float r132 = r13;
        if ((r14.measureText("...") + r112) < r132) goto L52;
        int r123 = r122 + 1;     // Catch: Exception -> L18
        int r142 = r123;
    L14:
        if (r142 >= (r4.length() - 1)) goto L20;
        if (Character.isWhitespace(r4.charAt(r142)) == true) goto L20;
        r142 = r142 + 1;     // Catch: Exception -> L18
    L20:
        float r152 = r3.getPrimaryHorizontal(r142);     // Catch: Exception -> L18
        if (r3.isRtlCharAt(r142) == false) goto L24;
        r152 = r112 - r152;     // Catch: Exception -> L18
    L24:
        if (r152 < r132) goto L52;
        float r113 = 0.1f * r132;     // Catch: Exception -> L18
        float r153 = ((r152 - r132) + (r14.measureText("...") * 2.0f)) + r113;     // Catch: Exception -> L18
        if ((r4.length() - r142) <= 20) goto L30;
        r153 = r153 + r113;     // Catch: Exception -> L18
    L30:
        if (r153 <= 0.0f) goto L52;
        int r114 = r3.getOffsetForHorizontal(0, r153);     // Catch: Exception -> L18
        if (r114 <= (r4.length() - 1)) goto L34;
        r114 = r4.length() - 1;
    L34:
        int r143 = 0;
    L36:
        if (Character.isWhitespace(r4.charAt(r114)) == true) goto L41;
        if (r143 >= 10) goto L41;
        r143 = r143 + 1;     // Catch: Exception -> L18
        r114 = r114 + 1;     // Catch: Exception -> L18
        if (r114 <= (r4.length() - 1)) goto L36;
        r114 = r3.getOffsetForHorizontal(0, r153);     // Catch: Exception -> L18
    L41:
        if (r143 < 10) goto L43;
        CharSequence r115 = r4.subSequence(r3.getOffsetForHorizontal(0, r3.getPrimaryHorizontal(r123) - (r132 * 0.3f)), r4.length());     // Catch: Exception -> L18
    L50:
        return SpannableStringBuilder.valueOf("...").append(r115);
    L43:
        if (r114 > 0) goto L45;
    L49:
        r115 = r4.subSequence(r114, r4.length());     // Catch: Exception -> L18
        goto L50
    L45:
        if (r114 >= (r4.length() - 2)) goto L49;
        if (Character.isWhitespace(r4.charAt(r114)) == false) goto L49;
        r114 = r114 + 1;     // Catch: Exception -> L18
    L52:
        return r4;
    L18:
        e = move-exception;
        Exception r124 = e;
        r11 = r4;
    L53:
        FileLog.e(r124);
        return r11;
    L7:
        e = move-exception;
        r124 = e;
        goto L53
    }

    public static MotionEvent emptyMotionEvent() {
        return MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0);
    }

    public static void enableEdgeToEdge(Activity r2) {
        Window r22 = r2.getWindow();
        enableEdgeToEdge(r22);     // Catch: Throwable -> L7
        if (Build.VERSION.SDK_INT < 28) goto L12;
        r22.setNavigationBarDividerColor(0);     // Catch: Throwable -> L7
        return;
    L12:
        return;
    L7:
        th = move-exception;
        FileLog.e(th);
    }

    public static void enableEdgeToEdge(Window r5) {
        Objects.requireNonNull(r5);
        r5.getDecorView();
        WindowCompat.setDecorFitsSystemWindows(r5, false);
        r5.setStatusBarColor(0);
        r5.setNavigationBarColor(0);
        int r1 = Build.VERSION.SDK_INT;
        if (r1 < 28) goto L12;
        if (r1 < 30) goto L7;
        int r2 = 3;
    L8:
        WindowManager.LayoutParams r3 = r5.getAttributes();
        if (AndroidUtilities$$ExternalSyntheticApiModelOutline2.m(r3) == r2) goto L12;
        AndroidUtilities$$ExternalSyntheticApiModelOutline1.m(r3, r2);
        r5.setAttributes(r3);
        goto L12
    L7:
        r2 = 1;
    L12:
        if (r1 < 29) goto L15;
        r5.setStatusBarContrastEnforced(false);
        r5.setNavigationBarContrastEnforced(false);
        return;
    }

    public static void endIncomingCall() {
        if (hasCallPermissions == true) goto L10;
        return;
    L10:
        TelephonyManager r0 = (TelephonyManager) ApplicationLoader.applicationContext.getSystemService("phone");     // Catch: Throwable -> L7
        Method r1 = Class.forName(r0.getClass().getName()).getDeclaredMethod("getITelephony", null);     // Catch: Throwable -> L7
        r1.setAccessible(true);     // Catch: Throwable -> L7
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.invoke(r0, null));     // Catch: Throwable -> L7
        throw null;     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        FileLog.e(th);
    }

    public static String escape(String r0) {
        return removeRTL(removeDiacritics(r0));
    }

    public static void executeOnUIThread(Runnable r2) {
        if (Looper.myLooper() == Looper.getMainLooper()) goto L6;
        runOnUIThread(r2);
        return;
    L6:
        r2.run();
    }

    public static void fillStatusBarHeight(Context r0, boolean r1) {
    }

    public static <A, B> B find(List<A> r4, Class<B> r5) {
        if (r4 != null) goto L5;
        return null;
    L5:
        int r1 = 0;
    L7:
        if (r1 >= r4.size()) goto L13;
        A r2 = r4.get(r1);
        if (r5.isInstance(r2) == true) goto L11;
        r1 = r1 + 1;
        goto L7
    L11:
        return r5.cast(r2);
    L13:
        return null;
    }

    public static Activity findActivity(Context r1) {
        if ((r1 instanceof Activity) == false) goto L7;
        return (Activity) r1;
    L7:
        if ((r1 instanceof ContextWrapper) == true) goto L9;
        return null;
    L9:
        return findActivity(((ContextWrapper) r1).getBaseContext());
    }

    public static View findChildViewUnder(ViewGroup r5, float r6, float r7) {
        if (r5 != null) goto L6;
        return null;
    L6:
        if (r5.getVisibility() == 0) goto L8;
        return null;
    L8:
        int r1 = 0;
    L10:
        if (r1 >= r5.getChildCount()) goto L32;
        View r2 = r5.getChildAt(r1);
        if (r2 == null) goto L31;
        if (r2.getVisibility() != 0) goto L31;
        if ((r2 instanceof ViewGroup) == false) goto L23;
        View r22 = findChildViewUnder((ViewGroup) r2, r6 - r2.getLeft(), r7 - r2.getTop());
        if (r22 == null) goto L31;
        return r22;
    L23:
        if (r6 < r2.getX()) goto L31;
        if (r6 > (r2.getX() + r2.getWidth())) goto L31;
        if (r7 < r2.getY()) goto L31;
        if (r6 > (r2.getY() + r2.getHeight())) goto L31;
        return r2;
    L31:
        r1 = r1 + 1;
        goto L10
    L32:
        return null;
    }

    public static boolean findClickableView(ViewGroup r1, float r2, float r3) {
        return findClickableView(r1, r2, r3, null);
    }

    public static boolean findClickableView(ViewGroup r6, float r7, float r8, View r9) {
        if (r6 != null) goto L5;
        return false;
    L5:
        int r1 = 0;
    L7:
        if (r1 >= r6.getChildCount()) goto L36;
        View r2 = r6.getChildAt(r1);
        if (r2.getVisibility() != 0) goto L35;
        if ((r2 instanceof PeerStoriesView) == false) goto L15;
        if (r2 != r9) goto L35;
    L15:
        if ((r2 instanceof StoryMediaAreasView.AreaView) == false) goto L25;
        StoryMediaAreasView r3 = (StoryMediaAreasView) r6;
        if (r3.hasSelected() == false) goto L23;
        if (r7 < dp(60.0f)) goto L25;
        if (r7 > (r6.getWidth() - dp(60.0f))) goto L25;
    L23:
        if (r3.hasAreaAboveAt(r7, r8) == false) goto L35;
    L25:
        Rect r32 = rectTmp2;
        r2.getHitRect(r32);
        if (r32.contains((int) r7, (int) r8) == false) goto L31;
        if (r2.isClickable() == false) goto L31;
        return true;
    L31:
        if ((r2 instanceof ViewGroup) == false) goto L35;
        if (findClickableView((ViewGroup) r2, r7 - r2.getX(), r8 - r2.getY(), r9) == false) goto L35;
        return true;
    L35:
        r1 = r1 + 1;
        goto L7
    L36:
        return false;
    }

    public static TLRPC.Document findDocument(List<TLRPC.Document> r5, long r6) {
        if (r5 != null) goto L5;
        return null;
    L5:
        int r1 = 0;
    L7:
        if (r1 >= r5.size()) goto L14;
        TLRPC.Document r2 = r5.get(r1);
        if (r2 == null) goto L13;
        if (r2.id != r6) goto L13;
        return r2;
    L13:
        r1 = r1 + 1;
        goto L7
    L14:
        return null;
    }

    public static <A, B> B findLast(List<A> r4, Class<B> r5) {
        if (r4 != null) goto L5;
        return null;
    L5:
        int r1 = r4.size() - 1;
    L6:
        if (r1 < 0) goto L12;
        A r2 = r4.get(r1);
        if (r5.isInstance(r2) == true) goto L10;
        r1 = r1 - 1;
        goto L6
    L10:
        return r5.cast(r2);
    L12:
        return null;
    }

    public static TLRPC.Photo findPhoto(List<TLRPC.Photo> r5, long r6) {
        if (r5 != null) goto L5;
        return null;
    L5:
        int r1 = 0;
    L7:
        if (r1 >= r5.size()) goto L14;
        TLRPC.Photo r2 = r5.get(r1);
        if (r2 == null) goto L13;
        if (r2.id != r6) goto L13;
        return r2;
    L13:
        r1 = r1 + 1;
        goto L7
    L14:
        return null;
    }

    public static void fixGoogleMapsBug() {
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("google_bug_154855417", 0);
        if (r0.contains("fixed") == true) goto L6;
        new File(ApplicationLoader.getFilesDirFixed(), "ZoomTables.data").delete();
        r0.edit().putBoolean("fixed", true).apply();
        return;
    }

    public static double fixLocationCoord(double r2) {
        return ((long) (r2 * 1000000.0d)) / 1000000.0d;
    }

    public static WindowInsets fixedDispatchApplyWindowInsets(WindowInsets r3, ViewGroup r4) {
        int r0 = r4.getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r4.getChildAt(r1).dispatchApplyWindowInsets(r3);
        r1 = r1 + 1;
        goto L3
    L5:
        return r3;
    }

    public static void forEachViews(View r3, Utilities.Callback<View> r4) {
        if ((r3 instanceof ViewGroup) == false) goto L9;
        ViewGroup r0 = (ViewGroup) r3;
        int r1 = 0;
    L6:
        if (r1 >= r0.getChildCount()) goto L8;
        r4.run(r3);
        forEachViews(r0.getChildAt(r1), r4);
        r1 = r1 + 1;
        goto L6
    L8:
        return;
    L9:
        r4.run(r3);
    }

    public static void forEachViews(RecyclerView r3, Utilities.Callback<View> r4) {
        if (r3 == null) goto L19;
        int r0 = 0;
        int r1 = 0;
    L6:
        if (r1 >= r3.getChildCount()) goto L8;
        r4.run(r3.getChildAt(r1));
        r1 = r1 + 1;
        goto L6
    L8:
        int r12 = 0;
    L10:
        if (r12 >= r3.getCachedChildCount()) goto L12;
        r4.run(r3.getCachedChildAt(r12));
        r12 = r12 + 1;
        goto L10
    L12:
        int r13 = 0;
    L14:
        if (r13 >= r3.getHiddenChildCount()) goto L17;
        r4.run(r3.getHiddenChildAt(r13));
        r13 = r13 + 1;
    L17:
        if (r0 >= r3.getAttachedScrapChildCount()) goto L24;
        r4.run(r3.getAttachedScrapChildAt(r0));
        r0 = r0 + 1;
        goto L17
    L24:
        return;
    }

    public static String formapMapUrl(int r8, double r9, double r11, int r13, int r14, boolean r15, int r16, int r17) {
        int r0 = Math.min(2, (int) Math.ceil(density));
        if (r17 != (-1)) goto L5;
        int r2 = MessagesController.getInstance(r8).mapProvider;
    L7:
        if (r2 != 1) goto L9;
    L23:
        String[] r82 = {"ru_RU", "tr_TR"};
        LocaleController.LocaleInfo r22 = LocaleController.getInstance().getCurrentLocaleInfo();
        String r3 = null;
        int r4 = 0;
    L24:
        if (r4 >= 2) goto L29;
        String r5 = r82[r4];
        if (r5.toLowerCase().contains(r22.shortName) == false) goto L28;
        r3 = r5;
    L28:
        r4 = r4 + 1;
        goto L24
    L29:
        if (r3 != null) goto L31;
        r3 = "en_US";
    L31:
        if (r15 == false) goto L35;
        return String.format(Locale.US, "https://static-maps.yandex.ru/1.x/?ll=%.6f,%.6f&z=%d&size=%d,%d&l=map&scale=%d&pt=%.6f,%.6f,vkbkm&lang=%s", new Object[]{Double.valueOf(r11), Double.valueOf(r9), Integer.valueOf(r16), Integer.valueOf(r13 * r0), Integer.valueOf(r14 * r0), Integer.valueOf(r0), Double.valueOf(r11), Double.valueOf(r9), r3});
    L35:
        return String.format(Locale.US, "https://static-maps.yandex.ru/1.x/?ll=%.6f,%.6f&z=%d&size=%d,%d&l=map&scale=%d&lang=%s", new Object[]{Double.valueOf(r11), Double.valueOf(r9), Integer.valueOf(r16), Integer.valueOf(r13 * r0), Integer.valueOf(r14 * r0), Integer.valueOf(r0), r3});
    L9:
        if (r2 == 3) goto L23;
        String r83 = MessagesController.getInstance(r8).mapKey;
        if (TextUtils.isEmpty(r83) == true) goto L18;
        if (r15 == false) goto L17;
        return String.format(Locale.US, "https://maps.googleapis.com/maps/api/staticmap?center=%.6f,%.6f&zoom=%d&size=%dx%d&maptype=roadmap&scale=%d&markers=color:red%%7Csize:mid%%7C%.6f,%.6f&sensor=false&key=%s", new Object[]{Double.valueOf(r9), Double.valueOf(r11), Integer.valueOf(r16), Integer.valueOf(r13), Integer.valueOf(r14), Integer.valueOf(r0), Double.valueOf(r9), Double.valueOf(r11), r83});
    L17:
        return String.format(Locale.US, "https://maps.googleapis.com/maps/api/staticmap?center=%.6f,%.6f&zoom=%d&size=%dx%d&maptype=roadmap&scale=%d&key=%s", new Object[]{Double.valueOf(r9), Double.valueOf(r11), Integer.valueOf(r16), Integer.valueOf(r13), Integer.valueOf(r14), Integer.valueOf(r0), r83});
    L18:
        if (r15 == false) goto L22;
        return String.format(Locale.US, "https://maps.googleapis.com/maps/api/staticmap?center=%.6f,%.6f&zoom=%d&size=%dx%d&maptype=roadmap&scale=%d&markers=color:red%%7Csize:mid%%7C%.6f,%.6f&sensor=false", new Object[]{Double.valueOf(r9), Double.valueOf(r11), Integer.valueOf(r16), Integer.valueOf(r13), Integer.valueOf(r14), Integer.valueOf(r0), Double.valueOf(r9), Double.valueOf(r11)});
    L22:
        return String.format(Locale.US, "https://maps.googleapis.com/maps/api/staticmap?center=%.6f,%.6f&zoom=%d&size=%dx%d&maptype=roadmap&scale=%d", new Object[]{Double.valueOf(r9), Double.valueOf(r11), Integer.valueOf(r16), Integer.valueOf(r13), Integer.valueOf(r14), Integer.valueOf(r0)});
    L5:
        r2 = r17;
        goto L7
    }

    public static String formatCount(int r4) {
        if (r4 < 1000) goto L5;
        ArrayList r0 = new ArrayList();
    L7:
        if (r4 == 0) goto L12;
        int r1 = r4 % 1000;
        r4 = r4 / 1000;
        if (r4 > 0) goto L10;
        r0.add(Integer.toString(r1));
        goto L7
    L10:
        r0.add(String.format(Locale.ENGLISH, "%03d", new Object[]{Integer.valueOf(r1)}));
        goto L7
    L12:
        StringBuilder r42 = new StringBuilder();
        int r12 = r0.size() - 1;
    L13:
        if (r12 < 0) goto L19;
        r42.append((String) r0.get(r12));
        if (r12 == 0) goto L17;
        r42.append(",");
    L17:
        r12 = r12 - 1;
        goto L13
    L19:
        return r42.toString();
    L5:
        return Integer.toString(r4);
    }

    public static String formatDuration(int r11, int r12, boolean r13) {
        int r0 = r12 / 3600;
        int r1 = (r12 / 60) % 60;
        int r2 = r12 % 60;
        int r3 = r11 / 3600;
        int r4 = (r11 / 60) % 60;
        int r112 = r11 % 60;
        if (r12 != 0) goto L12;
        if (r3 != 0) goto L11;
        if (r13 == false) goto L9;
        return String.format(Locale.US, "%02d:%02d / -:--", new Object[]{Integer.valueOf(r4), Integer.valueOf(r112)});
    L9:
        return String.format(Locale.US, "%d:%02d / -:--", new Object[]{Integer.valueOf(r4), Integer.valueOf(r112)});
    L11:
        return String.format(Locale.US, "%d:%02d:%02d / -:--", new Object[]{Integer.valueOf(r3), Integer.valueOf(r4), Integer.valueOf(r112)});
    L12:
        if (r3 != 0) goto L20;
        if (r0 != 0) goto L20;
        if (r13 == false) goto L18;
        return String.format(Locale.US, "%02d:%02d / %02d:%02d", new Object[]{Integer.valueOf(r4), Integer.valueOf(r112), Integer.valueOf(r1), Integer.valueOf(r2)});
    L18:
        return String.format(Locale.US, "%d:%02d / %d:%02d", new Object[]{Integer.valueOf(r4), Integer.valueOf(r112), Integer.valueOf(r1), Integer.valueOf(r2)});
    L20:
        return String.format(Locale.US, "%d:%02d:%02d / %d:%02d:%02d", new Object[]{Integer.valueOf(r3), Integer.valueOf(r4), Integer.valueOf(r112), Integer.valueOf(r0), Integer.valueOf(r1), Integer.valueOf(r2)});
    }

    public static String formatDuration(int r1, boolean r2) {
        return formatDuration(r1, r2, false);
    }

    public static String formatDuration(int r3, boolean r4, boolean r5) {
        int r0 = r3 / 3600;
        int r1 = (r3 / 60) % 60;
        int r32 = r3 % 60;
        if (r0 != 0) goto L9;
        if (r4 == false) goto L8;
        return String.format(Locale.US, "%02d:%02d", new Object[]{Integer.valueOf(r1), Integer.valueOf(r32)});
    L8:
        return String.format(Locale.US, "%d:%02d", new Object[]{Integer.valueOf(r1), Integer.valueOf(r32)});
    L9:
        if (r5 == false) goto L13;
        return String.format(Locale.US, "%d:%02d", new Object[]{Integer.valueOf(r0), Integer.valueOf(r1)});
    L13:
        return String.format(Locale.US, "%d:%02d:%02d", new Object[]{Integer.valueOf(r0), Integer.valueOf(r1), Integer.valueOf(r32)});
    }

    public static String formatDurationNoHours(int r1, boolean r2) {
        int r0 = r1 / 60;
        int r12 = r1 % 60;
        if (r2 == false) goto L7;
        return String.format(Locale.US, "%02d:%02d", new Object[]{Integer.valueOf(r0), Integer.valueOf(r12)});
    L7:
        return String.format(Locale.US, "%d:%02d", new Object[]{Integer.valueOf(r0), Integer.valueOf(r12)});
    }

    public static String formatFileSize(long r1) {
        return formatFileSize(r1, false, false);
    }

    public static String formatFileSize(long r7, boolean r9, boolean r10) {
        if (r7 != 0) goto L7;
        return String.format("%d KB", new Object[]{0});
    L7:
        if (r7 >= 1024) goto L11;
        return String.format("%d B", new Object[]{Long.valueOf(r7)});
    L11:
        if (r7 >= 1048576) goto L21;
        float r72 = r7 / 1024.0f;
        if (r9 == false) goto L19;
        int r8 = (int) r72;
        if (((r72 - r8) * 10.0f) != 0.0f) goto L19;
        return String.format("%d KB", new Object[]{Integer.valueOf(r8)});
    L19:
        return String.format("%.1f KB", new Object[]{Float.valueOf(r72)});
    L21:
        if (r7 >= 1048576000) goto L30;
        float r73 = (r7 / 1024.0f) / 1024.0f;
        if (r9 == false) goto L29;
        int r82 = (int) r73;
        if (((r73 - r82) * 10.0f) != 0.0f) goto L29;
        return String.format("%d MB", new Object[]{Integer.valueOf(r82)});
    L29:
        return String.format("%.1f MB", new Object[]{Float.valueOf(r73)});
    L30:
        float r74 = ((int) ((r7 / 1024) / 1024)) / 1000.0f;
        if (r9 == false) goto L36;
        int r83 = (int) r74;
        if (((r74 - r83) * 10.0f) != 0.0f) goto L36;
        return String.format("%d GB", new Object[]{Integer.valueOf(r83)});
    L36:
        if (r10 == false) goto L40;
        return String.format("%.1f GB", new Object[]{Float.valueOf(r74)});
    L40:
        return String.format("%.2f GB", new Object[]{Float.valueOf(r74)});
    }

    public static String formatFullDuration(int r3) {
        int r0 = r3 / 3600;
        int r1 = (r3 / 60) % 60;
        int r2 = r3 % 60;
        if (r3 >= 0) goto L7;
        return String.format(Locale.US, "-%02d:%02d:%02d", new Object[]{Integer.valueOf(Math.abs(r0)), Integer.valueOf(Math.abs(r1)), Integer.valueOf(Math.abs(r2))});
    L7:
        return String.format(Locale.US, "%02d:%02d:%02d", new Object[]{Integer.valueOf(r0), Integer.valueOf(r1), Integer.valueOf(r2)});
    }

    public static String formatLongDuration(int r1) {
        return formatDuration(r1, true);
    }

    public static String formatLongDuration(int r1, int r2) {
        return formatDuration(r1, r2, true);
    }

    public static String formatShortDuration(int r1) {
        return formatDuration(r1, false);
    }

    public static String formatShortDuration(int r1, int r2) {
        return formatDuration(r1, r2, false);
    }

    public static SpannableStringBuilder formatSpannable(CharSequence r7, GenericProvider<Integer, String> r8, CharSequence... r9) {
        String r0 = r7.toString();
        SpannableStringBuilder r72 = SpannableStringBuilder.valueOf(r7);
        int r2 = 0;
    L4:
        if (r2 >= r9.length) goto L9;
        String r3 = r8.provide(Integer.valueOf(r2));
        int r4 = r0.indexOf(r3);
        if (r4 == (-1)) goto L8;
        r72.replace(r4, r3.length() + r4, r9[r2]);
        r0 = r0.substring(0, r4) + r9[r2].toString() + r0.substring(r4 + r3.length());
    L8:
        r2 = r2 + 1;
        goto L4
    L9:
        return r72;
    }

    public static SpannableStringBuilder formatSpannable(CharSequence r2, CharSequence... r3) {
        if (r2.toString().contains("%s") == false) goto L7;
        return formatSpannableSimple(r2, r3);
    L7:
        return formatSpannable(r2, new AndroidUtilities$$ExternalSyntheticLambda8(), r3);
    }

    public static SpannableStringBuilder formatSpannableSimple(CharSequence r1, CharSequence... r2) {
        return formatSpannable(r1, new AndroidUtilities$$ExternalSyntheticLambda31(), r2);
    }

    public static String formatTimerDurationFast(long r10, int r12) {
        StringBuilder r0 = new StringBuilder();
        long r3 = r10 / 60;
        if (r3 < 60) goto L5;
        r0.append(r3 / 60);
        r0.append(":");
        normalizeTimePart(r0, r3 % 60);
        r0.append(":");
        normalizeTimePart(r0, r10 % 60);
        r0.append(",");
        r0.append(r12 / 10);
    L7:
        return r0.toString();
    L5:
        r0.append(r3);
        r0.append(":");
        normalizeTimePart(r0, r10 % 60);
        r0.append(",");
        r0.append(r12 / 10);
        goto L7
    }

    public static String formatTimestamp(int r6) {
        int r0 = r6 / 3600;
        int r1 = (r6 / 60) % 60;
        int r62 = r6 % 60;
        if (r0 <= 0) goto L5;
        String r2 = String.format(Locale.US, "%dh", new Object[]{Integer.valueOf(r0)});
    L6:
        if (r1 <= 0) goto L12;
        Locale r3 = Locale.US;
        if (r0 <= 0) goto L10;
        String r4 = "%02dm";
    L11:
        r2 = r2.concat(String.format(r3, r4, new Object[]{Integer.valueOf(r1)}));
        goto L12
    L10:
        r4 = "%dm";
    L12:
        Locale r32 = Locale.US;
        if (r0 > 0) goto L17;
        if (r1 > 0) goto L17;
        String r02 = "%ds";
    L19:
        return r2.concat(String.format(r32, r02, new Object[]{Integer.valueOf(r62)}));
    L17:
        r02 = "%02ds";
        goto L19
    L5:
        r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    public static String formatVideoDuration(int r11, int r12) {
        int r0 = r12 / 3600;
        int r1 = (r12 / 60) % 60;
        int r122 = r12 % 60;
        int r2 = r11 / 3600;
        int r3 = (r11 / 60) % 60;
        int r112 = r11 % 60;
        if (r2 != 0) goto L7;
        if (r0 != 0) goto L7;
        return String.format(Locale.US, "%02d:%02d / %02d:%02d", new Object[]{Integer.valueOf(r3), Integer.valueOf(r112), Integer.valueOf(r1), Integer.valueOf(r122)});
    L7:
        if (r0 == 0) goto L9;
        if (r2 != 0) goto L14;
        return String.format(Locale.US, "%02d:%02d / %d:%02d:%02d", new Object[]{Integer.valueOf(r3), Integer.valueOf(r112), Integer.valueOf(r0), Integer.valueOf(r1), Integer.valueOf(r122)});
    L14:
        return String.format(Locale.US, "%d:%02d:%02d / %d:%02d:%02d", new Object[]{Integer.valueOf(r2), Integer.valueOf(r3), Integer.valueOf(r112), Integer.valueOf(r0), Integer.valueOf(r1), Integer.valueOf(r122)});
    L9:
        return String.format(Locale.US, "%d:%02d:%02d / %02d:%02d", new Object[]{Integer.valueOf(r2), Integer.valueOf(r3), Integer.valueOf(r112), Integer.valueOf(r1), Integer.valueOf(r122)});
    }

    public static String formatVideoDurationFast(int r4, int r5) {
        StringBuilder r0 = new StringBuilder();
        if (r4 < 60) goto L5;
        normalizeTimePart(r0, r4 / 60);
        r0.append(":");
        normalizeTimePart(r0, r4 % 60);
        r0.append(":");
        normalizeTimePart(r0, r5);
    L7:
        return r0.toString();
    L5:
        normalizeTimePart(r0, r4);
        r0.append(":");
        normalizeTimePart(r0, r5);
        goto L7
    }

    public static String formatWholeNumber(int r6, int r7) {
        if (r6 != 0) goto L5;
        return MVEL.VERSION_SUB;
    L5:
        float r1 = r6;
        if (r7 != 0) goto L9;
        r7 = r6;
    L9:
        if (r7 < 1000) goto L11;
        int r62 = 0;
    L13:
        if (r7 < 1000) goto L18;
        if (r62 >= (numbersSignatureArray.length - 1)) goto L18;
        r7 = r7 / 1000;
        r1 = r1 / 1000.0f;
        r62 = r62 + 1;
    L18:
        if (r1 >= 0.1d) goto L20;
        return MVEL.VERSION_SUB;
    L20:
        float r0 = r1 * 10.0f;
        float r2 = (int) r0;
        if (r0 != r2) goto L25;
        return String.format(Locale.ENGLISH, "%s%s", new Object[]{formatCount((int) r1), numbersSignatureArray[r62]});
    L25:
        return String.format(Locale.ENGLISH, "%.1f%s", new Object[]{Float.valueOf(r2 / 10.0f), numbersSignatureArray[r62]});
    L11:
        return formatCount(r6);
    }

    private static void gatherLinks(ArrayList<LinkSpec> r7, CharSequence r8, Pattern r9, String[] r10, Linkify.MatchFilter r11, boolean r12) {
        if (TextUtils.indexOf(r8, 9472) < 0) goto L6;
        r8 = r8.toString().replace(9472, ' ');
    L6:
        if (TextUtils.isEmpty(r8) == false) goto L8;
    L10:
        Matcher r92 = r9.matcher(r8);
    L12:
        if (r92.find() == false) goto L23;
        int r0 = r92.start();
        int r3 = r92.end();
        if (r11 == null) goto L17;
        if (r11.acceptMatch(r8, r0, r3) == false) goto L12;
    L17:
        LinkSpec r4 = new LinkSpec(null);
        String r6 = makeUrl(r92.group(0), r10);
        if (r12 == false) goto L22;
        if (Browser.isInternalUrl(r6, true, null) == false) goto L12;
    L22:
        r4.url = r6;
        r4.start = r0;
        r4.end = r3;
        r7.add(r4);
        goto L12
    L23:
        return;
    L8:
        if (TextUtils.lastIndexOf(r8, '_') != (r8.length() - 1)) goto L10;
        r8 = r8.subSequence(0, r8.length() - 1).toString() + "a";
        goto L10
    }

    public static String generateFileName(int r5, String r6) {
        Date r0 = new Date();
        r0.setTime((System.currentTimeMillis() + Utilities.random.nextInt(1000)) + 1);
        String r02 = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS", Locale.US).format(r0);
        if (r5 != 0) goto L10;
        StringBuilder r52 = new StringBuilder("IMG_");
        r52.append(r02);
        r52.append(".");
        if (TextUtils.isEmpty(r6) == false) goto L7;
        r6 = "jpg";
    L7:
        r52.append(r6);
        return r52.toString();
    L10:
        return "VID_" + r02 + ".mp4";
    }

    public static File generatePicturePath() {
        return generatePicturePath(false, null);
    }

    public static File generatePicturePath(boolean r2, String r3) {
        File r0 = FileLoader.getDirectory(100);     // Catch: Exception -> L11
        if (r2 == true) goto L10;
        if (r0 == null) goto L10;
        return new File(r0, generateFileName(0, r3));
    L10:
        return new File(ApplicationLoader.applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES), generateFileName(0, r3));
    L11:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static CharSequence generateSearchName(String r8, String r9, String r10) {
        if (r8 != null) goto L6;
        if (r9 != null) goto L6;
    L7:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        if (TextUtils.isEmpty(r10) == true) goto L7;
        SpannableStringBuilder r1 = new SpannableStringBuilder();
        if (r8 != null) goto L11;
    L17:
        r8 = r9;
    L18:
        if (r8 != null) goto L20;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L20:
        String r82 = r8.trim();
        String r92 = " " + r82.toLowerCase();
        int r3 = 0;
    L21:
        int r4 = r92.indexOf(" " + r10, r3);
        if (r4 == (-1)) goto L42;
        int r5 = 1;
        if (r4 != 0) goto L26;
        int r6 = 0;
    L27:
        int r62 = r4 - r6;
        int r7 = r10.length();
        if (r4 != 0) goto L30;
        r5 = 0;
    L30:
        int r72 = (r7 + r5) + r62;
        if (r3 != 0) goto L33;
    L35:
        if (r3 != 0) goto L38;
        if (r62 == 0) goto L38;
        r1.append(r82.substring(0, r62));
    L38:
        String r32 = r82.substring(r62, Math.min(r82.length(), r72));
        if (r32.startsWith(" ") == false) goto L41;
        r1.append(" ");
    L41:
        String r33 = r32.trim();
        int r42 = r1.length();
        r1.append(r33);
        r1.setSpan(new ForegroundColorSpanThemable(Theme.key_windowBackgroundWhiteBlueText4), r42, r33.length() + r42, 33);
        r3 = r72;
        goto L21
    L33:
        if (r3 == (r62 + 1)) goto L35;
        r1.append(r82.substring(r3, r62));
        goto L38
    L26:
        r6 = 1;
        goto L27
    L42:
        if (r3 != (-1)) goto L44;
    L46:
        return r1;
    L44:
        if (r3 >= r82.length()) goto L46;
        r1.append(r82.substring(r3));
        goto L46
    L11:
        if (r8.length() == 0) goto L17;
        if (r9 == null) goto L18;
        if (r9.length() == 0) goto L18;
        r8 = r8 + " " + r9;
        goto L18
    }

    public static File generateVideoPath() {
        return generateVideoPath(false);
    }

    public static File generateVideoPath(boolean r6) {
        File r62 = getAlbumDir(r6);     // Catch: Exception -> L8
        Date r1 = new Date();     // Catch: Exception -> L8
        r1.setTime((System.currentTimeMillis() + Utilities.random.nextInt(1000)) + 1);     // Catch: Exception -> L8
        if (generatingVideoPathFormat != null) goto L6;
        generatingVideoPathFormat = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS", Locale.US);     // Catch: Exception -> L8
    L6:
        return new File(r62, "VID_" + generatingVideoPathFormat.format(r1) + ".mp4");
    L8:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static Activity getActivity() {
        return getActivity(null);
    }

    public static Activity getActivity(Context r1) {
        Activity r12 = findActivity(r1);
        if (r12 != null) goto L5;
    L6:
        r12 = LaunchActivity.instance;
    L7:
        if (r12 == null) goto L13;
        if (r12.isFinishing() == true) goto L13;
        return r12;
    L13:
        return findActivity(ApplicationLoader.applicationContext);
    L5:
        if (r12.isFinishing() == false) goto L7;
        goto L6
    }

    private static File getAlbumDir(boolean r3) {
        if (r3 == true) goto L29;
        if (BuildVars.NO_SCOPED_STORAGE == false) goto L29;
        int r32 = Build.VERSION.SDK_INT;
        if (r32 >= 33) goto L8;
    L9:
        if (r32 > 33) goto L14;
        if (ApplicationLoader.applicationContext.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0) goto L29;
    L14:
        if ("mounted".equals(Environment.getExternalStorageState()) == false) goto L25;
        File r33 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "exteraGram");
        if (r33.mkdirs() == false) goto L18;
    L23:
        return r33;
    L18:
        if (r33.exists() == true) goto L23;
        if (BuildVars.LOGS_ENABLED == false) goto L22;
        FileLog.d("failed to create directory");
    L22:
        return null;
    L25:
        if (BuildVars.LOGS_ENABLED == false) goto L27;
        FileLog.d("External storage is not mounted READ/WRITE.");
    L27:
        return null;
    L8:
        if (ApplicationLoader.applicationContext.checkSelfPermission("android.permission.READ_MEDIA_IMAGES") == 0) goto L9;
    L29:
        return FileLoader.getDirectory(0);
    }

    public static float getAnimatorDurationScale() {
        return Settings.Global.getFloat(ApplicationLoader.applicationContext.getContentResolver(), "animator_duration_scale", 1.0f);
    L8:
        return 1.0f;
    }

    public static int getAverageColor(int r4, int r5) {
        int r0 = Color.red(r4);
        int r1 = Color.red(r5);
        int r2 = Color.green(r4);
        int r3 = Color.green(r5);
        int r42 = Color.blue(r4);
        return Color.argb(255, (r0 / 2) + (r1 / 2), (r2 / 2) + (r3 / 2), (r42 / 2) + (Color.blue(r5) / 2));
    }

    public static Bitmap getBitmapFromRaw(int r2) {
        Bitmap r0 = null;
        InputStream r22 = ApplicationLoader.applicationContext.getResources().openRawResource(r2);     // Catch: Throwable -> L9
        r0 = BitmapFactory.decodeStream(r22);     // Catch: Throwable -> L7
    L27:
        r22.close();     // Catch: IOException -> L17
    L13:
        return r0;
    L7:
        th = th;
    L25:
        FileLog.e(th);     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        r22.close();     // Catch: IOException -> L18
    L16:
        throw th;
    L9:
        th = th;
        r22 = null;
        goto L25
    }

    public static void getBitmapFromSurface(Surface r3, Bitmap r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (r3.isValid() == false) goto L15;
        CountDownLatch r0 = new CountDownLatch(1);
        PixelCopy.request(r3, r4, new AndroidUtilities$$ExternalSyntheticLambda32(r0), Utilities.searchQueue.getHandler());
        r0.await();     // Catch: InterruptedException -> L9
        return;
    L9:
        e = move-exception;
        e.printStackTrace();
        return;
    }

    public static void getBitmapFromSurface(SurfaceView r3, Bitmap r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (r3.getHolder().getSurface().isValid() == false) goto L15;
        CountDownLatch r0 = new CountDownLatch(1);
        PixelCopy.request(r3, r4, new AndroidUtilities$$ExternalSyntheticLambda23(r0), Utilities.searchQueue.getHandler());
        r0.await();     // Catch: InterruptedException -> L9
        return;
    L9:
        e = move-exception;
        e.printStackTrace();
        return;
    }

    public static void getBitmapFromSurface(SurfaceView r1, Bitmap r2, Runnable r3) {
        if (r1 != null) goto L4;
        return;
    L4:
        if (ApplicationLoader.applicationHandler != null) goto L6;
        return;
    L6:
        if (r1.getHolder().getSurface().isValid() == false) goto L12;
        PixelCopy.request(r1, r2, new AndroidUtilities$$ExternalSyntheticLambda20(r3), ApplicationLoader.applicationHandler);
        return;
    }

    public static Bitmap getBitmapFromWindow(Window r7) {
        if (r7 != null) goto L5;
    L16:
        return null;
    L5:
        if (r7.getDecorView() == null) goto L16;
        Bitmap r1 = Bitmap.createBitmap(r7.getDecorView().getWidth(), r7.getDecorView().getHeight(), Bitmap.Config.ARGB_8888);
        boolean[] r3 = {false};
        CountDownLatch r5 = new CountDownLatch(1);
        PixelCopy.request(r7, r1, new AndroidUtilities$$ExternalSyntheticLambda33(r3, r5), Utilities.searchQueue.getHandler());
        r5.await();     // Catch: InterruptedException -> L10
    L13:
        if (r3[0] == false) goto L15;
        return r1;
    L15:
        r1.recycle();
    L10:
        e = move-exception;
        e.printStackTrace();
        goto L13
    }

    public static Uri getBitmapShareUri(Bitmap r3, String r4, Bitmap.CompressFormat r5) {
        File r0 = getCacheDir();
        if (r0.isDirectory() == false) goto L25;
    L9:
        File r1 = new File(r0, r4);
        FileOutputStream r42 = new FileOutputStream(r1);     // Catch: Exception -> L15
        r3.compress(r5, 87, r42);     // Catch: Throwable -> L17
        r42.close();     // Catch: Throwable -> L17
        Uri r32 = FileProvider.getUriForFile(ApplicationLoader.applicationContext, ApplicationLoader.getApplicationId() + ".provider", r1);     // Catch: Throwable -> L17
        r42.close();     // Catch: Exception -> L15
        return r32;
    L17:
        th = move-exception;
        r42.close();     // Catch: Throwable -> L20
    L22:
        throw th;     // Catch: Exception -> L15
    L20:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L15
    L15:
        e = move-exception;
        FileLog.e(e);
        return null;
    L25:
        r0.mkdirs();     // Catch: Exception -> L6
    L6:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static String getBuildVersionInfo() {
        PackageInfo r2 = ApplicationLoader.applicationContext.getPackageManager().getPackageInfo(ApplicationLoader.applicationContext.getPackageName(), 0);     // Catch: Exception -> L12
        int r3 = r2.versionCode;     // Catch: Exception -> L12
        int r4 = r3 / 10;     // Catch: Exception -> L12
        int r32 = r3 % 10;     // Catch: Exception -> L12
        if (r32 != 1) goto L7;
    L9:
        String r0 = "store bundled " + Build.CPU_ABI + " " + Build.CPU_ABI2;     // Catch: Exception -> L12
    L10:
        return LocaleController.formatString("TelegramVersion", R.string.TelegramVersion, new Object[]{String.format(Locale.US, "v%s (%d) %s", new Object[]{r2.versionName, Integer.valueOf(r4), r0})});
    L7:
        if (r32 == 2) goto L9;
        r0 = "universal " + Build.CPU_ABI + " " + Build.CPU_ABI2;     // Catch: Exception -> L12
    L12:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static File getCacheDir() {
        String r0 = null;
        String r1 = Environment.getExternalStorageState();     // Catch: Exception -> L5
    L7:
        if (r1 != null) goto L9;
    L10:
        FileLog.d("external dir mounted");
        File[] r12 = ApplicationLoader.applicationContext.getExternalCacheDirs();     // Catch: Exception -> L37
        int r2 = 0;
        File r3 = r12[0];     // Catch: Exception -> L37
        if (TextUtils.isEmpty(SharedConfig.storageCacheDir) == false) goto L14;
    L21:
        StringBuilder r13 = new StringBuilder();     // Catch: Exception -> L37
        r13.append("check dir ");     // Catch: Exception -> L37
        if (r3 == null) goto L25;
        r0 = r3.getPath();     // Catch: Exception -> L37
    L25:
        r13.append(r0);     // Catch: Exception -> L37
        r13.append(" ");     // Catch: Exception -> L37
        FileLog.d(r13.toString());     // Catch: Exception -> L37
        if (r3 != null) goto L28;
    L34:
        if (r3 == null) goto L56;
        FileLog.d("check dir file exist " + r3.exists() + " can write " + r3.canWrite());     // Catch: Exception -> L37
    L56:
        File r02 = ApplicationLoader.applicationContext.getCacheDir();     // Catch: Exception -> L42
        if (r02 == null) goto L58;
        return r02;
    L58:
        File r03 = ApplicationLoader.applicationContext.getFilesDir();     // Catch: Exception -> L55
        if (r03 == null) goto L54;
        File r14 = new File(r03, "cache/");     // Catch: Exception -> L55
        r14.mkdirs();     // Catch: Exception -> L55
        if (r03.exists() == false) goto L49;
    L50:
        if (r03.canWrite() == false) goto L54;
        return r14;
    L49:
        if (r03.mkdirs() == true) goto L50;
    L54:
        return new File(_UrlKt.FRAGMENT_ENCODE_SET);
    L42:
        e = move-exception;
        FileLog.e(e);
        goto L58
    L28:
        if (r3.exists() == true) goto L32;
        if (r3.mkdirs() == false) goto L34;
    L32:
        if (r3.canWrite() == false) goto L34;
        return r3;
    L14:
        if (r2 >= r12.length) goto L21;
        File r4 = r12[r2];     // Catch: Exception -> L37
        if (r4 == null) goto L20;
        if (r4.getAbsolutePath().startsWith(SharedConfig.storageCacheDir) == false) goto L20;
        r3 = r12[r2];     // Catch: Exception -> L37
    L20:
        r2 = r2 + 1;     // Catch: Exception -> L37
    L37:
        e = move-exception;
        FileLog.e(e);
        goto L56
    L9:
        if (r1.startsWith("mounted") == false) goto L56;
    L5:
        e = move-exception;
        FileLog.e(e);
        r1 = null;
        goto L7
    }

    public static String getCertificateSHA1Fingerprint() {
        ByteArrayInputStream r1 = new ByteArrayInputStream(ApplicationLoader.applicationContext.getPackageManager().getPackageInfo(ApplicationLoader.applicationContext.getPackageName(), 64).signatures[0].toByteArray());     // Catch: Throwable -> L5
        return Utilities.bytesToHex(Utilities.computeSHA1(((X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(r1)).getEncoded()));
    L5:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static String getCertificateSHA256Fingerprint() {
        ByteArrayInputStream r1 = new ByteArrayInputStream(ApplicationLoader.applicationContext.getPackageManager().getPackageInfo(ApplicationLoader.applicationContext.getPackageName(), 64).signatures[0].toByteArray());     // Catch: Throwable -> L5
        return Utilities.bytesToHex(Utilities.computeSHA256(((X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(r1)).getEncoded()));
    L5:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static int getColorDistance(int r5, int r6) {
        int r0 = Color.red(r5);
        int r1 = Color.green(r5);
        int r52 = Color.blue(r5);
        int r2 = Color.red(r6);
        int r3 = Color.green(r6);
        int r4 = (r0 + r2) / 2;
        int r02 = r0 - r2;
        int r12 = r1 - r3;
        int r53 = r52 - Color.blue(r6);
        return (((((r4 + 512) * r02) * r02) >> 8) + ((r12 * 4) * r12)) + ((((767 - r4) * r53) * r53) >> 8);
    }

    public static float[] getCoordinateInParent(ViewGroup r4, View r5) {
        float r0 = 0.0f;
        if (r5 == null) goto L8;
        if (r4 == null) goto L8;
        float r1 = 0.0f;
        float r2 = 0.0f;
    L6:
        if (r5 == r4) goto L15;
        if (r5 == null) goto L8;
        r1 = r1 + r5.getY();
        r2 = r2 + r5.getX();
        if ((r5 instanceof NestedScrollView) == false) goto L13;
        r1 = r1 - r5.getScrollY();
        r2 = r2 - r5.getScrollX();
    L13:
        if ((r5.getParent() instanceof View) == false) goto L8;
        r5 = (View) r5.getParent();
        goto L6
    L15:
        r0 = r2;
    L17:
        return new float[]{r0, r1};
    L8:
        r1 = 0.0f;
        goto L17
    }

    public static String[] getCurrentKeyboardLanguage() {
        InputMethodManager r1 = (InputMethodManager) ApplicationLoader.applicationContext.getSystemService("input_method");     // Catch: Exception -> L37
        InputMethodSubtype r2 = r1.getCurrentInputMethodSubtype();     // Catch: Exception -> L37
        String r3 = null;
        if (r2 == null) goto L8;
        String r12 = r2.getLanguageTag();     // Catch: Exception -> L37
        if (TextUtils.isEmpty(r12) == false) goto L16;
        r12 = r2.getLocale();     // Catch: Exception -> L37
    L16:
        if (TextUtils.isEmpty(r12) == false) goto L36;
        String r13 = LocaleController.getSystemLocaleStringIso639();     // Catch: Exception -> L37
        LocaleController.LocaleInfo r22 = LocaleController.getInstance().getCurrentLocaleInfo();     // Catch: Exception -> L37
        String r8 = r22.getBaseLangCode();     // Catch: Exception -> L37
        boolean r9 = TextUtils.isEmpty(r8);     // Catch: Exception -> L37
        String r82 = r8;
        if (r9 == false) goto L21;
        r82 = r22.getLangCode();     // Catch: Exception -> L37
    L21:
        if (r13.contains(r82) == true) goto L27;
        if (r82.contains(r13) == true) goto L27;
        r3 = r82;
    L30:
        if (TextUtils.isEmpty(r3) == true) goto L34;
        return new String[]{r13.replace('_', SignatureVisitor.SUPER), r3};
    L34:
        return new String[]{r13.replace('_', SignatureVisitor.SUPER)};
    L27:
        if (r13.contains("en") == true) goto L30;
        r3 = "en";
        goto L30
    L36:
        return new String[]{r12.replace('_', SignatureVisitor.SUPER)};
    L8:
        InputMethodSubtype r14 = r1.getLastInputMethodSubtype();     // Catch: Exception -> L37
        if (r14 == null) goto L14;
        String r23 = r14.getLanguageTag();     // Catch: Exception -> L37
        if (TextUtils.isEmpty(r23) == false) goto L13;
        r12 = r14.getLocale();     // Catch: Exception -> L37
        goto L16
    L13:
        r12 = r23;
        goto L16
    L14:
        r12 = null;
        goto L16
    L38:
        return new String[]{"en"};
    }

    public static String getDataColumn(Context r8, Uri r9, String r10, String[] r11) {
        String[] r3 = {"_data"};
        Cursor r82 = r8.getContentResolver().query(r9, r3, r10, r11, null);     // Catch: Exception -> L29
        if (r82 != null) goto L33;
    L26:
        if (r82 == null) goto L28;
        r82.close();     // Catch: Exception -> L29
        goto L28
    L33:
    L14:
        th = move-exception;
        r82.close();     // Catch: Throwable -> L22
        goto L28
    L35:
        throw th;     // Catch: Exception -> L29
    L22:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L29
        throw th;     // Catch: Exception -> L29
    L6:
        if (r82.moveToFirst() == false) goto L26;
        String r92 = r82.getString(r82.getColumnIndexOrThrow("_data"));     // Catch: Throwable -> L14
        if (r92.startsWith("content://") == false) goto L10;
    L18:
        r82.close();     // Catch: Exception -> L29
        return null;
    L10:
        if (r92.startsWith("/") == true) goto L16;
        if (r92.startsWith("file://") == true) goto L16;
    L16:
        r82.close();     // Catch: Exception -> L29
        return r92;
    L28:
        return null;
    }

    public static ArrayList<File> getDataDirs() {
        ArrayList<File> r1 = null;
        File[] r0 = ApplicationLoader.applicationContext.getExternalFilesDirs(null);
        if (r0 == null) goto L15;
        int r2 = 0;
    L6:
        if (r2 >= r0.length) goto L15;
        File r3 = r0[r2];
        if (r3 == null) goto L14;
        r3.getAbsolutePath();
        if (r1 != null) goto L13;
        r1 = new ArrayList();
    L13:
        r1.add(r0[r2]);
    L14:
        r2 = r2 + 1;
    L15:
        if (r1 != null) goto L18;
        r1 = new ArrayList();
    L18:
        if (r1.isEmpty() == false) goto L20;
        r1.add(Environment.getExternalStorageDirectory());
    L20:
        return r1;
    }

    public static Insets getDefaultWindowInsets(WindowInsetsCompat r2, boolean r3) {
        int r0 = WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout();
        Insets r02 = Insets.max(r2.getInsetsIgnoringVisibility(r0), r2.getInsets(r0));
        if (r3 == true) goto L5;
        return r02;
    L5:
        return Insets.max(r02, r2.getInsets(WindowInsetsCompat.Type.ime()));
    }

    public static int getDominantColor(Bitmap r13) {
        if (r13 != null) goto L5;
        return -1;
    L5:
        float r0 = (r13.getHeight() - 1) / 10.0f;
        float r2 = (r13.getWidth() - 1) / 10.0f;
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        int r7 = 0;
    L7:
        if (r3 >= 10) goto L15;
        int r9 = 0;
    L9:
        if (r9 >= 10) goto L14;
        int r10 = r13.getPixel((int) (r3 * r2), (int) (r9 * r0));
        if (Color.alpha(r10) <= 200) goto L13;
        r5 = r5 + Color.red(r10);
        r6 = r6 + Color.green(r10);
        r7 = r7 + Color.blue(r10);
        r4 = r4 + 1;
    L13:
        r9 = r9 + 1;
        goto L9
    L14:
        r3 = r3 + 1;
        goto L7
    L15:
        if (r4 != 0) goto L18;
        return 0;
    L18:
        return Color.argb(255, r5 / r4, r6 / r4, r7 / r4);
    }

    public static String getHelloWorld() {
        return "Hello World!";
    }

    public static String getHostAuthority(Uri r0) {
        if (r0 != null) goto L6;
        return null;
    L6:
        return getHostAuthority(r0.toString());
    }

    public static String getHostAuthority(Uri r0, boolean r1) {
        if (r0 != null) goto L6;
        return null;
    L6:
        return getHostAuthority(r0.toString(), r1);
    }

    public static String getHostAuthority(String r1) {
        return getHostAuthority(r1, false);
    }

    public static String getHostAuthority(String r2, boolean r3) {
        if (r2 != null) goto L5;
        return null;
    L5:
        Matcher r22 = getURIParsePattern().matcher(r2);
        if (r22.matches() == false) goto L16;
        String r23 = r22.group(4);
        if (r23 == null) goto L10;
        r23 = r23.toLowerCase();
    L10:
        if (r3 == false) goto L17;
        if (r23 != null) goto L13;
        return r23;
    L13:
        if (r23.startsWith("www.") == true) goto L15;
        return r23;
    L15:
        return r23.substring(4);
    L17:
        return r23;
    L16:
        return null;
    }

    public static Pair<Integer, Integer> getImageOrientation(ExifInterface r5) {
        int r0 = 0;
        int r2 = 2;
        switch(r5.getAttributeInt("Orientation", 1)) {
            case 2: goto L12;
            case 3: goto L11;
            case 4: goto L13;
            case 5: goto L7;
            case 6: goto L9;
            case 7: goto L8;
            case 8: goto L6;
            default: goto L5;
        };     // Catch: Exception -> L15
    L5:
        r2 = 0;
        goto L13
    L6:
        r2 = 0;
    L7:
        r0 = 270;
        goto L13
    L8:
        r2 = 1;
        goto L7
    L9:
        int r52 = 90;
    L10:
        r2 = 0;
        r0 = r52;
        goto L13
    L11:
        r52 = 180;
        goto L10
    L12:
        r2 = 1;
    L13:
        return new Pair(Integer.valueOf(r0), Integer.valueOf(r2));
    L15:
        e = move-exception;
        FileLog.e(e);
        return new Pair(0, 0);
    }

    public static Pair<Integer, Integer> getImageOrientation(File r2) {
        return getImageOrientation(new ExifInterface(r2));
    L5:
        e = move-exception;
        FileLog.e(e);
        return new Pair(0, 0);
    }

    public static Pair<Integer, Integer> getImageOrientation(InputStream r2) {
        return getImageOrientation(new ExifInterface(r2));
    L5:
        e = move-exception;
        FileLog.e(e);
        return new Pair(0, 0);
    }

    public static Pair<Integer, Integer> getImageOrientation(String r2) {
        return getImageOrientation(new ExifInterface(r2));
    L6:
        return new Pair(0, 0);
    }

    public static boolean getLightNavigationBar(Window r2) {
        if (Build.VERSION.SDK_INT >= 26) goto L5;
        return false;
    L5:
        return BitwiseUtils.hasFlag(r2.getDecorView().getSystemUiVisibility(), 16);
    }

    public static File getLogsDir() {
        if ("mounted".equals(Environment.getExternalStorageState()) == false) goto L13;
        File r3 = new File(ApplicationLoader.applicationContext.getExternalFilesDir(null).getAbsolutePath() + "/logs");     // Catch: Exception -> L12
        r3.mkdirs();     // Catch: Exception -> L12
        return r3;
    L13:
        File r2 = new File(ApplicationLoader.applicationContext.getCacheDir() + "/logs");     // Catch: Exception -> L9
        r2.mkdirs();     // Catch: Exception -> L9
        return r2;
    L9:
        File r22 = new File(ApplicationLoader.applicationContext.getFilesDir() + "/logs");     // Catch: Exception -> L11
        r22.mkdirs();     // Catch: Exception -> L11
        return r22;
    L11:
        return null;
    }

    public static int getMinTabletSide() {
        if (isSmallTablet() == true) goto L6;
        Point r0 = displaySize;
        int r02 = Math.min(r0.x, r0.y);
        return r02 - getTabletLeftFragmentSize(r02, 0, 0);
    L6:
        Point r03 = displaySize;
        int r04 = Math.min(r03.x, r03.y);
        Point r2 = displaySize;
        int r22 = Math.max(r2.x, r2.y);
        return Math.min(r04, r22 - getTabletLeftFragmentSize(r22, 0, 0));
    }

    public static int getMyLayerVersion(int r1) {
        return r1 & 65535;
    }

    public static float getNavigationBarThirdButtonsFactor(float r0, float r1, int r2) {
        return lerp(r0, r1, getNavigationBarThirdButtonsFactor(r2));
    }

    public static float getNavigationBarThirdButtonsFactor(int r1) {
        return Utilities.clamp01((r1 - dp(32.0f)) / dp(16.0f));
    }

    public static int getOffsetColor(int r7, int r8, float r9, float r10) {
        int r0 = Color.red(r8);
        int r1 = Color.green(r8);
        int r2 = Color.blue(r8);
        int r82 = Color.alpha(r8);
        int r3 = Color.red(r7);
        int r4 = Color.green(r7);
        int r5 = Color.blue(r7);
        return Color.argb((int) ((Color.alpha(r7) + ((r82 - r7) * r9)) * r10), (int) (r3 + ((r0 - r3) * r9)), (int) (r4 + ((r1 - r4) * r9)), (int) (r5 + ((r2 - r5) * r9)));
    }

    public static String getPath(Uri r5) {
    L12:
        e = move-exception;
        FileLog.e(e);
    L48:
        return null;
    L4:
        if (DocumentsContract.isDocumentUri(ApplicationLoader.applicationContext, r5) == false) goto L40;
        if (isExternalStorageDocument(r5) == false) goto L15;
        String[] r52 = DocumentsContract.getDocumentId(r5).split(":");     // Catch: Exception -> L12
        if ("primary".equalsIgnoreCase(r52[0]) == false) goto L48;
        return Environment.getExternalStorageDirectory() + "/" + r52[1];
    L15:
        if (isDownloadsDocument(r5) == false) goto L19;
        return getDataColumn(ApplicationLoader.applicationContext, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.parseLong(DocumentsContract.getDocumentId(r5))), null, null);
    L19:
        if (isMediaDocument(r5) == false) goto L48;
        String[] r53 = DocumentsContract.getDocumentId(r5).split(":");     // Catch: Exception -> L12
        String r1 = r53[0];     // Catch: Exception -> L12
        int r3 = r1.hashCode();     // Catch: Exception -> L12
        if (r3 == 93166550) goto L34;
        if (r3 == 100313435) goto L31;
        if (r3 == 112202875) goto L28;
    L36:
        Uri r12 = null;
    L38:
        return getDataColumn(ApplicationLoader.applicationContext, r12, "_id=?", new String[]{r53[1]});
    L28:
        if (r1.equals(MediaStreamTrack.VIDEO_TRACK_KIND) == false) goto L36;
        r12 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;     // Catch: Exception -> L12
        goto L38
    L31:
        if (r1.equals("image") == false) goto L36;
        r12 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;     // Catch: Exception -> L12
        goto L38
    L34:
        if (r1.equals(MediaStreamTrack.AUDIO_TRACK_KIND) == false) goto L36;
        r12 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;     // Catch: Exception -> L12
        goto L38
    L40:
        if ("content".equalsIgnoreCase(r5.getScheme()) == false) goto L44;
        return getDataColumn(ApplicationLoader.applicationContext, r5, null, null);
    L44:
        if ("file".equalsIgnoreCase(r5.getScheme()) == false) goto L48;
        return r5.getPath();
    }

    public static int getPatternColor(int r1) {
        return getPatternColor(r1, false);
    }

    public static int getPatternColor(int r7, boolean r8) {
        float[] r72 = RGBtoHSB(Color.red(r7), Color.green(r7), Color.blue(r7));
        float r1 = r72[1];
        if (r1 > 0.0f) goto L8;
        float r3 = r72[2];
        if (r3 >= 1.0f) goto L13;
        if (r3 > 0.0f) goto L8;
    L13:
        if (r8 == true) goto L18;
        float r32 = r72[2];
        if (r32 > 0.5f) goto L18;
        r72[2] = Math.max(0.0f, Math.min(1.0f, 1.0f - (r32 * 0.65f)));
    L19:
        int r73 = HSBtoRGB(r72[0], r72[1], r72[2]);
        if (r8 == false) goto L22;
        int r82 = -1711276033;
    L24:
        return r73 & r82;
    L22:
        r82 = 1728053247;
    L18:
        r72[2] = Math.max(0.0f, r72[2] * 0.65f);
    L8:
        if (r8 == false) goto L10;
        float r33 = 0.15f;
    L11:
        r72[1] = Math.min(1.0f, (r33 + r1) + ((1.0f - r1) * 0.1f));
        goto L13
    L10:
        r33 = 0.05f;
        goto L11
    }

    public static int getPatternSideColor(int r6) {
        float[] r62 = RGBtoHSB(Color.red(r6), Color.green(r6), Color.blue(r6));
        r62[1] = Math.min(1.0f, r62[1] + 0.05f);
        float r2 = r62[2];
        if (r2 <= 0.5f) goto L5;
        r62[2] = Math.max(0.0f, r2 * 0.9f);
    L7:
        return HSBtoRGB(r62[0], r62[1], r62[2]) | (-16777216);
    L5:
        r62[2] = Math.max(0.0f, r2 * 0.9f);
        goto L7
    }

    public static int getPeerLayerVersion(int r1) {
        return Math.max(73, (r1 >> 16) & 65535);
    }

    public static int getPhotoSize() {
        return getPhotoSize(false);
    }

    public static int getPhotoSize(boolean r0) {
        if (r0 == false) goto L9;
        if (highQualityPhotoSize != null) goto L7;
        highQualityPhotoSize = 2560;
    L7:
        return highQualityPhotoSize.intValue();
    L9:
        if (photoSize != null) goto L12;
        photoSize = 1280;
    L12:
        return photoSize.intValue();
    }

    public static float getPixelsInCM(float r1, boolean r2) {
        float r12 = r1 / 2.54f;
        if (r2 == false) goto L5;
        float r22 = displayMetrics.xdpi;
    L7:
        return r12 * r22;
    L5:
        r22 = displayMetrics.ydpi;
        goto L7
    }

    private static String getPlainText(CharSequence r0) {
        if (r0 != null) goto L6;
        return null;
    L6:
        return r0.toString();
    }

    public static long getPrefIntOrLong(SharedPreferences r0, String r1, long r2) {
        return r0.getLong(r1, r2);
    L5:
        return r0.getInt(r1, (int) r2);
    }

    public static Point getRealScreenSize() {
        Point r0 = new Point();
        ((WindowManager) ApplicationLoader.applicationContext.getSystemService("window")).getDefaultDisplay().getRealSize(r0);     // Catch: Exception -> L5
        return r0;
    L5:
        e = move-exception;
        FileLog.e(e);
        return r0;
    }

    public static ArrayList<File> getRootDirs() {
        HashSet r0 = new HashSet();
        ArrayList<File> r2 = null;
        File[] r1 = ApplicationLoader.applicationContext.getExternalFilesDirs(null);
        if (r1 == null) goto L21;
        int r3 = r1.length;
        int r5 = 0;
    L5:
        if (r5 >= r3) goto L21;
        File r6 = r1[r5];
        if (r6 == null) goto L20;
        String r62 = r6.getAbsolutePath();
        int r7 = r62.indexOf("/Android");
        if (r7 < 0) goto L20;
        if (r2 != null) goto L13;
        r2 = new ArrayList();
    L13:
        File r8 = new File(r62.substring(0, r7));
        int r63 = 0;
    L15:
        if (r63 >= r2.size()) goto L18;
        r2.get(r63).getPath().equals(r8.getPath());
        r63 = r63 + 1;
        goto L15
    L18:
        if (r0.contains(r8.getAbsolutePath()) == true) goto L20;
        r0.add(r8.getAbsolutePath());
        r2.add(r8);
    L20:
        r5 = r5 + 1;
    L21:
        if (r2 != null) goto L24;
        r2 = new ArrayList();
    L24:
        if (r2.isEmpty() == false) goto L30;
        File r12 = Environment.getExternalStorageDirectory();
        if (r12 == null) goto L30;
        if (r0.contains(r12.getAbsolutePath()) == true) goto L30;
        r2.add(r12);
    L30:
        return r2;
    }

    public static View getRootView(View r1) {
    L2:
        if (r1 == null) goto L6;
        if ((r1.getParent() instanceof View) == false) goto L6;
        r1 = (View) r1.getParent();
    L6:
        return r1;
    }

    public static String getSafeString(String r2) {
        return BAD_CHARS_MESSAGE_PATTERN.matcher(r2).replaceAll("\u200c");
    L7:
        return r2;
    }

    public static Bitmap getScaledBitmap(float r7, float r8, String r9, String r10, int r11) {
        BitmapFactory.Options r1 = new BitmapFactory.Options();     // Catch: Throwable -> L6
        r1.inJustDecodeBounds = true;     // Catch: Throwable -> L6
        if (r9 == null) goto L8;
        BitmapFactory.decodeFile(r9, r1);     // Catch: Throwable -> L6
        FileInputStream r3 = null;
    L10:
        int r102 = r1.outWidth;     // Catch: Throwable -> L23
        if (r102 <= 0) goto L35;
        int r4 = r1.outHeight;     // Catch: Throwable -> L23
        if (r4 <= 0) goto L35;
        if (r7 <= r8) goto L18;
        if (r102 >= r4) goto L18;
        r8 = r7;
        r7 = r8;
    L18:
        float r72 = Math.min(r102 / r7, r4 / r8);     // Catch: Throwable -> L23
        r1.inSampleSize = 1;     // Catch: Throwable -> L23
        if (r72 <= 1.0f) goto L25;
    L20:
        int r82 = r1.inSampleSize * 2;     // Catch: Throwable -> L23
        r1.inSampleSize = r82;     // Catch: Throwable -> L23
        if (r82 < r72) goto L20;
    L25:
        r1.inJustDecodeBounds = false;     // Catch: Throwable -> L23
        if (r9 == null) goto L28;
        Bitmap r73 = BitmapFactory.decodeFile(r9, r1);     // Catch: Throwable -> L23
    L29:
        if (r3 != null) goto L52;
    L34:
        return r73;
    L52:
        r3.close();     // Catch: Exception -> L32
        return r73;
    L32:
        e = move-exception;
        FileLog.e(e);
        goto L34
    L28:
        r3.getChannel().position(r11);     // Catch: Throwable -> L23
        r73 = BitmapFactory.decodeStream(r3, null, r1);     // Catch: Throwable -> L23
    L35:
        if (r3 != null) goto L59;
    L43:
        return null;
    L59:
        r3.close();     // Catch: Exception -> L38
    L38:
        e = move-exception;
        FileLog.e(e);
        goto L43
    L8:
        r3 = new FileInputStream(r10);     // Catch: Throwable -> L6
        r3.getChannel().position(r11);     // Catch: Throwable -> L23
        BitmapFactory.decodeStream(r3, null, r1);     // Catch: Throwable -> L23
    L23:
        th = th;
    L57:
        FileLog.e(th);     // Catch: Throwable -> L44
        if (r3 == null) goto L43;
    L44:
        th = move-exception;
        if (r3 != null) goto L55;
    L50:
        throw th;
    L55:
        r3.close();     // Catch: Exception -> L48
    L48:
        e = move-exception;
        FileLog.e(e);
    L6:
        th = th;
        r3 = null;
        goto L57
    }

    public static int getShadowHeight() {
        if (ExteraConfig.getDividerStyle() == DividerStyle.LINE) goto L6;
        return 0;
    L6:
        float r0 = density;
        if (r0 < 4.0f) goto L11;
        return 3;
    L11:
        if (r0 < 2.0f) goto L14;
        return 2;
    L14:
        return 1;
    }

    public static File getSharingDirectory() {
        return new File(FileLoader.getDirectory(4), "sharing/");
    }

    public static int getStatusBarHeight(Context r4) {
        int r0 = r4.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (r0 > 0) goto L5;
        return 0;
    L5:
        return r4.getResources().getDimensionPixelSize(r0);
    }

    public static byte[] getStringBytes(String r1) {
        return r1.getBytes(StandardCharsets.UTF_8);
    L5:
        return new byte[0];
    }

    public static Long getSysInfoLong(String r0) {
        String r02 = getSysInfoString(r0);
        if (r02 != null) goto L9;
        return null;
    L9:
        return Utilities.parseLong(r02);
    L11:
        return null;
    }

    public static String getSysInfoString(String r3) {
        RandomAccessFile r0 = null;
        RandomAccessFile r1 = new RandomAccessFile(r3, "r");     // Catch: Throwable -> L12 Exception -> L14
    L33:
        String r32 = r1.readLine();     // Catch: Throwable -> L10 Exception -> L24
        if (r32 != null) goto L29;
    L25:
        r1.close();     // Catch: Exception -> L21
    L20:
        return null;
    L29:
        r1.close();     // Catch: Exception -> L22
    L7:
        return r32;
    L10:
        th = th;
        r0 = r1;
    L15:
        if (r0 != null) goto L27;
    L17:
        throw th;
    L27:
        r0.close();     // Catch: Exception -> L23
    L18:
        if (r1 == null) goto L20;
    L14:
        r1 = null;
    L12:
        th = th;
        goto L15
    }

    @SuppressLint({"PrivateApi"})
    public static String getSystemProperty(String r4) {
        return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class}).invoke(null, new Object[]{r4});
    L5:
        return null;
    }

    public static int getTabletLeftFragmentSize(int r1, int r2, int r3) {
        return r2 + Math.max(dp(320.0f), (((r1 - r2) - r3) * 35) / 100);
    }

    public static int getThumbForNameOrMime(String r3, String r4, boolean r5) {
        if (r3 != null) goto L5;
    L57:
        if (r5 == false) goto L61;
        return documentMediaIcons[0];
    L61:
        return documentIcons[0];
    L5:
        if (r3.length() == 0) goto L57;
        if (r3.contains(".doc") == false) goto L9;
    L42:
        int r0 = 0;
    L43:
        if (r0 != (-1)) goto L52;
        int r02 = r3.lastIndexOf(46);
        if (r02 != (-1)) goto L47;
        String r03 = _UrlKt.FRAGMENT_ENCODE_SET;
    L49:
        if (r03.length() == 0) goto L51;
        r0 = r03.charAt(0) % documentIcons.length;
        goto L52
    L51:
        r0 = r3.charAt(0) % documentIcons.length;
        goto L52
    L47:
        r03 = r3.substring(r02 + 1);
    L52:
        if (r5 == false) goto L56;
        return documentMediaIcons[r0];
    L56:
        return documentIcons[r0];
    L9:
        if (r3.contains(".txt") == true) goto L42;
        if (r3.contains(".psd") == true) goto L42;
        if (r3.contains(".xls") == false) goto L16;
    L41:
        r0 = 1;
        goto L43
    L16:
        if (r3.contains(".csv") == true) goto L41;
        if (r3.contains(".pdf") == false) goto L21;
    L40:
        r0 = 2;
        goto L43
    L21:
        if (r3.contains(".ppt") == true) goto L40;
        if (r3.contains(".key") == true) goto L40;
        if (r3.contains(".zip") == false) goto L28;
    L39:
        r0 = 3;
        goto L43
    L28:
        if (r3.contains(".rar") == true) goto L39;
        if (r3.contains(".ai") == true) goto L39;
        if (r3.contains(".mp3") == true) goto L39;
        if (r3.contains(".mov") == true) goto L39;
        if (r3.contains(".avi") == true) goto L39;
        r0 = -1;
        goto L43
    }

    public static int getTransparentColor(int r3, float r4) {
        return Color.argb((int) (Color.alpha(r3) * r4), Color.red(r3), Color.green(r3), Color.blue(r3));
    }

    public static CharSequence getTrimmedString(CharSequence r5) {
        if (r5 != null) goto L4;
    L20:
        return r5;
    L4:
        if (r5.length() == 0) goto L20;
    L7:
        if (r5.length() <= 0) goto L14;
        if (r5.charAt(0) == '\n') goto L12;
        if (r5.charAt(0) != ' ') goto L14;
    L12:
        r5 = r5.subSequence(1, r5.length());
    L14:
        if (r5.length() <= 0) goto L20;
        if (r5.charAt(r5.length() - 1) == '\n') goto L19;
        if (r5.charAt(r5.length() - 1) != ' ') goto L20;
    L19:
        r5 = r5.subSequence(0, r5.length() - 1);
        goto L14
    }

    public static Typeface getTypeface(String r2) {
        return typefaceCache.computeIfAbsent(r2, new AndroidUtilities$$ExternalSyntheticLambda30());
    }

    public static Pattern getURIParsePattern() {
        if (uriParse != null) goto L6;
        uriParse = Pattern.compile("^(([^:/?#]+):)?(//([^/?#]*))?([^?#]*)(\\?([^#]*))?(#(.*))?");
    L6:
        return uriParse;
    }

    public static Vibrator getVibrator() {
        if (vibrator != null) goto L6;
        vibrator = (Vibrator) ApplicationLoader.applicationContext.getSystemService("vibrator");
    L6:
        return vibrator;
    }

    public static int getViewInset(View r4) {
        if (r4 != null) goto L5;
    L17:
        return 0;
    L5:
        if (r4.getHeight() == displaySize.y) goto L17;
        if (r4.getHeight() == (displaySize.y - statusBarHeight)) goto L17;
        WindowInsets r42 = r4.getRootWindowInsets();     // Catch: Exception -> L13
        if (r42 == null) goto L15;
        return r42.getStableInsetBottom();
    L15:
        return 0;
    L13:
        e = move-exception;
        FileLog.e(e);
        goto L17
    }

    public static void getViewPositionInParent(View r4, ViewGroup r5, float[] r6) {
        r6[0] = 0.0f;
        r6[1] = 0.0f;
        if (r4 == null) goto L11;
        if (r5 == null) goto L15;
    L6:
        if (r4 == r5) goto L16;
        if ((r4.getParent() instanceof ViewPager) == true) goto L10;
        r6[0] = r6[0] + r4.getX();
        r6[1] = r6[1] + r4.getY();
    L10:
        r4 = (View) r4.getParent();
        goto L6
    L16:
        return;
    L15:
        return;
    }

    public static String getWallPaperUrl(Object r3) {
        if ((r3 instanceof TLRPC.TL_wallPaper) == false) goto L21;
        TLRPC.TL_wallPaper r32 = (TLRPC.TL_wallPaper) r3;
        String r0 = "https://" + MessagesController.getInstance(UserConfig.selectedAccount).linkPrefix + "/bg/" + r32.slug;
        StringBuilder r1 = new StringBuilder();
        TLRPC.WallPaperSettings r2 = r32.settings;
        if (r2 == null) goto L16;
        if (r2.blur == false) goto L10;
        r1.append("blur");
    L10:
        if (r32.settings.motion == false) goto L16;
        if (r1.length() <= 0) goto L14;
        r1.append("+");
    L14:
        r1.append("motion");
    L16:
        if (r1.length() > 0) goto L18;
        return r0;
    L18:
        return r0 + "?mode=" + r1;
    L21:
        if ((r3 instanceof WallpapersListActivity.ColorWallpaper) == true) goto L23;
        return null;
    L23:
        return ((WallpapersListActivity.ColorWallpaper) r3).getUrl();
    }

    public static int getWallpaperRotation(int r0, boolean r1) {
        if (r1 == false) goto L4;
        int r02 = r0 + 180;
    L6:
        if (r02 < 360) goto L8;
        r02 = r02 - 360;
    L8:
        if (r02 >= 0) goto L10;
        r02 = r02 + 360;
        goto L8
    L10:
        return r02;
    L4:
        r02 = r0 - 180;
        goto L6
    }

    public static Boolean getWasTablet() {
        return wasTablet;
    }

    public static void googleVoiceClientService_performAction(Intent r0, boolean r1) {
        if (r1 == true) goto L4;
        return;
    L4:
        runOnUIThread(new AndroidUtilities$$ExternalSyntheticLambda12(r0));
    }

    public static boolean gzip(File r4, File r5) {
        BufferedInputStream r1 = new BufferedInputStream(new FileInputStream(r4));     // Catch: IOException -> L17 FileNotFoundException -> L19
        GZIPOutputStream r42 = new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream(r5)));     // Catch: Throwable -> L21
        byte[] r52 = new byte[8192];     // Catch: Throwable -> L11
    L7:
        int r2 = r1.read(r52);     // Catch: Throwable -> L11
        if (r2 == (-1)) goto L13;
        r42.write(r52, 0, r2);     // Catch: Throwable -> L11
        goto L7
    L13:
        r42.close();     // Catch: Throwable -> L21
        r1.close();     // Catch: IOException -> L17 FileNotFoundException -> L19
        return true;
    L11:
        th = move-exception;
        r42.close();     // Catch: Throwable -> L25
    L27:
        throw th;     // Catch: Throwable -> L21
    L25:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L21
    L21:
        th = move-exception;
        r1.close();     // Catch: Throwable -> L30
    L32:
        throw th;     // Catch: IOException -> L17 FileNotFoundException -> L19
    L30:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IOException -> L17 FileNotFoundException -> L19
    L19:
        e = move-exception;
        FileLog.e(e);
    L35:
        return false;
    L17:
        e = move-exception;
        FileLog.e(e);
        goto L35
    }

    public static boolean handleProxyIntent(Activity r3, Intent r4, boolean r5) {
        if (r4 != null) goto L20;
        return false;
    L20:
        if ((r4.getFlags() & 1048576) == 0) goto L8;
        return false;
    L8:
        Uri r42 = r4.getData();     // Catch: Exception -> L19
        if (r42 == null) goto L18;
        ProxySettings r43 = ProxySettings.fromUri(r42);     // Catch: Exception -> L19
        if (r43 == null) goto L18;
        if (r43.isValid() == false) goto L18;
        if (r5 == false) goto L22;
        showProxyAlert(r3, r43);     // Catch: Exception -> L19
        return true;
    L22:
        return true;
    L18:
        return false;
    }

    public static boolean hasDialogOnTop(BaseFragment r7) {
        if (r7 != null) goto L5;
        return false;
    L5:
        Dialog r1 = r7.visibleDialog;
        if (r1 == null) goto L15;
        if ((r1 instanceof AlertDialog) == true) goto L15;
        if ((r1 instanceof BottomSheet) == true) goto L12;
    L13:
        return true;
    L12:
        if (((BottomSheet) r1).attachedFragment == null) goto L13;
    L15:
        if (r7.getParentLayout() != null) goto L17;
        return false;
    L17:
        List<View> r12 = allGlobalViews();
        if (r12 != null) goto L20;
    L38:
        return false;
    L20:
        if (r12.isEmpty() == true) goto L38;
        int r3 = r12.size() - 1;
        View r4 = null;
    L23:
        if (r3 < 0) goto L36;
        r4 = r12.get(r3);
        Dialog r5 = r7.visibleDialog;
        if ((r5 instanceof AlertDialog) == false) goto L30;
        if (r4 != getRootView(((AlertDialog) r5).getContainerView())) goto L30;
    L34:
        r3 = r3 - 1;
    L30:
        if ((r4 instanceof AlertDialog.AlertDialogView) == true) goto L34;
        if ((r4 instanceof PipRoundVideoView.PipFrameLayout) == true) goto L34;
    L36:
        if (r4 == getRootView(r7.getParentLayout().getView())) goto L38;
        return true;
    }

    public static void hideKeyboard(View r2) {
        if (r2 == null) goto L14;
        InputMethodManager r0 = (InputMethodManager) r2.getContext().getSystemService("input_method");     // Catch: Exception -> L9
        if (r0.isActive() == true) goto L7;
        return;
    L7:
        r0.hideSoftInputFromWindow(r2.getWindowToken(), 0);     // Catch: Exception -> L9
        return;
    L9:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    public static CharSequence highlightText(CharSequence r6, String r7, Theme.ResourcesProvider r8) {
        if (TextUtils.isEmpty(r7) == false) goto L5;
        return null;
    L5:
        if (TextUtils.isEmpty(r6) == true) goto L22;
        String r0 = r6.toString().toLowerCase();
        SpannableStringBuilder r1 = SpannableStringBuilder.valueOf(r6);
        int r2 = r0.indexOf(r7);
    L8:
        if (r2 < 0) goto L14;
        r1.setSpan(new ForegroundColorSpanThemable(Theme.key_windowBackgroundWhiteBlueText4, r8), r2, Math.min(r7.length() + r2, r6.length()), 0);     // Catch: Exception -> L11
    L13:
        r2 = r0.indexOf(r7, r2 + 1);
    L11:
        e = move-exception;
        FileLog.e(e);
        goto L13
    L14:
        return r1;
    L22:
        return null;
    }

    public static CharSequence highlightText(CharSequence r4, ArrayList<String> r5, Theme.ResourcesProvider r6) {
        if (r5 != null) goto L5;
        return null;
    L5:
        int r1 = 0;
        int r2 = 0;
    L7:
        if (r1 >= r5.size()) goto L14;
        CharSequence r3 = highlightText(r4, r5.get(r1), r6);
        if (r3 == null) goto L11;
        r4 = r3;
    L12:
        r1 = r1 + 1;
        goto L7
    L11:
        r2 = r2 + 1;
        goto L12
    L14:
        if (r2 != r5.size()) goto L16;
        return null;
    L16:
        return r4;
    }

    public static int hsvToColor(double r0, double r2, double r4) {
        int[] r02 = hsvToRgb(r0, r2, r4);
        return Color.argb(255, r02[0], r02[1], r02[2]);
    }

    public static int[] hsvToRgb(double r10, double r12, double r14) {
        double r102 = r10 * 6.0d;
        double r0 = (int) Math.floor(r102);
        double r103 = r102 - r0;
        double r4 = (1.0d - r12) * r14;
        double r6 = (1.0d - (r103 * r12)) * r14;
        double r2 = (1.0d - ((1.0d - r103) * r12)) * r14;
        int r104 = ((int) r0) % 6;
        if (r104 != 0) goto L5;
        r4 = r2;
        r2 = r4;
    L23:
        return new int[]{(int) (r14 * 255.0d), (int) (r4 * 255.0d), (int) (r2 * 255.0d)};
    L5:
        if (r104 != 1) goto L7;
        r2 = r4;
        r4 = r14;
        r14 = r6;
        goto L23
    L7:
        if (r104 != 2) goto L9;
        double r8 = r4;
        r4 = r14;
    L17:
        r14 = r8;
        goto L23
    L9:
        if (r104 != 3) goto L11;
        r2 = r14;
        r14 = r4;
        r4 = r6;
        goto L23
    L11:
        if (r104 != 4) goto L13;
        r8 = r2;
        r2 = r14;
        goto L17
    L13:
        if (r104 == 5) goto L15;
        r14 = 0.0d;
        r2 = 0.0d;
        r4 = 0.0d;
        goto L23
    L15:
        r2 = r6;
        goto L23
    }

    public static float ilerp(float r0, float r1, float r2) {
        return (r0 - r1) / (r2 - r1);
    }

    public static float ilerp(int r0, int r1, int r2) {
        return (r0 - r1) / (r2 - r1);
    }

    public static int indexOfIgnoreCase(String r6, String r7) {
        if (r7.isEmpty() == true) goto L28;
        if (r6.isEmpty() == true) goto L28;
        int r1 = 0;
    L9:
        if (r1 >= r6.length()) goto L26;
        if ((r7.length() + r1) > r6.length()) goto L12;
        int r3 = 0;
        int r2 = r1;
    L15:
        if (r2 >= r6.length()) goto L23;
        if (r3 >= r7.length()) goto L23;
        if (Character.toLowerCase(r6.charAt(r2)) != Character.toLowerCase(r7.charAt(r3))) goto L23;
        r3 = r3 + 1;
        r2 = r2 + 1;
    L23:
        if (r3 == r7.length()) goto L24;
        r1 = r1 + 1;
        goto L9
    L24:
        return r1;
    L12:
        return -1;
    L26:
        return -1;
    L28:
        return r6.indexOf(r7);
    }

    public static boolean intersect1d(float r2, float r3, float r4, float r5) {
        if (Math.max(r2, r3) > Math.min(r4, r5)) goto L5;
        return false;
    L5:
        if (Math.max(r4, r5) <= Math.min(r2, r3)) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean intersect1d(int r2, int r3, int r4, int r5) {
        if (Math.max(r2, r3) > Math.min(r4, r5)) goto L5;
        return false;
    L5:
        if (Math.max(r4, r5) <= Math.min(r2, r3)) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean intersect1dInclusive(int r2, int r3, int r4, int r5) {
        if (Math.max(r2, r3) >= Math.min(r4, r5)) goto L5;
        return false;
    L5:
        if (Math.max(r4, r5) < Math.min(r2, r3)) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isAccessibilityScreenReaderEnabled() {
        return isAccessibilityTouchExplorationEnabled();
    }

    public static boolean isAccessibilityTouchExplorationEnabled() {
        if (accessibilityManager != null) goto L6;
        accessibilityManager = (AccessibilityManager) ApplicationLoader.applicationContext.getSystemService("accessibility");
    L6:
        if (accessibilityManager.isEnabled() == true) goto L8;
        return false;
    L8:
        if (accessibilityManager.isTouchExplorationEnabled() == false) goto L13;
        return true;
    L13:
        return false;
    }

    public static boolean isActivityRunning(Activity r2) {
        if (r2 != null) goto L6;
        return false;
    L6:
        if (r2.isDestroyed() == false) goto L8;
    L11:
        return false;
    L8:
        if (r2.isFinishing() == true) goto L11;
        return true;
    }

    public static boolean isAirplaneModeOn() {
        if (Settings.Global.getInt(ApplicationLoader.applicationContext.getContentResolver(), "airplane_mode_on", 0) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isBannedForever(TLRPC.TL_chatBannedRights r6) {
        if (r6 != null) goto L4;
        return true;
    L4:
        if (Math.abs(r6.until_date - (System.currentTimeMillis() / 1000)) > 157680000) goto L10;
        return false;
    L10:
        return true;
    }

    public static boolean isContextSafe(Context r3) {
        if (r3 != null) goto L6;
        return false;
    L6:
        if ((r3 instanceof Activity) == false) goto L15;
        Activity r32 = (Activity) r3;
        if (r32.isFinishing() == false) goto L11;
        return false;
    L11:
        if (r32.isDestroyed() == false) goto L13;
        return false;
    L13:
        return true;
    L15:
        if ((r3 instanceof ContextWrapper) == true) goto L17;
        return true;
    L17:
        return isContextSafe(((ContextWrapper) r3).getBaseContext());
    }

    public static boolean isDarkColor(int r1) {
        if (computePerceivedBrightness(r1) >= 0.721f) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isDownloadsDocument(Uri r1) {
        return "com.android.providers.downloads.documents".equals(r1.getAuthority());
    }

    public static boolean isENOSPC(Exception r2) {
        if ((r2 instanceof IOException) == false) goto L9;
        if ((r2.getCause() instanceof ErrnoException) == false) goto L9;
        if (((ErrnoException) r2.getCause()).errno != OsConstants.ENOSPC) goto L9;
        return true;
    L9:
        if (r2.getMessage() != null) goto L11;
        return false;
    L11:
        if (r2.getMessage().equalsIgnoreCase("no space left on device") == false) goto L17;
        return true;
    L17:
        return false;
    }

    public static boolean isEROFS(Exception r2) {
        if ((r2 instanceof IOException) == false) goto L9;
        if ((r2.getCause() instanceof ErrnoException) == false) goto L9;
        if (((ErrnoException) r2.getCause()).errno != OsConstants.EROFS) goto L9;
        return true;
    L9:
        if (r2.getMessage() != null) goto L11;
        return false;
    L11:
        if (r2.getMessage().toLowerCase().contains("read-only file system") == false) goto L17;
        return true;
    L17:
        return false;
    }

    public static boolean isExternalStorageDocument(Uri r1) {
        return "com.android.externalstorage.documents".equals(r1.getAuthority());
    }

    public static boolean isFilNotFoundException(Throwable r1) {
        if ((r1 instanceof FileNotFoundException) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof EOFException) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean isFold() {
        if (ApplicationLoader.applicationContext != null) goto L5;
        return false;
    L5:
        if (ApplicationLoader.applicationContext.getPackageManager().hasSystemFeature("android.hardware.sensor.hinge_angle") == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isHonor() {
        if (isHonor != null) goto L16;
        String r0 = Build.BRAND.toLowerCase();     // Catch: Exception -> L13
        if (r0.contains("huawei") == false) goto L7;
    L10:
        boolean r02 = true;
    L11:
        isHonor = Boolean.valueOf(r02);     // Catch: Exception -> L13
        goto L16
    L7:
        if (r0.contains("honor") == true) goto L10;
        r02 = false;
    L13:
        e = move-exception;
        FileLog.e(e);
        isHonor = Boolean.FALSE;
    L16:
        return isHonor.booleanValue();
    }

    public static boolean isInAirplaneMode(Context r2) {
        if (Settings.Global.getInt(r2.getContentResolver(), "airplane_mode_on", 0) == 0) goto L7;
        return true;
    L7:
        return false;
    }

    public static boolean isInPictureInPictureMode(Activity r0) {
        return r0.isInPictureInPictureMode();
    }

    public static boolean isInternalUri(int r1) {
        return isInternalUri(null, r1);
    }

    public static boolean isInternalUri(Uri r1) {
        return isInternalUri(r1, 0);
    }

    private static boolean isInternalUri(Uri r7, int r8) {
        if (r7 == null) goto L24;
        String r72 = r7.getPath();
        if (r72 != null) goto L8;
        return false;
    L8:
        if (r72.matches(Pattern.quote(new File(ApplicationLoader.applicationContext.getCacheDir(), "voip_logs").getAbsolutePath()) + "/\\d+\\.log") == false) goto L10;
        return false;
    L10:
        int r82 = 0;
    L12:
        if (r72.length() > 4096) goto L13;
        String r4 = Utilities.readlink(r72);     // Catch: Throwable -> L23
        if (r4 != null) goto L17;
    L46:
        r72 = new File(r72).getCanonicalPath();     // Catch: Exception -> L39
    L41:
        if (r72.endsWith(".attheme") == false) goto L44;
        return false;
    L44:
        return r72.toLowerCase().contains("/data/data/" + ApplicationLoader.applicationContext.getPackageName());
    L39:
        r72.replace("/./", "/");
        goto L41
    L17:
        if (r4.equals(r72) == true) goto L46;
        r82 = r82 + 1;
        if (r82 >= 10) goto L21;
        r72 = r4;
        goto L12
    L21:
        return true;
    L23:
        return true;
    L13:
        return true;
    L24:
        r72 = _UrlKt.FRAGMENT_ENCODE_SET;
        int r42 = 0;
    L26:
        if (r72.length() > 4096) goto L27;
        String r5 = Utilities.readlinkFd(r8);     // Catch: Throwable -> L45
        if (r5 == null) goto L46;
        if (r5.equals(r72) == true) goto L46;
        r42 = r42 + 1;
        if (r42 >= 10) goto L35;
        r72 = r5;
        goto L26
    L35:
        return true;
    L45:
        return true;
    L27:
        return true;
    }

    public static boolean isKeyguardSecure() {
        return ((KeyguardManager) ApplicationLoader.applicationContext.getSystemService("keyguard")).isKeyguardSecure();
    }

    public static boolean isMapsInstalled(BaseFragment r5) {
        String r0 = ApplicationLoader.getMapsProvider().getMapsAppPackageName();
        ApplicationLoader.applicationContext.getPackageManager().getApplicationInfo(r0, 0);     // Catch: PackageManager.NameNotFoundException -> L6
        return true;
    L7:
        if (r5.getParentActivity() != null) goto L9;
        return false;
    L9:
        AlertDialog.Builder r2 = new AlertDialog.Builder(r5.getParentActivity());
        r2.setMessage(LocaleController.getString(ApplicationLoader.getMapsProvider().getInstallMapsString()));
        r2.setPositiveButton(LocaleController.getString(R.string.OK), new AndroidUtilities$$ExternalSyntheticLambda17(r0, r5));
        r2.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r5.showDialog(r2.create());
        return false;
    }

    public static boolean isMediaDocument(Uri r1) {
        return "com.android.providers.media.documents".equals(r1.getAuthority());
    }

    public static boolean isNumeric(String r0) {
        Double.parseDouble(r0);     // Catch: NumberFormatException -> L5
        return true;
    L5:
        return false;
    }

    public static boolean isProxyLink(Uri r4) {
        Activity r0 = getActivity();
        if (r0 != null) goto L6;
        return false;
    L6:
        return handleProxyIntent(r0, new Intent("android.intent.action.VIEW", r4), false);
    }

    public static boolean isPunctuationCharacter(char r5) {
        if (charactersMap != null) goto L8;
        charactersMap = new HashSet();
        char[] r0 = characters;
        int r1 = r0.length;
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L8;
        char r3 = r0[r2];
        charactersMap.add(Character.valueOf(r3));
        r2 = r2 + 1;
    L8:
        return charactersMap.contains(Character.valueOf(r5));
    }

    public static boolean isRTL(CharSequence r4) {
        if (r4 != null) goto L5;
    L17:
        return false;
    L5:
        if (r4.length() <= 0) goto L17;
        int r1 = 0;
    L9:
        if (r1 >= r4.length()) goto L17;
        char r2 = r4.charAt(r1);
        if (r2 < 1424) goto L16;
        if (r2 > 1791) goto L16;
        return true;
    L16:
        r1 = r1 + 1;
        goto L9
    }

    public static boolean isSafeToShow(Context r0) {
        Activity r02 = findActivity(r0);
        if (r02 != null) goto L7;
        return true;
    L7:
        return isActivityRunning(r02);
    }

    public static boolean isSimAvailable() {
        TelephonyManager r0 = (TelephonyManager) ApplicationLoader.applicationContext.getSystemService("phone");
        int r1 = r0.getSimState();
        if (r1 == 1) goto L10;
        if (r1 != 0) goto L6;
        return false;
    L6:
        if (r0.getPhoneType() != 0) goto L8;
        return false;
    L8:
        if (isAirplaneModeOn() == true) goto L14;
        return true;
    L14:
        return false;
    L10:
        return false;
    }

    public static boolean isSmallScreen() {
        if (isSmallScreen != null) goto L10;
        Point r0 = displaySize;
        if ((((Math.max(r0.x, r0.y) - statusBarHeight) - navigationBarHeight) / density) > 650.0f) goto L7;
        boolean r02 = true;
    L8:
        isSmallScreen = Boolean.valueOf(r02);
        goto L10
    L7:
        r02 = false;
    L10:
        return isSmallScreen.booleanValue();
    }

    public static boolean isSmallTablet() {
        Point r0 = displaySize;
        if ((Math.min(r0.x, r0.y) / density) > 690.0f) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isTablet() {
        if (isTabletInternal() == true) goto L5;
        return false;
    L5:
        if (SharedConfig.forceDisableTabletMode == true) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isTabletForce() {
        if (ApplicationLoader.applicationContext != null) goto L5;
        return false;
    L5:
        if (ApplicationLoader.applicationContext.getResources().getBoolean(R.bool.isTablet) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isTabletInternal() {
        if (ExteraConfig.getTabletMode() != 1) goto L6;
        isTablet = Boolean.TRUE;
    L12:
        return isTablet.booleanValue();
    L6:
        if (ExteraConfig.getTabletMode() != 2) goto L9;
        isTablet = Boolean.FALSE;
        goto L12
    L9:
        if (isTablet != null) goto L12;
        isTablet = Boolean.valueOf(isTabletForce());
        goto L12
    }

    public static boolean isTabletTwoPane() {
        if (isInMultiwindow == false) goto L5;
        return false;
    L5:
        if (isSmallTablet() == false) goto L8;
        Point r0 = displaySize;
        if (r0.x <= r0.y) goto L13;
        return true;
    L13:
        return false;
    L8:
        return true;
    }

    public static boolean isValidWallChar(char r1) {
        if (r1 != '-') goto L5;
        return true;
    L5:
        if (r1 == '~') goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean isWaitingForCall() {
        Object r0 = callLock;
        monitor-enter(r0);
        boolean r1 = waitingForCall;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public static boolean isWaitingForSms() {
        Object r0 = smsLock;
        monitor-enter(r0);
        boolean r1 = waitingForSms;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public static boolean isWhitespace(char r1) {
        if (Character.isWhitespace(r1) == false) goto L5;
        return true;
    L5:
        if (r1 != 10240) goto L7;
        return true;
    L7:
        if (r1 != 12644) goto L9;
        return true;
    L9:
        if (r1 == 65440) goto L17;
        return false;
    L17:
        return true;
    }

    public static boolean isWifiEnabled(Context r2) {
        WifiManager r22 = (WifiManager) r2.getApplicationContext().getSystemService("wifi");     // Catch: Exception -> L10
        if (r22 == null) goto L9;
        if (r22.isWifiEnabled() == false) goto L9;
        return true;
    L9:
        return false;
    }

    public static double lerp(double r2, double r4, float r6) {
        return r2 + (r6 * (r4 - r2));
    }

    public static float lerp(float r0, float r1, float r2) {
        return r0 + (r2 * (r1 - r0));
    }

    public static float lerp(float r1, float r2, float r3, float r4) {
        return lerp(r1, r2, r3, 0.5f, r4);
    }

    public static float lerp(float r1, float r2, float r3, float r4, float r5) {
        if (r5 >= r4) goto L7;
        return lerp(r1, r2, r5 / r4);
    L7:
        return lerp(r2, r3, (r5 - r4) / (1.0f - r4));
    }

    public static float lerp(boolean r3, boolean r4, float r5) {
        float r0 = 0.0f;
        if (r3 == false) goto L5;
        float r2 = 1.0f;
    L6:
        if (r4 == false) goto L8;
        float r42 = 1.0f;
    L9:
        if (r3 == false) goto L12;
        r0 = 1.0f;
    L12:
        return r2 + (r5 * (r42 - r0));
    L8:
        r42 = 0.0f;
        goto L9
    L5:
        r2 = 0.0f;
        goto L6
    }

    public static float lerp(float[] r2, float r3) {
        return lerp(r2[0], r2[1], r3);
    }

    public static int lerp(int r1, int r2, float r3) {
        return (int) (r1 + (r3 * (r2 - r1)));
    }

    public static void lerp(Matrix r1, Matrix r2, float r3, Matrix r4) {
        if (r1 == null) goto L7;
        if (r2 == null) goto L8;
        float[] r0 = tempFloats;
        r1.getValues(r0);
        float[] r12 = tempFloats2;
        r2.getValues(r12);
        lerp(r0, r12, r3, r12);
        r4.setValues(r12);
        return;
    L8:
        return;
    }

    public static void lerp(Rect r4, Rect r5, float r6, Rect r7) {
        if (r7 == null) goto L5;
        r7.set(lerp(r4.left, r5.left, r6), lerp(r4.top, r5.top, r6), lerp(r4.right, r5.right, r6), lerp(r4.bottom, r5.bottom, r6));
        return;
    }

    public static void lerp(Rect r4, RectF r5, float r6, RectF r7) {
        if (r7 == null) goto L5;
        r7.set(lerp(r4.left, r5.left, r6), lerp(r4.top, r5.top, r6), lerp(r4.right, r5.right, r6), lerp(r4.bottom, r5.bottom, r6));
        return;
    }

    public static void lerp(RectF r4, Rect r5, float r6, RectF r7) {
        if (r7 == null) goto L5;
        r7.set(lerp(r4.left, r5.left, r6), lerp(r4.top, r5.top, r6), lerp(r4.right, r5.right, r6), lerp(r4.bottom, r5.bottom, r6));
        return;
    }

    public static void lerp(RectF r4, RectF r5, float r6, RectF r7) {
        if (r7 == null) goto L5;
        r7.set(lerp(r4.left, r5.left, r6), lerp(r4.top, r5.top, r6), lerp(r4.right, r5.right, r6), lerp(r4.bottom, r5.bottom, r6));
        return;
    }

    public static void lerp(float[] r4, float[] r5, float r6, float[] r7) {
        if (r7 == null) goto L20;
        int r0 = 0;
    L6:
        if (r0 >= r7.length) goto L25;
        float r1 = 0.0f;
        if (r4 != null) goto L10;
    L13:
        float r2 = 0.0f;
    L14:
        if (r5 == null) goto L19;
        if (r0 >= r5.length) goto L19;
        r1 = r5[r0];
    L19:
        r7[r0] = lerp(r2, r1, r6);
        r0 = r0 + 1;
        goto L6
    L10:
        if (r0 >= r4.length) goto L13;
        r2 = r4[r0];
        goto L14
    L25:
        return;
    }

    public static void lerp(int[] r4, int[] r5, float r6, int[] r7) {
        if (r7 == null) goto L20;
        int r1 = 0;
    L6:
        if (r1 >= r7.length) goto L24;
        if (r4 != null) goto L9;
    L12:
        int r2 = 0;
    L13:
        if (r5 != null) goto L15;
    L18:
        int r3 = 0;
    L19:
        r7[r1] = lerp(r2, r3, r6);
        r1 = r1 + 1;
        goto L6
    L15:
        if (r1 >= r5.length) goto L18;
        r3 = r5[r1];
        goto L19
    L9:
        if (r1 >= r4.length) goto L12;
        r2 = r4[r1];
        goto L13
    L24:
        return;
    }

    public static float lerp3(float r1, float r2, float r3, float r4) {
        if (r4 >= 0.0f) goto L7;
        return lerp(r2, r1, -r4);
    L7:
        return lerp(r2, r3, r4);
    }

    public static float lerpAngle(float r2, float r3, float r4) {
        return ((r2 + ((((((r3 - r2) + 360.0f) + 180.0f) % 360.0f) - 180.0f) * r4)) + 360.0f) % 360.0f;
    }

    public static void lerpCentered(Rect r6, Rect r7, float r8, Rect r9) {
        if (r9 != null) goto L4;
        return;
    L4:
        float r0 = lerp(r6.centerX(), r7.centerX(), r8);
        float r1 = lerp(r6.centerY(), r7.centerY(), r8);
        float r2 = lerp(r6.width(), r7.width(), Math.min(1.0f, r8)) / 2.0f;
        float r62 = lerp(r6.height(), r7.height(), Math.min(1.0f, r8)) / 2.0f;
        r9.set((int) (r0 - r2), (int) (r1 - r62), (int) (r0 + r2), (int) (r1 + r62));
    }

    public static void lerpCentered(RectF r6, RectF r7, float r8, RectF r9) {
        if (r9 != null) goto L4;
        return;
    L4:
        float r0 = lerp(r6.centerX(), r7.centerX(), r8);
        float r1 = lerp(r6.centerY(), r7.centerY(), r8);
        float r2 = lerp(r6.width(), r7.width(), Math.min(1.0f, r8)) / 2.0f;
        float r62 = lerp(r6.height(), r7.height(), Math.min(1.0f, r8)) / 2.0f;
        r9.set(r0 - r2, r1 - r62, r0 + r2, r1 + r62);
    }

    public static int lerpColor(int r4, int r5, float r6) {
        return Color.argb(lerp(Color.alpha(r4), Color.alpha(r5), r6), lerp(Color.red(r4), Color.red(r5), r6), lerp(Color.green(r4), Color.green(r5), r6), lerp(Color.blue(r4), Color.blue(r5), r6));
    }

    public static int lerpColor3(int r1, int r2, int r3, float r4) {
        if (r4 >= 0.0f) goto L7;
        return lerpColor(r2, r1, -r4);
    L7:
        return lerpColor(r2, r3, r4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ArrayList<TLRPC.User> loadVCardFromStream(Uri r21, int r22, boolean r23, ArrayList<VcardItem> r24, String r25) {
        ArrayList<VcardItem> r1 = r24;
        ArrayList<TLRPC.User> r3 = null;
        AndroidUtilitiesIA r32 = 0;
        if (r23 == true) goto L175;
        InputStream r0 = ApplicationLoader.applicationContext.getContentResolver().openInputStream(r21);     // Catch: Throwable -> L80
    L9:
        ArrayList r4 = new ArrayList();     // Catch: Throwable -> L80
        BufferedReader r5 = new BufferedReader(new InputStreamReader(r0, StandardCharsets.UTF_8));     // Catch: Throwable -> L80
        int r6 = 0;
        VcardData r7 = null;
        StringBuilder r8 = null;
        VcardItem r10 = null;
        int r9 = 0;
    L10:
        String r11 = r5.readLine();     // Catch: Throwable -> L80
        if (r11 == null) goto L143;
        if (r11.startsWith("PHOTO") == true) goto L14;
        if (r11.indexOf(58) < 0) goto L74;
        if (r11.startsWith("BEGIN:VCARD") == false) goto L24;
        r7 = new VcardData(r32);     // Catch: Throwable -> L6
        r4.add(r7);     // Catch: Throwable -> L6
        r7.name = r25;     // Catch: Throwable -> L6
    L21:
        r10 = r32;
    L22:
        r9 = r6;
        goto L74
    L24:
        if (r11.startsWith("END:VCARD") == true) goto L21;
        if (r1 == null) goto L21;
        if (r11.startsWith("TEL") == false) goto L31;
        VcardItem r92 = new VcardItem();     // Catch: Throwable -> L6
        r92.type = r6;     // Catch: Throwable -> L6
    L68:
        if (r92 != null) goto L70;
    L72:
        r10 = r92;
        goto L22
    L70:
        if (r92.type < 0) goto L72;
        r1.add(r92);     // Catch: Throwable -> L6
        goto L72
    L31:
        if (r11.startsWith("EMAIL") == false) goto L34;
        r92 = new VcardItem();     // Catch: Throwable -> L6
        r92.type = 1;     // Catch: Throwable -> L6
        goto L68
    L34:
        if (r11.startsWith("ADR") == false) goto L36;
    L67:
        r92 = new VcardItem();     // Catch: Throwable -> L6
        r92.type = 2;     // Catch: Throwable -> L6
        goto L68
    L36:
        if (r11.startsWith("LABEL") == true) goto L67;
        if (r11.startsWith("GEO") == true) goto L67;
        if (r11.startsWith("URL") == false) goto L44;
        r92 = new VcardItem();     // Catch: Throwable -> L6
        r92.type = 3;     // Catch: Throwable -> L6
        goto L68
    L44:
        if (r11.startsWith("NOTE") == false) goto L47;
        r92 = new VcardItem();     // Catch: Throwable -> L6
        r92.type = 4;     // Catch: Throwable -> L6
        goto L68
    L47:
        if (r11.startsWith("BDAY") == false) goto L50;
        r92 = new VcardItem();     // Catch: Throwable -> L6
        r92.type = 5;     // Catch: Throwable -> L6
        goto L68
    L50:
        if (r11.startsWith("ORG") == false) goto L52;
    L66:
        r92 = new VcardItem();     // Catch: Throwable -> L6
        r92.type = 6;     // Catch: Throwable -> L6
        goto L68
    L52:
        if (r11.startsWith("TITLE") == true) goto L66;
        if (r11.startsWith("ROLE") == true) goto L66;
        if (r11.startsWith("X-ANDROID") == false) goto L60;
        r92 = new VcardItem();     // Catch: Throwable -> L6
        r92.type = -1;     // Catch: Throwable -> L6
        goto L68
    L60:
        if (r11.startsWith("X-PHONETIC") == false) goto L63;
    L65:
        r92 = r32;
        goto L68
    L63:
        if (r11.startsWith("X-") == false) goto L65;
        r92 = new VcardItem();     // Catch: Throwable -> L6
        r92.type = 20;     // Catch: Throwable -> L6
    L74:
        if (r9 != 0) goto L84;
        if (r7 == null) goto L84;
        if (r10 == null) goto L78;
        r10.vcardData.add(r11);     // Catch: Throwable -> L80
        goto L84
    L78:
        if (r7.vcard.length() <= 0) goto L82;
        r7.vcard.append('\n');     // Catch: Throwable -> L80
    L82:
        r7.vcard.append(r11);     // Catch: Throwable -> L80
    L84:
        if (r8 == null) goto L88;
        r8.append(r11);     // Catch: Throwable -> L80
        r11 = r8.toString();     // Catch: Throwable -> L80
        r8 = null;
    L88:
        if (r11.contains("=QUOTED-PRINTABLE") == true) goto L90;
    L93:
        if (r9 != 0) goto L97;
        if (r7 == null) goto L97;
        if (r10 == null) goto L97;
        r10.fullData = r11;     // Catch: Throwable -> L80
    L97:
        int r33 = r11.indexOf(":");     // Catch: Throwable -> L80
        if (r33 < 0) goto L100;
        InputStream r16 = r0;
        String[] r02 = {r11.substring(r6, r33), r11.substring(r33 + 1).trim()};     // Catch: Throwable -> L80
    L102:
        if (r02.length < 2) goto L104;
        if (r7 == null) goto L104;
        if (r02[r6].startsWith("FN") == false) goto L109;
    L118:
        String[] r34 = r02[r6].split(";");     // Catch: Throwable -> L80
        int r14 = r34.length;     // Catch: Throwable -> L80
        String[] r18 = r02;
        int r15 = r6;
        int r17 = r15;
        String r03 = null;
        String r62 = null;
    L119:
        if (r15 >= r14) goto L131;
        String[] r12 = r34[r15].split("=");     // Catch: Throwable -> L80
        String[] r19 = r34;
        if (r12.length == 2) goto L124;
    L129:
        r15 = r15 + 1;     // Catch: Throwable -> L80
        r34 = r19;
        goto L119
    L124:
        if (r12[r17].equals("CHARSET") == false) goto L127;
        r03 = r12[1];     // Catch: Throwable -> L80
        goto L129
    L127:
        if (r12[r17].equals("ENCODING") == false) goto L129;
        r62 = r12[1];     // Catch: Throwable -> L80
        goto L129
    L131:
        if (r18[r17].startsWith("N") == false) goto L133;
        r7.name = r18[1].replace(';', ' ').trim();     // Catch: Throwable -> L80
    L134:
        if (r62 != null) goto L136;
    L142:
        r1 = r24;
        r0 = r16;
        r6 = r17;
    L92:
        r32 = 0;
        goto L10
    L136:
        if (r62.equalsIgnoreCase("QUOTED-PRINTABLE") == false) goto L142;
        byte[] r13 = decodeQuotedPrintable(getStringBytes(r7.name));     // Catch: Throwable -> L80
        if (r13 == null) goto L142;
        if (r13.length == 0) goto L142;
        r7.name = new String(r13, r03);     // Catch: Throwable -> L80
        goto L142
    L133:
        r7.name = r18[1];     // Catch: Throwable -> L80
        goto L134
    L109:
        if (r02[r6].startsWith("N") == true) goto L118;
        if (r02[r6].startsWith("ORG") == false) goto L116;
        if (TextUtils.isEmpty(r7.name) == true) goto L118;
    L116:
        if (r02[r6].startsWith("TEL") == false) goto L104;
        r7.phones.add(r02[1]);     // Catch: Throwable -> L80
    L104:
        r17 = r6;
        goto L142
    L100:
        r16 = r0;
        r02 = new String[]{r11.trim()};     // Catch: Throwable -> L80
        goto L102
    L90:
        if (r11.endsWith("=") == false) goto L93;
        r8 = new StringBuilder(r11.substring(r6, r11.length() - 1));     // Catch: Throwable -> L80
        goto L92
    L14:
        r9 = 1;
        goto L10
    L143:
        InputStream r162 = r0;
        int r172 = r6;
        r5.close();     // Catch: Throwable -> L80 Exception -> L146
        r162.close();     // Catch: Throwable -> L80 Exception -> L146
    L148:
        int r04 = r172;
        r3 = null;
    L149:
        if (r04 >= r4.size()) goto L170;
        VcardData r110 = (VcardData) r4.get(r04);     // Catch: Throwable -> L6
        if (r110.name != null) goto L154;
    L167:
        int r93 = r172;
    L168:
        r04 = r04 + 1;
        r172 = r93;
        goto L149
    L154:
        if (r110.phones.isEmpty() == true) goto L167;
        if (r3 != null) goto L157;
        r3 = new ArrayList();     // Catch: Throwable -> L6
    L157:
        String r52 = r110.phones.get(r172);     // Catch: Throwable -> L6
        int r63 = 0;
    L159:
        if (r63 >= r110.phones.size()) goto L164;
        String r72 = r110.phones.get(r63);     // Catch: Throwable -> L6
        r93 = 0;
        if (ContactsController.getInstance(r22).contactsByShortPhone.get(r72.substring(Math.max(0, r72.length() - 7))) != null) goto L162;
        r63 = r63 + 1;     // Catch: Throwable -> L6
        goto L159
    L162:
        r52 = r72;
    L165:
        TLRPC.TL_userContact_old2 r64 = new TLRPC.TL_userContact_old2();     // Catch: Throwable -> L6
        r64.phone = r52;     // Catch: Throwable -> L6
        r64.first_name = r110.name;     // Catch: Throwable -> L6
        r64.last_name = _UrlKt.FRAGMENT_ENCODE_SET;     // Catch: Throwable -> L6
        r64.id = 0;     // Catch: Throwable -> L6
        TLRPC.RestrictionReason r53 = new TLRPC.RestrictionReason();     // Catch: Throwable -> L6
        r53.text = r110.vcard.toString();     // Catch: Throwable -> L6
        r53.platform = _UrlKt.FRAGMENT_ENCODE_SET;     // Catch: Throwable -> L6
        r53.reason = _UrlKt.FRAGMENT_ENCODE_SET;     // Catch: Throwable -> L6
        r64.restriction_reason.add(r53);     // Catch: Throwable -> L6
        r3.add(r64);     // Catch: Throwable -> L6
        goto L168
    L164:
        r93 = 0;
    L170:
        return r3;
    L6:
        th = th;
    L169:
        FileLog.e(th);
        goto L170
    L146:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L80
    L80:
        th = th;
        r3 = null;
        goto L169
    L175:
        r0 = ApplicationLoader.applicationContext.getContentResolver().openAssetFileDescriptor(r21, "r").createInputStream();     // Catch: Throwable -> L6
        goto L9
    }

    @SuppressLint({"WrongConstant"})
    public static void lockOrientation(Activity r7) {
        if (r7 != null) goto L4;
        return;
    L4:
        if (prevOrientation == (-10)) goto L6;
        return;
    L6:
        if (isTabletInternal() == true) goto L46;
        prevOrientation = r7.getRequestedOrientation();     // Catch: Exception -> L38
        WindowManager r0 = (WindowManager) r7.getSystemService("window");     // Catch: Exception -> L38
        if (r0 != null) goto L11;
        return;
    L11:
        if (r0.getDefaultDisplay() == null) goto L45;
        int r02 = r0.getDefaultDisplay().getRotation();     // Catch: Exception -> L38
        int r1 = r7.getResources().getConfiguration().orientation;     // Catch: Exception -> L38
        if (r02 != 3) goto L20;
        if (r1 != 1) goto L17;
        r7.setRequestedOrientation(1);     // Catch: Exception -> L38
        return;
    L17:
        r7.setRequestedOrientation(8);     // Catch: Exception -> L38
        return;
    L20:
        if (r02 != 1) goto L27;
        if (r1 != 1) goto L24;
        r7.setRequestedOrientation(9);     // Catch: Exception -> L38
        return;
    L24:
        r7.setRequestedOrientation(0);     // Catch: Exception -> L38
        return;
    L27:
        if (r02 != 0) goto L33;
        if (r1 != 2) goto L31;
        r7.setRequestedOrientation(0);     // Catch: Exception -> L38
        return;
    L31:
        r7.setRequestedOrientation(1);     // Catch: Exception -> L38
        return;
    L33:
        if (r1 != 2) goto L36;
        r7.setRequestedOrientation(8);     // Catch: Exception -> L38
        return;
    L36:
        r7.setRequestedOrientation(9);     // Catch: Exception -> L38
        return;
    L45:
        return;
    L38:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    @SuppressLint({"WrongConstant"})
    public static void lockOrientation(Activity r1, int r2) {
        if (r1 != null) goto L4;
        return;
    L4:
        if (isTabletInternal() == true) goto L14;
        prevOrientation = r1.getRequestedOrientation();     // Catch: Exception -> L8
        r1.setRequestedOrientation(r2);     // Catch: Exception -> L8
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    public static void logFlagSecure() {
    }

    public static void makeAccessibilityAnnouncement(CharSequence r3) {
        if (TextUtils.isEmpty(r3) == true) goto L10;
        AccessibilityManager r0 = (AccessibilityManager) ApplicationLoader.applicationContext.getSystemService("accessibility");
        if (r0.isEnabled() == false) goto L9;
        AccessibilityEvent r1 = AccessibilityEvent.obtain();
        r1.setEventType(16384);
        r1.getText().add(r3);
        r0.sendAccessibilityEvent(r1);
        return;
    L9:
        return;
    }

    public static Bitmap makeBlurBitmap(View r2) {
        return makeBlurBitmap(r2, 6.0f, 7);
    }

    public static Bitmap makeBlurBitmap(View r5, float r6, int r7) {
        if (r5 != null) goto L5;
        return null;
    L5:
        int r0 = (int) (r5.getWidth() / r6);
        int r1 = (int) (r5.getHeight() / r6);
        Bitmap r2 = Bitmap.createBitmap(r0, r1, Bitmap.Config.ARGB_8888);
        Canvas r3 = new Canvas(r2);
        float r4 = 1.0f / r6;
        r3.scale(r4, r4);
        r3.drawColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        r5.draw(r3);
        Utilities.stackBlurBitmap(r2, Math.max(r7, Math.max(r0, r1) / 180));
        return r2;
    }

    public static SpannableStringBuilder makeClickable(CharSequence r2, int r3, Runnable r4, Theme.ResourcesProvider r5) {
        SpannableStringBuilder r0 = new SpannableStringBuilder(r2);
        if (r3 != 0) goto L5;
    L13:
        r0.setSpan(new AnonymousClass3(r3, r4), 0, r0.length(), 0);
        return r0;
    L5:
        if (r3 == 3) goto L13;
        if (r3 == 2) goto L13;
        if (r3 == 4) goto L13;
        r0.setSpan(new AnonymousClass4(r5), 0, r0.length(), 0);
        return r0;
    }

    public static SpannableStringBuilder makeClickable(CharSequence r2, Runnable r3) {
        return makeClickable(r2, 0, r3, null);
    }

    public static void makeGlobalBlurBitmap(Utilities.Callback<Bitmap> r2, float r3) {
        makeGlobalBlurBitmap(r2, r3, (int) r3, null, null);
    }

    public static void makeGlobalBlurBitmap(Utilities.Callback<Bitmap> r16, float r17, int r18, View r19, List<View> r20) {
        if (r16 == null) goto L57;
        List<View> r3 = allGlobalViews();
        if (r3 != null) goto L9;
        r16.run(null);
        return;
    L9:
        makingGlobalBlurBitmap = true;
        if (r19 != null) goto L17;
        int r8 = (int) (r7.x / r17);     // Catch: Throwable -> L13 Exception -> L15
        int r7 = (displaySize.y + statusBarHeight) + navigationBarHeight;     // Catch: Throwable -> L13 Exception -> L15
    L12:
        int r72 = (int) (r7 / r17);     // Catch: Throwable -> L13 Exception -> L15
        int[] r10 = new int[2];     // Catch: Throwable -> L13 Exception -> L15
        Bitmap r11 = Bitmap.createBitmap(r8, r72, Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L13 Exception -> L15
        Canvas r12 = new Canvas(r11);     // Catch: Throwable -> L13 Exception -> L15
        if (r19 == null) goto L21;
        r19.getLocationOnScreen(r10);     // Catch: Throwable -> L13 Exception -> L15
        r12.translate((-r10[0]) / r17, (-r10[1]) / r17);     // Catch: Throwable -> L13 Exception -> L15
    L21:
        float r0 = 1.0f / r17;     // Catch: Throwable -> L13 Exception -> L15
        r12.scale(r0, r0);     // Catch: Throwable -> L13 Exception -> L15
        r12.drawColor(Theme.getColor(Theme.key_windowBackgroundWhite));     // Catch: Throwable -> L13 Exception -> L15
        int r13 = 0;
    L23:
        if (r13 >= r3.size()) goto L42;
        View r02 = r3.get(r13);     // Catch: Throwable -> L13 Exception -> L15
        if ((r02 instanceof PipRoundVideoView.PipFrameLayout) == true) goto L41;
        if (r20 != null) goto L28;
    L30:
        ViewGroup.LayoutParams r14 = r02.getLayoutParams();     // Catch: Throwable -> L13 Exception -> L15
        if ((r14 instanceof WindowManager.LayoutParams) == false) goto L35;
        WindowManager.LayoutParams r142 = (WindowManager.LayoutParams) r14;     // Catch: Throwable -> L13 Exception -> L15
        if ((r142.flags & 2) == 0) goto L35;
        r12.drawColor(ColorUtils.setAlphaComponent(-16777216, (int) (r142.dimAmount * 255.0f)));     // Catch: Throwable -> L13 Exception -> L15
    L35:
        r12.save();     // Catch: Throwable -> L13 Exception -> L15
        r02.getLocationOnScreen(r10);     // Catch: Throwable -> L13 Exception -> L15
        r12.translate(r10[0] / r17, r10[1] / r17);     // Catch: Throwable -> L13 Exception -> L15
        r02.draw(r12);     // Catch: Throwable -> L13 Exception -> L38
    L40:
        r12.restore();     // Catch: Throwable -> L13 Exception -> L15
    L38:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L13 Exception -> L15
        goto L40
    L28:
        if (r20.contains(r02) == false) goto L30;
    L41:
        r13 = r13 + 1;     // Catch: Throwable -> L13 Exception -> L15
        goto L23
    L42:
        Utilities.stackBlurBitmap(r11, Math.max(r18, Math.max(r8, r72) / 180));     // Catch: Throwable -> L13 Exception -> L15
        r16.run(r11);     // Catch: Throwable -> L13 Exception -> L15
    L43:
        makingGlobalBlurBitmap = false;
        return;
    L17:
        r8 = (int) (r19.getWidth() / r17);     // Catch: Throwable -> L13 Exception -> L15
        r7 = r19.getHeight();     // Catch: Throwable -> L13 Exception -> L15
    L13:
        th = move-exception;
        makingGlobalBlurBitmap = false;
        throw th;
    L15:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L13
        r16.run(null);     // Catch: Throwable -> L13
        goto L43
    }

    private static String makeUrl(String r9, String[] r10) {
        int r0 = r10.length;
        boolean r1 = false;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L12;
        String r6 = r10[r2];
        String r3 = r9;
        if (r3.regionMatches(true, 0, r6, 0, r6.length()) == true) goto L6;
        r2 = r2 + 1;
        r9 = r3;
        goto L3
    L6:
        r1 = true;
        if (r3.regionMatches(false, 0, r6, 0, r6.length()) == true) goto L9;
        r9 = r6.concat(r3.substring(r6.length()));
        goto L12
    L9:
        r9 = r3;
    L12:
        if (r1 == false) goto L14;
        return r9;
    L14:
        return defaultUrlScheme(r9) + r9;
    }

    public static int multiplyAlphaComponent(int r1, float r2) {
        return ColorUtils.setAlphaComponent(r1, (int) (Color.alpha(r1) * r2));
    }

    public static void multiplyBrightnessColorMatrix(ColorMatrix r4, float r5) {
        if (r4 != null) goto L4;
        return;
    L4:
        r4.postConcat(new ColorMatrix(new float[]{r5, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, r5, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, r5, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
    }

    public static boolean needShowPasscode() {
        return needShowPasscode(false);
    }

    public static boolean needShowPasscode(boolean r5) {
        boolean r0 = ForegroundDetector.getInstance().isWasInBackground(r5);
        if (r5 == false) goto L5;
        ForegroundDetector.getInstance().resetBackgroundVar();
    L5:
        int r1 = (int) (SystemClock.elapsedRealtime() / 1000);
        if (BuildVars.LOGS_ENABLED == false) goto L12;
        if (r5 == false) goto L12;
        if (SharedConfig.passcodeHash.length() <= 0) goto L12;
        FileLog.d("wasInBackground = " + r0 + " appLocked = " + SharedConfig.appLocked + " autoLockIn = " + SharedConfig.autoLockIn + " lastPauseTime = " + SharedConfig.lastPauseTime + " uptime = " + r1);
    L12:
        if (SharedConfig.passcodeHash.length() <= 0) goto L29;
        if (r0 == true) goto L15;
        return false;
    L15:
        if (SharedConfig.appLocked == true) goto L27;
        int r52 = SharedConfig.autoLockIn;
        if (r52 == Integer.MAX_VALUE) goto L31;
        if (r52 == 0) goto L26;
        if (SharedConfig.lastPauseTime == 0) goto L26;
        if (SharedConfig.appLocked == true) goto L26;
        if ((SharedConfig.lastPauseTime + SharedConfig.autoLockIn) > r1) goto L26;
        return true;
    L26:
        if ((r1 + 5) >= SharedConfig.lastPauseTime) goto L35;
        return true;
    L35:
        return false;
    L31:
        return true;
    L27:
        return true;
    L29:
        return false;
    }

    public static void normalizeTimePart(StringBuilder r1, int r2) {
        if (r2 >= 10) goto L6;
        r1.append(MVEL.VERSION_SUB);
        r1.append(r2);
        return;
    L6:
        r1.append(r2);
    }

    public static void normalizeTimePart(StringBuilder r2, long r3) {
        if (r3 >= 10) goto L6;
        r2.append(MVEL.VERSION_SUB);
        r2.append(r3);
        return;
    L6:
        r2.append(r3);
    }

    public static void notifyDataSetChanged(RecyclerView r1) {
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.getAdapter() != null) goto L8;
        return;
    L8:
        if (r1.isComputingLayout() == false) goto L11;
        r1.post(new AndroidUtilities$$ExternalSyntheticLambda26(r1));
        return;
    L11:
        r1.getAdapter().notifyDataSetChanged();
    }

    public static String obtainLoginPhoneCall(String r8) {
        if (hasCallPermissions == true) goto L6;
        return null;
    L6:
        if (Build.VERSION.SDK_INT < 26) goto L9;
        String r0 = "date DESC";
    L41:
        Cursor r2 = ApplicationLoader.applicationContext.getContentResolver().query(CallLog.Calls.CONTENT_URI, new String[]{"number", "date"}, "type IN (3,1,5)", null, r0);     // Catch: Exception -> L25
    L39:
        if (r2.moveToNext() == false) goto L27;
        String r02 = r2.getString(0);     // Catch: Throwable -> L16
        long r3 = r2.getLong(1);     // Catch: Throwable -> L16
        if (BuildVars.LOGS_ENABLED == false) goto L19;
        FileLog.e("number = " + r02);     // Catch: Throwable -> L16
    L19:
        if (Math.abs(System.currentTimeMillis() - r3) >= DurationKt.MILLIS_IN_HOUR) goto L39;
        if (checkPhonePattern(r8, r02) == false) goto L39;
        r2.close();     // Catch: Exception -> L25
        return r02;
    L27:
        r2.close();     // Catch: Exception -> L25
    L36:
        return null;
    L16:
        th = move-exception;
        if (r2 == null) goto L49;
        r2.close();     // Catch: Throwable -> L32
    L50:
        throw th;     // Catch: Exception -> L25
    L32:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L25
        throw th;     // Catch: Exception -> L25
    L49:
        throw th;     // Catch: Exception -> L25
    L25:
        e = move-exception;
        FileLog.e(e);
        goto L36
    L9:
        r0 = "date DESC LIMIT 5";
        goto L41
    }

    public static void openDocument(MessageObject r19, Activity r20, BaseFragment r21) {
        if (r19 == null) goto L67;
        TLRPC.Document r4 = r19.getDocument();
        if (r4 != null) goto L9;
        return;
    L9:
        if (r19.messageOwner.media == null) goto L11;
        String r5 = FileLoader.getAttachFileName(r4);
    L12:
        String r6 = r19.messageOwner.attachPath;
        if (r6 != null) goto L15;
    L17:
        File r62 = null;
    L18:
        if (r62 != null) goto L20;
    L21:
        r62 = FileLoader.getInstance(UserConfig.selectedAccount).getPathToMessage(r19.messageOwner);
    L22:
        if (r62 != null) goto L24;
        return;
    L24:
        if (r62.exists() == true) goto L26;
        return;
    L26:
        if (r21 != null) goto L28;
    L61:
        Intent r11 = new Intent("android.intent.action.VIEW");     // Catch: Exception -> L57
        r11.setFlags(1);     // Catch: Exception -> L57
        MimeTypeMap r12 = MimeTypeMap.getSingleton();     // Catch: Exception -> L57
        int r13 = r5.lastIndexOf(46);     // Catch: Exception -> L57
        if (r13 == (-1)) goto L43;
        String r52 = r12.getMimeTypeFromExtension(r5.substring(r13 + 1).toLowerCase());     // Catch: Exception -> L57
        if (r52 != null) goto L44;
        r52 = r4.mime_type;     // Catch: Exception -> L57
        if (r52 == null) goto L43;
        if (r52.length() == 0) goto L43;
    L44:
        Uri r42 = FileProvider.getUriForFile(r20, ApplicationLoader.getApplicationId() + ".provider", r62);     // Catch: Exception -> L57
        if (r52 == null) goto L48;
        String r132 = r52;
    L49:
        r11.setDataAndType(r42, r132);     // Catch: Exception -> L57
        if (r52 != null) goto L62;
        r20.startActivityForResult(r11, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: Exception -> L57
        return;
    L62:
        r20.startActivityForResult(r11, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: Exception -> L54
    L69:
        return;
    L54:
        r11.setDataAndType(FileProvider.getUriForFile(r20, ApplicationLoader.getApplicationId() + ".provider", r62), "text/plain");     // Catch: Exception -> L57
        r20.startActivityForResult(r11, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: Exception -> L57
        return;
    L48:
        r132 = "text/plain";
    L43:
        r52 = null;
    L57:
        if (r20 == null) goto L71;
        AlertDialog.Builder r133 = new AlertDialog.Builder(r20);
        HashMap r1 = new HashMap();
        int r3 = Theme.key_dialogTopBackground;
        r1.put("info1", Integer.valueOf(r21.getThemedColor(r3)));
        r1.put("info2", Integer.valueOf(r21.getThemedColor(r3)));
        r133.setTopAnimation(R.raw.not_available, 52, false, r21.getThemedColor(r3), r1);
        r133.setTopAnimationIsNew(true);
        r133.setPositiveButton(LocaleController.getString(R.string.OK), null);
        r133.setMessage(LocaleController.formatString("NoHandleAppInstalled", R.string.NoHandleAppInstalled, new Object[]{r19.getDocument().mime_type}));
        r21.showDialog(r133.create());
        return;
    L71:
        return;
    L28:
        if (r62.getName().toLowerCase().endsWith("attheme") == false) goto L61;
        Theme.ThemeInfo r0 = Theme.applyThemeFile(r62, r19.getDocumentName(), null, true);
        if (r0 == null) goto L33;
        r21.presentFragment(new ThemePreviewActivity(r0));
        return;
    L33:
        AlertDialog.Builder r112 = new AlertDialog.Builder(r20);
        HashMap r02 = new HashMap();
        int r14 = Theme.key_dialogTopBackground;
        r02.put("info1", Integer.valueOf(r21.getThemedColor(r14)));
        r02.put("info2", Integer.valueOf(r21.getThemedColor(r14)));
        r112.setTopAnimation(R.raw.not_available, 52, false, r21.getThemedColor(r14), r02);
        r112.setTopAnimationIsNew(true);
        r112.setMessage(LocaleController.getString(R.string.IncorrectTheme));
        r112.setPositiveButton(LocaleController.getString(R.string.OK), null);
        r21.showDialog(r112.create());
        return;
    L20:
        if (r62.exists() == true) goto L22;
    L15:
        if (r6.length() == 0) goto L17;
        r62 = new File(r19.messageOwner.attachPath);
        goto L18
    L11:
        r5 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L12
    }

    public static boolean openForView(File r6, String r7, String r8, Activity r9, Theme.ResourcesProvider r10, boolean r11) {
        if (r6 != null) goto L4;
        return false;
    L4:
        if (r6.exists() == false) goto L49;
        Intent r0 = new Intent("android.intent.action.VIEW");
        r0.setFlags(1);
        MimeTypeMap r2 = MimeTypeMap.getSingleton();
        if (r7 != null) goto L8;
        int r4 = -1;
    L10:
        if (r4 == (-1)) goto L21;
        String r72 = r7.substring(r4 + 1);
        if (r11 == true) goto L14;
    L16:
        String r73 = r2.getMimeTypeFromExtension(r72.toLowerCase());
        if (r73 != null) goto L22;
        if (r8 == null) goto L21;
        if (r8.length() == 0) goto L21;
    L23:
        if (r8 != null) goto L25;
    L34:
        Uri r74 = FileProvider.getUriForFile(r9, ApplicationLoader.getApplicationId() + ".provider", r6);
        if (r8 == null) goto L37;
        String r22 = r8;
    L38:
        r0.setDataAndType(r74, r22);
        if (r8 != null) goto L47;
        r9.startActivityForResult(r0, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);
    L44:
        return true;
    L47:
        r9.startActivityForResult(r0, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: Exception -> L42
    L42:
        r0.setDataAndType(FileProvider.getUriForFile(r9, ApplicationLoader.getApplicationId() + ".provider", r6), "text/plain");
        r9.startActivityForResult(r0, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);
        goto L44
    L37:
        r22 = "text/plain";
        goto L38
    L25:
        if (r8.equals("application/vnd.android.package-archive") == false) goto L34;
        if (r11 == false) goto L29;
        return true;
    L29:
        if (Build.VERSION.SDK_INT < 26) goto L34;
        if (ApplicationLoader.applicationContext.getPackageManager().canRequestPackageInstalls() == true) goto L34;
        AlertsCreator.createApkRestrictedDialog(r9, r10).show();
        return true;
    L22:
        r8 = r73;
        goto L23
    L14:
        if (MessageObject.isV(r72) == false) goto L16;
        return true;
    L21:
        r8 = null;
        goto L23
    L8:
        r4 = r7.lastIndexOf(46);
        goto L10
    L49:
        return false;
    }

    public static boolean openForView(MessageObject r8, Activity r9, Theme.ResourcesProvider r10, boolean r11) {
        String r0 = r8.messageOwner.attachPath;
        String r1 = null;
        if (r0 != null) goto L5;
    L7:
        File r02 = null;
    L8:
        if (r02 != null) goto L10;
    L13:
        r02 = FileLoader.getInstance(r8.currentAccount).getPathToMessage(r8.messageOwner);
    L12:
        File r2 = r02;
        int r03 = r8.type;
        if (r03 == 9) goto L19;
        if (r03 == 0) goto L19;
    L21:
        return openForView(r2, r8.getFileName(), r1, r9, r10, r11);
    L19:
        r1 = r8.getMimeType();
        goto L21
    L10:
        if (r02.exists() == true) goto L12;
    L5:
        if (r0.length() == 0) goto L7;
        r02 = new File(r8.messageOwner.attachPath);
        goto L8
    }

    public static boolean openForView(TLObject r8, Activity r9) {
        if (r8 == null) goto L33;
        if (r9 == null) goto L33;
        String r1 = FileLoader.getAttachFileName(r8);
        File r2 = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r8, true);
        if (r2 == null) goto L33;
        if (r2.exists() == false) goto L33;
        Intent r0 = new Intent("android.intent.action.VIEW");
        r0.setFlags(1);
        MimeTypeMap r4 = MimeTypeMap.getSingleton();
        int r5 = r1.lastIndexOf(46);
        String r7 = null;
        if (r5 == (-1)) goto L22;
        String r12 = r4.getMimeTypeFromExtension(r1.substring(r5 + 1).toLowerCase());
        if (r12 == null) goto L15;
    L21:
        r7 = r12;
        goto L22
    L15:
        if ((r8 instanceof TLRPC.TL_document) == false) goto L17;
        r12 = ((TLRPC.TL_document) r8).mime_type;
    L17:
        if (r12 == null) goto L22;
        if (r12.length() != 0) goto L21;
    L22:
        Uri r82 = FileProvider.getUriForFile(r9, ApplicationLoader.getApplicationId() + ".provider", r2);
        if (r7 == null) goto L25;
        String r52 = r7;
    L26:
        r0.setDataAndType(r82, r52);
        if (r7 != null) goto L34;
        r9.startActivityForResult(r0, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);
    L32:
        return true;
    L34:
        r9.startActivityForResult(r0, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: Exception -> L30
    L30:
        r0.setDataAndType(FileProvider.getUriForFile(r9, ApplicationLoader.getApplicationId() + ".provider", r2), "text/plain");
        r9.startActivityForResult(r0, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);
        goto L32
    L25:
        r52 = "text/plain";
    L33:
        return false;
    }

    public static boolean openForView(TLRPC.Document r6, Activity r7) {
        String r1 = FileLoader.getAttachFileName(r6);
        return openForView(FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r6, true), r1, r6.mime_type, r7, null, false);
    }

    public static void openSharing(BaseFragment r8, String r9) {
        if (r8 != null) goto L4;
        return;
    L4:
        if (r8.getParentActivity() == null) goto L9;
        r8.showDialog(new ShareAlert(r8.getParentActivity(), null, r9, false, r9, false));
        return;
    }

    public static long pack(int r4, int r5) {
        return (r5 & 4294967295L) | (r4 << 32);
    }

    public static SpannableStringBuilder premiumText(String r2, Runnable r3) {
        return replaceSingleTag(r2, -1, 2, r3);
    }

    public static void printLayoutRequestedChain(View r5) {
        if (r5 != null) goto L5;
        FileLog.d("LayoutCheck view == null");
        return;
    L5:
        int r0 = 0;
    L6:
        if (r5 == null) goto L12;
        Object r1 = r5.getParent();
        FileLog.d("LayoutCheck level=" + r0 + ", view=" + r5.getClass().getSimpleName() + "@" + Integer.toHexString(System.identityHashCode(r5)) + ", isLayoutRequested=" + r5.isLayoutRequested());
        if ((r1 instanceof View) == false) goto L9;
        r5 = (View) r1;
        r0 = r0 + 1;
        goto L6
    L9:
        if (r1 == null) goto L12;
        FileLog.d("LayoutCheck level=" + (r0 + 1) + ", parent=" + r1.getClass().getSimpleName() + " (not a View)");
    L12:
        FileLog.d("LayoutCheck");
    }

    public static void printStackTrace(String r1) {
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L5;
        return;
    L5:
        printStackTrace(Thread.currentThread().getStackTrace(), r1);
    }

    public static void printStackTrace(StackTraceElement[] r4, String r5) {
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L5;
        return;
    L5:
        String r52 = "[" + r5 + "]";
        int r0 = Math.min(r4.length, 14);
        int r1 = 3;
    L6:
        if (r1 >= r0) goto L8;
        FileLog.d(r52 + " " + r4[r1]);
        r1 = r1 + 1;
        goto L6
    L8:
        FileLog.d(r52);
    }

    private static void pruneOverlaps(ArrayList<LinkSpec> r10) {
        Collections.sort(r10, new AndroidUtilities$$ExternalSyntheticLambda11());
        int r0 = r10.size();
        int r1 = 0;
    L4:
        if (r1 >= (r0 - 1)) goto L22;
        LinkSpec r2 = r10.get(r1);
        int r3 = r1 + 1;
        LinkSpec r4 = r10.get(r3);
        int r5 = r2.start;
        int r6 = r4.start;
        if (r5 > r6) goto L21;
        int r22 = r2.end;
        if (r22 <= r6) goto L21;
        int r42 = r4.end;
        if (r42 > r22) goto L13;
    L11:
        int r23 = r3;
    L19:
        if (r23 == (-1)) goto L21;
        r10.remove(r23);
        r0 = r0 - 1;
        goto L4
    L13:
        if ((r22 - r5) > (r42 - r6)) goto L11;
        if ((r22 - r5) >= (r42 - r6)) goto L18;
        r23 = r1;
        goto L19
    L18:
        r23 = -1;
    L21:
        r1 = r3;
        goto L4
    }

    public static void quietSleep(long r0) {
        Thread.sleep(r0);     // Catch: InterruptedException -> L4
        return;
    }

    public static <T> T randomOf(ArrayList<T> r2) {
        if (r2.isEmpty() == false) goto L7;
        return null;
    L7:
        return r2.get(Math.abs(Utilities.fastRandom.nextInt() % r2.size()));
    }

    public static String readRes(int r1) {
        return readRes(null, r1);
    }

    public static String readRes(File r1) {
        return readRes(r1, 0);
    }

    public static String readRes(File r7, int r8) {
        byte[] r0 = readBufferLocal.get();
        if (r0 != null) goto L37;
        r0 = new byte[65536];
        readBufferLocal.set(r0);
    L37:
        if (r7 == null) goto L9;
        InputStream r82 = new FileInputStream(r7);     // Catch: Throwable -> L8
    L33:
        byte[] r72 = bufferLocal.get();     // Catch: Throwable -> L29
        if (r72 != null) goto L13;
        r72 = new byte[4096];     // Catch: Throwable -> L29
        bufferLocal.set(r72);     // Catch: Throwable -> L29
    L13:
        int r3 = 0;
    L14:
        int r4 = r82.read(r72, 0, r72.length);     // Catch: Throwable -> L29
        if (r4 < 0) goto L31;
        int r6 = r3 + r4;     // Catch: Throwable -> L29
        if (r0.length >= r6) goto L19;
        byte[] r5 = new byte[r0.length * 2];     // Catch: Throwable -> L29
        System.arraycopy(r0, 0, r5, 0, r3);     // Catch: Throwable -> L29
        readBufferLocal.set(r5);     // Catch: Throwable -> L29
        r0 = r5;
    L19:
        if (r4 <= 0) goto L14;
        System.arraycopy(r72, 0, r0, r3, r4);     // Catch: Throwable -> L29
        r3 = r6;
        goto L14
    L31:
        r82.close();     // Catch: Throwable -> L28
    L24:
        return new String(r0, 0, r3);
    L25:
        if (r82 != null) goto L35;
    L27:
        return null;
    L35:
        r82.close();     // Catch: Throwable -> L30
        goto L27
    L9:
        r82 = ApplicationLoader.applicationContext.getResources().openRawResource(r8);     // Catch: Throwable -> L8
    L8:
        r82 = null;
        goto L25
    }

    public static void recycleBitmap(Bitmap r0) {
        recycleBitmaps(Collections.singletonList(r0));
    }

    public static void recycleBitmaps(List<Bitmap> r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        if (r4.isEmpty() == true) goto L21;
        ArrayList r0 = new ArrayList();
        int r1 = 0;
    L7:
        if (r1 >= r4.size()) goto L14;
        Bitmap r2 = r4.get(r1);
        if (r2 == null) goto L13;
        if (r2.isRecycled() == true) goto L13;
        r0.add(new WeakReference(r2));
    L13:
        r1 = r1 + 1;
        goto L7
    L14:
        runOnUIThread(new AndroidUtilities$$ExternalSyntheticLambda13(r0), 36);
        return;
    }

    public static Typeface regular() {
        return getTypeface(TYPEFACE_ROBOTO_REGULAR);
    }

    public static void removeAdjustResize(Activity r1, int r2) {
        if (r1 != null) goto L4;
        return;
    L4:
        if (isTablet() == false) goto L7;
        return;
    L7:
        if (adjustOwnerClassGuid != r2) goto L11;
        r1.getWindow().setSoftInputMode(32);
        return;
    }

    public static void removeAltFocusable(Activity r1, int r2) {
        if (r1 != null) goto L5;
        return;
    L5:
        if (altFocusableClassGuid != r2) goto L8;
        r1.getWindow().clearFlags(131072);
        return;
    }

    public static String removeDiacritics(String r1) {
        if (r1 != null) goto L5;
        return null;
    L5:
        Pattern r0 = REMOVE_MULTIPLE_DIACRITICS;
        if (r0 == null) goto L10;
        Matcher r02 = r0.matcher(r1);
        if (r02 == null) goto L10;
        return r02.replaceAll("$1");
    L10:
        return r1;
    }

    public static void removeFromParent(View r1) {
        if (r1 != null) goto L4;
        return;
    L4:
        if (r1.getParent() == null) goto L8;
        ((ViewGroup) r1.getParent()).removeView(r1);
        return;
    }

    public static String removeRTL(String r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        if (REMOVE_RTL != null) goto L8;
        REMOVE_RTL = Pattern.compile("[\\u200E\\u200F\\u202A-\\u202E]");
    L8:
        Matcher r0 = REMOVE_RTL.matcher(r1);
        if (r0 != null) goto L12;
        return r1;
    L12:
        return r0.replaceAll(_UrlKt.FRAGMENT_ENCODE_SET);
    }

    public static CharSequence removeSpans(CharSequence r2, Class r3) {
        if ((r2 instanceof Spannable) == true) goto L5;
        return r2;
    L5:
        Spannable r22 = (Spannable) r2;
        int r1 = 0;
        Object[] r32 = r22.getSpans(0, r22.length(), r3);
    L7:
        if (r1 >= r32.length) goto L9;
        r22.removeSpan(r32[r1]);
        r1 = r1 + 1;
        goto L7
    L9:
        return r22;
    }

    public static CharSequence replaceArrows(CharSequence r3, boolean r4) {
        return replaceArrows(r3, r4, dp(2.6666667f), 0.0f, 1.0f);
    }

    public static CharSequence replaceArrows(CharSequence r1, boolean r2, float r3, float r4) {
        return replaceArrows(r1, r2, r3, r4, 1.0f);
    }

    public static CharSequence replaceArrows(CharSequence r6, boolean r7, float r8, float r9, float r10) {
        return replaceArrows(r6, r7, r8, r9, r10, R.drawable.msg_mini_forumarrow);
    }

    public static CharSequence replaceArrows(CharSequence r8, boolean r9, float r10, float r11, float r12, int r13) {
        ColoredImageSpan r0 = new ColoredImageSpan(r13, 0);
        float r122 = r12 * 0.88f;
        r0.setScale(r122, r122);
        r0.translate(-r10, r11);
        r0.spaceScaleX = 0.8f;
        if (r9 == false) goto L5;
        r0.useLinkPaintColor = r9;
    L5:
        SpannableString r3 = new SpannableString(" >");
        r3.setSpan(r0, r3.length() - 1, r3.length(), 33);
        CharSequence r82 = replaceMultipleCharSequence(" >", r8, r3);
        SpannableString r32 = new SpannableString(">");
        r32.setSpan(r0, 0, 1, 33);
        CharSequence r83 = replaceMultipleCharSequence(">", r82, r32);
        ColoredImageSpan r02 = new ColoredImageSpan(r13, 0);
        r02.setScale(r122, r122);
        r02.translate(r10, r11);
        r02.rotate(180.0f);
        r02.spaceScaleX = 0.8f;
        if (r9 == false) goto L8;
        r02.useLinkPaintColor = r9;
    L8:
        SpannableString r92 = new SpannableString("<");
        r92.setSpan(r02, 0, 1, 33);
        return replaceMultipleCharSequence("<", r83, r92);
    }

    public static SpannableStringBuilder replaceCharSequence(String r1, CharSequence r2, CharSequence r3) {
        if ((r2 instanceof SpannableStringBuilder) == false) goto L5;
        SpannableStringBuilder r0 = (SpannableStringBuilder) r2;
    L6:
        int r22 = TextUtils.indexOf(r2, r1);
        if (r22 < 0) goto L9;
        r0.replace(r22, r1.length() + r22, r3);
    L9:
        return r0;
    L5:
        r0 = new SpannableStringBuilder(r2);
        goto L6
    }

    public static SpannableStringBuilder replaceLinks(String r1, Theme.ResourcesProvider r2) {
        return replaceLinks(r1, r2, null);
    }

    public static SpannableStringBuilder replaceLinks(String r6, Theme.ResourcesProvider r7, Runnable r8) {
        if (linksPattern != null) goto L5;
        linksPattern = Pattern.compile("\\[(.+?)\\]\\((.+?)\\)");
    L5:
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        Matcher r1 = linksPattern.matcher(r6);
        int r2 = 0;
    L7:
        if (r1.find() == false) goto L9;
        r0.append(r6, r2, r1.start());
        String r22 = r1.group(1);
        String r3 = r1.group(2);
        r0.append(r22);
        int r4 = r0.length() - r22.length();
        int r23 = r0.length();
        r0.setSpan(new AnonymousClass11(r8, r3, r7), r4, r23, 33);
        r2 = r1.end();
        goto L7
    L9:
        r0.append(r6, r2, r6.length());
        return r0;
    }

    public static CharSequence replaceMultipleCharSequence(String r2, CharSequence r3, CharSequence r4) {
        if ((r3 instanceof SpannableStringBuilder) == false) goto L5;
        SpannableStringBuilder r0 = (SpannableStringBuilder) r3;
    L6:
        int r32 = TextUtils.indexOf(r3, r2, 0);
    L7:
        if (r32 < 0) goto L9;
        r0.replace(r32, r2.length() + r32, r4);
        r32 = TextUtils.indexOf(r0, r2, r32 + 1);
        goto L7
    L9:
        return r0;
    L5:
        r0 = new SpannableStringBuilder(r3);
        goto L6
    }

    public static SpannableStringBuilder replaceMultipleTags(String r5, Runnable... r6) {
        SpannableStringBuilder r0 = new SpannableStringBuilder(r5);
        int r52 = 0;
    L4:
        if (r52 >= r6.length) goto L10;
        Runnable r1 = r6[r52];
        int r3 = charSequenceIndexOf(r0, "**");
        int r4 = r3 + 2;
        int r2 = charSequenceIndexOf(r0, "**", r4);
        if (r3 < 0) goto L10;
        if (r2 < 0) goto L10;
        r0.delete(r3, r4);
        int r42 = r2 - 2;
        r0.delete(r42, r2);
        r0.setSpan(new AnonymousClass5(r1), r3, r42, 33);
        r52 = r52 + 1;
    L10:
        return r0;
    }

    public static CharSequence replaceNewLines(CharSequence r6) {
        int r2 = 0;
        if ((r6 instanceof StringBuilder) == false) goto L12;
        StringBuilder r0 = (StringBuilder) r6;
        int r4 = r6.length();
    L5:
        if (r2 >= r4) goto L10;
        if (r6.charAt(r2) != '\n') goto L9;
        r0.setCharAt(r2, ' ');
    L9:
        r2 = r2 + 1;
        goto L5
    L10:
        return r6;
    L12:
        if ((r6 instanceof SpannableStringBuilder) == false) goto L21;
        SpannableStringBuilder r02 = (SpannableStringBuilder) r6;
        int r1 = r6.length();
    L14:
        if (r2 >= r1) goto L19;
        if (r6.charAt(r2) != '\n') goto L18;
        r02.replace(r2, r2 + 1, " ");
    L18:
        r2 = r2 + 1;
        goto L14
    L19:
        return r02;
    L21:
        if ((r6 instanceof SpannableString) == true) goto L23;
        if (r6 != null) goto L36;
        return null;
    L36:
        return r6.toString().replace('\n', ' ');
    L23:
        if (TextUtils.indexOf(r6, '\n') >= 0) goto L25;
        return r6;
    L25:
        SpannableStringBuilder r03 = new SpannableStringBuilder(r6);
        int r12 = r6.length();
    L26:
        if (r2 >= r12) goto L31;
        if (r6.charAt(r2) != '\n') goto L30;
        r03.replace(r2, r2 + 1, " ");
    L30:
        r2 = r2 + 1;
        goto L26
    L31:
        return r03;
    }

    public static SpannableStringBuilder replaceSingleLink(String r1, int r2) {
        return replaceSingleLink(r1, r2, null);
    }

    public static SpannableStringBuilder replaceSingleLink(String r4, int r5, Runnable r6) {
        int r1 = r4.indexOf("**");
        int r2 = r4.indexOf("**", r1 + 1);
        String r42 = r4.replace("**", _UrlKt.FRAGMENT_ENCODE_SET);
        if (r1 < 0) goto L8;
        if (r2 < 0) goto L8;
        int r22 = r2 - r1;
        if (r22 <= 2) goto L8;
        int r23 = r22 - 2;
    L9:
        SpannableStringBuilder r3 = new SpannableStringBuilder(r42);
        if (r1 < 0) goto L15;
        if (r6 == null) goto L14;
        r3.setSpan(new AnonymousClass6(r5, r6), r1, r23 + r1, 0);
        return r3;
    L14:
        r3.setSpan(new AnonymousClass7(r5), r1, r23 + r1, 0);
    L15:
        return r3;
    L8:
        r1 = -1;
        r23 = 0;
        goto L9
    }

    public static SpannableStringBuilder replaceSingleLinkBold(String r1, int r2) {
        return replaceSingleLinkBold(r1, r2, null);
    }

    public static SpannableStringBuilder replaceSingleLinkBold(String r4, int r5, Runnable r6) {
        int r1 = r4.indexOf("**");
        int r2 = r4.indexOf("**", r1 + 1);
        String r42 = r4.replace("**", _UrlKt.FRAGMENT_ENCODE_SET);
        if (r1 < 0) goto L8;
        if (r2 < 0) goto L8;
        int r22 = r2 - r1;
        if (r22 <= 2) goto L8;
        int r23 = r22 - 2;
    L9:
        SpannableStringBuilder r3 = new SpannableStringBuilder(r42);
        if (r1 < 0) goto L15;
        if (r6 == null) goto L14;
        r3.setSpan(new AnonymousClass8(r5, r6), r1, r23 + r1, 0);
        return r3;
    L14:
        r3.setSpan(new AnonymousClass9(r5), r1, r23 + r1, 0);
    L15:
        return r3;
    L8:
        r1 = -1;
        r23 = 0;
        goto L9
    }

    public static SpannableStringBuilder replaceSingleTag(String r1, int r2, int r3, Runnable r4) {
        return replaceSingleTag(r1, r2, r3, r4, null);
    }

    public static SpannableStringBuilder replaceSingleTag(String r9, int r10, int r11, Runnable r12, Theme.ResourcesProvider r13) {
        int r1 = r9.indexOf("**");
        int r2 = r9.indexOf("**", r1 + 1);
        String r92 = r9.replace("**", _UrlKt.FRAGMENT_ENCODE_SET);
        if (r1 < 0) goto L8;
        if (r2 < 0) goto L8;
        int r22 = r2 - r1;
        if (r22 <= 2) goto L8;
        int r23 = r22 - 2;
    L10:
        if (r1 >= 0) goto L15;
        int r5 = r92.indexOf("[");
        int r6 = r5 + 1;
        int r7 = r92.indexOf("]()", r6);
        if (r5 < 0) goto L15;
        if (r7 <= r5) goto L15;
        r23 = (r7 - r5) - 1;
        r92 = r92.substring(0, r5) + r92.substring(r6, r7) + r92.substring(r7 + 3);
        r1 = r5;
    L15:
        SpannableStringBuilder r52 = new SpannableStringBuilder(r92);
        if (r1 < 0) goto L28;
        if (r11 != 3) goto L19;
        int r93 = r1 + r23;
        r52.replace(r1, r93, replaceMultipleCharSequence(" ", r52.subSequence(r1, r93), " "));
    L19:
        if (r11 == 0) goto L27;
        if (r11 == 3) goto L27;
        if (r11 == 2) goto L27;
        if (r11 == 4) goto L27;
        r52.setSpan(new AnonymousClass2(r13), r1, r23 + r1, 0);
        return r52;
    L27:
        r52.setSpan(new AnonymousClass1(r11, r10, r13, r12), r1, r23 + r1, 0);
    L28:
        return r52;
    L8:
        r1 = -1;
        r23 = 0;
        goto L10
    }

    public static SpannableStringBuilder replaceSingleTag(String r2, Runnable r3) {
        return replaceSingleTag(r2, -1, 0, r3);
    }

    public static SpannableStringBuilder replaceTags(SpannableStringBuilder r7) {
        ArrayList r2 = new ArrayList();     // Catch: Exception -> L9
    L4:
        int r3 = charSequenceIndexOf(r7, "**");     // Catch: Exception -> L9
        if (r3 == (-1)) goto L11;
        r7.replace(r3, r3 + 2, _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Exception -> L9
        int r4 = charSequenceIndexOf(r7, "**");     // Catch: Exception -> L9
        if (r4 < 0) goto L4;
        r7.replace(r4, r4 + 2, _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Exception -> L9
        r2.add(Integer.valueOf(r3));     // Catch: Exception -> L9
        r2.add(Integer.valueOf(r4));     // Catch: Exception -> L9
        goto L4
    L11:
        SpannableStringBuilder r0 = new SpannableStringBuilder(r7);     // Catch: Exception -> L9
        int r1 = 0;
    L12:
        if (r1 >= (r2.size() / 2)) goto L16;
        TypefaceSpan r32 = new TypefaceSpan(bold());     // Catch: Exception -> L9
        int r42 = r1 * 2;     // Catch: Exception -> L9
        r0.setSpan(r32, ((Integer) r2.get(r42)).intValue(), ((Integer) r2.get(r42 + 1)).intValue(), 33);     // Catch: Exception -> L9
        r1 = r1 + 1;
        goto L12
    L16:
        return r0;
    L9:
        e = move-exception;
        FileLog.e(e);
        return r7;
    }

    public static SpannableStringBuilder replaceTags(String r1) {
        return replaceTags(r1, 11);
    }

    public static SpannableStringBuilder replaceTags(String r9, int r10) {
        StringBuilder r1 = new StringBuilder(r9);     // Catch: Exception -> L10
        if ((r10 & 1) == 0) goto L15;
    L5:
        int r2 = r1.indexOf("<br>");     // Catch: Exception -> L10
        if (r2 == (-1)) goto L12;
        r1.replace(r2, r2 + 4, "\n");     // Catch: Exception -> L10
    L12:
        int r22 = r1.indexOf("<br/>");     // Catch: Exception -> L10
        if (r22 == (-1)) goto L15;
        r1.replace(r22, r22 + 5, "\n");     // Catch: Exception -> L10
    L15:
        ArrayList r23 = new ArrayList();     // Catch: Exception -> L10
        if ((r10 & 2) == 0) goto L30;
    L18:
        int r4 = r1.indexOf("<b>");     // Catch: Exception -> L10
        if (r4 == (-1)) goto L24;
        r1.replace(r4, r4 + 3, _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Exception -> L10
        int r7 = r1.indexOf("</b>");     // Catch: Exception -> L10
        if (r7 != (-1)) goto L23;
        r7 = r1.indexOf("<b>");     // Catch: Exception -> L10
    L23:
        r1.replace(r7, r7 + 4, _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Exception -> L10
        r23.add(Integer.valueOf(r4));     // Catch: Exception -> L10
        r23.add(Integer.valueOf(r7));     // Catch: Exception -> L10
    L24:
        int r0 = r1.indexOf("**");     // Catch: Exception -> L10
        if (r0 == (-1)) goto L30;
        r1.replace(r0, r0 + 2, _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Exception -> L10
        int r42 = r1.indexOf("**");     // Catch: Exception -> L10
        if (r42 < 0) goto L24;
        r1.replace(r42, r42 + 2, _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Exception -> L10
        r23.add(Integer.valueOf(r0));     // Catch: Exception -> L10
        r23.add(Integer.valueOf(r42));     // Catch: Exception -> L10
    L30:
        if ((r10 & 8) == 0) goto L36;
    L31:
        int r102 = r1.indexOf("**");     // Catch: Exception -> L10
        if (r102 == (-1)) goto L36;
        r1.replace(r102, r102 + 2, _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Exception -> L10
        int r02 = r1.indexOf("**");     // Catch: Exception -> L10
        if (r02 < 0) goto L31;
        r1.replace(r02, r02 + 2, _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Exception -> L10
        r23.add(Integer.valueOf(r102));     // Catch: Exception -> L10
        r23.add(Integer.valueOf(r02));     // Catch: Exception -> L10
    L36:
        SpannableStringBuilder r103 = new SpannableStringBuilder(r1);     // Catch: Exception -> L10
        int r03 = 0;
    L37:
        if (r03 >= (r23.size() / 2)) goto L41;
        TypefaceSpan r12 = new TypefaceSpan(bold());     // Catch: Exception -> L10
        int r3 = r03 * 2;     // Catch: Exception -> L10
        r103.setSpan(r12, ((Integer) r23.get(r3)).intValue(), ((Integer) r23.get(r3 + 1)).intValue(), 33);     // Catch: Exception -> L10
        r03 = r03 + 1;
        goto L37
    L41:
        return r103;
    L10:
        e = move-exception;
        FileLog.e(e);
        return new SpannableStringBuilder(r9);
    }

    public static CharSequence replaceTags(CharSequence r1) {
        if ((r1 instanceof SpannableStringBuilder) == false) goto L7;
        return replaceTags((SpannableStringBuilder) r1);
    L7:
        return replaceTags(new SpannableStringBuilder(r1));
    }

    public static CharSequence replaceTwoNewLinesToOne(CharSequence r10) {
        char[] r0 = new char[2];
        if ((r10 instanceof StringBuilder) == false) goto L15;
        StringBuilder r1 = (StringBuilder) r10;
        int r6 = r10.length();
        int r7 = 0;
    L6:
        if (r7 >= (r6 - 2)) goto L13;
        int r8 = r7 + 2;
        r1.getChars(r7, r8, r0, 0);
        if (r0[0] != '\n') goto L12;
        if (r0[1] != '\n') goto L12;
        r1 = r1.replace(r7, r8, "\n");
        r7 = r7 - 1;
        r6 = r6 - 1;
    L12:
        r7 = r7 + 1;
        goto L6
    L13:
        return r10;
    L15:
        if ((r10 instanceof SpannableStringBuilder) == false) goto L27;
        SpannableStringBuilder r12 = (SpannableStringBuilder) r10;
        int r62 = r10.length();
        int r72 = 0;
    L18:
        if (r72 >= (r62 - 2)) goto L25;
        int r82 = r72 + 2;
        r12.getChars(r72, r82, r0, 0);
        if (r0[0] != '\n') goto L24;
        if (r0[1] != '\n') goto L24;
        r12 = r12.replace(r72, r82, "\n");
        r72 = r72 - 1;
        r62 = r62 - 1;
    L24:
        r72 = r72 + 1;
        goto L18
    L25:
        return r10;
    L27:
        if ((r10 instanceof SpannableString) == false) goto L42;
        if (TextUtils.indexOf(r10, "\n\n") >= 0) goto L31;
        return r10;
    L31:
        SpannableStringBuilder r13 = new SpannableStringBuilder(r10);
        int r102 = r10.length();
        int r63 = 0;
    L33:
        if (r63 >= (r102 - 2)) goto L40;
        int r73 = r63 + 2;
        r13.getChars(r63, r73, r0, 0);
        if (r0[0] != '\n') goto L39;
        if (r0[1] != '\n') goto L39;
        r13 = r13.replace(r63, r73, "\n");
        r63 = r63 - 1;
        r102 = r102 - 1;
    L39:
        r63 = r63 + 1;
        goto L33
    L40:
        return r13;
    L42:
        return r10.toString().replace("\n\n", "\n");
    }

    public static void requestAdjustNothing(Activity r1, int r2) {
        if (r1 != null) goto L4;
        return;
    L4:
        if (isTablet() == true) goto L9;
        r1.getWindow().setSoftInputMode(48);
        adjustOwnerClassGuid = r2;
        return;
    }

    public static void requestAdjustResize(Activity r0, int r1) {
        if (r0 != null) goto L4;
        return;
    L4:
        requestAdjustResize(r0.getWindow(), r1);
    }

    public static void requestAdjustResize(Window r1, int r2) {
        if (r1 != null) goto L4;
        return;
    L4:
        if (isTablet() == true) goto L9;
        r1.setSoftInputMode(16);
        adjustOwnerClassGuid = r2;
        return;
    }

    public static void requestAltFocusable(Activity r1, int r2) {
        if (r1 != null) goto L4;
        return;
    L4:
        r1.getWindow().setFlags(131072, 131072);
        altFocusableClassGuid = r2;
    }

    public static void resetPictureInPictureParams(Activity r3) {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 26) goto L9;
        PictureInPictureParams.Builder r1 = AndroidUtilities$$ExternalSyntheticApiModelOutline0.m();
        r1.setSourceRectHint(null);
        r1.setAspectRatio(null);
        if (r0 < 31) goto L7;
        r1.setAutoEnterEnabled(false);
    L7:
        setPictureInPictureParams(r3, r1.build());
        return;
    }

    public static void resetTabletFlag() {
        if (wasTablet != null) goto L5;
        wasTablet = Boolean.valueOf(isTabletInternal());
    L5:
        isTablet = null;
        SharedConfig.updateTabletConfig();
    }

    public static void resetWasTabletFlag() {
        wasTablet = null;
    }

    public static double[] rgbToHsv(int r18, int r19, int r20) {
        double r0 = r18 / 255.0d;
        double r4 = r19 / 255.0d;
        double r6 = r20 / 255.0d;
        if (r0 > r4) goto L5;
    L7:
        double r8 = Math.max(r4, r6);
    L9:
        if (r0 < r4) goto L11;
    L13:
        double r10 = Math.min(r4, r6);
    L14:
        double r12 = r8 - r10;
        double r14 = 0.0d;
        if (r8 != 0.0d) goto L17;
        double r16 = 0.0d;
    L19:
        if (r8 == r10) goto L36;
        if (r0 <= r4) goto L31;
        if (r0 <= r6) goto L31;
        double r02 = (r4 - r6) / r12;
        if (r4 >= r6) goto L27;
        int r2 = 6;
    L28:
        double r22 = r2;
    L29:
        double r03 = r02 + r22;
    L34:
        r14 = r03 / 6.0d;
        goto L36
    L27:
        r2 = 0;
    L31:
        if (r4 <= r6) goto L33;
        r03 = 2.0d + ((r6 - r0) / r12);
        goto L34
    L33:
        r02 = (r0 - r4) / r12;
        r22 = 4.0d;
    L36:
        return new double[]{r14, r16, r8};
    L17:
        r16 = r12 / r8;
        goto L19
    L11:
        if (r0 >= r6) goto L13;
        r10 = r0;
        goto L14
    L5:
        if (r0 <= r6) goto L7;
        r8 = r0;
        goto L9
    }

    public static int[] roundPercents(float[] r11, int[] r12) {
        if (r11 == null) goto L33;
        if (r12 != null) goto L7;
        r12 = new int[r11.length];
    L7:
        if (r11.length != r12.length) goto L31;
        float r3 = 0.0f;
        int r2 = 0;
    L10:
        if (r2 >= r11.length) goto L12;
        r3 = r3 + r11[r2];
        r2 = r2 + 1;
        goto L10
    L12:
        int r22 = 0;
        int r4 = 0;
    L14:
        if (r22 >= r11.length) goto L17;
        int r5 = (int) Math.floor((r11[r22] / r3) * 100.0f);
        r12[r22] = r5;
        r4 = r4 + r5;
        r22 = r22 + 1;
    L17:
        if (r4 >= 100) goto L30;
        int r23 = -1;
        float r7 = 0.0f;
        int r52 = 0;
    L20:
        if (r52 >= r11.length) goto L27;
        float r8 = r11[r52];
        float r9 = (r8 / r3) - (r12[r52] / 100.0f);
        if (r8 <= 0.0f) goto L26;
        if (r9 < r7) goto L26;
        r23 = r52;
        r7 = r9;
    L26:
        r52 = r52 + 1;
        goto L20
    L27:
        if (r23 < 0) goto L30;
        r12[r23] = r12[r23] + 1;
        r4 = r4 + 1;
    L30:
        return r12;
    L31:
        IntList$$ExternalSyntheticBUOutline0.m("percents.length != output.length");
        return null;
    L33:
        f$$ExternalSyntheticBUOutline2.m("percents or output is null");
        return null;
    }

    public static int roundPlayingMessageSize(boolean r0) {
        if (r0 == false) goto L6;
        return roundSidePlayingMessageSize;
    L6:
        return roundPlayingMessageSize;
    }

    public static void runOnUIThread(Runnable r2) {
        runOnUIThread(r2, 0);
    }

    public static void runOnUIThread(Runnable r2, long r3) {
        if (ApplicationLoader.applicationHandler != null) goto L6;
        return;
    L6:
        if (r3 != 0) goto L9;
        ApplicationLoader.applicationHandler.post(r2);
        return;
    L9:
        ApplicationLoader.applicationHandler.postDelayed(r2, r3);
    }

    public static void scaleRect(RectF r2, float r3) {
        scaleRect(r2, r3, r2.centerX(), r2.centerY());
    }

    public static void scaleRect(RectF r4, float r5, float r6, float r7) {
        float r0 = r6 - r4.left;
        float r1 = r4.right - r6;
        float r2 = r7 - r4.top;
        r4.set(r6 - (r0 * r5), r7 - (r2 * r5), r6 + (r1 * r5), r7 + ((r4.bottom - r7) * r5));
    }

    public static void scrollToFragmentRow(INavigationLayout r3, String r4) {
        if (r3 == null) goto L11;
        if (r4 == null) goto L12;
        BaseFragment r32 = r3.getFragmentStack().get(r3.getFragmentStack().size() - 1);
        Field r0 = r32.getClass().getDeclaredField("listView");     // Catch: Throwable -> L8
        r0.setAccessible(true);     // Catch: Throwable -> L8
        RecyclerListView r1 = (RecyclerListView) r0.get(r32);     // Catch: Throwable -> L8
        r1.highlightRow(new AndroidUtilities$$ExternalSyntheticLambda14(r32, r4, r1));     // Catch: Throwable -> L8
        r0.setAccessible(false);     // Catch: Throwable -> L8
        return;
    L13:
        return;
    L12:
        return;
    }

    public static void setAdjustResizeToNothing(Activity r1, int r2) {
        if (r1 != null) goto L4;
        return;
    L4:
        if (isTablet() == true) goto L13;
        int r0 = adjustOwnerClassGuid;
        if (r0 == 0) goto L9;
        if (r0 == r2) goto L9;
        return;
    L9:
        r1.getWindow().setSoftInputMode(48);
        return;
    }

    public static void setEnabled(View r2, boolean r3) {
        if (r2 == null) goto L12;
        r2.setEnabled(r3);
        if ((r2 instanceof ViewGroup) == false) goto L10;
        ViewGroup r22 = (ViewGroup) r2;
        int r0 = 0;
    L8:
        if (r0 >= r22.getChildCount()) goto L13;
        setEnabled(r22.getChildAt(r0), r3);
        r0 = r0 + 1;
        goto L8
    L13:
        return;
    L10:
        return;
    }

    public static void setLightNavigationBar(Activity r0, boolean r1) {
        if (r0 == null) goto L5;
        setLightNavigationBar(r0.getWindow(), r1);
        return;
    }

    public static void setLightNavigationBar(Dialog r0, boolean r1) {
        if (r0 == null) goto L5;
        setLightNavigationBar(r0.getWindow(), r1);
        return;
    }

    public static void setLightNavigationBar(View r2, boolean r3) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (Build.VERSION.SDK_INT < 26) goto L8;
        changeSetSystemUiVisibility(r2, 16, r3);
        return;
    }

    private static void setLightNavigationBar(Window r0, boolean r1) {
        if (r0 == null) goto L5;
        setLightNavigationBar(r0.getDecorView(), r1);
        return;
    }

    public static void setLightStatusBar(Activity r0, boolean r1) {
        if (r0 == null) goto L5;
        setLightStatusBar(r0.getWindow(), r1);
        return;
    }

    public static void setLightStatusBar(Dialog r0, boolean r1) {
        if (r0 == null) goto L5;
        setLightStatusBar(r0.getWindow(), r1);
        return;
    }

    public static void setLightStatusBar(View r2, boolean r3) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (Build.VERSION.SDK_INT < 26) goto L8;
        changeSetSystemUiVisibility(r2, 8192, r3);
        return;
    }

    public static void setLightStatusBar(Window r2, boolean r3) {
        changeSetSystemUiVisibility(r2.getDecorView(), 8192, r3);
        if (Build.VERSION.SDK_INT < 35) goto L5;
        return;
    L5:
        if (r2.getStatusBarColor() == 0) goto L9;
        r2.setStatusBarColor(0);
        return;
    }

    public static int setMyLayerVersion(int r1, int r2) {
        return (r1 & Opcodes.V_PREVIEW) | r2;
    }

    @Deprecated
    public static void setNavigationBarColor(Activity r1, int r2) {
        setNavigationBarColor(r1, r2, true);
    }

    @Deprecated
    public static void setNavigationBarColor(Activity r1, int r2, boolean r3) {
        if (r1 == null) goto L5;
        setNavigationBarColor(r1.getWindow(), r2, r3, null);
        return;
    }

    public static void setNavigationBarColor(Dialog r1, int r2) {
        setNavigationBarColor(r1, r2, true);
    }

    public static void setNavigationBarColor(Dialog r1, int r2, boolean r3) {
        setNavigationBarColor(r1, r2, r3, null);
    }

    public static void setNavigationBarColor(Dialog r0, int r1, boolean r2, IntColorCallback r3) {
        if (r0 == null) goto L5;
        setNavigationBarColor(r0.getWindow(), r1, r2, r3);
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [int[], kotlin.coroutines.Continuation] */
    /* JADX WARN: Type inference failed for: r2v2, types: [android.animation.Animator, android.animation.ValueAnimator, java.lang.Object, void] */
    private static void setNavigationBarColor(Window r1, int r2, boolean r3, IntColorCallback r4) {
        if (r1 != null) goto L4;
        return;
    L4:
        HashMap<Window, ValueAnimator> r0 = navigationBarColorAnimators;
        if (r0 == null) goto L9;
        ValueAnimator r02 = r0.get(r1);
        if (r02 == null) goto L9;
        r02.cancel();
        navigationBarColorAnimators.remove(r1);
    L9:
        if (r3 == true) goto L14;
        if (r4 == null) goto L20;
        r4.run(r2);
    L20:
        r1.setNavigationBarColor(r2);     // Catch: Exception -> L19
        return;
    L22:
        return;
    L14:
        ?? r22 = DebugProbesImpl.probeCoroutineResumed$kotlinx_coroutines_core(new int[]{r1.getNavigationBarColor(), r2});
        r22.addUpdateListener(new AndroidUtilities$$ExternalSyntheticLambda15(r4, r1));
        r22.addListener(new AnonymousClass13(r1));
        r22.setDuration(200);
        r22.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r22.start();
        if (navigationBarColorAnimators != null) goto L17;
        navigationBarColorAnimators = new HashMap();
    L17:
        navigationBarColorAnimators.put(r1, r22);
    }

    public static int setPeerLayerVersion(int r1, int r2) {
        return (r1 & 65535) | (r2 << 16);
    }

    public static void setPictureInPictureParams(Activity r1, PictureInPictureParams r2) {
        if (r1 != null) goto L4;
        return;
    L4:
        if (r1.isDestroyed() == true) goto L17;
        if (r2 != null) goto L14;
        resetPictureInPictureParams(r1);
        return;
    L14:
        r1.setPictureInPictureParams(r2);     // Catch: Throwable -> L11
        return;
    L11:
        th = move-exception;
        FileLog.e(th);
        return;
    }

    public static void setPreferredMaxRefreshRate(Window r1) {
        setPreferredMaxRefreshRate(r1, screenMaxRefreshRate);
    }

    public static void setPreferredMaxRefreshRate(Window r2, float r3) {
        if (r2 == null) goto L15;
        WindowManager r0 = r2.getWindowManager();
        if (r0 != null) goto L7;
        return;
    L7:
        WindowManager.LayoutParams r1 = r2.getAttributes();
        r1.preferredRefreshRate = r3;
        r0.updateViewLayout(r2.getDecorView(), r1);     // Catch: Exception -> L10
        return;
    L10:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    public static void setPreferredMaxRefreshRate(WindowManager r4, View r5, WindowManager.LayoutParams r6) {
        if (r4 != null) goto L5;
        return;
    L5:
        if (Math.abs(r6.preferredRefreshRate - screenMaxRefreshRate) <= 0.2d) goto L15;
        r6.preferredRefreshRate = screenMaxRefreshRate;
        if (r5.isAttachedToWindow() == false) goto L16;
        r4.updateViewLayout(r5, r6);     // Catch: Exception -> L10
        return;
    L10:
        e = move-exception;
        FileLog.e(e);
        return;
    L16:
        return;
    }

    public static void setRectD(RectF r2, float r3, float r4, float r5) {
        float r52 = r5 / 2.0f;
        r2.set(r3 - r52, r4 - r52, r3 + r52, r4 + r52);
    }

    public static void setRectR(RectF r2, float r3, float r4, float r5) {
        r2.set(r3 - r5, r4 - r5, r3 + r5, r4 + r5);
    }

    public static void setRectToRect(Matrix r9, RectF r10, RectF r11, int r12, int r13, boolean r14) {
        if (r12 == 90) goto L8;
        if (r12 == 270) goto L8;
        float r2 = r11.width() / r10.width();
        float r3 = r11.height();
        float r4 = r10.height();
    L7:
        float r32 = r3 / r4;
        if (r2 >= r32) goto L12;
        r2 = r32;
        boolean r33 = true;
    L13:
        if (r14 == false) goto L16;
        r9.setTranslate(r11.left, r11.top);
    L16:
        if (r12 != 90) goto L24;
        r9.preRotate(90.0f);
        if (r13 != 1) goto L20;
        r9.preScale(-1.0f, 1.0f);
    L22:
        r9.preTranslate(0.0f, -r11.width());
    L38:
        if (r14 == false) goto L40;
        float r122 = (-r10.left) * r2;
        float r132 = (-r10.top) * r2;
    L41:
        if (r33 == false) goto L44;
        float r112 = r11.width();
        float r102 = r10.width();
    L45:
        float r113 = (r112 - (r102 * r2)) / 2.0f;
        if (r33 == false) goto L48;
        r122 = r122 + r113;
    L49:
        r9.preScale(r2, r2);
        if (r14 == false) goto L53;
        r9.preTranslate(r122, r132);
        return;
    L53:
        return;
    L48:
        r132 = r132 + r113;
        goto L49
    L44:
        r112 = r11.height();
        r102 = r10.height();
        goto L45
    L40:
        r122 = r11.left - (r10.left * r2);
        r132 = r11.top - (r10.top * r2);
        goto L41
    L20:
        if (r13 != 2) goto L22;
        r9.preScale(1.0f, -1.0f);
        goto L22
    L24:
        if (r12 != 180) goto L31;
        r9.preRotate(180.0f);
        if (r13 != 1) goto L28;
        r9.preScale(-1.0f, 1.0f);
    L30:
        r9.preTranslate(-r11.width(), -r11.height());
        goto L38
    L28:
        if (r13 != 2) goto L30;
        r9.preScale(1.0f, -1.0f);
        goto L30
    L31:
        if (r12 != 270) goto L38;
        r9.preRotate(270.0f);
        if (r13 != 1) goto L35;
        r9.preScale(-1.0f, 1.0f);
    L37:
        r9.preTranslate(-r11.height(), 0.0f);
        goto L38
    L35:
        if (r13 != 2) goto L37;
        r9.preScale(1.0f, -1.0f);
        goto L37
    L12:
        r33 = false;
    L8:
        r2 = r11.height() / r10.width();
        r3 = r11.width();
        r4 = r10.height();
        goto L7
    }

    public static void setScrollViewEdgeEffectColor(HorizontalScrollView r3, int r4) {
        if (Build.VERSION.SDK_INT < 29) goto L16;
        r3.setEdgeEffectColor(r4);
        return;
    L16:
        Field r1 = HorizontalScrollView.class.getDeclaredField("mEdgeGlowLeft");     // Catch: Exception -> L13
        r1.setAccessible(true);     // Catch: Exception -> L13
        EdgeEffect r12 = (EdgeEffect) r1.get(r3);     // Catch: Exception -> L13
        if (r12 == null) goto L9;
        r12.setColor(r4);     // Catch: Exception -> L13
    L9:
        Field r0 = HorizontalScrollView.class.getDeclaredField("mEdgeGlowRight");     // Catch: Exception -> L13
        r0.setAccessible(true);     // Catch: Exception -> L13
        EdgeEffect r32 = (EdgeEffect) r0.get(r3);     // Catch: Exception -> L13
        if (r32 == null) goto L18;
        r32.setColor(r4);     // Catch: Exception -> L13
        return;
    L18:
        return;
    L13:
        e = move-exception;
        FileLog.e(e);
    }

    public static void setScrollViewEdgeEffectColor(ScrollView r3, int r4) {
        if (Build.VERSION.SDK_INT < 29) goto L14;
        r3.setTopEdgeEffectColor(r4);
        r3.setBottomEdgeEffectColor(r4);
        return;
    L14:
        Field r1 = ScrollView.class.getDeclaredField("mEdgeGlowTop");     // Catch: Exception -> L13
        r1.setAccessible(true);     // Catch: Exception -> L13
        EdgeEffect r12 = (EdgeEffect) r1.get(r3);     // Catch: Exception -> L13
        if (r12 == null) goto L9;
        r12.setColor(r4);     // Catch: Exception -> L13
    L9:
        Field r0 = ScrollView.class.getDeclaredField("mEdgeGlowBottom");     // Catch: Exception -> L13
        r0.setAccessible(true);     // Catch: Exception -> L13
        EdgeEffect r32 = (EdgeEffect) r0.get(r3);     // Catch: Exception -> L13
        if (r32 == null) goto L16;
        r32.setColor(r4);     // Catch: Exception -> L13
        return;
    L16:
        return;
    }

    public static void setViewLayoutMargins(View r2, int r3, int r4, int r5, int r6) {
        if (r2 == null) goto L18;
        ViewGroup.LayoutParams r0 = r2.getLayoutParams();
        if ((r0 instanceof ViewGroup.MarginLayoutParams) == false) goto L16;
        ViewGroup.MarginLayoutParams r02 = (ViewGroup.MarginLayoutParams) r0;
        if (r02.leftMargin == r3) goto L9;
    L14:
        r02.leftMargin = r3;
        r02.topMargin = r4;
        r02.rightMargin = r5;
        r02.bottomMargin = r6;
        r2.requestLayout();
        return;
    L9:
        if (r02.topMargin != r4) goto L14;
        if (r02.rightMargin != r5) goto L14;
        if (r02.bottomMargin != r6) goto L14;
        return;
    L16:
        return;
    }

    public static void setViewPagerEdgeEffectColor(ViewPager r3, int r4) {
        Field r1 = ViewPager.class.getDeclaredField("mLeftEdge");     // Catch: Exception -> L10
        r1.setAccessible(true);     // Catch: Exception -> L10
        EdgeEffect r12 = (EdgeEffect) r1.get(r3);     // Catch: Exception -> L10
        if (r12 == null) goto L6;
        r12.setColor(r4);     // Catch: Exception -> L10
    L6:
        Field r0 = ViewPager.class.getDeclaredField("mRightEdge");     // Catch: Exception -> L10
        r0.setAccessible(true);     // Catch: Exception -> L10
        EdgeEffect r32 = (EdgeEffect) r0.get(r3);     // Catch: Exception -> L10
        if (r32 == null) goto L13;
        r32.setColor(r4);     // Catch: Exception -> L10
        return;
    L13:
        return;
    }

    public static void setWaitingForCall(boolean r5) {
        Object r0 = callLock;
        monitor-enter(r0);
        if (r5 == false) goto L14;
        if (callReceiver != null) goto L24;
        IntentFilter r1 = new IntentFilter("android.intent.action.PHONE_STATE");     // Catch: Throwable -> L10 Exception -> L21
        if (Build.VERSION.SDK_INT < 33) goto L12;
        Context r2 = ApplicationLoader.applicationContext;     // Catch: Throwable -> L10 Exception -> L21
        CallReceiver r3 = new CallReceiver();     // Catch: Throwable -> L10 Exception -> L21
        callReceiver = r3;     // Catch: Throwable -> L10 Exception -> L21
        r2.registerReceiver(r3, r1, 4);     // Catch: Throwable -> L10 Exception -> L21
        goto L24
    L12:
        Context r22 = ApplicationLoader.applicationContext;     // Catch: Throwable -> L10 Exception -> L21
        CallReceiver r32 = new CallReceiver();     // Catch: Throwable -> L10 Exception -> L21
        callReceiver = r32;     // Catch: Throwable -> L10 Exception -> L21
        r22.registerReceiver(r32, r1);     // Catch: Throwable -> L10 Exception -> L21
    L24:
        goto L16
    L14:
        if (callReceiver == null) goto L24;
        ApplicationLoader.applicationContext.unregisterReceiver(callReceiver);     // Catch: Throwable -> L10 Exception -> L21
        callReceiver = null;     // Catch: Throwable -> L10 Exception -> L21
    L10:
        th = move-exception;
        throw th;
    L16:
        waitingForCall = r5;     // Catch: Throwable -> L10
        monitor-exit(r0);     // Catch: Throwable -> L10
    }

    public static void setWaitingForSms(boolean r2) {
        Object r0 = smsLock;
        monitor-enter(r0);
        waitingForSms = r2;     // Catch: Throwable -> L10
        if (r2 == true) goto L16;
    L12:
        monitor-exit(r0);     // Catch: Throwable -> L10
        return;
    L16:
        SmsRetriever.getClient(ApplicationLoader.applicationContext).startSmsRetriever().addOnSuccessListener(new AndroidUtilities$$ExternalSyntheticLambda22());     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        FileLog.e(th);     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    }

    public static void shakeView(View r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        Object r0 = r3.getTag(R.id.shake_animation);
        if ((r0 instanceof ValueAnimator) == false) goto L7;
        ((ValueAnimator) r0).cancel();
    L7:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r02.addUpdateListener(new AndroidUtilities$$ExternalSyntheticLambda4(r3));
        r02.addListener(new AnonymousClass12(r3));
        r02.setDuration(300);
        r02.start();
        r3.setTag(R.id.shake_animation, r02);
    }

    public static void shakeViewSpring(View r2) {
        shakeViewSpring(r2, 10.0f, null);
    }

    public static void shakeViewSpring(View r1, float r2) {
        shakeViewSpring(r1, r2, null);
    }

    public static void shakeViewSpring(View r4, float r5, Runnable r6) {
        if (r4 != null) goto L4;
        return;
    L4:
        int r52 = dp(r5);
        if (r4.getTag(R.id.spring_tag) == null) goto L7;
        ((SpringAnimation) r4.getTag(R.id.spring_tag)).cancel();
    L7:
        Float r0 = (Float) r4.getTag(R.id.spring_was_translation_x_tag);
        if (r0 == null) goto L10;
        r4.setTranslationX(r0.floatValue());
    L10:
        r4.setTag(R.id.spring_was_translation_x_tag, Float.valueOf(r4.getTranslationX()));
        float r02 = r4.getTranslationX();
        SpringAnimation r53 = new SpringAnimation(r4, DynamicAnimation.TRANSLATION_X, r02).setSpring(new SpringForce(r02).setStiffness(600.0f)).setStartVelocity((-r52) * 100).addUpdateListener(new AndroidUtilities$$ExternalSyntheticLambda6(r4)).addEndListener(new AndroidUtilities$$ExternalSyntheticLambda7(r6, r4, r02));
        r4.setTag(R.id.spring_tag, r53);
        r53.start();
    }

    public static void shakeViewSpring(View r1, Runnable r2) {
        shakeViewSpring(r1, 10.0f, r2);
    }

    public static boolean shouldEnableAnimation() {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 >= 26) goto L5;
    L14:
        return true;
    L5:
        if (r0 >= 28) goto L14;
        if (((PowerManager) ApplicationLoader.applicationContext.getSystemService("power")).isPowerSaveMode() == false) goto L11;
        return false;
    L11:
        if (getAnimatorDurationScale() <= 0.0f) goto L13;
        return true;
    L13:
        return false;
    }

    public static boolean shouldShowClipboardToast() {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 31) goto L7;
        if (OneUIUtilities.hasBuiltInClipboardToasts() == false) goto L7;
        return false;
    L7:
        if (r0 >= 33) goto L9;
        return true;
    L9:
        if (XiaomiUtilities.isMIUI() == true) goto L16;
        return false;
    L16:
        return true;
    }

    public static boolean shouldShowUrlInAlert(String r0) {
        return checkHostForPunycode(Uri.parse(r0).getHost());
    L4:
        e = move-exception;
        FileLog.e(e);
        return false;
    }

    public static boolean showKeyboard(View r3) {
        if (r3 != null) goto L10;
        return false;
    L10:
        return ((InputMethodManager) r3.getContext().getSystemService("input_method")).showSoftInput(r3, 1);
    L7:
        e = move-exception;
        FileLog.e(e);
        return false;
    }

    public static void showProxyAlert(Activity r21, ProxySettings r22) {
        if (r22 != null) goto L5;
        return;
    L5:
        if (r22.isValid() == false) goto L27;
        String r0 = r22.getAddress();
        int r2 = r22.getPort();
        String r3 = r22.getUser();
        String r5 = r22.getPassword();
        String r6 = r22.getSecret();
        BottomSheet.Builder r7 = new BottomSheet.Builder(r21);
        r7.setApplyTopPadding(false);
        r7.setApplyBottomPadding(false);
        Runnable r9 = r7.getDismissRunnable();
        LinearLayout r10 = new LinearLayout(r21);
        r10.setOrientation(1);
        r7.setCustomView(r10);
        TextView r12 = TextHelper.makeTextView(r21, 20.0f, Theme.key_dialogTextBlack, true);
        r12.setText(LocaleController.getString(R.string.UseProxyTitle));
        r10.addView(r12, LayoutHelper.createLinear(-1, -2, 55, 22, 18, 22, 0));
        TableView r122 = new TableView(r21, null);
        r10.addView(r122, LayoutHelper.createLinear(-1, -2, 55, 14, 18, 14, 0));
        ProxyController r23 = ProxyController.getInstance();
        SharedConfig.ProxyInfo r15 = new SharedConfig.ProxyInfo(r22);
        r122.addRow(LocaleController.getString(R.string.ProxyType), r23.getProxyTypeName(r15));
        ButtonSpan.TextViewButtons[] r8 = new ButtonSpan.TextViewButtons[1];
        r122.addRow(LocaleController.getString(R.string.Country), LocaleController.getString(R.string.Loading), r8);
        r23.requestProxyCountry(r15, new AndroidUtilities$$ExternalSyntheticLambda27(new WeakReference(r8[0])));
        if (TextUtils.isEmpty(r0) == true) goto L10;
        r122.addRow(LocaleController.getString(R.string.UseProxyAddress), r0);
    L10:
        if (r2 == 0) goto L13;
        r122.addRow(LocaleController.getString(R.string.UseProxyPort), Integer.toString(r2));
    L13:
        if (TextUtils.isEmpty(r6) == true) goto L16;
        r122.addRow(LocaleController.getString(R.string.UseProxySecret), r6);
    L16:
        if (TextUtils.isEmpty(r3) == true) goto L19;
        r122.addRow(LocaleController.getString(R.string.UseProxyUsername), r3);
    L19:
        if (TextUtils.isEmpty(r5) == true) goto L21;
        r122.addRow(LocaleController.getString(R.string.UseProxyPassword), r5);
    L21:
        ButtonSpan.TextViewButtons[] r32 = new ButtonSpan.TextViewButtons[1];
        r122.addRow(LocaleController.getString(R.string.ProxyStatus), _UrlKt.FRAGMENT_ENCODE_SET, r32);
        ((View) r32[0].getParent()).setPadding(0, 0, 0, 0);
        r32[0].setDisablePaddingsOffsetY(true);
        r32[0].setPadding(dp(12.66f), dp(9.33f), dp(12.66f), dp(9.33f));
        r32[0].setText(replaceSingleLink(LocaleController.getString(R.string.ProxyBottomSheetCheckStatus), Theme.getColor(Theme.key_chat_messageLinkIn), new AndroidUtilities$$ExternalSyntheticLambda28(new boolean[1], r32, r22, r21)));
        if (TextUtils.isEmpty(r6) == true) goto L24;
        TableView.TableRowFullContent r02 = r122.addFullRow(LocaleController.getString(R.string.UseProxyTelegramInfo2));
        r02.setFilled(true);
        SpoilersTextView r03 = (SpoilersTextView) r02.getChildAt(0);
        r03.setTextSize(1, 11.0f);
        r03.setGravity(17);
    L24:
        ButtonWithCounterView r62 = new ButtonWithCounterView(r21, null).setRound();
        r62.setText(LocaleController.getString(R.string.ConnectingConnectProxy));
        r62.setOnClickListener(new AndroidUtilities$$ExternalSyntheticLambda29(r22, r23, r15, r21, r9));
        r10.addView(r62, LayoutHelper.createLinear(-1, 48, 55, 14, 18, 14, 14));
        r7.show();
        return;
    }

    private static void snapshotTextureViews(int r5, int r6, int[] r7, Canvas r8, View r9) {
        int r1 = 0;
        if ((r9 instanceof TextureView) == false) goto L8;
        TextureView r0 = (TextureView) r9;
        r0.getLocationInWindow(r7);
        Bitmap r02 = r0.getBitmap();
        if (r02 == null) goto L8;
        r8.save();
        r8.drawBitmap(r02, r7[0] - r5, r7[1] - r6, null);
        r8.restore();
        r02.recycle();
    L8:
        if ((r9 instanceof ViewGroup) == false) goto L13;
        ViewGroup r92 = (ViewGroup) r9;
    L11:
        if (r1 >= r92.getChildCount()) goto L15;
        snapshotTextureViews(r5, r6, r7, r8, r92.getChildAt(r1));
        r1 = r1 + 1;
        goto L11
    L15:
        return;
    }

    public static Bitmap snapshotView(View r5) {
        Bitmap r0 = Bitmap.createBitmap(r5.getWidth(), r5.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas r1 = new Canvas(r0);
        r5.draw(r1);
        int[] r2 = new int[2];
        r5.getLocationInWindow(r2);
        snapshotTextureViews(r2[0], r2[1], r2, r1, r5);
        return r0;
    }

    public static CharSequence superTrim(CharSequence r3) {
        if (r3 != null) goto L5;
        return null;
    L5:
        int r0 = r3.length();
        int r1 = 0;
    L6:
        if (r1 >= r0) goto L10;
        if (isWhitespace(r3.charAt(r1)) == false) goto L10;
        r1 = r1 + 1;
    L10:
        if (r1 >= r0) goto L14;
        if (isWhitespace(r3.charAt(r0 - 1)) == false) goto L14;
        r0 = r0 - 1;
    L14:
        if (r1 > 0) goto L20;
        if (r0 < r3.length()) goto L20;
        return r3;
    L20:
        return r3.subSequence(r1, r0);
    }

    public static int[] toIntArray(List<Integer> r4) {
        int r0 = r4.size();
        int[] r1 = new int[r0];
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r1[r2] = r4.get(r2).intValue();
        r2 = r2 + 1;
        goto L3
    L5:
        return r1;
    }

    public static String translitSafe(String r3) {
        if (r3 != null) goto L10;
    L5:
        String r32 = LocaleController.getInstance().getTranslitString(r3, false);     // Catch: Exception -> L9
        if (r32 != null) goto L8;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        return r32;
    L9:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        r3 = r3.toLowerCase();     // Catch: Exception -> L9
        goto L5
    }

    public static CharSequence trim(CharSequence r5, int[] r6) {
        if (r5 != null) goto L5;
        return null;
    L5:
        int r0 = r5.length();
        int r2 = 0;
    L7:
        if (r2 >= r0) goto L11;
        if (r5.charAt(r2) > ' ') goto L11;
        r2 = r2 + 1;
    L11:
        if (r2 >= r0) goto L15;
        if (r5.charAt(r0 - 1) > ' ') goto L15;
        r0 = r0 - 1;
    L15:
        if (r6 == null) goto L17;
        r6[0] = r2;
    L17:
        if (r2 > 0) goto L23;
        if (r0 < r5.length()) goto L23;
        return r5;
    L23:
        return r5.subSequence(r2, r0);
    }

    @SuppressLint({"WrongConstant"})
    public static void unlockOrientation(Activity r2) {
        if (r2 == null) goto L14;
        int r0 = prevOrientation;     // Catch: Exception -> L8
        if (r0 == (-10)) goto L13;
        r2.setRequestedOrientation(r0);     // Catch: Exception -> L8
        prevOrientation = -10;     // Catch: Exception -> L8
        return;
    L13:
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    public static int unpackA(long r1) {
        return (int) (r1 >> 32);
    }

    public static int unpackB(long r0) {
        return (int) r0;
    }

    public static void updateImageViewImageAnimated(ImageView r1, int r2) {
        updateImageViewImageAnimated(r1, ContextCompat.getDrawable(r1.getContext(), r2));
    }

    public static void updateImageViewImageAnimated(ImageView r3, Drawable r4) {
        if (r3.getDrawable() != r4) goto L5;
        return;
    L5:
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(150);
        r0.addUpdateListener(new AndroidUtilities$$ExternalSyntheticLambda25(r3, new AtomicBoolean(), r4));
        r0.start();
    }

    public static void updateViewLayout(WindowManager r1, View r2, ViewGroup.LayoutParams r3) {
        if (r1 == null) goto L8;
        if (r2 != null) goto L5;
        return;
    L5:
        if (r2.getParent() == null) goto L10;
        r1.updateViewLayout(r2, r3);
        return;
    L10:
        return;
    }

    public static void updateViewShow(View r1, boolean r2) {
        updateViewShow(r1, r2, true, true);
    }

    public static void updateViewShow(View r8, boolean r9, boolean r10, float r11, boolean r12, Runnable r13) {
        if (r8 == null) goto L68;
        int r1 = 0;
        if (r8.getParent() != null) goto L7;
        r12 = false;
    L7:
        Integer r2 = null;
        r8.animate().setListener(null).cancel();
        float r3 = 0.5f;
        float r4 = 0.0f;
        if (r12 == true) goto L35;
        if (r9 == true) goto L12;
        r1 = 8;
    L12:
        r8.setVisibility(r1);
        if (r9 == false) goto L15;
        r2 = 1;
    L15:
        r8.setTag(r2);
        r8.setAlpha(1.0f);
        if (r10 == false) goto L19;
        if (r9 == true) goto L19;
        float r122 = 0.5f;
    L20:
        r8.setScaleX(r122);
        if (r10 == false) goto L24;
        if (r9 == true) goto L24;
    L25:
        r8.setScaleY(r3);
        if (r11 == 0.0f) goto L31;
        if (r9 == true) goto L30;
        r4 = dp(-16.0f) * r11;
    L30:
        r8.setTranslationY(r4);
    L31:
        if (r13 == null) goto L67;
        r13.run();
        return;
    L67:
        return;
    L24:
        r3 = 1.0f;
    L19:
        r122 = 1.0f;
        goto L20
    L35:
        if (r9 == true) goto L37;
        ViewPropertyAnimator r92 = r8.animate().alpha(0.0f);
        if (r10 == false) goto L57;
        float r123 = 0.5f;
    L58:
        ViewPropertyAnimator r93 = r92.scaleY(r123);
        if (r10 == true) goto L62;
        r3 = 1.0f;
    L62:
        ViewPropertyAnimator r82 = r93.scaleX(r3).setListener(new HideViewAfterAnimation(r8)).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(340).withEndAction(r13);
        if (r11 == 0.0f) goto L65;
        r82.translationY(dp(-16.0f) * r11);
    L65:
        r82.start();
        return;
    L57:
        r123 = 1.0f;
        goto L58
    L37:
        if (r8.getVisibility() == 0) goto L49;
        r8.setVisibility(0);
        r8.setAlpha(0.0f);
        if (r10 == false) goto L41;
        float r94 = 0.5f;
    L42:
        r8.setScaleX(r94);
        if (r10 == true) goto L46;
        r3 = 1.0f;
    L46:
        r8.setScaleY(r3);
        if (r11 == 0.0f) goto L49;
        r8.setTranslationY(dp(-16.0f) * r11);
        goto L49
    L41:
        r94 = 1.0f;
    L49:
        ViewPropertyAnimator r83 = r8.animate().alpha(1.0f).scaleY(1.0f).scaleX(1.0f).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(340).withEndAction(r13);
        if (r11 == 0.0f) goto L52;
        r83.translationY(0.0f);
    L52:
        r83.start();
        return;
    }

    public static void updateViewShow(View r6, boolean r7, boolean r8, boolean r9) {
        updateViewShow(r6, r7, r8, 0.0f, r9, null);
    }

    public static void updateViewShow(View r6, boolean r7, boolean r8, boolean r9, Runnable r10) {
        updateViewShow(r6, r7, r8, 0.0f, r9, r10);
    }

    public static void updateViewVisibilityAnimated(View r2, boolean r3) {
        updateViewVisibilityAnimated(r2, r3, 1.0f, true, true);
    }

    public static void updateViewVisibilityAnimated(View r1, boolean r2, float r3, boolean r4) {
        updateViewVisibilityAnimated(r1, r2, r3, true, r4);
    }

    public static void updateViewVisibilityAnimated(View r7, boolean r8, float r9, boolean r10, float r11, boolean r12, ValueAnimator.AnimatorUpdateListener r13) {
        Integer r0 = 1;
        if (r7 == null) goto L38;
        int r2 = 0;
        if (r7.getParent() != null) goto L9;
        r12 = false;
    L9:
        if (r12 == true) goto L23;
        r7.animate().setListener(null).cancel();
        if (r8 == true) goto L16;
        if (r10 == false) goto L15;
        r2 = 8;
        goto L16
    L15:
        r2 = 4;
    L16:
        r7.setVisibility(r2);
        if (r8 == true) goto L20;
        r0 = null;
    L20:
        r7.setTag(r0);
        r7.setAlpha(r11);
        r7.setScaleX(1.0f);
        r7.setScaleY(1.0f);
        return;
    L23:
        if (r8 == true) goto L25;
    L31:
        if (r8 == false) goto L33;
        return;
    L33:
        if (r7.getTag() == null) goto L37;
        r7.animate().setListener(null).cancel();
        r7.animate().alpha(0.0f).scaleY(r9).scaleX(r9).setListener(new HideViewAfterAnimation(r7, r10)).setDuration(150).setUpdateListener(r13).start();
        r7.setTag(null);
        return;
    L37:
        return;
    L25:
        if (r7.getTag() != null) goto L31;
        r7.animate().setListener(null).cancel();
        if (r7.getVisibility() == 0) goto L29;
        r7.setVisibility(0);
        r7.setAlpha(0.0f);
        r7.setScaleX(r9);
        r7.setScaleY(r9);
    L29:
        r7.animate().alpha(r11).scaleY(1.0f).scaleX(1.0f).setDuration(150).setUpdateListener(r13).start();
        r7.setTag(r0);
        return;
    }

    public static void updateViewVisibilityAnimated(View r7, boolean r8, float r9, boolean r10, boolean r11) {
        updateViewVisibilityAnimated(r7, r8, r9, r10, 1.0f, r11, null);
    }

    public static void updateVisibleRow(RecyclerListView r5, int r6) {
        if (r5 == null) goto L21;
        RecyclerView.Adapter r0 = r5.getAdapter();
        if (r0 == null) goto L28;
        int r1 = 0;
    L9:
        if (r1 >= r5.getChildCount()) goto L29;
        View r2 = r5.getChildAt(r1);
        int r3 = r5.getChildAdapterPosition(r2);
        if (r3 < 0) goto L20;
        RecyclerView.ViewHolder r22 = r5.getChildViewHolder(r2);
        if (r22 == null) goto L20;
        if (r22.shouldIgnore() == true) goto L20;
        if (r22.getAdapterPosition() != r6) goto L20;
        r0.onBindViewHolder(r22, r3);
    L20:
        r1 = r1 + 1;
        goto L9
    L29:
        return;
    L28:
        return;
    }

    public static void updateVisibleRows(RecyclerListView r5) {
        if (r5 == null) goto L19;
        RecyclerView.Adapter r0 = r5.getAdapter();
        if (r0 == null) goto L25;
        int r1 = 0;
    L9:
        if (r1 >= r5.getChildCount()) goto L26;
        View r2 = r5.getChildAt(r1);
        int r3 = r5.getChildAdapterPosition(r2);
        if (r3 < 0) goto L18;
        RecyclerView.ViewHolder r22 = r5.getChildViewHolder(r2);
        if (r22 == null) goto L18;
        if (r22.shouldIgnore() == true) goto L18;
        r0.onBindViewHolder(r22, r3);
    L18:
        r1 = r1 + 1;
        goto L9
    L26:
        return;
    L25:
        return;
    }

    public static void vibrate(View r2) {
        if (r2 == null) goto L19;
        if (r2.getContext() != null) goto L6;
        return;
    L6:
        if (ExteraConfig.getInAppVibration() == true) goto L9;
        return;
    L9:
        if (Build.VERSION.SDK_INT >= 26) goto L12;
        return;
    L12:
        if (((Vibrator) r2.getContext().getSystemService("vibrator")).hasAmplitudeControl() == false) goto L23;
        r2.performHapticFeedback(3, 1);     // Catch: Exception -> L16
        return;
    L23:
        return;
    L24:
        return;
    }

    public static void vibrateCursor(View r2) {
        if (r2 == null) goto L19;
        if (r2.getContext() != null) goto L6;
        return;
    L6:
        if (ExteraConfig.getInAppVibration() == true) goto L9;
        return;
    L9:
        if (Build.VERSION.SDK_INT >= 26) goto L12;
        return;
    L12:
        if (((Vibrator) r2.getContext().getSystemService("vibrator")).hasAmplitudeControl() == false) goto L23;
        r2.performHapticFeedback(9, 1);     // Catch: Exception -> L16
        return;
    L23:
        return;
    L24:
        return;
    }

    public static CharSequence withLearnMore(CharSequence r4, Runnable r5) {
        SpannableString r0 = new SpannableString(LocaleController.getString(R.string.LearnMoreArrow));
        r0.setSpan(new AnonymousClass14(r5), 0, r0.length(), 33);
        SpannableStringBuilder r52 = new SpannableStringBuilder(r4);
        r52.append(" ");
        r52.append(r0);
        return replaceArrows(r52, true);
    }
}
