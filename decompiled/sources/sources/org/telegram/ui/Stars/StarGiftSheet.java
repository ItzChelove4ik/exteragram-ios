package org.telegram.ui.Stars;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.RelativeSizeSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.collection.LongSparseArray;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;
import org.json.JSONObject;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.BirthdayController;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.ChannelBoostsController;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ChatThemeController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.GiftAuctionController;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.messenger.utils.CountdownTimer;
import org.telegram.messenger.utils.tlutils.AmountUtils$Amount;
import org.telegram.messenger.utils.tlutils.AmountUtils$Currency;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.AccountFrozenAlert;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.theme.ThemeKey;
import org.telegram.ui.Cells.SessionCell;
import org.telegram.ui.Cells.ShareDialogCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BottomSheetLayouted;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ButtonSpan;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CompatDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EllipsizeSpanAnimator;
import org.telegram.ui.Components.FireworksOverlay;
import org.telegram.ui.Components.HorizontalRoundTabsLayout;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkPath;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.LoadingSpan;
import org.telegram.ui.Components.Premium.LimitPreviewView;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.Premium.boosts.UserSelectorBottomSheet;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.ShareAlert;
import org.telegram.ui.Components.TableView;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.TextHelper;
import org.telegram.ui.Components.ViewPagerFixed;
import org.telegram.ui.Components.spoilers.SpoilersTextView;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.Gifts.GiftMessageBottomSheet;
import org.telegram.ui.Gifts.GiftMessageView;
import org.telegram.ui.Gifts.GiftSheet;
import org.telegram.ui.Gifts.ProfileGiftsContainer;
import org.telegram.ui.Gifts.ResaleGiftsFragment;
import org.telegram.ui.GradientClip;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.Stars.StarGiftPreviewSheet;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stars.StarsReactionsSheet;
import org.telegram.ui.StatisticActivity;
import org.telegram.ui.Stories.DarkThemeResourceProvider;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.Stories.recorder.HintView2;
import org.telegram.ui.Stories.recorder.StoryEntry;
import org.telegram.ui.Stories.recorder.StoryRecorder;
import org.telegram.ui.TON.TONIntroActivity;
import org.telegram.ui.TopicsFragment;
import org.telegram.ui.TwoStepVerificationActivity;
import org.telegram.ui.TwoStepVerificationSetupActivity;
import org.telegram.ui.bots.AffiliateProgramFragment;
import org.telegram.ui.bots.BotWebViewSheet;

/* loaded from: classes7.dex */
public class StarGiftSheet extends BottomSheetWithRecyclerListView implements NotificationCenter.NotificationCenterDelegate {
    private final ActionView actionView;
    private Adapter adapter;
    private final LinkSpanDrawable.LinksTextView afterTableTextView;
    private final LinkSpanDrawable.LinksTextView beforeTableTextView;
    private final FrameLayout bottomBulletinContainer;
    private final View bottomView;
    private BoughtGiftCallback boughtGift;
    private final ButtonWithCounterView button;
    private final FrameLayout buttonContainer;
    private final View buttonShadow;
    private final CheckBox2 checkbox;
    private final LinearLayout checkboxLayout;
    private final View checkboxSeparator;
    private final TextView checkboxTextView;
    private Utilities.Callback<Boolean> closeParentSheet;
    private ContainerView container;
    private final AffiliateProgramFragment.FeatureCell[] craftFeatureCells;
    private final LinearLayout craftLayout;
    private HintView2 currentHintView;
    private View currentHintViewTextView;
    private PageTransition currentPage;
    private final long dialogId;
    private FireworksOverlay fireworksOverlay;
    private boolean firstSet;
    private StarsController.IGiftsList giftsList;
    private ResaleGiftsFragment.SelectGiftSheet.State giftsToCraft;
    private final int[] heights;
    private final LinearLayout infoLayout;
    private boolean isLearnMore;
    private Float lastTop;
    private StarGiftSheet left;
    private ColoredImageSpan lockSpan;
    private MessageObject messageObject;
    private boolean messageObjectRepolled;
    private boolean messageObjectRepolling;
    private boolean myProfile;
    private boolean nextButtonCrafting;
    private ArrayList<TL_stars.StarGiftUpgradePrice> next_prices;
    private Runnable onGiftUpdatedListener;
    private boolean onlyWearInfo;
    private int overrideNextIndex;
    private View ownerTextView;
    private ArrayList<TL_stars.StarGiftUpgradePrice> prices;
    private boolean requesting_upgrade_form;
    private boolean resale;
    private StarGiftSheet right;
    private Roller roller;
    private boolean rolling;
    private ArrayList<TL_stars.StarGiftAttribute> sample_attributes;
    private TL_stars.SavedStarGift savedStarGift;
    private ShareAlert shareAlert;
    private boolean shownWearInfo;
    private String slug;
    private TL_stars.TL_starGiftUnique slugStarGift;
    private final ColoredImageSpan[] starCached;
    private ValueAnimator switchingPagesAnimator;
    private final TableView tableView;
    private final Runnable tickUpgradePriceRunnable;
    private String title;
    private final TopView topView;
    private final FrameLayout underButtonContainer;
    private final LinkSpanDrawable.LinksTextView underButtonLinkTextView;
    private Boolean unsavedFromSavedStarGift;
    private final AffiliateProgramFragment.FeatureCell[] upgradeFeatureCells;
    private ColoredImageSpan upgradeIconSpan;
    private final LinearLayout upgradeLayout;
    private UpgradePricesSheet upgradeSheet;
    private TLRPC.PaymentForm upgrade_form;
    private boolean upgradedOnce;
    private boolean userStarGiftRepolled;
    private boolean userStarGiftRepolling;
    private ViewPagerFixed viewPager;
    private final AffiliateProgramFragment.FeatureCell[] wearFeatureCells;
    private final LinearLayout wearLayout;
    private final TextView wearSubtitle;
    private final TextView wearTitle;

    /* renamed from: org.telegram.ui.Stars.StarGiftSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ StarGiftSheet this$0;

        public AnonymousClass1(StarGiftSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void setTranslationY(float r1) {
            super.setTranslationY(r1);
            if (StarGiftSheet.m19000$$Nest$fgetactionView(this.this$0) != null) goto L5;
            return;
        L5:
            if (StarGiftSheet.m19000$$Nest$fgetactionView(this.this$0).getVisibility() != 0) goto L9;
            StarGiftSheet.m19000$$Nest$fgetactionView(this.this$0).invalidate();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarGiftSheet$10, reason: invalid class name */
    public class AnonymousClass10 extends BaseFragment {
        final /* synthetic */ StarGiftSheet this$0;

        public AnonymousClass10(StarGiftSheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Context getContext() {
            return this.this$0.getContext();
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public int getCurrentAccount() {
            return this.currentAccount;
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Activity getParentActivity() {
            Context r1 = getContext();
        L4:
            if ((r1 instanceof ContextWrapper) == false) goto L10;
            if ((r1 instanceof Activity) == true) goto L8;
            r1 = ((ContextWrapper) r1).getBaseContext();
            goto L4
        L8:
            return (Activity) r1;
        L10:
            return null;
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Dialog showDialog(Dialog r1) {
            r1.show();
            return r1;
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarGiftSheet$11, reason: invalid class name */
    public class AnonymousClass11 extends ShareAlert {
        final /* synthetic */ StarGiftSheet this$0;

        public AnonymousClass11(StarGiftSheet r16, Context r17, ChatActivity r18, ArrayList r19, String r20, String r21, String r22, boolean r23, String r24, String r25, boolean r26, boolean r27, boolean r28, Integer r29, Theme.ResourcesProvider r30) {
            this.this$0 = r16;
            super(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30);
            this.includeStoryFromMessage = true;
        }

        @Override // org.telegram.ui.Components.ShareAlert
        public void onSend(LongSparseArray<TLRPC.Dialog> r7, int r8, TLRPC.TL_forumTopic r9, boolean r10) {
            if (r10 == false) goto L26;
            super.onSend(r7, r8, r9, r10);
            BulletinFactory r82 = getBulletinFactory();
            if (r82 != null) goto L7;
            return;
        L7:
            if (r7.size() != 1) goto L19;
            long r2 = r7.keyAt(0);
            if (r2 != UserConfig.getInstance(this.currentAccount).clientUserId) goto L11;
            r82.createSimpleBulletin(R.raw.saved_messages, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.LinkSharedToSavedMessages, new Object[0])), 5000).hideAfterBottomSheet(false).ignoreDetach().show();
        L23:
            this.container.performHapticFeedback(3);     // Catch: Exception -> L22
            return;
        L27:
            return;
        L11:
            int r0 = this.currentAccount;
            if (r2 >= 0) goto L18;
            TLRPC.Chat r72 = MessagesController.getInstance(r0).getChat(Long.valueOf(-r2));
            int r02 = R.raw.forward;
            int r22 = R.string.LinkSharedTo;
            if (r9 == null) goto L16;
            String r73 = r9.title;
        L17:
            r82.createSimpleBulletin(r02, AndroidUtilities.replaceTags(LocaleController.formatString(r22, new Object[]{r73})), 5000).hideAfterBottomSheet(false).ignoreDetach().show();
            goto L23
        L16:
            r73 = r72.title;
            goto L17
        L18:
            TLRPC.User r74 = MessagesController.getInstance(r0).getUser(Long.valueOf(r2));
            r82.createSimpleBulletin(R.raw.forward, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.LinkSharedTo, new Object[]{r74.first_name})), 5000).hideAfterBottomSheet(false).ignoreDetach().show();
            goto L23
        L19:
            r82.createSimpleBulletin(R.raw.forward, AndroidUtilities.replaceTags(LocaleController.formatPluralString("LinkSharedToManyChats", r7.size(), new Object[]{Integer.valueOf(r7.size())}))).hideAfterBottomSheet(false).ignoreDetach().show();
            goto L23
        }

        @Override // org.telegram.ui.Components.ShareAlert
        public void onShareStory(View r1) {
            StarGiftSheet.m19025$$Nest$mrepostStory(this.this$0, r1);
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarGiftSheet$12, reason: invalid class name */
    public class AnonymousClass12 implements ShareAlert.ShareAlertDelegate {
        final /* synthetic */ StarGiftSheet this$0;

        public AnonymousClass12(StarGiftSheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.ShareAlert.ShareAlertDelegate
        public boolean didCopy() {
            this.this$0.getBulletinFactory().createCopyLinkBulletin(false).ignoreDetach().show();
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarGiftSheet$13, reason: invalid class name */
    public class AnonymousClass13 extends AnimatorListenerAdapter {
        final /* synthetic */ StarGiftSheet this$0;
        final /* synthetic */ Runnable val$done;
        final /* synthetic */ int val$page;

        public AnonymousClass13(StarGiftSheet r1, int r2, Runnable r3) {
            this.this$0 = r1;
            this.val$page = r2;
            this.val$done = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r5) {
            StarGiftSheet.m19023$$Nest$monSwitchedPage(this.this$0);
            LinearLayout r52 = StarGiftSheet.m19008$$Nest$fgetinfoLayout(this.this$0);
            int r1 = 8;
            if (this.val$page != 0) goto L5;
            int r0 = 0;
        L6:
            r52.setVisibility(r0);
            LinearLayout r53 = StarGiftSheet.m19014$$Nest$fgetupgradeLayout(this.this$0);
            if (this.val$page != 1) goto L9;
            int r02 = 0;
        L10:
            r53.setVisibility(r02);
            LinearLayout r54 = StarGiftSheet.m19015$$Nest$fgetwearLayout(this.this$0);
            if (this.val$page != 2) goto L13;
            int r03 = 0;
        L14:
            r54.setVisibility(r03);
            LinearLayout r55 = StarGiftSheet.m19004$$Nest$fgetcraftLayout(this.this$0);
            if (this.val$page != 3) goto L17;
            r1 = 0;
        L17:
            r55.setVisibility(r1);
            StarGiftSheet.m19027$$Nest$mupdateUnderButtonContainer(this.this$0);
            StarGiftSheet.m19018$$Nest$fputswitchingPagesAnimator(this.this$0, null);
            Runnable r4 = this.val$done;
            if (r4 == null) goto L21;
            r4.run();
            return;
        L21:
            return;
        L13:
            r03 = 8;
            goto L14
        L9:
            r02 = 8;
            goto L10
        L5:
            r0 = 8;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarGiftSheet$14, reason: invalid class name */
    public class AnonymousClass14 extends ClickableSpan {
        final /* synthetic */ Runnable val$click;

        public AnonymousClass14(Runnable r1) {
            this.val$click = r1;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r1) {
            Runnable r0 = this.val$click;
            if (r0 == null) goto L6;
            r0.run();
            return;
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            r1.setUnderlineText(false);
            r1.setColor(-1);
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarGiftSheet$15, reason: invalid class name */
    public class AnonymousClass15 extends ClickableSpan {
        final /* synthetic */ StarGiftSheet this$0;
        final /* synthetic */ long val$sender_id;

        public AnonymousClass15(StarGiftSheet r1, long r2) {
            this.this$0 = r1;
            this.val$sender_id = r2;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r3) {
            StarGiftSheet.m19024$$Nest$mopenProfile(this.this$0, this.val$sender_id);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            r1.setColor(r1.linkColor);
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarGiftSheet$16, reason: invalid class name */
    public class AnonymousClass16 extends ClickableSpan {
        final /* synthetic */ StarGiftSheet this$0;
        final /* synthetic */ long val$recipient_id;

        public AnonymousClass16(StarGiftSheet r1, long r2) {
            this.this$0 = r1;
            this.val$recipient_id = r2;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r3) {
            StarGiftSheet.m19024$$Nest$mopenProfile(this.this$0, this.val$recipient_id);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            r1.setColor(r1.linkColor);
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarGiftSheet$17, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass17 {
        static final /* synthetic */ int[] $SwitchMap$org$telegram$ui$Stars$StarGiftSheet$CraftTopView$Cube3D$AnimSequence$CmdType = null;

        static {
            int[] r0 = new int[CraftTopView.Cube3D.AnimSequence.CmdType.values().length];
            $SwitchMap$org$telegram$ui$Stars$StarGiftSheet$CraftTopView$Cube3D$AnimSequence$CmdType = r0;
            r0[CraftTopView.Cube3D.AnimSequence.CmdType.RUN.ordinal()] = 1;     // Catch: NoSuchFieldError -> L10
        L16:
            $SwitchMap$org$telegram$ui$Stars$StarGiftSheet$CraftTopView$Cube3D$AnimSequence$CmdType[CraftTopView.Cube3D.AnimSequence.CmdType.FLING.ordinal()] = 2;     // Catch: NoSuchFieldError -> L11
        L20:
            $SwitchMap$org$telegram$ui$Stars$StarGiftSheet$CraftTopView$Cube3D$AnimSequence$CmdType[CraftTopView.Cube3D.AnimSequence.CmdType.FRICTION.ordinal()] = 3;     // Catch: NoSuchFieldError -> L12
        L26:
            $SwitchMap$org$telegram$ui$Stars$StarGiftSheet$CraftTopView$Cube3D$AnimSequence$CmdType[CraftTopView.Cube3D.AnimSequence.CmdType.DELAY.ordinal()] = 4;     // Catch: NoSuchFieldError -> L13
        L18:
            $SwitchMap$org$telegram$ui$Stars$StarGiftSheet$CraftTopView$Cube3D$AnimSequence$CmdType[CraftTopView.Cube3D.AnimSequence.CmdType.STEER.ordinal()] = 5;     // Catch: NoSuchFieldError -> L14
        L22:
            $SwitchMap$org$telegram$ui$Stars$StarGiftSheet$CraftTopView$Cube3D$AnimSequence$CmdType[CraftTopView.Cube3D.AnimSequence.CmdType.PUT.ordinal()] = 6;     // Catch: NoSuchFieldError -> L15
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarGiftSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends ViewPagerFixed {
        final /* synthetic */ StarGiftSheet this$0;

        public static /* synthetic */ void $r8$lambda$pVGkYMzBwBuC6fNWwksB58xC58k(AnonymousClass2 r0, boolean r1) {
            r0.lambda$swapViews$0(r1);
        }

        public AnonymousClass2(StarGiftSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        private /* synthetic */ void lambda$swapViews$0(boolean r4) {
            TL_stars.SavedStarGift r0 = StarGiftSheet.m19020$$Nest$mgetNeighbourSavedGift(this.this$0, r4);
            StarGiftSheet r1 = this.this$0;
            if (r0 == null) goto L5;
            StarGiftSheet.m19016$$Nest$fputfirstSet(r1, true);
            StarGiftSheet r42 = this.this$0;
            r42.set(r0, StarGiftSheet.m19006$$Nest$fgetgiftsList(r42));
        L8:
            StarGiftSheet.m19017$$Nest$fputoverrideNextIndex(this.this$0, -1);
            if (Bulletin.getVisibleBulletin() == null) goto L12;
            Bulletin.getVisibleBulletin().hide(false, 0);
            return;
        L12:
            return;
        L5:
            TL_stars.TL_starGiftUnique r43 = StarGiftSheet.m19021$$Nest$mgetNeighbourSlugGift(r1, r4);
            if (r43 == null) goto L8;
            StarGiftSheet.m19016$$Nest$fputfirstSet(this.this$0, true);
            StarGiftSheet r02 = this.this$0;
            r02.set(r43.slug, r43, StarGiftSheet.m19006$$Nest$fgetgiftsList(r02));
            goto L8
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public boolean canScroll(MotionEvent r1) {
            if (StarGiftSheet.m19005$$Nest$fgetcurrentPage(this.this$0) != null) goto L5;
            return true;
        L5:
            if (StarGiftSheet.m19005$$Nest$fgetcurrentPage(this.this$0).is(0) == true) goto L10;
            return false;
        L10:
            return true;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void setTranslationX(View r5, float r6) {
            if (getMeasuredWidth() > 0) goto L6;
            r5.setTranslationX(r6);
            return;
        L6:
            float r0 = Utilities.clamp(r6 / getMeasuredWidth(), 1.0f, -1.0f);
            r5.setTranslationX(r6 + (((-r0) * 2.0f) * StarGiftSheet.access$000(this.this$0)));
            float r62 = 0.0f;
            if (r0 > 0.0f) goto L10;
            r62 = r5.getMeasuredWidth();
        L10:
            r5.setPivotX(r62);
            r5.setCameraDistance(r5.getMeasuredHeight() * 3.4f);
            r5.setScaleX(1.0f - Math.abs(0.25f * r0));
            r5.setRotationY(r0 * 10.0f);
            if ((r5 instanceof FrameLayout) == false) goto L15;
            FrameLayout r52 = (FrameLayout) r5;
            if (r52.getChildCount() <= 0) goto L15;
            View r53 = r52.getChildAt(0);
        L17:
            if (StarGiftSheet.m19010$$Nest$fgetleft(this.this$0) == null) goto L24;
            if (r53 != StarGiftSheet.m19003$$Nest$fgetcontainer(StarGiftSheet.m19010$$Nest$fgetleft(this.this$0))) goto L24;
            if (StarGiftSheet.m19000$$Nest$fgetactionView(StarGiftSheet.m19010$$Nest$fgetleft(this.this$0)) == null) goto L24;
            StarGiftSheet.m19000$$Nest$fgetactionView(StarGiftSheet.m19010$$Nest$fgetleft(this.this$0)).invalidate();
        L24:
            if (r53 != StarGiftSheet.m19003$$Nest$fgetcontainer(this.this$0)) goto L29;
            if (StarGiftSheet.m19000$$Nest$fgetactionView(this.this$0) == null) goto L29;
            StarGiftSheet.m19000$$Nest$fgetactionView(this.this$0).invalidate();
        L29:
            if (StarGiftSheet.m19011$$Nest$fgetright(this.this$0) != null) goto L31;
            return;
        L31:
            if (r53 == StarGiftSheet.m19003$$Nest$fgetcontainer(StarGiftSheet.m19011$$Nest$fgetright(this.this$0))) goto L33;
            return;
        L33:
            if (StarGiftSheet.m19000$$Nest$fgetactionView(StarGiftSheet.m19011$$Nest$fgetright(this.this$0)) == null) goto L38;
            StarGiftSheet.m19000$$Nest$fgetactionView(StarGiftSheet.m19011$$Nest$fgetright(this.this$0)).invalidate();
            return;
        L38:
            return;
        L15:
            r53 = null;
            goto L17
        }

        /* JADX WARN: Type inference failed for: r1v3, types: [boolean] */
        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void swapViews() {
            super.swapViews();
            boolean r2 = false;
            if (this.currentPosition != StarGiftSheet.m19022$$Nest$mhasNeighbour(this.this$0, false)) goto L5;
            return;
        L5:
            if (this.currentPosition <= StarGiftSheet.m19022$$Nest$mhasNeighbour(this.this$0, false)) goto L7;
            r2 = true;
        L7:
            AndroidUtilities.runOnUIThread(new StarGiftSheet$2$$ExternalSyntheticLambda0(this, r2));
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarGiftSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends ViewPagerFixed.Adapter {
        final /* synthetic */ StarGiftSheet this$0;
        final /* synthetic */ Context val$context;

        public AnonymousClass3(StarGiftSheet r1, Context r2) {
            this.this$0 = r1;
            this.val$context = r2;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public void bindView(View r2, int r3, int r4) {
            if (r4 != 0) goto L9;
            StarGiftSheet.m19026$$Nest$msetupNeighbour(this.this$0, false, true);
            if (StarGiftSheet.m19010$$Nest$fgetleft(this.this$0) == null) goto L17;
            ContainerView r1 = StarGiftSheet.m19003$$Nest$fgetcontainer(StarGiftSheet.m19010$$Nest$fgetleft(this.this$0));
        L14:
            FrameLayout r22 = (FrameLayout) r2;
            r22.removeAllViews();
            AndroidUtilities.removeFromParent(r1);
            r22.addView(r1);
            return;
        L17:
            return;
        L9:
            if (r4 != 2) goto L16;
            StarGiftSheet.m19026$$Nest$msetupNeighbour(this.this$0, true, true);
            if (StarGiftSheet.m19011$$Nest$fgetright(this.this$0) == null) goto L18;
            r1 = StarGiftSheet.m19003$$Nest$fgetcontainer(StarGiftSheet.m19011$$Nest$fgetright(this.this$0));
            goto L14
        L18:
            return;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public View createView(int r5) {
            if (r5 != 0) goto L9;
            StarGiftSheet.m19026$$Nest$msetupNeighbour(this.this$0, false, false);
            if (StarGiftSheet.m19010$$Nest$fgetleft(this.this$0) != null) goto L7;
            return null;
        L7:
            ContainerView r52 = StarGiftSheet.m19003$$Nest$fgetcontainer(StarGiftSheet.m19010$$Nest$fgetleft(this.this$0));
        L17:
            AndroidUtilities.removeFromParent(r52);
            FrameLayout r0 = new FrameLayout(this.val$context);
            r0.addView(r52, LayoutHelper.createFrame(-1, -1, 119));
            return r0;
        L9:
            if (r5 != 1) goto L12;
            r52 = StarGiftSheet.m19003$$Nest$fgetcontainer(this.this$0);
            goto L17
        L12:
            if (r5 != 2) goto L19;
            StarGiftSheet.m19026$$Nest$msetupNeighbour(this.this$0, true, false);
            if (StarGiftSheet.m19011$$Nest$fgetright(this.this$0) != null) goto L16;
            return null;
        L16:
            r52 = StarGiftSheet.m19003$$Nest$fgetcontainer(StarGiftSheet.m19011$$Nest$fgetright(this.this$0));
            goto L17
        L19:
            return null;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemCount() {
            return ((StarGiftSheet.m19022$$Nest$mhasNeighbour(this.this$0, false) ? 1 : 0) + 1) + (StarGiftSheet.m19022$$Nest$mhasNeighbour(this.this$0, true) ? 1 : 0);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemViewType(int r2) {
            return (r2 - (StarGiftSheet.m19022$$Nest$mhasNeighbour(this.this$0, false) ? 1 : 0)) + 1;
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarGiftSheet$4, reason: invalid class name */
    public class AnonymousClass4 extends LinearLayout {
        final /* synthetic */ StarGiftSheet this$0;

        public AnonymousClass4(StarGiftSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            if (StarGiftSheet.m19005$$Nest$fgetcurrentPage(this.this$0).is(0) == true) goto L6;
            return false;
        L6:
            return super.dispatchTouchEvent(r3);
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarGiftSheet$5, reason: invalid class name */
    public class AnonymousClass5 extends LinearLayout {
        final /* synthetic */ StarGiftSheet this$0;

        public AnonymousClass5(StarGiftSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            if (StarGiftSheet.m19005$$Nest$fgetcurrentPage(this.this$0).is(1) == true) goto L7;
            return false;
        L7:
            return super.dispatchTouchEvent(r3);
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarGiftSheet$6, reason: invalid class name */
    public class AnonymousClass6 extends LinearLayout {
        final /* synthetic */ StarGiftSheet this$0;

        public AnonymousClass6(StarGiftSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            if (StarGiftSheet.m19005$$Nest$fgetcurrentPage(this.this$0).is(2) == true) goto L7;
            return false;
        L7:
            return super.dispatchTouchEvent(r3);
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarGiftSheet$7, reason: invalid class name */
    public class AnonymousClass7 extends LinearLayout {
        final /* synthetic */ StarGiftSheet this$0;

        public AnonymousClass7(StarGiftSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            if (StarGiftSheet.m19005$$Nest$fgetcurrentPage(this.this$0).is(3) == true) goto L7;
            return false;
        L7:
            return super.dispatchTouchEvent(r3);
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarGiftSheet$8, reason: invalid class name */
    public class AnonymousClass8 extends RecyclerView.OnScrollListener {
        final /* synthetic */ StarGiftSheet this$0;

        public AnonymousClass8(StarGiftSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            StarGiftSheet.m19003$$Nest$fgetcontainer(this.this$0).updateTranslations();
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarGiftSheet$9, reason: invalid class name */
    public class AnonymousClass9 extends StarGiftSheet {
        final /* synthetic */ StarGiftSheet this$0;

        public AnonymousClass9(StarGiftSheet r1, Context r2, int r3, long r4, Theme.ResourcesProvider r6, View r7) {
            this.this$0 = r1;
            super(r2, r3, r4, r6, r7);
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet
        public int getBottomInset() {
            return this.this$0.getBottomInset();
        }
    }

    public static class ActionView extends View {
        private final Paint bgDarkerPaint;
        private final Paint bgPaint;
        private BitmapShader blurBitmapShader;
        private Matrix blurInvertMatrix;
        private Matrix blurMatrix;
        private boolean fullRect;
        private StaticLayout layout;
        private final TextPaint paint;
        private final LinkPath path;
        private int px;
        private int py;
        private CharSequence textToSet;

        public static /* synthetic */ void $r8$lambda$PRbqyJLWwsMs7wHivmB5PWcuswM(ActionView r0, Bitmap r1) {
            r0.lambda$prepareBlur$0(r1);
        }

        public ActionView(Context r5) {
            super(r5);
            this.px = AndroidUtilities.dp(6.0f);
            this.py = AndroidUtilities.dp(2.0f);
            TextPaint r52 = new TextPaint(1);
            this.paint = r52;
            r52.setColor(-1);
            r52.setTextSize(AndroidUtilities.dp(13.0f));
            Paint r53 = new Paint(1);
            this.bgPaint = r53;
            r53.setPathEffect(new CornerPathEffect(AndroidUtilities.dp(9.66f)));
            Paint r54 = new Paint(1);
            this.bgDarkerPaint = r54;
            r54.setPathEffect(new CornerPathEffect(AndroidUtilities.dp(9.66f)));
            this.path = new LinkPath(true);
        }

        private /* synthetic */ void lambda$prepareBlur$0(Bitmap r3) {
            this.blurMatrix = new Matrix();
            this.blurInvertMatrix = new Matrix();
            Shader.TileMode r1 = Shader.TileMode.CLAMP;
            BitmapShader r0 = new BitmapShader(r3, r1, r1);
            this.blurBitmapShader = r0;
            this.bgPaint.setShader(r0);
            ColorMatrix r32 = new ColorMatrix();
            AndroidUtilities.adjustSaturationColorMatrix(r32, 0.25f);
            this.bgPaint.setColorFilter(new ColorMatrixColorFilter(r32));
        }

        private void set(CharSequence r10, int r11) {
            if (r11 > 0) goto L5;
            this.textToSet = r10;
            return;
        L5:
            this.layout = new StaticLayout(r10, this.paint, r11 - AndroidUtilities.dp(18.0f), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            this.path.rewind();
            this.path.setPadding(this.px, this.py);
            boolean r102 = this.fullRect;
            LinkPath r112 = this.path;
            int r0 = 0;
            if (r102 == false) goto L12;
            r112.setCurrentLayout(null, 0, 0.0f, 0.0f);
            float r2 = Float.MAX_VALUE;
            float r4 = this.layout.getWidth();
            float r5 = Float.MIN_VALUE;
            float r6 = 0.0f;
        L9:
            if (r0 >= this.layout.getLineCount()) goto L11;
            r4 = Math.min(r4, this.layout.getLineLeft(r0));
            r5 = Math.min(r5, this.layout.getLineTop(r0));
            r6 = Math.max(r6, this.layout.getLineRight(r0));
            r2 = Math.max(r2, this.layout.getLineBottom(r0));
            r0 = r0 + 1;
            goto L9
        L11:
            this.path.addRect(r4, r5, r6, this.layout.getHeight(), Path.Direction.CW);
        L13:
            invalidate();
            return;
        L12:
            r112.setCurrentLayout(this.layout, 0, 0.0f, 0.0f);
            StaticLayout r103 = this.layout;
            r103.getSelectionPath(0, r103.getText().length(), this.path);
            this.path.closeRects();
            goto L13
        }

        @Override // android.view.View
        public void onDraw(Canvas r5) {
            super.onDraw(r5);
            if (this.layout == null) goto L22;
            r5.save();
            r5.translate((getWidth() - this.layout.getWidth()) / 2.0f, AndroidUtilities.dp(16.0f));
            Matrix r0 = this.blurMatrix;
            if (r0 == null) goto L14;
            r0.reset();
            this.blurInvertMatrix.reset();
            View r02 = this;
        L7:
            Matrix r2 = this.blurInvertMatrix;
            if (r02 == null) goto L13;
            r2.postConcat(r02.getMatrix());
            if ((r02.getParent() instanceof View) == true) goto L11;
            r02 = null;
            goto L7
        L11:
            r02 = (View) r02.getParent();
            goto L7
        L13:
            r2.invert(this.blurMatrix);
            this.blurMatrix.preTranslate((-this.px) / 2, -AndroidUtilities.dp(16.0f));
            this.blurMatrix.preScale(12.0f, 12.0f);
            this.blurBitmapShader.setLocalMatrix(this.blurMatrix);
        L14:
            r5.drawPath(this.path, this.bgPaint);
            this.bgDarkerPaint.setColor(Theme.multAlpha(-16777216, 0.35f));
            r5.drawPath(this.path, this.bgDarkerPaint);
            this.layout.draw(r5);
            r5.restore();
            return;
        }

        @Override // android.view.View
        public void onMeasure(int r3, int r4) {
            int r32 = View.MeasureSpec.getSize(r3);
            CharSequence r42 = this.textToSet;
            if (r42 == null) goto L5;
            set(r42, r32);
        L5:
            int r33 = View.MeasureSpec.makeMeasureSpec(r32, TLObject.FLAG_30);
            StaticLayout r0 = this.layout;
            if (r0 != null) goto L8;
            int r02 = 0;
        L9:
            super.onMeasure(r33, View.MeasureSpec.makeMeasureSpec(r02, TLObject.FLAG_30));
            setPivotX(getMeasuredWidth() / 2.0f);
            setPivotY(getMeasuredHeight());
            return;
        L8:
            r02 = r0.getHeight() + AndroidUtilities.dp(32.0f);
            goto L9
        }

        public void prepareBlur(View r4) {
            ArrayList r0 = new ArrayList();
            if (r4 == null) goto L5;
            r0.add(r4);
        L5:
            AndroidUtilities.makeGlobalBlurBitmap(new StarGiftSheet$ActionView$$ExternalSyntheticLambda0(this), 12.0f, 12, null, r0);
        }

        public void set(int r8, TL_stars.SavedStarGift r9) {
            if (r9 != null) goto L4;
        L22:
            setVisibility(8);
            return;
        L4:
            if (r9.from_id == null) goto L22;
            if ((r9.gift instanceof TL_stars.TL_starGiftUnique) == false) goto L22;
            setVisibility(0);
            long r0 = UserConfig.getInstance(r8).getClientUserId();
            long r2 = DialogObject.getPeerDialogId(r9.from_id);
            long r4 = DialogObject.getPeerDialogId(r9.gift.owner_id);
            if (r0 != r2) goto L17;
            if (r9.gift.crafted == false) goto L13;
            int r82 = R.string.GiftSelfTopActionCrafted;
        L14:
            set(AndroidUtilities.replaceTags(LocaleController.formatString(r82, new Object[]{LocaleController.formatDate(r9.date)})));
            return;
        L13:
            r82 = R.string.GiftSelfTopAction;
            goto L14
        L17:
            if (r0 != r4) goto L20;
            set(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GiftTopAction, new Object[]{DialogObject.getShortName(r8, r2), LocaleController.formatDate(r9.date)})));
            return;
        L20:
            set(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GiftTopActionFromTo, new Object[]{DialogObject.getShortName(r8, r2), DialogObject.getShortName(r8, r4), LocaleController.formatDate(r9.date)})));
        }

        public void set(CharSequence r2) {
            set(r2, getMeasuredWidth());
        }

        public void set(MessageObject r10) {
            if (r10 == null) goto L37;
            TLRPC.Message r1 = r10.messageOwner;
            if (r1 == null) goto L37;
            if (r1.action == null) goto L37;
            int r12 = r10.currentAccount;
            long r2 = UserConfig.getInstance(r12).getClientUserId();
            TLRPC.MessageAction r4 = r10.messageOwner.action;
            if ((r4 instanceof TLRPC.TL_messageActionStarGift) == false) goto L14;
            TLRPC.TL_messageActionStarGift r42 = (TLRPC.TL_messageActionStarGift) r4;
            setVisibility(8);
            return;
        L14:
            if ((r4 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L35;
            TLRPC.TL_messageActionStarGiftUnique r43 = (TLRPC.TL_messageActionStarGiftUnique) r4;
            TLRPC.Peer r5 = r43.from_id;
            if (r5 != null) goto L19;
            setVisibility(8);
            return;
        L19:
            long r52 = DialogObject.getPeerDialogId(r5);
            long r7 = DialogObject.getPeerDialogId(r43.peer);
            if (r2 != r52) goto L30;
            if (r43.craft == false) goto L24;
        L27:
            int r0 = R.string.GiftSelfTopActionCrafted;
        L28:
            set(AndroidUtilities.replaceTags(LocaleController.formatString(r0, new Object[]{LocaleController.formatDate(r10.messageOwner.date)})));
        L33:
            setVisibility(0);
            return;
        L24:
            if (r43.gift.crafted == true) goto L27;
            r0 = R.string.GiftSelfTopAction;
            goto L28
        L30:
            if (r2 != r7) goto L32;
            set(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GiftTopAction, new Object[]{DialogObject.getShortName(r12, r52), LocaleController.formatDate(r10.messageOwner.date)})));
            goto L33
        L32:
            set(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GiftTopActionFromTo, new Object[]{DialogObject.getShortName(r12, r52), DialogObject.getShortName(r12, r7), LocaleController.formatDate(r10.messageOwner.date)})));
            goto L33
        L35:
            setVisibility(8);
            return;
        L37:
            setVisibility(8);
        }

        public void setFullRect(boolean r1) {
            this.fullRect = r1;
        }

        public void setPadding(int r1, int r2) {
            this.px = r1;
            this.py = r2;
        }

        public void setRoundRadius(float r3) {
            this.bgPaint.setPathEffect(new CornerPathEffect(r3));
            this.bgDarkerPaint.setPathEffect(new CornerPathEffect(r3));
        }

        @Override // android.view.View
        public void setTranslationY(float r1) {
            super.setTranslationY(r1);
            invalidate();
        }
    }

    public class Adapter extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ StarGiftSheet this$0;

        private Adapter(StarGiftSheet r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Adapter(StarGiftSheet r1, StarGiftSheetIA r2) {
            this(r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return StarGiftSheet.m19007$$Nest$fgetheights(this.this$0).length;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r2, int r3) {
            int r0 = (StarGiftSheet.m19007$$Nest$fgetheights(this.this$0).length - 1) - r3;
            ((BottomSheetLayouted.SpaceView) r2.itemView).setHeight(StarGiftSheet.m19007$$Nest$fgetheights(this.this$0)[r0], r0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
            return new RecyclerListView.Holder(new BottomSheetLayouted.SpaceView(this.this$0.getContext()));
        }

        public void setHeights(int r4, int r5) {
            if (StarGiftSheet.m19007$$Nest$fgetheights(this.this$0)[0] == r4) goto L5;
        L8:
            StarGiftSheet.m19007$$Nest$fgetheights(this.this$0)[0] = r4;
            StarGiftSheet.m19007$$Nest$fgetheights(this.this$0)[1] = r5;
            notifyDataSetChanged();
            return;
        L5:
            if (StarGiftSheet.m19007$$Nest$fgetheights(this.this$0)[1] != r5) goto L8;
        }
    }

    public interface BoughtGiftCallback {
        void onBoughtGift(TL_stars.TL_starGiftUnique r1, long r2, boolean r4);
    }

    public class ContainerView extends FrameLayout {
        private final Paint backgroundPaint;
        private float dimAlpha;
        private final Path path;
        private final RectF rect;
        final /* synthetic */ StarGiftSheet this$0;

        public ContainerView(StarGiftSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.rect = new RectF();
            this.backgroundPaint = new Paint(1);
            this.path = new Path();
            this.dimAlpha = 0.0f;
            setWillNotDraw(false);
            setClipChildren(false);
            setClipToPadding(false);
        }

        private void drawView(Canvas r9, View r10) {
            if (r10 != null) goto L4;
            return;
        L4:
            if (r10.getVisibility() == 0) goto L6;
            return;
        L6:
            if (r10.getAlpha() > 0.0f) goto L9;
            return;
        L9:
            if (r10.getAlpha() >= 1.0f) goto L11;
            Canvas r1 = r9;
            r1.saveLayerAlpha(r10.getX(), r10.getY(), r10.getX() + r10.getMeasuredWidth(), r10.getY() + r10.getMeasuredHeight(), (int) (StarGiftSheet.access$800(this.this$0).getAlpha() * 255.0f), 31);
        L12:
            r1.translate(r10.getX(), r10.getY());
            r10.draw(r1);
            r1.restore();
            return;
        L11:
            r1 = r9;
            r1.save();
            r1.clipRect(r10.getX(), r10.getY(), r10.getX() + r10.getMeasuredWidth(), r10.getY() + StarGiftSheet.access$900(this.this$0).getMeasuredHeight());
            goto L12
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r7) {
            StarGiftSheet.access$200(this.this$0, r7, this);
            r7.save();
            float r0 = top();
            float r1 = AndroidUtilities.dp(12.0f);
            this.rect.set(StarGiftSheet.access$300(this.this$0), r0, getWidth() - StarGiftSheet.access$400(this.this$0), getHeight() + r1);
            this.backgroundPaint.setColor(StarGiftSheet.access$500(this.this$0, Theme.key_dialogBackground));
            this.path.rewind();
            this.path.addRoundRect(this.rect, r1, r1, Path.Direction.CW);
            r7.drawPath(this.path, this.backgroundPaint);
            super.dispatchDraw(r7);
            float r02 = this.dimAlpha;
            if (r02 == 0.0f) goto L5;
            r7.drawColor(Theme.multAlpha(-16777216, r02));
        L5:
            updateTranslations();
            r7.restore();
            drawView(r7, StarGiftSheet.access$600(this.this$0));
            StarGiftSheet.access$700(this.this$0, r7, this);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L11;
            if (r3.getY() >= top()) goto L11;
            if (StarGiftSheet.access$100(this.this$0).isAttachedToWindow() == false) goto L11;
            this.this$0.dismiss();
            return true;
        L11:
            return super.dispatchTouchEvent(r3);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r2, View r3, long r4) {
            if (r3 != StarGiftSheet.access$1000(this.this$0)) goto L7;
            return false;
        L7:
            if (r3 == StarGiftSheet.m19000$$Nest$fgetactionView(this.this$0)) goto L11;
            r2.save();
            r2.clipPath(this.path);
            boolean r1 = super.drawChild(r2, r3, r4);
            r2.restore();
            return r1;
        L11:
            return super.drawChild(r2, r3, r4);
        }

        public float height() {
            return ((((StarGiftSheet.m19012$$Nest$fgettopView(this.this$0).getRealHeight() + 0.0f) + (StarGiftSheet.m19005$$Nest$fgetcurrentPage(this.this$0).at(0) * StarGiftSheet.m19008$$Nest$fgetinfoLayout(this.this$0).getMeasuredHeight())) + (StarGiftSheet.m19005$$Nest$fgetcurrentPage(this.this$0).at(1) * StarGiftSheet.m19014$$Nest$fgetupgradeLayout(this.this$0).getMeasuredHeight())) + (StarGiftSheet.m19005$$Nest$fgetcurrentPage(this.this$0).at(2) * StarGiftSheet.m19015$$Nest$fgetwearLayout(this.this$0).getMeasuredHeight())) + (StarGiftSheet.m19005$$Nest$fgetcurrentPage(this.this$0).at(3) * StarGiftSheet.m19004$$Nest$fgetcraftLayout(this.this$0).getMeasuredHeight());
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            if (StarGiftSheet.m19001$$Nest$fgetadapter(this.this$0) == null) goto L11;
            Adapter r12 = StarGiftSheet.m19001$$Nest$fgetadapter(this.this$0);
            int r22 = StarGiftSheet.m19012$$Nest$fgettopView(this.this$0).getFinalHeight();
            int r32 = StarGiftSheet.m19019$$Nest$mgetBottomHeight(this.this$0);
            if (StarGiftSheet.m19005$$Nest$fgetcurrentPage(this.this$0).to(1) == true) goto L7;
        L9:
            int r42 = 0;
        L10:
            r12.setHeights(r22, r32 + r42);
            goto L11
        L7:
            if (StarGiftSheet.m19013$$Nest$fgetunderButtonContainer(this.this$0).getVisibility() != 0) goto L9;
            r42 = StarGiftSheet.m19013$$Nest$fgetunderButtonContainer(this.this$0).getMeasuredHeight();
        L11:
            StarGiftSheet.m19023$$Nest$monSwitchedPage(this.this$0);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r8, int r9) {
            int r0 = this.this$0.getBottomInset();
            int r1 = 0;
            setPadding(0, 0, 0, r0);
            TopView.m19077$$Nest$fgetcraftTopView(StarGiftSheet.m19012$$Nest$fgettopView(this.this$0)).setPadding(0, 0, 0, r0);
            int r92 = View.MeasureSpec.getSize(r9);
            StarGiftSheet.access$1502(this.this$0, r92);
            int r2 = View.MeasureSpec.getSize(r8);
            int r3 = 0;
        L4:
            if (r3 >= getChildCount()) goto L19;
            View r4 = getChildAt(r3);
            if ((r4 instanceof HintView2) == false) goto L9;
            r4.measure(r8, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(100.0f), TLObject.FLAG_30));
        L18:
            r3 = r3 + 1;
            goto L4
        L9:
            if (r4 != StarGiftSheet.access$1600(this.this$0)) goto L12;
            r4.measure(r8, View.MeasureSpec.makeMeasureSpec(r92 - r0, TLObject.FLAG_30));
            goto L18
        L12:
            if (r4.getLayoutParams() != null) goto L14;
        L16:
            int r5 = 9999;
        L17:
            r4.measure(r8, View.MeasureSpec.makeMeasureSpec(r5, Integer.MIN_VALUE));
            goto L18
        L14:
            if (r4.getLayoutParams().height != (-1)) goto L16;
            r5 = r92;
            goto L17
        L19:
            setMeasuredDimension(r2, r92);
            if (StarGiftSheet.m19001$$Nest$fgetadapter(this.this$0) == null) goto L32;
            Adapter r82 = StarGiftSheet.m19001$$Nest$fgetadapter(this.this$0);
            int r93 = StarGiftSheet.m19012$$Nest$fgettopView(this.this$0).getFinalHeight();
            int r02 = StarGiftSheet.m19019$$Nest$mgetBottomHeight(this.this$0);
            if (StarGiftSheet.m19005$$Nest$fgetcurrentPage(this.this$0).to(1) == true) goto L24;
        L26:
            r82.setHeights(r93, r02 + r1);
            return;
        L24:
            if (StarGiftSheet.m19013$$Nest$fgetunderButtonContainer(this.this$0).getVisibility() != 0) goto L26;
            r1 = StarGiftSheet.m19013$$Nest$fgetunderButtonContainer(this.this$0).getMeasuredHeight();
            goto L26
        }

        @Override // android.view.View
        public void setTranslationY(float r2) {
            super.setTranslationY(r2);
            FrameLayout r22 = this.this$0.topBulletinContainer;
            if (r22 == null) goto L6;
            r22.setTranslationY((getTranslationY() - height()) - AndroidUtilities.navigationBarHeight);
            return;
        }

        public float top() {
            float r0 = Math.max(0.0f, getHeight() - height());
            int r1 = StarGiftSheet.access$1200(this.this$0).getChildCount() - 1;
        L3:
            if (r1 < 0) goto L15;
            View r3 = StarGiftSheet.access$1300(this.this$0).getChildAt(r1);
            int r4 = StarGiftSheet.access$1400(this.this$0).getChildAdapterPosition(r3);
            if (r4 < 0) goto L14;
            if (r4 == 2) goto L9;
            if (r4 == 1) goto L11;
            if (r4 != 0) goto L14;
            r0 = r3.getY() - StarGiftSheet.m19012$$Nest$fgettopView(this.this$0).getRealHeight();
            goto L15
        L11:
            r0 = r3.getY();
            goto L15
        L9:
            r0 = (r3.getTop() + r3.getTranslationY()) + r3.getHeight();
        L14:
            r1 = r1 - 1;
        L15:
            float r02 = r0 + (this.this$0.getBottomInset() * StarGiftSheet.m19005$$Nest$fgetcurrentPage(this.this$0).at(4));
            if (StarGiftSheet.m19009$$Nest$fgetlastTop(this.this$0) != null) goto L18;
        L23:
            return r02;
        L18:
            if (StarGiftSheet.m19005$$Nest$fgetcurrentPage(this.this$0) == null) goto L23;
            if (StarGiftSheet.m19005$$Nest$fgetcurrentPage(this.this$0).progress >= 1.0f) goto L23;
            return AndroidUtilities.lerp(StarGiftSheet.m19009$$Nest$fgetlastTop(this.this$0).floatValue(), r02, StarGiftSheet.m19005$$Nest$fgetcurrentPage(this.this$0).progress);
        }

        public void updateTranslations() {
            float r0 = top();
            StarGiftSheet.m19000$$Nest$fgetactionView(this.this$0).setTranslationY(r0 - StarGiftSheet.m19000$$Nest$fgetactionView(this.this$0).getHeight());
            float r1 = Utilities.clamp01(AndroidUtilities.ilerp(r0 - StarGiftSheet.m19000$$Nest$fgetactionView(this.this$0).getHeight(), 0.0f, AndroidUtilities.dp(32.0f)));
            StarGiftSheet.m19000$$Nest$fgetactionView(this.this$0).setAlpha(StarGiftSheet.m19005$$Nest$fgetcurrentPage(this.this$0).at(0) * r1);
            StarGiftSheet.m19000$$Nest$fgetactionView(this.this$0).setScaleX(AndroidUtilities.lerp(0.5f, 1.0f, r1));
            StarGiftSheet.m19000$$Nest$fgetactionView(this.this$0).setScaleY(AndroidUtilities.lerp(0.5f, 1.0f, r1));
            StarGiftSheet.m19012$$Nest$fgettopView(this.this$0).setTranslationY(r0);
            StarGiftSheet.m19008$$Nest$fgetinfoLayout(this.this$0).setTranslationY(StarGiftSheet.m19012$$Nest$fgettopView(this.this$0).getRealHeight() + r0);
            StarGiftSheet.m19014$$Nest$fgetupgradeLayout(this.this$0).setTranslationY(StarGiftSheet.m19012$$Nest$fgettopView(this.this$0).getRealHeight() + r0);
            StarGiftSheet.m19015$$Nest$fgetwearLayout(this.this$0).setTranslationY(StarGiftSheet.m19012$$Nest$fgettopView(this.this$0).getRealHeight() + r0);
            StarGiftSheet.m19004$$Nest$fgetcraftLayout(this.this$0).setTranslationY(r0 + StarGiftSheet.m19012$$Nest$fgettopView(this.this$0).getRealHeight());
            FrameLayout r02 = this.this$0.topBulletinContainer;
            if (r02 == null) goto L5;
            r02.setTranslationY((getTranslationY() - height()) - AndroidUtilities.navigationBarHeight);
        L5:
            AndroidUtilities.updateViewVisibilityAnimated(StarGiftSheet.m19002$$Nest$fgetbuttonShadow(this.this$0), StarGiftSheet.access$1100(this.this$0).canScrollVertically(1));
        }
    }

    public static class CraftTopView extends FrameLayout {
        private final int[] BACKGROUND_COLORS;
        private final int[] COLORS;
        private final LinearLayout attributesLayoutLine1;
        private final LinearLayout attributesLayoutLine2;
        private boolean attributesTwoLines;
        private final AttributeView[] backdropAttributes;
        private final SwitchGradientDrawable bg;
        private RLottieImageView brokenGiftImage;
        private final LinearLayout button;
        private final ButtonBackground buttonBackground;
        private final AnimatedTextView buttonSubtitle;
        private final AnimatedTextView buttonTitle;
        private final FrameLayout buttonsLayout;
        private final ImageView closeButton;
        private String collectionTitle;
        public boolean crafted;
        private TL_stars.StarGift craftedGift;
        public boolean crafting;
        private final TextView craftingChanceView;
        private final TextView craftingFooterView;
        private final RLottieImageView craftingIconView;
        private final FrameLayout craftingLayout;
        private final TextView craftingSubtitleView;
        private final TextView craftingTitleView;
        private final Cube3D cube;
        private int currentAccount;
        private HintView2 currentHint;
        private TLRPC.Document document;
        private final Face[] faces;
        public boolean failed;
        private GiftSheet.GiftCell[] failedGifts;
        private final LinearLayout failedGiftsLayout;
        private final FrameLayout failedLayout;
        private final TextView failedSubtitle;
        private final TextView failedTitle;
        private final Face frontFace;
        private long giftId;
        private final SelectGiftView[] gifts;
        private final ImageView helpButton;
        private Utilities.Callback2<Utilities.Callback<TL_stars.StarGift>, Boolean> onAddGift;
        private Runnable onClose;
        private Utilities.Callback3<ArrayList<TL_stars.StarGift>, Utilities.Callback2<TL_stars.StarGift, Runnable>, Runnable> onCraft;
        private Runnable openCraftedGift;
        private final AttributeView[] patternAttributes;
        private CharSequence plus;
        private final FrameLayout precraftingLayout;
        private ArrayList<TL_stars.StarGiftAttribute> previewAttributes;
        private final RaysView rays;
        private final Theme.ResourcesProvider resourcesProvider;
        private final SpoilersTextView textView;
        private final TextView titleView;
        private final AnimatedEmojiSpan.TextViewEmojis variantsButton;

        public static class AttributeView extends FrameLayout {
            public TL_stars.starGiftAttributeBackdrop backdrop;
            private final BackupImageView imageView;
            public TL_stars.starGiftAttributePattern pattern;
            public float progress;
            private final ProgressView progressView;
            private final AnimatedTextView textView;

            public AttributeView(Context r10) {
                super(r10);
                BackupImageView r0 = new BackupImageView(r10);
                this.imageView = r0;
                r0.setRoundRadius(AndroidUtilities.dp(13.0f));
                addView(r0, LayoutHelper.createFrame(26, 26.0f, 49, 0.0f, 11.33f, 0.0f, 0.0f));
                ProgressView r02 = new ProgressView(r10);
                this.progressView = r02;
                r02.setRadius(AndroidUtilities.dp(18.0f));
                r02.setStrokeWidth(AndroidUtilities.dp(3.0f));
                addView(r02, LayoutHelper.createFrame(48, 48.0f, 49, 0.0f, 0.66f, 0.0f, 0.0f));
                AnimatedTextView r03 = new AnimatedTextView(r10);
                this.textView = r03;
                r03.setTypeface(AndroidUtilities.bold());
                r03.setGravity(17);
                r03.setTextSize(AndroidUtilities.dp(12.0f));
                r03.setTextColor(-1);
                addView(r03, LayoutHelper.createFrame(-1, 14.0f, 48, 0.0f, 39.0f, 0.0f, 0.0f));
                setProgress(0.0f, false);
                ScaleStateListAnimator.apply(this);
            }

            public void setBackdrop(TL_stars.starGiftAttributeBackdrop r11) {
                this.backdrop = r11;
                this.pattern = null;
                this.imageView.setScaleX(1.0f);
                this.imageView.setScaleY(1.0f);
                BackupImageView r0 = this.imageView;
                if (r11 == null) goto L6;
                r0.setAlpha(1.0f);
                OvalShape r02 = new OvalShape();
                r02.resize(AndroidUtilities.dp(26.0f), AndroidUtilities.dp(26.0f));
                ShapeDrawable r1 = new ShapeDrawable(r02);
                r1.setIntrinsicWidth(AndroidUtilities.dp(26.0f));
                r1.setIntrinsicHeight(AndroidUtilities.dp(26.0f));
                r1.getPaint().setShader(new RadialGradient(AndroidUtilities.dp(13.0f), AndroidUtilities.dp(13.0f), AndroidUtilities.dp(13.0f), new int[]{r11.center_color | (-16777216), r11.edge_color | (-16777216)}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
                this.imageView.setImageDrawable(r1);
                return;
            L6:
                r0.setAlpha(1.0f);
                this.imageView.setImageDrawable(Theme.createCircleDrawable(AndroidUtilities.dp(26.0f), Theme.multAlpha(-1, 0.25f)));
            }

            public void setIcon(TL_stars.starGiftAttributePattern r4) {
                this.backdrop = null;
                this.pattern = r4;
                BackupImageView r1 = this.imageView;
                if (r4 != null) goto L6;
                r1.setAlpha(0.25f);
                this.imageView.setScaleX(0.75f);
                this.imageView.setScaleY(0.75f);
                this.imageView.setTranslationY(0.0f);
                this.imageView.setAnimatedEmojiDrawable(null);
                this.imageView.setImageResource(R.drawable.mini_roll);
                return;
            L6:
                r1.setAlpha(1.0f);
                this.imageView.setScaleX(0.95f);
                this.imageView.setScaleY(0.95f);
                this.imageView.setTranslationY(AndroidUtilities.dp(2.0f));
                AnimatedEmojiDrawable r42 = AnimatedEmojiDrawable.make(UserConfig.selectedAccount, 9, r4.document);
                r42.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
                this.imageView.setAnimatedEmojiDrawable(r42);
            }

            public void setProgress(float r3, boolean r4) {
                this.progress = r3;
                this.progressView.setProgress(r3, r4);
                this.textView.setText(Math.round(r3 * 100.0f) + "%", r4);
            }
        }

        public static class ButtonBackground extends Drawable {
            private final Paint backgroundPaint;
            private final Path clipPath;
            private LinearGradient[] gradient;
            private Matrix gradientMatrix;
            private int leftColor;
            private final Paint paintStrokeBottom;
            private final Paint paintStrokeTop;
            private StarsReactionsSheet.Particles particles;
            private int rightColor;
            private AnimatedFloat swapGradient;

            public ButtonBackground() {
                Paint r0 = new Paint(1);
                this.paintStrokeTop = r0;
                Paint r2 = new Paint(1);
                this.paintStrokeBottom = r2;
                this.backgroundPaint = new Paint(1);
                this.gradient = new LinearGradient[2];
                this.gradientMatrix = new Matrix();
                this.swapGradient = new AnimatedFloat(1.0f, new StarGiftSheet$CraftTopView$ButtonBackground$$ExternalSyntheticLambda0(this), 0, 420, CubicBezierInterpolator.EASE_OUT_QUINT);
                this.clipPath = new Path();
                this.particles = new StarsReactionsSheet.Particles(1, 45);
                Paint.Style r12 = Paint.Style.STROKE;
                r0.setStyle(r12);
                r0.setColor(117440511);
                r0.setStrokeWidth(AndroidUtilities.dpf2(1.0f));
                r2.setStyle(r12);
                r2.setColor(301989887);
                r2.setStrokeWidth(AndroidUtilities.dpf2(0.6666667f));
            }

            @Override // android.graphics.drawable.Drawable
            public void draw(Canvas r10) {
                AndroidUtilities.rectTmp.set(getBounds());
                float r0 = AndroidUtilities.dp(24.0f);
                float r1 = this.swapGradient.set(1.0f);
                int r3 = 0;
            L3:
                LinearGradient[] r4 = this.gradient;
                if (r3 >= r4.length) goto L13;
                if (r4[r3] == null) goto L12;
                float r42 = (float) Math.pow(1.0f - Math.abs(r3 - r1), 0.5d);
                if (r42 <= 0.0f) goto L12;
                this.gradientMatrix.reset();
                Matrix r5 = this.gradientMatrix;
                RectF r6 = AndroidUtilities.rectTmp;
                r5.postScale(r6.width() / 100.0f, 1.0f);
                this.gradient[r3].setLocalMatrix(this.gradientMatrix);
                this.backgroundPaint.setShader(this.gradient[r3]);
                this.backgroundPaint.setAlpha((int) (r42 * 255.0f));
                r10.drawRoundRect(r6, r0, r0, this.backgroundPaint);
            L12:
                r3 = r3 + 1;
                goto L3
            L13:
                this.clipPath.rewind();
                Path r12 = this.clipPath;
                RectF r2 = AndroidUtilities.rectTmp;
                r12.addRoundRect(r2, r0, r0, Path.Direction.CW);
                r10.save();
                r10.clipPath(this.clipPath);
                this.particles.setBounds(r2);
                this.particles.setSpeed(30.0f);
                this.particles.process();
                this.particles.draw(r10, Theme.multAlpha(-1, 0.6f));
                invalidateSelf();
                r10.restore();
                AndroidUtilities.drawStroke(r10, r2, r0);
            }

            @Override // android.graphics.drawable.Drawable
            public int getOpacity() {
                return -2;
            }

            @Override // android.graphics.drawable.Drawable
            public void setAlpha(int r1) {
            }

            public void setColor(int r13, int r14) {
                if (this.leftColor == r13) goto L5;
            L7:
                LinearGradient[] r0 = this.gradient;
                r0[0] = r0[1];
                this.leftColor = r13;
                this.rightColor = r14;
                r0[1] = new LinearGradient(0.0f, 0.0f, 100.0f, 0.0f, new int[]{r13, r14}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
                this.swapGradient.force(0.0f);
                invalidateSelf();
                return;
            L5:
                if (this.rightColor != r14) goto L7;
            }

            @Override // android.graphics.drawable.Drawable
            public void setColorFilter(ColorFilter r1) {
            }
        }

        public static class Cube3D extends FrameLayout {
            private final Matrix cameraMatrix;
            private final Integer[] drawOrder;
            private final float[] faceDepths;
            private final float[][] faceNormals;
            private final float[] faceRotations;
            private View[] faces;
            private final float friction;
            private boolean frictionEnabled;
            private final HashMap<Integer, RectF> index2Position;
            private final HashMap<Integer, Integer> index2face;
            private ValueAnimator pulling;
            private int pullingIndex;
            private float pullingT;
            private final float[] rotationMatrix;
            private AnimSequence sequence;
            private final float[] transformedNormal;
            private final Runnable updateRunnable;
            private final HashSet<Integer> usedFaces;
            private float vx;
            private float vy;

            /* renamed from: org.telegram.ui.Stars.StarGiftSheet$CraftTopView$Cube3D$1, reason: invalid class name */
            public class AnonymousClass1 extends AnimatorListenerAdapter {
                final /* synthetic */ Cube3D this$0;

                public AnonymousClass1(Cube3D r1) {
                    this.this$0 = r1;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator r2) {
                    Cube3D.m19049$$Nest$fputpullingT(this.this$0, 1.0f);
                    Cube3D.m19048$$Nest$fputpullingIndex(this.this$0, -1);
                    if (Cube3D.m19043$$Nest$fgetsequence(this.this$0) != null) goto L5;
                L7:
                    Cube3D.m19047$$Nest$fputpulling(this.this$0, null);
                    return;
                L5:
                    if (AnimSequence.m19059$$Nest$fgetwaitingForPull(Cube3D.m19043$$Nest$fgetsequence(this.this$0)) == false) goto L7;
                    Cube3D.m19043$$Nest$fgetsequence(this.this$0).onPullComplete();
                    goto L7
                }
            }

            public static class AnimSequence {
                private boolean cancelled;
                private final ArrayList<Cmd> commands;
                private final Cube3D cube;
                private int currentIndex;
                private int framesRemaining;
                private Runnable onComplete;
                private float[] startMatrix;
                private float startVx;
                private float startVy;
                private float[] targetMatrix;
                private int totalFrames;
                private boolean waitingForPull;

                public static class Cmd {
                    final int face;
                    final int frames;
                    final float rotation;
                    final Runnable runnable;
                    final CmdType type;
                    final View view;
                    final float x;
                    final float y;

                    public Cmd(CmdType r1, float r2, float r3, int r4, int r5, float r6, View r7, Runnable r8) {
                        this.type = r1;
                        this.x = r2;
                        this.y = r3;
                        this.frames = r4;
                        this.face = r5;
                        this.rotation = r6;
                        this.view = r7;
                        this.runnable = r8;
                    }
                }

                public enum CmdType extends Enum<CmdType> {
                    private static final /* synthetic */ CmdType[] $VALUES = null;
                    public static final CmdType DELAY = null;
                    public static final CmdType FLING = null;
                    public static final CmdType FRICTION = null;
                    public static final CmdType PUT = null;
                    public static final CmdType RUN = null;
                    public static final CmdType STEER = null;

                    private static /* synthetic */ CmdType[] $values() {
                        return new CmdType[]{RUN, FLING, DELAY, STEER, PUT, FRICTION};
                    }

                    static {
                        RUN = new CmdType("RUN", 0);
                        FLING = new CmdType("FLING", 1);
                        DELAY = new CmdType("DELAY", 2);
                        STEER = new CmdType("STEER", 3);
                        PUT = new CmdType("PUT", 4);
                        FRICTION = new CmdType("FRICTION", 5);
                        $VALUES = $values();
                    }

                    CmdType(String r1, int r2) {
                    }

                    public static CmdType valueOf(String r1) {
                        return (CmdType) Enum.valueOf(CmdType.class, r1);
                    }

                    public static CmdType[] values() {
                        return (CmdType[]) $VALUES.clone();
                    }
                }

                /* renamed from: -$$Nest$fgetwaitingForPull, reason: not valid java name */
                public static /* bridge */ /* synthetic */ boolean m19059$$Nest$fgetwaitingForPull(AnimSequence r0) {
                    return r0.waitingForPull;
                }

                public AnimSequence(Cube3D r4) {
                    this.commands = new ArrayList();
                    this.currentIndex = 0;
                    this.cancelled = false;
                    this.startMatrix = new float[16];
                    this.targetMatrix = new float[16];
                    this.waitingForPull = false;
                    this.cube = r4;
                }

                private float easeOutCubic(float r5) {
                    return 1.0f - ((float) Math.pow(1.0f - r5, 3.0d));
                }

                private void executeNext() {
                    if (this.cancelled == false) goto L5;
                L29:
                    Cube3D.m19050$$Nest$fputsequence(this.cube, null);
                    if (this.cancelled == true) goto L35;
                    Runnable r5 = this.onComplete;
                    if (r5 == null) goto L36;
                    r5.run();
                    return;
                L36:
                    return;
                L35:
                    return;
                L5:
                    if (this.currentIndex >= this.commands.size()) goto L29;
                    Cmd r0 = this.commands.get(this.currentIndex);
                    boolean r2 = true;
                    this.currentIndex++;
                    switch(AnonymousClass17.$SwitchMap$org$telegram$ui$Stars$StarGiftSheet$CraftTopView$Cube3D$AnimSequence$CmdType[r0.type.ordinal()]) {
                        case 1: goto L24;
                        case 2: goto L22;
                        case 3: goto L16;
                        case 4: goto L14;
                        case 5: goto L12;
                        case 6: goto L10;
                        default: goto L37;
                    };
                L10:
                    this.waitingForPull = true;
                    Cube3D.m19056$$Nest$mdoPull(this.cube, r0.view, r0.face, r0.frames);
                    return;
                L12:
                    System.arraycopy(Cube3D.m19042$$Nest$fgetrotationMatrix(this.cube), 0, this.startMatrix, 0, 16);
                    this.targetMatrix = Cube3D.m19055$$Nest$mcreateFaceMatrix(this.cube, r0.face, r0.rotation);
                    int r02 = r0.frames;
                    this.totalFrames = r02;
                    this.framesRemaining = r02;
                    this.startVx = Cube3D.m19044$$Nest$fgetvx(this.cube);
                    this.startVy = Cube3D.m19045$$Nest$fgetvy(this.cube);
                    return;
                L14:
                    int r03 = r0.frames;
                    this.framesRemaining = r03;
                    this.totalFrames = r03;
                    return;
                L16:
                    Cube3D r1 = this.cube;
                    if (r0.x > 0.0f) goto L20;
                    r2 = false;
                L20:
                    Cube3D.m19046$$Nest$fputfrictionEnabled(r1, r2);
                    executeNext();
                    return;
                L22:
                    this.cube.fling(r0.x, r0.y);
                    this.framesRemaining = 1;
                    this.totalFrames = 1;
                    return;
                L24:
                    Runnable r04 = r0.runnable;
                    if (r04 == null) goto L27;
                    r04.run();
                L27:
                    executeNext();
                    return;
                }

                public void cancel() {
                    this.cancelled = true;
                    this.waitingForPull = false;
                    Cube3D.m19050$$Nest$fputsequence(this.cube, null);
                }

                public AnimSequence delay(int r11) {
                    this.commands.add(new Cmd(CmdType.DELAY, 0.0f, 0.0f, r11, -1, 0.0f, null, null));
                    return this;
                }

                public AnimSequence fling(float r11, float r12) {
                    this.commands.add(new Cmd(CmdType.FLING, r11, r12, 0, -1, 0.0f, null, null));
                    return this;
                }

                public AnimSequence friction(boolean r11) {
                    ArrayList<Cmd> r0 = this.commands;
                    CmdType r2 = CmdType.FRICTION;
                    if (r11 == false) goto L6;
                    float r112 = 1.0f;
                L7:
                    r0.add(new Cmd(r2, r112, 0.0f, 0, -1, 0.0f, null, null));
                    return this;
                L6:
                    r112 = -1.0f;
                    goto L7
                }

                public void onPullComplete() {
                    if (this.waitingForPull == false) goto L6;
                    this.waitingForPull = false;
                    executeNext();
                    return;
                }

                public AnimSequence put(View r2, int r3, int r4) {
                    return put(r2, r3, r4, 0.0f);
                }

                public AnimSequence put(View r11, int r12, int r13, float r14) {
                    this.commands.add(new Cmd(CmdType.PUT, 0.0f, 0.0f, r13, r12, r14, r11, null));
                    return this;
                }

                public AnimSequence run(Runnable r11) {
                    this.commands.add(new Cmd(CmdType.RUN, 0.0f, 0.0f, 0, -1, 0.0f, null, r11));
                    return this;
                }

                public void start(Runnable r6) {
                    this.onComplete = r6;
                    int r62 = 0;
                    this.cancelled = false;
                    this.currentIndex = 0;
                    this.waitingForPull = false;
                    ArrayList<Cmd> r0 = this.commands;
                    int r1 = r0.size();
                L3:
                    if (r62 >= r1) goto L11;
                    Cmd r2 = r0.get(r62);
                    r62 = r62 + 1;
                    Cmd r22 = r2;
                    int r3 = r22.face;
                    if (r3 < 0) goto L3;
                    if (r3 >= 6) goto L3;
                    if (r22.rotation == 0.0f) goto L3;
                    Cube3D.m19040$$Nest$fgetfaceRotations(this.cube)[r22.face] = r22.rotation;
                    goto L3
                L11:
                    Cube3D.m19050$$Nest$fputsequence(this.cube, this);
                    executeNext();
                }

                public AnimSequence steerTo(int r11, int r12, float r13) {
                    this.commands.add(new Cmd(CmdType.STEER, 0.0f, 0.0f, r12, r11, r13, null, null));
                    return this;
                }

                public void tick() {
                    if (this.cancelled == true) goto L31;
                    int r0 = this.currentIndex;
                    if (r0 != 0) goto L8;
                    return;
                L8:
                    if (this.waitingForPull == false) goto L11;
                    Cube3D.m19053$$Nest$mapplyPhysics(this.cube);
                    return;
                L11:
                    int r02 = AnonymousClass17.$SwitchMap$org$telegram$ui$Stars$StarGiftSheet$CraftTopView$Cube3D$AnimSequence$CmdType[this.commands.get(r0 - 1).type.ordinal()];
                    if (r02 != 2) goto L14;
                L27:
                    Cube3D.m19053$$Nest$mapplyPhysics(this.cube);
                    int r03 = this.framesRemaining - 1;
                    this.framesRemaining = r03;
                    if (r03 > 0) goto L33;
                    executeNext();
                    return;
                L33:
                    return;
                L14:
                    if (r02 == 4) goto L27;
                    if (r02 != 5) goto L35;
                    float r04 = easeOutCubic(1.0f - (this.framesRemaining / this.totalFrames));
                    float r1 = 1.0f - r04;
                    if (Math.abs(this.startVx * r1) <= 1.0E-4f) goto L21;
                L22:
                    float[] r10 = new float[16];
                    Cube3D r5 = this.cube;
                    float r2 = this.startVx * r1;
                    Objects.requireNonNull(r5);
                    Cube3D.m19054$$Nest$maxisAngleToMatrix(r5, 1.0f, 0.0f, 0.0f, r2 * 0.96f, r10);
                    Cube3D r22 = this.cube;
                    float[] r52 = this.startMatrix;
                    Cube3D.m19058$$Nest$mmultiplyMatrix(r22, r10, r52, r52);
                    Cube3D r53 = this.cube;
                    float r23 = this.startVy * r1;
                    Objects.requireNonNull(r53);
                    Cube3D.m19054$$Nest$maxisAngleToMatrix(r53, 0.0f, 1.0f, 0.0f, r23 * 0.96f, r10);
                    Cube3D r12 = this.cube;
                    float[] r24 = this.startMatrix;
                    Cube3D.m19058$$Nest$mmultiplyMatrix(r12, r10, r24, r24);
                L23:
                    Cube3D r13 = this.cube;
                    Cube3D.m19057$$Nest$mlerpMatrix(r13, this.startMatrix, this.targetMatrix, r04, Cube3D.m19042$$Nest$fgetrotationMatrix(r13));
                    int r05 = this.framesRemaining - 1;
                    this.framesRemaining = r05;
                    if (r05 > 0) goto L32;
                    System.arraycopy(this.targetMatrix, 0, Cube3D.m19042$$Nest$fgetrotationMatrix(this.cube), 0, 16);
                    Cube3D.m19051$$Nest$fputvx(this.cube, 0.0f);
                    Cube3D.m19052$$Nest$fputvy(this.cube, 0.0f);
                    executeNext();
                    return;
                L32:
                    return;
                L21:
                    if (Math.abs(this.startVy * r1) <= 1.0E-4f) goto L23;
                L35:
                    return;
                }
            }

            /* renamed from: $r8$lambda$-Abt0mtGl6Obvj6_DH5PTgTRukU, reason: not valid java name */
            public static /* synthetic */ int m19039$r8$lambda$Abt0mtGl6Obvj6_DH5PTgTRukU(Cube3D r0, Integer r1, Integer r2) {
                return r0.lambda$update$3(r1, r2);
            }

            public static /* synthetic */ void $r8$lambda$8zqmP5PUt5G4eycb0hrv_vJWDDM(Cube3D r0, ValueAnimator r1) {
                r0.lambda$doPull$0(r1);
            }

            public static /* synthetic */ void $r8$lambda$QOasUq_PABarA1FGMZwEVnF5nkQ(Cube3D r0) {
                r0.lambda$new$2();
            }

            /* renamed from: -$$Nest$fgetfaceRotations, reason: not valid java name */
            public static /* bridge */ /* synthetic */ float[] m19040$$Nest$fgetfaceRotations(Cube3D r0) {
                return r0.faceRotations;
            }

            /* renamed from: -$$Nest$fgetfaces, reason: not valid java name */
            public static /* bridge */ /* synthetic */ View[] m19041$$Nest$fgetfaces(Cube3D r0) {
                return r0.faces;
            }

            /* renamed from: -$$Nest$fgetrotationMatrix, reason: not valid java name */
            public static /* bridge */ /* synthetic */ float[] m19042$$Nest$fgetrotationMatrix(Cube3D r0) {
                return r0.rotationMatrix;
            }

            /* renamed from: -$$Nest$fgetsequence, reason: not valid java name */
            public static /* bridge */ /* synthetic */ AnimSequence m19043$$Nest$fgetsequence(Cube3D r0) {
                return r0.sequence;
            }

            /* renamed from: -$$Nest$fgetvx, reason: not valid java name */
            public static /* bridge */ /* synthetic */ float m19044$$Nest$fgetvx(Cube3D r0) {
                return r0.vx;
            }

            /* renamed from: -$$Nest$fgetvy, reason: not valid java name */
            public static /* bridge */ /* synthetic */ float m19045$$Nest$fgetvy(Cube3D r0) {
                return r0.vy;
            }

            /* renamed from: -$$Nest$fputfrictionEnabled, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m19046$$Nest$fputfrictionEnabled(Cube3D r0, boolean r1) {
                r0.frictionEnabled = r1;
            }

            /* renamed from: -$$Nest$fputpulling, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m19047$$Nest$fputpulling(Cube3D r0, ValueAnimator r1) {
                r0.pulling = r1;
            }

            /* renamed from: -$$Nest$fputpullingIndex, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m19048$$Nest$fputpullingIndex(Cube3D r0, int r1) {
                r0.pullingIndex = r1;
            }

            /* renamed from: -$$Nest$fputpullingT, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m19049$$Nest$fputpullingT(Cube3D r0, float r1) {
                r0.pullingT = r1;
            }

            /* renamed from: -$$Nest$fputsequence, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m19050$$Nest$fputsequence(Cube3D r0, AnimSequence r1) {
                r0.sequence = r1;
            }

            /* renamed from: -$$Nest$fputvx, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m19051$$Nest$fputvx(Cube3D r0, float r1) {
                r0.vx = r1;
            }

            /* renamed from: -$$Nest$fputvy, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m19052$$Nest$fputvy(Cube3D r0, float r1) {
                r0.vy = r1;
            }

            /* renamed from: -$$Nest$mapplyPhysics, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m19053$$Nest$mapplyPhysics(Cube3D r0) {
                r0.applyPhysics();
            }

            /* renamed from: -$$Nest$maxisAngleToMatrix, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m19054$$Nest$maxisAngleToMatrix(Cube3D r0, float r1, float r2, float r3, float r4, float[] r5) {
                r0.axisAngleToMatrix(r1, r2, r3, r4, r5);
            }

            /* renamed from: -$$Nest$mcreateFaceMatrix, reason: not valid java name */
            public static /* bridge */ /* synthetic */ float[] m19055$$Nest$mcreateFaceMatrix(Cube3D r0, int r1, float r2) {
                return r0.createFaceMatrix(r1, r2);
            }

            /* renamed from: -$$Nest$mdoPull, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m19056$$Nest$mdoPull(Cube3D r0, View r1, int r2, int r3) {
                r0.doPull(r1, r2, r3);
            }

            /* renamed from: -$$Nest$mlerpMatrix, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m19057$$Nest$mlerpMatrix(Cube3D r0, float[] r1, float[] r2, float r3, float[] r4) {
                r0.lerpMatrix(r1, r2, r3, r4);
            }

            /* renamed from: -$$Nest$mmultiplyMatrix, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m19058$$Nest$mmultiplyMatrix(Cube3D r0, float[] r1, float[] r2, float[] r3) {
                r0.multiplyMatrix(r1, r2, r3);
            }

            public Cube3D(Context r11, View[] r12) {
                super(r11);
                this.cameraMatrix = new Matrix();
                float[] r112 = new float[16];
                this.rotationMatrix = r112;
                this.vx = 0.0f;
                this.vy = 0.0f;
                this.friction = 0.96f;
                this.frictionEnabled = true;
                this.faceNormals = new float[][]{new float[]{-1.0f, 0.0f, 0.0f, 0.0f}, new float[]{1.0f, 0.0f, 0.0f, 0.0f}, new float[]{0.0f, 1.0f, 0.0f, 0.0f}, new float[]{0.0f, -1.0f, 0.0f, 0.0f}, new float[]{0.0f, 0.0f, -1.0f, 0.0f}, new float[]{0.0f, 0.0f, 1.0f, 0.0f}};
                this.transformedNormal = new float[4];
                this.faceDepths = new float[6];
                int r3 = 0;
                this.drawOrder = new Integer[]{0, 1, 2, 3, 4, 5};
                this.usedFaces = new HashSet();
                this.index2face = new HashMap();
                this.index2Position = new HashMap();
                this.faceRotations = new float[6];
                this.pullingIndex = -1;
                this.updateRunnable = new StarGiftSheet$CraftTopView$Cube3D$$ExternalSyntheticLambda1(this);
                setClipToOutline(false);
                setClipToPadding(false);
                android.opengl.Matrix.setIdentityM(r112, 0);
                this.faces = r12;
            L4:
                if (r3 >= r12.length) goto L6;
                addView(r12[r3], LayoutHelper.createFrame(108, 108, 17));
                r3 = r3 + 1;
                goto L4
            }

            private void applyPhysics() {
                if (Math.abs(this.vx) <= 1.0E-4f) goto L5;
            L6:
                float[] r6 = new float[16];
                axisAngleToMatrix(1.0f, 0.0f, 0.0f, this.vx, r6);
                float[] r7 = this.rotationMatrix;
                multiplyMatrix(r6, r7, r7);
                axisAngleToMatrix(0.0f, 1.0f, 0.0f, this.vy, r6);
                float[] r72 = this.rotationMatrix;
                multiplyMatrix(r6, r72, r72);
                if (this.frictionEnabled == false) goto L11;
                this.vx *= 0.96f;
                this.vy *= 0.96f;
                return;
            L11:
                return;
            L5:
                if (Math.abs(this.vy) > 1.0E-4f) goto L6;
            }

            private void axisAngleToMatrix(float r9, float r10, float r11, float r12, float[] r13) {
                double r0 = r12;
                float r8 = (float) Math.cos(r0);
                float r122 = (float) Math.sin(r0);
                float r1 = 1.0f - r8;
                float r2 = r1 * r9;
                r13[0] = (r2 * r9) + r8;
                float r3 = r2 * r10;
                float r4 = r122 * r11;
                r13[4] = r3 - r4;
                float r22 = r2 * r11;
                float r5 = r122 * r10;
                r13[8] = r22 + r5;
                r13[12] = 0.0f;
                r13[1] = r3 + r4;
                float r32 = r1 * r10;
                r13[5] = (r10 * r32) + r8;
                float r33 = r32 * r11;
                float r123 = r122 * r9;
                r13[9] = r33 - r123;
                r13[13] = 0.0f;
                r13[2] = r22 - r5;
                r13[6] = r33 + r123;
                r13[10] = ((r1 * r11) * r11) + r8;
                r13[14] = 0.0f;
                r13[3] = 0.0f;
                r13[7] = 0.0f;
                r13[11] = 0.0f;
                r13[15] = 1.0f;
            }

            private float[] createFaceMatrix(int r7, float r8) {
                float[] r0 = new float[16];
                android.opengl.Matrix.setIdentityM(r0, 0);
                if (r8 == 0.0f) goto L5;
                android.opengl.Matrix.rotateM(r0, 0, -r8, 0.0f, 0.0f, 1.0f);
            L5:
                if (r7 != 0) goto L7;
                android.opengl.Matrix.rotateM(r0, 0, 90.0f, 0.0f, 1.0f, 0.0f);
                return r0;
            L7:
                if (r7 != 1) goto L9;
                android.opengl.Matrix.rotateM(r0, 0, -90.0f, 0.0f, 1.0f, 0.0f);
                return r0;
            L9:
                if (r7 != 2) goto L11;
                android.opengl.Matrix.rotateM(r0, 0, 90.0f, 1.0f, 0.0f, 0.0f);
                return r0;
            L11:
                if (r7 != 3) goto L13;
                android.opengl.Matrix.rotateM(r0, 0, -90.0f, 1.0f, 0.0f, 0.0f);
                return r0;
            L13:
                if (r7 == 4) goto L15;
                return r0;
            L15:
                android.opengl.Matrix.rotateM(r0, 0, 180.0f, 0.0f, 1.0f, 0.0f);
                return r0;
            }

            private void cross(float[] r6, float[] r7, float[] r8) {
                float r0 = r6[1];
                float r2 = r7[2];
                float r3 = r6[2];
                r8[0] = (r0 * r2) - (r7[1] * r3);
                float r02 = r7[0];
                float r4 = r6[0];
                r8[1] = (r3 * r02) - (r2 * r4);
                r8[2] = (r4 * r7[1]) - (r6[1] * r02);
            }

            private void doPull(View r5, int r6, int r7) {
                ValueAnimator r0 = this.pulling;
                if (r0 == null) goto L5;
                r0.cancel();
                this.pulling = null;
            L5:
                RectF r02 = new RectF();
                r02.left = r5.getX() - getX();
                r02.top = r5.getY() - getY();
                r02.right = r02.left + r5.getWidth();
                r02.bottom = r02.top + r5.getHeight();
                AndroidUtilities.removeFromParent(r5);
                int r1 = getChildCount();
                addView(r5, LayoutHelper.createFrame(64, 64, 17));
                this.usedFaces.add(Integer.valueOf(r6));
                this.index2face.put(Integer.valueOf(r1), Integer.valueOf(r6));
                this.index2Position.put(Integer.valueOf(r1), r02);
                this.pullingIndex = r1;
                this.pullingT = 0.0f;
                long r52 = r7 * 16;
                ValueAnimator r72 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
                this.pulling = r72;
                r72.addUpdateListener(new StarGiftSheet$CraftTopView$Cube3D$$ExternalSyntheticLambda0(this));
                this.pulling.addListener(new AnonymousClass1(this));
                this.pulling.setDuration(r52);
                this.pulling.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
                this.pulling.start();
            }

            private void getFaceBasis(int r7, float[] r8, float[] r9, float[] r10) {
                System.arraycopy(this.faceNormals[r7], 0, r8, 0, 4);
                if (r7 == 0) goto L16;
                if (r7 == 1) goto L15;
                if (r7 == 2) goto L14;
                if (r7 == 3) goto L13;
                if (r7 != 4) goto L9;
                r9[0] = -1.0f;
                r9[1] = 0.0f;
                r9[2] = 0.0f;
                r10[0] = 0.0f;
                r10[1] = -1.0f;
                r10[2] = 0.0f;
            L17:
                r9[3] = 0.0f;
                r10[3] = 0.0f;
                return;
            L9:
                if (r7 != 5) goto L17;
                r9[0] = 1.0f;
                r9[1] = 0.0f;
                r9[2] = 0.0f;
                r10[0] = 0.0f;
                r10[1] = -1.0f;
                r10[2] = 0.0f;
                goto L17
            L13:
                r9[0] = 1.0f;
                r9[1] = 0.0f;
                r9[2] = 0.0f;
                r10[0] = 0.0f;
                r10[1] = 0.0f;
                r10[2] = -1.0f;
                goto L17
            L14:
                r9[0] = 1.0f;
                r9[1] = 0.0f;
                r9[2] = 0.0f;
                r10[0] = 0.0f;
                r10[1] = 0.0f;
                r10[2] = 1.0f;
                goto L17
            L15:
                r9[0] = 0.0f;
                r9[1] = 0.0f;
                r9[2] = -1.0f;
                r10[0] = 0.0f;
                r10[1] = -1.0f;
                r10[2] = 0.0f;
                goto L17
            L16:
                r9[0] = 0.0f;
                r9[1] = 0.0f;
                r9[2] = 1.0f;
                r10[0] = 0.0f;
                r10[1] = -1.0f;
                r10[2] = 0.0f;
                goto L17
            }

            private /* synthetic */ void lambda$doPull$0(ValueAnimator r2) {
                float r22 = ((Float) r2.getAnimatedValue()).floatValue();
                this.pullingT = r22;
                if (r22 < 0.8f) goto L9;
                AnimSequence r23 = this.sequence;
                if (r23 == null) goto L9;
                if (AnimSequence.m19059$$Nest$fgetwaitingForPull(r23) == false) goto L9;
                this.sequence.onPullComplete();
            L9:
                invalidate();
            }

            private /* synthetic */ void lambda$new$2() {
                update();
            }

            private /* synthetic */ int lambda$update$3(Integer r2, Integer r3) {
                return Float.compare(this.faceDepths[r2.intValue()], this.faceDepths[r3.intValue()]);
            }

            private void lerpMatrix(float[] r4, float[] r5, float r6, float[] r7) {
                int r0 = 0;
            L4:
                if (r0 >= 16) goto L6;
                float r1 = r4[r0];
                r7[r0] = r1 + ((r5[r0] - r1) * r6);
                r0 = r0 + 1;
                goto L4
            L6:
                orthonormalize(r7);
            }

            private void multiplyMatrix(float[] r7, float[] r8, float[] r9) {
                float[] r0 = new float[16];
                android.opengl.Matrix.multiplyMM(r0, 0, r7, 0, r8, 0);
                System.arraycopy(r0, 0, r9, 0, 16);
            }

            private void normalize(float[] r6) {
                float r0 = r6[0];
                float r2 = r6[1];
                float r3 = r6[2];
                float r02 = (float) Math.sqrt(((r0 * r0) + (r2 * r2)) + (r3 * r3));
                if (r02 <= 0.0f) goto L6;
                r6[0] = r6[0] / r02;
                r6[1] = r6[1] / r02;
                r6[2] = r6[2] / r02;
                return;
            }

            private void orthonormalize(float[] r13) {
                float[] r7 = {r13[0], r13[1], r13[2]};
                float[] r11 = {r13[4], r13[5], r13[6]};
                float[] r3 = new float[3];
                normalize(r7);
                cross(r7, r11, r3);
                normalize(r3);
                cross(r3, r7, r11);
                r13[0] = r7[0];
                r13[1] = r7[1];
                r13[2] = r7[2];
                r13[4] = r11[0];
                r13[5] = r11[1];
                r13[6] = r11[2];
                r13[8] = r3[0];
                r13[9] = r3[1];
                r13[10] = r3[2];
            }

            private void update() {
                AnimSequence r0 = this.sequence;
                boolean r1 = true;
                int r2 = 0;
                if (r0 == null) goto L7;
                r0.tick();
            L5:
                boolean r02 = true;
            L14:
                if (this.pulling != null) goto L17;
                r1 = r02;
            L17:
                if (r1 == false) goto L23;
            L19:
                if (r2 >= 6) goto L21;
                android.opengl.Matrix.multiplyMV(this.transformedNormal, 0, this.rotationMatrix, 0, this.faceNormals[r2], 0);
                this.faceDepths[r2] = this.transformedNormal[2];
                r2 = r2 + 1;
                goto L19
            L21:
                Arrays.sort(this.drawOrder, new StarGiftSheet$CraftTopView$Cube3D$$ExternalSyntheticLambda2(this));
                invalidate();
            L23:
                if (isAttachedToWindow() == false) goto L27;
                AndroidUtilities.runOnUIThread(this.updateRunnable, 16);
                return;
            L27:
                return;
            L7:
                if (Math.abs(this.vx) <= 1.0E-4f) goto L9;
            L12:
                applyPhysics();
                goto L5
            L9:
                if (Math.abs(this.vy) > 1.0E-4f) goto L12;
                r02 = false;
                goto L14
            }

            @Override // android.view.ViewGroup
            public boolean drawChild(Canvas r37, View r38, long r39) {
                int r2 = indexOfChild(r38);
                if (r2 >= 6) goto L5;
                RectF r9 = null;
                boolean r8 = false;
                boolean r10 = false;
            L24:
                android.opengl.Matrix.multiplyMV(this.transformedNormal, 0, this.rotationMatrix, 0, this.faceNormals[r2], 0);
                float r11 = this.transformedNormal[2];
                if (r11 >= 0.001f) goto L27;
                return false;
            L27:
                r38.setAlpha(Math.min(1.0f, r11 / 0.3f));
                float r112 = r38.getWidth() / 2.0f;
                if (r10 == true) goto L30;
            L36:
                float r102 = 0.0f;
            L37:
                float r4 = AndroidUtilities.dp(108.0f) / 2.0f;
                float r15 = getWidth() / 2.0f;
                float r6 = getHeight() / 2.0f;
                float[] r7 = new float[4];
                float[] r14 = new float[4];
                float[] r3 = new float[4];
                getFaceBasis(r2, r7, r14, r3);
                int r32 = 1;
                float r22 = this.faceRotations[r2];
                if (r22 == 0.0f) goto L40;
                double r12 = (float) Math.toRadians(r22);
                int r34 = 2;
                float r5 = (float) Math.cos(r12);
                float r62 = (float) Math.sin(r12);
                float r122 = r14[0];
                float r18 = r3[0];
                float r1 = r14[1];
                float r20 = r3[1];
                float r35 = r6;
                float r23 = r14[2];
                float r222 = r3[2];
                r14[0] = (r122 * r5) + (r18 * r62);
                r14[1] = (r1 * r5) + (r20 * r62);
                r14[2] = (r23 * r5) + (r222 * r62);
                r3[0] = ((-r122) * r62) + (r18 * r5);
                r3[1] = ((-r1) * r62) + (r20 * r5);
                r3[2] = ((-r23) * r62) + (r222 * r5);
                int r13 = 4;
            L41:
                float[] r24 = new float[r13];
                float[] r52 = new float[r13];
                float[] r63 = new float[r13];
                android.opengl.Matrix.multiplyMV(r24, 0, this.rotationMatrix, 0, r7, 0);
                android.opengl.Matrix.multiplyMV(r52, 0, this.rotationMatrix, 0, r14, 0);
                android.opengl.Matrix.multiplyMV(r63, 0, this.rotationMatrix, 0, r3, 0);
                float r25 = 64.0f * r4;
                int r53 = r34;
                int[] r33 = new int[r53];
                r33[1] = 3;
                int r72 = 4;
                r33[0] = 4;
                float[][] r36 = (float[][]) Array.newInstance(Float.TYPE, r33);
                int r123 = 0;
            L42:
                if (r123 >= r72) goto L53;
                float r73 = -1.0f;
                int r132 = r32;
                if (r123 == r132) goto L48;
                if (r123 == r53) goto L48;
                float r142 = -1.0f;
            L49:
                if (r123 == 0) goto L51;
                if (r123 == r132) goto L51;
            L52:
                float[] r133 = r36[r123];
                r133[0] = ((r24[0] + (r52[0] * r142)) + (r63[0] * r73)) * r4;
                r133[1] = ((r24[1] + (r52[1] * r142)) + (r63[1] * r73)) * r4;
                r53 = 2;
                r133[2] = ((r24[2] + (r52[2] * r142)) + (r63[2] * r73)) * r4;
                r123 = r123 + 1;
                r72 = 4;
                r32 = 1;
            L51:
                r73 = 1.0f;
            L48:
                r142 = 1.0f;
                goto L49
            L53:
                float[] r16 = new float[8];
                int r42 = 0;
            L55:
                if (r42 >= 4) goto L57;
                float[] r74 = r36[r42];
                float r124 = r25 / (r25 - r74[r53]);
                int r134 = r42 * 2;
                r16[r134] = (r74[0] * r124) + r15;
                r16[r134 + 1] = r35 + (r74[1] * r124);
                r42 = r42 + 1;
                r53 = 2;
                goto L55
            L57:
                float r26 = (r15 - r112) - r102;
                float r310 = (r35 - r112) - r102;
                float r152 = (r15 + r112) + r102;
                float r43 = (r35 + r112) + r102;
                float[] r103 = {r26, r310, r152, r310, r152, r43, r26, r43};
                if (r8 == false) goto L61;
                if (r9 == null) goto L61;
                float r44 = r9.left;
                float r75 = r9.top;
                float r82 = r9.right;
                float r92 = r9.bottom;
                AndroidUtilities.lerp(new float[]{r44, r75, r82, r75, r82, r92, r44, r92}, r16, this.pullingT, r16);
            L61:
                this.cameraMatrix.reset();
                this.cameraMatrix.setPolyToPoly(r103, 0, r16, 0, 4);
                r37.save();
                r37.concat(this.cameraMatrix);
                boolean r0 = super.drawChild(r37, r38, r39);
                r37.restore();
                return r0;
            L40:
                r35 = r6;
                r34 = 2;
                r13 = 4;
                goto L41
            L30:
                if ((r38 instanceof SelectGiftView) == false) goto L36;
                float r104 = AndroidUtilities.dp(-6.0f);
                if (r8 == false) goto L34;
                float r153 = this.pullingT;
            L35:
                r102 = r104 + (r153 * AndroidUtilities.dp(2.0f));
                goto L37
            L34:
                r153 = 1.0f;
                goto L35
            L5:
                if (this.pullingIndex != r2) goto L7;
                r8 = true;
            L8:
                r9 = this.index2Position.get(Integer.valueOf(r2));
                Integer r105 = this.index2face.get(Integer.valueOf(r2));
                if (r105 == null) goto L12;
                r2 = r105.intValue();
            L12:
                if ((r38 instanceof SelectGiftView) == false) goto L22;
                SelectGiftView r106 = (SelectGiftView) r38;
                if (r8 == false) goto L16;
                float r113 = this.pullingT;
            L17:
                r106.setHideButtons(r113);
                if (r8 == true) goto L20;
            L21:
                this.faces[r2].setVisibility(8);
                goto L22
            L20:
                if (this.pullingT < 1.0f) goto L22;
            L16:
                r113 = 1.0f;
            L22:
                r10 = true;
                goto L24
            L7:
                r8 = false;
                goto L8
            }

            public void fling(float r3, float r4) {
                this.vx += r4 * 0.01f;
                this.vy += r3 * 0.01f;
            }

            @Override // android.view.ViewGroup
            public int getChildDrawingOrder(int r1, int r2) {
                if (r2 >= 6) goto L8;
                Integer[] r0 = this.drawOrder;
                if (r2 >= r0.length) goto L8;
                return r0[r2].intValue();
            L8:
                return r2;
            }

            @Override // android.view.ViewGroup, android.view.View
            public void onAttachedToWindow() {
                super.onAttachedToWindow();
                AndroidUtilities.runOnUIThread(this.updateRunnable, 16);
            }

            @Override // android.view.ViewGroup, android.view.View
            public void onDetachedFromWindow() {
                super.onDetachedFromWindow();
                AndroidUtilities.cancelRunOnUIThread(this.updateRunnable);
            }

            public int putView(int r4, View r5) {
                if (r4 != (-1)) goto L5;
                r4 = 5;
            L5:
                AndroidUtilities.removeFromParent(r5);
                int r0 = getChildCount();
                addView(r5, LayoutHelper.createFrame(64, 64, 17));
                this.usedFaces.add(Integer.valueOf(r4));
                this.index2face.put(Integer.valueOf(r0), Integer.valueOf(r4));
                return r4;
            }

            public void reset() {
                AnimSequence r0 = this.sequence;
                if (r0 == null) goto L5;
                r0.cancel();
                this.sequence = null;
            L5:
                ValueAnimator r02 = this.pulling;
                if (r02 == null) goto L8;
                r02.cancel();
                this.pulling = null;
            L8:
                this.pullingIndex = -1;
                this.pullingT = 0.0f;
                this.usedFaces.clear();
                this.index2face.clear();
                this.index2Position.clear();
                int r2 = 0;
            L10:
                if (r2 >= 6) goto L12;
                this.faceRotations[r2] = 0.0f;
                r2 = r2 + 1;
                goto L10
            L12:
                removeAllViews();
                int r22 = 0;
            L13:
                View[] r3 = this.faces;
                if (r22 >= r3.length) goto L16;
                r3[r22].setAlpha(1.0f);
                this.faces[r22].setVisibility(0);
                addView(this.faces[r22], LayoutHelper.createFrame(108, 108, 17));
                r22 = r22 + 1;
                goto L13
            L16:
                android.opengl.Matrix.setIdentityM(this.rotationMatrix, 0);
                this.vy = 0.0f;
                this.vx = 0.0f;
                this.frictionEnabled = true;
            }
        }

        public static class Face extends FrameLayout {
            private AnimatedTextView counter;
            private BackupImageView image;
            private FrameLayout layout;
            private ProgressView progress;

            public Face(Context r13, boolean r14) {
                super(r13);
                FrameLayout r0 = new FrameLayout(r13);
                this.layout = r0;
                r0.setBackground(new RoundRectStrokeDrawable(AndroidUtilities.dp(24.0f), Theme.multAlpha(-1, 0.08f)));
                addView(this.layout, LayoutHelper.createFrame(-1, -1.0f, 119, 2.0f, 2.0f, 2.0f, 2.0f));
                BackupImageView r02 = new BackupImageView(r13);
                this.image = r02;
                r02.setImageResource(R.drawable.large_forge);
                BackupImageView r03 = this.image;
                if (r14 == false) goto L5;
                float r1 = 1.0f;
            L6:
                r03.setAlpha(r1);
                FrameLayout r04 = this.layout;
                BackupImageView r12 = this.image;
                int r2 = 64;
                if (r14 == false) goto L9;
                int r5 = 42;
            L10:
                if (r14 == false) goto L12;
                r2 = 42;
            L12:
                r04.addView(r12, LayoutHelper.createFrame(r5, r2, 17));
                if (r14 == false) goto L16;
                this.image.setTranslationX(AndroidUtilities.dp(-4.0f));
                ProgressView r142 = new ProgressView(r13);
                this.progress = r142;
                r142.setRadius(AndroidUtilities.dp(37.0f));
                this.progress.setStrokeWidth(AndroidUtilities.dpf2(4.66f));
                this.layout.addView(this.progress, LayoutHelper.createFrame(90, 90, 17));
                AnimatedTextView r143 = new AnimatedTextView(r13);
                this.counter = r143;
                r143.getDrawable().setHacks(false, true, true);
                this.counter.setTypeface(AndroidUtilities.bold());
                this.counter.setTextColor(-1);
                this.counter.setTextSize(AndroidUtilities.dp(14.0f));
                this.counter.setGravity(17);
                this.counter.setText("0%");
                this.layout.addView(this.counter, LayoutHelper.createFrame(-1, 16.0f, 55, 12.0f, 80.0f, 12.0f, 0.0f));
                return;
            L16:
                return;
            L9:
                r5 = 64;
                goto L10
            L5:
                r1 = 0.45f;
                goto L6
            }

            public void setChance(float r4, boolean r5) {
                AnimatedTextView r0 = this.counter;
                if (r0 != null) goto L5;
                return;
            L5:
                r0.setText(Math.round(r4) + "%", r5);
                this.progress.setProgress(r4 / 100.0f, r5);
            }
        }

        public static class ProgressView extends View {
            private final AnimatedFloat animatedProgress;
            private final AnimatedFloat animatedProgressAlpha;
            private final Paint paint;
            private float progress;
            private float radius;

            public ProgressView(Context r11) {
                super(r11);
                Paint r112 = new Paint(1);
                this.paint = r112;
                StarGiftSheet$CraftTopView$ProgressView$$ExternalSyntheticLambda0 r2 = new StarGiftSheet$CraftTopView$ProgressView$$ExternalSyntheticLambda0(this);
                CubicBezierInterpolator r7 = CubicBezierInterpolator.EASE_OUT_QUINT;
                this.animatedProgress = new AnimatedFloat(r2, 0, 420, r7);
                this.animatedProgressAlpha = new AnimatedFloat(new StarGiftSheet$CraftTopView$ProgressView$$ExternalSyntheticLambda0(this), 0, 420, r7);
                r112.setStyle(Paint.Style.STROKE);
                r112.setStrokeCap(Paint.Cap.ROUND);
                r112.setStrokeJoin(Paint.Join.ROUND);
            }

            @Override // android.view.View
            public void dispatchDraw(Canvas r13) {
                float r0 = this.animatedProgress.set(this.progress);
                AnimatedFloat r1 = this.animatedProgressAlpha;
                if (this.progress <= 0.0f) goto L5;
                boolean r2 = true;
            L6:
                float r12 = r1.set(r2);
                float r22 = getWidth() / 2.0f;
                float r5 = getHeight() / 2.0f;
                float r4 = this.radius;
                RectF r7 = AndroidUtilities.rectTmp;
                r7.set(r22 - r4, r5 - r4, r22 + r4, r5 + r4);
                this.paint.setColor(Theme.multAlpha(-1, 0.25f));
                r13.drawArc(r7, 135.0f, 270.0f, false, this.paint);
                if (r12 <= 0.0f) goto L10;
                this.paint.setColor(Theme.multAlpha(-1, r12));
                r13.drawArc(r7, 135.0f, r0 * 270.0f, false, this.paint);
                return;
            L10:
                return;
            L5:
                r2 = false;
                goto L6
            }

            public void setProgress(float r1, boolean r2) {
                this.progress = r1;
                if (r2 == true) goto L5;
                this.animatedProgress.force(r1);
            L5:
                invalidate();
            }

            public void setRadius(float r1) {
                this.radius = r1;
            }

            public void setStrokeWidth(float r1) {
                this.paint.setStrokeWidth(r1);
            }
        }

        public static class RaysView extends View {
            private final Paint fillPaint;
            private RadialGradient[] gradient;
            private Matrix gradientMatrix;
            private int leftColor;
            private RadialGradient maskGradient;
            private final Paint maskPaint;
            private final Path path;
            private int rightColor;
            private final Paint strokePaint;
            private AnimatedFloat swapGradient;

            public RaysView(Context r12) {
                super(r12);
                this.fillPaint = new Paint(1);
                this.strokePaint = new Paint(1);
                Paint r122 = new Paint(1);
                this.maskPaint = r122;
                this.gradient = new RadialGradient[2];
                this.gradientMatrix = new Matrix();
                this.swapGradient = new AnimatedFloat(1.0f, this, 0, 420, CubicBezierInterpolator.EASE_OUT_QUINT);
                this.maskGradient = new RadialGradient(0.0f, 0.0f, 100.0f, new int[]{0, -1, -1, 0}, new float[]{0.15f, 0.35f, 0.65f, 0.88f}, Shader.TileMode.CLAMP);
                this.path = new Path();
                r122.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            }

            @Override // android.view.View
            public void onDraw(Canvas r21) {
                float r8 = 1.0f;
                float r9 = this.swapGradient.set(1.0f);
                float r1 = ((System.currentTimeMillis() % 15000) / 15000.0f) * 360.0f;
                if (getAlpha() <= 0.0f) goto L5;
                invalidate();
            L5:
                this.strokePaint.setStyle(Paint.Style.STROKE);
                this.strokePaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
                this.path.rewind();
                float r11 = getWidth() / 2.0f;
                float r12 = getHeight() / 2.0f;
                float r13 = Math.min(getWidth(), getHeight()) / 2.0f;
                int r14 = 0;
                int r2 = 0;
            L7:
                if (r2 >= 6) goto L9;
                float r4 = ((r2 * 60.0f) + 12.5f) + r1;
                this.path.moveTo(r11, r12);
                double r3 = ((r4 - 12.5f) / 180.0f) * 3.141592653589793d;
                this.path.lineTo((((float) Math.cos(r3)) * r13) + r11, (((float) Math.sin(r3)) * r13) + r12);
                double r42 = ((r4 + 12.5f) / 180.0f) * 3.141592653589793d;
                this.path.lineTo((((float) Math.cos(r42)) * r13) + r11, (((float) Math.sin(r42)) * r13) + r12);
                this.path.lineTo(r11, r12);
                r2 = r2 + 1;
                r8 = r8;
                goto L7
            L9:
                float r19 = r8;
                r21.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
            L10:
                RadialGradient[] r22 = this.gradient;
                if (r14 >= r22.length) goto L20;
                if (r22[r14] == null) goto L19;
                float r23 = (float) Math.pow(r19 - Math.abs(r14 - r9), 0.5d);
                if (r23 <= 0.0f) goto L19;
                this.gradientMatrix.reset();
                float r43 = r13 / 100.0f;
                this.gradientMatrix.postScale(r43, r43);
                this.gradientMatrix.postTranslate(r11, r12);
                this.gradient[r14].setLocalMatrix(this.gradientMatrix);
                this.fillPaint.setShader(this.gradient[r14]);
                float r24 = r23 * 255.0f;
                this.fillPaint.setAlpha((int) (0.3f * r24));
                this.strokePaint.setShader(this.gradient[r14]);
                this.strokePaint.setAlpha((int) r24);
                r21.drawPath(this.path, this.fillPaint);
                r21.drawPath(this.path, this.strokePaint);
            L19:
                r14 = r14 + 1;
                goto L10
            L20:
                this.gradientMatrix.reset();
                float r132 = r13 / 100.0f;
                this.gradientMatrix.postScale(r132, r132);
                this.gradientMatrix.postTranslate(r11, r12);
                this.maskGradient.setLocalMatrix(this.gradientMatrix);
                this.maskPaint.setShader(this.maskGradient);
                r21.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.maskPaint);
                r21.restore();
            }

            public void setColor(int r12, int r13) {
                if (this.leftColor == r12) goto L5;
            L7:
                RadialGradient[] r0 = this.gradient;
                r0[0] = r0[1];
                this.leftColor = r12;
                this.rightColor = r13;
                r0[1] = new RadialGradient(0.0f, 0.0f, 100.0f, new int[]{r12, r13}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
                this.swapGradient.force(0.0f);
                invalidate();
                return;
            L5:
                if (this.rightColor != r13) goto L7;
            }
        }

        public final class SelectGiftView extends FrameLayout {
            private final TextView chance;
            private final ImageView closeIcon;
            private final FrameLayout closeLayout;
            public TL_stars.StarGift gift;
            private final GiftSheet.CardBackground giftBackground;
            private final BackupImageView giftImage;
            private final FrameLayout giftLayout;
            public boolean isReplaceIcon;
            private final FrameLayout layout;
            private final ImageView plus;
            public TL_stars.SavedStarGift savedGift;
            final /* synthetic */ CraftTopView this$0;

            public static /* synthetic */ void $r8$lambda$6QWhzWLrwAY9mjq0TVf41RBSC1U(SelectGiftView r0, boolean r1) {
                r0.lambda$setGiftVisible$2(r1);
            }

            public static /* synthetic */ void $r8$lambda$PDrIg6TfENgT66YNZ35xlTCT7eE(SelectGiftView r0, boolean r1) {
                r0.lambda$setGiftVisible$0(r1);
            }

            public static /* synthetic */ void $r8$lambda$_WSclZOOp8TSxHwxCUWWZ3YMyM4(SelectGiftView r0, boolean r1) {
                r0.lambda$setGiftVisible$1(r1);
            }

            public SelectGiftView(CraftTopView r14, Context r15) {
                this.this$0 = r14;
                super(r15);
                FrameLayout r142 = new FrameLayout(r15);
                this.layout = r142;
                addView(r142, LayoutHelper.createFrame(-1, -1.0f, 119, 6.0f, 6.0f, 6.0f, 6.0f));
                r142.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(18.0f), Theme.multAlpha(-4530177, 0.12f)));
                r142.setForeground(new RoundRectStrokeDrawable(AndroidUtilities.dp(18.0f), 0));
                ImageView r1 = new ImageView(r15);
                this.plus = r1;
                r1.setImageResource(R.drawable.filled_add_album);
                r1.setScaleX(1.25f);
                r1.setScaleY(1.25f);
                r142.addView(r1, LayoutHelper.createFrame(24, 24, 17));
                FrameLayout r12 = new FrameLayout(r15);
                this.giftLayout = r12;
                GiftSheet.CardBackground r2 = new GiftSheet.CardBackground(r12, null, false);
                this.giftBackground = r2;
                r12.setBackground(r2);
                r2.setRoundRadius(AndroidUtilities.dp(18.0f));
                r2.setPadding(false);
                r142.addView(r12, LayoutHelper.createFrame(-1, -1, 119));
                r12.setAlpha(0.0f);
                r12.setScaleX(0.6f);
                r12.setScaleY(0.6f);
                BackupImageView r0 = new BackupImageView(r15);
                this.giftImage = r0;
                r12.addView(r0, LayoutHelper.createFrame(52, 52, 17));
                TextView r02 = new TextView(r15);
                this.chance = r02;
                r02.setPadding(AndroidUtilities.dp(5.0f), 0, AndroidUtilities.dp(5.0f), 0);
                r02.setGravity(17);
                r02.setTypeface(AndroidUtilities.bold());
                r02.setTextSize(1, 10.0f);
                r02.setTextColor(-1);
                r02.setAlpha(0.0f);
                addView(r02, LayoutHelper.createFrame(-2, 15.33f, 51, 2.0f, 0.0f, 2.0f, 0.0f));
                FrameLayout r03 = new FrameLayout(r15);
                this.closeLayout = r03;
                r03.setAlpha(0.0f);
                addView(r03, LayoutHelper.createFrame(20, 20.0f, 53, 2.0f, 0.0f, 2.0f, 0.0f));
                ImageView r143 = new ImageView(r15);
                this.closeIcon = r143;
                r143.setImageResource(R.drawable.msg_close);
                r143.setScaleType(ImageView.ScaleType.CENTER);
                r03.addView(r143, LayoutHelper.createFrame(12, 12, 17));
                setGiftVisible(false, false);
            }

            private /* synthetic */ void lambda$setGiftVisible$0(boolean r1) {
                if (r1 == true) goto L5;
                this.closeLayout.setVisibility(8);
                return;
            }

            private /* synthetic */ void lambda$setGiftVisible$1(boolean r1) {
                if (r1 == true) goto L5;
                this.closeLayout.setVisibility(8);
                return;
            }

            private /* synthetic */ void lambda$setGiftVisible$2(boolean r1) {
                if (r1 == true) goto L5;
                this.closeLayout.setVisibility(8);
                return;
            }

            public TL_stars.StarGift getGift() {
                TL_stars.StarGift r0 = this.gift;
                if (r0 == null) goto L5;
                return r0;
            L5:
                TL_stars.SavedStarGift r1 = this.savedGift;
                if (r1 != null) goto L8;
                return null;
            L8:
                return r1.gift;
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(76.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(76.0f), TLObject.FLAG_30));
            }

            public void setGift(TL_stars.StarGift r6, boolean r7) {
                this.gift = r6;
                if (r6 == null) goto L5;
                TL_stars.starGiftAttributeBackdrop r0 = (TL_stars.starGiftAttributeBackdrop) StarsController.findAttribute(r6.attributes, TL_stars.starGiftAttributeBackdrop.class);
                TL_stars.starGiftAttributePattern r1 = (TL_stars.starGiftAttributePattern) StarsController.findAttribute(r6.attributes, TL_stars.starGiftAttributePattern.class);
                TL_stars.starGiftAttributeModel r2 = (TL_stars.starGiftAttributeModel) StarsController.findAttribute(r6.attributes, TL_stars.starGiftAttributeModel.class);
                this.giftBackground.setBackdrop(r0);
                this.giftBackground.setPattern(r1);
                StarsIntroActivity.setGiftImage(this.giftImage.getImageReceiver(), r2.document, 52);
                int r02 = Theme.adaptHSV(Theme.multAlpha(r0.edge_color | (-16777216), 0.88f), -0.05f, -0.15f);
                this.chance.setText(AffiliateProgramFragment.percents(r6.craft_chance_permille));
                this.chance.setBackground(new RoundRectStrokeDrawable(AndroidUtilities.dp(10.0f), r02));
                this.closeLayout.setBackground(new RoundRectStrokeDrawable(AndroidUtilities.dp(10.0f), r02));
            L5:
                if (r6 == null) goto L7;
                boolean r62 = true;
            L8:
                setGiftVisible(r62, r7);
                return;
            L7:
                r62 = false;
                goto L8
            }

            public void setGiftVisible(boolean r8, boolean r9) {
                this.giftLayout.animate().cancel();
                this.chance.animate().cancel();
                this.closeLayout.animate().cancel();
                FrameLayout r0 = this.giftLayout;
                float r1 = 0.6f;
                float r2 = 0.0f;
                int r3 = 0;
                if (r9 == false) goto L5;
                r0.setVisibility(0);
                ViewPropertyAnimator r92 = this.giftLayout.animate();
                if (r8 == false) goto L39;
                float r02 = 1.0f;
            L40:
                ViewPropertyAnimator r93 = r92.scaleX(r02);
                if (r8 == false) goto L43;
                r1 = 1.0f;
            L43:
                ViewPropertyAnimator r94 = r93.scaleY(r1);
                if (r8 == false) goto L46;
                float r03 = 1.0f;
            L47:
                ViewPropertyAnimator r95 = r94.alpha(r03);
                CubicBezierInterpolator r04 = CubicBezierInterpolator.EASE_OUT_QUINT;
                r95.setInterpolator(r04).setDuration(420).withEndAction(new StarGiftSheet$CraftTopView$SelectGiftView$$ExternalSyntheticLambda0(this, r8)).start();
                this.chance.setVisibility(0);
                ViewPropertyAnimator r96 = this.chance.animate();
                if (r8 == false) goto L50;
                float r12 = 1.0f;
            L51:
                r96.alpha(r12).setInterpolator(r04).setDuration(420).withEndAction(new StarGiftSheet$CraftTopView$SelectGiftView$$ExternalSyntheticLambda1(this, r8)).start();
                this.closeLayout.setVisibility(0);
                ViewPropertyAnimator r97 = this.closeLayout.animate();
                if (r8 == false) goto L54;
                r2 = 1.0f;
            L54:
                r97.alpha(r2).setInterpolator(r04).setDuration(420).withEndAction(new StarGiftSheet$CraftTopView$SelectGiftView$$ExternalSyntheticLambda2(this, r8)).start();
                return;
            L50:
                r12 = 0.0f;
                goto L51
            L46:
                r03 = 0.0f;
                goto L47
            L39:
                r02 = 0.6f;
                goto L40
            L5:
                if (r8 == false) goto L7;
                int r5 = 0;
            L8:
                r0.setVisibility(r5);
                FrameLayout r05 = this.giftLayout;
                if (r8 == false) goto L11;
                float r52 = 1.0f;
            L12:
                r05.setScaleX(r52);
                FrameLayout r06 = this.giftLayout;
                if (r8 == false) goto L15;
                r1 = 1.0f;
            L15:
                r06.setScaleY(r1);
                FrameLayout r07 = this.giftLayout;
                if (r8 == false) goto L18;
                float r13 = 1.0f;
            L19:
                r07.setAlpha(r13);
                TextView r08 = this.chance;
                if (r8 == false) goto L22;
                int r14 = 0;
            L23:
                r08.setVisibility(r14);
                TextView r09 = this.chance;
                if (r8 == false) goto L26;
                float r15 = 1.0f;
            L27:
                r09.setAlpha(r15);
                FrameLayout r010 = this.closeLayout;
                if (r8 == true) goto L31;
                r3 = 8;
            L31:
                r010.setVisibility(r3);
                FrameLayout r7 = this.closeLayout;
                if (r8 == false) goto L34;
                r2 = 1.0f;
            L34:
                r7.setAlpha(r2);
                return;
            L26:
                r15 = 0.0f;
                goto L27
            L22:
                r14 = 8;
                goto L23
            L18:
                r13 = 0.0f;
                goto L19
            L11:
                r52 = 0.6f;
                goto L12
            L7:
                r5 = 8;
                goto L8
            }

            public void setHideButtons(float r3) {
                float r1 = 1.0f - r3;
                this.chance.setAlpha(r1);
                this.closeLayout.setAlpha(r1);
            }

            public void setReplaceIcon(boolean r3) {
                this.closeIcon.setScaleX(0.8f);
                this.closeIcon.setScaleY(0.8f);
                ImageView r0 = this.closeIcon;
                this.isReplaceIcon = r3;
                if (r3 == false) goto L5;
                int r2 = R.drawable.mini_replace2;
            L6:
                r0.setImageResource(r2);
                return;
            L5:
                r2 = R.drawable.msg_close;
                goto L6
            }
        }

        public static class SwitchGradientDrawable extends Drawable {
            private int color1;
            private int color2;
            private Shader[] gradient;
            private Matrix gradientMatrix;
            private Drawable icon;
            private final Paint paint;
            private float r;
            private final RectF rect;
            private AnimatedFloat swapGradient;
            private final int type;

            public SwitchGradientDrawable(int r10) {
                this.paint = new Paint(1);
                this.gradient = new Shader[2];
                this.gradientMatrix = new Matrix();
                this.swapGradient = new AnimatedFloat(1.0f, new StarGiftSheet$CraftTopView$SwitchGradientDrawable$$ExternalSyntheticLambda0(this), 0, 420, CubicBezierInterpolator.EASE_OUT_QUINT);
                this.rect = new RectF();
                this.r = 0.0f;
                this.type = r10;
            }

            @Override // android.graphics.drawable.Drawable
            public void draw(Canvas r10) {
                this.rect.set(getBounds());
                RectF r0 = this.rect;
                r0.right = r0.left + r0.width();
                float r02 = this.swapGradient.set(1.0f);
                int r2 = 0;
            L3:
                Shader[] r3 = this.gradient;
                if (r2 >= r3.length) goto L18;
                if (r3[r2] == null) goto L16;
                float r32 = (float) Math.pow(1.0f - Math.abs(r2 - r02), 0.25d);
                if (r32 <= 0.0f) goto L16;
                this.gradientMatrix.reset();
                int r4 = this.type;
                Matrix r6 = this.gradientMatrix;
                if (r4 != 1) goto L14;
                r6.postTranslate(this.rect.centerX(), AndroidUtilities.dp(145.0f));
            L15:
                this.gradient[r2].setLocalMatrix(this.gradientMatrix);
                this.paint.setShader(this.gradient[r2]);
                this.paint.setAlpha((int) (r32 * 255.0f));
                RectF r33 = this.rect;
                float r42 = this.r;
                r10.drawRoundRect(r33, r42, r42, this.paint);
                goto L16
            L14:
                r6.postScale(getBounds().width() / 100.0f, 1.0f);
            L16:
                r2 = r2 + 1;
                goto L3
            L18:
                if (this.icon == null) goto L25;
                r10.save();
                r10.translate(this.rect.centerX(), AndroidUtilities.dp(145.0f));
                StarGiftPatterns.drawPattern(r10, 0, this.icon, this.rect.width(), AndroidUtilities.dp(290.0f), 2.0f, 1.0f);
                r10.restore();
                return;
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

            public void setColors(int r13, int r14) {
                if (this.color1 == r13) goto L5;
            L7:
                Shader[] r0 = this.gradient;
                r0[0] = r0[1];
                if (this.type != 0) goto L10;
                this.color1 = r13;
                this.color2 = r14;
                r0[1] = new LinearGradient(0.0f, 0.0f, 100.0f, 0.0f, new int[]{r13, r14}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
            L11:
                this.swapGradient.force(0.0f);
                invalidateSelf();
                return;
            L10:
                float r8 = AndroidUtilities.dp(340.0f);
                this.color1 = r13;
                this.color2 = r14;
                r0[1] = new RadialGradient(0.0f, 0.0f, r8, new int[]{r13, r14}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
                goto L11
            L5:
                if (this.color2 != r14) goto L7;
            }

            public void setIcon(Drawable r1) {
                this.icon = r1;
            }
        }

        public static /* synthetic */ void $r8$lambda$5VjoeEbEJVBxB1TZIzrnJJdBumY(CraftTopView r0, View r1) {
            r0.lambda$setupGiftButtons$8(r1);
        }

        /* renamed from: $r8$lambda$5qykpmGoijbhtOeIYzfMbOZBi-8, reason: not valid java name */
        public static /* synthetic */ void m19028$r8$lambda$5qykpmGoijbhtOeIYzfMbOZBi8(CraftTopView r0, View r1) {
            r0.lambda$new$0(r1);
        }

        /* renamed from: $r8$lambda$Bk2ZpJXkLPEGpoB2-ou-3Iuh9bU, reason: not valid java name */
        public static /* synthetic */ void m19029$r8$lambda$Bk2ZpJXkLPEGpoB2ou3Iuh9bU(CraftTopView r0, TL_stars.StarGift r1, ArrayList r2, Runnable r3) {
            r0.lambda$playAnimation$11(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$H0BqICLanRnnzAXtoQOWyt3u110(CraftTopView r0, int r1, TL_stars.StarGift r2) {
            r0.lambda$playAnimation$9(r1, r2);
        }

        /* renamed from: $r8$lambda$IXV1dQpCrJKFmKUPPIn6e4cn-jE, reason: not valid java name */
        public static /* synthetic */ void m19030$r8$lambda$IXV1dQpCrJKFmKUPPIn6e4cnjE(CraftTopView r0, SelectGiftView r1, TL_stars.StarGift r2) {
            r0.lambda$setupGiftButtons$7(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$LVwHVicDpxX9QOXnsy2MviTyrCE(CraftTopView r0, ValueAnimator r1) {
            r0.lambda$setupFinishFace$14(r1);
        }

        public static /* synthetic */ void $r8$lambda$NXv_X61M454pmHp8XJfOtbKG8zM(CraftTopView r0, View r1) {
            r0.lambda$new$1(r1);
        }

        /* renamed from: $r8$lambda$Q8tmmZyzZKvmQbh87p9-DGM4mdE, reason: not valid java name */
        public static /* synthetic */ void m19031$r8$lambda$Q8tmmZyzZKvmQbh87p9DGM4mdE(CraftTopView r0, View r1) {
            r0.lambda$new$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$_GqPQbO2vg534uxsWi5mGES1GhU(CraftTopView r0) {
            r0.lambda$playAnimation$13();
        }

        /* renamed from: $r8$lambda$c6krd0u9cEt4--DImxWgcqVZ5bY, reason: not valid java name */
        public static /* synthetic */ void m19032$r8$lambda$c6krd0u9cEt4DImxWgcqVZ5bY(CraftTopView r0, View r1) {
            r0.lambda$new$3(r1);
        }

        /* renamed from: $r8$lambda$lQ_PXlBxm-1JKLM8KeYYetfMcsc, reason: not valid java name */
        public static /* synthetic */ void m19033$r8$lambda$lQ_PXlBxm1JKLM8KeYYetfMcsc(CraftTopView r0, View r1) {
            r0.lambda$new$5(r1);
        }

        public static /* synthetic */ void $r8$lambda$nkN5m9U87OYUa7JMygnaoFC4sgk() {
            BotWebViewVibrationEffect.APP_ERROR.vibrate();
        }

        /* renamed from: $r8$lambda$qJB-4OVFK0kZM0mYgsd7WUG46Z8, reason: not valid java name */
        public static /* synthetic */ void m19034$r8$lambda$qJB4OVFK0kZM0mYgsd7WUG46Z8(CraftTopView r0, long r1, long r3, ArrayList r5) {
            r0.lambda$setup$6(r1, r3, r5);
        }

        /* renamed from: $r8$lambda$ryY3PhdHcaMAU5g4HGsk-rqkfuU, reason: not valid java name */
        public static /* synthetic */ void m19035$r8$lambda$ryY3PhdHcaMAU5g4HGskrqkfuU(CraftTopView r0, ArrayList r1, TL_stars.StarGift r2, Runnable r3) {
            r0.lambda$playAnimation$12(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$ulZy-pPEfcoe55dI3mOjVy1V3tM, reason: not valid java name */
        public static /* synthetic */ void m19036$r8$lambda$ulZypPEfcoe55dI3mOjVy1V3tM(CraftTopView r0, Theme.ResourcesProvider r1, View r2) {
            r0.lambda$new$4(r1, r2);
        }

        /* renamed from: -$$Nest$fgetgifts, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SelectGiftView[] m19037$$Nest$fgetgifts(CraftTopView r0) {
            return r0.gifts;
        }

        /* renamed from: -$$Nest$fgethelpButton, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ImageView m19038$$Nest$fgethelpButton(CraftTopView r0) {
            return r0.helpButton;
        }

        public CraftTopView(Context r28, Theme.ResourcesProvider r29) {
            super(r28);
            this.BACKGROUND_COLORS = new int[]{-14861233, -15787732, -11327734, -14742773, -14527649, -15920861};
            this.COLORS = new int[]{Theme.multAlpha(-1, 0.08f), Theme.multAlpha(-1, 0.08f), -294362, -3914963, -13519030, -12613223};
            this.resourcesProvider = r29;
            SwitchGradientDrawable r6 = new SwitchGradientDrawable(1);
            this.bg = r6;
            Drawable r8 = r28.getResources().getDrawable(R.drawable.filled_forge).mutate();
            r8.setColorFilter(new PorterDuffColorFilter(-16777216, PorterDuff.Mode.SRC_IN));
            r6.setIcon(r8);
            setBackground(r6);
            FrameLayout r62 = new FrameLayout(r28);
            this.buttonsLayout = r62;
            addView(r62, LayoutHelper.createFrame(-1, 60, 55));
            ImageView r82 = new ImageView(r28);
            this.helpButton = r82;
            r82.setImageResource(R.drawable.outline_question_mark);
            r82.setBackground(new RoundRectStrokeDrawable(AndroidUtilities.dp(24.0f), Theme.multAlpha(-1, 0.08f)));
            r62.addView(r82, LayoutHelper.createFrame(32, 32.0f, 51, 14.0f, 14.0f, 14.0f, 14.0f));
            r82.setOnClickListener(new StarGiftSheet$CraftTopView$$ExternalSyntheticLambda0(this));
            ScaleStateListAnimator.apply(r82);
            ImageView r83 = new ImageView(r28);
            this.closeButton = r83;
            r83.setImageResource(R.drawable.msg_close);
            r83.setBackground(new RoundRectStrokeDrawable(AndroidUtilities.dp(24.0f), Theme.multAlpha(-1, 0.08f)));
            r62.addView(r83, LayoutHelper.createFrame(32, 32.0f, 53, 14.0f, 14.0f, 14.0f, 14.0f));
            r83.setOnClickListener(new StarGiftSheet$CraftTopView$$ExternalSyntheticLambda1(this));
            ScaleStateListAnimator.apply(r83);
            TextView r63 = new TextView(r28);
            this.titleView = r63;
            r63.setTypeface(AndroidUtilities.bold());
            r63.setGravity(17);
            r63.setTextSize(1, 20.0f);
            r63.setTextColor(-1);
            r63.setText(LocaleController.getString(R.string.GiftCraftTitle));
            addView(r63, LayoutHelper.createFrame(-1, -2.0f, 49, 0.0f, 20.0f, 0.0f, 0.0f));
            FrameLayout r64 = new FrameLayout(r28);
            this.precraftingLayout = r64;
            addView(r64, LayoutHelper.createFrame(-1, -1, 119));
            FrameLayout r11 = new FrameLayout(r28);
            this.craftingLayout = r11;
            r11.setAlpha(0.0f);
            addView(r11, LayoutHelper.createFrame(-1, -1, 119));
            FrameLayout r112 = new FrameLayout(r28);
            this.failedLayout = r112;
            r112.setAlpha(0.0f);
            addView(r112, LayoutHelper.createFrame(-1, -1, 119));
            SpoilersTextView r10 = new SpoilersTextView(r28);
            this.textView = r10;
            r10.setGravity(17);
            r10.setTextSize(1, 13.0f);
            r10.setTextColor(-1);
            r64.addView(r10, LayoutHelper.createFrame(-1, -2.0f, 49, 32.0f, 244.0f, 32.0f, 84.0f));
            LinearLayout r65 = new LinearLayout(r28);
            this.attributesLayoutLine1 = r65;
            LayoutTransition r102 = new LayoutTransition();
            r102.setDuration(2, 320);
            r102.setDuration(3, 320);
            r102.setDuration(0, 320);
            r102.setDuration(1, 320);
            r102.setDuration(4, 320);
            CubicBezierInterpolator r4 = CubicBezierInterpolator.EASE_OUT_QUINT;
            r102.setInterpolator(2, r4);
            r102.setInterpolator(3, r4);
            r102.setInterpolator(0, r4);
            r102.setInterpolator(1, r4);
            r102.setInterpolator(4, r4);
            r65.setLayoutTransition(r102);
            r65.setOrientation(0);
            r65.setGravity(17);
            LinearLayout r66 = new LinearLayout(r28);
            this.attributesLayoutLine2 = r66;
            LayoutTransition r103 = new LayoutTransition();
            r103.setDuration(2, 320);
            r103.setDuration(3, 320);
            r103.setDuration(0, 320);
            r103.setDuration(1, 320);
            r103.setDuration(4, 320);
            r103.setInterpolator(2, r4);
            r103.setInterpolator(3, r4);
            r103.setInterpolator(0, r4);
            r103.setInterpolator(1, r4);
            r103.setInterpolator(4, r4);
            r66.setLayoutTransition(r103);
            r66.setOrientation(0);
            r66.setAlpha(0.0f);
            r66.setGravity(17);
            this.backdropAttributes = new AttributeView[4];
            this.patternAttributes = new AttributeView[4];
            int r42 = 0;
        L3:
            if (r42 >= 4) goto L5;
            LinearLayout r67 = this.attributesLayoutLine1;
            AttributeView[] r104 = this.backdropAttributes;
            AttributeView r113 = new AttributeView(r28);
            r104[r42] = r113;
            r67.addView(r113, LayoutHelper.createLinear(48, 54, 0.0f, 0.0f, 0.0f, 0.0f));
            this.backdropAttributes[r42].setOnClickListener(new StarGiftSheet$CraftTopView$$ExternalSyntheticLambda2(this));
            r42 = r42 + 1;
            goto L3
        L5:
            int r43 = 0;
        L6:
            if (r43 >= 4) goto L8;
            LinearLayout r68 = this.attributesLayoutLine1;
            AttributeView[] r105 = this.patternAttributes;
            AttributeView r114 = new AttributeView(r28);
            r105[r43] = r114;
            r68.addView(r114, LayoutHelper.createLinear(48, 54, 0.0f, 0.0f, 0.0f, 0.0f));
            this.patternAttributes[r43].setOnClickListener(new StarGiftSheet$CraftTopView$$ExternalSyntheticLambda3(this));
            r43 = r43 + 1;
            goto L6
        L8:
            this.gifts = new SelectGiftView[4];
            this.faces = new Face[6];
            int r44 = 0;
        L9:
            Face[] r5 = this.faces;
            if (r44 >= 6) goto L16;
            if (r44 != 5) goto L14;
            boolean r69 = true;
        L15:
            r5[r44] = new Face(r28, r69);
            r44 = r44 + 1;
            goto L9
        L14:
            r69 = false;
            goto L15
        L16:
            this.frontFace = r5[5];
            RaysView r3 = new RaysView(r28);
            this.rays = r3;
            r3.setVisibility(8);
            r3.setAlpha(0.0f);
            addView(r3, LayoutHelper.createFrame(300, 300.0f, 49, 0.0f, 0.0f, 0.0f, 0.0f));
            Cube3D r32 = new Cube3D(r28, this.faces);
            this.cube = r32;
            addView(r32, LayoutHelper.createFrame(-1, 300.0f, 55, 0.0f, 0.0f, 0.0f, 0.0f));
            AnimatedEmojiSpan.TextViewEmojis r33 = new AnimatedEmojiSpan.TextViewEmojis(r28);
            this.variantsButton = r33;
            r33.setTextSize(1, 12.0f);
            r33.setTypeface(AndroidUtilities.bold());
            float r610 = 1.0f;
            r33.setText(AndroidUtilities.replaceArrows(LocaleController.getString(R.string.GiftCraftViewAllVariants), false, AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f)));
            r33.setPadding(AndroidUtilities.dp(9.0f), 0, AndroidUtilities.dp(9.0f), 0);
            r33.setGravity(17);
            r33.setTextColor(-1);
            if (this.previewAttributes != null) goto L20;
            r610 = 0.25f;
        L20:
            r33.setAlpha(r610);
            r33.setBackground(new RoundRectStrokeDrawable(AndroidUtilities.dp(14.0f), Theme.multAlpha(-1, 0.08f)));
            ScaleStateListAnimator.apply(r33, 0.02f, 1.2f);
            this.precraftingLayout.addView(r33, LayoutHelper.createFrame(-2, 27.0f, 49, 32.0f, 412.0f, 32.0f, 84.0f));
            r33.setOnClickListener(new StarGiftSheet$CraftTopView$$ExternalSyntheticLambda4(this, r29));
            this.precraftingLayout.addView(this.attributesLayoutLine1, LayoutHelper.createFrame(-2, 54.0f, 49, 32.0f, 340.0f, 32.0f, 84.0f));
            this.precraftingLayout.addView(this.attributesLayoutLine2, LayoutHelper.createFrame(-2, 54.0f, 49, 32.0f, 394.0f, 32.0f, 84.0f));
            LinearLayout r2 = new LinearLayout(r28);
            this.button = r2;
            r2.setOrientation(1);
            ButtonBackground r34 = new ButtonBackground();
            this.buttonBackground = r34;
            r2.setBackground(r34);
            r34.setColor(Theme.multAlpha(-1, 0.08f), Theme.multAlpha(-1, 0.08f));
            ScaleStateListAnimator.apply(r2, 0.02f, 1.2f);
            addView(r2, LayoutHelper.createFrame(-1, -2.0f, 87, 20.0f, 0.0f, 20.0f, 18.0f));
            r2.setOnClickListener(new StarGiftSheet$CraftTopView$$ExternalSyntheticLambda5(this));
            AnimatedTextView r35 = new AnimatedTextView(r28);
            this.buttonTitle = r35;
            r35.setTypeface(AndroidUtilities.bold());
            r35.setGravity(17);
            r35.setTextColor(Theme.multAlpha(-1, 0.75f));
            r35.setText(LocaleController.getString(R.string.GiftCraftButton));
            r35.setTextSize(AndroidUtilities.dp(14.0f));
            r2.addView(r35, LayoutHelper.createLinear(-1, 18, 55, 16.0f, 7.33f, 16.0f, 0.0f));
            AnimatedTextView r36 = new AnimatedTextView(r28);
            this.buttonSubtitle = r36;
            r36.getDrawable().setHacks(true, true, false);
            r36.setGravity(17);
            r36.setTextColor(Theme.multAlpha(-1, 0.75f));
            r36.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GiftCraftSuccessChance, new Object[]{"0%"})));
            r36.setTextSize(AndroidUtilities.dp(12.0f));
            r2.addView(r36, LayoutHelper.createLinear(-1, 14, 55, 16.0f, 2.66f, 16.0f, 7.66f));
            LinearLayout r22 = new LinearLayout(r28);
            r22.setOrientation(0);
            r22.setGravity(17);
            RLottieImageView r37 = new RLottieImageView(r28);
            this.craftingIconView = r37;
            r37.setAutoRepeat(true);
            r37.setAnimation(R.raw.gift_crafting, 30, 30);
            r22.addView(r37, LayoutHelper.createLinear(30, 30, 17, 0, 0, 4, 0));
            TextView r38 = new TextView(r28);
            this.craftingTitleView = r38;
            r38.setTextSize(1, 20.0f);
            r38.setTextColor(-1);
            r38.setTypeface(AndroidUtilities.bold());
            r38.setGravity(17);
            r38.setText(LocaleController.getString(R.string.GiftCraftProgressTitle));
            r22.addView(r38, LayoutHelper.createLinear(-2, -2, 17, 0, 0, 0, 0));
            this.craftingLayout.addView(r22, LayoutHelper.createFrame(-1, -2.0f, 49, 0.0f, 350.0f, 0.0f, 0.0f));
            TextView r23 = new TextView(r28);
            this.craftingSubtitleView = r23;
            r23.setTextSize(1, 13.0f);
            r23.setTextColor(Theme.multAlpha(-1, 0.5f));
            r23.setTypeface(AndroidUtilities.bold());
            r23.setGravity(17);
            this.craftingLayout.addView(r23, LayoutHelper.createFrame(-1, -2.0f, 49, 0.0f, 383.0f, 0.0f, 0.0f));
            TextView r24 = new TextView(r28);
            this.craftingChanceView = r24;
            r24.setTextSize(1, 13.0f);
            r24.setTextColor(-1);
            r24.setTypeface(AndroidUtilities.bold());
            r24.setGravity(17);
            r24.setPadding(AndroidUtilities.dp(9.0f), 0, AndroidUtilities.dp(9.0f), 0);
            r24.setBackground(new RoundRectStrokeDrawable(AndroidUtilities.dp(14.0f), Theme.multAlpha(-1, 0.08f)));
            this.craftingLayout.addView(r24, LayoutHelper.createFrame(-2, 27.0f, 81, 0.0f, 0.0f, 0.0f, 74.0f));
            TextView r25 = new TextView(r28);
            this.craftingFooterView = r25;
            r25.setTextSize(1, 13.0f);
            r25.setTextColor(Theme.multAlpha(-1, 0.5f));
            r25.setGravity(17);
            r25.setText(LocaleController.getString(R.string.GiftCraftProgressText));
            this.craftingLayout.addView(r25, LayoutHelper.createFrame(-1, -2.0f, 81, 42.0f, 0.0f, 42.0f, 24.0f));
            TextView r26 = new TextView(r28);
            this.failedTitle = r26;
            r26.setText(LocaleController.getString(R.string.GiftCraftFailedTitle));
            r26.setTextColor(-505270);
            r26.setTextSize(1, 20.0f);
            r26.setTypeface(AndroidUtilities.bold());
            r26.setGravity(17);
            this.failedLayout.addView(r26, LayoutHelper.createFrame(-1, -2.0f, 55, 32.0f, 352.0f, 32.0f, 0.0f));
            TextView r27 = new TextView(r28);
            this.failedSubtitle = r27;
            r27.setTextColor(-17253);
            r27.setTextSize(1, 13.0f);
            r27.setGravity(17);
            this.failedLayout.addView(r27, LayoutHelper.createFrame(-1, -2.0f, 55, 32.0f, 383.0f, 32.0f, 0.0f));
            LinearLayout r210 = new LinearLayout(r28);
            this.failedGiftsLayout = r210;
            r210.setOrientation(0);
            this.failedLayout.addView(r210, LayoutHelper.createFrame(-2, -2.0f, 49, 0.0f, 250.0f, 0.0f, 0.0f));
            this.failedGifts = null;
            updateCounts();
        }

        private /* synthetic */ void lambda$new$0(View r2) {
            if (this.buttonsLayout.getAlpha() >= 1.0f) goto L5;
            return;
        L5:
            this.onClose.run();
        }

        private /* synthetic */ void lambda$new$1(View r2) {
            if (this.buttonsLayout.getAlpha() >= 1.0f) goto L5;
            return;
        L5:
            this.onClose.run();
        }

        private /* synthetic */ void lambda$new$2(View r1) {
            showHint((AttributeView) r1);
        }

        private /* synthetic */ void lambda$new$3(View r1) {
            showHint((AttributeView) r1);
        }

        private /* synthetic */ void lambda$new$4(Theme.ResourcesProvider r8, View r9) {
            if (this.variantsButton.getAlpha() >= 1.0f) goto L5;
            return;
        L5:
            if (this.crafting == false) goto L7;
            return;
        L7:
            if (this.failed == false) goto L10;
            return;
        L10:
            if (this.previewAttributes == null) goto L15;
            new StarGiftPreviewSheet(getContext(), r8, this.currentAccount, this.collectionTitle, this.previewAttributes, true).show();
            return;
        }

        private /* synthetic */ void lambda$new$5(View r4) {
            if (getAlpha() >= 1.0f) goto L6;
            return;
        L6:
            if (this.crafting == false) goto L9;
            return;
        L9:
            if (this.failed == false) goto L12;
            setup();
            return;
        L12:
            ArrayList r42 = new ArrayList();
            int r0 = 0;
        L13:
            SelectGiftView[] r1 = this.gifts;
            if (r0 >= r1.length) goto L22;
            SelectGiftView r12 = r1[r0];
            if (r12 == null) goto L20;
            if (r12.getGift() == null) goto L20;
            r42.add(this.gifts[r0].getGift());
        L20:
            r0 = r0 + 1;
            goto L13
        L22:
            if (r42.isEmpty() == false) goto L24;
        L28:
            AndroidUtilities.shakeViewSpring(this.button);
            return;
        L24:
            if (this.onCraft == null) goto L28;
            playAnimation();
        }

        private /* synthetic */ void lambda$playAnimation$11(TL_stars.StarGift r13, ArrayList r14, Runnable r15) {
            this.crafting = false;
            if (r13 != null) goto L27;
            RLottieImageView r132 = this.brokenGiftImage;
            if (r132 == null) goto L7;
            r132.playAnimation();
            AndroidUtilities.runOnUIThread(new StarGiftSheet$CraftTopView$$ExternalSyntheticLambda14(), 750);
        L7:
            this.precraftingLayout.animate().alpha(0.0f).start();
            this.failedLayout.animate().alpha(1.0f).start();
            this.button.animate().alpha(1.0f).start();
            this.craftingLayout.animate().alpha(0.0f).start();
            this.buttonsLayout.animate().alpha(1.0f).start();
            this.failedSubtitle.setText(AndroidUtilities.replaceTags(LocaleController.formatPluralString("GiftCraftFailedText", r14.size(), new Object[0])));
            this.buttonTitle.setText(LocaleController.getString(R.string.GiftCraftButtonFailed));
            this.buttonTitle.setTranslationY(AndroidUtilities.dp(6.0f));
            this.buttonSubtitle.setAlpha(0.0f);
            if (this.failedGifts == null) goto L14;
            int r133 = 0;
        L10:
            GiftSheet.GiftCell[] r152 = this.failedGifts;
            if (r133 >= r152.length) goto L13;
            AndroidUtilities.removeFromParent(r152[r133]);
            r133 = r133 + 1;
            goto L10
        L13:
            this.failedGifts = null;
        L14:
            this.failedGifts = new GiftSheet.GiftCell[r14.size()];
            int r134 = 0;
        L16:
            if (r134 >= r14.size()) goto L26;
            TL_stars.StarGift r2 = (TL_stars.StarGift) r14.get(r134);
            GiftSheet.GiftCell r1 = new GiftSheet.GiftCell(getContext(), this.currentAccount, this.resourcesProvider);
            r1.setStarsGift(r2, false, false, false, false, true);
            r1.chanceTextView.setVisibility(8);
            r1.setRibbonColor(-3065286);
            BackupImageView r153 = r1.imageView;
            FrameLayout.LayoutParams r22 = LayoutHelper.createFrame(42, 42, 17);
            r1.imageViewLayoutParams = r22;
            r153.setLayoutParams(r22);
            int r154 = r134 + 1;
            if (r154 < r14.size()) goto L20;
            boolean r23 = true;
        L21:
            LinearLayout r3 = this.failedGiftsLayout;
            this.failedGifts[r134] = r1;
            if (r23 == false) goto L24;
            int r10 = 0;
        L25:
            r3.addView(r1, LayoutHelper.createLinear(74, 74, 0.0f, 51, 0, 0, r10, 0));
            r134 = r154;
            goto L16
        L24:
            r10 = 6;
            goto L25
        L20:
            r23 = false;
            goto L21
        L26:
            return;
        L27:
            AndroidUtilities.runOnUIThread(r15);
        }

        private /* synthetic */ void lambda$playAnimation$12(ArrayList r18, TL_stars.StarGift r19, Runnable r20) {
            HintView2 r3 = this.currentHint;
            if (r3 == null) goto L5;
            r3.hide();
            this.currentHint = null;
        L5:
            this.crafted = true;
            if (r19 != null) goto L8;
            boolean r5 = true;
        L9:
            this.failed = r5;
            this.craftedGift = r19;
            this.openCraftedGift = r20;
            Cube3D.AnimSequence r52 = new Cube3D.AnimSequence(this.cube);
            ArrayList r6 = new ArrayList();
            int r7 = 0;
        L10:
            SelectGiftView[] r8 = this.gifts;
            if (r7 >= r8.length) goto L18;
            SelectGiftView r82 = r8[r7];
            if (r82 == null) goto L17;
            if (r82.getGift() == null) goto L17;
            r6.add(Integer.valueOf(r7));
        L17:
            r7 = r7 + 1;
            goto L10
        L18:
            int r72 = r6.size();
            SelectGiftView[] r83 = this.gifts;
            int r9 = 4;
            int r11 = 40;
            if (r72 != 1) goto L21;
            r52.put(r83[((Integer) r6.get(0)).intValue()], 5, 32).friction(false).fling(26.0f, -26.0f).delay(90).friction(true).delay(20);
        L49:
            r52.run(new StarGiftSheet$CraftTopView$$ExternalSyntheticLambda11(this, r9, r19)).steerTo(r9, r11, -90.0f).start(new StarGiftSheet$CraftTopView$$ExternalSyntheticLambda12(this, r19, r18, r20));
            return;
        L21:
            int[] r92 = {5, 0, 2, 3, 4};
            SelectGiftView r84 = r83[0];
            if (r84 != null) goto L24;
        L26:
            int r85 = 0;
        L27:
            SelectGiftView r13 = this.gifts[1];
            if (r13 != null) goto L30;
        L34:
            SelectGiftView r10 = this.gifts[2];
            if (r10 != null) goto L37;
        L41:
            SelectGiftView r62 = this.gifts[3];
            if (r62 != null) goto L44;
        L48:
            r52.friction(false);
            r52.delay(40);
            r52.friction(true);
            r52.delay(40);
            r9 = r92[r85];
            r11 = 80;
            goto L49
        L44:
            if (r62.getGift() == null) goto L48;
            if (r85 <= 0) goto L47;
            r52.delay(42);
        L47:
            r52.put(this.gifts[3], r92[r85], 32).fling(-31.0f, 31.0f);
            r85 = r85 + 1;
            goto L48
        L37:
            if (r10.getGift() == null) goto L41;
            if (r85 <= 0) goto L40;
            r52.delay(42);
        L40:
            r52.put(this.gifts[2], r92[r85], 32, 180.0f).fling(-36.0f, -36.0f);
            r85 = r85 + 1;
            goto L41
        L30:
            if (r13.getGift() == null) goto L34;
            if (r85 <= 0) goto L33;
            r52.delay(42);
        L33:
            r52.put(this.gifts[1], r92[r85], 32).fling(25.0f, 31.0f);
            r85 = r85 + 1;
            goto L34
        L24:
            if (r84.getGift() == null) goto L26;
            r52.put(this.gifts[0], r92[0], 32).fling(25.0f, -22.0f);
            r85 = 1;
            goto L27
        L8:
            r5 = false;
            goto L9
        }

        private /* synthetic */ void lambda$playAnimation$13() {
            this.crafting = false;
            this.openCraftedGift = null;
            this.failed = false;
            setup();
        }

        private /* synthetic */ void lambda$playAnimation$9(int r1, TL_stars.StarGift r2) {
            setupFinishFace(r1, r2);
        }

        private /* synthetic */ void lambda$setup$6(long r7, long r9, ArrayList r11) {
            if (r7 == r9) goto L5;
            return;
        L5:
            this.previewAttributes = r11;
            ViewPropertyAnimator r72 = this.variantsButton.animate();
            if (this.attributesTwoLines == false) goto L9;
            float r8 = 0.0f;
        L12:
            r72.alpha(r8).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(420).start();
            ArrayList r73 = new ArrayList();
            int r10 = 0;
        L14:
            if (r10 >= r11.size()) goto L23;
            if ((r11.get(r10) instanceof TL_stars.starGiftAttributeModel) == false) goto L22;
            if ((((TL_stars.StarGiftAttribute) r11.get(r10)).rarity instanceof TL_stars.TL_starGiftAttributeRarity) == true) goto L22;
            r73.add((TL_stars.starGiftAttributeModel) r11.get(r10));
            if (r73.size() >= 3) goto L23;
        L22:
            r10 = r10 + 1;
        L23:
            SpannableStringBuilder r102 = new SpannableStringBuilder();
            int r112 = r73.size();
            int r0 = 0;
        L25:
            if (r0 >= r112) goto L28;
            Object r2 = r73.get(r0);
            r0 = r0 + 1;
            r102.append("x");
            r102.setSpan(new AnimatedEmojiSpan(((TL_stars.starGiftAttributeModel) r2).document, this.variantsButton.getPaint().getFontMetricsInt()), r102.length() - 1, r102.length(), 33);
            r102.append(" ");
            goto L25
        L28:
            if (r102.length() <= 0) goto L30;
            r102.append(" ");
        L30:
            r102.append(AndroidUtilities.replaceArrows(LocaleController.getString(R.string.GiftCraftViewAllVariants), false, AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f)));
            this.variantsButton.setText(r102);
            return;
        L9:
            if (this.previewAttributes == null) goto L11;
            r8 = 1.0f;
            goto L12
        L11:
            r8 = 0.25f;
            goto L12
        }

        private /* synthetic */ void lambda$setupFinishFace$14(ValueAnimator r1) {
            this.cube.invalidate();
        }

        private /* synthetic */ void lambda$setupGiftButtons$7(SelectGiftView r2, TL_stars.StarGift r3) {
            r2.setGift(r3, true);
            updateCounts();
        }

        private /* synthetic */ void lambda$setupGiftButtons$8(View r7) {
            SelectGiftView r0 = (SelectGiftView) r7;
            boolean r2 = true;
            if (r0.getGift() != null) goto L5;
        L8:
            int r3 = 0;
        L9:
            SelectGiftView[] r4 = this.gifts;
            if (r3 >= r4.length) goto L19;
            SelectGiftView r42 = r4[r3];
            if (r42 == r7) goto L19;
            if (r42 == null) goto L18;
            if (r42.getGift() == null) goto L18;
            r2 = false;
        L18:
            r3 = r3 + 1;
        L19:
            this.onAddGift.run(new StarGiftSheet$CraftTopView$$ExternalSyntheticLambda10(this, r0), Boolean.valueOf(r2));
            return;
        L5:
            if (r0.isReplaceIcon == true) goto L8;
            r0.setGift(null, true);
            updateCounts();
        }

        private void removeFromParent(View r4) {
            if (r4 == null) goto L19;
            ViewParent r3 = r4.getParent();
            if ((r3 instanceof ViewGroup) == true) goto L7;
            return;
        L7:
            ViewGroup r32 = (ViewGroup) r3;
            LayoutTransition r0 = r32.getLayoutTransition();
            if (r0 == null) goto L10;
            boolean r1 = true;
        L12:
            if (r1 == false) goto L14;
            r0.disableTransitionType(3);
        L14:
            r32.removeView(r4);
            if (r1 == false) goto L17;
            r0.enableTransitionType(3);
        L17:
            r4.animate().cancel();
            r4.clearAnimation();
            r4.setTranslationX(0.0f);
            r4.setTranslationY(0.0f);
            r4.setTranslationZ(0.0f);
            r4.setAlpha(0.0f);
            r4.setScaleX(1.0f);
            r4.setScaleY(1.0f);
            r4.setRotation(0.0f);
            r4.setRotationX(0.0f);
            r4.setRotationY(0.0f);
            return;
        L10:
            r1 = false;
            goto L12
        }

        private void setupFinishFace(int r9, TL_stars.StarGift r10) {
            if (r10 == null) goto L6;
            SelectGiftView r5 = new SelectGiftView(this, getContext());
            r5.setGift(r10, false);
            r5.setRotation(180.0f);
            this.cube.putView(r9, r5);
            r5.setScaleX(0.5f);
            r5.setScaleY(0.5f);
            r5.setAlpha(0.0f);
            ViewPropertyAnimator r102 = r5.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(520);
            CubicBezierInterpolator r1 = CubicBezierInterpolator.EASE_OUT_QUINT;
            ViewPropertyAnimator r103 = r102.setInterpolator(r1);
            r103.setUpdateListener(new StarGiftSheet$CraftTopView$$ExternalSyntheticLambda13(this));
            r103.start();
            Cube3D.m19041$$Nest$fgetfaces(this.cube)[r9].setVisibility(8);
            this.rays.setVisibility(0);
            this.rays.setAlpha(0.0f);
            this.rays.animate().alpha(0.5f).setDuration(820).setInterpolator(r1).start();
            return;
        L6:
            FrameLayout r104 = new FrameLayout(getContext());
            RLottieImageView r52 = new RLottieImageView(getContext());
            r52.setAnimation(R.raw.gift_broken, 32, 32);
            r104.addView(r52, LayoutHelper.createFrame(32, 32, 17));
            r52.setScaleX(0.5f);
            r52.setScaleY(0.5f);
            r52.setAlpha(0.0f);
            r52.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).start();
            this.brokenGiftImage = r52;
            r104.setBackground(new RoundRectStrokeDrawable(AndroidUtilities.dp(12.0f), Theme.multAlpha(-1, 0.075f)));
            Cube3D.m19041$$Nest$fgetfaces(this.cube)[r9].setVisibility(8);
            r104.setRotation(180.0f);
            this.cube.putView(r9, r104);
            ButtonBackground r92 = this.buttonBackground;
            int[] r105 = this.COLORS;
            r92.setColor(r105[2], r105[3]);
            SwitchGradientDrawable r93 = this.bg;
            int[] r106 = this.BACKGROUND_COLORS;
            r93.setColors(r106[2], r106[3]);
            RaysView r94 = this.rays;
            int[] r8 = this.COLORS;
            r94.setColor(r8[3], r8[2]);
        }

        private void setupGiftButtons() {
            SelectGiftView[] r0 = this.gifts;
            SelectGiftView r1 = new SelectGiftView(this, getContext());
            int r2 = 0;
            r0[0] = r1;
            addView(r1, LayoutHelper.createFrame(76, 76.0f, 49, -117.0f, 74.0f, 0.0f, 0.0f));
            SelectGiftView[] r02 = this.gifts;
            SelectGiftView r12 = new SelectGiftView(this, getContext());
            r02[1] = r12;
            addView(r12, LayoutHelper.createFrame(76, 76.0f, 49, -117.0f, 149.0f, 0.0f, 0.0f));
            SelectGiftView[] r03 = this.gifts;
            SelectGiftView r13 = new SelectGiftView(this, getContext());
            r03[2] = r13;
            addView(r13, LayoutHelper.createFrame(76, 76.0f, 49, 117.0f, 74.0f, 0.0f, 0.0f));
            SelectGiftView[] r04 = this.gifts;
            SelectGiftView r14 = new SelectGiftView(this, getContext());
            r04[3] = r14;
            addView(r14, LayoutHelper.createFrame(76, 76.0f, 49, 117.0f, 149.0f, 0.0f, 0.0f));
        L3:
            SelectGiftView[] r05 = this.gifts;
            if (r2 >= r05.length) goto L6;
            ScaleStateListAnimator.apply(r05[r2]);
            this.gifts[r2].setClickable(true);
            this.gifts[r2].setOnClickListener(new StarGiftSheet$CraftTopView$$ExternalSyntheticLambda7(this));
            r2 = r2 + 1;
            goto L3
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void updateAttributeFreq() {
            int[][] r1 = MessagesController.getInstance(this.currentAccount).stargiftsCraftAttributesPermilles;
            HashMap r2 = new HashMap();
            HashMap r3 = new HashMap();
            ArrayList r4 = new ArrayList();
            int r5 = 0;
            int r7 = 0;
            int r8 = 0;
        L3:
            SelectGiftView[] r9 = this.gifts;
            int r13 = 1;
            if (r7 >= r9.length) goto L11;
            SelectGiftView r92 = r9[r7];
            if (r92 == null) goto L10;
            if (r92.getGift() == null) goto L10;
            r8 = r8 + 1;
            TL_stars.StarGift r93 = this.gifts[r7].getGift();
            TL_stars.starGiftAttributePattern r10 = (TL_stars.starGiftAttributePattern) StarsController.findAttribute(r93.attributes, TL_stars.starGiftAttributePattern.class);
            TL_stars.starGiftAttributeBackdrop r94 = (TL_stars.starGiftAttributeBackdrop) StarsController.findAttribute(r93.attributes, TL_stars.starGiftAttributeBackdrop.class);
            r2.put(Integer.valueOf(r94.backdrop_id), Integer.valueOf(((Integer) r2.getOrDefault(Integer.valueOf(r94.backdrop_id), 0)).intValue() + 1));
            r3.put(Long.valueOf(r10.document.id), Integer.valueOf(((Integer) r3.getOrDefault(Long.valueOf(r10.document.id), 0)).intValue() + 1));
        L10:
            r7 = r7 + 1;
            goto L3
        L11:
            int r102 = 4;
            if (r2.isEmpty() == false) goto L17;
            AttributeView r22 = this.backdropAttributes[0];
            r22.setBackdrop(null);
            r22.setProgress(0.0f, true);
            r4.add(r22);
            int r23 = 1;
        L14:
            if (r23 >= 4) goto L16;
            this.backdropAttributes[r23].setVisibility(8);
            r23 = r23 + 1;
            goto L14
        L16:
            float r17 = 1000.0f;
        L38:
            if (r3.isEmpty() == false) goto L44;
            AttributeView r12 = this.patternAttributes[0];
            r12.setIcon(null);
            r12.setProgress(0.0f, true);
            r4.add(r12);
            int r132 = 1;
        L41:
            if (r132 >= 4) goto L43;
            this.patternAttributes[r132].setVisibility(8);
            r132 = r132 + 1;
            goto L41
        L43:
            boolean r133 = true;
        L68:
            if (r4.size() > 5) goto L71;
            r133 = false;
        L71:
            this.attributesTwoLines = r133;
            int r14 = 0;
        L73:
            if (r14 >= r4.size()) goto L84;
            AttributeView r15 = (AttributeView) r4.get(r14);
            if (this.attributesTwoLines == true) goto L77;
        L79:
            LinearLayout r24 = this.attributesLayoutLine1;
        L81:
            if (r15.getParent() == r24) goto L83;
            removeFromParent(r15);
            r24.addView(r15, LayoutHelper.createLinear(48, 54, 0.0f, 0.0f, 0.0f, 0.0f));
        L83:
            r15.setVisibility(0);
            r14 = r14 + 1;
            goto L73
        L77:
            if (r14 < (r4.size() / 2.0f)) goto L79;
            r24 = this.attributesLayoutLine2;
            goto L81
        L84:
            ViewPropertyAnimator r16 = this.attributesLayoutLine2.animate();
            if (this.attributesTwoLines == false) goto L87;
            float r25 = 1.0f;
        L88:
            r16.alpha(r25);
            ViewPropertyAnimator r18 = this.variantsButton.animate();
            if (this.attributesTwoLines == false) goto L92;
            float r142 = 0.0f;
        L95:
            r18.alpha(r142);
            return;
        L92:
            if (this.previewAttributes == null) goto L94;
            r142 = 1.0f;
            goto L95
        L94:
            r142 = 0.25f;
            goto L95
        L87:
            r25 = 0.0f;
            goto L88
        L44:
            ArrayList r72 = new ArrayList(r3.entrySet());
            Collections.sort(r72, Map.Entry.comparingByValue());
            int r32 = r72.size();
            int r95 = 0;
            int r103 = 0;
        L45:
            if (r103 >= r32) goto L64;
            Object r11 = r72.get(r103);
            r103 = r103 + 1;
            Map.Entry r112 = (Map.Entry) r11;
            long r134 = ((Long) r112.getKey()).longValue();
            int r113 = ((Integer) r112.getValue()).intValue();
            int r152 = r5;
        L47:
            SelectGiftView[] r26 = this.gifts;
            if (r152 >= r26.length) goto L59;
            SelectGiftView r27 = r26[r152];
            if (r27 == null) goto L58;
            if (r27.getGift() == null) goto L58;
            TL_stars.starGiftAttributePattern r28 = (TL_stars.starGiftAttributePattern) StarsController.findAttribute(this.gifts[r152].getGift().attributes, TL_stars.starGiftAttributePattern.class);
            if (r28 == null) goto L58;
            if (r28.document.id != r134) goto L58;
        L60:
            if (r28 == null) goto L63;
            AttributeView r52 = this.patternAttributes[r95];
            r52.setIcon(r28);
            int[] r29 = r1[Utilities.clamp(r8 - 1, r1.length - 1, 0)];
            r52.setProgress(r29[Utilities.clamp(r113 - 1, r29.length - 1, 0)] / r17, true);
            r4.add(r52);
            r95 = r95 + 1;
        L63:
            r5 = 0;
        L58:
            r152 = r152 + 1;
            goto L47
        L59:
            r28 = null;
            goto L60
        L64:
            r133 = true;
        L65:
            if (r95 >= 4) goto L68;
            this.patternAttributes[r95].setVisibility(8);
            r95 = r95 + 1;
            goto L65
        L17:
            ArrayList r6 = new ArrayList(r2.entrySet());
            Collections.sort(r6, Map.Entry.comparingByValue());
            int r210 = r6.size();
            int r73 = 0;
            int r162 = 0;
            r17 = 1000.0f;
        L18:
            if (r73 >= r210) goto L34;
            Object r182 = r6.get(r73);
            r73 = r73 + 1;
            Map.Entry r183 = (Map.Entry) r182;
            int r143 = ((Integer) r183.getKey()).intValue();
            int r184 = ((Integer) r183.getValue()).intValue();
            int r153 = 0;
        L20:
            SelectGiftView[] r96 = this.gifts;
            if (r153 >= r96.length) goto L30;
            SelectGiftView r97 = r96[r153];
            if (r97 == null) goto L29;
            if (r97.getGift() == null) goto L29;
            TL_stars.starGiftAttributeBackdrop r98 = (TL_stars.starGiftAttributeBackdrop) StarsController.findAttribute(this.gifts[r153].getGift().attributes, TL_stars.starGiftAttributeBackdrop.class);
            if (r98.backdrop_id != r143) goto L29;
        L31:
            if (r98 == null) goto L33;
            AttributeView r104 = this.backdropAttributes[r162];
            r104.setBackdrop(r98);
            int[] r99 = r1[Utilities.clamp(r8 - 1, r1.length - r13, 0)];
            int r154 = r184 - 1;
            r104.setProgress(r99[Utilities.clamp(r154, r99.length - 1, 0)] / 1000.0f, r13);
            r4.add(r104);
            r162 = r162 + 1;
        L33:
            r102 = 4;
            r13 = 1;
        L29:
            r153 = r153 + 1;
            goto L20
        L30:
            r98 = null;
            goto L31
        L34:
            int r62 = r102;
            int r211 = r162;
        L35:
            if (r211 >= r62) goto L38;
            this.backdropAttributes[r211].setVisibility(8);
            r211 = r211 + 1;
            r62 = 4;
            goto L35
        }

        private void updateGiftButtonIcons() {
            if (this.gifts == null) goto L33;
            int r2 = 0;
            boolean r3 = true;
        L6:
            SelectGiftView[] r4 = this.gifts;
            if (r2 >= r4.length) goto L42;
            SelectGiftView r42 = r4[r2];
            if (r42 == null) goto L32;
            if (r42.getGift() == null) goto L32;
            if (r3 == false) goto L30;
            int r32 = r2 + 1;
        L14:
            SelectGiftView[] r43 = this.gifts;
            if (r32 >= r43.length) goto L22;
            SelectGiftView r44 = r43[r32];
            if (r44 == null) goto L21;
            if (r44.getGift() == null) goto L21;
            TL_stars.StarGift r33 = this.gifts[r32].getGift();
        L23:
            SelectGiftView r45 = this.gifts[r2];
            if (r33 != null) goto L26;
        L28:
            boolean r34 = false;
        L29:
            r45.setReplaceIcon(r34);
        L31:
            r3 = false;
            goto L32
        L26:
            if (TextUtils.isEmpty(r33.gift_address) == true) goto L28;
            r34 = true;
        L21:
            r32 = r32 + 1;
            goto L14
        L22:
            r33 = null;
            goto L23
        L30:
            this.gifts[r2].setReplaceIcon(false);
        L32:
            r2 = r2 + 1;
            goto L6
        L42:
            return;
        }

        public TL_stars.StarGift getFirstGift() {
            int r0 = 0;
        L3:
            SelectGiftView[] r1 = this.gifts;
            if (r0 >= r1.length) goto L12;
            SelectGiftView r12 = r1[r0];
            if (r12 == null) goto L11;
            if (r12.getGift() == null) goto L11;
            return this.gifts[r0].getGift();
        L11:
            r0 = r0 + 1;
            goto L3
        L12:
            return null;
        }

        public int getGiftsSelectedCount() {
            int r0 = 0;
            int r1 = 0;
        L3:
            SelectGiftView[] r2 = this.gifts;
            if (r0 >= r2.length) goto L11;
            SelectGiftView r22 = r2[r0];
            if (r22 == null) goto L10;
            if (r22.getGift() == null) goto L10;
            r1 = r1 + 1;
        L10:
            r0 = r0 + 1;
            goto L3
        L11:
            return r1;
        }

        public int getGiftsSuccessChance() {
            int r0 = 0;
            int r1 = 0;
        L3:
            SelectGiftView[] r2 = this.gifts;
            if (r0 >= r2.length) goto L11;
            SelectGiftView r22 = r2[r0];
            if (r22 == null) goto L10;
            if (r22.getGift() == null) goto L10;
            r1 = r1 + this.gifts[r0].getGift().craft_chance_permille;
        L10:
            r0 = r0 + 1;
            goto L3
        L11:
            return r1;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }

        public void playAnimation() {
            this.crafting = true;
            int r0 = 0;
            this.failed = false;
            this.openCraftedGift = null;
            HintView2 r2 = this.currentHint;
            if (r2 == null) goto L5;
            r2.hide();
            this.currentHint = null;
        L5:
            this.craftingSubtitleView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.craftingChanceView.setText(LocaleController.formatString(R.string.GiftCraftProgressSuccessChance, new Object[]{AffiliateProgramFragment.percents(getGiftsSuccessChance())}));
            int r1 = 0;
        L6:
            SelectGiftView[] r22 = this.gifts;
            if (r1 >= r22.length) goto L14;
            SelectGiftView r23 = r22[r1];
            if (r23 == null) goto L13;
            r23.setClickable(false);
            if (this.gifts[r1].getGift() != null) goto L13;
            this.gifts[r1].animate().alpha(0.0f).start();
        L13:
            r1 = r1 + 1;
            goto L6
        L14:
            int r12 = 0;
        L15:
            SelectGiftView[] r24 = this.gifts;
            if (r12 >= r24.length) goto L23;
            SelectGiftView r25 = r24[r12];
            if (r25 == null) goto L22;
            if (r25.getGift() == null) goto L22;
            TL_stars.StarGift r13 = this.gifts[r12].getGift();
            this.craftingSubtitleView.setText(r13.title + " #" + LocaleController.formatNumber(r13.num, ','));
        L22:
            r12 = r12 + 1;
        L23:
            this.precraftingLayout.animate().alpha(0.0f).start();
            this.button.animate().alpha(0.0f).start();
            this.craftingLayout.animate().alpha(1.0f).start();
            this.buttonsLayout.animate().alpha(0.25f).start();
            this.craftingIconView.playAnimation();
            ArrayList<TL_stars.StarGift> r14 = new ArrayList();
        L24:
            SelectGiftView[] r26 = this.gifts;
            if (r0 >= r26.length) goto L30;
            if (r26[r0].getGift() == null) goto L29;
            r14.add(this.gifts[r0].getGift());
        L29:
            r0 = r0 + 1;
            goto L24
        L30:
            this.onCraft.run(r14, new StarGiftSheet$CraftTopView$$ExternalSyntheticLambda8(this, r14), new StarGiftSheet$CraftTopView$$ExternalSyntheticLambda9(this));
        }

        public void selectGift(TL_stars.StarGift r4) {
            if (r4 != null) goto L4;
            return;
        L4:
            int r0 = 0;
        L5:
            SelectGiftView[] r1 = this.gifts;
            if (r0 >= r1.length) goto L11;
            if (r1[r0].getGift() == null) goto L9;
            r0 = r0 + 1;
            goto L5
        L9:
            this.gifts[r0].setGift(r4, true);
        L11:
            updateCounts();
        }

        public void setOnAddGift(Utilities.Callback2<Utilities.Callback<TL_stars.StarGift>, Boolean> r1) {
            this.onAddGift = r1;
        }

        public void setOnClose(Runnable r1) {
            this.onClose = r1;
        }

        public void setOnCraft(Utilities.Callback3<ArrayList<TL_stars.StarGift>, Utilities.Callback2<TL_stars.StarGift, Runnable>, Runnable> r1) {
            this.onCraft = r1;
        }

        public void setup() {
            setup(this.currentAccount, this.giftId, this.document, this.collectionTitle);
        }

        public void setup(int r7, long r8, TLRPC.Document r10, String r11) {
            this.currentAccount = r7;
            this.giftId = r8;
            this.document = r10;
            this.collectionTitle = r11;
            this.crafting = false;
            this.failed = false;
            int r112 = 0;
        L3:
            SelectGiftView[] r0 = this.gifts;
            if (r112 >= r0.length) goto L9;
            SelectGiftView r02 = r0[r112];
            if (r02 == null) goto L8;
            AndroidUtilities.removeFromParent(r02);
        L8:
            r112 = r112 + 1;
            goto L3
        L9:
            this.cube.reset();
            setupGiftButtons();
            updateCounts(false);
            this.crafting = false;
            this.precraftingLayout.animate().cancel();
            this.precraftingLayout.setAlpha(1.0f);
            this.button.animate().cancel();
            this.button.setAlpha(1.0f);
            this.craftingLayout.animate().cancel();
            this.craftingLayout.setAlpha(0.0f);
            this.failedLayout.animate().cancel();
            this.failedLayout.setAlpha(0.0f);
            this.buttonsLayout.animate().cancel();
            this.buttonsLayout.setAlpha(1.0f);
            AnimatedEmojiSpan.TextViewEmojis r102 = this.variantsButton;
            if (this.attributesTwoLines == false) goto L13;
            float r1 = 0.0f;
        L16:
            r102.setAlpha(r1);
            this.rays.setVisibility(8);
            this.rays.setAlpha(0.0f);
            this.buttonTitle.setText(LocaleController.getString(R.string.GiftCraftButton));
            this.buttonTitle.setTranslationY(0.0f);
            this.buttonSubtitle.setAlpha(1.0f);
            GiftAuctionController.getInstance(r7).requestAuctionUpgrades(r8, new StarGiftSheet$CraftTopView$$ExternalSyntheticLambda6(this, r8, r8));
            return;
        L13:
            if (this.previewAttributes == null) goto L15;
            r1 = 1.0f;
            goto L16
        L15:
            r1 = 0.25f;
            goto L16
        }

        public void showHint(View r12, CharSequence r13) {
            HintView2 r0 = this.currentHint;
            View r1 = null;
            if (r0 == null) goto L6;
            r0.hide();
            this.currentHint = null;
        L6:
            if (this.crafting == false) goto L8;
            return;
        L8:
            if (this.failed == false) goto L11;
            return;
        L11:
            if ((r12.getParent() instanceof View) == false) goto L14;
            r1 = (View) r12.getParent();
        L14:
            if (r1 == null) goto L16;
            float r2 = r1.getX();
        L17:
            float r22 = r2 + r12.getX();
            if (r1 == null) goto L20;
            float r14 = r1.getY();
        L21:
            float r15 = r14 + r12.getY();
            HintView2 r3 = new HintView2(getContext(), 3);
            this.currentHint = r3;
            r3.setMultilineText(true);
            this.currentHint.setText(r13);
            HintView2 r132 = this.currentHint;
            r132.setMaxWidthPx(HintView2.cutInFancyHalf(r132.getText(), this.currentHint.getTextPaint()));
            this.currentHint.setTextAlign(Layout.Alignment.ALIGN_CENTER);
            this.currentHint.setPadding(AndroidUtilities.dp(2.0f), 0, AndroidUtilities.dp(2.0f), 0);
            addView(this.currentHint, LayoutHelper.createFrame(-1, 100.0f, 55, 0.0f, 0.0f, 0.0f, 0.0f));
            this.currentHint.setTranslationY(r15 - AndroidUtilities.dp(100.0f));
            this.currentHint.setJointPx(0.0f, (r22 + (r12.getWidth() / 2.0f)) - AndroidUtilities.dp(2.0f));
            this.currentHint.show();
            return;
        L20:
            r14 = 0.0f;
            goto L21
        L16:
            r2 = 0.0f;
            goto L17
        }

        public void showHint(AttributeView r4) {
            if (r4.backdrop == null) goto L7;
            showHint(r4, AndroidUtilities.replaceTags(LocaleController.formatPluralString("GiftCraftBackdropChance", Math.round(r4.progress * 100.0f), new Object[]{r4.backdrop.name})));
            return;
        L7:
            if (r4.pattern == null) goto L10;
            showHint(r4, AndroidUtilities.replaceTags(LocaleController.formatPluralString("GiftCraftSymbolChance", Math.round(r4.progress * 100.0f), new Object[]{r4.pattern.name})));
            return;
        }

        public void updateCounts() {
            updateCounts(true);
        }

        public void updateCounts(boolean r17) {
            int r1 = getGiftsSelectedCount();
            this.frontFace.setChance(getGiftsSuccessChance() / 10.0f, r17);
            if (r1 <= 0) goto L5;
            this.buttonSubtitle.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GiftCraftSuccessChance, new Object[]{AffiliateProgramFragment.percents(getGiftsSuccessChance())})));
        L10:
            if (r1 != 0) goto L12;
            int r12 = 0;
        L15:
            ButtonBackground r7 = this.buttonBackground;
            int[] r8 = this.COLORS;
            int r13 = r12 * 2;
            int r10 = r13 + 1;
            r7.setColor(r8[r13], r8[r10]);
            SwitchGradientDrawable r72 = this.bg;
            int[] r82 = this.BACKGROUND_COLORS;
            r72.setColors(r82[r13], r82[r10]);
            RaysView r73 = this.rays;
            int[] r83 = this.COLORS;
            r73.setColor(r83[r10], r83[r13]);
            if (this.document != null) goto L18;
        L21:
            updateAttributeFreq();
            updateGiftButtonIcons();
            return;
        L18:
            if (getFirstGift() == null) goto L20;
            SpannableString r122 = new SpannableString("x");
            r122.setSpan(new AnimatedEmojiSpan(this.document, this.textView.getPaint().getFontMetricsInt()), 0, r122.length(), 33);
            this.textView.setText(TextUtils.concat(new CharSequence[]{AndroidUtilities.replaceTags(LocaleController.getString(R.string.GiftCraftText1)), "\n", r122, " ", AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GiftCraftText2, new Object[]{this.collectionTitle, LocaleController.formatNumber(r1.num, ',')}))}));
            goto L21
        L20:
            SpannableString r14 = new SpannableString("x");
            r14.setSpan(new AnimatedEmojiSpan(this.document, this.textView.getPaint().getFontMetricsInt()), 0, r14.length(), 33);
            this.textView.setText(TextUtils.concat(new CharSequence[]{AndroidUtilities.replaceTags(LocaleController.getString(R.string.GiftCraftTextEmpty1)), "\n", r14, " ", AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GiftCraftTextEmpty2, new Object[]{this.collectionTitle}))}));
            goto L21
        L12:
            if (r1 >= 4) goto L14;
            r12 = 1;
            goto L15
        L14:
            r12 = 2;
            goto L15
        L5:
            if (this.plus != null) goto L7;
            this.plus = new SpannableStringBuilder("+");
            ColoredImageSpan r4 = new ColoredImageSpan(R.drawable.filled_add_album);
            r4.setScale(0.65f, 0.65f);
            CharSequence r6 = this.plus;
            ((SpannableStringBuilder) r6).setSpan(r4, 0, r6.length(), 33);
        L7:
            SpannableStringBuilder r42 = new SpannableStringBuilder(LocaleController.getString(R.string.GiftCraftButtonEmpty));
            AndroidUtilities.replaceMultipleCharSequence("+", r42, this.plus);
            this.buttonSubtitle.setText(r42);
            goto L10
        }
    }

    public static class GiftThemeReuseTopView extends View {
        private final Drawable drawable;
        private final StarGiftDrawableIcon giftDrawable;
        private final ImageReceiver userImageReceiver;

        public GiftThemeReuseTopView(Context r4, TL_stars.StarGift r5, TLObject r6) {
            super(r4);
            StarGiftDrawableIcon r0 = new StarGiftDrawableIcon(this, r5, 60, 0.27f);
            this.giftDrawable = r0;
            r0.setPatternsType(3);
            AvatarDrawable r52 = new AvatarDrawable();
            r52.setInfo(r6);
            ImageReceiver r02 = new ImageReceiver(this);
            this.userImageReceiver = r02;
            r02.setRoundRadius(AndroidUtilities.dp(30.0f));
            r02.setForUserOrChat(r6, r52);
            Drawable r42 = r4.getDrawable(R.drawable.chats_undo).mutate();
            this.drawable = r42;
            r42.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2), PorterDuff.Mode.MULTIPLY));
            r42.setBounds(AndroidUtilities.dp(-12.0f), AndroidUtilities.dp(-12.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f));
        }

        @Override // android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.userImageReceiver.onAttachedToWindow();
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.userImageReceiver.onDetachedFromWindow();
        }

        @Override // android.view.View
        public void onDraw(Canvas r7) {
            int r1 = (getWidth() / 2) - (AndroidUtilities.dp(156.0f) / 2);
            int r0 = (getHeight() / 2) - AndroidUtilities.dp(30.0f);
            this.giftDrawable.setBounds(r1, r0, AndroidUtilities.dp(60.0f) + r1, AndroidUtilities.dp(60.0f) + r0);
            this.giftDrawable.draw(r7);
            r7.save();
            r7.translate(getWidth() / 2.0f, getHeight() / 2.0f);
            this.drawable.draw(r7);
            r7.restore();
            this.userImageReceiver.setImageCoords(r1 + AndroidUtilities.dp(96.0f), r0, AndroidUtilities.dp(60.0f), AndroidUtilities.dp(60.0f));
            this.userImageReceiver.draw(r7);
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(100.0f), TLObject.FLAG_30));
        }
    }

    public static class GiftTransferTopView extends View {
        private final Paint arrowPaint;
        private final Path arrowPath;
        private final StarGiftDrawableIcon giftDrawable;
        private final ImageReceiver userImageReceiver;

        public GiftTransferTopView(Context r5, TL_stars.StarGift r6) {
            super(r5);
            Path r52 = new Path();
            this.arrowPath = r52;
            Paint r0 = new Paint(1);
            this.arrowPaint = r0;
            StarGiftDrawableIcon r1 = new StarGiftDrawableIcon(this, r6, 60, 0.27f);
            this.giftDrawable = r1;
            r1.setPatternsType(3);
            ImageReceiver r62 = new ImageReceiver(this);
            this.userImageReceiver = r62;
            r62.setRoundRadius(AndroidUtilities.dp(30.0f));
            r62.setImageBitmap(SessionCell.createDrawable(60, "fragment"));
            r0.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText7));
            r0.setStyle(Paint.Style.STROKE);
            r0.setStrokeCap(Paint.Cap.ROUND);
            r0.setStrokeJoin(Paint.Join.ROUND);
            r0.setStrokeWidth(AndroidUtilities.dp(2.33f));
            r52.rewind();
            r52.moveTo(0.0f, -AndroidUtilities.dp(8.0f));
            r52.lineTo(AndroidUtilities.dp(6.166f), 0.0f);
            r52.lineTo(0.0f, AndroidUtilities.dp(8.0f));
        }

        public GiftTransferTopView(Context r5, TL_stars.StarGift r6, TLObject r7) {
            super(r5);
            Path r52 = new Path();
            this.arrowPath = r52;
            Paint r0 = new Paint(1);
            this.arrowPaint = r0;
            StarGiftDrawableIcon r1 = new StarGiftDrawableIcon(this, r6, 60, 0.27f);
            this.giftDrawable = r1;
            r1.setPatternsType(3);
            AvatarDrawable r62 = new AvatarDrawable();
            r62.setInfo(r7);
            ImageReceiver r12 = new ImageReceiver(this);
            this.userImageReceiver = r12;
            r12.setRoundRadius(AndroidUtilities.dp(30.0f));
            r12.setForUserOrChat(r7, r62);
            r0.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText7));
            r0.setStyle(Paint.Style.STROKE);
            r0.setStrokeCap(Paint.Cap.ROUND);
            r0.setStrokeJoin(Paint.Join.ROUND);
            r0.setStrokeWidth(AndroidUtilities.dp(2.0f));
            r52.rewind();
            r52.moveTo(0.0f, -AndroidUtilities.dp(8.0f));
            r52.lineTo(AndroidUtilities.dp(6.166f), 0.0f);
            r52.lineTo(0.0f, AndroidUtilities.dp(8.0f));
        }

        @Override // android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.userImageReceiver.onAttachedToWindow();
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.userImageReceiver.onDetachedFromWindow();
        }

        @Override // android.view.View
        public void onDraw(Canvas r7) {
            int r1 = (getWidth() / 2) - (AndroidUtilities.dp(156.0f) / 2);
            int r0 = (getHeight() / 2) - AndroidUtilities.dp(30.0f);
            this.giftDrawable.setBounds(r1, r0, AndroidUtilities.dp(60.0f) + r1, AndroidUtilities.dp(60.0f) + r0);
            this.giftDrawable.draw(r7);
            r7.save();
            r7.translate((getWidth() / 2.0f) - (AndroidUtilities.dp(6.166f) / 2.0f), getHeight() / 2.0f);
            r7.drawPath(this.arrowPath, this.arrowPaint);
            r7.restore();
            this.userImageReceiver.setImageCoords(r1 + AndroidUtilities.dp(96.0f), r0, AndroidUtilities.dp(60.0f), AndroidUtilities.dp(60.0f));
            this.userImageReceiver.draw(r7);
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(100.0f), TLObject.FLAG_30));
        }
    }

    public static class PageTransition {
        public int from;
        public float progress;
        public int to;

        public PageTransition(int r1, int r2, float r3) {
            this.from = r1;
            this.to = r2;
        }

        public float at(int r4) {
            int r0 = this.to;
            if (r0 == r4) goto L5;
        L7:
            if (r0 != r4) goto L11;
            return this.progress;
        L11:
            if (this.from == r4) goto L13;
            return 0.0f;
        L13:
            return 1.0f - this.progress;
        L5:
            if (this.from != r4) goto L7;
            return 1.0f;
        }

        public float at(int r2, int r3) {
            if (contains(r2) == false) goto L9;
            if (contains(r3) == false) goto L9;
            return 1.0f;
        L9:
            return Math.max(at(r2), at(r3));
        }

        public float at(int r2, int r3, int r4) {
            if (contains(r2) == false) goto L7;
            if (contains(r3) == false) goto L7;
            return 1.0f;
        L7:
            if (contains(r3) == false) goto L11;
            if (contains(r4) == false) goto L11;
            return 1.0f;
        L11:
            if (contains(r4) == false) goto L17;
            if (contains(r2) == false) goto L17;
            return 1.0f;
        L17:
            return Math.max(at(r2), Math.max(at(r3), at(r4)));
        }

        public boolean contains(int r2) {
            if (this.from != r2) goto L5;
            return true;
        L5:
            if (this.to == r2) goto L11;
            return false;
        L11:
            return true;
        }

        public boolean is(int r1) {
            if (this.to != r1) goto L6;
            return true;
        L6:
            return false;
        }

        public void setProgress(float r1) {
            this.progress = r1;
        }

        public boolean to(int r1) {
            if (this.to != r1) goto L6;
            return true;
        L6:
            return false;
        }
    }

    public static class PaymentFormState {
        public final AmountUtils$Amount amount;
        public final AmountUtils$Currency currency;
        public final TLRPC.TL_payments_paymentFormStarGift form;

        public PaymentFormState(AmountUtils$Currency r4, TLRPC.TL_payments_paymentFormStarGift r5) {
            this.currency = r4;
            this.form = r5;
            long r0 = StarsController.getFormStarsPrice(r5);
            AmountUtils$Currency r52 = AmountUtils$Currency.STARS;
            if (r4 != r52) goto L6;
            this.amount = AmountUtils$Amount.fromDecimal(r0, r52);
            return;
        L6:
            AmountUtils$Currency r2 = AmountUtils$Currency.TON;
            if (r4 != r2) goto L10;
            this.amount = AmountUtils$Amount.fromNano(r0, r2);
            return;
        L10:
            this.amount = AmountUtils$Amount.fromNano(0, r52);
        }
    }

    public static class ResaleBuyTransferAlert {
        public final AlertDialog alertDialog;
        private BalanceCloud balanceCloud;
        private final boolean canSwitchToTON;
        public final Context context;
        private final HorizontalRoundTabsLayout currencyTabsView;
        public final int currentAccount;
        public final long dialogId;
        private final HashMap<AmountUtils$Currency, PaymentFormState> forms;
        public final TL_stars.TL_starGiftUnique gift;
        private final String giftName;
        private Browser.Progress lastPositiveButtonProgress;
        private final HashSet<AmountUtils$Currency> loadingForms;
        private TextView positiveButton;
        private final Theme.ResourcesProvider resourcesProvider;
        private FrameLayout rootView;
        private AmountUtils$Currency selectedCurrency;
        private final TextView textInfoView;
        private HintView2 tonHint;

        /* renamed from: org.telegram.ui.Stars.StarGiftSheet$ResaleBuyTransferAlert$1, reason: invalid class name */
        public class AnonymousClass1 extends FrameLayout {

            /* renamed from: c, reason: collision with root package name */
            private final int[] f514c;
            final /* synthetic */ ResaleBuyTransferAlert this$0;

            public AnonymousClass1(ResaleBuyTransferAlert r1, Context r2) {
                this.this$0 = r1;
                super(r2);
                this.f514c = new int[2];
            }

            @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
            public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
                super.onLayout(r2, r3, r4, r5, r6);
                if (ResaleBuyTransferAlert.m19062$$Nest$fgetcurrencyTabsView(this.this$0) != null) goto L5;
                return;
            L5:
                if (ResaleBuyTransferAlert.m19062$$Nest$fgetcurrencyTabsView(this.this$0).linearLayout.getChildCount() >= 2) goto L7;
                return;
            L7:
                if (ResaleBuyTransferAlert.m19064$$Nest$fgettonHint(this.this$0) != null) goto L9;
                return;
            L9:
                if (ResaleBuyTransferAlert.m19063$$Nest$fgetrootView(this.this$0) == null) goto L15;
                ResaleBuyTransferAlert.m19063$$Nest$fgetrootView(this.this$0).getLocationInWindow(this.f514c);
                float r22 = this.f514c[0] - ResaleBuyTransferAlert.m19063$$Nest$fgetrootView(this.this$0).getTranslationX();
                float r42 = this.f514c[1] - ResaleBuyTransferAlert.m19063$$Nest$fgetrootView(this.this$0).getTranslationY();
                View r62 = ResaleBuyTransferAlert.m19062$$Nest$fgetcurrencyTabsView(this.this$0).linearLayout.getChildAt(1);
                r62.getLocationInWindow(this.f514c);
                float r32 = this.f514c[0] - r62.getTranslationX();
                ResaleBuyTransferAlert.m19064$$Nest$fgettonHint(this.this$0).setTranslationY((((this.f514c[1] - r62.getTranslationY()) - r42) - ResaleBuyTransferAlert.m19064$$Nest$fgettonHint(this.this$0).getMeasuredHeight()) - ResaleBuyTransferAlert.m19062$$Nest$fgetcurrencyTabsView(this.this$0).getMeasuredHeight());
                ResaleBuyTransferAlert.m19064$$Nest$fgettonHint(this.this$0).setJointPx(0.0f, ((r32 - r22) + (r62.getMeasuredWidth() / 2.0f)) - AndroidUtilities.dp(12.0f));
                return;
            }
        }

        /* renamed from: $r8$lambda$Ir3-yo6P7HWmUPIq9zYFIETuXUY, reason: not valid java name */
        public static /* synthetic */ void m19060$r8$lambda$Ir3yo6P7HWmUPIq9zYFIETuXUY(View r0) {
        }

        public static /* synthetic */ void $r8$lambda$LOV2QiXXD9CtDCYjrDIYFXlIlOk(ResaleBuyTransferAlert r0, AmountUtils$Currency r1, TLRPC.TL_payments_paymentFormStarGift r2) {
            r0.lambda$onUpdateCurrency$4(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$hdlvJ9RRUBKs06x07gI0LVn0Lrc(ResaleBuyTransferAlert r0, int r1) {
            r0.lambda$new$0(r1);
        }

        /* renamed from: $r8$lambda$s2Z0-R10XLvYZO7xtLLufPG0tQI, reason: not valid java name */
        public static /* synthetic */ void m19061$r8$lambda$s2Z0R10XLvYZO7xtLLufPG0tQI(ResaleBuyTransferAlert r0, View r1) {
            r0.lambda$onUpdateCurrency$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$tP1XVtpq9bbSONKCEQwZpPvwVRg(ResaleBuyTransferAlert r0, int r1, Context r2, Theme.ResourcesProvider r3, Utilities.Callback2 r4, AlertDialog r5, int r6) {
            r0.lambda$new$1(r1, r2, r3, r4, r5, r6);
        }

        /* renamed from: -$$Nest$fgetcurrencyTabsView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ HorizontalRoundTabsLayout m19062$$Nest$fgetcurrencyTabsView(ResaleBuyTransferAlert r0) {
            return r0.currencyTabsView;
        }

        /* renamed from: -$$Nest$fgetrootView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FrameLayout m19063$$Nest$fgetrootView(ResaleBuyTransferAlert r0) {
            return r0.rootView;
        }

        /* renamed from: -$$Nest$fgettonHint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ HintView2 m19064$$Nest$fgettonHint(ResaleBuyTransferAlert r0) {
            return r0.tonHint;
        }

        public ResaleBuyTransferAlert(Context r21, Theme.ResourcesProvider r22, TL_stars.TL_starGiftUnique r23, PaymentFormState r24, int r25, long r26, String r28, boolean r29, Utilities.Callback2<PaymentFormState, Browser.Progress> r30) {
            HashMap<AmountUtils$Currency, PaymentFormState> r7 = new HashMap();
            this.forms = r7;
            this.loadingForms = new HashSet();
            this.context = r21;
            this.gift = r23;
            this.dialogId = r26;
            this.currentAccount = r25;
            AmountUtils$Currency r9 = r24.currency;
            this.selectedCurrency = r9;
            r7.put(r9, r24);
            this.resourcesProvider = r22;
            this.giftName = r28;
            boolean r2 = r23.resale_ton_only;
            this.canSwitchToTON = !r2;
            if (r26 < 0) goto L5;
            TLObject r5 = MessagesController.getInstance(r25).getUser(Long.valueOf(r26));
        L6:
            LinearLayout r6 = new LinearLayout(r21);
            r6.setOrientation(1);
            AnonymousClass1 r92 = new AnonymousClass1(this, r21);
            r92.addView(r6, LayoutHelper.createFrame(-1, -2.0f));
            if (r2 == true) goto L9;
            HorizontalRoundTabsLayout r27 = new HorizontalRoundTabsLayout(r21, r22);
            this.currencyTabsView = r27;
            ArrayList<CharSequence> r11 = new ArrayList();
            r11.add(LocaleController.getString(R.string.Gift2BuyInStars));
            r11.add(LocaleController.getString(R.string.Gift2BuyInTON));
            r27.setTabs(r11, new StarGiftSheet$ResaleBuyTransferAlert$$ExternalSyntheticLambda0(this));
            r6.addView(r27, LayoutHelper.createLinear(-2, -2, 1, 18, 0, 18, 12));
        L10:
            r6.addView(new GiftTransferTopView(r21, r23, r5), LayoutHelper.createLinear(-1, -2, 48, 0, -4, 0, 0));
            TextView r210 = new TextView(r21);
            this.textInfoView = r210;
            r210.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r22));
            r210.setTextSize(1, 16.0f);
            r6.addView(r210, LayoutHelper.createLinear(-1, -2, 48, 24, 4, 24, 4));
            if (r29 == false) goto L18;
            TableView r211 = new TableView(r21, r22);
            StarGiftSheet.addAttributeRow(r211, StarsController.findAttribute(r23.attributes, TL_stars.starGiftAttributeModel.class));
            StarGiftSheet.addAttributeRow(r211, StarsController.findAttribute(r23.attributes, TL_stars.starGiftAttributeBackdrop.class));
            StarGiftSheet.addAttributeRow(r211, StarsController.findAttribute(r23.attributes, TL_stars.starGiftAttributePattern.class));
            if (TextUtils.isEmpty(r23.slug) == false) goto L15;
        L17:
            r6.addView(r211, LayoutHelper.createLinear(-1, -2, 48, 23, 16, 23, 4));
            goto L18
        L15:
            if ((r23.flags & 256) == 0) goto L17;
            String r0 = BillingController.getInstance().formatCurrency(r23.value_amount, r23.value_currency, BillingController.getInstance().getCurrencyExp(r23.value_currency), true);
            r211.addRow(LocaleController.getString(R.string.GiftValue2), "~" + r0);
        L18:
            this.alertDialog = new AlertDialog.Builder(r21, r22).setView(r92).setPositiveButton("_", new StarGiftSheet$ResaleBuyTransferAlert$$ExternalSyntheticLambda1(this, r25, r21, r22, r30)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create();
            return;
        L9:
            this.currencyTabsView = null;
            TextView r212 = new TextView(r21);
            r212.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, r22));
            r212.setTextSize(1, 14.0f);
            r212.setText(LocaleController.getString(R.string.Gift2BuyPriceOnlyTON));
            r212.setGravity(17);
            r6.addView(r212, LayoutHelper.createLinear(-2, -2, 17, 24, 4, 24, 4));
            goto L10
        L5:
            r5 = MessagesController.getInstance(r25).getChat(Long.valueOf(-r26));
            goto L6
        }

        private /* synthetic */ void lambda$new$0(int r1) {
            if (r1 != 0) goto L4;
            AmountUtils$Currency r12 = AmountUtils$Currency.STARS;
        L5:
            this.selectedCurrency = r12;
            onUpdateCurrency(true);
            return;
        L4:
            r12 = AmountUtils$Currency.TON;
            goto L5
        }

        private /* synthetic */ void lambda$new$1(int r12, Context r13, Theme.ResourcesProvider r14, Utilities.Callback2 r15, AlertDialog r16, int r17) {
            PaymentFormState r0 = this.forms.get(this.selectedCurrency);
            if (r0 == null) goto L26;
            StarsController r122 = StarsController.getInstance(r12, this.selectedCurrency);
            if (r122.balanceAvailable() == false) goto L8;
            AmountUtils$Amount r123 = AmountUtils$Amount.of(r122.getBalance());
        L9:
            if (r123 != null) goto L11;
        L20:
            Browser.Progress r124 = this.lastPositiveButtonProgress;
            if (r124 == null) goto L23;
            r124.cancel();
            this.lastPositiveButtonProgress = null;
        L23:
            r15.run(r0, r16.makeButtonLoading(r17));
            return;
        L11:
            if (r0.amount.asNano() <= r123.asNano()) goto L20;
            AmountUtils$Currency r11 = this.selectedCurrency;
            if (r11 != AmountUtils$Currency.STARS) goto L17;
            new StarsIntroActivity.StarsNeededSheet(r13, r14, r0.amount.asDecimal(), 14, null, null, 0).show();
            return;
        L17:
            if (r11 != AmountUtils$Currency.TON) goto L25;
            new TONIntroActivity.StarsNeededSheet(r13, r14, r0.amount, true, null).show();
            return;
        L25:
            return;
        L8:
            r123 = null;
            goto L9
        }

        private /* synthetic */ void lambda$onUpdateCurrency$2(View r2) {
            new StarsIntroActivity.StarsOptionsSheet(this.context, this.resourcesProvider).show();
        }

        private /* synthetic */ void lambda$onUpdateCurrency$4(AmountUtils$Currency r3, TLRPC.TL_payments_paymentFormStarGift r4) {
            Browser.Progress r0 = this.lastPositiveButtonProgress;
            if (r0 != null) goto L5;
        L7:
            this.loadingForms.remove(r3);
            if (r4 == null) goto L11;
            this.forms.put(r3, new PaymentFormState(r3, r4));
            onUpdateCurrency(true);
            return;
        L11:
            return;
        L5:
            if (r3 != this.selectedCurrency) goto L7;
            r0.end();
            goto L7
        }

        private void onUpdateCurrency(boolean r10) {
            AmountUtils$Currency r0 = this.selectedCurrency;
            PaymentFormState r1 = this.forms.get(r0);
            ViewPropertyAnimator r2 = this.textInfoView.animate();
            if (r1 == null) goto L5;
            float r3 = 1.0f;
        L6:
            r2.alpha(r3).start();
            TextView r22 = this.textInfoView;
            boolean r4 = false;
            if (r1 == null) goto L9;
            boolean r5 = true;
        L10:
            r22.setEnabled(r5);
            TextView r23 = this.positiveButton;
            if (r1 == null) goto L13;
            boolean r52 = true;
        L14:
            r23.setEnabled(r52);
            this.balanceCloud.setCurrency(r0, r10);
            HorizontalRoundTabsLayout r24 = this.currencyTabsView;
            if (r24 != null) goto L17;
        L21:
            AmountUtils$Currency r102 = AmountUtils$Currency.TON;
            if (r0 != r102) goto L28;
            HintView2 r25 = this.tonHint;
            if (r25 == null) goto L28;
            if (r25.shown() == false) goto L28;
            this.tonHint.hide();
        L28:
            BalanceCloud r26 = this.balanceCloud;
            if (r26 != null) goto L31;
        L34:
            Browser.Progress r27 = this.lastPositiveButtonProgress;
            if (r27 == null) goto L37;
            r27.cancel();
            this.lastPositiveButtonProgress = null;
        L37:
            if (r1 != null) goto L39;
            Browser.Progress r103 = this.alertDialog.makeButtonLoading(-1, false, false);
            this.lastPositiveButtonProgress = r103;
            r103.init();
            if (this.loadingForms.add(r0) == false) goto L61;
            StarsController.getInstance(this.currentAccount, r0).getResellingGiftForm(this.gift, this.dialogId, new StarGiftSheet$ResaleBuyTransferAlert$$ExternalSyntheticLambda4(this, r0));
            return;
        L61:
            return;
        L39:
            if (this.dialogId != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L42;
            r4 = true;
        L42:
            if (r1.currency != AmountUtils$Currency.STARS) goto L49;
            this.positiveButton.setText(StarsIntroActivity.replaceStars(LocaleController.formatPluralStringComma("Gift2BuyDoPrice2", (int) r1.amount.asDecimal())));
            TextView r02 = this.textInfoView;
            AmountUtils$Amount r28 = r1.amount;
            if (r4 == false) goto L46;
            String r29 = LocaleController.formatPluralStringComma("Gift2BuyPriceSelfText", (int) r28.asDecimal(), new Object[]{this.giftName});
        L47:
            r02.setText(AndroidUtilities.replaceTags(r29));
            goto L49
        L46:
            r29 = LocaleController.formatPluralStringComma("Gift2BuyPriceText", (int) r28.asDecimal(), new Object[]{this.giftName, DialogObject.getShortName(this.dialogId)});
        L49:
            if (r1.currency != r102) goto L60;
            this.positiveButton.setText(StarsIntroActivity.replaceStars(true, LocaleController.formatString(R.string.Gift2BuyDoPrice2TON, new Object[]{r1.amount.asFormatString()})));
            TextView r104 = this.textInfoView;
            AmountUtils$Amount r03 = r1.amount;
            if (r4 == false) goto L53;
            String r9 = LocaleController.formatString(R.string.Gift2BuyPriceSelfTextTON, new Object[]{r03.asFormatString(), this.giftName});
        L54:
            r104.setText(AndroidUtilities.replaceTags(r9));
            return;
        L53:
            r9 = LocaleController.formatString(R.string.Gift2BuyPriceTextTON, new Object[]{r03.asFormatString(), this.giftName, DialogObject.getShortName(this.dialogId)});
            goto L54
        L60:
            return;
        L31:
            if (r0 != AmountUtils$Currency.STARS) goto L33;
            r26.setOnClickListener(new StarGiftSheet$ResaleBuyTransferAlert$$ExternalSyntheticLambda2(this));
            goto L34
        L33:
            r26.setOnClickListener(new StarGiftSheet$ResaleBuyTransferAlert$$ExternalSyntheticLambda3());
            goto L34
        L17:
            if (r0 != AmountUtils$Currency.TON) goto L19;
            int r53 = 1;
        L20:
            r24.setSelectedIndex(r53, r10);
            goto L21
        L19:
            r53 = 0;
            goto L20
        L13:
            r52 = false;
            goto L14
        L9:
            r5 = false;
            goto L10
        L5:
            r3 = 0.25f;
            goto L6
        }

        public void show() {
            this.alertDialog.setShowStarsBalance(true).show();
            this.positiveButton = (TextView) this.alertDialog.getButton(-1);
            this.balanceCloud = this.alertDialog.getStarsBalanceCloud();
            FrameLayout r0 = this.alertDialog.getFullscreenContainerView();
            this.rootView = r0;
            if (r0 != null) goto L5;
        L7:
            onUpdateCurrency(false);
            return;
        L5:
            if (this.canSwitchToTON == false) goto L7;
            HintView2 r02 = new HintView2(this.context, 3).setMultilineText(true).setTextAlign(Layout.Alignment.ALIGN_NORMAL).setDuration(5000).setText(LocaleController.getString(R.string.Gift2BuyPricePayHintTON)).show();
            this.tonHint = r02;
            r02.setPadding(AndroidUtilities.dp(7.33f), 0, AndroidUtilities.dp(7.33f), 0);
            this.rootView.addView(this.tonHint, LayoutHelper.createFrame(-2, 100.0f, 48, 0.0f, 26.0f, 0.0f, 0.0f));
            goto L7
        }
    }

    public static class Roller {
        private AttrRoller<Background> backdropRoller;
        private AttrRoller<Background> backdropRoller2;
        TextViewRoll backdropText;
        private final ArrayList<Background> backgrounds;
        private boolean drawing;
        private float durationT;
        private long lastFrameTime;
        private AttrRoller<Sticker> modelRoller;
        TextViewRoll modelText;
        private final ArrayList<Sticker> models;
        TextViewRoll patternText;
        private boolean posted;
        private float realTime;
        private boolean rolling;
        private TL_stars.TL_starGiftUnique rollingGift;
        private boolean sentDone;
        private boolean sentDone2;
        private AttrRoller<Symbol> symbolRoller;
        private final ArrayList<Symbol> symbols;
        public final TopView topView;
        private Runnable whenDone;
        private Runnable whenDone2;

        /* renamed from: org.telegram.ui.Stars.StarGiftSheet$Roller$1, reason: invalid class name */
        public class AnonymousClass1 implements View.OnAttachStateChangeListener {
            final /* synthetic */ Roller this$0;

            public AnonymousClass1(Roller r1) {
                this.this$0 = r1;
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View r3) {
                ArrayList r2 = Roller.m19067$$Nest$fgetmodels(this.this$0);
                int r32 = r2.size();
                int r0 = 0;
            L3:
                if (r0 >= r32) goto L5;
                Object r1 = r2.get(r0);
                r0 = r0 + 1;
                ((Sticker) r1).attach();
                goto L3
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View r3) {
                ArrayList r2 = Roller.m19067$$Nest$fgetmodels(this.this$0);
                int r32 = r2.size();
                int r0 = 0;
            L3:
                if (r0 >= r32) goto L5;
                Object r1 = r2.get(r0);
                r0 = r0 + 1;
                ((Sticker) r1).detach();
                goto L3
            }
        }

        public static class Attr {
            public String name;
            public int rarity_permille;

            public Attr() {
            }

            public void detach() {
            }

            public boolean isLoaded() {
                return true;
            }
        }

        public static class AttrRoller<T extends Attr> {
            public final ArrayList<T> attributes;
            public T current;
            public int currentT;
            private final AnimatedFloat fast;
            public final T finish;
            private final Runnable invalidate;
            private int lastNextIndex;
            public T next;
            public T prev;
            private int slowing;
            private final float speedMult;
            public final T start;
            public float time;
            private final int totalSlowing;

            public AttrRoller(Runnable r2, ArrayList<T> r3, T r4, T r5, float r6, int r7) {
                this.time = 0.0f;
                this.lastNextIndex = -1;
                this.invalidate = r2;
                this.attributes = r3;
                this.start = r4;
                this.finish = r5;
                this.speedMult = r6;
                this.totalSlowing = r7;
                AnimatedFloat r32 = new AnimatedFloat(r2, 300, CubicBezierInterpolator.EASE_OUT_QUINT);
                this.fast = r32;
                r32.force(true);
                this.time = -0.5f;
                this.currentT = 1;
                this.slowing = r7;
                this.prev = r4;
                this.current = (T) next(false);
                this.next = (T) next(false);
            }

            public void detach() {
                T r0 = this.start;
                if (r0 == null) goto L5;
                r0.detach();
            L5:
                T r1 = this.finish;
                if (r1 == null) goto L9;
                r1.detach();
                return;
            }

            public boolean isAlmostFinished() {
                return isAlmostFinished(0.25f);
            }

            public boolean isAlmostFinished(float r3) {
                if (this.current == this.finish) goto L5;
                return false;
            L5:
                if ((this.time + r3) < (this.currentT + 0.5f)) goto L10;
                return true;
            L10:
                return false;
            }

            public boolean isFinished() {
                if (this.current == this.finish) goto L5;
                return false;
            L5:
                if (this.time < (this.currentT + 0.5f)) goto L10;
                return true;
            L10:
                return false;
            }

            public T next(boolean r4) {
                if (r4 == true) goto L4;
            L10:
                ArrayList r42 = new ArrayList();
                int r0 = 0;
                int r1 = 0;
            L12:
                if (r1 >= this.attributes.size()) goto L20;
                if (r1 == this.lastNextIndex) goto L18;
                if (this.attributes.get(r1).isLoaded() == false) goto L18;
                r42.add(Integer.valueOf(r1));
            L18:
                r1 = r1 + 1;
                goto L12
            L20:
                if (r42.isEmpty() == true) goto L22;
            L31:
                int r43 = ((Integer) AndroidUtilities.randomOf(r42)).intValue();
                this.lastNextIndex = r43;
                return this.attributes.get(r43);
            L22:
                if (r0 >= this.attributes.size()) goto L28;
                if (this.attributes.get(r0).isLoaded() == false) goto L26;
                r42.add(Integer.valueOf(r0));
            L26:
                r0 = r0 + 1;
                goto L22
            L28:
                if (r42.isEmpty() == false) goto L31;
                return this.start;
            L4:
                if (this.finish.isLoaded() == false) goto L10;
                int r44 = this.slowing;
                if (r44 <= 0) goto L8;
                this.slowing = r44 - 1;
                goto L10
            L8:
                return this.finish;
            }

            public void skip() {
                this.prev = this.current;
                this.current = this.finish;
                this.next = null;
                int r0 = this.currentT + 1;
                this.currentT = r0;
                this.time = r0 + 0.5f;
            }

            public float step(float r10, boolean r11) {
                AnimatedFloat r0 = this.fast;
                int r1 = this.slowing;
                int r2 = this.totalSlowing;
                if (r1 < r2) goto L5;
                long r12 = 450;
            L9:
                r0.setDuration(r12);
                AnimatedFloat r02 = this.fast;
                if (this.slowing < this.totalSlowing) goto L12;
                boolean r13 = true;
            L13:
                float r03 = r02.set(r13);
                if (this.totalSlowing != 3) goto L16;
                float r14 = 0.75f;
            L17:
                float r15 = this.time + (r10 * (AndroidUtilities.lerp(r14, 7.5f, r03) * this.speedMult));
                this.time = r15;
                if (r15 < 0.0f) goto L29;
                double r22 = r15;
                if ((Math.floor(r22) + 1.0d) <= this.currentT) goto L29;
                T r102 = this.current;
                T r04 = this.finish;
                if (r102 == r04) goto L29;
                this.prev = r102;
                T r103 = this.next;
                this.current = r103;
                if (r103 != r04) goto L26;
                T r104 = null;
            L27:
                this.next = r104;
                this.currentT = ((int) Math.floor(r22)) + 1;
                goto L29
            L26:
                r104 = (T) next(r11);
            L29:
                if (this.current == this.finish) goto L31;
                return r15;
            L31:
                return Math.min(r15, this.currentT + 0.5f);
            L16:
                r14 = 2.0f;
                goto L17
            L12:
                r13 = false;
                goto L13
            L5:
                if (r2 != 3) goto L7;
                int r16 = 4500;
            L8:
                r12 = r16;
                goto L9
            L7:
                r16 = 2500;
                goto L8
            }
        }

        public static class Background extends Attr {
            public final int backgroundColor;
            public final RadialGradient backgroundGradient;
            public final Matrix backgroundMatrix;
            public final Paint backgroundPaint;
            public final int patternColor;
            public final int textColor;

            public Background(TL_stars.starGiftAttributeBackdrop r11) {
                this.name = r11.name;
                this.rarity_permille = r11.getRarityPermille();
                Paint r0 = new Paint(1);
                this.backgroundPaint = r0;
                this.backgroundMatrix = new Matrix();
                RadialGradient r2 = new RadialGradient(0.0f, 0.0f, AndroidUtilities.dp(200.0f), new int[]{r11.center_color | (-16777216), r11.edge_color | (-16777216)}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
                this.backgroundGradient = r2;
                r0.setShader(r2);
                this.textColor = r11.text_color | (-16777216);
                int r02 = r11.pattern_color;
                this.patternColor = r02 | (-16777216);
                this.backgroundColor = ColorUtils.blendARGB(r11.edge_color | (-16777216), r02 | (-16777216), 0.25f);
            }
        }

        public static class Sticker extends Attr {
            public final ImageReceiver imageReceiver;
            public final boolean mine;

            public Sticker(View r2, TL_stars.starGiftAttributeModel r3) {
                this.name = r3.name;
                this.rarity_permille = r3.getRarityPermille();
                this.mine = true;
                ImageReceiver r0 = new ImageReceiver(r2);
                this.imageReceiver = r0;
                StarsIntroActivity.setGiftImage(r0, r3.document, 160);
            }

            public Sticker(BackupImageView r2, TL_stars.starGiftAttributeModel r3) {
                this.name = r3.name;
                this.rarity_permille = r3.getRarityPermille();
                this.mine = false;
                this.imageReceiver = r2.getImageReceiver();
            }

            public void attach() {
                if (this.mine == false) goto L6;
                this.imageReceiver.onAttachedToWindow();
                return;
            }

            @Override // org.telegram.ui.Stars.StarGiftSheet.Roller.Attr
            public void detach() {
                if (this.mine == false) goto L6;
                this.imageReceiver.onDetachedFromWindow();
                return;
            }

            @Override // org.telegram.ui.Stars.StarGiftSheet.Roller.Attr
            public boolean isLoaded() {
                if (this.imageReceiver.getLottieAnimation() == null) goto L6;
                return true;
            L6:
                return false;
            }
        }

        public static class Symbol extends Attr {
            public final TL_stars.starGiftAttributePattern attr;

            public Symbol(TL_stars.starGiftAttributePattern r2) {
                this.name = r2.name;
                this.rarity_permille = r2.getRarityPermille();
                this.attr = r2;
            }
        }

        public static /* synthetic */ void $r8$lambda$N4awmFhHBJ_NlQdWi9VSEhpZ8cA(Roller r0) {
            r0.lambda$update$0();
        }

        /* renamed from: $r8$lambda$us0U7Q-tGiJzIKEDvaatDCH0gKc, reason: not valid java name */
        public static /* synthetic */ void m19065$r8$lambda$us0U7QtGiJzIKEDvaatDCH0gKc(Roller r0) {
            r0.lambda$update$1();
        }

        /* renamed from: -$$Nest$fgetmodelRoller, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AttrRoller m19066$$Nest$fgetmodelRoller(Roller r0) {
            return r0.modelRoller;
        }

        /* renamed from: -$$Nest$fgetmodels, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m19067$$Nest$fgetmodels(Roller r0) {
            return r0.models;
        }

        /* renamed from: -$$Nest$fgetrollingGift, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TL_stars.TL_starGiftUnique m19068$$Nest$fgetrollingGift(Roller r0) {
            return r0.rollingGift;
        }

        public Roller(TopView r2) {
            this.models = new ArrayList();
            this.backgrounds = new ArrayList();
            this.symbols = new ArrayList();
            this.realTime = 0.0f;
            this.rolling = false;
            this.sentDone = false;
            this.sentDone2 = false;
            this.topView = r2;
            TopView.m19082$$Nest$fgetimagesRollView(r2).addOnAttachStateChangeListener(new AnonymousClass1(this));
        }

        private /* synthetic */ void lambda$update$0() {
            this.rolling = false;
            TopView.m19082$$Nest$fgetimagesRollView(this.topView).resetDrawing();
            Runnable r1 = this.whenDone;
            if (r1 == null) goto L6;
            r1.run();
            return;
        }

        private /* synthetic */ void lambda$update$1() {
            Runnable r0 = this.whenDone2;
            if (r0 == null) goto L6;
            r0.run();
            return;
        }

        public void detach() {
            this.rolling = false;
            TopView.m19082$$Nest$fgetimagesRollView(this.topView).resetDrawing();
            AttrRoller<Sticker> r0 = this.modelRoller;
            if (r0 == null) goto L5;
            r0.detach();
        L5:
            AttrRoller<Symbol> r02 = this.symbolRoller;
            if (r02 == null) goto L8;
            r02.detach();
        L8:
            AttrRoller<Background> r03 = this.backdropRoller;
            if (r03 == null) goto L11;
            r03.detach();
        L11:
            AttrRoller<Background> r04 = this.backdropRoller2;
            if (r04 == null) goto L14;
            r04.detach();
        L14:
            stopPreload();
        }

        public void invalidate() {
            if (this.rolling == true) goto L6;
            return;
        L6:
            if (this.posted == true) goto L9;
            this.posted = true;
            AndroidUtilities.runOnUIThread(new StarGiftSheet$Roller$$ExternalSyntheticLambda1(this));
            return;
        }

        public boolean isRolling() {
            return this.rolling;
        }

        public void preload(ArrayList<TL_stars.StarGiftAttribute> r8) {
            ArrayList<Sticker> r0 = this.models;
            int r1 = r0.size();
            int r2 = 0;
            int r3 = 0;
        L3:
            if (r3 >= r1) goto L5;
            Sticker r4 = r0.get(r3);
            r3 = r3 + 1;
            r4.detach();
            goto L3
        L5:
            this.models.clear();
            this.backgrounds.clear();
            this.symbols.clear();
            ArrayList r02 = StarsController.findAttributes(r8, TL_stars.starGiftAttributeModel.class);
            int r12 = r02.size();
            int r32 = 0;
        L6:
            if (r32 >= r12) goto L11;
            Object r42 = r02.get(r32);
            r32 = r32 + 1;
            Sticker r5 = new Sticker(TopView.m19082$$Nest$fgetimagesRollView(this.topView), (TL_stars.starGiftAttributeModel) r42);
            if (this.topView.isAttachedToWindow() == false) goto L10;
            r5.attach();
        L10:
            this.models.add(r5);
            goto L6
        L11:
            ArrayList r03 = StarsController.findAttributes(r8, TL_stars.starGiftAttributeBackdrop.class);
            int r13 = r03.size();
            int r33 = 0;
        L12:
            if (r33 >= r13) goto L14;
            Object r43 = r03.get(r33);
            r33 = r33 + 1;
            this.backgrounds.add(new Background((TL_stars.starGiftAttributeBackdrop) r43));
            goto L12
        L14:
            ArrayList r82 = StarsController.findAttributes(r8, TL_stars.starGiftAttributePattern.class);
            int r04 = r82.size();
        L15:
            if (r2 >= r04) goto L17;
            Object r14 = r82.get(r2);
            r2 = r2 + 1;
            this.symbols.add(new Symbol((TL_stars.starGiftAttributePattern) r14));
            goto L15
        }

        public boolean set(TL_stars.TL_starGiftUnique r19, boolean r20, Runnable r21, Runnable r22) {
            if (r19 != null) goto L5;
            boolean r3 = false;
        L6:
            TL_stars.TL_starGiftUnique r4 = this.rollingGift;
            if (r4 != null) goto L9;
        L12:
            if (r3 == true) goto L14;
            return false;
        L14:
            BackupImageView r32 = this.topView.getUpgradeImageView();
            TL_stars.starGiftAttributeModel r42 = this.topView.getUpgradeImageViewAttribute();
            TL_stars.starGiftAttributePattern r5 = this.topView.getUpgradePatternAttribute();
            TL_stars.starGiftAttributeBackdrop r6 = this.topView.getUpgradeBackdropAttribute();
            TL_stars.starGiftAttributeModel r7 = (TL_stars.starGiftAttributeModel) StarsController.findAttribute(r19.attributes, TL_stars.starGiftAttributeModel.class);
            TL_stars.starGiftAttributePattern r8 = (TL_stars.starGiftAttributePattern) StarsController.findAttribute(r19.attributes, TL_stars.starGiftAttributePattern.class);
            TL_stars.starGiftAttributeBackdrop r9 = (TL_stars.starGiftAttributeBackdrop) StarsController.findAttribute(r19.attributes, TL_stars.starGiftAttributeBackdrop.class);
            this.rolling = true;
            this.rollingGift = r19;
            this.whenDone = r21;
            this.whenDone2 = r22;
            this.durationT = (float) Math.random();
            this.lastFrameTime = System.currentTimeMillis();
            this.realTime = 0.0f;
            this.sentDone = false;
            this.sentDone2 = false;
            this.rolling = true;
            AttrRoller<Sticker> r1 = this.modelRoller;
            if (r1 == null) goto L17;
            r1.detach();
        L17:
            Sticker r15 = new Sticker(TopView.m19082$$Nest$fgetimagesRollView(this.topView), r7);
            if (TopView.m19082$$Nest$fgetimagesRollView(this.topView).isAttachedToWindow() == false) goto L20;
            r15.attach();
        L20:
            StarGiftSheet$Roller$$ExternalSyntheticLambda0 r12 = new StarGiftSheet$Roller$$ExternalSyntheticLambda0(this);
            ArrayList<Sticker> r13 = this.models;
            Sticker r14 = new Sticker(r32, r42);
            if (this.durationT <= 0.5f) goto L23;
            int r17 = 3;
        L24:
            this.modelRoller = new AttrRoller(r12, r13, r14, r15, 0.9f, r17);
            AttrRoller<Symbol> r16 = this.symbolRoller;
            if (r16 == null) goto L27;
            r16.detach();
        L27:
            StarGiftSheet$Roller$$ExternalSyntheticLambda0 r122 = new StarGiftSheet$Roller$$ExternalSyntheticLambda0(this);
            ArrayList<Symbol> r132 = this.symbols;
            Symbol r142 = new Symbol(r5);
            Symbol r152 = new Symbol(r8);
            if (this.durationT <= 0.5f) goto L30;
            int r172 = 2;
        L31:
            this.symbolRoller = new AttrRoller(r122, r132, r142, r152, 1.0f, r172);
            AttrRoller<Background> r18 = this.backdropRoller;
            if (r18 == null) goto L34;
            r18.detach();
        L34:
            StarGiftSheet$Roller$$ExternalSyntheticLambda0 r123 = new StarGiftSheet$Roller$$ExternalSyntheticLambda0(this);
            ArrayList<Background> r133 = this.backgrounds;
            Background r143 = new Background(r6);
            Background r153 = new Background(r9);
            if (this.durationT <= 0.5f) goto L37;
            int r173 = 2;
        L38:
            this.backdropRoller = new AttrRoller(r123, r133, r143, r153, 0.5f, r173);
            AttrRoller<Background> r110 = this.backdropRoller2;
            if (r110 == null) goto L41;
            r110.detach();
        L41:
            StarGiftSheet$Roller$$ExternalSyntheticLambda0 r124 = new StarGiftSheet$Roller$$ExternalSyntheticLambda0(this);
            ArrayList<Background> r134 = this.backgrounds;
            Background r144 = new Background(r6);
            Background r154 = new Background(r9);
            if (this.durationT <= 0.5f) goto L44;
            int r174 = 2;
        L45:
            this.backdropRoller2 = new AttrRoller(r124, r134, r144, r154, 1.25f, r174);
            invalidate();
            return true;
        L44:
            r174 = 1;
            goto L45
        L37:
            r173 = 1;
            goto L38
        L30:
            r172 = 1;
            goto L31
        L23:
            r17 = 2;
            goto L24
        L9:
            if (r4.id != r19.id) goto L12;
            return this.rolling;
        L5:
            r3 = r20;
            goto L6
        }

        public void skip() {
            this.modelRoller.skip();
            this.symbolRoller.skip();
            this.backdropRoller.skip();
            this.backdropRoller2.skip();
        }

        public void stopPreload() {
            if (this.rolling == false) goto L5;
            return;
        L5:
            ArrayList<Sticker> r0 = this.models;
            int r1 = r0.size();
            int r2 = 0;
        L6:
            if (r2 >= r1) goto L8;
            Sticker r3 = r0.get(r2);
            r2 = r2 + 1;
            r3.detach();
            goto L6
        L8:
            this.models.clear();
            this.backgrounds.clear();
            this.symbols.clear();
        }

        public void update() {
            if (this.drawing == true) goto L115;
            this.posted = false;
            if (this.rolling == true) goto L8;
            return;
        L8:
            this.drawing = true;
            long r3 = System.currentTimeMillis();
            float r5 = Math.min((r3 - this.lastFrameTime) / 1000.0f, 0.25f);
            float r6 = this.realTime + r5;
            this.realTime = r6;
            AttrRoller<Background> r7 = this.backdropRoller;
            if (r6 <= AndroidUtilities.lerp(0.1f, 1.0f, this.durationT)) goto L11;
            boolean r62 = true;
        L12:
            float r63 = r7.step(r5, r62);
            AttrRoller<Background> r72 = this.backdropRoller2;
            if (this.realTime <= AndroidUtilities.lerp(0.1f, 1.0f, this.durationT)) goto L15;
            boolean r8 = true;
        L16:
            float r73 = r72.step(r5, r8);
            float r82 = this.symbolRoller.step(r5, this.backdropRoller.isAlmostFinished(0.5f));
            AttrRoller<Sticker> r9 = this.modelRoller;
            if (this.backdropRoller.isAlmostFinished(0.5f) == true) goto L19;
        L21:
            boolean r11 = false;
        L22:
            float r52 = r9.step(r5, r11);
            this.lastFrameTime = r3;
            if (this.backdropRoller.isFinished() == false) goto L32;
            if (this.symbolRoller.isFinished() == false) goto L32;
            if (this.modelRoller.isFinished() == false) goto L32;
            if (this.sentDone == true) goto L32;
            this.sentDone = true;
            AndroidUtilities.runOnUIThread(new StarGiftSheet$Roller$$ExternalSyntheticLambda2(this));
        L32:
            if (this.backdropRoller.isFinished() == true) goto L34;
        L40:
            TextViewRoll r112 = this.modelText;
            if (r112 == null) goto L56;
            AttrRoller<Sticker> r32 = this.modelRoller;
            Attr r12 = r32.prev;
            int r4 = r32.currentT;
            float r13 = (r4 - r52) - 1.0f;
            Attr r92 = r32.finish;
            if (r12 != r92) goto L45;
            boolean r14 = true;
        L46:
            Attr r15 = r32.current;
            float r21 = 1.0f;
            float r16 = r4 - r52;
            if (r15 != r92) goto L49;
            boolean r17 = true;
        L50:
            Attr r33 = r32.next;
            float r19 = (r4 - r52) + 1.0f;
            if (r33 != r92) goto L54;
            boolean r20 = true;
        L55:
            r112.update(r12, r13, r14, r15, r16, r17, r33, r19, r20);
        L57:
            TextViewRoll r34 = this.patternText;
            if (r34 == null) goto L73;
            AttrRoller<Symbol> r42 = this.symbolRoller;
            Attr r93 = r42.prev;
            int r10 = r42.currentT;
            float r24 = (r10 - r82) - r21;
            Attr r113 = r42.finish;
            if (r93 != r113) goto L62;
            boolean r25 = true;
        L63:
            Attr r122 = r42.current;
            float r27 = r10 - r82;
            if (r122 != r113) goto L66;
            boolean r28 = true;
        L67:
            Attr r43 = r42.next;
            float r30 = (r10 - r82) + r21;
            if (r43 != r113) goto L71;
            boolean r31 = true;
        L72:
            r34.update(r93, r24, r25, r122, r27, r28, r43, r30, r31);
            goto L73
        L71:
            r31 = false;
            goto L72
        L66:
            r28 = false;
            goto L67
        L62:
            r25 = false;
        L73:
            TextViewRoll r35 = this.backdropText;
            if (r35 == null) goto L89;
            AttrRoller<Background> r44 = this.backdropRoller2;
            Attr r83 = r44.prev;
            int r94 = r44.currentT;
            float r332 = (r94 - r73) - r21;
            Attr r102 = r44.finish;
            if (r83 != r102) goto L78;
            boolean r342 = true;
        L79:
            Attr r114 = r44.current;
            float r36 = r94 - r73;
            if (r114 != r102) goto L82;
            boolean r37 = true;
        L83:
            Attr r45 = r44.next;
            float r39 = (r94 - r73) + r21;
            if (r45 != r102) goto L87;
            boolean r40 = true;
        L88:
            r35.update(r83, r332, r342, r114, r36, r37, r45, r39, r40);
            goto L89
        L87:
            r40 = false;
            goto L88
        L82:
            r37 = false;
            goto L83
        L78:
            r342 = false;
        L89:
            this.topView.setPattern(0, ((Symbol) this.symbolRoller.current).attr, true);
            StickersRollView r22 = TopView.m19082$$Nest$fgetimagesRollView(this.topView);
            AttrRoller<Sticker> r38 = this.modelRoller;
            T r46 = r38.prev;
            Sticker r23 = (Sticker) r46;
            int r74 = r38.currentT;
            float r242 = (r74 - r52) - r21;
            T r84 = r38.finish;
            if (r46 != r84) goto L92;
            boolean r252 = true;
        L93:
            T r47 = r38.current;
            Sticker r26 = (Sticker) r47;
            float r272 = r74 - r52;
            if (r47 != r84) goto L96;
            boolean r282 = true;
        L97:
            T r310 = r38.next;
            Sticker r29 = (Sticker) r310;
            float r302 = (r74 - r52) + r21;
            if (r310 != r84) goto L100;
            boolean r312 = true;
        L101:
            AttrRoller<Background> r311 = this.backdropRoller;
            T r48 = r311.prev;
            Background r322 = (Background) r48;
            int r53 = r311.currentT;
            float r333 = (r53 - r63) - r21;
            T r75 = r311.finish;
            if (r48 != r75) goto L104;
            boolean r343 = true;
        L105:
            T r49 = r311.current;
            Background r352 = (Background) r49;
            float r362 = r53 - r63;
            if (r49 != r75) goto L108;
            boolean r372 = true;
        L109:
            T r313 = r311.next;
            Background r382 = (Background) r313;
            float r392 = (r53 - r63) + r21;
            if (r313 != r75) goto L112;
            boolean r402 = true;
        L113:
            r22.setDrawing(r23, r242, r252, r26, r272, r282, r29, r302, r312, r322, r333, r343, r352, r362, r372, r382, r392, r402);
            this.drawing = false;
            invalidate();
            return;
        L112:
            r402 = false;
            goto L113
        L108:
            r372 = false;
            goto L109
        L104:
            r343 = false;
            goto L105
        L100:
            r312 = false;
            goto L101
        L96:
            r282 = false;
            goto L97
        L92:
            r252 = false;
            goto L93
        L54:
            r20 = false;
            goto L55
        L49:
            r17 = false;
            goto L50
        L45:
            r14 = false;
            goto L46
        L56:
            r21 = 1.0f;
            goto L57
        L34:
            if (this.symbolRoller.isFinished() == false) goto L40;
            if (this.modelRoller.isAlmostFinished() == false) goto L40;
            if (this.sentDone2 == true) goto L40;
            this.sentDone2 = true;
            AndroidUtilities.runOnUIThread(new StarGiftSheet$Roller$$ExternalSyntheticLambda3(this));
            goto L40
        L19:
            if (this.symbolRoller.isAlmostFinished(0.5f) == false) goto L21;
            r11 = true;
            goto L22
        L15:
            r8 = false;
            goto L16
        L11:
            r62 = false;
            goto L12
        }
    }

    public static final class RoundRectStrokeDrawable extends Drawable {
        private final Paint paint;
        private float radius;

        public RoundRectStrokeDrawable(float r3, int r4) {
            Paint r0 = new Paint(1);
            this.paint = r0;
            this.radius = r3;
            r0.setColor(r4);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r4) {
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(getBounds());
            float r1 = this.radius;
            r4.drawRoundRect(r0, r1, r1, this.paint);
            AndroidUtilities.drawStroke(r4, r0, this.radius);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
        }

        public void setColor(int r1) {
            this.paint.setColor(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    public static class StarGiftDrawableIcon extends CompatDrawable {
        private final Paint countdownPaint;
        private AnimatedTextView.AnimatedTextDrawable countdownText;
        private CountdownTimer countdownTimer;
        private int endTime;
        private Text giftName;
        private Text giftStatus;
        private RadialGradient gradient;
        private final ImageReceiver imageReceiver;
        private final Matrix matrix;
        private StarsReactionsSheet.Particles particles;
        private final Path path;
        private final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable pattern;
        private float patternsScale;
        private int patternsType;
        private final RectF rect;
        private int rounding;
        private final int sizeDp;
        private final TL_stars.StarGift starGift;
        private int startTime;
        private final View view;

        /* renamed from: org.telegram.ui.Stars.StarGiftSheet$StarGiftDrawableIcon$1, reason: invalid class name */
        public class AnonymousClass1 implements Drawable.Callback {
            final /* synthetic */ StarGiftDrawableIcon this$0;

            public AnonymousClass1(StarGiftDrawableIcon r1) {
                this.this$0 = r1;
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void invalidateDrawable(Drawable r1) {
                StarGiftDrawableIcon.m19070$$Nest$fgetview(this.this$0).invalidate();
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void scheduleDrawable(Drawable r1, Runnable r2, long r3) {
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void unscheduleDrawable(Drawable r1, Runnable r2) {
            }
        }

        /* renamed from: $r8$lambda$4ru1cbNZlrWe_-1w6GE89c7ZtNc, reason: not valid java name */
        public static /* synthetic */ void m19069$r8$lambda$4ru1cbNZlrWe_1w6GE89c7ZtNc(StarGiftDrawableIcon r0, long r1) {
            r0.lambda$setCountdownRemainingTime$0(r1);
        }

        /* renamed from: -$$Nest$fgetview, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m19070$$Nest$fgetview(StarGiftDrawableIcon r0) {
            return r0.view;
        }

        public StarGiftDrawableIcon(View r19, TL_stars.StarGift r20, int r21, float r22) {
            super(r19);
            this.path = new Path();
            this.rect = new RectF();
            this.matrix = new Matrix();
            this.countdownPaint = new Paint(1);
            this.rounding = AndroidUtilities.dp(16.0f);
            this.patternsType = 0;
            this.starGift = r20;
            this.view = r19;
            this.patternsScale = r22;
            ImageReceiver r7 = new ImageReceiver(r19);
            this.imageReceiver = r7;
            if (r21 <= 180) goto L5;
            float r9 = 24.0f;
        L6:
            AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r8 = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(r19, false, AndroidUtilities.dp(r9));
            this.pattern = r8;
            this.sizeDp = r21;
            if ((r20 instanceof TL_stars.TL_starGift) == false) goto L17;
            float r92 = r21;
            StarsIntroActivity.setGiftImage(r7, r20.sticker, (int) (0.75f * r92));
            String r82 = r20.title;
            if (r82 != null) goto L12;
            r82 = "Gift";
        L12:
            Text r72 = new Text(r82, 16.0f, AndroidUtilities.bold());
            this.giftName = r72;
            r72.setColor(-1);
            float r3 = r21 - 30;
            this.giftName.setMaxWidth(AndroidUtilities.dp(r3));
            Text r4 = this.giftName;
            Layout.Alignment r73 = Layout.Alignment.ALIGN_CENTER;
            r4.align(r73);
            this.giftName.multiline(1);
            if (r20.sold_out == false) goto L15;
            String r2 = LocaleController.getString(R.string.Gift2SoldOutTitle);
        L16:
            Text r42 = new Text(r2, 13.0f);
            this.giftStatus = r42;
            r42.setMaxWidth(AndroidUtilities.dp(r3));
            this.giftStatus.align(r73);
            this.giftStatus.multiline(1);
            StarsReactionsSheet.Particles r23 = new StarsReactionsSheet.Particles(1, 40);
            this.particles = r23;
            float r32 = 0.45f * r92;
            r23.setBounds(-AndroidUtilities.dp(r32), -AndroidUtilities.dp(r32), AndroidUtilities.dp(r32), AndroidUtilities.dp(r92 * 0.25f));
            this.particles.generateGrid();
        L25:
            this.paint.setShader(this.gradient);
            if (r19.isAttachedToWindow() == false) goto L29;
            onAttachedToWindow();
            return;
        L29:
            return;
        L15:
            r2 = LocaleController.formatPluralString("Gift2SoldAuctionPreviewGifts", r20.availability_total, new Object[0]);
            goto L16
        L17:
            if (r20 == null) goto L25;
            TL_stars.starGiftAttributeBackdrop r43 = (TL_stars.starGiftAttributeBackdrop) StarsController.findAttribute(r20.attributes, TL_stars.starGiftAttributeBackdrop.class);
            TL_stars.starGiftAttributePattern r5 = (TL_stars.starGiftAttributePattern) StarsController.findAttribute(r20.attributes, TL_stars.starGiftAttributePattern.class);
            TL_stars.starGiftAttributeModel r24 = (TL_stars.starGiftAttributeModel) StarsController.findAttribute(r20.attributes, TL_stars.starGiftAttributeModel.class);
            if (r5 == null) goto L21;
            r8.set(r5.document, false);
        L21:
            if (r43 == null) goto L23;
            this.gradient = new RadialGradient(0.0f, 0.0f, AndroidUtilities.dpf2(r21) / 2.0f, new int[]{r43.center_color | (-16777216), r43.edge_color | (-16777216)}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
            r8.setColor(Integer.valueOf(r43.pattern_color | (-16777216)));
        L23:
            if (r24 == null) goto L25;
            StarsIntroActivity.setGiftImage(r7, r24.document, (int) (r21 * 0.75f));
            goto L25
        L5:
            r9 = 18.0f;
            goto L6
        }

        private /* synthetic */ void lambda$setCountdownRemainingTime$0(long r2) {
            updateCountdownText(r2, true);
        }

        private void updateCountdownText(long r3, boolean r5) {
            int r32 = ConnectionsManager.getInstance(UserConfig.selectedAccount).getCurrentTime();
            int r4 = this.endTime;
            if (r32 <= r4) goto L5;
            this.countdownText.setText(LocaleController.getString(R.string.Gift2AuctionCountdownFinished));
        L10:
            if (r32 <= this.endTime) goto L15;
            Text r2 = this.giftStatus;
            if (r2 == null) goto L16;
            r2.setText(LocaleController.getString(R.string.Gift2SoldOutTitle));
            return;
        L16:
            return;
        L15:
            return;
        L5:
            int r52 = this.startTime;
            AnimatedTextView.AnimatedTextDrawable r0 = this.countdownText;
            if (r32 >= r52) goto L8;
            r0.setText(LocaleController.formatString(R.string.Gift2AuctionCountdownStartsIn, new Object[]{AndroidUtilities.formatDuration(r52 - r32, true)}));
            goto L10
        L8:
            r0.setText(AndroidUtilities.formatDuration(r4 - r32, true));
            goto L10
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r11) {
            this.rect.set(getBounds());
            r11.save();
            this.path.rewind();
            Path r2 = this.path;
            RectF r3 = this.rect;
            int r4 = this.rounding;
            r2.addRoundRect(r3, r4, r4, Path.Direction.CW);
            r11.clipPath(this.path);
            if (this.gradient == null) goto L5;
            this.matrix.reset();
            this.matrix.postTranslate(this.rect.centerX(), this.rect.centerY());
            this.gradient.setLocalMatrix(this.matrix);
            this.paint.setShader(this.gradient);
        L5:
            r11.drawPaint(this.paint);
            r11.save();
            r11.translate(this.rect.centerX(), this.rect.centerY());
            StarGiftPatterns.drawPattern(r11, this.patternsType, this.pattern, this.rect.width(), this.rect.height(), 1.0f, this.patternsScale);
            StarsReactionsSheet.Particles r22 = this.particles;
            if (r22 == null) goto L8;
            r22.draw(r11, -1, 1.0f);
        L8:
            r11.restore();
            if (this.giftName != null) goto L11;
        L16:
            float r23 = Math.min(this.rect.width(), this.rect.height()) * 0.75f;
            float r5 = r23 / 2.0f;
            this.imageReceiver.setImageCoords(this.rect.centerX() - r5, this.rect.centerY() - r5, r23, r23);
            this.imageReceiver.draw(r11);
        L17:
            r11.restore();
            return;
        L11:
            if (this.giftStatus == null) goto L16;
            if (this.countdownText == null) goto L15;
            this.countdownPaint.setColor(1342177280);
            r11.drawRoundRect(this.rect.left + AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f) + this.rect.top, (this.rect.left + AndroidUtilities.dp(20.0f)) + Math.max(this.countdownText.getCurrentWidth(), AndroidUtilities.dp(3.0f)), this.rect.top + AndroidUtilities.dp(23.0f), AndroidUtilities.dp(8.5f), AndroidUtilities.dp(8.5f), this.countdownPaint);
            r11.save();
            r11.translate(this.rect.left + AndroidUtilities.dp(13.0f), this.rect.top + AndroidUtilities.dp(14.0f));
            this.countdownText.draw(r11);
            r11.restore();
        L15:
            float r24 = Math.min(this.rect.width(), this.rect.height()) * 0.6f;
            ImageReceiver r32 = this.imageReceiver;
            float r42 = this.rect.centerX() - (r24 / 2.0f);
            RectF r52 = this.rect;
            r32.setImageCoords(r42, r52.top + (r52.height() * 0.12f), r24, r24);
            this.imageReceiver.draw(r11);
            this.giftName.draw(r11, this.rect.centerX() - (this.giftName.getWidth() / 2.0f), this.rect.bottom - AndroidUtilities.dp(50.0f));
            this.giftStatus.draw(r11, this.rect.centerX() - (this.giftStatus.getWidth() / 2.0f), this.rect.bottom - AndroidUtilities.dp(30.0f));
            goto L17
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return AndroidUtilities.dp(this.sizeDp);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return AndroidUtilities.dp(this.sizeDp);
        }

        @Override // org.telegram.ui.Components.CompatDrawable
        public void onAttachedToWindow() {
            this.pattern.attach();
            this.imageReceiver.onAttachedToWindow();
            if (this.countdownTimer == null) goto L10;
            int r0 = ConnectionsManager.getInstance(UserConfig.selectedAccount).getCurrentTime();
            int r1 = this.startTime;
            if (r0 < r1) goto L8;
            r1 = this.endTime;
        L8:
            this.countdownTimer.start(r1 - r0);
            return;
        }

        @Override // org.telegram.ui.Components.CompatDrawable
        public void onDetachedToWindow() {
            this.pattern.detach();
            this.imageReceiver.onDetachedFromWindow();
            CountdownTimer r1 = this.countdownTimer;
            if (r1 == null) goto L6;
            r1.stop();
            return;
        }

        public void setAuctionStateTextColor(int r2) {
            Text r1 = this.giftStatus;
            if (r1 == null) goto L6;
            r1.setColor(r2 | (-16777216));
            return;
        }

        public void setCountdownRemainingTime(int r3, int r4) {
            this.startTime = r3;
            this.endTime = r4;
            if (this.countdownTimer != null) goto L5;
            this.countdownTimer = new CountdownTimer(new StarGiftSheet$StarGiftDrawableIcon$$ExternalSyntheticLambda0(this));
        L5:
            int r0 = ConnectionsManager.getInstance(UserConfig.selectedAccount).getCurrentTime();
            if (r0 >= r3) goto L8;
            int r32 = r3 - r0;
        L9:
            long r02 = r32;
            this.countdownTimer.start(r02);
            if (this.countdownText != null) goto L12;
            AnimatedTextView.AnimatedTextDrawable r33 = new AnimatedTextView.AnimatedTextDrawable();
            this.countdownText = r33;
            r33.setTextColor(-1);
            this.countdownText.setTextSize(AndroidUtilities.dp(12.0f));
            this.countdownText.setCallback(new AnonymousClass1(this));
        L12:
            updateCountdownText(r02, false);
            return;
        L8:
            r32 = r4 - r0;
            goto L9
        }

        public void setGradient(int r8, int r9) {
            RadialGradient r0 = new RadialGradient(0.0f, 0.0f, AndroidUtilities.dpf2(this.sizeDp) / 2.0f, new int[]{r8 | (-16777216), r9 | (-16777216)}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
            this.gradient = r0;
            this.paint.setShader(r0);
        }

        public StarGiftDrawableIcon setPatternsType(int r1) {
            this.patternsType = r1;
            return this;
        }

        public StarGiftDrawableIcon setRounding(int r1) {
            this.rounding = r1;
            return this;
        }
    }

    public static class StickersRollView extends View {

        /* renamed from: a, reason: collision with root package name */
        private Roller.Sticker f515a;
        private boolean aIsFinish;
        private float aT;

        /* renamed from: b, reason: collision with root package name */
        private Roller.Sticker f516b;
        private boolean bIsFinish;
        private float bT;
        private Roller.Background bgA;
        private boolean bgAIsFinish;
        private float bgAT;
        private Roller.Background bgB;
        private boolean bgBIsFinish;
        private float bgBT;
        private Roller.Background bgC;
        private boolean bgCIsFinish;
        private float bgCT;

        /* renamed from: c, reason: collision with root package name */
        private Roller.Sticker f517c;
        private boolean cIsFinish;
        private float cT;
        private final Camera camera;
        private final GradientClip clip;
        private int lastBlurRx;
        private final RectF rect;
        private final Theme.ResourcesProvider resourcesProvider;

        public StickersRollView(Context r1, Theme.ResourcesProvider r2) {
            super(r1);
            this.lastBlurRx = 0;
            this.camera = new Camera();
            this.clip = new GradientClip();
            this.rect = new RectF();
            this.resourcesProvider = r2;
        }

        private void drawBackground(Canvas r17, Roller.Background r18, float r19, float r20, float r21, int[] r22, int[] r23, int[] r24) {
            if (r18 != null) goto L5;
            return;
        L5:
            if (r18.backgroundPaint == null) goto L49;
            float r1 = (r19 - 0.5f) / 1.5f;
            float r13 = Utilities.clamp01(1.0f - Math.abs(r1));
            float r14 = Math.max(0.8f * r20, AndroidUtilities.dp(180.0f));
            float r15 = (r20 / 2.0f) - ((r1 * r14) * 1.8f);
            float r12 = Math.min(AndroidUtilities.dp(176.0f), r21) / 2.0f;
            float r2 = r15 - r14;
            float r4 = r15 + r14;
            r17.saveLayerAlpha(r2, 0.0f, r4, r21, 255, 31);
            r18.backgroundMatrix.reset();
            r18.backgroundMatrix.postTranslate(r15, r12);
            r18.backgroundGradient.setLocalMatrix(r18.backgroundMatrix);
            r18.backgroundPaint.setAlpha((int) (255.0f * r13));
            r17.drawRect(r2, 0.0f, r4, r21, r18.backgroundPaint);
            r17.save();
            float r3 = AndroidUtilities.dp(90.0f);
            this.rect.set(r2, 0.0f, r2 + r3, r21);
            int r152 = 0;
            this.clip.draw(r17, this.rect, 0, 1.0f);
            this.rect.set(r4 - r3, 0.0f, r4, r21);
            this.clip.draw(r17, this.rect, 2, 1.0f);
            r17.restore();
            r17.restore();
            int r16 = 0;
        L9:
            if (r16 >= r22.length) goto L18;
            float r32 = r16 * (getWidth() / (r22.length - 1));
            if (r32 >= r2) goto L13;
        L16:
            float r33 = 0.0f;
        L17:
            r22[r16] = Theme.blendOver(r22[r16], Theme.multAlpha(r18.textColor, r33 * r13));
            r16 = r16 + 1;
            goto L9
        L13:
            if (r32 > r4) goto L16;
            r33 = Math.min(Utilities.clamp01((r32 - r2) / r14), Utilities.clamp01(1.0f - ((r32 - (r4 - r14)) / r14)));
            goto L17
        L18:
            int r110 = 0;
        L20:
            if (r110 >= r23.length) goto L30;
            float r34 = r110 * (getWidth() / (r23.length - 1));
            if (r34 >= r2) goto L24;
        L27:
            float r35 = 0.0f;
        L28:
            r23[r110] = Theme.blendOver(r23[r110], Theme.multAlpha(r18.backgroundColor, r35 * r13));
            r110 = r110 + 1;
            goto L20
        L24:
            if (r34 > r4) goto L27;
            r35 = Math.min(Utilities.clamp01((r34 - r2) / r14), Utilities.clamp01(1.0f - ((r34 - (r4 - r14)) / r14)));
        L30:
            if (r152 >= r24.length) goto L50;
            float r111 = r152 * (getWidth() / (r23.length - 1));
            if (r111 < r2) goto L37;
            if (r111 > r4) goto L37;
            float r112 = Math.min(Utilities.clamp01((r111 - r2) / r14), Utilities.clamp01(1.0f - ((r111 - (r4 - r14)) / r14)));
        L38:
            r24[r152] = Theme.blendOver(r24[r152], Theme.multAlpha(r18.patternColor, r112 * r13));
            r152 = r152 + 1;
        L37:
            r112 = 0.0f;
            goto L38
        L50:
            return;
        }

        private void drawSticker(Canvas r18, Roller.Sticker r19, float r20, boolean r21) {
            if (r19 != null) goto L5;
            return;
        L5:
            float r4 = r20;
            if (r21 == false) goto L8;
            r4 = Math.max(0.5f, r4);
        L8:
            float r5 = r19.imageReceiver.getImageX();
            float r6 = r19.imageReceiver.getImageY();
            float r7 = r19.imageReceiver.getImageWidth();
            float r8 = r19.imageReceiver.getImageHeight();
            float r9 = r19.imageReceiver.getAlpha();
            float r42 = (r4 - 0.5f) / 1.5f;
            float r3 = Utilities.clamp01(1.0f - Math.abs(r42));
            float r11 = (getWidth() / 2.0f) - (AndroidUtilities.dp(220.0f) * r42);
            float r13 = AndroidUtilities.dp(80.0f);
            float r10 = AndroidUtilities.lerp(0.85f, 1.0f, r3);
            float r14 = AndroidUtilities.dp(160.0f);
            r18.save();
            float r15 = ((r14 / 2.0f) * r42) + r11;
            r18.translate(r15, r13);
            this.camera.save();
            this.camera.rotateY(r42 * (-30.0f));
            this.camera.applyToCanvas(r18);
            this.camera.restore();
            r18.translate(-r15, -r13);
            float r142 = r14 * r10;
            float r43 = r142 / 2.0f;
            r19.imageReceiver.setImageCoords(r11 - r43, r13 - r43, r142, r142);
            r19.imageReceiver.setAlpha(r3);
            r19.imageReceiver.draw(r18);
            r19.imageReceiver.setImageCoords(r5, r6, r7, r8);
            r19.imageReceiver.setAlpha(r9);
            r18.restore();
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r4) {
            drawSticker(r4, this.f515a, this.aT, this.aIsFinish);
            drawSticker(r4, this.f516b, this.bT, this.bIsFinish);
            drawSticker(r4, this.f517c, this.cT, this.cIsFinish);
        }

        public void drawBackgrounds(Canvas r10, float r11, float r12, int[] r13, int[] r14, int[] r15) {
            drawBackground(r10, this.bgA, this.bgAT, r11, r12, r13, r14, r15);
            drawBackground(r10, this.bgB, this.bgBT, r11, r12, r13, r14, r15);
            drawBackground(r10, this.bgC, this.bgCT, r11, r12, r13, r14, r15);
        }

        public boolean hasBackgrounds() {
            if (this.bgA == null) goto L5;
            return true;
        L5:
            if (this.bgB == null) goto L7;
            return true;
        L7:
            if (this.bgC != null) goto L14;
            return false;
        L14:
            return true;
        }

        public void resetDrawing() {
            if (this.f515a == null) goto L5;
        L16:
            boolean r0 = true;
        L17:
            this.f517c = null;
            this.f516b = null;
            this.f515a = null;
            this.cT = 0.0f;
            this.bT = 0.0f;
            this.aT = 0.0f;
            this.cIsFinish = false;
            this.bIsFinish = false;
            this.aIsFinish = false;
            this.bgC = null;
            this.bgB = null;
            this.bgA = null;
            this.bgCT = 0.0f;
            this.bgBT = 0.0f;
            this.bgAT = 0.0f;
            this.bgCIsFinish = false;
            this.bgBIsFinish = false;
            this.bgAIsFinish = false;
            if (r0 == false) goto L21;
            invalidate();
            return;
        L21:
            return;
        L5:
            if (this.f516b != null) goto L16;
            if (this.f517c != null) goto L16;
            if (this.bgA != null) goto L16;
            if (this.bgB != null) goto L16;
            if (this.bgC != null) goto L16;
            r0 = false;
            goto L17
        }

        public void setDrawing(Roller.Sticker r1, float r2, boolean r3, Roller.Sticker r4, float r5, boolean r6, Roller.Sticker r7, float r8, boolean r9, Roller.Background r10, float r11, boolean r12, Roller.Background r13, float r14, boolean r15, Roller.Background r16, float r17, boolean r18) {
            this.f515a = r1;
            this.f516b = r4;
            this.f517c = r7;
            this.aT = r2;
            this.bT = r5;
            this.cT = r8;
            this.aIsFinish = r3;
            this.bIsFinish = r6;
            this.cIsFinish = r9;
            this.bgA = r10;
            this.bgB = r13;
            this.bgC = r16;
            this.bgAT = r11;
            this.bgBT = r14;
            this.bgCT = r17;
            this.bgAIsFinish = r12;
            this.bgBIsFinish = r15;
            this.bgCIsFinish = r18;
            invalidate();
        }
    }

    public static class TextViewRoll extends FrameLayout {
        private boolean bounced;
        private final GradientClip clip;
        private final TextView current;
        private final TextView next;
        private final TextView prev;
        private final RectF rect;
        private final Theme.ResourcesProvider resourcesProvider;
        private final Utilities.Callback3<CharSequence, View, Boolean> showHint;

        public static class TextView extends ButtonSpan.TextViewButtons {
            private String lastName;
            private int lastRarity;
            private final Theme.ResourcesProvider resourcesProvider;

            /* renamed from: $r8$lambda$M4AKLAd-_wYKtJGkv_4jrIES2Ac, reason: not valid java name */
            public static /* synthetic */ void m19072$r8$lambda$M4AKLAd_wYKtJGkv_4jrIES2Ac(TextView r0, Utilities.Callback3 r1, int r2) {
                r0.lambda$set$0(r1, r2);
            }

            public TextView(Context r2, Theme.ResourcesProvider r3) {
                super(r2);
                this.resourcesProvider = r3;
                setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r3));
                setTextSize(1, 14.0f);
                setPadding(0, AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f));
            }

            private /* synthetic */ void lambda$set$0(Utilities.Callback3 r2, int r3) {
                r2.run(LocaleController.formatString(R.string.Gift2RarityHint, new Object[]{AffiliateProgramFragment.percents(r3)}), this, Boolean.FALSE);
            }

            public void set(String r5, int r6, Utilities.Callback3<CharSequence, View, Boolean> r7) {
                if (r5 == this.lastName) goto L5;
            L7:
                SpannableStringBuilder r0 = new SpannableStringBuilder(Emoji.replaceEmoji(r5, getPaint().getFontMetricsInt(), false));
                SpannableStringBuilder r1 = r0.append(" ");
                CharSequence r2 = AffiliateProgramFragment.percents(r6);
                if (r7 == null) goto L10;
                StarGiftSheet$TextViewRoll$TextView$$ExternalSyntheticLambda0 r3 = new StarGiftSheet$TextViewRoll$TextView$$ExternalSyntheticLambda0(this, r7, r6);
            L11:
                r1.append(ButtonSpan.make(r2, r3, this.resourcesProvider));
                setText(r0);
                this.lastName = r5;
                this.lastRarity = r6;
                return;
            L10:
                r3 = null;
                goto L11
            L5:
                if (this.lastRarity != r6) goto L7;
            }
        }

        /* renamed from: $r8$lambda$iWC1ta3hFA-7tVuFzO3Fxhu3ze8, reason: not valid java name */
        public static /* synthetic */ void m19071$r8$lambda$iWC1ta3hFA7tVuFzO3Fxhu3ze8(View r4, ValueAnimator r5) {
            float r52 = (((float) Math.sin(((Float) r5.getAnimatedValue()).floatValue() * 3.141592653589793d)) * 0.03f) + 1.0f;
            r4.setScaleX(r52);
            r4.setScaleY(r52);
        }

        public TextViewRoll(Context r10, Theme.ResourcesProvider r11, Utilities.Callback3<CharSequence, View, Boolean> r12) {
            super(r10);
            this.clip = new GradientClip();
            this.rect = new RectF();
            this.showHint = r12;
            this.resourcesProvider = r11;
            TextView r122 = new TextView(r10, r11);
            this.prev = r122;
            TextView r0 = new TextView(r10, r11);
            this.current = r0;
            TextView r1 = new TextView(r10, r11);
            this.next = r1;
            addView(r122, LayoutHelper.createFrame(-2, -2.0f, 51, 12.66f, 5.33f, 12.66f, 5.33f));
            addView(r0, LayoutHelper.createFrame(-2, -2.0f, 51, 12.66f, 5.33f, 12.66f, 5.33f));
            addView(r1, LayoutHelper.createFrame(-2, -2.0f, 51, 12.66f, 5.33f, 12.66f, 5.33f));
        }

        private void bounce(View r3) {
            if (this.bounced == true) goto L9;
            if (r3 != null) goto L7;
            return;
        L7:
            this.bounced = true;
            ValueAnimator r2 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            r2.addUpdateListener(new StarGiftSheet$TextViewRoll$$ExternalSyntheticLambda0(r3));
            r2.setDuration(180);
            r2.start();
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r9) {
            r9.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
            super.dispatchDraw(r9);
            r9.save();
            this.rect.set(0.0f, 0.0f, getWidth(), AndroidUtilities.dp(8.0f));
            this.clip.draw(r9, this.rect, 1, 1.0f);
            this.rect.set(0.0f, getHeight() - AndroidUtilities.dp(8.0f), getWidth(), getHeight());
            this.clip.draw(r9, this.rect, 3, 1.0f);
            r9.restore();
            r9.restore();
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(37.66f), TLObject.FLAG_30));
        }

        public void update(Roller.Attr r11, float r12, boolean r13, Roller.Attr r14, float r15, boolean r16, Roller.Attr r17, float r18, boolean r19) {
            if (r11 == null) goto L7;
            if (r13 == false) goto L6;
            r12 = Math.max(0.5f, r12);
        L6:
            this.prev.setVisibility(0);
            this.prev.set(r11.name, r11.rarity_permille, this.showHint);
            this.prev.setTranslationY(AndroidUtilities.dp(36.0f) * ((r12 - 0.5f) / 1.5f));
        L8:
            if (r14 == null) goto L17;
            if (r16 == false) goto L11;
            float r112 = Math.max(0.5f, r15);
        L12:
            float r113 = (r112 - 0.5f) / 1.5f;
            this.current.setVisibility(0);
            this.current.set(r14.name, r14.rarity_permille, this.showHint);
            this.current.setTranslationY(AndroidUtilities.dp(36.0f) * r113);
            if (r16 == true) goto L15;
        L18:
            if (r17 == null) goto L24;
            float r114 = r18;
            if (r19 == false) goto L22;
            r114 = Math.max(0.5f, r114);
        L22:
            this.next.setVisibility(0);
            this.next.set(r17.name, r17.rarity_permille, this.showHint);
            this.next.setTranslationY(AndroidUtilities.dp(36.0f) * ((r114 - 0.5f) / 1.5f));
            return;
        L24:
            this.next.setVisibility(4);
            return;
        L15:
            if (r113 > 0.0f) goto L18;
            bounce(this.current);
            goto L18
        L11:
            r112 = r15;
            goto L12
        L17:
            this.current.setVisibility(4);
            goto L18
        L7:
            this.prev.setVisibility(4);
            goto L8
        }
    }

    public static class TopView extends FrameLayout {
        private boolean attached;
        private BackupImageView avatarView;
        protected final TL_stars.starGiftAttributeBackdrop[] backdrop;
        private BagRandomizer<TL_stars.starGiftAttributeBackdrop> backdrops;
        protected final int[] backgroundColors;
        private final RadialGradient[] backgroundGradient;
        private final Matrix[] backgroundMatrix;
        private final Paint[] backgroundPaint;
        public final Button[] buttons;
        private final LinearLayout buttonsLayout;
        private final Runnable checkToRotateRunnable;
        private final ImageView closeView;
        private final TextView collectionReleasedView;
        private int collectionReleasedViewColor;
        private CraftTopView craftTopView;
        private final ImageView craftView;
        private int currentImageIndex;
        private PageTransition currentPage;
        private boolean hasLink;
        private boolean hasResellPrice;
        private boolean hasRibbon;
        public final FrameLayout imageLayout;
        private final BackupImageView[] imageView;
        private final TL_stars.starGiftAttributeModel[] imageViewAttributes;
        private final StickersRollView imagesRollView;
        private final LinearLayout[] layout;
        private final FrameLayout.LayoutParams[] layoutLayoutParams;
        private TextPaint messageTextPaint;
        private final GiftMessageView[] messageTextView;
        private BagRandomizer<TL_stars.starGiftAttributeModel> models;
        private View.OnClickListener onResellClick;
        private View.OnClickListener onShareClick;
        private View.OnClickListener onUpdatePriceClick;
        public final ImageView optionsView;
        private StarsReactionsSheet.Particles particles;
        private final RectF particlesBounds;
        private final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable[] pattern;
        private final TL_stars.starGiftAttributePattern[] patternAttribute;
        private final int[] patternColors;
        private BagRandomizer<TL_stars.starGiftAttributePattern> patterns;
        private RadialGradient profileBackgroundGradient;
        private final Matrix profileBackgroundMatrix;
        private Paint profileBackgroundPaint;
        private final LinkSpanDrawable.LinksTextView releasedView;
        private final TextView resellPriceView;
        private boolean resellPriceViewInProgress;
        private final Theme.ResourcesProvider resourcesProvider;
        private final GiftSheet.Ribbon ribbon;
        private ValueAnimator rotationAnimator;
        private ArrayList<TL_stars.StarGiftAttribute> sampleAttributes;
        private final FrameLayout subtitleContainer;
        private final LinkSpanDrawable.LinksTextView[] subtitleView;
        private final LinearLayout.LayoutParams[] subtitleViewLayoutParams;
        private ValueAnimator switchAnimator;
        private float switchScale;
        private final int[] textColors;
        private final LinkSpanDrawable.LinksTextView[] titleView;
        private float toggleBackdrop;
        private int toggled;
        private FrameLayout userLayout;
        private float wearImageScale;
        private float wearImageTx;
        private float wearImageTy;
        private TLObject wearPreviewObject;

        /* renamed from: org.telegram.ui.Stars.StarGiftSheet$TopView$1, reason: invalid class name */
        public class AnonymousClass1 extends BackupImageView {
            final /* synthetic */ TopView this$0;

            public AnonymousClass1(TopView r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void setAlpha(float r2) {
                super.setAlpha(r2);
                if (r2 <= 0.0f) goto L5;
                int r22 = 0;
            L6:
                setVisibility(r22);
                return;
            L5:
                r22 = 4;
                goto L6
            }
        }

        /* renamed from: org.telegram.ui.Stars.StarGiftSheet$TopView$2, reason: invalid class name */
        public class AnonymousClass2 extends LinearLayout {
            final /* synthetic */ TopView this$0;

            public AnonymousClass2(TopView r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // android.view.ViewGroup, android.view.View
            public boolean dispatchTouchEvent(MotionEvent r3) {
                if (TopView.m19079$$Nest$fgetcurrentPage(this.this$0).is(0) == true) goto L6;
                return false;
            L6:
                return super.dispatchTouchEvent(r3);
            }
        }

        /* renamed from: org.telegram.ui.Stars.StarGiftSheet$TopView$3, reason: invalid class name */
        public class AnonymousClass3 extends AnimatorListenerAdapter {
            final /* synthetic */ TopView this$0;

            public AnonymousClass3(TopView r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                TopView.m19089$$Nest$fputresellPriceViewInProgress(this.this$0, false);
            }
        }

        /* renamed from: org.telegram.ui.Stars.StarGiftSheet$TopView$4, reason: invalid class name */
        public class AnonymousClass4 extends AnimatorListenerAdapter {
            final /* synthetic */ TopView this$0;

            public AnonymousClass4(TopView r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                TopView.m19089$$Nest$fputresellPriceViewInProgress(this.this$0, false);
            }
        }

        /* renamed from: org.telegram.ui.Stars.StarGiftSheet$TopView$5, reason: invalid class name */
        public class AnonymousClass5 extends AnimatorListenerAdapter {
            final /* synthetic */ TopView this$0;

            public AnonymousClass5(TopView r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                TopView.m19086$$Nest$fgetresellPriceView(this.this$0).setVisibility(4);
            }
        }

        /* renamed from: org.telegram.ui.Stars.StarGiftSheet$TopView$6, reason: invalid class name */
        public class AnonymousClass6 extends AnimatorListenerAdapter {
            final /* synthetic */ TopView this$0;

            public AnonymousClass6(TopView r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                TopView.m19091$$Nest$fputtoggleBackdrop(this.this$0, TopView.m19088$$Nest$fgettoggled(r2));
                TopView r1 = this.this$0;
                r1.onSwitchPage(TopView.m19079$$Nest$fgetcurrentPage(r1));
            }
        }

        /* renamed from: org.telegram.ui.Stars.StarGiftSheet$TopView$7, reason: invalid class name */
        public class AnonymousClass7 extends AnimatorListenerAdapter {
            final /* synthetic */ TopView this$0;

            public AnonymousClass7(TopView r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r3) {
                TopView.m19091$$Nest$fputtoggleBackdrop(this.this$0, TopView.m19088$$Nest$fgettoggled(r3));
                TopView r32 = this.this$0;
                r32.onSwitchPage(TopView.m19079$$Nest$fgetcurrentPage(r32));
                TopView.m19081$$Nest$fgetimageViewAttributes(this.this$0)[2 - TopView.m19088$$Nest$fgettoggled(this.this$0)] = (TL_stars.starGiftAttributeModel) TopView.m19084$$Nest$fgetmodels(this.this$0).getNext();
                StarsIntroActivity.setGiftImage(TopView.m19080$$Nest$fgetimageView(this.this$0)[2 - TopView.m19088$$Nest$fgettoggled(this.this$0)].getImageReceiver(), TopView.m19081$$Nest$fgetimageViewAttributes(this.this$0)[2 - TopView.m19088$$Nest$fgettoggled(this.this$0)].document, 160);
                TopView r33 = this.this$0;
                TopView.m19092$$Nest$mpreloadPattern(r33, (TL_stars.starGiftAttributePattern) TopView.m19085$$Nest$fgetpatterns(r33).getNext());
                AndroidUtilities.cancelRunOnUIThread(TopView.m19076$$Nest$fgetcheckToRotateRunnable(this.this$0));
                AndroidUtilities.runOnUIThread(TopView.m19076$$Nest$fgetcheckToRotateRunnable(this.this$0), 2500);
            }
        }

        /* renamed from: org.telegram.ui.Stars.StarGiftSheet$TopView$8, reason: invalid class name */
        public class AnonymousClass8 extends AnimatorListenerAdapter {
            final /* synthetic */ TopView this$0;

            public AnonymousClass8(TopView r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                TopView.m19090$$Nest$fputswitchScale(this.this$0, 1.0f);
                TopView r22 = this.this$0;
                r22.imageLayout.setScaleX(TopView.m19087$$Nest$fgetswitchScale(r22));
                TopView r23 = this.this$0;
                r23.imageLayout.setScaleY(TopView.m19087$$Nest$fgetswitchScale(r23));
                this.this$0.invalidate();
            }
        }

        public static class Button extends FrameLayout {
            public ImageView imageView;
            public TextView textView;

            public Button(Context r12) {
                super(r12);
                ImageView r0 = new ImageView(r12);
                this.imageView = r0;
                r0.setScaleType(ImageView.ScaleType.CENTER);
                this.imageView.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
                addView(this.imageView, LayoutHelper.createFrame(24, 24.0f, 49, 0.0f, 8.0f, 0.0f, 0.0f));
                TextView r02 = new TextView(r12);
                this.textView = r02;
                r02.setTypeface(AndroidUtilities.bold());
                this.textView.setTextSize(1, 12.0f);
                this.textView.setTextColor(-1);
                this.textView.setGravity(17);
                addView(this.textView, LayoutHelper.createFrame(-1, -2.0f, 49, 4.0f, 35.0f, 4.0f, 0.0f));
            }

            public void set(int r2, CharSequence r3, boolean r4) {
                ImageView r0 = this.imageView;
                if (r4 == false) goto L5;
                AndroidUtilities.updateImageViewImageAnimated(r0, r2);
            L6:
                this.textView.setText(r3);
                return;
            L5:
                r0.setImageResource(r2);
                goto L6
            }
        }

        /* renamed from: $r8$lambda$-2_Mf3bm2DSqAEoK5KG5SszrhHM, reason: not valid java name */
        public static /* synthetic */ void m19073$r8$lambda$2_Mf3bm2DSqAEoK5KG5SszrhHM(TopView r0, ValueAnimator r1) {
            r0.lambda$setPreviewAttributes$4(r1);
        }

        /* renamed from: $r8$lambda$-a1OwP2C8h1w4pEgPWy8FSexnuc, reason: not valid java name */
        public static /* synthetic */ void m19074$r8$lambda$a1OwP2C8h1w4pEgPWy8FSexnuc(TopView r0, ValueAnimator r1) {
            r0.lambda$rotateAttributes$5(r1);
        }

        public static /* synthetic */ void $r8$lambda$2cTi3QS8pWBZdV9oIIGBEs6lE7Y(TopView r0, View r1) {
            r0.lambda$setGift$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$K59r5vJC5rwxDMOaojWVt0OHdX8(TopView r0, View r1) {
            r0.lambda$new$0(r1);
        }

        /* renamed from: $r8$lambda$Mr3-92Bou-nEE6pVDndKZrX_uM0, reason: not valid java name */
        public static /* synthetic */ void m19075$r8$lambda$Mr392BounEE6pVDndKZrX_uM0(TopView r0) {
            r0.lambda$new$3();
        }

        public static /* synthetic */ void $r8$lambda$cTrNfSv2Wv0CxR3BJ5d76X_dpq8(Runnable r0, View r1) {
            r0.run();
        }

        public static /* synthetic */ void $r8$lambda$doLWZa2TquitvgVUzp8ewGu7qLA(TopView r0, ValueAnimator r1) {
            r0.lambda$animateSwitch$6(r1);
        }

        /* renamed from: -$$Nest$fgetcheckToRotateRunnable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m19076$$Nest$fgetcheckToRotateRunnable(TopView r0) {
            return r0.checkToRotateRunnable;
        }

        /* renamed from: -$$Nest$fgetcraftTopView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ CraftTopView m19077$$Nest$fgetcraftTopView(TopView r0) {
            return r0.craftTopView;
        }

        /* renamed from: -$$Nest$fgetcraftView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ImageView m19078$$Nest$fgetcraftView(TopView r0) {
            return r0.craftView;
        }

        /* renamed from: -$$Nest$fgetcurrentPage, reason: not valid java name */
        public static /* bridge */ /* synthetic */ PageTransition m19079$$Nest$fgetcurrentPage(TopView r0) {
            return r0.currentPage;
        }

        /* renamed from: -$$Nest$fgetimageView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BackupImageView[] m19080$$Nest$fgetimageView(TopView r0) {
            return r0.imageView;
        }

        /* renamed from: -$$Nest$fgetimageViewAttributes, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TL_stars.starGiftAttributeModel[] m19081$$Nest$fgetimageViewAttributes(TopView r0) {
            return r0.imageViewAttributes;
        }

        /* renamed from: -$$Nest$fgetimagesRollView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ StickersRollView m19082$$Nest$fgetimagesRollView(TopView r0) {
            return r0.imagesRollView;
        }

        /* renamed from: -$$Nest$fgetmessageTextPaint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextPaint m19083$$Nest$fgetmessageTextPaint(TopView r0) {
            return r0.messageTextPaint;
        }

        /* renamed from: -$$Nest$fgetmodels, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BagRandomizer m19084$$Nest$fgetmodels(TopView r0) {
            return r0.models;
        }

        /* renamed from: -$$Nest$fgetpatterns, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BagRandomizer m19085$$Nest$fgetpatterns(TopView r0) {
            return r0.patterns;
        }

        /* renamed from: -$$Nest$fgetresellPriceView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView m19086$$Nest$fgetresellPriceView(TopView r0) {
            return r0.resellPriceView;
        }

        /* renamed from: -$$Nest$fgetswitchScale, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m19087$$Nest$fgetswitchScale(TopView r0) {
            return r0.switchScale;
        }

        /* renamed from: -$$Nest$fgettoggled, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m19088$$Nest$fgettoggled(TopView r0) {
            return r0.toggled;
        }

        /* renamed from: -$$Nest$fputresellPriceViewInProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19089$$Nest$fputresellPriceViewInProgress(TopView r0, boolean r1) {
            r0.resellPriceViewInProgress = r1;
        }

        /* renamed from: -$$Nest$fputswitchScale, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19090$$Nest$fputswitchScale(TopView r0, float r1) {
            r0.switchScale = r1;
        }

        /* renamed from: -$$Nest$fputtoggleBackdrop, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19091$$Nest$fputtoggleBackdrop(TopView r0, float r1) {
            r0.toggleBackdrop = r1;
        }

        /* renamed from: -$$Nest$mpreloadPattern, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19092$$Nest$mpreloadPattern(TopView r0, TL_stars.starGiftAttributePattern r1) {
            r0.preloadPattern(r1);
        }

        public TopView(Context r29, Theme.ResourcesProvider r30, Runnable r31, View.OnClickListener r32, View.OnClickListener r33, View.OnClickListener r34, View.OnClickListener r35, View.OnClickListener r36, View.OnClickListener r37, View.OnClickListener r38) {
            super(r29);
            this.imageView = new BackupImageView[5];
            this.imageViewAttributes = new TL_stars.starGiftAttributeModel[3];
            int r7 = 0;
            this.currentImageIndex = 0;
            this.layout = new LinearLayout[5];
            this.layoutLayoutParams = new FrameLayout.LayoutParams[5];
            this.titleView = new LinkSpanDrawable.LinksTextView[5];
            this.subtitleView = new LinkSpanDrawable.LinksTextView[5];
            this.subtitleViewLayoutParams = new LinearLayout.LayoutParams[5];
            this.messageTextView = new GiftMessageView[5];
            this.currentPage = new PageTransition(0, 0, 1.0f);
            this.backdrop = new TL_stars.starGiftAttributeBackdrop[3];
            this.checkToRotateRunnable = new StarGiftSheet$TopView$$ExternalSyntheticLambda0(this);
            this.backgroundPaint = new Paint[3];
            this.backgroundGradient = new RadialGradient[3];
            this.backgroundMatrix = new Matrix[3];
            this.profileBackgroundMatrix = new Matrix();
            this.profileBackgroundPaint = new Paint(1);
            this.patternAttribute = new TL_stars.starGiftAttributePattern[2];
            this.pattern = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable[2];
            int r10 = 0;
        L3:
            Paint[] r11 = this.backgroundPaint;
            if (r10 >= r11.length) goto L6;
            r11[r10] = new Paint(1);
            r10 = r10 + 1;
            goto L3
        L6:
            int r102 = 0;
        L7:
            AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable[] r112 = this.pattern;
            float r13 = 28.0f;
            if (r102 >= r112.length) goto L10;
            r112[r102] = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this, AndroidUtilities.dp(28.0f));
            r102 = r102 + 1;
            goto L7
        L10:
            this.switchScale = 1.0f;
            this.particlesBounds = new RectF();
            this.backgroundColors = new int[12];
            this.textColors = new int[12];
            this.patternColors = new int[12];
            this.resourcesProvider = r30;
            this.onShareClick = r36;
            this.onResellClick = r37;
            this.onUpdatePriceClick = r38;
            setWillNotDraw(false);
            this.imageLayout = new FrameLayout(r29);
            int r103 = 0;
        L11:
            BackupImageView[] r113 = this.imageView;
            float r14 = 0.0f;
            float r16 = r13;
            if (r103 >= r113.length) goto L20;
            r113[r103] = new AnonymousClass1(this, r29);
            this.imageView[r103].setLayerNum(6660);
            if (r103 <= 0) goto L16;
            this.imageView[r103].getImageReceiver().setCrossfadeDuration(1);
        L16:
            this.imageLayout.addView(this.imageView[r103], LayoutHelper.createFrame(-1, -1, 119));
            BackupImageView r114 = this.imageView[r103];
            if (r103 != this.currentImageIndex) goto L19;
            r14 = 1.0f;
        L19:
            r114.setAlpha(r14);
            r103 = r103 + 1;
            r13 = r16;
            goto L11
        L20:
            LinkSpanDrawable.LinksTextView r104 = new LinkSpanDrawable.LinksTextView(r29);
            this.releasedView = r104;
            r104.setTextSize(1, 12.0f);
            r104.setGravity(17);
            r104.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r30));
            r104.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r30));
            r104.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
            TextView r8 = new TextView(r29);
            this.collectionReleasedView = r8;
            r8.setOnClickListener(new StarGiftSheet$TopView$$ExternalSyntheticLambda1(this));
            ScaleStateListAnimator.apply(r8, 0.05f, 1.25f);
            r8.setTextSize(1, 13.0f);
            r8.setGravity(17);
            r8.setLinkTextColor(-1);
            r8.setPadding(AndroidUtilities.dp(7.0f), 0, AndroidUtilities.dp(7.0f), 0);
            TextView r82 = new TextView(r29);
            this.resellPriceView = r82;
            r82.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
            r82.setTextSize(1, 13.0f);
            r82.setTextColor(-1);
            r82.setTypeface(AndroidUtilities.bold());
            r82.setAlpha(0.0f);
            r82.setScaleX(0.4f);
            r82.setScaleY(0.4f);
            r82.setVisibility(8);
            r82.setGravity(17);
            ScaleStateListAnimator.apply(r82);
            AnonymousClass2 r83 = new AnonymousClass2(this, r29);
            this.buttonsLayout = r83;
            r83.setOrientation(0);
            this.buttons = new Button[3];
            int r84 = 0;
        L21:
            Button[] r12 = this.buttons;
            if (r84 >= r12.length) goto L36;
            r12[r84] = new Button(r29);
            if (r84 == 0) goto L30;
            if (r84 == 1) goto L29;
            if (r84 != 2) goto L31;
            this.buttons[r84].set(R.drawable.filled_share, LocaleController.getString(R.string.Gift2ActionShare), false);
            this.buttons[r84].setOnClickListener(r36);
        L31:
            this.buttons[r84].setBackground(Theme.createRadSelectorDrawable(0, 285212671, 16, 16));
            ScaleStateListAnimator.apply(this.buttons[r84], 0.075f, 1.5f);
            LinearLayout r6 = this.buttonsLayout;
            Button[] r105 = this.buttons;
            Button r15 = r105[r84];
            if (r84 == (r105.length - 1)) goto L34;
            int r26 = 11;
        L35:
            r6.addView(r15, LayoutHelper.createLinear(0, 56, 1.0f, 119, 0, 0, r26, 0));
            r84 = r84 + 1;
            goto L21
        L34:
            r26 = 0;
            goto L35
        L29:
            this.buttons[r84].set(R.drawable.filled_crown_on, LocaleController.getString(R.string.Gift2ActionWear), false);
            this.buttons[r84].setOnClickListener(r35);
            goto L31
        L30:
            this.buttons[r84].set(R.drawable.filled_gift_transfer, LocaleController.getString(R.string.Gift2ActionTransfer), false);
            this.buttons[r84].setOnClickListener(r34);
            goto L31
        L36:
            this.subtitleContainer = new FrameLayout(r29);
            int r4 = 0;
        L37:
            LinearLayout[] r62 = this.layout;
            if (r4 >= r62.length) goto L99;
            r62[r4] = new LinearLayout(r29);
            this.layout[r4].setOrientation(1);
            if (r4 != 2) goto L42;
            FrameLayout r122 = new FrameLayout(r29);
            this.userLayout = r122;
            int r20 = r7;
            this.layout[r4].addView(r122, LayoutHelper.createLinear(-1, 144, 119));
            BackupImageView r123 = new BackupImageView(r29);
            this.avatarView = r123;
            r123.setRoundRadius(AndroidUtilities.dp(41.0f));
            this.userLayout.addView(this.avatarView, LayoutHelper.createFrame(82, 82.0f, 49, 0.0f, 2.0f, 0.0f, 0.0f));
            this.titleView[r4] = new LinkSpanDrawable.LinksTextView(r29);
            this.titleView[r4].setTextColor(-1);
            this.titleView[r4].setTextSize(1, 20.0f);
            this.titleView[r4].setTypeface(AndroidUtilities.bold());
            this.titleView[r4].setSingleLine();
            LinkSpanDrawable.LinksTextView r63 = this.titleView[r4];
            TextUtils.TruncateAt r124 = TextUtils.TruncateAt.END;
            r63.setEllipsize(r124);
            this.titleView[r4].setGravity(17);
            this.userLayout.addView(this.titleView[r4], LayoutHelper.createFrame(-1, -2.0f, 49, 16.0f, 95.33f, 16.0f, 0.0f));
            this.subtitleView[r4] = new LinkSpanDrawable.LinksTextView(r29);
            this.subtitleView[r4].setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r30));
            this.subtitleView[r4].setTextSize(1, 14.0f);
            this.subtitleView[r4].setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r30));
            this.subtitleView[r4].setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.subtitleView[r4].setDisablePaddingsOffsetY(true);
            this.subtitleView[r4].setSingleLine();
            this.subtitleView[r4].setGravity(17);
            this.subtitleView[r4].setEllipsize(r124);
            this.userLayout.addView(this.subtitleView[r4], LayoutHelper.createFrame(-1, -2.0f, 49, 16.0f, 122.0f, 16.0f, 0.0f));
        L90:
            if (r4 != 0) goto L92;
            this.layout[r4].addView(this.buttonsLayout, LayoutHelper.createLinear(-1, -2, 7, 0, 15, 0, 0));
        L92:
            View r64 = this.layout[r4];
            FrameLayout.LayoutParams[] r72 = this.layoutLayoutParams;
            if (r4 != 2) goto L96;
            float r106 = 32.0f;
        L97:
            ViewGroup.LayoutParams r107 = LayoutHelper.createFrame(-1, -2.0f, 119, 16.0f, r106, 16.0f, 0.0f);
            r72[r4] = r107;
            addView(r64, r107);
        L98:
            r4 = r4 + 1;
            r7 = r20;
            goto L37
        L96:
            r106 = 170.0f;
            goto L97
        L42:
            r20 = r7;
            if (r4 != 4) goto L45;
            CraftTopView r65 = new CraftTopView(r29, r30);
            this.craftTopView = r65;
            this.layout[r4].addView(r65, LayoutHelper.createLinear(-1, -2));
            View r66 = this.layout[r4];
            FrameLayout.LayoutParams[] r85 = this.layoutLayoutParams;
            ViewGroup.LayoutParams r108 = LayoutHelper.createFrame(-1, -2.0f, 119, 0.0f, 0.0f, 0.0f, 0.0f);
            r85[r4] = r108;
            addView(r66, r108);
            goto L98
        L45:
            this.titleView[r4] = new LinkSpanDrawable.LinksTextView(r29);
            LinkSpanDrawable.LinksTextView r125 = this.titleView[r4];
            if (r4 != 3) goto L48;
            int r152 = -1;
        L49:
            r125.setTextColor(r152);
            this.titleView[r4].setTextSize(1, 20.0f);
            this.titleView[r4].setTypeface(AndroidUtilities.bold());
            this.titleView[r4].setGravity(17);
            LinearLayout r67 = this.layout[r4];
            LinkSpanDrawable.LinksTextView r126 = this.titleView[r4];
            if (r4 != 3) goto L52;
            int r25 = 10;
        L53:
            r67.addView(r126, LayoutHelper.createLinear(-1, -2, 17, 24, r25, 24, 0));
            if (r4 != 0) goto L56;
            this.layout[r4].addView(this.releasedView, LayoutHelper.createLinear(-2, -2, 17, 0, 4, 0, 4));
            this.layout[r4].addView(this.collectionReleasedView, LayoutHelper.createLinear(-2, 19.33f, 17, 0, 6, 0, 2));
        L56:
            LinkSpanDrawable.LinksTextView[] r68 = this.subtitleView;
            if (r4 != 0) goto L67;
            r68[r4] = new LinkSpanDrawable.LinksTextView(r29);
            LinkSpanDrawable.LinksTextView r69 = this.subtitleView[r4];
            if (r4 != 3) goto L61;
            int r73 = Theme.multAlpha(-1, 0.75f);
        L62:
            r69.setTextColor(r73);
            this.subtitleView[r4].setTextSize(1, 14.0f);
            this.subtitleView[r4].setGravity(17);
            this.subtitleView[r4].setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r30));
            this.subtitleView[r4].setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.subtitleView[r4].setDisablePaddingsOffsetY(true);
            this.subtitleContainer.addView(this.subtitleView[r4], LayoutHelper.createFrame(-2, -2, 17));
            this.subtitleContainer.addView(this.resellPriceView, LayoutHelper.createFrame(-2.0f, 20.33f, 17));
            LinearLayout r610 = this.layout[r4];
            FrameLayout r74 = this.subtitleContainer;
            LinearLayout.LayoutParams[] r86 = this.subtitleViewLayoutParams;
            if (r4 != 3) goto L65;
            int r27 = 6;
        L66:
            LinearLayout.LayoutParams r127 = LayoutHelper.createLinear(-1, -2, 17, 24, 0, 24, r27);
            r86[r4] = r127;
            r610.addView(r74, r127);
        L76:
            LinearLayout.LayoutParams r611 = this.subtitleViewLayoutParams[r4];
            if (r4 != 3) goto L79;
            float r75 = 6.0f;
        L86:
            r611.topMargin = AndroidUtilities.dp(r75);
            this.messageTextView[r4] = new GiftMessageView(r29);
            this.messageTextView[r4].setVisibility(8);
            this.messageTextView[r4].setPadding(AndroidUtilities.dp(30.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(30.0f), AndroidUtilities.dp(2.0f));
            if (r4 != 0) goto L89;
            this.messageTextPaint = this.messageTextView[r4].getTextPaint();
        L89:
            this.layout[r4].addView(this.messageTextView[r4], LayoutHelper.createLinear(-1, -2, 17, 24, 8, 24, 0));
            goto L90
        L79:
            if (r4 != 1) goto L82;
            float r76 = 7.33f;
        L85:
            r75 = r76 - 4.0f;
            goto L86
        L82:
            if (this.backdrop[r20] != null) goto L84;
            r76 = 9.0f;
            goto L85
        L84:
            r76 = 5.66f;
            goto L85
        L65:
            r27 = r20;
            goto L66
        L61:
            r73 = Theme.getColor(Theme.key_dialogTextBlack, r30);
            goto L62
        L67:
            r68[r4] = new LinkSpanDrawable.LinksTextView(r29);
            LinkSpanDrawable.LinksTextView r612 = this.subtitleView[r4];
            if (r4 != 3) goto L70;
            int r77 = Theme.multAlpha(-1, 0.75f);
        L71:
            r612.setTextColor(r77);
            this.subtitleView[r4].setTextSize(1, 14.0f);
            this.subtitleView[r4].setGravity(17);
            this.subtitleView[r4].setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r30));
            this.subtitleView[r4].setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.subtitleView[r4].setDisablePaddingsOffsetY(true);
            LinearLayout r613 = this.layout[r4];
            LinkSpanDrawable.LinksTextView r78 = this.subtitleView[r4];
            LinearLayout.LayoutParams[] r142 = this.subtitleViewLayoutParams;
            if (r4 != 3) goto L74;
            int r272 = 6;
        L75:
            LinearLayout.LayoutParams r128 = LayoutHelper.createLinear(-1, -2, 17, 24, 0, 24, r272);
            r142[r4] = r128;
            r613.addView(r78, r128);
            goto L76
        L74:
            r272 = r20;
            goto L75
        L70:
            r77 = Theme.getColor(Theme.key_dialogTextBlack, r30);
            goto L71
        L52:
            r25 = r20;
            goto L53
        L48:
            r152 = Theme.getColor(Theme.key_dialogTextBlack, r30);
            goto L49
        L99:
            addView(this.imageLayout, LayoutHelper.createFrame(160, 160.0f, 49, 0.0f, 8.0f, 0.0f, 0.0f));
            StickersRollView r42 = new StickersRollView(r29, r30);
            this.imagesRollView = r42;
            addView(r42, LayoutHelper.createFrame(-1, 160.0f, 55, 0.0f, 8.0f, 0.0f, 0.0f));
            ImageView r2 = new ImageView(r29);
            this.closeView = r2;
            r2.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(r16), 620756991));
            r2.setImageResource(R.drawable.msg_close);
            ScaleStateListAnimator.apply(r2);
            addView(r2, LayoutHelper.createFrame(28, 28.0f, 53, 0.0f, 12.0f, 12.0f, 0.0f));
            r2.setOnClickListener(new StarGiftSheet$TopView$$ExternalSyntheticLambda2(r31));
            r2.setVisibility(8);
            ImageView r22 = new ImageView(r29);
            this.craftView = r22;
            r22.setImageResource(R.drawable.filled_forge);
            ImageView.ScaleType r43 = ImageView.ScaleType.CENTER;
            r22.setScaleType(r43);
            r22.setBackground(Theme.createSelectorDrawable(553648127, 1));
            ScaleStateListAnimator.apply(r22);
            if (r33 == null) goto L102;
            addView(r22, LayoutHelper.createFrame(42, 42.0f, 53, 0.0f, 5.0f, 47.0f, 0.0f));
            r22.setOnClickListener(r33);
        L102:
            r22.setVisibility(8);
            ImageView r23 = new ImageView(r29);
            this.optionsView = r23;
            r23.setContentDescription(LocaleController.getString(R.string.AccDescrGoBack));
            r23.setImageResource(R.drawable.media_more);
            r23.setScaleType(r43);
            r23.setBackground(Theme.createSelectorDrawable(553648127, 1));
            ScaleStateListAnimator.apply(r23);
            addView(r23, LayoutHelper.createFrame(42, 42.0f, 53, 0.0f, 5.0f, 5.0f, 0.0f));
            r23.setOnClickListener(r32);
            r23.setVisibility(8);
            GiftSheet.Ribbon r24 = new GiftSheet.Ribbon(r29);
            this.ribbon = r24;
            r24.setText(LocaleController.getString(R.string.GiftCrafted), true);
            r24.drawable.setParticles(true);
            r24.drawable.setLeft(true);
            r24.setScaleX(1.2f);
            r24.setScaleY(1.2f);
            addView(r24, LayoutHelper.createFrame(-2, -2.0f, 51, 0.0f, 0.0f, 0.0f, 0.0f));
            r24.setVisibility(8);
        }

        private void animateSwitch() {
            ValueAnimator r0 = this.switchAnimator;
            if (r0 == null) goto L5;
            r0.cancel();
            this.switchAnimator = null;
        L5:
            ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.switchAnimator = r02;
            r02.addUpdateListener(new StarGiftSheet$TopView$$ExternalSyntheticLambda5(this));
            this.switchAnimator.addListener(new AnonymousClass8(this));
            this.switchAnimator.setDuration(320);
            this.switchAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT);
            this.switchAnimator.start();
        }

        private /* synthetic */ void lambda$animateSwitch$6(ValueAnimator r5) {
            float r0 = ((((float) Math.pow((r5 * 2.0f) - 2.0f, 2.0d)) * 0.075f) * ((Float) r5.getAnimatedValue()).floatValue()) + 1.0f;
            this.switchScale = r0;
            this.imageLayout.setScaleX(r0);
            this.imageLayout.setScaleY(this.switchScale);
            invalidate();
        }

        private /* synthetic */ void lambda$new$0(View r4) {
            CharSequence r3 = this.collectionReleasedView.getText();
            if ((r3 instanceof Spanned) == false) goto L10;
            ClickableSpan[] r32 = (ClickableSpan[]) ((Spanned) r3).getSpans(0, r3.length(), ClickableSpan.class);
            if (r32.length <= 0) goto L9;
            r32[0].onClick(r4);
            return;
        L9:
            return;
        }

        private /* synthetic */ void lambda$new$3() {
            if (this.imageView[2 - this.toggled].getImageReceiver().hasImageLoaded() == false) goto L6;
            rotateAttributes();
            return;
        L6:
            AndroidUtilities.cancelRunOnUIThread(this.checkToRotateRunnable);
            AndroidUtilities.runOnUIThread(this.checkToRotateRunnable, 150);
        }

        private /* synthetic */ void lambda$rotateAttributes$5(ValueAnimator r1) {
            this.toggleBackdrop = ((Float) r1.getAnimatedValue()).floatValue();
            onSwitchPage(this.currentPage);
        }

        private /* synthetic */ void lambda$setGift$2(View r2) {
            if (this.resellPriceView.getVisibility() != 0) goto L10;
            View.OnClickListener r1 = this.onUpdatePriceClick;
            if (r1 == null) goto L9;
            r1.onClick(r2);
            return;
        L9:
            return;
        }

        private /* synthetic */ void lambda$setPreviewAttributes$4(ValueAnimator r1) {
            this.toggleBackdrop = ((Float) r1.getAnimatedValue()).floatValue();
            onSwitchPage(this.currentPage);
        }

        private void preloadPattern(TL_stars.starGiftAttributePattern r2) {
            if (r2 != null) goto L4;
            return;
        L4:
            AnimatedEmojiDrawable.make(UserConfig.selectedAccount, 7, r2.document).preload();
        }

        private void rotateAttributes() {
            PageTransition r0 = this.currentPage;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.to == 1) goto L7;
            return;
        L7:
            if (isAttachedToWindow() == false) goto L20;
            AndroidUtilities.cancelRunOnUIThread(this.checkToRotateRunnable);
            ValueAnimator r02 = this.rotationAnimator;
            if (r02 == null) goto L12;
            r02.cancel();
            this.rotationAnimator = null;
        L12:
            int r03 = 1 - this.toggled;
            this.toggled = r03;
            RLottieDrawable r04 = this.imageView[2 - r03].getImageReceiver().getLottieAnimation();
            RLottieDrawable r2 = this.imageView[this.toggled + 1].getImageReceiver().getLottieAnimation();
            if (r2 == null) goto L16;
            if (r04 == null) goto L16;
            r2.setProgress(r04.getProgress(), false);
        L16:
            this.models.next();
            int r05 = this.toggled;
            TL_stars.starGiftAttributeBackdrop r6 = this.backdrops.next();
            this.backdrop[r05 + 1] = r6;
            setBackdropPaint(r05 + 1, r6);
            setPattern(1, this.patterns.next(), true);
            animateSwitch();
            int r06 = this.toggled;
            ValueAnimator r07 = ValueAnimator.ofFloat(new float[]{1.0f - r06, r06});
            this.rotationAnimator = r07;
            r07.addUpdateListener(new StarGiftSheet$TopView$$ExternalSyntheticLambda6(this));
            this.rotationAnimator.addListener(new AnonymousClass7(this));
            this.rotationAnimator.setDuration(320);
            this.rotationAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.rotationAnimator.start();
            return;
        }

        private void setBackdropPaint(int r20, TL_stars.starGiftAttributeBackdrop r21) {
            if (r21 != null) goto L5;
            return;
        L5:
            RadialGradient[] r2 = this.backgroundGradient;
            Shader.TileMode r18 = Shader.TileMode.CLAMP;
            r2[r20] = new RadialGradient(0.0f, 0.0f, AndroidUtilities.dp(200.0f), new int[]{r21.center_color | (-16777216), r21.edge_color | (-16777216)}, new float[]{0.0f, 1.0f}, r18);
            if (r20 != 0) goto L8;
            RadialGradient r12 = new RadialGradient(0.0f, 0.0f, AndroidUtilities.dp(168.0f), new int[]{r21.center_color | (-16777216), r21.edge_color | (-16777216)}, new float[]{0.0f, 1.0f}, r18);
            this.profileBackgroundGradient = r12;
            this.profileBackgroundPaint.setShader(r12);
        L8:
            Matrix[] r1 = this.backgroundMatrix;
            if (r1[r20] != null) goto L11;
            r1[r20] = new Matrix();
        L11:
            this.backgroundPaint[r20].setShader(this.backgroundGradient[r20]);
        }

        private void updateWearImageTranslation() {
            this.wearImageScale = AndroidUtilities.dpf2(33.33f) / AndroidUtilities.dpf2(160.0f);
            this.wearImageTx = ((((-this.imageLayout.getLeft()) + this.titleView[2].getX()) + ((this.titleView[2].getWidth() + Math.min(this.titleView[2].getPaint().measureText(this.titleView[2].getText().toString()), this.titleView[2].getWidth())) / 2.0f)) + AndroidUtilities.dp(24.0f)) - (AndroidUtilities.dp(126.67f) / 2.0f);
            this.wearImageTy = ((-this.imageLayout.getTop()) + AndroidUtilities.dp(124.0f)) - (AndroidUtilities.dp(126.67f) / 2.0f);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r19) {
            float r5 = getRealHeight();
            r19.save();
            r19.clipRect(0.0f, 0.0f, getWidth(), r5);
            float r9 = getWidth() / 2.0f;
            float r11 = AndroidUtilities.lerp(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(24.0f), this.currentPage.at(1)) + AndroidUtilities.dp(80.0f);
            float r2 = this.currentPage.at(0, 2, 3);
            if (r2 <= 0.0f) goto L17;
            if (this.backdrop[0] == null) goto L17;
            if (this.profileBackgroundGradient != null) goto L9;
        L10:
            this.backgroundPaint[0].setAlpha((int) (r2 * 255.0f));
            this.backgroundMatrix[0].reset();
            this.backgroundMatrix[0].postTranslate(r9, r11);
            this.backgroundGradient[0].setLocalMatrix(this.backgroundMatrix[0]);
            r19.drawRect(0.0f, 0.0f, getWidth(), r5, this.backgroundPaint[0]);
        L12:
            if (this.profileBackgroundGradient == null) goto L17;
            if (this.currentPage.at(2) <= 0.0f) goto L17;
            this.profileBackgroundPaint.setAlpha((int) (this.currentPage.at(2) * 255.0f));
            this.profileBackgroundMatrix.reset();
            this.profileBackgroundMatrix.postTranslate(getWidth() / 2.0f, 0.4f * r5);
            this.profileBackgroundGradient.setLocalMatrix(this.profileBackgroundMatrix);
            r19.drawRect(0.0f, 0.0f, getWidth(), r5, this.profileBackgroundPaint);
            goto L17
        L9:
            if (this.currentPage.at(2) >= 1.0f) goto L12;
        L17:
            if (this.currentPage.at(1) <= 0.0f) goto L19;
            float r3 = r11;
            int r4 = drawBackground(r19, r9, r3, getWidth(), r5);
            TopView r92 = this;
            float r112 = r9;
            r92.updateButtonsBackgrounds(r4);
        L21:
            if (r92.backdrop[0] == null) goto L27;
            int r0 = 0;
        L23:
            int[] r1 = r92.backgroundColors;
            if (r0 >= r1.length) goto L27;
            int[] r22 = r92.textColors;
            TL_stars.starGiftAttributeBackdrop r42 = r92.backdrop[0];
            r22[r0] = r42.text_color | (-16777216);
            r1[r0] = ColorUtils.blendARGB(r42.edge_color | (-16777216), r42.pattern_color | (-16777216), 0.25f);
            r92.patternColors[r0] = r92.backdrop[0].pattern_color | (-16777216);
            r0 = r0 + 1;
        L27:
            if (r92.imagesRollView.hasBackgrounds() == false) goto L29;
            float r16 = 0.0f;
            float r7 = r3;
            Canvas r12 = r19;
            r92.imagesRollView.drawBackgrounds(r12, r92.getWidth(), r5, r92.textColors, r92.backgroundColors, r92.patternColors);
            r5 = r5;
            r92.invalidate();
        L30:
            if (r2 <= 0.0f) goto L61;
            if (r92.backdrop[0] == null) goto L61;
            int[] r02 = r92.patternColors;
            int r6 = r02[r02.length / 2];
            float r43 = r92.currentPage.at(0, 3);
            if (r43 <= r16) goto L37;
            r12.save();
            r12.translate(r112, r7);
            r92.pattern[0].setColor(Integer.valueOf(r6));
            float r32 = r5;
            StarGiftPatterns.drawPattern(r12, r92.pattern[0], r92.getWidth(), r32, r43, 1.0f);
            r5 = r32;
            r19.restore();
        L37:
            if (r92.currentPage.at(2) <= r16) goto L39;
            r19.save();
            r92.pattern[0].setColor(Integer.valueOf(r6));
            float r33 = r5;
            RectF r52 = AndroidUtilities.rectTmp;
            r52.set((r92.layout[2].getX() + r92.userLayout.getX()) + r92.avatarView.getX(), (r92.layout[2].getY() + r92.userLayout.getY()) + r92.avatarView.getY(), ((r92.layout[2].getX() + r92.userLayout.getX()) + r92.avatarView.getX()) + r92.avatarView.getWidth(), ((r92.layout[2].getY() + r92.userLayout.getY()) + r92.avatarView.getY()) + r92.avatarView.getHeight());
            StarGiftPatterns.drawProfileAnimatedPattern(r19, r92.pattern[0], r92.getWidth(), r33 * 0.7f, 1.0f, r52, r92.currentPage.at(2));
            r12 = r19;
            r12.restore();
        L40:
            Button[] r03 = r92.buttons;
            int r23 = r03.length;
            int r34 = 0;
        L41:
            if (r34 >= r23) goto L46;
            Button r44 = r03[r34];
            if (Theme.setSelectorDrawableColor(r44.getBackground(), r92.backgroundColors[Utilities.clamp(Math.round(((r44.getX() + (r44.getWidth() / 2.0f)) / r92.getWidth()) * (r92.backgroundColors.length - 1)), r92.backgroundColors.length - 1, 0)], false) == false) goto L45;
            r44.invalidate();
        L45:
            r34 = r34 + 1;
            goto L41
        L46:
            int[] r04 = r92.textColors;
            int r05 = r04[r04.length / 2];
            int[] r24 = r92.backgroundColors;
            int r25 = r24[r24.length / 2];
            TextView r35 = r92.collectionReleasedView;
            if (r35 == null) goto L52;
            if (r92.collectionReleasedViewColor == r05) goto L52;
            r92.collectionReleasedViewColor = r05;
            r35.setTextColor(r05);
            Theme.setSelectorDrawableColor(r92.collectionReleasedView.getBackground(), r25, false);
        L52:
            if (r92.imagesRollView.hasBackgrounds() == false) goto L55;
            r92.subtitleView[0].setTextColor(r05);
        L55:
            if (r92.currentPage.at(2) <= r16) goto L61;
            if (r92.particles != null) goto L59;
            r92.particles = new StarsReactionsSheet.Particles(1, 12);
        L59:
            float r06 = r92.imageLayout.getX() + (r92.imageLayout.getMeasuredWidth() / 2.0f);
            float r26 = (r92.imageLayout.getMeasuredWidth() * r92.imageLayout.getScaleX()) / 2.0f;
            float r36 = r92.imageLayout.getY() + (r92.imageLayout.getMeasuredHeight() / 2.0f);
            float r45 = (r92.imageLayout.getMeasuredHeight() * r92.imageLayout.getScaleY()) / 2.0f;
            r92.particlesBounds.set(r06 - r26, r36 - r45, r06 + r26, r36 + r45);
            r92.particles.setBounds(r92.particlesBounds);
            r92.particles.process();
            r92.particles.draw(r12, Theme.multAlpha(-1, r92.currentPage.at(2)));
            r92.invalidate();
            goto L61
        L39:
            r12 = r19;
        L61:
            if (r92.currentPage.at(1) <= r16) goto L63;
            TopView r07 = r92;
            float r27 = r112;
            r07.drawPattern(r12, r27, r7, r92.getWidth(), r92.getRealHeight());
        L63:
            super.dispatchDraw(r19);
            r19.restore();
            return;
        L29:
            r12 = r19;
            r16 = 0.0f;
            r7 = r3;
            goto L30
        L19:
            r3 = r11;
            r112 = r9;
            r92 = this;
            goto L21
        }

        public int drawBackground(Canvas r26, float r27, float r28, float r29, float r30) {
            int r3 = this.toggled;
            float r4 = this.toggleBackdrop;
            int r12 = 0;
            if (r3 != 0) goto L17;
            if (r4 <= 0.0f) goto L10;
            if (this.backdrop[2] == null) goto L10;
            this.backgroundPaint[2].setAlpha((int) (this.currentPage.at(1) * 255.0f));
            this.backgroundMatrix[2].reset();
            this.backgroundMatrix[2].postTranslate(r27, r28);
            this.backgroundGradient[2].setLocalMatrix(this.backgroundMatrix[2]);
            r26.drawRect(0.0f, 0.0f, r29, r30, this.backgroundPaint[2]);
            TL_stars.starGiftAttributeBackdrop r32 = this.backdrop[2];
            r12 = ColorUtils.compositeColors(ColorUtils.setAlphaComponent(ColorUtils.blendARGB(r32.edge_color | (-16777216), r32.pattern_color | (-16777216), 0.25f), this.backgroundPaint[2].getAlpha()), 0);
        L10:
            if (this.toggleBackdrop < 1.0f) goto L12;
        L15:
            return r12;
        L12:
            if (this.backdrop[1] == null) goto L15;
            this.backgroundPaint[1].setAlpha((int) ((this.currentPage.at(1) * 255.0f) * (1.0f - this.toggleBackdrop)));
            this.backgroundMatrix[1].reset();
            this.backgroundMatrix[1].postTranslate(r27, r28);
            this.backgroundGradient[1].setLocalMatrix(this.backgroundMatrix[1]);
            r26.drawRect(0.0f, 0.0f, r29, r30, this.backgroundPaint[1]);
            TL_stars.starGiftAttributeBackdrop r1 = this.backdrop[1];
            return ColorUtils.compositeColors(ColorUtils.setAlphaComponent(ColorUtils.blendARGB(r1.edge_color | (-16777216), r1.pattern_color | (-16777216), 0.25f), this.backgroundPaint[1].getAlpha()), r12);
        L17:
            if (r4 >= 1.0f) goto L22;
            if (this.backdrop[1] == null) goto L22;
            this.backgroundPaint[1].setAlpha((int) (this.currentPage.at(1) * 255.0f));
            this.backgroundMatrix[1].reset();
            this.backgroundMatrix[1].postTranslate(r27, r28);
            this.backgroundGradient[1].setLocalMatrix(this.backgroundMatrix[1]);
            r26.drawRect(0.0f, 0.0f, r29, r30, this.backgroundPaint[1]);
            TL_stars.starGiftAttributeBackdrop r33 = this.backdrop[1];
            r12 = ColorUtils.compositeColors(ColorUtils.setAlphaComponent(ColorUtils.blendARGB(r33.edge_color | (-16777216), r33.pattern_color | (-16777216), 0.25f), this.backgroundPaint[1].getAlpha()), 0);
        L22:
            if (this.toggleBackdrop > 0.0f) goto L24;
        L27:
            return r12;
        L24:
            if (this.backdrop[2] == null) goto L27;
            this.backgroundPaint[2].setAlpha((int) ((this.currentPage.at(1) * 255.0f) * this.toggleBackdrop));
            this.backgroundMatrix[2].reset();
            this.backgroundMatrix[2].postTranslate(r27, r28);
            this.backgroundGradient[2].setLocalMatrix(this.backgroundMatrix[2]);
            r26.drawRect(0.0f, 0.0f, r29, r30, this.backgroundPaint[2]);
            TL_stars.starGiftAttributeBackdrop r13 = this.backdrop[2];
            return ColorUtils.compositeColors(ColorUtils.setAlphaComponent(ColorUtils.blendARGB(r13.edge_color | (-16777216), r13.pattern_color | (-16777216), 0.25f), this.backgroundPaint[2].getAlpha()), r12);
        }

        public void drawPattern(Canvas r7, float r8, float r9, float r10, float r11) {
            r7.save();
            r7.translate(r8, r9);
            TL_stars.starGiftAttributeBackdrop[] r82 = this.backdrop;
            TL_stars.starGiftAttributeBackdrop r0 = r82[1];
            int r2 = 0;
            if (r0 != null) goto L5;
            int r02 = 0;
        L6:
            TL_stars.starGiftAttributeBackdrop r83 = r82[2];
            if (r83 == null) goto L10;
            r2 = r83.pattern_color | (-16777216);
        L10:
            this.pattern[1].setColor(Integer.valueOf(ColorUtils.blendARGB(r02, r2, this.toggleBackdrop)));
            StarGiftPatterns.drawPattern(r7, this.pattern[1], r10, r11, this.currentPage.at(1), this.switchScale);
            r7.restore();
            return;
        L5:
            r02 = r0.pattern_color | (-16777216);
            goto L6
        }

        public int getFinalHeight() {
            float r2 = 10.0f;
            if (this.currentPage.to(0) == false) goto L11;
            if (this.backdrop[0] == null) goto L7;
            r2 = 24.0f;
        L7:
            int r0 = AndroidUtilities.dp(r2) + AndroidUtilities.dp(160.0f);
            int r6 = this.layout[0].getMeasuredHeight();
        L9:
            return r0 + r6;
        L11:
            if (this.currentPage.to(1) == false) goto L17;
            if (this.backdrop[1] == null) goto L15;
            r2 = 24.0f;
        L15:
            r0 = AndroidUtilities.dp(r2) + AndroidUtilities.dp(160.0f);
            r6 = this.layout[1].getMeasuredHeight();
            goto L9
        L17:
            if (this.currentPage.to(2) == false) goto L20;
            r0 = AndroidUtilities.dp(64.0f);
            r6 = this.layout[2].getMeasuredHeight();
            goto L9
        L20:
            if (this.currentPage.to(3) == false) goto L23;
            r0 = AndroidUtilities.dp(160.0f);
            r6 = this.layout[3].getMeasuredHeight();
            goto L9
        L23:
            if (this.currentPage.to(4) == true) goto L25;
            return 0;
        L25:
            if (this.craftTopView.getMeasuredHeight() <= 0) goto L29;
            return this.craftTopView.getMeasuredHeight();
        L29:
            return AndroidUtilities.dp(550.0f);
        }

        public float getRealHeight() {
            float r2 = 10.0f;
            if (this.backdrop[0] == null) goto L5;
            float r0 = 24.0f;
        L6:
            float r02 = (((AndroidUtilities.dp(r0) + AndroidUtilities.dp(160.0f)) + this.layout[0].getMeasuredHeight()) * this.currentPage.at(0)) + 0.0f;
            if (this.backdrop[1] == null) goto L9;
            float r5 = 24.0f;
        L10:
            float r03 = (r02 + (((AndroidUtilities.dp(r5) + AndroidUtilities.dp(160.0f)) + this.layout[1].getMeasuredHeight()) * this.currentPage.at(1))) + ((AndroidUtilities.dp(64.0f) + this.layout[2].getMeasuredHeight()) * this.currentPage.at(2));
            if (this.backdrop[0] == null) goto L13;
            r2 = 24.0f;
        L13:
            float r04 = r03 + (((AndroidUtilities.dp(r2) + AndroidUtilities.dp(160.0f)) + this.layout[3].getMeasuredHeight()) * this.currentPage.at(3));
            if (this.craftTopView.getMeasuredHeight() <= 0) goto L16;
            int r1 = this.craftTopView.getMeasuredHeight();
        L18:
            return r04 + (r1 * this.currentPage.at(4));
        L16:
            r1 = AndroidUtilities.dp(550.0f);
            goto L18
        L9:
            r5 = 10.0f;
            goto L10
        L5:
            r0 = 10.0f;
            goto L6
        }

        public TL_stars.starGiftAttributeBackdrop getUpgradeBackdropAttribute() {
            float r0 = this.toggleBackdrop;
            TL_stars.starGiftAttributeBackdrop[] r2 = this.backdrop;
            if (r0 <= 0.5f) goto L7;
            return r2[2];
        L7:
            return r2[1];
        }

        public BackupImageView getUpgradeImageView() {
            float r0 = this.toggleBackdrop;
            BackupImageView[] r2 = this.imageView;
            if (r0 <= 0.5f) goto L7;
            return r2[2];
        L7:
            return r2[1];
        }

        public TL_stars.starGiftAttributeModel getUpgradeImageViewAttribute() {
            float r0 = this.toggleBackdrop;
            TL_stars.starGiftAttributeModel[] r2 = this.imageViewAttributes;
            if (r0 <= 0.5f) goto L7;
            return r2[2];
        L7:
            return r2[1];
        }

        public TL_stars.starGiftAttributePattern getUpgradePatternAttribute() {
            return this.patternAttribute[1];
        }

        public void hideCloseButton() {
            removeView(this.closeView);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            this.attached = true;
            super.onAttachedToWindow();
            this.pattern[0].attach();
            this.pattern[1].attach();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            this.attached = false;
            super.onDetachedFromWindow();
            this.pattern[0].detach();
            this.pattern[1].detach();
            AndroidUtilities.cancelRunOnUIThread(this.checkToRotateRunnable);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            if (this.currentPage.contains(2) == false) goto L6;
            updateWearImageTranslation();
            onSwitchPage(this.currentPage);
            return;
        }

        public void onSwitchPage(PageTransition r15) {
            this.currentPage = r15;
            int r0 = 0;
            int r1 = 0;
        L3:
            int r3 = 4;
            if (r1 >= this.layout.length) goto L9;
            float r2 = r15.at(r1);
            this.layout[r1].setAlpha(r2);
            LinearLayout r5 = this.layout[r1];
            if (r2 <= 0.0f) goto L8;
            r3 = 0;
        L8:
            r5.setVisibility(r3);
            r1 = r1 + 1;
            goto L3
        L9:
            ImageView r12 = this.closeView;
            if (this.backdrop[0] == null) goto L12;
            float r22 = r15.at(2);
        L14:
            if (this.backdrop[1] == null) goto L16;
            float r6 = r15.at(1);
        L17:
            r12.setAlpha(Math.max(r22, r6));
            ImageView r13 = this.closeView;
            TL_stars.starGiftAttributeBackdrop[] r23 = this.backdrop;
            if (r23[0] == null) goto L22;
            if (r15.to != 2) goto L22;
        L25:
            int r24 = 0;
        L27:
            r13.setVisibility(r24);
            ImageView r14 = this.optionsView;
            if (this.backdrop[0] == null) goto L30;
            boolean r25 = true;
        L31:
            r14.setAlpha(AndroidUtilities.lerp(false, r25, r15.at(0)));
            ImageView r16 = this.optionsView;
            if (this.backdrop[0] != null) goto L34;
        L36:
            int r26 = 8;
        L37:
            r16.setVisibility(r26);
            if (this.resellPriceViewInProgress == true) goto L54;
            this.resellPriceView.setAlpha(AndroidUtilities.lerp(false, this.hasResellPrice, r15.at(0)));
            TextView r17 = this.resellPriceView;
            if (this.hasResellPrice == false) goto L42;
            float r62 = 1.0f;
        L43:
            r17.setScaleX(AndroidUtilities.lerp(0.4f, r62, r15.at(0)));
            TextView r18 = this.resellPriceView;
            if (this.hasResellPrice == false) goto L46;
            float r63 = 1.0f;
        L47:
            r18.setScaleY(AndroidUtilities.lerp(0.4f, r63, r15.at(0)));
            TextView r19 = this.resellPriceView;
            if (this.hasResellPrice == true) goto L50;
        L52:
            int r64 = 4;
        L53:
            r19.setVisibility(r64);
            goto L54
        L50:
            if (r15.to != 0) goto L52;
            r64 = 0;
            goto L53
        L46:
            r63 = 0.4f;
            goto L47
        L42:
            r62 = 0.4f;
        L54:
            int r110 = Theme.getColor(Theme.key_dialogTextBlack, this.resourcesProvider);
            int r65 = 0;
        L56:
            if (r65 >= 2) goto L112;
            LinkSpanDrawable.LinksTextView r10 = this.titleView[r65];
            if (this.backdrop[Math.min(1, r65)] != null) goto L60;
            int r11 = r110;
        L61:
            r10.setTextColor(r11);
            LinkSpanDrawable.LinksTextView r102 = this.subtitleView[r65];
            if (r65 == 0) goto L74;
            if (r65 == 2) goto L74;
            TL_stars.starGiftAttributeBackdrop[] r112 = this.backdrop;
            TL_stars.starGiftAttributeBackdrop r122 = r112[1];
            if (r122 != null) goto L68;
            int r123 = r110;
        L69:
            TL_stars.starGiftAttributeBackdrop r113 = r112[2];
            if (r113 != null) goto L72;
            int r9 = r110;
        L73:
            int r92 = ColorUtils.blendARGB(r123, r9, this.toggleBackdrop);
        L78:
            r102.setTextColor(r92);
            if (this.backdrop[r65] == null) goto L90;
            if (AndroidUtilities.dp(184.0f) == this.layoutLayoutParams[r65].topMargin) goto L83;
        L86:
            boolean r103 = true;
        L87:
            if (r103 == false) goto L98;
            this.layout[r65].setPadding(0, 0, 0, AndroidUtilities.dp(18.0f));
            this.layoutLayoutParams[r65].topMargin = AndroidUtilities.dp(184.0f);
        L98:
            LinearLayout.LayoutParams r93 = this.subtitleViewLayoutParams[r65];
            if (r65 != 1) goto L102;
            float r114 = 7.33f;
        L105:
            r93.topMargin = AndroidUtilities.dp(r114 - 4.0f);
            if (r103 == false) goto L111;
            this.layout[r65].setLayoutParams(this.layoutLayoutParams[r65]);
            if (r65 != 0) goto L110;
            this.subtitleContainer.setLayoutParams(this.subtitleViewLayoutParams[r65]);
            goto L111
        L110:
            this.subtitleView[r65].setLayoutParams(this.subtitleViewLayoutParams[r65]);
        L111:
            r65 = r65 + 1;
            goto L56
        L102:
            if (this.backdrop[0] != null) goto L104;
            r114 = 9.0f;
            goto L105
        L104:
            r114 = 5.66f;
            goto L105
        L83:
            if (this.layout[r65].getPaddingBottom() != AndroidUtilities.dp(18.0f)) goto L86;
            r103 = false;
            goto L87
        L90:
            if (AndroidUtilities.dp(170.0f) == this.layoutLayoutParams[r65].topMargin) goto L92;
        L95:
            r103 = true;
        L96:
            if (r103 == false) goto L98;
            this.layout[r65].setPadding(0, 0, 0, AndroidUtilities.dp(3.0f));
            this.layoutLayoutParams[r65].topMargin = AndroidUtilities.dp(170.0f);
            goto L98
        L92:
            if (this.layout[r65].getPaddingBottom() != AndroidUtilities.dp(3.0f)) goto L95;
            r103 = false;
            goto L96
        L72:
            r9 = (-16777216) | r113.text_color;
            goto L73
        L68:
            r123 = r122.text_color | (-16777216);
        L74:
            TL_stars.starGiftAttributeBackdrop r115 = this.backdrop[r65];
            if (r115 != null) goto L77;
            r92 = r110;
            goto L78
        L77:
            r92 = (-16777216) | r115.text_color;
            goto L78
        L60:
            r11 = -1;
            goto L61
        L112:
            TextView r66 = this.collectionReleasedView;
            int r104 = AndroidUtilities.dp(24.0f);
            TL_stars.starGiftAttributeBackdrop r116 = this.backdrop[0];
            if (r116 != null) goto L115;
            int r117 = 553648127;
        L116:
            r66.setBackground(Theme.createRoundRectDrawable(r104, r117));
            LinkSpanDrawable.LinksTextView r67 = this.subtitleView[2];
            TL_stars.starGiftAttributeBackdrop r105 = this.backdrop[0];
            if (r105 == null) goto L120;
            r110 = r105.text_color | (-16777216);
        L120:
            r67.setTextColor(r110);
            this.imageView[0].setAlpha(Math.max(this.currentPage.at(0, 2), this.currentPage.at(3)));
            this.imageView[1].setAlpha(r15.at(1) * (1.0f - this.toggleBackdrop));
            this.imageView[2].setAlpha(r15.at(1) * this.toggleBackdrop);
            this.imageLayout.setScaleX(AndroidUtilities.lerp(1.0f, this.wearImageScale, r15.at(2)));
            this.imageLayout.setScaleY(AndroidUtilities.lerp(1.0f, this.wearImageScale, r15.at(2)));
            this.imageLayout.setTranslationX(this.wearImageTx * r15.at(2));
            this.imageLayout.setTranslationY((AndroidUtilities.dp(16.0f) * r15.at(1)) + (this.wearImageTy * r15.at(2)));
            LinearLayout r68 = this.layout[2];
            int r7 = r15.from;
            if (r7 == 2) goto L123;
        L126:
            if (r7 != 2) goto L128;
            r7 = r15.to;
        L128:
            float r111 = (-(r1[r7].getMeasuredHeight() - this.layout[2].getMeasuredHeight())) * (1.0f - r15.at(2));
        L129:
            r68.setTranslationY(r111);
            GiftSheet.Ribbon r118 = this.ribbon;
            if (this.hasRibbon == true) goto L132;
        L134:
            int r27 = 8;
        L135:
            r118.setVisibility(r27);
            this.ribbon.setAlpha(this.currentPage.at(0));
            CraftTopView r119 = this.craftTopView;
            if (r15.at(4) > 0.0f) goto L139;
            r0 = 8;
        L139:
            r119.setVisibility(r0);
            this.craftTopView.setAlpha(r15.at(4));
            invalidate();
            return;
        L132:
            if (this.currentPage.contains(0) == false) goto L134;
            r27 = 0;
            goto L135
        L123:
            if (r15.to != 2) goto L126;
            r111 = 0.0f;
            goto L129
        L115:
            r117 = ColorUtils.blendARGB(r116.edge_color | (-16777216), r116.pattern_color | (-16777216), 0.25f);
            goto L116
        L34:
            if (r15.to != 0) goto L36;
            r26 = 0;
            goto L37
        L30:
            r25 = false;
        L22:
            if (r23[1] != null) goto L24;
        L26:
            r24 = 8;
            goto L27
        L24:
            if (r15.to != 1) goto L26;
        L16:
            r6 = 0.0f;
            goto L17
        L12:
            r22 = 0.0f;
            goto L14
        }

        public void prepareSwitchPage(PageTransition r3) {
            int r0 = r3.from;
            if (r0 == r3.to) goto L9;
            RLottieDrawable r02 = this.imageView[r0].getImageReceiver().getLottieAnimation();
            RLottieDrawable r2 = this.imageView[r3.to].getImageReceiver().getLottieAnimation();
            if (r2 == null) goto L10;
            if (r02 == null) goto L11;
            r2.setProgress(r02.getProgress(), false);
            return;
        L11:
            return;
        L10:
            return;
        }

        public void setGift(TL_stars.StarGift r9, boolean r10, boolean r11, boolean r12, boolean r13, boolean r14) {
            this.hasResellPrice = false;
            if (r10 == true) goto L7;
            if (r11 == true) goto L7;
            boolean r1 = false;
        L8:
            boolean r2 = r9 instanceof TL_stars.TL_starGiftUnique;
            TL_stars.starGiftAttributeBackdrop[] r3 = this.backdrop;
            int r4 = 8;
            if (r2 == false) goto L54;
            r3[0] = (TL_stars.starGiftAttributeBackdrop) StarsController.findAttribute(r9.attributes, TL_stars.starGiftAttributeBackdrop.class);
            setPattern(0, (TL_stars.starGiftAttributePattern) StarsController.findAttribute(r9.attributes, TL_stars.starGiftAttributePattern.class), false);
            this.subtitleView[0].setTextSize(1, 13.0f);
            LinearLayout r22 = this.buttonsLayout;
            if (r1 == false) goto L13;
            r4 = 0;
        L13:
            r22.setVisibility(r4);
            if (r1 == false) goto L23;
            Button r15 = this.buttons[1];
            if (r12 == false) goto L18;
            int r23 = R.drawable.filled_crown_off;
        L19:
            if (r12 == false) goto L21;
            int r122 = R.string.Gift2ActionWearOff;
        L22:
            r15.set(r23, LocaleController.getString(r122), false);
            goto L23
        L21:
            r122 = R.string.Gift2ActionWear;
            goto L22
        L18:
            r23 = R.drawable.filled_crown_on;
        L23:
            float r16 = 1.0f;
            if (r9.resell_amount == null) goto L37;
            this.hasResellPrice = true;
            if (r9.resale_ton_only == false) goto L28;
            AmountUtils$Currency r123 = AmountUtils$Currency.TON;
        L29:
            AmountUtils$Amount r124 = r9.getResellAmount(r123);
            TextView r24 = this.resellPriceView;
            int r32 = R.string.GiftOnSale;
            if (r124.currency != AmountUtils$Currency.TON) goto L32;
            boolean r42 = true;
        L33:
            r24.setText(LocaleController.formatSpannable(r32, new Object[]{StarsIntroActivity.replaceStars(r42, "⭐️ " + StarsIntroActivity.formatStarsAmount(r124.toTl(), 1.0f, ',')), Float.valueOf(0.9f)}));
            TL_stars.starGiftAttributeBackdrop r125 = this.backdrop[0];
            this.resellPriceView.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(12.0f), ColorUtils.blendARGB(r125.edge_color | (-16777216), r125.pattern_color | (-16777216), 0.25f)));
            boolean r126 = StarGiftSheet.isMine(UserConfig.selectedAccount, DialogObject.getPeerDialogId(r9.owner_id));
            TextView r25 = this.resellPriceView;
            if (r126 == false) goto L36;
            r25.setOnClickListener(new StarGiftSheet$TopView$$ExternalSyntheticLambda4(this));
            ScaleStateListAnimator.apply(this.resellPriceView);
            goto L37
        L36:
            r25.setOnClickListener(null);
            ScaleStateListAnimator.reset(this.resellPriceView);
            goto L37
        L32:
            r42 = false;
            goto L33
        L28:
            r123 = AmountUtils$Currency.STARS;
        L37:
            Button[] r127 = this.buttons;
            if (r10 == false) goto L40;
            r127[0].setAlpha(1.0f);
            this.buttons[0].set(R.drawable.filled_gift_transfer, LocaleController.getString(R.string.Gift2ActionTransfer), false);
        L41:
            Button r128 = this.buttons[1];
            if (r10 == true) goto L46;
            if (r11 == true) goto L46;
            r16 = 0.5f;
        L46:
            r128.setAlpha(r16);
            if (r10 == false) goto L52;
            ArrayList<TL_stars.StarsAmount> r102 = r9.resell_amount;
            Button[] r129 = this.buttons;
            if (r102 == null) goto L51;
            r129[2].set(R.drawable.filled_gift_sell_off, LocaleController.getString(R.string.Gift2ActionUnlist), false);
            this.buttons[2].setOnClickListener(this.onResellClick);
        L53:
            this.hasRibbon = r9.crafted;
            this.ribbon.drawable.setBackdrop(this.backdrop[0], false, true);
        L55:
            this.hasLink = r13;
            setBackdropPaint(0, this.backdrop[0]);
            StarsIntroActivity.setGiftImage(this.imageView[0].getImageReceiver(), r9, 160);
            this.imageViewAttributes[0] = (TL_stars.starGiftAttributeModel) StarsController.findAttribute(r9.attributes, TL_stars.starGiftAttributeModel.class);
            onSwitchPage(this.currentPage);
            return;
        L51:
            r129[2].set(R.drawable.filled_gift_sell_on, LocaleController.getString(R.string.Gift2ActionResell), false);
            this.buttons[2].setOnClickListener(this.onResellClick);
            goto L53
        L52:
            this.buttons[2].set(R.drawable.filled_share, LocaleController.getString(R.string.Gift2ActionShare), false);
            this.buttons[2].setOnClickListener(this.onShareClick);
            goto L53
        L40:
            r127[0].setAlpha(0.5f);
            SpannableStringBuilder r1210 = new SpannableStringBuilder("L ");
            r1210.setSpan(new ColoredImageSpan(R.drawable.msg_mini_lock2), 0, 1, 33);
            r1210.append(LocaleController.getString(R.string.Gift2ActionTransfer));
            this.buttons[0].set(R.drawable.filled_gift_transfer, r1210, false);
            goto L41
        L54:
            r3[0] = null;
            setPattern(0, null, false);
            this.subtitleView[0].setTextSize(1, 14.0f);
            this.hasRibbon = false;
            this.buttonsLayout.setVisibility(8);
        L7:
            r1 = true;
            goto L8
        }

        public void setPattern(int r3, TL_stars.starGiftAttributePattern r4, boolean r5) {
            if (r4 == null) goto L8;
            TL_stars.starGiftAttributePattern[] r0 = this.patternAttribute;
            if (r0[r3] == r4) goto L9;
            r0[r3] = r4;
            this.pattern[r3].set(r4.document, r5);
            return;
        L9:
            return;
        }

        public void setPreviewAttributes(StarGiftPreviewSheet.Attributes r8) {
            PageTransition r0 = this.currentPage;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.to == 1) goto L7;
            return;
        L7:
            if (isAttachedToWindow() == false) goto L20;
            AndroidUtilities.cancelRunOnUIThread(this.checkToRotateRunnable);
            ValueAnimator r02 = this.rotationAnimator;
            if (r02 == null) goto L12;
            r02.cancel();
            this.rotationAnimator = null;
        L12:
            int r03 = 1 - this.toggled;
            this.toggled = r03;
            RLottieDrawable r04 = this.imageView[2 - r03].getImageReceiver().getLottieAnimation();
            RLottieDrawable r2 = this.imageView[this.toggled + 1].getImageReceiver().getLottieAnimation();
            if (r2 == null) goto L16;
            if (r04 == null) goto L16;
            r2.setProgress(r04.getProgress(), false);
        L16:
            int r05 = this.toggled;
            TL_stars.starGiftAttributeBackdrop r6 = r8.backdrop;
            this.backdrop[r05 + 1] = r6;
            setBackdropPaint(r05 + 1, r6);
            setPattern(1, r8.pattern, true);
            TL_stars.starGiftAttributeModel[] r06 = this.imageViewAttributes;
            int r22 = this.toggled;
            r06[r22 + 1] = r8.model;
            StarsIntroActivity.setGiftImage(this.imageView[r22 + 1].getImageReceiver(), this.imageViewAttributes[this.toggled + 1].document, 160);
            animateSwitch();
            int r82 = this.toggled;
            ValueAnimator r83 = ValueAnimator.ofFloat(new float[]{1.0f - r82, r82});
            this.rotationAnimator = r83;
            r83.addUpdateListener(new StarGiftSheet$TopView$$ExternalSyntheticLambda3(this));
            this.rotationAnimator.addListener(new AnonymousClass6(this));
            this.rotationAnimator.setDuration(320);
            this.rotationAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.rotationAnimator.start();
            return;
        }

        public void setPreviewingAttributes(ArrayList<TL_stars.StarGiftAttribute> r4) {
            this.sampleAttributes = r4;
            this.models = new BagRandomizer(StarsController.findAttributes(r4, TL_stars.starGiftAttributeModel.class));
            this.patterns = new BagRandomizer(StarsController.findAttributes(r4, TL_stars.starGiftAttributePattern.class));
            this.backdrops = new BagRandomizer(StarsController.findAttributes(r4, TL_stars.starGiftAttributeBackdrop.class));
            this.subtitleView[1].setTextSize(1, 14.0f);
            this.buttonsLayout.setVisibility(8);
            this.toggleBackdrop = 0.0f;
            this.toggled = 0;
            setPattern(1, this.patterns.next(), true);
            this.imageViewAttributes[1] = this.models.next();
            StarsIntroActivity.setGiftImage(this.imageView[1].getImageReceiver(), this.imageViewAttributes[1].document, 160);
            TL_stars.starGiftAttributeBackdrop[] r42 = this.backdrop;
            TL_stars.starGiftAttributeBackdrop r1 = this.backdrops.next();
            r42[1] = r1;
            setBackdropPaint(1, r1);
            this.imageViewAttributes[2] = this.models.getNext();
            StarsIntroActivity.setGiftImage(this.imageView[2].getImageReceiver(), this.imageViewAttributes[2].document, 160);
            AndroidUtilities.cancelRunOnUIThread(this.checkToRotateRunnable);
            AndroidUtilities.runOnUIThread(this.checkToRotateRunnable, 2500);
            invalidate();
        }

        public void setResellPrice(AmountUtils$Amount r12) {
            boolean r0 = r12.isZero();
            this.hasResellPrice = !r0;
            TextView r1 = this.resellPriceView;
            if (r0 == true) goto L9;
            int r02 = R.string.GiftOnSale;
            if (r12.currency != AmountUtils$Currency.TON) goto L7;
            boolean r8 = true;
        L8:
            r1.setText(LocaleController.formatSpannable(r02, new Object[]{StarsIntroActivity.replaceStars(r8, "⭐️ " + StarsIntroActivity.formatStarsAmount(r12.toTl(), 1.0f, ','), 0.9f)}));
            TL_stars.starGiftAttributeBackdrop r122 = this.backdrop[0];
            this.resellPriceView.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(12.0f), ColorUtils.blendARGB(r122.edge_color | (-16777216), r122.pattern_color | (-16777216), 0.25f)));
            this.resellPriceView.setVisibility(0);
            this.resellPriceViewInProgress = true;
            ViewPropertyAnimator r123 = this.resellPriceView.animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setDuration(420);
            CubicBezierInterpolator r03 = CubicBezierInterpolator.EASE_OUT_QUINT;
            r123.setInterpolator(r03).setListener(new AnonymousClass3(this)).start();
            this.subtitleView[0].animate().alpha(0.0f).setDuration(420).setInterpolator(r03).start();
        L10:
            boolean r124 = this.hasResellPrice;
            Button[] r04 = this.buttons;
            if (r124 == false) goto L13;
            r04[2].set(R.drawable.filled_gift_sell_off, LocaleController.getString(R.string.Gift2ActionUnlist), true);
        L14:
            this.buttons[2].setOnClickListener(this.onResellClick);
            return;
        L13:
            r04[2].set(R.drawable.filled_gift_sell_on, LocaleController.getString(R.string.Gift2ActionResell), true);
            goto L14
        L7:
            r8 = false;
            goto L8
        L9:
            ViewPropertyAnimator r125 = r1.animate().scaleX(0.4f).scaleY(0.4f).alpha(0.0f).setDuration(420);
            CubicBezierInterpolator r05 = CubicBezierInterpolator.EASE_OUT_QUINT;
            r125.setInterpolator(r05).setListener(new AnonymousClass5(this)).setListener(new AnonymousClass4(this)).start();
            this.subtitleView[0].animate().alpha(1.0f).setDuration(420).setInterpolator(r05).start();
            goto L10
        }

        public void setText(int r9, CharSequence r10, CharSequence r11, CharSequence r12, CharSequence r13) {
            setText(r9, r10, r11, r12, r13, null, null);
        }

        public void setText(int r3, CharSequence r4, CharSequence r5, CharSequence r6, CharSequence r7, TLObject r8, CharSequence r9) {
            this.titleView[r3].setText(r4);
            int r42 = 0;
            if (r3 == 0) goto L5;
        L10:
            if (r3 == 0) goto L12;
        L17:
            this.subtitleView[r3].setText(r5);
            if (r3 != 0) goto L24;
            FrameLayout r62 = this.subtitleContainer;
            if (TextUtils.isEmpty(r5) == false) goto L22;
            int r52 = 8;
        L23:
            r62.setVisibility(r52);
        L29:
            this.releasedView.setVisibility(8);
            this.collectionReleasedView.setVisibility(8);
        L30:
            GiftMessageView r53 = this.messageTextView[r3];
            if (r53 != null) goto L33;
            return;
        L33:
            if (TextUtils.isEmpty(r9) == false) goto L35;
            r42 = 8;
        L35:
            r53.setVisibility(r42);
            this.messageTextView[r3].setUser(r8);
            this.messageTextView[r3].setMessage(r9);
            return;
        L22:
            r52 = 0;
            goto L23
        L24:
            LinkSpanDrawable.LinksTextView r63 = this.subtitleView[r3];
            if (TextUtils.isEmpty(r5) == false) goto L27;
            int r54 = 8;
        L28:
            r63.setVisibility(r54);
            goto L29
        L27:
            r54 = 0;
            goto L28
        L12:
            if (TextUtils.isEmpty(r7) == true) goto L17;
            this.releasedView.setText(r7);
            this.releasedView.setVisibility(0);
            this.collectionReleasedView.setVisibility(8);
            if (r3 != 0) goto L16;
            this.subtitleContainer.setVisibility(8);
            goto L30
        L16:
            this.subtitleView[r3].setVisibility(8);
            goto L30
        L5:
            if (TextUtils.isEmpty(r6) == true) goto L10;
            this.collectionReleasedView.setText(r6);
            this.collectionReleasedView.setVisibility(0);
            this.releasedView.setVisibility(8);
            if (r3 != 0) goto L9;
            this.subtitleContainer.setVisibility(8);
            goto L30
        L9:
            this.subtitleView[r3].setVisibility(8);
            goto L30
        }

        public void setWearPreview(TLObject r6) {
            this.wearPreviewObject = r6;
            if ((r6 instanceof TLRPC.User) == false) goto L6;
            String r0 = UserObject.getUserName((TLRPC.User) r6);
            String r1 = LocaleController.getString(R.string.Online);
        L16:
            AvatarDrawable r2 = new AvatarDrawable();
            r2.setInfo(r6);
            this.avatarView.setForUserOrChat(r6, r2);
            this.titleView[2].setText(r0);
            this.subtitleView[2].setText(r1);
            updateWearImageTranslation();
            onSwitchPage(this.currentPage);
            return;
        L6:
            if ((r6 instanceof TLRPC.Chat) == false) goto L18;
            TLRPC.Chat r02 = (TLRPC.Chat) r6;
            String r12 = r02.title;
            boolean r22 = ChatObject.isChannelAndNotMegaGroup(r02);
            int r03 = r02.participants_count;
            if (r22 == false) goto L12;
            if (r03 <= 1) goto L11;
            String r04 = LocaleController.formatPluralStringComma("Subscribers", r03);
        L15:
            r1 = r04;
            r0 = r12;
            goto L16
        L11:
            r04 = LocaleController.getString(R.string.DiscussChannel);
            goto L15
        L12:
            if (r03 <= 1) goto L14;
            r04 = LocaleController.formatPluralStringComma("Members", r03);
            goto L15
        L14:
            r04 = LocaleController.getString(R.string.AccDescrGroup).toLowerCase();
            goto L15
        }

        public void updateButtonsBackgrounds(int r1) {
        }
    }

    public static class UpgradeIcon extends CompatDrawable {
        private float alpha;
        private final Path arrow;
        private final long start;
        private final Paint strokePaint;
        private final View view;

        public UpgradeIcon(View r5, int r6) {
            super(r5);
            Paint r0 = new Paint(1);
            this.strokePaint = r0;
            Path r1 = new Path();
            this.arrow = r1;
            this.start = System.currentTimeMillis();
            this.alpha = 1.0f;
            this.view = r5;
            this.paint.setColor(-1);
            r0.setStyle(Paint.Style.STROKE);
            r0.setStrokeJoin(Paint.Join.ROUND);
            r0.setStrokeCap(Paint.Cap.ROUND);
            r0.setColor(r6);
            r1.rewind();
            r1.moveTo(-AndroidUtilities.dpf2(2.91f), AndroidUtilities.dpf2(1.08f));
            r1.lineTo(0.0f, -AndroidUtilities.dpf2(1.08f));
            r1.lineTo(AndroidUtilities.dpf2(2.91f), AndroidUtilities.dpf2(1.08f));
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r10) {
            this.paint.setAlpha((int) (this.alpha * 255.0f));
            r10.drawCircle(getBounds().centerX(), getBounds().centerY(), getBounds().width() / 2.0f, this.paint);
            float r0 = ((System.currentTimeMillis() - this.start) % 400) / 400.0f;
            int r1 = this.strokePaint.getAlpha();
            this.strokePaint.setAlpha((int) (r1 * this.alpha));
            this.strokePaint.setStrokeWidth(AndroidUtilities.dpf2(1.33f));
            r10.save();
            r10.translate(getBounds().centerX(), getBounds().centerY() - (((AndroidUtilities.dpf2(2.16f) * 3.0f) + (AndroidUtilities.dpf2(1.166f) * 2.0f)) / 2.0f));
            int r3 = 0;
        L4:
            if (r3 >= 4) goto L13;
            if (r3 != 0) goto L9;
            float r5 = 1.0f - r0;
        L12:
            this.strokePaint.setAlpha((int) ((r5 * 255.0f) * this.alpha));
            r10.save();
            float r4 = AndroidUtilities.lerp(0.5f, 1.0f, r5);
            r10.scale(r4, r4);
            r10.drawPath(this.arrow, this.strokePaint);
            r10.restore();
            r10.translate(0.0f, AndroidUtilities.dpf2(3.3260002f) * r5);
            r3 = r3 + 1;
            goto L4
        L9:
            if (r3 != 3) goto L11;
            r5 = r0;
            goto L12
        L11:
            r5 = 1.0f;
            goto L12
        L13:
            r10.restore();
            this.strokePaint.setAlpha(r1);
            View r9 = this.view;
            if (r9 == null) goto L21;
            r9.invalidate();
            return;
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return AndroidUtilities.dp(18.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return AndroidUtilities.dp(18.0f);
        }

        @Override // org.telegram.ui.Components.CompatDrawable, android.graphics.drawable.Drawable
        public void setAlpha(int r2) {
            this.alpha = r2 / 255.0f;
        }
    }

    public static final class UpgradePricesSheet extends BottomSheetLayouted {
        private LimitPreviewView limitPreviewView;
        private ArrayList<TL_stars.StarGiftUpgradePrice> prices;

        public static /* synthetic */ void $r8$lambda$lgAlaVtuVE6063PunlyyNaasuWw(UpgradePricesSheet r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public UpgradePricesSheet(Context r21, long r22, ArrayList<TL_stars.StarGiftUpgradePrice> r24, Theme.ResourcesProvider r25) {
            super(r21, r25);
            this.prices = r24;
            float r9 = this.backgroundPaddingLeft / AndroidUtilities.density;
            LimitPreviewView r3 = new LimitPreviewView(getContext(), R.drawable.star, 0, 0, r25);
            this.limitPreviewView = r3;
            float r12 = 14.0f;
            r3.setTranslationY(-AndroidUtilities.dp(14.0f));
            r3.setIconScale(1.8f);
            this.layout.addView(r3, LayoutHelper.createLinear(-1, -2, 17, r9, 20.0f, r9, 10.0f));
            setCurrentPrice(r22);
            int r4 = Theme.key_windowBackgroundWhiteBlackText;
            TextView r5 = TextHelper.makeTextView(r21, 20.0f, r4, true);
            r5.setGravity(17);
            r5.setText(LocaleController.getString(R.string.Gift2UpgradeCostsTitle));
            setTitle(LocaleController.getString(R.string.Gift2UpgradeCostsTitle));
            this.layout.addView(r5, LayoutHelper.createLinear(-1, -2, 17, 32, 0, 32, 0));
            TextView r42 = TextHelper.makeTextView(r21, 14.0f, r4, false);
            r42.setGravity(17);
            r42.setText(LocaleController.getString(R.string.Gift2UpgradeCostsText));
            this.layout.addView(r42, LayoutHelper.createLinear(-1, -2, 17, 32, 10, 32, 10));
            int r43 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
            TableView r92 = new TableView(r21, r25);
            int r32 = 0;
            boolean r10 = false;
        L4:
            if (r32 >= r24.size()) goto L14;
            float r18 = r12;
            if (r43 <= r24.get(r32).date) goto L12;
            int r122 = r32 + 1;
            if (r122 >= r24.size()) goto L13;
            if (r43 <= r24.get(r122).date) goto L12;
        L13:
            r32 = r32 + 1;
            r12 = r18;
        L12:
            Date r7 = new Date(r11.date * 1000);
            r92.addRow(LocaleController.getInstance().getFormatterDay().format(r7) + ", " + LocaleController.getInstance().getFormatterDayMonth().format(r7), StarsIntroActivity.replaceStarsWithPlain("⭐️ " + LocaleController.formatNumber((int) r11.upgrade_stars, ','), 0.8f));
            r10 = true;
            goto L13
        L14:
            float r182 = r12;
            if (r10 == true) goto L19;
            int r33 = r24.size();
            int r44 = 0;
        L17:
            if (r44 >= r33) goto L19;
            TL_stars.StarGiftUpgradePrice r72 = r24.get(r44);
            r44 = r44 + 1;
            TL_stars.StarGiftUpgradePrice r73 = r72;
            Date r102 = new Date(r73.date * 1000);
            r92.addRow(LocaleController.getInstance().getFormatterDay().format(r102) + ", " + LocaleController.getInstance().getFormatterDayMonth().format(r102), StarsIntroActivity.replaceStarsWithPlain("⭐️ " + LocaleController.formatNumber((int) r73.upgrade_stars, ','), 0.8f));
        L19:
            float r13 = r9 + r182;
            this.layout.addView(r92, LayoutHelper.createLinear(-1, -2, 7, r13, 16.0f, r13, 15.0f));
            TextView r1 = TextHelper.makeTextView(r21, 12.0f, Theme.key_windowBackgroundWhiteGrayText, false);
            r1.setGravity(17);
            r1.setText(LocaleController.getString(R.string.Gift2UpgradeCostsFooter));
            this.layout.addView(r1, LayoutHelper.createLinear(-1, -2, 17, 32, 0, 32, 15));
            createButton();
            this.button.setText(StarGiftSheet.replaceUnderstood(LocaleController.getString(R.string.Understood)), false);
            this.button.setOnClickListener(new StarGiftSheet$UpgradePricesSheet$$ExternalSyntheticLambda0(this));
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            dismiss();
        }

        public void setCurrentPrice(long r4) {
            ArrayList<TL_stars.StarGiftUpgradePrice> r0 = this.prices;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.isEmpty() == true) goto L10;
            this.limitPreviewView.setStarsUpgradePrice(this.prices.get(0), r4, this.prices.get(r1.size() - 1));
            return;
        }
    }

    public static class UserToUserTransferTopView extends View {
        private final Paint arrowPaint;
        private final Path arrowPath;
        private final ImageReceiver fromUserImageReceiver;
        private final ImageReceiver toUserImageReceiver;

        public UserToUserTransferTopView(Context r6, TLObject r7, TLObject r8) {
            super(r6);
            Path r62 = new Path();
            this.arrowPath = r62;
            Paint r0 = new Paint(1);
            this.arrowPaint = r0;
            AvatarDrawable r1 = new AvatarDrawable();
            r1.setInfo(r7);
            ImageReceiver r2 = new ImageReceiver(this);
            this.fromUserImageReceiver = r2;
            r2.setRoundRadius(AndroidUtilities.dp(30.0f));
            r2.setForUserOrChat(r7, r1);
            AvatarDrawable r72 = new AvatarDrawable();
            r72.setInfo(r8);
            ImageReceiver r12 = new ImageReceiver(this);
            this.toUserImageReceiver = r12;
            r12.setRoundRadius(AndroidUtilities.dp(30.0f));
            r12.setForUserOrChat(r8, r72);
            r0.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText7));
            r0.setStyle(Paint.Style.STROKE);
            r0.setStrokeCap(Paint.Cap.ROUND);
            r0.setStrokeJoin(Paint.Join.ROUND);
            r0.setStrokeWidth(AndroidUtilities.dp(2.0f));
            r62.rewind();
            r62.moveTo(0.0f, -AndroidUtilities.dp(8.0f));
            r62.lineTo(AndroidUtilities.dp(6.166f), 0.0f);
            r62.lineTo(0.0f, AndroidUtilities.dp(8.0f));
        }

        @Override // android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.fromUserImageReceiver.onAttachedToWindow();
            this.toUserImageReceiver.onAttachedToWindow();
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.fromUserImageReceiver.onDetachedFromWindow();
            this.toUserImageReceiver.onDetachedFromWindow();
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            int r1 = (getWidth() / 2) - (AndroidUtilities.dp(156.0f) / 2);
            float r0 = (getHeight() / 2) - AndroidUtilities.dp(30.0f);
            this.fromUserImageReceiver.setImageCoords(r1, r0, AndroidUtilities.dp(60.0f), AndroidUtilities.dp(60.0f));
            this.fromUserImageReceiver.draw(r8);
            r8.save();
            r8.translate((getWidth() / 2.0f) - (AndroidUtilities.dp(6.166f) / 2.0f), getHeight() / 2.0f);
            r8.drawPath(this.arrowPath, this.arrowPaint);
            r8.restore();
            this.toUserImageReceiver.setImageCoords(r1 + AndroidUtilities.dp(96.0f), r0, AndroidUtilities.dp(60.0f), AndroidUtilities.dp(60.0f));
            this.toUserImageReceiver.draw(r8);
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(100.0f), TLObject.FLAG_30));
        }
    }

    /* renamed from: $r8$lambda$-WebsMLMNB3mK0jzfbI4brOLKCs, reason: not valid java name */
    public static /* synthetic */ void m18943$r8$lambda$WebsMLMNB3mK0jzfbI4brOLKCs(StarGiftSheet r0, int r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$repollMessage$97(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$-g1H1ElEzGIWSLQcwXtoguWD11Y, reason: not valid java name */
    public static /* synthetic */ void m18944$r8$lambda$g1H1ElEzGIWSLQcwXtoguWD11Y(StarGiftSheet r0, TL_stars.starGiftUpgradePreview r1) {
        r0.lambda$openUpgrade$116(r1);
    }

    /* renamed from: $r8$lambda$-sok17keG7v-CwZ-tlIburuEWiQ, reason: not valid java name */
    public static /* synthetic */ void m18945$r8$lambda$sok17keG7vCwZtlIburuEWiQ(StarGiftSheet r0, TL_stars.TL_starGiftUnique r1, AmountUtils$Amount r2, Runnable r3) {
        r0.lambda$onUpdatePriceClick$29(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$01fWu2eypkc-1X0YSS2VddakPf4, reason: not valid java name */
    public static /* synthetic */ void m18946$r8$lambda$01fWu2eypkc1X0YSS2VddakPf4(StarGiftSheet r0, AlertDialog r1, TLObject r2, TLRPC.Document r3, String r4, String r5, String r6, long r7, TLRPC.TL_error r9) {
        r0.lambda$openValueStats$183(r1, r2, r3, r4, r5, r6, r7, r9);
    }

    public static /* synthetic */ void $r8$lambda$0Ejm_KjCg5_mAZg7J8o8yGkb21Y(StarGiftSheet r0) {
        r0.lambda$onMenuPressed$12();
    }

    public static /* synthetic */ void $r8$lambda$0fDttvAPr1bxZPgOZ1F5c8zdZqM(StarGiftSheet r0, long r1) {
        r0.lambda$set$88(r1);
    }

    public static /* synthetic */ void $r8$lambda$0mK7LcDXcHHgYQ8uttwY3q4iqrk(Utilities.Callback r0, TL_stars.StarGift r1) {
        r0.run(r1);
    }

    public static /* synthetic */ void $r8$lambda$0r2fGn1ki4R1G_0UzOT8eZ4I05M(AlertDialog r0, int r1) {
    }

    public static /* synthetic */ void $r8$lambda$1Sq5Usp2MelfAqjLC_ZiLsQo3FM(StarGiftSheet r0, TL_stars.TL_starGiftUnique r1, AmountUtils$Amount r2, Runnable r3) {
        r0.lambda$onResellPressed$40(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$1agnP5LYR4s0g8EfmpReqPOwVRg(StarGiftSheet r0, Utilities.Callback r1, TLRPC.TL_error r2, TLObject r3, long r4, long r6) {
        r0.lambda$doTransfer$157(r1, r2, r3, r4, r6);
    }

    /* renamed from: $r8$lambda$1rDG65Nzmoa7WpqH3u3l0RV-W8E, reason: not valid java name */
    public static /* synthetic */ void m18947$r8$lambda$1rDG65Nzmoa7WpqH3u3l0RVW8E(StarGiftSheet r0) {
        r0.lambda$onMenuPressed$11();
    }

    public static /* synthetic */ void $r8$lambda$2RPhuV37byWjgYq2dbwAcJmE6Gg(HintView2 r0) {
        AndroidUtilities.removeFromParent(r0);
    }

    public static /* synthetic */ void $r8$lambda$2ceGCNsCKl01JuI2OL3XsmJbTxY(StarGiftSheet r0, Long r1, UserSelectorBottomSheet[] r2, Browser.Progress r3) {
        r0.lambda$openTransfer$143(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$2dzLBBrNEJmgJzOPhf7Q5ugCcU8(StarGiftSheet r0) {
        r0.lambda$onMenuPressed$14();
    }

    public static /* synthetic */ void $r8$lambda$3SPhKaGRhjQZ9e8c7bCBoz3OSm8(StarGiftSheet r0, TLRPC.TL_inputInvoiceStarGiftTransfer r1, long r2, long r4, Utilities.Callback r6, TLObject r7, TLRPC.TL_error r8) {
        r0.lambda$doTransfer$168(r1, r2, r4, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$3juKIihAClDMucRjIbJNPl0LTAw(StarGiftSheet r0, int r1, View r2) {
        r0.lambda$set$77(r1, r2);
    }

    /* renamed from: $r8$lambda$44XUSD-oU9CWo-zacFzRofy2gyM, reason: not valid java name */
    public static /* synthetic */ void m18948$r8$lambda$44XUSDoU9CWozacFzRofy2gyM(StarGiftSheet r0) {
        r0.lambda$onMenuPressed$10();
    }

    /* renamed from: $r8$lambda$4Y3YM-pCDqdUJ31OJerdRyMT5sk, reason: not valid java name */
    public static /* synthetic */ void m18949$r8$lambda$4Y3YMpCDqdUJ31OJerdRyMT5sk(StarGiftSheet r0, Utilities.Callback2 r1, ArrayList r2, Runnable r3, TLRPC.Updates r4, TLRPC.TL_error r5) {
        r0.lambda$openCrafting$4(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$4axmOVsl2Vv4lNZTVIxqYAmo_4o(StarGiftSheet r0, Boolean r1) {
        r0.lambda$openValueStats$180(r1);
    }

    public static /* synthetic */ void $r8$lambda$4ntT3Xffr1yTR0yGMp3Hkph9L1o(StarGiftSheet r0, ValueAnimator r1) {
        r0.lambda$switchPage$43(r1);
    }

    public static /* synthetic */ void $r8$lambda$4uTUiVjyWOqRbsZLkpp09rtKDmY(StarGiftSheet r0, TLRPC.TL_error r1, TLObject r2, TL_stars.InputSavedStarGift r3) {
        r0.lambda$doUpgrade$125(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$59MFGI6n3tumKoiuM3M8Alhuo90(StarGiftSheet r0, View r1) {
        r0.lambda$set$91(r1);
    }

    public static /* synthetic */ void $r8$lambda$5cKz0KFfETxtKHuZ3mips1A5PsM(StarGiftSheet r0, HintView2[] r1, FrameLayout r2, View r3, CharSequence r4) {
        r0.lambda$openValueStats$175(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$5pFkyhXQRHitZEyZCcgU-4pOLak, reason: not valid java name */
    public static /* synthetic */ void m18950$r8$lambda$5pFkyhXQRHitZEyZCcgU4pOLak(StarGiftSheet r0, AlertDialog r1, TLRPC.Document r2, String r3, String r4, String r5, long r6, TLObject r8, TLRPC.TL_error r9) {
        r0.lambda$openValueStats$184(r1, r2, r3, r4, r5, r6, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$5r00nOIv2gp2xaTwal2qxjQyN1k(StarGiftSheet r0, boolean r1, TLRPC.Document r2, boolean r3, TL_stars.saveStarGift r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$toggleShow$108(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$5vgS5xE_avPSJQ4wzFc_nU4eoeo(StarGiftSheet r0, TL_stars.TL_starGiftUnique r1, TLRPC.PaymentForm r2, TLRPC.TL_inputInvoiceStarGiftDropOriginalDetails r3, long r4, CharSequence r6, AlertDialog r7, int r8) {
        r0.lambda$showDeleteDescriptionAlert$70(r1, r2, r3, r4, r6, r7, r8);
    }

    /* renamed from: $r8$lambda$5z-BIt4zEZYPIoxwbhNgVs3XsxI, reason: not valid java name */
    public static /* synthetic */ void m18951$r8$lambda$5zBIt4zEZYPIoxwbhNgVs3XsxI(StarGiftSheet r0, TLObject r1, TLRPC.TL_inputInvoiceStarGiftTransfer r2, long r3, long r5, Utilities.Callback r7, TLRPC.TL_error r8) {
        r0.lambda$doTransfer$167(r1, r2, r3, r5, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$62WyC7LnwRWNZsPZJ6MhiDk2kp8(StarGiftSheet r0, TLObject r1, String r2, TL_stars.InputSavedStarGift r3, TLRPC.TL_error r4, long r5) {
        r0.lambda$doUpgrade$135(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$6hCTOe8ypNxz4-wmR9wQjVAXKWo, reason: not valid java name */
    public static /* synthetic */ void m18952$r8$lambda$6hCTOe8ypNxz4wmR9wQjVAXKWo(StarGiftSheet r0) {
        r0.lambda$set$73();
    }

    public static /* synthetic */ void $r8$lambda$6nQhgsw7Je4rv8uIASlH3RTmazo(StarGiftSheet r0, TL_stars.TL_starGiftUnique r1, AmountUtils$Amount r2, Runnable r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$onUpdatePriceClick$28(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$6wRL0_QETb_-W7PDjwsaqC39U_w, reason: not valid java name */
    public static /* synthetic */ void m18953$r8$lambda$6wRL0_QETb_W7PDjwsaqC39U_w(StarGiftSheet r0, String r1, TL_stars.starGiftUpgradePreview r2) {
        r0.lambda$openAsLearnMore$100(r1, r2);
    }

    /* renamed from: $r8$lambda$7-s39HguwETvO7pQ2MGn2R7t7TQ, reason: not valid java name */
    public static /* synthetic */ void m18954$r8$lambda$7s39HguwETvO7pQ2MGn2R7t7TQ(HintView2 r0) {
        AndroidUtilities.removeFromParent(r0);
    }

    public static /* synthetic */ void $r8$lambda$7OOhbCSCCH_Fg0JvRwWC5nucVEk(StarGiftSheet r0, Browser.Progress r1, TL_stars.TL_starGiftUnique r2) {
        r0.lambda$onResellPressed$30(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$7UEULjL5RtR1VVDLNoEMxOiLScQ(StarGiftSheet r0, int r1, int r2, int r3, TL_stars.TL_starGiftUnique r4, UserSelectorBottomSheet[] r5, Long r6) {
        r0.lambda$openTransfer$149(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$7WoW_nqBKImppdIEQSazA-gLKfQ, reason: not valid java name */
    public static /* synthetic */ void m18955$r8$lambda$7WoW_nqBKImppdIEQSazAgLKfQ(StarGiftSheet r0, TLObject r1, MessageObject r2) {
        r0.lambda$repollMessage$96(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$7k7hiYrp6kqiEmzvArlccl4BzJo(StarGiftSheet r0, TwoStepVerificationActivity r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$initTONTransfer$155(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$7l0OpEkGiHsKGGmP_vjxEBPYvBM(StarGiftSheet r0, View r1) {
        r0.onWearPressed(r1);
    }

    public static /* synthetic */ void $r8$lambda$8D6ZkBEbMNrHjfeKHm82V98SCJM(StatisticActivity r3, long r4) {
        BulletinFactory.of(r3).createSimpleBulletin(R.raw.stars_topup, LocaleController.getString(R.string.Gift2ConvertedTitle), LocaleController.formatPluralStringComma("Gift2ConvertedChannel", (int) r4)).show(true);
    }

    public static /* synthetic */ void $r8$lambda$8YSaUCeu6TLKb82uj2m4y1xxMkU(StarGiftSheet r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$8_y3XnSOa4QRATd329f9a_bRmsY(StarGiftSheet r0, GiftMessageBottomSheet r1, TL_stars.TL_starGiftUnique r2, long r3, AmountUtils$Currency r5, TLRPC.TL_textWithEntities r6, boolean r7) {
        r0.lambda$onBuyPressed$169(r1, r2, r3, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$90RQCNpa3M_2rMpsT3qMPEwgRhU(StarGiftSheet r0, View r1) {
        r0.lambda$set$92(r1);
    }

    public static /* synthetic */ void $r8$lambda$9mfNHp7QzCNfWPz2nDSekahzXpo(StarGiftSheet r0, View r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$ALufsBg7lytvu3N16Bhq08rowb8(StarGiftSheet r0, AlertDialog r1, TLObject r2, long r3, long r5, long r7, TLRPC.TL_error r9) {
        r0.lambda$convert$103(r1, r2, r3, r5, r7, r9);
    }

    public static /* synthetic */ void $r8$lambda$BBpX_lX2vlSaU0rgg6o_sYX3jiY(StarGiftSheet r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$openUpgrade$118(r1, r2);
    }

    /* renamed from: $r8$lambda$BGZnTnWcomDAmn-RH-MWWjo-3LY, reason: not valid java name */
    public static /* synthetic */ void m18956$r8$lambda$BGZnTnWcomDAmnRHMWWjo3LY(StarGiftSheet r0, View r1) {
        r0.lambda$set$76(r1);
    }

    public static /* synthetic */ void $r8$lambda$DZvqIYYy0R9Et3Ke8cnYURaMhdo(StarGiftSheet r0, long r1, Runnable r3) {
        r0.lambda$onResellPressed$37(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$Dbym5fCyoqEjHaSltgXJ1D1NbjE(StarGiftSheet r0, TLObject r1, CharSequence r2, TL_stars.TL_starGiftUnique r3, TLRPC.TL_inputInvoiceStarGiftDropOriginalDetails r4, TLRPC.TL_error r5) {
        r0.lambda$showDeleteDescriptionAlert$71(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$EbHhUeFoRDF86ozyBvinuwXAnoc(StarGiftSheet r0, Long r1, UserSelectorBottomSheet[] r2) {
        r0.lambda$openTransfer$144(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$EjbO5LeHL5XhE9fJvi5FwJEunds(StarGiftSheet r0, long r1) {
        r0.lambda$set$52(r1);
    }

    public static /* synthetic */ void $r8$lambda$Evvi0crpSRFT_UCjYNmzRLJJIYo(StarGiftSheet r0, Browser.Progress r1, TLRPC.TL_error r2) {
        r0.lambda$onResellPressed$32(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$F7kFjtNuATnlr5Z7HFKsV0nKSqw(StarGiftSheet r0, String r1) {
        r0.lambda$set$81(r1);
    }

    public static /* synthetic */ void $r8$lambda$FF1l4mRLtLXFPOUAAi2qmtMO5FQ(StarGiftSheet r0, Browser.Progress r1, long r2) {
        r0.lambda$onResellPressed$31(r1, r2);
    }

    /* renamed from: $r8$lambda$FVo9ESS-E3gOp11hY6ACxtRrJDA, reason: not valid java name */
    public static /* synthetic */ void m18957$r8$lambda$FVo9ESSE3gOp11hY6ACxtRrJDA(StarGiftSheet r0, boolean[] r1, TL_stars.StarGiftAttribute r2, ButtonSpan.TextViewButtons[] r3) {
        r0.lambda$addAttributeRow$45(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Fjk2htt3Z2vEwB7sj9g7MFVjIOQ(StarGiftSheet r0) {
        r0.lambda$set$82();
    }

    public static /* synthetic */ void $r8$lambda$FjufJDtQSJwHyWpGB1F33pNEgR4(StarsIntroActivity r3, long r4) {
        BulletinFactory.of(r3).createSimpleBulletin(R.raw.stars_topup, LocaleController.getString(R.string.Gift2ConvertedTitle), LocaleController.formatPluralStringComma("Gift2Converted", (int) r4)).show(true);
    }

    /* renamed from: $r8$lambda$FuZaZ-9SYPYTcWw-arIo3P7fTmc, reason: not valid java name */
    public static /* synthetic */ void m18958$r8$lambda$FuZaZ9SYPYTcWwarIo3P7fTmc(StarGiftSheet r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$GLfeManhcb6nzq1Ng7nQnufljks(StarGiftSheet r0, TL_stars.TL_starGiftUnique r1, String r2) {
        r0.lambda$set$55(r1, r2);
    }

    /* renamed from: $r8$lambda$HLU1pYUVL3MK23HqpmRTNh78-7s, reason: not valid java name */
    public static /* synthetic */ void m18959$r8$lambda$HLU1pYUVL3MK23HqpmRTNh787s(StarGiftSheet r0, String r1) {
        r0.lambda$onMenuPressed$13(r1);
    }

    /* renamed from: $r8$lambda$HaST1O5x-F2EKQJq4DS7nSu51DE, reason: not valid java name */
    public static /* synthetic */ void m18960$r8$lambda$HaST1O5xF2EKQJq4DS7nSu51DE(Utilities.Callback2 r4, ButtonSpan.TextViewButtons[] r5, TL_stars.UniqueStarGiftValueInfo r6, String r7) {
        r4.run(r5[0], LocaleController.formatString(R.string.GiftValueMinPriceInfo, new Object[]{BillingController.getInstance().formatCurrency(r6.floor_price, r6.currency), r7}));
    }

    public static /* synthetic */ void $r8$lambda$I0YH4K0i0hX53Ol078yA7dtk9Zc(StarGiftSheet r0, boolean[] r1, long r2, Utilities.Callback r4) {
        r0.lambda$doTransfer$162(r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$IdR5fiIYLuvbMJDv17mEsUXqV6Q(StarGiftSheet r0, TL_stars.TL_starGiftUnique r1) {
        r0.lambda$showDeleteDescriptionAlert$66(r1);
    }

    public static /* synthetic */ void $r8$lambda$IooeQ70zpOGyAmLavPvmC_O_j_w(StarGiftSheet r0, View r1) {
        r0.lambda$openCraftInfo$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$Iq97aI1jSep5a4MaC4h4nJ0MDWE(StarGiftSheet r0, long r1) {
        r0.lambda$doUpgrade$134(r1);
    }

    public static /* synthetic */ void $r8$lambda$Iup6TGb4Wgo_tDJhcByYatGou2c(StarGiftSheet r0, TLRPC.TL_error r1, TLObject r2, TwoStepVerificationActivity r3) {
        r0.lambda$initTONTransfer$152(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$J2LGJ7TG1JWvv5wTLQRZZ1r46-Y, reason: not valid java name */
    public static /* synthetic */ void m18961$r8$lambda$J2LGJ7TG1JWvv5wTLQRZZ1r46Y(StarGiftSheet r0, String r1, long r2, View r4) {
        r0.lambda$openValueStats$181(r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$JBgyQlILhWh6tNoyV9TXcF0COoA(StarGiftSheet r0) {
        r0.lambda$toggleWear$20();
    }

    public static /* synthetic */ void $r8$lambda$JG7RFCZaSsOXYRref3ZshUzPldg(AlertDialog r1, TLRPC.TL_error r2) {
        r1.dismiss();
        BaseFragment r12 = LaunchActivity.getSafeLastFragment();
        if (r12 == null) goto L11;
        if (r2 != null) goto L6;
    L9:
        BulletinFactory.of(r12).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.UniqueGiftNotFound)).show();
        return;
    L6:
        if ("STARGIFT_ALREADY_BURNED".equalsIgnoreCase(r2.text) == false) goto L9;
        BulletinFactory.of(r12).createSimpleBulletin(R.raw.fire_on, LocaleController.getString(R.string.UniqueGiftNotFoundBurned)).show();
        return;
    }

    public static /* synthetic */ void $r8$lambda$JOGJlNvDOcG5MEzUO9gchaXhJ6A(StarGiftSheet r0, TLRPC.TL_error r1, Runnable r2) {
        r0.lambda$onUpdatePriceClick$27(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$JWapmBzEpJfh9IZ0UfsdmbpaMBA(StarGiftSheet r0) {
        r0.lambda$set$63();
    }

    public static /* synthetic */ void $r8$lambda$JfX7tTnfEEt3S8_4cw3CSyCefaQ(StarGiftSheet r0, DialogInterface r1) {
        r0.lambda$doUpgrade$133(r1);
    }

    public static /* synthetic */ void $r8$lambda$KdMtrmyXGHCM69WIqGGS9B_23Ts(StarGiftSheet r0, long r1, long r3, Utilities.Callback r5) {
        r0.lambda$doTransfer$164(r1, r3, r5);
    }

    /* renamed from: $r8$lambda$LYCuPpF3wEQW-tQZgEZXWlOnres, reason: not valid java name */
    public static /* synthetic */ void m18962$r8$lambda$LYCuPpF3wEQWtQZgEZXWlOnres(StarGiftSheet r0) {
        r0.lambda$set$51();
    }

    /* renamed from: $r8$lambda$N9JvoH8DduA3Q2oYHMOYt7O-8vU, reason: not valid java name */
    public static /* synthetic */ void m18963$r8$lambda$N9JvoH8DduA3Q2oYHMOYt7O8vU(StarGiftSheet r0, int r1, View r2) {
        r0.lambda$set$90(r1, r2);
    }

    /* renamed from: $r8$lambda$OD5QriZQu-EzahIluWpkqJHz6yA, reason: not valid java name */
    public static /* synthetic */ void m18964$r8$lambda$OD5QriZQuEzahIluWpkqJHz6yA(StarGiftSheet r0, View r1) {
        r0.onMenuPressed(r1);
    }

    /* renamed from: $r8$lambda$ONTNKNivyrBMVR7bU-4UplgAV6U, reason: not valid java name */
    public static /* synthetic */ void m18965$r8$lambda$ONTNKNivyrBMVR7bU4UplgAV6U(StarGiftSheet r0, String r1) {
        r0.lambda$set$47(r1);
    }

    /* renamed from: $r8$lambda$OPXPd-j3YuMQZ09Yji7sylBlFg0, reason: not valid java name */
    public static /* synthetic */ void m18966$r8$lambda$OPXPdj3YuMQZ09Yji7sylBlFg0(StarGiftSheet r0, UserSelectorBottomSheet[] r1, AlertDialog r2, int r3) {
        r0.lambda$openTransfer$140(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$OU6LQBAQtY3tL8q6XIHgSf_80CM(StarGiftSheet r0, DialogInterface r1) {
        r0.lambda$doTransfer$163(r1);
    }

    public static /* synthetic */ void $r8$lambda$OYKHh887laM9juGgciFimCpmql0(StarGiftSheet r0) {
        r0.openUpgradePrices();
    }

    public static /* synthetic */ void $r8$lambda$O_JmcfMeo_kCQrE1Ne2E0or6hn8(StarGiftSheet r0, String r1) {
        r0.lambda$doUpgrade$130(r1);
    }

    /* renamed from: $r8$lambda$OjUNpebL-Mi3oD5yfjYTaA9iX2s, reason: not valid java name */
    public static /* synthetic */ void m18967$r8$lambda$OjUNpebLMi3oD5yfjYTaA9iX2s(StarGiftSheet r0) {
        r0.lambda$set$62();
    }

    public static /* synthetic */ void $r8$lambda$OvrkOinGoax7vNYBCnK1xV4OyFs(StarGiftSheet r0, TwoStepVerificationActivity r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$initTONTransfer$153(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$P50xZr5AEV3W0icZf2MdvsyeYd4(StarGiftSheet r0) {
        r0.showGiftOfferSheet();
    }

    public static /* synthetic */ void $r8$lambda$P5g0mcXi37JPhT9OmjVm8FgTKdg(StarGiftSheet r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$openUpgrade$117(r1, r2);
    }

    /* renamed from: $r8$lambda$PLDCZ-DtPDRkVzx0pIPIrhdagIg, reason: not valid java name */
    public static /* synthetic */ void m18968$r8$lambda$PLDCZDtPDRkVzx0pIPIrhdagIg(StarGiftSheet r0, AlertDialog r1, long r2, long r4, long r6, TLObject r8, TLRPC.TL_error r9) {
        r0.lambda$convert$104(r1, r2, r4, r6, r8, r9);
    }

    /* renamed from: $r8$lambda$PN-5dsfJtBOlZr377qMLHRI1O3E, reason: not valid java name */
    public static /* synthetic */ void m18969$r8$lambda$PN5dsfJtBOlZr377qMLHRI1O3E(StarGiftSheet r0, ChatActivity r1, long r2) {
        r0.lambda$doTransfer$160(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$PygpDaCh9zOr7_8vcjvb07j4MvQ(StarGiftSheet r0, AlertDialog r1, MessageObject r2) {
        r0.lambda$show$112(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Pzf40dhix8PNjvth15kDHoawvVE(StarGiftSheet r0, TL_stars.InputSavedStarGift r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$doUpgrade$126(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$QAi8yZUyZ4DkvT1ggdah07LvOwk(StarGiftSheet r0, TL_stars.TL_starGiftUnique r1, AmountUtils$Amount r2, Runnable r3) {
        r0.lambda$onResellPressed$36(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$R5GDL_h5GbIXvUzw2MJSEL2474U(StarGiftSheet r0, TL_stars.TL_payments_uniqueStarGift r1) {
        r0.lambda$show$109(r1);
    }

    public static /* synthetic */ void $r8$lambda$RmxMXeJtsIBY1P7SHFvzKkflFwY(StarGiftSheet r0, TLObject r1, boolean r2, TLRPC.Document r3, boolean r4, TLRPC.TL_error r5, TL_stars.saveStarGift r6) {
        r0.lambda$toggleShow$107(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$S8EEemYNdxt2FFEZ_nB21q9VfS0(StarGiftSheet r0) {
        r0.toggleShow();
    }

    public static /* synthetic */ void $r8$lambda$SYW2e2GfVd4T71XDQr9jjMwZmGQ(StarGiftSheet r0, Long r1) {
        r0.lambda$repostStory$41(r1);
    }

    /* renamed from: $r8$lambda$SgHMiTmzzmx2QScq6KTokYA-BGg, reason: not valid java name */
    public static /* synthetic */ void m18970$r8$lambda$SgHMiTmzzmx2QScq6KTokYABGg(StarGiftSheet r0, TLObject r1) {
        r0.lambda$doUpgrade$124(r1);
    }

    /* renamed from: $r8$lambda$THUr5-w43ZLQR_g_eiskbjT4a2E, reason: not valid java name */
    public static /* synthetic */ void m18971$r8$lambda$THUr5w43ZLQR_g_eiskbjT4a2E(StarGiftSheet r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$tickUpgradePrice$122(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$U9XrMbh9k9_uWtDMuPzM1Ae0YgI(StarGiftSheet r0, CharSequence r1, TL_stars.TL_starGiftUnique r2, TLRPC.TL_inputInvoiceStarGiftDropOriginalDetails r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$showDeleteDescriptionAlert$72(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$UHbIxmXQY0Wx2kROoMPHHMRLq2k(StarGiftSheet r0, String r1) {
        r0.lambda$set$80(r1);
    }

    public static /* synthetic */ void $r8$lambda$UIJsbD8nrYlKbTUK0JRHjhV9jhE(StarGiftSheet r0, UserSelectorBottomSheet[] r1, Browser.Progress r2, TwoStepVerificationActivity r3) {
        r0.lambda$openTransfer$139(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$URuUHGoHClRYnY6z-HyYPCkTqDo, reason: not valid java name */
    public static /* synthetic */ void m18972$r8$lambda$URuUHGoHClRYnY6zHyYPCkTqDo(StarGiftSheet r0, String r1, TL_stars.InputSavedStarGift r2, long r3, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$doUpgrade$136(r1, r2, r3, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$UWv3d1vz0EAshi_2s8XfUycf6UU(StarGiftSheet r0, String r1) {
        r0.lambda$set$95(r1);
    }

    /* renamed from: $r8$lambda$UmIEBmyKIqLTFWo-52oeXB_D8zo, reason: not valid java name */
    public static /* synthetic */ void m18973$r8$lambda$UmIEBmyKIqLTFWo52oeXB_D8zo(StarGiftSheet r0, TL_stars.TL_starGiftUnique r1, AmountUtils$Amount r2, Runnable r3) {
        r0.lambda$onUpdatePriceClick$26(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$VCHat0UuXgDHi7UIlGxVOMX82BY(StarGiftSheet r0, TL_stars.TL_starGiftUnique r1, Utilities.Callback r2, Boolean r3) {
        r0.lambda$openCrafting$7(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$VUo9_44WPzhd8PfDz5nWs0pC-Cg, reason: not valid java name */
    public static /* synthetic */ void m18974$r8$lambda$VUo9_44WPzhd8PfDz5nWs0pCCg(StarGiftSheet r0, AlertDialog r1, TL_stars.SavedStarGift r2) {
        r0.lambda$show$115(r1, r2);
    }

    /* renamed from: $r8$lambda$ViLFGviVca-T6Z8tcMvKZ1KMOAU, reason: not valid java name */
    public static /* synthetic */ void m18975$r8$lambda$ViLFGviVcaT6Z8tcMvKZ1KMOAU(StarGiftSheet r0, Utilities.Callback r1, long r2, long r4, TLObject r6, TLRPC.TL_error r7) {
        r0.lambda$doTransfer$158(r1, r2, r4, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$WCVM_hkSDigx_Skkb5teptShORQ(StarGiftSheet r0, long r1) {
        r0.lambda$set$85(r1);
    }

    /* renamed from: $r8$lambda$X-l2zeLlw4fFxlGm4074ssy58p4, reason: not valid java name */
    public static /* synthetic */ void m18976$r8$lambda$Xl2zeLlw4fFxlGm4074ssy58p4(StarGiftSheet r0, Browser.Progress r1, AlertDialog r2, TL_stars.TL_starGiftUnique r3, long r4, CharSequence r6, TLObject r7, TLRPC.TL_error r8) {
        r0.lambda$showDeleteDescriptionAlert$69(r1, r2, r3, r4, r6, r7, r8);
    }

    /* renamed from: $r8$lambda$X1hgfpb3Sa3Oy-7nBaBEXOnsPe4, reason: not valid java name */
    public static /* synthetic */ void m18977$r8$lambda$X1hgfpb3Sa3Oy7nBaBEXOnsPe4(StarGiftSheet r0, View r1) {
        r0.lambda$onWearPressed$18(r1);
    }

    public static /* synthetic */ void $r8$lambda$XAzrChbsfq5HonR0Rvv9sqwx8RU(StarGiftSheet r0, boolean r1) {
        r0.lambda$toggleWear$24(r1);
    }

    /* renamed from: $r8$lambda$XFpGoTxJZwOALE-vTikdkWwWcDo, reason: not valid java name */
    public static /* synthetic */ void m18978$r8$lambda$XFpGoTxJZwOALEvTikdkWwWcDo(Runnable r0, View r1) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$Xn_US33YrpoURtAUqw3awCJWTvA(StarGiftSheet r0, TL_stars.TL_starGiftUnique r1, long r2, TLRPC.TL_textWithEntities r4, boolean r5, GiftMessageBottomSheet r6, PaymentFormState r7, Browser.Progress r8) {
        r0.lambda$performBuyPressed$171(r1, r2, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$YSDXEtGR_wfucl4iywAHmyE7JOE(StarGiftSheet r0, TLObject r1, Runnable r2, TLRPC.TL_error r3) {
        r0.lambda$openTransfer$145(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$YVQinnyQg1OlUdWd3ZJP1VzXVUo(StarGiftSheet r0, TLRPC.TL_payments_paymentResult r1) {
        r0.lambda$doTransfer$161(r1);
    }

    /* renamed from: $r8$lambda$YhxAsUhy60ELOXg_4-Dr5rUKaCo, reason: not valid java name */
    public static /* synthetic */ void m18979$r8$lambda$YhxAsUhy60ELOXg_4Dr5rUKaCo(StarGiftSheet r0, View r1) {
        r0.lambda$set$78(r1);
    }

    /* renamed from: $r8$lambda$ZEOmEmnQJC44rDNJETEmo8l2-1Y, reason: not valid java name */
    public static /* synthetic */ void m18980$r8$lambda$ZEOmEmnQJC44rDNJETEmo8l21Y(StarGiftSheet r0, TL_stars.UniqueStarGiftValueInfo r1, View r2) {
        r0.lambda$openValueStats$182(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$Zjtfq5CPZsYHnXpGy7nbos107zc(StarGiftSheet r0, TL_stars.TL_starGiftUnique r1, DialogsActivity r2, DialogsActivity r3, ArrayList r4, CharSequence r5, boolean r6, boolean r7, int r8, int r9, TopicsFragment r10) {
        return r0.lambda$openSetAsTheme$17(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    /* renamed from: $r8$lambda$ZrnnnURQOdZQ1-wEK9YIF8SJsjw, reason: not valid java name */
    public static /* synthetic */ void m18981$r8$lambda$ZrnnnURQOdZQ1wEK9YIF8SJsjw(StarGiftSheet r0, String r1) {
        r0.lambda$set$94(r1);
    }

    public static /* synthetic */ void $r8$lambda$ZsnOHEK7PfKsb9rjGhUSNedUtbY(StarGiftSheet r0, boolean[] r1) {
        r0.lambda$doUpgrade$132(r1);
    }

    public static /* synthetic */ void $r8$lambda$ZwigORoSg0NJZoDl3ydZm_vVGZE(StarGiftSheet r0, MessagesController r1, long r2, TL_stories.TL_premium_boostsStatus r4) {
        r0.lambda$toggleWear$23(r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$_GA3Otgo4e1rax6A4PxpmvnReYk(long r3, BaseFragment r5) {
        Bundle r0 = new Bundle();
        if (r3 < 0) goto L5;
        r0.putLong("user_id", r3);
    L6:
        r0.putBoolean("my_profile", true);
        r0.putBoolean("open_gifts", true);
        r5.presentFragment(new ProfileActivity(r0));
        return;
    L5:
        r0.putLong("chat_id", -r3);
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$_mvZOF4hgPdEZnd1eKFkeBb1GnU(StarGiftSheet r0, MessageObject r1, ArrayList r2, TL_stars.StarGift r3) {
        r0.lambda$openCrafting$3(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$_p7JaSZQ_QUgIXrutYyw8z-LCHA, reason: not valid java name */
    public static /* synthetic */ void m18982$r8$lambda$_p7JaSZQ_QUgIXrutYyw8zLCHA(StarGiftSheet r0) {
        r0.lambda$doUpgrade$128();
    }

    /* renamed from: $r8$lambda$ajInNuMH3U-_W2fMVJQZhDsLKho, reason: not valid java name */
    public static /* synthetic */ void m18983$r8$lambda$ajInNuMH3U_W2fMVJQZhDsLKho(StarGiftSheet r0, TLRPC.Chat r1) {
        r0.lambda$toggleWear$21(r1);
    }

    public static /* synthetic */ void $r8$lambda$ayz9CeZSNfCTkjyo3yWyDyXn1pE(StarGiftSheet r0, TL_stories.TL_premium_boostsStatus r1, long r2, MessagesController r4, ChannelBoostsController.CanApplyBoost r5) {
        r0.lambda$toggleWear$22(r1, r2, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$b33brTUyDo1QzIKE2ICRorp3kvw(StarGiftSheet r0, View r1) {
        r0.lambda$set$61(r1);
    }

    public static /* synthetic */ void $r8$lambda$bX0epD4xA5LWviRqa3RGHWlmwaU(StarGiftSheet r0, View r1) {
        r0.lambda$set$89(r1);
    }

    public static /* synthetic */ void $r8$lambda$bhApmADmA8COa6KdybmSeVNUTHw(StarGiftSheet r0, int r1, View r2) {
        r0.lambda$set$60(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$c9lcaFhLFdTq7nktbr4hE6FX6Ys(StarGiftSheet r0, CharSequence r1, View r2) {
        r0.lambda$set$56(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$crs0arBSyJX1Dq86kTWbz5tFj3c(StarGiftSheet r0, long r1) {
        r0.lambda$set$74(r1);
    }

    public static /* synthetic */ void $r8$lambda$d3jakOpRrdJ2uK5bjh7CRB2KRtQ(StarGiftSheet r0, StarsController r1, long r2, Utilities.Callback r4) {
        r0.lambda$doTransfer$159(r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$drAaG0P0KofF0rdYAZun1lI7KWY(StarGiftSheet r0, TLRPC.TL_error r1, TwoStepVerificationActivity r2, TLObject r3) {
        r0.lambda$initTONTransfer$154(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$du5v5V2y3kirqBH5kY9FDCQhzCg(StarGiftSheet r0, int r1, View r2) {
        r0.lambda$set$58(r1, r2);
    }

    /* renamed from: $r8$lambda$eRnYXsfV8-uNUAS44iZcazhnpuk, reason: not valid java name */
    public static /* synthetic */ void m18984$r8$lambda$eRnYXsfV8uNUAS44iZcazhnpuk(StarGiftSheet r0, View r1) {
        r0.lambda$set$48(r1);
    }

    public static /* synthetic */ void $r8$lambda$esd6UJSh91GhHkFyWOfy_K81__U(StarGiftSheet r0, TLRPC.TL_payments_paymentResult r1) {
        r0.lambda$doUpgrade$131(r1);
    }

    /* renamed from: $r8$lambda$f18OJ-nwem3Zg7ABlinffULs3YU, reason: not valid java name */
    public static /* synthetic */ void m18985$r8$lambda$f18OJnwem3Zg7ABlinffULs3YU(StarGiftSheet r0) {
        r0.openInProfile();
    }

    public static /* synthetic */ void $r8$lambda$f5snybu5ASZurSzB7AROK2pvTTQ(StarGiftSheet r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$tickUpgradePrice$121(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$f9cDyu9QeZyVtCEFCKQ2pxktnZY(StarGiftSheet r0) {
        r0.convert();
    }

    public static /* synthetic */ void $r8$lambda$fG4mHZ9e05_3dsWtkqo1BfuApuQ(StarGiftSheet r0) {
        r0.lambda$set$53();
    }

    public static /* synthetic */ void $r8$lambda$gRxzNna633vOpaAkv0XC0kMCDIg(StarGiftSheet r0, TL_stars.StarGift r1, TL_stars.StarGiftAttribute r2, ButtonSpan.TextViewButtons[] r3, boolean[] r4, ArrayList r5) {
        r0.lambda$addAttributeRow$44(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$gi3JPbvX6kap2zntMZYibei_mFY(StarGiftSheet r0, long r1) {
        r0.lambda$set$87(r1);
    }

    public static /* synthetic */ void $r8$lambda$h61nV3E66vHpjS89UYaNzkZ_478(StarGiftSheet r0, long r1) {
        r0.lambda$set$86(r1);
    }

    /* renamed from: $r8$lambda$hE7tBGB2AcyFNdg1iafP-sc3vmA, reason: not valid java name */
    public static /* synthetic */ void m18986$r8$lambda$hE7tBGB2AcyFNdg1iafPsc3vmA(StarGiftSheet r0, CharSequence r1) {
        r0.lambda$showDeleteDescriptionAlert$67(r1);
    }

    public static /* synthetic */ void $r8$lambda$hFEuHqqSrETBDiwzfg8P8aIuYQQ(StarGiftSheet r0, TL_stars.TL_starGiftUnique r1, AmountUtils$Amount r2, Runnable r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$onResellPressed$39(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$hnzglEYXovU6joJdWvrqghw6lZg(StarGiftSheet r0, UserSelectorBottomSheet[] r1, Long r2, Runnable r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$openTransfer$148(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$hsD0vMaPBSND2_chelVWNtAZWtI(StarGiftSheet r0, long r1) {
        r0.lambda$set$54(r1);
    }

    public static /* synthetic */ void $r8$lambda$iDKkCcj41sXoqLUU8hFatrdh3Vo(Runnable r0, View r1) {
        r0.run();
    }

    /* renamed from: $r8$lambda$j5PIWpWaRYsnQ-2oUqpDJLV5Ot4, reason: not valid java name */
    public static /* synthetic */ void m18987$r8$lambda$j5PIWpWaRYsnQ2oUqpDJLV5Ot4(StarGiftSheet r0) {
        r0.openSetAsTheme();
    }

    public static /* synthetic */ void $r8$lambda$jAsf7EKrlto6TDrSUY2FUjLJQGo(StarGiftSheet r0, TLRPC.TL_error r1, Runnable r2) {
        r0.lambda$onResellPressed$38(r1, r2);
    }

    /* renamed from: $r8$lambda$jHAmjXbxChvXHST5LZ-VZki1VeY, reason: not valid java name */
    public static /* synthetic */ void m18988$r8$lambda$jHAmjXbxChvXHST5LZVZki1VeY(StarGiftSheet r0, TLObject r1, long r2, long r4, Utilities.Callback r6, TLRPC.TL_error r7, long r8) {
        r0.lambda$doTransfer$165(r1, r2, r4, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$jNEw8zi_On1S7ANHcfjfMymPJ3Q(StarGiftSheet r0, TLRPC.TL_error r1) {
        r0.lambda$openTransfer$141(r1);
    }

    public static /* synthetic */ void $r8$lambda$jiBO3uTLj9jlvV1jpNpSCAwmjwE(StarGiftSheet r0, AlertDialog r1, int r2) {
        r0.lambda$initTONTransfer$151(r1, r2);
    }

    /* renamed from: $r8$lambda$jpDyE9haXv6cGUbGGPL-HmKsAUs, reason: not valid java name */
    public static /* synthetic */ void m18989$r8$lambda$jpDyE9haXv6cGUbGGPLHmKsAUs(StarGiftSheet r0, View r1) {
        r0.lambda$setupWearPage$19(r1);
    }

    public static /* synthetic */ void $r8$lambda$k2U2BH8w6mGev7q8bFv_16UwRhM(AlertDialog r2) {
        r2.dismiss();
        BaseFragment r22 = LaunchActivity.getSafeLastFragment();
        if (r22 == null) goto L6;
        BulletinFactory.of(r22).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.MessageNotFound)).ignoreDetach().show();
        return;
    }

    /* renamed from: $r8$lambda$kI_870nFx-rzzd-tf7NwvKkPVsw, reason: not valid java name */
    public static /* synthetic */ void m18990$r8$lambda$kI_870nFxrzzdtf7NwvKkPVsw(StarGiftSheet r0) {
        r0.lambda$openCrafting$8();
    }

    public static /* synthetic */ void $r8$lambda$kVYotzlJlBRWt2hZR5Bv_2oz71g(StarGiftSheet r0, TL_stars.TL_starGiftUnique r1, AlertDialog r2, int r3) {
        r0.lambda$cantWithBlockchainGiftAlert$137(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$kj6zWAK2OPfdjUaH6zJsE-x9_cE, reason: not valid java name */
    public static /* synthetic */ void m18991$r8$lambda$kj6zWAK2OPfdjUaH6zJsEx9_cE(StarGiftSheet r0, String r1) {
        r0.lambda$releasedByText$49(r1);
    }

    public static /* synthetic */ void $r8$lambda$l4V70rDsHZS8qwcJdRYHOmKD2_4(StarGiftSheet r0, View r1) {
        r0.lambda$openUpgradeAfter$119(r1);
    }

    public static /* synthetic */ void $r8$lambda$lb4VMxmGXyBeH2whcQ0P8bSXIf4(StarGiftSheet r0, TL_stars.TL_starGiftUnique r1, AlertDialog r2, int r3) {
        r0.lambda$onResellPressed$34(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$mCGYC8WB9j9r2MyblWGi5-sXTJs, reason: not valid java name */
    public static /* synthetic */ void m18992$r8$lambda$mCGYC8WB9j9r2MyblWGi5sXTJs(StarGiftSheet r0) {
        r0.lambda$set$65();
    }

    /* renamed from: $r8$lambda$mLww1guo-bW-YGpubArGVAzjrJE, reason: not valid java name */
    public static /* synthetic */ void m18993$r8$lambda$mLww1guobWYGpubArGVAzjrJE(StarGiftSheet r0, View r1) {
        r0.lambda$set$79(r1);
    }

    public static /* synthetic */ void $r8$lambda$mRHWa3yonOEFk4sgeO7kTbWZTZY(Utilities.Callback r0, AlertDialog r1, int r2) {
        r0.run(r1.makeButtonLoading(r2));
    }

    public static /* synthetic */ void $r8$lambda$n9CdV5sdmfWLc7RbeYsicYMZrJk(StarGiftSheet r0, Browser.Progress r1, TL_stars.TL_starGiftUnique r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$onResellPressed$33(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$nA8us8LHxrSaag0PkTEy_CoDit8(StarGiftSheet r0, long r1) {
        r0.lambda$openUpgradeAfter$120(r1);
    }

    public static /* synthetic */ void $r8$lambda$nsGzPcKzCsg6f7Wd4VyUyb4lK6c(StarGiftSheet r0, long r1) {
        r0.lambda$set$84(r1);
    }

    /* renamed from: $r8$lambda$oER3QG33-NS_A7g15UCc280aSQM, reason: not valid java name */
    public static /* synthetic */ void m18994$r8$lambda$oER3QG33NS_A7g15UCc280aSQM(StarGiftSheet r0) {
        r0.lambda$showGiftOfferSheet$15();
    }

    public static /* synthetic */ void $r8$lambda$p6pSx3BZRHQoAOKCoEoqZM_v1J4(StarGiftSheet r0, Runnable r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$openTransfer$146(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$pKT70BlVh2-oN_9jAkWDlSl7UwE, reason: not valid java name */
    public static /* synthetic */ void m18995$r8$lambda$pKT70BlVh2oN_9jAkWDlSl7UwE(StarGiftSheet r0, ChatActivity r1) {
        r0.lambda$doUpgrade$129(r1);
    }

    public static /* synthetic */ void $r8$lambda$pXpAslhT_1e_tUCANrq6MLh8P1Y(StarGiftSheet r0, AlertDialog r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$show$111(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$qZ55IM7nGOhrrTorfT4vbBVQdac(StarGiftSheet r0, long r1) {
        r0.lambda$set$83(r1);
    }

    public static /* synthetic */ void $r8$lambda$qhL9thKAJ2_zWamPR5t3MC_va04(StarGiftSheet r0, View r1) {
        r0.lambda$set$64(r1);
    }

    /* renamed from: $r8$lambda$r6scimVQSZo6XVjrfRv8z-0NtCc, reason: not valid java name */
    public static /* synthetic */ void m18996$r8$lambda$r6scimVQSZo6XVjrfRv8z0NtCc(StarGiftSheet r0, Browser.Progress r1, TL_stars.TL_starGiftUnique r2, long r3, GiftMessageBottomSheet r5, Boolean r6, String r7) {
        r0.lambda$performBuyPressed$170(r1, r2, r3, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$rFQavRCikydUt6bux55kvR0uUcM(StarGiftSheet r0, long r1, long r3, Utilities.Callback r5, long r6, TLObject r8, TLRPC.TL_error r9) {
        r0.lambda$doTransfer$166(r1, r3, r5, r6, r8, r9);
    }

    /* renamed from: $r8$lambda$rR2OIeat9eQsteJO-ofNc2bJgZY, reason: not valid java name */
    public static /* synthetic */ void m18997$r8$lambda$rR2OIeat9eQsteJOofNc2bJgZY(StarGiftSheet r0, Browser.Progress r1, AlertDialog r2, TLObject r3, TL_stars.TL_starGiftUnique r4, TLRPC.TL_error r5, long r6, CharSequence r8) {
        r0.lambda$showDeleteDescriptionAlert$68(r1, r2, r3, r4, r5, r6, r8);
    }

    public static /* synthetic */ void $r8$lambda$rlYMsLabYQlLpNQLnDBJMzdosdo(StarGiftSheet r0) {
        r0.tickUpgradePrice();
    }

    public static /* synthetic */ void $r8$lambda$rz4Rz9ELrVUzSuWE7VDkEK5YEe0(StarGiftSheet r0, TLRPC.TL_messageActionStarGift r1, AlertDialog r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$show$114(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$s5uTpjKVG47bdnnfU60OXu8V5Jk(StarGiftSheet r0, long r1, TL_stars.TL_starGiftUnique r3, DialogsActivity r4) {
        r0.lambda$openSetAsTheme$16(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$s_76laQdOXIuHe_lY8kcRvtlkrE(StarGiftSheet r0, View r1) {
        r0.lambda$toggleWear$25(r1);
    }

    public static /* synthetic */ void $r8$lambda$stZ69trv_Brnu_fiiS9fzyxgeEc(StarGiftSheet r0, View r1) {
        r0.lambda$set$59(r1);
    }

    public static /* synthetic */ void $r8$lambda$tHGaYToT59er6ZZ_QCjTChotxTY(StarGiftSheet r0, ChatActivity r1, long r2) {
        r0.lambda$doTransfer$156(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$tIPj6hwB5HFcTBxzvWKmwyFHSMc(StarGiftSheet r0, StoryRecorder r1, View r2, Long r3, Runnable r4, Boolean r5, Long r6) {
        r0.lambda$repostStory$42(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$ubT_9L350q7Aml0Ck5Oua9tnitc(StarGiftSheet r0, TwoStepVerificationActivity r1, TLRPC.InputCheckPasswordSRP r2) {
        r0.lambda$openTransfer$138(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$uhnyq8Y3on6fvavVRaLROBv7cHk(StarGiftSheet r0, View r1) {
        r0.lambda$openAsLearnMore$99(r1);
    }

    public static /* synthetic */ void $r8$lambda$uswXRkjUeqsAHZ5E16x7fbkMljI(StarGiftSheet r0, String r1) {
        r0.lambda$set$46(r1);
    }

    public static /* synthetic */ void $r8$lambda$uvFXMujXIHalISDLsH3hClH1234(StarGiftSheet r0) {
        r0.lambda$doUpgrade$123();
    }

    public static /* synthetic */ void $r8$lambda$v_tSLDDyNV8I1NsqGghDr7XKoB4(StarGiftSheet r0, View r1) {
        r0.lambda$set$57(r1);
    }

    /* renamed from: $r8$lambda$w1JsQ-WGxgbOxS052WWI4mM9aR4, reason: not valid java name */
    public static /* synthetic */ void m18998$r8$lambda$w1JsQWGxgbOxS052WWI4mM9aR4(StarGiftSheet r0, GiftMessageBottomSheet r1, AmountUtils$Currency r2, TL_stars.TL_starGiftUnique r3, long r4, TLRPC.TL_textWithEntities r6, boolean r7, TLRPC.TL_payments_paymentFormStarGift r8) {
        r0.lambda$performBuyPressed$172(r1, r2, r3, r4, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$wC4i8LWXtX6D_8gCfPjYW5WtMjU(StarGiftSheet r0, TL_stars.InputSavedStarGift r1, long r2, long r4, long r6, AlertDialog r8, int r9) {
        r0.lambda$convert$105(r1, r2, r4, r6, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$wHslII7K9o0CQZZnovbX5sDzgV4(StarGiftSheet r0, ArrayList r1, Utilities.Callback2 r2, Runnable r3) {
        r0.lambda$openCrafting$5(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$weAvD2bLa9r5Us4lhxfS0TsZrTU(StarGiftSheet r0, StarsController r1) {
        r0.lambda$doUpgrade$127(r1);
    }

    public static /* synthetic */ void $r8$lambda$weenqdtQRFpOHBj0eI61bFsjueU(StarGiftSheet r0, TL_stars.SavedStarGift r1) {
        r0.lambda$repollSavedStarGift$98(r1);
    }

    public static /* synthetic */ void $r8$lambda$wqg4RmcAa_AaN0xz50ofqGu7oMg(StarGiftSheet r0, long r1) {
        r0.lambda$set$75(r1);
    }

    /* renamed from: $r8$lambda$wzefS0wk0YyqsYellgIwP-L35I0, reason: not valid java name */
    public static /* synthetic */ void m18999$r8$lambda$wzefS0wk0YyqsYellgIwPL35I0(Utilities.Callback2 r4, ButtonSpan.TextViewButtons[] r5, TL_stars.UniqueStarGiftValueInfo r6, String r7) {
        r4.run(r5[0], LocaleController.formatString(R.string.GiftValueAveragePriceInfo, new Object[]{BillingController.getInstance().formatCurrency(r6.average_price, r6.currency), r7}));
    }

    public static /* synthetic */ void $r8$lambda$xroS_Sse0N3rApUaaZE_ZWcLKR8(StarGiftSheet r0, View r1) {
        r0.lambda$set$93(r1);
    }

    public static /* synthetic */ void $r8$lambda$yjNNR41cKIq_sDnJxm2U72VIg3M(StarGiftSheet r0, Browser.Progress r1, UserSelectorBottomSheet[] r2, TLRPC.TL_error r3) {
        r0.lambda$openTransfer$142(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$zTiTet1osaKcAg6Eebwol2RJwiY(StarGiftSheet r0, TLObject r1, UserSelectorBottomSheet[] r2, Long r3, Runnable r4, TLRPC.TL_error r5) {
        r0.lambda$openTransfer$147(r1, r2, r3, r4, r5);
    }

    /* renamed from: -$$Nest$fgetactionView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionView m19000$$Nest$fgetactionView(StarGiftSheet r0) {
        return r0.actionView;
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Adapter m19001$$Nest$fgetadapter(StarGiftSheet r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetbuttonShadow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m19002$$Nest$fgetbuttonShadow(StarGiftSheet r0) {
        return r0.buttonShadow;
    }

    /* renamed from: -$$Nest$fgetcontainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ContainerView m19003$$Nest$fgetcontainer(StarGiftSheet r0) {
        return r0.container;
    }

    /* renamed from: -$$Nest$fgetcraftLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m19004$$Nest$fgetcraftLayout(StarGiftSheet r0) {
        return r0.craftLayout;
    }

    /* renamed from: -$$Nest$fgetcurrentPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PageTransition m19005$$Nest$fgetcurrentPage(StarGiftSheet r0) {
        return r0.currentPage;
    }

    /* renamed from: -$$Nest$fgetgiftsList, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StarsController.IGiftsList m19006$$Nest$fgetgiftsList(StarGiftSheet r0) {
        return r0.giftsList;
    }

    /* renamed from: -$$Nest$fgetheights, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m19007$$Nest$fgetheights(StarGiftSheet r0) {
        return r0.heights;
    }

    /* renamed from: -$$Nest$fgetinfoLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m19008$$Nest$fgetinfoLayout(StarGiftSheet r0) {
        return r0.infoLayout;
    }

    /* renamed from: -$$Nest$fgetlastTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Float m19009$$Nest$fgetlastTop(StarGiftSheet r0) {
        return r0.lastTop;
    }

    /* renamed from: -$$Nest$fgetleft, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StarGiftSheet m19010$$Nest$fgetleft(StarGiftSheet r0) {
        return r0.left;
    }

    /* renamed from: -$$Nest$fgetright, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StarGiftSheet m19011$$Nest$fgetright(StarGiftSheet r0) {
        return r0.right;
    }

    /* renamed from: -$$Nest$fgettopView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TopView m19012$$Nest$fgettopView(StarGiftSheet r0) {
        return r0.topView;
    }

    /* renamed from: -$$Nest$fgetunderButtonContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m19013$$Nest$fgetunderButtonContainer(StarGiftSheet r0) {
        return r0.underButtonContainer;
    }

    /* renamed from: -$$Nest$fgetupgradeLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m19014$$Nest$fgetupgradeLayout(StarGiftSheet r0) {
        return r0.upgradeLayout;
    }

    /* renamed from: -$$Nest$fgetwearLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m19015$$Nest$fgetwearLayout(StarGiftSheet r0) {
        return r0.wearLayout;
    }

    /* renamed from: -$$Nest$fputfirstSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19016$$Nest$fputfirstSet(StarGiftSheet r0, boolean r1) {
        r0.firstSet = r1;
    }

    /* renamed from: -$$Nest$fputoverrideNextIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19017$$Nest$fputoverrideNextIndex(StarGiftSheet r0, int r1) {
        r0.overrideNextIndex = r1;
    }

    /* renamed from: -$$Nest$fputswitchingPagesAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19018$$Nest$fputswitchingPagesAnimator(StarGiftSheet r0, ValueAnimator r1) {
        r0.switchingPagesAnimator = r1;
    }

    /* renamed from: -$$Nest$mgetBottomHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19019$$Nest$mgetBottomHeight(StarGiftSheet r0) {
        return r0.getBottomHeight();
    }

    /* renamed from: -$$Nest$mgetNeighbourSavedGift, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_stars.SavedStarGift m19020$$Nest$mgetNeighbourSavedGift(StarGiftSheet r0, boolean r1) {
        return r0.getNeighbourSavedGift(r1);
    }

    /* renamed from: -$$Nest$mgetNeighbourSlugGift, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_stars.TL_starGiftUnique m19021$$Nest$mgetNeighbourSlugGift(StarGiftSheet r0, boolean r1) {
        return r0.getNeighbourSlugGift(r1);
    }

    /* renamed from: -$$Nest$mhasNeighbour, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19022$$Nest$mhasNeighbour(StarGiftSheet r0, boolean r1) {
        return r0.hasNeighbour(r1);
    }

    /* renamed from: -$$Nest$monSwitchedPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19023$$Nest$monSwitchedPage(StarGiftSheet r0) {
        r0.onSwitchedPage();
    }

    /* renamed from: -$$Nest$mopenProfile, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19024$$Nest$mopenProfile(StarGiftSheet r0, long r1) {
        r0.openProfile(r1);
    }

    /* renamed from: -$$Nest$mrepostStory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19025$$Nest$mrepostStory(StarGiftSheet r0, View r1) {
        r0.repostStory(r1);
    }

    /* renamed from: -$$Nest$msetupNeighbour, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19026$$Nest$msetupNeighbour(StarGiftSheet r0, boolean r1, boolean r2) {
        r0.setupNeighbour(r1, r2);
    }

    /* renamed from: -$$Nest$mupdateUnderButtonContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19027$$Nest$mupdateUnderButtonContainer(StarGiftSheet r0) {
        r0.updateUnderButtonContainer();
    }

    public StarGiftSheet(Context r8, int r9, long r10, Theme.ResourcesProvider r12) {
        this(r8, r9, r10, r12, null);
    }

    public StarGiftSheet(Context r35, int r36, long r37, Theme.ResourcesProvider r39, View r40) {
        super(r35, null, false, false, false, r39);
        this.upgradedOnce = false;
        this.heights = new int[2];
        this.overrideNextIndex = -1;
        this.title = _UrlKt.FRAGMENT_ENCODE_SET;
        this.currentPage = new PageTransition(0, 0, 1.0f);
        this.firstSet = true;
        this.starCached = new ColoredImageSpan[1];
        this.tickUpgradePriceRunnable = new StarGiftSheet$$ExternalSyntheticLambda28(this);
        this.currentAccount = r36;
        this.dialogId = r37;
        this.topPadding = Math.max(0.05f, AndroidUtilities.dp(82.0f) / (AndroidUtilities.displaySize.y + AndroidUtilities.statusBarHeight));
        this.occupyNavigationBar = true;
        this.containerView = new AnonymousClass1(this, r35);
        this.container = new ContainerView(this, r35);
        AnonymousClass2 r3 = new AnonymousClass2(this, r35);
        this.viewPager = r3;
        r3.setAdapter(new AnonymousClass3(this, r35));
        updateViewPager();
        View r32 = new View(r35);
        this.bottomView = r32;
        int r4 = Theme.key_dialogBackground;
        r32.setBackgroundColor(getThemedColor(r4));
        this.containerView.addView(r32, LayoutHelper.createFrame(-1, 50, 80));
        this.containerView.addView(this.viewPager, LayoutHelper.createFrame(-1, -1, 119));
        fixNavigationBar(getThemedColor(r4));
        AndroidUtilities.removeFromParent(this.recyclerListView);
        this.container.addView(this.recyclerListView, LayoutHelper.createFrame(-1, -1, 119));
        AnonymousClass4 r33 = new AnonymousClass4(this, r35);
        this.infoLayout = r33;
        r33.setOrientation(1);
        r33.setPadding(this.backgroundPaddingLeft + AndroidUtilities.dp(14.0f), AndroidUtilities.dp(16.0f), this.backgroundPaddingLeft + AndroidUtilities.dp(14.0f), AndroidUtilities.dp(68.0f));
        this.container.addView(r33, LayoutHelper.createFrame(-1, -1, 55));
        LinkSpanDrawable.LinksTextView r5 = new LinkSpanDrawable.LinksTextView(r35, r39);
        this.beforeTableTextView = r5;
        int r8 = Theme.key_dialogTextGray2;
        r5.setTextColor(Theme.getColor(r8, r39));
        r5.setTextSize(1, 12.0f);
        r5.setGravity(17);
        r5.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        r5.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r39));
        r5.setDisablePaddingsOffsetY(true);
        r33.addView(r5, LayoutHelper.createLinear(-2, -2, 1, 4, -2, 4, 16));
        r5.setVisibility(8);
        TableView r52 = new TableView(r35, r39);
        this.tableView = r52;
        r33.addView(r52, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 12.0f));
        LinkSpanDrawable.LinksTextView r53 = new LinkSpanDrawable.LinksTextView(r35, r39);
        this.afterTableTextView = r53;
        r53.setTextColor(Theme.getColor(r8, r39));
        r53.setTextSize(1, 12.0f);
        r53.setGravity(17);
        r53.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        int r6 = Theme.key_featuredStickers_addButton;
        r53.setLinkTextColor(Theme.getColor(r6, r39));
        r53.setDisablePaddingsOffsetY(true);
        r53.setPadding(AndroidUtilities.dp(5.0f), 0, AndroidUtilities.dp(5.0f), 0);
        r33.addView(r53, LayoutHelper.createLinear(-2, -2, 1, 4, 2, 4, 8));
        r53.setVisibility(8);
        AnonymousClass5 r54 = new AnonymousClass5(this, r35);
        this.upgradeLayout = r54;
        r54.setOrientation(1);
        r54.setPadding(AndroidUtilities.dp(4.0f) + this.backgroundPaddingLeft, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(4.0f) + this.backgroundPaddingLeft, AndroidUtilities.dp(66.0f));
        this.container.addView(r54, LayoutHelper.createFrame(-1, -1, 55));
        AffiliateProgramFragment.FeatureCell[] r82 = {r9, r0, r0};
        this.upgradeFeatureCells = r82;
        AffiliateProgramFragment.FeatureCell r9 = new AffiliateProgramFragment.FeatureCell(r35, r39);
        r9.set(R.drawable.menu_feature_unique, LocaleController.getString(R.string.Gift2UpgradeFeature1Title), LocaleController.getString(R.string.GiftsFeature1Text));
        r54.addView(r82[0], LayoutHelper.createLinear(-1, -2));
        AffiliateProgramFragment.FeatureCell r0 = new AffiliateProgramFragment.FeatureCell(r35, r39);
        r0.set(R.drawable.menu_feature_tradable, LocaleController.getString(R.string.Gift2UpgradeFeature3Title), LocaleController.getString(R.string.GiftsFeature2Text));
        r54.addView(r82[1], LayoutHelper.createLinear(-1, -2));
        AffiliateProgramFragment.FeatureCell r02 = new AffiliateProgramFragment.FeatureCell(r35, r39);
        r02.set(R.drawable.menu_wear, LocaleController.getString(R.string.GiftsFeature3Title), LocaleController.getString(R.string.GiftsFeature3Text));
        r54.addView(r82[2], LayoutHelper.createLinear(-1, -2));
        View r03 = new View(r35);
        this.checkboxSeparator = r03;
        int r7 = Theme.key_divider;
        r03.setBackgroundColor(Theme.getColor(r7, r39));
        r54.addView(r03, LayoutHelper.createLinear(-2, 1.0f / AndroidUtilities.density, 7, 17, -4, 17, 6));
        LinearLayout r04 = new LinearLayout(r35);
        this.checkboxLayout = r04;
        r04.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(8.0f));
        r04.setOrientation(0);
        r04.setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_listSelector, r39), 6, 6));
        CheckBox2 r83 = new CheckBox2(r35, 24, r39);
        this.checkbox = r83;
        r83.setColor(Theme.key_radioBackgroundChecked, Theme.key_checkboxDisabled, Theme.key_checkboxCheck);
        r83.setDrawUnchecked(true);
        r83.setChecked(false, false);
        r83.setDrawBackgroundAsArc(10);
        r04.addView(r83, LayoutHelper.createLinear(26, 26, 16, 0, 0, 0, 0));
        TextView r84 = new TextView(r35);
        this.checkboxTextView = r84;
        int r92 = Theme.key_dialogTextBlack;
        r84.setTextColor(getThemedColor(r92));
        r84.setTextSize(1, 14.0f);
        r84.setText(LocaleController.getString(R.string.Gift2AddSenderName));
        r04.addView(r84, LayoutHelper.createLinear(-2, -2, 16, 9, 0, 0, 0));
        r54.addView(r04, LayoutHelper.createLinear(-2, -2, 1, 0, 0, 0, 4));
        ScaleStateListAnimator.apply(r04, 0.025f, 1.5f);
        AnonymousClass6 r85 = new AnonymousClass6(this, r35);
        this.wearLayout = r85;
        r85.setOrientation(1);
        r85.setPadding(AndroidUtilities.dp(4.0f) + this.backgroundPaddingLeft, AndroidUtilities.dp(20.0f), AndroidUtilities.dp(4.0f) + this.backgroundPaddingLeft, AndroidUtilities.dp(66.0f));
        this.container.addView(r85, LayoutHelper.createFrame(-1, -1, 55));
        TextView r10 = new TextView(r35);
        this.wearTitle = r10;
        r10.setTextColor(Theme.getColor(r92, r39));
        r10.setTextSize(1, 20.0f);
        r10.setGravity(17);
        r10.setTypeface(AndroidUtilities.bold());
        r85.addView(r10, LayoutHelper.createLinear(-1, -2, 7, 20, 0, 20, 0));
        TextView r102 = new TextView(r35);
        this.wearSubtitle = r102;
        r102.setTextColor(Theme.getColor(r92, r39));
        r102.setTextSize(1, 14.0f);
        r102.setGravity(17);
        r102.setText(LocaleController.getString(R.string.Gift2WearSubtitle));
        r85.addView(r102, LayoutHelper.createLinear(-1, -2, 7, 20, 6, 20, 24));
        AffiliateProgramFragment.FeatureCell[] r14 = {r10, r9, r9};
        this.wearFeatureCells = r14;
        AffiliateProgramFragment.FeatureCell r103 = new AffiliateProgramFragment.FeatureCell(r35, r39);
        r103.set(R.drawable.menu_feature_unique, LocaleController.getString(R.string.Gift2WearFeature1Title), LocaleController.getString(R.string.Gift2WearFeature1Text));
        r85.addView(r14[0], LayoutHelper.createLinear(-1, -2));
        AffiliateProgramFragment.FeatureCell r93 = new AffiliateProgramFragment.FeatureCell(r35, r39);
        r93.set(R.drawable.menu_feature_cover, LocaleController.getString(R.string.Gift2WearFeature2Title), LocaleController.getString(R.string.Gift2WearFeature2Text));
        r85.addView(r14[1], LayoutHelper.createLinear(-1, -2));
        AffiliateProgramFragment.FeatureCell r94 = new AffiliateProgramFragment.FeatureCell(r35, r39);
        r94.set(R.drawable.menu_verification, LocaleController.getString(R.string.Gift2WearFeature3Title), LocaleController.getString(R.string.Gift2WearFeature3Text));
        r85.addView(r14[2], LayoutHelper.createLinear(-1, -2));
        AnonymousClass7 r95 = new AnonymousClass7(this, r35);
        this.craftLayout = r95;
        r95.setOrientation(1);
        r95.setPadding(AndroidUtilities.dp(4.0f) + this.backgroundPaddingLeft, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(4.0f) + this.backgroundPaddingLeft, AndroidUtilities.dp(92.0f));
        this.container.addView(r95, LayoutHelper.createFrame(-1, -1, 55));
        AffiliateProgramFragment.FeatureCell[] r104 = {r14, r12, r12};
        this.craftFeatureCells = r104;
        AffiliateProgramFragment.FeatureCell r142 = new AffiliateProgramFragment.FeatureCell(r35, r39);
        r142.set(R.drawable.menu_feature_unique, LocaleController.getString(R.string.GiftCraftInfoFeature1Title), LocaleController.getString(R.string.GiftCraftInfoFeature1Text));
        r95.addView(r104[0], LayoutHelper.createLinear(-1, -2));
        AffiliateProgramFragment.FeatureCell r12 = new AffiliateProgramFragment.FeatureCell(r35, r39);
        r12.set(R.drawable.menu_random, LocaleController.getString(R.string.GiftCraftInfoFeature2Title), LocaleController.getString(R.string.GiftCraftInfoFeature2Text));
        r95.addView(r104[1], LayoutHelper.createLinear(-1, -2));
        AffiliateProgramFragment.FeatureCell r122 = new AffiliateProgramFragment.FeatureCell(r35, r39);
        r122.set(R.drawable.menu_feature_affect, LocaleController.getString(R.string.GiftCraftInfoFeature3Title), LocaleController.getString(R.string.GiftCraftInfoFeature3Text));
        r95.addView(r104[2], LayoutHelper.createLinear(-1, -2));
        r33.setAlpha(1.0f);
        r54.setAlpha(0.0f);
        r85.setAlpha(0.0f);
        r95.setAlpha(0.0f);
        TopView r05 = new TopView(r35, r39, new StarGiftSheet$$ExternalSyntheticLambda30(this), new StarGiftSheet$$ExternalSyntheticLambda31(this), new StarGiftSheet$$ExternalSyntheticLambda32(this), new StarGiftSheet$$ExternalSyntheticLambda33(this), new StarGiftSheet$$ExternalSyntheticLambda34(this), new StarGiftSheet$$ExternalSyntheticLambda35(this), new StarGiftSheet$$ExternalSyntheticLambda36(this), new StarGiftSheet$$ExternalSyntheticLambda37(this));
        this.topView = r05;
        CraftTopView.m19038$$Nest$fgethelpButton(TopView.m19077$$Nest$fgetcraftTopView(r05)).setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda38(this));
        int r34 = this.backgroundPaddingLeft;
        r05.setPadding(r34, 0, r34, 0);
        this.container.addView(r05, LayoutHelper.createFrame(-1, -2, 55));
        LinearLayoutManager r06 = this.layoutManager;
        this.reverseLayout = true;
        r06.setReverseLayout(true);
        FrameLayout r07 = new FrameLayout(r35);
        this.buttonContainer = r07;
        r07.setBackgroundColor(getThemedColor(r4));
        View r38 = new View(r35);
        this.buttonShadow = r38;
        r38.setBackgroundColor(getThemedColor(r7));
        r38.setAlpha(0.0f);
        r07.addView(r38, LayoutHelper.createFrame(-1.0f, 1.0f / AndroidUtilities.density, 55));
        ButtonWithCounterView r310 = new ButtonWithCounterView(r35, r39).setRound();
        this.button = r310;
        r310.setText(LocaleController.getString(R.string.OK), false);
        r310.setSubText(null, false);
        FrameLayout.LayoutParams r42 = LayoutHelper.createFrame(-1, 48.0f, 119, 0.0f, 12.0f, 0.0f, 12.0f);
        r42.leftMargin = this.backgroundPaddingLeft + AndroidUtilities.dp(14.0f);
        r42.rightMargin = this.backgroundPaddingLeft + AndroidUtilities.dp(14.0f);
        r07.addView(r310, r42);
        this.container.addView(r07, LayoutHelper.createFrame(-1, 72, 87));
        FrameLayout r08 = new FrameLayout(r35);
        this.underButtonContainer = r08;
        r08.setBackgroundColor(getThemedColor(r4));
        LinkSpanDrawable.LinksTextView r311 = new LinkSpanDrawable.LinksTextView(r35);
        this.underButtonLinkTextView = r311;
        r311.setTextSize(1, 12.0f);
        r311.setTextColor(Theme.getColor(r6, r39));
        r311.setLinkTextColor(Theme.getColor(r6, r39));
        r311.setGravity(17);
        r08.addView(r311, LayoutHelper.createFrame(-1, -2.0f, 17, 16.0f, 8.0f, 16.0f, 14.0f));
        this.container.addView(r08, LayoutHelper.createFrame(-1, -2, 87));
        r08.setVisibility(8);
        this.recyclerListView.setOnScrollListener(new AnonymousClass8(this));
        r04.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda29(this));
        FireworksOverlay r09 = new FireworksOverlay(r35);
        this.fireworksOverlay = r09;
        this.container.addView(r09, LayoutHelper.createFrame(-1, -1.0f));
        FrameLayout r010 = new FrameLayout(r35);
        this.bottomBulletinContainer = r010;
        r010.setPadding(this.backgroundPaddingLeft + AndroidUtilities.dp(6.0f), 0, this.backgroundPaddingLeft + AndroidUtilities.dp(6.0f), 0);
        this.container.addView(r010, LayoutHelper.createFrame(-1, 200.0f, 87, 0.0f, 0.0f, 0.0f, 60.0f));
        AndroidUtilities.removeFromParent(this.actionBar);
        this.container.addView(this.actionBar, LayoutHelper.createFrame(-1, -2.0f, 0, 6.0f, 0.0f, 6.0f, 0.0f));
        ActionView r011 = new ActionView(r35);
        this.actionView = r011;
        this.container.addView(r011, LayoutHelper.createFrame(-1, -2, 55));
        r011.prepareBlur(r40);
    }

    public static /* synthetic */ int access$000(StarGiftSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ ViewGroup access$100(StarGiftSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ActionBar access$1000(StarGiftSheet r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ RecyclerListView access$1100(StarGiftSheet r0) {
        return r0.recyclerListView;
    }

    public static /* synthetic */ RecyclerListView access$1200(StarGiftSheet r0) {
        return r0.recyclerListView;
    }

    public static /* synthetic */ RecyclerListView access$1300(StarGiftSheet r0) {
        return r0.recyclerListView;
    }

    public static /* synthetic */ RecyclerListView access$1400(StarGiftSheet r0) {
        return r0.recyclerListView;
    }

    public static /* synthetic */ int access$1502(StarGiftSheet r0, int r1) {
        r0.contentHeight = r1;
        return r1;
    }

    public static /* synthetic */ RecyclerListView access$1600(StarGiftSheet r0) {
        return r0.recyclerListView;
    }

    public static /* synthetic */ void access$200(StarGiftSheet r0, Canvas r1, View r2) {
        r0.preDrawInternal(r1, r2);
    }

    public static /* synthetic */ int access$300(StarGiftSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$400(StarGiftSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$500(StarGiftSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ ActionBar access$600(StarGiftSheet r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ void access$700(StarGiftSheet r0, Canvas r1, View r2) {
        r0.postDrawInternal(r1, r2);
    }

    public static /* synthetic */ ActionBar access$800(StarGiftSheet r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$900(StarGiftSheet r0) {
        return r0.actionBar;
    }

    private void addAttributeRow(TL_stars.StarGiftAttribute r11) {
        if ((r11 instanceof TL_stars.starGiftAttributeModel) == false) goto L6;
        String r5 = LocaleController.getString(R.string.Gift2AttributeModel);
        char r0 = 2;
    L12:
        if (this.rolling == true) goto L25;
        Roller r4 = this.roller;
        if (r4 != null) goto L16;
    L18:
        boolean[] r02 = new boolean[1];
        ButtonSpan.TextViewButtons[] r1 = new ButtonSpan.TextViewButtons[1];
        Integer[] r42 = new Integer[1];
        CharSequence r7 = getRarityName(r11.rarity, r42);
        if ((r11.rarity instanceof TL_stars.TL_starGiftAttributeRarity) == false) goto L22;
        StarGiftSheet$$ExternalSyntheticLambda99 r6 = new StarGiftSheet$$ExternalSyntheticLambda99(this, r02, r11, r1);
    L23:
        r1[0] = (ButtonSpan.TextViewButtons) ((TableView.TableRowContent) this.tableView.addRow(r5, r11.name, r7, r6, r42[0]).getChildAt(1)).getChildAt(0);
        return;
    L22:
        r6 = null;
        goto L23
    L16:
        if (r4.isRolling() == false) goto L18;
    L25:
        TextViewRoll r112 = new TextViewRoll(getContext(), this.resourcesProvider, new StarGiftSheet$$ExternalSyntheticLambda98(this));
        TableRow r43 = new TableRow(getContext());
        r43.addView(new TableView.TableRowTitle(this.tableView, r5), new TableRow.LayoutParams(-2, -1));
        r43.addView(new TableView.TableRowContent(this.tableView, r112, true), new TableRow.LayoutParams(0, -1, 1.0f));
        this.tableView.addView(r43);
        Roller r10 = this.roller;
        if (r10 == null) goto L35;
        if (r0 != 0) goto L29;
        r10.backdropText = r112;
    L29:
        if (r0 != 1) goto L31;
        r10.patternText = r112;
    L31:
        if (r0 != 2) goto L36;
        r10.modelText = r112;
        return;
    L36:
        return;
    L35:
        return;
    L6:
        if ((r11 instanceof TL_stars.starGiftAttributePattern) == false) goto L9;
        r5 = LocaleController.getString(R.string.Gift2AttributeSymbol);
        r0 = 1;
        goto L12
    L9:
        if ((r11 instanceof TL_stars.starGiftAttributeBackdrop) == false) goto L34;
        r5 = LocaleController.getString(R.string.Gift2AttributeBackdrop);
        r0 = 0;
        goto L12
    }

    public static void addAttributeRow(TableView r7, TL_stars.StarGiftAttribute r8) {
        if ((r8 instanceof TL_stars.starGiftAttributeModel) == false) goto L7;
        String r0 = LocaleController.getString(R.string.Gift2AttributeModel);
    L5:
        String r2 = r0;
        Integer[] r02 = new Integer[1];
        CharSequence r4 = getRarityName(r8.rarity, r02);
        r7.addRow(r2, r8.name, r4, null, r02[0]);
        return;
    L7:
        if ((r8 instanceof TL_stars.starGiftAttributePattern) == false) goto L10;
        r0 = LocaleController.getString(R.string.Gift2AttributeSymbol);
        goto L5
    L10:
        if ((r8 instanceof TL_stars.starGiftAttributeBackdrop) == false) goto L14;
        r0 = LocaleController.getString(R.string.Gift2AttributeBackdrop);
        goto L5
    }

    private int applyNewGiftFromUpdates(TL_stars.InputSavedStarGift r7, TLRPC.Updates r8, Runnable r9) {
        if (r8 != null) goto L6;
        StarsController.getInstance(this.currentAccount).invalidateProfileGifts(getDialogId());
        dismiss();
        return 0;
    L6:
        TLRPC.Update r1 = r8.update;
        if ((r1 instanceof TL_update.TL_updateNewMessage) == false) goto L10;
        TLRPC.Message r82 = ((TL_update.TL_updateNewMessage) r1).message;
    L19:
        if (r82 == null) goto L38;
        TL_stars.SavedStarGift r12 = this.savedStarGift;
        if (r12 == null) goto L34;
        if (r7 == null) goto L34;
        if (eq(r12, r7) == false) goto L34;
        TLRPC.MessageAction r72 = r82.action;
        if ((r72 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L34;
        this.rolling = true;
        TLRPC.TL_messageActionStarGiftUnique r73 = (TLRPC.TL_messageActionStarGiftUnique) r72;
        TL_stars.SavedStarGift r13 = this.savedStarGift;
        r13.gift = r73.gift;
        int r4 = r13.flags | 8;
        r13.flags = r4;
        r13.msg_id = r82.id;
        r13.flags = r4 & (-2049);
        r13.saved_id = 0;
        r13.unsaved = !r73.saved;
        r13.refunded = r73.refunded;
        r13.can_upgrade = false;
        r13.can_resell_at = r73.can_resell_at;
        r13.can_transfer_at = r73.can_transfer_at;
        r13.can_export_at = r73.can_export_at;
        set(r13, this.giftsList);
        this.sample_attributes = null;
        this.rolling = false;
        StarsController.IGiftsList r74 = this.giftsList;
        if (r74 == null) goto L30;
        r74.notifyUpdate();
    L31:
        AndroidUtilities.runOnUIThread(r9);
        return 1;
    L30:
        StarsController.getInstance(this.currentAccount).invalidateProfileGifts(this.dialogId);
    L34:
        if (this.giftsList != null) goto L36;
        StarsController.getInstance(this.currentAccount).invalidateProfileGifts(getDialogId());
    L36:
        this.rolling = true;
        this.savedStarGift = null;
        this.myProfile = false;
        MessageObject r75 = new MessageObject(this.currentAccount, r82, false, false);
        r75.setType();
        set(r75, this.giftsList);
        this.sample_attributes = null;
        this.rolling = false;
        AndroidUtilities.runOnUIThread(r9);
        return 1;
    L38:
        StarsController.getInstance(this.currentAccount).invalidateProfileGifts(getDialogId());
        dismiss();
        return 0;
    L10:
        if (r8.updates == null) goto L18;
        int r14 = 0;
    L13:
        if (r14 >= r8.updates.size()) goto L18;
        TLRPC.Update r2 = r8.updates.get(r14);
        if ((r2 instanceof TL_update.TL_updateNewMessage) == true) goto L16;
        r14 = r14 + 1;
        goto L13
    L16:
        r82 = ((TL_update.TL_updateNewMessage) r2).message;
    L18:
        r82 = null;
        goto L19
    }

    private boolean canConvert() {
        if (getInputStarGift() != null) goto L5;
        return false;
    L5:
        MessageObject r0 = this.messageObject;
        if (r0 == null) goto L32;
        TLRPC.MessageAction r5 = r0.messageOwner.action;
        if ((r5 instanceof TLRPC.TL_messageActionStarGift) == false) goto L48;
        TLRPC.TL_messageActionStarGift r52 = (TLRPC.TL_messageActionStarGift) r5;
        if (r52.peer == null) goto L12;
        boolean r6 = true;
    L13:
        boolean r02 = r0.isOutOwner();
        if (this.messageObject.getDialogId() != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L16;
        boolean r7 = true;
    L17:
        int r9 = MessagesController.getInstance(this.currentAccount).stargiftsConvertPeriodMax - (ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() - this.messageObject.messageOwner.date);
        if (r6 == true) goto L21;
        if (r02 == false) goto L26;
        if (r7 == false) goto L21;
    L26:
        if (r52.converted == false) goto L28;
    L31:
        return false;
    L28:
        if (r52.convert_stars <= 0) goto L31;
        if (r9 <= 0) goto L31;
        return true;
    L21:
        TLRPC.Peer r03 = r52.peer;
        if (r03 == null) goto L31;
        if (isMineWithActions(this.currentAccount, DialogObject.getPeerDialogId(r03)) == false) goto L31;
    L16:
        r7 = false;
        goto L17
    L12:
        r6 = false;
    L48:
        return false;
    L32:
        TL_stars.SavedStarGift r04 = this.savedStarGift;
        if (r04 == null) goto L48;
        int r53 = MessagesController.getInstance(this.currentAccount).stargiftsConvertPeriodMax - (ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() - r04.date);
        if (isMineWithActions(this.currentAccount, this.dialogId) == false) goto L48;
        int r05 = this.savedStarGift.flags;
        if (this.dialogId >= 0) goto L39;
        int r11 = 2048;
    L41:
        if ((r11 & r05) == 0) goto L48;
        if ((r05 & 16) == 0) goto L48;
        if ((r05 & 2) == 0) goto L48;
        if (r53 <= 0) goto L48;
        return true;
    L39:
        r11 = 8;
        goto L41
    }

    private boolean canSetAsTheme() {
        TL_stars.TL_starGiftUnique r0 = getUniqueGift();
        if (r0 != null) goto L5;
    L17:
        return false;
    L5:
        if (r0.theme_available == false) goto L17;
        long r2 = DialogObject.getPeerDialogId(r0.owner_id);
        long r4 = DialogObject.getPeerDialogId(r0.host_id);
        if (r2 <= 0) goto L12;
        if (isMineWithActions(this.currentAccount, r2) == false) goto L12;
        return true;
    L12:
        if (r4 <= 0) goto L17;
        if (isMineWithActions(this.currentAccount, r4) == false) goto L17;
        return true;
    }

    private boolean canSomeoneConvert() {
        if (getInputStarGift() != null) goto L5;
        return false;
    L5:
        MessageObject r0 = this.messageObject;
        if (r0 == null) goto L26;
        TLRPC.MessageAction r5 = r0.messageOwner.action;
        if ((r5 instanceof TLRPC.TL_messageActionStarGift) == false) goto L42;
        TLRPC.TL_messageActionStarGift r52 = (TLRPC.TL_messageActionStarGift) r5;
        if (r52.peer == null) goto L12;
        boolean r6 = true;
    L13:
        r0.isOutOwner();
        this.messageObject.getDialogId();
        UserConfig.getInstance(this.currentAccount).getClientUserId();
        int r7 = MessagesController.getInstance(this.currentAccount).stargiftsConvertPeriodMax - (ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() - this.messageObject.messageOwner.date);
        if (r6 == false) goto L20;
        TLRPC.Peer r02 = r52.peer;
        if (r02 != null) goto L18;
    L25:
        return false;
    L18:
        if (isMineWithActions(this.currentAccount, DialogObject.getPeerDialogId(r02)) == false) goto L25;
    L20:
        if (r52.converted == true) goto L25;
        if (r52.convert_stars <= 0) goto L25;
        if (r7 <= 0) goto L25;
        return true;
    L12:
        r6 = false;
    L42:
        return false;
    L26:
        TL_stars.SavedStarGift r03 = this.savedStarGift;
        if (r03 == null) goto L42;
        int r53 = MessagesController.getInstance(this.currentAccount).stargiftsConvertPeriodMax - (ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() - r03.date);
        if (isMineWithActions(this.currentAccount, this.dialogId) == false) goto L42;
        int r04 = this.savedStarGift.flags;
        if (this.dialogId >= 0) goto L33;
        int r10 = 2048;
    L35:
        if ((r10 & r04) == 0) goto L42;
        if ((r04 & 16) == 0) goto L42;
        if ((r04 & 2) == 0) goto L42;
        if (r53 <= 0) goto L42;
        return true;
    L33:
        r10 = 8;
        goto L35
    }

    private void cantWithBlockchainGiftAlert(int r4) {
        AlertDialog.Builder r42 = new AlertDialog.Builder(getContext(), this.resourcesProvider);
        r42.setTitle(LocaleController.getString(R.string.Gift2CantDoTitle));
        r42.setMessage(LocaleController.getString(R.string.Gift2CantDoText));
        TL_stars.TL_starGiftUnique r0 = getUniqueGift();
        if (r0 != null) goto L5;
    L7:
        r42.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r42.show();
        return;
    L5:
        if (TextUtils.isEmpty(r0.slug) == true) goto L7;
        r42.setPositiveButton(LocaleController.getString(R.string.OpenFragment), new StarGiftSheet$$ExternalSyntheticLambda79(this, r0));
        goto L7
    }

    private void convert() {
        long r6 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        TL_stars.InputSavedStarGift r3 = getInputStarGift();
        if (r3 == null) goto L45;
        MessageObject r0 = this.messageObject;
        if (r0 == null) goto L24;
        int r4 = r0.messageOwner.date;
        boolean r02 = r0.isOutOwner();
        MessageObject r5 = this.messageObject;
        TLRPC.Message r8 = r5.messageOwner;
        if (r8 == null) goto L42;
        TLRPC.MessageAction r82 = r8.action;
        if ((r82 instanceof TLRPC.TL_messageActionStarGift) == false) goto L43;
        TLRPC.TL_messageActionStarGift r83 = (TLRPC.TL_messageActionStarGift) r82;
        TLRPC.Peer r9 = r83.peer;
        if (r9 == null) goto L14;
        long r92 = DialogObject.getPeerDialogId(r9);
    L17:
        TLRPC.Peer r52 = r83.from_id;
        if (r52 == null) goto L20;
        long r11 = DialogObject.getPeerDialogId(r52);
    L23:
        long r13 = r83.convert_stars;
    L33:
        int r03 = Math.max(1, (MessagesController.getInstance(this.currentAccount).stargiftsConvertPeriodMax - (ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() - r4)) / 86400);
        AlertDialog.Builder r42 = new AlertDialog.Builder(getContext(), this.resourcesProvider).setTitle(LocaleController.getString(R.string.Gift2ConvertTitle));
        if (UserObject.isService(r11) == false) goto L36;
    L39:
        String r1 = LocaleController.getString(R.string.StarsTransactionHidden);
    L40:
        r42.setMessage(AndroidUtilities.replaceTags(LocaleController.formatPluralString("Gift2ConvertText2", r03, new Object[]{r1, LocaleController.formatPluralStringComma("Gift2ConvertStars", (int) r13)}))).setPositiveButton(LocaleController.getString(R.string.Gift2ConvertButton), new StarGiftSheet$$ExternalSyntheticLambda55(this, r3, r92, r6, r13)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).show();
        return;
    L36:
        if (r11 == UserObject.ANONYMOUS) goto L39;
        r1 = DialogObject.getShortName(r11);
        goto L40
    L20:
        if (r02 == false) goto L22;
        r11 = r6;
        goto L23
    L22:
        r11 = this.messageObject.getDialogId();
        goto L23
    L14:
        if (r02 == false) goto L16;
        r92 = r5.getDialogId();
        goto L17
    L16:
        r92 = r6;
        goto L17
    L43:
        return;
    L42:
        return;
    L24:
        TL_stars.SavedStarGift r04 = this.savedStarGift;
        if (r04 == null) goto L44;
        r4 = r04.date;
        if ((r04.flags & 2) != 0) goto L29;
    L31:
        r11 = 2666000;
    L32:
        r13 = this.savedStarGift.convert_stars;
        r92 = this.dialogId;
        goto L33
    L29:
        if (r04.name_hidden == true) goto L31;
        r11 = DialogObject.getPeerDialogId(r04.from_id);
        goto L32
    L44:
        return;
    }

    private void doUpgrade() {
        if (this.button.isLoading() == true) goto L45;
        TL_stars.InputSavedStarGift r4 = getInputStarGift();
        if (r4 == null) goto L46;
        MessageObject r0 = this.messageObject;
        String r1 = null;
        long r2 = 0;
        if (r0 == null) goto L15;
        TLRPC.MessageAction r02 = r0.messageOwner.action;
        if ((r02 instanceof TLRPC.TL_messageActionStarGift) == false) goto L43;
        TLRPC.TL_messageActionStarGift r03 = (TLRPC.TL_messageActionStarGift) r02;
        long r5 = r03.upgrade_stars;
        if (r5 > 0) goto L21;
        r1 = r03.prepaid_upgrade_hash;
    L21:
        if (r5 <= 0) goto L23;
    L25:
        this.button.setLoading(true);
        if (r5 <= 0) goto L29;
        TL_stars.upgradeStarGift r04 = new TL_stars.upgradeStarGift();
        r04.keep_original_details = this.checkbox.isChecked();
        r04.stargift = r4;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r04, new StarGiftSheet$$ExternalSyntheticLambda160(this, r4));
        return;
    L29:
        StarsController r05 = StarsController.getInstance(this.currentAccount);
        if (r05.balanceAvailable() == true) goto L33;
        r05.getBalance(new StarGiftSheet$$ExternalSyntheticLambda161(this, r05));
        return;
    L33:
        TL_stars.TL_payments_sendStarsForm r06 = new TL_stars.TL_payments_sendStarsForm();
        r06.form_id = this.upgrade_form.form_id;
        if (TextUtils.isEmpty(r1) == true) goto L36;
        TLRPC.TL_inputInvoiceStarGiftPrepaidUpgrade r52 = new TLRPC.TL_inputInvoiceStarGiftPrepaidUpgrade();
        r52.hash = r1;
        r52.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        r06.invoice = r52;
    L37:
        ArrayList<TLRPC.TL_labeledPrice> r53 = this.upgrade_form.invoice.prices;
        int r6 = r53.size();
        int r7 = 0;
    L38:
        if (r7 >= r6) goto L40;
        TLRPC.TL_labeledPrice r8 = r53.get(r7);
        r7 = r7 + 1;
        r2 = r2 + r8.amount;
        goto L38
    L40:
        String r3 = r1;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r06, new StarGiftSheet$$ExternalSyntheticLambda162(this, r3, r4, r2));
        return;
    L36:
        TLRPC.TL_inputInvoiceStarGiftUpgrade r54 = new TLRPC.TL_inputInvoiceStarGiftUpgrade();
        r54.keep_original_details = this.checkbox.isChecked();
        r54.stargift = r4;
        r06.invoice = r54;
        goto L37
    L23:
        if (this.upgrade_form != null) goto L25;
        return;
    L43:
        return;
    L15:
        TL_stars.SavedStarGift r07 = this.savedStarGift;
        if (r07 == null) goto L44;
        r5 = r07.upgrade_stars;
        if (r5 > 0) goto L21;
        r1 = r07.prepaid_upgrade_hash;
        goto L21
    L44:
        return;
    L46:
        return;
    }

    private int getBottomHeight() {
        if (this.currentPage.to(1) == false) goto L7;
        return this.upgradeLayout.getMeasuredHeight();
    L7:
        if (this.currentPage.to(2) == false) goto L11;
        return this.wearLayout.getMeasuredHeight();
    L11:
        if (this.currentPage.to(3) == false) goto L15;
        return this.craftLayout.getMeasuredHeight();
    L15:
        if (this.currentPage.to(4) == false) goto L19;
        return 0;
    L19:
        return this.infoLayout.getMeasuredHeight();
    }

    private long getDialogId() {
        MessageObject r0 = this.messageObject;
        if (r0 == null) goto L31;
        TLRPC.Message r3 = r0.messageOwner;
        if (r3 != null) goto L7;
        return 0;
    L7:
        TLRPC.MessageAction r32 = r3.action;
        if ((r32 instanceof TLRPC.TL_messageActionStarGift) == false) goto L20;
        TLRPC.Peer r1 = ((TLRPC.TL_messageActionStarGift) r32).peer;
        if (r1 == null) goto L14;
        return DialogObject.getPeerDialogId(r1);
    L14:
        if (r0.isOutOwner() == false) goto L18;
        return this.messageObject.getDialogId();
    L18:
        return UserConfig.getInstance(this.currentAccount).getClientUserId();
    L20:
        if ((r32 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L44;
        TLRPC.TL_messageActionStarGiftUnique r33 = (TLRPC.TL_messageActionStarGiftUnique) r32;
        TL_stars.StarGift r5 = r33.gift;
        if ((r5 instanceof TL_stars.TL_starGiftUnique) == false) goto L27;
        TLRPC.Peer r52 = r5.owner_id;
        if (r52 == null) goto L27;
        return DialogObject.getPeerDialogId(r52);
    L27:
        TLRPC.Peer r53 = r33.peer;
        if (r53 == null) goto L44;
        return DialogObject.getPeerDialogId(r53);
    L44:
        return 0;
    L31:
        TL_stars.SavedStarGift r02 = this.savedStarGift;
        if (r02 == null) goto L39;
        TL_stars.StarGift r03 = r02.gift;
        if ((r03 instanceof TL_stars.TL_starGiftUnique) == false) goto L38;
        return DialogObject.getPeerDialogId(((TL_stars.TL_starGiftUnique) r03).owner_id);
    L38:
        return this.dialogId;
    L39:
        TL_stars.TL_starGiftUnique r04 = this.slugStarGift;
        if (r04 == null) goto L44;
        if (r04 == null) goto L44;
        return DialogObject.getPeerDialogId(this.slugStarGift.owner_id);
    }

    private BaseFragment getDummyFragment() {
        return new AnonymousClass10(this);
    }

    public static String getGiftName(TL_stars.StarGift r3) {
        if ((r3 instanceof TL_stars.TL_starGiftUnique) == false) goto L7;
        return ((TL_stars.TL_starGiftUnique) r3).title + " #" + LocaleController.formatNumber(r3.num, ',');
    L7:
        if ((r3 instanceof TL_stars.TL_starGift) == false) goto L13;
        if (TextUtils.isEmpty(r3.title) == true) goto L13;
        return r3.title;
    L13:
        return LocaleController.getString(R.string.Gift2Gift);
    }

    private TL_stars.InputSavedStarGift getInputStarGift() {
        if (this.dialogId >= 0) goto L36;
        TL_stars.TL_inputSavedStarGiftChat r0 = new TL_stars.TL_inputSavedStarGiftChat();
        r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        MessageObject r1 = this.messageObject;
        if (r1 == null) goto L23;
        TLRPC.Message r12 = r1.messageOwner;
        if (r12 == null) goto L23;
        TLRPC.MessageAction r5 = r12.action;
        if ((r5 instanceof TLRPC.TL_messageActionStarGift) == false) goto L16;
        TLRPC.TL_messageActionStarGift r52 = (TLRPC.TL_messageActionStarGift) r5;
        if ((r52.flags & 4096) != 0) goto L13;
        return null;
    L13:
        r0.saved_id = r52.saved_id;
        return r0;
    L16:
        if ((r5 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L22;
        TLRPC.TL_messageActionStarGiftUnique r53 = (TLRPC.TL_messageActionStarGiftUnique) r5;
        if ((r53.flags & 128) != 0) goto L20;
        return null;
    L20:
        r0.saved_id = r53.saved_id;
        return r0;
    L22:
        return null;
    L23:
        TL_stars.SavedStarGift r13 = this.savedStarGift;
        if (r13 == null) goto L31;
        if ((r13.flags & 2048) != 0) goto L28;
        return null;
    L28:
        r0.saved_id = r13.saved_id;
        return r0;
    L31:
        if (this.slugStarGift != null) goto L33;
        return r0;
    L33:
        if (TextUtils.isEmpty(this.slug) == true) goto L83;
        TL_stars.TL_inputSavedStarGiftSlug r02 = new TL_stars.TL_inputSavedStarGiftSlug();
        r02.slug = this.slug;
        return r02;
    L83:
        return r0;
    L36:
        MessageObject r03 = this.messageObject;
        if (r03 != null) goto L39;
    L48:
        MessageObject r04 = this.messageObject;
        if (r04 != null) goto L51;
    L60:
        TL_stars.TL_inputSavedStarGiftUser r05 = new TL_stars.TL_inputSavedStarGiftUser();
        MessageObject r14 = this.messageObject;
        if (r14 == null) goto L72;
        TLRPC.Message r54 = r14.messageOwner;
        if (r54 == null) goto L70;
        TLRPC.MessageAction r55 = r54.action;
        if ((r55 instanceof TLRPC.TL_messageActionStarGift) == false) goto L70;
        if ((r55.flags & 32768) == 0) goto L70;
        r05.msg_id = ((TLRPC.TL_messageActionStarGift) r55).gift_msg_id;
        return r05;
    L70:
        r05.msg_id = r14.getId();
        return r05;
    L72:
        TL_stars.SavedStarGift r15 = this.savedStarGift;
        if (r15 == null) goto L77;
        r05.msg_id = r15.msg_id;
        return r05;
    L77:
        if (this.slugStarGift != null) goto L79;
        return r05;
    L79:
        if (TextUtils.isEmpty(this.slug) == true) goto L85;
        TL_stars.TL_inputSavedStarGiftSlug r06 = new TL_stars.TL_inputSavedStarGiftSlug();
        r06.slug = this.slug;
        return r06;
    L85:
        return r05;
    L51:
        if (r04.getDialogId() >= 0) goto L60;
        TLRPC.Message r07 = this.messageObject.messageOwner;
        if (r07 == null) goto L60;
        TLRPC.MessageAction r08 = r07.action;
        if ((r08 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L60;
        if ((r08.flags & 128) == 0) goto L60;
        TL_stars.TL_inputSavedStarGiftChat r16 = new TL_stars.TL_inputSavedStarGiftChat();
        r16.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.messageObject.getDialogId());
        r16.saved_id = ((TLRPC.TL_messageActionStarGiftUnique) r08).saved_id;
        return r16;
    L39:
        if (r03.getDialogId() >= 0) goto L48;
        TLRPC.Message r09 = this.messageObject.messageOwner;
        if (r09 == null) goto L48;
        TLRPC.MessageAction r010 = r09.action;
        if ((r010 instanceof TLRPC.TL_messageActionStarGift) == false) goto L48;
        if ((r010.flags & 4096) == 0) goto L48;
        TL_stars.TL_inputSavedStarGiftChat r17 = new TL_stars.TL_inputSavedStarGiftChat();
        r17.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.messageObject.getDialogId());
        r17.saved_id = ((TLRPC.TL_messageActionStarGift) r010).saved_id;
        return r17;
    }

    private String getLink() {
        TL_stars.StarGift r0 = getGift();
        if ((r0 instanceof TL_stars.TL_starGiftUnique) == true) goto L5;
        return null;
    L5:
        if (r0.slug != null) goto L7;
        return null;
    L7:
        return MessagesController.getInstance(this.currentAccount).linkPrefix + "/nft/" + r0.slug;
    }

    private int getListPosition() {
        StarsController.IGiftsList r0 = this.giftsList;
        if (r0 != null) goto L5;
        return -1;
    L5:
        TL_stars.SavedStarGift r2 = this.savedStarGift;
        if (r2 == null) goto L8;
        int r02 = r0.indexOf(r2);
    L11:
        if (r02 < 0) goto L13;
        return r02;
    L13:
        TL_stars.StarGift r03 = getGift();
        int r22 = 0;
    L15:
        if (r22 >= this.giftsList.getLoadedCount()) goto L32;
        Object r3 = this.giftsList.get(r22);
        if ((r3 instanceof TL_stars.SavedStarGift) == false) goto L27;
        TL_stars.SavedStarGift r4 = this.savedStarGift;
        if (r4 != null) goto L21;
    L22:
        if (r03 == null) goto L31;
        if (eq(r03, (TL_stars.SavedStarGift) r3) == false) goto L31;
    L30:
        return r22;
    L31:
        r22 = r22 + 1;
        goto L15
    L21:
        if (eq(r4, (TL_stars.SavedStarGift) r3) == true) goto L30;
    L27:
        if ((r3 instanceof TL_stars.TL_starGiftUnique) == false) goto L31;
        if (eq(this.slugStarGift, (TL_stars.TL_starGiftUnique) r3) == false) goto L31;
    L32:
        return -1;
    L8:
        TL_stars.TL_starGiftUnique r23 = this.slugStarGift;
        if (r23 == null) goto L32;
        r02 = r0.indexOf(r23);
        goto L11
    }

    private TL_stars.SavedStarGift getNeighbourSavedGift(boolean r5) {
        int r0 = getListPosition();
        if (r0 >= 0) goto L5;
        return null;
    L5:
        if (r5 == false) goto L7;
        int r2 = 1;
    L8:
        int r22 = r2 + r0;
        int r3 = this.overrideNextIndex;
        if (r3 < 0) goto L15;
        if (r5 == false) goto L13;
        if (r3 <= r0) goto L15;
    L14:
        r22 = r3;
        goto L15
    L13:
        if (r3 < r0) goto L14;
    L15:
        StarsController.IGiftsList r52 = this.giftsList;
        if (r52 == null) goto L21;
        if (r22 < 0) goto L21;
        if (r22 >= r52.getLoadedCount()) goto L21;
        Object r4 = this.giftsList.get(r22);
    L23:
        if ((r4 instanceof TL_stars.SavedStarGift) == true) goto L25;
        return null;
    L25:
        return (TL_stars.SavedStarGift) r4;
    L21:
        r4 = null;
        goto L23
    L7:
        r2 = -1;
        goto L8
    }

    private TL_stars.TL_starGiftUnique getNeighbourSlugGift(boolean r5) {
        int r0 = getListPosition();
        if (r0 >= 0) goto L5;
        return null;
    L5:
        if (r5 == false) goto L7;
        int r2 = 1;
    L8:
        int r22 = r2 + r0;
        int r3 = this.overrideNextIndex;
        if (r3 < 0) goto L15;
        if (r5 == false) goto L13;
        if (r3 <= r0) goto L15;
    L14:
        r22 = r3;
        goto L15
    L13:
        if (r3 < r0) goto L14;
    L15:
        StarsController.IGiftsList r52 = this.giftsList;
        if (r52 == null) goto L21;
        if (r22 < 0) goto L21;
        if (r22 >= r52.getLoadedCount()) goto L21;
        Object r4 = this.giftsList.get(r22);
    L23:
        if ((r4 instanceof TL_stars.TL_starGiftUnique) == true) goto L25;
        return null;
    L25:
        return (TL_stars.TL_starGiftUnique) r4;
    L21:
        r4 = null;
        goto L23
    L7:
        r2 = -1;
        goto L8
    }

    public static CharSequence getRarityName(TL_stars.StarGiftAttributeRarity r2, Integer[] r3) {
        if ((r2 instanceof TL_stars.TL_starGiftAttributeRarityUncommon) == false) goto L9;
        if (r3 == null) goto L7;
        r3[0] = -12539616;
    L7:
        return LocaleController.getString(R.string.GiftRarityUncommon);
    L9:
        if ((r2 instanceof TL_stars.TL_starGiftAttributeRarityRare) == false) goto L15;
        if (r3 == null) goto L13;
        r3[0] = -15619394;
    L13:
        return LocaleController.getString(R.string.GiftRarityRare);
    L15:
        if ((r2 instanceof TL_stars.TL_starGiftAttributeRarityEpic) == false) goto L21;
        if (r3 == null) goto L19;
        r3[0] = -6988581;
    L19:
        return LocaleController.getString(R.string.GiftRarityEpic);
    L21:
        if ((r2 instanceof TL_stars.TL_starGiftAttributeRarityLegendary) == false) goto L27;
        if (r3 == null) goto L25;
        r3[0] = -4229632;
    L25:
        return LocaleController.getString(R.string.GiftRarityLegendary);
    L27:
        if ((r2 instanceof TL_stars.TL_starGiftAttributeRarity) == false) goto L34;
        int r22 = ((TL_stars.TL_starGiftAttributeRarity) r2).permille;
        if (r22 > 0) goto L33;
        return "<0.1%";
    L33:
        return AffiliateProgramFragment.percents(r22);
    L34:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    private boolean hasNeighbour(boolean r2) {
        if (getNeighbourSavedGift(r2) == null) goto L5;
        return true;
    L5:
        if (getNeighbourSlugGift(r2) != null) goto L11;
        return false;
    L11:
        return true;
    }

    private void initTONTransfer(TLRPC.InputCheckPasswordSRP r3, TwoStepVerificationActivity r4) {
        TL_stars.getStarGiftWithdrawalUrl r0 = new TL_stars.getStarGiftWithdrawalUrl();
        TL_stars.InputSavedStarGift r1 = getInputStarGift();
        r0.stargift = r1;
        if (r1 != null) goto L5;
        return;
    L5:
        r0.password = r3;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StarGiftSheet$$ExternalSyntheticLambda179(this, r4));
    }

    public static boolean isMine(int r2, long r3) {
        if (r3 < 0) goto L11;
        if (UserConfig.getInstance(r2).getClientUserId() != r3) goto L8;
        return true;
    L8:
        return false;
    L11:
        return ChatObject.canUserDoAction(MessagesController.getInstance(r2).getChat(Long.valueOf(-r3)), 5);
    }

    public static boolean isMineWithActions(int r5, long r6) {
        if (r6 >= 0) goto L5;
        TLRPC.Chat r52 = MessagesController.getInstance(r5).getChat(Long.valueOf(-r6));
        if (r52 != null) goto L11;
    L13:
        return false;
    L11:
        if (r52.creator == false) goto L13;
        return true;
    L5:
        if (UserConfig.getInstance(r5).getClientUserId() != r6) goto L7;
        return true;
    L7:
        return false;
    }

    public static boolean isWorn(int r5, TL_stars.TL_starGiftUnique r6) {
        if (r6 != null) goto L5;
        return false;
    L5:
        TLRPC.Peer r1 = r6.owner_id;
        if (r1 == null) goto L8;
    L7:
        long r12 = DialogObject.getPeerDialogId(r1);
        if (r12 != 0) goto L13;
        return false;
    L13:
        if (r12 <= 0) goto L22;
        TLRPC.User r52 = MessagesController.getInstance(r5).getUser(Long.valueOf(r12));
        if (r52 == null) goto L29;
        TLRPC.EmojiStatus r53 = r52.emoji_status;
        if ((r53 instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L29;
        if (((TLRPC.TL_emojiStatusCollectible) r53).collectible_id != r6.id) goto L21;
        return true;
    L21:
        return false;
    L29:
        return false;
    L22:
        TLRPC.Chat r54 = MessagesController.getInstance(r5).getChat(Long.valueOf(-r12));
        if (r54 == null) goto L29;
        TLRPC.EmojiStatus r55 = r54.emoji_status;
        if ((r55 instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L29;
        if (((TLRPC.TL_emojiStatusCollectible) r55).collectible_id != r6.id) goto L29;
        return true;
    L8:
        r1 = r6.host_id;
        goto L7
    }

    private /* synthetic */ void lambda$addAttributeRow$44(TL_stars.StarGift r9, TL_stars.StarGiftAttribute r10, ButtonSpan.TextViewButtons[] r11, boolean[] r12, ArrayList r13) {
        if (r13 == null) goto L5;
        new StarGiftPreviewSheet(getContext(), this.resourcesProvider, this.currentAccount, r9.title, r13, false).show();
    L6:
        r12[0] = false;
        return;
    L5:
        showHint(LocaleController.formatString(R.string.Gift2RarityHint, new Object[]{AffiliateProgramFragment.percents(r10.getRarityPermille())}), r11[0], false);
        goto L6
    }

    private /* synthetic */ void lambda$addAttributeRow$45(boolean[] r11, TL_stars.StarGiftAttribute r12, ButtonSpan.TextViewButtons[] r13) {
        if (r11[0] == false) goto L5;
        return;
    L5:
        r11[0] = true;
        TL_stars.StarGift r4 = getGift();
        GiftAuctionController.getInstance(this.currentAccount).requestAuctionUpgrades(r4.gift_id, new StarGiftSheet$$ExternalSyntheticLambda136(this, r4, r12, r13, r11));
    }

    private /* synthetic */ void lambda$cantWithBlockchainGiftAlert$137(TL_stars.TL_starGiftUnique r1, AlertDialog r2, int r3) {
        Browser.openUrlInSystemBrowser(getContext(), "https://fragment.com/gift/" + r1.slug);
    }

    private /* synthetic */ void lambda$convert$103(AlertDialog r4, TLObject r5, long r6, long r8, long r10, TLRPC.TL_error r12) {
        r4.dismissUnless(400);
        BaseFragment r42 = LaunchActivity.getSafeLastFragment();
        if (r42 != null) goto L6;
        return;
    L6:
        if ((r5 instanceof TLRPC.TL_boolTrue) == false) goto L22;
        dismiss();
        StarsController.getInstance(this.currentAccount).invalidateProfileGifts(r6);
        if (r6 < 0) goto L20;
        TLRPC.UserFull r52 = MessagesController.getInstance(this.currentAccount).getUserFull(r8);
        if (r52 == null) goto L14;
        int r62 = Math.max(0, r52.stargifts_count - 1);
        r52.stargifts_count = r62;
        if (r62 > 0) goto L14;
        r52.flags2 &= -257;
    L14:
        StarsController.getInstance(this.currentAccount).invalidateBalance();
        StarsController.getInstance(this.currentAccount).invalidateTransactions(true);
        if ((r42 instanceof StarsIntroActivity) == true) goto L18;
        StarsIntroActivity r3 = new StarsIntroActivity();
        r3.whenFullyVisible(new StarGiftSheet$$ExternalSyntheticLambda176(r3, r10));
        r42.presentFragment(r3);
        return;
    L18:
        BulletinFactory.of(r42).createSimpleBulletin(R.raw.stars_topup, LocaleController.getString(R.string.Gift2ConvertedTitle), LocaleController.formatPluralStringComma("Gift2Converted", (int) r10)).show(true);
        return;
    L20:
        Bundle r53 = new Bundle();
        r53.putLong("chat_id", -r6);
        r53.putBoolean("start_from_monetization", true);
        StatisticActivity r82 = new StatisticActivity(r53);
        BotStarsController.getInstance(this.currentAccount).invalidateStarsBalance(r6);
        BotStarsController.getInstance(this.currentAccount).invalidateTransactions(r6, true);
        r82.whenFullyVisible(new StarGiftSheet$$ExternalSyntheticLambda177(r82, r10));
        r42.presentFragment(r82);
        return;
    L22:
        if (r12 == null) goto L25;
        getBulletinFactory().createErrorBulletin(LocaleController.formatString(R.string.UnknownErrorCode, new Object[]{r12.text})).show(false);
        return;
    L25:
        getBulletinFactory().createErrorBulletin(LocaleController.getString(R.string.UnknownError)).show(false);
    }

    private /* synthetic */ void lambda$convert$104(AlertDialog r12, long r13, long r15, long r17, TLObject r19, TLRPC.TL_error r20) {
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda151(this, r12, r19, r13, r15, r17, r20));
    }

    private /* synthetic */ void lambda$convert$105(TL_stars.InputSavedStarGift r3, long r4, long r6, long r8, AlertDialog r10, int r11) {
        AlertDialog r42 = new AlertDialog(ApplicationLoader.applicationContext, 3);
        r42.showDelayed(500);
        TL_stars.convertStarGift r112 = new TL_stars.convertStarGift();
        r112.stargift = r3;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r112, new StarGiftSheet$$ExternalSyntheticLambda111(this, r42, r4, r6, r8));
    }

    private /* synthetic */ void lambda$doTransfer$156(ChatActivity r4, long r5) {
        BulletinFactory.of(r4).createSimpleBulletin(R.raw.forward, LocaleController.getString(R.string.Gift2TransferredTitle), AndroidUtilities.replaceTags(LocaleController.formatString(R.string.Gift2TransferredText, new Object[]{getGiftName(), DialogObject.getShortName(r5)}))).ignoreDetach().show();
    }

    private /* synthetic */ void lambda$doTransfer$157(Utilities.Callback r4, TLRPC.TL_error r5, TLObject r6, long r7, long r9) {
        if (r4 == null) goto L4;
        r4.run(r5);
    L4:
        BaseFragment r42 = LaunchActivity.getSafeLastFragment();
        if (r42 != null) goto L7;
    L15:
        StarsController.getInstance(this.currentAccount).invalidateProfileGifts(r7);
        StarsController.getInstance(this.currentAccount).invalidateProfileGifts(r9);
        return;
    L7:
        if ((r6 instanceof TLRPC.Updates) == true) goto L9;
        BulletinFactory.of(r42).showForError(r5);
        goto L15
    L9:
        if (r7 >= 0) goto L11;
    L13:
        BulletinFactory.of(r42).createSimpleBulletin(R.raw.forward, LocaleController.getString(R.string.Gift2TransferredTitle), AndroidUtilities.replaceTags(LocaleController.formatString(R.string.Gift2TransferredText, new Object[]{getGiftName(), DialogObject.getShortName(r7)}))).ignoreDetach().show();
        goto L15
    L11:
        if (r9 < 0) goto L13;
        ChatActivity r52 = ChatActivity.of(r7);
        r52.whenFullyVisible(new StarGiftSheet$$ExternalSyntheticLambda174(this, r52, r7));
        r42.presentFragment(r52);
        goto L15
    }

    private /* synthetic */ void lambda$doTransfer$158(Utilities.Callback r10, long r11, long r13, TLObject r15, TLRPC.TL_error r16) {
        if ((r15 instanceof TLRPC.Updates) == false) goto L5;
        MessagesController.getInstance(this.currentAccount).processUpdates((TLRPC.Updates) r15, false);
    L5:
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda150(this, r10, r16, r15, r11, r13));
    }

    private /* synthetic */ void lambda$doTransfer$159(StarsController r1, long r2, Utilities.Callback r4) {
        if (r1.balanceAvailable() == true) goto L6;
        getBulletinFactory().createSimpleBulletin(R.raw.error, LocaleController.formatString(R.string.UnknownErrorCode, new Object[]{"NO_BALANCE"})).ignoreDetach().show();
        return;
    L6:
        doTransfer(r2, r4);
    }

    private /* synthetic */ void lambda$doTransfer$160(ChatActivity r4, long r5) {
        BulletinFactory.of(r4).createSimpleBulletin(R.raw.forward, LocaleController.getString(R.string.Gift2TransferredTitle), AndroidUtilities.replaceTags(LocaleController.formatString(R.string.Gift2TransferredText, new Object[]{getGiftName(), DialogObject.getShortName(r5)}))).ignoreDetach().show();
    }

    private /* synthetic */ void lambda$doTransfer$161(TLRPC.TL_payments_paymentResult r2) {
        MessagesController.getInstance(this.currentAccount).processUpdates(r2.updates, false);
    }

    private /* synthetic */ void lambda$doTransfer$162(boolean[] r3, long r4, Utilities.Callback r6) {
        r3[0] = true;
        this.button.setLoading(false);
        doTransfer(r4, r6);
    }

    private /* synthetic */ void lambda$doTransfer$163(DialogInterface r1) {
        this.button.setLoading(false);
    }

    private /* synthetic */ void lambda$doTransfer$164(long r14, long r16, Utilities.Callback r18) {
        StarsIntroActivity.StarsNeededSheet r6 = new StarsIntroActivity.StarsNeededSheet(getContext(), this.resourcesProvider, r14, 11, null, new StarGiftSheet$$ExternalSyntheticLambda4(this, new boolean[]{false}, r16, r18), 0);
        r6.setOnDismissListener(new StarGiftSheet$$ExternalSyntheticLambda5(this));
        r6.show();
    }

    private /* synthetic */ void lambda$doTransfer$165(TLObject r3, long r4, long r6, Utilities.Callback r8, TLRPC.TL_error r9, long r10) {
        if ((r3 instanceof TLRPC.TL_payments_paymentResult) == false) goto L17;
        TLRPC.TL_payments_paymentResult r32 = (TLRPC.TL_payments_paymentResult) r3;
        MessagesController.getInstance(this.currentAccount).putUsers(r32.updates.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r32.updates.chats, false);
        StarsController.getInstance(this.currentAccount).invalidateTransactions(false);
        StarsController.getInstance(this.currentAccount).invalidateProfileGifts(r4);
        StarsController.getInstance(this.currentAccount).invalidateProfileGifts(r6);
        StarsController.getInstance(this.currentAccount).invalidateBalance();
        if (r8 == null) goto L7;
        r8.run(null);
    L7:
        BaseFragment r82 = LaunchActivity.getSafeLastFragment();
        if (r82 != null) goto L10;
    L15:
        Utilities.stageQueue.postRunnable(new StarGiftSheet$$ExternalSyntheticLambda197(this, r32));
        return;
    L10:
        if (r4 >= 0) goto L12;
    L14:
        BulletinFactory.of(r82).createSimpleBulletin(R.raw.forward, LocaleController.getString(R.string.Gift2TransferredTitle), AndroidUtilities.replaceTags(LocaleController.formatString(R.string.Gift2TransferredText, new Object[]{getGiftName(), DialogObject.getShortName(r4)}))).ignoreDetach().show();
        goto L15
    L12:
        if (r6 < 0) goto L14;
        ChatActivity r62 = ChatActivity.of(r4);
        r62.whenFullyVisible(new StarGiftSheet$$ExternalSyntheticLambda196(this, r62, r4));
        r82.presentFragment(r62);
        goto L15
    L17:
        if (r9 == null) goto L27;
        if ("BALANCE_TOO_LOW".equals(r9.text) == false) goto L27;
        if (MessagesController.getInstance(this.currentAccount).starsPurchaseAvailable() == true) goto L24;
        this.button.setLoading(false);
        StarsController.showNoSupportDialog(getContext(), this.resourcesProvider);
        return;
    L24:
        StarsController.getInstance(this.currentAccount).invalidateBalance(new StarGiftSheet$$ExternalSyntheticLambda198(this, r10, r4, r8));
        return;
    L27:
        if (r8 == null) goto L29;
        r8.run(r9);
    L29:
        getBulletinFactory().showForError(r9);
    }

    private /* synthetic */ void lambda$doTransfer$166(long r12, long r14, Utilities.Callback r16, long r17, TLObject r19, TLRPC.TL_error r20) {
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda188(this, r19, r12, r14, r16, r20, r17));
    }

    private /* synthetic */ void lambda$doTransfer$167(TLObject r12, TLRPC.TL_inputInvoiceStarGiftTransfer r13, long r14, long r16, Utilities.Callback r18, TLRPC.TL_error r19) {
        if ((r12 instanceof TLRPC.PaymentForm) == false) goto L9;
        TLRPC.PaymentForm r0 = (TLRPC.PaymentForm) r12;
        int r4 = 0;
        MessagesController.getInstance(this.currentAccount).putUsers(r0.users, false);
        TL_stars.TL_payments_sendStarsForm r9 = new TL_stars.TL_payments_sendStarsForm();
        r9.form_id = r0.form_id;
        r9.invoice = r13;
        ArrayList<TLRPC.TL_labeledPrice> r02 = r0.invoice.prices;
        int r2 = r02.size();
        long r7 = 0;
    L5:
        if (r4 >= r2) goto L7;
        TLRPC.TL_labeledPrice r3 = r02.get(r4);
        r4 = r4 + 1;
        r7 = r7 + r3.amount;
        goto L5
    L7:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r9, new StarGiftSheet$$ExternalSyntheticLambda165(this, r14, r16, r18, r7));
        return;
    L9:
        if (r18 == null) goto L11;
        r18.run(r19);
    L11:
        getBulletinFactory().makeForError(r19).ignoreDetach().show();
    }

    private /* synthetic */ void lambda$doTransfer$168(TLRPC.TL_inputInvoiceStarGiftTransfer r11, long r12, long r14, Utilities.Callback r16, TLObject r17, TLRPC.TL_error r18) {
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda132(this, r17, r11, r12, r14, r16, r18));
    }

    private /* synthetic */ void lambda$doUpgrade$123() {
        this.button.setLoading(false);
        switchPage(0, true);
    }

    private /* synthetic */ void lambda$doUpgrade$124(TLObject r2) {
        MessagesController.getInstance(this.currentAccount).processUpdates((TLRPC.Updates) r2, false);
    }

    private /* synthetic */ void lambda$doUpgrade$125(TLRPC.TL_error r2, TLObject r3, TL_stars.InputSavedStarGift r4) {
        if (r2 == null) goto L4;
    L8:
        getBulletinFactory().showForError(r2);
        return;
    L4:
        if ((r3 instanceof TLRPC.Updates) == false) goto L8;
        this.upgradedOnce = true;
        this.upgrade_form = null;
        applyNewGiftFromUpdates(r4, (TLRPC.Updates) r3, new StarGiftSheet$$ExternalSyntheticLambda190(this));
        Utilities.stageQueue.postRunnable(new StarGiftSheet$$ExternalSyntheticLambda191(this, r3));
    }

    private /* synthetic */ void lambda$doUpgrade$126(TL_stars.InputSavedStarGift r5, TLObject r6, TLRPC.TL_error r7) {
        if ((r6 instanceof TLRPC.Updates) == false) goto L5;
        TLRPC.Updates r1 = (TLRPC.Updates) r6;
        MessagesController.getInstance(this.currentAccount).putUsers(r1.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r1.chats, false);
    L5:
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda178(this, r7, r6, r5));
    }

    private /* synthetic */ void lambda$doUpgrade$127(StarsController r3) {
        if (r3.balanceAvailable() == true) goto L6;
        getBulletinFactory().createSimpleBulletin(R.raw.error, LocaleController.formatString(R.string.UnknownErrorCode, new Object[]{"NO_BALANCE"})).ignoreDetach().show();
        return;
    L6:
        this.button.setLoading(false);
        doUpgrade();
    }

    private /* synthetic */ void lambda$doUpgrade$128() {
        Bundle r0 = new Bundle();
        long r1 = this.dialogId;
        if (r1 < 0) goto L5;
        r0.putLong("user_id", r1);
    L7:
        if (this.dialogId != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L9;
        r0.putBoolean("my_profile", true);
    L9:
        r0.putBoolean("open_gifts", true);
        r0.putBoolean("open_gifts_upgradable", true);
        presentFragment(new ProfileActivity(r0));
        return;
    L5:
        r0.putLong("chat_id", -r1);
        goto L7
    }

    private /* synthetic */ void lambda$doUpgrade$129(ChatActivity r7) {
        BulletinFactory.of(r7).createSimpleBulletin(R.raw.gift, LocaleController.getString(R.string.StarsGiftUpgradeCompleted), AndroidUtilities.replaceTags(LocaleController.formatString(R.string.StarsGiftUpgradeCompletedText, new Object[]{DialogObject.getShortName(this.dialogId)})), LocaleController.getString(R.string.StarsGiftUpgradeCompletedMoreButton), new StarGiftSheet$$ExternalSyntheticLambda3(this)).show(true);
    }

    private /* synthetic */ void lambda$doUpgrade$130(String r7) {
        this.button.setLoading(false);
        if (TextUtils.isEmpty(r7) == true) goto L16;
        dismiss();
        BaseFragment r72 = LaunchActivity.getLastFragment();
        if (r72 != null) goto L8;
        return;
    L8:
        if ((r72 instanceof ChatActivity) == false) goto L14;
        ChatActivity r1 = (ChatActivity) r72;
        if (r1.getDialogId() != this.dialogId) goto L14;
        BulletinFactory.of(r1).createSimpleBulletin(R.raw.gift, LocaleController.getString(R.string.StarsGiftUpgradeCompleted), AndroidUtilities.replaceTags(LocaleController.formatString(R.string.StarsGiftUpgradeCompletedText, new Object[]{DialogObject.getShortName(this.dialogId)}))).show(true);
        return;
    L14:
        NotificationCenter r0 = NotificationCenter.getInstance(this.currentAccount);
        int r12 = NotificationCenter.closeProfileActivity;
        Long r2 = Long.valueOf(this.dialogId);
        Boolean r3 = Boolean.FALSE;
        r0.postNotificationName(r12, new Object[]{r2, r3});
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.closeChatActivity, new Object[]{Long.valueOf(this.dialogId), r3});
        ChatActivity r02 = ChatActivity.of(this.dialogId);
        r02.whenFullyVisible(new StarGiftSheet$$ExternalSyntheticLambda201(this, r02));
        r72.presentFragment(r02);
        return;
    L16:
        switchPage(0, true);
    }

    private /* synthetic */ void lambda$doUpgrade$131(TLRPC.TL_payments_paymentResult r2) {
        MessagesController.getInstance(this.currentAccount).processUpdates(r2.updates, false);
    }

    private /* synthetic */ void lambda$doUpgrade$132(boolean[] r3) {
        r3[0] = true;
        this.button.setLoading(false);
        doUpgrade();
    }

    private /* synthetic */ void lambda$doUpgrade$133(DialogInterface r1) {
        this.button.setLoading(false);
    }

    private /* synthetic */ void lambda$doUpgrade$134(long r13) {
        StarsIntroActivity.StarsNeededSheet r2 = new StarsIntroActivity.StarsNeededSheet(getContext(), this.resourcesProvider, r13, 10, null, new StarGiftSheet$$ExternalSyntheticLambda199(this, new boolean[]{false}), 0);
        r2.setOnDismissListener(new StarGiftSheet$$ExternalSyntheticLambda200(this));
        r2.show();
    }

    private /* synthetic */ void lambda$doUpgrade$135(TLObject r3, String r4, TL_stars.InputSavedStarGift r5, TLRPC.TL_error r6, long r7) {
        if ((r3 instanceof TLRPC.TL_payments_paymentResult) == false) goto L11;
        TLRPC.TL_payments_paymentResult r32 = (TLRPC.TL_payments_paymentResult) r3;
        MessagesController.getInstance(this.currentAccount).putUsers(r32.updates.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r32.updates.chats, false);
        StarsController.getInstance(this.currentAccount).invalidateTransactions(false);
        StarsController.getInstance(this.currentAccount).invalidateBalance();
        if (TextUtils.isEmpty(r4) == true) goto L9;
        TL_stars.SavedStarGift r62 = this.savedStarGift;
        if (r62 == null) goto L9;
        r62.flags &= -65537;
        r62.prepaid_upgrade_hash = null;
    L9:
        this.upgradedOnce = true;
        this.upgrade_form = null;
        applyNewGiftFromUpdates(r5, r32.updates, new StarGiftSheet$$ExternalSyntheticLambda185(this, r4));
        Utilities.stageQueue.postRunnable(new StarGiftSheet$$ExternalSyntheticLambda186(this, r32));
        return;
    L11:
        if (r6 != null) goto L13;
    L20:
        getBulletinFactory().showForError(r6);
        return;
    L13:
        if ("BALANCE_TOO_LOW".equals(r6.text) == false) goto L20;
        if (MessagesController.getInstance(this.currentAccount).starsPurchaseAvailable() == true) goto L18;
        this.button.setLoading(false);
        StarsController.showNoSupportDialog(getContext(), this.resourcesProvider);
        return;
    L18:
        StarsController.getInstance(this.currentAccount).invalidateBalance(new StarGiftSheet$$ExternalSyntheticLambda187(this, r7));
    }

    private /* synthetic */ void lambda$doUpgrade$136(String r9, TL_stars.InputSavedStarGift r10, long r11, TLObject r13, TLRPC.TL_error r14) {
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda163(this, r13, r9, r10, r14, r11));
    }

    private /* synthetic */ void lambda$initTONTransfer$151(AlertDialog r2, int r3) {
        presentFragment(new TwoStepVerificationSetupActivity(6, null));
    }

    private /* synthetic */ void lambda$initTONTransfer$152(TLRPC.TL_error r1, TLObject r2, TwoStepVerificationActivity r3) {
        if (r1 != null) goto L5;
        TL_account.Password r22 = (TL_account.Password) r2;
        r3.setCurrentPasswordInfo(null, r22);
        TwoStepVerificationActivity.initPasswordNewAlgo(r22);
        initTONTransfer(r3.getNewSrpPassword(), r3);
        return;
    }

    private /* synthetic */ void lambda$initTONTransfer$153(TwoStepVerificationActivity r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda0(this, r4, r3, r2));
    }

    private /* synthetic */ void lambda$initTONTransfer$154(TLRPC.TL_error r28, TwoStepVerificationActivity r29, TLObject r30) {
        if (getContext() == null) goto L77;
        if (r28 != null) goto L7;
        r29.needHideProgress();
        r29.finishFragment();
        if ((r30 instanceof TL_stars.starGiftWithdrawalUrl) == false) goto L76;
        Browser.openUrlInSystemBrowser(getContext(), ((TL_stars.starGiftWithdrawalUrl) r30).url);
        return;
    L76:
        return;
    L7:
        if ("PASSWORD_MISSING".equals(r28.text) == false) goto L9;
    L21:
        if (r29 == null) goto L23;
        r29.needHideProgress();
    L23:
        AlertDialog.Builder r3 = new AlertDialog.Builder(getContext());
        r3.setTitle(LocaleController.getString(R.string.Gift2TransferToTONAlertTitle));
        LinearLayout r5 = new LinearLayout(getContext());
        r5.setPadding(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(24.0f), 0);
        r5.setOrientation(1);
        r3.setView(r5);
        TextView r7 = new TextView(getContext());
        int r8 = Theme.key_dialogTextBlack;
        r7.setTextColor(Theme.getColor(r8));
        r7.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L26;
        int r11 = 5;
    L27:
        r7.setGravity(r11 | 48);
        r7.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.Gift2TransferToTONAlertText)));
        r5.addView(r7, LayoutHelper.createLinear(-1, -2));
        LinearLayout r72 = new LinearLayout(getContext());
        r72.setOrientation(0);
        r5.addView(r72, LayoutHelper.createLinear(-1, -2, 0.0f, 11.0f, 0.0f, 0.0f));
        ImageView r15 = new ImageView(getContext());
        r15.setImageResource(R.drawable.list_circle);
        if (LocaleController.isRTL == false) goto L30;
        int r12 = AndroidUtilities.dp(11.0f);
    L31:
        int r13 = AndroidUtilities.dp(9.0f);
        if (LocaleController.isRTL == false) goto L34;
        int r112 = 0;
    L35:
        r15.setPadding(r12, r13, r112, 0);
        int r122 = Theme.getColor(r8);
        PorterDuff.Mode r132 = PorterDuff.Mode.MULTIPLY;
        r15.setColorFilter(new PorterDuffColorFilter(r122, r132));
        TextView r113 = new TextView(getContext());
        r113.setTextColor(Theme.getColor(r8));
        r113.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L38;
        int r123 = 5;
    L39:
        r113.setGravity(r123 | 48);
        r113.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.Gift2TransferToTONAlertText1)));
        if (LocaleController.isRTL == false) goto L42;
        r72.addView(r113, LayoutHelper.createLinear(-1, -2));
        r72.addView(r15, LayoutHelper.createLinear(-2, -2, 5));
    L43:
        LinearLayout r6 = new LinearLayout(getContext());
        r6.setOrientation(0);
        r5.addView(r6, LayoutHelper.createLinear(-1, -2, 0.0f, 11.0f, 0.0f, 0.0f));
        ImageView r73 = new ImageView(getContext());
        r73.setImageResource(R.drawable.list_circle);
        if (LocaleController.isRTL == false) goto L46;
        int r114 = AndroidUtilities.dp(11.0f);
    L47:
        int r124 = AndroidUtilities.dp(9.0f);
        if (LocaleController.isRTL == false) goto L50;
        int r152 = 0;
    L51:
        r73.setPadding(r114, r124, r152, 0);
        r73.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r8), r132));
        TextView r9 = new TextView(getContext());
        r9.setTextColor(Theme.getColor(r8));
        r9.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L54;
        int r115 = 5;
    L55:
        r9.setGravity(r115 | 48);
        r9.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.Gift2TransferToTONAlertText2)));
        if (LocaleController.isRTL == false) goto L58;
        r6.addView(r9, LayoutHelper.createLinear(-1, -2));
        int r116 = 5;
        r6.addView(r73, LayoutHelper.createLinear(-2, -2, 5));
    L60:
        if ("PASSWORD_MISSING".equals(r28.text) == false) goto L62;
        r3.setPositiveButton(LocaleController.getString(R.string.Gift2TransferToTONSetPassword), new StarGiftSheet$$ExternalSyntheticLambda194(this));
        r3.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
    L67:
        if (r29 == null) goto L70;
        r29.showDialog(r3.create());
        return;
    L70:
        r3.show();
        return;
    L62:
        TextView r1 = new TextView(getContext());
        r1.setTextColor(Theme.getColor(r8));
        r1.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L65;
        int r125 = r116;
    L66:
        r1.setGravity(r125 | 48);
        r1.setText(LocaleController.getString(R.string.Gift2TransferToTONAlertText3));
        r5.addView(r1, LayoutHelper.createLinear(-1, -2, 0.0f, 11.0f, 0.0f, 0.0f));
        r3.setNegativeButton(LocaleController.getString(R.string.OK), null);
        goto L67
    L65:
        r125 = 3;
        goto L66
    L58:
        r116 = 5;
        r6.addView(r73, LayoutHelper.createLinear(-2, -2));
        r6.addView(r9, LayoutHelper.createLinear(-1, -2));
        goto L60
    L54:
        r115 = 3;
        goto L55
    L50:
        r152 = AndroidUtilities.dp(11.0f);
        goto L51
    L46:
        r114 = 0;
        goto L47
    L42:
        r72.addView(r15, LayoutHelper.createLinear(-2, -2));
        r72.addView(r113, LayoutHelper.createLinear(-1, -2));
        goto L43
    L38:
        r123 = 3;
        goto L39
    L34:
        r112 = AndroidUtilities.dp(11.0f);
        goto L35
    L30:
        r12 = 0;
        goto L31
    L26:
        r11 = 3;
        goto L27
    L9:
        if (r28.text.startsWith("PASSWORD_TOO_FRESH_") == true) goto L21;
        if (r28.text.startsWith("SESSION_TOO_FRESH_") == true) goto L21;
        if ("SRP_ID_INVALID".equals(r28.text) == false) goto L17;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TL_account.getPassword(), new StarGiftSheet$$ExternalSyntheticLambda195(this, r29), 8);
        return;
    L17:
        if (r29 == null) goto L19;
        r29.needHideProgress();
        r29.finishFragment();
    L19:
        BulletinFactory.showError(r28);
        return;
    }

    private /* synthetic */ void lambda$initTONTransfer$155(TwoStepVerificationActivity r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda182(this, r4, r2, r3));
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        openCrafting(true);
    }

    private /* synthetic */ void lambda$new$1(View r2) {
        if (r2.getAlpha() >= 1.0f) goto L5;
        return;
    L5:
        openCraftInfo();
    }

    private /* synthetic */ void lambda$new$2(View r2) {
        if (this.button.isLoading() == false) goto L5;
        return;
    L5:
        this.checkbox.setChecked(!r1.isChecked(), true);
    }

    private /* synthetic */ void lambda$onBuyPressed$169(GiftMessageBottomSheet r3, TL_stars.TL_starGiftUnique r4, long r5, AmountUtils$Currency r7, TLRPC.TL_textWithEntities r8, boolean r9) {
        if (r3.isLoading() == false) goto L5;
        return;
    L5:
        performBuyPressed(r4, r5, r7, r8, r9, r3);
    }

    private /* synthetic */ void lambda$onMenuPressed$10() {
        TL_stars.SavedStarGift r0 = this.savedStarGift;
        if (r0.unsaved == false) goto L8;
        r0.unsaved = false;
        StarsController.GiftsCollections r02 = StarsController.getInstance(this.currentAccount).getProfileGiftCollectionsList(this.dialogId, false);
        if (r02 == null) goto L7;
        TL_stars.SavedStarGift r1 = this.savedStarGift;
        r02.updateGiftsUnsaved(r1, r1.unsaved);
    L7:
        TL_stars.saveStarGift r03 = new TL_stars.saveStarGift();
        r03.stargift = getInputStarGift();
        r03.unsave = this.savedStarGift.unsaved;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r03, null, 64);
    L8:
        TL_stars.SavedStarGift r04 = this.savedStarGift;
        boolean r12 = r04.pinned_to_top;
        if (((StarsController.GiftsList) this.giftsList).togglePinned(r04, !r12, false) == false) goto L12;
        new ProfileGiftsContainer.UnpinSheet(getContext(), this.dialogId, this.savedStarGift, this.resourcesProvider, new StarGiftSheet$$ExternalSyntheticLambda101(this)).show();
        return;
    L12:
        if (r12 == true) goto L15;
        getBulletinFactory().createSimpleBulletin(R.raw.ic_pin, LocaleController.getString(R.string.Gift2PinnedTitle), LocaleController.getString(R.string.Gift2PinnedSubtitle)).show();
        return;
    L15:
        getBulletinFactory().createSimpleBulletin(R.raw.ic_unpin, LocaleController.getString(R.string.Gift2Unpinned)).show();
    }

    private /* synthetic */ void lambda$onMenuPressed$11() {
        openCrafting(true);
    }

    private /* synthetic */ void lambda$onMenuPressed$12() {
        onUpdatePriceClick(null);
    }

    private /* synthetic */ void lambda$onMenuPressed$13(String r1) {
        AndroidUtilities.addToClipboard(r1);
        getBulletinFactory().createCopyLinkBulletin(false).ignoreDetach().show();
    }

    private /* synthetic */ void lambda$onMenuPressed$14() {
        onSharePressed(null);
    }

    private /* synthetic */ void lambda$onResellPressed$30(Browser.Progress r3, TL_stars.TL_starGiftUnique r4) {
        r3.end();
        r4.flags &= -17;
        r4.resale_ton_only = false;
        r4.resell_amount = null;
        this.topView.setResellPrice(AmountUtils$Amount.fromNano(0, AmountUtils$Currency.STARS));
        Runnable r32 = this.onGiftUpdatedListener;
        if (r32 == null) goto L5;
        r32.run();
    L5:
        getBulletinFactory().createSimpleBulletin(R.raw.contact_check, LocaleController.formatString(R.string.Gift2ResaleDisable, new Object[]{getGiftName()})).show();
    }

    private /* synthetic */ void lambda$onResellPressed$31(Browser.Progress r2, long r3) {
        r2.end();
        showTimeoutAlert(getContext(), true, (int) r3);
    }

    private /* synthetic */ void lambda$onResellPressed$32(Browser.Progress r1, TLRPC.TL_error r2) {
        r1.end();
        getBulletinFactory().showForError(r2);
    }

    private /* synthetic */ void lambda$onResellPressed$33(Browser.Progress r2, TL_stars.TL_starGiftUnique r3, TLObject r4, TLRPC.TL_error r5) {
        if ((r4 instanceof TLRPC.Updates) == false) goto L6;
        MessagesController.getInstance(this.currentAccount).processUpdates((TLRPC.Updates) r4, false);
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda154(this, r2, r3));
        return;
    L6:
        if (r5 != null) goto L8;
    L11:
        if (r5 == null) goto L14;
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda156(this, r2, r5));
        return;
    L14:
        return;
    L8:
        if (r5.text.startsWith("STARGIFT_RESELL_TOO_EARLY_") == false) goto L11;
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda155(this, r2, Long.parseLong(r5.text.substring(26))));
    }

    private /* synthetic */ void lambda$onResellPressed$34(TL_stars.TL_starGiftUnique r3, AlertDialog r4, int r5) {
        Browser.Progress r42 = r4.makeButtonLoading(-1);
        r42.init();
        TL_stars.updateStarGiftPrice r52 = new TL_stars.updateStarGiftPrice();
        r52.stargift = getInputStarGift();
        r52.resell_amount = TL_stars.StarsAmount.ofStars(0);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r52, new StarGiftSheet$$ExternalSyntheticLambda117(this, r42, r3));
    }

    private /* synthetic */ void lambda$onResellPressed$36(TL_stars.TL_starGiftUnique r4, AmountUtils$Amount r5, Runnable r6) {
        r4.flags |= 16;
        AmountUtils$Currency r0 = r5.currency;
        AmountUtils$Currency r1 = AmountUtils$Currency.TON;
        if (r0 != r1) goto L5;
        boolean r02 = true;
    L6:
        r4.resale_ton_only = r02;
        ArrayList<TL_stars.StarsAmount> r03 = new ArrayList();
        r4.resell_amount = r03;
        r03.add(r5.convertTo(AmountUtils$Currency.STARS).toTl());
        r4.resell_amount.add(r5.convertTo(r1).toTl());
        this.topView.setResellPrice(r5);
        Runnable r42 = this.onGiftUpdatedListener;
        if (r42 == null) goto L9;
        r42.run();
    L9:
        if (r6 == null) goto L11;
        r6.run();
    L11:
        getBulletinFactory().createSimpleBulletin(R.raw.contact_check, LocaleController.formatString(R.string.Gift2ResaleEnable, new Object[]{getGiftName()})).show();
        return;
    L5:
        r02 = false;
        goto L6
    }

    private /* synthetic */ void lambda$onResellPressed$37(long r3, Runnable r5) {
        showTimeoutAlert(getContext(), true, (int) r3);
        if (r5 == null) goto L6;
        r5.run();
        return;
    }

    private /* synthetic */ void lambda$onResellPressed$38(TLRPC.TL_error r1, Runnable r2) {
        getBulletinFactory().showForError(r1);
        if (r2 == null) goto L6;
        r2.run();
        return;
    }

    private /* synthetic */ void lambda$onResellPressed$39(TL_stars.TL_starGiftUnique r2, AmountUtils$Amount r3, Runnable r4, TLObject r5, TLRPC.TL_error r6) {
        if ((r5 instanceof TLRPC.Updates) == false) goto L6;
        MessagesController.getInstance(this.currentAccount).processUpdates((TLRPC.Updates) r5, false);
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda139(this, r2, r3, r4));
        return;
    L6:
        if (r6 != null) goto L8;
    L11:
        if (r6 == null) goto L14;
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda141(this, r6, r4));
        return;
    L14:
        return;
    L8:
        if (r6.text.startsWith("STARGIFT_RESELL_TOO_EARLY_") == false) goto L11;
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda140(this, Long.parseLong(r6.text.substring(26)), r4));
    }

    private /* synthetic */ void lambda$onResellPressed$40(TL_stars.TL_starGiftUnique r4, AmountUtils$Amount r5, Runnable r6) {
        TL_stars.StarsAmount r0 = r5.toTl();
        TL_stars.updateStarGiftPrice r1 = new TL_stars.updateStarGiftPrice();
        r1.stargift = getInputStarGift();
        r1.resell_amount = r0;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new StarGiftSheet$$ExternalSyntheticLambda118(this, r4, r5, r6));
    }

    private /* synthetic */ void lambda$onUpdatePriceClick$26(TL_stars.TL_starGiftUnique r4, AmountUtils$Amount r5, Runnable r6) {
        r4.flags |= 16;
        AmountUtils$Currency r0 = r5.currency;
        AmountUtils$Currency r1 = AmountUtils$Currency.TON;
        if (r0 != r1) goto L5;
        boolean r02 = true;
    L6:
        r4.resale_ton_only = r02;
        ArrayList<TL_stars.StarsAmount> r03 = new ArrayList();
        r4.resell_amount = r03;
        r03.add(r5.convertTo(AmountUtils$Currency.STARS).toTl());
        r4.resell_amount.add(r5.convertTo(r1).toTl());
        this.topView.setResellPrice(r5);
        Runnable r3 = this.onGiftUpdatedListener;
        if (r3 == null) goto L9;
        r3.run();
    L9:
        if (r6 == null) goto L12;
        r6.run();
        return;
    L12:
        return;
    L5:
        r02 = false;
        goto L6
    }

    private /* synthetic */ void lambda$onUpdatePriceClick$27(TLRPC.TL_error r1, Runnable r2) {
        getBulletinFactory().showForError(r1);
        if (r2 == null) goto L6;
        r2.run();
        return;
    }

    private /* synthetic */ void lambda$onUpdatePriceClick$28(TL_stars.TL_starGiftUnique r2, AmountUtils$Amount r3, Runnable r4, TLObject r5, TLRPC.TL_error r6) {
        if ((r5 instanceof TLRPC.Updates) == false) goto L6;
        MessagesController.getInstance(this.currentAccount).processUpdates((TLRPC.Updates) r5, false);
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda145(this, r2, r3, r4));
        return;
    L6:
        if (r6 == null) goto L9;
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda146(this, r6, r4));
        return;
    }

    private /* synthetic */ void lambda$onUpdatePriceClick$29(TL_stars.TL_starGiftUnique r4, AmountUtils$Amount r5, Runnable r6) {
        TL_stars.StarsAmount r0 = r5.toTl();
        TL_stars.updateStarGiftPrice r1 = new TL_stars.updateStarGiftPrice();
        r1.stargift = getInputStarGift();
        r1.resell_amount = r0;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new StarGiftSheet$$ExternalSyntheticLambda102(this, r4, r5, r6));
    }

    private /* synthetic */ void lambda$onWearPressed$18(View r1) {
        this.shownWearInfo = true;
        toggleWear();
    }

    private /* synthetic */ void lambda$openAsLearnMore$100(String r8, TL_stars.starGiftUpgradePreview r9) {
        if (r9 != null) goto L4;
        return;
    L4:
        this.topView.setPreviewingAttributes(r9.sample_attributes);
        switchPage(1, false);
        this.topView.setText(1, LocaleController.getString(R.string.Gift2LearnMoreTitle), LocaleController.formatString(R.string.Gift2LearnMoreText, new Object[]{r8}), null, null);
        this.upgradeFeatureCells[0].setText(LocaleController.getString(R.string.Gift2UpgradeFeature1TextLearn));
        this.upgradeFeatureCells[1].setText(LocaleController.getString(R.string.Gift2UpgradeFeature2TextLearn));
        this.upgradeFeatureCells[2].setText(LocaleController.getString(R.string.Gift2UpgradeFeature3TextLearn));
        this.checkboxLayout.setVisibility(8);
        this.checkboxSeparator.setVisibility(8);
        this.button.setFilled(true);
        this.button.setText(LocaleController.getString(R.string.OK), false);
        this.button.setSubText(null, false);
        this.button.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda164(this));
        show();
    }

    private /* synthetic */ void lambda$openAsLearnMore$99(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$openCraftInfo$9(View r1) {
        openCrafting(false);
    }

    private /* synthetic */ void lambda$openCrafting$3(MessageObject r4, ArrayList r5, TL_stars.StarGift r6) {
        this.nextButtonCrafting = true;
        set(r4);
        switchPage(0, true);
        FireworksOverlay r1 = this.fireworksOverlay;
        if (r1 == null) goto L5;
        r1.start(true);
    L5:
        StarsController.getInstance(this.currentAccount).invalidateBalance();
        StarsController.GiftsList r3 = StarsController.getInstance(this.currentAccount).getProfileGiftsList(UserConfig.getInstance(this.currentAccount).getClientUserId(), false);
        if (r3 == null) goto L9;
        r3.processCrafting(r5, r6);
        return;
    }

    private /* synthetic */ void lambda$openCrafting$4(Utilities.Callback2 r6, ArrayList r7, Runnable r8, TLRPC.Updates r9, TLRPC.TL_error r10) {
        if (r9 == null) goto L20;
        ArrayList r82 = MessagesController.findUpdates(r9, TL_update.TL_updateNewMessage.class);
        int r102 = r82.size();
        int r2 = 0;
    L5:
        if (r2 >= r102) goto L11;
        Object r3 = r82.get(r2);
        r2 = r2 + 1;
        TL_update.TL_updateNewMessage r32 = (TL_update.TL_updateNewMessage) r3;
        TLRPC.Message r4 = r32.message;
        if (r4 == null) goto L5;
        if ((r4.action instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L5;
        MessageObject r83 = new MessageObject(this.currentAccount, r32.message, false, false);
    L12:
        MessagesController.getInstance(this.currentAccount).processUpdates(r9, false);
        if (r83 == null) goto L16;
        TL_stars.StarGift r92 = ((TLRPC.TL_messageActionStarGiftUnique) r83.messageOwner.action).gift;
        r6.run(r92, new StarGiftSheet$$ExternalSyntheticLambda137(this, r83, r7, r92));
        return;
    L16:
        r6.run(null, null);
        StarsController.getInstance(this.currentAccount).invalidateBalance();
        StarsController.GiftsList r5 = StarsController.getInstance(this.currentAccount).getProfileGiftsList(UserConfig.getInstance(this.currentAccount).getClientUserId(), false);
        if (r5 == null) goto L38;
        r5.processCrafting(r7, null);
        return;
    L38:
        return;
    L11:
        r83 = null;
        goto L12
    L20:
        if (r10 != null) goto L22;
        return;
    L22:
        if ("STARGIFT_CRAFT_UNAVAILABLE".equalsIgnoreCase(r10.text) == false) goto L24;
        new AlertDialog.Builder(getContext(), new DarkThemeResourceProvider()).setTitle(LocaleController.getString(R.string.GiftCraftUnavailableTitle)).setMessage(AndroidUtilities.replaceTags(LocaleController.getString(R.string.GiftCraftUnavailableText))).setPositiveButton(LocaleController.getString(R.string.OK), null).show();
    L30:
        r8.run();
        return;
    L24:
        String r62 = r10.text;
        if (r62 != null) goto L27;
    L29:
        getBulletinFactory().showForError(r10);
        goto L30
    L27:
        if (r62.startsWith("STARGIFT_CRAFT_TOO_EARLY_") == false) goto L29;
        new AlertDialog.Builder(getContext(), new DarkThemeResourceProvider()).setTitle(LocaleController.getString(R.string.GiftCraftUnavailableTitle)).setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GiftCraftUnavailableTextTime, new Object[]{LocaleController.formatDateTime(ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() + Long.parseLong(r10.text.substring(25)), true)}))).setPositiveButton(LocaleController.getString(R.string.OK), null).show();
        goto L30
    }

    private /* synthetic */ void lambda$openCrafting$5(ArrayList r6, Utilities.Callback2 r7, Runnable r8) {
        ResaleGiftsFragment.SelectGiftSheet.State r0 = this.giftsToCraft;
        if (r0 == null) goto L5;
        r0.detach();
        this.giftsToCraft = null;
    L5:
        TL_stars.craftStarGift r02 = new TL_stars.craftStarGift();
        int r1 = r6.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L8;
        Object r3 = r6.get(r2);
        r2 = r2 + 1;
        TL_stars.TL_inputSavedStarGiftSlug r4 = new TL_stars.TL_inputSavedStarGiftSlug();
        r4.slug = ((TL_stars.StarGift) r3).slug;
        r02.stargift.add(r4);
        goto L6
    L8:
        ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r02, new AiTonesController$$ExternalSyntheticLambda0(), new StarGiftSheet$$ExternalSyntheticLambda112(this, r7, r6, r8));
    }

    private /* synthetic */ void lambda$openCrafting$7(TL_stars.TL_starGiftUnique r6, Utilities.Callback r7, Boolean r8) {
        if (this.giftsToCraft != null) goto L5;
        ResaleGiftsFragment.SelectGiftSheet.State r0 = new ResaleGiftsFragment.SelectGiftSheet.State(this.currentAccount, r6.gift_id);
        this.giftsToCraft = r0;
        r0.attach();
    L5:
        HashSet<Long> r02 = new HashSet();
        int r2 = 0;
    L7:
        if (r2 >= CraftTopView.m19037$$Nest$fgetgifts(TopView.m19077$$Nest$fgetcraftTopView(this.topView)).length) goto L12;
        CraftTopView.SelectGiftView r3 = CraftTopView.m19037$$Nest$fgetgifts(TopView.m19077$$Nest$fgetcraftTopView(this.topView))[r2];
        if (r3.getGift() == null) goto L11;
        r02.add(Long.valueOf(r3.getGift().id));
    L11:
        r2 = r2 + 1;
        goto L7
    L12:
        new ResaleGiftsFragment.SelectGiftSheet(getContext(), r6.title, this.giftsToCraft).without(r02).setWillBeFirst(r8.booleanValue()).setActionText(AndroidUtilities.replaceTags(LocaleController.formatPluralString("GiftCraftSelect", 4 - r02.size(), new Object[0]))).setOnSelect(new StarGiftSheet$$ExternalSyntheticLambda97(r7)).show();
    }

    private /* synthetic */ void lambda$openCrafting$8() {
        onBackPressed();
    }

    private /* synthetic */ void lambda$openSetAsTheme$16(long r1, TL_stars.TL_starGiftUnique r3, DialogsActivity r4) {
        ChatThemeController.getInstance(this.currentAccount).setDialogTheme(r1, ThemeKey.ofGiftSlug(r3.slug));
        r4.presentFragment(ChatActivity.of(r1), true);
    }

    private /* synthetic */ boolean lambda$openSetAsTheme$17(TL_stars.TL_starGiftUnique r10, DialogsActivity r11, DialogsActivity r12, ArrayList r13, CharSequence r14, boolean r15, boolean r16, int r17, int r18, TopicsFragment r19) {
        if (r13.isEmpty() == false) goto L5;
        return false;
    L5:
        long r122 = ((MessagesStorage.TopicKey) r13.get(0)).dialogId;
        long r6 = ChatThemeController.getInstance(this.currentAccount).getGiftThemeUser(r10.slug);
        if (r6 != 0) goto L8;
    L11:
        ChatThemeController.getInstance(this.currentAccount).setDialogTheme(r122, ThemeKey.ofGiftSlug(r10.slug));
        r11.presentFragment(ChatActivity.of(r122), true);
        return true;
    L8:
        if (r6 == r122) goto L11;
        AlertsCreator.showGiftThemeApplyConfirm(getContext(), this.resourcesProvider, this.currentAccount, r10, r6, new StarGiftSheet$$ExternalSyntheticLambda148(this, r122, r10, r11));
        return true;
    }

    private /* synthetic */ void lambda$openTransfer$138(TwoStepVerificationActivity r1, TLRPC.InputCheckPasswordSRP r2) {
        initTONTransfer(r2, r1);
    }

    private /* synthetic */ void lambda$openTransfer$139(UserSelectorBottomSheet[] r2, Browser.Progress r3, TwoStepVerificationActivity r4) {
        r2[0].dismiss();
        r3.end();
        presentFragment(r4);
    }

    private /* synthetic */ void lambda$openTransfer$140(UserSelectorBottomSheet[] r3, AlertDialog r4, int r5) {
        Browser.Progress r42 = r4.makeButtonLoading(r5);
        TwoStepVerificationActivity r52 = new TwoStepVerificationActivity();
        r52.setDelegate(2, new StarGiftSheet$$ExternalSyntheticLambda158(this, r52));
        r52.setDelegateString(getGiftName());
        r42.init();
        r52.preload(new StarGiftSheet$$ExternalSyntheticLambda159(this, r3, r42, r52));
    }

    private /* synthetic */ void lambda$openTransfer$141(TLRPC.TL_error r1) {
        getBulletinFactory().showForError(r1);
    }

    private /* synthetic */ void lambda$openTransfer$142(Browser.Progress r1, UserSelectorBottomSheet[] r2, TLRPC.TL_error r3) {
        r1.end();
        r2[0].dismiss();
        if (r3 == null) goto L6;
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda183(this, r3));
        return;
    L6:
        dismiss();
    }

    private /* synthetic */ void lambda$openTransfer$143(Long r3, UserSelectorBottomSheet[] r4, Browser.Progress r5) {
        r5.init();
        doTransfer(r3.longValue(), new StarGiftSheet$$ExternalSyntheticLambda175(this, r5, r4));
    }

    private /* synthetic */ void lambda$openTransfer$144(Long r4, UserSelectorBottomSheet[] r5) {
        openTransferAlert(r4.longValue(), new StarGiftSheet$$ExternalSyntheticLambda149(this, r4, r5));
    }

    private /* synthetic */ void lambda$openTransfer$145(TLObject r3, Runnable r4, TLRPC.TL_error r5) {
        if ((r3 instanceof TLRPC.TL_messages_chatFull) == false) goto L10;
        TLRPC.TL_messages_chatFull r32 = (TLRPC.TL_messages_chatFull) r3;
        MessagesController.getInstance(this.currentAccount).putUsers(r32.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r32.chats, false);
        MessagesController.getInstance(this.currentAccount).putChatFull(r32.full_chat);
        if (r32.full_chat.stargifts_available == true) goto L8;
        new AlertDialog.Builder(getContext(), this.resourcesProvider).setTitle(LocaleController.getString(R.string.Gift2ChannelDoesntSupportGiftsTitle)).setMessage(LocaleController.getString(R.string.Gift2ChannelDoesntSupportGiftsText)).setPositiveButton(LocaleController.getString(R.string.OK), null).show();
        return;
    L8:
        r4.run();
        return;
    L10:
        getBulletinFactory().makeForError(r5).ignoreDetach().show();
    }

    private /* synthetic */ void lambda$openTransfer$146(Runnable r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda134(this, r3, r2, r4));
    }

    private /* synthetic */ void lambda$openTransfer$147(TLObject r3, UserSelectorBottomSheet[] r4, Long r5, Runnable r6, TLRPC.TL_error r7) {
        if ((r3 instanceof TLRPC.TL_users_userFull) == false) goto L14;
        TLRPC.TL_users_userFull r32 = (TLRPC.TL_users_userFull) r3;
        MessagesController.getInstance(this.currentAccount).putUsers(r32.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r32.chats, false);
        TLRPC.UserFull r33 = r32.full_user;
        if (r33 == null) goto L12;
        TLRPC.DisallowedGiftsSettings r34 = r33.disallowed_stargifts;
        if (r34 == null) goto L12;
        if (r34.disallow_unique_stargifts == false) goto L12;
        BulletinFactory.of(r4[0].container, this.resourcesProvider).createSimpleBulletin(R.raw.error, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.UserDisallowedGifts, new Object[]{DialogObject.getShortName(r5.longValue())}))).show();
        return;
    L12:
        r6.run();
        return;
    L14:
        getBulletinFactory().makeForError(r7).ignoreDetach().show();
    }

    private /* synthetic */ void lambda$openTransfer$148(UserSelectorBottomSheet[] r8, Long r9, Runnable r10, TLObject r11, TLRPC.TL_error r12) {
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda147(this, r11, r8, r9, r10, r12));
    }

    private /* synthetic */ void lambda$openTransfer$149(int r15, int r16, int r17, TL_stars.TL_starGiftUnique r18, UserSelectorBottomSheet[] r19, Long r20) {
        if (r20.longValue() == (-99)) goto L5;
        StarGiftSheet$$ExternalSyntheticLambda125 r2 = new StarGiftSheet$$ExternalSyntheticLambda125(this, r20, r19);
        if (r20.longValue() >= 0) goto L21;
        TLRPC.ChatFull r0 = MessagesController.getInstance(this.currentAccount).getChatFull(-r20.longValue());
        if (r0 != null) goto L17;
        TLRPC.TL_channels_getFullChannel r02 = new TLRPC.TL_channels_getFullChannel();
        r02.channel = MessagesController.getInstance(this.currentAccount).getInputChannel(-r20.longValue());
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r02, new StarGiftSheet$$ExternalSyntheticLambda126(this, r2));
        return;
    L17:
        if (r0.stargifts_available == true) goto L34;
        new AlertDialog.Builder(getContext(), this.resourcesProvider).setTitle(LocaleController.getString(R.string.Gift2ChannelDoesntSupportGiftsTitle)).setMessage(LocaleController.getString(R.string.Gift2ChannelDoesntSupportGiftsText)).setPositiveButton(LocaleController.getString(R.string.OK), null).show();
        return;
    L34:
        r2.run();
        return;
    L21:
        if (r20.longValue() < 0) goto L34;
        TLRPC.User r4 = MessagesController.getInstance(this.currentAccount).getUser(r20);
        TLRPC.UserFull r5 = MessagesController.getInstance(this.currentAccount).getUserFull(r20.longValue());
        if (r5 == null) goto L30;
        TLRPC.DisallowedGiftsSettings r6 = r5.disallowed_stargifts;
        if (r6 == null) goto L30;
        if (r6.disallow_unique_stargifts == false) goto L30;
        BulletinFactory.of(r19[0].container, this.resourcesProvider).createSimpleBulletin(R.raw.error, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.UserDisallowedGifts, new Object[]{DialogObject.getShortName(r20.longValue())}))).show();
        return;
    L30:
        if (r5 != null) goto L34;
        if (r4 == null) goto L34;
        TLRPC.TL_users_getFullUser r3 = new TLRPC.TL_users_getFullUser();
        r3.id = MessagesController.getInstance(this.currentAccount).getInputUser(r4);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r3, new StarGiftSheet$$ExternalSyntheticLambda127(this, r19, r20, r2));
        return;
    L5:
        if (r15 >= r16) goto L8;
        new AlertDialog.Builder(getContext(), this.resourcesProvider).setTitle(LocaleController.getString(R.string.Gift2ExportTONUnlocksAlertTitle)).setMessage(LocaleController.formatPluralString("Gift2ExportTONUnlocksAlertText", Math.max(1, r17), new Object[0])).setPositiveButton(LocaleController.getString(R.string.OK), null).show();
        return;
    L8:
        LinearLayout r22 = new LinearLayout(getContext());
        r22.setOrientation(1);
        r22.addView(new GiftTransferTopView(getContext(), r18), LayoutHelper.createLinear(-1, -2, 48, 0, -4, 0, 0));
        TextView r32 = new TextView(getContext());
        int r52 = Theme.key_dialogTextBlack;
        r32.setTextColor(Theme.getColor(r52, this.resourcesProvider));
        r32.setTextSize(1, 20.0f);
        r32.setTypeface(AndroidUtilities.bold());
        r32.setText(LocaleController.getString(R.string.Gift2ExportTONFragmentTitle));
        r22.addView(r32, LayoutHelper.createLinear(-1, -2, 48, 24, 4, 24, 14));
        TextView r33 = new TextView(getContext());
        r33.setTextColor(Theme.getColor(r52, this.resourcesProvider));
        r33.setTextSize(1, 16.0f);
        r33.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.Gift2ExportTONFragmentText, new Object[]{getGiftName()})));
        r22.addView(r33, LayoutHelper.createLinear(-1, -2, 48, 24, 0, 24, 4));
        new AlertDialog.Builder(getContext(), this.resourcesProvider).setView(r22).setPositiveButton(LocaleController.getString(R.string.Gift2ExportTONFragmentOpen), new StarGiftSheet$$ExternalSyntheticLambda124(this, r19)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).show();
    }

    private /* synthetic */ void lambda$openUpgrade$116(TL_stars.starGiftUpgradePreview r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        this.sample_attributes = r2.sample_attributes;
        this.prices = r2.prices;
        this.next_prices = r2.next_prices;
        openUpgradeAfter();
    }

    private /* synthetic */ void lambda$openUpgrade$117(TLObject r3, TLRPC.TL_error r4) {
        this.requesting_upgrade_form = false;
        if ((r3 instanceof TLRPC.PaymentForm) == false) goto L6;
        TLRPC.PaymentForm r32 = (TLRPC.PaymentForm) r3;
        MessagesController.getInstance(this.currentAccount).putUsers(r32.users, false);
        this.upgrade_form = r32;
        openUpgradeAfter();
        return;
    L6:
        getBulletinFactory().makeForError(r4).ignoreDetach().show();
    }

    private /* synthetic */ void lambda$openUpgrade$118(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda107(this, r2, r3));
    }

    private /* synthetic */ void lambda$openUpgradeAfter$119(View r1) {
        doUpgrade();
    }

    private /* synthetic */ void lambda$openUpgradeAfter$120(long r3) {
        switchPage(1, true);
        if (r3 <= 0) goto L6;
        AndroidUtilities.cancelRunOnUIThread(this.tickUpgradePriceRunnable);
        AndroidUtilities.runOnUIThread(this.tickUpgradePriceRunnable);
        return;
    }

    private /* synthetic */ void lambda$openValueStats$175(HintView2[] r7, FrameLayout r8, View r9, CharSequence r10) {
        HintView2 r1 = r7[0];
        if (r1 == null) goto L5;
        r1.hide();
    L5:
        CharSequence r102 = AndroidUtilities.replaceTags(r10);
        float r12 = (r9.getX() + ((View) r9.getParent()).getX()) + ((View) ((View) r9.getParent()).getParent()).getX();
        float r2 = (r9.getY() + ((View) r9.getParent()).getY()) + ((View) ((View) r9.getParent()).getParent()).getY();
        if ((r9 instanceof ButtonSpan.TextViewButtons) == false) goto L14;
        Layout r92 = ((ButtonSpan.TextViewButtons) r9).getLayout();
        CharSequence r3 = r92.getText();
        if ((r3 instanceof Spanned) == false) goto L14;
        Spanned r4 = (Spanned) r3;
        ButtonSpan[] r32 = (ButtonSpan[]) r4.getSpans(0, r3.length(), ButtonSpan.class);
        if (r32.length <= 0) goto L14;
        ButtonSpan r5 = r32[0];
        if (r5 == null) goto L14;
        r12 = r12 + (r92.getPrimaryHorizontal(r4.getSpanStart(r5)) + (r32[0].getSize() / 2));
        r2 = r2 + r92.getLineTop(r92.getLineForOffset(r4));
    L14:
        HintView2 r93 = new HintView2(getContext(), 3);
        r7[0] = r93;
        r93.setMultilineText(true);
        r93.setInnerPadding(11.0f, 8.0f, 11.0f, 7.0f);
        r93.setRounding(10.0f);
        r93.setText(r102);
        r93.setOnHiddenListener(new StarGiftSheet$$ExternalSyntheticLambda184(r93));
        r93.setTranslationY((-AndroidUtilities.dp(100.0f)) + r2);
        r93.setMaxWidthPx(AndroidUtilities.dp(300.0f));
        r93.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f));
        r93.setJointPx(0.0f, r12 - AndroidUtilities.dp(4.0f));
        r8.addView(r93, LayoutHelper.createFrame(-1, 100, 55));
        r93.show();
    }

    private /* synthetic */ void lambda$openValueStats$180(Boolean r2) {
        Utilities.Callback<Boolean> r0 = this.closeParentSheet;
        if (r0 == null) goto L6;
        r0.run(r2);
    L6:
        if (r2.booleanValue() == false) goto L8;
        skipDismissAnimation();
    L8:
        dismiss();
    }

    private /* synthetic */ void lambda$openValueStats$181(String r10, long r11, View r13) {
        BaseFragment r132 = LaunchActivity.getLastFragment();
        if (r132 != null) goto L5;
        return;
    L5:
        BaseFragment.BottomSheetParams r0 = new BaseFragment.BottomSheetParams();
        r0.transitionFromLeft = true;
        r0.allowNestedScroll = false;
        ResaleGiftsFragment r2 = new ResaleGiftsFragment(this.dialogId, r10, r11, this.resourcesProvider);
        r2.setCloseParentSheet(new StarGiftSheet$$ExternalSyntheticLambda193(this));
        r132.showAsSheet(r2, r0);
    }

    private /* synthetic */ void lambda$openValueStats$182(TL_stars.UniqueStarGiftValueInfo r1, View r2) {
        Browser.openUrlInSystemBrowser(getContext(), r1.fragment_listed_url);
    }

    private /* synthetic */ void lambda$openValueStats$183(AlertDialog r21, TLObject r22, TLRPC.Document r23, String r24, String r25, String r26, long r27, TLRPC.TL_error r29) {
        r21.dismiss();
        if ((r22 instanceof TL_stars.UniqueStarGiftValueInfo) == false) goto L32;
        TL_stars.UniqueStarGiftValueInfo r1 = (TL_stars.UniqueStarGiftValueInfo) r22;
        BottomSheet.Builder r4 = new BottomSheet.Builder(getContext(), false, this.resourcesProvider);
        LinearLayout r5 = new LinearLayout(getContext());
        r5.setOrientation(1);
        r5.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(20.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(8.0f));
        r5.setClipChildren(false);
        r5.setClipToPadding(false);
        BackupImageView r8 = new BackupImageView(getContext());
        StarsIntroActivity.setGiftImage(r8.getImageReceiver(), r23, 160);
        r5.addView(r8, LayoutHelper.createLinear(160, 160, 1, 0, 0, 0, 0));
        TextView r82 = new TextView(getContext());
        r82.setTextSize(1, 20.0f);
        r82.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText, this.resourcesProvider));
        r82.setTypeface(AndroidUtilities.bold());
        r82.setPadding(AndroidUtilities.dp(20.0f), 0, AndroidUtilities.dp(20.0f), 0);
        r82.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(21.0f), Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider)));
        r82.setGravity(17);
        r5.addView(r82, LayoutHelper.createLinear(-2, 42, 1, 0, 12, 0, 15));
        r82.setText(r24);
        TextView r83 = new TextView(getContext());
        r83.setTextSize(1, 14.0f);
        r83.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, this.resourcesProvider));
        r83.setGravity(17);
        r5.addView(r83, LayoutHelper.createLinear(-2, -2, 1, 16, 0, 16, 19));
        if (r1.value_is_average == false) goto L8;
        r83.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GiftValueAverage, new Object[]{r25})));
    L11:
        FrameLayout r84 = new FrameLayout(getContext());
        r84.setClipChildren(false);
        r84.setClipToPadding(false);
        StarGiftSheet$$ExternalSyntheticLambda167 r10 = new StarGiftSheet$$ExternalSyntheticLambda167(this, new HintView2[1], r84);
        TableView r9 = new TableView(getContext(), this.resourcesProvider);
        r84.addView(r9, LayoutHelper.createFrame(-1, -1, 119));
        r9.addRow(LocaleController.getString(R.string.GiftValueInitialSale), LocaleController.formatYearMonthDay(r1.initial_sale_date, true));
        r9.addRow(LocaleController.getString(R.string.GiftValueInitialPrice), StarsIntroActivity.replaceStarsWithPlain("⭐️" + r1.initial_sale_stars + " (~" + BillingController.getInstance().formatCurrency(r1.initial_sale_price, r1.currency) + ")", 0.8f));
        if (TLObject.hasFlag(r1.flags, 1) == false) goto L18;
        r9.addRow(LocaleController.getString(R.string.GiftValueLastSale), LocaleController.formatYearMonthDay(r1.last_sale_date, true));
        int r7 = ((int) (Math.round((r1.last_sale_price / r1.initial_sale_price) * 1000.0d) / 10)) - 100;
        if (r7 <= 0) goto L16;
        r9.addRow(LocaleController.getString(R.string.GiftValueLastPrice), BillingController.getInstance().formatCurrency(r1.last_sale_price, r1.currency), "+" + LocaleController.formatNumber(r7, ' ') + "%", null);
        goto L18
    L16:
        r9.addRow(LocaleController.getString(R.string.GiftValueLastPrice), BillingController.getInstance().formatCurrency(r1.last_sale_price, r1.currency));
    L18:
        if (TLObject.hasFlag(r1.flags, 4) == false) goto L21;
        StarGiftSheet$$ExternalSyntheticLambda168 r13 = new StarGiftSheet$$ExternalSyntheticLambda168(r10, new ButtonSpan.TextViewButtons[]{(ButtonSpan.TextViewButtons) ((TableView.TableRowContent) r6.getChildAt(1)).getChildAt(0)}, r1, r25);
        TableRow r6 = r9.addRow(LocaleController.getString(R.string.GiftValueMinPrice), BillingController.getInstance().formatCurrency(r1.floor_price, r1.currency), "?", r13);
        r6.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda169(r13));
    L21:
        if (TLObject.hasFlag(r1.flags, 8) == false) goto L23;
        StarGiftSheet$$ExternalSyntheticLambda170 r12 = new StarGiftSheet$$ExternalSyntheticLambda170(r10, new ButtonSpan.TextViewButtons[]{(ButtonSpan.TextViewButtons) ((TableView.TableRowContent) r6.getChildAt(1)).getChildAt(0)}, r1, r25);
        TableRow r62 = r9.addRow(LocaleController.getString(R.string.GiftValueAveragePrice), BillingController.getInstance().formatCurrency(r1.average_price, r1.currency), "?", r12);
        r62.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda171(r12));
    L23:
        r5.addView(r84, LayoutHelper.createLinear(-1, -2, 7, 0, 0, 0, 12));
        if (r1.listed_count <= 0) goto L26;
        ButtonWithCounterView r63 = new ButtonWithCounterView(getContext(), false, this.resourcesProvider);
        SpannableStringBuilder r132 = new SpannableStringBuilder();
        float r262 = 1.0f;
        r132.append(LocaleController.formatNumber(r1.listed_count, ' '));
        r132.append(" ");
        r132.append("e");
        r132.setSpan(new AnimatedEmojiSpan(r23, 1.5f, r63.getTextPaint().getFontMetricsInt()), r132.length() - 1, r132.length(), 33);
        r132.append(" ");
        r132.append(LocaleController.getString(R.string.GiftValueOnSaleTelegram));
        r63.setText(AndroidUtilities.replaceArrows(r132, false, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(1.0f)), false);
        r63.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda172(this, r25, r27));
        r5.addView(r63, LayoutHelper.createLinear(-1, 42, 7, 0, 0, 0, 2));
    L28:
        if (r1.fragment_listed_count <= 0) goto L30;
        ButtonWithCounterView r3 = new ButtonWithCounterView(getContext(), false, this.resourcesProvider);
        SpannableStringBuilder r64 = new SpannableStringBuilder();
        r64.append(LocaleController.formatNumber(r1.fragment_listed_count, ' '));
        r64.append("e");
        r64.setSpan(new AnimatedEmojiSpan(r23, 1.5f, r3.getTextPaint().getFontMetricsInt()), r64.length() - 1, r64.length(), 33);
        r64.append(" ");
        r64.append(LocaleController.getString(R.string.GiftValueOnSaleFragment));
        r3.setText(AndroidUtilities.replaceArrows(r64, false, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(r262)), false);
        r3.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda173(this, r1));
        r5.addView(r3, LayoutHelper.createLinear(-1, 42, 7, 0, 0, 0, 0));
    L30:
        r4.setCustomView(r5);
        r4.show();
        return;
    L26:
        r262 = 1.0f;
        goto L28
    L8:
        if (r1.last_sale_on_fragment == false) goto L10;
        r83.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GiftValueLastFragment, new Object[]{r26})));
        goto L11
    L10:
        r83.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GiftValueLastTelegram, new Object[]{r26})));
        goto L11
    L32:
        if (r29 == null) goto L35;
        getBulletinFactory().showForError(r29);
        return;
    }

    private /* synthetic */ void lambda$openValueStats$184(AlertDialog r12, TLRPC.Document r13, String r14, String r15, String r16, long r17, TLObject r19, TLRPC.TL_error r20) {
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda135(this, r12, r19, r13, r14, r15, r16, r17, r20));
    }

    private /* synthetic */ void lambda$performBuyPressed$170(Browser.Progress r1, TL_stars.TL_starGiftUnique r2, long r3, GiftMessageBottomSheet r5, Boolean r6, String r7) {
        r1.end();
        if (r6.booleanValue() == false) goto L14;
        BoughtGiftCallback r12 = this.boughtGift;
        if (r12 == null) goto L10;
        if (r5 == null) goto L8;
        boolean r62 = true;
    L9:
        r12.onBoughtGift(r2, r3, r62);
        goto L10
    L8:
        r62 = false;
    L10:
        if (r5 == null) goto L12;
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda189(r5));
        skipDismissAnimation();
    L12:
        dismiss();
        return;
    }

    private /* synthetic */ void lambda$performBuyPressed$171(TL_stars.TL_starGiftUnique r13, long r14, TLRPC.TL_textWithEntities r16, boolean r17, GiftMessageBottomSheet r18, PaymentFormState r19, Browser.Progress r20) {
        r20.init();
        StarsController.getInstance(this.currentAccount, r19.currency).buyResellingGift(r19.form, r13, r14, r16, r17, new StarGiftSheet$$ExternalSyntheticLambda180(this, r20, r13, r14, r18));
    }

    private /* synthetic */ void lambda$performBuyPressed$172(GiftMessageBottomSheet r15, AmountUtils$Currency r16, TL_stars.TL_starGiftUnique r17, long r18, TLRPC.TL_textWithEntities r20, boolean r21, TLRPC.TL_payments_paymentFormStarGift r22) {
        this.button.setLoading(false);
        if (r15 == null) goto L5;
        r15.setLoading(false);
    L5:
        if (r22 != null) goto L7;
        return;
    L7:
        PaymentFormState r8 = new PaymentFormState(r16, r22);
        new ResaleBuyTransferAlert(getContext(), this.resourcesProvider, r17, r8, this.currentAccount, r18, getGiftName(), false, new StarGiftSheet$$ExternalSyntheticLambda157(this, r17, r18, r20, r21, r15)).show();
    }

    private /* synthetic */ void lambda$releasedByText$49(String r4) {
        dismiss();
        Browser.openUrl(getContext(), "https://" + MessagesController.getInstance(this.currentAccount).linkPrefix + "/" + r4);
    }

    private /* synthetic */ void lambda$repollMessage$96(TLObject r4, MessageObject r5) {
        TLRPC.messages_Messages r42 = (TLRPC.messages_Messages) r4;
        MessagesController.getInstance(this.currentAccount).putUsers(r42.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r42.chats, false);
        this.messageObjectRepolled = true;
        this.messageObjectRepolling = false;
        Boolean r0 = this.unsavedFromSavedStarGift;
        if (r0 == null) goto L13;
        if (r5 == null) goto L13;
        TLRPC.Message r1 = r5.messageOwner;
        if (r1 == null) goto L13;
        TLRPC.MessageAction r12 = r1.action;
        if ((r12 instanceof TLRPC.TL_messageActionStarGift) == false) goto L11;
        boolean r43 = true ^ r0.booleanValue();
        ((TLRPC.TL_messageActionStarGift) r12).saved = r43;
        goto L13
    L11:
        if ((r12 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L13;
        boolean r44 = true ^ r0.booleanValue();
        ((TLRPC.TL_messageActionStarGiftUnique) r12).saved = r44;
    L13:
        set(r5);
    }

    private /* synthetic */ void lambda$repollMessage$97(int r6, TLObject r7, TLRPC.TL_error r8) {
        if ((r7 instanceof TLRPC.messages_Messages) == false) goto L17;
        TLRPC.messages_Messages r82 = (TLRPC.messages_Messages) r7;
        int r1 = 0;
    L6:
        if (r1 >= r82.messages.size()) goto L17;
        TLRPC.Message r2 = r82.messages.get(r1);
        if (r2 == null) goto L16;
        if (r2.id != r6) goto L16;
        TLRPC.MessageAction r3 = r2.action;
        if ((r3 instanceof TLRPC.TL_messageActionStarGift) == true) goto L15;
        if ((r3 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L16;
    L15:
        MessageObject r62 = new MessageObject(this.currentAccount, r2, false, false);
        r62.setType();
    L18:
        if (r62 == null) goto L27;
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda144(this, r7, r62));
        return;
    L27:
        return;
    L16:
        r1 = r1 + 1;
    L17:
        r62 = null;
        goto L18
    }

    private /* synthetic */ void lambda$repollSavedStarGift$98(TL_stars.SavedStarGift r6) {
        this.userStarGiftRepolling = false;
        this.userStarGiftRepolled = true;
        if (r6 == null) goto L22;
        this.unsavedFromSavedStarGift = Boolean.valueOf(r6.unsaved);
        MessageObject r1 = this.messageObject;
        if (r1 == null) goto L23;
        TLRPC.Message r2 = r1.messageOwner;
        if (r2 == null) goto L24;
        TLRPC.MessageAction r22 = r2.action;
        if ((r22 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L15;
        TLRPC.TL_messageActionStarGiftUnique r23 = (TLRPC.TL_messageActionStarGiftUnique) r22;
        boolean r3 = r23.saved;
        boolean r62 = r6.unsaved;
        if (r3 == (!r62)) goto L25;
        r23.saved = !r62;
    L20:
        set(r1);
        return;
    L25:
        return;
    L15:
        if ((r22 instanceof TLRPC.TL_messageActionStarGift) == false) goto L20;
        TLRPC.TL_messageActionStarGift r24 = (TLRPC.TL_messageActionStarGift) r22;
        boolean r32 = r24.saved;
        boolean r63 = r6.unsaved;
        if (r32 == (!r63)) goto L26;
        r24.saved = !r63;
        goto L20
    L26:
        return;
    L24:
        return;
    L23:
        return;
    }

    private /* synthetic */ void lambda$repostStory$41(Long r5) {
        if (r5.longValue() >= 0) goto L7;
        TLRPC.Chat r52 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r5.longValue()));
        if (r52 == null) goto L7;
        String r53 = r52.title;
    L8:
        BulletinFactory r4 = getBulletinFactory();
        int r0 = R.raw.contact_check;
        if (TextUtils.isEmpty(r53) == false) goto L11;
        String r54 = LocaleController.getString(R.string.GiftRepostedToProfile);
    L12:
        r4.createSimpleBulletin(r0, AndroidUtilities.replaceTags(r54)).ignoreDetach().show();
        return;
    L11:
        r54 = LocaleController.formatString(R.string.GiftRepostedToChannelProfile, new Object[]{r53});
    L7:
        r53 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L8
    }

    private /* synthetic */ void lambda$repostStory$42(StoryRecorder r1, View r2, Long r3, Runnable r4, Boolean r5, Long r6) {
        boolean r32 = r5.booleanValue();
        StoryRecorder.SourceView r52 = null;
        if (r32 == false) goto L8;
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda153(this, r6));
        r1.replaceSourceView(null);
        ShareAlert r12 = this.shareAlert;
        if (r12 == null) goto L13;
        r12.dismiss();
        this.shareAlert = null;
    L13:
        AndroidUtilities.runOnUIThread(r4);
        return;
    L8:
        if ((r2 instanceof ShareDialogCell) == true) goto L10;
    L12:
        r1.replaceSourceView(r52);
        goto L13
    L10:
        if (r2.isAttachedToWindow() == false) goto L12;
        r52 = StoryRecorder.SourceView.fromShareCell((ShareDialogCell) r2);
        goto L12
    }

    private /* synthetic */ void lambda$set$46(String r3) {
        Browser.openUrlInSystemBrowser(getContext(), MessagesController.getInstance(this.currentAccount).tonBlockchainExplorerUrl + r3);
    }

    private /* synthetic */ void lambda$set$47(String r3) {
        Browser.openUrlInSystemBrowser(getContext(), MessagesController.getInstance(this.currentAccount).tonBlockchainExplorerUrl + r3);
    }

    private /* synthetic */ void lambda$set$48(View r1) {
        onBuyPressed();
    }

    private /* synthetic */ void lambda$set$51() {
        getBulletinFactory().createSimpleBulletin(R.raw.copy, LocaleController.getString(R.string.WalletAddressCopied)).show(false);
    }

    private /* synthetic */ void lambda$set$52(long r1) {
        openProfile(r1);
    }

    private /* synthetic */ void lambda$set$53() {
        getBulletinFactory().createSimpleBulletin(R.raw.copy, LocaleController.getString(R.string.WalletAddressCopied)).show(false);
    }

    private /* synthetic */ void lambda$set$54(long r1) {
        openProfile(r1);
    }

    private /* synthetic */ void lambda$set$55(TL_stars.TL_starGiftUnique r9, String r10) {
        openValueStats(r9.gift_id, r9.title, getGiftName(), r10, r9.getDocument(), r9.slug);
    }

    private /* synthetic */ void lambda$set$56(CharSequence r1, View r2) {
        showDeleteDescriptionAlert(r1);
    }

    private /* synthetic */ void lambda$set$57(View r1) {
        onBuyPressed();
    }

    private /* synthetic */ void lambda$set$58(int r2, View r3) {
        this.overrideNextIndex = r2;
        ViewPagerFixed r32 = this.viewPager;
        int r0 = r32.getCurrentPosition();
        if (r2 <= getListPosition()) goto L5;
        int r1 = 1;
    L6:
        r32.scrollToPosition(r0 + r1);
        return;
    L5:
        r1 = -1;
        goto L6
    }

    private /* synthetic */ void lambda$set$59(View r1) {
        onBackPressed();
    }

    private /* synthetic */ void lambda$set$60(int r2, View r3) {
        this.overrideNextIndex = r2;
        ViewPagerFixed r32 = this.viewPager;
        int r0 = r32.getCurrentPosition();
        if (r2 <= getListPosition()) goto L5;
        int r1 = 1;
    L6:
        r32.scrollToPosition(r0 + r1);
        return;
    L5:
        r1 = -1;
        goto L6
    }

    private /* synthetic */ void lambda$set$61(View r1) {
        onBackPressed();
    }

    private /* synthetic */ void lambda$set$62() {
        if (Roller.m19066$$Nest$fgetmodelRoller(this.roller) != null) goto L5;
    L7:
        ImageReceiver r0 = null;
    L8:
        BackupImageView r2 = TopView.m19080$$Nest$fgetimageView(this.topView)[0];
        if (r0 == null) goto L20;
        if (r2 == null) goto L20;
        if (r2.getImageReceiver() == null) goto L20;
        RLottieDrawable r4 = r0.getLottieAnimation();
        RLottieDrawable r5 = r2.getImageReceiver().getLottieAnimation();
        if (r5 == null) goto L17;
        if (r4 == null) goto L17;
        r5.setProgress(r4.getProgress(), false);
    L17:
        if (r5 != null) goto L20;
        if (r4 == null) goto L20;
        r0.clearImage();
        r2.setImageDrawable(r4);
    L20:
        this.topView.imageLayout.setAlpha(1.0f);
        TopView.m19082$$Nest$fgetimagesRollView(this.topView).setAlpha(0.0f);
        if (this.upgradedOnce == true) goto L23;
    L39:
        this.button.setFilled(true);
        this.button.setText(LocaleController.getString(R.string.OK), true);
        this.button.setSubText(null, true);
        this.button.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda109(this));
        return;
    L23:
        if (this.viewPager == null) goto L39;
        if (this.giftsList == null) goto L39;
        if (getListPosition() < 0) goto L39;
        if (this.giftsList.findGiftToUpgrade(getListPosition()) < 0) goto L39;
        this.button.setFilled(false);
        int r02 = this.giftsList.findGiftToUpgrade(getListPosition());
        SpannableStringBuilder r3 = new SpannableStringBuilder();
        r3.append(LocaleController.getString(R.string.Gift2UpgradeNext));
        Object r42 = this.giftsList.get(r02);
        if ((r42 instanceof TL_stars.SavedStarGift) == false) goto L37;
        TL_stars.StarGift r43 = ((TL_stars.SavedStarGift) r42).gift;
        if (r43 == null) goto L37;
        TLRPC.Document r44 = r43.getDocument();
        if (r44 == null) goto L37;
        r3.append(" e");
        r3.setSpan(new AnimatedEmojiSpan(r44, this.button.getTextPaint().getFontMetricsInt()), r3.length() - 1, r3.length(), 33);
    L37:
        this.button.setText(r3, true);
        this.button.setSubText(null, true);
        this.button.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda108(this, r02));
        return;
    L5:
        if (Roller.m19066$$Nest$fgetmodelRoller(this.roller).current == 0) goto L7;
        r0 = ((Roller.Sticker) Roller.m19066$$Nest$fgetmodelRoller(this.roller).current).imageReceiver;
        goto L8
    }

    private /* synthetic */ void lambda$set$63() {
        if (getGift() == null) goto L5;
        String r0 = getGift().title + " #" + LocaleController.formatNumber(getGift().num, ',');
    L6:
        getBulletinFactory().createSimpleBulletin(R.raw.gift_upgrade, LocaleController.getString(R.string.Gift2UpgradedTitle), AndroidUtilities.replaceTags(LocaleController.formatString(R.string.Gift2UpgradedText, new Object[]{r0}))).setDuration(5000).ignoreDetach().show();
        FireworksOverlay r5 = this.fireworksOverlay;
        if (r5 == null) goto L10;
        r5.start(true);
        return;
    L10:
        return;
    L5:
        r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    private /* synthetic */ void lambda$set$64(View r1) {
        this.roller.skip();
    }

    private /* synthetic */ void lambda$set$65() {
        this.recyclerListView.scrollToPosition(this.adapter.getItemCount() - 1);
    }

    private /* synthetic */ void lambda$set$73() {
        new ExplainStarsSheet(getContext()).show();
    }

    private /* synthetic */ void lambda$set$74(long r1) {
        openProfile(r1);
    }

    private /* synthetic */ void lambda$set$75(long r7) {
        new GiftSheet(getContext(), this.currentAccount, r7, new StarGiftSheet$$ExternalSyntheticLambda64(this)).show();
    }

    private /* synthetic */ void lambda$set$76(View r1) {
        openUpgrade();
    }

    private /* synthetic */ void lambda$set$77(int r2, View r3) {
        this.overrideNextIndex = r2;
        ViewPagerFixed r32 = this.viewPager;
        int r0 = r32.getCurrentPosition();
        if (r2 <= getListPosition()) goto L5;
        int r1 = 1;
    L6:
        r32.scrollToPosition(r0 + r1);
        return;
    L5:
        r1 = -1;
        goto L6
    }

    private /* synthetic */ void lambda$set$78(View r1) {
        openUpgrade();
    }

    private /* synthetic */ void lambda$set$79(View r1) {
        onBackPressed();
    }

    private /* synthetic */ void lambda$set$80(String r3) {
        Browser.openUrlInSystemBrowser(getContext(), MessagesController.getInstance(this.currentAccount).tonBlockchainExplorerUrl + r3);
    }

    private /* synthetic */ void lambda$set$81(String r3) {
        Browser.openUrlInSystemBrowser(getContext(), MessagesController.getInstance(this.currentAccount).tonBlockchainExplorerUrl + r3);
    }

    private /* synthetic */ void lambda$set$82() {
        new ExplainStarsSheet(getContext()).show();
    }

    private /* synthetic */ void lambda$set$83(long r1) {
        openProfile(r1);
    }

    private /* synthetic */ void lambda$set$84(long r7) {
        new GiftSheet(getContext(), this.currentAccount, r7, new StarGiftSheet$$ExternalSyntheticLambda64(this)).show();
    }

    private /* synthetic */ void lambda$set$85(long r1) {
        openProfile(r1);
    }

    private /* synthetic */ void lambda$set$86(long r7) {
        new GiftSheet(getContext(), this.currentAccount, r7, new StarGiftSheet$$ExternalSyntheticLambda64(this)).show();
    }

    private /* synthetic */ void lambda$set$87(long r1) {
        openProfile(r1);
    }

    private /* synthetic */ void lambda$set$88(long r7) {
        new GiftSheet(getContext(), this.currentAccount, r7, new StarGiftSheet$$ExternalSyntheticLambda64(this)).show();
    }

    private /* synthetic */ void lambda$set$89(View r1) {
        openUpgrade();
    }

    private /* synthetic */ void lambda$set$90(int r2, View r3) {
        this.overrideNextIndex = r2;
        ViewPagerFixed r32 = this.viewPager;
        int r0 = r32.getCurrentPosition();
        if (r2 <= getListPosition()) goto L5;
        int r1 = 1;
    L6:
        r32.scrollToPosition(r0 + r1);
        return;
    L5:
        r1 = -1;
        goto L6
    }

    private /* synthetic */ void lambda$set$91(View r1) {
        openUpgrade();
    }

    private /* synthetic */ void lambda$set$92(View r1) {
        onBackPressed();
    }

    private /* synthetic */ void lambda$set$93(View r1) {
        openCrafting(true);
    }

    private /* synthetic */ void lambda$set$94(String r3) {
        Browser.openUrlInSystemBrowser(getContext(), MessagesController.getInstance(this.currentAccount).tonBlockchainExplorerUrl + r3);
    }

    private /* synthetic */ void lambda$set$95(String r3) {
        Browser.openUrlInSystemBrowser(getContext(), MessagesController.getInstance(this.currentAccount).tonBlockchainExplorerUrl + r3);
    }

    private /* synthetic */ void lambda$setupWearPage$19(View r1) {
        this.shownWearInfo = true;
        toggleWear();
    }

    private /* synthetic */ void lambda$show$109(TL_stars.TL_payments_uniqueStarGift r2) {
        TL_stars.TL_starGiftUnique r22 = (TL_stars.TL_starGiftUnique) r2.gift;
        this.slugStarGift = r22;
        set(r22, false);
        super.show();
    }

    private /* synthetic */ void lambda$show$111(AlertDialog r4, TLObject r5, TLRPC.TL_error r6) {
        if ((r5 instanceof TL_stars.TL_payments_uniqueStarGift) == false) goto L8;
        TL_stars.TL_payments_uniqueStarGift r52 = (TL_stars.TL_payments_uniqueStarGift) r5;
        MessagesController.getInstance(this.currentAccount).putUsers(r52.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r52.chats, false);
        if ((r52.gift instanceof TL_stars.TL_starGiftUnique) == false) goto L8;
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda53(this, r52));
        return;
    L8:
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda54(r4, r6));
    }

    private /* synthetic */ void lambda$show$112(AlertDialog r1, MessageObject r2) {
        r1.dismiss();
        this.messageObjectRepolled = true;
        set(r2);
        super.show();
    }

    private /* synthetic */ void lambda$show$114(TLRPC.TL_messageActionStarGift r5, AlertDialog r6, TLObject r7, TLRPC.TL_error r8) {
        if ((r7 instanceof TLRPC.messages_Messages) == false) goto L15;
        TLRPC.messages_Messages r72 = (TLRPC.messages_Messages) r7;
        MessagesController.getInstance(this.currentAccount).putUsers(r72.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r72.chats, false);
        int r82 = 0;
    L6:
        if (r82 >= r72.messages.size()) goto L15;
        TLRPC.Message r0 = r72.messages.get(r82);
        if (r0 == null) goto L14;
        if ((r0 instanceof TLRPC.TL_messageEmpty) == true) goto L14;
        if (r0.id != r5.upgrade_msg_id) goto L14;
        MessageObject r52 = new MessageObject(this.currentAccount, r0, false, false);
        r52.setType();
    L16:
        if (r52 == null) goto L19;
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda62(this, r6, r52));
        return;
    L19:
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda63(r6));
        return;
    L14:
        r82 = r82 + 1;
    L15:
        r52 = null;
        goto L16
    }

    private /* synthetic */ void lambda$show$115(AlertDialog r1, TL_stars.SavedStarGift r2) {
        if (r2 == null) goto L5;
        r1.dismiss();
        this.userStarGiftRepolled = true;
        set(r2, null);
        super.show();
        return;
    L5:
        r1.dismiss();
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 == null) goto L9;
        BulletinFactory.of(r0).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.MessageNotFound)).ignoreDetach().show();
        return;
    }

    private /* synthetic */ void lambda$showDeleteDescriptionAlert$66(TL_stars.TL_starGiftUnique r5) {
        getBulletinFactory().createSimpleBulletin(R.raw.ic_delete, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GiftRemovedDescription, new Object[]{r5.title + " #" + r5.num}))).show();
    }

    private /* synthetic */ void lambda$showDeleteDescriptionAlert$67(CharSequence r1) {
        showDeleteDescriptionAlert(r1);
    }

    private /* synthetic */ void lambda$showDeleteDescriptionAlert$68(Browser.Progress r11, AlertDialog r12, TLObject r13, TL_stars.TL_starGiftUnique r14, TLRPC.TL_error r15, long r16, CharSequence r18) {
        r11.end();
        r12.dismiss();
        if ((r13 instanceof TLRPC.TL_payments_paymentResult) == false) goto L16;
        boolean r112 = false;
        int r122 = 0;
    L6:
        if (r122 >= r14.attributes.size()) goto L11;
        if ((r14.attributes.get(r122) instanceof TL_stars.starGiftAttributeOriginalDetails) == false) goto L10;
        r14.attributes.remove(r122);
        r122 = r122 - 1;
    L10:
        r122 = r122 + 1;
        goto L6
    L11:
        TL_stars.SavedStarGift r123 = this.savedStarGift;
        if (r123 == null) goto L14;
        r112 = r123.refunded;
    L14:
        set(r14, r112);
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda1(this, r14));
        return;
    L16:
        if (r15 != null) goto L18;
    L21:
        if (r15 == null) goto L27;
        getBulletinFactory().showForError(r15);
        return;
    L27:
        return;
    L18:
        if ("BALANCE_TOO_LOW".equalsIgnoreCase(r15.text) == false) goto L21;
        new StarsIntroActivity.StarsNeededSheet(getContext(), this.resourcesProvider, r16, 16, null, new StarGiftSheet$$ExternalSyntheticLambda2(this, r18), 0).show();
    }

    private /* synthetic */ void lambda$showDeleteDescriptionAlert$69(Browser.Progress r11, AlertDialog r12, TL_stars.TL_starGiftUnique r13, long r14, CharSequence r16, TLObject r17, TLRPC.TL_error r18) {
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda202(this, r11, r12, r17, r13, r18, r14, r16));
    }

    private /* synthetic */ void lambda$showDeleteDescriptionAlert$70(TL_stars.TL_starGiftUnique r4, TLRPC.PaymentForm r5, TLRPC.TL_inputInvoiceStarGiftDropOriginalDetails r6, long r7, CharSequence r9, AlertDialog r10, int r11) {
        if (r4 != null) goto L4;
        return;
    L4:
        Browser.Progress r112 = r10.makeButtonLoading(-1);
        r112.init();
        TL_stars.TL_payments_sendStarsForm r0 = new TL_stars.TL_payments_sendStarsForm();
        r0.form_id = r5.form_id;
        r0.invoice = r6;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StarGiftSheet$$ExternalSyntheticLambda192(this, r112, r10, r4, r7, r9));
    }

    private /* synthetic */ void lambda$showDeleteDescriptionAlert$71(TLObject r16, CharSequence r17, TL_stars.TL_starGiftUnique r18, TLRPC.TL_inputInvoiceStarGiftDropOriginalDetails r19, TLRPC.TL_error r20) {
        if ((r16 instanceof TLRPC.PaymentForm) == false) goto L9;
        TLRPC.PaymentForm r3 = (TLRPC.PaymentForm) r16;
        ArrayList<TLRPC.TL_labeledPrice> r0 = r3.invoice.prices;
        int r2 = r0.size();
        long r5 = 0;
        int r7 = 0;
    L5:
        if (r7 >= r2) goto L7;
        TLRPC.TL_labeledPrice r8 = r0.get(r7);
        r7 = r7 + 1;
        r5 = r5 + r8.amount;
        goto L5
    L7:
        LinearLayout r02 = new LinearLayout(getContext());
        r02.setOrientation(1);
        r02.setPadding(AndroidUtilities.dp(23.0f), 0, AndroidUtilities.dp(23.0f), 0);
        TextView r72 = TextHelper.makeTextView(getContext(), 16.0f, Theme.key_dialogTextBlack, false);
        r72.setText(LocaleController.getString(R.string.Gift2RemoveDescriptionText));
        r02.addView(r72, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 16.0f));
        TableView r73 = new TableView(getContext(), this.resourcesProvider);
        TableView.TableRowFullContent r9 = r73.addFullRow(r17);
        r9.setFilled(true);
        SpoilersTextView r4 = (SpoilersTextView) r9.getChildAt(0);
        r4.setTextSize(1, 12.0f);
        r4.setGravity(17);
        r02.addView(r73, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 0.0f));
        new AlertDialog.Builder(getContext(), this.resourcesProvider).setTitle(LocaleController.getString(R.string.Gift2RemoveDescriptionTitle)).setView(r02).setNegativeButton(LocaleController.getString(R.string.Cancel), null).setPositiveButton(StarsIntroActivity.replaceStars(LocaleController.formatString(R.string.Gift2RemoveDescriptionButton, new Object[]{Integer.valueOf((int) r5)})), new StarGiftSheet$$ExternalSyntheticLambda166(this, r18, r3, r19, r5, r17)).show();
        return;
    L9:
        if (r20 == null) goto L13;
        getBulletinFactory().showForError(r20);
        return;
    }

    private /* synthetic */ void lambda$showDeleteDescriptionAlert$72(CharSequence r8, TL_stars.TL_starGiftUnique r9, TLRPC.TL_inputInvoiceStarGiftDropOriginalDetails r10, TLObject r11, TLRPC.TL_error r12) {
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda133(this, r11, r8, r9, r10, r12));
    }

    private /* synthetic */ void lambda$showGiftOfferSheet$15() {
        Utilities.Callback<Boolean> r0 = this.closeParentSheet;
        if (r0 == null) goto L5;
        r0.run(Boolean.FALSE);
    L5:
        dismiss();
    }

    private /* synthetic */ void lambda$switchPage$43(ValueAnimator r2) {
        this.currentPage.setProgress(((Float) r2.getAnimatedValue()).floatValue());
        onSwitchedPage();
    }

    private /* synthetic */ void lambda$tickUpgradePrice$121(TLObject r3, TLRPC.TL_error r4) {
        this.requesting_upgrade_form = false;
        if ((r3 instanceof TLRPC.PaymentForm) == false) goto L6;
        TLRPC.PaymentForm r32 = (TLRPC.PaymentForm) r3;
        MessagesController.getInstance(this.currentAccount).putUsers(r32.users, false);
        this.upgrade_form = r32;
        AndroidUtilities.cancelRunOnUIThread(this.tickUpgradePriceRunnable);
        AndroidUtilities.runOnUIThread(this.tickUpgradePriceRunnable);
        return;
    L6:
        getBulletinFactory().makeForError(r4).ignoreDetach().show();
    }

    private /* synthetic */ void lambda$tickUpgradePrice$122(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda115(this, r2, r3));
    }

    private /* synthetic */ void lambda$toggleShow$107(TLObject r3, boolean r4, TLRPC.Document r5, boolean r6, TLRPC.TL_error r7, TL_stars.saveStarGift r8) {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 != null) goto L6;
        return;
    L6:
        if ((r3 instanceof TLRPC.TL_boolTrue) == false) goto L36;
        dismiss();
        long r72 = getDialogId();
        if (r4 == true) goto L11;
        StarsController.getInstance(this.currentAccount).invalidateProfileGifts(r72);
    L11:
        if (r72 < 0) goto L26;
        BulletinFactory r2 = BulletinFactory.of(r0);
        if (r6 == false) goto L15;
        int r32 = R.string.Gift2MadePrivateTitle;
    L16:
        String r33 = LocaleController.getString(r32);
        if (r6 == false) goto L19;
        int r42 = R.string.Gift2MadePrivate;
    L20:
        String r43 = LocaleController.getString(r42);
        if ((r0 instanceof ProfileActivity) == false) goto L23;
        StarGiftSheet$$ExternalSyntheticLambda138 r62 = null;
    L24:
        r2.createEmojiBulletin(r5, r33, AndroidUtilities.replaceSingleTag(r43, r62)).show(true);
        return;
    L23:
        r62 = new StarGiftSheet$$ExternalSyntheticLambda138(r72, r0);
        goto L24
    L19:
        r42 = R.string.Gift2MadePublic;
        goto L20
    L15:
        r32 = R.string.Gift2MadePublicTitle;
        goto L16
    L26:
        BulletinFactory r22 = BulletinFactory.of(r0);
        if (r6 == false) goto L29;
        int r34 = R.string.Gift2ChannelMadePrivateTitle;
    L30:
        String r35 = LocaleController.getString(r34);
        if (r6 == false) goto L33;
        int r44 = R.string.Gift2ChannelMadePrivate;
    L34:
        r22.createEmojiBulletin(r5, r35, LocaleController.getString(r44)).show();
        return;
    L33:
        r44 = R.string.Gift2ChannelMadePublic;
        goto L34
    L29:
        r34 = R.string.Gift2ChannelMadePublicTitle;
        goto L30
    L36:
        if (r7 != null) goto L38;
        return;
    L38:
        if (r4 == true) goto L40;
    L44:
        getBulletinFactory().createErrorBulletin(LocaleController.formatString(R.string.UnknownErrorCode, new Object[]{r7.text})).show(false);
        return;
    L40:
        if (this.savedStarGift == null) goto L44;
        StarsController.GiftsCollections r45 = StarsController.getInstance(this.currentAccount).getProfileGiftCollectionsList(this.dialogId, false);
        if (r45 == null) goto L44;
        r45.updateGiftsUnsaved(this.savedStarGift, !r8.unsave);
        goto L44
    }

    private /* synthetic */ void lambda$toggleShow$108(boolean r9, TLRPC.Document r10, boolean r11, TL_stars.saveStarGift r12, TLObject r13, TLRPC.TL_error r14) {
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda123(this, r13, r9, r10, r11, r14, r12));
    }

    private /* synthetic */ void lambda$toggleWear$20() {
        new PremiumFeatureBottomSheet(getDummyFragment(), 12, false).show();
    }

    private /* synthetic */ void lambda$toggleWear$21(TLRPC.Chat r1) {
        presentFragment(StatisticActivity.create(r1));
    }

    private /* synthetic */ void lambda$toggleWear$22(TL_stories.TL_premium_boostsStatus r9, long r10, MessagesController r12, ChannelBoostsController.CanApplyBoost r13) {
        this.button.setLoading(false);
        LimitReachedBottomSheet r2 = new LimitReachedBottomSheet(getDummyFragment(), getContext(), 26, this.currentAccount, this.resourcesProvider);
        r2.setCanApplyBoost(r13);
        r2.setBoostsStats(r9, true);
        r2.setDialogId(r10);
        TLRPC.Chat r92 = r12.getChat(Long.valueOf(-r10));
        if (r92 == null) goto L5;
        r2.showStatisticButtonInLink(new StarGiftSheet$$ExternalSyntheticLambda181(this, r92));
    L5:
        r2.show();
    }

    private /* synthetic */ void lambda$toggleWear$23(MessagesController r8, long r9, TL_stories.TL_premium_boostsStatus r11) {
        if (r11 == null) goto L8;
        if (r11.level >= r8.channelEmojiStatusLevelMin) goto L8;
        r8.getBoostsController().userCanBoostChannel(r9, r11, new StarGiftSheet$$ExternalSyntheticLambda142(this, r11, r9, r8));
        return;
    L8:
        this.button.setLoading(false);
        toggleWear(true);
    }

    private /* synthetic */ void lambda$toggleWear$24(boolean r3) {
        if (r3 == false) goto L4;
        int r32 = R.string.Gift2ActionWearDone;
    L5:
        showHint(AndroidUtilities.replaceTags(LocaleController.formatString(r32, new Object[]{getGiftName()})), this.ownerTextView, true);
        return;
    L4:
        r32 = R.string.Gift2ActionWearOffDone;
        goto L5
    }

    private /* synthetic */ void lambda$toggleWear$25(View r1) {
        onBackPressed();
    }

    private void onMenuPressed(View r9) {
        String r0 = getLink();
        TL_stars.TL_starGiftUnique r1 = getUniqueGift();
        ItemOptions r92 = ItemOptions.makeOptions(this.container, this.resourcesProvider, r9);
        boolean r3 = true;
        if (getUniqueGift() != null) goto L5;
    L13:
        boolean r2 = false;
    L14:
        TL_stars.SavedStarGift r5 = this.savedStarGift;
        if (r5 != null) goto L17;
    L19:
        int r6 = R.drawable.msg_pin;
    L20:
        if (r5 != null) goto L22;
    L24:
        int r52 = R.string.Gift2Pin;
    L25:
        ItemOptions r93 = r92.addIf(r2, r6, LocaleController.getString(r52), new StarGiftSheet$$ExternalSyntheticLambda40(this)).addIf(canCraft(), R.drawable.outline_craft, LocaleController.getString(R.string.GiftCraft), new StarGiftSheet$$ExternalSyntheticLambda41(this));
        if (getUniqueGift() != null) goto L28;
    L32:
        boolean r22 = false;
    L33:
        ItemOptions r94 = r93.addIf(r22, R.drawable.menu_edit_price, LocaleController.getString(R.string.Gift2ChangePrice), new StarGiftSheet$$ExternalSyntheticLambda42(this));
        if (r0 == null) goto L36;
        boolean r23 = true;
    L37:
        ItemOptions r95 = r94.addIf(r23, R.drawable.msg_link, LocaleController.getString(R.string.CopyLink), new StarGiftSheet$$ExternalSyntheticLambda43(this, r0));
        if (r0 == null) goto L40;
        boolean r02 = true;
    L41:
        ItemOptions r96 = r95.addIf(r02, R.drawable.msg_share, LocaleController.getString(R.string.ShareFile), new StarGiftSheet$$ExternalSyntheticLambda44(this));
        if (r1 != null) goto L44;
    L46:
        boolean r03 = false;
    L47:
        ItemOptions r97 = r96.addIf(r03, R.drawable.input_suggest_paid_24, LocaleController.getString(R.string.GiftOfferToBuyMenu), new StarGiftSheet$$ExternalSyntheticLambda45(this)).addIf(canSetAsTheme(), R.drawable.msg_colors, LocaleController.getString(R.string.GiftThemesSetIn), new StarGiftSheet$$ExternalSyntheticLambda46(this)).addIf(canTransfer(), R.drawable.menu_feature_transfer, LocaleController.getString(R.string.Gift2TransferOption), new StarGiftSheet$$ExternalSyntheticLambda47(this));
        if (this.savedStarGift == null) goto L50;
    L52:
        r3 = false;
    L53:
        r97.addIf(r3, R.drawable.msg_view_file, LocaleController.getString(R.string.Gift2ViewInProfile), new StarGiftSheet$$ExternalSyntheticLambda48(this)).setDrawScrim(false).setOnTopOfScrim().setDimAlpha(0).translate(0.0f, -AndroidUtilities.dp(2.0f)).show();
        return;
    L50:
        if (getDialogId() == 0) goto L52;
    L44:
        if (r1.offer_min_stars <= 0) goto L46;
        r03 = true;
        goto L47
    L40:
        r02 = false;
        goto L41
    L36:
        r23 = false;
        goto L37
    L28:
        if (isMineWithActions(this.currentAccount, DialogObject.getPeerDialogId(getUniqueGift().owner_id)) == false) goto L32;
        if (getUniqueGift().resell_amount == null) goto L32;
        r22 = true;
        goto L33
    L22:
        if (r5.pinned_to_top == false) goto L24;
        r52 = R.string.Gift2Unpin;
        goto L25
    L17:
        if (r5.pinned_to_top == false) goto L19;
        r6 = R.drawable.msg_unpin;
        goto L20
    L5:
        if (isMineWithActions(this.currentAccount, DialogObject.getPeerDialogId(getUniqueGift().owner_id)) == false) goto L13;
        if ((this.giftsList instanceof StarsController.GiftsList) == false) goto L13;
        if (this.savedStarGift == null) goto L13;
        if (getInputStarGift() == null) goto L13;
        r2 = true;
        goto L14
    }

    private void onSwitchedPage() {
        int r2 = 0;
        this.infoLayout.setAlpha(this.currentPage.at(0));
        this.upgradeLayout.setAlpha(this.currentPage.at(1));
        this.wearLayout.setAlpha(this.currentPage.at(2));
        this.craftLayout.setAlpha(this.currentPage.at(3));
        this.buttonContainer.setAlpha(1.0f - this.currentPage.at(4));
        this.topView.onSwitchPage(this.currentPage);
        ImageView r0 = TopView.m19078$$Nest$fgetcraftView(this.topView);
        if (this.currentPage.is(0) == true) goto L5;
    L7:
        int r1 = 8;
    L8:
        r0.setVisibility(r1);
        this.actionView.setAlpha(this.currentPage.at(0) * Utilities.clamp01(AndroidUtilities.ilerp(this.container.top() - this.actionView.getHeight(), 0.0f, AndroidUtilities.dp(32.0f))));
        this.container.updateTranslations();
        this.container.invalidate();
        FrameLayout r02 = this.buttonContainer;
        if (this.currentPage.is(4) == false) goto L11;
        r2 = 8;
    L11:
        r02.setVisibility(r2);
        updateUnderButtonContainer();
        return;
    L5:
        if (showCraft() == false) goto L7;
        r1 = 0;
        goto L8
    }

    private void onWearPressed(View r8) {
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L5;
    L10:
        TL_stars.TL_starGiftUnique r82 = getUniqueGift();
        if (r82 != null) goto L13;
        return;
    L13:
        TLRPC.Peer r0 = r82.owner_id;
        if (r0 == null) goto L16;
    L15:
        long r02 = DialogObject.getPeerDialogId(r0);
        this.wearTitle.setText(LocaleController.formatString(R.string.Gift2WearTitle, new Object[]{r82.title + " #" + LocaleController.formatNumber(r82.num, ',')}));
        SpannableStringBuilder r83 = new SpannableStringBuilder(LocaleController.getString(R.string.Gift2WearStart));
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L23;
        r83.append(" l");
        if (this.lockSpan != null) goto L22;
        this.lockSpan = new ColoredImageSpan(R.drawable.msg_mini_lock3);
    L22:
        r83.setSpan(this.lockSpan, r83.length() - 1, r83.length(), 33);
    L23:
        this.button.setText(r83, true);
        this.button.setSubText(null, true);
        this.button.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda95(this));
        this.topView.setWearPreview(MessagesController.getInstance(this.currentAccount).getUserOrChat(r02));
        switchPage(2, true);
        return;
    L16:
        r0 = r82.host_id;
        goto L15
    L5:
        if (isWorn(this.currentAccount, getUniqueGift()) == false) goto L7;
    L8:
        toggleWear();
        return;
    L7:
        if (this.shownWearInfo == false) goto L10;
        goto L8
    }

    private void openCraftInfo() {
        this.button.setText(LocaleController.getString(R.string.GiftCraftInfoButton), true);
        this.button.setSubText(null, true);
        this.button.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda77(this));
        this.topView.setText(3, LocaleController.getString(R.string.GiftCraftInfoTitle), LocaleController.getString(R.string.GiftCraftInfoText), null, null);
        switchPage(3, true);
    }

    private void openCrafting(boolean r11) {
        MessageObject r0 = this.messageObject;
        if (r0 == null) goto L9;
        TLRPC.Message r02 = r0.messageOwner;
        if (r02 == null) goto L12;
        TLRPC.MessageAction r03 = r02.action;
        if ((r03 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L12;
        int r04 = ((TLRPC.TL_messageActionStarGiftUnique) r03).can_craft_at;
    L14:
        if (r04 <= ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()) goto L17;
        new AlertDialog.Builder(getContext(), this.resourcesProvider).setTitle(LocaleController.getString(R.string.GiftCraftLaterTitle)).setMessage(LocaleController.formatString(R.string.GiftCraftLaterText, new Object[]{LocaleController.formatDateTime(r04, true)})).setPositiveButton(LocaleController.getString(R.string.OK), null).show();
        return;
    L17:
        TL_stars.TL_starGiftUnique r05 = getUniqueGift();
        if (r05 != null) goto L21;
        return;
    L21:
        if (TextUtils.isEmpty(r05.gift_address) == true) goto L24;
        new AlertDialog.Builder(getContext(), this.resourcesProvider).setTitle(LocaleController.getString(R.string.GiftCraftCantChooseFirstTitle)).setMessage(LocaleController.getString(R.string.GiftCraftCantChooseFirst)).setPositiveButton(LocaleController.getString(R.string.OK), null).show();
        return;
    L24:
        if (r11 == false) goto L28;
        TopView.m19077$$Nest$fgetcraftTopView(this.topView).setup(this.currentAccount, r05.gift_id, r05.getDocument(), r05.title);
        if (canCraft() == false) goto L28;
        TopView.m19077$$Nest$fgetcraftTopView(this.topView).selectGift(getUniqueGift());
    L28:
        TopView.m19077$$Nest$fgetcraftTopView(this.topView).setOnCraft(new StarGiftSheet$$ExternalSyntheticLambda56(this));
        if (this.giftsToCraft != null) goto L31;
        ResaleGiftsFragment.SelectGiftSheet.State r112 = new ResaleGiftsFragment.SelectGiftSheet.State(this.currentAccount, r05.gift_id);
        this.giftsToCraft = r112;
        r112.attach();
    L31:
        TopView.m19077$$Nest$fgetcraftTopView(this.topView).setOnAddGift(new StarGiftSheet$$ExternalSyntheticLambda57(this, r05));
        TopView.m19077$$Nest$fgetcraftTopView(this.topView).setOnClose(new StarGiftSheet$$ExternalSyntheticLambda58(this));
        switchPage(4, true);
        return;
    L12:
        r04 = 0;
        goto L14
    L9:
        TL_stars.SavedStarGift r06 = this.savedStarGift;
        if (r06 == null) goto L12;
        r04 = r06.can_craft_at;
        goto L14
    }

    private void openInProfile() {
        long r0 = getDialogId();
        if (r0 != 0) goto L5;
        return;
    L5:
        openProfile(r0);
    }

    private void openProfile(long r7) {
        HintView2 r0 = this.currentHintView;
        if (r0 == null) goto L5;
        r0.hide();
        this.currentHintView = null;
    L5:
        dismiss();
        BaseFragment r02 = LaunchActivity.getSafeLastFragment();
        if (r02 != null) goto L8;
        return;
    L8:
        if (UserObject.isService(r7) == true) goto L19;
        Bundle r1 = new Bundle();
        if (r7 <= 0) goto L15;
        r1.putLong("user_id", r7);
        if (r7 != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L16;
        r1.putBoolean("my_profile", true);
    L16:
        r1.putBoolean("open_gifts", true);
        r02.presentFragment(new ProfileActivity(r1));
        return;
    L15:
        r1.putLong("chat_id", -r7);
        goto L16
    }

    private void openSetAsTheme() {
        dismiss();
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        TL_stars.TL_starGiftUnique r1 = getUniqueGift();
        if (r0 == null) goto L8;
        if (r1 == null) goto L9;
        Bundle r2 = new Bundle();
        r2.putBoolean("onlySelect", true);
        r2.putInt("dialogsType", 4);
        DialogsActivity r3 = new DialogsActivity(r2);
        r3.setDelegate(new StarGiftSheet$$ExternalSyntheticLambda110(this, r1, r3));
        r0.presentFragment(r3);
        return;
    L9:
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void openTransferAlert(long r22, long r24, Utilities.Callback<Browser.Progress> r26) {
        TL_stars.TL_starGiftUnique r5 = getUniqueGift();
        if (r5 != null) goto L5;
        return;
    L5:
        int r9 = this.currentAccount;
        if (r22 < 0) goto L8;
        TLRPC.User r8 = MessagesController.getInstance(r9).getUser(Long.valueOf(r22));
        String r92 = UserObject.getForcedFirstName(r8);
        TLRPC.Chat r82 = r8;
    L12:
        LinearLayout r10 = new LinearLayout(getContext());
        r10.setOrientation(1);
        r10.addView(new GiftTransferTopView(getContext(), r5, r82), LayoutHelper.createLinear(-1, -2, 48, 0, -4, 0, 0));
        TextView r83 = new TextView(getContext());
        r83.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, this.resourcesProvider));
        r83.setTextSize(1, 16.0f);
        if (r24 <= 0) goto L15;
        String r1 = LocaleController.formatPluralStringComma("Gift2TransferPriceText", (int) r24, new Object[]{getGiftName(), DialogObject.getShortName(r22)});
    L16:
        r83.setText(AndroidUtilities.replaceTags(r1));
        r10.addView(r83, LayoutHelper.createLinear(-1, -2, 48, 24, 4, 24, 4));
        TableView r12 = new TableView(getContext(), this.resourcesProvider);
        addAttributeRow(r12, StarsController.findAttribute(r5.attributes, TL_stars.starGiftAttributeModel.class));
        addAttributeRow(r12, StarsController.findAttribute(r5.attributes, TL_stars.starGiftAttributeBackdrop.class));
        addAttributeRow(r12, StarsController.findAttribute(r5.attributes, TL_stars.starGiftAttributePattern.class));
        if (TextUtils.isEmpty(r5.slug) == false) goto L19;
    L21:
        r10.addView(r12, LayoutHelper.createLinear(-1, -2, 48, 23, 16, 23, 4));
        AlertDialog.Builder r0 = new AlertDialog.Builder(getContext(), this.resourcesProvider).setView(r10);
        if (r24 <= 0) goto L24;
        CharSequence r13 = StarsIntroActivity.replaceStars(LocaleController.formatString(R.string.Gift2TransferDoPrice, new Object[]{Integer.valueOf((int) r24)}));
    L25:
        r0.setPositiveButton(r13, new StarGiftSheet$$ExternalSyntheticLambda78(r26)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create().setShowStarsBalance(true).show();
        return;
    L24:
        r13 = LocaleController.getString(R.string.Gift2TransferDo);
        goto L25
    L19:
        if ((r5.flags & 256) == 0) goto L21;
        String r2 = BillingController.getInstance().formatCurrency(r5.value_amount, r5.value_currency, BillingController.getInstance().getCurrencyExp(r5.value_currency), true);
        r12.addRow(LocaleController.getString(R.string.GiftValue2), "~" + r2);
        goto L21
    L15:
        r1 = LocaleController.formatString(R.string.Gift2TransferText, new Object[]{getGiftName(), r92});
        goto L16
    L8:
        TLRPC.Chat r84 = MessagesController.getInstance(r9).getChat(Long.valueOf(-r22));
        if (r84 != null) goto L11;
        r92 = _UrlKt.FRAGMENT_ENCODE_SET;
        r82 = r84;
        goto L12
    L11:
        r92 = r84.title;
        r82 = r84;
        goto L12
    }

    private void openUpgrade() {
        HintView2 r1 = this.currentHintView;
        if (r1 == null) goto L6;
        r1.hide();
        this.currentHintView = null;
    L6:
        if (this.switchingPagesAnimator != null) goto L92;
        TL_stars.InputSavedStarGift r12 = getInputStarGift();
        if (r12 == null) goto L93;
        MessageObject r2 = this.messageObject;
        if (r2 == null) goto L28;
        TLRPC.MessageAction r22 = r2.messageOwner.action;
        if ((r22 instanceof TLRPC.TL_messageActionStarGift) == false) goto L88;
        TLRPC.TL_messageActionStarGift r23 = (TLRPC.TL_messageActionStarGift) r22;
        long r7 = r23.gift.id;
        long r9 = r23.upgrade_stars;
        boolean r11 = r23.name_hidden;
        TLRPC.TL_textWithEntities r122 = r23.message;
        if (r122 != null) goto L18;
    L20:
        boolean r123 = false;
    L21:
        boolean r13 = r23.peer instanceof TLRPC.TL_peerChannel;
        String r14 = r23.prepaid_upgrade_hash;
        if (r23.prepaid_upgrade == true) goto L24;
        boolean r24 = r23.upgrade_separate;
    L47:
        if (r11 == false) goto L53;
        TextView r124 = this.checkboxTextView;
        if (r13 == false) goto L51;
        int r132 = R.string.Gift2AddMyNameNameChannel;
    L52:
        r124.setText(LocaleController.getString(r132));
    L57:
        CheckBox2 r125 = this.checkbox;
        if (r11 == false) goto L60;
    L63:
        boolean r25 = false;
    L64:
        r125.setChecked(r25, false);
        ArrayList<TL_stars.StarGiftAttribute> r26 = this.sample_attributes;
        if (r26 != null) goto L67;
    L73:
        if (r26 != null) goto L76;
        StarsController.getInstance(this.currentAccount).getStarGiftPreview(r7, new StarGiftSheet$$ExternalSyntheticLambda59(this));
    L76:
        if (r9 <= 0) goto L78;
        return;
    L78:
        if (this.upgrade_form != null) goto L91;
        this.requesting_upgrade_form = true;
        TLRPC.TL_payments_getPaymentForm r27 = new TLRPC.TL_payments_getPaymentForm();
        if (TextUtils.isEmpty(r14) == true) goto L82;
        TLRPC.TL_inputInvoiceStarGiftPrepaidUpgrade r15 = new TLRPC.TL_inputInvoiceStarGiftPrepaidUpgrade();
        r15.hash = r14;
        r15.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        r27.invoice = r15;
    L83:
        JSONObject r16 = BotWebViewSheet.makeThemeParams(this.resourcesProvider);
        if (r16 == null) goto L86;
        TLRPC.TL_dataJSON r3 = new TLRPC.TL_dataJSON();
        r27.theme_params = r3;
        r3.data = r16.toString();
        r27.flags |= 1;
    L86:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r27, new StarGiftSheet$$ExternalSyntheticLambda60(this));
        return;
    L82:
        TLRPC.TL_inputInvoiceStarGiftUpgrade r32 = new TLRPC.TL_inputInvoiceStarGiftUpgrade();
        r32.keep_original_details = this.checkbox.isChecked();
        r32.stargift = r12;
        r27.invoice = r32;
        goto L83
    L91:
        return;
    L67:
        if (r9 <= 0) goto L69;
    L71:
        openUpgradeAfter();
        return;
    L69:
        if (this.upgrade_form != null) goto L71;
    L60:
        if (r9 <= 0) goto L63;
        if (r24 == true) goto L63;
        r25 = true;
        goto L64
    L51:
        r132 = R.string.Gift2AddMyNameName;
        goto L52
    L53:
        TextView r133 = this.checkboxTextView;
        if (r123 == false) goto L56;
        r133.setText(LocaleController.getString(R.string.Gift2AddSenderNameComment));
        goto L57
    L56:
        r133.setText(LocaleController.getString(R.string.Gift2AddSenderName));
        goto L57
    L24:
        if (DialogObject.getPeerDialogId(r23.from_id) == this.messageObject.getFromChatId()) goto L26;
        r24 = true;
        goto L47
    L26:
        r24 = false;
        goto L47
    L18:
        if (TextUtils.isEmpty(r122.text) == true) goto L20;
        r123 = true;
        goto L21
    L88:
        return;
    L28:
        TL_stars.SavedStarGift r28 = this.savedStarGift;
        if (r28 == null) goto L89;
        TL_stars.StarGift r72 = r28.gift;
        long r8 = r72.id;
        long r10 = r28.upgrade_stars;
        if ((r72 instanceof TL_stars.TL_starGift) == true) goto L33;
    L35:
        boolean r73 = false;
    L36:
        TLRPC.TL_textWithEntities r29 = r28.message;
        if (r29 != null) goto L39;
    L41:
        r123 = false;
    L43:
        if (this.dialogId >= 0) goto L45;
        r13 = true;
    L46:
        TL_stars.SavedStarGift r210 = this.savedStarGift;
        r14 = r210.prepaid_upgrade_hash;
        r24 = r210.upgrade_separate;
        r11 = r73;
        r7 = r8;
        r9 = r10;
        goto L47
    L45:
        r13 = false;
        goto L46
    L39:
        if (TextUtils.isEmpty(r29.text) == true) goto L41;
        r123 = true;
        goto L43
    L33:
        if (r28.name_hidden == false) goto L35;
        r73 = true;
        goto L36
    L89:
        return;
    L93:
        return;
    }

    private void openUpgradeAfter() {
        MessageObject r0 = this.messageObject;
        if (r0 == null) goto L12;
        TLRPC.MessageAction r02 = r0.messageOwner.action;
        if ((r02 instanceof TLRPC.TL_messageActionStarGift) == false) goto L87;
        TLRPC.TL_messageActionStarGift r03 = (TLRPC.TL_messageActionStarGift) r02;
        long r5 = r03.upgrade_stars;
        if (r5 <= 0) goto L9;
    L11:
        boolean r04 = false;
    L20:
        if (this.sample_attributes != null) goto L22;
        return;
    L22:
        if (r5 <= 0) goto L24;
    L26:
        long r6 = 0;
        if (this.upgrade_form == null) goto L33;
        int r52 = 0;
    L30:
        if (r52 >= this.upgrade_form.invoice.prices.size()) goto L33;
        r6 = r6 + this.upgrade_form.invoice.prices.get(r52).amount;
        r52 = r52 + 1;
    L33:
        if (this.roller != null) goto L35;
        this.roller = new Roller(this.topView);
    L35:
        this.roller.preload(this.sample_attributes);
        this.topView.setPreviewingAttributes(this.sample_attributes);
        TopView r9 = this.topView;
        if (r04 == false) goto L38;
        r9.setText(1, LocaleController.getString(R.string.Gift2PrepayUpgradeTitle), LocaleController.formatString(R.string.Gift2PrepayUpgradeText, new Object[]{DialogObject.getShortName(this.currentAccount, this.dialogId)}), null, null);
    L39:
        this.button.setFilled(true);
        TL_stars.StarGiftUpgradePrice r8 = null;
        this.button.setSubText(null, true);
        if (r6 <= 0) goto L61;
        int r1 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        if (this.next_prices == null) goto L50;
        int r53 = 0;
    L45:
        if (r53 >= this.next_prices.size()) goto L50;
        TL_stars.StarGiftUpgradePrice r92 = this.next_prices.get(r53);
        if (r92.date >= r1) goto L48;
        r53 = r53 + 1;
        goto L45
    L48:
        r8 = r92;
    L50:
        ArrayList<TL_stars.StarGiftUpgradePrice> r12 = this.prices;
        if (r12 == null) goto L56;
        if (r8 == null) goto L56;
        if (r12.isEmpty() == true) goto L56;
        this.underButtonContainer.setVisibility(0);
        this.underButtonLinkTextView.setText(AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag("**" + LocaleController.getString(R.string.Gift2UpgradeCostsInfo) + "**", new StarGiftSheet$$ExternalSyntheticLambda104(this)), false, AndroidUtilities.dp(0.6666667f), AndroidUtilities.dp(0.66f)));
    L57:
        updateUnderButtonContainer();
        ButtonWithCounterView r13 = this.button;
        if (r04 == false) goto L60;
        r13.setText(StarsIntroActivity.replaceStars(LocaleController.formatString(R.string.Gift2PrepayUpgradeButton, new Object[]{Long.valueOf(r6)}), 1.13f, this.starCached), true);
    L62:
        this.button.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda105(this));
        LinearLayout r14 = this.checkboxLayout;
        if (r04 == false) goto L65;
        r14.setVisibility(8);
        this.checkboxSeparator.setVisibility(8);
    L66:
        AffiliateProgramFragment.FeatureCell[] r15 = this.upgradeFeatureCells;
        if (r04 == false) goto L81;
        AffiliateProgramFragment.FeatureCell r16 = r15[0];
        int r3 = R.drawable.menu_feature_unique;
        String r54 = LocaleController.getString(R.string.Gift2UpgradeFeature1Title);
        if (r04 == false) goto L71;
        String r82 = LocaleController.formatString(R.string.Gift2PrepayUpgradeFeature1Text, new Object[]{DialogObject.getShortName(this.currentAccount, this.dialogId)});
    L72:
        r16.set(r3, r54, r82);
        AffiliateProgramFragment.FeatureCell r17 = this.upgradeFeatureCells[1];
        int r32 = R.drawable.menu_feature_transfer;
        String r4 = LocaleController.getString(R.string.Gift2UpgradeFeature2Title);
        if (r04 == false) goto L75;
        String r55 = LocaleController.formatString(R.string.Gift2PrepayUpgradeFeature2Text, new Object[]{DialogObject.getShortName(this.currentAccount, this.dialogId)});
    L76:
        r17.set(r32, r4, r55);
        AffiliateProgramFragment.FeatureCell r18 = this.upgradeFeatureCells[2];
        int r2 = R.drawable.menu_feature_tradable;
        String r33 = LocaleController.getString(R.string.Gift2UpgradeFeature3Title);
        if (r04 == false) goto L79;
        String r05 = LocaleController.formatString(R.string.Gift2PrepayUpgradeFeature3Text, new Object[]{DialogObject.getShortName(this.currentAccount, this.dialogId)});
    L80:
        r18.set(r2, r33, r05);
    L82:
        AndroidUtilities.runOnUIThread(new StarGiftSheet$$ExternalSyntheticLambda106(this, r6));
        return;
    L79:
        r05 = LocaleController.getString(R.string.Gift2UpgradeFeature3Text);
        goto L80
    L75:
        r55 = LocaleController.getString(R.string.Gift2UpgradeFeature2Text);
        goto L76
    L71:
        r82 = LocaleController.getString(R.string.Gift2UpgradeFeature1Text);
        goto L72
    L81:
        r15[0].set(R.drawable.menu_feature_unique, LocaleController.getString(R.string.Gift2UpgradeFeature1Title), LocaleController.getString(R.string.GiftsFeature1Text));
        this.upgradeFeatureCells[1].set(R.drawable.menu_feature_tradable, LocaleController.getString(R.string.Gift2UpgradeFeature3Title), LocaleController.getString(R.string.GiftsFeature2Text));
        this.upgradeFeatureCells[2].set(R.drawable.menu_wear, LocaleController.getString(R.string.GiftsFeature3Title), LocaleController.getString(R.string.GiftsFeature3Text));
        goto L82
    L65:
        r14.setVisibility(0);
        this.checkboxSeparator.setVisibility(0);
        goto L66
    L60:
        r13.setText(StarsIntroActivity.replaceStars(LocaleController.formatString(R.string.Gift2UpgradeButton, new Object[]{Long.valueOf(r6)}), 1.13f, this.starCached), true);
    L56:
        this.underButtonContainer.setVisibility(8);
        goto L57
    L61:
        this.button.setText(LocaleController.getString(R.string.Confirm), true);
        goto L62
    L38:
        r9.setText(1, LocaleController.getString(R.string.Gift2UpgradeTitle), LocaleController.getString(R.string.Gift2UpgradeText), null, null);
        goto L39
    L24:
        if (this.upgrade_form != null) goto L26;
        return;
    L9:
        if (TextUtils.isEmpty(r03.prepaid_upgrade_hash) == true) goto L11;
    L10:
        r04 = true;
        goto L20
    L87:
        return;
    L12:
        TL_stars.SavedStarGift r06 = this.savedStarGift;
        if (r06 == null) goto L88;
        r5 = r06.upgrade_stars;
        TL_stars.StarGift r7 = r06.gift;
        if (r5 > 0) goto L11;
        if (TextUtils.isEmpty(r06.prepaid_upgrade_hash) == true) goto L11;
    }

    private void openUpgradePrices() {
        if (this.upgrade_form != null) goto L5;
        return;
    L5:
        int r2 = 0;
        long r5 = 0;
    L7:
        if (r2 >= this.upgrade_form.invoice.prices.size()) goto L9;
        r5 = r5 + this.upgrade_form.invoice.prices.get(r2).amount;
        r2 = r2 + 1;
        goto L7
    L9:
        UpgradePricesSheet r3 = new UpgradePricesSheet(getContext(), r5, this.prices, this.resourcesProvider);
        this.upgradeSheet = r3;
        r3.show();
    }

    private void openValueStats(long r12, String r14, String r15, String r16, TLRPC.Document r17, String r18) {
        AlertDialog r2 = new AlertDialog(ApplicationLoader.applicationContext, 3);
        r2.showDelayed(500);
        TL_stars.getUniqueStarGiftValueInfo r9 = new TL_stars.getUniqueStarGiftValueInfo();
        r9.slug = r18;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r9, new StarGiftSheet$$ExternalSyntheticLambda114(this, r2, r17, r16, r14, r15, r12));
    }

    private void performBuyPressed(TL_stars.TL_starGiftUnique r11, long r12, AmountUtils$Currency r14, TLRPC.TL_textWithEntities r15, boolean r16, GiftMessageBottomSheet r17) {
        this.button.setLoading(true);
        if (r17 == null) goto L5;
        r17.setLoading(true);
    L5:
        StarsController.getInstance(this.currentAccount, r14).getResellingGiftForm(r11, r12, r15, r16, new StarGiftSheet$$ExternalSyntheticLambda100(this, r17, r14, r11, r12, r15, r16));
    }

    private void presentFragment(BaseFragment r3) {
        BaseFragment r2 = LaunchActivity.getSafeLastFragment();
        if (r2 != null) goto L5;
        return;
    L5:
        BaseFragment.BottomSheetParams r0 = new BaseFragment.BottomSheetParams();
        r0.transitionFromLeft = true;
        r0.allowNestedScroll = false;
        r2.showAsSheet(r3, r0);
    }

    private CharSequence releasedByText(TLRPC.Peer r5) {
        if (r5 != null) goto L5;
        return null;
    L5:
        String r52 = DialogObject.getPublicUsername(MessagesController.getInstance(this.currentAccount).getUserOrChat(DialogObject.getPeerDialogId(r5)));
        if (TextUtils.isEmpty(r52) == false) goto L9;
        return null;
    L9:
        return AndroidUtilities.replaceSingleTag(LocaleController.formatString(R.string.Gift2ReleasedBy2, new Object[]{"@" + r52}), new StarGiftSheet$$ExternalSyntheticLambda49(this, r52));
    }

    private CharSequence releasedByText(TL_stars.StarGift r2) {
        if (r2 != null) goto L4;
        return null;
    L4:
        if ((r2 instanceof TL_stars.TL_starGiftUnique) == false) goto L7;
        return null;
    L7:
        return releasedByText(r2.released_by);
    }

    public static SpannableStringBuilder replaceSingleTagToLink(String r4, Runnable r5) {
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
        if (r1 < 0) goto L12;
        r3.setSpan(new AnonymousClass14(r5), r1, r23 + r1, 0);
    L12:
        return r3;
    L8:
        r1 = -1;
        r23 = 0;
        goto L9
    }

    public static CharSequence replaceUnderstood(CharSequence r1) {
        return replaceUnderstood(r1, null);
    }

    public static CharSequence replaceUnderstood(CharSequence r7, ColoredImageSpan[] r8) {
        if (r7 != null) goto L6;
        return null;
    L6:
        if ((r7 instanceof SpannableStringBuilder) == true) goto L8;
        SpannableStringBuilder r82 = new SpannableStringBuilder(r7);
    L9:
        SpannableString r72 = new SpannableString("👌");
        r72.setSpan(new ColoredImageSpan(R.drawable.filled_understood), 0, r72.length(), 33);
        SpannableString r1 = new SpannableString("👍");
        r1.setSpan(new ColoredImageSpan(R.drawable.filled_reactions), 0, r1.length(), 33);
        AndroidUtilities.replaceMultipleCharSequence("👌", r82, r72);
        AndroidUtilities.replaceMultipleCharSequence("👍", r82, r1);
        return r82;
    L8:
        r82 = (SpannableStringBuilder) r7;
        goto L9
    }

    private void repollMessage() {
        if (this.messageObjectRepolling == false) goto L5;
        return;
    L5:
        if (this.messageObjectRepolled == true) goto L12;
        MessageObject r0 = this.messageObject;
        if (r0 == null) goto L13;
        this.messageObjectRepolling = true;
        int r02 = r0.getId();
        TLRPC.TL_messages_getMessages r1 = new TLRPC.TL_messages_getMessages();
        r1.id.add(Integer.valueOf(r02));
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new StarGiftSheet$$ExternalSyntheticLambda103(this, r02));
        return;
    L13:
        return;
    }

    private void repollSavedStarGift() {
        if (this.userStarGiftRepolling == false) goto L5;
        return;
    L5:
        if (this.userStarGiftRepolled == false) goto L7;
        return;
    L7:
        if (this.messageObject == null) goto L16;
        TL_stars.InputSavedStarGift r0 = getInputStarGift();
        if (r0 == null) goto L17;
        this.userStarGiftRepolling = true;
        StarsController.getInstance(this.currentAccount).getUserStarGift(r0, new StarGiftSheet$$ExternalSyntheticLambda65(this));
        return;
    L17:
        return;
    }

    private void repostStory(View r9) {
        LaunchActivity r0 = LaunchActivity.instance;
        if (r0 != null) goto L6;
        return;
    L6:
        if ((r9 instanceof ShareDialogCell) == false) goto L8;
        StoryRecorder.SourceView r1 = StoryRecorder.SourceView.fromShareCell((ShareDialogCell) r9);
    L9:
        ArrayList r2 = new ArrayList();
        MessageObject r3 = this.messageObject;
        if (r3 == null) goto L13;
        r2.add(r3);
    L15:
        StoryRecorder r02 = StoryRecorder.getInstance(r0, this.currentAccount);
        r02.setOnPrepareCloseListener(new StarGiftSheet$$ExternalSyntheticLambda128(this, r02, r9));
        r02.openRepost(r1, StoryEntry.repostMessage(r2));
        return;
    L13:
        if ((getGift() instanceof TL_stars.TL_starGiftUnique) == false) goto L17;
        long r32 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        TL_stars.TL_starGiftUnique r5 = (TL_stars.TL_starGiftUnique) getGift();
        TLRPC.TL_messageService r6 = new TLRPC.TL_messageService();
        r6.peer_id = MessagesController.getInstance(this.currentAccount).getPeer(r32);
        r6.from_id = MessagesController.getInstance(this.currentAccount).getPeer(r32);
        r6.date = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        TLRPC.TL_messageActionStarGiftUnique r33 = new TLRPC.TL_messageActionStarGiftUnique();
        r33.gift = r5;
        r33.upgrade = true;
        r6.action = r33;
        MessageObject r34 = new MessageObject(this.currentAccount, r6, false, false);
        r34.setType();
        r2.add(r34);
        goto L15
    L17:
        return;
    L8:
        r1 = null;
        goto L9
    }

    private void setButtonTextResale(TL_stars.StarGift r5) {
        AmountUtils$Amount r0 = r5.getResellAmount(AmountUtils$Currency.STARS);
        if (r5.resale_ton_only == false) goto L6;
        this.button.setText(StarsIntroActivity.replaceStars(true, LocaleController.formatString(R.string.ResellGiftBuyTON, new Object[]{r5.getResellAmount(AmountUtils$Currency.TON).asFormatString()})), !this.firstSet);
        this.button.setSubText(StarsIntroActivity.replaceStars(LocaleController.formatPluralStringComma("ResellGiftBuyEq", (int) r0.asDecimal())), !this.firstSet);
        return;
    L6:
        this.button.setText(StarsIntroActivity.replaceStars(LocaleController.formatPluralStringComma("ResellGiftBuy", (int) r0.asDecimal())), !this.firstSet);
        this.button.setSubText(null, !this.firstSet);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v4 */
    private void setupNeighbour(boolean r10, boolean r11) {
        int r112 = getListPosition();
        if (r112 < 0) goto L56;
        if (r10 == false) goto L7;
        int r0 = 1;
    L8:
        int r02 = r0 + r112;
        int r1 = this.overrideNextIndex;
        if (r1 < 0) goto L15;
        if (r10 == false) goto L13;
        if (r1 <= r112) goto L15;
    L14:
        r02 = r1;
        goto L15
    L13:
        if (r1 < r112) goto L14;
    L15:
        StarsController.IGiftsList r113 = this.giftsList;
        if (r113 == null) goto L21;
        if (r02 < 0) goto L21;
        if (r02 >= r113.getLoadedCount()) goto L21;
        TL_stars.SavedStarGift r114 = this.giftsList.get(r02);
    L22:
        if (r114 == 0) goto L57;
        if (r10 == false) goto L26;
        StarGiftSheet r03 = this.right;
    L27:
        if (r03 != null) goto L29;
    L44:
        AnonymousClass9 r12 = new AnonymousClass9(this, getContext(), this.currentAccount, this.dialogId, this.resourcesProvider, this.container.getRootView());
        if ((r114 instanceof TL_stars.SavedStarGift) == false) goto L48;
        r12.set(r114, this.giftsList);
    L50:
        AndroidUtilities.removeFromParent(r12.containerView);
        if (r10 == false) goto L54;
        this.right = r12;
        return;
    L54:
        this.left = r12;
        return;
    L48:
        if ((r114 instanceof TL_stars.TL_starGiftUnique) == false) goto L50;
        TL_stars.TL_starGiftUnique r115 = (TL_stars.TL_starGiftUnique) r114;
        r12.set(r115.slug, r115, this.giftsList);
        goto L50
    L29:
        if ((r114 instanceof TL_stars.SavedStarGift) == false) goto L37;
        if (r10 == false) goto L32;
        StarGiftSheet r04 = this.right;
    L34:
        if (eq(r04.savedStarGift, r114) == false) goto L37;
        return;
    L32:
        r04 = this.left;
    L37:
        if ((r114 instanceof TL_stars.TL_starGiftUnique) == false) goto L44;
        if (r10 == false) goto L40;
        StarGiftSheet r05 = this.right;
    L42:
        if (eq(r05.slugStarGift, r114) == false) goto L44;
        return;
    L40:
        r05 = this.left;
        goto L42
    L26:
        r03 = this.left;
        goto L27
    L57:
        return;
    L21:
        r114 = 0;
        goto L22
    L7:
        r0 = -1;
        goto L8
    }

    private void showDeleteDescriptionAlert(CharSequence r6) {
        TL_stars.TL_starGiftUnique r0 = getUniqueGift();
        TL_stars.InputSavedStarGift r1 = getInputStarGift();
        if (r1 == null) goto L11;
        if (r0 == null) goto L12;
        TLRPC.TL_inputInvoiceStarGiftDropOriginalDetails r2 = new TLRPC.TL_inputInvoiceStarGiftDropOriginalDetails();
        r2.stargift = r1;
        TLRPC.TL_payments_getPaymentForm r12 = new TLRPC.TL_payments_getPaymentForm();
        r12.invoice = r2;
        JSONObject r3 = BotWebViewSheet.makeThemeParams(this.resourcesProvider);
        if (r3 == null) goto L9;
        TLRPC.TL_dataJSON r4 = new TLRPC.TL_dataJSON();
        r12.theme_params = r4;
        r4.data = r3.toString();
        r12.flags |= 1;
    L9:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r12, new StarGiftSheet$$ExternalSyntheticLambda116(this, r6, r0, r2));
        return;
    L12:
        return;
    }

    private void showGiftOfferSheet() {
        TL_stars.TL_starGiftUnique r5 = getUniqueGift();
        new GiftOfferSheet(getContext(), this.currentAccount, DialogObject.getPeerDialogId(r5.owner_id), r5, this.resourcesProvider, new StarGiftSheet$$ExternalSyntheticLambda113(this)).show();
    }

    private void showTimeoutAlert(Context r15, boolean r16, int r17) {
        LinearLayout r1 = new LinearLayout(r15);
        r1.setOrientation(1);
        FrameLayout r3 = new FrameLayout(r15);
        r3.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(64.0f), Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider)));
        r1.addView(r3, LayoutHelper.createLinear(64, 64, 49, 0, 6, 0, 0));
        RLottieImageView r4 = new RLottieImageView(r15);
        r4.setScaleType(ImageView.ScaleType.CENTER);
        r4.setAnimation(R.raw.timer_3, 42, 42);
        r3.addView(r4, LayoutHelper.createLinear(64, 64, 17));
        r4.playAnimation();
        TextView r2 = TextHelper.makeTextView(r15, 20.0f, Theme.key_windowBackgroundWhiteBlackText, true);
        r2.setGravity(17);
        if (r16 == false) goto L5;
        int r32 = R.string.Gift2ResellTimeoutTitle;
    L6:
        r2.setText(LocaleController.getString(r32));
        r1.addView(r2, LayoutHelper.createLinear(-1, -2, 48, 24, 14, 24, 0));
        TextView r22 = TextHelper.makeTextView(r15, 14.0f, Theme.key_windowBackgroundWhiteGrayText8, false);
        r22.setGravity(17);
        if (r16 == false) goto L9;
        int r33 = R.string.Gift2ResellTimeout;
    L10:
        r22.setText(LocaleController.formatString(r33, new Object[]{LocaleController.formatTTLString(Math.max(10, r17))}));
        r1.addView(r22, LayoutHelper.createLinear(-1, -2, 48, 24, 6, 24, 6));
        new AlertDialog.Builder(r15, this.resourcesProvider).setView(r1).setPositiveButton(LocaleController.getString(R.string.OK), null).show();
        return;
    L9:
        r33 = R.string.Gift2TransferTimeout;
        goto L10
    L5:
        r32 = R.string.Gift2TransferTimeoutTitle;
        goto L6
    }

    private void showTimeoutAlertAt(Context r2, boolean r3, int r4) {
        showTimeoutAlert(r2, r3, r4 - ConnectionsManager.getInstance(this.currentAccount).getCurrentTime());
    }

    private void tickUpgradePrice() {
        if (this.currentPage.to == 1) goto L6;
        return;
    L6:
        if (isDismissed() == true) goto L80;
        TL_stars.InputSavedStarGift r0 = getInputStarGift();
        MessageObject r2 = this.messageObject;
        long r3 = 0;
        if (r2 == null) goto L18;
        TLRPC.MessageAction r22 = r2.messageOwner.action;
        if ((r22 instanceof TLRPC.TL_messageActionStarGift) == false) goto L77;
        TLRPC.TL_messageActionStarGift r23 = (TLRPC.TL_messageActionStarGift) r22;
        long r6 = r23.upgrade_stars;
        String r24 = r23.prepaid_upgrade_hash;
        if (r6 <= 0) goto L15;
    L17:
        boolean r62 = false;
    L25:
        int r7 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        if (this.next_prices == null) goto L34;
        int r8 = 0;
    L29:
        if (r8 >= this.next_prices.size()) goto L34;
        TL_stars.StarGiftUpgradePrice r10 = this.next_prices.get(r8);
        if (r10.date >= r7) goto L36;
        r8 = r8 + 1;
    L36:
        if (this.upgrade_form == null) goto L41;
        int r11 = 0;
    L39:
        if (r11 >= this.upgrade_form.invoice.prices.size()) goto L41;
        r3 = r3 + this.upgrade_form.invoice.prices.get(r11).amount;
        r11 = r11 + 1;
    L41:
        if (r8 > 0) goto L43;
    L57:
        ButtonWithCounterView r02 = this.button;
        if (r62 == false) goto L60;
        r02.setText(StarsIntroActivity.replaceStars(LocaleController.formatString(R.string.Gift2PrepayUpgradeButton, new Object[]{Long.valueOf(r3)}), 1.13f, this.starCached), true);
    L61:
        UpgradePricesSheet r03 = this.upgradeSheet;
        if (r03 == null) goto L64;
        r03.setCurrentPrice(r3);
    L64:
        if (r10 == null) goto L71;
        int r04 = r10.date - r7;
        if (r04 >= 86400) goto L68;
        String r05 = AndroidUtilities.formatDuration(r04, false, true);
    L69:
        this.button.setSubTextHacks(false, true, true, false);
        this.button.setSubText(LocaleController.formatString(R.string.Gift2UpgradeButtonDecreasesIn, new Object[]{r05}), true);
        AndroidUtilities.runOnUIThread(this.tickUpgradePriceRunnable, 1000);
        return;
    L68:
        r05 = LocaleController.formatPluralString("Days", Math.round(r04 / 86400.0f), new Object[0]);
        goto L69
    L71:
        this.button.setSubText(null, true);
        return;
    L60:
        r02.setText(StarsIntroActivity.replaceStars(LocaleController.formatString(R.string.Gift2UpgradeButton, new Object[]{Long.valueOf(r3)}), 1.13f, this.starCached), true);
        goto L61
    L43:
        if (this.requesting_upgrade_form == true) goto L57;
        this.requesting_upgrade_form = true;
        if (this.next_prices == null) goto L49;
        int r112 = 0;
    L47:
        if (r112 >= r8) goto L49;
        this.next_prices.remove(0);
        r112 = r112 + 1;
    L49:
        TLRPC.TL_payments_getPaymentForm r82 = new TLRPC.TL_payments_getPaymentForm();
        if (TextUtils.isEmpty(r24) == true) goto L52;
        TLRPC.TL_inputInvoiceStarGiftPrepaidUpgrade r06 = new TLRPC.TL_inputInvoiceStarGiftPrepaidUpgrade();
        r06.hash = r24;
        r06.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        r82.invoice = r06;
    L53:
        JSONObject r07 = BotWebViewSheet.makeThemeParams(this.resourcesProvider);
        if (r07 == null) goto L56;
        TLRPC.TL_dataJSON r25 = new TLRPC.TL_dataJSON();
        r82.theme_params = r25;
        r25.data = r07.toString();
        r82.flags |= 1;
    L56:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r82, new StarGiftSheet$$ExternalSyntheticLambda61(this));
        goto L57
    L52:
        TLRPC.TL_inputInvoiceStarGiftUpgrade r26 = new TLRPC.TL_inputInvoiceStarGiftUpgrade();
        r26.keep_original_details = this.checkbox.isChecked();
        r26.stargift = r0;
        r82.invoice = r26;
    L34:
        r8 = -1;
        r10 = null;
        goto L36
    L15:
        if (TextUtils.isEmpty(r24) == true) goto L17;
    L16:
        r62 = true;
        goto L25
    L77:
        return;
    L18:
        TL_stars.SavedStarGift r27 = this.savedStarGift;
        if (r27 == null) goto L78;
        long r63 = r27.upgrade_stars;
        r24 = r27.prepaid_upgrade_hash;
        if (r63 > 0) goto L17;
        if (TextUtils.isEmpty(r24) == true) goto L17;
    L78:
        return;
    }

    private void toggleShow() {
        if (this.button.isLoading() == true) goto L29;
        TL_stars.InputSavedStarGift r0 = getInputStarGift();
        MessageObject r1 = this.messageObject;
        if (r1 == null) goto L16;
        TLRPC.Message r12 = r1.messageOwner;
        if (r12 == null) goto L16;
        TLRPC.MessageAction r13 = r12.action;
        if ((r13 instanceof TLRPC.TL_messageActionStarGift) == false) goto L13;
        TLRPC.TL_messageActionStarGift r14 = (TLRPC.TL_messageActionStarGift) r13;
        boolean r3 = r14.saved;
        TLRPC.Document r15 = r14.gift.getDocument();
    L15:
        TLRPC.Document r7 = r15;
        boolean r8 = r3;
        this.button.setLoading(true);
        TL_stars.saveStarGift r9 = new TL_stars.saveStarGift();
        r9.unsave = r8;
        r9.stargift = r0;
        if (this.savedStarGift == null) goto L24;
        StarsController.GiftsCollections r02 = StarsController.getInstance(this.currentAccount).getProfileGiftCollectionsList(this.dialogId, false);
        if (r02 == null) goto L24;
        r02.updateGiftsUnsaved(this.savedStarGift, r9.unsave);
        boolean r6 = true;
    L25:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r9, new StarGiftSheet$$ExternalSyntheticLambda66(this, r6, r7, r8, r9));
        return;
    L24:
        r6 = false;
        goto L25
    L13:
        if ((r13 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L27;
        TLRPC.TL_messageActionStarGiftUnique r16 = (TLRPC.TL_messageActionStarGiftUnique) r13;
        r3 = r16.saved;
        r15 = r16.gift.getDocument();
        goto L15
    L27:
        return;
    L16:
        TL_stars.SavedStarGift r17 = this.savedStarGift;
        if (r17 == null) goto L28;
        r3 = !r17.unsaved;
        r15 = r17.gift.getDocument();
        goto L15
    L28:
        return;
    }

    private void updateUnderButtonContainer() {
        int r0 = this.underButtonContainer.getVisibility();
        FrameLayout r1 = this.buttonContainer;
        if (r0 != 0) goto L6;
        r1.setTranslationY((-this.underButtonContainer.getMeasuredHeight()) * this.currentPage.at(1));
        this.underButtonContainer.setTranslationY(r0.getMeasuredHeight() * (1.0f - this.currentPage.at(1)));
        this.bottomBulletinContainer.setTranslationY((-this.underButtonContainer.getMeasuredHeight()) * this.currentPage.at(1));
        return;
    L6:
        r1.setTranslationY(0.0f);
        this.underButtonContainer.setTranslationY(0.0f);
        this.bottomBulletinContainer.setTranslationY(0.0f);
    }

    private void updateViewPager() {
        this.viewPager.setPosition(hasNeighbour(false) ? 1 : 0);
        this.viewPager.rebuild(false);
        if (this.giftsList != null) goto L5;
        return;
    L5:
        if (hasNeighbour(true) == false) goto L7;
        return;
    L7:
        if (this.giftsList.getLoadedCount() >= this.giftsList.getTotalCount()) goto L12;
        this.giftsList.load();
        return;
    }

    public boolean canCraft() {
        TL_stars.TL_starGiftUnique r0 = getUniqueGift();
        if (r0 != null) goto L5;
    L29:
        return false;
    L5:
        if (r0.crafted == true) goto L29;
        if (isMineWithActions(this.currentAccount, DialogObject.getPeerDialogId(r0.owner_id)) == true) goto L10;
        return false;
    L10:
        MessageObject r02 = this.messageObject;
        if (r02 == null) goto L19;
        TLRPC.Message r03 = r02.messageOwner;
        if (r03 != null) goto L15;
        return false;
    L15:
        TLRPC.MessageAction r04 = r03.action;
        if ((r04 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L18;
        int r05 = ((TLRPC.TL_messageActionStarGiftUnique) r04).can_craft_at;
    L24:
        int r5 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        if (r05 <= 0) goto L29;
        if (r5 < r05) goto L29;
        return true;
    L18:
        return false;
    L19:
        TL_stars.SavedStarGift r06 = this.savedStarGift;
        if (r06 == null) goto L29;
        if ((r06.gift instanceof TL_stars.TL_starGiftUnique) == false) goto L29;
        r05 = r06.can_craft_at;
        goto L24
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView, org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        if (this.currentPage.is(4) == false) goto L9;
        if (TopView.m19077$$Nest$fgetcraftTopView(this.topView).crafting == false) goto L9;
        return false;
    L9:
        return super.canDismissWithSwipe();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithTouchOutside() {
        if (this.currentPage.is(4) == false) goto L9;
        if (TopView.m19077$$Nest$fgetcraftTopView(this.topView).crafting == false) goto L9;
        return false;
    L9:
        return super.canDismissWithTouchOutside();
    }

    public int canResellAt() {
        MessageObject r0 = this.messageObject;
        if (r0 == null) goto L10;
        TLRPC.Message r02 = r0.messageOwner;
        if (r02 == null) goto L10;
        TLRPC.MessageAction r03 = r02.action;
        if ((r03 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L10;
        return ((TLRPC.TL_messageActionStarGiftUnique) r03).can_resell_at;
    L10:
        TL_stars.SavedStarGift r2 = this.savedStarGift;
        if (r2 != null) goto L13;
        return 0;
    L13:
        return r2.can_resell_at;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canSwipeToBack(MotionEvent r3) {
        if (this.currentPage.is(4) == false) goto L9;
        if (TopView.m19077$$Nest$fgetcraftTopView(this.topView).crafting == false) goto L9;
        return false;
    L9:
        return super.canSwipeToBack(r3);
    }

    public boolean canTransfer() {
        if (getInputStarGift() != null) goto L5;
        return false;
    L5:
        MessageObject r0 = this.messageObject;
        if (r0 == null) goto L18;
        TLRPC.Message r02 = r0.messageOwner;
        if (r02 == null) goto L18;
        TLRPC.MessageAction r03 = r02.action;
        if ((r03 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L18;
        TLRPC.TL_messageActionStarGiftUnique r04 = (TLRPC.TL_messageActionStarGiftUnique) r03;
        if ((r04.flags & 16) != 0) goto L14;
        return false;
    L14:
        TL_stars.StarGift r05 = r04.gift;
        if ((r05 instanceof TL_stars.TL_starGiftUnique) == true) goto L17;
        return false;
    L17:
        TL_stars.TL_starGiftUnique r06 = (TL_stars.TL_starGiftUnique) r05;
    L26:
        return isMineWithActions(this.currentAccount, DialogObject.getPeerDialogId(r06.owner_id));
    L18:
        TL_stars.SavedStarGift r07 = this.savedStarGift;
        if (r07 == null) goto L23;
        TL_stars.StarGift r08 = r07.gift;
        if ((r08 instanceof TL_stars.TL_starGiftUnique) == false) goto L23;
        r06 = (TL_stars.TL_starGiftUnique) r08;
    L23:
        r06 = this.slugStarGift;
        if (r06 != null) goto L26;
        return false;
    }

    public int canTransferAt() {
        MessageObject r0 = this.messageObject;
        if (r0 == null) goto L10;
        TLRPC.Message r02 = r0.messageOwner;
        if (r02 == null) goto L10;
        TLRPC.MessageAction r03 = r02.action;
        if ((r03 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L10;
        return ((TLRPC.TL_messageActionStarGiftUnique) r03).can_transfer_at;
    L10:
        TL_stars.SavedStarGift r2 = this.savedStarGift;
        if (r2 != null) goto L13;
        return 0;
    L13:
        return r2.can_transfer_at;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r2) {
        Adapter r22 = new Adapter(this, null);
        this.adapter = r22;
        return r22;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.starUserGiftsLoaded) goto L12;
        StarsController.GiftsList r12 = (StarsController.GiftsList) r3[1];
        if (this.giftsList == r12) goto L7;
        return;
    L7:
        if (TopView.m19077$$Nest$fgetcraftTopView(this.topView) != null) goto L9;
    L10:
        updateViewPager();
        return;
    L9:
        if (TopView.m19077$$Nest$fgetcraftTopView(this.topView).crafting == false) goto L10;
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        if (this.currentPage.is(4) == true) goto L5;
    L7:
        ResaleGiftsFragment.SelectGiftSheet.State r0 = this.giftsToCraft;
        if (r0 == null) goto L10;
        r0.detach();
        this.giftsToCraft = null;
    L10:
        Roller r02 = this.roller;
        if (r02 == null) goto L13;
        r02.detach();
    L13:
        super.dismiss();
        return;
    L5:
        if (TopView.m19077$$Nest$fgetcraftTopView(this.topView).crafting == false) goto L7;
    }

    public void doTransfer(long r12, Utilities.Callback<TLRPC.TL_error> r14) {
        TL_stars.InputSavedStarGift r0 = getInputStarGift();
        if (r0 == null) goto L33;
        TL_stars.SavedStarGift r2 = this.savedStarGift;
        if (r2 != null) goto L8;
    L10:
        MessageObject r22 = this.messageObject;
        if (r22 == null) goto L30;
        TLRPC.Message r23 = r22.messageOwner;
        if (r23 == null) goto L31;
        TLRPC.MessageAction r24 = r23.action;
        if ((r24 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L32;
        TLRPC.TL_messageActionStarGiftUnique r25 = (TLRPC.TL_messageActionStarGiftUnique) r24;
        long r5 = DialogObject.getPeerDialogId(r25.gift.owner_id);
        long r7 = r25.transfer_stars;
    L18:
        if (r7 > 0) goto L21;
        TL_stars.transferStarGift r72 = new TL_stars.transferStarGift();
        r72.stargift = r0;
        r72.to_id = MessagesController.getInstance(this.currentAccount).getInputPeer(r12);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r72, new StarGiftSheet$$ExternalSyntheticLambda129(this, r14, r12, r5));
        return;
    L21:
        StarsController r26 = StarsController.getInstance(this.currentAccount);
        if (r26.balanceAvailable() == true) goto L25;
        r26.getBalance(new StarGiftSheet$$ExternalSyntheticLambda130(this, r26, r12, r14));
        return;
    L25:
        TLRPC.TL_inputInvoiceStarGiftTransfer r27 = new TLRPC.TL_inputInvoiceStarGiftTransfer();
        r27.stargift = r0;
        r27.to_id = MessagesController.getInstance(this.currentAccount).getInputPeer(r12);
        TLRPC.TL_payments_getPaymentForm r8 = new TLRPC.TL_payments_getPaymentForm();
        r8.invoice = r27;
        JSONObject r02 = BotWebViewSheet.makeThemeParams(this.resourcesProvider);
        if (r02 == null) goto L28;
        TLRPC.TL_dataJSON r73 = new TLRPC.TL_dataJSON();
        r8.theme_params = r73;
        r73.data = r02.toString();
        r8.flags |= 1;
    L28:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r8, new StarGiftSheet$$ExternalSyntheticLambda131(this, r27, r12, r5, r14));
        return;
    L32:
        return;
    L31:
        return;
    L30:
        return;
    L8:
        if ((r2.gift instanceof TL_stars.TL_starGiftUnique) == false) goto L10;
        r5 = this.dialogId;
        r7 = r2.transfer_stars;
        goto L18
    }

    public boolean eq(TL_stars.SavedStarGift r5, TL_stars.InputSavedStarGift r6) {
        if (r5 != null) goto L6;
        return false;
    L6:
        if ((r6 instanceof TL_stars.TL_inputSavedStarGiftUser) == false) goto L12;
        if (r5.msg_id != ((TL_stars.TL_inputSavedStarGiftUser) r6).msg_id) goto L10;
        return true;
    L10:
        return false;
    L12:
        if ((r6 instanceof TL_stars.TL_inputSavedStarGiftChat) == false) goto L18;
        if (r5.saved_id != ((TL_stars.TL_inputSavedStarGiftChat) r6).saved_id) goto L16;
        return true;
    L16:
        return false;
    L18:
        if ((r6 instanceof TL_stars.TL_inputSavedStarGiftSlug) == false) goto L24;
        TL_stars.StarGift r52 = r5.gift;
        if (r52 == null) goto L24;
        if (TextUtils.equals(r52.slug, ((TL_stars.TL_inputSavedStarGiftSlug) r6).slug) == false) goto L24;
        return true;
    L24:
        return false;
    }

    public boolean eq(TL_stars.SavedStarGift r6, TL_stars.SavedStarGift r7) {
        if (r6 != r7) goto L6;
        return true;
    L6:
        if (r6 == null) goto L29;
        if (r7 == null) goto L29;
        TL_stars.StarGift r1 = r6.gift;
        TL_stars.StarGift r2 = r7.gift;
        if (r1 != r2) goto L13;
        return true;
    L13:
        if ((r1 instanceof TL_stars.TL_starGiftUnique) == false) goto L21;
        if ((r2 instanceof TL_stars.TL_starGiftUnique) == false) goto L21;
        if (r1.id != r2.id) goto L19;
        return true;
    L19:
        return false;
    L21:
        if ((r1 instanceof TL_stars.TL_starGift) == false) goto L29;
        if ((r2 instanceof TL_stars.TL_starGift) == false) goto L29;
        if (r1.id != r2.id) goto L29;
        if (r6.date != r7.date) goto L29;
        return true;
    L29:
        return false;
    }

    public boolean eq(TL_stars.StarGift r4, TL_stars.SavedStarGift r5) {
        if (r4 == null) goto L16;
        if (r5 == null) goto L16;
        TL_stars.StarGift r52 = r5.gift;
        if (r4 != r52) goto L10;
        return true;
    L10:
        if ((r4 instanceof TL_stars.TL_starGiftUnique) == false) goto L16;
        if ((r52 instanceof TL_stars.TL_starGiftUnique) == false) goto L16;
        if (r4.id != r52.id) goto L16;
        return true;
    L16:
        return false;
    }

    public boolean eq(TL_stars.TL_starGiftUnique r6, TL_stars.TL_starGiftUnique r7) {
        if (r6 != r7) goto L6;
        return true;
    L6:
        if (r6 == null) goto L16;
        if (r7 == null) goto L16;
        if (r6.id != r7.id) goto L12;
    L15:
        return true;
    L12:
        if (TextUtils.equals(r6.slug, r7.slug) == true) goto L15;
        return false;
    L16:
        return false;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public int getActionBarProgressHeight() {
        return AndroidUtilities.dp(12.0f);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public BulletinFactory getBulletinFactory() {
        return BulletinFactory.of(this.bottomBulletinContainer, this.resourcesProvider);
    }

    public TL_stars.StarGift getGift() {
        MessageObject r0 = this.messageObject;
        if (r0 == null) goto L15;
        TLRPC.Message r2 = r0.messageOwner;
        if (r2 != null) goto L7;
        return null;
    L7:
        TLRPC.MessageAction r22 = r2.action;
        if ((r22 instanceof TLRPC.TL_messageActionStarGift) == false) goto L12;
        return ((TLRPC.TL_messageActionStarGift) r22).gift;
    L12:
        if ((r22 instanceof TLRPC.TL_messageActionStarGiftUnique) == true) goto L14;
    L22:
        return null;
    L14:
        return ((TLRPC.TL_messageActionStarGiftUnique) r22).gift;
    L15:
        TL_stars.SavedStarGift r02 = this.savedStarGift;
        if (r02 != null) goto L18;
        TL_stars.TL_starGiftUnique r23 = this.slugStarGift;
        if (r23 == null) goto L22;
        return r23;
    L18:
        return r02.gift;
    }

    public String getGiftName() {
        TL_stars.StarGift r3 = getGift();
        if ((r3 instanceof TL_stars.TL_starGiftUnique) == true) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        return ((TL_stars.TL_starGiftUnique) r3).title + " #" + LocaleController.formatNumber(r3.num, ',');
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return this.title;
    }

    public TL_stars.TL_starGiftUnique getUniqueGift() {
        TL_stars.StarGift r1 = getGift();
        if ((r1 instanceof TL_stars.TL_starGiftUnique) == true) goto L5;
        return null;
    L5:
        return (TL_stars.TL_starGiftUnique) r1;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starUserGiftsLoaded);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void onBackPressed() {
        if (this.currentPage.is(4) == false) goto L16;
        TopView r0 = this.topView;
        if (r0 == null) goto L16;
        if (TopView.m19077$$Nest$fgetcraftTopView(r0) == null) goto L16;
        if (TopView.m19077$$Nest$fgetcraftTopView(this.topView).crafting == false) goto L12;
        return;
    L12:
        if (TopView.m19077$$Nest$fgetcraftTopView(this.topView).crafted == false) goto L16;
        super.onBackPressed();
        return;
    L16:
        if (this.onlyWearInfo == false) goto L18;
    L34:
        super.onBackPressed();
        return;
    L18:
        if (this.currentPage.to <= 0) goto L34;
        if (this.button.isLoading() == true) goto L34;
        if (this.isLearnMore == true) goto L34;
        MessageObject r02 = this.messageObject;
        if (r02 == null) goto L26;
        set(r02);
    L32:
        switchPage(0, true);
        return;
    L26:
        TL_stars.SavedStarGift r03 = this.savedStarGift;
        if (r03 == null) goto L29;
        set(r03, this.giftsList);
        goto L32
    L29:
        TL_stars.TL_starGiftUnique r04 = this.slugStarGift;
        if (r04 == null) goto L32;
        set(this.slug, r04, this.giftsList);
        goto L32
    }

    public void onBuyPressed() {
        TL_stars.TL_starGiftUnique r3 = getUniqueGift();
        if (this.button.isLoading() == true) goto L27;
        if (r3 != null) goto L7;
        return;
    L7:
        if (this.slugStarGift != null) goto L9;
    L13:
        long r0 = UserConfig.getInstance(this.currentAccount).getClientUserId();
    L12:
        long r4 = r0;
        if (r3.resale_ton_only == false) goto L18;
        AmountUtils$Currency r02 = AmountUtils$Currency.TON;
    L17:
        AmountUtils$Currency r6 = r02;
        if (this.slugStarGift != null) goto L22;
    L25:
        performBuyPressed(r3, r4, r6, null, true, null);
        return;
    L22:
        if (this.resale == false) goto L25;
        GiftMessageBottomSheet r03 = new GiftMessageBottomSheet(getContext(), this.resourcesProvider, r3, r4);
        r03.setCallback(new StarGiftSheet$$ExternalSyntheticLambda39(this, r03, r3, r4, r6));
        r03.show();
        return;
    L18:
        r02 = AmountUtils$Currency.STARS;
        goto L17
    L9:
        if (this.resale == false) goto L13;
        r0 = this.dialogId;
        if (r0 == 0) goto L13;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starUserGiftsLoaded);
    }

    public void onResellPressed(View r4) {
        if (r4.getAlpha() >= 0.99f) goto L6;
        cantWithBlockchainGiftAlert(1);
        return;
    L6:
        TL_stars.TL_starGiftUnique r42 = getUniqueGift();
        if (r42 != null) goto L10;
        return;
    L10:
        if (r42.resell_amount == null) goto L14;
        new AlertDialog.Builder(getContext(), this.resourcesProvider).setTitle(LocaleController.formatString(R.string.Gift2UnlistTitle, new Object[]{getGiftName()})).setMessage(LocaleController.getString(R.string.Gift2UnlistText)).setPositiveButton(LocaleController.getString(R.string.Gift2ActionUnlist), new StarGiftSheet$$ExternalSyntheticLambda50(this, r42)).setNegativeButton(LocaleController.getString(R.string.Cancel), new StarGiftSheet$$ExternalSyntheticLambda51()).show();
        return;
    L14:
        if (canResellAt() <= ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()) goto L17;
        showTimeoutAlertAt(getContext(), true, canResellAt());
        return;
    L17:
        StarsIntroActivity.showGiftResellPriceSheet(getContext(), this.currentAccount, new StarGiftSheet$$ExternalSyntheticLambda52(this, r42), this.resourcesProvider);
    }

    public void onSharePressed(View r17) {
        ShareAlert r0 = this.shareAlert;
        if (r0 != null) goto L5;
    L7:
        String r6 = getLink();
        AnonymousClass11 r02 = new AnonymousClass11(this, getContext(), null, null, null, r6, null, false, r6, null, false, false, true, null, this.resourcesProvider);
        this.shareAlert = r02;
        r02.setDelegate(new AnonymousClass12(this));
        this.shareAlert.show();
        return;
    L5:
        if (r0.isShown() == false) goto L7;
        this.shareAlert.dismiss();
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onSwipeStarts() {
        HintView2 r0 = this.currentHintView;
        if (r0 == null) goto L6;
        r0.hide();
        this.currentHintView = null;
        return;
    }

    public void onTransferClick(View r2) {
        if (r2.getAlpha() >= 0.99f) goto L6;
        cantWithBlockchainGiftAlert(0);
        return;
    L6:
        openTransfer();
    }

    public void onUpdatePriceClick(View r7) {
        TL_stars.TL_starGiftUnique r2 = getUniqueGift();
        if (r2 != null) goto L5;
        return;
    L5:
        StarsIntroActivity.showGiftResellPriceSheet(getContext(), this.currentAccount, r2, null, new StarGiftSheet$$ExternalSyntheticLambda80(this, r2), this.resourcesProvider);
    }

    public void openAsLearnMore(long r3, String r5) {
        this.isLearnMore = true;
        StarsController.getInstance(this.currentAccount).getStarGiftPreview(r3, new StarGiftSheet$$ExternalSyntheticLambda143(this, r5));
    }

    public void openTransfer() {
        HintView2 r0 = this.currentHintView;
        if (r0 == null) goto L6;
        r0.hide();
        this.currentHintView = null;
    L6:
        if (canTransferAt() <= ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()) goto L9;
        showTimeoutAlertAt(getContext(), false, canTransferAt());
        return;
    L9:
        TL_stars.SavedStarGift r02 = this.savedStarGift;
        if (r02 == null) goto L15;
        TL_stars.StarGift r2 = r02.gift;
        if ((r2 instanceof TL_stars.TL_starGiftUnique) == false) goto L15;
        TL_stars.TL_starGiftUnique r22 = (TL_stars.TL_starGiftUnique) r2;
        int r3 = r02.can_export_at;
        long r4 = r02.transfer_stars;
    L14:
        TL_stars.TL_starGiftUnique r5 = r22;
        int r23 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        Context r9 = getContext();
        int r10 = this.currentAccount;
        UserSelectorBottomSheet[] r6 = {new UserSelectorBottomSheet(r9, r10, 0, BirthdayController.getInstance(r10).getState(), 3, true, this.resourcesProvider)};
        r6[0].setTitle(LocaleController.getString(R.string.Gift2TransferShort));
        if (r23 <= r3) goto L28;
        int r42 = 0;
    L29:
        r6[0].addTONOption(r42);
        r6[0].setOnUserSelector(new StarGiftSheet$$ExternalSyntheticLambda81(this, r23, r3, r42, r5, r6));
        r6[0].show();
        return;
    L28:
        r42 = Math.max(1, Math.round(Math.max(0, r3 - r23) / 86400.0f));
    L15:
        MessageObject r03 = this.messageObject;
        if (r03 == null) goto L31;
        TLRPC.Message r04 = r03.messageOwner;
        if (r04 == null) goto L32;
        TLRPC.MessageAction r05 = r04.action;
        if ((r05 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L33;
        TLRPC.TL_messageActionStarGiftUnique r06 = (TLRPC.TL_messageActionStarGiftUnique) r05;
        TL_stars.StarGift r24 = r06.gift;
        if ((r24 instanceof TL_stars.TL_starGiftUnique) == false) goto L34;
        r22 = (TL_stars.TL_starGiftUnique) r24;
        r3 = r06.can_export_at;
        goto L14
    L34:
        return;
    L33:
        return;
    L32:
        return;
    }

    public void openTransferAlert(long r9, Utilities.Callback<Browser.Progress> r11) {
        TL_stars.SavedStarGift r0 = this.savedStarGift;
        if (r0 != null) goto L5;
    L8:
        MessageObject r02 = this.messageObject;
        if (r02 == null) goto L20;
        TLRPC.Message r03 = r02.messageOwner;
        if (r03 == null) goto L21;
        TLRPC.MessageAction r04 = r03.action;
        if ((r04 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L22;
        TLRPC.TL_messageActionStarGiftUnique r05 = (TLRPC.TL_messageActionStarGiftUnique) r04;
        if ((r05.gift instanceof TL_stars.TL_starGiftUnique) == false) goto L23;
        long r06 = r05.transfer_stars;
    L18:
        openTransferAlert(r9, r06, r11);
        return;
    L23:
        return;
    L22:
        return;
    L21:
        return;
    L20:
        return;
    L5:
        if ((r0.gift instanceof TL_stars.TL_starGiftUnique) == false) goto L8;
        r06 = r0.transfer_stars;
        goto L18
    }

    public StarGiftSheet set(String r11, TL_stars.TL_starGiftUnique r12, StarsController.IGiftsList r13) {
        this.slug = r11;
        this.slugStarGift = r12;
        this.giftsList = r13;
        if (r12.resell_amount != null) goto L5;
    L7:
        boolean r112 = false;
    L8:
        this.resale = r112;
        if (this.rolling == true) goto L19;
        Roller r113 = this.roller;
        if (r113 == null) goto L19;
        if (r113.isRolling() == false) goto L19;
        if (Roller.m19068$$Nest$fgetrollingGift(this.roller) == null) goto L19;
        if (Roller.m19068$$Nest$fgetrollingGift(this.roller).id == r12.id) goto L19;
        this.roller.detach();
        this.roller = null;
        this.topView.imageLayout.setAlpha(1.0f);
        TopView.m19082$$Nest$fgetimagesRollView(this.topView).setAlpha(0.0f);
    L19:
        this.actionView.set(this.currentAccount, this.savedStarGift);
        set(r12, false);
        String r114 = r12.owner_address;
        String r1 = r12.gift_address;
        if (r12.host_id == null) goto L22;
        boolean r2 = true;
    L24:
        if (r2 == true) goto L26;
    L30:
        this.beforeTableTextView.setVisibility(8);
    L31:
        if (r2 == false) goto L33;
    L37:
        this.afterTableTextView.setVisibility(8);
    L39:
        if (this.resale == false) goto L42;
        setButtonTextResale(r12);
        this.button.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda11(this));
    L42:
        if (this.firstSet == false) goto L44;
        switchPage(0, false);
        this.layoutManager.scrollToPosition(1);
        this.firstSet = false;
    L44:
        updateViewPager();
        return this;
    L33:
        if (TextUtils.isEmpty(r114) == true) goto L37;
        if (TextUtils.isEmpty(r1) == true) goto L37;
        this.afterTableTextView.setText(AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.Gift2InBlockchain), new StarGiftSheet$$ExternalSyntheticLambda10(this, r1)), true, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(0.66f)));
        this.afterTableTextView.setVisibility(0);
        goto L39
    L26:
        if (TextUtils.isEmpty(r114) == true) goto L30;
        if (TextUtils.isEmpty(r1) == true) goto L30;
        this.beforeTableTextView.setText(AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.Gift2InBlockchain), new StarGiftSheet$$ExternalSyntheticLambda9(this, r1)), true, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(0.66f)));
        this.beforeTableTextView.setVisibility(0);
        this.beforeTableTextView.setTextColor(Theme.getColor(Theme.key_dialogTextGray2, this.resourcesProvider));
        goto L31
    L22:
        r2 = false;
        goto L24
    L5:
        if (isMine(this.currentAccount, DialogObject.getPeerDialogId(r12.owner_id)) == true) goto L7;
        r112 = true;
        goto L8
    }

    public StarGiftSheet set(MessageObject r2) {
        return set(r2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r19v0 */
    /* JADX WARN: Type inference failed for: r19v1 */
    /* JADX WARN: Type inference failed for: r19v2 */
    public StarGiftSheet set(MessageObject r56, StarsController.IGiftsList r57) {
        if (r56 != null) goto L5;
    L374:
        return this;
    L5:
        if (r56.messageOwner == null) goto L374;
        this.myProfile = false;
        TLRPC.TL_textWithEntities r3 = null;
        this.savedStarGift = null;
        this.messageObject = r56;
        this.giftsList = r57;
        this.actionView.set(r56);
        long r4 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        boolean r7 = true;
        if (r56.getDialogId() != r4) goto L10;
        boolean r6 = true;
    L11:
        TLRPC.MessageAction r8 = r56.messageOwner.action;
        if ((r8 instanceof TLRPC.TL_messageActionStarGift) == true) goto L55;
        if ((r8 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L19;
        if ((((TLRPC.TL_messageActionStarGiftUnique) r8).gift instanceof TL_stars.TL_starGift) == true) goto L55;
    L19:
        if ((r8 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L374;
        TLRPC.TL_messageActionStarGiftUnique r82 = (TLRPC.TL_messageActionStarGiftUnique) r8;
        if ((r82.gift instanceof TL_stars.TL_starGiftUnique) == false) goto L374;
        if (r82.name_hidden == false) goto L26;
        TLObject r9 = null;
    L31:
        TL_stars.TL_starGiftUnique r13 = (TL_stars.TL_starGiftUnique) r82.gift;
        boolean r14 = r82.refunded;
        set(r13, r14, r9, r82.message);
        boolean r92 = r82.saved;
        TL_stars.StarGift r132 = r82.gift;
        if ((!r82.upgrade) != r56.isOutOwner()) goto L34;
        boolean r83 = true;
    L36:
        if (r56.getDialogId() != r4) goto L38;
        r83 = false;
    L38:
        repollSavedStarGift();
        if (this.rolling == true) goto L52;
        Roller r42 = this.roller;
        if (r42 == null) goto L52;
        if (r42.isRolling() == false) goto L52;
        if (Roller.m19068$$Nest$fgetrollingGift(this.roller) == null) goto L52;
        if (r132 == null) goto L50;
        long r15 = 0;
        if (Roller.m19068$$Nest$fgetrollingGift(this.roller).id != r132.id) goto L51;
    L53:
        boolean r25 = false;
        boolean r27 = false;
        TLRPC.Peer r2 = null;
        String r43 = null;
        boolean r30 = r6;
        long r17 = r15;
    L299:
        if (this.nextButtonCrafting == false) goto L301;
        this.button.setFilled(r7);
        this.button.setText(LocaleController.getString(R.string.GiftCraftButtonNext), false);
        this.button.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda15(this));
    L301:
        if (r132 != null) goto L303;
        String r1 = r43;
    L304:
        if (r132 == null) goto L307;
        r43 = r132.gift_address;
    L307:
        if (r132 != null) goto L309;
    L311:
        boolean r5 = false;
    L313:
        if (r14 == false) goto L315;
        this.beforeTableTextView.setVisibility(0);
        this.beforeTableTextView.setText(LocaleController.getString(R.string.Gift2Refunded));
        this.beforeTableTextView.setTextColor(Theme.getColor(Theme.key_text_RedBold, this.resourcesProvider));
    L343:
        if (r5 == false) goto L345;
    L349:
        if (r25 == true) goto L369;
        if (r14 == true) goto L369;
        if (r132 == null) goto L369;
        if (isMine(this.currentAccount, getDialogId()) == false) goto L369;
        if (r2 != null) goto L369;
        this.afterTableTextView.setVisibility(0);
        if (getDialogId() < r17) goto L364;
        SpannableStringBuilder r12 = new SpannableStringBuilder();
        if (r92 == true) goto L360;
        r12.append(". ");
        r12.setSpan(new ColoredImageSpan(R.drawable.mini_gift_hidden), 0, 1, 33);
    L360:
        if (r92 == false) goto L362;
        int r22 = R.string.Gift2ProfileVisible4;
    L363:
        r12.append(AndroidUtilities.replaceSingleTag(LocaleController.getString(r22), new StarGiftSheet$$ExternalSyntheticLambda18(this)));
        this.afterTableTextView.setText(AndroidUtilities.replaceArrows(r12, true, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(0.66f)));
    L371:
        if (this.firstSet == false) goto L373;
        switchPage(0, false);
        this.layoutManager.scrollToPosition(1);
        this.firstSet = false;
    L373:
        this.actionBar.setTitle(getTitle());
        updateViewPager();
        goto L374
    L362:
        r22 = R.string.Gift2ProfileInvisible4;
        goto L363
    L364:
        LinkSpanDrawable.LinksTextView r16 = this.afterTableTextView;
        if (r92 == false) goto L367;
        int r23 = R.string.Gift2ChannelProfileVisible3;
    L368:
        r16.setText(AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(r23), new StarGiftSheet$$ExternalSyntheticLambda18(this)), true, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(0.66f)));
        goto L371
    L367:
        r23 = R.string.Gift2ChannelProfileInvisible3;
    L369:
        this.afterTableTextView.setVisibility(8);
        goto L371
    L345:
        if (TextUtils.isEmpty(r1) == true) goto L349;
        if (TextUtils.isEmpty(r43) == true) goto L349;
        this.afterTableTextView.setVisibility(0);
        this.afterTableTextView.setText(AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.Gift2InBlockchain), new StarGiftSheet$$ExternalSyntheticLambda17(this, r43)), true, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(0.66f)));
        goto L371
    L315:
        if (r5 == false) goto L322;
        if (TextUtils.isEmpty(r1) == true) goto L322;
        if (TextUtils.isEmpty(r43) == true) goto L322;
        this.beforeTableTextView.setVisibility(0);
        this.beforeTableTextView.setText(AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.Gift2InBlockchain), new StarGiftSheet$$ExternalSyntheticLambda16(this, r43)), true, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(0.66f)));
        this.beforeTableTextView.setTextColor(Theme.getColor(Theme.key_dialogTextGray2, this.resourcesProvider));
    L322:
        if (TextUtils.isEmpty(r1) == true) goto L324;
    L342:
        this.beforeTableTextView.setVisibility(8);
        goto L343
    L324:
        if (TextUtils.isEmpty(r43) == false) goto L342;
        if (r27 == false) goto L342;
        if (r30 == true) goto L342;
        this.beforeTableTextView.setVisibility(0);
        LinkSpanDrawable.LinksTextView r11 = this.beforeTableTextView;
        if (r83 == false) goto L335;
        if (r3 != null) goto L331;
    L333:
        int r32 = R.string.Gift2OutSenderHidden2;
    L334:
        String r33 = LocaleController.formatString(r32, new Object[]{DialogObject.getShortName(r56.getDialogId())});
    L341:
        r11.setText(r33);
        this.beforeTableTextView.setTextColor(Theme.getColor(Theme.key_dialogTextGray2, this.resourcesProvider));
        goto L343
    L331:
        if (TextUtils.isEmpty(r3.text) == true) goto L333;
        r32 = R.string.Gift2OutSenderMessageHidden2;
        goto L334
    L335:
        if (r3 != null) goto L337;
    L339:
        int r34 = R.string.Gift2InSenderHidden2;
    L340:
        r33 = LocaleController.getString(r34);
        goto L341
    L337:
        if (TextUtils.isEmpty(r3.text) == true) goto L339;
        r34 = R.string.Gift2InSenderMessageHidden2;
        goto L340
    L309:
        if (r132.host_id == null) goto L311;
        r5 = true;
        goto L313
    L303:
        r1 = r132.owner_address;
    L51:
        this.roller.detach();
        this.roller = null;
        this.topView.imageLayout.setAlpha(1.0f);
        TopView.m19082$$Nest$fgetimagesRollView(this.topView).setAlpha(0.0f);
        goto L53
    L50:
        r15 = 0;
    L52:
        r15 = 0;
        goto L53
    L34:
        r83 = false;
        goto L36
    L26:
        MessagesController r93 = MessagesController.getInstance(this.currentAccount);
        TLRPC.Peer r133 = r82.from_id;
        if (r133 == null) goto L29;
        long r134 = DialogObject.getPeerDialogId(r133);
    L30:
        r9 = r93.getUserOrChat(r134);
        goto L31
    L29:
        r134 = r56.getFromChatId();
    L55:
        if (this.rolling == true) goto L63;
        Roller r84 = this.roller;
        if (r84 == null) goto L63;
        if (r84.isRolling() == false) goto L63;
        if (Roller.m19068$$Nest$fgetrollingGift(this.roller) == null) goto L63;
        this.roller.detach();
        this.roller = null;
        this.topView.imageLayout.setVisibility(0);
        TopView.m19082$$Nest$fgetimagesRollView(this.topView).setVisibility(4);
    L63:
        boolean r85 = r56.isOutOwner();
        if (r6 == false) goto L66;
        r85 = false;
    L66:
        TLRPC.Message r94 = r56.messageOwner;
        int r112 = r94.date;
        TLRPC.MessageAction r95 = r94.action;
        if ((r95 instanceof TLRPC.TL_messageActionStarGift) == false) goto L73;
        TLRPC.TL_messageActionStarGift r96 = (TLRPC.TL_messageActionStarGift) r95;
        boolean r122 = r96.converted;
        boolean r135 = r96.saved;
        boolean r142 = r96.refunded;
        r17 = 0;
        boolean r152 = r96.name_hidden;
        TL_stars.StarGift r35 = r96.gift;
        int r19 = 0;
        boolean r24 = r96.can_upgrade;
        int r20 = r112;
        long r10 = r96.convert_stars;
        boolean r222 = r85;
        long r72 = r96.upgrade_stars;
        TLRPC.TL_textWithEntities r18 = r96.message;
        TLRPC.Peer r110 = r96.from_id;
        TLRPC.Peer r111 = r96.peer;
        boolean r113 = r96.prepaid_upgrade;
        String r114 = r96.prepaid_upgrade_hash;
        if (r96.auction_acquired == false) goto L71;
        TLRPC.Peer r115 = r96.to_id;
    L72:
        int r97 = r96.gift_num;
        boolean r29 = r24;
        boolean r302 = r113;
        String r28 = r114;
        TLRPC.Peer r26 = r115;
        boolean r262 = r142;
        r27 = r152;
        TLRPC.Peer r116 = r111;
        r25 = r122;
        boolean r153 = r135;
        TL_stars.StarGift r98 = r35;
        r3 = r18;
        int r86 = r97;
        TLRPC.Peer r73 = r110;
        long r232 = r72;
    L74:
        String r31 = DialogObject.getShortName(this.dialogId);
        boolean r322 = UserObject.isBot(MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.dialogId)));
        if (r116 != null) goto L77;
    L80:
        boolean r332 = r19;
    L79:
        int r123 = r86;
        TopView r87 = this.topView;
        boolean r136 = isWorn(this.currentAccount, getUniqueGift());
        if (getLink() == null) goto L84;
        int r143 = r123;
        boolean r124 = r136;
        boolean r137 = true;
    L85:
        long r352 = r10;
        TLRPC.Peer r37 = r116;
        int r117 = r143;
        r87.setGift(r98, false, false, r124, r137, false);
        CharSequence r118 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r6 == false) goto L110;
        if (r117 == 0) goto L93;
        if (r98 == null) goto L93;
        char r36 = 2;
        if (r98.title == null) goto L92;
        StringBuilder r88 = new StringBuilder();
        r88.append(r98.title);
        r88.append(" #");
        TLRPC.Peer r102 = r26;
        r88.append(LocaleController.formatNumber(r117, ','));
        String r119 = r88.toString();
    L95:
        this.title = r119;
        TopView r210 = this.topView;
        if (r262 == false) goto L98;
        SpannableStringBuilder r41 = null;
    L108:
        r210.setText(0, r119, r41, null, releasedByText(r98));
    L109:
        boolean r89 = r6;
        TLRPC.Peer r138 = r73;
    L193:
        this.tableView.clear();
        if (r138 == null) goto L196;
        long r120 = DialogObject.getPeerDialogId(r138);
    L199:
        if (r37 == null) goto L201;
        long r62 = DialogObject.getPeerDialogId(r37);
    L204:
        TLRPC.User r139 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r120));
        if (r102 == null) goto L212;
        long r121 = DialogObject.getPeerDialogId(r102);
        TableView r63 = this.tableView;
        String r45 = LocaleController.getString(R.string.Gift2To);
        int r74 = this.currentAccount;
        StarGiftSheet$$ExternalSyntheticLambda19 r1310 = new StarGiftSheet$$ExternalSyntheticLambda19(this, r121);
        if (r332 == false) goto L209;
        StarGiftSheet$$ExternalSyntheticLambda20 r51 = null;
    L210:
        r63.addRowUser(r45, r74, r121, r1310, null, r51);
        boolean r303 = r89;
    L240:
        this.tableView.addRowDateTime(LocaleController.getString(R.string.StarsTransactionDate), r20);
        if (r98.stars <= r17) goto L248;
        TableView r125 = this.tableView;
        String r211 = LocaleController.getString(R.string.Gift2Value);
        String r64 = "⭐️ " + LocaleController.formatNumber(r98.stars + r232, ',');
        if (canConvert() == false) goto L246;
        if (r262 == true) goto L246;
        r118 = ButtonSpan.make(LocaleController.formatPluralStringComma("Gift2ButtonSell", (int) r352), new StarGiftSheet$$ExternalSyntheticLambda25(this), this.resourcesProvider);
    L246:
        CharSequence[] r44 = new CharSequence[3];
        r44[r19] = r64;
        r44[1] = " ";
        r44[r36] = r118;
        r125.addRow(r211, StarsIntroActivity.replaceStarsWithPlain(TextUtils.concat(r44), 0.8f));
    L248:
        if (r98.limited == false) goto L251;
        if (r262 == true) goto L251;
        StarsIntroActivity.addAvailabilityRow(this.tableView, this.currentAccount, r98, this.resourcesProvider);
    L251:
        if (r3 == null) goto L257;
        if (TextUtils.isEmpty(r3.text) == true) goto L257;
        if (r262 == true) goto L257;
        this.tableView.addFullRow(r3.text, r3.entities);
    L257:
        if (r222 == true) goto L270;
        if (r29 == false) goto L270;
        if (r262 == true) goto L270;
        SpannableStringBuilder r212 = new SpannableStringBuilder("^  ");
        if (this.upgradeIconSpan != null) goto L263;
        this.upgradeIconSpan = new ColoredImageSpan(new UpgradeIcon(this.button, Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider)));
    L263:
        r212.setSpan(this.upgradeIconSpan, r19, 1, 33);
        if (r232 <= r17) goto L266;
        String r126 = LocaleController.getString(R.string.Gift2UpgradeButtonFree);
    L267:
        r212.append(r126);
        this.button.setFilled(true);
        this.button.setText(r212, !this.firstSet);
        this.button.setSubText(null, !this.firstSet);
        this.button.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda26(this));
    L268:
        r43 = null;
        r7 = true;
    L297:
        r132 = r98;
        r2 = r102;
        r92 = r153;
        r83 = r222;
        r14 = r262;
        r30 = r303;
        goto L299
    L266:
        r126 = LocaleController.getString(R.string.Gift2UpgradeButtonGift);
    L270:
        if (this.upgradedOnce == false) goto L289;
        if (this.viewPager == null) goto L289;
        if (this.giftsList == null) goto L289;
        if (getListPosition() < 0) goto L289;
        if (this.giftsList.findGiftToUpgrade(getListPosition()) < 0) goto L289;
        this.button.setFilled(false);
        int r127 = this.giftsList.findGiftToUpgrade(getListPosition());
        SpannableStringBuilder r213 = new SpannableStringBuilder();
        r213.append(LocaleController.getString(R.string.Gift2UpgradeNext));
        Object r46 = this.giftsList.get(r127);
        if ((r46 instanceof TL_stars.SavedStarGift) == false) goto L287;
        TL_stars.StarGift r47 = ((TL_stars.SavedStarGift) r46).gift;
        if (r47 == null) goto L287;
        TLRPC.Document r48 = r47.getDocument();
        if (r48 == null) goto L287;
        r213.append(" e");
        r213.setSpan(new AnimatedEmojiSpan(r48, this.button.getTextPaint().getFontMetricsInt()), r213.length() - 1, r213.length(), 33);
    L287:
        this.button.setText(r213, !this.firstSet);
        this.button.setSubText(null, !this.firstSet);
        this.button.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda27(this, r127));
    L289:
        if ((r98 instanceof TL_stars.TL_starGift) == true) goto L291;
    L296:
        r7 = true;
        this.button.setFilled(true);
        this.button.setText(LocaleController.getString(R.string.OK), !this.firstSet);
        r43 = null;
        this.button.setSubText(null, !this.firstSet);
        this.button.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda14(this));
        goto L297
    L291:
        if (TextUtils.isEmpty(r28) == true) goto L296;
        SpannableStringBuilder r214 = new SpannableStringBuilder("^  ");
        if (this.upgradeIconSpan != null) goto L295;
        this.upgradeIconSpan = new ColoredImageSpan(new UpgradeIcon(this.button, Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider)));
    L295:
        r7 = true;
        r214.setSpan(this.upgradeIconSpan, 0, 1, 33);
        r214.append(LocaleController.getString(R.string.Gift2GiftAnUpgrade));
        this.button.setFilled(true);
        this.button.setText(r214, !this.firstSet);
        this.button.setSubText(null, !this.firstSet);
        this.button.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda13(this));
        r43 = null;
        goto L297
    L209:
        r51 = new StarGiftSheet$$ExternalSyntheticLambda20(this, r121);
        goto L210
    L212:
        if (r120 != r4) goto L215;
        if (r302 == true) goto L215;
        if (r332 == true) goto L215;
        boolean r304 = r89;
    L232:
        r303 = r304;
        if (r62 != r4) goto L235;
        if (r332 == false) goto L240;
    L235:
        TableView r128 = this.tableView;
        String r452 = LocaleController.getString(R.string.Gift2To);
        int r215 = this.currentAccount;
        StarGiftSheet$$ExternalSyntheticLambda23 r810 = new StarGiftSheet$$ExternalSyntheticLambda23(this, r62);
        if (r332 == false) goto L238;
        StarGiftSheet$$ExternalSyntheticLambda24 r512 = null;
    L239:
        r128.addRowUser(r452, r215, r62, r810, null, r512);
        r303 = r304;
        goto L240
    L238:
        r512 = new StarGiftSheet$$ExternalSyntheticLambda24(this, r62);
    L215:
        r304 = r89;
        TableView r811 = this.tableView;
        String r453 = LocaleController.getString(R.string.Gift2From);
        int r812 = this.currentAccount;
        StarGiftSheet$$ExternalSyntheticLambda21 r813 = new StarGiftSheet$$ExternalSyntheticLambda21(this, r120);
        if (r120 != r4) goto L220;
    L226:
        String r50 = null;
    L227:
        if (r332 == false) goto L230;
        StarGiftSheet$$ExternalSyntheticLambda22 r513 = null;
    L231:
        r811.addRowUser(r453, r812, r120, r813, r50, r513);
        goto L232
    L230:
        r513 = new StarGiftSheet$$ExternalSyntheticLambda22(this, r120);
        goto L231
    L220:
        if (r120 == UserObject.ANONYMOUS) goto L226;
        if (UserObject.isDeleted(r139) == true) goto L226;
        if (r322 == true) goto L226;
        if (r332 == true) goto L226;
        r50 = LocaleController.getString(R.string.Gift2ButtonSendGift);
        goto L227
    L201:
        if (r222 == false) goto L203;
        r62 = this.dialogId;
        goto L204
    L203:
        r62 = r4;
        goto L204
    L196:
        if (r222 == false) goto L198;
        r120 = r4;
        goto L199
    L198:
        r120 = this.dialogId;
        goto L199
    L98:
        if (r29 == false) goto L102;
        SpannableStringBuilder r814 = AndroidUtilities.replaceTags(LocaleController.getString(R.string.Gift2SelfInfoUpgrade));
    L100:
        r41 = r814;
        goto L108
    L102:
        if (r352 <= r17) goto L107;
        if (r25 == false) goto L105;
        String r815 = "Gift2SelfInfoConverted";
    L106:
        r814 = AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma(r815, (int) r352));
        goto L100
    L105:
        r815 = "Gift2SelfInfoConvert";
        goto L106
    L107:
        r814 = AndroidUtilities.replaceTags(LocaleController.getString(R.string.Gift2SelfInfo));
        goto L100
    L92:
        r102 = r26;
    L94:
        r119 = LocaleController.getString(R.string.Gift2TitleSaved);
    L93:
        r102 = r26;
        r36 = 2;
        goto L94
    L110:
        r102 = r26;
        r36 = 2;
        if (r332 == true) goto L113;
    L115:
        if (r222 == true) goto L118;
        if (r29 == true) goto L118;
    L131:
        if (r117 == 0) goto L136;
        if (r98 == null) goto L136;
        if (r98.title == null) goto L136;
        StringBuilder r216 = new StringBuilder();
        r216.append(r98.title);
        r216.append(" #");
        boolean r816 = r6;
        r138 = r73;
        r216.append(LocaleController.formatNumber(r117, ','));
        String r129 = r216.toString();
    L141:
        this.title = r129;
        TopView r217 = this.topView;
        if (r262 == false) goto L144;
        String r40 = r129;
        CharSequence r412 = null;
    L192:
        r217.setText(0, r40, r412, null, releasedByText(r98));
        r89 = r816;
        goto L193
    L144:
        if (r322 == false) goto L146;
    L168:
        if (r222 == false) goto L175;
        if (r29 == true) goto L171;
    L173:
        int r65 = R.string.Gift2Info2OutExpired;
    L174:
        String r66 = LocaleController.formatString(r65, new Object[]{r31});
    L183:
        SpannableStringBuilder r67 = AndroidUtilities.replaceTags(r66);
        if (r322 == false) goto L186;
    L187:
        r40 = r129;
        boolean r75 = true;
        CharSequence r130 = _UrlKt.FRAGMENT_ENCODE_SET;
    L189:
        boolean r21 = r75;
        CharSequence[] r76 = new CharSequence[3];
        r76[r19] = r67;
        r76[r21 ? 1 : 0] = " ";
        r76[2] = r130;
        r412 = TextUtils.concat(r76);
        goto L192
    L186:
        if (canConvert() == false) goto L187;
        r40 = r129;
        r75 = true;
        r130 = AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.Gift2More).replace(' ', Typography.nbsp), new StarGiftSheet$$ExternalSyntheticLambda12(this)), true);
        goto L189
    L171:
        if (r232 <= r17) goto L173;
        r65 = R.string.Gift2Info2OutUpgrade;
        goto L174
    L175:
        if (r153 == true) goto L179;
        if (r332 == false) goto L178;
        int r68 = R.string.Gift2Info2ChannelKeep;
    L182:
        r66 = LocaleController.getString(r68);
        goto L183
    L178:
        r68 = R.string.Gift2Info2BotKeep;
        goto L182
    L179:
        if (r332 == false) goto L181;
        r68 = R.string.Gift2Info2ChannelRemove;
        goto L182
    L181:
        r68 = R.string.Gift2Info2BotRemove;
        goto L182
    L146:
        if (canSomeoneConvert() == false) goto L168;
        if (r222 == false) goto L160;
        if (r29 == true) goto L151;
    L153:
        if (r153 == false) goto L156;
        if (r25 == true) goto L156;
        r66 = LocaleController.formatString(R.string.Gift2InfoOutPinned, new Object[]{r31});
    L156:
        if (r25 == false) goto L158;
        String r69 = "Gift2InfoOutConverted";
    L159:
        r66 = LocaleController.formatPluralStringComma(r69, (int) r352, new Object[]{r31});
        goto L183
    L158:
        r69 = "Gift2InfoOut";
        goto L159
    L151:
        if (r232 <= r17) goto L153;
        r66 = LocaleController.formatString(R.string.Gift2Info2OutUpgrade, new Object[]{r31});
        goto L183
    L160:
        if (r25 == false) goto L164;
        if (r332 == false) goto L163;
        String r610 = "Gift2InfoChannelConverted";
    L167:
        r66 = LocaleController.formatPluralStringComma(r610, (int) r352);
        goto L183
    L163:
        r610 = "Gift2InfoConverted";
        goto L167
    L164:
        if (r332 == false) goto L166;
        r610 = "Gift2Info3Channel";
        goto L167
    L166:
        r610 = "Gift2Info3";
    L136:
        r816 = r6;
        r138 = r73;
        if (r222 == false) goto L139;
        int r131 = R.string.Gift2TitleSent;
    L140:
        r129 = LocaleController.getString(r131);
        goto L141
    L139:
        r131 = R.string.Gift2TitleReceived;
    L118:
        if (r232 <= r17) goto L131;
        TopView r140 = this.topView;
        if (r222 == false) goto L122;
        int r218 = R.string.Gift2TitleSent;
    L123:
        String r219 = LocaleController.getString(r218);
        this.title = r219;
        if (r262 == false) goto L126;
        String r413 = null;
    L130:
        r140.setText(0, r219, r413, null, releasedByText(r98));
        goto L109
    L126:
        if (r222 == true) goto L129;
        String r817 = LocaleController.getString(R.string.Gift2InfoInFreeUpgrade);
    L128:
        r413 = r817;
        goto L130
    L129:
        r817 = LocaleController.formatString(R.string.Gift2InfoFreeUpgrade, new Object[]{r31});
        goto L128
    L122:
        r218 = R.string.Gift2TitleReceived;
        goto L123
    L113:
        if (this.myProfile == true) goto L115;
        this.topView.setText(0, LocaleController.getString(R.string.Gift2TitleProfile), null, null, releasedByText(r98));
        goto L109
    L84:
        r143 = r123;
        r124 = r136;
        r137 = r19;
        goto L85
    L77:
        if (DialogObject.getPeerDialogId(r116) >= r17) goto L80;
        r332 = true;
        goto L79
    L71:
        r115 = null;
        goto L72
    L73:
        r19 = 0;
        r222 = r85;
        r20 = r112;
        r17 = 0;
        TLRPC.TL_messageActionStarGiftUnique r99 = (TLRPC.TL_messageActionStarGiftUnique) r95;
        boolean r141 = r99.saved;
        boolean r220 = r99.refunded;
        TL_stars.StarGift r38 = r99.gift;
        r73 = r99.from_id;
        TLRPC.Peer r818 = r99.peer;
        r153 = r141;
        r262 = r220;
        r98 = r38;
        r116 = r818;
        r10 = 0;
        r232 = 0;
        r86 = 0;
        r25 = false;
        r27 = false;
        r29 = false;
        r302 = false;
        r26 = null;
        r3 = null;
        r28 = null;
        goto L74
    L10:
        r6 = false;
        goto L11
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v19 */
    /* JADX WARN: Type inference failed for: r6v20 */
    /* JADX WARN: Type inference failed for: r6v22, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r6v23 */
    public StarGiftSheet set(TL_stars.SavedStarGift r35, StarsController.IGiftsList r36) {
        if (r35 != null) goto L5;
        return this;
    L5:
        this.myProfile = isMine(this.currentAccount, this.dialogId);
        this.savedStarGift = r35;
        this.giftsList = r36;
        this.messageObject = null;
        if (this.rolling == true) goto L14;
        Roller r3 = this.roller;
        if (r3 == null) goto L14;
        if (r3.isRolling() == false) goto L14;
        if (Roller.m19068$$Nest$fgetrollingGift(this.roller) == null) goto L14;
        this.roller.detach();
        this.roller = null;
        this.topView.imageLayout.setVisibility(0);
        TopView.m19082$$Nest$fgetimagesRollView(this.topView).setVisibility(4);
    L14:
        this.actionView.set(this.currentAccount, r35);
        String r32 = DialogObject.getShortName(this.dialogId);
        long r5 = DialogObject.getPeerDialogId(r35.from_id);
        boolean r7 = UserObject.isBot(MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r5)));
        int r8 = MessagesController.getInstance(this.currentAccount).stargiftsConvertPeriodMax - (ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() - r35.date);
        long r9 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        if ((r35.flags & 2) != 0) goto L18;
        r5 = UserObject.ANONYMOUS;
    L18:
        long r12 = this.dialogId;
        if (r12 >= 0) goto L21;
        boolean r11 = true;
    L22:
        TLRPC.TL_textWithEntities r2 = r35.message;
        boolean r4 = r35.refunded;
        TL_stars.StarGift r15 = r35.gift;
        if ((r15 instanceof TL_stars.TL_starGiftUnique) == false) goto L34;
        String r52 = r15.owner_address;
        String r6 = r15.gift_address;
        if (r15.host_id == null) goto L27;
        boolean r72 = true;
    L28:
        TL_stars.TL_starGiftUnique r152 = (TL_stars.TL_starGiftUnique) r15;
        if (r35.name_hidden == true) goto L31;
        TLObject r82 = MessagesController.getInstance(this.currentAccount).getUserOrChat(DialogObject.getPeerDialogId(r35.from_id));
    L32:
        set(r152, r4, r82, r35.message);
    L226:
        if (r35.refunded == false) goto L228;
        this.beforeTableTextView.setVisibility(0);
        this.beforeTableTextView.setText(LocaleController.getString(R.string.Gift2Refunded));
        this.beforeTableTextView.setTextColor(Theme.getColor(Theme.key_text_RedBold, this.resourcesProvider));
    L252:
        if (r72 == true) goto L259;
        if (TextUtils.isEmpty(r52) == true) goto L259;
        if (TextUtils.isEmpty(r6) == true) goto L259;
        this.afterTableTextView.setText(AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.Gift2InBlockchain), new StarGiftSheet$$ExternalSyntheticLambda76(this, r6)), true, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(0.66f)));
        ?? r62 = 0;
        this.afterTableTextView.setVisibility(0);
    L280:
        if (this.firstSet == false) goto L282;
        switchPage(r62, r62);
        this.layoutManager.scrollToPosition(1);
        this.firstSet = r62;
    L282:
        this.actionBar.setTitle(getTitle());
        updateViewPager();
        return this;
    L259:
        if (this.myProfile == true) goto L261;
    L278:
        r62 = 0;
        this.afterTableTextView.setVisibility(8);
        goto L280
    L261:
        if (isMine(this.currentAccount, this.dialogId) == false) goto L278;
        if (this.dialogId < 0) goto L272;
        SpannableStringBuilder r22 = new SpannableStringBuilder();
        if (r35.unsaved == false) goto L268;
        r22.append(". ");
        r22.setSpan(new ColoredImageSpan(R.drawable.mini_gift_hidden), 0, 1, 33);
    L268:
        if (r35.unsaved == true) goto L270;
        int r1 = R.string.Gift2ProfileVisible4;
    L271:
        r22.append(AndroidUtilities.replaceSingleTag(LocaleController.getString(r1), new StarGiftSheet$$ExternalSyntheticLambda18(this)));
        this.afterTableTextView.setText(AndroidUtilities.replaceArrows(r22, true, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(0.66f)));
    L277:
        r62 = 0;
        this.afterTableTextView.setVisibility(0);
        goto L280
    L270:
        r1 = R.string.Gift2ProfileInvisible4;
        goto L271
    L272:
        LinkSpanDrawable.LinksTextView r23 = this.afterTableTextView;
        if (r35.unsaved == true) goto L275;
        int r13 = R.string.Gift2ChannelProfileVisible3;
    L276:
        r23.setText(AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(r13), new StarGiftSheet$$ExternalSyntheticLambda18(this)), true, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(0.66f)));
        goto L277
    L275:
        r13 = R.string.Gift2ChannelProfileInvisible3;
        goto L276
    L228:
        if (r72 == false) goto L235;
        if (TextUtils.isEmpty(r52) == true) goto L235;
        if (TextUtils.isEmpty(r6) == true) goto L235;
        this.beforeTableTextView.setText(AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.Gift2InBlockchain), new StarGiftSheet$$ExternalSyntheticLambda75(this, r6)), true, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(0.66f)));
        this.beforeTableTextView.setVisibility(0);
        this.beforeTableTextView.setTextColor(Theme.getColor(Theme.key_dialogTextGray2, this.resourcesProvider));
    L235:
        if (TextUtils.isEmpty(r52) == true) goto L237;
    L251:
        this.beforeTableTextView.setVisibility(8);
        goto L252
    L237:
        if (TextUtils.isEmpty(r6) == false) goto L251;
        if (this.myProfile == false) goto L251;
        if ((r35.gift instanceof TL_stars.TL_starGift) == false) goto L251;
        if (r35.name_hidden == false) goto L251;
        this.beforeTableTextView.setVisibility(0);
        LinkSpanDrawable.LinksTextView r33 = this.beforeTableTextView;
        if (r2 != null) goto L247;
    L249:
        int r24 = R.string.Gift2InSenderHidden2;
    L250:
        r33.setText(LocaleController.getString(r24));
        this.beforeTableTextView.setTextColor(Theme.getColor(Theme.key_dialogTextGray2, this.resourcesProvider));
        goto L252
    L247:
        if (TextUtils.isEmpty(r2.text) == true) goto L249;
        r24 = R.string.Gift2InSenderMessageHidden2;
        goto L250
    L31:
        r82 = null;
        goto L32
    L27:
        r72 = false;
        goto L28
    L34:
        if (this.myProfile == true) goto L36;
    L40:
        boolean r122 = false;
    L41:
        TopView r132 = this.topView;
        boolean r30 = isWorn(this.currentAccount, getUniqueGift());
        if (getLink() == null) goto L44;
        boolean r31 = true;
    L45:
        r132.setGift(r15, false, false, r30, r31, false);
        this.tableView.clear();
        if (r122 == true) goto L48;
        long r19 = r9;
        boolean r10 = r4;
        if (r11 == true) goto L71;
    L73:
        boolean r34 = this.myProfile;
        if (r34 == true) goto L76;
    L77:
        CharSequence r42 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r35.upgrade_stars <= 0) goto L93;
        TopView r83 = this.topView;
        if (r34 == false) goto L84;
        int r37 = R.string.Gift2TitleReceived;
    L85:
        String r38 = LocaleController.getString(r37);
        this.title = r38;
        if (r10 == false) goto L89;
    L87:
        CharSequence r302 = null;
    L91:
        r83.setText(0, r38, r302, null, releasedByText(r35.gift));
    L153:
        if (r19 != r5) goto L155;
        if (r11 == true) goto L155;
    L166:
        this.tableView.addRow(LocaleController.getString(R.string.StarsTransactionDate), LocaleController.formatString(R.string.formatDateAtTime, new Object[]{LocaleController.getInstance().getFormatterGiveawayCard().format(new Date(r35.date * 1000)), LocaleController.getInstance().getFormatterDay().format(new Date(r35.date * 1000))}));
        TableView r39 = this.tableView;
        String r53 = LocaleController.getString(R.string.Gift2Value);
        String r63 = "⭐️ " + LocaleController.formatNumber(r35.gift.stars + r35.upgrade_stars, ',');
        if (canConvert() == false) goto L171;
        if (r10 == true) goto L171;
        CharSequence r133 = ButtonSpan.make(LocaleController.formatPluralStringComma("Gift2ButtonSell", (int) r35.convert_stars), new StarGiftSheet$$ExternalSyntheticLambda25(this), this.resourcesProvider);
    L172:
        r39.addRow(r53, StarsIntroActivity.replaceStarsWithPlain(TextUtils.concat(new CharSequence[]{r63, " ", r133}), 0.8f));
        TL_stars.StarGift r310 = r35.gift;
        if (r310.limited == false) goto L176;
        if (r10 == true) goto L176;
        StarsIntroActivity.addAvailabilityRow(this.tableView, this.currentAccount, r310, this.resourcesProvider);
    L176:
        TLRPC.TL_textWithEntities r311 = r35.message;
        if (r311 != null) goto L179;
    L182:
        boolean r312 = this.myProfile;
        if (r312 == false) goto L196;
        if (r35.can_upgrade == false) goto L196;
        SpannableStringBuilder r313 = new SpannableStringBuilder("^  ");
        if (this.upgradeIconSpan != null) goto L189;
        this.upgradeIconSpan = new ColoredImageSpan(new UpgradeIcon(this.button, Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider)));
    L189:
        r313.setSpan(this.upgradeIconSpan, 0, 1, 33);
        if (r35.upgrade_stars <= 0) goto L192;
        String r43 = LocaleController.getString(R.string.Gift2UpgradeButtonFree);
    L193:
        r313.append(r43);
        this.button.setFilled(true);
        this.button.setText(r313, !this.firstSet);
        this.button.setSubText(null, !this.firstSet);
        this.button.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda71(this));
    L194:
        r52 = null;
    L224:
        r6 = r52;
        r72 = false;
        goto L226
    L192:
        r43 = LocaleController.getString(R.string.Gift2UpgradeButtonGift);
    L196:
        if (this.upgradedOnce == false) goto L216;
        if (r312 == false) goto L216;
        if (this.viewPager == null) goto L216;
        if (this.giftsList == null) goto L216;
        if (getListPosition() < 0) goto L216;
        if (this.giftsList.findGiftToUpgrade(getListPosition()) < 0) goto L216;
        this.button.setFilled(false);
        int r314 = this.giftsList.findGiftToUpgrade(getListPosition());
        SpannableStringBuilder r44 = new SpannableStringBuilder();
        r44.append(LocaleController.getString(R.string.Gift2UpgradeNext));
        Object r54 = this.giftsList.get(r314);
        if ((r54 instanceof TL_stars.SavedStarGift) == false) goto L214;
        TL_stars.StarGift r55 = ((TL_stars.SavedStarGift) r54).gift;
        if (r55 == null) goto L214;
        TLRPC.Document r56 = r55.getDocument();
        if (r56 == null) goto L214;
        r44.append(" e");
        r44.setSpan(new AnimatedEmojiSpan(r56, this.button.getTextPaint().getFontMetricsInt()), r44.length() - 1, r44.length(), 33);
    L214:
        this.button.setText(r44, !this.firstSet);
        this.button.setSubText(null, !this.firstSet);
        this.button.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda72(this, r314));
    L216:
        if ((r35.gift instanceof TL_stars.TL_starGift) == true) goto L218;
    L223:
        this.button.setFilled(true);
        this.button.setText(LocaleController.getString(R.string.OK), !this.firstSet);
        r52 = null;
        this.button.setSubText(null, !this.firstSet);
        this.button.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda74(this));
        goto L224
    L218:
        if (TextUtils.isEmpty(r35.prepaid_upgrade_hash) == true) goto L223;
        SpannableStringBuilder r315 = new SpannableStringBuilder("^  ");
        if (this.upgradeIconSpan != null) goto L222;
        this.upgradeIconSpan = new ColoredImageSpan(new UpgradeIcon(this.button, Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider)));
    L222:
        r315.setSpan(this.upgradeIconSpan, 0, 1, 33);
        r315.append(LocaleController.getString(R.string.Gift2GiftAnUpgrade));
        this.button.setFilled(true);
        this.button.setText(r315, !this.firstSet);
        this.button.setSubText(null, !this.firstSet);
        this.button.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda73(this));
        goto L194
    L179:
        if (TextUtils.isEmpty(r311.text) == true) goto L182;
        if (r10 == true) goto L182;
        TableView r316 = this.tableView;
        TLRPC.TL_textWithEntities r45 = r35.message;
        r316.addFullRow(r45.text, r45.entities);
    L171:
        r133 = r42;
    L155:
        TLRPC.User r317 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r5));
        TableView r153 = this.tableView;
        CharSequence r16 = LocaleController.getString(R.string.Gift2From);
        int r84 = this.currentAccount;
        Runnable r92 = new StarGiftSheet$$ExternalSyntheticLambda69(this, r5);
        if (r5 != r19) goto L158;
    L164:
        CharSequence r21 = null;
    L165:
        r153.addRowUser(r16, r84, r5, r92, r21, new StarGiftSheet$$ExternalSyntheticLambda70(this, r5));
        goto L166
    L158:
        if (r5 == UserObject.ANONYMOUS) goto L164;
        if (r7 == true) goto L164;
        if (UserObject.isDeleted(r317) == true) goto L164;
        if (r11 == true) goto L164;
        r21 = LocaleController.getString(R.string.Gift2ButtonSendGift);
        goto L165
    L89:
        if (this.myProfile == false) goto L87;
        r302 = LocaleController.getString(R.string.Gift2InfoInFreeUpgrade);
        goto L91
    L84:
        r37 = R.string.Gift2TitleProfile;
    L93:
        if (r35.gift_num == 0) goto L99;
        TL_stars.StarGift r123 = r35.gift;
        if (r123 == null) goto L99;
        if (r123.title == null) goto L99;
        String r318 = r35.gift.title + " #" + LocaleController.formatNumber(r35.gift_num, ',');
    L103:
        this.title = r318;
        TopView r93 = this.topView;
        if (r10 == false) goto L106;
    L150:
        CharSequence r303 = null;
    L151:
        r93.setText(0, r318, r303, null, releasedByText(r35.gift));
        goto L153
    L106:
        if (this.myProfile == false) goto L150;
        if (r7 == true) goto L124;
        if (canConvert() == false) goto L124;
        if (this.myProfile == false) goto L122;
        if (r8 > 0) goto L118;
        if (r11 == false) goto L117;
        String r85 = "Gift2Info2ChannelExpired";
    L121:
        String r86 = LocaleController.formatPluralStringComma(r85, (int) r35.convert_stars);
    L141:
        SpannableStringBuilder r87 = AndroidUtilities.replaceTags(r86);
        if (r7 == false) goto L144;
    L145:
        boolean r134 = true;
        CharSequence r124 = r42;
    L147:
        boolean r332 = r134;
        CharSequence[] r135 = new CharSequence[3];
        r135[0] = r87;
        r135[r332 ? 1 : 0] = " ";
        r135[2] = r124;
        r303 = TextUtils.concat(r135);
        goto L151
    L144:
        if (canConvert() == false) goto L145;
        r134 = true;
        r124 = AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.Gift2More).replace(' ', Typography.nbsp), new StarGiftSheet$$ExternalSyntheticLambda68(this)), true);
        goto L147
    L117:
        r85 = "Gift2Info2Expired";
        goto L121
    L118:
        if (r11 == false) goto L120;
        r85 = "Gift2Info3Channel";
        goto L121
    L120:
        r85 = "Gift2Info3";
        goto L121
    L122:
        r86 = LocaleController.formatPluralStringComma("Gift2Info2Out", (int) r35.convert_stars, new Object[]{r32});
    L124:
        if (this.myProfile == false) goto L135;
        if (r35.unsaved == false) goto L131;
        if (r11 == false) goto L129;
        int r88 = R.string.Gift2Info2ChannelKeep;
    L130:
        r86 = LocaleController.getString(r88);
        goto L141
    L129:
        r88 = R.string.Gift2Info2BotKeep;
        goto L130
    L131:
        if (r11 == false) goto L133;
        r88 = R.string.Gift2Info2ChannelRemove;
        goto L130
    L133:
        r88 = R.string.Gift2Info2BotRemove;
        goto L130
    L135:
        if (r35.can_upgrade == true) goto L137;
    L139:
        int r89 = R.string.Gift2Info2OutExpired;
    L140:
        r86 = LocaleController.formatString(r89, new Object[]{r32});
        goto L141
    L137:
        if (r35.upgrade_stars <= 0) goto L139;
        r89 = R.string.Gift2Info2OutUpgrade;
    L99:
        if (r34 == false) goto L101;
        int r319 = R.string.Gift2TitleReceived;
    L102:
        r318 = LocaleController.getString(r319);
        goto L103
    L101:
        r319 = R.string.Gift2TitleProfile;
        goto L102
    L76:
        if (r35.can_upgrade == true) goto L77;
        r42 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L93
    L71:
        if (this.myProfile == true) goto L73;
        TopView r320 = this.topView;
        String r46 = LocaleController.getString(R.string.Gift2TitleProfile);
        this.title = r46;
        r320.setText(0, r46, null, null, releasedByText(r35.gift.released_by));
    L67:
        r42 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L153
    L48:
        if (r35.gift_num == 0) goto L54;
        TL_stars.StarGift r810 = r35.gift;
        if (r810 == null) goto L54;
        if (r810.title == null) goto L54;
        StringBuilder r811 = new StringBuilder();
        r811.append(r35.gift.title);
        r811.append(" #");
        r19 = r9;
        r811.append(LocaleController.formatNumber(r35.gift_num, ','));
        String r812 = r811.toString();
    L55:
        this.title = r812;
        TopView r94 = this.topView;
        if (r4 == false) goto L59;
        r10 = r4;
        CharSequence r304 = null;
    L66:
        r94.setText(0, r812, r304, null, releasedByText(r35.gift));
        goto L67
    L59:
        if (r35.can_upgrade == false) goto L61;
        r304 = AndroidUtilities.replaceTags(LocaleController.getString(R.string.Gift2SelfInfoUpgrade));
        r10 = r4;
        goto L66
    L61:
        r10 = r4;
        long r321 = r35.convert_stars;
        if (r321 <= 0) goto L65;
        CharSequence r322 = AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("Gift2SelfInfoConvert", (int) r321));
    L64:
        r304 = r322;
        goto L66
    L65:
        r322 = AndroidUtilities.replaceTags(LocaleController.getString(R.string.Gift2SelfInfo));
    L54:
        r19 = r9;
        r812 = LocaleController.getString(R.string.Gift2TitleSaved);
        goto L55
    L44:
        r31 = false;
        goto L45
    L36:
        if (r9 != r5) goto L40;
        if (r12 < 0) goto L40;
        r122 = true;
        goto L41
    L21:
        r11 = false;
        goto L22
    }

    public void set(TL_stars.TL_starGiftUnique r2, boolean r3) {
        set(r2, r3, null, null);
    }

    public void set(TL_stars.TL_starGiftUnique r32, boolean r33, TLObject r34, TLRPC.TL_textWithEntities r35) {
        long r9 = DialogObject.getPeerDialogId(r32.owner_id);
        long r11 = DialogObject.getPeerDialogId(r32.host_id);
        this.title = r32.title + " #" + LocaleController.formatNumber(r32.num, ',');
        if (this.rolling == true) goto L14;
        Roller r1 = this.roller;
        if (r1 == null) goto L14;
        if (r1.isRolling() == false) goto L14;
        if (Roller.m19068$$Nest$fgetrollingGift(this.roller) == null) goto L14;
        if (Roller.m19068$$Nest$fgetrollingGift(this.roller).id == r32.id) goto L14;
        this.roller.detach();
        this.roller = null;
        this.topView.imageLayout.setAlpha(1.0f);
        TopView.m19082$$Nest$fgetimagesRollView(this.topView).setAlpha(0.0f);
    L18:
        TopView r12 = this.topView;
        boolean r4 = isMineWithActions(this.currentAccount, r9);
        boolean r5 = isMineWithActions(this.currentAccount, r11);
        boolean r6 = isWorn(this.currentAccount, getUniqueGift());
        if (getLink() == null) goto L21;
        boolean r62 = true;
    L22:
        r12.setGift(r32, r4, r5, r6, r62, this.rolling);
        TL_stars.starGiftAttributeModel r2 = (TL_stars.starGiftAttributeModel) StarsController.findAttribute(r32.attributes, TL_stars.starGiftAttributeModel.class);
        SpannableStringBuilder r42 = new SpannableStringBuilder();
        r42.append(r32.title);
        r42.append(" ");
        int r52 = r42.length();
        r42.append("#" + LocaleController.formatNumber(r32.num, ','));
        r42.setSpan(new RelativeSizeSpan(0.85f), r52, r42.length(), 33);
        r42.setSpan(new EllipsizeSpanAnimator.TextAlphaSpan(190), r52, r42.length(), 33);
        if (r34 == null) goto L27;
        CharSequence r22 = AndroidUtilities.replaceTags(LocaleController.formatString(R.string.UniqueGiftFrom, new Object[]{DialogObject.getShortName(r34)}));
    L25:
        CharSequence r20 = r22;
    L31:
        if (r35 != null) goto L33;
    L35:
        Spannable r24 = null;
    L36:
        this.topView.setText(0, r42, r20, releasedByText(r32.released_by), null, r34, r24);
        this.ownerTextView = null;
        this.tableView.clear();
        if (r33 == true) goto L46;
        TLRPC.Peer r23 = r32.host_id;
        String r43 = r32.owner_address;
        if (r23 != null) goto L41;
        Class r8 = TL_stars.starGiftAttributeModel.class;
        if (TextUtils.isEmpty(r43) == true) goto L51;
        this.tableView.addWalletAddressRow(LocaleController.getString(R.string.Gift2Owner), r32.owner_address, new StarGiftSheet$$ExternalSyntheticLambda87(this));
    L57:
        addAttributeRow(StarsController.findAttribute(r32.attributes, r8));
        addAttributeRow(StarsController.findAttribute(r32.attributes, TL_stars.starGiftAttributePattern.class));
        addAttributeRow(StarsController.findAttribute(r32.attributes, TL_stars.starGiftAttributeBackdrop.class));
        if (r33 == false) goto L60;
    L71:
        TL_stars.starGiftAttributeOriginalDetails r25 = (TL_stars.starGiftAttributeOriginalDetails) StarsController.findAttribute(r32.attributes, TL_stars.starGiftAttributeOriginalDetails.class);
        if (r25 != null) goto L74;
    L109:
        Roller r26 = this.roller;
        if (r26 == null) goto L114;
        if (r26.isRolling() == false) goto L114;
    L138:
        this.actionBar.setTitle(getTitle());
        Roller r27 = this.roller;
        if (r27 != null) goto L141;
        return;
    L141:
        if (r27.set(r32, this.rolling, new StarGiftSheet$$ExternalSyntheticLambda94(this), new StarGiftSheet$$ExternalSyntheticLambda83(this)) == false) goto L145;
        this.topView.imageLayout.setAlpha(0.0f);
        TopView.m19082$$Nest$fgetimagesRollView(this.topView).setAlpha(1.0f);
        this.button.setText(LocaleController.getString(R.string.GiftSkipAnimation), true);
        this.button.setFilled(true);
        this.button.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda84(this));
        this.recyclerListView.scrollToPosition(this.adapter.getItemCount() - 1);
        this.recyclerListView.post(new StarGiftSheet$$ExternalSyntheticLambda85(this));
        return;
    L145:
        return;
    L114:
        if (isMine(this.currentAccount, DialogObject.getPeerDialogId(r32.owner_id)) == true) goto L119;
        if (r32.resell_amount == null) goto L119;
        this.button.setFilled(true);
        setButtonTextResale(r32);
        this.button.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda91(this));
    L119:
        if (this.upgradedOnce == true) goto L121;
    L137:
        this.button.setFilled(true);
        this.button.setText(LocaleController.getString(R.string.OK), !this.firstSet);
        this.button.setSubText(null, !this.firstSet);
        this.button.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda93(this));
        goto L138
    L121:
        if (this.viewPager == null) goto L137;
        if (this.giftsList == null) goto L137;
        if (getListPosition() < 0) goto L137;
        if (this.giftsList.findGiftToUpgrade(getListPosition()) < 0) goto L137;
        this.button.setFilled(false);
        int r28 = this.giftsList.findGiftToUpgrade(getListPosition());
        SpannableStringBuilder r3 = new SpannableStringBuilder();
        r3.append(LocaleController.getString(R.string.Gift2UpgradeNext));
        Object r44 = this.giftsList.get(r28);
        if ((r44 instanceof TL_stars.SavedStarGift) == false) goto L136;
        TL_stars.StarGift r45 = ((TL_stars.SavedStarGift) r44).gift;
        if (r45 == null) goto L136;
        TLRPC.Document r46 = r45.getDocument();
        if (r46 == null) goto L136;
        r3.append(" e");
        r3.setSpan(new AnimatedEmojiSpan(r46, this.button.getTextPaint().getFontMetricsInt()), r3.length() - 1, r3.length(), 33);
    L136:
        this.button.setText(r3, !this.firstSet);
        this.button.setSubText(null, !this.firstSet);
        this.button.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda92(this, r28));
        goto L138
    L74:
        if ((r25.flags & 1) == 0) goto L76;
        long r36 = DialogObject.getPeerDialogId(r25.sender_id);
        SpannableString r53 = new SpannableString(DialogObject.getName(r36));
        int r47 = 0;
        r53.setSpan(new AnonymousClass15(this, r36), 0, r53.length(), 33);
        SpannableString r37 = r53;
    L77:
        long r54 = DialogObject.getPeerDialogId(r25.recipient_id);
        SpannableString r7 = new SpannableString(DialogObject.getName(r54));
        r7.setSpan(new AnonymousClass16(this, r54), r47, r7.length(), 33);
        if (r25.message == null) goto L80;
        TextPaint r48 = new TextPaint(1);
        r48.setTextSize(AndroidUtilities.dp(14.0f));
        SpannableStringBuilder r55 = new SpannableStringBuilder(r25.message.text);
        MessageObject.addEntitiesToText(r55, r25.message.entities, false, false, false, false);
        Spannable r49 = MessageObject.replaceAnimatedEmoji(Emoji.replaceEmoji(r55, r48.getFontMetricsInt(), false), r25.message.entities, r48.getFontMetricsInt());
    L81:
        String r56 = LocaleController.getInstance().getFormatterYear().format(r25.date * 1000).replaceAll("\\.", "/");
        if (r25.sender_id != r25.recipient_id) goto L86;
        if (r49 != null) goto L85;
        CharSequence r29 = LocaleController.formatSpannable(R.string.Gift2AttributeOriginalDetailsSelf, new Object[]{r37, r56});
    L94:
        if (isMine(this.currentAccount, DialogObject.getPeerDialogId(r32.owner_id)) == false) goto L108;
        TL_stars.SavedStarGift r38 = this.savedStarGift;
        if (r38 != null) goto L98;
    L99:
        MessageObject r39 = this.messageObject;
        if (r39 == null) goto L108;
        TLRPC.Message r310 = r39.messageOwner;
        if (r310 == null) goto L108;
        TLRPC.MessageAction r311 = r310.action;
        if ((r311 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L108;
        if (((TLRPC.TL_messageActionStarGiftUnique) r311).drop_original_details_stars < 0) goto L108;
    L107:
        LinearLayout r312 = new LinearLayout(getContext());
        r312.setPadding(AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f), AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f));
        r312.setOrientation(0);
        SpoilersTextView r57 = new SpoilersTextView(getContext());
        r57.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
        r57.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, this.resourcesProvider));
        r57.setTextSize(1, 12.0f);
        r57.setGravity(3);
        r57.setText(r29);
        r312.addView(r57, LayoutHelper.createLinear(-1, -2, 1.0f, 19));
        ImageView r410 = new ImageView(getContext());
        r410.setScaleType(ImageView.ScaleType.CENTER);
        int r58 = Theme.key_featuredStickers_addButton;
        r410.setBackground(Theme.createRadSelectorDrawable(Theme.multAlpha(Theme.getColor(r58, this.resourcesProvider), 0.1f), 6, 6));
        r410.setImageResource(R.drawable.menu_delete_old);
        r410.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r58, this.resourcesProvider), PorterDuff.Mode.SRC_IN));
        ScaleStateListAnimator.apply(r410);
        r410.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda90(this, r29));
        r312.addView(r410, LayoutHelper.createLinear(32, 32, 0.0f, 21, 8, 0, 0, 0));
        TableRow r210 = new TableRow(getContext());
        TableRow.LayoutParams r411 = new TableRow.LayoutParams(-2, -1);
        r411.span = 2;
        r210.addView(new TableView.TableRowFullContent(this.tableView, r312, true), r411);
        this.tableView.addView(r210);
        goto L109
    L98:
        if (r38.drop_original_details_stars >= 0) goto L107;
    L108:
        TableView.TableRowFullContent r211 = this.tableView.addFullRow(r29);
        r211.setFilled(true);
        SpoilersTextView r212 = (SpoilersTextView) r211.getChildAt(0);
        r212.setTextSize(1, 12.0f);
        r212.setGravity(17);
        goto L109
    L85:
        r29 = LocaleController.formatSpannable(R.string.Gift2AttributeOriginalDetailsSelfComment, new Object[]{r37, r56, r49});
        goto L94
    L86:
        if (r37 == null) goto L90;
        if (r49 != null) goto L89;
        r29 = LocaleController.formatSpannable(R.string.Gift2AttributeOriginalDetails, new Object[]{r37, r7, r56});
        goto L94
    L89:
        r29 = LocaleController.formatSpannable(R.string.Gift2AttributeOriginalDetailsComment, new Object[]{r37, r7, r56, r49});
        goto L94
    L90:
        if (r49 != null) goto L92;
        r29 = LocaleController.formatSpannable(R.string.Gift2AttributeOriginalDetailsNoSender, new Object[]{r7, r56});
        goto L94
    L92:
        r29 = LocaleController.formatSpannable(R.string.Gift2AttributeOriginalDetailsNoSenderComment, new Object[]{r7, r56, r49});
        goto L94
    L80:
        r49 = null;
        goto L81
    L76:
        r47 = 0;
        r37 = null;
        goto L77
    L60:
        if (this.messageObject == null) goto L65;
        boolean r213 = this.messageObjectRepolled;
        TableView r59 = this.tableView;
        if (r213 == true) goto L64;
        TextView r214 = (TextView) ((TableView.TableRowContent) r59.addRow(LocaleController.getString(R.string.Gift2Quantity), _UrlKt.FRAGMENT_ENCODE_SET).getChildAt(1)).getChildAt(0);
        SpannableStringBuilder r313 = new SpannableStringBuilder("x ");
        LoadingSpan r412 = new LoadingSpan(r214, AndroidUtilities.dp(90.0f), 0, this.resourcesProvider);
        int r510 = Theme.key_windowBackgroundWhiteBlackText;
        r412.setColors(Theme.multAlpha(Theme.getColor(r510, this.resourcesProvider), 0.21f), Theme.multAlpha(Theme.getColor(r510, this.resourcesProvider), 0.08f));
        r313.setSpan(r412, 0, 1, 33);
        r214.setText(r313, TextView.BufferType.SPANNABLE);
        repollMessage();
    L67:
        if (TextUtils.isEmpty(r32.slug) == true) goto L71;
        if ((r32.flags & 256) == 0) goto L71;
        String r215 = BillingController.getInstance().formatCurrency(r32.value_amount, r32.value_currency, BillingController.getInstance().getCurrencyExp(r32.value_currency), true);
        String r314 = BillingController.getInstance().formatCurrency(r32.value_amount, r32.value_currency);
        this.tableView.addRow(LocaleController.getString(R.string.GiftValue2), "~" + r215, LocaleController.getString(R.string.GiftValue2LearnMore), new StarGiftSheet$$ExternalSyntheticLambda89(this, r32, r314));
        goto L71
    L64:
        r59.addRow(LocaleController.getString(R.string.Gift2Quantity), LocaleController.formatPluralStringComma("Gift2QuantityIssued1", r32.availability_issued) + LocaleController.formatPluralStringComma("Gift2QuantityIssued2", r32.availability_total));
        goto L67
    L65:
        this.tableView.addRow(LocaleController.getString(R.string.Gift2Quantity), LocaleController.formatPluralStringComma("Gift2QuantityIssued1", r32.availability_issued) + LocaleController.formatPluralStringComma("Gift2QuantityIssued2", r32.availability_total));
        goto L67
    L51:
        if (r9 == 0) goto L53;
    L55:
        if (r9 == 0) goto L57;
        this.ownerTextView = ((TableView.TableRowContent) this.tableView.addRowUserWithEmojiStatus(LocaleController.getString(R.string.Gift2Owner), this.currentAccount, r9, new StarGiftSheet$$ExternalSyntheticLambda88(this, r9)).getChildAt(1)).getChildAt(0);
        goto L57
    L53:
        if (r32.owner_name == null) goto L55;
        this.tableView.addRow(LocaleController.getString(R.string.Gift2Owner), r32.owner_name);
        goto L57
    L41:
        if (TextUtils.isEmpty(r43) == true) goto L44;
        this.tableView.addWalletAddressRow(LocaleController.getString(R.string.Gift2HostAddress), r32.owner_address, new StarGiftSheet$$ExternalSyntheticLambda82(this));
    L44:
        if (r11 == 0) goto L46;
        r8 = TL_stars.starGiftAttributeModel.class;
        this.ownerTextView = ((TableView.TableRowContent) this.tableView.addRowUserWithEmojiStatus(LocaleController.getString(R.string.Gift2Host), this.currentAccount, r11, new StarGiftSheet$$ExternalSyntheticLambda86(this, r11)).getChildAt(1)).getChildAt(0);
    L46:
        r8 = TL_stars.starGiftAttributeModel.class;
        goto L57
    L33:
        if (TopView.m19083$$Nest$fgetmessageTextPaint(this.topView) == null) goto L35;
        SpannableStringBuilder r216 = new SpannableStringBuilder(r35.text);
        MessageObject.addEntitiesToText(r216, r35.entities, false, false, false, false);
        r24 = MessageObject.replaceAnimatedEmoji(Emoji.replaceEmoji(r216, TopView.m19083$$Nest$fgetmessageTextPaint(this.topView).getFontMetricsInt(), false), r35.entities, TopView.m19083$$Nest$fgetmessageTextPaint(this.topView).getFontMetricsInt());
        goto L36
    L27:
        if (r32.released_by != null) goto L30;
        if (r2 == null) goto L30;
        r22 = r2.name;
    L30:
        r20 = null;
        goto L31
    L21:
        r62 = false;
    L14:
        if (this.rolling == false) goto L18;
        if (this.roller != null) goto L18;
        this.roller = new Roller(this.topView);
        goto L18
    }

    public StarGiftSheet setOnBoughtGift(BoughtGiftCallback r1) {
        this.boughtGift = r1;
        return this;
    }

    public StarGiftSheet setOnGiftUpdatedListener(Runnable r1) {
        this.onGiftUpdatedListener = r1;
        return this;
    }

    public StarGiftSheet setupWearPage() {
        TL_stars.TL_starGiftUnique r0 = getUniqueGift();
        if (r0 != null) goto L5;
        return this;
    L5:
        TLRPC.Peer r1 = r0.owner_id;
        if (r1 == null) goto L8;
    L7:
        long r12 = DialogObject.getPeerDialogId(r1);
        this.wearTitle.setText(LocaleController.formatString(R.string.Gift2WearTitle, new Object[]{r0.title + " #" + LocaleController.formatNumber(r0.num, ',')}));
        SpannableStringBuilder r02 = new SpannableStringBuilder(LocaleController.getString(R.string.Gift2WearStart));
        if (r12 == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L12;
    L17:
        this.button.setText(r02, true);
        this.button.setSubText(null, true);
        this.button.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda96(this));
        this.topView.setWearPreview(MessagesController.getInstance(this.currentAccount).getUserOrChat(r12));
        switchPage(2, false);
        this.onlyWearInfo = true;
        return this;
    L12:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L17;
        r02.append(" l");
        if (this.lockSpan != null) goto L16;
        this.lockSpan = new ColoredImageSpan(R.drawable.msg_mini_lock3);
    L16:
        r02.setSpan(this.lockSpan, r02.length() - 1, r02.length(), 33);
        goto L17
    L8:
        r1 = r0.host_id;
        goto L7
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public boolean shouldDrawBackground() {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        if (MessagesController.getInstance(this.currentAccount).isFrozen() == false) goto L7;
        AccountFrozenAlert.show(this.currentAccount);
        return;
    L7:
        if (this.slug == null) goto L12;
        if (this.slugStarGift != null) goto L12;
        AlertDialog r0 = new AlertDialog(getContext(), 3);
        r0.showDelayed(500);
        TL_stars.getUniqueStarGift r1 = new TL_stars.getUniqueStarGift();
        r1.slug = this.slug;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new StarGiftSheet$$ExternalSyntheticLambda6(this, r0));
    L29:
        super.show();
        return;
    L12:
        if (this.savedStarGift != null) goto L29;
        MessageObject r02 = this.messageObject;
        if (r02 == null) goto L29;
        TLRPC.Message r03 = r02.messageOwner;
        if (r03 == null) goto L29;
        TLRPC.MessageAction r04 = r03.action;
        if ((r04 instanceof TLRPC.TL_messageActionStarGift) == false) goto L29;
        TLRPC.TL_messageActionStarGift r05 = (TLRPC.TL_messageActionStarGift) r04;
        if (r05.upgraded == false) goto L29;
        if (r05.upgrade_msg_id == 0) goto L26;
        AlertDialog r4 = new AlertDialog(getContext(), 3);
        r4.showDelayed(500);
        TLRPC.TL_messages_getMessages r12 = new TLRPC.TL_messages_getMessages();
        r12.id.add(Integer.valueOf(r05.upgrade_msg_id));
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r12, new StarGiftSheet$$ExternalSyntheticLambda7(this, r05, r4));
        return;
    L26:
        if (getInputStarGift() == null) goto L29;
        AlertDialog r06 = new AlertDialog(getContext(), 3);
        r06.showDelayed(500);
        StarsController.getInstance(this.currentAccount).getUserStarGift(getInputStarGift(), new StarGiftSheet$$ExternalSyntheticLambda8(this, r06));
    }

    public boolean showCraft() {
        TL_stars.TL_starGiftUnique r0 = getUniqueGift();
        if (r0 != null) goto L5;
    L28:
        return false;
    L5:
        if (r0.crafted == true) goto L28;
        if (isMineWithActions(this.currentAccount, DialogObject.getPeerDialogId(r0.owner_id)) == true) goto L10;
        return false;
    L10:
        MessageObject r02 = this.messageObject;
        if (r02 == null) goto L21;
        TLRPC.Message r5 = r02.messageOwner;
        if (r5 != null) goto L15;
        return false;
    L15:
        TLRPC.MessageAction r52 = r5.action;
        if ((r52 instanceof TLRPC.TL_messageActionStarGiftUnique) == true) goto L18;
    L20:
        return false;
    L18:
        if (((TLRPC.TL_messageActionStarGiftUnique) r52).can_craft_at <= 0) goto L20;
        return true;
    L21:
        TL_stars.SavedStarGift r53 = this.savedStarGift;
        if (r53 == null) goto L28;
        if ((r53.gift instanceof TL_stars.TL_starGiftUnique) == false) goto L28;
        if (r53.can_craft_at <= 0) goto L28;
        return true;
    }

    public void showHint(CharSequence r9, View r10, boolean r11) {
        HintView2 r0 = this.currentHintView;
        if (r0 != null) goto L5;
    L9:
        if (r10 != null) goto L12;
        return;
    L12:
        if (r11 == false) goto L17;
        if ((r10 instanceof SimpleTextView) == false) goto L42;
        SimpleTextView r3 = (SimpleTextView) r10;
        float r4 = r3.getRightDrawableX() + (r3.getRightDrawableWidth() / 2.0f);
    L33:
        int[] r5 = new int[2];
        int[] r32 = new int[2];
        r10.getLocationOnScreen(r5);
        this.container.getLocationOnScreen(r32);
        r5[0] = r5[0] - r32[0];
        r5[1] = r5[1] - r32[1];
        HintView2 r33 = this.currentHintView;
        if (r33 == null) goto L36;
        r33.hide();
        this.currentHintView = null;
    L36:
        HintView2 r34 = new HintView2(getContext(), 3);
        r34.setMultilineText(!r11);
        r34.setText(r9);
        r34.setJointPx(0.0f, (r5[0] + r4) - (AndroidUtilities.dp(16.0f) + this.backgroundPaddingLeft));
        float r92 = (r5[1] - AndroidUtilities.dp(100.0f)) - (r10.getHeight() / 2.0f);
        if (r11 == false) goto L39;
        int r112 = 18;
    L40:
        r34.setTranslationY(r92 + AndroidUtilities.dp(r112 + 4.33f));
        r34.setDuration(3000);
        r34.setPadding(AndroidUtilities.dp(16.0f) + this.backgroundPaddingLeft, 0, AndroidUtilities.dp(16.0f) + this.backgroundPaddingLeft, 0);
        r34.setOnHiddenListener(new StarGiftSheet$$ExternalSyntheticLambda152(r34));
        r34.show();
        this.container.addView(r34, LayoutHelper.createFrame(-1, 100.0f));
        this.currentHintView = r34;
        this.currentHintViewTextView = r10;
        return;
    L39:
        r112 = 0;
        goto L40
    L42:
        return;
    L17:
        if ((r10 instanceof TextView) == false) goto L20;
        Layout r35 = ((TextView) r10).getLayout();
    L22:
        if (r35 == null) goto L47;
        CharSequence r42 = r35.getText();
        if ((r42 instanceof Spanned) == false) goto L48;
        Spanned r43 = (Spanned) r42;
        ButtonSpan[] r52 = (ButtonSpan[]) r43.getSpans(0, r43.length(), ButtonSpan.class);
        if (r52 != null) goto L30;
        return;
    L30:
        if (r52.length <= 0) goto L49;
        r4 = (r10.getPaddingLeft() + r35.getPrimaryHorizontal(r43.getSpanStart(r52[r52.length - 1]))) + (r5.getSize() / 2.0f);
        goto L33
    L49:
        return;
    L48:
        return;
    L47:
        return;
    L20:
        if ((r10 instanceof SimpleTextView) == false) goto L43;
        r35 = ((SimpleTextView) r10).getLayout();
        goto L22
    L43:
        return;
    L5:
        if (r0.shown() == false) goto L9;
        if (this.currentHintViewTextView != r10) goto L9;
    }

    public void switchPage(int r2, boolean r3) {
        switchPage(r2, r3, null);
    }

    public void switchPage(int r8, boolean r9, Runnable r10) {
        ValueAnimator r0 = this.switchingPagesAnimator;
        if (r0 == null) goto L6;
        r0.cancel();
        this.switchingPagesAnimator = null;
    L6:
        if (r8 == 1) goto L9;
        AndroidUtilities.cancelRunOnUIThread(TopView.m19076$$Nest$fgetcheckToRotateRunnable(this.topView));
    L9:
        if (this.firstSet == true) goto L11;
        this.lastTop = Float.valueOf(this.container.top());
    L11:
        PageTransition r3 = this.currentPage;
        int r4 = 0;
        if (r3 != null) goto L14;
        int r32 = 0;
    L15:
        this.currentPage = new PageTransition(r32, r8, 0.0f);
        Adapter r2 = this.adapter;
        int r33 = this.topView.getFinalHeight();
        int r5 = getBottomHeight();
        if (this.currentPage.to(1) == true) goto L18;
    L20:
        int r6 = 0;
    L21:
        r2.setHeights(r33, r5 + r6);
        if (this.currentPage.to != 0) goto L27;
        Roller r22 = this.roller;
        if (r22 == null) goto L27;
        r22.stopPreload();
    L27:
        if (r9 == false) goto L45;
        LinearLayout r92 = this.infoLayout;
        if (this.currentPage.contains(0) == false) goto L31;
        int r62 = 0;
    L32:
        r92.setVisibility(r62);
        LinearLayout r93 = this.upgradeLayout;
        if (this.currentPage.contains(1) == false) goto L35;
        int r02 = 0;
    L36:
        r93.setVisibility(r02);
        LinearLayout r94 = this.wearLayout;
        if (this.currentPage.contains(2) == false) goto L39;
        int r03 = 0;
    L40:
        r94.setVisibility(r03);
        LinearLayout r95 = this.craftLayout;
        if (this.currentPage.contains(3) == true) goto L44;
        r4 = 8;
    L44:
        r95.setVisibility(r4);
        ValueAnimator r96 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.switchingPagesAnimator = r96;
        r96.addUpdateListener(new StarGiftSheet$$ExternalSyntheticLambda67(this));
        this.switchingPagesAnimator.addListener(new AnonymousClass13(this, r8, r10));
        this.switchingPagesAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.switchingPagesAnimator.setDuration(320);
        this.switchingPagesAnimator.start();
        this.topView.prepareSwitchPage(this.currentPage);
    L64:
        HintView2 r82 = this.currentHintView;
        if (r82 == null) goto L68;
        r82.hide();
        this.currentHintView = null;
        return;
    L68:
        return;
    L39:
        r03 = 8;
        goto L40
    L35:
        r02 = 8;
        goto L36
    L31:
        r62 = 8;
        goto L32
    L45:
        this.currentPage.setProgress(1.0f);
        onSwitchedPage();
        LinearLayout r97 = this.infoLayout;
        if (r8 != 0) goto L48;
        int r63 = 0;
    L49:
        r97.setVisibility(r63);
        LinearLayout r98 = this.upgradeLayout;
        if (r8 != 1) goto L52;
        int r04 = 0;
    L53:
        r98.setVisibility(r04);
        LinearLayout r99 = this.wearLayout;
        if (r8 != 2) goto L56;
        int r05 = 0;
    L57:
        r99.setVisibility(r05);
        LinearLayout r910 = this.craftLayout;
        if (r8 == 3) goto L61;
        r4 = 8;
    L61:
        r910.setVisibility(r4);
        updateUnderButtonContainer();
        if (r10 == null) goto L64;
        r10.run();
        goto L64
    L56:
        r05 = 8;
        goto L57
    L52:
        r04 = 8;
        goto L53
    L48:
        r63 = 8;
        goto L49
    L18:
        if (this.underButtonContainer.getVisibility() != 0) goto L20;
        r6 = this.underButtonContainer.getMeasuredHeight();
        goto L21
    L14:
        r32 = r3.to;
        goto L15
    }

    public void toggleWear() {
        toggleWear(false);
    }

    public void toggleWear(boolean r11) {
        TL_stars.TL_starGiftUnique r0 = getUniqueGift();
        if (r0 != null) goto L5;
        return;
    L5:
        MessagesController.getGlobalMainSettings().edit().putInt("statusgiftpage", 3).apply();
        boolean r1 = isWorn(this.currentAccount, getUniqueGift());
        boolean r2 = !r1;
        if (isWorn(this.currentAccount, getUniqueGift()) == false) goto L8;
        MessagesController.getInstance(this.currentAccount).updateEmojiStatus(getDialogId(), new TLRPC.TL_emojiStatusEmpty(), null);
    L18:
        TopView.Button r112 = this.topView.buttons[1];
        if (r1 == true) goto L21;
        int r02 = R.drawable.filled_crown_off;
    L22:
        if (r1 == true) goto L24;
        int r12 = R.string.Gift2ActionWearOff;
    L25:
        r112.set(r02, LocaleController.getString(r12), true);
        if (this.onlyWearInfo == false) goto L29;
        dismiss();
        return;
    L29:
        StarGiftSheet$$ExternalSyntheticLambda121 r113 = new StarGiftSheet$$ExternalSyntheticLambda121(this, r2);
        if (this.currentPage.is(0) == false) goto L32;
        r113.run();
    L33:
        this.button.setText(LocaleController.getString(R.string.OK), !this.firstSet);
        this.button.setSubText(null, !this.firstSet);
        this.button.setOnClickListener(new StarGiftSheet$$ExternalSyntheticLambda122(this));
        return;
    L32:
        switchPage(0, true, r113);
        goto L33
    L24:
        r12 = R.string.Gift2ActionWear;
        goto L25
    L21:
        r02 = R.drawable.filled_crown_on;
        goto L22
    L8:
        long r6 = getDialogId();
        if (r6 >= 0) goto L11;
        if (r11 == true) goto L17;
        MessagesController r114 = MessagesController.getInstance(this.currentAccount);
        this.button.setLoading(true);
        MessagesController.getInstance(this.currentAccount).getBoostsController().getBoostsStats(r6, new StarGiftSheet$$ExternalSyntheticLambda120(this, r114, r6));
        return;
    L17:
        TLRPC.TL_inputEmojiStatusCollectible r115 = new TLRPC.TL_inputEmojiStatusCollectible();
        r115.collectible_id = r0.id;
        MessagesController.getInstance(this.currentAccount).updateEmojiStatus(getDialogId(), r115, r0);
        goto L18
    L11:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L17;
        getBulletinFactory().createSimpleBulletinDetail(R.raw.star_premium_2, AndroidUtilities.premiumText(LocaleController.getString(R.string.Gift2ActionWearNeededPremium), new StarGiftSheet$$ExternalSyntheticLambda119(this))).ignoreDetach().show();
    }
}
