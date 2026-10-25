package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Property;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Components.ColorSpanUnderline;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ProgressButton;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes6.dex */
public class FeaturedStickerSetInfoCell extends FrameLayout {
    private ProgressButton addButton;
    private AnimatorSet animatorSet;
    private boolean canAddRemove;
    private int currentAccount;
    private TextView delButton;
    private boolean hasOnClick;
    private TextView infoTextView;
    private boolean isInstalled;
    private boolean isUnread;
    private TextView nameTextView;
    private boolean needDivider;
    private Paint paint;
    private final Theme.ResourcesProvider resourcesProvider;
    private TLRPC.StickerSetCovered set;
    private int stickerSetNameSearchIndex;
    private int stickerSetNameSearchLength;
    float unreadProgress;
    private CharSequence url;
    private int urlSearchLength;

    /* renamed from: org.telegram.ui.Cells.FeaturedStickerSetInfoCell$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ FeaturedStickerSetInfoCell this$0;

        public AnonymousClass1(FeaturedStickerSetInfoCell r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            boolean r22 = FeaturedStickerSetInfoCell.m6942$$Nest$fgetisInstalled(this.this$0);
            FeaturedStickerSetInfoCell r1 = this.this$0;
            if (r22 == false) goto L6;
            FeaturedStickerSetInfoCell.m6940$$Nest$fgetaddButton(r1).setVisibility(4);
            return;
        L6:
            FeaturedStickerSetInfoCell.m6941$$Nest$fgetdelButton(r1).setVisibility(4);
        }
    }

    /* renamed from: -$$Nest$fgetaddButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ProgressButton m6940$$Nest$fgetaddButton(FeaturedStickerSetInfoCell r0) {
        return r0.addButton;
    }

    /* renamed from: -$$Nest$fgetdelButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m6941$$Nest$fgetdelButton(FeaturedStickerSetInfoCell r0) {
        return r0.delButton;
    }

    /* renamed from: -$$Nest$fgetisInstalled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6942$$Nest$fgetisInstalled(FeaturedStickerSetInfoCell r0) {
        return r0.isInstalled;
    }

    public FeaturedStickerSetInfoCell(Context r10, int r11, boolean r12, boolean r13, Theme.ResourcesProvider r14) {
        super(r10);
        this.currentAccount = UserConfig.selectedAccount;
        this.paint = new Paint(1);
        this.canAddRemove = r13;
        this.resourcesProvider = r14;
        TextView r142 = new TextView(r10);
        this.nameTextView = r142;
        r142.setTextColor(getThemedColor(Theme.key_chat_emojiPanelTrendingTitle));
        this.nameTextView.setTextSize(1, 17.0f);
        this.nameTextView.setTypeface(AndroidUtilities.bold());
        TextView r143 = this.nameTextView;
        TextUtils.TruncateAt r0 = TextUtils.TruncateAt.END;
        r143.setEllipsize(r0);
        this.nameTextView.setSingleLine(true);
        if (r12 == false) goto L5;
        FrameLayout.LayoutParams r144 = LayoutHelper.createFrameRelatively(-2.0f, -2.0f, 8388659, r11, 8.0f, 40.0f, 0.0f);
    L6:
        addView(this.nameTextView, r144);
        TextView r145 = new TextView(r10);
        this.infoTextView = r145;
        r145.setTextColor(getThemedColor(Theme.key_chat_emojiPanelTrendingDescription));
        this.infoTextView.setTextSize(1, 13.0f);
        this.infoTextView.setEllipsize(r0);
        this.infoTextView.setSingleLine(true);
        if (r12 == false) goto L9;
        FrameLayout.LayoutParams r112 = LayoutHelper.createFrameRelatively(-2.0f, -2.0f, 8388659, r11, 30.0f, 100.0f, 0.0f);
    L10:
        addView(this.infoTextView, r112);
        if (r13 == false) goto L21;
        ProgressButton r113 = new ProgressButton(r10);
        this.addButton = r113;
        r113.setTextColor(getThemedColor(Theme.key_featuredStickers_buttonText));
        this.addButton.setText(LocaleController.getString(R.string.Add));
        if (r12 == false) goto L15;
        FrameLayout.LayoutParams r114 = LayoutHelper.createFrameRelatively(-2.0f, 28.0f, 8388661, 0.0f, 16.0f, 14.0f, 0.0f);
    L16:
        addView(this.addButton, r114);
        TextView r115 = new TextView(r10);
        this.delButton = r115;
        r115.setGravity(17);
        this.delButton.setTextColor(getThemedColor(Theme.key_featuredStickers_removeButtonText));
        this.delButton.setTextSize(1, 14.0f);
        this.delButton.setTypeface(AndroidUtilities.bold());
        this.delButton.setText(LocaleController.getString(R.string.StickersRemove));
        if (r12 == false) goto L19;
        FrameLayout.LayoutParams r102 = LayoutHelper.createFrameRelatively(-2.0f, 28.0f, 8388661, 0.0f, 16.0f, 14.0f, 0.0f);
    L20:
        addView(this.delButton, r102);
        goto L21
    L19:
        r102 = LayoutHelper.createFrame(-2, 28.0f, 53, 0.0f, 16.0f, 14.0f, 0.0f);
        goto L20
    L15:
        r114 = LayoutHelper.createFrame(-2, 28.0f, 53, 0.0f, 16.0f, 14.0f, 0.0f);
    L21:
        setWillNotDraw(false);
        updateColors();
        return;
    L9:
        r112 = LayoutHelper.createFrame(-2, -2.0f, 51, r11, 30.0f, 100.0f, 0.0f);
        goto L10
    L5:
        r144 = LayoutHelper.createFrame(-2, -2.0f, 51, r11, 8.0f, 40.0f, 0.0f);
        goto L6
    }

    public static void createThemeDescriptions(List<ThemeDescription> r28, RecyclerListView r29, ThemeDescription.ThemeDescriptionDelegate r30) {
        r28.add(new ThemeDescription(r29, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{FeaturedStickerSetInfoCell.class}, new String[]{"nameTextView"}, null, null, null, Theme.key_chat_emojiPanelTrendingTitle));
        int r19 = Theme.key_chat_emojiPanelTrendingDescription;
        r28.add(new ThemeDescription(r29, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{FeaturedStickerSetInfoCell.class}, new String[]{"infoTextView"}, null, null, null, r19));
        r28.add(new ThemeDescription(r29, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{FeaturedStickerSetInfoCell.class}, new String[]{"addButton"}, null, null, null, Theme.key_featuredStickers_buttonText));
        r28.add(new ThemeDescription(r29, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{FeaturedStickerSetInfoCell.class}, new String[]{"delButton"}, null, null, null, Theme.key_featuredStickers_removeButtonText));
        r28.add(new ThemeDescription(r29, 0, new Class[]{FeaturedStickerSetInfoCell.class}, null, null, null, Theme.key_featuredStickers_unread));
        r28.add(new ThemeDescription(r29, 0, new Class[]{FeaturedStickerSetInfoCell.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r28.add(new ThemeDescription(null, 0, null, null, null, r30, Theme.key_featuredStickers_buttonProgress));
        r28.add(new ThemeDescription(null, 0, null, null, null, r30, Theme.key_featuredStickers_addButton));
        r28.add(new ThemeDescription(null, 0, null, null, null, r30, Theme.key_featuredStickers_addButtonPressed));
        r28.add(new ThemeDescription(null, 0, null, null, null, r30, Theme.key_windowBackgroundWhiteBlueText4));
        r28.add(new ThemeDescription(null, 0, null, null, null, r30, r19));
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private void updateStickerSetNameSearchSpan() {
        if (this.stickerSetNameSearchLength == 0) goto L11;
        SpannableStringBuilder r0 = new SpannableStringBuilder(this.set.set.title);
        ForegroundColorSpan r1 = new ForegroundColorSpan(getThemedColor(Theme.key_windowBackgroundWhiteBlueText4));     // Catch: Exception -> L8
        int r2 = this.stickerSetNameSearchIndex;     // Catch: Exception -> L8
        r0.setSpan(r1, r2, this.stickerSetNameSearchLength + r2, 33);     // Catch: Exception -> L8
    L6:
        this.nameTextView.setText(r0);
        return;
    }

    private void updateUrlSearchSpan() {
        if (this.url == null) goto L11;
        SpannableStringBuilder r0 = new SpannableStringBuilder(this.url);
        r0.setSpan(new ColorSpanUnderline(getThemedColor(Theme.key_windowBackgroundWhiteBlueText4)), 0, this.urlSearchLength, 33);     // Catch: Exception -> L8
        r0.setSpan(new ColorSpanUnderline(getThemedColor(Theme.key_chat_emojiPanelTrendingDescription)), this.urlSearchLength, this.url.length(), 33);     // Catch: Exception -> L8
    L6:
        this.infoTextView.setText(r0);
        return;
    }

    public TLRPC.StickerSetCovered getStickerSet() {
        return this.set;
    }

    public boolean isInstalled() {
        return this.isInstalled;
    }

    @Override // android.view.View
    public void onDraw(Canvas r8) {
        boolean r0 = this.isUnread;
        if (r0 == true) goto L7;
        if (this.unreadProgress != 0.0f) goto L7;
    L23:
        if (this.needDivider == false) goto L26;
        r8.drawLine(0.0f, 0.0f, getWidth(), 0.0f, Theme.getThemePaint("paintDivider", this.resourcesProvider));
        return;
    L26:
        return;
    L7:
        if (r0 == false) goto L14;
        float r3 = this.unreadProgress;
        if (r3 == 1.0f) goto L14;
        float r32 = r3 + 0.16f;
        this.unreadProgress = r32;
        if (r32 <= 1.0f) goto L13;
        this.unreadProgress = 1.0f;
    L21:
        this.paint.setColor(getThemedColor(Theme.key_featuredStickers_unread));
        r8.drawCircle(this.nameTextView.getRight() + AndroidUtilities.dp(12.0f), AndroidUtilities.dp(20.0f), AndroidUtilities.dp(4.0f) * this.unreadProgress, this.paint);
        goto L23
    L13:
        invalidate();
    L14:
        if (r0 == true) goto L21;
        float r02 = this.unreadProgress;
        if (r02 == 0.0f) goto L21;
        float r03 = r02 - 0.16f;
        this.unreadProgress = r03;
        if (r03 >= 0.0f) goto L20;
        this.unreadProgress = 0.0f;
        goto L21
    L20:
        invalidate();
        goto L21
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r8, int r9) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r8), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(60.0f), TLObject.FLAG_30));
        if (this.canAddRemove == false) goto L10;
        int r4 = this.addButton.getMeasuredWidth();
        int r0 = this.delButton.getMeasuredWidth();
        FrameLayout.LayoutParams r1 = (FrameLayout.LayoutParams) this.delButton.getLayoutParams();
        if (r0 >= r4) goto L7;
        r1.rightMargin = AndroidUtilities.dp(14.0f) + ((r4 - r0) / 2);
    L8:
        measureChildWithMargins(this.nameTextView, r8, r4, r9, 0);
        return;
    L7:
        r1.rightMargin = AndroidUtilities.dp(14.0f);
        goto L8
    }

    public void setAddDrawProgress(boolean r2, boolean r3) {
        if (this.canAddRemove == false) goto L6;
        this.addButton.setDrawProgress(r2, r3);
        return;
    }

    public void setAddOnClickListener(View.OnClickListener r2) {
        if (this.canAddRemove == false) goto L6;
        this.hasOnClick = true;
        this.addButton.setOnClickListener(r2);
        this.delButton.setOnClickListener(r2);
        return;
    }

    public void setNeedDivider(boolean r1) {
        this.needDivider = r1;
    }

    public void setStickerSet(TLRPC.StickerSetCovered r8, boolean r9) {
        setStickerSet(r8, r9, false, 0, 0, false);
    }

    public void setStickerSet(TLRPC.StickerSetCovered r8, boolean r9, boolean r10, int r11, int r12) {
        setStickerSet(r8, r9, r10, r11, r12, false);
    }

    public void setStickerSet(TLRPC.StickerSetCovered r10, boolean r11, boolean r12, int r13, int r14, boolean r15) {
        AnimatorSet r0 = this.animatorSet;
        if (r0 == null) goto L5;
        r0.cancel();
        this.animatorSet = null;
    L5:
        float r1 = 0.0f;
        if (this.set == r10) goto L11;
        if (r11 == false) goto L9;
        float r02 = 1.0f;
    L10:
        this.unreadProgress = r02;
        invalidate();
        goto L11
    L9:
        r02 = 0.0f;
    L11:
        this.set = r10;
        this.stickerSetNameSearchIndex = r13;
        this.stickerSetNameSearchLength = r14;
        if (r14 == 0) goto L14;
        updateStickerSetNameSearchSpan();
    L15:
        TLRPC.StickerSet r132 = r10.set;
        boolean r142 = r132.emojis;
        TextView r03 = this.infoTextView;
        if (r142 == false) goto L18;
        r03.setText(LocaleController.formatPluralString("EmojiCount", r132.count, new Object[0]));
    L19:
        this.isUnread = r11;
        if (this.canAddRemove == false) goto L68;
        boolean r112 = this.hasOnClick;
        ProgressButton r133 = this.addButton;
        if (r112 == false) goto L66;
        r133.setVisibility(0);
        if (r15 == false) goto L26;
    L29:
        boolean r102 = true;
    L30:
        this.isInstalled = r102;
        if (r12 == false) goto L61;
        if (r102 == false) goto L34;
        this.delButton.setVisibility(0);
    L35:
        AnimatorSet r103 = new AnimatorSet();
        this.animatorSet = r103;
        r103.setDuration(250);
        AnimatorSet r104 = this.animatorSet;
        TextView r122 = this.delButton;
        if (this.isInstalled == false) goto L38;
        float r143 = 1.0f;
    L39:
        float[] r152 = {r143};
        Property r144 = View.ALPHA;
        ObjectAnimator r123 = ObjectAnimator.ofFloat(r122, r144, r152);
        TextView r153 = this.delButton;
        if (this.isInstalled == false) goto L42;
        float r04 = 1.0f;
    L43:
        float[] r4 = {r04};
        Property r05 = View.SCALE_X;
        ObjectAnimator r154 = ObjectAnimator.ofFloat(r153, r05, r4);
        TextView r42 = this.delButton;
        if (this.isInstalled == false) goto L46;
        float r5 = 1.0f;
    L47:
        float[] r6 = {r5};
        Property r52 = View.SCALE_Y;
        ObjectAnimator r43 = ObjectAnimator.ofFloat(r42, r52, r6);
        ProgressButton r62 = this.addButton;
        if (this.isInstalled == false) goto L50;
        float r7 = 0.0f;
    L51:
        ObjectAnimator r145 = ObjectAnimator.ofFloat(r62, r144, new float[]{r7});
        ProgressButton r63 = this.addButton;
        if (this.isInstalled == false) goto L54;
        float r72 = 0.0f;
    L55:
        ObjectAnimator r06 = ObjectAnimator.ofFloat(r63, r05, new float[]{r72});
        ProgressButton r64 = this.addButton;
        if (this.isInstalled == true) goto L59;
        r1 = 1.0f;
    L59:
        r104.playTogether(new Animator[]{r123, r154, r43, r145, r06, ObjectAnimator.ofFloat(r64, r52, new float[]{r1})});
        this.animatorSet.addListener(new AnonymousClass1(this));
        this.animatorSet.setInterpolator(new OvershootInterpolator(1.02f));
        this.animatorSet.start();
        return;
    L54:
        r72 = 1.0f;
        goto L55
    L50:
        r7 = 1.0f;
        goto L51
    L46:
        r5 = 0.0f;
        goto L47
    L42:
        r04 = 0.0f;
        goto L43
    L38:
        r143 = 0.0f;
        goto L39
    L34:
        this.addButton.setVisibility(0);
        goto L35
    L61:
        if (r102 == false) goto L64;
        this.delButton.setVisibility(0);
        this.delButton.setAlpha(1.0f);
        this.delButton.setScaleX(1.0f);
        this.delButton.setScaleY(1.0f);
        this.addButton.setVisibility(4);
        this.addButton.setAlpha(0.0f);
        this.addButton.setScaleX(0.0f);
        this.addButton.setScaleY(0.0f);
        return;
    L64:
        this.addButton.setVisibility(0);
        this.addButton.setAlpha(1.0f);
        this.addButton.setScaleX(1.0f);
        this.addButton.setScaleY(1.0f);
        this.delButton.setVisibility(4);
        this.delButton.setAlpha(0.0f);
        this.delButton.setScaleX(0.0f);
        this.delButton.setScaleY(0.0f);
        return;
    L26:
        if (MediaDataController.getInstance(this.currentAccount).isStickerPackInstalled(r10.set.id) == true) goto L29;
        r102 = false;
        goto L30
    L66:
        r133.setVisibility(8);
        return;
    L68:
        return;
    L18:
        r03.setText(LocaleController.formatPluralString("Stickers", r132.count, new Object[0]));
        goto L19
    L14:
        this.nameTextView.setText(r10.set.title);
        goto L15
    }

    public void setUrl(CharSequence r1, int r2) {
        this.url = r1;
        this.urlSearchLength = r2;
        updateUrlSearchSpan();
    }

    public void updateColors() {
        if (this.canAddRemove == false) goto L5;
        this.addButton.setProgressColor(getThemedColor(Theme.key_featuredStickers_buttonProgress));
        this.addButton.setBackgroundRoundRect(getThemedColor(Theme.key_featuredStickers_addButton), getThemedColor(Theme.key_featuredStickers_addButtonPressed));
    L5:
        updateStickerSetNameSearchSpan();
        updateUrlSearchSpan();
    }
}
