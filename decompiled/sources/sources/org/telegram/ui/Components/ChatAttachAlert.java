package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.LongSparseArray;
import android.util.Property;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.RoundedCorner;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.components.TranslateBeforeSendWrapper;
import com.exteragram.messenger.translator.TranslatorUtils;
import com.exteragram.messenger.utils.system.SystemUtils;
import com.exteragram.messenger.utils.text.LocaleUtils;
import com.google.android.material.timepicker.TimeModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import kotlin.time.DurationKt;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import me.vkryl.android.animator.ListAnimator;
import me.vkryl.android.animator.ReplaceAnimator;
import me.vkryl.core.BitwiseUtils;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagePreviewParams;
import org.telegram.messenger.MessageSuggestionParams;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationsSettingsFacade;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.messenger.utils.EphemeralMessagesHelper;
import org.telegram.messenger.utils.FBool;
import org.telegram.messenger.utils.GradientProtectionDrawable;
import org.telegram.messenger.utils.RectFMergeBounding;
import org.telegram.messenger.utils.ViewOutlineProviderImpl;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.AdjustPanLayoutHelper;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Adapters.MentionsAdapter;
import org.telegram.ui.Business.ChatAttachAlertQuickRepliesLayout;
import org.telegram.ui.Business.QuickRepliesController;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimationProperties;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.ChatActivityEnterView;
import org.telegram.ui.Components.ChatAttachAlertAudioLayout;
import org.telegram.ui.Components.ChatAttachAlertContactsLayout;
import org.telegram.ui.Components.ChatAttachAlertDocumentLayout;
import org.telegram.ui.Components.ChatAttachAlertLocationLayout;
import org.telegram.ui.Components.ChatAttachAlertPhotoLayout;
import org.telegram.ui.Components.EmojiView;
import org.telegram.ui.Components.ImageUpdater;
import org.telegram.ui.Components.MentionsContainerView;
import org.telegram.ui.Components.MessagePreviewView;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.BlurredBackgroundWithFadeDrawable;
import org.telegram.ui.Components.blur3.DownscaleScrollableNoiseSuppressor;
import org.telegram.ui.Components.blur3.RenderNodeWithHash;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;
import org.telegram.ui.Components.blur3.capture.IBlur3Hash;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundProviderBuilder;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceRenderNode;
import org.telegram.ui.Components.blur3.utils.Blur3Utils;
import org.telegram.ui.Components.chat.ViewPositionWatcher;
import org.telegram.ui.Components.chat.layouts.ChatActivityFadeView;
import org.telegram.ui.Components.glass.GlassTabView;
import org.telegram.ui.Components.poll.PollAttachedMediaPack;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.GradientClip;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.MessageSendPreview;
import org.telegram.ui.PassportActivity;
import org.telegram.ui.PaymentFormActivity;
import org.telegram.ui.PhotoPickerActivity;
import org.telegram.ui.PhotoPickerSearchActivity;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.PremiumPreviewFragment;
import org.telegram.ui.Stars.MessageSuggestionOfferSheet;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.recorder.HintView2;
import org.telegram.ui.Stories.recorder.StoryEntry;
import org.telegram.ui.TopicsFragment;
import org.telegram.ui.WebAppDisclaimerAlert;
import org.telegram.ui.bots.BotWebViewMenuContainer$ActionBarColorsAnimating;
import org.telegram.ui.bots.ChatAttachAlertBotWebViewLayout;
import org.telegram.ui.iv.ChatAttachAlertRichLayout;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes7.dex */
public class ChatAttachAlert extends BottomSheet implements NotificationCenter.NotificationCenterDelegate, BottomSheet.BottomSheetDelegateInterface, FactorAnimator.Target {
    public final Property<AttachAlertLayout, Float> ATTACH_ALERT_LAYOUT_TRANSLATION;
    private final Property<ChatAttachAlert, Float> ATTACH_ALERT_PROGRESS;
    public ActionBar actionBar;
    private AnimatorSet actionBarAnimation;
    private final ImageView aiButton;
    private final AiButtonDrawable aiButtonIcon;
    private boolean allowDrawContent;
    public boolean allowEnterCaption;
    public boolean allowLivePhotos;
    protected boolean allowOrder;
    protected boolean allowPassConfirmationAlert;
    private final BoolAnimator animatorActionBarVisible;
    private final BoolAnimator animatorCaptionAbove;
    private final BoolAnimator animatorCaptionNotEmpty;
    private final BoolAnimator animatorCaptionVisible;
    private final ReplaceAnimator<Long> animatorCurrentVisibleLayout;
    private final BoolAnimator animatorEphemeralMessageVisibility;
    private final BoolAnimator animatorToggleCaptionSupported;
    private SpringAnimation appearSpringAnimation;
    private final Paint attachButtonPaint;
    private int attachItemSize;
    private ChatAttachAlertAudioLayout audioLayout;
    private ChatAttachAlertAudioLayout.AudioSelectDelegate audioSelectDelegate;
    protected int avatarPicker;
    protected boolean avatarSearch;
    protected Utilities.Callback0Return<PhotoViewer.PlaceProviderObject> avatarWithBulletin;
    public final BaseFragment baseFragment;
    private float baseSelectedTextViewTranslationY;
    private LongSparseArray<ChatAttachAlertBotWebViewLayout> botAttachLayouts;
    private boolean botButtonProgressWasVisible;
    private boolean botButtonWasVisible;
    private float botMainButtonOffsetY;
    private AnimatedTextView botMainButtonTextView;
    private RadialProgressView botProgressView;
    private BlurredBackgroundWithFadeDrawable bottomFadeDrawable;
    private View bottomFadeView;
    private float bottomPannelTranslation;
    private boolean buttonPressed;
    private ButtonsAdapter buttonsAdapter;
    private AnimatorSet buttonsAnimation;
    private LinearLayoutManager buttonsLayoutManager;
    protected RecyclerListView buttonsRecyclerView;
    protected FrameLayout buttonsRecyclerViewWrapper;
    private final FragmentFloatingButton cameraFloatingButton;
    public boolean canOpenPreview;
    public boolean captionAbove;
    private FrameLayout captionContainer;
    private BlurredBackgroundDrawable captionContainerBg;
    private float captionEditTextTopOffset;
    protected boolean captionLimitBulletinShown;
    private final AnimatedTextView captionLimitView;
    private float chatActivityEnterViewAnimateFromTop;
    private int codepointCount;
    public ChatAttachAlertColorsLayout colorsLayout;
    public EditTextEmoji commentTextView;
    private int[] commentTextViewLocation;
    private AnimatorSet commentsAnimator;
    private boolean confirmationAlertShown;
    private ChatAttachAlertContactsLayout contactsLayout;
    protected float cornerRadius;
    public final int currentAccount;
    private AttachAlertLayout currentAttachLayout;
    private int currentLimit;
    public float currentPanTranslationY;
    public Utilities.Callback2<String, TLRPC.InputDocument> customStickerHandler;
    private DecelerateInterpolator decelerateInterpolator;
    protected ChatAttachViewDelegate delegate;
    public boolean destroyed;
    public long dialogId;
    private ChatAttachAlertDocumentLayout documentLayout;
    private ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate documentsDelegate;
    private boolean documentsEnabled;
    protected TextView doneItem;
    private float doneItemAlphaByEnabled;
    private float doneItemAlphaByLayout;
    private int editType;
    protected MessageObject editingMessageObject;
    private long effectId;
    private ChatAttachAlertEmojiLayout emojiLayout;
    private BlurredBackgroundDrawable emojiViewChildBg;
    private EmojiView.EmojiViewDelegate emojiViewDelegate;
    private boolean enterCommentEventSent;
    private ArrayList<Rect> exclusionRects;
    private Rect exclustionRect;
    private ChatActivityFadeView fadeView;
    public boolean forUser;
    private final boolean forceDarkTheme;
    private FrameLayout frameLayout2;
    private float fromScrollY;
    private boolean hasOverridenWebviewBackgroundColor;
    protected FrameLayout headerView;
    private final IBlur3Capture iBlur3Capture;
    private final BlurredBackgroundDrawableViewFactory iBlur3FactoryFade;
    private final BlurredBackgroundDrawableViewFactory iBlur3FactoryFrostedLiquidGlass;
    private final BlurredBackgroundDrawableViewFactory iBlur3FactoryLiquidGlass;
    private final RectF iBlur3PositionActionBar;
    private final RectF iBlur3PositionFastScroll;
    private final RectF iBlur3PositionMainTabs;
    private final ArrayList<RectF> iBlur3Positions;
    private final ArrayList<RectF> iBlur3PositionsMerged;
    private final BlurredBackgroundSourceColor iBlur3SourceColor;
    private final BlurredBackgroundSourceRenderNode iBlur3SourceGlass;
    private final BlurredBackgroundSourceRenderNode iBlur3SourceGlassFrosted;
    public boolean inBubbleMode;
    public boolean isBizLocationPicker;
    public boolean isLocationPicker;
    public boolean isPhotoPicker;
    public boolean isPollAttach;
    private boolean isSoundPicker;
    public boolean isStickerMode;
    public boolean isStoryAudioPicker;
    public boolean isStoryLocationPicker;
    private AttachAlertLayout[] layouts;
    private ChatAttachAlertLocationLayout.LocationActivityDelegate locationActivityDelegate;
    private ChatAttachAlertLocationLayout locationLayout;
    protected int maxSelectedPhotos;
    protected TextView mediaPreviewTextView;
    protected LinearLayout mediaPreviewView;
    public MentionsContainerView mentionContainer;
    private MentionsContainerView.Delegate mentionsDelegate;
    private AnimatorSet menuAnimator;
    private boolean menuShowed;
    private MessageSendPreview messageSendPreview;
    private HintView2 motionHint;
    private MotionPhotoDrawable motionIcon;
    protected ActionBarMenuItem motionItem;
    public ImageView moveCaptionButton;
    private boolean musicEnabled;
    private AttachAlertLayout nextAttachLayout;
    private boolean openTransitionFinished;
    protected boolean openWithFrontFaceCamera;
    protected ActionBarMenuItem optionsItem;
    private boolean overrideBackgroundColor;
    private int overridenWebviewBackgroundColor;
    private Paint paint;
    public ImageUpdater parentImageUpdater;
    public ChatActivity.ThemeDelegate parentThemeDelegate;
    private PasscodeView passcodeView;
    protected boolean paused;
    private ChatAttachAlertPhotoLayout photoLayout;
    private ChatAttachAlertPhotoLayoutPreview photoPreviewLayout;
    private boolean photosEnabled;
    public boolean pinnedToTop;
    private boolean plainTextEnabled;
    private int pollAllowedLayouts;
    private ChatAttachAlertPollLayout pollLayout;
    private boolean pollsEnabled;
    private int previousScrollOffsetY;
    private ChatAttachAlertQuickRepliesLayout quickRepliesLayout;
    private RectF rect;
    public boolean restrictEphemeralMessageTypes;
    private ChatAttachRestrictedLayout restrictedLayout;
    private ChatAttachAlertRichLayout richLayout;
    public int[] scrollOffsetY;
    private final DownscaleScrollableNoiseSuppressor scrollableViewNoiseSuppressor;
    protected ActionBarMenuItem searchItem;
    protected ImageView selectedArrowImageView;
    private View selectedCountView;
    private long selectedId;
    protected ActionBarMenuItem selectedMenuItem;
    protected TextView selectedTextView;
    protected LinearLayout selectedView;
    boolean sendButtonEnabled;
    private float sendButtonEnabledProgress;
    private ItemOptions sendButtonItemOptions;
    public boolean sent;
    private ImageUpdater.AvatarFor setAvatarFor;
    private final boolean showingFromDialog;
    private boolean shownAiButton;
    public SizeNotifierFrameLayout sizeNotifierFrameLayout;
    private ChatAttachAlertEmojiLayout stickersLayout;
    public boolean storyLocationPickerFileIsVideo;
    public double[] storyLocationPickerLatLong;
    public File storyLocationPickerPhotoFile;
    public boolean storyMediaPicker;
    private TextPaint textPaint;
    private float toScrollY;
    private boolean todoEnabled;
    private ChatAttachAlertPollLayout todoLayout;
    private final ImageView topAiButton;
    private final AiButtonDrawable topAiButtonIcon;
    private ValueAnimator topBackgroundAnimator;
    private final AnimatedTextView topCaptionLimitView;
    public FrameLayout topCommentContainer;
    public ImageView topCommentMoveButton;
    public EditTextEmoji topCommentTextView;
    public float translationProgress;
    protected boolean typeButtonsAvailable;
    private boolean typeButtonsHidden;
    private boolean videosEnabled;
    private Object viewChangeAnimator;
    private ViewPositionWatcher viewPositionWatcher;
    private ChatActivityEnterView.SendButton writeButton;
    private FrameLayout writeButtonContainer;

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$1, reason: invalid class name */
    public class AnonymousClass1 implements BotWebViewContainer.Delegate {
        private ValueAnimator botButtonAnimator;
        final /* synthetic */ ChatAttachAlert this$0;
        final /* synthetic */ long val$id;
        final /* synthetic */ String val$startCommand;
        final /* synthetic */ ChatAttachAlertBotWebViewLayout val$webViewLayout;

        /* renamed from: org.telegram.ui.Components.ChatAttachAlert$1$1, reason: invalid class name and collision with other inner class name */
        public class C00471 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass1 this$1;
            final /* synthetic */ boolean val$isVisible;

            public C00471(AnonymousClass1 r1, boolean r2) {
                this.this$1 = r1;
                this.val$isVisible = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r5) {
                boolean r0 = this.val$isVisible;
                AnonymousClass1 r1 = this.this$1;
                if (r0 == true) goto L5;
                ChatAttachAlert.m9438$$Nest$fgetbotMainButtonTextView(r1.this$0).setVisibility(8);
            L6:
                int r12 = 0;
                if (this.val$isVisible == false) goto L9;
                int r02 = AndroidUtilities.dp(36.0f);
            L10:
                int r2 = ChatAttachAlert.m9434$$Nest$fgetbotAttachLayouts(this.this$1.this$0).size();
                AnonymousClass1 r3 = this.this$1;
                if (r12 >= r2) goto L14;
                ((ChatAttachAlertBotWebViewLayout) ChatAttachAlert.m9434$$Nest$fgetbotAttachLayouts(r3.this$0).valueAt(r12)).setMeasureOffsetY(r02);
                r12 = r12 + 1;
                goto L10
            L14:
                if (AnonymousClass1.m9538$$Nest$fgetbotButtonAnimator(r3) != r5) goto L19;
                AnonymousClass1.m9539$$Nest$fputbotButtonAnimator(this.this$1, null);
                return;
            L19:
                return;
            L9:
                r02 = 0;
                goto L10
            L5:
                r1.this$0.buttonsRecyclerViewWrapper.setVisibility(8);
                goto L6
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator r4) {
                boolean r42 = this.val$isVisible;
                AnonymousClass1 r0 = this.this$1;
                int r2 = 0;
                if (r42 == false) goto L9;
                ChatAttachAlert.m9438$$Nest$fgetbotMainButtonTextView(r0.this$0).setAlpha(0.0f);
                ChatAttachAlert.m9438$$Nest$fgetbotMainButtonTextView(this.this$1.this$0).setVisibility(0);
                int r43 = AndroidUtilities.dp(36.0f);
            L6:
                if (r2 >= ChatAttachAlert.m9434$$Nest$fgetbotAttachLayouts(this.this$1.this$0).size()) goto L8;
                ((ChatAttachAlertBotWebViewLayout) ChatAttachAlert.m9434$$Nest$fgetbotAttachLayouts(this.this$1.this$0).valueAt(r2)).setMeasureOffsetY(r43);
                r2 = r2 + 1;
                goto L6
            L8:
                return;
            L9:
                r0.this$0.buttonsRecyclerViewWrapper.setAlpha(0.0f);
                this.this$1.this$0.buttonsRecyclerViewWrapper.setVisibility(0);
            }
        }

        /* renamed from: org.telegram.ui.Components.ChatAttachAlert$1$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass1 this$1;
            final /* synthetic */ boolean val$isProgressVisible;

            public AnonymousClass2(AnonymousClass1 r1, boolean r2) {
                this.this$1 = r1;
                this.val$isProgressVisible = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                ChatAttachAlert.m9500$$Nest$fputbotButtonProgressWasVisible(this.this$1.this$0, this.val$isProgressVisible);
                if (this.val$isProgressVisible == true) goto L6;
                ChatAttachAlert.m9439$$Nest$fgetbotProgressView(this.this$1.this$0).setVisibility(8);
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$5VR9q9lwPAc0w9T9S9Rgia9EvsA(ChatAttachAlertBotWebViewLayout r0, String r1, String r2) {
            r0.getWebViewContainer().onInvoiceStatusUpdate(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$BMUcx9WwxMZ0HcEWOR7QpstU5dQ(AnonymousClass1 r0, int r1, int r2, ChatAttachAlertBotWebViewLayout r3, BotWebViewMenuContainer$ActionBarColorsAnimating r4, ValueAnimator r5) {
            r0.lambda$onWebAppSetActionBarColor$1(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$PmxU41FRyzgAm9jTxK4Sg8RKKow(OverlayActionBarLayoutDialog r1, ChatAttachAlertBotWebViewLayout r2, String r3, PaymentFormActivity.InvoiceStatus r4) {
            if (r4 == PaymentFormActivity.InvoiceStatus.PENDING) goto L5;
            r1.dismiss();
        L5:
            r2.getWebViewContainer().onInvoiceStatusUpdate(r3, r4.name().toLowerCase(Locale.ROOT));
        }

        /* renamed from: $r8$lambda$Q-_CTMf7GmwnuZ0FVcmTTTyMPhw, reason: not valid java name */
        public static /* synthetic */ void m9537$r8$lambda$Q_CTMf7GmwnuZ0FVcmTTTyMPhw(AnonymousClass1 r0, ValueAnimator r1) {
            r0.lambda$onSetupMainButton$6(r1);
        }

        public static /* synthetic */ boolean $r8$lambda$eL1VSu9ERXLJ2jM9QWKMuKduEq4(AnonymousClass1 r0, TLRPC.User r1, String r2, OverlayActionBarLayoutDialog r3, DialogsActivity r4, ArrayList r5, CharSequence r6, boolean r7, boolean r8, int r9, int r10, TopicsFragment r11) {
            return r0.lambda$onWebAppSwitchInlineQuery$5(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
        }

        public static /* synthetic */ void $r8$lambda$o9xXvPK9XJQ3UhaUGaDM0nQN0oE(Runnable r0) {
            if (r0 == null) goto L5;
            r0.run();
            return;
        }

        public static /* synthetic */ void $r8$lambda$q6E3RRqmvpsYoMNSeFerin7cZDM(AlertDialog r0) {
            r0.dismiss();
        }

        /* renamed from: -$$Nest$fgetbotButtonAnimator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ValueAnimator m9538$$Nest$fgetbotButtonAnimator(AnonymousClass1 r0) {
            return r0.botButtonAnimator;
        }

        /* renamed from: -$$Nest$fputbotButtonAnimator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m9539$$Nest$fputbotButtonAnimator(AnonymousClass1 r0, ValueAnimator r1) {
            r0.botButtonAnimator = r1;
        }

        public AnonymousClass1(ChatAttachAlert r1, ChatAttachAlertBotWebViewLayout r2, String r3, long r4) {
            this.this$0 = r1;
            this.val$webViewLayout = r2;
            this.val$startCommand = r3;
            this.val$id = r4;
        }

        private /* synthetic */ void lambda$onSetupMainButton$6(ValueAnimator r3) {
            float r32 = ((Float) r3.getAnimatedValue()).floatValue();
            this.this$0.buttonsRecyclerViewWrapper.setAlpha(1.0f - r32);
            ChatAttachAlert.m9438$$Nest$fgetbotMainButtonTextView(this.this$0).setAlpha(r32);
            ChatAttachAlert.m9502$$Nest$fputbotMainButtonOffsetY(this.this$0, r32 * AndroidUtilities.dp(36.0f));
            ChatAttachAlert r2 = this.this$0;
            r2.buttonsRecyclerViewWrapper.setTranslationY(ChatAttachAlert.m9437$$Nest$fgetbotMainButtonOffsetY(r2));
        }

        private /* synthetic */ void lambda$onWebAppSetActionBarColor$1(int r2, int r3, ChatAttachAlertBotWebViewLayout r4, BotWebViewMenuContainer$ActionBarColorsAnimating r5, ValueAnimator r6) {
            float r62 = ((Float) r6.getAnimatedValue()).floatValue();
            int r22 = ColorUtils.blendARGB(r2, r3, r62);
            ChatAttachAlert.m9513$$Nest$fputoverridenWebviewBackgroundColor(this.this$0, r22);
            ChatAttachAlert.m9510$$Nest$fputhasOverridenWebviewBackgroundColor(this.this$0, true);
            ActionBar r32 = this.this$0.actionBar;
            if (r32 == null) goto L5;
            r32.updateColors();
            this.this$0.actionBar.invalidate();
        L5:
            ChatAttachAlert.m9463$$Nest$fgetiBlur3SourceColor(this.this$0).setColor(r22);
            if (ChatAttachAlert.m9460$$Nest$fgetfadeView(this.this$0) == null) goto L8;
            ChatAttachAlert.m9460$$Nest$fgetfadeView(this.this$0).invalidate();
        L8:
            r4.setCustomActionBarBackground(r22);
            ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).invalidate();
            this.this$0.sizeNotifierFrameLayout.invalidate();
            r5.updateActionBar(this.this$0.actionBar, r62);
        }

        private /* synthetic */ boolean lambda$onWebAppSwitchInlineQuery$5(TLRPC.User r1, String r2, OverlayActionBarLayoutDialog r3, DialogsActivity r4, ArrayList r5, CharSequence r6, boolean r7, boolean r8, int r9, int r10, TopicsFragment r11) {
            long r42 = ((MessagesStorage.TopicKey) r5.get(0)).dialogId;
            Bundle r62 = new Bundle();
            r62.putBoolean("scrollToTopOnResume", true);
            if (DialogObject.isEncryptedDialog(r42) == false) goto L6;
            r62.putInt("enc_id", DialogObject.getEncryptedChatId(r42));
        L9:
            r62.putString("start_text", "@" + UserObject.getPublicUsername(r1) + " " + r2);
            ChatAttachAlert r12 = this.this$0;
            BaseFragment r22 = r12.baseFragment;
            if (MessagesController.getInstance(r12.currentAccount).checkCanOpenChat(r62, r22) == false) goto L12;
            r3.dismiss();
            this.this$0.dismiss(true);
            r22.presentFragment(new INavigationLayout.NavigationParams(new ChatActivity(r62)).setRemoveLast(true));
        L12:
            return true;
        L6:
            if (DialogObject.isUserDialog(r42) == false) goto L8;
            r62.putLong("user_id", r42);
            goto L9
        L8:
            r62.putLong("chat_id", -r42);
            goto L9
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public boolean isClipboardAvailable() {
            if (MediaDataController.getInstance(this.this$0.currentAccount).botInAttachMenu(this.val$id) == false) goto L5;
            return true;
        L5:
            if (MessagesController.getInstance(this.this$0.currentAccount).whitelistedBots.contains(Long.valueOf(this.val$id)) == true) goto L11;
            return false;
        L11:
            return true;
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onCloseRequested(Runnable r3) {
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0) == this.val$webViewLayout) goto L5;
            return;
        L5:
            this.this$0.setFocusable(false);
            this.this$0.getWindow().setSoftInputMode(48);
            this.this$0.dismiss();
            AndroidUtilities.runOnUIThread(new ChatAttachAlert$1$$ExternalSyntheticLambda3(r3), 150);
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onSetBackButtonVisible(boolean r1) {
            ImageView r0 = this.this$0.actionBar.getBackButton();
            if (r1 == false) goto L5;
            int r12 = R.drawable.ic_ab_back;
        L6:
            AndroidUtilities.updateImageViewImageAnimated(r0, r12);
            return;
        L5:
            r12 = R.drawable.ic_close_white;
            goto L6
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onSetSettingsButtonVisible(boolean r1) {
            ActionBarMenuSubItem r0 = this.val$webViewLayout.settingsItem;
            if (r0 == null) goto L9;
            if (r1 == false) goto L6;
            int r12 = 0;
        L7:
            r0.setVisibility(r12);
            return;
        L6:
            r12 = 8;
            goto L7
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onSetupMainButton(boolean r4, boolean r5, String r6, long r7, int r9, int r10, boolean r11, boolean r12) {
            AttachAlertLayout r122 = ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0);
            ChatAttachAlertBotWebViewLayout r0 = this.val$webViewLayout;
            if (r122 == r0) goto L5;
            return;
        L5:
            if (r0.isBotButtonAvailable() == false) goto L7;
        L9:
            ChatAttachAlert.m9438$$Nest$fgetbotMainButtonTextView(this.this$0).setClickable(r5);
            SpannableStringBuilder r52 = new SpannableStringBuilder();
            if (r7 == 0) goto L12;
            r52.append("* ");
            r52.append(r6);
            r52.setSpan(new AnimatedEmojiSpan(r7, 1.4f, ChatAttachAlert.m9438$$Nest$fgetbotMainButtonTextView(this.this$0).getPaint().getFontMetricsInt()), 0, 1, 33);
            ChatAttachAlert.m9438$$Nest$fgetbotMainButtonTextView(this.this$0).setText(r52);
        L13:
            ChatAttachAlert.m9438$$Nest$fgetbotMainButtonTextView(this.this$0).setTextColor(r10);
            ChatAttachAlert.m9438$$Nest$fgetbotMainButtonTextView(this.this$0).setEmojiColor(r10);
            ChatAttachAlert.m9438$$Nest$fgetbotMainButtonTextView(this.this$0).setBackground(BotWebViewContainer.getMainButtonRippleDrawable(r9));
            float r8 = 0.0f;
            float r92 = 1.0f;
            if (ChatAttachAlert.m9436$$Nest$fgetbotButtonWasVisible(this.this$0) == r4) goto L25;
            ChatAttachAlert.m9501$$Nest$fputbotButtonWasVisible(this.this$0, r4);
            ValueAnimator r53 = this.botButtonAnimator;
            if (r53 == null) goto L18;
            r53.cancel();
        L18:
            if (r4 == false) goto L20;
            float r54 = 0.0f;
        L21:
            if (r4 == false) goto L23;
            float r123 = 1.0f;
        L24:
            ValueAnimator r55 = ValueAnimator.ofFloat(new float[]{r54, r123}).setDuration(250);
            this.botButtonAnimator = r55;
            r55.addUpdateListener(new ChatAttachAlert$1$$ExternalSyntheticLambda2(this));
            this.botButtonAnimator.addListener(new C00471(this, r4));
            this.botButtonAnimator.start();
            goto L25
        L23:
            r123 = 0.0f;
            goto L24
        L20:
            r54 = 1.0f;
        L25:
            ChatAttachAlert.m9439$$Nest$fgetbotProgressView(this.this$0).setProgressColor(r10);
            if (ChatAttachAlert.m9435$$Nest$fgetbotButtonProgressWasVisible(this.this$0) == r11) goto L44;
            ChatAttachAlert.m9439$$Nest$fgetbotProgressView(this.this$0).animate().cancel();
            if (r11 == false) goto L30;
            ChatAttachAlert.m9439$$Nest$fgetbotProgressView(this.this$0).setAlpha(0.0f);
            ChatAttachAlert.m9439$$Nest$fgetbotProgressView(this.this$0).setVisibility(0);
        L30:
            ViewPropertyAnimator r42 = ChatAttachAlert.m9439$$Nest$fgetbotProgressView(this.this$0).animate();
            if (r11 == false) goto L33;
            r8 = 1.0f;
        L33:
            ViewPropertyAnimator r43 = r42.alpha(r8);
            if (r11 == false) goto L36;
            float r82 = 1.0f;
        L37:
            ViewPropertyAnimator r44 = r43.scaleX(r82);
            if (r11 == true) goto L41;
            r92 = 0.1f;
        L41:
            r44.scaleY(r92).setDuration(250).setListener(new AnonymousClass2(this, r11)).start();
            return;
        L36:
            r82 = 0.1f;
            goto L37
        L44:
            return;
        L12:
            ChatAttachAlert.m9438$$Nest$fgetbotMainButtonTextView(this.this$0).setText(r6);
            goto L13
        L7:
            if (this.val$startCommand != null) goto L9;
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onSetupSecondaryButton(boolean r1, boolean r2, String r3, long r4, int r6, int r7, boolean r8, boolean r9, String r10) {
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onWebAppExpand() {
            AttachAlertLayout r0 = ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0);
            ChatAttachAlertBotWebViewLayout r1 = this.val$webViewLayout;
            if (r0 == r1) goto L6;
            return;
        L6:
            if (r1.canExpandByRequest() == false) goto L9;
            this.val$webViewLayout.scrollToTop();
            return;
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onWebAppOpenInvoice(TLRPC.InputInvoice r9, String r10, TLObject r11) {
            ChatAttachAlert r0 = this.this$0;
            BaseFragment r1 = r0.baseFragment;
            if ((r11 instanceof TLRPC.TL_payments_paymentFormStars) == false) goto L7;
            AlertDialog r02 = new AlertDialog(this.this$0.getContext(), 3);
            r02.showDelayed(150);
            StarsController.getInstance(this.this$0.currentAccount).openPaymentForm(null, r9, (TLRPC.TL_payments_paymentFormStars) r11, new ChatAttachAlert$1$$ExternalSyntheticLambda4(r02), new ChatAttachAlert$1$$ExternalSyntheticLambda5(this.val$webViewLayout, r10));
            AndroidUtilities.hideKeyboard(this.val$webViewLayout);
            return;
        L7:
            if ((r11 instanceof TLRPC.PaymentForm) == false) goto L10;
            TLRPC.PaymentForm r112 = (TLRPC.PaymentForm) r11;
            MessagesController.getInstance(r0.currentAccount).putUsers(r112.users, false);
            PaymentFormActivity r92 = new PaymentFormActivity(r112, r10, r1);
        L13:
            if (r92 == null) goto L16;
            this.val$webViewLayout.scrollToTop();
            AndroidUtilities.hideKeyboard(this.val$webViewLayout);
            OverlayActionBarLayoutDialog r113 = new OverlayActionBarLayoutDialog(r1.getParentActivity(), ChatAttachAlert.access$200(this.this$0));
            r113.show();
            r92.setPaymentFormCallback(new ChatAttachAlert$1$$ExternalSyntheticLambda6(r113, this.val$webViewLayout, r10));
            r92.setResourcesProvider(ChatAttachAlert.access$300(this.this$0));
            r113.addFragment(r92);
            return;
        L16:
            return;
        L10:
            if ((r11 instanceof TLRPC.PaymentReceipt) == false) goto L12;
            r92 = new PaymentFormActivity((TLRPC.PaymentReceipt) r11);
            goto L13
        L12:
            r92 = null;
            goto L13
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onWebAppSetActionBarColor(int r7, int r8, boolean r9) {
            int r2 = ChatAttachAlert.m9463$$Nest$fgetiBlur3SourceColor(this.this$0).getColor();
            BotWebViewMenuContainer$ActionBarColorsAnimating r5 = new BotWebViewMenuContainer$ActionBarColorsAnimating();
            int r0 = 0;
            if (ChatAttachAlert.m9473$$Nest$fgetoverrideBackgroundColor(this.this$0) == false) goto L5;
            int r72 = r2;
        L6:
            r5.setFrom(r72, ChatAttachAlert.access$000(this.this$0));
            ChatAttachAlert.m9512$$Nest$fputoverrideBackgroundColor(this.this$0, r9);
            if (ChatAttachAlert.m9473$$Nest$fgetoverrideBackgroundColor(this.this$0) == false) goto L9;
            r0 = r8;
        L9:
            r5.setTo(r0, ChatAttachAlert.access$100(this.this$0));
            ValueAnimator r73 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(200);
            r73.setInterpolator(CubicBezierInterpolator.DEFAULT);
            r73.addUpdateListener(new ChatAttachAlert$1$$ExternalSyntheticLambda1(this, r2, r8, this.val$webViewLayout, r5));
            r73.start();
            return;
        L5:
            r72 = 0;
            goto L6
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onWebAppSetBackgroundColor(int r1) {
            this.val$webViewLayout.setCustomBackground(r1);
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onWebAppSetupClosingBehavior(boolean r1) {
            this.val$webViewLayout.setNeedCloseConfirmation(r1);
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onWebAppSwipingBehavior(boolean r1) {
            this.val$webViewLayout.setAllowSwipes(r1);
        }

        @Override // org.telegram.ui.web.BotWebViewContainer.Delegate
        public void onWebAppSwitchInlineQuery(TLRPC.User r5, String r6, List<String> r7) {
            if (r7.isEmpty() == false) goto L9;
            BaseFragment r72 = this.this$0.baseFragment;
            if ((r72 instanceof ChatActivity) == false) goto L7;
            ((ChatActivity) r72).getChatActivityEnterView().setFieldText("@" + UserObject.getPublicUsername(r5) + " " + r6);
        L7:
            this.this$0.dismiss(true);
            return;
        L9:
            Bundle r0 = new Bundle();
            r0.putInt("dialogsType", 14);
            r0.putBoolean("onlySelect", true);
            r0.putBoolean("allowGroups", r7.contains("groups"));
            r0.putBoolean("allowLegacyGroups", r7.contains("groups"));
            r0.putBoolean("allowMegagroups", r7.contains("groups"));
            r0.putBoolean("allowUsers", r7.contains("users"));
            r0.putBoolean("allowChannels", r7.contains("channels"));
            r0.putBoolean("allowBots", r7.contains("bots"));
            DialogsActivity r73 = new DialogsActivity(r0);
            OverlayActionBarLayoutDialog r02 = new OverlayActionBarLayoutDialog(this.this$0.getContext(), ChatAttachAlert.access$400(this.this$0));
            r73.setDelegate(new ChatAttachAlert$1$$ExternalSyntheticLambda0(this, r5, r6, r02));
            r02.show();
            r02.addFragment(r73);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$10, reason: invalid class name */
    public class AnonymousClass10 implements PhotoPickerActivity.PhotoPickerActivityDelegate {
        private boolean sendPressed;
        final /* synthetic */ ChatAttachAlert this$0;
        final /* synthetic */ ArrayList val$order;
        final /* synthetic */ HashMap val$photos;

        public AnonymousClass10(ChatAttachAlert r1, HashMap r2, ArrayList r3) {
            this.this$0 = r1;
            this.val$photos = r2;
            this.val$order = r3;
        }

        @Override // org.telegram.ui.PhotoPickerActivity.PhotoPickerActivityDelegate
        public void actionButtonPressed(boolean r7, boolean r8, int r9, int r10) {
            if (r7 == false) goto L5;
            return;
        L5:
            if (this.val$photos.isEmpty() == false) goto L7;
            return;
        L7:
            if (this.sendPressed == true) goto L34;
            this.sendPressed = true;
            ArrayList<SendMessagesHelper.SendingMediaInfo> r102 = new ArrayList();
            int r0 = 0;
        L11:
            if (r0 >= this.val$order.size()) goto L26;
            Object r1 = this.val$photos.get(this.val$order.get(r0));
            SendMessagesHelper.SendingMediaInfo r2 = new SendMessagesHelper.SendingMediaInfo();
            r102.add(r2);
            MediaController.SearchImage r12 = (MediaController.SearchImage) r1;
            String r3 = r12.imagePath;
            if (r3 == null) goto L15;
            r2.path = r3;
        L16:
            r2.thumbPath = r12.thumbPath;
            r2.videoEditedInfo = r12.editedInfo;
            CharSequence r32 = r12.caption;
            if (r32 == null) goto L19;
            String r33 = r32.toString();
        L20:
            r2.caption = r33;
            r2.entities = r12.entities;
            r2.masks = r12.stickers;
            r2.ttl = r12.ttl;
            TLRPC.BotInlineResult r34 = r12.inlineResult;
            if (r34 == null) goto L25;
            if (r12.type != 1) goto L25;
            r2.inlineResult = r34;
            r2.params = r12.params;
        L25:
            r12.date = (int) (System.currentTimeMillis() / 1000);
            r0 = r0 + 1;
            goto L11
        L19:
            r33 = null;
            goto L20
        L15:
            r2.searchImage = r12;
            goto L16
        L26:
            ((ChatActivity) this.this$0.baseFragment).didSelectSearchPhotos(r102, r8, r9);
            return;
        }

        @Override // org.telegram.ui.PhotoPickerActivity.PhotoPickerActivityDelegate
        public void onCaptionChanged(CharSequence r1) {
        }

        @Override // org.telegram.ui.PhotoPickerActivity.PhotoPickerActivityDelegate
        public void selectedPhotosChanged() {
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$11, reason: invalid class name */
    public class AnonymousClass11 extends PhotoViewer.EmptyPhotoViewerProvider {
        final /* synthetic */ ChatAttachAlert this$0;
        final /* synthetic */ MediaController.PhotoEntry val$entry;

        public static /* synthetic */ void $r8$lambda$8AKsV6FBSbBgxsyA4HqxMYYZQAc(AnonymousClass11 r0, MediaController.PhotoEntry r1, boolean r2, int r3, boolean r4, Long r5) {
            r0.lambda$sendButtonPressed$0(r1, r2, r3, r4, r5);
        }

        public AnonymousClass11(ChatAttachAlert r1, MediaController.PhotoEntry r2) {
            this.this$0 = r1;
            this.val$entry = r2;
        }

        private /* synthetic */ void lambda$sendButtonPressed$0(MediaController.PhotoEntry r13, boolean r14, int r15, boolean r16, Long r17) {
            ChatAttachAlertPhotoLayout.selectedPhotosOrder.clear();
            ChatAttachAlertPhotoLayout.selectedPhotos.clear();
            ChatAttachAlertPhotoLayout.selectedPhotosOrder.add(0);
            ChatAttachAlertPhotoLayout.selectedPhotos.put(0, r13);
            this.this$0.delegate.didPressedButton(7, true, r14, r15, 0, 0, isCaptionAbove(), r16, r17.longValue());
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean allowCaption() {
            return false;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void sendButtonPressed(int r9, VideoEditedInfo r10, boolean r11, int r12, int r13, boolean r14) {
            ChatAttachAlert r92 = this.this$0;
            r92.sent = true;
            if (r92.delegate != null) goto L5;
            return;
        L5:
            this.val$entry.editedInfo = r10;
            AlertsCreator.ensurePaidMessageConfirmation(r92.currentAccount, getDialogId(), this.this$0.getAdditionalMessagesCount() + 1, new ChatAttachAlert$11$$ExternalSyntheticLambda0(this, this.val$entry, r11, r12, r14));
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$12, reason: invalid class name */
    public class AnonymousClass12 extends FrameLayout {
        final /* synthetic */ ChatAttachAlert this$0;

        public AnonymousClass12(ChatAttachAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r2) {
            if (this.this$0.headerView.getVisibility() == 0) goto L7;
            return false;
        L7:
            return super.onInterceptTouchEvent(r2);
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (this.this$0.headerView.getVisibility() == 0) goto L7;
            return false;
        L7:
            return super.onTouchEvent(r2);
        }

        @Override // android.view.View
        public void setAlpha(float r2) {
            super.setAlpha(r2);
            ChatAttachAlert.m9531$$Nest$mupdateSelectedPosition(this.this$0, 0);
            ChatAttachAlert.access$7500(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$13, reason: invalid class name */
    public class AnonymousClass13 extends FrameLayout {
        private final GradientClip clip;
        private final Path path;
        final /* synthetic */ ChatAttachAlert this$0;

        public AnonymousClass13(ChatAttachAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.path = new Path();
            this.clip = new GradientClip();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r8) {
            float r0 = AndroidUtilities.dp(20.0f);
            RectF r1 = AndroidUtilities.rectTmp;
            r1.set(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
            this.path.rewind();
            this.path.addRoundRect(r1, r0, r0, Path.Direction.CW);
            r8.save();
            r8.clipRect(0.0f, 0.0f, getWidth(), getHeight() * getAlpha());
            r8.clipPath(this.path);
            r8.saveLayerAlpha(r1, 255, 31);
            super.dispatchDraw(r8);
            r1.set(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getPaddingTop() + AndroidUtilities.dp(6.0f));
            this.clip.draw(r8, r1, 1, 1.0f);
            r1.set(getPaddingLeft(), (getHeight() - getPaddingBottom()) - AndroidUtilities.dp(6.0f), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
            this.clip.draw(r8, r1, 3, 1.0f);
            r8.restore();
            r8.restore();
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$14, reason: invalid class name */
    public class AnonymousClass14 extends FrameLayout {
        final /* synthetic */ ChatAttachAlert this$0;

        public AnonymousClass14(ChatAttachAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            ChatAttachAlert r0 = this.this$0;
            if (r0.isPollAttach == true) goto L5;
        L8:
            super.onMeasure(r3, r4);
            return;
        L5:
            if (ChatAttachAlert.m9479$$Nest$fgetpollAllowedLayouts(r0) == 0) goto L8;
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(r3), (Integer.bitCount(ChatAttachAlert.m9479$$Nest$fgetpollAllowedLayouts(this.this$0)) * AndroidUtilities.dp(80.0f)) + AndroidUtilities.dp(36.0f)), TLObject.FLAG_30), r4);
        }

        @Override // android.view.View
        public void setAlpha(float r1) {
            super.setAlpha(r1);
            ChatAttachAlert.m9530$$Nest$mupdateCameraButtonVisibility(this.this$0);
        }

        @Override // android.view.View
        public void setTranslationY(float r1) {
            super.setTranslationY(r1);
            ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).onButtonsTranslationYUpdated();
            ChatAttachAlert.m9530$$Nest$mupdateCameraButtonVisibility(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$15, reason: invalid class name */
    public class AnonymousClass15 extends RecyclerListView {
        private final BoolAnimator hasFadeLeft;
        private final BoolAnimator hasFadeRight;
        private boolean mHasFadeLeft;
        private boolean mHasFadeRight;
        private final Paint paintLeft;
        private final Paint paintRight;
        private final Shader shaderLeft;
        private final Shader shaderRight;
        final /* synthetic */ ChatAttachAlert this$0;

        public AnonymousClass15(ChatAttachAlert r20, Context r21) {
            this.this$0 = r20;
            super(r21);
            CubicBezierInterpolator r2 = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.hasFadeLeft = new BoolAnimator(this, r2, 320);
            this.hasFadeRight = new BoolAnimator(this, r2, 320);
            Shader.TileMode r18 = Shader.TileMode.CLAMP;
            LinearGradient r5 = new LinearGradient(0.0f, 0.0f, AndroidUtilities.dp(8.0f), 0.0f, new int[]{0, -16777216}, null, r18);
            this.shaderLeft = r5;
            LinearGradient r11 = new LinearGradient(0.0f, 0.0f, AndroidUtilities.dp(8.0f), 0.0f, new int[]{-16777216, 0}, null, r18);
            this.shaderRight = r11;
            Paint r1 = new Paint(1);
            this.paintLeft = r1;
            Paint r3 = new Paint(1);
            this.paintRight = r3;
            r1.setShader(r5);
            PorterDuff.Mode r22 = PorterDuff.Mode.DST_IN;
            r1.setXfermode(new PorterDuffXfermode(r22));
            r3.setShader(r11);
            r3.setXfermode(new PorterDuffXfermode(r22));
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r3) {
            this.mHasFadeRight = false;
            this.mHasFadeLeft = false;
            super.dispatchDraw(r3);
            this.hasFadeLeft.setValue(this.mHasFadeLeft, true);
            this.hasFadeRight.setValue(this.mHasFadeRight, true);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean drawChild(Canvas r15, View r16, long r17) {
            float r2 = r16.getX();
            float r3 = r16.getWidth() + r2;
            boolean r5 = true;
            if (r2 >= AndroidUtilities.dp(10.0f)) goto L5;
            boolean r22 = true;
        L7:
            if (r3 <= (getMeasuredWidth() - AndroidUtilities.dp(10.0f))) goto L9;
            boolean r7 = true;
        L10:
            if (r22 == true) goto L14;
            if (r7 == true) goto L14;
            r5 = false;
        L14:
            this.mHasFadeLeft |= r22;
            this.mHasFadeRight |= r7;
            r15.save();
            if (r5 == false) goto L17;
            r15.clipRect(AndroidUtilities.dp(19.0f), 0, getMeasuredWidth() - AndroidUtilities.dp(19.0f), getMeasuredHeight());
        L17:
            boolean r9 = super.drawChild(r15, r16, r17);
            r15.restore();
            if (r22 == false) goto L20;
            float r23 = AndroidUtilities.dp(11.0f);
            r15.saveLayer(r23, getPaddingTop(), AndroidUtilities.dp(19.0f), getMeasuredHeight() - getPaddingBottom(), null);
            super.drawChild(r15, r16, r17);
            r15.save();
            r15.translate(r23 - (AndroidUtilities.dp(8.0f) * (1.0f - this.hasFadeLeft.getFloatValue())), 0.0f);
            r15.drawPaint(this.paintLeft);
            r15.restore();
            r15.restore();
        L20:
            if (r7 == false) goto L22;
            float r24 = getMeasuredWidth() - AndroidUtilities.dp(19.0f);
            r15.saveLayer(r24, getPaddingTop(), getMeasuredWidth() - AndroidUtilities.dp(11.0f), getMeasuredHeight() - getPaddingBottom(), null);
            super.drawChild(r15, r16, r17);
            r15.save();
            r15.translate(r24 + (AndroidUtilities.dp(8.0f) * (1.0f - this.hasFadeRight.getFloatValue())), 0.0f);
            r15.drawPaint(this.paintRight);
            r15.restore();
            r15.restore();
        L22:
            return r9;
        L9:
            r7 = false;
            goto L10
        L5:
            r22 = false;
            goto L7
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public void onMeasure(int r8, int r9) {
            int r0 = getChildCount();
            int r1 = (View.MeasureSpec.getSize(r8) - getPaddingLeft()) - getPaddingRight();
            float r2 = 0.0f;
            int r3 = 0;
            int r4 = 0;
        L3:
            if (r4 >= r0) goto L9;
            View r5 = getChildAt(r4);
            if ((r5 instanceof AttachButtonBase) == false) goto L7;
            r2 = r2 + ((AttachButtonBase) r5).glassTabView.measureAttachTabWidth();
        L7:
            r4 = r4 + 1;
            goto L3
        L9:
            if (r1 <= r2) goto L12;
            if (r0 <= 0) goto L12;
            int r12 = (int) Math.floor((r1 - r2) / r0);
        L13:
            if (r3 >= r0) goto L18;
            View r22 = getChildAt(r3);
            if ((r22 instanceof AttachButtonBase) == false) goto L17;
            ((AttachButtonBase) r22).glassTabView.setAdditionalWidth(r12);
        L17:
            r3 = r3 + 1;
            goto L13
        L18:
            super.onMeasure(r8, r9);
            return;
        L12:
            r12 = 0;
            goto L13
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$16, reason: invalid class name */
    public class AnonymousClass16 extends View {
        final /* synthetic */ ChatAttachAlert this$0;

        public AnonymousClass16(ChatAttachAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void draw(Canvas r1) {
            super.draw(r1);
            ChatAttachAlert.m9440$$Nest$fgetbottomFadeDrawable(this.this$0).draw(r1);
        }

        @Override // android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            super.onSizeChanged(r1, r2, r3, r4);
            ChatAttachAlert.m9440$$Nest$fgetbottomFadeDrawable(this.this$0).setBounds(0, (r2 - AndroidUtilities.navigationBarHeight) - AndroidUtilities.dp(48.0f), r1, r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$17, reason: invalid class name */
    public class AnonymousClass17 extends FrameLayout {
        final /* synthetic */ ChatAttachAlert this$0;

        public static /* synthetic */ void $r8$lambda$Y7qTreiSaKEN638mPugttPnRhfw(AnonymousClass17 r0, ValueAnimator r1) {
            r0.lambda$onDraw$0(r1);
        }

        public AnonymousClass17(ChatAttachAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        private /* synthetic */ void lambda$onDraw$0(ValueAnimator r2) {
            ChatAttachAlert.m9504$$Nest$fputcaptionEditTextTopOffset(this.this$0, ((Float) r2.getAnimatedValue()).floatValue());
            ChatAttachAlert.m9443$$Nest$fgetcaptionContainer(this.this$0).invalidate();
            ChatAttachAlert.m9461$$Nest$fgetframeLayout2(this.this$0).invalidate();
            invalidate();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r5) {
            r5.save();
            r5.clipRect(0.0f, ChatAttachAlert.m9445$$Nest$fgetcaptionEditTextTopOffset(this.this$0), getMeasuredWidth(), getMeasuredHeight());
            super.dispatchDraw(r5);
            r5.restore();
        }

        @Override // android.view.View
        public void onDraw(Canvas r5) {
            if (ChatAttachAlert.m9443$$Nest$fgetcaptionContainer(this.this$0).getAlpha() > 0.0f) goto L6;
            return;
        L6:
            if (ChatAttachAlert.m9447$$Nest$fgetchatActivityEnterViewAnimateFromTop(this.this$0) != 0.0f) goto L8;
            return;
        L8:
            if (ChatAttachAlert.m9447$$Nest$fgetchatActivityEnterViewAnimateFromTop(this.this$0) != (ChatAttachAlert.m9461$$Nest$fgetframeLayout2(this.this$0).getTop() + ChatAttachAlert.m9447$$Nest$fgetchatActivityEnterViewAnimateFromTop(this.this$0))) goto L10;
            return;
        L10:
            if (ChatAttachAlert.m9492$$Nest$fgettopBackgroundAnimator(this.this$0) == null) goto L12;
            ChatAttachAlert.m9492$$Nest$fgettopBackgroundAnimator(this.this$0).cancel();
        L12:
            ChatAttachAlert r52 = this.this$0;
            ChatAttachAlert.m9504$$Nest$fputcaptionEditTextTopOffset(r52, ChatAttachAlert.m9447$$Nest$fgetchatActivityEnterViewAnimateFromTop(r52) - (ChatAttachAlert.m9461$$Nest$fgetframeLayout2(this.this$0).getTop() + ChatAttachAlert.m9445$$Nest$fgetcaptionEditTextTopOffset(this.this$0)));
            ChatAttachAlert r53 = this.this$0;
            ChatAttachAlert.m9516$$Nest$fputtopBackgroundAnimator(r53, ValueAnimator.ofFloat(new float[]{ChatAttachAlert.m9445$$Nest$fgetcaptionEditTextTopOffset(r53), 0.0f}));
            ChatAttachAlert.m9492$$Nest$fgettopBackgroundAnimator(this.this$0).addUpdateListener(new ChatAttachAlert$17$$ExternalSyntheticLambda0(this));
            ChatAttachAlert.m9492$$Nest$fgettopBackgroundAnimator(this.this$0).setInterpolator(CubicBezierInterpolator.DEFAULT);
            ChatAttachAlert.m9492$$Nest$fgettopBackgroundAnimator(this.this$0).setDuration(200);
            ChatAttachAlert.m9492$$Nest$fgettopBackgroundAnimator(this.this$0).start();
            ChatAttachAlert.m9505$$Nest$fputchatActivityEnterViewAnimateFromTop(this.this$0, 0.0f);
        }

        @Override // android.view.View
        public void setAlpha(float r1) {
            super.setAlpha(r1);
            invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$18, reason: invalid class name */
    public class AnonymousClass18 extends FrameLayout {
        private final GradientClip clip;
        private int lastHeight;
        private final Path path;
        final /* synthetic */ ChatAttachAlert this$0;

        public AnonymousClass18(ChatAttachAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.path = new Path();
            this.clip = new GradientClip();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r9) {
            if (ChatAttachAlert.m9444$$Nest$fgetcaptionContainerBg(this.this$0) == null) goto L5;
            ChatAttachAlert.m9444$$Nest$fgetcaptionContainerBg(this.this$0).setBounds(0, (int) ChatAttachAlert.m9445$$Nest$fgetcaptionEditTextTopOffset(this.this$0), getMeasuredWidth(), getMeasuredHeight());
            ChatAttachAlert.m9444$$Nest$fgetcaptionContainerBg(this.this$0).draw(r9);
        L5:
            float r0 = AndroidUtilities.dp(20.0f);
            int r2 = AndroidUtilities.dp(7.0f);
            int r1 = AndroidUtilities.dp(7.0f);
            RectF r3 = AndroidUtilities.rectTmp;
            float r5 = r2;
            r3.set(getPaddingLeft(), r5, getWidth() - getPaddingRight(), getHeight() - r1);
            this.path.rewind();
            this.path.addRoundRect(r3, r0, r0, Path.Direction.CW);
            r9.save();
            r9.clipPath(this.path);
            r9.saveLayerAlpha(r3, 255, 31);
            super.dispatchDraw(r9);
            r3.set(getPaddingLeft(), r5, getWidth() - getPaddingRight(), r2 + AndroidUtilities.dp(6.0f));
            this.clip.draw(r9, r3, 1, 1.0f);
            r3.set(getPaddingLeft(), (getHeight() - r1) - AndroidUtilities.dp(6.0f), getWidth() - getPaddingRight(), getHeight() - r1);
            this.clip.draw(r9, r3, 3, 1.0f);
            r9.restore();
            r9.restore();
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r3, int r4, int r5, int r6, int r7) {
            int r0 = this.lastHeight - ChatAttachAlert.m9428$$Nest$fgetaiButton(this.this$0).getTop();
            super.onLayout(r3, r4, r5, r6, r7);
            this.lastHeight = getHeight();
            if (ChatAttachAlert.m9428$$Nest$fgetaiButton(this.this$0).getVisibility() == 0) goto L5;
            return;
        L5:
            if ((getHeight() - ChatAttachAlert.m9428$$Nest$fgetaiButton(this.this$0).getTop()) == r0) goto L9;
            ChatAttachAlert.m9428$$Nest$fgetaiButton(this.this$0).setTranslationY(((getHeight() - ChatAttachAlert.m9428$$Nest$fgetaiButton(this.this$0).getTop()) - r0) + ChatAttachAlert.m9428$$Nest$fgetaiButton(this.this$0).getTranslationY());
            ChatAttachAlert.m9428$$Nest$fgetaiButton(this.this$0).animate().translationY(0.0f).setDuration(320).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$19, reason: invalid class name */
    public class AnonymousClass19 extends EditTextEmoji {
        private ValueAnimator messageEditTextAnimator;
        private int messageEditTextPredrawHeigth;
        private int messageEditTextPredrawScrollY;
        private boolean shouldAnimateEditTextWithBounds;
        final /* synthetic */ ChatAttachAlert this$0;

        public static /* synthetic */ void $r8$lambda$DVyMIZtp0YBOstMInWnI5UggLOg(AnonymousClass19 r0, EditTextCaption r1, ValueAnimator r2) {
            r0.lambda$dispatchDraw$0(r1, r2);
        }

        public AnonymousClass19(ChatAttachAlert r1, Context r2, SizeNotifierFrameLayout r3, BaseFragment r4, int r5, boolean r6, Theme.ResourcesProvider r7) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7);
        }

        private /* synthetic */ void lambda$dispatchDraw$0(EditTextCaption r1, ValueAnimator r2) {
            r1.setOffsetY(((Float) r2.getAnimatedValue()).floatValue());
            this.this$0.updateCommentTextViewPosition();
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0) != ChatAttachAlert.m9475$$Nest$fgetphotoLayout(this.this$0)) goto L6;
            ChatAttachAlert.m9475$$Nest$fgetphotoLayout(this.this$0).onContainerTranslationUpdated(this.this$0.currentPanTranslationY);
            return;
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public void bottomPanelTranslationY(float r3) {
            ChatAttachAlert.m9503$$Nest$fputbottomPannelTranslation(this.this$0, r3);
            ChatAttachAlert.m9461$$Nest$fgetframeLayout2(this.this$0).setTranslationY(r3);
            ChatAttachAlert.m9461$$Nest$fgetframeLayout2(this.this$0).invalidate();
            ChatAttachAlert.m9521$$Nest$mcheckUi_writeButtonContainerY(this.this$0);
            ChatAttachAlert r2 = this.this$0;
            r2.updateLayout(ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r2), true, 0);
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public void closeParent() {
            ChatAttachAlert.access$7601(this.this$0);
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public void createEmojiView() {
            super.createEmojiView();
            EmojiView r1 = getEmojiView();
            if (r1 == null) goto L6;
            r1.shouldLightenBackground = false;
            r1.fixBottomTabContainerTranslation = false;
            r1.setShouldDrawBackground(false);
            r1.setBottomInset(AndroidUtilities.navigationBarHeight);
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r7) {
            if (this.shouldAnimateEditTextWithBounds == false) goto L8;
            EditTextCaption r0 = this.this$0.commentTextView.getEditText();
            r0.setOffsetY(r0.getOffsetY() - ((this.messageEditTextPredrawHeigth - r0.getMeasuredHeight()) + (this.messageEditTextPredrawScrollY - r0.getScrollY())));
            ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{r0.getOffsetY(), 0.0f});
            r1.addUpdateListener(new ChatAttachAlert$19$$ExternalSyntheticLambda0(this, r0));
            ValueAnimator r02 = this.messageEditTextAnimator;
            if (r02 == null) goto L7;
            r02.cancel();
        L7:
            this.messageEditTextAnimator = r1;
            r1.setDuration(200);
            r1.setInterpolator(CubicBezierInterpolator.DEFAULT);
            r1.start();
            this.shouldAnimateEditTextWithBounds = false;
        L8:
            super.dispatchDraw(r7);
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public void extendActionMode(ActionMode r3, Menu r4) {
            BaseFragment r0 = this.this$0.baseFragment;
            if ((r0 instanceof ChatActivity) == false) goto L5;
            ChatActivity.fillActionModeMenu(r4, ((ChatActivity) r0).getCurrentEncryptedChat(), true, true);
        L5:
            super.extendActionMode(r3, r4);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r4) {
            if (ChatAttachAlert.m9457$$Nest$fgetenterCommentEventSent(this.this$0) == true) goto L15;
            if (r4.getX() > this.this$0.commentTextView.getEditText().getLeft()) goto L7;
        L13:
            ChatAttachAlert r0 = this.this$0;
            r0.makeFocusable(r0.commentTextView.getEditText(), false);
            goto L15
        L7:
            if (r4.getX() >= this.this$0.commentTextView.getEditText().getRight()) goto L13;
            if (r4.getY() <= this.this$0.commentTextView.getEditText().getTop()) goto L13;
            if (r4.getY() >= this.this$0.commentTextView.getEditText().getBottom()) goto L13;
            ChatAttachAlert r02 = this.this$0;
            r02.makeFocusable(r02.commentTextView.getEditText(), true);
        L15:
            return super.onInterceptTouchEvent(r4);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            this.this$0.updateCommentTextViewPosition();
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public void onLineCountChanged(int r4, int r5) {
            boolean r0 = false;
            if (TextUtils.isEmpty(getEditText().getText()) == true) goto L5;
            this.shouldAnimateEditTextWithBounds = true;
            this.messageEditTextPredrawHeigth = getEditText().getMeasuredHeight();
            this.messageEditTextPredrawScrollY = getEditText().getScrollY();
            invalidate();
        L6:
            ChatAttachAlert r42 = this.this$0;
            if (r42.captionAbove == false) goto L9;
        L14:
            ChatAttachAlert.m9505$$Nest$fputchatActivityEnterViewAnimateFromTop(this.this$0, ChatAttachAlert.m9461$$Nest$fgetframeLayout2(r4).getTop() + ChatAttachAlert.m9445$$Nest$fgetcaptionEditTextTopOffset(this.this$0));
            ChatAttachAlert.m9461$$Nest$fgetframeLayout2(this.this$0).invalidate();
            this.this$0.updateCommentTextViewPosition();
            return;
        L9:
            if (r5 > 2) goto L11;
        L13:
            ChatAttachAlert.m9528$$Nest$mshowAiButton(r42, r0);
            goto L14
        L11:
            if (TextUtils.isEmpty(getEditText().getText().toString().trim()) == true) goto L13;
            r0 = true;
            goto L13
        L5:
            getEditText().animate().cancel();
            getEditText().setOffsetY(0.0f);
            this.shouldAnimateEditTextWithBounds = false;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimationProperties.FloatProperty<AttachAlertLayout> {
        final /* synthetic */ ChatAttachAlert this$0;

        public AnonymousClass2(ChatAttachAlert r1, String r2) {
            this.this$0 = r1;
            super(r2);
        }

        public Float get(AttachAlertLayout r1) {
            return Float.valueOf(this.this$0.translationProgress);
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object r1) {
            return get((AttachAlertLayout) r1);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
        public /* bridge */ /* synthetic */ void setValue(AttachAlertLayout r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(AttachAlertLayout r6, float r7) {
            ChatAttachAlert r62 = this.this$0;
            r62.translationProgress = r7;
            if (ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(r62) != null) goto L6;
            return;
        L6:
            if ((ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(this.this$0) instanceof ChatAttachAlertPhotoLayoutPreview) == false) goto L8;
        L28:
            int r63 = Math.max(ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(this.this$0).getWidth(), ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).getWidth());
            boolean r2 = ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(this.this$0) instanceof ChatAttachAlertPhotoLayoutPreview;
            ChatAttachAlert r3 = this.this$0;
            if (r2 == false) goto L31;
            ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r3).setTranslationX((-r63) * r7);
            ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(this.this$0).setTranslationX((1.0f - r7) * r63);
        L33:
            if (ChatAttachAlert.m9495$$Nest$fgetviewChangeAnimator(this.this$0) == null) goto L35;
            ChatAttachAlert.m9531$$Nest$mupdateSelectedPosition(this.this$0, 1);
        L35:
            this.this$0.blur3_InvalidateBlur();
            ChatAttachAlert.access$500(this.this$0).invalidate();
            return;
        L31:
            ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r3).setTranslationX(r63 * r7);
            ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(this.this$0).setTranslationX((-r63) * (1.0f - r7));
            goto L33
        L8:
            if ((ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0) instanceof ChatAttachAlertPhotoLayoutPreview) == true) goto L28;
            ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(this.this$0).setAlpha(r7);
            ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(this.this$0).onHideShowProgress(r7);
            int r32 = 0;
            if (ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(this.this$0) != ChatAttachAlert.m9480$$Nest$fgetpollLayout(this.this$0)) goto L13;
        L14:
            ChatAttachAlert r64 = this.this$0;
            if (ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(r64) != ChatAttachAlert.m9480$$Nest$fgetpollLayout(this.this$0)) goto L17;
            int r22 = 1;
        L18:
            ChatAttachAlert.m9531$$Nest$mupdateSelectedPosition(r64, r22);
        L20:
            if (ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(this.this$0) != ChatAttachAlert.m9491$$Nest$fgettodoLayout(this.this$0)) goto L22;
        L23:
            ChatAttachAlert r65 = this.this$0;
            if (ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(r65) != ChatAttachAlert.m9491$$Nest$fgettodoLayout(this.this$0)) goto L26;
            r32 = 1;
        L26:
            ChatAttachAlert.m9531$$Nest$mupdateSelectedPosition(r65, r32);
        L27:
            ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(this.this$0).setTranslationY(AndroidUtilities.dp(78.0f) * r7);
            ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).onHideShowProgress(1.0f - Math.min(1.0f, r7 / 0.7f));
            ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).onContainerTranslationUpdated(this.this$0.currentPanTranslationY);
            goto L33
        L22:
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0) != ChatAttachAlert.m9491$$Nest$fgettodoLayout(this.this$0)) goto L27;
        L17:
            r22 = 0;
            goto L18
        L13:
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0) != ChatAttachAlert.m9480$$Nest$fgetpollLayout(this.this$0)) goto L20;
            goto L14
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$20, reason: invalid class name */
    public class AnonymousClass20 implements TextWatcher {
        private boolean processChange;
        final /* synthetic */ ChatAttachAlert this$0;
        private boolean wasEmpty;

        /* renamed from: org.telegram.ui.Components.ChatAttachAlert$20$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass20 this$1;

            public AnonymousClass1(AnonymousClass20 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                ChatAttachAlert.m9446$$Nest$fgetcaptionLimitView(this.this$1.this$0).setVisibility(8);
            }
        }

        public AnonymousClass20(ChatAttachAlert r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r13) {
            if (this.wasEmpty != TextUtils.isEmpty(r13)) goto L5;
        L8:
            boolean r1 = false;
            if (this.processChange == false) goto L15;
            ImageSpan[] r0 = (ImageSpan[]) r13.getSpans(0, r13.length(), ImageSpan.class);
            int r3 = 0;
        L12:
            if (r3 >= r0.length) goto L14;
            r13.removeSpan(r0[r3]);
            r3 = r3 + 1;
            goto L12
        L14:
            Emoji.replaceEmoji(r13, this.this$0.commentTextView.getEditText().getPaint().getFontMetricsInt(), false);
            this.processChange = false;
        L15:
            ChatAttachAlert.m9506$$Nest$fputcodepointCount(this.this$0, Character.codePointCount(r13, 0, r13.length()));
            BoolAnimator r132 = ChatAttachAlert.m9429$$Nest$fgetanimatorCaptionNotEmpty(this.this$0);
            if (ChatAttachAlert.m9448$$Nest$fgetcodepointCount(this.this$0) <= 0) goto L18;
            boolean r02 = true;
        L19:
            r132.setValue(r02, true);
            if (ChatAttachAlert.m9452$$Nest$fgetcurrentLimit(this.this$0) <= 0) goto L38;
            int r133 = ChatAttachAlert.m9452$$Nest$fgetcurrentLimit(this.this$0) - ChatAttachAlert.m9448$$Nest$fgetcodepointCount(this.this$0);
            if (r133 > 100) goto L38;
            if (r133 >= (-9999)) goto L26;
            r133 = -9999;
        L26:
            AnimatedTextView r6 = ChatAttachAlert.m9446$$Nest$fgetcaptionLimitView(this.this$0);
            long r7 = r133;
            String r10 = LocaleController.formatNumber(r7, ',');
            if (ChatAttachAlert.m9446$$Nest$fgetcaptionLimitView(this.this$0).getVisibility() != 0) goto L29;
            boolean r11 = true;
        L30:
            r6.setText(r10, r11);
            if (ChatAttachAlert.m9446$$Nest$fgetcaptionLimitView(this.this$0).getVisibility() == 0) goto L33;
            ChatAttachAlert.m9446$$Nest$fgetcaptionLimitView(this.this$0).setVisibility(0);
            ChatAttachAlert.m9446$$Nest$fgetcaptionLimitView(this.this$0).setAlpha(0.0f);
            ChatAttachAlert.m9446$$Nest$fgetcaptionLimitView(this.this$0).setScaleX(0.5f);
            ChatAttachAlert.m9446$$Nest$fgetcaptionLimitView(this.this$0).setScaleY(0.5f);
        L33:
            ChatAttachAlert.m9446$$Nest$fgetcaptionLimitView(this.this$0).animate().setListener(null).cancel();
            ChatAttachAlert.m9446$$Nest$fgetcaptionLimitView(this.this$0).animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(100).start();
            ChatAttachAlert r03 = this.this$0;
            if (r133 >= 0) goto L36;
            ChatAttachAlert.m9446$$Nest$fgetcaptionLimitView(r03).setTextColor(ChatAttachAlert.access$7700(this.this$0, Theme.key_text_RedRegular));
            boolean r134 = false;
        L37:
            ChatAttachAlert.m9493$$Nest$fgettopCaptionLimitView(this.this$0).setText(LocaleController.formatNumber(r7, ','), false);
            ChatAttachAlert.m9493$$Nest$fgettopCaptionLimitView(this.this$0).setAlpha(1.0f);
        L39:
            ChatAttachAlert r04 = this.this$0;
            if (r04.sendButtonEnabled == r134) goto L42;
            r04.sendButtonEnabled = r134;
            ChatAttachAlert.m9496$$Nest$fgetwriteButton(r04).invalidate();
        L42:
            ChatAttachAlert r135 = this.this$0;
            if (r135.captionAbove == false) goto L45;
        L50:
            ChatAttachAlert.m9519$$Nest$mcheckIsEphemeralMessage(this.this$0, true);
            return;
        L45:
            if (r135.commentTextView.getEditText().getLineCount() > 2) goto L47;
        L49:
            ChatAttachAlert.m9528$$Nest$mshowAiButton(r135, r1);
            goto L50
        L47:
            if (TextUtils.isEmpty(this.this$0.commentTextView.getText().toString().trim()) == true) goto L49;
            r1 = true;
            goto L49
        L36:
            ChatAttachAlert.m9446$$Nest$fgetcaptionLimitView(r03).setTextColor(ChatAttachAlert.access$7800(this.this$0, Theme.key_windowBackgroundWhiteGrayText));
            r134 = true;
            goto L37
        L29:
            r11 = false;
        L38:
            ChatAttachAlert.m9446$$Nest$fgetcaptionLimitView(this.this$0).animate().alpha(0.0f).scaleX(0.5f).scaleY(0.5f).setDuration(100).setListener(new AnonymousClass1(this));
            ChatAttachAlert.m9493$$Nest$fgettopCaptionLimitView(this.this$0).setAlpha(0.0f);
            r134 = true;
            goto L39
        L18:
            r02 = false;
            goto L19
        L5:
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0) == null) goto L7;
            ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).onSelectedItemsCountChanged(ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).getSelectedItemsCount());
        L7:
            this.wasEmpty = !this.wasEmpty;
            goto L8
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r7, int r8, int r9, int r10) {
            if ((r10 - r9) < 1) goto L5;
            this.processChange = true;
        L5:
            ChatAttachAlert r82 = this.this$0;
            if (r82.mentionContainer != null) goto L9;
            ChatAttachAlert.m9522$$Nest$mcreateMentionsContainer(r82);
        L9:
            if (this.this$0.mentionContainer.getAdapter() == null) goto L12;
            this.this$0.mentionContainer.setReversed(false);
            this.this$0.mentionContainer.getAdapter().searchUsernameOrHashtag(r7, this.this$0.commentTextView.getEditText().getSelectionStart(), null, false, false);
            this.this$0.updateCommentTextViewPosition();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$21, reason: invalid class name */
    public class AnonymousClass21 extends EditTextEmoji {
        final /* synthetic */ ChatAttachAlert this$0;

        public AnonymousClass21(ChatAttachAlert r1, Context r2, SizeNotifierFrameLayout r3, BaseFragment r4, int r5, boolean r6, Theme.ResourcesProvider r7) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7);
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public void createEmojiView() {
            super.createEmojiView();
            EmojiView r1 = getEmojiView();
            if (r1 == null) goto L6;
            r1.shouldLightenBackground = false;
            r1.fixBottomTabContainerTranslation = false;
            r1.setShouldDrawBackground(false);
            r1.setBottomInset(AndroidUtilities.navigationBarHeight);
            return;
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public void extendActionMode(ActionMode r3, Menu r4) {
            BaseFragment r0 = this.this$0.baseFragment;
            if ((r0 instanceof ChatActivity) == false) goto L5;
            ChatActivity.fillActionModeMenu(r4, ((ChatActivity) r0).getCurrentEncryptedChat(), true, true);
        L5:
            super.extendActionMode(r3, r4);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r4) {
            if (ChatAttachAlert.m9457$$Nest$fgetenterCommentEventSent(this.this$0) == true) goto L15;
            if (r4.getX() > this.this$0.topCommentTextView.getEditText().getLeft()) goto L7;
        L13:
            ChatAttachAlert r0 = this.this$0;
            r0.makeFocusable(r0.topCommentTextView.getEditText(), false);
            goto L15
        L7:
            if (r4.getX() >= this.this$0.topCommentTextView.getEditText().getRight()) goto L13;
            if (r4.getY() <= this.this$0.topCommentTextView.getEditText().getTop()) goto L13;
            if (r4.getY() >= this.this$0.topCommentTextView.getEditText().getBottom()) goto L13;
            ChatAttachAlert r02 = this.this$0;
            r02.makeFocusable(r02.topCommentTextView.getEditText(), true);
        L15:
            return super.onInterceptTouchEvent(r4);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            ChatAttachAlert.m9532$$Nest$mupdatedTopCaptionHeight(this.this$0);
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public void onLineCountChanged(int r2, int r3) {
            super.onLineCountChanged(r2, r3);
            ChatAttachAlert.m9532$$Nest$mupdatedTopCaptionHeight(this.this$0);
            ChatAttachAlert r22 = this.this$0;
            if (r22.captionAbove == true) goto L5;
            return;
        L5:
            if (r3 > 2) goto L7;
        L9:
            boolean r1 = false;
        L10:
            ChatAttachAlert.m9528$$Nest$mshowAiButton(r22, r1);
            return;
        L7:
            if (TextUtils.isEmpty(getEditText().getText().toString().trim()) == true) goto L9;
            r1 = true;
            goto L10
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$22, reason: invalid class name */
    public class AnonymousClass22 implements TextWatcher {
        private boolean processChange;
        final /* synthetic */ ChatAttachAlert this$0;
        final /* synthetic */ BaseFragment val$parentFragment;
        private boolean wasEmpty;

        /* renamed from: org.telegram.ui.Components.ChatAttachAlert$22$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass22 this$1;

            public AnonymousClass1(AnonymousClass22 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                ChatAttachAlert.m9493$$Nest$fgettopCaptionLimitView(this.this$1.this$0).setVisibility(8);
            }
        }

        public AnonymousClass22(ChatAttachAlert r1, BaseFragment r2) {
            this.this$0 = r1;
            this.val$parentFragment = r2;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r13) {
            if (this.wasEmpty != TextUtils.isEmpty(r13)) goto L5;
        L8:
            boolean r1 = false;
            if (this.processChange == false) goto L15;
            ImageSpan[] r0 = (ImageSpan[]) r13.getSpans(0, r13.length(), ImageSpan.class);
            int r3 = 0;
        L12:
            if (r3 >= r0.length) goto L14;
            r13.removeSpan(r0[r3]);
            r3 = r3 + 1;
            goto L12
        L14:
            Emoji.replaceEmoji(r13, this.this$0.topCommentTextView.getEditText().getPaint().getFontMetricsInt(), false);
            this.processChange = false;
        L15:
            ChatAttachAlert.m9506$$Nest$fputcodepointCount(this.this$0, Character.codePointCount(r13, 0, r13.length()));
            BoolAnimator r132 = ChatAttachAlert.m9429$$Nest$fgetanimatorCaptionNotEmpty(this.this$0);
            if (ChatAttachAlert.m9448$$Nest$fgetcodepointCount(this.this$0) <= 0) goto L18;
            boolean r02 = true;
        L19:
            r132.setValue(r02, true);
            if (ChatAttachAlert.m9452$$Nest$fgetcurrentLimit(this.this$0) <= 0) goto L38;
            int r133 = ChatAttachAlert.m9452$$Nest$fgetcurrentLimit(this.this$0) - ChatAttachAlert.m9448$$Nest$fgetcodepointCount(this.this$0);
            if (r133 > 100) goto L38;
            if (r133 >= (-9999)) goto L26;
            r133 = -9999;
        L26:
            AnimatedTextView r6 = ChatAttachAlert.m9493$$Nest$fgettopCaptionLimitView(this.this$0);
            long r7 = r133;
            String r10 = LocaleController.formatNumber(r7, ',');
            if (ChatAttachAlert.m9493$$Nest$fgettopCaptionLimitView(this.this$0).getVisibility() != 0) goto L29;
            boolean r11 = true;
        L30:
            r6.setText(r10, r11);
            if (ChatAttachAlert.m9493$$Nest$fgettopCaptionLimitView(this.this$0).getVisibility() == 0) goto L33;
            ChatAttachAlert.m9493$$Nest$fgettopCaptionLimitView(this.this$0).setVisibility(0);
            ChatAttachAlert.m9493$$Nest$fgettopCaptionLimitView(this.this$0).setAlpha(0.0f);
            ChatAttachAlert.m9493$$Nest$fgettopCaptionLimitView(this.this$0).setScaleX(0.5f);
            ChatAttachAlert.m9493$$Nest$fgettopCaptionLimitView(this.this$0).setScaleY(0.5f);
        L33:
            ChatAttachAlert.m9493$$Nest$fgettopCaptionLimitView(this.this$0).animate().setListener(null).cancel();
            ChatAttachAlert.m9493$$Nest$fgettopCaptionLimitView(this.this$0).animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(100).start();
            ChatAttachAlert r03 = this.this$0;
            if (r133 >= 0) goto L36;
            ChatAttachAlert.m9493$$Nest$fgettopCaptionLimitView(r03).setTextColor(ChatAttachAlert.access$7900(this.this$0, Theme.key_text_RedRegular));
            boolean r134 = false;
        L37:
            ChatAttachAlert.m9446$$Nest$fgetcaptionLimitView(this.this$0).setText(LocaleController.formatNumber(r7, ','), false);
            ChatAttachAlert.m9446$$Nest$fgetcaptionLimitView(this.this$0).setAlpha(1.0f);
        L39:
            ChatAttachAlert r04 = this.this$0;
            if (r04.sendButtonEnabled == r134) goto L42;
            r04.sendButtonEnabled = r134;
            ChatAttachAlert.m9496$$Nest$fgetwriteButton(r04).invalidate();
        L42:
            ChatAttachAlert r135 = this.this$0;
            if (r135.captionLimitBulletinShown == false) goto L45;
        L53:
            ChatAttachAlert r136 = this.this$0;
            if (r136.captionAbove == true) goto L56;
        L61:
            ChatAttachAlert.m9519$$Nest$mcheckIsEphemeralMessage(this.this$0, true);
            return;
        L56:
            if (r136.topCommentTextView.getEditText().getLineCount() > 2) goto L58;
        L60:
            ChatAttachAlert.m9528$$Nest$mshowAiButton(r136, r1);
            goto L61
        L58:
            if (TextUtils.isEmpty(this.this$0.topCommentTextView.getText().toString().trim()) == true) goto L60;
            r1 = true;
            goto L60
        L45:
            if (MessagesController.getInstance(r135.currentAccount).premiumFeaturesBlocked() == true) goto L53;
            if (UserConfig.getInstance(this.this$0.currentAccount).isPremium() == true) goto L53;
            if (ChatAttachAlert.m9448$$Nest$fgetcodepointCount(this.this$0) <= MessagesController.getInstance(this.this$0.currentAccount).captionLengthLimitDefault) goto L53;
            if (ChatAttachAlert.m9448$$Nest$fgetcodepointCount(this.this$0) >= MessagesController.getInstance(this.this$0.currentAccount).captionLengthLimitPremium) goto L53;
            ChatAttachAlert r137 = this.this$0;
            r137.captionLimitBulletinShown = true;
            ChatAttachAlert.m9529$$Nest$mshowCaptionLimitBulletin(r137, this.val$parentFragment);
            goto L53
        L36:
            ChatAttachAlert.m9493$$Nest$fgettopCaptionLimitView(r03).setTextColor(ChatAttachAlert.access$8000(this.this$0, Theme.key_windowBackgroundWhiteGrayText));
            r134 = true;
            goto L37
        L29:
            r11 = false;
        L38:
            ChatAttachAlert.m9493$$Nest$fgettopCaptionLimitView(this.this$0).animate().alpha(0.0f).scaleX(0.5f).scaleY(0.5f).setDuration(100).setListener(new AnonymousClass1(this));
            ChatAttachAlert.m9446$$Nest$fgetcaptionLimitView(this.this$0).setAlpha(0.0f);
            r134 = true;
            goto L39
        L18:
            r02 = false;
            goto L19
        L5:
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0) == null) goto L7;
            ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).onSelectedItemsCountChanged(ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).getSelectedItemsCount());
        L7:
            this.wasEmpty = !this.wasEmpty;
            goto L8
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r7, int r8, int r9, int r10) {
            if ((r10 - r9) < 1) goto L5;
            this.processChange = true;
        L5:
            ChatAttachAlert r92 = this.this$0;
            if (r92.mentionContainer != null) goto L9;
            ChatAttachAlert.m9522$$Nest$mcreateMentionsContainer(r92);
        L9:
            if (this.this$0.mentionContainer.getAdapter() == null) goto L12;
            this.this$0.mentionContainer.setReversed(true);
            this.this$0.mentionContainer.getAdapter().searchUsernameOrHashtag(r7, this.this$0.topCommentTextView.getEditText().getSelectionStart(), null, false, false);
            this.this$0.updateCommentTextViewPosition();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$23, reason: invalid class name */
    public class AnonymousClass23 extends FrameLayout {
        final /* synthetic */ ChatAttachAlert this$0;

        public AnonymousClass23(ChatAttachAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r5) {
            super.onInitializeAccessibilityNodeInfo(r5);
            AttachAlertLayout r0 = ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0);
            ChatAttachAlertPhotoLayout r1 = ChatAttachAlert.m9475$$Nest$fgetphotoLayout(this.this$0);
            ChatAttachAlert r2 = this.this$0;
            if (r0 != r1) goto L5;
            r5.setText(LocaleController.formatPluralString("AccDescrSendPhotos", ChatAttachAlert.m9475$$Nest$fgetphotoLayout(r2).getSelectedItemsCount(), new Object[0]));
        L11:
            r5.setClassName(Button.class.getName());
            r5.setLongClickable(true);
            r5.setClickable(true);
            return;
        L5:
            AttachAlertLayout r02 = ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r2);
            ChatAttachAlertDocumentLayout r12 = ChatAttachAlert.m9453$$Nest$fgetdocumentLayout(this.this$0);
            ChatAttachAlert r22 = this.this$0;
            if (r02 != r12) goto L9;
            r5.setText(LocaleController.formatPluralString("AccDescrSendFiles", ChatAttachAlert.m9453$$Nest$fgetdocumentLayout(r22).getSelectedItemsCount(), new Object[0]));
            goto L11
        L9:
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r22) != ChatAttachAlert.m9432$$Nest$fgetaudioLayout(this.this$0)) goto L11;
            r5.setText(LocaleController.formatPluralString("AccDescrSendAudio", ChatAttachAlert.m9432$$Nest$fgetaudioLayout(this.this$0).getSelectedItemsCount(), new Object[0]));
            goto L11
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$24, reason: invalid class name */
    public class AnonymousClass24 extends ChatActivityEnterView.SendButton {
        final /* synthetic */ ChatAttachAlert this$0;

        public AnonymousClass24(ChatAttachAlert r1, Context r2, int r3, Theme.ResourcesProvider r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.SendButton
        public int getFillColor() {
            return ChatAttachAlert.access$8100(this.this$0, Theme.key_dialogFloatingButton);
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.SendButton
        public boolean isInScheduleMode() {
            return super.isInScheduleMode();
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.SendButton
        public boolean isInactive() {
            return !this.this$0.sendButtonEnabled;
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.SendButton
        public boolean isOpen() {
            return true;
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.SendButton
        public boolean shouldDrawBackground() {
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$25, reason: invalid class name */
    public class AnonymousClass25 extends MessageSendPreview {
        final /* synthetic */ ChatAttachAlert this$0;

        public AnonymousClass25(ChatAttachAlert r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.MessageSendPreview
        public void onEffectChange(long r3) {
            ChatActivityEnterView.SendButton r0 = ChatAttachAlert.m9496$$Nest$fgetwriteButton(this.this$0);
            ChatAttachAlert.m9508$$Nest$fputeffectId(this.this$0, r3);
            r0.setEffect(r3);
            super.onEffectChange(r3);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$26, reason: invalid class name */
    public class AnonymousClass26 extends TranslateBeforeSendWrapper {
        final /* synthetic */ ChatAttachAlert this$0;
        final /* synthetic */ BaseFragment val$parentFragment;

        /* renamed from: org.telegram.ui.Components.ChatAttachAlert$26$1, reason: invalid class name */
        public class AnonymousClass1 implements TranslatorUtils.TranslateCallback {
            final /* synthetic */ AnonymousClass26 this$1;
            final /* synthetic */ AlertDialog val$progressDialog;

            public AnonymousClass1(AnonymousClass26 r1, AlertDialog r2) {
                this.this$1 = r1;
                this.val$progressDialog = r2;
            }

            @Override // com.exteragram.messenger.translator.TranslatorUtils.TranslateCallback
            public void onFailed() {
                this.val$progressDialog.dismiss();     // Catch: Exception -> L10
            L3:
                BaseFragment r0 = this.this$1.val$parentFragment;
                if ((r0 instanceof ChatActivity) == true) goto L6;
                return;
            L6:
                if (ChatObject.isChannelAndNotMegaGroup(((ChatActivity) r0).getCurrentChat()) == false) goto L14;
                AnonymousClass26 r2 = this.this$1;
                BulletinFactory.of(r2.this$0.sizeNotifierFrameLayout, AnonymousClass26.access$9300(r2)).createErrorBulletin(LocaleController.getString(R.string.TranslationFailedAlert2)).show();
                return;
            }

            @Override // com.exteragram.messenger.translator.TranslatorUtils.TranslateCallback
            public void onSuccess(TLRPC.TL_textWithEntities r8) {
                this.val$progressDialog.dismiss();     // Catch: Exception -> L7
            L3:
                SpannableStringBuilder r1 = SpannableStringBuilder.valueOf(r8.text);
                MessageObject.addEntitiesToText(r1, r8.entities, true, true, false, true);
                this.this$1.this$0.getCommentTextView().setText(r1);
                this.this$1.this$0.applyCaption();
                this.this$1.this$0.getCommentTextView().setSelection(r1.length());
                if (ChatAttachAlert.m9469$$Nest$fgetmessageSendPreview(this.this$1.this$0) == null) goto L10;
                ChatAttachAlert.m9469$$Nest$fgetmessageSendPreview(this.this$1.this$0).dismiss(false);
                return;
            }
        }

        public AnonymousClass26(ChatAttachAlert r1, Context r2, boolean r3, boolean r4, Theme.ResourcesProvider r5, BaseFragment r6) {
            this.this$0 = r1;
            this.val$parentFragment = r6;
            super(r2, r3, r4, r5);
        }

        public static /* synthetic */ Theme.ResourcesProvider access$9300(AnonymousClass26 r0) {
            return r0.resourcesProvider;
        }

        @Override // com.exteragram.messenger.components.TranslateBeforeSendWrapper
        public void onClick() {
            if (ChatAttachAlert.m9469$$Nest$fgetmessageSendPreview(this.this$0) == null) goto L5;
            ChatAttachAlert.m9469$$Nest$fgetmessageSendPreview(this.this$0).dismiss(false);
        L5:
            AlertDialog r0 = new AlertDialog(getContext(), 3, this.resourcesProvider);
            r0.showDelayed(150);
            CharSequence[] r4 = {this.this$0.getCommentTextView().getText()};
            TranslatorUtils.translate(r4[0], TranslatorUtils.getResolvedSendTargetLanguageCode(), MediaDataController.getInstance(this.this$0.currentAccount).getEntities(r4, true), new AnonymousClass1(this, r0));
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$27, reason: invalid class name */
    public class AnonymousClass27 extends View {
        final /* synthetic */ ChatAttachAlert this$0;

        public AnonymousClass27(ChatAttachAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r13) {
            String r0 = String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(Math.max(1, ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).getSelectedItemsCount()))});
            int r2 = Math.max(AndroidUtilities.dp(16.0f) + ((int) Math.ceil(ChatAttachAlert.m9488$$Nest$fgettextPaint(this.this$0).measureText(r0))), AndroidUtilities.dp(24.0f));
            int r3 = getMeasuredWidth() / 2;
            ChatAttachAlert.m9488$$Nest$fgettextPaint(this.this$0).setColor(ColorUtils.setAlphaComponent(ChatAttachAlert.access$8200(this.this$0, Theme.key_dialogRoundCheckBoxCheck), (int) (Color.alpha(r4) * ((ChatAttachAlert.m9487$$Nest$fgetsendButtonEnabledProgress(this.this$0) * 0.42d) + 0.58d))));
            ChatAttachAlert.m9474$$Nest$fgetpaint(this.this$0).setColor(ChatAttachAlert.access$8300(this.this$0, Theme.key_dialogBackground));
            int r22 = r2 / 2;
            ChatAttachAlert.m9484$$Nest$fgetrect(this.this$0).set(r3 - r22, 0.0f, r22 + r3, getMeasuredHeight());
            r13.drawRoundRect(ChatAttachAlert.m9484$$Nest$fgetrect(this.this$0), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), ChatAttachAlert.m9474$$Nest$fgetpaint(this.this$0));
            ChatAttachAlert.m9474$$Nest$fgetpaint(this.this$0).setColor(ChatAttachAlert.access$8400(this.this$0, Theme.key_chat_attachCheckBoxBackground));
            ChatAttachAlert.m9484$$Nest$fgetrect(this.this$0).set(r5 + AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), r2 - AndroidUtilities.dp(2.0f), getMeasuredHeight() - AndroidUtilities.dp(2.0f));
            r13.drawRoundRect(ChatAttachAlert.m9484$$Nest$fgetrect(this.this$0), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), ChatAttachAlert.m9474$$Nest$fgetpaint(this.this$0));
            r13.drawText(r0, r3 - (r1 / 2), AndroidUtilities.dp(16.2f), ChatAttachAlert.m9488$$Nest$fgettextPaint(this.this$0));
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$28, reason: invalid class name */
    public class AnonymousClass28 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatAttachAlert this$0;
        final /* synthetic */ Runnable val$onEnd;
        final /* synthetic */ int val$t;

        public static /* synthetic */ void $r8$lambda$6XMK4OoI5veRZg8Awie6tnzfdhw(AnonymousClass28 r0, DynamicAnimation r1, float r2, float r3) {
            r0.lambda$onAnimationEnd$0(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$bbzWh6AP8oSll_cokvXKwew_wRc(AnonymousClass28 r0, Runnable r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
            r0.lambda$onAnimationEnd$1(r1, r2, r3, r4, r5);
        }

        public AnonymousClass28(ChatAttachAlert r1, int r2, Runnable r3) {
            this.this$0 = r1;
            this.val$t = r2;
            this.val$onEnd = r3;
        }

        private /* synthetic */ void lambda$onAnimationEnd$0(DynamicAnimation r1, float r2, float r3) {
            if (ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(this.this$0) != ChatAttachAlert.m9480$$Nest$fgetpollLayout(this.this$0)) goto L5;
        L10:
            ChatAttachAlert.m9531$$Nest$mupdateSelectedPosition(this.this$0, 1);
        L11:
            ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(this.this$0).onContainerTranslationUpdated(this.this$0.currentPanTranslationY);
            ChatAttachAlert.access$8600(this.this$0).invalidate();
            return;
        L5:
            if (ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(this.this$0) == ChatAttachAlert.m9491$$Nest$fgettodoLayout(this.this$0)) goto L10;
            ChatAttachAlert r12 = this.this$0;
            if (r12.isPhotoPicker == false) goto L11;
            if (ChatAttachAlert.m9495$$Nest$fgetviewChangeAnimator(r12) == null) goto L11;
            goto L10
        }

        private /* synthetic */ void lambda$onAnimationEnd$1(Runnable r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
            ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(this.this$0).setTranslationY(0.0f);
            ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(this.this$0).onContainerTranslationUpdated(this.this$0.currentPanTranslationY);
            ChatAttachAlert.access$8500(this.this$0).invalidate();
            r1.run();
            ChatAttachAlert.m9531$$Nest$mupdateSelectedPosition(this.this$0, 0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).setAlpha(0.0f);
            ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).setTranslationY(AndroidUtilities.dp(78.0f) + this.val$t);
            ChatAttachAlert r42 = this.this$0;
            r42.ATTACH_ALERT_LAYOUT_TRANSLATION.set(ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r42), Float.valueOf(1.0f));
            this.this$0.actionBar.setAlpha(0.0f);
            SpringAnimation r43 = new SpringAnimation(ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(this.this$0), DynamicAnimation.TRANSLATION_Y, 0.0f);
            r43.getSpring().setDampingRatio(0.75f);
            r43.getSpring().setStiffness(500.0f);
            r43.addUpdateListener(new ChatAttachAlert$28$$ExternalSyntheticLambda0(this));
            r43.addEndListener(new ChatAttachAlert$28$$ExternalSyntheticLambda1(this, this.val$onEnd));
            ChatAttachAlert.m9517$$Nest$fputviewChangeAnimator(this.this$0, r43);
            r43.start();
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$29, reason: invalid class name */
    public class AnonymousClass29 implements ChatAttachAlertContactsLayout.PhonebookShareAlertDelegate {
        final /* synthetic */ ChatAttachAlert this$0;

        public AnonymousClass29(ChatAttachAlert r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlertContactsLayout.PhonebookShareAlertDelegate
        public void didSelectContact(TLRPC.User r10, boolean r11, int r12, long r13, boolean r15, long r16) {
            ((ChatActivity) this.this$0.baseFragment).sendContact(r10, r11, r12, r13, r15, r16);
        }

        @Override // org.telegram.ui.Components.ChatAttachAlertContactsLayout.PhonebookShareAlertDelegate
        public void didSelectContacts(ArrayList<TLRPC.User> r11, String r12, boolean r13, int r14, long r15, boolean r17, long r18) {
            ((ChatActivity) this.this$0.baseFragment).sendContacts(r11, r12, r13, r14, r15, r17, 0);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$3, reason: invalid class name */
    public class AnonymousClass3 implements RenderNodeWithHash.Renderer {
        final /* synthetic */ ChatAttachAlert this$0;

        public AnonymousClass3(ChatAttachAlert r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.blur3.RenderNodeWithHash.Renderer
        public void renderNodeCalculateHash(IBlur3Hash r3) {
            r3.add(ChatAttachAlert.access$800(this.this$0, Theme.key_windowBackgroundWhite));
            r3.add(SharedConfig.chatBlurEnabled());
        }

        @Override // org.telegram.ui.Components.blur3.RenderNodeWithHash.Renderer
        public void renderNodeUpdateDisplayList(Canvas r3) {
            r3.drawColor(ChatAttachAlert.access$900(this.this$0, Theme.key_windowBackgroundWhite));
            if (SharedConfig.chatBlurEnabled() == false) goto L6;
            ChatAttachAlert.m9485$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).draw(r3, -2);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$30, reason: invalid class name */
    public class AnonymousClass30 implements ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate {
        final /* synthetic */ ChatAttachAlert this$0;

        public AnonymousClass30(ChatAttachAlert r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate
        public void didSelectFiles(ArrayList<String> r17, String r18, ArrayList<TLRPC.MessageEntity> r19, ArrayList<MessageObject> r20, boolean r21, int r22, int r23, long r24, boolean r26, long r27) {
            ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate r1 = ChatAttachAlert.m9454$$Nest$fgetdocumentsDelegate(this.this$0);
            ChatAttachAlert r0 = this.this$0;
            if (r1 == null) goto L6;
            ChatAttachAlert.m9454$$Nest$fgetdocumentsDelegate(r0).didSelectFiles(r17, r18, r19, r20, r21, r22, r23, r24, r26, r27);
            return;
        L6:
            Object r02 = r0.baseFragment;
            if ((r02 instanceof ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate) == false) goto L11;
            ((ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate) r02).didSelectFiles(r17, r18, r19, r20, r21, r22, r23, r24, r26, r27);
            return;
        L11:
            if ((r02 instanceof PassportActivity) == false) goto L14;
            ((PassportActivity) r02).didSelectFiles(r17, r18, r21, r22, r24, r26);
            return;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate
        public void didSelectPhotos(ArrayList<SendMessagesHelper.SendingMediaInfo> r9, boolean r10, int r11, int r12, long r13) {
            ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate r0 = ChatAttachAlert.m9454$$Nest$fgetdocumentsDelegate(this.this$0);
            ChatAttachAlert r8 = this.this$0;
            if (r0 == null) goto L6;
            ChatAttachAlert.m9454$$Nest$fgetdocumentsDelegate(r8).didSelectPhotos(r9, r10, r11, r12, r13);
            return;
        L6:
            BaseFragment r82 = r8.baseFragment;
            if ((r82 instanceof ChatActivity) == false) goto L11;
            ((ChatActivity) r82).didSelectPhotos(r9, r10, r11, r12, r13);
            return;
        L11:
            if ((r82 instanceof PassportActivity) == false) goto L14;
            ((PassportActivity) r82).didSelectPhotos(r9, r10, r11);
            return;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate
        public void startDocumentSelectActivity() {
            ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate r0 = ChatAttachAlert.m9454$$Nest$fgetdocumentsDelegate(this.this$0);
            ChatAttachAlert r1 = this.this$0;
            if (r0 == null) goto L6;
            ChatAttachAlert.m9454$$Nest$fgetdocumentsDelegate(r1).startDocumentSelectActivity();
            return;
        L6:
            Object r12 = r1.baseFragment;
            if ((r12 instanceof ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate) == false) goto L11;
            ((ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate) r12).startDocumentSelectActivity();
            return;
        L11:
            if ((r12 instanceof PassportActivity) == false) goto L14;
            ((PassportActivity) r12).startDocumentSelectActivity();
            return;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate
        public void startMusicSelectActivity() {
            ChatAttachAlert.m9526$$Nest$mopenAudioLayout(this.this$0, true);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$31, reason: invalid class name */
    public class AnonymousClass31 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatAttachAlert this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass31(ChatAttachAlert r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            if (r2.equals(ChatAttachAlert.m9449$$Nest$fgetcommentsAnimator(this.this$0)) == false) goto L6;
            ChatAttachAlert.m9507$$Nest$fputcommentsAnimator(this.this$0, null);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            if (r3.equals(ChatAttachAlert.m9449$$Nest$fgetcommentsAnimator(this.this$0)) == false) goto L16;
            boolean r32 = this.val$show;
            ChatAttachAlert r0 = this.this$0;
            if (r32 == true) goto L8;
            ChatAttachAlert.m9497$$Nest$fgetwriteButtonContainer(r0).setVisibility(4);
        L14:
            ChatAttachAlert.m9507$$Nest$fputcommentsAnimator(this.this$0, null);
            return;
        L8:
            if (r0.typeButtonsAvailable == false) goto L14;
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r0) != null) goto L12;
        L13:
            this.this$0.buttonsRecyclerViewWrapper.setVisibility(4);
            goto L14
        L12:
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).shouldHideBottomButtons() == false) goto L14;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$32, reason: invalid class name */
    public class AnonymousClass32 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatAttachAlert this$0;
        final /* synthetic */ boolean val$above;
        final /* synthetic */ boolean val$show;

        public AnonymousClass32(ChatAttachAlert r1, boolean r2, boolean r3) {
            this.this$0 = r1;
            this.val$show = r2;
            this.val$above = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            if (r2.equals(ChatAttachAlert.m9449$$Nest$fgetcommentsAnimator(this.this$0)) == false) goto L6;
            ChatAttachAlert.m9507$$Nest$fputcommentsAnimator(this.this$0, null);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            if (r3.equals(ChatAttachAlert.m9449$$Nest$fgetcommentsAnimator(this.this$0)) == false) goto L26;
            boolean r32 = this.val$show;
            ChatAttachAlert r0 = this.this$0;
            if (r32 == true) goto L11;
            if (ChatAttachAlert.m9466$$Nest$fgetisSoundPicker(r0) == true) goto L9;
            ChatAttachAlert.m9461$$Nest$fgetframeLayout2(this.this$0).setVisibility(4);
        L9:
            ChatAttachAlert.m9497$$Nest$fgetwriteButtonContainer(this.this$0).setVisibility(4);
        L18:
            if (this.val$above == false) goto L24;
            ChatAttachAlert.m9532$$Nest$mupdatedTopCaptionHeight(this.this$0);
            FrameLayout r33 = this.this$0.topCommentContainer;
            if (this.val$show == false) goto L22;
            int r02 = 0;
        L23:
            r33.setVisibility(r02);
            goto L24
        L22:
            r02 = 8;
        L24:
            ChatAttachAlert.m9507$$Nest$fputcommentsAnimator(this.this$0, null);
            return;
        L11:
            if (r0.typeButtonsAvailable == false) goto L18;
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r0) != null) goto L15;
        L16:
            this.this$0.buttonsRecyclerViewWrapper.setVisibility(4);
            goto L18
        L15:
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).shouldHideBottomButtons() == false) goto L18;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$33, reason: invalid class name */
    public class AnonymousClass33 extends AnimationProperties.FloatProperty<ChatAttachAlert> {
        private float openProgress;
        final /* synthetic */ ChatAttachAlert this$0;

        public AnonymousClass33(ChatAttachAlert r1, String r2) {
            this.this$0 = r1;
            super(r2);
        }

        public Float get(ChatAttachAlert r1) {
            return Float.valueOf(this.openProgress);
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object r1) {
            return get((ChatAttachAlert) r1);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
        public /* bridge */ /* synthetic */ void setValue(ChatAttachAlert r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(ChatAttachAlert r8, float r9) {
            int r82 = this.this$0.buttonsRecyclerView.getChildCount();
            int r0 = 0;
        L3:
            if (r0 >= r82) goto L18;
            float r1 = (3 - r0) * 32.0f;
            View r2 = this.this$0.buttonsRecyclerView.getChildAt(r0);
            if (r9 <= r1) goto L13;
            float r12 = r9 - r1;
            if (r12 > 200.0f) goto L9;
            float r13 = r12 / 200.0f;
            float r3 = CubicBezierInterpolator.EASE_OUT.getInterpolation(r13) * 1.1f;
            r2.setAlpha(CubicBezierInterpolator.EASE_BOTH.getInterpolation(r13));
        L15:
            if ((r2 instanceof AttachButtonBase) == false) goto L17;
            ((AttachButtonBase) r2).glassTabView.setAttachScale(r3);
        L17:
            r0 = r0 + 1;
            goto L3
        L9:
            r2.setAlpha(1.0f);
            float r14 = r12 - 200.0f;
            if (r14 > 100.0f) goto L12;
            r3 = 1.1f - (CubicBezierInterpolator.EASE_IN.getInterpolation(r14 / 100.0f) * 0.1f);
            goto L15
        L12:
            r3 = 1.0f;
            goto L15
        L13:
            r3 = 0.0f;
            goto L15
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$34, reason: invalid class name */
    public class AnonymousClass34 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatAttachAlert this$0;
        final /* synthetic */ Runnable val$onAnimationEnd;

        public AnonymousClass34(ChatAttachAlert r1, Runnable r2) {
            this.this$0 = r1;
            this.val$onAnimationEnd = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            if (ChatAttachAlert.access$8900(this.this$0) != null) goto L5;
            return;
        L5:
            if (ChatAttachAlert.access$9000(this.this$0).equals(r2) == false) goto L9;
            this.val$onAnimationEnd.run();
            ChatAttachAlert.access$9102(this.this$0, null);
            ChatAttachAlert.access$9202(this.this$0, 0);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (ChatAttachAlert.access$8700(this.this$0) != null) goto L5;
            return;
        L5:
            if (ChatAttachAlert.access$8800(this.this$0).equals(r2) == true) goto L7;
            return;
        L7:
            if (ChatAttachAlert.m9430$$Nest$fgetappearSpringAnimation(this.this$0) != null) goto L9;
            return;
        L9:
            if (ChatAttachAlert.m9430$$Nest$fgetappearSpringAnimation(this.this$0).isRunning() == true) goto L15;
            this.val$onAnimationEnd.run();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$35, reason: invalid class name */
    public class AnonymousClass35 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatAttachAlert this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass35(ChatAttachAlert r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            ChatAttachAlert.m9498$$Nest$fputactionBarAnimation(this.this$0, null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            if (ChatAttachAlert.m9427$$Nest$fgetactionBarAnimation(this.this$0) == null) goto L23;
            boolean r32 = this.val$show;
            ChatAttachAlert r0 = this.this$0;
            if (r32 == true) goto L7;
            ActionBarMenuItem r33 = r0.searchItem;
            if (r33 == null) goto L17;
            r33.setVisibility(4);
        L17:
            ChatAttachAlert r34 = this.this$0;
            if (r34.avatarPicker == 0) goto L20;
        L21:
            this.this$0.selectedMenuItem.setVisibility(4);
            return;
        L20:
            if (ChatAttachAlert.m9468$$Nest$fgetmenuShowed(r34) == false) goto L21;
            return;
        L7:
            if (r0.typeButtonsAvailable == true) goto L9;
            return;
        L9:
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r0) != null) goto L11;
        L12:
            this.this$0.buttonsRecyclerViewWrapper.setVisibility(4);
            return;
        L11:
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).shouldHideBottomButtons() == true) goto L12;
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$36, reason: invalid class name */
    public class AnonymousClass36 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatAttachAlert this$0;

        public AnonymousClass36(ChatAttachAlert r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            ChatAttachAlert.m9511$$Nest$fputmenuAnimator(this.this$0, null);
            boolean r32 = ChatAttachAlert.m9468$$Nest$fgetmenuShowed(this.this$0);
            ChatAttachAlert r0 = this.this$0;
            if (r32 == false) goto L5;
            ActionBarMenuItem r2 = r0.searchItem;
            if (r2 == null) goto L17;
            r2.setVisibility(4);
            return;
        L17:
            return;
        L5:
            if (r0.actionBar.getTag() != null) goto L11;
            ChatAttachAlert r33 = this.this$0;
            if (r33.avatarPicker != 0) goto L11;
            if (r33.storyMediaPicker == true) goto L11;
            r33.selectedMenuItem.setVisibility(4);
        L11:
            this.this$0.headerView.setVisibility(4);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$37, reason: invalid class name */
    public class AnonymousClass37 extends MentionsContainerView {
        final /* synthetic */ ChatAttachAlert this$0;

        public AnonymousClass37(ChatAttachAlert r1, Context r2, long r3, long r5, BaseFragment r7, Theme.ResourcesProvider r8) {
            this.this$0 = r1;
            super(r2, r3, r5, r7, r8);
        }

        @Override // org.telegram.ui.Components.MentionsContainerView
        public void onAnimationScroll() {
            if (ChatAttachAlert.m9475$$Nest$fgetphotoLayout(this.this$0) == null) goto L6;
            ChatAttachAlert.m9475$$Nest$fgetphotoLayout(this.this$0).checkCameraViewPosition();
            return;
        }

        @Override // org.telegram.ui.Components.MentionsContainerView
        public void onScrolled(boolean r1, boolean r2) {
            if (ChatAttachAlert.m9475$$Nest$fgetphotoLayout(this.this$0) == null) goto L6;
            ChatAttachAlert.m9475$$Nest$fgetphotoLayout(this.this$0).checkCameraViewPosition();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$38, reason: invalid class name */
    public class AnonymousClass38 implements MentionsContainerView.Delegate {
        final /* synthetic */ ChatAttachAlert this$0;

        public AnonymousClass38(ChatAttachAlert r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.MentionsContainerView.Delegate
        public Paint.FontMetricsInt getFontMetrics() {
            return this.this$0.commentTextView.getEditText().getPaint().getFontMetricsInt();
        }

        @Override // org.telegram.ui.Components.MentionsContainerView.Delegate
        public void replaceText(int r1, int r2, CharSequence r3, boolean r4) {
            ChatAttachAlert.m9527$$Nest$mreplaceWithText(this.this$0, r1, r2, r3, r4);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$4, reason: invalid class name */
    public class AnonymousClass4 implements RenderNodeWithHash.Renderer {
        final /* synthetic */ ChatAttachAlert this$0;

        public AnonymousClass4(ChatAttachAlert r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.blur3.RenderNodeWithHash.Renderer
        public void renderNodeCalculateHash(IBlur3Hash r3) {
            r3.add(ChatAttachAlert.access$1000(this.this$0, Theme.key_windowBackgroundWhite));
            r3.add(SharedConfig.chatBlurEnabled());
        }

        @Override // org.telegram.ui.Components.blur3.RenderNodeWithHash.Renderer
        public void renderNodeUpdateDisplayList(Canvas r3) {
            r3.drawColor(ChatAttachAlert.access$1100(this.this$0, Theme.key_windowBackgroundWhite));
            if (SharedConfig.chatBlurEnabled() == false) goto L6;
            ChatAttachAlert.m9485$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).draw(r3, -3);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$5, reason: invalid class name */
    public class AnonymousClass5 extends SizeNotifierFrameLayout {
        AdjustPanLayoutHelper adjustPanLayoutHelper;
        private Bulletin.Delegate bulletinDelegate;
        private boolean ignoreLayout;
        private float initialTranslationY;
        private int lastNotifyWidth;
        private RectF rect;
        final /* synthetic */ ChatAttachAlert this$0;

        /* renamed from: org.telegram.ui.Components.ChatAttachAlert$5$1, reason: invalid class name */
        public class AnonymousClass1 implements Bulletin.Delegate {
            final /* synthetic */ AnonymousClass5 this$1;

            public AnonymousClass1(AnonymousClass5 r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.Components.Bulletin.Delegate
            public int getBottomOffset(int r1) {
                return (this.this$1.getHeight() - ChatAttachAlert.m9461$$Nest$fgetframeLayout2(this.this$1.this$0).getTop()) + AndroidUtilities.dp(52.0f);
            }
        }

        /* renamed from: org.telegram.ui.Components.ChatAttachAlert$5$2, reason: invalid class name */
        public class AnonymousClass2 extends AdjustPanLayoutHelper {
            final /* synthetic */ AnonymousClass5 this$1;

            public AnonymousClass2(AnonymousClass5 r1, View r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.ActionBar.AdjustPanLayoutHelper
            public boolean heightAnimationEnabled() {
                if (this.this$1.this$0.isDismissed() == false) goto L5;
            L23:
                return false;
            L5:
                if (ChatAttachAlert.m9472$$Nest$fgetopenTransitionFinished(this.this$1.this$0) == false) goto L23;
                if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$1.this$0) == ChatAttachAlert.m9480$$Nest$fgetpollLayout(this.this$1.this$0)) goto L14;
                if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$1.this$0) == ChatAttachAlert.m9491$$Nest$fgettodoLayout(this.this$1.this$0)) goto L14;
                if (this.this$1.this$0.getCommentView().isPopupVisible() == true) goto L14;
                return true;
            L14:
                if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$1.this$0) != ChatAttachAlert.m9480$$Nest$fgetpollLayout(this.this$1.this$0)) goto L18;
                if (ChatAttachAlert.m9480$$Nest$fgetpollLayout(this.this$1.this$0).isPopupVisible() == true) goto L18;
                return true;
            L18:
                if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$1.this$0) != ChatAttachAlert.m9491$$Nest$fgettodoLayout(this.this$1.this$0)) goto L23;
                if (ChatAttachAlert.m9491$$Nest$fgettodoLayout(this.this$1.this$0).isPopupVisible() == true) goto L23;
                return true;
            }

            @Override // org.telegram.ui.ActionBar.AdjustPanLayoutHelper
            public void onPanTranslationUpdate(float r3, float r4, boolean r5) {
                ChatAttachAlert r52 = this.this$1.this$0;
                r52.currentPanTranslationY = r3;
                if (ChatAttachAlert.m9462$$Nest$fgetfromScrollY(r52) <= 0.0f) goto L5;
                ChatAttachAlert r32 = this.this$1.this$0;
                r32.currentPanTranslationY += (ChatAttachAlert.m9462$$Nest$fgetfromScrollY(r32) - ChatAttachAlert.m9489$$Nest$fgettoScrollY(this.this$1.this$0)) * (1.0f - r4);
            L5:
                ChatAttachAlert r33 = this.this$1.this$0;
                r33.actionBar.setTranslationY(r33.currentPanTranslationY);
                ChatAttachAlert r34 = this.this$1.this$0;
                r34.selectedMenuItem.setTranslationY(r34.currentPanTranslationY);
                ChatAttachAlert r35 = this.this$1.this$0;
                ActionBarMenuItem r42 = r35.searchItem;
                if (r42 == null) goto L8;
                r42.setTranslationY(r35.currentPanTranslationY);
            L8:
                ChatAttachAlert r36 = this.this$1.this$0;
                ActionBarMenuItem r43 = r36.motionItem;
                if (r43 == null) goto L12;
                r43.setTranslationY(r36.selectedMenuItem.getTranslationY());
            L12:
                if (ChatAttachAlert.m9470$$Nest$fgetmotionHint(this.this$1.this$0) == null) goto L14;
                ChatAttachAlert.m9470$$Nest$fgetmotionHint(this.this$1.this$0).setTranslationY(this.this$1.this$0.selectedMenuItem.getTranslationY());
            L14:
                ChatAttachAlert r37 = this.this$1.this$0;
                r37.doneItem.setTranslationY(r37.currentPanTranslationY);
                ChatAttachAlert.m9531$$Nest$mupdateSelectedPosition(this.this$1.this$0, 0);
                ChatAttachAlert r38 = this.this$1.this$0;
                r38.setCurrentPanTranslationY(r38.currentPanTranslationY);
                this.this$1.invalidate();
                ChatAttachAlert.m9461$$Nest$fgetframeLayout2(this.this$1.this$0).invalidate();
                this.this$1.this$0.updateCommentTextViewPosition();
                if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$1.this$0) == null) goto L18;
                ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$1.this$0).onContainerTranslationUpdated(this.this$1.this$0.currentPanTranslationY);
                return;
            }

            @Override // org.telegram.ui.ActionBar.AdjustPanLayoutHelper
            public void onTransitionEnd() {
                super.onTransitionEnd();
                ChatAttachAlert r0 = this.this$1.this$0;
                int r2 = 0;
                r0.updateLayout(ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r0), false, 0);
                ChatAttachAlert r02 = this.this$1.this$0;
                ChatAttachAlert.m9514$$Nest$fputpreviousScrollOffsetY(r02, r02.scrollOffsetY[0]);
                ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$1.this$0).onPanTransitionEnd();
                if ((ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$1.this$0) instanceof ChatAttachAlertBotWebViewLayout) == true) goto L5;
                return;
            L5:
                if (ChatAttachAlert.m9436$$Nest$fgetbotButtonWasVisible(this.this$1.this$0) == false) goto L7;
                return;
            L7:
                if (ChatAttachAlert.access$1200(this.this$1.this$0) == false) goto L9;
                int r03 = AndroidUtilities.dp(84.0f);
            L11:
                if (r2 >= ChatAttachAlert.m9434$$Nest$fgetbotAttachLayouts(this.this$1.this$0).size()) goto L17;
                ((ChatAttachAlertBotWebViewLayout) ChatAttachAlert.m9434$$Nest$fgetbotAttachLayouts(this.this$1.this$0).valueAt(r2)).setMeasureOffsetY(r03);
                r2 = r2 + 1;
                goto L11
            L17:
                return;
            L9:
                r03 = 0;
                goto L11
            }

            @Override // org.telegram.ui.ActionBar.AdjustPanLayoutHelper
            public void onTransitionStart(boolean r5, int r6) {
                super.onTransitionStart(r5, r6);
                if (ChatAttachAlert.m9482$$Nest$fgetpreviousScrollOffsetY(this.this$1.this$0) <= 0) goto L8;
                int r0 = ChatAttachAlert.m9482$$Nest$fgetpreviousScrollOffsetY(this.this$1.this$0);
                ChatAttachAlert r2 = this.this$1.this$0;
                if (r0 == r2.scrollOffsetY[0]) goto L8;
                if (r5 == false) goto L8;
                ChatAttachAlert.m9509$$Nest$fputfromScrollY(r2, ChatAttachAlert.m9482$$Nest$fgetpreviousScrollOffsetY(r2));
                ChatAttachAlert.m9515$$Nest$fputtoScrollY(this.this$1.this$0, r0.scrollOffsetY[0]);
            L9:
                this.this$1.invalidate();
                if ((ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$1.this$0) instanceof ChatAttachAlertBotWebViewLayout) == true) goto L12;
            L17:
                ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$1.this$0).onPanTransitionStart(r5, r6);
                return;
            L12:
                if (ChatAttachAlert.m9436$$Nest$fgetbotButtonWasVisible(this.this$1.this$0) == true) goto L17;
                AnonymousClass5 r02 = this.this$1;
                if (r5 == false) goto L16;
                r02.this$0.buttonsRecyclerViewWrapper.setVisibility(8);
                goto L17
            L16:
                r02.this$0.buttonsRecyclerViewWrapper.setVisibility(0);
            L8:
                ChatAttachAlert.m9509$$Nest$fputfromScrollY(this.this$1.this$0, -1.0f);
                goto L9
            }
        }

        /* renamed from: $r8$lambda$g1f9tHPZhIZ0sprp-bxOyKuFUI4, reason: not valid java name */
        public static /* synthetic */ void m9540$r8$lambda$g1f9tHPZhIZ0sprpbxOyKuFUI4(AnonymousClass5 r0) {
            r0.lambda$onMeasure$0();
        }

        public AnonymousClass5(ChatAttachAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.bulletinDelegate = new AnonymousClass1(this);
            this.rect = new RectF();
            this.adjustPanLayoutHelper = new AnonymousClass2(this, this);
        }

        private void drawChildBackground(Canvas r22, View r23) {
            if ((r23 instanceof AttachAlertLayout) == false) goto L92;
            r22.save();
            r22.translate(0.0f, this.this$0.currentPanTranslationY);
            int r3 = (int) (r23.getAlpha() * 255.0f);
            AttachAlertLayout r5 = (AttachAlertLayout) r23;
            int r6 = r5.needsActionBar();
            int r7 = AndroidUtilities.dp(13.0f);
            FrameLayout r8 = this.this$0.headerView;
            if (r8 == null) goto L7;
            float r82 = r8.getAlpha();
        L8:
            int r72 = r7 + ((int) (r82 * AndroidUtilities.dp(26.0f)));
            FrameLayout r83 = this.this$0.topCommentContainer;
            if (r83 == null) goto L11;
            float r84 = r83.getAlpha() * this.this$0.topCommentContainer.getMeasuredHeight();
        L12:
            int r73 = r72 + ((int) r84);
            int r85 = (ChatAttachAlert.m9525$$Nest$mgetScrollOffsetY(this.this$0, 0) - ChatAttachAlert.access$2400(this.this$0)) - r73;
            if (ChatAttachAlert.access$2500(this.this$0) != 1) goto L15;
        L16:
            r85 = (int) (r85 + r23.getTranslationY());
        L17:
            int r10 = AndroidUtilities.dp(20.0f) + r85;
            getMeasuredHeight();
            AndroidUtilities.dp(45.0f);
            ChatAttachAlert.access$2600(this.this$0);
            if (r6 == 0) goto L20;
            int r12 = ActionBar.getCurrentActionBarHeight();
        L22:
            if (r6 != 2) goto L26;
            if (r85 >= r12) goto L25;
            float r52 = Math.max(0.0f, 1.0f - ((r12 - r85) / ChatAttachAlert.access$2800(this.this$0)));
            float r16 = 0.0f;
        L39:
            ChatAttachAlert r4 = this.this$0;
            if (r4.inBubbleMode == true) goto L42;
            int r74 = AndroidUtilities.statusBarHeight;
            r85 = r85 + r74;
            r10 = r10 + r74;
        L42:
            boolean r42 = ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r4).hasCustomBackground();
            ChatAttachAlert r75 = this.this$0;
            if (r42 == false) goto L45;
            int r43 = ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r75).getCustomBackground();
        L46:
            ChatAttachAlert.access$2900(this.this$0).setAlpha(r3);
            ChatAttachAlert.access$3100(this.this$0).setBounds(0, r85, getMeasuredWidth(), (getMeasuredHeight() + AndroidUtilities.dp(45.0f)) + ChatAttachAlert.access$3000(this.this$0));
            ChatAttachAlert.access$3200(this.this$0).draw(r22);
            if (r6 != 2) goto L49;
            Theme.dialogs_onlineCirclePaint.setColor(r43);
            Theme.dialogs_onlineCirclePaint.setAlpha(r3);
            float r17 = 24.0f;
            float r19 = 4.0f;
            float r18 = 1.0f;
            this.rect.set(ChatAttachAlert.access$3300(this.this$0), ChatAttachAlert.access$3400(this.this$0) + r85, getMeasuredWidth() - ChatAttachAlert.access$3500(this.this$0), (ChatAttachAlert.access$3600(this.this$0) + r85) + AndroidUtilities.dp(24.0f));
        L51:
            if (r52 == r18) goto L54;
            if (r6 == 2) goto L54;
        L55:
            Paint r76 = Theme.dialogs_onlineCirclePaint;
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).hasCustomActionBarBackground() == false) goto L58;
            r43 = ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).getCustomActionBarBackground();
        L58:
            r76.setColor(r43);
            Theme.dialogs_onlineCirclePaint.setAlpha(r3);
            this.rect.set(ChatAttachAlert.access$3700(this.this$0), ChatAttachAlert.access$3800(this.this$0) + r85, getMeasuredWidth() - ChatAttachAlert.access$3900(this.this$0), (ChatAttachAlert.access$4000(this.this$0) + r85) + AndroidUtilities.dp(r17));
        L60:
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).hasCustomActionBarBackground() == false) goto L65;
            Theme.dialogs_onlineCirclePaint.setColor(ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).getCustomActionBarBackground());
            Theme.dialogs_onlineCirclePaint.setAlpha(r3);
            int r32 = ChatAttachAlert.m9525$$Nest$mgetScrollOffsetY(this.this$0, 0);
            if (this.this$0.inBubbleMode == true) goto L64;
            r32 = r32 + AndroidUtilities.statusBarHeight;
        L64:
            this.rect.set(ChatAttachAlert.access$4100(r4), ((ChatAttachAlert.access$4200(this.this$0) + r85) + AndroidUtilities.dp(12.0f)) * r52, getMeasuredWidth() - ChatAttachAlert.access$4300(this.this$0), r32 + AndroidUtilities.dp(12.0f));
            r22.save();
            r22.drawRect(this.rect, Theme.dialogs_onlineCirclePaint);
            r22.restore();
        L65:
            FrameLayout r33 = this.this$0.headerView;
            if (r33 == null) goto L70;
            if (r33.getAlpha() != r18) goto L70;
        L90:
            r22.restore();
            return;
        L70:
            if (r52 == r16) goto L90;
            int r34 = AndroidUtilities.dp(36.0f);
            this.rect.set((getMeasuredWidth() - r34) / 2, r10, (getMeasuredWidth() + r34) / 2, r10 + AndroidUtilities.dp(r19));
            if (r6 != 2) goto L74;
            int r35 = 536870912;
            float r15 = r52;
        L89:
            int r44 = Color.alpha(r35);
            Theme.dialogs_onlineCirclePaint.setColor(r35);
            Theme.dialogs_onlineCirclePaint.setAlpha((int) (((r44 * r15) * r52) * r23.getAlpha()));
            r22.drawRoundRect(this.rect, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), Theme.dialogs_onlineCirclePaint);
            goto L90
        L74:
            boolean r36 = ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).hasCustomActionBarBackground();
            ChatAttachAlert r45 = this.this$0;
            if (r36 == false) goto L85;
            int r37 = ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r45).getCustomActionBarBackground();
            if (ColorUtils.calculateLuminance(r37) >= 0.5d) goto L79;
            int r46 = -1;
        L80:
            r35 = ColorUtils.blendARGB(r37, r46, 0.5f);
            FrameLayout r47 = this.this$0.headerView;
            if (r47 != null) goto L83;
        L82:
            r15 = r18;
            goto L89
        L83:
            float r48 = r47.getAlpha();
        L84:
            r15 = r18 - r48;
            goto L89
        L79:
            r46 = -16777216;
            goto L80
        L85:
            r35 = ChatAttachAlert.access$4400(r45, Theme.key_sheet_scrollUp);
            FrameLayout r49 = this.this$0.headerView;
            if (r49 == null) goto L82;
            r48 = r49.getAlpha();
        L54:
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).hasCustomActionBarBackground() == false) goto L60;
        L49:
            r19 = 4.0f;
            r18 = 1.0f;
            r17 = 24.0f;
            goto L51
        L45:
            r43 = ChatAttachAlert.m9523$$Nest$mgetActionBarDrawableColor(r75);
            goto L46
        L25:
            r16 = 0.0f;
            r52 = 1.0f;
            goto L39
        L26:
            float r77 = r73;
            r16 = 0.0f;
            if (r5 != ChatAttachAlert.m9467$$Nest$fgetlocationLayout(this.this$0)) goto L31;
            int r410 = AndroidUtilities.dp(11.0f);
        L29:
            float r78 = r77 + r410;
        L38:
            float r411 = this.this$0.actionBar.getAlpha();
            int r53 = (int) (((r12 - r78) + AndroidUtilities.statusBarHeight) * r411);
            r85 = r85 - r53;
            r10 = r10 - r53;
            r52 = 1.0f - r411;
            goto L39
        L31:
            if (r5 != ChatAttachAlert.m9480$$Nest$fgetpollLayout(this.this$0)) goto L35;
            int r412 = AndroidUtilities.dp(3.0f);
        L33:
            r78 = r77 - r412;
            goto L38
        L35:
            if (r5 != ChatAttachAlert.m9491$$Nest$fgettodoLayout(this.this$0)) goto L37;
            r412 = AndroidUtilities.dp(3.0f);
            goto L33
        L37:
            r410 = AndroidUtilities.dp(4.0f);
            goto L29
        L20:
            r12 = ChatAttachAlert.access$2700(this.this$0);
            goto L22
        L15:
            if (ChatAttachAlert.m9495$$Nest$fgetviewChangeAnimator(this.this$0) == null) goto L17;
        L11:
            r84 = 0.0f;
            goto L12
        L7:
            r82 = 0.0f;
            goto L8
        }

        private int getCurrentTop() {
            ChatAttachAlert r0 = this.this$0;
            int r2 = 0;
            int r1 = r0.scrollOffsetY[0] - (ChatAttachAlert.access$6400(r0) * 2);
            int r02 = AndroidUtilities.dp(13.0f);
            FrameLayout r3 = this.this$0.headerView;
            if (r3 == null) goto L5;
            r2 = AndroidUtilities.dp(r3.getAlpha() * 26.0f);
        L5:
            int r12 = r1 - (r02 + r2);
            FrameLayout r03 = this.this$0.topCommentContainer;
            if (r03 == null) goto L8;
            float r04 = r03.getAlpha() * this.this$0.topCommentContainer.getMeasuredHeight();
        L9:
            int r13 = (r12 - ((int) r04)) + AndroidUtilities.dp(20.0f);
            if (this.this$0.inBubbleMode == false) goto L12;
            return r13;
        L12:
            return r13 + AndroidUtilities.statusBarHeight;
        L8:
            r04 = 0.0f;
            goto L9
        }

        private int getRootBottomInset(int r1) {
            WindowInsetsCompat r0 = ViewCompat.getRootWindowInsets(this);
            if (r0 != null) goto L5;
            return 0;
        L5:
            return r0.getInsets(r1).bottom;
        }

        private int getRootKeyboardHeight() {
            return getRootBottomInset(WindowInsetsCompat.Type.ime());
        }

        private float getY(View r7) {
            float r1 = 0.0f;
            if ((r7 instanceof AttachAlertLayout) == false) goto L42;
            AttachAlertLayout r0 = (AttachAlertLayout) r7;
            int r2 = r0.needsActionBar();
            int r3 = AndroidUtilities.dp(13.0f);
            FrameLayout r4 = this.this$0.headerView;
            if (r4 == null) goto L7;
            float r42 = r4.getAlpha();
        L8:
            int r32 = r3 + ((int) (r42 * AndroidUtilities.dp(26.0f)));
            FrameLayout r43 = this.this$0.topCommentContainer;
            if (r43 == null) goto L11;
            r1 = r43.getAlpha() * this.this$0.topCommentContainer.getMeasuredHeight();
        L11:
            int r33 = r32 + ((int) r1);
            int r12 = (ChatAttachAlert.m9525$$Nest$mgetScrollOffsetY(this.this$0, 0) - ChatAttachAlert.access$2000(this.this$0)) - r33;
            if (ChatAttachAlert.access$2100(this.this$0) != 1) goto L14;
        L15:
            r12 = (int) (r12 + r7.getTranslationY());
        L16:
            int r72 = AndroidUtilities.dp(20.0f) + r12;
            if (r2 == 0) goto L19;
            int r44 = ActionBar.getCurrentActionBarHeight();
        L21:
            if (r2 == 2) goto L38;
            if ((r12 + ChatAttachAlert.access$2300(this.this$0)) >= r44) goto L38;
            float r13 = r33;
            if (r0 != ChatAttachAlert.m9467$$Nest$fgetlocationLayout(this.this$0)) goto L29;
            int r02 = AndroidUtilities.dp(11.0f);
        L27:
            float r14 = r13 + r02;
        L36:
            r72 = r72 - ((int) (((r44 - r14) + AndroidUtilities.statusBarHeight) * this.this$0.actionBar.getAlpha()));
            goto L38
        L29:
            if (r0 != ChatAttachAlert.m9480$$Nest$fgetpollLayout(this.this$0)) goto L33;
            int r03 = AndroidUtilities.dp(3.0f);
        L31:
            r14 = r13 - r03;
            goto L36
        L33:
            if (r0 != ChatAttachAlert.m9491$$Nest$fgettodoLayout(this.this$0)) goto L35;
            r03 = AndroidUtilities.dp(3.0f);
            goto L31
        L35:
            r02 = AndroidUtilities.dp(4.0f);
        L38:
            if (this.this$0.inBubbleMode == true) goto L41;
            r72 = r72 + AndroidUtilities.statusBarHeight;
        L41:
            return r72;
        L19:
            r44 = ChatAttachAlert.access$2200(this.this$0);
            goto L21
        L14:
            if (ChatAttachAlert.m9495$$Nest$fgetviewChangeAnimator(this.this$0) == null) goto L16;
        L7:
            r42 = 0.0f;
            goto L8
        L42:
            return 0.0f;
        }

        private /* synthetic */ void lambda$onMeasure$0() {
            ChatAttachAlert.m9442$$Nest$fgetbuttonsAdapter(this.this$0).notifyDataSetChanged();
        }

        private void onMeasureInternal(int r13, int r14) {
            int r0 = View.MeasureSpec.getSize(r13);
            int r1 = View.MeasureSpec.getSize(r14);
            setMeasuredDimension(r0, r1);
            int r02 = r0 - (ChatAttachAlert.access$1600(this.this$0) * 2);
            if (this.this$0.commentTextView.isWaitingForKeyboardOpen() == true) goto L12;
            if (AndroidUtilities.dp(20.0f) < 0) goto L12;
            if (this.this$0.commentTextView.isPopupShowing() == true) goto L12;
            if (this.this$0.commentTextView.isAnimatePopupClosing() == true) goto L12;
            this.ignoreLayout = true;
            this.this$0.commentTextView.hideEmojiView();
            this.ignoreLayout = false;
        L12:
            if (this.this$0.topCommentTextView.isWaitingForKeyboardOpen() == true) goto L21;
            if (AndroidUtilities.dp(20.0f) < 0) goto L21;
            if (this.this$0.topCommentTextView.isPopupShowing() == true) goto L21;
            if (this.this$0.topCommentTextView.isAnimatePopupClosing() == true) goto L21;
            this.ignoreLayout = true;
            this.this$0.topCommentTextView.hideEmojiView();
            this.ignoreLayout = false;
        L21:
            if (ChatAttachAlert.m9480$$Nest$fgetpollLayout(this.this$0) == null) goto L34;
            if (AndroidUtilities.dp(20.0f) < 0) goto L34;
            if (ChatAttachAlert.m9480$$Nest$fgetpollLayout(this.this$0).isWaitingForKeyboardOpen() == true) goto L34;
            if (ChatAttachAlert.m9480$$Nest$fgetpollLayout(this.this$0).isPopupShowing() == true) goto L34;
            if (ChatAttachAlert.m9480$$Nest$fgetpollLayout(this.this$0).isAnimatePopupClosing() == true) goto L34;
            if (ChatAttachAlert.m9480$$Nest$fgetpollLayout(this.this$0).isEmojiSearchOpened == true) goto L34;
            this.ignoreLayout = true;
            ChatAttachAlert.m9480$$Nest$fgetpollLayout(this.this$0).hideEmojiView();
            this.ignoreLayout = false;
        L34:
            if (ChatAttachAlert.m9491$$Nest$fgettodoLayout(this.this$0) == null) goto L47;
            if (AndroidUtilities.dp(20.0f) < 0) goto L47;
            if (ChatAttachAlert.m9491$$Nest$fgettodoLayout(this.this$0).isWaitingForKeyboardOpen() == true) goto L47;
            if (ChatAttachAlert.m9491$$Nest$fgettodoLayout(this.this$0).isPopupShowing() == true) goto L47;
            if (ChatAttachAlert.m9491$$Nest$fgettodoLayout(this.this$0).isAnimatePopupClosing() == true) goto L47;
            if (ChatAttachAlert.m9491$$Nest$fgettodoLayout(this.this$0).isEmojiSearchOpened == true) goto L47;
            this.ignoreLayout = true;
            ChatAttachAlert.m9491$$Nest$fgettodoLayout(this.this$0).hideEmojiView();
            this.ignoreLayout = false;
        L47:
            if (AndroidUtilities.dp(20.0f) < 0) goto L81;
            boolean r2 = ChatAttachAlert.access$1700(this.this$0);
            ChatAttachAlert r3 = this.this$0;
            if (r2 == true) goto L51;
            int r22 = ChatAttachAlert.m9524$$Nest$mgetEmojiPadding(r3);
        L66:
            int r32 = getRootBottomInset(WindowInsetsCompat.Type.ime());
            Math.max(getRootBottomInset(WindowInsetsCompat.Type.ime() | WindowInsetsCompat.Type.systemBars()), r22);
            if (r32 <= 0) goto L69;
            int r33 = 0;
        L70:
            int r34 = Math.max(r33, r22);
            this.ignoreLayout = true;
            boolean r4 = ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).occupyNavigationBar;
            ChatAttachAlert r6 = this.this$0;
            if (r4 == false) goto L73;
            ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r6).listPaddingBottom = AndroidUtilities.dp(62.0f) + r34;
            ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).onPreMeasure(r02, r1);
        L75:
            if (ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(this.this$0) == null) goto L80;
            boolean r42 = ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(this.this$0).occupyNavigationBar;
            ChatAttachAlert r62 = this.this$0;
            if (r42 == false) goto L79;
            ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(r62).listPaddingBottom = r34 + AndroidUtilities.dp(62.0f);
            ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(this.this$0).onPreMeasure(r02, r1);
            goto L80
        L79:
            ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(r62).listPaddingBottom = AndroidUtilities.navigationBarHeight;
            ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(this.this$0).onPreMeasure(r02, r1 - r22);
        L80:
            this.ignoreLayout = false;
            goto L81
        L73:
            ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r6).listPaddingBottom = AndroidUtilities.navigationBarHeight;
            ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).onPreMeasure(r02, r1 - r22);
            goto L75
        L69:
            r33 = AndroidUtilities.navigationBarHeight;
            goto L70
        L51:
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r3) != ChatAttachAlert.m9480$$Nest$fgetpollLayout(this.this$0)) goto L58;
            if (ChatAttachAlert.m9480$$Nest$fgetpollLayout(this.this$0).emojiView == null) goto L58;
            if (ChatAttachAlert.m9480$$Nest$fgetpollLayout(this.this$0).isEmojiSearchOpened == false) goto L58;
            r22 = AndroidUtilities.dp(120.0f);
        L58:
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0) == ChatAttachAlert.m9491$$Nest$fgettodoLayout(this.this$0)) goto L60;
        L64:
            r22 = 0;
            goto L66
        L60:
            if (ChatAttachAlert.m9491$$Nest$fgettodoLayout(this.this$0).emojiView == null) goto L64;
            if (ChatAttachAlert.m9491$$Nest$fgettodoLayout(this.this$0).isEmojiSearchOpened == false) goto L64;
            r22 = AndroidUtilities.dp(120.0f);
        L81:
            int r23 = getChildCount();
            int r35 = 0;
        L82:
            if (r35 >= r23) goto L134;
            View r7 = this.getChildAt(r35);
            if (r7 != null) goto L86;
        L87:
            AnonymousClass5 r63 = this;
            int r8 = r13;
            int r10 = r14;
        L133:
            r35 = r35 + 1;
            AnonymousClass5 this = r63;
            r13 = r8;
            r14 = r10;
            goto L82
        L86:
            if (r7.getVisibility() == 8) goto L87;
            if (r7 != ChatAttachAlert.m9460$$Nest$fgetfadeView(this.this$0)) goto L91;
            r63 = this;
            r8 = r13;
            r10 = r14;
            r63.measureChildWithMargins(r7, r8, 0, r10, 0);
            goto L133
        L91:
            r63 = this;
            r8 = r13;
            r10 = r14;
            int r12 = AndroidUtilities.statusBarHeight;
            int r132 = AndroidUtilities.navigationBarHeight;
            if ((r7 instanceof AttachAlertLayout) == false) goto L99;
            AttachAlertLayout r142 = (AttachAlertLayout) r7;
            if (r142.occupyStatusBar == false) goto L97;
            r12 = 0;
        L97:
            if (r142.occupyNavigationBar == false) goto L99;
            r132 = 0;
        L99:
            EditTextEmoji r143 = r63.this$0.commentTextView;
            if (r143 != null) goto L102;
        L103:
            EditTextEmoji r144 = r63.this$0.topCommentTextView;
            if (r144 == null) goto L108;
            if (r144.isPopupView(r7) == false) goto L108;
        L116:
            if (r63.this$0.inBubbleMode == false) goto L119;
            r7.measure(View.MeasureSpec.makeMeasureSpec(r02, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r63.getPaddingTop() + r1, TLObject.FLAG_30));
            goto L133
        L119:
            if (AndroidUtilities.isInMultiwindow == true) goto L125;
            if (AndroidUtilities.isTablet() == true) goto L125;
            r7.measure(View.MeasureSpec.makeMeasureSpec(r02, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r7.getLayoutParams().height, TLObject.FLAG_30));
        L125:
            if (AndroidUtilities.isTablet() == false) goto L131;
            int r122 = View.MeasureSpec.makeMeasureSpec(r02, TLObject.FLAG_30);
            if (AndroidUtilities.isTablet() == false) goto L129;
            float r145 = 200.0f;
        L130:
            r7.measure(r122, View.MeasureSpec.makeMeasureSpec(Math.min(AndroidUtilities.dp(r145), (r1 - AndroidUtilities.statusBarHeight) + r63.getPaddingTop()), TLObject.FLAG_30));
            goto L133
        L129:
            r145 = 320.0f;
            goto L130
        L131:
            r7.measure(View.MeasureSpec.makeMeasureSpec(r02, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((r1 - AndroidUtilities.statusBarHeight) + r63.getPaddingTop(), TLObject.FLAG_30));
        L108:
            if (ChatAttachAlert.m9480$$Nest$fgetpollLayout(r63.this$0) == null) goto L112;
            if (r7 == ChatAttachAlert.m9480$$Nest$fgetpollLayout(r63.this$0).emojiView) goto L116;
        L112:
            if (ChatAttachAlert.m9491$$Nest$fgettodoLayout(r63.this$0) != null) goto L114;
        L132:
            r63.measureChildWithMargins(r7, r8, 0, r10, r12 + r132);
            goto L133
        L114:
            if (r7 != ChatAttachAlert.m9491$$Nest$fgettodoLayout(r63.this$0).emojiView) goto L132;
        L102:
            if (r143.isPopupView(r7) == true) goto L116;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r4) {
            if (Build.VERSION.SDK_INT >= 31) goto L5;
        L12:
            r4.save();
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0) != ChatAttachAlert.m9476$$Nest$fgetphotoPreviewLayout(this.this$0)) goto L15;
        L20:
            drawChildBackground(r4, ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0));
        L21:
            super.dispatchDraw(r4);
            r4.restore();
            return;
        L15:
            if (ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(this.this$0) == ChatAttachAlert.m9476$$Nest$fgetphotoPreviewLayout(this.this$0)) goto L20;
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0) != ChatAttachAlert.m9475$$Nest$fgetphotoLayout(this.this$0)) goto L21;
            if (ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(this.this$0) != null) goto L21;
        L5:
            if (ChatAttachAlert.m9485$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0) == null) goto L12;
            this.this$0.blur3_InvalidateBlur();
            if (ChatAttachAlert.m9465$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0) == null) goto L10;
            ChatAttachAlert.m9465$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).setSize(ChatAttachAlert.access$6500(this.this$0).getMeasuredWidth(), ChatAttachAlert.access$6600(this.this$0).getMeasuredHeight());
            ChatAttachAlert.m9465$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).updateDisplayListIfNeeded();
        L10:
            if (ChatAttachAlert.m9464$$Nest$fgetiBlur3SourceGlass(this.this$0) == null) goto L12;
            ChatAttachAlert.m9464$$Nest$fgetiBlur3SourceGlass(this.this$0).setSize(ChatAttachAlert.access$6700(this.this$0).getMeasuredWidth(), ChatAttachAlert.access$6800(this.this$0).getMeasuredHeight());
            ChatAttachAlert.m9464$$Nest$fgetiBlur3SourceGlass(this.this$0).updateDisplayListIfNeeded();
            goto L12
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public void drawBlurRect(Canvas r1, float r2, Rect r3, Paint r4, boolean r5) {
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r22, View r23, long r24) {
            if ((r23 instanceof AttachAlertLayout) == true) goto L5;
        L100:
            ChatAttachAlert r3 = this.this$0;
            ActionBar r5 = r3.actionBar;
            if (r23 != r5) goto L112;
            float r32 = r5.getAlpha();
            if (r32 > 0.0f) goto L106;
            return false;
        L106:
            if (r32 >= 1.0f) goto L108;
            r22.save();
            r22.clipRect(this.this$0.actionBar.getX(), getY(ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0)), this.this$0.actionBar.getX() + this.this$0.actionBar.getWidth(), this.this$0.actionBar.getY() + this.this$0.actionBar.getHeight());
            boolean r0 = super.drawChild(r22, r23, r24);
            r22.restore();
            return r0;
        L108:
            return super.drawChild(r22, r23, r24);
        L112:
            if ((r23 instanceof EmojiView) == false) goto L118;
            if (ChatAttachAlert.m9456$$Nest$fgetemojiViewChildBg(r3) == null) goto L118;
            r22.save();
            ChatAttachAlert.m9456$$Nest$fgetemojiViewChildBg(this.this$0).setBounds(0, r23.getTop(), getMeasuredWidth(), getMeasuredHeight());
            r22.clipPath(ChatAttachAlert.m9456$$Nest$fgetemojiViewChildBg(this.this$0).getPath());
            ChatAttachAlert.m9456$$Nest$fgetemojiViewChildBg(this.this$0).draw(r22);
            boolean r02 = super.drawChild(r22, r23, r24);
            r22.restore();
            return r02;
        L118:
            return super.drawChild(r22, r23, r24);
        L5:
            if (r23.getAlpha() <= 0.0f) goto L100;
            r22.save();
            r22.translate(0.0f, this.this$0.currentPanTranslationY);
            int r33 = (int) (r23.getAlpha() * 255.0f);
            AttachAlertLayout r7 = (AttachAlertLayout) r23;
            int r8 = r7.needsActionBar();
            int r9 = AndroidUtilities.dp(13.0f);
            FrameLayout r10 = this.this$0.headerView;
            if (r10 == null) goto L9;
            int r102 = AndroidUtilities.dp(r10.getAlpha() * 26.0f);
        L10:
            int r92 = r9 + r102;
            FrameLayout r103 = this.this$0.topCommentContainer;
            if (r103 == null) goto L13;
            float r104 = r103.getAlpha() * this.this$0.topCommentContainer.getMeasuredHeight();
        L14:
            int r93 = r92 + ((int) r104);
            ChatAttachAlert r105 = this.this$0;
            if (r7 != ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r105)) goto L17;
            int r11 = 0;
        L18:
            int r106 = (ChatAttachAlert.m9525$$Nest$mgetScrollOffsetY(r105, r11) - ChatAttachAlert.access$4500(this.this$0)) - r93;
            if (ChatAttachAlert.access$4600(this.this$0) != 1) goto L21;
        L22:
            r106 = (int) (r106 + r23.getTranslationY());
        L23:
            int r112 = AndroidUtilities.dp(20.0f) + r106;
            int r13 = (getMeasuredHeight() + AndroidUtilities.dp(45.0f)) + ChatAttachAlert.access$4700(this.this$0);
            if (r8 == 0) goto L26;
            int r14 = ActionBar.getCurrentActionBarHeight();
        L28:
            if (r8 != 2) goto L32;
            if (r106 >= r14) goto L31;
            float r72 = Math.max(0.0f, 1.0f - ((r14 - r106) / ChatAttachAlert.access$4900(this.this$0)));
            float r17 = 0.0f;
        L48:
            ChatAttachAlert r52 = this.this$0;
            if (r52.inBubbleMode == true) goto L51;
            int r94 = AndroidUtilities.statusBarHeight;
            r106 = r106 + r94;
            r112 = r112 + r94;
            r13 = r13 - r94;
        L51:
            boolean r53 = ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r52).hasCustomBackground();
            ChatAttachAlert r95 = this.this$0;
            if (r53 == false) goto L54;
            int r54 = ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r95).getCustomBackground();
        L56:
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0) != ChatAttachAlert.m9476$$Nest$fgetphotoPreviewLayout(this.this$0)) goto L58;
        L64:
            boolean r16 = false;
        L65:
            if (r16 == false) goto L70;
            ChatAttachAlert.access$5200(this.this$0).setAlpha(r33);
            float r18 = 1.0f;
            ChatAttachAlert.access$5300(this.this$0).setBounds(0, r106, getMeasuredWidth(), r13);
            ChatAttachAlert.access$5400(this.this$0).draw(r22);
            if (r8 != 2) goto L69;
            Theme.dialogs_onlineCirclePaint.setColor(r54);
            Theme.dialogs_onlineCirclePaint.setAlpha(r33);
            float r19 = 24.0f;
            this.rect.set(ChatAttachAlert.access$5500(this.this$0), ChatAttachAlert.access$5600(this.this$0) + r106, getMeasuredWidth() - ChatAttachAlert.access$5700(this.this$0), (ChatAttachAlert.access$5800(this.this$0) + r106) + AndroidUtilities.dp(24.0f));
        L72:
            if (r23 != ChatAttachAlert.m9450$$Nest$fgetcontactsLayout(this.this$0)) goto L74;
        L78:
            boolean r4 = super.drawChild(r22, r23, r24);
        L79:
            if (r16 == true) goto L81;
        L98:
            r22.restore();
            return r4;
        L81:
            if (r72 == r18) goto L84;
            if (r8 == 2) goto L84;
            Theme.dialogs_onlineCirclePaint.setColor(r54);
            Theme.dialogs_onlineCirclePaint.setAlpha(r33);
            this.rect.set(ChatAttachAlert.access$5900(this.this$0), ChatAttachAlert.access$6000(this.this$0) + r106, getMeasuredWidth() - ChatAttachAlert.access$6100(this.this$0), (ChatAttachAlert.access$6200(this.this$0) + r106) + AndroidUtilities.dp(r19));
        L84:
            FrameLayout r34 = this.this$0.headerView;
            if (r34 == null) goto L89;
            if (r34.getAlpha() == r18) goto L98;
        L89:
            if (r72 == r17) goto L98;
            int r35 = AndroidUtilities.dp(36.0f);
            this.rect.set((getMeasuredWidth() - r35) / 2, r112, (getMeasuredWidth() + r35) / 2, r112 + AndroidUtilities.dp(4.0f));
            if (r8 != 2) goto L93;
            int r36 = 536870912;
            float r6 = r72;
        L97:
            int r55 = Color.alpha(r36);
            Theme.dialogs_onlineCirclePaint.setColor(r36);
            Theme.dialogs_onlineCirclePaint.setAlpha((int) (((r55 * r6) * r72) * r23.getAlpha()));
            r22.drawRoundRect(this.rect, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), Theme.dialogs_onlineCirclePaint);
            goto L98
        L93:
            r36 = ChatAttachAlert.access$6300(this.this$0, Theme.key_sheet_scrollUp);
            FrameLayout r56 = this.this$0.headerView;
            if (r56 != null) goto L96;
            r6 = r18;
            goto L97
        L96:
            r6 = r18 - r56.getAlpha();
            goto L97
        L74:
            if (r23 == ChatAttachAlert.m9483$$Nest$fgetquickRepliesLayout(this.this$0)) goto L78;
            if (r23 == ChatAttachAlert.m9432$$Nest$fgetaudioLayout(this.this$0)) goto L78;
            r22.save();
            r4 = super.drawChild(r22, r23, r24);
            r22.restore();
        L69:
            r19 = 24.0f;
            goto L72
        L70:
            r18 = 1.0f;
            goto L69
        L58:
            if (ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(this.this$0) == ChatAttachAlert.m9476$$Nest$fgetphotoPreviewLayout(this.this$0)) goto L64;
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0) == ChatAttachAlert.m9475$$Nest$fgetphotoLayout(this.this$0)) goto L62;
        L63:
            r16 = true;
            goto L65
        L62:
            if (ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(this.this$0) == null) goto L64;
        L54:
            r54 = ChatAttachAlert.m9523$$Nest$mgetActionBarDrawableColor(r95);
            goto L56
        L31:
            r17 = 0.0f;
        L47:
            r72 = 1.0f;
            goto L48
        L32:
            r17 = 0.0f;
            if ((ChatAttachAlert.access$5000(this.this$0) + r106) >= r14) goto L47;
            float r57 = r93;
            if (r7 != ChatAttachAlert.m9467$$Nest$fgetlocationLayout(this.this$0)) goto L39;
            int r73 = AndroidUtilities.dp(11.0f);
        L37:
            float r58 = r57 + r73;
        L46:
            float r74 = Math.min(1.0f, ((r14 - r106) - ChatAttachAlert.access$5100(this.this$0)) / r58);
            int r59 = (int) ((r14 - r58) * r74);
            r106 = r106 - r59;
            r112 = r112 - r59;
            r13 = r13 + r59;
            r72 = 1.0f - r74;
            goto L48
        L39:
            if (r7 != ChatAttachAlert.m9480$$Nest$fgetpollLayout(this.this$0)) goto L43;
            int r75 = AndroidUtilities.dp(3.0f);
        L41:
            r58 = r57 - r75;
            goto L46
        L43:
            if (r7 != ChatAttachAlert.m9491$$Nest$fgettodoLayout(this.this$0)) goto L45;
            r75 = AndroidUtilities.dp(3.0f);
            goto L41
        L45:
            r73 = AndroidUtilities.dp(4.0f);
            goto L37
        L26:
            r14 = ChatAttachAlert.access$4800(this.this$0);
            goto L28
        L21:
            if (ChatAttachAlert.m9495$$Nest$fgetviewChangeAnimator(this.this$0) == null) goto L23;
        L17:
            r11 = 1;
            goto L18
        L13:
            r104 = 0.0f;
            goto L14
        L9:
            r102 = 0;
            goto L10
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.adjustPanLayoutHelper.setResizableView(this);
            this.adjustPanLayoutHelper.onAttach();
            this.this$0.commentTextView.setAdjustPanLayoutHelper(this.adjustPanLayoutHelper);
            this.this$0.topCommentTextView.setAdjustPanLayoutHelper(this.adjustPanLayoutHelper);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.adjustPanLayoutHelper.onDetach();
        }

        @Override // android.view.View
        public void onDraw(Canvas r1) {
            boolean r0 = this.this$0.inBubbleMode;
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r4) {
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).onContainerViewTouchEvent(r4) == false) goto L6;
            return true;
        L6:
            if (r4.getAction() != 0) goto L16;
            if (this.this$0.scrollOffsetY[0] == 0) goto L16;
            if (r4.getY() >= getCurrentTop()) goto L16;
            if (this.this$0.actionBar.getAlpha() != 0.0f) goto L16;
            this.this$0.onDismissWithTouchOutside();
            return true;
        L16:
            return super.onInterceptTouchEvent(r4);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r17, int r18, int r19, int r20, int r21) {
            int r6 = r20 - r18;
            if (this.lastNotifyWidth == r6) goto L9;
            this.lastNotifyWidth = r6;
            if (ChatAttachAlert.m9469$$Nest$fgetmessageSendPreview(this.this$0) == null) goto L9;
            if (ChatAttachAlert.m9469$$Nest$fgetmessageSendPreview(this.this$0).isShowing() == false) goto L9;
            ChatAttachAlert.m9469$$Nest$fgetmessageSendPreview(this.this$0).dismiss();
        L9:
            int r5 = getChildCount();
            if (Build.VERSION.SDK_INT < 29) goto L12;
            ChatAttachAlert.m9459$$Nest$fgetexclustionRect(this.this$0).set(r18, r19, r20, r21);
            setSystemGestureExclusionRects(ChatAttachAlert.m9458$$Nest$fgetexclusionRects(this.this$0));
        L12:
            int r1 = getRootKeyboardHeight();
            int r3 = getPaddingBottom();
            if (ChatAttachAlert.access$1800(this.this$0) == false) goto L15;
        L50:
            setBottomClip(r3);
            int r7 = 0;
        L51:
            if (r7 >= r5) goto L126;
            View r9 = getChildAt(r7);
            if (r9.getVisibility() == 8) goto L125;
            int r10 = AndroidUtilities.statusBarHeight;
            if (r1 != 0) goto L58;
            int r11 = Math.max(AndroidUtilities.navigationBarHeight, r3);
        L60:
            if ((r9 instanceof AttachAlertLayout) == false) goto L67;
            AttachAlertLayout r12 = (AttachAlertLayout) r9;
            if (r12.occupyStatusBar == false) goto L65;
            r10 = 0;
        L65:
            if (r12.occupyNavigationBar == false) goto L67;
            r11 = 0;
        L67:
            FrameLayout.LayoutParams r122 = (FrameLayout.LayoutParams) r9.getLayoutParams();
            int r13 = r9.getMeasuredWidth();
            int r14 = r9.getMeasuredHeight();
            int r15 = r122.gravity;
            if (r15 != (-1)) goto L70;
            r15 = 51;
        L70:
            int r8 = r15 & 112;
            int r152 = r15 & 7;
            if (r152 != 1) goto L73;
            int r2 = ((r6 - r13) / 2) + r122.leftMargin;
            int r153 = r122.rightMargin;
        L76:
            int r22 = r2 - r153;
        L79:
            if (r8 != 16) goto L81;
            int r82 = ((((r21 - r11) - r19) - r14) / 2) + r122.topMargin;
            int r102 = r122.bottomMargin;
        L86:
            int r83 = r82 - r102;
        L89:
            ChatAttachAlert r103 = this.this$0;
            if (r9 != r103.actionBar) goto L92;
        L93:
            r83 = 0;
        L94:
            EditTextEmoji r104 = this.this$0.commentTextView;
            if (r104 != null) goto L97;
        L98:
            EditTextEmoji r105 = this.this$0.topCommentTextView;
            if (r105 == null) goto L103;
            if (r105.isPopupView(r9) == false) goto L103;
        L111:
            if (AndroidUtilities.isTablet() == false) goto L114;
            int r84 = getMeasuredHeight();
            int r106 = r9.getMeasuredHeight();
        L113:
            r83 = r84 - r106;
        L124:
            r9.layout(r22, r83, r13 + r22, r14 + r83);
            goto L125
        L114:
            r84 = getMeasuredHeight() + r1;
            r106 = r9.getMeasuredHeight();
        L103:
            if (ChatAttachAlert.m9480$$Nest$fgetpollLayout(this.this$0) == null) goto L107;
            if (r9 == ChatAttachAlert.m9480$$Nest$fgetpollLayout(this.this$0).emojiView) goto L111;
        L107:
            if (ChatAttachAlert.m9491$$Nest$fgettodoLayout(this.this$0) != null) goto L109;
        L115:
            ChatAttachAlert r107 = this.this$0;
            if (r9 != r107.mentionContainer) goto L122;
            if (r107.captionAbove == false) goto L124;
            int r85 = AndroidUtilities.statusBarHeight;
            int r108 = ActionBar.getCurrentActionBarHeight();
        L120:
            r83 = r85 + r108;
            goto L124
        L122:
            if (r9 != ChatAttachAlert.m9441$$Nest$fgetbottomFadeView(r107)) goto L124;
            r85 = r83 + AndroidUtilities.navigationBarHeight;
            r108 = Math.max(r1, ChatAttachAlert.m9524$$Nest$mgetEmojiPadding(this.this$0));
            goto L120
        L109:
            if (r9 != ChatAttachAlert.m9491$$Nest$fgettodoLayout(this.this$0).emojiView) goto L115;
        L97:
            if (r104.isPopupView(r9) == true) goto L111;
        L92:
            if (r9 != ChatAttachAlert.m9460$$Nest$fgetfadeView(r103)) goto L94;
        L81:
            if (r8 != 48) goto L83;
            r83 = r122.topMargin + r10;
            goto L89
        L83:
            if (r8 == 80) goto L85;
            r83 = r122.topMargin;
            goto L89
        L85:
            r82 = ((r21 - r11) - r19) - r14;
            r102 = r122.bottomMargin;
            goto L86
        L73:
            if (r152 == 5) goto L75;
            r22 = r122.leftMargin + getPaddingLeft();
            goto L79
        L75:
            r2 = ((r6 - r13) - r122.rightMargin) - getPaddingRight();
            r153 = ChatAttachAlert.access$1900(this.this$0);
            goto L76
        L58:
            r11 = 0;
        L125:
            r7 = r7 + 1;
            goto L51
        L126:
            notifyHeightChanged();
            ChatAttachAlert r16 = this.this$0;
            r16.updateLayout(ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r16), false, 0);
            ChatAttachAlert r110 = this.this$0;
            r110.updateLayout(ChatAttachAlert.m9471$$Nest$fgetnextAttachLayout(r110), false, 0);
            ChatAttachAlert r111 = this.this$0;
            if (r111.captionAbove == false) goto L130;
            r111.updateCommentTextViewPosition();
        L130:
            if (ChatAttachAlert.m9475$$Nest$fgetphotoLayout(this.this$0) != null) goto L132;
            return;
        L132:
            if (ChatAttachAlert.m9475$$Nest$fgetphotoLayout(this.this$0).gridView != null) goto L134;
            return;
        L134:
            if (ChatAttachAlert.m9475$$Nest$fgetphotoLayout(this.this$0).gridView.getFastScroll() == null) goto L146;
            RecyclerListView.FastScroll r112 = ChatAttachAlert.m9475$$Nest$fgetphotoLayout(this.this$0).gridView.getFastScroll();
            int r23 = ActionBar.getCurrentActionBarHeight() + ChatAttachAlert.m9475$$Nest$fgetphotoLayout(this.this$0).listAdditionalH;
            if (this.this$0.captionAbove == false) goto L138;
            int r86 = (int) (r4.topCommentContainer.getMeasuredHeight() * this.this$0.topCommentContainer.getAlpha());
        L139:
            r112.topOffset = r23 + r86;
            ChatAttachAlert.m9475$$Nest$fgetphotoLayout(this.this$0).gridView.getFastScroll().invalidate();
            return;
        L138:
            r86 = 0;
            goto L139
        L146:
            return;
        L15:
            if (ChatAttachAlert.m9480$$Nest$fgetpollLayout(this.this$0) == null) goto L28;
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0) != ChatAttachAlert.m9480$$Nest$fgetpollLayout(this.this$0)) goto L28;
            if (ChatAttachAlert.m9480$$Nest$fgetpollLayout(this.this$0).emojiView == null) goto L28;
            if (r1 <= AndroidUtilities.dp(20.0f)) goto L23;
        L40:
            int r72 = 0;
        L48:
            if (r72 <= 0) goto L50;
            r3 = r3 + r72;
            goto L50
        L23:
            if (AndroidUtilities.isInMultiwindow == true) goto L40;
            if (AndroidUtilities.isTablet() == true) goto L40;
            r72 = ChatAttachAlert.m9480$$Nest$fgetpollLayout(this.this$0).getEmojiPadding();
        L28:
            if (ChatAttachAlert.m9491$$Nest$fgettodoLayout(this.this$0) == null) goto L42;
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0) != ChatAttachAlert.m9491$$Nest$fgettodoLayout(this.this$0)) goto L42;
            if (ChatAttachAlert.m9491$$Nest$fgettodoLayout(this.this$0).emojiView == null) goto L42;
            if (r1 > AndroidUtilities.dp(20.0f)) goto L40;
            if (AndroidUtilities.isInMultiwindow == true) goto L40;
            if (AndroidUtilities.isTablet() == true) goto L40;
            r72 = ChatAttachAlert.m9491$$Nest$fgettodoLayout(this.this$0).getEmojiPadding();
        L42:
            if (r1 > AndroidUtilities.dp(20.0f)) goto L40;
            if (AndroidUtilities.isInMultiwindow == true) goto L40;
            if (AndroidUtilities.isTablet() == true) goto L40;
            r72 = this.this$0.getCommentView().getEmojiPadding();
            goto L48
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r8, int r9) {
            if (getLayoutParams().height <= 0) goto L5;
            int r92 = getLayoutParams().height;
        L6:
            ChatAttachAlert r0 = this.this$0;
            if (r0.inBubbleMode == true) goto L9;
            this.ignoreLayout = true;
            setPadding(ChatAttachAlert.access$1300(r0), 0, ChatAttachAlert.access$1400(this.this$0), 0);
            this.ignoreLayout = false;
        L9:
            int r02 = View.MeasureSpec.getSize(r8) - (ChatAttachAlert.access$1500(this.this$0) * 2);
            if (AndroidUtilities.isTablet() == false) goto L12;
            this.this$0.selectedMenuItem.setAdditionalYOffset(-AndroidUtilities.dp(3.0f));
        L16:
            ((FrameLayout.LayoutParams) this.this$0.doneItem.getLayoutParams()).height = ActionBar.getCurrentActionBarHeight();
            this.ignoreLayout = true;
            int r03 = (int) (r02 / Math.min(4.5f, ChatAttachAlert.m9442$$Nest$fgetbuttonsAdapter(this.this$0).getItemCount()));
            if (ChatAttachAlert.m9431$$Nest$fgetattachItemSize(this.this$0) == r03) goto L19;
            ChatAttachAlert.m9499$$Nest$fputattachItemSize(this.this$0, r03);
            AndroidUtilities.runOnUIThread(new ChatAttachAlert$5$$ExternalSyntheticLambda0(this));
        L19:
            this.ignoreLayout = false;
            onMeasureInternal(r8, View.MeasureSpec.makeMeasureSpec(r92, TLObject.FLAG_30));
            return;
        L12:
            Point r1 = AndroidUtilities.displaySize;
            int r5 = r1.x;
            int r12 = r1.y;
            ChatAttachAlert r6 = this.this$0;
            if (r5 <= r12) goto L15;
            r6.selectedMenuItem.setAdditionalYOffset(0);
            goto L16
        L15:
            r6.selectedMenuItem.setAdditionalYOffset(-AndroidUtilities.dp(3.0f));
            goto L16
        L5:
            r92 = View.MeasureSpec.getSize(r9);
            goto L6
        }

        @Override // android.view.View
        public void onSizeChanged(int r7, int r8, int r9, int r10) {
            super.onSizeChanged(r7, r8, r9, r10);
            int r92 = 0;
            if (Build.VERSION.SDK_INT < 31) goto L13;
            WindowInsets r72 = getRootWindowInsets();
            if (r72 == null) goto L13;
            RoundedCorner r82 = r72.getRoundedCorner(3);
            RoundedCorner r73 = r72.getRoundedCorner(2);
            if (r82 != null) goto L9;
            int r83 = 0;
        L10:
            if (r73 == null) goto L15;
            r92 = r73.getRadius();
        L15:
            if (ChatAttachAlert.m9456$$Nest$fgetemojiViewChildBg(this.this$0) == null) goto L18;
            ChatAttachAlert.m9456$$Nest$fgetemojiViewChildBg(this.this$0).setRadius(AndroidUtilities.dp(29.0f), AndroidUtilities.dp(29.0f), r92, r83, true);
            return;
        L18:
            return;
        L9:
            r83 = r82.getRadius();
        L13:
            r83 = 0;
            goto L15
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r3) {
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).onContainerViewTouchEvent(r3) == false) goto L6;
            return true;
        L6:
            if (this.this$0.isDismissed() == false) goto L8;
            return false;
        L8:
            if (super.onTouchEvent(r3) == false) goto L12;
            return true;
        L12:
            return false;
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }

        @Override // android.view.View
        public void setTranslationY(float r6) {
            ChatAttachAlert r0 = this.this$0;
            float r62 = r6 + r0.currentPanTranslationY;
            if (ChatAttachAlert.access$6900(r0) != 0) goto L6;
            this.initialTranslationY = r62;
        L6:
            if (ChatAttachAlert.access$7000(this.this$0) != 1) goto L17;
            ChatAttachAlert r3 = this.this$0;
            if (r62 >= 0.0f) goto L15;
            ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r3).setTranslationY(r62);
            ChatAttachAlert r2 = this.this$0;
            if (r2.avatarPicker == 0) goto L12;
        L13:
            r2.headerView.setTranslationY((ChatAttachAlert.m9433$$Nest$fgetbaseSelectedTextViewTranslationY(r2) + r62) - this.this$0.currentPanTranslationY);
        L14:
            this.this$0.buttonsRecyclerViewWrapper.setTranslationY(0.0f);
            r62 = 0.0f;
        L16:
            ChatAttachAlert.access$7100(this.this$0).invalidate();
            goto L17
        L12:
            if (r2.storyMediaPicker == false) goto L14;
        L15:
            ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r3).setTranslationY(0.0f);
            this.this$0.buttonsRecyclerViewWrapper.setTranslationY((-r62) + (r0.getMeasuredHeight() * (r62 / this.initialTranslationY)));
        L17:
            super.setTranslationY(r62 - this.this$0.currentPanTranslationY);
            if (ChatAttachAlert.access$7200(this.this$0) == 1) goto L21;
            ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).onContainerTranslationUpdated(this.this$0.currentPanTranslationY);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$6, reason: invalid class name */
    public class AnonymousClass6 implements SizeNotifierFrameLayout.SizeNotifierFrameLayoutDelegate {
        final /* synthetic */ ChatAttachAlert this$0;

        public AnonymousClass6(ChatAttachAlert r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout.SizeNotifierFrameLayoutDelegate
        public void onSizeChanged(int r1, boolean r2) {
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0) != ChatAttachAlert.m9476$$Nest$fgetphotoPreviewLayout(this.this$0)) goto L6;
            ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).invalidate();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$7, reason: invalid class name */
    public class AnonymousClass7 extends ActionBar {
        final /* synthetic */ ChatAttachAlert this$0;

        public AnonymousClass7(ChatAttachAlert r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // android.view.View
        public void setAlpha(float r5) {
            float r0 = getAlpha();
            super.setAlpha(r5);
            if (r0 == r5) goto L33;
            TextView r02 = this.this$0.selectedTextView;
            float r1 = 0.0f;
            if (r02 == null) goto L11;
            float r3 = 1.0f - r5;
            r02.setAlpha(r3);
            TextView r03 = this.this$0.selectedTextView;
            if (r3 <= 0.0f) goto L9;
            int r32 = 0;
        L10:
            r03.setVisibility(r32);
            goto L11
        L9:
            r32 = 8;
        L11:
            ChatAttachAlert.m9520$$Nest$mcheckUi_fadeTopAlpha(this.this$0);
            ChatAttachAlert.access$7300(this.this$0).invalidate();
            if (ChatAttachAlert.m9461$$Nest$fgetframeLayout2(this.this$0) == null) goto L34;
            ChatAttachAlert r04 = this.this$0;
            if (r04.buttonsRecyclerViewWrapper == null) goto L35;
            Object r05 = ChatAttachAlert.m9461$$Nest$fgetframeLayout2(r04).getTag();
            ChatAttachAlert r33 = this.this$0;
            if (r05 != null) goto L25;
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r33) != null) goto L20;
        L21:
            this.this$0.buttonsRecyclerViewWrapper.setAlpha(1.0f - r5);
            this.this$0.buttonsRecyclerViewWrapper.setTranslationY(AndroidUtilities.dp(44.0f) * r5);
        L22:
            ChatAttachAlert.m9461$$Nest$fgetframeLayout2(this.this$0).setTranslationY(AndroidUtilities.dp(48.0f) * r5);
            return;
        L20:
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(this.this$0).shouldHideBottomButtons() == false) goto L22;
        L25:
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r33) == null) goto L27;
            return;
        L27:
            if (r5 != 0.0f) goto L30;
            r1 = 1.0f;
        L30:
            if (this.this$0.buttonsRecyclerViewWrapper.getAlpha() == r1) goto L37;
            this.this$0.buttonsRecyclerViewWrapper.setAlpha(r1);
            return;
        L37:
            return;
        L35:
            return;
        L34:
            return;
        }

        @Override // android.view.View
        public void setVisibility(int r1) {
            super.setVisibility(r1);
            ChatAttachAlert.m9520$$Nest$mcheckUi_fadeTopAlpha(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$8, reason: invalid class name */
    public class AnonymousClass8 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ChatAttachAlert this$0;

        public AnonymousClass8(ChatAttachAlert r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r3) {
            ChatAttachAlert r0 = this.this$0;
            if (r3 == (-1)) goto L5;
            ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r0).onMenuItemClick(r3);
            return;
        L5:
            if (ChatAttachAlert.m9451$$Nest$fgetcurrentAttachLayout(r0).onBackPressed() == false) goto L7;
            return;
        L7:
            this.this$0.dismiss();
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlert$9, reason: invalid class name */
    public class AnonymousClass9 extends TextView {
        Paint p;
        final /* synthetic */ ChatAttachAlert this$0;

        public AnonymousClass9(ChatAttachAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.p = new Paint(1);
        }

        @Override // android.widget.TextView, android.view.View
        public void onDraw(Canvas r13) {
            this.p.setColor(ChatAttachAlert.access$7400(this.this$0, Theme.key_featuredStickers_addButton));
            r13.drawRoundRect(0.0f, (getHeight() / 2.0f) - AndroidUtilities.dp(14.0f), getWidth(), (getHeight() / 2.0f) + AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f), this.p);
            super.onDraw(r13);
        }
    }

    /* loaded from: classes3.dex */
    public static class AttachAlertLayout extends FrameLayout {
        protected IBlur3Capture iBlur3Capture;
        protected View iBlur3CaptureView;
        protected int listPaddingBottom;
        protected boolean occupyNavigationBar;
        protected boolean occupyStatusBar;
        protected ChatAttachAlert parentAlert;
        protected final Theme.ResourcesProvider resourcesProvider;

        public AttachAlertLayout(ChatAttachAlert r1, Context r2, Theme.ResourcesProvider r3) {
            super(r2);
            this.resourcesProvider = r3;
            this.parentAlert = r1;
        }

        public void applyCaption(CharSequence r1) {
        }

        public boolean canDismissWithTouchOutside() {
            return true;
        }

        public boolean canScheduleMessages() {
            return true;
        }

        public void checkColors() {
        }

        public boolean disableBottomFade() {
            return false;
        }

        public int getButtonsHideOffset() {
            if (needsActionBar() == 0) goto L5;
            float r0 = 12.0f;
        L7:
            return AndroidUtilities.dp(r0);
        L5:
            r0 = 17.0f;
            goto L7
        }

        public int getCurrentItemTop() {
            return 0;
        }

        public int getCustomActionBarBackground() {
            return 0;
        }

        public int getCustomBackground() {
            return 0;
        }

        public int getFirstOffset() {
            return 0;
        }

        public IBlur3Capture getIBlur3Capture() {
            return null;
        }

        public int getListTopPadding() {
            return 0;
        }

        public int getSelectedItemsCount() {
            return 0;
        }

        public ArrayList<ThemeDescription> getThemeDescriptions() {
            return null;
        }

        public int getThemedColor(int r1) {
            return Theme.getColor(r1, this.resourcesProvider);
        }

        public boolean hasCustomActionBarBackground() {
            return false;
        }

        public boolean hasCustomBackground() {
            return false;
        }

        public boolean isDoneItemEnabled() {
            return false;
        }

        public int needsActionBar() {
            return 0;
        }

        public boolean onBackPressed() {
            return false;
        }

        public void onButtonsTranslationYUpdated() {
        }

        public void onContainerTranslationUpdated(float r1) {
        }

        public boolean onContainerViewTouchEvent(MotionEvent r1) {
            return false;
        }

        public void onDestroy() {
        }

        public boolean onDismiss() {
            return false;
        }

        public void onDismissWithButtonClick(int r1) {
        }

        public boolean onDismissWithTouchOutside() {
            return true;
        }

        public void onHidden() {
        }

        public void onHide() {
        }

        public void onHideShowProgress(float r1) {
        }

        public void onMenuItemClick(int r1) {
        }

        public void onOpenAnimationEnd() {
        }

        public void onPanTransitionEnd() {
        }

        public void onPanTransitionStart(boolean r1, int r2) {
        }

        public void onPause() {
        }

        public void onPreMeasure(int r1, int r2) {
        }

        public void onResume() {
        }

        public void onSelectedItemsCountChanged(int r1) {
        }

        public boolean onSheetKeyDown(int r1, KeyEvent r2) {
            return false;
        }

        public void onShow(AttachAlertLayout r1) {
        }

        public void onShown() {
        }

        public void scrollToTop() {
        }

        public boolean sendSelectedItems(boolean r1, int r2, int r3, long r4, boolean r6) {
            return false;
        }

        public boolean shouldHideBottomButtons() {
            return true;
        }
    }

    public class AttachBotButton extends AttachButtonBase {
        private TLRPC.TL_attachMenuBot attachMenuBot;
        private TLRPC.User currentUser;
        final /* synthetic */ ChatAttachAlert this$0;

        /* renamed from: $r8$lambda$-Q4NR2NsLg9EoXirNGvADBmdOEU, reason: not valid java name */
        public static /* synthetic */ void m9541$r8$lambda$Q4NR2NsLg9EoXirNGvADBmdOEU(ImageReceiver r0, boolean r1, boolean r2, boolean r3) {
            Drawable r02 = r0.getDrawable();
            if ((r02 instanceof RLottieDrawable) == false) goto L6;
            RLottieDrawable r03 = (RLottieDrawable) r02;
            r03.setCustomEndFrame(0);
            r03.stop();
            r03.setProgress(0.0f, false);
            return;
        }

        /* renamed from: -$$Nest$fgetattachMenuBot, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.TL_attachMenuBot m9542$$Nest$fgetattachMenuBot(AttachBotButton r0) {
            return r0.attachMenuBot;
        }

        /* renamed from: -$$Nest$fgetcurrentUser, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.User m9543$$Nest$fgetcurrentUser(AttachBotButton r0) {
            return r0.currentUser;
        }

        public AttachBotButton(ChatAttachAlert r2, Context r3) {
            this.this$0 = r2;
            super(r3);
            setWillNotDraw(false);
            setFocusable(true);
            setFocusableInTouchMode(true);
            GlassTabView r22 = GlassTabView.createAttachBotTab(r3, ChatAttachAlert.access$700(r2));
            this.glassTabView = r22;
            r22.getBackupImageView().imageReceiver.setDelegate(new ChatAttachAlert$AttachBotButton$$ExternalSyntheticLambda0());
            addView(this.glassTabView, LayoutHelper.createFrame(-1, -1.0f));
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            updateCheckedState(false);
        }

        public void setAttachBot(TLRPC.User r3, TLRPC.TL_attachMenuBot r4) {
            if (r3 == null) goto L7;
            if (r4 == null) goto L8;
            this.glassTabView.setAttachBot(r3, r4, this.this$0.currentAccount);
            this.currentUser = r3;
            this.attachMenuBot = r4;
            this.glassTabView.setSelected(false, false);
            invalidate();
            return;
        L8:
            return;
        }

        public void setUser(TLRPC.User r3) {
            if (r3 != null) goto L4;
            return;
        L4:
            this.glassTabView.setAttachBotUser(r3, this.this$0.currentAccount);
            this.currentUser = r3;
            this.attachMenuBot = null;
            this.glassTabView.setSelected(false, false);
            invalidate();
        }

        public void updateCheckedState(boolean r7) {
            if (this.attachMenuBot != null) goto L5;
        L7:
            boolean r0 = false;
        L8:
            this.glassTabView.setSelected(r0, r7);
            RLottieDrawable r6 = this.glassTabView.getBackupImageView().getImageReceiver().getLottieAnimation();
            if (r7 == false) goto L14;
            if (r0 == false) goto L17;
            if (r6 == null) goto L18;
            r6.setAutoRepeat(0);
            r6.setCustomEndFrame(-1);
            r6.setProgress(0.0f, false);
            r6.start();
            return;
        L18:
            return;
        L17:
            return;
        L14:
            if (r6 == null) goto L19;
            r6.stop();
            r6.setProgress(0.0f, false);
            return;
        L19:
            return;
        L5:
            if ((-this.currentUser.id) != ChatAttachAlert.m9486$$Nest$fgetselectedId(this.this$0)) goto L7;
            r0 = true;
            goto L8
        }
    }

    public class AttachButton extends AttachButtonBase {
        private int currentId;
        final /* synthetic */ ChatAttachAlert this$0;

        public AttachButton(ChatAttachAlert r2, Context r3) {
            this.this$0 = r2;
            super(r3);
            setWillNotDraw(false);
            setFocusable(true);
            GlassTabView r22 = GlassTabView.createAttachTab(r3, ChatAttachAlert.access$600(r2));
            this.glassTabView = r22;
            addView(r22, LayoutHelper.createFrame(-1, -1.0f));
        }

        @Override // android.view.View
        public boolean hasOverlappingRendering() {
            return false;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            updateCheckedState(false);
        }

        public void setTextAndIcon(int r2, CharSequence r3, GlassTabView.TabAnimation r4) {
            this.glassTabView.setText(r3);
            this.glassTabView.setTabAnimation(r4);
            this.currentId = r2;
        }

        public void updateCheckedState(boolean r5) {
            if (this.currentId != ChatAttachAlert.m9486$$Nest$fgetselectedId(this.this$0)) goto L5;
            boolean r0 = true;
        L6:
            this.glassTabView.setSelected(r0, r5);
            return;
        L5:
            r0 = false;
            goto L6
        }
    }

    public static abstract class AttachButtonBase extends FrameLayout {
        protected GlassTabView glassTabView;

        public AttachButtonBase(Context r1) {
            super(r1);
        }
    }

    @SuppressLint({"ViewConstructor"})
    public static class AttachSearchField extends FragmentSearchField {
        private final ChatAttachAlert parentAlert;

        public AttachSearchField(Context r1, ChatAttachAlert r2, Theme.ResourcesProvider r3) {
            super(r1, r3);
            this.parentAlert = r2;
        }

        @Override // org.telegram.ui.Components.FragmentSearchField, org.telegram.ui.ActionBar.Theme.Colorable
        public /* bridge */ /* synthetic */ int[] getColorKeys() {
            return super.getColorKeys();
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r4) {
            this.parentAlert.makeFocusable(this.editText, true);
            return super.onInterceptTouchEvent(r4);
        }
    }

    public class ButtonsAdapter extends RecyclerListView.SelectionAdapter {
        private int attachBotsEndRow;
        private int attachBotsStartRow;
        private List<TLRPC.TL_attachMenuBot> attachMenuBots;
        private int buttonsCount;
        private int contactButton;
        private int documentButton;
        private int emojiButton;
        private int galleryButton;
        private int linksButton;
        private int locationButton;
        private Context mContext;
        private int musicButton;
        private int pollButton;
        private int quickRepliesButton;
        private int richButton;
        private int stickerButton;
        final /* synthetic */ ChatAttachAlert this$0;
        private int todoButton;

        public ButtonsAdapter(ChatAttachAlert r1, Context r2) {
            this.this$0 = r1;
            this.attachMenuBots = new ArrayList();
            this.mContext = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            int r0 = this.buttonsCount;
            ChatAttachAlert r2 = this.this$0;
            if (r2.editingMessageObject == null) goto L5;
            return r0;
        L5:
            if ((r2.baseFragment instanceof ChatActivity) == true) goto L7;
            return r0;
        L7:
            if (r2.isPollAttach == false) goto L9;
            return r0;
        L9:
            return r0 + MediaDataController.getInstance(r2.currentAccount).inlineBots.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (r3 < this.buttonsCount) goto L5;
            return 1;
        L5:
            if (r3 >= this.attachBotsStartRow) goto L7;
            return 0;
        L7:
            if (r3 >= this.attachBotsEndRow) goto L12;
            return 1;
        L12:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            int r0 = 0;
            this.buttonsCount = 0;
            this.galleryButton = -1;
            this.documentButton = -1;
            this.musicButton = -1;
            this.pollButton = -1;
            this.todoButton = -1;
            this.contactButton = -1;
            this.quickRepliesButton = -1;
            this.locationButton = -1;
            this.stickerButton = -1;
            this.linksButton = -1;
            this.richButton = -1;
            this.emojiButton = -1;
            this.attachBotsStartRow = -1;
            this.attachBotsEndRow = -1;
            ChatAttachAlert r2 = this.this$0;
            boolean r4 = true;
            if (r2.isPollAttach == false) goto L34;
            this.buttonsCount = 1;
            this.galleryButton = 0;
            if (ChatAttachAlert.m9479$$Nest$fgetpollAllowedLayouts(r2) != 0) goto L7;
        L8:
            int r02 = this.buttonsCount;
            this.buttonsCount = r02 + 1;
            this.documentButton = r02;
        L10:
            if (ChatAttachAlert.m9479$$Nest$fgetpollAllowedLayouts(this.this$0) != 0) goto L12;
        L13:
            int r03 = this.buttonsCount;
            this.buttonsCount = r03 + 1;
            this.stickerButton = r03;
        L15:
            if (ChatAttachAlert.m9479$$Nest$fgetpollAllowedLayouts(this.this$0) != 0) goto L17;
        L18:
            int r04 = this.buttonsCount;
            this.buttonsCount = r04 + 1;
            this.emojiButton = r04;
        L20:
            if (ChatAttachAlert.m9479$$Nest$fgetpollAllowedLayouts(this.this$0) != 0) goto L22;
        L23:
            int r05 = this.buttonsCount;
            this.buttonsCount = r05 + 1;
            this.musicButton = r05;
        L25:
            if (ChatAttachAlert.m9479$$Nest$fgetpollAllowedLayouts(this.this$0) != 0) goto L27;
        L28:
            int r06 = this.buttonsCount;
            this.buttonsCount = r06 + 1;
            this.locationButton = r06;
        L30:
            if (ChatAttachAlert.m9479$$Nest$fgetpollAllowedLayouts(this.this$0) != 0) goto L32;
        L33:
            int r07 = this.buttonsCount;
            this.buttonsCount = r07 + 1;
            this.linksButton = r07;
        L122:
            super.notifyDataSetChanged();
            return;
        L32:
            if (BitwiseUtils.hasFlag(ChatAttachAlert.m9479$$Nest$fgetpollAllowedLayouts(this.this$0), 32768) == false) goto L122;
        L27:
            if (BitwiseUtils.hasFlag(ChatAttachAlert.m9479$$Nest$fgetpollAllowedLayouts(this.this$0), 64) == false) goto L30;
        L22:
            if (BitwiseUtils.hasFlag(ChatAttachAlert.m9479$$Nest$fgetpollAllowedLayouts(this.this$0), 8) == false) goto L25;
        L17:
            if (BitwiseUtils.hasFlag(ChatAttachAlert.m9479$$Nest$fgetpollAllowedLayouts(this.this$0), 16384) == false) goto L20;
        L12:
            if (BitwiseUtils.hasFlag(ChatAttachAlert.m9479$$Nest$fgetpollAllowedLayouts(this.this$0), 8192) == false) goto L15;
        L7:
            if (BitwiseUtils.hasFlag(ChatAttachAlert.m9479$$Nest$fgetpollAllowedLayouts(this.this$0), 16) == false) goto L10;
        L34:
            BaseFragment r3 = r2.baseFragment;
            if ((r3 instanceof ChatActivity) == true) goto L40;
            this.galleryButton = 0;
            int r08 = 1 + 1;
            this.buttonsCount = r08;
            this.documentButton = 1;
            if (r2.allowEnterCaption == false) goto L122;
            this.buttonsCount = r08 + 1;
            this.musicButton = r08;
            goto L122
        L40:
            if (r2.editingMessageObject != null) goto L42;
            TLRPC.Chat r22 = null;
            if ((r3 instanceof ChatActivity) == false) goto L56;
            TLRPC.User r1 = ((ChatActivity) r3).getCurrentUser();
        L57:
            BaseFragment r32 = this.this$0.baseFragment;
            if ((r32 instanceof ChatActivity) == false) goto L60;
            r22 = ((ChatActivity) r32).getCurrentChat();
        L60:
            if (r1 != null) goto L62;
        L64:
            r4 = false;
        L65:
            int r33 = this.buttonsCount;
            this.buttonsCount = r33 + 1;
            this.galleryButton = r33;
            if (ChatAttachAlert.m9477$$Nest$fgetphotosEnabled(this.this$0) == false) goto L68;
        L69:
            if (r4 == true) goto L93;
            if (r22 != null) goto L72;
        L73:
            BaseFragment r23 = this.this$0.baseFragment;
            if ((r23 instanceof ChatActivity) == false) goto L93;
            if (((ChatActivity) r23).isInScheduleMode() == true) goto L93;
            if (((ChatActivity) this.this$0.baseFragment).isSecretChat() == true) goto L93;
            if (((ChatActivity) this.this$0.baseFragment).getChatMode() == 5) goto L93;
            ChatActivity r24 = (ChatActivity) this.this$0.baseFragment;
            this.attachBotsStartRow = this.buttonsCount;
            this.attachMenuBots.clear();
            ArrayList<TLRPC.TL_attachMenuBot> r34 = MediaDataController.getInstance(this.this$0.currentAccount).getAttachMenuBots().bots;
            int r5 = r34.size();
        L82:
            if (r0 >= r5) goto L92;
            TLRPC.TL_attachMenuBot r6 = r34.get(r0);
            r0 = r0 + 1;
            TLRPC.TL_attachMenuBot r62 = r6;
            if (r62.show_in_attach_menu == false) goto L82;
            if (r24.getCurrentChat() == null) goto L88;
            TLObject r7 = r24.getCurrentChat();
        L90:
            if (MediaDataController.canShowAttachMenuBot(r62, r7) == false) goto L82;
            this.attachMenuBots.add(r62);
            goto L82
        L88:
            r7 = r24.getCurrentUser();
            goto L90
        L92:
            int r09 = this.buttonsCount + this.attachMenuBots.size();
            this.buttonsCount = r09;
            this.attachBotsEndRow = r09;
            goto L93
        L72:
            if (ChatObject.isMonoForum(r22) == false) goto L73;
        L93:
            int r010 = this.buttonsCount;
            this.buttonsCount = r010 + 1;
            this.documentButton = r010;
            if (ChatAttachAlert.m9478$$Nest$fgetplainTextEnabled(this.this$0) == false) goto L97;
            int r011 = this.buttonsCount;
            this.buttonsCount = r011 + 1;
            this.locationButton = r011;
        L97:
            if (ChatAttachAlert.m9478$$Nest$fgetplainTextEnabled(this.this$0) == false) goto L102;
            if (MessagesController.getInstance(this.this$0.currentAccount).richEditorAvailable() == false) goto L102;
            int r012 = this.buttonsCount;
            this.buttonsCount = r012 + 1;
            this.richButton = r012;
        L102:
            if (ChatAttachAlert.m9481$$Nest$fgetpollsEnabled(this.this$0) == false) goto L105;
            int r013 = this.buttonsCount;
            this.buttonsCount = r013 + 1;
            this.pollButton = r013;
        L105:
            if (ChatAttachAlert.m9490$$Nest$fgettodoEnabled(this.this$0) == false) goto L108;
            int r014 = this.buttonsCount;
            this.buttonsCount = r014 + 1;
            this.todoButton = r014;
        L108:
            if (ChatAttachAlert.m9478$$Nest$fgetplainTextEnabled(this.this$0) == false) goto L110;
            int r015 = this.buttonsCount;
            this.buttonsCount = r015 + 1;
            this.contactButton = r015;
        L110:
            BaseFragment r016 = this.this$0.baseFragment;
            if ((r016 instanceof ChatActivity) == true) goto L113;
        L121:
            int r017 = this.buttonsCount;
            this.buttonsCount = r017 + 1;
            this.musicButton = r017;
            goto L122
        L113:
            if (((ChatActivity) r016).getChatMode() != 0) goto L121;
            if (r1 == null) goto L121;
            if (r4 == true) goto L121;
            if (r1.bot == true) goto L121;
            if (QuickRepliesController.getInstance(this.this$0.currentAccount).hasReplies() == false) goto L121;
            int r018 = this.buttonsCount;
            this.buttonsCount = r018 + 1;
            this.quickRepliesButton = r018;
            goto L121
        L68:
            if (ChatAttachAlert.m9494$$Nest$fgetvideosEnabled(this.this$0) == false) goto L93;
        L62:
            if (((ChatActivity) this.this$0.baseFragment).getMessagesController().getSendPaidMessagesStars(r1.id) <= 0) goto L64;
        L56:
            r1 = null;
            goto L57
        L42:
            if (ChatAttachAlert.m9455$$Nest$fgeteditType(r2) != (-1)) goto L45;
            int r019 = this.buttonsCount;
            this.galleryButton = r019;
            this.documentButton = r019 + 1;
            this.buttonsCount = r019 + 3;
            this.musicButton = r019 + 2;
            goto L122
        L45:
            if (ChatAttachAlert.m9455$$Nest$fgeteditType(this.this$0) != 0) goto L48;
            int r020 = this.buttonsCount;
            this.buttonsCount = r020 + 1;
            this.galleryButton = r020;
        L48:
            if (ChatAttachAlert.m9455$$Nest$fgeteditType(this.this$0) != 1) goto L51;
            int r021 = this.buttonsCount;
            this.buttonsCount = r021 + 1;
            this.documentButton = r021;
        L51:
            if (ChatAttachAlert.m9455$$Nest$fgeteditType(this.this$0) != 2) goto L122;
            int r022 = this.buttonsCount;
            this.buttonsCount = r022 + 1;
            this.musicButton = r022;
            goto L122
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r6, int r7) {
            int r0 = r6.getItemViewType();
            boolean r1 = true;
            if (r0 == 0) goto L14;
            if (r0 == 1) goto L6;
            return;
        L6:
            AttachBotButton r62 = (AttachBotButton) r6.itemView;
            r62.glassTabView.onPreBind();
            int r02 = this.attachBotsStartRow;
            if (r7 >= r02) goto L9;
        L12:
            int r72 = r7 - this.buttonsCount;
            r62.setTag(Integer.valueOf(r72));
            r62.setUser(MessagesController.getInstance(this.this$0.currentAccount).getUser(Long.valueOf(MediaDataController.getInstance(this.this$0.currentAccount).inlineBots.get(r72).peer.user_id)));
            return;
        L9:
            if (r7 >= this.attachBotsEndRow) goto L12;
            int r73 = r7 - r02;
            r62.setTag(Integer.valueOf(r73));
            TLRPC.TL_attachMenuBot r74 = this.attachMenuBots.get(r73);
            r62.setAttachBot(MessagesController.getInstance(this.this$0.currentAccount).getUser(Long.valueOf(r74.bot_id)), r74);
            return;
        L14:
            AttachButton r63 = (AttachButton) r6.itemView;
            r63.glassTabView.onPreBind();
            if (r7 != this.galleryButton) goto L20;
            r63.setTextAndIcon(1, LocaleController.getString(R.string.ChatGallery), GlassTabView.TabAnimation.GALLERY);
            r63.setTag(1);
            boolean r75 = ChatAttachAlert.m9535$$Nest$smcheckPhotoAndCameraPermission(this.mContext);
        L17:
            boolean r76 = !r75;
        L18:
            boolean r03 = false;
        L60:
            GlassTabView r3 = r63.glassTabView;
            if (r76 == false) goto L63;
            String r4 = "!";
        L64:
            r3.setCounter(r4, r76, false);
            GlassTabView r64 = r63.glassTabView;
            if (r03 == true) goto L67;
        L69:
            r1 = false;
        L70:
            r64.setPremiumBadge(r1);
            return;
        L67:
            if (UserConfig.getInstance(this.this$0.currentAccount).isPremium() == true) goto L69;
        L63:
            r4 = null;
            goto L64
        L20:
            if (r7 != this.documentButton) goto L23;
            r63.setTextAndIcon(4, LocaleController.getString(R.string.ChatDocument), GlassTabView.TabAnimation.FILES);
            r63.setTag(4);
            r75 = ChatAttachAlert.m9536$$Nest$smcheckPhotoAndDocumentsPermission(this.mContext);
            goto L17
        L23:
            if (r7 != this.locationButton) goto L26;
            r63.setTextAndIcon(6, LocaleController.getString(R.string.ChatLocation), GlassTabView.TabAnimation.LOCATION);
            r63.setTag(6);
        L59:
            r76 = false;
            r03 = false;
            goto L60
        L26:
            if (r7 != this.musicButton) goto L29;
            r63.setTextAndIcon(3, LocaleController.getString(R.string.AttachMusic), GlassTabView.TabAnimation.MUSIC);
            r63.setTag(3);
            r75 = ChatAttachAlert.m9534$$Nest$smcheckMusicPermission(this.mContext);
            goto L17
        L29:
            if (r7 != this.pollButton) goto L32;
            r63.setTextAndIcon(9, LocaleController.getString(R.string.Poll), GlassTabView.TabAnimation.POLL);
            r63.setTag(9);
            goto L59
        L32:
            if (r7 != this.contactButton) goto L40;
            r63.setTextAndIcon(5, LocaleController.getString(R.string.AttachContact), GlassTabView.TabAnimation.CONTACTS);
            r63.setTag(5);
            if (UserConfig.getInstance(this.this$0.currentAccount).syncContacts == true) goto L36;
        L38:
            r76 = false;
            goto L18
        L36:
            if (ChatAttachAlert.m9533$$Nest$smcheckContactsPermission(this.mContext) == true) goto L38;
            r76 = true;
            goto L18
        L40:
            if (r7 != this.quickRepliesButton) goto L45;
            r63.setTextAndIcon(11, LocaleController.getString(R.string.AttachQuickReplies), GlassTabView.TabAnimation.REPLIES);
            r63.setTag(11);
        L42:
            r03 = true;
        L43:
            r76 = false;
            goto L60
        L45:
            if (r7 != this.todoButton) goto L48;
            r63.setTextAndIcon(12, LocaleController.getString(R.string.Todo), GlassTabView.TabAnimation.CHECKLIST);
            r63.setTag(12);
            goto L42
        L48:
            if (r7 != this.stickerButton) goto L51;
            r63.setTextAndIcon(13, LocaleController.getString(R.string.ChatSticker), GlassTabView.TabAnimation.STICKER);
            r63.setTag(13);
            goto L59
        L51:
            if (r7 != this.linksButton) goto L54;
            r63.setTextAndIcon(15, LocaleController.getString(R.string.ChatLink), GlassTabView.TabAnimation.LINK);
            r63.setTag(15);
            goto L59
        L54:
            if (r7 != this.emojiButton) goto L57;
            r63.setTextAndIcon(14, LocaleController.getString(R.string.ChatEmoji), GlassTabView.TabAnimation.EMOJI);
            r63.setTag(14);
            goto L59
        L57:
            if (r7 != this.richButton) goto L59;
            r63.setTextAndIcon(16, LocaleController.getString(R.string.AttachArticle), GlassTabView.TabAnimation.ARTICLE);
            r63.setTag(16);
            r03 = !MessagesController.getInstance(this.this$0.currentAccount).storyEntitiesAllowed();
            goto L43
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            if (r3 == 0) goto L4;
            View r22 = new AttachBotButton(this.this$0, this.mContext);
        L5:
            r22.setImportantForAccessibility(1);
            r22.setFocusable(true);
            r22.setLayoutParams(new RecyclerView.LayoutParams(-2, -1));
            return new RecyclerListView.Holder(r22);
        L4:
            r22 = new AttachButton(this.this$0, this.mContext);
            goto L5
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder r1) {
            ChatAttachAlert.m9518$$Nest$mapplyAttachButtonColors(this.this$0, r1.itemView);
        }
    }

    public interface ChatAttachViewDelegate {
        void didPressedButton(int r1, boolean r2, boolean r3, int r4, int r5, long r6, boolean r8, boolean r9, long r10);

        default void didSelectBot(TLRPC.User r1) {
        }

        default void doOnIdle(Runnable r1) {
            r1.run();
        }

        default boolean needEnterComment() {
            return false;
        }

        default void onCameraOpened() {
        }

        default void onWallpaperSelected(Object r1) {
        }

        default void openAvatarsSearch() {
        }

        default boolean selectItemOnClicking() {
            return false;
        }

        default void sendAudio(ArrayList<MessageObject> r1, CharSequence r2, boolean r3, int r4, int r5, long r6, boolean r8, long r9) {
        }
    }

    @SuppressLint({"ViewConstructor"})
    public static class SearchFadeView extends View {
        private final int bgKeyColor;
        private final GradientProtectionDrawable gradientProtectionDrawable;
        private final GradientProtectionDrawable gradientProtectionDrawable2;
        private final Theme.ResourcesProvider resourcesProvider;

        public SearchFadeView(Context r2, int r3, Theme.ResourcesProvider r4) {
            super(r2);
            this.gradientProtectionDrawable = new GradientProtectionDrawable(2);
            this.gradientProtectionDrawable2 = new GradientProtectionDrawable(2);
            this.resourcesProvider = r4;
            this.bgKeyColor = r3;
        }

        @Override // android.view.View
        public void onDraw(Canvas r4) {
            this.gradientProtectionDrawable.setColor(Theme.multAlpha(Theme.getColor(this.bgKeyColor, this.resourcesProvider), 0.5f));
            this.gradientProtectionDrawable.draw(r4);
            this.gradientProtectionDrawable2.setColor(Theme.multAlpha(Theme.getColor(this.bgKeyColor, this.resourcesProvider), 0.95f));
            this.gradientProtectionDrawable2.draw(r4);
        }

        @Override // android.view.View
        public void onSizeChanged(int r2, int r3, int r4, int r5) {
            super.onSizeChanged(r2, r3, r4, r5);
            int r32 = AndroidUtilities.statusBarHeight;
            this.gradientProtectionDrawable.setInsets(0, AndroidUtilities.dp(12.0f) + r32, 0, 0);
            this.gradientProtectionDrawable.setBounds(0, 0, r2, AndroidUtilities.dp(52.0f) + r32);
            this.gradientProtectionDrawable2.setInsets(0, r32 / 3, 0, 0);
            this.gradientProtectionDrawable2.setBounds(0, 0, r2, r32);
        }
    }

    /* renamed from: $r8$lambda$-aVVmQ7zLs8dGUZhlIYCazsRcoo, reason: not valid java name */
    public static /* synthetic */ void m9402$r8$lambda$aVVmQ7zLs8dGUZhlIYCazsRcoo(ChatAttachAlert r0, ValueAnimator r1) {
        r0.lambda$onCustomOpenAnimation$65(r1);
    }

    /* renamed from: $r8$lambda$-n75Y4U_pr8aUtMF0G6xxpZV1Co, reason: not valid java name */
    public static /* synthetic */ void m9403$r8$lambda$n75Y4U_pr8aUtMF0G6xxpZV1Co(ChatAttachAlert r0, View r1) {
        r0.lambda$new$3(r1);
    }

    /* renamed from: $r8$lambda$-y0lSP2YT6_ry5UbSCNlK58q1i0, reason: not valid java name */
    public static /* synthetic */ void m9404$r8$lambda$y0lSP2YT6_ry5UbSCNlK58q1i0(ChatAttachAlert r0, boolean r1, boolean r2) {
        r0.lambda$setCaptionAbove$81(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$01jUseOMYEYYMqQ8zbUMn4_Mrcw(ChatAttachAlert r0, AlertDialog r1, int r2) {
        r0.lambda$dismiss$74(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$0KTJgeGtTCn7MHVv0qJD8u9ogec(ChatAttachAlert r0, DialogInterface r1) {
        r0.lambda$new$30(r1);
    }

    public static /* synthetic */ void $r8$lambda$0MSASC_WPe6xEcn9SdOzKQl_dlo(ChatAttachAlert r0, View r1) {
        r0.lambda$new$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$0QX8RxzstuQG75e4ghOhCZo7JTs(ChatAttachAlert r0) {
        r0.lambda$setCaptionAbove$82();
    }

    /* renamed from: $r8$lambda$2eAXZ250gcRg_QcedcnwGX-7KK8, reason: not valid java name */
    public static /* synthetic */ void m9405$r8$lambda$2eAXZ250gcRg_QcedcnwGX7KK8(ChatAttachAlert r0, View r1) {
        r0.lambda$new$28(r1);
    }

    public static /* synthetic */ void $r8$lambda$2wZkyGGZHQJqp3Dr0BO0pIbVeHA(ChatAttachAlert r0) {
        r0.lambda$new$38();
    }

    public static /* synthetic */ int $r8$lambda$3Q9ePS3mopTZdteJpt0ECc2YABk(ChatAttachAlert r0, Theme.ResourcesProvider r1, boolean r2) {
        return r0.lambda$new$44(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$4ZDllJ3EvyGzrwRtRd6goOtUKNk(ChatAttachAlert r0, TLRPC.MessageMedia r1, int r2, boolean r3, int r4, long r5) {
        r0.lambda$openAttachLayoutForType$53(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$4dwdfpYK2qHhqKsoKtjrdaYrBzo(ChatAttachAlert r0, long r1, ChatActivity r3, Theme.ResourcesProvider r4) {
        r0.lambda$new$37(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$52wGzGcWTH4fIxiYuKoTpoFzjag(ChatAttachAlert r0, DialogInterface r1) {
        r0.lambda$dismiss$75(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$5oybhBc0BwH0UGhAajC9NwhyUuI(ChatAttachAlert r0, Context r1, Theme.ResourcesProvider r2, BaseFragment r3, View r4) {
        return r0.lambda$new$42(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$7QNaN_6mpF5MQMH6A3CniuSLvCU(ChatAttachAlert r0, ValueAnimator r1) {
        r0.lambda$onCustomOpenAnimation$62(r1);
    }

    public static /* synthetic */ void $r8$lambda$7SQibuwtFe5EZWzHv7MSyk5pjg0(ChatAttachAlert r0, EditTextBoldCursor r1, boolean r2) {
        r0.lambda$makeFocusable$67(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$8sYN7GW_SsepY42mGsiBPyqbBic(ChatAttachAlert r0, ArrayList r1, HashMap r2) {
        r0.lambda$new$39(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$951lLyzH1kuqRnkXoJk3xhIZ1h0(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$9udQg1qdbffgsh0Vcg7EAOtPYaU(ChatAttachAlert r0, AttachBotButton r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$new$12(r1, r2, r3);
    }

    public static /* synthetic */ int $r8$lambda$AGNj3YsH9u1fKyFgYZHyRNBmRLY(ChatAttachAlert r0, Theme.ResourcesProvider r1, boolean r2) {
        return r0.lambda$new$45(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$CgCTOw8RH1wbNKVetKe9RtSXUMY(ChatAttachAlert r0, boolean r1, int r2, int r3) {
        r0.lambda$onWriteButtonPressed$47(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$DPIfBKakVNmX_N7Tgb1mtRMP0qM(ChatAttachAlert r0, Theme.ResourcesProvider r1, View r2) {
        r0.lambda$new$23(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$DPsSFN5G1JsZCLBFs5pRvPbYlY8(ChatAttachAlert r0, Theme.ResourcesProvider r1, View r2, int r3) {
        r0.lambda$new$14(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$E4C3zXVo9wJNhG27d8xWMoj3Lvk(ChatAttachAlert r0, Object r1) {
        r0.lambda$openColorsLayout$60(r1);
    }

    public static /* synthetic */ void $r8$lambda$FOor2pT0TUmgdhYUaRg4n11AOKY(ChatAttachAlert r0, View r1) {
        r0.lambda$new$16(r1);
    }

    /* renamed from: $r8$lambda$Fyl3tzJT-ZJLarMdc-JYknCs3q0, reason: not valid java name */
    public static /* synthetic */ void m9406$r8$lambda$Fyl3tzJTZJLarMdcJYknCs3q0(ChatAttachAlert r0, long r1, Theme.ResourcesProvider r3) {
        r0.lambda$new$35(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$GEIcnZiSW8eD6N74hz8GqVDCXEQ(ChatAttachAlert r0, CharSequence r1) {
        r0.lambda$new$21(r1);
    }

    public static /* synthetic */ void $r8$lambda$JWVkjI89wuxREuMzyP1gcyA2fZs(ChatAttachAlert r0, ValueAnimator r1) {
        r0.lambda$setCaptionAbove$78(r1);
    }

    public static /* synthetic */ void $r8$lambda$KbuSZCZoU92gKchHbHCFzYRyYbg(ChatAttachAlert r0, boolean r1, boolean r2) {
        r0.lambda$setCaptionAbove$79(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$N1p4k_LquET8gLIGdxuP1iwIots(ChatAttachAlert r0, int r1) {
        r0.lambda$dismiss$77(r1);
    }

    public static /* synthetic */ void $r8$lambda$NOl5aeiitRWTMs1DkKcySRSyO6U(ChatAttachAlert r0, TLRPC.MessageMedia r1, int r2, boolean r3, int r4, long r5) {
        r0.lambda$init$70(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$OA9DVc-pRP2yyWtqXEpPZMZBXAo, reason: not valid java name */
    public static /* synthetic */ void m9407$r8$lambda$OA9DVcpRP2yyWtqXEpPZMZBXAo(ChatAttachAlert r0, ReplaceAnimator r1) {
        r0.onCurrentLayoutAnimatorChanged(r1);
    }

    public static /* synthetic */ void $r8$lambda$OsJEjSnFLabvjGvl_yIg4WE9q5o(ChatAttachAlert r0, boolean r1, int r2, int r3, long r4, boolean r6, Long r7) {
        r0.lambda$sendPressed$52(r1, r2, r3, r4, r6, r7);
    }

    /* renamed from: $r8$lambda$QCFlU0i759V1IYVAdy5-7aDZDfA, reason: not valid java name */
    public static /* synthetic */ void m9408$r8$lambda$QCFlU0i759V1IYVAdy57aDZDfA(EditTextBoldCursor r0) {
        AndroidUtilities.showKeyboard(r0);
    }

    public static /* synthetic */ int $r8$lambda$Q_Q48zKQdI8PqE5HieC7AYI8gI0(ChatAttachAlert r0, Theme.ResourcesProvider r1, boolean r2) {
        return r0.lambda$new$43(r1, r2);
    }

    /* renamed from: $r8$lambda$QeU4qhmIsxgdBdGtLBvHApFe-gY, reason: not valid java name */
    public static /* synthetic */ void m9409$r8$lambda$QeU4qhmIsxgdBdGtLBvHApFegY(ChatAttachAlert r0, TLRPC.MessageMedia r1, int r2, boolean r3, int r4, long r5) {
        r0.lambda$new$9(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$SDF-tfzVtO5X3KWwLw1X9xLMzEo, reason: not valid java name */
    public static /* synthetic */ void m9410$r8$lambda$SDFtfzVtO5X3KWwLw1X9xLMzEo(ChatAttachAlert r0, CharSequence r1, Integer r2, Integer r3, Boolean r4) {
        r0.lambda$new$26(r1, r2, r3, r4);
    }

    public static /* synthetic */ boolean $r8$lambda$Sml907oOJGiBmAUkRUtsQYtJ7tQ(ChatAttachAlert r0, View r1) {
        return r0.lambda$new$18(r1);
    }

    public static /* synthetic */ void $r8$lambda$TfLasa0Dfa7qKAT0jDYbN_Sb5uw(ChatAttachAlert r0, boolean r1) {
        r0.lambda$updateMotionItem$68(r1);
    }

    public static /* synthetic */ void $r8$lambda$UMQfqGUKPlF6S6ZdnGPI2FLnyis(ChatAttachAlert r0, View r1) {
        r0.lambda$new$17(r1);
    }

    public static /* synthetic */ void $r8$lambda$UfT3M_mo7c9DbK78k42R0qeWtkY(ChatAttachAlert r0, boolean r1, Runnable r2, DynamicAnimation r3, boolean r4, float r5, float r6) {
        r0.lambda$showLayout$57(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$V2laMabzuOpMBwXUo2FGNQ2pU7M(ChatAttachAlert r0, AttachBotButton r1) {
        r0.lambda$new$11(r1);
    }

    public static /* synthetic */ void $r8$lambda$VuPDl5KSFC2j1hMwgMXXWEo7NN8(ChatAttachAlert r0, boolean r1) {
        r0.lambda$showAiButton$73(r1);
    }

    /* renamed from: $r8$lambda$WdINAI12Hv7iA-xLcq2UOm_m0AU, reason: not valid java name */
    public static /* synthetic */ void m9411$r8$lambda$WdINAI12Hv7iAxLcq2UOm_m0AU(ChatAttachAlert r0, CharSequence r1) {
        r0.lambda$new$25(r1);
    }

    public static /* synthetic */ void $r8$lambda$XzEPXzKMHgjWTeREYVoHOP07aDE(ChatAttachAlert r0, boolean r1) {
        r0.lambda$setTypeButtonsHidden$71(r1);
    }

    /* renamed from: $r8$lambda$Zh_1zPPO-KHhEDWRfNcKHoksPPM, reason: not valid java name */
    public static /* synthetic */ void m9412$r8$lambda$Zh_1zPPOKHhEDWRfNcKHoksPPM(ChatAttachAlert r0, ValueAnimator r1) {
        r0.lambda$setCaptionAbove$80(r1);
    }

    public static /* synthetic */ void $r8$lambda$_QnnrOOciYJGqgaPBgRpfoK0qPE(ChatAttachAlert r0, BaseFragment r1) {
        r0.lambda$showCaptionLimitBulletin$48(r1);
    }

    public static /* synthetic */ void $r8$lambda$_QzwFG1lCFJYM6aRjp3AlEtBqE0(ChatAttachAlert r0, Theme.ResourcesProvider r1, View r2) {
        r0.lambda$new$6(r1, r2);
    }

    /* renamed from: $r8$lambda$aNu-9lYGVeCHf5k3DViLhkCya7M, reason: not valid java name */
    public static /* synthetic */ void m9413$r8$lambda$aNu9lYGVeCHf5k3DViLhkCya7M(ChatAttachAlert r0, TLRPC.TL_attachMenuBot r1, TLRPC.User r2, AlertDialog r3, int r4) {
        r0.lambda$onLongClickBotButton$51(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$bimGsSZ3_i92nn9Uk9ABvQPKCuQ(ChatAttachAlert r0, AttachAlertLayout r1, Runnable r2) {
        r0.lambda$showLayout$58(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$d8dLHybRxI2iHCWdpZtgAN28Jmk(ChatAttachAlert r0, AnimationNotificationsLocker r1, BottomSheet.BottomSheetDelegateInterface r2) {
        r0.lambda$onCustomOpenAnimation$63(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$d9NrQF3ab2olOR2mEg1WE_trroM(ChatAttachAlert r0, Runnable r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
        r0.lambda$onCustomOpenAnimation$64(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$dW9tzSf0do8Wvlqml1SbKZ7RtGk(ChatAttachAlert r0, HintView2 r1) {
        r0.lambda$showMotionHint$69(r1);
    }

    public static /* synthetic */ void $r8$lambda$dhlSR7Q_Ma0g01qbZVet1fOJfMI(ChatAttachAlert r0, View r1) {
        r0.lambda$new$2(r1);
    }

    /* renamed from: $r8$lambda$gCgDRpoQZ3Jlh-ApgVxGPWPQmRk, reason: not valid java name */
    public static /* synthetic */ void m9414$r8$lambda$gCgDRpoQZ3JlhApgVxGPWPQmRk(ChatAttachAlert r0, View r1) {
        r0.lambda$new$8(r1);
    }

    /* renamed from: $r8$lambda$ggtfeulug-IeRQSEZ7soEE2BIbc, reason: not valid java name */
    public static /* synthetic */ void m9415$r8$lambda$ggtfeulugIeRQSEZ7soEE2BIbc(ChatAttachAlert r0, TLRPC.MessageMedia r1, CharSequence r2, PollAttachedMediaPack r3, ArrayList r4, boolean r5, int r6, long r7) {
        r0.lambda$new$10(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$h4S9YdLyLKX0Ls6iZP3IGvEqjVk(ChatAttachAlert r0, MessageObject r1, MessagePreviewView.ToggleButton r2, View r3) {
        r0.lambda$new$33(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$hPZANvEx7o3i85orLw0efzR7iow(ChatAttachAlert r0, Context r1, ActionBarMenuSubItem r2, Theme.ResourcesProvider r3, View r4) {
        r0.lambda$new$41(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$hYAm0TD1QkDIZJ1_rvpSwTiRX8g(ChatAttachAlert r0) {
        r0.removeFromRoot();
    }

    public static /* synthetic */ void $r8$lambda$hgbHO9a94ExX0Lh6a3LnJNwog34(ChatAttachAlert r0, boolean r1) {
        r0.lambda$showAiButton$72(r1);
    }

    /* renamed from: $r8$lambda$iFfTgQNL-nggeac5VdRovhcerkQ, reason: not valid java name */
    public static /* synthetic */ void m9416$r8$lambda$iFfTgQNLnggeac5VdRovhcerkQ(ChatAttachAlert r0, View r1) {
        r0.lambda$new$4(r1);
    }

    /* renamed from: $r8$lambda$k-IloEqFOYXhkJRnKcYfshxxBTw, reason: not valid java name */
    public static /* synthetic */ void m9417$r8$lambda$kIloEqFOYXhkJRnKcYfshxxBTw(ChatAttachAlert r0, View r1) {
        r0.lambda$new$19(r1);
    }

    public static /* synthetic */ void $r8$lambda$kF8eofh9ncRqb_qccNMGjq3AuI4(ChatAttachAlert r0, TLRPC.TL_attachMenuBot r1) {
        r0.lambda$onLongClickBotButton$49(r1);
    }

    /* renamed from: $r8$lambda$kUHi2Fhap_6ookA-xUJX-Es1fIg, reason: not valid java name */
    public static /* synthetic */ void m9418$r8$lambda$kUHi2Fhap_6ookAxUJXEs1fIg(ChatAttachAlert r0, BaseFragment r1, Theme.ResourcesProvider r2, View r3) {
        r0.lambda$new$32(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$l4XZZ7xMbmp_jnjK6JSy30ZDal4(ChatAttachAlert r0, ValueAnimator r1) {
        r0.lambda$showCommentTextView$61(r1);
    }

    public static /* synthetic */ void $r8$lambda$lc8p1AvOGHqm2S0p5nbeMAAASok(ChatAttachAlert r0, ActionBarMenuSubItem r1, Long r2, Runnable r3) {
        r0.lambda$new$40(r1, r2, r3);
    }

    public static /* synthetic */ int $r8$lambda$mJVw4JwCys8s77Re5eXgVUbv8CM(ChatAttachAlert r0, Theme.ResourcesProvider r1, boolean r2) {
        return r0.lambda$new$46(r1, r2);
    }

    /* renamed from: $r8$lambda$nXQLY6blFfKe7OP3w-u5NFtbNLY, reason: not valid java name */
    public static /* synthetic */ void m9419$r8$lambda$nXQLY6blFfKe7OP3wu5NFtbNLY(ChatAttachAlert r0, TLRPC.MessageMedia r1, CharSequence r2, PollAttachedMediaPack r3, ArrayList r4, boolean r5, int r6, long r7) {
        r0.lambda$showPollLayout$54(r1, r2, r3, r4, r5, r6, r7);
    }

    /* renamed from: $r8$lambda$nr-TlIIx86cihAGfIdN1izczirc, reason: not valid java name */
    public static /* synthetic */ void m9420$r8$lambda$nrTlIIx86cihAGfIdN1izczirc(ChatAttachAlert r0, long r1, boolean r3, int r4, int r5) {
        r0.lambda$new$31(r1, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$oEa6ChF-R-3yvcx7iVHOmhfBxmg, reason: not valid java name */
    public static /* synthetic */ void m9421$r8$lambda$oEa6ChFR3yvcx7iVHOmhfBxmg(ChatAttachAlert r0) {
        r0.lambda$showLayout$55();
    }

    /* renamed from: $r8$lambda$obpS3RbjR-Vyj9ueumH8tQnB3KE, reason: not valid java name */
    public static /* synthetic */ void m9422$r8$lambda$obpS3RbjRVyj9ueumH8tQnB3KE(ChatAttachAlert r0, View r1) {
        r0.lambda$new$24(r1);
    }

    public static /* synthetic */ void $r8$lambda$pjT7_n2qhMFIGYSbI5gDcn8HI9k(ChatAttachAlert r0, Canvas r1, RectF r2) {
        r0.lambda$new$0(r1, r2);
    }

    /* renamed from: $r8$lambda$qvTJl5eE-Yrp15MPVIQsJF30eFw, reason: not valid java name */
    public static /* synthetic */ void m9423$r8$lambda$qvTJl5eEYrp15MPVIQsJF30eFw(ChatAttachAlert r0, AttachBotButton r1, Boolean r2) {
        r0.lambda$new$13(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$sOV1bBpQs3ckOBvmzpRyXH30Cps(ChatAttachAlert r0, CharSequence r1, Integer r2, Integer r3, Boolean r4) {
        r0.lambda$new$22(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$t1Bl6JHa2kiDTdaVE1SazxpfqQA(ChatAttachAlert r0, ArrayList r1, CharSequence r2, boolean r3, int r4, int r5, long r6, boolean r8, long r9) {
        r0.lambda$openAudioLayout$59(r1, r2, r3, r4, r5, r6, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$uNWNUSkg7_uT1VyjVSeNzkCmf1A(ChatAttachAlert r0, boolean r1, View r2) {
        r0.lambda$new$5(r1, r2);
    }

    /* renamed from: $r8$lambda$vOyCuEHcVbQ_81vDy-D5baQk5vc, reason: not valid java name */
    public static /* synthetic */ void m9424$r8$lambda$vOyCuEHcVbQ_81vDyD5baQk5vc(ChatAttachAlert r0, int r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: $r8$lambda$vZDuI28n-lprnwYyt0bQpUOJXu0, reason: not valid java name */
    public static /* synthetic */ void m9425$r8$lambda$vZDuI28nlprnwYyt0bQpUOJXu0(ChatAttachAlert r0, Theme.ResourcesProvider r1, View r2) {
        r0.lambda$new$27(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$w5aai3dnK5ISPeH_vJsqZEr32uE(ChatAttachAlert r0, ChatActivity r1, MessageSuggestionParams r2) {
        r0.lambda$new$36(r1, r2);
    }

    /* renamed from: $r8$lambda$wxdvnv1GgB6BfxSAQIWOtlQ3l-M, reason: not valid java name */
    public static /* synthetic */ void m9426$r8$lambda$wxdvnv1GgB6BfxSAQIWOtlQ3lM(ChatAttachAlert r0, float r1, float r2, boolean r3, DynamicAnimation r4, float r5, float r6) {
        r0.lambda$showLayout$56(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ boolean $r8$lambda$wyxZawmu1FQ4dxlEUsqdpH13sHI(ChatAttachAlert r0, View r1, int r2) {
        return r0.lambda$new$15(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$xM1pOcNTw0VaZh_n1MkAifiKCzs(ChatAttachAlert r0, DialogInterface r1) {
        r0.lambda$dismiss$76(r1);
    }

    public static /* synthetic */ void $r8$lambda$yBhIcLalhy8NilDppjlNebmrc_s(ChatAttachAlert r0, boolean r1, int r2, int r3) {
        r0.lambda$new$34(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$yJRapRRYU4hG9RVTIx1EAzSzGeM(ChatAttachAlert r0, TLRPC.TL_attachMenuBot r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$onLongClickBotButton$50(r1, r2, r3);
    }

    public static /* synthetic */ boolean $r8$lambda$yoGb_XH4v_f5CZ4XvSQBkQ0D9jw(ChatAttachAlert r0, View r1, MotionEvent r2) {
        return r0.lambda$new$29(r1, r2);
    }

    /* renamed from: -$$Nest$fgetactionBarAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m9427$$Nest$fgetactionBarAnimation(ChatAttachAlert r0) {
        return r0.actionBarAnimation;
    }

    /* renamed from: -$$Nest$fgetaiButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m9428$$Nest$fgetaiButton(ChatAttachAlert r0) {
        return r0.aiButton;
    }

    /* renamed from: -$$Nest$fgetanimatorCaptionNotEmpty, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BoolAnimator m9429$$Nest$fgetanimatorCaptionNotEmpty(ChatAttachAlert r0) {
        return r0.animatorCaptionNotEmpty;
    }

    /* renamed from: -$$Nest$fgetappearSpringAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SpringAnimation m9430$$Nest$fgetappearSpringAnimation(ChatAttachAlert r0) {
        return r0.appearSpringAnimation;
    }

    /* renamed from: -$$Nest$fgetattachItemSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9431$$Nest$fgetattachItemSize(ChatAttachAlert r0) {
        return r0.attachItemSize;
    }

    /* renamed from: -$$Nest$fgetaudioLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatAttachAlertAudioLayout m9432$$Nest$fgetaudioLayout(ChatAttachAlert r0) {
        return r0.audioLayout;
    }

    /* renamed from: -$$Nest$fgetbaseSelectedTextViewTranslationY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m9433$$Nest$fgetbaseSelectedTextViewTranslationY(ChatAttachAlert r0) {
        return r0.baseSelectedTextViewTranslationY;
    }

    /* renamed from: -$$Nest$fgetbotAttachLayouts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m9434$$Nest$fgetbotAttachLayouts(ChatAttachAlert r0) {
        return r0.botAttachLayouts;
    }

    /* renamed from: -$$Nest$fgetbotButtonProgressWasVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9435$$Nest$fgetbotButtonProgressWasVisible(ChatAttachAlert r0) {
        return r0.botButtonProgressWasVisible;
    }

    /* renamed from: -$$Nest$fgetbotButtonWasVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9436$$Nest$fgetbotButtonWasVisible(ChatAttachAlert r0) {
        return r0.botButtonWasVisible;
    }

    /* renamed from: -$$Nest$fgetbotMainButtonOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m9437$$Nest$fgetbotMainButtonOffsetY(ChatAttachAlert r0) {
        return r0.botMainButtonOffsetY;
    }

    /* renamed from: -$$Nest$fgetbotMainButtonTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedTextView m9438$$Nest$fgetbotMainButtonTextView(ChatAttachAlert r0) {
        return r0.botMainButtonTextView;
    }

    /* renamed from: -$$Nest$fgetbotProgressView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RadialProgressView m9439$$Nest$fgetbotProgressView(ChatAttachAlert r0) {
        return r0.botProgressView;
    }

    /* renamed from: -$$Nest$fgetbottomFadeDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundWithFadeDrawable m9440$$Nest$fgetbottomFadeDrawable(ChatAttachAlert r0) {
        return r0.bottomFadeDrawable;
    }

    /* renamed from: -$$Nest$fgetbottomFadeView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m9441$$Nest$fgetbottomFadeView(ChatAttachAlert r0) {
        return r0.bottomFadeView;
    }

    /* renamed from: -$$Nest$fgetbuttonsAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ButtonsAdapter m9442$$Nest$fgetbuttonsAdapter(ChatAttachAlert r0) {
        return r0.buttonsAdapter;
    }

    /* renamed from: -$$Nest$fgetcaptionContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m9443$$Nest$fgetcaptionContainer(ChatAttachAlert r0) {
        return r0.captionContainer;
    }

    /* renamed from: -$$Nest$fgetcaptionContainerBg, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundDrawable m9444$$Nest$fgetcaptionContainerBg(ChatAttachAlert r0) {
        return r0.captionContainerBg;
    }

    /* renamed from: -$$Nest$fgetcaptionEditTextTopOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m9445$$Nest$fgetcaptionEditTextTopOffset(ChatAttachAlert r0) {
        return r0.captionEditTextTopOffset;
    }

    /* renamed from: -$$Nest$fgetcaptionLimitView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedTextView m9446$$Nest$fgetcaptionLimitView(ChatAttachAlert r0) {
        return r0.captionLimitView;
    }

    /* renamed from: -$$Nest$fgetchatActivityEnterViewAnimateFromTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m9447$$Nest$fgetchatActivityEnterViewAnimateFromTop(ChatAttachAlert r0) {
        return r0.chatActivityEnterViewAnimateFromTop;
    }

    /* renamed from: -$$Nest$fgetcodepointCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9448$$Nest$fgetcodepointCount(ChatAttachAlert r0) {
        return r0.codepointCount;
    }

    /* renamed from: -$$Nest$fgetcommentsAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m9449$$Nest$fgetcommentsAnimator(ChatAttachAlert r0) {
        return r0.commentsAnimator;
    }

    /* renamed from: -$$Nest$fgetcontactsLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatAttachAlertContactsLayout m9450$$Nest$fgetcontactsLayout(ChatAttachAlert r0) {
        return r0.contactsLayout;
    }

    /* renamed from: -$$Nest$fgetcurrentAttachLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AttachAlertLayout m9451$$Nest$fgetcurrentAttachLayout(ChatAttachAlert r0) {
        return r0.currentAttachLayout;
    }

    /* renamed from: -$$Nest$fgetcurrentLimit, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9452$$Nest$fgetcurrentLimit(ChatAttachAlert r0) {
        return r0.currentLimit;
    }

    /* renamed from: -$$Nest$fgetdocumentLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatAttachAlertDocumentLayout m9453$$Nest$fgetdocumentLayout(ChatAttachAlert r0) {
        return r0.documentLayout;
    }

    /* renamed from: -$$Nest$fgetdocumentsDelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate m9454$$Nest$fgetdocumentsDelegate(ChatAttachAlert r0) {
        return r0.documentsDelegate;
    }

    /* renamed from: -$$Nest$fgeteditType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9455$$Nest$fgeteditType(ChatAttachAlert r0) {
        return r0.editType;
    }

    /* renamed from: -$$Nest$fgetemojiViewChildBg, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundDrawable m9456$$Nest$fgetemojiViewChildBg(ChatAttachAlert r0) {
        return r0.emojiViewChildBg;
    }

    /* renamed from: -$$Nest$fgetenterCommentEventSent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9457$$Nest$fgetenterCommentEventSent(ChatAttachAlert r0) {
        return r0.enterCommentEventSent;
    }

    /* renamed from: -$$Nest$fgetexclusionRects, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m9458$$Nest$fgetexclusionRects(ChatAttachAlert r0) {
        return r0.exclusionRects;
    }

    /* renamed from: -$$Nest$fgetexclustionRect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Rect m9459$$Nest$fgetexclustionRect(ChatAttachAlert r0) {
        return r0.exclustionRect;
    }

    /* renamed from: -$$Nest$fgetfadeView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivityFadeView m9460$$Nest$fgetfadeView(ChatAttachAlert r0) {
        return r0.fadeView;
    }

    /* renamed from: -$$Nest$fgetframeLayout2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m9461$$Nest$fgetframeLayout2(ChatAttachAlert r0) {
        return r0.frameLayout2;
    }

    /* renamed from: -$$Nest$fgetfromScrollY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m9462$$Nest$fgetfromScrollY(ChatAttachAlert r0) {
        return r0.fromScrollY;
    }

    /* renamed from: -$$Nest$fgetiBlur3SourceColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundSourceColor m9463$$Nest$fgetiBlur3SourceColor(ChatAttachAlert r0) {
        return r0.iBlur3SourceColor;
    }

    /* renamed from: -$$Nest$fgetiBlur3SourceGlass, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundSourceRenderNode m9464$$Nest$fgetiBlur3SourceGlass(ChatAttachAlert r0) {
        return r0.iBlur3SourceGlass;
    }

    /* renamed from: -$$Nest$fgetiBlur3SourceGlassFrosted, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundSourceRenderNode m9465$$Nest$fgetiBlur3SourceGlassFrosted(ChatAttachAlert r0) {
        return r0.iBlur3SourceGlassFrosted;
    }

    /* renamed from: -$$Nest$fgetisSoundPicker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9466$$Nest$fgetisSoundPicker(ChatAttachAlert r0) {
        return r0.isSoundPicker;
    }

    /* renamed from: -$$Nest$fgetlocationLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatAttachAlertLocationLayout m9467$$Nest$fgetlocationLayout(ChatAttachAlert r0) {
        return r0.locationLayout;
    }

    /* renamed from: -$$Nest$fgetmenuShowed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9468$$Nest$fgetmenuShowed(ChatAttachAlert r0) {
        return r0.menuShowed;
    }

    /* renamed from: -$$Nest$fgetmessageSendPreview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageSendPreview m9469$$Nest$fgetmessageSendPreview(ChatAttachAlert r0) {
        return r0.messageSendPreview;
    }

    /* renamed from: -$$Nest$fgetmotionHint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HintView2 m9470$$Nest$fgetmotionHint(ChatAttachAlert r0) {
        return r0.motionHint;
    }

    /* renamed from: -$$Nest$fgetnextAttachLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AttachAlertLayout m9471$$Nest$fgetnextAttachLayout(ChatAttachAlert r0) {
        return r0.nextAttachLayout;
    }

    /* renamed from: -$$Nest$fgetopenTransitionFinished, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9472$$Nest$fgetopenTransitionFinished(ChatAttachAlert r0) {
        return r0.openTransitionFinished;
    }

    /* renamed from: -$$Nest$fgetoverrideBackgroundColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9473$$Nest$fgetoverrideBackgroundColor(ChatAttachAlert r0) {
        return r0.overrideBackgroundColor;
    }

    /* renamed from: -$$Nest$fgetpaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m9474$$Nest$fgetpaint(ChatAttachAlert r0) {
        return r0.paint;
    }

    /* renamed from: -$$Nest$fgetphotoLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatAttachAlertPhotoLayout m9475$$Nest$fgetphotoLayout(ChatAttachAlert r0) {
        return r0.photoLayout;
    }

    /* renamed from: -$$Nest$fgetphotoPreviewLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatAttachAlertPhotoLayoutPreview m9476$$Nest$fgetphotoPreviewLayout(ChatAttachAlert r0) {
        return r0.photoPreviewLayout;
    }

    /* renamed from: -$$Nest$fgetphotosEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9477$$Nest$fgetphotosEnabled(ChatAttachAlert r0) {
        return r0.photosEnabled;
    }

    /* renamed from: -$$Nest$fgetplainTextEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9478$$Nest$fgetplainTextEnabled(ChatAttachAlert r0) {
        return r0.plainTextEnabled;
    }

    /* renamed from: -$$Nest$fgetpollAllowedLayouts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9479$$Nest$fgetpollAllowedLayouts(ChatAttachAlert r0) {
        return r0.pollAllowedLayouts;
    }

    /* renamed from: -$$Nest$fgetpollLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatAttachAlertPollLayout m9480$$Nest$fgetpollLayout(ChatAttachAlert r0) {
        return r0.pollLayout;
    }

    /* renamed from: -$$Nest$fgetpollsEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9481$$Nest$fgetpollsEnabled(ChatAttachAlert r0) {
        return r0.pollsEnabled;
    }

    /* renamed from: -$$Nest$fgetpreviousScrollOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9482$$Nest$fgetpreviousScrollOffsetY(ChatAttachAlert r0) {
        return r0.previousScrollOffsetY;
    }

    /* renamed from: -$$Nest$fgetquickRepliesLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatAttachAlertQuickRepliesLayout m9483$$Nest$fgetquickRepliesLayout(ChatAttachAlert r0) {
        return r0.quickRepliesLayout;
    }

    /* renamed from: -$$Nest$fgetrect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m9484$$Nest$fgetrect(ChatAttachAlert r0) {
        return r0.rect;
    }

    /* renamed from: -$$Nest$fgetscrollableViewNoiseSuppressor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DownscaleScrollableNoiseSuppressor m9485$$Nest$fgetscrollableViewNoiseSuppressor(ChatAttachAlert r0) {
        return r0.scrollableViewNoiseSuppressor;
    }

    /* renamed from: -$$Nest$fgetselectedId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m9486$$Nest$fgetselectedId(ChatAttachAlert r2) {
        return r2.selectedId;
    }

    /* renamed from: -$$Nest$fgetsendButtonEnabledProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m9487$$Nest$fgetsendButtonEnabledProgress(ChatAttachAlert r0) {
        return r0.sendButtonEnabledProgress;
    }

    /* renamed from: -$$Nest$fgettextPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextPaint m9488$$Nest$fgettextPaint(ChatAttachAlert r0) {
        return r0.textPaint;
    }

    /* renamed from: -$$Nest$fgettoScrollY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m9489$$Nest$fgettoScrollY(ChatAttachAlert r0) {
        return r0.toScrollY;
    }

    /* renamed from: -$$Nest$fgettodoEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9490$$Nest$fgettodoEnabled(ChatAttachAlert r0) {
        return r0.todoEnabled;
    }

    /* renamed from: -$$Nest$fgettodoLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatAttachAlertPollLayout m9491$$Nest$fgettodoLayout(ChatAttachAlert r0) {
        return r0.todoLayout;
    }

    /* renamed from: -$$Nest$fgettopBackgroundAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m9492$$Nest$fgettopBackgroundAnimator(ChatAttachAlert r0) {
        return r0.topBackgroundAnimator;
    }

    /* renamed from: -$$Nest$fgettopCaptionLimitView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedTextView m9493$$Nest$fgettopCaptionLimitView(ChatAttachAlert r0) {
        return r0.topCaptionLimitView;
    }

    /* renamed from: -$$Nest$fgetvideosEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9494$$Nest$fgetvideosEnabled(ChatAttachAlert r0) {
        return r0.videosEnabled;
    }

    /* renamed from: -$$Nest$fgetviewChangeAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Object m9495$$Nest$fgetviewChangeAnimator(ChatAttachAlert r0) {
        return r0.viewChangeAnimator;
    }

    /* renamed from: -$$Nest$fgetwriteButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivityEnterView.SendButton m9496$$Nest$fgetwriteButton(ChatAttachAlert r0) {
        return r0.writeButton;
    }

    /* renamed from: -$$Nest$fgetwriteButtonContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m9497$$Nest$fgetwriteButtonContainer(ChatAttachAlert r0) {
        return r0.writeButtonContainer;
    }

    /* renamed from: -$$Nest$fputactionBarAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9498$$Nest$fputactionBarAnimation(ChatAttachAlert r0, AnimatorSet r1) {
        r0.actionBarAnimation = r1;
    }

    /* renamed from: -$$Nest$fputattachItemSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9499$$Nest$fputattachItemSize(ChatAttachAlert r0, int r1) {
        r0.attachItemSize = r1;
    }

    /* renamed from: -$$Nest$fputbotButtonProgressWasVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9500$$Nest$fputbotButtonProgressWasVisible(ChatAttachAlert r0, boolean r1) {
        r0.botButtonProgressWasVisible = r1;
    }

    /* renamed from: -$$Nest$fputbotButtonWasVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9501$$Nest$fputbotButtonWasVisible(ChatAttachAlert r0, boolean r1) {
        r0.botButtonWasVisible = r1;
    }

    /* renamed from: -$$Nest$fputbotMainButtonOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9502$$Nest$fputbotMainButtonOffsetY(ChatAttachAlert r0, float r1) {
        r0.botMainButtonOffsetY = r1;
    }

    /* renamed from: -$$Nest$fputbottomPannelTranslation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9503$$Nest$fputbottomPannelTranslation(ChatAttachAlert r0, float r1) {
        r0.bottomPannelTranslation = r1;
    }

    /* renamed from: -$$Nest$fputcaptionEditTextTopOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9504$$Nest$fputcaptionEditTextTopOffset(ChatAttachAlert r0, float r1) {
        r0.captionEditTextTopOffset = r1;
    }

    /* renamed from: -$$Nest$fputchatActivityEnterViewAnimateFromTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9505$$Nest$fputchatActivityEnterViewAnimateFromTop(ChatAttachAlert r0, float r1) {
        r0.chatActivityEnterViewAnimateFromTop = r1;
    }

    /* renamed from: -$$Nest$fputcodepointCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9506$$Nest$fputcodepointCount(ChatAttachAlert r0, int r1) {
        r0.codepointCount = r1;
    }

    /* renamed from: -$$Nest$fputcommentsAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9507$$Nest$fputcommentsAnimator(ChatAttachAlert r0, AnimatorSet r1) {
        r0.commentsAnimator = r1;
    }

    /* renamed from: -$$Nest$fputeffectId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9508$$Nest$fputeffectId(ChatAttachAlert r0, long r1) {
        r0.effectId = r1;
    }

    /* renamed from: -$$Nest$fputfromScrollY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9509$$Nest$fputfromScrollY(ChatAttachAlert r0, float r1) {
        r0.fromScrollY = r1;
    }

    /* renamed from: -$$Nest$fputhasOverridenWebviewBackgroundColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9510$$Nest$fputhasOverridenWebviewBackgroundColor(ChatAttachAlert r0, boolean r1) {
        r0.hasOverridenWebviewBackgroundColor = r1;
    }

    /* renamed from: -$$Nest$fputmenuAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9511$$Nest$fputmenuAnimator(ChatAttachAlert r0, AnimatorSet r1) {
        r0.menuAnimator = r1;
    }

    /* renamed from: -$$Nest$fputoverrideBackgroundColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9512$$Nest$fputoverrideBackgroundColor(ChatAttachAlert r0, boolean r1) {
        r0.overrideBackgroundColor = r1;
    }

    /* renamed from: -$$Nest$fputoverridenWebviewBackgroundColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9513$$Nest$fputoverridenWebviewBackgroundColor(ChatAttachAlert r0, int r1) {
        r0.overridenWebviewBackgroundColor = r1;
    }

    /* renamed from: -$$Nest$fputpreviousScrollOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9514$$Nest$fputpreviousScrollOffsetY(ChatAttachAlert r0, int r1) {
        r0.previousScrollOffsetY = r1;
    }

    /* renamed from: -$$Nest$fputtoScrollY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9515$$Nest$fputtoScrollY(ChatAttachAlert r0, float r1) {
        r0.toScrollY = r1;
    }

    /* renamed from: -$$Nest$fputtopBackgroundAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9516$$Nest$fputtopBackgroundAnimator(ChatAttachAlert r0, ValueAnimator r1) {
        r0.topBackgroundAnimator = r1;
    }

    /* renamed from: -$$Nest$fputviewChangeAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9517$$Nest$fputviewChangeAnimator(ChatAttachAlert r0, Object r1) {
        r0.viewChangeAnimator = r1;
    }

    /* renamed from: -$$Nest$mapplyAttachButtonColors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9518$$Nest$mapplyAttachButtonColors(ChatAttachAlert r0, View r1) {
        r0.applyAttachButtonColors(r1);
    }

    /* renamed from: -$$Nest$mcheckIsEphemeralMessage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9519$$Nest$mcheckIsEphemeralMessage(ChatAttachAlert r0, boolean r1) {
        r0.checkIsEphemeralMessage(r1);
    }

    /* renamed from: -$$Nest$mcheckUi_fadeTopAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9520$$Nest$mcheckUi_fadeTopAlpha(ChatAttachAlert r0) {
        r0.checkUi_fadeTopAlpha();
    }

    /* renamed from: -$$Nest$mcheckUi_writeButtonContainerY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9521$$Nest$mcheckUi_writeButtonContainerY(ChatAttachAlert r0) {
        r0.checkUi_writeButtonContainerY();
    }

    /* renamed from: -$$Nest$mcreateMentionsContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9522$$Nest$mcreateMentionsContainer(ChatAttachAlert r0) {
        r0.createMentionsContainer();
    }

    /* renamed from: -$$Nest$mgetActionBarDrawableColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9523$$Nest$mgetActionBarDrawableColor(ChatAttachAlert r0) {
        return r0.getActionBarDrawableColor();
    }

    /* renamed from: -$$Nest$mgetEmojiPadding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9524$$Nest$mgetEmojiPadding(ChatAttachAlert r0) {
        return r0.getEmojiPadding();
    }

    /* renamed from: -$$Nest$mgetScrollOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9525$$Nest$mgetScrollOffsetY(ChatAttachAlert r0, int r1) {
        return r0.getScrollOffsetY(r1);
    }

    /* renamed from: -$$Nest$mopenAudioLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9526$$Nest$mopenAudioLayout(ChatAttachAlert r0, boolean r1) {
        r0.openAudioLayout(r1);
    }

    /* renamed from: -$$Nest$mreplaceWithText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9527$$Nest$mreplaceWithText(ChatAttachAlert r0, int r1, int r2, CharSequence r3, boolean r4) {
        r0.replaceWithText(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$mshowAiButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9528$$Nest$mshowAiButton(ChatAttachAlert r0, boolean r1) {
        r0.showAiButton(r1);
    }

    /* renamed from: -$$Nest$mshowCaptionLimitBulletin, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9529$$Nest$mshowCaptionLimitBulletin(ChatAttachAlert r0, BaseFragment r1) {
        r0.showCaptionLimitBulletin(r1);
    }

    /* renamed from: -$$Nest$mupdateCameraButtonVisibility, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9530$$Nest$mupdateCameraButtonVisibility(ChatAttachAlert r0) {
        r0.updateCameraButtonVisibility();
    }

    /* renamed from: -$$Nest$mupdateSelectedPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9531$$Nest$mupdateSelectedPosition(ChatAttachAlert r0, int r1) {
        r0.updateSelectedPosition(r1);
    }

    /* renamed from: -$$Nest$mupdatedTopCaptionHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9532$$Nest$mupdatedTopCaptionHeight(ChatAttachAlert r0) {
        r0.updatedTopCaptionHeight();
    }

    /* renamed from: -$$Nest$smcheckContactsPermission, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9533$$Nest$smcheckContactsPermission(Context r0) {
        return checkContactsPermission(r0);
    }

    /* renamed from: -$$Nest$smcheckMusicPermission, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9534$$Nest$smcheckMusicPermission(Context r0) {
        return checkMusicPermission(r0);
    }

    /* renamed from: -$$Nest$smcheckPhotoAndCameraPermission, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9535$$Nest$smcheckPhotoAndCameraPermission(Context r0) {
        return checkPhotoAndCameraPermission(r0);
    }

    /* renamed from: -$$Nest$smcheckPhotoAndDocumentsPermission, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9536$$Nest$smcheckPhotoAndDocumentsPermission(Context r0) {
        return checkPhotoAndDocumentsPermission(r0);
    }

    public ChatAttachAlert(Context r8, BaseFragment r9, boolean r10, boolean r11) {
        this(r8, r9, r10, r11, true, null);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public ChatAttachAlert(Context r40, BaseFragment r41, boolean r42, boolean r43, boolean r44, Theme.ResourcesProvider r45) {
        super(r40, false, r45);
        CubicBezierInterpolator r3 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.animatorCaptionAbove = new BoolAnimator(0, this, r3, 380);
        this.animatorCaptionVisible = new BoolAnimator(1, this, r3, 380);
        this.animatorActionBarVisible = new BoolAnimator(2, this, r3, 380);
        this.animatorCaptionNotEmpty = new BoolAnimator(3, this, r3, 380);
        this.animatorToggleCaptionSupported = new BoolAnimator(4, this, r3, 380, true);
        this.animatorEphemeralMessageVisibility = new BoolAnimator(5, this, r3, 320);
        ReplaceAnimator<Long> r14 = new ReplaceAnimator(new ChatAttachAlert$$ExternalSyntheticLambda18(this), r3, 380);
        this.animatorCurrentVisibleLayout = r14;
        this.canOpenPreview = false;
        this.isSoundPicker = false;
        this.isStoryLocationPicker = false;
        this.isBizLocationPicker = false;
        this.isLocationPicker = false;
        this.isStoryAudioPicker = false;
        this.translationProgress = 0.0f;
        this.ATTACH_ALERT_LAYOUT_TRANSLATION = new AnonymousClass2(this, "translation");
        this.allowLivePhotos = false;
        this.layouts = new AttachAlertLayout[11];
        this.botAttachLayouts = new LongSparseArray();
        this.commentTextViewLocation = new int[2];
        this.textPaint = new TextPaint(1);
        this.rect = new RectF();
        this.paint = new Paint(1);
        this.sendButtonEnabled = true;
        this.sendButtonEnabledProgress = 1.0f;
        this.cornerRadius = 1.0f;
        this.botButtonProgressWasVisible = false;
        this.botButtonWasVisible = false;
        int r32 = UserConfig.selectedAccount;
        this.currentAccount = r32;
        this.documentsEnabled = true;
        this.photosEnabled = true;
        this.videosEnabled = true;
        this.musicEnabled = true;
        this.pollsEnabled = true;
        this.todoEnabled = true;
        this.plainTextEnabled = true;
        this.maxSelectedPhotos = -1;
        this.allowOrder = true;
        this.attachItemSize = AndroidUtilities.dp(85.0f);
        this.decelerateInterpolator = new DecelerateInterpolator();
        this.scrollOffsetY = new int[2];
        this.attachButtonPaint = new Paint(1);
        this.captionLimitBulletinShown = false;
        this.exclusionRects = new ArrayList();
        this.exclustionRect = new Rect();
        this.ATTACH_ALERT_PROGRESS = new AnonymousClass33(this, "openProgress");
        this.allowDrawContent = true;
        this.sent = false;
        this.confirmationAlertShown = false;
        this.allowPassConfirmationAlert = false;
        ArrayList<RectF> r5 = new ArrayList();
        this.iBlur3Positions = r5;
        RectF r6 = new RectF();
        this.iBlur3PositionActionBar = r6;
        RectF r0 = new RectF();
        this.iBlur3PositionMainTabs = r0;
        RectF r1 = new RectF();
        this.iBlur3PositionFastScroll = r1;
        r5.add(r6);
        r5.add(r0);
        r5.add(r1);
        this.iBlur3PositionsMerged = new ArrayList();
        this.occupyNavigationBarWithoutKeyboard = true;
        BlurredBackgroundSourceColor r02 = new BlurredBackgroundSourceColor();
        this.iBlur3SourceColor = r02;
        r02.setColor(getThemedColor(Theme.key_windowBackgroundWhite));
        if (Build.VERSION.SDK_INT < 31) goto L5;
        this.scrollableViewNoiseSuppressor = new DownscaleScrollableNoiseSuppressor();
        BlurredBackgroundSourceRenderNode r12 = new BlurredBackgroundSourceRenderNode(null);
        this.iBlur3SourceGlass = r12;
        r12.setupRenderer(new AnonymousClass3(this));
        BlurredBackgroundSourceRenderNode r52 = new BlurredBackgroundSourceRenderNode(null);
        this.iBlur3SourceGlassFrosted = r52;
        r52.setupRenderer(new AnonymousClass4(this));
        BlurredBackgroundDrawableViewFactory r4 = new BlurredBackgroundDrawableViewFactory(r12);
        this.iBlur3FactoryLiquidGlass = r4;
        r4.setLiquidGlassEffectAllowed(LiteMode.isEnabled(262144));
        BlurredBackgroundDrawableViewFactory r13 = new BlurredBackgroundDrawableViewFactory(r52);
        this.iBlur3FactoryFrostedLiquidGlass = r13;
        r13.setLiquidGlassEffectAllowed(LiteMode.isEnabled(262144));
    L6:
        BlurredBackgroundDrawableViewFactory r15 = new BlurredBackgroundDrawableViewFactory(r02);
        this.iBlur3FactoryFade = r15;
        this.iBlur3Capture = new ChatAttachAlert$$ExternalSyntheticLambda29(this);
        this.forceDarkTheme = r42;
        this.showingFromDialog = r43;
        if ((r41 instanceof ChatActivity) == true) goto L9;
    L11:
        boolean r03 = false;
    L12:
        this.inBubbleMode = r03;
        this.openInterpolator = new OvershootInterpolator(0.7f);
        this.baseFragment = r41;
        this.useSmoothKeyboard = true;
        setDelegate(this);
        NotificationCenter.getInstance(r32).addObserver(this, NotificationCenter.reloadInlineHints);
        NotificationCenter.getInstance(r32).addObserver(this, NotificationCenter.attachMenuBotsDidLoad);
        NotificationCenter.getInstance(r32).addObserver(this, NotificationCenter.currentUserPremiumStatusChanged);
        NotificationCenter.getInstance(r32).addObserver(this, NotificationCenter.quickRepliesUpdated);
        this.exclusionRects.add(this.exclustionRect);
        AnonymousClass5 r04 = new AnonymousClass5(this, r40);
        this.sizeNotifierFrameLayout = r04;
        r04.setDelegate(new AnonymousClass6(this));
        SizeNotifierFrameLayout r05 = this.sizeNotifierFrameLayout;
        this.containerView = r05;
        r05.setWillNotDraw(false);
        this.containerView.setClipChildren(false);
        this.containerView.setClipToPadding(false);
        ViewGroup r06 = this.containerView;
        int r46 = this.backgroundPaddingLeft;
        r06.setPadding(r46, 0, r46, 0);
        AnonymousClass7 r07 = new AnonymousClass7(this, r40, r45);
        this.actionBar = r07;
        r07.alwaysApplyColorFilterToBackButton();
        this.actionBar.setForcedMenuWidth(AndroidUtilities.dp(46.0f));
        this.actionBar.setBackButtonDrawable(new BackDrawable(false));
        ActionBar r08 = this.actionBar;
        int r47 = Theme.key_dialogTextBlack;
        r08.setItemsColor(getThemedColor(r47), false);
        ActionBar r09 = this.actionBar;
        int r53 = Theme.key_dialogButtonSelector;
        r09.setItemsBackgroundColor(getThemedColor(r53), false);
        this.actionBar.setTitleColor(getThemedColor(r47));
        this.actionBar.setOccupyStatusBar(true);
        this.actionBar.setAlpha(0.0f);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass8(this));
        ActionBarMenuItem r010 = new ActionBarMenuItem(r40, null, 0, getThemedColor(r47), false, r45);
        this.selectedMenuItem = r010;
        r010.setLongClickEnabled(false);
        this.selectedMenuItem.setIcon(R.drawable.ic_ab_other);
        this.selectedMenuItem.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
        this.selectedMenuItem.setVisibility(4);
        this.selectedMenuItem.setAlpha(0.0f);
        this.selectedMenuItem.setScaleX(0.6f);
        this.selectedMenuItem.setScaleY(0.6f);
        this.selectedMenuItem.setSubMenuOpenSide(2);
        this.selectedMenuItem.setDelegate(new ChatAttachAlert$$ExternalSyntheticLambda37(this));
        this.selectedMenuItem.setAdditionalYOffset(AndroidUtilities.dp(72.0f));
        this.selectedMenuItem.setTranslationX(AndroidUtilities.dp(1.0f));
        this.selectedMenuItem.setBackground(Theme.createSelectorDrawable(getThemedColor(r53), 6));
        this.selectedMenuItem.setOnClickListener(new ChatAttachAlert$$ExternalSyntheticLambda38(this));
        ActionBarMenuItem r011 = new ActionBarMenuItem(r40, null, 0, getThemedColor(r47), false, r45);
        this.motionItem = r011;
        r011.setLongClickEnabled(false);
        ActionBarMenuItem r012 = this.motionItem;
        MotionPhotoDrawable r2 = new MotionPhotoDrawable();
        this.motionIcon = r2;
        r012.setIcon(r2);
        this.motionItem.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
        this.motionItem.setVisibility(8);
        this.motionItem.setAlpha(0.0f);
        this.motionItem.setScaleX(0.6f);
        this.motionItem.setScaleY(0.6f);
        this.motionItem.setAdditionalYOffset(AndroidUtilities.dp(72.0f));
        this.motionItem.setTranslationX(-AndroidUtilities.dp(3.0f));
        this.motionItem.setBackground(Theme.createSelectorDrawable(getThemedColor(r53), 6));
        this.motionItem.setOnClickListener(new ChatAttachAlert$$ExternalSyntheticLambda39(this));
        AnonymousClass9 r013 = new AnonymousClass9(this, r40);
        r013.setTextColor(getThemedColor(Theme.key_featuredStickers_buttonText));
        r013.setText(LocaleController.getString(R.string.Create));
        r013.setTypeface(AndroidUtilities.bold());
        r013.setTextSize(1, 14.0f);
        r013.setVisibility(4);
        r013.setAlpha(0.0f);
        r013.setGravity(17);
        r013.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
        r013.setTranslationX(-AndroidUtilities.dp(12.0f));
        r013.setOnClickListener(new ChatAttachAlert$$ExternalSyntheticLambda40(this));
        ScaleStateListAnimator.apply(r013);
        this.doneItem = r013;
        updateDoneItemEnabled();
        if (r41 == null) goto L15;
        float r25 = 14.0f;
        AnonymousClass9 r27 = r013;
        int r152 = r53;
        ActionBarMenuItem r014 = new ActionBarMenuItem(r40, null, 0, getThemedColor(r47), false, r45);
        this.searchItem = r014;
        r014.setLongClickEnabled(false);
        this.searchItem.setIcon(R.drawable.outline_header_search);
        this.searchItem.setContentDescription(LocaleController.getString(R.string.Search));
        int r16 = 4;
        this.searchItem.setVisibility(4);
        this.searchItem.setAlpha(0.0f);
        this.searchItem.setTranslationX(-AndroidUtilities.dp(42.0f));
        this.searchItem.setBackgroundDrawable(Theme.createSelectorDrawable(getThemedColor(r152), 6));
        this.searchItem.setOnClickListener(new ChatAttachAlert$$ExternalSyntheticLambda41(this, r43));
    L16:
        ActionBarMenuItem r015 = new ActionBarMenuItem(r40, null, 0, getThemedColor(r47), false, r45);
        this.optionsItem = r015;
        r015.setLongClickEnabled(false);
        this.optionsItem.setIcon(R.drawable.ic_ab_other);
        this.optionsItem.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
        this.optionsItem.setVisibility(8);
        this.optionsItem.setBackground(Theme.createSelectorDrawable(getThemedColor(r152), 3));
        this.optionsItem.addSubItem(1, R.drawable.msg_addbot, LocaleController.getString(R.string.StickerCreateEmpty)).setOnClickListener(new ChatAttachAlert$$ExternalSyntheticLambda42(this, r45));
        this.optionsItem.setMenuYOffset(AndroidUtilities.dp(-12.0f));
        this.optionsItem.setAdditionalXOffset(AndroidUtilities.dp(12.0f));
        this.optionsItem.setOnClickListener(new ChatAttachAlert$$ExternalSyntheticLambda43(this));
        AnonymousClass12 r016 = new AnonymousClass12(this, r40);
        this.headerView = r016;
        r016.setOnClickListener(new ChatAttachAlert$$ExternalSyntheticLambda44(this));
        this.headerView.setAlpha(0.0f);
        this.headerView.setVisibility(r16);
        LinearLayout r017 = new LinearLayout(r40);
        this.selectedView = r017;
        r017.setOrientation(0);
        this.selectedView.setGravity(16);
        TextView r018 = new TextView(r40);
        this.selectedTextView = r018;
        r018.setTextColor(getThemedColor(r47));
        this.selectedTextView.setTextSize(1, 16.0f);
        this.selectedTextView.setTypeface(AndroidUtilities.bold());
        this.selectedTextView.setGravity(19);
        this.selectedTextView.setMaxLines(1);
        this.selectedTextView.setEllipsize(TextUtils.TruncateAt.END);
        this.selectedView.addView(this.selectedTextView, LayoutHelper.createLinear(-2, -2, 16));
        this.selectedArrowImageView = new ImageView(r40);
        Drawable r019 = getContext().getResources().getDrawable(R.drawable.attach_arrow_right).mutate();
        int r54 = getThemedColor(r47);
        PorterDuff.Mode r153 = PorterDuff.Mode.MULTIPLY;
        r019.setColorFilter(new PorterDuffColorFilter(r54, r153));
        this.selectedArrowImageView.setImageDrawable(r019);
        this.selectedArrowImageView.setVisibility(8);
        this.selectedView.addView(this.selectedArrowImageView, LayoutHelper.createLinear(-2, -2, 16, 4, 1, 0, 0));
        this.selectedView.setAlpha(1.0f);
        this.headerView.addView(this.selectedView, LayoutHelper.createFrame(-2, -1.0f));
        LinearLayout r020 = new LinearLayout(r40);
        this.mediaPreviewView = r020;
        r020.setOrientation(0);
        this.mediaPreviewView.setGravity(16);
        ImageView r021 = new ImageView(r40);
        Drawable r22 = getContext().getResources().getDrawable(R.drawable.attach_arrow_left).mutate();
        r22.setColorFilter(new PorterDuffColorFilter(getThemedColor(r47), r153));
        r021.setImageDrawable(r22);
        this.mediaPreviewView.addView(r021, LayoutHelper.createLinear(-2, -2, 16, 0, 1, 4, 0));
        TextView r022 = new TextView(r40);
        this.mediaPreviewTextView = r022;
        r022.setTextColor(getThemedColor(r47));
        this.mediaPreviewTextView.setTextSize(1, 16.0f);
        this.mediaPreviewTextView.setTypeface(AndroidUtilities.bold());
        this.mediaPreviewTextView.setGravity(19);
        this.mediaPreviewTextView.setText(LocaleController.getString("AttachMediaPreview", R.string.AttachMediaPreview));
        this.mediaPreviewView.setAlpha(0.0f);
        this.mediaPreviewView.addView(this.mediaPreviewTextView, LayoutHelper.createLinear(-2, -2, 16));
        this.headerView.addView(this.mediaPreviewView, LayoutHelper.createFrame(-2, -1.0f));
        AttachAlertLayout[] r11 = this.layouts;
        ChatAttachAlertPhotoLayout r023 = new ChatAttachAlertPhotoLayout(this, r40, r42, r44, r45);
        this.photoLayout = r023;
        r11[0] = r023;
        r023.setTranslationX(0.0f);
        ChatAttachAlertPhotoLayout r024 = this.photoLayout;
        this.currentAttachLayout = r024;
        this.selectedId = 1;
        this.containerView.addView(r024, LayoutHelper.createFrame(-1, -1.0f));
        ChatActivityFadeView r025 = new ChatActivityFadeView(r40);
        this.fadeView = r025;
        r025.setup(r15);
        this.fadeView.setFadeTopAlpha(0);
        this.fadeView.setFadeHeightTop(AndroidUtilities.dp(48.0f));
        this.fadeView.setFadeHeightBottom(AndroidUtilities.dp(48.0f));
        this.fadeView.setFadeZoneTop((AndroidUtilities.statusBarHeight + ActionBar.getCurrentActionBarHeight()) + AndroidUtilities.dp(5.0f));
        this.containerView.addView(this.fadeView, LayoutHelper.createFrameMatchParent());
        this.containerView.addView(this.headerView, LayoutHelper.createFrame(-1, -2.0f, 51, 23.0f, 0.0f, 21.0f, 0.0f));
        AnonymousClass13 r026 = new AnonymousClass13(this, r40);
        this.topCommentContainer = r026;
        this.containerView.addView(r026, LayoutHelper.createFrame(-1, -2, 55));
        this.containerView.addView(this.actionBar, LayoutHelper.createFrame(-1, -2.0f));
        this.containerView.addView(this.selectedMenuItem, LayoutHelper.createFrame(48, 48, 53));
        this.containerView.addView(this.motionItem, LayoutHelper.createFrame(48, 48.0f, 53, 0.0f, 0.0f, 48.0f, 0.0f));
        ActionBarMenuItem r027 = this.searchItem;
        if (r027 == null) goto L19;
        this.containerView.addView(r027, LayoutHelper.createFrame(48, 48, 53));
    L19:
        ActionBarMenuItem r028 = this.optionsItem;
        if (r028 == null) goto L22;
        this.headerView.addView(r028, LayoutHelper.createFrame(32, 32.0f, 21, 0.0f, 0.0f, 0.0f, 8.0f));
    L22:
        this.containerView.addView(r27, LayoutHelper.createFrame(-2, 48, 53));
        this.buttonsRecyclerViewWrapper = new AnonymousClass14(this, r40);
        AnonymousClass15 r029 = new AnonymousClass15(this, r40);
        this.buttonsRecyclerView = r029;
        r029.setClipChildren(true);
        this.buttonsRecyclerView.setClipToPadding(false);
        RecyclerListView r030 = this.buttonsRecyclerView;
        ButtonsAdapter r33 = new ButtonsAdapter(this, r40);
        this.buttonsAdapter = r33;
        r030.setAdapter(r33);
        RecyclerListView r031 = this.buttonsRecyclerView;
        LinearLayoutManager r34 = new LinearLayoutManager(r40, 0, false);
        this.buttonsLayoutManager = r34;
        r031.setLayoutManager(r34);
        this.buttonsRecyclerView.setVerticalScrollBarEnabled(false);
        this.buttonsRecyclerView.setHorizontalScrollBarEnabled(false);
        this.buttonsRecyclerView.setItemAnimator(null);
        this.buttonsRecyclerView.setLayoutAnimation(null);
        this.buttonsRecyclerView.setGlowColor(getThemedColor(Theme.key_dialogScrollGlow));
        this.buttonsRecyclerView.setAdaptiveOverScroll();
        ViewPositionWatcher r032 = this.viewPositionWatcher;
        if (r032 == null) goto L25;
        r032.shutdown();
    L25:
        ViewPositionWatcher r033 = new ViewPositionWatcher(this.containerView);
        this.viewPositionWatcher = r033;
        this.iBlur3FactoryLiquidGlass.setSourceRootView(r033, this.containerView);
        this.iBlur3FactoryFrostedLiquidGlass.setSourceRootView(this.viewPositionWatcher, this.containerView);
        r15.setSourceRootView(this.viewPositionWatcher, this.containerView);
        this.bottomFadeView = new AnonymousClass16(this, r40);
        this.bottomFadeDrawable = new BlurredBackgroundWithFadeDrawable(r15.create(this.bottomFadeView, null));
        if (SharedConfig.chatBlurEnabled() == false) goto L28;
        LiteMode.isEnabled(262144);
    L28:
        this.bottomFadeDrawable.setFadeHeight(AndroidUtilities.dp(72.0f), true);
        this.containerView.addView(this.bottomFadeView, LayoutHelper.createFrameMatchParent());
        BlurredBackgroundDrawable r034 = this.iBlur3FactoryLiquidGlass.create(this.buttonsRecyclerViewWrapper, BlurredBackgroundProviderImpl.mainTabs(r45));
        r034.setRadius(AndroidUtilities.dp(28.0f));
        r034.setPadding(AndroidUtilities.dp(7.0f));
        this.buttonsRecyclerViewWrapper.setBackground(r034);
        this.buttonsRecyclerView.setPadding(AndroidUtilities.dp(11.0f), AndroidUtilities.dp(11.0f), AndroidUtilities.dp(11.0f), AndroidUtilities.dp(11.0f));
        this.buttonsRecyclerView.setClipToOutline(true);
        this.buttonsRecyclerView.setOutlineProvider(ViewOutlineProviderImpl.boundsWithPaddingRoundRect(AndroidUtilities.dp(11.0f), AndroidUtilities.dp(28.0f)));
        this.buttonsRecyclerView.setImportantForAccessibility(1);
        this.buttonsRecyclerView.setOverScrollMode(2);
        this.buttonsRecyclerViewWrapper.addView(this.buttonsRecyclerView, LayoutHelper.createFrameMatchParent());
        this.containerView.addView(this.buttonsRecyclerViewWrapper, LayoutHelper.createFrame(-1, 70, 81));
        this.buttonsRecyclerView.setOnItemClickListener(new ChatAttachAlert$$ExternalSyntheticLambda19(this, r45));
        this.buttonsRecyclerView.setOnItemLongClickListener(new ChatAttachAlert$$ExternalSyntheticLambda20(this));
        AnimatedTextView r035 = new AnimatedTextView(r40, true, false, true);
        this.botMainButtonTextView = r035;
        r035.setVisibility(8);
        this.botMainButtonTextView.setAlpha(0.0f);
        this.botMainButtonTextView.setGravity(17);
        this.botMainButtonTextView.setTypeface(AndroidUtilities.bold());
        int r036 = AndroidUtilities.dp(16.0f);
        this.botMainButtonTextView.setPadding(r036, 0, r036, 0);
        this.botMainButtonTextView.setTextSize(AndroidUtilities.dp(r25));
        this.botMainButtonTextView.setOnClickListener(new ChatAttachAlert$$ExternalSyntheticLambda21(this));
        this.containerView.addView(this.botMainButtonTextView, LayoutHelper.createFrame(-1, 48, 83));
        RadialProgressView r037 = new RadialProgressView(r40);
        this.botProgressView = r037;
        r037.setSize(AndroidUtilities.dp(18.0f));
        this.botProgressView.setAlpha(0.0f);
        this.botProgressView.setScaleX(0.1f);
        this.botProgressView.setScaleY(0.1f);
        this.botProgressView.setVisibility(8);
        this.containerView.addView(this.botProgressView, LayoutHelper.createFrame(28, 28.0f, 85, 0.0f, 0.0f, 10.0f, 10.0f));
        FragmentFloatingButton r038 = new FragmentFloatingButton(r40, r45);
        this.cameraFloatingButton = r038;
        r038.setImageResource(R.drawable.camera);
        r038.setOnClickListener(new ChatAttachAlert$$ExternalSyntheticLambda22(this));
        r038.setOnLongClickListener(new ChatAttachAlert$$ExternalSyntheticLambda23(this));
        FrameLayout.LayoutParams r35 = FragmentFloatingButton.createDefaultLayoutParams();
        r35.bottomMargin += AndroidUtilities.dp(63.0f);
        this.containerView.addView(r038, r35);
        ImageView r039 = new ImageView(r40);
        this.moveCaptionButton = r039;
        ImageView.ScaleType r36 = ImageView.ScaleType.CENTER;
        r039.setScaleType(r36);
        ImageView r040 = this.moveCaptionButton;
        int r7 = getThemedColor(Theme.key_windowBackgroundWhiteGrayText2);
        PorterDuff.Mode r9 = PorterDuff.Mode.SRC_IN;
        r040.setColorFilter(new PorterDuffColorFilter(r7, r9));
        this.moveCaptionButton.setImageResource(R.drawable.menu_link_above);
        this.moveCaptionButton.setVisibility(8);
        this.moveCaptionButton.setOnClickListener(new ChatAttachAlert$$ExternalSyntheticLambda24(this));
        this.frameLayout2 = new AnonymousClass17(this, r40);
        AnonymousClass18 r041 = new AnonymousClass18(this, r40);
        this.captionContainer = r041;
        this.frameLayout2.addView(r041, LayoutHelper.createFrame(-1, -1, 119));
        BlurredBackgroundDrawable r042 = this.iBlur3FactoryFrostedLiquidGlass.create(this.sizeNotifierFrameLayout, BlurredBackgroundProviderImpl.inputFieldDialogActivity(r45));
        this.emojiViewChildBg = r042;
        r042.enableInAppKeyboardOptimization();
        this.emojiViewChildBg.setRadius(AndroidUtilities.dp(29.0f), AndroidUtilities.dp(29.0f), 0.0f, 0.0f);
        this.emojiViewChildBg.setThickness(AndroidUtilities.dp(32.0f));
        this.emojiViewChildBg.setIntensity(0.4f);
        BlurredBackgroundDrawable r043 = this.iBlur3FactoryLiquidGlass.create(this.captionContainer, BlurredBackgroundProviderImpl.inputFieldDialogActivity(r45));
        this.captionContainerBg = r043;
        r043.setRadius(AndroidUtilities.dp(22.0f));
        this.captionContainerBg.setPadding(AndroidUtilities.dp(7.0f));
        this.captionContainer.setPadding(AndroidUtilities.dp(7.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(7.0f), AndroidUtilities.dp(5.0f));
        this.frameLayout2.setWillNotDraw(false);
        this.frameLayout2.setVisibility(4);
        this.frameLayout2.setAlpha(0.0f);
        this.containerView.addView(this.frameLayout2, LayoutHelper.createFrame(-1, -2, 83));
        this.frameLayout2.setOnTouchListener(new ChatAttachAlert$$ExternalSyntheticLambda25());
        AnimatedTextView r044 = new AnimatedTextView(r40);
        this.captionLimitView = r044;
        r044.setAllowCancel(true);
        r044.setScaleProperty(0.6f);
        r044.setVisibility(8);
        r044.setTextSize(AndroidUtilities.dp(15.0f));
        int r48 = Theme.key_windowBackgroundWhiteGrayText;
        r044.setTextColor(getThemedColor(r48));
        r044.setTypeface(AndroidUtilities.bold());
        r044.setGravity(17);
        this.captionContainer.addView(r044, LayoutHelper.createFrame(56, 20.0f, 85, 3.0f, 0.0f, 3.0f, 50.0f));
        ImageView r045 = new ImageView(r40);
        this.aiButton = r045;
        AiButtonDrawable r55 = new AiButtonDrawable(r40);
        this.aiButtonIcon = r55;
        r045.setImageDrawable(r55);
        r045.setScaleType(r36);
        int r72 = Theme.key_glass_defaultIcon;
        r045.setColorFilter(new PorterDuffColorFilter(getThemedColor(r72), r153));
        int r10 = Theme.key_listSelector;
        r045.setBackground(Theme.createSelectorDrawable(getThemedColor(r10), 1, AndroidUtilities.dp(16.0f)));
        this.captionContainer.addView(r045, LayoutHelper.createFrame(44, 44.0f, 53, 0.0f, 1.0f, 0.0f, 0.0f));
        r045.setContentDescription(LocaleController.getString(R.string.AIEditor));
        ScaleStateListAnimator.apply(r045);
        r045.setOnClickListener(new ChatAttachAlert$$ExternalSyntheticLambda26(this, r45));
        r045.setVisibility(8);
        r045.setAlpha(0.0f);
        r045.setScaleX(0.6f);
        r045.setScaleY(0.6f);
        this.currentLimit = MessagesController.getInstance(UserConfig.selectedAccount).getCaptionMaxLengthLimit();
        if (UserConfig.getInstance(r32).isPremium() == false) goto L31;
    L34:
        boolean r62 = true;
    L35:
        AnonymousClass19 r046 = new AnonymousClass19(this, r40, this.sizeNotifierFrameLayout, null, 1, r62, r45);
        this.commentTextView = r046;
        r046.includeNavigationBar = true;
        r046.allowEmojisForNonPremium(LocaleUtils.canUseLocalPremiumEmojis(r32));
        this.commentTextView.setHint(LocaleController.getString("AddCaption", R.string.AddCaption));
        this.commentTextView.onResume();
        this.commentTextView.getEditText().setLayoutParams(LayoutHelper.createFrame(-1, -1.0f, 19, 48.0f, 0.0f, 36.0f, 0.0f));
        this.commentTextView.getEditText().addTextChangedListener(new AnonymousClass20(this));
        this.captionContainer.addView(this.commentTextView, LayoutHelper.createFrame(-1, -2.0f, 83, 0.0f, 0.0f, 84.0f, 0.0f));
        this.captionContainer.setClipChildren(false);
        this.frameLayout2.setClipChildren(false);
        this.commentTextView.setClipChildren(false);
        this.topCommentContainer.setPadding(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f));
        this.topCommentContainer.setWillNotDraw(false);
        AnonymousClass21 r047 = new AnonymousClass21(this, r40, this.sizeNotifierFrameLayout, null, 1, r62, r45);
        this.topCommentTextView = r047;
        r047.includeNavigationBar = true;
        r047.getEditText().addTextChangedListener(new AnonymousClass22(this, r41));
        this.topCommentTextView.allowEmojisForNonPremium(LocaleUtils.canUseLocalPremiumEmojis(r32));
        this.topCommentTextView.getEditText().setPadding(0, AndroidUtilities.dp(9.0f), 0, AndroidUtilities.dp(9.0f));
        this.topCommentTextView.getEditText().setLayoutParams(LayoutHelper.createFrame(-1, -1.0f, 19, 48.0f, 0.0f, 96.0f, 0.0f));
        this.topCommentTextView.getEditText().setTextSize(1, 17.0f);
        this.topCommentTextView.getEmojiButton().setLayoutParams(LayoutHelper.createFrame(40, 40.0f, 83, 0.0f, 0.0f, 0.0f, 0.0f));
        this.topCommentTextView.setHint(LocaleController.getString("AddCaption", R.string.AddCaption));
        this.topCommentContainer.addView(this.topCommentTextView, LayoutHelper.createFrame(-1, -2, 119));
        this.topCommentContainer.setAlpha(0.0f);
        this.topCommentContainer.setVisibility(8);
        this.commentTextView.addView(this.moveCaptionButton, LayoutHelper.createFrame(40, 40.0f, 85, 0.0f, 0.0f, 0.0f, 4.0f));
        BlurredBackgroundDrawable r048 = this.iBlur3FactoryLiquidGlass.create(this.topCommentContainer, BlurredBackgroundProviderImpl.inputFieldDialogActivity(r45));
        r048.setRadius(AndroidUtilities.dp(22.0f));
        r048.setPadding(AndroidUtilities.dp(7.0f));
        this.topCommentContainer.setBackground(r048);
        this.topCommentContainer.setPadding(AndroidUtilities.dp(11.0f), AndroidUtilities.dp(9.0f), AndroidUtilities.dp(11.0f), AndroidUtilities.dp(9.0f));
        AnimatedTextView r049 = new AnimatedTextView(r40);
        this.topCaptionLimitView = r049;
        r049.setScaleProperty(0.6f);
        r049.setVisibility(8);
        r049.setTextSize(AndroidUtilities.dp(15.0f));
        r049.setTextColor(getThemedColor(r48));
        r049.setTypeface(AndroidUtilities.bold());
        r049.setGravity(17);
        r049.setAllowCancel(true);
        this.topCommentContainer.addView(r049, LayoutHelper.createFrame(56, 20.0f, 53, 3.0f, 45.0f, 3.0f, 0.0f));
        ImageView r050 = new ImageView(r40);
        this.topCommentMoveButton = r050;
        r050.setScaleType(r36);
        this.topCommentMoveButton.setImageResource(R.drawable.menu_link_below);
        this.topCommentMoveButton.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_chat_messagePanelIcons), r9));
        this.topCommentTextView.addView(this.topCommentMoveButton, LayoutHelper.createFrame(40, 40.0f, 85, 0.0f, 0.0f, 60.0f, 0.0f));
        this.topCommentMoveButton.setOnClickListener(new ChatAttachAlert$$ExternalSyntheticLambda27(this));
        ImageView r051 = new ImageView(r40);
        this.topAiButton = r051;
        AiButtonDrawable r37 = new AiButtonDrawable(r40);
        this.topAiButtonIcon = r37;
        r051.setImageDrawable(r37);
        r051.setScaleType(r36);
        r051.setColorFilter(new PorterDuffColorFilter(getThemedColor(r72), r153));
        r051.setBackground(Theme.createSelectorDrawable(getThemedColor(r10), 1, AndroidUtilities.dp(16.0f)));
        this.topCommentContainer.addView(r051, LayoutHelper.createFrame(44, 44.0f, 85, 0.0f, 1.0f, 0.0f, 0.0f));
        r051.setContentDescription(LocaleController.getString(R.string.AIEditor));
        ScaleStateListAnimator.apply(r051);
        r051.setOnClickListener(new ChatAttachAlert$$ExternalSyntheticLambda28(this, r45));
        r051.setVisibility(8);
        r051.setAlpha(0.0f);
        r051.setScaleX(0.6f);
        r051.setScaleY(0.6f);
        AnonymousClass23 r052 = new AnonymousClass23(this, r40);
        this.writeButtonContainer = r052;
        r052.setFocusable(true);
        this.writeButtonContainer.setFocusableInTouchMode(true);
        this.writeButtonContainer.setVisibility(4);
        this.writeButtonContainer.setScaleX(0.2f);
        this.writeButtonContainer.setScaleY(0.2f);
        this.writeButtonContainer.setAlpha(0.0f);
        this.writeButtonContainer.setClipChildren(false);
        this.writeButtonContainer.setClipToPadding(false);
        this.containerView.addView(this.writeButtonContainer, LayoutHelper.createFrame(110, 50, 85));
        AnonymousClass24 r053 = new AnonymousClass24(this, r40, R.drawable.send_extera_24, r45);
        this.writeButton = r053;
        r053.setImportantForAccessibility(2);
        this.writeButtonContainer.addView(this.writeButton, LayoutHelper.createFrame(-1, -1, 119));
        this.writeButton.setTranslationX(this.backgroundPaddingLeft);
        this.writeButton.setCircleSize(AndroidUtilities.dp(52.0f), AndroidUtilities.dp(38.0f));
        this.writeButton.setCirclePadding(AndroidUtilities.dp(7.0f), AndroidUtilities.dp(6.0f));
        ChatActivityEnterView.SendButton r054 = this.writeButton;
        r054.newCounterPos = true;
        r054.setOnClickListener(new ChatAttachAlert$$ExternalSyntheticLambda30(this));
        this.writeButton.setOnTouchListener(new ChatAttachAlert$$ExternalSyntheticLambda31(this));
        this.writeButton.setOnLongClickListener(new ChatAttachAlert$$ExternalSyntheticLambda32(this, r40, r45, r41));
        this.textPaint.setTextSize(AndroidUtilities.dp(12.0f));
        this.textPaint.setTypeface(AndroidUtilities.bold());
        AnonymousClass27 r055 = new AnonymousClass27(this, r40);
        this.selectedCountView = r055;
        r055.setAlpha(0.0f);
        this.selectedCountView.setScaleX(0.2f);
        this.selectedCountView.setScaleY(0.2f);
        if (r42 == false) goto L38;
        checkColors();
        this.navBarColorKey = -1;
    L38:
        ChatAttachAlertPhotoLayout r056 = this.photoLayout;
        if (r056 == null) goto L41;
        r056.gridView.getFastScroll().applyBlurDrawables(this.iBlur3FactoryLiquidGlass, BlurredBackgroundProviderImpl.topPanel(r45));
    L41:
        PasscodeView r057 = new PasscodeView(r40);
        this.passcodeView = r057;
        this.containerView.addView(r057, LayoutHelper.createFrame(-1, -1.0f));
        this.actionBar.setupGlass(this.iBlur3FactoryLiquidGlass, new BlurredBackgroundProviderBuilder(r45).setBackgroundColor(new ChatAttachAlert$$ExternalSyntheticLambda33(this)).setStrokeColorTop(new ChatAttachAlert$$ExternalSyntheticLambda34(this)).setStrokeColorBottom(new ChatAttachAlert$$ExternalSyntheticLambda35(this)).setShadowColor(new ChatAttachAlert$$ExternalSyntheticLambda36(this)).setShadowLayer(AndroidUtilities.dpf2(3.3333333f), 0.0f, AndroidUtilities.dpf2(0.6666667f)).setStrokeWidth(AndroidUtilities.dpf2(1.0f), AndroidUtilities.dpf2(0.6666667f)).build());
        r14.replace(1L, false);
        return;
    L31:
        if (LocaleUtils.canUseLocalPremiumEmojis(r32) == true) goto L34;
        r62 = false;
        goto L35
    L15:
        r27 = r013;
        r152 = r53;
        r25 = 14.0f;
        r16 = 4;
        goto L16
    L9:
        if (r41.isInBubbleMode() == false) goto L11;
        r03 = true;
        goto L12
    L5:
        this.scrollableViewNoiseSuppressor = null;
        this.iBlur3SourceGlassFrosted = null;
        this.iBlur3SourceGlass = null;
        this.iBlur3FactoryLiquidGlass = new BlurredBackgroundDrawableViewFactory(r02);
        this.iBlur3FactoryFrostedLiquidGlass = new BlurredBackgroundDrawableViewFactory(r02);
        goto L6
    }

    public static /* synthetic */ Theme.ResourcesProvider access$000(ChatAttachAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$100(ChatAttachAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$1000(ChatAttachAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1100(ChatAttachAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ boolean access$1200(ChatAttachAlert r0) {
        return r0.keyboardVisible;
    }

    public static /* synthetic */ int access$1300(ChatAttachAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1400(ChatAttachAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1500(ChatAttachAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1600(ChatAttachAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ boolean access$1700(ChatAttachAlert r0) {
        return r0.keyboardVisible;
    }

    public static /* synthetic */ boolean access$1800(ChatAttachAlert r0) {
        return r0.keyboardVisible;
    }

    public static /* synthetic */ int access$1900(ChatAttachAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$200(ChatAttachAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$2000(ChatAttachAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$2100(ChatAttachAlert r0) {
        return r0.currentSheetAnimationType;
    }

    public static /* synthetic */ int access$2200(ChatAttachAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$2300(ChatAttachAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$2400(ChatAttachAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$2500(ChatAttachAlert r0) {
        return r0.currentSheetAnimationType;
    }

    public static /* synthetic */ int access$2600(ChatAttachAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$2700(ChatAttachAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$2800(ChatAttachAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ Drawable access$2900(ChatAttachAlert r0) {
        return r0.shadowDrawable;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$300(ChatAttachAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$3000(ChatAttachAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ Drawable access$3100(ChatAttachAlert r0) {
        return r0.shadowDrawable;
    }

    public static /* synthetic */ Drawable access$3200(ChatAttachAlert r0) {
        return r0.shadowDrawable;
    }

    public static /* synthetic */ int access$3300(ChatAttachAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$3400(ChatAttachAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$3500(ChatAttachAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$3600(ChatAttachAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$3700(ChatAttachAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$3800(ChatAttachAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$3900(ChatAttachAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$400(ChatAttachAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$4000(ChatAttachAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$4100(ChatAttachAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$4200(ChatAttachAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$4300(ChatAttachAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$4400(ChatAttachAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$4500(ChatAttachAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$4600(ChatAttachAlert r0) {
        return r0.currentSheetAnimationType;
    }

    public static /* synthetic */ int access$4700(ChatAttachAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$4800(ChatAttachAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$4900(ChatAttachAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ ViewGroup access$500(ChatAttachAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$5000(ChatAttachAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$5100(ChatAttachAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ Drawable access$5200(ChatAttachAlert r0) {
        return r0.shadowDrawable;
    }

    public static /* synthetic */ Drawable access$5300(ChatAttachAlert r0) {
        return r0.shadowDrawable;
    }

    public static /* synthetic */ Drawable access$5400(ChatAttachAlert r0) {
        return r0.shadowDrawable;
    }

    public static /* synthetic */ int access$5500(ChatAttachAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$5600(ChatAttachAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$5700(ChatAttachAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$5800(ChatAttachAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$5900(ChatAttachAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$600(ChatAttachAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$6000(ChatAttachAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$6100(ChatAttachAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$6200(ChatAttachAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$6300(ChatAttachAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$6400(ChatAttachAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ ViewGroup access$6500(ChatAttachAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$6600(ChatAttachAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$6700(ChatAttachAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$6800(ChatAttachAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$6900(ChatAttachAlert r0) {
        return r0.currentSheetAnimationType;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$700(ChatAttachAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$7000(ChatAttachAlert r0) {
        return r0.currentSheetAnimationType;
    }

    public static /* synthetic */ ViewGroup access$7100(ChatAttachAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$7200(ChatAttachAlert r0) {
        return r0.currentSheetAnimationType;
    }

    public static /* synthetic */ ViewGroup access$7300(ChatAttachAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$7400(ChatAttachAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ ViewGroup access$7500(ChatAttachAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ void access$7601(ChatAttachAlert r0) {
        super.dismiss();
    }

    public static /* synthetic */ int access$7700(ChatAttachAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$7800(ChatAttachAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$7900(ChatAttachAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$800(ChatAttachAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$8000(ChatAttachAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$8100(ChatAttachAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$8200(ChatAttachAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$8300(ChatAttachAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$8400(ChatAttachAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ ViewGroup access$8500(ChatAttachAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$8600(ChatAttachAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ AnimatorSet access$8700(ChatAttachAlert r0) {
        return r0.currentSheetAnimation;
    }

    public static /* synthetic */ AnimatorSet access$8800(ChatAttachAlert r0) {
        return r0.currentSheetAnimation;
    }

    public static /* synthetic */ AnimatorSet access$8900(ChatAttachAlert r0) {
        return r0.currentSheetAnimation;
    }

    public static /* synthetic */ int access$900(ChatAttachAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ AnimatorSet access$9000(ChatAttachAlert r0) {
        return r0.currentSheetAnimation;
    }

    public static /* synthetic */ AnimatorSet access$9102(ChatAttachAlert r0, AnimatorSet r1) {
        r0.currentSheetAnimation = r1;
        return r1;
    }

    public static /* synthetic */ int access$9202(ChatAttachAlert r0, int r1) {
        r0.currentSheetAnimationType = r1;
        return r1;
    }

    private void applyAttachButtonColors(View r1) {
    }

    private void checkColorSourceColor(int r2) {
        if (this.iBlur3SourceColor.getColor() == r2) goto L11;
        this.iBlur3SourceColor.setColor(r2);
        ChatActivityFadeView r22 = this.fadeView;
        if (r22 == null) goto L7;
        r22.invalidate();
    L7:
        View r1 = this.bottomFadeView;
        if (r1 == null) goto L12;
        r1.invalidate();
        return;
    L12:
        return;
    }

    private static boolean checkContactsPermission(Context r1) {
        if (ContextCompat.checkSelfPermission(r1, "android.permission.READ_CONTACTS") != 0) goto L6;
        return true;
    L6:
        return false;
    }

    private void checkIsEphemeralMessage(boolean r7) {
        BaseFragment r0 = this.baseFragment;
        if (r0 != null) goto L5;
        return;
    L5:
        if ((r0 instanceof ChatActivity) == false) goto L37;
        ChatActivity r02 = (ChatActivity) r0;
        if (this.captionAbove == false) goto L10;
        EditTextEmoji r1 = this.topCommentTextView;
    L11:
        if (r1 == null) goto L13;
        String r12 = r1.getText().toString();
    L15:
        if (this.editingMessageObject != null) goto L27;
        AttachAlertLayout r2 = this.currentAttachLayout;
        boolean r4 = true;
        if (r2 == null) goto L21;
        if (r2.getSelectedItemsCount() > 1) goto L27;
    L21:
        if (EphemeralMessagesHelper.getInstance(this.currentAccount).isEphemeralCommand(r12, r02.botInfo) == false) goto L23;
    L28:
        this.animatorEphemeralMessageVisibility.setValue(r4, r7);
        if (r4 == true) goto L31;
        return;
    L31:
        if (isCaptionAbove() == false) goto L36;
        setCaptionAbove(false, r7);
        return;
    L36:
        return;
    L23:
        if (r02.getReplyMessage() == null) goto L27;
        if (r02.getReplyMessage().isEphemeral() == true) goto L28;
    L27:
        r4 = false;
        goto L28
    L13:
        r12 = null;
        goto L15
    L10:
        r1 = this.commentTextView;
        goto L11
    }

    private static boolean checkMusicPermission(Context r2) {
        if (Build.VERSION.SDK_INT < 33) goto L5;
        String r0 = "android.permission.READ_MEDIA_AUDIO";
    L7:
        if (ContextCompat.checkSelfPermission(r2, r0) != 0) goto L10;
        return true;
    L10:
        return false;
    L5:
        r0 = "android.permission.READ_EXTERNAL_STORAGE";
        goto L7
    }

    private static boolean checkPhotoAndCameraPermission(Context r4) {
        if (Build.VERSION.SDK_INT < 33) goto L13;
        if (ContextCompat.checkSelfPermission(r4, "android.permission.READ_MEDIA_IMAGES") == 0) goto L7;
    L11:
        return false;
    L7:
        if (ContextCompat.checkSelfPermission(r4, "android.permission.READ_MEDIA_VIDEO") != 0) goto L11;
        if (ContextCompat.checkSelfPermission(r4, "android.permission.CAMERA") != 0) goto L11;
        return true;
    L13:
        if (ContextCompat.checkSelfPermission(r4, "android.permission.READ_EXTERNAL_STORAGE") != 0) goto L15;
        return true;
    L15:
        return false;
    }

    private static boolean checkPhotoAndDocumentsPermission(Context r4) {
        if (Build.VERSION.SDK_INT < 33) goto L11;
        if (ContextCompat.checkSelfPermission(r4, "android.permission.READ_MEDIA_IMAGES") == 0) goto L7;
    L9:
        return false;
    L7:
        if (ContextCompat.checkSelfPermission(r4, "android.permission.READ_MEDIA_VIDEO") != 0) goto L9;
        return true;
    L11:
        if (ContextCompat.checkSelfPermission(r4, "android.permission.READ_EXTERNAL_STORAGE") != 0) goto L13;
        return true;
    L13:
        return false;
    }

    private void checkUi_bottomFade() {
        float r0 = this.animatorCaptionVisible.getFloatValue();
        this.bottomFadeView.setTranslationY(AndroidUtilities.dp(48.0f) * Math.min(Math.min(1.0f, 1.0f - ((1.0f - this.animatorActionBarVisible.getFloatValue()) * (1.0f - r0))), 1.0f - ((1.0f - this.animatorCaptionAbove.getFloatValue()) * r0)));
    }

    private void checkUi_doneItemVisibility() {
        TextView r0 = this.doneItem;
        if (r0 == null) goto L10;
        float r1 = this.doneItemAlphaByEnabled * this.doneItemAlphaByLayout;
        r0.setAlpha(r1);
        TextView r3 = this.doneItem;
        if (r1 <= 0.0f) goto L7;
        int r02 = 0;
    L8:
        r3.setVisibility(r02);
        return;
    L7:
        r02 = 4;
        goto L8
    }

    private void checkUi_fadeTopAlpha() {
        if (this.fadeView != null) goto L5;
        return;
    L5:
        if (this.actionBar == null) goto L20;
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L9;
        boolean r02 = r0.isDark();
    L10:
        ChatActivityFadeView r1 = this.fadeView;
        if (this.actionBar.getVisibility() != 0) goto L16;
        if (r02 == false) goto L14;
        int r03 = 255;
    L15:
        int r3 = (int) (r03 * this.actionBar.getAlpha());
    L17:
        r1.setFadeTopAlpha(r3);
        return;
    L14:
        r03 = 160;
        goto L15
    L16:
        r3 = 0;
        goto L17
    L9:
        r02 = Theme.isCurrentThemeDark();
        goto L10
    }

    private void checkUi_writeButtonContainerY() {
        FrameLayout r0 = this.topCommentContainer;
        if (r0 != null) goto L5;
    L11:
        this.writeButtonContainer.setTranslationY(this.bottomPannelTranslation);
        this.writeButton.setAlpha(1.0f);
        return;
    L5:
        if (r0.getVisibility() != 0) goto L11;
        if (this.topCommentContainer.getAlpha() == 0.0f) goto L11;
        float r02 = this.animatorCaptionAbove.getFloatValue();
        float r1 = Math.abs(AndroidUtilities.lerp(-1.0f, 1.0f, r02));
        this.writeButton.setAlpha(r1 * ((r1 * r1) * r1));
        this.writeButtonContainer.setTranslationY(AndroidUtilities.lerp(this.bottomPannelTranslation, ((this.topCommentContainer.getTop() + this.topCommentContainer.getTranslationY()) - this.writeButtonContainer.getTop()) + AndroidUtilities.dp(8.0f), CubicBezierInterpolator.EASE_BOTH.getInterpolation(r02)));
    }

    private void createMentionsContainer() {
        AnonymousClass37 r0 = new AnonymousClass37(this, getContext(), this.dialogId, 0, LaunchActivity.getLastFragment(), this.resourcesProvider);
        this.mentionContainer = r0;
        setupMentionContainer(r0);
        AnonymousClass38 r9 = new AnonymousClass38(this);
        this.mentionsDelegate = r9;
        this.mentionContainer.withDelegate(r9);
        ViewGroup r92 = this.containerView;
        r92.addView(this.mentionContainer, r92.indexOfChild(this.frameLayout2), LayoutHelper.createFrame(-1, -1, 83));
        setupMentionContainer(this.mentionContainer);
        updateCommentTextViewPosition();
    }

    private int getActionBarDrawableColor() {
        return getShadowDrawableColor(true);
    }

    private int getEmojiPadding() {
        AttachAlertLayout r0 = this.currentAttachLayout;
        ChatAttachAlertPollLayout r1 = this.pollLayout;
        if (r0 == r1) goto L5;
    L8:
        ChatAttachAlertPollLayout r12 = this.todoLayout;
        if (r0 != r12) goto L15;
        if (r12.emojiView == null) goto L15;
        return r12.getEmojiPadding();
    L15:
        if (this.captionAbove == false) goto L19;
        return this.topCommentTextView.getEmojiPadding();
    L19:
        return this.commentTextView.getEmojiPadding();
    L5:
        if (r1.emojiView == null) goto L8;
        return r1.getEmojiPadding();
    }

    private int getScrollOffsetY(int r3) {
        AttachAlertLayout r0 = this.nextAttachLayout;
        if (r0 == null) goto L11;
        if ((this.currentAttachLayout instanceof ChatAttachAlertPhotoLayoutPreview) == false) goto L7;
    L8:
        int[] r32 = this.scrollOffsetY;
        return AndroidUtilities.lerp(r32[0], r32[1], this.translationProgress);
    L7:
        if ((r0 instanceof ChatAttachAlertPhotoLayoutPreview) == true) goto L8;
    L11:
        return this.scrollOffsetY[r3];
    }

    private int getShadowDrawableColor() {
        return getShadowDrawableColor(false);
    }

    private int getShadowDrawableColor(boolean r10) {
        if (this.forceDarkTheme == true) goto L5;
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L9;
        boolean r02 = r0.isDark();
    L10:
        Iterator<ListAnimator.Entry<Long>> r1 = this.animatorCurrentVisibleLayout.iterator();
        float r3 = 0.0f;
    L12:
        if (r1.hasNext() == false) goto L30;
        ListAnimator.Entry<Long> r4 = r1.next();
        long r5 = r4.item.longValue();
        if (r5 == 1) goto L29;
        if (r5 == 3) goto L29;
        if (r5 == 4) goto L29;
        if (r5 == 5) goto L29;
        if (r5 == 6) goto L29;
        if (r5 == 9) goto L29;
        if (r5 == 11) goto L29;
        if (r5 != 12) goto L12;
    L29:
        r3 = r3 + r4.getVisibility();
        goto L12
    L30:
        float r2 = MathUtils.clamp(r3, 0.0f, 1.0f);
        if (r10 == false) goto L37;
        ActionBar r102 = this.actionBar;
        if (r102 == null) goto L37;
        if (r102.getVisibility() != 0) goto L37;
        r2 = r2 * (1.0f - this.actionBar.getAlpha());
    L37:
        int r103 = getThemedColor(Theme.key_dialogBackground);
        if (r02 == false) goto L40;
        int r03 = Theme.key_windowBackgroundGray;
    L42:
        return ColorUtils.blendARGB(r103, getThemedColor(r03), r2);
    L40:
        r03 = Theme.key_dialogBackgroundGray;
        goto L42
    L9:
        r02 = Theme.isCurrentThemeDark();
        goto L10
    L5:
        return getThemedColor(Theme.key_voipgroup_listViewBackground);
    }

    private boolean isLightStatusBar() {
        if (this.forceDarkTheme == false) goto L5;
        int r0 = Theme.key_voipgroup_listViewBackground;
    L7:
        if (ColorUtils.calculateLuminance(getThemedColor(r0)) <= 0.699999988079071d) goto L10;
        return true;
    L10:
        return false;
    L5:
        r0 = Theme.key_dialogBackground;
        goto L7
    }

    private /* synthetic */ void lambda$dismiss$74(AlertDialog r1, int r2) {
        this.allowPassConfirmationAlert = true;
        dismiss();
    }

    private /* synthetic */ void lambda$dismiss$75(DialogInterface r4) {
        SpringAnimation r42 = this.appearSpringAnimation;
        if (r42 == null) goto L5;
        r42.cancel();
    L5:
        SpringAnimation r43 = new SpringAnimation(this.containerView, DynamicAnimation.TRANSLATION_Y, 0.0f);
        this.appearSpringAnimation = r43;
        r43.getSpring().setDampingRatio(1.5f);
        this.appearSpringAnimation.getSpring().setStiffness(1500.0f);
        this.appearSpringAnimation.start();
    }

    private /* synthetic */ void lambda$dismiss$76(DialogInterface r1) {
        this.confirmationAlertShown = false;
    }

    private /* synthetic */ void lambda$dismiss$77(int r2) {
        this.navBarColorKey = -1;
        this.navBarColor = r2;
        this.containerView.invalidate();
    }

    private /* synthetic */ void lambda$init$70(TLRPC.MessageMedia r8, int r9, boolean r10, int r11, long r12) {
        ((ChatActivity) this.baseFragment).didSelectLocation(r8, r9, r10, r11, 0);
    }

    private /* synthetic */ void lambda$makeFocusable$67(EditTextBoldCursor r2, boolean r3) {
        setFocusable(true);
        r2.requestFocus();
        if (r3 == false) goto L6;
        AndroidUtilities.runOnUIThread(new ChatAttachAlert$$ExternalSyntheticLambda74(r2));
        return;
    }

    private /* synthetic */ void lambda$new$0(Canvas r10, RectF r11) {
        int r0 = 0;
    L4:
        if (r0 >= 2) goto L23;
        if (r0 != 0) goto L7;
        AttachAlertLayout r1 = this.currentAttachLayout;
    L8:
        if (r1 != null) goto L10;
    L21:
        Canvas r4 = r10;
        RectF r5 = r11;
    L22:
        r0 = r0 + 1;
        r10 = r4;
        r11 = r5;
        goto L4
    L10:
        if (r1.iBlur3Capture == null) goto L21;
        if (r1.getVisibility() != 0) goto L21;
        if (r0 != 0) goto L19;
        AttachAlertLayout r2 = this.nextAttachLayout;
        if (r2 == null) goto L19;
        if (r2.getVisibility() != 0) goto L19;
        float r22 = r1.getAlpha() * (1.0f - this.nextAttachLayout.getAlpha());
    L20:
        IBlur3Capture r3 = r1.iBlur3Capture;
        View r6 = r1.iBlur3CaptureView;
        ViewGroup r7 = getContainerView();
        r4 = r10;
        r5 = r11;
        Blur3Utils.captureRelativeParent(r3, r4, r5, r6, r7, (int) (r22 * 255.0f));
    L19:
        r22 = r1.getAlpha();
        goto L20
    L7:
        r1 = this.nextAttachLayout;
        goto L8
    }

    private /* synthetic */ void lambda$new$1(int r1) {
        this.actionBar.getActionBarMenuOnItemClick().onItemClick(r1);
    }

    private /* synthetic */ void lambda$new$10(TLRPC.MessageMedia r1, CharSequence r2, PollAttachedMediaPack r3, ArrayList r4, boolean r5, int r6, long r7) {
        ((ChatActivity) this.baseFragment).sendTodo((TLRPC.TL_messageMediaToDo) r1, r5, r6, r7);
    }

    private /* synthetic */ void lambda$new$11(AttachBotButton r4) {
        TLRPC.TL_attachMenuBot r0 = AttachBotButton.m9542$$Nest$fgetattachMenuBot(r4);
        AttachBotButton.m9542$$Nest$fgetattachMenuBot(r4).side_menu_disclaimer_needed = false;
        r0.inactive = false;
        showBotLayout(AttachBotButton.m9542$$Nest$fgetattachMenuBot(r4).bot_id, true);
        MediaDataController.getInstance(this.currentAccount).updateAttachMenuBotsInCache();
    }

    private /* synthetic */ void lambda$new$12(AttachBotButton r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new ChatAttachAlert$$ExternalSyntheticLambda80(this, r1));
    }

    private /* synthetic */ void lambda$new$13(AttachBotButton r4, Boolean r5) {
        TLRPC.TL_messages_toggleBotInAttachMenu r52 = new TLRPC.TL_messages_toggleBotInAttachMenu();
        r52.bot = MessagesController.getInstance(this.currentAccount).getInputUser(AttachBotButton.m9542$$Nest$fgetattachMenuBot(r4).bot_id);
        r52.enabled = true;
        r52.write_allowed = true;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r52, new ChatAttachAlert$$ExternalSyntheticLambda69(this, r4), 66);
    }

    private /* synthetic */ void lambda$new$14(Theme.ResourcesProvider r21, View r22, int r23) {
        BaseFragment r0 = this.baseFragment;
        if (r0 != null) goto L5;
        r0 = LaunchActivity.getLastFragment();
    L5:
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.getParentActivity() == null) goto L176;
        boolean r5 = true;
        if ((r22 instanceof AttachButton) == false) goto L158;
        Activity r02 = r0.getParentActivity();
        if (r02 != null) goto L15;
        r02 = AndroidUtilities.findActivity(getContext());
    L15:
        if ((r22.getTag() instanceof Integer) == false) goto L17;
        int r2 = ((Integer) r22.getTag()).intValue();
    L18:
        if (r2 != 1) goto L33;
        if (this.photosEnabled == true) goto L27;
        if (this.videosEnabled == true) goto L27;
        if (checkCanRemoveRestrictionsByBoosts() == false) goto L27;
        return;
    L27:
        if (this.photosEnabled == false) goto L29;
    L31:
        showLayout(this.photoLayout);
    L166:
        int r03 = r22.getLeft();
        int r24 = r22.getRight();
        int r3 = AndroidUtilities.dp(70.0f);
        int r04 = r03 - r3;
        RecyclerListView r4 = this.buttonsRecyclerView;
        if (r04 >= 0) goto L170;
        r4.smoothScrollBy(r04, 0);
        return;
    L170:
        int r25 = r24 + r3;
        if (r25 <= r4.getMeasuredWidth()) goto L175;
        RecyclerListView r05 = this.buttonsRecyclerView;
        r05.smoothScrollBy(r25 - r05.getMeasuredWidth(), 0);
        return;
    L175:
        return;
    L29:
        if (this.videosEnabled == true) goto L31;
        ChatAttachRestrictedLayout r06 = new ChatAttachRestrictedLayout(1, this, getContext(), r21);
        this.restrictedLayout = r06;
        showLayout(r06);
        goto L31
    L33:
        if (r2 == 3) goto L35;
        if (r2 != 4) goto L68;
        if (this.documentsEnabled == true) goto L57;
        if (checkCanRemoveRestrictionsByBoosts() == false) goto L57;
        return;
    L57:
        if (Build.VERSION.SDK_INT < 33) goto L63;
        if (SystemUtils.isImagesAndVideoPermissionGranted() == true) goto L66;
        SystemUtils.requestImagesAndVideoPermission(r02);
        return;
    L66:
        openDocumentsLayout(true);
        goto L166
    L63:
        if (SystemUtils.isStoragePermissionGranted() == true) goto L66;
        SystemUtils.requestStoragePermission(r02);
        return;
    L68:
        if (r2 != 5) goto L83;
        if (this.plainTextEnabled == false) goto L72;
    L74:
        boolean r26 = UserConfig.getInstance(this.currentAccount).syncContacts;
        if (this.plainTextEnabled == false) goto L81;
        if (r26 == false) goto L81;
        if (getContext().checkSelfPermission("android.permission.READ_CONTACTS") == 0) goto L81;
        r02.requestPermissions(new String[]{"android.permission.READ_CONTACTS"}, 5);
        return;
    L81:
        openContactsLayout();
        goto L166
    L72:
        if (checkCanRemoveRestrictionsByBoosts() == false) goto L74;
        return;
    L83:
        if (r2 != 6) goto L109;
        if (this.plainTextEnabled == true) goto L90;
        if (checkCanRemoveRestrictionsByBoosts() == false) goto L90;
        return;
    L90:
        if (AndroidUtilities.isMapsInstalled(this.baseFragment) == true) goto L93;
        return;
    L93:
        if (this.plainTextEnabled == true) goto L96;
        ChatAttachRestrictedLayout r07 = new ChatAttachRestrictedLayout(6, this, getContext(), r21);
        this.restrictedLayout = r07;
        showLayout(r07);
        goto L166
    L96:
        if (this.locationLayout != null) goto L107;
        AttachAlertLayout[] r08 = this.layouts;
        Context r32 = getContext();
        if (this.isPollAttach == false) goto L100;
    L102:
        r5 = false;
    L103:
        ChatAttachAlertLocationLayout r27 = new ChatAttachAlertLocationLayout(this, r32, r21, r5);
        this.locationLayout = r27;
        r08[5] = r27;
        ChatAttachAlertLocationLayout.LocationActivityDelegate r09 = this.locationActivityDelegate;
        if (r09 == null) goto L106;
        r27.setDelegate(r09);
        goto L107
    L106:
        r27.setDelegate(new ChatAttachAlert$$ExternalSyntheticLambda48(this));
        goto L107
    L100:
        if (this.restrictEphemeralMessageTypes == true) goto L102;
    L107:
        showLayout(this.locationLayout);
        goto L166
    L109:
        if (r2 != 9) goto L120;
        if (this.pollsEnabled == true) goto L116;
        if (checkCanRemoveRestrictionsByBoosts() == false) goto L116;
        return;
    L116:
        if (this.pollsEnabled == true) goto L118;
        ChatAttachRestrictedLayout r28 = new ChatAttachRestrictedLayout(9, this, getContext(), r21);
        this.restrictedLayout = r28;
        showLayout(r28);
        goto L166
    L118:
        showPollLayout(true, null);
        goto L166
    L120:
        if (r2 != 11) goto L123;
        openQuickRepliesLayout();
        goto L166
    L123:
        if (r2 != 12) goto L137;
        if (this.todoEnabled == true) goto L130;
        if (checkCanRemoveRestrictionsByBoosts() == false) goto L130;
        return;
    L130:
        if (this.todoEnabled == true) goto L133;
        ChatAttachRestrictedLayout r29 = new ChatAttachRestrictedLayout(9, this, getContext(), r21);
        this.restrictedLayout = r29;
        showLayout(r29);
        goto L166
    L133:
        if (this.todoLayout != null) goto L135;
        AttachAlertLayout[] r8 = this.layouts;
        ChatAttachAlertPollLayout r010 = new ChatAttachAlertPollLayout(this, getContext(), true, r21, null);
        this.todoLayout = r010;
        r8[6] = r010;
        r010.setDelegate(new ChatAttachAlert$$ExternalSyntheticLambda49(this));
    L135:
        showLayout(this.todoLayout);
        goto L166
    L137:
        if (r2 != 13) goto L143;
        if (this.stickersLayout != null) goto L141;
        AttachAlertLayout[] r011 = this.layouts;
        ChatAttachAlertEmojiLayout r210 = new ChatAttachAlertEmojiLayout(this, getContext(), r21, true);
        this.stickersLayout = r210;
        r011[8] = r210;
        r210.setDelegate(this.emojiViewDelegate);
    L141:
        showLayout(this.stickersLayout);
        goto L166
    L143:
        if (r2 != 14) goto L149;
        if (this.emojiLayout != null) goto L147;
        AttachAlertLayout[] r211 = this.layouts;
        ChatAttachAlertEmojiLayout r33 = new ChatAttachAlertEmojiLayout(this, getContext(), r21, false);
        this.emojiLayout = r33;
        r211[9] = r33;
        r33.setDelegate(this.emojiViewDelegate);
    L147:
        showLayout(this.emojiLayout);
        goto L166
    L149:
        if (r2 != 16) goto L155;
        if (this.richLayout != null) goto L153;
        AttachAlertLayout[] r012 = this.layouts;
        ChatAttachAlertRichLayout r212 = new ChatAttachAlertRichLayout(this, getContext(), this.currentAccount, r21);
        this.richLayout = r212;
        r012[10] = r212;
    L153:
        showLayout(this.richLayout);
        goto L166
    L155:
        if ((r22.getTag() instanceof Integer) == false) goto L166;
        this.delegate.didPressedButton(((Integer) r22.getTag()).intValue(), true, true, 0, 0, 0, isCaptionAbove(), false, 0);
        goto L166
    L35:
        if (this.musicEnabled == true) goto L40;
        if (checkCanRemoveRestrictionsByBoosts() == false) goto L40;
        return;
    L40:
        if (Build.VERSION.SDK_INT < 33) goto L46;
        if (SystemUtils.isAudioPermissionGranted() == true) goto L49;
        SystemUtils.requestAudioPermission(r02);
        return;
    L49:
        openAudioLayout(true);
        goto L166
    L46:
        if (r02.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0) goto L49;
        r02.requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 4);
        return;
    L17:
        r2 = -1;
        goto L18
    L158:
        if ((r22 instanceof AttachBotButton) == false) goto L166;
        AttachBotButton r013 = (AttachBotButton) r22;
        if (AttachBotButton.m9542$$Nest$fgetattachMenuBot(r013) != null) goto L162;
        this.delegate.didSelectBot(AttachBotButton.m9543$$Nest$fgetcurrentUser(r013));
        dismiss();
        goto L166
    L162:
        if (AttachBotButton.m9542$$Nest$fgetattachMenuBot(r013).inactive == false) goto L164;
        WebAppDisclaimerAlert.show(getContext(), new ChatAttachAlert$$ExternalSyntheticLambda50(this, r013), null, null);
        goto L166
    L164:
        showBotLayout(AttachBotButton.m9542$$Nest$fgetattachMenuBot(r013).bot_id, true);
        goto L166
    }

    private /* synthetic */ boolean lambda$new$15(View r2, int r3) {
        if ((r2 instanceof AttachBotButton) == false) goto L11;
        AttachBotButton r22 = (AttachBotButton) r2;
        if (this.destroyed == true) goto L11;
        if (AttachBotButton.m9543$$Nest$fgetcurrentUser(r22) == null) goto L11;
        onLongClickBotButton(AttachBotButton.m9542$$Nest$fgetattachMenuBot(r22), AttachBotButton.m9543$$Nest$fgetcurrentUser(r22));
        return true;
    L11:
        return false;
    }

    private /* synthetic */ void lambda$new$16(View r5) {
        long r0 = this.selectedId;
        if (r0 >= 0) goto L8;
        ChatAttachAlertBotWebViewLayout r4 = this.botAttachLayouts.get(-r0);
        if (r4 == null) goto L9;
        r4.getWebViewContainer().onMainButtonPressed();
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$new$17(View r2) {
        AttachAlertLayout r22 = this.currentAttachLayout;
        ChatAttachAlertPhotoLayout r0 = this.photoLayout;
        if (r22 != r0) goto L7;
        if (r0 == null) goto L8;
        r0.checkCamera(false);
        this.photoLayout.openCamera(true);
        return;
    L8:
        return;
    }

    private /* synthetic */ boolean lambda$new$18(View r13) {
        ChatAttachViewDelegate r0 = this.delegate;
        if (r0 == null) goto L7;
        r0.didPressedButton(0, false, true, 0, 0, 0, false, false, 0);
        return true;
    L7:
        return true;
    }

    private /* synthetic */ void lambda$new$19(View r1) {
        if (this.captionAbove == true) goto L6;
        toggleCaptionAbove();
        return;
    }

    private /* synthetic */ void lambda$new$2(View r1) {
        this.selectedMenuItem.toggleSubMenu();
    }

    private /* synthetic */ void lambda$new$21(CharSequence r2) {
        this.commentTextView.setText(r2);
        this.commentTextView.setSelection(r2.length(), r2.length());
    }

    private /* synthetic */ void lambda$new$22(CharSequence r1, Integer r2, Integer r3, Boolean r4) {
        this.commentTextView.setText(r1);
        this.commentTextView.setSelection(r1.length(), r1.length());
        onWriteButtonPressed();
    }

    private /* synthetic */ void lambda$new$23(Theme.ResourcesProvider r4, View r5) {
        if (this.commentTextView != null) goto L5;
        return;
    L5:
        MessagesController.getGlobalMainSettings().edit().putInt("aihintshown", 3).apply();
        AIEditorAlert r42 = new AIEditorAlert(getContext(), r4).setText(this.commentTextView.getText()).setOnUse(new ChatAttachAlert$$ExternalSyntheticLambda64(this));
        long r0 = this.dialogId;
        if (this.editingMessageObject == null) goto L8;
        boolean r52 = true;
    L9:
        r42.setOnSend(r0, r52, new ChatAttachAlert$$ExternalSyntheticLambda65(this)).show();
        return;
    L8:
        r52 = false;
        goto L9
    }

    private /* synthetic */ void lambda$new$24(View r1) {
        if (this.captionAbove == false) goto L6;
        toggleCaptionAbove();
        return;
    }

    private /* synthetic */ void lambda$new$25(CharSequence r2) {
        this.topCommentTextView.setText(r2);
        this.topCommentTextView.setSelection(r2.length(), r2.length());
    }

    private /* synthetic */ void lambda$new$26(CharSequence r1, Integer r2, Integer r3, Boolean r4) {
        this.topCommentTextView.setText(r1);
        this.topCommentTextView.setSelection(r1.length(), r1.length());
        onWriteButtonPressed();
    }

    private /* synthetic */ void lambda$new$27(Theme.ResourcesProvider r4, View r5) {
        if (this.topCommentTextView != null) goto L5;
        return;
    L5:
        MessagesController.getGlobalMainSettings().edit().putInt("aihintshown", 3).apply();
        AIEditorAlert r42 = new AIEditorAlert(getContext(), r4).setText(this.topCommentTextView.getText()).setOnUse(new ChatAttachAlert$$ExternalSyntheticLambda51(this));
        long r0 = this.dialogId;
        if (this.editingMessageObject == null) goto L8;
        boolean r52 = true;
    L9:
        r42.setOnSend(r0, r52, new ChatAttachAlert$$ExternalSyntheticLambda52(this)).show();
        return;
    L8:
        r52 = false;
        goto L9
    }

    private /* synthetic */ void lambda$new$28(View r1) {
        onWriteButtonPressed();
    }

    private /* synthetic */ boolean lambda$new$29(View r2, MotionEvent r3) {
        if (this.sendButtonItemOptions == null) goto L14;
        MessageSendPreview r0 = this.messageSendPreview;
        if (r0 != null) goto L7;
        return false;
    L7:
        if (r0.isShowing() == true) goto L9;
        return false;
    L9:
        if (r2.getParent() == null) goto L11;
        r2.getParent().requestDisallowInterceptTouchEvent(true);
    L11:
        this.messageSendPreview.dispatchCapturedTouchEvent(r3, this.sendButtonItemOptions);
        return false;
    L14:
        return false;
    }

    private /* synthetic */ void lambda$new$3(View r3) {
        ChatAttachAlertPhotoLayout r32 = this.photoLayout;
        if (r32 != null) goto L5;
        return;
    L5:
        boolean r33 = !r32.areLivePhotosEnabled();
        this.photoLayout.toggleLivePhotos(r33);
        updateMotionItem(true);
        showMotionHint(r33);
    }

    private /* synthetic */ void lambda$new$30(DialogInterface r1) {
        this.sendButtonItemOptions = null;
        this.messageSendPreview = null;
    }

    private /* synthetic */ void lambda$new$31(long r9, boolean r11, int r12, int r13) {
        AttachAlertLayout r0 = this.currentAttachLayout;
        if (r0 == this.photoLayout) goto L11;
        if (r0 == this.photoPreviewLayout) goto L11;
        if (r0.sendSelectedItems(r11, r12, r13, r9, isCaptionAbove()) == true) goto L10;
        this.allowPassConfirmationAlert = true;
        dismiss();
    L10:
        boolean r92 = false;
        ChatAttachAlert r02 = this;
    L12:
        MessageSendPreview r8 = r02.messageSendPreview;
        if (r8 == null) goto L16;
        r8.dismiss(!r92);
        r02.messageSendPreview = null;
        return;
    L16:
        return;
    L11:
        r02 = this;
        r92 = r02.sendPressed(r11, r12, r13, r9, isCaptionAbove());
        goto L12
    }

    private /* synthetic */ void lambda$new$32(BaseFragment r10, Theme.ResourcesProvider r11, View r12) {
        MessageSendPreview r122 = this.messageSendPreview;
        if (r122 == null) goto L6;
        long r0 = r122.getSelectedEffect();
    L5:
        long r6 = r0;
        ChatActivityEnterView.SendButton r123 = this.writeButton;
        this.effectId = r6;
        r123.setEffect(r6);
        forceKeyboardOnDismiss();
        if ((this.currentLimit - this.codepointCount) >= 0) goto L21;
        AndroidUtilities.shakeView(this.captionLimitView);
        AndroidUtilities.shakeView(this.topCaptionLimitView);
        this.writeButton.performHapticFeedback(3, 2);     // Catch: Exception -> L42
    L12:
        if (MessagesController.getInstance(this.currentAccount).premiumFeaturesBlocked() == false) goto L14;
    L16:
        MessageSendPreview r102 = this.messageSendPreview;
        if (r102 == null) goto L45;
        r102.dismiss(false);
        this.messageSendPreview = null;
        return;
    L45:
        return;
    L14:
        if (MessagesController.getInstance(this.currentAccount).captionLengthLimitPremium <= this.codepointCount) goto L16;
        showCaptionLimitBulletin(r10);
        goto L16
    L21:
        if (this.editingMessageObject != null) goto L27;
        BaseFragment r103 = this.baseFragment;
        if ((r103 instanceof ChatActivity) == false) goto L27;
        if (((ChatActivity) r103).isInScheduleMode() == false) goto L27;
        AlertsCreator.createScheduleDatePickerDialog(getContext(), ((ChatActivity) this.baseFragment).getDialogId(), new ChatAttachAlert$$ExternalSyntheticLambda68(this, r6), r11);
        ChatAttachAlert r2 = this;
    L40:
        r2.setCaptionAbove(false, false);
        return;
    L27:
        AttachAlertLayout r22 = this.currentAttachLayout;
        if (r22 != this.photoLayout) goto L30;
    L36:
        r2 = this;
        boolean r9 = r2.sendPressed(true, 0, 0, r6, isCaptionAbove());
    L37:
        MessageSendPreview r104 = r2.messageSendPreview;
        if (r104 == null) goto L40;
        r104.dismiss(!r9);
        r2.messageSendPreview = null;
        goto L40
    L30:
        if (r22 == this.photoPreviewLayout) goto L36;
        if (r22.sendSelectedItems(true, 0, 0, r6, isCaptionAbove()) == true) goto L35;
        this.allowPassConfirmationAlert = true;
        dismiss();
    L35:
        r2 = this;
        r9 = false;
        goto L37
    L6:
        r0 = 0;
        goto L5
    }

    private /* synthetic */ void lambda$new$33(MessageObject r3, MessagePreviewView.ToggleButton r4, View r5) {
        setCaptionAbove(!this.captionAbove);
        TLRPC.Message r52 = r3.messageOwner;
        boolean r1 = this.captionAbove;
        r52.invert_media = r1;
        r4.setState(!r1, true);
        this.messageSendPreview.changeMessage(r3);
        ChatAttachAlertPhotoLayout r32 = this.photoLayout;
        if (r32 == null) goto L7;
        MessagePreviewView.ToggleButton r33 = r32.captionItem;
        if (r33 == null) goto L7;
        r33.setState(!this.captionAbove, true);
    L7:
        this.messageSendPreview.scrollTo(!this.captionAbove);
    }

    private /* synthetic */ void lambda$new$34(boolean r10, int r11, int r12) {
        MessageSendPreview r0 = this.messageSendPreview;
        if (r0 == null) goto L6;
        long r02 = r0.getSelectedEffect();
    L5:
        long r6 = r02;
        ChatActivityEnterView.SendButton r03 = this.writeButton;
        this.effectId = r6;
        r03.setEffect(r6);
        AttachAlertLayout r2 = this.currentAttachLayout;
        if (r2 == this.photoLayout) goto L16;
        if (r2 == this.photoPreviewLayout) goto L16;
        if (r2.sendSelectedItems(r10, r11, r12, r6, isCaptionAbove()) == true) goto L15;
        dismiss();
    L15:
        boolean r102 = false;
        ChatAttachAlert r22 = this;
    L17:
        MessageSendPreview r9 = r22.messageSendPreview;
        if (r9 == null) goto L21;
        r9.dismiss(!r102);
        r22.messageSendPreview = null;
        return;
    L21:
        return;
    L16:
        r22 = this;
        r102 = r22.sendPressed(r10, r11, r12, r6, isCaptionAbove());
        goto L17
    L6:
        r02 = 0;
        goto L5
    }

    private /* synthetic */ void lambda$new$35(long r3, Theme.ResourcesProvider r5) {
        AlertsCreator.createScheduleDatePickerDialog(getContext(), r3, new ChatAttachAlert$$ExternalSyntheticLambda76(this), r5);
    }

    private /* synthetic */ void lambda$new$36(ChatActivity r8, MessageSuggestionParams r9) {
        r8.messageSuggestionParams = r9;
        boolean r7 = sendPressed(true, 0, 0, this.effectId, isCaptionAbove());
        MessageSendPreview r82 = this.messageSendPreview;
        if (r82 == null) goto L6;
        r82.dismiss(!r7);
        this.messageSendPreview = null;
        return;
    }

    private /* synthetic */ void lambda$new$37(long r11, ChatActivity r13, Theme.ResourcesProvider r14) {
        Context r1 = getContext();
        int r2 = this.currentAccount;
        MessageSuggestionParams r3 = r13.messageSuggestionParams;
        if (r3 == null) goto L5;
    L4:
        MessageSuggestionParams r5 = r3;
        new MessageSuggestionOfferSheet(r1, r2, r11, r5, r13, r14, 0, new ChatAttachAlert$$ExternalSyntheticLambda71(this, r13)).show();
        return;
    L5:
        r3 = MessageSuggestionParams.empty();
        goto L4
    }

    private /* synthetic */ void lambda$new$38() {
        MessageSendPreview r0 = this.messageSendPreview;
        if (r0 == null) goto L6;
        long r02 = r0.getSelectedEffect();
    L5:
        long r6 = r02;
        ChatActivityEnterView.SendButton r03 = this.writeButton;
        this.effectId = r6;
        r03.setEffect(r6);
        AttachAlertLayout r2 = this.currentAttachLayout;
        if (r2 != this.photoLayout) goto L10;
    L16:
        ChatAttachAlert r22 = this;
        boolean r04 = r22.sendPressed(false, 0, 0, r6, isCaptionAbove());
    L17:
        MessageSendPreview r9 = r22.messageSendPreview;
        if (r9 == null) goto L21;
        r9.dismiss(!r04);
        r22.messageSendPreview = null;
        return;
    L21:
        return;
    L10:
        if (r2 == this.photoPreviewLayout) goto L16;
        if (r2.sendSelectedItems(false, 0, 0, r6, isCaptionAbove()) == true) goto L15;
        dismiss();
    L15:
        r04 = false;
        r22 = this;
        goto L17
    L6:
        r02 = 0;
        goto L5
    }

    private /* synthetic */ void lambda$new$39(ArrayList r10, HashMap r11) {
        MessageSendPreview r0 = this.messageSendPreview;
        if (r0 == null) goto L6;
        long r02 = r0.getSelectedEffect();
    L5:
        long r6 = r02;
        ChatActivityEnterView.SendButton r03 = this.writeButton;
        this.effectId = r6;
        r03.setEffect(r6);
        int r04 = r10.size();
        int r1 = 0;
    L8:
        if (r1 >= r04) goto L17;
        Object r2 = r10.get(r1);
        r1 = r1 + 1;
        Object r22 = r11.get(r2);
        if ((r22 instanceof MediaController.PhotoEntry) == false) goto L8;
        MediaController.PhotoEntry r23 = (MediaController.PhotoEntry) r22;
        if (r23.isVideo == false) goto L8;
        r23.resetEdit();
        if (r23.editedInfo != null) goto L16;
        r23.editedInfo = new VideoEditedInfo();
    L16:
        VideoEditedInfo r3 = r23.editedInfo;
        r3.bitrate = -2;
        int r4 = r23.width;
        r3.resultWidth = r4;
        int r24 = r23.height;
        r3.resultHeight = r24;
        r3.originalWidth = r4;
        r3.originalHeight = r24;
        goto L8
    L17:
        boolean r9 = sendPressed(true, 0, 0, r6, isCaptionAbove());
        MessageSendPreview r102 = this.messageSendPreview;
        if (r102 == null) goto L28;
        r102.dismiss(!r9);
        this.messageSendPreview = null;
        return;
    L28:
        return;
    L6:
        r02 = 0;
        goto L5
    }

    private /* synthetic */ void lambda$new$4(View r1) {
        AttachAlertLayout r0 = this.currentAttachLayout;
        if (r0 == null) goto L6;
        r0.onMenuItemClick(40);
        return;
    }

    private /* synthetic */ void lambda$new$40(ActionBarMenuSubItem r5, Long r6, Runnable r7) {
        r7.run();
        this.photoLayout.setStarsPrice(r6.longValue());
        if (r6.longValue() <= 0) goto L6;
        r5.setText(LocaleController.getString(R.string.PaidMediaPriceButton));
        r5.setSubtext(LocaleController.formatPluralString("Stars", (int) r6.longValue(), new Object[0]));
        this.messageSendPreview.setStars(r6.longValue());
        return;
    L6:
        r5.setText(LocaleController.getString(R.string.PaidMediaButton));
        r5.setSubtext(null);
        this.messageSendPreview.setStars(0);
    }

    private /* synthetic */ void lambda$new$41(Context r7, ActionBarMenuSubItem r8, Theme.ResourcesProvider r9, View r10) {
        ChatAttachAlertPhotoLayout r102 = this.photoLayout;
        if (r102 != null) goto L5;
        return;
    L5:
        StarsIntroActivity.showMediaPriceSheet(r7, r102.getStarsPrice(), true, new ChatAttachAlert$$ExternalSyntheticLambda72(this, r8), r9);
    }

    private /* synthetic */ boolean lambda$new$42(Context r45, Theme.ResourcesProvider r46, BaseFragment r47, View r48) {
        if (this.dialogId != 0) goto L9;
        if ((this.baseFragment instanceof ChatActivity) == true) goto L9;
    L350:
        return false;
    L9:
        if ((this.currentLimit - this.codepointCount) < 0) goto L350;
        if (this.animatorEphemeralMessageVisibility.getValue() == true) goto L350;
        long r3 = this.dialogId;
        BaseFragment r0 = this.baseFragment;
        if ((r0 instanceof ChatActivity) == false) goto L22;
        ChatActivity r02 = (ChatActivity) r0;
        r02.getCurrentChat();
        TLRPC.User r32 = r02.getCurrentUser();
        MessageObject r4 = r02.getReplyMessage();
        MessageObject r6 = r02.getReplyTopMessage();
        if (r02.isInScheduleMode() == false) goto L18;
    L21:
        return false;
    L18:
        if (r02.getChatMode() == 5) goto L21;
        ChatActivity r14 = r02;
        TLRPC.User r15 = r32;
        MessageObject r62 = r4;
        r3 = r02.getDialogId();
        MessageObject r7 = r6;
    L23:
        MessageSendPreview r03 = this.messageSendPreview;
        if (r03 == null) goto L26;
        r03.dismiss();
    L26:
        AnonymousClass25 r04 = new AnonymousClass25(this, r45, r46);
        this.messageSendPreview = r04;
        r04.setOnDismissListener(new ChatAttachAlert$$ExternalSyntheticLambda55(this));
        this.messageSendPreview.setSendButton(this.writeButton, false, new ChatAttachAlert$$ExternalSyntheticLambda56(this, r47, r46));
        ArrayList<MessageObject> r10 = new ArrayList();
        AttachAlertLayout r05 = this.currentAttachLayout;
        ChatAttachAlertPhotoLayout r8 = this.photoLayout;
        if (r05 != r8) goto L29;
    L30:
        ChatActivity r24 = r14;
        HashMap<Object, Object> r82 = r8.getSelectedPhotos();
        ArrayList<Object> r11 = this.photoLayout.getSelectedPhotosOrder();
        if (r82.isEmpty() == true) goto L247;
        int r12 = (int) Math.ceil(r82.size() / 10.0f);
        MessageObject r06 = null;
        int r13 = 0;
        int r18 = 0;
        boolean r25 = false;
        boolean r26 = false;
    L104:
        if (r13 >= r12) goto L245;
        int r28 = r13 * 10;
        MessageObject r29 = r06;
        MessageObject r21 = r7;
        int r72 = Math.min(10, r82.size() - r28);
        long r30 = r3;
        long r2 = Utilities.random.nextLong();
        int r07 = r18;
        int r42 = 0;
    L106:
        if (r42 >= r72) goto L244;
        int r27 = r42;
        int r43 = r28 + r27;
        int r322 = r72;
        if (r43 < r11.size()) goto L110;
        HashMap<Object, Object> r33 = r82;
        long r34 = r30;
        long r302 = r2;
    L243:
        r42 = r27 + 1;
        r2 = r302;
        r72 = r322;
        r82 = r33;
        r30 = r34;
        goto L106
    L110:
        MediaController.PhotoEntry r44 = (MediaController.PhotoEntry) r82.get(r11.get(r43));
        TLRPC.TL_message r73 = new TLRPC.TL_message();
        int r252 = r07 + 1;
        r73.id = r07;
        r33 = r82;
        r73.out = true;
        r73.from_id = MessagesController.getInstance(this.currentAccount).getPeer(UserConfig.getInstance(this.currentAccount).getClientUserId());
        long r83 = r30;
        r73.peer_id = MessagesController.getInstance(this.currentAccount).getPeer(r83);
        boolean r08 = r44.isVideo;
        if (r08 == true) goto L115;
        String r09 = r44.imagePath;
        if (r09 == null) goto L115;
        r73.attachPath = r09;
    L118:
        if (r322 <= 0) goto L120;
        r73.grouped_id = r2;
    L120:
        r302 = r2;
        int r22 = r44.width;
        int r35 = r44.height;
        int r010 = r44.orientation;
        if (r08 == false) goto L202;
        int r262 = r22;
        if (r44.videoOrientation == (-1)) goto L372;
        int r40 = r35;
    L201:
        r010 = r44.videoOrientation;
    L204:
        if (((r010 / 90) % 2) == 0) goto L206;
        int r011 = r262;
        int r23 = r40;
    L208:
        if (r44.isLivePhoto() == false) goto L210;
        TLRPC.TL_messageMediaPhoto r36 = new TLRPC.TL_messageMediaPhoto();
        r73.media = r36;
        r34 = r83;
        r36.live_photo = true;
        r36.photo = new TLRPC.TL_photo();
        TLRPC.TL_photoSize r37 = new TLRPC.TL_photoSize();
        r37.w = r23;
        r37.h = r011;
        r37.location = new TLRPC.TL_fileLocationToBeDeprecated();
        r73.media.photo.sizes.add(r37);
        r73.media.document = new TLRPC.TL_document();
        r73.media.document.mime_type = MimeTypeMap.getSingleton().getExtensionFromMimeType(r73.attachPath);
        TLRPC.TL_documentAttributeVideo r38 = new TLRPC.TL_documentAttributeVideo();
        r38.w = r23;
        r38.h = r011;
        r38.duration = r44.duration;
        r73.media.document.attributes.add(r38);
    L214:
        r73.media.spoiler = r44.hasSpoiler;
        CharSequence r012 = r44.caption;
        if (r012 != null) goto L217;
        String r013 = _UrlKt.FRAGMENT_ENCODE_SET;
    L218:
        r73.message = r013;
        if (TextUtils.isEmpty(r013) == false) goto L223;
        if (r13 != 0) goto L223;
        if (r27 != 0) goto L223;
        CharSequence[] r210 = {getCommentView().getText()};
        MessageObject.addLinks(true, r210[0]);
        r73.entities = MediaDataController.getInstance(this.currentAccount).getEntities(r210, true);
        r73.message = r210[0].toString();
    L223:
        if (r13 != 0) goto L231;
        if (r62 == null) goto L231;
        if (r62.isTopicMainMessage == true) goto L231;
        TLRPC.TL_messageReplyHeader r014 = new TLRPC.TL_messageReplyHeader();
        if (r21 == null) goto L230;
        r014.flags |= 2;
        r014.reply_to_top_id = r21.getId();
    L230:
        r014.flags |= 16;
        r014.reply_to_msg_id = r62.getId();
        r73.reply_to = r014;
    L231:
        MessageObject r015 = new MessageObject(this.currentAccount, r73, true, false);
        if (r13 != 0) goto L237;
        if (r62 == null) goto L237;
        if (r62.isTopicMainMessage == true) goto L237;
        r015.replyMessageObject = r62;
    L237:
        r015.sendPreviewEntry = r44;
        r015.sendPreview = true;
        r015.notime = true;
        r015.isOutOwnerCached = Boolean.TRUE;
        r10.add(r015);
        if (r29 == null) goto L240;
    L242:
        r07 = r252;
        r25 = true;
        r26 = true;
        goto L243
    L240:
        if (TextUtils.isEmpty(r73.message) == true) goto L242;
        r29 = r015;
        goto L242
    L217:
        r013 = r012.toString();
        goto L218
    L210:
        r34 = r83;
        if (r44.isVideo == false) goto L213;
        TLRPC.TL_messageMediaDocument r39 = new TLRPC.TL_messageMediaDocument();
        r73.media = r39;
        r39.document = new TLRPC.TL_document();
        r73.media.document.mime_type = MimeTypeMap.getSingleton().getExtensionFromMimeType(r73.attachPath);
        TLRPC.TL_documentAttributeVideo r310 = new TLRPC.TL_documentAttributeVideo();
        r310.w = r23;
        r310.h = r011;
        r310.duration = r44.duration;
        r73.media.document.attributes.add(r310);
        goto L214
    L213:
        TLRPC.TL_messageMediaPhoto r311 = new TLRPC.TL_messageMediaPhoto();
        r73.media = r311;
        r311.photo = new TLRPC.TL_photo();
        TLRPC.TL_photoSize r312 = new TLRPC.TL_photoSize();
        r312.w = r23;
        r312.h = r011;
        r312.location = new TLRPC.TL_fileLocationToBeDeprecated();
        r73.media.photo.sizes.add(r312);
        goto L214
    L206:
        r23 = r262;
        r011 = r40;
        goto L208
    L372:
        MediaMetadataRetriever r342 = new MediaMetadataRetriever();     // Catch: Throwable -> L177 Exception -> L179
        if (r44.isLivePhoto() == false) goto L153;
        r40 = r35;
        if (r44.livePhotoVideoOffset <= 0) goto L152;
        File r016 = new File(r44.path);     // Catch: Throwable -> L145 Exception -> L149
        ParcelFileDescriptor r211 = ParcelFileDescriptor.open(r016, 268435456);     // Catch: Throwable -> L145 Exception -> L149
        ParcelFileDescriptor r41 = r211;
        r342.setDataSource(r211.getFileDescriptor(), r44.livePhotoVideoOffset, r016.length() - r44.livePhotoVideoOffset);     // Catch: Throwable -> L135 Exception -> L138
        MediaMetadataRetriever r212 = r342;
    L362:
        r44.videoOrientation = Integer.parseInt(r212.extractMetadata(24));     // Catch: Throwable -> L167 Exception -> L169
        r212.release();     // Catch: IOException -> L160
    L162:
        if (r41 == null) goto L201;
    L354:
        r41.close();     // Catch: IOException -> L165
    L165:
        e = move-exception;
        FileLog.e(e);
    L160:
        e = move-exception;
        FileLog.e(e);
        goto L162
    L169:
        e = e;
    L140:
        int r313 = 0;
    L181:
        r44.videoOrientation = r313;     // Catch: Throwable -> L167
        FileLog.e(e);     // Catch: Throwable -> L167
        if (r212 != null) goto L352;
    L187:
        if (r41 == null) goto L201;
    L352:
        r212.release();     // Catch: IOException -> L185
    L185:
        e = move-exception;
        FileLog.e(e);
    L167:
        Throwable th = th;
    L137:
        Throwable r1 = th;
        MediaMetadataRetriever r132 = r212;
        ParcelFileDescriptor r19 = r41;
    L189:
        if (r132 != null) goto L360;
    L194:
        if (r19 == null) goto L398;
        r19.close();     // Catch: IOException -> L197
        throw r1;
    L197:
        e = move-exception;
        FileLog.e(e);
        throw r1;
    L398:
        throw r1;
    L360:
        r132.release();     // Catch: IOException -> L192
    L192:
        e = move-exception;
        FileLog.e(e);
        goto L194
    L138:
        e = e;
    L139:
        r212 = r342;
    L135:
        th = th;
    L136:
        r212 = r342;
        goto L137
    L143:
        e = e;
        r41 = r211;
    L141:
        th = th;
        r41 = r211;
    L152:
        r212 = r342;
        r212.setDataSource(r44.path);     // Catch: Throwable -> L171 Exception -> L173
        r41 = null;
        goto L362
    L173:
        e = e;
    L151:
        r313 = 0;
        r41 = null;
    L171:
        th = th;
    L147:
        r1 = th;
        r132 = r212;
    L148:
        r19 = null;
    L149:
        e = e;
    L150:
        r212 = r342;
        goto L151
    L153:
        r40 = r35;
        goto L152
    L175:
        e = e;
        r40 = r35;
    L145:
        th = th;
        r212 = r342;
        goto L147
    L179:
        e = e;
        r40 = r35;
        r212 = null;
    L177:
        th = move-exception;
        r1 = th;
        r132 = null;
        goto L148
    L202:
        r262 = r22;
        r40 = r35;
    L115:
        String r017 = r44.path;
        if (r017 == null) goto L118;
        r73.attachPath = r017;
        goto L118
    L244:
        r13 = r13 + 1;
        r18 = r07;
        r7 = r21;
        r06 = r29;
        r3 = r30;
        goto L104
    L245:
        MessageObject r292 = r06;
    L246:
        long r343 = r3;
        MessageObject r018 = r292;
    L250:
        if (r10.isEmpty() == false) goto L253;
        return false;
    L253:
        ItemOptions r9 = ItemOptions.makeOptions(this.containerView, r46, this.writeButton);
        this.sendButtonItemOptions = r9;
        if (r018 == null) goto L260;
        AttachAlertLayout r213 = this.currentAttachLayout;
        if (r213 != this.photoLayout) goto L258;
    L261:
        long r112 = r343;
        MessagePreviewView.ToggleButton r214 = new MessagePreviewView.ToggleButton(r45, R.raw.position_below, LocaleController.getString(R.string.CaptionAbove), R.raw.position_above, LocaleController.getString(R.string.CaptionBelow), r46);
        TLRPC.Message r314 = r018.messageOwner;
        boolean r49 = this.captionAbove;
        r314.invert_media = r49;
        r214.setState(!r49, false);
        r214.setOnClickListener(new ChatAttachAlert$$ExternalSyntheticLambda57(this, r018, r214));
        r9.addView(r214);
        if (this.editingMessageObject != null) goto L265;
        r9.addGap();
    L265:
        if (TextUtils.isEmpty(getCommentTextView().getText()) == true) goto L267;
        Theme.ResourcesProvider r5 = r46;
        BaseFragment r74 = r47;
        Context r63 = r45;
        r9.addView(new AnonymousClass26(this, r45, true, false, r5, r47), LayoutHelper.createLinear(-1, 48));
    L268:
        boolean r84 = UserObject.isUserSelf(r15);
        if (this.editingMessageObject != null) goto L283;
        if (r24 != null) goto L272;
    L273:
        if (r24 == null) goto L277;
        if (r24.canScheduleMessage() == false) goto L277;
    L278:
        int r019 = R.drawable.msg_calendar2;
        if (r84 == false) goto L281;
        int r215 = R.string.SetReminder;
    L282:
        r9.add(r019, LocaleController.getString(r215), new ChatAttachAlert$$ExternalSyntheticLambda58(this, r112, r5));
        goto L283
    L281:
        r215 = R.string.ScheduleMessage;
    L277:
        if (this.currentAttachLayout.canScheduleMessages() == false) goto L283;
    L272:
        if (ChatObject.isMonoForum(r24.getCurrentChat()) == false) goto L273;
    L283:
        AttachAlertLayout r020 = this.currentAttachLayout;
        if (r020 == this.photoLayout) goto L290;
        if (r020 == this.photoPreviewLayout) goto L290;
    L288:
        ChatActivity r410 = r24;
    L296:
        if (this.editingMessageObject != null) goto L299;
        if (r84 == true) goto L299;
        r9.add(R.drawable.input_notify_off, LocaleController.getString(R.string.SendWithoutSound), new ChatAttachAlert$$ExternalSyntheticLambda60(this));
    L299:
        AttachAlertLayout r021 = this.currentAttachLayout;
        ChatAttachAlertPhotoLayout r216 = this.photoLayout;
        if (r021 != r216) goto L302;
    L305:
        boolean r022 = true;
    L306:
        if (r022 == false) goto L308;
        HashMap<Object, Object> r217 = r216.getSelectedPhotos();
    L309:
        if (r022 == false) goto L311;
        ArrayList<Object> r023 = this.photoLayout.getSelectedPhotosOrder();
    L312:
        if (r023 == null) goto L320;
        int r315 = r023.size();
        int r85 = 0;
    L314:
        if (r85 >= r315) goto L320;
        Object r133 = r023.get(r85);
        r85 = r85 + 1;
        Object r134 = r217.get(r133);
        if ((r134 instanceof MediaController.PhotoEntry) == false) goto L314;
        if (((MediaController.PhotoEntry) r134).isVideo == false) goto L314;
        boolean r316 = true;
    L322:
        if (this.editingMessageObject != null) goto L326;
        if (r316 == false) goto L326;
        r9.add(R.drawable.msg_filehq, LocaleController.getString(R.string.SendWithoutCompression), new ChatAttachAlert$$ExternalSyntheticLambda61(this, r023, r217));
    L326:
        if (this.editingMessageObject != null) goto L340;
        if (r25 == false) goto L340;
        if (r410 == null) goto L340;
        if (ChatObject.isChannelAndNotMegaGroup(r410.getCurrentChat()) == false) goto L340;
        if (r410.getCurrentChatInfo() == null) goto L340;
        if (r410.getCurrentChatInfo().paid_media_allowed == false) goto L340;
        ActionBarMenuSubItem r024 = r9.add(R.drawable.menu_feature_paid, LocaleController.getString(R.string.PaidMediaButton), null).getLast();
        r024.setOnClickListener(new ChatAttachAlert$$ExternalSyntheticLambda62(this, r63, r024, r5));
        long r218 = this.photoLayout.getStarsPrice();
        if (r218 <= 0) goto L338;
        r024.setText(LocaleController.getString(R.string.PaidMediaPriceButton));
        r024.setSubtext(LocaleController.formatPluralString("Stars", (int) r218, new Object[0]));
    L339:
        this.messageSendPreview.setStars(r218);
        goto L340
    L338:
        r024.setText(LocaleController.getString(R.string.PaidMediaButton));
        r024.setSubtext(null);
    L340:
        r9.setupSelectors();
        this.messageSendPreview.setItemOptions(r9);
        this.messageSendPreview.setMessageObjects(r10);
        if (this.editingMessageObject == null) goto L343;
    L346:
        this.messageSendPreview.show();
        r48.performHapticFeedback(3, 2);     // Catch: Exception -> L351
        return true;
    L400:
        return true;
    L343:
        if (r112 < 0) goto L346;
        if (r26 == false) goto L346;
        this.messageSendPreview.allowEffectSelector(r74);
        this.messageSendPreview.setEffectId(this.effectId);
    L320:
        r316 = false;
        goto L322
    L311:
        r023 = null;
        goto L312
    L308:
        r217 = null;
        goto L309
    L302:
        if (r021 == this.photoPreviewLayout) goto L305;
        r022 = false;
    L290:
        if (r020.getSelectedItemsCount() != 1) goto L288;
        if (r24 == null) goto L288;
        if (ChatObject.isMonoForum(r24.getCurrentChat()) == false) goto L288;
        r410 = r24;
        r9.add(R.drawable.input_suggest_paid_24, LocaleController.getString(R.string.PostSuggestionsSendWithOffer), new ChatAttachAlert$$ExternalSyntheticLambda59(this, r112, r410, r5));
        goto L296
    L267:
        r63 = r45;
        r5 = r46;
        r74 = r47;
        goto L268
    L258:
        if (r213 == this.photoPreviewLayout) goto L261;
    L260:
        r112 = r343;
        goto L265
    L247:
        r25 = false;
        r26 = false;
        r292 = null;
        goto L246
    L29:
        if (r05 == this.photoPreviewLayout) goto L30;
        if (r05 != this.contactsLayout) goto L50;
        if (TextUtils.isEmpty(getCommentView().getText()) == true) goto L36;
        TLRPC.TL_message r025 = new TLRPC.TL_message();
        r025.id = 0;
        r025.out = true;
        r025.from_id = MessagesController.getInstance(this.currentAccount).getPeer(UserConfig.getInstance(this.currentAccount).getClientUserId());
        r025.peer_id = MessagesController.getInstance(this.currentAccount).getPeer(r3);
        CharSequence[] r86 = {getCommentView().getText()};
        MessageObject.addLinks(true, r86[0]);
        r025.entities = MediaDataController.getInstance(this.currentAccount).getEntities(r86, true);
        r025.message = r86[0].toString();
        MessageObject r64 = new MessageObject(this.currentAccount, r025, true, false);
        r64.sendPreview = true;
        r64.notime = true;
        r64.isOutOwnerCached = Boolean.TRUE;
        r10.add(r64);
        int r026 = 1;
        boolean r65 = true;
    L37:
        ArrayList<TLRPC.User> r75 = this.contactsLayout.getSelected();
        int r87 = 0;
    L39:
        if (r87 >= r75.size()) goto L47;
        TLRPC.User r66 = r75.get(r87);
        TLRPC.TL_message r113 = new TLRPC.TL_message();
        int r182 = r026 + 1;
        r113.id = r026;
        r113.out = true;
        ArrayList<TLRPC.User> r219 = r75;
        int r242 = r87;
        r113.from_id = MessagesController.getInstance(this.currentAccount).getPeer(UserConfig.getInstance(this.currentAccount).getClientUserId());
        r113.peer_id = MessagesController.getInstance(this.currentAccount).getPeer(r3);
        TLRPC.TL_messageMediaContact r027 = new TLRPC.TL_messageMediaContact();
        r113.media = r027;
        r027.phone_number = r66.phone;
        r027.first_name = r66.first_name;
        r027.last_name = r66.last_name;
        if (r66.restriction_reason.isEmpty() == false) goto L43;
    L45:
        r113.media.vcard = _UrlKt.FRAGMENT_ENCODE_SET;
    L46:
        r113.media.user_id = r66.id;
        MessageObject r028 = new MessageObject(this.currentAccount, r113, true, false);
        r028.sendPreview = true;
        r028.notime = true;
        r028.isOutOwnerCached = Boolean.TRUE;
        r10.add(r028);
        r87 = r242 + 1;
        r026 = r182;
        r75 = r219;
        r65 = true;
        goto L39
    L43:
        if (r66.restriction_reason.get(0).text.startsWith("BEGIN:VCARD") == false) goto L45;
        r113.media.vcard = r66.restriction_reason.get(0).text;
        goto L46
    L47:
        r343 = r3;
        r26 = r65;
        r24 = r14;
        r018 = null;
    L48:
        r25 = false;
        goto L250
    L36:
        r026 = 0;
        r65 = false;
        goto L37
    L50:
        if (r05 != this.documentLayout) goto L78;
        r018 = null;
        boolean r67 = false;
        int r76 = 0;
        int r88 = 0;
    L53:
        if (r76 >= this.documentLayout.selectedFilesOrder.size()) goto L77;
        String r114 = this.documentLayout.selectedFilesOrder.get(r76);
        if (r114 == null) goto L63;
        int r122 = r114.lastIndexOf(File.separator);
        if (r122 >= 0) goto L60;
        String r123 = r114;
    L62:
        if (TextUtils.isEmpty(r123) == true) goto L63;
        TLRPC.TL_message r68 = new TLRPC.TL_message();
        int r135 = r88 + 1;
        r68.id = r88;
        r68.out = true;
        MessageObject r183 = r018;
        ChatActivity r243 = r14;
        r68.from_id = MessagesController.getInstance(this.currentAccount).getPeer(UserConfig.getInstance(this.currentAccount).getClientUserId());
        r68.peer_id = MessagesController.getInstance(this.currentAccount).getPeer(r3);
        TLRPC.TL_messageMediaDocument r029 = new TLRPC.TL_messageMediaDocument();
        r68.media = r029;
        r68.attachPath = r114;
        r029.document = new TLRPC.TL_document();
        TLRPC.Document r030 = r68.media.document;
        r030.file_name = r123;
        r030.size = new File(r114).length();
        if (TextUtils.isEmpty(r68.message) == false) goto L68;
        if (r76 != 0) goto L68;
        boolean r124 = true;
        boolean r115 = false;
        CharSequence[] r89 = {getCommentView().getText()};
        r68.entities = MediaDataController.getInstance(this.currentAccount).getEntities(r89, true);
        r68.message = r89[0].toString();
    L69:
        r018 = new MessageObject(this.currentAccount, r68, r124, r115);
        r018.attachPathExists = r124;
        r018.sendPreview = r124;
        r018.notime = r124;
        r018.isOutOwnerCached = Boolean.TRUE;
        r10.add(r018);
        if (r76 != 0) goto L75;
        if (r183 != null) goto L75;
        if (TextUtils.isEmpty(r68.message) == true) goto L75;
    L74:
        r88 = r135;
        r67 = true;
    L76:
        r76 = r76 + 1;
        r14 = r243;
    L75:
        r018 = r183;
    L68:
        r115 = false;
        r124 = true;
        goto L69
    L60:
        r123 = r114.substring(r122 + 1);
    L63:
        r243 = r14;
        goto L76
    L77:
        r24 = r14;
        r343 = r3;
        r26 = r67;
        goto L48
    L78:
        r24 = r14;
        ChatAttachAlertAudioLayout r69 = this.audioLayout;
        if (r05 != r69) goto L100;
        r10.addAll(r69.getSelected());
        if (r10.isEmpty() == true) goto L85;
        MessageObject r031 = r10.get(0);
        CharSequence[] r810 = {getCommentView().getText()};
        MessageObject.addLinks(true, r810[0]);
        r031.messageOwner.entities = MediaDataController.getInstance(this.currentAccount).getEntities(r810, true);
        r031.messageOwner.message = r810[0].toString();
        if (TextUtils.isEmpty(r031.messageOwner.message) == true) goto L85;
        r031.generateCaption();
    L87:
        if (r10.size() <= 1) goto L99;
        int r610 = 0;
    L90:
        if (r610 >= Math.ceil(r10.size() / 10.0f)) goto L99;
        int r811 = r610 * 10;
        int r77 = Math.min(10, r10.size() - r811);
        long r116 = Utilities.random.nextLong();
        int r136 = 0;
    L92:
        if (r136 >= r77) goto L98;
        int r142 = r811 + r136;
        MessageObject r253 = r031;
        if (r142 >= r10.size()) goto L97;
        r10.get(r142).messageOwner.grouped_id = r116;
    L97:
        r136 = r136 + 1;
        r031 = r253;
        goto L92
    L98:
        r610 = r610 + 1;
    L99:
        r343 = r3;
        r018 = r031;
        r25 = false;
        r26 = true;
    L85:
        r031 = null;
        goto L87
    L100:
        r343 = r3;
        r018 = null;
        r25 = false;
        r26 = false;
        goto L250
    L22:
        r15 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r3));
        r62 = null;
        r7 = null;
        r14 = null;
        goto L23
    }

    private /* synthetic */ int lambda$new$43(Theme.ResourcesProvider r3, boolean r4) {
        if (LiteMode.isEnabled(262144) == false) goto L5;
        float r0 = 0.85f;
    L6:
        if (r4 == false) goto L8;
        int r42 = Theme.key_windowBackgroundGray;
    L9:
        int r43 = Theme.getColor(r42, r3);
        int r32 = Theme.getColor(Theme.key_windowBackgroundWhite, r3);
        if (this.hasOverridenWebviewBackgroundColor == false) goto L14;
        return ColorUtils.blendARGB(r32, this.overridenWebviewBackgroundColor, 0.75f);
    L14:
        return BlurredBackgroundProviderImpl.solveSrcColor(r43, r32, r0);
    L8:
        r42 = Theme.key_dialogBackgroundGray;
        goto L9
    L5:
        r0 = 0.76f;
        goto L6
    }

    private /* synthetic */ int lambda$new$44(Theme.ResourcesProvider r1, boolean r2) {
        if (this.hasOverridenWebviewBackgroundColor == false) goto L6;
        return 0;
    L6:
        if (r2 == false) goto L9;
        return 687865855;
    L9:
        return -1;
    }

    private /* synthetic */ int lambda$new$45(Theme.ResourcesProvider r1, boolean r2) {
        if (this.hasOverridenWebviewBackgroundColor == false) goto L6;
        return 0;
    L6:
        if (r2 == false) goto L9;
        return 352321535;
    L9:
        return -1;
    }

    private /* synthetic */ int lambda$new$46(Theme.ResourcesProvider r2, boolean r3) {
        if (this.hasOverridenWebviewBackgroundColor == true) goto L5;
        if (r3 == false) goto L12;
        return 0;
    L12:
        return 536870912;
    L5:
        if (AndroidUtilities.computePerceivedBrightness(this.overridenWebviewBackgroundColor) <= 0.72f) goto L7;
        return 536870912;
    L7:
        return 1090519039;
    }

    private /* synthetic */ void lambda$new$5(boolean r7, View r8) {
        if (this.avatarPicker == 0) goto L6;
        this.delegate.openAvatarsSearch();
        dismiss();
        return;
    L6:
        HashMap r1 = new HashMap();
        ArrayList r2 = new ArrayList();
        PhotoPickerSearchActivity r0 = new PhotoPickerSearchActivity(r1, r2, 0, true, (ChatActivity) this.baseFragment);
        r0.setDelegate(new AnonymousClass10(this, r1, r2));
        r0.setMaxSelectedPhotos(this.maxSelectedPhotos, this.allowOrder);
        BaseFragment r82 = this.baseFragment;
        if (r7 == false) goto L9;
        r82.showAsSheet(r0);
    L10:
        dismiss();
        return;
    L9:
        r82.presentFragment(r0);
        goto L10
    }

    private /* synthetic */ void lambda$new$6(Theme.ResourcesProvider r19, View r20) {
        this.optionsItem.toggleSubMenu();
        PhotoViewer.getInstance().setParentActivity(this.baseFragment, r19);
        PhotoViewer.getInstance().setParentAlert(this);
        PhotoViewer.getInstance().setMaxSelectedPhotos(this.maxSelectedPhotos, this.allowOrder);
        if (this.delegate.needEnterComment() == true) goto L5;
        AndroidUtilities.hideKeyboard(this.baseFragment.getFragmentView().findFocus());
        AndroidUtilities.hideKeyboard(getContainer().findFocus());
    L5:
        File r2 = StoryEntry.makeCacheFile(this.currentAccount, "webp");
        Point r0 = AndroidUtilities.displaySize;
        int r3 = r0.x;
        int r02 = r0.y;
        if (r3 > 1080) goto L8;
        if (r02 > 1080) goto L8;
    L9:
        Bitmap r32 = Bitmap.createBitmap(r3, r02, Bitmap.Config.ARGB_8888);
        r32.compress(Bitmap.CompressFormat.WEBP, 100, new FileOutputStream(r2));     // Catch: Throwable -> L12
    L14:
        r32.recycle();
        ArrayList<Object> r5 = new ArrayList();
        MediaController.PhotoEntry r6 = new MediaController.PhotoEntry(0, 0, 0, r2.getAbsolutePath(), 0, false, 0, 0, 0);
        r5.add(r6);
        PhotoViewer r4 = PhotoViewer.getInstance();
        AnonymousClass11 r9 = new AnonymousClass11(this, r6);
        BaseFragment r03 = this.baseFragment;
        if ((r03 instanceof ChatActivity) == false) goto L17;
        ChatActivity r10 = (ChatActivity) r03;
    L18:
        r4.openPhotoForSelect(r5, 0, 11, false, r9, r10);
        if (this.isStickerMode == false) goto L24;
        PhotoViewer.getInstance().enableStickerMode(null, null, true, this.customStickerHandler);
        return;
    L24:
        return;
    L17:
        r10 = null;
    L12:
        th = move-exception;
        FileLog.e(th);
    L8:
        float r42 = Math.min(r3, r02) / 1080.0f;
        r3 = (int) (r3 * r42);
        r02 = (int) (r02 * r42);
        goto L9
    }

    private /* synthetic */ void lambda$new$7(View r1) {
        this.optionsItem.toggleSubMenu();
    }

    private /* synthetic */ void lambda$new$8(View r2) {
        if (this.currentAttachLayout == this.photoPreviewLayout) goto L5;
        boolean r22 = true;
    L6:
        updatePhotoPreview(r22);
        return;
    L5:
        r22 = false;
        goto L6
    }

    private /* synthetic */ void lambda$new$9(TLRPC.MessageMedia r8, int r9, boolean r10, int r11, long r12) {
        ((ChatActivity) this.baseFragment).didSelectLocation(r8, r9, r10, r11, r12);
    }

    private /* synthetic */ void lambda$onCustomOpenAnimation$62(ValueAnimator r1) {
        this.navigationBarAlpha = ((Float) r1.getAnimatedValue()).floatValue();
        BottomSheet.ContainerView r0 = this.container;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    private /* synthetic */ void lambda$onCustomOpenAnimation$63(AnimationNotificationsLocker r2, BottomSheet.BottomSheetDelegateInterface r3) {
        this.currentSheetAnimation = null;
        this.appearSpringAnimation = null;
        r2.unlock();
        this.currentSheetAnimationType = 0;
        if (r3 == null) goto L6;
        r3.onOpenAnimationEnd();
    L6:
        if (this.useHardwareLayer == false) goto L9;
        this.container.setLayerType(0, null);
    L9:
        if (this.isFullscreen == false) goto L11;
        WindowManager.LayoutParams r22 = getWindow().getAttributes();
        r22.flags &= -1025;
        getWindow().setAttributes(r22);
    L11:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{512});
    }

    private /* synthetic */ void lambda$onCustomOpenAnimation$64(Runnable r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
        AnimatorSet r0 = this.currentSheetAnimation;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isRunning() == true) goto L9;
        r1.run();
        return;
    }

    private /* synthetic */ void lambda$onCustomOpenAnimation$65(ValueAnimator r1) {
        setNavBarAlpha(((Float) r1.getAnimatedValue()).floatValue());
    }

    private /* synthetic */ void lambda$onLongClickBotButton$49(TLRPC.TL_attachMenuBot r5) {
        MediaDataController.getInstance(this.currentAccount).loadAttachMenuBots(false, true);
        if (this.currentAttachLayout != this.botAttachLayouts.get(r5.bot_id)) goto L6;
        showLayout(this.photoLayout);
        return;
    }

    private /* synthetic */ void lambda$onLongClickBotButton$50(TLRPC.TL_attachMenuBot r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new ChatAttachAlert$$ExternalSyntheticLambda83(this, r1));
    }

    private /* synthetic */ void lambda$onLongClickBotButton$51(TLRPC.TL_attachMenuBot r1, TLRPC.User r2, AlertDialog r3, int r4) {
        if (r1 == null) goto L5;
        TLRPC.TL_messages_toggleBotInAttachMenu r32 = new TLRPC.TL_messages_toggleBotInAttachMenu();
        r32.bot = MessagesController.getInstance(this.currentAccount).getInputUser(r2);
        r32.enabled = false;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r32, new ChatAttachAlert$$ExternalSyntheticLambda81(this, r1), 66);
        return;
    L5:
        MediaDataController.getInstance(this.currentAccount).removeInline(r2.id);
    }

    private /* synthetic */ void lambda$onWriteButtonPressed$47(boolean r8, int r9, int r10) {
        AttachAlertLayout r0 = this.currentAttachLayout;
        if (r0 == this.photoLayout) goto L11;
        if (r0 == this.photoPreviewLayout) goto L11;
        if (r0.sendSelectedItems(r8, r9, r10, 0, isCaptionAbove()) == true) goto L13;
        this.allowPassConfirmationAlert = true;
        dismiss();
        return;
    L13:
        return;
    L11:
        sendPressed(r8, r9, 0, this.effectId, isCaptionAbove());
    }

    private /* synthetic */ void lambda$openAttachLayoutForType$53(TLRPC.MessageMedia r8, int r9, boolean r10, int r11, long r12) {
        ((ChatActivity) this.baseFragment).didSelectLocation(r8, r9, r10, r11, r12);
    }

    private /* synthetic */ void lambda$openAudioLayout$59(ArrayList r13, CharSequence r14, boolean r15, int r16, int r17, long r18, boolean r20, long r21) {
        ChatAttachAlertAudioLayout.AudioSelectDelegate r0 = this.audioSelectDelegate;
        if (r0 == null) goto L6;
        r0.didSelectAudio(r13, r14, r15, r16, r17, r18, r20, r21);
        return;
    L6:
        BaseFragment r02 = this.baseFragment;
        if (r02 != null) goto L9;
    L12:
        ChatAttachViewDelegate r1 = this.delegate;
        if (r1 == null) goto L16;
        r1.sendAudio(r13, r14, r15, r16, r17, r18, r20, r21);
        return;
    L16:
        return;
    L9:
        if ((r02 instanceof ChatActivity) == false) goto L12;
        ((ChatActivity) r02).sendAudio(r13, r14, r15, r16, r17, r18, r20, r21);
    }

    private /* synthetic */ void lambda$openColorsLayout$60(Object r1) {
        ChatAttachViewDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onWallpaperSelected(r1);
        return;
    }

    private /* synthetic */ void lambda$sendPressed$52(boolean r14, int r15, int r16, long r17, boolean r19, Long r20) {
        setButtonPressed(true);
        this.delegate.didPressedButton(7, true, r14, r15, r16, r17, r19, false, r20.longValue());
    }

    private /* synthetic */ void lambda$setCaptionAbove$78(ValueAnimator r1) {
        updatedTopCaptionHeight();
    }

    private /* synthetic */ void lambda$setCaptionAbove$79(boolean r1, boolean r2) {
        if (r1 == false) goto L4;
        if (r2 == false) goto L4;
    L5:
        updatedTopCaptionHeight();
        return;
    L4:
        this.topCommentContainer.setVisibility(8);
        goto L5
    }

    private /* synthetic */ void lambda$setCaptionAbove$80(ValueAnimator r1) {
        this.frameLayout2.invalidate();
    }

    private /* synthetic */ void lambda$setCaptionAbove$81(boolean r1, boolean r2) {
        if (r1 == true) goto L6;
        if (r2 == false) goto L6;
        return;
    L6:
        this.captionContainer.setVisibility(8);
    }

    private /* synthetic */ void lambda$setCaptionAbove$82() {
        if (this.captionAbove == false) goto L5;
        EditTextEmoji r0 = this.topCommentTextView;
    L7:
        if (r0.getEditText().getLineCount() > 2) goto L9;
    L11:
        boolean r02 = false;
    L12:
        showAiButton(r02);
        return;
    L9:
        if (TextUtils.isEmpty(r0.getText().toString().trim()) == true) goto L11;
        r02 = true;
        goto L12
    L5:
        r0 = this.commentTextView;
        goto L7
    }

    private /* synthetic */ void lambda$setTypeButtonsHidden$71(boolean r1) {
        if (r1 == false) goto L5;
        this.buttonsRecyclerViewWrapper.setVisibility(4);
        return;
    }

    private /* synthetic */ void lambda$showAiButton$72(boolean r1) {
        if (r1 == true) goto L5;
        this.aiButton.setVisibility(8);
        return;
    }

    private /* synthetic */ void lambda$showAiButton$73(boolean r1) {
        if (r1 == true) goto L5;
        this.topAiButton.setVisibility(8);
        return;
    }

    private /* synthetic */ void lambda$showCaptionLimitBulletin$48(BaseFragment r2) {
        dismiss(true);
        if (r2 == null) goto L6;
        r2.presentFragment(new PremiumPreviewFragment("caption_limit"));
        return;
    }

    private /* synthetic */ void lambda$showCommentTextView$61(ValueAnimator r1) {
        updatedTopCaptionHeight();
    }

    private /* synthetic */ void lambda$showLayout$55() {
        this.viewChangeAnimator = null;
        AttachAlertLayout r1 = this.currentAttachLayout;
        if (r1 == this.photoLayout) goto L9;
        AttachAlertLayout r2 = this.nextAttachLayout;
        ChatAttachAlertPhotoLayoutPreview r3 = this.photoPreviewLayout;
        if (r2 == r3) goto L9;
        if (r1 == r2) goto L9;
        if (r1 == r3) goto L9;
        this.containerView.removeView(r1);
    L9:
        this.currentAttachLayout.setVisibility(8);
        this.currentAttachLayout.onHidden();
        this.nextAttachLayout.onShown();
        this.currentAttachLayout = this.nextAttachLayout;
        this.nextAttachLayout = null;
        int[] r0 = this.scrollOffsetY;
        r0[0] = r0[1];
        setCaptionAbove(this.captionAbove, false);
        updateDoneItemEnabled();
    }

    private /* synthetic */ void lambda$showLayout$56(float r2, float r3, boolean r4, DynamicAnimation r5, float r6, float r7) {
        float r62 = r6 / 500.0f;
        this.ATTACH_ALERT_LAYOUT_TRANSLATION.set(this.currentAttachLayout, Float.valueOf(r62));
        this.actionBar.setAlpha(AndroidUtilities.lerp(r2, r3, r62));
        updateLayout(this.currentAttachLayout, false, 0);
        updateLayout(this.nextAttachLayout, false, 0);
        if ((this.nextAttachLayout instanceof ChatAttachAlertPhotoLayoutPreview) == false) goto L6;
        if (r4 == true) goto L6;
    L7:
        float r22 = Utilities.clamp(r62, 1.0f, 0.0f);
        this.mediaPreviewView.setAlpha(r22);
        float r32 = 1.0f - r22;
        this.selectedView.setAlpha(r32);
        this.selectedView.setTranslationX(r22 * (-AndroidUtilities.dp(16.0f)));
        this.mediaPreviewView.setTranslationX(r32 * AndroidUtilities.dp(16.0f));
        return;
    L6:
        r62 = 1.0f - r62;
        goto L7
    }

    private /* synthetic */ void lambda$showLayout$57(boolean r1, Runnable r2, DynamicAnimation r3, boolean r4, float r5, float r6) {
        this.currentAttachLayout.onHideShowProgress(1.0f);
        this.nextAttachLayout.onHideShowProgress(1.0f);
        this.currentAttachLayout.onContainerTranslationUpdated(this.currentPanTranslationY);
        this.nextAttachLayout.onContainerTranslationUpdated(this.currentPanTranslationY);
        this.containerView.invalidate();
        ActionBar r0 = this.actionBar;
        if (r1 == false) goto L5;
        Integer r12 = 1;
    L6:
        r0.setTag(r12);
        r2.run();
        return;
    L5:
        r12 = null;
        goto L6
    }

    private /* synthetic */ void lambda$showLayout$58(AttachAlertLayout r7, Runnable r8) {
        if (this.nextAttachLayout.getCurrentItemTop() > r7.getButtonsHideOffset()) goto L5;
        boolean r72 = true;
    L6:
        float r0 = this.actionBar.getAlpha();
        if (r72 == false) goto L9;
        float r3 = 1.0f;
    L10:
        SpringAnimation r4 = new SpringAnimation(new FloatValueHolder(0.0f));
        r4.addUpdateListener(new ChatAttachAlert$$ExternalSyntheticLambda46(this, r0, r3, r72));
        r4.addEndListener(new ChatAttachAlert$$ExternalSyntheticLambda47(this, r72, r8));
        r4.setSpring(new SpringForce(500.0f));
        r4.getSpring().setDampingRatio(1.0f);
        r4.getSpring().setStiffness(1000.0f);
        r4.start();
        this.viewChangeAnimator = r4;
        return;
    L9:
        r3 = 0.0f;
        goto L10
    L5:
        r72 = false;
        goto L6
    }

    private /* synthetic */ void lambda$showMotionHint$69(HintView2 r1) {
        this.containerView.removeView(r1);
    }

    private /* synthetic */ void lambda$showPollLayout$54(TLRPC.MessageMedia r10, CharSequence r11, PollAttachedMediaPack r12, ArrayList r13, boolean r14, int r15, long r16) {
        ((ChatActivity) this.baseFragment).sendPoll((TLRPC.TL_messageMediaPoll) r10, r11, r12, r13, r14, r15, r16);
    }

    private /* synthetic */ void lambda$updateMotionItem$68(boolean r1) {
        if (r1 == true) goto L5;
        this.motionItem.setVisibility(8);
        return;
    }

    private void onCurrentLayoutAnimatorChanged() {
        if (this.shadowDrawable != null) goto L5;
        return;
    L5:
        if (this.containerView == null) goto L10;
        int r0 = getShadowDrawableColor();
        Theme.setDrawableColor(this.shadowDrawable, r0);
        checkColorSourceColor(r0);
        updateDoneItemEnabled();
        this.containerView.invalidate();
        return;
    }

    private void onCurrentLayoutAnimatorChanged(ReplaceAnimator<?> r1) {
        onCurrentLayoutAnimatorChanged();
    }

    private void onWriteButtonPressed() {
        MessageObject r0 = this.editingMessageObject;
        if (r0 == null) goto L19;
        if (r0.needResendWhenEdit() == false) goto L19;
        if (ChatObject.canManageMonoForum(this.currentAccount, this.editingMessageObject.getDialogId()) == true) goto L19;
        BaseFragment r02 = this.baseFragment;
        if ((r02 instanceof ChatActivity) == false) goto L19;
        ChatActivity r03 = (ChatActivity) r02;
        MessageSuggestionParams r1 = r03.messageSuggestionParams;
        if (r1 != null) goto L15;
        r1 = MessageSuggestionParams.of(this.editingMessageObject.messageOwner.suggested_post);
    L15:
        if (StarsController.isEnoughAmount(this.currentAccount, r1.amount) == true) goto L19;
        r03.showSuggestionOfferForEditMessage(r1);
        return;
    L19:
        if ((this.currentLimit - this.codepointCount) >= 0) goto L28;
        AndroidUtilities.shakeView(this.captionLimitView);
        AndroidUtilities.shakeView(this.topCaptionLimitView);
        this.writeButton.performHapticFeedback(3, 2);     // Catch: Exception -> L46
    L23:
        if (MessagesController.getInstance(this.currentAccount).premiumFeaturesBlocked() == false) goto L25;
        return;
    L25:
        if (MessagesController.getInstance(this.currentAccount).captionLengthLimitPremium <= this.codepointCount) goto L50;
        showCaptionLimitBulletin(this.baseFragment);
        return;
    L50:
        return;
    L28:
        if (this.editingMessageObject != null) goto L35;
        BaseFragment r04 = this.baseFragment;
        if ((r04 instanceof ChatActivity) == false) goto L35;
        if (((ChatActivity) r04).isInScheduleMode() == false) goto L35;
        AlertsCreator.createScheduleDatePickerDialog(getContext(), ((ChatActivity) this.baseFragment).getDialogId(), new ChatAttachAlert$$ExternalSyntheticLambda54(this), this.resourcesProvider);
        return;
    L35:
        AttachAlertLayout r4 = this.currentAttachLayout;
        if (r4 != this.photoLayout) goto L38;
    L44:
        sendPressed(true, 0, 0, this.effectId, isCaptionAbove());
        return;
    L38:
        if (r4 == this.photoPreviewLayout) goto L44;
        if (r4.sendSelectedItems(true, 0, 0, this.effectId, isCaptionAbove()) == true) goto L52;
        this.allowPassConfirmationAlert = true;
        dismiss();
        return;
    }

    private void openAudioLayout(boolean r7) {
        if (this.musicEnabled == true) goto L6;
        if (r7 == false) goto L6;
        ChatAttachRestrictedLayout r0 = new ChatAttachRestrictedLayout(3, this, getContext(), this.resourcesProvider);
        this.restrictedLayout = r0;
        showLayout(r0);
    L6:
        int r2 = 1;
        if (this.audioLayout != null) goto L11;
        AttachAlertLayout[] r02 = this.layouts;
        ChatAttachAlertAudioLayout r3 = new ChatAttachAlertAudioLayout(this, getContext(), this.resourcesProvider);
        this.audioLayout = r3;
        r02[3] = r3;
        r3.setupBlurredSearchField(this.iBlur3FactoryLiquidGlass);
        this.audioLayout.setDelegate(new ChatAttachAlert$$ExternalSyntheticLambda79(this));
        if (this.isPollAttach == false) goto L11;
        this.audioLayout.setMaxSelectedFiles(1);
    L11:
        BaseFragment r03 = this.baseFragment;
        if ((r03 instanceof ChatActivity) == false) goto L24;
        TLRPC.Chat r04 = ((ChatActivity) r03).getCurrentChat();
        ChatAttachAlertAudioLayout r1 = this.audioLayout;
        if (r04 == null) goto L20;
        if (ChatObject.hasAdminRights(r04) == true) goto L20;
        if (r04.slowmode_enabled == false) goto L20;
    L23:
        r1.setMaxSelectedFiles(r2);
    L20:
        if (this.editingMessageObject != null) goto L23;
        r2 = -1;
    L24:
        if (r7 == false) goto L27;
        showLayout(this.audioLayout);
        return;
    }

    private void openContactsLayout() {
        if (this.plainTextEnabled == true) goto L6;
        ChatAttachRestrictedLayout r0 = new ChatAttachRestrictedLayout(5, this, getContext(), this.resourcesProvider);
        this.restrictedLayout = r0;
        showLayout(r0);
    L6:
        if (this.contactsLayout != null) goto L8;
        AttachAlertLayout[] r02 = this.layouts;
        ChatAttachAlertContactsLayout r1 = new ChatAttachAlertContactsLayout(this, getContext(), this.resourcesProvider);
        this.contactsLayout = r1;
        r02[2] = r1;
        r1.setupBlurredSearchField(this.iBlur3FactoryLiquidGlass);
        this.contactsLayout.setDelegate(new AnonymousClass29(this));
    L8:
        BaseFragment r03 = this.baseFragment;
        if ((r03 instanceof ChatActivity) == false) goto L20;
        TLRPC.Chat r04 = ((ChatActivity) r03).getCurrentChat();
        ChatAttachAlertContactsLayout r12 = this.contactsLayout;
        if (r04 != null) goto L13;
    L18:
        boolean r05 = true;
    L19:
        r12.setMultipleSelectionAllowed(r05);
        goto L20
    L13:
        if (ChatObject.hasAdminRights(r04) == true) goto L18;
        if (r04.slowmode_enabled == false) goto L18;
        r05 = false;
    L20:
        showLayout(this.contactsLayout);
    }

    private void openDocumentsLayout(boolean r8) {
        if (this.documentsEnabled == true) goto L6;
        if (r8 == false) goto L6;
        ChatAttachRestrictedLayout r0 = new ChatAttachRestrictedLayout(4, this, getContext(), this.resourcesProvider);
        this.restrictedLayout = r0;
        showLayout(r0);
    L6:
        boolean r2 = false;
        if (this.documentLayout == null) goto L9;
    L13:
        int r1 = 1;
        if (this.isPollAttach == false) goto L16;
        this.documentLayout.setMaxSelectedFiles(1);
    L35:
        ChatAttachAlertDocumentLayout r02 = this.documentLayout;
        r02.isSoundPicker = this.isSoundPicker;
        if (r8 == false) goto L39;
        showLayout(r02);
        return;
    L39:
        return;
    L16:
        BaseFragment r03 = this.baseFragment;
        if ((r03 instanceof ChatActivity) == false) goto L29;
        TLRPC.Chat r04 = ((ChatActivity) r03).getCurrentChat();
        ChatAttachAlertDocumentLayout r22 = this.documentLayout;
        if (r04 == null) goto L25;
        if (ChatObject.hasAdminRights(r04) == true) goto L25;
        if (r04.slowmode_enabled == false) goto L25;
    L28:
        r22.setMaxSelectedFiles(r1);
    L25:
        if (this.editingMessageObject != null) goto L28;
        r1 = -1;
        goto L28
    L29:
        this.documentLayout.setMaxSelectedFiles(this.maxSelectedPhotos);
        ChatAttachAlertDocumentLayout r05 = this.documentLayout;
        if (this.isSoundPicker == false) goto L32;
    L34:
        r05.setCanSelectOnlyImageFiles(r2);
        goto L35
    L32:
        if (this.allowEnterCaption == true) goto L34;
        r2 = true;
        goto L34
    L9:
        if (this.isSoundPicker == false) goto L11;
        int r06 = 2;
    L12:
        AttachAlertLayout[] r3 = this.layouts;
        ChatAttachAlertDocumentLayout r4 = new ChatAttachAlertDocumentLayout(this, getContext(), r06, this.resourcesProvider);
        this.documentLayout = r4;
        r3[4] = r4;
        r4.setDelegate(new AnonymousClass30(this));
        goto L13
    L11:
        r06 = 0;
        goto L12
    }

    private void openQuickRepliesLayout() {
        if (this.quickRepliesLayout != null) goto L5;
        AttachAlertLayout[] r0 = this.layouts;
        ChatAttachAlertQuickRepliesLayout r1 = new ChatAttachAlertQuickRepliesLayout(this, getContext(), this.resourcesProvider);
        this.quickRepliesLayout = r1;
        r0[7] = r1;
        r1.setupBlurredSearchField(this.iBlur3FactoryLiquidGlass);
    L5:
        showLayout(this.quickRepliesLayout);
    }

    private void removeFromRoot() {
        ViewGroup r0 = this.containerView;
        if (r0 == null) goto L6;
        r0.setVisibility(4);
    L6:
        if (this.actionBar.isSearchFieldVisible() == false) goto L8;
        this.actionBar.closeSearchField();
    L8:
        this.contactsLayout = null;
        this.quickRepliesLayout = null;
        this.audioLayout = null;
        this.pollLayout = null;
        this.todoLayout = null;
        this.locationLayout = null;
        this.documentLayout = null;
        int r1 = 1;
    L9:
        AttachAlertLayout[] r2 = this.layouts;
        if (r1 >= r2.length) goto L16;
        AttachAlertLayout r22 = r2[r1];
        if (r22 == null) goto L15;
        r22.onDestroy();
        this.containerView.removeView(this.layouts[r1]);
        this.layouts[r1] = null;
    L15:
        r1 = r1 + 1;
        goto L9
    L16:
        updateActionBarVisibility(false, false);
        super.dismissInternal();
    }

    private void replaceWithText(int r3, int r4, CharSequence r5, boolean r6) {
        if (getCommentView() != null) goto L13;
        return;
    L13:
        SpannableStringBuilder r0 = new SpannableStringBuilder(getCommentView().getText());     // Catch: Exception -> L10
        r0.replace(r3, r4 + r3, r5);     // Catch: Exception -> L10
        if (r6 == false) goto L8;
        Emoji.replaceEmoji(r0, getCommentView().getEditText().getPaint().getFontMetricsInt(), false);     // Catch: Exception -> L10
    L8:
        getCommentView().setText(r0);     // Catch: Exception -> L10
        getCommentView().setSelection(r3 + r5.length());     // Catch: Exception -> L10
        return;
    L10:
        e = move-exception;
        FileLog.e(e);
    }

    private boolean sendPressed(boolean r16, int r17, int r18, long r19, boolean r21) {
        if (this.buttonPressed == false) goto L6;
        return false;
    L6:
        BaseFragment r0 = this.baseFragment;
        if ((r0 instanceof ChatActivity) == false) goto L17;
        ChatActivity r02 = (ChatActivity) r0;
        TLRPC.Chat r2 = r02.getCurrentChat();
        if (r02.getCurrentUser() == null) goto L11;
    L16:
        MessagesController.getNotificationsSettings(this.currentAccount).edit().putBoolean(NotificationsSettingsFacade.PROPERTY_SILENT + r02.getDialogId(), !r16).apply();
        goto L17
    L11:
        if (ChatObject.isChannel(r2) == false) goto L15;
        if (r2.megagroup == true) goto L16;
    L15:
        if (ChatObject.isChannel(r2) == false) goto L16;
    L17:
        int r22 = 1;
        if (checkCaption(getCommentView().getText()) == false) goto L20;
        return true;
    L20:
        applyCaption();
        if (this.animatorEphemeralMessageVisibility.getValue() == false) goto L24;
        setButtonPressed(true);
        this.delegate.didPressedButton(7, true, r16, r17, r18, r19, r21, false, 0);
        return true;
    L24:
        int r8 = this.currentAccount;
        long r9 = getDialogId();
        AttachAlertLayout r03 = this.currentAttachLayout;
        if (r03 == null) goto L29;
        r22 = r03.getSelectedItemsCount();
    L29:
        return AlertsCreator.ensurePaidMessageConfirmation(r8, r9, r22 + getAdditionalMessagesCount(), new ChatAttachAlert$$ExternalSyntheticLambda77(this, r16, r17, r18, r19, r21));
    }

    private void setNavBarAlpha(float r7) {
        boolean r1 = false;
        int r72 = ColorUtils.setAlphaComponent(getThemedColor(Theme.key_windowBackgroundGray), Math.min(255, Math.max(0, (int) (r7 * 255.0f))));
        this.navBarColor = r72;
        AndroidUtilities.setNavigationBarColor(this, r72, false);
        if (AndroidUtilities.computePerceivedBrightness(this.navBarColor) <= 0.721d) goto L5;
        r1 = true;
    L5:
        AndroidUtilities.setLightNavigationBar(this, r1);
        getContainer().invalidate();
    }

    private void showAiButton(boolean r9) {
        if (r9 == true) goto L5;
    L11:
        boolean r92 = false;
    L13:
        if (this.shownAiButton == r92) goto L45;
        if (r92 == false) goto L17;
        MessagesController.getInstance(this.currentAccount).getTonesController().load();
    L17:
        this.shownAiButton = r92;
        this.aiButton.setVisibility(0);
        this.topAiButton.setVisibility(0);
        ViewPropertyAnimator r0 = this.aiButton.animate();
        float r1 = 0.0f;
        float r2 = 1.0f;
        if (r92 == false) goto L20;
        float r3 = 1.0f;
    L21:
        ViewPropertyAnimator r02 = r0.alpha(r3);
        if (r92 == false) goto L24;
        float r4 = 1.0f;
    L25:
        ViewPropertyAnimator r03 = r02.scaleX(r4);
        if (r92 == false) goto L28;
        float r42 = 1.0f;
    L29:
        ViewPropertyAnimator r04 = r03.scaleY(r42);
        CubicBezierInterpolator r43 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r04.setInterpolator(r43).setDuration(420).withEndAction(new ChatAttachAlert$$ExternalSyntheticLambda66(this, r92)).start();
        ViewPropertyAnimator r05 = this.topAiButton.animate();
        if (r92 == false) goto L32;
        r1 = 1.0f;
    L32:
        ViewPropertyAnimator r06 = r05.alpha(r1);
        if (r92 == false) goto L35;
        float r12 = 1.0f;
    L36:
        ViewPropertyAnimator r07 = r06.scaleX(r12);
        if (r92 == true) goto L40;
        r2 = 0.6f;
    L40:
        r07.scaleY(r2).setInterpolator(r43).setDuration(420).withEndAction(new ChatAttachAlert$$ExternalSyntheticLambda67(this, r92)).start();
        if (r92 == false) goto L44;
        ImageView r93 = this.aiButton;
        AiButtonDrawable r08 = this.aiButtonIcon;
        Objects.requireNonNull(r08);
        r93.postDelayed(new CaptionPhotoViewer$$ExternalSyntheticLambda9(r08), 220);
        ImageView r94 = this.topAiButton;
        AiButtonDrawable r8 = this.topAiButtonIcon;
        Objects.requireNonNull(r8);
        r94.postDelayed(new CaptionPhotoViewer$$ExternalSyntheticLambda9(r8), 220);
        return;
    L44:
        return;
    L35:
        r12 = 0.6f;
        goto L36
    L28:
        r42 = 0.6f;
        goto L29
    L24:
        r4 = 0.6f;
        goto L25
    L20:
        r3 = 0.0f;
        goto L21
    L45:
        return;
    L5:
        if (ExteraConfig.getTelegramAiEditor() == false) goto L11;
        BaseFragment r95 = this.baseFragment;
        if ((r95 instanceof ChatActivity) == false) goto L11;
        if (((ChatActivity) r95).isSecretChat() == true) goto L11;
        r92 = true;
        goto L13
    }

    private void showCaptionLimitBulletin(BaseFragment r4) {
        if ((r4 instanceof ChatActivity) == true) goto L5;
        return;
    L5:
        if (ChatObject.isChannelAndNotMegaGroup(((ChatActivity) r4).getCurrentChat()) == false) goto L10;
        BulletinFactory.of(this.sizeNotifierFrameLayout, this.resourcesProvider).createCaptionLimitBulletin(MessagesController.getInstance(this.currentAccount).captionLengthLimitPremium, new ChatAttachAlert$$ExternalSyntheticLambda75(this, r4)).show();
        return;
    }

    private boolean showCommentTextView(boolean r18, boolean r19) {
        this.animatorCaptionVisible.setValue(r18, true);
        int r5 = 0;
        if (this.frameLayout2.getTag() == null) goto L5;
        boolean r3 = true;
    L6:
        if (r18 != r3) goto L8;
        return false;
    L8:
        AnimatorSet r32 = this.commentsAnimator;
        if (r32 == null) goto L11;
        r32.cancel();
    L11:
        FrameLayout r33 = this.frameLayout2;
        if (r18 == false) goto L14;
        Integer r6 = 1;
    L15:
        r33.setTag(r6);
        if (this.commentTextView.getEditText().isFocused() == false) goto L18;
        AndroidUtilities.hideKeyboard(this.commentTextView.getEditText());
    L18:
        this.commentTextView.hidePopup(true);
        this.topCommentTextView.hidePopup(true);
        if (r18 == false) goto L25;
        if (this.isSoundPicker == true) goto L23;
        this.frameLayout2.setVisibility(0);
    L23:
        this.writeButtonContainer.setVisibility(0);
    L27:
        AttachAlertLayout r34 = this.currentAttachLayout;
        if (r34 == this.photoLayout) goto L32;
        if (r34 == this.photoPreviewLayout) goto L32;
    L34:
        boolean r35 = false;
    L35:
        float r7 = 0.2f;
        float r8 = 0.0f;
        if (r19 == false) goto L99;
        this.commentsAnimator = new AnimatorSet();
        if (r35 == false) goto L40;
        this.topCommentContainer.setVisibility(0);
    L40:
        ArrayList r10 = new ArrayList();
        FrameLayout r11 = this.frameLayout2;
        Property r12 = View.ALPHA;
        if (r18 == false) goto L43;
        float r13 = 1.0f;
    L44:
        r10.add(ObjectAnimator.ofFloat(r11, r12, new float[]{r13}));
        FrameLayout r112 = this.captionContainer;
        if (r18 == false) goto L48;
        if (r35 == true) goto L48;
        float r132 = 1.0f;
    L49:
        r10.add(ObjectAnimator.ofFloat(r112, r12, new float[]{r132}));
        if (r18 == false) goto L53;
        if (r35 == true) goto L53;
        this.captionContainer.setVisibility(0);
        r10.add(ObjectAnimator.ofFloat(this.captionContainer, View.TRANSLATION_Y, new float[]{0.0f}));
    L53:
        FrameLayout r113 = this.topCommentContainer;
        if (r18 == false) goto L57;
        if (r35 == false) goto L57;
        float r133 = 1.0f;
    L58:
        r10.add(ObjectAnimator.ofFloat(r113, r12, new float[]{r133}));
        FrameLayout r114 = this.writeButtonContainer;
        Property r134 = View.SCALE_X;
        if (r18 == false) goto L61;
        float r14 = 1.0f;
    L62:
        r10.add(ObjectAnimator.ofFloat(r114, r134, new float[]{r14}));
        FrameLayout r115 = this.writeButtonContainer;
        Property r142 = View.SCALE_Y;
        if (r18 == false) goto L66;
        float r15 = 1.0f;
    L65:
        float r16 = 48.0f;
        r10.add(ObjectAnimator.ofFloat(r115, r142, new float[]{r15}));
        FrameLayout r62 = this.writeButtonContainer;
        if (r18 == false) goto L70;
        float r116 = 1.0f;
    L71:
        r10.add(ObjectAnimator.ofFloat(r62, r12, new float[]{r116}));
        ChatActivityEnterView.SendButton r63 = this.writeButton;
        if (r18 == false) goto L74;
        float r117 = 1.0f;
    L75:
        r10.add(ObjectAnimator.ofFloat(r63, r134, new float[]{r117}));
        ChatActivityEnterView.SendButton r64 = this.writeButton;
        if (r18 == false) goto L78;
        r7 = 1.0f;
    L78:
        r10.add(ObjectAnimator.ofFloat(r64, r142, new float[]{r7}));
        if (this.actionBar.getTag() == null) goto L86;
        FrameLayout r65 = this.frameLayout2;
        Property r72 = View.TRANSLATION_Y;
        if (r18 == true) goto L84;
        r8 = AndroidUtilities.dp(48.0f);
    L84:
        r10.add(ObjectAnimator.ofFloat(r65, r72, new float[]{r8}));
    L96:
        if (r35 == false) goto L98;
        ValueAnimator r66 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r66.addUpdateListener(new ChatAttachAlert$$ExternalSyntheticLambda6(this));
        r10.add(r66);
    L98:
        this.commentsAnimator.playTogether(r10);
        this.commentsAnimator.setInterpolator(new DecelerateInterpolator());
        this.commentsAnimator.setDuration(180);
        this.commentsAnimator.addListener(new AnonymousClass32(this, r18, r35));
        this.commentsAnimator.start();
    L162:
        ChatActivityEnterView.SendButton r36 = this.writeButton;
        if (r18 == false) goto L165;
        r5 = Math.max(1, this.currentAttachLayout.getSelectedItemsCount());
    L165:
        r36.setCount(r5, r19);
        if (this.editingMessageObject == null) goto L168;
        long r1 = 0;
    L169:
        this.writeButton.setStarsPrice(r1, this.currentAttachLayout.getSelectedItemsCount() + getAdditionalMessagesCount());
        EditTextEmoji r17 = this.commentTextView;
        if (r17 == null) goto L174;
        ViewGroup.MarginLayoutParams r110 = (ViewGroup.MarginLayoutParams) r17.getLayoutParams();
        int r2 = Math.max(AndroidUtilities.dp(r16), this.writeButton.width());
        if (r110.rightMargin == r2) goto L174;
        r110.rightMargin = r2;
        this.commentTextView.setLayoutParams(r110);
    L174:
        updateCameraButtonVisibility();
        return true;
    L168:
        r1 = MessagesController.getInstance(this.currentAccount).getSendPaidMessagesStars(getDialogId());
        goto L169
    L86:
        if (this.typeButtonsAvailable == false) goto L96;
        FrameLayout r67 = this.buttonsRecyclerViewWrapper;
        Property r73 = View.TRANSLATION_Y;
        if (r18 == false) goto L90;
        float r118 = AndroidUtilities.dp(36.0f);
    L91:
        r10.add(ObjectAnimator.ofFloat(r67, r73, new float[]{r118}));
        FrameLayout r68 = this.buttonsRecyclerViewWrapper;
        if (r18 == true) goto L95;
        r8 = 1.0f;
    L95:
        r10.add(ObjectAnimator.ofFloat(r68, r12, new float[]{r8}));
        goto L96
    L90:
        r118 = 0.0f;
        goto L91
    L74:
        r117 = 0.2f;
        goto L75
    L70:
        r116 = 0.0f;
        goto L71
    L66:
        r15 = 0.2f;
        goto L65
    L61:
        r14 = 0.2f;
    L57:
        r133 = 0.0f;
    L48:
        r132 = 0.0f;
        goto L49
    L43:
        r13 = 0.0f;
        goto L44
    L99:
        r16 = 48.0f;
        FrameLayout r69 = this.frameLayout2;
        if (r18 == false) goto L102;
        float r102 = 1.0f;
    L103:
        r69.setAlpha(r102);
        FrameLayout r610 = this.captionContainer;
        if (r18 == false) goto L107;
        if (r35 == false) goto L107;
        float r103 = 1.0f;
    L108:
        r610.setAlpha(r103);
        if (r18 == false) goto L112;
        if (r35 == true) goto L112;
        this.captionContainer.setVisibility(0);
        this.captionContainer.setTranslationY(0.0f);
    L112:
        FrameLayout r611 = this.writeButtonContainer;
        if (r18 == false) goto L115;
        float r104 = 1.0f;
    L116:
        r611.setScaleX(r104);
        FrameLayout r612 = this.writeButtonContainer;
        if (r18 == false) goto L119;
        float r105 = 1.0f;
    L120:
        r612.setScaleY(r105);
        FrameLayout r613 = this.writeButtonContainer;
        if (r18 == false) goto L123;
        float r106 = 1.0f;
    L124:
        r613.setAlpha(r106);
        FrameLayout r614 = this.topCommentContainer;
        if (r18 == false) goto L128;
        if (r35 == false) goto L128;
        int r107 = 0;
    L129:
        r614.setVisibility(r107);
        FrameLayout r615 = this.topCommentContainer;
        if (r18 == false) goto L133;
        if (r35 == false) goto L133;
        float r108 = 1.0f;
    L134:
        r615.setAlpha(r108);
        ChatActivityEnterView.SendButton r616 = this.writeButton;
        if (r18 == false) goto L137;
        float r109 = 1.0f;
    L138:
        r616.setScaleX(r109);
        ChatActivityEnterView.SendButton r617 = this.writeButton;
        if (r18 == false) goto L141;
        r7 = 1.0f;
    L141:
        r617.setScaleY(r7);
        if (this.actionBar.getTag() == null) goto L149;
        FrameLayout r618 = this.frameLayout2;
        if (r18 == true) goto L147;
        r8 = AndroidUtilities.dp(48.0f);
    L147:
        r618.setTranslationY(r8);
    L158:
        if (r18 == true) goto L160;
        this.frameLayout2.setVisibility(4);
        this.writeButtonContainer.setVisibility(4);
    L160:
        if (r35 == false) goto L162;
        updatedTopCaptionHeight();
        goto L162
    L149:
        if (this.typeButtonsAvailable == false) goto L158;
        AttachAlertLayout r619 = this.currentAttachLayout;
        if (r619 != null) goto L153;
    L154:
        FrameLayout r620 = this.buttonsRecyclerViewWrapper;
        if (r18 == false) goto L157;
        r8 = AndroidUtilities.dp(84.0f);
    L157:
        r620.setTranslationY(r8);
        goto L158
    L153:
        if (r619.shouldHideBottomButtons() == false) goto L158;
    L137:
        r109 = 0.2f;
    L133:
        r108 = 0.0f;
    L128:
        r107 = 8;
        goto L129
    L123:
        r106 = 0.0f;
        goto L124
    L119:
        r105 = 0.2f;
        goto L120
    L115:
        r104 = 0.2f;
    L107:
        r103 = 0.0f;
        goto L108
    L102:
        r102 = 0.0f;
    L32:
        if (this.captionAbove == false) goto L34;
        r35 = true;
        goto L35
    L25:
        if (this.typeButtonsAvailable == false) goto L27;
        this.buttonsRecyclerViewWrapper.setVisibility(0);
        goto L27
    L14:
        r6 = null;
        goto L15
    L5:
        r3 = false;
        goto L6
    }

    private void showLayout(AttachAlertLayout r2, long r3) {
        showLayout(r2, r3, true);
    }

    private void showLayout(AttachAlertLayout r20, long r21, boolean r23) {
        Float r6 = Float.valueOf(0.0f);
        if (this.viewChangeAnimator == null) goto L5;
        return;
    L5:
        if (this.commentsAnimator != null) goto L125;
        AttachAlertLayout r7 = this.currentAttachLayout;
        if (r7 != r20) goto L12;
        r7.scrollToTop();
        return;
    L12:
        if (r20 == this.todoLayout) goto L14;
    L17:
        BoolAnimator r72 = this.animatorToggleCaptionSupported;
        if (r21 != 1) goto L20;
        boolean r11 = true;
    L21:
        r72.setValue(r11, r23);
        this.animatorCurrentVisibleLayout.replace(Long.valueOf(r21), r23);
        this.botButtonWasVisible = false;
        this.botButtonProgressWasVisible = false;
        this.botMainButtonOffsetY = 0.0f;
        this.botMainButtonTextView.setVisibility(8);
        this.botProgressView.setAlpha(0.0f);
        this.botProgressView.setScaleX(0.1f);
        this.botProgressView.setScaleY(0.1f);
        this.botProgressView.setVisibility(8);
        this.buttonsRecyclerViewWrapper.setAlpha(1.0f);
        this.buttonsRecyclerViewWrapper.setTranslationY(this.botMainButtonOffsetY);
        int r73 = 0;
    L23:
        if (r73 >= this.botAttachLayouts.size()) goto L25;
        this.botAttachLayouts.valueAt(r73).setMeasureOffsetY(0);
        r73 = r73 + 1;
        goto L23
    L25:
        this.selectedId = r21;
        int r74 = this.buttonsRecyclerView.getChildCount();
        int r12 = 0;
    L26:
        if (r12 >= r74) goto L34;
        View r13 = this.buttonsRecyclerView.getChildAt(r12);
        if ((r13 instanceof AttachButton) == false) goto L31;
        ((AttachButton) r13).updateCheckedState(true);
    L33:
        r12 = r12 + 1;
        goto L26
    L31:
        if ((r13 instanceof AttachBotButton) == false) goto L33;
        ((AttachBotButton) r13).updateCheckedState(true);
        goto L33
    L34:
        int r75 = (this.currentAttachLayout.getFirstOffset() - AndroidUtilities.dp(11.0f)) - this.scrollOffsetY[0];
        this.nextAttachLayout = r20;
        boolean r122 = r20.disableBottomFade();
        ChatActivityFadeView r132 = this.fadeView;
        if (r132 == null) goto L40;
        if (r122 == false) goto L38;
        int r14 = 0;
    L39:
        r132.setFadeHeightBottom(r14);
        goto L40
    L38:
        r14 = AndroidUtilities.dp(48.0f);
    L40:
        View r133 = this.bottomFadeView;
        if (r133 == null) goto L46;
        if (r122 == false) goto L44;
        int r123 = 4;
    L45:
        r133.setVisibility(r123);
        goto L46
    L44:
        r123 = 0;
    L46:
        ActionBar r124 = this.actionBar;
        if (this.nextAttachLayout.needsActionBar() == 0) goto L49;
        int r134 = 0;
    L50:
        r124.setVisibility(r134);
        if (this.actionBar.isSearchFieldVisible() == false) goto L53;
        this.actionBar.closeSearchField();
    L53:
        this.currentAttachLayout.onHide();
        AttachAlertLayout r125 = this.nextAttachLayout;
        ChatAttachAlertPhotoLayout r135 = this.photoLayout;
        if (r125 != r135) goto L56;
        r135.setCheckCameraWhenShown(true);
    L56:
        this.nextAttachLayout.onShow(this.currentAttachLayout);
        this.nextAttachLayout.setVisibility(0);
        updateCameraButtonVisibility();
        if (r20.getParent() == null) goto L59;
        this.containerView.removeView(this.nextAttachLayout);
    L59:
        int r126 = this.containerView.indexOfChild(this.currentAttachLayout);
        ViewParent r136 = this.nextAttachLayout.getParent();
        ViewGroup r15 = this.containerView;
        if (r136 == r15) goto L66;
        AttachAlertLayout r137 = this.nextAttachLayout;
        if (r137 == this.locationLayout) goto L65;
        r126 = r126 + 1;
    L65:
        r15.addView(r137, r126, LayoutHelper.createFrame(-1, -1.0f));
    L66:
        ChatAttachAlert$$ExternalSyntheticLambda7 r112 = new ChatAttachAlert$$ExternalSyntheticLambda7(this);
        AttachAlertLayout r127 = this.currentAttachLayout;
        if ((r127 instanceof ChatAttachAlertPhotoLayoutPreview) == false) goto L69;
    L73:
        int r5 = Math.max(this.nextAttachLayout.getWidth(), this.currentAttachLayout.getWidth());
        AttachAlertLayout r76 = this.nextAttachLayout;
        if ((r76 instanceof ChatAttachAlertPhotoLayoutPreview) == false) goto L80;
        r76.setTranslationX(r5);
        AttachAlertLayout r52 = this.currentAttachLayout;
        if ((r52 instanceof ChatAttachAlertPhotoLayout) == false) goto L85;
        ChatAttachAlertPhotoLayout.CameraViewInternal r53 = ((ChatAttachAlertPhotoLayout) r52).cameraView;
        if (r53 == null) goto L85;
        r53.setVisibility(4);
    L85:
        this.nextAttachLayout.setAlpha(1.0f);
        this.currentAttachLayout.setAlpha(1.0f);
        if (r23 == false) goto L89;
        this.ATTACH_ALERT_LAYOUT_TRANSLATION.set(this.currentAttachLayout, r6);
        AndroidUtilities.runOnUIThread(new ChatAttachAlert$$ExternalSyntheticLambda8(this, r20, r112));
    L97:
        ActionBar r4 = this.actionBar;
        if (r4 != null) goto L100;
        return;
    L100:
        if (this.hasOverridenWebviewBackgroundColor == true) goto L102;
    L104:
        int r77 = 0;
    L105:
        if (r21 != 1) goto L107;
    L115:
        int r8 = AndroidUtilities.dp(46.0f);
    L116:
        this.actionBar.setForcedMenuWidth(r8);
        return;
    L107:
        if (r21 == 6) goto L115;
        if ((r20 instanceof ChatAttachAlertBotWebViewLayout) == true) goto L115;
        if (r21 != 4) goto L114;
        r8 = AndroidUtilities.dp(84.0f);
        goto L116
    L114:
        r8 = r77;
        goto L116
    L102:
        if ((r20 instanceof ChatAttachAlertBotWebViewLayout) == true) goto L104;
        r77 = 0;
        this.hasOverridenWebviewBackgroundColor = false;
        r4.updateColors();
        this.actionBar.invalidate();
        onCurrentLayoutAnimatorChanged();
        goto L105
    L89:
        if (this.nextAttachLayout.getCurrentItemTop() > r20.getButtonsHideOffset()) goto L91;
        boolean r42 = true;
    L92:
        this.currentAttachLayout.onHideShowProgress(1.0f);
        this.nextAttachLayout.onHideShowProgress(1.0f);
        this.currentAttachLayout.onContainerTranslationUpdated(this.currentPanTranslationY);
        this.nextAttachLayout.onContainerTranslationUpdated(this.currentPanTranslationY);
        this.containerView.invalidate();
        this.ATTACH_ALERT_LAYOUT_TRANSLATION.set(this.currentAttachLayout, Float.valueOf(1.0f));
        ActionBar r54 = this.actionBar;
        if (r42 == false) goto L95;
        Integer r43 = 1;
    L96:
        r54.setTag(r43);
        r112.run();
        goto L97
    L95:
        r43 = null;
        goto L96
    L91:
        r42 = false;
        goto L92
    L80:
        this.currentAttachLayout.setTranslationX(-r5);
        AttachAlertLayout r55 = this.nextAttachLayout;
        if (r55 != this.photoLayout) goto L85;
        ChatAttachAlertPhotoLayout.CameraViewInternal r56 = ((ChatAttachAlertPhotoLayout) r55).cameraView;
        if (r56 == null) goto L85;
        r56.setVisibility(0);
        goto L85
    L69:
        if ((this.nextAttachLayout instanceof ChatAttachAlertPhotoLayoutPreview) == true) goto L73;
        if (r23 == false) goto L72;
        AnimatorSet r44 = new AnimatorSet();
        this.nextAttachLayout.setAlpha(0.0f);
        this.nextAttachLayout.setTranslationY(AndroidUtilities.dp(78.0f));
        ObjectAnimator r128 = ObjectAnimator.ofFloat(this.currentAttachLayout, View.TRANSLATION_Y, new float[]{AndroidUtilities.dp(78.0f) + r75});
        ObjectAnimator r10 = ObjectAnimator.ofFloat(this.currentAttachLayout, this.ATTACH_ALERT_LAYOUT_TRANSLATION, new float[]{0.0f, 1.0f});
        ActionBar r138 = this.actionBar;
        r44.playTogether(new Animator[]{r128, r10, ObjectAnimator.ofFloat(r138, View.ALPHA, new float[]{r138.getAlpha(), 0.0f})});
        r44.setDuration(180);
        r44.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r44.addListener(new AnonymousClass28(this, r75, r112));
        this.viewChangeAnimator = r44;
        this.ATTACH_ALERT_LAYOUT_TRANSLATION.set(this.currentAttachLayout, r6);
        r44.start();
        goto L97
    L72:
        r127.setAlpha(0.0f);
        r112.run();
        updateSelectedPosition(0);
        this.containerView.invalidate();
        goto L97
    L49:
        r134 = 4;
        goto L50
    L20:
        r11 = false;
        goto L21
    L14:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L17;
        new PremiumFeatureBottomSheet(this.baseFragment, 39, false).show();
        return;
    }

    private void showMotionHint(boolean r9) {
        HintView2 r0 = this.motionHint;
        if (r0 == null) goto L5;
        r0.hide();
    L5:
        HintView2 r02 = new HintView2(getContext(), 1);
        this.motionHint = r02;
        if (r9 == false) goto L8;
        int r92 = R.string.LivePhotosOn;
    L9:
        r02.setText(AndroidUtilities.replaceTags(LocaleController.getString(r92)));
        this.motionHint.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        this.motionHint.setJointPx(1.0f, -((this.containerView.getWidth() - (this.motionItem.getX() + (this.motionItem.getWidth() / 2.0f))) - AndroidUtilities.dp(14.0f)));
        this.motionHint.setTranslationY(this.selectedMenuItem.getTranslationY());
        this.motionHint.setOnHiddenListener(new ChatAttachAlert$$ExternalSyntheticLambda63(this, r02));
        this.containerView.addView(this.motionHint, LayoutHelper.createFrame(-1, 60.0f, 48, 0.0f, 46.0f, 0.0f, 0.0f));
        this.motionHint.show();
        return;
    L8:
        r92 = R.string.LivePhotosOff;
        goto L9
    }

    private void showPollLayout(boolean r8, Boolean r9) {
        if (this.pollLayout != null) goto L5;
        AttachAlertLayout[] r0 = this.layouts;
        ChatAttachAlert r2 = this;
        ChatAttachAlertPollLayout r1 = new ChatAttachAlertPollLayout(r2, getContext(), false, this.resourcesProvider, r9);
        r2.pollLayout = r1;
        r0[1] = r1;
        r1.setDelegate(new ChatAttachAlert$$ExternalSyntheticLambda78(r2));
    L6:
        r2.showLayout(r2.pollLayout, 9, r8);
        return;
    L5:
        r2 = this;
        goto L6
    }

    private void toggleCaptionAbove() {
        setCaptionAbove(!this.captionAbove);
    }

    private void updateActionBarVisibility(boolean r11, boolean r12) {
        this.animatorActionBarVisible.setValue(r11, r12);
        if (r11 == true) goto L5;
    L6:
        if (r11 == false) goto L8;
        return;
    L8:
        if (this.actionBar.getTag() == null) goto L110;
    L9:
        ActionBar r0 = this.actionBar;
        if (r11 == false) goto L12;
        Integer r3 = 1;
    L13:
        r0.setTag(r3);
        AnimatorSet r02 = this.actionBarAnimation;
        if (r02 == null) goto L17;
        r02.cancel();
        this.actionBarAnimation = null;
    L17:
        if (this.isPhotoPicker == false) goto L19;
    L31:
        boolean r03 = false;
    L33:
        if (this.currentAttachLayout != this.restrictedLayout) goto L35;
        r03 = false;
    L35:
        if (r11 == false) goto L39;
        if (r03 == false) goto L43;
        this.selectedMenuItem.setVisibility(0);
        this.selectedMenuItem.setClickable(true);
    L43:
        BaseFragment r32 = this.baseFragment;
        if (r32 == null) goto L48;
        if (r11 == false) goto L47;
        AndroidUtilities.setLightStatusBar(this, isLightStatusBar());
        goto L48
    L47:
        AndroidUtilities.setLightStatusBar(this, r32.isLightStatusBar());
    L48:
        float r33 = 0.6f;
        float r4 = 0.0f;
        if (r12 == false) goto L73;
        AnimatorSet r122 = new AnimatorSet();
        this.actionBarAnimation = r122;
        if (r11 == false) goto L53;
        float r6 = 1.0f;
    L54:
        r122.setDuration((long) (Math.abs(r6 - this.actionBar.getAlpha()) * 180.0f));
        ArrayList r123 = new ArrayList();
        ActionBar r62 = this.actionBar;
        Property r7 = View.ALPHA;
        if (r11 == false) goto L57;
        float r8 = 1.0f;
    L58:
        r123.add(ObjectAnimator.ofFloat(r62, r7, new float[]{r8}));
        if (r03 == false) goto L71;
        ActionBarMenuItem r04 = this.selectedMenuItem;
        if (r11 == false) goto L63;
        r4 = 1.0f;
    L63:
        r123.add(ObjectAnimator.ofFloat(r04, r7, new float[]{r4}));
        ActionBarMenuItem r05 = this.selectedMenuItem;
        Property r42 = View.SCALE_X;
        if (r11 == false) goto L66;
        float r63 = 1.0f;
    L67:
        r123.add(ObjectAnimator.ofFloat(r05, r42, new float[]{r63}));
        ActionBarMenuItem r06 = this.selectedMenuItem;
        Property r43 = View.SCALE_Y;
        if (r11 == false) goto L70;
        r33 = 1.0f;
    L70:
        r123.add(ObjectAnimator.ofFloat(r06, r43, new float[]{r33}));
        goto L71
    L66:
        r63 = 0.6f;
    L71:
        this.actionBarAnimation.playTogether(r123);
        this.actionBarAnimation.addListener(new AnonymousClass35(this, r11));
        this.actionBarAnimation.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.actionBarAnimation.setDuration(380);
        this.actionBarAnimation.start();
    L107:
        updateCameraButtonVisibility();
        return;
    L57:
        r8 = 0.0f;
        goto L58
    L53:
        r6 = 0.0f;
        goto L54
    L73:
        if (r11 == true) goto L75;
    L81:
        ActionBar r1 = this.actionBar;
        if (r11 == false) goto L84;
        float r2 = 1.0f;
    L85:
        r1.setAlpha(r2);
        if (r03 == false) goto L98;
        ActionBarMenuItem r07 = this.selectedMenuItem;
        if (r11 == false) goto L90;
        r4 = 1.0f;
    L90:
        r07.setAlpha(r4);
        ActionBarMenuItem r08 = this.selectedMenuItem;
        if (r11 == false) goto L93;
        float r13 = 1.0f;
    L94:
        r08.setScaleX(r13);
        ActionBarMenuItem r09 = this.selectedMenuItem;
        if (r11 == false) goto L97;
        r33 = 1.0f;
    L97:
        r09.setScaleY(r33);
        goto L98
    L93:
        r13 = 0.6f;
    L98:
        if (r11 == true) goto L107;
        ActionBarMenuItem r112 = this.searchItem;
        if (r112 == null) goto L103;
        r112.setVisibility(4);
    L103:
        if (this.avatarPicker == 0) goto L105;
    L106:
        this.selectedMenuItem.setVisibility(4);
        goto L107
    L105:
        if (this.menuShowed == true) goto L107;
    L84:
        r2 = 0.0f;
        goto L85
    L75:
        if (this.typeButtonsAvailable == false) goto L81;
        AttachAlertLayout r14 = this.currentAttachLayout;
        if (r14 != null) goto L79;
    L80:
        this.buttonsRecyclerViewWrapper.setVisibility(4);
        goto L81
    L79:
        if (r14.shouldHideBottomButtons() == false) goto L81;
    L39:
        if (this.typeButtonsAvailable == false) goto L43;
        if (this.frameLayout2.getTag() != null) goto L43;
        this.buttonsRecyclerViewWrapper.setVisibility(0);
        goto L43
    L19:
        if (this.storyMediaPicker == true) goto L31;
        if (this.avatarPicker != 0) goto L25;
        if (this.menuShowed == true) goto L31;
    L25:
        if (this.currentAttachLayout != this.photoLayout) goto L31;
        if (this.photosEnabled == false) goto L29;
    L30:
        r03 = true;
        goto L33
    L29:
        if (this.videosEnabled == false) goto L31;
    L12:
        r3 = null;
        goto L13
    L110:
        return;
    L5:
        if (this.actionBar.getTag() == null) goto L9;
        goto L6
    }

    private void updateCameraButtonVisibility() {
        if (this.cameraFloatingButton != null) goto L6;
        return;
    L6:
        if (ExteraConfig.getHideCameraTile() == false) goto L25;
        AttachAlertLayout r0 = this.nextAttachLayout;
        if (r0 != null) goto L13;
        if (this.currentAttachLayout != this.photoLayout) goto L25;
    L14:
        ChatAttachAlertPhotoLayout r02 = this.photoLayout;
        if (r02 == null) goto L25;
        if (r02.deviceHasGoodCamera == false) goto L25;
        if (r02.cameraOpened == true) goto L25;
        if (this.isPhotoPicker == true) goto L25;
        if (r02.getSelectedItemsCount() != 0) goto L25;
        boolean r03 = true;
    L26:
        float r3 = this.buttonsRecyclerViewWrapper.getAlpha();
        if (this.buttonsRecyclerViewWrapper.getVisibility() == 0) goto L29;
        r3 = 0.0f;
    L29:
        float r32 = Math.max(this.buttonsRecyclerViewWrapper.getTranslationY(), (1.0f - r3) * AndroidUtilities.dp(70.0f));
        FragmentFloatingButton r4 = this.cameraFloatingButton;
        if (r03 == false) goto L38;
        boolean r04 = r4.getButtonVisible();
        FragmentFloatingButton r1 = this.cameraFloatingButton;
        if (r04 == true) goto L35;
        r1.setTranslationY(r32);
        this.cameraFloatingButton.setButtonVisible(true, true);
        return;
    L35:
        r1.setTranslationY(r32);
        return;
    L38:
        if (r4.getButtonVisible() == false) goto L41;
        this.cameraFloatingButton.setButtonVisible(false, true);
        return;
    L41:
        return;
    L13:
        if (r0 == this.photoLayout) goto L14;
    L25:
        r03 = false;
        goto L26
    }

    private void updateMotionItem(boolean r6) {
        ChatAttachAlertPhotoLayout r0 = this.photoLayout;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.motionIcon == null) goto L50;
        int r3 = 0;
        if (this.menuShowed == true) goto L10;
    L16:
        boolean r02 = false;
    L17:
        this.motionIcon.setDisabled(true ^ this.photoLayout.areLivePhotosEnabled(), r6);
        float r1 = 0.0f;
        float r2 = 0.6f;
        if (r6 == true) goto L20;
    L33:
        ActionBarMenuItem r62 = this.motionItem;
        if (r02 == true) goto L37;
        r3 = 8;
    L37:
        r62.setVisibility(r3);
        ActionBarMenuItem r63 = this.motionItem;
        if (r02 == false) goto L40;
        r1 = 1.0f;
    L40:
        r63.setAlpha(r1);
        ActionBarMenuItem r64 = this.motionItem;
        if (r02 == false) goto L43;
        float r12 = 1.0f;
    L44:
        r64.setScaleX(r12);
        ActionBarMenuItem r5 = this.motionItem;
        if (r02 == false) goto L47;
        r2 = 1.0f;
    L47:
        r5.setScaleY(r2);
        return;
    L43:
        r12 = 0.6f;
        goto L44
    L20:
        if (this.menuShowed == false) goto L33;
        this.motionItem.setVisibility(0);
        ViewPropertyAnimator r65 = this.motionItem.animate();
        if (r02 == false) goto L24;
        r1 = 1.0f;
    L24:
        ViewPropertyAnimator r66 = r65.alpha(r1);
        if (r02 == false) goto L27;
        float r13 = 1.0f;
    L28:
        ViewPropertyAnimator r67 = r66.scaleX(r13);
        if (r02 == false) goto L31;
        r2 = 1.0f;
    L31:
        r67.scaleY(r2).setDuration(320).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).withEndAction(new ChatAttachAlert$$ExternalSyntheticLambda4(this, r02)).start();
        return;
    L27:
        r13 = 0.6f;
        goto L28
    L10:
        if (this.allowLivePhotos == false) goto L16;
        if (this.currentAttachLayout != r0) goto L16;
        if (r0.hasLivePhotos() == false) goto L16;
        r02 = true;
        goto L17
    }

    private void updateSelectedPosition(int r15) {
        float r0 = this.animatorActionBarVisible.getFloatValue();
        if (r15 != 0) goto L5;
        AttachAlertLayout r1 = this.currentAttachLayout;
    L6:
        if (r1 != null) goto L8;
        return;
    L8:
        if (r1.getVisibility() != 0) goto L120;
        int r152 = getScrollOffsetY(r15);
        if (r1 != this.pollLayout) goto L13;
    L16:
        AndroidUtilities.dp(13.0f);
        AndroidUtilities.dp(11.0f);
    L17:
        ActionBar.getCurrentActionBarHeight();
        float r12 = 1.0f;
        float r2 = 1.0f - r0;
        this.cornerRadius = r2;
        if (AndroidUtilities.isTablet() == false) goto L20;
        int r3 = 16;
    L25:
        if (this.actionBar.getAlpha() == 0.0f) goto L27;
        float r4 = 0.0f;
    L29:
        if (this.menuShowed == true) goto L31;
    L35:
        this.selectedMenuItem.setTranslationY(((ActionBar.getCurrentActionBarHeight() - AndroidUtilities.dp(3.0f)) - AndroidUtilities.dp(r3 + 37)) + this.currentPanTranslationY);
    L36:
        ActionBarMenuItem r7 = this.motionItem;
        if (r7 == null) goto L39;
        r7.setTranslationY(this.selectedMenuItem.getTranslationY());
    L39:
        HintView2 r72 = this.motionHint;
        if (r72 == null) goto L43;
        r72.setTranslationY(this.selectedMenuItem.getTranslationY());
    L43:
        if (this.isPhotoPicker == true) goto L45;
    L53:
        float r73 = 0.0f;
    L54:
        ActionBarMenuItem r8 = this.searchItem;
        if (r8 == null) goto L57;
        r8.setTranslationY(((ActionBar.getCurrentActionBarHeight() - AndroidUtilities.dp(4.0f)) - AndroidUtilities.dp(r3 + 37)) + this.currentPanTranslationY);
    L57:
        float r153 = ((((r152 - AndroidUtilities.dp((r3 * r0) + 25.0f)) + r4) + this.currentPanTranslationY) + r73) - (this.topCommentContainer.getMeasuredHeight() * this.topCommentContainer.getAlpha());
        this.baseSelectedTextViewTranslationY = r153;
        this.headerView.setTranslationY(Math.max(this.currentPanTranslationY, r153));
        this.topCommentContainer.setTranslationY(Math.max(ActionBar.getCurrentActionBarHeight() + this.currentPanTranslationY, (this.baseSelectedTextViewTranslationY + (AndroidUtilities.dp(26.0f) * this.headerView.getAlpha())) + AndroidUtilities.dp(8.0f)));
        if (this.captionAbove == false) goto L60;
        updateCommentTextViewPosition();
    L60:
        checkUi_writeButtonContainerY();
        int r42 = 59;
        int r82 = 0;
        if (this.pollLayout != null) goto L63;
    L78:
        float r154 = 0.0f;
        float r11 = 0.0f;
    L80:
        if (this.todoLayout != null) goto L82;
    L97:
        float r02 = 0.0f;
        float r13 = 0.0f;
    L99:
        if (this.doneItem != null) goto L101;
    L117:
        this.doneItemAlphaByLayout = Math.max(r02, r154);
        checkUi_doneItemVisibility();
        return;
    L101:
        if (LocaleController.isRTL == false) goto L103;
        r82 = (this.containerView.getMeasuredWidth() - this.doneItem.getMeasuredWidth()) - AndroidUtilities.dp(62.0f);
    L103:
        TextView r32 = this.doneItem;
        if (r154 > 0.0f) goto L106;
    L108:
        if (r154 > 0.0f) goto L112;
        r11 = 0.0f;
    L112:
        if (r02 > 0.0f) goto L115;
        r13 = 0.0f;
    L115:
        float r14 = Math.max(r11, r13);
    L116:
        r32.setTranslationY(Math.max(0.0f, r14) + this.currentPanTranslationY);
        this.doneItem.setTranslationX(-(AndroidUtilities.dp((7.0f * r2) + 12.0f) + (r82 * r2)));
        goto L117
    L106:
        if (r02 <= 0.0f) goto L108;
        r14 = AndroidUtilities.lerp(r11, r13, r02);
        goto L116
    L82:
        if (AndroidUtilities.isTablet() == false) goto L84;
        r42 = 63;
    L87:
        ChatAttachAlertPollLayout r6 = this.todoLayout;
        if (r6 != this.nextAttachLayout) goto L91;
        r13 = (r6.getTranslationY() + getScrollOffsetY(1)) - AndroidUtilities.dp(((r42 * r0) + 7.0f) - (r2 * 12.0f));
        r02 = this.translationProgress;
        goto L99
    L91:
        if (r6 != this.currentAttachLayout) goto L97;
        float r03 = (r6.getTranslationY() + getScrollOffsetY(0)) - AndroidUtilities.dp(((r42 * r0) + 7.0f) - (r2 * 12.0f));
        if (this.nextAttachLayout == null) goto L96;
        r12 = 1.0f - this.translationProgress;
    L96:
        float r132 = r12;
        r13 = r03;
        r02 = r132;
        goto L99
    L84:
        Point r74 = AndroidUtilities.displaySize;
        if (r74.x <= r74.y) goto L87;
        r42 = 53;
        goto L87
    L63:
        if (AndroidUtilities.isTablet() == false) goto L65;
        int r155 = 63;
    L69:
        ChatAttachAlertPollLayout r112 = this.pollLayout;
        if (r112 != this.nextAttachLayout) goto L73;
        r11 = (r112.getTranslationY() + getScrollOffsetY(1)) - AndroidUtilities.dp(((r155 * r0) + 7.0f) - (r2 * 12.0f));
        r154 = this.translationProgress;
        goto L80
    L73:
        if (r112 != this.currentAttachLayout) goto L78;
        r11 = (r112.getTranslationY() + getScrollOffsetY(0)) - AndroidUtilities.dp(((r155 * r0) + 7.0f) - (r2 * 12.0f));
        if (this.nextAttachLayout != null) goto L77;
        r154 = 1.0f;
        goto L80
    L77:
        r154 = 1.0f - this.translationProgress;
        goto L80
    L65:
        Point r156 = AndroidUtilities.displaySize;
        if (r156.x <= r156.y) goto L68;
        r155 = 53;
        goto L69
    L68:
        r155 = 59;
        goto L69
    L45:
        if (this.openTransitionFinished == false) goto L53;
        AttachAlertLayout r75 = this.nextAttachLayout;
        if (r75 != null) goto L49;
    L51:
        if (r75 == null) goto L53;
        r73 = r75.getTranslationY();
        goto L54
    L49:
        if (this.currentAttachLayout == null) goto L51;
        r73 = Math.min(r75.getTranslationY(), this.currentAttachLayout.getTranslationY());
        goto L54
    L31:
        if (this.avatarPicker != 0) goto L35;
        if (this.storyMediaPicker == true) goto L35;
        this.selectedMenuItem.setTranslationY(Math.max((ActionBar.getCurrentActionBarHeight() - AndroidUtilities.dp(3.0f)) - AndroidUtilities.dp(r3 + 37), ((r152 - AndroidUtilities.dp((r3 * r0) + 37.0f)) + r4) - (this.topCommentContainer.getMeasuredHeight() * this.topCommentContainer.getAlpha())) + this.currentPanTranslationY);
        goto L36
    L27:
        r4 = AndroidUtilities.dp((1.0f - this.headerView.getAlpha()) * 26.0f);
        goto L29
    L20:
        Point r33 = AndroidUtilities.displaySize;
        if (r33.x <= r33.y) goto L23;
        r3 = 6;
        goto L25
    L23:
        r3 = 12;
        goto L25
    L13:
        if (r1 == this.todoLayout) goto L16;
        AndroidUtilities.dp(39.0f);
        AndroidUtilities.dp(43.0f);
        goto L17
    L120:
        return;
    L5:
        r1 = this.nextAttachLayout;
        goto L6
    }

    private void updatedTopCaptionHeight() {
        int r0 = 0;
        updateSelectedPosition(0);
        this.sizeNotifierFrameLayout.invalidate();
        this.topCommentContainer.invalidate();
        ChatAttachAlertPhotoLayout r1 = this.photoLayout;
        if (r1 == null) goto L12;
        r1.checkCameraViewPosition();
        RecyclerListView r12 = this.photoLayout.gridView;
        if (r12 == null) goto L12;
        if (r12.getFastScroll() == null) goto L12;
        RecyclerListView.FastScroll r13 = this.photoLayout.gridView.getFastScroll();
        int r2 = ActionBar.getCurrentActionBarHeight() + this.photoLayout.listAdditionalH;
        if (this.captionAbove == false) goto L11;
        r0 = (int) (this.topCommentContainer.getMeasuredHeight() * this.topCommentContainer.getAlpha());
    L11:
        r13.topOffset = r2 + r0;
        this.photoLayout.gridView.getFastScroll().invalidate();
    L12:
        updateCommentTextViewPosition();
        checkUi_writeButtonContainerY();
    }

    public void applyCaption() {
        if (getCommentView().length() > 0) goto L5;
        return;
    L5:
        this.currentAttachLayout.applyCaption(getCommentView().getText());
    }

    public void avatarFor(ImageUpdater.AvatarFor r1) {
        this.setAvatarFor = r1;
    }

    public void blur3_InvalidateBlur() {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        return;
    L5:
        if (this.scrollableViewNoiseSuppressor == null) goto L25;
        ViewPositionWatcher.computeRectInParent(this.buttonsRecyclerViewWrapper, this.containerView, this.iBlur3PositionMainTabs);
        this.iBlur3PositionActionBar.set(0.0f, 0.0f, this.containerView.getMeasuredWidth(), this.actionBar.getMeasuredHeight());
        this.iBlur3PositionActionBar.inset(0.0f, -AndroidUtilities.dp(48.0f));
        int r0 = Math.max(AndroidUtilities.navigationBarHeight, getEmojiPadding()) + AndroidUtilities.dp(180.0f);
        this.iBlur3PositionMainTabs.set(0.0f, this.containerView.getMeasuredHeight() - r0, this.containerView.getMeasuredWidth(), this.containerView.getMeasuredHeight());
        RectF r02 = this.iBlur3PositionMainTabs;
        if (LiteMode.isEnabled(262144) == false) goto L10;
        float r2 = 0.0f;
    L11:
        r02.inset(0.0f, r2);
        AttachAlertLayout r03 = this.currentAttachLayout;
        ChatAttachAlertPhotoLayout r22 = this.photoLayout;
        if (r03 != r22) goto L17;
        if (r22 == null) goto L17;
        if (r22.gridView.getFastScroll() == null) goto L17;
        this.photoLayout.gridView.getFastScroll().fillDrawablesRect(this.iBlur3PositionFastScroll);
        RecyclerListView.FastScroll r04 = this.photoLayout.gridView.getFastScroll();
        ViewGroup r23 = this.containerView;
        RectF r4 = AndroidUtilities.rectTmp;
        ViewPositionWatcher.computeRectInParent(r04, r23, r4);
        this.iBlur3PositionFastScroll.offset(r4.left, r4.top);
        this.iBlur3PositionFastScroll.inset(-AndroidUtilities.dp(48.0f), -AndroidUtilities.dp(48.0f));
        RectF r05 = this.iBlur3PositionFastScroll;
        r05.left = Math.max(0.0f, r05.left);
        this.iBlur3PositionFastScroll.right = Math.min(this.containerView.getMeasuredWidth(), this.iBlur3PositionFastScroll.right);
        boolean r06 = true;
    L18:
        ArrayList<RectF> r1 = this.iBlur3Positions;
        if (r06 == false) goto L21;
        int r07 = 3;
    L22:
        this.scrollableViewNoiseSuppressor.setupRenderNodes(this.iBlur3PositionsMerged, RectFMergeBounding.mergeOverlapping(r1, r07, this.iBlur3PositionsMerged));
        this.scrollableViewNoiseSuppressor.invalidateResultRenderNodes(this.iBlur3Capture, this.containerView.getMeasuredWidth(), this.containerView.getMeasuredHeight());
        return;
    L21:
        r07 = 2;
    L17:
        r06 = false;
        goto L18
    L10:
        r2 = -AndroidUtilities.dp(48.0f);
        goto L11
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet.BottomSheetDelegateInterface
    public boolean canDismiss() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithTouchOutside() {
        return this.currentAttachLayout.canDismissWithTouchOutside();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void cancelSheetAnimation() {
        AnimatorSet r0 = this.currentSheetAnimation;
        if (r0 == null) goto L12;
        r0.cancel();
        SpringAnimation r02 = this.appearSpringAnimation;
        if (r02 == null) goto L7;
        r02.cancel();
    L7:
        AnimatorSet r03 = this.buttonsAnimation;
        if (r03 == null) goto L10;
        r03.cancel();
    L10:
        this.currentSheetAnimation = null;
        this.currentSheetAnimationType = 0;
        return;
    }

    public boolean checkCanRemoveRestrictionsByBoosts() {
        BaseFragment r1 = this.baseFragment;
        if ((r1 instanceof ChatActivity) == true) goto L5;
        return false;
    L5:
        if (((ChatActivity) r1).checkCanRemoveRestrictionsByBoosts() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean checkCaption(CharSequence r8) {
        BaseFragment r0 = this.baseFragment;
        if ((r0 instanceof ChatActivity) == false) goto L6;
        long r2 = ((ChatActivity) r0).getDialogId();
        return ChatActivityEnterView.checkPremiumAnimatedEmoji(this.currentAccount, r2, this.baseFragment, this.sizeNotifierFrameLayout, r8);
    L6:
        return false;
    }

    public void checkColors() {
        RecyclerListView r0 = this.buttonsRecyclerView;
        if (r0 != null) goto L5;
        return;
    L5:
        int r02 = r0.getChildCount();
        boolean r1 = false;
        int r2 = 0;
    L6:
        if (r2 >= r02) goto L8;
        applyAttachButtonColors(this.buttonsRecyclerView.getChildAt(r2));
        r2 = r2 + 1;
        goto L6
    L8:
        TextView r03 = this.selectedTextView;
        if (this.forceDarkTheme == false) goto L12;
        int r22 = Theme.key_voipgroup_actionBarItems;
    L13:
        r03.setTextColor(getThemedColor(r22));
        TextView r04 = this.mediaPreviewTextView;
        if (this.forceDarkTheme == false) goto L17;
        int r23 = Theme.key_voipgroup_actionBarItems;
    L18:
        r04.setTextColor(getThemedColor(r23));
        this.doneItem.setTextColor(getThemedColor(Theme.key_featuredStickers_buttonText));
        ActionBarMenuItem r05 = this.selectedMenuItem;
        if (this.forceDarkTheme == false) goto L22;
        int r24 = Theme.key_voipgroup_actionBarItems;
    L23:
        r05.setIconColor(getThemedColor(r24));
        Drawable r06 = this.selectedMenuItem.getBackground();
        if (this.forceDarkTheme == false) goto L27;
        int r25 = Theme.key_voipgroup_actionBarItemsSelector;
    L28:
        Theme.setDrawableColor(r06, getThemedColor(r25));
        ActionBarMenuItem r07 = this.selectedMenuItem;
        int r26 = Theme.key_actionBarDefaultSubmenuItem;
        r07.setPopupItemsColor(getThemedColor(r26), false);
        this.selectedMenuItem.setPopupItemsColor(getThemedColor(r26), true);
        this.selectedMenuItem.redrawPopup(getThemedColor(Theme.key_actionBarDefaultSubmenuBackground));
        ActionBarMenuItem r08 = this.motionItem;
        if (r08 != null) goto L31;
    L36:
        ActionBarMenuItem r09 = this.searchItem;
        if (r09 != null) goto L39;
    L49:
        this.commentTextView.updateColors();
        this.buttonsRecyclerView.setGlowColor(getThemedColor(Theme.key_dialogScrollGlow));
        ActionBar r010 = this.actionBar;
        if (this.forceDarkTheme == false) goto L53;
        int r27 = Theme.key_voipgroup_actionBarItems;
    L54:
        r010.setItemsColor(getThemedColor(r27), false);
        ActionBar r011 = this.actionBar;
        if (this.forceDarkTheme == false) goto L58;
        int r28 = Theme.key_voipgroup_actionBarItemsSelector;
    L59:
        r011.setItemsBackgroundColor(getThemedColor(r28), false);
        ActionBar r012 = this.actionBar;
        if (this.forceDarkTheme == false) goto L63;
        int r29 = Theme.key_voipgroup_actionBarItems;
    L64:
        r012.setTitleColor(getThemedColor(r29));
        int r013 = getShadowDrawableColor();
        Theme.setDrawableColor(this.shadowDrawable, r013);
        checkColorSourceColor(r013);
        this.containerView.invalidate();
        int r014 = 0;
    L65:
        AttachAlertLayout[] r210 = this.layouts;
        if (r014 >= r210.length) goto L72;
        AttachAlertLayout r211 = r210[r014];
        if (r211 == null) goto L70;
        r211.checkColors();
    L70:
        r014 = r014 + 1;
        goto L65
    L72:
        if (Build.VERSION.SDK_INT < 30) goto L78;
        this.navBarColorKey = -1;
        this.navBarColor = getThemedColor(Theme.key_dialogBackgroundGray);
        AndroidUtilities.setNavigationBarColor(this, getThemedColor(Theme.key_dialogBackground), false);
        if (AndroidUtilities.computePerceivedBrightness(this.navBarColor) <= 0.721d) goto L76;
        r1 = true;
    L76:
        AndroidUtilities.setLightNavigationBar(this, r1);
        return;
    L78:
        fixNavigationBar(getThemedColor(Theme.key_dialogBackground));
        return;
    L63:
        r29 = Theme.key_dialogTextBlack;
        goto L64
    L58:
        r28 = Theme.key_dialogButtonSelector;
        goto L59
    L53:
        r27 = Theme.key_dialogTextBlack;
        goto L54
    L39:
        if (this.forceDarkTheme == false) goto L42;
        int r212 = Theme.key_voipgroup_actionBarItems;
    L43:
        r09.setIconColor(getThemedColor(r212));
        Drawable r015 = this.searchItem.getBackground();
        if (this.forceDarkTheme == false) goto L47;
        int r213 = Theme.key_voipgroup_actionBarItemsSelector;
    L48:
        Theme.setDrawableColor(r015, getThemedColor(r213));
        goto L49
    L47:
        r213 = Theme.key_dialogButtonSelector;
        goto L48
    L42:
        r212 = Theme.key_dialogTextBlack;
        goto L43
    L31:
        if (this.forceDarkTheme == false) goto L34;
        int r214 = Theme.key_voipgroup_actionBarItems;
    L35:
        r08.setIconColor(getThemedColor(r214));
        goto L36
    L34:
        r214 = Theme.key_dialogTextBlack;
        goto L35
    L27:
        r25 = Theme.key_dialogButtonSelector;
        goto L28
    L22:
        r24 = Theme.key_dialogTextBlack;
        goto L23
    L17:
        r23 = Theme.key_dialogTextBlack;
        goto L18
    L12:
        r22 = Theme.key_dialogTextBlack;
        goto L13
    }

    public void checkUi_moveCaptionButtonVisibility() {
        float r0 = this.animatorCaptionNotEmpty.getFloatValue();
        float r1 = this.animatorToggleCaptionSupported.getFloatValue();
        float r2 = FBool.not(this.animatorEphemeralMessageVisibility.getFloatValue());
        if (this.restrictEphemeralMessageTypes == false) goto L5;
        float r3 = 0.0f;
    L6:
        ImageView r4 = this.moveCaptionButton;
        FragmentFloatingButton.setAnimatedVisibility(r4, ((r0 * r1) * r2) * r3);
        return;
    L5:
        r3 = 1.0f;
        goto L6
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.reloadInlineHints) goto L5;
    L13:
        ButtonsAdapter r0 = this.buttonsAdapter;
        if (r0 == null) goto L18;
        r0.notifyDataSetChanged();
        return;
    L18:
        return;
    L5:
        if (r1 == NotificationCenter.attachMenuBotsDidLoad) goto L13;
        if (r1 == NotificationCenter.quickRepliesUpdated) goto L13;
        if (r1 != NotificationCenter.currentUserPremiumStatusChanged) goto L17;
        this.currentLimit = MessagesController.getInstance(UserConfig.selectedAccount).getCaptionMaxLengthLimit();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        if (this.currentAttachLayout.onDismiss() == false) goto L5;
        return;
    L5:
        if (isDismissed() == true) goto L50;
        EditTextEmoji r0 = this.commentTextView;
        if (r0 == null) goto L10;
        AndroidUtilities.hideKeyboard(r0.getEditText());
    L10:
        EditTextEmoji r02 = this.topCommentTextView;
        if (r02 == null) goto L13;
        AndroidUtilities.hideKeyboard(r02.getEditText());
    L13:
        this.botAttachLayouts.clear();
        BaseFragment r03 = this.baseFragment;
        if (r03 != null) goto L17;
        r03 = LaunchActivity.getLastFragment();
    L17:
        if (this.allowPassConfirmationAlert == true) goto L30;
        if (r03 == null) goto L30;
        if (this.currentAttachLayout.getSelectedItemsCount() <= 0) goto L30;
        if (this.isPhotoPicker == true) goto L30;
        if (this.confirmationAlertShown == true) goto L51;
        this.confirmationAlertShown = true;
        AlertDialog r04 = new AlertDialog.Builder(r03.getParentActivity(), this.resourcesProvider).setTitle(LocaleController.getString(R.string.DiscardSelectionAlertTitle)).setMessage(LocaleController.getString(R.string.DiscardSelectionAlertMessage)).setPositiveButton(LocaleController.getString(R.string.Discard), new ChatAttachAlert$$ExternalSyntheticLambda0(this)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).setOnCancelListener(new ChatAttachAlert$$ExternalSyntheticLambda1(this)).setOnPreDismissListener(new ChatAttachAlert$$ExternalSyntheticLambda2(this)).create();
        r04.show();
        TextView r05 = (TextView) r04.getButton(-1);
        if (r05 == null) goto L49;
        r05.setTextColor(getThemedColor(Theme.key_text_RedBold));
        return;
    L49:
        return;
    L51:
        return;
    L30:
        int r3 = 0;
    L31:
        AttachAlertLayout[] r4 = this.layouts;
        if (r3 >= r4.length) goto L39;
        AttachAlertLayout r42 = r4[r3];
        if (r42 == null) goto L38;
        if (this.currentAttachLayout == r42) goto L38;
        r42.onDismiss();
    L38:
        r3 = r3 + 1;
        goto L31
    L39:
        AndroidUtilities.setNavigationBarColor(this, ColorUtils.setAlphaComponent(getThemedColor(Theme.key_windowBackgroundGray), 0), true, new ChatAttachAlert$$ExternalSyntheticLambda3(this));
        if (r03 == null) goto L42;
        AndroidUtilities.setLightStatusBar(this, r03.isLightStatusBar());
    L42:
        this.captionLimitBulletinShown = false;
        super.dismiss();
        this.allowPassConfirmationAlert = false;
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss(boolean r1) {
        if (r1 == false) goto L4;
        this.allowPassConfirmationAlert = r1;
    L4:
        dismiss();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissInternal() {
        ChatAttachViewDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.doOnIdle(new ChatAttachAlert$$ExternalSyntheticLambda5(this));
        return;
    L6:
        removeFromRoot();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissWithButtonClick(int r1) {
        super.dismissWithButtonClick(r1);
        this.currentAttachLayout.onDismissWithButtonClick(r1);
    }

    public void enableDefaultMode() {
        this.typeButtonsAvailable = true;
        this.buttonsRecyclerViewWrapper.setVisibility(0);
        this.avatarPicker = 0;
        this.isPhotoPicker = false;
        this.isStickerMode = false;
        this.allowLivePhotos = true;
        this.customStickerHandler = null;
        if (this.optionsItem == null) goto L6;
        this.selectedTextView.setTranslationY(0.0f);
        this.optionsItem.setVisibility(8);
        return;
    }

    public void enablePollAttachMode(int r4) {
        this.typeButtonsAvailable = true;
        this.buttonsRecyclerViewWrapper.setVisibility(0);
        this.isPollAttach = true;
        this.pollAllowedLayouts = r4;
        this.avatarPicker = 0;
        this.isPhotoPicker = false;
        this.isStickerMode = false;
        this.customStickerHandler = null;
        if (this.optionsItem == null) goto L6;
        this.selectedTextView.setTranslationY(0.0f);
        this.optionsItem.setVisibility(8);
        return;
    }

    public void enableStickerMode(Utilities.Callback2<String, TLRPC.InputDocument> r4) {
        this.selectedTextView.setText(LocaleController.getString(R.string.ChoosePhotoForSticker));
        this.typeButtonsAvailable = false;
        this.buttonsRecyclerViewWrapper.setVisibility(8);
        this.avatarPicker = 1;
        this.isPhotoPicker = true;
        this.isStickerMode = true;
        this.allowLivePhotos = false;
        this.customStickerHandler = r4;
        if (this.optionsItem == null) goto L6;
        this.selectedTextView.setTranslationY(-AndroidUtilities.dp(8.0f));
        this.optionsItem.setVisibility(0);
        this.optionsItem.setClickable(true);
        this.optionsItem.setAlpha(1.0f);
        this.optionsItem.setScaleX(1.0f);
        this.optionsItem.setScaleY(1.0f);
        return;
    }

    public int getAdditionalMessagesCount() {
        BaseFragment r1 = this.baseFragment;
        if ((r1 instanceof ChatActivity) == false) goto L8;
        MessagePreviewParams r12 = ((ChatActivity) r1).messagePreviewParams;
        if (r12 != null) goto L7;
        return 0;
    L7:
        return r12.getForwardedMessagesCount();
    L8:
        return 0;
    }

    public ImageUpdater.AvatarFor getAvatarFor() {
        return this.setAvatarFor;
    }

    public BaseFragment getBaseFragment() {
        return this.baseFragment;
    }

    public TLRPC.Chat getChat() {
        BaseFragment r0 = this.baseFragment;
        if ((r0 instanceof ChatActivity) == false) goto L7;
        return ((ChatActivity) r0).getCurrentChat();
    L7:
        return MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId));
    }

    public float getClipLayoutBottom() {
        return this.frameLayout2.getMeasuredHeight() - ((this.frameLayout2.getMeasuredHeight() - AndroidUtilities.dp(84.0f)) * (1.0f - this.frameLayout2.getAlpha()));
    }

    public EditTextEmoji getCommentTextView() {
        return this.commentTextView;
    }

    public int getCommentTextViewTop() {
        return this.commentTextViewLocation[1];
    }

    public EditTextEmoji getCommentView() {
        if (this.captionAbove == false) goto L11;
        AttachAlertLayout r0 = this.currentAttachLayout;
        if (r0 == this.photoLayout) goto L9;
        if (r0 != this.photoPreviewLayout) goto L11;
    L9:
        return this.topCommentTextView;
    L11:
        return this.commentTextView;
    }

    public AttachAlertLayout getCurrentAttachLayout() {
        return this.currentAttachLayout;
    }

    public long getDialogId() {
        BaseFragment r0 = this.baseFragment;
        if ((r0 instanceof ChatActivity) == false) goto L7;
        return ((ChatActivity) r0).getDialogId();
    L7:
        return this.dialogId;
    }

    public ChatAttachAlertDocumentLayout getDocumentLayout() {
        return this.documentLayout;
    }

    public MessageObject getEditingMessageObject() {
        return this.editingMessageObject;
    }

    public ChatAttachAlertPhotoLayout getPhotoLayout() {
        return this.photoLayout;
    }

    public ChatAttachAlertPhotoLayoutPreview getPhotoPreviewLayout() {
        return this.photoPreviewLayout;
    }

    public TextView getSelectedTextView() {
        return this.selectedTextView;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r0 = new ArrayList();
        int r1 = 0;
    L3:
        AttachAlertLayout[] r2 = this.layouts;
        if (r1 >= r2.length) goto L11;
        AttachAlertLayout r22 = r2[r1];
        if (r22 == null) goto L10;
        ArrayList<ThemeDescription> r23 = r22.getThemeDescriptions();
        if (r23 == null) goto L10;
        r0.addAll(r23);
    L10:
        r1 = r1 + 1;
        goto L3
    L11:
        r0.add(new ThemeDescription(this.container, 0, null, null, null, null, Theme.key_dialogBackgroundGray));
        return r0;
    }

    public int getTypeButtonsHeight() {
        if (this.typeButtonsAvailable == true) goto L5;
        return 0;
    L5:
        return AndroidUtilities.dp(62.0f);
    }

    public boolean hasCaption() {
        ChatAttachAlertPhotoLayout r0 = this.photoLayout;
        if (r0 != null) goto L5;
        return false;
    L5:
        HashMap<Object, Object> r02 = r0.getSelectedPhotos();
        ArrayList<Object> r2 = this.photoLayout.getSelectedPhotosOrder();
        if (r02.isEmpty() == true) goto L33;
        int r3 = 0;
        boolean r4 = false;
    L9:
        if (r3 >= Math.ceil(r02.size() / 10.0f)) goto L32;
        int r6 = r3 * 10;
        int r5 = Math.min(10, r02.size() - r6);
        Utilities.random.nextLong();
        int r7 = 0;
    L11:
        if (r7 >= r5) goto L31;
        int r8 = r6 + r7;
        if (r8 >= r2.size()) goto L30;
        CharSequence r82 = ((MediaController.PhotoEntry) r02.get(r2.get(r8))).caption;
        if (r82 != null) goto L18;
        String r83 = _UrlKt.FRAGMENT_ENCODE_SET;
    L20:
        if (getCommentView() == null) goto L26;
        if (TextUtils.isEmpty(r83) == false) goto L26;
        if (r7 != 0) goto L26;
        r83 = getCommentView().getText().toString();
    L26:
        if (TextUtils.isEmpty(r83) == true) goto L30;
        if (r4 == true) goto L28;
        r4 = true;
        goto L30
    L28:
        return false;
    L18:
        r83 = r82.toString();
    L30:
        r7 = r7 + 1;
        goto L11
    L31:
        r3 = r3 + 1;
        goto L9
    L32:
        return r4;
    L33:
        return false;
    }

    public void init() {
        ChatActivityEnterView.SendButton r1 = this.writeButton;
        this.effectId = 0;
        r1.setEffect(0);
        this.botButtonWasVisible = false;
        this.botButtonProgressWasVisible = false;
        this.botMainButtonOffsetY = 0.0f;
        this.botMainButtonTextView.setVisibility(8);
        this.botProgressView.setAlpha(0.0f);
        this.botProgressView.setScaleX(0.1f);
        this.botProgressView.setScaleY(0.1f);
        this.botProgressView.setVisibility(8);
        this.buttonsRecyclerViewWrapper.setAlpha(1.0f);
        this.buttonsRecyclerViewWrapper.setTranslationY(0.0f);
        int r5 = 0;
    L4:
        if (r5 >= this.botAttachLayouts.size()) goto L7;
        this.botAttachLayouts.valueAt(r5).setMeasureOffsetY(0);
        r5 = r5 + 1;
        goto L4
    L7:
        if (this.avatarPicker == 2) goto L17;
        BaseFragment r52 = this.baseFragment;
        if ((r52 instanceof ChatActivity) == false) goto L11;
        TLRPC.Chat r2 = ((ChatActivity) r52).getCurrentChat();
        TLRPC.User r3 = ((ChatActivity) this.baseFragment).getCurrentUser();
    L19:
        if ((this.baseFragment instanceof ChatActivity) == true) goto L21;
    L22:
        if (r2 != null) goto L24;
        if (r3 != null) goto L24;
    L49:
        if (this.restrictEphemeralMessageTypes == false) goto L52;
        this.pollsEnabled = false;
        this.todoEnabled = false;
    L52:
        if ((this.baseFragment instanceof ChatActivity) == true) goto L54;
    L55:
        EditTextEmoji r22 = this.commentTextView;
        if (this.allowEnterCaption == false) goto L58;
        int r53 = 0;
    L59:
        r22.setVisibility(r53);
    L60:
        this.photoLayout.onInit(this.videosEnabled, this.photosEnabled, this.documentsEnabled);
        this.commentTextView.hidePopup(true);
        this.topCommentTextView.hidePopup(true);
        this.enterCommentEventSent = false;
        setFocusable(false);
        if (this.isStoryLocationPicker == true) goto L98;
        if (this.isBizLocationPicker == true) goto L98;
        if (this.isLocationPicker == true) goto L98;
        if (this.isStoryAudioPicker == false) goto L71;
        openAudioLayout(false);
        AttachAlertLayout r23 = this.audioLayout;
        this.selectedId = 3;
    L112:
        FrameLayout r32 = this.buttonsRecyclerViewWrapper;
        if (this.typeButtonsAvailable == false) goto L115;
        int r4 = 0;
    L116:
        r32.setVisibility(r4);
        if (this.typeButtonsAvailable == false) goto L120;
        this.buttonsRecyclerViewWrapper.setAlpha(1.0f);
        this.buttonsRecyclerViewWrapper.setTranslationY(0.0f);
    L120:
        if (this.currentAttachLayout != r23) goto L122;
    L132:
        AttachAlertLayout r24 = this.currentAttachLayout;
        ChatAttachAlertPhotoLayout r33 = this.photoLayout;
        if (r24 == r33) goto L135;
        r33.setCheckCameraWhenShown(true);
    L135:
        updateCountButton(0);
        this.buttonsAdapter.notifyDataSetChanged();
        getCommentView().setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.buttonsLayoutManager.scrollToPositionWithOffset(0, DurationKt.NANOS_IN_MILLIS);
        return;
    L122:
        if (this.actionBar.isSearchFieldVisible() == false) goto L124;
        this.actionBar.closeSearchField();
    L124:
        this.containerView.removeView(this.currentAttachLayout);
        this.currentAttachLayout.onHide();
        this.currentAttachLayout.setVisibility(8);
        this.currentAttachLayout.onHidden();
        this.currentAttachLayout = r23;
        setAllowNestedScroll(true);
        if (this.currentAttachLayout.getParent() != null) goto L127;
        this.containerView.addView(this.currentAttachLayout, 0, LayoutHelper.createFrame(-1, -1.0f));
    L127:
        r23.setAlpha(1.0f);
        r23.setVisibility(0);
        r23.onShow(null);
        r23.onShown();
        ActionBar r34 = this.actionBar;
        if (r23.needsActionBar() == 0) goto L130;
        int r25 = 0;
    L131:
        r34.setVisibility(r25);
        setCaptionAbove(this.captionAbove, false);
        updateDoneItemEnabled();
        updateCameraButtonVisibility();
        goto L132
    L130:
        r25 = 4;
        goto L131
    L115:
        r4 = 8;
        goto L116
    L71:
        if (this.isSoundPicker == false) goto L73;
        openDocumentsLayout(false);
        r23 = this.documentLayout;
        this.selectedId = 4;
        goto L112
    L73:
        MessageObject r26 = this.editingMessageObject;
        if (r26 == null) goto L90;
        int r15 = this.editType;
        if (r15 != (-1)) goto L84;
        this.typeButtonsAvailable = true;
        if (r26.isMusic() == false) goto L81;
        openAudioLayout(false);
        r23 = this.audioLayout;
        this.selectedId = 3;
        goto L112
    L81:
        if (this.editingMessageObject.isDocument() == false) goto L83;
        openDocumentsLayout(false);
        r23 = this.documentLayout;
        this.selectedId = 4;
        goto L112
    L83:
        r23 = this.photoLayout;
        this.selectedId = 1;
        goto L112
    L84:
        if (r15 != 2) goto L86;
        openAudioLayout(false);
        r23 = this.audioLayout;
        this.selectedId = 3;
    L89:
        this.typeButtonsAvailable = false;
        goto L112
    L86:
        if (r15 != 1) goto L88;
        openDocumentsLayout(false);
        r23 = this.documentLayout;
        this.selectedId = 4;
        goto L89
    L88:
        r23 = this.photoLayout;
        this.selectedId = 1;
        goto L89
    L90:
        r23 = this.photoLayout;
        if (this.avatarPicker == 0) goto L93;
    L95:
        boolean r9 = false;
    L96:
        this.typeButtonsAvailable = r9;
        this.selectedId = 1;
        goto L112
    L93:
        if (this.storyMediaPicker == true) goto L95;
        r9 = true;
    L98:
        if (this.locationLayout != null) goto L111;
        AttachAlertLayout[] r27 = this.layouts;
        Context r42 = getContext();
        Theme.ResourcesProvider r92 = this.resourcesProvider;
        if (this.isPollAttach == false) goto L102;
    L106:
        boolean r11 = false;
    L107:
        ChatAttachAlertLocationLayout r35 = new ChatAttachAlertLocationLayout(this, r42, r92, r11);
        this.locationLayout = r35;
        r27[5] = r35;
        ChatAttachAlertLocationLayout.LocationActivityDelegate r28 = this.locationActivityDelegate;
        if (r28 == null) goto L110;
        r35.setDelegate(r28);
        goto L111
    L110:
        r35.setDelegate(new ChatAttachAlert$$ExternalSyntheticLambda45(this));
        goto L111
    L102:
        if (this.isLocationPicker == true) goto L106;
        if (this.restrictEphemeralMessageTypes == true) goto L106;
        r11 = true;
    L111:
        this.selectedId = 5;
        r23 = this.locationLayout;
        goto L112
    L58:
        r53 = 4;
        goto L59
    L54:
        if (this.avatarPicker != 2) goto L60;
    L24:
        if (r2 == null) goto L33;
        this.photosEnabled = ChatObject.canSendPhoto(r2);
        this.videosEnabled = ChatObject.canSendVideo(r2);
        this.musicEnabled = ChatObject.canSendMusic(r2);
        this.pollsEnabled = ChatObject.canSendPolls(r2);
        if (ChatObject.isChannelAndNotMegaGroup(r2) == false) goto L28;
    L30:
        boolean r36 = false;
    L31:
        this.todoEnabled = r36;
        this.plainTextEnabled = ChatObject.canSendPlain(r2);
        this.documentsEnabled = ChatObject.canSendDocument(r2);
    L47:
        this.todoEnabled &= UserConfig.getInstance(this.currentAccount).isPremium();
        goto L49
    L28:
        if (ChatObject.canSendPolls(r2) == false) goto L30;
        r36 = true;
        goto L31
    L33:
        if (UserObject.isBot(r3) == false) goto L35;
    L38:
        boolean r29 = true;
    L39:
        this.pollsEnabled = r29;
        BaseFragment r210 = this.baseFragment;
        if ((r210 instanceof ChatActivity) == true) goto L42;
    L45:
        boolean r211 = true;
    L46:
        this.todoEnabled = r211;
        goto L47
    L42:
        if (((ChatActivity) r210).getCurrentEncryptedChat() == null) goto L45;
        r211 = false;
        goto L46
    L35:
        if (UserObject.isUserSelf(r3) == true) goto L38;
        r29 = false;
        goto L39
    L21:
        if (this.avatarPicker != 2) goto L24;
    L11:
        long r10 = this.dialogId;
        if (r10 < 0) goto L15;
        r3 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.dialogId));
        r2 = null;
        goto L19
    L15:
        if (r10 >= 0) goto L17;
        r2 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId));
        r3 = null;
    L17:
        r2 = null;
        r3 = null;
        goto L19
    }

    public boolean isCaptionAbove() {
        if (this.captionAbove == false) goto L10;
        AttachAlertLayout r0 = this.currentAttachLayout;
        if (r0 != this.photoLayout) goto L7;
        return true;
    L7:
        if (r0 != this.photoPreviewLayout) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public void makeFocusable(EditTextBoldCursor r3, boolean r4) {
        ChatAttachViewDelegate r0 = this.delegate;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.enterCommentEventSent == true) goto L13;
        boolean r02 = r0.needEnterComment();
        this.enterCommentEventSent = true;
        ChatAttachAlert$$ExternalSyntheticLambda53 r1 = new ChatAttachAlert$$ExternalSyntheticLambda53(this, r3, r4);
        if (r02 == false) goto L10;
        long r2 = 200;
    L11:
        AndroidUtilities.runOnUIThread(r1, r2);
        return;
    L10:
        r2 = 0;
        goto L11
    }

    public void onActivityResultFragment(int r1, Intent r2, String r3) {
        this.photoLayout.onActivityResultFragment(r1, r2, r3);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void onBackPressed() {
        if (this.passcodeView.getVisibility() != 0) goto L9;
        if (getOwnerActivity() == null) goto L14;
        getOwnerActivity().finish();
        return;
    L14:
        return;
    L9:
        if (this.actionBar.isSearchFieldVisible() == false) goto L13;
        this.actionBar.closeSearchField();
        return;
    L13:
        if (this.currentAttachLayout.onBackPressed() == false) goto L16;
        return;
    L16:
        if (getCommentView() != null) goto L18;
    L21:
        super.onBackPressed();
        return;
    L18:
        if (getCommentView().isPopupShowing() == false) goto L21;
        getCommentView().hidePopup(true);
    }

    public void onCameraStateChanged() {
        updateCameraButtonVisibility();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean onContainerTouchEvent(MotionEvent r1) {
        return this.currentAttachLayout.onContainerViewTouchEvent(r1);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void onCreate(Bundle r1) {
        super.onCreate(r1);
        BaseFragment r12 = this.baseFragment;
        if (r12 == null) goto L6;
        AndroidUtilities.setLightStatusBar(this, r12.isLightStatusBar());
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean onCustomLayout(View r1, int r2, int r3, int r4, int r5) {
        if (this.photoLayout.onCustomLayout(r1, r2, r3, r4, r5) == false) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean onCustomMeasure(View r1, int r2, int r3) {
        if (this.photoLayout.onCustomMeasure(r1, r2, r3) == false) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean onCustomOpenAnimation() {
        this.photoLayout.setTranslationX(0.0f);
        this.mediaPreviewView.setAlpha(0.0f);
        this.selectedView.setAlpha(1.0f);
        this.containerView.setTranslationY(this.containerView.getMeasuredHeight());
        AnimatorSet r0 = new AnimatorSet();
        this.buttonsAnimation = r0;
        r0.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, this.ATTACH_ALERT_PROGRESS, new float[]{0.0f, 400.0f})});
        this.buttonsAnimation.setDuration(400);
        this.buttonsAnimation.setStartDelay(20);
        this.ATTACH_ALERT_PROGRESS.set(this, Float.valueOf(0.0f));
        this.buttonsAnimation.start();
        ValueAnimator r02 = this.navigationBarAnimation;
        if (r02 == null) goto L5;
        r02.cancel();
    L5:
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{this.navigationBarAlpha, 1.0f});
        this.navigationBarAnimation = r03;
        r03.addUpdateListener(new ChatAttachAlert$$ExternalSyntheticLambda14(this));
        SpringAnimation r04 = this.appearSpringAnimation;
        if (r04 == null) goto L8;
        r04.cancel();
    L8:
        SpringAnimation r05 = new SpringAnimation(this.containerView, DynamicAnimation.TRANSLATION_Y, 0.0f);
        this.appearSpringAnimation = r05;
        if (this.editingMessageObject == null) goto L11;
        r05.getSpring().setDampingRatio(0.75f);
        this.appearSpringAnimation.getSpring().setStiffness(350.0f);
    L12:
        this.appearSpringAnimation.start();
        if (this.useHardwareLayer == false) goto L15;
        this.container.setLayerType(2, null);
    L15:
        this.currentSheetAnimationType = 1;
        AnimatorSet r06 = new AnimatorSet();
        this.currentSheetAnimation = r06;
        BottomSheet.SheetBackDrawable r2 = this.backDrawable;
        Property<Drawable, Integer> r3 = AnimationProperties.COLOR_DRAWABLE_ALPHA;
        if (this.dimBehind == false) goto L18;
        int r6 = this.dimBehindAlpha;
    L19:
        r06.playTogether(new Animator[]{ObjectAnimator.ofInt(r2, r3, new int[]{r6})});
        this.currentSheetAnimation.setDuration(400);
        this.currentSheetAnimation.setStartDelay(20);
        this.currentSheetAnimation.setInterpolator(this.openInterpolator);
        AnimationNotificationsLocker r07 = new AnimationNotificationsLocker();
        ChatAttachAlert$$ExternalSyntheticLambda15 r32 = new ChatAttachAlert$$ExternalSyntheticLambda15(this, r07, this.delegate);
        this.appearSpringAnimation.addEndListener(new ChatAttachAlert$$ExternalSyntheticLambda16(this, r32));
        this.currentSheetAnimation.addListener(new AnonymousClass34(this, r32));
        r07.lock();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopAllHeavyOperations, new Object[]{512});
        this.currentSheetAnimation.start();
        ValueAnimator r08 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        setNavBarAlpha(0.0f);
        r08.addUpdateListener(new ChatAttachAlert$$ExternalSyntheticLambda17(this));
        r08.setStartDelay(25);
        r08.setDuration(200);
        r08.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r08.start();
        return true;
    L18:
        r6 = 0;
        goto L19
    L11:
        r05.getSpring().setDampingRatio(0.75f);
        this.appearSpringAnimation.getSpring().setStiffness(350.0f);
        goto L12
    }

    public void onDestroy() {
        ViewPositionWatcher r0 = this.viewPositionWatcher;
        if (r0 == null) goto L5;
        r0.shutdown();
        this.viewPositionWatcher = null;
    L5:
        int r02 = 0;
    L6:
        AttachAlertLayout[] r2 = this.layouts;
        if (r02 >= r2.length) goto L12;
        AttachAlertLayout r22 = r2[r02];
        if (r22 == null) goto L11;
        r22.onDestroy();
    L11:
        r02 = r02 + 1;
        goto L6
    L12:
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.reloadInlineHints);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.attachMenuBotsDidLoad);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.currentUserPremiumStatusChanged);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.quickRepliesUpdated);
        this.destroyed = true;
        EditTextEmoji r03 = this.commentTextView;
        if (r03 == null) goto L15;
        r03.onDestroy();
    L15:
        EditTextEmoji r04 = this.topCommentTextView;
        if (r04 == null) goto L18;
        r04.onDestroy();
    L18:
        MentionsContainerView r05 = this.mentionContainer;
        if (r05 != null) goto L21;
    L24:
        PhotoViewer.getInstance().nullifyParentAlert(this);
        return;
    L21:
        if (r05.getAdapter() == null) goto L23;
        this.mentionContainer.getAdapter().onDestroy();
    L23:
        this.mentionContainer.onDetachedFromWindow();
        this.mentionContainer = null;
        goto L24
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onDismissWithTouchOutside() {
        if (this.currentAttachLayout.onDismissWithTouchOutside() == false) goto L6;
        dismiss();
        return;
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r2, float r3, float r4, FactorAnimator r5) {
        if (r2 != 0) goto L5;
        checkUi_writeButtonContainerY();
        checkUi_bottomFade();
        return;
    L5:
        int r52 = 1;
        if (r2 != 2) goto L28;
        checkUi_bottomFade();
        ChatAttachAlertPollLayout r22 = this.pollLayout;
        if (r22 == null) goto L17;
        AttachAlertLayout r42 = this.nextAttachLayout;
        if (r42 != r22) goto L12;
    L13:
        if (r42 != r22) goto L15;
        int r23 = 1;
    L16:
        updateSelectedPosition(r23);
        goto L17
    L15:
        r23 = 0;
        goto L16
    L12:
        if (this.currentAttachLayout == r22) goto L13;
    L17:
        ChatAttachAlertPollLayout r24 = this.todoLayout;
        if (r24 == null) goto L45;
        AttachAlertLayout r43 = this.nextAttachLayout;
        if (r43 != r24) goto L22;
    L23:
        if (r43 == r24) goto L26;
        r52 = 0;
    L26:
        updateSelectedPosition(r52);
        return;
    L22:
        if (this.currentAttachLayout == r24) goto L23;
        return;
    L45:
        return;
    L28:
        if (r2 != 1) goto L32;
        checkUi_bottomFade();
        return;
    L32:
        if (r2 != 3) goto L36;
        checkUi_moveCaptionButtonVisibility();
        return;
    L36:
        if (r2 != 4) goto L40;
        checkUi_moveCaptionButtonVisibility();
        return;
    L40:
        if (r2 != 5) goto L47;
        checkUi_moveCaptionButtonVisibility();
        ChatActivityEnterView.SendButton r25 = this.writeButton;
        if (r25 == null) goto L48;
        r25.setEphemeralFactor(r3);
        this.writeButton.setSameWidthFactor(r3);
        return;
    L48:
        return;
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int r2, KeyEvent r3) {
        if (this.currentAttachLayout.onSheetKeyDown(r2, r3) == false) goto L7;
        return true;
    L7:
        return super.onKeyDown(r2, r3);
    }

    public void onLongClickBotButton(TLRPC.TL_attachMenuBot r9, TLRPC.User r10) {
        if (r9 == null) goto L4;
        String r0 = r9.short_name;
    L5:
        ArrayList<TLRPC.TL_attachMenuBot> r1 = MediaDataController.getInstance(this.currentAccount).getAttachMenuBots().bots;
        int r2 = r1.size();
        int r3 = 0;
    L6:
        if (r3 >= r2) goto L9;
        TLRPC.TL_attachMenuBot r4 = r1.get(r3);
        r3 = r3 + 1;
        if (r4.bot_id != r10.id) goto L6;
    L9:
        String r12 = LocaleController.formatString("BotRemoveFromMenu", R.string.BotRemoveFromMenu, new Object[]{r0});
        AlertDialog.Builder r22 = new AlertDialog.Builder(getContext()).setTitle(LocaleController.getString(R.string.BotRemoveFromMenuTitle));
        if (r9 != null) goto L13;
        r12 = LocaleController.formatString("BotRemoveInlineFromMenu", R.string.BotRemoveInlineFromMenu, new Object[]{r0});
    L13:
        r22.setMessage(AndroidUtilities.replaceTags(r12)).setPositiveButton(LocaleController.getString("OK", R.string.OK), new ChatAttachAlert$$ExternalSyntheticLambda73(this, r9, r10)).setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null).show();
        return;
    L4:
        r0 = UserObject.getUserName(r10);
        goto L5
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onOpenAnimationEnd() {
        if ((this.baseFragment instanceof ChatActivity) == false) goto L5;
        int r0 = MediaController.VIDEO_BITRATE_1080;
    L6:
        this.currentAttachLayout.onOpenAnimationEnd();
        AndroidUtilities.makeAccessibilityAnnouncement(LocaleController.getString("AccDescrAttachButton", R.string.AccDescrAttachButton));
        this.openTransitionFinished = true;
        if (this.videosEnabled == false) goto L9;
        return;
    L9:
        if (this.photosEnabled == true) goto L13;
        checkCanRemoveRestrictionsByBoosts();
        return;
    L13:
        return;
    L5:
        int r02 = MediaController.VIDEO_BITRATE_1080;
        goto L6
    }

    public void onPause() {
        int r0 = 0;
    L3:
        AttachAlertLayout[] r1 = this.layouts;
        if (r0 >= r1.length) goto L9;
        AttachAlertLayout r12 = r1[r0];
        if (r12 == null) goto L8;
        r12.onPause();
    L8:
        r0 = r0 + 1;
        goto L3
    L9:
        this.paused = true;
    }

    public void onPollAttachFilePicker(Intent r1) {
        ChatAttachAlertPollLayout r0 = this.pollLayout;
        if (r0 == null) goto L6;
        r0.onPollAttachFilePicker(r1);
        return;
    }

    public void onRequestPermissionsResultFragment(int r1, String[] r2, int[] r3) {
        if (r1 != 5) goto L12;
        if (r3 == null) goto L12;
        if (r3.length <= 0) goto L12;
        if (r3[0] != 0) goto L12;
        openContactsLayout();
        return;
    L12:
        if (r1 != 30) goto L21;
        ChatAttachAlertLocationLayout r12 = this.locationLayout;
        if (r12 != null) goto L16;
        return;
    L16:
        if (this.currentAttachLayout == r12) goto L18;
        return;
    L18:
        if (isShowing() == false) goto L24;
        this.locationLayout.openShareLiveLocation();
        return;
    L24:
        return;
    }

    public void onResume() {
        int r0 = 0;
        this.paused = false;
    L3:
        AttachAlertLayout[] r1 = this.layouts;
        if (r0 >= r1.length) goto L10;
        AttachAlertLayout r12 = r1[r0];
        if (r12 == null) goto L8;
        r12.onResume();
    L8:
        r0 = r0 + 1;
        goto L3
    L10:
        if (isShowing() == false) goto L12;
        this.delegate.needEnterComment();
    L12:
        ButtonsAdapter r3 = this.buttonsAdapter;
        if (r3 == null) goto L19;
        r3.notifyDataSetChanged();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void onStart() {
        super.onStart();
        Context r0 = getContext();
        if ((r0 instanceof ContextWrapper) == false) goto L8;
        if ((r0 instanceof LaunchActivity) == true) goto L8;
        r0 = ((ContextWrapper) r0).getBaseContext();
    L8:
        if ((r0 instanceof LaunchActivity) == false) goto L11;
        ((LaunchActivity) r0).addOverlayPasscodeView(this.passcodeView);
        return;
    }

    @Override // android.app.Dialog
    public void onStop() {
        super.onStop();
        Context r0 = getContext();
        if ((r0 instanceof ContextWrapper) == false) goto L8;
        if ((r0 instanceof LaunchActivity) == true) goto L8;
        r0 = ((ContextWrapper) r0).getBaseContext();
    L8:
        if ((r0 instanceof LaunchActivity) == false) goto L11;
        ((LaunchActivity) r0).removeOverlayPasscodeView(this.passcodeView);
        return;
    }

    public void openAttachLayoutForType(int r6) {
        boolean r1 = true;
        if (r6 != 3) goto L27;
        if (this.musicEnabled == false) goto L7;
    L9:
        BaseFragment r62 = this.baseFragment;
        if (r62 == null) goto L12;
        Activity r63 = r62.getParentActivity();
    L13:
        if (r63 != null) goto L15;
    L24:
        openAudioLayout(true);
        return;
    L15:
        if (Build.VERSION.SDK_INT < 33) goto L21;
        if (r63.checkSelfPermission("android.permission.READ_MEDIA_AUDIO") == 0) goto L24;
        r63.requestPermissions(new String[]{"android.permission.READ_MEDIA_AUDIO"}, 4);
        return;
    L21:
        if (r63.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0) goto L24;
        r63.requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 4);
        return;
    L12:
        r63 = null;
        goto L13
    L7:
        if (checkCanRemoveRestrictionsByBoosts() == false) goto L9;
        return;
    L27:
        if (r6 == 6) goto L29;
        return;
    L29:
        if (AndroidUtilities.isMapsInstalled(this.baseFragment) == true) goto L32;
        return;
    L32:
        if (this.locationLayout != null) goto L45;
        AttachAlertLayout[] r64 = this.layouts;
        Context r2 = getContext();
        Theme.ResourcesProvider r3 = this.resourcesProvider;
        if (this.isPollAttach == false) goto L36;
    L38:
        r1 = false;
    L39:
        ChatAttachAlertLocationLayout r0 = new ChatAttachAlertLocationLayout(this, r2, r3, r1);
        this.locationLayout = r0;
        r64[5] = r0;
        ChatAttachAlertLocationLayout.LocationActivityDelegate r65 = this.locationActivityDelegate;
        if (r65 == null) goto L43;
        r0.setDelegate(r65);
        goto L45
    L43:
        if ((this.baseFragment instanceof ChatActivity) == false) goto L45;
        r0.setDelegate(new ChatAttachAlert$$ExternalSyntheticLambda82(this));
        goto L45
    L36:
        if (this.restrictEphemeralMessageTypes == true) goto L38;
    L45:
        showLayout(this.locationLayout);
    }

    public void openColorsLayout() {
        if (this.colorsLayout != null) goto L5;
        ChatAttachAlertColorsLayout r0 = new ChatAttachAlertColorsLayout(this, getContext(), this.resourcesProvider);
        this.colorsLayout = r0;
        r0.setDelegate(new ChatAttachAlert$$ExternalSyntheticLambda84(this));
    L5:
        showLayout(this.colorsLayout);
    }

    public void presentFragment(PhotoPickerActivity r1) {
        BaseFragment r0 = this.baseFragment;
        if (r0 == null) goto L6;
        r0.presentFragment(r1);
        return;
    L6:
        BaseFragment r02 = LaunchActivity.getLastFragment();
        if (r02 == null) goto L10;
        r02.presentFragment(r1);
        return;
    }

    public void setAllowDrawContent(boolean r3) {
        this.currentAttachLayout.onContainerTranslationUpdated(this.currentPanTranslationY);
        if (this.allowDrawContent == r3) goto L17;
        this.allowDrawContent = r3;
        AttachAlertLayout r0 = this.currentAttachLayout;
        ChatAttachAlertPhotoLayout r1 = this.photoLayout;
        if (r0 != r1) goto L18;
        if (r1 != null) goto L8;
        return;
    L8:
        if (r1.cameraExpanded == true) goto L20;
        if (r3 == true) goto L11;
    L14:
        boolean r2 = true;
    L15:
        r1.pauseCamera(r2);
        return;
    L11:
        if (this.sent == true) goto L14;
        r2 = false;
        goto L15
    L20:
        return;
    L18:
        return;
    }

    public void setAllowEnterCaption(boolean r1) {
        this.allowEnterCaption = r1;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void setAllowNestedScroll(boolean r1) {
        this.allowNestedScroll = r1;
    }

    public void setAudioSelectDelegate(ChatAttachAlertAudioLayout.AudioSelectDelegate r1) {
        this.audioSelectDelegate = r1;
    }

    public void setAvatarPicker(int r1, boolean r2, Utilities.Callback0Return<PhotoViewer.PlaceProviderObject> r3) {
        this.avatarPicker = r1;
        this.avatarSearch = r2;
        this.avatarWithBulletin = r3;
        if (r1 == 0) goto L13;
        this.typeButtonsAvailable = false;
        AttachAlertLayout r12 = this.currentAttachLayout;
        if (r12 != null) goto L7;
    L8:
        this.buttonsRecyclerViewWrapper.setVisibility(8);
    L9:
        int r13 = this.avatarPicker;
        TextView r22 = this.selectedTextView;
        if (r13 != 2) goto L12;
        r22.setText(LocaleController.getString(R.string.ChoosePhotoOrVideo));
    L14:
        ChatAttachAlertPhotoLayout r0 = this.photoLayout;
        if (r0 == null) goto L18;
        r0.updateAvatarPicker();
        return;
    L18:
        return;
    L12:
        r22.setText(LocaleController.getString(R.string.ChoosePhoto));
        goto L14
    L7:
        if (r12 != this.photoLayout) goto L9;
    L13:
        this.typeButtonsAvailable = true;
        goto L14
    }

    public void setButtonPressed(boolean r1) {
        this.buttonPressed = r1;
    }

    public void setCanOpenPreview(boolean r2) {
        this.canOpenPreview = r2;
        ImageView r0 = this.selectedArrowImageView;
        if (r2 == true) goto L5;
    L7:
        int r1 = 8;
    L8:
        r0.setVisibility(r1);
        return;
    L5:
        if (this.avatarPicker == 2) goto L7;
        r1 = 0;
        goto L8
    }

    public void setCaptionAbove(boolean r2) {
        setCaptionAbove(r2, true);
    }

    public void setCaptionAbove(boolean r12, boolean r13) {
        this.animatorCaptionAbove.setValue(r12, r13);
        EditTextEmoji r0 = getCommentView();
        this.captionAbove = r12;
        EditTextEmoji r122 = getCommentView();
        int r3 = 0;
        if (this.frameLayout2.getTag() == null) goto L5;
        boolean r1 = true;
    L6:
        AttachAlertLayout r4 = this.currentAttachLayout;
        if (r4 != this.photoLayout) goto L9;
    L12:
        boolean r42 = true;
    L14:
        if (this.captionAbove == false) goto L17;
        if (r42 == false) goto L17;
        boolean r43 = true;
    L18:
        FrameLayout r5 = this.topCommentContainer;
        int r6 = 8;
        float r7 = 1.0f;
        float r8 = 0.0f;
        if (r13 == false) goto L38;
        if (r1 == false) goto L22;
        r6 = 0;
    L22:
        r5.setVisibility(r6);
        ViewPropertyAnimator r132 = this.topCommentContainer.animate();
        if (r43 == false) goto L26;
        if (r1 == false) goto L26;
        float r52 = 1.0f;
    L27:
        ViewPropertyAnimator r133 = r132.alpha(r52).setDuration(320);
        CubicBezierInterpolator r9 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r133.setInterpolator(r9).setUpdateListener(new ChatAttachAlert$$ExternalSyntheticLambda9(this)).withEndAction(new ChatAttachAlert$$ExternalSyntheticLambda10(this, r43, r1)).start();
        this.captionContainer.setVisibility(0);
        ViewPropertyAnimator r134 = this.captionContainer.animate();
        if (r43 == true) goto L31;
        if (r1 == false) goto L31;
        float r32 = 0.0f;
    L32:
        ViewPropertyAnimator r135 = r134.translationY(r32);
        if (r43 == true) goto L36;
        if (r1 == false) goto L36;
    L37:
        r135.alpha(r7).setDuration(320).setInterpolator(r9).setUpdateListener(new ChatAttachAlert$$ExternalSyntheticLambda11(this)).withEndAction(new ChatAttachAlert$$ExternalSyntheticLambda12(this, r43, r1)).start();
    L63:
        if (r0 == r122) goto L67;
        r0.hidePopup(true);
        r122.setText(AnimatedEmojiSpan.cloneSpans(r0.getText()));
        r122.getEditText().setAllowTextEntitiesIntersection(r0.getEditText().getAllowTextEntitiesIntersection());
        if (r0.getEditText().isFocused() == false) goto L67;
        r122.getEditText().requestFocus();
        r122.getEditText().setSelection(r0.getEditText().getSelectionStart(), r0.getEditText().getSelectionEnd());
    L67:
        AndroidUtilities.runOnUIThread(new ChatAttachAlert$$ExternalSyntheticLambda13(this));
        return;
    L36:
        r7 = 0.0f;
    L31:
        r32 = this.captionContainer.getMeasuredHeight();
    L26:
        r52 = 0.0f;
        goto L27
    L38:
        if (r43 == false) goto L41;
        if (r1 == false) goto L41;
        int r136 = 0;
    L42:
        r5.setVisibility(r136);
        FrameLayout r137 = this.topCommentContainer;
        if (r43 == false) goto L46;
        if (r1 == false) goto L46;
        float r53 = 1.0f;
    L47:
        r137.setAlpha(r53);
        updatedTopCaptionHeight();
        FrameLayout r138 = this.captionContainer;
        if (r43 == true) goto L51;
        if (r1 == false) goto L51;
    L52:
        r138.setAlpha(r7);
        FrameLayout r139 = this.captionContainer;
        if (r43 == true) goto L56;
        if (r1 == false) goto L56;
    L57:
        r139.setTranslationY(r8);
        FrameLayout r1310 = this.captionContainer;
        if (r43 == true) goto L61;
        if (r1 == false) goto L61;
    L62:
        r1310.setVisibility(r3);
    L61:
        r3 = 8;
    L56:
        r8 = r139.getMeasuredHeight();
    L51:
        r7 = 0.0f;
    L46:
        r53 = 0.0f;
    L41:
        r136 = 8;
    L17:
        r43 = false;
        goto L18
    L9:
        if (r4 == this.photoPreviewLayout) goto L12;
        r42 = false;
        goto L14
    L5:
        r1 = false;
        goto L6
    }

    public void setDelegate(ChatAttachViewDelegate r1) {
        this.delegate = r1;
    }

    public void setDialogId(long r1) {
        this.dialogId = r1;
    }

    public void setDocumentsDelegate(ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate r1) {
        this.documentsDelegate = r1;
    }

    public void setEditingMessageObject(int r3, MessageObject r4) {
        if (r4 == null) goto L7;
        ChatAttachAlertPhotoLayout r0 = this.photoLayout;
        if (r0 == null) goto L7;
        r0.clearSelectedPhotos();
    L7:
        if (this.editingMessageObject == r4) goto L9;
    L11:
        this.editingMessageObject = r4;
        if (r4 != null) goto L14;
    L22:
        this.editType = r3;
        if (this.editingMessageObject == null) goto L25;
        this.maxSelectedPhotos = 1;
        this.allowOrder = false;
    L26:
        this.buttonsAdapter.notifyDataSetChanged();
        updateCountButton(0);
        return;
    L25:
        this.maxSelectedPhotos = -1;
        this.allowOrder = true;
        goto L26
    L14:
        if (r4.hasValidGroupId() == false) goto L22;
        if (this.editingMessageObject.isMusic() == false) goto L19;
        r3 = 2;
        goto L22
    L19:
        if (this.editingMessageObject.isDocument() == false) goto L21;
        r3 = 1;
        goto L22
    L21:
        r3 = 0;
        goto L22
    L9:
        if (this.editType != r3) goto L11;
    }

    public void setEmojiViewDelegate(EmojiView.EmojiViewDelegate r1) {
        this.emojiViewDelegate = r1;
    }

    public void setImageUpdater(ImageUpdater r1) {
        this.parentImageUpdater = r1;
    }

    public void setLocationActivityDelegate(ChatAttachAlertLocationLayout.LocationActivityDelegate r1) {
        this.locationActivityDelegate = r1;
    }

    public void setLocationPicker() {
        this.isLocationPicker = true;
        this.buttonsRecyclerViewWrapper.setVisibility(8);
    }

    public void setMaxSelectedPhotos(int r2, boolean r3) {
        if (this.editingMessageObject == null) goto L5;
        return;
    L5:
        this.maxSelectedPhotos = r2;
        this.allowOrder = r3;
    }

    public void setOpenWithFrontFaceCamera(boolean r1) {
        this.openWithFrontFaceCamera = r1;
    }

    public void setSoundPicker() {
        this.isSoundPicker = true;
        this.buttonsRecyclerViewWrapper.setVisibility(8);
        this.selectedTextView.setText(LocaleController.getString(R.string.ChoosePhotoOrVideo));
    }

    public void setStoryLocationPicker() {
        this.isStoryLocationPicker = true;
        this.buttonsRecyclerViewWrapper.setVisibility(8);
    }

    public void setStoryLocationPicker(double r3, double r5) {
        this.storyLocationPickerLatLong = new double[]{r3, r5};
        this.isStoryLocationPicker = true;
        this.buttonsRecyclerViewWrapper.setVisibility(8);
    }

    public void setStoryLocationPicker(boolean r1, File r2) {
        this.storyLocationPickerFileIsVideo = r1;
        this.storyLocationPickerPhotoFile = r2;
        this.isStoryLocationPicker = true;
        this.buttonsRecyclerViewWrapper.setVisibility(8);
    }

    public void setStoryMediaPicker() {
        this.storyMediaPicker = true;
        this.typeButtonsAvailable = false;
        this.selectedTextView.setText(LocaleController.getString(R.string.ChoosePhotoOrVideo));
    }

    public void setTypeButtonsHidden(boolean r6, boolean r7) {
        if (this.typeButtonsHidden == r6) goto L31;
        this.typeButtonsHidden = r6;
        if (this.typeButtonsAvailable == true) goto L8;
        return;
    L8:
        this.buttonsRecyclerViewWrapper.animate().cancel();
        int r0 = 0;
        if (r6 == true) goto L11;
        this.buttonsRecyclerViewWrapper.setVisibility(0);
    L11:
        FrameLayout r1 = this.buttonsRecyclerViewWrapper;
        float r3 = 1.0f;
        float r4 = 0.0f;
        if (r7 == false) goto L21;
        ViewPropertyAnimator r72 = r1.animate();
        if (r6 == false) goto L16;
        r3 = 0.0f;
    L16:
        ViewPropertyAnimator r73 = r72.alpha(r3);
        if (r6 == false) goto L19;
        r4 = AndroidUtilities.dp(48.0f);
    L19:
        r73.translationY(r4).setDuration(180).withEndAction(new ChatAttachAlert$$ExternalSyntheticLambda70(this, r6)).start();
        return;
    L21:
        if (r6 == false) goto L23;
        r3 = 0.0f;
    L23:
        r1.setAlpha(r3);
        FrameLayout r74 = this.buttonsRecyclerViewWrapper;
        if (r6 == false) goto L26;
        r4 = AndroidUtilities.dp(48.0f);
    L26:
        r74.setTranslationY(r4);
        FrameLayout r5 = this.buttonsRecyclerViewWrapper;
        if (r6 == false) goto L29;
        r0 = 4;
    L29:
        r5.setVisibility(r0);
        return;
    }

    public void setupMentionContainer(MentionsContainerView r6) {
        r6.getAdapter().setAllowStickers(false);
        r6.getAdapter().setAllowBots(false);
        r6.getAdapter().setAllowChats(false);
        boolean r2 = true;
        if ((this.baseFragment instanceof ChatActivity) == false) goto L9;
        r6.getAdapter().setSearchInDialogs(false);
        ChatActivity r5 = (ChatActivity) this.baseFragment;
        r6.getAdapter().setUserOrChat(r5.getCurrentUser(), r5.getCurrentChat());
        r6.getAdapter().setChatInfo(r5.getCurrentChatInfo());
        MentionsAdapter r0 = r6.getAdapter();
        if (r5.getCurrentChat() != null) goto L8;
        r2 = false;
    L8:
        r0.setNeedUsernames(r2);
    L10:
        r6.getAdapter().setNeedBotContext(false);
        return;
    L9:
        r6.getAdapter().setSearchInDialogs(true);
        r6.getAdapter().setChatInfo(null);
        r6.getAdapter().setNeedUsernames(false);
        goto L10
    }

    public void setupPhotoPicker(String r3) {
        this.avatarPicker = 1;
        this.isPhotoPicker = true;
        this.avatarSearch = false;
        this.typeButtonsAvailable = false;
        this.videosEnabled = false;
        this.buttonsRecyclerViewWrapper.setVisibility(8);
        this.selectedTextView.setText(r3);
        ChatAttachAlertPhotoLayout r2 = this.photoLayout;
        if (r2 == null) goto L6;
        r2.updateAvatarPicker();
        return;
    }

    public void setupPoll(Boolean r4) {
        this.typeButtonsAvailable = false;
        this.buttonsRecyclerViewWrapper.setVisibility(8);
        showPollLayout(false, r4);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean shouldOverlayCameraViewOverNavBar() {
        AttachAlertLayout r0 = this.currentAttachLayout;
        ChatAttachAlertPhotoLayout r1 = this.photoLayout;
        if (r0 == r1) goto L5;
        return false;
    L5:
        if (r1.cameraExpanded == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        super.show();
        this.buttonPressed = false;
        BaseFragment r1 = this.baseFragment;
        if ((r1 instanceof ChatActivity) == false) goto L5;
        this.calcMandatoryInsets = ((ChatActivity) r1).isKeyboardVisible();
    L5:
        updateDoneItemEnabled();
        this.openTransitionFinished = false;
        if (Build.VERSION.SDK_INT < 30) goto L13;
        this.navBarColorKey = -1;
        int r12 = ColorUtils.setAlphaComponent(getThemedColor(Theme.key_windowBackgroundGray), 0);
        this.navBarColor = r12;
        AndroidUtilities.setNavigationBarColor(this, r12, false);
        if (AndroidUtilities.computePerceivedBrightness(this.navBarColor) <= 0.721d) goto L10;
        boolean r13 = true;
    L11:
        AndroidUtilities.setLightNavigationBar(this, r13);
        goto L13
    L10:
        r13 = false;
    L13:
        if (this.hasOverridenWebviewBackgroundColor == false) goto L16;
        this.hasOverridenWebviewBackgroundColor = false;
        this.actionBar.updateColors();
        this.actionBar.invalidate();
        onCurrentLayoutAnimatorChanged();
        return;
    }

    public void showBotLayout(long r15, String r17, boolean r18, boolean r19) {
        if (this.botAttachLayouts.get(r15) == null) goto L9;
        String r3 = r17;
        if (Objects.equals(r3, this.botAttachLayouts.get(r15).getStartCommand()) == false) goto L11;
        if (this.botAttachLayouts.get(r15).needReload() == true) goto L11;
    L18:
        if (this.botAttachLayouts.get(r15) == null) goto L23;
        this.botAttachLayouts.get(r15).disallowSwipeOffsetAnimation();
        this.botAttachLayouts.get(r15).getSwipeContainer().isPinned = false;
        this.botAttachLayouts.get(r15).pinItem.setTextAndIcon(LocaleController.getString(R.string.PinMessage), R.drawable.msg_pin);
        showLayout(this.botAttachLayouts.get(r15), -r15, r19);
        if (r18 == false) goto L24;
        this.botAttachLayouts.get(r15).showJustAddedBulletin();
        return;
    L24:
        return;
    L23:
        return;
    L11:
        if ((this.baseFragment instanceof ChatActivity) == false) goto L18;
        ChatAttachAlertBotWebViewLayout r2 = new ChatAttachAlertBotWebViewLayout(this, getContext(), this.resourcesProvider);
        this.botAttachLayouts.put(r15, r2);
        this.botAttachLayouts.get(r15).setDelegate(new AnonymousClass1(this, r2, r3, r15));
        MessageObject r0 = ((ChatActivity) this.baseFragment).getChatActivityEnterView().getReplyingMessageObject();
        ChatAttachAlertBotWebViewLayout r1 = this.botAttachLayouts.get(r15);
        int r12 = this.currentAccount;
        long r6 = ((ChatActivity) this.baseFragment).getDialogId();
        if (r0 == null) goto L15;
        int r02 = r0.messageOwner.id;
    L16:
        r1.requestWebView(r12, r6, r15, false, r02, r17, ((ChatActivity) this.baseFragment).getSendMonoForumPeerId());
        goto L18
    L15:
        r02 = 0;
        goto L16
    L9:
        r3 = r17;
        goto L11
    }

    public void showBotLayout(long r7, boolean r9) {
        showBotLayout(r7, null, false, r9);
    }

    public void showLayout(AttachAlertLayout r4) {
        long r0 = this.selectedId;
        ChatAttachRestrictedLayout r2 = this.restrictedLayout;
        if (r4 != r2) goto L6;
        r0 = r2.id;
    L41:
        showLayout(r4, r0);
        return;
    L6:
        if (r4 != this.photoLayout) goto L9;
        r0 = 1;
        goto L41
    L9:
        if (r4 != this.audioLayout) goto L12;
        r0 = 3;
        goto L41
    L12:
        if (r4 != this.documentLayout) goto L15;
        r0 = 4;
        goto L41
    L15:
        if (r4 != this.contactsLayout) goto L18;
        r0 = 5;
        goto L41
    L18:
        if (r4 != this.locationLayout) goto L21;
        r0 = 6;
        goto L41
    L21:
        if (r4 != this.pollLayout) goto L24;
        r0 = 9;
        goto L41
    L24:
        if (r4 != this.colorsLayout) goto L27;
        r0 = 10;
        goto L41
    L27:
        if (r4 != this.quickRepliesLayout) goto L30;
        r0 = 11;
        goto L41
    L30:
        if (r4 != this.todoLayout) goto L33;
        r0 = 12;
        goto L41
    L33:
        if (r4 != this.emojiLayout) goto L36;
        r0 = 14;
        goto L41
    L36:
        if (r4 != this.stickersLayout) goto L39;
        r0 = 13;
        goto L41
    L39:
        if (r4 != this.richLayout) goto L41;
        r0 = 16;
        goto L41
    }

    public boolean showSendButtonOnly(boolean r14, boolean r15) {
        if (this.frameLayout2.getTag() == null) goto L5;
        boolean r0 = true;
    L6:
        if (r14 != r0) goto L8;
        return false;
    L8:
        AnimatorSet r02 = this.commentsAnimator;
        if (r02 == null) goto L11;
        r02.cancel();
    L11:
        FrameLayout r03 = this.frameLayout2;
        if (r14 == false) goto L14;
        Integer r3 = 1;
    L15:
        r03.setTag(r3);
        if (r14 == false) goto L19;
        this.writeButtonContainer.setVisibility(0);
    L21:
        float r32 = 0.0f;
        float r4 = 0.2f;
        if (r15 == false) goto L54;
        this.commentsAnimator = new AnimatorSet();
        ArrayList r6 = new ArrayList();
        FrameLayout r7 = this.writeButtonContainer;
        Property r8 = View.SCALE_X;
        if (r14 == false) goto L26;
        float r9 = 1.0f;
    L27:
        r6.add(ObjectAnimator.ofFloat(r7, r8, new float[]{r9}));
        FrameLayout r72 = this.writeButtonContainer;
        Property r92 = View.SCALE_Y;
        if (r14 == false) goto L30;
        float r10 = 1.0f;
    L31:
        r6.add(ObjectAnimator.ofFloat(r72, r92, new float[]{r10}));
        FrameLayout r73 = this.writeButtonContainer;
        Property r102 = View.ALPHA;
        if (r14 == false) goto L34;
        float r11 = 1.0f;
    L35:
        r6.add(ObjectAnimator.ofFloat(r73, r102, new float[]{r11}));
        ChatActivityEnterView.SendButton r74 = this.writeButton;
        if (r14 == false) goto L38;
        float r112 = 1.0f;
    L39:
        r6.add(ObjectAnimator.ofFloat(r74, r8, new float[]{r112}));
        ChatActivityEnterView.SendButton r75 = this.writeButton;
        if (r14 == false) goto L42;
        r4 = 1.0f;
    L42:
        r6.add(ObjectAnimator.ofFloat(r75, r92, new float[]{r4}));
        if (this.typeButtonsAvailable == false) goto L53;
        FrameLayout r42 = this.buttonsRecyclerViewWrapper;
        Property r76 = View.TRANSLATION_Y;
        if (r14 == false) goto L47;
        float r04 = AndroidUtilities.dp(36.0f);
    L48:
        r6.add(ObjectAnimator.ofFloat(r42, r76, new float[]{r04}));
        FrameLayout r05 = this.buttonsRecyclerViewWrapper;
        if (r14 == true) goto L52;
        r32 = 1.0f;
    L52:
        r6.add(ObjectAnimator.ofFloat(r05, r102, new float[]{r32}));
        goto L53
    L47:
        r04 = 0.0f;
    L53:
        this.commentsAnimator.playTogether(r6);
        this.commentsAnimator.setInterpolator(new DecelerateInterpolator());
        this.commentsAnimator.setDuration(180);
        this.commentsAnimator.addListener(new AnonymousClass31(this, r14));
        this.commentsAnimator.start();
    L92:
        this.writeButton.setCount(0, r15);
        return true;
    L38:
        r112 = 0.2f;
        goto L39
    L34:
        r11 = 0.0f;
        goto L35
    L30:
        r10 = 0.2f;
        goto L31
    L26:
        r9 = 0.2f;
        goto L27
    L54:
        FrameLayout r62 = this.writeButtonContainer;
        if (r14 == false) goto L57;
        float r77 = 1.0f;
    L58:
        r62.setScaleX(r77);
        FrameLayout r63 = this.writeButtonContainer;
        if (r14 == false) goto L61;
        float r78 = 1.0f;
    L62:
        r63.setScaleY(r78);
        FrameLayout r64 = this.writeButtonContainer;
        if (r14 == false) goto L65;
        float r79 = 1.0f;
    L66:
        r64.setAlpha(r79);
        ChatActivityEnterView.SendButton r65 = this.writeButton;
        if (r14 == false) goto L69;
        float r710 = 1.0f;
    L70:
        r65.setScaleX(r710);
        ChatActivityEnterView.SendButton r66 = this.writeButton;
        if (r14 == false) goto L73;
        r4 = 1.0f;
    L73:
        r66.setScaleY(r4);
        if (this.typeButtonsAvailable == false) goto L90;
        FrameLayout r43 = this.buttonsRecyclerViewWrapper;
        if (r14 == false) goto L78;
        float r06 = AndroidUtilities.dp(36.0f);
    L79:
        r43.setTranslationY(r06);
        FrameLayout r07 = this.buttonsRecyclerViewWrapper;
        if (r14 == true) goto L83;
        r32 = 1.0f;
    L83:
        r07.setAlpha(r32);
        if (r14 == false) goto L90;
        AttachAlertLayout r08 = this.currentAttachLayout;
        if (r08 != null) goto L88;
    L89:
        this.buttonsRecyclerViewWrapper.setVisibility(4);
        goto L90
    L88:
        if (r08.shouldHideBottomButtons() == false) goto L90;
    L78:
        r06 = 0.0f;
    L90:
        if (r14 == true) goto L92;
        this.writeButtonContainer.setVisibility(4);
        goto L92
    L69:
        r710 = 0.2f;
        goto L70
    L65:
        r79 = 0.0f;
        goto L66
    L61:
        r78 = 0.2f;
        goto L62
    L57:
        r77 = 0.2f;
        goto L58
    L19:
        if (this.typeButtonsAvailable == false) goto L21;
        this.buttonsRecyclerViewWrapper.setVisibility(0);
        goto L21
    L14:
        r3 = null;
        goto L15
    L5:
        r0 = false;
        goto L6
    }

    public void updateCommentTextViewPosition() {
        this.commentTextView.getLocationOnScreen(this.commentTextViewLocation);
        if (this.mentionContainer == null) goto L17;
        AttachAlertLayout r0 = this.currentAttachLayout;
        if (r0 == this.photoLayout) goto L9;
        if (r0 == this.photoPreviewLayout) goto L9;
    L11:
        float r02 = -this.commentTextView.getHeight();
    L13:
        if (Math.abs(this.mentionContainer.getTranslationY() - r02) <= 0.5f) goto L17;
        this.mentionContainer.setTranslationY(r02);
        this.mentionContainer.invalidate();
        ChatAttachAlertPhotoLayout r03 = this.photoLayout;
        if (r03 == null) goto L17;
        r03.checkCameraViewPosition();
    L9:
        if (this.captionAbove == false) goto L11;
        r02 = (this.topCommentContainer.getY() - this.mentionContainer.getTop()) + (this.topCommentContainer.getMeasuredHeight() * this.topCommentContainer.getAlpha());
    L17:
        checkUi_writeButtonContainerY();
    }

    public void updateCountButton(int r12) {
        if (this.viewChangeAnimator != null) goto L173;
        int r0 = this.currentAttachLayout.getSelectedItemsCount();
        boolean r1 = true;
        int r2 = 0;
        if (r0 != 0) goto L16;
        ChatActivityEnterView.SendButton r3 = this.writeButton;
        if (r12 == 0) goto L10;
        boolean r4 = true;
    L11:
        r3.setCount(0, r4);
        if (r12 == 0) goto L14;
        boolean r32 = true;
    L15:
        showCommentTextView(false, r32);
    L28:
        this.currentAttachLayout.onSelectedItemsCountChanged(r0);
        if (r12 == 0) goto L31;
        boolean r33 = true;
    L32:
        checkIsEphemeralMessage(r33);
        if (this.currentAttachLayout == this.photoLayout) goto L35;
    L152:
        if (r12 != 0) goto L155;
        r1 = false;
    L155:
        updateMotionItem(r1);
        MessageObject r122 = this.editingMessageObject;
        if (r122 != null) goto L158;
    L160:
        long r02 = MessagesController.getInstance(this.currentAccount).getSendPaidMessagesStars(getDialogId());
    L161:
        AttachAlertLayout r123 = this.currentAttachLayout;
        if (r123 == null) goto L165;
        r2 = r123.getSelectedItemsCount();
    L165:
        this.writeButton.setStarsPrice(r02, r2 + getAdditionalMessagesCount());
        EditTextEmoji r124 = this.commentTextView;
        if (r124 == null) goto L171;
        ViewGroup.MarginLayoutParams r125 = (ViewGroup.MarginLayoutParams) r124.getLayoutParams();
        int r03 = Math.max(AndroidUtilities.dp(48.0f), this.writeButton.width());
        if (r125.rightMargin == r03) goto L172;
        r125.rightMargin = r03;
        this.commentTextView.setLayoutParams(r125);
        return;
    L172:
        return;
    L171:
        return;
    L158:
        if (r122.needResendWhenEdit() == true) goto L160;
        r02 = 0;
        goto L161
    L35:
        if ((this.baseFragment instanceof ChatActivity) == false) goto L37;
    L40:
        if (r0 == 0) goto L42;
    L43:
        if (r0 != 0) goto L49;
        if (this.avatarPicker != 0) goto L49;
        if (this.storyMediaPicker == false) goto L152;
    L49:
        if (this.menuShowed == true) goto L152;
    L50:
        if (r0 == 0) goto L52;
    L57:
        boolean r04 = true;
    L58:
        this.menuShowed = r04;
        AnimatorSet r05 = this.menuAnimator;
        if (r05 == null) goto L62;
        r05.cancel();
        this.menuAnimator = null;
    L62:
        if (this.avatarPicker != 0) goto L64;
    L72:
        boolean r06 = false;
    L74:
        if (this.menuShowed == false) goto L82;
        if (this.avatarPicker == 0) goto L78;
    L80:
        this.headerView.setVisibility(0);
    L86:
        float r34 = 0.6f;
        float r42 = 0.0f;
        if (r12 == 0) goto L89;
        this.menuAnimator = new AnimatorSet();
        ArrayList r6 = new ArrayList();
        if (this.actionBar.getTag() == null) goto L125;
    L140:
        FrameLayout r35 = this.headerView;
        Property r7 = View.ALPHA;
        if (this.menuShowed == false) goto L143;
        float r8 = 1.0f;
    L144:
        r6.add(ObjectAnimator.ofFloat(r35, r7, new float[]{r8}));
        if (r06 == false) goto L151;
        ActionBarMenuItem r07 = this.searchItem;
        if (this.menuShowed == true) goto L150;
        r42 = 1.0f;
    L150:
        r6.add(ObjectAnimator.ofFloat(r07, r7, new float[]{r42}));
    L151:
        this.menuAnimator.playTogether(r6);
        this.menuAnimator.addListener(new AnonymousClass36(this));
        this.menuAnimator.setDuration(180);
        this.menuAnimator.start();
        goto L152
    L143:
        r8 = 0.0f;
        goto L144
    L125:
        if (this.avatarPicker != 0) goto L140;
        if (this.storyMediaPicker == true) goto L140;
        ActionBarMenuItem r72 = this.selectedMenuItem;
        Property r82 = View.ALPHA;
        if (this.menuShowed == false) goto L131;
        float r9 = 1.0f;
    L132:
        r6.add(ObjectAnimator.ofFloat(r72, r82, new float[]{r9}));
        ActionBarMenuItem r73 = this.selectedMenuItem;
        Property r83 = View.SCALE_X;
        if (this.menuShowed == false) goto L135;
        float r92 = 1.0f;
    L136:
        r6.add(ObjectAnimator.ofFloat(r73, r83, new float[]{r92}));
        ActionBarMenuItem r74 = this.selectedMenuItem;
        Property r84 = View.SCALE_Y;
        if (this.menuShowed == false) goto L139;
        r34 = 1.0f;
    L139:
        r6.add(ObjectAnimator.ofFloat(r74, r84, new float[]{r34}));
        goto L140
    L135:
        r92 = 0.6f;
        goto L136
    L131:
        r9 = 0.0f;
        goto L132
    L89:
        if (this.actionBar.getTag() == null) goto L91;
    L106:
        FrameLayout r36 = this.headerView;
        if (this.menuShowed == false) goto L109;
        float r62 = 1.0f;
    L110:
        r36.setAlpha(r62);
        if (r06 == false) goto L118;
        ActionBarMenuItem r08 = this.searchItem;
        if (this.menuShowed == true) goto L116;
        r42 = 1.0f;
    L116:
        r08.setAlpha(r42);
    L118:
        if (this.menuShowed == false) goto L152;
        ActionBarMenuItem r09 = this.searchItem;
        if (r09 == null) goto L152;
        r09.setVisibility(4);
        goto L152
    L109:
        r62 = 0.0f;
        goto L110
    L91:
        if (this.avatarPicker != 0) goto L106;
        if (this.storyMediaPicker == true) goto L106;
        ActionBarMenuItem r63 = this.selectedMenuItem;
        if (this.menuShowed == false) goto L97;
        float r75 = 1.0f;
    L98:
        r63.setAlpha(r75);
        ActionBarMenuItem r64 = this.selectedMenuItem;
        if (this.menuShowed == false) goto L101;
        float r76 = 1.0f;
    L102:
        r64.setScaleX(r76);
        ActionBarMenuItem r65 = this.selectedMenuItem;
        if (this.menuShowed == false) goto L105;
        r34 = 1.0f;
    L105:
        r65.setScaleY(r34);
        goto L106
    L101:
        r76 = 0.6f;
        goto L102
    L97:
        r75 = 0.0f;
        goto L98
    L78:
        if (this.storyMediaPicker == true) goto L80;
        this.selectedMenuItem.setVisibility(0);
        this.selectedMenuItem.setClickable(true);
        goto L80
    L82:
        if (this.actionBar.getTag() == null) goto L86;
        ActionBarMenuItem r37 = this.searchItem;
        if (r37 == null) goto L86;
        r37.setVisibility(0);
        goto L86
    L64:
        if (this.searchItem == null) goto L72;
        if (this.actionBar.getTag() == null) goto L72;
        BaseFragment r010 = this.baseFragment;
        if ((r010 instanceof ChatActivity) == false) goto L72;
        if (((ChatActivity) r010).allowSendGifs() == false) goto L72;
        r06 = true;
        goto L74
    L52:
        if (this.avatarPicker != 0) goto L57;
        if (this.storyMediaPicker == true) goto L57;
        r04 = false;
        goto L58
    L42:
        if (this.menuShowed == true) goto L50;
    L37:
        if (this.avatarPicker != 0) goto L40;
        if (this.storyMediaPicker == false) goto L152;
    L31:
        r33 = false;
        goto L32
    L14:
        r32 = false;
        goto L15
    L10:
        r4 = false;
        goto L11
    L16:
        if (r12 == 0) goto L18;
        boolean r38 = true;
    L20:
        if (showCommentTextView(true, r38) == true) goto L23;
        if (r12 == 0) goto L23;
        this.writeButton.setCount(r0, true);
        this.writeButton.bounceCount();
    L23:
        ChatActivityEnterView.SendButton r39 = this.writeButton;
        if (r12 == 0) goto L26;
        boolean r43 = true;
    L27:
        r39.setCount(r0, r43);
        this.writeButton.bounceCount();
        goto L28
    L26:
        r43 = false;
        goto L27
    L18:
        r38 = false;
        goto L20
    }

    public void updateDoneItemEnabled() {
        TextView r0 = this.doneItem;
        AttachAlertLayout r1 = this.currentAttachLayout;
        if (r1 != null) goto L5;
        boolean r12 = false;
    L6:
        r0.setEnabled(r12);
        AttachAlertLayout r02 = this.currentAttachLayout;
        float r13 = 0.5f;
        float r3 = 0.0f;
        if (r02 != null) goto L9;
    L17:
        AttachAlertLayout r03 = this.nextAttachLayout;
        if (r03 != null) goto L20;
    L23:
        this.doneItemAlphaByEnabled = r3;
        checkUi_doneItemVisibility();
        return;
    L20:
        if (r03.isDoneItemEnabled() == false) goto L22;
        r13 = 1.0f;
    L22:
        r3 = r3 + (r13 * (1.0f - this.translationProgress));
        goto L23
    L9:
        if (r02.isDoneItemEnabled() == false) goto L11;
        float r04 = 1.0f;
    L13:
        if (this.nextAttachLayout != null) goto L15;
        float r4 = 1.0f;
    L16:
        r3 = 0.0f + (r04 * r4);
        goto L17
    L15:
        r4 = this.translationProgress;
        goto L16
    L11:
        r04 = 0.5f;
        goto L13
    L5:
        r12 = r1.isDoneItemEnabled();
        goto L6
    }

    public void updateLayout(AttachAlertLayout r5, boolean r6, int r7) {
        if (r5 == null) goto L47;
        DownscaleScrollableNoiseSuppressor r62 = this.scrollableViewNoiseSuppressor;
        if (r62 != null) goto L7;
    L9:
        int r63 = r5.getCurrentItemTop();
        if (r63 != Integer.MAX_VALUE) goto L12;
        return;
    L12:
        boolean r1 = false;
        if (r5 == this.currentAttachLayout) goto L15;
    L17:
        boolean r0 = false;
    L18:
        this.pinnedToTop = r0;
        if (r5 != this.currentAttachLayout) goto L21;
        updateActionBarVisibility(r0, true);
    L21:
        FrameLayout.LayoutParams r02 = (FrameLayout.LayoutParams) r5.getLayoutParams();
        if (r02 != null) goto L24;
        int r03 = 0;
    L25:
        int r64 = r63 + (r03 - AndroidUtilities.dp(11.0f));
        AttachAlertLayout r04 = this.currentAttachLayout;
        if (r04 != r5) goto L28;
        int r52 = 0;
    L30:
        if ((r04 instanceof ChatAttachAlertPhotoLayoutPreview) == false) goto L32;
    L33:
        Object r05 = this.viewChangeAnimator;
        if ((r05 instanceof SpringAnimation) == true) goto L36;
    L38:
        int[] r06 = this.scrollOffsetY;
        int r2 = r06[r52];
        if (r2 != r64) goto L44;
        if (r1 == true) goto L44;
        if (r7 == 0) goto L45;
        this.previousScrollOffsetY = r2;
    L45:
        updateCameraButtonVisibility();
        return;
    L44:
        this.previousScrollOffsetY = r2;
        r06[r52] = r64;
        updateSelectedPosition(r52);
        this.containerView.invalidate();
        goto L45
    L36:
        if (((SpringAnimation) r05).isRunning() == false) goto L38;
        r1 = true;
        goto L38
    L32:
        if ((this.nextAttachLayout instanceof ChatAttachAlertPhotoLayoutPreview) == false) goto L38;
    L28:
        r52 = 1;
        goto L30
    L24:
        r03 = r02.topMargin;
        goto L25
    L15:
        if (r63 > r5.getButtonsHideOffset()) goto L17;
        r0 = true;
        goto L18
    L7:
        if (Build.VERSION.SDK_INT < 31) goto L9;
        r62.onScrolled(0.0f, r7);
        blur3_InvalidateBlur();
        goto L9
    }

    public void updatePhotoPreview(boolean r3) {
        if (r3 == true) goto L4;
        showLayout(this.photoLayout);
        return;
    L4:
        if (this.canOpenPreview == true) goto L7;
        return;
    L7:
        if (this.photoPreviewLayout != null) goto L13;
        Context r0 = getContext();
        Theme.ResourcesProvider r1 = this.parentThemeDelegate;
        if (r1 != null) goto L12;
        r1 = this.resourcesProvider;
    L12:
        ChatAttachAlertPhotoLayoutPreview r32 = new ChatAttachAlertPhotoLayoutPreview(this, r0, r1);
        this.photoPreviewLayout = r32;
        r32.bringToFront();
    L13:
        AttachAlertLayout r33 = this.currentAttachLayout;
        AttachAlertLayout r02 = this.photoPreviewLayout;
        if (r33 != r02) goto L16;
        r02 = this.photoLayout;
    L16:
        showLayout(r02);
    }
}
