package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.PictureInPictureParams;
import android.app.PictureInPictureUiState;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StatFs;
import android.os.StrictMode;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.SparseIntArray;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.window.BackEvent;
import android.window.OnBackAnimationCallback;
import android.window.OnBackInvokedCallback;
import androidx.appcompat.app.AppCompatDelegateImpl$Api33Impl$$ExternalSyntheticApiModelOutline0;
import androidx.arch.core.util.Function;
import androidx.collection.LongSparseArray;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.drawer.DrawerContainer;
import com.exteragram.messenger.icons.ExteraResources;
import com.exteragram.messenger.icons.IconManager;
import com.exteragram.messenger.icons.ui.picker.IconPickerController;
import com.exteragram.messenger.pillstack.core.PillStackConfig;
import com.exteragram.messenger.pillstack.core.PillType;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.updater.UpdaterUtils;
import com.exteragram.messenger.utils.chats.WidePosts;
import com.exteragram.messenger.utils.network.RemoteUtils;
import com.exteragram.messenger.utils.ui.MonetUtils;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.common.api.Status;
import com.google.android.material.navigation.NavigationBarView;
import com.google.common.primitives.Longs;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.AutoDeleteMediaTask;
import org.telegram.messenger.BackupAgent;
import org.telegram.messenger.BotGuardHelper;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChannelBoostsController;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.FingerprintController;
import org.telegram.messenger.FlagSecureReason;
import org.telegram.messenger.GenericProvider;
import org.telegram.messenger.GiftAuctionController;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.LocationController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationsController;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.SharedPrefsHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.messenger.pip.PipActivityController;
import org.telegram.messenger.pip.activity.IPipActivity;
import org.telegram.messenger.pip.activity.IPipActivityHandler;
import org.telegram.messenger.pip.activity.IPipActivityListener;
import org.telegram.messenger.utils.Choreographer60FpsContent;
import org.telegram.messenger.utils.FrameMetricsOverlayView;
import org.telegram.messenger.utils.LeakDetector;
import org.telegram.messenger.utils.WindowVisibilityManager;
import org.telegram.messenger.video.VideoAds;
import org.telegram.messenger.voip.VideoCapturerDevice;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_chatlists;
import org.telegram.tgnet.tl.TL_forum;
import org.telegram.tgnet.tl.TL_payments;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarLayout;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.BottomSheetTabs;
import org.telegram.ui.ActionBar.BottomSheetTabsOverlay;
import org.telegram.ui.ActionBar.DrawerLayoutContainer;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Cells.LanguageCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.ChatRightsEditActivity;
import org.telegram.ui.Components.ActivityWindowEmptyBackgroundDrawable;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AttachBotIntroTopView;
import org.telegram.ui.Components.BatteryDrawable;
import org.telegram.ui.Components.BlockingUpdateView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ChatActivityEnterView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Easings;
import org.telegram.ui.Components.EmbedBottomSheet;
import org.telegram.ui.Components.EmojiPacksAlert;
import org.telegram.ui.Components.FireworksOverlay;
import org.telegram.ui.Components.FloatingDebug.FloatingDebugController;
import org.telegram.ui.Components.FolderBottomSheet;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.GroupCallPip;
import org.telegram.ui.Components.JoinGroupAlert;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.PasscodeView;
import org.telegram.ui.Components.PasscodeViewDialog;
import org.telegram.ui.Components.PhonebookShareAlert;
import org.telegram.ui.Components.PipRoundVideoView;
import org.telegram.ui.Components.PipVideoOverlay;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.Premium.boosts.BoostPagerBottomSheet;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.SearchTagsList;
import org.telegram.ui.Components.ShareTopView;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.StickerSetBulletinLayout;
import org.telegram.ui.Components.StickersAlert;
import org.telegram.ui.Components.TermsOfServiceView;
import org.telegram.ui.Components.TextStyleSpan;
import org.telegram.ui.Components.ThemeEditorView;
import org.telegram.ui.Components.UndoView;
import org.telegram.ui.Components.inset.WindowAnimatedInsetsProvider;
import org.telegram.ui.Components.spoilers.SpoilerEffect2;
import org.telegram.ui.Components.voip.RTMPStreamPipOverlay;
import org.telegram.ui.Components.voip.VoIPHelper;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.Gifts.AuctionJoinSheet;
import org.telegram.ui.Gifts.GiftSheet;
import org.telegram.ui.PaymentFormActivity;
import org.telegram.ui.SelectAnimatedEmojiDialog;
import org.telegram.ui.Stars.ISuperRipple;
import org.telegram.ui.Stars.StarGiftPreviewSheet;
import org.telegram.ui.Stars.StarGiftSheet;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stars.SuperRipple;
import org.telegram.ui.Stories.LiveStoryPipOverlay;
import org.telegram.ui.Stories.StoriesController;
import org.telegram.ui.Stories.StoriesListPlaceProvider;
import org.telegram.ui.Stories.StoryViewer;
import org.telegram.ui.Stories.recorder.StoryEntry;
import org.telegram.ui.Stories.recorder.StoryRecorder;
import org.telegram.ui.WallpapersListActivity;
import org.telegram.ui.bots.BotWebViewSheet;
import org.telegram.ui.bots.WebViewRequestProps;
import org.webrtc.MediaStreamTrack;
import org.webrtc.voiceengine.WebRtcAudioTrack;

/* loaded from: classes3.dex */
public class LaunchActivity extends BasePermissionsActivity implements INavigationLayout.INavigationLayoutDelegate, NotificationCenter.NotificationCenterDelegate, DialogsActivity.DialogsActivityDelegate, IPipActivity {
    public static final Pattern PREFIX_T_ME_PATTERN = null;
    private static int activeInstanceCount;
    public static LaunchActivity instance;
    public static boolean isActive;
    public static boolean isResumed;
    public static Runnable onResumeStaticCallback;
    private static LaunchActivity staticInstanceForAlerts;
    public static boolean systemBlurEnabled;
    private static Pattern timestampPattern;
    public static Runnable whenResumed;
    public ActionBarLayout actionBarLayout;
    private long alreadyShownFreeDiscSpaceAlertForced;
    private SizeNotifierFrameLayout backgroundTablet;
    private final LiteMode.BatteryReceiver batteryReceiver;
    private BlockingUpdateView blockingUpdateView;
    private Consumer<Boolean> blurListener;
    private BottomSheetTabsOverlay bottomSheetTabsOverlay;
    private boolean checkFreeDiscSpaceShown;
    private ArrayList<TLRPC.User> contactsToSend;
    private Uri contactsToSendUri;
    private int currentConnectionState;
    private ISuperRipple currentRipple;
    private String documentsMimeType;
    private ArrayList<String> documentsOriginalPathsArray;
    private ArrayList<String> documentsPathsArray;
    private ArrayList<Uri> documentsUrisArray;
    public DrawerLayoutContainer drawerLayoutContainer;
    private HashMap<String, String> englishLocaleStrings;
    private Uri exportingChatUri;
    View feedbackView;
    private boolean finished;
    private FireworksOverlay fireworksOverlay;
    private FlagSecureReason flagSecureReason;
    public FrameLayout frameLayout;
    private FrameMetricsOverlayView frameMetricsOverlayView;
    private NotificationCenter.ObserversGroup globalObserversGroup;
    private ArrayList<Parcelable> importingStickers;
    private ArrayList<String> importingStickersEmoji;
    private String importingStickersSoftware;
    private final int instanceId;
    private boolean isInPictureInPictureMode;
    private boolean isNavigationBarColorFrozen;
    private boolean isStarted;
    private RelativeLayout launchLayout;
    private final ArrayList<BaseFragment> layerFragmentsStack;
    private ActionBarLayout layersActionBarLayout;
    private boolean loadingLocaleDialog;
    private TLRPC.TL_theme loadingTheme;
    private boolean loadingThemeAccent;
    private String loadingThemeFileName;
    private Theme.ThemeInfo loadingThemeInfo;
    private AlertDialog loadingThemeProgressDialog;
    private TLRPC.TL_wallPaper loadingThemeWallpaper;
    private String loadingThemeWallpaperName;
    private Dialog localeDialog;
    private Runnable lockRunnable;
    private final ArrayList<BaseFragment> mainFragmentsStack;
    private AlertDialog memoryLeakErrorAlertDialog;
    private ValueAnimator navBarAnimator;
    private boolean navigateToPremiumBot;
    public Runnable navigateToPremiumGiftCallback;
    private NotificationCenter.ObserversGroup observersGroup;
    private Object onBackAnimationCallback;
    private Object onBackInvokedCallback;
    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
    private Utilities.Callback<Boolean> onPowerSaverCallback;
    private final List<Runnable> onUserLeaveHintListeners;
    private List<PasscodeView> overlayPasscodeViews;
    private PasscodeViewDialog passcodeDialog;
    private Intent passcodeSaveIntent;
    private boolean passcodeSaveIntentIsNew;
    private boolean passcodeSaveIntentIsRestore;
    private ArrayList<SendMessagesHelper.SendingMediaInfo> photoPathsArray;
    private final PipActivityController pipActivityController;
    private final IPipActivityHandler pipActivityHandler;
    private Dialog proxyErrorDialog;
    private int reasonsToHideDecorView;
    private int reasonsToHideMainContent;
    private final SparseIntArray requestedPermissions;
    private int requsetPermissionsPointer;
    private ExteraResources res;
    private final CopyOnWriteArrayList<WeakReference<ExteraResources>> resHistory;
    public ActionBarLayout rightActionBarLayout;
    private final ArrayList<BaseFragment> rightFragmentsStack;
    private View rippleAbove;
    private WindowAnimatedInsetsProvider rootAnimatedInsetsListener;
    private SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow selectAnimatedEmojiDialog;
    private CharSequence sendingText;
    private FrameLayout shadowTablet;
    public ArrayList<INavigationLayout> sheetFragmentsStack;
    private boolean switchingAccount;
    private HashMap<String, String> systemLocaleStrings;
    private boolean tabletFullSize;
    private int[] tempLocation;
    private TermsOfServiceView termsOfServiceView;
    private ImageView themeSwitchImageView;
    private ImageView themeSwitchSunView;
    private String videoPath;
    private ActionMode visibleActionMode;
    public final ArrayList<Dialog> visibleDialogs;
    private String voicePath;
    public boolean voipLaunchedInBackground;
    private boolean wasMutedByAdminRaisedHand;
    private Utilities.Callback<Boolean> webviewShareAPIDoneListener;

    /* renamed from: org.telegram.ui.LaunchActivity$1, reason: invalid class name */
    public class AnonymousClass1 implements Consumer<Boolean> {
        final /* synthetic */ LaunchActivity this$0;

        public AnonymousClass1(LaunchActivity r1) {
            this.this$0 = r1;
        }

        /* renamed from: accept, reason: avoid collision after fix types in other method */
        public void accept2(Boolean r1) {
            LaunchActivity.systemBlurEnabled = r1.booleanValue();
        }

        @Override // java.util.function.Consumer
        public /* bridge */ /* synthetic */ void accept(Boolean r1) {
            accept2(r1);
        }
    }

    /* renamed from: org.telegram.ui.LaunchActivity$10, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass10 extends RelativeLayout {
        private boolean inLayout;
        private Insets insets;
        final /* synthetic */ LaunchActivity this$0;

        /* renamed from: $r8$lambda$zb9kO-l0QrhG3PwUOuFUlR2l9Wg, reason: not valid java name */
        public static /* synthetic */ WindowInsetsCompat m15667$r8$lambda$zb9kOl0QrhG3PwUOuFUlR2l9Wg(AnonymousClass10 r0, View r1, WindowInsetsCompat r2) {
            return r0.lambda$new$0(r1, r2);
        }

        public AnonymousClass10(LaunchActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.insets = Insets.NONE;
            ViewCompat.setOnApplyWindowInsetsListener(this, new LaunchActivity$10$$ExternalSyntheticLambda0(this));
        }

        private /* synthetic */ WindowInsetsCompat lambda$new$0(View r3, WindowInsetsCompat r4) {
            int r32 = 0;
            Insets r0 = AndroidUtilities.getDefaultWindowInsets(r4, false);
            if (this.insets.equals(r0) == true) goto L5;
            this.insets = r0;
            requestLayout();
        L5:
            int r02 = getChildCount();
        L6:
            if (r32 >= r02) goto L8;
            ViewCompat.dispatchApplyWindowInsets(getChildAt(r32), r4);
            r32 = r32 + 1;
            goto L6
        L8:
            return r4;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r2) {
            if (LaunchActivity.m15650$$Nest$fgetlayersActionBarLayout(this.this$0) == null) goto L5;
            LaunchActivity.m15650$$Nest$fgetlayersActionBarLayout(this.this$0).parentDraw(this, r2);
        L5:
            super.dispatchDraw(r2);
        }

        @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
            int r22 = getMeasuredWidth();
            getMeasuredHeight();
            if (AndroidUtilities.isTabletTwoPane() == false) goto L5;
            Insets r32 = this.insets;
            int r33 = AndroidUtilities.getTabletLeftFragmentSize(r22, r32.left, r32.right);
            this.this$0.actionBarLayout.getView().layout(0, 0, this.this$0.actionBarLayout.getView().getMeasuredWidth(), this.this$0.actionBarLayout.getView().getMeasuredHeight());
            this.this$0.rightActionBarLayout.getView().layout(r33, 0, this.this$0.rightActionBarLayout.getView().getMeasuredWidth() + r33, this.this$0.rightActionBarLayout.getView().getMeasuredHeight());
        L6:
            int r23 = (r22 - LaunchActivity.m15650$$Nest$fgetlayersActionBarLayout(this.this$0).getView().getMeasuredWidth()) / 2;
            int r34 = this.insets.top + AndroidUtilities.dp(8.0f);
            LaunchActivity.m15650$$Nest$fgetlayersActionBarLayout(this.this$0).getView().layout(r23, r34, LaunchActivity.m15650$$Nest$fgetlayersActionBarLayout(this.this$0).getView().getMeasuredWidth() + r23, LaunchActivity.m15650$$Nest$fgetlayersActionBarLayout(this.this$0).getView().getMeasuredHeight() + r34);
            LaunchActivity.m15648$$Nest$fgetbackgroundTablet(this.this$0).layout(0, 0, LaunchActivity.m15648$$Nest$fgetbackgroundTablet(this.this$0).getMeasuredWidth(), LaunchActivity.m15648$$Nest$fgetbackgroundTablet(this.this$0).getMeasuredHeight());
            LaunchActivity.m15654$$Nest$fgetshadowTablet(this.this$0).layout(0, 0, LaunchActivity.m15654$$Nest$fgetshadowTablet(this.this$0).getMeasuredWidth(), LaunchActivity.m15654$$Nest$fgetshadowTablet(this.this$0).getMeasuredHeight());
            return;
        L5:
            this.this$0.actionBarLayout.getView().layout(0, 0, this.this$0.actionBarLayout.getView().getMeasuredWidth(), this.this$0.actionBarLayout.getView().getMeasuredHeight());
            goto L6
        }

        @Override // android.widget.RelativeLayout, android.view.View
        public void onMeasure(int r7, int r8) {
            this.inLayout = true;
            int r72 = View.MeasureSpec.getSize(r7);
            int r82 = View.MeasureSpec.getSize(r8);
            setMeasuredDimension(r72, r82);
            boolean r1 = AndroidUtilities.isTabletTwoPane();
            LaunchActivity r2 = this.this$0;
            if (r1 == false) goto L5;
            LaunchActivity.m15662$$Nest$fputtabletFullSize(r2, false);
            Insets r0 = this.insets;
            int r02 = AndroidUtilities.getTabletLeftFragmentSize(r72, r0.left, r0.right);
            this.this$0.actionBarLayout.getView().measure(View.MeasureSpec.makeMeasureSpec(r02, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r82, TLObject.FLAG_30));
            this.this$0.rightActionBarLayout.getView().measure(View.MeasureSpec.makeMeasureSpec(r72 - r02, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r82, TLObject.FLAG_30));
        L6:
            LaunchActivity.m15648$$Nest$fgetbackgroundTablet(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r72, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r82, TLObject.FLAG_30));
            LaunchActivity.m15654$$Nest$fgetshadowTablet(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r72, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r82, TLObject.FLAG_30));
            ViewGroup r03 = LaunchActivity.m15650$$Nest$fgetlayersActionBarLayout(this.this$0).getView();
            int r73 = View.MeasureSpec.makeMeasureSpec(Math.min(AndroidUtilities.dp(500.0f), r72 - AndroidUtilities.dp(16.0f)), TLObject.FLAG_30);
            Insets r12 = this.insets;
            r03.measure(r73, View.MeasureSpec.makeMeasureSpec(((r82 - r12.top) - r12.bottom) - AndroidUtilities.dp(16.0f), TLObject.FLAG_30));
            this.inLayout = false;
            return;
        L5:
            LaunchActivity.m15662$$Nest$fputtabletFullSize(r2, true);
            this.this$0.actionBarLayout.getView().measure(View.MeasureSpec.makeMeasureSpec(r72, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r82, TLObject.FLAG_30));
            goto L6
        }

        @Override // android.widget.RelativeLayout, android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.inLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.LaunchActivity$11, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass11 extends SizeNotifierFrameLayout {
        final /* synthetic */ LaunchActivity this$0;

        public AnonymousClass11(LaunchActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public boolean isActionBarVisible() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.LaunchActivity$12, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass12 implements TermsOfServiceView.TermsOfServiceViewDelegate {
        final /* synthetic */ LaunchActivity this$0;

        /* renamed from: $r8$lambda$536X8cUMBzdC3R0SZs-Gwg-Y6EI, reason: not valid java name */
        public static /* synthetic */ void m15668$r8$lambda$536X8cUMBzdC3R0SZsGwgY6EI(AnonymousClass12 r0) {
            r0.lambda$onAcceptTerms$0();
        }

        public AnonymousClass12(LaunchActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onAcceptTerms$0() {
            LaunchActivity.m15655$$Nest$fgettermsOfServiceView(this.this$0).setVisibility(8);
        }

        @Override // org.telegram.ui.Components.TermsOfServiceView.TermsOfServiceViewDelegate
        public void onAcceptTerms(int r3) {
            UserConfig.getInstance(r3).unacceptedTermsOfService = null;
            UserConfig.getInstance(r3).saveConfig(false);
            if (LaunchActivity.m15652$$Nest$fgetmainFragmentsStack(this.this$0).isEmpty() == true) goto L5;
            ((BaseFragment) LaunchActivity.m15652$$Nest$fgetmainFragmentsStack(this.this$0).get(LaunchActivity.m15652$$Nest$fgetmainFragmentsStack(this.this$0).size() - 1)).onResume();
        L5:
            LaunchActivity.m15655$$Nest$fgettermsOfServiceView(this.this$0).animate().alpha(0.0f).setDuration(150).setInterpolator(AndroidUtilities.accelerateInterpolator).withEndAction(new LaunchActivity$12$$ExternalSyntheticLambda0(this)).start();
        }
    }

    /* renamed from: org.telegram.ui.LaunchActivity$13, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass13 implements ChatRightsEditActivity.ChatRightsEditActivityDelegate {
        final /* synthetic */ LaunchActivity this$0;
        final /* synthetic */ DialogsActivity val$fragment;
        final /* synthetic */ int val$intentAccount;

        public AnonymousClass13(LaunchActivity r1, DialogsActivity r2, int r3) {
            this.this$0 = r1;
            this.val$fragment = r2;
            this.val$intentAccount = r3;
        }

        @Override // org.telegram.ui.ChatRightsEditActivity.ChatRightsEditActivityDelegate
        public void didChangeOwner(TLRPC.User r1) {
        }

        @Override // org.telegram.ui.ChatRightsEditActivity.ChatRightsEditActivityDelegate
        public void didSetRights(int r1, TLRPC.TL_chatAdminRights r2, TLRPC.TL_chatBannedRights r3, String r4) {
            this.val$fragment.removeSelfFromStack();
            NotificationCenter.getInstance(this.val$intentAccount).postNotificationName(NotificationCenter.closeChats, new Object[0]);
        }
    }

    /* renamed from: org.telegram.ui.LaunchActivity$14, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass14 implements MessagesController.MessagesLoadedCallback {
        final /* synthetic */ LaunchActivity this$0;
        final /* synthetic */ Bundle val$args;
        final /* synthetic */ long val$dialog_id;
        final /* synthetic */ Runnable val$dismissLoading;
        final /* synthetic */ BaseFragment val$lastFragment;
        final /* synthetic */ String val$livestream;
        final /* synthetic */ Integer val$messageId;

        public static /* synthetic */ void $r8$lambda$PilRTk06mVDI1bqJINwZm7spGfw(AnonymousClass14 r0, AccountInstance r1, long r2, BaseFragment r4) {
            r0.lambda$onMessagesLoaded$1(r1, r2, r4);
        }

        public static /* synthetic */ void $r8$lambda$ibJOIui5JbM403nQu4ZENp5Pqg8(AnonymousClass14 r0, String r1, long r2, BaseFragment r4) {
            r0.lambda$onMessagesLoaded$2(r1, r2, r4);
        }

        public static /* synthetic */ void $r8$lambda$v6QOk2CuwcUm4oOiNIguVJLVmGA(AnonymousClass14 r0, AccountInstance r1, long r2, BaseFragment r4) {
            r0.lambda$onMessagesLoaded$0(r1, r2, r4);
        }

        public AnonymousClass14(LaunchActivity r1, Runnable r2, String r3, BaseFragment r4, long r5, Integer r7, Bundle r8) {
            this.this$0 = r1;
            this.val$dismissLoading = r2;
            this.val$livestream = r3;
            this.val$lastFragment = r4;
            this.val$dialog_id = r5;
            this.val$messageId = r7;
            this.val$args = r8;
        }

        private /* synthetic */ void lambda$onMessagesLoaded$0(AccountInstance r16, long r17, BaseFragment r19) {
            long r3 = -r17;
            boolean r5 = false;
            ChatObject.Call r2 = r16.getMessagesController().getGroupCall(r3, false);
            TLRPC.Chat r7 = r16.getMessagesController().getChat(Long.valueOf(r3));
            TLRPC.InputPeer r8 = r16.getMessagesController().getInputPeer(r17);
            if (r2 != null) goto L5;
        L6:
            r5 = true;
        L7:
            VoIPHelper.startCall(r7, r8, null, false, Boolean.valueOf(r5), this.this$0, r19, r16);
            return;
        L5:
            if (r2.call.rtmp_stream == true) goto L7;
            goto L6
        }

        private /* synthetic */ void lambda$onMessagesLoaded$1(AccountInstance r7, long r8, BaseFragment r10) {
            AndroidUtilities.runOnUIThread(new LaunchActivity$14$$ExternalSyntheticLambda2(this, r7, r8, r10));
        }

        private /* synthetic */ void lambda$onMessagesLoaded$2(String r10, long r11, BaseFragment r13) {
            if (r10 == null) goto L17;
            AccountInstance r2 = AccountInstance.getInstance(this.this$0.currentAccount);
            long r6 = -r11;
            if (r2.getMessagesController().getGroupCall(r6, false) == null) goto L7;
            VoIPHelper.startCall(r2.getMessagesController().getChat(Long.valueOf(r6)), r2.getMessagesController().getInputPeer(r11), null, false, Boolean.valueOf(!r10.call.rtmp_stream), this.this$0, r13, r2);
            return;
        L7:
            TLRPC.ChatFull r102 = r2.getMessagesController().getChatFull(r6);
            if (r102 != null) goto L10;
            return;
        L10:
            if (r102.call == null) goto L12;
            r2.getMessagesController().getGroupCall(r6, true, new LaunchActivity$14$$ExternalSyntheticLambda1(this, r2, r11, r13));
            return;
        L12:
            if (r13.getParentActivity() == null) goto L19;
            BulletinFactory.of(r13).createSimpleBulletin(R.raw.linkbroken, LocaleController.getString(R.string.InviteExpired)).show();
            return;
        L19:
            return;
        }

        @Override // org.telegram.messenger.MessagesController.MessagesLoadedCallback
        public void onError() {
            if (this.this$0.isFinishing() == true) goto L10;
            AlertsCreator.showSimpleAlert((BaseFragment) LaunchActivity.m15652$$Nest$fgetmainFragmentsStack(this.this$0).get(LaunchActivity.m15652$$Nest$fgetmainFragmentsStack(this.this$0).size() - 1), LocaleController.getString(R.string.JoinToGroupErrorNotExist));
        L10:
            this.val$dismissLoading.run();     // Catch: Exception -> L7
            return;
        L7:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // org.telegram.messenger.MessagesController.MessagesLoadedCallback
        public void onMessagesLoaded(boolean r8) {
            this.val$dismissLoading.run();     // Catch: Exception -> L4
        L7:
            if (this.this$0.isFinishing() == false) goto L9;
            return;
        L9:
            if (this.val$livestream == null) goto L17;
            BaseFragment r82 = this.val$lastFragment;
            if ((r82 instanceof ChatActivity) == false) goto L17;
            if (((ChatActivity) r82).getDialogId() != this.val$dialog_id) goto L17;
            BaseFragment r83 = this.val$lastFragment;
        L33:
            AndroidUtilities.runOnUIThread(new LaunchActivity$14$$ExternalSyntheticLambda0(this, this.val$livestream, this.val$dialog_id, r83), 150);
            return;
        L17:
            BaseFragment r84 = this.val$lastFragment;
            if ((r84 instanceof ChatActivity) == true) goto L20;
        L32:
            r83 = new ChatActivity(this.val$args);
            this.this$0.getActionBarLayout().presentFragment(r83);
            goto L33
        L20:
            if (((ChatActivity) r84).getDialogId() != this.val$dialog_id) goto L32;
            if (this.val$messageId != null) goto L32;
            ChatActivity r85 = (ChatActivity) this.val$lastFragment;
            AndroidUtilities.shakeViewSpring(r85.getChatListView(), 5.0f);
            BotWebViewVibrationEffect.APP_ERROR.vibrate();
            ChatActivityEnterView r0 = r85.getChatActivityEnterView();
            int r2 = 0;
            int r3 = 0;
        L25:
            if (r3 >= r0.getChildCount()) goto L27;
            AndroidUtilities.shakeViewSpring(r0.getChildAt(r3), 5.0f);
            r3 = r3 + 1;
            goto L25
        L27:
            ActionBar r86 = r85.getActionBar();
        L29:
            if (r2 >= r86.getChildCount()) goto L31;
            AndroidUtilities.shakeViewSpring(r86.getChildAt(r2), 5.0f);
            r2 = r2 + 1;
            goto L29
        L31:
            r83 = this.val$lastFragment;
        L4:
            e = move-exception;
            FileLog.e(e);
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.LaunchActivity$15, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass15 implements MessagesController.MessagesLoadedCallback {
        final /* synthetic */ LaunchActivity this$0;
        final /* synthetic */ Bundle val$args;
        final /* synthetic */ boolean[] val$canceled;
        final /* synthetic */ Runnable val$dismissLoading;
        final /* synthetic */ TLRPC.ChatInvite val$invite;

        public AnonymousClass15(LaunchActivity r1, Runnable r2, boolean[] r3, Bundle r4, TLRPC.ChatInvite r5) {
            this.this$0 = r1;
            this.val$dismissLoading = r2;
            this.val$canceled = r3;
            this.val$args = r4;
            this.val$invite = r5;
        }

        @Override // org.telegram.messenger.MessagesController.MessagesLoadedCallback
        public void onError() {
            if (this.this$0.isFinishing() == true) goto L10;
            AlertsCreator.showSimpleAlert((BaseFragment) LaunchActivity.m15652$$Nest$fgetmainFragmentsStack(this.this$0).get(LaunchActivity.m15652$$Nest$fgetmainFragmentsStack(this.this$0).size() - 1), LocaleController.getString(R.string.JoinToGroupErrorNotExist));
        L10:
            this.val$dismissLoading.run();     // Catch: Exception -> L7
            return;
        L7:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // org.telegram.messenger.MessagesController.MessagesLoadedCallback
        public void onMessagesLoaded(boolean r3) {
            this.val$dismissLoading.run();     // Catch: Exception -> L4
        L7:
            if (this.val$canceled[0] == false) goto L9;
            return;
        L9:
            ChatActivity r32 = new ChatActivity(this.val$args);
            TLRPC.ChatInvite r0 = this.val$invite;
            if ((r0 instanceof TLRPC.TL_chatInvitePeek) == false) goto L12;
            r32.setChatInvite(r0);
        L12:
            this.this$0.getActionBarLayout().presentFragment(r32);
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.LaunchActivity$16, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass16 extends ClickableSpan {
        final /* synthetic */ LaunchActivity this$0;

        public AnonymousClass16(LaunchActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r2) {
            this.this$0.getActionBarLayout().presentFragment(new PremiumPreviewFragment("gift"));
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            super.updateDrawState(r1);
            r1.setUnderlineText(false);
        }
    }

    /* renamed from: org.telegram.ui.LaunchActivity$17, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass17 extends AnimatorListenerAdapter {
        final /* synthetic */ LaunchActivity this$0;
        final /* synthetic */ RLottieImageView val$darkThemeView;
        final /* synthetic */ RLottieDrawable val$drawable;
        final /* synthetic */ boolean val$toDark;
        final /* synthetic */ RLottieDrawable val$transitionDrawable;

        public AnonymousClass17(LaunchActivity r1, RLottieDrawable r2, RLottieImageView r3, RLottieDrawable r4, boolean r5) {
            this.this$0 = r1;
            this.val$transitionDrawable = r2;
            this.val$darkThemeView = r3;
            this.val$drawable = r4;
            this.val$toDark = r5;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            LaunchActivity.m15660$$Nest$fputrippleAbove(this.this$0, null);
            this.this$0.drawerLayoutContainer.invalidate();
            LaunchActivity.m15656$$Nest$fgetthemeSwitchImageView(this.this$0).invalidate();
            LaunchActivity.m15656$$Nest$fgetthemeSwitchImageView(this.this$0).setImageDrawable(null);
            LaunchActivity.m15656$$Nest$fgetthemeSwitchImageView(this.this$0).setVisibility(8);
            RLottieDrawable r42 = this.val$transitionDrawable;
            if (r42 == null) goto L5;
            r42.stop();
        L5:
            LaunchActivity.m15657$$Nest$fgetthemeSwitchSunView(this.this$0).setImageDrawable(null);
            LaunchActivity.m15657$$Nest$fgetthemeSwitchSunView(this.this$0).setVisibility(8);
            RLottieImageView r43 = this.val$darkThemeView;
            if (r43 == null) goto L8;
            r43.setImageDrawable(this.val$drawable);
            this.val$darkThemeView.invalidate();
        L8:
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.themeAccentListUpdated, new Object[0]);
            if (this.val$toDark == true) goto L13;
            RLottieImageView r3 = this.val$darkThemeView;
            if (r3 == null) goto L13;
            r3.setVisibility(0);
        L13:
            DialogsActivity.switchingTheme = false;
        }
    }

    /* renamed from: org.telegram.ui.LaunchActivity$18, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass18 implements Runnable {
        final /* synthetic */ LaunchActivity this$0;

        public AnonymousClass18(LaunchActivity r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (LaunchActivity.m15651$$Nest$fgetlockRunnable(this.this$0) == this) goto L5;
            return;
        L5:
            if (AndroidUtilities.needShowPasscode(true) == false) goto L15;
            if (BuildVars.LOGS_ENABLED == false) goto L9;
            FileLog.d("lock app");
        L9:
            this.this$0.showPasscodeActivity(true, false, -1, -1, null, null);
            NotificationsController.getInstance(UserConfig.selectedAccount).showNotifications();     // Catch: Exception -> L12
        L17:
            LaunchActivity.m15659$$Nest$fputlockRunnable(this.this$0, null);
            return;
        L12:
            e = move-exception;
            FileLog.e(e);
            goto L17
        L15:
            if (BuildVars.LOGS_ENABLED == false) goto L17;
            FileLog.d("didn't pass lock check");
            goto L17
        }
    }

    /* renamed from: org.telegram.ui.LaunchActivity$19, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass19 extends AnimatorListenerAdapter {
        final /* synthetic */ LaunchActivity this$0;
        final /* synthetic */ int val$toColor;

        public AnonymousClass19(LaunchActivity r1, int r2) {
            this.this$0 = r1;
            this.val$toColor = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.setNavigationBarColor(this.val$toColor);
        }
    }

    /* renamed from: org.telegram.ui.LaunchActivity$2, reason: invalid class name */
    public class AnonymousClass2 implements IPipActivityListener {
        final ActivityVisibilityController activityVisibilityController;
        final /* synthetic */ LaunchActivity this$0;

        public AnonymousClass2(LaunchActivity r2) {
            this.this$0 = r2;
            this.activityVisibilityController = LaunchActivity.m15664$$Nest$mcreateActivityVisibilityController(r2, false);
        }

        @Override // org.telegram.messenger.pip.activity.IPipActivityListener
        public void onCompleteEnterToPip() {
            LaunchActivity.m15658$$Nest$fputisInPictureInPictureMode(this.this$0, true);
            this.activityVisibilityController.hide();
            LaunchActivity.m15663$$Nest$mcheckDecorViewVisibility(this.this$0);
        }

        @Override // org.telegram.messenger.pip.activity.IPipActivityListener
        public void onCompleteExitFromPip(boolean r2) {
            LaunchActivity.m15658$$Nest$fputisInPictureInPictureMode(this.this$0, false);
            LaunchActivity.m15663$$Nest$mcheckDecorViewVisibility(this.this$0);
        }

        @Override // org.telegram.messenger.pip.activity.IPipActivityListener
        public void onStartExitFromPip(boolean r1) {
            this.activityVisibilityController.show();
        }
    }

    /* renamed from: org.telegram.ui.LaunchActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements View.OnLayoutChangeListener {
        final /* synthetic */ LaunchActivity this$0;
        private boolean wasPortrait;

        /* renamed from: $r8$lambda$wSD-oyr0VeHeAL3LXXjQS7TvmGE, reason: not valid java name */
        public static /* synthetic */ void m15669$r8$lambda$wSDoyr0VeHeAL3LXXjQS7TvmGE(AnonymousClass3 r0) {
            r0.lambda$onLayoutChange$0();
        }

        public AnonymousClass3(LaunchActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onLayoutChange$0() {
            if (LaunchActivity.m15653$$Nest$fgetselectAnimatedEmojiDialog(this.this$0) == null) goto L6;
            LaunchActivity.m15653$$Nest$fgetselectAnimatedEmojiDialog(this.this$0).dismiss();
            LaunchActivity.m15661$$Nest$fputselectAnimatedEmojiDialog(this.this$0, null);
            return;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
            if ((r5 - r3) <= (r4 - r2)) goto L5;
            boolean r12 = true;
        L7:
            if (r12 == this.wasPortrait) goto L10;
            AndroidUtilities.runOnUIThread(new LaunchActivity$3$$ExternalSyntheticLambda0(this));
            this.wasPortrait = r12;
            return;
        L10:
            return;
        L5:
            r12 = false;
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.LaunchActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends ImageView {
        final /* synthetic */ LaunchActivity this$0;

        public AnonymousClass4(LaunchActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.ImageView, android.view.View
        public void onDraw(Canvas r1) {
            super.onDraw(r1);
            invalidate();
        }
    }

    /* renamed from: org.telegram.ui.LaunchActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends FireworksOverlay {
        final /* synthetic */ LaunchActivity this$0;

        public AnonymousClass5(LaunchActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            setVisibility(8);
        }

        @Override // org.telegram.ui.Components.FireworksOverlay
        public void onStop() {
            super.onStop();
            setVisibility(8);
        }

        @Override // org.telegram.ui.Components.FireworksOverlay
        public void start(boolean r2) {
            setVisibility(0);
            super.start(r2);
        }
    }

    /* renamed from: org.telegram.ui.LaunchActivity$6, reason: invalid class name */
    public class AnonymousClass6 implements View.OnAttachStateChangeListener {
        final /* synthetic */ LaunchActivity this$0;

        public AnonymousClass6(LaunchActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r1) {
            this.this$0.getWindowManager().addCrossWindowBlurEnabledListener(LaunchActivity.m15649$$Nest$fgetblurListener(this.this$0));
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r1) {
            this.this$0.getWindowManager().removeCrossWindowBlurEnabledListener(LaunchActivity.m15649$$Nest$fgetblurListener(this.this$0));
        }
    }

    /* renamed from: org.telegram.ui.LaunchActivity$7, reason: invalid class name */
    public class AnonymousClass7 implements Bulletin.Delegate {
        final /* synthetic */ LaunchActivity this$0;

        public AnonymousClass7(LaunchActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getBottomOffset(int r1) {
            return AndroidUtilities.navigationBarHeight + AndroidUtilities.dp(16.0f);
        }
    }

    /* renamed from: org.telegram.ui.LaunchActivity$8, reason: invalid class name */
    public class AnonymousClass8 implements OnBackAnimationCallback {
        private boolean drawerPredictiveBackStarted;
        private boolean invoked;
        private boolean locked;
        private AnimationNotificationsLocker locker;
        private boolean predictiveBackStarted;
        private boolean started;
        final /* synthetic */ LaunchActivity this$0;

        public AnonymousClass8(LaunchActivity r1) {
            this.this$0 = r1;
            this.locker = new AnimationNotificationsLocker();
            this.started = false;
            this.invoked = false;
            this.drawerPredictiveBackStarted = false;
        }

        private void onBackStartedInternal(BackEvent r4) {
            if (AndroidUtilities.isTablet() == false) goto L6;
            return;
        L6:
            if (this.this$0.onBackPressed(false) == false) goto L28;
            DrawerLayoutContainer r0 = this.this$0.drawerLayoutContainer;
            if (r0 != null) goto L11;
        L16:
            ActionBarLayout r02 = this.this$0.actionBarLayout;
            if (r02 != null) goto L19;
            return;
        L19:
            if (r02.onBackStarted(r4.getTouchX(), r4.getTouchY()) == true) goto L21;
            return;
        L21:
            if (this.locked == true) goto L26;
            this.locker.lock();
            this.locked = true;
            return;
        L26:
            return;
        L11:
            if (r0.getDrawerContainer() == null) goto L16;
            if (this.this$0.drawerLayoutContainer.getDrawerContainer().startPredictiveBack() == false) goto L16;
            this.drawerPredictiveBackStarted = true;
            return;
        }

        public void onBackCancelled() {
            this.started = false;
            this.invoked = false;
            if (this.locked == false) goto L6;
            this.locker.unlock();
            this.locked = false;
        L6:
            if (AndroidUtilities.isTablet() == false) goto L9;
            return;
        L9:
            if (this.drawerPredictiveBackStarted == false) goto L16;
            DrawerLayoutContainer r1 = this.this$0.drawerLayoutContainer;
            if (r1 == null) goto L16;
            if (r1.getDrawerContainer() == null) goto L16;
            this.this$0.drawerLayoutContainer.getDrawerContainer().cancelPredictiveBack();
            this.drawerPredictiveBackStarted = false;
            return;
        L16:
            ActionBarLayout r2 = this.this$0.actionBarLayout;
            if (r2 == null) goto L20;
            r2.onBackCancelled();
            return;
        }

        public void onBackInvoked() {
            this.invoked = true;
            if (this.locked == false) goto L5;
            this.locker.unlock();
            this.locked = false;
        L5:
            boolean r1 = AndroidUtilities.isTablet();
            LaunchActivity r2 = this.this$0;
            if (r1 == false) goto L10;
            r2.onBackPressed();
            return;
        L10:
            if (r2.onBackPressed(true) == true) goto L13;
            return;
        L13:
            if (this.drawerPredictiveBackStarted == false) goto L20;
            DrawerLayoutContainer r0 = this.this$0.drawerLayoutContainer;
            if (r0 == null) goto L20;
            if (r0.getDrawerContainer() == null) goto L20;
            this.this$0.drawerLayoutContainer.getDrawerContainer().commitPredictiveBack();
            return;
        L20:
            LaunchActivity r3 = this.this$0;
            ActionBarLayout r02 = r3.actionBarLayout;
            if (r02 == null) goto L24;
            r02.onBackInvoked();
            return;
        L24:
            r3.onBackPressed();
        }

        public void onBackProgressed(BackEvent r4) {
            if (this.started == true) goto L5;
        L7:
            float r0 = r4.getProgress();
            if (this.predictiveBackStarted == false) goto L10;
        L12:
            float r02 = Math.max(0.0f, r0 - 0.015f) / 0.985f;
            if (AndroidUtilities.isTablet() == false) goto L16;
            return;
        L16:
            if (this.drawerPredictiveBackStarted == false) goto L23;
            DrawerLayoutContainer r1 = this.this$0.drawerLayoutContainer;
            if (r1 == null) goto L23;
            if (r1.getDrawerContainer() == null) goto L23;
            this.this$0.drawerLayoutContainer.getDrawerContainer().updatePredictiveBackProgress(r02);
            return;
        L23:
            ActionBarLayout r3 = this.this$0.actionBarLayout;
            if (r3 == null) goto L27;
            r3.onBackProgress(r02, r4.getTouchY());
            return;
        L27:
            return;
        L10:
            if (r0 <= 0.015f) goto L12;
            this.predictiveBackStarted = true;
            onBackStartedInternal(r4);
            goto L12
        L5:
            if (this.invoked == false) goto L7;
        }

        public void onBackStarted(BackEvent r1) {
            this.started = true;
            this.invoked = false;
            this.predictiveBackStarted = false;
            this.drawerPredictiveBackStarted = false;
        }
    }

    /* renamed from: org.telegram.ui.LaunchActivity$9, reason: invalid class name */
    public class AnonymousClass9 implements OnBackInvokedCallback {
        final /* synthetic */ LaunchActivity this$0;

        public AnonymousClass9(LaunchActivity r1) {
            this.this$0 = r1;
        }

        public void onBackInvoked() {
            boolean r0 = AndroidUtilities.isTablet();
            LaunchActivity r1 = this.this$0;
            if (r0 == false) goto L7;
            r1.onBackPressed();
            return;
        L7:
            if (r1.onBackPressed(true) == true) goto L9;
            return;
        L9:
            LaunchActivity r2 = this.this$0;
            ActionBarLayout r02 = r2.actionBarLayout;
            if (r02 == null) goto L13;
            r02.onBackInvoked();
            return;
        L13:
            r2.onBackPressed();
        }
    }

    /* loaded from: classes6.dex */
    public class ActivityContentLayout extends FrameLayout {
        final /* synthetic */ LaunchActivity this$0;

        public ActivityContentLayout(LaunchActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public WindowInsets dispatchApplyWindowInsets(WindowInsets r1) {
            return AndroidUtilities.fixedDispatchApplyWindowInsets(r1, this);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r2) {
            super.dispatchDraw(r2);
            LaunchActivity.m15665$$Nest$mdrawRippleAbove(this.this$0, r2, this);
        }
    }

    public static class ActivityVisibilityController implements WindowVisibilityManager.Controller {
        private final WeakReference<LaunchActivity> activity;
        private boolean destroyed;
        private boolean hidden;
        private final boolean withDecorView;

        private ActivityVisibilityController(LaunchActivity r2, boolean r3) {
            this.activity = new WeakReference(r2);
            this.withDecorView = r3;
        }

        public /* synthetic */ ActivityVisibilityController(LaunchActivity r1, boolean r2, LaunchActivityIA r3) {
            this(r1, r2);
        }

        @Override // org.telegram.messenger.utils.WindowVisibilityManager.Controller
        public void destroy() {
            setHidden(false);
            this.destroyed = true;
        }

        public void hide() {
            setHidden(true);
        }

        @Override // org.telegram.messenger.utils.WindowVisibilityManager.Controller
        public void setHidden(boolean r2) {
            if (this.hidden != r2) goto L5;
            return;
        L5:
            if (this.destroyed == true) goto L11;
            this.hidden = r2;
            LaunchActivity r0 = this.activity.get();
            if (r0 == null) goto L12;
            LaunchActivity.m15666$$Nest$mupdateReasonsToHideMainContent(r0, r2, this.withDecorView);
            return;
        L12:
            return;
        }

        public void show() {
            setHidden(false);
        }
    }

    public static /* synthetic */ void $r8$lambda$0cTE1m4sKirN0nsnPBTI8Fhk3Ws(LaunchActivity r0, TLObject r1, Uri r2, int r3, AlertDialog r4) {
        r0.lambda$runImportRequest$36(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$1Agd4Lwd9EjIhtn-Z1f5cHBrTz0, reason: not valid java name */
    public static /* synthetic */ void m15587$r8$lambda$1Agd4Lwd9EjIhtnZ1f5cHBrTz0(LaunchActivity r0, TLRPC.TL_error r1, TLObject r2, int r3, AlertDialog r4, Runnable r5, String r6) {
        r0.lambda$runLinkRequest$88(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$1QkEMoqxFZeMeHSc-QD0ImaUulY, reason: not valid java name */
    public static /* synthetic */ void m15588$r8$lambda$1QkEMoqxFZeMeHScQD0ImaUulY(LaunchActivity r0, int[] r1, LocationController.SharingLocationInfo r2) {
        r0.lambda$handleIntent$18(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$1zht5wYfxHG6SJ4FJ9_4BoLUEN4(LaunchActivity r0, AlertDialog r1, int r2) {
        r0.lambda$didReceivedNotification$154(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$2poBaxj0Ut_Z3tFnLDr6NcWnDtE(LaunchActivity r0, String r1, String r2, int r3, TLRPC.Chat r4, DialogsActivity r5, TLRPC.User r6, long r7, boolean r9, TLRPC.TL_chatAdminRights r10, String r11) {
        r0.lambda$runLinkRequest$77(r1, r2, r3, r4, r5, r6, r7, r9, r10, r11);
    }

    /* renamed from: $r8$lambda$3kivSecf8yzvyZRNhRK-4HCn1MA, reason: not valid java name */
    public static /* synthetic */ void m15589$r8$lambda$3kivSecf8yzvyZRNhRK4HCn1MA(LaunchActivity r0, BaseFragment r1, String r2, String r3, AlertDialog r4, int r5) {
        r0.lambda$handleIntent$28(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$4Kenb9n2if9N3PjP71bb4naUL74(LaunchActivity r0, Runnable r1, TLRPC.TL_error r2) {
        r0.lambda$runLinkRequest$96(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$5XCA6PleS3AZmsQvC4reAqp6SHQ(LaunchActivity r0, int r1, String r2, Runnable r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$runLinkRequest$52(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$5yJmt7eer039p7GDxu1DnBa5ut0(Browser.Progress r0) {
        if (r0 == null) goto L5;
        r0.end();
        return;
    }

    public static /* synthetic */ void $r8$lambda$653WXcvVO59kRp283EbgnEyh4fE(Runnable r1, String r2) {
        if (r1 != null) goto L4;
        return;
    L4:
        if ("paid".equals(r2) == false) goto L8;
        r1.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$65VHYkg1vsJ3EsSWZZHCDAFY2UU(View r0) {
    }

    public static /* synthetic */ void $r8$lambda$6rOE20Aa7Tkl_QIvaqTRmjXycxA(LaunchActivity r0, Runnable r1, TLObject r2) {
        r0.lambda$runLinkRequest$98(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$74IFvoNZAmzyamOEGsdY6qoRRJQ(Runnable r0) {
        r0.run();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    /* renamed from: $r8$lambda$7BMLBJnOGvoXC-GffQ2vnbaa7rE, reason: not valid java name */
    public static /* synthetic */ void m15590$r8$lambda$7BMLBJnOGvoXCGffQ2vnbaa7rE(LaunchActivity r0, Runnable r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$runLinkRequest$100(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$7DdopUEnlivX3Qa1pdyRtHaMm2k(LaunchActivity r0, TLRPC.User r1) {
        r0.lambda$processAttachedMenuBotFromShortcut$123(r1);
    }

    public static /* synthetic */ void $r8$lambda$7MjKMS6RK0Vbq1Lpb8sy8ME2JYo(LaunchActivity r0, TLObject r1, int r2, DialogsActivity r3, BaseFragment r4, TLRPC.User r5, String r6) {
        r0.lambda$processAttachMenuBot$130(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$7OCWrtf1ocrnpR8uBUKIZ1pYtGI(LaunchActivity r0, Browser.Progress r1, TLObject r2, long r3, int r5, Integer r6, byte[] r7, BaseFragment r8, Bundle r9, ChatActivity r10, String r11, int r12) {
        r0.lambda$openMessage$41(r1, r2, r3, r5, r6, r7, r8, r9, r10, r11, r12);
    }

    public static /* synthetic */ void $r8$lambda$7VBDVa0K1b7vZfz8SMRrGH1dD_M(LaunchActivity r0, boolean r1, int r2, Browser.Progress r3, TLRPC.TL_help_appUpdate r4, TLRPC.TL_error r5) {
        r0.lambda$checkAppUpdate$139(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$8C-mFJHdjSO1Klo0Ro2VNg4Hjt4, reason: not valid java name */
    public static /* synthetic */ void m15591$r8$lambda$8CmFJHdjSO1Klo0Ro2VNg4Hjt4(LaunchActivity r0, Browser.Progress r1, Long r2, TL_stories.TL_premium_boostsStatus r3, ChatMessageCell r4, Runnable r5, ChannelBoostsController.CanApplyBoost r6) {
        r0.lambda$processBoostDialog$126(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$8W39nJXJOoLxKWCo0g5DcxcUsrw(LaunchActivity r0) {
        r0.lambda$onActivityResult$149();
    }

    public static /* synthetic */ void $r8$lambda$8c0XqjUbvXYcw3OXKAJy9_vld7I(LaunchActivity r0, AtomicBoolean r1, TLObject r2, int r3, boolean r4, TLRPC.Chat r5, Long r6, Integer r7, Integer r8, Runnable r9, String r10, Integer r11, byte[] r12, int r13, int r14, TLRPC.TL_messages_getDiscussionMessage r15, WidePosts.CommentsPostAuthorLoader r16, Runnable r17) {
        r0.lambda$runCommentRequest$32(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17);
    }

    public static /* synthetic */ void $r8$lambda$8d2esPnNafiwrYgGt0X6IyVhxXk(LaunchActivity r0, ValueAnimator r1) {
        r0.lambda$animateNavigationBarColor$183(r1);
    }

    public static /* synthetic */ void $r8$lambda$9keRobloW2haDiRX6TXBAuURyO4(LaunchActivity r0, HashMap r1, int r2, AlertDialog r3, int r4) {
        r0.lambda$didReceivedNotification$156(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$AO4XFBhJMV92sWVv3M3W1R3UIIo(LaunchActivity r0, int r1, DialogsActivity r2, BaseFragment r3, TLRPC.User r4, String r5, TLObject r6, TLRPC.TL_error r7) {
        r0.lambda$processAttachMenuBot$131(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$AUPXq_TylsEvVkjqCgmJBcxuIR4(LaunchActivity r0, Runnable r1, TLObject r2, int r3, TL_account.authorizationForm r4, TL_account.getAuthorizationForm r5, String r6, String r7, String r8) {
        r0.lambda$runLinkRequest$94(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ MainTabsActivity $r8$lambda$AgtVsDJu6qeK8DrzrChE17ZgC88(Void r0) {
        return new MainTabsActivity();
    }

    public static /* synthetic */ void $r8$lambda$Bfpgs566727C9nmZ_VUB9DTbeuc(BaseFragment r2, long r3, TLRPC.Chat r5) {
        BulletinFactory.of(r2).createSimpleBulletin(R.raw.stars_send, LocaleController.getString(R.string.StarsSubscriptionCompleted), AndroidUtilities.replaceTags(LocaleController.formatPluralString("StarsSubscriptionCompletedText", (int) r3, new Object[]{r5.title}))).show(true);
    }

    public static /* synthetic */ void $r8$lambda$Bh7_uw6bEZXmenYdcNSYtZzKmBI(TLObject r2, int r3) {
        if ((r2 instanceof TLRPC.TL_boolTrue) == false) goto L6;
        MediaDataController.getInstance(r3).loadAttachMenuBots(false, true, null);
        return;
    }

    public static /* synthetic */ void $r8$lambda$C5OAF2qwwZdi93KautJFllzx1C0(LaunchActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$handleIntent$21(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$CHv83ZVhVnspKNLenCnNY3sPBGY(LaunchActivity r0, Runnable r1, TL_payments.TL_StarGiftAuctionState r2, TLRPC.TL_error r3) {
        r0.lambda$runLinkRequest$54(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$DBMymuJryo94pVTqRpIQr3tndpk(LaunchActivity r0, AlertDialog r1, DialogInterface r2) {
        r0.lambda$showAlertDialog$145(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$DDOCmwt7UXAtUfyR4lE720_wK_U(View r3) {
        int r32 = r3.getMeasuredHeight();
        FileLog.d("height = " + r32 + " displayHeight = " + AndroidUtilities.displaySize.y);
        int r33 = (r32 - AndroidUtilities.navigationBarHeight) - AndroidUtilities.statusBarHeight;
        if (r33 > AndroidUtilities.dp(100.0f)) goto L5;
        return;
    L5:
        if (r33 >= AndroidUtilities.displaySize.y) goto L13;
        int r0 = AndroidUtilities.dp(100.0f) + r33;
        Point r1 = AndroidUtilities.displaySize;
        if (r0 <= r1.y) goto L14;
        r1.y = r33;
        if (BuildVars.LOGS_ENABLED == false) goto L15;
        FileLog.d("fix display size y to " + AndroidUtilities.displaySize.y);
        return;
    L15:
        return;
    L14:
        return;
    }

    public static /* synthetic */ void $r8$lambda$DbEAkL6ZWA7oA5rRzFAWooog7Ks(boolean[] r1, DialogInterface r2) {
        r1[0] = true;
    }

    public static /* synthetic */ void $r8$lambda$DdYKJovHzmXSs8g20atvTZFkxbs(LaunchActivity r0, ActionIntroActivity r1, String r2) {
        r0.lambda$handleIntent$27(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$EMJo6z54DlgZXpyvrD5f1BSk2Qw(LaunchActivity r0, int r1, AlertDialog r2, int r3) {
        r0.lambda$didReceivedNotification$153(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$EpoPWDsQwZpS2LnMy6_Qu3YwKko(LaunchActivity r0, TLRPC.TL_attachMenuBot r1, int r2, Long r3, String r4, String r5, String r6, String r7, String r8, String r9, String r10, String r11, String r12, String r13, String r14, String r15, boolean r16, Integer r17, Long r18, Long r19, Integer r20, String r21, HashMap r22, String r23, String r24, String r25, String r26, TLRPC.TL_wallPaper r27, String r28, String r29, String r30, String r31, boolean r32, String r33, int r34, int r35, String r36, String r37, String r38, String r39, String r40, Browser.Progress r41, boolean r42, int r43, int r44, int r45, boolean r46, String r47, TLRPC.User r48, Runnable r49, boolean r50, boolean r51, boolean r52, boolean r53, boolean r54, boolean r55, String r56, boolean r57, Boolean r58) {
        r0.lambda$runLinkRequest$68(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58);
    }

    /* renamed from: $r8$lambda$F-tpp20y5u6CkXcEYMM1PtNdVA0, reason: not valid java name */
    public static /* synthetic */ void m15592$r8$lambda$Ftpp20y5u6CkXcEYMM1PtNdVA0() {
        ApplicationLoader.mainInterfacePausedStageQueue = false;
        ApplicationLoader.mainInterfacePausedStageQueueTime = System.currentTimeMillis();
    }

    public static /* synthetic */ void $r8$lambda$F3S0tbIDNp9jhgT0GU73GvVC9dw(LaunchActivity r0, int r1, String r2, Runnable r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$runLinkRequest$56(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$F95KlDR6z-aq-myeYZhFQI4vz6U, reason: not valid java name */
    public static /* synthetic */ void m15593$r8$lambda$F95KlDR6zaqmyeYZhFQI4vz6U(LaunchActivity r0, DialogInterface r1) {
        r0.lambda$checkFreeDiscSpace$172(r1);
    }

    public static /* synthetic */ void $r8$lambda$FTAUwwwsz0jkbkcuetu9029RmJE(LaunchActivity r0, HashMap r1, LocaleController.LocaleInfo[] r2, String r3) {
        r0.lambda$showLanguageAlert$180(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$FW83YuWf1Az-s4r51_2pM0G4hIQ, reason: not valid java name */
    public static /* synthetic */ void m15594$r8$lambda$FW83YuWf1Azs4r51_2pM0G4hIQ(LaunchActivity r0, TLObject r1, int r2, String r3, Runnable r4) {
        r0.lambda$runLinkRequest$51(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$FxTKo59jDHQxxK0wDF4CT81PsLA(LaunchActivity r0, int r1, String r2, String r3, String r4, String r5, String r6, String r7, String r8, String r9, String r10, String r11, String r12, String r13, boolean r14, Integer r15, Long r16, Long r17, Integer r18, String r19, HashMap r20, String r21, String r22, String r23, String r24, TLRPC.TL_wallPaper r25, String r26, String r27, String r28, String r29, boolean r30, String r31, int r32, int r33, String r34, String r35, String r36, Browser.Progress r37, boolean r38, int r39, boolean r40, int r41, int r42, boolean r43, String r44, boolean r45, boolean r46, boolean r47, boolean r48, boolean r49, String r50, boolean r51) {
        r0.lambda$processWebAppBot$118(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51);
    }

    public static /* synthetic */ void $r8$lambda$GCOuO0QHI_K4iTWi5x4E9i5QZnA(LaunchActivity r0, int r1) {
        r0.lambda$openMessage$43(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$GG9lmjRIT5yRH4MkNvlHRGQ2Mjw(LaunchActivity r0, int r1, TLRPC.User r2, String r3, String r4, DialogsActivity r5, DialogsActivity r6, ArrayList r7, CharSequence r8, boolean r9, boolean r10, int r11, int r12, TopicsFragment r13) {
        return r0.lambda$runLinkRequest$79(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
    }

    /* renamed from: $r8$lambda$GiJiD7UitvqHhw-QA-hBf2XfR74, reason: not valid java name */
    public static /* synthetic */ void m15595$r8$lambda$GiJiD7UitvqHhwQAhBf2XfR74(LaunchActivity r0, Browser.Progress r1, Runnable r2, ChannelBoostsController r3, Long r4, ChatMessageCell r5, TL_stories.TL_premium_boostsStatus r6) {
        r0.lambda$processBoostDialog$127(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$GkyzFKS8VqzE_ZZaDSkyy6QWqQM(LaunchActivity r0, TLObject r1, int r2, long r3, boolean r5) {
        r0.lambda$openStory$184(r1, r2, r3, r5);
    }

    public static /* synthetic */ void $r8$lambda$H50UM3M_hzqckC0z7tYs2TmHafI(int r0, HashMap r1, boolean r2, boolean r3, AlertDialog r4, int r5) {
        ContactsController.getInstance(r0).syncPhoneBookByAlert(r1, r2, r3, true);
    }

    public static /* synthetic */ void $r8$lambda$H6HpKmknrULFeuApv5pZHMNwouw(TLObject r2, MessagesController r3, long r4, Runnable r6) {
        if ((r2 instanceof TL_stories.TL_stories_peerStories) == false) goto L6;
        TL_stories.TL_stories_peerStories r22 = (TL_stories.TL_stories_peerStories) r2;
        r3.putUsers(r22.users, false);
        r3.getStoriesController().putStories(r4, r22.stories);
        r6.run();
        return;
    L6:
        r6.run();
    }

    /* renamed from: $r8$lambda$HI2MdIjrWgp8u-l8AaBBq8MhY8M, reason: not valid java name */
    public static /* synthetic */ void m15596$r8$lambda$HI2MdIjrWgp8ul8AaBBq8MhY8M(Runnable r1, PaymentFormActivity.InvoiceStatus r2) {
        if (r2 != PaymentFormActivity.InvoiceStatus.PAID) goto L6;
        r1.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$I89DQ0opAGdYNEiyUnJZiioOhMY(TLObject r2, int r3) {
        if ((r2 instanceof TLRPC.TL_boolTrue) == false) goto L6;
        MediaDataController.getInstance(r3).loadAttachMenuBots(false, true, null);
        return;
    }

    public static /* synthetic */ void $r8$lambda$IXkMoRPuXcMnjPaWCUck8wkyCWY(LaunchActivity r0, int[] r1, int r2, Runnable r3, TL_account.getAuthorizationForm r4, String r5, String r6, String r7, TLObject r8, TLRPC.TL_error r9) {
        r0.lambda$runLinkRequest$97(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$IfL3ATYIurryek_sK4ssK77o7gg(LaunchActivity r0, LoginActivity r1, AlertDialog r2, int r3) {
        r0.lambda$openEmailSettings$30(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$IqKqV63xi2ldSfOECxKe9Hz4O6Y(MessagesController r2, long r3, Runnable r5, TLObject r6, TLRPC.TL_error r7) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda113(r6, r2, r3, r5));
    }

    public static /* synthetic */ void $r8$lambda$IrJY4p9XhkaBDzX1XaQnLHUl39E(LaunchActivity r0, TLRPC.TL_help_appUpdate r1, boolean r2, int r3, Browser.Progress r4) {
        r0.lambda$checkAppUpdate$137(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$JNvtOkdfXaE0xNk9AI0pNahyj3M(AtomicBoolean r0, int r1, int[] r2, Runnable r3, DialogInterface r4) {
        r0.set(true);
        ConnectionsManager.getInstance(r1).cancelRequest(r2[0], true);
        if (r3 == null) goto L6;
        r3.run();
        return;
    }

    /* renamed from: $r8$lambda$Jh3EYhrSvD_-Ny4hPoYER0bmRck, reason: not valid java name */
    public static /* synthetic */ void m15597$r8$lambda$Jh3EYhrSvD_Ny4hPoYER0bmRck(int r0, TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda180(r1, r0));
    }

    public static /* synthetic */ void $r8$lambda$JiTEmkCV0JxyEMtJfHdkXl7VJm4(LaunchActivity r0) {
        r0.lambda$openEmailSettings$29();
    }

    /* renamed from: $r8$lambda$JkdEwd0NPPP_NmZ-UCf22pdy70o, reason: not valid java name */
    public static /* synthetic */ void m15598$r8$lambda$JkdEwd0NPPP_NmZUCf22pdy70o(LaunchActivity r0, AlertDialog r1, Runnable r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$runLinkRequest$107(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$JsBVDEY4GCUXgOlut_cl9RtE3d8(LaunchActivity r0, String r1) {
        r0.lambda$handleIntent$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$K0hB6BC0u6uMhRLlV839amIhRsQ(LaunchActivity r0, Runnable r1, boolean r2, Long r3, Browser.Progress r4, Long r5, Integer r6, Integer r7, byte[] r8, BaseFragment r9, int r10, Bundle r11, TLObject r12, TLRPC.TL_error r13) {
        r0.lambda$runLinkRequest$112(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
    }

    /* renamed from: $r8$lambda$L7mLkGTuzQX1qll4bhXAn-qdVBc, reason: not valid java name */
    public static /* synthetic */ void m15599$r8$lambda$L7mLkGTuzQX1qll4bhXAnqdVBc(LaunchActivity r0, long r1, int r3, TLRPC.User r4, String r5, AlertDialog r6, int r7) {
        r0.lambda$runLinkRequest$78(r1, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$MXEmYwDOB8MOIonRt_a5ynGcvE8(LaunchActivity r0, AtomicBoolean r1, int r2, boolean r3, TLRPC.Chat r4, Long r5, Integer r6, Integer r7, Runnable r8, String r9, Integer r10, byte[] r11, int r12, int r13, TLRPC.TL_messages_getDiscussionMessage r14, WidePosts.CommentsPostAuthorLoader r15, Runnable r16, TLObject r17, TLRPC.TL_error r18) {
        r0.lambda$runCommentRequest$33(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18);
    }

    /* renamed from: $r8$lambda$Mfh7fxKn2cM2Jb-2boHEaWrRr44, reason: not valid java name */
    public static /* synthetic */ void m15600$r8$lambda$Mfh7fxKn2cM2Jb2boHEaWrRr44(LaunchActivity r0) {
        r0.lambda$handleIntent$19();
    }

    public static /* synthetic */ void $r8$lambda$MyoQ54j9tWMJwjDP55aaafKXB14(LaunchActivity r0, int r1, TLRPC.Chat r2, DialogsActivity r3) {
        r0.lambda$runLinkRequest$75(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$N4tc0UkOC5kUQ-Gl1147dXsz3SE, reason: not valid java name */
    public static /* synthetic */ void m15601$r8$lambda$N4tc0UkOC5kUQGl1147dXsz3SE(LaunchActivity r0, Intent r1, boolean r2) {
        r0.lambda$handleIntent$10(r1, r2);
    }

    /* renamed from: $r8$lambda$N6BF4KLIEUYEPdZ-jM8avoUrZzI, reason: not valid java name */
    public static /* synthetic */ void m15602$r8$lambda$N6BF4KLIEUYEPdZjM8avoUrZzI(LaunchActivity r0, ValueAnimator r1) {
        r0.lambda$didReceivedNotification$160(r1);
    }

    public static /* synthetic */ void $r8$lambda$NhnlfXrF9k2IEovD1M5PflVLEfo(LaunchActivity r0, Browser.Progress r1, int[] r2, Long r3) {
        r0.lambda$handleIntent$11(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$O8ey94q-xE1HGXQ8k8uOWe8K4Bc, reason: not valid java name */
    public static /* synthetic */ void m15603$r8$lambda$O8ey94qxE1HGXQ8k8uOWe8K4Bc(int r2) {
        ApplicationLoader.mainInterfacePausedStageQueue = true;
        ApplicationLoader.mainInterfacePausedStageQueueTime = 0;
        if (VoIPService.getSharedInstance() != null) goto L6;
        MessagesController.getInstance(r2).ignoreSetOnline = false;
        return;
    }

    public static /* synthetic */ void $r8$lambda$ORk5Bh9LkBUaP99lmsH9x0RMRXs(int r0, HashMap r1, boolean r2, boolean r3, AlertDialog r4, int r5) {
        ContactsController.getInstance(r0).syncPhoneBookByAlert(r1, r2, r3, false);
    }

    public static /* synthetic */ void $r8$lambda$OYRqKAoUvdc_eYvu4WXsMHVeVLM(LaunchActivity r0, TLRPC.TL_chatInviteJoinResultWebView r1) {
        r0.lambda$runLinkRequest$90(r1);
    }

    /* renamed from: $r8$lambda$OZo1p-hn5OqLHW0vIJe6nhR3vcA, reason: not valid java name */
    public static /* synthetic */ void m15604$r8$lambda$OZo1phn5OqLHW0vIJe6nhR3vcA(LaunchActivity r0, LocaleController.LocaleInfo[] r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$showLanguageAlert$181(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$Or4XAZFzmdBhR5REeDY2X_ir4fY(LaunchActivity r0, Runnable r1, TLRPC.TL_wallPaper r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$runLinkRequest$104(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$P-ANo4NIH5MKSTQFJA040Rb2N_o, reason: not valid java name */
    public static /* synthetic */ void m15605$r8$lambda$PANo4NIH5MKSTQFJA040Rb2N_o(LaunchActivity r0, int r1, String r2, String r3, TLRPC.User r4, String r5, long r6, TLObject r8, TLRPC.TL_error r9) {
        r0.lambda$processAttachMenuBot$134(r1, r2, r3, r4, r5, r6, r8, r9);
    }

    /* renamed from: $r8$lambda$P49iG-5LR0J4d_I-lwUMZBQfK4I, reason: not valid java name */
    public static /* synthetic */ void m15606$r8$lambda$P49iG5LR0J4d_IlwUMZBQfK4I(LaunchActivity r0, TLRPC.TL_error r1, TLObject r2, int r3, TLRPC.Chat r4, int r5, int r6, Runnable r7, String r8, Integer r9, byte[] r10, int r11, ArrayList r12, int r13) {
        r0.lambda$openTopicRequest$34(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
    }

    public static /* synthetic */ void $r8$lambda$PiZKniFI3zTPcVcSv8ZQd6gU_Ig(LaunchActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$runLinkRequest$115(r1, r2);
    }

    /* renamed from: $r8$lambda$Q3aTtC4dX-dYPeHJkLJ9MBdxVgA, reason: not valid java name */
    public static /* synthetic */ void m15607$r8$lambda$Q3aTtC4dXdYPeHJkLJ9MBdxVgA(LaunchActivity r0, Uri r1, int r2, AlertDialog r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$runImportRequest$37(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$QS4Z3_TSuYyFaugxCVgFt1Z2R-Y, reason: not valid java name */
    public static /* synthetic */ void m15608$r8$lambda$QS4Z3_TSuYyFaugxCVgFt1Z2RY(LaunchActivity r0, Browser.Progress r1, int r2, String r3, String r4, String r5, String r6, String r7, String r8, String r9, String r10, String r11, String r12, String r13, String r14, boolean r15, Integer r16, Long r17, Long r18, Integer r19, String r20, HashMap r21, String r22, String r23, String r24, String r25, TLRPC.TL_wallPaper r26, String r27, String r28, String r29, String r30, boolean r31, String r32, int r33, int r34, String r35, String r36, String r37, boolean r38, int r39, boolean r40, int r41, int r42, boolean r43, String r44, boolean r45, boolean r46, boolean r47, boolean r48, boolean r49, String r50, boolean r51, Runnable r52, TLRPC.User r53, String r54, boolean r55, boolean r56, TLObject r57, TLRPC.TL_error r58) {
        r0.lambda$processWebAppBot$122(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58);
    }

    public static /* synthetic */ void $r8$lambda$Qqa_1DC7uT7x8bPNwmjCh21txuU(LaunchActivity r0, boolean r1, int[] r2, TLRPC.User r3, String r4, ContactsActivity r5) {
        r0.lambda$handleIntent$23(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$QvoDLEod3ysPuX9sPSidI_jagGo(Runnable r0, Boolean r1) {
        r0.run();
    }

    /* renamed from: $r8$lambda$RHU-cunaCZBXiBZAfwEMq1NLKjo, reason: not valid java name */
    public static /* synthetic */ void m15609$r8$lambda$RHUcunaCZBXiBZAfwEMq1NLKjo(LaunchActivity r0, DialogsActivity r1, BaseFragment r2, TLRPC.User r3, String r4) {
        r0.lambda$processAttachMenuBot$129(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$RJKr_vM4MuoW_6PzFkkEZ7q96_E(LaunchActivity r0, long r1, long r3, ChatActivity r5) {
        r0.lambda$handleIntent$16(r1, r3, r5);
    }

    public static /* synthetic */ void $r8$lambda$RTv9Y0w5sD62TZDlhEsglYCZGhA(LaunchActivity r0, boolean r1) {
        r0.onPowerSaver(r1);
    }

    /* renamed from: $r8$lambda$RlM6PqDEX1WIRZ-uURFwJQ6o_Gw, reason: not valid java name */
    public static /* synthetic */ void m15610$r8$lambda$RlM6PqDEX1WIRZuURFwJQ6o_Gw(Browser.Progress r0, AlertDialog r1) {
        if (r0 == null) goto L4;
        r0.end();
    L4:
        if (r1 == null) goto L7;
        r1.dismiss();
        return;
    }

    /* renamed from: $r8$lambda$RzWAc1eGkDYf-8jkk6S2rfy2Zdo, reason: not valid java name */
    public static /* synthetic */ void m15611$r8$lambda$RzWAc1eGkDYf8jkk6S2rfy2Zdo(LaunchActivity r0, Bulletin r1) {
        r0.lambda$didReceivedNotification$171(r1);
    }

    public static /* synthetic */ void $r8$lambda$SHoRONtXxYicOSAjcrvG0qtbNdw(LaunchActivity r0, Runnable r1, TLRPC.TL_error r2, TLRPC.Updates r3, int r4) {
        r0.lambda$runLinkRequest$91(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$SIgMC1aOBLNYudpddKhr4E_f1gY(LaunchActivity r0, Runnable r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$runLinkRequest$101(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$SQl19lZn8j_x-FXtmuoYtbHiyKs, reason: not valid java name */
    public static /* synthetic */ void m15612$r8$lambda$SQl19lZn8j_xFXtmuoYtbHiyKs(LaunchActivity r0, AlertDialog r1, TLRPC.TL_error r2, String r3, Bundle r4, TLObject r5, TL_account.sendConfirmPhoneCode r6) {
        r0.lambda$handleIntent$12(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$TSTeQBCTwZf27eYK5jAtMnol5Gg(LaunchActivity r0, Runnable r1, boolean r2, int r3, int r4, String r5, String r6, boolean r7, String r8, String r9, int r10, String r11, String r12, String r13, String r14, String r15, String r16, String r17, String r18, String r19, String r20, String r21, String r22, boolean r23, Integer r24, Long r25, Long r26, Integer r27, HashMap r28, String r29, String r30, String r31, String r32, TLRPC.TL_wallPaper r33, String r34, String r35, String r36, int r37, int r38, String r39, String r40, String r41, Browser.Progress r42, boolean r43, int r44, String r45, String r46, boolean r47, String r48, boolean r49, boolean r50, boolean r51, boolean r52, boolean r53, String r54, Integer r55, boolean r56, byte[] r57, String r58, int[] r59, AtomicBoolean r60, Long r61) {
        r0.lambda$runLinkRequest$83(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61);
    }

    /* renamed from: $r8$lambda$TfQqNgujpUHdExSFgA-dLP5OT9g, reason: not valid java name */
    public static /* synthetic */ void m15613$r8$lambda$TfQqNgujpUHdExSFgAdLP5OT9g(LaunchActivity r0, Runnable r1, Long r2, TL_stories.StoryItem r3) {
        r0.lambda$runLinkRequest$63(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$UB3QRxTV1JlkRWFJNnKNVVxJZes(LaunchActivity r0, int[] r1, int r2, Runnable r3, Integer r4, Integer r5, Long r6, Integer r7, AtomicBoolean r8, TLObject r9, TLRPC.TL_error r10) {
        r0.lambda$runLinkRequest$109(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    public static /* synthetic */ void $r8$lambda$UEG0c11n5e6iSiI_ljqGyB01yc0(Browser.Progress r0, TLRPC.TL_error r1) {
        if (r0 == null) goto L5;
        r0.end();
    L5:
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L10;
        BaseFragment r02 = getLastFragment();
        if (r02 == null) goto L11;
        BulletinFactory.of(r02).showForError(r1);
        return;
    L11:
        return;
    }

    public static /* synthetic */ void $r8$lambda$UTTZARtWRcmjkg01HiafGM8BxvQ(LaunchActivity r0, int r1, long r2, boolean r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$openStory$185(r1, r2, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$UlZ5jEk46AMeNAo-SmpXstSQwm4, reason: not valid java name */
    public static /* synthetic */ void m15614$r8$lambda$UlZ5jEk46AMeNAoSmpXstSQwm4(LaunchActivity r0, Bundle r1, byte[] r2, int r3, Integer r4, String r5, int r6, long r7, Browser.Progress r9, BaseFragment r10) {
        r0.lambda$openMessage$44(r1, r2, r3, r4, r5, r6, r7, r9, r10);
    }

    public static /* synthetic */ void $r8$lambda$VDNy_QBazi0rkZSX6lCWUQxiYFg(LaunchActivity r0, int r1, String r2, Runnable r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$runLinkRequest$50(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$VN38J8t3Zx-A-12ptMt9mHMAjiA, reason: not valid java name */
    public static /* synthetic */ void m15615$r8$lambda$VN38J8t3ZxA12ptMt9mHMAjiA(ActionIntroActivity r3, TLRPC.TL_error r4) {
        AlertsCreator.showSimpleAlert(r3, LocaleController.getString(R.string.AuthAnotherClient), LocaleController.getString(R.string.ErrorOccurred) + "\n" + r4.text);
    }

    /* renamed from: $r8$lambda$WTxn9QMZ-ECsRnqASI_mTgFbxWU, reason: not valid java name */
    public static /* synthetic */ void m15616$r8$lambda$WTxn9QMZECsRnqASI_mTgFbxWU(LaunchActivity r0, BaseFragment r1, boolean r2) {
        r0.lambda$handleIntent$22(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$WbNxdCC6icSRFgvMWFEklB2uVH0(LaunchActivity r0, Runnable r1, TLObject r2, boolean r3, Long r4, Browser.Progress r5, Long r6, Integer r7, Integer r8, byte[] r9, BaseFragment r10, int r11, Bundle r12) {
        r0.lambda$runLinkRequest$111(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
    }

    public static /* synthetic */ void $r8$lambda$XbL_9b7_a7VszpVYUx5nUkWWKYQ(LaunchActivity r0, LocaleController.LocaleInfo[] r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$showLanguageAlert$179(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$YdRYI14iFG-Zw3LVs8w3Z1jWlGY, reason: not valid java name */
    public static /* synthetic */ void m15617$r8$lambda$YdRYI14iFGZw3LVs8w3Z1jWlGY(LaunchActivity r0, TLObject r1, int r2, String r3, TLRPC.TL_error r4, Runnable r5) {
        r0.lambda$runLinkRequest$49(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$YwnVaImGjAUfR9re8VZEgJyouYM(AlertDialog r1, ActionIntroActivity r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda165(r1, r3, r2, r4));
    }

    public static /* synthetic */ void $r8$lambda$ZGXgnokbVijhDApCH3pIUjTrPf0(int r0, HashMap r1, boolean r2, boolean r3, AlertDialog r4, int r5) {
        ContactsController.getInstance(r0).syncPhoneBookByAlert(r1, r2, r3, true);
    }

    public static /* synthetic */ void $r8$lambda$_7RPm5bb29lBxu5k0L5QDcvUX70(LaunchActivity r0, Runnable r1, TLObject r2, TLRPC.TL_wallPaper r3, TLRPC.TL_error r4) {
        r0.lambda$runLinkRequest$103(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$_Co-K5frPQdm6MXs4fJk5DpVheM, reason: not valid java name */
    public static /* synthetic */ void m15618$r8$lambda$_CoK5frPQdm6MXs4fJk5DpVheM(LaunchActivity r0, Runnable r1, int r2, TLRPC.ChatInviteJoinResult r3, TLRPC.TL_error r4) {
        r0.lambda$runLinkRequest$92(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$_ivF-6h1JDX_gWPp_rmpcT9Fslg, reason: not valid java name */
    public static /* synthetic */ void m15619$r8$lambda$_ivF6h1JDX_gWPp_rmpcT9Fslg(LaunchActivity r0, TLRPC.TL_error r1, TLObject r2, TLRPC.TL_inputInvoiceSlug r3, Runnable r4, int r5, String r6) {
        r0.lambda$runLinkRequest$60(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$_tMGBX22U6AV-TqRBsB77oU87ug, reason: not valid java name */
    public static /* synthetic */ void m15620$r8$lambda$_tMGBX22U6AVTqRBsB77oU87ug(LaunchActivity r0, int r1, long r2, DialogsActivity r4, BaseFragment r5, TLRPC.User r6, String r7, Boolean r8) {
        r0.lambda$processAttachMenuBot$132(r1, r2, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$aCNs7Rq1QlF5WntoYnyKetOuVPs(LaunchActivity r0, PasscodeView r1) {
        r0.lambda$showPasscodeActivity$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$aXqLDRmkaHOL0KJtaHZnbdq_pWY(LaunchActivity r0) {
        r0.lambda$checkFreeDiscSpace$173();
    }

    public static /* synthetic */ void $r8$lambda$a_23XaUKR_ow7phOwoQoz85UZBo(LaunchActivity r0, Runnable r1, int r2, TL_account.authorizationForm r3, TL_account.getAuthorizationForm r4, String r5, String r6, String r7, TLObject r8, TLRPC.TL_error r9) {
        r0.lambda$runLinkRequest$95(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$b1K3AbUh4YVXr4EDFpeOt5yBar4() {
        PillStackConfig.notifySettingsChanged(new int[]{PillType.GRAM.getId(), PillType.BTC.getId(), PillType.USD.getId()});
    }

    /* renamed from: $r8$lambda$b3j-necjRVnUt4mRVVt4M7mNaZc, reason: not valid java name */
    public static /* synthetic */ void m15621$r8$lambda$b3jnecjRVnUt4mRVVt4M7mNaZc(Browser.Progress r0) {
        if (r0 == null) goto L5;
        r0.end();
        return;
    }

    public static /* synthetic */ void $r8$lambda$bI8eT12rd1ohSsgWpj4Bq0XU_NM(LaunchActivity r0, Browser.Progress r1, long r2, int r4, Integer r5, byte[] r6, BaseFragment r7, Bundle r8, ChatActivity r9, String r10, int r11, TLObject r12, TLRPC.TL_error r13) {
        r0.lambda$openMessage$42(r1, r2, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
    }

    /* renamed from: $r8$lambda$bIDyl7tdS8jkk-7wBLxJ9nb5UaE, reason: not valid java name */
    public static /* synthetic */ void m15622$r8$lambda$bIDyl7tdS8jkk7wBLxJ9nb5UaE(int[] r10, long r11, TLRPC.MessageMedia r13, int r14, boolean r15, int r16, long r17) {
        SendMessagesHelper.getInstance(r10[0]).sendMessage(SendMessagesHelper.SendMessageParams.of(r13, r11, null, null, null, null, r15, r16, 0));
    }

    public static /* synthetic */ void $r8$lambda$bZgcR4i3cQW0GAWfPjPLrGo1UbA(LaunchActivity r0, ChatActivity r1, ArrayList r2, int r3, int r4, CharSequence r5, int r6, boolean r7, TLRPC.User r8, boolean r9, int r10, long r11, boolean r13, long r14) {
        r0.lambda$didSelectDialogs$147(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r13, r14);
    }

    public static /* synthetic */ void $r8$lambda$bakVL_JOe3lBvzMRLbTczAoQz0Q(LaunchActivity r0, TLRPC.TL_error r1, TLObject r2, int r3, String r4, Runnable r5) {
        r0.lambda$runLinkRequest$55(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$cBQ7k9MUyTCV3FSeyWgzdSDZM2g(LaunchActivity r0, int r1, String r2, String r3, String r4, String r5, String r6, String r7, String r8, String r9, String r10, String r11, String r12, String r13, boolean r14, Integer r15, Long r16, Long r17, Integer r18, String r19, HashMap r20, String r21, String r22, String r23, String r24, TLRPC.TL_wallPaper r25, String r26, String r27, String r28, String r29, boolean r30, String r31, int r32, String r33, String r34, String r35, String r36, String r37, Browser.Progress r38, boolean r39, int r40, boolean r41, int r42, int r43, String r44, String r45, boolean r46, String r47, boolean r48, boolean r49, boolean r50, boolean r51, boolean r52, String r53, Integer r54, boolean r55, byte[] r56, int r57) {
        r0.lambda$runLinkRequest$47(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57);
    }

    /* renamed from: $r8$lambda$cGql7dMMasMg6ltzgaG-5CECadw, reason: not valid java name */
    public static /* synthetic */ void m15623$r8$lambda$cGql7dMMasMg6ltzgaG5CECadw(LaunchActivity r0, Runnable r1, Long r2, int r3, TL_stories.TL_storyAlbum r4) {
        r0.lambda$runLinkRequest$64(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$dBuWlt2r5oFIt1cU6OVCiACe5SM(LaunchActivity r0) {
        r0.lambda$updateCurrentConnectionState$182();
    }

    /* renamed from: $r8$lambda$dGCM2XliGV-g6h2QoNvd1KffWkU, reason: not valid java name */
    public static /* synthetic */ void m15624$r8$lambda$dGCM2XliGVg6h2QoNvd1KffWkU(LaunchActivity r0, TLObject r1, TLRPC.TL_messages_requestUrlAuth r2, String r3, TLRPC.TL_error r4) {
        r0.lambda$handleIntent$14(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$e32FEcdgdk6zM3q9D4JdLynXiqI(LaunchActivity r0) {
        r0.lambda$onCreate$1();
    }

    public static /* synthetic */ void $r8$lambda$eedRt5QNF23767TJgDjDhpCXCi4(LaunchActivity r0, TLObject r1, int r2, String r3, String r4, TLRPC.User r5, String r6, long r7) {
        r0.lambda$processAttachMenuBot$133(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$ek3oeSH4zUEdSD7B3cv1Cmnlx9k(LaunchActivity r0, long r1, Utilities.Callback r3) {
        r0.lambda$processAttachedMenuBotFromShortcut$125(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$ezAjccuiN6gHwQUeyoeoa7kGVyE(LaunchActivity r0, TLObject r1, Theme.ThemeInfo r2) {
        r0.lambda$didReceivedNotification$162(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$f2RN9k46LBoLKFrLgLmatym3b7Q(LaunchActivity r0) {
        r0.lambda$didReceivedNotification$161();
    }

    public static /* synthetic */ void $r8$lambda$f9yT63oFJSsDCQfDDdkpNOtQywE(LaunchActivity r0) {
        r0.lambda$didReceivedNotification$164();
    }

    /* renamed from: $r8$lambda$frpSrGpQttvVulDCY-BOJj2jPiw, reason: not valid java name */
    public static /* synthetic */ void m15625$r8$lambda$frpSrGpQttvVulDCYBOJj2jPiw(LaunchActivity r0, TLRPC.TL_inputInvoiceSlug r1, Runnable r2, int r3, String r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$runLinkRequest$61(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$ftdKffGPDDdzmb0Ek9zG6-0sSW0, reason: not valid java name */
    public static /* synthetic */ void m15626$r8$lambda$ftdKffGPDDdzmb0Ek9zG60sSW0(LaunchActivity r0, Long r1, TLRPC.TL_attachMenuBot r2, AtomicBoolean r3, int r4, String r5, String r6, String r7, String r8, String r9, String r10, String r11, String r12, String r13, String r14, String r15, String r16, boolean r17, Integer r18, Long r19, Long r20, Integer r21, String r22, HashMap r23, String r24, String r25, String r26, String r27, TLRPC.TL_wallPaper r28, String r29, String r30, String r31, String r32, boolean r33, String r34, int r35, int r36, String r37, String r38, String r39, String r40, String r41, Browser.Progress r42, boolean r43, int r44, int r45, int r46, boolean r47, String r48, TLRPC.User r49, Runnable r50, boolean r51, boolean r52, boolean r53, boolean r54, boolean r55, String r56, boolean r57) {
        r0.lambda$runLinkRequest$71(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57);
    }

    /* renamed from: $r8$lambda$g-ZhB4ya15svBUI27dYzauOd73U, reason: not valid java name */
    public static /* synthetic */ void m15627$r8$lambda$gZhB4ya15svBUI27dYzauOd73U(LaunchActivity r0, int r1) {
        r0.lambda$checkFreeDiscSpace$174(r1);
    }

    public static /* synthetic */ void $r8$lambda$gF_dwvwXK9Y6pxdmjjasC7wr6Bs(LaunchActivity r0, Theme.ThemeInfo r1, File r2) {
        r0.lambda$didReceivedNotification$165(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$gL8dbFIxrHrqeKZj10EQwUHQsaE(LocaleController.LocaleInfo[] r4, LanguageCell[] r5, View r6) {
        Integer r0 = (Integer) r6.getTag();
        r4[0] = ((LanguageCell) r6).getCurrentLocale();
        int r42 = 0;
    L4:
        if (r42 >= r5.length) goto L10;
        LanguageCell r62 = r5[r42];
        if (r42 != r0.intValue()) goto L8;
        boolean r2 = true;
    L9:
        r62.setLanguageSelected(r2, true);
        r42 = r42 + 1;
        goto L4
    L8:
        r2 = false;
        goto L9
    }

    public static /* synthetic */ void $r8$lambda$gi2ghZ3dZ6qaZVYDIEe16qwizNE(LaunchActivity r0, TLObject r1, AlertDialog r2, Runnable r3, TLRPC.TL_error r4) {
        r0.lambda$runLinkRequest$106(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$gogV8DArGt8Qpd5NG4n5Bv_Mfwg(AtomicBoolean r1, int r2, int[] r3, Runnable r4) {
        r1.set(true);
        ConnectionsManager.getInstance(r2).cancelRequest(r3[0], true);
        if (r4 == null) goto L6;
        r4.run();
        return;
    }

    /* renamed from: $r8$lambda$grEXyQWLR1JTXmpSZ5-3e8GE9Xw, reason: not valid java name */
    public static /* synthetic */ void m15628$r8$lambda$grEXyQWLR1JTXmpSZ53e8GE9Xw(LaunchActivity r0, Browser.Progress r1) {
        r0.lambda$runLinkRequest$105(r1);
    }

    public static /* synthetic */ void $r8$lambda$i67Aaq769Sqarhntctd5MZfVz0w(LaunchActivity r0, Runnable r1) {
        r0.lambda$showPasscodeActivity$7(r1);
    }

    /* renamed from: $r8$lambda$iGQyJz_x5RqkLT-qyKxHDdrnuhI, reason: not valid java name */
    public static /* synthetic */ boolean m15629$r8$lambda$iGQyJz_x5RqkLTqyKxHDdrnuhI(LaunchActivity r0, TLRPC.User r1, String r2, int r3, DialogsActivity r4, ArrayList r5, CharSequence r6, boolean r7, boolean r8, int r9, int r10, TopicsFragment r11) {
        return r0.lambda$processAttachMenuBot$128(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
    }

    public static /* synthetic */ void $r8$lambda$j141n3LWNB_65fhKVKdZ5QDN0cc(LaunchActivity r0) {
        r0.lambda$didReceivedNotification$169();
    }

    public static /* synthetic */ void $r8$lambda$jGdZXYDSkfOgLzekuP2aXrcvSU0(LaunchActivity r0, int r1, DialogsActivity r2, boolean r3, ArrayList r4, Uri r5, AlertDialog r6, long r7) {
        r0.lambda$didSelectDialogs$146(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$jM_4X0vYAWVa4uoG7I3ILaZNVLU(LaunchActivity r0, TLObject r1, Integer r2, Integer r3, byte[] r4, long r5, Runnable r7, String r8, int r9, int r10) {
        r0.lambda$openForumFromLink$135(r1, r2, r3, r4, r5, r7, r8, r9, r10);
    }

    public static /* synthetic */ void $r8$lambda$jM_8o6GVE8PkJhfAd55d3R9jdKc(int r0, TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda178(r1, r0));
    }

    public static /* synthetic */ void $r8$lambda$jmddudCy79E7e8b9FheTkctPzyI(LaunchActivity r0, TLObject r1, int[] r2, int r3, Runnable r4, Integer r5, Integer r6, Long r7, Integer r8, AtomicBoolean r9) {
        r0.lambda$runLinkRequest$108(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    /* renamed from: $r8$lambda$ju-TkcuiFl6vQ820245peQTrOWc, reason: not valid java name */
    public static /* synthetic */ void m15630$r8$lambda$juTkcuiFl6vQ820245peQTrOWc(LaunchActivity r0, int r1, AlertDialog r2, Runnable r3, String r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$runLinkRequest$89(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$jwVmL5Cgr3EgEFKVvoFeDXoTL-4, reason: not valid java name */
    public static /* synthetic */ void m15631$r8$lambda$jwVmL5Cgr3EgEFKVvoFeDXoTL4(LaunchActivity r0, DialogInterface r1) {
        r0.lambda$didReceivedNotification$170(r1);
    }

    /* renamed from: $r8$lambda$jxhEL3UtSVUlp36T38y-7_1O2Hs, reason: not valid java name */
    public static /* synthetic */ void m15632$r8$lambda$jxhEL3UtSVUlp36T38y7_1O2Hs(LaunchActivity r0, ThemePreviewActivity r1) {
        r0.lambda$runLinkRequest$102(r1);
    }

    /* renamed from: $r8$lambda$k6zVEHNJj-Jp3L_-HHEwcZLN0Mc, reason: not valid java name */
    public static /* synthetic */ void m15633$r8$lambda$k6zVEHNJjJp3L_HHEwcZLN0Mc(LaunchActivity r0, TLObject r1) {
        r0.lambda$runLinkRequest$114(r1);
    }

    public static /* synthetic */ void $r8$lambda$kV0oVu3f8FwgJAhK0IOw3aQWXlQ(LaunchActivity r0, int r1, TLRPC.Chat r2, Runnable r3, String r4, String r5, String r6, String r7, String r8, String r9, String r10, String r11, String r12, String r13, String r14, String r15, boolean r16, Integer r17, Long r18, Long r19, Integer r20, String r21, HashMap r22, String r23, String r24, String r25, String r26, TLRPC.TL_wallPaper r27, String r28, String r29, String r30, String r31, boolean r32, String r33, int r34, int r35, String r36, String r37, String r38, String r39, String r40, Browser.Progress r41, boolean r42, int r43, boolean r44, int r45, int r46, String r47, String r48, boolean r49, String r50, boolean r51, boolean r52, boolean r53, boolean r54, boolean r55, String r56, Integer r57, boolean r58, byte[] r59, TLObject r60, TLRPC.TL_error r61) {
        r0.lambda$runLinkRequest$81(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61);
    }

    public static /* synthetic */ void $r8$lambda$kZAhKPFa3zUJWTHG3LJurMlBo7o(LaunchActivity r0, Bundle r1, Long r2, int[] r3, Runnable r4, boolean r5, Browser.Progress r6, Long r7, Integer r8, Integer r9, byte[] r10, BaseFragment r11, int r12) {
        r0.lambda$runLinkRequest$113(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
    }

    /* renamed from: $r8$lambda$lBsBDL8Y9FmnH-UMliKqyQoxM-I, reason: not valid java name */
    public static /* synthetic */ void m15634$r8$lambda$lBsBDL8Y9FmnHUMliKqyQoxMI(LaunchActivity r0, Theme.ThemeInfo r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$didReceivedNotification$163(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$lYiBtqBOcScyGT1MPYQTYP9MVFo(LaunchActivity r0, BaseFragment r1, int r2, TLRPC.User r3, TLRPC.TL_messages_botApp r4, AtomicBoolean r5, String r6, boolean r7, boolean r8, boolean r9, boolean r10) {
        r0.lambda$processWebAppBot$119(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    public static /* synthetic */ void $r8$lambda$lbdgq7YGCmAzQAGc5a6rhzT1onM(LaunchActivity r0, Integer r1, Integer r2, byte[] r3, long r4, Runnable r6, String r7, int r8, int r9, TLObject r10, TLRPC.TL_error r11) {
        r0.lambda$openForumFromLink$136(r1, r2, r3, r4, r6, r7, r8, r9, r10, r11);
    }

    public static /* synthetic */ void $r8$lambda$mehsv98QCTVvqpmJNxpaw1ROt5E(LaunchActivity r0, LocaleController.LocaleInfo[] r1, AlertDialog r2, int r3) {
        r0.lambda$showLanguageAlertInternal$177(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$nsI2TlPIa-WR0QFFQL6LRSfknWQ, reason: not valid java name */
    public static /* synthetic */ void m15635$r8$lambda$nsI2TlPIaWR0QFFQL6LRSfknWQ(LaunchActivity r0, TLRPC.TL_messages_requestUrlAuth r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$handleIntent$15(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$oLnyMnRpV_TpsJEE29-4-GA0trQ, reason: not valid java name */
    public static /* synthetic */ boolean m15636$r8$lambda$oLnyMnRpV_TpsJEE294GA0trQ(LaunchActivity r0, boolean r1, int r2, String r3, DialogsActivity r4, ArrayList r5, CharSequence r6, boolean r7, boolean r8, int r9, int r10, TopicsFragment r11) {
        return r0.lambda$runLinkRequest$93(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
    }

    public static /* synthetic */ void $r8$lambda$oXRmXZUvdlyEh8aj_9J1SdzQHR4(LaunchActivity r0, TLRPC.User r1, Utilities.Callback r2) {
        r0.lambda$processAttachedMenuBotFromShortcut$124(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ohbf0K5IuTj8DUbuWUDlZ4bayN0(LaunchActivity r0, int r1, String r2, String r3, String r4, String r5, String r6, String r7, String r8, String r9, String r10, String r11, String r12, String r13, boolean r14, Integer r15, Long r16, Long r17, Integer r18, String r19, HashMap r20, String r21, String r22, String r23, String r24, TLRPC.TL_wallPaper r25, String r26, String r27, String r28, String r29, boolean r30, String r31, int r32, int r33, String r34, String r35, String r36, Browser.Progress r37, boolean r38, int r39, boolean r40, int r41, int r42, String r43, String r44, boolean r45, String r46, boolean r47, boolean r48, boolean r49, boolean r50, boolean r51, String r52, Integer r53, boolean r54, byte[] r55) {
        r0.lambda$runLinkRequest$65(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55);
    }

    /* renamed from: $r8$lambda$okaSxvwcngEFEQ8vqjWX-jGnfOs, reason: not valid java name */
    public static /* synthetic */ void m15637$r8$lambda$okaSxvwcngEFEQ8vqjWXjGnfOs(LaunchActivity r0, Runnable r1, TL_payments.TL_StarGiftAuctionState r2, TLRPC.TL_error r3) {
        r0.lambda$runLinkRequest$53(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$onfUO3ySy0t_0zULZ_00oSW9Pw0(LaunchActivity r0, int[] r1, long[] r2) {
        r0.lambda$openStories$186(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$oqOIRYU_4KcsKz9Xpxo6AqkBd_8(LaunchActivity r0) {
        r0.lambda$onResume$152();
    }

    /* renamed from: $r8$lambda$p-MM2HRZa7FMQfKigIR1dvAXKl0, reason: not valid java name */
    public static /* synthetic */ boolean m15638$r8$lambda$pMM2HRZa7FMQfKigIR1dvAXKl0(LaunchActivity r0, View r1, MotionEvent r2) {
        return r0.lambda$setupActionBarLayout$3(r1, r2);
    }

    /* renamed from: $r8$lambda$poDp-H5Shvt6JNfvyx0gSpLrA4Y, reason: not valid java name */
    public static /* synthetic */ boolean m15639$r8$lambda$poDpH5Shvt6JNfvyx0gSpLrA4Y(LaunchActivity r0, String r1, int r2, TLRPC.User r3, DialogsActivity r4, ArrayList r5, CharSequence r6, boolean r7, boolean r8, int r9, int r10, TopicsFragment r11) {
        return r0.lambda$runLinkRequest$74(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
    }

    /* renamed from: $r8$lambda$qSXcZkMxPgjWDgCL2l-SXcB21CE, reason: not valid java name */
    public static /* synthetic */ void m15640$r8$lambda$qSXcZkMxPgjWDgCL2lSXcB21CE(Runnable r0) {
        r0.run();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$rpWE1SRNPdDVd0tIW56wcyQRV8o(LaunchActivity r0, Runnable r1, int r2, TLRPC.User r3, TLRPC.TL_messages_botApp r4, String r5, boolean r6, boolean r7, boolean r8, boolean r9, boolean r10, boolean r11, Browser.Progress r12) {
        r0.lambda$processWebAppBot$121(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
    }

    /* renamed from: $r8$lambda$rrVnnDJ9RqWijqPode-0FEwEM10, reason: not valid java name */
    public static /* synthetic */ void m15641$r8$lambda$rrVnnDJ9RqWijqPode0FEwEM10(Runnable r0) {
        r0.run();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$saJMDmqKQaf9RmsMJQ3Ld3kIab0(LaunchActivity r0, Long r1, long r2) {
        r0.lambda$runLinkRequest$86(r1, r2);
    }

    /* renamed from: $r8$lambda$swXf3d-Svh8HHFZzxTC8rZQJfnc, reason: not valid java name */
    public static /* synthetic */ void m15642$r8$lambda$swXf3dSvh8HHFZzxTC8rZQJfnc(AlertDialog r0, TLObject r1, ActionIntroActivity r2, TLRPC.TL_error r3) {
        r0.dismiss();     // Catch: Exception -> L7
    L4:
        if ((r1 instanceof TLRPC.TL_authorization) == true) goto L10;
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda179(r2, r3));
        return;
    }

    public static /* synthetic */ void $r8$lambda$t4h_cUUyHBcKwqTymmuRCLXTGTA(LaunchActivity r0, TLRPC.TL_error r1, int r2, String r3, String r4, String r5, String r6, String r7, String r8, String r9, String r10, String r11, String r12, String r13, String r14, boolean r15, Integer r16, Long r17, Long r18, Integer r19, String r20, HashMap r21, String r22, String r23, String r24, String r25, TLRPC.TL_wallPaper r26, String r27, String r28, String r29, String r30, boolean r31, String r32, int r33, int r34, String r35, String r36, String r37, Browser.Progress r38, boolean r39, int r40, boolean r41, int r42, int r43, String r44, String r45, boolean r46, String r47, boolean r48, boolean r49, boolean r50, boolean r51, boolean r52, String r53, Integer r54, boolean r55, byte[] r56, TLObject r57, Long r58, String r59, String r60, TLRPC.User r61, Runnable r62) {
        r0.lambda$runLinkRequest$72(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, r62);
    }

    /* renamed from: $r8$lambda$tBKDkTE0uNrtm19-dqmO--dYw68, reason: not valid java name */
    public static /* synthetic */ void m15643$r8$lambda$tBKDkTE0uNrtm19dqmOdYw68(int r0, int[] r1, Runnable r2, DialogInterface r3) {
        ConnectionsManager.getInstance(r0).cancelRequest(r1[0], true);
        if (r2 == null) goto L6;
        r2.run();
        return;
    }

    public static /* synthetic */ boolean $r8$lambda$tVxf7B2qg58ZTD63CkBfbFc7BaA(AtomicBoolean r0) {
        if (r0 != null) goto L4;
        return false;
    L4:
        if (r0.get() == false) goto L9;
        return true;
    L9:
        return false;
    }

    /* renamed from: $r8$lambda$tWar-MWUYomk-LYr2Ir07PmK0uo, reason: not valid java name */
    public static /* synthetic */ void m15644$r8$lambda$tWarMWUYomkLYr2Ir07PmK0uo(LaunchActivity r0, AlertDialog r1, String r2, Bundle r3, TL_account.sendConfirmPhoneCode r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$handleIntent$13(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$tYTeO00WvMfWeYU2m_iTnMjwsNo(LaunchActivity r0, TLObject r1, int r2, TLRPC.Chat r3, Runnable r4, String r5, String r6, String r7, String r8, String r9, String r10, String r11, String r12, String r13, String r14, String r15, String r16, boolean r17, Integer r18, Long r19, Long r20, Integer r21, String r22, HashMap r23, String r24, String r25, String r26, String r27, TLRPC.TL_wallPaper r28, String r29, String r30, String r31, String r32, boolean r33, String r34, int r35, int r36, String r37, String r38, String r39, String r40, String r41, Browser.Progress r42, boolean r43, int r44, boolean r45, int r46, int r47, String r48, String r49, boolean r50, String r51, boolean r52, boolean r53, boolean r54, boolean r55, boolean r56, String r57, Integer r58, boolean r59, byte[] r60) {
        r0.lambda$runLinkRequest$80(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60);
    }

    public static /* synthetic */ void $r8$lambda$tj91f7hSQSglYBO1IAI3J7U6wuA(LaunchActivity r0, long r1, String r3, Long r4) {
        r0.lambda$runLinkRequest$87(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$u0DyKeKrF5bwVINE2Wlhq5LqLbM(LaunchActivity r0, int r1, String r2, String r3, String r4, String r5, String r6, String r7, String r8, String r9, String r10, String r11, String r12, String r13, boolean r14, Integer r15, Long r16, Long r17, Integer r18, String r19, HashMap r20, String r21, String r22, String r23, String r24, TLRPC.TL_wallPaper r25, String r26, String r27, String r28, String r29, boolean r30, String r31, int r32, int r33, String r34, String r35, String r36, Browser.Progress r37, boolean r38, int r39, boolean r40, int r41, int r42, String r43, String r44, boolean r45, String r46, boolean r47, boolean r48, boolean r49, boolean r50, boolean r51, String r52, Integer r53, boolean r54, byte[] r55, Long r56, String r57, String r58, TLRPC.User r59, Runnable r60, TLObject r61, TLRPC.TL_error r62) {
        r0.lambda$runLinkRequest$73(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, r62);
    }

    /* renamed from: $r8$lambda$uF1yBkilTNq-aldKiXiHQ-Uohl0, reason: not valid java name */
    public static /* synthetic */ void m15645$r8$lambda$uF1yBkilTNqaldKiXiHQUohl0(LaunchActivity r0, String r1, TLRPC.TL_chatAdminRights r2, boolean r3, String r4, int r5, TLRPC.Chat r6, DialogsActivity r7, TLRPC.User r8, long r9, String r11) {
        r0.lambda$runLinkRequest$76(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11);
    }

    public static /* synthetic */ void $r8$lambda$ueeHaKuBooRedRlRYtTjhMc0l8Y(LaunchActivity r0, View r1) {
        r0.lambda$showLanguageAlertInternal$176(r1);
    }

    public static /* synthetic */ void $r8$lambda$vPlTfHzONQJfx_xrsa7m7bTOrwE(LaunchActivity r0, HashMap r1, LocaleController.LocaleInfo[] r2, String r3) {
        r0.lambda$showLanguageAlert$178(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$vgfyBYQPFNJt259o6DlmSjro5VA(LaunchActivity r0, TL_account.Password r1) {
        r0.lambda$handleIntent$20(r1);
    }

    /* renamed from: $r8$lambda$wMn9VfBBpkb2PS_nsaS8o1Z6i-Q, reason: not valid java name */
    public static /* synthetic */ void m15646$r8$lambda$wMn9VfBBpkb2PS_nsaS8o1Z6iQ(LaunchActivity r0, int r1, TLRPC.Chat r2, int r3, int r4, Runnable r5, String r6, Integer r7, byte[] r8, int r9, ArrayList r10, int r11, TLObject r12, TLRPC.TL_error r13) {
        r0.lambda$openTopicRequest$35(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
    }

    public static /* synthetic */ void $r8$lambda$wx_f4NOdXkzyU6k3Efcag7f77sg(LaunchActivity r0) {
        r0.lambda$onPowerSaver$148();
    }

    public static /* synthetic */ void $r8$lambda$yhwxFTlNxIBKDvax4b0H2IsC3Xk(HashMap r10, int r11, TLRPC.MessageMedia r12, int r13, boolean r14, int r15, long r16) {
        Iterator r102 = r10.entrySet().iterator();
    L4:
        if (r102.hasNext() == false) goto L6;
        MessageObject r3 = (MessageObject) ((Map.Entry) r102.next()).getValue();
        SendMessagesHelper.getInstance(r11).sendMessage(SendMessagesHelper.SendMessageParams.of(r12, r3.getDialogId(), r3, null, null, null, r14, r15, 0));
        goto L4
    }

    /* renamed from: $r8$lambda$zX_imISOm2o_YJkN8e0g-KQKybQ, reason: not valid java name */
    public static /* synthetic */ void m15647$r8$lambda$zX_imISOm2o_YJkN8e0gKQKybQ(LaunchActivity r0, Runnable r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$runLinkRequest$99(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$zfytYymF27Pi3oeVL54h612FKB8(LaunchActivity r0, Runnable r1, Long r2, TL_stories.StoryItem r3) {
        r0.lambda$runLinkRequest$62(r1, r2, r3);
    }

    public static /* synthetic */ boolean $r8$lambda$zgDmHDYwCMzcay6OxRkyPCmZOe8() {
        if (SharedConfig.passcodeHash.length() > 0) goto L5;
        return false;
    L5:
        if (SharedConfig.allowScreenCapture == true) goto L10;
        return true;
    L10:
        return false;
    }

    /* renamed from: -$$Nest$fgetbackgroundTablet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SizeNotifierFrameLayout m15648$$Nest$fgetbackgroundTablet(LaunchActivity r0) {
        return r0.backgroundTablet;
    }

    /* renamed from: -$$Nest$fgetblurListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Consumer m15649$$Nest$fgetblurListener(LaunchActivity r0) {
        return r0.blurListener;
    }

    /* renamed from: -$$Nest$fgetlayersActionBarLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarLayout m15650$$Nest$fgetlayersActionBarLayout(LaunchActivity r0) {
        return r0.layersActionBarLayout;
    }

    /* renamed from: -$$Nest$fgetlockRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m15651$$Nest$fgetlockRunnable(LaunchActivity r0) {
        return r0.lockRunnable;
    }

    /* renamed from: -$$Nest$fgetmainFragmentsStack, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m15652$$Nest$fgetmainFragmentsStack(LaunchActivity r0) {
        return r0.mainFragmentsStack;
    }

    /* renamed from: -$$Nest$fgetselectAnimatedEmojiDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow m15653$$Nest$fgetselectAnimatedEmojiDialog(LaunchActivity r0) {
        return r0.selectAnimatedEmojiDialog;
    }

    /* renamed from: -$$Nest$fgetshadowTablet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m15654$$Nest$fgetshadowTablet(LaunchActivity r0) {
        return r0.shadowTablet;
    }

    /* renamed from: -$$Nest$fgettermsOfServiceView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TermsOfServiceView m15655$$Nest$fgettermsOfServiceView(LaunchActivity r0) {
        return r0.termsOfServiceView;
    }

    /* renamed from: -$$Nest$fgetthemeSwitchImageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m15656$$Nest$fgetthemeSwitchImageView(LaunchActivity r0) {
        return r0.themeSwitchImageView;
    }

    /* renamed from: -$$Nest$fgetthemeSwitchSunView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m15657$$Nest$fgetthemeSwitchSunView(LaunchActivity r0) {
        return r0.themeSwitchSunView;
    }

    /* renamed from: -$$Nest$fputisInPictureInPictureMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15658$$Nest$fputisInPictureInPictureMode(LaunchActivity r0, boolean r1) {
        r0.isInPictureInPictureMode = r1;
    }

    /* renamed from: -$$Nest$fputlockRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15659$$Nest$fputlockRunnable(LaunchActivity r0, Runnable r1) {
        r0.lockRunnable = r1;
    }

    /* renamed from: -$$Nest$fputrippleAbove, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15660$$Nest$fputrippleAbove(LaunchActivity r0, View r1) {
        r0.rippleAbove = r1;
    }

    /* renamed from: -$$Nest$fputselectAnimatedEmojiDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15661$$Nest$fputselectAnimatedEmojiDialog(LaunchActivity r0, SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow r1) {
        r0.selectAnimatedEmojiDialog = r1;
    }

    /* renamed from: -$$Nest$fputtabletFullSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15662$$Nest$fputtabletFullSize(LaunchActivity r0, boolean r1) {
        r0.tabletFullSize = r1;
    }

    /* renamed from: -$$Nest$mcheckDecorViewVisibility, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15663$$Nest$mcheckDecorViewVisibility(LaunchActivity r0) {
        r0.checkDecorViewVisibility();
    }

    /* renamed from: -$$Nest$mcreateActivityVisibilityController, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActivityVisibilityController m15664$$Nest$mcreateActivityVisibilityController(LaunchActivity r0, boolean r1) {
        return r0.createActivityVisibilityController(r1);
    }

    /* renamed from: -$$Nest$mdrawRippleAbove, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15665$$Nest$mdrawRippleAbove(LaunchActivity r0, Canvas r1, View r2) {
        r0.drawRippleAbove(r1, r2);
    }

    /* renamed from: -$$Nest$mupdateReasonsToHideMainContent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15666$$Nest$mupdateReasonsToHideMainContent(LaunchActivity r0, boolean r1, boolean r2) {
        r0.updateReasonsToHideMainContent(r1, r2);
    }

    static {
        PREFIX_T_ME_PATTERN = Pattern.compile("^(?:http(?:s|)://|)([A-z0-9-]+?)\\.t\\.me");
    }

    public LaunchActivity() {
        this.res = null;
        this.resHistory = new CopyOnWriteArrayList();
        this.sheetFragmentsStack = new ArrayList();
        this.mainFragmentsStack = new ArrayList();
        this.layerFragmentsStack = new ArrayList();
        this.rightFragmentsStack = new ArrayList();
        PipActivityController r0 = new PipActivityController(this);
        this.pipActivityController = r0;
        this.pipActivityHandler = r0.getHandler();
        this.overlayPasscodeViews = new ArrayList();
        this.visibleDialogs = new ArrayList();
        this.isNavigationBarColorFrozen = false;
        this.onUserLeaveHintListeners = new ArrayList();
        this.requestedPermissions = new SparseIntArray();
        this.requsetPermissionsPointer = 5934;
        this.blurListener = new AnonymousClass1(this);
        this.batteryReceiver = new LiteMode.BatteryReceiver();
        this.instanceId = System.identityHashCode(this);
        this.reasonsToHideMainContent = 0;
        this.reasonsToHideDecorView = 0;
    }

    private boolean addSharedPhotoUri(Uri r5, boolean r6) {
        if (r5 != null) goto L6;
        return false;
    L6:
        if (this.photoPathsArray != null) goto L8;
        this.photoPathsArray = new ArrayList();
    L17:
        SendMessagesHelper.SendingMediaInfo r0 = new SendMessagesHelper.SendingMediaInfo();
        r0.uri = r5;
        r0.isVideo = r6;
        this.photoPathsArray.add(r0);
        return true;
    L8:
        String r1 = r5.normalizeScheme().toString();
        int r2 = 0;
    L10:
        if (r2 >= this.photoPathsArray.size()) goto L17;
        Uri r3 = this.photoPathsArray.get(r2).uri;
        if (r3 == null) goto L16;
        if (r1.equals(r3.normalizeScheme().toString()) == false) goto L16;
        return false;
    L16:
        r2 = r2 + 1;
        goto L10
    }

    private static ArrayList<SendMessagesHelper.SendingMediaInfo> buildSendingInfosFromEntries(ArrayList<MediaController.PhotoEntry> r7) {
        ArrayList<SendMessagesHelper.SendingMediaInfo> r0 = new ArrayList();
        if (r7 == null) goto L20;
        int r1 = r7.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L20;
        MediaController.PhotoEntry r3 = r7.get(r2);
        r2 = r2 + 1;
        MediaController.PhotoEntry r32 = r3;
        SendMessagesHelper.SendingMediaInfo r4 = new SendMessagesHelper.SendingMediaInfo();
        boolean r5 = r32.isVideo;
        if (r5 == true) goto L12;
        String r6 = r32.imagePath;
        if (r6 == null) goto L12;
        r4.path = r6;
    L15:
        r4.thumbPath = r32.thumbPath;
        r4.coverPath = r32.coverPath;
        r4.isVideo = r5;
        r4.isLivePhoto = r32.isLivePhoto();
        r4.discardLivePhoto = r32.isUnalivePhoto();
        r4.livePhotoVideoOffset = r32.livePhotoVideoOffset;
        r4.livePhotoTimestampUs = r32.livePhotoTimestampUs;
        CharSequence r52 = r32.caption;
        if (r52 == null) goto L18;
        String r53 = r52.toString();
    L19:
        r4.caption = r53;
        r4.entities = r32.entities;
        r4.masks = r32.stickers;
        r4.ttl = r32.ttl;
        r4.videoEditedInfo = r32.editedInfo;
        r4.canDeleteAfter = r32.canDeleteAfter;
        r4.hasMediaSpoilers = r32.hasSpoiler;
        r4.highQuality = r32.isHighQuality();
        r0.add(r4);
        goto L6
    L18:
        r53 = null;
    L12:
        String r62 = r32.path;
        if (r62 == null) goto L15;
        r4.path = r62;
    L20:
        return r0;
    }

    private void checkCurrentAccount() {
        if (this.currentAccount == UserConfig.selectedAccount) goto L5;
    L7:
        NotificationCenter.ObserversGroup r0 = this.observersGroup;
        if (r0 == null) goto L10;
        r0.removeAllObservers();
        this.observersGroup = null;
    L10:
        int r02 = UserConfig.selectedAccount;
        this.currentAccount = r02;
        this.observersGroup = NotificationCenter.getInstance(r02).createObserversGroup(this).add(NotificationCenter.openBoostForUsersDialog).add(NotificationCenter.appDidLogout).add(NotificationCenter.mainUserInfoChanged).add(NotificationCenter.attachMenuBotsDidLoad).add(NotificationCenter.didUpdateConnectionState).add(NotificationCenter.needShowAlert).add(NotificationCenter.wasUnableToFindCurrentLocation).add(NotificationCenter.openArticle).add(NotificationCenter.hasNewContactsToImport).add(NotificationCenter.needShowPlayServicesAlert).add(NotificationCenter.fileLoaded).add(NotificationCenter.fileLoadFailed).add(NotificationCenter.historyImportProgressChanged).add(NotificationCenter.groupCallUpdated).add(NotificationCenter.stickersImportComplete).add(NotificationCenter.currentUserShowLimitReachedDialog).add(NotificationCenter.currentUserPremiumStatusChanged).add(NotificationCenter.chatSwitchedForum).add(NotificationCenter.guardBotDecisionResult);
        return;
    L5:
        if (this.observersGroup == null) goto L7;
    }

    private void checkDecorViewVisibility() {
        getWindow();
    }

    private void checkFreeDiscSpace(int r3) {
        staticInstanceForAlerts = this;
        AutoDeleteMediaTask.run();
        SharedConfig.checkLogsToDelete();
        if (Build.VERSION.SDK_INT < 26) goto L6;
        if (r3 != 0) goto L6;
        return;
    L6:
        if (this.checkFreeDiscSpaceShown == true) goto L10;
        Utilities.globalQueue.postRunnable(new LaunchActivity$$ExternalSyntheticLambda3(this, r3), 2000);
        return;
    }

    public static void checkFreeDiscSpaceStatic(int r1) {
        LaunchActivity r0 = staticInstanceForAlerts;
        if (r0 == null) goto L6;
        r0.checkFreeDiscSpace(r1);
        return;
    }

    private void checkLayout() {
        if (AndroidUtilities.isTablet() == true) goto L5;
        return;
    L5:
        if (this.rightActionBarLayout != null) goto L7;
        return;
    L7:
        if (AndroidUtilities.getWasTablet() != null) goto L9;
    L11:
        int r2 = 8;
        if (AndroidUtilities.isTabletTwoPane() == false) goto L35;
        this.tabletFullSize = false;
        ArrayList r0 = new ArrayList(this.actionBarLayout.getFragmentStack());
        if (r0.size() >= 2) goto L16;
    L26:
        ViewGroup r02 = this.rightActionBarLayout.getView();
        if (this.rightActionBarLayout.getFragmentStack().isEmpty() == false) goto L29;
        int r1 = 8;
    L30:
        r02.setVisibility(r1);
        SizeNotifierFrameLayout r03 = this.backgroundTablet;
        if (this.rightActionBarLayout.getFragmentStack().isEmpty() == false) goto L33;
        r2 = 0;
    L33:
        r03.setVisibility(r2);
        return;
    L29:
        r1 = 0;
    L16:
        if (1 >= r0.size()) goto L21;
        BaseFragment r5 = (BaseFragment) r0.get(1);
        if ((r5 instanceof ChatActivity) == false) goto L20;
        ((ChatActivity) r5).setIgnoreAttachOnPause(true);
    L20:
        r5.onPause();
        r5.onFragmentDestroy();
        r5.setParentLayout(null);
        r0.remove(r5);
        this.rightActionBarLayout.addFragmentToStack(r5);
        goto L16
    L21:
        PasscodeViewDialog r04 = this.passcodeDialog;
        if (r04 != null) goto L24;
    L25:
        this.actionBarLayout.rebuildFragments(1);
        this.rightActionBarLayout.rebuildFragments(1);
        goto L26
    L24:
        if (r04.passcodeView.getVisibility() == 0) goto L26;
    L35:
        this.tabletFullSize = true;
        List<BaseFragment> r05 = this.rightActionBarLayout.getFragmentStack();
        if (r05.isEmpty() == false) goto L38;
    L48:
        this.rightActionBarLayout.getView().setVisibility(8);
        SizeNotifierFrameLayout r06 = this.backgroundTablet;
        if (this.actionBarLayout.getFragmentStack().isEmpty() == false) goto L52;
        r2 = 0;
    L52:
        r06.setVisibility(r2);
        return;
    L38:
        if (r05.size() <= 0) goto L43;
        BaseFragment r52 = r05.get(0);
        if ((r52 instanceof ChatActivity) == false) goto L42;
        ((ChatActivity) r52).setIgnoreAttachOnPause(true);
    L42:
        r52.onPause();
        r52.onFragmentDestroy();
        r52.setParentLayout(null);
        r05.remove(r52);
        this.actionBarLayout.addFragmentToStack(r52);
        goto L38
    L43:
        PasscodeViewDialog r07 = this.passcodeDialog;
        if (r07 != null) goto L46;
    L47:
        this.actionBarLayout.rebuildFragments(1);
        goto L48
    L46:
        if (r07.passcodeView.getVisibility() == 0) goto L48;
    L9:
        if (AndroidUtilities.getWasTablet().booleanValue() == AndroidUtilities.isTabletForce()) goto L11;
    }

    private void checkSystemBarColors() {
        checkSystemBarColors(false, true, !this.isNavigationBarColorFrozen);
    }

    private void checkSystemBarColors(boolean r3) {
        checkSystemBarColors(r3, true, !this.isNavigationBarColorFrozen);
    }

    private void checkSystemBarColors(boolean r2, boolean r3) {
        checkSystemBarColors(false, r2, r3);
    }

    private void checkWasMutedByAdmin(boolean r10) {
        VoIPService r0 = VoIPService.getSharedInstance();
        boolean r1 = false;
        if (r0 == null) goto L37;
        ChatObject.Call r2 = r0.groupCall;
        if (r2 == null) goto L37;
        boolean r3 = this.wasMutedByAdminRaisedHand;
        TLRPC.InputPeer r02 = r0.getGroupCallPeer();
        if (r02 == null) goto L15;
        long r6 = r02.user_id;
        if (r6 != 0) goto L16;
        long r62 = r02.chat_id;
        if (r62 == 0) goto L14;
    L13:
        r6 = -r62;
        goto L16
    L14:
        r62 = r02.channel_id;
    L16:
        TLRPC.GroupCallParticipant r03 = r2.participants.get(r6);
        if (r03 != null) goto L19;
    L23:
        boolean r63 = false;
    L24:
        if (r63 == true) goto L26;
    L28:
        this.wasMutedByAdminRaisedHand = r1;
        if (r10 == true) goto L39;
        if (r3 == false) goto L40;
        if (r1 == true) goto L41;
        if (r63 == false) goto L34;
        return;
    L34:
        if (GroupCallActivity.groupCallInstance != null) goto L43;
        showVoiceChatTooltip(38);
        return;
    L43:
        return;
    L41:
        return;
    L40:
        return;
    L39:
        return;
    L26:
        if (r03.raise_hand_rating == 0) goto L28;
        r1 = true;
        goto L28
    L19:
        if (r03.can_self_unmute == true) goto L23;
        if (r03.muted == false) goto L23;
        r63 = true;
        goto L24
    L15:
        r6 = UserConfig.getInstance(this.currentAccount).clientUserId;
    L37:
        this.wasMutedByAdminRaisedHand = false;
    }

    private ActivityVisibilityController createActivityVisibilityController(boolean r3) {
        return new ActivityVisibilityController(this, r3, null);
    }

    public static void dismissAllWeb() {
        BaseFragment r0 = getSafeLastFragment();
        if (r0 == null) goto L28;
        int r2 = 0;
        if ((r0.getParentLayout() instanceof ActionBarLayout) == false) goto L8;
        EmptyBaseFragment r1 = ((ActionBarLayout) r0.getParentLayout()).getSheetFragment(false);
    L10:
        if (r1 == null) goto L16;
        ArrayList<BaseFragment.AttachedSheet> r4 = r1.sheetsStack;
        if (r4 == null) goto L16;
        int r42 = r4.size() - 1;
    L14:
        if (r42 < 0) goto L16;
        r1.sheetsStack.get(r42).dismiss(true);
        r42 = r42 - 1;
    L16:
        ArrayList<BaseFragment.AttachedSheet> r12 = r0.sheetsStack;
        if (r12 == null) goto L21;
        int r13 = r12.size() - 1;
    L19:
        if (r13 < 0) goto L21;
        r0.sheetsStack.get(r13).dismiss(true);
        r13 = r13 - 1;
    L21:
        ArrayList r02 = new ArrayList();
        Iterator<BotWebViewSheet> r14 = BotWebViewSheet.activeSheets.iterator();
    L23:
        if (r14.hasNext() == false) goto L25;
        r02.add(r14.next());
        goto L23
    L25:
        int r15 = r02.size();
    L26:
        if (r2 >= r15) goto L33;
        Object r43 = r02.get(r2);
        r2 = r2 + 1;
        ((BotWebViewSheet) r43).dismiss(true);
        goto L26
    L33:
        return;
    L8:
        r1 = null;
        goto L10
    }

    private void drawRippleAbove(Canvas r6, View r7) {
        if (r7 == null) goto L13;
        View r0 = this.rippleAbove;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.getBackground() != null) goto L9;
        return;
    L9:
        if (this.tempLocation != null) goto L11;
        this.tempLocation = new int[2];
    L11:
        this.rippleAbove.getLocationInWindow(this.tempLocation);
        int[] r02 = this.tempLocation;
        int r2 = r02[0];
        int r4 = r02[1];
        r7.getLocationInWindow(r02);
        int[] r72 = this.tempLocation;
        int r22 = r2 - r72[0];
        int r42 = r4 - r72[1];
        r6.save();
        r6.translate(r22, r42);
        this.rippleAbove.getBackground().draw(r6);
        r6.restore();
        return;
    }

    private List<TLRPC.TL_contact> findContacts(String r18, String r19, boolean r20) {
        MessagesController r1 = MessagesController.getInstance(this.currentAccount);
        ContactsController r0 = ContactsController.getInstance(this.currentAccount);
        ArrayList r2 = new ArrayList(r0.contacts);
        ArrayList r3 = new ArrayList();
        String r4 = null;
        int r5 = 0;
        if (r19 == null) goto L15;
        String r6 = PhoneFormat.stripExceptNumbers(r19);
        TLRPC.TL_contact r7 = r0.contactsByPhone.get(r6);
        if (r7 != null) goto L7;
        r7 = r0.contactsByShortPhone.get(r6.substring(Math.max(0, r6.length() - 7)));
    L7:
        if (r7 == null) goto L15;
        TLRPC.User r02 = r1.getUser(Long.valueOf(r7.user_id));
        if (r02 != null) goto L11;
    L14:
        String r03 = null;
    L17:
        if (r3.isEmpty() == false) goto L69;
        if (r03 == null) goto L69;
        String r04 = r03.trim().toLowerCase();
        if (TextUtils.isEmpty(r04) == true) goto L69;
        String r62 = LocaleController.getInstance().getTranslitString(r04);
        if (r04.equals(r62) == false) goto L24;
    L25:
        r62 = null;
    L26:
        String[] r05 = {r04, r62};
        int r63 = r2.size();
        int r72 = 0;
    L27:
        if (r72 >= r63) goto L69;
        TLRPC.TL_contact r8 = (TLRPC.TL_contact) r2.get(r72);
        if (r8 == null) goto L68;
        TLRPC.User r9 = r1.getUser(Long.valueOf(r8.user_id));
        if (r9 == null) goto L68;
        if (r9.self == false) goto L36;
        if (r20 == false) goto L68;
    L36:
        int r10 = 3;
        String[] r11 = new String[3];
        r11[r5] = ContactsController.formatName(r9.first_name, r9.last_name).toLowerCase();
        String r12 = LocaleController.getInstance().getTranslitString(r11[r5]);
        r11[1] = r12;
        if (r11[r5].equals(r12) == false) goto L40;
        r11[1] = r4;
    L40:
        if (UserObject.isReplyUser(r9) == false) goto L43;
        r11[2] = LocaleController.getString(R.string.RepliesTitle).toLowerCase();
    L45:
        int r122 = r5;
        int r15 = r122;
    L46:
        if (r122 >= 2) goto L68;
        String r42 = r05[r122];
        if (r42 == null) goto L67;
    L50:
        if (r5 >= r10) goto L59;
        String r102 = r11[r5];
        if (r102 == null) goto L58;
        if (r102.startsWith(r42) == true) goto L57;
        if (r102.contains(" ".concat(r42)) == false) goto L58;
    L57:
        r15 = 1;
    L58:
        r5 = r5 + 1;
        r10 = 3;
    L59:
        String r52 = UserObject.getPublicUsername(r9);
        if (r15 != 0) goto L65;
        if (r52 == null) goto L65;
        if (r52.startsWith(r42) == false) goto L65;
        r15 = 1;
    L65:
        if (r15 == 0) goto L67;
        r3.add(r8);
    L67:
        r122 = r122 + 1;
        r5 = 0;
        r10 = 3;
        goto L46
    L43:
        if (r9.self == false) goto L45;
        r11[2] = LocaleController.getString(R.string.SavedMessages).toLowerCase();
    L68:
        r72 = r72 + 1;
        r4 = null;
        r5 = 0;
        goto L27
    L24:
        if (r62.length() != 0) goto L26;
    L69:
        return r3;
    L11:
        if (r02.self == false) goto L13;
        if (r20 == false) goto L14;
    L13:
        r3.add(r7);
    L15:
        r03 = r18;
        goto L17
    }

    public static <T extends BaseFragment> T findFragment(Class<T> r2) {
        BubbleActivity r0 = BubbleActivity.instance;
        if (r0 == null) goto L8;
        INavigationLayout r02 = r0.actionBarLayout;
        if (r02 == null) goto L8;
        return (T) r02.findFragment(r2);
    L8:
        LaunchActivity r03 = instance;
        if (r03 != null) goto L11;
    L14:
        LaunchActivity r04 = instance;
        if (r04 != null) goto L17;
        return null;
    L17:
        if (r04.getActionBarLayout() != null) goto L19;
        return null;
    L19:
        return (T) instance.getActionBarLayout().findFragment(r2);
    L11:
        if (r03.sheetFragmentsStack.isEmpty() == true) goto L14;
        return (T) instance.sheetFragmentsStack.get(r0.size() - 1).findFragment(r2);
    }

    private BaseFragment getClientNotActivatedFragment() {
        if (LoginActivity.loadCurrentState(false, this.currentAccount).getInt("currentViewNum", 0) == 0) goto L7;
        return new LoginActivity();
    L7:
        return new IntroActivity();
    }

    public static BaseFragment getLastFragment() {
        BubbleActivity r0 = BubbleActivity.instance;
        if (r0 == null) goto L8;
        INavigationLayout r02 = r0.actionBarLayout;
        if (r02 == null) goto L8;
        return r02.getLastFragment();
    L8:
        LaunchActivity r03 = instance;
        if (r03 != null) goto L11;
    L14:
        LaunchActivity r04 = instance;
        if (r04 != null) goto L17;
        return null;
    L17:
        if (r04.getActionBarLayout() != null) goto L19;
        return null;
    L19:
        return instance.getActionBarLayout().getLastFragment();
    L11:
        if (r03.sheetFragmentsStack.isEmpty() == true) goto L14;
        return instance.sheetFragmentsStack.get(r0.size() - 1).getLastFragment();
    }

    public static BaseFragment getLastFragmentIncludeMainTabs() {
        BaseFragment r0 = getLastFragment();
        if ((r0 instanceof MainTabsActivity) == true) goto L5;
        return r0;
    L5:
        return ((MainTabsActivity) r0).getCurrentVisibleFragment();
    }

    public static BaseFragment getSafeLastFragment() {
        BubbleActivity r0 = BubbleActivity.instance;
        if (r0 == null) goto L8;
        INavigationLayout r02 = r0.actionBarLayout;
        if (r02 == null) goto L8;
        return r02.getSafeLastFragment();
    L8:
        LaunchActivity r03 = instance;
        if (r03 != null) goto L11;
    L14:
        LaunchActivity r04 = instance;
        if (r04 != null) goto L17;
        return null;
    L17:
        if (r04.getActionBarLayout() != null) goto L19;
        return null;
    L19:
        return instance.getActionBarLayout().getSafeLastFragment();
    L11:
        if (r03.sheetFragmentsStack.isEmpty() == true) goto L14;
        return instance.sheetFragmentsStack.get(r0.size() - 1).getSafeLastFragment();
    }

    private String getStringForLanguageAlert(HashMap<String, String> r1, String r2, int r3) {
        String r0 = r1.get(r2);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return LocaleController.getString(r2, r3);
    }

    public static int getTimestampFromLink(Uri r11) {
        if (r11.getPathSegments().contains(MediaStreamTrack.VIDEO_TRACK_KIND) == false) goto L6;
        String r112 = r11.getQuery();
    L10:
        if (TextUtils.isEmpty(r112) == false) goto L12;
        return -1;
    L12:
        if (timestampPattern != null) goto L67;
        timestampPattern = Pattern.compile("^\\??(?:(\\d+)[dD])?(?:(\\d+)h)?(?:(\\d+)[mM])?(?:(\\d+)[sS])?$");
    L67:
        Matcher r5 = timestampPattern.matcher(r112);     // Catch: Throwable -> L62
        if (r5.matches() == false) goto L63;
        String r6 = r5.group(1);     // Catch: Throwable -> L62
        String r7 = r5.group(2);     // Catch: Throwable -> L62
        String r8 = r5.group(3);     // Catch: Throwable -> L62
        String r52 = r5.group(4);     // Catch: Throwable -> L62
        int r10 = 0;
        if (TextUtils.isEmpty(r6) == false) goto L20;
        int r62 = 0;
    L22:
        if (TextUtils.isEmpty(r7) == false) goto L24;
        int r72 = 0;
    L26:
        if (TextUtils.isEmpty(r8) == false) goto L28;
        int r82 = 0;
    L30:
        if (TextUtils.isEmpty(r52) == true) goto L34;
        r10 = Integer.parseInt(r52);     // Catch: Throwable -> L62
    L34:
        return ((r10 + (r82 * 60)) + (r72 * 3600)) + (r62 * 86400);
    L28:
        r82 = Integer.parseInt(r8);     // Catch: Throwable -> L62
        goto L30
    L24:
        r72 = Integer.parseInt(r7);     // Catch: Throwable -> L62
        goto L26
    L20:
        r62 = Integer.parseInt(r6);     // Catch: Throwable -> L62
    L63:
        return Integer.parseInt(r112);
    L38:
        if (r112.contains(":") == false) goto L70;
        String[] r113 = r112.split(":");
        int r53 = r113.length - 1;
        String r63 = MVEL.VERSION_SUB;
        if (r53 >= 0) goto L42;
        String r4 = MVEL.VERSION_SUB;
    L44:
        if ((r113.length - 2) >= 0) goto L46;
        String r3 = MVEL.VERSION_SUB;
    L48:
        if ((r113.length - 3) >= 0) goto L50;
        String r2 = MVEL.VERSION_SUB;
    L52:
        if ((r113.length - 4) < 0) goto L65;
        r63 = r113[r113.length - 4];
    L65:
        return ((Integer.parseInt(r4) + (Integer.parseInt(r3) * 60)) + (Integer.parseInt(r2) * 3600)) + (Integer.parseInt(r63) * 86400);
    L58:
        e = move-exception;
        FileLog.e(e);
        return -1;
    L50:
        r2 = r113[r113.length - 3];
        goto L52
    L46:
        r3 = r113[r113.length - 2];
        goto L48
    L42:
        r4 = r113[r113.length - 1];
        goto L44
    L70:
        return -1;
    L6:
        if (r11.getQueryParameter("t") == null) goto L8;
        r112 = r11.getQueryParameter("t");
        goto L10
    L8:
        r112 = null;
        goto L10
    }

    public static List<BaseFragment> getVisibleFragments() {
        ArrayList r0 = new ArrayList();
        BubbleActivity r1 = BubbleActivity.instance;
        if (r1 == null) goto L9;
        INavigationLayout r12 = r1.actionBarLayout;
        if (r12 == null) goto L9;
        BaseFragment r13 = r12.getSafeLastFragment();
        if (r13 == null) goto L9;
        r0.add(r13);
    L9:
        LaunchActivity r14 = instance;
        if (r14 != null) goto L12;
    L29:
        return r0;
    L12:
        if (r14.sheetFragmentsStack.isEmpty() == true) goto L21;
        int r15 = 0;
    L15:
        if (r15 >= instance.sheetFragmentsStack.size()) goto L21;
        BaseFragment r2 = instance.sheetFragmentsStack.get(r15).getSafeLastFragment();
        if (r2 == null) goto L19;
        r0.add(r2);
    L19:
        r15 = r15 + 1;
    L21:
        if (instance.layerFragmentsStack.isEmpty() == true) goto L24;
        r0.add(instance.layerFragmentsStack.get(r1.size() - 1));
    L24:
        if (instance.rightFragmentsStack.isEmpty() == true) goto L27;
        r0.add(instance.rightFragmentsStack.get(r1.size() - 1));
    L27:
        if (instance.mainFragmentsStack.isEmpty() == true) goto L29;
        r0.add(instance.mainFragmentsStack.get(r1.size() - 1));
        goto L29
    }

    private boolean handleIntent(Intent r9, boolean r10, boolean r11, boolean r12) {
        return handleIntent(r9, r10, r11, r12, null, true, false);
    }

    /*  JADX ERROR: Types fix failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:96)
        */
    @android.annotation.SuppressLint({"Range"})
    private boolean handleIntent(android.content.Intent r122, boolean r123, boolean r124, boolean r125, org.telegram.messenger.browser.Browser.Progress r126, boolean r127, boolean r128) {
        /*  JADX ERROR: Simple mode code generation failed
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x039c: CONST (r0v767 ?? I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (0 ??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) A[DECLARE_VAR] in method: org.telegram.ui.LaunchActivity.handleIntent(android.content.Intent, boolean, boolean, boolean, org.telegram.messenger.browser.Browser$Progress, boolean, boolean):boolean, file: classes3.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
            	at jadx.core.codegen.MethodGen.generateSimpleCode(MethodGen.java:355)
            	at jadx.core.codegen.MethodGen.addSimpleMethodCode(MethodGen.java:323)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:286)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
            Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.ArgType.getPrimitiveType()" because "type" is null
            	at jadx.core.codegen.ClassGen.useType(ClassGen.java:554)
            	at jadx.core.codegen.InsnGen.useType(InsnGen.java:269)
            	at jadx.core.codegen.InsnGen.declareVar(InsnGen.java:166)
            	at jadx.core.codegen.InsnGen.declareVar(InsnGen.java:159)
            	at jadx.core.codegen.InsnGen.assignVar(InsnGen.java:152)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:299)
            	... 11 more
            */
        /*
            Method dump skipped, instructions count: 16038
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.telegram.ui.LaunchActivity.handleIntent(android.content.Intent, boolean, boolean, boolean, org.telegram.messenger.browser.Browser$Progress, boolean, boolean):boolean");
    }

    private void invalidateCachedViews(View r3) {
        if (r3.getLayerType() == 0) goto L6;
        r3.invalidate();
    L6:
        if ((r3 instanceof ViewGroup) == false) goto L11;
        ViewGroup r32 = (ViewGroup) r3;
        int r0 = 0;
    L9:
        if (r0 >= r32.getChildCount()) goto L13;
        invalidateCachedViews(r32.getChildAt(r0));
        r0 = r0 + 1;
        goto L9
    L13:
        return;
    }

    private void invalidateTabletMode() {
        Boolean r0 = AndroidUtilities.getWasTablet();
        if (r0 == null) goto L70;
        AndroidUtilities.resetWasTabletFlag();
        if (r0.booleanValue() == AndroidUtilities.isTablet()) goto L45;
        int r1 = 0;
        long r2 = 0;
        if (r0.booleanValue() == false) goto L10;
        this.mainFragmentsStack.addAll(this.rightFragmentsStack);
        this.mainFragmentsStack.addAll(this.layerFragmentsStack);
        this.rightFragmentsStack.clear();
        this.layerFragmentsStack.clear();
        long r7 = 0;
    L33:
        setupActionBarLayout();
        this.actionBarLayout.rebuildFragments(1);
        if (AndroidUtilities.isTablet() == false) goto L69;
        this.rightActionBarLayout.rebuildFragments(1);
        this.layersActionBarLayout.rebuildFragments(1);
        ArrayList<BaseFragment> r13 = this.mainFragmentsStack;
        int r02 = r13.size();
    L36:
        if (r1 >= r02) goto L71;
        Object r4 = r13.get(r1);
        r1 = r1 + 1;
        Object r42 = (BaseFragment) r4;
        if ((r42 instanceof MainTabsActivity) == false) goto L41;
        r42 = ((MainTabsActivity) r42).getDialogsActivity();
    L41:
        if ((r42 instanceof DialogsActivity) == false) goto L36;
        DialogsActivity r43 = (DialogsActivity) r42;
        if (r43.isMainDialogList() == false) goto L36;
        r43.setOpenedDialogId(r2, r7);
        goto L36
    L71:
        return;
    L69:
        return;
    L10:
        ArrayList r03 = new ArrayList(this.mainFragmentsStack);
        this.mainFragmentsStack.clear();
        this.rightFragmentsStack.clear();
        this.layerFragmentsStack.clear();
        int r44 = r03.size();
        int r9 = 0;
        long r5 = 0;
        r7 = 0;
    L11:
        if (r9 >= r44) goto L32;
        Object r10 = r03.get(r9);
        r9 = r9 + 1;
        BaseFragment r102 = (BaseFragment) r10;
        if ((r102 instanceof MainTabsActivity) == true) goto L31;
        if ((r102 instanceof DialogsActivity) == false) goto L24;
        DialogsActivity r11 = (DialogsActivity) r102;
        if (r11.isMainDialogList() == false) goto L24;
        if (r11.isArchive() == true) goto L24;
        if (r11.isCommunity() == false) goto L31;
    L24:
        if ((r102 instanceof ChatActivity) == false) goto L30;
        ChatActivity r112 = (ChatActivity) r102;
        if (r112.isInScheduleMode() == true) goto L30;
        this.rightFragmentsStack.add(r102);
        if (r5 != 0) goto L11;
        r5 = r112.getDialogId();
        r7 = r112.getTopicId();
    L30:
        this.layerFragmentsStack.add(r102);
    L31:
        this.mainFragmentsStack.add(r102);
        goto L11
    L32:
        r2 = r5;
        goto L33
    L45:
        return;
    }

    private /* synthetic */ void lambda$animateNavigationBarColor$183(ValueAnimator r1) {
        setNavigationBarColor(((Integer) r1.getAnimatedValue()).intValue());
    }

    private /* synthetic */ void lambda$checkAppUpdate$137(TLRPC.TL_help_appUpdate r3, boolean r4, int r5, Browser.Progress r6) {
        TLRPC.TL_help_appUpdate r0 = SharedConfig.pendingAppUpdate;
        if (r0 == null) goto L11;
        if (r0.version.equals(r3.version) == false) goto L11;
        if (r4 == true) goto L11;
        if (r3.can_not_skip == true) goto L11;
        return;
    L11:
        if (SharedConfig.setNewAppVersionAvailable(r3) == true) goto L13;
        BaseFragment r2 = getLastFragment();
        if (SharedConfig.pendingAppUpdate == null) goto L21;
        SharedConfig.pendingAppUpdate = null;
        SharedConfig.saveConfig();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.appUpdateAvailable, new Object[0]);
    L21:
        if (r6 == null) goto L23;
        r6.end();
    L23:
        if (r2 == null) goto L27;
        if (r4 == false) goto L28;
        BulletinFactory.of(r2).createSimpleBulletin(R.raw.chats_infotip, LocaleController.getString(R.string.YourVersionIsLatest)).show();
        return;
    L28:
        return;
    L27:
        return;
    L13:
        if (r3.can_not_skip == false) goto L16;
        showUpdateActivity(r5, r3, false);
        return;
    L16:
        ApplicationLoader.applicationLoaderInstance.showUpdateAppPopup(this, r3, r5);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.appUpdateAvailable, new Object[0]);
    }

    private /* synthetic */ void lambda$checkAppUpdate$139(boolean r2, int r3, Browser.Progress r4, TLRPC.TL_help_appUpdate r5, TLRPC.TL_error r6) {
        if (r5 == null) goto L6;
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda118(this, r5, r2, r3, r4));
        return;
    L6:
        if (r6 == null) goto L9;
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda119(r4, r6));
        return;
    }

    private /* synthetic */ void lambda$checkFreeDiscSpace$172(DialogInterface r1) {
        this.checkFreeDiscSpaceShown = false;
    }

    private /* synthetic */ void lambda$checkFreeDiscSpace$173() {
        if (this.checkFreeDiscSpaceShown == true) goto L10;
        Dialog r0 = AlertsCreator.createFreeSpaceDialog(this);     // Catch: Throwable -> L7
        r0.setOnDismissListener(new LaunchActivity$$ExternalSyntheticLambda125(this));     // Catch: Throwable -> L7
        this.checkFreeDiscSpaceShown = true;     // Catch: Throwable -> L7
        r0.show();     // Catch: Throwable -> L7
        return;
    L11:
        return;
    }

    private /* synthetic */ void lambda$checkFreeDiscSpace$174(int r9) {
        if (UserConfig.getInstance(this.currentAccount).isClientActivated() == false) goto L31;
        SharedPreferences r0 = MessagesController.getGlobalMainSettings();     // Catch: Throwable -> L25
        if (r9 == 2) goto L11;
        if (r9 == 1) goto L11;
    L13:
        if (Math.abs(r0.getLong("last_space_check", 0) - System.currentTimeMillis()) < 259200000) goto L28;
    L14:
        File r1 = FileLoader.getDirectory(4);     // Catch: Throwable -> L25
        if (r1 == null) goto L29;
        StatFs r3 = new StatFs(r1.getAbsolutePath());     // Catch: Throwable -> L25
        long r4 = r3.getAvailableBlocksLong() * r3.getBlockSizeLong();     // Catch: Throwable -> L25
        if (r9 <= 0) goto L20;
    L21:
        if (r9 <= 0) goto L23;
        this.alreadyShownFreeDiscSpaceAlertForced = System.currentTimeMillis();     // Catch: Throwable -> L25
    L23:
        r0.edit().putLong("last_space_check", System.currentTimeMillis()).apply();     // Catch: Throwable -> L25
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda65(this));     // Catch: Throwable -> L25
        return;
    L20:
        if (r4 < 52428800) goto L21;
        return;
    L29:
        return;
    L28:
        return;
    L11:
        if (Math.abs(this.alreadyShownFreeDiscSpaceAlertForced - System.currentTimeMillis()) > 240000) goto L14;
    L32:
        return;
    }

    private /* synthetic */ void lambda$didReceivedNotification$153(int r1, AlertDialog r2, int r3) {
        if (this.mainFragmentsStack.isEmpty() == true) goto L6;
        MessagesController r12 = MessagesController.getInstance(r1);
        ArrayList<BaseFragment> r0 = this.mainFragmentsStack;
        r12.openByUserName("spambot", r0.get(r0.size() - 1), 1);
        return;
    }

    private /* synthetic */ void lambda$didReceivedNotification$154(AlertDialog r1, int r2) {
        MessagesController.getInstance(this.currentAccount).performLogout(2);
    }

    private /* synthetic */ void lambda$didReceivedNotification$156(HashMap r1, int r2, AlertDialog r3, int r4) {
        if (this.mainFragmentsStack.isEmpty() == false) goto L6;
        return;
    L6:
        if (AndroidUtilities.isMapsInstalled(this.mainFragmentsStack.get(r3.size() - 1)) == true) goto L8;
        return;
    L8:
        LocationActivity r32 = new LocationActivity(0);
        r32.setDelegate(new LaunchActivity$$ExternalSyntheticLambda59(r1, r2));
        presentFragment(r32);
    }

    private /* synthetic */ void lambda$didReceivedNotification$160(ValueAnimator r1) {
        this.frameLayout.invalidate();
    }

    private /* synthetic */ void lambda$didReceivedNotification$161() {
        if (this.isNavigationBarColorFrozen == false) goto L6;
        this.isNavigationBarColorFrozen = false;
        checkSystemBarColors(false, true);
        return;
    }

    private /* synthetic */ void lambda$didReceivedNotification$162(TLObject r2, Theme.ThemeInfo r3) {
        if ((r2 instanceof TLRPC.TL_wallPaper) == false) goto L6;
        TLRPC.TL_wallPaper r22 = (TLRPC.TL_wallPaper) r2;
        this.loadingThemeInfo = r3;
        this.loadingThemeWallpaperName = FileLoader.getAttachFileName(r22.document);
        this.loadingThemeWallpaper = r22;
        FileLoader.getInstance(r3.account).loadFile(r22.document, r22, 1, 1);
        return;
    L6:
        onThemeLoadFinish();
    }

    private /* synthetic */ void lambda$didReceivedNotification$163(Theme.ThemeInfo r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda58(this, r2, r1));
    }

    private /* synthetic */ void lambda$didReceivedNotification$164() {
        if (this.loadingTheme != null) goto L5;
        return;
    L5:
        File r0 = new File(ApplicationLoader.getFilesDirFixed(), "remote" + this.loadingTheme.id + ".attheme");
        TLRPC.TL_theme r1 = this.loadingTheme;
        Theme.ThemeInfo r5 = Theme.applyThemeFile(r0, r1.title, r1, true);
        if (r5 == null) goto L8;
        presentFragment(new ThemePreviewActivity(r5, true, 0, false, false));
    L8:
        onThemeLoadFinish();
    }

    private /* synthetic */ void lambda$didReceivedNotification$165(Theme.ThemeInfo r2, File r3) {
        r2.createBackground(r3, r2.pathToWallpaper);
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda62(this));
    }

    private /* synthetic */ void lambda$didReceivedNotification$169() {
        this.memoryLeakErrorAlertDialog = null;
    }

    private /* synthetic */ void lambda$didReceivedNotification$170(DialogInterface r3) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda60(this), NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
    }

    private /* synthetic */ void lambda$didReceivedNotification$171(Bulletin r1) {
        if (this.finished == false) goto L5;
        return;
    L5:
        if (isResumed == false) goto L9;
        r1.show();
        return;
    }

    private /* synthetic */ void lambda$didSelectDialogs$146(int r10, DialogsActivity r11, boolean r12, ArrayList r13, Uri r14, AlertDialog r15, long r16) {
        boolean r5 = false;
        if (r16 == 0) goto L20;
        Bundle r1 = new Bundle();
        r1.putBoolean("scrollToTopOnResume", true);
        if (AndroidUtilities.isTablet() == true) goto L8;
        NotificationCenter.getInstance(r10).postNotificationName(NotificationCenter.closeChats, new Object[0]);
    L8:
        if (DialogObject.isUserDialog(r16) == false) goto L10;
        r1.putLong("user_id", r16);
    L11:
        ChatActivity r2 = new ChatActivity(r1);
        r2.setOpenImport();
        INavigationLayout r0 = getActionBarLayout();
        if (r11 != null) goto L16;
        if (r12 == true) goto L16;
        boolean r17 = false;
    L17:
        if (r11 != null) goto L19;
        r5 = true;
    L19:
        r0.presentFragment(r2, r17, r5, true, false);
    L29:
        r15.dismiss();     // Catch: Exception -> L26
        return;
    L26:
        e = move-exception;
        FileLog.e(e);
        return;
    L16:
        r17 = true;
        goto L17
    L10:
        r1.putLong("chat_id", -r16);
        goto L11
    L20:
        this.documentsUrisArray = r13;
        if (r13 != null) goto L23;
        this.documentsUrisArray = new ArrayList();
    L23:
        this.documentsUrisArray.add(0, r14);
        openDialogsToSend(true);
        goto L29
    }

    private /* synthetic */ void lambda$didSelectDialogs$147(ChatActivity r23, ArrayList r24, int r25, int r26, CharSequence r27, int r28, boolean r29, TLRPC.User r30, boolean r31, int r32, long r33, boolean r35, long r36) {
        if (r23 == null) goto L5;
        getActionBarLayout().presentFragment(r23, true, false, true, false);
    L5:
        AccountInstance r2 = AccountInstance.getInstance(UserConfig.selectedAccount);
        int r11 = 0;
    L7:
        if (r11 >= r24.size()) goto L31;
        long r4 = ((MessagesStorage.TopicKey) r24.get(r11)).dialogId;
        long r6 = ((MessagesStorage.TopicKey) r24.get(r11)).topicId;
        if (r6 == 0) goto L16;
        TLRPC.TL_forumTopic r3 = r2.getMessagesController().getTopicsController().findTopic(-r4, r6);
        if (r3 == null) goto L16;
        if (r3.topicStartMessage == null) goto L16;
        MessageObject r62 = new MessageObject(r2.getCurrentAccount(), r3.topicStartMessage, false, false);
        r62.isTopicMainMessage = true;
    L15:
        MessageObject r15 = r62;
        if (r32 == 0) goto L19;
        int r20 = r32;
    L20:
        SendMessagesHelper.SendMessageParams r34 = SendMessagesHelper.SendMessageParams.of(r30, r4, r15, r15, null, null, r31, r20, r26);
        if (TextUtils.isEmpty(r27) == false) goto L23;
        r34.effect_id = r33;
    L23:
        r34.invert_media = r35;
        SendMessagesHelper.getInstance(r28).sendMessage(r34);
        if (TextUtils.isEmpty(r27) == true) goto L30;
        if (r32 == 0) goto L28;
        int r7 = r32;
    L29:
        SendMessagesHelper.prepareSendingText(r2, r27, r4, r29, r7, r26, r33);
        goto L30
    L28:
        r7 = r25;
    L30:
        r11 = r11 + 1;
        goto L7
    L19:
        r20 = r25;
    L16:
        r62 = null;
        goto L15
    }

    private /* synthetic */ void lambda$handleIntent$10(Intent r2, boolean r3) {
        handleIntent(r2, true, false, false);
    }

    private /* synthetic */ void lambda$handleIntent$11(Browser.Progress r7, int[] r8, Long r9) {
        if (r7 == null) goto L4;
        r7.end();
    L4:
        if (r9 != null) goto L7;
        return;
    L7:
        if (MessagesController.getInstance(this.currentAccount).getUserOrChat(r9.longValue()) != null) goto L14;
        BaseFragment r6 = getLastFragment();
        if (r6 != null) goto L11;
        return;
    L11:
        if ((r6 instanceof ChatActivity) == false) goto L17;
        ((ChatActivity) r6).shakeContent();
        return;
    L17:
        return;
    L14:
        new GiftSheet(this, r8[0], r9.longValue(), null).show();
    }

    private /* synthetic */ void lambda$handleIntent$12(AlertDialog r1, TLRPC.TL_error r2, String r3, Bundle r4, TLObject r5, TL_account.sendConfirmPhoneCode r6) {
        r1.dismiss();
        if (r2 != null) goto L6;
        presentFragment(new LoginActivity().cancelAccountDeletion(r3, r4, (TLRPC.TL_auth_sentCode) r5));
        return;
    L6:
        AlertsCreator.processError(this.currentAccount, r2, getActionBarLayout().getLastFragment(), r6, new Object[0]);
    }

    private /* synthetic */ void lambda$handleIntent$13(AlertDialog r9, String r10, Bundle r11, TL_account.sendConfirmPhoneCode r12, TLObject r13, TLRPC.TL_error r14) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda57(this, r9, r14, r10, r11, r13, r12));
    }

    private /* synthetic */ void lambda$handleIntent$14(TLObject r3, TLRPC.TL_messages_requestUrlAuth r4, String r5, TLRPC.TL_error r6) {
        BaseFragment r0 = getSafeLastFragment();
        if (r3 != null) goto L5;
        if (r6 != null) goto L18;
        return;
    L18:
        if ("URL_EXPIRED".equalsIgnoreCase(r6.text) == false) goto L21;
        OAuthSheet.getBulletinFactory().createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.BotAuthLoggedInFailTitle), LocaleController.getString(R.string.BotAuthLoggedInFailNoDomain)).show();
        return;
    L21:
        OAuthSheet.getBulletinFactory().showForError(r6);
        return;
    L5:
        if ((r3 instanceof TLRPC.TL_urlAuthResultRequest) == false) goto L9;
        OAuthSheet.handle(false, this.currentAccount, r4, (TLRPC.TL_urlAuthResultRequest) r3);
        return;
    L9:
        if ((r3 instanceof TLRPC.TL_urlAuthResultAccepted) == false) goto L13;
        OAuthSheet.handle(false, this.currentAccount, r4, (TLRPC.TL_urlAuthResultAccepted) r3);
        return;
    L13:
        if ((r3 instanceof TLRPC.TL_urlAuthResultDefault) == false) goto L23;
        AlertsCreator.showOpenUrlAlert(r0, r5, false, true);
        return;
    }

    private /* synthetic */ void lambda$handleIntent$15(TLRPC.TL_messages_requestUrlAuth r7, String r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda64(this, r9, r7, r8, r10));
    }

    private /* synthetic */ void lambda$handleIntent$16(long r4, long r6, ChatActivity r8) {
        FileLog.d("LaunchActivity openForum after load " + r4 + " " + r6 + " TL_forumTopic " + MessagesController.getInstance(this.currentAccount).getTopicsController().findTopic(r4, r6));
        if (this.actionBarLayout == null) goto L6;
        ForumUtilities.applyTopic(r8, MessagesStorage.TopicKey.of(-r4, r6));
        getActionBarLayout().presentFragment(r8);
        return;
    }

    private /* synthetic */ void lambda$handleIntent$18(int[] r4, LocationController.SharingLocationInfo r5) {
        int r0 = r5.messageObject.currentAccount;
        r4[0] = r0;
        switchToAccount(r0, true);
        LocationActivity r02 = new LocationActivity(2);
        r02.setMessageObject(r5.messageObject);
        r02.setDelegate(new LaunchActivity$$ExternalSyntheticLambda112(r4, r5.messageObject.getDialogId()));
        presentFragment(r02);
    }

    private /* synthetic */ void lambda$handleIntent$19() {
        if (this.actionBarLayout.getFragmentStack().isEmpty() == true) goto L6;
        this.actionBarLayout.getFragmentStack().get(0).showDialog(new StickersAlert(this, this.importingStickersSoftware, this.importingStickers, this.importingStickersEmoji, null));
        return;
    }

    private /* synthetic */ void lambda$handleIntent$20(TL_account.Password r1) {
        openEmailSettings(r1);
    }

    private /* synthetic */ void lambda$handleIntent$21(TLObject r1, TLRPC.TL_error r2) {
        if (r1 == null) goto L5;
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda44(this, (TL_account.Password) r1));
        return;
    }

    private /* synthetic */ void lambda$handleIntent$22(BaseFragment r2, boolean r3) {
        presentFragment(r2, r3, false);
    }

    private /* synthetic */ void lambda$handleIntent$23(boolean r9, int[] r10, TLRPC.User r11, String r12, ContactsActivity r13) {
        TLRPC.UserFull r6 = MessagesController.getInstance(this.currentAccount).getUserFull(r11.id);
        if (r6 != null) goto L5;
    L7:
        boolean r4 = false;
    L8:
        VoIPHelper.startCall(r11, r9, r4, this, r6, AccountInstance.getInstance(r10[0]));
        return;
    L5:
        if (r6.video_calls_available == false) goto L7;
        r4 = true;
        goto L8
    }

    private /* synthetic */ void lambda$handleIntent$27(ActionIntroActivity r3, String r4) {
        AlertDialog r0 = new AlertDialog(this, 3);
        r0.setCanCancel(false);
        r0.show();
        byte[] r42 = Base64.decode(r4.substring(17), 8);
        TLRPC.TL_auth_acceptLoginToken r1 = new TLRPC.TL_auth_acceptLoginToken();
        r1.token = r42;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new LaunchActivity$$ExternalSyntheticLambda150(r0, r3));
    }

    private /* synthetic */ void lambda$handleIntent$28(BaseFragment r1, String r2, String r3, AlertDialog r4, int r5) {
        NewContactBottomSheet r42 = new NewContactBottomSheet(r1, this);
        r42.setInitialPhoneNumber(r2, false);
        if (r3 == null) goto L9;
        String[] r12 = r3.split(" ", 2);
        String r0 = r12[0];
        if (r12.length <= 1) goto L7;
        String r13 = r12[1];
    L8:
        r42.setInitialName(r0, r13);
        goto L9
    L7:
        r13 = null;
    L9:
        r42.show();
    }

    private /* synthetic */ void lambda$handleIntent$9(String r3) {
        if (this.actionBarLayout.getFragmentStack().isEmpty() == true) goto L6;
        this.actionBarLayout.getFragmentStack().get(0).presentFragment(new PremiumPreviewFragment(Uri.parse(r3).getQueryParameter("ref")));
        return;
    }

    private /* synthetic */ void lambda$onActivityResult$149() {
        GroupCallPip.clearForce();
        GroupCallPip.updateVisibility(this);
    }

    private /* synthetic */ void lambda$onCreate$1() {
        checkSystemBarColors(true, false);
        if (getLastFragment() != null) goto L5;
        return;
    L5:
        if (getLastFragment().getLastStoryViewer() == null) goto L9;
        getLastFragment().getLastStoryViewer().updatePlayingMode();
        return;
    }

    private /* synthetic */ void lambda$onPowerSaver$148() {
        presentFragment(new LiteModeSettingsActivity());
    }

    private /* synthetic */ void lambda$onResume$152() {
        checkSystemBarColors(true);
    }

    private /* synthetic */ void lambda$openEmailSettings$29() {
        Bulletin.LottieLayout r0 = new Bulletin.LottieLayout(this, null);
        r0.setAnimation(R.raw.email_check_inbox, new String[0]);
        r0.textView.setText(LocaleController.getString(R.string.YourLoginEmailChangedSuccess));
        BaseFragment r2 = getLastFragment();
        if (r2 == null) goto L10;
        Bulletin.make(r2, r0, 1500).show();
        r2.fragmentView.performHapticFeedback(3, 2);     // Catch: Exception -> L7
        return;
    L11:
        return;
    }

    private /* synthetic */ void lambda$openEmailSettings$30(LoginActivity r1, AlertDialog r2, int r3) {
        presentFragment(r1);
    }

    private /* synthetic */ void lambda$openForumFromLink$135(TLObject r15, Integer r16, Integer r17, byte[] r18, long r19, Runnable r21, String r22, int r23, int r24) {
        if ((r15 instanceof TLRPC.messages_Messages) == false) goto L13;
        ArrayList<TLRPC.Message> r152 = ((TLRPC.messages_Messages) r15).messages;
        int r2 = 0;
    L6:
        if (r2 >= r152.size()) goto L13;
        if (r152.get(r2) == null) goto L12;
        if (r152.get(r2).id != r16.intValue()) goto L12;
        TLRPC.Message r153 = r152.get(r2);
    L14:
        if (r153 == null) goto L17;
        int r25 = this.currentAccount;
        Integer r3 = Integer.valueOf(r153.id);
        int r4 = this.currentAccount;
        runCommentRequest(r25, null, r3, null, Long.valueOf(MessageObject.getTopicId(r4, r153, MessagesController.getInstance(r4).isForum(r153))), r17, r18, MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r19)), r21, r22, r23, r24, null);
        return;
    L17:
        Bundle r26 = new Bundle();
        r26.putLong("chat_id", -r19);
        presentFragment(TopicsFragment.getTopicsOrChat(this, r26));
        if (r21 == null) goto L25;
        r21.run();
        return;
    L25:
        return;
    L12:
        r2 = r2 + 1;
    L13:
        r153 = null;
        goto L14
    }

    private /* synthetic */ void lambda$openForumFromLink$136(Integer r5, Integer r6, byte[] r7, long r8, Runnable r10, String r11, int r12, int r13, TLObject r14, TLRPC.TL_error r15) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda138(this, r14, r5, r6, r7, r8, r10, r11, r12, r13));
    }

    private /* synthetic */ void lambda$openMessage$41(Browser.Progress r13, TLObject r14, long r15, int r17, Integer r18, byte[] r19, BaseFragment r20, Bundle r21, ChatActivity r22, String r23, int r24) {
        if (r13 == null) goto L6;
        r13.end();
    L6:
        if ((r14 instanceof TLRPC.TL_messages_chats) == false) goto L21;
        TLRPC.TL_messages_chats r142 = (TLRPC.TL_messages_chats) r14;
        if (r142.chats.isEmpty() == true) goto L21;
        MessagesController.getInstance(this.currentAccount).putChats(r142.chats, false);
        TLRPC.Chat r132 = r142.chats.get(0);
        if (r132 != null) goto L12;
    L14:
        if (r20 != null) goto L16;
    L19:
        ChatActivity r133 = new ChatActivity(r21);
        r22.setHighlightQuote(r17, r23, r24);
        getActionBarLayout().presentFragment(r133);
        return;
    L16:
        if (MessagesController.getInstance(this.currentAccount).checkCanOpenChat(r21, r20) == true) goto L19;
        return;
    L12:
        if (r132.forum == false) goto L14;
        openForumFromLink(-r15, Integer.valueOf(r17), null, r18, r19, null, 0, -1);
    L21:
        showAlertDialog(AlertsCreator.createNoAccessAlert(this, LocaleController.getString(R.string.DialogNotAvailable), LocaleController.getString(R.string.LinkNotFound), null));
    }

    private /* synthetic */ void lambda$openMessage$42(Browser.Progress r15, long r16, int r18, Integer r19, byte[] r20, BaseFragment r21, Bundle r22, ChatActivity r23, String r24, int r25, TLObject r26, TLRPC.TL_error r27) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda177(this, r15, r26, r16, r18, r19, r20, r21, r22, r23, r24, r25));
    }

    private /* synthetic */ void lambda$openMessage$43(int r2) {
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(r2, true);
    }

    private /* synthetic */ void lambda$openMessage$44(Bundle r17, byte[] r18, int r19, Integer r20, String r21, int r22, long r23, Browser.Progress r25, BaseFragment r26) {
        ChatActivity r10 = new ChatActivity(r17);
        if (r18 == null) goto L6;
        r10.highlightPollOptionId = r18;
        r10.setHighlightMessageId(r19);
    L5:
        String r11 = r21;
        int r12 = r22;
    L10:
        if (AndroidUtilities.isTablet() == false) goto L12;
        INavigationLayout r0 = this.rightActionBarLayout;
    L14:
        if (r0.presentFragment(r10) == true) goto L22;
        if (r23 >= 0) goto L22;
        TLRPC.TL_channels_getChannels r13 = new TLRPC.TL_channels_getChannels();
        TLRPC.TL_inputChannel r02 = new TLRPC.TL_inputChannel();
        r02.channel_id = -r23;
        r13.id.add(r02);
        int r03 = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r13, new LaunchActivity$$ExternalSyntheticLambda173(this, r25, r23, r19, r20, r18, r26, r17, r10, r11, r12));
        if (r25 == null) goto L25;
        r25.onCancel(new LaunchActivity$$ExternalSyntheticLambda174(this, r03));
        return;
    L25:
        return;
    L22:
        if (r25 == null) goto L26;
        r25.end();
        return;
    L26:
        return;
    L12:
        r0 = getActionBarLayout();
        goto L14
    L6:
        if (r20 == null) goto L8;
        r10.highlightTaskId = r20;
        r10.setHighlightMessageId(r19);
        goto L5
    L8:
        r11 = r21;
        r12 = r22;
        r10.setHighlightQuote(r19, r11, r12);
        goto L10
    }

    private /* synthetic */ void lambda$openStories$186(int[] r4, long[] r5) {
        int r1 = r4[0] - 1;
        r4[0] = r1;
        if (r1 != 0) goto L6;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesUpdated, new Object[0]);
        openStories(r5, false);
        return;
    }

    private /* synthetic */ void lambda$openStory$184(TLObject r12, int r13, long r14, boolean r16) {
        if ((r12 instanceof TL_stories.TL_stories_stories) == false) goto L24;
        TL_stories.TL_stories_stories r122 = (TL_stories.TL_stories_stories) r12;
        int r0 = 0;
    L5:
        StoriesListPlaceProvider r3 = null;
        if (r0 >= r122.stories.size()) goto L11;
        if (r122.stories.get(r0).id == r13) goto L9;
        r0 = r0 + 1;
        goto L5
    L9:
        TL_stories.StoryItem r4 = r122.stories.get(r0);
    L12:
        if (r4 == null) goto L24;
        r4.dialogId = r14;
        BaseFragment r02 = getLastFragment();
        if (r02 != null) goto L17;
        return;
    L17:
        if ((r02 instanceof DialogsActivity) == true) goto L27;
    L19:
        StoriesListPlaceProvider r9 = r3;
        r02.getOrCreateStoryViewer().instantClose();
        ArrayList<Long> r5 = new ArrayList();
        r5.add(Long.valueOf(r14));
        if (r16 == false) goto L22;
        r02.getOrCreateStoryViewer().showViewsAfterOpening();
    L22:
        r02.getOrCreateStoryViewer().open(this, r4, r5, 0, null, null, r9, false);
        return;
    L27:
        r3 = StoriesListPlaceProvider.of(((DialogsActivity) r02).dialogStoriesCell.recyclerListView);     // Catch: Exception -> L26
        goto L19
    L11:
        r4 = null;
    L24:
        BulletinFactory.global().createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.StoryNotFound)).show(false);
    }

    private /* synthetic */ void lambda$openStory$185(int r4, long r5, boolean r7, TLObject r8, TLRPC.TL_error r9) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda99(this, r8, r4, r5, r7));
    }

    private /* synthetic */ void lambda$openTopicRequest$34(TLRPC.TL_error r14, TLObject r15, int r16, TLRPC.Chat r17, int r18, int r19, Runnable r20, String r21, Integer r22, byte[] r23, int r24, ArrayList r25, int r26) {
        if (r14 != null) goto L11;
        TLRPC.TL_messages_forumTopics r152 = (TLRPC.TL_messages_forumTopics) r15;
        LongSparseArray<TLRPC.Message> r8 = new LongSparseArray();
        int r0 = 0;
    L6:
        if (r0 >= r152.messages.size()) goto L8;
        r8.put(r152.messages.get(r0).id, r152.messages.get(r0));
        r0 = r0 + 1;
        goto L6
    L8:
        MessagesController.getInstance(r16).putUsers(r152.users, false);
        MessagesController.getInstance(r16).putChats(r152.chats, false);
        MessagesController.getInstance(r16).getTopicsController().processTopics(r17.id, r152.topics, r8, false, 2, -1);
        openTopicRequest(r16, r18, r17, r19, MessagesController.getInstance(r16).getTopicsController().findTopic(r17.id, r18), r20, r21, r22, r23, r24, r25, r26);
        return;
    }

    private /* synthetic */ void lambda$openTopicRequest$35(int r16, TLRPC.Chat r17, int r18, int r19, Runnable r20, String r21, Integer r22, byte[] r23, int r24, ArrayList r25, int r26, TLObject r27, TLRPC.TL_error r28) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda176(this, r28, r27, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26));
    }

    private /* synthetic */ boolean lambda$processAttachMenuBot$128(TLRPC.User r3, String r4, int r5, DialogsActivity r6, ArrayList r7, CharSequence r8, boolean r9, boolean r10, int r11, int r12, TopicsFragment r13) {
        long r92 = ((MessagesStorage.TopicKey) r7.get(0)).dialogId;
        Bundle r72 = new Bundle();
        r72.putBoolean("scrollToTopOnResume", true);
        if (DialogObject.isEncryptedDialog(r92) == false) goto L6;
        r72.putInt("enc_id", DialogObject.getEncryptedChatId(r92));
    L9:
        r72.putString("attach_bot", UserObject.getPublicUsername(r3));
        if (r4 == null) goto L13;
        r72.putString("attach_bot_start_command", r4);
    L13:
        if (MessagesController.getInstance(r5).checkCanOpenChat(r72, r6) == false) goto L15;
        NotificationCenter.getInstance(r5).postNotificationName(NotificationCenter.closeChats, new Object[0]);
        getActionBarLayout().presentFragment(new ChatActivity(r72), true, false, true, false);
    L15:
        return true;
    L6:
        if (DialogObject.isUserDialog(r92) == false) goto L8;
        r72.putLong("user_id", r92);
        goto L9
    L8:
        r72.putLong("chat_id", -r92);
        goto L9
    }

    private /* synthetic */ void lambda$processAttachMenuBot$129(DialogsActivity r1, BaseFragment r2, TLRPC.User r3, String r4) {
        if (r1 == null) goto L15;
        if (r2 == null) goto L5;
        r2.dismissCurrentDialog();
    L5:
        int r22 = 0;
    L6:
        int r32 = this.visibleDialogs.size();
        ArrayList<Dialog> r42 = this.visibleDialogs;
        if (r22 >= r32) goto L12;
        if (r42.get(r22).isShowing() == false) goto L11;
        this.visibleDialogs.get(r22).dismiss();
    L11:
        r22 = r22 + 1;
        goto L6
    L12:
        r42.clear();
        presentFragment(r1);
        return;
    L15:
        if ((r2 instanceof ChatActivity) == false) goto L21;
        ((ChatActivity) r2).openAttachBotLayout(r3.id, r4, true);
        return;
    }

    private /* synthetic */ void lambda$processAttachMenuBot$130(TLObject r7, int r8, DialogsActivity r9, BaseFragment r10, TLRPC.User r11, String r12) {
        if ((r7 instanceof TLRPC.TL_boolTrue) == false) goto L6;
        MediaDataController.getInstance(r8).loadAttachMenuBots(false, true, new LaunchActivity$$ExternalSyntheticLambda182(this, r9, r10, r11, r12));
        return;
    }

    private /* synthetic */ void lambda$processAttachMenuBot$131(int r2, DialogsActivity r3, BaseFragment r4, TLRPC.User r5, String r6, TLObject r7, TLRPC.TL_error r8) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda181(this, r7, r2, r3, r4, r5, r6));
    }

    private /* synthetic */ void lambda$processAttachMenuBot$132(int r8, long r9, DialogsActivity r11, BaseFragment r12, TLRPC.User r13, String r14, Boolean r15) {
        TLRPC.TL_messages_toggleBotInAttachMenu r152 = new TLRPC.TL_messages_toggleBotInAttachMenu();
        r152.bot = MessagesController.getInstance(r8).getInputUser(r9);
        r152.enabled = true;
        r152.write_allowed = true;
        ConnectionsManager.getInstance(r8).sendRequest(r152, new LaunchActivity$$ExternalSyntheticLambda175(this, r8, r11, r12, r13, r14), 66);
    }

    private /* synthetic */ void lambda$processAttachMenuBot$133(TLObject r12, int r13, String r14, String r15, TLRPC.User r16, String r17, long r18) {
        if ((r12 instanceof TLRPC.TL_attachMenuBotsBot) == false) goto L60;
        TLRPC.TL_attachMenuBotsBot r122 = (TLRPC.TL_attachMenuBotsBot) r12;
        int r4 = 0;
        MessagesController.getInstance(r13).putUsers(r122.users, false);
        TLRPC.TL_attachMenuBot r123 = r122.bot;
        if (r14 == null) goto L8;
        showAttachMenuBot(this, this.currentAccount, r123, r14, false);
        return;
    L8:
        ArrayList<BaseFragment> r0 = this.mainFragmentsStack;
        BaseFragment r02 = r0.get(r0.size() - 1);
        if (AndroidUtilities.isTablet() == true) goto L11;
    L15:
        BaseFragment r6 = r02;
        ArrayList r03 = new ArrayList();
        if (TextUtils.isEmpty(r15) == true) goto L24;
        String[] r1 = r15.split(" ");
        int r3 = r1.length;
        int r5 = 0;
    L18:
        if (r5 >= r3) goto L24;
        String r9 = r1[r5];
        if (MediaDataController.canShowAttachMenuBotForTarget(r123, r9) == false) goto L22;
        r03.add(r9);
    L22:
        r5 = r5 + 1;
    L24:
        if (r03.isEmpty() == true) goto L26;
        Bundle r19 = new Bundle();
        r19.putInt("dialogsType", 14);
        r19.putBoolean("onlySelect", true);
        r19.putBoolean("allowGroups", r03.contains("groups"));
        r19.putBoolean("allowMegagroups", r03.contains("groups"));
        r19.putBoolean("allowLegacyGroups", r03.contains("groups"));
        r19.putBoolean("allowUsers", r03.contains("users"));
        r19.putBoolean("allowChannels", r03.contains("channels"));
        r19.putBoolean("allowBots", r03.contains("bots"));
        DialogsActivity r04 = new DialogsActivity(r19);
        r04.setDelegate(new LaunchActivity$$ExternalSyntheticLambda170(this, r16, r17, r13));
        DialogsActivity r52 = r04;
    L28:
        if (r123.inactive == true) goto L54;
        if (r52 == null) goto L41;
        if (r6 == null) goto L32;
        r6.dismissCurrentDialog();
    L32:
        int r124 = this.visibleDialogs.size();
        ArrayList<Dialog> r132 = this.visibleDialogs;
        if (r4 >= r124) goto L38;
        if (r132.get(r4).isShowing() == false) goto L37;
        this.visibleDialogs.get(r4).dismiss();
    L37:
        r4 = r4 + 1;
        goto L32
    L38:
        r132.clear();
        presentFragment(r52);
        return;
    L41:
        if ((r6 instanceof ChatActivity) == false) goto L52;
        ChatActivity r11 = (ChatActivity) r6;
        if (r11.getCurrentUser() == null) goto L45;
        TLObject r133 = r11.getCurrentUser();
    L47:
        if (MediaDataController.canShowAttachMenuBot(r123, r133) == true) goto L50;
        BulletinFactory.of(r6).createErrorBulletin(LocaleController.getString(R.string.BotAlreadyAddedToAttachMenu)).show();
        return;
    L50:
        r11.openAttachBotLayout(r16.id, r17, false);
        return;
    L45:
        r133 = r11.getCurrentChat();
        goto L47
    L52:
        BulletinFactory.of(r6).createErrorBulletin(LocaleController.getString(R.string.BotAlreadyAddedToAttachMenu)).show();
        return;
    L54:
        AttachBotIntroTopView r05 = new AttachBotIntroTopView(this);
        r05.setColor(Theme.getColor(Theme.key_chat_attachIcon));
        r05.setBackgroundColor(Theme.getColor(Theme.key_dialogTopBackground));
        r05.setAttachBot(r123);
        LaunchActivity$$ExternalSyntheticLambda171 r06 = new LaunchActivity$$ExternalSyntheticLambda171(this, r13, r18, r52, r6, r16, r17);
        if (r123.request_write_access == false) goto L57;
        TLRPC.User r125 = r16;
    L58:
        WebAppDisclaimerAlert.show(this, r06, r125, null);
        return;
    L57:
        r125 = null;
        goto L58
    L26:
        r52 = null;
        goto L28
    L11:
        if ((r02 instanceof ChatActivity) == true) goto L15;
        if (this.rightFragmentsStack.isEmpty() == true) goto L15;
        ArrayList<BaseFragment> r07 = this.rightFragmentsStack;
        r02 = r07.get(r07.size() - 1);
        goto L15
    L60:
        ArrayList<BaseFragment> r112 = this.mainFragmentsStack;
        BulletinFactory.of(r112.get(r112.size() - 1)).createErrorBulletin(LocaleController.getString(R.string.BotCantAddToAttachMenu)).show();
    }

    private /* synthetic */ void lambda$processAttachMenuBot$134(int r2, String r3, String r4, TLRPC.User r5, String r6, long r7, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda156(this, r9, r2, r3, r4, r5, r6, r7));
    }

    private /* synthetic */ void lambda$processAttachedMenuBotFromShortcut$123(TLRPC.User r2) {
        MessagesController.getInstance(this.currentAccount).openApp(r2, 0);
    }

    private /* synthetic */ void lambda$processAttachedMenuBotFromShortcut$124(TLRPC.User r2, Utilities.Callback r3) {
        MessagesController.getInstance(this.currentAccount).putUser(r2, true);
        r3.run(r2);
    }

    private /* synthetic */ void lambda$processAttachedMenuBotFromShortcut$125(long r2, Utilities.Callback r4) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda92(this, MessagesStorage.getInstance(this.currentAccount).getUser(r2), r4));
    }

    private /* synthetic */ void lambda$processBoostDialog$126(Browser.Progress r7, Long r8, TL_stories.TL_premium_boostsStatus r9, ChatMessageCell r10, Runnable r11, ChannelBoostsController.CanApplyBoost r12) {
        if (r7 == null) goto L4;
        r7.end();
    L4:
        BaseFragment r1 = getLastFragmentIncludeMainTabs();
        if (r1 == null) goto L36;
        Theme.ResourcesProvider r72 = r1.getResourceProvider();
        if (r1.getLastStoryViewer() != null) goto L10;
    L12:
        LimitReachedBottomSheet r0 = new LimitReachedBottomSheet(r1, this, 19, this.currentAccount, r72);
        r0.setCanApplyBoost(r12);
        boolean r73 = true;
        boolean r122 = false;
        if ((r1 instanceof ChatActivity) == false) goto L20;
        if (((ChatActivity) r1).getDialogId() == r8.longValue()) goto L18;
    L17:
        r73 = false;
    L18:
        r122 = r73;
    L26:
        r0.setBoostsStats(r9, r122);
        r0.setDialogId(r8.longValue());
        r0.setChatMessageCell(r10);
        r1.showDialog(r0);
        if (r11 == null) goto L35;
        r11.run();     // Catch: Exception -> L30
        return;
    L30:
        e = move-exception;
        FileLog.e(e);
        return;
    L35:
        return;
    L20:
        if ((r1 instanceof DialogsActivity) == false) goto L26;
        RightSlidingDialogContainer r6 = ((DialogsActivity) r1).rightSlidingDialogContainer;
        if (r6 == null) goto L17;
        if (r6.getCurrentFragmetDialogId() != r8.longValue()) goto L17;
    L10:
        if (r1.getLastStoryViewer().isFullyVisible() == false) goto L12;
        r72 = r1.getLastStoryViewer().getResourceProvider();
        goto L12
    }

    private /* synthetic */ void lambda$processBoostDialog$127(Browser.Progress r10, Runnable r11, ChannelBoostsController r12, Long r13, ChatMessageCell r14, TL_stories.TL_premium_boostsStatus r15) {
        if (r15 != null) goto L8;
        if (r10 == null) goto L5;
        r10.end();
    L5:
        if (r11 == null) goto L10;
        r11.run();
        return;
    L10:
        return;
    L8:
        r12.userCanBoostChannel(r13.longValue(), r15, new LaunchActivity$$ExternalSyntheticLambda94(this, r10, r13, r15, r14, r11));
    }

    private /* synthetic */ void lambda$processWebAppBot$118(int r59, String r60, String r61, String r62, String r63, String r64, String r65, String r66, String r67, String r68, String r69, String r70, String r71, boolean r72, Integer r73, Long r74, Long r75, Integer r76, String r77, HashMap r78, String r79, String r80, String r81, String r82, TLRPC.TL_wallPaper r83, String r84, String r85, String r86, String r87, boolean r88, String r89, int r90, int r91, String r92, String r93, String r94, Browser.Progress r95, boolean r96, int r97, boolean r98, int r99, int r100, boolean r101, String r102, boolean r103, boolean r104, boolean r105, boolean r106, boolean r107, String r108, boolean r109) {
        runLinkRequest(r59, r60, r61, r62, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, null, null, r95, r96, r97, r98, r99, r100, null, null, r101, r102, r103, r104, r105, r106, r107, r108, null, r109, null);
    }

    private /* synthetic */ void lambda$processWebAppBot$119(BaseFragment r22, int r23, TLRPC.User r24, TLRPC.TL_messages_botApp r25, AtomicBoolean r26, String r27, boolean r28, boolean r29, boolean r30, boolean r31) {
        if (r22 != null) goto L5;
        return;
    L5:
        if (isActive == true) goto L7;
        return;
    L7:
        if (isFinishing() == false) goto L9;
        return;
    L9:
        if (isDestroyed() == true) goto L28;
        long r4 = r24.id;
        WebViewRequestProps r2 = WebViewRequestProps.of(r23, r4, r4, null, null, 3, 0, 0, false, r25.app, r26.get(), r27, r24, 0, r28, r29);
        if (getBottomSheetTabs() != null) goto L14;
    L16:
        SharedPrefsHelper.setWebViewConfirmShown(this.currentAccount, r24.id, true);
        BotWebViewSheet r42 = new BotWebViewSheet(this, r22.getResourceProvider());
        r42.setWasOpenedByLinkIntent(r30);
        r42.setDefaultFullsize(!r28);
        if (r29 == false) goto L19;
        r42.setFullscreen(true, false);
    L19:
        r42.setNeedsContext(false);
        r42.setParentActivity(this);
        r42.requestWebView(r22, r2);
        r42.show();
        if (r25.inactive == true) goto L22;
        if (r31 == true) goto L22;
        return;
    L22:
        r42.showJustAddedBulletin();
        return;
    L14:
        if (getBottomSheetTabs().tryReopenTab(r2) == null) goto L16;
        return;
    }

    private /* synthetic */ void lambda$processWebAppBot$121(Runnable r15, int r16, TLRPC.User r17, TLRPC.TL_messages_botApp r18, String r19, boolean r20, boolean r21, boolean r22, boolean r23, boolean r24, boolean r25, Browser.Progress r26) {
        r15.run();
        AtomicBoolean r6 = new AtomicBoolean();
        ArrayList<BaseFragment> r152 = this.mainFragmentsStack;
        if (r152 != null) goto L5;
    L8:
        BaseFragment r2 = null;
    L9:
        LaunchActivity$$ExternalSyntheticLambda166 r0 = new LaunchActivity$$ExternalSyntheticLambda166(this, r2, r16, r17, r18, r6, r19, r20, r21, r22, r23);
        if (r24 == false) goto L14;
        r0.run();
        return;
    L14:
        if (r18.inactive == false) goto L23;
        if (r25 == false) goto L23;
        LaunchActivity$$ExternalSyntheticLambda167 r153 = new LaunchActivity$$ExternalSyntheticLambda167(r0);
        if (r26 == null) goto L19;
        ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda16 r02 = new ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda16(r26);
    L20:
        WebAppDisclaimerAlert.show(this, r153, null, r02);
        return;
    L19:
        r02 = null;
    L23:
        if (r18.request_write_access == true) goto L28;
        if (r23 == true) goto L28;
        r0.run();
        return;
    L28:
        AlertsCreator.createBotLaunchAlert(r2, r6, r17, r0);
        return;
    L5:
        if (r152.isEmpty() == true) goto L8;
        r2 = this.mainFragmentsStack.get(r15.size() - 1);
        goto L9
    }

    private /* synthetic */ void lambda$processWebAppBot$122(Browser.Progress r54, int r55, String r56, String r57, String r58, String r59, String r60, String r61, String r62, String r63, String r64, String r65, String r66, String r67, boolean r68, Integer r69, Long r70, Long r71, Integer r72, String r73, HashMap r74, String r75, String r76, String r77, String r78, TLRPC.TL_wallPaper r79, String r80, String r81, String r82, String r83, boolean r84, String r85, int r86, int r87, String r88, String r89, String r90, boolean r91, int r92, boolean r93, int r94, int r95, boolean r96, String r97, boolean r98, boolean r99, boolean r100, boolean r101, boolean r102, String r103, boolean r104, Runnable r105, TLRPC.User r106, String r107, boolean r108, boolean r109, TLObject r110, TLRPC.TL_error r111) {
        if (r54 == null) goto L4;
        r54.end();
    L4:
        if (r111 == null) goto L7;
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda157(this, r55, r56, r57, r58, r59, r60, r61, r62, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r54, r91, r92, r93, r94, r95, r96, r97, r98, r99, r100, r101, r102, r103, r104));
        return;
    L7:
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda158(this, r105, r55, r106, (TLRPC.TL_messages_botApp) r110, r107, r98, r99, r100, r91, r108, r109, r54));
    }

    private /* synthetic */ void lambda$runCommentRequest$32(AtomicBoolean r16, TLObject r17, int r18, boolean r19, TLRPC.Chat r20, Long r21, Integer r22, Integer r23, Runnable r24, String r25, Integer r26, byte[] r27, int r28, int r29, TLRPC.TL_messages_getDiscussionMessage r30, WidePosts.CommentsPostAuthorLoader r31, Runnable r32) {
        if (r16 == null) goto L7;
        if (r16.get() == false) goto L7;
        return;
    L7:
        if (isFinishing() == false) goto L10;
        return;
    L10:
        if ((r17 instanceof TLRPC.TL_messages_discussionMessage) == false) goto L61;
        TLRPC.TL_messages_discussionMessage r1 = (TLRPC.TL_messages_discussionMessage) r17;
        MessagesController.getInstance(r18).putUsers(r1.users, false);
        MessagesController.getInstance(r18).putChats(r1.chats, false);
        ArrayList<MessageObject> r2 = new ArrayList();
        int r5 = r1.messages.size();
        int r7 = 0;
    L12:
        if (r7 >= r5) goto L18;
        TLRPC.Message r10 = r1.messages.get(r7);
        if (r19 == false) goto L16;
        r10.isThreadMessage = true;
    L16:
        r2.add(new MessageObject(r18, r10, true, true));
        r7 = r7 + 1;
        goto L12
    L18:
        if (r2.isEmpty() == false) goto L25;
        if (r20.forum == false) goto L61;
        if (r21 == null) goto L61;
        if (r21.longValue() != 1) goto L61;
    L25:
        if (r20.forum == false) goto L31;
        int r0 = (int) r21.longValue();
        if (r22 == null) goto L29;
        Integer r12 = r22;
    L30:
        openTopicRequest(r18, r0, r20, r12.intValue(), null, r24, r25, r26, r27, r28, r2, r29);
    L53:
        if (r32 != null) goto L63;
    L55:
        if (r24 == null) goto L69;
        r24.run();     // Catch: Exception -> L58
        return;
    L58:
        e = move-exception;
        FileLog.e(e);
        return;
    L69:
        return;
    L63:
        r32.run();     // Catch: Exception -> L58
        goto L55
    L29:
        r12 = r23;
        goto L30
    L31:
        Bundle r3 = new Bundle();
        r3.putLong("chat_id", -r2.get(0).getDialogId());
        r3.putInt("message_id", Math.max(1, r23.intValue()));
        ChatActivity r4 = new ChatActivity(r3);
        r4.setThreadMessages(r2, r20, r30.msg_id, r1.read_inbox_max_id, r1.read_outbox_max_id, null);
        if (r31 == null) goto L34;
        r31.setMessages(r2, r4);
        r31.load(r30.msg_id, new LaunchActivity$$ExternalSyntheticLambda155(r16));
    L34:
        if (r22 == null) goto L40;
        if (r27 == null) goto L37;
        r4.highlightPollOptionId = r27;
        r4.setHighlightMessageId(r22.intValue());
    L46:
        presentFragment(r4);
        goto L53
    L37:
        if (r25 == null) goto L39;
        r4.setHighlightQuote(r22.intValue(), r25, r29);
        goto L46
    L39:
        r4.highlightTaskId = r26;
        r4.setHighlightMessageId(r22.intValue());
        goto L46
    L40:
        if (r21 == null) goto L46;
        if (r27 == null) goto L43;
        r4.highlightPollOptionId = r27;
        r4.setHighlightMessageId(r23.intValue());
        goto L46
    L43:
        if (r25 == null) goto L45;
        r4.setHighlightQuote(r23.intValue(), r25, r29);
        goto L46
    L45:
        r4.highlightTaskId = r26;
        r4.setHighlightMessageId(r23.intValue());
    L61:
    L51:
        e = move-exception;
        FileLog.e(e);
        goto L53
    L48:
        if (this.mainFragmentsStack.isEmpty() == true) goto L53;
        ArrayList<BaseFragment> r15 = this.mainFragmentsStack;     // Catch: Exception -> L51
        BulletinFactory.of(r15.get(r15.size() - 1)).createErrorBulletin(LocaleController.getString(R.string.ChannelPostDeleted)).show();     // Catch: Exception -> L51
        goto L53
    }

    private /* synthetic */ void lambda$runCommentRequest$33(AtomicBoolean r20, int r21, boolean r22, TLRPC.Chat r23, Long r24, Integer r25, Integer r26, Runnable r27, String r28, Integer r29, byte[] r30, int r31, int r32, TLRPC.TL_messages_getDiscussionMessage r33, WidePosts.CommentsPostAuthorLoader r34, Runnable r35, TLObject r36, TLRPC.TL_error r37) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda135(this, r20, r36, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35));
    }

    private /* synthetic */ void lambda$runImportRequest$36(TLObject r10, Uri r11, int r12, AlertDialog r13) {
        if (isFinishing() == true) goto L82;
        boolean r0 = false;
        if (r10 == null) goto L66;
        if (this.actionBarLayout == null) goto L66;
        TLRPC.TL_messages_historyImportParsed r102 = (TLRPC.TL_messages_historyImportParsed) r10;
        Bundle r2 = new Bundle();
        r2.putBoolean("onlySelect", true);
        r2.putString("importTitle", r102.title);
        r2.putBoolean("allowSwitchAccount", true);
        if (r102.pm == false) goto L12;
        r2.putInt("dialogsType", 12);
    L31:
        if (SecretMediaViewer.hasInstance() == false) goto L36;
        if (SecretMediaViewer.getInstance().isVisible() == false) goto L36;
        SecretMediaViewer.getInstance().closePhoto(false, false);
    L45:
        StoryRecorder.destroyInstance();
        GroupCallActivity r103 = GroupCallActivity.groupCallInstance;
        if (r103 == null) goto L49;
        r103.dismiss();
    L49:
        if (AndroidUtilities.isTablet() == false) goto L51;
        this.actionBarLayout.rebuildFragments(1);
        this.rightActionBarLayout.rebuildFragments(1);
    L51:
        DialogsActivity r4 = new DialogsActivity(r2);
        r4.setDelegate(this);
        if (AndroidUtilities.isTablet() == false) goto L60;
        if (this.layersActionBarLayout.getFragmentStack().isEmpty() == true) goto L64;
        if ((this.layersActionBarLayout.getFragmentStack().get(this.layersActionBarLayout.getFragmentStack().size() - 1) instanceof MainTabsActivity) == false) goto L64;
    L57:
        r0 = true;
    L64:
        getActionBarLayout().presentFragment(r4, r0, false, true, false);
    L74:
        r13.dismiss();     // Catch: Exception -> L71
        return;
    L71:
        e = move-exception;
        FileLog.e(e);
        return;
    L60:
        if (this.actionBarLayout.getFragmentStack().size() <= 1) goto L64;
        if ((this.actionBarLayout.getFragmentStack().get(this.actionBarLayout.getFragmentStack().size() - 1) instanceof MainTabsActivity) == false) goto L64;
    L36:
        if (PhotoViewer.hasInstance() == false) goto L41;
        if (PhotoViewer.getInstance().isVisible() == false) goto L41;
        PhotoViewer.getInstance().closePhoto(false, true);
    L41:
        if (ArticleViewer.hasInstance() == false) goto L45;
        if (ArticleViewer.getInstance().isVisible() == false) goto L45;
        ArticleViewer.getInstance().close(false, true);
        goto L45
    L12:
        if (r102.group == false) goto L14;
        r2.putInt("dialogsType", 11);
        goto L31
    L14:
        String r104 = r11.toString();
        Iterator<String> r112 = MessagesController.getInstance(r12).exportPrivateUri.iterator();
    L16:
        if (r112.hasNext() == false) goto L20;
        if (r104.contains(r112.next()) == false) goto L16;
        r2.putInt("dialogsType", 12);
        boolean r113 = true;
    L21:
        if (r113 == true) goto L31;
        Iterator<String> r122 = MessagesController.getInstance(r12).exportGroupUri.iterator();
    L24:
        if (r122.hasNext() == false) goto L28;
        if (r104.contains(r122.next()) == false) goto L24;
        r2.putInt("dialogsType", 11);
        r113 = true;
    L28:
        if (r113 == true) goto L31;
        r2.putInt("dialogsType", 13);
        goto L31
    L20:
        r113 = false;
    L66:
        if (this.documentsUrisArray != null) goto L68;
        this.documentsUrisArray = new ArrayList();
    L68:
        this.documentsUrisArray.add(0, this.exportingChatUri);
        this.exportingChatUri = null;
        openDialogsToSend(true);
        goto L74
    }

    private /* synthetic */ void lambda$runImportRequest$37(Uri r2, int r3, AlertDialog r4, TLObject r5, TLRPC.TL_error r6) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda126(this, r5, r2, r3, r4), 2);
    }

    private /* synthetic */ void lambda$runLinkRequest$100(Runnable r1, TLObject r2, TLRPC.TL_error r3) {
        r1.run();     // Catch: Exception -> L4
    L7:
        if ((r2 instanceof TLRPC.TL_langPackLanguage) == false) goto L9;
        showAlertDialog(AlertsCreator.createLanguageAlert(this, (TLRPC.TL_langPackLanguage) r2));
        return;
    L9:
        if (r3 != null) goto L11;
        return;
    L11:
        if ("LANG_CODE_NOT_SUPPORTED".equals(r3.text) == false) goto L13;
        showAlertDialog(AlertsCreator.createSimpleAlert(this, LocaleController.getString(R.string.LanguageUnsupportedError)));
        return;
    L13:
        showAlertDialog(AlertsCreator.createSimpleAlert(this, LocaleController.getString(R.string.ErrorOccurred) + "\n" + r3.text));
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L7
    }

    private /* synthetic */ void lambda$runLinkRequest$101(Runnable r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda98(this, r2, r3, r4));
    }

    private /* synthetic */ void lambda$runLinkRequest$102(ThemePreviewActivity r1) {
        presentFragment(r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private /* synthetic */ void lambda$runLinkRequest$103(Runnable r11, TLObject r12, TLRPC.TL_wallPaper r13, TLRPC.TL_error r14) {
        r11.run();     // Catch: Exception -> L4
    L7:
        if ((r12 instanceof TLRPC.TL_wallPaper) == false) goto L12;
        TLRPC.TL_wallPaper r122 = (TLRPC.TL_wallPaper) r12;
        if (r122.pattern == false) goto L11;
        String r1 = r122.slug;
        TLRPC.WallPaperSettings r112 = r13.settings;
        WallpapersListActivity.ColorWallpaper r0 = new WallpapersListActivity.ColorWallpaper(r1, r112.background_color, r112.second_background_color, r112.third_background_color, r112.fourth_background_color, AndroidUtilities.getWallpaperRotation(r112.rotation, false), r11.intensity / 100.0f, r13.settings.motion, null);
        r0.pattern = r122;
        r122 = r0;
    L11:
        ThemePreviewActivity r113 = new ThemePreviewActivity(r122, null, true, false);
        TLRPC.WallPaperSettings r123 = r13.settings;
        r113.setInitialModes(r123.blur, r123.motion, r123.intensity);
        presentFragment(r113);
        return;
    L12:
        showAlertDialog(AlertsCreator.createSimpleAlert(this, LocaleController.getString(R.string.ErrorOccurred) + "\n" + r14.text));
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L7
    }

    private /* synthetic */ void lambda$runLinkRequest$104(Runnable r7, TLRPC.TL_wallPaper r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda131(this, r7, r9, r8, r10));
    }

    private /* synthetic */ void lambda$runLinkRequest$105(Browser.Progress r2) {
        this.loadingThemeFileName = null;
        this.loadingThemeWallpaperName = null;
        this.loadingThemeWallpaper = null;
        this.loadingThemeInfo = null;
        this.loadingThemeProgressDialog = null;
        this.loadingTheme = null;
        if (r2 == null) goto L6;
        r2.end();
        return;
    }

    private /* synthetic */ void lambda$runLinkRequest$106(TLObject r6, AlertDialog r7, Runnable r8, TLRPC.TL_error r9) {
        if ((r6 instanceof TLRPC.TL_theme) == false) goto L25;
        TLRPC.TL_theme r62 = (TLRPC.TL_theme) r6;
        TLRPC.TL_wallPaper r0 = null;
        char r2 = 0;
        if (r62.settings.size() <= 0) goto L7;
        TLRPC.ThemeSettings r92 = r62.settings.get(0);
    L8:
        if (r92 == null) goto L22;
        Theme.ThemeInfo r3 = Theme.getTheme(Theme.getBaseThemeKey(r92));
        if (r3 == null) goto L28;
        TLRPC.WallPaper r93 = r92.wallpaper;
        if ((r93 instanceof TLRPC.TL_wallPaper) == false) goto L41;
        r0 = (TLRPC.TL_wallPaper) r93;
        if (FileLoader.getInstance(this.currentAccount).getPathToAttach(r0.document, true).exists() == true) goto L41;
        this.loadingThemeProgressDialog = r7;
        this.loadingThemeAccent = true;
        this.loadingThemeInfo = r3;
        this.loadingTheme = r62;
        this.loadingThemeWallpaper = r0;
        this.loadingThemeWallpaperName = FileLoader.getAttachFileName(r0.document);
        FileLoader.getInstance(this.currentAccount).loadFile(r0.document, r0, 1, 1);
        return;
    L41:
        r8.run();     // Catch: Exception -> L19
    L21:
        openThemeAccentPreview(r62, r0, r3);
    L30:
        if (r2 == 0) goto L43;
        r8.run();     // Catch: Exception -> L33
    L35:
        if (r2 != 1) goto L37;
        showAlertDialog(AlertsCreator.createSimpleAlert(this, LocaleController.getString(R.string.Theme), LocaleController.getString(R.string.ThemeNotSupported)));
        return;
    L37:
        showAlertDialog(AlertsCreator.createSimpleAlert(this, LocaleController.getString(R.string.Theme), LocaleController.getString(R.string.ThemeNotFound)));
        return;
    L33:
        e = move-exception;
        FileLog.e(e);
        goto L35
    L43:
        return;
    L19:
        e = move-exception;
        FileLog.e(e);
    L28:
        r2 = 1;
        goto L30
    L22:
        TLRPC.Document r94 = r62.document;
        if (r94 == null) goto L28;
        this.loadingThemeAccent = false;
        this.loadingTheme = r62;
        this.loadingThemeFileName = FileLoader.getAttachFileName(r94);
        this.loadingThemeProgressDialog = r7;
        FileLoader.getInstance(this.currentAccount).loadFile(this.loadingTheme.document, r62, 1, 1);
        goto L30
    L7:
        r92 = null;
        goto L8
    L25:
        if (r9 != null) goto L27;
    L29:
        r2 = 2;
        goto L30
    L27:
        if ("THEME_FORMAT_INVALID".equals(r9.text) == false) goto L29;
        goto L28
    }

    private /* synthetic */ void lambda$runLinkRequest$107(AlertDialog r7, Runnable r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda93(this, r9, r7, r8, r10));
    }

    private /* synthetic */ void lambda$runLinkRequest$108(TLObject r12, int[] r13, int r14, Runnable r15, Integer r16, Integer r17, Long r18, Integer r19, AtomicBoolean r20) {
        if ((r12 instanceof TLRPC.TL_messages_chats) == false) goto L14;
        TLRPC.TL_messages_chats r0 = (TLRPC.TL_messages_chats) r12;
        if (r0.chats.isEmpty() == true) goto L14;
        MessagesController.getInstance(this.currentAccount).putChats(r0.chats, false);
        r13[0] = runCommentRequest(r14, r15, r16, r17, r18, r19, r0.chats.get(0), r20);
        return;
    L14:
        r15.run();     // Catch: Exception -> L10
    L12:
        showAlertDialog(AlertsCreator.createNoAccessAlert(this, LocaleController.getString(R.string.DialogNotAvailable), LocaleController.getString(R.string.LinkNotFound), null));
        return;
    L10:
        e = move-exception;
        FileLog.e(e);
        goto L12
    }

    private /* synthetic */ void lambda$runLinkRequest$109(int[] r1, int r2, Runnable r3, Integer r4, Integer r5, Long r6, Integer r7, AtomicBoolean r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda110(this, r9, r1, r2, r3, r4, r5, r6, r7, r8));
    }

    private /* synthetic */ void lambda$runLinkRequest$111(Runnable r14, TLObject r15, boolean r16, Long r17, Browser.Progress r18, Long r19, Integer r20, Integer r21, byte[] r22, BaseFragment r23, int r24, Bundle r25) {
        r14.run();     // Catch: Exception -> L5
    L8:
        if ((r15 instanceof TLRPC.TL_messages_chats) == false) goto L27;
        TLRPC.TL_messages_chats r0 = (TLRPC.TL_messages_chats) r15;
        if (r0.chats.isEmpty() == true) goto L27;
        MessagesController.getInstance(this.currentAccount).putChats(r0.chats, false);
        TLRPC.Chat r02 = r0.chats.get(0);
        if (r02 == null) goto L17;
        if (r16 == false) goto L17;
        if (ChatObject.isBoostSupported(r02) == false) goto L17;
        processBoostDialog(Long.valueOf(-r17.longValue()), null, r18);
    L23:
        if (r23 != null) goto L25;
    L26:
        getActionBarLayout().presentFragment(new ChatActivity(r25));
        return;
    L25:
        if (MessagesController.getInstance(r24).checkCanOpenChat(r25, r23) == true) goto L26;
        return;
    L17:
        if (r02 == null) goto L23;
        if (r02.forum == false) goto L23;
        if (r19 == null) goto L22;
        openForumFromLink(-r17.longValue(), r20, null, r21, r22, null, 0, -1);
        goto L23
    L22:
        openForumFromLink(-r17.longValue(), null, null, r21, r22, null, 0, -1);
    L27:
        showAlertDialog(AlertsCreator.createNoAccessAlert(this, LocaleController.getString(R.string.DialogNotAvailable), LocaleController.getString(R.string.LinkNotFound), null));
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
        goto L8
    }

    private /* synthetic */ void lambda$runLinkRequest$112(Runnable r1, boolean r2, Long r3, Browser.Progress r4, Long r5, Integer r6, Integer r7, byte[] r8, BaseFragment r9, int r10, Bundle r11, TLObject r12, TLRPC.TL_error r13) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda144(this, r1, r12, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11));
    }

    private /* synthetic */ void lambda$runLinkRequest$113(Bundle r16, Long r17, int[] r18, Runnable r19, boolean r20, Browser.Progress r21, Long r22, Integer r23, Integer r24, byte[] r25, BaseFragment r26, int r27) {
        if (getActionBarLayout().presentFragment(new ChatActivity(r16)) == true) goto L6;
        TLRPC.TL_channels_getChannels r0 = new TLRPC.TL_channels_getChannels();
        TLRPC.TL_inputChannel r1 = new TLRPC.TL_inputChannel();
        r1.channel_id = r17.longValue();
        r0.id.add(r1);
        r18[0] = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new LaunchActivity$$ExternalSyntheticLambda114(this, r19, r20, r17, r21, r22, r23, r24, r25, r26, r27, r16));
        return;
    }

    private /* synthetic */ void lambda$runLinkRequest$114(TLObject r8) {
        if ((r8 instanceof TL_account.resolvedBusinessChatLinks) == false) goto L14;
        TL_account.resolvedBusinessChatLinks r82 = (TL_account.resolvedBusinessChatLinks) r8;
        MessagesController.getInstance(this.currentAccount).putUsers(r82.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r82.chats, false);
        MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r82.users, r82.chats, true, true);
        Bundle r0 = new Bundle();
        TLRPC.Peer r1 = r82.peer;
        if ((r1 instanceof TLRPC.TL_peerUser) == false) goto L8;
        r0.putLong("user_id", r1.user_id);
    L12:
        ChatActivity r12 = new ChatActivity(r0);
        r12.setResolvedChatLink(r82);
        presentFragment(r12, false, true);
        return;
    L8:
        if ((r1 instanceof TLRPC.TL_peerChat) == false) goto L10;
    L11:
        r0.putLong("chat_id", r1.channel_id);
        goto L12
    L10:
        if ((r1 instanceof TLRPC.TL_peerChannel) == false) goto L12;
    L14:
        showAlertDialog(AlertsCreator.createSimpleAlert(this, LocaleController.getString(R.string.BusinessLink), LocaleController.getString(R.string.BusinessLinkInvalid)));
    }

    private /* synthetic */ void lambda$runLinkRequest$115(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda111(this, r1));
    }

    private /* synthetic */ void lambda$runLinkRequest$47(int r59, String r60, String r61, String r62, String r63, String r64, String r65, String r66, String r67, String r68, String r69, String r70, String r71, boolean r72, Integer r73, Long r74, Long r75, Integer r76, String r77, HashMap r78, String r79, String r80, String r81, String r82, TLRPC.TL_wallPaper r83, String r84, String r85, String r86, String r87, boolean r88, String r89, int r90, String r91, String r92, String r93, String r94, String r95, Browser.Progress r96, boolean r97, int r98, boolean r99, int r100, int r101, String r102, String r103, boolean r104, String r105, boolean r106, boolean r107, boolean r108, boolean r109, boolean r110, String r111, Integer r112, boolean r113, byte[] r114, int r115) {
        if (r115 == r59) goto L5;
        LaunchActivity r2 = this;
        r2.switchToAccount(r115, true);
    L6:
        r2.runLinkRequest(r115, r60, r61, r62, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, 1, r90, r91, r92, r93, r94, r95, r96, r97, r98, r99, r100, r101, r102, r103, r104, r105, r106, r107, r108, r109, r110, r111, r112, r113, r114);
        return;
    L5:
        r2 = this;
        goto L6
    }

    private /* synthetic */ void lambda$runLinkRequest$49(TLObject r3, int r4, String r5, TLRPC.TL_error r6, Runnable r7) {
        if ((r3 instanceof TLRPC.User) == false) goto L5;
        TLRPC.User r32 = (TLRPC.User) r3;
        MessagesController.getInstance(r4).putUser(r32, false);
        Bundle r42 = new Bundle();
        r42.putLong("user_id", r32.id);
        presentFragment(new ChatActivity(r42));
    L15:
        r7.run();     // Catch: Exception -> L12
        return;
    L12:
        e = move-exception;
        FileLog.e(e);
        return;
    L5:
        StringBuilder r33 = new StringBuilder("cant import contact token. token=");
        r33.append(r5);
        r33.append(" err=");
        if (r6 != null) goto L8;
        String r43 = null;
    L9:
        r33.append(r43);
        FileLog.e(r33.toString());
        BulletinFactory.of(this.mainFragmentsStack.get(r2.size() - 1)).createErrorBulletin(LocaleController.getString(R.string.NoUsernameFound)).show();
        goto L15
    L8:
        r43 = r6.text;
        goto L9
    }

    private /* synthetic */ void lambda$runLinkRequest$50(int r8, String r9, Runnable r10, TLObject r11, TLRPC.TL_error r12) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda116(this, r11, r8, r9, r12, r10));
    }

    private /* synthetic */ void lambda$runLinkRequest$51(TLObject r6, int r7, String r8, Runnable r9) {
        BaseFragment r5 = this.mainFragmentsStack.get(r5.size() - 1);
        if ((r6 instanceof TL_chatlists.chatlist_ChatlistInvite) == false) goto L21;
        TL_chatlists.chatlist_ChatlistInvite r62 = (TL_chatlists.chatlist_ChatlistInvite) r6;
        boolean r0 = r62 instanceof TL_chatlists.TL_chatlists_chatlistInvite;
        if (r0 == false) goto L8;
        TL_chatlists.TL_chatlists_chatlistInvite r1 = (TL_chatlists.TL_chatlists_chatlistInvite) r62;
        ArrayList<TLRPC.Chat> r2 = r1.chats;
        ArrayList<TLRPC.User> r12 = r1.users;
    L11:
        MessagesController.getInstance(r7).putChats(r2, false);
        MessagesController.getInstance(r7).putUsers(r12, false);
        if (r0 == true) goto L14;
    L17:
        FolderBottomSheet r72 = new FolderBottomSheet(r5, r8, r62);
        if (r5 == null) goto L20;
        r5.showDialog(r72);
    L27:
        r9.run();     // Catch: Exception -> L24
        return;
    L24:
        e = move-exception;
        FileLog.e(e);
        return;
    L20:
        r72.show();
        goto L27
    L14:
        if (((TL_chatlists.TL_chatlists_chatlistInvite) r62).peers.isEmpty() == false) goto L17;
        BulletinFactory.of(r5).createErrorBulletin(LocaleController.getString(R.string.NoFolderFound)).show();
        goto L27
    L8:
        if ((r62 instanceof TL_chatlists.TL_chatlists_chatlistInviteAlready) == false) goto L10;
        TL_chatlists.TL_chatlists_chatlistInviteAlready r13 = (TL_chatlists.TL_chatlists_chatlistInviteAlready) r62;
        r2 = r13.chats;
        r12 = r13.users;
        goto L11
    L10:
        r2 = null;
        r12 = null;
        goto L11
    L21:
        BulletinFactory.of(r5).createErrorBulletin(LocaleController.getString(R.string.NoFolderFound)).show();
        goto L27
    }

    private /* synthetic */ void lambda$runLinkRequest$52(int r2, String r3, Runnable r4, TLObject r5, TLRPC.TL_error r6) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda97(this, r5, r2, r3, r4));
    }

    private /* synthetic */ void lambda$runLinkRequest$53(Runnable r8, TL_payments.TL_StarGiftAuctionState r9, TLRPC.TL_error r10) {
        if (r10 == null) goto L4;
        BulletinFactory.of(this.mainFragmentsStack.get(r7.size() - 1)).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.GiftAuctionNotFound)).show();
    L13:
        r8.run();     // Catch: Exception -> L10
        return;
    L10:
        e = move-exception;
        FileLog.e(e);
        return;
    L4:
        if (r9 == null) goto L13;
        GiftAuctionController.Auction r92 = GiftAuctionController.getInstance(this.currentAccount).getAuction(r9.gift.id);
        if (r92 == null) goto L13;
        new StarGiftPreviewSheet(this, null, this.currentAccount, r92.gift.title, r92.previewAttributes, false).show();
        goto L13
    }

    private /* synthetic */ void lambda$runLinkRequest$54(Runnable r9, TL_payments.TL_StarGiftAuctionState r10, TLRPC.TL_error r11) {
        if (r11 == null) goto L4;
        BulletinFactory.of(this.mainFragmentsStack.get(r8.size() - 1)).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.GiftAuctionNotFound)).show();
    L11:
        r9.run();     // Catch: Exception -> L8
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
        return;
    L4:
        if (r10 == null) goto L11;
        AuctionJoinSheet.show(this, null, this.currentAccount, 0, r10.gift.id, null);
        goto L11
    }

    private /* synthetic */ void lambda$runLinkRequest$55(TLRPC.TL_error r7, TLObject r8, int r9, String r10, Runnable r11) {
        if (r7 == null) goto L11;
        BaseFragment r6 = getSafeLastFragment();
        if (r6 != null) goto L7;
        return;
    L7:
        if ("STARGIFT_ALREADY_BURNED".equalsIgnoreCase(r7.text) == false) goto L9;
        BulletinFactory.of(r6).createSimpleBulletin(R.raw.fire_on, LocaleController.getString(R.string.UniqueGiftNotFoundBurned)).show();
    L28:
        r11.run();     // Catch: Exception -> L25
        return;
    L25:
        e = move-exception;
        FileLog.e(e);
        return;
    L9:
        BulletinFactory.of(r6).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.UniqueGiftNotFound)).show();
        goto L28
    L11:
        if ((r8 instanceof TL_stars.TL_payments_uniqueStarGift) == false) goto L28;
        TL_stars.TL_payments_uniqueStarGift r82 = (TL_stars.TL_payments_uniqueStarGift) r8;
        MessagesController.getInstance(this.currentAccount).putUsers(r82.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r82.chats, false);
        BaseFragment r72 = getSafeLastFragment();
        TL_stars.StarGift r83 = r82.gift;
        if ((r83 instanceof TL_stars.TL_starGiftUnique) == false) goto L28;
        StarGiftSheet r0 = new StarGiftSheet(this, r9, 0, null);
        StarGiftSheet r62 = r0.set(r10, (TL_stars.TL_starGiftUnique) r83, null);
        if (r72 != null) goto L17;
        r62.show();
        goto L28
    L17:
        if (r72.getLastStoryViewer() != null) goto L19;
    L21:
        r72.showDialog(r62);
        goto L28
    L19:
        if (r72.getLastStoryViewer().isFullyVisible() == false) goto L21;
        r72.getLastStoryViewer().showDialog(r62);
        goto L28
    }

    private /* synthetic */ void lambda$runLinkRequest$56(int r8, String r9, Runnable r10, TLObject r11, TLRPC.TL_error r12) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda122(this, r12, r11, r8, r9, r10));
    }

    private /* synthetic */ void lambda$runLinkRequest$60(TLRPC.TL_error r7, TLObject r8, TLRPC.TL_inputInvoiceSlug r9, Runnable r10, int r11, String r12) {
        if (r7 == null) goto L8;
        boolean r72 = "SUBSCRIPTION_ALREADY_ACTIVE".equalsIgnoreCase(r7.text);
        ArrayList<BaseFragment> r6 = this.mainFragmentsStack;
        if (r72 == false) goto L6;
        BulletinFactory.of(r6.get(r6.size() - 1)).createErrorBulletin(LocaleController.getString(R.string.PaymentInvoiceSubscriptionLinkAlreadyPaid)).show();
    L30:
        r10.run();     // Catch: Exception -> L27
        return;
    L27:
        e = move-exception;
        FileLog.e(e);
        return;
    L6:
        BulletinFactory.of(r6.get(r6.size() - 1)).createErrorBulletin(LocaleController.getString(R.string.PaymentInvoiceLinkInvalid)).show();
        goto L30
    L8:
        if (isFinishing() == true) goto L30;
        if ((r8 instanceof TLRPC.TL_payments_paymentFormStars) == false) goto L14;
        Runnable r73 = this.navigateToPremiumGiftCallback;
        this.navigateToPremiumGiftCallback = null;
        StarsController.getInstance(this.currentAccount).openPaymentForm(null, r9, (TLRPC.TL_payments_paymentFormStars) r8, new LaunchActivity$$ExternalSyntheticLambda132(r10), new LaunchActivity$$ExternalSyntheticLambda133(r73));
        return;
    L14:
        if ((r8 instanceof TLRPC.PaymentForm) == false) goto L17;
        TLRPC.PaymentForm r82 = (TLRPC.PaymentForm) r8;
        MessagesController.getInstance(r11).putUsers(r82.users, false);
        PaymentFormActivity r74 = new PaymentFormActivity(r82, r12, getActionBarLayout().getLastFragment());
    L20:
        if (r74 == null) goto L30;
        Runnable r83 = this.navigateToPremiumGiftCallback;
        if (r83 == null) goto L24;
        this.navigateToPremiumGiftCallback = null;
        r74.setPaymentFormCallback(new LaunchActivity$$ExternalSyntheticLambda134(r83));
    L24:
        presentFragment(r74);
        goto L30
    L17:
        if ((r8 instanceof TLRPC.PaymentReceipt) == false) goto L19;
        r74 = new PaymentFormActivity((TLRPC.PaymentReceipt) r8);
        goto L20
    L19:
        r74 = null;
        goto L20
    }

    private /* synthetic */ void lambda$runLinkRequest$61(TLRPC.TL_inputInvoiceSlug r9, Runnable r10, int r11, String r12, TLObject r13, TLRPC.TL_error r14) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda117(this, r14, r13, r9, r10, r11, r12));
    }

    private /* synthetic */ void lambda$runLinkRequest$62(Runnable r3, Long r4, TL_stories.StoryItem r5) {
        r3.run();     // Catch: Exception -> L4
    L6:
        BaseFragment r32 = getLastFragment();
        if (r5 != null) goto L12;
        BulletinFactory r2 = BulletinFactory.global();
        if (r2 == null) goto L21;
        r2.createSimpleBulletin(R.raw.story_bomb2, LocaleController.getString(R.string.StoryNotFound)).show();
        return;
    L21:
        return;
    L12:
        if ((r5 instanceof TL_stories.TL_storyItemDeleted) == false) goto L16;
        BulletinFactory r22 = BulletinFactory.global();
        if (r22 == null) goto L23;
        r22.createSimpleBulletin(R.raw.story_bomb1, LocaleController.getString(R.string.StoryNotFound)).show();
        return;
    L23:
        return;
    L16:
        if (r32 == null) goto L25;
        r5.dialogId = r4.longValue();
        StoryViewer r33 = r32.createOverlayStoryViewer();
        r33.instantClose();
        r33.open(this, r5, null);
        return;
    L25:
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L6
    }

    private /* synthetic */ void lambda$runLinkRequest$63(Runnable r3, Long r4, TL_stories.StoryItem r5) {
        r3.run();     // Catch: Exception -> L4
    L6:
        BaseFragment r32 = getLastFragment();
        if (r5 != null) goto L12;
        BulletinFactory r2 = BulletinFactory.global();
        if (r2 == null) goto L21;
        r2.createSimpleBulletin(R.raw.story_bomb2, LocaleController.getString(R.string.StoryNotFound)).show();
        return;
    L21:
        return;
    L12:
        if ((r5 instanceof TL_stories.TL_storyItemDeleted) == false) goto L16;
        BulletinFactory r22 = BulletinFactory.global();
        if (r22 == null) goto L23;
        r22.createSimpleBulletin(R.raw.story_bomb1, LocaleController.getString(R.string.StoryNotFound)).show();
        return;
    L23:
        return;
    L16:
        if (r32 == null) goto L25;
        r5.dialogId = r4.longValue();
        StoryViewer r33 = r32.createOverlayStoryViewer();
        r33.instantClose();
        r33.open(this, r5, null);
        return;
    L25:
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L6
    }

    private /* synthetic */ void lambda$runLinkRequest$64(Runnable r5, Long r6, int r7, TL_stories.TL_storyAlbum r8) {
        r5.run();     // Catch: Exception -> L4
    L6:
        getLastFragment();
        if (r8 != null) goto L12;
        BulletinFactory r4 = BulletinFactory.global();
        if (r4 == null) goto L24;
        r4.createSimpleBulletin(R.raw.story_bomb2, LocaleController.getString(R.string.StoryAlbumNotFound)).show();
        return;
    L24:
        return;
    L12:
        Bundle r52 = new Bundle();
        if (r6.longValue() <= 0) goto L19;
        r52.putLong("user_id", r6.longValue());
        if (r6.longValue() != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L17;
        boolean r62 = true;
    L18:
        r52.putBoolean("my_profile", r62);
    L20:
        r52.putInt("open_story_album_id", r7);
        presentFragment(new ProfileActivity(r52));
        return;
    L17:
        r62 = false;
        goto L18
    L19:
        r52.putLong("chat_id", -r6.longValue());
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L6
    }

    private /* synthetic */ void lambda$runLinkRequest$65(int r59, String r60, String r61, String r62, String r63, String r64, String r65, String r66, String r67, String r68, String r69, String r70, String r71, boolean r72, Integer r73, Long r74, Long r75, Integer r76, String r77, HashMap r78, String r79, String r80, String r81, String r82, TLRPC.TL_wallPaper r83, String r84, String r85, String r86, String r87, boolean r88, String r89, int r90, int r91, String r92, String r93, String r94, Browser.Progress r95, boolean r96, int r97, boolean r98, int r99, int r100, String r101, String r102, boolean r103, String r104, boolean r105, boolean r106, boolean r107, boolean r108, boolean r109, String r110, Integer r111, boolean r112, byte[] r113) {
        runLinkRequest(r59, r60, r61, r62, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, null, null, r95, r96, r97, r98, r99, r100, r101, r102, r103, r104, r105, r106, r107, r108, r109, r110, r111, r112, r113);
    }

    private /* synthetic */ void lambda$runLinkRequest$68(TLRPC.TL_attachMenuBot r62, int r63, Long r64, String r65, String r66, String r67, String r68, String r69, String r70, String r71, String r72, String r73, String r74, String r75, String r76, boolean r77, Integer r78, Long r79, Long r80, Integer r81, String r82, HashMap r83, String r84, String r85, String r86, String r87, TLRPC.TL_wallPaper r88, String r89, String r90, String r91, String r92, boolean r93, String r94, int r95, int r96, String r97, String r98, String r99, String r100, String r101, Browser.Progress r102, boolean r103, int r104, int r105, int r106, boolean r107, String r108, TLRPC.User r109, Runnable r110, boolean r111, boolean r112, boolean r113, boolean r114, boolean r115, boolean r116, String r117, boolean r118, Boolean r119) {
        r62.inactive = false;
        r62.request_write_access = false;
        TLRPC.TL_messages_toggleBotInAttachMenu r0 = new TLRPC.TL_messages_toggleBotInAttachMenu();
        r0.bot = MessagesController.getInstance(r63).getInputUser(r64.longValue());
        r0.enabled = true;
        r0.write_allowed = true;
        ConnectionsManager.getInstance(r63).sendRequest(r0, new LaunchActivity$$ExternalSyntheticLambda163(r63), 66);
        processWebAppBot(r63, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, r95, r96, r97, r98, r99, r100, r101, r102, r103, r104, false, r105, r106, r107, r108, r109, r110, r111, true, r112, r113, r114, r115, r116, r117, r118);
    }

    private /* synthetic */ void lambda$runLinkRequest$71(Long r59, TLRPC.TL_attachMenuBot r60, AtomicBoolean r61, int r62, String r63, String r64, String r65, String r66, String r67, String r68, String r69, String r70, String r71, String r72, String r73, String r74, boolean r75, Integer r76, Long r77, Long r78, Integer r79, String r80, HashMap r81, String r82, String r83, String r84, String r85, TLRPC.TL_wallPaper r86, String r87, String r88, String r89, String r90, boolean r91, String r92, int r93, int r94, String r95, String r96, String r97, String r98, String r99, Browser.Progress r100, boolean r101, int r102, int r103, int r104, boolean r105, String r106, TLRPC.User r107, Runnable r108, boolean r109, boolean r110, boolean r111, boolean r112, boolean r113, String r114, boolean r115) {
        SharedPrefsHelper.setWebViewConfirmShown(this.currentAccount, r59.longValue(), true);
        r60.inactive = false;
        r60.request_write_access = !r61.get();
        TLRPC.TL_messages_toggleBotInAttachMenu r0 = new TLRPC.TL_messages_toggleBotInAttachMenu();
        r0.bot = MessagesController.getInstance(r62).getInputUser(r59.longValue());
        r0.write_allowed = r61.get();
        ConnectionsManager.getInstance(r62).sendRequest(r0, new LaunchActivity$$ExternalSyntheticLambda168(r62), 66);
        processWebAppBot(r62, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, r95, r96, r97, r98, r99, r100, r101, r102, false, r103, r104, r105, r106, r107, r108, false, false, r109, r110, r111, r112, r113, r114, r115);
    }

    private /* synthetic */ void lambda$runLinkRequest$72(TLRPC.TL_error r61, int r62, String r63, String r64, String r65, String r66, String r67, String r68, String r69, String r70, String r71, String r72, String r73, String r74, boolean r75, Integer r76, Long r77, Long r78, Integer r79, String r80, HashMap r81, String r82, String r83, String r84, String r85, TLRPC.TL_wallPaper r86, String r87, String r88, String r89, String r90, boolean r91, String r92, int r93, int r94, String r95, String r96, String r97, Browser.Progress r98, boolean r99, int r100, boolean r101, int r102, int r103, String r104, String r105, boolean r106, String r107, boolean r108, boolean r109, boolean r110, boolean r111, boolean r112, String r113, Integer r114, boolean r115, byte[] r116, TLObject r117, Long r118, String r119, String r120, TLRPC.User r121, Runnable r122) {
        if (r61 == null) goto L7;
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda160(this, r62, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, r95, r96, r97, r98, r99, r100, r101, r102, r103, r104, r105, r106, r107, r108, r109, r110, r111, r112, r113, r114, r115, r116));
        return;
    L7:
        if ((r117 instanceof TLRPC.TL_attachMenuBotsBot) == false) goto L38;
        TLRPC.TL_attachMenuBot r2 = ((TLRPC.TL_attachMenuBotsBot) r117).bot;
        if (r2 != null) goto L11;
    L15:
        boolean r51 = false;
    L17:
        if (r2.inactive == false) goto L19;
    L22:
        if (r51 == false) goto L31;
        LaunchActivity$$ExternalSyntheticLambda161 r0 = new LaunchActivity$$ExternalSyntheticLambda161(this, r2, r62, r118, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, r95, r96, r97, r119, r120, r98, r99, r100, r102, r103, r106, r107, r121, r122, r51, r108, r109, r110, r111, r112, r113, r115);
        if (r98 == null) goto L27;
        ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda16 r3 = new ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda16(r98);
    L28:
        WebAppDisclaimerAlert.show(this, r0, null, r3);
        return;
    L27:
        r3 = null;
    L31:
        if (r2.request_write_access == true) goto L36;
        if (r99 == true) goto L36;
        processWebAppBot(r62, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, r95, r96, r97, r119, r120, r98, r99, r100, false, r102, r103, r106, r107, r121, r122, false, false, r108, r109, r110, r111, r112, r113, r115);
        return;
    L36:
        AtomicBoolean r4 = new AtomicBoolean(true);
        AlertsCreator.createBotLaunchAlert(getLastFragment(), r4, r121, new LaunchActivity$$ExternalSyntheticLambda162(this, r118, r2, r4, r62, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, r95, r96, r97, r119, r120, r98, r99, r100, r102, r103, r106, r107, r121, r122, r108, r109, r110, r111, r112, r113, r115));
        return;
    L19:
        if (r2.side_menu_disclaimer_needed == false) goto L31;
    L11:
        if (r2.show_in_side_menu == false) goto L13;
    L14:
        r51 = true;
        goto L17
    L13:
        if (r2.show_in_attach_menu == false) goto L15;
    }

    private /* synthetic */ void lambda$runLinkRequest$73(int r65, String r66, String r67, String r68, String r69, String r70, String r71, String r72, String r73, String r74, String r75, String r76, String r77, boolean r78, Integer r79, Long r80, Long r81, Integer r82, String r83, HashMap r84, String r85, String r86, String r87, String r88, TLRPC.TL_wallPaper r89, String r90, String r91, String r92, String r93, boolean r94, String r95, int r96, int r97, String r98, String r99, String r100, Browser.Progress r101, boolean r102, int r103, boolean r104, int r105, int r106, String r107, String r108, boolean r109, String r110, boolean r111, boolean r112, boolean r113, boolean r114, boolean r115, String r116, Integer r117, boolean r118, byte[] r119, Long r120, String r121, String r122, TLRPC.User r123, Runnable r124, TLObject r125, TLRPC.TL_error r126) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda139(this, r126, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, r95, r96, r97, r98, r99, r100, r101, r102, r103, r104, r105, r106, r107, r108, r109, r110, r111, r112, r113, r114, r115, r116, r117, r118, r119, r125, r120, r121, r122, r123, r124));
    }

    private /* synthetic */ boolean lambda$runLinkRequest$74(String r8, int r9, TLRPC.User r10, DialogsActivity r11, ArrayList r12, CharSequence r13, boolean r14, boolean r15, int r16, int r17, TopicsFragment r18) {
        long r1 = ((MessagesStorage.TopicKey) r12.get(0)).dialogId;
        TLRPC.TL_inputMediaGame r122 = new TLRPC.TL_inputMediaGame();
        TLRPC.TL_inputGameShortName r3 = new TLRPC.TL_inputGameShortName();
        r122.id = r3;
        r3.short_name = r8;
        r3.bot_id = MessagesController.getInstance(r9).getInputUser(r10);
        SendMessagesHelper.getInstance(r9).sendGame(MessagesController.getInstance(r9).getInputPeer(r1), r122, 0, 0);
        Bundle r82 = new Bundle();
        r82.putBoolean("scrollToTopOnResume", true);
        if (DialogObject.isEncryptedDialog(r1) == false) goto L6;
        r82.putInt("enc_id", DialogObject.getEncryptedChatId(r1));
    L10:
        if (MessagesController.getInstance(r9).checkCanOpenChat(r82, r11) == false) goto L12;
        NotificationCenter.getInstance(r9).postNotificationName(NotificationCenter.closeChats, new Object[0]);
        getActionBarLayout().presentFragment(new ChatActivity(r82), true, false, true, false);
    L12:
        return true;
    L6:
        if (DialogObject.isUserDialog(r1) == false) goto L8;
        r82.putLong("user_id", r1);
        goto L10
    L8:
        r82.putLong("chat_id", -r1);
        goto L10
    }

    private /* synthetic */ void lambda$runLinkRequest$75(int r6, TLRPC.Chat r7, DialogsActivity r8) {
        NotificationCenter.getInstance(r6).postNotificationName(NotificationCenter.closeChats, new Object[0]);
        Bundle r62 = new Bundle();
        r62.putBoolean("scrollToTopOnResume", true);
        r62.putLong("chat_id", r7.id);
        if (MessagesController.getInstance(this.currentAccount).checkCanOpenChat(r62, r8) == true) goto L5;
        return;
    L5:
        presentFragment(new ChatActivity(r62), true, false);
    }

    private /* synthetic */ void lambda$runLinkRequest$76(String r24, TLRPC.TL_chatAdminRights r25, boolean r26, String r27, int r28, TLRPC.Chat r29, DialogsActivity r30, TLRPC.User r31, long r32, String r34) {
        if (r24 == null) goto L92;
        String[] r1 = r24.split("[+ ]");
        TLRPC.TL_chatAdminRights r9 = new TLRPC.TL_chatAdminRights();
        int r10 = r1.length;
        int r11 = 0;
    L5:
        if (r11 >= r10) goto L93;
        String r12 = r1[r11];
        r12.getClass();
        char r14 = 65535;
        switch(r12.hashCode()) {
            case -2110462504: goto L74;
            case -2095811475: goto L70;
            case -1654794275: goto L66;
            case -1593320096: goto L62;
            case -939200543: goto L58;
            case 22162680: goto L54;
            case 22169074: goto L50;
            case 106069776: goto L46;
            case 323243326: goto L42;
            case 449085338: goto L38;
            case 632157522: goto L34;
            case 703163861: goto L30;
            case 758599179: goto L26;
            case 1357805750: goto L22;
            case 1529816162: goto L18;
            case 1542893206: goto L14;
            case 1641337725: goto L10;
            default: goto L77;
        };
    L77:
        switch(r14) {
            case 0: goto L90;
            case 1: goto L89;
            case 2: goto L88;
            case 3: goto L87;
            case 4: goto L86;
            case 5: goto L85;
            case 6: goto L84;
            case 7: goto L84;
            case 8: goto L83;
            case 9: goto L82;
            case 10: goto L81;
            case 11: goto L83;
            case 12: goto L80;
            case 13: goto L79;
            case 14: goto L82;
            case 15: goto L90;
            case 16: goto L85;
            default: goto L91;
        };
    L79:
        r9.pin_messages = true;
        goto L91
    L80:
        r9.post_messages = true;
        goto L91
    L81:
        r9.invite_users = true;
        goto L91
    L82:
        r9.add_admins = true;
        goto L91
    L83:
        r9.manage_topics = true;
        goto L91
    L84:
        r9.other = true;
        goto L91
    L85:
        r9.manage_call = true;
        goto L91
    L86:
        r9.edit_messages = true;
        goto L91
    L87:
        r9.delete_messages = true;
        goto L91
    L88:
        r9.change_info = true;
        goto L91
    L89:
        r9.anonymous = true;
        goto L91
    L90:
        r9.ban_users = true;
    L91:
        r11 = r11 + 1;
        goto L5
    L10:
        if (r12.equals("manage_video_chats") == false) goto L77;
        r14 = 16;
        goto L77
    L14:
        if (r12.equals("restrict_members") == false) goto L77;
        r14 = 15;
        goto L77
    L18:
        if (r12.equals("add_admins") == false) goto L77;
        r14 = 14;
        goto L77
    L22:
        if (r12.equals("pin_messages") == false) goto L77;
        r14 = '\r';
        goto L77
    L26:
        if (r12.equals("post_messages") == false) goto L77;
        r14 = '\f';
        goto L77
    L30:
        if (r12.equals("manage_topic") == false) goto L77;
        r14 = 11;
        goto L77
    L34:
        if (r12.equals("invite_users") == false) goto L77;
        r14 = '\n';
        goto L77
    L38:
        if (r12.equals("promote_members") == false) goto L77;
        r14 = '\t';
        goto L77
    L42:
        if (r12.equals("manage_topics") == false) goto L77;
        r14 = '\b';
        goto L77
    L46:
        if (r12.equals("other") == false) goto L77;
        r14 = 7;
        goto L77
    L50:
        if (r12.equals("manage_chat") == false) goto L77;
        r14 = 6;
        goto L77
    L54:
        if (r12.equals("manage_call") == false) goto L77;
        r14 = 5;
        goto L77
    L58:
        if (r12.equals("edit_messages") == false) goto L77;
        r14 = 4;
        goto L77
    L62:
        if (r12.equals("delete_messages") == false) goto L77;
        r14 = 3;
        goto L77
    L66:
        if (r12.equals("change_info") == false) goto L77;
        r14 = 2;
        goto L77
    L70:
        if (r12.equals("anonymous") == false) goto L77;
        r14 = 1;
        goto L77
    L74:
        if (r12.equals("ban_users") == false) goto L77;
        r14 = 0;
    L93:
        if (r9 != null) goto L97;
        if (r25 != null) goto L97;
        TLRPC.TL_chatAdminRights r15 = null;
    L180:
        if (r26 == false) goto L186;
        if (r9 != null) goto L186;
        if (TextUtils.isEmpty(r27) == true) goto L186;
        MessagesController.getInstance(this.currentAccount).addUserToChat(r29.id, r31, 0, r27, r30, true, new LaunchActivity$$ExternalSyntheticLambda172(this, r28, r29, r30), null);
        return;
    L186:
        ChatRightsEditActivity r102 = new ChatRightsEditActivity(r31.id, -r32, r15, null, null, r34, 2, true, !r26, r27);
        r102.setDelegate(new AnonymousClass13(this, r30, r28));
        getActionBarLayout().presentFragment(r102, false);
        return;
    L97:
        if (r9 == null) goto L179;
        if (r25 != null) goto L102;
        r15 = r9;
        goto L180
    L102:
        if (r9.change_info == false) goto L104;
    L107:
        boolean r13 = true;
    L108:
        r25.change_info = r13;
        if (r9.post_messages == false) goto L111;
    L114:
        boolean r16 = true;
    L115:
        r25.post_messages = r16;
        if (r9.edit_messages == false) goto L118;
    L121:
        boolean r17 = true;
    L122:
        r25.edit_messages = r17;
        if (r9.add_admins == false) goto L125;
    L128:
        boolean r18 = true;
    L129:
        r25.add_admins = r18;
        if (r9.delete_messages == false) goto L132;
    L135:
        boolean r19 = true;
    L136:
        r25.delete_messages = r19;
        if (r9.ban_users == false) goto L139;
    L142:
        boolean r110 = true;
    L143:
        r25.ban_users = r110;
        if (r9.invite_users == false) goto L146;
    L149:
        boolean r111 = true;
    L150:
        r25.invite_users = r111;
        if (r9.pin_messages == false) goto L153;
    L156:
        boolean r112 = true;
    L157:
        r25.pin_messages = r112;
        if (r9.manage_call == false) goto L160;
    L163:
        boolean r113 = true;
    L164:
        r25.manage_call = r113;
        if (r9.anonymous == false) goto L167;
    L170:
        boolean r114 = true;
    L171:
        r25.anonymous = r114;
        if (r9.other == false) goto L174;
    L177:
        boolean r115 = true;
    L178:
        r25.other = r115;
        goto L179
    L174:
        if (r25.other == true) goto L177;
        r115 = false;
        goto L178
    L167:
        if (r25.anonymous == true) goto L170;
        r114 = false;
        goto L171
    L160:
        if (r25.manage_call == true) goto L163;
        r113 = false;
        goto L164
    L153:
        if (r25.pin_messages == true) goto L156;
        r112 = false;
        goto L157
    L146:
        if (r25.invite_users == true) goto L149;
        r111 = false;
        goto L150
    L139:
        if (r25.ban_users == true) goto L142;
        r110 = false;
        goto L143
    L132:
        if (r25.delete_messages == true) goto L135;
        r19 = false;
        goto L136
    L125:
        if (r25.add_admins == true) goto L128;
        r18 = false;
        goto L129
    L118:
        if (r25.edit_messages == true) goto L121;
        r17 = false;
        goto L122
    L111:
        if (r25.post_messages == true) goto L114;
        r16 = false;
        goto L115
    L104:
        if (r25.change_info == true) goto L107;
        r13 = false;
    L179:
        r15 = r25;
        goto L180
    L92:
        r9 = null;
        goto L93
    }

    private /* synthetic */ void lambda$runLinkRequest$77(String r14, String r15, int r16, TLRPC.Chat r17, DialogsActivity r18, TLRPC.User r19, long r20, boolean r22, TLRPC.TL_chatAdminRights r23, String r24) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda151(this, r14, r23, r22, r15, r16, r17, r18, r19, r20, r24));
    }

    private /* synthetic */ void lambda$runLinkRequest$78(long r10, int r12, TLRPC.User r13, String r14, AlertDialog r15, int r16) {
        Bundle r0 = new Bundle();
        r0.putBoolean("scrollToTopOnResume", true);
        long r2 = -r10;
        r0.putLong("chat_id", r2);
        ChatActivity r7 = new ChatActivity(r0);
        NotificationCenter.getInstance(r12).postNotificationName(NotificationCenter.closeChats, new Object[0]);
        MessagesController.getInstance(r12).addUserToChat(r2, r13, 0, r14, r7, null);
        getActionBarLayout().presentFragment(r7, true, false, true, false);
    }

    private /* synthetic */ boolean lambda$runLinkRequest$79(int r13, TLRPC.User r14, String r15, String r16, DialogsActivity r17, DialogsActivity r18, ArrayList r19, CharSequence r20, boolean r21, boolean r22, int r23, int r24, TopicsFragment r25) {
        long r10 = ((MessagesStorage.TopicKey) r19.get(0)).dialogId;
        TLRPC.Chat r7 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r10));
        if (r7 != null) goto L5;
    L11:
        AlertDialog.Builder r152 = new AlertDialog.Builder(this);
        r152.setTitle(LocaleController.getString(R.string.AddBot));
        if (r7 != null) goto L14;
        String r0 = _UrlKt.FRAGMENT_ENCODE_SET;
    L15:
        r152.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("AddMembersAlertNamesText", R.string.AddMembersAlertNamesText, new Object[]{UserObject.getUserName(r14), r0})));
        r152.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r152.setPositiveButton(LocaleController.getString(R.string.AddBot), new LaunchActivity$$ExternalSyntheticLambda143(this, r10, r13, r14, r16));
        r152.show();
        return true;
    L14:
        r0 = r7.title;
        goto L15
    L5:
        if (r7.creator == true) goto L10;
        TLRPC.TL_chatAdminRights r1 = r7.admin_rights;
        if (r1 == null) goto L11;
        if (r1.add_admins == false) goto L11;
    L10:
        MessagesController.getInstance(r13).checkIsInChat(false, r7, r14, new LaunchActivity$$ExternalSyntheticLambda142(this, r15, r16, r13, r7, r17, r14, r10));
        return true;
    }

    private /* synthetic */ void lambda$runLinkRequest$80(TLObject r60, int r61, TLRPC.Chat r62, Runnable r63, String r64, String r65, String r66, String r67, String r68, String r69, String r70, String r71, String r72, String r73, String r74, String r75, boolean r76, Integer r77, Long r78, Long r79, Integer r80, String r81, HashMap r82, String r83, String r84, String r85, String r86, TLRPC.TL_wallPaper r87, String r88, String r89, String r90, String r91, boolean r92, String r93, int r94, int r95, String r96, String r97, String r98, String r99, String r100, Browser.Progress r101, boolean r102, int r103, boolean r104, int r105, int r106, String r107, String r108, boolean r109, String r110, boolean r111, boolean r112, boolean r113, boolean r114, boolean r115, String r116, Integer r117, boolean r118, byte[] r119) {
        if ((r60 instanceof TLRPC.TL_messages_chatFull) == false) goto L18;
        TLRPC.TL_messages_chatFull r0 = (TLRPC.TL_messages_chatFull) r60;
        MessagesController.getInstance(r61).putUsers(r0.users, false);
        MessagesController.getInstance(r61).putChats(r0.chats, false);
        MessagesStorage.getInstance(r61).putUsersAndChats(r0.users, r0.chats, false, true);
        if (MessagesController.getInstance(r61).getChat(Long.valueOf(r62.linked_monoforum_id)) == null) goto L20;
        runLinkRequest(r61, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, r95, r96, r97, r98, r99, r100, r101, r102, r103, r104, r105, r106, r107, r108, r109, r110, r111, r112, r113, r114, r115, r116, r117, r118, r119);
        return;
    L20:
        r63.run();     // Catch: Exception -> L8
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
        return;
    L18:
        r63.run();     // Catch: Exception -> L15
        return;
    L15:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$runLinkRequest$81(int r63, TLRPC.Chat r64, Runnable r65, String r66, String r67, String r68, String r69, String r70, String r71, String r72, String r73, String r74, String r75, String r76, String r77, boolean r78, Integer r79, Long r80, Long r81, Integer r82, String r83, HashMap r84, String r85, String r86, String r87, String r88, TLRPC.TL_wallPaper r89, String r90, String r91, String r92, String r93, boolean r94, String r95, int r96, int r97, String r98, String r99, String r100, String r101, String r102, Browser.Progress r103, boolean r104, int r105, boolean r106, int r107, int r108, String r109, String r110, boolean r111, String r112, boolean r113, boolean r114, boolean r115, boolean r116, boolean r117, String r118, Integer r119, boolean r120, byte[] r121, TLObject r122, TLRPC.TL_error r123) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda137(this, r122, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, r95, r96, r97, r98, r99, r100, r101, r102, r103, r104, r105, r106, r107, r108, r109, r110, r111, r112, r113, r114, r115, r116, r117, r118, r119, r120, r121));
    }

    private /* synthetic */ void lambda$runLinkRequest$83(Runnable r67, boolean r68, int r69, int r70, String r71, String r72, boolean r73, String r74, String r75, int r76, String r77, String r78, String r79, String r80, String r81, String r82, String r83, String r84, String r85, String r86, String r87, String r88, boolean r89, Integer r90, Long r91, Long r92, Integer r93, HashMap r94, String r95, String r96, String r97, String r98, TLRPC.TL_wallPaper r99, String r100, String r101, String r102, int r103, int r104, String r105, String r106, String r107, Browser.Progress r108, boolean r109, int r110, String r111, String r112, boolean r113, String r114, boolean r115, boolean r116, boolean r117, boolean r118, boolean r119, String r120, Integer r121, boolean r122, byte[] r123, String r124, int[] r125, AtomicBoolean r126, Long r127) {
        if (r127 == null) goto L13;
        if (r127.longValue() != LongCompanionObject.MAX_VALUE) goto L13;
        r67.run();     // Catch: Exception -> L8
    L10:
        new AlertDialog.Builder(this, null).setTitle(LocaleController.getString(R.string.AffiliateLinkExpiredTitle)).setMessage(LocaleController.getString(R.string.AffiliateLinkExpiredText)).setNegativeButton(LocaleController.getString(R.string.OK), null).show();
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
    L13:
        if (isFinishing() == true) goto L303;
        if (r68 == false) goto L17;
        if (r127 == null) goto L17;
        MessagesController.getInstance(this.currentAccount).getStoriesController().resolveLiveStoryLink(r127.longValue(), new LaunchActivity$$ExternalSyntheticLambda102(this, r67, r127));
        return;
    L17:
        if (r69 == 0) goto L20;
        if (r127 == null) goto L20;
        MessagesController.getInstance(this.currentAccount).getStoriesController().resolveStoryLink(r127.longValue(), r69, new LaunchActivity$$ExternalSyntheticLambda103(this, r67, r127));
        return;
    L20:
        if (r70 == 0) goto L23;
        if (r127 == null) goto L23;
        MessagesController.getInstance(this.currentAccount).getStoriesController().resolveStoryAlbumLink(r127.longValue(), r70, new LaunchActivity$$ExternalSyntheticLambda104(this, r67, r127, r70));
        return;
    L23:
        if (r127 != null) goto L25;
    L300:
        BaseFragment r0 = getLastFragment();     // Catch: Exception -> L279
        if (r0 != null) goto L277;
    L298:
        r67.run();     // Catch: Exception -> L289
        return;
    L289:
        e = move-exception;
        FileLog.e(e);
        return;
    L277:
        if ((r0 instanceof ChatActivity) == false) goto L282;
        ((ChatActivity) r0).shakeContent();     // Catch: Exception -> L279
    L282:
        if (AndroidUtilities.isNumeric(r77) == false) goto L284;
        BulletinFactory.of(r0).createErrorBulletin(LocaleController.getString(R.string.NoPhoneFound)).show();     // Catch: Exception -> L279
        goto L298
    L284:
        BulletinFactory.of(r0).createErrorBulletin(LocaleController.getString(R.string.NoUsernameFound)).show();     // Catch: Exception -> L279
    L279:
        e = move-exception;
        FileLog.e(e);
        goto L298
    L25:
        if (this.actionBarLayout == null) goto L300;
        if (r71 != null) goto L29;
        if (r72 != null) goto L29;
    L42:
        if (TextUtils.isEmpty(r75) == true) goto L56;
        TLRPC.User r8 = MessagesController.getInstance(r76).getUser(r127);
        if (r8 == null) goto L56;
        if (r8.bot == false) goto L56;
        if (r8.bot_attach_menu == true) goto L50;
    L53:
        processWebAppBot(r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r71, r94, r95, r96, r97, r98, r99, r100, r101, r102, r72, r73, r74, r103, r104, r105, r106, r107, r75, r124, r108, r109, r69, false, r70, r110, r113, r114, r8, r67, false, false, r115, r116, r117, r118, r119, r120, r122);
        return;
    L50:
        if (MediaDataController.getInstance(r76).botInAttachMenu(r8.id) == true) goto L53;
        TLRPC.TL_messages_getAttachMenuBot r5 = new TLRPC.TL_messages_getAttachMenuBot();
        r5.bot = MessagesController.getInstance(r76).getInputUser(r127.longValue());
        ConnectionsManager.getInstance(r76).sendRequest(r5, new LaunchActivity$$ExternalSyntheticLambda105(this, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r71, r94, r95, r96, r97, r98, r99, r100, r101, r102, r72, r73, r74, r103, r104, r105, r106, r107, r108, r109, r69, r68, r70, r110, r111, r112, r113, r114, r115, r116, r117, r118, r119, r120, r121, r122, r123, r127, r75, r124, r8, r67));
        return;
    L56:
        if (r113 == false) goto L62;
        if (ChatObject.isBoostSupported(MessagesController.getInstance(r76).getChat(Long.valueOf(-r127.longValue()))) == false) goto L62;
        processBoostDialog(r127, r67, r108);
        return;
    L62:
        if (r124 == null) goto L69;
        TLRPC.User r02 = MessagesController.getInstance(r76).getUser(r127);
        if (r02 == null) goto L298;
        if (r02.bot == false) goto L298;
        MessagesController.getInstance(r76).openApp(null, r02, r124, 0, r108, r115, r116);
        goto L298
    L69:
        if (r105 == null) goto L81;
        if (r106 != null) goto L81;
        TLRPC.User r03 = MessagesController.getInstance(r76).getUser(r127);
        if (r03 != null) goto L74;
    L79:
        ArrayList<BaseFragment> r04 = this.mainFragmentsStack;
        BulletinFactory.of(r04.get(r04.size() - 1)).createErrorBulletin(LocaleController.getString(R.string.BotSetAttachLinkNotBot)).show();
        goto L298
    L74:
        if (r03.bot == false) goto L79;
        if (r03.bot_attach_menu == false) goto L78;
        processAttachMenuBot(r76, r127.longValue(), r107, r03, r105, r124);
        goto L298
    L78:
        ArrayList<BaseFragment> r05 = this.mainFragmentsStack;
        BulletinFactory.of(r05.get(r05.size() - 1)).createErrorBulletin(LocaleController.getString(R.string.BotCantAddToAttachMenu)).show();
    L81:
        if (r90 == null) goto L90;
        if (r93 != null) goto L85;
        if (r92 == null) goto L90;
    L85:
        if (r127.longValue() >= 0) goto L90;
        int r06 = runCommentRequest(r76, r67, r90, r93, r92, null, MessagesController.getInstance(r76).getChat(Long.valueOf(-r127.longValue())), r126);
        r125[0] = r06;
        if (r06 == 0) goto L298;
        return;
    L90:
        if (r71 == null) goto L126;
        Bundle r52 = new Bundle();
        r52.putBoolean("onlySelect", true);
        r52.putBoolean("cantSendToChannels", true);
        r52.putInt("dialogsType", 1);
        r52.putString("selectAlertString", LocaleController.getString(R.string.SendGameToText));
        r52.putString("selectAlertStringGroup", LocaleController.getString(R.string.SendGameToGroupText));
        DialogsActivity r4 = new DialogsActivity(r52);
        r4.setDelegate(new LaunchActivity$$ExternalSyntheticLambda106(this, r71, r76, MessagesController.getInstance(r76).getUser(r127)));
        if (AndroidUtilities.isTablet() == false) goto L100;
        if (this.layersActionBarLayout.getFragmentStack().isEmpty() == false) goto L96;
    L98:
        boolean r07 = false;
    L104:
        getActionBarLayout().presentFragment(r4, r07, true, true, false);
        if (SecretMediaViewer.hasInstance() == false) goto L110;
        if (SecretMediaViewer.getInstance().isVisible() == false) goto L110;
        SecretMediaViewer.getInstance().closePhoto(false, false);
    L119:
        StoryRecorder.destroyInstance();
        GroupCallActivity r08 = GroupCallActivity.groupCallInstance;
        if (r08 == null) goto L123;
        r08.dismiss();
    L123:
        if (AndroidUtilities.isTablet() == false) goto L298;
        this.actionBarLayout.rebuildFragments(1);
        this.rightActionBarLayout.rebuildFragments(1);
    L110:
        if (PhotoViewer.hasInstance() == false) goto L115;
        if (PhotoViewer.getInstance().isVisible() == false) goto L115;
        PhotoViewer.getInstance().closePhoto(false, true);
    L115:
        if (ArticleViewer.hasInstance() == false) goto L119;
        if (ArticleViewer.getInstance().isVisible() == false) goto L119;
        ArticleViewer.getInstance().close(false, true);
        goto L119
    L96:
        if ((this.layersActionBarLayout.getFragmentStack().get(this.layersActionBarLayout.getFragmentStack().size() - 1) instanceof MainTabsActivity) == false) goto L98;
    L97:
        r07 = true;
        goto L104
    L100:
        if (this.actionBarLayout.getFragmentStack().size() <= 1) goto L98;
        if ((this.actionBarLayout.getFragmentStack().get(this.actionBarLayout.getFragmentStack().size() - 1) instanceof MainTabsActivity) == false) goto L98;
    L126:
        if (r82 != null) goto L245;
        if (r83 != null) goto L245;
        Bundle r42 = new Bundle();
        TLRPC.User r9 = MessagesController.getInstance(r76).getUser(r127);
        if (r127.longValue() >= 0) goto L141;
        TLRPC.Chat r11 = MessagesController.getInstance(r76).getChat(Long.valueOf(-r127.longValue()));
        if (r122 == true) goto L134;
    L140:
        Runnable r53 = r67;
        String r09 = r72;
        int r10 = r104;
        String r1110 = r105;
        String r14 = r106;
        long r16 = 0;
        TLRPC.User r1262 = r9;
        String r6 = r74;
        String r7 = r81;
        r42.putLong("chat_id", -r127.longValue());
        long r810 = r127.longValue();
    L148:
        if (r7 == null) goto L153;
        if (r1262 == null) goto L153;
        if (r1262.bot == false) goto L153;
        r42.putString("botUser", r7);
        boolean r682 = true;
    L155:
        if (this.navigateToPremiumBot == false) goto L157;
        this.navigateToPremiumBot = false;
        r42.putBoolean("premium_bot", true);
    L157:
        if (r90 == null) goto L166;
        r42.putInt("message_id", r90.intValue());
        if (r121 == null) goto L162;
        r42.putInt("task_id", r121.intValue());
    L162:
        if (r123 == null) goto L166;
        r42.putByteArray("poll_option_id", r123);
    L166:
        if (r09 == null) goto L169;
        r42.putString("voicechat", r09);
    L169:
        if (r73 == false) goto L171;
        r42.putBoolean("videochat", true);
    L171:
        if (r6 == null) goto L173;
        r42.putString("livestream", r6);
    L173:
        if (r10 < 0) goto L175;
        r42.putInt("video_timestamp", r10);
    L175:
        if (r14 == null) goto L177;
        r42.putString("attach_bot", r14);
    L177:
        if (r1110 == null) goto L180;
        r42.putString("attach_bot_start_command", r1110);
    L180:
        if (this.mainFragmentsStack.isEmpty() == true) goto L183;
        if (r09 != null) goto L183;
        BaseFragment r1010 = this.mainFragmentsStack.get(r10.size() - 1);
    L184:
        if (r1010 == null) goto L188;
        if (MessagesController.getInstance(r76).checkCanOpenChat(r42, r1010) == false) goto L298;
    L188:
        if ((r1010 instanceof ChatActivity) == true) goto L190;
    L192:
        boolean r1111 = false;
    L193:
        if (r682 == false) goto L196;
        if (r1111 == false) goto L196;
        ((ChatActivity) r1010).setBotUser(r7);
    L196:
        if (r14 == null) goto L199;
        if (r1111 == false) goto L199;
        ((ChatActivity) r1010).openAttachBotLayout(r14);
    L199:
        long r142 = -r810;
        TLRPC.Chat r710 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r142));
        if (r118 == true) goto L301;
        if (r110 > 0) goto L301;
        if (r710 != null) goto L205;
    L224:
        MessagesController r010 = MessagesController.getInstance(r76);
        if (r90 != null) goto L227;
        int r2 = 0;
    L228:
        r010.ensureMessagesLoaded(r810, r2, new AnonymousClass14(this, r53, r6, r1010, r810, r90, r42));
        return;
    L227:
        r2 = r90.intValue();
        goto L228
    L205:
        if (r710.forum == false) goto L224;
        if (r92 != null) goto L209;
        if (r90 == null) goto L209;
        Long r43 = Long.valueOf(r90.intValue());
    L210:
        if (r43 != null) goto L212;
    L214:
        Bundle r44 = new Bundle();
        r44.putLong("chat_id", r142);
        if (r09 == null) goto L217;
        r44.putString("voicechat", r09);
    L217:
        if (r73 == false) goto L219;
        r44.putBoolean("videochat", true);
    L219:
        presentFragment(TopicsFragment.getTopicsOrChat(this, r44));
        r53.run();     // Catch: Exception -> L222
        return;
    L222:
        e = move-exception;
        FileLog.e(e);
        return;
    L212:
        if (r43.longValue() == r16) goto L214;
        openForumFromLink(r810, r90, null, r121, r123, new LaunchActivity$$ExternalSyntheticLambda109(r53), 0, -1);
        return;
    L209:
        r43 = r92;
    L301:
        r67.run();     // Catch: Exception -> L231
    L234:
        if (isFinishing() == true) goto L314;
        Bundle r011 = new Bundle();
        if (r127.longValue() >= r16) goto L239;
        r011.putLong("chat_id", -r127.longValue());
    L240:
        if (r110 <= 0) goto L244;
        r011.putBoolean("open_gifts", true);
        r011.putInt("open_gifts_collection", r110);
        if (r127.longValue() != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L244;
        r011.putBoolean("my_profile", true);
    L244:
        getActionBarLayout().presentFragment(new ProfileActivity(r011));
        return;
    L239:
        r011.putLong("user_id", r127.longValue());
        goto L240
    L314:
        return;
    L231:
        e = move-exception;
        FileLog.e(e);
        goto L234
    L190:
        if (((ChatActivity) r1010).getDialogId() != r810) goto L192;
        r1111 = true;
    L183:
        r1010 = null;
    L153:
        r682 = false;
        goto L155
    L134:
        if (r11.linked_monoforum_id == 0) goto L140;
        r16 = 0;
        if (MessagesController.getInstance(r76).getChat(Long.valueOf(r11.linked_monoforum_id)) != null) goto L139;
        TLRPC.TL_channels_getFullChannel r22 = new TLRPC.TL_channels_getFullChannel();
        r22.channel = MessagesController.getInputChannel(r11);
        ConnectionsManager.getInstance(r76).sendRequest(r22, new LaunchActivity$$ExternalSyntheticLambda108(this, r76, r11, r67, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r71, r94, r95, r96, r97, r98, r99, r100, r101, r102, r72, r73, r74, r103, r104, r105, r106, r107, r75, r124, r108, r109, r69, r68, r70, r110, r111, r112, r113, r114, r115, r116, r117, r118, r119, r120, r121, r122, r123));
        return;
    L139:
        r53 = r67;
        r09 = r72;
        r6 = r74;
        r7 = r81;
        r10 = r104;
        r14 = r106;
        r1262 = r9;
        r1110 = r105;
        r42.putLong("chat_id", r11.linked_monoforum_id);
        r810 = -r11.linked_monoforum_id;
        goto L148
    L141:
        r53 = r67;
        r09 = r72;
        String r23 = r88;
        r10 = r104;
        r1110 = r105;
        r14 = r106;
        r16 = 0;
        r1262 = r9;
        r6 = r74;
        r7 = r81;
        r42.putLong("user_id", r127.longValue());
        long r811 = r127.longValue();
        if (r23 != null) goto L144;
    L147:
        r810 = r811;
        goto L148
    L144:
        if (r23.startsWith("@") == false) goto L146;
        r23 = " ".concat(r23);
    L146:
        r42.putString("start_text", r23);
    L245:
        TLRPC.User r012 = MessagesController.getInstance(r76).getUser(r127);
        if (r012 != null) goto L248;
    L292:
    L271:
        e = move-exception;
        FileLog.e(e);
        return;
    L268:
        if (this.mainFragmentsStack.isEmpty() == true) goto L311;
        BulletinFactory.of(this.mainFragmentsStack.get(r0.size() - 1)).createErrorBulletin(LocaleController.getString(R.string.BotCantJoinGroups)).show();     // Catch: Exception -> L271
        return;
    L311:
        return;
    L248:
        if (r012.bot == true) goto L250;
    L252:
        Bundle r24 = new Bundle();
        r24.putBoolean("onlySelect", true);
        r24.putInt("dialogsType", 2);
        boolean r45 = false;
        r24.putBoolean("resetDelegate", false);
        r24.putBoolean("closeFragment", false);
        if (r82 == null) goto L255;
        boolean r3 = true;
    L256:
        r24.putBoolean("allowGroups", r3);
        if (r83 == null) goto L259;
        r45 = true;
    L259:
        r24.putBoolean("allowChannels", r45);
        if (TextUtils.isEmpty(r82) == true) goto L262;
        String r32 = r82;
    L266:
        DialogsActivity r46 = new DialogsActivity(r24);
        r46.setDelegate(new LaunchActivity$$ExternalSyntheticLambda107(this, r76, r012, r84, r32, r46));
        presentFragment(r46);
        goto L298
    L262:
        if (TextUtils.isEmpty(r83) == false) goto L264;
        r32 = null;
        goto L266
    L264:
        r32 = r83;
        goto L266
    L255:
        r3 = false;
        goto L256
    L250:
        if (r012.bot_nochats == false) goto L252;
    L29:
        if (r71 != null) goto L31;
    L32:
        if (r72 != null) goto L34;
    L35:
        if (r73 == true) goto L37;
    L38:
        if (r74 == null) goto L300;
        if (r127.longValue() >= 0) goto L300;
    L37:
        if (r127.longValue() < 0) goto L42;
    L34:
        if (r127.longValue() > 0) goto L42;
    L31:
        if (r127.longValue() > 0) goto L42;
    }

    private /* synthetic */ void lambda$runLinkRequest$86(Long r5, long r6) {
        BaseFragment r0 = getSafeLastFragment();
        if (r0 == null) goto L10;
        ChatActivity r1 = ChatActivity.of(r5.longValue());
        r0.presentFragment(r1);
        TLRPC.Chat r4 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r5.longValue()));
        if (r4 == null) goto L9;
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda169(r1, r6, r4), 250);
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$runLinkRequest$87(long r5, String r7, Long r8) {
        if ("paid".equals(r7) == true) goto L5;
        return;
    L5:
        if (r8.longValue() == 0) goto L9;
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda159(this, r8, r5));
        return;
    }

    private /* synthetic */ void lambda$runLinkRequest$88(TLRPC.TL_error r7, TLObject r8, int r9, AlertDialog r10, Runnable r11, String r12) {
        if (isFinishing() == true) goto L56;
        ChatActivity.ThemeDelegate r0 = null;
        if (r7 == null) goto L7;
    L41:
        Runnable r4 = r11;
        AlertDialog.Builder r82 = new AlertDialog.Builder(this);
        r82.setTitle(LocaleController.getString(R.string.AppName));
        if (r7.text.startsWith("FLOOD_WAIT") == false) goto L45;
        r82.setMessage(LocaleController.getString(R.string.FloodWait));
    L48:
        r82.setPositiveButton(LocaleController.getString(R.string.OK), null);
        showAlertDialog(r82);
    L54:
        r4.run();     // Catch: Exception -> L51
        return;
    L51:
        e = move-exception;
        FileLog.e(e);
        return;
    L45:
        if (r7.text.startsWith("INVITE_HASH_EXPIRED") == false) goto L47;
        r82.setTitle(LocaleController.getString(R.string.ExpiredLink));
        r82.setMessage(LocaleController.getString(R.string.InviteExpired));
        goto L48
    L47:
        r82.setMessage(LocaleController.getString(R.string.JoinToGroupErrorNotExist));
        goto L48
    L7:
        if (this.actionBarLayout == null) goto L41;
        TLRPC.ChatInvite r83 = (TLRPC.ChatInvite) r8;
        TLRPC.Chat r72 = r83.chat;
        if (r72 != null) goto L11;
    L21:
        r4 = r11;
        TL_stars.TL_starsSubscriptionPricing r6 = r83.subscription_pricing;
        if (r6 != null) goto L35;
    L37:
        ArrayList<BaseFragment> r62 = this.mainFragmentsStack;
        BaseFragment r102 = r62.get(r62.size() - 1);
        if ((r102 instanceof ChatActivity) == false) goto L40;
        r0 = ((ChatActivity) r102).themeDelegate;
    L40:
        r102.showDialog(new JoinGroupAlert(this, r83, r12, r102, r0));
        goto L54
    L35:
        if (r83.can_refulfill_subscription == true) goto L37;
        long r92 = r6.amount;
        MessagesController.getInstance(r9).putChat(r83.chat, false);
        StarsController.getInstance(this.currentAccount).subscribeTo(r12, r83, new LaunchActivity$$ExternalSyntheticLambda146(this, r92));
        goto L54
    L11:
        if (ChatObject.isLeftFromChat(r72) == false) goto L22;
        TLRPC.Chat r73 = r83.chat;
        if (r73.kicked == true) goto L21;
        if (ChatObject.isPublic(r73) == true) goto L22;
        if ((r83 instanceof TLRPC.TL_chatInvitePeek) == true) goto L22;
        if (r83.chat.has_geo == false) goto L21;
    L22:
        MessagesController.getInstance(r9).putChat(r83.chat, false);
        ArrayList r74 = new ArrayList();
        r74.add(r83.chat);
        MessagesStorage.getInstance(r9).putUsersAndChats(null, r74, false, true);
        Bundle r103 = new Bundle();
        r103.putLong("chat_id", r83.chat.id);
        if (this.mainFragmentsStack.isEmpty() == true) goto L26;
        MessagesController r02 = MessagesController.getInstance(r9);
        ArrayList<BaseFragment> r3 = this.mainFragmentsStack;
        if (r02.checkCanOpenChat(r103, r3.get(r3.size() - 1)) == true) goto L26;
    L29:
        r4 = r11;
    L26:
        boolean[] r93 = new boolean[1];
        r10.setOnCancelListener(new LaunchActivity$$ExternalSyntheticLambda145(r93));
        if (r83.chat.forum == false) goto L30;
        Bundle r75 = new Bundle();
        r75.putLong("chat_id", r83.chat.id);
        presentFragment(TopicsFragment.getTopicsOrChat(this, r75));
        goto L29
    L30:
        MessagesController.getInstance(r9).ensureMessagesLoaded(-r83.chat.id, 0, new AnonymousClass15(this, r11, r93, r103, r83));
        return;
    }

    private /* synthetic */ void lambda$runLinkRequest$89(int r9, AlertDialog r10, Runnable r11, String r12, TLObject r13, TLRPC.TL_error r14) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda120(this, r14, r13, r9, r10, r11, r12));
    }

    private /* synthetic */ void lambda$runLinkRequest$90(TLRPC.TL_chatInviteJoinResultWebView r8) {
        MessagesController.getInstance(this.currentAccount).putUsers(r8.users, false);
        BotGuardHelper r0 = BotGuardHelper.getInstance(this.currentAccount);
        long r1 = r8.bot_id;
        r0.openGuardBotWebApp(r1, r1, r8.query_id);
    }

    private /* synthetic */ void lambda$runLinkRequest$91(Runnable r7, TLRPC.TL_error r8, TLRPC.Updates r9, int r10) {
        if (isFinishing() == true) goto L30;
        r7.run();     // Catch: Exception -> L6
    L8:
        if (r8 == null) goto L10;
        AlertDialog.Builder r72 = new AlertDialog.Builder(this);
        r72.setTitle(LocaleController.getString(R.string.AppName));
        if (r8.text.startsWith("FLOOD_WAIT") == false) goto L23;
        r72.setMessage(LocaleController.getString(R.string.FloodWait));
    L26:
        r72.setPositiveButton(LocaleController.getString(R.string.OK), null);
        showAlertDialog(r72);
        return;
    L23:
        if (r8.text.equals("USERS_TOO_MUCH") == false) goto L25;
        r72.setMessage(LocaleController.getString(R.string.JoinToGroupErrorFull));
        goto L26
    L25:
        r72.setMessage(LocaleController.getString(R.string.JoinToGroupErrorNotExist));
        goto L26
    L10:
        if (this.actionBarLayout == null) goto L31;
        if (r9 != null) goto L13;
        return;
    L13:
        if (r9.chats.isEmpty() == true) goto L33;
        TLRPC.Chat r73 = r9.chats.get(0);
        r73.left = false;
        r73.kicked = false;
        MessagesController.getInstance(r10).putUsers(r9.users, false);
        MessagesController.getInstance(r10).putChats(r9.chats, false);
        Bundle r92 = new Bundle();
        r92.putLong("chat_id", r73.id);
        if (this.mainFragmentsStack.isEmpty() == false) goto L17;
    L18:
        ChatActivity r1 = new ChatActivity(r92);
        NotificationCenter.getInstance(r10).postNotificationName(NotificationCenter.closeChats, new Object[0]);
        getActionBarLayout().presentFragment(r1, false, true, true, false);
        return;
    L17:
        if (MessagesController.getInstance(r10).checkCanOpenChat(r92, this.mainFragmentsStack.get(r0.size() - 1)) == true) goto L18;
        return;
    L33:
        return;
    L31:
        return;
    L6:
        e = move-exception;
        FileLog.e(e);
        goto L8
    }

    private /* synthetic */ void lambda$runLinkRequest$92(Runnable r9, int r10, TLRPC.ChatInviteJoinResult r11, TLRPC.TL_error r12) {
        if ((r11 instanceof TLRPC.TL_chatInviteJoinResultOk) == false) goto L6;
        TLRPC.Updates r112 = ((TLRPC.TL_chatInviteJoinResultOk) r11).updates;
        MessagesController.getInstance(this.currentAccount).processUpdates(r112, false);
        TLRPC.Updates r6 = r112;
    L9:
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda130(this, r9, r12, r6, r10));
        return;
    L6:
        if ((r11 instanceof TLRPC.TL_chatInviteJoinResultWebView) == false) goto L8;
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda129(this, (TLRPC.TL_chatInviteJoinResultWebView) r11));
    L8:
        r6 = null;
        goto L9
    }

    private /* synthetic */ boolean lambda$runLinkRequest$93(boolean r14, int r15, String r16, DialogsActivity r17, ArrayList r18, CharSequence r19, boolean r20, boolean r21, int r22, int r23, TopicsFragment r24) {
        long r3 = ((MessagesStorage.TopicKey) r18.get(0)).dialogId;
        Bundle r1 = new Bundle();
        r1.putBoolean("scrollToTopOnResume", true);
        r1.putBoolean("hasUrl", r14);
        if (DialogObject.isEncryptedDialog(r3) == false) goto L6;
        r1.putInt("enc_id", DialogObject.getEncryptedChatId(r3));
    L10:
        if (MessagesController.getInstance(r15).checkCanOpenChat(r1, r17) == false) goto L12;
        NotificationCenter.getInstance(r15).postNotificationName(NotificationCenter.closeChats, new Object[0]);
        MediaDataController.getInstance(r15).saveDraft(r3, 0, r16, null, null, false, 0);
        getActionBarLayout().presentFragment(new ChatActivity(r1), true, false, true, false);
    L12:
        return true;
    L6:
        if (DialogObject.isUserDialog(r3) == false) goto L8;
        r1.putLong("user_id", r3);
        goto L10
    L8:
        r1.putLong("chat_id", -r3);
        goto L10
    }

    private /* synthetic */ void lambda$runLinkRequest$94(Runnable r12, TLObject r13, int r14, TL_account.authorizationForm r15, TL_account.getAuthorizationForm r16, String r17, String r18, String r19) {
        r12.run();     // Catch: Exception -> L5
    L7:
        if (r13 == null) goto L12;
        TL_account.Password r10 = (TL_account.Password) r13;
        MessagesController.getInstance(r14).putUsers(r15.users, false);
        presentFragment(new PassportActivity(5, r16.bot_id, r16.scope, r16.public_key, r17, r18, r19, r15, r10));
        return;
    L12:
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
        goto L7
    }

    private /* synthetic */ void lambda$runLinkRequest$95(Runnable r1, int r2, TL_account.authorizationForm r3, TL_account.getAuthorizationForm r4, String r5, String r6, String r7, TLObject r8, TLRPC.TL_error r9) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda136(this, r1, r8, r2, r3, r4, r5, r6, r7));
    }

    private /* synthetic */ void lambda$runLinkRequest$96(Runnable r2, TLRPC.TL_error r3) {
        r2.run();     // Catch: Exception -> L8
        if ("APP_VERSION_OUTDATED".equals(r3.text) == false) goto L6;
        AlertsCreator.showUpdateAppAlert(this, LocaleController.getString(R.string.UpdateAppAlert), true);     // Catch: Exception -> L8
        return;
    L6:
        showAlertDialog(AlertsCreator.createSimpleAlert(this, LocaleController.getString(R.string.ErrorOccurred) + "\n" + r3.text));     // Catch: Exception -> L8
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$runLinkRequest$97(int[] r12, int r13, Runnable r14, TL_account.getAuthorizationForm r15, String r16, String r17, String r18, TLObject r19, TLRPC.TL_error r20) {
        TL_account.authorizationForm r4 = (TL_account.authorizationForm) r19;
        if (r4 == null) goto L6;
        TL_account.getPassword r9 = new TL_account.getPassword();
        r12[0] = ConnectionsManager.getInstance(r13).sendRequest(r9, new LaunchActivity$$ExternalSyntheticLambda95(this, r14, r13, r4, r15, r16, r17, r18));
        return;
    L6:
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda96(this, r14, r20));
    }

    private /* synthetic */ void lambda$runLinkRequest$98(Runnable r1, TLObject r2) {
        r1.run();     // Catch: Exception -> L4
    L7:
        if ((r2 instanceof TLRPC.TL_help_deepLinkInfo) == false) goto L12;
        TLRPC.TL_help_deepLinkInfo r22 = (TLRPC.TL_help_deepLinkInfo) r2;
        AlertsCreator.showUpdateAppAlert(this, r22.message, r22.update_app);
        return;
    L12:
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L7
    }

    private /* synthetic */ void lambda$runLinkRequest$99(Runnable r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda127(this, r1, r2));
    }

    private /* synthetic */ boolean lambda$setupActionBarLayout$3(View r6, MotionEvent r7) {
        if (this.actionBarLayout.getFragmentStack().isEmpty() == false) goto L5;
    L24:
        return false;
    L5:
        if (r7.getAction() != 1) goto L24;
        float r62 = r7.getX();
        float r72 = r7.getY();
        int[] r2 = new int[2];
        this.layersActionBarLayout.getView().getLocationOnScreen(r2);
        int r3 = r2[0];
        int r22 = r2[1];
        if (this.layersActionBarLayout.checkTransitionAnimation() == true) goto L24;
        if (r62 <= r3) goto L18;
        if (r62 >= (r3 + this.layersActionBarLayout.getView().getWidth())) goto L18;
        if (r72 <= r22) goto L18;
        if (r72 < (r22 + this.layersActionBarLayout.getView().getHeight())) goto L24;
    L18:
        if (this.layersActionBarLayout.getFragmentStack().isEmpty() == true) goto L23;
    L19:
        int r63 = this.layersActionBarLayout.getFragmentStack().size() - 1;
        ActionBarLayout r73 = this.layersActionBarLayout;
        if (r63 <= 0) goto L22;
        r73.removeFragmentFromStack(r73.getFragmentStack().get(0));
        goto L19
    L22:
        r73.closeLastFragment(true);
    L23:
        return true;
    }

    private /* synthetic */ void lambda$showAlertDialog$145(AlertDialog r7, DialogInterface r8) {
        if (r7 != null) goto L4;
    L30:
        this.visibleDialogs.remove(r7);
        return;
    L4:
        if (r7 != this.localeDialog) goto L28;
        ActionBarLayout r82 = this.actionBarLayout;
        if (r82 != null) goto L8;
        BaseFragment r83 = null;
    L32:
        String r1 = LocaleController.getInstance().getCurrentLocaleInfo().shortName;     // Catch: Exception -> L15
        if (r83 == null) goto L19;
        BulletinFactory r84 = BulletinFactory.of(r83);     // Catch: Exception -> L15
        int r5 = R.raw.msg_translate;     // Catch: Exception -> L15
        if (r1.equals("en") == false) goto L17;
        HashMap<String, String> r12 = this.englishLocaleStrings;     // Catch: Exception -> L15
    L18:
        r84.createSimpleBulletin(r5, getStringForLanguageAlert(r12, "ChangeLanguageLater", R.string.ChangeLanguageLater)).setDuration(5000).show();     // Catch: Exception -> L15
    L26:
        this.localeDialog = null;
        goto L30
    L17:
        r12 = this.systemLocaleStrings;     // Catch: Exception -> L15
        goto L18
    L19:
        BulletinFactory r85 = BulletinFactory.of(Bulletin.BulletinWindow.make(this), null);     // Catch: Exception -> L15
        int r52 = R.raw.msg_translate;     // Catch: Exception -> L15
        if (r1.equals("en") == false) goto L22;
        HashMap<String, String> r13 = this.englishLocaleStrings;     // Catch: Exception -> L15
    L23:
        r85.createSimpleBulletin(r52, getStringForLanguageAlert(r13, "ChangeLanguageLater", R.string.ChangeLanguageLater)).setDuration(5000).show();     // Catch: Exception -> L15
        goto L26
    L22:
        r13 = this.systemLocaleStrings;     // Catch: Exception -> L15
    L15:
        e = move-exception;
        FileLog.e(e);
        goto L26
    L8:
        r83 = r82.getLastFragment();
        goto L32
    L28:
        if (r7 != this.proxyErrorDialog) goto L30;
        SharedPreferences.Editor r86 = MessagesController.getGlobalMainSettings().edit();
        r86.putBoolean("proxy_enabled", false);
        r86.commit();
        ConnectionsManager.setProxySettings(false, null);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.proxySettingsChanged, new Object[0]);
        this.proxyErrorDialog = null;
        goto L30
    }

    private /* synthetic */ void lambda$showLanguageAlert$178(HashMap r2, LocaleController.LocaleInfo[] r3, String r4) {
        this.systemLocaleStrings = r2;
        if (this.englishLocaleStrings == null) goto L6;
        showLanguageAlertInternal(r3[1], r3[0], r4);
        return;
    }

    private /* synthetic */ void lambda$showLanguageAlert$179(LocaleController.LocaleInfo[] r4, String r5, TLObject r6, TLRPC.TL_error r7) {
        HashMap r72 = new HashMap();
        if ((r6 instanceof Vector) == false) goto L8;
        Vector r62 = (Vector) r6;
        int r0 = 0;
    L6:
        if (r0 >= r62.objects.size()) goto L8;
        TLRPC.LangPackString r1 = (TLRPC.LangPackString) r62.objects.get(r0);
        r72.put(r1.key, r1.value);
        r0 = r0 + 1;
    L8:
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda115(this, r72, r4, r5));
    }

    private /* synthetic */ void lambda$showLanguageAlert$180(HashMap r2, LocaleController.LocaleInfo[] r3, String r4) {
        this.englishLocaleStrings = r2;
        if (this.systemLocaleStrings == null) goto L6;
        showLanguageAlertInternal(r3[1], r3[0], r4);
        return;
    }

    private /* synthetic */ void lambda$showLanguageAlert$181(LocaleController.LocaleInfo[] r4, String r5, TLObject r6, TLRPC.TL_error r7) {
        HashMap r72 = new HashMap();
        if ((r6 instanceof Vector) == false) goto L8;
        Vector r62 = (Vector) r6;
        int r0 = 0;
    L6:
        if (r0 >= r62.objects.size()) goto L8;
        TLRPC.LangPackString r1 = (TLRPC.LangPackString) r62.objects.get(r0);
        r72.put(r1.key, r1.value);
        r0 = r0 + 1;
    L8:
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda101(this, r72, r4, r5));
    }

    private /* synthetic */ void lambda$showLanguageAlertInternal$176(View r3) {
        this.localeDialog = null;
        presentFragment(new LanguageSelectActivity());
        int r32 = 0;
    L3:
        int r0 = this.visibleDialogs.size();
        ArrayList<Dialog> r1 = this.visibleDialogs;
        if (r32 >= r0) goto L9;
        if (r1.get(r32).isShowing() == false) goto L8;
        this.visibleDialogs.get(r32).dismiss();
    L8:
        r32 = r32 + 1;
        goto L3
    L9:
        r1.clear();
    }

    private /* synthetic */ void lambda$showLanguageAlertInternal$177(LocaleController.LocaleInfo[] r3, AlertDialog r4, int r5) {
        LocaleController.getInstance().applyLanguage(r3[0], true, false, this.currentAccount);
        rebuildAllFragments(true);
    }

    private /* synthetic */ void lambda$showPasscodeActivity$7(Runnable r3) {
        this.actionBarLayout.getView().setVisibility(4);
        if (AndroidUtilities.isTablet() == false) goto L16;
        ActionBarLayout r0 = this.layersActionBarLayout;
        if (r0 != null) goto L7;
    L11:
        ActionBarLayout r02 = this.rightActionBarLayout;
        if (r02 == null) goto L16;
        if (r02.getView() == null) goto L16;
        this.rightActionBarLayout.getView().setVisibility(4);
        goto L16
    L7:
        if (r0.getView() == null) goto L11;
        if (this.layersActionBarLayout.getView().getVisibility() != 0) goto L11;
        this.layersActionBarLayout.getView().setVisibility(4);
    L16:
        if (r3 == null) goto L19;
        r3.run();
        return;
    }

    private /* synthetic */ void lambda$showPasscodeActivity$8(PasscodeView r10) {
        SharedConfig.isWaitingForPasscodeEnter = false;
        Intent r2 = this.passcodeSaveIntent;
        if (r2 == null) goto L5;
        LaunchActivity r1 = this;
        r1.handleIntent(r2, this.passcodeSaveIntentIsNew, this.passcodeSaveIntentIsRestore, true, null, false, true);
        r1.passcodeSaveIntent = null;
    L6:
        r1.actionBarLayout.getView().setVisibility(0);
        r1.actionBarLayout.rebuildFragments(1);
        r1.actionBarLayout.updateTitleOverlay();
        if (AndroidUtilities.isTablet() == false) goto L20;
        ActionBarLayout r9 = r1.layersActionBarLayout;
        if (r9 == null) goto L15;
        r9.rebuildFragments(1);
        if (r1.layersActionBarLayout.getView() == null) goto L15;
        if (r1.layersActionBarLayout.getView().getVisibility() != 4) goto L15;
        r1.layersActionBarLayout.getView().setVisibility(0);
    L15:
        ActionBarLayout r92 = r1.rightActionBarLayout;
        if (r92 == null) goto L20;
        r92.rebuildFragments(1);
        if (r1.rightActionBarLayout.getView() == null) goto L20;
        r1.rightActionBarLayout.getView().setVisibility(0);
    L20:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.passcodeDismissed, new Object[]{r10});
        NotificationsController.getInstance(UserConfig.selectedAccount).showNotifications();     // Catch: Exception -> L23
        return;
    L23:
        e = move-exception;
        FileLog.e(e);
        return;
    L5:
        r1 = this;
        goto L6
    }

    private /* synthetic */ void lambda$updateCurrentConnectionState$182() {
        if (AndroidUtilities.isTablet() == false) goto L8;
        if (this.layerFragmentsStack.isEmpty() == true) goto L10;
        BaseFragment r0 = this.layerFragmentsStack.get(r0.size() - 1);
    L12:
        if ((r0 instanceof ProxyListActivity) == false) goto L14;
        return;
    L14:
        if ((r0 instanceof ProxySettingsActivity) == true) goto L19;
        presentFragment(new ProxyListActivity());
        return;
    L19:
        return;
    L10:
        r0 = null;
        goto L12
    L8:
        if (this.mainFragmentsStack.isEmpty() == true) goto L10;
        r0 = this.mainFragmentsStack.get(r0.size() - 1);
        goto L12
    }

    public static void makeRipple(float r1, float r2, float r3) {
        LaunchActivity r0 = instance;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.makeRippleInternal(r1, r2, r3);
    }

    private void makeRippleInternal(float r4, float r5, float r6) {
        View r0 = getWindow().getDecorView();
        if (r0 != null) goto L6;
        return;
    L6:
        if (Build.VERSION.SDK_INT < 33) goto L12;
        ISuperRipple r1 = this.currentRipple;
        if (r1 != null) goto L10;
    L11:
        this.currentRipple = new SuperRipple(r0);
        goto L12
    L10:
        if (r1.view != r0) goto L11;
    L12:
        ISuperRipple r3 = this.currentRipple;
        if (r3 == null) goto L16;
        r3.animate(r4, r5, r6);
        return;
    }

    public static WindowVisibilityManager.Controller obtainActivityVisibilityController() {
        LaunchActivity r0 = instance;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.createActivityVisibilityController(true);
    }

    private static void onDestroyStaticResources() {
        if (PhotoViewer.getPipInstance() == null) goto L6;
        PhotoViewer.getPipInstance().destroyPhotoViewer();
    L6:
        if (PhotoViewer.hasInstance() == false) goto L9;
        PhotoViewer.getInstance().destroyPhotoViewer();
    L9:
        if (SecretMediaViewer.hasInstance() == false) goto L12;
        SecretMediaViewer.getInstance().destroyPhotoViewer();
    L12:
        if (ArticleViewer.hasInstance() == false) goto L15;
        ArticleViewer.getInstance().destroyArticleViewer();
    L15:
        if (ContentPreviewViewer.hasInstance() == false) goto L17;
        ContentPreviewViewer.getInstance().destroy();
    L17:
        GroupCallActivity r0 = GroupCallActivity.groupCallInstance;
        if (r0 == null) goto L20;
        r0.dismissInternal();
    L20:
        PipRoundVideoView r02 = PipRoundVideoView.getInstance();
        if (r02 == null) goto L23;
        r02.close(false);
    L23:
        Theme.destroyResources();
        EmbedBottomSheet r03 = EmbedBottomSheet.getInstance();
        if (r03 == null) goto L26;
        r03.destroy();
    L26:
        ThemeEditorView r04 = ThemeEditorView.getInstance();
        if (r04 == null) goto L29;
        r04.destroy();
    L29:
        FloatingDebugController.onDestroy();
        IconPickerController.onDestroy();
        AnimatedEmojiDrawable.dropGlobalEmojiCache();
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L33;
        LeakDetector.getInstance().stop();
        return;
    }

    private void onFinish() {
        Runnable r0 = this.lockRunnable;
        if (r0 == null) goto L6;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.lockRunnable = null;
    L6:
        if (this.finished == true) goto L19;
        this.finished = true;
        NotificationCenter.ObserversGroup r02 = this.observersGroup;
        if (r02 == null) goto L11;
        r02.removeAllObservers();
        this.observersGroup = null;
    L11:
        NotificationCenter.ObserversGroup r03 = this.globalObserversGroup;
        if (r03 == null) goto L14;
        r03.removeAllObservers();
        this.globalObserversGroup = null;
    L14:
        Utilities.Callback<Boolean> r2 = this.onPowerSaverCallback;
        if (r2 == null) goto L18;
        LiteMode.removeOnPowerSaverAppliedListener(r2);
        return;
    L18:
        return;
    }

    private void onPasscodePause() {
        if (this.lockRunnable == null) goto L9;
        if (BuildVars.LOGS_ENABLED == false) goto L7;
        FileLog.d("cancel lockRunnable onPasscodePause");
    L7:
        AndroidUtilities.cancelRunOnUIThread(this.lockRunnable);
        this.lockRunnable = null;
    L9:
        if (SharedConfig.passcodeHash.length() == 0) goto L23;
        SharedConfig.lastPauseTime = (int) (SystemClock.elapsedRealtime() / 1000);
        AnonymousClass18 r0 = new AnonymousClass18(this);
        this.lockRunnable = r0;
        if (SharedConfig.appLocked == true) goto L20;
        int r1 = SharedConfig.autoLockIn;
        if (r1 == Integer.MAX_VALUE) goto L20;
        if (r1 != 0) goto L17;
    L24:
        SharedConfig.saveConfig();
        return;
    L17:
        if (BuildVars.LOGS_ENABLED == false) goto L19;
        FileLog.d("schedule app lock in " + ((SharedConfig.autoLockIn * 1000) + 1000));
    L19:
        AndroidUtilities.runOnUIThread(this.lockRunnable, (SharedConfig.autoLockIn * 1000) + 1000);
    L20:
        AndroidUtilities.runOnUIThread(r0, 1000);
        if (BuildVars.LOGS_ENABLED == false) goto L24;
        FileLog.d("schedule app lock in 1000");
        goto L24
    L23:
        SharedConfig.lastPauseTime = 0;
        goto L24
    }

    private void onPasscodeResume() {
        if (this.lockRunnable == null) goto L9;
        if (BuildVars.LOGS_ENABLED == false) goto L7;
        FileLog.d("cancel lockRunnable onPasscodeResume");
    L7:
        AndroidUtilities.cancelRunOnUIThread(this.lockRunnable);
        this.lockRunnable = null;
    L9:
        if (AndroidUtilities.needShowPasscode(true) == false) goto L12;
        showPasscodeActivity(true, false, -1, -1, null, null);
    L12:
        if (SharedConfig.lastPauseTime == 0) goto L17;
        SharedConfig.lastPauseTime = 0;
        SharedConfig.saveConfig();
        if (BuildVars.LOGS_ENABLED == false) goto L18;
        FileLog.d("reset lastPauseTime onPasscodeResume");
        return;
    L18:
        return;
    }

    private void onPowerSaver(boolean r8) {
        if (this.actionBarLayout == null) goto L15;
        if (r8 == true) goto L6;
        return;
    L6:
        if (LiteMode.getPowerSaverLevel() >= 100) goto L18;
        BaseFragment r82 = this.actionBarLayout.getLastFragment();
        if (r82 != null) goto L11;
        return;
    L11:
        if ((r82 instanceof LiteModeSettingsActivity) == true) goto L19;
        int r0 = LiteMode.getBatteryLevel();
        BulletinFactory.of(r82).createSimpleBulletin(new BatteryDrawable(r0 / 100.0f, -1, r82.getThemedColor(Theme.key_dialogSwipeRemove), 1.3f), LocaleController.getString(R.string.LowPowerEnabledTitle), LocaleController.formatString("LowPowerEnabledSubtitle", R.string.LowPowerEnabledSubtitle, new Object[]{String.format("%d%%", new Object[]{Integer.valueOf(r0)})}), LocaleController.getString(R.string.Disable), new LaunchActivity$$ExternalSyntheticLambda63(this)).setDuration(5000).show();
        return;
    L19:
        return;
    L18:
        return;
    }

    private void onThemeLoadFinish() {
        AlertDialog r0 = this.loadingThemeProgressDialog;
        if (r0 != null) goto L11;
    L9:
        this.loadingThemeWallpaperName = null;
        this.loadingThemeWallpaper = null;
        this.loadingThemeInfo = null;
        this.loadingThemeFileName = null;
        this.loadingTheme = null;
        return;
    L11:
        r0.dismiss();     // Catch: Throwable -> L6
        this.loadingThemeProgressDialog = null;
    L6:
        th = move-exception;
        this.loadingThemeProgressDialog = null;
        throw th;
    }

    private void openDialogsToSend(boolean r20) {
        Bundle r1 = new Bundle();
        r1.putBoolean("onlySelect", true);
        r1.putBoolean("canSelectTopics", true);
        r1.putInt("dialogsType", 3);
        r1.putBoolean("allowSwitchAccount", true);
        ArrayList<TLRPC.User> r2 = this.contactsToSend;
        if (r2 != null) goto L5;
        r1.putString("selectAlertString", LocaleController.getString(R.string.SendMessagesToText));
        r1.putString("selectAlertStringGroup", LocaleController.getString(R.string.SendMessagesToGroupText));
    L8:
        DialogsActivity r6 = new DialogsActivity(r1);
        r6.setDelegate(this);
        if (this.videoPath != null) goto L21;
        ArrayList<SendMessagesHelper.SendingMediaInfo> r12 = this.photoPathsArray;
        if (r12 == null) goto L16;
        if (r12.isEmpty() == false) goto L21;
    L16:
        if (TextUtils.isEmpty(this.sendingText) == true) goto L36;
        String r13 = ShareTopView.extractFirstUrl(this.sendingText);
        CharSequence r4 = this.sendingText;
        if (r13 == null) goto L20;
        r6.setSharedLink(r13, r4);
        goto L36
    L20:
        r6.setSharedText(r4, r4);
    L36:
        if (AndroidUtilities.isTablet() == false) goto L45;
        if (this.layersActionBarLayout.getFragmentStack().isEmpty() == false) goto L40;
    L42:
        boolean r14 = false;
    L49:
        getActionBarLayout().presentFragment(r6, r14, !r20, true, false);
        if (SecretMediaViewer.hasInstance() == false) goto L55;
        if (SecretMediaViewer.getInstance().isVisible() == false) goto L55;
        SecretMediaViewer.getInstance().closePhoto(false, false);
    L64:
        StoryRecorder.destroyInstance();
        GroupCallActivity r15 = GroupCallActivity.groupCallInstance;
        if (r15 == null) goto L67;
        r15.dismiss();
    L67:
        if (r20 == false) goto L69;
        return;
    L69:
        if (AndroidUtilities.isTablet() == false) goto L73;
        this.actionBarLayout.rebuildFragments(1);
        this.rightActionBarLayout.rebuildFragments(1);
        return;
    L73:
        return;
    L55:
        if (PhotoViewer.hasInstance() == false) goto L60;
        if (PhotoViewer.getInstance().isVisible() == false) goto L60;
        PhotoViewer.getInstance().closePhoto(false, true);
    L60:
        if (ArticleViewer.hasInstance() == false) goto L64;
        if (ArticleViewer.getInstance().isVisible() == false) goto L64;
        ArticleViewer.getInstance().close(false, true);
        goto L64
    L40:
        if ((this.layersActionBarLayout.getFragmentStack().get(this.layersActionBarLayout.getFragmentStack().size() - 1) instanceof MainTabsActivity) == false) goto L42;
    L41:
        r14 = true;
        goto L49
    L45:
        if (this.actionBarLayout.getFragmentStack().size() <= 1) goto L42;
        if ((this.actionBarLayout.getFragmentStack().get(this.actionBarLayout.getFragmentStack().size() - 1) instanceof MainTabsActivity) == false) goto L42;
    L21:
        ArrayList<MediaController.PhotoEntry> r16 = new ArrayList();
        ArrayList<SendMessagesHelper.SendingMediaInfo> r42 = this.photoPathsArray;
        if (r42 != null) goto L24;
    L26:
        String r122 = this.videoPath;
        if (r122 == null) goto L30;
        r16.add(new MediaController.PhotoEntry(0, 0, 0, r122, 0, true, 0, 0, 0));
    L30:
        if (r16.isEmpty() == true) goto L36;
        if (TextUtils.isEmpty(this.sendingText) == true) goto L34;
        r16.get(0).caption = this.sendingText;
    L34:
        r6.setSharedMedia(r16, this.sendingText);
        goto L36
    L24:
        if (r42.isEmpty() == true) goto L26;
        r16.addAll(ChatActivity.createEntriesFromMedia(this.photoPathsArray, false, null));
        goto L26
    L5:
        if (r2.size() == 1) goto L8;
        r1.putString("selectAlertString", LocaleController.getString(R.string.SendMessagesToText));
        r1.putString("selectAlertStringGroup", LocaleController.getString(R.string.SendContactToGroupText));
        goto L8
    }

    private void openForumFromLink(long r14, Integer r16, String r17, Integer r18, byte[] r19, Runnable r20, int r21, int r22) {
        if (r16 != null) goto L8;
        Bundle r0 = new Bundle();
        r0.putLong("chat_id", -r14);
        presentFragment(TopicsFragment.getTopicsOrChat(this, r0));
        if (r20 == null) goto L10;
        r20.run();
        return;
    L10:
        return;
    L8:
        TLRPC.TL_channels_getMessages r11 = new TLRPC.TL_channels_getMessages();
        r11.channel = MessagesController.getInstance(this.currentAccount).getInputChannel(-r14);
        r11.id.add(r16);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r11, new LaunchActivity$$ExternalSyntheticLambda100(this, r16, r18, r19, r14, r20, r17, r21, r22));
    }

    private void openStories(long[] r15, boolean r16) {
        int r2 = 0;
        int r3 = 0;
    L4:
        if (r3 >= r15.length) goto L11;
        TLRPC.User r4 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r15[r3]));
        if (r4 == null) goto L10;
        if (r4.stories_hidden == true) goto L10;
        boolean r32 = false;
    L12:
        BaseFragment r42 = getLastFragment();
        if (r42 == null) goto L96;
        StoriesController r6 = MessagesController.getInstance(this.currentAccount).getStoriesController();
        if (r32 == false) goto L18;
        ArrayList<TL_stories.PeerStories> r8 = r6.getHiddenList();
    L19:
        ArrayList r7 = new ArrayList(r8);
        boolean r82 = r32;
        ArrayList<Long> r33 = new ArrayList();
        ArrayList r9 = new ArrayList();
        if (r82 == true) goto L31;
        ArrayList r83 = new ArrayList();
        int r10 = 0;
    L23:
        if (r10 >= r15.length) goto L30;
        TLRPC.User r11 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r15[r10]));
        if (r11 != null) goto L27;
    L28:
        r83.add(Long.valueOf(r15[r10]));
    L29:
        r10 = r10 + 1;
        goto L23
    L27:
        if (r11.stories_hidden == true) goto L29;
    L30:
        long[] r0 = Longs.toArray(r83);
    L32:
        if (r16 == false) goto L37;
        int r84 = 0;
    L35:
        if (r84 >= r0.length) goto L42;
        r9.add(Long.valueOf(r0[r84]));
        r84 = r84 + 1;
    L42:
        if (r9.isEmpty() == true) goto L54;
        if (r16 == false) goto L54;
        MessagesController r34 = MessagesController.getInstance(this.currentAccount);
        int[] r43 = {r9.size()};
        LaunchActivity$$ExternalSyntheticLambda47 r62 = new LaunchActivity$$ExternalSyntheticLambda47(this, r43, r0);
        int r02 = 0;
    L46:
        if (r02 >= r9.size()) goto L97;
        long r72 = ((Long) r9.get(r02)).longValue();
        TL_stories.TL_stories_getPeerStories r102 = new TL_stories.TL_stories_getPeerStories();
        TLRPC.InputPeer r112 = r34.getInputPeer(r72);
        r102.peer = r112;
        if ((r112 instanceof TLRPC.TL_inputPeerEmpty) == false) goto L50;
        r43[0] = r43[0] - 1;
    L53:
        r02 = r02 + 1;
        goto L46
    L50:
        if (r112 != null) goto L52;
        r43[0] = r43[0] - 1;
        goto L53
    L52:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r102, new LaunchActivity$$ExternalSyntheticLambda48(r34, r72, r62));
        goto L53
    L97:
        return;
    L54:
        long r85 = UserConfig.getInstance(this.currentAccount).getClientUserId();
    L56:
        if (r2 >= r7.size()) goto L66;
        long r103 = DialogObject.getPeerDialogId(((TL_stories.PeerStories) r7.get(r2)).peer);
        if (r103 == r85) goto L64;
        if (r33.contains(Long.valueOf(r103)) == true) goto L64;
        if (r6.hasUnreadStories(r103) == false) goto L64;
        r33.add(Long.valueOf(r103));
    L64:
        r2 = r2 + 1;
        goto L56
    L66:
        if (r33.isEmpty() == false) goto L68;
        return;
    L68:
        if ((r42 instanceof DialogsActivity) == true) goto L75;
    L71:
        StoriesListPlaceProvider r03 = null;
    L70:
        StoriesListPlaceProvider r73 = r03;
        r42.getOrCreateStoryViewer().instantClose();
        r42.getOrCreateStoryViewer().open(this, null, r33, 0, null, null, r73, false);
        return;
    L75:
        r03 = StoriesListPlaceProvider.of(((DialogsActivity) r42).dialogStoriesCell.recyclerListView);     // Catch: Exception -> L74
    L37:
        int r86 = 0;
    L39:
        if (r86 >= r0.length) goto L42;
        r33.add(Long.valueOf(r0[r86]));
        r86 = r86 + 1;
        goto L39
    L31:
        r0 = r15;
        goto L32
    L18:
        r8 = r6.getDialogListStories();
        goto L19
    L96:
        return;
    L10:
        r3 = r3 + 1;
        goto L4
    L11:
        r32 = true;
        goto L12
    }

    private void openStory(long r11, int r13, boolean r14) {
        StoriesController r0 = MessagesController.getInstance(this.currentAccount).getStoriesController();
        TL_stories.PeerStories r6 = r0.getStories(r11);
        StoriesListPlaceProvider r7 = null;
        if (r6 == null) goto L24;
        int r8 = 0;
    L6:
        if (r8 >= r6.stories.size()) goto L11;
        if (r6.stories.get(r8).id == r13) goto L9;
        r8 = r8 + 1;
        goto L6
    L9:
        TL_stories.StoryItem r82 = r6.stories.get(r8);
    L12:
        if (r82 == null) goto L25;
        r82.dialogId = r11;
        BaseFragment r02 = getLastFragment();
        if (r02 != null) goto L17;
        return;
    L17:
        if ((r02 instanceof DialogsActivity) == true) goto L56;
    L19:
        r02.getOrCreateStoryViewer().instantClose();
        ArrayList<Long> r3 = new ArrayList();
        r3.add(Long.valueOf(r82.dialogId));
        if (r14 == false) goto L22;
        r02.getOrCreateStoryViewer().showViewsAfterOpening();
    L22:
        r02.getOrCreateStoryViewer().open(this, r82, r3, 0, null, r6, r7, false);
        return;
    L56:
        r7 = StoriesListPlaceProvider.of(((DialogsActivity) r02).dialogStoriesCell.recyclerListView);     // Catch: Exception -> L54
    L25:
        if (r82 != null) goto L52;
        StoriesController.StoriesList r1 = r0.getStoriesList(r11, 0);
        if (r1 == null) goto L31;
        MessageObject r5 = r1.findMessageObject(r13);
        if (r5 == null) goto L31;
        r82 = r5.storyItem;
    L32:
        if (r82 != null) goto L38;
        StoriesController.StoriesList r03 = r0.getStoriesList(r11, 1);
        if (r03 == null) goto L38;
        MessageObject r52 = r03.findMessageObject(r13);
        if (r52 == null) goto L38;
        r82 = r52.storyItem;
        StoriesController.StoriesList r53 = r03;
    L39:
        if (r82 == null) goto L52;
        if (r53 == null) goto L52;
        r82.dialogId = r11;
        BaseFragment r04 = getLastFragment();
        if (r04 != null) goto L45;
        return;
    L45:
        if ((r04 instanceof DialogsActivity) == true) goto L58;
    L47:
        r04.getOrCreateStoryViewer().instantClose();
        ArrayList<Long> r32 = new ArrayList();
        r32.add(Long.valueOf(r82.dialogId));
        if (r14 == false) goto L50;
        r04.getOrCreateStoryViewer().showViewsAfterOpening();
    L50:
        r04.getOrCreateStoryViewer().open(this, r82, r32, 0, r53, null, r7, false);
        return;
    L58:
        r7 = StoriesListPlaceProvider.of(((DialogsActivity) r04).dialogStoriesCell.recyclerListView);     // Catch: Exception -> L55
    L38:
        r53 = r1;
    L31:
        r1 = null;
    L52:
        TL_stories.TL_stories_getStoriesByID r62 = new TL_stories.TL_stories_getStoriesByID();
        r62.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r11);
        r62.id.add(Integer.valueOf(r13));
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r62, new LaunchActivity$$ExternalSyntheticLambda49(this, r13, r11, r14));
        return;
    L11:
        r82 = null;
        goto L12
    L24:
        r82 = null;
        goto L25
    }

    private void openThemeAccentPreview(TLRPC.TL_theme r9, TLRPC.TL_wallPaper r10, Theme.ThemeInfo r11) {
        int r0 = r11.lastAccentId;
        Theme.ThemeAccent r92 = r11.createNewAccent(r9, this.currentAccount);
        r11.prevAccentId = r11.currentAccentId;
        r11.setCurrentAccentId(r92.id);
        r92.pattern = r10;
        if (r0 == r11.lastAccentId) goto L6;
        boolean r93 = true;
    L7:
        presentFragment(new ThemePreviewActivity(r11, r93, 0, false, false));
        return;
    L6:
        r93 = false;
        goto L7
    }

    private void openTopicRequest(int r16, int r17, TLRPC.Chat r18, int r19, TLRPC.TL_forumTopic r20, Runnable r21, String r22, Integer r23, byte[] r24, int r25, ArrayList<MessageObject> r26, int r27) {
        if (r20 != null) goto L5;
        int r4 = r17;
        TLRPC.TL_forumTopic r6 = MessagesController.getInstance(r16).getTopicsController().findTopic(r18.id, r4);
    L6:
        if (r6 != null) goto L10;
        TL_forum.TL_messages_getForumTopicsByID r13 = new TL_forum.TL_messages_getForumTopicsByID();
        r13.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(-r18.id);
        r13.topics.add(Integer.valueOf(r4));
        ConnectionsManager.getInstance(r16).sendRequest(r13, new LaunchActivity$$ExternalSyntheticLambda164(this, r16, r18, r4, r19, r21, r22, r23, r24, r25, r26, r27));
        return;
    L10:
        if (this.mainFragmentsStack.isEmpty() == true) goto L12;
        ArrayList<BaseFragment> r0 = this.mainFragmentsStack;
        BaseFragment r02 = r0.get(r0.size() - 1);
    L14:
        if ((r02 instanceof ChatActivity) == false) goto L24;
        ChatActivity r03 = (ChatActivity) r02;
        if (r03.getDialogId() != (-r18.id)) goto L24;
        if (r03.isTopic == false) goto L24;
        if (r03.getTopicId() != r6.id) goto L24;
        if (r22 == null) goto L23;
        r03.setHighlightQuote(r19, r22, r27);
    L23:
        r03.highlightTaskId = r23;
        r03.scrollToMessageId(r19, r25, true, 0, true, 0, null, null);
    L41:
        if (r21 == null) goto L44;
        r21.run();
        return;
    L44:
        return;
    L24:
        Bundle r04 = new Bundle();
        r04.putLong("chat_id", r18.id);
        if (r19 == r6.id) goto L27;
        r04.putInt("message_id", Math.max(1, r19));
    L27:
        if (r23 == null) goto L29;
        r04.putInt("task_id", r23.intValue());
    L29:
        ChatActivity r42 = new ChatActivity(r04);
        if (r26.isEmpty() == false) goto L33;
        TLRPC.Message r05 = new TLRPC.Message();
        r05.id = 1;
        r05.action = new TLRPC.TL_messageActionChannelMigrateFrom();
        ArrayList<MessageObject> r11 = r26;
        r11.add(new MessageObject(r16, r05, false, false));
    L34:
        r42.setThreadMessages(r11, r18, r19, r6.read_inbox_max_id, r6.read_outbox_max_id, r6);
        if (r19 == r6.id) goto L40;
        if (r22 == null) goto L38;
        r42.setHighlightQuote(r19, r22, r27);
    L39:
        r42.scrollToMessageId(r19, r25, true, 0, true, 0, null, null);
        goto L40
    L38:
        r42.highlightTaskId = r23;
        r42.setHighlightMessageId(r19);
    L40:
        presentFragment(r42);
        goto L41
    L33:
        r11 = r26;
        goto L34
    L12:
        r02 = null;
        goto L14
    L5:
        r4 = r17;
        r6 = r20;
        goto L6
    }

    private void processAttachMenuBot(int r12, long r13, String r15, TLRPC.User r16, String r17, String r18) {
        TLRPC.TL_messages_getAttachMenuBot r0 = new TLRPC.TL_messages_getAttachMenuBot();
        r0.bot = MessagesController.getInstance(r12).getInputUser(r13);
        ConnectionsManager.getInstance(r12).sendRequest(r0, new LaunchActivity$$ExternalSyntheticLambda141(this, r12, r18, r15, r16, r17, r13));
    }

    private void processAttachedMenuBotFromShortcut(long r5) {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= this.visibleDialogs.size()) goto L13;
        if ((this.visibleDialogs.get(r1) instanceof BotWebViewSheet) == false) goto L12;
        BotWebViewSheet r2 = (BotWebViewSheet) this.visibleDialogs.get(r1);
        if (r2.isShowing() == false) goto L12;
        if (r2.getBotId() != r5) goto L12;
        return;
    L12:
        r1 = r1 + 1;
        goto L4
    L13:
        BaseFragment r12 = getSafeLastFragment();
        if (r12 != null) goto L16;
    L24:
        EmptyBaseFragment r13 = this.actionBarLayout.getSheetFragment(false);
        if (r13 != null) goto L27;
    L34:
        LaunchActivity$$ExternalSyntheticLambda53 r02 = new LaunchActivity$$ExternalSyntheticLambda53(this);
        TLRPC.User r14 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r5));
        if (r14 == null) goto L38;
        r02.run(r14);
        return;
    L38:
        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new LaunchActivity$$ExternalSyntheticLambda54(this, r5, r02));
        return;
    L27:
        if (r13.sheetsStack == null) goto L34;
    L29:
        if (r0 >= r13.sheetsStack.size()) goto L34;
        if (r13.sheetsStack.get(r0).isShown() == false) goto L33;
        r13.sheetsStack.get(r0);
    L33:
        r0 = r0 + 1;
        goto L29
    L16:
        if (r12.sheetsStack == null) goto L24;
        int r22 = 0;
    L19:
        if (r22 >= r12.sheetsStack.size()) goto L24;
        if (r12.sheetsStack.get(r22).isShown() == false) goto L23;
        r12.sheetsStack.get(r22);
    L23:
        r22 = r22 + 1;
        goto L19
    }

    private void processBoostDialog(Long r2, Runnable r3, Browser.Progress r4) {
        processBoostDialog(r2, r3, r4, null);
    }

    private void processBoostDialog(Long r11, Runnable r12, Browser.Progress r13, ChatMessageCell r14) {
        ChannelBoostsController r5 = MessagesController.getInstance(this.currentAccount).getBoostsController();
        if (r13 == null) goto L5;
        r13.init();
    L5:
        r5.getBoostsStats(r11.longValue(), new LaunchActivity$$ExternalSyntheticLambda61(this, r13, r12, r5, r11, r14));
    }

    private void processWebAppBot(int r62, String r63, String r64, String r65, String r66, String r67, String r68, String r69, String r70, String r71, String r72, String r73, String r74, boolean r75, Integer r76, Long r77, Long r78, Integer r79, String r80, HashMap<String, String> r81, String r82, String r83, String r84, String r85, TLRPC.TL_wallPaper r86, String r87, String r88, String r89, String r90, boolean r91, String r92, int r93, int r94, String r95, String r96, String r97, String r98, String r99, Browser.Progress r100, boolean r101, int r102, boolean r103, int r104, int r105, boolean r106, String r107, TLRPC.User r108, Runnable r109, boolean r110, boolean r111, boolean r112, boolean r113, boolean r114, boolean r115, boolean r116, String r117, boolean r118) {
        TLRPC.TL_messages_getBotApp r0 = new TLRPC.TL_messages_getBotApp();
        TLRPC.TL_inputBotAppShortName r1 = new TLRPC.TL_inputBotAppShortName();
        r1.bot_id = MessagesController.getInstance(r62).getInputUser(r108);
        r1.short_name = r98;
        r0.app = r1;
        ConnectionsManager.getInstance(r62).sendRequest(r0, new LaunchActivity$$ExternalSyntheticLambda140(this, r100, r62, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, r95, r96, r97, r101, r102, r103, r104, r105, r106, r107, r112, r113, r114, r115, r116, r117, r118, r109, r108, r99, r111, r110));
    }

    private String readImport(Uri r7) {
        String r0 = FileLoader.fixFileName(MediaController.getFileName(r7));
        int r3 = 0;
        InputStream r4 = null;
        String r42 = null;
        r42 = null;
        r42 = null;
        r42 = null;
        r42 = null;
        r42 = null;
        if (r0 != null) goto L5;
    L97:
        InputStream r6 = getContentResolver().openInputStream(r7);     // Catch: Throwable -> L64 Exception -> L66
        BufferedReader r72 = new BufferedReader(new InputStreamReader(r6));     // Catch: Throwable -> L53 Exception -> L55
        StringBuilder r02 = new StringBuilder();     // Catch: Throwable -> L53 Exception -> L55
    L49:
        String r5 = r72.readLine();     // Catch: Throwable -> L53 Exception -> L55
        if (r5 == null) goto L57;
        if (r3 >= 100) goto L57;
        r02.append(r5);     // Catch: Throwable -> L53 Exception -> L55
        r02.append('\n');     // Catch: Throwable -> L53 Exception -> L55
        r3 = r3 + 1;     // Catch: Throwable -> L53 Exception -> L55
    L57:
        String r73 = r02.toString();     // Catch: Throwable -> L53 Exception -> L55
        if (r6 != null) goto L93;
    L63:
        return r73;
    L93:
        r6.close();     // Catch: Exception -> L61
        return r73;
    L61:
        e = move-exception;
        FileLog.e(e);
    L53:
        th = th;
        r4 = r6;
    L75:
        if (r4 != null) goto L81;
    L80:
        throw th;
    L81:
        r4.close();     // Catch: Exception -> L78
    L78:
        e = move-exception;
        FileLog.e(e);
    L55:
        e = e;
    L68:
        FileLog.e(e);     // Catch: Throwable -> L53
        if (r6 != null) goto L91;
    L74:
        return null;
    L91:
        r6.close();     // Catch: Exception -> L72
    L72:
        e = move-exception;
        FileLog.e(e);
        goto L74
    L66:
        e = e;
        r6 = null;
    L64:
        th = th;
        goto L75
    L5:
        if (r0.endsWith(".zip") == false) goto L97;
        ZipInputStream r03 = new ZipInputStream(getContentResolver().openInputStream(r7));     // Catch: Exception -> L31
        ZipEntry r62 = r03.getNextEntry();     // Catch: Throwable -> L12
    L8:
        if (r62 == null) goto L34;
        String r63 = r62.getName();     // Catch: Throwable -> L12
        if (r63 == null) goto L11;
        int r74 = r63.lastIndexOf("/");     // Catch: Throwable -> L12
        if (r74 < 0) goto L18;
        r63 = r63.substring(r74 + 1);     // Catch: Throwable -> L12
    L18:
        if (r63.endsWith(".txt") == true) goto L83;
        r62 = r03.getNextEntry();     // Catch: Throwable -> L12
        goto L8
    L83:
        BufferedReader r64 = new BufferedReader(new InputStreamReader(r03));     // Catch: Throwable -> L12 Exception -> L24
        StringBuilder r75 = new StringBuilder();     // Catch: Throwable -> L12 Exception -> L24
    L20:
        String r52 = r64.readLine();     // Catch: Throwable -> L12 Exception -> L24
        if (r52 == null) goto L26;
        if (r3 >= 100) goto L26;
        r75.append(r52);     // Catch: Throwable -> L12 Exception -> L24
        r75.append('\n');     // Catch: Throwable -> L12 Exception -> L24
        r3 = r3 + 1;     // Catch: Throwable -> L12 Exception -> L24
    L26:
        r42 = r75.toString();     // Catch: Throwable -> L12 Exception -> L24
    L24:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L12
        r03.close();     // Catch: Exception -> L31
        return null;
    L11:
        r62 = r03.getNextEntry();     // Catch: Throwable -> L12
    L34:
        r03.closeEntry();     // Catch: Throwable -> L12
        r03.close();     // Catch: Exception -> L31
    L46:
        return r42;
    L12:
        th = move-exception;
        r03.close();     // Catch: Throwable -> L39
    L41:
        throw th;     // Catch: Exception -> L31
    L39:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L31
    L31:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L44
    L44:
        e = move-exception;
        FileLog.e(e);
        goto L46
    }

    private int runCommentRequest(int r15, Runnable r16, Integer r17, Integer r18, Long r19, Integer r20, TLRPC.Chat r21, AtomicBoolean r22) {
        return runCommentRequest(r15, r16, r17, r18, r19, r20, null, r21, null, null, 0, -1, r22);
    }

    private int runCommentRequest(int r20, Runnable r21, Integer r22, Integer r23, Long r24, Integer r25, byte[] r26, TLRPC.Chat r27, Runnable r28, String r29, int r30, int r31, AtomicBoolean r32) {
        boolean r0 = false;
        if (r27 == null) goto L24;
        if (r32 != null) goto L6;
    L8:
        TLRPC.TL_messages_getDiscussionMessage r15 = new TLRPC.TL_messages_getDiscussionMessage();
        r15.peer = MessagesController.getInputPeer(r27);
        if (r23 == null) goto L11;
        int r1 = r22.intValue();
    L12:
        r15.msg_id = r1;
        if (ChatObject.isChannelAndNotMegaGroup(r27) == true) goto L15;
    L17:
        boolean r4 = r0;
        if (r4 == false) goto L21;
        int r3 = r20;
        WidePosts.CommentsPostAuthorLoader r02 = new WidePosts.CommentsPostAuthorLoader(r3, r27);
    L20:
        WidePosts.CommentsPostAuthorLoader r16 = r02;
        return ConnectionsManager.getInstance(r3).sendRequest(r15, new LaunchActivity$$ExternalSyntheticLambda128(this, r32, r3, r4, r27, r24, r23, r22, r28, r29, r25, r26, r30, r31, r15, r16, r21));
    L21:
        r3 = r20;
        r02 = null;
        goto L20
    L15:
        if (ExteraConfig.getWidePostsInChannels() == false) goto L17;
        r0 = true;
        goto L17
    L11:
        r1 = (int) r24.longValue();
        goto L12
    L6:
        if (r32.get() == false) goto L8;
        return 0;
    L24:
        return 0;
    }

    private void runImportRequest(Uri r7, ArrayList<Uri> r8) {
        int r82 = UserConfig.selectedAccount;
        AlertDialog r0 = new AlertDialog(this, 3);
        int[] r1 = {0};
        String r3 = readImport(r7);
        if (r3 != null) goto L5;
        return;
    L5:
        TLRPC.TL_messages_checkHistoryImport r4 = new TLRPC.TL_messages_checkHistoryImport();
        r4.import_head = r3;
        r1[0] = ConnectionsManager.getInstance(r82).sendRequest(r4, new LaunchActivity$$ExternalSyntheticLambda51(this, r7, r82, r0));
        r0.setOnCancelListener(new LaunchActivity$$ExternalSyntheticLambda52(r82, r1, null));
        r0.showDelayed(300);     // Catch: Exception -> L8
        return;
    }

    private void runLinkRequest(int r67, String r68, String r69, String r70, String r71, String r72, String r73, String r74, String r75, String r76, String r77, String r78, String r79, boolean r80, Integer r81, Long r82, Long r83, Integer r84, String r85, HashMap<String, String> r86, String r87, String r88, String r89, String r90, TLRPC.TL_wallPaper r91, String r92, String r93, String r94, String r95, boolean r96, String r97, int r98, int r99, String r100, String r101, String r102, String r103, String r104, Browser.Progress r105, boolean r106, int r107, boolean r108, int r109, int r110, String r111, String r112, boolean r113, String r114, boolean r115, boolean r116, boolean r117, boolean r118, boolean r119, String r120, Integer r121, boolean r122, byte[] r123) {
        if (r98 == 0) goto L5;
    L9:
        int r11 = r67;
        if (r89 == null) goto L17;
        NotificationCenter r0 = NotificationCenter.getGlobalInstance();
        int r2 = NotificationCenter.didReceiveSmsCode;
        if (r0.hasObservers(r2) == false) goto L15;
        NotificationCenter.getGlobalInstance().postNotificationName(r2, new Object[]{r89});
        return;
    L15:
        AlertDialog.Builder r02 = new AlertDialog.Builder(this);
        r02.setTitle(LocaleController.getString(R.string.AppName));
        r02.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.OtherLoginCode, new Object[]{r89})));
        r02.setPositiveButton(LocaleController.getString(R.string.OK), null);
        showAlertDialog(r02);
        return;
    L17:
        if (r90 == null) goto L20;
        AlertDialog.Builder r03 = new AlertDialog.Builder(this);
        r03.setTitle(LocaleController.getString(R.string.AuthAnotherClient));
        r03.setMessage(LocaleController.getString(R.string.AuthAnotherClientUrl));
        r03.setPositiveButton(LocaleController.getString(R.string.OK), null);
        showAlertDialog(r03);
        return;
    L20:
        AlertDialog r8 = new AlertDialog(this, 3);
        Runnable r10 = new LaunchActivity$$ExternalSyntheticLambda77(r105, r8);
        boolean r14 = true;
        int[] r15 = {0};
        AtomicBoolean r61 = new AtomicBoolean();
        if (r77 == null) goto L24;
        TLRPC.TL_contacts_importContactToken r04 = new TLRPC.TL_contacts_importContactToken();
        r04.token = r77;
        r15[0] = ConnectionsManager.getInstance(r11).sendRequest(r04, new LaunchActivity$$ExternalSyntheticLambda83(this, r11, r77, r10));
    L23:
        char r5 = 0;
        LaunchActivity$$ExternalSyntheticLambda74 r12 = null;
        AlertDialog r6 = r8;
        int[] r1 = r15;
        AtomicBoolean r22 = r61;
        Browser.Progress r4 = r105;
    L142:
        LaunchActivity$$ExternalSyntheticLambda74 r7 = r12;
    L144:
        if (r1[r5] == 0) goto L160;
        r6.setOnCancelListener(new LaunchActivity$$ExternalSyntheticLambda81(r22, r11, r1, r7));
        if (r4 == null) goto L156;
        r4.onCancel(new LaunchActivity$$ExternalSyntheticLambda82(r22, r11, r1, r7));
    L156:
        if (r4 == null) goto L150;
        r4.init();     // Catch: Exception -> L152
    L155:
        return;
    L150:
        r6.showDelayed(300);     // Catch: Exception -> L152
    L162:
        return;
    L160:
        return;
    L24:
        if (r78 == null) goto L26;
        TL_chatlists.TL_chatlists_checkChatlistInvite r05 = new TL_chatlists.TL_chatlists_checkChatlistInvite();
        r05.slug = r78;
        r15[0] = ConnectionsManager.getInstance(r11).sendRequest(r05, new LaunchActivity$$ExternalSyntheticLambda84(this, r11, r78, r10));
        goto L23
    L26:
        if (r112 == null) goto L28;
        r15[0] = GiftAuctionController.getInstance(this.currentAccount).requestGiftAuctionBySlug(r112, new LaunchActivity$$ExternalSyntheticLambda85(this, r10));
        goto L23
    L28:
        if (r111 == null) goto L30;
        r15[0] = GiftAuctionController.getInstance(this.currentAccount).requestGiftAuctionBySlug(r111, new LaunchActivity$$ExternalSyntheticLambda86(this, r10));
        goto L23
    L30:
        if (r93 == null) goto L32;
        TL_stars.getUniqueStarGift r06 = new TL_stars.getUniqueStarGift();
        r06.slug = r93;
        r15[0] = ConnectionsManager.getInstance(r11).sendRequest(r06, new LaunchActivity$$ExternalSyntheticLambda87(this, r11, r93, r10));
        goto L23
    L32:
        if (r92 == null) goto L35;
        TLRPC.TL_payments_getPaymentForm r07 = new TLRPC.TL_payments_getPaymentForm();
        TLRPC.TL_inputInvoiceSlug r3 = new TLRPC.TL_inputInvoiceSlug();
        r3.slug = r92;
        r07.invoice = r3;
        r15[0] = ConnectionsManager.getInstance(r11).sendRequest(r07, new LaunchActivity$$ExternalSyntheticLambda88(this, r3, r10, r11, r92));
        r11 = r67;
        goto L23
    L35:
        if (r68 == null) goto L40;
        if (r105 == null) goto L38;
        r105.init();
    L38:
        r11 = r67;
        r4 = r105;
        MessagesController.getInstance(r67).getUserNameResolver().resolve(r68, r120, new LaunchActivity$$ExternalSyntheticLambda89(this, r10, r108, r107, r109, r85, r95, r96, r97, r103, r11, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r86, r87, r88, r89, r90, r91, r92, r93, r94, r98, r99, r100, r101, r102, r105, r106, r110, r111, r112, r113, r114, r115, r116, r117, r118, r119, r120, r121, r122, r123, r104, r15, r61));
        r1 = r15;
        r22 = r61;
        r6 = r8;
        r5 = 0;
    L39:
        r12 = null;
        goto L142
    L40:
        r4 = r105;
        if (r69 == null) goto L49;
        if (r98 != 0) goto L45;
        TLRPC.TL_messages_checkChatInvite r13 = new TLRPC.TL_messages_checkChatInvite();
        r13.hash = r69;
        r15[0] = ConnectionsManager.getInstance(r67).sendRequest(r13, new LaunchActivity$$ExternalSyntheticLambda90(this, r67, r8, r10, r69), 2);
        r11 = r67;
        r1 = r15;
        r22 = r61;
        r6 = r8;
        r5 = 0;
        goto L39
    L45:
        if (r98 != 1) goto L48;
        TLRPC.TL_messages_importChatInvite r52 = new TLRPC.TL_messages_importChatInvite();
        r52.hash = r69;
        r12 = null;
        ConnectionsManager.getInstance(r67).sendRequestTyped(r52, null, new LaunchActivity$$ExternalSyntheticLambda67(this, r10, r67), 2);
    L47:
        r11 = r67;
        r1 = r15;
        r22 = r61;
        r6 = r8;
        r5 = 0;
        goto L142
    L48:
        r12 = null;
        goto L47
    L49:
        r12 = null;
        if (r70 != null) goto L52;
        if (r71 != null) goto L64;
        if (r76 == null) goto L74;
        Bundle r16 = new Bundle();
        r16.putBoolean("onlySelect", true);
        r16.putInt("dialogsType", 3);
        DialogsActivity r08 = new DialogsActivity(r16);
        r08.setDelegate(new LaunchActivity$$ExternalSyntheticLambda68(this, r80, r67, r76));
        r5 = 0;
        presentFragment(r08, false, true);
        r11 = r67;
    L73:
        r1 = r15;
        r22 = r61;
        r6 = r8;
        goto L142
    L74:
        r5 = 0;
        if (r86 == null) goto L82;
        long r09 = Utilities.parseLong(r86.get("bot_id")).longValue();
        if (r09 == 0) goto L161;
        String r32 = r86.get("payload");
        String r62 = r86.get("nonce");
        String r710 = r86.get("callback_url");
        TL_account.getAuthorizationForm r810 = new TL_account.getAuthorizationForm();
        r810.bot_id = r09;
        r810.scope = r86.get("scope");
        r810.public_key = r86.get("public_key");
        r15[0] = ConnectionsManager.getInstance(r67).sendRequest(r810, new LaunchActivity$$ExternalSyntheticLambda69(this, r15, r67, r10, r810, r32, r62, r710));
    L80:
        r11 = r67;
        goto L73
    L161:
        return;
    L82:
        if (r88 == null) goto L85;
        TLRPC.TL_help_getDeepLinkInfo r010 = new TLRPC.TL_help_getDeepLinkInfo();
        r010.path = r88;
        r15[0] = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r010, new LaunchActivity$$ExternalSyntheticLambda70(this, r10));
        goto L80
    L85:
        if (r87 == null) goto L87;
        TLRPC.TL_langpack_getLanguage r17 = new TLRPC.TL_langpack_getLanguage();
        r17.lang_code = r87;
        r17.lang_pack = "android";
        r15[0] = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r17, new LaunchActivity$$ExternalSyntheticLambda71(this, r10));
        goto L80
    L87:
        if (r91 != null) goto L89;
        if (r94 == null) goto L102;
        r7 = new LaunchActivity$$ExternalSyntheticLambda74(this, r4);
        TL_account.getTheme r18 = new TL_account.getTheme();
        r18.format = "android";
        TLRPC.TL_inputThemeSlug r011 = new TLRPC.TL_inputThemeSlug();
        r011.slug = r94;
        r18.theme = r011;
        r6 = r8;
        r15[0] = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r18, new LaunchActivity$$ExternalSyntheticLambda75(this, r6, r10));
        r11 = r67;
        r1 = r15;
        r22 = r61;
        goto L144
    L102:
        r6 = r8;
        if (r82 == null) goto L107;
        if (r81 != null) goto L108;
        if (r113 == false) goto L107;
    L108:
        if (r83 == null) goto L114;
        TLRPC.Chat r012 = MessagesController.getInstance(r67).getChat(r82);
        if (r012 == null) goto L112;
        r15[0] = runCommentRequest(r67, r10, r81, r84, r83, r121, r012, r61);
        r22 = r61;
    L113:
        r11 = r67;
        r1 = r15;
        goto L142
    L112:
        TLRPC.TL_channels_getChannels r013 = new TLRPC.TL_channels_getChannels();
        TLRPC.TL_inputChannel r33 = new TLRPC.TL_inputChannel();
        r33.channel_id = r82.longValue();
        r013.id.add(r33);
        r22 = r61;
        r15[0] = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r013, new LaunchActivity$$ExternalSyntheticLambda76(this, r15, r67, r10, r81, r84, r83, r121, r61));
        goto L113
    L114:
        r22 = r61;
        Bundle r014 = new Bundle();
        r014.putLong("chat_id", r82.longValue());
        if (r81 == null) goto L117;
        r014.putInt("message_id", r81.intValue());
    L117:
        if (r121 == null) goto L120;
        r014.putInt("task_id", r121.intValue());
    L120:
        if (r123 == null) goto L122;
        r014.putByteArray("poll_option_id", r123);
    L122:
        TLRPC.Chat r811 = MessagesController.getInstance(this.currentAccount).getChat(r82);
        if (r811 != null) goto L125;
    L128:
        if (r811 == null) goto L133;
        if (r811.forum == false) goto L133;
        openForumFromLink(-r82.longValue(), r81, null, r121, r123, new LaunchActivity$$ExternalSyntheticLambda78(r10), 0, -1);
    L133:
        if (this.mainFragmentsStack.isEmpty() == true) goto L135;
        ArrayList<BaseFragment> r711 = this.mainFragmentsStack;
        BaseFragment r712 = r711.get(r711.size() - 1);
    L136:
        if (r712 != null) goto L138;
    L139:
        r1 = r15;
        r11 = r67;
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda79(this, r014, r82, r15, r10, r113, r4, r83, r81, r121, r123, r712, r67));
        goto L142
    L138:
        if (MessagesController.getInstance(r67).checkCanOpenChat(r014, r712) == false) goto L113;
    L135:
        r712 = null;
        goto L136
    L125:
        if (ChatObject.isBoostSupported(r811) == false) goto L128;
        if (r113 == false) goto L128;
        processBoostDialog(Long.valueOf(-r82.longValue()), r10, r4);
    L107:
        r11 = r67;
        r1 = r15;
        r22 = r61;
        if (r114 == null) goto L142;
        TL_account.resolveBusinessChatLink r015 = new TL_account.resolveBusinessChatLink();
        r015.slug = r114;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r015, new LaunchActivity$$ExternalSyntheticLambda80(this));
        goto L142
    L89:
        if (TextUtils.isEmpty(r91.slug) == true) goto L153;
    L99:
        TL_account.getWallPaper r016 = new TL_account.getWallPaper();
        TLRPC.TL_inputWallPaperSlug r19 = new TLRPC.TL_inputWallPaperSlug();
        r19.slug = r91.slug;
        r016.wallpaper = r19;
        r15[0] = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r016, new LaunchActivity$$ExternalSyntheticLambda73(this, r10, r91));
        goto L80
    L153:
        TLRPC.WallPaperSettings r017 = r91.settings;     // Catch: Exception -> L93
        int r124 = r017.third_background_color;     // Catch: Exception -> L93
        if (r124 == 0) goto L95;
        WallpapersListActivity.ColorWallpaper r34 = new WallpapersListActivity.ColorWallpaper("c", r017.background_color, r017.second_background_color, r124, r017.fourth_background_color);     // Catch: Exception -> L93
    L96:
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda72(this, new ThemePreviewActivity(r34, null, true, false)));     // Catch: Exception -> L93
        goto L80
    L95:
        r34 = new WallpapersListActivity.ColorWallpaper("c", r017.background_color, r017.second_background_color, AndroidUtilities.getWallpaperRotation(r017.rotation, false));     // Catch: Exception -> L93
    L93:
        e = move-exception;
        FileLog.e(e);
        goto L99
    L64:
        if (this.mainFragmentsStack.isEmpty() == true) goto L159;
        TLRPC.TL_inputStickerSetShortName r018 = new TLRPC.TL_inputStickerSetShortName();
        r018.short_name = r71;
        ArrayList r125 = new ArrayList(1);
        r125.add(r018);
        ArrayList<BaseFragment> r019 = this.mainFragmentsStack;
        BaseFragment r020 = r019.get(r019.size() - 1);
        if ((r020 instanceof ChatActivity) == false) goto L68;
        ChatActivity r23 = (ChatActivity) r020;
        EmojiPacksAlert r35 = new EmojiPacksAlert(r020, this, r23.getResourceProvider(), r125);
        r35.setCalcMandatoryInsets(r23.isKeyboardVisible());
    L69:
        r020.showDialog(r35);
        return;
    L68:
        r35 = new EmojiPacksAlert(r020, this, null, r125);
        goto L69
    L159:
        return;
    L52:
        if (this.mainFragmentsStack.isEmpty() == true) goto L158;
        TLRPC.TL_inputStickerSetShortName r021 = new TLRPC.TL_inputStickerSetShortName();
        r021.short_name = r70;
        ArrayList<BaseFragment> r126 = this.mainFragmentsStack;
        BaseFragment r127 = r126.get(r126.size() - 1);
        if ((r127 instanceof ChatActivity) == false) goto L56;
        ChatActivity r24 = (ChatActivity) r127;
        StickersAlert r36 = new StickersAlert(this, r127, r021, null, r24.getChatActivityEnterViewForStickers(), r24.getResourceProvider(), false);
        r36.setCalcMandatoryInsets(r24.isKeyboardVisible());
    L57:
        if (r71 != null) goto L60;
        r14 = false;
    L60:
        r36.probablyEmojis = r14;
        r127.showDialog(r36);
        return;
    L56:
        r36 = new StickersAlert(this, r127, r021, null, null, false);
        goto L57
    L158:
        return;
    L5:
        if (UserConfig.getActivatedAccountsCount() < 2) goto L9;
        if (r86 == null) goto L9;
        AlertsCreator.createAccountSelectDialog(this, new LaunchActivity$$ExternalSyntheticLambda66(this, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, r95, r96, r97, r99, r100, r101, r102, r103, r104, r105, r106, r107, r108, r109, r110, r111, r112, r113, r114, r115, r116, r117, r118, r119, r120, r121, r122, r123)).show();
    }

    private static void sendShareText(AccountInstance r21, DialogsActivity r22, CharSequence r23, long r24, MessageObject r26, long r27, boolean r29, int r30, int r31) {
        if (r22 != null) goto L5;
    L8:
        boolean r9 = true;
    L9:
        if (r9 == false) goto L13;
        if (r22 == null) goto L13;
        TLRPC.WebPage r2 = r22.getSharedWebPage();
    L12:
        TLRPC.WebPage r8 = r2;
        if (TextUtils.isEmpty(r23) == true) goto L29;
        if (r8 != null) goto L21;
        if (r9 == false) goto L21;
        SendMessagesHelper.prepareSendingText(r21, r23, r24, r27, r29, r30, r31, 0);
        return;
    L21:
        CharSequence r25 = SendMessagesHelper.getTrimmedString(r23);
        if (r25 != null) goto L24;
        return;
    L24:
        if (r25.length() == 0) goto L30;
        CharSequence[] r3 = {r25};
        r21.getSendMessagesHelper().sendMessage(SendMessagesHelper.SendMessageParams.of(r3[0].toString(), r24, r26, r26, r8, r9, r21.getMediaDataController().getEntities(r3, true), null, null, r29, r30, r31, null, false));
        return;
    L30:
        return;
    L29:
        return;
    L13:
        r2 = null;
        goto L12
    L5:
        if (r22.isWebPagePreviewEnabled() == true) goto L8;
        r9 = false;
        goto L9
    }

    private void setupActionBarLayout() {
        int r0 = this.drawerLayoutContainer.indexOfChild(this.launchLayout);
        DrawerLayoutContainer r1 = this.drawerLayoutContainer;
        if (r0 == (-1)) goto L6;
        ViewGroup r02 = this.launchLayout;
    L5:
        int r03 = r1.indexOfChild(r02);
        if (r03 == (-1)) goto L10;
        this.drawerLayoutContainer.removeViewAt(r03);
    L10:
        if (AndroidUtilities.isTablet() == false) goto L27;
        getWindow().setSoftInputMode(16);
        AnonymousClass10 r12 = new AnonymousClass10(this, this);
        this.launchLayout = r12;
        DrawerLayoutContainer r4 = this.drawerLayoutContainer;
        if (r03 == (-1)) goto L14;
        r4.addView(r12, r03, LayoutHelper.createFrame(-1, -1.0f));
    L15:
        AnonymousClass11 r04 = new AnonymousClass11(this, this);
        this.backgroundTablet = r04;
        r04.setOccupyStatusBar(false);
        this.backgroundTablet.setBackgroundImage(Theme.getCachedWallpaper(), Theme.isWallpaperMotion());
        this.launchLayout.addView(this.backgroundTablet, LayoutHelper.createRelative(-1, -1));
        ViewGroup r05 = (ViewGroup) this.actionBarLayout.getView().getParent();
        if (r05 == null) goto L18;
        r05.removeView(this.actionBarLayout.getView());
    L18:
        this.launchLayout.addView(this.actionBarLayout.getView());
        ActionBarLayout r06 = new ActionBarLayout(this, false);
        this.rightActionBarLayout = r06;
        r06.setIsRightLayout();
        this.rightActionBarLayout.setFragmentStack(this.rightFragmentsStack);
        this.rightActionBarLayout.setDelegate(this);
        this.launchLayout.addView(this.rightActionBarLayout.getView());
        FrameLayout r07 = new FrameLayout(this);
        this.shadowTablet = r07;
        int r2 = 8;
        if (this.layerFragmentsStack.isEmpty() == false) goto L21;
        int r13 = 8;
    L22:
        r07.setVisibility(r13);
        this.shadowTablet.setBackgroundColor(1056964608);
        this.launchLayout.addView(this.shadowTablet);
        this.shadowTablet.setOnTouchListener(new LaunchActivity$$ExternalSyntheticLambda16(this));
        this.shadowTablet.setOnClickListener(new LaunchActivity$$ExternalSyntheticLambda17());
        ActionBarLayout r08 = new ActionBarLayout(this, false);
        this.layersActionBarLayout = r08;
        r08.setIsLayersLayout();
        this.layersActionBarLayout.setRemoveActionBarExtraHeight(true);
        this.layersActionBarLayout.setBackgroundView(this.shadowTablet);
        this.layersActionBarLayout.setUseAlphaAnimations(true);
        this.layersActionBarLayout.setFragmentStack(this.layerFragmentsStack);
        this.layersActionBarLayout.setDelegate(this);
        this.layersActionBarLayout.setDrawerLayoutContainer(this.drawerLayoutContainer);
        ViewGroup r09 = this.layersActionBarLayout.getView();
        if (this.layerFragmentsStack.isEmpty() == true) goto L26;
        r2 = 0;
    L26:
        r09.setVisibility(r2);
        this.launchLayout.addView(r09);
    L34:
        FloatingDebugController.setActive(this, SharedConfig.isFloatingDebugActive, false);
        return;
    L21:
        r13 = 0;
        goto L22
    L14:
        r4.addView(r12, LayoutHelper.createFrame(-1, -1.0f));
        goto L15
    L27:
        ViewGroup r14 = (ViewGroup) this.actionBarLayout.getView().getParent();
        if (r14 == null) goto L30;
        r14.removeView(this.actionBarLayout.getView());
    L30:
        this.actionBarLayout.setFragmentStack(this.mainFragmentsStack);
        DrawerLayoutContainer r15 = this.drawerLayoutContainer;
        if (r03 == (-1)) goto L33;
        r15.addView(this.actionBarLayout.getView(), r03, new ViewGroup.LayoutParams(-1, -1));
        goto L34
    L33:
        r15.addView(this.actionBarLayout.getView(), new ViewGroup.LayoutParams(-1, -1));
        goto L34
    L6:
        r02 = this.actionBarLayout.getView();
        goto L5
    }

    public static void showAttachMenuBot(LaunchActivity r22, int r23, TLRPC.TL_attachMenuBot r24, String r25, boolean r26) {
        BaseFragment r2 = getLastFragment();
        if (r2 == null) goto L20;
        long r4 = r24.bot_id;
        WebViewRequestProps r42 = WebViewRequestProps.of(r23, r4, r4, r24.short_name, null, 1, 0, 0, false, null, false, r25, null, 2, false, false);
        if (r22.getBottomSheetTabs() != null) goto L8;
    L10:
        TLRPC.User r1 = MessagesController.getInstance(r23).getUser(Long.valueOf(r24.bot_id));
        if (r1 != null) goto L13;
        String r12 = null;
    L15:
        if (TextUtils.isEmpty(r12) == true) goto L18;
        MessagesController.getInstance(r23);
        MessagesController.showCantOpenAlert(r2, r12);
        return;
    L18:
        BotWebViewSheet r13 = new BotWebViewSheet(r22, r2.getResourceProvider());
        r13.setNeedsContext(false);
        r13.setDefaultFullsize(r26);
        r13.setParentActivity(r22);
        r13.requestWebView(r2, r42);
        r13.show();
        return;
    L13:
        r12 = MessagesController.getInstance(r23).getRestrictionReason(r1.restriction_reason);
        goto L15
    L8:
        if (r22.getBottomSheetTabs().tryReopenTab(r42) == null) goto L10;
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r17v1 */
    /* JADX WARN: Type inference failed for: r17v2 */
    /* JADX WARN: Type inference failed for: r17v7 */
    private void showLanguageAlert(boolean r17) {
        if (UserConfig.getInstance(this.currentAccount).isClientActivated() == false) goto L77;
    L63:
        e = move-exception;
        FileLog.e(e);
        return;
    L6:
        if (this.loadingLocaleDialog == false) goto L8;
        return;
    L8:
        if (ApplicationLoader.mainInterfacePaused == true) goto L72;
        String r7 = MessagesController.getGlobalMainSettings().getString("language_showed2", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Exception -> L63
        String r8 = MessagesController.getInstance(this.currentAccount).suggestedLangCode;     // Catch: Exception -> L63
        if (r17 == false) goto L13;
    L18:
        LocaleController.LocaleInfo[] r6 = new LocaleController.LocaleInfo[2];     // Catch: Exception -> L63
        boolean r9 = false;
        if (r8.contains("-") == false) goto L21;
        String r72 = r8.split("-")[0];     // Catch: Exception -> L63
    L23:
        if ("in".equals(r72) == false) goto L26;
        String r10 = "id";
    L32:
        int r12 = 0;
    L34:
        if (r12 >= LocaleController.getInstance().languages.size()) goto L51;
        LocaleController.LocaleInfo r13 = LocaleController.getInstance().languages.get(r12);     // Catch: Exception -> L63
        boolean r172 = r9;
        if (r13.shortName.equals("en") == false) goto L39;
        r6[r172 ? 1 : 0] = r13;     // Catch: Exception -> L63
    L39:
        if (r13.shortName.replace("_", "-").equals(r8) == false) goto L41;
    L44:
        r6[1] = r13;     // Catch: Exception -> L63
    L46:
        if (r6[r172 ? 1 : 0] == null) goto L50;
        if (r6[1] == null) goto L50;
        ?? r173 = r172;
    L52:
        LocaleController.LocaleInfo r5 = r6[r173];     // Catch: Exception -> L63
        if (r5 == null) goto L74;
        LocaleController.LocaleInfo r73 = r6[1];     // Catch: Exception -> L63
        if (r73 == null) goto L75;
        if (r5 != r73) goto L59;
        return;
    L59:
        if (BuildVars.LOGS_ENABLED == false) goto L61;
        FileLog.d("show lang alert for " + r6[r173].getKey() + " and " + r6[1].getKey());     // Catch: Exception -> L63
    L61:
        this.systemLocaleStrings = null;     // Catch: Exception -> L63
        this.englishLocaleStrings = null;     // Catch: Exception -> L63
        this.loadingLocaleDialog = true;     // Catch: Exception -> L63
        TLRPC.TL_langpack_getStrings r52 = new TLRPC.TL_langpack_getStrings();     // Catch: Exception -> L63
        r52.lang_code = r6[1].getLangCode();     // Catch: Exception -> L63
        r52.keys.add("English");     // Catch: Exception -> L63
        r52.keys.add("ChooseYourLanguage");     // Catch: Exception -> L63
        r52.keys.add("ChooseYourLanguageOther");     // Catch: Exception -> L63
        r52.keys.add("ChangeLanguageLater");     // Catch: Exception -> L63
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r52, new LaunchActivity$$ExternalSyntheticLambda42(this, r6, r8), 8);     // Catch: Exception -> L63
        TLRPC.TL_langpack_getStrings r53 = new TLRPC.TL_langpack_getStrings();     // Catch: Exception -> L63
        r53.lang_code = r6[r173].getLangCode();     // Catch: Exception -> L63
        r53.keys.add("English");     // Catch: Exception -> L63
        r53.keys.add("ChooseYourLanguage");     // Catch: Exception -> L63
        r53.keys.add("ChooseYourLanguageOther");     // Catch: Exception -> L63
        r53.keys.add("ChangeLanguageLater");     // Catch: Exception -> L63
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r53, new LaunchActivity$$ExternalSyntheticLambda43(this, r6, r8), 8);     // Catch: Exception -> L63
        return;
    L75:
        return;
    L74:
        return;
    L50:
        r12 = r12 + 1;     // Catch: Exception -> L63
        r9 = r172 ? 1 : 0;
        goto L34
    L41:
        if (r13.shortName.equals(r72) == true) goto L44;
        if (r13.shortName.equals(r10) == false) goto L46;
    L51:
        r173 = r9;
        goto L52
    L26:
        if ("iw".equals(r72) == false) goto L29;
        r10 = "he";
        goto L32
    L29:
        if ("jw".equals(r72) == false) goto L31;
        r10 = "jv";
        goto L32
    L31:
        r10 = null;
        goto L32
    L21:
        r72 = r8;
        goto L23
    L13:
        if (r7.equals(r8) == false) goto L18;
        if (BuildVars.LOGS_ENABLED == false) goto L73;
        FileLog.d("alert already showed for ".concat(r7));     // Catch: Exception -> L63
        return;
    L73:
        return;
    L72:
        return;
    }

    private void showLanguageAlertInternal(LocaleController.LocaleInfo r18, LocaleController.LocaleInfo r19, String r20) {
        this.loadingLocaleDialog = false;     // Catch: Exception -> L34
        LocaleController.LocaleInfo r4 = r18;
        if (r4.builtIn == false) goto L6;
    L9:
        boolean r5 = true;
    L10:
        AlertDialog.Builder r7 = new AlertDialog.Builder(this);     // Catch: Exception -> L34
        r7.setTitle(getStringForLanguageAlert(this.systemLocaleStrings, "ChooseYourLanguage", R.string.ChooseYourLanguage));     // Catch: Exception -> L34
        r7.setSubtitle(getStringForLanguageAlert(this.englishLocaleStrings, "ChooseYourLanguage", R.string.ChooseYourLanguage));     // Catch: Exception -> L34
        LinearLayout r2 = new LinearLayout(this);     // Catch: Exception -> L34
        r2.setOrientation(1);     // Catch: Exception -> L34
        LanguageCell[] r9 = new LanguageCell[2];     // Catch: Exception -> L34
        String r10 = getStringForLanguageAlert(this.systemLocaleStrings, "English", R.string.English);     // Catch: Exception -> L34
        if (r5 == false) goto L13;
        LocaleController.LocaleInfo r11 = r4;
    L14:
        if (r5 == false) goto L16;
        LocaleController.LocaleInfo r12 = r19;
    L17:
        LocaleController.LocaleInfo[] r112 = {r11, r12};     // Catch: Exception -> L34
        if (r5 == true) goto L21;
        r4 = r19;
    L21:
        LocaleController.LocaleInfo[] r42 = {r4};     // Catch: Exception -> L34
        int r52 = 0;
    L22:
        if (r52 >= 2) goto L32;
        LanguageCell r14 = new LanguageCell(this);     // Catch: Exception -> L34
        r9[r52] = r14;     // Catch: Exception -> L34
        LocaleController.LocaleInfo r15 = r112[r52];     // Catch: Exception -> L34
        if (r15 != r19) goto L26;
        String r13 = r10;
    L27:
        r14.setLanguage(r15, r13, true);     // Catch: Exception -> L34
        r9[r52].setTag(Integer.valueOf(r52));     // Catch: Exception -> L34
        r9[r52].setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_dialogButtonSelector), 2));     // Catch: Exception -> L34
        LanguageCell r132 = r9[r52];     // Catch: Exception -> L34
        if (r52 != 0) goto L30;
        boolean r142 = true;
    L31:
        r132.setLanguageSelected(r142, false);     // Catch: Exception -> L34
        r2.addView(r9[r52], LayoutHelper.createLinear(-1, 50));     // Catch: Exception -> L34
        r9[r52].setOnClickListener(new LaunchActivity$$ExternalSyntheticLambda147(r42, r9));     // Catch: Exception -> L34
        r52 = r52 + 1;     // Catch: Exception -> L34
        goto L22
    L30:
        r142 = false;
        goto L31
    L26:
        r13 = null;
        goto L27
    L32:
        LanguageCell r3 = new LanguageCell(this);     // Catch: Exception -> L34
        r3.setValue(getStringForLanguageAlert(this.systemLocaleStrings, "ChooseYourLanguageOther", R.string.ChooseYourLanguageOther), getStringForLanguageAlert(this.englishLocaleStrings, "ChooseYourLanguageOther", R.string.ChooseYourLanguageOther));     // Catch: Exception -> L34
        r3.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_dialogButtonSelector), 2));     // Catch: Exception -> L34
        r3.setOnClickListener(new LaunchActivity$$ExternalSyntheticLambda148(this));     // Catch: Exception -> L34
        r2.addView(r3, LayoutHelper.createLinear(-1, 50));     // Catch: Exception -> L34
        r7.setView(r2);     // Catch: Exception -> L34
        r7.setNegativeButton(LocaleController.getString(R.string.OK), new LaunchActivity$$ExternalSyntheticLambda149(this, r42));     // Catch: Exception -> L34
        this.localeDialog = showAlertDialog(r7);     // Catch: Exception -> L34
        MessagesController.getGlobalMainSettings().edit().putString("language_showed2", r20).apply();     // Catch: Exception -> L34
        return;
    L16:
        r12 = r4;
        goto L17
    L13:
        r11 = r19;
        goto L14
    L6:
        if (LocaleController.getInstance().isCurrentLocalLocale() == true) goto L9;
        r5 = false;
    L34:
        e = move-exception;
        FileLog.e(e);
    }

    private void showTosActivity(int r5, TLRPC.TL_help_termsOfService r6) {
        if (this.termsOfServiceView != null) goto L5;
        TermsOfServiceView r0 = new TermsOfServiceView(this);
        this.termsOfServiceView = r0;
        r0.setAlpha(0.0f);
        this.drawerLayoutContainer.addView(this.termsOfServiceView, LayoutHelper.createFrame(-1, -1.0f));
        this.termsOfServiceView.setDelegate(new AnonymousClass12(this));
    L5:
        TLRPC.TL_help_termsOfService r02 = UserConfig.getInstance(r5).unacceptedTermsOfService;
        if (r02 == r6) goto L11;
        if (r02 != null) goto L9;
    L10:
        UserConfig.getInstance(r5).unacceptedTermsOfService = r6;
        UserConfig.getInstance(r5).saveConfig(false);
        goto L11
    L9:
        if (r02.id.data.equals(r6.id.data) == false) goto L10;
    L11:
        this.termsOfServiceView.show(r5, r6);
        this.termsOfServiceView.animate().alpha(1.0f).setDuration(150).setInterpolator(AndroidUtilities.decelerateInterpolator).setListener(null).start();
    }

    private void showUpdateActivity(int r4, TLRPC.TL_help_appUpdate r5, boolean r6) {
        if (this.blockingUpdateView != null) goto L5;
        BlockingUpdateView r0 = new BlockingUpdateView(this, r5);
        this.blockingUpdateView = r0;
        this.drawerLayoutContainer.addView(r0, LayoutHelper.createFrame(-1, -1.0f));
    L5:
        this.blockingUpdateView.show(r4, r6);
    }

    private void showVoiceChatTooltip(int r8) {
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.mainFragmentsStack.isEmpty() == false) goto L7;
        return;
    L7:
        if (r0.groupCall == null) goto L38;
        TLRPC.Chat r02 = r0.getChat();
        BaseFragment r7 = this.actionBarLayout.getFragmentStack().get(this.actionBarLayout.getFragmentStack().size() - 1);
        if ((r7 instanceof MainTabsActivity) == false) goto L12;
        r7 = ((MainTabsActivity) r7).getCurrentVisibleFragment();
    L12:
        UndoView r2 = null;
        if ((r7 instanceof ChatActivity) == false) goto L21;
        ChatActivity r72 = (ChatActivity) r7;
        if (r02 != null) goto L17;
    L19:
        r2 = r72.getUndoView();
    L26:
        if (r2 == null) goto L29;
        r2.showWithAction(0, r8, r02);
    L29:
        if (r8 == 38) goto L31;
        return;
    L31:
        if (VoIPService.getSharedInstance() == null) goto L37;
        VoIPService.getSharedInstance().playAllowTalkSound();
        return;
    L37:
        return;
    L17:
        if (r72.getDialogId() != (-r02.id)) goto L19;
        r02 = null;
        goto L19
    L21:
        if ((r7 instanceof DialogsActivity) == false) goto L24;
        r2 = ((DialogsActivity) r7).getUndoView();
        goto L26
    L24:
        if ((r7 instanceof ProfileActivity) == false) goto L26;
        r2 = ((ProfileActivity) r7).getUndoView();
        goto L26
    }

    private void switchToAvailableAccountOrLogout() {
        int r0 = 0;
    L4:
        if (r0 >= 16) goto L9;
        if (UserConfig.getInstance(r0).isClientActivated() == true) goto L10;
        r0 = r0 + 1;
    L10:
        TermsOfServiceView r1 = this.termsOfServiceView;
        if (r1 == null) goto L14;
        r1.setVisibility(8);
    L14:
        if (r0 == (-1)) goto L17;
        switchToAccount(r0, true);
        return;
    L17:
        RestrictedLanguagesSelectActivity.checkRestrictedLanguages(true);
        clearFragments();
        this.actionBarLayout.rebuildLogout();
        if (AndroidUtilities.isTablet() == false) goto L20;
        this.layersActionBarLayout.rebuildLogout();
        this.rightActionBarLayout.rebuildLogout();
    L20:
        presentFragment(new IntroActivity().setOnLogout());
        return;
    L9:
        r0 = -1;
        goto L10
    }

    private void syncDrawerContainerEnabled() {
        if (this.drawerLayoutContainer == null) goto L17;
        boolean r0 = ExteraConfig.getNavigationDrawer();
        DrawerLayoutContainer r1 = this.drawerLayoutContainer;
        if (r0 == false) goto L12;
        if (r1.getDrawerContainer() != null) goto L15;
        this.drawerLayoutContainer.setDrawerContainer(new DrawerContainer(this));
        return;
    L15:
        return;
    L12:
        if (r1.getDrawerContainer() == null) goto L16;
        this.drawerLayoutContainer.setDrawerContainer(null);
        return;
    L16:
        return;
    }

    private void updateCurrentConnectionState() {
        if (this.actionBarLayout != null) goto L5;
        return;
    L5:
        int r0 = ConnectionsManager.getInstance(this.currentAccount).getConnectionState();
        this.currentConnectionState = r0;
        LaunchActivity$$ExternalSyntheticLambda39 r4 = null;
        if (r0 != 2) goto L9;
        int r1 = R.string.WaitingForNetwork;
        String r5 = "WaitingForNetwork";
    L16:
        if (r0 == 1) goto L18;
        if (r0 == 4) goto L18;
    L19:
        this.actionBarLayout.setTitleOverlayText(r5, r1, r4);
        return;
    L18:
        r4 = new LaunchActivity$$ExternalSyntheticLambda39(this);
        goto L19
    L9:
        if (r0 != 5) goto L11;
        r1 = R.string.Updating;
        r5 = "Updating";
        goto L16
    L11:
        if (r0 != 4) goto L13;
        r1 = R.string.ConnectingToProxyWithDots;
        r5 = "ConnectingToProxyWithDots";
        goto L16
    L13:
        if (r0 != 1) goto L15;
        r1 = R.string.Connecting;
        r5 = "Connecting";
        goto L16
    L15:
        r1 = 0;
        r5 = null;
        goto L16
    }

    private void updateReasonsToHideMainContent(boolean r5, boolean r6) {
        int r0 = this.reasonsToHideMainContent;
        int r1 = -1;
        if (r5 == false) goto L5;
        int r3 = 1;
    L6:
        int r02 = r0 + r3;
        this.reasonsToHideMainContent = r02;
        if (r6 == false) goto L12;
        int r62 = this.reasonsToHideDecorView;
        if (r5 == false) goto L11;
        r1 = 1;
    L11:
        this.reasonsToHideDecorView = r62 + r1;
    L12:
        FrameLayout r52 = this.frameLayout;
        if (r52 == null) goto L18;
        if (r02 <= 0) goto L16;
        int r63 = 8;
    L17:
        r52.setVisibility(r63);
        goto L18
    L16:
        r63 = 0;
    L18:
        checkDecorViewVisibility();
        return;
    L5:
        r3 = -1;
        goto L6
    }

    public void addOnUserLeaveHintListener(Runnable r1) {
        this.onUserLeaveHintListeners.add(r1);
    }

    public void addOverlayPasscodeView(PasscodeView r1) {
        this.overlayPasscodeViews.add(r1);
    }

    public boolean allowShowFingerprintDialog(PasscodeView r4) {
        if (this.overlayPasscodeViews.isEmpty() == false) goto L10;
        PasscodeViewDialog r0 = this.passcodeDialog;
        if (r0 == null) goto L10;
        if (r4 != r0.passcodeView) goto L9;
        return true;
    L9:
        return false;
    L10:
        List<PasscodeView> r3 = this.overlayPasscodeViews;
        if (r3.get(r3.size() - 1) != r4) goto L13;
        return true;
    L13:
        return false;
    }

    @Deprecated
    public void animateNavigationBarColor(int r3) {
        if (Build.VERSION.SDK_INT >= 26) goto L5;
        return;
    L5:
        ValueAnimator r0 = this.navBarAnimator;
        if (r0 == null) goto L8;
        r0.cancel();
        this.navBarAnimator = null;
    L8:
        ValueAnimator r02 = ValueAnimator.ofArgb(new int[]{getNavigationBarColor(), r3});
        this.navBarAnimator = r02;
        r02.addUpdateListener(new LaunchActivity$$ExternalSyntheticLambda50(this));
        this.navBarAnimator.addListener(new AnonymousClass19(this, r3));
        this.navBarAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.navBarAnimator.setDuration(320);
        this.navBarAnimator.start();
    }

    @Override // org.telegram.ui.DialogsActivity.DialogsActivityDelegate
    public boolean canSelectStories() {
        ArrayList<SendMessagesHelper.SendingMediaInfo> r0 = this.photoPathsArray;
        if (r0 == null) goto L7;
        if (r0.size() != 1) goto L7;
    L8:
        return true;
    L7:
        if (this.videoPath != null) goto L8;
        return false;
    }

    public void checkAppUpdate(boolean r2) {
        checkAppUpdate(r2, null);
    }

    public void checkAppUpdate(boolean r7, Browser.Progress r8) {
        if (r7 == true) goto L8;
        long r0 = System.currentTimeMillis();
        if (Math.abs(r0 - SharedConfig.lastUpdateCheckTime) >= 1800000) goto L6;
        return;
    L6:
        if (Math.abs(r0 - ExteraConfig.getUpdateScheduleTimestamp()) >= DurationKt.MILLIS_IN_HOUR) goto L8;
        return;
    L8:
        SharedConfig.lastUpdateCheckTime = System.currentTimeMillis();
        SharedConfig.saveConfig();
        UpdaterUtils.getAppUpdate(new LaunchActivity$$ExternalSyntheticLambda41(this, r7, this.currentAccount, r8));
        if (r8 == null) goto L13;
        r8.init();
        return;
    }

    public void checkFrameMetrics() {
        if (this.frameMetricsOverlayView == null) goto L5;
    L7:
        FrameMetricsOverlayView r0 = this.frameMetricsOverlayView;
        if (r0 != null) goto L10;
        return;
    L10:
        if (SharedConfig.frameMetricsEnabled == true) goto L14;
        r0.detach();
        this.frameMetricsOverlayView = null;
        return;
    L14:
        return;
    L5:
        if (SharedConfig.frameMetricsEnabled == false) goto L7;
        this.frameMetricsOverlayView = FrameMetricsOverlayView.attachToActivityCorner(this, NavigationBarView.ITEM_GRAVITY_START_CENTER, 12, this.frameLayout);
        goto L7
    }

    public void checkSystemBarColors(boolean r10, boolean r11, boolean r12) {
        boolean r2 = true;
        if (this.mainFragmentsStack.isEmpty() == true) goto L5;
        ArrayList<BaseFragment> r0 = this.mainFragmentsStack;
        BaseFragment r02 = r0.get(r0.size() - 1);
    L6:
        char r3 = 2;
        if (r02 != null) goto L9;
    L16:
        if (r02 == null) goto L19;
        r02.hasForceLightStatusBar();
    L19:
        if (r11 == false) goto L43;
        if (r02 == null) goto L38;
        boolean r5 = r02.isLightStatusBar();
        if ((r02.getParentLayout() instanceof ActionBarLayout) == false) goto L41;
        ActionBarLayout r6 = (ActionBarLayout) r02.getParentLayout();
        if (r6.getSheetFragment(false) != null) goto L26;
    L30:
        ArrayList<BaseFragment.AttachedSheet> r62 = r02.sheetsStack;
        if (r62 == null) goto L41;
        if (r62.isEmpty() == true) goto L41;
        ArrayList<BaseFragment.AttachedSheet> r63 = r02.sheetsStack;
        BaseFragment.AttachedSheet r64 = r63.get(r63.size() - 1);
        if (r64.isShown() == false) goto L41;
        r5 = r64.isAttachedLightStatusBar();
        goto L41
    L26:
        if (r6.getSheetFragment(false).getLastSheet() == null) goto L30;
        BaseFragment.AttachedSheet r65 = r6.getSheetFragment(false).getLastSheet();
        if (r65.isShown() == false) goto L41;
        r5 = r65.isAttachedLightStatusBar();
    L41:
        AndroidUtilities.setLightStatusBar(this, r5);
        goto L43
    L38:
        if (ColorUtils.calculateLuminance(Theme.getColor(Theme.key_actionBarDefault, null, true)) <= 0.699999988079071d) goto L40;
        r5 = true;
        goto L41
    L40:
        r5 = false;
    L43:
        if (Build.VERSION.SDK_INT < 26) goto L88;
        if (r12 == false) goto L88;
        if (r10 == false) goto L49;
        if (r02 == null) goto L49;
        if (r02.isInPreviewMode() == true) goto L88;
    L49:
        if (r02 == null) goto L52;
        if (r10 == false) goto L52;
        int r102 = r02.getNavigationBarColor();
    L54:
        if ((r02 instanceof ChatActivity) == true) goto L56;
        r3 = 0;
    L61:
        if (getBottomSheetTabs() == null) goto L66;
        if (getBottomSheetTabs().getHeight(false) <= 0) goto L66;
        r3 = 0;
    L66:
        if (this.actionBarLayout.getSheetFragment(false) == null) goto L76;
        EmptyBaseFragment r122 = this.actionBarLayout.getSheetFragment(false);
        if (r122.sheetsStack == null) goto L76;
        int r03 = 0;
    L71:
        if (r03 >= r122.sheetsStack.size()) goto L76;
        BaseFragment.AttachedSheet r1 = r122.sheetsStack.get(r03);
        if (r1.attachedToParent() == false) goto L75;
        r102 = r1.getNavigationBarColor(r102);
        r3 = 0;
    L75:
        r03 = r03 + 1;
    L76:
        Iterator<BotWebViewSheet> r123 = BotWebViewSheet.activeSheets.iterator();
    L78:
        if (r123.hasNext() == false) goto L80;
        r102 = r123.next().getNavigationBarColor(r102);
        r3 = 0;
        goto L78
    L80:
        setNavigationBarColor(r102);
        if (r3 == 0) goto L83;
    L84:
        if (r3 == 1) goto L87;
        r2 = false;
    L87:
        AndroidUtilities.setLightNavigationBar(this, r2);
        goto L88
    L83:
        if (AndroidUtilities.computePerceivedBrightness(r102) >= 0.721f) goto L87;
    L56:
        if (((ChatActivity) r02).isShouldHaveLightNavigationBarIcons() == true) goto L61;
        r3 = 1;
    L52:
        r102 = Theme.getColor(Theme.key_windowBackgroundGray, null, true);
    L88:
        if (r11 == false) goto L95;
        getWindow().setStatusBarColor(0);
        return;
    L95:
        return;
    L9:
        if (r02.isRemovingFromStack() == true) goto L13;
        if (r02.isInPreviewMode() == false) goto L16;
    L13:
        if (this.mainFragmentsStack.size() <= 1) goto L15;
        ArrayList<BaseFragment> r04 = this.mainFragmentsStack;
        r02 = r04.get(r04.size() - 2);
        goto L16
    L15:
        r02 = null;
        goto L16
    L5:
        r02 = null;
        goto L6
    }

    public void clearFragments() {
        ArrayList<BaseFragment> r0 = this.mainFragmentsStack;
        int r1 = r0.size();
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L5;
        BaseFragment r4 = r0.get(r3);
        r3 = r3 + 1;
        r4.onFragmentDestroy();
        goto L3
    L5:
        this.mainFragmentsStack.clear();
        if (AndroidUtilities.isTablet() == false) goto L18;
        ArrayList<BaseFragment> r02 = this.layerFragmentsStack;
        int r12 = r02.size();
        int r32 = 0;
    L8:
        if (r32 >= r12) goto L10;
        BaseFragment r42 = r02.get(r32);
        r32 = r32 + 1;
        r42.onFragmentDestroy();
        goto L8
    L10:
        this.layerFragmentsStack.clear();
        ArrayList<BaseFragment> r03 = this.rightFragmentsStack;
        int r13 = r03.size();
    L11:
        if (r2 >= r13) goto L13;
        BaseFragment r33 = r03.get(r2);
        r2 = r2 + 1;
        r33.onFragmentDestroy();
        goto L11
    L13:
        this.rightFragmentsStack.clear();
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r21, int r22, Object... r23) {
        if (r21 != NotificationCenter.appDidLogout) goto L6;
        switchToAvailableAccountOrLogout();
        return;
    L6:
        boolean r8 = false;
        if (r21 != NotificationCenter.openBoostForUsersDialog) goto L15;
        Long r0 = (Long) r23[0];
        r0.longValue();
        if (r23.length <= 1) goto L11;
        ChatMessageCell r2 = (ChatMessageCell) r23[1];
    L12:
        processBoostDialog(r0, null, null, r2);
        return;
    L11:
        r2 = null;
        goto L12
    L15:
        if (r21 != NotificationCenter.closeOtherAppActivities) goto L21;
        if (r23[0] == this) goto L505;
        onFinish();
        finish();
        return;
    L505:
        return;
    L21:
        if (r21 != NotificationCenter.didUpdateConnectionState) goto L30;
        int r02 = ConnectionsManager.getInstance(r22).getConnectionState();
        if (this.currentConnectionState != r02) goto L25;
        return;
    L25:
        if (BuildVars.LOGS_ENABLED == false) goto L27;
        FileLog.d("switch to state " + r02);
    L27:
        this.currentConnectionState = r02;
        updateCurrentConnectionState();
        return;
    L30:
        if (r21 != NotificationCenter.mainUserInfoChanged) goto L34;
        syncDrawerContainerEnabled();
        return;
    L34:
        if (r21 != NotificationCenter.attachMenuBotsDidLoad) goto L37;
        return;
    L37:
        if (r21 != NotificationCenter.needShowAlert) goto L93;
        Integer r03 = (Integer) r23[0];
        if (r03.intValue() != 6) goto L41;
        return;
    L41:
        if (r03.intValue() != 3) goto L46;
        if (this.proxyErrorDialog == null) goto L46;
        return;
    L46:
        if (r03.intValue() != 4) goto L50;
        showTosActivity(r22, (TLRPC.TL_help_termsOfService) r23[1]);
        return;
    L50:
        if (this.mainFragmentsStack.isEmpty() == true) goto L52;
        BaseFragment r3 = this.actionBarLayout.getFragmentStack().get(this.actionBarLayout.getFragmentStack().size() - 1);
    L53:
        AlertDialog.Builder r14 = new AlertDialog.Builder(this);
        r14.setTitle(LocaleController.getString(R.string.AppName));
        if (r3 == null) goto L57;
        Map<String, Integer> r82 = new HashMap();
        int r10 = Theme.key_dialogTopBackground;
        r82.put("info1", Integer.valueOf(r3.getThemedColor(r10)));
        r82.put("info2", Integer.valueOf(r3.getThemedColor(r10)));
        r14.setTopAnimation(R.raw.not_available, 52, false, r3.getThemedColor(r10), r82);
        r14.setTopAnimationIsNew(true);
    L57:
        if (r03.intValue() == 2) goto L62;
        if (r03.intValue() == 3) goto L62;
        r14.setNegativeButton(LocaleController.getString(R.string.MoreInfo), new LaunchActivity$$ExternalSyntheticLambda4(this, r22));
    L62:
        if (r03.intValue() != 5) goto L65;
        r14.setMessage(LocaleController.getString(R.string.NobodyLikesSpam3));
        r14.setPositiveButton(LocaleController.getString(R.string.OK), null);
    L90:
        r14.show();
        return;
    L65:
        if (r03.intValue() != 0) goto L68;
        r14.setMessage(LocaleController.getString(R.string.NobodyLikesSpam1));
        r14.setPositiveButton(LocaleController.getString(R.string.OK), null);
        goto L90
    L68:
        if (r03.intValue() != 1) goto L71;
        r14.setMessage(LocaleController.getString(R.string.NobodyLikesSpam2));
        r14.setPositiveButton(LocaleController.getString(R.string.OK), null);
        goto L90
    L71:
        if (r03.intValue() != 2) goto L87;
        SpannableStringBuilder r04 = SpannableStringBuilder.valueOf((String) r23[1]);
        String r24 = (String) r23[2];
        if (r24.startsWith("PREMIUM_GIFT_SELF_REQUIRED_") == false) goto L79;
        String r32 = (String) r23[1];
        int r6 = r32.indexOf(42);
        int r83 = r6 + 1;
        int r4 = r32.indexOf(42, r83);
        if (r6 == (-1)) goto L79;
        if (r4 == (-1)) goto L79;
        if (r6 == r4) goto L79;
        r04.replace(r6, r4 + 1, r32.substring(r83, r4));
        r04.setSpan(new AnonymousClass16(this), r6, r4 - 1, 33);
    L79:
        r14.setMessage(r04);
        if (r24.startsWith("AUTH_KEY_DROP_") == false) goto L83;
        r14.setPositiveButton(LocaleController.getString(R.string.Cancel), null);
        r14.setNegativeButton(LocaleController.getString(R.string.LogOut), new LaunchActivity$$ExternalSyntheticLambda7(this));
        goto L90
    L83:
        if (r24.startsWith("PREMIUM_") == false) goto L85;
        r14.setTitle(LocaleController.getString(R.string.TelegramPremium));
        r14.setPositiveButton(LocaleController.getString(R.string.OK), null);
        goto L90
    L85:
        r14.setPositiveButton(LocaleController.getString(R.string.OK), null);
        goto L90
    L87:
        if (r03.intValue() != 3) goto L90;
        r14.setTitle(LocaleController.getString(R.string.Proxy));
        r14.setMessage(LocaleController.getString(R.string.UseProxyTelegramError));
        r14.setPositiveButton(LocaleController.getString(R.string.OK), null);
        this.proxyErrorDialog = showAlertDialog(r14);
        return;
    L52:
        r3 = null;
        goto L53
    L93:
        if (r21 != NotificationCenter.wasUnableToFindCurrentLocation) goto L99;
        HashMap r05 = (HashMap) r23[0];
        AlertDialog.Builder r33 = new AlertDialog.Builder(this);
        r33.setTitle(LocaleController.getString(R.string.AppName));
        r33.setPositiveButton(LocaleController.getString(R.string.OK), null);
        r33.setNegativeButton(LocaleController.getString(R.string.ShareYouLocationUnableManually), new LaunchActivity$$ExternalSyntheticLambda8(this, r05, r22));
        r33.setMessage(LocaleController.getString(R.string.ShareYouLocationUnable));
        if (this.mainFragmentsStack.isEmpty() == true) goto L508;
        ArrayList<BaseFragment> r06 = this.mainFragmentsStack;
        r06.get(r06.size() - 1).showDialog(r33.create());
        return;
    L508:
        return;
    L99:
        if (r21 != NotificationCenter.didSetNewWallpapper) goto L105;
        SizeNotifierFrameLayout r07 = this.backgroundTablet;
        if (r07 == null) goto L509;
        r07.setBackgroundImage(Theme.getCachedWallpaper(), Theme.isWallpaperMotion());
        return;
    L509:
        return;
    L105:
        if (r21 != NotificationCenter.didSetPasscode) goto L109;
        this.flagSecureReason.invalidate();
        return;
    L109:
        if (r21 != NotificationCenter.reloadInterface) goto L124;
        if (this.mainFragmentsStack.size() <= 1) goto L115;
        ArrayList<BaseFragment> r08 = this.mainFragmentsStack;
        if ((r08.get(r08.size() - 1) instanceof ProfileActivity) == false) goto L115;
        boolean r09 = true;
    L116:
        if (r09 == false) goto L120;
        ArrayList<BaseFragment> r25 = this.mainFragmentsStack;
        if (((ProfileActivity) r25.get(r25.size() - 1)).isSettings() == true) goto L120;
    L121:
        rebuildAllFragments(r8);
        return;
    L120:
        r8 = r09;
    L115:
        r09 = false;
        goto L116
    L124:
        if (r21 != NotificationCenter.suggestedLangpack) goto L128;
        showLanguageAlert(false);
        return;
    L128:
        if (r21 != NotificationCenter.openArticle) goto L142;
        if (this.mainFragmentsStack.isEmpty() == true) goto L530;
        LaunchActivity r010 = instance;
        if (r010 != null) goto L135;
    L139:
        ArrayList<BaseFragment> r011 = this.mainFragmentsStack;
        r011.get(r011.size() - 1).createArticleViewer(false).open((TLRPC.TL_webPage) r23[0], (String) r23[1]);
        return;
    L135:
        if (r010.getBottomSheetTabs() == null) goto L139;
        if (instance.getBottomSheetTabs().tryReopenTab((TLRPC.TL_webPage) r23[0]) == null) goto L139;
        return;
    L530:
        return;
    L142:
        if (r21 != NotificationCenter.hasNewContactsToImport) goto L151;
        ActionBarLayout r012 = this.actionBarLayout;
        if (r012 != null) goto L146;
        return;
    L146:
        if (r012.getFragmentStack().isEmpty() == true) goto L532;
        HashMap r013 = (HashMap) r23[1];
        boolean r34 = ((Boolean) r23[2]).booleanValue();
        boolean r42 = ((Boolean) r23[3]).booleanValue();
        BaseFragment r5 = this.actionBarLayout.getFragmentStack().get(this.actionBarLayout.getFragmentStack().size() - 1);
        AlertDialog.Builder r62 = new AlertDialog.Builder(this);
        r62.setTopAnimation(R.raw.permission_request_contacts, 72, false, Theme.getColor(Theme.key_dialogTopBackground));
        r62.setTitle(LocaleController.getString(R.string.UpdateContactsTitle));
        r62.setMessage(LocaleController.getString(R.string.UpdateContactsMessage));
        r62.setPositiveButton(LocaleController.getString(R.string.OK), new LaunchActivity$$ExternalSyntheticLambda9(r22, r013, r34, r42));
        r62.setNegativeButton(LocaleController.getString(R.string.Cancel), new LaunchActivity$$ExternalSyntheticLambda10(r22, r013, r34, r42));
        r62.setOnBackButtonListener(new LaunchActivity$$ExternalSyntheticLambda11(r22, r013, r34, r42));
        AlertDialog r014 = r62.create();
        r5.showDialog(r014);
        r014.setCanceledOnTouchOutside(false);
        return;
    L532:
        return;
    L151:
        if (r21 != NotificationCenter.didSetNewTheme) goto L173;
        if (((Boolean) r23[0]).booleanValue() == true) goto L156;
        setTaskDescription(new ActivityManager.TaskDescription(null, null, Theme.getColor(Theme.key_actionBarDefault) | (-16777216)));     // Catch: Exception -> L496
    L156:
        if (r23.length <= 1) goto L158;
        boolean r015 = ((Boolean) r23[1]).booleanValue();
    L160:
        if (r23.length > 2) goto L162;
    L164:
        boolean r26 = false;
    L165:
        if (r015 == true) goto L167;
    L171:
        checkSystemBarColors(r26, true, r8);
        return;
    L167:
        if (this.isNavigationBarColorFrozen == true) goto L171;
        if (this.actionBarLayout.isTransitionAnimationInProgress() == true) goto L171;
        r8 = true;
        goto L171
    L162:
        if (((Boolean) r23[2]).booleanValue() == false) goto L164;
        r26 = true;
        goto L165
    L158:
        r015 = true;
        goto L160
    L173:
        if (r21 != NotificationCenter.needSetDayNightTheme) goto L273;
        if (r23[2] != null) goto L177;
        DialogsActivity.switchingTheme = false;
    L256:
        boolean r016 = false;
    L257:
        Theme.ThemeInfo r35 = (Theme.ThemeInfo) r23[0];
        boolean r52 = ((Boolean) r23[1]).booleanValue();
        int r43 = ((Integer) r23[3]).intValue();
        if (r23.length <= 7) goto L260;
        Runnable r7 = (Runnable) r23[7];
    L261:
        ActionBarLayout r27 = this.actionBarLayout;
        if (r27 == null) goto L534;
        boolean r63 = r016;
        r27.animateThemedValues(r35, r43, r52, r63, r7);
        if (AndroidUtilities.isTablet() == false) goto L512;
        ActionBarLayout r017 = this.layersActionBarLayout;
        if (r017 == null) goto L269;
        r017.animateThemedValues(r35, r43, r52, r63);
    L269:
        ActionBarLayout r018 = this.rightActionBarLayout;
        if (r018 == null) goto L513;
        r018.animateThemedValues(r35, r43, r52, r63);
        return;
    L513:
        return;
    L512:
        return;
    L534:
        return;
    L260:
        r7 = null;
        goto L261
    L177:
        if (this.themeSwitchImageView.getVisibility() == 0) goto L533;
        int[] r17 = (int[]) r23[2];     // Catch: Throwable -> L182
        boolean r019 = ((Boolean) r23[4]).booleanValue();     // Catch: Throwable -> L182
        RLottieImageView r36 = (RLottieImageView) r23[5];     // Catch: Throwable -> L182
        if (r23.length <= 8) goto L185;
        ((Boolean) r23[8]).getClass();     // Catch: Throwable -> L182
    L185:
        if (r23.length <= 9) goto L189;
        Object r28 = r23[9];     // Catch: Throwable -> L182
        if ((r28 instanceof RLottieDrawable) == false) goto L189;
        RLottieDrawable r29 = (RLottieDrawable) r28;     // Catch: Throwable -> L182
    L190:
        int r12 = this.drawerLayoutContainer.getMeasuredWidth();     // Catch: Throwable -> L182
        int r13 = this.drawerLayoutContainer.getMeasuredHeight();     // Catch: Throwable -> L182
        if (r019 == true) goto L194;
        if (r36 == null) goto L194;
        r36.setImageDrawable(null);     // Catch: Throwable -> L182
        r36.setVisibility(4);     // Catch: Throwable -> L182
        r36.invalidate();     // Catch: Throwable -> L182
    L194:
        this.rippleAbove = null;     // Catch: Throwable -> L182
        if (r23.length <= 6) goto L197;
        this.rippleAbove = (View) r23[6];     // Catch: Throwable -> L182
    L197:
        this.isNavigationBarColorFrozen = true;     // Catch: Throwable -> L182
        invalidateCachedViews(this.drawerLayoutContainer);     // Catch: Throwable -> L182
        View r102 = this.rippleAbove;     // Catch: Throwable -> L182
        if (r102 == null) goto L203;
        if (r102.getBackground() == null) goto L203;
        this.rippleAbove.getBackground().setAlpha(0);     // Catch: Throwable -> L182
    L203:
        if (Build.VERSION.SDK_INT < 26) goto L207;
        if (r019 == true) goto L206;
        if (r36 != null) goto L207;
    L206:
        Bitmap r103 = AndroidUtilities.getBitmapFromWindow(getWindow());     // Catch: Throwable -> L182
    L208:
        if (r103 != null) goto L210;
        r103 = AndroidUtilities.snapshotView(this.drawerLayoutContainer);     // Catch: Throwable -> L182
    L210:
        View r11 = this.rippleAbove;     // Catch: Throwable -> L182
        if (r11 != null) goto L213;
    L215:
        this.frameLayout.removeView(this.themeSwitchImageView);     // Catch: Throwable -> L182
        this.themeSwitchImageView = new ImageView(this);     // Catch: Throwable -> L182
        if (r36 == null) goto L218;
        RLottieDrawable r72 = r36.getAnimatedDrawable();     // Catch: Throwable -> L182
    L219:
        if (r29 == null) goto L221;
        RLottieDrawable r112 = r29;
    L222:
        this.themeSwitchSunView.setImageDrawable(r112);     // Catch: Throwable -> L182
        FrameLayout r113 = this.frameLayout;
        if (r019 == false) goto L226;
        r113.addView(this.themeSwitchImageView, 0, LayoutHelper.createFrame(-1, -1.0f));     // Catch: Throwable -> L182
        this.themeSwitchSunView.setVisibility(8);     // Catch: Throwable -> L182
    L230:
        this.themeSwitchImageView.setImageBitmap(r103);     // Catch: Throwable -> L182
        this.themeSwitchImageView.setVisibility(0);     // Catch: Throwable -> L182
        int r44 = r17[0];     // Catch: Throwable -> L182
        int r53 = (r12 - r44) * (r12 - r44);     // Catch: Throwable -> L182
        int r45 = r17[1];     // Catch: Throwable -> L182
        double r46 = Math.sqrt(r53 + ((r13 - r45) * (r13 - r45)));     // Catch: Throwable -> L182
        int r104 = r17[0];     // Catch: Throwable -> L182
        int r114 = r17[1];     // Catch: Throwable -> L182
        float r47 = (float) Math.max(r46, Math.sqrt((r104 * r104) + ((r13 - r114) * (r13 - r114))));     // Catch: Throwable -> L182
        int r54 = r17[0];     // Catch: Throwable -> L182
        int r105 = (r12 - r54) * (r12 - r54);     // Catch: Throwable -> L182
        int r55 = r17[1];     // Catch: Throwable -> L182
        double r106 = Math.sqrt(r105 + (r55 * r55));     // Catch: Throwable -> L182
        int r56 = r17[0];     // Catch: Throwable -> L182
        int r122 = r17[1];     // Catch: Throwable -> L182
        float r48 = Math.max(r47, (float) Math.max(r106, Math.sqrt((r56 * r56) + (r122 * r122))));     // Catch: Throwable -> L182
        if (r019 == false) goto L233;
        View r57 = this.drawerLayoutContainer;     // Catch: Throwable -> L182
    L234:
        int r107 = r17[0];     // Catch: Throwable -> L182
        int r115 = r17[1];     // Catch: Throwable -> L182
        if (r019 == false) goto L237;
        float r142 = 0.0f;
    L238:
        if (r019 == true) goto L241;
        r48 = 0.0f;
    L241:
        Animator r108 = ViewAnimationUtils.createCircularReveal(r57, r107, r115, r142, r48);     // Catch: Throwable -> L182
        r108.setDuration(400);     // Catch: Throwable -> L182
        r108.setInterpolator(Easings.easeInOutQuad);     // Catch: Throwable -> L182
        r108.addListener(new AnonymousClass17(this, r29, r36, r72, r019));     // Catch: Throwable -> L182
        if (this.rippleAbove == null) goto L244;
        ValueAnimator r020 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});     // Catch: Throwable -> L182
        r020.addUpdateListener(new LaunchActivity$$ExternalSyntheticLambda12(this));     // Catch: Throwable -> L182
        r020.setDuration(r108.getDuration());     // Catch: Throwable -> L182
        r020.start();     // Catch: Throwable -> L182
    L244:
        LaunchActivity$$ExternalSyntheticLambda13 r021 = new LaunchActivity$$ExternalSyntheticLambda13(this);     // Catch: Throwable -> L182
        if (r019 == false) goto L247;
        long r210 = (r13 - r17[1]) / AndroidUtilities.dp(2.25f);     // Catch: Throwable -> L182
    L248:
        AndroidUtilities.runOnUIThread(r021, r210);     // Catch: Throwable -> L182
        r108.start();     // Catch: Throwable -> L182
        r016 = true;
        goto L257
    L247:
        r210 = 50;
        goto L248
    L237:
        r142 = r48;
        goto L238
    L233:
        r57 = this.themeSwitchImageView;     // Catch: Throwable -> L182
        goto L234
    L226:
        r113.addView(this.themeSwitchImageView, 1, LayoutHelper.createFrame(-1, -1.0f));     // Catch: Throwable -> L182
        this.themeSwitchSunView.setTranslationX(r17[0] - AndroidUtilities.dp(24.0f));     // Catch: Throwable -> L182
        this.themeSwitchSunView.setTranslationY(r17[1] - AndroidUtilities.dp(24.0f));     // Catch: Throwable -> L182
        this.themeSwitchSunView.setVisibility(0);     // Catch: Throwable -> L182
        if (r29 == null) goto L229;
        r29.start();     // Catch: Throwable -> L182
    L229:
        this.themeSwitchSunView.invalidate();     // Catch: Throwable -> L182
        goto L230
    L221:
        r112 = r72;
        goto L222
    L218:
        r72 = null;
        goto L219
    L213:
        if (r11.getBackground() == null) goto L215;
        this.rippleAbove.getBackground().setAlpha(255);     // Catch: Throwable -> L182
    L207:
        r103 = null;
    L189:
        r29 = null;
    L182:
        th = move-exception;
        FileLog.e(th);
        this.themeSwitchImageView.setImageDrawable(null);     // Catch: Exception -> L253
        this.frameLayout.removeView(this.themeSwitchImageView);     // Catch: Exception -> L253
        DialogsActivity.switchingTheme = false;     // Catch: Exception -> L253
    L253:
        e = move-exception;
        FileLog.e(e);
        goto L256
    L533:
        return;
    L273:
        if (r21 != NotificationCenter.needShowPlayServicesAlert) goto L277;
        ((Status) r23[0]).startResolutionForResult(this, 140);     // Catch: Throwable -> L495
        return;
    L538:
        return;
    L277:
        if (r21 != NotificationCenter.fileLoaded) goto L306;
        String r022 = (String) r23[0];
        String r211 = this.loadingThemeFileName;
        if (r211 != null) goto L281;
        String r212 = this.loadingThemeWallpaperName;
        if (r212 != null) goto L298;
        return;
    L298:
        if (r212.equals(r022) == false) goto L517;
        this.loadingThemeWallpaperName = null;
        File r023 = (File) r23[1];
        if (this.loadingThemeAccent == false) goto L303;
        openThemeAccentPreview(this.loadingTheme, this.loadingThemeWallpaper, this.loadingThemeInfo);
        onThemeLoadFinish();
        return;
    L303:
        Utilities.globalQueue.postRunnable(new LaunchActivity$$ExternalSyntheticLambda15(this, this.loadingThemeInfo, r023));
        return;
    L517:
        return;
    L281:
        if (r211.equals(r022) == false) goto L515;
        this.loadingThemeFileName = null;
        File r024 = new File(ApplicationLoader.getFilesDirFixed(), "remote" + this.loadingTheme.id + ".attheme");
        TLRPC.TL_theme r213 = this.loadingTheme;
        Theme.ThemeInfo r214 = Theme.fillThemeValues(r024, r213.title, r213);
        if (r214 != null) goto L285;
    L293:
        onThemeLoadFinish();
        return;
    L285:
        if (r214.pathToWallpaper != null) goto L287;
    L290:
        TLRPC.TL_theme r215 = this.loadingTheme;
        Theme.ThemeInfo r116 = Theme.applyThemeFile(r024, r215.title, r215, true);
        if (r116 == null) goto L293;
        presentFragment(new ThemePreviewActivity(r116, true, 0, false, false));
        goto L293
    L287:
        if (new File(r214.pathToWallpaper).exists() == true) goto L290;
        TL_account.getWallPaper r025 = new TL_account.getWallPaper();
        TLRPC.TL_inputWallPaperSlug r37 = new TLRPC.TL_inputWallPaperSlug();
        r37.slug = r214.slug;
        r025.wallpaper = r37;
        ConnectionsManager.getInstance(r214.account).sendRequest(r025, new LaunchActivity$$ExternalSyntheticLambda14(this, r214));
        return;
    L515:
        return;
    L306:
        if (r21 != NotificationCenter.fileLoadFailed) goto L314;
        String r026 = (String) r23[0];
        if (r026.equals(this.loadingThemeFileName) == false) goto L310;
    L311:
        onThemeLoadFinish();
        return;
    L310:
        if (r026.equals(this.loadingThemeWallpaperName) == true) goto L311;
        return;
    L314:
        if (r21 != NotificationCenter.screenStateChanged) goto L325;
        Choreographer60FpsContent.onApplicationStateChanged();
        if (ApplicationLoader.mainInterfacePaused == false) goto L319;
        return;
    L319:
        if (ApplicationLoader.isScreenOn == false) goto L322;
        onPasscodeResume();
        return;
    L322:
        onPasscodePause();
        return;
    L325:
        if (r21 != NotificationCenter.needCheckSystemBarColors) goto L334;
        if (r23.length > 0) goto L329;
    L331:
        checkSystemBarColors(r8);
        return;
    L329:
        if (((Boolean) r23[0]).booleanValue() == false) goto L331;
        r8 = true;
        goto L331
    L334:
        if (r21 != NotificationCenter.historyImportProgressChanged) goto L342;
        if (r23.length > 1) goto L338;
        return;
    L338:
        if (this.mainFragmentsStack.isEmpty() == true) goto L520;
        int r027 = this.currentAccount;
        TLRPC.TL_error r216 = (TLRPC.TL_error) r23[2];
        ArrayList<BaseFragment> r1 = this.mainFragmentsStack;
        AlertsCreator.processError(r027, r216, r1.get(r1.size() - 1), (TLObject) r23[1], new Object[0]);
        return;
    L520:
        return;
    L342:
        if (r21 != NotificationCenter.billingConfirmPurchaseError) goto L346;
        int r028 = this.currentAccount;
        TLRPC.TL_error r217 = (TLRPC.TL_error) r23[1];
        ArrayList<BaseFragment> r15 = this.mainFragmentsStack;
        AlertsCreator.processError(r028, r217, r15.get(r15.size() - 1), (TLObject) r23[0], new Object[0]);
        return;
    L346:
        if (r21 != NotificationCenter.stickersImportComplete) goto L354;
        MediaDataController r029 = MediaDataController.getInstance(r22);
        TLObject r218 = (TLObject) r23[0];
        if (this.mainFragmentsStack.isEmpty() == true) goto L350;
        ArrayList<BaseFragment> r38 = this.mainFragmentsStack;
        BaseFragment r49 = r38.get(r38.size() - 1);
    L351:
        r029.toggleStickerSet(this, r218, 2, r49, false, true);
        return;
    L350:
        r49 = null;
        goto L351
    L354:
        if (r21 != NotificationCenter.showBulletin) goto L436;
        if (this.mainFragmentsStack.isEmpty() == true) goto L521;
        int r030 = ((Integer) r23[0]).intValue();
        if (GroupCallActivity.groupCallUiVisible == false) goto L362;
        GroupCallActivity r219 = GroupCallActivity.groupCallInstance;
        if (r219 == null) goto L362;
        BottomSheet.ContainerView r73 = r219.getContainer();
    L363:
        if (r73 != null) goto L365;
        ArrayList<BaseFragment> r220 = this.mainFragmentsStack;
        BaseFragment r84 = r220.get(r220.size() - 1);
    L366:
        if (r030 == 0) goto L423;
        if (r030 != 1) goto L369;
        if (r84 == null) goto L421;
        BulletinFactory.of(r84).createErrorBulletin((String) r23[1]).show();
        return;
    L421:
        BulletinFactory.of(r73, null).createErrorBulletin((String) r23[1]).show();
        return;
    L369:
        if (r030 == 2) goto L409;
        if (r030 == 3) goto L399;
        if (r030 == 4) goto L393;
        if (r030 == 6) goto L388;
        switch(r030) {
            case 10: goto L376;
            case 11: goto L376;
            case 12: goto L376;
            default: goto L536;
        };
    L376:
        if (r030 != 10) goto L379;
        BulletinFactory.FileType r031 = BulletinFactory.FileType.STICKER;
    L382:
        if (r84 == null) goto L385;
        BulletinFactory.of(r84).createDownloadBulletin(r031).show();
        return;
    L385:
        BulletinFactory.of(r73, null).createDownloadBulletin(r031).show();
        return;
    L379:
        if (r030 != 11) goto L381;
        r031 = BulletinFactory.FileType.EMOJI;
        goto L382
    L381:
        r031 = BulletinFactory.FileType.GIF_TO_GALLERY;
        goto L382
    L536:
        return;
    L388:
        if (r84 == null) goto L391;
        BulletinFactory.of(r84).createSuccessBulletin((String) r23[1]).show();
        return;
    L391:
        BulletinFactory.of(r73, null).createSuccessBulletin((String) r23[1]).show();
        return;
    L393:
        if (r84 == null) goto L396;
        BulletinFactory.of(r84).createErrorBulletinSubtitle((String) r23[1], (String) r23[2], r84.getResourceProvider()).show();
        return;
    L396:
        BulletinFactory.of(r73, null).createErrorBulletinSubtitle((String) r23[1], (String) r23[2], null).show();
        return;
    L399:
        if (((Long) r23[1]).longValue() <= 0) goto L402;
        int r032 = R.string.YourNameChanged;
    L401:
        String r033 = LocaleController.getString(r032);
        if (r73 == null) goto L405;
        BulletinFactory r16 = BulletinFactory.of(r73, null);
    L406:
        r16.createErrorBulletin(r033).show();
        return;
    L405:
        r16 = BulletinFactory.of(r84);
        goto L406
    L402:
        r032 = R.string.ChannelTitleChanged;
        goto L401
    L409:
        if (((Long) r23[1]).longValue() <= 0) goto L412;
        int r034 = R.string.YourBioChanged;
    L411:
        String r035 = LocaleController.getString(r034);
        if (r73 == null) goto L415;
        BulletinFactory r18 = BulletinFactory.of(r73, null);
    L416:
        r18.createErrorBulletin(r035).show();
        return;
    L415:
        r18 = BulletinFactory.of(r84);
        goto L416
    L412:
        r034 = R.string.ChannelDescriptionChanged;
        goto L411
    L423:
        TLRPC.Document r410 = (TLRPC.Document) r23[1];
        int r39 = ((Integer) r23[2]).intValue();
        StickerSetBulletinLayout r036 = new StickerSetBulletinLayout(this, null, r39, r410, null);
        if (r39 != 6) goto L426;
    L429:
        int r19 = 3500;
    L430:
        if (r84 == null) goto L433;
        Bulletin.make(r84, r036, r19).show();
        return;
    L433:
        Bulletin.make(r73, r036, r19).show();
        return;
    L426:
        if (r39 == 7) goto L429;
        r19 = 1500;
        goto L430
    L365:
        r84 = null;
    L362:
        r73 = null;
        goto L363
    L521:
        return;
    L436:
        if (r21 != NotificationCenter.groupCallUpdated) goto L440;
        checkWasMutedByAdmin(false);
        return;
    L440:
        if (r21 != NotificationCenter.currentUserShowLimitReachedDialog) goto L448;
        if (this.mainFragmentsStack.isEmpty() == true) goto L522;
        ArrayList<BaseFragment> r037 = this.mainFragmentsStack;
        BaseFragment r221 = r037.get(r037.size() - 1);
        if (r221.getParentActivity() == null) goto L523;
        r221.showDialog(new LimitReachedBottomSheet(r221, r221.getParentActivity(), ((Integer) r23[0]).intValue(), this.currentAccount, null));
        return;
    L523:
        return;
    L522:
        return;
    L448:
        if (r21 != NotificationCenter.currentUserPremiumStatusChanged) goto L452;
        MessagesController.getMainSettings(this.currentAccount).edit().remove("transcribeButtonPressed").apply();
        return;
    L452:
        if (r21 != NotificationCenter.requestPermissions) goto L463;
        int r038 = ((Integer) r23[0]).intValue();
        if (r038 == 0) goto L456;
    L458:
        String[] r74 = null;
    L459:
        if (r74 == null) goto L524;
        int r222 = this.requsetPermissionsPointer + 1;
        this.requsetPermissionsPointer = r222;
        this.requestedPermissions.put(r222, r038);
        ActivityCompat.requestPermissions(this, r74, this.requsetPermissionsPointer);
        return;
    L524:
        return;
    L456:
        if (Build.VERSION.SDK_INT < 31) goto L458;
        r74 = new String[]{"android.permission.BLUETOOTH_CONNECT"};
        goto L459
    L463:
        if (r21 != NotificationCenter.chatSwitchedForum) goto L469;
        long r223 = ((Long) r23[0]).longValue();
        if (((Boolean) r23[1]).booleanValue() == false) goto L525;
        ForumUtilities.switchAllFragmentsInStackToForum(r223, this.actionBarLayout);
        return;
    L525:
        return;
    L469:
        if (r21 != NotificationCenter.tlSchemeParseException) goto L472;
        return;
    L472:
        if (r21 != NotificationCenter.memoryLeakFoundException) goto L478;
        if (this.memoryLeakErrorAlertDialog != null) goto L526;
        Class r039 = (Class) r23[0];
        int r224 = ((Integer) r23[1]).intValue();
        StringBuilder r411 = new StringBuilder();
        r411.append(r039.toString());
        r411.append('\n');
        r411.append(r224);
        r411.append('\n');
        r411.append(AndroidUtilities.getBuildVersionInfo());
        AlertDialog.Builder r040 = new AlertDialog.Builder(this, null);
        r040.setTitle("Memory Leak Found");
        r040.setMessage(r411);
        r040.setPositiveButton(LocaleController.getString(R.string.OK), null);
        r040.setOnDismissListener(new LaunchActivity$$ExternalSyntheticLambda5(this));
        AlertDialog r041 = r040.show();
        this.memoryLeakErrorAlertDialog = r041;
        r041.setCanceledOnTouchOutside(true);
        return;
    L526:
        return;
    L478:
        if (r21 != NotificationCenter.guardBotDecisionResult) goto L527;
        BotGuardHelper.GuardBotDecisionResultNotification r042 = (BotGuardHelper.GuardBotDecisionResultNotification) r23[0];
        BaseFragment r310 = getLastFragment();
        if ((r310 instanceof ChatActivity) == false) goto L482;
        BulletinFactory r311 = BulletinFactory.of(r310);
    L483:
        String r412 = DialogObject.getShortName(this.currentAccount, r042.dialogId);
        TLRPC.JoinChatBotResult r043 = r042.result;
        if ((r043 instanceof TLRPC.TL_joinChatBotResultApproved) == false) goto L487;
        Bulletin r75 = r311.createSimpleBulletin(R.raw.contact_check, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GuardBotJoinRequestApproved, new Object[]{r412})));
    L493:
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda6(this, r75), 400);
        return;
    L487:
        if ((r043 instanceof TLRPC.TL_joinChatBotResultDeclined) == false) goto L490;
        r75 = r311.createSimpleBulletin(R.raw.e_hand_2, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GuardBotJoinRequestDeclined, new Object[]{r412})));
        goto L493
    L490:
        if ((r043 instanceof TLRPC.TL_joinChatBotResultQueued) == false) goto L492;
        r75 = r311.createSimpleBulletinWithIconSize(R.raw.timer_toast, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GuardBotJoinRequestQueued, new Object[]{r412})), 24);
        goto L493
    L492:
        r75 = null;
        goto L493
    L482:
        r311 = BulletinFactory.global();
        goto L483
    }

    /* JADX WARN: Type inference failed for: r10v20 */
    /* JADX WARN: Type inference failed for: r10v26, types: [android.net.Uri, java.lang.CharSequence, java.lang.String, java.util.ArrayList<java.lang.String>, java.util.ArrayList<org.telegram.messenger.SendMessagesHelper$SendingMediaInfo>, java.util.ArrayList<org.telegram.tgnet.TLRPC$User>] */
    /* JADX WARN: Type inference failed for: r10v27 */
    @Override // org.telegram.ui.DialogsActivity.DialogsActivityDelegate
    public boolean didSelectDialogs(DialogsActivity r45, ArrayList<MessagesStorage.TopicKey> r46, CharSequence r47, boolean r48, boolean r49, int r50, int r51, TopicsFragment r52) {
        if (r45 == null) goto L6;
        int r0 = r45.getCurrentAccount();
    L5:
        int r7 = r0;
        Uri r6 = this.exportingChatUri;
        if (r6 != null) goto L10;
        DialogsActivity r9 = r45;
        if (r9 != null) goto L20;
    L24:
        boolean r8 = true;
    L25:
        if (r50 == 0) goto L27;
        int r26 = r50;
    L31:
        if (r46.size() > 1) goto L46;
        long r4 = r46.get(0).dialogId;
        Bundle r02 = new Bundle();
        r02.putBoolean("scrollToTopOnResume", true);
        if (AndroidUtilities.isTablet() == true) goto L36;
        NotificationCenter.getInstance(r7).postNotificationName(NotificationCenter.closeChats, new Object[0]);
    L36:
        if (DialogObject.isEncryptedDialog(r4) == false) goto L39;
        r02.putInt("enc_id", DialogObject.getEncryptedChatId(r4));
    L43:
        if (MessagesController.getInstance(r7).checkCanOpenChat(r02, r9) == true) goto L45;
        return false;
    L45:
        ChatActivity r42 = new ChatActivity(r02);
        ForumUtilities.applyTopic(r42, r46.get(0));
        ChatActivity r15 = r42;
    L47:
        ArrayList<TLRPC.User> r03 = this.contactsToSend;
        if (r03 == null) goto L50;
        int r04 = r03.size();
    L52:
        if (this.videoPath == null) goto L55;
        r04 = r04 + 1;
    L55:
        if (this.voicePath == null) goto L57;
        r04 = r04 + 1;
    L57:
        ArrayList<SendMessagesHelper.SendingMediaInfo> r43 = this.photoPathsArray;
        if (r43 == null) goto L60;
        r04 = r04 + r43.size();
    L60:
        ArrayList<String> r44 = this.documentsPathsArray;
        if (r44 == null) goto L63;
        r04 = r04 + r44.size();
    L63:
        ArrayList<Uri> r410 = this.documentsUrisArray;
        if (r410 == null) goto L67;
        r04 = r04 + r410.size();
    L67:
        if (this.videoPath == null) goto L69;
    L79:
        int r411 = 0;
    L81:
        if (r411 >= r46.size()) goto L90;
        long r5 = r46.get(r411).dialogId;
        int r72 = this.currentAccount;
        if (r04 <= 1) goto L85;
        boolean r10 = true;
    L87:
        if (AlertsCreator.checkSlowMode(this, r72, r5, r10) == true) goto L88;
        r411 = r411 + 1;
        goto L81
    L88:
        return false;
    L85:
        r10 = false;
        goto L87
    L90:
        if (r52 == null) goto L92;
        r52.removeSelfFromStack();
    L92:
        ArrayList<TLRPC.User> r05 = this.contactsToSend;
        if (r05 != null) goto L95;
    L100:
        LaunchActivity r06 = this;
        ArrayList<MessagesStorage.TopicKey> r1 = r46;
        if (TextUtils.isEmpty(r47) == true) goto L103;
        r06.sendingText = r47;
        CharSequence r41 = null;
    L104:
        if (r9 != null) goto L106;
    L108:
        boolean r422 = false;
    L109:
        if (r422 == false) goto L128;
        ArrayList<SendMessagesHelper.SendingMediaInfo> r2 = buildSendingInfosFromEntries(r9.getSharedMediaEntries());
        r06.photoPathsArray = r2;
        if (r2.isEmpty() == true) goto L127;
        CharSequence r22 = r06.sendingText;
        if (r22 != null) goto L115;
        r22 = _UrlKt.FRAGMENT_ENCODE_SET;
    L115:
        CharSequence[] r3 = {r22};
        ArrayList<TLRPC.MessageEntity> r23 = MediaDataController.getInstance(r06.currentAccount).getEntities(r3, false);
        CharSequence r32 = r3[0];
        if (r32 != null) goto L118;
        String r33 = null;
    L119:
        int r412 = 0;
    L121:
        if (r412 >= r06.photoPathsArray.size()) goto L127;
        SendMessagesHelper.SendingMediaInfo r53 = r06.photoPathsArray.get(r412);
        if (r412 != 0) goto L125;
        r53.caption = r33;
        r53.entities = r23;
    L126:
        r412 = r412 + 1;
        goto L121
    L125:
        r53.caption = null;
        r53.entities = null;
        goto L126
    L118:
        r33 = r32.toString();
    L127:
        r06.videoPath = null;
        r06.sendingText = null;
    L128:
        CharSequence r413 = null;
        int r24 = 0;
        boolean r34 = false;
    L130:
        if (r24 >= r1.size()) goto L99;
        long r54 = r1.get(r24).dialogId;
        long r11 = r1.get(r24).topicId;
        AccountInstance r73 = AccountInstance.getInstance(UserConfig.selectedAccount);
        if (r11 == 0) goto L138;
        int r472 = r24;
        TLRPC.TL_forumTopic r12 = r73.getMessagesController().getTopicsController().findTopic(-r54, r11);
        if (r12 != null) goto L136;
    L139:
        MessageObject r222 = null;
    L141:
        if (r15 == null) goto L209;
        if (r9 != null) goto L144;
    L151:
        boolean r17 = true;
    L152:
        INavigationLayout r14 = r06.getActionBarLayout();
        if (r9 == null) goto L155;
        boolean r16 = true;
    L156:
        r14.presentFragment(r15, r16, r17, true, false);
        ChatActivity r25 = r15;
        if (r9 == null) goto L159;
        r34 = true;
    L160:
        String r142 = r06.videoPath;
        if (r142 == null) goto L165;
        if (r11 != 0) goto L165;
        if (r422 == true) goto L165;
        r25.openVideoEditor(r142, r06.sendingText);
        r06.sendingText = null;
        long r20 = r54;
        AccountInstance r143 = r73;
        boolean r102 = false;
        boolean r55 = true;
    L207:
        boolean r37 = r55;
        boolean r38 = r102;
    L208:
        boolean r36 = r34;
        if (r06.documentsPathsArray == null) goto L235;
    L239:
        CharSequence r35 = r06.sendingText;
        if (r35 != null) goto L242;
    L254:
        CharSequence r18 = r413;
        int r28 = r26;
        SendMessagesHelper.prepareSendingDocuments(r143, r06.documentsPathsArray, r06.documentsOriginalPathsArray, r06.documentsUrisArray, r18, r06.documentsMimeType, r20, r222, r222, null, null, null, r8, r28, null, null, 0, false, 0);
        r26 = r28;
        CharSequence r322 = r18;
    L238:
        AccountInstance r13 = r143;
        if (r06.voicePath == null) goto L269;
        File r39 = new File(r06.voicePath);
        if (r39.exists() == false) goto L269;
        TLRPC.TL_document r144 = new TLRPC.TL_document();
        r144.file_reference = new byte[0];
        r144.dc_id = Integer.MIN_VALUE;
        r144.id = SharedConfig.getLastLocalId();
        r144.user_id = r13.getUserConfig().getClientUserId();
        r144.mime_type = "audio/ogg";
        r144.date = r13.getConnectionsManager().getCurrentTime();
        r144.size = (int) r39.length();
        TLRPC.TL_documentAttributeAudio r414 = new TLRPC.TL_documentAttributeAudio();
        r414.voice = true;
        byte[] r56 = MediaController.getWaveform(r39.getAbsolutePath());
        r414.waveform = r56;
        if (r56 == null) goto L262;
        r414.flags |= 4;
    L262:
        r144.attributes.add(r414);
        CharSequence[] r57 = {r06.sendingText};
        ArrayList<TLRPC.MessageEntity> r415 = MediaDataController.getInstance(r06.currentAccount).getEntities(r57, false);
        SendMessagesHelper r62 = r13.getSendMessagesHelper();
        String r162 = r39.getAbsolutePath();
        CharSequence r310 = r57[0];
        if (r310 != null) goto L265;
        String r311 = null;
    L266:
        long r172 = r20;
        MessageObject r19 = r222;
        SendMessagesHelper.SendMessageParams r312 = SendMessagesHelper.SendMessageParams.of(r144, null, r162, r172, r19, r222, r311, r415, null, null, r8, r26, r51, 0, null, null, false);
        r20 = r172;
        r222 = r19;
        r62.sendMessage(r312);
        if (r06.sendingText == null) goto L269;
        r06.sendingText = null;
        goto L269
    L265:
        r311 = r310.toString();
    L269:
        ChatActivity r152 = r25;
        CharSequence r27 = r06.sendingText;
        int r242 = r472;
        if (r27 == null) goto L272;
        long r63 = r11;
        ChatActivity r122 = r152;
        LaunchActivity r112 = r06;
        AccountInstance r07 = r13;
        sendShareText(r07, r9, r27, r20, r222, r63, r8, r26, r51);
    L273:
        ArrayList<TLRPC.User> r110 = r112.contactsToSend;
        if (r110 == null) goto L282;
        if (r110.isEmpty() == true) goto L282;
        int r111 = 0;
    L279:
        if (r111 >= r112.contactsToSend.size()) goto L282;
        TLRPC.User r145 = r112.contactsToSend.get(r111);
        SendMessagesHelper r29 = SendMessagesHelper.getInstance(r7);
        long r163 = r20;
        MessageObject r173 = r222;
        SendMessagesHelper.SendMessageParams r313 = SendMessagesHelper.SendMessageParams.of(r145, r163, r173, r222, null, null, r8, r26, r51);
        r20 = r163;
        r222 = r173;
        r29.sendMessage(r313);
        r111 = r111 + 1;
    L282:
        if (TextUtils.isEmpty(r41) == true) goto L286;
        if (r37 == true) goto L286;
        if (r38 == true) goto L286;
        CharSequence r210 = r41;
        sendShareText(r07, r45, r210, r20, r222, r63, r8, r26, r51);
    L287:
        r9 = r45;
        r1 = r46;
        r41 = r210;
        r15 = r122;
        r413 = r322;
        r34 = r36;
        r24 = r242 + 1;
        r06 = r112;
    L286:
        r210 = r41;
        goto L287
    L272:
        r63 = r11;
        r122 = r152;
        r112 = r06;
        r07 = r13;
        goto L273
    L242:
        if (r35.length() > 1024) goto L254;
        ArrayList<String> r113 = r06.documentsPathsArray;
        if (r113 == null) goto L246;
        int r114 = r113.size();
    L247:
        ArrayList<Uri> r314 = r06.documentsUrisArray;
        if (r314 == null) goto L250;
        int r315 = r314.size();
    L252:
        if ((r114 + r315) != 1) goto L254;
        r413 = r06.sendingText;
        r06.sendingText = null;
        goto L254
    L250:
        r315 = 0;
        goto L252
    L246:
        r114 = 0;
        goto L247
    L235:
        if (r06.documentsUrisArray != null) goto L239;
        r322 = r413;
    L165:
        ArrayList<SendMessagesHelper.SendingMediaInfo> r146 = r06.photoPathsArray;
        if (r146 == null) goto L183;
        if (r146.size() <= 0) goto L183;
        if (r11 != 0) goto L183;
        if (r422 == true) goto L183;
        ArrayList<SendMessagesHelper.SendingMediaInfo> r103 = r06.photoPathsArray;
        if (r41 != null) goto L174;
    L177:
        CharSequence r147 = r06.sendingText;
    L178:
        r102 = r25.openPhotosEditor(r103, r147);
        if (r102 == false) goto L181;
        r06.sendingText = null;
    L181:
        r20 = r54;
        r143 = r73;
        r55 = false;
        goto L207
    L174:
        if (r41.length() == 0) goto L177;
        r147 = r41;
    L183:
        if (r06.videoPath == null) goto L190;
        CharSequence r104 = r06.sendingText;
        if (r104 != null) goto L187;
    L189:
        CharSequence r182 = r413;
        ArrayList r153 = new ArrayList();
        r153.add(r06.videoPath);
        int r282 = r26;
        r20 = r54;
        r143 = r73;
        SendMessagesHelper.prepareSendingDocuments(r143, r153, r153, null, r182, null, r20, r222, r222, null, null, null, r8, r282, null, null, 0, false, 0);
        r55 = false;
        r102 = false;
        r413 = r182;
        r26 = r282;
        goto L207
    L187:
        if (r104.length() > 1024) goto L189;
        r413 = r06.sendingText;
        r06.sendingText = null;
        goto L189
    L190:
        r20 = r54;
        r143 = r73;
        ArrayList<SendMessagesHelper.SendingMediaInfo> r58 = r06.photoPathsArray;
        if (r58 != null) goto L193;
    L206:
        r55 = false;
        r102 = false;
        goto L207
    L193:
        if (r58.size() <= 0) goto L206;
        CharSequence r59 = r06.sendingText;
        if (r59 != null) goto L197;
    L201:
        ArrayList<SendMessagesHelper.SendingMediaInfo> r154 = r06.photoPathsArray;
        if (r154.size() <= 1) goto L204;
        boolean r232 = true;
    L205:
        MessageObject r183 = r222;
        SendMessagesHelper.prepareSendingMedia(r143, r154, r20, r183, r183, null, null, false, r232, null, r8, r26, r51, 0, false, null, null, 0, false, 0, 0, null);
        r20 = r20;
        r222 = r183;
        goto L206
    L204:
        r232 = false;
        goto L205
    L197:
        if (r59.length() > 1024) goto L201;
        if (r06.photoPathsArray.size() != 1) goto L201;
        CharSequence[] r74 = {r06.sendingText};
        r06.photoPathsArray.get(0).entities = MediaDataController.getInstance(r06.currentAccount).getEntities(r74, false);
        r06.photoPathsArray.get(0).caption = r74[0].toString();
        r06.sendingText = null;
        goto L201
    L159:
        r34 = false;
        goto L160
    L155:
        r16 = false;
        goto L156
    L144:
        if (r06.videoPath != null) goto L151;
        ArrayList<SendMessagesHelper.SendingMediaInfo> r211 = r06.photoPathsArray;
        if (r211 != null) goto L148;
    L150:
        r17 = false;
        goto L152
    L148:
        if (r211.size() <= 0) goto L150;
    L209:
        r20 = r54;
        r143 = r73;
        r25 = r15;
        if (r06.videoPath == null) goto L218;
        CharSequence r510 = r06.sendingText;
        if (r510 != null) goto L214;
    L216:
        CharSequence r184 = r413;
        ArrayList r155 = new ArrayList();
        r155.add(r06.videoPath);
        int r283 = r26;
        SendMessagesHelper.prepareSendingDocuments(r143, r155, r155, null, r184, null, r20, r222, r222, null, null, null, r8, r283, null, null, 0, false, 0);
        r26 = r283;
        r413 = r184;
        goto L218
    L214:
        if (r510.length() > 1024) goto L216;
        r413 = r06.sendingText;
        r06.sendingText = null;
    L218:
        if (r06.photoPathsArray == null) goto L231;
        CharSequence r511 = r06.sendingText;
        if (r511 != null) goto L222;
    L226:
        ArrayList<SendMessagesHelper.SendingMediaInfo> r156 = r06.photoPathsArray;
        if (r156.size() <= 1) goto L229;
        boolean r233 = true;
    L230:
        MessageObject r185 = r222;
        SendMessagesHelper.prepareSendingMedia(r143, r156, r20, r185, r185, null, null, false, r233, null, r8, r26, r51, 0, false, null, null, 0, false, 0, 0, null);
        r20 = r20;
        r222 = r185;
        goto L231
    L229:
        r233 = false;
        goto L230
    L222:
        if (r511.length() > 1024) goto L226;
        if (r06.photoPathsArray.size() != 1) goto L226;
        CharSequence[] r75 = {r06.sendingText};
        r06.photoPathsArray.get(0).entities = MediaDataController.getInstance(r06.currentAccount).getEntities(r75, false);
        r06.photoPathsArray.get(0).caption = r75[0].toString();
        r06.sendingText = null;
    L231:
        r37 = false;
        r38 = false;
        goto L208
    L136:
        if (r12.topicStartMessage == null) goto L139;
        MessageObject r212 = new MessageObject(r73.getCurrentAccount(), r12.topicStartMessage, false, false);
        r212.isTopicMainMessage = true;
        r222 = r212;
        goto L141
    L138:
        r472 = r24;
    L99:
        LaunchActivity r115 = r06;
        ChatActivity r123 = r15;
        if (r45 == null) goto L292;
        if (r123 != null) goto L292;
        if (r34 == true) goto L292;
        r45.finishFragment();
    L292:
        ?? r105 = 0;
    L293:
        r115.photoPathsArray = r105;
        r115.videoPath = r105;
        r115.voicePath = r105;
        r115.sendingText = r105;
        r115.documentsPathsArray = r105;
        r115.documentsOriginalPathsArray = r105;
        r115.contactsToSend = r105;
        r115.contactsToSendUri = r105;
        r115.exportingChatUri = r105;
        return true;
    L106:
        if (r9.hasSharedMediaEntries() == false) goto L108;
        r422 = true;
        goto L109
    L103:
        r41 = r47;
        goto L104
    L95:
        if (r05.size() != 1) goto L100;
        if (this.mainFragmentsStack.isEmpty() == true) goto L100;
        ArrayList<BaseFragment> r08 = this.mainFragmentsStack;
        PhonebookShareAlert r164 = new PhonebookShareAlert(r08.get(r08.size() - 1), null, null, this.contactsToSendUri, null, null, null);
        ChatActivity r213 = r15;
        r06 = this;
        r15 = r213;
        r164.setDelegate(new LaunchActivity$$ExternalSyntheticLambda46(this, r213, r46, r26, r51, r47, r7, r8));
        ArrayList<BaseFragment> r116 = r06.mainFragmentsStack;
        r116.get(r116.size() - 1).showDialog(r164);
        r34 = true;
        goto L99
    L69:
        if (this.voicePath != null) goto L79;
        if (this.photoPathsArray != null) goto L79;
        if (this.documentsPathsArray != null) goto L79;
        if (this.documentsUrisArray != null) goto L79;
        if (this.sendingText == null) goto L79;
        r04 = r04 + 1;
        goto L79
    L50:
        r04 = 0;
        goto L52
    L39:
        if (DialogObject.isUserDialog(r4) == false) goto L41;
        r02.putLong("user_id", r4);
        goto L43
    L41:
        r02.putLong("chat_id", -r4);
        goto L43
    L46:
        r15 = null;
        goto L47
    L27:
        if (r9 != null) goto L29;
        r26 = 0;
        goto L31
    L29:
        r26 = r9.scheduleDate;
        goto L31
    L20:
        if (r9.notify == true) goto L24;
        if (r49 == true) goto L24;
        r8 = false;
        goto L25
    L10:
        if (this.documentsUrisArray == null) goto L13;
        ArrayList r512 = new ArrayList(this.documentsUrisArray);
    L14:
        AlertDialog r76 = new AlertDialog(this, 3);
        SendMessagesHelper.getInstance(r7).prepareImportHistory(r46.get(0).dialogId, this.exportingChatUri, this.documentsUrisArray, new LaunchActivity$$ExternalSyntheticLambda45(this, r7, r45, r48, r512, r6, r76));
        r76.showDelayed(300);     // Catch: Exception -> L295
    L16:
        r105 = 0;
        r115 = this;
        goto L293
    L13:
        r512 = null;
        goto L14
    L6:
        r0 = this.currentAccount;
        goto L5
    }

    @Override // org.telegram.ui.DialogsActivity.DialogsActivityDelegate
    public boolean didSelectStories(DialogsActivity r18) {
        ArrayList<SendMessagesHelper.SendingMediaInfo> r1 = this.photoPathsArray;
        if (r1 != null) goto L5;
    L8:
        String r10 = this.videoPath;
        if (r10 == null) goto L28;
        MediaController.PhotoEntry r5 = new MediaController.PhotoEntry(0, 0, 0, r10, 0, true, 0, 0, 0);
        Bitmap r12 = SendMessagesHelper.createVideoThumbnail(this.videoPath, 1);     // Catch: Throwable -> L45
        File r6 = new File(FileLoader.getDirectory(4), "-2147483648_" + SharedConfig.getLastLocalId() + ".jpg");     // Catch: Throwable -> L45
        FileOutputStream r7 = new FileOutputStream(r6);     // Catch: Throwable -> L18
        r12.compress(Bitmap.CompressFormat.JPEG, 87, r7);     // Catch: Throwable -> L16
        r5.thumbPath = r6.getAbsolutePath();     // Catch: Throwable -> L16
    L14:
        r7.close();     // Catch: Throwable -> L45
    L16:
        th = th;
    L46:
        FileLog.e(th);     // Catch: Throwable -> L23
        if (r7 == null) goto L27;
    L23:
        th = move-exception;
        if (r7 != null) goto L48;
    L26:
        throw th;     // Catch: Throwable -> L45
    L48:
        r7.close();     // Catch: Throwable -> L44
    L18:
        th = th;
        r7 = null;
    L27:
        StoryEntry r0 = StoryEntry.fromPhotoEntry(r5);
    L7:
        StoryEntry r72 = r0;
    L30:
        if (r72 != null) goto L32;
        return false;
    L32:
        StoriesController.StoryLimit r02 = MessagesController.getInstance(this.currentAccount).getStoriesController().checkStoryLimit();
        if (r18 == null) goto L39;
        if (r02 == null) goto L39;
        if (r02.active(this.currentAccount, 1) == false) goto L39;
        r18.showDialog(new LimitReachedBottomSheet(r18, this, r02.getLimitReachedType(), this.currentAccount, null));
        return false;
    L39:
        r72.isShare = true;
        r72.fileDeletable = false;
        StoryRecorder.getInstance(this, this.currentAccount).openEdit(null, r72, 0, true);
        if (r18 == null) goto L42;
        r18.finishFragment();
    L42:
        this.photoPathsArray = null;
        this.videoPath = null;
        this.voicePath = null;
        this.sendingText = null;
        this.documentsPathsArray = null;
        this.documentsOriginalPathsArray = null;
        this.contactsToSend = null;
        this.contactsToSendUri = null;
        this.exportingChatUri = null;
        return true;
    L28:
        r72 = null;
        goto L30
    L5:
        if (r1.isEmpty() == true) goto L8;
        r0 = StoryEntry.fromMedia(this.photoPathsArray);
        goto L7
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent r7) {
        int r0 = r7.getKeyCode();
        boolean r3 = true;
        if (r7.getKeyCode() != 24) goto L5;
    L6:
        BaseFragment r1 = getLastFragment();
        if (r1 != null) goto L9;
    L13:
        if (r7.getKeyCode() != 24) goto L15;
    L16:
        BaseFragment r12 = getLastFragment();
        if ((r12 instanceof ChatActivity) == false) goto L25;
        ChatActivity r13 = (ChatActivity) r12;
        if (r13.getInstantCameraView() == null) goto L25;
        if (r13.getInstantCameraView().isCameraReady() == false) goto L25;
        r13.getInstantCameraView().onKeyDown(r0, r7);
        return true;
    L25:
        if (r7.getAction() == 0) goto L27;
    L77:
        return super.dispatchKeyEvent(r7);
    L74:
        e = move-exception;
        FileLog.e(e);
        return false;
    L27:
        if (r7.getKeyCode() == 24) goto L31;
        if (r7.getKeyCode() != 25) goto L77;
    L31:
        if (VoIPService.getSharedInstance() == null) goto L47;
        if (Build.VERSION.SDK_INT < 32) goto L77;
        boolean r02 = WebRtcAudioTrack.isSpeakerMuted();
        AudioManager r4 = (AudioManager) getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        if (r4.getStreamVolume(0) == r4.getStreamMinVolume(0)) goto L37;
    L39:
        r3 = false;
    L40:
        WebRtcAudioTrack.setSpeakerMute(r3);
        if (r02 == WebRtcAudioTrack.isSpeakerMuted()) goto L77;
        if (r3 == false) goto L44;
        int r03 = 42;
    L45:
        showVoiceChatTooltip(r03);
        goto L77
    L44:
        r03 = 43;
        goto L45
    L37:
        if (r7.getKeyCode() != 25) goto L39;
    L47:
        if (ExteraConfig.getUnmuteWithVolumeButtons() == false) goto L77;
        if (PhotoViewer.hasInstance() == false) goto L53;
        if (PhotoViewer.getInstance().isVisible() == true) goto L77;
    L53:
        if (r7.getRepeatCount() != 0) goto L77;
        ArrayList<BaseFragment> r04 = this.mainFragmentsStack;
        BaseFragment r05 = r04.get(r04.size() - 1);
        if ((r05 instanceof ChatActivity) == false) goto L62;
        if (BaseFragment.hasSheets(r05) == true) goto L62;
        if (((ChatActivity) r05).maybePlayVisibleVideo() == false) goto L62;
        return true;
    L62:
        if (AndroidUtilities.isTablet() == false) goto L77;
        if (this.rightFragmentsStack.isEmpty() == true) goto L77;
        ArrayList<BaseFragment> r06 = this.rightFragmentsStack;
        BaseFragment r07 = r06.get(r06.size() - 1);
        if ((r07 instanceof ChatActivity) == false) goto L77;
        if (BaseFragment.hasSheets(r07) == true) goto L77;
        if (((ChatActivity) r07).maybePlayVisibleVideo() == false) goto L77;
        return true;
    L15:
        if (r7.getKeyCode() != 25) goto L25;
    L9:
        if (r1.getLastStoryViewer() == null) goto L13;
        r1.getLastStoryViewer().dispatchKeyEvent(r7);
        return true;
    L5:
        if (r7.getKeyCode() != 25) goto L13;
        goto L6
    }

    public INavigationLayout getActionBarLayout() {
        ActionBarLayout r0 = this.actionBarLayout;
        if (this.sheetFragmentsStack.isEmpty() == false) goto L5;
        return r0;
    L5:
        return this.sheetFragmentsStack.get(r2.size() - 1);
    }

    public BottomSheetTabs getBottomSheetTabs() {
        ActionBarLayout r0 = this.rightActionBarLayout;
        if (r0 != null) goto L5;
    L8:
        ActionBarLayout r02 = this.actionBarLayout;
        if (r02 != null) goto L11;
        return null;
    L11:
        if (r02.getBottomSheetTabs() != null) goto L13;
        return null;
    L13:
        return this.actionBarLayout.getBottomSheetTabs();
    L5:
        if (r0.getBottomSheetTabs() == null) goto L8;
        return this.rightActionBarLayout.getBottomSheetTabs();
    }

    public BottomSheetTabsOverlay getBottomSheetTabsOverlay() {
        return this.bottomSheetTabsOverlay;
    }

    public FireworksOverlay getFireworksOverlay() {
        return this.fireworksOverlay;
    }

    public INavigationLayout getLayersActionBarLayout() {
        return this.layersActionBarLayout;
    }

    public FrameLayout getMainContainerFrameLayout() {
        return this.frameLayout;
    }

    public int getMainFragmentsCount() {
        return this.mainFragmentsStack.size();
    }

    public int getMainFragmentsStackSize() {
        return this.mainFragmentsStack.size();
    }

    public int getNavigationBarColor() {
        if (Build.VERSION.SDK_INT >= 26) goto L5;
        return 0;
    L5:
        return getWindow().getNavigationBarColor();
    }

    @Override // org.telegram.messenger.pip.activity.IPipActivity
    public PipActivityController getPipController() {
        return this.pipActivityController;
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        Resources r0 = super.getResources();
        ExteraResources r1 = this.res;
        if (r1 != null) goto L5;
    L6:
        this.res = new ExteraResources(r0);
        this.resHistory.add(new WeakReference(this.res));
    L8:
        return this.res;
    L5:
        if (r1.getSourceAssets() == r0.getAssets()) goto L8;
        goto L6
    }

    public INavigationLayout getRightActionBarLayout() {
        return this.rightActionBarLayout;
    }

    public WindowAnimatedInsetsProvider getRootAnimatedInsetsListener() {
        return this.rootAnimatedInsetsListener;
    }

    public Dialog getVisibleDialog() {
        int r0 = this.visibleDialogs.size() - 1;
    L3:
        if (r0 < 0) goto L8;
        Dialog r1 = this.visibleDialogs.get(r0);
        if (r1.isShowing() == true) goto L6;
        r0 = r0 - 1;
        goto L3
    L6:
        return r1;
    L8:
        return null;
    }

    public void hideVisibleActionMode() {
        ActionMode r0 = this.visibleActionMode;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.finish();
    }

    public boolean isLightNavigationBar() {
        return AndroidUtilities.getLightNavigationBar(getWindow());
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout.INavigationLayoutDelegate
    public boolean needAddFragmentToStack(BaseFragment r6, INavigationLayout r7) {
        if (AndroidUtilities.isTablet() == false) goto L67;
        boolean r0 = r6 instanceof DialogsActivity;
        if (r0 == true) goto L52;
        if ((r6 instanceof MainTabsActivity) == true) goto L52;
        if ((r6 instanceof ChatActivity) == true) goto L12;
    L34:
        ActionBarLayout r02 = this.layersActionBarLayout;
        if (r7 == r02) goto L67;
        r02.getView().setVisibility(0);
        int r72 = 0;
    L38:
        if (r72 >= 16) goto L43;
        if (UserConfig.getInstance(r72).isClientActivated() == true) goto L45;
        r72 = r72 + 1;
    L45:
        if ((r6 instanceof LoginActivity) == false) goto L48;
        if (r72 != (-1)) goto L48;
        this.backgroundTablet.setVisibility(0);
        this.shadowTablet.setBackgroundColor(0);
    L49:
        this.layersActionBarLayout.addFragmentToStack(r6);
        return false;
    L48:
        this.shadowTablet.setBackgroundColor(2130706432);
        goto L49
    L43:
        r72 = -1;
        goto L45
    L12:
        if (((ChatActivity) r6).isInScheduleMode() == true) goto L34;
        boolean r03 = this.tabletFullSize;
        if (r03 == true) goto L24;
        ActionBarLayout r4 = this.rightActionBarLayout;
        if (r7 == r4) goto L24;
        r4.getView().setVisibility(0);
        this.backgroundTablet.setVisibility(8);
        this.rightActionBarLayout.removeAllFragments();
        this.rightActionBarLayout.addFragmentToStack(r6);
        if (this.layersActionBarLayout.getFragmentStack().isEmpty() == true) goto L23;
    L19:
        int r62 = this.layersActionBarLayout.getFragmentStack().size() - 1;
        ActionBarLayout r73 = this.layersActionBarLayout;
        if (r62 <= 0) goto L22;
        r73.removeFragmentFromStack(r73.getFragmentStack().get(0));
        goto L19
    L22:
        r73.closeLastFragment(true);
    L23:
        return false;
    L24:
        if (r03 == false) goto L67;
        ActionBarLayout r04 = this.actionBarLayout;
        if (r7 == r04) goto L67;
        r04.addFragmentToStack(r6);
        if (this.layersActionBarLayout.getFragmentStack().isEmpty() == true) goto L33;
    L29:
        int r63 = this.layersActionBarLayout.getFragmentStack().size() - 1;
        ActionBarLayout r74 = this.layersActionBarLayout;
        if (r63 <= 0) goto L32;
        r74.removeFragmentFromStack(r74.getFragmentStack().get(0));
        goto L29
    L32:
        r74.closeLastFragment(true);
    L33:
        return false;
    L52:
        if (r7 == this.actionBarLayout) goto L54;
        boolean r75 = true;
    L55:
        if (r75 == false) goto L60;
        if (r0 == false) goto L60;
        if (((DialogsActivity) r6).isMainDialogList() == true) goto L60;
        r75 = false;
    L60:
        if (r75 == false) goto L67;
        this.actionBarLayout.removeAllFragments();
        this.actionBarLayout.addFragmentToStack(r6);
        this.layersActionBarLayout.removeAllFragments();
        this.layersActionBarLayout.getView().setVisibility(8);
        if (this.tabletFullSize == false) goto L64;
    L66:
        return false;
    L64:
        if (this.rightActionBarLayout.getFragmentStack().isEmpty() == false) goto L66;
        this.backgroundTablet.setVisibility(0);
        goto L66
    L54:
        r75 = false;
    L67:
        return true;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout.INavigationLayoutDelegate
    public boolean needCloseLastFragment(INavigationLayout r4) {
        if (AndroidUtilities.isTablet() == false) goto L26;
        if (r4 != this.actionBarLayout) goto L13;
        if (r4.getFragmentStack().size() > 1) goto L13;
        if (this.switchingAccount == true) goto L13;
        onFinish();
        finish();
        return false;
    L13:
        if (r4 != this.rightActionBarLayout) goto L18;
        if (this.tabletFullSize == true) goto L29;
        this.backgroundTablet.setVisibility(0);
    L29:
        return true;
    L18:
        if (r4 != this.layersActionBarLayout) goto L29;
        if (this.actionBarLayout.getFragmentStack().isEmpty() == false) goto L29;
        if (this.layersActionBarLayout.getFragmentStack().size() != 1) goto L29;
        onFinish();
        finish();
        return false;
    L26:
        if (r4.getFragmentStack().size() > 1) goto L29;
        onFinish();
        finish();
        return false;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout.INavigationLayoutDelegate
    public boolean needPresentFragment(INavigationLayout r13, INavigationLayout.NavigationParams r14) {
        BaseFragment r0 = r14.fragment;
        boolean r1 = r14.removeLast;
        boolean r2 = r14.noAnimation;
        boolean r4 = true;
        if (ArticleViewer.hasInstance() == false) goto L8;
        if (ArticleViewer.getInstance().isVisible() == false) goto L8;
        ArticleViewer.getInstance().close(false, true);
    L8:
        if (AndroidUtilities.isTablet() == true) goto L10;
    L146:
        return true;
    L10:
        if ((r0 instanceof MainTabsActivity) == false) goto L20;
        ActionBarLayout r3 = this.actionBarLayout;
        if (r13 == r3) goto L41;
        r3.removeAllFragments();
        getActionBarLayout().presentFragment(r14.setRemoveLast(r1).setNoAnimation(r2).setCheckPresentFromDelegate(false));
        this.layersActionBarLayout.removeAllFragments();
        this.layersActionBarLayout.getView().setVisibility(8);
        if (this.tabletFullSize == false) goto L16;
    L18:
        return false;
    L16:
        if (this.rightActionBarLayout.getFragmentStack().isEmpty() == false) goto L18;
        this.backgroundTablet.setVisibility(0);
    L41:
        if ((r0 instanceof ChatActivity) == false) goto L45;
        if (((ChatActivity) r0).isInScheduleMode() == true) goto L45;
    L71:
        boolean r02 = this.tabletFullSize;
        if (r02 == false) goto L74;
    L75:
        if (r02 == true) goto L77;
    L102:
        if (r02 == true) goto L116;
        ActionBarLayout r32 = this.rightActionBarLayout;
        if (r13 == r32) goto L116;
        if (r32 == null) goto L116;
        if (r32.getView() == null) goto L109;
        this.rightActionBarLayout.getView().setVisibility(0);
    L109:
        this.backgroundTablet.setVisibility(8);
        this.rightActionBarLayout.removeAllFragments();
        this.rightActionBarLayout.presentFragment(r14.setNoAnimation(true).setRemoveLast(r1).setCheckPresentFromDelegate(false));
        if (this.layersActionBarLayout.getFragmentStack().isEmpty() == true) goto L115;
    L111:
        int r132 = this.layersActionBarLayout.getFragmentStack().size() - 1;
        ActionBarLayout r142 = this.layersActionBarLayout;
        if (r132 <= 0) goto L114;
        r142.removeFragmentFromStack(r142.getFragmentStack().get(0));
        goto L111
    L114:
        r142.closeLastFragment(!r2);
    L115:
        return false;
    L116:
        if (r02 == true) goto L118;
    L130:
        ActionBarLayout r133 = this.layersActionBarLayout;
        if (r133 != null) goto L133;
    L140:
        INavigationLayout r134 = getActionBarLayout();
        if (this.actionBarLayout.getFragmentStack().size() > 1) goto L144;
        r4 = false;
    L144:
        r134.presentFragment(r14.setRemoveLast(r4).setNoAnimation(r2).setCheckPresentFromDelegate(false));
        return false;
    L133:
        if (r133.getFragmentStack() == null) goto L140;
        if (this.layersActionBarLayout.getFragmentStack().isEmpty() == true) goto L140;
    L136:
        int r135 = this.layersActionBarLayout.getFragmentStack().size() - 1;
        ActionBarLayout r03 = this.layersActionBarLayout;
        if (r135 <= 0) goto L139;
        r03.removeFragmentFromStack(r03.getFragmentStack().get(0));
        goto L136
    L139:
        r03.closeLastFragment(!r2);
        goto L140
    L118:
        if (r13 == this.actionBarLayout) goto L130;
        INavigationLayout r136 = getActionBarLayout();
        if (this.actionBarLayout.getFragmentStack().size() <= 1) goto L122;
        boolean r04 = true;
    L123:
        r136.presentFragment(r14.setRemoveLast(r04).setNoAnimation(r2).setCheckPresentFromDelegate(false));
        if (this.layersActionBarLayout.getFragmentStack().isEmpty() == true) goto L129;
    L125:
        int r137 = this.layersActionBarLayout.getFragmentStack().size() - 1;
        ActionBarLayout r143 = this.layersActionBarLayout;
        if (r137 <= 0) goto L128;
        r143.removeFragmentFromStack(r143.getFragmentStack().get(0));
        goto L125
    L128:
        r143.closeLastFragment(!r2);
    L129:
        return false;
    L122:
        r04 = false;
        goto L123
    L77:
        if (r13 != this.actionBarLayout) goto L102;
    L78:
        ActionBarLayout r05 = this.rightActionBarLayout;
        if (r13 != r05) goto L85;
        if (r05.getView() == null) goto L83;
        this.rightActionBarLayout.getView().setVisibility(0);
    L83:
        this.backgroundTablet.setVisibility(8);
    L85:
        if (this.tabletFullSize == false) goto L92;
        ActionBarLayout r06 = this.actionBarLayout;
        if (r13 != r06) goto L92;
        if (r06.getFragmentStack().size() != 1) goto L92;
        boolean r138 = false;
    L94:
        if (this.layersActionBarLayout.getFragmentStack().isEmpty() == true) goto L99;
    L95:
        int r07 = this.layersActionBarLayout.getFragmentStack().size() - 1;
        ActionBarLayout r12 = this.layersActionBarLayout;
        if (r07 <= 0) goto L98;
        r12.removeFragmentFromStack(r12.getFragmentStack().get(0));
        goto L95
    L98:
        r12.closeLastFragment(!r2);
    L99:
        if (r138 == true) goto L101;
        getActionBarLayout().presentFragment(r14.setNoAnimation(r2).setCheckPresentFromDelegate(false));
    L101:
        return r138;
    L92:
        r138 = true;
        goto L94
    L74:
        if (r13 == this.rightActionBarLayout) goto L78;
    L45:
        if (r14.forceRightLayout == true) goto L71;
        ActionBarLayout r33 = this.rightActionBarLayout;
        if (r13 == r33) goto L49;
    L53:
        ActionBarLayout r34 = this.layersActionBarLayout;
        if (r34 == null) goto L146;
        if (r13 == r34) goto L146;
        r34.getView().setVisibility(0);
        int r139 = 0;
    L58:
        if (r139 >= 16) goto L63;
        if (UserConfig.getInstance(r139).isClientActivated() == true) goto L65;
        r139 = r139 + 1;
    L65:
        if ((r0 instanceof LoginActivity) == false) goto L68;
        if (r139 != (-1)) goto L68;
        this.backgroundTablet.setVisibility(0);
        this.shadowTablet.setBackgroundColor(0);
    L69:
        this.layersActionBarLayout.presentFragment(r14.setRemoveLast(r1).setNoAnimation(r2).setCheckPresentFromDelegate(false));
        return false;
    L68:
        this.shadowTablet.setBackgroundColor(2130706432);
        goto L69
    L63:
        r139 = -1;
        goto L65
    L49:
        if (r33.getFragmentStack().size() > 1) goto L71;
        if ((this.rightActionBarLayout.getLastFragment() instanceof ChatActivity) == true) goto L53;
    L20:
        if ((r0 instanceof DialogsActivity) == false) goto L41;
        DialogsActivity r35 = (DialogsActivity) r0;
        if (r35.isMainDialogList() == false) goto L32;
        ActionBarLayout r7 = this.actionBarLayout;
        if (r13 == r7) goto L32;
        r7.removeAllFragments();
        getActionBarLayout().presentFragment(r14.setRemoveLast(r1).setNoAnimation(r2).setCheckPresentFromDelegate(false));
        this.layersActionBarLayout.removeAllFragments();
        this.layersActionBarLayout.getView().setVisibility(8);
        if (this.tabletFullSize == false) goto L28;
    L30:
        return false;
    L28:
        if (this.rightActionBarLayout.getFragmentStack().isEmpty() == false) goto L30;
        this.backgroundTablet.setVisibility(0);
    L32:
        if (r13 != this.actionBarLayout) goto L41;
        if (r35.getArguments() == null) goto L41;
        if (r35.getArguments().getInt("folderId", 0) != 1) goto L38;
    L39:
        return true;
    L38:
        if (r35.getArguments().getLong("community_id", 0) == 0) goto L41;
        goto L39
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onActionModeFinished(ActionMode r3) {
        super.onActionModeFinished(r3);
        if (this.visibleActionMode != r3) goto L6;
        this.visibleActionMode = null;
    L6:
        if (r3.getType() == 1) goto L13;
        this.actionBarLayout.onActionModeFinished(r3);
        if (AndroidUtilities.isTablet() == false) goto L12;
        this.rightActionBarLayout.onActionModeFinished(r3);
        this.layersActionBarLayout.onActionModeFinished(r3);
        return;
    L12:
        return;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onActionModeStarted(ActionMode r3) {
        super.onActionModeStarted(r3);
        this.visibleActionMode = r3;
        Menu r0 = r3.getMenu();     // Catch: Exception -> L13
        if (r0 == null) goto L16;
        if (this.actionBarLayout.extendActionMode(r0) == true) goto L16;
        if (AndroidUtilities.isTablet() == false) goto L16;
        if (this.rightActionBarLayout.extendActionMode(r0) == true) goto L16;
        this.layersActionBarLayout.extendActionMode(r0);     // Catch: Exception -> L13
    L16:
        if (r3.getType() == 1) goto L25;
        this.actionBarLayout.onActionModeStarted(r3);
        if (AndroidUtilities.isTablet() == false) goto L24;
        this.rightActionBarLayout.onActionModeStarted(r3);
        this.layersActionBarLayout.onActionModeStarted(r3);
        return;
    L24:
        return;
    L25:
        return;
    L13:
        e = move-exception;
        FileLog.e(e);
        goto L16
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int r5, int r6, Intent r7) {
        boolean r1 = false;
        if (SharedConfig.passcodeHash.length() == 0) goto L11;
        if (SharedConfig.lastPauseTime == 0) goto L11;
        SharedConfig.lastPauseTime = 0;
        if (BuildVars.LOGS_ENABLED == false) goto L9;
        FileLog.d("reset lastPauseTime onActivityResult");
    L9:
        UserConfig.getInstance(this.currentAccount).saveConfig(false);
    L11:
        if (r5 != 105) goto L19;
        boolean r52 = Settings.canDrawOverlays(this);
        ApplicationLoader.canDrawOverlays = r52;
        if (r52 == false) goto L65;
        GroupCallActivity r53 = GroupCallActivity.groupCallInstance;
        if (r53 == null) goto L17;
        r53.dismissInternal();
    L17:
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda36(this), 200);
        return;
    L65:
        return;
    L19:
        super.onActivityResult(r5, r6, r7);
        if (r5 != 520) goto L27;
        if (r6 != (-1)) goto L66;
        VoIPService r4 = VoIPService.getSharedInstance();
        if (r4 == null) goto L67;
        VideoCapturerDevice.mediaProjectionPermissionResultData = r7;
        r4.createCaptureDevice(true);
        return;
    L67:
        return;
    L66:
        return;
    L27:
        if (r5 != 140) goto L34;
        LocationController r42 = LocationController.getInstance(this.currentAccount);
        if (r6 != (-1)) goto L31;
        r1 = true;
    L31:
        r42.startFusedLocationRequest(r1);
        return;
    L34:
        if (r5 != 521) goto L41;
        Utilities.Callback<Boolean> r54 = this.webviewShareAPIDoneListener;
        if (r54 == null) goto L68;
        if (r6 != (-1)) goto L39;
        r1 = true;
    L39:
        r54.run(Boolean.valueOf(r1));
        this.webviewShareAPIDoneListener = null;
        return;
    L68:
        return;
    L41:
        ThemeEditorView r0 = ThemeEditorView.getInstance();
        if (r0 == null) goto L44;
        r0.onActivityResult(r5, r6, r7);
    L44:
        ActionBarLayout r02 = this.actionBarLayout;
        if (r02 == null) goto L52;
        if (r02.getFragmentStack().size() == 0) goto L52;
        BaseFragment r03 = this.actionBarLayout.getFragmentStack().get(this.actionBarLayout.getFragmentStack().size() - 1);
        r03.onActivityResultFragment(r5, r6, r7);
        if (r03.getLastStoryViewer() == null) goto L52;
        r03.getLastStoryViewer().onActivityResult(r5, r6, r7);
    L52:
        if (AndroidUtilities.isTablet() == false) goto L63;
        ActionBarLayout r04 = this.rightActionBarLayout;
        if (r04 != null) goto L56;
    L58:
        ActionBarLayout r05 = this.layersActionBarLayout;
        if (r05 == null) goto L63;
        if (r05.getFragmentStack().size() == 0) goto L63;
        this.layersActionBarLayout.getFragmentStack().get(this.layersActionBarLayout.getFragmentStack().size() - 1).onActivityResultFragment(r5, r6, r7);
        goto L63
    L56:
        if (r04.getFragmentStack().size() == 0) goto L58;
        this.rightActionBarLayout.getFragmentStack().get(this.rightActionBarLayout.getFragmentStack().size() - 1).onActivityResultFragment(r5, r6, r7);
    L63:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.onActivityResultReceived, new Object[]{Integer.valueOf(r5), Integer.valueOf(r6), r7});
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (onBackPressed(true) == true) goto L6;
        return;
    L6:
        if (AndroidUtilities.isTablet() == false) goto L25;
        ActionBarLayout r1 = this.layersActionBarLayout;
        if (r1 != null) goto L10;
    L13:
        ActionBarLayout r12 = this.rightActionBarLayout;
        if (r12 != null) goto L16;
    L23:
        this.actionBarLayout.onBackPressed();
        return;
    L16:
        if (r12.getView().getVisibility() != 0) goto L23;
        if (this.rightActionBarLayout.getFragmentStack().isEmpty() == true) goto L23;
        BaseFragment r2 = this.rightActionBarLayout.getFragmentStack().get(this.rightActionBarLayout.getFragmentStack().size() - 1);
        if (r2.onBackPressed(true) == false) goto L27;
        r2.finishFragment();
        return;
    L27:
        return;
    L10:
        if (r1.getView().getVisibility() != 0) goto L13;
        this.layersActionBarLayout.onBackPressed();
        return;
    L25:
        this.actionBarLayout.onBackPressed();
    }

    public boolean onBackPressed(boolean r3) {
        if (FloatingDebugController.onBackPressed(r3) == false) goto L5;
    L53:
        return false;
    L5:
        if (IconPickerController.onBackPressed(r3) == true) goto L53;
        PasscodeViewDialog r0 = this.passcodeDialog;
        if (r0 != null) goto L10;
    L14:
        BottomSheetTabsOverlay r2 = this.bottomSheetTabsOverlay;
        if (r2 == null) goto L22;
        if (r2.isOpen == false) goto L22;
        if (r3 == false) goto L20;
        r2.onBackPressed();
    L20:
        return false;
    L22:
        if (SearchTagsList.onBackPressedRenameTagAlert(r3) == true) goto L25;
        return false;
    L25:
        if (ContentPreviewViewer.hasInstance() == false) goto L32;
        if (ContentPreviewViewer.getInstance().isVisible() == false) goto L32;
        if (r3 == false) goto L30;
        ContentPreviewViewer.getInstance().closeWithMenu();
    L30:
        return false;
    L32:
        if (SecretMediaViewer.hasInstance() == false) goto L39;
        if (SecretMediaViewer.getInstance().isVisible() == false) goto L39;
        if (r3 == false) goto L37;
        SecretMediaViewer.getInstance().closePhoto(true, false);
    L37:
        return false;
    L39:
        if (PhotoViewer.hasInstance() == false) goto L46;
        if (PhotoViewer.getInstance().isVisible() == false) goto L46;
        if (r3 == false) goto L44;
        PhotoViewer.getInstance().closePhoto(true, false);
    L44:
        return false;
    L46:
        if (ArticleViewer.hasInstance() == true) goto L48;
    L52:
        return true;
    L48:
        if (ArticleViewer.getInstance().isVisible() == false) goto L52;
        if (r3 == false) goto L51;
        ArticleViewer.getInstance().close(true, false);
    L51:
        return false;
    L10:
        if (r0.passcodeView.getVisibility() != 0) goto L14;
        if (r3 == false) goto L13;
        finish();
    L13:
        return false;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration r5) {
        DisplayMetrics r0 = super.getResources().getDisplayMetrics();
        Iterator<WeakReference<ExteraResources>> r1 = this.resHistory.iterator();
    L4:
        if (r1.hasNext() == false) goto L9;
        WeakReference<ExteraResources> r2 = r1.next();
        ExteraResources r3 = r2.get();
        if (r3 == null) goto L7;
        r3.updateConfiguration(r5, r0);
        goto L4
    L7:
        this.resHistory.remove(r2);
        goto L4
    L9:
        AndroidUtilities.checkDisplaySize(this, r5);
        AndroidUtilities.setPreferredMaxRefreshRate(getWindow());
        super.onConfigurationChanged(r5);
        this.pipActivityHandler.onConfigurationChanged(r5);
        AndroidUtilities.resetTabletFlag();
        invalidateTabletMode();
        checkLayout();
        PipRoundVideoView r4 = PipRoundVideoView.getInstance();
        if (r4 == null) goto L12;
        r4.onConfigurationChanged();
    L12:
        EmbedBottomSheet r42 = EmbedBottomSheet.getInstance();
        if (r42 == null) goto L15;
        r42.onConfigurationChanged(r5);
    L15:
        BoostPagerBottomSheet r43 = BoostPagerBottomSheet.getInstance();
        if (r43 == null) goto L18;
        r43.onConfigurationChanged(r5);
    L18:
        PhotoViewer r44 = PhotoViewer.getPipInstance();
        if (r44 == null) goto L21;
        r44.onConfigurationChanged(r5);
    L21:
        ThemeEditorView r45 = ThemeEditorView.getInstance();
        if (r45 == null) goto L25;
        r45.onConfigurationChanged();
    L25:
        if (Theme.selectedAutoNightType != 3) goto L34;
        Theme.checkAutoNightThemeConditions();
        return;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle r14) {
        isActive = true;
        activeInstanceCount++;
        if (BuildVars.DEBUG_VERSION == false) goto L6;
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder(StrictMode.getVmPolicy()).detectLeakedClosableObjects().penaltyLog().build());
    L6:
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L8;
        LeakDetector.getInstance().start();
    L8:
        instance = this;
        ApplicationLoader.postInitApplication();
        IconManager.INSTANCE.awaitInitialization();
        AndroidUtilities.checkDisplaySize(this, getResources().getConfiguration());
        int r0 = UserConfig.selectedAccount;
        this.currentAccount = r0;
        if (DialogsActivity.dialogsLoaded[r0] == true) goto L11;
        DialogsActivity.setFirstCreate(true);
    L11:
        IntentFilter r02 = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        r02.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
        registerReceiver(this.batteryReceiver, r02);
        if (UserConfig.getInstance(this.currentAccount).isClientActivated() == true) goto L23;
        Intent r03 = getIntent();
        if (r03 == null) goto L23;
        if (r03.getAction() == null) goto L23;
        if ("android.intent.action.SEND".equals(r03.getAction()) == false) goto L20;
    L21:
        super.onCreate(r14);
        finish();
        return;
    L20:
        if ("android.intent.action.SEND_MULTIPLE".equals(r03.getAction()) == true) goto L21;
    L23:
        requestWindowFeature(1);
        setTheme(R.style.Theme_TMessages);
        setTaskDescription(new ActivityManager.TaskDescription(null, null, Theme.getColor(Theme.key_actionBarDefault) | (-16777216)));     // Catch: Throwable -> L144
    L25:
        getWindow().setBackgroundDrawable(new ActivityWindowEmptyBackgroundDrawable());
        getWindow().setFormat(-1);
        FlagSecureReason r4 = new FlagSecureReason(getWindow(), new LaunchActivity$$ExternalSyntheticLambda18());
        this.flagSecureReason = r4;
        r4.attach();
        super.onCreate(r14);
        AndroidUtilities.isInMultiwindow = isInMultiWindowMode();
        Theme.createCommonChatResources();
        if (Theme.dialogs_namePaint == null) goto L28;
        Theme.reloadAllResources(this);
    L30:
        if (SharedConfig.passcodeHash.length() != 0) goto L32;
    L34:
        AndroidUtilities.fillStatusBarHeight(this, false);
        this.actionBarLayout = new ActionBarLayout(this, true);
        ActivityContentLayout r6 = new ActivityContentLayout(this, this);
        this.frameLayout = r6;
        r6.setClipToPadding(false);
        this.frameLayout.setClipChildren(false);
        setContentView(this.frameLayout);
        this.rootAnimatedInsetsListener = new WindowAnimatedInsetsProvider(this.frameLayout);
        this.pipActivityController.addPipListener(new AnonymousClass2(this));
        ((ViewGroup) getWindow().getDecorView()).addView(this.pipActivityController.getPipContentView());
        this.pipActivityController.getPipContentView().bringToFront();
        ImageView r62 = new ImageView(this);
        this.themeSwitchImageView = r62;
        r62.setVisibility(8);
        DrawerLayoutContainer r63 = new DrawerLayoutContainer(this);
        this.drawerLayoutContainer = r63;
        r63.setActionBarLayout(this.actionBarLayout);
        this.drawerLayoutContainer.addOnLayoutChangeListener(new AnonymousClass3(this));
        this.drawerLayoutContainer.setClipChildren(false);
        this.drawerLayoutContainer.setClipToPadding(false);
        this.frameLayout.addView(this.drawerLayoutContainer, LayoutHelper.createFrame(-1, -1.0f));
        AnonymousClass4 r5 = new AnonymousClass4(this, this);
        this.themeSwitchSunView = r5;
        r5.setScaleType(ImageView.ScaleType.CENTER);
        this.frameLayout.addView(this.themeSwitchSunView, LayoutHelper.createFrame(48, 48.0f));
        this.themeSwitchSunView.setVisibility(8);
        FrameLayout r52 = this.frameLayout;
        BottomSheetTabsOverlay r64 = new BottomSheetTabsOverlay(this);
        this.bottomSheetTabsOverlay = r64;
        r52.addView(r64);
        FrameLayout r53 = this.frameLayout;
        AnonymousClass5 r65 = new AnonymousClass5(this, this);
        this.fireworksOverlay = r65;
        r53.addView(r65);
        setupActionBarLayout();
        this.drawerLayoutContainer.setParentActionBarLayout(this.actionBarLayout);
        this.actionBarLayout.setDrawerLayoutContainer(this.drawerLayoutContainer);
        syncDrawerContainerEnabled();
        this.actionBarLayout.setFragmentStack(this.mainFragmentsStack);
        this.actionBarLayout.setFragmentStackChangedListener(new LaunchActivity$$ExternalSyntheticLambda19(this));
        this.actionBarLayout.setDelegate(this);
        Theme.loadWallpaper(true);
        checkCurrentAccount();
        updateCurrentConnectionState();
        NotificationCenter r54 = NotificationCenter.getGlobalInstance();
        int r66 = NotificationCenter.closeOtherAppActivities;
        r54.postNotificationName(r66, new Object[]{this});
        this.currentConnectionState = ConnectionsManager.getInstance(this.currentAccount).getConnectionState();
        NotificationCenter.ObserversGroup r55 = this.globalObserversGroup;
        if (r55 == null) goto L37;
        r55.removeAllObservers();
        this.globalObserversGroup = null;
    L37:
        this.globalObserversGroup = NotificationCenter.getGlobalInstance().createObserversGroup(this).add(NotificationCenter.needShowAlert).add(NotificationCenter.reloadInterface).add(NotificationCenter.suggestedLangpack).add(NotificationCenter.didSetNewTheme).add(NotificationCenter.needSetDayNightTheme).add(NotificationCenter.needCheckSystemBarColors).add(r66).add(NotificationCenter.didSetPasscode).add(NotificationCenter.didSetNewWallpapper).add(NotificationCenter.screenStateChanged).add(NotificationCenter.showBulletin).add(NotificationCenter.requestPermissions).add(NotificationCenter.billingConfirmPurchaseError).add(NotificationCenter.tlSchemeParseException).add(NotificationCenter.memoryLeakFoundException);
        LaunchActivity$$ExternalSyntheticLambda20 r04 = new LaunchActivity$$ExternalSyntheticLambda20(this);
        this.onPowerSaverCallback = r04;
        LiteMode.addOnPowerSaverAppliedListener(r04);
        if (this.actionBarLayout.getFragmentStack().isEmpty() == false) goto L90;
        ActionBarLayout r05 = this.layersActionBarLayout;
        if (r05 == null) goto L44;
        if (r05.getFragmentStack().isEmpty() == false) goto L90;
    L44:
        if (UserConfig.getInstance(this.currentAccount).isClientActivated() == true) goto L46;
        this.actionBarLayout.addFragmentToStack(getClientNotActivatedFragment());
    L47:
        if (r14 == null) goto L90;
        String r06 = r14.getString("fragment");     // Catch: Exception -> L56
        if (r06 == null) goto L149;
        Bundle r56 = r14.getBundle("args");     // Catch: Exception -> L56
        switch(r06.hashCode()) {
            case -1529105743: goto L86;
            case -1349522494: goto L80;
            case 3052376: goto L74;
            case 98629247: goto L68;
            case 738950403: goto L62;
            case 1434631203: goto L59;
            case 1523894383: goto L54;
            default: goto L149;
        };     // Catch: Exception -> L56
    L54:
        if (r06.equals("settings2") == false) goto L149;
        this.actionBarLayout.addFragmentToStack(new SettingsActivity());     // Catch: Exception -> L56
        goto L149
    L59:
        if (r06.equals("settings") == false) goto L149;
        r56.putLong("user_id", UserConfig.getInstance(this.currentAccount).getClientUserId());     // Catch: Exception -> L56
        ProfileActivity r07 = new ProfileActivity(r56);     // Catch: Exception -> L56
        this.actionBarLayout.addFragmentToStack(r07);     // Catch: Exception -> L56
        r07.restoreSelfArgs(r14);     // Catch: Exception -> L56
        goto L149
    L62:
        if (r06.equals("channel") == false) goto L149;
        if (r56 == null) goto L149;
        ChannelCreateActivity r08 = new ChannelCreateActivity(r56);     // Catch: Exception -> L56
        if (this.actionBarLayout.addFragmentToStack(r08) == false) goto L149;
        r08.restoreSelfArgs(r14);     // Catch: Exception -> L56
        goto L149
    L68:
        if (r06.equals("group") == false) goto L149;
        if (r56 == null) goto L149;
        GroupCreateFinalActivity r09 = new GroupCreateFinalActivity(r56);     // Catch: Exception -> L56
        if (this.actionBarLayout.addFragmentToStack(r09) == false) goto L149;
        r09.restoreSelfArgs(r14);     // Catch: Exception -> L56
        goto L149
    L74:
        if (r06.equals("chat") == false) goto L149;
        if (r56 == null) goto L149;
        ChatActivity r010 = new ChatActivity(r56);     // Catch: Exception -> L56
        if (this.actionBarLayout.addFragmentToStack(r010) == false) goto L149;
        r010.restoreSelfArgs(r14);     // Catch: Exception -> L56
        goto L149
    L80:
        if (r06.equals("chat_profile") == false) goto L149;
        if (r56 == null) goto L149;
        ProfileActivity r011 = new ProfileActivity(r56);     // Catch: Exception -> L56
        if (this.actionBarLayout.addFragmentToStack(r011) == false) goto L149;
        r011.restoreSelfArgs(r14);     // Catch: Exception -> L56
        goto L149
    L86:
        if (r06.equals("wallpapers") == false) goto L149;
        WallpapersListActivity r012 = new WallpapersListActivity(0);     // Catch: Exception -> L56
        this.actionBarLayout.addFragmentToStack(r012);     // Catch: Exception -> L56
        r012.restoreSelfArgs(r14);     // Catch: Exception -> L56
    L149:
    L56:
        e = move-exception;
        FileLog.e(e);
        goto L90
    L46:
        this.actionBarLayout.addFragmentToStack(new MainTabsActivity());
    L90:
        checkLayout();
        checkSystemBarColors();
        ExteraConfig.loadConfig();
        Intent r67 = getIntent();
        if (r14 == null) goto L93;
        boolean r8 = true;
    L94:
        handleIntent(r67, false, r8, false, null, true, true);
        String r13 = Build.DISPLAY;     // Catch: Exception -> L99
        String r142 = Build.USER;     // Catch: Exception -> L99
        String r013 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r13 == null) goto L101;
        String r132 = r13.toLowerCase();     // Catch: Exception -> L99
    L102:
        if (r142 == null) goto L105;
        r013 = r132.toLowerCase();     // Catch: Exception -> L99
    L105:
        if (BuildVars.LOGS_ENABLED == false) goto L108;
        FileLog.d("OS name " + r132 + " " + r013);     // Catch: Exception -> L99
    L108:
        if (r132.contains("flyme") == true) goto L112;
        if (r013.contains("flyme") == true) goto L112;
    L116:
        MediaController.getInstance().setBaseActivity(this, true);
        FingerprintController.checkKeyReady();
        int r133 = Build.VERSION.SDK_INT;
        if (r133 < 28) goto L126;
        if (((ActivityManager) getSystemService("activity")).isBackgroundRestricted() == false) goto L126;
        if ((System.currentTimeMillis() - SharedConfig.BackgroundActivityPrefs.getLastCheckedBackgroundActivity()) < DurationKt.MILLIS_IN_DAY) goto L126;
        if (SharedConfig.BackgroundActivityPrefs.getDismissedCount() >= 3) goto L126;
        AlertsCreator.createBackgroundActivityDialog(this).show();
        SharedConfig.BackgroundActivityPrefs.setLastCheckedBackgroundActivity(System.currentTimeMillis());
    L126:
        if (r133 < 31) goto L128;
        MonetUtils.registerReceiver(this);
        getWindow().getDecorView().addOnAttachStateChangeListener(new AnonymousClass6(this));
    L128:
        Bulletin.addDelegate(this.frameLayout, new AnonymousClass7(this));
        getWindow().getDecorView().setSystemUiVisibility(1792);
        AndroidUtilities.enableEdgeToEdge(this);
        checkAppUpdate(false);
        ExteraConfig.init();
        BackupAgent.requestBackup();
        RestrictedLanguagesSelectActivity.checkRestrictedLanguages(false);
        if (r133 < 34) goto L137;
        if (ExteraConfig.getPredictiveBackIntensity() <= 0.0f) goto L137;
        if (this.onBackAnimationCallback != null) goto L135;
        this.onBackAnimationCallback = new AnonymousClass8(this);
    L135:
        getOnBackInvokedDispatcher().registerOnBackInvokedCallback(0, LaunchActivity$$ExternalSyntheticApiModelOutline0.m(this.onBackAnimationCallback));
    L142:
        checkFrameMetrics();
        return;
    L137:
        if (r133 < 33) goto L142;
        if (this.onBackInvokedCallback != null) goto L141;
        this.onBackInvokedCallback = new AnonymousClass9(this);
    L141:
        getOnBackInvokedDispatcher().registerOnBackInvokedCallback(0, AppCompatDelegateImpl$Api33Impl$$ExternalSyntheticApiModelOutline0.m(this.onBackInvokedCallback));
    L112:
        if (Build.VERSION.SDK_INT > 24) goto L116;
        AndroidUtilities.incorrectDisplaySizeFix = true;     // Catch: Exception -> L99
        View r134 = getWindow().getDecorView().getRootView();     // Catch: Exception -> L99
        ViewTreeObserver r143 = r134.getViewTreeObserver();     // Catch: Exception -> L99
        LaunchActivity$$ExternalSyntheticLambda21 r014 = new LaunchActivity$$ExternalSyntheticLambda21(r134);     // Catch: Exception -> L99
        this.onGlobalLayoutListener = r014;     // Catch: Exception -> L99
        r143.addOnGlobalLayoutListener(r014);     // Catch: Exception -> L99
        goto L116
    L101:
        r132 = _UrlKt.FRAGMENT_ENCODE_SET;
    L99:
        e = move-exception;
        FileLog.e(e);
        goto L116
    L93:
        r8 = false;
        goto L94
    L32:
        if (SharedConfig.appLocked == false) goto L34;
        SharedConfig.lastPauseTime = (int) (SystemClock.elapsedRealtime() / 1000);
        goto L34
    L28:
        Theme.createDialogsResources(this);
        goto L30
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        int r0 = 0;
        isActive = false;
        activeInstanceCount--;
        unregisterReceiver(this.batteryReceiver);
        if (Build.VERSION.SDK_INT < 31) goto L5;
        MonetUtils.unregisterReceiver(this);
    L5:
        PluginsController.getInstance().executeOnAppEvent(PluginsConstants.APP_STOP);
        if (activeInstanceCount != 0) goto L8;
        onDestroyStaticResources();
    L8:
        MediaController.getInstance().setBaseActivity(this, false);
        MediaController.getInstance().setFeedbackView(this.feedbackView, false);
    L52:
        int r1 = this.visibleDialogs.size();     // Catch: Exception -> L15
        ArrayList<Dialog> r2 = this.visibleDialogs;
        if (r0 >= r1) goto L18;
        if (r2.get(r0).isShowing() == false) goto L17;
        this.visibleDialogs.get(r0).dismiss();     // Catch: Exception -> L15
    L17:
        r0 = r0 + 1;     // Catch: Exception -> L15
        goto L52
    L18:
        r2.clear();     // Catch: Exception -> L15
    L50:
    L25:
        e = move-exception;
        FileLog.e(e);
    L27:
        int r02 = Build.VERSION.SDK_INT;
        if (r02 < 35) goto L31;
        Bulletin.removeDelegate(this.frameLayout);
    L31:
        if (r02 < 34) goto L36;
        if (LaunchActivity$$ExternalSyntheticApiModelOutline1.m(this.onBackAnimationCallback) == false) goto L40;
        getOnBackInvokedDispatcher().unregisterOnBackInvokedCallback(LaunchActivity$$ExternalSyntheticApiModelOutline0.m(this.onBackAnimationCallback));
    L40:
        Bulletin.removeDelegate(this.frameLayout);
        VideoAds.dropCache();
        if (instance != this) goto L43;
        clearFragments();
        instance = null;
    L43:
        super.onDestroy();
        onFinish();
        FlagSecureReason r03 = this.flagSecureReason;
        if (r03 == null) goto L46;
        r03.detach();
    L46:
        FrameMetricsOverlayView r3 = this.frameMetricsOverlayView;
        if (r3 == null) goto L57;
        r3.detach();
        return;
    L57:
        return;
    L36:
        if (r02 != 33) goto L40;
        if (LaunchActivity$$ExternalSyntheticApiModelOutline2.m(this.onBackInvokedCallback) == false) goto L40;
        getOnBackInvokedDispatcher().unregisterOnBackInvokedCallback(AppCompatDelegateImpl$Api33Impl$$ExternalSyntheticApiModelOutline0.m(this.onBackInvokedCallback));
        goto L40
    L22:
        if (this.onGlobalLayoutListener == null) goto L27;
        getWindow().getDecorView().getRootView().getViewTreeObserver().removeOnGlobalLayoutListener(this.onGlobalLayoutListener);     // Catch: Exception -> L25
    L15:
        e = move-exception;
        FileLog.e(e);
        goto L50
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int r2, KeyEvent r3) {
        if (r2 != 82) goto L33;
        if (SharedConfig.isWaitingForPasscodeEnter == true) goto L33;
        if (PhotoViewer.hasInstance() == false) goto L13;
        if (PhotoViewer.getInstance().isVisible() == false) goto L13;
        return super.onKeyUp(r2, r3);
    L13:
        if (ArticleViewer.hasInstance() == false) goto L19;
        if (ArticleViewer.getInstance().isVisible() == false) goto L19;
        return super.onKeyUp(r2, r3);
    L19:
        if (AndroidUtilities.isTablet() == true) goto L21;
        this.actionBarLayout.getView().onKeyUp(r2, r3);
        goto L33
    L21:
        if (this.layersActionBarLayout.getView().getVisibility() != 0) goto L26;
        if (this.layersActionBarLayout.getFragmentStack().isEmpty() == true) goto L26;
        this.layersActionBarLayout.getView().onKeyUp(r2, r3);
    L26:
        if (this.rightActionBarLayout.getView().getVisibility() == 0) goto L28;
    L30:
        this.actionBarLayout.getView().onKeyUp(r2, r3);
        goto L33
    L28:
        if (this.rightActionBarLayout.getFragmentStack().isEmpty() == true) goto L30;
        this.rightActionBarLayout.getView().onKeyUp(r2, r3);
    L33:
        return super.onKeyUp(r2, r3);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        ActionBarLayout r0 = this.actionBarLayout;
        if (r0 == null) goto L13;
        r0.onLowMemory();
        if (AndroidUtilities.isTablet() == false) goto L14;
        ActionBarLayout r02 = this.rightActionBarLayout;
        if (r02 == null) goto L9;
        r02.onLowMemory();
    L9:
        ActionBarLayout r1 = this.layersActionBarLayout;
        if (r1 == null) goto L15;
        r1.onLowMemory();
        return;
    L15:
        return;
    L14:
        return;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onMultiWindowModeChanged(boolean r1) {
        AndroidUtilities.isInMultiwindow = r1;
        checkLayout();
        super.onMultiWindowModeChanged(r1);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent r9) {
        super.onNewIntent(r9);
        handleIntent(r9, true, false, false, null, true, true);
    }

    public void onNewIntent(Intent r9, Browser.Progress r10) {
        super.onNewIntent(r9);
        handleIntent(r9, true, false, false, r10, true, false);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        isResumed = false;
        this.pipActivityHandler.onPause();
        PluginsController.getInstance().executeOnAppEvent(PluginsConstants.APP_PAUSE);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopAllHeavyOperations, new Object[]{4096});
        ApplicationLoader.mainInterfacePaused = true;
        Choreographer60FpsContent.onApplicationStateChanged();
        Utilities.stageQueue.postRunnable(new LaunchActivity$$ExternalSyntheticLambda35(this.currentAccount));
        onPasscodePause();
        this.actionBarLayout.onPause();
        if (AndroidUtilities.isTablet() == false) goto L10;
        ActionBarLayout r2 = this.rightActionBarLayout;
        if (r2 == null) goto L7;
        r2.onPause();
    L7:
        ActionBarLayout r22 = this.layersActionBarLayout;
        if (r22 == null) goto L10;
        r22.onPause();
    L10:
        PasscodeViewDialog r23 = this.passcodeDialog;
        if (r23 == null) goto L13;
        r23.passcodeView.onPause();
    L13:
        Iterator<PasscodeView> r24 = this.overlayPasscodeViews.iterator();
    L15:
        if (r24.hasNext() == false) goto L17;
        r24.next().onPause();
        goto L15
    L17:
        ApplicationLoader r25 = ApplicationLoader.applicationLoaderInstance;
        if (r25 == null) goto L20;
        boolean r26 = r25.onPause();
    L21:
        ConnectionsManager.getInstance(this.currentAccount).setAppPaused(!r26, false);
        if (PhotoViewer.hasInstance() == true) goto L24;
    L26:
        StoryRecorder.onPause();
        if (VoIPFragment.getInstance() == null) goto L29;
        VoIPFragment.onPause();
    L29:
        SpoilerEffect2.pause(true);
        return;
    L24:
        if (PhotoViewer.getInstance().isVisible() == false) goto L26;
        PhotoViewer.getInstance().onPause();
        goto L26
    L20:
        r26 = false;
        goto L21
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onPictureInPictureModeChanged(boolean r2, Configuration r3) {
        super.onPictureInPictureModeChanged(r2, r3);
        this.pipActivityHandler.onPictureInPictureModeChanged(r2, r3);
        if (r2 == false) goto L5;
        return;
    L5:
        if (this.isStarted == false) goto L7;
        return;
    L7:
        if (RTMPStreamPipOverlay.isVisible() == false) goto L10;
        RTMPStreamPipOverlay.dismiss();
    L10:
        if (LiveStoryPipOverlay.isVisible() == false) goto L13;
        LiveStoryPipOverlay.dismiss();
    L13:
        if (PipVideoOverlay.isVisible() == false) goto L15;
        PipVideoOverlay.dismiss();
    L15:
        GroupCallActivity.onLeaveClick(this, null, false, true);
        if (PhotoViewer.getPipInstance() == null) goto L19;
        PhotoViewer.getPipInstance().destroyPhotoViewer();
    L19:
        if (PhotoViewer.hasInstance() == false) goto L24;
        PhotoViewer.getInstance().closePhoto(false, false);
        return;
    }

    @Override // android.app.Activity
    public boolean onPictureInPictureRequested() {
        this.pipActivityHandler.onPictureInPictureRequested();
        return super.onPictureInPictureRequested();
    }

    @Override // android.app.Activity
    public void onPictureInPictureUiStateChanged(PictureInPictureUiState r1) {
        super.onPictureInPictureUiStateChanged(r1);
        this.pipActivityHandler.onPictureInPictureUiStateChanged(r1);
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout.INavigationLayoutDelegate
    public boolean onPreIme() {
        if (SecretMediaViewer.hasInstance() == false) goto L9;
        if (SecretMediaViewer.getInstance().isVisible() == false) goto L9;
        SecretMediaViewer.getInstance().closePhoto(true, false);
        return true;
    L9:
        if (PhotoViewer.hasInstance() == false) goto L15;
        if (PhotoViewer.getInstance().isVisible() == false) goto L15;
        PhotoViewer.getInstance().closePhoto(true, false);
        return true;
    L15:
        if (ArticleViewer.hasInstance() == true) goto L17;
    L20:
        return false;
    L17:
        if (ArticleViewer.getInstance().isVisible() == false) goto L20;
        ArticleViewer.getInstance().close(true, false);
        return true;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout.INavigationLayoutDelegate
    public void onRebuildAllFragments(INavigationLayout r2, boolean r3) {
        if (AndroidUtilities.isTablet() == true) goto L5;
        return;
    L5:
        if (r2 != this.layersActionBarLayout) goto L9;
        this.rightActionBarLayout.rebuildAllFragmentViews(r3, r3);
        this.actionBarLayout.rebuildAllFragmentViews(r3, r3);
        return;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int r4, String[] r5, int[] r6) {
        super.onRequestPermissionsResult(r4, r5, r6);
        if (checkPermissionsResult(r4, r5, r6) == false) goto L30;
        ApplicationLoader r0 = ApplicationLoader.applicationLoaderInstance;
        if (r0 == null) goto L11;
        if (r0.checkRequestPermissionResult(r4, r5, r6) == false) goto L11;
        return;
    L11:
        if (this.actionBarLayout.getFragmentStack().size() == 0) goto L14;
        this.actionBarLayout.getFragmentStack().get(this.actionBarLayout.getFragmentStack().size() - 1).onRequestPermissionsResultFragment(r4, r5, r6);
    L14:
        if (AndroidUtilities.isTablet() == false) goto L25;
        ActionBarLayout r02 = this.rightActionBarLayout;
        if (r02 != null) goto L18;
    L20:
        ActionBarLayout r03 = this.layersActionBarLayout;
        if (r03 == null) goto L25;
        if (r03.getFragmentStack().size() == 0) goto L25;
        this.layersActionBarLayout.getFragmentStack().get(this.layersActionBarLayout.getFragmentStack().size() - 1).onRequestPermissionsResultFragment(r4, r5, r6);
        goto L25
    L18:
        if (r02.getFragmentStack().size() == 0) goto L20;
        this.rightActionBarLayout.getFragmentStack().get(this.rightActionBarLayout.getFragmentStack().size() - 1).onRequestPermissionsResultFragment(r4, r5, r6);
    L25:
        VoIPFragment.onRequestPermissionsResult(r4, r5, r6);
        StoryRecorder.onRequestPermissionsResult(r4, r5, r6);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.onRequestPermissionResultReceived, new Object[]{Integer.valueOf(r4), r5, r6});
        if (this.requestedPermissions.get(r4, -1) < 0) goto L28;
        int r04 = this.requestedPermissions.get(r4, -1);
        this.requestedPermissions.delete(r4);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.permissionsGranted, new Object[]{Integer.valueOf(r04)});
    L28:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.activityPermissionsGranted, new Object[]{Integer.valueOf(r4), r5, r6});
        return;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        isResumed = true;
        this.pipActivityHandler.onResume();
        Runnable r1 = onResumeStaticCallback;
        if (r1 == null) goto L6;
        r1.run();
        onResumeStaticCallback = null;
    L6:
        if (Theme.selectedAutoNightType != 3) goto L8;
        Theme.checkAutoNightThemeConditions();
    L8:
        checkWasMutedByAdmin(true);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{4096});
        MediaController r12 = MediaController.getInstance();
        ViewGroup r3 = this.actionBarLayout.getView();
        this.feedbackView = r3;
        r12.setFeedbackView(r3, true);
        ApplicationLoader.mainInterfacePaused = false;
        Choreographer60FpsContent.onApplicationStateChanged();
        MessagesController.getInstance(this.currentAccount).sortDialogs(null);
        showLanguageAlert(false);
        Utilities.stageQueue.postRunnable(new LaunchActivity$$ExternalSyntheticLambda37());
        checkFreeDiscSpace(0);
        MediaController.checkGallery();
        onPasscodeResume();
        PasscodeViewDialog r32 = this.passcodeDialog;
        if (r32 != null) goto L11;
    L25:
        this.actionBarLayout.onResume();
        if (AndroidUtilities.isTablet() == false) goto L33;
        ActionBarLayout r33 = this.rightActionBarLayout;
        if (r33 == null) goto L30;
        r33.onResume();
    L30:
        ActionBarLayout r34 = this.layersActionBarLayout;
        if (r34 == null) goto L33;
        r34.onResume();
    L33:
        ConnectionsManager.getInstance(this.currentAccount).setAppPaused(false, false);
        updateCurrentConnectionState();
        if (PhotoViewer.hasInstance() == true) goto L36;
    L38:
        StoryRecorder.onResume();
        if (PipRoundVideoView.getInstance() == null) goto L46;
        if (MediaController.getInstance().isMessagePaused() == false) goto L46;
        MessageObject r35 = MediaController.getInstance().getPlayingMessageObject();
        if (r35 == null) goto L46;
        MediaController.getInstance().seekToProgress(r35, r35.audioProgress);
    L46:
        if (UserConfig.getInstance(UserConfig.selectedAccount).unacceptedTermsOfService == null) goto L48;
        int r36 = UserConfig.selectedAccount;
        showTosActivity(r36, UserConfig.getInstance(r36).unacceptedTermsOfService);
    L53:
        checkAppUpdate(false);
        RemoteUtils.init();
        ApplicationLoader.canDrawOverlays = Settings.canDrawOverlays(this);
        if (VoIPFragment.getInstance() == null) goto L56;
        VoIPFragment.onResume();
    L56:
        invalidateTabletMode();
        SpoilerEffect2.pause(false);
        ApplicationLoader r13 = ApplicationLoader.applicationLoaderInstance;
        if (r13 == null) goto L59;
        r13.onResume();
    L59:
        PluginsController.getInstance().executeOnAppEvent(PluginsConstants.APP_RESUME);
        Runnable r14 = whenResumed;
        if (r14 == null) goto L63;
        r14.run();
        whenResumed = null;
    L63:
        if (MessagesController.getInstance(this.currentAccount).hasSetupEmailSuggestion() == false) goto L65;
        MessagesController.getInstance(this.currentAccount).checkPromoInfo(true);
    L65:
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda38(this), 300);
        return;
    L48:
        TLRPC.TL_help_appUpdate r37 = SharedConfig.pendingAppUpdate;
        if (r37 == null) goto L53;
        if (r37.can_not_skip == false) goto L53;
        showUpdateActivity(UserConfig.selectedAccount, SharedConfig.pendingAppUpdate, true);
        goto L53
    L36:
        if (PhotoViewer.getInstance().isVisible() == false) goto L38;
        PhotoViewer.getInstance().onResume();
        goto L38
    L11:
        if (r32.passcodeView.getVisibility() != 0) goto L25;
        this.actionBarLayout.dismissDialogs();
        if (AndroidUtilities.isTablet() == false) goto L21;
        ActionBarLayout r38 = this.rightActionBarLayout;
        if (r38 == null) goto L18;
        r38.dismissDialogs();
    L18:
        ActionBarLayout r39 = this.layersActionBarLayout;
        if (r39 == null) goto L21;
        r39.dismissDialogs();
    L21:
        this.passcodeDialog.passcodeView.onResume();
        Iterator<PasscodeView> r310 = this.overlayPasscodeViews.iterator();
    L23:
        if (r310.hasNext() == false) goto L33;
        r310.next().onResume();
        goto L23
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle r6) {
        super.onSaveInstanceState(r6);     // Catch: Exception -> L54
        if (AndroidUtilities.isTablet() == false) goto L18;
        ActionBarLayout r0 = this.layersActionBarLayout;     // Catch: Exception -> L54
        if (r0 != null) goto L7;
    L9:
        ActionBarLayout r02 = this.rightActionBarLayout;     // Catch: Exception -> L54
        if (r02 == null) goto L15;
        if (r02.getFragmentStack().isEmpty() == true) goto L15;
        BaseFragment r5 = this.rightActionBarLayout.getFragmentStack().get(this.rightActionBarLayout.getFragmentStack().size() - 1);     // Catch: Exception -> L54
    L21:
        if (r5 == null) goto L59;
        Bundle r03 = r5.getArguments();     // Catch: Exception -> L54
        if ((r5 instanceof ChatActivity) == false) goto L28;
        if (r03 == null) goto L28;
        r6.putBundle("args", r03);     // Catch: Exception -> L54
        r6.putString("fragment", "chat");     // Catch: Exception -> L54
    L52:
        r5.saveSelfArgs(r6);     // Catch: Exception -> L54
        return;
    L28:
        if ((r5 instanceof GroupCreateFinalActivity) == false) goto L32;
        if (r03 == null) goto L32;
        r6.putBundle("args", r03);     // Catch: Exception -> L54
        r6.putString("fragment", "group");     // Catch: Exception -> L54
    L32:
        if ((r5 instanceof WallpapersListActivity) == false) goto L35;
        r6.putString("fragment", "wallpapers");     // Catch: Exception -> L54
        goto L52
    L35:
        if ((r5 instanceof ProfileActivity) == false) goto L44;
        ProfileActivity r1 = (ProfileActivity) r5;     // Catch: Exception -> L54
        if (r1.isSettings() == false) goto L40;
        r6.putString("fragment", "settings");     // Catch: Exception -> L54
        goto L52
    L40:
        if (r1.isChat() == false) goto L52;
        if (r03 == null) goto L52;
        r6.putBundle("args", r03);     // Catch: Exception -> L54
        r6.putString("fragment", "chat_profile");     // Catch: Exception -> L54
        goto L52
    L44:
        if ((r5 instanceof ChannelCreateActivity) == false) goto L50;
        if (r03 == null) goto L50;
        if (r03.getInt("step") != 0) goto L50;
        r6.putBundle("args", r03);     // Catch: Exception -> L54
        r6.putString("fragment", "channel");     // Catch: Exception -> L54
    L50:
        if ((r5 instanceof SettingsActivity) == false) goto L52;
        r6.putString("fragment", "settings2");     // Catch: Exception -> L54
        goto L52
    L59:
        return;
    L15:
        if (this.actionBarLayout.getFragmentStack().isEmpty() == true) goto L20;
        r5 = this.actionBarLayout.getFragmentStack().get(this.actionBarLayout.getFragmentStack().size() - 1);     // Catch: Exception -> L54
    L20:
        r5 = null;
        goto L21
    L7:
        if (r0.getFragmentStack().isEmpty() == true) goto L9;
        r5 = this.layersActionBarLayout.getFragmentStack().get(this.layersActionBarLayout.getFragmentStack().size() - 1);     // Catch: Exception -> L54
        goto L21
    L18:
        if (this.actionBarLayout.getFragmentStack().isEmpty() == true) goto L20;
        r5 = this.actionBarLayout.getFragmentStack().get(this.actionBarLayout.getFragmentStack().size() - 1);     // Catch: Exception -> L54
    L54:
        e = move-exception;
        FileLog.e(e);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        this.isStarted = true;
        this.pipActivityHandler.onStart();
        Browser.bindCustomTabsService(this);
        ApplicationLoader.mainInterfaceStopped = false;
        GroupCallPip.updateVisibility(this);
        GroupCallActivity r1 = GroupCallActivity.groupCallInstance;
        if (r1 == null) goto L6;
        r1.onResume();
        return;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        this.isStarted = false;
        this.pipActivityHandler.onStop();
        Browser.unbindCustomTabsService(this);
        ApplicationLoader.mainInterfaceStopped = true;
        GroupCallPip.updateVisibility(this);
        GroupCallActivity r1 = GroupCallActivity.groupCallInstance;
        if (r1 == null) goto L6;
        r1.onPause();
        return;
    }

    @Override // org.telegram.ui.ActionBar.INavigationLayout.INavigationLayoutDelegate
    public void onThemeProgress(float r1) {
        if (ArticleViewer.hasInstance() == false) goto L8;
        if (ArticleViewer.getInstance().isVisible() == false) goto L8;
        ArticleViewer.getInstance().updateThemeColors(r1);
    L8:
        if (PhotoViewer.hasInstance() == false) goto L11;
        PhotoViewer.getInstance().updateColors();
        return;
    }

    @Override // android.app.Activity
    public void onUserInteraction() {
        super.onUserInteraction();
        this.voipLaunchedInBackground = false;
    }

    @Override // android.app.Activity
    public void onUserLeaveHint() {
        this.pipActivityHandler.onUserLeaveHint();
        Iterator<Runnable> r0 = this.onUserLeaveHintListeners.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().run();
        goto L4
    L6:
        ActionBarLayout r2 = this.actionBarLayout;
        if (r2 == null) goto L11;
        r2.onUserLeaveHint();
        return;
    }

    public void openEmailSettings(TL_account.Password r6) {
        LoginActivity r0 = new LoginActivity().changeEmail(new LaunchActivity$$ExternalSyntheticLambda123(this));
        if (r6 == null) goto L14;
        String r1 = r6.login_email_pattern;
        if (r1 == null) goto L14;
        SpannableStringBuilder r12 = SpannableStringBuilder.valueOf(r1);
        int r2 = r6.login_email_pattern.indexOf(42);
        int r62 = r6.login_email_pattern.lastIndexOf(42);
        if (r2 != r62) goto L9;
    L12:
        new AlertDialog.Builder(this).setTitle(r12).setMessage(LocaleController.getString(R.string.EmailLoginChangeMessage)).setPositiveButton(LocaleController.getString(R.string.ChangeEmail), new LaunchActivity$$ExternalSyntheticLambda124(this, r0)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).show();
        return;
    L9:
        if (r2 == (-1)) goto L12;
        if (r62 == (-1)) goto L12;
        TextStyleSpan.TextStyleRun r3 = new TextStyleSpan.TextStyleRun();
        r3.flags |= 256;
        r3.start = r2;
        int r63 = r62 + 1;
        r3.end = r63;
        r12.setSpan(new TextStyleSpan(r3), r2, r63, 0);
    L14:
        presentFragment(r0);
    }

    public void openMessage(long r13, int r15, String r16, Browser.Progress r17, int r18, int r19, Integer r20, byte[] r21) {
        if (r13 >= 0) goto L12;
        TLRPC.Chat r4 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r13));
        if (r4 == null) goto L12;
        if (ChatObject.isForum(r4) == false) goto L12;
        if (r17 == null) goto L10;
        r17.init();
    L10:
        openForumFromLink(r13, Integer.valueOf(r15), r16, r20, r21, new LaunchActivity$$ExternalSyntheticLambda152(r17), r18, r19);
        return;
    L12:
        if (r17 == null) goto L14;
        r17.init();
    L14:
        Bundle r42 = new Bundle();
        if (r13 < 0) goto L17;
        r42.putLong("user_id", r13);
    L24:
        r42.putInt("message_id", r15);
        if (this.mainFragmentsStack.isEmpty() == true) goto L28;
        BaseFragment r1 = this.mainFragmentsStack.get(r1.size() - 1);
    L27:
        BaseFragment r11 = r1;
        if (r11 != null) goto L31;
    L34:
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda154(this, r42, r21, r15, r20, r16, r19, r13, r17, r11));
        return;
    L31:
        if (MessagesController.getInstance(this.currentAccount).checkCanOpenChat(r42, r11) == true) goto L34;
        return;
    L28:
        r1 = null;
        goto L27
    L17:
        long r5 = -r13;
        TLRPC.Chat r3 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r5));
        if (r3 != null) goto L20;
    L23:
        r42.putLong("chat_id", r5);
        goto L24
    L20:
        if (r3.forum == false) goto L23;
        openForumFromLink(r13, Integer.valueOf(r15), r16, r20, r21, new LaunchActivity$$ExternalSyntheticLambda153(r17), r18, r19);
    }

    public void presentFragment(BaseFragment r1) {
        getActionBarLayout().presentFragment(r1);
    }

    public boolean presentFragment(BaseFragment r7, boolean r8, boolean r9) {
        return getActionBarLayout().presentFragment(r7, r8, r9, true, false);
    }

    public void rebuildAllFragments(boolean r2) {
        ActionBarLayout r0 = this.layersActionBarLayout;
        if (r0 == null) goto L6;
        r0.rebuildAllFragmentViews(r2, r2);
        return;
    L6:
        this.actionBarLayout.rebuildAllFragmentViews(r2, r2);
    }

    public void removeOnUserLeaveHintListener(Runnable r1) {
        this.onUserLeaveHintListeners.remove(r1);
    }

    public void removeOverlayPasscodeView(PasscodeView r1) {
        this.overlayPasscodeViews.remove(r1);
    }

    public void setNavigateToPremiumBot(boolean r1) {
        this.navigateToPremiumBot = r1;
    }

    public void setNavigateToPremiumGiftCallback(Runnable r1) {
        this.navigateToPremiumGiftCallback = r1;
    }

    public void setNavigationBarColor(int r2) {
        this.drawerLayoutContainer.setInternalNavigationBarColor(r2);
        BottomSheetTabs r1 = getBottomSheetTabs();
        if (r1 == null) goto L6;
        r1.setNavigationBarColor(r2);
        return;
    }

    @Override // android.app.Activity
    public void setPictureInPictureParams(PictureInPictureParams r1) {
        super.setPictureInPictureParams(r1);
        this.pipActivityHandler.setPictureInPictureParams(r1);
    }

    public Dialog showAlertDialog(AlertDialog.Builder r2) {
        AlertDialog r22 = r2.show();     // Catch: Exception -> L4
        r22.setCanceledOnTouchOutside(true);     // Catch: Exception -> L4
        r22.setOnDismissListener(new LaunchActivity$$ExternalSyntheticLambda91(this, r22));     // Catch: Exception -> L4
        this.visibleDialogs.add(r22);     // Catch: Exception -> L4
        return r22;
    L4:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public void showBulletin(Function<BulletinFactory, Bulletin> r2) {
        if (this.layerFragmentsStack.isEmpty() == true) goto L6;
        BaseFragment r1 = this.layerFragmentsStack.get(r1.size() - 1);
    L13:
        if (BulletinFactory.canShowBulletin(r1) == false) goto L16;
        r2.apply(BulletinFactory.of(r1)).show();
        return;
    L16:
        return;
    L6:
        if (this.rightFragmentsStack.isEmpty() == true) goto L9;
        r1 = this.rightFragmentsStack.get(r1.size() - 1);
        goto L13
    L9:
        if (this.mainFragmentsStack.isEmpty() == true) goto L11;
        r1 = this.mainFragmentsStack.get(r1.size() - 1);
        goto L13
    L11:
        r1 = null;
        goto L13
    }

    public void showPasscodeActivity(boolean r18, boolean r19, int r20, int r21, Runnable r22, Runnable r23) {
        if (this.drawerLayoutContainer != null) goto L5;
        return;
    L5:
        if (isFinishing() == false) goto L8;
        return;
    L8:
        if (this.passcodeDialog != null) goto L10;
        this.passcodeDialog = new PasscodeViewDialog(this);
    L10:
        SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow r1 = this.selectAnimatedEmojiDialog;
        if (r1 == null) goto L13;
        r1.dismiss();
        this.selectAnimatedEmojiDialog = null;
    L13:
        SharedConfig.appLocked = true;
        if (SecretMediaViewer.hasInstance() == false) goto L19;
        if (SecretMediaViewer.getInstance().isVisible() == false) goto L19;
        SecretMediaViewer.getInstance().closePhoto(false, false);
    L28:
        StoryRecorder.destroyInstance();
        MessageObject r2 = MediaController.getInstance().getPlayingMessageObject();
        if (r2 != null) goto L31;
    L33:
        this.passcodeDialog.show();
        PasscodeView r4 = this.passcodeDialog.passcodeView;
        if (this.overlayPasscodeViews.isEmpty() == false) goto L37;
        if (r18 == false) goto L37;
        boolean r5 = true;
    L38:
        r4.onShow(r5, r19, r20, r21, new LaunchActivity$$ExternalSyntheticLambda55(this, r22), r23);
        int r24 = 0;
    L40:
        if (r24 >= this.overlayPasscodeViews.size()) goto L48;
        PasscodeView r10 = this.overlayPasscodeViews.get(r24);
        if (r18 == true) goto L44;
    L46:
        boolean r11 = false;
    L47:
        r10.onShow(r11, r19, r20, r21, null, null);
        r24 = r24 + 1;
        goto L40
    L44:
        if (r24 != (this.overlayPasscodeViews.size() - 1)) goto L46;
        r11 = true;
        goto L47
    L48:
        SharedConfig.isWaitingForPasscodeEnter = true;
        LaunchActivity$$ExternalSyntheticLambda56 r12 = new LaunchActivity$$ExternalSyntheticLambda56(this);
        this.passcodeDialog.passcodeView.setDelegate(r12);
        Iterator<PasscodeView> r0 = this.overlayPasscodeViews.iterator();
    L50:
        if (r0.hasNext() == false) goto L57;
        r0.next().setDelegate(r12);
        goto L50
    L57:
        NotificationsController.getInstance(UserConfig.selectedAccount).showNotifications();     // Catch: Exception -> L54
        return;
    L54:
        e = move-exception;
        FileLog.e(e);
        return;
    L37:
        r5 = false;
        goto L38
    L31:
        if (r2.isRoundVideo() == false) goto L33;
        MediaController.getInstance().cleanupPlayer(true, true);
    L19:
        if (PhotoViewer.hasInstance() == false) goto L24;
        if (PhotoViewer.getInstance().isVisible() == false) goto L24;
        PhotoViewer.getInstance().closePhoto(false, true);
    L24:
        if (ArticleViewer.hasInstance() == false) goto L28;
        if (ArticleViewer.getInstance().isVisible() == false) goto L28;
        ArticleViewer.getInstance().close(false, true);
        goto L28
    }

    public void switchToAccount(int r2, boolean r3) {
        switchToAccount(r2, r3, new LaunchActivity$$ExternalSyntheticLambda40());
    }

    public void switchToAccount(int r6, boolean r7, GenericProvider<Void, MainTabsActivity> r8) {
        if (r6 != UserConfig.selectedAccount) goto L5;
        return;
    L5:
        if (UserConfig.isValidAccount(r6) == false) goto L36;
        this.switchingAccount = true;
        ConnectionsManager.getInstance(this.currentAccount).setAppPaused(true, false);
        UserConfig.selectedAccount = r6;
        UserConfig.getInstance(0).saveConfig(false);
        checkCurrentAccount();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.activeAccountChanged, new Object[]{Integer.valueOf(r6)});
        if (AndroidUtilities.isTablet() == false) goto L15;
        this.layersActionBarLayout.removeAllFragments();
        this.rightActionBarLayout.removeAllFragments();
        if (this.tabletFullSize == true) goto L15;
        if (this.rightActionBarLayout.getFragmentStack().isEmpty() == false) goto L14;
        this.backgroundTablet.setVisibility(0);
    L14:
        this.rightActionBarLayout.getView().setVisibility(8);
    L15:
        ActionBarLayout r1 = this.actionBarLayout;
        if (r7 == false) goto L18;
        r1.removeAllFragments();
    L19:
        MainTabsActivity r72 = r8.provide(null);
        this.actionBarLayout.addFragmentToStack(r72, -3);
        this.actionBarLayout.rebuildFragments(1);
        if (AndroidUtilities.isTablet() == false) goto L22;
        this.layersActionBarLayout.rebuildFragments(1);
        this.rightActionBarLayout.rebuildFragments(1);
    L22:
        DrawerLayoutContainer r73 = this.drawerLayoutContainer;
        if (r73 == null) goto L28;
        if (r73.getDrawerContainer() == null) goto L28;
        this.drawerLayoutContainer.getDrawerContainer().onAccountChanged();
    L28:
        if (ApplicationLoader.mainInterfacePaused == true) goto L31;
        ConnectionsManager.getInstance(this.currentAccount).setAppPaused(false, false);
    L31:
        if (UserConfig.getInstance(r6).unacceptedTermsOfService == null) goto L33;
        showTosActivity(r6, UserConfig.getInstance(r6).unacceptedTermsOfService);
    L33:
        updateCurrentConnectionState();
        PluginsController.getInstance().loadPluginSettings();
        this.switchingAccount = false;
        AndroidUtilities.runOnUIThread(new LaunchActivity$$ExternalSyntheticLambda121(), 150);
        return;
    L18:
        r1.removeFragmentFromStack(0);
        goto L19
    }

    public void whenWebviewShareAPIDone(Utilities.Callback<Boolean> r1) {
        this.webviewShareAPIDoneListener = r1;
    }
}
