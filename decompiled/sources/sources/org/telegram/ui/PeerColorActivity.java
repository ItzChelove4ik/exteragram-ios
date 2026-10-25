package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ReplacementSpan;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.Insets;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.ExteraConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import me.vkryl.core.BitwiseUtils;
import me.vkryl.core.reference.ReferenceList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.messenger.utils.DrawableUtils;
import org.telegram.messenger.utils.OnPostDrawView;
import org.telegram.messenger.utils.RectFMergeBounding;
import org.telegram.messenger.utils.ViewOutlineProviderImpl;
import org.telegram.messenger.utils.tlutils.AmountUtils$Amount;
import org.telegram.messenger.utils.tlutils.AmountUtils$Currency;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.EdgeToEdgeSupportMode;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.MessageDrawable;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.ThemePreviewMessagesCell;
import org.telegram.ui.Components.AnimatedColor;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Easings;
import org.telegram.ui.Components.FilledTabsView;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.Premium.PremiumGradient;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.SimpleThemeDescription;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.TextHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.ViewPagerFixed;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.BlurredBackgroundWithFadeDrawable;
import org.telegram.ui.Components.blur3.DownscaleScrollableNoiseSuppressor;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceRenderNode;
import org.telegram.ui.Components.blur3.utils.Blur3Utils;
import org.telegram.ui.Components.chat.ViewPositionWatcher;
import org.telegram.ui.Gifts.GiftSheet;
import org.telegram.ui.Gifts.ResaleGiftsFragment;
import org.telegram.ui.SelectAnimatedEmojiDialog;
import org.telegram.ui.Stars.StarGiftPatterns;
import org.telegram.ui.Stars.StarGiftSheet;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.StoriesUtilities;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes6.dex */
public class PeerColorActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private FrameLayout actionBarContainer;
    private boolean applying;
    private boolean applyingName;
    private boolean applyingProfile;
    private ImageView backButton;
    private BaseFragment bulletinFragment;
    private ButtonWithCounterView button;
    private FrameLayout buttonContainer;
    private FrameLayout buttonContainerInternal;
    private Page buttonPage;
    private View changeDayNightView;
    private ValueAnimator changeDayNightViewAnimator;
    private float changeDayNightViewProgress;
    private ColoredActionBar colorBar;
    private FrameLayout contentView;
    private final SparseIntArray currentColors;
    private ImageView dayNightItem;
    private final long dialogId;
    private boolean forceDark;
    private final StarsController.GiftsList gifts;
    private final StarsController.GiftsList giftsWithPeerColor;
    private final ReferenceList<BlurredBackgroundDrawable> glassAttachedDrawables;
    private final ReferenceList<View> glassAttachedViews;
    private final BlurredBackgroundDrawableViewFactory glassBackgroundDrawableFactory;
    private final BlurredBackgroundSourceRenderNode glassBackgroundSourceRenderNode;
    private final ArrayList<RectF> glassDrawablesPositions;
    private int glassDrawablesPositionsCount;
    private final ArrayList<RectF> glassDrawablesPositionsMerged;
    private final BlurredBackgroundDrawableViewFactory iBlur3BackgroundFactory;
    private final BlurredBackgroundDrawableViewFactory iBlur3ButtonFactory;
    private IBlur3Capture iBlur3Capture;
    private final BlurredBackgroundSourceColor iBlur3SourceBackgroundColor;
    private final BlurredBackgroundSourceColor iBlur3SourceButtonColor;
    private Insets insets;
    private OnPostDrawView invalidateBlurredSourcesView;
    private final boolean isChannel;
    private boolean isDark;
    public boolean loading;
    private final MessageDrawable msgInDrawable;
    private final MessageDrawable msgInDrawableSelected;
    public Page namePage;
    private Theme.ResourcesProvider parentResourcesProvider;
    public Page profilePage;
    private final DownscaleScrollableNoiseSuppressor scrollableViewNoiseSuppressor;
    private boolean startAtProfile;
    private RLottieDrawable sunDrawable;
    private int tabsPinOffset;
    private FilledTabsView tabsView;
    private SimpleTextView titleView;
    private ViewPagerFixed viewPager;

    /* renamed from: org.telegram.ui.PeerColorActivity$1, reason: invalid class name */
    public class AnonymousClass1 implements Theme.ResourcesProvider {
        final /* synthetic */ PeerColorActivity this$0;

        public AnonymousClass1(PeerColorActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public int getColor(int r3) {
            int r0 = PeerColorActivity.m16678$$Nest$fgetcurrentColors(this.this$0).indexOfKey(r3);
            PeerColorActivity r1 = this.this$0;
            if (r0 < 0) goto L7;
            return PeerColorActivity.m16678$$Nest$fgetcurrentColors(r1).valueAt(r0);
        L7:
            if (PeerColorActivity.m16689$$Nest$fgetparentResourcesProvider(r1) == null) goto L11;
            return PeerColorActivity.m16689$$Nest$fgetparentResourcesProvider(this.this$0).getColor(r3);
        L11:
            return Theme.getColor(r3);
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public Drawable getDrawable(String r3) {
            if (r3.equals("drawableMsgIn") == true) goto L5;
            boolean r0 = r3.equals("drawableMsgInSelected");
            PeerColorActivity r1 = this.this$0;
            if (r0 == false) goto L11;
            return PeerColorActivity.m16688$$Nest$fgetmsgInDrawableSelected(r1);
        L11:
            if (PeerColorActivity.m16689$$Nest$fgetparentResourcesProvider(r1) == null) goto L15;
            return PeerColorActivity.m16689$$Nest$fgetparentResourcesProvider(this.this$0).getDrawable(r3);
        L15:
            return Theme.getThemeDrawable(r3);
        L5:
            return PeerColorActivity.m16687$$Nest$fgetmsgInDrawable(this.this$0);
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public Paint getPaint(String r1) {
            return super.getPaint(r1);
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public boolean isDark() {
            return PeerColorActivity.m16686$$Nest$fgetisDark(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.PeerColorActivity$2, reason: invalid class name */
    public class AnonymousClass2 implements Bulletin.Delegate {
        final /* synthetic */ PeerColorActivity this$0;

        public AnonymousClass2(PeerColorActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public boolean clipWithGradient(int r1) {
            return true;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getBottomOffset(int r1) {
            return AndroidUtilities.dp(62.0f);
        }
    }

    /* renamed from: org.telegram.ui.PeerColorActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends FrameLayout {
        final /* synthetic */ PeerColorActivity this$0;

        public AnonymousClass3(PeerColorActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            if (PeerColorActivity.m16672$$Nest$fgetactionBarContainer(this.this$0) == null) goto L5;
            ViewGroup.MarginLayoutParams r0 = (ViewGroup.MarginLayoutParams) PeerColorActivity.m16672$$Nest$fgetactionBarContainer(this.this$0).getLayoutParams();
            r0.height = ActionBar.getCurrentActionBarHeight();
            ViewGroup.MarginLayoutParams r02 = (ViewGroup.MarginLayoutParams) PeerColorActivity.m16672$$Nest$fgetactionBarContainer(this.this$0).getLayoutParams();
            r02.topMargin = AndroidUtilities.statusBarHeight;
        L5:
            super.onMeasure(r3, r4);
        }
    }

    /* renamed from: org.telegram.ui.PeerColorActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends ColoredActionBar {
        private int lastBtnColor;
        final /* synthetic */ PeerColorActivity this$0;

        public AnonymousClass4(PeerColorActivity r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
            this.lastBtnColor = 0;
        }

        @Override // org.telegram.ui.PeerColorActivity.ColoredActionBar
        public void onUpdateColor() {
            this.this$0.updateLightStatusBar();
            updateActionBarButtonsColor();
            PeerColorActivity.m16704$$Nest$mupdateTabColors(this.this$0);
            PeerColorActivity.m16698$$Nest$mcheckPagesColors(this.this$0);
        }

        public void updateActionBarButtonsColor() {
            int r0 = getActionBarButtonColor();
            if (this.lastBtnColor != r0) goto L5;
            return;
        L5:
            if (PeerColorActivity.m16673$$Nest$fgetbackButton(this.this$0) == null) goto L8;
            this.lastBtnColor = r0;
            PeerColorActivity.m16673$$Nest$fgetbackButton(this.this$0).setColorFilter(new PorterDuffColorFilter(r0, PorterDuff.Mode.SRC_IN));
        L8:
            if (PeerColorActivity.m16679$$Nest$fgetdayNightItem(this.this$0) == null) goto L12;
            this.lastBtnColor = r0;
            PeerColorActivity.m16679$$Nest$fgetdayNightItem(this.this$0).setColorFilter(new PorterDuffColorFilter(r0, PorterDuff.Mode.SRC_IN));
            return;
        }
    }

    /* renamed from: org.telegram.ui.PeerColorActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends ViewPagerFixed {
        final /* synthetic */ PeerColorActivity this$0;

        public AnonymousClass5(PeerColorActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onTabAnimationUpdate(boolean r3) {
            float r32 = PeerColorActivity.m16693$$Nest$fgetviewPager(this.this$0).getPositionAnimated();
            PeerColorActivity.m16692$$Nest$fgettabsView(this.this$0).setSelected(r32);
            PeerColorActivity.m16677$$Nest$fgetcolorBar(this.this$0).setProgressToGradient(1.0f - PeerColorActivity.m16700$$Nest$mgetTabProgressFromPageProgress(this.this$0, r32));
            PeerColorActivity.m16704$$Nest$mupdateTabColors(this.this$0);
            PeerColorActivity.m16703$$Nest$mupdateButtonState(this.this$0, true);
            PeerColorActivity.m16701$$Nest$minvalidateMergedVisibleBlurredPositionsAndSources(this.this$0, 1);
        }
    }

    /* renamed from: org.telegram.ui.PeerColorActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends ViewPagerFixed.Adapter {
        final /* synthetic */ PeerColorActivity this$0;

        public AnonymousClass6(PeerColorActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public void bindView(View r1, int r2, int r3) {
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public View createView(int r2) {
            if (r2 == 1) goto L5;
            if (r2 == 0) goto L8;
            return null;
        L8:
            return this.this$0.profilePage;
        L5:
            return this.this$0.namePage;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemCount() {
            return 2;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemViewType(int r1) {
            return r1;
        }
    }

    /* renamed from: org.telegram.ui.PeerColorActivity$7, reason: invalid class name */
    public class AnonymousClass7 extends View {
        final /* synthetic */ PeerColorActivity this$0;
        final /* synthetic */ Bitmap val$bitmap;
        final /* synthetic */ Canvas val$bitmapCanvas;
        final /* synthetic */ Paint val$bitmapPaint;
        final /* synthetic */ float val$cx;
        final /* synthetic */ float val$cy;
        final /* synthetic */ float val$r;
        final /* synthetic */ float val$x;
        final /* synthetic */ Paint val$xRefPaint;
        final /* synthetic */ float val$y;

        public AnonymousClass7(PeerColorActivity r1, Context r2, Canvas r3, float r4, float r5, float r6, Paint r7, Bitmap r8, Paint r9, float r10, float r11) {
            this.this$0 = r1;
            this.val$bitmapCanvas = r3;
            this.val$cx = r4;
            this.val$cy = r5;
            this.val$r = r6;
            this.val$xRefPaint = r7;
            this.val$bitmap = r8;
            this.val$bitmapPaint = r9;
            this.val$x = r10;
            this.val$y = r11;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r7) {
            super.onDraw(r7);
            if (PeerColorActivity.m16686$$Nest$fgetisDark(this.this$0) == true) goto L5;
            r7.drawCircle(this.val$cx, this.val$cy, this.val$r * (1.0f - PeerColorActivity.m16676$$Nest$fgetchangeDayNightViewProgress(this.this$0)), this.val$bitmapPaint);
        L9:
            r7.save();
            r7.translate(this.val$x, this.val$y);
            PeerColorActivity.m16679$$Nest$fgetdayNightItem(this.this$0).draw(r7);
            r7.restore();
            return;
        L5:
            if (PeerColorActivity.m16676$$Nest$fgetchangeDayNightViewProgress(this.this$0) <= 0.0f) goto L7;
            this.val$bitmapCanvas.drawCircle(this.val$cx, this.val$cy, this.val$r * PeerColorActivity.m16676$$Nest$fgetchangeDayNightViewProgress(this.this$0), this.val$xRefPaint);
        L7:
            r7.drawBitmap(this.val$bitmap, 0.0f, 0.0f, this.val$bitmapPaint);
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.PeerColorActivity$8, reason: invalid class name */
    public class AnonymousClass8 implements ValueAnimator.AnimatorUpdateListener {
        boolean changedNavigationBarColor;
        final /* synthetic */ PeerColorActivity this$0;

        public AnonymousClass8(PeerColorActivity r1) {
            this.this$0 = r1;
            this.changedNavigationBarColor = false;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r2) {
            PeerColorActivity.m16696$$Nest$fputchangeDayNightViewProgress(this.this$0, ((Float) r2.getAnimatedValue()).floatValue());
            PeerColorActivity.m16675$$Nest$fgetchangeDayNightView(this.this$0).invalidate();
            if (this.changedNavigationBarColor == false) goto L5;
            return;
        L5:
            if (PeerColorActivity.m16676$$Nest$fgetchangeDayNightViewProgress(this.this$0) <= 0.5f) goto L9;
            this.changedNavigationBarColor = true;
            return;
        }
    }

    /* renamed from: org.telegram.ui.PeerColorActivity$9, reason: invalid class name */
    public class AnonymousClass9 extends AnimatorListenerAdapter {
        final /* synthetic */ PeerColorActivity this$0;

        public AnonymousClass9(PeerColorActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            if (PeerColorActivity.m16675$$Nest$fgetchangeDayNightView(this.this$0) != null) goto L5;
        L8:
            PeerColorActivity.m16695$$Nest$fputchangeDayNightViewAnimator(this.this$0, null);
            super.onAnimationEnd(r4);
            return;
        L5:
            if (PeerColorActivity.m16675$$Nest$fgetchangeDayNightView(this.this$0).getParent() == null) goto L7;
            ((ViewGroup) PeerColorActivity.m16675$$Nest$fgetchangeDayNightView(this.this$0).getParent()).removeView(PeerColorActivity.m16675$$Nest$fgetchangeDayNightView(this.this$0));
        L7:
            PeerColorActivity.m16694$$Nest$fputchangeDayNightView(this.this$0, null);
            goto L8
        }
    }

    public final class ButtonState {
        private CharSequence subText;
        private CharSequence text;
        final /* synthetic */ PeerColorActivity this$0;

        /* renamed from: -$$Nest$fgetsubText, reason: not valid java name */
        public static /* bridge */ /* synthetic */ CharSequence m16705$$Nest$fgetsubText(ButtonState r0) {
            return r0.subText;
        }

        /* renamed from: -$$Nest$fgettext, reason: not valid java name */
        public static /* bridge */ /* synthetic */ CharSequence m16706$$Nest$fgettext(ButtonState r0) {
            return r0.text;
        }

        /* renamed from: -$$Nest$fputsubText, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16707$$Nest$fputsubText(ButtonState r0, CharSequence r1) {
            r0.subText = r1;
        }

        /* renamed from: -$$Nest$fputtext, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16708$$Nest$fputtext(ButtonState r0, CharSequence r1) {
            r0.text = r1;
        }

        private ButtonState(PeerColorActivity r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ ButtonState(PeerColorActivity r1, PeerColorActivityIA r2) {
            this(r1);
        }
    }

    public static class ChangeNameColorCell extends View {
        private final Text buttonText;
        private PeerColorDrawable color1Drawable;
        private PeerColorDrawable color2Drawable;
        private final int currentAccount;
        private final Drawable drawable;
        private final boolean isChannelOrGroup;
        private final boolean isGroup;
        private LevelLock lock;
        private boolean needDivider;
        private final Theme.ResourcesProvider resourcesProvider;
        private Text userText;
        private final Paint userTextBackgroundPaint;
        private int userTextColorKey;

        public ChangeNameColorCell(int r10, long r11, Context r13, Theme.ResourcesProvider r14) {
            super(r13);
            this.userTextBackgroundPaint = new Paint(1);
            this.userTextColorKey = -1;
            MessagesController r0 = MessagesController.getInstance(r10);
            TLRPC.Chat r2 = r0.getChat(Long.valueOf(-r11));
            this.currentAccount = r10;
            int r12 = 0;
            if (r11 >= 0) goto L5;
            boolean r112 = true;
        L6:
            this.isChannelOrGroup = r112;
            if (r112 == true) goto L9;
        L11:
            boolean r3 = false;
        L12:
            this.isGroup = r3;
            this.resourcesProvider = r14;
            Drawable r4 = r13.getResources().getDrawable(R.drawable.menu_edit_appearance).mutate();
            this.drawable = r4;
            r4.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4, r14), PorterDuff.Mode.SRC_IN));
            if (r112 == false) goto L17;
            if (r3 == false) goto L16;
            int r42 = R.string.ChangeGroupAppearance;
        L18:
            String r43 = LocaleController.getString(r42);
            if (r112 == false) goto L36;
            if (r3 == true) goto L36;
            if (MessagesController.getInstance(r10).getMainSettings().getInt("boostingappearance", 0) >= 3) goto L36;
            MessagesController.PeerColors r102 = r0.peerColors;
            int r113 = Integer.MAX_VALUE;
            if (r102 == null) goto L26;
            int r103 = Math.min(Integer.MAX_VALUE, r102.maxLevel());
            int r114 = Math.max(0, r0.peerColors.maxLevel());
            int r104 = Math.min(r103, r0.peerColors.minLevel());
            int r115 = Math.max(r114, r0.peerColors.minLevel());
            r113 = r104;
            int r105 = r115;
        L27:
            int r116 = Math.min(r113, r0.channelBgIconLevelMin);
            int r106 = Math.min(r105, r0.channelBgIconLevelMin);
            MessagesController.PeerColors r32 = r0.profilePeerColors;
            if (r32 == null) goto L30;
            int r117 = Math.min(r116, r32.maxLevel());
            int r107 = Math.max(r106, r0.profilePeerColors.maxLevel());
            r116 = Math.min(r117, r0.profilePeerColors.minLevel());
            r106 = Math.max(r107, r0.profilePeerColors.minLevel());
        L30:
            int r118 = Math.min(r116, r0.channelProfileIconLevelMin);
            int r108 = Math.max(r106, r0.channelProfileIconLevelMin);
            int r119 = Math.min(r118, r0.channelEmojiStatusLevelMin);
            int r109 = Math.max(r108, r0.channelEmojiStatusLevelMin);
            int r1110 = Math.min(r119, r0.channelWallpaperLevelMin);
            int r1010 = Math.max(r109, r0.channelWallpaperLevelMin);
            int r1111 = Math.min(r1110, r0.channelCustomWallpaperLevelMin);
            int r1011 = Math.max(r1010, r0.channelCustomWallpaperLevelMin);
            if (r2 == null) goto L34;
            r12 = r2.level;
        L34:
            if (r12 >= r1011) goto L36;
            this.lock = new LevelLock(r13, true, Math.max(r12, r1111), r14);
            goto L36
        L26:
            r105 = 0;
        L36:
            setContentDescription(r43);
            this.buttonText = new Text(r43, 16.0f);
            updateColors();
            return;
        L16:
            r42 = R.string.ChangeChannelNameColor2;
            goto L18
        L17:
            r42 = R.string.ChangeUserNameColor;
            goto L18
        L9:
            if (ChatObject.isChannelAndNotMegaGroup(r2) == true) goto L11;
            r3 = true;
            goto L12
        L5:
            r112 = false;
            goto L6
        }

        private int rtl(int r2) {
            if (LocaleController.isRTL == true) goto L5;
            return r2;
        L5:
            return getMeasuredWidth() - r2;
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r15) {
            DrawableUtils.setBounds(this.drawable, rtl(AndroidUtilities.dp(32.0f)), getMeasuredHeight() / 2.0f, 17);
            this.drawable.draw(r15);
            Text r0 = this.buttonText;
            int r1 = getMeasuredWidth() - AndroidUtilities.dp(171.0f);
            LevelLock r2 = this.lock;
            int r4 = 0;
            if (r2 == null) goto L5;
            int r22 = r2.getIntrinsicWidth() + AndroidUtilities.dp(8.0f);
        L6:
            r0.ellipsize(r1 - r22);
            if (LocaleController.isRTL == false) goto L9;
            float r02 = (getMeasuredWidth() - this.buttonText.getWidth()) - AndroidUtilities.dp(71.0f);
        L10:
            this.buttonText.draw(r15, r02, getMeasuredHeight() / 2.0f);
            if (this.lock == null) goto L13;
            int r03 = (int) ((r02 + this.buttonText.getWidth()) + AndroidUtilities.dp(6.0f));
            this.lock.setBounds(r03, 0, r03, getHeight());
            this.lock.draw(r15);
        L13:
            boolean r04 = this.isGroup;
            if (r04 == false) goto L23;
            if (this.color2Drawable == null) goto L23;
            if (LocaleController.isRTL == false) goto L20;
            int r05 = AndroidUtilities.dp(58.0f);
        L21:
            this.color2Drawable.setBounds(r05 - AndroidUtilities.dp(11.0f), (getMeasuredHeight() - AndroidUtilities.dp(11.0f)) / 2, r05, (getMeasuredHeight() + AndroidUtilities.dp(11.0f)) / 2);
            this.color2Drawable.stroke(AndroidUtilities.dpf2(3.0f), Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider));
            this.color2Drawable.draw(r15);
        L54:
            if (this.needDivider == false) goto L71;
            Theme.ResourcesProvider r06 = this.resourcesProvider;
            if (r06 == null) goto L58;
            Paint r07 = r06.getPaint("paintDivider");
        L59:
            if (r07 != null) goto L61;
            r07 = Theme.dividerPaint;
        L61:
            Paint r11 = r07;
            if (LocaleController.isRTL == false) goto L65;
            float r08 = 0.0f;
        L64:
            float r7 = r08;
            float r8 = getMeasuredHeight() - 1;
            int r09 = getMeasuredWidth();
            if (LocaleController.isRTL == false) goto L69;
            r4 = AndroidUtilities.dp(58.0f);
        L69:
            r15.drawLine(r7, r8, r09 - r4, getMeasuredHeight() - 1, r11);
            return;
        L65:
            r08 = AndroidUtilities.dp(58.0f);
            goto L64
        L58:
            r07 = null;
            goto L59
        L71:
            return;
        L20:
            r05 = getMeasuredWidth() - AndroidUtilities.dp(24.0f);
        L23:
            if (this.color1Drawable == null) goto L32;
            if (this.color2Drawable == null) goto L32;
            if (LocaleController.isRTL == false) goto L29;
            int r010 = AndroidUtilities.dp(58.0f);
        L30:
            this.color2Drawable.setBounds(r010 - AndroidUtilities.dp(11.0f), (getMeasuredHeight() - AndroidUtilities.dp(11.0f)) / 2, r010, (getMeasuredHeight() + AndroidUtilities.dp(11.0f)) / 2);
            PeerColorDrawable r23 = this.color2Drawable;
            float r3 = AndroidUtilities.dpf2(3.0f);
            int r72 = Theme.key_windowBackgroundWhite;
            r23.stroke(r3, Theme.getColor(r72, this.resourcesProvider));
            this.color2Drawable.draw(r15);
            int r011 = r010 - AndroidUtilities.dp(18.0f);
            this.color1Drawable.setBounds(r011 - AndroidUtilities.dp(11.0f), (getMeasuredHeight() - AndroidUtilities.dp(11.0f)) / 2, r011, (getMeasuredHeight() + AndroidUtilities.dp(11.0f)) / 2);
            this.color1Drawable.stroke(AndroidUtilities.dpf2(3.0f), Theme.getColor(r72, this.resourcesProvider));
            this.color1Drawable.draw(r15);
            goto L54
        L29:
            r010 = getMeasuredWidth() - AndroidUtilities.dp(24.0f);
        L32:
            if (this.userText == null) goto L54;
            if (r04 == true) goto L54;
            float r012 = getMeasuredWidth() - AndroidUtilities.dp(116.0f);
            float r12 = this.buttonText.getWidth();
            LevelLock r6 = this.lock;
            if (r6 != null) goto L37;
            int r62 = 0;
        L38:
            float r013 = (int) (r012 - Math.min(r12 + r62, getMeasuredWidth() - AndroidUtilities.dp(164.0f)));
            int r13 = (int) Math.min(this.userText.getWidth(), r013);
            RectF r63 = AndroidUtilities.rectTmp;
            if (LocaleController.isRTL == false) goto L42;
            int r82 = AndroidUtilities.dp(15.0f);
        L41:
            float r83 = r82;
            float r112 = (getMeasuredHeight() - AndroidUtilities.dp(22.0f)) / 2.0f;
            if (LocaleController.isRTL == false) goto L47;
            int r9 = AndroidUtilities.dp(33.0f) + r13;
        L48:
            r63.set(r83, r112, r9, (getMeasuredHeight() + AndroidUtilities.dp(22.0f)) / 2.0f);
            r15.drawRoundRect(r63, AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), this.userTextBackgroundPaint);
            Text r014 = this.userText.ellipsize(r013);
            if (LocaleController.isRTL == false) goto L51;
            float r14 = AndroidUtilities.dp(24.0f);
        L52:
            r014.draw(r15, r14, getMeasuredHeight() / 2.0f);
            goto L54
        L51:
            r14 = (getMeasuredWidth() - AndroidUtilities.dp(24.0f)) - r13;
            goto L52
        L47:
            r9 = getMeasuredWidth() - AndroidUtilities.dp(15.0f);
            goto L48
        L42:
            r82 = (getMeasuredWidth() - AndroidUtilities.dp(33.0f)) - r13;
            goto L41
        L37:
            r62 = r6.getIntrinsicWidth() + AndroidUtilities.dp(12.0f);
            goto L38
        L9:
            r02 = AndroidUtilities.dp(71.0f);
            goto L10
        L5:
            r22 = 0;
            goto L6
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
        }

        public void set(TLRPC.Chat r13, boolean r14) {
            if (r13 == null) goto L60;
            this.needDivider = r14;
            this.userText = new Text(Emoji.replaceEmoji(r13.title, Theme.chat_msgTextPaint.getFontMetricsInt(), false), 13.0f, AndroidUtilities.bold());
            PeerColorDrawable r142 = this.color1Drawable;
            MessagesController.PeerColor r0 = null;
            if (r142 == null) goto L7;
            r142.setView(null);
        L7:
            TLRPC.EmojiStatus r143 = r13.emoji_status;
            if ((r143 instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L11;
            this.color1Drawable = PeerColorDrawable.from((TLRPC.TL_emojiStatusCollectible) r143);
        L15:
            PeerColorDrawable r144 = this.color1Drawable;
            if (r144 == null) goto L18;
            r144.setView(this);
        L18:
            TLRPC.PeerColor r145 = r13.color;
            if ((r145 instanceof TLRPC.TL_peerColorCollectible) == false) goto L45;
            TLRPC.TL_peerColorCollectible r146 = (TLRPC.TL_peerColorCollectible) r145;
            Theme.ResourcesProvider r132 = this.resourcesProvider;
            if (r132 == null) goto L23;
            boolean r133 = r132.isDark();
        L25:
            if (r133 == true) goto L27;
        L29:
            int r2 = r146.accent_color;
        L30:
            if (r133 == false) goto L34;
            ArrayList<Integer> r134 = r146.dark_colors;
            if (r134 == null) goto L34;
        L35:
            int r7 = r134.get(0).intValue() | (-16777216);
            if (r134.size() < 2) goto L38;
            int r8 = r134.get(1).intValue() | (-16777216);
        L40:
            if (r134.size() < 3) goto L42;
            int r9 = r134.get(2).intValue() | (-16777216);
        L43:
            this.userText.setColor(r2);
            this.userTextBackgroundPaint.setColor(Theme.multAlpha(r2, 0.1f));
            PeerColorDrawable r135 = new PeerColorDrawable(r7, r8, r9, r146.gift_emoji_id).setRadius(AndroidUtilities.dp(11.0f));
            this.color2Drawable = r135;
            r135.setView(this);
            return;
        L42:
            r9 = r7;
            goto L43
        L38:
            r8 = r7;
        L34:
            r134 = r146.colors;
            goto L35
        L27:
            if ((r146.flags & 1) == 0) goto L29;
            r2 = r146.dark_accent_color;
            goto L30
        L23:
            r133 = Theme.isCurrentThemeDark();
            goto L25
        L45:
            int r136 = ChatObject.getColorId(r13);
            if (r136 >= 7) goto L48;
            int r147 = Theme.keys_avatar_nameInMessage[r136];
            this.userTextColorKey = r147;
            int r148 = Theme.getColor(r147, this.resourcesProvider);
        L55:
            this.userText.setColor(r148);
            this.userTextBackgroundPaint.setColor(Theme.multAlpha(r148, 0.1f));
            PeerColorDrawable r137 = PeerColorDrawable.from(this.currentAccount, r136).setRadius(AndroidUtilities.dp(11.0f));
            this.color2Drawable = r137;
            if (r137 == null) goto L59;
            r137.setView(this);
            return;
        L59:
            return;
        L48:
            MessagesController.PeerColors r149 = MessagesController.getInstance(UserConfig.selectedAccount).peerColors;
            if (r149 == null) goto L52;
            r0 = r149.getColor(r136);
        L52:
            if (r0 == null) goto L54;
            this.userTextColorKey = -1;
            r148 = r0.getColor1();
            goto L55
        L54:
            int r1410 = Theme.keys_avatar_nameInMessage[0];
            this.userTextColorKey = r1410;
            r148 = Theme.getColor(r1410, this.resourcesProvider);
            goto L55
        L11:
            if (ChatObject.getProfileColorId(r13) < 0) goto L13;
            PeerColorDrawable r1411 = PeerColorDrawable.fromProfile(this.currentAccount, ChatObject.getProfileColorId(r13)).setRadius(AndroidUtilities.dp(11.0f));
        L14:
            this.color1Drawable = r1411;
            goto L15
        L13:
            r1411 = null;
            goto L14
        }

        public void set(TLRPC.User r14) {
            if (r14 != null) goto L4;
            return;
        L4:
            String r0 = r14.first_name;
            if (r0 != null) goto L7;
            String r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        L8:
            int r1 = r02.indexOf(" ");
            if (r1 <= 0) goto L11;
            r02 = r02.substring(0, r1);
        L11:
            this.userText = new Text(Emoji.replaceEmoji(r02, Theme.chat_msgTextPaint.getFontMetricsInt(), false), 13.0f, AndroidUtilities.bold());
            PeerColorDrawable r03 = this.color1Drawable;
            MessagesController.PeerColor r12 = null;
            if (r03 == null) goto L14;
            r03.setView(null);
        L14:
            TLRPC.EmojiStatus r04 = r14.emoji_status;
            if ((r04 instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L18;
            this.color1Drawable = PeerColorDrawable.from((TLRPC.TL_emojiStatusCollectible) r04);
        L22:
            PeerColorDrawable r05 = this.color1Drawable;
            if (r05 == null) goto L25;
            r05.setView(this);
        L25:
            TLRPC.PeerColor r06 = r14.color;
            if ((r06 instanceof TLRPC.TL_peerColorCollectible) == false) goto L52;
            TLRPC.TL_peerColorCollectible r07 = (TLRPC.TL_peerColorCollectible) r06;
            Theme.ResourcesProvider r142 = this.resourcesProvider;
            if (r142 == null) goto L30;
            boolean r143 = r142.isDark();
        L32:
            if (r143 == true) goto L34;
        L36:
            int r3 = r07.accent_color;
        L37:
            if (r143 == false) goto L41;
            ArrayList<Integer> r144 = r07.dark_colors;
            if (r144 == null) goto L41;
        L42:
            int r8 = r144.get(0).intValue() | (-16777216);
            if (r144.size() < 2) goto L45;
            int r9 = r144.get(1).intValue() | (-16777216);
        L47:
            if (r144.size() < 3) goto L49;
            int r10 = r144.get(2).intValue() | (-16777216);
        L50:
            this.userText.setColor(r3);
            this.userTextBackgroundPaint.setColor(Theme.multAlpha(r3, 0.1f));
            PeerColorDrawable r145 = new PeerColorDrawable(r8, r9, r10, r07.gift_emoji_id).setRadius(AndroidUtilities.dp(11.0f));
            this.color2Drawable = r145;
            r145.setView(this);
            return;
        L49:
            r10 = r8;
            goto L50
        L45:
            r9 = r8;
        L41:
            r144 = r07.colors;
            goto L42
        L34:
            if ((r07.flags & 1) == 0) goto L36;
            r3 = r07.dark_accent_color;
            goto L37
        L30:
            r143 = Theme.isCurrentThemeDark();
            goto L32
        L52:
            int r146 = UserObject.getColorId(r14);
            if (r146 >= 7) goto L55;
            int r08 = Theme.keys_avatar_nameInMessage[r146];
            this.userTextColorKey = r08;
            int r09 = Theme.getColor(r08, this.resourcesProvider);
        L62:
            this.userText.setColor(r09);
            this.userTextBackgroundPaint.setColor(Theme.multAlpha(r09, 0.1f));
            this.color2Drawable = PeerColorDrawable.from(this.currentAccount, r146).setRadius(AndroidUtilities.dp(11.0f));
            return;
        L55:
            MessagesController.PeerColors r010 = MessagesController.getInstance(UserConfig.selectedAccount).peerColors;
            if (r010 == null) goto L59;
            r12 = r010.getColor(r146);
        L59:
            if (r12 == null) goto L61;
            this.userTextColorKey = -1;
            r09 = r12.getColor1();
            goto L62
        L61:
            int r011 = Theme.keys_avatar_nameInMessage[0];
            this.userTextColorKey = r011;
            r09 = Theme.getColor(r011, this.resourcesProvider);
            goto L62
        L18:
            if (UserObject.getProfileColorId(r14) < 0) goto L20;
            PeerColorDrawable r012 = PeerColorDrawable.fromProfile(this.currentAccount, UserObject.getProfileColorId(r14)).setRadius(AndroidUtilities.dp(11.0f));
        L21:
            this.color1Drawable = r012;
            goto L22
        L20:
            r012 = null;
            goto L21
        L7:
            r02 = r0.trim();
            goto L8
        }

        public void updateColors() {
            Drawable r0 = this.drawable;
            if (this.isChannelOrGroup == false) goto L5;
            int r2 = Theme.key_windowBackgroundWhiteGrayIcon;
        L6:
            r0.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r2, this.resourcesProvider), PorterDuff.Mode.SRC_IN));
            Text r02 = this.buttonText;
            if (this.isChannelOrGroup == false) goto L9;
            int r1 = Theme.key_windowBackgroundWhiteBlackText;
        L10:
            r02.setColor(Theme.getColor(r1, this.resourcesProvider));
            if (this.userText != null) goto L13;
            return;
        L13:
            if (this.userTextBackgroundPaint == null) goto L19;
            int r03 = this.userTextColorKey;
            if (r03 == (-1)) goto L20;
            int r04 = Theme.getColor(r03, this.resourcesProvider);
            this.userText.setColor(r04);
            this.userTextBackgroundPaint.setColor(Theme.multAlpha(r04, 0.1f));
            return;
        L20:
            return;
        L19:
            return;
        L9:
            r1 = Theme.key_windowBackgroundWhiteBlueText4;
            goto L10
        L5:
            r2 = Theme.key_windowBackgroundWhiteBlueText4;
            goto L6
        }
    }

    public static class ColoredActionBar extends View {
        private RadialGradient backgroundGradient;
        private int backgroundGradientColor1;
        private int backgroundGradientColor2;
        private int backgroundGradientHeight;
        private int backgroundGradientWidth;
        private final Paint backgroundPaint;
        public int color1;
        private final AnimatedColor color1Animated;
        public int color2;
        private final AnimatedColor color2Animated;
        private int defaultColor;
        protected boolean ignoreMeasure;
        public boolean isDefault;
        protected boolean newMode;
        private float progressToGradient;
        private final Theme.ResourcesProvider resourcesProvider;

        public ColoredActionBar(Context r4, Theme.ResourcesProvider r5) {
            super(r4);
            this.progressToGradient = 0.0f;
            CubicBezierInterpolator r0 = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.color1Animated = new AnimatedColor(this, 350, r0);
            this.color2Animated = new AnimatedColor(this, 350, r0);
            this.backgroundPaint = new Paint(1);
            this.resourcesProvider = r5;
            this.defaultColor = Theme.getColor(Theme.key_actionBarDefault, r5);
            setColor(-1, -1, false);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r11) {
            int r0 = this.color1Animated.set(this.color1);
            int r1 = this.color2Animated.set(this.color2);
            if (this.backgroundGradient != null) goto L5;
        L12:
            this.backgroundGradientWidth = getWidth();
            this.backgroundGradientHeight = getHeight();
            int r2 = this.backgroundGradientWidth;
            int r5 = this.backgroundGradientHeight;
            float r22 = AndroidUtilities.distance(0.0f, 0.0f, r2, r5) * 0.75f;
            this.backgroundGradientColor2 = r1;
            this.backgroundGradientColor1 = r0;
            RadialGradient r3 = new RadialGradient(r2 / 2.0f, r5 * 0.4f, r22, new int[]{r1, r0}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
            this.backgroundGradient = r3;
            this.backgroundPaint.setShader(r3);
            onUpdateColor();
        L14:
            if (this.progressToGradient < 1.0f) goto L16;
        L18:
            Paint r02 = this.backgroundPaint;
            if (this.newMode == false) goto L21;
            int r12 = 255;
        L22:
            r02.setAlpha(r12);
            r11.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.backgroundPaint);
            return;
        L21:
            r12 = (int) (this.progressToGradient * 255.0f);
            goto L22
        L16:
            if (this.newMode == true) goto L18;
            r11.drawColor(this.defaultColor);
            goto L18
        L5:
            if (this.backgroundGradientColor1 != r0) goto L12;
            if (this.backgroundGradientColor2 != r1) goto L12;
            if (this.backgroundGradientWidth != getWidth()) goto L12;
            if (this.backgroundGradientHeight == getHeight()) goto L14;
            goto L12
        }

        public int getActionBarButtonColor() {
            int r0 = Theme.key_actionBarDefaultIcon;
            int r1 = Theme.getColor(r0, this.resourcesProvider);
            if (this.isDefault == false) goto L5;
            int r02 = Theme.getColor(r0, this.resourcesProvider);
        L7:
            return ColorUtils.blendARGB(r1, r02, this.progressToGradient);
        L5:
            r02 = -1;
            goto L7
        }

        public int getColor() {
            return ColorUtils.blendARGB(Theme.getColor(Theme.key_actionBarDefault, this.resourcesProvider), ColorUtils.blendARGB(this.color1Animated.get(), this.color2Animated.get(), 0.75f), this.progressToGradient);
        }

        public int getTabsViewBackgroundColor() {
            int r0 = Theme.key_actionBarDefault;
            if (AndroidUtilities.computePerceivedBrightness(Theme.getColor(r0, this.resourcesProvider)) <= 0.721f) goto L5;
            int r02 = Theme.getColor(Theme.key_actionBarDefaultIcon, this.resourcesProvider);
        L7:
            if (AndroidUtilities.computePerceivedBrightness(ColorUtils.blendARGB(this.color1Animated.get(), this.color2Animated.get(), 0.75f)) <= 0.721f) goto L9;
            int r1 = Theme.getColor(Theme.key_windowBackgroundWhiteBlueIcon, this.resourcesProvider);
        L11:
            return ColorUtils.blendARGB(r02, r1, this.progressToGradient);
        L9:
            r1 = Theme.adaptHSV(ColorUtils.blendARGB(this.color1Animated.get(), this.color2Animated.get(), 0.75f), 0.08f, -0.08f);
            goto L11
        L5:
            r02 = Theme.adaptHSV(Theme.getColor(r0, this.resourcesProvider), 0.08f, -0.08f);
            goto L7
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            if (this.ignoreMeasure == true) goto L6;
            r3 = View.MeasureSpec.makeMeasureSpec(AndroidUtilities.statusBarHeight + AndroidUtilities.dp(230.0f), TLObject.FLAG_30);
        L6:
            super.onMeasure(r2, r3);
        }

        public void onUpdateColor() {
        }

        public void setColor(int r2, int r3, boolean r4) {
            MessagesController.PeerColor r0 = null;
            if (r3 < 0) goto L9;
            if (r2 < 0) goto L9;
            MessagesController.PeerColors r22 = MessagesController.getInstance(r2).profilePeerColors;
            if (r22 == null) goto L9;
            r0 = r22.getColor(r3);
        L9:
            setColor(r0, r4);
        }

        public void setColor(MessagesController.PeerColor r4, boolean r5) {
            this.isDefault = false;
            if (r4 != null) goto L5;
            this.isDefault = true;
            int r42 = Theme.getColor(Theme.key_actionBarDefault, this.resourcesProvider);
            this.color2 = r42;
            this.color1 = r42;
        L10:
            if (r5 == true) goto L12;
            this.color1Animated.set(this.color1, true);
            this.color2Animated.set(this.color2, true);
        L12:
            invalidate();
            return;
        L5:
            Theme.ResourcesProvider r1 = this.resourcesProvider;
            if (r1 == null) goto L8;
            boolean r12 = r1.isDark();
        L9:
            this.color1 = r4.getBgColor1(r12);
            this.color2 = r4.getBgColor2(r12);
            goto L10
        L8:
            r12 = Theme.isCurrentThemeDark();
            goto L9
        }

        public void setProgressToGradient(float r3) {
            if (Math.abs(this.progressToGradient - r3) <= 0.001f) goto L6;
            this.progressToGradient = r3;
            onUpdateColor();
            invalidate();
            return;
        }

        public void updateColors() {
            this.defaultColor = Theme.getColor(Theme.key_windowBackgroundGray, this.resourcesProvider);
            onUpdateColor();
            invalidate();
        }
    }

    public static class GiftCell extends FrameLayout {
        public TL_stars.starGiftAttributeBackdrop backdrop;
        public final FrameLayout card;
        public final GiftSheet.CardBackground cardBackground;
        public long id;
        public final BackupImageView imageView;
        private TLRPC.Document lastDocument;
        private long lastDocumentId;
        public TL_stars.starGiftAttributePattern pattern;
        private final GiftSheet.Ribbon ribbon;

        public static class Factory extends UItem.UItemFactory<GiftCell> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem asGiftCell(TL_stars.SavedStarGift r1) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.object = r1;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                GiftCell r12 = (GiftCell) r1;
                r12.set(-1, (TL_stars.SavedStarGift) r2.object);
                r12.setSelected(r2.checked, false);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public GiftCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new GiftCell(r1, true, r5);
            }
        }

        public GiftCell(Context r9, boolean r10, Theme.ResourcesProvider r11) {
            super(r9);
            FrameLayout r0 = new FrameLayout(r9);
            this.card = r0;
            GiftSheet.CardBackground r1 = new GiftSheet.CardBackground(r0, r11, false);
            this.cardBackground = r1;
            r0.setBackground(r1);
            addView(r0, LayoutHelper.createFrame(-1, -1, 119));
            ScaleStateListAnimator.apply(r0, 0.025f, 1.25f);
            BackupImageView r112 = new BackupImageView(r9);
            this.imageView = r112;
            r0.addView(r112, LayoutHelper.createFrame(80, 80.0f, 17, 0.0f, 12.0f, 0.0f, 12.0f));
            if (r10 == false) goto L6;
            GiftSheet.Ribbon r102 = new GiftSheet.Ribbon(r9);
            this.ribbon = r102;
            addView(r102, LayoutHelper.createFrame(-2, -2.0f, 53, 0.0f, 2.0f, 1.0f, 0.0f));
            return;
        L6:
            this.ribbon = null;
        }

        private void setSticker(TLRPC.Document r11, Object r12) {
            if (r11 != null) goto L6;
            this.imageView.clearImage();
            this.lastDocument = null;
            this.lastDocumentId = 0;
            return;
        L6:
            if (this.lastDocument != r11) goto L8;
            return;
        L8:
            this.lastDocument = r11;
            this.lastDocumentId = r11.id;
            TLRPC.PhotoSize r0 = FileLoader.getClosestPhotoSizeWithSize(r11.thumbs, AndroidUtilities.dp(100.0f));
            SvgHelper.SvgDrawable r8 = DocumentObject.getSvgThumb(r11, Theme.key_windowBackgroundGray, 0.3f);
            this.imageView.setImage(ImageLocation.getForDocument(r11), "100_100", ImageLocation.getForDocument(r0, r11), "100_100", r8, r12);
        }

        public long getGiftId() {
            return this.id;
        }

        public void set(int r4, TL_stars.SavedStarGift r5) {
            this.id = r5.gift.id;
            boolean r1 = true;
            if ((r4 % 3) == 1) goto L7;
            r1 = false;
        L7:
            if (r1 == false) goto L9;
            int r2 = AndroidUtilities.dp(4.0f);
        L10:
            if (r1 == false) goto L12;
            int r42 = AndroidUtilities.dp(4.0f);
        L13:
            setPadding(r2, 0, r42, 0);
            setSticker(r5.gift.getDocument(), r5.gift);
            this.backdrop = (TL_stars.starGiftAttributeBackdrop) StarsController.findAttribute(r5.gift.attributes, TL_stars.starGiftAttributeBackdrop.class);
            this.pattern = (TL_stars.starGiftAttributePattern) StarsController.findAttribute(r5.gift.attributes, TL_stars.starGiftAttributePattern.class);
            this.cardBackground.setBackdrop(this.backdrop);
            this.cardBackground.setPattern(this.pattern);
            GiftSheet.Ribbon r43 = this.ribbon;
            if (r43 == null) goto L17;
            r43.setBackdrop(this.backdrop);
            this.ribbon.setText(9, "#" + LocaleController.formatNumber(r5.gift.num, ','), false);
            return;
        L17:
            return;
        L12:
            r42 = 0;
            goto L13
        L9:
            r2 = 0;
            goto L10
        }

        public void set(int r4, TL_stars.TL_starGiftUnique r5) {
            this.id = r5.id;
            boolean r1 = true;
            if ((r4 % 3) == 1) goto L7;
            r1 = false;
        L7:
            if (r1 == false) goto L9;
            int r2 = AndroidUtilities.dp(4.0f);
        L10:
            if (r1 == false) goto L12;
            int r42 = AndroidUtilities.dp(4.0f);
        L13:
            setPadding(r2, 0, r42, 0);
            setSticker(r5.getDocument(), r5);
            this.backdrop = (TL_stars.starGiftAttributeBackdrop) StarsController.findAttribute(r5.attributes, TL_stars.starGiftAttributeBackdrop.class);
            this.pattern = (TL_stars.starGiftAttributePattern) StarsController.findAttribute(r5.attributes, TL_stars.starGiftAttributePattern.class);
            this.cardBackground.setBackdrop(this.backdrop);
            this.cardBackground.setPattern(this.pattern);
            return;
        L12:
            r42 = 0;
            goto L13
        L9:
            r2 = 0;
            goto L10
        }

        public void setSelected(boolean r2, boolean r3) {
            this.cardBackground.setSelected(r2, r3);
            if (r2 == false) goto L5;
            float r22 = 0.9f;
        L6:
            BackupImageView r0 = this.imageView;
            if (r3 == false) goto L10;
            r0.animate().scaleX(r22).scaleY(r22).start();
            return;
        L10:
            r0.animate().cancel();
            this.imageView.setScaleX(r22);
            this.imageView.setScaleY(r22);
            return;
        L5:
            r22 = 1.0f;
            goto L6
        }
    }

    public static class LevelLock extends Drawable {
        private int foregroundColor;
        private final PremiumGradient.PremiumGradientTools gradientTools;
        private final Drawable lock;
        private final float lockScale;
        private final Theme.ResourcesProvider resourcesProvider;
        private final Text text;

        public LevelLock(Context r2, int r3, Theme.ResourcesProvider r4) {
            this(r2, false, r3, r4);
        }

        public LevelLock(Context r8, boolean r9, int r10, Theme.ResourcesProvider r11) {
            this.lockScale = 0.875f;
            this.foregroundColor = Integer.MIN_VALUE;
            this.resourcesProvider = r11;
            if (r9 == false) goto L5;
            String r92 = "BoostLevelPlus";
        L6:
            this.text = new Text(LocaleController.formatPluralString(r92, r10, new Object[0]), 12.0f, AndroidUtilities.bold());
            this.lock = r8.getResources().getDrawable(R.drawable.mini_switch_lock).mutate();
            this.gradientTools = new PremiumGradient.PremiumGradientTools(Theme.key_premiumGradient1, Theme.key_premiumGradient2, -1, -1, -1, r11);
            return;
        L5:
            r92 = "BoostLevel";
            goto L6
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r11) {
            int r0 = getBounds().left;
            int r1 = getBounds().centerY();
            RectF r2 = AndroidUtilities.rectTmp;
            float r7 = r1;
            r2.set(r0, r7 - (getIntrinsicHeight() / 2.0f), getIntrinsicWidth() + r0, (getIntrinsicHeight() / 2.0f) + r7);
            this.gradientTools.gradientMatrix(r2);
            r11.drawRoundRect(r2, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), this.gradientTools.paint);
            this.lock.setBounds(AndroidUtilities.dp(3.33f) + r0, (int) (r7 - ((this.lock.getIntrinsicHeight() * 0.875f) / 2.0f)), (int) ((AndroidUtilities.dp(3.33f) + r0) + (this.lock.getIntrinsicWidth() * 0.875f)), (int) (((this.lock.getIntrinsicHeight() * 0.875f) / 2.0f) + r7));
            if (Theme.isCurrentThemeMonet(this.resourcesProvider) == false) goto L6;
            int r12 = Theme.getColor(Theme.key_chats_actionIcon, this.resourcesProvider);
        L5:
            int r8 = r12;
            if (this.foregroundColor == r8) goto L10;
            this.foregroundColor = r8;
            this.lock.setColorFilter(new PorterDuffColorFilter(r8, PorterDuff.Mode.SRC_IN));
        L10:
            this.lock.draw(r11);
            this.text.draw(r11, (r0 + AndroidUtilities.dp(3.66f)) + (this.lock.getIntrinsicWidth() * 0.875f), r7, r8, 1.0f);
            return;
        L6:
            r12 = -1;
            goto L5
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return AndroidUtilities.dp(18.33f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return (int) ((AndroidUtilities.dp(9.66f) + (this.lock.getIntrinsicWidth() * 0.875f)) + this.text.getWidth());
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    public class Page extends FrameLayout {
        private int actionBarHeight;
        private CharSequence buttonCollectible;
        private CharSequence buttonLocked;
        int buttonRow;
        private final ButtonState buttonState;
        private CharSequence buttonUnlocked;
        int clearRow;
        int colorPickerRow;
        private boolean drawGiftTabsInList;
        private boolean giftTabsPinned;
        private View giftTabsPlaceholder;
        private Tabs giftTabsView;
        int giftsCount;
        int giftsEmptyRow;
        int giftsEmptyRowGap;
        int giftsEndRow;
        int giftsHeaderRow;
        int giftsInfoRow;
        int giftsLoadingEndRow;
        int giftsLoadingStartRow;
        int giftsStartRow;
        int giftsTabsRow;
        int iconRow;
        private final HashMap<Integer, TL_stars.StarGift> index2gift;
        int info2Row;
        int infoRow;
        private GridLayoutManager layoutManager;
        private RecyclerView.Adapter listAdapter;
        private RecyclerListView listView;
        private View messagesCellFade;
        private View messagesCellFade2;
        private ThemePreviewMessagesCell messagesCellPreview;
        private PeerColorGrid peerColorPicker;
        private ProfilePreview profilePreview;
        private ResaleGiftsFragment.ResaleGiftsList resaleGifts;
        int rowCount;
        private SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow selectAnimatedEmojiDialog;
        private int selectedColor;
        private long selectedEmoji;
        private TLRPC.TL_emojiStatusCollectible selectedEmojiCollectible;
        private TLRPC.TL_peerColorCollectible selectedPeerCollectible;
        private TL_stars.TL_starGiftUnique selectedResaleGift;
        private TL_stars.StarGift selectedTabGift;
        private SetReplyIconCell setReplyIconCell;
        int shadowRow;
        private final ArrayList<CharSequence> tabs;
        final /* synthetic */ PeerColorActivity this$0;
        private final int type;
        final ArrayList<TL_stars.TL_starGiftUnique> uniqueGifts;

        /* renamed from: org.telegram.ui.PeerColorActivity$Page$1, reason: invalid class name */
        public class AnonymousClass1 extends RecyclerListView {
            final /* synthetic */ Page this$1;
            final /* synthetic */ PeerColorActivity val$this$0;
            final /* synthetic */ int val$type;

            public AnonymousClass1(Page r1, Context r2, Theme.ResourcesProvider r3, PeerColorActivity r4, int r5) {
                this.this$1 = r1;
                this.val$this$0 = r4;
                this.val$type = r5;
                super(r2, r3);
            }

            @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r4) {
                super.dispatchDraw(r4);
                if (Page.m16711$$Nest$fgetdrawGiftTabsInList(this.this$1) == true) goto L5;
                return;
            L5:
                if (Page.m16713$$Nest$fgetgiftTabsView(this.this$1) != null) goto L7;
                return;
            L7:
                if (Page.m16712$$Nest$fgetgiftTabsPlaceholder(this.this$1) == null) goto L12;
                int r0 = r4.save();
                r4.translate(Page.m16712$$Nest$fgetgiftTabsPlaceholder(this.this$1).getLeft() + Page.m16713$$Nest$fgetgiftTabsView(this.this$1).getLeft(), Page.m16712$$Nest$fgetgiftTabsPlaceholder(this.this$1).getTop());
                Page.m16713$$Nest$fgetgiftTabsView(this.this$1).draw(r4);
                r4.restoreToCount(r0);
                return;
            }

            @Override // org.telegram.ui.Components.RecyclerListView
            public Integer getSelectorColor(int r3) {
                Page r0 = this.this$1;
                if (r3 < r0.giftsStartRow) goto L7;
                if (r3 >= r0.giftsEndRow) goto L7;
            L11:
                return 0;
            L7:
                if (r3 < r0.giftsLoadingStartRow) goto L13;
                if (r3 < r0.giftsLoadingEndRow) goto L11;
            L13:
                return super.getSelectorColor(r3);
            }

            @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
            public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
                super.onLayout(r1, r2, r3, r4, r5);
                Page.m16742$$Nest$mupdateGiftTabsPosition(this.this$1);
                if (Page.m16726$$Nest$fgetselectedTabGift(this.this$1) != null) goto L5;
                int r12 = this.val$type;
                Page r22 = this.this$1;
                if (r12 != 1) goto L13;
                StarsController.GiftsList r13 = PeerColorActivity.m16682$$Nest$fgetgiftsWithPeerColor(r22.this$0);
            L14:
                if (r13 != null) goto L16;
                return;
            L16:
                if (this.this$1.seesLoading() == false) goto L22;
                r13.load();
                return;
            L22:
                return;
            L13:
                r13 = PeerColorActivity.m16681$$Nest$fgetgifts(r22.this$0);
                goto L14
            L5:
                if (Page.m16720$$Nest$fgetresaleGifts(this.this$1) != null) goto L7;
                return;
            L7:
                if (this.this$1.seesLoading() == false) goto L20;
                Page.m16720$$Nest$fgetresaleGifts(this.this$1).load();
                return;
            }
        }

        /* renamed from: org.telegram.ui.PeerColorActivity$Page$2, reason: invalid class name */
        public class AnonymousClass2 extends GridLayoutManager.SpanSizeLookup {
            final /* synthetic */ Page this$1;
            final /* synthetic */ PeerColorActivity val$this$0;

            public AnonymousClass2(Page r1, PeerColorActivity r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int r3) {
                Page r2 = this.this$1;
                if (r3 < r2.giftsStartRow) goto L8;
                if (r3 >= r2.giftsEndRow) goto L8;
                return 1;
            L8:
                if (r3 >= r2.giftsLoadingStartRow) goto L10;
                return 3;
            L10:
                if (r3 >= r2.giftsLoadingEndRow) goto L14;
                return 1;
            L14:
                return 3;
            }
        }

        /* renamed from: org.telegram.ui.PeerColorActivity$Page$3, reason: invalid class name */
        public class AnonymousClass3 extends RecyclerView.ItemDecoration {
            final /* synthetic */ Page this$1;
            final /* synthetic */ PeerColorActivity val$this$0;

            public AnonymousClass3(Page r1, PeerColorActivity r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect r4, View r5, RecyclerView r6, RecyclerView.State r7) {
                int r52 = r6.getChildAdapterPosition(r5);
                Page r3 = this.this$1;
                int r62 = r3.giftsStartRow;
                if (r52 < r62) goto L40;
                int r32 = r3.giftsCount;
                if (r52 >= (r62 + r32)) goto L41;
                int r53 = r52 - r62;
                int r63 = r53 / 3;
                boolean r72 = true;
                int r0 = 0;
                if (r63 != 0) goto L10;
                boolean r1 = true;
            L12:
                if (r63 != ((r32 - 1) / 3)) goto L14;
                boolean r33 = true;
            L15:
                int r54 = r53 % 3;
                if (r54 != 0) goto L18;
                boolean r64 = true;
            L20:
                if (r54 == 2) goto L24;
                r72 = false;
            L24:
                if (r1 == false) goto L26;
                int r12 = AndroidUtilities.dp(8.0f);
            L27:
                r4.top = r12;
                if (r33 == false) goto L30;
                int r34 = AndroidUtilities.dp(8.0f);
            L31:
                r4.bottom = r34;
                if (r64 == false) goto L34;
                int r55 = AndroidUtilities.dp(10.0f);
            L35:
                r4.left = r55;
                if (r72 == false) goto L38;
                r0 = AndroidUtilities.dp(10.0f);
            L38:
                r4.right = r0;
                return;
            L34:
                r55 = 0;
                goto L35
            L30:
                r34 = 0;
                goto L31
            L26:
                r12 = 0;
                goto L27
            L18:
                r64 = false;
                goto L20
            L14:
                r33 = false;
                goto L15
            L10:
                r1 = false;
                goto L12
            L41:
                return;
            }
        }

        /* renamed from: org.telegram.ui.PeerColorActivity$Page$4, reason: invalid class name */
        public class AnonymousClass4 extends RecyclerListView.SelectionAdapter {
            final /* synthetic */ Page this$1;
            final /* synthetic */ Context val$context;
            final /* synthetic */ PeerColorActivity val$this$0;
            final /* synthetic */ int val$type;

            /* renamed from: org.telegram.ui.PeerColorActivity$Page$4$1, reason: invalid class name */
            public class AnonymousClass1 extends View {
                final /* synthetic */ AnonymousClass4 this$2;

                public AnonymousClass1(AnonymousClass4 r1, Context r2) {
                    this.this$2 = r1;
                    super(r2);
                }

                @Override // android.view.View
                public void onMeasure(int r2, int r3) {
                    super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(12.0f), TLObject.FLAG_30));
                }
            }

            /* renamed from: org.telegram.ui.PeerColorActivity$Page$4$2, reason: invalid class name */
            public class AnonymousClass2 extends View {
                final /* synthetic */ AnonymousClass4 this$2;

                public AnonymousClass2(AnonymousClass4 r1, Context r2) {
                    this.this$2 = r1;
                    super(r2);
                }

                @Override // android.view.View
                public void onMeasure(int r1, int r2) {
                    setMeasuredDimension(View.MeasureSpec.getSize(r1), AndroidUtilities.dp(36.0f));
                }
            }

            public static /* synthetic */ void $r8$lambda$0ZMbueDzZJMmZC9KCWZj2ZtNgTM(AnonymousClass4 r0, Integer r1) {
                r0.lambda$onCreateViewHolder$0(r1);
            }

            /* renamed from: $r8$lambda$4penw0vaZiCEPOTNkY81onnHm-Y, reason: not valid java name */
            public static /* synthetic */ void m16745$r8$lambda$4penw0vaZiCEPOTNkY81onnHmY(AnonymousClass4 r0, Integer r1) {
                r0.lambda$onBindViewHolder$3(r1);
            }

            /* renamed from: $r8$lambda$AX2MbdS2pksx4-MTsw_A9ukH1OY, reason: not valid java name */
            public static /* synthetic */ void m16746$r8$lambda$AX2MbdS2pksx4MTsw_A9ukH1OY(AnonymousClass4 r0, Boolean r1) {
                r0.lambda$onBindViewHolder$2(r1);
            }

            /* renamed from: $r8$lambda$LMasEbJCFd78EB2-Em6wg4HYYu0, reason: not valid java name */
            public static /* synthetic */ void m16747$r8$lambda$LMasEbJCFd78EB2Em6wg4HYYu0(Page r0) {
                Page.m16742$$Nest$mupdateGiftTabsPosition(r0);
            }

            public static /* synthetic */ void $r8$lambda$Nnpak0_LGsP_GVXNf9Cj0AJBRI4(AnonymousClass4 r0, int r1) {
                r0.lambda$onBindViewHolder$1(r1);
            }

            public AnonymousClass4(Page r1, PeerColorActivity r2, Context r3, int r4) {
                this.this$1 = r1;
                this.val$this$0 = r2;
                this.val$context = r3;
                this.val$type = r4;
            }

            private /* synthetic */ void lambda$onBindViewHolder$1(int r1) {
                PeerColorActivity.m16693$$Nest$fgetviewPager(this.this$1.this$0).scrollToPosition(1 - r1);
            }

            private /* synthetic */ void lambda$onBindViewHolder$2(Boolean r1) {
                this.this$1.update();
            }

            private /* synthetic */ void lambda$onBindViewHolder$3(Integer r6) {
                Page r0 = this.this$1;
                if (r6.intValue() != 0) goto L5;
                TL_stars.StarGift r62 = null;
            L6:
                Page.m16739$$Nest$fputselectedTabGift(r0, r62);
                TL_stars.StarGift r63 = Page.m16726$$Nest$fgetselectedTabGift(this.this$1);
                Page r02 = this.this$1;
                if (r63 != null) goto L12;
                if (Page.m16720$$Nest$fgetresaleGifts(r02) == null) goto L16;
                Page.m16720$$Nest$fgetresaleGifts(this.this$1).cancel();
                Page.m16732$$Nest$fputresaleGifts(this.this$1, null);
            L16:
                Page.m16741$$Nest$mupdateAfterGiftTabSelected(this.this$1);
                int r64 = PeerColorActivity.m16693$$Nest$fgetviewPager(this.this$1.this$0).getCurrentPosition();
                PeerColorActivity r5 = this.this$1.this$0;
                if (r64 != 1) goto L19;
                Page r52 = r5.profilePage;
            L20:
                r52.update();
                return;
            L19:
                r52 = r5.namePage;
                goto L20
            L12:
                if (Page.m16720$$Nest$fgetresaleGifts(r02) != null) goto L14;
            L15:
                Page r65 = this.this$1;
                Page.m16732$$Nest$fputresaleGifts(r65, new ResaleGiftsFragment.ResaleGiftsList(PeerColorActivity.access$800(r65.this$0), Page.m16726$$Nest$fgetselectedTabGift(this.this$1).id, new PeerColorActivity$Page$4$$ExternalSyntheticLambda4(this)));
                Page.m16720$$Nest$fgetresaleGifts(this.this$1).load();
                goto L16
            L14:
                if (Page.m16720$$Nest$fgetresaleGifts(this.this$1).gift_id == Page.m16726$$Nest$fgetselectedTabGift(this.this$1).id) goto L16;
            L5:
                r62 = (TL_stars.StarGift) Page.m16714$$Nest$fgetindex2gift(this.this$1).get(r6);
                goto L6
            }

            private /* synthetic */ void lambda$onCreateViewHolder$0(Integer r2) {
                Page.m16734$$Nest$fputselectedColor(this.this$1, r2.intValue());
                Page.m16736$$Nest$fputselectedEmojiCollectible(this.this$1, null);
                Page.m16737$$Nest$fputselectedPeerCollectible(this.this$1, null);
                Page.m16738$$Nest$fputselectedResaleGift(this.this$1, null);
                this.this$1.updateProfilePreview(true);
                Page.m16743$$Nest$mupdateMessages(this.this$1);
                this.this$1.updateButton(true);
                if (Page.m16727$$Nest$fgetsetReplyIconCell(this.this$1) == null) goto L5;
                Page.m16727$$Nest$fgetsetReplyIconCell(this.this$1).invalidate();
            L5:
                Page r22 = this.this$1.this$0.profilePage;
                if (r22 != null) goto L8;
                return;
            L8:
                if (Page.m16719$$Nest$fgetprofilePreview(r22) == null) goto L14;
                PeerColorActivity r23 = this.this$1.this$0;
                if (r23.namePage == null) goto L15;
                Page.m16719$$Nest$fgetprofilePreview(r23.profilePage).overrideAvatarColor(Page.m16721$$Nest$fgetselectedColor(this.this$1.this$0.namePage));
                return;
            L15:
                return;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                return this.this$1.rowCount;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemViewType(int r5) {
                Page r0 = this.this$1;
                if (r5 != r0.infoRow) goto L5;
            L62:
                return 2;
            L5:
                if (r5 == r0.giftsInfoRow) goto L62;
                if (r5 == r0.info2Row) goto L62;
                if (r5 == r0.shadowRow) goto L62;
                if (r5 != r0.colorPickerRow) goto L15;
                return 1;
            L15:
                if (r5 != r0.iconRow) goto L19;
                return 3;
            L19:
                if (r5 != r0.buttonRow) goto L23;
                return 5;
            L23:
                if (r5 != r0.clearRow) goto L27;
                return 6;
            L27:
                if (r5 != r0.giftsTabsRow) goto L31;
                return 10;
            L31:
                if (r5 != r0.giftsEmptyRow) goto L35;
                return 11;
            L35:
                if (r5 != r0.giftsHeaderRow) goto L39;
                return 7;
            L39:
                if (r5 < r0.giftsStartRow) goto L49;
                if (r5 >= r0.giftsEndRow) goto L49;
                if (Page.m16726$$Nest$fgetselectedTabGift(r0) != null) goto L46;
                return 8;
            L46:
                return 12;
            L49:
                if (r5 < r0.giftsLoadingStartRow) goto L55;
                if (r5 >= r0.giftsLoadingEndRow) goto L55;
                return 9;
            L55:
                if (r5 != (getItemCount() - 1)) goto L57;
                return 4;
            L57:
                if (r5 == this.this$1.giftsEmptyRowGap) goto L63;
                return 2;
            L63:
                return 4;
            }

            @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
            public boolean isEnabled(RecyclerView.ViewHolder r2) {
                if (r2.getItemViewType() != 3) goto L5;
                return true;
            L5:
                if (r2.getItemViewType() != 6) goto L7;
                return true;
            L7:
                if (r2.getItemViewType() != 8) goto L9;
                return true;
            L9:
                if (r2.getItemViewType() == 12) goto L17;
                return false;
            L17:
                return true;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(RecyclerView.ViewHolder r12, int r13) {
                boolean r1 = true;
                switch(getItemViewType(r13)) {
                    case 1: goto L99;
                    case 2: goto L75;
                    case 3: goto L73;
                    case 4: goto L110;
                    case 5: goto L110;
                    case 6: goto L65;
                    case 7: goto L60;
                    case 8: goto L43;
                    case 9: goto L110;
                    case 10: goto L26;
                    case 11: goto L24;
                    case 12: goto L5;
                    default: goto L110;
                };
            L5:
                GiftSheet.GiftCell r3 = (GiftSheet.GiftCell) r12.itemView;
                Page r122 = this.this$1;
                int r132 = r13 - r122.giftsStartRow;
                if (Page.m16720$$Nest$fgetresaleGifts(r122) == null) goto L111;
                if (r132 >= 0) goto L10;
                return;
            L10:
                if (r132 >= this.this$1.uniqueGifts.size()) goto L112;
                TL_stars.TL_starGiftUnique r4 = this.this$1.uniqueGifts.get(r132);
                r3.setStarsGift(r4, false, false, false, true, false);
                if (Page.m16723$$Nest$fgetselectedEmojiCollectible(this.this$1) == null) goto L17;
                if (Page.m16723$$Nest$fgetselectedEmojiCollectible(this.this$1).collectible_id != r4.id) goto L17;
            L22:
                r3.setSelected(r1, false);
                return;
            L17:
                if (Page.m16724$$Nest$fgetselectedPeerCollectible(this.this$1) != null) goto L19;
            L21:
                r1 = false;
                goto L22
            L19:
                if (Page.m16724$$Nest$fgetselectedPeerCollectible(this.this$1).collectible_id != r4.id) goto L21;
            L112:
                return;
            L111:
                return;
            L24:
                ((EmptyView) r12.itemView).updateColors();
                return;
            L26:
                Page.m16730$$Nest$fputgiftTabsPlaceholder(this.this$1, r12.itemView);
                Tabs r133 = Page.m16713$$Nest$fgetgiftTabsView(this.this$1);
                Page.m16728$$Nest$fgettabs(this.this$1).clear();
                Page.m16714$$Nest$fgetindex2gift(this.this$1).clear();
                ArrayList<TL_stars.StarGift> r0 = StarsController.getInstance(PeerColorActivity.access$700(this.this$1.this$0)).sortedGifts;
                Page.m16728$$Nest$fgettabs(this.this$1).add(LocaleController.getString(R.string.Gift2TabMine));
                int r32 = 0;
                int r42 = 0;
            L28:
                if (r32 >= r0.size()) goto L41;
                TL_stars.StarGift r5 = r0.get(r32);
                int r6 = this.val$type;
                if (r6 == 0) goto L35;
                if (r6 != 1) goto L40;
                if (r5.peer_color_available == true) goto L35;
            L40:
                r32 = r32 + 1;
            L35:
                if (r5.availability_resale <= 0) goto L40;
                if (Page.m16726$$Nest$fgetselectedTabGift(this.this$1) != r5) goto L39;
                r42 = Page.m16728$$Nest$fgettabs(this.this$1).size();
            L39:
                Page.m16714$$Nest$fgetindex2gift(this.this$1).put(Integer.valueOf(Page.m16728$$Nest$fgettabs(this.this$1).size()), r5);
                TextPaint r62 = new TextPaint(1);
                r62.setTextSize(AndroidUtilities.dp(14.0f));
                SpannableStringBuilder r8 = new SpannableStringBuilder("x ");
                AnimatedEmojiSpan r9 = new AnimatedEmojiSpan(r5.getDocument(), r62.getFontMetricsInt());
                r9.size = AndroidUtilities.dp(14.0f);
                r8.setSpan(r9, 0, 1, 33);
                r8.append(r5.title);
                Page.m16728$$Nest$fgettabs(this.this$1).add(r8);
                goto L40
            L41:
                r133.set(0, Page.m16728$$Nest$fgettabs(this.this$1), r42, new PeerColorActivity$Page$4$$ExternalSyntheticLambda2(this));
                Page.m16744$$Nest$mupdateTabsColors(this.this$1, r133);
                r12.itemView.post(new PeerColorActivity$Page$4$$ExternalSyntheticLambda0(this.this$1));
                return;
            L43:
                GiftCell r123 = (GiftCell) r12.itemView;
                Page r02 = this.this$1;
                int r134 = r13 - r02.giftsStartRow;
                if (r134 >= 0) goto L46;
                return;
            L46:
                if (r134 >= r02.uniqueGifts.size()) goto L113;
                TL_stars.TL_starGiftUnique r03 = this.this$1.uniqueGifts.get(r134);
                r123.set(r134, r03);
                if (Page.m16723$$Nest$fgetselectedEmojiCollectible(this.this$1) == null) goto L53;
                if (Page.m16723$$Nest$fgetselectedEmojiCollectible(this.this$1).collectible_id != r03.id) goto L53;
            L58:
                r123.setSelected(r1, false);
                r123.card.invalidate();
                return;
            L53:
                if (Page.m16724$$Nest$fgetselectedPeerCollectible(this.this$1) != null) goto L55;
            L57:
                r1 = false;
                goto L58
            L55:
                if (Page.m16724$$Nest$fgetselectedPeerCollectible(this.this$1).collectible_id != r03.id) goto L57;
            L113:
                return;
            L60:
                HeaderCell r124 = (HeaderCell) r12.itemView;
                if (r13 != this.this$1.giftsHeaderRow) goto L63;
                r124.setText(LocaleController.getString(R.string.UserProfileCollectibleHeader), false);
            L63:
                r124.setBackgroundColor(this.this$1.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
                return;
            L65:
                TextCell r125 = (TextCell) r12.itemView;
                r125.updateColors();
                r125.setBackgroundColor(this.this$1.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
                r125.updateColors();
                Page r11 = this.this$1;
                if (r13 == r11.clearRow) goto L68;
                return;
            L68:
                if (PeerColorActivity.m16685$$Nest$fgetisChannel(r11.this$0) == false) goto L70;
                int r112 = R.string.ChannelProfileColorReset;
            L71:
                r125.setText(LocaleController.getString(r112), false);
                return;
            L70:
                r112 = R.string.UserProfileColorReset;
                goto L71
            L73:
                ((SetReplyIconCell) r12.itemView).updateColors();
                return;
            L75:
                TextInfoPrivacyCell r126 = (TextInfoPrivacyCell) r12.itemView;
                r126.setFixedSize(0);
                Page r04 = this.this$1;
                if (r13 != r04.infoRow) goto L92;
                if (this.val$type != 1) goto L85;
                if (PeerColorActivity.m16685$$Nest$fgetisChannel(r04.this$0) == false) goto L82;
                int r135 = R.string.ChannelColorHint;
            L83:
                String r136 = LocaleController.getString(r135);
            L89:
                r126.setText(AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(r136, new PeerColorActivity$Page$4$$ExternalSyntheticLambda1(this, this.val$type)), true));
                return;
            L82:
                r135 = R.string.UserColorHint;
                goto L83
            L85:
                if (PeerColorActivity.m16685$$Nest$fgetisChannel(r04.this$0) == false) goto L87;
                int r137 = R.string.ChannelProfileHint;
            L88:
                r136 = LocaleController.getString(r137);
                goto L89
            L87:
                r137 = R.string.UserProfileHint2;
                goto L88
            L92:
                if (r13 != r04.shadowRow) goto L96;
                r126.setText(_UrlKt.FRAGMENT_ENCODE_SET);
                r126.setFixedSize(12);
                return;
            L96:
                if (r13 != r04.giftsInfoRow) goto L109;
                r126.setText(LocaleController.getString(R.string.UserProfileCollectibleInfo));
                return;
            L109:
                return;
            L99:
                r12.itemView.setBackgroundColor(this.this$1.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
                ((PeerColorGrid) r12.itemView).updateColors();
                return;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r6, int r7) {
                switch(r7) {
                    case 1: goto L16;
                    case 2: goto L4;
                    case 3: goto L15;
                    case 4: goto L14;
                    case 5: goto L13;
                    case 6: goto L12;
                    case 7: goto L11;
                    case 8: goto L10;
                    case 9: goto L9;
                    case 10: goto L8;
                    case 11: goto L7;
                    case 12: goto L5;
                    default: goto L4;
                };
            L4:
                View r62 = new TextInfoPrivacyCell(this.this$1.getContext(), this.this$1.this$0.getResourceProvider());
            L18:
                return new RecyclerListView.Holder(r62);
            L5:
                View r72 = new GiftSheet.GiftCell(this.this$1.getContext(), PeerColorActivity.access$200(this.this$1.this$0), PeerColorActivity.access$300(this.this$1.this$0));
                r72.setTag(-33024);
                View r73 = r72;
            L6:
                r62 = r73;
                goto L18
            L7:
                Page r5 = this.this$1;
                r62 = new EmptyView(r5, r5.getContext());
                goto L18
            L8:
                View r74 = new AnonymousClass2(this, this.this$1.getContext());
                r74.setTag(-33024);
                r73 = r74;
                goto L6
            L9:
                FlickerLoadingView r75 = new FlickerLoadingView(this.val$context, PeerColorActivity.access$400(this.this$1.this$0));
                r75.setIsSingleCell(true);
                r75.setViewType(35);
                r75.setTag(-33024);
                r73 = r75;
                goto L6
            L10:
                View r76 = new GiftCell(this.this$1.getContext(), false, PeerColorActivity.access$100(this.this$1.this$0));
                r76.setTag(-33024);
                r73 = r76;
                goto L6
            L11:
                r62 = new HeaderCell(this.this$1.getContext(), PeerColorActivity.access$000(this.this$1.this$0));
                r62.setBackgroundColor(this.this$1.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
                goto L18
            L12:
                r62 = new TextCell(this.this$1.getContext(), this.this$1.this$0.getResourceProvider());
                r62.setBackgroundColor(this.this$1.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
                goto L18
            L13:
                View r77 = new View(this.this$1.getContext());
                r77.setTag(-33024);
                r73 = r77;
                goto L6
            L14:
                View r78 = new AnonymousClass1(this, this.this$1.getContext());
                r78.setTag(-33024);
                r73 = r78;
                goto L6
            L15:
                Page r63 = this.this$1;
                Page r52 = this.this$1;
                SetReplyIconCell r79 = new SetReplyIconCell(r52, r52.getContext());
                Page.m16740$$Nest$fputsetReplyIconCell(r63, r79);
                r79.update(false);
                r73 = r79;
                goto L6
            L16:
                Page r64 = this.this$1;
                PeerColorGrid r710 = new PeerColorGrid(this.this$1.getContext(), this.val$type, PeerColorActivity.access$500(this.this$1.this$0), PeerColorActivity.access$600(this.this$1.this$0));
                Page.m16731$$Nest$fputpeerColorPicker(r64, r710);
                r710.setBackgroundColor(this.this$1.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
                r710.setSelected(Page.m16721$$Nest$fgetselectedColor(this.this$1), false);
                r710.setOnColorClick(new PeerColorActivity$Page$4$$ExternalSyntheticLambda3(this));
                r73 = r710;
                goto L6
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onViewAttachedToWindow(RecyclerView.ViewHolder r12) {
                super.onViewAttachedToWindow(r12);
                if (r12.getItemViewType() != 10) goto L6;
                Page.m16730$$Nest$fputgiftTabsPlaceholder(this.this$1, r12.itemView);
                r12.itemView.post(new PeerColorActivity$Page$4$$ExternalSyntheticLambda0(this.this$1));
                return;
            L6:
                boolean r2 = true;
                if (r12.getItemViewType() != 8) goto L26;
                GiftCell r0 = (GiftCell) r12.itemView;
                int r122 = r12.getAdapterPosition();
                Page r1 = this.this$1;
                int r123 = r122 - r1.giftsStartRow;
                if (r123 >= 0) goto L11;
                return;
            L11:
                if (r123 >= r1.uniqueGifts.size()) goto L49;
                TL_stars.TL_starGiftUnique r13 = this.this$1.uniqueGifts.get(r123);
                r0.set(r123, r13);
                if (Page.m16723$$Nest$fgetselectedEmojiCollectible(this.this$1) == null) goto L18;
                if (Page.m16723$$Nest$fgetselectedEmojiCollectible(this.this$1).collectible_id != r13.id) goto L18;
            L23:
                r0.setSelected(r2, false);
                return;
            L18:
                if (Page.m16724$$Nest$fgetselectedPeerCollectible(this.this$1) != null) goto L20;
            L22:
                r2 = false;
                goto L23
            L20:
                if (Page.m16724$$Nest$fgetselectedPeerCollectible(this.this$1).collectible_id != r13.id) goto L22;
            L49:
                return;
            L26:
                if (r12.getItemViewType() != 12) goto L47;
                GiftSheet.GiftCell r4 = (GiftSheet.GiftCell) r12.itemView;
                int r124 = r12.getAdapterPosition();
                Page r02 = this.this$1;
                int r125 = r124 - r02.giftsStartRow;
                if (Page.m16720$$Nest$fgetresaleGifts(r02) == null) goto L50;
                if (r125 >= 0) goto L32;
                return;
            L32:
                if (r125 >= this.this$1.uniqueGifts.size()) goto L51;
                TL_stars.TL_starGiftUnique r5 = this.this$1.uniqueGifts.get(r125);
                r4.setStarsGift(r5, false, false, false, true, false);
                if (Page.m16723$$Nest$fgetselectedEmojiCollectible(this.this$1) == null) goto L39;
                if (Page.m16723$$Nest$fgetselectedEmojiCollectible(this.this$1).collectible_id != r5.id) goto L39;
            L44:
                r4.setSelected(r2, false);
                return;
            L39:
                if (Page.m16724$$Nest$fgetselectedPeerCollectible(this.this$1) != null) goto L41;
            L43:
                r2 = false;
                goto L44
            L41:
                if (Page.m16724$$Nest$fgetselectedPeerCollectible(this.this$1).collectible_id != r5.id) goto L43;
            L51:
                return;
            L50:
                return;
            }
        }

        /* renamed from: org.telegram.ui.PeerColorActivity$Page$5, reason: invalid class name */
        public class AnonymousClass5 extends RecyclerView.OnScrollListener {
            final /* synthetic */ Page this$1;
            final /* synthetic */ PeerColorActivity val$this$0;
            final /* synthetic */ int val$type;

            public AnonymousClass5(Page r1, PeerColorActivity r2, int r3) {
                this.this$1 = r1;
                this.val$this$0 = r2;
                this.val$type = r3;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView r3, int r4, int r5) {
                super.onScrolled(r3, r4, r5);
                if (r5 == 0) goto L6;
                PeerColorActivity.m16701$$Nest$minvalidateMergedVisibleBlurredPositionsAndSources(this.this$1.this$0, 1);
            L6:
                if (Build.VERSION.SDK_INT >= 31) goto L8;
            L10:
                Page.m16742$$Nest$mupdateGiftTabsPosition(this.this$1);
                if (Page.m16726$$Nest$fgetselectedTabGift(this.this$1) != null) goto L13;
                int r42 = this.val$type;
                Page r52 = this.this$1;
                if (r42 != 1) goto L21;
                StarsController.GiftsList r32 = PeerColorActivity.m16682$$Nest$fgetgiftsWithPeerColor(r52.this$0);
            L22:
                if (r32 != null) goto L24;
                return;
            L24:
                if (this.this$1.seesLoading() == false) goto L30;
                r32.load();
                return;
            L30:
                return;
            L21:
                r32 = PeerColorActivity.m16681$$Nest$fgetgifts(r52.this$0);
                goto L22
            L13:
                if (Page.m16720$$Nest$fgetresaleGifts(this.this$1) != null) goto L15;
                return;
            L15:
                if (this.this$1.seesLoading() == false) goto L28;
                Page.m16720$$Nest$fgetresaleGifts(this.this$1).load();
                return;
            L28:
                return;
            L8:
                if (PeerColorActivity.m16690$$Nest$fgetscrollableViewNoiseSuppressor(this.this$1.this$0) == null) goto L10;
                PeerColorActivity.m16690$$Nest$fgetscrollableViewNoiseSuppressor(this.this$1.this$0).onScrolled(r4, r5);
                goto L10
            }
        }

        /* renamed from: org.telegram.ui.PeerColorActivity$Page$6, reason: invalid class name */
        public class AnonymousClass6 extends ThemePreviewMessagesCell {
            final /* synthetic */ Page this$1;
            final /* synthetic */ PeerColorActivity val$this$0;

            /* renamed from: $r8$lambda$OfE5KM-LshX_L_bGzUCVbEWu2h4, reason: not valid java name */
            public static /* synthetic */ void m16748$r8$lambda$OfE5KMLshX_L_bGzUCVbEWu2h4(AnonymousClass6 r0, Canvas r1) {
                r0.lambda$draw$0(r1);
            }

            public AnonymousClass6(Page r1, Context r2, INavigationLayout r3, int r4, long r5, Theme.ResourcesProvider r7, PeerColorActivity r8) {
                this.this$1 = r1;
                this.val$this$0 = r8;
                super(r2, r3, r4, r5, r7);
            }

            private /* synthetic */ void lambda$draw$0(Canvas r1) {
                super.draw(r1);
            }

            @Override // android.view.View
            public void draw(Canvas r2) {
                if (r2.isHardwareAccelerated() == false) goto L6;
                super.draw(r2);
                return;
            L6:
                ViewOutlineProviderImpl.drawViewWithOutline(r2, this, new PeerColorActivity$Page$6$$ExternalSyntheticLambda0(this));
            }
        }

        /* renamed from: org.telegram.ui.PeerColorActivity$Page$7, reason: invalid class name */
        public class AnonymousClass7 extends SelectAnimatedEmojiDialog {
            final /* synthetic */ Page this$1;
            final /* synthetic */ SetReplyIconCell val$cell;
            final /* synthetic */ SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[] val$popup;

            public AnonymousClass7(Page r1, BaseFragment r2, Context r3, boolean r4, Integer r5, int r6, boolean r7, Theme.ResourcesProvider r8, int r9, int r10, SetReplyIconCell r11, SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[] r12) {
                this.this$1 = r1;
                this.val$cell = r11;
                this.val$popup = r12;
                super(r2, r3, r4, r5, r6, r7, r8, r9, r10);
            }

            @Override // org.telegram.ui.SelectAnimatedEmojiDialog
            public float getScrimDrawableTranslationY() {
                return 0.0f;
            }

            @Override // org.telegram.ui.SelectAnimatedEmojiDialog
            public void onEmojiSelected(View r1, Long r2, TLRPC.Document r3, TL_stars.TL_starGiftUnique r4, Integer r5) {
                Page r12 = this.this$1;
                if (r4 != null) goto L5;
                if (r2 != null) goto L14;
                long r42 = 0;
            L15:
                Page.m16735$$Nest$fputselectedEmoji(r12, r42);
                Page.m16736$$Nest$fputselectedEmojiCollectible(this.this$1, null);
                Page.m16737$$Nest$fputselectedPeerCollectible(this.this$1, null);
                Page.m16738$$Nest$fputselectedResaleGift(this.this$1, null);
            L16:
                SetReplyIconCell r13 = this.val$cell;
                if (r13 == null) goto L19;
                r13.update(true);
            L19:
                this.this$1.updateProfilePreview(true);
                Page.m16743$$Nest$mupdateMessages(this.this$1);
                this.this$1.updateButton(true);
                if (this.val$popup[0] == null) goto L23;
                Page.m16733$$Nest$fputselectAnimatedEmojiDialog(this.this$1, null);
                this.val$popup[0].dismiss();
                return;
            L23:
                return;
            L14:
                r42 = r2.longValue();
                goto L15
            L5:
                if (Page.m16729$$Nest$fgettype(r12) != 0) goto L10;
                TLRPC.PeerColor r14 = r4.peer_color;
                if ((r14 instanceof TLRPC.TL_peerColorCollectible) == false) goto L24;
                Page.m16737$$Nest$fputselectedPeerCollectible(this.this$1, (TLRPC.TL_peerColorCollectible) r14);
                Page.m16736$$Nest$fputselectedEmojiCollectible(this.this$1, null);
            L11:
                Page.m16738$$Nest$fputselectedResaleGift(this.this$1, null);
                Page.m16734$$Nest$fputselectedColor(this.this$1, -1);
                goto L16
            L24:
                return;
            L10:
                Page.m16737$$Nest$fputselectedPeerCollectible(this.this$1, null);
                Page.m16736$$Nest$fputselectedEmojiCollectible(this.this$1, MessagesController.emojiStatusCollectibleFromGift(r4));
                goto L11
            }
        }

        /* renamed from: org.telegram.ui.PeerColorActivity$Page$8, reason: invalid class name */
        public class AnonymousClass8 extends SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow {
            final /* synthetic */ Page this$1;

            public AnonymousClass8(Page r1, View r2, int r3, int r4) {
                this.this$1 = r1;
                super(r2, r3, r4);
            }

            @Override // org.telegram.ui.SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow, android.widget.PopupWindow
            public void dismiss() {
                super.dismiss();
                Page.m16733$$Nest$fputselectAnimatedEmojiDialog(this.this$1, null);
            }
        }

        public class EmptyView extends LinearLayout {
            private final BackupImageView imageView;
            private final TextView subtitle;
            final /* synthetic */ Page this$1;
            private final TextView title;

            /* renamed from: $r8$lambda$1FjbG6q9Hu9ZdGtUCLmfxOUHZ-E, reason: not valid java name */
            public static /* synthetic */ void m16749$r8$lambda$1FjbG6q9Hu9ZdGtUCLmfxOUHZE(EmptyView r0, Boolean r1) {
                r0.lambda$new$0(r1);
            }

            public static /* synthetic */ void $r8$lambda$yV0j6NIbz6aqd31ZcBbWSF0s7Vo(EmptyView r0) {
                r0.lambda$new$1();
            }

            public EmptyView(Page r13, Context r14) {
                this.this$1 = r13;
                super(r14);
                setOrientation(1);
                setBackgroundColor(r13.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
                BackupImageView r0 = new BackupImageView(getContext());
                this.imageView = r0;
                r0.setImageDrawable(new RLottieDrawable(R.raw.utyan_draw, AndroidUtilities.dp(120.0f), AndroidUtilities.dp(120.0f)));
                addView(r0, LayoutHelper.createLinear(120, 120, 1, 0, 6, 0, 0));
                LinkSpanDrawable.LinksTextView r02 = TextHelper.makeLinkTextView(getContext(), 14.0f, Theme.key_windowBackgroundWhiteGrayText, false, PeerColorActivity.access$1500(r13.this$0));
                this.title = r02;
                r02.setGravity(17);
                if (Page.m16729$$Nest$fgettype(r13) != 0) goto L5;
                int r2 = R.string.Gift2PeerColorProfileEmptyTitle;
            L6:
                r02.setText(LocaleController.getString(r2));
                addView(r02, LayoutHelper.createLinear(-1, -2, 1, 64, 8, 64, 8));
                LinkSpanDrawable.LinksTextView r132 = TextHelper.makeLinkTextView(getContext(), 14.0f, Theme.key_chat_messageLinkIn, false, PeerColorActivity.access$1600(r13.this$0));
                this.subtitle = r132;
                r132.setGravity(17);
                r132.setText(AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.Gift2PeerColorEmptyButton), new PeerColorActivity$Page$EmptyView$$ExternalSyntheticLambda0(this)), true, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(1.33f), 1.0f));
                addView(r132, LayoutHelper.createLinear(-1, -2, 1, 32, 4, 32, 24));
                return;
            L5:
                r2 = R.string.Gift2PeerColorReplyEmptyTitle;
                goto L6
            }

            private /* synthetic */ void lambda$new$0(Boolean r1) {
                this.this$1.update();
            }

            private /* synthetic */ void lambda$new$1() {
                if (Page.m16713$$Nest$fgetgiftTabsView(this.this$1) != null) goto L5;
                return;
            L5:
                if (Page.m16728$$Nest$fgettabs(this.this$1).size() <= 1) goto L23;
                Page.m16713$$Nest$fgetgiftTabsView(this.this$1).setSelected(1, true);
                Page r0 = this.this$1;
                Page.m16739$$Nest$fputselectedTabGift(r0, (TL_stars.StarGift) Page.m16714$$Nest$fgetindex2gift(r0).get(1));
                TL_stars.StarGift r02 = Page.m16726$$Nest$fgetselectedTabGift(this.this$1);
                Page r2 = this.this$1;
                if (r02 != null) goto L12;
                if (Page.m16720$$Nest$fgetresaleGifts(r2) == null) goto L16;
                Page.m16720$$Nest$fgetresaleGifts(this.this$1).cancel();
                Page.m16732$$Nest$fputresaleGifts(this.this$1, null);
            L16:
                Page.m16741$$Nest$mupdateAfterGiftTabSelected(this.this$1);
                int r03 = PeerColorActivity.m16693$$Nest$fgetviewPager(this.this$1.this$0).getCurrentPosition();
                PeerColorActivity r7 = this.this$1.this$0;
                if (r03 != 1) goto L19;
                Page r72 = r7.profilePage;
            L20:
                r72.update();
                return;
            L19:
                r72 = r7.namePage;
                goto L20
            L12:
                if (Page.m16720$$Nest$fgetresaleGifts(r2) != null) goto L14;
            L15:
                Page r04 = this.this$1;
                Page.m16732$$Nest$fputresaleGifts(r04, new ResaleGiftsFragment.ResaleGiftsList(PeerColorActivity.access$1700(r04.this$0), Page.m16726$$Nest$fgetselectedTabGift(this.this$1).id, new PeerColorActivity$Page$EmptyView$$ExternalSyntheticLambda1(this)));
                Page.m16720$$Nest$fgetresaleGifts(this.this$1).load();
                goto L16
            L14:
                if (Page.m16720$$Nest$fgetresaleGifts(this.this$1).gift_id == Page.m16726$$Nest$fgetselectedTabGift(this.this$1).id) goto L16;
            }

            public void updateColors() {
                setBackgroundColor(this.this$1.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
                this.title.setTextColor(this.this$1.this$0.getThemedColor(Theme.key_windowBackgroundWhiteGrayText));
                TextView r0 = this.subtitle;
                PeerColorActivity r1 = this.this$1.this$0;
                int r2 = Theme.key_chat_messageLinkIn;
                r0.setTextColor(r1.getThemedColor(r2));
                this.subtitle.setLinkTextColor(this.this$1.this$0.getThemedColor(r2));
            }
        }

        public class SetReplyIconCell extends FrameLayout {
            private AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable imageDrawable;
            private Text offText;
            private TextView textView;
            final /* synthetic */ Page this$1;

            /* renamed from: -$$Nest$fgetimageDrawable, reason: not valid java name */
            public static /* bridge */ /* synthetic */ AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable m16750$$Nest$fgetimageDrawable(SetReplyIconCell r0) {
                return r0.imageDrawable;
            }

            public SetReplyIconCell(Page r8, Context r9) {
                this.this$1 = r8;
                super(r9);
                setBackgroundColor(r8.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
                TextView r0 = new TextView(r9);
                this.textView = r0;
                r0.setTextSize(1, 16.0f);
                this.textView.setTextColor(r8.this$0.getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
                int r92 = Page.m16729$$Nest$fgettype(r8);
                TextView r02 = this.textView;
                if (r92 != 1) goto L10;
                if (PeerColorActivity.m16685$$Nest$fgetisChannel(r8.this$0) == false) goto L7;
                int r82 = R.string.ChannelReplyIcon;
            L8:
                r02.setText(LocaleController.getString(r82));
            L14:
                addView(this.textView, LayoutHelper.createFrame(-1, -2.0f, 23, 20.0f, 0.0f, 20.0f, 0.0f));
                this.imageDrawable = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this, false, AndroidUtilities.dp(24.0f), 13);
                return;
            L7:
                r82 = R.string.UserReplyIcon;
                goto L8
            L10:
                if (PeerColorActivity.m16685$$Nest$fgetisChannel(r8.this$0) == false) goto L12;
                int r83 = R.string.ChannelProfileIcon;
            L13:
                r02.setText(LocaleController.getString(r83));
                goto L14
            L12:
                r83 = R.string.UserProfileIcon;
                goto L13
            }

            @Override // android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r9) {
                super.dispatchDraw(r9);
                updateImageBounds();
                this.imageDrawable.setColor(Integer.valueOf(getColor()));
                Text r2 = this.offText;
                if (r2 == null) goto L6;
                r2.draw(r9, (getMeasuredWidth() - this.offText.getWidth()) - AndroidUtilities.dp(19.0f), getMeasuredHeight() / 2.0f, this.this$1.this$0.getThemedColor(Theme.key_windowBackgroundWhiteBlueText4), 1.0f);
                return;
            L6:
                this.imageDrawable.draw(r9);
            }

            public int getColor() {
                int r0 = Page.m16721$$Nest$fgetselectedColor(this.this$1);
                Page r1 = this.this$1;
                if (r0 >= 0) goto L14;
                PeerColorActivity r02 = r1.this$0;
                int r12 = Theme.key_actionBarDefault;
                if (AndroidUtilities.computePerceivedBrightness(r02.getThemedColor(r12)) <= 0.8f) goto L9;
                return Theme.getColor(Theme.key_windowBackgroundWhiteBlueText, PeerColorActivity.access$1800(this.this$1.this$0));
            L9:
                if (AndroidUtilities.computePerceivedBrightness(this.this$1.this$0.getThemedColor(r12)) >= 0.2f) goto L13;
                return Theme.multAlpha(Theme.getColor(Theme.key_actionBarDefaultTitle, PeerColorActivity.access$1900(this.this$1.this$0)), 0.5f);
            L13:
                return Theme.blendOver(Theme.getColor(Theme.key_windowBackgroundWhite, PeerColorActivity.access$2000(this.this$1.this$0)), Theme.multAlpha(PeerColorActivity.adaptProfileEmojiColor(Theme.getColor(r12, PeerColorActivity.access$2100(this.this$1.this$0))), 0.7f));
            L14:
                int r03 = Page.m16721$$Nest$fgetselectedColor(r1);
                Page r13 = this.this$1;
                if (r03 < 7) goto L17;
                int r04 = Page.m16729$$Nest$fgettype(r13);
                Page r14 = this.this$1;
                if (r04 != 1) goto L21;
                MessagesController.PeerColors r05 = MessagesController.getInstance(PeerColorActivity.access$2200(r14.this$0)).peerColors;
            L22:
                if (r05 == null) goto L28;
                MessagesController.PeerColor r06 = r05.getColor(Page.m16721$$Nest$fgetselectedColor(this.this$1));
                if (r06 == null) goto L28;
                return r06.getColor1();
            L28:
                return this.this$1.this$0.getThemedColor(Theme.keys_avatar_nameInMessage[0]);
            L21:
                r05 = MessagesController.getInstance(PeerColorActivity.access$2300(r14.this$0)).profilePeerColors;
                goto L22
            L17:
                return r13.this$0.getThemedColor(Theme.keys_avatar_nameInMessage[Page.m16721$$Nest$fgetselectedColor(r13)]);
            }

            @Override // android.view.ViewGroup, android.view.View
            public void onAttachedToWindow() {
                super.onAttachedToWindow();
                this.imageDrawable.attach();
            }

            @Override // android.view.ViewGroup, android.view.View
            public void onDetachedFromWindow() {
                super.onDetachedFromWindow();
                this.imageDrawable.detach();
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f), TLObject.FLAG_30));
            }

            public void update(boolean r6) {
                long r0 = Page.m16722$$Nest$fgetselectedEmoji(this.this$1);
                AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r1 = this.imageDrawable;
                if (r0 == 0) goto L6;
                r1.set(Page.m16722$$Nest$fgetselectedEmoji(this.this$1), r6);
                this.offText = null;
                return;
            L6:
                r1.set(null, r6);
                if (this.offText == null) goto L9;
                return;
            L9:
                if (PeerColorActivity.m16685$$Nest$fgetisChannel(this.this$1.this$0) == false) goto L11;
                int r02 = R.string.ChannelReplyIconOff;
            L12:
                this.offText = new Text(LocaleController.getString(r02), 16.0f);
                return;
            L11:
                r02 = R.string.UserReplyIconOff;
                goto L12
            }

            public void updateColors() {
                setBackgroundColor(this.this$1.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
                this.textView.setTextColor(this.this$1.this$0.getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
            }

            public void updateImageBounds() {
                AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r0 = this.imageDrawable;
                if (LocaleController.isRTL == false) goto L5;
                int r1 = AndroidUtilities.dp(21.0f);
            L6:
                int r3 = (getHeight() - this.imageDrawable.getIntrinsicHeight()) / 2;
                if (LocaleController.isRTL == false) goto L9;
                int r2 = AndroidUtilities.dp(21.0f) + this.imageDrawable.getIntrinsicWidth();
            L10:
                r0.setBounds(r1, r3, r2, (getHeight() + this.imageDrawable.getIntrinsicHeight()) / 2);
                return;
            L9:
                r2 = getWidth() - AndroidUtilities.dp(21.0f);
                goto L10
            L5:
                r1 = (getWidth() - this.imageDrawable.getIntrinsicWidth()) - AndroidUtilities.dp(21.0f);
                goto L6
            }
        }

        /* renamed from: $r8$lambda$LMasEbJCFd78EB2-Em6wg4HYYu0, reason: not valid java name */
        public static /* synthetic */ void m16709$r8$lambda$LMasEbJCFd78EB2Em6wg4HYYu0(Page r0) {
            r0.updateGiftTabsPosition();
        }

        public static /* synthetic */ void $r8$lambda$oDVjr9x0p_JH2M0JIZoNZ9Mn6bs(Page r0, int r1, View r2, int r3) {
            r0.lambda$new$0(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$vWHUOoicbYE5OxfLSGcFkbNp28s(Page r0) {
            r0.lambda$new$1();
        }

        public static /* synthetic */ void $r8$lambda$xghulqJ1pGO9jwl3rR9OGPd9t64(Page r0, View r1) {
            r0.lambda$updateColors$2(r1);
        }

        /* renamed from: -$$Nest$fgetbuttonState, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ButtonState m16710$$Nest$fgetbuttonState(Page r0) {
            return r0.buttonState;
        }

        /* renamed from: -$$Nest$fgetdrawGiftTabsInList, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m16711$$Nest$fgetdrawGiftTabsInList(Page r0) {
            return r0.drawGiftTabsInList;
        }

        /* renamed from: -$$Nest$fgetgiftTabsPlaceholder, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m16712$$Nest$fgetgiftTabsPlaceholder(Page r0) {
            return r0.giftTabsPlaceholder;
        }

        /* renamed from: -$$Nest$fgetgiftTabsView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Tabs m16713$$Nest$fgetgiftTabsView(Page r0) {
            return r0.giftTabsView;
        }

        /* renamed from: -$$Nest$fgetindex2gift, reason: not valid java name */
        public static /* bridge */ /* synthetic */ HashMap m16714$$Nest$fgetindex2gift(Page r0) {
            return r0.index2gift;
        }

        /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RecyclerListView m16715$$Nest$fgetlistView(Page r0) {
            return r0.listView;
        }

        /* renamed from: -$$Nest$fgetmessagesCellFade, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m16716$$Nest$fgetmessagesCellFade(Page r0) {
            return r0.messagesCellFade;
        }

        /* renamed from: -$$Nest$fgetmessagesCellFade2, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m16717$$Nest$fgetmessagesCellFade2(Page r0) {
            return r0.messagesCellFade2;
        }

        /* renamed from: -$$Nest$fgetmessagesCellPreview, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ThemePreviewMessagesCell m16718$$Nest$fgetmessagesCellPreview(Page r0) {
            return r0.messagesCellPreview;
        }

        /* renamed from: -$$Nest$fgetprofilePreview, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ProfilePreview m16719$$Nest$fgetprofilePreview(Page r0) {
            return r0.profilePreview;
        }

        /* renamed from: -$$Nest$fgetresaleGifts, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ResaleGiftsFragment.ResaleGiftsList m16720$$Nest$fgetresaleGifts(Page r0) {
            return r0.resaleGifts;
        }

        /* renamed from: -$$Nest$fgetselectedColor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m16721$$Nest$fgetselectedColor(Page r0) {
            return r0.selectedColor;
        }

        /* renamed from: -$$Nest$fgetselectedEmoji, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m16722$$Nest$fgetselectedEmoji(Page r2) {
            return r2.selectedEmoji;
        }

        /* renamed from: -$$Nest$fgetselectedEmojiCollectible, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.TL_emojiStatusCollectible m16723$$Nest$fgetselectedEmojiCollectible(Page r0) {
            return r0.selectedEmojiCollectible;
        }

        /* renamed from: -$$Nest$fgetselectedPeerCollectible, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.TL_peerColorCollectible m16724$$Nest$fgetselectedPeerCollectible(Page r0) {
            return r0.selectedPeerCollectible;
        }

        /* renamed from: -$$Nest$fgetselectedResaleGift, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TL_stars.TL_starGiftUnique m16725$$Nest$fgetselectedResaleGift(Page r0) {
            return r0.selectedResaleGift;
        }

        /* renamed from: -$$Nest$fgetselectedTabGift, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TL_stars.StarGift m16726$$Nest$fgetselectedTabGift(Page r0) {
            return r0.selectedTabGift;
        }

        /* renamed from: -$$Nest$fgetsetReplyIconCell, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SetReplyIconCell m16727$$Nest$fgetsetReplyIconCell(Page r0) {
            return r0.setReplyIconCell;
        }

        /* renamed from: -$$Nest$fgettabs, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m16728$$Nest$fgettabs(Page r0) {
            return r0.tabs;
        }

        /* renamed from: -$$Nest$fgettype, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m16729$$Nest$fgettype(Page r0) {
            return r0.type;
        }

        /* renamed from: -$$Nest$fputgiftTabsPlaceholder, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16730$$Nest$fputgiftTabsPlaceholder(Page r0, View r1) {
            r0.giftTabsPlaceholder = r1;
        }

        /* renamed from: -$$Nest$fputpeerColorPicker, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16731$$Nest$fputpeerColorPicker(Page r0, PeerColorGrid r1) {
            r0.peerColorPicker = r1;
        }

        /* renamed from: -$$Nest$fputresaleGifts, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16732$$Nest$fputresaleGifts(Page r0, ResaleGiftsFragment.ResaleGiftsList r1) {
            r0.resaleGifts = r1;
        }

        /* renamed from: -$$Nest$fputselectAnimatedEmojiDialog, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16733$$Nest$fputselectAnimatedEmojiDialog(Page r0, SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow r1) {
            r0.selectAnimatedEmojiDialog = r1;
        }

        /* renamed from: -$$Nest$fputselectedColor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16734$$Nest$fputselectedColor(Page r0, int r1) {
            r0.selectedColor = r1;
        }

        /* renamed from: -$$Nest$fputselectedEmoji, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16735$$Nest$fputselectedEmoji(Page r0, long r1) {
            r0.selectedEmoji = r1;
        }

        /* renamed from: -$$Nest$fputselectedEmojiCollectible, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16736$$Nest$fputselectedEmojiCollectible(Page r0, TLRPC.TL_emojiStatusCollectible r1) {
            r0.selectedEmojiCollectible = r1;
        }

        /* renamed from: -$$Nest$fputselectedPeerCollectible, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16737$$Nest$fputselectedPeerCollectible(Page r0, TLRPC.TL_peerColorCollectible r1) {
            r0.selectedPeerCollectible = r1;
        }

        /* renamed from: -$$Nest$fputselectedResaleGift, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16738$$Nest$fputselectedResaleGift(Page r0, TL_stars.TL_starGiftUnique r1) {
            r0.selectedResaleGift = r1;
        }

        /* renamed from: -$$Nest$fputselectedTabGift, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16739$$Nest$fputselectedTabGift(Page r0, TL_stars.StarGift r1) {
            r0.selectedTabGift = r1;
        }

        /* renamed from: -$$Nest$fputsetReplyIconCell, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16740$$Nest$fputsetReplyIconCell(Page r0, SetReplyIconCell r1) {
            r0.setReplyIconCell = r1;
        }

        /* renamed from: -$$Nest$mupdateAfterGiftTabSelected, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16741$$Nest$mupdateAfterGiftTabSelected(Page r0) {
            r0.updateAfterGiftTabSelected();
        }

        /* renamed from: -$$Nest$mupdateGiftTabsPosition, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16742$$Nest$mupdateGiftTabsPosition(Page r0) {
            r0.updateGiftTabsPosition();
        }

        /* renamed from: -$$Nest$mupdateMessages, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16743$$Nest$mupdateMessages(Page r0) {
            r0.updateMessages();
        }

        /* renamed from: -$$Nest$mupdateTabsColors, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16744$$Nest$mupdateTabsColors(Page r0, Tabs r1) {
            r0.updateTabsColors(r1);
        }

        public Page(PeerColorActivity r22, Context r23, int r24) {
            PeerColorActivity r4 = r22;
            this.this$0 = r4;
            super(r23);
            this.buttonState = new ButtonState(r4, null);
            this.selectedColor = -1;
            this.selectedEmoji = 0;
            this.selectedEmojiCollectible = null;
            this.selectedPeerCollectible = null;
            this.selectedTabGift = null;
            this.tabs = new ArrayList();
            this.index2gift = new HashMap();
            this.colorPickerRow = -1;
            this.infoRow = -1;
            this.iconRow = -1;
            this.info2Row = -1;
            this.buttonRow = -1;
            this.clearRow = -1;
            this.shadowRow = -1;
            this.giftsHeaderRow = -1;
            this.giftsStartRow = -1;
            this.giftsEndRow = -1;
            this.giftsLoadingStartRow = -1;
            this.giftsLoadingEndRow = -1;
            this.giftsCount = 0;
            this.giftsInfoRow = -1;
            this.giftsTabsRow = -1;
            this.giftsEmptyRow = -1;
            this.giftsEmptyRowGap = -1;
            this.uniqueGifts = new ArrayList();
            this.type = r24;
            setupValues();
            AnonymousClass1 r0 = new AnonymousClass1(this, getContext(), r4.getResourceProvider(), r4, r24);
            this.listView = r0;
            r0.setClipToPadding(false);
            this.listView.setSections(true);
            ((DefaultItemAnimator) this.listView.getItemAnimator()).setSupportsChangeAnimations(false);
            GridLayoutManager r02 = new GridLayoutManager(getContext(), 3);
            this.layoutManager = r02;
            r02.setSpanSizeLookup(new AnonymousClass2(this, r4));
            this.listView.addItemDecoration(new AnonymousClass3(this, r4));
            this.listView.setLayoutManager(this.layoutManager);
            RecyclerListView r03 = this.listView;
            AnonymousClass4 r2 = new AnonymousClass4(this, r4, r23, r24);
            this.listAdapter = r2;
            r03.setAdapter(r2);
            this.listView.setOnItemClickListener(new PeerColorActivity$Page$$ExternalSyntheticLambda0(this, r24));
            this.listView.addOnScrollListener(new AnonymousClass5(this, r4, r24));
            addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
            SpannableStringBuilder r04 = new SpannableStringBuilder("l");
            r04.setSpan(new ColoredImageSpan(R.drawable.msg_mini_lock2), 0, 1, 33);
            if (PeerColorActivity.m16685$$Nest$fgetisChannel(r4) == false) goto L5;
            int r25 = R.string.ChannelColorApply;
        L6:
            this.buttonUnlocked = LocaleController.getString(r25);
            this.buttonLocked = new SpannableStringBuilder(r04).append(" ").append(this.buttonUnlocked);
            this.buttonCollectible = LocaleController.getString(R.string.UserColorApplyCollectible);
            updateButton(false);
            DefaultItemAnimator r05 = new DefaultItemAnimator();
            r05.setDurations(350);
            r05.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            r05.setDelayAnimations(false);
            r05.setSupportsChangeAnimations(false);
            this.listView.setItemAnimator(r05);
            this.messagesCellFade = new View(getContext());
            BlurredBackgroundWithFadeDrawable r06 = new BlurredBackgroundWithFadeDrawable(PeerColorActivity.m16683$$Nest$fgetiBlur3BackgroundFactory(r4).create(this.messagesCellFade));
            r06.setFadeHeight(-AndroidUtilities.dp(24.0f), true);
            r06.setAlpha(Opcodes.REM_INT_LIT8);
            this.messagesCellFade.setBackground(r06);
            addView(this.messagesCellFade, LayoutHelper.createFrame(-1, 72, 55));
            if (r24 != 0) goto L9;
            this.profilePreview = new ProfilePreview(getContext(), PeerColorActivity.access$900(r4), PeerColorActivity.m16680$$Nest$fgetdialogId(r4), PeerColorActivity.access$1000(r4));
            updateProfilePreview(false);
        L10:
            Tabs r07 = new Tabs(r4, getContext(), PeerColorActivity.access$1300(r4));
            this.giftTabsView = r07;
            BlurredBackgroundDrawable r26 = PeerColorActivity.m16684$$Nest$fgetiBlur3ButtonFactory(r4).create(this.giftTabsView).setColorProvider(BlurredBackgroundProviderImpl.tabsPanelNoShadow(PeerColorActivity.access$1400(r4))).setRadius(AndroidUtilities.dp(18.0f)).setHasPadding(true);
            Tabs.m16766$$Nest$fputbackgroundDrawableNoGlass(r07, r26);
            Tabs.m16765$$Nest$fputbackgroundDrawableGlass(r07, r26);
            this.giftTabsView.setExternalInvalidate(new PeerColorActivity$Page$$ExternalSyntheticLambda1(this));
            this.giftTabsView.setVisibility(4);
            addView(this.giftTabsView, LayoutHelper.createFrame(-1, 36.0f, 55, 12.0f, 0.0f, 12.0f, 0.0f));
            ProfilePreview r08 = this.profilePreview;
            if (r08 == null) goto L13;
            addView(r08, LayoutHelper.createFrame(-1, -2, 55));
        L13:
            updateColors();
            updateRows();
            setWillNotDraw(false);
            return;
        L9:
            this.messagesCellFade2 = new View(getContext());
            BlurredBackgroundWithFadeDrawable r09 = new BlurredBackgroundWithFadeDrawable(PeerColorActivity.m16683$$Nest$fgetiBlur3BackgroundFactory(r4).create(this.messagesCellFade2));
            r09.setFadeHeight(-AndroidUtilities.dp(16.0f), false);
            this.messagesCellFade2.setBackground(r09);
            addView(this.messagesCellFade2, LayoutHelper.createFrame(-1, 16, 55));
            AnonymousClass6 r010 = new AnonymousClass6(this, getContext(), PeerColorActivity.access$1100(r4), 3, PeerColorActivity.m16680$$Nest$fgetdialogId(r4), PeerColorActivity.access$1200(r4), r22);
            r4 = r22;
            this.messagesCellPreview = r010;
            r010.setImportantForAccessibility(4);
            ThemePreviewMessagesCell r011 = this.messagesCellPreview;
            r011.fragment = r4;
            r011.setClipToOutline(true);
            this.messagesCellPreview.setOutlineProvider(ViewOutlineProviderImpl.boundsWithPaddingRoundRect(0, AndroidUtilities.dp(16.0f)));
            addView(this.messagesCellPreview, LayoutHelper.createFrame(-1, -2.0f, 55, 12.0f, 0.0f, 12.0f, 0.0f));
            goto L10
        L5:
            r25 = R.string.UserColorApply;
            goto L6
        }

        private /* synthetic */ void lambda$new$0(int r7, View r8, int r9) {
            if ((r8 instanceof SetReplyIconCell) == false) goto L7;
            showSelectStatusDialog((SetReplyIconCell) r8);
            return;
        L7:
            if (r9 != this.clearRow) goto L22;
            this.selectedColor = -1;
            this.selectedEmoji = 0;
            this.selectedEmojiCollectible = null;
            this.selectedPeerCollectible = null;
            this.selectedResaleGift = null;
            updateMessages();
            if (r7 != 0) goto L11;
            this.this$0.namePage.updateMessages();
        L11:
            SetReplyIconCell r72 = this.setReplyIconCell;
            if (r72 == null) goto L14;
            r72.update(true);
        L14:
            updateProfilePreview(true);
            updateButton(true);
            PeerColorActivity r6 = this.this$0;
            Page r73 = r6.profilePage;
            if (r73 == null) goto L60;
            ProfilePreview r74 = r73.profilePreview;
            if (r74 == null) goto L61;
            Page r62 = r6.namePage;
            if (r62 == null) goto L62;
            r74.overrideAvatarColor(r62.selectedColor);
            return;
        L62:
            return;
        L61:
            return;
        L60:
            return;
        L22:
            int r82 = this.giftsStartRow;
            if (r9 >= r82) goto L25;
            return;
        L25:
            if (r9 >= this.giftsEndRow) goto L64;
            int r92 = r9 - r82;
            if (this.selectedTabGift != null) goto L44;
            if (r92 >= 0) goto L30;
            return;
        L30:
            if (r92 >= this.uniqueGifts.size()) goto L70;
            TL_stars.TL_starGiftUnique r83 = this.uniqueGifts.get(r92);
            if (r7 != 1) goto L38;
            TLRPC.PeerColor r75 = r83.peer_color;
            if ((r75 instanceof TLRPC.TL_peerColorCollectible) == false) goto L71;
            this.selectedEmoji = 0;
            this.selectedColor = -1;
            this.selectedResaleGift = null;
            this.selectedEmojiCollectible = null;
            this.selectedPeerCollectible = (TLRPC.TL_peerColorCollectible) r75;
        L39:
            updateProfilePreview(true);
            updateMessages();
            updateButton(true);
            SetReplyIconCell r63 = this.setReplyIconCell;
            if (r63 == null) goto L66;
            r63.update(true);
            return;
        L66:
            return;
        L71:
            return;
        L38:
            this.selectedEmoji = 0;
            this.selectedColor = -1;
            this.selectedResaleGift = null;
            this.selectedEmojiCollectible = MessagesController.emojiStatusCollectibleFromGift(r83);
            this.selectedPeerCollectible = null;
            goto L39
        L70:
            return;
        L44:
            if (this.resaleGifts == null) goto L67;
            if (r92 >= 0) goto L47;
            return;
        L47:
            if (r92 >= this.uniqueGifts.size()) goto L72;
            TL_stars.TL_starGiftUnique r84 = this.uniqueGifts.get(r92);
            if (r7 != 1) goto L55;
            TLRPC.PeerColor r76 = r84.peer_color;
            if ((r76 instanceof TLRPC.TL_peerColorCollectible) == false) goto L73;
            this.selectedEmoji = 0;
            this.selectedColor = -1;
            this.selectedEmojiCollectible = null;
            this.selectedPeerCollectible = (TLRPC.TL_peerColorCollectible) r76;
        L56:
            this.selectedResaleGift = r84;
            updateProfilePreview(true);
            updateMessages();
            updateButton(true);
            SetReplyIconCell r64 = this.setReplyIconCell;
            if (r64 == null) goto L69;
            r64.update(true);
            return;
        L69:
            return;
        L73:
            return;
        L55:
            this.selectedEmoji = 0;
            this.selectedColor = -1;
            this.selectedEmojiCollectible = MessagesController.emojiStatusCollectibleFromGift(r84);
            this.selectedPeerCollectible = null;
            goto L56
        L72:
            return;
        L67:
            return;
        }

        private /* synthetic */ void lambda$new$1() {
            if (this.drawGiftTabsInList == false) goto L6;
            this.listView.invalidate();
            return;
        }

        private /* synthetic */ void lambda$updateColors$2(View r2) {
            if ((r2 instanceof PeerColorGrid) == false) goto L7;
            r2.setBackgroundColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
            ((PeerColorGrid) r2).updateColors();
            return;
        L7:
            if ((r2 instanceof TextCell) == false) goto L11;
            r2.setBackgroundColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
            ((TextCell) r2).updateColors();
            return;
        L11:
            if ((r2 instanceof SetReplyIconCell) == false) goto L15;
            r2.setBackgroundColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
            ((SetReplyIconCell) r2).updateColors();
            return;
        L15:
            if ((r2 instanceof HeaderCell) == false) goto L19;
            r2.setBackgroundColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
            return;
        L19:
            if ((r2 instanceof GiftCell) == false) goto L23;
            ((GiftCell) r2).card.invalidate();
            return;
        L23:
            if ((r2 instanceof Tabs) == false) goto L27;
            updateTabsColors((Tabs) r2);
            return;
        L27:
            if ((r2 instanceof EmptyView) == false) goto L30;
            ((EmptyView) r2).updateColors();
            return;
        }

        private void updateAfterGiftTabSelected() {
            boolean r0 = this.giftTabsPinned;
            if (r0 == false) goto L5;
            this.listView.stopScroll();
        L5:
            update();
            if (r0 == false) goto L11;
            int r02 = this.giftsTabsRow;
            if (r02 < 0) goto L12;
            this.layoutManager.scrollToPositionWithOffset(r02, PeerColorActivity.m16691$$Nest$fgettabsPinOffset(this.this$0));
            this.listView.post(new PeerColorActivity$Page$$ExternalSyntheticLambda3(this));
            return;
        L12:
            return;
        }

        private void updateGiftTabsPosition() {
            float r1 = 0.0f;
            if (this.giftTabsView == null) goto L25;
            int r0 = this.giftsTabsRow;
            if (r0 < 0) goto L25;
            this.giftTabsPlaceholder = this.layoutManager.findViewByPosition(r0);
            int r02 = this.listView.getPaddingTop() + PeerColorActivity.m16691$$Nest$fgettabsPinOffset(this.this$0);
            int r3 = this.layoutManager.findFirstVisibleItemPosition();
            View r4 = this.giftTabsPlaceholder;
            if (r4 == null) goto L15;
            if (r4.getTop() > r02) goto L12;
            boolean r32 = true;
        L13:
            this.giftTabsPinned = r32;
            this.drawGiftTabsInList = !r32;
            this.giftTabsView.setVisibility(0);
            int r2 = this.listView.getTop() + Math.max(this.giftTabsPlaceholder.getTop(), r02);
            this.giftTabsView.setTranslationY(r2 - r0.getTop());
        L20:
            this.giftTabsView.setDrawInList(this.drawGiftTabsInList);
            View r03 = this.messagesCellFade;
            if (this.giftTabsPinned == false) goto L23;
            r1 = AndroidUtilities.dp(36.0f) + PeerColorActivity.m16691$$Nest$fgettabsPinOffset(this.this$0);
        L23:
            r03.setTranslationY(r1);
            this.listView.invalidate();
            invalidate();
            return;
        L12:
            r32 = false;
            goto L13
        L15:
            if (r3 != (-1)) goto L17;
        L19:
            this.giftTabsPinned = false;
            this.drawGiftTabsInList = false;
            this.giftTabsView.setVisibility(0);
            this.giftTabsView.setTranslationY((getHeight() + AndroidUtilities.dp(1.0f)) - this.giftTabsView.getTop());
            goto L20
        L17:
            if (r3 <= this.giftsTabsRow) goto L19;
            this.giftTabsPinned = true;
            this.drawGiftTabsInList = false;
            this.giftTabsView.setVisibility(0);
            int r22 = this.listView.getTop() + r02;
            this.giftTabsView.setTranslationY(r22 - r0.getTop());
        L25:
            this.drawGiftTabsInList = false;
            this.giftTabsPinned = false;
            View r04 = this.messagesCellFade;
            if (r04 == null) goto L28;
            r04.setTranslationY(0.0f);
        L28:
            Tabs r05 = this.giftTabsView;
            if (r05 == null) goto L32;
            r05.setVisibility(4);
            this.giftTabsView.setDrawInList(this.drawGiftTabsInList);
            return;
        }

        private void updateMessages() {
            ThemePreviewMessagesCell r0 = this.messagesCellPreview;
            if (r0 == null) goto L16;
            ChatMessageCell[] r02 = r0.getCells();
            int r1 = 0;
        L6:
            if (r1 >= r02.length) goto L21;
            ChatMessageCell r2 = r02[r1];
            if (r2 == null) goto L15;
            MessageObject r22 = r2.getMessageObject();
            if (r22 == null) goto L15;
            r22.notime = true;
            PeerColorGrid r3 = this.peerColorPicker;
            if (r3 == null) goto L14;
            r22.overrideLinkColor = r3.getColorId();
        L14:
            r22.overrideLinkEmoji = this.selectedEmoji;
            r22.overrideLinkPeerColor = this.selectedPeerCollectible;
            r02[r1].setAvatar(r22);
            r02[r1].invalidate();
        L15:
            r1 = r1 + 1;
            goto L6
        L21:
            return;
        }

        private void updateRows() {
            this.clearRow = -1;
            this.shadowRow = -1;
            this.giftsHeaderRow = -1;
            this.giftsStartRow = -1;
            this.giftsLoadingStartRow = -1;
            this.giftsLoadingEndRow = -1;
            this.giftsEndRow = -1;
            this.giftsInfoRow = -1;
            this.giftsTabsRow = -1;
            this.giftsEmptyRow = -1;
            this.giftsEmptyRowGap = -1;
            int r0 = 0;
            this.giftsCount = 0;
            this.uniqueGifts.clear();
            this.colorPickerRow = 0;
            int r2 = 1 + 1;
            this.iconRow = 1;
            int r3 = r2 + 1;
            this.rowCount = r3;
            this.infoRow = r2;
            int r4 = this.type;
            int r5 = 3;
            if (r4 == 0) goto L5;
        L11:
            PeerColorActivity r22 = this.this$0;
            if (r4 != 1) goto L14;
            StarsController.GiftsList r23 = PeerColorActivity.m16682$$Nest$fgetgiftsWithPeerColor(r22);
        L15:
            int r32 = this.type;
            if (r32 == 0) goto L18;
            if (r32 == 1) goto L18;
        L73:
            int r02 = this.rowCount;
            this.rowCount = r02 + 1;
            this.buttonRow = r02;
            Tabs r03 = this.giftTabsView;
            if (r03 == null) goto L84;
            r03.post(new PeerColorActivity$Page$$ExternalSyntheticLambda3(this));
            return;
        L84:
            return;
        L18:
            if (r23 == null) goto L73;
            int r1 = this.rowCount;
            this.rowCount = r1 + 1;
            this.giftsTabsRow = r1;
            TL_stars.StarGift r12 = this.selectedTabGift;
            if (r12 == null) goto L22;
            if (r12 != null) goto L47;
        L72:
            int r04 = this.rowCount;
            this.rowCount = r04 + 1;
            this.giftsInfoRow = r04;
            goto L73
        L47:
            if (this.resaleGifts == null) goto L72;
            long r13 = UserConfig.getInstance(PeerColorActivity.access$2400(this.this$0)).getClientUserId();
        L50:
            if (r0 >= this.resaleGifts.gifts.size()) goto L57;
            TL_stars.TL_starGiftUnique r42 = this.resaleGifts.gifts.get(r0);
            if (DialogObject.getPeerDialogId(r42.owner_id) == r13) goto L56;
            if (DialogObject.getPeerDialogId(r42.host_id) == r13) goto L56;
            this.uniqueGifts.add(r42);
        L56:
            r0 = r0 + 1;
            goto L50
        L57:
            int r05 = this.rowCount;
            this.giftsStartRow = r05;
            this.rowCount = r05 + this.uniqueGifts.size();
            int r06 = this.giftsCount + this.uniqueGifts.size();
            this.giftsCount = r06;
            int r14 = this.rowCount;
            this.giftsEndRow = r14;
            ResaleGiftsFragment.ResaleGiftsList r24 = this.resaleGifts;
            if (r24.loading == false) goto L60;
        L61:
            this.giftsLoadingStartRow = r14;
            int r43 = 3 - (r06 % 3);
            if (r06 > 0) goto L64;
            r5 = 9;
        L67:
            int r15 = r14 + r5;
            this.rowCount = r15;
            this.giftsCount = r06 + r5;
            this.giftsLoadingEndRow = r15;
        L68:
            if (r24 == null) goto L72;
            if (seesLoading() == false) goto L72;
            this.resaleGifts.load();
            goto L72
        L64:
            if (r43 <= 0) goto L67;
            r5 = r43;
            goto L67
        L60:
            if (r24.endReached == true) goto L68;
        L22:
            if (r0 >= r23.gifts.size()) goto L27;
            TL_stars.StarGift r16 = r23.gifts.get(r0).gift;
            if ((r16 instanceof TL_stars.TL_starGiftUnique) == false) goto L26;
            this.uniqueGifts.add((TL_stars.TL_starGiftUnique) r16);
        L26:
            r0 = r0 + 1;
            goto L22
        L27:
            int r07 = this.rowCount;
            this.giftsStartRow = r07;
            this.rowCount = r07 + this.uniqueGifts.size();
            this.giftsCount += this.uniqueGifts.size();
            this.giftsEndRow = this.rowCount;
            if (PeerColorActivity.m16681$$Nest$fgetgifts(this.this$0).loading == false) goto L30;
        L35:
            int r08 = this.rowCount;
            this.giftsLoadingStartRow = r08;
            int r17 = this.giftsCount;
            int r44 = 3 - (r17 % 3);
            if (r17 > 0) goto L38;
            r5 = 9;
        L41:
            int r09 = r08 + r5;
            this.rowCount = r09;
            this.giftsCount = r17 + r5;
            this.giftsLoadingEndRow = r09;
        L43:
            if (seesLoading() == false) goto L72;
            r23.load();
            goto L72
        L38:
            if (r44 <= 0) goto L41;
            r5 = r44;
            goto L41
        L30:
            if (PeerColorActivity.m16681$$Nest$fgetgifts(this.this$0).endReached == false) goto L35;
            if (this.uniqueGifts.isEmpty() == false) goto L43;
            int r010 = this.rowCount;
            this.giftsEmptyRowGap = r010;
            this.rowCount = r010 + 2;
            this.giftsEmptyRow = r010 + 1;
            goto L43
        L14:
            r23 = PeerColorActivity.m16681$$Nest$fgetgifts(r22);
            goto L15
        L5:
            if (this.selectedColor < 0) goto L7;
        L10:
            this.clearRow = r3;
            this.rowCount = r2 + 3;
            this.shadowRow = r2 + 2;
            goto L11
        L7:
            if (this.selectedEmojiCollectible != null) goto L10;
            if (this.selectedPeerCollectible == null) goto L11;
            goto L10
        }

        private void updateTabsColors(Tabs r5) {
            r5.setColors(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite), this.this$0.getThemedColor(Theme.key_windowBackgroundGray), this.this$0.getThemedColor(Theme.key_windowBackgroundWhiteGrayText2), this.this$0.getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
        }

        public void checkResetColorButton() {
            if (this.type != 0) goto L18;
            int r0 = this.clearRow;
            updateRows();
            if (r0 >= 0) goto L8;
        L11:
            if (r0 >= 0) goto L16;
            int r02 = this.clearRow;
            if (r02 < 0) goto L17;
            this.listAdapter.notifyItemRangeInserted(r02, 2);
            return;
        L17:
            return;
        L16:
            return;
        L8:
            if (this.clearRow >= 0) goto L11;
            this.listAdapter.notifyItemRangeRemoved(r0, 2);
            return;
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r2, View r3, long r4) {
            if (r3 != this.giftTabsView) goto L9;
            if (this.giftTabsPinned == true) goto L9;
            return false;
        L9:
            return super.drawChild(r2, r3, r4);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v10 */
        /* JADX WARN: Type inference failed for: r1v11 */
        /* JADX WARN: Type inference failed for: r1v4 */
        /* JADX WARN: Type inference failed for: r1v5 */
        /* JADX WARN: Type inference failed for: r1v6 */
        /* JADX WARN: Type inference failed for: r1v9 */
        /* JADX WARN: Type inference failed for: r2v10 */
        /* JADX WARN: Type inference failed for: r2v11 */
        /* JADX WARN: Type inference failed for: r2v3 */
        /* JADX WARN: Type inference failed for: r2v4 */
        /* JADX WARN: Type inference failed for: r2v5 */
        /* JADX WARN: Type inference failed for: r2v9 */
        public boolean hasUnsavedChanged() {
            boolean r0 = PeerColorActivity.m16685$$Nest$fgetisChannel(this.this$0);
            PeerColorActivity r1 = this.this$0;
            long r2 = 0;
            int r4 = -1;
            TLRPC.TL_emojiStatusCollectible r5 = null;
            TLRPC.TL_peerColorCollectible r52 = null;
            TLRPC.TL_peerColorCollectible r53 = null;
            if (r0 == false) goto L37;
            TLRPC.Chat r02 = r1.getMessagesController().getChat(Long.valueOf(-PeerColorActivity.m16680$$Nest$fgetdialogId(this.this$0)));
            if (r02 != null) goto L7;
            return false;
        L7:
            int r12 = this.type;
            int r8 = this.selectedColor;
            if (r12 != 1) goto L22;
            if (r8 == ChatObject.getColorId(r02)) goto L12;
        L20:
            return true;
        L12:
            if (this.selectedEmoji != ChatObject.getEmojiId(r02)) goto L20;
            TLRPC.PeerColor r03 = r02.color;
            if ((r03 instanceof TLRPC.TL_peerColorCollectible) == false) goto L17;
            r52 = (TLRPC.TL_peerColorCollectible) r03;
        L17:
            if (PeerColorActivity.eq(r52, this.selectedPeerCollectible) == false) goto L20;
            return false;
        L22:
            if ((r02.emoji_status instanceof TLRPC.TL_emojiStatusCollectible) == true) goto L25;
            r4 = ChatObject.getProfileColorId(r02);
        L25:
            if (r8 != r4) goto L36;
            long r42 = this.selectedEmoji;
            if ((r02.emoji_status instanceof TLRPC.TL_emojiStatusCollectible) == true) goto L31;
            r2 = ChatObject.getOnlyProfileEmojiId(r02);
        L31:
            if (r42 != r2) goto L36;
            if (PeerColorActivity.eq(r02.emoji_status, this.selectedEmojiCollectible) == false) goto L36;
            return false;
        L36:
            return true;
        L37:
            TLRPC.User r04 = r1.getUserConfig().getCurrentUser();
            if (r04 != null) goto L40;
            return false;
        L40:
            int r13 = this.type;
            int r82 = this.selectedColor;
            if (r13 != 1) goto L64;
            if ((r04.color instanceof TLRPC.TL_peerColorCollectible) == true) goto L46;
            r4 = UserObject.getColorId(r04);
        L46:
            if (r82 != r4) goto L48;
            ?? r14 = true;
        L50:
            if (this.selectedEmoji != UserObject.getEmojiId(r04)) goto L52;
            ?? r22 = true;
        L53:
            TLRPC.PeerColor r05 = r04.color;
            if ((r05 instanceof TLRPC.TL_peerColorCollectible) == false) goto L56;
            r53 = (TLRPC.TL_peerColorCollectible) r05;
        L56:
            boolean r10 = PeerColorActivity.eq(r53, this.selectedPeerCollectible);
            if (r14 == false) goto L62;
            if (r22 == false) goto L62;
            if (r10 == false) goto L62;
            return false;
        L62:
            return true;
        L52:
            r22 = false;
            goto L53
        L48:
            r14 = false;
            goto L50
        L64:
            if ((r04.emoji_status instanceof TLRPC.TL_emojiStatusCollectible) == true) goto L67;
            r4 = UserObject.getProfileColorId(r04);
        L67:
            if (r82 != r4) goto L69;
            ?? r15 = true;
        L70:
            long r83 = this.selectedEmoji;
            if ((r04.emoji_status instanceof TLRPC.TL_emojiStatusCollectible) == true) goto L75;
            r2 = UserObject.getOnlyProfileEmojiId(r04);
        L75:
            if (r83 != r2) goto L77;
            ?? r23 = true;
        L78:
            TLRPC.EmojiStatus r06 = r04.emoji_status;
            if ((r06 instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L81;
            r5 = (TLRPC.TL_emojiStatusCollectible) r06;
        L81:
            boolean r102 = PeerColorActivity.eq(r5, this.selectedEmojiCollectible);
            if (r15 == false) goto L87;
            if (r23 == false) goto L87;
            if (r102 == false) goto L87;
            return false;
        L87:
            return true;
        L77:
            r23 = false;
            goto L78
        L69:
            r15 = false;
            goto L70
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r6, int r7) {
            if (this.type != 1) goto L5;
            super.onMeasure(r6, r7);
            this.actionBarHeight = (this.messagesCellPreview.getMeasuredHeight() + ActionBar.getCurrentActionBarHeight()) + AndroidUtilities.statusBarHeight;
            ViewGroup.MarginLayoutParams r0 = (ViewGroup.MarginLayoutParams) this.messagesCellPreview.getLayoutParams();
            r0.topMargin = ActionBar.getCurrentActionBarHeight() + AndroidUtilities.statusBarHeight;
            ViewGroup.MarginLayoutParams r02 = (ViewGroup.MarginLayoutParams) this.listView.getLayoutParams();
            r02.topMargin = this.actionBarHeight - AndroidUtilities.dp(16.0f);
            ViewGroup.MarginLayoutParams r03 = (ViewGroup.MarginLayoutParams) this.messagesCellFade.getLayoutParams();
            r03.topMargin = this.actionBarHeight - AndroidUtilities.dp(64.0f);
            ViewGroup.MarginLayoutParams r04 = (ViewGroup.MarginLayoutParams) this.messagesCellFade2.getLayoutParams();
            r04.topMargin = this.actionBarHeight - AndroidUtilities.dp(16.0f);
            RecyclerListView r05 = this.listView;
            r05.setPadding(r05.getPaddingLeft(), AndroidUtilities.dp(16.0f), this.listView.getPaddingRight(), this.listView.getPaddingBottom());
        L6:
            super.onMeasure(r6, r7);
            return;
        L5:
            this.actionBarHeight = AndroidUtilities.dp(230.0f) + AndroidUtilities.statusBarHeight;
            ViewGroup.MarginLayoutParams r06 = (ViewGroup.MarginLayoutParams) this.listView.getLayoutParams();
            r06.topMargin = this.actionBarHeight;
            ViewGroup.MarginLayoutParams r07 = (ViewGroup.MarginLayoutParams) this.messagesCellFade.getLayoutParams();
            r07.topMargin = this.actionBarHeight - AndroidUtilities.dp(64.0f);
            ViewGroup.MarginLayoutParams r08 = (ViewGroup.MarginLayoutParams) this.profilePreview.getLayoutParams();
            r08.height = this.actionBarHeight;
            goto L6
        }

        public void premiumChanged() {
            updateButton(true);
        }

        public boolean seesLoading() {
            if (this.listView != null) goto L5;
            return false;
        L5:
            int r0 = 0;
        L7:
            if (r0 >= this.listView.getChildCount()) goto L13;
            if ((this.listView.getChildAt(r0) instanceof FlickerLoadingView) == true) goto L10;
            r0 = r0 + 1;
            goto L7
        L10:
            return true;
        L13:
            return false;
        }

        public void setupValues() {
            int r0 = this.type;
            PeerColorActivity r1 = this.this$0;
            TLRPC.TL_peerColorCollectible r4 = null;
            if (r0 != 0) goto L20;
            long r02 = PeerColorActivity.m16680$$Nest$fgetdialogId(r1);
            PeerColorActivity r12 = this.this$0;
            if (r02 >= 0) goto L13;
            TLRPC.Chat r03 = r12.getMessagesController().getChat(Long.valueOf(-PeerColorActivity.m16680$$Nest$fgetdialogId(this.this$0)));
            this.selectedColor = ChatObject.getProfileColorId(r03);
            this.selectedEmoji = ChatObject.getProfileEmojiId(r03);
            if (r03 == null) goto L11;
            TLRPC.EmojiStatus r04 = r03.emoji_status;
            if ((r04 instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L11;
            TLRPC.TL_emojiStatusCollectible r05 = (TLRPC.TL_emojiStatusCollectible) r04;
        L12:
            this.selectedEmojiCollectible = r05;
            this.selectedPeerCollectible = null;
        L35:
            if (this.selectedEmojiCollectible == null) goto L37;
        L40:
            this.selectedColor = -1;
            this.selectedEmoji = 0;
            return;
        L37:
            if (this.selectedPeerCollectible != null) goto L40;
            return;
        L11:
            r05 = null;
            goto L12
        L13:
            TLRPC.User r06 = r12.getUserConfig().getCurrentUser();
            this.selectedColor = UserObject.getProfileColorId(r06);
            this.selectedEmoji = UserObject.getProfileEmojiId(r06);
            if (r06 == null) goto L18;
            TLRPC.EmojiStatus r07 = r06.emoji_status;
            if ((r07 instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L18;
            TLRPC.TL_emojiStatusCollectible r08 = (TLRPC.TL_emojiStatusCollectible) r07;
        L19:
            this.selectedEmojiCollectible = r08;
            this.selectedPeerCollectible = null;
        L18:
            r08 = null;
            goto L19
        L20:
            long r09 = PeerColorActivity.m16680$$Nest$fgetdialogId(r1);
            PeerColorActivity r13 = this.this$0;
            if (r09 >= 0) goto L28;
            TLRPC.Chat r010 = r13.getMessagesController().getChat(Long.valueOf(-PeerColorActivity.m16680$$Nest$fgetdialogId(this.this$0)));
            this.selectedColor = ChatObject.getColorId(r010);
            this.selectedEmoji = ChatObject.getEmojiId(r010);
            this.selectedEmojiCollectible = null;
            if (r010 == null) goto L27;
            TLRPC.PeerColor r011 = r010.color;
            if ((r011 instanceof TLRPC.TL_peerColorCollectible) == false) goto L27;
            r4 = (TLRPC.TL_peerColorCollectible) r011;
        L27:
            this.selectedPeerCollectible = r4;
            goto L35
        L28:
            TLRPC.User r012 = r13.getUserConfig().getCurrentUser();
            this.selectedColor = UserObject.getColorId(r012);
            this.selectedEmoji = UserObject.getEmojiId(r012);
            this.selectedEmojiCollectible = null;
            if (r012 == null) goto L33;
            TLRPC.PeerColor r013 = r012.color;
            if ((r013 instanceof TLRPC.TL_peerColorCollectible) == false) goto L33;
            r4 = (TLRPC.TL_peerColorCollectible) r013;
        L33:
            this.selectedPeerCollectible = r4;
            goto L35
        }

        public void showSelectStatusDialog(SetReplyIconCell r19) {
            if (this.selectAnimatedEmojiDialog != null) goto L32;
            if (r19 == null) goto L33;
            SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[] r12 = new SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[1];
            int r0 = (int) Math.min(AndroidUtilities.dp(330.0f), AndroidUtilities.displaySize.y * 0.75f);
            int r2 = (int) Math.min(AndroidUtilities.dp(324.0f), AndroidUtilities.displaySize.x * 0.95f);
            AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r14 = SetReplyIconCell.m16750$$Nest$fgetimageDrawable(r19);
            if (SetReplyIconCell.m16750$$Nest$fgetimageDrawable(r19) == null) goto L13;
            SetReplyIconCell.m16750$$Nest$fgetimageDrawable(r19).play();
            r19.updateImageBounds();
            Rect r3 = AndroidUtilities.rectTmp2;
            r3.set(SetReplyIconCell.m16750$$Nest$fgetimageDrawable(r19).getBounds());
            if (this.type != 1) goto L11;
            int r4 = ((-r3.centerY()) + AndroidUtilities.dp(12.0f)) - r0;
        L12:
            int r02 = r3.centerX() - (r19.getRight() - r2);
        L14:
            PeerColorActivity r22 = this.this$0;
            Context r32 = getContext();
            Integer r5 = Integer.valueOf(r02);
            int r16 = 5;
            if (this.type != 1) goto L17;
            int r6 = 5;
        L18:
            Theme.ResourcesProvider r8 = this.this$0.getResourceProvider();
            if (this.type != 1) goto L22;
            int r7 = 24;
        L21:
            int r9 = r7;
            int r72 = r4;
            AnonymousClass7 r23 = new AnonymousClass7(this, r22, r32, true, r5, r6, true, r8, r9, r19.getColor(), r19, r12);
            r23.useAccentForPlus = true;
            long r24 = this.selectedEmoji;
            if (r24 != 0) goto L26;
            Long r25 = null;
        L27:
            r23.setSelected(r25);
            r23.setSaveState(3);
            r23.setScrimDrawable(r14, r19);
            AnonymousClass8 r33 = new AnonymousClass8(this, r23, -2, -2);
            this.selectAnimatedEmojiDialog = r33;
            r12[0] = r33;
            if (LocaleController.isRTL == false) goto L30;
            r16 = 3;
        L30:
            r33.showAsDropDown(r19, 0, r72, r16 | 48);
            r12[0].dimBehind();
            return;
        L26:
            r25 = Long.valueOf(r24);
            goto L27
        L22:
            r7 = 16;
            goto L21
        L17:
            r6 = 7;
            goto L18
        L11:
            r4 = (-(r19.getHeight() - r3.centerY())) - AndroidUtilities.dp(16.0f);
            goto L12
        L13:
            r02 = 0;
            r4 = 0;
            goto L14
        L33:
            return;
        }

        public void update() {
            updateRows();
            this.listAdapter.notifyDataSetChanged();
        }

        public void updateButton(boolean r5) {
            TL_stars.TL_starGiftUnique r0 = this.selectedResaleGift;
            if (r0 == null) goto L8;
            AmountUtils$Amount r2 = r0.getResellAmount(AmountUtils$Currency.STARS);
            if (r0.resale_ton_only == false) goto L7;
            AmountUtils$Amount r02 = r0.getResellAmount(AmountUtils$Currency.TON);
            ButtonState.m16708$$Nest$fputtext(this.buttonState, StarsIntroActivity.replaceStars(true, LocaleController.formatString(R.string.ResellGiftBuyTON, new Object[]{r02.asFormatString()})));
            ButtonState.m16707$$Nest$fputsubText(this.buttonState, StarsIntroActivity.replaceStars(LocaleController.formatPluralStringComma("ResellGiftBuyEq", (int) r2.asDecimal())));
        L19:
            if (PeerColorActivity.m16699$$Nest$mgetButtonPage(this.this$0) != this) goto L22;
            PeerColorActivity.m16697$$Nest$mapplyButtonState(this.this$0, this, r5);
            return;
        L22:
            return;
        L7:
            ButtonState.m16708$$Nest$fputtext(this.buttonState, StarsIntroActivity.replaceStars(LocaleController.formatPluralStringComma("ResellGiftBuy", (int) r2.asDecimal())));
            ButtonState.m16707$$Nest$fputsubText(this.buttonState, null);
            goto L19
        L8:
            ButtonState r03 = this.buttonState;
            if (this.this$0.getUserConfig().isPremium() == true) goto L14;
            if (PeerColorActivity.m16685$$Nest$fgetisChannel(this.this$0) == true) goto L14;
            CharSequence r22 = this.buttonLocked;
        L17:
            ButtonState.m16708$$Nest$fputtext(r03, r22);
            ButtonState.m16707$$Nest$fputsubText(this.buttonState, null);
        L14:
            if (this.selectedEmojiCollectible == null) goto L16;
            r22 = this.buttonCollectible;
            goto L17
        L16:
            r22 = this.buttonUnlocked;
            goto L17
        }

        public void updateColors() {
            if (PeerColorActivity.m16674$$Nest$fgetbutton(this.this$0) == null) goto L5;
            PeerColorActivity.m16674$$Nest$fgetbutton(this.this$0).updateColors();
        L5:
            ThemePreviewMessagesCell r0 = this.messagesCellPreview;
            if (r0 == null) goto L8;
            r0.invalidate();
        L8:
            updateProfilePreview(true);
            AndroidUtilities.forEachViews(this.listView, new PeerColorActivity$Page$$ExternalSyntheticLambda2(this));
            Tabs r02 = this.giftTabsView;
            if (r02 == null) goto L12;
            updateTabsColors(r02);
            return;
        }

        public void updateProfilePreview(boolean r8) {
            PeerColorGrid r0 = this.peerColorPicker;
            if (r0 == null) goto L5;
            r0.setSelected(this.selectedColor, r8);
        L5:
            ProfilePreview r02 = this.profilePreview;
            if (r02 == null) goto L16;
            TLRPC.TL_emojiStatusCollectible r1 = this.selectedEmojiCollectible;
            if (r1 == null) goto L10;
            r02.setStatusEmoji(r1.document_id, true, r8);
            this.profilePreview.setColor(MessagesController.PeerColor.fromCollectible(this.selectedEmojiCollectible), r8);
            this.profilePreview.setEmoji(this.selectedEmojiCollectible.pattern_document_id, true, r8);
            goto L16
        L10:
            boolean r03 = DialogObject.isEmojiStatusCollectible(PeerColorActivity.m16680$$Nest$fgetdialogId(this.this$0));
            ProfilePreview r12 = this.profilePreview;
            if (r03 == false) goto L13;
            r12.setStatusEmoji(0, false, r8);
        L14:
            this.profilePreview.setColor(this.selectedColor, r8);
            this.profilePreview.setEmoji(this.selectedEmoji, false, r8);
            goto L16
        L13:
            r12.setStatusEmoji(DialogObject.getEmojiStatusDocumentId(PeerColorActivity.m16680$$Nest$fgetdialogId(this.this$0)), DialogObject.isEmojiStatusCollectible(PeerColorActivity.m16680$$Nest$fgetdialogId(this.this$0)), r8);
        L16:
            if (this.type == 0) goto L18;
        L23:
            checkResetColorButton();
            updateSelectedGift();
            return;
        L18:
            if (PeerColorActivity.m16677$$Nest$fgetcolorBar(this.this$0) == null) goto L23;
            TLRPC.TL_emojiStatusCollectible r04 = this.selectedEmojiCollectible;
            PeerColorActivity r13 = this.this$0;
            if (r04 == null) goto L22;
            PeerColorActivity.m16677$$Nest$fgetcolorBar(r13).setColor(MessagesController.PeerColor.fromCollectible(this.selectedEmojiCollectible), r8);
            goto L23
        L22:
            PeerColorActivity.m16677$$Nest$fgetcolorBar(r13).setColor(PeerColorActivity.access$2500(this.this$0), this.selectedColor, r8);
            goto L23
        }

        public void updateSelectedGift() {
            int r1 = 0;
        L4:
            if (r1 >= this.listView.getChildCount()) goto L32;
            View r2 = this.listView.getChildAt(r1);
            if ((r2 instanceof GiftCell) == false) goto L19;
            GiftCell r22 = (GiftCell) r2;
            TLRPC.TL_emojiStatusCollectible r3 = this.selectedEmojiCollectible;
            if (r3 != null) goto L10;
        L11:
            TLRPC.TL_peerColorCollectible r32 = this.selectedPeerCollectible;
            if (r32 != null) goto L14;
        L16:
            boolean r33 = false;
        L17:
            r22.setSelected(r33, true);
        L31:
            r1 = r1 + 1;
            goto L4
        L14:
            if (r32.collectible_id != r22.getGiftId()) goto L16;
        L15:
            r33 = true;
            goto L17
        L10:
            if (r3.collectible_id == r22.getGiftId()) goto L15;
        L19:
            if ((r2 instanceof GiftSheet.GiftCell) == false) goto L31;
            GiftSheet.GiftCell r23 = (GiftSheet.GiftCell) r2;
            TLRPC.TL_emojiStatusCollectible r34 = this.selectedEmojiCollectible;
            if (r34 != null) goto L23;
        L24:
            TLRPC.TL_peerColorCollectible r35 = this.selectedPeerCollectible;
            if (r35 != null) goto L27;
        L29:
            boolean r36 = false;
        L30:
            r23.setSelected(r36, true);
            goto L31
        L27:
            if (r35.collectible_id != r23.getGiftId()) goto L29;
        L28:
            r36 = true;
            goto L30
        L23:
            if (r34.collectible_id == r23.getGiftId()) goto L28;
        }
    }

    public static class PeerColorDrawable extends Drawable {
        private final Path clipCirclePath;
        private final Paint color1Paint;
        private final Paint color2Paint;
        private final Path color2Path;
        private final Paint color3Paint;
        private final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable emoji;
        private final boolean hasColor3;
        private float radius;
        private Paint strokePaint;

        /* renamed from: org.telegram.ui.PeerColorActivity$PeerColorDrawable$1, reason: invalid class name */
        public class AnonymousClass1 implements View.OnAttachStateChangeListener {
            final /* synthetic */ PeerColorDrawable this$0;

            public AnonymousClass1(PeerColorDrawable r1) {
                this.this$0 = r1;
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View r1) {
                if (PeerColorDrawable.m16751$$Nest$fgetemoji(this.this$0) == null) goto L6;
                PeerColorDrawable.m16751$$Nest$fgetemoji(this.this$0).attach();
                return;
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View r1) {
                if (PeerColorDrawable.m16751$$Nest$fgetemoji(this.this$0) == null) goto L6;
                PeerColorDrawable.m16751$$Nest$fgetemoji(this.this$0).detach();
                return;
            }
        }

        /* renamed from: -$$Nest$fgetemoji, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable m16751$$Nest$fgetemoji(PeerColorDrawable r0) {
            return r0.emoji;
        }

        public PeerColorDrawable(int r6, int r7, int r8) {
            this.radius = AndroidUtilities.dpf2(10.6665f);
            boolean r1 = true;
            Paint r0 = new Paint(1);
            this.color1Paint = r0;
            Paint r2 = new Paint(1);
            this.color2Paint = r2;
            Paint r3 = new Paint(1);
            this.color3Paint = r3;
            this.color2Path = new Path();
            this.clipCirclePath = new Path();
            if (r8 != r6) goto L6;
            r1 = false;
        L6:
            this.hasColor3 = r1;
            r0.setColor(r6);
            r2.setColor(r7);
            r3.setColor(r8);
            this.emoji = null;
            initPath();
        }

        public PeerColorDrawable(int r6, int r7, int r8, long r9) {
            this.radius = AndroidUtilities.dpf2(10.6665f);
            boolean r1 = true;
            Paint r0 = new Paint(1);
            this.color1Paint = r0;
            Paint r2 = new Paint(1);
            this.color2Paint = r2;
            Paint r3 = new Paint(1);
            this.color3Paint = r3;
            this.color2Path = new Path();
            this.clipCirclePath = new Path();
            if (r8 != r6) goto L6;
            r1 = false;
        L6:
            this.hasColor3 = r1;
            r0.setColor(r6);
            r2.setColor(r7);
            r3.setColor(r8);
            initPath();
            AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r62 = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(null, AndroidUtilities.dp(14.0f));
            this.emoji = r62;
            r62.set(r9, false);
        }

        public static PeerColorDrawable from(int r3, int r4) {
            if (r4 < 7) goto L5;
            MessagesController.PeerColors r32 = MessagesController.getInstance(r3).peerColors;
            if (r32 != null) goto L9;
            MessagesController.PeerColor r33 = null;
        L11:
            return from(r33, false);
        L9:
            r33 = r32.getColor(r4);
            goto L11
        L5:
            return new PeerColorDrawable(Theme.getColor(Theme.keys_avatar_nameInMessage[r4]), Theme.getColor(Theme.keys_avatar_nameInMessage[r4]), Theme.getColor(Theme.keys_avatar_nameInMessage[r4]));
        }

        public static PeerColorDrawable from(MessagesController.PeerColor r3, boolean r4) {
            if (r3 == null) goto L4;
            int r1 = r3.getColor1();
            if (r4 == true) goto L8;
        L11:
            int r2 = r3.getColor2();
        L12:
            if (r4 == false) goto L14;
            int r32 = r3.getColor1();
        L16:
            return new PeerColorDrawable(r1, r2, r32);
        L14:
            r32 = r3.getColor3();
            goto L16
        L8:
            if (r3.hasColor6(Theme.isCurrentThemeDark()) == true) goto L11;
            r2 = r3.getColor1();
            goto L12
        L4:
            return new PeerColorDrawable(0, 0, 0);
        }

        public static PeerColorDrawable from(TLRPC.TL_emojiStatusCollectible r8) {
            int r3 = r8.center_color | (-16777216);
            return new PeerColorDrawable(r3, r3, r3, r8.document_id);
        }

        public static PeerColorDrawable from(TLRPC.TL_peerColorCollectible r9) {
            if (Theme.isCurrentThemeDark() == false) goto L7;
            ArrayList<Integer> r0 = r9.dark_colors;
            if (r0 == null) goto L7;
        L8:
            if (r0 != null) goto L10;
            return null;
        L10:
            if (r0.isEmpty() == true) goto L24;
            int r4 = r0.get(0).intValue() | (-16777216);
            if (r0.size() < 2) goto L15;
            int r5 = r0.get(1).intValue() | (-16777216);
        L17:
            if (r0.size() < 3) goto L19;
            int r6 = r0.get(2).intValue() | (-16777216);
        L21:
            return new PeerColorDrawable(r4, r5, r6, r9.gift_emoji_id);
        L19:
            r6 = r4;
            goto L21
        L15:
            r5 = r4;
            goto L17
        L24:
            return null;
        L7:
            r0 = r9.colors;
            goto L8
        }

        public static PeerColorDrawable fromProfile(int r0, int r1) {
            MessagesController.PeerColors r02 = MessagesController.getInstance(r0).profilePeerColors;
            if (r02 != null) goto L5;
            MessagesController.PeerColor r03 = null;
        L7:
            return from(r03, true);
        L5:
            r03 = r02.getColor(r1);
            goto L7
        }

        private void initPath() {
            this.clipCirclePath.rewind();
            Path r0 = this.clipCirclePath;
            float r1 = this.radius;
            r0.addCircle(r1, r1, r1, Path.Direction.CW);
            this.color2Path.rewind();
            this.color2Path.moveTo(this.radius * 2.0f, 0.0f);
            Path r02 = this.color2Path;
            float r12 = this.radius;
            r02.lineTo(r12 * 2.0f, r12 * 2.0f);
            this.color2Path.lineTo(0.0f, this.radius * 2.0f);
            this.color2Path.close();
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r7) {
            r7.save();
            r7.translate(getBounds().centerX() - this.radius, getBounds().centerY() - this.radius);
            Paint r0 = this.strokePaint;
            if (r0 == null) goto L5;
            float r1 = this.radius;
            r7.drawCircle(r1, r1, r1, r0);
        L5:
            r7.clipPath(this.clipCirclePath);
            r7.drawPaint(this.color1Paint);
            r7.drawPath(this.color2Path, this.color2Paint);
            if (this.hasColor3 == false) goto L8;
            RectF r02 = AndroidUtilities.rectTmp;
            r02.set(this.radius - AndroidUtilities.dp(3.66f), this.radius - AndroidUtilities.dp(3.66f), this.radius + AndroidUtilities.dp(3.66f), this.radius + AndroidUtilities.dp(3.66f));
            float r2 = this.radius;
            r7.rotate(45.0f, r2, r2);
            r7.drawRoundRect(r02, AndroidUtilities.dp(2.33f), AndroidUtilities.dp(2.33f), this.color3Paint);
        L8:
            r7.restore();
            if (this.emoji == null) goto L12;
            int r03 = AndroidUtilities.dp(14.0f) / 2;
            this.emoji.setBounds(getBounds().centerX() - r03, getBounds().centerY() - r03, getBounds().centerX() + r03, getBounds().centerY() + r03);
            this.emoji.draw(r7);
            return;
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return (int) (this.radius * 2.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return (int) (this.radius * 2.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }

        public PeerColorDrawable setRadius(float r1) {
            this.radius = r1;
            initPath();
            return this;
        }

        public PeerColorDrawable setView(View r2) {
            AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r0 = this.emoji;
            if (r2 != null) goto L7;
            if (r0 == null) goto L6;
            r0.detach();
            this.emoji.setParentView(null);
        L6:
            return this;
        L7:
            if (r0 == null) goto L9;
            r0.setParentView(r2);
        L9:
            r2.addOnAttachStateChangeListener(new AnonymousClass1(this));
            return this;
        }

        public PeerColorDrawable stroke(float r3, int r4) {
            if (this.strokePaint != null) goto L5;
            Paint r0 = new Paint(1);
            this.strokePaint = r0;
            r0.setStyle(Paint.Style.STROKE);
        L5:
            this.strokePaint.setStrokeWidth(r3);
            this.strokePaint.setColor(r4);
            return this;
        }
    }

    public static class PeerColorGrid extends View {
        private final Paint backgroundPaint;
        private ColorButton[] buttons;
        private final int currentAccount;
        private final Paint dividerPaint;
        private boolean lock;
        private boolean needDivider;
        private Utilities.Callback<Integer> onColorClick;
        final int[] order;
        private ColorButton pressedButton;
        private final Theme.ResourcesProvider resourcesProvider;
        private int selectedColorId;
        private final int type;

        public class ColorButton {
            private final ButtonBounce bounce;
            private final RectF bounds;
            private final Path circlePath;
            public final RectF clickBounds;
            private Paint closePaint;
            private Path closePath;
            private final Path color2Path;
            private boolean hasClose;
            private boolean hasColor2;
            private boolean hasColor3;
            public int id;
            private Drawable lockDrawable;
            private final Paint paint1;
            private final Paint paint2;
            private final Paint paint3;
            private boolean pressed;
            private boolean selected;
            private final AnimatedFloat selectedT;
            final /* synthetic */ PeerColorGrid this$0;

            public ColorButton(PeerColorGrid r9) {
                this.this$0 = r9;
                this.paint1 = new Paint(1);
                this.paint2 = new Paint(1);
                this.paint3 = new Paint(1);
                this.circlePath = new Path();
                this.color2Path = new Path();
                this.bounce = new ButtonBounce(r9);
                this.selectedT = new AnimatedFloat(r9, 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
                this.bounds = new RectF();
                this.clickBounds = new RectF();
            }

            public void draw(Canvas r9) {
                r9.save();
                float r0 = this.bounce.getScale(0.05f);
                r9.scale(r0, r0, this.bounds.centerX(), this.bounds.centerY());
                r9.save();
                this.circlePath.rewind();
                this.circlePath.addCircle(this.bounds.centerX(), this.bounds.centerY(), Math.min(this.bounds.height() / 2.0f, this.bounds.width() / 2.0f), Path.Direction.CW);
                r9.clipPath(this.circlePath);
                r9.drawPaint(this.paint1);
                if (this.hasColor2 == false) goto L5;
                this.color2Path.rewind();
                Path r02 = this.color2Path;
                RectF r1 = this.bounds;
                r02.moveTo(r1.right, r1.top);
                Path r03 = this.color2Path;
                RectF r12 = this.bounds;
                r03.lineTo(r12.right, r12.bottom);
                Path r04 = this.color2Path;
                RectF r13 = this.bounds;
                r04.lineTo(r13.left, r13.bottom);
                this.color2Path.close();
                r9.drawPath(this.color2Path, this.paint2);
            L5:
                r9.restore();
                if (this.hasColor3 == false) goto L8;
                r9.save();
                float r05 = this.bounds.width() * 0.315f;
                RectF r14 = AndroidUtilities.rectTmp;
                float r06 = r05 / 2.0f;
                r14.set(this.bounds.centerX() - r06, this.bounds.centerY() - r06, this.bounds.centerX() + r06, this.bounds.centerY() + r06);
                r9.rotate(45.0f, this.bounds.centerX(), this.bounds.centerY());
                r9.drawRoundRect(r14, AndroidUtilities.dp(2.33f), AndroidUtilities.dp(2.33f), this.paint3);
                r9.restore();
            L8:
                float r07 = this.selectedT.set(this.selected);
                if (r07 <= 0.0f) goto L12;
                PeerColorGrid.m16752$$Nest$fgetbackgroundPaint(this.this$0).setStrokeWidth(AndroidUtilities.dpf2(2.0f));
                PeerColorGrid.m16752$$Nest$fgetbackgroundPaint(this.this$0).setColor(Theme.getColor(Theme.key_windowBackgroundWhite, PeerColorGrid.m16754$$Nest$fgetresourcesProvider(this.this$0)));
                r9.drawCircle(this.bounds.centerX(), this.bounds.centerY(), Math.min(this.bounds.height() / 2.0f, this.bounds.width() / 2.0f) + (PeerColorGrid.m16752$$Nest$fgetbackgroundPaint(this.this$0).getStrokeWidth() * AndroidUtilities.lerp(0.5f, -2.0f, r07)), PeerColorGrid.m16752$$Nest$fgetbackgroundPaint(this.this$0));
            L12:
                if (this.hasClose == false) goto L29;
                int r2 = -1;
                if (PeerColorGrid.m16753$$Nest$fgetlock(this.this$0) == false) goto L20;
                if (this.lockDrawable != null) goto L18;
                Drawable r08 = this.this$0.getContext().getResources().getDrawable(R.drawable.msg_mini_lock3);
                this.lockDrawable = r08;
                r08.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
            L18:
                this.lockDrawable.setBounds((int) (this.bounds.centerX() - ((this.lockDrawable.getIntrinsicWidth() / 2.0f) * 1.2f)), (int) (this.bounds.centerY() - ((this.lockDrawable.getIntrinsicHeight() / 2.0f) * 1.2f)), (int) (this.bounds.centerX() + ((this.lockDrawable.getIntrinsicWidth() / 2.0f) * 1.2f)), (int) (this.bounds.centerY() + ((this.lockDrawable.getIntrinsicHeight() / 2.0f) * 1.2f)));
                this.lockDrawable.draw(r9);
                goto L29
            L20:
                if (this.closePath != null) goto L23;
                this.closePath = new Path();
            L23:
                if (this.closePaint != null) goto L28;
                Paint r15 = new Paint(1);
                this.closePaint = r15;
                if (Theme.isCurrentThemeMonet() == false) goto L27;
                r2 = Theme.key_chats_actionIcon;
            L27:
                r15.setColor(r2);
                this.closePaint.setStyle(Paint.Style.STROKE);
                this.closePaint.setStrokeCap(Paint.Cap.ROUND);
            L28:
                this.closePaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
                this.closePath.rewind();
                float r09 = AndroidUtilities.lerp(AndroidUtilities.dp(5.0f), AndroidUtilities.dp(4.0f), r07);
                this.closePath.moveTo(this.bounds.centerX() - r09, this.bounds.centerY() - r09);
                this.closePath.lineTo(this.bounds.centerX() + r09, this.bounds.centerY() + r09);
                this.closePath.moveTo(this.bounds.centerX() + r09, this.bounds.centerY() - r09);
                this.closePath.lineTo(this.bounds.centerX() - r09, this.bounds.centerY() + r09);
                r9.drawPath(this.closePath, this.closePaint);
            L29:
                r9.restore();
            }

            public void layout(RectF r1) {
                this.bounds.set(r1);
            }

            public void layoutClickBounds(RectF r1) {
                this.clickBounds.set(r1);
            }

            public void set(int r2) {
                this.hasColor3 = false;
                this.hasColor2 = false;
                this.paint1.setColor(r2);
            }

            public void set(MessagesController.PeerColor r7) {
                if (r7 != null) goto L5;
                return;
            L5:
                if (PeerColorGrid.m16754$$Nest$fgetresourcesProvider(this.this$0) != null) goto L7;
                boolean r0 = Theme.isCurrentThemeDark();
            L9:
                if (PeerColorGrid.m16755$$Nest$fgettype(this.this$0) != 1) goto L19;
                if (r0 == true) goto L12;
            L16:
                this.paint1.setColor(r7.getColor(0, PeerColorGrid.m16754$$Nest$fgetresourcesProvider(this.this$0)));
                this.paint2.setColor(r7.getColor(1, PeerColorGrid.m16754$$Nest$fgetresourcesProvider(this.this$0)));
            L17:
                this.paint3.setColor(r7.getColor(2, PeerColorGrid.m16754$$Nest$fgetresourcesProvider(this.this$0)));
                this.hasColor2 = r7.hasColor2(r0);
                this.hasColor3 = r7.hasColor3(r0);
                return;
            L12:
                if (r7.hasColor2() == false) goto L16;
                if (r7.hasColor3() == true) goto L16;
                this.paint1.setColor(r7.getColor(1, PeerColorGrid.m16754$$Nest$fgetresourcesProvider(this.this$0)));
                this.paint2.setColor(r7.getColor(0, PeerColorGrid.m16754$$Nest$fgetresourcesProvider(this.this$0)));
                goto L17
            L19:
                this.paint1.setColor(r7.getColor(0, PeerColorGrid.m16754$$Nest$fgetresourcesProvider(this.this$0)));
                Paint r1 = this.paint2;
                boolean r4 = r7.hasColor6(r0);
                PeerColorGrid r5 = this.this$0;
                if (r4 == false) goto L22;
                int r2 = r7.getColor(1, PeerColorGrid.m16754$$Nest$fgetresourcesProvider(r5));
            L23:
                r1.setColor(r2);
                this.hasColor2 = r7.hasColor6(r0);
                this.hasColor3 = false;
                return;
            L22:
                r2 = r7.getColor(0, PeerColorGrid.m16754$$Nest$fgetresourcesProvider(r5));
                goto L23
            L7:
                r0 = PeerColorGrid.m16754$$Nest$fgetresourcesProvider(this.this$0).isDark();
                goto L9
            }

            public void setClose(boolean r1) {
                this.hasClose = r1;
            }

            public void setPressed(boolean r2) {
                ButtonBounce r0 = this.bounce;
                this.pressed = r2;
                r0.setPressed(r2);
            }

            public void setSelected(boolean r2, boolean r3) {
                this.selected = r2;
                if (r3 == true) goto L5;
                this.selectedT.set(r2, true);
            L5:
                this.this$0.invalidate();
            }
        }

        /* renamed from: -$$Nest$fgetbackgroundPaint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Paint m16752$$Nest$fgetbackgroundPaint(PeerColorGrid r0) {
            return r0.backgroundPaint;
        }

        /* renamed from: -$$Nest$fgetlock, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m16753$$Nest$fgetlock(PeerColorGrid r0) {
            return r0.lock;
        }

        /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m16754$$Nest$fgetresourcesProvider(PeerColorGrid r0) {
            return r0.resourcesProvider;
        }

        /* renamed from: -$$Nest$fgettype, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m16755$$Nest$fgettype(PeerColorGrid r0) {
            return r0.type;
        }

        public PeerColorGrid(Context r3, int r4, int r5, Theme.ResourcesProvider r6) {
            super(r3);
            Paint r32 = new Paint(1);
            this.backgroundPaint = r32;
            r32.setStyle(Paint.Style.STROKE);
            this.order = new int[]{5, 3, 1, 0, 2, 4, 6, -1};
            this.dividerPaint = new Paint(1);
            this.needDivider = true;
            this.selectedColorId = 0;
            this.type = r4;
            this.currentAccount = r5;
            this.resourcesProvider = r6;
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r9) {
            if (this.buttons == null) goto L9;
            int r0 = 0;
        L5:
            ColorButton[] r1 = this.buttons;
            if (r0 >= r1.length) goto L9;
            r1[r0].draw(r9);
            r0 = r0 + 1;
        L9:
            if (this.needDivider == false) goto L13;
            this.dividerPaint.setColor(Theme.getColor(Theme.key_divider, this.resourcesProvider));
            r9.drawRect(AndroidUtilities.dp(21.0f), getMeasuredHeight() - 1, getMeasuredWidth() - AndroidUtilities.dp(21.0f), getMeasuredHeight(), this.dividerPaint);
            return;
        }

        @Override // android.view.View
        public boolean dispatchTouchEvent(MotionEvent r7) {
            if (this.buttons == null) goto L11;
            int r0 = 0;
        L5:
            ColorButton[] r3 = this.buttons;
            if (r0 >= r3.length) goto L11;
            if (r3[r0].clickBounds.contains(r7.getX(), r7.getY()) == true) goto L9;
            r0 = r0 + 1;
            goto L5
        L9:
            ColorButton r02 = this.buttons[r0];
        L13:
            if (r7.getAction() != 0) goto L21;
            this.pressedButton = r02;
            if (r02 == null) goto L18;
            r02.setPressed(true);
        L18:
            if (getParent() == null) goto L53;
            getParent().requestDisallowInterceptTouchEvent(true);
        L53:
            return true;
        L21:
            if (r7.getAction() != 2) goto L36;
            ColorButton r72 = this.pressedButton;
            if (r72 == r02) goto L53;
            if (r72 == null) goto L26;
            r72.setPressed(false);
        L26:
            if (r02 == null) goto L29;
            r02.setPressed(true);
        L29:
            if (this.pressedButton == null) goto L34;
            if (r02 == null) goto L34;
            Utilities.Callback<Integer> r73 = this.onColorClick;
            if (r73 == null) goto L34;
            r73.run(Integer.valueOf(r02.id));
        L34:
            this.pressedButton = r02;
            goto L53
        L36:
            if (r7.getAction() == 1) goto L40;
            if (r7.getAction() != 3) goto L53;
        L40:
            if (r7.getAction() != 1) goto L47;
            ColorButton r74 = this.pressedButton;
            if (r74 == null) goto L47;
            Utilities.Callback<Integer> r03 = this.onColorClick;
            if (r03 == null) goto L47;
            r03.run(Integer.valueOf(r74.id));
        L47:
            if (this.buttons == null) goto L52;
            int r75 = 0;
        L49:
            ColorButton[] r04 = this.buttons;
            if (r75 >= r04.length) goto L52;
            r04[r75].setPressed(false);
            r75 = r75 + 1;
        L52:
            this.pressedButton = null;
        L11:
            r02 = null;
            goto L13
        }

        public int getColorId() {
            return this.selectedColorId;
        }

        @Override // android.view.View
        public void onMeasure(int r17, int r18) {
            int r1 = View.MeasureSpec.getSize(r17);
            MessagesController r2 = MessagesController.getInstance(this.currentAccount);
            boolean r4 = true;
            if (this.type != 1) goto L5;
            MessagesController.PeerColors r22 = r2.peerColors;
        L7:
            if (r22 != null) goto L9;
            int r5 = 0;
        L10:
            int r6 = this.type;
            int r7 = 8;
            int r8 = 2;
            if (r6 != 2) goto L13;
            r5 = 8;
        L13:
            if (r6 == 2) goto L17;
            if (r6 != 1) goto L17;
            r7 = 7;
        L17:
            float r9 = r1;
            float r10 = r7;
            float r11 = r7 + 1;
            float r62 = Math.min(AndroidUtilities.dp(54.0f), r9 / ((r11 * 0.28947f) + r10));
            float r12 = Math.min(0.28947f * r62, AndroidUtilities.dp(8.0f));
            float r13 = Math.min(0.31578946f * r62, AndroidUtilities.dp(11.33f));
            setMeasuredDimension(r1, (int) (((r5 / r7) * r62) + ((r14 + 1) * r13)));
            ColorButton[] r14 = this.buttons;
            if (r14 != null) goto L20;
        L23:
            this.buttons = new ColorButton[r5];
            int r15 = 0;
        L24:
            if (r15 >= r5) goto L22;
            this.buttons[r15] = new ColorButton(this);
            if (this.type != r8) goto L36;
            ColorButton r142 = this.buttons[r15];
            int r152 = this.order[r15];
            r142.id = r152;
            if (r152 >= 0) goto L30;
            boolean r153 = r4;
        L31:
            r142.setClose(r153);
            ColorButton r143 = this.buttons[r15];
            int r154 = this.order[r15];
            if (r154 >= 0) goto L34;
            int r155 = Theme.key_avatar_backgroundGray;
            boolean r172 = r4;
        L35:
            r143.set(Theme.getColor(r155, this.resourcesProvider));
        L42:
            r15 = r15 + 1;
            r4 = r172;
            r8 = 2;
            goto L24
        L34:
            r172 = r4;
            int[] r42 = Theme.keys_avatar_nameInMessage;
            r155 = r42[r154 % r42.length];
            goto L35
        L30:
            r153 = false;
            goto L31
        L36:
            r172 = r4;
            if (r22 == null) goto L42;
            if (r15 < 0) goto L42;
            if (r15 >= r22.colors.size()) goto L42;
            this.buttons[r15].id = r22.colors.get(r15).id;
            this.buttons[r15].set(r22.colors.get(r15));
        L22:
            boolean r173 = r4;
            float r92 = ((r9 - ((r10 * r62) + (r11 * r12))) / 2.0f) + r12;
            if (this.buttons == null) goto L57;
            int r23 = 0;
            float r43 = r92;
            float r52 = r13;
        L47:
            if (r23 >= this.buttons.length) goto L67;
            RectF r82 = AndroidUtilities.rectTmp;
            r82.set(r43, r52, r43 + r62, r52 + r62);
            this.buttons[r23].layout(r82);
            r82.inset((-r12) / 2.0f, (-r13) / 2.0f);
            this.buttons[r23].layoutClickBounds(r82);
            ColorButton r83 = this.buttons[r23];
            if (r83.id != this.selectedColorId) goto L51;
            boolean r102 = r173;
        L52:
            r83.setSelected(r102, false);
            if ((r23 % r7) != (r7 - 1)) goto L55;
            r52 = r52 + (r62 + r13);
            r43 = r92;
        L56:
            r23 = r23 + 1;
            goto L47
        L55:
            r43 = r43 + (r62 + r12);
            goto L56
        L51:
            r102 = false;
            goto L52
        L67:
            return;
        L57:
            return;
        L20:
            if (r14.length == r5) goto L22;
        L9:
            r5 = r22.colors.size();
            goto L10
        L5:
            r22 = r2.profilePeerColors;
            goto L7
        }

        public void setCloseAsLock(boolean r1) {
            this.lock = r1;
        }

        public void setDivider(boolean r1) {
            this.needDivider = r1;
            invalidate();
        }

        public void setOnColorClick(Utilities.Callback<Integer> r1) {
            this.onColorClick = r1;
        }

        public void setSelected(int r5, boolean r6) {
            this.selectedColorId = r5;
            if (this.buttons == null) goto L12;
            int r1 = 0;
        L5:
            ColorButton[] r2 = this.buttons;
            if (r1 >= r2.length) goto L16;
            ColorButton r22 = r2[r1];
            if (r22.id != r5) goto L10;
            boolean r3 = true;
        L11:
            r22.setSelected(r3, r6);
            r1 = r1 + 1;
            goto L5
        L10:
            r3 = false;
            goto L11
        L16:
            return;
        }

        public void updateColors() {
            if (this.buttons != null) goto L5;
            return;
        L5:
            MessagesController r0 = MessagesController.getInstance(this.currentAccount);
            if (this.type != 1) goto L8;
            MessagesController.PeerColors r02 = r0.peerColors;
        L9:
            int r3 = 0;
        L10:
            ColorButton[] r4 = this.buttons;
            if (r3 >= r4.length) goto L33;
            int r5 = this.type;
            if (r5 != 2) goto L24;
            ColorButton r42 = r4[r3];
            int r52 = this.order[r3];
            r42.id = r52;
            if (r52 >= 0) goto L17;
            boolean r53 = true;
        L18:
            r42.setClose(r53);
            ColorButton r43 = this.buttons[r3];
            int r54 = this.order[r3];
            if (r54 >= 0) goto L21;
            int r55 = Theme.key_avatar_backgroundGray;
        L22:
            r43.set(Theme.getColor(r55, this.resourcesProvider));
        L32:
            r3 = r3 + 1;
            goto L10
        L21:
            int[] r6 = Theme.keys_avatar_nameInMessage;
            r55 = r6[r54 % r6.length];
            goto L22
        L17:
            r53 = false;
            goto L18
        L24:
            if (r3 >= 7) goto L27;
            if (r5 != 1) goto L27;
            ColorButton r44 = r4[r3];
            int r56 = this.order[r3];
            r44.id = r56;
            r44.set(Theme.getColor(Theme.keys_avatar_nameInMessage[r56], this.resourcesProvider));
        L27:
            if (r02 == null) goto L32;
            if (r3 < 0) goto L32;
            if (r3 >= r02.colors.size()) goto L32;
            this.buttons[r3].id = r02.colors.get(r3).id;
            this.buttons[r3].set(r02.colors.get(r3));
            goto L32
        L33:
            invalidate();
            return;
        L8:
            r02 = r0.profilePeerColors;
            goto L9
        }
    }

    public static class PeerColorSpan extends ReplacementSpan {
        public PeerColorDrawable drawable;
        private int size;

        public PeerColorSpan(boolean r2, int r3, int r4) {
            this.size = AndroidUtilities.dp(21.0f);
            if (r2 == false) goto L5;
            PeerColorDrawable r22 = PeerColorDrawable.fromProfile(r3, r4);
        L6:
            this.drawable = r22;
            return;
        L5:
            r22 = PeerColorDrawable.from(r3, r4);
            goto L6
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
            PeerColorDrawable r22 = this.drawable;
            if (r22 == null) goto L6;
            int r62 = (r6 + r8) / 2;
            int r72 = this.size;
            r22.setBounds((int) (AndroidUtilities.dp(3.0f) + r5), r62 - this.size, (int) ((r5 + AndroidUtilities.dp(5.0f)) + r72), r62 + r72);
            this.drawable.draw(r1);
            return;
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
            return (AndroidUtilities.dp(3.0f) + this.size) + AndroidUtilities.dp(3.0f);
        }

        public PeerColorSpan setSize(int r4) {
            PeerColorDrawable r0 = this.drawable;
            if (r0 == null) goto L5;
            r0.setRadius(r4 / 2.0f);
            this.size = r4;
        L5:
            return this;
        }
    }

    public static class ProfilePreview extends FrameLayout {
        protected final AvatarDrawable avatarDrawable;
        private final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable botVerificationEmoji;
        private final int currentAccount;
        private final long dialogId;
        private final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable emoji;
        private final AnimatedFloat emojiCollectible;
        protected final ImageReceiver imageReceiver;
        private final boolean isChannel;
        private boolean isEmojiCollectible;
        private boolean isForum;
        private MessagesController.PeerColor peerColor;
        private final RectF rectF;
        private final Theme.ResourcesProvider resourcesProvider;
        private final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable statusEmoji;
        private final StoriesUtilities.StoryGradientTools storyGradient;
        protected final SimpleTextView subtitleView;
        protected final SimpleTextView titleView;

        /* renamed from: org.telegram.ui.PeerColorActivity$ProfilePreview$1, reason: invalid class name */
        public class AnonymousClass1 extends SimpleTextView {
            final /* synthetic */ ProfilePreview this$0;

            public AnonymousClass1(ProfilePreview r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.ActionBar.SimpleTextView, android.view.View
            public void onAttachedToWindow() {
                super.onAttachedToWindow();
                ProfilePreview.m16756$$Nest$fgetstatusEmoji(this.this$0).attach();
            }

            @Override // org.telegram.ui.ActionBar.SimpleTextView, android.view.View
            public void onDetachedFromWindow() {
                super.onDetachedFromWindow();
                ProfilePreview.m16756$$Nest$fgetstatusEmoji(this.this$0).detach();
            }
        }

        /* renamed from: -$$Nest$fgetstatusEmoji, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable m16756$$Nest$fgetstatusEmoji(ProfilePreview r0) {
            return r0.statusEmoji;
        }

        public ProfilePreview(Context r22, int r23, long r24, Theme.ResourcesProvider r26) {
            super(r22);
            ImageReceiver r5 = new ImageReceiver(this);
            this.imageReceiver = r5;
            AvatarDrawable r6 = new AvatarDrawable();
            this.avatarDrawable = r6;
            this.emoji = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this, false, AndroidUtilities.dp(20.0f), 13);
            this.storyGradient = new StoriesUtilities.StoryGradientTools(this, false);
            this.emojiCollectible = new AnimatedFloat(this, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.rectF = new RectF();
            this.currentAccount = r23;
            this.dialogId = r24;
            this.resourcesProvider = r26;
            long r7 = 0;
            if (r24 >= 0) goto L5;
            boolean r9 = true;
        L6:
            this.isChannel = r9;
            AnonymousClass1 r12 = new AnonymousClass1(this, r22);
            this.titleView = r12;
            this.botVerificationEmoji = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(r12, AndroidUtilities.dp(17.0f));
            this.statusEmoji = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(r12, AndroidUtilities.dp(24.0f));
            r12.setLeftDrawableOutside(true);
            r12.setRightDrawableOutside(true);
            r12.setTextColor(-1);
            r12.setTextSize(20);
            r12.setTypeface(AndroidUtilities.bold());
            r12.setWidthWrapContent(true);
            addView(r12, LayoutHelper.createFrame(-2, -2.0f, 81, 16.0f, 0.0f, 16.0f, 40.33f));
            SimpleTextView r122 = new SimpleTextView(r22);
            this.subtitleView = r122;
            r122.setTextSize(14);
            r122.setTextColor(-2130706433);
            r122.setGravity(1);
            addView(r122, LayoutHelper.createFrame(-2, -2.0f, 81, 16.0f, 0.0f, 16.0f, 20.66f));
            r5.setRoundRadius(AndroidUtilities.dp(96.0f));
            if (r9 == false) goto L15;
            TLRPC.Chat r1 = MessagesController.getInstance(r23).getChat(Long.valueOf(-r24));
            if (r1 != null) goto L11;
            CharSequence r92 = _UrlKt.FRAGMENT_ENCODE_SET;
        L12:
            r6.setInfo(r23, r1);
            r5.setForUserOrChat(r1, r6);
            long r52 = DialogObject.getBotVerificationIcon(r1);
            if (r1 == null) goto L51;
            r7 = DialogObject.getEmojiStatusDocumentId(r1.emoji_status);
        L51:
            r92 = Emoji.replaceEmoji(r92, null, false);     // Catch: Exception -> L50
        L20:
            this.titleView.setText(r92);
            this.botVerificationEmoji.set(r52, false);
            this.titleView.setLeftDrawable(this.botVerificationEmoji);
            this.statusEmoji.set(r7, false);
            this.titleView.setRightDrawable(this.statusEmoji);
            if (this.isChannel == false) goto L47;
            long r3 = -r24;
            TLRPC.Chat r13 = MessagesController.getInstance(r23).getChat(Long.valueOf(r3));
            TLRPC.ChatFull r2 = MessagesController.getInstance(r23).getChatFull(r3);
            if (r2 != null) goto L25;
        L30:
            if (r13 != null) goto L32;
        L37:
            boolean r25 = ChatObject.isPublic(r13);
            boolean r14 = ChatObject.isChannelAndNotMegaGroup(r13);
            SimpleTextView r32 = this.subtitleView;
            if (r14 == false) goto L43;
            if (r25 == false) goto L41;
            int r15 = R.string.ChannelPublic;
        L42:
            r32.setText(LocaleController.getString(r15).toLowerCase());
        L48:
            setWillNotDraw(false);
            return;
        L41:
            r15 = R.string.ChannelPrivate;
            goto L42
        L43:
            if (r25 == false) goto L45;
            int r16 = R.string.MegaPublic;
        L46:
            r32.setText(LocaleController.getString(r16).toLowerCase());
            goto L48
        L45:
            r16 = R.string.MegaPrivate;
            goto L46
        L32:
            if (r13.participants_count <= 0) goto L37;
            boolean r27 = ChatObject.isChannelAndNotMegaGroup(r13);
            SimpleTextView r53 = this.subtitleView;
            if (r27 == false) goto L36;
            r53.setText(LocaleController.formatPluralStringComma("Subscribers", r13.participants_count));
            goto L48
        L36:
            r53.setText(LocaleController.formatPluralStringComma("Members", r13.participants_count));
            goto L48
        L25:
            if (r2.participants_count <= 0) goto L30;
            boolean r17 = ChatObject.isChannelAndNotMegaGroup(r13);
            SimpleTextView r54 = this.subtitleView;
            if (r17 == false) goto L29;
            r54.setText(LocaleController.formatPluralStringComma("Subscribers", r2.participants_count));
            goto L48
        L29:
            r54.setText(LocaleController.formatPluralStringComma("Members", r2.participants_count));
            goto L48
        L47:
            this.subtitleView.setText(LocaleController.getString(R.string.Online));
            goto L48
        L11:
            r92 = r1.title;
            goto L12
        L15:
            TLRPC.User r18 = UserConfig.getInstance(r23).getCurrentUser();
            r92 = UserObject.getUserName(r18);
            r6.setInfo(r23, r18);
            r5.setForUserOrChat(r18, r6);
            r52 = DialogObject.getBotVerificationIcon(r18);
            if (r18 == null) goto L51;
            r7 = DialogObject.getEmojiStatusDocumentId(r18.emoji_status);
            goto L51
        L5:
            r9 = false;
            goto L6
        }

        private int getThemedColor(int r1) {
            return Theme.getColor(r1, this.resourcesProvider);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r23) {
            this.rectF.set((getWidth() - AndroidUtilities.dp(86.0f)) / 2.0f, getHeight() - AndroidUtilities.dp(168.0f), (getWidth() + AndroidUtilities.dp(86.0f)) / 2.0f, getHeight() - AndroidUtilities.dp(82.0f));
            StarGiftPatterns.drawProfileAnimatedPattern(r23, this.emoji, getWidth(), getHeight(), 1.0f, this.rectF, 1.0f);
            this.imageReceiver.setRoundRadius(ExteraConfig.getAvatarCorners(this.rectF.width(), true, this.isForum));
            this.imageReceiver.setImageCoords(this.rectF);
            this.imageReceiver.draw(r23);
            float r1 = (this.rectF.width() / 2.0f) + AndroidUtilities.dp(4.0f);
            float r2 = ExteraConfig.getAvatarCorners(2.0f * r1, true, this.isForum);
            r23.drawRoundRect(this.rectF.centerX() - r1, this.rectF.centerY() - r1, this.rectF.centerX() + r1, this.rectF.centerY() + r1, r2, r2, this.storyGradient.getPaint(this.rectF));
            super.dispatchDraw(r23);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.emoji.attach();
            this.imageReceiver.onAttachedToWindow();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.emoji.detach();
            this.imageReceiver.onDetachedFromWindow();
        }

        public void overrideAvatarColor(int r4) {
            if (r4 < 14) goto L13;
            MessagesController r0 = MessagesController.getInstance(UserConfig.selectedAccount);
            MessagesController.PeerColor r1 = null;
            if (r0 == null) goto L7;
            MessagesController.PeerColors r02 = r0.peerColors;
        L8:
            if (r02 == null) goto L10;
            r1 = r02.getColor(r4);
        L10:
            if (r1 == null) goto L12;
            int r42 = r1.getColor1();
            int r03 = getThemedColor(Theme.keys_avatar_background[AvatarDrawable.getPeerColorIndex(r42)]);
            int r43 = getThemedColor(Theme.keys_avatar_background2[AvatarDrawable.getPeerColorIndex(r42)]);
        L14:
            this.avatarDrawable.setColor(r03, r43);
            invalidate();
            return;
        L12:
            long r12 = r4;
            r03 = getThemedColor(Theme.keys_avatar_background[AvatarDrawable.getColorIndex(r12)]);
            r43 = getThemedColor(Theme.keys_avatar_background2[AvatarDrawable.getColorIndex(r12)]);
            goto L14
        L7:
            r02 = null;
            goto L8
        L13:
            long r13 = r4;
            r03 = getThemedColor(Theme.keys_avatar_background[AvatarDrawable.getColorIndex(r13)]);
            r43 = getThemedColor(Theme.keys_avatar_background2[AvatarDrawable.getColorIndex(r13)]);
            goto L14
        }

        public void setColor(int r2, boolean r3) {
            MessagesController.PeerColors r0 = MessagesController.getInstance(this.currentAccount).profilePeerColors;
            if (r0 != null) goto L5;
            MessagesController.PeerColor r22 = null;
        L6:
            setColor(r22, r3);
            return;
        L5:
            r22 = r0.getColor(r2);
            goto L6
        }

        public void setColor(MessagesController.PeerColor r7, boolean r8) {
            this.peerColor = r7;
            Theme.ResourcesProvider r0 = this.resourcesProvider;
            if (r0 == null) goto L5;
            boolean r02 = r0.isDark();
        L7:
            if (r7 == null) goto L17;
            int r2 = r7.patternColor;
            AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r3 = this.emoji;
            if (r2 == 0) goto L11;
            r3.setColor(Integer.valueOf(r2));
        L12:
            this.statusEmoji.setColor(Integer.valueOf(ColorUtils.blendARGB(r7.getStoryColor1(Theme.isCurrentThemeDark()), -1, 0.25f)));
            this.botVerificationEmoji.setColor(Integer.valueOf(ColorUtils.blendARGB(r7.getStoryColor1(Theme.isCurrentThemeDark()), -1, 0.25f)));
            int r1 = ColorUtils.blendARGB(r7.getStoryColor1(r02), r7.getStoryColor2(r02), 0.5f);
            int r22 = Theme.key_actionBarDefault;
            boolean r32 = Theme.hasHue(getThemedColor(r22));
            SimpleTextView r5 = this.subtitleView;
            if (r32 == true) goto L15;
            r5.setTextColor(r1);
        L16:
            this.titleView.setTextColor(-1);
        L25:
            this.storyGradient.setColor(r7, r8);
            invalidate();
            return;
        L15:
            r5.setTextColor(Theme.changeColorAccent(getThemedColor(r22), r1, getThemedColor(Theme.key_avatar_subtitleInProfileBlue), r02, r1));
            goto L16
        L11:
            r3.setColor(Integer.valueOf(PeerColorActivity.adaptProfileEmojiColor(r7.getBgColor1(r02))));
            goto L12
        L17:
            int r03 = Theme.key_actionBarDefault;
            if (AndroidUtilities.computePerceivedBrightness(getThemedColor(r03)) <= 0.8f) goto L20;
            this.emoji.setColor(Integer.valueOf(getThemedColor(Theme.key_windowBackgroundWhiteBlueText)));
        L24:
            AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r04 = this.statusEmoji;
            int r12 = Theme.key_profile_verifiedBackground;
            r04.setColor(Integer.valueOf(Theme.getColor(r12, this.resourcesProvider)));
            this.botVerificationEmoji.setColor(Integer.valueOf(Theme.getColor(r12, this.resourcesProvider)));
            this.subtitleView.setTextColor(getThemedColor(Theme.key_actionBarDefaultSubtitle));
            this.titleView.setTextColor(getThemedColor(Theme.key_actionBarDefaultTitle));
            goto L25
        L20:
            float r23 = AndroidUtilities.computePerceivedBrightness(getThemedColor(r03));
            AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r33 = this.emoji;
            if (r23 >= 0.2f) goto L23;
            r33.setColor(Integer.valueOf(Theme.multAlpha(getThemedColor(Theme.key_actionBarDefaultTitle), 0.5f)));
            goto L24
        L23:
            r33.setColor(Integer.valueOf(PeerColorActivity.adaptProfileEmojiColor(getThemedColor(r03))));
            goto L24
        L5:
            r02 = Theme.isCurrentThemeDark();
            goto L7
        }

        public void setEmoji(long r5, boolean r7, boolean r8) {
            AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r1 = this.emoji;
            if (r5 != 0) goto L5;
            r1.set(null, r8);
        L6:
            Theme.ResourcesProvider r52 = this.resourcesProvider;
            if (r52 == null) goto L9;
            boolean r53 = r52.isDark();
        L10:
            MessagesController.PeerColor r6 = this.peerColor;
            if (r6 == null) goto L16;
            int r12 = r6.patternColor;
            AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r2 = this.emoji;
            if (r12 == 0) goto L15;
            r2.setColor(Integer.valueOf(r12));
        L23:
            MessagesController.PeerColor r62 = this.peerColor;
            AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r13 = this.statusEmoji;
            if (r62 == null) goto L31;
            int r63 = r62.getColor(1, this.resourcesProvider);
            boolean r54 = this.peerColor.hasColor6(r53);
            MessagesController.PeerColor r22 = this.peerColor;
            if (r54 == false) goto L29;
            int r55 = 4;
        L30:
            r13.setColor(Integer.valueOf(ColorUtils.blendARGB(r63, r22.getColor(r55, this.resourcesProvider), 0.5f)));
        L32:
            this.isEmojiCollectible = r7;
            if (r8 == true) goto L35;
            this.emojiCollectible.force(r7);
        L35:
            invalidate();
            return;
        L29:
            r55 = 2;
            goto L30
        L31:
            r13.setColor(Integer.valueOf(Theme.getColor(Theme.key_profile_verifiedBackground, this.resourcesProvider)));
            goto L32
        L15:
            r2.setColor(Integer.valueOf(PeerColorActivity.adaptProfileEmojiColor(r6.getBgColor1(r53))));
            goto L23
        L16:
            int r64 = Theme.key_actionBarDefault;
            if (AndroidUtilities.computePerceivedBrightness(getThemedColor(r64)) <= 0.8f) goto L19;
            this.emoji.setColor(Integer.valueOf(getThemedColor(Theme.key_windowBackgroundWhiteBlueText)));
            goto L23
        L19:
            float r14 = AndroidUtilities.computePerceivedBrightness(getThemedColor(r64));
            AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r23 = this.emoji;
            if (r14 >= 0.2f) goto L22;
            r23.setColor(Integer.valueOf(Theme.multAlpha(Theme.getColor(Theme.key_actionBarDefaultTitle), 0.5f)));
            goto L23
        L22:
            r23.setColor(Integer.valueOf(PeerColorActivity.adaptProfileEmojiColor(Theme.getColor(r64))));
            goto L23
        L9:
            r53 = Theme.isCurrentThemeDark();
            goto L10
        L5:
            r1.set(r5, r8);
            goto L6
        }

        public void setForum(boolean r2) {
            if (this.isForum == r2) goto L5;
            invalidate();
        L5:
            this.isForum = r2;
        }

        public void setStatusEmoji(long r2, boolean r4, boolean r5) {
            this.statusEmoji.set(r2, r5);
            this.statusEmoji.setParticles(r4, r5);
            Theme.ResourcesProvider r22 = this.resourcesProvider;
            if (r22 == null) goto L5;
            boolean r23 = r22.isDark();
        L6:
            MessagesController.PeerColor r3 = this.peerColor;
            AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r42 = this.statusEmoji;
            if (r3 == null) goto L14;
            int r32 = r3.getColor2(r23);
            boolean r52 = this.peerColor.hasColor6(r23);
            MessagesController.PeerColor r1 = this.peerColor;
            if (r52 == false) goto L11;
            int r12 = r1.getColor5(r23);
        L12:
            r42.setColor(Integer.valueOf(ColorUtils.blendARGB(r32, r12, 0.5f)));
            return;
        L11:
            r12 = r1.getColor3(r23);
            goto L12
        L14:
            r42.setColor(Integer.valueOf(Theme.getColor(Theme.key_profile_verifiedBackground, this.resourcesProvider)));
            return;
        L5:
            r23 = Theme.isCurrentThemeDark();
            goto L6
        }
    }

    public class Tabs extends FrameLayout {
        private final RecyclerView.Adapter<RecyclerView.ViewHolder> adapter;
        private final AnimatedFloat animatedSelected;
        private Drawable backgroundDrawableGlass;
        private Drawable backgroundDrawableNoGlass;
        private final Paint backgroundPaint;
        private final RectF ceiledRect;
        private Path clipPath;
        private boolean drawInList;
        private Runnable externalInvalidate;
        private final RectF flooredRect;
        private int lastId;
        private final LinearLayoutManager layoutManager;
        private final RecyclerListView listView;
        private int selected;
        private int selectedColor;
        private final Paint selectedPaint;
        private final RectF selectedRect;
        private int selectedTextColor;
        private final ArrayList<CharSequence> tabs;
        private int textColor;
        final /* synthetic */ PeerColorActivity this$0;
        private Utilities.Callback<Integer> whenTabSelected;

        /* renamed from: org.telegram.ui.PeerColorActivity$Tabs$1, reason: invalid class name */
        public class AnonymousClass1 extends RecyclerListView {
            final /* synthetic */ Tabs this$1;
            final /* synthetic */ PeerColorActivity val$this$0;

            public AnonymousClass1(Tabs r1, Context r2, Theme.ResourcesProvider r3, PeerColorActivity r4) {
                this.this$1 = r1;
                this.val$this$0 = r4;
                super(r2, r3);
            }

            @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r4) {
                if (Tabs.m16767$$Nest$mupdateSelectedRect(this.this$1) == true) goto L6;
                super.dispatchDraw(r4);
                return;
            L6:
                Tabs.m16760$$Nest$fgetselectedPaint(this.this$1).setColor(Tabs.m16759$$Nest$fgetselectedColor(this.this$1));
                float r0 = Tabs.m16761$$Nest$fgetselectedRect(this.this$1).height() / 2.0f;
                r4.drawRoundRect(Tabs.m16761$$Nest$fgetselectedRect(this.this$1), r0, r0, Tabs.m16760$$Nest$fgetselectedPaint(this.this$1));
                super.dispatchDraw(r4);
            }

            @Override // android.view.View
            public void invalidate() {
                super.invalidate();
                if (Tabs.m16757$$Nest$fgetexternalInvalidate(this.this$1) == null) goto L6;
                Tabs.m16757$$Nest$fgetexternalInvalidate(this.this$1).run();
                return;
            }
        }

        /* renamed from: org.telegram.ui.PeerColorActivity$Tabs$2, reason: invalid class name */
        public class AnonymousClass2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
            final /* synthetic */ Tabs this$1;
            final /* synthetic */ PeerColorActivity val$this$0;

            public AnonymousClass2(Tabs r1, PeerColorActivity r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                return Tabs.m16763$$Nest$fgettabs(this.this$1).size();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(RecyclerView.ViewHolder r2, int r3) {
                TextView r22 = (TextView) r2.itemView;
                r22.setText((CharSequence) Tabs.m16763$$Nest$fgettabs(this.this$1).get(r3));
                int r0 = Tabs.m16758$$Nest$fgetselected(this.this$1);
                Tabs r1 = this.this$1;
                if (r3 != r0) goto L5;
                int r12 = Tabs.m16762$$Nest$fgetselectedTextColor(r1);
            L6:
                r22.setTextColor(r12);
                return;
            L5:
                r12 = Tabs.m16764$$Nest$fgettextColor(r1);
                goto L6
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
                LinkSpanDrawable.LinksTextView r2 = new LinkSpanDrawable.LinksTextView(r3.getContext());
                r2.setGravity(17);
                r2.setTypeface(AndroidUtilities.bold());
                r2.setTextSize(1, 14.0f);
                r2.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
                r2.setEllipsize(TextUtils.TruncateAt.END);
                r2.setSingleLine();
                r2.setMaxLines(1);
                r2.setLayoutParams(new RecyclerView.LayoutParams(-2, AndroidUtilities.dp(28.0f)));
                ScaleStateListAnimator.apply(r2, 0.075f, 1.4f);
                return new RecyclerListView.Holder(r2);
            }
        }

        public static /* synthetic */ void $r8$lambda$8H4otQClxHs90OPfyxpdqfknFOI(Tabs r0, View r1, int r2) {
            r0.lambda$new$0(r1, r2);
        }

        /* renamed from: -$$Nest$fgetexternalInvalidate, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m16757$$Nest$fgetexternalInvalidate(Tabs r0) {
            return r0.externalInvalidate;
        }

        /* renamed from: -$$Nest$fgetselected, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m16758$$Nest$fgetselected(Tabs r0) {
            return r0.selected;
        }

        /* renamed from: -$$Nest$fgetselectedColor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m16759$$Nest$fgetselectedColor(Tabs r0) {
            return r0.selectedColor;
        }

        /* renamed from: -$$Nest$fgetselectedPaint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Paint m16760$$Nest$fgetselectedPaint(Tabs r0) {
            return r0.selectedPaint;
        }

        /* renamed from: -$$Nest$fgetselectedRect, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RectF m16761$$Nest$fgetselectedRect(Tabs r0) {
            return r0.selectedRect;
        }

        /* renamed from: -$$Nest$fgetselectedTextColor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m16762$$Nest$fgetselectedTextColor(Tabs r0) {
            return r0.selectedTextColor;
        }

        /* renamed from: -$$Nest$fgettabs, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m16763$$Nest$fgettabs(Tabs r0) {
            return r0.tabs;
        }

        /* renamed from: -$$Nest$fgettextColor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m16764$$Nest$fgettextColor(Tabs r0) {
            return r0.textColor;
        }

        /* renamed from: -$$Nest$fputbackgroundDrawableGlass, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16765$$Nest$fputbackgroundDrawableGlass(Tabs r0, Drawable r1) {
            r0.backgroundDrawableGlass = r1;
        }

        /* renamed from: -$$Nest$fputbackgroundDrawableNoGlass, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16766$$Nest$fputbackgroundDrawableNoGlass(Tabs r0, Drawable r1) {
            r0.backgroundDrawableNoGlass = r1;
        }

        /* renamed from: -$$Nest$mupdateSelectedRect, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m16767$$Nest$mupdateSelectedRect(Tabs r0) {
            return r0.updateSelectedRect();
        }

        public Tabs(PeerColorActivity r9, Context r10, Theme.ResourcesProvider r11) {
            this.this$0 = r9;
            super(r10);
            this.tabs = new ArrayList();
            this.flooredRect = new RectF();
            this.ceiledRect = new RectF();
            this.selectedRect = new RectF();
            this.backgroundPaint = new Paint(1);
            this.selectedPaint = new Paint(1);
            this.clipPath = new Path();
            this.lastId = Integer.MIN_VALUE;
            AnonymousClass1 r2 = new AnonymousClass1(this, r10, r11, r9);
            this.listView = r2;
            r2.setClipToPadding(false);
            r2.setClipChildren(false);
            r2.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f));
            r2.setOverScrollMode(2);
            r2.setHorizontalScrollBarEnabled(false);
            r2.setItemAnimator(null);
            LinearLayoutManager r0 = new LinearLayoutManager(r10, 0, false);
            this.layoutManager = r0;
            r2.setLayoutManager(r0);
            AnonymousClass2 r102 = new AnonymousClass2(this, r9);
            this.adapter = r102;
            r2.setAdapter(r102);
            r2.setOnItemClickListener(new PeerColorActivity$Tabs$$ExternalSyntheticLambda0(this));
            addView(r2, LayoutHelper.createFrame(-1, -1, 119));
            setHorizontalScrollBarEnabled(false);
            setClipToPadding(false);
            setClipChildren(false);
            this.animatedSelected = new AnimatedFloat(r2, 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
        }

        private /* synthetic */ void lambda$new$0(View r1, int r2) {
            setSelected(r2, true);
            Utilities.Callback<Integer> r0 = this.whenTabSelected;
            if (r0 == null) goto L6;
            r0.run(Integer.valueOf(r2));
            return;
        }

        private void setBounds(RectF r3, View r4) {
            r3.set(r4.getLeft(), r4.getTop(), r4.getRight(), r4.getBottom());
        }

        private boolean updateSelectedRect() {
            if (this.tabs.isEmpty() == false) goto L5;
            return false;
        L5:
            float r0 = this.animatedSelected.set(this.selected);
            double r2 = r0;
            int r4 = Utilities.clamp((int) Math.floor(r2), this.tabs.size() - 1, 0);
            int r22 = Utilities.clamp((int) Math.ceil(r2), this.tabs.size() - 1, 0);
            View r3 = this.layoutManager.findViewByPosition(r4);
            View r23 = this.layoutManager.findViewByPosition(r22);
            if (r3 != null) goto L9;
            if (r23 != null) goto L9;
            return false;
        L9:
            RectF r1 = this.flooredRect;
            if (r3 == null) goto L12;
            View r5 = r3;
        L13:
            setBounds(r1, r5);
            RectF r12 = this.ceiledRect;
            if (r23 == null) goto L16;
            r3 = r23;
        L16:
            setBounds(r12, r3);
            AndroidUtilities.lerp(this.flooredRect, this.ceiledRect, r0 - r4, this.selectedRect);
            return true;
        L12:
            r5 = r23;
            goto L13
        }

        private void updateTabTextColor(int r4) {
            View r0 = this.layoutManager.findViewByPosition(r4);
            if ((r0 instanceof TextView) == false) goto L10;
            TextView r1 = (TextView) r0;
            if (r4 != this.selected) goto L7;
            int r3 = this.selectedTextColor;
        L8:
            r1.setTextColor(r3);
            r0.invalidate();
            return;
        L7:
            r3 = this.textColor;
            goto L8
        }

        private void updateVisibleTabTextColors() {
            int r0 = 0;
        L4:
            if (r0 >= this.listView.getChildCount()) goto L15;
            View r1 = this.listView.getChildAt(r0);
            int r2 = this.listView.getChildAdapterPosition(r1);
            if (r2 == (-1)) goto L14;
            if ((r1 instanceof TextView) == false) goto L14;
            TextView r3 = (TextView) r1;
            if (r2 != this.selected) goto L12;
            int r22 = this.selectedTextColor;
        L13:
            r3.setTextColor(r22);
            r1.invalidate();
            goto L14
        L12:
            r22 = this.textColor;
        L14:
            r0 = r0 + 1;
            goto L4
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r5) {
            DrawableUtils.setBoundsIncreasePadding(this.backgroundDrawableGlass, 0, 0, getWidth(), getHeight());
            DrawableUtils.setBoundsIncreasePadding(this.backgroundDrawableNoGlass, 0, 0, getWidth(), getHeight());
            if (this.drawInList == false) goto L5;
            Drawable r0 = this.backgroundDrawableNoGlass;
        L6:
            r0.draw(r5);
            r5.save();
            r5.clipPath(this.clipPath);
            super.dispatchDraw(r5);
            r5.restore();
            return;
        L5:
            r0 = this.backgroundDrawableGlass;
            goto L6
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }

        @Override // android.view.View
        public void onSizeChanged(int r9, int r10, int r11, int r12) {
            super.onSizeChanged(r9, r10, r11, r12);
            int r112 = AndroidUtilities.dp(3.0f);
            this.clipPath.rewind();
            float r1 = r112;
            this.clipPath.addRoundRect(r1, r1, r9 - r112, r10 - r112, AndroidUtilities.dp(18.0f) - r112, AndroidUtilities.dp(18.0f) - r112, Path.Direction.CW);
        }

        public void set(int r2, ArrayList<CharSequence> r3, int r4, Utilities.Callback<Integer> r5) {
            if (this.lastId != r2) goto L5;
            boolean r0 = true;
        L6:
            this.lastId = r2;
            this.whenTabSelected = r5;
            this.tabs.clear();
            this.tabs.addAll(r3);
            this.adapter.notifyDataSetChanged();
            setSelected(r4, r0);
            return;
        L5:
            r0 = false;
            goto L6
        }

        public void setColors(int r1, int r2, int r3, int r4) {
            this.backgroundPaint.setColor(r1);
            this.selectedColor = Theme.multAlpha(r4, 0.06f);
            this.textColor = r3;
            this.selectedTextColor = r4;
            updateVisibleTabTextColors();
            this.listView.invalidate();
            invalidate();
        }

        public void setDrawInList(boolean r2) {
            if (this.drawInList == r2) goto L6;
            this.drawInList = r2;
            PeerColorActivity.m16702x22c1fec(this.this$0);
            invalidate();
            return;
        }

        public void setExternalInvalidate(Runnable r1) {
            this.externalInvalidate = r1;
        }

        public void setSelected(int r5, boolean r6) {
            int r0 = this.selected;
            this.selected = r5;
            if (r6 == true) goto L5;
            this.animatedSelected.set(r5, true);
        L5:
            updateTabTextColor(r0);
            if (r5 == r0) goto L9;
            updateTabTextColor(r5);
        L9:
            if (this.tabs.isEmpty() == true) goto L14;
            int r52 = Utilities.clamp(r5, this.tabs.size() - 1, 0);
            RecyclerListView r02 = this.listView;
            if (r6 == false) goto L13;
            r02.smoothScrollToPosition(r52);
            goto L14
        L13:
            r02.scrollToPosition(r52);
        L14:
            this.listView.invalidate();
        }
    }

    public static /* synthetic */ void $r8$lambda$BYzZJchiyq29WI1BlHQqbo7_xgA(PeerColorActivity r0, AlertDialog r1, int r2) {
        r0.lambda$showUnsavedAlert$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Bo19yxq4L4YPYzQxQi5bQTJeQdc(PeerColorActivity r0, boolean[] r1, TL_stars.TL_starGiftUnique r2, long r3, Utilities.Callback r5, StarGiftSheet.PaymentFormState r6, Browser.Progress r7) {
        r0.lambda$buy$8(r1, r2, r3, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$ChROuf2QzXLsOze_5rnU0ObnQpY(PeerColorActivity r0, int r1) {
        r0.invalidateMergedVisibleBlurredPositionsAndSourcesImpl(r1);
    }

    public static /* synthetic */ void $r8$lambda$EJJWxiMyr1tDckZ2sRT08aZBq0c(boolean[] r0, Utilities.Callback r1, DialogInterface r2) {
        if (r0[0] == true) goto L9;
        if (r1 == null) goto L8;
        r1.run(Boolean.FALSE);
        return;
    L8:
        return;
    }

    public static /* synthetic */ void $r8$lambda$EyUdfqKrIYwTfnf4NQ4Z1g2AqJM(PeerColorActivity r0, View r1) {
        r0.lambda$createButton$3(r1);
    }

    /* renamed from: $r8$lambda$FWyoiq_aIdpvO3jKdmdxyWLc-iQ, reason: not valid java name */
    public static /* synthetic */ WindowInsetsCompat m16666$r8$lambda$FWyoiq_aIdpvO3jKdmdxyWLciQ(PeerColorActivity r0, View r1, WindowInsetsCompat r2) {
        return r0.onApplyWindowInsets(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Guv5Zt7FrMcdkYLRn9bf3SXbxWg(PeerColorActivity r0) {
        r0.invalidateMergedVisibleBlurredPositionsAndSourcesPositions();
    }

    public static /* synthetic */ void $r8$lambda$I99zNmZDltIM9fAJHYx2Yuxs6k8(PeerColorActivity r0, AlertDialog r1, int r2) {
        r0.lambda$showUnsavedAlert$5(r1, r2);
    }

    /* renamed from: $r8$lambda$IxF1gi3b1_M-30guT81tJ0Gf-E0, reason: not valid java name */
    public static /* synthetic */ void m16667$r8$lambda$IxF1gi3b1_M30guT81tJ0GfE0(PeerColorActivity r0, Integer r1) {
        r0.lambda$createView$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$LSdMWw85NBDXOwDAyY1hozkfXYw(PeerColorActivity r0, Canvas r1, RectF r2) {
        r0.drawList(r1, r2);
    }

    /* renamed from: $r8$lambda$MUGXXOz6CfS44ix2yifxeNxzZ-Y, reason: not valid java name */
    public static /* synthetic */ void m16668$r8$lambda$MUGXXOz6CfS44ix2yifxeNxzZY(PeerColorActivity r0, View r1) {
        r0.lambda$createView$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$RmX_I6_CqZd9knyLYaPU4ZvyjM8(PeerColorActivity r0) {
        r0.updateColors();
    }

    /* renamed from: $r8$lambda$XE2GqWzbGG0ms-uL2O7thG3S_bk, reason: not valid java name */
    public static /* synthetic */ void m16669$r8$lambda$XE2GqWzbGG0msuL2O7thG3S_bk(PeerColorActivity r0, AmountUtils$Currency r1, TL_stars.TL_starGiftUnique r2, long r3, Utilities.Callback r5, TLRPC.TL_payments_paymentFormStarGift r6) {
        r0.lambda$buy$10(r1, r2, r3, r5, r6);
    }

    /* renamed from: $r8$lambda$citDSR-4cwl1UbmuZ9yjcB4kdLs, reason: not valid java name */
    public static /* synthetic */ boolean m16670$r8$lambda$citDSR4cwl1UbmuZ9yjcB4kdLs(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$d7Oc1A168OTGq7axD6i4fBRbCHc(PeerColorActivity r0, View r1) {
        r0.lambda$createView$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$jmZbMhWCo__j_UUOv_oIYIXP438(PeerColorActivity r0, Boolean r1) {
        r0.lambda$buttonClick$6(r1);
    }

    /* renamed from: $r8$lambda$kEWBPmwVhZw59_D81fV5TG-vMTs, reason: not valid java name */
    public static /* synthetic */ void m16671$r8$lambda$kEWBPmwVhZw59_D81fV5TGvMTs(Browser.Progress r0, Utilities.Callback r1, Boolean r2, String r3) {
        r0.end();
        if (r1 == null) goto L6;
        r1.run(r2);
        return;
    }

    public static /* synthetic */ void $r8$lambda$zpWCZ0pjuQdj6G8KVyhnMXXVxu4(PeerColorActivity r0) {
        r0.lambda$toggleTheme$12();
    }

    /* renamed from: -$$Nest$fgetactionBarContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m16672$$Nest$fgetactionBarContainer(PeerColorActivity r0) {
        return r0.actionBarContainer;
    }

    /* renamed from: -$$Nest$fgetbackButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m16673$$Nest$fgetbackButton(PeerColorActivity r0) {
        return r0.backButton;
    }

    /* renamed from: -$$Nest$fgetbutton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ButtonWithCounterView m16674$$Nest$fgetbutton(PeerColorActivity r0) {
        return r0.button;
    }

    /* renamed from: -$$Nest$fgetchangeDayNightView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m16675$$Nest$fgetchangeDayNightView(PeerColorActivity r0) {
        return r0.changeDayNightView;
    }

    /* renamed from: -$$Nest$fgetchangeDayNightViewProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m16676$$Nest$fgetchangeDayNightViewProgress(PeerColorActivity r0) {
        return r0.changeDayNightViewProgress;
    }

    /* renamed from: -$$Nest$fgetcolorBar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ColoredActionBar m16677$$Nest$fgetcolorBar(PeerColorActivity r0) {
        return r0.colorBar;
    }

    /* renamed from: -$$Nest$fgetcurrentColors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SparseIntArray m16678$$Nest$fgetcurrentColors(PeerColorActivity r0) {
        return r0.currentColors;
    }

    /* renamed from: -$$Nest$fgetdayNightItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m16679$$Nest$fgetdayNightItem(PeerColorActivity r0) {
        return r0.dayNightItem;
    }

    /* renamed from: -$$Nest$fgetdialogId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m16680$$Nest$fgetdialogId(PeerColorActivity r2) {
        return r2.dialogId;
    }

    /* renamed from: -$$Nest$fgetgifts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StarsController.GiftsList m16681$$Nest$fgetgifts(PeerColorActivity r0) {
        return r0.gifts;
    }

    /* renamed from: -$$Nest$fgetgiftsWithPeerColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StarsController.GiftsList m16682$$Nest$fgetgiftsWithPeerColor(PeerColorActivity r0) {
        return r0.giftsWithPeerColor;
    }

    /* renamed from: -$$Nest$fgetiBlur3BackgroundFactory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundDrawableViewFactory m16683$$Nest$fgetiBlur3BackgroundFactory(PeerColorActivity r0) {
        return r0.iBlur3BackgroundFactory;
    }

    /* renamed from: -$$Nest$fgetiBlur3ButtonFactory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundDrawableViewFactory m16684$$Nest$fgetiBlur3ButtonFactory(PeerColorActivity r0) {
        return r0.iBlur3ButtonFactory;
    }

    /* renamed from: -$$Nest$fgetisChannel, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16685$$Nest$fgetisChannel(PeerColorActivity r0) {
        return r0.isChannel;
    }

    /* renamed from: -$$Nest$fgetisDark, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16686$$Nest$fgetisDark(PeerColorActivity r0) {
        return r0.isDark;
    }

    /* renamed from: -$$Nest$fgetmsgInDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageDrawable m16687$$Nest$fgetmsgInDrawable(PeerColorActivity r0) {
        return r0.msgInDrawable;
    }

    /* renamed from: -$$Nest$fgetmsgInDrawableSelected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageDrawable m16688$$Nest$fgetmsgInDrawableSelected(PeerColorActivity r0) {
        return r0.msgInDrawableSelected;
    }

    /* renamed from: -$$Nest$fgetparentResourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m16689$$Nest$fgetparentResourcesProvider(PeerColorActivity r0) {
        return r0.parentResourcesProvider;
    }

    /* renamed from: -$$Nest$fgetscrollableViewNoiseSuppressor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DownscaleScrollableNoiseSuppressor m16690$$Nest$fgetscrollableViewNoiseSuppressor(PeerColorActivity r0) {
        return r0.scrollableViewNoiseSuppressor;
    }

    /* renamed from: -$$Nest$fgettabsPinOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16691$$Nest$fgettabsPinOffset(PeerColorActivity r0) {
        return r0.tabsPinOffset;
    }

    /* renamed from: -$$Nest$fgettabsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FilledTabsView m16692$$Nest$fgettabsView(PeerColorActivity r0) {
        return r0.tabsView;
    }

    /* renamed from: -$$Nest$fgetviewPager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewPagerFixed m16693$$Nest$fgetviewPager(PeerColorActivity r0) {
        return r0.viewPager;
    }

    /* renamed from: -$$Nest$fputchangeDayNightView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16694$$Nest$fputchangeDayNightView(PeerColorActivity r0, View r1) {
        r0.changeDayNightView = r1;
    }

    /* renamed from: -$$Nest$fputchangeDayNightViewAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16695$$Nest$fputchangeDayNightViewAnimator(PeerColorActivity r0, ValueAnimator r1) {
        r0.changeDayNightViewAnimator = r1;
    }

    /* renamed from: -$$Nest$fputchangeDayNightViewProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16696$$Nest$fputchangeDayNightViewProgress(PeerColorActivity r0, float r1) {
        r0.changeDayNightViewProgress = r1;
    }

    /* renamed from: -$$Nest$mapplyButtonState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16697$$Nest$mapplyButtonState(PeerColorActivity r0, Page r1, boolean r2) {
        r0.applyButtonState(r1, r2);
    }

    /* renamed from: -$$Nest$mcheckPagesColors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16698$$Nest$mcheckPagesColors(PeerColorActivity r0) {
        r0.checkPagesColors();
    }

    /* renamed from: -$$Nest$mgetButtonPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Page m16699$$Nest$mgetButtonPage(PeerColorActivity r0) {
        return r0.getButtonPage();
    }

    /* renamed from: -$$Nest$mgetTabProgressFromPageProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m16700$$Nest$mgetTabProgressFromPageProgress(PeerColorActivity r0, float r1) {
        return r0.getTabProgressFromPageProgress(r1);
    }

    /* renamed from: -$$Nest$minvalidateMergedVisibleBlurredPositionsAndSources, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16701$$Nest$minvalidateMergedVisibleBlurredPositionsAndSources(PeerColorActivity r0, int r1) {
        r0.invalidateMergedVisibleBlurredPositionsAndSources(r1);
    }

    /* renamed from: -$$Nest$minvalidateMergedVisibleBlurredPositionsAndSourcesPositions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16702x22c1fec(PeerColorActivity r0) {
        r0.invalidateMergedVisibleBlurredPositionsAndSourcesPositions();
    }

    /* renamed from: -$$Nest$mupdateButtonState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16703$$Nest$mupdateButtonState(PeerColorActivity r0, boolean r1) {
        r0.updateButtonState(r1);
    }

    /* renamed from: -$$Nest$mupdateTabColors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16704$$Nest$mupdateTabColors(PeerColorActivity r0) {
        r0.updateTabColors();
    }

    public PeerColorActivity(long r10) {
        this.tabsPinOffset = AndroidUtilities.dp(6.0f);
        this.currentColors = new SparseIntArray();
        boolean r0 = Theme.isCurrentThemeDark();
        this.isDark = r0;
        this.forceDark = r0;
        this.insets = Insets.NONE;
        ReferenceList<View> r02 = new ReferenceList();
        this.glassAttachedViews = r02;
        ReferenceList<BlurredBackgroundDrawable> r1 = new ReferenceList();
        this.glassAttachedDrawables = r1;
        this.iBlur3Capture = new PeerColorActivity$$ExternalSyntheticLambda8(this);
        this.glassDrawablesPositions = new ArrayList();
        this.glassDrawablesPositionsMerged = new ArrayList();
        BlurredBackgroundSourceColor r2 = new BlurredBackgroundSourceColor();
        this.iBlur3SourceButtonColor = r2;
        BlurredBackgroundDrawableViewFactory r3 = new BlurredBackgroundDrawableViewFactory(r2);
        this.iBlur3ButtonFactory = r3;
        BlurredBackgroundSourceColor r4 = new BlurredBackgroundSourceColor();
        this.iBlur3SourceBackgroundColor = r4;
        BlurredBackgroundDrawableViewFactory r5 = new BlurredBackgroundDrawableViewFactory(r4);
        this.iBlur3BackgroundFactory = r5;
        if (Build.VERSION.SDK_INT >= 31) goto L5;
    L7:
        this.scrollableViewNoiseSuppressor = null;
        this.glassBackgroundSourceRenderNode = null;
        this.glassBackgroundDrawableFactory = new BlurredBackgroundDrawableViewFactory(r2);
    L8:
        r3.setLinkedViewsRef(r02);
        r3.setLinkedDrawablesRef(r1);
        r5.setLinkedViewsRef(r02);
        r5.setLinkedDrawablesRef(r1);
        this.glassBackgroundDrawableFactory.setLinkedViewsRef(r02);
        this.glassBackgroundDrawableFactory.setLinkedDrawablesRef(r1);
        this.dialogId = r10;
        if (r10 == 0) goto L11;
        boolean r32 = true;
    L12:
        this.isChannel = r32;
        if (r10 < 0) goto L15;
        StarsController.getInstance(this.currentAccount).loadStarGifts();
        StarsController.GiftsList r03 = new StarsController.GiftsList(this.currentAccount, r10, false);
        this.gifts = r03;
        r03.forceTypeIncludeFlag(8, false);
        r03.load();
        StarsController.GiftsList r04 = new StarsController.GiftsList(this.currentAccount, r10, false);
        this.giftsWithPeerColor = r04;
        r04.forceTypeIncludeFlag(8, false);
        r04.peer_color_available = true;
        r04.load();
    L16:
        this.resourceProvider = new AnonymousClass1(this);
        this.msgInDrawable = new MessageDrawable(0, false, false, this.resourceProvider);
        this.msgInDrawableSelected = new MessageDrawable(0, false, true, this.resourceProvider);
        return;
    L15:
        this.gifts = null;
        this.giftsWithPeerColor = null;
        goto L16
    L11:
        r32 = false;
        goto L12
    L5:
        if (SharedConfig.chatBlurEnabled() == false) goto L7;
        DownscaleScrollableNoiseSuppressor r42 = new DownscaleScrollableNoiseSuppressor();
        this.scrollableViewNoiseSuppressor = r42;
        BlurredBackgroundSourceRenderNode r6 = new BlurredBackgroundSourceRenderNode(r2);
        this.glassBackgroundSourceRenderNode = r6;
        r6.setOnDrawablesRelativePositionChangeListener(new PeerColorActivity$$ExternalSyntheticLambda9(this));
        r6.setScrollableNoiseSuppressor(r42, -2);
        r6.setUnderSource(r2);
        BlurredBackgroundDrawableViewFactory r22 = new BlurredBackgroundDrawableViewFactory(r6);
        this.glassBackgroundDrawableFactory = r22;
        r22.setLiquidGlassEffectAllowed(LiteMode.isEnabled(262144));
        goto L8
    }

    public static /* synthetic */ Theme.ResourcesProvider access$000(PeerColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$100(PeerColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1000(PeerColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ INavigationLayout access$1100(PeerColorActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1200(PeerColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1300(PeerColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1400(PeerColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1500(PeerColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1600(PeerColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ int access$1700(PeerColorActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1800(PeerColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1900(PeerColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ int access$200(PeerColorActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2000(PeerColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2100(PeerColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ int access$2200(PeerColorActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2300(PeerColorActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2400(PeerColorActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2500(PeerColorActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$300(PeerColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$400(PeerColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ int access$500(PeerColorActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$600(PeerColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ int access$700(PeerColorActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$800(PeerColorActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$900(PeerColorActivity r0) {
        return r0.currentAccount;
    }

    public static int adaptProfileEmojiColor(int r2) {
        if (AndroidUtilities.computePerceivedBrightness(r2) >= 0.2f) goto L5;
        boolean r0 = true;
    L6:
        if (r0 == false) goto L8;
        float r02 = 0.28f;
    L10:
        return Theme.adaptHSV(r2, 0.5f, r02);
    L8:
        r02 = -0.28f;
        goto L10
    L5:
        r0 = false;
        goto L6
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v14, types: [org.telegram.tgnet.TLRPC$TL_emojiStatusEmpty] */
    /* JADX WARN: Type inference failed for: r2v15, types: [org.telegram.tgnet.TLRPC$EmojiStatus] */
    /* JADX WARN: Type inference failed for: r2v16, types: [org.telegram.tgnet.TLRPC$TL_inputEmojiStatusCollectible] */
    /* JADX WARN: Type inference failed for: r7v41, types: [org.telegram.messenger.MessagesController] */
    private void apply() {
        if (this.applying == false) goto L5;
        return;
    L5:
        if (this.isChannel == true) goto L10;
        if (getUserConfig().isPremium() == true) goto L10;
        return;
    L10:
        if (this.isChannel == false) goto L12;
        finishFragment();
    L84:
        this.applying = true;
        getNotificationCenter().postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(MessagesController.UPDATE_MASK_EMOJI_STATUS)});
        return;
    L12:
        TLRPC.User r0 = getUserConfig().getCurrentUser();
        if (r0.color != null) goto L15;
        TLRPC.TL_peerColor r2 = new TLRPC.TL_peerColor();
        r0.color = r2;
        r2.flags |= 1;
        r2.color = (int) (r0.id % 7);
    L15:
        TL_stars.TL_starGiftUnique r4 = null;
        if (Page.m16721$$Nest$fgetselectedColor(this.namePage) == UserObject.getColorId(r0)) goto L18;
    L29:
        this.applyingName = true;
        TL_account.updateColor r22 = new TL_account.updateColor();
        r0.flags2 |= 256;
        r0.color.flags |= 1;
        TLRPC.TL_peerColorCollectible r3 = Page.m16724$$Nest$fgetselectedPeerCollectible(this.namePage);
        int r7 = r22.flags;
        if (r3 == null) goto L32;
        r22.flags = r7 | 4;
        TLRPC.TL_inputPeerColorCollectible r32 = new TLRPC.TL_inputPeerColorCollectible();
        r22.color = r32;
        r32.collectible_id = Page.m16724$$Nest$fgetselectedPeerCollectible(this.namePage).collectible_id;
        r0.color = Page.m16724$$Nest$fgetselectedPeerCollectible(this.namePage);
    L36:
        getConnectionsManager().sendRequest(r22, null);
    L38:
        if (Page.m16721$$Nest$fgetselectedColor(this.profilePage) == UserObject.getProfileColorId(r0)) goto L40;
    L47:
        this.applyingProfile = true;
        if (r0.profile_color != null) goto L50;
        r0.profile_color = new TLRPC.TL_peerColor();
    L50:
        TL_account.updateColor r23 = new TL_account.updateColor();
        r23.for_profile = true;
        r0.flags2 |= 512;
        if (Page.m16721$$Nest$fgetselectedColor(this.profilePage) >= 0) goto L54;
        r0.profile_color.flags &= -2;
    L58:
        if (Page.m16722$$Nest$fgetselectedEmoji(this.profilePage) == 0) goto L63;
        int r33 = r23.flags;
        r23.flags = r33 | 1;
        r0.profile_color.flags |= 2;
        if (r23.color != null) goto L62;
        r23.flags = r33 | 5;
        r23.color = new TLRPC.TL_peerColor();
    L62:
        TLRPC.PeerColor r34 = r23.color;
        r34.flags |= 2;
        TLRPC.PeerColor r72 = r0.profile_color;
        long r8 = Page.m16722$$Nest$fgetselectedEmoji(this.profilePage);
        r72.background_emoji_id = r8;
        r34.background_emoji_id = r8;
    L64:
        getConnectionsManager().sendRequest(r23, null);
    L66:
        if (eq(r0.emoji_status, Page.m16723$$Nest$fgetselectedEmojiCollectible(this.profilePage)) == false) goto L68;
    L83:
        getMessagesController().putUser(r0, false);
        getUserConfig().saveConfig(true);
        finishFragment();
        showBulletin();
        goto L84
    L68:
        if (Page.m16723$$Nest$fgetselectedEmojiCollectible(this.profilePage) == null) goto L70;
    L71:
        ?? r24 = new TLRPC.TL_emojiStatusEmpty();
        if (Page.m16723$$Nest$fgetselectedEmojiCollectible(this.profilePage) == null) goto L80;
        long r73 = Page.m16723$$Nest$fgetselectedEmojiCollectible(this.profilePage).collectible_id;
        int r9 = 0;
    L75:
        if (r9 >= this.profilePage.uniqueGifts.size()) goto L80;
        TL_stars.TL_starGiftUnique r10 = this.profilePage.uniqueGifts.get(r9);
        if (r10.id == r73) goto L78;
        r9 = r9 + 1;
        goto L75
    L78:
        r4 = r10;
    L80:
        if (r4 == null) goto L82;
        r24 = new TLRPC.TL_inputEmojiStatusCollectible();
        r24.collectible_id = r4.id;
    L82:
        getMessagesController().updateEmojiStatus(0, r24, r4);
        goto L83
    L70:
        if (DialogObject.isEmojiStatusCollectible(r0.emoji_status) == false) goto L83;
    L63:
        TLRPC.PeerColor r35 = r0.profile_color;
        r35.flags &= -3;
        r35.background_emoji_id = 0;
        goto L64
    L54:
        if (r23.color != null) goto L56;
        r23.flags |= 4;
        r23.color = new TLRPC.TL_peerColor();
    L56:
        TLRPC.PeerColor r36 = r23.color;
        r36.flags |= 1;
        r36.color = Page.m16721$$Nest$fgetselectedColor(this.profilePage);
        TLRPC.PeerColor r37 = r0.profile_color;
        r37.flags |= 1;
        r37.color = Page.m16721$$Nest$fgetselectedColor(this.profilePage);
        goto L58
    L40:
        if (Page.m16722$$Nest$fgetselectedEmoji(this.profilePage) != UserObject.getOnlyProfileEmojiId(r0)) goto L47;
        if (Page.m16723$$Nest$fgetselectedEmojiCollectible(this.profilePage) != null) goto L44;
        long r25 = 0;
    L46:
        if (r25 == UserObject.getProfileCollectibleId(r0)) goto L66;
    L44:
        r25 = Page.m16723$$Nest$fgetselectedEmojiCollectible(this.profilePage).collectible_id;
        goto L46
    L32:
        r22.flags = r7 | 4;
        TLRPC.TL_peerColor r38 = new TLRPC.TL_peerColor();
        r22.color = r38;
        r38.flags |= 1;
        r38.color = Page.m16721$$Nest$fgetselectedColor(this.namePage);
        TLRPC.PeerColor r39 = r0.color;
        r39.flags |= 1;
        r39.color = Page.m16721$$Nest$fgetselectedColor(this.namePage);
        if (Page.m16722$$Nest$fgetselectedEmoji(this.namePage) == 0) goto L35;
        r22.flags |= 1;
        TLRPC.PeerColor r310 = r0.color;
        r310.flags |= 2;
        TLRPC.PeerColor r74 = r22.color;
        r74.flags |= 2;
        long r82 = Page.m16722$$Nest$fgetselectedEmoji(this.namePage);
        r310.background_emoji_id = r82;
        r74.background_emoji_id = r82;
        goto L36
    L35:
        TLRPC.PeerColor r311 = r0.color;
        r311.flags &= -3;
        r311.background_emoji_id = 0;
        goto L36
    L18:
        if (Page.m16722$$Nest$fgetselectedEmoji(this.namePage) != UserObject.getEmojiId(r0)) goto L29;
        if (Page.m16724$$Nest$fgetselectedPeerCollectible(this.namePage) != null) goto L22;
        long r26 = 0;
    L23:
        TLRPC.PeerColor r75 = r0.color;
        if ((r75 instanceof TLRPC.TL_peerColorCollectible) == false) goto L26;
        long r76 = r75.collectible_id;
    L28:
        if (r26 == r76) goto L38;
    L26:
        r76 = 0;
        goto L28
    L22:
        r26 = Page.m16724$$Nest$fgetselectedPeerCollectible(this.namePage).collectible_id;
        goto L23
    }

    private void applyButtonState(Page r3, boolean r4) {
        this.buttonPage = r3;
        this.button.setText(ButtonState.m16706$$Nest$fgettext(Page.m16710$$Nest$fgetbuttonState(r3)), r4);
        this.button.setSubText(ButtonState.m16705$$Nest$fgetsubText(Page.m16710$$Nest$fgetbuttonState(r3)), r4);
    }

    private void buttonClick() {
        if (this.loading == false) goto L6;
        return;
    L6:
        if (this.isChannel == false) goto L9;
        finishFragment();
    L12:
        Page r0 = getButtonPage();
        TL_stars.TL_starGiftUnique r2 = Page.m16725$$Nest$fgetselectedResaleGift(r0);
        Page r3 = this.namePage;
        if (r2 == null) goto L18;
        if (r0 != r3) goto L16;
        r3 = this.profilePage;
    L16:
        r3.setupValues();
        this.loading = true;
        this.button.setLoading(true);
        buy(Page.m16725$$Nest$fgetselectedResaleGift(r0), new PeerColorActivity$$ExternalSyntheticLambda10(this));
        return;
    L18:
        if (r0 != r3) goto L21;
        r3 = this.profilePage;
    L21:
        if (Page.m16725$$Nest$fgetselectedResaleGift(r3) == null) goto L23;
        r3.setupValues();
    L23:
        apply();
        finishFragment();
        showBulletin();
        return;
    L9:
        if (getUserConfig().isPremium() == true) goto L12;
        showDialog(new PremiumFeatureBottomSheet(this, 23, true));
    }

    private void checkColors() {
        this.iBlur3SourceButtonColor.setColor(getThemedColor(Theme.key_windowBackgroundWhite));
        this.buttonContainerInternal.invalidate();
        this.buttonContainer.invalidate();
        Page.m16716$$Nest$fgetmessagesCellFade(this.namePage).invalidate();
        Page.m16717$$Nest$fgetmessagesCellFade2(this.namePage).invalidate();
        Page.m16716$$Nest$fgetmessagesCellFade(this.profilePage).invalidate();
        Page.m16715$$Nest$fgetlistView(this.namePage).invalidate();
        Page.m16715$$Nest$fgetlistView(this.profilePage).invalidate();
        ((BlurredBackgroundDrawable) this.buttonContainerInternal.getBackground()).updateColors();
        checkPagesColors();
        Iterator<BlurredBackgroundDrawable> r2 = this.glassAttachedDrawables.iterator();
    L4:
        if (r2.hasNext() == false) goto L6;
        r2.next().updateColors();
        goto L4
    }

    private void checkPagesColors() {
        if (AndroidUtilities.computePerceivedBrightness(getThemedColor(Theme.key_windowBackgroundWhite)) <= 0.72d) goto L5;
        int r0 = ColorUtils.blendARGB(getThemedColor(Theme.key_windowBackgroundGray), this.colorBar.getColor(), 0.085f);
    L6:
        this.contentView.setBackgroundColor(r0);
        if (this.iBlur3SourceBackgroundColor.getColor() == r0) goto L15;
        this.iBlur3SourceBackgroundColor.setColor(r0);
        Iterator<BlurredBackgroundDrawable> r02 = this.glassAttachedDrawables.iterator();
    L10:
        if (r02.hasNext() == false) goto L12;
        r02.next().updateColors();
        goto L10
    L12:
        invalidateAllGlassAttachedViews();
        return;
    L15:
        return;
    L5:
        r0 = getThemedColor(Theme.key_windowBackgroundGray);
        goto L6
    }

    private void createButton(Context r10) {
        ButtonWithCounterView r0 = new ButtonWithCounterView(r10, getResourceProvider());
        this.button = r0;
        r0.setStateListAnimator(null);
        this.button.setRound();
        this.button.text.setHacks(true, true, true);
        FrameLayout r02 = new FrameLayout(r10);
        this.buttonContainerInternal = r02;
        r02.setOnClickListener(new PeerColorActivity$$ExternalSyntheticLambda13(this));
        this.buttonContainerInternal.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        this.buttonContainerInternal.addView(this.button, LayoutHelper.createFrame(-1, -1.0f));
        FrameLayout r03 = this.buttonContainerInternal;
        r03.setBackground(this.iBlur3ButtonFactory.create(r03).setColorProvider(BlurredBackgroundProviderImpl.premiumButton(this.resourceProvider)).setRadius(AndroidUtilities.dp(28.0f)).setPadding(AndroidUtilities.dp(5.0f)));
        ScaleStateListAnimator.apply(this.buttonContainerInternal, 0.02f, 1.5f);
        FrameLayout r04 = new FrameLayout(r10);
        this.buttonContainer = r04;
        r04.addView(this.buttonContainerInternal, LayoutHelper.createFrame(-1, 64.0f, 80, 4.0f, 0.0f, 4.0f, 0.0f));
        BlurredBackgroundWithFadeDrawable r102 = new BlurredBackgroundWithFadeDrawable(this.iBlur3BackgroundFactory.create(this.buttonContainer));
        r102.setFadeHeight(AndroidUtilities.dp(40.0f), true);
        r102.setAlpha(Opcodes.REM_INT_LIT8);
        this.buttonContainer.setBackground(r102);
        updateButtonState(false);
    }

    private void drawList(Canvas r4, RectF r5) {
        FrameLayout r0 = this.contentView;
        Blur3Utils.captureRelativeParent(Page.m16715$$Nest$fgetlistView(this.namePage), r4, r5, Page.m16715$$Nest$fgetlistView(this.namePage), r0);
        Blur3Utils.captureRelativeParent(Page.m16715$$Nest$fgetlistView(this.profilePage), r4, r5, Page.m16715$$Nest$fgetlistView(this.profilePage), r0);
    }

    public static boolean eq(TLRPC.EmojiStatus r4, TLRPC.TL_emojiStatusCollectible r5) {
        if (r5 != r4) goto L6;
        return true;
    L6:
        if (r5 == null) goto L8;
        boolean r2 = true;
    L9:
        boolean r3 = r4 instanceof TLRPC.TL_emojiStatusCollectible;
        if (r2 == r3) goto L12;
        return false;
    L12:
        if (r5 == null) goto L18;
        if (r3 == false) goto L18;
        if (((TLRPC.TL_emojiStatusCollectible) r4).collectible_id != r5.collectible_id) goto L18;
        return true;
    L18:
        return false;
    L8:
        r2 = false;
        goto L9
    }

    public static boolean eq(TLRPC.TL_peerColorCollectible r4, TLRPC.TL_peerColorCollectible r5) {
        if (r4 != r5) goto L5;
        return true;
    L5:
        if (r4 != null) goto L9;
        if (r5 != null) goto L9;
        return true;
    L9:
        if (r4 == null) goto L15;
        if (r5 == null) goto L15;
        if (r4.collectible_id != r5.collectible_id) goto L15;
        return true;
    L15:
        return false;
    }

    private Page getButtonPage() {
        ViewPagerFixed r0 = this.viewPager;
        if (r0 == null) goto L9;
        if (r0.getPositionAnimated() < 0.5f) goto L9;
        return this.namePage;
    L9:
        return this.profilePage;
    }

    private int getMergedVisibleBlurredPositions(List<RectF> r8) {
        int r0 = RectFMergeBounding.mergeOverlapping(this.glassDrawablesPositions, getVisibleBlurredPositions(this.glassDrawablesPositions), r8);
        int r1 = this.contentView.getMeasuredWidth();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        RectF r3 = r8.get(r2);
        float r5 = r1;
        r3.left = MathUtils.clamp(r3.left, 0.0f, r5);
        r3.top = Math.max(0.0f, r3.top);
        r3.right = MathUtils.clamp(r3.right, 0.0f, r5);
        r3.bottom = Math.min(this.contentView.getHeight(), r3.bottom);
        r2 = r2 + 1;
        goto L3
    L5:
        return r0;
    }

    private float getTabProgressFromPageProgress(float r2) {
        return MathUtils.clamp((r2 - 0.333333f) / 0.333333f, 0.0f, 1.0f);
    }

    private int getVisibleBlurredPositions(List<RectF> r4) {
        if (Build.VERSION.SDK_INT < 29) goto L8;
        BlurredBackgroundSourceRenderNode r3 = this.glassBackgroundSourceRenderNode;
        if (r3 == null) goto L8;
        return r3.getVisiblePositions(r4, 0, AndroidUtilities.dp(8.0f));
    L8:
        return 0;
    }

    private void invalidateAllGlassAttachedViews() {
        Iterator<View> r1 = this.glassAttachedViews.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().invalidate();
        goto L4
    }

    private void invalidateMergedVisibleBlurredPositionsAndSources(int r3) {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        return;
    L5:
        if (this.scrollableViewNoiseSuppressor == null) goto L10;
        this.invalidateBlurredSourcesView.invalidate(r3);
        return;
    }

    private void invalidateMergedVisibleBlurredPositionsAndSourcesImpl(int r4) {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        return;
    L5:
        if (this.scrollableViewNoiseSuppressor == null) goto L16;
        BitwiseUtils.hasFlag(r4, 4);
        int r42 = getMergedVisibleBlurredPositions(this.glassDrawablesPositionsMerged);
        this.glassDrawablesPositionsCount = r42;
        this.scrollableViewNoiseSuppressor.setupRenderNodes(this.glassDrawablesPositionsMerged, r42);
        if (this.scrollableViewNoiseSuppressor.invalidateResultRenderNodes(this.iBlur3Capture, this.contentView.getWidth(), this.contentView.getHeight()) == false) goto L15;
        BlurredBackgroundSourceRenderNode r43 = this.glassBackgroundSourceRenderNode;
        if (r43 == null) goto L12;
        r43.invalidateDisplayListForDrawables();
    L12:
        invalidateAllGlassAttachedViews();
        return;
    L15:
        return;
    }

    private void invalidateMergedVisibleBlurredPositionsAndSourcesPositions() {
        invalidateMergedVisibleBlurredPositionsAndSources(2);
    }

    private /* synthetic */ void lambda$buttonClick$6(Boolean r3) {
        this.loading = false;
        this.button.setLoading(false);
        if (r3.booleanValue() == false) goto L6;
        apply();
        finishFragment();
        showBulletin();
        return;
    }

    private /* synthetic */ void lambda$buy$10(AmountUtils$Currency r13, TL_stars.TL_starGiftUnique r14, long r15, Utilities.Callback r17, TLRPC.TL_payments_paymentFormStarGift r18) {
        if (r18 != null) goto L5;
        return;
    L5:
        boolean[] r2 = new boolean[1];
        StarGiftSheet.ResaleBuyTransferAlert r132 = new StarGiftSheet.ResaleBuyTransferAlert(getContext(), this.resourceProvider, r14, new StarGiftSheet.PaymentFormState(r13, r18), this.currentAccount, r15, r14.title + " #" + LocaleController.formatNumber(r14.num, ','), false, new PeerColorActivity$$ExternalSyntheticLambda15(this, r2, r14, r15, r17));
        r132.alertDialog.setOnDismissListener(new PeerColorActivity$$ExternalSyntheticLambda16(r2, r17));
        r132.show();
    }

    private /* synthetic */ void lambda$buy$8(boolean[] r3, TL_stars.TL_starGiftUnique r4, long r5, Utilities.Callback r7, StarGiftSheet.PaymentFormState r8, Browser.Progress r9) {
        r3[0] = true;
        r9.init();
        StarsController.getInstance(this.currentAccount, r8.currency).buyResellingGift(r8.form, r4, r5, new PeerColorActivity$$ExternalSyntheticLambda17(r9, r7));
    }

    private /* synthetic */ void lambda$createButton$3(View r1) {
        buttonClick();
    }

    private /* synthetic */ void lambda$createView$0(Integer r1) {
        ViewPagerFixed r0 = this.viewPager;
        if (r0 == null) goto L6;
        r0.scrollToPosition(r1.intValue());
        return;
    }

    private /* synthetic */ void lambda$createView$1(View r1) {
        if (onBackPressed(true) == false) goto L6;
        finishFragment();
        return;
    }

    private /* synthetic */ void lambda$createView$2(View r1) {
        toggleTheme();
    }

    private /* synthetic */ void lambda$showUnsavedAlert$4(AlertDialog r1, int r2) {
        finishFragment();
    }

    private /* synthetic */ void lambda$showUnsavedAlert$5(AlertDialog r1, int r2) {
        buttonClick();
    }

    private /* synthetic */ void lambda$toggleTheme$12() {
        this.isDark = !this.isDark;
        updateThemeColors();
        setForceDark(this.isDark, true);
        updateColors();
    }

    private WindowInsetsCompat onApplyWindowInsets(View r7, WindowInsetsCompat r8) {
        this.insets = AndroidUtilities.getDefaultWindowInsets(r8, false);
        RecyclerListView r82 = Page.m16715$$Nest$fgetlistView(this.namePage);
        int r0 = this.insets.left;
        int r1 = Page.m16715$$Nest$fgetlistView(this.namePage).getPaddingTop();
        Insets r2 = this.insets;
        r82.setPadding(r0, r1, r2.right, r2.bottom + AndroidUtilities.dp(72.0f));
        RecyclerListView r83 = Page.m16715$$Nest$fgetlistView(this.profilePage);
        int r02 = this.insets.left;
        int r12 = Page.m16715$$Nest$fgetlistView(this.profilePage).getPaddingTop();
        Insets r22 = this.insets;
        r83.setPadding(r02, r12, r22.right, r22.bottom + AndroidUtilities.dp(72.0f));
        FrameLayout r84 = this.buttonContainer;
        Insets r6 = this.insets;
        r84.setPadding(r6.left, 0, r6.right, r6.bottom);
        return WindowInsetsCompat.CONSUMED;
    }

    private void showBulletin() {
        if (this.bulletinFragment != null) goto L5;
        return;
    L5:
        if (this.applyingName == false) goto L26;
        if (this.applyingProfile == false) goto L11;
        if (getCurrentPage() != this.namePage) goto L26;
    L11:
        if (Page.m16721$$Nest$fgetselectedColor(this.namePage) < 0) goto L13;
        BulletinFactory r0 = BulletinFactory.of(this.bulletinFragment);
        PeerColorDrawable r1 = PeerColorDrawable.from(this.currentAccount, Page.m16721$$Nest$fgetselectedColor(this.namePage));
        if (this.isChannel == false) goto L23;
        int r2 = R.string.ChannelColorApplied;
    L24:
        r0.createSimpleBulletin(r1, LocaleController.getString(r2)).show();
    L50:
        this.bulletinFragment = null;
        return;
    L23:
        r2 = R.string.UserColorApplied;
        goto L24
    L13:
        if (Page.m16724$$Nest$fgetselectedPeerCollectible(this.namePage) == null) goto L53;
        BulletinFactory r02 = BulletinFactory.of(this.bulletinFragment);
        PeerColorDrawable r12 = PeerColorDrawable.from(Page.m16724$$Nest$fgetselectedPeerCollectible(this.namePage));
        if (this.isChannel == false) goto L18;
        int r22 = R.string.ChannelColorApplied;
    L19:
        r02.createSimpleBulletin(r12, LocaleController.getString(r22)).show();
        goto L50
    L18:
        r22 = R.string.UserColorApplied;
        goto L19
    L53:
        return;
    L26:
        if (this.applyingProfile == false) goto L50;
        if (this.applyingName == false) goto L32;
        if (getCurrentPage() != this.profilePage) goto L50;
    L32:
        if (Page.m16721$$Nest$fgetselectedColor(this.profilePage) >= 0) goto L45;
        long r03 = Page.m16722$$Nest$fgetselectedEmoji(this.profilePage);
        BaseFragment r13 = this.bulletinFragment;
        if (r03 == 0) goto L40;
        BulletinFactory r04 = BulletinFactory.of(r13);
        TLRPC.Document r14 = AnimatedEmojiDrawable.findDocument(this.currentAccount, Page.m16722$$Nest$fgetselectedEmoji(this.profilePage));
        if (this.isChannel == false) goto L38;
        int r23 = R.string.ChannelProfileColorEmojiApplied;
    L39:
        r04.createStaticEmojiBulletin(r14, LocaleController.getString(r23)).show();
        goto L50
    L38:
        r23 = R.string.UserProfileColorEmojiApplied;
        goto L39
    L40:
        BulletinFactory r05 = BulletinFactory.of(r13);
        int r15 = R.raw.contact_check;
        if (this.isChannel == false) goto L43;
        int r24 = R.string.ChannelProfileColorResetApplied;
    L44:
        r05.createSimpleBulletin(r15, LocaleController.getString(r24)).show();
        goto L50
    L43:
        r24 = R.string.UserProfileColorResetApplied;
        goto L44
    L45:
        BulletinFactory r06 = BulletinFactory.of(this.bulletinFragment);
        PeerColorDrawable r16 = PeerColorDrawable.fromProfile(this.currentAccount, Page.m16721$$Nest$fgetselectedColor(this.profilePage));
        if (this.isChannel == false) goto L48;
        int r25 = R.string.ChannelProfileColorApplied;
    L49:
        r06.createSimpleBulletin(r16, LocaleController.getString(r25)).show();
        goto L50
    L48:
        r25 = R.string.UserProfileColorApplied;
        goto L49
    }

    private void showUnsavedAlert() {
        if (getVisibleDialog() == null) goto L5;
        return;
    L5:
        AlertDialog.Builder r0 = new AlertDialog.Builder(getContext(), getResourceProvider());
        if (this.isChannel == false) goto L8;
        int r1 = R.string.ChannelColorUnsaved;
    L9:
        AlertDialog.Builder r02 = r0.setTitle(LocaleController.getString(r1));
        if (this.isChannel == false) goto L12;
        int r12 = R.string.ChannelColorUnsavedMessage;
    L13:
        AlertDialog r03 = r02.setMessage(LocaleController.getString(r12)).setNegativeButton(LocaleController.getString(R.string.Dismiss), new PeerColorActivity$$ExternalSyntheticLambda5(this)).setPositiveButton(LocaleController.getString(R.string.ApplyTheme), new PeerColorActivity$$ExternalSyntheticLambda6(this)).create();
        showDialog(r03);
        ((TextView) r03.getButton(-2)).setTextColor(getThemedColor(Theme.key_text_RedBold));
        return;
    L12:
        r12 = R.string.UserColorUnsavedMessage;
        goto L13
    L8:
        r1 = R.string.UserColorUnsaved;
        goto L9
    }

    private void updateButtonState(boolean r3) {
        Page r0 = getButtonPage();
        if (this.button == null) goto L10;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0 == this.buttonPage) goto L12;
        applyButtonState(r0, r3);
        return;
    L12:
        return;
    }

    private void updateColors() {
        SimpleTextView r0 = this.titleView;
        if (r0 == null) goto L5;
        r0.setTextColor(getThemedColor(Theme.key_actionBarDefaultTitle));
    L5:
        this.namePage.updateColors();
        this.profilePage.updateColors();
        ColoredActionBar r02 = this.colorBar;
        if (r02 == null) goto L8;
        r02.updateColors();
    L8:
        updateTabColors();
        checkColors();
        setNavigationBarColor(getNavigationBarColor());
    }

    private void updateTabColors() {
        if (this.colorBar == null) goto L14;
        checkPagesColors();
        int r0 = this.colorBar.getTabsViewBackgroundColor();
        if (this.tabsView == null) goto L13;
        ViewPagerFixed r1 = this.viewPager;
        if (r1 != null) goto L10;
        float r12 = 0.0f;
    L11:
        float r13 = getTabProgressFromPageProgress(r12);
        this.tabsView.setColors(ColorUtils.blendARGB(r0, getThemedColor(Theme.key_windowBackgroundWhite), r13), ColorUtils.blendARGB(-1, getThemedColor(Theme.key_windowBackgroundGray), r13), ColorUtils.blendARGB(-1, getThemedColor(Theme.key_windowBackgroundWhiteGrayText2), r13), ColorUtils.blendARGB(r0, getThemedColor(Theme.key_windowBackgroundWhiteBlackText), r13));
        return;
    L10:
        r12 = r1.getPositionAnimated();
        goto L11
    L13:
        return;
    }

    public static CharSequence withLevelLock(CharSequence r3, int r4) {
        if (r4 > 0) goto L4;
        return r3;
    L4:
        Context r0 = ApplicationLoader.applicationContext;
        SpannableStringBuilder r1 = new SpannableStringBuilder(r3);
        r1.append("  L");
        ColoredImageSpan r42 = new ColoredImageSpan(new LevelLock(r0, r4, null));
        r42.setTranslateY(AndroidUtilities.dp(1.0f));
        r1.setSpan(r42, r1.length() - 1, r1.length(), 33);
        return r1;
    }

    public void buy(TL_stars.TL_starGiftUnique r9, Utilities.Callback<Boolean> r10) {
        long r5 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        if (r9.resale_ton_only == false) goto L6;
        AmountUtils$Currency r0 = AmountUtils$Currency.TON;
    L5:
        AmountUtils$Currency r3 = r0;
        StarsController.getInstance(this.currentAccount, r3).getResellingGiftForm(r9, r5, new PeerColorActivity$$ExternalSyntheticLambda14(this, r3, r9, r5, r10));
        return;
    L6:
        r0 = AmountUtils$Currency.STARS;
        goto L5
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r19) {
        setHasOwnBackground(true);
        this.actionBar.setCastShadows(false);
        this.actionBar.setVisibility(8);
        this.actionBar.setAllowOverlayTitle(false);
        this.actionBar.setAddToContainer(false);
        AnonymousClass3 r3 = new AnonymousClass3(this, r19);
        this.contentView = r3;
        this.fragmentView = r3;
        this.invalidateBlurredSourcesView = new OnPostDrawView(r19, true, new PeerColorActivity$$ExternalSyntheticLambda0(this));
        this.glassBackgroundDrawableFactory.setSourceRootView(new ViewPositionWatcher(this.contentView), this.contentView);
        this.namePage = new Page(this, r19, 1);
        this.profilePage = new Page(this, r19, 0);
        AnonymousClass4 r5 = new AnonymousClass4(this, r19, this.resourceProvider);
        this.colorBar = r5;
        r5.newMode = true;
        this.profilePage.updateProfilePreview(false);
        this.profilePage.addView(this.colorBar, 2, LayoutHelper.createFrame(-1, -2, 55));
        AnonymousClass5 r52 = new AnonymousClass5(this, r19);
        this.viewPager = r52;
        r52.setAdapter(new AnonymousClass6(this));
        r3.addView(this.viewPager, LayoutHelper.createFrame(-1, -1, 119));
        createButton(r19);
        r3.addView(this.buttonContainer, LayoutHelper.createFrame(-1, -2, 80));
        FrameLayout r53 = new FrameLayout(r19);
        this.actionBarContainer = r53;
        r3.addView(r53, LayoutHelper.createFrame(-1, -2, 55));
        if (this.isChannel == true) goto L13;
        FilledTabsView r32 = new FilledTabsView(r19);
        this.tabsView = r32;
        if (this.isChannel == false) goto L7;
        int r54 = R.string.ChannelColorTabProfile;
    L8:
        String r55 = LocaleController.getString(r54);
        if (this.isChannel == false) goto L11;
        int r6 = R.string.ChannelColorTabName;
    L12:
        r32.setTabs(new CharSequence[]{r55, LocaleController.getString(r6)});
        this.tabsView.onTabSelected(new PeerColorActivity$$ExternalSyntheticLambda1(this));
        updateTabColors();
        this.actionBarContainer.addView(this.tabsView, LayoutHelper.createFrame(-1, 40, 17));
    L14:
        ColoredActionBar r33 = this.colorBar;
        if (r33 == null) goto L17;
        r33.setProgressToGradient(1.0f);
        updateLightStatusBar();
    L17:
        ImageView r34 = new ImageView(r19);
        this.backButton = r34;
        ImageView.ScaleType r56 = ImageView.ScaleType.CENTER;
        r34.setScaleType(r56);
        ImageView r35 = this.backButton;
        int r62 = Theme.key_actionBarWhiteSelector;
        r35.setBackground(Theme.createSelectorDrawable(getThemedColor(r62), 1));
        this.backButton.setImageResource(R.drawable.ic_ab_back);
        ImageView r36 = this.backButton;
        PorterDuff.Mode r9 = PorterDuff.Mode.SRC_IN;
        r36.setColorFilter(new PorterDuffColorFilter(-1, r9));
        this.backButton.setOnClickListener(new PeerColorActivity$$ExternalSyntheticLambda2(this));
        ScaleStateListAnimator.apply(this.backButton);
        this.actionBarContainer.addView(this.backButton, LayoutHelper.createFrame(54, 54, 19));
        RLottieDrawable r12 = new RLottieDrawable(R.raw.sun_outline, AndroidUtilities.dp(28.0f), AndroidUtilities.dp(28.0f), true, null);
        this.sunDrawable = r12;
        r12.setPlayInDirectionOfCustomEndFrame(true);
        boolean r37 = this.isDark;
        RLottieDrawable r8 = this.sunDrawable;
        if (r37 == true) goto L20;
        r8.setCustomEndFrame(0);
        this.sunDrawable.setCurrentFrame(0);
    L21:
        this.sunDrawable.beginApplyLayerColors();
        int r38 = Theme.getColor(Theme.key_chats_menuName);
        this.sunDrawable.setLayerColor("Sunny", r38);
        this.sunDrawable.setLayerColor("Path 6", r38);
        this.sunDrawable.setLayerColor("Path", r38);
        this.sunDrawable.setLayerColor("Path 5", r38);
        this.sunDrawable.commitApplyLayerColors();
        ImageView r39 = new ImageView(r19);
        this.dayNightItem = r39;
        r39.setScaleType(r56);
        this.dayNightItem.setBackground(Theme.createSelectorDrawable(getThemedColor(r62), 1));
        this.dayNightItem.setColorFilter(new PorterDuffColorFilter(-1, r9));
        this.dayNightItem.setOnClickListener(new PeerColorActivity$$ExternalSyntheticLambda3(this));
        this.actionBarContainer.addView(this.dayNightItem, LayoutHelper.createFrame(54, 54, 21));
        this.dayNightItem.setImageDrawable(this.sunDrawable);
        this.colorBar.updateColors();
        checkColors();
        this.contentView.addView(this.invalidateBlurredSourcesView);
        checkPagesColors();
        ViewCompat.setOnApplyWindowInsetsListener(this.fragmentView, new PeerColorActivity$$ExternalSyntheticLambda4(this));
        return this.contentView;
    L20:
        r8.setCurrentFrame(35);
        this.sunDrawable.setCustomEndFrame(36);
        goto L21
    L11:
        r6 = R.string.UserColorTabName;
        goto L12
    L7:
        r54 = R.string.UserColorTabProfile;
        goto L8
    L13:
        SimpleTextView r310 = new SimpleTextView(r19);
        this.titleView = r310;
        r310.setText(LocaleController.getString(R.string.ChannelColorTitle2));
        this.titleView.setEllipsizeByGradient(true);
        this.titleView.setTextSize(20);
        this.titleView.setTextColor(getThemedColor(Theme.key_actionBarDefaultTitle));
        this.titleView.setTypeface(AndroidUtilities.bold());
        this.actionBarContainer.addView(this.titleView, LayoutHelper.createFrame(-2, -2.0f, 19, 72.0f, 0.0f, 72.0f, 0.0f));
        goto L14
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    @SuppressLint({"NotifyDataSetChanged"})
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r2 == this.currentAccount) goto L6;
        return;
    L6:
        if (r1 != NotificationCenter.currentUserPremiumStatusChanged) goto L10;
        this.namePage.premiumChanged();
        this.profilePage.premiumChanged();
        return;
    L10:
        if (r1 != NotificationCenter.starUserGiftsLoaded) goto L14;
        this.namePage.update();
        this.profilePage.update();
        return;
    L14:
        if (r1 != NotificationCenter.starGiftsLoaded) goto L17;
        this.namePage.update();
        this.profilePage.update();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean drawEdgeNavigationBar() {
        return false;
    }

    public Page getCurrentPage() {
        if (this.viewPager.getCurrentPosition() != 0) goto L7;
        return this.profilePage;
    L7:
        return this.namePage;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public EdgeToEdgeSupportMode getEdgeToEdgeSupportMode() {
        return EdgeToEdgeSupportMode.FULL;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        return SimpleThemeDescription.createThemeDescriptions(new PeerColorActivity$$ExternalSyntheticLambda7(this), new int[]{Theme.key_windowBackgroundWhite, Theme.key_windowBackgroundWhiteBlackText, Theme.key_windowBackgroundWhiteGrayText2, Theme.key_listSelector, Theme.key_windowBackgroundGray, Theme.key_windowBackgroundWhiteGrayText4, Theme.key_text_RedRegular, Theme.key_windowBackgroundChecked, Theme.key_windowBackgroundCheckText, Theme.key_switchTrackBlue, Theme.key_switchTrackBlueChecked, Theme.key_switchTrackBlueThumb, Theme.key_switchTrackBlueThumbChecked});
    }

    public boolean hasUnsavedChanged() {
        if (this.namePage.hasUnsavedChanged() == false) goto L5;
        return true;
    L5:
        if (this.profilePage.hasUnsavedChanged() == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        ColoredActionBar r0 = this.colorBar;
        if (r0 != null) goto L7;
        return super.isLightStatusBar();
    L7:
        if (ColorUtils.calculateLuminance(r0.getColor()) <= 0.699999988079071d) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSwipeBackEnabled(MotionEvent r2) {
        if (this.isChannel == true) goto L11;
        if (hasUnsavedChanged() == false) goto L11;
        if (getUserConfig().isPremium() == false) goto L11;
        return false;
    L11:
        return super.isSwipeBackEnabled(r2);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r2) {
        if (this.isChannel == true) goto L13;
        if (hasUnsavedChanged() == false) goto L13;
        if (getUserConfig().isPremium() == false) goto L13;
        if (r2 == false) goto L14;
        showUnsavedAlert();
        return false;
    L14:
        return false;
    L13:
        return super.onBackPressed(r2);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentClosed() {
        super.onFragmentClosed();
        Bulletin.removeDelegate(this);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getNotificationCenter().addObserver(this, NotificationCenter.currentUserPremiumStatusChanged);
        getNotificationCenter().addObserver(this, NotificationCenter.starUserGiftsLoaded);
        getNotificationCenter().addObserver(this, NotificationCenter.starGiftsLoaded);
        Bulletin.addDelegate(this, new AnonymousClass2(this));
        getMediaDataController().loadReplyIcons();
        if (MessagesController.getInstance(this.currentAccount).peerColors != null) goto L8;
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L8;
        MessagesController.getInstance(this.currentAccount).loadAppConfig(true);
    L8:
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        getNotificationCenter().removeObserver(this, NotificationCenter.currentUserPremiumStatusChanged);
        getNotificationCenter().removeObserver(this, NotificationCenter.starUserGiftsLoaded);
        getNotificationCenter().removeObserver(this, NotificationCenter.starGiftsLoaded);
        Bulletin.removeDelegate(this);
    }

    public void setForceDark(boolean r3, boolean r4) {
        if (this.forceDark == r3) goto L24;
        this.forceDark = r3;
        int r0 = 0;
        if (r4 == false) goto L15;
        RLottieDrawable r42 = this.sunDrawable;
        if (r3 == false) goto L10;
        r0 = r42.getFramesCount();
    L10:
        r42.setCustomEndFrame(r0);
        RLottieDrawable r2 = this.sunDrawable;
        if (r2 == null) goto L22;
        r2.start();
        return;
    L22:
        return;
    L15:
        if (r3 == false) goto L17;
        int r32 = this.sunDrawable.getFramesCount() - 1;
    L18:
        this.sunDrawable.setCurrentFrame(r32, false, true);
        this.sunDrawable.setCustomEndFrame(r32);
        ImageView r22 = this.dayNightItem;
        if (r22 == null) goto L23;
        r22.invalidate();
        return;
    L23:
        return;
    L17:
        r32 = 0;
        goto L18
    }

    public PeerColorActivity setOnApplied(BaseFragment r1) {
        this.bulletinFragment = r1;
        return this;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void setResourceProvider(Theme.ResourcesProvider r1) {
        this.parentResourcesProvider = r1;
    }

    public PeerColorActivity startOnProfile() {
        this.startAtProfile = true;
        return this;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public void toggleTheme() {
        FrameLayout r12 = (FrameLayout) getParentActivity().getWindow().getDecorView();
        Bitmap r8 = Bitmap.createBitmap(r12.getWidth(), r12.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas r3 = new Canvas(r8);
        this.dayNightItem.setAlpha(0.0f);
        r12.draw(r3);
        this.dayNightItem.setAlpha(1.0f);
        Paint r7 = new Paint(1);
        r7.setColor(-16777216);
        r7.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Paint r9 = new Paint(1);
        r9.setFilterBitmap(true);
        int[] r2 = new int[2];
        this.dayNightItem.getLocationInWindow(r2);
        float r10 = r2[0];
        float r11 = r2[1];
        float r6 = Math.max(r8.getHeight(), r8.getWidth()) + AndroidUtilities.navigationBarHeight;
        Shader.TileMode r22 = Shader.TileMode.CLAMP;
        r9.setShader(new BitmapShader(r8, r22, r22));
        AnonymousClass7 r0 = new AnonymousClass7(this, getContext(), r3, r10 + (this.dayNightItem.getMeasuredWidth() / 2.0f), r11 + (this.dayNightItem.getMeasuredHeight() / 2.0f), r6, r7, r8, r9, r10, r11);
        this.changeDayNightView = r0;
        r0.setOnTouchListener(new PeerColorActivity$$ExternalSyntheticLambda11());
        this.changeDayNightViewProgress = 0.0f;
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.changeDayNightViewAnimator = r02;
        r02.addUpdateListener(new AnonymousClass8(this));
        this.changeDayNightViewAnimator.addListener(new AnonymousClass9(this));
        this.changeDayNightViewAnimator.setDuration(400);
        this.changeDayNightViewAnimator.setInterpolator(Easings.easeInOutQuad);
        this.changeDayNightViewAnimator.start();
        r12.addView(this.changeDayNightView, new ViewGroup.LayoutParams(-1, -1));
        AndroidUtilities.runOnUIThread(new PeerColorActivity$$ExternalSyntheticLambda12(this));
    }

    public void updateLightStatusBar() {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        AndroidUtilities.setLightStatusBar(getParentActivity(), isLightStatusBar());
    }

    public void updateThemeColors() {
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("themeconfig", 0);
        String r3 = "Blue";
        String r1 = r0.getString("lastDayTheme", "Blue");
        if (Theme.getTheme(r1) != null) goto L5;
    L6:
        r1 = "Blue";
    L7:
        String r5 = "Dark Blue";
        String r02 = r0.getString("lastDarkTheme", "Dark Blue");
        if (Theme.getTheme(r02) != null) goto L10;
    L11:
        r02 = "Dark Blue";
    L12:
        Theme.ThemeInfo r4 = Theme.getActiveTheme();
        if (r1.equals(r02) == true) goto L15;
        r5 = r02;
    L21:
        r3 = r1;
    L25:
        if (this.isDark == false) goto L27;
        Theme.ThemeInfo r03 = Theme.getTheme(r5);
    L28:
        this.currentColors.clear();
        String[] r32 = new String[1];
        String r42 = r03.assetName;
        if (r42 == null) goto L31;
        SparseIntArray r43 = Theme.getThemeFileValues(null, r42, r32);
    L32:
        int[] r52 = Theme.getDefaultColors();
        if (r52 == null) goto L38;
        int r6 = 0;
    L36:
        if (r6 >= r52.length) goto L38;
        this.currentColors.put(r6, r52[r6]);
        r6 = r6 + 1;
    L38:
        int r53 = 0;
    L40:
        if (r53 >= r43.size()) goto L42;
        this.currentColors.put(r43.keyAt(r53), r43.valueAt(r53));
        r53 = r53 + 1;
        goto L40
    L42:
        Theme.ThemeAccent r54 = r03.getAccent(false);
        if (r54 == null) goto L45;
        r54.fillAccentColors(r43, this.currentColors);
    L45:
        Page r44 = this.namePage;
        if (r44 != null) goto L48;
        return;
    L48:
        if (Page.m16718$$Nest$fgetmessagesCellPreview(r44) == null) goto L58;
        Theme.BackgroundDrawableSettings r04 = Theme.createBackgroundDrawable(r03, this.currentColors, r32[0], 0, true);
        ThemePreviewMessagesCell r9 = Page.m16718$$Nest$fgetmessagesCellPreview(this.namePage);
        Drawable r12 = r04.themedWallpaper;
        if (r12 != null) goto L53;
        r12 = r04.wallpaper;
    L53:
        r9.setOverrideBackground(r12);
        return;
    L58:
        return;
    L31:
        r43 = Theme.getThemeFileValues(new File(r03.pathToFile), null, r32);
        goto L32
    L27:
        r03 = Theme.getTheme(r3);
        goto L28
    L15:
        if (r4.isDark() == false) goto L17;
    L22:
        r5 = r02;
        goto L25
    L17:
        if (r1.equals("Dark Blue") == true) goto L22;
        if (r1.equals("Night") == false) goto L21;
    L10:
        if (Theme.getTheme(r02).isDark() == true) goto L12;
    L5:
        if (Theme.getTheme(r1).isDark() == false) goto L7;
        goto L6
    }
}
