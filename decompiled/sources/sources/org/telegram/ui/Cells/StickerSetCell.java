package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import java.util.ArrayList;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LiteMode;
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
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.Easings;
import org.telegram.ui.Components.ForegroundColorSpanThemable;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.PremiumButtonView;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

/* loaded from: classes6.dex */
public class StickerSetCell extends FrameLayout {
    public TextView addButtonView;
    private CheckBox2 checkBox;
    public ImageView deleteView;
    private boolean emojis;
    private boolean groupSearch;
    private BackupImageView imageView;
    private boolean needDivider;
    private final int option;
    private ImageView optionsButton;
    public PremiumButtonView premiumButtonView;
    private RadialProgressView progressView;
    private Rect rect;
    public TextView removeButtonView;
    private ImageView reorderButton;
    private FrameLayout sideButtons;
    private AnimatorSet stateAnimator;
    private TLRPC.TL_messages_stickerSet stickersSet;
    private TextView textView;
    private TextView valueTextView;

    /* renamed from: org.telegram.ui.Cells.StickerSetCell$1, reason: invalid class name */
    public class AnonymousClass1 extends TextView {
        final /* synthetic */ StickerSetCell this$0;

        public AnonymousClass1(StickerSetCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.TextView
        public void setText(CharSequence r3, TextView.BufferType r4) {
            super.setText(Emoji.replaceEmoji(r3, getPaint().getFontMetricsInt(), false), r4);
        }
    }

    /* renamed from: org.telegram.ui.Cells.StickerSetCell$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ StickerSetCell this$0;
        final /* synthetic */ boolean val$checked;

