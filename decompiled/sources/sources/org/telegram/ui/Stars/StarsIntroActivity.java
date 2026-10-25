package org.telegram.ui.Stars;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ReplacementSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.cast.CredentialsData;
import com.google.android.material.navigation.NavigationBarView;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;
import kotlin.text.Typography;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.BirthdayController;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.WebFile;
import org.telegram.messenger.browser.Browser;
import org.telegram.messenger.utils.tlutils.AmountUtils$Amount;
import org.telegram.messenger.utils.tlutils.AmountUtils$Currency;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_payments;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.AccountFrozenAlert;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.AvatarSpan;
import org.telegram.ui.Cells.ChatActionCell$$ExternalSyntheticLambda13;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.SessionCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.ChatEditActivity;
import org.telegram.ui.ChatUsersActivity;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ButtonSpan;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.FireworksOverlay;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.LoadingSpan;
import org.telegram.ui.Components.OutlineTextContainerView;
import org.telegram.ui.Components.Premium.GLIcon.GLIconRenderer;
import org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView;
import org.telegram.ui.Components.Premium.StarParticlesView;
import org.telegram.ui.Components.Premium.boosts.UserSelectorBottomSheet;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.StarAppsSheet;
import org.telegram.ui.Components.TableView;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.TextHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.ViewPagerFixed;
import org.telegram.ui.Components.spoilers.SpoilerEffect2;
import org.telegram.ui.Gifts.GiftSheet;
import org.telegram.ui.GradientHeaderActivity;
import org.telegram.ui.ImageReceiverSpan;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.PostSuggestionsEditActivity;
import org.telegram.ui.PrivacyControlActivity;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.Stars.StarGiftSheet;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.Stories.recorder.HintView2;
import org.telegram.ui.bots.AffiliateProgramFragment;
import org.telegram.ui.bots.ChannelAffiliateProgramsFragment;

/* loaded from: classes7.dex */
public class StarsIntroActivity extends GradientHeaderActivity implements NotificationCenter.NotificationCenterDelegate {
    private static DecimalFormat floatFormat;
    private static DecimalFormat floatFormat2;
    private final int BUTTON_AFFILIATE;
    private final int BUTTON_EXPAND;
    private final int BUTTON_GIFT;
    private final int BUTTON_SUBSCRIPTIONS_EXPAND;
    private FrameLayout aboveTitleView;
    private UniversalAdapter adapter;
    private LinearLayout balanceLayout;
    private ButtonWithCounterView buyButton;
    private View emptyLayout;
    private boolean expanded;
    private FireworksOverlay fireworksOverlay;
    private ButtonWithCounterView giftButton;
    private boolean hadTransactions;
    private GLIconTextureView iconTextureView;
    private FrameLayout oneButtonsLayout;
    private SpannableStringBuilder starBalanceIcon;
    private AnimatedTextView starBalanceTextView;
    private TextView starBalanceTitleView;
    private ButtonWithCounterView topupButton;
    private StarsTransactionsLayout transactionsLayout;
    private boolean twoButtons;
    private LinearLayout twoButtonsLayout;
    private ButtonWithCounterView withdrawButton;

    /* renamed from: org.telegram.ui.Stars.StarsIntroActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends View {
        final /* synthetic */ StarsIntroActivity this$0;

        public AnonymousClass1(StarsIntroActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onMeasure(int r4, int r5) {
            StarsIntroActivity r52 = this.this$0;
            if (r52.isLandscapeMode == false) goto L5;
            int r0 = (r52.statusBarHeight + StarsIntroActivity.access$000(r52).getMeasuredHeight()) - AndroidUtilities.dp(16.0f);
        L9:
            super.onMeasure(r4, View.MeasureSpec.makeMeasureSpec((int) (r0 - (StarsIntroActivity.access$100(this.this$0) * 2.5f)), TLObject.FLAG_30));
            return;
        L5:
            int r53 = AndroidUtilities.dp(140.0f);
            StarsIntroActivity r02 = this.this$0;
            int r54 = r53 + r02.statusBarHeight;
            if ((r02.backgroundView.getMeasuredHeight() + AndroidUtilities.dp(24.0f)) <= r54) goto L8;
            r54 = this.this$0.backgroundView.getMeasuredHeight() + AndroidUtilities.dp(24.0f);
        L8:
            r0 = r54;
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarsIntroActivity$10, reason: invalid class name */
    public class AnonymousClass10 extends ClickableSpan {
        final /* synthetic */ Context val$context;
        final /* synthetic */ BottomSheet[] val$sheet;
        final /* synthetic */ boolean val$ton;

        public AnonymousClass10(BottomSheet[] r1, Context r2, boolean r3) {
            this.val$sheet = r1;
            this.val$context = r2;
            this.val$ton = r3;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r2) {
            this.val$sheet[0].dismiss();
            Context r22 = this.val$context;
            if (this.val$ton == false) goto L5;
            int r1 = R.string.StarsTransactionTONFromFragmentLink;
        L6:
            Browser.openUrl(r22, LocaleController.getString(r1));
            return;
        L5:
            r1 = R.string.StarsTransactionUnknownLink;
            goto L6
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            r1.setUnderlineText(false);
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarsIntroActivity$11, reason: invalid class name */
    public class AnonymousClass11 extends ClickableSpan {
        final /* synthetic */ Runnable val$open;

        public AnonymousClass11(Runnable r1) {
            this.val$open = r1;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r1) {
            this.val$open.run();
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            r1.setUnderlineText(false);
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarsIntroActivity$12, reason: invalid class name */
    public class AnonymousClass12 implements NotificationCenter.NotificationCenterDelegate {
        final /* synthetic */ boolean[] val$maybeCloseAfterUpdate;
        final /* synthetic */ BottomSheet[] val$sheet;

        public AnonymousClass12(boolean[] r1, BottomSheet[] r2) {
            this.val$maybeCloseAfterUpdate = r1;
            this.val$sheet = r2;
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r1, int r2, Object... r3) {
            if (r1 == NotificationCenter.starSubscriptionsLoaded) goto L5;
            return;
        L5:
            if (this.val$maybeCloseAfterUpdate[0] == false) goto L11;
            BottomSheet r0 = this.val$sheet[0];
            if (r0 == null) goto L12;
            r0.dismiss();
            return;
        L12:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarsIntroActivity$13, reason: invalid class name */
    public class AnonymousClass13 extends ClickableSpan {
        final /* synthetic */ long val$did;
        final /* synthetic */ BottomSheet[] val$sheet;

        public AnonymousClass13(BottomSheet[] r1, long r2) {
            this.val$sheet = r1;
            this.val$did = r2;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r3) {
            this.val$sheet[0].dismiss();
            BaseFragment r32 = LaunchActivity.getSafeLastFragment();
            if (r32 == null) goto L6;
            r32.presentFragment(ChatActivity.of(this.val$did));
            return;
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            r1.setUnderlineText(false);
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarsIntroActivity$14, reason: invalid class name */
    public class AnonymousClass14 extends Browser.Progress {
        final /* synthetic */ ButtonWithCounterView val$button;

        public AnonymousClass14(ButtonWithCounterView r1) {
            this.val$button = r1;
        }

        @Override // org.telegram.messenger.browser.Browser.Progress
        public void end() {
            this.val$button.setLoading(false);
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarsIntroActivity$15, reason: invalid class name */
    public class AnonymousClass15 extends ReplacementSpan {
        private final Paint backgroundPaint;
        private final Text layout;
        final /* synthetic */ int val$color;
        final /* synthetic */ String val$string;

        public AnonymousClass15(int r3, String r4) {
            this.val$color = r3;
            this.val$string = r4;
            Paint r0 = new Paint(1);
            this.backgroundPaint = r0;
            r0.setColor(Theme.multAlpha(r3, 0.1f));
            this.layout = new Text(r4, 13.0f, AndroidUtilities.bold());
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(Canvas r7, CharSequence r8, int r9, int r10, float r11, int r12, int r13, int r14, Paint r15) {
            RectF r82 = AndroidUtilities.rectTmp;
            r82.set(r11, (r12 - AndroidUtilities.dp(20.0f)) / 2.0f, (AndroidUtilities.dp(12.0f) + r11) + this.layout.getCurrentWidth(), (AndroidUtilities.dp(20.0f) + r12) / 2.0f);
            r7.drawRoundRect(r82, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), this.backgroundPaint);
            this.layout.draw(r7, r11 + AndroidUtilities.dp(6.0f), (r12 + r14) / 2.0f, this.val$color, 1.0f);
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
            return (int) (AndroidUtilities.dp(12.0f) + this.layout.getCurrentWidth());
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarsIntroActivity$16, reason: invalid class name */
    public class AnonymousClass16 implements TextWatcher {
        private boolean ignore;
        private int shakeDp;
        final /* synthetic */ boolean val$allowClear;
        final /* synthetic */ ButtonWithCounterView val$button;
        final /* synthetic */ EditTextBoldCursor val$editText;
        final /* synthetic */ OutlineTextContainerView val$editTextContainer;
        final /* synthetic */ long val$stars;
        final /* synthetic */ TextView val$subPriceView;

        public AnonymousClass16(EditTextBoldCursor r1, OutlineTextContainerView r2, long r3, boolean r5, ButtonWithCounterView r6, TextView r7) {
            this.val$editText = r1;
            this.val$editTextContainer = r2;
            this.val$stars = r3;
            this.val$allowClear = r5;
            this.val$button = r6;
            this.val$subPriceView = r7;
            this.shakeDp = 2;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r10) {
            if (this.ignore == false) goto L34;
            return;
        L34:
        L14:
            long r4 = 0;
        L15:
            this.ignore = true;
            EditTextBoldCursor r102 = this.val$editText;
            long r6 = this.val$stars;
            if (r6 > 0) goto L18;
            String r62 = _UrlKt.FRAGMENT_ENCODE_SET;
        L19:
            r102.setText(r62);
            EditTextBoldCursor r103 = this.val$editText;
            r103.setSelection(r103.getText().length());
        L20:
            boolean r104 = false;
            this.ignore = false;
            if (this.val$allowClear == true) goto L26;
            ButtonWithCounterView r63 = this.val$button;
            if (r4 <= 0) goto L25;
            r104 = true;
        L25:
            r63.setEnabled(r104);
        L26:
            this.val$editTextContainer.animateSelection(this.val$editText.isFocused(), true ^ TextUtils.isEmpty(this.val$editText.getText()));
            TextView r1 = this.val$subPriceView;
            if (r4 != 0) goto L29;
            r1.animate().alpha(0.0f).start();
            this.val$subPriceView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            return;
        L29:
            r1.animate().alpha(1.0f).start();
            this.val$subPriceView.setText("≈" + BillingController.getInstance().formatCurrency((long) ((r4 / 1000.0d) * MessagesController.getInstance(UserConfig.selectedAccount).starsUsdWithdrawRate1000), "USD"));
            return;
        L18:
            r62 = Long.toString(r6);
            goto L19
        L7:
            if (TextUtils.isEmpty(r10) == false) goto L9;
            r4 = 0;
        L32:
            if (r4 <= MessagesController.getInstance(UserConfig.selectedAccount).starsPaidPostAmountMax) goto L20;
            this.ignore = true;     // Catch: Exception -> L31
            EditTextBoldCursor r105 = this.val$editText;     // Catch: Exception -> L31
            r4 = MessagesController.getInstance(UserConfig.selectedAccount).starsPaidPostAmountMax;     // Catch: Exception -> L31
            r105.setText(Long.toString(r4));     // Catch: Exception -> L31
            EditTextBoldCursor r106 = this.val$editText;     // Catch: Exception -> L31
            r106.setSelection(r106.getText().length());     // Catch: Exception -> L31
            OutlineTextContainerView r107 = this.val$editTextContainer;     // Catch: Exception -> L31
            int r64 = -this.shakeDp;
            this.shakeDp = r64;     // Catch: Exception -> L31
            AndroidUtilities.shakeViewSpring(r107, r64);     // Catch: Exception -> L31
        L9:
            r4 = Long.parseLong(r10.toString());     // Catch: Exception -> L14
            goto L32
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarsIntroActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        final /* synthetic */ StarsIntroActivity this$0;

        public AnonymousClass2(StarsIntroActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r2) {
            if (StarsIntroActivity.m19195$$Nest$fgettwoButtons(this.this$0) == false) goto L7;
            return false;
        L7:
            return super.dispatchTouchEvent(r2);
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarsIntroActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends LinearLayout {
        final /* synthetic */ StarsIntroActivity this$0;

        public AnonymousClass3(StarsIntroActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r2) {
            if (StarsIntroActivity.m19195$$Nest$fgettwoButtons(this.this$0) == true) goto L7;
            return false;
        L7:
            return super.dispatchTouchEvent(r2);
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarsIntroActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends StarParticlesView {
        Paint[] paints;
        final /* synthetic */ int val$particlesCount;
        final /* synthetic */ int val$type;

        public static /* synthetic */ Paint $r8$lambda$k4ZQZ2fKDgpISTQQ6qlUlFyE6yA(AnonymousClass4 r0, Integer r1) {
            return r0.lambda$configure$0(r1);
        }

        public AnonymousClass4(Context r1, int r2, int r3) {
            this.val$particlesCount = r2;
            this.val$type = r3;
            super(r1);
            setClipWithGradient();
        }

        private /* synthetic */ Paint lambda$configure$0(Integer r2) {
            return this.paints[r2.intValue() % this.paints.length];
        }

        @Override // org.telegram.ui.Components.Premium.StarParticlesView
        public void configure() {
            StarParticlesView.Drawable r0 = new StarParticlesView.Drawable(this.val$particlesCount);
            this.drawable = r0;
            r0.type = 105;
            int r1 = 0;
            r0.roundEffect = false;
            r0.useRotate = false;
            r0.useBlur = true;
            r0.checkBounds = true;
            r0.isCircle = false;
            r0.useScale = true;
            r0.startFromCenter = true;
            if (this.val$type != 1) goto L5;
            r0.centerOffsetY = AndroidUtilities.dp(24.0f);
        L5:
            this.paints = new Paint[20];
        L6:
            Paint[] r02 = this.paints;
            if (r1 >= r02.length) goto L9;
            r02[r1] = new Paint(1);
            this.paints[r1].setColorFilter(new PorterDuffColorFilter(ColorUtils.blendARGB(-371690, -14281, r1 / (this.paints.length - 1)), PorterDuff.Mode.SRC_IN));
            r1 = r1 + 1;
            goto L6
        L9:
            this.drawable.getPaint = new StarsIntroActivity$4$$ExternalSyntheticLambda0(this);
            StarParticlesView.Drawable r7 = this.drawable;
            r7.size1 = 17;
            r7.size2 = 18;
            r7.size3 = 19;
            r7.colorKey = Theme.key_windowBackgroundWhiteBlackText;
            r7.init();
        }

        @Override // org.telegram.ui.Components.Premium.StarParticlesView
        public int getStarsRectWidth() {
            return getMeasuredWidth();
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarsIntroActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends UniversalAdapter {
        final /* synthetic */ StarsIntroActivity this$0;

        public AnonymousClass5(StarsIntroActivity r1, RecyclerListView r2, Context r3, int r4, int r5, boolean r6, Utilities.Callback2 r7, Theme.ResourcesProvider r8) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8);
        }

        @Override // org.telegram.ui.Components.UniversalAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r9, int r10) {
            if (r10 != 42) goto L7;
            HeaderCell r1 = new HeaderCell(this.this$0.getContext(), Theme.key_windowBackgroundWhiteBlueHeader, 21, 0, false, StarsIntroActivity.access$1200(this.this$0));
            r1.setHeight(25);
            return new RecyclerListView.Holder(r1);
        L7:
            return super.onCreateViewHolder(r9, r10);
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarsIntroActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends BackupImageView {
        private Path clipPath;
        private RectF clipRect;
        private Drawable lock;
        private SpoilerEffect2 spoilerEffect2;
        final /* synthetic */ Context val$context;

        public AnonymousClass6(Context r1, Context r2) {
            this.val$context = r2;
            super(r1);
            this.clipPath = new Path();
            this.clipRect = new RectF();
            this.lock = r2.getResources().getDrawable(R.drawable.large_locked_post).mutate();
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r8) {
            super.dispatchDraw(r8);
            if (this.spoilerEffect2 != null) goto L6;
            this.spoilerEffect2 = SpoilerEffect2.getInstance(this);
        L6:
            if (this.spoilerEffect2 == null) goto L8;
            this.clipRect.set(0.0f, 0.0f, getWidth(), getHeight());
            this.clipPath.rewind();
            this.clipPath.addRoundRect(this.clipRect, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f), Path.Direction.CW);
            r8.save();
            r8.clipPath(this.clipPath);
            AnonymousClass6 r3 = this;
            Canvas r2 = r8;
            this.spoilerEffect2.draw(r2, r3, getWidth(), getHeight(), 1.0f);
            r2.restore();
        L9:
            r3.lock.setBounds((r3.getWidth() - r3.lock.getIntrinsicWidth()) / 2, (r3.getHeight() - r3.lock.getIntrinsicHeight()) / 2, (r3.getWidth() + r3.lock.getIntrinsicWidth()) / 2, (r3.getHeight() + r3.lock.getIntrinsicHeight()) / 2);
            r3.lock.draw(r2);
            return;
        L8:
            r3 = this;
            r2 = r8;
            goto L9
        }

        @Override // org.telegram.ui.Components.BackupImageView, android.view.View
        public void onAttachedToWindow() {
            SpoilerEffect2 r0 = this.spoilerEffect2;
            if (r0 == null) goto L5;
            r0.attach(this);
        L5:
            super.onAttachedToWindow();
        }

        @Override // org.telegram.ui.Components.BackupImageView, android.view.View
        public void onDetachedFromWindow() {
            SpoilerEffect2 r0 = this.spoilerEffect2;
            if (r0 == null) goto L5;
            r0.detach(this);
        L5:
            super.onDetachedFromWindow();
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarsIntroActivity$7, reason: invalid class name */
    public class AnonymousClass7 implements ImageReceiver.ImageReceiverDelegate {
        final /* synthetic */ boolean[] val$played;

        public AnonymousClass7(boolean[] r1) {
            this.val$played = r1;
        }

        @Override // org.telegram.messenger.ImageReceiver.ImageReceiverDelegate
        public void didSetImage(ImageReceiver r1, boolean r2, boolean r3, boolean r4) {
            if (r2 == false) goto L9;
            RLottieDrawable r12 = r1.getLottieAnimation();
            if (r12 != null) goto L6;
            return;
        L6:
            if (this.val$played[0] == true) goto L11;
            r12.setCurrentFrame(0, false);
            AndroidUtilities.runOnUIThread(new ChatActionCell$$ExternalSyntheticLambda13(r12));
            this.val$played[0] = true;
            return;
        L11:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarsIntroActivity$8, reason: invalid class name */
    public class AnonymousClass8 extends LinearLayout {
        private final Path clipPath;
        final /* synthetic */ Matrix val$backgroundMatrix;
        final /* synthetic */ Paint val$backgroundPaint;
        final /* synthetic */ RadialGradient val$gradient;
        final /* synthetic */ AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable val$symbolDrawable;

        public AnonymousClass8(Context r1, Matrix r2, RadialGradient r3, Paint r4, AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r5) {
            this.val$backgroundMatrix = r2;
            this.val$gradient = r3;
            this.val$backgroundPaint = r4;
            this.val$symbolDrawable = r5;
            super(r1);
            this.clipPath = new Path();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r9) {
            float r1 = AndroidUtilities.dp(10.0f);
            RectF r2 = AndroidUtilities.rectTmp;
            r2.set(0.0f, AndroidUtilities.dp(2.0f) + 1, getWidth(), getHeight() + r1);
            this.clipPath.rewind();
            this.clipPath.addRoundRect(r2, r1, r1, Path.Direction.CW);
            r9.save();
            r9.clipPath(this.clipPath);
            this.val$backgroundMatrix.reset();
            this.val$backgroundMatrix.postTranslate(getWidth() / 2.0f, AndroidUtilities.dp(100.0f));
            this.val$gradient.setLocalMatrix(this.val$backgroundMatrix);
            r9.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.val$backgroundPaint);
            r9.save();
            r9.translate(getWidth() / 2.0f, AndroidUtilities.dp(100.0f));
            StarGiftPatterns.drawPattern(r9, this.val$symbolDrawable, getWidth(), AndroidUtilities.dp(180.0f), 1.0f, 1.0f);
            r9.restore();
            super.dispatchDraw(r9);
            r9.restore();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.val$symbolDrawable.attach();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.val$symbolDrawable.detach();
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarsIntroActivity$9, reason: invalid class name */
    public class AnonymousClass9 extends PhotoViewer.EmptyPhotoViewerProvider {
        final /* synthetic */ long val$did;
        final /* synthetic */ BackupImageView val$imageView;
        final /* synthetic */ LinearLayout val$linearLayout;

        public AnonymousClass9(BackupImageView r1, LinearLayout r2, long r3) {
            this.val$imageView = r1;
            this.val$linearLayout = r2;
            this.val$did = r3;
        }

        @Override // org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean forceAllInGroup() {
            return true;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public PhotoViewer.PlaceProviderObject getPlaceForPhoto(MessageObject r2, TLRPC.FileLocation r3, int r4, boolean r5, boolean r6) {
            ImageReceiver r22 = this.val$imageView.getImageReceiver();
            int[] r32 = new int[2];
            this.val$imageView.getLocationInWindow(r32);
            PhotoViewer.PlaceProviderObject r42 = new PhotoViewer.PlaceProviderObject();
            r42.viewX = r32[0];
            r42.viewY = r32[1];
            r42.parentView = this.val$linearLayout;
            r42.animatingImageView = null;
            r42.imageReceiver = r22;
            if (r5 == false) goto L5;
            r42.thumb = r22.getBitmapSafe();
        L5:
            r42.radius = r22.getRoundRadius(true);
            r42.dialogId = this.val$did;
            r42.clipTopAddition = 0;
            r42.clipBottomAddition = 0;
            return r42;
        }
    }

    public static class ExpandView extends FrameLayout {
        public final ImageView arrowView;
        private int lastId;
        private boolean needDivider;
        public final AnimatedTextView textView;

        public static class Factory extends UItem.UItemFactory<ExpandView> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem asExpand(int r1, CharSequence r2, boolean r3) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.id = r1;
                r0.text = r2;
                r0.collapsed = r3;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                ((ExpandView) r1).set(r2, r3);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public ExpandView createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new ExpandView(r1, r5);
            }
        }

        public ExpandView(Context r9, Theme.ResourcesProvider r10) {
            super(r9);
            AnimatedTextView r102 = new AnimatedTextView(r9);
            this.textView = r102;
            r102.getDrawable().setHacks(true, true, true);
            r102.setTextSize(AndroidUtilities.dp(15.0f));
            addView(r102, LayoutHelper.createFrameRelatively(-1.0f, -1.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 22.0f, 0.0f, 58.0f, 0.0f));
            ImageView r103 = new ImageView(r9);
            this.arrowView = r103;
            r103.setScaleType(ImageView.ScaleType.CENTER);
            r103.setImageResource(R.drawable.arrow_more);
            addView(r103, LayoutHelper.createFrameRelatively(24.0f, 24.0f, 8388629, 0.0f, 0.0f, 17.0f, 0.0f));
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.lastId = Integer.MAX_VALUE;
        }

        @Override // android.view.View
        public void onDraw(Canvas r9) {
            super.onDraw(r9);
            if (this.needDivider == true) goto L5;
            return;
        L5:
            if (LocaleController.isRTL == false) goto L8;
            float r0 = 0.0f;
        L7:
            float r3 = r0;
            float r4 = getMeasuredHeight() - 1;
            int r02 = getMeasuredWidth();
            if (LocaleController.isRTL == false) goto L12;
            int r1 = AndroidUtilities.dp(22.0f);
        L13:
            r9.drawRect(r3, r4, r02 - r1, getMeasuredHeight(), Theme.dividerPaint);
            return;
        L12:
            r1 = 0;
            goto L13
        L8:
            r0 = AndroidUtilities.dp(22.0f);
            goto L7
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
        }

        public void set(String r5, boolean r6, boolean r7, boolean r8) {
            if (this.lastId != (-1)) goto L5;
            boolean r72 = true;
        L6:
            this.lastId = -1;
            this.textView.setText(r5, r72);
            int r52 = Theme.getColor(Theme.key_windowBackgroundWhiteBlueText2);
            this.textView.setTextColor(r52);
            this.arrowView.setColorFilter(new PorterDuffColorFilter(r52, PorterDuff.Mode.SRC_IN));
            ImageView r53 = this.arrowView;
            float r1 = 180.0f;
            if (r72 == false) goto L12;
            ViewPropertyAnimator r54 = r53.animate();
            if (r6 == false) goto L11;
            r1 = 0.0f;
        L11:
            r54.rotation(r1).setDuration(340).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        L15:
            this.needDivider = r8;
            setWillNotDraw(!r8);
            return;
        L12:
            if (r6 == false) goto L14;
            r1 = 0.0f;
        L14:
            r53.setRotation(r1);
            goto L15
        L5:
            r72 = false;
            goto L6
        }

        public void set(UItem r7, boolean r8) {
            int r0 = this.lastId;
            int r1 = r7.id;
            if (r0 != r1) goto L5;
            boolean r02 = true;
        L6:
            this.lastId = r1;
            this.textView.setText(r7.text, r02);
            if (r7.accent == false) goto L9;
            int r12 = Theme.key_windowBackgroundWhiteBlueText2;
        L10:
            int r13 = Theme.getColor(r12);
            this.textView.setTextColor(r13);
            this.arrowView.setColorFilter(new PorterDuffColorFilter(r13, PorterDuff.Mode.SRC_IN));
            ImageView r14 = this.arrowView;
            float r3 = 180.0f;
            if (r02 == false) goto L17;
            ViewPropertyAnimator r03 = r14.animate();
            if (r7.collapsed == false) goto L15;
            r3 = 0.0f;
        L15:
            r03.rotation(r3).setDuration(340).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        L20:
            this.needDivider = r8;
            setWillNotDraw(!r8);
            return;
        L17:
            if (r7.collapsed == false) goto L19;
            r3 = 0.0f;
        L19:
            r14.setRotation(r3);
            goto L20
        L9:
            r12 = Theme.key_windowBackgroundWhiteBlackText;
            goto L10
        L5:
            r02 = false;
            goto L6
        }
    }

    public static class GiftStarsSheet extends BottomSheetWithRecyclerListView implements NotificationCenter.NotificationCenterDelegate {
        private final int BUTTON_EXPAND;
        private UniversalAdapter adapter;
        private boolean expanded;
        private final FireworksOverlay fireworksOverlay;
        private final FrameLayout footerView;
        private final HeaderView headerView;
        private final TLRPC.User user;
        private final Runnable whenPurchased;

        public static class HeaderView extends LinearLayout {
            public final BackupImageView avatarImageView;
            public final StarParticlesView particlesView;
            public final LinkSpanDrawable.LinksTextView subtitleView;
            public final TextView titleView;
            private final FrameLayout topView;

            public HeaderView(Context r12, int r13, Theme.ResourcesProvider r14) {
                super(r12);
                setOrientation(1);
                FrameLayout r0 = new FrameLayout(r12);
                this.topView = r0;
                r0.setClipChildren(false);
                r0.setClipToPadding(false);
                StarParticlesView r1 = StarsIntroActivity.makeParticlesView(r12, 70, 0);
                this.particlesView = r1;
                r0.addView(r1, LayoutHelper.createFrame(-1, -1.0f));
                BackupImageView r15 = new BackupImageView(r12);
                this.avatarImageView = r15;
                r15.setRoundRadius(AndroidUtilities.dp(50.0f));
                r0.addView(r15, LayoutHelper.createFrame(100, 100.0f, 17, 0.0f, 32.0f, 0.0f, 24.0f));
                addView(r0, LayoutHelper.createFrame(-1, 150.0f));
                TextView r02 = new TextView(r12);
                this.titleView = r02;
                r02.setTextSize(1, 20.0f);
                r02.setTypeface(AndroidUtilities.bold());
                int r16 = Theme.key_dialogTextBlack;
                r02.setTextColor(Theme.getColor(r16, r14));
                r02.setGravity(17);
                addView(r02, LayoutHelper.createLinear(-2, -2, 1, 0, 2, 0, 0));
                LinkSpanDrawable.LinksTextView r03 = new LinkSpanDrawable.LinksTextView(r12, r14);
                this.subtitleView = r03;
                r03.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r14));
                r03.setTextSize(1, 14.0f);
                r03.setTextColor(Theme.getColor(r16, r14));
                r03.setGravity(17);
                addView(r03, LayoutHelper.createLinear(-2, -2, 1, 0, 9, 0, 18));
            }
        }

        public static /* synthetic */ void $r8$lambda$JQHHUHBXKIcBttveLdyZM9htdC8(GiftStarsSheet r0) {
            r0.lambda$new$1();
        }

        public static /* synthetic */ void $r8$lambda$KGb7ensm6asp7a2svjo9VQbKdjs(GiftStarsSheet r0, UItem r1, long r2, Boolean r4, String r5) {
            r0.lambda$onItemClick$4(r1, r2, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$WJi_kBS_c_jgwa6WesPWSvJDBDo(GiftStarsSheet r0, View r1, int r2) {
            r0.lambda$new$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$XJR9mtiHZI9jRwVOKA2h0N0l6G0(long r1) {
            BaseFragment r0 = LaunchActivity.getSafeLastFragment();
            if (r0 == null) goto L6;
            r0.presentFragment(ChatActivity.of(r1));
            return;
        }

        /* renamed from: $r8$lambda$bUmsqE8-fkQz7-6Mlm5bbiEKF0w, reason: not valid java name */
        public static /* synthetic */ void m19196$r8$lambda$bUmsqE8fkQz76Mlm5bbiEKF0w(GiftStarsSheet r0) {
            r0.lambda$new$2();
        }

        public GiftStarsSheet(Context r8, Theme.ResourcesProvider r9, TLRPC.User r10, Runnable r11) {
            super(r8, null, false, false, false, r9);
            this.BUTTON_EXPAND = -1;
            this.user = r10;
            this.whenPurchased = r11;
            this.topPadding = 0.2f;
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starGiftOptionsLoaded);
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starBalanceUpdated);
            fixNavigationBar();
            RecyclerListView r82 = this.recyclerListView;
            int r92 = this.backgroundPaddingLeft;
            r82.setPadding(r92, 0, r92, 0);
            this.recyclerListView.setOnItemClickListener(new StarsIntroActivity$GiftStarsSheet$$ExternalSyntheticLambda0(this));
            DefaultItemAnimator r83 = new DefaultItemAnimator();
            r83.setSupportsChangeAnimations(false);
            r83.setDelayAnimations(false);
            r83.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            r83.setDurations(350);
            this.recyclerListView.setItemAnimator(r83);
            setBackgroundColor(Theme.getColor(Theme.key_dialogBackgroundGray, r9));
            HeaderView r84 = new HeaderView(r8, this.currentAccount, r9);
            this.headerView = r84;
            r84.titleView.setText(LocaleController.getString(R.string.GiftStarsTitle));
            r84.subtitleView.setText(TextUtils.concat(new CharSequence[]{AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GiftStarsSubtitle, new Object[]{UserObject.getForcedFirstName(r10)})), " ", AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.GiftStarsSubtitleLinkName).replace(' ', Typography.nbsp), new StarsIntroActivity$GiftStarsSheet$$ExternalSyntheticLambda1(this)), true)}));
            LinkSpanDrawable.LinksTextView r93 = r84.subtitleView;
            r93.setMaxWidth(HintView2.cutInFancyHalf(r93.getText(), r84.subtitleView.getPaint()) + 1);
            this.actionBar.setTitle(getTitle());
            AvatarDrawable r94 = new AvatarDrawable();
            r94.setInfo(r10);
            r84.avatarImageView.setForUserOrChat(r10, r94);
            FrameLayout r85 = new FrameLayout(r8);
            this.footerView = r85;
            LinkSpanDrawable.LinksTextView r95 = new LinkSpanDrawable.LinksTextView(r8, r9);
            r85.setPadding(0, AndroidUtilities.dp(11.0f), 0, AndroidUtilities.dp(11.0f));
            r95.setTextSize(1, 12.0f);
            r95.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4, r9));
            r95.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r9));
            r95.setText(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.StarsTOS), new StarsIntroActivity$GiftStarsSheet$$ExternalSyntheticLambda2(this)));
            r95.setGravity(17);
            r95.setMaxWidth(HintView2.cutInFancyHalf(r95.getText(), r95.getPaint()));
            r85.addView(r95, LayoutHelper.createFrame(-2, -1, 17));
            r85.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, r9));
            FireworksOverlay r86 = new FireworksOverlay(getContext());
            this.fireworksOverlay = r86;
            this.containerView.addView(r86, LayoutHelper.createFrame(-1, -1.0f));
            UniversalAdapter r7 = this.adapter;
            if (r7 == null) goto L6;
            r7.update(false);
            return;
        }

        private /* synthetic */ void lambda$new$0(View r1, int r2) {
            UniversalAdapter r12 = this.adapter;
            if (r12 == null) goto L10;
            UItem r13 = r12.getItem(r2 - 1);
            if (r13 != null) goto L8;
            return;
        L8:
            onItemClick(r13, this.adapter);
            return;
        }

        private /* synthetic */ void lambda$new$1() {
            StarAppsSheet r0 = new StarAppsSheet(getContext());
            if (AndroidUtilities.isTablet() == false) goto L5;
        L9:
            r0.show();
            return;
        L5:
            if (AndroidUtilities.hasDialogOnTop(this.attachedFragment) == true) goto L9;
            BaseFragment r2 = this.attachedFragment;
            if (r2 == null) goto L9;
            r0.makeAttached(r2);
            goto L9
        }

        private /* synthetic */ void lambda$new$2() {
            Browser.openUrl(getContext(), LocaleController.getString(R.string.StarsTOSLink));
        }

        private /* synthetic */ void lambda$onItemClick$4(UItem r9, long r10, Boolean r12, String r13) {
            if (getContext() != null) goto L6;
            return;
        L6:
            if (r12.booleanValue() == true) goto L8;
            if (r13 != null) goto L8;
        L11:
            dismiss();
            BaseFragment r0 = LaunchActivity.getSafeLastFragment();
            FireworksOverlay r1 = LaunchActivity.instance.getFireworksOverlay();
            if (r0 != null) goto L15;
            return;
        L15:
            if (r12.booleanValue() == false) goto L21;
            BulletinFactory.of(r0).createSimpleBulletin(R.raw.stars_send, LocaleController.getString(R.string.StarsGiftSentPopup), AndroidUtilities.replaceTags(LocaleController.formatPluralString("StarsGiftSentPopupInfo", (int) r9.longValue, new Object[]{UserObject.getForcedFirstName(this.user)})), LocaleController.getString(R.string.ViewInChat), new StarsIntroActivity$GiftStarsSheet$$ExternalSyntheticLambda5(r10)).setDuration(5000).show(true);
            if (r1 == null) goto L19;
            r1.start(true);
        L19:
            StarsController.getInstance(this.currentAccount).invalidateTransactions(true);
            return;
        L21:
            if (r13 == null) goto L24;
            BulletinFactory.of(r0).createSimpleBulletin(R.raw.error, LocaleController.formatString(R.string.UnknownErrorCode, new Object[]{r13})).show();
            return;
        L24:
            return;
        L8:
            Runnable r02 = this.whenPurchased;
            if (r02 == null) goto L11;
            r02.run();
            goto L11
        }

        @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
        public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
            UniversalAdapter r0 = new UniversalAdapter(this.recyclerListView, getContext(), this.currentAccount, 0, true, new StarsIntroActivity$GiftStarsSheet$$ExternalSyntheticLambda3(this), this.resourcesProvider);
            this.adapter = r0;
            return r0;
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r1, int r2, Object... r3) {
            if (r1 != NotificationCenter.starGiftOptionsLoaded) goto L5;
        L6:
            UniversalAdapter r0 = this.adapter;
            if (r0 == null) goto L11;
            r0.update(true);
            return;
        L11:
            return;
        L5:
            if (r1 == NotificationCenter.starBalanceUpdated) goto L6;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
        public void dismiss() {
            super.dismiss();
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet
        public void dismissInternal() {
            super.dismissInternal();
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starGiftOptionsLoaded);
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starBalanceUpdated);
        }

        public void fillItems(ArrayList<UItem> r7, UniversalAdapter r8) {
            r7.add(UItem.asCustom(this.headerView));
            r7.add(UItem.asHeader(LocaleController.getString(R.string.TelegramStarsChoose)));
            ArrayList<TL_stars.TL_starsGiftOption> r82 = StarsController.getInstance(this.currentAccount).getGiftOptions();
            if (r82 != null) goto L5;
        L23:
            r7.add(UItem.asFlicker(31));
            r7.add(UItem.asFlicker(31));
            r7.add(UItem.asFlicker(31));
        L24:
            r7.add(UItem.asCustom(this.footerView));
            return;
        L5:
            if (r82.isEmpty() == true) goto L23;
            int r0 = 0;
            int r2 = 0;
            int r3 = 1;
        L8:
            if (r0 >= r82.size()) goto L16;
            TL_stars.TL_starsGiftOption r4 = r82.get(r0);
            if (this.expanded == false) goto L12;
        L14:
            r7.add(StarTierView.Factory.asStarTier(r0, r3, r4));
            r3 = r3 + 1;
        L15:
            r0 = r0 + 1;
            goto L8
        L12:
            if (r4.extended == false) goto L14;
            r2 = r2 + 1;
            goto L15
        L16:
            boolean r83 = this.expanded;
            if (r83 == true) goto L24;
            if (r2 <= 0) goto L24;
            if (r83 == false) goto L21;
            int r84 = R.string.NotifyLessOptions;
        L22:
            r7.add(ExpandView.Factory.asExpand(-1, LocaleController.getString(r84), !this.expanded).accent());
            goto L24
        L21:
            r84 = R.string.NotifyMoreOptions;
            goto L22
        }

        @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
        public CharSequence getTitle() {
            HeaderView r0 = this.headerView;
            if (r0 != null) goto L7;
            return null;
        L7:
            return r0.titleView.getText();
        }

        public void onItemClick(UItem r7, UniversalAdapter r8) {
            if (r7.id != (-1)) goto L7;
            this.expanded = !this.expanded;
            r8.update(true);
            this.recyclerListView.smoothScrollBy(0, AndroidUtilities.dp(200.0f), CubicBezierInterpolator.EASE_OUT);
            return;
        L7:
            if (r7.instanceOf(StarTierView.Factory.class) == true) goto L9;
            return;
        L9:
            if ((r7.object instanceof TL_stars.TL_starsGiftOption) == false) goto L19;
            Activity r82 = AndroidUtilities.findActivity(getContext());
            if (r82 != null) goto L13;
            r82 = LaunchActivity.instance;
        L13:
            Activity r1 = r82;
            if (r1 == null) goto L20;
            long r3 = this.user.id;
            StarsController.getInstance(this.currentAccount).buyGift(r1, (TL_stars.TL_starsGiftOption) r7.object, r3, new StarsIntroActivity$GiftStarsSheet$$ExternalSyntheticLambda4(this, r7, r3));
            return;
        L20:
            return;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
        public void show() {
            BaseFragment r0 = LaunchActivity.getLastFragment();
            if ((r0 instanceof ChatActivity) == false) goto L9;
            ChatActivity r02 = (ChatActivity) r0;
            if (r02.isKeyboardVisible() == false) goto L9;
            if (r02.getChatActivityEnterView() == null) goto L9;
            r02.getChatActivityEnterView().closeKeyboard();
        L9:
            super.show();
        }
    }

    public class NestedFrameLayout extends GradientHeaderActivity.ContentView implements NestedScrollingParent3 {
        private NestedScrollingParentHelper nestedScrollingParentHelper;
        final /* synthetic */ StarsIntroActivity this$0;

        public static /* synthetic */ void $r8$lambda$wHdLAz8F0bbdX9L3fAE2boFMDSM(NestedFrameLayout r0) {
            r0.lambda$onNestedScroll$0();
        }

        public NestedFrameLayout(StarsIntroActivity r1, Context r2) {
            this.this$0 = r1;
            super(r1, r2);
            this.nestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        }

        private /* synthetic */ void lambda$onNestedScroll$0() {
            RecyclerListView r1 = StarsIntroActivity.m19194$$Nest$fgettransactionsLayout(this.this$0).getCurrentListView();     // Catch: Throwable -> L8
            if (r1 != null) goto L5;
            return;
        L5:
            if (r1.getAdapter() == null) goto L12;
            r1.getAdapter().notifyDataSetChanged();     // Catch: Throwable -> L8
            return;
        L12:
            return;
        }

        @Override // org.telegram.ui.Components.NestedSizeNotifierLayout, android.view.ViewGroup, android.view.ViewParent
        public boolean onNestedPreFling(View r1, float r2, float r3) {
            return super.onNestedPreFling(r1, r2, r3);
        }

        @Override // org.telegram.ui.Components.NestedSizeNotifierLayout, androidx.core.view.NestedScrollingParent2
        public void onNestedPreScroll(View r5, int r6, int r7, int[] r8, int r9) {
            if (r5 == StarsIntroActivity.access$500(this.this$0)) goto L5;
            return;
        L5:
            if (StarsIntroActivity.m19194$$Nest$fgettransactionsLayout(this.this$0).isAttachedToWindow() == false) goto L47;
            boolean r52 = StarsIntroActivity.access$600(this.this$0).isSearchFieldVisible();
            int r62 = (((View) StarsIntroActivity.m19194$$Nest$fgettransactionsLayout(this.this$0).getParent()).getTop() - AndroidUtilities.statusBarHeight) - ActionBar.getCurrentActionBarHeight();
            int r92 = ((View) StarsIntroActivity.m19194$$Nest$fgettransactionsLayout(this.this$0).getParent()).getBottom();
            boolean r0 = false;
            if (r7 < 0) goto L9;
            if (r52 == false) goto L38;
            RecyclerListView r4 = StarsIntroActivity.m19194$$Nest$fgettransactionsLayout(this.this$0).getCurrentListView();
            r8[1] = r7;
            if (r62 <= 0) goto L33;
            r8[1] = 0;
        L33:
            if (r4 == null) goto L49;
            int r53 = r8[1];
            if (r53 <= 0) goto L50;
            r4.scrollBy(0, r53);
            return;
        L50:
            return;
        L49:
            return;
        L38:
            if (r7 <= 0) goto L51;
            RecyclerListView r54 = StarsIntroActivity.m19194$$Nest$fgettransactionsLayout(this.this$0).getCurrentListView();
            if (((StarsIntroActivity.access$900(this.this$0).getHeight() - StarsIntroActivity.access$1000(this.this$0).getPaddingBottom()) - r92) < 0) goto L52;
            if (r54 != null) goto L43;
            return;
        L43:
            if (r54.canScrollVertically(1) == true) goto L54;
            r8[1] = r7;
            StarsIntroActivity.access$1100(this.this$0).stopScroll();
            return;
        L54:
            return;
        L52:
            return;
        L51:
            return;
        L9:
            if (((StarsIntroActivity.access$700(this.this$0).getHeight() - StarsIntroActivity.access$800(this.this$0).getPaddingBottom()) - r92) < 0) goto L22;
            RecyclerListView r42 = StarsIntroActivity.m19194$$Nest$fgettransactionsLayout(this.this$0).getCurrentListView();
            int r93 = ((LinearLayoutManager) r42.getLayoutManager()).findFirstVisibleItemPosition();
            int r2 = -1;
            if (r93 == (-1)) goto L22;
            RecyclerView.ViewHolder r3 = r42.findViewHolderForAdapterPosition(r93);
            if (r3 == null) goto L15;
            r2 = r3.itemView.getTop();
        L15:
            int r32 = r42.getPaddingTop();
            if (r2 != r32) goto L18;
            if (r93 == 0) goto L22;
        L18:
            if (r93 == 0) goto L20;
            int r94 = r7;
        L21:
            r8[1] = r94;
            r42.scrollBy(0, r7);
            r0 = true;
            goto L22
        L20:
            r94 = Math.max(r7, r2 - r32);
        L22:
            if (r52 == false) goto L48;
            if (r0 == true) goto L27;
            if (r62 >= 0) goto L27;
            r8[1] = r7 - Math.max(r62, r7);
            return;
        L27:
            r8[1] = r7;
            return;
        L48:
            return;
        }

        @Override // org.telegram.ui.Components.NestedSizeNotifierLayout, androidx.core.view.NestedScrollingParent2
        public void onNestedScroll(View r1, int r2, int r3, int r4, int r5, int r6) {
        }

        @Override // org.telegram.ui.Components.NestedSizeNotifierLayout, androidx.core.view.NestedScrollingParent3
        public void onNestedScroll(View r1, int r2, int r3, int r4, int r5, int r6, int[] r7) {
        L10:
            th = move-exception;
            FileLog.e(th);
            AndroidUtilities.runOnUIThread(new StarsIntroActivity$NestedFrameLayout$$ExternalSyntheticLambda0(this));
            return;
        L3:
            if (r1 == StarsIntroActivity.access$200(this.this$0)) goto L5;
            return;
        L5:
            if (StarsIntroActivity.m19194$$Nest$fgettransactionsLayout(this.this$0).isAttachedToWindow() == false) goto L17;
            RecyclerListView r12 = StarsIntroActivity.m19194$$Nest$fgettransactionsLayout(this.this$0).getCurrentListView();     // Catch: Throwable -> L10
            int r22 = ((View) StarsIntroActivity.m19194$$Nest$fgettransactionsLayout(this.this$0).getParent()).getBottom();     // Catch: Throwable -> L10
            if (((StarsIntroActivity.access$300(this.this$0).getHeight() - StarsIntroActivity.access$400(this.this$0).getPaddingBottom()) - r22) < 0) goto L18;
            r7[1] = r5;     // Catch: Throwable -> L10
            r12.scrollBy(0, r5);     // Catch: Throwable -> L10
            return;
        L18:
            return;
        }

        @Override // org.telegram.ui.Components.NestedSizeNotifierLayout, androidx.core.view.NestedScrollingParent2
        public void onNestedScrollAccepted(View r1, View r2, int r3, int r4) {
            this.nestedScrollingParentHelper.onNestedScrollAccepted(r1, r2, r3);
        }

        @Override // org.telegram.ui.Components.NestedSizeNotifierLayout, androidx.core.view.NestedScrollingParent2
        public boolean onStartNestedScroll(View r1, View r2, int r3, int r4) {
            if (r3 != 2) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.Components.NestedSizeNotifierLayout, android.view.ViewGroup, android.view.ViewParent
        public void onStopNestedScroll(View r1) {
        }

        @Override // org.telegram.ui.Components.NestedSizeNotifierLayout, androidx.core.view.NestedScrollingParent2
        public void onStopNestedScroll(View r1, int r2) {
            this.nestedScrollingParentHelper.onStopNestedScroll(r1);
        }
    }

    public static class StarTierView extends FrameLayout {
        private final AnimatedFloat animatedStarsCount;
        private SpannableString loading;
        private boolean needDivider;
        private final Theme.ResourcesProvider resourcesProvider;
        private final Drawable starDrawable;
        private final Drawable starDrawableOutline;
        private int starsCount;
        private final TextView textView;
        private final AnimatedTextView textView2;

        public static class Factory extends UItem.UItemFactory<StarTierView> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem asStarTier(int r3, int r4, TL_stars.TL_starsGiftOption r5) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.id = r3;
                r0.intValue = r4;
                long r32 = r5.stars;
                r0.longValue = r32;
                r0.text = LocaleController.formatPluralStringSpaced("StarsCount", (int) r32);
                if (r5.loadingStorePrice == false) goto L5;
                String r33 = null;
            L6:
                r0.subtext = r33;
                r0.object = r5;
                return r0;
            L5:
                r33 = BillingController.getInstance().formatCurrency(r5.amount, r5.currency);
                goto L6
            }

            public static UItem asStarTier(int r3, int r4, TL_stars.TL_starsTopupOption r5) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.id = r3;
                r0.intValue = r4;
                long r32 = r5.stars;
                r0.longValue = r32;
                r0.text = LocaleController.formatPluralStringSpaced("StarsCount", (int) r32);
                if (r5.loadingStorePrice == false) goto L5;
                String r33 = null;
            L6:
                r0.subtext = r33;
                r0.object = r5;
                return r0;
            L5:
                r33 = BillingController.getInstance().formatCurrency(r5.amount, r5.currency);
                goto L6
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                ((StarTierView) r1).set(r2.intValue, r2.text, r2.subtext, r3);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean contentsEquals(UItem r2, UItem r3) {
                if (r2.intValue == r3.intValue) goto L5;
                return false;
            L5:
                if (r2.id == r3.id) goto L7;
                return false;
            L7:
                if (TextUtils.equals(r2.subtext, r3.subtext) == false) goto L13;
                return true;
            L13:
                return false;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public StarTierView createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new StarTierView(r1, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean equals(UItem r1, UItem r2) {
                if (r1.id != r2.id) goto L6;
                return true;
            L6:
                return false;
            }
        }

        public StarTierView(Context r11, Theme.ResourcesProvider r12) {
            super(r11);
            this.animatedStarsCount = new AnimatedFloat(this, 0, 500, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.resourcesProvider = r12;
            Drawable r10 = r11.getResources().getDrawable(R.drawable.star_small_outline).mutate();
            this.starDrawableOutline = r10;
            r10.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogBackground, r12), PorterDuff.Mode.SRC_IN));
            this.starDrawable = r11.getResources().getDrawable(R.drawable.star_small_inner).mutate();
            setWillNotDraw(false);
            TextView r102 = new TextView(r11);
            this.textView = r102;
            r102.setTypeface(AndroidUtilities.bold());
            r102.setTextSize(1, 15.0f);
            r102.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r12));
            addView(r102, LayoutHelper.createFrameRelatively(-2.0f, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 48.0f, 0.0f, 0.0f, 0.0f));
            AnimatedTextView r103 = new AnimatedTextView(r11);
            this.textView2 = r103;
            r103.setTextSize(AndroidUtilities.dp(15.0f));
            r103.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r12));
            if (LocaleController.isRTL == false) goto L5;
            int r112 = 3;
        L6:
            r103.setGravity(r112);
            addView(r103, LayoutHelper.createFrameRelatively(-2.0f, 21.0f, 8388629, 0.0f, 0.0f, 19.0f, 0.0f));
            return;
        L5:
            r112 = 5;
            goto L6
        }

        @Override // android.view.View
        public void onDraw(Canvas r18) {
            super.onDraw(r18);
            float r2 = this.animatedStarsCount.set(this.starsCount);
            if (LocaleController.isRTL == false) goto L5;
            float r3 = -1.0f;
        L6:
            float r6 = AndroidUtilities.dp(24.0f);
            float r5 = AndroidUtilities.dp(24.0f);
            float r7 = AndroidUtilities.dp(2.5f);
            if (LocaleController.isRTL == false) goto L9;
            float r8 = (getWidth() - AndroidUtilities.dp(19.0f)) - r6;
        L10:
            int r9 = (int) Math.ceil(r2);
        L11:
            r9 = r9 - 1;
            float r10 = 0.0f;
            if (r9 < 0) goto L15;
            float r102 = Utilities.clamp(r2 - r9, 1.0f, 0.0f);
            float r11 = ((((r9 - 1) - (1.0f - r102)) * r7) * r3) + r8;
            float r12 = (getMeasuredHeight() - r5) / 2.0f;
            int r14 = (int) r11;
            int r15 = (int) r12;
            int r112 = (int) (r11 + r6);
            int r122 = (int) (r12 + r5);
            this.starDrawableOutline.setBounds(r14, r15, r112, r122);
            int r103 = (int) (r102 * 255.0f);
            this.starDrawableOutline.setAlpha(r103);
            this.starDrawableOutline.draw(r18);
            this.starDrawable.setBounds(r14, r15, r112, r122);
            this.starDrawable.setAlpha(r103);
            this.starDrawable.draw(r18);
            goto L11
        L15:
            if (this.needDivider == false) goto L33;
            Theme.ResourcesProvider r22 = this.resourcesProvider;
            if (r22 == null) goto L19;
            Paint r23 = r22.getPaint("paintDivider");
        L20:
            if (r23 != null) goto L22;
            r23 = Theme.dividerPaint;
        L22:
            Paint r52 = r23;
            if (LocaleController.isRTL == true) goto L26;
            r10 = AndroidUtilities.dp(22.0f);
        L26:
            float r24 = getMeasuredHeight() - 1;
            int r4 = getMeasuredWidth();
            if (LocaleController.isRTL == false) goto L29;
            int r32 = AndroidUtilities.dp(22.0f);
        L30:
            r18.drawRect(r10, r24, r4 - r32, getMeasuredHeight(), r52);
            return;
        L29:
            r32 = 0;
            goto L30
        L19:
            r23 = null;
            goto L20
        L33:
            return;
        L9:
            r8 = AndroidUtilities.dp(19.0f);
            goto L10
        L5:
            r3 = 1.0f;
            goto L6
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
        }

        public void set(int r6, CharSequence r7, CharSequence r8, boolean r9) {
            boolean r0 = TextUtils.equals(this.textView.getText(), r7);
            this.starsCount = r6;
            if (r0 == true) goto L5;
            this.animatedStarsCount.set(r6, true);
        L5:
            this.textView.setText(r7);
            if (r8 == null) goto L8;
        L11:
            this.textView2.setText(r8);
            if (LocaleController.isRTL == false) goto L14;
            float r72 = -1.0f;
        L15:
            TextView r82 = this.textView;
            if (r0 == false) goto L18;
            r82.animate().translationX((r72 * (r6 - 1)) * AndroidUtilities.dp(2.66f)).setDuration(320).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
        L19:
            this.needDivider = r9;
            invalidate();
            return;
        L18:
            r82.setTranslationX((r72 * (r6 - 1)) * AndroidUtilities.dp(2.66f));
            goto L19
        L14:
            r72 = 1.0f;
            goto L15
        L8:
            if (this.loading != null) goto L10;
            SpannableString r73 = new SpannableString("x");
            this.loading = r73;
            r73.setSpan(new LoadingSpan(this.textView2, AndroidUtilities.dp(55.0f)), 0, this.loading.length(), 33);
        L10:
            r8 = this.loading;
            goto L11
        }
    }

    public static class StarsBalanceView extends LinearLayout implements NotificationCenter.NotificationCenterDelegate {
        private final AnimatedTextView amountTextView;
        private ValueAnimator bounceAnimator;
        private final int currentAccount;
        private long dialogId;
        private final TextView headerTextView;
        public long lastBalance;
        private SpannableString loadingString;
        private final ColoredImageSpan[] ref;
        private final ColoredImageSpan[] refTon;
        private final Theme.ResourcesProvider resourcesProvider;
        private boolean withTon;

        /* renamed from: org.telegram.ui.Stars.StarsIntroActivity$StarsBalanceView$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatedTextView {
            final /* synthetic */ StarsBalanceView this$0;
            final /* synthetic */ Drawable val$starDrawable;

            public AnonymousClass1(StarsBalanceView r1, Context r2, Drawable r3) {
                this.this$0 = r1;
                this.val$starDrawable = r3;
                super(r2);
            }

            @Override // android.view.View
            public void dispatchDraw(Canvas r7) {
                if (StarsBalanceView.m19198$$Nest$fgetwithTon(this.this$0) == true) goto L5;
                int r0 = (int) ((getMeasuredWidth() - getDrawable().getCurrentWidth()) - AndroidUtilities.dp(20.0f));
                this.val$starDrawable.setBounds(r0, (getMeasuredHeight() - AndroidUtilities.dp(17.0f)) / 2, AndroidUtilities.dp(17.0f) + r0, (getMeasuredHeight() + AndroidUtilities.dp(17.0f)) / 2);
                this.val$starDrawable.draw(r7);
            L5:
                super.dispatchDraw(r7);
            }
        }

        /* renamed from: org.telegram.ui.Stars.StarsIntroActivity$StarsBalanceView$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ StarsBalanceView this$0;

            public AnonymousClass2(StarsBalanceView r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                StarsBalanceView.m19197$$Nest$fgetamountTextView(this.this$0).setScaleX(1.0f);
                StarsBalanceView.m19197$$Nest$fgetamountTextView(this.this$0).setScaleY(1.0f);
            }
        }

        public static /* synthetic */ void $r8$lambda$a8z6ulwH2VQyIOpYfQxF6pQUSbo(StarsBalanceView r0, ValueAnimator r1) {
            r0.lambda$bounce$0(r1);
        }

        /* renamed from: -$$Nest$fgetamountTextView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedTextView m19197$$Nest$fgetamountTextView(StarsBalanceView r0) {
            return r0.amountTextView;
        }

        /* renamed from: -$$Nest$fgetwithTon, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m19198$$Nest$fgetwithTon(StarsBalanceView r0) {
            return r0.withTon;
        }

        public StarsBalanceView(Context r13, int r14, Theme.ResourcesProvider r15) {
            super(r13);
            this.lastBalance = -1;
            this.ref = new ColoredImageSpan[1];
            this.refTon = new ColoredImageSpan[1];
            this.resourcesProvider = r15;
            this.currentAccount = r14;
            this.dialogId = UserConfig.getInstance(r14).getClientUserId();
            setOrientation(1);
            setGravity(21);
            TextView r142 = new TextView(r13);
            this.headerTextView = r142;
            int r1 = Theme.key_windowBackgroundWhiteBlackText;
            r142.setTextColor(Theme.getColor(r1, r15));
            r142.setTextSize(1, 13.0f);
            r142.setText(LocaleController.getString(R.string.StarsBalance));
            r142.setGravity(5);
            r142.setTypeface(AndroidUtilities.bold());
            addView(r142, LayoutHelper.createLinear(-2, -2, 5));
            AnonymousClass1 r4 = new AnonymousClass1(this, r13, r13.getResources().getDrawable(R.drawable.star_small_inner).mutate());
            this.amountTextView = r4;
            r4.adaptWidth = true;
            r4.getDrawable().setHacks(false, true, true);
            r4.setTypeface(AndroidUtilities.bold());
            r4.setTextColor(Theme.getColor(r1, r15));
            r4.setTextSize(AndroidUtilities.dp(13.0f));
            r4.setGravity(5);
            r4.setPadding(AndroidUtilities.dp(19.0f), 0, 0, 0);
            addView(r4, LayoutHelper.createLinear(-2, 20, 5, 0, -2, 0, 0));
            updateBalance(false);
            setPadding(AndroidUtilities.dp(15.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(15.0f), AndroidUtilities.dp(4.0f));
        }

        private /* synthetic */ void lambda$bounce$0(ValueAnimator r2) {
            float r22 = ((Float) r2.getAnimatedValue()).floatValue();
            this.amountTextView.setScaleX(r22);
            this.amountTextView.setScaleY(r22);
        }

        public void bounce() {
            ValueAnimator r0 = this.bounceAnimator;
            if (r0 == null) goto L5;
            r0.cancel();
        L5:
            ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.9f, 1.0f});
            this.bounceAnimator = r02;
            r02.addUpdateListener(new StarsIntroActivity$StarsBalanceView$$ExternalSyntheticLambda0(this));
            this.bounceAnimator.addListener(new AnonymousClass2(this));
            this.bounceAnimator.setDuration(320);
            this.bounceAnimator.setInterpolator(new OvershootInterpolator());
            this.bounceAnimator.start();
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r4, int r5, Object... r6) {
            if (r4 != NotificationCenter.starBalanceUpdated) goto L7;
            updateBalance(true);
            return;
        L7:
            if (r4 == NotificationCenter.botStarsUpdated) goto L9;
            return;
        L9:
            if (((Long) r6[0]).longValue() != this.dialogId) goto L13;
            updateBalance(true);
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            updateBalance(false);
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starBalanceUpdated);
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.botStarsUpdated);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starBalanceUpdated);
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.botStarsUpdated);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(ActionBar.getCurrentActionBarHeight(), TLObject.FLAG_30));
        }

        public void setDialogId(long r3) {
            if (this.dialogId == r3) goto L6;
            this.dialogId = r3;
            updateBalance(true);
            return;
        }

        public void updateBalance(boolean r12) {
            StarsController r0 = StarsController.getInstance(this.currentAccount);
            if (this.withTon == false) goto L5;
            StarsController r1 = StarsController.getTonInstance(this.currentAccount);
        L6:
            long r3 = 0;
            AmountUtils$Amount r2 = AmountUtils$Amount.fromNano(0, AmountUtils$Currency.TON);
            this.amountTextView.cancelAnimation();
            if (this.dialogId != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L11;
            boolean r32 = !r0.balanceAvailable();
            long r4 = r0.getBalance().amount;
            if (r1 == null) goto L23;
            r32 = r32 | (!r1.balanceAvailable());
            r2 = r1.getBalanceAmount();
        L23:
            long r02 = this.lastBalance;
            if (r4 > r02) goto L26;
        L28:
            if (r32 == false) goto L35;
            if (this.loadingString != null) goto L32;
            SpannableString r03 = new SpannableString("x");
            this.loadingString = r03;
            r03.setSpan(new LoadingSpan(this.amountTextView, AndroidUtilities.dp(48.0f)), 0, this.loadingString.length(), 33);
        L32:
            this.amountTextView.setText(this.loadingString, r12);
            this.lastBalance = -1;
            return;
        L35:
            if (this.withTon == false) goto L43;
            SpannableStringBuilder r122 = new SpannableStringBuilder();
            if (r2.isZero() == true) goto L42;
            r122.append(StarsIntroActivity.replaceStarsWithPlain(true, "⭐️" + r2.asFormatString(), 0.62f, this.refTon));
            ColoredImageSpan r13 = this.refTon[0];
            if (r13 == null) goto L41;
            r13.setColorKey(Theme.key_telegram_color_text);
        L41:
            r122.append("  ");
        L42:
            r122.append(StarsIntroActivity.replaceStarsWithPlain("⭐️" + LocaleController.formatNumber(r4, ' '), 0.62f, this.ref));
            this.amountTextView.setText(r122);
        L44:
            this.lastBalance = r4;
            return;
        L43:
            this.amountTextView.setText(LocaleController.formatNumber(r4, ' '));
            goto L44
        L26:
            if (r02 == (-1)) goto L28;
            bounce();
            goto L28
        L11:
            TLRPC.TL_payments_starsRevenueStats r04 = BotStarsController.getInstance(this.currentAccount).getStarsRevenueStats(this.dialogId);
            if (r04 != null) goto L14;
        L17:
            boolean r14 = true;
        L18:
            if (r04 == null) goto L22;
            TLRPC.TL_starsRevenueStatus r05 = r04.status;
            if (r05 == null) goto L22;
            r3 = r05.current_balance.amount;
        L22:
            r4 = r3;
            r32 = r14;
            goto L23
        L14:
            if (r04.status == null) goto L17;
            r14 = false;
            goto L18
        L5:
            r1 = null;
            goto L6
        }

        public void withTon() {
            this.withTon = true;
        }
    }

    public static class StarsNeededSheet extends BottomSheetWithRecyclerListView implements NotificationCenter.NotificationCenterDelegate {
        private final int BUTTON_EXPAND;
        private UniversalAdapter adapter;
        private final boolean canBuy;
        private boolean expanded;
        private final FireworksOverlay fireworksOverlay;
        private final FrameLayout footerView;
        private final HeaderView headerView;
        private final TLRPC.InputPeer purposePeer;
        private final long starsNeeded;
        private Runnable whenPurchased;

        public static class HeaderView extends LinearLayout {
            public final StarsBalanceView balanceView;
            public final GLIconTextureView iconView;
            public final StarParticlesView particlesView;
            public final TextView subtitleView;
            public final TextView titleView;
            private final FrameLayout topView;

            /* renamed from: $r8$lambda$6T0235Ai3-NsIWNPdUZkGNXX_lk, reason: not valid java name */
            public static /* synthetic */ void m19201$r8$lambda$6T0235Ai3NsIWNPdUZkGNXX_lk(HeaderView r0, View r1) {
                r0.lambda$new$0(r1);
            }

            public HeaderView(Context r19, int r20, Theme.ResourcesProvider r21) {
                super(r19);
                setOrientation(1);
                FrameLayout r4 = new FrameLayout(r19);
                this.topView = r4;
                r4.setClipChildren(false);
                r4.setClipToPadding(false);
                StarParticlesView r6 = StarsIntroActivity.makeParticlesView(r19, 70, 0);
                this.particlesView = r6;
                r4.addView(r6, LayoutHelper.createFrame(-1, -1.0f));
                GLIconTextureView r7 = new GLIconTextureView(r19, 1, 2);
                this.iconView = r7;
                GLIconRenderer r9 = r7.mRenderer;
                r9.colorKey1 = Theme.key_starsGradient1;
                r9.colorKey2 = Theme.key_starsGradient2;
                r9.updateColors();
                r7.setStarParticlesView(r6);
                r4.addView(r7, LayoutHelper.createFrame(170, 170.0f, 17, 0.0f, 32.0f, 0.0f, 24.0f));
                r7.setPaused(false);
                StarsBalanceView r5 = new StarsBalanceView(r19, r20, r21);
                this.balanceView = r5;
                ScaleStateListAnimator.apply(r5);
                r5.setOnClickListener(new StarsIntroActivity$StarsNeededSheet$HeaderView$$ExternalSyntheticLambda0(this));
                r4.addView(r5, LayoutHelper.createFrame(-2, -2.0f, 53, 0.0f, 0.0f, 0.0f, 0.0f));
                addView(r4, LayoutHelper.createFrame(-1, 150.0f));
                TextView r42 = new TextView(r19);
                this.titleView = r42;
                r42.setTextSize(1, 20.0f);
                r42.setTypeface(AndroidUtilities.bold());
                int r52 = Theme.key_dialogTextBlack;
                r42.setTextColor(Theme.getColor(r52, r21));
                r42.setGravity(17);
                addView(r42, LayoutHelper.createLinear(-2, -2, 1, 0, 2, 0, 0));
                TextView r43 = new TextView(r19);
                this.subtitleView = r43;
                r43.setTextSize(1, 14.0f);
                r43.setTextColor(Theme.getColor(r52, r21));
                r43.setGravity(17);
                addView(r43, LayoutHelper.createLinear(-2, -2, 1, 0, 9, 0, 18));
            }

            private /* synthetic */ void lambda$new$0(View r3) {
                if (this.balanceView.lastBalance <= 0) goto L10;
                BaseFragment r2 = LaunchActivity.getLastFragment();
                if (r2 == null) goto L9;
                BaseFragment.BottomSheetParams r32 = new BaseFragment.BottomSheetParams();
                r32.transitionFromLeft = true;
                r32.allowNestedScroll = false;
                r2.showAsSheet(new StarsIntroActivity(), r32);
                return;
            L9:
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$4bTLagt5fSUTUoBWpUZuvDRqaWk(StarsNeededSheet r0) {
            r0.lambda$new$1();
        }

        /* renamed from: $r8$lambda$CH8oNY_02-2xJDP_GxLNh_8QECQ, reason: not valid java name */
        public static /* synthetic */ void m19199$r8$lambda$CH8oNY_022xJDP_GxLNh_8QECQ(StarsNeededSheet r0, View r1, int r2) {
            r0.lambda$new$0(r1, r2);
        }

        /* renamed from: $r8$lambda$z-jwzFL8yIpvi60yITwQK7MTbFU, reason: not valid java name */
        public static /* synthetic */ void m19200$r8$lambda$zjwzFL8yIpvi60yITwQK7MTbFU(StarsNeededSheet r0, UItem r1, Boolean r2, String r3) {
            r0.lambda$onItemClick$2(r1, r2, r3);
        }

        public StarsNeededSheet(Context r16, Theme.ResourcesProvider r17, long r18, int r20, String r21, Runnable r22, long r23) {
            super(r16, null, false, false, false, r17);
            this.BUTTON_EXPAND = -1;
            this.topPadding = 0.2f;
            this.whenPurchased = r22;
            if (r23 != 0) goto L5;
            TLRPC.InputPeer r5 = null;
        L6:
            this.purposePeer = r5;
            boolean r52 = StarsController.getInstance(this.currentAccount).canBuy(r5);
            this.canBuy = r52;
            fixNavigationBar();
            RecyclerListView r10 = this.recyclerListView;
            int r11 = this.backgroundPaddingLeft;
            r10.setPadding(r11, 0, r11, 0);
            this.recyclerListView.setOnItemClickListener(new StarsIntroActivity$StarsNeededSheet$$ExternalSyntheticLambda0(this));
            this.recyclerListView.setSections();
            DefaultItemAnimator r102 = new DefaultItemAnimator();
            r102.setSupportsChangeAnimations(false);
            r102.setDelayAnimations(false);
            r102.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            r102.setDurations(350);
            this.recyclerListView.setItemAnimator(r102);
            setBackgroundColor(Theme.getColor(Theme.key_dialogBackgroundGray, r17));
            this.starsNeeded = r18;
            HeaderView r103 = new HeaderView(r16, this.currentAccount, r17);
            this.headerView = r103;
            r103.titleView.setText(LocaleController.formatPluralString("StarsNeededTitle", (int) Math.max(0, r18 - StarsController.getInstance(this.currentAccount).getBalance().amount), new Object[0]));
            if (r20 != 1) goto L9;
            String r7 = "StarsNeededTextBuySubscription";
        L64:
            if (TextUtils.isEmpty(r7) == false) goto L66;
            r103.subtitleView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        L70:
            this.actionBar.setTitle(getTitle());
            FrameLayout r72 = new FrameLayout(r16);
            this.footerView = r72;
            LinkSpanDrawable.LinksTextView r8 = new LinkSpanDrawable.LinksTextView(r16, r17);
            r72.setPadding(0, AndroidUtilities.dp(11.0f), 0, AndroidUtilities.dp(11.0f));
            r8.setTextSize(1, 12.0f);
            r8.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4, r17));
            r8.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r17));
            if (r52 == false) goto L73;
            r8.setText(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.StarsTOS), new StarsIntroActivity$StarsNeededSheet$$ExternalSyntheticLambda1(this)));
        L74:
            r8.setGravity(17);
            r8.setMaxWidth(HintView2.cutInFancyHalf(r8.getText(), r8.getPaint()));
            r72.addView(r8, LayoutHelper.createFrame(-2, -1, 17));
            r72.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, r17));
            FireworksOverlay r1 = new FireworksOverlay(getContext());
            this.fireworksOverlay = r1;
            this.containerView.addView(r1, LayoutHelper.createFrame(-1, -1.0f));
            UniversalAdapter r15 = this.adapter;
            if (r15 == null) goto L78;
            r15.update(false);
            return;
        L78:
            return;
        L73:
            r8.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.StarsPurchaseUnavailable)));
            goto L74
        L66:
            String r82 = LocaleController.nullable(LocaleController.formatString(r7, 0, new Object[]{r21}));
            if (r82 != null) goto L69;
            r82 = LocaleController.getString(r7);
        L69:
            r103.subtitleView.setText(AndroidUtilities.replaceTags(r82));
            TextView r73 = r103.subtitleView;
            r73.setMaxWidth(HintView2.cutInFancyHalf(r73.getText(), r103.subtitleView.getPaint()));
            goto L70
        L9:
            String r83 = "StarsNeededTextKeepSubscription";
            if (r20 != 2) goto L13;
        L11:
            r7 = r83;
            goto L64
        L13:
            if (r20 != 7) goto L16;
            r7 = "StarsNeededTextKeepBotSubscription";
            goto L64
        L16:
            if (r20 != 8) goto L19;
            r7 = "StarsNeededTextKeepBizSubscription";
            goto L64
        L19:
            if (r20 == 3) goto L11;
            if (r20 != 4) goto L31;
            r7 = "StarsNeededTextLink";
            if (r21 != null) goto L26;
            r83 = "StarsNeededTextLink";
        L28:
            if (LocaleController.nullable(LocaleController.getString(r83)) != null) goto L11;
        L26:
            r83 = "StarsNeededTextLink_" + r21.toLowerCase();
            goto L28
        L31:
            if (r20 != 5) goto L34;
            r7 = "StarsNeededTextReactions";
            goto L64
        L34:
            if (r20 != 6) goto L37;
            r7 = "StarsNeededTextGift";
            goto L64
        L37:
            if (r20 != 12) goto L40;
            r7 = "StarsNeededTextGiftChannel";
            goto L64
        L40:
            if (r20 != 13) goto L43;
            r7 = "StarsNeededTextPrivateMessage";
            goto L64
        L43:
            if (r20 != 10) goto L46;
            r7 = "StarsNeededTextGiftUpgrade";
            goto L64
        L46:
            if (r20 != 11) goto L49;
            r7 = "StarsNeededTextGiftTransfer";
            goto L64
        L49:
            if (r20 != 9) goto L52;
            r7 = "StarsNeededBizText";
            goto L64
        L52:
            if (r20 != 14) goto L55;
            r7 = "StarsNeededTextGiftBuyResale";
            goto L64
        L55:
            if (r20 != 15) goto L58;
            r7 = "StarsNeededTextSearch";
            goto L64
        L58:
            if (r20 != 16) goto L60;
            r7 = "StarsNeededRemoveGiftDescription";
            goto L64
        L60:
            if (r20 != 17) goto L62;
            r7 = "StarsNeededLiveComments";
            goto L64
        L62:
            r7 = "StarsNeededText";
            goto L64
        L5:
            r5 = MessagesController.getInstance(this.currentAccount).getInputPeer(r23);
            goto L6
        }

        private /* synthetic */ void lambda$new$0(View r1, int r2) {
            UniversalAdapter r12 = this.adapter;
            if (r12 == null) goto L10;
            UItem r13 = r12.getItem(r2 - 1);
            if (r13 != null) goto L8;
            return;
        L8:
            onItemClick(r13, this.adapter);
            return;
        }

        private /* synthetic */ void lambda$new$1() {
            Browser.openUrl(getContext(), LocaleController.getString(R.string.StarsTOSLink));
        }

        private /* synthetic */ void lambda$onItemClick$2(UItem r4, Boolean r5, String r6) {
            if (getContext() != null) goto L6;
            return;
        L6:
            if (r5.booleanValue() == false) goto L9;
            BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider).createSimpleBulletin(R.raw.stars_topup, LocaleController.getString(R.string.StarsAcquired), AndroidUtilities.replaceTags(LocaleController.formatPluralString("StarsAcquiredInfo", (int) r4.longValue, new Object[0]))).show();
            this.fireworksOverlay.start(true);
            StarsController.getInstance(this.currentAccount).invalidateTransactions(true);
            return;
        L9:
            if (r6 == null) goto L12;
            BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider).createSimpleBulletin(R.raw.error, LocaleController.formatString(R.string.UnknownErrorCode, new Object[]{r6})).show();
            return;
        }

        @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
        public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
            UniversalAdapter r0 = new UniversalAdapter(this.recyclerListView, getContext(), this.currentAccount, 0, true, new StarsIntroActivity$StarsNeededSheet$$ExternalSyntheticLambda2(this), this.resourcesProvider);
            this.adapter = r0;
            return r0;
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r3, int r4, Object... r5) {
            if (r3 != NotificationCenter.starOptionsLoaded) goto L5;
        L6:
            UniversalAdapter r32 = this.adapter;
            if (r32 == null) goto L9;
            r32.update(true);
        L9:
            long r33 = StarsController.getInstance(this.currentAccount).getBalance().amount;
            this.headerView.titleView.setText(LocaleController.formatPluralStringComma("StarsNeededTitle", (int) (this.starsNeeded - r33)));
            ActionBar r52 = this.actionBar;
            if (r52 == null) goto L13;
            r52.setTitle(getTitle());
        L13:
            if (r33 < this.starsNeeded) goto L19;
            Runnable r34 = this.whenPurchased;
            if (r34 == null) goto L20;
            r34.run();
            this.whenPurchased = null;
            dismiss();
            return;
        L20:
            return;
        L19:
            return;
        L5:
            if (r3 == NotificationCenter.starBalanceUpdated) goto L6;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
        public void dismiss() {
            super.dismiss();
            HeaderView r1 = this.headerView;
            if (r1 == null) goto L6;
            r1.iconView.setPaused(true);
            return;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet
        public void dismissInternal() {
            super.dismissInternal();
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starOptionsLoaded);
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starBalanceUpdated);
        }

        public void fillItems(ArrayList<UItem> r13, UniversalAdapter r14) {
            r13.add(UItem.asCustomShadow(this.headerView));
            if (this.canBuy == false) goto L5;
            r13.add(UItem.asHeader(LocaleController.getString(R.string.TelegramStarsChoose)));
        L5:
            ArrayList<TL_stars.TL_starsTopupOption> r142 = StarsController.getInstance(this.currentAccount).getOptions();
            if (this.canBuy == false) goto L59;
            if (r142 != null) goto L10;
        L58:
            r13.add(UItem.asFlicker(31));
            r13.add(UItem.asFlicker(31));
            r13.add(UItem.asFlicker(31));
            goto L59
        L10:
            if (r142.isEmpty() == true) goto L58;
            int r0 = 0;
            int r2 = 0;
            int r3 = 0;
            int r4 = 0;
            boolean r5 = false;
            int r6 = 1;
        L13:
            if (r2 >= r142.size()) goto L26;
            TL_stars.TL_starsTopupOption r7 = r142.get(r2);
            if (r7.stars < this.starsNeeded) goto L24;
            if (r7.extended == true) goto L20;
        L23:
            r13.add(StarTierView.Factory.asStarTier(r2, r6, r7));
            r3 = r3 + 1;
            r6 = r6 + 1;
            r5 = true;
            goto L24
        L20:
            if (this.expanded == true) goto L23;
            if (r5 == false) goto L23;
            r4 = r4 + 1;
        L24:
            r2 = r2 + 1;
            goto L13
        L26:
            if (r3 >= 3) goto L47;
            r13.clear();
            r13.add(UItem.asCustom(this.headerView));
            r13.add(UItem.asHeader(LocaleController.getString(R.string.TelegramStarsChoose)));
            int r22 = 0;
            int r32 = 0;
        L29:
            if (r22 >= r142.size()) goto L35;
            TL_stars.TL_starsTopupOption r72 = r142.get(r22);
            if (r72.stars < this.starsNeeded) goto L34;
            r13.add(StarTierView.Factory.asStarTier(r22, r6, r72));
            r32 = r32 + 1;
            r6 = r6 + 1;
        L34:
            r22 = r22 + 1;
            goto L29
        L35:
            if (r32 == 0) goto L37;
            this.expanded = true;
        L37:
            if (r0 >= r142.size()) goto L39;
            r13.add(StarTierView.Factory.asStarTier(r0, r6, r142.get(r0)));
            r0 = r0 + 1;
            r6 = r6 + 1;
            goto L37
        L39:
            boolean r143 = this.expanded;
            if (r143 == true) goto L59;
            if (r4 <= 0) goto L59;
            if (r143 == false) goto L44;
            int r144 = R.string.NotifyLessOptions;
        L45:
            r13.add(ExpandView.Factory.asExpand(-1, LocaleController.getString(r144), !this.expanded).accent());
            goto L59
        L44:
            r144 = R.string.NotifyMoreOptions;
            goto L45
        L47:
            if (r3 <= 0) goto L56;
            boolean r145 = this.expanded;
            if (r145 == true) goto L59;
            if (r4 <= 0) goto L59;
            if (r145 == false) goto L53;
            int r146 = R.string.NotifyLessOptions;
        L54:
            r13.add(ExpandView.Factory.asExpand(-1, LocaleController.getString(r146), !this.expanded).accent());
            goto L59
        L53:
            r146 = R.string.NotifyMoreOptions;
        L56:
            if (r0 >= r142.size()) goto L59;
            r13.add(StarTierView.Factory.asStarTier(r0, r6, r142.get(r0)));
            r0 = r0 + 1;
            r6 = r6 + 1;
        L59:
            r13.add(UItem.asCustom(this.footerView));
        }

        @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
        public CharSequence getTitle() {
            HeaderView r0 = this.headerView;
            if (r0 != null) goto L7;
            return null;
        L7:
            return r0.titleView.getText();
        }

        public void onItemClick(UItem r4, UniversalAdapter r5) {
            if (r4.id != (-1)) goto L7;
            this.expanded = !this.expanded;
            r5.update(true);
            return;
        L7:
            if (r4.instanceOf(StarTierView.Factory.class) == true) goto L9;
            return;
        L9:
            if ((r4.object instanceof TL_stars.TL_starsTopupOption) == false) goto L18;
            Activity r52 = AndroidUtilities.findActivity(getContext());
            if (r52 != null) goto L13;
            r52 = LaunchActivity.instance;
        L13:
            if (r52 == null) goto L19;
            StarsController.getInstance(this.currentAccount).buy(r52, (TL_stars.TL_starsTopupOption) r4.object, new StarsIntroActivity$StarsNeededSheet$$ExternalSyntheticLambda3(this, r4), this.purposePeer);
            return;
        L19:
            return;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
        public void show() {
            if (this.canBuy == true) goto L7;
            BulletinFactory.of(Bulletin.BulletinWindow.make(getContext()), this.resourcesProvider).createSimpleBulletin(R.raw.stars_topup, LocaleController.getString(R.string.PaymentInvoiceDisabledStarsText)).show();
            return;
        L7:
            if (StarsController.getInstance(this.currentAccount).getBalance().amount < this.starsNeeded) goto L12;
            Runnable r0 = this.whenPurchased;
            if (r0 == null) goto L21;
            r0.run();
            this.whenPurchased = null;
            return;
        L21:
            return;
        L12:
            BaseFragment r02 = LaunchActivity.getLastFragment();
            if ((r02 instanceof ChatActivity) == false) goto L19;
            ChatActivity r03 = (ChatActivity) r02;
            if (r03.isKeyboardVisible() == false) goto L19;
            if (r03.getChatActivityEnterView() == null) goto L19;
            r03.getChatActivityEnterView().closeKeyboard();
        L19:
            super.show();
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starOptionsLoaded);
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starBalanceUpdated);
        }
    }

    public static class StarsOptionsSheet extends BottomSheetWithRecyclerListView implements NotificationCenter.NotificationCenterDelegate {
        private final int BUTTON_EXPAND;
        private UniversalAdapter adapter;
        private boolean expanded;
        private final FireworksOverlay fireworksOverlay;
        private final FrameLayout footerView;

        public static /* synthetic */ void $r8$lambda$6yrhD_6jehku_ps0ptoZ1z8cAPg(StarsOptionsSheet r0, UItem r1, Boolean r2, String r3) {
            r0.lambda$onItemClick$2(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$Vwyd6L2_K6wDdPibgRgSTXOCFD4(StarsOptionsSheet r0) {
            r0.lambda$new$1();
        }

        public static /* synthetic */ void $r8$lambda$nYFnHpf3OlRl8640kaybasTrJis(StarsOptionsSheet r0, View r1, int r2) {
            r0.lambda$new$0(r1, r2);
        }

        public StarsOptionsSheet(Context r8, Theme.ResourcesProvider r9) {
            super(r8, null, false, false, false, r9);
            this.BUTTON_EXPAND = -1;
            RecyclerListView r82 = this.recyclerListView;
            int r92 = this.backgroundPaddingLeft;
            r82.setPadding(r92, 0, r92, 0);
            this.recyclerListView.setOnItemClickListener(new StarsIntroActivity$StarsOptionsSheet$$ExternalSyntheticLambda1(this));
            DefaultItemAnimator r83 = new DefaultItemAnimator();
            r83.setSupportsChangeAnimations(false);
            r83.setDelayAnimations(false);
            r83.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            r83.setDurations(350);
            this.recyclerListView.setItemAnimator(r83);
            int r84 = Theme.key_windowBackgroundWhite;
            setBackgroundColor(Theme.getColor(r84, r9));
            fixNavigationBar(Theme.getColor(r84, r9));
            this.actionBar.setTitle(getTitle());
            FrameLayout r85 = new FrameLayout(r8);
            this.footerView = r85;
            LinkSpanDrawable.LinksTextView r93 = new LinkSpanDrawable.LinksTextView(r8, r9);
            r85.setPadding(0, AndroidUtilities.dp(11.0f), 0, AndroidUtilities.dp(11.0f));
            r93.setTextSize(1, 12.0f);
            r93.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4, r9));
            r93.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r9));
            r93.setText(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.StarsTOS), new StarsIntroActivity$StarsOptionsSheet$$ExternalSyntheticLambda2(this)));
            r93.setGravity(17);
            r93.setMaxWidth(HintView2.cutInFancyHalf(r93.getText(), r93.getPaint()));
            r85.addView(r93, LayoutHelper.createFrame(-2, -1, 17));
            r85.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, r9));
            FireworksOverlay r86 = new FireworksOverlay(getContext());
            this.fireworksOverlay = r86;
            this.containerView.addView(r86, LayoutHelper.createFrame(-1, -1.0f));
            UniversalAdapter r7 = this.adapter;
            if (r7 == null) goto L6;
            r7.update(false);
            return;
        }

        private /* synthetic */ void lambda$new$0(View r1, int r2) {
            UniversalAdapter r12 = this.adapter;
            if (r12 == null) goto L10;
            UItem r13 = r12.getItem(r2 - 1);
            if (r13 != null) goto L8;
            return;
        L8:
            onItemClick(r13, this.adapter);
            return;
        }

        private /* synthetic */ void lambda$new$1() {
            Browser.openUrl(getContext(), LocaleController.getString(R.string.StarsTOSLink));
        }

        private /* synthetic */ void lambda$onItemClick$2(UItem r4, Boolean r5, String r6) {
            if (getContext() == null) goto L19;
            dismiss();
            StarsController.getInstance(this.currentAccount).invalidateTransactions(true);
            BaseFragment r3 = LaunchActivity.getSafeLastFragment();
            if (r3 != null) goto L9;
            return;
        L9:
            if (r5.booleanValue() == false) goto L14;
            BulletinFactory.of(r3).createSimpleBulletin(R.raw.stars_topup, LocaleController.getString(R.string.StarsAcquired), AndroidUtilities.replaceTags(LocaleController.formatPluralString("StarsAcquiredInfo", (int) r4.longValue, new Object[0]))).show();
            LaunchActivity r32 = LaunchActivity.instance;
            if (r32 == null) goto L17;
            r32.getFireworksOverlay().start(true);
            return;
        L17:
            return;
        L14:
            if (r6 == null) goto L18;
            BulletinFactory.of(r3).createSimpleBulletin(R.raw.error, LocaleController.formatString(R.string.UnknownErrorCode, new Object[]{r6})).show();
            return;
        L18:
            return;
        }

        @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
        public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
            UniversalAdapter r0 = new UniversalAdapter(this.recyclerListView, getContext(), this.currentAccount, 0, true, new StarsIntroActivity$StarsOptionsSheet$$ExternalSyntheticLambda0(this), this.resourcesProvider);
            this.adapter = r0;
            r0.setApplyBackground(false);
            return this.adapter;
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r1, int r2, Object... r3) {
            if (r1 != NotificationCenter.starOptionsLoaded) goto L5;
        L6:
            UniversalAdapter r0 = this.adapter;
            if (r0 == null) goto L11;
            r0.update(true);
            return;
        L11:
            return;
        L5:
            if (r1 == NotificationCenter.starBalanceUpdated) goto L6;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet
        public void dismissInternal() {
            super.dismissInternal();
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starOptionsLoaded);
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starBalanceUpdated);
        }

        public void fillItems(ArrayList<UItem> r7, UniversalAdapter r8) {
            r7.add(UItem.asHeader(LocaleController.getString(R.string.TelegramStarsChoose)));
            ArrayList<TL_stars.TL_starsTopupOption> r82 = StarsController.getInstance(this.currentAccount).getOptions();
            if (r82 != null) goto L5;
        L23:
            r7.add(UItem.asFlicker(31));
            r7.add(UItem.asFlicker(31));
            r7.add(UItem.asFlicker(31));
            r7.add(UItem.asFlicker(31));
            r7.add(UItem.asFlicker(31));
        L24:
            r7.add(UItem.asCustom(this.footerView));
            return;
        L5:
            if (r82.isEmpty() == true) goto L23;
            int r0 = 0;
            int r2 = 0;
            int r3 = 1;
        L8:
            if (r0 >= r82.size()) goto L16;
            TL_stars.TL_starsTopupOption r4 = r82.get(r0);
            if (r4.extended == true) goto L12;
        L14:
            r7.add(StarTierView.Factory.asStarTier(r0, r3, r4));
            r3 = r3 + 1;
        L15:
            r0 = r0 + 1;
            goto L8
        L12:
            if (this.expanded == true) goto L14;
            r2 = r2 + 1;
            goto L15
        L16:
            boolean r83 = this.expanded;
            if (r83 == true) goto L24;
            if (r2 <= 0) goto L24;
            if (r83 == false) goto L21;
            int r84 = R.string.NotifyLessOptions;
        L22:
            r7.add(ExpandView.Factory.asExpand(-1, LocaleController.getString(r84), !this.expanded).accent());
            goto L24
        L21:
            r84 = R.string.NotifyMoreOptions;
            goto L22
        }

        @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
        public CharSequence getTitle() {
            return LocaleController.getString(R.string.StarsBuy);
        }

        public void onItemClick(UItem r4, UniversalAdapter r5) {
            if (r4.id != (-1)) goto L7;
            this.expanded = !this.expanded;
            r5.update(true);
            this.recyclerListView.smoothScrollBy(0, AndroidUtilities.dp(300.0f));
            return;
        L7:
            if (r4.instanceOf(StarTierView.Factory.class) == true) goto L9;
            return;
        L9:
            if ((r4.object instanceof TL_stars.TL_starsTopupOption) == false) goto L18;
            Activity r52 = AndroidUtilities.findActivity(getContext());
            if (r52 != null) goto L13;
            r52 = LaunchActivity.instance;
        L13:
            if (r52 == null) goto L19;
            StarsController.getInstance(this.currentAccount).buy(r52, (TL_stars.TL_starsTopupOption) r4.object, new StarsIntroActivity$StarsOptionsSheet$$ExternalSyntheticLambda3(this, r4), null);
            return;
        L19:
            return;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
        public void show() {
            long r0 = StarsController.getInstance(this.currentAccount).getBalance().amount;
            BaseFragment r02 = LaunchActivity.getLastFragment();
            if ((r02 instanceof ChatActivity) == false) goto L9;
            ChatActivity r03 = (ChatActivity) r02;
            if (r03.isKeyboardVisible() == false) goto L9;
            if (r03.getChatActivityEnterView() == null) goto L9;
            r03.getChatActivityEnterView().closeKeyboard();
        L9:
            super.show();
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starOptionsLoaded);
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starBalanceUpdated);
        }
    }

    public static class StarsSubscriptionView extends LinearLayout {
        private final int currentAccount;
        public final BackupImageView imageView;
        private boolean needDivider;
        public final LinearLayout priceLayout;
        public final TextView priceSubtitleView;
        public final TextView priceTitleView;
        public final TextView productView;
        private final Theme.ResourcesProvider resourcesProvider;
        public final TextView subtitleView;
        public final LinearLayout textLayout;
        private boolean threeLines;
        public final SimpleTextView titleView;

        public static class Factory extends UItem.UItemFactory<StarsSubscriptionView> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem asSubscription(TL_stars.StarsSubscription r1) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.object = r1;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                ((StarsSubscriptionView) r1).set((TL_stars.StarsSubscription) r2.object, r3);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public StarsSubscriptionView createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                StarsSubscriptionView r0 = getCached();
                if (r0 == null) goto L6;
                return r0;
            L6:
                return new StarsSubscriptionView(r1, r3, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean equals(UItem r2, UItem r3) {
                if (r2 != null) goto L7;
                if (r3 != null) goto L7;
                return true;
            L7:
                if (r2 == null) goto L17;
                if (r3 == null) goto L19;
                Object r22 = r2.object;
                if ((r22 instanceof TL_stars.StarsSubscription) == false) goto L18;
                Object r32 = r3.object;
                if ((r32 instanceof TL_stars.StarsSubscription) == true) goto L16;
                return false;
            L16:
                return TextUtils.equals(((TL_stars.StarsSubscription) r22).id, ((TL_stars.StarsSubscription) r32).id);
            L18:
                return false;
            L19:
                return false;
            L17:
                return false;
            }
        }

        public StarsSubscriptionView(Context r18, int r19, Theme.ResourcesProvider r20) {
            super(r18);
            this.currentAccount = r19;
            this.resourcesProvider = r20;
            setOrientation(0);
            BackupImageView r3 = new BackupImageView(r18);
            this.imageView = r3;
            r3.setRoundRadius(AndroidUtilities.dp(46.0f));
            addView(r3, LayoutHelper.createLinear(46, 46, 0.0f, 19, 13, 0, 13, 0));
            LinearLayout r32 = new LinearLayout(r18);
            this.textLayout = r32;
            r32.setOrientation(1);
            addView(r32, LayoutHelper.createLinear(-1, -2, 1.0f, 16, 0, 0, 0, 0));
            SimpleTextView r5 = new SimpleTextView(r18);
            this.titleView = r5;
            int r6 = Theme.key_windowBackgroundWhiteBlackText;
            r5.setTextColor(Theme.getColor(r6, r20));
            r5.setTextSize(16);
            r5.setTypeface(AndroidUtilities.bold());
            NotificationCenter.listenEmojiLoading(r5);
            r32.addView(r5, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 2.0f));
            TextView r52 = new TextView(r18);
            this.productView = r52;
            r52.setTextColor(Theme.getColor(r6, r20));
            r52.setTextSize(1, 13.0f);
            r52.setVisibility(8);
            r32.addView(r52, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 1.0f));
            TextView r53 = new TextView(r18);
            this.subtitleView = r53;
            int r8 = Theme.key_windowBackgroundWhiteGrayText2;
            r53.setTextColor(Theme.getColor(r8, r20));
            r53.setTextSize(1, 14.0f);
            r32.addView(r53, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 0.0f));
            LinearLayout r33 = new LinearLayout(r18);
            this.priceLayout = r33;
            r33.setOrientation(1);
            addView(r33, LayoutHelper.createLinear(-2, -2, 0.0f, 16, 0, 0, 18, 0));
            TextView r54 = new TextView(r18);
            this.priceTitleView = r54;
            r54.setTextColor(Theme.getColor(r6, r20));
            r54.setTextSize(1, 16.0f);
            r54.setTypeface(AndroidUtilities.bold());
            r54.setGravity(5);
            r33.addView(r54, LayoutHelper.createLinear(-1, -2, 5, 0, 0, 0, 1));
            TextView r55 = new TextView(r18);
            this.priceSubtitleView = r55;
            r55.setTextColor(Theme.getColor(r8, r20));
            r55.setTextSize(1, 13.0f);
            r55.setGravity(5);
            r33.addView(r55, LayoutHelper.createLinear(-1, -2, 5, 0, 0, 0, 0));
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onDraw(Canvas r8) {
            super.onDraw(r8);
            if (this.needDivider == false) goto L6;
            r8.drawRect(AndroidUtilities.dp(72.0f), getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight(), Theme.dividerPaint);
            return;
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            int r22 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30);
            if (this.threeLines == false) goto L5;
            float r0 = 68.0f;
        L6:
            super.onMeasure(r22, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r0), TLObject.FLAG_30));
            return;
        L5:
            r0 = 58.0f;
            goto L6
        }

        public void set(TL_stars.StarsSubscription r23, boolean r24) {
            long r3 = DialogObject.getPeerDialogId(r23.peer);
            this.threeLines = !TextUtils.isEmpty(r23.title);
            int r7 = this.currentAccount;
            if (r3 >= 0) goto L9;
            TLRPC.Chat r32 = MessagesController.getInstance(r7).getChat(Long.valueOf(-r3));
            AvatarDrawable r4 = new AvatarDrawable();
            r4.setInfo(r32);
            this.imageView.setForUserOrChat(r32, r4);
            if (r32 == null) goto L7;
            String r33 = r32.title;
        L8:
            boolean r42 = false;
        L10:
            long r9 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
            SimpleTextView r5 = this.titleView;
            r5.setText(Emoji.replaceEmoji(r33, r5.getPaint().getFontMetricsInt(), false));
            boolean r34 = TextUtils.isEmpty(r23.title);
            TextView r52 = this.productView;
            float r72 = 14.0f;
            if (r34 == true) goto L16;
            r52.setVisibility(0);
            SpannableStringBuilder r35 = new SpannableStringBuilder();
            if (r23.photo == null) goto L15;
            ImageReceiverSpan r53 = new ImageReceiverSpan(this.productView, this.currentAccount, 14.0f);
            r53.setRoundRadius(4.0f);
            r53.enableShadow(false);
            SpannableString r12 = new SpannableString("x");
            r12.setSpan(r53, 0, 1, 33);
            r53.imageReceiver.setImage(ImageLocation.getForWebFile(WebFile.createWithWebDocument(r23.photo)), "14_14", null, null, 0, 0);
            r35.append(r12).append(" ");
        L15:
            r35.append(Emoji.replaceEmoji(r23.title, this.titleView.getPaint().getFontMetricsInt(), false));
            this.productView.setText(r35);
        L17:
            TextView r36 = this.subtitleView;
            if (this.threeLines == false) goto L20;
            r72 = 13.0f;
        L20:
            r36.setTextSize(1, r72);
            if (r23.canceled == false) goto L23;
        L37:
            TextView r37 = this.subtitleView;
            int r54 = r23.until_date;
            if (r54 >= r9) goto L40;
            int r73 = R.string.StarsSubscriptionExpired;
        L41:
            r37.setText(LocaleController.formatString(r73, new Object[]{LocaleController.formatDateChat(r54)}));
            this.priceTitleView.setVisibility(8);
            this.priceSubtitleView.setTextColor(Theme.getColor(Theme.key_color_red, this.resourcesProvider));
            TextView r38 = this.priceSubtitleView;
            if (r23.bot_canceled == false) goto L46;
            if (r42 == false) goto L45;
            int r1 = R.string.StarsSubscriptionStatusBizCancelled;
        L47:
            r38.setText(LocaleController.getString(r1));
        L48:
            this.needDivider = r24;
            setWillNotDraw(!r24);
            return;
        L45:
            r1 = R.string.StarsSubscriptionStatusBotCancelled;
            goto L47
        L46:
            r1 = R.string.StarsSubscriptionStatusCancelled;
            goto L47
        L40:
            r73 = R.string.StarsSubscriptionExpires;
            goto L41
        L23:
            if (r23.bot_canceled == true) goto L37;
            int r39 = r23.until_date;
            long r43 = r39;
            TextView r55 = this.subtitleView;
            if (r43 >= r9) goto L28;
            r55.setText(LocaleController.formatString(R.string.StarsSubscriptionExpired, new Object[]{LocaleController.formatDateChat(r39)}));
            this.priceTitleView.setVisibility(8);
            this.priceSubtitleView.setTextColor(Theme.getColor(Theme.key_color_red, this.resourcesProvider));
            this.priceSubtitleView.setText(LocaleController.getString(R.string.StarsSubscriptionStatusExpired));
            goto L48
        L28:
            r55.setText(LocaleController.formatString(R.string.StarsSubscriptionRenews, new Object[]{LocaleController.formatDateChat(r39)}));
            this.priceTitleView.setVisibility(0);
            this.priceTitleView.setText(StarsIntroActivity.replaceStarsWithPlain("⭐️ " + Long.toString(r23.pricing.amount), 0.8f));
            this.priceSubtitleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, this.resourcesProvider));
            int r13 = r23.pricing.period;
            if (r13 != 2592000) goto L32;
            this.priceSubtitleView.setText(LocaleController.getString(R.string.StarsParticipantSubscriptionPerMonth));
            goto L48
        L32:
            if (r13 != 60) goto L35;
            this.priceSubtitleView.setText("per minute");
            goto L48
        L35:
            if (r13 != 300) goto L48;
            this.priceSubtitleView.setText("per 5 minutes");
            goto L48
        L16:
            r52.setVisibility(8);
            goto L17
        L7:
            r33 = null;
            goto L8
        L9:
            TLRPC.User r310 = MessagesController.getInstance(r7).getUser(Long.valueOf(r3));
            AvatarDrawable r44 = new AvatarDrawable();
            r44.setInfo(r310);
            this.imageView.setForUserOrChat(r310, r44);
            String r45 = UserObject.getUserName(r310);
            r42 = !UserObject.isBot(r310);
            r33 = r45;
            goto L10
        }
    }

    public static class StarsTransactionView extends LinearLayout {
        public static HashMap<String, CombinedDrawable> cachedPlatformDrawables;
        private final TextView amountTextView;
        private final AvatarDrawable avatarDrawable;
        private Runnable cancelCurrentGift;
        private final int currentAccount;
        private final TextView dateTextView;
        private final LinearLayout.LayoutParams dateTextViewParams;
        private final BackupImageView imageView;
        private final BackupImageView imageView2;
        private final FrameLayout imageViewContainer;
        private int imageViewCount;
        private boolean needDivider;
        private final SpannableString star;
        private final TextView subtitleTextView;
        private final LinearLayout textLayout;
        private boolean threeLines;
        private final TextView titleTextView;
        private final LinearLayout.LayoutParams titleTextViewParams;
        private final SpannableString ton;

        /* renamed from: org.telegram.ui.Stars.StarsIntroActivity$StarsTransactionView$1, reason: invalid class name */
        public class AnonymousClass1 extends FrameLayout {
            private final Paint backgroundPaint;
            final /* synthetic */ StarsTransactionView this$0;
            final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

            public AnonymousClass1(StarsTransactionView r1, Context r2, Theme.ResourcesProvider r3) {
                this.this$0 = r1;
                this.val$resourcesProvider = r3;
                super(r2);
                this.backgroundPaint = new Paint(1);
            }

            @Override // android.view.ViewGroup
            public boolean drawChild(Canvas r7, View r8, long r9) {
                if (StarsTransactionView.m19202$$Nest$fgetimageViewCount(this.this$0) <= 1) goto L6;
                this.backgroundPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhite, this.val$resourcesProvider));
                RectF r0 = AndroidUtilities.rectTmp;
                r0.set(r8.getX(), r8.getY(), r8.getX() + r8.getWidth(), r8.getY() + r8.getHeight());
                r0.inset(-AndroidUtilities.dp(1.66f), -AndroidUtilities.dp(1.66f));
                r7.drawRoundRect(r0, AndroidUtilities.dp(13.0f), AndroidUtilities.dp(13.0f), this.backgroundPaint);
            L6:
                return super.drawChild(r7, r8, r9);
            }
        }

        public static class Factory extends UItem.UItemFactory<StarsTransactionView> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem asTransaction(TL_stars.StarsTransaction r1, boolean r2) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.object = r1;
                r0.accent = r2;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                ((StarsTransactionView) r1).set((TL_stars.StarsTransaction) r2.object, r2.accent, r3);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public StarsTransactionView createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                StarsTransactionView r0 = getCached();
                if (r0 == null) goto L6;
                return r0;
            L6:
                return new StarsTransactionView(r1, r3, r5);
            }
        }

        /* renamed from: -$$Nest$fgetimageViewCount, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m19202$$Nest$fgetimageViewCount(StarsTransactionView r0) {
            return r0.imageViewCount;
        }

        public StarsTransactionView(Context r18, int r19, Theme.ResourcesProvider r20) {
            super(r18);
            this.imageViewCount = 1;
            this.currentAccount = r19;
            setOrientation(0);
            AnonymousClass1 r5 = new AnonymousClass1(this, r18, r20);
            this.imageViewContainer = r5;
            addView(r5, LayoutHelper.createLinear(72, -1, 0.0f, 115));
            BackupImageView r6 = new BackupImageView(r18);
            this.imageView2 = r6;
            r6.setRoundRadius(AndroidUtilities.dp(46.0f));
            r5.addView(r6, LayoutHelper.createFrame(46, 46.0f, 16, 13.0f, 0.0f, 13.0f, 0.0f));
            this.avatarDrawable = new AvatarDrawable();
            BackupImageView r62 = new BackupImageView(r18);
            this.imageView = r62;
            r62.setRoundRadius(AndroidUtilities.dp(46.0f));
            r5.addView(r62, LayoutHelper.createFrame(46, 46.0f, 16, 13.0f, 0.0f, 13.0f, 0.0f));
            LinearLayout r52 = new LinearLayout(r18);
            this.textLayout = r52;
            r52.setOrientation(1);
            r52.setGravity(19);
            addView(r52, LayoutHelper.createLinear(-2, -1, 1.0f, 119));
            TextView r63 = new TextView(r18);
            this.titleTextView = r63;
            r63.setTypeface(AndroidUtilities.bold());
            int r7 = Theme.key_windowBackgroundWhiteBlackText;
            r63.setTextColor(Theme.getColor(r7, r20));
            r63.setTextSize(1, 16.0f);
            TextUtils.TruncateAt r10 = TextUtils.TruncateAt.END;
            r63.setEllipsize(r10);
            r63.setSingleLine(true);
            LinearLayout.LayoutParams r11 = LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 4.33f);
            this.titleTextViewParams = r11;
            r52.addView(r63, r11);
            LinkSpanDrawable.LinksTextView r64 = new LinkSpanDrawable.LinksTextView(r18);
            this.subtitleTextView = r64;
            r64.setTextColor(Theme.getColor(r7, r20));
            r64.setTextSize(1, 13.0f);
            r64.setEllipsize(r10);
            r64.setSingleLine(true);
            r52.addView(r64, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 0.33f));
            TextView r65 = new TextView(r18);
            this.dateTextView = r65;
            r65.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r20));
            r65.setTextSize(1, 14.0f);
            r65.setEllipsize(r10);
            r65.setSingleLine(true);
            LinearLayout.LayoutParams r2 = LayoutHelper.createLinear(-1, -2);
            this.dateTextViewParams = r2;
            r52.addView(r65, r2);
            TextView r22 = new TextView(r18);
            this.amountTextView = r22;
            r22.setTypeface(AndroidUtilities.bold());
            r22.setTextSize(1, 15.3f);
            r22.setGravity(5);
            addView(r22, LayoutHelper.createLinear(-2, -2, 0.0f, 21, 8, 0, 20, 0));
            SpannableString r23 = new SpannableString("⭐️");
            this.star = r23;
            Drawable r3 = r18.getResources().getDrawable(R.drawable.star_small_inner).mutate();
            r3.setBounds(0, 0, AndroidUtilities.dp(21.0f), AndroidUtilities.dp(21.0f));
            r23.setSpan(new ImageSpan(r3), 0, r23.length(), 33);
            SpannableString r24 = new SpannableString("TON");
            this.ton = r24;
            ColoredImageSpan r1 = new ColoredImageSpan(r18.getResources().getDrawable(R.drawable.mini_gram_72).mutate());
            r1.setSize(AndroidUtilities.dp(18.0f));
            r1.setTranslateY(AndroidUtilities.dp(0.5f));
            r24.setSpan(r1, 0, r24.length(), 33);
        }

        public static CombinedDrawable getPlatformDrawable(String r1) {
            return getPlatformDrawable(r1, 44);
        }

        public static CombinedDrawable getPlatformDrawable(String r1, int r2) {
            if (r2 == 44) goto L7;
            return SessionCell.createDrawable(r2, r1);
        L7:
            if (cachedPlatformDrawables != null) goto L9;
            cachedPlatformDrawables = new HashMap();
        L9:
            CombinedDrawable r22 = cachedPlatformDrawables.get(r1);
            if (r22 != null) goto L13;
            HashMap<String, CombinedDrawable> r23 = cachedPlatformDrawables;
            CombinedDrawable r0 = SessionCell.createDrawable(44, r1);
            r23.put(r1, r0);
            return r0;
        L13:
            return r22;
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onDraw(Canvas r9) {
            super.onDraw(r9);
            if (this.needDivider == true) goto L5;
            return;
        L5:
            if (LocaleController.isRTL == false) goto L8;
            float r0 = 0.0f;
        L7:
            float r3 = r0;
            float r4 = getMeasuredHeight() - 1;
            int r02 = getMeasuredWidth();
            if (LocaleController.isRTL == false) goto L12;
            int r1 = AndroidUtilities.dp(72.0f);
        L13:
            r9.drawRect(r3, r4, r02 - r1, getMeasuredHeight(), Theme.dividerPaint);
            return;
        L12:
            r1 = 0;
            goto L13
        L8:
            r0 = AndroidUtilities.dp(72.0f);
            goto L7
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            int r22 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30);
            if (this.threeLines == false) goto L5;
            float r0 = 71.0f;
        L6:
            super.onMeasure(r22, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r0), TLObject.FLAG_30));
            return;
        L5:
            r0 = 58.0f;
            goto L6
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r19v0 */
        /* JADX WARN: Type inference failed for: r19v1 */
        /* JADX WARN: Type inference failed for: r19v10 */
        /* JADX WARN: Type inference failed for: r19v4 */
        /* JADX WARN: Type inference failed for: r6v2 */
        /* JADX WARN: Type inference failed for: r6v3 */
        /* JADX WARN: Type inference failed for: r6v57 */
        public void set(TL_stars.StarsTransaction r26, boolean r27, boolean r28) {
            Integer r9 = 0;
            long r4 = DialogObject.getPeerDialogId(r26.peer.peer);
            boolean r11 = r26.amount instanceof TL_stars.TL_starsTonAmount;
            int r6 = r26.flags;
            if ((131072 & r6) == 0) goto L6;
        L8:
            ?? r62 = false;
        L10:
            if (r4 == 0) goto L18;
            if (r26.stargift_upgrade == true) goto L18;
            if (r26.stargift_drop_original_details == true) goto L18;
            if (r26.posts_search == true) goto L18;
        L33:
            boolean r8 = true;
        L34:
            this.threeLines = r8;
            LinearLayout.LayoutParams r10 = this.titleTextViewParams;
            if (r8 == false) goto L37;
            int r82 = 0;
        L38:
            r10.bottomMargin = r82;
            TextView r83 = this.subtitleTextView;
            if (this.threeLines == false) goto L41;
            int r102 = 0;
        L42:
            r83.setVisibility(r102);
            TextView r84 = this.dateTextView;
            if (this.threeLines == false) goto L45;
            float r103 = 13.0f;
        L46:
            r84.setTextSize(1, r103);
            this.dateTextView.setText(LocaleController.formatShortDateTime(r26.date));
            if (r26.refund == false) goto L49;
            TextView r85 = this.dateTextView;
            int r21 = 2;
            r85.setText(TextUtils.concat(new CharSequence[]{r85.getText(), " — ", LocaleController.getString(R.string.StarsRefunded)}));
            ?? r19 = 0;
            int r20 = 1;
        L56:
            Runnable r3 = this.cancelCurrentGift;
            if (r3 == null) goto L59;
            r3.run();
            this.cancelCurrentGift = null;
        L59:
            this.imageView.setTranslationX(0.0f);
            this.imageView.setTranslationY(0.0f);
            this.imageView2.setVisibility(8);
            this.imageView.setRoundRadius(AndroidUtilities.dp(46.0f));
            if (r26.stargift_upgrade == false) goto L65;
            if (r26.stargift == null) goto L65;
            this.imageView.setImageDrawable(new StarGiftSheet.StarGiftDrawableIcon(this.imageView, r26.stargift, 46, 0.25f));
            this.titleTextView.setText(LocaleController.getString(R.string.Gift2TransactionUpgraded));
            this.subtitleTextView.setVisibility(8);
        L312:
            TL_stars.StarsAmount r32 = r26.amount;
            long r42 = r32.amount;
            if (r42 > 0) goto L333;
            if (r42 != 0) goto L320;
            if (r32.nanos > 0) goto L333;
        L320:
            if (r42 >= 0) goto L322;
        L328:
            this.amountTextView.setVisibility(0);
            this.amountTextView.setTextColor(Theme.getColor(Theme.key_color_red));
            TextView r33 = this.amountTextView;
            CharSequence r1 = StarsIntroActivity.formatStarsAmount(r26.amount);
            if (r11 == false) goto L331;
            SpannableString r43 = this.ton;
        L332:
            r33.setText(TextUtils.concat(new CharSequence[]{r1, " ", r43}));
        L338:
            this.needDivider = r28;
            setWillNotDraw(!r28);
            return;
        L331:
            r43 = this.star;
            goto L332
        L322:
            if (r42 == 0) goto L324;
        L326:
            this.amountTextView.setVisibility(8);
            goto L338
        L324:
            if (r32.nanos >= 0) goto L326;
        L333:
            this.amountTextView.setVisibility(0);
            this.amountTextView.setTextColor(Theme.getColor(Theme.key_color_green));
            TextView r34 = this.amountTextView;
            CharSequence r12 = StarsIntroActivity.formatStarsAmount(r26.amount);
            if (r11 == false) goto L336;
            SpannableString r44 = this.ton;
        L337:
            r34.setText(TextUtils.concat(new CharSequence[]{"+", r12, " ", r44}));
            goto L338
        L336:
            r44 = this.star;
        L65:
            if (r26.stargift_drop_original_details == false) goto L70;
            if (r26.stargift == null) goto L70;
            this.imageView.setImageDrawable(new StarGiftSheet.StarGiftDrawableIcon(this.imageView, r26.stargift, 46, 0.25f));
            this.titleTextView.setText(LocaleController.getString(R.string.Gift2TransactionRemovedDescription));
            this.subtitleTextView.setVisibility(8);
        L70:
            if (r26.posts_search == false) goto L73;
            this.imageView.setImageDrawable(getPlatformDrawable("search"));
            this.titleTextView.setText(LocaleController.getString(R.string.StarsTransactionPostsSearch));
            this.subtitleTextView.setVisibility(8);
            goto L312
        L73:
            if (r4 == 0) goto L272;
            if (UserObject.isService(r4) == false) goto L77;
            String r45 = LocaleController.getString(R.string.StarsTransactionUnknown);
            this.imageView.setImageDrawable(getPlatformDrawable("fragment"));
            String r35 = r45;
            int r23 = r19;
        L94:
            if (r26.stargift == null) goto L156;
            ImageReceiverSpan r46 = new ImageReceiverSpan(this.subtitleTextView, this.currentAccount, 16.0f);
            r46.setRoundRadius(4.0f);
            boolean r63 = r19;
            r46.enableShadow(r63);
            SpannableString r7 = new SpannableString("x");
            r7.setSpan(r46, r63 ? 1 : 0, r20, 33);
            StarsIntroActivity.setGiftImage(r46.imageReceiver, r26.stargift, 16);
            this.titleTextView.setText(r35);
            if (r26.offer == false) goto L109;
            SpannableStringBuilder r36 = new SpannableStringBuilder("x ");
            r36.setSpan(new AnimatedEmojiSpan(r26.stargift.getDocument(), this.subtitleTextView.getPaint().getFontMetricsInt()), 0, 1, 33);
            boolean r47 = r26.amount.negative();
            boolean r5 = r26.refund;
            if (r47 == true) goto L103;
            if (r5 == false) goto L101;
            int r48 = R.string.StarGiftTransactionGiftOfferRefund;
        L102:
            r36.append(LocaleController.getString(r48));
        L107:
            this.subtitleTextView.setText(r36);
            goto L312
        L101:
            r48 = R.string.StarGiftTransactionGiftSale;
            goto L102
        L103:
            if (r5 == false) goto L105;
            int r49 = R.string.StarGiftTransactionGiftSaleRefund;
        L106:
            r36.append(LocaleController.getString(r49));
            goto L107
        L105:
            r49 = R.string.StarGiftTransactionGiftOffer;
            goto L106
        L109:
            if (r26.stargift_resale == false) goto L122;
            SpannableStringBuilder r37 = new SpannableStringBuilder("x ");
            r37.setSpan(new AnimatedEmojiSpan(r26.stargift.getDocument(), this.subtitleTextView.getPaint().getFontMetricsInt()), 0, 1, 33);
            boolean r410 = r26.amount.negative();
            boolean r52 = r26.refund;
            if (r410 == true) goto L116;
            if (r52 == false) goto L114;
            int r411 = R.string.StarGiftTransactionGiftPurchaseRefund;
        L115:
            r37.append(LocaleController.getString(r411));
        L120:
            this.subtitleTextView.setText(r37);
            goto L312
        L114:
            r411 = R.string.StarGiftTransactionGiftSale;
            goto L115
        L116:
            if (r52 == false) goto L118;
            int r412 = R.string.StarGiftTransactionGiftSaleRefund;
        L119:
            r37.append(LocaleController.getString(r412));
            goto L120
        L118:
            r412 = R.string.StarGiftTransactionGiftPurchase;
            goto L119
        L122:
            if (r26.stargift_prepaid_upgrade == false) goto L125;
            TextView r38 = this.subtitleTextView;
            String r413 = LocaleController.getString(R.string.Gift2TransactionPrepaidUpgrade);
            CharSequence[] r53 = new CharSequence[3];
            r53[0] = r7;
            r53[1] = " ";
            r53[r21] = r413;
            r38.setText(TextUtils.concat(r53));
            goto L312
        L125:
            if ((r26.stargift instanceof TL_stars.TL_starGiftUnique) == false) goto L131;
            TextView r39 = this.subtitleTextView;
            if (r26.refund == false) goto L129;
            int r414 = R.string.StarGiftTransactionGiftTransferRefund;
        L130:
            r39.setText(LocaleController.getString(r414));
            goto L312
        L129:
            r414 = R.string.StarGiftTransactionGiftTransfer;
            goto L130
        L131:
            boolean r310 = r26.refund;
            TextView r415 = this.subtitleTextView;
            if (r310 == false) goto L145;
            if (r26.stargift_auction_bid == false) goto L137;
            int r311 = R.string.Gift2TransactionRefundedAuctionBid;
        L143:
            String r312 = LocaleController.getString(r311);
            CharSequence[] r54 = new CharSequence[3];
            r54[0] = r7;
            r54[1] = " ";
            r54[r21] = r312;
            r415.setText(TextUtils.concat(r54));
            goto L312
        L137:
            if (r26.amount.amount > 0) goto L139;
            r311 = R.string.Gift2TransactionRefundedConverted;
            goto L143
        L139:
            if (r26.stargift_upgrade == false) goto L141;
            r311 = R.string.Gift2TransactionRefundedUpgrade;
            goto L143
        L141:
            r311 = R.string.Gift2TransactionRefundedSent;
            goto L143
        L145:
            if (r26.stargift_auction_bid == false) goto L148;
            int r313 = R.string.Gift2TransactionAuctionBid;
        L154:
            String r314 = LocaleController.getString(r313);
            CharSequence[] r55 = new CharSequence[3];
            r55[0] = r7;
            r55[1] = " ";
            r55[r21] = r314;
            r415.setText(TextUtils.concat(r55));
            goto L312
        L148:
            if (r26.amount.amount <= 0) goto L151;
            r313 = R.string.Gift2TransactionConverted;
            goto L154
        L151:
            if (r26.stargift_upgrade == false) goto L153;
            r313 = R.string.Gift2TransactionUpgraded;
            goto L154
        L153:
            r313 = R.string.Gift2TransactionSent;
            goto L154
        L156:
            if (r26.subscription == false) goto L166;
            this.titleTextView.setText(r35);
            int r315 = r26.subscription_period;
            if (r315 != 2592000) goto L161;
            this.subtitleTextView.setVisibility(0);
            this.subtitleTextView.setText(LocaleController.getString(R.string.StarsTransactionSubscriptionMonthly));
            goto L312
        L161:
            if (r315 != 300) goto L163;
            String r316 = "5 minutes";
        L164:
            this.subtitleTextView.setVisibility(0);
            this.subtitleTextView.setText(String.format(Locale.US, "%s subscription fee", new Object[]{r316}));
            goto L312
        L163:
            r316 = "Minute";
            goto L164
        L166:
            if (r26.phonegroup_message == false) goto L177;
            this.titleTextView.setText(r35);
            TextView r317 = this.subtitleTextView;
            if (r23 == 0) goto L170;
            int r416 = 8;
        L171:
            r317.setVisibility(r416);
            TextView r318 = this.subtitleTextView;
            if (r26.reaction == false) goto L174;
            int r417 = R.string.StarsTransactionLiveStoryReactionFee;
        L175:
            r318.setText(LocaleController.getString(r417));
            goto L312
        L174:
            r417 = R.string.StarsTransactionLiveStoryMessageFee;
            goto L175
        L170:
            r416 = 0;
            goto L171
        L177:
            if (r26.paid_message == false) goto L184;
            this.titleTextView.setText(r35);
            TextView r319 = this.subtitleTextView;
            if (r23 == 0) goto L181;
            int r418 = 8;
        L182:
            r319.setVisibility(r418);
            this.subtitleTextView.setText(LocaleController.formatPluralStringComma("StarsTransactionMessageFee", r26.paid_messages));
            goto L312
        L181:
            r418 = 0;
            goto L182
        L184:
            if (r26.premium_gift == false) goto L190;
            this.titleTextView.setText(r35);
            TextView r320 = this.subtitleTextView;
            if (r23 == 0) goto L188;
            int r419 = 8;
        L189:
            r320.setVisibility(r419);
            this.subtitleTextView.setText(LocaleController.getString(R.string.StarsTransactionPremiumGift));
            goto L312
        L188:
            r419 = 0;
            goto L189
        L190:
            if (r62 == false) goto L197;
            this.titleTextView.setText(r35);
            TextView r321 = this.subtitleTextView;
            if (r23 == 0) goto L194;
            int r420 = 8;
        L195:
            r321.setVisibility(r420);
            this.subtitleTextView.setText(LocaleController.formatString(R.string.StarTransactionCommission, new Object[]{AffiliateProgramFragment.percents(r26.starref_commission_permille)}));
            goto L312
        L194:
            r420 = 0;
            goto L195
        L197:
            if (r26.gift == false) goto L204;
            this.titleTextView.setText(r35);
            TextView r322 = this.subtitleTextView;
            if (r23 == 0) goto L201;
            int r421 = 8;
        L202:
            r322.setVisibility(r421);
            this.subtitleTextView.setText(LocaleController.getString(R.string.StarsGiftReceived));
            goto L312
        L201:
            r421 = 0;
            goto L202
        L204:
            if ((r26.flags & 8192) == 0) goto L211;
            this.titleTextView.setText(r35);
            TextView r323 = this.subtitleTextView;
            if (r23 == 0) goto L208;
            int r422 = 8;
        L209:
            r323.setVisibility(r422);
            this.subtitleTextView.setText(LocaleController.getString(R.string.StarsGiveawayPrizeReceived));
            goto L312
        L208:
            r422 = 0;
            goto L209
        L211:
            if (r26.reaction == false) goto L218;
            this.titleTextView.setText(r35);
            TextView r324 = this.subtitleTextView;
            if (r23 == 0) goto L215;
            int r423 = 8;
        L216:
            r324.setVisibility(r423);
            this.subtitleTextView.setText(LocaleController.getString(R.string.StarsReactionsSent));
            goto L312
        L215:
            r423 = 0;
            goto L216
        L218:
            if (r26.extended_media.isEmpty() == true) goto L252;
            TextView r424 = this.titleTextView;
            if (r27 == false) goto L223;
            r424.setText(r35);
            this.subtitleTextView.setVisibility(0);
            this.subtitleTextView.setText(LocaleController.getString(R.string.StarMediaPurchase));
        L228:
            this.imageViewCount = 0;
            int r325 = 0;
        L230:
            if (r325 >= Math.min(r21, r26.extended_media.size())) goto L244;
            TLRPC.MessageMedia r425 = r26.extended_media.get(r325);
            if (r325 != 0) goto L234;
            BackupImageView r56 = this.imageView;
        L235:
            r56.setRoundRadius(AndroidUtilities.dp(12.0f));
            if ((r425 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L240;
            ImageLocation r426 = ImageLocation.getForPhoto(FileLoader.getClosestPhotoSizeWithSize(r425.photo.sizes, AndroidUtilities.dp(46.0f), true), r425.photo);
        L243:
            r56.setVisibility(0);
            Integer r104 = r9;
            r56.setImage(r426, "46_46", null, null, null, r104);
            r9 = r104;
            this.imageViewCount++;
            r325 = r325 + 1;
            r21 = 2;
            goto L230
        L240:
            if ((r425 instanceof TLRPC.TL_messageMediaDocument) == false) goto L242;
            r426 = ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r425.document.thumbs, AndroidUtilities.dp(46.0f), true), r425.document);
            goto L243
        L242:
            r426 = null;
            goto L243
        L234:
            r56 = this.imageView2;
            goto L235
        L244:
            int r326 = 0;
        L246:
            if (r326 >= this.imageViewCount) goto L312;
            if (r326 != 0) goto L249;
            BackupImageView r427 = this.imageView;
        L250:
            float r72 = r326;
            r427.setTranslationX(AndroidUtilities.dp(2.0f) + ((r72 - (this.imageViewCount / 2.0f)) * AndroidUtilities.dp(4.33f)));
            r427.setTranslationY((r72 - (this.imageViewCount / 2.0f)) * AndroidUtilities.dp(4.33f));
            r326 = r326 + 1;
            goto L246
        L249:
            r427 = this.imageView2;
            goto L250
        L223:
            r424.setText(LocaleController.getString(R.string.StarMediaPurchase));
            TextView r428 = this.subtitleTextView;
            if (r23 == 0) goto L226;
            int r57 = 8;
        L227:
            r428.setVisibility(r57);
            this.subtitleTextView.setText(r35);
            goto L228
        L226:
            r57 = 0;
            goto L227
        L252:
            if (r26.photo == null) goto L262;
            ImageReceiverSpan r429 = new ImageReceiverSpan(this.subtitleTextView, this.currentAccount, 14.0f);
            r429.setRoundRadius(4.0f);
            r429.enableShadow(false);
            SpannableString r122 = new SpannableString("x");
            r122.setSpan(r429, 0, 1, 33);
            r429.imageReceiver.setImage(ImageLocation.getForWebFile(WebFile.createWithWebDocument(r26.photo)), "14_14", null, null, r9, 0);
            this.titleTextView.setText(r35);
            TextView r327 = this.subtitleTextView;
            if (r23 == 0) goto L256;
            int r430 = 8;
        L257:
            r327.setVisibility(r430);
            TextView r328 = this.subtitleTextView;
            String r86 = r26.title;
            if (r86 != null) goto L261;
            r86 = _UrlKt.FRAGMENT_ENCODE_SET;
        L261:
            r328.setText(Emoji.replaceEmoji(TextUtils.concat(new CharSequence[]{r122, " ", r86}), this.subtitleTextView.getPaint().getFontMetricsInt(), false));
            goto L312
        L256:
            r430 = 0;
            goto L257
        L262:
            this.titleTextView.setText(r35);
            TextView r329 = this.subtitleTextView;
            if (r23 == 0) goto L265;
            int r431 = 8;
        L266:
            r329.setVisibility(r431);
            TextView r330 = this.subtitleTextView;
            String r87 = r26.title;
            if (r87 != null) goto L270;
            r87 = _UrlKt.FRAGMENT_ENCODE_SET;
        L270:
            r330.setText(Emoji.replaceEmoji(r87, r330.getPaint().getFontMetricsInt(), false));
            goto L312
        L265:
            r431 = 0;
            goto L266
        L77:
            int r331 = this.currentAccount;
            if (r4 < 0) goto L85;
            TLRPC.User r332 = MessagesController.getInstance(r331).getUser(Long.valueOf(r4));
            if (r332 != null) goto L82;
            int r432 = r20;
        L83:
            this.avatarDrawable.setInfo(r332);
            this.imageView.setForUserOrChat(r332, this.avatarDrawable);
            r35 = UserObject.getUserName(r332);
        L84:
            r23 = r432;
            goto L94
        L82:
            r432 = r19;
            goto L83
        L85:
            TLRPC.Chat r333 = MessagesController.getInstance(r331).getChat(Long.valueOf(-r4));
            if (r333 != null) goto L88;
            r432 = r20;
        L89:
            this.avatarDrawable.setInfo(r333);
            this.imageView.setForUserOrChat(r333, this.avatarDrawable);
            if (r333 != null) goto L92;
            r35 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L84
        L92:
            r35 = r333.title;
            goto L84
        L88:
            r432 = r19;
            goto L89
        L272:
            if (r26.floodskip == false) goto L274;
            this.titleTextView.setText(LocaleController.getString(R.string.StarsTransactionFloodskip));
            this.subtitleTextView.setText(LocaleController.formatPluralStringComma("StarsTransactionFloodskipMessages", r26.floodskip_number));
            this.imageView.setImageDrawable(getPlatformDrawable("api"));
            goto L312
        L274:
            TL_stars.StarsTransactionPeer r433 = r26.peer;
            if ((r433 instanceof TL_stars.TL_starsTransactionPeerAppStore) == false) goto L278;
            this.titleTextView.setText(LocaleController.getString(R.string.StarsTransactionInApp));
            this.imageView.setImageDrawable(getPlatformDrawable(CredentialsData.CREDENTIALS_TYPE_IOS));
            goto L312
        L278:
            if ((r433 instanceof TL_stars.TL_starsTransactionPeerPlayMarket) == false) goto L281;
            this.titleTextView.setText(LocaleController.getString(R.string.StarsTransactionInApp));
            this.imageView.setImageDrawable(getPlatformDrawable("android"));
            goto L312
        L281:
            if ((r433 instanceof TL_stars.TL_starsTransactionPeerFragment) == false) goto L303;
            boolean r434 = r26.gift;
            TextView r58 = this.titleTextView;
            if (r434 == false) goto L289;
            r58.setText(LocaleController.getString(R.string.StarsGiftReceived));
            TextView r435 = this.subtitleTextView;
            if (r11 == false) goto L287;
            int r59 = R.string.StarsTransactionTONFromFragment;
        L288:
            r435.setText(LocaleController.getString(r59));
            this.subtitleTextView.setVisibility(0);
        L301:
            this.imageView.setImageDrawable(getPlatformDrawable("fragment"));
            goto L312
        L287:
            r59 = R.string.StarsTransactionUnknown;
            goto L288
        L289:
            if (r27 == true) goto L299;
            boolean r436 = r26.refund;
            TL_stars.StarsAmount r64 = r26.amount;
            if (r436 == false) goto L296;
            if (r64.positive() == true) goto L299;
        L298:
            int r437 = R.string.StarsTransactionFragment;
        L300:
            r58.setText(LocaleController.getString(r437));
            goto L301
        L296:
            if (r64.negative() == false) goto L298;
        L299:
            r437 = R.string.StarsTransactionWithdrawFragment;
            goto L300
        L303:
            if ((r433 instanceof TL_stars.TL_starsTransactionPeerPremiumBot) == false) goto L306;
            this.titleTextView.setText(LocaleController.getString(R.string.StarsTransactionBot));
            this.imageView.setImageDrawable(getPlatformDrawable("premiumbot"));
            goto L312
        L306:
            if ((r433 instanceof TL_stars.TL_starsTransactionPeerUnsupported) == false) goto L308;
            this.titleTextView.setText(LocaleController.getString(R.string.StarsTransactionUnsupported));
            this.imageView.setImageDrawable(getPlatformDrawable("?"));
            goto L312
        L308:
            boolean r334 = r433 instanceof TL_stars.TL_starsTransactionPeerAds;
            TextView r438 = this.titleTextView;
            if (r334 == false) goto L311;
            r438.setText(LocaleController.getString(R.string.StarsTransactionAds));
            this.imageView.setImageDrawable(getPlatformDrawable("ads"));
            goto L312
        L311:
            r438.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.imageView.setImageDrawable(null);
            goto L312
        L49:
            r21 = 2;
            if (r26.failed == false) goto L53;
            TextView r88 = this.dateTextView;
            r20 = 1;
            r88.setText(TextUtils.concat(new CharSequence[]{r88.getText(), " — ", LocaleController.getString(R.string.StarsFailed)}));
        L52:
            r19 = 0;
            goto L56
        L53:
            r20 = 1;
            if (r26.pending == false) goto L52;
            TextView r89 = this.dateTextView;
            r19 = 0;
            r89.setText(TextUtils.concat(new CharSequence[]{r89.getText(), " — ", LocaleController.getString(R.string.StarsPending)}));
            goto L56
        L45:
            r103 = 14.0f;
            goto L46
        L41:
            r102 = 8;
            goto L42
        L37:
            r82 = AndroidUtilities.dp(4.33f);
        L18:
            if (r26.subscription == true) goto L33;
            if (r26.floodskip == true) goto L33;
            if (r26.stargift == null) goto L28;
            if (r26.stargift_upgrade == true) goto L28;
            if (r26.stargift_drop_original_details == false) goto L33;
        L28:
            if (r26.gift == true) goto L30;
        L32:
            r8 = false;
            goto L34
        L30:
            if ((r26.peer instanceof TL_stars.TL_starsTransactionPeerFragment) == false) goto L32;
        L6:
            if ((r6 & 65536) == 0) goto L8;
            r62 = true;
            goto L10
        }
    }

    public static class StarsTransactionsLayout extends LinearLayout implements NotificationCenter.NotificationCenterDelegate {
        private final PageAdapter adapter;
        private final long bot_id;
        private final int currentAccount;
        private final ViewPagerFixed.TabsView tabsView;
        private final boolean ton;
        private final ViewPagerFixed viewPager;

        public static class Page extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
            private final long bot_id;
            private final int currentAccount;
            private final UniversalRecyclerView listView;
            private final Runnable loadTransactionsRunnable;
            private final Theme.ResourcesProvider resourcesProvider;
            private final boolean ton;
            private final int type;

            /* renamed from: org.telegram.ui.Stars.StarsIntroActivity$StarsTransactionsLayout$Page$1, reason: invalid class name */
            public class AnonymousClass1 extends RecyclerView.OnScrollListener {
                final /* synthetic */ Page this$0;

                public AnonymousClass1(Page r1) {
                    this.this$0 = r1;
                }

                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView r1, int r2, int r3) {
                    if (Page.m19204$$Nest$fgetlistView(this.this$0).canScrollVertically(1) == true) goto L5;
                L8:
                    Page.m19205$$Nest$fgetloadTransactionsRunnable(this.this$0).run();
                    return;
                L5:
                    if (this.this$0.isLoadingVisible() == true) goto L8;
                }
            }

            public static /* synthetic */ void $r8$lambda$BdY5IePEJVmjFRMp_wLAQjLeH9U(long r2, int r4, int r5, boolean r6) {
                if (r2 == 0) goto L6;
                BotStarsController.getInstance(r4).loadTransactions(r2, r5);
                return;
            L6:
                StarsController.getInstance(r4, r6).loadTransactions(r5);
            }

            public static /* synthetic */ void $r8$lambda$cX17L0_64AzQgr4ZD8L8Mmg_T0E(Page r0, ArrayList r1, UniversalAdapter r2) {
                r0.fillItems(r1, r2);
            }

            /* renamed from: $r8$lambda$sFEcM1-z2qmflPUDc8Px-lXhV7Y, reason: not valid java name */
            public static /* synthetic */ void m19203$r8$lambda$sFEcM1z2qmflPUDc8PxlXhV7Y(Page r0, UItem r1, View r2, int r3, float r4, float r5) {
                r0.onClick(r1, r2, r3, r4, r5);
            }

            /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
            public static /* bridge */ /* synthetic */ UniversalRecyclerView m19204$$Nest$fgetlistView(Page r0) {
                return r0.listView;
            }

            /* renamed from: -$$Nest$fgetloadTransactionsRunnable, reason: not valid java name */
            public static /* bridge */ /* synthetic */ Runnable m19205$$Nest$fgetloadTransactionsRunnable(Page r0) {
                return r0.loadTransactionsRunnable;
            }

            public Page(Context r10, boolean r11, long r12, int r14, int r15, int r16, Theme.ResourcesProvider r17) {
                super(r10);
                this.type = r14;
                this.ton = r11;
                this.currentAccount = r15;
                this.bot_id = r12;
                this.resourcesProvider = r17;
                this.loadTransactionsRunnable = new StarsIntroActivity$StarsTransactionsLayout$Page$$ExternalSyntheticLambda0(r12, r15, r14, r11);
                UniversalRecyclerView r0 = new UniversalRecyclerView(r10, r15, r16, true, new StarsIntroActivity$StarsTransactionsLayout$Page$$ExternalSyntheticLambda1(this), new StarsIntroActivity$StarsTransactionsLayout$Page$$ExternalSyntheticLambda2(this), null, r17);
                this.listView = r0;
                addView(r0, LayoutHelper.createFrame(-1, -1.0f));
                r0.setOnScrollListener(new AnonymousClass1(this));
            }

            private void fillItems(ArrayList<UItem> r7, UniversalAdapter r8) {
                long r0 = this.bot_id;
                int r02 = this.currentAccount;
                int r1 = 0;
                if (r0 == 0) goto L11;
                BotStarsController r82 = BotStarsController.getInstance(r02);
                ArrayList<TL_stars.StarsTransaction> r03 = r82.getTransactions(this.bot_id, this.type);
                int r3 = r03.size();
            L5:
                if (r1 >= r3) goto L8;
                TL_stars.StarsTransaction r4 = r03.get(r1);
                r1 = r1 + 1;
                r7.add(StarsTransactionView.Factory.asTransaction(r4, true));
                goto L5
            L8:
                if (r82.didFullyLoadTransactions(this.bot_id, this.type) == true) goto L20;
                r7.add(UItem.asFlicker(r7.size(), 7));
                r7.add(UItem.asFlicker(r7.size(), 7));
                r7.add(UItem.asFlicker(r7.size(), 7));
                return;
            L20:
                return;
            L11:
                StarsController r83 = StarsController.getInstance(r02, this.ton);
                ArrayList<TL_stars.StarsTransaction> r04 = r83.transactions[this.type];
                int r32 = r04.size();
                int r42 = 0;
            L12:
                if (r42 >= r32) goto L15;
                TL_stars.StarsTransaction r5 = r04.get(r42);
                r42 = r42 + 1;
                r7.add(StarsTransactionView.Factory.asTransaction(r5, false));
                goto L12
            L15:
                if (r83.didFullyLoadTransactions(this.type) == true) goto L21;
                r7.add(UItem.asFlicker(r7.size(), 7));
                r7.add(UItem.asFlicker(r7.size(), 7));
                r7.add(UItem.asFlicker(r7.size(), 7));
                return;
            }

            private void onClick(UItem r8, View r9, int r10, float r11, float r12) {
                if ((r8.object instanceof TL_stars.StarsTransaction) == false) goto L6;
                StarsIntroActivity.showTransactionSheet(getContext(), false, 0, this.currentAccount, (TL_stars.StarsTransaction) r8.object, this.resourcesProvider);
                return;
            }

            @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
            public void didReceivedNotification(int r4, int r5, Object... r6) {
                if (r4 != NotificationCenter.starTransactionsLoaded) goto L11;
                this.listView.adapter.update(true);
                if (this.listView.canScrollVertically(1) == true) goto L7;
            L8:
                this.loadTransactionsRunnable.run();
                return;
            L7:
                if (isLoadingVisible() == true) goto L8;
                return;
            L11:
                if (r4 == NotificationCenter.botStarsTransactionsLoaded) goto L13;
                return;
            L13:
                if (((Long) r6[0]).longValue() != this.bot_id) goto L18;
                this.listView.adapter.update(true);
                return;
            }

            public boolean isLoadingVisible() {
                int r1 = 0;
            L4:
                if (r1 >= this.listView.getChildCount()) goto L10;
                if ((this.listView.getChildAt(r1) instanceof FlickerLoadingView) == true) goto L7;
                r1 = r1 + 1;
                goto L4
            L7:
                return true;
            L10:
                return false;
            }

            @Override // android.view.ViewGroup, android.view.View
            public void onAttachedToWindow() {
                super.onAttachedToWindow();
                long r0 = this.bot_id;
                int r1 = this.currentAccount;
                if (r0 == 0) goto L5;
                NotificationCenter.getInstance(r1).addObserver(this, NotificationCenter.botStarsTransactionsLoaded);
            L6:
                this.listView.adapter.update(false);
                return;
            L5:
                NotificationCenter.getInstance(r1).addObserver(this, NotificationCenter.starTransactionsLoaded);
                goto L6
            }

            @Override // android.view.ViewGroup, android.view.View
            public void onDetachedFromWindow() {
                super.onDetachedFromWindow();
                long r0 = this.bot_id;
                int r1 = this.currentAccount;
                if (r0 == 0) goto L6;
                NotificationCenter.getInstance(r1).removeObserver(this, NotificationCenter.botStarsTransactionsLoaded);
                return;
            L6:
                NotificationCenter.getInstance(r1).removeObserver(this, NotificationCenter.starTransactionsLoaded);
            }
        }

        public static class PageAdapter extends ViewPagerFixed.Adapter {
            private final long bot_id;
            private final int classGuid;
            private final Context context;
            private final int currentAccount;
            private final ArrayList<UItem> items;
            private final Theme.ResourcesProvider resourcesProvider;
            private final boolean ton;

            public PageAdapter(Context r2, int r3, boolean r4, long r5, int r7, Theme.ResourcesProvider r8) {
                this.items = new ArrayList();
                this.context = r2;
                this.currentAccount = r3;
                this.ton = r4;
                this.classGuid = r7;
                this.resourcesProvider = r8;
                this.bot_id = r5;
                fill();
            }

            @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
            public void bindView(View r1, int r2, int r3) {
            }

            @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
            public View createView(int r10) {
                return new Page(this.context, this.ton, this.bot_id, r10, this.currentAccount, this.classGuid, this.resourcesProvider);
            }

            public void fill() {
                this.items.clear();
                long r0 = this.bot_id;
                int r1 = this.currentAccount;
                if (r0 != 0) goto L11;
                StarsController r02 = StarsController.getInstance(r1, this.ton);
                this.items.add(UItem.asSpace(0));
                if (r02.hasTransactions(1) == false) goto L8;
                this.items.add(UItem.asSpace(1));
            L8:
                if (r02.hasTransactions(2) == false) goto L18;
                this.items.add(UItem.asSpace(2));
                return;
            L18:
                return;
            L11:
                BotStarsController r03 = BotStarsController.getInstance(r1);
                this.items.add(UItem.asSpace(0));
                if (r03.hasTransactions(this.bot_id, 1) == false) goto L15;
                this.items.add(UItem.asSpace(1));
            L15:
                if (r03.hasTransactions(this.bot_id, 2) == false) goto L19;
                this.items.add(UItem.asSpace(2));
                return;
            }

            @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
            public int getItemCount() {
                return this.items.size();
            }

            @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
            public /* bridge */ /* synthetic */ CharSequence getItemTitle(int r1) {
                return getItemTitle(r1);
            }

            @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
            public String getItemTitle(int r1) {
                int r0 = getItemViewType(r1);
                if (r0 == 0) goto L15;
                if (r0 == 1) goto L13;
                if (r0 == 2) goto L11;
                return _UrlKt.FRAGMENT_ENCODE_SET;
            L11:
                return LocaleController.getString(R.string.StarsTransactionsOutgoing);
            L13:
                return LocaleController.getString(R.string.StarsTransactionsIncoming);
            L15:
                return LocaleController.getString(R.string.StarsTransactionsAll);
            }

            @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
            public int getItemViewType(int r2) {
                if (r2 >= 0) goto L4;
                return 0;
            L4:
                if (r2 < this.items.size()) goto L7;
                return 0;
            L7:
                return this.items.get(r2).intValue;
            }
        }

        public StarsTransactionsLayout(Context r11, int r12, boolean r13, long r14, int r16, Theme.ResourcesProvider r17) {
            super(r11);
            this.currentAccount = r12;
            this.ton = r13;
            this.bot_id = r14;
            setOrientation(1);
            ViewPagerFixed r9 = new ViewPagerFixed(r11);
            this.viewPager = r9;
            PageAdapter r0 = new PageAdapter(r11, r12, r13, r14, r16, r17);
            this.adapter = r0;
            r9.setAdapter(r0);
            ViewPagerFixed.TabsView r122 = r9.createTabsView(true, 3);
            this.tabsView = r122;
            View r132 = new View(r11);
            r132.setBackgroundColor(Theme.getColor(Theme.key_divider, r17));
            addView(r122, LayoutHelper.createLinear(-1, 48));
            addView(r132, LayoutHelper.createLinear(-1.0f, 1.0f / AndroidUtilities.density));
            addView(r9, LayoutHelper.createLinear(-1, -1));
            setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, r17));
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r1, int r2, Object... r3) {
            if (r1 != NotificationCenter.starTransactionsLoaded) goto L6;
            this.adapter.fill();
            this.viewPager.fillTabs(true);
            return;
        }

        public RecyclerListView getCurrentListView() {
            View r1 = this.viewPager.getCurrentView();
            if ((r1 instanceof Page) == true) goto L7;
            return null;
        L7:
            return Page.m19204$$Nest$fgetlistView((Page) r1);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            this.adapter.fill();
            this.viewPager.fillTabs(false);
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starTransactionsLoaded);
            super.onAttachedToWindow();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starTransactionsLoaded);
            super.onDetachedFromWindow();
        }
    }

    /* renamed from: $r8$lambda$-1gNBS7abUvYE_OQhlnhhwW5IvY, reason: not valid java name */
    public static /* synthetic */ void m19155$r8$lambda$1gNBS7abUvYE_OQhlnhhwW5IvY(BottomSheet[] r2, Context r3, int r4, long r5, Theme.ResourcesProvider r7, TL_payments.connectedBotStarRef r8) {
        r2[0].dismiss();
        ChannelAffiliateProgramsFragment.showShareAffiliateAlert(r3, r4, r8, r5, r7);
    }

    /* renamed from: $r8$lambda$-RJA1IKlB-ZwzeHkVpaZ9gg-EB4, reason: not valid java name */
    public static /* synthetic */ void m19156$r8$lambda$RJA1IKlBZwzeHkVpaZ9ggEB4(BottomSheet[] r1, TL_stars.StarsTransaction r2, long r3) {
        r1[0].dismiss();
        BaseFragment r12 = LaunchActivity.getSafeLastFragment();
        if (r12 != null) goto L5;
        return;
    L5:
        if ((r2.flags & 8192) == 0) goto L8;
        r12.presentFragment(ChatActivity.of(r3, r2.giveaway_post_id));
        return;
    L8:
        r12.presentFragment(ChatActivity.of(r3));
    }

    /* renamed from: $r8$lambda$-gG9gtP2tdwiOVMm9QYQ34tJszk, reason: not valid java name */
    public static /* synthetic */ void m19157$r8$lambda$gG9gtP2tdwiOVMm9QYQ34tJszk(StarsIntroActivity r0, boolean r1) {
        r0.lambda$updateButtonsLayouts$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$0nn0Z8n1ThaLHuZ7S1hDAreDjCQ(BottomSheet[] r1, long r2) {
        r1[0].dismiss();
        BaseFragment r12 = LaunchActivity.getSafeLastFragment();
        if (r12 == null) goto L6;
        r12.presentFragment(ProfileActivity.of(r2));
        return;
    }

    public static /* synthetic */ void $r8$lambda$1W7CtXtcUz2fAWNaJvsTOrY3G18(boolean[] r2, Utilities.Callback2 r3, EditTextBoldCursor r4, ButtonWithCounterView r5, BottomSheet[] r6, View r7) {
        if (r2[0] == false) goto L5;
        return;
    L5:
        if (r3 == null) goto L12;
        String r0 = r4.getText().toString();
        r2[0] = true;
        r5.setLoading(true);
        if (TextUtils.isEmpty(r0) == false) goto L9;
        long r02 = 0;
    L10:
        r3.run(Long.valueOf(r02), new StarsIntroActivity$$ExternalSyntheticLambda79(r4, r6));
        return;
    L9:
        r02 = Long.parseLong(r0);
        goto L10
    L12:
        AndroidUtilities.hideKeyboard(r4);
        r6[0].dismiss();
    }

    /* renamed from: $r8$lambda$1aYKVcSuG2LSE-LCq-k6YGGdDaI, reason: not valid java name */
    public static /* synthetic */ void m19158$r8$lambda$1aYKVcSuG2LSELCqk6YGGdDaI(ButtonWithCounterView r1, BottomSheet[] r2, int r3, long r4) {
        r1.setLoading(false);
        BottomSheet r12 = r2[0];
        if (r12 == null) goto L5;
        r12.dismiss();
    L5:
        StarsController.getInstance(r3).invalidateSubscriptions(true);
        BaseFragment r13 = LaunchActivity.getSafeLastFragment();
        if (r13 != null) goto L8;
        return;
    L8:
        r13.presentFragment(ChatActivity.of(r4));
    }

    public static /* synthetic */ void $r8$lambda$3DQ58Z8O7JOUMF1JDyPIPT8HuYQ(StarsBalanceView r2, View r3) {
        if (r2.lastBalance <= 0) goto L10;
        BaseFragment r22 = LaunchActivity.getLastFragment();
        if (r22 == null) goto L9;
        BaseFragment.BottomSheetParams r32 = new BaseFragment.BottomSheetParams();
        r32.transitionFromLeft = true;
        r32.allowNestedScroll = false;
        r22.showAsSheet(new StarsIntroActivity(), r32);
        return;
    L9:
        return;
    }

    public static /* synthetic */ void $r8$lambda$6hn_iMyOY89r07iYM9YWg76FlrU(BottomSheet[] r1, TL_stars.StarsTransaction r2, long r3) {
        r1[0].dismiss();
        BaseFragment r12 = LaunchActivity.getSafeLastFragment();
        if (r12 != null) goto L5;
        return;
    L5:
        if ((r2.flags & 8192) == 0) goto L8;
        r12.presentFragment(ChatActivity.of(r3, r2.giveaway_post_id));
        return;
    L8:
        r12.presentFragment(ChatActivity.of(r3));
    }

    public static /* synthetic */ void $r8$lambda$6w0lhPHcMh3iU9irDI7ZKSUKmPI(Context r1) {
        Browser.openUrl(r1, LocaleController.getString(R.string.PaidContentInfoLink));
    }

    /* renamed from: $r8$lambda$6yoR4Iq9GbT6hgJd2aiDqi9-OdE, reason: not valid java name */
    public static /* synthetic */ void m19159$r8$lambda$6yoR4Iq9GbT6hgJd2aiDqi9OdE(TextView r4, TL_stars.StarGift r5) {
        if (r5 != null) goto L4;
        return;
    L4:
        boolean r0 = r5 instanceof TL_stars.TL_starGiftUnique;
        int r1 = r5.availability_remains;
        if (r0 == false) goto L11;
        if (r1 > 0) goto L8;
        String r52 = LocaleController.formatPluralStringComma("Gift2QuantityIssuedNone", r5.availability_total);
    L9:
        r4.setText(r52);
        return;
    L8:
        r52 = LocaleController.formatPluralStringComma("Gift2QuantityIssued1", r5.availability_issued) + LocaleController.formatPluralStringComma("Gift2QuantityIssued2", r5.availability_total);
        goto L9
    L11:
        int r53 = r5.availability_total;
        if (r1 > 0) goto L14;
        String r54 = LocaleController.formatPluralStringComma("Gift2Availability2ValueNone", r53);
    L15:
        r4.setText(r54);
        return;
    L14:
        r54 = LocaleController.formatPluralStringComma("Gift2Availability4Value", r1, new Object[]{LocaleController.formatNumber(r53, ',')});
        goto L15
    }

    public static /* synthetic */ void $r8$lambda$707VQncvlJFDhOIr6j36GAjMikU(BaseFragment r2, long r3, TLRPC.Chat r5) {
        BulletinFactory.of(r2).createSimpleBulletin(R.raw.stars_send, LocaleController.getString(R.string.StarsSubscriptionCompleted), AndroidUtilities.replaceTags(LocaleController.formatPluralString("StarsSubscriptionCompletedText", (int) r3, new Object[]{r5.title}))).show(true);
    }

    public static /* synthetic */ void $r8$lambda$7PL3orQMT_NP3BhLDG9qZ7s3bhs(Context r2, BottomSheet[] r3) {
        StarAppsSheet r0 = new StarAppsSheet(r2);
        if (AndroidUtilities.isTablet() == false) goto L5;
    L11:
        r0.show();
        return;
    L5:
        if (AndroidUtilities.hasDialogOnTop(r3[0].attachedFragment) == true) goto L11;
        BottomSheet r22 = r3[0];
        if (r22 == null) goto L11;
        BaseFragment r23 = r22.attachedFragment;
        if (r23 == null) goto L11;
        r0.makeAttached(r23);
        goto L11
    }

    /* renamed from: $r8$lambda$7ZsrleWoA0mADFYg-9aN5w130ds, reason: not valid java name */
    public static /* synthetic */ void m19160$r8$lambda$7ZsrleWoA0mADFYg9aN5w130ds(BottomSheet[] r1, long r2, TL_stories.Boost r4) {
        BottomSheet r12 = r1[0];
        if (r12 == null) goto L5;
        r12.dismiss();
    L5:
        BaseFragment r13 = LaunchActivity.getSafeLastFragment();
        if (r13 != null) goto L8;
        return;
    L8:
        r13.presentFragment(ChatActivity.of(r2, r4.giveaway_msg_id));
    }

    /* renamed from: $r8$lambda$7evpbfi-zB9nLlpHaSFnru9oFCc, reason: not valid java name */
    public static /* synthetic */ void m19161$r8$lambda$7evpbfizB9nLlpHaSFnru9oFCc(Context r1) {
        Browser.openUrl(r1, LocaleController.getString(R.string.StarsTOSLink));
    }

    public static /* synthetic */ void $r8$lambda$814H3pfYzoktoRr3Odn3tqvBzyQ(StarsIntroActivity r0, View r1) {
        r0.lambda$createView$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$8D3iPuiORhRvfU_OginKQroc44M(BottomSheet[] r2, long r3, long r5) {
        r2[0].dismiss();
        BaseFragment r22 = LaunchActivity.getSafeLastFragment();
        if (r22 == null) goto L9;
        Bundle r0 = new Bundle();
        r0.putLong("user_id", r3);
        if (r3 != r5) goto L7;
        r0.putBoolean("my_profile", true);
    L7:
        r0.putBoolean("open_gifts", true);
        r22.presentFragment(new ProfileActivity(r0));
        return;
    }

    /* renamed from: $r8$lambda$9YDO1oz7yQK_v-0iAyFCkVRyg_g, reason: not valid java name */
    public static /* synthetic */ void m19162$r8$lambda$9YDO1oz7yQK_v0iAyFCkVRyg_g(ButtonWithCounterView r1, BottomSheet[] r2, int r3, TLObject r4, String r5) {
        r1.setLoading(false);
        BottomSheet r12 = r2[0];
        if (r12 == null) goto L5;
        r12.dismiss();
    L5:
        StarsController.getInstance(r3).invalidateSubscriptions(true);
        BaseFragment r13 = LaunchActivity.getSafeLastFragment();
        if (r13 == null) goto L9;
        BulletinFactory.of(r13).createUsersBulletin(Collections.singletonList(r4), LocaleController.getString(R.string.StarsSubscriptionRenewedToast), AndroidUtilities.replaceTags(LocaleController.formatString(R.string.StarsSubscriptionRenewedToastText, new Object[]{r5}))).show(false);
        return;
    }

    public static /* synthetic */ void $r8$lambda$A1w6RPPIPTFcktB9L_VREHtG9rc(BottomSheet[] r0, View r1) {
        r0[0].dismiss();
    }

    public static /* synthetic */ void $r8$lambda$A5ctNUNnrw_YIggmHmNtCCZ1cIU(Context r1) {
        Browser.openUrl(r1, LocaleController.getString(R.string.StarsTOSLink));
    }

    public static /* synthetic */ void $r8$lambda$AUr_XEXXRDvLDtJmB1NEsAk5IkI(int r4, long r5, String r7, Long r8) {
        if ("paid".equals(r7) == true) goto L5;
        return;
    L5:
        if (r8.longValue() == 0) goto L9;
        AndroidUtilities.runOnUIThread(new StarsIntroActivity$$ExternalSyntheticLambda98(r8, r4, r5));
        return;
    }

    public static /* synthetic */ void $r8$lambda$BNFwSzyKJq9kxZpPOkbeIzIYmIk(int r0, NotificationCenter.NotificationCenterDelegate r1, DialogInterface r2) {
        NotificationCenter.getInstance(r0).removeObserver(r1, NotificationCenter.starSubscriptionsLoaded);
    }

    public static /* synthetic */ void $r8$lambda$CG1atoraQ0frx_CFFt99WDi52h4(Utilities.Callback2 r1, SellGiftEnterPriceSheet[] r2, AmountUtils$Amount r3) {
        r1.run(r3, new StarsIntroActivity$$ExternalSyntheticLambda93(r2));
    }

    public static /* synthetic */ void $r8$lambda$EvGo8SDEtLNryPvpxOeqUYlVN2U(BottomSheet[] r2, long r3, long r5) {
        r2[0].dismiss();
        BaseFragment r22 = LaunchActivity.getSafeLastFragment();
        if (r22 == null) goto L9;
        Bundle r0 = new Bundle();
        r0.putLong("user_id", r3);
        if (r3 != r5) goto L7;
        r0.putBoolean("my_profile", true);
    L7:
        r0.putBoolean("open_gifts", true);
        r22.presentFragment(new ProfileActivity(r0));
        return;
    }

    public static /* synthetic */ void $r8$lambda$FSHDo5_r5iae7kqpOxT0Ppad5TU(EditTextBoldCursor r0, BottomSheet[] r1) {
        AndroidUtilities.hideKeyboard(r0);
        r1[0].dismiss();
    }

    public static /* synthetic */ void $r8$lambda$FveBt0RZL2ASfWZl8Ig9iYOYCbY(Runnable r0, DialogInterface r1) {
        if (r0 == null) goto L5;
        r0.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$FwyKMEFKgz9Jb0qoH1k8JITszdc(Context r6, int r7, long r8, BottomSheet[] r10) {
        BottomSheet r102 = r10[0];
        Objects.requireNonNull(r102);
        new GiftSheet(r6, r7, r8, new StarsIntroActivity$$ExternalSyntheticLambda46(r102)).show();
    }

    /* renamed from: $r8$lambda$GWmXBEhjST4C-WptQkYWLJZ3f5g, reason: not valid java name */
    public static /* synthetic */ void m19163$r8$lambda$GWmXBEhjST4CWptQkYWLJZ3f5g(Context r1) {
        Browser.openUrl(r1, LocaleController.getString(R.string.StarsTOSLink));
    }

    /* renamed from: $r8$lambda$GlYDQcjXmO82yQsqRy-yzMeiMAI, reason: not valid java name */
    public static /* synthetic */ void m19164$r8$lambda$GlYDQcjXmO82yQsqRyyzMeiMAI(EditTextBoldCursor r0, BottomSheet[] r1) {
        AndroidUtilities.hideKeyboard(r0);
        r1[0].dismiss();
    }

    public static /* synthetic */ void $r8$lambda$HXjjolat772q5MuwE4enDUo5kC4(ButtonWithCounterView r1, BottomSheet[] r2, int r3, boolean r4, TL_stars.StarsSubscription r5, boolean r6, TLObject r7) {
        r1.setLoading(false);
        BottomSheet r12 = r2[0];
        if (r12 == null) goto L5;
        r12.dismiss();
    L5:
        StarsController.getInstance(r3).invalidateSubscriptions(true);
        BaseFragment r13 = LaunchActivity.getSafeLastFragment();
        if (r13 == null) goto L18;
        if (r4 == true) goto L9;
    L11:
        if (r6 == true) goto L13;
    L15:
        String r22 = LocaleController.formatString(R.string.StarsSubscriptionCancelledToastText, new Object[]{LocaleController.formatDateChat(r5.until_date)});
    L16:
        BulletinFactory.of(r13).createUsersBulletin(Collections.singletonList(r7), LocaleController.getString(R.string.StarsSubscriptionCancelledToast), AndroidUtilities.replaceTags(r22)).show(false);
        return;
    L13:
        if (TextUtils.isEmpty(r5.title) == true) goto L15;
        r22 = LocaleController.formatString(R.string.StarsSubscriptionCancelledBotToastText, new Object[]{LocaleController.formatDateChat(r5.until_date), r5.title});
        goto L16
    L9:
        if (TextUtils.isEmpty(r5.title) == true) goto L11;
        r22 = LocaleController.formatString(R.string.StarsSubscriptionCancelledBizToastText, new Object[]{LocaleController.formatDateChat(r5.until_date), r5.title});
        goto L16
    }

    public static /* synthetic */ void $r8$lambda$IAdWasl_sdnozILJrsejnC5Fdl8(Context r1) {
        Browser.openUrl(r1, LocaleController.getString(R.string.StarsTOSLink));
    }

    public static /* synthetic */ void $r8$lambda$InZQNYbkB3t_uOamUwU4Vynuiyk(BottomSheet[] r0, View r1) {
        r0[0].dismiss();
    }

    /* renamed from: $r8$lambda$JNtk_W-66tT8hy6JhNUu67YLzwQ, reason: not valid java name */
    public static /* synthetic */ void m19165$r8$lambda$JNtk_W66tT8hy6JhNUu67YLzwQ(BottomSheet[] r3, int r4) {
        r3[0].dismiss();
        BaseFragment r32 = LaunchActivity.getSafeLastFragment();
        if (r32 == null) goto L6;
        Bundle r0 = new Bundle();
        r0.putLong("user_id", UserConfig.getInstance(r4).getClientUserId());
        r0.putBoolean("my_profile", true);
        r0.putBoolean("open_gifts", true);
        r32.presentFragment(new ProfileActivity(r0));
        return;
    }

    public static /* synthetic */ void $r8$lambda$JRR6PFwiWj5tVezMdJ3uZhRENKU(BottomSheet r0, ButtonWithCounterView r1, Boolean r2) {
        if (r2.booleanValue() == false) goto L6;
        r0.dismiss();
        return;
    L6:
        AndroidUtilities.runOnUIThread(new StarsIntroActivity$$ExternalSyntheticLambda97(r0, r1), 400);
    }

    /* renamed from: $r8$lambda$JVZSe-KSq3m3Qorlo_SNypgkDKI, reason: not valid java name */
    public static /* synthetic */ void m19166$r8$lambda$JVZSeKSq3m3Qorlo_SNypgkDKI(EditTextBoldCursor r0) {
        AndroidUtilities.showKeyboard(r0);
    }

    public static /* synthetic */ void $r8$lambda$JpN0BqVDVXPifM6YGlhaZHMYk28(SellGiftEnterPriceSheet[] r1) {
        r1[0].dismiss();
    }

    public static /* synthetic */ void $r8$lambda$K5aoLQnR0i1OzFEjMT7d81gKMDA(BottomSheet[] r1, TL_stars.StarsTransaction r2, long r3) {
        r1[0].dismiss();
        BaseFragment r12 = LaunchActivity.getSafeLastFragment();
        if (r12 != null) goto L5;
        return;
    L5:
        if ((r2.flags & 8192) == 0) goto L8;
        r12.presentFragment(ChatActivity.of(r3, r2.giveaway_post_id));
        return;
    L8:
        r12.presentFragment(ChatActivity.of(r3));
    }

    public static /* synthetic */ void $r8$lambda$K77avHa9Rwc1kDpemc6ka6K4cnM(BottomSheet r0, ButtonWithCounterView r1, Boolean r2) {
        if (r2.booleanValue() == false) goto L6;
        r0.dismiss();
        return;
    L6:
        AndroidUtilities.runOnUIThread(new StarsIntroActivity$$ExternalSyntheticLambda76(r0, r1), 400);
    }

    public static /* synthetic */ void $r8$lambda$KN1YxrmfDGIOr12ZmoytgqnXE7s(ButtonWithCounterView r0, BottomSheet[] r1, Theme.ResourcesProvider r2, int r3, TLRPC.TL_messages_checkChatInvite r4, TLObject r5, TLRPC.TL_error r6) {
        AndroidUtilities.runOnUIThread(new StarsIntroActivity$$ExternalSyntheticLambda90(r0, r5, r1, r2, r3, r4));
    }

    public static /* synthetic */ void $r8$lambda$M7B49l2nlUHQOQhvgmJgBU1XyQU(BottomSheet[] r1, EditTextBoldCursor r2) {
        r1[0].setFocusable(true);
        r2.requestFocus();
        AndroidUtilities.runOnUIThread(new StarsIntroActivity$$ExternalSyntheticLambda83(r2));
    }

    /* renamed from: $r8$lambda$OLgeKZGLGJ1-_KH2V72D3vBLhwE, reason: not valid java name */
    public static /* synthetic */ void m19167$r8$lambda$OLgeKZGLGJ1_KH2V72D3vBLhwE(ButtonWithCounterView r0, BottomSheet[] r1, int r2, long r3, TLObject r5, TLRPC.TL_error r6) {
        AndroidUtilities.runOnUIThread(new StarsIntroActivity$$ExternalSyntheticLambda94(r0, r1, r2, r3));
    }

    /* renamed from: $r8$lambda$PSexWNx2HdW3KaXu6-hbjpsViEA, reason: not valid java name */
    public static /* synthetic */ void m19168$r8$lambda$PSexWNx2HdW3KaXu6hbjpsViEA(BottomSheet[] r1, long r2, Context r4) {
        r1[0].dismiss();
        if (UserObject.isService(r2) == false) goto L6;
        Browser.openUrl(r4, LocaleController.getString(R.string.StarsTransactionUnknownLink));
        return;
    L6:
        BaseFragment r12 = LaunchActivity.getSafeLastFragment();
        if (r12 == null) goto L10;
        r12.presentFragment(ChatActivity.of(r2));
        return;
    }

    /* renamed from: $r8$lambda$PUcLBJ7bNknzy-rHru5RbTP3EEQ, reason: not valid java name */
    public static /* synthetic */ boolean m19169$r8$lambda$PUcLBJ7bNknzyrHru5RbTP3EEQ(boolean[] r2, Utilities.Callback2 r3, ButtonWithCounterView r4, EditTextBoldCursor r5, BottomSheet[] r6, TextView r7, int r8, KeyEvent r9) {
        if (r8 == 5) goto L5;
        return false;
    L5:
        if (r2[0] == false) goto L7;
        return true;
    L7:
        if (r3 == null) goto L10;
        r2[0] = true;
        r4.setLoading(true);
        r3.run(Long.valueOf(Long.parseLong(r5.getText().toString())), new StarsIntroActivity$$ExternalSyntheticLambda82(r5, r6));
        return true;
    L10:
        AndroidUtilities.hideKeyboard(r5);
        r6[0].dismiss();
        return true;
    }

    /* renamed from: $r8$lambda$Q1S9j1Mq-eefuyJam-TixcTBvy8, reason: not valid java name */
    public static /* synthetic */ void m19170$r8$lambda$Q1S9j1MqeefuyJamTixcTBvy8(BottomSheet[] r1, long r2) {
        r1[0].dismiss();
        BaseFragment r12 = LaunchActivity.getSafeLastFragment();
        if (r12 == null) goto L6;
        r12.presentFragment(ProfileActivity.of(r2));
        return;
    }

    public static /* synthetic */ void $r8$lambda$SDojXNL6NZi5jKFjw7CMOuFdtZ4(int r7, Context r8, long r9, long r11, BottomSheet[] r13, Theme.ResourcesProvider r14) {
        BotStarsController.getInstance(r7).getConnectedBot(r8, r9, r11, new StarsIntroActivity$$ExternalSyntheticLambda45(r13, r8, r7, r9, r14));
    }

    /* renamed from: $r8$lambda$SUA6c15Kw-8xJ4cZLSHlpKgM6Qg, reason: not valid java name */
    public static /* synthetic */ void m19171$r8$lambda$SUA6c15Kw8xJ4cZLSHlpKgM6Qg(boolean[] r1, EditTextBoldCursor r2, BottomSheet[] r3) {
        r1[0] = false;
        AndroidUtilities.hideKeyboard(r2);
        r3[0].dismiss();
    }

    public static /* synthetic */ void $r8$lambda$TD6yZ5ko1PMQadpw0gjp3pjjhdw(StarsIntroActivity r0, Context r1, View r2) {
        r0.lambda$createView$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Thc2AVcSQy69lVnyvZDeWdPLg14(boolean r12, int r13, String r14, ImageReceiver r15, boolean[] r16) {
        if (r12 == false) goto L7;
        String r122 = UserConfig.getInstance(r13).premiumTonStickerPack;
        if (r122 != null) goto L11;
        MediaDataController.getInstance(r13).checkTonGiftStickers();
        return;
    L11:
        TLRPC.TL_messages_stickerSet r0 = MediaDataController.getInstance(r13).getStickerSetByName(r122);
        if (r0 != null) goto L14;
        r0 = MediaDataController.getInstance(r13).getStickerSetByEmojiOrName(r122);
    L14:
        TLRPC.TL_messages_stickerSet r10 = r0;
        if (r10 == null) goto L38;
        int r2 = 0;
    L18:
        if (r2 >= r10.packs.size()) goto L33;
        TLRPC.TL_stickerPack r3 = r10.packs.get(r2);
        if (TextUtils.equals(r3.emoticon, r14) == false) goto L32;
        if (r3.documents.isEmpty() == true) goto L32;
        long r22 = r3.documents.get(0).longValue();
        int r142 = 0;
    L25:
        if (r142 >= r10.documents.size()) goto L33;
        TLRPC.Document r4 = r10.documents.get(r142);
        if (r4 == null) goto L31;
        if (r4.id != r22) goto L31;
    L34:
        if (r4 == null) goto L36;
    L39:
        boolean r143 = true;
        if (r4 == null) goto L43;
        r15.setAllowStartLottieAnimation(true);
        r15.setDelegate(new AnonymousClass7(r16));
        Drawable r6 = DocumentObject.getSvgThumb(r4, Theme.key_windowBackgroundGray, 0.3f);
        TLRPC.PhotoSize r123 = FileLoader.getClosestPhotoSizeWithSize(r4.thumbs, 160, true, null, true);
        r15.setAutoRepeat(0);
        r15.setImage(ImageLocation.getForDocument(r4), "160_160_nr", ImageLocation.getForDocument(r123, r4), "160_160", r6, r4.size, "tgs", r10, 1);
        return;
    L43:
        MediaDataController r132 = MediaDataController.getInstance(r13);
        if (r10 == null) goto L47;
        r143 = false;
    L47:
        r132.loadStickersByEmojiOrName(r122, false, r143);
        return;
    L36:
        if (r10.documents.isEmpty() == true) goto L39;
        r4 = r10.documents.get(0);
    L31:
        r142 = r142 + 1;
    L32:
        r2 = r2 + 1;
    L33:
        r4 = null;
        goto L34
    L38:
        r4 = null;
        goto L39
    L7:
        r122 = UserConfig.getInstance(r13).premiumGiftsStickerPack;
        if (r122 != null) goto L11;
        MediaDataController.getInstance(r13).checkPremiumGiftStickers();
    }

    public static /* synthetic */ void $r8$lambda$UJrgeQXB_AWuKvlIikjNUVwiQbk(BottomSheet[] r1, long r2) {
        BottomSheet r12 = r1[0];
        if (r12 == null) goto L5;
        r12.dismiss();
    L5:
        BaseFragment r13 = LaunchActivity.getSafeLastFragment();
        if (r13 != null) goto L8;
        return;
    L8:
        r13.presentFragment(ChatActivity.of(r2));
    }

    /* renamed from: $r8$lambda$UO74Br-RyqnkXHQghyUxL915ZfY, reason: not valid java name */
    public static /* synthetic */ void m19172$r8$lambda$UO74BrRyqnkXHQghyUxL915ZfY(ButtonWithCounterView r0, BottomSheet[] r1, int r2, TLObject r3, String r4, TLObject r5, TLRPC.TL_error r6) {
        AndroidUtilities.runOnUIThread(new StarsIntroActivity$$ExternalSyntheticLambda85(r0, r1, r2, r3, r4));
    }

    public static /* synthetic */ void $r8$lambda$UmFdWpuCNya2ihlwGoLICMAGMwE(StarsIntroActivity r0, View r1) {
        r0.lambda$createView$5(r1);
    }

    /* renamed from: $r8$lambda$VNiD53id_Djtqbi-CiksxVpI83A, reason: not valid java name */
    public static /* synthetic */ void m19173$r8$lambda$VNiD53id_DjtqbiCiksxVpI83A(BottomSheet[] r1, long r2, Context r4) {
        r1[0].dismiss();
        if (UserObject.isService(r2) == false) goto L6;
        Browser.openUrl(r4, LocaleController.getString(R.string.StarsTransactionUnknownLink));
        return;
    L6:
        BaseFragment r12 = LaunchActivity.getSafeLastFragment();
        if (r12 == null) goto L10;
        r12.presentFragment(ChatActivity.of(r2));
        return;
    }

    public static /* synthetic */ void $r8$lambda$X2ffkRJUqdnOyR9hqJyh77UOzbQ(BottomSheet[] r2, Theme.ResourcesProvider r3) {
        BulletinFactory.of(r2[0].topBulletinContainer, r3).createSimpleBulletin(R.raw.copy, LocaleController.getString(R.string.StarsTransactionIDCopied)).show(false);
    }

    public static /* synthetic */ void $r8$lambda$XnFteaku7WSmKmw_rh7NFFxndVE(boolean r10, long r11, TL_stars.StarsTransaction r13, int r14, Theme.ResourcesProvider r15, BackupImageView r16, LinearLayout r17, View r18) {
        if (r10 == false) goto L4;
    L3:
        long r3 = r11;
        ArrayList<MessageObject> r1 = new ArrayList();
        int r112 = 0;
    L7:
        if (r112 >= r13.extended_media.size()) goto L10;
        TLRPC.MessageMedia r12 = r13.extended_media.get(r112);
        TLRPC.TL_message r2 = new TLRPC.TL_message();
        r2.id = r13.msg_id;
        r2.dialog_id = r3;
        TLRPC.TL_peerChannel r5 = new TLRPC.TL_peerChannel();
        r2.from_id = r5;
        long r6 = -r3;
        r5.channel_id = r6;
        TLRPC.TL_peerChannel r52 = new TLRPC.TL_peerChannel();
        r2.peer_id = r52;
        r52.channel_id = r6;
        r2.date = r13.date;
        r2.flags |= 512;
        r2.media = r12;
        r2.noforwards = true;
        r1.add(new MessageObject(r14, r2, false, false));
        r112 = r112 + 1;
        goto L7
    L10:
        if (r1.isEmpty() == false) goto L12;
        return;
    L12:
        PhotoViewer.getInstance().setParentActivity(LaunchActivity.getLastFragment(), r15);
        PhotoViewer.getInstance().openPhoto(r1, 0, r3, 0, 0, new AnonymousClass9(r16, r17, r3));
        return;
    L4:
        r11 = DialogObject.getPeerDialogId(r13.peer.peer);
        goto L3
    }

    public static /* synthetic */ void $r8$lambda$ZFsjmEYMGQapdNOWj8n8E9haNww(Context r1) {
        Browser.openUrl(r1, LocaleController.getString(R.string.StarsSubscribeInfoLink));
    }

    public static /* synthetic */ void $r8$lambda$ZKe9APX_9Xq7x6wSOliwNmWFNso(ButtonWithCounterView r0, BottomSheet[] r1, int r2, boolean r3, TL_stars.StarsSubscription r4, boolean r5, TLObject r6, TLObject r7, TLRPC.TL_error r8) {
        AndroidUtilities.runOnUIThread(new StarsIntroActivity$$ExternalSyntheticLambda92(r0, r1, r2, r3, r4, r5, r6));
    }

    public static /* synthetic */ void $r8$lambda$ZW2Ilg6RObAVl6CF3AILhSh6aKw(ButtonWithCounterView r6, TL_stars.StarsSubscription r7, int r8, BottomSheet[] r9, TLObject r10, String r11, View r12) {
        if (r6.isLoading() == false) goto L5;
        return;
    L5:
        r6.setLoading(true);
        TL_stars.TL_changeStarsSubscription r122 = new TL_stars.TL_changeStarsSubscription();
        r122.canceled = Boolean.FALSE;
        r122.peer = new TLRPC.TL_inputPeerSelf();
        r122.subscription_id = r7.id;
        ConnectionsManager.getInstance(r8).sendRequest(r122, new StarsIntroActivity$$ExternalSyntheticLambda77(r6, r9, r8, r10, r11));
    }

    /* renamed from: $r8$lambda$_IUToQOzpzvbwAo5dB-KoOwLDHs, reason: not valid java name */
    public static /* synthetic */ void m19174$r8$lambda$_IUToQOzpzvbwAo5dBKoOwLDHs(BottomSheet[] r3, int r4) {
        r3[0].dismiss();
        BaseFragment r32 = LaunchActivity.getSafeLastFragment();
        if (r32 == null) goto L6;
        Bundle r0 = new Bundle();
        r0.putLong("user_id", UserConfig.getInstance(r4).getClientUserId());
        r0.putBoolean("my_profile", true);
        r0.putBoolean("open_gifts", true);
        r32.presentFragment(new ProfileActivity(r0));
        return;
    }

    public static /* synthetic */ void $r8$lambda$_RrERp9YKwmIu91kpuMRrq1VWIk(BottomSheet[] r1, long r2) {
        r1[0].dismiss();
        BaseFragment r12 = LaunchActivity.getSafeLastFragment();
        if (r12 == null) goto L6;
        r12.presentFragment(new AffiliateProgramFragment(r2));
        return;
    }

    public static /* synthetic */ void $r8$lambda$a7iJeVvju5MfqbnnbMYsxZ_jlJQ(int r7, Context r8, Theme.ResourcesProvider r9, TL_stars.SavedStarGift r10) {
        new StarGiftSheet(r8, r7, UserConfig.getInstance(r7).getClientUserId(), r9).set(r10, null).show();
    }

    public static /* synthetic */ void $r8$lambda$arICYNa0jsYeQfgRMGJO7B296U8(BottomSheet[] r1, long r2) {
        r1[0].dismiss();
        BaseFragment r12 = LaunchActivity.getSafeLastFragment();
        if (r12 == null) goto L6;
        r12.presentFragment(ChatActivity.of(r2));
        return;
    }

    public static /* synthetic */ void $r8$lambda$bGnYDScq0MjNpc_G0Ilw5ZT_LYE(BottomSheet r1, ButtonWithCounterView r2) {
        r1.setCanDismissWithSwipe(false);
        r2.setLoading(false);
    }

    /* renamed from: $r8$lambda$bMLbQQVepN-FPt99ZGB3iaHKeVg, reason: not valid java name */
    public static /* synthetic */ void m19175$r8$lambda$bMLbQQVepNFPt99ZGB3iaHKeVg(ButtonWithCounterView r10, TL_stars.StarsSubscription r11, int r12, BottomSheet[] r13, boolean r14, boolean r15, TLObject r16, View r17) {
        if (r10.isLoading() == false) goto L5;
        return;
    L5:
        r10.setLoading(true);
        TL_stars.TL_changeStarsSubscription r0 = new TL_stars.TL_changeStarsSubscription();
        r0.canceled = Boolean.TRUE;
        r0.peer = new TLRPC.TL_inputPeerSelf();
        r0.subscription_id = r11.id;
        ConnectionsManager.getInstance(r12).sendRequest(r0, new StarsIntroActivity$$ExternalSyntheticLambda81(r10, r13, r12, r14, r11, r15, r16));
    }

    /* renamed from: $r8$lambda$csuf9Cdy-sUOI37q7ZUM6flepIQ, reason: not valid java name */
    public static /* synthetic */ void m19176$r8$lambda$csuf9CdysUOI37q7ZUM6flepIQ(BottomSheet[] r1, long r2) {
        r1[0].dismiss();
        BaseFragment r12 = LaunchActivity.getSafeLastFragment();
        if (r12 == null) goto L6;
        r12.presentFragment(ProfileActivity.of(r2));
        return;
    }

    public static /* synthetic */ void $r8$lambda$elZFBY5WO423yaw049FUDZ0hzNI(Context r2, int r3, String r4) {
        Browser.openUrl(r2, "https://" + MessagesController.getInstance(r3).linkPrefix + "/nft/" + r4);
    }

    public static /* synthetic */ void $r8$lambda$fQMhWWgBnGNgmShghlmbb77XPAg(ButtonSpan.TextViewButtons r4, int r5, Context r6, Theme.ResourcesProvider r7, TL_stars.SavedStarGift r8) {
        if (r8 == null) goto L5;
        SpannableStringBuilder r0 = new SpannableStringBuilder(r4.getText());
        r0.append(" ").append(ButtonSpan.make(LocaleController.getString(R.string.StarGiftReasonUpgradeView), new StarsIntroActivity$$ExternalSyntheticLambda52(r5, r6, r7, r8), r7));
        r4.setText(r0);
        return;
    }

    public static /* synthetic */ void $r8$lambda$hYp7bVi1EFEUcuWVv41QaOqpcb4(StarsIntroActivity r0, boolean r1) {
        r0.lambda$updateButtonsLayouts$6(r1);
    }

    /* renamed from: $r8$lambda$hhuFHR-pdra3sZmF_Um9WowVLXE, reason: not valid java name */
    public static /* synthetic */ void m19177$r8$lambda$hhuFHRpdra3sZmF_Um9WowVLXE(Runnable r0, View r1) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$i9SWg9STm0FNshf8H2lMAs0pd1s(StarsIntroActivity r0, Context r1, View r2) {
        r0.lambda$createView$2(r1, r2);
    }

    /* renamed from: $r8$lambda$iKVE-DbR38vPdkNPf7eee0PWjIM, reason: not valid java name */
    public static /* synthetic */ void m19178$r8$lambda$iKVEDbR38vPdkNPf7eee0PWjIM(EditTextBoldCursor r0, DialogInterface r1) {
        AndroidUtilities.hideKeyboard(r0);
    }

    public static /* synthetic */ void $r8$lambda$ijwwvAb1HAyZEdCFM3nAGJid1YU(BottomSheet[] r1, TL_stars.StarsTransaction r2, long r3) {
        r1[0].dismiss();
        BaseFragment r12 = LaunchActivity.getSafeLastFragment();
        if (r12 != null) goto L5;
        return;
    L5:
        if ((r2.flags & 8192) == 0) goto L8;
        r12.presentFragment(ChatActivity.of(r3, r2.giveaway_post_id));
        return;
    L8:
        r12.presentFragment(ChatActivity.of(r3));
    }

    public static /* synthetic */ void $r8$lambda$ipPdXX7asKv2b8XGKXux2lIeVQg(BottomSheet[] r2, long r3, TL_stars.StarsTransaction r5) {
        r2[0].dismiss();
        BaseFragment r22 = LaunchActivity.getSafeLastFragment();
        if (r22 == null) goto L6;
        Bundle r0 = new Bundle();
        r0.putLong("chat_id", -r3);
        r0.putInt("message_id", r5.msg_id);
        r22.presentFragment(new ChatActivity(r0));
        return;
    }

    public static /* synthetic */ void $r8$lambda$ipbcP8b7a053N5xSEE2QTd_sqIg(ButtonWithCounterView r10, TL_stars.StarsSubscription r11, int r12, BottomSheet[] r13, Theme.ResourcesProvider r14, boolean[] r15, Context r16, View r17) {
        if (r10.isLoading() == true) goto L14;
        r10.setLoading(true);
        if (r11.chat_invite_hash == null) goto L10;
        TLRPC.TL_messages_checkChatInvite r7 = new TLRPC.TL_messages_checkChatInvite();
        r7.hash = r11.chat_invite_hash;
        ConnectionsManager.getInstance(r12).sendRequest(r7, new StarsIntroActivity$$ExternalSyntheticLambda78(r10, r13, r14, r12, r7));
        return;
    L10:
        if (r11.invoice_slug == null) goto L13;
        r15[0] = true;
        Browser.openUrl(r16, Uri.parse("https://t.me/$" + r11.invoice_slug), true, false, false, new AnonymousClass14(r10), null, false, true, false);
        return;
    L13:
        return;
    }

    /* renamed from: $r8$lambda$jdcOnrH0PgPxKN-XVE-1hEWcFE0, reason: not valid java name */
    public static /* synthetic */ void m19179$r8$lambda$jdcOnrH0PgPxKNXVE1hEWcFE0(ButtonWithCounterView r7, TL_stars.StarsSubscription r8, int r9, BottomSheet[] r10, long r11) {
        r7.setLoading(true);
        TL_stars.TL_fulfillStarsSubscription r0 = new TL_stars.TL_fulfillStarsSubscription();
        r0.subscription_id = r8.id;
        r0.peer = new TLRPC.TL_inputPeerSelf();
        ConnectionsManager.getInstance(r9).sendRequest(r0, new StarsIntroActivity$$ExternalSyntheticLambda91(r7, r10, r9, r11));
    }

    public static /* synthetic */ void $r8$lambda$kkYRwSdQXxOuQRsPGWqHBsjR2RY(Long r4, int r5, long r6) {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 == null) goto L10;
        ChatActivity r1 = ChatActivity.of(r4.longValue());
        r0.presentFragment(r1);
        TLRPC.Chat r42 = MessagesController.getInstance(r5).getChat(Long.valueOf(-r4.longValue()));
        if (r42 == null) goto L9;
        AndroidUtilities.runOnUIThread(new StarsIntroActivity$$ExternalSyntheticLambda99(r1, r6, r42), 250);
        return;
    L9:
        return;
    }

    public static /* synthetic */ void $r8$lambda$lEbaw3a8lVtKvfm4T5oVNcYwAKs(GLIconTextureView r1) {
        r1.setPaused(true);
    }

    public static /* synthetic */ void $r8$lambda$lFPNdaiqWJQZyV1okrpfshDzca4(OutlineTextContainerView r0, EditTextBoldCursor r1, View r2, boolean r3) {
        r0.animateSelection(r3, !TextUtils.isEmpty(r1.getText()));
    }

    public static /* synthetic */ void $r8$lambda$lRsVxobHv7NCzcCJH9NdQGplK5Q(StarsIntroActivity r0, UItem r1, Boolean r2, String r3) {
        r0.lambda$onItemClick$8(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$laqoFMs_J2ETEpV2XpJ2h5yFzZ8(ButtonWithCounterView r18, int r19, TL_stars.StarsSubscription r20, BottomSheet[] r21, long r22, Context r24, Theme.ResourcesProvider r25, boolean r26, String r27, View r28) {
        if (r18.isLoading() == false) goto L5;
        return;
    L5:
        StarsController r7 = StarsController.getInstance(r19);
        StarsIntroActivity$$ExternalSyntheticLambda80 r15 = new StarsIntroActivity$$ExternalSyntheticLambda80(r18, r20, r19, r21, r22);
        if (r7.balance.amount >= r20.pricing.amount) goto L17;
        long r11 = r20.pricing.amount;
        if (r26 == false) goto L12;
        int r0 = 8;
    L15:
        new StarsNeededSheet(r24, r25, r11, r0, r27, r15, r22).show();
        return;
    L12:
        if (r22 >= 0) goto L14;
        r0 = 2;
        goto L15
    L14:
        r0 = 7;
        goto L15
    L17:
        r15.run();
    }

    /* renamed from: $r8$lambda$m8Sw0wRH5Hjoap-9UKdsDZE9GtM, reason: not valid java name */
    public static /* synthetic */ void m19180$r8$lambda$m8Sw0wRH5Hjoap9UKdsDZE9GtM(Context r1, int r2, TL_stars.TL_starGiftUnique r3, View r4) {
        Browser.openUrl(r1, "https://" + MessagesController.getInstance(r2).linkPrefix + "/nft/" + r3.slug);
    }

    /* renamed from: $r8$lambda$mUY6Qqoo42-WFKMz3Ac4XQviAI8, reason: not valid java name */
    public static /* synthetic */ void m19181$r8$lambda$mUY6Qqoo42WFKMz3Ac4XQviAI8(StarsBalanceView r2, View r3) {
        if (r2.lastBalance <= 0) goto L10;
        BaseFragment r22 = LaunchActivity.getLastFragment();
        if (r22 == null) goto L9;
        BaseFragment.BottomSheetParams r32 = new BaseFragment.BottomSheetParams();
        r32.transitionFromLeft = true;
        r32.allowNestedScroll = false;
        r22.showAsSheet(new StarsIntroActivity(), r32);
        return;
    L9:
        return;
    }

    public static /* synthetic */ void $r8$lambda$nY1AL9mt5yQeTz_aj8eKbpZ5x8s(BottomSheet[] r3, int r4) {
        r3[0].dismiss();
        BaseFragment r32 = LaunchActivity.getSafeLastFragment();
        if (r32 == null) goto L6;
        Bundle r0 = new Bundle();
        r0.putLong("user_id", UserConfig.getInstance(r4).getClientUserId());
        r0.putBoolean("my_profile", true);
        r32.presentFragment(new ProfileActivity(r0));
        return;
    }

    /* renamed from: $r8$lambda$p9IR0r-rjiVLGiCJ5k3b28eFKgY, reason: not valid java name */
    public static /* synthetic */ void m19182$r8$lambda$p9IR0rrjiVLGiCJ5k3b28eFKgY(BottomSheet[] r1, long r2, Context r4) {
        r1[0].dismiss();
        if (UserObject.isService(r2) == false) goto L6;
        Browser.openUrl(r4, LocaleController.getString(R.string.StarsTransactionUnknownLink));
        return;
    L6:
        BaseFragment r12 = LaunchActivity.getSafeLastFragment();
        if (r12 == null) goto L10;
        r12.presentFragment(ChatActivity.of(r2));
        return;
    }

    /* renamed from: $r8$lambda$pmt-gvPnW9n5jyt5ctnNglR1NMI, reason: not valid java name */
    public static /* synthetic */ void m19183$r8$lambda$pmtgvPnW9n5jyt5ctnNglR1NMI(Context r1) {
        new ExplainStarsSheet(r1).show();
    }

    /* renamed from: $r8$lambda$q4XZIkv5mvgTld4qAYI-ie9JkdM, reason: not valid java name */
    public static /* synthetic */ void m19184$r8$lambda$q4XZIkv5mvgTld4qAYIie9JkdM(Runnable r0, Object[] r1) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$qhOeP7EsRhmdX9PP5CuHsRsu2TU(Runnable r0, Object[] r1) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$s2eX6YGfcp0ExqVgIcYOP7jBPFw(Utilities.Callback r0, BottomSheet r1, ButtonWithCounterView r2, View r3) {
        if (r0 == null) goto L5;
        r1.setCanDismissWithSwipe(false);
        r2.setLoading(true);
        r0.run(new StarsIntroActivity$$ExternalSyntheticLambda96(r1, r2));
        return;
    L5:
        r1.dismiss();
    }

    /* renamed from: $r8$lambda$sFFyu7tglm5UYol-9TNRZbCKkGk, reason: not valid java name */
    public static /* synthetic */ void m19185$r8$lambda$sFFyu7tglm5UYol9TNRZbCKkGk(Runnable r0, Runnable r1) {
        r0.run();
        r1.run();
    }

    /* renamed from: $r8$lambda$u-EYXk9CvtRo81sov3k2iYxLQ08, reason: not valid java name */
    public static /* synthetic */ void m19186$r8$lambda$uEYXk9CvtRo81sov3k2iYxLQ08(BottomSheet r1, ButtonWithCounterView r2) {
        r1.setCanDismissWithSwipe(false);
        r2.setLoading(false);
    }

    public static /* synthetic */ void $r8$lambda$uEOd_eZkdzAo6jf3OfosQ3Ms8to(boolean[] r2, Utilities.Callback2 r3, ButtonWithCounterView r4, EditTextBoldCursor r5, BottomSheet[] r6, View r7) {
        if (r2[0] == false) goto L5;
        return;
    L5:
        if (r3 == null) goto L8;
        r2[0] = true;
        r4.setLoading(true);
        r3.run(0L, new StarsIntroActivity$$ExternalSyntheticLambda75(r2, r5, r6));
        return;
    L8:
        AndroidUtilities.hideKeyboard(r5);
        r6[0].dismiss();
    }

    /* renamed from: $r8$lambda$vI5E-LfqjVrUX8WDeFMsdSv4lNM, reason: not valid java name */
    public static /* synthetic */ void m19187$r8$lambda$vI5ELfqjVrUX8WDeFMsdSv4lNM(Context r0, TL_stars.StarsTransaction r1, View r2) {
        Browser.openUrl(r0, r1.transaction_url);
    }

    /* renamed from: $r8$lambda$v_vgNFcTwMDDVzGV44BUag7-P1M, reason: not valid java name */
    public static /* synthetic */ void m19188$r8$lambda$v_vgNFcTwMDDVzGV44BUag7P1M(ButtonWithCounterView r1, TLObject r2, BottomSheet[] r3, Theme.ResourcesProvider r4, int r5, TLRPC.TL_messages_checkChatInvite r6) {
        r1.setLoading(false);
        if ((r2 instanceof TLRPC.ChatInvite) == false) goto L10;
        TLRPC.ChatInvite r22 = (TLRPC.ChatInvite) r2;
        TL_stars.TL_starsSubscriptionPricing r12 = r22.subscription_pricing;
        if (r12 != null) goto L8;
        BulletinFactory.of(r3[0].topBulletinContainer, r4).createErrorBulletin(LocaleController.getString(R.string.UnknownError)).show(false);
        return;
    L8:
        long r32 = r12.amount;
        StarsController.getInstance(r5).subscribeTo(r6.hash, r22, new StarsIntroActivity$$ExternalSyntheticLambda95(r5, r32));
        return;
    L10:
        BulletinFactory.of(r3[0].topBulletinContainer, r4).createErrorBulletin(LocaleController.getString(R.string.LinkHashExpired)).show(false);
    }

    /* renamed from: $r8$lambda$vpIjOReXh8zTLIY2rdvSW-lDLes, reason: not valid java name */
    public static /* synthetic */ void m19189$r8$lambda$vpIjOReXh8zTLIY2rdvSWlDLes(Utilities.Callback r0, BottomSheet r1, ButtonWithCounterView r2, View r3) {
        if (r0 == null) goto L5;
        r1.setCanDismissWithSwipe(false);
        r2.setLoading(true);
        r0.run(new StarsIntroActivity$$ExternalSyntheticLambda59(r1, r2));
        return;
    L5:
        r1.dismiss();
    }

    /* renamed from: $r8$lambda$w4FKhkRtIxAaT0-VafeHKHV_kH8, reason: not valid java name */
    public static /* synthetic */ void m19190$r8$lambda$w4FKhkRtIxAaT0VafeHKHV_kH8(Runnable r0, DialogInterface r1) {
        if (r0 == null) goto L5;
        r0.run();
        return;
    }

    /* renamed from: $r8$lambda$w5wFW-Peug5L7TC-VPFmCvlF4BI, reason: not valid java name */
    public static /* synthetic */ void m19191$r8$lambda$w5wFWPeug5L7TCVPFmCvlF4BI(BottomSheet[] r0, View r1) {
        BottomSheet r02 = r0[0];
        if (r02 == null) goto L6;
        r02.dismiss();
        return;
    }

    public static /* synthetic */ void $r8$lambda$wy1kOU9z83jNUQez4ncO4QHItIU(Context r6, int r7, long r8, BottomSheet[] r10) {
        BottomSheet r102 = r10[0];
        Objects.requireNonNull(r102);
        new GiftSheet(r6, r7, r8, new StarsIntroActivity$$ExternalSyntheticLambda46(r102)).show();
    }

    /* renamed from: $r8$lambda$yUSh2coUnyWFxzFg5y8c3HADa-A, reason: not valid java name */
    public static /* synthetic */ void m19192$r8$lambda$yUSh2coUnyWFxzFg5y8c3HADaA(StarsIntroActivity r0, View r1, int r2) {
        r0.lambda$createView$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$z6029uLjkfSiuWjHimoYCU9kjbI(BottomSheet[] r1, long r2) {
        r1[0].dismiss();
        BaseFragment r12 = LaunchActivity.getSafeLastFragment();
        if (r12 == null) goto L6;
        r12.presentFragment(ChatActivity.of(r2));
        return;
    }

    /* renamed from: $r8$lambda$zwULHCHSR6XhVIWPoGD8TwBS-pY, reason: not valid java name */
    public static /* synthetic */ void m19193$r8$lambda$zwULHCHSR6XhVIWPoGD8TwBSpY(long r4, int r6) {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 != null) goto L5;
        return;
    L5:
        if (r4 < 0) goto L8;
        r0.presentFragment(new PrivacyControlActivity(10));
        return;
    L8:
        long r42 = -r4;
        if (ChatObject.isChannelAndNotMegaGroup(MessagesController.getInstance(r6).getChat(Long.valueOf(r42))) == false) goto L12;
        r0.presentFragment(new PostSuggestionsEditActivity(r42));
        return;
    L12:
        Bundle r1 = new Bundle();
        r1.putLong("chat_id", r42);
        r1.putInt("type", 3);
        ChatUsersActivity r2 = new ChatUsersActivity(r1);
        r2.setInfo(MessagesController.getInstance(r6).getChatFull(r42));
        r0.presentFragment(r2);
    }

    /* renamed from: -$$Nest$fgettransactionsLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StarsTransactionsLayout m19194$$Nest$fgettransactionsLayout(StarsIntroActivity r0) {
        return r0.transactionsLayout;
    }

    /* renamed from: -$$Nest$fgettwoButtons, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19195$$Nest$fgettwoButtons(StarsIntroActivity r0) {
        return r0.twoButtons;
    }

    public StarsIntroActivity() {
        this.expanded = false;
        this.BUTTON_EXPAND = -1;
        this.BUTTON_GIFT = -2;
        this.BUTTON_SUBSCRIPTIONS_EXPAND = -3;
        this.BUTTON_AFFILIATE = -4;
        setWhiteBackground(true);
    }

    public static /* synthetic */ ActionBar access$000(StarsIntroActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$100(StarsIntroActivity r0) {
        return r0.yOffset;
    }

    public static /* synthetic */ RecyclerListView access$1000(StarsIntroActivity r0) {
        return r0.listView;
    }

    public static /* synthetic */ RecyclerListView access$1100(StarsIntroActivity r0) {
        return r0.listView;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1200(StarsIntroActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ RecyclerListView access$200(StarsIntroActivity r0) {
        return r0.listView;
    }

    public static /* synthetic */ RecyclerListView access$300(StarsIntroActivity r0) {
        return r0.listView;
    }

    public static /* synthetic */ RecyclerListView access$400(StarsIntroActivity r0) {
        return r0.listView;
    }

    public static /* synthetic */ RecyclerListView access$500(StarsIntroActivity r0) {
        return r0.listView;
    }

    public static /* synthetic */ ActionBar access$600(StarsIntroActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ RecyclerListView access$700(StarsIntroActivity r0) {
        return r0.listView;
    }

    public static /* synthetic */ RecyclerListView access$800(StarsIntroActivity r0) {
        return r0.listView;
    }

    public static /* synthetic */ RecyclerListView access$900(StarsIntroActivity r0) {
        return r0.listView;
    }

    public static void addAvailabilityRow(TableView r6, int r7, TL_stars.StarGift r8, Theme.ResourcesProvider r9) {
        TextView r62 = (TextView) ((TableView.TableRowContent) r6.addRow(LocaleController.getString(R.string.Gift2Availability), _UrlKt.FRAGMENT_ENCODE_SET).getChildAt(1)).getChildAt(0);
        SpannableStringBuilder r2 = new SpannableStringBuilder("x ");
        LoadingSpan r3 = new LoadingSpan(r62, AndroidUtilities.dp(90.0f), 0, r9);
        r3.setColors(Theme.multAlpha(r62.getPaint().getColor(), 0.21f), Theme.multAlpha(r62.getPaint().getColor(), 0.08f));
        r2.setSpan(r3, 0, 1, 33);
        r62.setText(r2, TextView.BufferType.SPANNABLE);
        if (r8.sold_out == true) goto L6;
        StarsController.getInstance(r7).getStarGift(r8.id, new StarsIntroActivity$$ExternalSyntheticLambda51(r62));
        return;
    L6:
        boolean r72 = r8 instanceof TL_stars.TL_starGiftUnique;
        int r92 = r8.availability_remains;
        if (r72 == false) goto L13;
        if (r92 > 0) goto L10;
        CharSequence r73 = LocaleController.formatPluralStringComma("Gift2QuantityIssuedNone", r8.availability_total);
    L11:
        r62.setText(r73);
        return;
    L10:
        r73 = LocaleController.formatPluralStringComma("Gift2QuantityIssued1", r8.availability_issued) + LocaleController.formatPluralStringComma("Gift2QuantityIssued2", r8.availability_total);
        goto L11
    L13:
        int r74 = r8.availability_total;
        if (r92 > 0) goto L16;
        CharSequence r75 = LocaleController.formatPluralStringComma("Gift2Availability2ValueNone", r74);
    L17:
        r62.setText(r75);
        return;
    L16:
        r75 = LocaleController.formatPluralStringComma("Gift2Availability4Value", r92, new Object[]{LocaleController.formatNumber(r74, ',')});
        goto L17
    }

    private static CharSequence appendStatus(SpannableStringBuilder r3, TextView r4, String r5) {
        r3.append(" ");
        SpannableString r0 = new SpannableString(r5);
        r0.setSpan(new AnonymousClass15(r4.getCurrentTextColor(), r5), 0, r0.length(), 33);
        r3.append(r0);
        return r3;
    }

    public static CharSequence formatStarsAmount(TL_stars.StarsAmount r2) {
        return formatStarsAmount(r2, 0.777f, ',');
    }

    public static CharSequence formatStarsAmount(TL_stars.StarsAmount r22, float r23, char r24) {
        if (floatFormat != null) goto L5;
        floatFormat = new DecimalFormat("0.################", new DecimalFormatSymbols(Locale.US));
    L5:
        SpannableStringBuilder r3 = new SpannableStringBuilder();
        boolean r4 = r22 instanceof TL_stars.TL_starsTonAmount;
        String r7 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r4 == false) goto L18;
        long r13 = r22.amount;
        if ((r13 % 1000000000) == 0) goto L13;
        String r0 = floatFormat.format(r13 / 1.0E9d);
        r3.append(r0);
        int r02 = r0.indexOf(".");
        if (r02 < 0) goto L45;
        r3.setSpan(new RelativeSizeSpan(r23), r02, r3.length(), 33);
        return r3;
    L45:
        return r3;
    L13:
        StringBuilder r1 = new StringBuilder();
        if (r22.negative() == false) goto L16;
        r7 = "-";
    L16:
        r1.append(r7);
        r1.append(LocaleController.formatNumber(Math.abs(r22.amount / 1000000000), r24));
        r3.append(r1.toString());
        return r3;
    L18:
        long r132 = r22.amount;
        int r42 = r22.nanos;
        boolean r15 = false;
        if (r42 < 0) goto L21;
    L23:
        if (r42 > 0) goto L25;
    L27:
        double r17 = 1.0E9d;
        int r9 = 0;
    L28:
        long r92 = r9 + r132;
        if (r132 != 0) goto L33;
        if (r42 >= 0) goto L35;
    L31:
        r15 = true;
    L35:
        if (r42 == 0) goto L46;
        StringBuilder r43 = new StringBuilder();
        if (r15 == false) goto L39;
        r7 = "-";
    L39:
        r43.append(r7);
        r43.append(LocaleController.formatNumber(Math.abs(r92), r24));
        r3.append(r43.toString());
        DecimalFormat r2 = floatFormat;
        int r03 = r22.nanos;
        double r72 = r03;
        if (r03 >= 0) goto L42;
        r72 = r72 + r17;
    L42:
        String r04 = r2.format(r72 / r17);
        int r25 = r04.indexOf(".");
        if (r25 < 0) goto L45;
        int r44 = r3.length();
        r3.append(r04.substring(r25));
        r3.setSpan(new RelativeSizeSpan(r23), r44 + 1, r3.length(), 33);
        goto L45
    L46:
        StringBuilder r05 = new StringBuilder();
        if (r15 == false) goto L49;
        r7 = "-";
    L49:
        r05.append(r7);
        r05.append(LocaleController.formatNumber(Math.abs(r92), r24));
        r3.append(r05.toString());
        return r3;
    L33:
        if (r132 >= 0) goto L35;
    L25:
        if (r132 >= 0) goto L27;
        r17 = 1.0E9d;
        r9 = 1;
        goto L28
    L21:
        if (r132 <= 0) goto L23;
        r9 = -1;
        r17 = 1.0E9d;
        goto L28
    }

    public static CharSequence formatStarsAmountShort(TL_stars.StarsAmount r2) {
        return formatStarsAmountShort(r2, 0.777f, ' ');
    }

    public static CharSequence formatStarsAmountShort(TL_stars.StarsAmount r18, float r19, char r20) {
        if (floatFormat != null) goto L5;
        floatFormat = new DecimalFormat("0.################", new DecimalFormatSymbols(Locale.US));
    L5:
        SpannableStringBuilder r3 = new SpannableStringBuilder();
        if ((r18 instanceof TL_stars.TL_starsTonAmount) == false) goto L11;
        String r0 = floatFormat.format(r18.amount / 1.0E9d);
        r3.append(r0);
        int r02 = r0.indexOf(".");
        if (r02 < 0) goto L44;
        r3.setSpan(new RelativeSizeSpan(r19), r02, r3.length(), 33);
        return r3;
    L44:
        return r3;
    L11:
        long r9 = r18.amount;
        int r4 = r18.nanos;
        if (r4 < 0) goto L14;
    L16:
        if (r4 > 0) goto L18;
    L20:
        double r16 = 1.0E9d;
        int r15 = 0;
    L21:
        long r7 = r15 + r9;
        if (r9 != 0) goto L27;
        if (r4 >= 0) goto L25;
    L24:
        boolean r42 = true;
    L29:
        long r92 = Math.abs(r7);
        String r10 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r92 > 1000) goto L46;
        if (r18.nanos == 0) goto L46;
        StringBuilder r93 = new StringBuilder();
        if (r42 == false) goto L36;
        r10 = "-";
    L36:
        r93.append(r10);
        r93.append(LocaleController.formatNumber(Math.abs(r7), r20));
        r3.append(r93.toString());
        DecimalFormat r2 = floatFormat;
        int r03 = r18.nanos;
        double r72 = r03;
        if (r03 >= 0) goto L39;
        r72 = r72 + r16;
    L39:
        String r04 = r2.format(r72 / r16);
        int r22 = r04.indexOf(".");
        if (r22 < 0) goto L44;
        int r43 = r3.length();
        String r05 = r04.substring(r22);
        if (r05.length() <= 1) goto L44;
        r3.append(r05.substring(0, Math.min(r05.length(), 3)));
        r3.setSpan(new RelativeSizeSpan(r19), r43 + 1, r3.length(), 33);
    L46:
        if (r18.amount > 1000) goto L52;
        StringBuilder r06 = new StringBuilder();
        if (r42 == false) goto L50;
        r10 = "-";
    L50:
        r06.append(r10);
        r06.append(LocaleController.formatNumber(Math.abs(r7), r20));
        r3.append(r06.toString());
        return r3;
    L52:
        StringBuilder r07 = new StringBuilder();
        if (r42 == false) goto L55;
        r10 = "-";
    L55:
        r07.append(r10);
        r07.append(AndroidUtilities.formatWholeNumber((int) Math.abs(r7), 0));
        r3.append(r07.toString());
        return r3;
    L25:
        r42 = false;
        goto L29
    L27:
        if (r9 >= 0) goto L25;
    L18:
        if (r9 >= 0) goto L20;
        r16 = 1.0E9d;
        r15 = 1;
        goto L21
    L14:
        if (r9 <= 0) goto L16;
        r15 = -1;
        r16 = 1.0E9d;
        goto L21
    }

    public static CharSequence formatStarsAmountString(TL_stars.StarsAmount r2) {
        return formatStarsAmountString(r2, 0.777f, ',');
    }

    public static CharSequence formatStarsAmountString(TL_stars.StarsAmount r18, float r19, char r20) {
        SpannableStringBuilder r2 = new SpannableStringBuilder();
        if ((r18 instanceof TL_stars.TL_starsTonAmount) == true) goto L5;
        long r9 = r18.amount;
        int r3 = r18.nanos;
        boolean r11 = false;
        if (r3 < 0) goto L14;
    L16:
        if (r3 > 0) goto L18;
    L20:
        double r16 = 1.0E9d;
        int r15 = 0;
    L21:
        long r7 = r15 + r9;
        if (r9 != 0) goto L26;
        if (r3 >= 0) goto L28;
    L24:
        r11 = true;
    L28:
        if (r3 == 0) goto L44;
        StringBuilder r32 = new StringBuilder();
        if (r11 == false) goto L32;
        String r92 = "-";
    L33:
        r32.append(r92);
        r32.append(LocaleController.formatNumber(Math.abs(r7), r20));
        r2.append(r32.toString());
        if (floatFormat != null) goto L36;
        floatFormat = new DecimalFormat("0.################", new DecimalFormatSymbols(Locale.US));
    L36:
        DecimalFormat r33 = floatFormat;
        int r0 = r18.nanos;
        double r6 = r0;
        if (r0 >= 0) goto L39;
        r6 = r6 + r16;
    L39:
        String r02 = r33.format(r6 / r16);
        int r34 = r02.indexOf(".");
        if (r34 < 0) goto L42;
        int r5 = r2.length();
        r2.append(r02.substring(r34));
        r2.setSpan(new RelativeSizeSpan(r19), r5 + 1, r2.length(), 33);
    L42:
        r2.append(" ").append(LocaleController.getString(R.string.StarsNano));
        return r2;
    L32:
        r92 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L33
    L44:
        r2.append(LocaleController.formatPluralStringComma("Stars", (int) r9));
        return r2;
    L26:
        if (r9 >= 0) goto L28;
    L18:
        if (r9 >= 0) goto L20;
        r16 = 1.0E9d;
        r15 = 1;
        goto L21
    L14:
        if (r9 <= 0) goto L16;
        r15 = -1;
        r16 = 1.0E9d;
        goto L21
    L5:
        if (floatFormat != null) goto L7;
        floatFormat = new DecimalFormat("0.################", new DecimalFormatSymbols(Locale.US));
    L7:
        String r03 = floatFormat.format(r18.amount / 1.0E9d);
        r2.append(r03);
        int r04 = r03.indexOf(".");
        if (r04 < 0) goto L10;
        r2.setSpan(new RelativeSizeSpan(r19), r04, r2.length(), 33);
    L10:
        return r2;
    }

    public static String formatTON(long r6) {
        if (floatFormat2 != null) goto L6;
        floatFormat2 = new DecimalFormat("0.####", new DecimalFormatSymbols(Locale.US));
    L6:
        if ((r6 % 1000000000) != 0) goto L8;
        StringBuilder r2 = new StringBuilder();
        if (r6 >= 0) goto L12;
        String r3 = "-";
    L13:
        r2.append(r3);
        r2.append(LocaleController.formatNumber(Math.abs(r6 / 1000000000), ','));
        return r2.toString();
    L12:
        r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L13
    L8:
        return floatFormat2.format(r6 / 1.0E9d);
    }

    public static String getGiftStarsEmoji(long r2) {
        if (r2 > 1000) goto L7;
        return "2⃣";
    L7:
        if (r2 >= 2500) goto L10;
        return "3⃣";
    L10:
        return "4⃣";
    }

    public static String getPremiumGiftMonthsEmoji(int r1) {
        if (r1 != 3) goto L5;
        return "2⃣";
    L5:
        if (r1 != 6) goto L7;
        return "3⃣";
    L7:
        if (r1 != 12) goto L9;
        return "4⃣";
    L9:
        if (r1 == 24) goto L12;
        return "1⃣";
    L12:
        return "5⃣";
    }

    public static String getTonGiftEmoji(long r2) {
        if (r2 > RealConnection.IDLE_CONNECTION_HEALTHY_NS) goto L7;
        return "2⃣";
    L7:
        if (r2 > 50000000000L) goto L10;
        return "1⃣";
    L10:
        return "3⃣";
    }

    public static CharSequence getTransactionTitle(int r5, boolean r6, TL_stars.StarsTransaction r7) {
        if (r7.stargift_drop_original_details == false) goto L7;
        return LocaleController.getString(R.string.StarsTransactionRemovedDescription);
    L7:
        if (r7.posts_search == false) goto L11;
        return LocaleController.getString(R.string.StarsTransactionPostsSearch);
    L11:
        if (r7.premium_gift == false) goto L15;
        return LocaleController.getString(R.string.StarsTransactionPremiumGift);
    L15:
        if (r7.phonegroup_message == false) goto L23;
        if (r7.reaction == false) goto L19;
        int r52 = R.string.StarsTransactionLiveStoryReactionFee;
    L21:
        return LocaleController.getString(r52);
    L19:
        r52 = R.string.StarsTransactionLiveStoryMessageFee;
        goto L21
    L23:
        if (r7.paid_message == false) goto L27;
        return LocaleController.formatPluralStringComma("StarsTransactionMessageFee", r7.paid_messages);
    L27:
        if (r7.floodskip == false) goto L31;
        return LocaleController.getString(R.string.StarsTransactionFloodskip);
    L31:
        if (r7.extended_media.isEmpty() == false) goto L33;
        TL_stars.StarsAmount r0 = r7.amount;
        int r1 = r7.flags;
        if ((131072 & r1) != 0) goto L42;
        if ((65536 & r1) == 0) goto L42;
        return LocaleController.formatString(R.string.StarTransactionCommission, new Object[]{AffiliateProgramFragment.percents(r7.starref_commission_permille)});
    L42:
        if (r7.stargift == null) goto L72;
        if (r7.stargift_prepaid_upgrade == true) goto L46;
        boolean r53 = r7.refund;
        boolean r62 = r7.stargift_auction_bid;
        if (r53 == false) goto L60;
        if (r62 == false) goto L52;
        int r54 = R.string.Gift2TransactionRefundedAuctionBid;
    L59:
        return LocaleController.getString(r54);
    L52:
        if (r0.amount > 0) goto L54;
        r54 = R.string.Gift2TransactionRefundedConverted;
        goto L59
    L54:
        if (r7.stargift_upgrade == false) goto L56;
        r54 = R.string.Gift2TransactionRefundedUpgrade;
        goto L59
    L56:
        r54 = R.string.Gift2TransactionRefundedSent;
        goto L59
    L60:
        if (r62 == false) goto L63;
        int r55 = R.string.Gift2TransactionAuctionBid;
    L70:
        return LocaleController.getString(r55);
    L63:
        if (r0.amount <= 0) goto L66;
        r55 = R.string.Gift2TransactionConverted;
        goto L70
    L66:
        if (r7.stargift_upgrade == false) goto L68;
        r55 = R.string.Gift2TransactionUpgraded;
        goto L70
    L68:
        r55 = R.string.Gift2TransactionSent;
        goto L70
    L46:
        return LocaleController.getString(R.string.Gift2TransactionPrepaidUpgrade);
    L72:
        if (r7.subscription == false) goto L86;
        int r02 = r7.subscription_period;
        if (r02 != 2592000) goto L78;
        return LocaleController.getString(R.string.StarSubscriptionPurchase);
    L78:
        if (r02 != 300) goto L82;
        return "5-minute subscription fee";
    L82:
        if (r02 != 60) goto L86;
        return "Minute subscription fee";
    L86:
        if ((r1 & 8192) == 0) goto L90;
        return LocaleController.getString(R.string.StarsGiveawayPrizeReceived);
    L90:
        if (r7.gift == true) goto L92;
        String r56 = r7.title;
        if (r56 == null) goto L104;
        return r56;
    L104:
        long r03 = DialogObject.getPeerDialogId(r7.peer.peer);
        if (r03 == 0) goto L115;
        if (r03 >= 0) goto L108;
        TLRPC.Chat r57 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(-r03));
        if (r57 != null) goto L114;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L114:
        return r57.title;
    L108:
        return UserObject.getUserName(MessagesController.getInstance(UserConfig.selectedAccount).getUser(Long.valueOf(r03)));
    L115:
        TL_stars.StarsTransactionPeer r58 = r7.peer;
        if ((r58 instanceof TL_stars.TL_starsTransactionPeerFragment) == false) goto L131;
        if (r6 == true) goto L127;
        boolean r59 = r7.refund;
        TL_stars.StarsAmount r63 = r7.amount;
        if (r59 == false) goto L124;
        if (r63.positive() == true) goto L127;
    L126:
        int r510 = R.string.StarsTransactionFragment;
    L129:
        return LocaleController.getString(r510);
    L124:
        if (r63.negative() == false) goto L126;
    L127:
        r510 = R.string.StarsTransactionWithdrawFragment;
        goto L129
    L131:
        if ((r58 instanceof TL_stars.TL_starsTransactionPeerPremiumBot) == false) goto L135;
        return LocaleController.getString(R.string.StarsTransactionBot);
    L135:
        if ((r58 instanceof TL_stars.TL_starsTransactionPeerAds) == false) goto L139;
        return LocaleController.getString(R.string.StarsTransactionAds);
    L139:
        return LocaleController.getString(R.string.StarsTransactionUnsupported);
    L92:
        if (r7.sent_by == null) goto L100;
        if (UserObject.isUserSelf(MessagesController.getInstance(r5).getUser(Long.valueOf(DialogObject.getPeerDialogId(r7.sent_by)))) == false) goto L96;
        int r511 = R.string.StarsGiftSent;
    L98:
        return LocaleController.getString(r511);
    L96:
        r511 = R.string.StarsGiftReceived;
        goto L98
    L100:
        return LocaleController.getString(R.string.StarsGiftReceived);
    L33:
        return LocaleController.getString(R.string.StarMediaPurchase);
    }

    private /* synthetic */ void lambda$createView$1(View r1, int r2) {
        UniversalAdapter r12 = this.adapter;
        if (r12 == null) goto L10;
        UItem r13 = r12.getItem(r2);
        if (r13 != null) goto L8;
        return;
    L8:
        onItemClick(r13, r2);
        return;
    }

    private /* synthetic */ void lambda$createView$2(Context r1, View r2) {
        if (MessagesController.getInstance(this.currentAccount).isFrozen() == false) goto L6;
        AccountFrozenAlert.show(this.currentAccount);
        return;
    L6:
        new StarsOptionsSheet(r1, this.resourceProvider).show();
    }

    private /* synthetic */ void lambda$createView$3(Context r1, View r2) {
        new StarsOptionsSheet(r1, this.resourceProvider).show();
    }

    private /* synthetic */ void lambda$createView$4(View r4) {
        presentFragment(new BotStarsActivity(0, getUserConfig().getClientUserId()));
    }

    private /* synthetic */ void lambda$createView$5(View r3) {
        StarsController.getInstance(this.currentAccount).getGiftOptions();
        UserSelectorBottomSheet.open(1, 0, BirthdayController.getInstance(this.currentAccount).getState());
    }

    private /* synthetic */ void lambda$onItemClick$8(UItem r4, Boolean r5, String r6) {
        if (getContext() != null) goto L6;
        return;
    L6:
        if (r5.booleanValue() == false) goto L9;
        BulletinFactory.of(this).createSimpleBulletin(R.raw.stars_topup, LocaleController.getString(R.string.StarsAcquired), AndroidUtilities.replaceTags(LocaleController.formatPluralString("StarsAcquiredInfo", (int) r4.longValue, new Object[0]))).show();
        this.fireworksOverlay.start(true);
        StarsController.getInstance(this.currentAccount).invalidateTransactions(true);
        return;
    L9:
        if (r6 == null) goto L12;
        BulletinFactory.of(this).createSimpleBulletin(R.raw.error, LocaleController.formatString(R.string.UnknownErrorCode, new Object[]{r6})).show();
        return;
    }

    private /* synthetic */ void lambda$updateButtonsLayouts$6(boolean r1) {
        if (r1 == false) goto L5;
        this.oneButtonsLayout.setVisibility(8);
        return;
    }

    private /* synthetic */ void lambda$updateButtonsLayouts$7(boolean r1) {
        if (r1 == true) goto L5;
        this.twoButtonsLayout.setVisibility(8);
        return;
    }

    public static StarParticlesView makeParticlesView(Context r1, int r2, int r3) {
        return new AnonymousClass4(r1, r2, r3);
    }

    public static BottomSheet openConfirmPurchaseSheet(Context r26, Theme.ResourcesProvider r27, int r28, MessageObject r29, long r30, String r32, long r33, TLRPC.WebDocument r35, int r36, Utilities.Callback<Utilities.Callback<Boolean>> r37, Runnable r38) {
        BottomSheet.Builder r12 = new BottomSheet.Builder(r26, false, r27);
        TLRPC.User r14 = MessagesController.getInstance(r28).getUser(Long.valueOf(r30));
        LinearLayout r15 = new LinearLayout(r26);
        r15.setOrientation(1);
        r15.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(8.0f));
        FrameLayout r4 = new FrameLayout(r26);
        r4.addView(makeParticlesView(r26, 40, 0), LayoutHelper.createFrame(-1, -1.0f));
        if (r29 == null) goto L26;
        TLRPC.Message r3 = r29.messageOwner;
        if (r3 == null) goto L26;
        if ((r3.media instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L26;
        AnonymousClass6 r34 = new AnonymousClass6(r26, r26);
        r34.setRoundRadius(AndroidUtilities.dp(24.0f));
        TLRPC.TL_messageMediaPaidMedia r5 = (TLRPC.TL_messageMediaPaidMedia) r29.messageOwner.media;
        if (r5.extended_media.isEmpty() == true) goto L23;
        TLRPC.MessageExtendedMedia r52 = r5.extended_media.get(0);
        if ((r52 instanceof TLRPC.TL_messageExtendedMediaPreview) == false) goto L14;
        ImageLocation r53 = ImageLocation.getForObject(((TLRPC.TL_messageExtendedMediaPreview) r52).thumb, r29.messageOwner);
    L22:
        AnonymousClass6 r2 = r34;
        BottomSheet.Builder r18 = r12;
        ViewGroup r13 = r4;
        float r302 = 20.0f;
        int r9 = 80;
        int r122 = 17;
        r2.setImage(r53, "80_80_b2", null, null, null, r29);
    L24:
        r13.addView(r2, LayoutHelper.createFrame(r9, r9, r122));
    L25:
        int r42 = -1;
    L30:
        StarsBalanceView r22 = new StarsBalanceView(r26, r28, r27);
        ScaleStateListAnimator.apply(r22);
        r22.setOnClickListener(new StarsIntroActivity$$ExternalSyntheticLambda47(r22));
        r13.addView(r22, LayoutHelper.createFrame(-2, -2.0f, 53, 0.0f, 0.0f, -8.0f, 0.0f));
        r15.addView(r13, LayoutHelper.createLinear(r42, 117, 7));
        TextView r23 = new TextView(r26);
        r23.setTextSize(1, r302);
        r23.setTypeface(AndroidUtilities.bold());
        int r54 = Theme.key_dialogTextBlack;
        r23.setTextColor(Theme.getColor(r54, r27));
        if (r36 <= 0) goto L37;
        if (r35 == null) goto L34;
        String r6 = r32;
    L35:
        r23.setText(Emoji.replaceEmoji(r6, r23.getPaint().getFontMetricsInt(), false));
    L41:
        NotificationCenter.listenEmojiLoading(r23);
        r23.setGravity(17);
        if (r35 == null) goto L45;
        int r62 = -8;
    L46:
        r15.addView(r23, LayoutHelper.createLinear(-2, -2, 1, 0, r62, 0, 0));
        if (r35 == null) goto L49;
        LinearLayout r63 = new LinearLayout(r26);
        r63.setOrientation(0);
        r63.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(28.0f), Theme.getColor(Theme.key_windowBackgroundGray, r27)));
        BackupImageView r7 = new BackupImageView(r26);
        r7.setRoundRadius(AndroidUtilities.dp(14.0f));
        AvatarDrawable r92 = new AvatarDrawable();
        r92.setInfo(r14);
        r7.setForUserOrChat(r14, r92);
        r63.addView(r7, LayoutHelper.createLinear(28, 28));
        TextView r72 = new TextView(r26);
        r72.setTextSize(1, 13.0f);
        r72.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r27));
        r72.setText(UserObject.getUserName(r14));
        r63.addView(r72, LayoutHelper.createLinear(-2, -2, 16, 6, 0, 10, 0));
        r15.addView(r63, LayoutHelper.createLinear(-2, 28, 1, 0, 8, 0, 2));
    L49:
        TextView r64 = new TextView(r26);
        r64.setTextSize(1, 14.0f);
        r64.setTextColor(Theme.getColor(r54, r27));
        if (r29 == null) goto L130;
        TLRPC.Message r55 = r29.messageOwner;
        if (r55 == null) goto L130;
        if ((r55.media instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L130;
        long r123 = r29.getDialogId();
        TLRPC.Message r56 = r29.messageOwner;
        if (r56 == null) goto L63;
        TLRPC.MessageFwdHeader r57 = r56.fwd_from;
        if (r57 == null) goto L63;
        TLRPC.Peer r58 = r57.from_id;
        if (r58 == null) goto L63;
        r123 = DialogObject.getPeerDialogId(r58);
    L63:
        if (r123 >= 0) goto L72;
        if (r29.getFromChatId() <= 0) goto L72;
        TLRPC.User r59 = MessagesController.getInstance(r28).getUser(Long.valueOf(r29.getFromChatId()));
        if (r59 == null) goto L72;
        if (r59.bot == false) goto L72;
        r123 = r59.id;
    L72:
        if (r123 < 0) goto L78;
        TLRPC.User r39 = MessagesController.getInstance(r28).getUser(Long.valueOf(r123));
        String r510 = UserObject.getUserName(r39);
        if (r39 != null) goto L76;
    L83:
        boolean r310 = false;
    L84:
        TLRPC.TL_messageMediaPaidMedia r73 = (TLRPC.TL_messageMediaPaidMedia) r29.messageOwner.media;
        int r8 = 0;
        int r93 = 0;
        int r124 = 0;
    L86:
        if (r93 >= r73.extended_media.size()) goto L100;
        TLRPC.MessageExtendedMedia r132 = r73.extended_media.get(r93);
        if ((r132 instanceof TLRPC.TL_messageExtendedMediaPreview) == false) goto L93;
        if ((((TLRPC.TL_messageExtendedMediaPreview) r132).flags & 4) == 0) goto L95;
        boolean r133 = true;
    L96:
        if (r133 == false) goto L98;
        r8 = r8 + 1;
    L99:
        r93 = r93 + 1;
        goto L86
    L98:
        r124 = r124 + 1;
    L95:
        r133 = false;
        goto L96
    L93:
        if ((r132 instanceof TLRPC.TL_messageExtendedMedia) == false) goto L95;
        r133 = ((TLRPC.TL_messageExtendedMedia) r132).media instanceof TLRPC.TL_messageMediaDocument;
        goto L96
    L100:
        String r94 = "StarsConfirmPurchaseMediaOne2";
        if (r8 != 0) goto L110;
        if (r310 == false) goto L104;
        r94 = "StarsConfirmPurchaseMediaBotOne2";
    L104:
        int r311 = (int) r33;
        if (r124 != 1) goto L107;
        String r74 = LocaleController.getString(R.string.StarsConfirmPurchaseMedia_SinglePhoto);
    L108:
        String r312 = LocaleController.formatPluralString(r94, r311, new Object[]{r74, r510});
    L129:
        r64.setText(AndroidUtilities.replaceTags(r312));
    L133:
        r64.setMaxWidth(HintView2.cutInFancyHalf(r64.getText(), r64.getPaint()));
        r64.setGravity(17);
        r15.addView(r64, LayoutHelper.createLinear(-2, -2, 1, 0, 6, 0, 18));
        ButtonWithCounterView r24 = new ButtonWithCounterView(r26, r27);
        if (r36 <= 0) goto L136;
        r24.setText(replaceStars(AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("StarsConfirmSubscriptionButton", (int) r33))), false);
    L137:
        r15.addView(r24, LayoutHelper.createFrame(-1, 48.0f));
        LinkSpanDrawable.LinksTextView r313 = new LinkSpanDrawable.LinksTextView(r26, r27);
        r313.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r27));
        r313.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r27));
        r313.setTextSize(1, 14.0f);
        if (r36 <= 0) goto L140;
        int r1 = R.string.StarsConfirmSubscriptionTOS;
    L141:
        r313.setText(AndroidUtilities.replaceSingleTag(LocaleController.getString(r1), new StarsIntroActivity$$ExternalSyntheticLambda48(r26)));
        r313.setGravity(17);
        r15.addView(r313, LayoutHelper.createLinear(-1, -2, 0.0f, 12.0f, 0.0f, 2.0f));
        BottomSheet.Builder r0 = r18;
        r0.setCustomView(r15);
        BottomSheet r02 = r0.create();
        r24.setOnClickListener(new StarsIntroActivity$$ExternalSyntheticLambda49(r37, r02, r24));
        r02.setOnDismissListener(new StarsIntroActivity$$ExternalSyntheticLambda50(r38));
        r02.fixNavigationBar();
        r02.show();
        return r02;
    L140:
        r1 = R.string.StarsConfirmPurchaseTOS;
        goto L141
    L136:
        r24.setText(replaceStars(AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("StarsConfirmPurchaseButton", (int) r33))), false);
        goto L137
    L107:
        r74 = LocaleController.formatPluralString("StarsConfirmPurchaseMedia_Photos", r124, new Object[0]);
        goto L108
    L110:
        if (r124 != 0) goto L118;
        if (r310 == false) goto L113;
        r94 = "StarsConfirmPurchaseMediaBotOne2";
    L113:
        int r314 = (int) r33;
        if (r8 != 1) goto L116;
        String r75 = LocaleController.getString(R.string.StarsConfirmPurchaseMedia_SingleVideo);
    L117:
        r312 = LocaleController.formatPluralString(r94, r314, new Object[]{r75, r510});
        goto L129
    L116:
        r75 = LocaleController.formatPluralString("StarsConfirmPurchaseMedia_Videos", r8, new Object[0]);
        goto L117
    L118:
        if (r310 == false) goto L120;
        String r315 = "StarsConfirmPurchaseMediaBotTwo2";
    L121:
        int r95 = (int) r33;
        if (r124 != 1) goto L124;
        String r76 = LocaleController.getString(R.string.StarsConfirmPurchaseMedia_SinglePhoto);
        int r25 = 0;
    L125:
        if (r8 != 1) goto L127;
        String r43 = LocaleController.getString(R.string.StarsConfirmPurchaseMedia_SingleVideo);
    L128:
        r312 = LocaleController.formatPluralString(r315, r95, new Object[]{r76, r43, r510});
        goto L129
    L127:
        r43 = LocaleController.formatPluralString("StarsConfirmPurchaseMedia_Videos", r8, new Object[r25]);
        goto L128
    L124:
        r25 = 0;
        r76 = LocaleController.formatPluralString("StarsConfirmPurchaseMedia_Photos", r124, new Object[0]);
        goto L125
    L120:
        r315 = "StarsConfirmPurchaseMediaTwo2";
        goto L121
    L76:
        if (r39.bot == false) goto L83;
        r310 = true;
        goto L84
    L78:
        TLRPC.Chat r316 = MessagesController.getInstance(r28).getChat(Long.valueOf(-r123));
        if (r316 != null) goto L82;
        String r317 = _UrlKt.FRAGMENT_ENCODE_SET;
    L81:
        r510 = r317;
        goto L83
    L82:
        r317 = r316.title;
    L130:
        if (r36 <= 0) goto L132;
        r64.setText(AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("StarsConfirmSubscriptionText2", (int) r33, new Object[]{r32, UserObject.getUserName(r14)})));
        goto L133
    L132:
        r64.setText(AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("StarsConfirmPurchaseText2", (int) r33, new Object[]{r32, UserObject.getUserName(r14)})));
        goto L133
    L45:
        r62 = 8;
        goto L46
    L34:
        r6 = LocaleController.getString(R.string.StarsConfirmSubscriptionTitle);
        goto L35
    L37:
        if (r35 == null) goto L39;
        String r65 = r32;
    L40:
        r23.setText(Emoji.replaceEmoji(r65, r23.getPaint().getFontMetricsInt(), false));
        goto L41
    L39:
        r65 = LocaleController.getString(R.string.StarsConfirmPurchaseTitle);
        goto L40
    L14:
        if ((r52 instanceof TLRPC.TL_messageExtendedMedia) == false) goto L21;
        TLRPC.MessageMedia r511 = ((TLRPC.TL_messageExtendedMedia) r52).media;
        if ((r511 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L19;
        r53 = ImageLocation.getForPhoto(FileLoader.getClosestPhotoSizeWithSize(r511.photo.sizes, AndroidUtilities.dp(80.0f), true), r511.photo);
        goto L22
    L19:
        if ((r511 instanceof TLRPC.TL_messageMediaDocument) == false) goto L21;
        r53 = ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r511.document.thumbs, AndroidUtilities.dp(80.0f), true), r511.document);
    L21:
        r53 = null;
        goto L22
    L23:
        r2 = r34;
        r13 = r4;
        r18 = r12;
        r302 = 20.0f;
        r9 = 80;
        r122 = 17;
    L26:
        r13 = r4;
        r18 = r12;
        r302 = 20.0f;
        if (r35 != null) goto L29;
        BackupImageView r210 = new BackupImageView(r26);
        r210.setRoundRadius(AndroidUtilities.dp(80.0f));
        AvatarDrawable r318 = new AvatarDrawable();
        r318.setInfo(r14);
        r210.setForUserOrChat(r14, r318);
        r13.addView(r210, LayoutHelper.createFrame(80, 80, 17));
        goto L25
    L29:
        FrameLayout r211 = new FrameLayout(r26);
        BackupImageView r319 = new BackupImageView(r26);
        r319.setRoundRadius(AndroidUtilities.dp(18.0f));
        r319.setImage(ImageLocation.getForWebFile(WebFile.createWithWebDocument(r35)), "80_80", null, 0, null);
        r211.addView(r319, LayoutHelper.createFrame(80, 80, 48));
        r13.addView(r211, LayoutHelper.createFrame(80, 87, 17));
        TextView r320 = new TextView(r26);
        r320.setTypeface(AndroidUtilities.getTypeface("fonts/num.otf"));
        r320.setTextSize(1, 13.0f);
        r42 = -1;
        r320.setTextColor(-1);
        r320.setText(replaceStars("XTR " + LocaleController.formatNumber((int) r33, ','), 0.85f));
        r320.setPadding(AndroidUtilities.dp(5.33f), 0, AndroidUtilities.dp(5.33f), 0);
        r320.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(16.0f), -1133566));
        FrameLayout r512 = new FrameLayout(r26);
        r512.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(20.0f), Theme.getColor(Theme.key_dialogBackground, r27)));
        r512.setPadding(AndroidUtilities.dp(1.33f), AndroidUtilities.dp(1.33f), AndroidUtilities.dp(1.33f), AndroidUtilities.dp(1.33f));
        r512.addView(r320, LayoutHelper.createLinear(-2, 16, 119));
        r211.addView(r512, LayoutHelper.createFrame(-2.0f, 18.66f, 81));
        goto L30
    }

    public static BottomSheet openStarsChannelInviteSheet(Context r23, Theme.ResourcesProvider r24, int r25, TLRPC.ChatInvite r26, Utilities.Callback<Utilities.Callback<Boolean>> r27, Runnable r28) {
        BottomSheet.Builder r3 = new BottomSheet.Builder(r23, false, r24);
        LinearLayout r5 = new LinearLayout(r23);
        r5.setOrientation(1);
        r5.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(8.0f));
        FrameLayout r7 = new FrameLayout(r23);
        r7.addView(makeParticlesView(r23, 40, 0), LayoutHelper.createFrame(-1, -1.0f));
        BackupImageView r8 = new BackupImageView(r23);
        r8.setRoundRadius(AndroidUtilities.dp(80.0f));
        AvatarDrawable r11 = new AvatarDrawable();
        r11.setPeerColor(r26.color);
        r11.setText(r26.title);
        TLRPC.Photo r12 = r26.photo;
        if (r12 == null) goto L5;
        r8.setImage(ImageLocation.getForPhoto(FileLoader.getClosestPhotoSizeWithSize(r12.sizes, AndroidUtilities.dp(80.0f)), r26.photo), "80_80", r11, r26);
    L6:
        r7.addView(r8, LayoutHelper.createFrame(80, 80, 17));
        Drawable r82 = r23.getResources().getDrawable(R.drawable.star_small_outline);
        int r122 = Theme.key_dialogBackground;
        r82.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r122, r24), PorterDuff.Mode.SRC_IN));
        Drawable r9 = r23.getResources().getDrawable(R.drawable.star_small_inner);
        ImageView r13 = new ImageView(r23);
        r13.setImageDrawable(r82);
        r7.addView(r13, LayoutHelper.createFrame(26, 26, 17));
        r13.setTranslationX(AndroidUtilities.dp(26.0f));
        r13.setTranslationY(AndroidUtilities.dp(26.0f));
        r13.setScaleX(1.2f);
        r13.setScaleY(1.2f);
        ImageView r132 = new ImageView(r23);
        r132.setImageDrawable(r9);
        r7.addView(r132, LayoutHelper.createFrame(26, 26, 17));
        r132.setTranslationX(AndroidUtilities.dp(26.0f));
        r132.setTranslationY(AndroidUtilities.dp(26.0f));
        StarsBalanceView r83 = new StarsBalanceView(r23, r25, r24);
        ScaleStateListAnimator.apply(r83);
        r83.setOnClickListener(new StarsIntroActivity$$ExternalSyntheticLambda86(r83));
        r7.addView(r83, LayoutHelper.createFrame(-2, -2.0f, 53, 0.0f, 0.0f, -8.0f, 0.0f));
        r5.addView(r7, LayoutHelper.createLinear(-1, 117, 7));
        TextView r72 = new TextView(r23);
        r72.setTextSize(1, 20.0f);
        r72.setTypeface(AndroidUtilities.bold());
        int r84 = Theme.key_dialogTextBlack;
        r72.setTextColor(Theme.getColor(r84, r24));
        r72.setText(LocaleController.getString(R.string.StarsSubscribeTitle));
        r72.setGravity(17);
        r5.addView(r72, LayoutHelper.createLinear(-2, -2, 1, 0, 8, 0, 0));
        TextView r73 = new TextView(r23);
        r73.setTextSize(1, 14.0f);
        r73.setTextColor(Theme.getColor(r84, r24));
        TL_stars.TL_starsSubscriptionPricing r133 = r26.subscription_pricing;
        int r14 = r133.period;
        if (r14 != 2592000) goto L10;
        r73.setText(AndroidUtilities.replaceTags(LocaleController.formatPluralString("StarsSubscribeText", (int) r133.amount, new Object[]{r26.title})));
    L14:
        r73.setMaxWidth(HintView2.cutInFancyHalf(r73.getText(), r73.getPaint()));
        r73.setGravity(17);
        r5.addView(r73, LayoutHelper.createLinear(-2, -2, 1, 0, 6, 0, 22));
        if (TextUtils.isEmpty(r26.about) == true) goto L17;
        TextView r74 = new TextView(r23);
        r74.setTextSize(1, 14.0f);
        r74.setTextColor(Theme.getColor(r84, r24));
        r74.setText(Emoji.replaceEmoji(r26.about, r74.getPaint().getFontMetricsInt(), false));
        r74.setGravity(17);
        r5.addView(r74, LayoutHelper.createLinear(-2, -2, 1, 0, 6, 0, 22));
    L17:
        ButtonWithCounterView r2 = new ButtonWithCounterView(r23, r24);
        r2.setText(LocaleController.getString(R.string.StarsSubscribeButton), false);
        r5.addView(r2, LayoutHelper.createLinear(-1, 48));
        LinkSpanDrawable.LinksTextView r4 = new LinkSpanDrawable.LinksTextView(r23, r24);
        r4.setText(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.StarsSubscribeInfo), new StarsIntroActivity$$ExternalSyntheticLambda87(r23)));
        r4.setGravity(17);
        r4.setTextSize(1, 13.0f);
        r4.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4, r24));
        r4.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r24));
        r5.addView(r4, LayoutHelper.createLinear(-1, -2, 49, 14, 14, 14, 6));
        r3.setCustomView(r5);
        BottomSheet r0 = r3.create();
        r2.setOnClickListener(new StarsIntroActivity$$ExternalSyntheticLambda88(r27, r0, r2));
        r0.setOnDismissListener(new StarsIntroActivity$$ExternalSyntheticLambda89(r28));
        r0.fixNavigationBar(Theme.getColor(r122, r24));
        BaseFragment r1 = LaunchActivity.getSafeLastFragment();
        if (AndroidUtilities.isTablet() == true) goto L23;
        if (r1 == null) goto L23;
        if (AndroidUtilities.hasDialogOnTop(r1) == true) goto L23;
        r0.makeAttached(r1);
    L23:
        r0.show();
        return r0;
    L10:
        if (r14 != 300) goto L12;
        String r142 = "5 minutes";
    L13:
        r73.setText(AndroidUtilities.replaceTags(LocaleController.formatPluralString("StarsSubscribeTextTest", (int) r133.amount, new Object[]{r26.title, r142})));
        goto L14
    L12:
        r142 = "a minute";
        goto L13
    L5:
        r8.setImageDrawable(r11);
        goto L6
    }

    public static SpannableStringBuilder replaceDiamond(CharSequence r6) {
        return replaceDiamond(r6, 0.9f, null, 0.0f, 0.0f, 1.0f);
    }

    public static SpannableStringBuilder replaceDiamond(CharSequence r6, float r7) {
        return replaceDiamond(r6, r7, null, 0.0f, 0.0f, 1.0f);
    }

    public static SpannableStringBuilder replaceDiamond(CharSequence r4, float r5, ColoredImageSpan[] r6, float r7, float r8, float r9) {
        if (r4 != null) goto L6;
        return null;
    L6:
        if ((r4 instanceof SpannableStringBuilder) == true) goto L8;
        SpannableStringBuilder r0 = new SpannableStringBuilder(r4);
    L9:
        SpannableString r42 = new SpannableString("💎 ");
        if (r6 == null) goto L14;
        ColoredImageSpan r2 = r6[0];
        if (r2 == null) goto L14;
    L17:
        r2.recolorDrawable = false;
        r2.translate(r7, r8);
        r2.spaceScaleX = r9;
        r2.setScale(r5, r5);
        r42.setSpan(r2, 0, r42.length() - 1, 33);
        AndroidUtilities.replaceMultipleCharSequence("💎️", r0, "💎");
        AndroidUtilities.replaceMultipleCharSequence("💎 ", r0, "💎");
        AndroidUtilities.replaceMultipleCharSequence("💎", r0, r42);
        AndroidUtilities.replaceMultipleCharSequence("XTR ", r0, "XTR");
        AndroidUtilities.replaceMultipleCharSequence("XTR", r0, r42);
        return r0;
    L14:
        r2 = new ColoredImageSpan(R.drawable.diamond);
        if (r6 == null) goto L17;
        r6[0] = r2;
        goto L17
    L8:
        r0 = (SpannableStringBuilder) r4;
        goto L9
    }

    public static SpannableStringBuilder replaceStars(CharSequence r1) {
        return replaceStars(r1, 1.13f);
    }

    public static SpannableStringBuilder replaceStars(CharSequence r1, float r2) {
        return replaceStars(r1, r2, null);
    }

    public static SpannableStringBuilder replaceStars(CharSequence r6, float r7, ColoredImageSpan[] r8) {
        return replaceStars(r6, r7, r8, 0.0f, 0.0f, 1.0f);
    }

    public static SpannableStringBuilder replaceStars(CharSequence r7, float r8, ColoredImageSpan[] r9, float r10, float r11, float r12) {
        return replaceStars(false, r7, r8, r9, r10, r11, r12);
    }

    public static SpannableStringBuilder replaceStars(CharSequence r1, ColoredImageSpan[] r2) {
        return replaceStars(false, r1, r2);
    }

    public static SpannableStringBuilder replaceStars(TL_stars.StarsAmount r1, CharSequence r2, float r3) {
        return replaceStars(r1 instanceof TL_stars.TL_starsTonAmount, r2, r3, null);
    }

    public static SpannableStringBuilder replaceStars(boolean r1, CharSequence r2) {
        return replaceStars(r1, r2, 1.13f);
    }

    public static SpannableStringBuilder replaceStars(boolean r1, CharSequence r2, float r3) {
        return replaceStars(r1, r2, r3, null);
    }

    public static SpannableStringBuilder replaceStars(boolean r7, CharSequence r8, float r9, ColoredImageSpan[] r10) {
        return replaceStars(r7, r8, r9, r10, 0.0f, 0.0f, 1.0f);
    }

    public static SpannableStringBuilder replaceStars(boolean r5, CharSequence r6, float r7, ColoredImageSpan[] r8, float r9, float r10, float r11) {
        if (r6 != null) goto L6;
        return null;
    L6:
        if ((r6 instanceof SpannableStringBuilder) == true) goto L8;
        SpannableStringBuilder r0 = new SpannableStringBuilder(r6);
    L10:
        if (r5 == false) goto L12;
        String r1 = "TON";
    L13:
        SpannableString r2 = new SpannableString(r1.concat(" "));
        if (r8 == null) goto L19;
        ColoredImageSpan r3 = r8[0];
        if (r3 == null) goto L19;
    L25:
        r3.translate(r9, r10);
        r3.spaceScaleX = r11;
        if (r5 == false) goto L28;
        float r72 = r7 * 0.2f;
        r3.setScale(r72, r72);
    L29:
        r2.setSpan(r3, 0, r2.length() - 1, 33);
        AndroidUtilities.replaceMultipleCharSequence("⭐️", r0, "⭐");
        AndroidUtilities.replaceMultipleCharSequence("⭐ ", r0, "⭐");
        AndroidUtilities.replaceMultipleCharSequence("⭐", r0, r2);
        AndroidUtilities.replaceMultipleCharSequence("XTR ", r0, "XTR");
        AndroidUtilities.replaceMultipleCharSequence("XTR", r0, r2);
        return r0;
    L28:
        r3.setScale(r7, r7);
    L19:
        if (r5 == false) goto L21;
        int r4 = R.drawable.mini_gram_72;
    L22:
        r3 = new ColoredImageSpan(r4);
        if (r8 == null) goto L25;
        r8[0] = r3;
        goto L25
    L21:
        r4 = R.drawable.msg_premium_liststar;
        goto L22
    L12:
        r1 = "⭐";
        goto L13
    L8:
        r0 = (SpannableStringBuilder) r6;
        goto L10
    }

    public static SpannableStringBuilder replaceStars(boolean r5, CharSequence r6, ColoredImageSpan[] r7) {
        if (r6 != null) goto L6;
        return null;
    L6:
        if ((r6 instanceof SpannableStringBuilder) == true) goto L8;
        SpannableStringBuilder r0 = new SpannableStringBuilder(r6);
    L10:
        if (r7 == null) goto L15;
        ColoredImageSpan r1 = r7[0];
        if (r1 == null) goto L15;
    L25:
        if (r7 == null) goto L27;
        r7[0] = r1;
    L27:
        SpannableString r52 = new SpannableString("⭐ ");
        r52.setSpan(r1, 0, r52.length() - 1, 33);
        AndroidUtilities.replaceMultipleCharSequence("⭐️", r0, "⭐");
        AndroidUtilities.replaceMultipleCharSequence("⭐ ", r0, "⭐");
        AndroidUtilities.replaceMultipleCharSequence("⭐", r0, r52);
        AndroidUtilities.replaceMultipleCharSequence("XTR ", r0, "XTR");
        AndroidUtilities.replaceMultipleCharSequence("XTR", r0, r52);
        return r0;
    L15:
        if (r5 == false) goto L17;
        int r2 = R.drawable.mini_gram_72;
    L18:
        r1 = new ColoredImageSpan(r2);
        float r22 = 1.13f;
        if (r5 == false) goto L21;
        float r4 = 0.222f;
    L22:
        if (r5 == false) goto L24;
        r22 = 0.222f;
    L24:
        r1.setScale(r4, r22);
        goto L25
    L21:
        r4 = 1.13f;
        goto L22
    L17:
        r2 = R.drawable.msg_premium_liststar;
        goto L18
    L8:
        r0 = (SpannableStringBuilder) r6;
        goto L10
    }

    public static SpannableStringBuilder replaceStarsWithPlain(CharSequence r1, float r2) {
        return replaceStarsWithPlain(r1, r2, null);
    }

    public static SpannableStringBuilder replaceStarsWithPlain(CharSequence r1, float r2, ColoredImageSpan[] r3) {
        return replaceStarsWithPlain(false, r1, r2, r3);
    }

    public static SpannableStringBuilder replaceStarsWithPlain(TL_stars.StarsAmount r1, CharSequence r2, float r3) {
        return replaceStarsWithPlain(r1 instanceof TL_stars.TL_starsTonAmount, r2, r3, null);
    }

    public static SpannableStringBuilder replaceStarsWithPlain(TL_stars.StarsAmount r0, CharSequence r1, float r2, ColoredImageSpan[] r3) {
        return replaceStarsWithPlain(r0 instanceof TL_stars.TL_starsTonAmount, r1, r2, r3);
    }

    public static SpannableStringBuilder replaceStarsWithPlain(boolean r1, CharSequence r2, float r3) {
        return replaceStarsWithPlain(r1, r2, r3, null);
    }

    public static SpannableStringBuilder replaceStarsWithPlain(boolean r5, CharSequence r6, float r7, ColoredImageSpan[] r8) {
        if (r6 != null) goto L6;
        return null;
    L6:
        if ((r6 instanceof SpannableStringBuilder) == true) goto L8;
        SpannableStringBuilder r0 = new SpannableStringBuilder(r6);
    L10:
        if (r5 == false) goto L12;
        String r1 = "TON";
    L13:
        if (r5 == false) goto L15;
        int r2 = R.drawable.mini_gram_72;
    L16:
        SpannableString r3 = new SpannableString(r1.concat(" "));
        if (r8 == null) goto L21;
        ColoredImageSpan r4 = r8[0];
        if (r4 == null) goto L21;
    L26:
        if (r5 == false) goto L28;
        r7 = r7 * 0.33f;
    L29:
        r4.setScale(r7, r7);
        r3.setSpan(r4, 0, r3.length() - 1, 33);
        AndroidUtilities.replaceMultipleCharSequence("⭐️", r0, "⭐");
        AndroidUtilities.replaceMultipleCharSequence("⭐ ", r0, "⭐");
        AndroidUtilities.replaceMultipleCharSequence("⭐", r0, r3);
        AndroidUtilities.replaceMultipleCharSequence("XTR ", r0, "XTR");
        AndroidUtilities.replaceMultipleCharSequence("XTR", r0, r3);
        return r0;
    L28:
        r4.recolorDrawable = false;
    L21:
        if (r8 != null) goto L23;
    L25:
        r4 = new ColoredImageSpan(r2);
        goto L26
    L23:
        if (r8.length <= 0) goto L25;
        r4 = new ColoredImageSpan(r2);
        r8[0] = r4;
        goto L26
    L15:
        r2 = R.drawable.star_small_inner;
        goto L16
    L12:
        r1 = "⭐";
        goto L13
    L8:
        r0 = (SpannableStringBuilder) r6;
        goto L10
    }

    public static Runnable setGiftImage(View r0, ImageReceiver r1, long r2) {
        return setGiftImage(r0, r1, getGiftStarsEmoji(r2));
    }

    public static Runnable setGiftImage(View r1, ImageReceiver r2, String r3) {
        return setGiftImage(r1, r2, r3, false);
    }

    public static Runnable setGiftImage(View r7, ImageReceiver r8, String r9, boolean r10) {
        int r3 = r8.getCurrentAccount();
        StarsIntroActivity$$ExternalSyntheticLambda41 r1 = new StarsIntroActivity$$ExternalSyntheticLambda41(r10, r3, r9, r8, new boolean[1]);
        r1.run();
        NotificationCenter r82 = NotificationCenter.getInstance(r3);
        if (r10 == false) goto L5;
        int r92 = NotificationCenter.didUpdateTonGiftStickers;
    L7:
        return new StarsIntroActivity$$ExternalSyntheticLambda44(r82.listen(r7, r92, new StarsIntroActivity$$ExternalSyntheticLambda42(r1)), NotificationCenter.getInstance(r3).listen(r7, NotificationCenter.diceStickersDidLoad, new StarsIntroActivity$$ExternalSyntheticLambda43(r1)));
    L5:
        r92 = NotificationCenter.didUpdatePremiumGiftStickers;
        goto L7
    }

    public static void setGiftImage(ImageReceiver r17, TLRPC.Document r18, int r19) {
        if (r18 != null) goto L6;
        r17.clearImage();
        return;
    L6:
        TLRPC.PhotoSize r2 = FileLoader.getClosestPhotoSizeWithSize(r18.thumbs, r19);
        SvgHelper.SvgDrawable r11 = DocumentObject.getSvgThumb(r18.thumbs, Theme.key_windowBackgroundGray, 0.35f);
        r17.setImage(ImageLocation.getForDocument(r18), r19 + "_" + r19, ImageLocation.getForDocument(r2, r18), r19 + "_" + r19, r11, 0, null, null, 0);
    }

    public static void setGiftImage(ImageReceiver r0, TL_stars.StarGift r1, int r2) {
        if (r1 != null) goto L4;
        TLRPC.Document r12 = null;
    L5:
        setGiftImage(r0, r12, r2);
        return;
    L4:
        r12 = r1.getDocument();
        goto L5
    }

    public static Runnable setPremiumGiftImage(View r0, ImageReceiver r1, int r2) {
        return setGiftImage(r0, r1, getPremiumGiftMonthsEmoji(r2));
    }

    public static Runnable setTonGiftImage(View r0, ImageReceiver r1, long r2) {
        return setGiftImage(r0, r1, getTonGiftEmoji(r2), true);
    }

    public static BottomSheet showBoostsSheet(Context r28, int r29, long r30, TL_stories.Boost r32, Theme.ResourcesProvider r33) {
        if (r32 == null) goto L19;
        if (r28 == null) goto L21;
        BottomSheet.Builder r9 = new BottomSheet.Builder(r28, false, r33);
        BottomSheet[] r12 = new BottomSheet[1];
        LinearLayout r13 = new LinearLayout(r28);
        r13.setOrientation(1);
        r13.setPadding(0, AndroidUtilities.dp(20.0f), 0, AndroidUtilities.dp(4.0f));
        r13.setClipChildren(false);
        r13.setClipToPadding(false);
        FrameLayout r2 = new FrameLayout(r28);
        r2.setClipChildren(false);
        r2.setClipToPadding(false);
        r13.addView(r2, LayoutHelper.createLinear(-1, 150, 7, 0, 0, 0, 10));
        StarParticlesView r6 = makeParticlesView(r28, 70, 0);
        r2.addView(r6, LayoutHelper.createFrame(-1, -1.0f));
        GLIconTextureView r14 = new GLIconTextureView(r28, 1, 2);
        GLIconRenderer r3 = r14.mRenderer;
        r3.colorKey1 = Theme.key_starsGradient1;
        r3.colorKey2 = Theme.key_starsGradient2;
        r3.updateColors();
        r14.setStarParticlesView(r6);
        r2.addView(r14, LayoutHelper.createFrame(170, 170.0f, 17, 0.0f, 32.0f, 0.0f, 24.0f));
        r14.setPaused(false);
        TextView r22 = new TextView(r28);
        r22.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r33));
        r22.setTextSize(1, 20.0f);
        r22.setTypeface(AndroidUtilities.bold());
        r22.setGravity(17);
        r22.setText(LocaleController.formatPluralStringSpaced("BoostStars", (int) r32.stars));
        r13.addView(r22, LayoutHelper.createLinear(-1, -2, 17, 20, 0, 20, 4));
        TextView r23 = new TextView(r28);
        r23.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(20.0f), -6915073));
        r23.setTextColor(-1);
        r23.setTextSize(1, 11.33f);
        r23.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(8.33f), 0);
        r23.setGravity(17);
        r23.setTypeface(AndroidUtilities.bold());
        StringBuilder r34 = new StringBuilder("x");
        int r62 = r32.multiplier;
        if (r62 != 0) goto L9;
        r62 = 1;
    L9:
        r34.append(LocaleController.formatPluralStringSpaced("BoostingBoostsCount", r62));
        SpannableStringBuilder r1 = new SpannableStringBuilder(r34.toString());
        ColoredImageSpan r35 = new ColoredImageSpan(R.drawable.mini_boost_badge, 2);
        r35.translate(0.0f, AndroidUtilities.dp(0.66f));
        r1.setSpan(r35, 0, 1, 33);
        r23.setText(r1);
        r13.addView(r23, LayoutHelper.createLinear(-2, 20, 17, 20, 4, 20, 4));
        TableView r15 = new TableView(r28, r33);
        r15.addRowUser(LocaleController.getString(R.string.BoostFrom), r29, r30, new StarsIntroActivity$$ExternalSyntheticLambda54(r12, r30));
        r15.addRow(LocaleController.getString(R.string.BoostGift), LocaleController.formatPluralString("BoostStars", (int) r32.stars, new Object[0]));
        if (r32.giveaway_msg_id == 0) goto L12;
        r15.addRowLink(LocaleController.getString(R.string.BoostReason), LocaleController.getString(R.string.BoostReasonGiveaway), new StarsIntroActivity$$ExternalSyntheticLambda55(r12, r30, r32));
    L12:
        r15.addRow(LocaleController.getString(R.string.BoostDate), LocaleController.formatString(R.string.formatDateAtTime, new Object[]{LocaleController.getInstance().getFormatterGiveawayCard().format(new Date(r32.date * 1000)), LocaleController.getInstance().getFormatterDay().format(new Date(r32.date * 1000))}));
        r15.addRow(LocaleController.getString(R.string.BoostUntil), LocaleController.formatString(R.string.formatDateAtTime, new Object[]{LocaleController.getInstance().getFormatterGiveawayCard().format(new Date(r32.expires * 1000)), LocaleController.getInstance().getFormatterDay().format(new Date(r32.expires * 1000))}));
        r13.addView(r15, LayoutHelper.createLinear(-1, -2, 16.0f, 17.0f, 16.0f, 0.0f));
        LinkSpanDrawable.LinksTextView r16 = new LinkSpanDrawable.LinksTextView(r28, r33);
        r16.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r33));
        r16.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r33));
        r16.setTextSize(1, 14.0f);
        r16.setText(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.StarsTransactionTOS), new StarsIntroActivity$$ExternalSyntheticLambda56(r28)));
        r16.setGravity(17);
        r13.addView(r16, LayoutHelper.createLinear(-1, -2, 14.0f, 15.0f, 14.0f, 7.0f));
        ButtonWithCounterView r17 = new ButtonWithCounterView(r28, r33);
        r17.setText(LocaleController.getString(R.string.OK), false);
        r17.setOnClickListener(new StarsIntroActivity$$ExternalSyntheticLambda57(r12));
        r13.addView(r17, LayoutHelper.createLinear(-1, 48, 16.0f, 8.0f, 16.0f, 0.0f));
        r9.setCustomView(r13);
        BottomSheet r0 = r9.create();
        r12[0] = r0;
        r0.useBackgroundTopPadding = false;
        r0.fixNavigationBar();
        BaseFragment r02 = LaunchActivity.getSafeLastFragment();
        if (AndroidUtilities.isTablet() == false) goto L15;
    L17:
        r14.setPaused(false);
        r12[0].show();
        r12[0].setOnDismissListener(new StarsIntroActivity$$ExternalSyntheticLambda58(r14));
        return r12[0];
    L15:
        if (AndroidUtilities.hasDialogOnTop(r02) == true) goto L17;
        r12[0].makeAttached(r02);
        goto L17
    L21:
        return null;
    L19:
        return null;
    }

    public static BottomSheet showGiftResellPriceSheet(Context r6, int r7, Utilities.Callback2<AmountUtils$Amount, Runnable> r8, Theme.ResourcesProvider r9) {
        return showGiftResellPriceSheet(r6, r7, null, null, r8, r9);
    }

    public static BottomSheet showGiftResellPriceSheet(Context r6, int r7, TL_stars.StarGift r8, AmountUtils$Amount r9, Utilities.Callback2<AmountUtils$Amount, Runnable> r10, Theme.ResourcesProvider r11) {
        if (r9 != null) goto L10;
        if (r8 != null) goto L7;
        r9 = AmountUtils$Amount.fromDecimal(MessagesController.getInstance(r7).config.starsStarGiftResaleAmountMin.get(), AmountUtils$Currency.STARS);
        goto L10
    L7:
        if (r8.resale_ton_only == false) goto L9;
        r9 = r8.getResellAmount(AmountUtils$Currency.TON);
        goto L10
    L9:
        r9 = r8.getResellAmount(AmountUtils$Currency.STARS);
    L10:
        SellGiftEnterPriceSheet r0 = new SellGiftEnterPriceSheet(r6, r11, r7, r9, new StarsIntroActivity$$ExternalSyntheticLambda84(r10, r8));
        SellGiftEnterPriceSheet[] r82 = {r0};
        r0.show();
        return r82[0];
    }

    public static BottomSheet showMediaPriceSheet(Context r25, long r26, boolean r28, Utilities.Callback2<Long, Runnable> r29, Theme.ResourcesProvider r30) {
        BottomSheet.Builder r2 = new BottomSheet.Builder(r25, false, r30);
        LinearLayout r4 = new LinearLayout(r25);
        r4.setOrientation(1);
        r4.setClipChildren(false);
        r4.setClipToPadding(false);
        r4.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(8.0f));
        TextView r7 = new TextView(r25);
        r7.setTypeface(AndroidUtilities.bold());
        r7.setText(LocaleController.getString(R.string.PaidContentTitle));
        r7.setTextSize(1, 20.0f);
        int r9 = Theme.key_windowBackgroundWhiteBlackText;
        r7.setTextColor(Theme.getColor(r9, r30));
        r4.addView(r7, LayoutHelper.createLinear(-1, -2, 4.0f, 0.0f, 4.0f, 18.0f));
        EditTextBoldCursor r15 = new EditTextBoldCursor(r25);
        OutlineTextContainerView r13 = new OutlineTextContainerView(r25, r30);
        r13.setForceForceUseCenter(true);
        r13.setText(LocaleController.getString(R.string.PaidContentPriceTitle));
        r13.setLeftPadding(AndroidUtilities.dp(36.0f));
        r15.setTextColor(Theme.getColor(r9, r30));
        r15.setCursorSize(AndroidUtilities.dp(20.0f));
        r15.setCursorWidth(1.5f);
        ButtonWithCounterView r72 = null;
        r15.setBackground(null);
        r15.setTextSize(1, 18.0f);
        r15.setMaxLines(1);
        int r8 = AndroidUtilities.dp(16.0f);
        r15.setPadding(AndroidUtilities.dp(6.0f), r8, r8, r8);
        r15.setInputType(2);
        r15.setTypeface(Typeface.DEFAULT);
        r15.setSelectAllOnFocus(true);
        r15.setHighlightColor(Theme.getColor(Theme.key_chat_inTextSelectionHighlight, r30));
        r15.setHandlesColor(Theme.getColor(Theme.key_chat_TextSelectionCursor, r30));
        if (LocaleController.isRTL == false) goto L5;
        int r82 = 5;
    L6:
        r15.setGravity(r82);
        r15.setOnFocusChangeListener(new StarsIntroActivity$$ExternalSyntheticLambda60(r13, r15));
        LinearLayout r83 = new LinearLayout(r25);
        r83.setOrientation(0);
        ImageView r92 = new ImageView(r25);
        r92.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        r92.setImageResource(R.drawable.star_small_inner);
        r83.addView(r92, LayoutHelper.createLinear(-2, -2, 0.0f, 19, 14, 0, 0, 0));
        r83.addView(r15, LayoutHelper.createLinear(-1, -2, 1.0f, 119));
        r13.attachEditText(r15);
        r13.addView(r83, LayoutHelper.createFrame(-1, -2, 48));
        r4.addView(r13, LayoutHelper.createLinear(-1, -2));
        TextView r84 = new TextView(r25);
        r84.setTextSize(1, 16.0f);
        r84.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3));
        r13.addView(r84, LayoutHelper.createFrame(-2, -2.0f, 21, 0.0f, 0.0f, 14.0f, 0.0f));
        LinkSpanDrawable.LinksTextView r6 = new LinkSpanDrawable.LinksTextView(r25);
        r6.setText(AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.PaidContentInfo), new StarsIntroActivity$$ExternalSyntheticLambda61(r25)), true));
        r6.setTextSize(1, 12.0f);
        r6.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r30));
        r6.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r30));
        r4.addView(r6, LayoutHelper.createLinear(-1, -2, 14.0f, 3.0f, 14.0f, 24.0f));
        ButtonWithCounterView r14 = new ButtonWithCounterView(r25, r30).setRound();
        if (r26 <= 0) goto L9;
        int r10 = R.string.PaidContentUpdateButton;
    L10:
        r14.setText(LocaleController.getString(r10), false);
        r4.addView(r14, LayoutHelper.createLinear(-1, 48));
        if (r26 <= 0) goto L14;
        if (r28 == false) goto L14;
        r72 = new ButtonWithCounterView(r25, false, r30).setRound();
        r72.setText(LocaleController.getString(R.string.PaidContentClearButton), false, false);
        r4.addView(r72, LayoutHelper.createLinear(-1, 48, 0.0f, 4.0f, 0.0f, 0.0f));
    L14:
        r2.setCustomView(r4);
        BottomSheet[] r0 = {r2.create()};
        if (r26 > 0) goto L17;
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L18:
        r15.setText(r1);
        r15.addTextChangedListener(new AnonymousClass16(r15, r13, r26, r28, r14, r84));
        boolean[] r12 = {false};
        r15.setOnEditorActionListener(new StarsIntroActivity$$ExternalSyntheticLambda62(r12, r29, r14, r15, r0));
        r14.setOnClickListener(new StarsIntroActivity$$ExternalSyntheticLambda63(r12, r29, r15, r14, r0));
        if (r72 == null) goto L21;
        ButtonWithCounterView r142 = r72;
        StarsIntroActivity$$ExternalSyntheticLambda64 r11 = new StarsIntroActivity$$ExternalSyntheticLambda64(r12, r29, r142, r15, r0);
        BottomSheet[] r02 = r0;
        r142.setOnClickListener(r11);
    L22:
        r02[0].fixNavigationBar();
        r02[0].setOnDismissListener(new StarsIntroActivity$$ExternalSyntheticLambda65(r15));
        r02[0].show();
        BaseFragment r16 = LaunchActivity.getLastFragment();
        if ((r16 instanceof ChatActivity) == false) goto L25;
        boolean r17 = ((ChatActivity) r16).needEnterText();
    L26:
        StarsIntroActivity$$ExternalSyntheticLambda66 r22 = new StarsIntroActivity$$ExternalSyntheticLambda66(r02, r15);
        if (r17 == false) goto L29;
        long r42 = 200;
    L30:
        AndroidUtilities.runOnUIThread(r22, r42);
        return r02[0];
    L29:
        r42 = 80;
        goto L30
    L25:
        r17 = false;
        goto L26
    L21:
        r02 = r0;
        goto L22
    L17:
        r1 = Long.toString(r26);
        goto L18
    L9:
        r10 = R.string.PaidContentButton;
        goto L10
    L5:
        r82 = 3;
        goto L6
    }

    public static BottomSheet showSoldOutGiftSheet(Context r18, int r19, TL_stars.StarGift r20, Theme.ResourcesProvider r21) {
        if (r20 == null) goto L22;
        if (r18 == null) goto L24;
        BottomSheet.Builder r3 = new BottomSheet.Builder(r18, false, r21);
        LinearLayout r5 = new LinearLayout(r18);
        r5.setOrientation(1);
        r5.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(20.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(8.0f));
        r5.setClipChildren(false);
        r5.setClipToPadding(false);
        BackupImageView r7 = new BackupImageView(r18);
        setGiftImage(r7.getImageReceiver(), r20, 160);
        r5.addView(r7, LayoutHelper.createLinear(160, 160, 17, 0, -8, 0, 10));
        TextView r72 = new TextView(r18);
        r72.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r21));
        r72.setTextSize(1, 20.0f);
        r72.setTypeface(AndroidUtilities.bold());
        r72.setGravity(17);
        r72.setText(LocaleController.getString(R.string.Gift2SoldOutSheetTitle));
        r5.addView(r72, LayoutHelper.createLinear(-1, -2, 17, 20, 0, 20, 4));
        TextView r73 = new TextView(r18);
        r73.setTextSize(1, 14.0f);
        r73.setTypeface(AndroidUtilities.bold());
        r73.setGravity(17);
        r73.setTextColor(Theme.getColor(Theme.key_text_RedBold, r21));
        r73.setText(LocaleController.getString(R.string.Gift2SoldOutSheetSubtitle));
        r5.addView(r73, LayoutHelper.createLinear(-1, -2, 17, 20, 0, 20, 4));
        TableView r6 = new TableView(r18, r21);
        if (r20.first_sale_date == 0) goto L10;
        r6.addRowDateTime(LocaleController.getString(R.string.Gift2SoldOutSheetFirstSale), r20.first_sale_date);
    L10:
        if (r20.last_sale_date == 0) goto L12;
        r6.addRowDateTime(LocaleController.getString(R.string.Gift2SoldOutSheetLastSale), r20.last_sale_date);
    L12:
        r6.addRow(LocaleController.getString(R.string.Gift2SoldOutSheetValue), replaceStarsWithPlain("⭐️ " + LocaleController.formatNumber(r20.stars, ','), 0.8f));
        if (r20.limited == false) goto L15;
        addAvailabilityRow(r6, r19, r20, r21);
    L15:
        r5.addView(r6, LayoutHelper.createLinear(-1, -2, 0.0f, 17.0f, 0.0f, 12.0f));
        ButtonWithCounterView r1 = new ButtonWithCounterView(r18, r21);
        r1.setText(LocaleController.getString(R.string.OK), false);
        r5.addView(r1, LayoutHelper.createLinear(-1, 48));
        r3.setCustomView(r5);
        BottomSheet[] r0 = {r3.create()};
        r0[0].useBackgroundTopPadding = false;
        r1.setOnClickListener(new StarsIntroActivity$$ExternalSyntheticLambda68(r0));
        r0[0].fixNavigationBar();
        BaseFragment r12 = LaunchActivity.getSafeLastFragment();
        if (AndroidUtilities.isTablet() == false) goto L18;
    L20:
        r0[0].show();
        return r0[0];
    L18:
        if (AndroidUtilities.hasDialogOnTop(r12) == true) goto L20;
        r0[0].makeAttached(r12);
        goto L20
    L24:
        return null;
    L22:
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v18, types: [boolean] */
    /* JADX WARN: Type inference failed for: r8v19 */
    public static BottomSheet showSubscriptionSheet(Context r35, int r36, TL_stars.StarsSubscription r37, Theme.ResourcesProvider r38) {
        if (r37 == null) goto L122;
        if (r35 == null) goto L124;
        BottomSheet.Builder r11 = new BottomSheet.Builder(r35, false, r38);
        BottomSheet[] r4 = new BottomSheet[1];
        LinearLayout r13 = new LinearLayout(r35);
        r13.setOrientation(1);
        r13.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(20.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(4.0f));
        r13.setClipChildren(false);
        r13.setClipToPadding(false);
        FrameLayout r1 = new FrameLayout(r35);
        r13.addView(r1, LayoutHelper.createLinear(-1, -2, 7, 0, 0, 0, 10));
        boolean[] r6 = new boolean[1];
        AnonymousClass12 r14 = new AnonymousClass12(r6, r4);
        NotificationCenter.getInstance(r36).addObserver(r14, NotificationCenter.starSubscriptionsLoaded);
        long r9 = DialogObject.getPeerDialogId(r37.peer);
        BackupImageView r15 = new BackupImageView(r35);
        String r22 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r9 < 0) goto L9;
        TLRPC.User r0 = MessagesController.getInstance(r36).getUser(Long.valueOf(r9));
        String r12 = UserObject.getUserName(r0);
        boolean r16 = UserObject.isBot(r0);
        boolean[] r24 = r6;
        BottomSheet.Builder r23 = r11;
        boolean r62 = r16;
        boolean r112 = !r16;
        TLRPC.Chat r02 = r0;
    L15:
        if (r37.photo == null) goto L18;
        r15.setRoundRadius(AndroidUtilities.dp(21.0f));
        r15.setImage(ImageLocation.getForWebFile(WebFile.createWithWebDocument(r37.photo)), "100_100", null, 0, null);
        TLRPC.Chat r162 = r02;
        boolean r17 = r62;
    L17:
        boolean r18 = r112;
        String r63 = r12;
    L22:
        r1.addView(r15, LayoutHelper.createFrame(100, 100, 17));
        Drawable r03 = r35.getResources().getDrawable(R.drawable.star_small_outline);
        r03.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogBackground, r38), PorterDuff.Mode.SRC_IN));
        Drawable r113 = r35.getResources().getDrawable(R.drawable.star_small_inner);
        if (r37.photo != null) goto L25;
        ImageView r122 = new ImageView(r35);
        r122.setImageDrawable(r03);
        r1.addView(r122, LayoutHelper.createFrame(28, 28, 17));
        r122.setTranslationX(AndroidUtilities.dp(34.0f));
        r122.setTranslationY(AndroidUtilities.dp(35.0f));
        r122.setScaleX(1.1f);
        r122.setScaleY(1.1f);
        ImageView r123 = new ImageView(r35);
        r123.setImageDrawable(r113);
        r1.addView(r123, LayoutHelper.createFrame(28, 28, 17));
        r123.setTranslationX(AndroidUtilities.dp(34.0f));
        r123.setTranslationY(AndroidUtilities.dp(35.0f));
    L25:
        TextView r04 = new TextView(r35);
        r04.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r38));
        r04.setTextSize(1, 20.0f);
        r04.setTypeface(AndroidUtilities.bold());
        r04.setGravity(17);
        if (TextUtils.isEmpty(r37.title) == true) goto L28;
        r04.setText(r37.title);
    L29:
        r13.addView(r04, LayoutHelper.createLinear(-1, -2, 17, 20, 0, 20, 4));
        TextView r05 = new TextView(r35);
        r05.setTextSize(1, 14.0f);
        r05.setGravity(17);
        r05.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4, r38));
        TL_stars.TL_starsSubscriptionPricing r114 = r37.pricing;
        int r124 = r114.period;
        if (r124 != 2592000) goto L33;
        r05.setText(replaceStarsWithPlain(LocaleController.formatString(R.string.StarsSubscriptionPrice, new Object[]{Long.valueOf(r114.amount)}), 0.8f));
    L37:
        r13.addView(r05, LayoutHelper.createLinear(-1, -2, 17, 20, 0, 20, 4));
        TableView r06 = new TableView(r35, r38);
        LinkSpanDrawable.LinksTextView r19 = new LinkSpanDrawable.LinksTextView(r35, r38);
        r19.setPadding(AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f), AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f));
        r19.setEllipsize(TextUtils.TruncateAt.END);
        int r2 = Theme.key_chat_messageLinkIn;
        r19.setTextColor(Theme.getColor(r2, r38));
        r19.setLinkTextColor(Theme.getColor(r2, r38));
        r19.setTextSize(1, 14.0f);
        r19.setSingleLine(true);
        r19.setDisablePaddingsOffsetY(true);
        AvatarSpan r8 = new AvatarSpan(r19, r36, 24.0f);
        if (r9 < 0) goto L47;
        TLRPC.User r115 = MessagesController.getInstance(r36).getUser(Long.valueOf(r9));
        if (r115 != null) goto L42;
    L45:
        boolean r125 = true;
    L46:
        String r20 = UserObject.getUserName(r115);
        r8.setUser(r115);
        boolean r142 = r125;
        AnonymousClass12 r126 = r14;
        String r116 = r20;
    L54:
        String r222 = r63;
        SpannableStringBuilder r152 = new SpannableStringBuilder("x  " + r116);
        r152.setSpan(r8, 0, 1, 33);
        r152.setSpan(new AnonymousClass13(r4, r9), 3, r152.length(), 33);
        r19.setText(r152);
        if (r142 == true) goto L63;
        if (r9 >= 0) goto L58;
        int r3 = R.string.StarsSubscriptionChannel;
    L61:
        r06.addRowUnpadded(LocaleController.getString(r3), r19);
        goto L63
    L58:
        if (r18 == false) goto L60;
        r3 = R.string.StarsSubscriptionBusiness;
        goto L61
    L60:
        r3 = R.string.StarsSubscriptionBot;
    L63:
        if (r9 >= 0) goto L65;
    L70:
        r06.addRow(LocaleController.getString(R.string.StarsSubscriptionSince), LocaleController.formatString(R.string.formatDateAtTime, new Object[]{LocaleController.getInstance().getFormatterGiveawayCard().format(new Date((r37.until_date - r37.pricing.period) * 1000)), LocaleController.getInstance().getFormatterDay().format(new Date((r37.until_date - r37.pricing.period) * 1000))}));
        long r82 = ConnectionsManager.getInstance(r36).getCurrentTime();
        if (r37.canceled == false) goto L73;
    L79:
        int r110 = R.string.StarsSubscriptionUntilExpires;
    L80:
        r06.addRow(LocaleController.getString(r110), LocaleController.formatString(R.string.formatDateAtTime, new Object[]{LocaleController.getInstance().getFormatterGiveawayCard().format(new Date(r37.until_date * 1000)), LocaleController.getInstance().getFormatterDay().format(new Date(r37.until_date * 1000))}));
        r13.addView(r06, LayoutHelper.createLinear(-1, -2, 0.0f, 17.0f, 0.0f, 0.0f));
        LinkSpanDrawable.LinksTextView r07 = new LinkSpanDrawable.LinksTextView(r35, r38);
        int r111 = Theme.key_windowBackgroundWhiteGrayText2;
        r07.setTextColor(Theme.getColor(r111, r38));
        r07.setLinkTextColor(Theme.getColor(r2, r38));
        r07.setTextSize(1, 14.0f);
        r07.setText(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.StarsTransactionTOS), new StarsIntroActivity$$ExternalSyntheticLambda69(r35)));
        r07.setGravity(17);
        r13.addView(r07, LayoutHelper.createLinear(-1, -2, 14.0f, 15.0f, 14.0f, 7.0f));
        if (r82 < r37.until_date) goto L83;
        LinkSpanDrawable.LinksTextView r08 = new LinkSpanDrawable.LinksTextView(r35, r38);
        r08.setTextColor(Theme.getColor(r111, r38));
        r08.setLinkTextColor(Theme.getColor(r2, r38));
        r08.setTextSize(1, 14.0f);
        r08.setText(LocaleController.formatString(R.string.StarsSubscriptionExpiredInfo, new Object[]{LocaleController.formatDateChat(r37.until_date)}));
        r08.setSingleLine(false);
        r08.setMaxLines(4);
        r08.setGravity(17);
        r13.addView(r08, LayoutHelper.createLinear(-1, -2, 26.0f, 7.0f, 26.0f, 15.0f));
        if (r37.chat_invite_hash == null) goto L113;
    L114:
        ButtonWithCounterView r117 = new ButtonWithCounterView(r35, true, r38).setRound();
        ?? r83 = 0;
        r117.setText(LocaleController.getString(R.string.StarsSubscriptionAgain), false);
        r13.addView(r117, LayoutHelper.createLinear(-1, 48));
        BottomSheet[] r42 = r4;
        int r32 = r36;
        r117.setOnClickListener(new StarsIntroActivity$$ExternalSyntheticLambda73(r117, r37, r32, r42, r38, r24, r35));
        BottomSheet.Builder r09 = r23;
    L115:
        r09.setCustomView(r13);
        BottomSheet r010 = r09.create();
        r42[r83] = r010;
        r010.useBackgroundTopPadding = r83;
        r010.setOnDismissListener(new StarsIntroActivity$$ExternalSyntheticLambda74(r32, r126));
        r42[r83].fixNavigationBar();
        BaseFragment r011 = LaunchActivity.getSafeLastFragment();
        if (AndroidUtilities.isTablet() == false) goto L118;
    L120:
        r42[r83].show();
        return r42[r83];
    L118:
        if (AndroidUtilities.hasDialogOnTop(r011) == true) goto L120;
        r42[r83].makeAttached(r011);
        goto L120
    L113:
        if (r37.invoice_slug != null) goto L114;
    L101:
        r32 = r36;
        r42 = r4;
    L93:
        r09 = r23;
        r83 = 0;
        goto L115
    L83:
        if (r37.can_refulfill == false) goto L95;
        LinkSpanDrawable.LinksTextView r012 = new LinkSpanDrawable.LinksTextView(r35, r38);
        r012.setTextColor(Theme.getColor(r111, r38));
        r012.setLinkTextColor(Theme.getColor(r2, r38));
        r012.setTextSize(1, 14.0f);
        if (r17 == false) goto L87;
        int r118 = R.string.StarsSubscriptionBotRefulfillInfo;
    L88:
        r012.setText(LocaleController.formatString(r118, new Object[]{LocaleController.formatDateChat(r37.until_date)}));
        r012.setSingleLine(false);
        r012.setMaxLines(4);
        r012.setGravity(17);
        r13.addView(r012, LayoutHelper.createLinear(-1, -2, 26.0f, 7.0f, 26.0f, 15.0f));
        ButtonWithCounterView r119 = new ButtonWithCounterView(r35, true, r38);
        if (r17 == false) goto L91;
        int r013 = R.string.StarsSubscriptionBotRefulfill;
    L92:
        r119.setText(LocaleController.getString(r013), false);
        r13.addView(r119, LayoutHelper.createLinear(-1, 48));
        r42 = r4;
        r119.setOnClickListener(new StarsIntroActivity$$ExternalSyntheticLambda70(r119, r36, r37, r42, r9, r35, r38, r18, r222));
        r32 = r36;
        goto L93
    L91:
        r013 = R.string.StarsSubscriptionRefulfill;
        goto L92
    L87:
        r118 = R.string.StarsSubscriptionRefulfillInfo;
        goto L88
    L95:
        if (r37.bot_canceled == false) goto L103;
        LinkSpanDrawable.LinksTextView r014 = new LinkSpanDrawable.LinksTextView(r35, r38);
        r014.setTextColor(Theme.getColor(Theme.key_color_red, r38));
        r014.setLinkTextColor(Theme.getColor(r2, r38));
        r014.setTextSize(1, 14.0f);
        if (r18 == false) goto L99;
        int r120 = R.string.StarsSubscriptionBusinessCancelledText;
    L100:
        r014.setText(LocaleController.getString(r120));
        r014.setSingleLine(false);
        r014.setMaxLines(4);
        r014.setGravity(17);
        r13.addView(r014, LayoutHelper.createLinear(-1, -2, 26.0f, 7.0f, 26.0f, 15.0f));
        goto L101
    L99:
        r120 = R.string.StarsSubscriptionBotCancelledText;
        goto L100
    L103:
        if (r37.canceled == false) goto L109;
        LinkSpanDrawable.LinksTextView r015 = new LinkSpanDrawable.LinksTextView(r35, r38);
        r015.setTextColor(Theme.getColor(Theme.key_color_red, r38));
        r015.setLinkTextColor(Theme.getColor(r2, r38));
        r015.setTextSize(1, 14.0f);
        r015.setText(LocaleController.getString(R.string.StarsSubscriptionCancelledText));
        r015.setSingleLine(false);
        r015.setMaxLines(4);
        r015.setGravity(17);
        r13.addView(r015, LayoutHelper.createLinear(-1, -2, 26.0f, 7.0f, 26.0f, 15.0f));
        if (r37.chat_invite_hash == null) goto L107;
    L108:
        ButtonWithCounterView r121 = new ButtonWithCounterView(r35, true, r38);
        r121.setText(LocaleController.getString(R.string.StarsSubscriptionRenew), false);
        r13.addView(r121, LayoutHelper.createLinear(-1, 48));
        r42 = r4;
        r32 = r36;
        r121.setOnClickListener(new StarsIntroActivity$$ExternalSyntheticLambda71(r121, r37, r32, r42, r162, r222));
        goto L93
    L107:
        if (r37.invoice_slug == null) goto L101;
    L109:
        LinkSpanDrawable.LinksTextView r016 = new LinkSpanDrawable.LinksTextView(r35, r38);
        r016.setTextColor(Theme.getColor(r111, r38));
        r016.setLinkTextColor(Theme.getColor(r2, r38));
        r016.setTextSize(1, 14.0f);
        r016.setText(LocaleController.formatString(R.string.StarsSubscriptionCancelInfo, new Object[]{LocaleController.formatDateChat(r37.until_date)}));
        r016.setSingleLine(false);
        r016.setMaxLines(4);
        r016.setGravity(17);
        r13.addView(r016, LayoutHelper.createLinear(-1, -2, 26.0f, 7.0f, 26.0f, 15.0f));
        ButtonWithCounterView r127 = new ButtonWithCounterView(r35, false, r38);
        r127.setText(LocaleController.getString(R.string.StarsSubscriptionCancel), false);
        r127.setTextColor(Theme.getColor(Theme.key_color_red, r38));
        r13.addView(r127, LayoutHelper.createLinear(-1, 48));
        boolean r5 = r18;
        r42 = r4;
        r32 = r36;
        r127.setOnClickListener(new StarsIntroActivity$$ExternalSyntheticLambda72(r127, r37, r32, r42, r5, r17, r162));
        goto L93
    L73:
        if (r37.bot_canceled == true) goto L79;
        if (r82 <= r37.until_date) goto L78;
        r110 = R.string.StarsSubscriptionUntilExpired;
        goto L80
    L78:
        r110 = R.string.StarsSubscriptionUntilRenews;
        goto L80
    L65:
        if (TextUtils.isEmpty(r37.title) == true) goto L70;
        if (r18 == false) goto L68;
        int r128 = R.string.StarsSubscriptionBusinessProduct;
    L69:
        r06.addRow(LocaleController.getString(r128), r37.title);
        goto L70
    L68:
        r128 = R.string.StarsSubscriptionBotProduct;
        goto L69
    L42:
        if (UserObject.isDeleted(r115) == true) goto L45;
        r125 = false;
        goto L46
    L47:
        r126 = r14;
        TLRPC.Chat r1110 = MessagesController.getInstance(r36).getChat(Long.valueOf(-r9));
        if (r1110 != null) goto L50;
        r142 = true;
    L51:
        if (r1110 == null) goto L53;
        r22 = r1110.title;
    L53:
        r8.setChat(r1110);
        r116 = r22;
        goto L54
    L50:
        r142 = false;
        goto L51
    L33:
        if (r124 != 300) goto L35;
        String r129 = "5min";
    L36:
        r05.setText(replaceStarsWithPlain(LocaleController.formatString(R.string.StarsSubscriptionPrice, new Object[]{Long.valueOf(r114.amount), r129}), 0.8f));
        goto L37
    L35:
        r129 = "min";
        goto L36
    L28:
        r04.setText(LocaleController.getString(R.string.StarsSubscriptionTitle));
        goto L29
    L18:
        r15.setRoundRadius(AndroidUtilities.dp(50.0f));
        AvatarDrawable r84 = new AvatarDrawable();
        if (r9 < 0) goto L21;
        r162 = r02;
        r17 = r62;
        TLRPC.User r017 = MessagesController.getInstance(r36).getUser(Long.valueOf(r9));
        r84.setInfo(r017);
        r15.setForUserOrChat(r017, r84);
        goto L17
    L21:
        r162 = r02;
        r17 = r62;
        r18 = r112;
        r63 = r12;
        TLRPC.Chat r018 = MessagesController.getInstance(r36).getChat(Long.valueOf(-r9));
        r84.setInfo(r018);
        r15.setForUserOrChat(r018, r84);
        goto L22
    L9:
        r23 = r11;
        TLRPC.Chat r019 = MessagesController.getInstance(r36).getChat(Long.valueOf(-r9));
        if (r019 != null) goto L12;
        r12 = _UrlKt.FRAGMENT_ENCODE_SET;
    L13:
        r24 = r6;
        r62 = false;
        r112 = false;
        r02 = r019;
        goto L15
    L12:
        r12 = r019.title;
        goto L13
    L124:
        return null;
    L122:
        return null;
    }

    public static BottomSheet showTransactionSheet(Context r3, int r4, int r5, TLRPC.Peer r6, TLRPC.Peer r7, TLRPC.TL_messageActionGiftStars r8, Theme.ResourcesProvider r9) {
        TL_stars.StarsTransaction r82 = new TL_stars.StarsTransaction();
        r82.title = null;
        r82.description = null;
        r82.photo = null;
        TL_stars.TL_starsTransactionPeer r1 = new TL_stars.TL_starsTransactionPeer();
        r82.peer = r1;
        r1.peer = r6;
        r82.date = r5;
        r82.amount = TL_stars.StarsAmount.ofStars(r8.stars);
        r82.id = r8.transaction_id;
        r82.gift = true;
        r82.sent_by = r6;
        r82.received_by = r7;
        return showTransactionSheet(r3, false, 0, r4, r82, r9);
    }

    public static BottomSheet showTransactionSheet(Context r3, int r4, int r5, TLRPC.Peer r6, TLRPC.Peer r7, TLRPC.TL_messageActionGiftTon r8, Theme.ResourcesProvider r9) {
        TL_stars.StarsTransaction r82 = new TL_stars.StarsTransaction();
        r82.title = null;
        r82.description = null;
        r82.photo = null;
        TL_stars.TL_starsTransactionPeer r1 = new TL_stars.TL_starsTransactionPeer();
        r82.peer = r1;
        r1.peer = r6;
        r82.date = r5;
        TL_stars.TL_starsTonAmount r52 = new TL_stars.TL_starsTonAmount();
        r82.amount = r52;
        r52.amount = r8.cryptoAmount;
        r82.id = r8.transaction_id;
        r82.gift = true;
        r82.sent_by = r6;
        r82.received_by = r7;
        return showTransactionSheet(r3, false, 0, r4, r82, r9);
    }

    public static BottomSheet showTransactionSheet(Context r3, int r4, int r5, TLRPC.Peer r6, TLRPC.Peer r7, TLRPC.TL_messageActionPrizeStars r8, Theme.ResourcesProvider r9) {
        TL_stars.StarsTransaction r82 = new TL_stars.StarsTransaction();
        r82.title = null;
        r82.description = null;
        r82.photo = null;
        TL_stars.TL_starsTransactionPeer r1 = new TL_stars.TL_starsTransactionPeer();
        r82.peer = r1;
        r1.peer = r8.boost_peer;
        r82.date = r5;
        r82.amount = TL_stars.StarsAmount.ofStars(r8.stars);
        r82.id = r8.transaction_id;
        r82.gift = true;
        r82.flags |= 8192;
        r82.giveaway_post_id = r8.giveaway_msg_id;
        r82.sent_by = r6;
        r82.received_by = r7;
        return showTransactionSheet(r3, false, 0, r4, r82, r9);
    }

    public static BottomSheet showTransactionSheet(Context r7, int r8, int r9, TLRPC.TL_messageActionPaymentRefunded r10, Theme.ResourcesProvider r11) {
        TL_stars.StarsTransaction r5 = new TL_stars.StarsTransaction();
        r5.title = null;
        r5.description = null;
        r5.photo = null;
        TL_stars.TL_starsTransactionPeer r0 = new TL_stars.TL_starsTransactionPeer();
        r5.peer = r0;
        r0.peer = r10.peer;
        r5.date = r9;
        r5.amount = TL_stars.StarsAmount.ofStars(r10.total_amount);
        r5.id = r10.charge.id;
        r5.refund = true;
        return showTransactionSheet(r7, false, 0, r8, r5, r11);
    }

    public static BottomSheet showTransactionSheet(Context r7, boolean r8, int r9, TLRPC.TL_payments_paymentReceiptStars r10, Theme.ResourcesProvider r11) {
        TL_stars.StarsTransaction r5 = new TL_stars.StarsTransaction();
        r5.title = r10.title;
        r5.description = r10.description;
        r5.photo = r10.photo;
        TL_stars.TL_starsTransactionPeer r0 = new TL_stars.TL_starsTransactionPeer();
        r5.peer = r0;
        r0.peer = MessagesController.getInstance(r9).getPeer(r10.bot_id);
        r5.date = r10.date;
        r5.amount = TL_stars.StarsAmount.ofStars(-r10.total_amount);
        r5.id = r10.transaction_id;
        return showTransactionSheet(r7, r8, 0, r9, r5, r11);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [org.telegram.ui.Components.TableView] */
    /* JADX WARN: Type inference failed for: r0v23, types: [android.view.View, org.telegram.ui.Components.TableView] */
    /* JADX WARN: Type inference failed for: r0v61, types: [android.view.View, android.view.ViewGroup, android.widget.LinearLayout, org.telegram.ui.Stars.StarsIntroActivity$8] */
    /* JADX WARN: Type inference failed for: r0v68 */
    /* JADX WARN: Type inference failed for: r0v69 */
    /* JADX WARN: Type inference failed for: r0v70 */
    /* JADX WARN: Type inference failed for: r0v71 */
    /* JADX WARN: Type inference failed for: r0v72 */
    /* JADX WARN: Type inference failed for: r0v73 */
    /* JADX WARN: Type inference failed for: r0v74 */
    /* JADX WARN: Type inference failed for: r0v75 */
    /* JADX WARN: Type inference failed for: r0v76 */
    /* JADX WARN: Type inference failed for: r0v77 */
    /* JADX WARN: Type inference failed for: r0v78 */
    /* JADX WARN: Type inference failed for: r0v79 */
    /* JADX WARN: Type inference failed for: r1v170 */
    /* JADX WARN: Type inference failed for: r1v171 */
    /* JADX WARN: Type inference failed for: r1v172 */
    /* JADX WARN: Type inference failed for: r1v20, types: [android.view.View, android.widget.TextView, org.telegram.ui.Components.LinkSpanDrawable$LinksTextView] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6, types: [android.view.ViewGroup] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r2v59, types: [android.view.View, android.view.ViewGroup] */
    /* JADX WARN: Type inference failed for: r3v48, types: [android.view.View, android.widget.TextView, org.telegram.ui.Components.LinkSpanDrawable$LinksTextView] */
    /* JADX WARN: Type inference failed for: r3v95, types: [org.telegram.ui.ActionBar.BottomSheet$Builder] */
    /* JADX WARN: Type inference failed for: r5v138, types: [org.telegram.ui.Components.AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable] */
    /* JADX WARN: Type inference failed for: r5v34 */
    /* JADX WARN: Type inference failed for: r5v35, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r5v44 */
    /* JADX WARN: Type inference failed for: r5v94, types: [android.view.View, android.widget.TextView, org.telegram.ui.Components.LinkSpanDrawable$LinksTextView] */
    /* JADX WARN: Type inference failed for: r8v1, types: [android.view.View, android.view.ViewGroup, android.widget.LinearLayout] */
    public static BottomSheet showTransactionSheet(Context r55, boolean r56, long r57, int r59, TL_stars.StarsTransaction r60, Theme.ResourcesProvider r61) {
        if (r60 == null) goto L5;
        if (r55 == null) goto L435;
        TL_stars.StarsAmount r0 = r60.amount;
        boolean r10 = r0 instanceof TL_stars.TL_starsTonAmount;
        int r2 = r60.flags;
        if ((r2 & 8192) == 0) goto L9;
        boolean r13 = true;
    L11:
        if ((131072 & r2) != 0) goto L13;
    L15:
        boolean r14 = false;
    L16:
        if (r14 == false) goto L18;
    L22:
        boolean r15 = false;
    L23:
        boolean r16 = r0.positive();
        boolean r17 = r60.amount.negative();
        BottomSheet.Builder r02 = new BottomSheet.Builder(r55, false, r61);
        BottomSheet[] r22 = new BottomSheet[1];
        ?? r8 = new LinearLayout(r55);
        r8.setOrientation(1);
        if (r13 == false) goto L26;
    L33:
        float r4 = 0.0f;
    L34:
        r8.setPadding(0, AndroidUtilities.dp(r4), 0, AndroidUtilities.dp(8.0f));
        r8.setClipChildren(false);
        r8.setClipToPadding(false);
        String r20 = "+";
        if (r60.stargift_resale == false) goto L57;
        TL_stars.StarGift r42 = r60.stargift;
        if ((r42 instanceof TL_stars.TL_starGiftUnique) == false) goto L55;
        TL_stars.TL_starGiftUnique r3 = (TL_stars.TL_starGiftUnique) r42;
        TL_stars.starGiftAttributeBackdrop r9 = (TL_stars.starGiftAttributeBackdrop) StarsController.findAttribute(r3.attributes, TL_stars.starGiftAttributeBackdrop.class);
        TL_stars.starGiftAttributePattern r03 = (TL_stars.starGiftAttributePattern) StarsController.findAttribute(r3.attributes, TL_stars.starGiftAttributePattern.class);
        ?? r5 = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(null, AndroidUtilities.dp(20.0f));
        RadialGradient r33 = new RadialGradient(0.0f, 0.0f, AndroidUtilities.dp(200.0f), new int[]{r9.center_color | (-16777216), r9.edge_color | (-16777216)}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
        boolean r332 = r13;
        Paint r43 = new Paint(1);
        Matrix r23 = new Matrix();
        r43.setShader(r33);
        boolean r30 = r14;
        String r41 = "fragment";
        BottomSheet.Builder r422 = r02;
        ?? r04 = new AnonymousClass8(r55, r23, r33, r43, r5);
        r5.setParentView(r04);
        r5.set(r03.document, false);
        r04.setOrientation(1);
        BackupImageView r24 = new BackupImageView(r55);
        setGiftImage(r24.getImageReceiver(), r60.stargift, 160);
        r04.addView(r24, LayoutHelper.createLinear(160, 160, 17, 0, 20, 0, 0));
        if (TextUtils.isEmpty(r3.slug) == true) goto L41;
        ScaleStateListAnimator.apply(r24);
        r24.setOnClickListener(new StarsIntroActivity$$ExternalSyntheticLambda0(r55, r59, r3));
    L41:
        TextView r25 = TextHelper.makeTextView(r55, 20.0f, 0, true);
        r25.setTextColor(-1);
        r25.setText(r3.title);
        r04.addView(r25, LayoutHelper.createLinear(-2, -2, 17, 0, 1, 0, 0));
        TextView r26 = TextHelper.makeTextView(r55, 13.0f, 0, false);
        r26.setTextColor(r9.text_color | (-16777216));
        r26.setText(LocaleController.formatPluralStringComma("Gift2CollectionNumber", r3.num));
        r04.addView(r26, LayoutHelper.createLinear(-2, -2, 17, 0, 5, 0, 0));
        TextView r27 = TextHelper.makeTextView(r55, 18.0f, 0, true);
        r27.setTextColor(-1);
        TL_stars.StarsAmount r44 = r60.amount;
        if (r16 == true) goto L45;
        r20 = _UrlKt.FRAGMENT_ENCODE_SET;
    L45:
        r27.setText(replaceStars(r44, TextUtils.concat(new CharSequence[]{r20, formatStarsAmount(r44), " ⭐️"}), 1.25f));
        SpannableStringBuilder r45 = new SpannableStringBuilder(r27.getText());
        if (r60.refund == false) goto L49;
        appendStatus(r45, r27, LocaleController.getString(R.string.StarsRefunded));
    L54:
        r27.setText(r45);
        r04.addView(r27, LayoutHelper.createLinear(-2, -2, 17, 0, 11, 0, 17));
        r8.addView(r04, LayoutHelper.createLinear(-1, -2));
        Context r92 = r55;
        int r28 = r59;
        TL_stars.StarsTransaction r6 = r60;
        Object r1 = r8;
        boolean r34 = r15;
        BottomSheet[] r82 = r22;
        Theme.ResourcesProvider r05 = r61;
    L187:
        TableView r32 = new TableView(r92, r05);
        TL_stars.StarGift r46 = r6.stargift;
        if (r46 != null) goto L190;
        Object r52 = r1;
        ?? r06 = r32;
        BottomSheet[] r93 = r82;
        TL_stars.StarsTransaction r83 = r6;
        TL_stars.StarsTransactionPeer r12 = r83.peer;
        if ((r12 instanceof TL_stars.TL_starsTransactionPeer) == false) goto L333;
        long r35 = DialogObject.getPeerDialogId(r12.peer);
        if (r83.paid_message == false) goto L314;
        if (r16 == false) goto L307;
        int r18 = R.string.Gift2From;
    L308:
        r06.addRowUser(LocaleController.getString(r18), r59, r35, new StarsIntroActivity$$ExternalSyntheticLambda6(r93, r35));
        TableView r07 = r06;
        if (r83.starref_amount != null) goto L310;
    L298:
        Context r62 = r55;
        r28 = r59;
        BottomSheet[] r7 = r93;
        TableView r08 = r07;
    L299:
        TL_stars.StarsTransaction r94 = r83;
        TableView r09 = r08;
    L200:
        Theme.ResourcesProvider r84 = r61;
        ?? r010 = r09;
    L352:
        TL_stars.StarsTransactionPeer r19 = r94.peer;
        if ((r19 instanceof TL_stars.TL_starsTransactionPeer) == true) goto L355;
    L388:
        boolean r162 = r10;
    L390:
        if (TextUtils.isEmpty(r94.id) == true) goto L398;
        if (r332 == true) goto L398;
        String r110 = LocaleController.getString(R.string.StarsTransactionID);
        String r36 = r94.id;
        if (r36.length() <= 25) goto L395;
        int r47 = 9;
    L396:
        r010.addRowMonospaced(r110, r36, r47, new StarsIntroActivity$$ExternalSyntheticLambda21(r7, r84));
        goto L398
    L395:
        r47 = 10;
    L398:
        if (r94.floodskip == true) goto L400;
    L402:
        r010.addRow(LocaleController.getString(R.string.StarsTransactionDate), LocaleController.formatString(R.string.formatDateAtTime, new Object[]{LocaleController.getInstance().getFormatterGiveawayCard().format(new Date(r94.date * 1000)), LocaleController.getInstance().getFormatterDay().format(new Date(r94.date * 1000))}));
        TL_stars.StarGift r111 = r94.stargift;
        if (r111 != null) goto L405;
    L410:
        ?? r29 = r52;
        r29.addView(r010, LayoutHelper.createLinear(-1, -2, 16.0f, 17.0f, 16.0f, 0.0f));
        if ((r94.flags & 32) == 0) goto L413;
        r010.addRow(LocaleController.getString(R.string.StarsTransactionTONDate), LocaleController.formatString(R.string.formatDateAtTime, new Object[]{LocaleController.getInstance().getFormatterGiveawayCard().format(new Date(r94.transaction_date * 1000)), LocaleController.getInstance().getFormatterDay().format(new Date(r94.transaction_date * 1000))}));
    L413:
        if (r162 == true) goto L415;
        Context r112 = r55;
        LinkSpanDrawable.LinksTextView r011 = new LinkSpanDrawable.LinksTextView(r112, r84);
        r011.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r84));
        r011.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r84));
        r011.setTextSize(1, 14.0f);
        r011.setText(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.StarsTransactionTOS), new StarsIntroActivity$$ExternalSyntheticLambda23(r112)));
        r011.setGravity(17);
        r29.addView(r011, LayoutHelper.createLinear(-1, -2, 16.0f, 15.0f, 16.0f, 0.0f));
    L416:
        ButtonWithCounterView r012 = new ButtonWithCounterView(r112, r84).setRound();
        if ((r94.flags & 32) == 0) goto L419;
        r012.setText(LocaleController.getString(R.string.StarsTransactionViewInBlockchainExplorer), false);
    L420:
        r29.addView(r012, LayoutHelper.createLinear(-1, 48, 16.0f, 15.0f, 16.0f, 0.0f));
        ?? r37 = r422;
        r37.setCustomView(r29);
        BottomSheet r210 = r37.create();
        r7[0] = r210;
        r210.useBackgroundTopPadding = false;
        if ((r94.flags & 32) == 0) goto L424;
        r012.setOnClickListener(new StarsIntroActivity$$ExternalSyntheticLambda24(r112, r94));
    L425:
        r7[0].fixNavigationBar();
        BaseFragment r013 = LaunchActivity.getSafeLastFragment();
        if (AndroidUtilities.isTablet() == false) goto L428;
    L430:
        r7[0].show();
        return r7[0];
    L428:
        if (AndroidUtilities.hasDialogOnTop(r013) == true) goto L430;
        r7[0].makeAttached(r013);
        goto L430
    L424:
        r012.setOnClickListener(new StarsIntroActivity$$ExternalSyntheticLambda25(r7));
        goto L425
    L419:
        r012.setText(LocaleController.getString(R.string.OK), false);
        goto L420
    L415:
        r112 = r55;
        goto L416
    L405:
        if (r111.limited == false) goto L408;
        addAvailabilityRow(r010, r28, r111, r84);
    L408:
        if (TextUtils.isEmpty(r94.description) == true) goto L410;
        r010.addFullRow(new SpannableStringBuilder(r94.description));
        goto L410
    L400:
        if (r94.floodskip_number <= 0) goto L402;
        r010.addRow(LocaleController.getString(R.string.StarsTransactionFloodskipNumberName), LocaleController.formatPluralStringComma("StarsTransactionFloodskipNumber", r94.floodskip_number));
        goto L402
    L355:
        if ((r94.flags & 256) == 0) goto L388;
        long r38 = DialogObject.getPeerDialogId(r19.peer);
        if (r56 == false) goto L359;
        r38 = r57;
    L359:
        TLRPC.Chat r113 = MessagesController.getInstance(r28).getChat(Long.valueOf(-r38));
        if (r113 == null) goto L388;
        ?? r53 = new LinkSpanDrawable.LinksTextView(r62, r84);
        r162 = r10;
        r53.setPadding(AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f), AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f));
        r53.setEllipsize(TextUtils.TruncateAt.END);
        int r102 = Theme.key_chat_messageLinkIn;
        r53.setTextColor(Theme.getColor(r102, r84));
        r53.setLinkTextColor(Theme.getColor(r102, r84));
        r53.setTextSize(1, 14.0f);
        r53.setDisablePaddingsOffsetY(true);
        SpannableStringBuilder r103 = new SpannableStringBuilder(_UrlKt.FRAGMENT_ENCODE_SET);
        if (r94.extended_media.isEmpty() == true) goto L379;
        ArrayList<TLRPC.MessageMedia> r122 = r94.extended_media;
        int r132 = r122.size();
        int r142 = 0;
        int r152 = 0;
    L364:
        if (r152 >= r132) goto L379;
        TLRPC.MessageMedia r172 = r122.get(r152);
        int r153 = r152 + 1;
        ArrayList<TLRPC.MessageMedia> r182 = r122;
        TLRPC.MessageMedia r123 = r172;
        int r562 = r132;
        int r572 = r142;
        ImageReceiverSpan r133 = new ImageReceiverSpan(r53, r28, 24.0f);
        if ((r123 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L368;
        int r58 = r153;
        ImageLocation r352 = ImageLocation.getForPhoto(FileLoader.getClosestPhotoSizeWithSize(r123.photo.sizes, AndroidUtilities.dp(24.0f), true), r123.photo);
    L372:
        if (r352 == null) goto L375;
        r133.setRoundRadius(6.0f);
        r133.imageReceiver.setImage(r352, "24_24", null, null, null, 0);
        SpannableString r63 = new SpannableString("x");
        r63.setSpan(r133, 0, r63.length(), 33);
        r103.append(r63);
        r103.append(" ");
        r142 = r572 + 1;
    L376:
        if (r142 >= 3) goto L379;
        r132 = r562;
        r152 = r58;
        r122 = r182;
        goto L364
    L375:
        r142 = r572;
        goto L376
    L368:
        r58 = r153;
        if ((r123 instanceof TLRPC.TL_messageMediaDocument) == false) goto L371;
        r352 = ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r123.document.thumbs, AndroidUtilities.dp(24.0f), true), r123.document);
        goto L372
    L371:
        r352 = null;
    L379:
        r103.append(" ");
        int r64 = r103.length();
        String r11 = ChatObject.getPublicUsername(r113);
        if (TextUtils.isEmpty(r11) == false) goto L382;
        r103.append(r113.title);
    L383:
        StarsIntroActivity$$ExternalSyntheticLambda19 r114 = new StarsIntroActivity$$ExternalSyntheticLambda19(r7, r38, r94);
        r103.setSpan(new AnonymousClass11(r114), r64, r103.length(), 33);
        r53.setSingleLine(true);
        r53.setEllipsize(TextUtils.TruncateAt.END);
        r53.setText(r103);
        r53.setOnClickListener(new StarsIntroActivity$$ExternalSyntheticLambda20(r114));
        if (r94.reaction == false) goto L386;
        int r115 = R.string.StarsTransactionMessage;
    L387:
        r010.addRowUnpadded(LocaleController.getString(r115), r53);
        goto L390
    L386:
        r115 = R.string.StarsTransactionMedia;
        goto L387
    L382:
        r103.append(MessagesController.getInstance(r28).linkPrefix + "/" + r11 + "/" + r94.msg_id);
        goto L383
    L310:
        r07 = r06;
        if (r83.starref_commission_permille <= 0) goto L298;
        long r116 = Math.abs(Math.round(r83.amount.toDouble() + r83.starref_amount.toDouble()));
        r06.addRow(LocaleController.getString(R.string.StarsTransactionFullPrice), replaceStarsWithPlain(r83.amount, "⭐️ " + LocaleController.formatNumber(r116, ','), 0.8f));
        r07 = r06;
        goto L298
    L307:
        r18 = R.string.Gift2To;
        goto L308
    L314:
        if (r30 == false) goto L317;
        long r39 = DialogObject.getPeerDialogId(r83.starref_peer);
        r06.addRowLink(LocaleController.getString(R.string.StarAffiliateReason), LocaleController.getString(R.string.StarAffiliateReasonProgram), new StarsIntroActivity$$ExternalSyntheticLambda7(r93, r57));
        r28 = r59;
        r06.addRowUser(LocaleController.getString(R.string.StarAffiliate), r28, r39, new StarsIntroActivity$$ExternalSyntheticLambda8(r93, r39));
        r06.addRowUser(LocaleController.getString(R.string.StarAffiliateReferredUser), r28, r35, new StarsIntroActivity$$ExternalSyntheticLambda9(r93, r35));
        r06.addRow(LocaleController.getString(R.string.StarAffiliateCommission), AffiliateProgramFragment.percents(r83.starref_commission_permille));
        r62 = r55;
        r7 = r93;
        r08 = r06;
        goto L299
    L317:
        if (r34 == false) goto L319;
        r7 = r93;
        r94 = r83;
        r84 = r61;
        r62 = r55;
        r06.addRowLink(LocaleController.getString(R.string.StarAffiliateReason), LocaleController.getString(R.string.StarAffiliateReasonProgram), new StarsIntroActivity$$ExternalSyntheticLambda10(r59, r55, r57, r35, r7, r84));
        r28 = r59;
        TableView r014 = r06;
        r014.addRowUser(LocaleController.getString(R.string.StarAffiliateMiniApp), r28, r35, new StarsIntroActivity$$ExternalSyntheticLambda12(r7, r35));
        r010 = r014;
        goto L352
    L319:
        r62 = r55;
        r7 = r93;
        TableView r015 = r06;
        r94 = r83;
        r84 = r61;
        if (r332 == false) goto L324;
        r015.addRowUser(LocaleController.getString(R.string.StarGiveawayPrizeFrom), r59, r35, new StarsIntroActivity$$ExternalSyntheticLambda13(r7, r94, r35));
        r015.addRowUser(LocaleController.getString(R.string.StarGiveawayPrizeTo), r59, UserConfig.getInstance(r59).getClientUserId(), new StarsIntroActivity$$ExternalSyntheticLambda14(r7, r59));
        r015.addRowLink(LocaleController.getString(R.string.StarGiveawayReason), LocaleController.getString(R.string.StarGiveawayReasonLink), new StarsIntroActivity$$ExternalSyntheticLambda15(r7, r94, r35));
        r015.addRow(LocaleController.getString(R.string.StarGiveawayGift), formatStarsAmountString(r94.amount));
    L322:
        r28 = r59;
        r010 = r015;
        goto L352
    L324:
        if (r94.subscription == false) goto L328;
        if (r56 == true) goto L328;
        r28 = r59;
        r015.addRowUser(LocaleController.getString(R.string.StarSubscriptionTo), r28, r35, new StarsIntroActivity$$ExternalSyntheticLambda16(r7, r35, r62));
        r010 = r015;
    L328:
        if (r94.premium_gift == false) goto L331;
        r015.addRowUser(LocaleController.getString(R.string.Gift2To), r59, r35, new StarsIntroActivity$$ExternalSyntheticLambda17(r7, r35, r62));
        r015.addRow(LocaleController.getString(R.string.StarsTransactionPremiumGiftDuration), LocaleController.formatPluralStringComma("Months", r94.premium_gift_months));
        goto L322
    L331:
        if (r94.posts_search == true) goto L322;
        r28 = r59;
        r015.addRowUser(LocaleController.getString(R.string.StarsTransactionRecipient), r28, r35, new StarsIntroActivity$$ExternalSyntheticLambda18(r7, r35, r62));
        r010 = r015;
        goto L352
    L333:
        r62 = r55;
        r28 = r59;
        r7 = r93;
        r94 = r83;
        r84 = r61;
        if ((r12 instanceof TL_stars.TL_starsTransactionPeerFragment) == false) goto L344;
        if (r94.gift == false) goto L342;
        ?? r117 = new LinkSpanDrawable.LinksTextView(r62, r84);
        r117.setPadding(AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f), AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f));
        r117.setEllipsize(TextUtils.TruncateAt.END);
        int r310 = Theme.key_chat_messageLinkIn;
        r117.setTextColor(Theme.getColor(r310, r84));
        r117.setLinkTextColor(Theme.getColor(r310, r84));
        r117.setTextSize(1, 14.0f);
        r117.setSingleLine(true);
        r117.setDisablePaddingsOffsetY(true);
        AvatarSpan r311 = new AvatarSpan(r117, r28, 24.0f);
        if (r10 == false) goto L340;
        int r48 = R.string.StarsTransactionTONFromFragment;
    L341:
        String r49 = LocaleController.getString(r48);
        CombinedDrawable r54 = StarsTransactionView.getPlatformDrawable(r41, 24);
        r54.setIconSize(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        r311.setImageDrawable(r54);
        SpannableStringBuilder r510 = new SpannableStringBuilder("x  " + r49);
        r510.setSpan(r311, 0, 1, 33);
        r510.setSpan(new AnonymousClass10(r7, r62, r10), 3, r510.length(), 33);
        r117.setText(r510);
        r06.addRowUnpadded(LocaleController.getString(R.string.StarsTransactionRecipient), r117);
        r010 = r06;
        goto L352
    L340:
        r48 = R.string.StarsTransactionUnknown;
        goto L341
    L342:
        r06.addRow(LocaleController.getString(R.string.StarsTransactionSource), LocaleController.getString(R.string.Fragment));
        r010 = r06;
        goto L352
    L344:
        if ((r12 instanceof TL_stars.TL_starsTransactionPeerAppStore) == false) goto L347;
        r06.addRow(LocaleController.getString(R.string.StarsTransactionSource), LocaleController.getString(R.string.AppStore));
        r010 = r06;
        goto L352
    L347:
        if ((r12 instanceof TL_stars.TL_starsTransactionPeerPlayMarket) == false) goto L349;
        r06.addRow(LocaleController.getString(R.string.StarsTransactionSource), LocaleController.getString(R.string.PlayMarket));
        r010 = r06;
        goto L352
    L349:
        r010 = r06;
        if ((r12 instanceof TL_stars.TL_starsTransactionPeerPremiumBot) == false) goto L352;
        r06.addRow(LocaleController.getString(R.string.StarsTransactionSource), LocaleController.getString(R.string.StarsTransactionBot));
        r010 = r06;
        goto L352
    L190:
        if (r6.stargift_upgrade == true) goto L192;
        int r016 = r28;
        Object r211 = r1;
        if ((r46 instanceof TL_stars.TL_starGiftUnique) == false) goto L263;
        String r312 = r46.slug;
        if (TextUtils.isEmpty(r312) == true) goto L207;
        r32.addRowLink(LocaleController.getString(R.string.Gift2Gift), r6.stargift.title + " #" + r6.stargift.num, new StarsIntroActivity$$ExternalSyntheticLambda29(r92, r016, r312));
    L207:
        long r313 = UserConfig.getInstance(r016).getClientUserId();
        long r183 = DialogObject.getPeerDialogId(((TL_stars.TL_starsTransactionPeer) r6.peer).peer);
        if (r6.offer == false) goto L221;
        if (r17 == true) goto L215;
        String r511 = LocaleController.getString(R.string.StarGiftReason);
        if (r6.refund == false) goto L213;
        int r143 = R.string.StarGiftReasonOfferRefund;
    L214:
        r32.addRow(r511, LocaleController.getString(r143));
    L228:
        long r163 = r313;
    L240:
        if (r183 == r313) goto L242;
        Theme.ResourcesProvider r154 = r61;
        TableView r017 = r32;
        r52 = r211;
        BottomSheet[] r212 = r82;
        TL_stars.StarsTransaction r85 = r6;
        long r314 = r183;
        BottomSheet[] r432 = r212;
        long r184 = r313;
        r017.addRowUser(LocaleController.getString(R.string.Gift2From), r59, r314, new StarsIntroActivity$$ExternalSyntheticLambda30(r212, r314, r313));
        TableView r018 = r017;
    L244:
        if (r163 == r184) goto L246;
        long r315 = r163;
        BottomSheet[] r213 = r432;
        BottomSheet[] r65 = r213;
        r018.addRowUser(LocaleController.getString(R.string.Gift2To), r59, r315, new StarsIntroActivity$$ExternalSyntheticLambda31(r213, r315, r184));
    L247:
        if (r183 != r313) goto L249;
    L250:
        TL_stars.StarsAmount r118 = r85.starref_amount;
        if (r118 != null) goto L253;
    L262:
        r28 = r59;
        r7 = r65;
        r62 = r92;
        r94 = r85;
        r84 = r154;
        r010 = r018;
        goto L352
    L253:
        if (r85.starref_commission_permille <= 0) goto L262;
        TL_stars.StarsAmount r214 = r85.amount;
        if ((r214 instanceof TL_stars.TL_starsTonAmount) == true) goto L257;
    L261:
        long r119 = Math.abs(Math.round(r214.toDouble() + r85.starref_amount.toDouble()));
        r018.addRow(LocaleController.getString(R.string.StarsTransactionFullPrice), replaceStarsWithPlain(r85.amount, "⭐️ " + LocaleController.formatNumber(r119, ','), 0.8f));
        goto L262
    L257:
        if ((r118 instanceof TL_stars.TL_starsTonAmount) == false) goto L261;
        TL_stars.TL_starsTonAmount r120 = new TL_stars.TL_starsTonAmount();
        r120.amount = r85.amount.amount + r85.starref_amount.amount;
        ColoredImageSpan[] r215 = new ColoredImageSpan[1];
        r018.addRow(LocaleController.getString(R.string.StarsTransactionFullPrice), replaceStarsWithPlain(r85.amount, "⭐️ " + formatStarsAmount(r120), 0.8f, r215));
        ColoredImageSpan r121 = r215[0];
        if (r121 == null) goto L262;
        r121.setOverrideColor(Theme.getColor(Theme.key_featuredStickers_addButton, r154));
        goto L262
    L249:
        if (r85.stargift_resale == false) goto L262;
    L246:
        r65 = r432;
        goto L247
    L242:
        r154 = r61;
        r018 = r32;
        r52 = r211;
        r184 = r313;
        r432 = r82;
        r85 = r6;
        goto L244
    L213:
        r143 = R.string.StarGiftReasonSale;
        goto L214
    L215:
        String r512 = LocaleController.getString(R.string.StarGiftReason);
        if (r6.refund == false) goto L218;
        int r144 = R.string.StarGiftReasonSale;
    L219:
        r32.addRow(r512, LocaleController.getString(r144));
    L238:
        r163 = r183;
        r183 = r313;
        goto L240
    L218:
        r144 = R.string.StarGiftReasonOffer;
        goto L219
    L221:
        if (r6.stargift_resale == false) goto L235;
        if (r17 == true) goto L229;
        String r513 = LocaleController.getString(R.string.StarGiftReason);
        if (r6.refund == false) goto L226;
        int r145 = R.string.StarGiftReasonPurchase;
    L227:
        r32.addRow(r513, LocaleController.getString(r145));
        goto L228
    L226:
        r145 = R.string.StarGiftReasonSale;
        goto L227
    L229:
        String r514 = LocaleController.getString(R.string.StarGiftReason);
        if (r6.refund == false) goto L232;
        int r146 = R.string.StarGiftReasonSale;
    L233:
        r32.addRow(r514, LocaleController.getString(r146));
        goto L238
    L232:
        r146 = R.string.StarGiftReasonPurchase;
        goto L233
    L235:
        if (r6.stargift_drop_original_details == false) goto L237;
        r32.addRow(LocaleController.getString(R.string.StarGiftReason), LocaleController.getString(R.string.StarGiftReasonRemovedDescription));
        r163 = r313;
        r183 = r163;
        goto L240
    L237:
        r32.addRow(LocaleController.getString(R.string.StarGiftReason), LocaleController.getString(R.string.StarGiftReasonTransfer));
        goto L238
    L263:
        BottomSheet[] r019 = r82;
        r83 = r6;
        TableView r020 = r32;
        r52 = r211;
        if (r83.refund == false) goto L266;
        r28 = r59;
        r7 = r019;
        r94 = r83;
        r62 = r55;
        r09 = r020;
        goto L200
    L266:
        if (r57 != 0) goto L268;
        long r134 = UserConfig.getInstance(r59).getClientUserId();
    L269:
        long r316 = DialogObject.getPeerDialogId(r83.peer.peer);
        TLRPC.User r72 = MessagesController.getInstance(r59).getUser(Long.valueOf(r316));
        if (r16 == true) goto L272;
        r93 = r019;
        long r317 = r134;
        if (r316 == r317) goto L288;
        TLRPC.User r135 = r72;
        long r66 = r316;
        r020.addRowUser(LocaleController.getString(R.string.StarGiveawayPrizeFrom), r59, r317, new StarsIntroActivity$$ExternalSyntheticLambda3(r93, r59));
    L289:
        String r173 = LocaleController.getString(R.string.StarGiveawayPrizeTo);
        StarsIntroActivity$$ExternalSyntheticLambda4 r021 = new StarsIntroActivity$$ExternalSyntheticLambda4(r93, r83, r66);
        if (r135 != null) goto L292;
    L296:
        long r318 = r66;
        String r67 = null;
    L297:
        TableView r022 = r020;
        r022.addRowUser(r173, r59, r318, r021, r67, new StarsIntroActivity$$ExternalSyntheticLambda5(r55, r59, r318, r93));
        r07 = r022;
        goto L298
    L292:
        if (UserObject.isDeleted(r135) == true) goto L296;
        if (UserObject.areGiftsDisabled(r66) == true) goto L296;
        r318 = r66;
        r67 = LocaleController.getString(R.string.Gift2ButtonSendGift);
        goto L297
    L288:
        r135 = r72;
        r66 = r316;
        goto L289
    L272:
        if (r316 == r134) goto L282;
        String r164 = LocaleController.getString(R.string.StarGiveawayPrizeFrom);
        StarsIntroActivity$$ExternalSyntheticLambda32 r124 = new StarsIntroActivity$$ExternalSyntheticLambda32(r019, r83, r316);
        if (r72 != null) goto L276;
    L280:
        BottomSheet[] r433 = r019;
        String r68 = null;
    L281:
        int r216 = r59;
        BottomSheet[] r515 = r433;
        StarsIntroActivity$$ExternalSyntheticLambda1 r73 = new StarsIntroActivity$$ExternalSyntheticLambda1(r92, r216, r316, r515);
        TableView r023 = r020;
        r93 = r515;
        long r174 = r134;
        r023.addRowUser(r164, r216, r316, r124, r68, r73);
        TableView r024 = r023;
    L283:
        r024.addRowUser(LocaleController.getString(R.string.StarGiveawayPrizeTo), r216, r174, new StarsIntroActivity$$ExternalSyntheticLambda2(r93, r216));
        r07 = r024;
        goto L298
    L276:
        if (UserObject.isDeleted(r72) == true) goto L280;
        if (UserObject.areGiftsDisabled(r316) == true) goto L280;
        r433 = r019;
        r68 = LocaleController.getString(R.string.Gift2ButtonSendGift);
        goto L281
    L282:
        r216 = r59;
        r93 = r019;
        r174 = r134;
        r024 = r020;
        goto L283
    L268:
        r134 = r57;
        goto L269
    L192:
        if ((r6.flags & 256) != 0) goto L194;
    L196:
        TL_stars.StarsTransactionPeer r410 = r6.peer;
        if ((r410 instanceof TL_stars.TL_starsTransactionPeer) == false) goto L201;
        long r411 = DialogObject.getPeerDialogId(((TL_stars.TL_starsTransactionPeer) r410).peer);
        Object r74 = r1;
        TableView r025 = r32;
        r025.addRowUser(LocaleController.getString(R.string.StarGiftUpgradeGiftFrom), r28, r411, new StarsIntroActivity$$ExternalSyntheticLambda28(r82, r411));
        Context r522 = r92;
        r94 = r6;
        r62 = r522;
        TableView r026 = r025;
    L199:
        r52 = r74;
        r7 = r82;
        r09 = r026;
        goto L200
    L201:
        r74 = r1;
        Context r027 = r92;
        r94 = r6;
        r62 = r027;
        r026 = r32;
        goto L199
    L194:
        if (r6.msg_id <= 0) goto L196;
        ButtonSpan.TextViewButtons r412 = (ButtonSpan.TextViewButtons) ((TableView.TableRowContent) r32.addRow(LocaleController.getString(R.string.StarGiftReason), LocaleController.getString(R.string.StarGiftReasonUpgrade)).getChildAt(1)).getChildAt(0);
        TL_stars.TL_inputSavedStarGiftUser r516 = new TL_stars.TL_inputSavedStarGiftUser();
        r516.msg_id = r6.msg_id;
        StarsController.getInstance(r28).getUserStarGift(r516, new StarsIntroActivity$$ExternalSyntheticLambda27(r412, r28, r92, r05));
        goto L196
    L49:
        if (r60.failed == false) goto L52;
        appendStatus(r45, r27, LocaleController.getString(R.string.StarsFailed));
        goto L54
    L52:
        if (r60.pending == false) goto L54;
        appendStatus(r45, r27, LocaleController.getString(R.string.StarsPending));
        goto L54
    L55:
        r30 = r14;
    L56:
        r422 = r02;
        r41 = "fragment";
        r332 = r13;
        BackupImageView r125 = new BackupImageView(r55);
        if (r60.premium_gift == false) goto L63;
        setPremiumGiftImage(r125, r125.getImageReceiver(), r60.premium_gift_months);
        r8.addView(r125, LayoutHelper.createLinear(160, 160, 17, 0, -8, 0, 10));
    L61:
        boolean r136 = r56;
        r92 = r55;
        r28 = r59;
        r6 = r60;
        ?? r126 = r8;
        r34 = r15;
        long r75 = r57;
        r05 = r61;
    L130:
        TextView r319 = new TextView(r92);
        int r413 = Theme.key_dialogTextBlack;
        r319.setTextColor(Theme.getColor(r413, r05));
        r319.setTextSize(1, 20.0f);
        r319.setTypeface(AndroidUtilities.bold());
        r319.setGravity(17);
        r319.setText(getTransactionTitle(r28, r136, r6));
        r126.addView(r319, LayoutHelper.createLinear(-1, -2, 17, 36, 0, 36, 4));
        TextView r320 = new TextView(r92);
        r320.setTextSize(1, 18.0f);
        r320.setTypeface(AndroidUtilities.bold());
        r320.setGravity(17);
        if (r16 == false) goto L133;
        int r517 = Theme.key_color_green;
    L134:
        r320.setTextColor(Theme.getColor(r517, r05));
        TL_stars.StarsAmount r518 = r6.amount;
        if (r16 == true) goto L138;
        r20 = _UrlKt.FRAGMENT_ENCODE_SET;
    L138:
        r320.setText(replaceStarsWithPlain(r518, TextUtils.concat(new CharSequence[]{r20, formatStarsAmount(r518), " ⭐️"}), 0.8f));
        SpannableStringBuilder r519 = new SpannableStringBuilder(r320.getText());
        if (r6.refund == false) goto L142;
        appendStatus(r519, r320, LocaleController.getString(R.string.StarsRefunded));
    L147:
        r320.setText(r519);
        r126.addView(r320, LayoutHelper.createLinear(-1, -2, 17, 36, 0, 36, 4));
        if (r6.paid_message == false) goto L159;
        if (r6.starref_commission_permille <= 0) goto L159;
        if (r16 == false) goto L159;
        LinkSpanDrawable.LinksTextView r321 = new LinkSpanDrawable.LinksTextView(r92);
        r321.setTextColor(Theme.getColor(r413, r05));
        r321.setTextSize(1, 14.0f);
        r321.setGravity(17);
        r321.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r05));
        r321.setDisablePaddingsOffsetY(true);
        SpannableStringBuilder r414 = new SpannableStringBuilder();
        r414.append(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.StarsTransactionMessageFeeInfo, new Object[]{AffiliateProgramFragment.percents(1000 - r6.starref_commission_permille)})));
        if (r75 != UserConfig.getInstance(r28).getClientUserId()) goto L155;
    L156:
        r414.append(" ");
        r414.append(AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.StarsTransactionMessageFeeInfoLink).replace(' ', Typography.nbsp), new StarsIntroActivity$$ExternalSyntheticLambda22(r75, r28)), true));
    L157:
        r321.setText(r414);
        r126.addView(r321, LayoutHelper.createLinear(-1, -2, 17, 36, 0, 36, 4));
        r82 = r22;
        r1 = r126;
        goto L187
    L155:
        if (ChatObject.canUserDoAction(MessagesController.getInstance(r28).getChat(Long.valueOf(-r75)), 2) == false) goto L157;
    L159:
        if ((r6.amount instanceof TL_stars.TL_starsTonAmount) == true) goto L164;
        if (r332 == true) goto L166;
        if (r6.gift == false) goto L164;
    L166:
        if (r6.sent_by != null) goto L168;
        TLRPC.User r520 = null;
    L170:
        if (r6.sent_by != null) goto L172;
        TLRPC.User r137 = null;
    L173:
        boolean r521 = UserObject.isUserSelf(r520);
        if (r521 == false) goto L176;
        r320.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r05));
        TL_stars.StarsAmount r147 = r6.amount;
        boolean r202 = r521;
        ?? r523 = 1;
        r320.setText(replaceStarsWithPlain(r147, TextUtils.concat(new CharSequence[]{formatStarsAmount(r147), " ⭐️"}), 0.8f));
    L177:
        ?? r322 = new LinkSpanDrawable.LinksTextView(r92);
        r322.setTextColor(Theme.getColor(r413, r05));
        r322.setTextSize(r523, 16.0f);
        r322.setGravity(17);
        r322.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r05));
        r322.setDisablePaddingsOffsetY(r523);
        if (r202 == false) goto L180;
        String r415 = LocaleController.formatString(R.string.ActionGiftStarsSubtitle, new Object[]{UserObject.getForcedFirstName(r137)});
    L181:
        r82 = r22;
        r322.setText(TextUtils.concat(new CharSequence[]{AndroidUtilities.replaceTags(r415), " ", AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.GiftStarsSubtitleLinkName).replace(' ', Typography.nbsp), new StarsIntroActivity$$ExternalSyntheticLambda26(r92, r82)), true)}));
        r126.addView(r322, LayoutHelper.createLinear(-1, -2, 17, 36, 0, 36, 4));
        r1 = r126;
        goto L187
    L180:
        r415 = LocaleController.getString(R.string.ActionGiftStarsSubtitleYou);
        goto L181
    L176:
        r202 = r521;
        r523 = 1;
        goto L177
    L172:
        r137 = MessagesController.getInstance(r28).getUser(Long.valueOf(DialogObject.getPeerDialogId(r6.received_by)));
        goto L173
    L168:
        r520 = MessagesController.getInstance(r28).getUser(Long.valueOf(DialogObject.getPeerDialogId(r6.sent_by)));
    L164:
        r82 = r22;
        r1 = r126;
        if (r6.description == null) goto L187;
        r1 = r126;
        if (r6.extended_media.isEmpty() == false) goto L187;
        TextView r323 = new TextView(r92);
        r323.setTextColor(Theme.getColor(r413, r05));
        r323.setTextSize(1, 16.0f);
        r323.setGravity(17);
        r323.setText(r6.description);
        r126.addView(r323, LayoutHelper.createLinear(-1, -2, 17, 36, 0, 36, 4));
        r1 = r126;
        goto L187
    L142:
        if (r6.failed == false) goto L145;
        r320.setTextColor(Theme.getColor(Theme.key_color_red, r05));
        appendStatus(r519, r320, LocaleController.getString(R.string.StarsFailed));
        goto L147
    L145:
        if (r6.pending == false) goto L147;
        r320.setTextColor(Theme.getColor(Theme.key_color_yellow, r05));
        appendStatus(r519, r320, LocaleController.getString(R.string.StarsPending));
        goto L147
    L133:
        r517 = Theme.key_color_red;
        goto L134
    L63:
        if (r60.posts_search == false) goto L65;
        CombinedDrawable r217 = SessionCell.createDrawable(100, "search");
        r217.setIconSize(AndroidUtilities.dp(40.0f), AndroidUtilities.dp(40.0f));
        r125.setImageDrawable(r217);
        goto L61
    L65:
        TL_stars.StarGift r218 = r60.stargift;
        if (r218 != null) goto L68;
        if (r332 == false) goto L73;
    L74:
        r136 = r56;
        r92 = r55;
        r28 = r59;
        r6 = r60;
        ViewGroup r127 = r8;
        r34 = r15;
        r75 = r57;
        r05 = r61;
        if ((r6.amount instanceof TL_stars.TL_starsTonAmount) == false) goto L128;
        setTonGiftImage(r125, r125.getImageReceiver(), r6.amount.amount);
    L129:
        r127.addView(r125, LayoutHelper.createLinear(160, 160, 17, 0, -8, 0, 10));
        r126 = r127;
        goto L130
    L128:
        setGiftImage(r125, r125.getImageReceiver(), r6.amount.amount);
        goto L129
    L73:
        if (r60.gift == true) goto L74;
        if (r60.extended_media.isEmpty() == true) goto L86;
        r125.setRoundRadius(AndroidUtilities.dp(30.0f));
        TLRPC.MessageMedia r219 = r60.extended_media.get(0);
        if ((r219 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L82;
        ImageLocation r220 = ImageLocation.getForPhoto(FileLoader.getClosestPhotoSizeWithSize(r219.photo.sizes, AndroidUtilities.dp(100.0f), true), r219.photo);
    L80:
        ImageLocation r353 = r220;
    L85:
        r125.setImage(r353, "100_100", null, null, null, 0);
        r8.addView(r125, LayoutHelper.createLinear(100, 100, 17, 0, 0, 0, 10));
        r92 = r55;
        StarsIntroActivity$$ExternalSyntheticLambda11 r028 = new StarsIntroActivity$$ExternalSyntheticLambda11(r56, r57, r60, r59, r61, r125, r8);
        r136 = r56;
        r126 = r8;
        r28 = r59;
        r05 = r61;
        r75 = r57;
        r6 = r60;
        r125.setOnClickListener(r028);
        r34 = r15;
        goto L130
    L82:
        if ((r219 instanceof TLRPC.TL_messageMediaDocument) == false) goto L84;
        r220 = ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r219.document.thumbs, AndroidUtilities.dp(100.0f), true), r219.document);
        goto L80
    L84:
        r353 = null;
        goto L85
    L86:
        r136 = r56;
        r92 = r55;
        r28 = r59;
        r6 = r60;
        ViewGroup r128 = r8;
        r75 = r57;
        r05 = r61;
        TL_stars.StarsTransactionPeer r416 = r6.peer;
        if ((r416 instanceof TL_stars.TL_starsTransactionPeer) == true) goto L89;
        r34 = r15;
        if ((r416 instanceof TL_stars.TL_starsTransactionPeerAppStore) == false) goto L109;
        String r417 = CredentialsData.CREDENTIALS_TYPE_IOS;
    L124:
        CombinedDrawable r324 = SessionCell.createDrawable(100, r417);
        r324.setIconSize(AndroidUtilities.dp(40.0f), AndroidUtilities.dp(40.0f));
        r125.setImageDrawable(r324);
        r126 = r128;
        goto L130
    L109:
        if ((r416 instanceof TL_stars.TL_starsTransactionPeerPlayMarket) == false) goto L112;
        r417 = "android";
        goto L124
    L112:
        if ((r416 instanceof TL_stars.TL_starsTransactionPeerPremiumBot) == false) goto L115;
        r417 = "premiumbot";
        goto L124
    L115:
        if ((r416 instanceof TL_stars.TL_starsTransactionPeerFragment) == false) goto L118;
        r417 = r41;
        goto L124
    L118:
        if ((r416 instanceof TL_stars.TL_starsTransactionPeerAds) == false) goto L121;
        r417 = "ads";
        goto L124
    L121:
        if ((r416 instanceof TL_stars.TL_starsTransactionPeerAPI) == false) goto L123;
        r417 = "api";
        goto L124
    L123:
        r417 = "?";
        goto L124
    L89:
        if (r6.photo == null) goto L91;
        r125.setRoundRadius(AndroidUtilities.dp(50.0f));
        r125.setImage(ImageLocation.getForWebFile(WebFile.createWithWebDocument(r6.photo)), "100_100", null, 0, null);
        r34 = r15;
    L104:
        r128.addView(r125, LayoutHelper.createLinear(100, 100, 17, 0, 0, 0, 10));
        r126 = r128;
        goto L130
    L91:
        r125.setRoundRadius(AndroidUtilities.dp(50.0f));
        if (r15 == false) goto L96;
        TLRPC.Peer r325 = r6.starref_peer;
    L94:
        long r326 = DialogObject.getPeerDialogId(r325);
    L100:
        AvatarDrawable r148 = new AvatarDrawable();
        if (r326 < 0) goto L103;
        r34 = r15;
        TLRPC.User r327 = MessagesController.getInstance(r28).getUser(Long.valueOf(r326));
        r148.setInfo(r327);
        r125.setForUserOrChat(r327, r148);
        goto L104
    L103:
        r34 = r15;
        TLRPC.Chat r328 = MessagesController.getInstance(r28).getChat(Long.valueOf(-r326));
        r148.setInfo(r328);
        r125.setForUserOrChat(r328, r148);
        goto L104
    L96:
        if (r6.subscription == false) goto L99;
        if (r136 == false) goto L99;
        r326 = r75;
    L99:
        r325 = r6.peer.peer;
        goto L94
    L68:
        if ((r218 instanceof TL_stars.TL_starGiftUnique) == false) goto L70;
        r125.setImageDrawable(new StarGiftSheet.StarGiftDrawableIcon(r125, r60.stargift, 94, 0.44f));
        r8.addView(r125, LayoutHelper.createLinear(94, 94, 17, 0, 2, 0, 10));
        goto L61
    L70:
        setGiftImage(r125.getImageReceiver(), r60.stargift, 160);
        r8.addView(r125, LayoutHelper.createLinear(160, 160, 17, 0, -8, 0, 10));
        goto L61
    L57:
        r30 = r14;
        goto L56
    L26:
        if (r60.gift == true) goto L33;
        if (r60.stargift_resale == true) goto L30;
    L32:
        r4 = 20.0f;
        goto L34
    L30:
        if ((r60.stargift instanceof TL_stars.TL_starGiftUnique) == false) goto L32;
    L18:
        if ((r2 & 65536) == 0) goto L22;
        if (r60.paid_message == true) goto L22;
        r15 = true;
        goto L23
    L13:
        if (r60.paid_message == true) goto L15;
        r14 = true;
        goto L16
    L9:
        r13 = false;
        goto L11
    L435:
        return null;
    L5:
        return null;
    }

    private void updateBalance() {
        StarsController r0 = StarsController.getInstance(this.currentAccount);
        SpannableStringBuilder r1 = new SpannableStringBuilder();
        r1.append(this.starBalanceIcon);
        r1.append(formatStarsAmount(r0.getBalance(), 0.66f, ' '));
        this.starBalanceTextView.setText(r1);
        ButtonWithCounterView r12 = this.buyButton;
        if (r0.getBalance().amount <= 0) goto L5;
        int r02 = R.string.StarsBuyMore;
    L6:
        r12.setText(LocaleController.getString(r02), true);
        TLRPC.TL_payments_starsRevenueStats r03 = BotStarsController.getInstance(this.currentAccount).getStarsRevenueStats(getUserConfig().getClientUserId());
        if (r03 == null) goto L13;
        TLRPC.TL_starsRevenueStatus r04 = r03.status;
        if (r04 == null) goto L13;
        if (r04.overall_revenue.positive() == false) goto L13;
        boolean r05 = true;
    L14:
        updateButtonsLayouts(r05, true);
        return;
    L13:
        r05 = false;
        goto L14
    L5:
        r02 = R.string.StarsBuy;
        goto L6
    }

    private void updateButtonsLayouts(boolean r5, boolean r6) {
        this.twoButtons = r5;
        FrameLayout r0 = this.oneButtonsLayout;
        float r1 = 1.0f;
        int r3 = 0;
        if (r6 == false) goto L13;
        r0.setVisibility(0);
        this.twoButtonsLayout.setVisibility(0);
        ViewPropertyAnimator r62 = this.oneButtonsLayout.animate();
        if (r5 == false) goto L7;
        float r02 = 0.0f;
    L8:
        r62.alpha(r02).withEndAction(new StarsIntroActivity$$ExternalSyntheticLambda39(this, r5)).start();
        ViewPropertyAnimator r63 = this.twoButtonsLayout.animate();
        if (r5 == true) goto L11;
        r1 = 0.0f;
    L11:
        r63.alpha(r1).withEndAction(new StarsIntroActivity$$ExternalSyntheticLambda40(this, r5)).start();
        return;
    L7:
        r02 = 1.0f;
        goto L8
    L13:
        r0.animate().cancel();
        this.twoButtonsLayout.animate().cancel();
        LinearLayout r64 = this.twoButtonsLayout;
        if (r5 == false) goto L16;
        float r03 = 1.0f;
    L17:
        r64.setAlpha(r03);
        FrameLayout r65 = this.oneButtonsLayout;
        if (r5 == false) goto L20;
        r1 = 0.0f;
    L20:
        r65.setAlpha(r1);
        LinearLayout r66 = this.twoButtonsLayout;
        if (r5 == false) goto L23;
        int r12 = 0;
    L24:
        r66.setVisibility(r12);
        FrameLayout r4 = this.oneButtonsLayout;
        if (r5 == false) goto L27;
        r3 = 8;
    L27:
        r4.setVisibility(r3);
        return;
    L23:
        r12 = 8;
        goto L24
    L16:
        r03 = 0.0f;
        goto L17
    }

    public boolean attachedTransactionsLayout() {
        StarsTransactionsLayout r0 = this.transactionsLayout;
        if (r0 != null) goto L5;
    L11:
        return false;
    L5:
        if ((r0.getParent() instanceof View) == false) goto L11;
        int r02 = ((View) this.transactionsLayout.getParent()).getBottom();
        if (((this.listView.getHeight() - this.listView.getPaddingBottom()) - r02) < 0) goto L11;
        return true;
    }

    @Override // org.telegram.ui.GradientHeaderActivity
    public RecyclerView.Adapter<?> createAdapter() {
        AnonymousClass5 r0 = new AnonymousClass5(this, this.listView, getContext(), this.currentAccount, this.classGuid, true, new StarsIntroActivity$$ExternalSyntheticLambda67(this), getResourceProvider());
        this.adapter = r0;
        r0.setApplyBackground(false);
        return this.adapter;
    }

    @Override // org.telegram.ui.GradientHeaderActivity
    public GradientHeaderActivity.ContentView createContentView() {
        return new NestedFrameLayout(this, getContext());
    }

    @Override // org.telegram.ui.GradientHeaderActivity
    public StarParticlesView createParticlesView() {
        return makeParticlesView(getContext(), 75, 1);
    }

    @Override // org.telegram.ui.GradientHeaderActivity, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r20) {
        this.useFillLastLayoutManager = false;
        this.particlesViewHeight = AndroidUtilities.dp(238.0f);
        this.transactionsLayout = new StarsTransactionsLayout(r20, this.currentAccount, false, 0, getClassGuid(), getResourceProvider());
        this.emptyLayout = new AnonymousClass1(this, r20);
        super.createView(r20);
        INavigationLayout r1 = this.parentLayout;
        if (r1 != null) goto L5;
    L7:
        FrameLayout r12 = new FrameLayout(r20);
        this.aboveTitleView = r12;
        boolean r3 = true;
        r12.setClickable(true);
        GLIconTextureView r13 = new GLIconTextureView(r20, 1, 2);
        this.iconTextureView = r13;
        GLIconRenderer r14 = r13.mRenderer;
        r14.colorKey1 = Theme.key_starsGradient1;
        r14.colorKey2 = Theme.key_starsGradient2;
        r14.updateColors();
        this.iconTextureView.setStarParticlesView(this.particlesView);
        this.aboveTitleView.addView(this.iconTextureView, LayoutHelper.createFrame(190, 190.0f, 17, 0.0f, 12.0f, 0.0f, 24.0f));
        configureHeader(LocaleController.getString(R.string.TelegramStars), AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.TelegramStarsInfo2), new StarsIntroActivity$$ExternalSyntheticLambda33(r20)), true), this.aboveTitleView, null);
        this.listView.setOverScrollMode(2);
        DefaultItemAnimator r15 = new DefaultItemAnimator();
        r15.setSupportsChangeAnimations(false);
        r15.setDelayAnimations(false);
        r15.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r15.setDurations(350);
        this.listView.setItemAnimator(r15);
        this.listView.setOnItemClickListener(new StarsIntroActivity$$ExternalSyntheticLambda34(this));
        FireworksOverlay r16 = new FireworksOverlay(getContext());
        this.fireworksOverlay = r16;
        this.contentView.addView(r16, LayoutHelper.createFrame(-1, -1.0f));
        StarsController r17 = StarsController.getInstance(this.currentAccount);
        LinearLayout r5 = new LinearLayout(getContext());
        this.balanceLayout = r5;
        r5.setOrientation(1);
        this.balanceLayout.setPadding(0, AndroidUtilities.dp(24.0f), 0, AndroidUtilities.dp(10.0f));
        AnimatedTextView r52 = new AnimatedTextView(getContext(), false, true, false);
        this.starBalanceTextView = r52;
        r52.setTypeface(AndroidUtilities.bold());
        this.starBalanceTextView.setTextSize(AndroidUtilities.dp(32.0f));
        this.starBalanceTextView.setGravity(17);
        this.starBalanceTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourceProvider));
        this.starBalanceIcon = new SpannableStringBuilder("S");
        ImageReceiverSpan r53 = new ImageReceiverSpan(this.starBalanceTextView, this.currentAccount, 42.0f);
        r53.imageReceiver.setImageBitmap(new RLottieDrawable(R.raw.star_reaction, AndroidUtilities.dp(42.0f), AndroidUtilities.dp(42.0f)));
        r53.imageReceiver.setAutoRepeat(2);
        r53.enableShadow(false);
        r53.translate(-AndroidUtilities.dp(3.0f), 0.0f);
        this.starBalanceIcon.setSpan(r53, 0, 1, 33);
        this.balanceLayout.addView(this.starBalanceTextView, LayoutHelper.createFrame(-1, 40.0f, 17, 24.0f, 0.0f, 24.0f, 0.0f));
        TextView r54 = new TextView(getContext());
        this.starBalanceTitleView = r54;
        r54.setTextSize(1, 14.0f);
        this.starBalanceTitleView.setGravity(17);
        this.starBalanceTitleView.setText(LocaleController.getString(R.string.YourStarsBalance));
        this.starBalanceTitleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, this.resourceProvider));
        this.balanceLayout.addView(this.starBalanceTitleView, LayoutHelper.createFrame(-1, -2.0f, 17, 24.0f, 0.0f, 24.0f, 0.0f));
        FrameLayout r55 = new FrameLayout(getContext());
        AnonymousClass2 r6 = new AnonymousClass2(this, getContext());
        this.oneButtonsLayout = r6;
        r55.addView(r6);
        ButtonWithCounterView r62 = new ButtonWithCounterView(getContext(), this.resourceProvider);
        this.buyButton = r62;
        r62.setRound();
        this.buyButton.setText(_UrlKt.FRAGMENT_ENCODE_SET, false);
        this.buyButton.setOnClickListener(new StarsIntroActivity$$ExternalSyntheticLambda35(this, r20));
        this.oneButtonsLayout.addView(this.buyButton, LayoutHelper.createFrame(-1, 48, 119));
        AnonymousClass3 r63 = new AnonymousClass3(this, getContext());
        this.twoButtonsLayout = r63;
        r55.addView(r63);
        ButtonWithCounterView r64 = new ButtonWithCounterView(getContext(), this.resourceProvider);
        this.topupButton = r64;
        r64.setRound();
        SpannableStringBuilder r65 = new SpannableStringBuilder("x  ");
        r65.setSpan(new ColoredImageSpan(R.drawable.mini_topup, 2), 0, 1, 33);
        r65.append(LocaleController.getString(R.string.StarsTopUp));
        this.topupButton.setText(r65, false);
        this.topupButton.setOnClickListener(new StarsIntroActivity$$ExternalSyntheticLambda36(this, r20));
        this.twoButtonsLayout.addView(this.topupButton, LayoutHelper.createLinear(-1, 48, 17.0f, 1, 0, 0, 8, 0));
        ButtonWithCounterView r2 = new ButtonWithCounterView(getContext(), this.resourceProvider);
        this.withdrawButton = r2;
        r2.setRound();
        SpannableStringBuilder r22 = new SpannableStringBuilder("x  ");
        r22.setSpan(new ColoredImageSpan(R.drawable.mini_stats, 2), 0, 1, 33);
        r22.append(LocaleController.getString(R.string.StarsStats));
        this.withdrawButton.setText(r22, false);
        this.withdrawButton.setOnClickListener(new StarsIntroActivity$$ExternalSyntheticLambda37(this));
        this.twoButtonsLayout.addView(this.withdrawButton, LayoutHelper.createLinear(-1, 48, 17.0f, 1, 0, 0, 0, 0));
        this.balanceLayout.addView(r55, LayoutHelper.createFrame(-1, 48.0f, 17, 20.0f, 17.0f, 20.0f, 0.0f));
        ButtonWithCounterView r23 = new ButtonWithCounterView(getContext(), false, this.resourceProvider);
        this.giftButton = r23;
        r23.setRound();
        SpannableStringBuilder r24 = new SpannableStringBuilder();
        r24.append("G  ");
        r24.setSpan(new ColoredImageSpan(R.drawable.menu_stars_gift), 0, 1, 33);
        r24.append(LocaleController.getString(R.string.TelegramStarsGift));
        this.giftButton.setText(r24, false);
        this.giftButton.setOnClickListener(new StarsIntroActivity$$ExternalSyntheticLambda38(this));
        this.balanceLayout.addView(this.giftButton, LayoutHelper.createFrame(-1, 48.0f, 17, 20.0f, 8.0f, 20.0f, 0.0f));
        updateBalance();
        UniversalAdapter r25 = this.adapter;
        if (r25 == null) goto L10;
        r25.update(false);
    L10:
        BotStarsController.getInstance(this.currentAccount).preloadStarsStats(getUserConfig().getClientUserId());
        TLRPC.TL_payments_starsRevenueStats r26 = BotStarsController.getInstance(this.currentAccount).getStarsRevenueStats(getUserConfig().getClientUserId());
        if (r17.getBalance().amount <= 0) goto L18;
        if (r26 == null) goto L18;
        TLRPC.TL_starsRevenueStatus r18 = r26.status;
        if (r18 == null) goto L18;
        if (r18.overall_revenue.positive() == false) goto L18;
    L19:
        updateButtonsLayouts(r3, false);
        return this.fragmentView;
    L18:
        r3 = false;
        goto L19
    L5:
        if (r1.isRightLayout() == false) goto L7;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_close);
        goto L7
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.starOptionsLoaded) goto L15;
        saveScrollPosition();
        UniversalAdapter r32 = this.adapter;
        if (r32 == null) goto L8;
        r32.update(true);
    L8:
        if (this.savedScrollPosition == 0) goto L10;
    L12:
        applyScrolledPosition();
        return;
    L10:
        if (this.savedScrollOffset >= 0) goto L12;
        this.savedScrollOffset = 0;
        goto L12
    L15:
        if (r3 != NotificationCenter.starTransactionsLoaded) goto L29;
        StarsController r33 = StarsController.getInstance(this.currentAccount);
        if (this.hadTransactions == r33.hasTransactions()) goto L44;
        this.hadTransactions = r33.hasTransactions();
        saveScrollPosition();
        UniversalAdapter r34 = this.adapter;
        if (r34 == null) goto L22;
        r34.update(true);
    L22:
        if (this.savedScrollPosition == 0) goto L24;
    L26:
        applyScrolledPosition();
        return;
    L24:
        if (this.savedScrollOffset >= 0) goto L26;
        this.savedScrollOffset = 0;
        goto L26
    L44:
        return;
    L29:
        if (r3 != NotificationCenter.starSubscriptionsLoaded) goto L35;
        UniversalAdapter r2 = this.adapter;
        if (r2 == null) goto L45;
        r2.update(true);
        return;
    L45:
        return;
    L35:
        if (r3 != NotificationCenter.starBalanceUpdated) goto L39;
        updateBalance();
        return;
    L39:
        if (r3 == NotificationCenter.botStarsUpdated) goto L41;
        return;
    L41:
        if (getUserConfig().getClientUserId() != ((Long) r5[0]).longValue()) goto L47;
        updateBalance();
        return;
    }

    @Override // org.telegram.ui.GradientHeaderActivity
    public boolean drawActionBarShadow() {
        return !attachedTransactionsLayout();
    }

    public void fillItems(ArrayList<UItem> r8, UniversalAdapter r9) {
        if (getContext() != null) goto L5;
        return;
    L5:
        StarsController r92 = StarsController.getInstance(this.currentAccount);
        r8.add(UItem.asFullyCustom(getHeader(getContext())));
        r8.add(UItem.asCustom(this.balanceLayout));
        ButtonWithCounterView r0 = this.giftButton;
        int r1 = 0;
        if (r0 != null) goto L8;
    L12:
        r8.add(UItem.asShadow(null));
        if (getMessagesController().starrefConnectAllowed == false) goto L16;
        r8.add(AffiliateProgramFragment.ColorfulTextCell.Factory.as(-4, getThemedColor(Theme.key_color_green), R.drawable.filled_earn_stars, ChatEditActivity.applyNewSpan(LocaleController.getString(R.string.UserAffiliateProgramRowTitle)), LocaleController.getString(R.string.UserAffiliateProgramRowText)));
        r8.add(UItem.asShadow(null));
    L16:
        if (r92.hasSubscriptions() == false) goto L28;
        r8.add(UItem.asHeader(LocaleController.getString(R.string.StarMySubscriptions)));
    L19:
        if (r1 >= r92.subscriptions.size()) goto L22;
        r8.add(StarsSubscriptionView.Factory.asSubscription(r92.subscriptions.get(r1)));
        r1 = r1 + 1;
        goto L19
    L22:
        if (r92.isLoadingSubscriptions() == false) goto L25;
        r8.add(UItem.asFlicker(r8.size(), 33));
    L27:
        r8.add(UItem.asShadow(null));
        goto L28
    L25:
        if (r92.didFullyLoadSubscriptions() == true) goto L27;
        r8.add(UItem.asButton(-3, R.drawable.arrow_more, LocaleController.getString(R.string.StarMySubscriptionsExpand)).accent());
    L28:
        boolean r93 = r92.hasTransactions();
        this.hadTransactions = r93;
        if (r93 == false) goto L32;
        r8.add(UItem.asFullscreenCustom(this.transactionsLayout, ((ActionBar.getCurrentActionBarHeight() + AndroidUtilities.statusBarHeight) + AndroidUtilities.dp(24.0f)) + AndroidUtilities.navigationBarHeight));
        return;
    L32:
        r8.add(UItem.asCustomShadow(this.emptyLayout));
        return;
    L8:
        if (getMessagesController().starsGiftsEnabled == false) goto L10;
        int r2 = 0;
    L11:
        r0.setVisibility(r2);
        goto L12
    L10:
        r2 = 8;
        goto L11
    }

    @Override // org.telegram.ui.GradientHeaderActivity
    public View getHeader(Context r1) {
        return super.getHeader(r1);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starOptionsLoaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starBalanceUpdated);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starTransactionsLoaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starSubscriptionsLoaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.botStarsUpdated);
        StarsController.getInstance(this.currentAccount).invalidateTransactions(true);
        StarsController.getInstance(this.currentAccount).invalidateSubscriptions(true);
        StarsController.getInstance(this.currentAccount).getOptions();
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starOptionsLoaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starBalanceUpdated);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starTransactionsLoaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starSubscriptionsLoaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.botStarsUpdated);
    }

    public void onItemClick(UItem r4, int r5) {
        int r52 = r4.id;
        if (r52 != (-1)) goto L7;
        this.expanded = !this.expanded;
        this.adapter.update(true);
        return;
    L7:
        if (r52 != (-2)) goto L11;
        StarsController.getInstance(this.currentAccount).getGiftOptions();
        UserSelectorBottomSheet.open(1, 0, BirthdayController.getInstance(this.currentAccount).getState());
        return;
    L11:
        if (r52 != (-3)) goto L15;
        StarsController.getInstance(this.currentAccount).loadSubscriptions();
        this.adapter.update(true);
        return;
    L15:
        if (r52 != (-4)) goto L23;
        if (MessagesController.getInstance(this.currentAccount).isFrozen() == false) goto L20;
        AccountFrozenAlert.show(this.currentAccount);
        return;
    L20:
        presentFragment(new ChannelAffiliateProgramsFragment(getUserConfig().getClientUserId()));
        return;
    L23:
        if (r4.instanceOf(StarTierView.Factory.class) == false) goto L29;
        if ((r4.object instanceof TL_stars.TL_starsTopupOption) == false) goto L34;
        StarsController.getInstance(this.currentAccount).buy(getParentActivity(), (TL_stars.TL_starsTopupOption) r4.object, new StarsIntroActivity$$ExternalSyntheticLambda53(this, r4), null);
        return;
    L34:
        return;
    L29:
        if (r4.instanceOf(StarsSubscriptionView.Factory.class) == true) goto L31;
        return;
    L31:
        if ((r4.object instanceof TL_stars.StarsSubscription) == false) goto L36;
        showSubscriptionSheet(getContext(), this.currentAccount, (TL_stars.StarsSubscription) r4.object, getResourceProvider());
        return;
    }

    @Override // org.telegram.ui.GradientHeaderActivity, org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        GLIconTextureView r0 = this.iconTextureView;
        if (r0 == null) goto L6;
        r0.setPaused(true);
        this.iconTextureView.setDialogVisible(true);
        return;
    }

    @Override // org.telegram.ui.GradientHeaderActivity, org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        GLIconTextureView r0 = this.iconTextureView;
        if (r0 == null) goto L6;
        r0.setPaused(false);
        this.iconTextureView.setDialogVisible(false);
        return;
    }
}
