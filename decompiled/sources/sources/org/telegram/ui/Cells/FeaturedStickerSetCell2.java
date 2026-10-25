package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.PremiumButtonView;
import org.telegram.ui.Components.ProgressButton;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

/* loaded from: classes6.dex */
public class FeaturedStickerSetCell2 extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    private final ProgressButton addButton;
    private boolean bindedObserver;
    private final int currentAccount;
    private AnimatorSet currentAnimation;
    private final TextView delButton;
    private boolean forceInstalled;
    private final BackupImageView imageView;
    private boolean isInstalled;
    private boolean isLocked;
    private boolean needDivider;
    private final Theme.ResourcesProvider resourcesProvider;
    private TLRPC.StickerSetCovered stickersSet;
    private final TextView textView;
    private final PremiumButtonView unlockButton;
    private boolean unread;
    private final TextView valueTextView;
    private Long waitingForStickerSetId;

    /* renamed from: org.telegram.ui.Cells.FeaturedStickerSetCell2$1, reason: invalid class name */
    public class AnonymousClass1 extends Drawable {
        Paint paint;
        final /* synthetic */ FeaturedStickerSetCell2 this$0;

        public AnonymousClass1(FeaturedStickerSetCell2 r2) {
            this.this$0 = r2;
            this.paint = new Paint(1);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r4) {
            this.paint.setColor(-12277526);
            r4.drawCircle(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(3.0f), this.paint);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return AndroidUtilities.dp(8.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return AndroidUtilities.dp(12.0f);
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

    /* renamed from: org.telegram.ui.Cells.FeaturedStickerSetCell2$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ FeaturedStickerSetCell2 this$0;

        public AnonymousClass2(FeaturedStickerSetCell2 r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            boolean r32 = FeaturedStickerSetCell2.m6938$$Nest$fgetisLocked(this.this$0);
            FeaturedStickerSetCell2 r0 = this.this$0;
            if (r32 == false) goto L6;
            FeaturedStickerSetCell2.m6935$$Nest$fgetaddButton(r0).setVisibility(4);
            FeaturedStickerSetCell2.m6936$$Nest$fgetdelButton(this.this$0).setVisibility(4);
            FeaturedStickerSetCell2.m6939$$Nest$fgetunlockButton(this.this$0).setVisibility(0);
            return;
        L6:
            boolean r33 = FeaturedStickerSetCell2.m6937$$Nest$fgetisInstalled(r0);
            FeaturedStickerSetCell2 r02 = this.this$0;
            if (r33 == false) goto L9;
            FeaturedStickerSetCell2.m6935$$Nest$fgetaddButton(r02).setVisibility(4);
        L10:
            FeaturedStickerSetCell2.m6939$$Nest$fgetunlockButton(this.this$0).setVisibility(8);
            return;
        L9:
            FeaturedStickerSetCell2.m6936$$Nest$fgetdelButton(r02).setVisibility(4);
            goto L10
        }
    }

    public static final class Factory extends UItem.UItemFactory<FeaturedStickerSetCell2> {
        static {
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        public static UItem of(TLRPC.StickerSetCovered r1) {
            UItem r0 = UItem.ofFactory(Factory.class);
            r0.object = r1;
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r7, UItem r8, boolean r9, UniversalAdapter r10, UniversalRecyclerView r11) {
            FeaturedStickerSetCell2 r0 = (FeaturedStickerSetCell2) r7;
            r0.setStickersSet((TLRPC.StickerSetCovered) r8.object, r9, false, r8.locked, false);
            r0.setDrawProgress(r8.locked, false);
            r0.setAddOnClickListener(r8.clickCallback);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public FeaturedStickerSetCell2 createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return new FeaturedStickerSetCell2(r1, r5);
        }
    }

    public static /* synthetic */ void $r8$lambda$3wRxfnl7VQIOvTf2Zd8U_RaPUIU(FeaturedStickerSetCell2 r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: -$$Nest$fgetaddButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ProgressButton m6935$$Nest$fgetaddButton(FeaturedStickerSetCell2 r0) {
        return r0.addButton;
    }

    /* renamed from: -$$Nest$fgetdelButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m6936$$Nest$fgetdelButton(FeaturedStickerSetCell2 r0) {
        return r0.delButton;
    }

    /* renamed from: -$$Nest$fgetisInstalled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6937$$Nest$fgetisInstalled(FeaturedStickerSetCell2 r0) {
        return r0.isInstalled;
    }

    /* renamed from: -$$Nest$fgetisLocked, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6938$$Nest$fgetisLocked(FeaturedStickerSetCell2 r0) {
        return r0.isLocked;
    }

    /* renamed from: -$$Nest$fgetunlockButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PremiumButtonView m6939$$Nest$fgetunlockButton(FeaturedStickerSetCell2 r0) {
        return r0.unlockButton;
    }

    public FeaturedStickerSetCell2(Context r18, Theme.ResourcesProvider r19) {
        super(r18);
        this.currentAccount = UserConfig.selectedAccount;
        this.resourcesProvider = r19;
        TextView r3 = new TextView(r18);
        this.textView = r3;
        r3.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        r3.setTextSize(1, 16.0f);
        r3.setLines(1);
        r3.setMaxLines(1);
        r3.setSingleLine(true);
        TextUtils.TruncateAt r4 = TextUtils.TruncateAt.END;
        r3.setEllipsize(r4);
        int r7 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r6 = 5;
    L6:
        r3.setGravity(r6);
        boolean r62 = LocaleController.isRTL;
        if (r62 == false) goto L9;
        int r11 = 5;
    L11:
        if (r62 == false) goto L13;
        float r12 = 22.0f;
    L14:
        if (r62 == false) goto L16;
        float r14 = 71.0f;
    L17:
        addView(r3, LayoutHelper.createFrame(-2, -2.0f, r11, r12, 10.0f, r14, 0.0f));
        TextView r32 = new TextView(r18);
        this.valueTextView = r32;
        r32.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2));
        r32.setTextSize(1, 13.0f);
        r32.setLines(1);
        r32.setMaxLines(1);
        r32.setSingleLine(true);
        r32.setEllipsize(r4);
        if (LocaleController.isRTL == false) goto L20;
        int r42 = 5;
    L21:
        r32.setGravity(r42);
        boolean r43 = LocaleController.isRTL;
        if (r43 == false) goto L24;
        int r112 = 5;
    L26:
        if (r43 == false) goto L28;
        float r122 = 100.0f;
    L29:
        if (r43 == false) goto L31;
        float r142 = 71.0f;
    L32:
        addView(r32, LayoutHelper.createFrame(-2, -2.0f, r112, r122, 35.0f, r142, 0.0f));
        BackupImageView r33 = new BackupImageView(r18);
        this.imageView = r33;
        r33.setAspectFit(true);
        r33.setLayerNum(1);
        boolean r44 = LocaleController.isRTL;
        if (r44 == false) goto L35;
        r7 = 5;
    L35:
        int r10 = r7 | 48;
        if (r44 == false) goto L38;
        float r113 = 0.0f;
    L39:
        if (r44 == false) goto L41;
        float r13 = 12.0f;
    L42:
        addView(r33, LayoutHelper.createFrame(48, 48.0f, r10, r113, 8.0f, r13, 0.0f));
        ProgressButton r34 = new ProgressButton(r18);
        this.addButton = r34;
        r34.setText(LocaleController.getString(R.string.Add));
        r34.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
        addView(r34, LayoutHelper.createFrameRelatively(-2.0f, 28.0f, 8388661, 0.0f, 18.0f, 14.0f, 0.0f));
        TextView r35 = new TextView(r18);
        this.delButton = r35;
        r35.setGravity(17);
        r35.setTextColor(Theme.getColor(Theme.key_featuredStickers_removeButtonText));
        r35.setTextSize(1, 14.0f);
        r35.setTypeface(AndroidUtilities.bold());
        r35.setText(LocaleController.getString(R.string.StickersRemove));
        addView(r35, LayoutHelper.createFrameRelatively(-2.0f, 28.0f, 8388661, 0.0f, 16.0f, 14.0f, 0.0f));
        PremiumButtonView r36 = new PremiumButtonView(r18, AndroidUtilities.dp(4.0f), false, r19);
        this.unlockButton = r36;
        r36.setIcon(R.raw.unlock_icon);
        r36.setButton(LocaleController.getString(R.string.Unlock), new FeaturedStickerSetCell2$$ExternalSyntheticLambda0(this));
        r36.setVisibility(8);
        ViewGroup.MarginLayoutParams r1 = (ViewGroup.MarginLayoutParams) r36.getIconView().getLayoutParams();     // Catch: Exception -> L46
        r1.leftMargin = AndroidUtilities.dp(1.0f);     // Catch: Exception -> L46
        r1.topMargin = AndroidUtilities.dp(1.0f);     // Catch: Exception -> L46
        int r2 = AndroidUtilities.dp(20.0f);     // Catch: Exception -> L46
        r1.height = r2;     // Catch: Exception -> L46
        r1.width = r2;     // Catch: Exception -> L46
        ((ViewGroup.MarginLayoutParams) r36.getTextView().getLayoutParams()).leftMargin = AndroidUtilities.dp(3.0f);     // Catch: Exception -> L46
        r36.getChildAt(0).setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);     // Catch: Exception -> L46
    L44:
        addView(this.unlockButton, LayoutHelper.createFrameRelatively(-2.0f, 28.0f, 8388661, 0.0f, 16.0f, 10.0f, 0.0f));
        updateColors();
        return;
    L41:
        r13 = 0.0f;
        goto L42
    L38:
        r113 = 12.0f;
        goto L39
    L31:
        r142 = 100.0f;
        goto L32
    L28:
        r122 = 71.0f;
        goto L29
    L24:
        r112 = 3;
        goto L26
    L20:
        r42 = 3;
        goto L21
    L16:
        r14 = 22.0f;
        goto L17
    L13:
        r12 = 71.0f;
        goto L14
    L9:
        r11 = 3;
        goto L11
    L5:
        r6 = 3;
        goto L6
    }

    public static void createThemeDescriptions(List<ThemeDescription> r20, RecyclerListView r21, ThemeDescription.ThemeDescriptionDelegate r22) {
        r20.add(new ThemeDescription(r21, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{FeaturedStickerSetCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r20.add(new ThemeDescription(r21, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{FeaturedStickerSetCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText2));
        r20.add(new ThemeDescription(r21, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{FeaturedStickerSetCell.class}, new String[]{"addButton"}, null, null, null, Theme.key_featuredStickers_buttonText));
        r20.add(new ThemeDescription(r21, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{FeaturedStickerSetCell.class}, new String[]{"delButton"}, null, null, null, Theme.key_featuredStickers_removeButtonText));
        r20.add(new ThemeDescription(r21, 0, new Class[]{FeaturedStickerSetCell.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r20.add(new ThemeDescription(null, 0, null, null, null, r22, Theme.key_featuredStickers_buttonProgress));
        r20.add(new ThemeDescription(null, 0, null, null, null, r22, Theme.key_featuredStickers_addButtonPressed));
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        onPremiumButtonClick();
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r7, int r8, Object... r9) {
        if (r7 != NotificationCenter.groupStickersDidLoad) goto L10;
        long r72 = ((Long) r9[0]).longValue();
        Long r0 = this.waitingForStickerSetId;
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.longValue() != r72) goto L12;
        this.waitingForStickerSetId = null;
        TLRPC.TL_stickerSetNoCovered r1 = new TLRPC.TL_stickerSetNoCovered();
        r1.set = ((TLRPC.TL_messages_stickerSet) r9[1]).set;
        setStickersSet(r1, this.needDivider, this.unread, this.forceInstalled, true);
        return;
    L12:
        return;
    }

    public BackupImageView getImageView() {
        return this.imageView;
    }

    public TLRPC.StickerSetCovered getStickerSet() {
        return this.stickersSet;
    }

    public TextView getTextView() {
        return this.textView;
    }

    public boolean isInstalled() {
        return this.isInstalled;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.bindedObserver == false) goto L6;
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.groupStickersDidLoad);
        this.bindedObserver = false;
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r9) {
        if (this.needDivider == true) goto L5;
        return;
    L5:
        if (LocaleController.isRTL == false) goto L8;
        float r0 = 0.0f;
    L7:
        float r3 = r0;
        float r4 = getHeight() - 1;
        int r02 = getWidth();
        if (LocaleController.isRTL == false) goto L12;
        int r1 = AndroidUtilities.dp(71.0f);
    L13:
        r9.drawLine(r3, r4, r02 - r1, getHeight() - 1, Theme.dividerPaint);
        return;
    L12:
        r1 = 0;
        goto L13
    L8:
        r0 = AndroidUtilities.dp(71.0f);
        goto L7
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r8, int r9) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r8), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64.0f) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
        int r4 = this.addButton.getMeasuredWidth();
        int r0 = this.delButton.getMeasuredWidth();
        FrameLayout.LayoutParams r1 = (FrameLayout.LayoutParams) this.delButton.getLayoutParams();
        if (r0 >= r4) goto L5;
        r1.rightMargin = AndroidUtilities.dp(14.0f) + ((r4 - r0) / 2);
    L6:
        measureChildWithMargins(this.textView, r8, r4, r9, 0);
        return;
    L5:
        r1.rightMargin = AndroidUtilities.dp(14.0f);
        goto L6
    }

    public void onPremiumButtonClick() {
    }

    public void setAddOnClickListener(View.OnClickListener r2) {
        this.addButton.setOnClickListener(r2);
        this.delButton.setOnClickListener(r2);
    }

    public void setDrawProgress(boolean r1, boolean r2) {
        this.addButton.setDrawProgress(r1, r2);
    }

    public void setStickersSet(TLRPC.StickerSetCovered r17, boolean r18, boolean r19, boolean r20, boolean r21) {
        AnimatorSet r3 = this.currentAnimation;
        TLRPC.Document r4 = null;
        if (r3 == null) goto L5;
        r3.cancel();
        this.currentAnimation = null;
    L5:
        this.needDivider = r18;
        this.stickersSet = r17;
        setWillNotDraw(!r18);
        this.textView.setText(this.stickersSet.set.title);
        this.unread = r19;
        if (r19 == false) goto L15;
        AnonymousClass1 r1 = new AnonymousClass1(this);
        TextView r2 = this.textView;
        boolean r32 = LocaleController.isRTL;
        if (r32 == false) goto L10;
        AnonymousClass1 r5 = null;
    L11:
        if (r32 == true) goto L14;
        r1 = null;
    L14:
        r2.setCompoundDrawablesWithIntrinsicBounds(r5, null, r1, null);
    L16:
        TextView r12 = this.valueTextView;
        TLRPC.StickerSet r22 = r17.set;
        if (r22.emojis == false) goto L19;
        String r33 = "EmojiCount";
    L20:
        r12.setText(LocaleController.formatPluralString(r33, r22.count, new Object[0]));
        if ((r17 instanceof TLRPC.TL_stickerSetNoCovered) == false) goto L40;
        TLRPC.StickerSet r13 = r17.set;
        if (r13 == null) goto L40;
        this.waitingForStickerSetId = Long.valueOf(r13.id);
        if (this.bindedObserver == true) goto L27;
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.groupStickersDidLoad);
        this.bindedObserver = true;
    L27:
        TLRPC.TL_messages_stickerSet r14 = MediaDataController.getInstance(this.currentAccount).getStickerSet(MediaDataController.getInputStickerSet(r17.set), Integer.valueOf(r17.set.hash), false);
        if (r14 == null) goto L67;
        ArrayList<TLRPC.Document> r23 = r14.documents;
        if (r23 == null) goto L67;
        if (r23.isEmpty() == true) goto L67;
        r4 = r14.documents.get(0);
        int r24 = 0;
    L35:
        if (r24 >= r14.documents.size()) goto L67;
        if (r14.documents.get(r24).id == r17.set.thumb_document_id) goto L38;
        r24 = r24 + 1;
        goto L35
    L38:
        r4 = r14.documents.get(r24);
    L67:
        if (r4 != null) goto L69;
        TLRPC.StickerSetCovered r6 = r17;
        this.imageView.setImage(null, null, "webp", null, r6);
    L100:
        this.addButton.setVisibility(0);
        this.forceInstalled = r20;
        if (r20 == false) goto L103;
    L106:
        boolean r15 = true;
    L107:
        this.isInstalled = r15;
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L110;
    L112:
        boolean r16 = false;
    L113:
        this.isLocked = r16;
        if (r21 == false) goto L169;
        PremiumButtonView r52 = this.unlockButton;
        if (r16 == false) goto L118;
        r52.setVisibility(0);
        this.delButton.setVisibility(0);
        this.addButton.setVisibility(0);
    L122:
        AnimatorSet r110 = new AnimatorSet();
        this.currentAnimation = r110;
        r110.setDuration(250);
        AnimatorSet r111 = this.currentAnimation;
        TextView r53 = this.delButton;
        if (this.isInstalled == true) goto L125;
    L127:
        float r62 = 0.0f;
    L128:
        float[] r7 = {r62};
        Property r63 = View.ALPHA;
        ObjectAnimator r54 = ObjectAnimator.ofFloat(r53, r63, r7);
        TextView r72 = this.delButton;
        if (this.isInstalled == true) goto L131;
    L133:
        float r8 = 0.0f;
    L134:
        float[] r122 = {r8};
        Property r82 = View.SCALE_X;
        ObjectAnimator r73 = ObjectAnimator.ofFloat(r72, r82, r122);
        TextView r123 = this.delButton;
        if (this.isInstalled == true) goto L137;
    L139:
        float r132 = 0.0f;
    L140:
        float[] r142 = {r132};
        Property r133 = View.SCALE_Y;
        ObjectAnimator r124 = ObjectAnimator.ofFloat(r123, r133, r142);
        ProgressButton r143 = this.addButton;
        if (this.isInstalled == false) goto L143;
    L146:
        float r152 = 0.0f;
    L147:
        ObjectAnimator r42 = ObjectAnimator.ofFloat(r143, r63, new float[]{r152});
        ProgressButton r64 = this.addButton;
        if (this.isInstalled == false) goto L150;
    L153:
        float r144 = 0.0f;
    L154:
        ObjectAnimator r65 = ObjectAnimator.ofFloat(r64, r82, new float[]{r144});
        PremiumButtonView r145 = this.unlockButton;
        if (this.isLocked == true) goto L158;
        float r153 = 0.0f;
    L159:
        ObjectAnimator r34 = ObjectAnimator.ofFloat(r145, r133, new float[]{r153});
        PremiumButtonView r146 = this.unlockButton;
        if (this.isLocked == true) goto L162;
        float r154 = 0.0f;
    L163:
        ObjectAnimator r83 = ObjectAnimator.ofFloat(r146, r82, new float[]{r154});
        PremiumButtonView r11 = this.unlockButton;
        if (this.isLocked == true) goto L166;
        float r147 = 0.0f;
    L167:
        r111.playTogether(new Animator[]{r54, r73, r124, r42, r65, r34, r83, ObjectAnimator.ofFloat(r11, r133, new float[]{r147})});
        this.currentAnimation.addListener(new AnonymousClass2(this));
        this.currentAnimation.setInterpolator(new OvershootInterpolator(1.02f));
        this.currentAnimation.start();
        return;
    L166:
        r147 = 1.0f;
        goto L167
    L162:
        r154 = 1.0f;
        goto L163
    L158:
        r153 = 1.0f;
        goto L159
    L150:
        if (this.isLocked == true) goto L153;
        r144 = 1.0f;
        goto L154
    L143:
        if (this.isLocked == true) goto L146;
        r152 = 1.0f;
        goto L147
    L137:
        if (this.isLocked == true) goto L139;
        r132 = 1.0f;
        goto L140
    L131:
        if (this.isLocked == true) goto L133;
        r8 = 1.0f;
        goto L134
    L125:
        if (this.isLocked == true) goto L127;
        r62 = 1.0f;
        goto L128
    L118:
        r52.setVisibility(0);
        if (this.isInstalled == false) goto L121;
        this.delButton.setVisibility(0);
        goto L122
    L121:
        this.addButton.setVisibility(0);
        goto L122
    L169:
        PremiumButtonView r35 = this.unlockButton;
        if (r16 == false) goto L173;
        r35.setVisibility(0);
        this.unlockButton.setAlpha(1.0f);
        this.unlockButton.setScaleX(1.0f);
        this.unlockButton.setScaleY(1.0f);
        this.addButton.setVisibility(4);
        this.addButton.setAlpha(0.0f);
        this.addButton.setScaleX(0.0f);
        this.addButton.setScaleY(0.0f);
        this.delButton.setVisibility(4);
        this.delButton.setAlpha(0.0f);
        this.delButton.setScaleX(0.0f);
        this.delButton.setScaleY(0.0f);
        return;
    L173:
        r35.setVisibility(8);
        this.unlockButton.setAlpha(0.0f);
        this.unlockButton.setScaleX(0.0f);
        this.unlockButton.setScaleY(0.0f);
        if (this.isInstalled == false) goto L177;
        this.delButton.setVisibility(0);
        this.delButton.setAlpha(1.0f);
        this.delButton.setScaleX(1.0f);
        this.delButton.setScaleY(1.0f);
        this.addButton.setVisibility(4);
        this.addButton.setAlpha(0.0f);
        this.addButton.setScaleX(0.0f);
        this.addButton.setScaleY(0.0f);
        return;
    L177:
        this.addButton.setVisibility(0);
        this.addButton.setAlpha(1.0f);
        this.addButton.setScaleX(1.0f);
        this.addButton.setScaleY(1.0f);
        this.delButton.setVisibility(4);
        this.delButton.setAlpha(0.0f);
        this.delButton.setScaleX(0.0f);
        this.delButton.setScaleY(0.0f);
        return;
    L110:
        if (MessageObject.isPremiumEmojiPack(r6) == false) goto L112;
        r16 = true;
        goto L113
    L103:
        if (MediaDataController.getInstance(this.currentAccount).isStickerPackInstalled(r6.set.id) == true) goto L106;
        r15 = false;
        goto L107
    L69:
        if (MessageObject.canAutoplayAnimatedSticker(r4) == false) goto L95;
        TLObject r112 = FileLoader.getClosestPhotoSizeWithSize(r17.set.thumbs, 90);
        if (r112 != null) goto L73;
        r112 = r4;
    L73:
        SvgHelper.SvgDrawable r55 = DocumentObject.getSvgThumb(r17.set.thumbs, Theme.key_windowBackgroundGray, 1.0f);
        boolean r36 = r112 instanceof TLRPC.Document;
        if (r36 == false) goto L77;
        ImageLocation r113 = ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r4.thumbs, 90), r4);
    L76:
        ImageLocation r25 = r113;
        if (r36 == false) goto L89;
        if (MessageObject.isAnimatedStickerDocument(r4, true) == false) goto L82;
    L85:
        BackupImageView r114 = this.imageView;
        if (r55 == null) goto L88;
        r114.setImage(ImageLocation.getForDocument(r4), "50_50", r55, 0, r17);
    L94:
        r6 = r17;
        goto L100
    L88:
        r114.setImage(ImageLocation.getForDocument(r4), "50_50", r25, null, 0, r17);
        goto L94
    L82:
        if (MessageObject.isVideoSticker(r4) == true) goto L85;
    L89:
        if (r25 != null) goto L91;
    L93:
        this.imageView.setImage(r25, "50_50", "webp", r55, r17);
        goto L94
    L91:
        if (r25.imageType != 1) goto L93;
        this.imageView.setImage(r25, "50_50", "tgs", r55, r17);
        goto L94
    L77:
        r113 = ImageLocation.getForSticker((TLRPC.PhotoSize) r112, r4, r17.set.thumb_version);
        goto L76
    L95:
        TLRPC.Document r37 = r4;
        TLRPC.PhotoSize r115 = FileLoader.getClosestPhotoSizeWithSize(r37.thumbs, 90);
        BackupImageView r26 = this.imageView;
        if (r115 == null) goto L98;
        r26.setImage(ImageLocation.getForDocument(r115, r37), "50_50", "webp", null, r17);
        goto L94
    L98:
        r26.setImage(ImageLocation.getForDocument(r37), "50_50", "webp", null, r17);
    L40:
        TLRPC.Document r116 = r17.cover;
        if (r116 == null) goto L44;
    L42:
        r4 = r116;
        goto L67
    L44:
        if (r17.covers.isEmpty() == true) goto L55;
        r4 = r17.covers.get(0);
        if (r17.set == null) goto L67;
        int r117 = 0;
    L49:
        if (r117 >= r17.covers.size()) goto L67;
        if (r17.covers.get(r117).id == r17.set.thumb_document_id) goto L52;
        r117 = r117 + 1;
        goto L49
    L52:
        r116 = r17.covers.get(r117);
        goto L42
    L55:
        if ((r17 instanceof TLRPC.TL_stickerSetFullCovered) == false) goto L67;
        TLRPC.TL_stickerSetFullCovered r118 = (TLRPC.TL_stickerSetFullCovered) r17;
        if (r118.documents.isEmpty() == true) goto L67;
        ArrayList<TLRPC.Document> r119 = r118.documents;
        TLRPC.Document r27 = r119.get(0);
        int r38 = 0;
    L60:
        if (r38 >= r119.size()) goto L65;
        if (r119.get(r38).id == r17.set.thumb_document_id) goto L63;
        r38 = r38 + 1;
        goto L60
    L63:
        r116 = r119.get(r38);
        goto L42
    L65:
        r4 = r27;
        goto L67
    L19:
        r33 = "Stickers";
        goto L20
    L10:
        r5 = r1;
        goto L11
    L15:
        this.textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        goto L16
    }

    public void updateColors() {
        this.addButton.setProgressColor(Theme.getColor(Theme.key_featuredStickers_buttonProgress));
        this.addButton.setBackgroundRoundRect(Theme.getColor(Theme.key_featuredStickers_addButton), Theme.getColor(Theme.key_featuredStickers_addButtonPressed));
    }
}