        public AnonymousClass2(StickerSetCell r1, boolean r2) {
            this.this$0 = r1;
            this.val$checked = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (this.val$checked == true) goto L6;
            StickerSetCell.m7054$$Nest$fgetoptionsButton(this.this$0).setVisibility(4);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            if (this.val$checked == false) goto L6;
            StickerSetCell.m7054$$Nest$fgetoptionsButton(this.this$0).setVisibility(0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Cells.StickerSetCell$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ StickerSetCell this$0;
        final /* synthetic */ boolean val$checked;

        public AnonymousClass3(StickerSetCell r1, boolean r2) {
            this.this$0 = r1;
            this.val$checked = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (this.val$checked == true) goto L6;
            StickerSetCell.m7055$$Nest$fgetsideButtons(this.this$0).setVisibility(4);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            if (this.val$checked == false) goto L6;
            StickerSetCell.m7055$$Nest$fgetsideButtons(this.this$0).setVisibility(0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Cells.StickerSetCell$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ StickerSetCell this$0;
        final /* synthetic */ int val$state;

        public AnonymousClass4(StickerSetCell r1, int r2) {
            this.this$0 = r1;
            this.val$state = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r5) {
            PremiumButtonView r52 = this.this$0.premiumButtonView;
            int r0 = this.val$state;
            int r2 = 0;
            if (r0 != 1) goto L5;
        L8:
            int r02 = 0;
        L9:
            r52.setVisibility(r02);
            TextView r53 = this.this$0.addButtonView;
            if (this.val$state != 3) goto L12;
            int r03 = 0;
        L13:
            r53.setVisibility(r03);
            TextView r54 = this.this$0.removeButtonView;
            if (this.val$state == 4) goto L17;
            r2 = 8;
        L17:
            r54.setVisibility(r2);
            this.this$0.updateRightMargin();
            return;
        L12:
            r03 = 8;
            goto L13
        L5:
            if (r0 == 2) goto L8;
            r02 = 8;
            goto L9
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r2) {
            this.this$0.premiumButtonView.setVisibility(0);
            this.this$0.addButtonView.setVisibility(0);
            this.this$0.removeButtonView.setVisibility(0);
        }
    }

    public static final class Factory extends UItem.UItemFactory<StickerSetCell> {
        public static /* synthetic */ boolean $r8$lambda$N3qnKGflVcLtawK7SCRoWHhosIQ(UniversalRecyclerView r0, StickerSetCell r1, View r2, MotionEvent r3) {
            if (r3.getAction() != 0) goto L9;
            ItemTouchHelper r22 = r0.itemTouchHelper;
            if (r22 == null) goto L10;
            r22.startDrag(r0.getChildViewHolder(r1));
            return false;
        L10:
            return false;
        L9:
            return false;
        }

        static {
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        public static UItem of(TLRPC.TL_messages_stickerSet r1) {
            UItem r0 = UItem.ofFactory(Factory.class);
            r0.object = r1;
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void attachedView(RecyclerListView r1, View r2, UItem r3) {
            StickerSetCell r22 = (StickerSetCell) r2;
            r22.setChecked(r3.checked, true);
            if ((r1 instanceof UniversalRecyclerView) == false) goto L5;
            boolean r0 = ((UniversalRecyclerView) r1).isReorderAllowed();
        L6:
            r22.setReorderable(r0, true);
            return;
        L5:
            r0 = false;
            goto L6
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r4, UItem r5, boolean r6, UniversalAdapter r7, UniversalRecyclerView r8) {
            StickerSetCell r42 = (StickerSetCell) r4;
            TLRPC.TL_messages_stickerSet r3 = (TLRPC.TL_messages_stickerSet) r5.object;
            r42.setStickersSet(r3, r6);
            r42.setChecked(r5.checked, false);
            boolean r62 = r8.isReorderAllowed();
            int r82 = 1;
            r42.setReorderable(r62, true);
            r42.setOnOptionsClick(r5.clickCallback);
            r42.addButtonView.setOnClickListener(r5.clickCallback2);
            r42.removeButtonView.setOnClickListener(r5.clickCallback2);
            r42.premiumButtonView.setOnClickListener(r5.clickCallback2);
            if (r3 == null) goto L30;
            TLRPC.StickerSet r52 = r3.set;
            if (r52 != null) goto L7;
            return;
        L7:
            if (r52.emojis == false) goto L32;
            boolean r53 = MediaDataController.getInstance(r7.currentAccount).isStickerPackInstalled(r3.set.id);
            boolean r63 = UserConfig.getInstance(r7.currentAccount).isPremium();
            boolean r72 = !r63;
            if (r63 == true) goto L18;
            int r64 = 0;
        L12:
            if (r64 >= r3.documents.size()) goto L17;
            if (MessageObject.isFreeEmoji(r3.documents.get(r64)) == false) goto L18;
            r64 = r64 + 1;
            goto L12
        L17:
            r72 = false;
        L18:
            if (r72 == false) goto L23;
            if (r53 == true) goto L21;
        L26:
            r42.updateButtonState(r82, false);
            return;
        L21:
            if (r3.set.official == true) goto L26;
            r82 = 2;
            goto L26
        L23:
            if (r53 == false) goto L25;
            r82 = 4;
            goto L26
        L25:
            r82 = 3;
            goto L26
        L32:
            return;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        @SuppressLint({"ClickableViewAccessibility"})
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        @SuppressLint({"ClickableViewAccessibility"})
        public StickerSetCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            StickerSetCell r0 = new StickerSetCell(r1, 1);
            if ((r2 instanceof UniversalRecyclerView) == false) goto L5;
            r0.setOnReorderButtonTouchListener(new StickerSetCell$Factory$$ExternalSyntheticLambda0((UniversalRecyclerView) r2, r0));
        L5:
            return r0;
        }
    }

    public static /* synthetic */ void $r8$lambda$AW9MPJYU7VTiuiaaHHabSlB9NHs(StickerSetCell r0, boolean r1) {
        r0.lambda$setReorderable$7(r1);
    }

    /* renamed from: $r8$lambda$GGLQgtzVI2BVP1A-1Dn9EZVwe7c, reason: not valid java name */
    public static /* synthetic */ void m7050$r8$lambda$GGLQgtzVI2BVP1A1Dn9EZVwe7c(StickerSetCell r0, TLRPC.Document r1) {
        r0.lambda$setStickersSet$4(r1);
    }

    /* renamed from: $r8$lambda$IhGfwscd7Jxx7eLXR3q8A-GtUs0, reason: not valid java name */
    public static /* synthetic */ void m7051$r8$lambda$IhGfwscd7Jxx7eLXR3q8AGtUs0(StickerSetCell r0, TLRPC.Document r1) {
        r0.lambda$setStickersSet$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$Jsxxtlg0amAlFm5NUHdJXqvJNM8(StickerSetCell r0, View r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$KbERFMV2pje_RwKN7ItqAcd8cwA(StickerSetCell r0, boolean r1) {
        r0.lambda$setReorderable$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$W33Mkm9YVwkyx5gCxgGcLknRU48(StickerSetCell r0, View r1) {
        r0.lambda$new$3(r1);
    }

    /* renamed from: $r8$lambda$XdpqxAUsERvu_kIL-Znjd1HhJZ4, reason: not valid java name */
    public static /* synthetic */ void m7052$r8$lambda$XdpqxAUsERvu_kILZnjd1HhJZ4(StickerSetCell r0, boolean r1) {
        r0.lambda$setReorderable$8(r1);
    }

    /* renamed from: $r8$lambda$dlJIFYsKHZqBxo-srZ8TPGjWkik, reason: not valid java name */
    public static /* synthetic */ void m7053$r8$lambda$dlJIFYsKHZqBxosrZ8TPGjWkik(StickerSetCell r0, View r1) {
        r0.lambda$updateButtonState$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$fdG778dZ4ESblOn0QUWm1nVk9cc(StickerSetCell r0, View r1) {
        r0.lambda$updateButtonState$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$uN3rNRHqu_g294wBfpxCM50Jpug(StickerSetCell r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$zvM7x3yLsO8UzxTf47eW8BdIvBM(StickerSetCell r0, View r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: -$$Nest$fgetoptionsButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m7054$$Nest$fgetoptionsButton(StickerSetCell r0) {
        return r0.optionsButton;
    }

    /* renamed from: -$$Nest$fgetsideButtons, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m7055$$Nest$fgetsideButtons(StickerSetCell r0) {
        return r0.sideButtons;
    }

    public StickerSetCell(Context r2, int r3) {
        this(r2, null, r3);
    }

    public StickerSetCell(Context r27, Theme.ResourcesProvider r28, int r29) {
        super(r27);
        this.rect = new Rect();
        this.option = r29;
        BackupImageView r4 = new BackupImageView(r27);
        this.imageView = r4;
        r4.setAspectFit(true);
        this.imageView.setLayerNum(1);
        BackupImageView r42 = this.imageView;
        boolean r6 = LocaleController.isRTL;
        if (r6 == false) goto L5;
        int r9 = 5;
    L6:
        int r12 = r9 | 48;
        if (r6 == false) goto L9;
        float r13 = 0.0f;
    L10:
        if (r6 == false) goto L12;
        float r15 = 13.0f;
    L13:
        addView(r42, LayoutHelper.createFrame(40, 40.0f, r12, r13, 9.0f, r15, 0.0f));
        if (r29 == 0) goto L37;
        ImageView r11 = new ImageView(r27);
        this.optionsButton = r11;
        r11.setFocusable(false);
        ImageView r112 = this.optionsButton;
        ImageView.ScaleType r122 = ImageView.ScaleType.CENTER;
        r112.setScaleType(r122);
        if (r29 == 3) goto L18;
        this.optionsButton.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_stickers_menuSelector)));
    L18:
        if (r29 != 1) goto L24;
        ImageView r113 = this.optionsButton;
        int r14 = Theme.key_stickers_menu;
        int r152 = Theme.getColor(r14);
        PorterDuff.Mode r7 = PorterDuff.Mode.MULTIPLY;
        r113.setColorFilter(new PorterDuffColorFilter(r152, r7));
        this.optionsButton.setImageResource(R.drawable.msg_actions);
        this.optionsButton.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
        ImageView r114 = this.optionsButton;
        if (LocaleController.isRTL == false) goto L22;
        int r132 = 3;
    L23:
        addView(r114, LayoutHelper.createFrame(40, 40, r132 | 16));
        ImageView r115 = new ImageView(r27);
        this.reorderButton = r115;
        r115.setAlpha(0.0f);
        this.reorderButton.setVisibility(8);
        this.reorderButton.setScaleType(r122);
        this.reorderButton.setImageResource(R.drawable.list_reorder);
        this.reorderButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r14), r7));
        addView(this.reorderButton, LayoutHelper.createFrameRelatively(58.0f, 58.0f, 8388613));
        CheckBox2 r72 = new CheckBox2(r27, 21);
        this.checkBox = r72;
        r72.setColor(-1, Theme.key_windowBackgroundWhite, Theme.key_checkboxCheck);
        this.checkBox.setDrawUnchecked(false);
        this.checkBox.setDrawBackgroundAsArc(3);
        addView(this.checkBox, LayoutHelper.createFrameRelatively(24.0f, 24.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 34.0f, 30.0f, 0.0f, 0.0f));
        goto L37
    L22:
        r132 = 5;
        goto L23
    L24:
        if (r29 != 3) goto L37;
        this.optionsButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_featuredStickers_addedIcon), PorterDuff.Mode.MULTIPLY));
        this.optionsButton.setImageResource(R.drawable.floating_check);
        ImageView r73 = this.optionsButton;
        boolean r116 = LocaleController.isRTL;
        if (r116 == false) goto L28;
        int r123 = 3;
    L29:
        int r19 = r123 | 48;
        int r124 = 10;
        if (r116 == false) goto L32;
        int r133 = 10;
    L33:
        float r134 = r133;
        if (r116 == false) goto L36;
        r124 = 0;
    L36:
        addView(r73, LayoutHelper.createFrame(40, 40.0f, r19, r134, 9.0f, r124, 0.0f));
        goto L37
    L32:
        r133 = 0;
        goto L33
    L28:
        r123 = 5;
    L37:
        this.sideButtons = new FrameLayout(getContext());
        TextView r74 = new TextView(r27);
        this.addButtonView = r74;
        r74.setTextSize(1, 14.0f);
        this.addButtonView.setTypeface(AndroidUtilities.bold());
        this.addButtonView.setText(LocaleController.getString(R.string.Add));
        this.addButtonView.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText, r28));
        TextView r75 = this.addButtonView;
        int r125 = Theme.key_featuredStickers_addButton;
        r75.setBackground(Theme.AdaptiveRipple.createRect(Theme.getColor(r125, r28), Theme.getColor(Theme.key_featuredStickers_addButtonPressed, r28), new float[]{14.0f}));
        this.addButtonView.setPadding(AndroidUtilities.dp(14.0f), 0, AndroidUtilities.dp(14.0f), 0);
        this.addButtonView.setGravity(17);
        this.addButtonView.setOnClickListener(new StickerSetCell$$ExternalSyntheticLambda5(this));
        FrameLayout r76 = this.sideButtons;
        TextView r142 = this.addButtonView;
        if (LocaleController.isRTL == false) goto L40;
        int r153 = 3;
    L41:
        r76.addView(r142, LayoutHelper.createFrameRelatively(-2.0f, 28.0f, r153 | 16));
        ScaleStateListAnimator.apply(this.addButtonView);
        TextView r77 = new TextView(r27);
        this.removeButtonView = r77;
        r77.setTextSize(1, 14.0f);
        this.removeButtonView.setTypeface(AndroidUtilities.bold());
        this.removeButtonView.setText(LocaleController.getString(R.string.StickersRemove));
        this.removeButtonView.setTextColor(Theme.getColor(Theme.key_featuredStickers_removeButtonText, r28));
        this.removeButtonView.setBackground(Theme.AdaptiveRipple.createRect(0, Theme.getColor(r125, r28) & 452984831, new float[]{14.0f}));
        this.removeButtonView.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
        this.removeButtonView.setGravity(17);
        this.removeButtonView.setOnClickListener(new StickerSetCell$$ExternalSyntheticLambda6(this));
        FrameLayout r78 = this.sideButtons;
        TextView r117 = this.removeButtonView;
        if (LocaleController.isRTL == false) goto L44;
        int r126 = 3;
    L45:
        r78.addView(r117, LayoutHelper.createFrameRelatively(-2.0f, 32.0f, r126 | 16, 0.0f, -2.0f, 0.0f, 0.0f));
        ScaleStateListAnimator.apply(this.removeButtonView);
        PremiumButtonView r79 = new PremiumButtonView(r27, AndroidUtilities.dp(4.0f), false, r28);
        this.premiumButtonView = r79;
        r79.setIcon(R.raw.unlock_icon);
        this.premiumButtonView.setButton(LocaleController.getString(R.string.Unlock), new StickerSetCell$$ExternalSyntheticLambda7(this));
        ViewGroup.MarginLayoutParams r710 = (ViewGroup.MarginLayoutParams) this.premiumButtonView.getIconView().getLayoutParams();     // Catch: Exception -> L71
        r710.leftMargin = AndroidUtilities.dp(1.0f);     // Catch: Exception -> L71
        r710.topMargin = AndroidUtilities.dp(1.0f);     // Catch: Exception -> L71
        int r127 = AndroidUtilities.dp(20.0f);     // Catch: Exception -> L71
        r710.height = r127;     // Catch: Exception -> L71
        r710.width = r127;     // Catch: Exception -> L71
        ((ViewGroup.MarginLayoutParams) this.premiumButtonView.getTextView().getLayoutParams()).leftMargin = AndroidUtilities.dp(3.0f);     // Catch: Exception -> L71
        this.premiumButtonView.getChildAt(0).setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);     // Catch: Exception -> L71
    L47:
        FrameLayout r711 = this.sideButtons;
        PremiumButtonView r128 = this.premiumButtonView;
        if (LocaleController.isRTL == false) goto L50;
        int r135 = 3;
    L51:
        r711.addView(r128, LayoutHelper.createFrameRelatively(-2.0f, 28.0f, r135 | 16));
        ScaleStateListAnimator.apply(this.premiumButtonView);
        this.sideButtons.setPadding(AndroidUtilities.dp(10.0f), 0, AndroidUtilities.dp(10.0f), 0);
        FrameLayout r43 = this.sideButtons;
        if (LocaleController.isRTL == false) goto L54;
        int r21 = 3;
    L55:
        addView(r43, LayoutHelper.createFrame(-2, -1.0f, r21, 0.0f, 0.0f, 0.0f, 0.0f));
        this.sideButtons.setOnClickListener(new StickerSetCell$$ExternalSyntheticLambda8(this));
        AnonymousClass1 r44 = new AnonymousClass1(this, r27);
        this.textView = r44;
        NotificationCenter.listenEmojiLoading(r44);
        this.textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.textView.setTextSize(1, 16.0f);
        this.textView.setTypeface(AndroidUtilities.bold());
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        this.textView.setEllipsize(TextUtils.TruncateAt.END);
        this.textView.setGravity(LayoutHelper.getAbsoluteGravityStart());
        addView(this.textView, LayoutHelper.createFrameRelatively(-2.0f, -2.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 71.0f, 9.0f, 70.0f, 0.0f));
        TextView r45 = new TextView(r27);
        this.valueTextView = r45;
        r45.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2));
        this.valueTextView.setTextSize(1, 13.0f);
        this.valueTextView.setLines(1);
        this.valueTextView.setMaxLines(1);
        this.valueTextView.setSingleLine(true);
        this.valueTextView.setGravity(LayoutHelper.getAbsoluteGravityStart());
        addView(this.valueTextView, LayoutHelper.createFrameRelatively(-2.0f, -2.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 71.0f, 32.0f, 70.0f, 0.0f));
        if (r29 != 3) goto L69;
        ImageView r3 = new ImageView(r27);
        this.deleteView = r3;
        r3.setImageResource(R.drawable.msg_close);
        this.deleteView.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        this.deleteView.setColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText), PorterDuff.Mode.SRC_IN);
        this.deleteView.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector)));
        this.deleteView.setVisibility(8);
        ImageView r1 = this.deleteView;
        boolean r2 = LocaleController.isRTL;
        if (r2 == false) goto L60;
        int r712 = 3;
    L61:
        int r20 = r712 | 16;
        if (r2 == false) goto L64;
        float r212 = 4.0f;
    L65:
        if (r2 == false) goto L67;
        float r23 = 0.0f;
    L68:
        addView(r1, LayoutHelper.createFrame(-2, -2.0f, r20, r212, 0.0f, r23, 0.0f));
        goto L69
    L67:
        r23 = 4.0f;
        goto L68
    L64:
        r212 = 0.0f;
        goto L65
    L60:
        r712 = 5;
    L69:
        updateButtonState(0, false);
        return;
    L54:
        r21 = 5;
        goto L55
    L50:
        r135 = 5;
        goto L51
    L44:
        r126 = 5;
        goto L45
    L40:
        r153 = 5;
        goto L41
    L12:
        r15 = 0.0f;
        goto L13
    L9:
        r13 = 13.0f;
        goto L10
    L5:
        r9 = 3;
        goto L6
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        onAddButtonClick();
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        onRemoveButtonClick();
    }

    private /* synthetic */ void lambda$new$2(View r1) {
        onPremiumButtonClick();
    }

    private /* synthetic */ void lambda$new$3(View r1) {
        if (this.premiumButtonView.getVisibility() != 0) goto L9;
        if (this.premiumButtonView.isEnabled() == false) goto L9;
        this.premiumButtonView.performClick();
        return;
    L9:
        if (this.addButtonView.getVisibility() != 0) goto L15;
        if (this.addButtonView.isEnabled() == false) goto L15;
        this.addButtonView.performClick();
        return;
    L15:
        if (this.removeButtonView.getVisibility() == 0) goto L17;
        return;
    L17:
        if (this.removeButtonView.isEnabled() == false) goto L21;
        this.removeButtonView.performClick();
        return;
    }

    private /* synthetic */ void lambda$setReorderable$6(boolean r1) {
        if (r1 == true) goto L5;
        this.reorderButton.setVisibility(8);
        return;
    }

    private /* synthetic */ void lambda$setReorderable$7(boolean r1) {
        if (r1 == false) goto L5;
        this.sideButtons.setVisibility(8);
        return;
    }

    private /* synthetic */ void lambda$setReorderable$8(boolean r1) {
        if (r1 == false) goto L5;
        this.optionsButton.setVisibility(8);
        return;
    }

    private /* synthetic */ void lambda$setStickersSet$4(TLRPC.Document r6) {
        if (this.stickersSet.documents.isEmpty() == false) goto L8;
        TLRPC.TL_messages_stickerSet r0 = this.stickersSet;
        if (r0.set.thumb_document_id != r6.id) goto L9;
        r0.documents.add(r6);
        setStickersSet(this.stickersSet, this.needDivider, this.groupSearch);
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$setStickersSet$5(TLRPC.Document r2) {
        AndroidUtilities.runOnUIThread(new StickerSetCell$$ExternalSyntheticLambda10(this, r2));
    }

    private /* synthetic */ void lambda$updateButtonState$10(View r1) {
        onPremiumButtonClick();
    }

    private /* synthetic */ void lambda$updateButtonState$9(View r1) {
        onPremiumButtonClick();
    }

    public TLRPC.TL_messages_stickerSet getStickersSet() {
        return this.stickersSet;
    }

    public boolean isChecked() {
        int r0 = this.option;
        if (r0 != 1) goto L7;
        return this.checkBox.isChecked();
    L7:
        if (r0 != 3) goto L13;
        if (this.optionsButton.getVisibility() != 0) goto L11;
        return true;
    L11:
        return false;
    L13:
        if (this.emojis == true) goto L15;
    L17:
        return false;
    L15:
        if (this.sideButtons.getVisibility() != 0) goto L17;
        return true;
    }

    public void onAddButtonClick() {
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
        int r02 = getWidth() - getPaddingRight();
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

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
        super.onInitializeAccessibilityNodeInfo(r1);
        CheckBox2 r0 = this.checkBox;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isChecked() == false) goto L9;
        r1.setCheckable(true);
        r1.setChecked(true);
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(58.0f) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
    }

    public void onPremiumButtonClick() {
    }

    public void onRemoveButtonClick() {
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r5) {
        if (getBackground() == null) goto L10;
        ImageView r0 = this.optionsButton;
        if (r0 == null) goto L10;
        r0.getHitRect(this.rect);
        if (this.rect.contains((int) r5.getX(), (int) r5.getY()) == false) goto L10;
        return true;
    L10:
        if (getBackground() == null) goto L19;
        if (this.emojis == false) goto L19;
        FrameLayout r02 = this.sideButtons;
        if (r02 == null) goto L19;
        r02.getHitRect(this.rect);
        if (this.rect.contains((int) r5.getX(), (int) r5.getY()) == false) goto L19;
        return true;
    L19:
        return super.onTouchEvent(r5);
    }

    public void setChecked(boolean r2) {
        setChecked(r2, true);
    }

    public void setChecked(boolean r10, boolean r11) {
        int r0 = this.option;
        if (r0 != 1) goto L6;
        this.checkBox.setChecked(r10, r11);
        return;
    L6:
        int r2 = 4;
        float r6 = 0.0f;
        float r7 = 0.1f;
        if (r0 != 3) goto L31;
        ImageView r02 = this.optionsButton;
        if (r11 == false) goto L22;
        r02.animate().cancel();
        ViewPropertyAnimator r9 = this.optionsButton.animate().setListener(new AnonymousClass2(this, r10));
        if (r10 == false) goto L13;
        r6 = 1.0f;
    L13:
        ViewPropertyAnimator r92 = r9.alpha(r6);
        if (r10 == false) goto L16;
        float r112 = 1.0f;
    L17:
        ViewPropertyAnimator r93 = r92.scaleX(r112);
        if (r10 == false) goto L20;
        r7 = 1.0f;
    L20:
        r93.scaleY(r7).setDuration(150).start();
        return;
    L16:
        r112 = 0.1f;
        goto L17
    L22:
        if (r10 == false) goto L24;
        r2 = 0;
    L24:
        r02.setVisibility(r2);
        ImageView r113 = this.optionsButton;
        if (r10 == true) goto L28;
        r113.setAlpha(0.0f);
        this.optionsButton.setScaleX(0.1f);
        this.optionsButton.setScaleY(0.1f);
        return;
    L28:
        r113.setAlpha(1.0f);
        this.optionsButton.setScaleX(1.0f);
        this.optionsButton.setScaleY(1.0f);
        return;
    L31:
        if (this.emojis == false) goto L54;
        FrameLayout r03 = this.sideButtons;
        if (r11 == false) goto L46;
        r03.animate().cancel();
        ViewPropertyAnimator r94 = this.sideButtons.animate().setListener(new AnonymousClass3(this, r10));
        if (r10 == false) goto L37;
        r6 = 1.0f;
    L37:
        ViewPropertyAnimator r95 = r94.alpha(r6);
        if (r10 == false) goto L40;
        float r114 = 1.0f;
    L41:
        ViewPropertyAnimator r96 = r95.scaleX(r114);
        if (r10 == false) goto L44;
        r7 = 1.0f;
    L44:
        r96.scaleY(r7).setDuration(150).start();
        return;
    L40:
        r114 = 0.1f;
        goto L41
    L46:
        if (r10 == false) goto L48;
        r2 = 0;
    L48:
        r03.setVisibility(r2);
        FrameLayout r115 = this.sideButtons;
        if (r10 == true) goto L52;
        r115.setAlpha(0.0f);
        this.sideButtons.setScaleX(0.1f);
        this.sideButtons.setScaleY(0.1f);
        return;
    L52:
        r115.setAlpha(1.0f);
        this.sideButtons.setScaleX(1.0f);
        this.sideButtons.setScaleY(1.0f);
        return;
    }

    public void setDeleteAction(View.OnClickListener r3) {
        ImageView r0 = this.deleteView;
        if (r0 == null) goto L9;
        if (r3 != null) goto L6;
        int r1 = 8;
    L7:
        r0.setVisibility(r1);
        this.deleteView.setOnClickListener(r3);
        return;
    L6:
        r1 = 0;
        goto L7
    }

    public void setNeedDivider(boolean r1) {
        this.needDivider = r1;
    }

    public void setOnOptionsClick(View.OnClickListener r1) {
        ImageView r0 = this.optionsButton;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setOnClickListener(r1);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public void setOnReorderButtonTouchListener(View.OnTouchListener r1) {
        this.reorderButton.setOnTouchListener(r1);
    }

    public void setReorderable(boolean r2) {
        setReorderable(r2, true);
    }

    public void setReorderable(boolean r9, boolean r10) {
        if (this.option != 1) goto L41;
        float r0 = 0.0f;
        float r2 = 1.0f;
        if (r9 == false) goto L7;
        float r3 = 1.0f;
    L8:
        if (r9 == true) goto L11;
        r0 = 1.0f;
    L11:
        int r6 = 0;
        float[] r5 = {r3, r0};
        if (r9 == false) goto L14;
        float r32 = 1.0f;
    L15:
        if (r9 == false) goto L17;
        r2 = 0.66f;
    L17:
        float[] r02 = {r32, r2};
        ImageView r22 = this.reorderButton;
        if (r10 == false) goto L26;
        r22.setVisibility(0);
        ViewPropertyAnimator r102 = this.reorderButton.animate().alpha(r5[0]).scaleX(r02[0]).scaleY(r02[0]).setDuration(200);
        Interpolator r4 = Easings.easeOutSine;
        r102.setInterpolator(r4).withEndAction(new StickerSetCell$$ExternalSyntheticLambda2(this, r9)).start();
        if (this.emojis == false) goto L23;
        this.sideButtons.setVisibility(0);
        this.sideButtons.animate().alpha(r5[1]).scaleX(r02[1]).scaleY(r02[1]).setDuration(200).setInterpolator(r4).withEndAction(new StickerSetCell$$ExternalSyntheticLambda3(this, r9)).start();
        return;
    L23:
        this.optionsButton.setVisibility(0);
        this.optionsButton.animate().alpha(r5[1]).scaleX(r02[1]).scaleY(r02[1]).setDuration(200).setInterpolator(r4).withEndAction(new StickerSetCell$$ExternalSyntheticLambda4(this, r9)).start();
        return;
    L26:
        if (r9 == false) goto L28;
        int r33 = 0;
    L29:
        r22.setVisibility(r33);
        this.reorderButton.setAlpha(r5[0]);
        this.reorderButton.setScaleX(r02[0]);
        this.reorderButton.setScaleY(r02[0]);
        if (this.emojis == false) goto L36;
        FrameLayout r23 = this.sideButtons;
        if (r9 == false) goto L34;
        r6 = 8;
    L34:
        r23.setVisibility(r6);
        this.sideButtons.setAlpha(r5[1]);
        this.sideButtons.setScaleX(r02[1]);
        this.sideButtons.setScaleY(r02[1]);
        return;
    L36:
        ImageView r24 = this.optionsButton;
        if (r9 == false) goto L39;
        r6 = 8;
    L39:
        r24.setVisibility(r6);
        this.optionsButton.setAlpha(r5[1]);
        this.optionsButton.setScaleX(r02[1]);
        this.optionsButton.setScaleY(r02[1]);
        return;
    L28:
        r33 = 8;
        goto L29
    L14:
        r32 = 0.66f;
        goto L15
    L7:
        r3 = 0.0f;
        goto L8
    }

    public void setSearchQuery(TLRPC.TL_messages_stickerSet r8, String r9, Theme.ResourcesProvider r10) {
        TLRPC.StickerSet r82 = r8.set;
        String r0 = r82.title;
        Locale r1 = Locale.ROOT;
        int r02 = r0.toLowerCase(r1).indexOf(r9);
        if (r02 == (-1)) goto L5;
        SpannableString r4 = new SpannableString(r82.title);
        r4.setSpan(new ForegroundColorSpanThemable(Theme.key_windowBackgroundWhiteBlueText4, r10), r02, r9.length() + r02, 0);
        this.textView.setText(r4);
    L5:
        int r03 = r82.short_name.toLowerCase(r1).indexOf(r9);
        if (r03 != (-1)) goto L8;
        return;
    L8:
        if (r82.emojis == false) goto L10;
        String r12 = "t.me/addemoji/";
    L11:
        int r04 = r03 + r12.length();
        SpannableString r3 = new SpannableString(r12 + r82.short_name);
        r3.setSpan(new ForegroundColorSpanThemable(Theme.key_windowBackgroundWhiteBlueText4, r10), r04, r9.length() + r04, 0);
        this.valueTextView.setText(r3);
        return;
    L10:
        r12 = "t.me/addstickers/";
        goto L11
    }

    public void setStickersSet(TLRPC.TL_messages_stickerSet r2, boolean r3) {
        setStickersSet(r2, r3, false);
    }

    @SuppressLint({"SetTextI18n"})
    public void setStickersSet(TLRPC.TL_messages_stickerSet r14, boolean r15, boolean r16) {
        this.needDivider = r15;
        this.stickersSet = r14;
        this.groupSearch = r16;
        this.imageView.setVisibility(0);
        RadialProgressView r1 = this.progressView;
        if (r1 == null) goto L5;
        r1.setVisibility(4);
    L5:
        this.textView.setTranslationY(0.0f);
        this.textView.setText(this.stickersSet.set.title);
        boolean r12 = this.stickersSet.set.archived;
        TextView r3 = this.textView;
        if (r12 == false) goto L8;
        r3.setAlpha(0.5f);
        this.valueTextView.setAlpha(0.5f);
        this.imageView.setAlpha(0.5f);
    L9:
        boolean r13 = r14.set.emojis;
        this.emojis = r13;
        FrameLayout r32 = this.sideButtons;
        int r5 = 8;
        if (r13 == false) goto L12;
        int r17 = 0;
    L13:
        r32.setVisibility(r17);
        ImageView r18 = this.optionsButton;
        if (this.emojis == true) goto L17;
        r5 = 0;
    L17:
        r18.setVisibility(r5);
        this.imageView.setColorFilter(null);
        ArrayList<TLRPC.Document> r19 = r14.documents;
        String r33 = "Stickers";
        if (r19 != null) goto L20;
    L71:
        TextView r110 = this.valueTextView;
        if (r14.set.emojis == false) goto L74;
        r33 = "EmojiCount";
    L74:
        r110.setText(LocaleController.formatPluralString(r33, 0, new Object[0]));
        this.imageView.setImageDrawable(null);
        if (r14.set.thumb_document_id == 0) goto L78;
        AnimatedEmojiDrawable.getDocumentFetcher(UserConfig.selectedAccount).fetchDocument(r14.set.thumb_document_id, new StickerSetCell$$ExternalSyntheticLambda9(this));
    L78:
        if (this.groupSearch == false) goto L89;
        TextView r0 = this.valueTextView;
        StringBuilder r111 = new StringBuilder();
        if (r14.set.emojis == false) goto L82;
        String r2 = "t.me/addemoji/";
    L83:
        r111.append(r2);
        r111.append(r14.set.short_name);
        r0.setText(r111.toString());
        return;
    L82:
        r2 = "t.me/addstickers/";
        goto L83
    L89:
        return;
    L20:
        if (r19.isEmpty() == true) goto L71;
        TextView r7 = this.valueTextView;
        if (this.emojis == false) goto L24;
        r33 = "EmojiCount";
    L24:
        r7.setText(LocaleController.formatPluralString(r33, r19.size(), new Object[0]));
        int r34 = 0;
    L26:
        if (r34 >= r19.size()) goto L33;
        TLRPC.Document r52 = r19.get(r34);
        if (r52 == null) goto L32;
        if (r52.id != r14.set.thumb_document_id) goto L32;
    L34:
        if (r52 != null) goto L36;
        r52 = r19.get(0);
    L36:
        TLRPC.Document r9 = r52;
        LiteMode.isEnabled(1);
        TLObject r22 = FileLoader.getClosestPhotoSizeWithSize(r14.set.thumbs, 90);
        if (r22 != null) goto L39;
        r22 = r9;
    L39:
        SvgHelper.SvgDrawable r53 = DocumentObject.getSvgThumb(r14.set.thumbs, Theme.key_windowBackgroundGray, 1.0f);
        boolean r4 = r22 instanceof TLRPC.Document;
        if (r4 == false) goto L42;
        ImageLocation r23 = ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r9.thumbs, 90), r9);
    L44:
        if (this.emojis == false) goto L46;
        int r35 = LiteMode.FLAG_ANIMATED_EMOJI_KEYBOARD;
    L48:
        if (LiteMode.isEnabled(r35) == true) goto L50;
        String r36 = "_firstframe";
    L51:
        String r37 = "50_50".concat(r36);
        if (r4 == false) goto L66;
        if (MessageObject.isAnimatedStickerDocument(r9, true) == false) goto L56;
    L59:
        BackupImageView r112 = this.imageView;
        if (r53 == null) goto L62;
        r112.setImage(ImageLocation.getForDocument(r9), r37, r53, 0, r14);
    L64:
        if (MessageObject.isTextColorEmoji(r9) == false) goto L78;
        this.imageView.setColorFilter(Theme.getAnimatedEmojiColorFilter(null));
        goto L78
    L62:
        r112.setImage(ImageLocation.getForDocument(r9), r37, r23, null, 0, r14);
        goto L64
    L56:
        if (MessageObject.isVideoSticker(r9) == true) goto L59;
    L66:
        if (r23 != null) goto L68;
    L70:
        this.imageView.setImage(r23, r37, "webp", r53, r14);
        goto L78
    L68:
        if (r23.imageType != 1) goto L70;
        this.imageView.setImage(r23, r37, "tgs", r53, r14);
        goto L78
    L50:
        r36 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L51
    L46:
        r35 = 1;
        goto L48
    L42:
        r23 = ImageLocation.getForSticker((TLRPC.PhotoSize) r22, r9, r14.set.thumb_version);
    L32:
        r34 = r34 + 1;
        goto L26
    L33:
        r52 = null;
        goto L34
    L12:
        r17 = 8;
        goto L13
    L8:
        r3.setAlpha(1.0f);
        this.valueTextView.setAlpha(1.0f);
        this.imageView.setAlpha(1.0f);
        goto L9
    }

    public void updateButtonState(int r24, boolean r25) {
        AnimatorSet r2 = this.stateAnimator;
        if (r2 == null) goto L6;
        r2.cancel();
        this.stateAnimator = null;
    L6:
        if (r24 != 1) goto L8;
        this.premiumButtonView.setButton(LocaleController.getString(R.string.Unlock), new StickerSetCell$$ExternalSyntheticLambda0(this));
    L10:
        PremiumButtonView r4 = this.premiumButtonView;
        if (r24 == 1) goto L15;
        if (r24 == 2) goto L15;
        boolean r6 = false;
    L16:
        r4.setEnabled(r6);
        TextView r42 = this.addButtonView;
        if (r24 != 3) goto L19;
        boolean r7 = true;
    L20:
        r42.setEnabled(r7);
        TextView r43 = this.removeButtonView;
        if (r24 != 4) goto L23;
        boolean r8 = true;
    L24:
        r43.setEnabled(r8);
        if (r25 == false) goto L74;
        AnimatorSet r11 = new AnimatorSet();
        this.stateAnimator = r11;
        PremiumButtonView r12 = this.premiumButtonView;
        if (r24 == 1) goto L31;
        if (r24 == 2) goto L31;
        float r13 = 0.0f;
    L32:
        float[] r14 = {r13};
        Property r132 = FrameLayout.ALPHA;
        ObjectAnimator r122 = ObjectAnimator.ofFloat(r12, r132, r14);
        PremiumButtonView r142 = this.premiumButtonView;
        if (r24 == 1) goto L38;
        if (r24 == 2) goto L38;
        float r15 = 0.6f;
    L39:
        float[] r44 = {r15};
        Property r152 = FrameLayout.SCALE_X;
        ObjectAnimator r45 = ObjectAnimator.ofFloat(r142, r152, r44);
        PremiumButtonView r143 = this.premiumButtonView;
        if (r24 == 1) goto L45;
        if (r24 == 2) goto L45;
        float r17 = 0.6f;
    L46:
        Property r82 = FrameLayout.SCALE_Y;
        ObjectAnimator r5 = ObjectAnimator.ofFloat(r143, r82, new float[]{r17});
        TextView r144 = this.addButtonView;
        if (r24 != 3) goto L49;
        float r19 = 1.0f;
    L50:
        ObjectAnimator r9 = ObjectAnimator.ofFloat(r144, r132, new float[]{r19});
        TextView r145 = this.addButtonView;
        if (r24 != 3) goto L53;
        float r192 = 1.0f;
    L54:
        ObjectAnimator r10 = ObjectAnimator.ofFloat(r145, r152, new float[]{r192});
        TextView r146 = this.addButtonView;
        if (r24 != 3) goto L58;
        float r193 = 1.0f;
    L59:
        ObjectAnimator r62 = ObjectAnimator.ofFloat(r146, r82, new float[]{r193});
        TextView r147 = this.removeButtonView;
        if (r24 != 4) goto L63;
        float r172 = 1.0f;
    L64:
        ObjectAnimator r22 = ObjectAnimator.ofFloat(r147, r132, new float[]{r172});
        TextView r133 = this.removeButtonView;
        if (r24 != 4) goto L67;
        float r148 = 1.0f;
    L68:
        ObjectAnimator r72 = ObjectAnimator.ofFloat(r133, r152, new float[]{r148});
        TextView r134 = this.removeButtonView;
        if (r24 != 4) goto L71;
        float r20 = 1.0f;
    L72:
        r11.playTogether(new Animator[]{r122, r45, r5, r9, r10, r62, r22, r72, ObjectAnimator.ofFloat(r134, r82, new float[]{r20})});
        this.stateAnimator.addListener(new AnonymousClass4(this, r24));
        this.stateAnimator.setDuration(250);
        this.stateAnimator.setInterpolator(new OvershootInterpolator(1.02f));
        this.stateAnimator.start();
        return;
    L71:
        r20 = 0.6f;
        goto L72
    L67:
        r148 = 0.6f;
        goto L68
    L63:
        r172 = 0.0f;
        goto L64
    L58:
        r193 = 0.6f;
        goto L59
    L53:
        r192 = 0.6f;
        goto L54
    L49:
        r19 = 0.0f;
    L45:
        r17 = 1.0f;
    L38:
        r15 = 1.0f;
    L31:
        r13 = 1.0f;
        goto L32
    L74:
        PremiumButtonView r23 = this.premiumButtonView;
        if (r24 == 1) goto L79;
        if (r24 == 2) goto L79;
        float r52 = 0.0f;
    L80:
        r23.setAlpha(r52);
        PremiumButtonView r26 = this.premiumButtonView;
        if (r24 == 1) goto L85;
        if (r24 == 2) goto L85;
        float r53 = 0.6f;
    L86:
        r26.setScaleX(r53);
        PremiumButtonView r27 = this.premiumButtonView;
        if (r24 == 1) goto L91;
        if (r24 == 2) goto L91;
        float r54 = 0.6f;
    L92:
        r27.setScaleY(r54);
        PremiumButtonView r28 = this.premiumButtonView;
        if (r24 == 1) goto L97;
        if (r24 == 2) goto L97;
        int r3 = 8;
    L98:
        r28.setVisibility(r3);
        TextView r29 = this.addButtonView;
        if (r24 != 3) goto L101;
        float r46 = 1.0f;
    L102:
        r29.setAlpha(r46);
        TextView r210 = this.addButtonView;
        if (r24 != 3) goto L105;
        float r47 = 1.0f;
    L106:
        r210.setScaleX(r47);
        TextView r211 = this.addButtonView;
        if (r24 != 3) goto L109;
        float r48 = 1.0f;
    L110:
        r211.setScaleY(r48);
        TextView r212 = this.addButtonView;
        if (r24 != 3) goto L113;
        int r32 = 0;
    L114:
        r212.setVisibility(r32);
        TextView r213 = this.removeButtonView;
        if (r24 != 4) goto L117;
        float r83 = 1.0f;
    L118:
        r213.setAlpha(r83);
        TextView r214 = this.removeButtonView;
        if (r24 != 4) goto L121;
        float r33 = 1.0f;
    L122:
        r214.setScaleX(r33);
        TextView r215 = this.removeButtonView;
        if (r24 != 4) goto L125;
        float r92 = 1.0f;
    L126:
        r215.setScaleY(r92);
        TextView r216 = this.removeButtonView;
        if (r24 != 4) goto L129;
        int r55 = 0;
    L130:
        r216.setVisibility(r55);
        updateRightMargin();
        return;
    L129:
        r55 = 8;
        goto L130
    L125:
        r92 = 0.6f;
        goto L126
    L121:
        r33 = 0.6f;
        goto L122
    L117:
        r83 = 0.0f;
        goto L118
    L113:
        r32 = 8;
        goto L114
    L109:
        r48 = 0.6f;
        goto L110
    L105:
        r47 = 0.6f;
        goto L106
    L101:
        r46 = 0.0f;
    L97:
        r3 = 0;
    L91:
        r54 = 1.0f;
    L85:
        r53 = 1.0f;
    L79:
        r52 = 1.0f;
        goto L80
    L23:
        r8 = false;
        goto L24
    L19:
        r7 = false;
    L15:
        r6 = true;
        goto L16
    L8:
        if (r24 != 2) goto L10;
        this.premiumButtonView.setButton(LocaleController.getString(R.string.Restore), new StickerSetCell$$ExternalSyntheticLambda1(this));
        goto L10
    }

    public void updateRightMargin() {
        this.sideButtons.measure(View.MeasureSpec.makeMeasureSpec(999999, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(58.0f), TLObject.FLAG_30));
        int r0 = AndroidUtilities.dp(26.0f) + this.sideButtons.getMeasuredWidth();
        boolean r1 = LocaleController.isRTL;
        TextView r2 = this.textView;
        if (r1 == false) goto L6;
        ((ViewGroup.MarginLayoutParams) r2.getLayoutParams()).leftMargin = r0;
        ((ViewGroup.MarginLayoutParams) this.valueTextView.getLayoutParams()).leftMargin = r0;
        return;
    L6:
        ((ViewGroup.MarginLayoutParams) r2.getLayoutParams()).rightMargin = r0;
        ((ViewGroup.MarginLayoutParams) this.valueTextView.getLayoutParams()).rightMargin = r0;
    }
}
