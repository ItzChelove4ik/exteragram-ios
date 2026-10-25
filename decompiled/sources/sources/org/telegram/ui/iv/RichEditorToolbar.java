package org.telegram.ui.iv;

import android.content.Context;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AiButtonDrawable;
import org.telegram.ui.Components.ChatActivityEnterView;
import org.telegram.ui.Components.ChatActivityEnterViewAnimatedIconView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.iv.RichEditor;

/* loaded from: classes7.dex */
public class RichEditorToolbar extends FrameLayout {
    private final ImageView addButton;
    private final ImageView aiButton;
    private final RichEditor.Button aiStyleButton;
    private final ImageView backButton;
    private final ArrayList<RichEditor.Button> blockButtons;
    private final LinearLayout blocksLayout;
    private final HorizontalScrollView blocksScrollView;
    private final FrameLayout bottomContainer;
    private final View bottomGradient;
    private final FrameLayout bottomInnerContainer;
    private final LinearLayout bottomPanel;
    private final RichEditor.Button dateButton;
    private final Delegate delegate;
    private final ChatActivityEnterViewAnimatedIconView emojiButton;
    private final ArrayList<RichEditor.Button> formattingButtons;
    private LinearLayout formattingLayout1;
    private LinearLayout formattingLayout2;
    private LinearLayout formattingLayout3;
    private final LinearLayout formattingPanel;
    private final LinearLayout formattingPanelLayout;
    private int formattingScrollMaxWidth;
    private HorizontalScrollView formattingScrollView;
    private final LinearLayout historyButtons;
    private final RichEditor.Button inlineButton;
    private final RichEditor.Button linkButton;
    private final RichEditor.Button mathButton;
    private int panelType;
    private final ArrayList<RichEditor.Button> premiumButtons;
    private final RichEditor.Button quoteButton;
    private final ImageView redoButton;
    private int reorderSavedPanelType;
    private final Theme.ResourcesProvider resourcesProvider;
    private final ChatActivityEnterView.SendButton sendButton;
    private boolean sendLoading;
    private final View topGradient;
    private final FrameLayout topPanel;
    private boolean trashHovered;
    private final FrameLayout trashPanel;
    private final RLottieImageView trashPanelIcon;
    private final ImageView undoButton;

    /* renamed from: org.telegram.ui.iv.RichEditorToolbar$1, reason: invalid class name */
    public class AnonymousClass1 extends HorizontalScrollView {
        final /* synthetic */ RichEditorToolbar this$0;

        public AnonymousClass1(RichEditorToolbar r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            int r0 = View.MeasureSpec.getMode(r4);
            int r1 = View.MeasureSpec.getSize(r4);
            if (r0 != 1073741824) goto L6;
            super.onMeasure(r4, r5);
            return;
        L6:
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(r1, 0), r5);
            int r42 = getMeasuredWidth();
            if (r0 != Integer.MIN_VALUE) goto L9;
            r42 = Math.min(r42, r1);
        L9:
            setMeasuredDimension(r42, getMeasuredHeight());
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditorToolbar$2, reason: invalid class name */
    public class AnonymousClass2 extends ViewOutlineProvider {
        final /* synthetic */ RichEditorToolbar this$0;

        public AnonymousClass2(RichEditorToolbar r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r7, Outline r8) {
            r8.setRoundRect(0, 0, r7.getWidth(), r7.getHeight(), AndroidUtilities.dp(22.0f));
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditorToolbar$3, reason: invalid class name */
    public class AnonymousClass3 extends LinearLayout {
        final /* synthetic */ RichEditorToolbar this$0;

        public AnonymousClass3(RichEditorToolbar r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r9, int r10) {
            int r0 = View.MeasureSpec.getSize(r9);
            int r1 = getPaddingLeft() + getPaddingRight();
            if (RichEditorToolbar.m21862$$Nest$fgetformattingLayout1(this.this$0) == null) goto L8;
            if (RichEditorToolbar.m21862$$Nest$fgetformattingLayout1(this.this$0).getVisibility() != 0) goto L8;
            RichEditorToolbar.m21862$$Nest$fgetformattingLayout1(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r0, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(44.0f), TLObject.FLAG_30));
            ViewGroup.MarginLayoutParams r2 = (ViewGroup.MarginLayoutParams) RichEditorToolbar.m21862$$Nest$fgetformattingLayout1(this.this$0).getLayoutParams();
            r1 = r1 + ((RichEditorToolbar.m21862$$Nest$fgetformattingLayout1(this.this$0).getMeasuredWidth() + r2.leftMargin) + r2.rightMargin);
        L8:
            if (RichEditorToolbar.m21863$$Nest$fgetformattingLayout2(this.this$0) == null) goto L11;
            RichEditorToolbar.m21863$$Nest$fgetformattingLayout2(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r0, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(44.0f), TLObject.FLAG_30));
            ViewGroup.MarginLayoutParams r22 = (ViewGroup.MarginLayoutParams) RichEditorToolbar.m21863$$Nest$fgetformattingLayout2(this.this$0).getLayoutParams();
            r1 = r1 + ((RichEditorToolbar.m21863$$Nest$fgetformattingLayout2(this.this$0).getMeasuredWidth() + r22.leftMargin) + r22.rightMargin);
        L11:
            if (RichEditorToolbar.m21864$$Nest$fgetformattingLayout3(this.this$0) == null) goto L13;
            RichEditorToolbar.m21864$$Nest$fgetformattingLayout3(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r0, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(44.0f), TLObject.FLAG_30));
            ViewGroup.MarginLayoutParams r23 = (ViewGroup.MarginLayoutParams) RichEditorToolbar.m21864$$Nest$fgetformattingLayout3(this.this$0).getLayoutParams();
            r1 = r1 + ((RichEditorToolbar.m21864$$Nest$fgetformattingLayout3(this.this$0).getMeasuredWidth() + r23.leftMargin) + r23.rightMargin);
        L13:
            RichEditorToolbar.m21867$$Nest$fputformattingScrollMaxWidth(this.this$0, Math.max(0, r0 - r1));
            super.onMeasure(r9, r10);
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditorToolbar$4, reason: invalid class name */
    public class AnonymousClass4 extends HorizontalScrollView {
        final /* synthetic */ RichEditorToolbar this$0;

        public AnonymousClass4(RichEditorToolbar r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            int r0 = View.MeasureSpec.getMode(r4);
            if (r0 != 1073741824) goto L6;
            super.onMeasure(r4, r5);
            return;
        L6:
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r4), 0), r5);
            int r52 = getMeasuredWidth();
            int r1 = RichEditorToolbar.m21865$$Nest$fgetformattingScrollMaxWidth(this.this$0);
            if (r0 != Integer.MIN_VALUE) goto L9;
            r1 = Math.min(r1, View.MeasureSpec.getSize(r4));
        L9:
            setMeasuredDimension(Math.min(r52, r1), getMeasuredHeight());
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditorToolbar$5, reason: invalid class name */
    public class AnonymousClass5 extends ViewOutlineProvider {
        final /* synthetic */ RichEditorToolbar this$0;

        public AnonymousClass5(RichEditorToolbar r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r7, Outline r8) {
            r8.setRoundRect(0, 0, r7.getWidth(), r7.getHeight(), AndroidUtilities.dp(22.0f));
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditorToolbar$6, reason: invalid class name */
    public class AnonymousClass6 extends ChatActivityEnterView.SendButton {
        final /* synthetic */ RichEditorToolbar this$0;

        public AnonymousClass6(RichEditorToolbar r1, Context r2, int r3, Theme.ResourcesProvider r4, boolean r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.SendButton
        public boolean isOpen() {
            if (RichEditorToolbar.m21866$$Nest$fgetsendLoading(this.this$0) == false) goto L5;
            return true;
        L5:
            if (super.isOpen() == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    public interface Delegate {
        Theme.ResourcesProvider getResourcesProvider();

        void onAi();

        void onAiStyle();

        void onAttach();

        void onBack();

        void onBlockButton(int r1, View r2);

        void onButton(View r1);

        void onDate();

        void onEmoji();

        void onFormatting(int r1);

        void onLink();

        void onMath();

        void onQuote();

        void onRedo();

        void onSend();

        boolean onSendLongClick(View r1);

        void onUndo();
    }

    /* renamed from: $r8$lambda$-ALKsMAxFgekQuWLQ8GM66_FxzY, reason: not valid java name */
    public static /* synthetic */ void m21855$r8$lambda$ALKsMAxFgekQuWLQ8GM66_FxzY(Delegate r0, View r1) {
        r0.onSend();
    }

    /* renamed from: $r8$lambda$-eq6wqIw7PIZHKALL9YozMLyU9Y, reason: not valid java name */
    public static /* synthetic */ void m21856$r8$lambda$eq6wqIw7PIZHKALL9YozMLyU9Y(Delegate r0, View r1) {
        r0.onAi();
    }

    /* renamed from: $r8$lambda$-yW1WXc_v-tlIPSRxqveBtOuSq8, reason: not valid java name */
    public static /* synthetic */ void m21857$r8$lambda$yW1WXc_vtlIPSRxqveBtOuSq8(Delegate r0, View r1) {
        r0.onEmoji();
    }

    /* renamed from: $r8$lambda$0nX-LPTEGxFkQCQxBHGFyvC9QRc, reason: not valid java name */
    public static /* synthetic */ void m21858$r8$lambda$0nXLPTEGxFkQCQxBHGFyvC9QRc(Delegate r0, View r1) {
        r0.onUndo();
    }

    public static /* synthetic */ void $r8$lambda$1M0r1UjvrMCtyKma1TccxrQfZ3E(Delegate r0, View r1) {
        r0.onAiStyle();
    }

    /* renamed from: $r8$lambda$2T4uriMvRK2COfs-NmbwWxYBMG8, reason: not valid java name */
    public static /* synthetic */ void m21859$r8$lambda$2T4uriMvRK2COfsNmbwWxYBMG8(RichEditorToolbar r0) {
        r0.lambda$updatePanel$17();
    }

    public static /* synthetic */ void $r8$lambda$G4Z5MJn_bEwvrc9YiwlRaiE2qIc(Delegate r0, View r1) {
        r0.onLink();
    }

    public static /* synthetic */ void $r8$lambda$LIsQqAwH0V9YBGuFIwujYxeRJl8(Delegate r0, View r1) {
        r0.onMath();
    }

    public static /* synthetic */ void $r8$lambda$M5_zaZScZy3D_pEuYKmCxkmpdFc(Delegate r0, View r1) {
        r0.onRedo();
    }

    public static /* synthetic */ void $r8$lambda$Mipl4aRiA0dJuCrJx_czXkV_dmY(RichEditorToolbar r0) {
        r0.lambda$updatePanel$16();
    }

    public static /* synthetic */ void $r8$lambda$PDa6r0BnuIMO0jpzMRsOhQU6rjI(RichEditorToolbar r0, int r1, View r2) {
        r0.lambda$addFormattingButton$15(r1, r2);
    }

    /* renamed from: $r8$lambda$TNcHc2haGgm6i-LKI51qSeaEnuw, reason: not valid java name */
    public static /* synthetic */ void m21860$r8$lambda$TNcHc2haGgm6iLKI51qSeaEnuw(Delegate r0, View r1) {
        r0.onDate();
    }

    public static /* synthetic */ void $r8$lambda$UmwLMnP0k07rBNrogZCeA6Rj42s(Delegate r0, View r1) {
        r0.onButton(r1);
    }

    public static /* synthetic */ void $r8$lambda$YiBqkR23k5oZtq6_KKqkxPp_tpw(RichEditorToolbar r0) {
        r0.lambda$updatePanel$18();
    }

    public static /* synthetic */ boolean $r8$lambda$Ysf8jjMV2jnfxmYh0olBN7290FE(Delegate r0, View r1) {
        return r0.onSendLongClick(r1);
    }

    public static /* synthetic */ void $r8$lambda$bAu_0EUU50jh2B8Lzp17oQKtLWU(Delegate r0, View r1) {
        r0.onAttach();
    }

    /* renamed from: $r8$lambda$f2DynZezAiJO2gTioq3N-lVDv3Q, reason: not valid java name */
    public static /* synthetic */ void m21861$r8$lambda$f2DynZezAiJO2gTioq3NlVDv3Q(Delegate r0, View r1) {
        r0.onBack();
    }

    public static /* synthetic */ void $r8$lambda$nEYunK4wtteRhu8S4wPK7EuyUFM(Delegate r0, View r1) {
        r0.onQuote();
    }

    public static /* synthetic */ void $r8$lambda$uTl3Xq7YaIMK7IgpZxzwjV6wTRU(RichEditorToolbar r0, int r1, View r2) {
        r0.lambda$addBlockButton$14(r1, r2);
    }

    /* renamed from: -$$Nest$fgetformattingLayout1, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m21862$$Nest$fgetformattingLayout1(RichEditorToolbar r0) {
        return r0.formattingLayout1;
    }

    /* renamed from: -$$Nest$fgetformattingLayout2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m21863$$Nest$fgetformattingLayout2(RichEditorToolbar r0) {
        return r0.formattingLayout2;
    }

    /* renamed from: -$$Nest$fgetformattingLayout3, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m21864$$Nest$fgetformattingLayout3(RichEditorToolbar r0) {
        return r0.formattingLayout3;
    }

    /* renamed from: -$$Nest$fgetformattingScrollMaxWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21865$$Nest$fgetformattingScrollMaxWidth(RichEditorToolbar r0) {
        return r0.formattingScrollMaxWidth;
    }

    /* renamed from: -$$Nest$fgetsendLoading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21866$$Nest$fgetsendLoading(RichEditorToolbar r0) {
        return r0.sendLoading;
    }

    /* renamed from: -$$Nest$fputformattingScrollMaxWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21867$$Nest$fputformattingScrollMaxWidth(RichEditorToolbar r0, int r1) {
        r0.formattingScrollMaxWidth = r1;
    }

    public RichEditorToolbar(Context r39, Delegate r40) {
        super(r39);
        this.formattingScrollMaxWidth = Integer.MAX_VALUE;
        this.blockButtons = new ArrayList();
        this.formattingButtons = new ArrayList();
        ArrayList<RichEditor.Button> r0 = new ArrayList();
        this.premiumButtons = r0;
        this.panelType = -1;
        this.reorderSavedPanelType = 0;
        this.delegate = r40;
        Theme.ResourcesProvider r4 = r40.getResourcesProvider();
        this.resourcesProvider = r4;
        setClipChildren(false);
        setClipToPadding(false);
        View r5 = new View(r39);
        this.topGradient = r5;
        GradientDrawable.Orientation r9 = GradientDrawable.Orientation.TOP_BOTTOM;
        int r10 = Theme.key_windowBackgroundWhite;
        r5.setBackground(new GradientDrawable(r9, new int[]{color(r10), Theme.multAlpha(color(r10), 0.0f)}));
        addView(r5, LayoutHelper.createFrame(-1, 68, 55));
        View r52 = new View(r39);
        this.bottomGradient = r52;
        r52.setBackground(new GradientDrawable(r9, new int[]{Theme.multAlpha(color(r10), 0.0f), color(r10)}));
        addView(r52, LayoutHelper.createFrame(-1, 68, 87));
        FrameLayout r53 = new FrameLayout(r39);
        this.topPanel = r53;
        r53.setClipChildren(false);
        r53.setClipToPadding(false);
        addView(r53, LayoutHelper.createFrame(-1, -1, 55));
        ImageView r8 = new ImageView(r39);
        this.backButton = r8;
        r8.setImageResource(R.drawable.ic_ab_back);
        ImageView.ScaleType r11 = ImageView.ScaleType.CENTER;
        r8.setScaleType(r11);
        int r12 = Theme.key_glass_targetMainTabs;
        int r13 = color(r12);
        int r14 = color(r12);
        int r15 = Theme.key_listSelector;
        r8.setBackground(RichEditor.withShadow(Theme.createRadSelectorDrawable(r13, Theme.blendOver(r14, color(r15)), AndroidUtilities.dp(22.0f), AndroidUtilities.dp(22.0f))));
        int r92 = Theme.key_windowBackgroundWhiteBlackText;
        int r132 = color(r92);
        PorterDuff.Mode r142 = PorterDuff.Mode.SRC_IN;
        r8.setColorFilter(new PorterDuffColorFilter(r132, r142));
        ScaleStateListAnimator.apply(r8);
        r8.setContentDescription(LocaleController.getString(R.string.AccDescrGoBack));
        r8.setOnClickListener(new RichEditorToolbar$$ExternalSyntheticLambda0(r40));
        r53.addView(r8, LayoutHelper.createFrame(44, 44.0f, 51, 8.0f, 8.0f, 8.0f, 8.0f));
        LinearLayout r3 = new LinearLayout(r39);
        this.historyButtons = r3;
        r3.setOrientation(0);
        r3.setBackground(RichEditor.withShadow(Theme.createRoundRectDrawable(AndroidUtilities.dp(22.0f), color(r12))));
        r53.addView(r3, LayoutHelper.createFrame(82, 44.0f, 53, 8.0f, 8.0f, 8.0f, 8.0f));
        ImageView r54 = new ImageView(r39);
        this.undoButton = r54;
        r54.setImageResource(R.drawable.iv_undo);
        r54.setScaleType(r11);
        r54.setBackground(Theme.createSelectorDrawable(color(r15)));
        r54.setColorFilter(new PorterDuffColorFilter(color(r92), r142));
        ScaleStateListAnimator.apply(r54);
        r54.setContentDescription("Undo");
        r54.setOnClickListener(new RichEditorToolbar$$ExternalSyntheticLambda5(r40));
        r3.addView(r54, LayoutHelper.createLinear(41, 41, 16));
        ImageView r55 = new ImageView(r39);
        this.redoButton = r55;
        r55.setImageResource(R.drawable.iv_redo);
        r55.setScaleType(r11);
        r55.setBackground(Theme.createSelectorDrawable(color(r15)));
        r55.setColorFilter(new PorterDuffColorFilter(color(r92), r142));
        ScaleStateListAnimator.apply(r55);
        r55.setContentDescription("Redo");
        r55.setOnClickListener(new RichEditorToolbar$$ExternalSyntheticLambda6(r40));
        r3.addView(r55, LayoutHelper.createLinear(41, 41, 16));
        FrameLayout r32 = new FrameLayout(r39);
        this.bottomContainer = r32;
        r32.setClipChildren(false);
        r32.setClipToPadding(false);
        addView(r32, LayoutHelper.createFrame(-1, -1, 87));
        FrameLayout r133 = new FrameLayout(r39);
        this.bottomInnerContainer = r133;
        r133.setClipChildren(false);
        r133.setClipToPadding(false);
        r32.addView(r133, LayoutHelper.createFrame(-1, 60, 87));
        LinearLayout r7 = new LinearLayout(r39);
        this.bottomPanel = r7;
        r7.setClipToPadding(false);
        r7.setClipChildren(false);
        r7.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        r133.addView(r7, LayoutHelper.createFrame(-1, 60, 87));
        ImageView r02 = new ImageView(r39);
        this.aiButton = r02;
        r02.setImageDrawable(new AiButtonDrawable(r39));
        r02.setScaleType(r11);
        r02.setColorFilter(new PorterDuffColorFilter(color(r92), r142));
        r02.setBackground(RichEditor.withShadow(Theme.createRadSelectorDrawable(color(r12), Theme.blendOver(color(r12), color(r15)), AndroidUtilities.dp(22.0f), AndroidUtilities.dp(22.0f))));
        if (ExteraConfig.getTelegramAiEditor() == false) goto L5;
        int r42 = 0;
    L6:
        r02.setVisibility(r42);
        r7.addView(r02, LayoutHelper.createLinear(44, 44, 0.0f, 19, 0, 0, 8, 0));
        ScaleStateListAnimator.apply(r02);
        r02.setContentDescription("AI");
        r02.setOnClickListener(new RichEditorToolbar$$ExternalSyntheticLambda7(r40));
        FrameLayout r03 = new FrameLayout(r39);
        r03.setClipToPadding(false);
        r03.setClipChildren(false);
        FrameLayout r43 = new FrameLayout(r39);
        r43.setBackground(RichEditor.withShadow(Theme.createRoundRectDrawable(AndroidUtilities.dp(22.0f), color(r10))));
        r03.addView(r43, LayoutHelper.createFrame(-2, 44, 81));
        AnonymousClass1 r122 = new AnonymousClass1(this, r39);
        this.blocksScrollView = r122;
        r122.setClipToOutline(true);
        r122.setOutlineProvider(new AnonymousClass2(this));
        LinearLayout r82 = new LinearLayout(r39);
        this.blocksLayout = r82;
        r82.setPadding(AndroidUtilities.dp(2.0f), 0, AndroidUtilities.dp(2.0f), 0);
        r82.setOrientation(0);
        r122.addView(r82);
        r43.addView(r122, LayoutHelper.createFrame(-1, -1.0f));
        ChatActivityEnterViewAnimatedIconView r44 = new ChatActivityEnterViewAnimatedIconView(r39, 24);
        this.emojiButton = r44;
        r44.setPadding(AndroidUtilities.dp(7.0f), AndroidUtilities.dp(7.0f), AndroidUtilities.dp(7.0f), AndroidUtilities.dp(7.0f));
        r44.setColorFilter(new PorterDuffColorFilter(color(r92), r142));
        r44.setBackground(Theme.createRadSelectorDrawable(color(r10), color(r15), AndroidUtilities.dp(20.0f), AndroidUtilities.dp(20.0f)));
        r44.setState(ChatActivityEnterViewAnimatedIconView.State.SMILE, false);
        r82.addView(r44, LayoutHelper.createLinear(38, 38, 16));
        ScaleStateListAnimator.apply(r44);
        r44.setContentDescription("Emoji");
        r44.setOnClickListener(new RichEditorToolbar$$ExternalSyntheticLambda8(r40));
        addBlockButton(R.drawable.iv_text, 1, false);
        addBlockButton(R.drawable.iv_lists, 2, true);
        addBlockButton(R.drawable.iv_table, 4, true);
        addBlockButton(R.drawable.iv_math, 7, true);
        ImageView r33 = new ImageView(r39);
        this.addButton = r33;
        r33.setImageResource(R.drawable.outline_poll_attach_24);
        r33.setScaleType(r11);
        r33.setColorFilter(new PorterDuffColorFilter(color(r92), r142));
        r33.setBackground(Theme.createRadSelectorDrawable(color(r10), color(r15), AndroidUtilities.dp(20.0f), AndroidUtilities.dp(20.0f)));
        r82.addView(r33, LayoutHelper.createLinear(38, 38, 16, 2, 0, 0, 0));
        ScaleStateListAnimator.apply(r33);
        r33.setContentDescription("Attach");
        r33.setOnClickListener(new RichEditorToolbar$$ExternalSyntheticLambda9(r40));
        r7.addView(r03, LayoutHelper.createLinear(0, 44, 1.0f));
        AnonymousClass3 r04 = new AnonymousClass3(this, r39);
        this.formattingPanel = r04;
        r04.setOrientation(0);
        r04.setClipToPadding(false);
        r04.setClipChildren(false);
        r04.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        r32.addView(r04, LayoutHelper.createFrame(-2, 60, 81));
        FrameLayout r45 = new FrameLayout(r39);
        this.trashPanel = r45;
        r45.setClipChildren(false);
        r45.setClipToPadding(false);
        r45.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        r32.addView(r45, LayoutHelper.createFrame(80, 60, 81));
        RLottieImageView r34 = new RLottieImageView(r39);
        this.trashPanelIcon = r34;
        r34.setAnimation(R.raw.group_pip_delete_icon, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        RLottieDrawable r83 = r34.getAnimatedDrawable();
        if (r83 == null) goto L9;
        r83.setPlayInDirectionOfCustomEndFrame(true);
        r83.setAutoRepeat(0);
        r83.setCustomEndFrame(0);
    L9:
        r34.setScaleType(r11);
        r34.setColorFilter(new PorterDuffColorFilter(color(r92), r142));
        r34.setBackground(RichEditor.withShadow(Theme.createRoundRectDrawable(AndroidUtilities.dp(22.0f), color(r12))));
        r45.addView(r34, LayoutHelper.createFrame(-1, -1, 119));
        FrameLayout r35 = new FrameLayout(r39);
        r35.setBackground(RichEditor.withShadow(Theme.createRoundRectDrawable(AndroidUtilities.dp(22.0f), color(r12))));
        r04.addView(r35, LayoutHelper.createFrame(-2, 44.0f));
        AnonymousClass4 r46 = new AnonymousClass4(this, r39);
        this.formattingScrollView = r46;
        r46.setHorizontalScrollBarEnabled(false);
        this.formattingScrollView.setClipToOutline(true);
        this.formattingScrollView.setOutlineProvider(new AnonymousClass5(this));
        r35.addView(this.formattingScrollView, LayoutHelper.createFrame(-1, -1.0f));
        LinearLayout r36 = new LinearLayout(r39);
        this.formattingPanelLayout = r36;
        r36.setOrientation(0);
        r36.setPadding(AndroidUtilities.dp(2.0f), 0, AndroidUtilities.dp(2.0f), 0);
        this.formattingScrollView.addView(r36, new FrameLayout.LayoutParams(-2, -1));
        addFormattingButton(R.drawable.formatting_bold, 1);
        addFormattingButton(R.drawable.formatting_italic, 2);
        addFormattingButton(R.drawable.formatting_underline, 16);
        int r84 = 8;
        addFormattingButton(R.drawable.formatting_strikethrough, 8);
        addFormattingButton(R.drawable.formatting_spoiler, 256);
        addFormattingButton(R.drawable.iv_code, 4);
        addFormattingButton(R.drawable.iv_sub, 16384, true);
        addFormattingButton(R.drawable.iv_super, 32768, true);
        RichEditor.Button r47 = new RichEditor.Button(r39, R.drawable.iv_quote, r4);
        this.quoteButton = r47;
        r47.setBackgroundColorKey(r12);
        r47.setContentDescription(LocaleController.getString(R.string.Quote));
        r47.setOnClickListener(new RichEditorToolbar$$ExternalSyntheticLambda10(r40));
        if (r36.getChildCount() != 0) goto L12;
        int r342 = 0;
    L13:
        r36.addView(r47, LayoutHelper.createLinear(38, 38, 16, r342, 0, 0, 0));
        RichEditor.Button r48 = new RichEditor.Button(r39, R.drawable.iv_button, r4);
        this.inlineButton = r48;
        r48.setBackgroundColorKey(r12);
        r48.setContentDescription(LocaleController.getString(R.string.RichEditorButton));
        r48.setOnClickListener(new RichEditorToolbar$$ExternalSyntheticLambda11(r40));
        if (r36.getChildCount() != 0) goto L16;
        int r343 = 0;
    L17:
        r36.addView(r48, LayoutHelper.createLinear(38, 38, 16, r343, 0, 0, 0));
        LinearLayout r37 = new LinearLayout(r39);
        this.formattingLayout2 = r37;
        r37.setOrientation(0);
        this.formattingLayout2.setPadding(AndroidUtilities.dp(2.0f), 0, AndroidUtilities.dp(2.0f), 0);
        this.formattingLayout2.setBackground(RichEditor.withShadow(Theme.createRoundRectDrawable(AndroidUtilities.dp(22.0f), color(r12))));
        r04.addView(this.formattingLayout2, LayoutHelper.createFrame(-2, 44.0f, 80, 8.0f, 0.0f, 0.0f, 0.0f));
        RichEditor.Button r38 = new RichEditor.Button(r39, R.drawable.media_link_24, r4);
        this.linkButton = r38;
        r38.setBackgroundColorKey(r12);
        r38.setContentDescription(LocaleController.getString(R.string.CreateLink));
        r38.setOnClickListener(new RichEditorToolbar$$ExternalSyntheticLambda12(r40));
        this.formattingLayout2.addView(r38, LayoutHelper.createLinear(38, 38, 16));
        RichEditor.Button r310 = new RichEditor.Button(r39, R.drawable.msg_calendar2, r4);
        this.dateButton = r310;
        r310.setBackgroundColorKey(r12);
        r310.setContentDescription(LocaleController.getString(R.string.AccDescrIVInsertDate));
        r310.setOnClickListener(new RichEditorToolbar$$ExternalSyntheticLambda13(r40));
        this.formattingLayout2.addView(r310, LayoutHelper.createLinear(38, 38, 16));
        LinearLayout r311 = new LinearLayout(r39);
        this.formattingLayout3 = r311;
        r311.setOrientation(0);
        this.formattingLayout3.setPadding(AndroidUtilities.dp(2.0f), 0, AndroidUtilities.dp(2.0f), 0);
        this.formattingLayout3.setBackground(RichEditor.withShadow(Theme.createRoundRectDrawable(AndroidUtilities.dp(22.0f), color(r12))));
        r04.addView(this.formattingLayout3, LayoutHelper.createFrame(-2, 44.0f, 80, 8.0f, 0.0f, 0.0f, 0.0f));
        RichEditor.Button r312 = new RichEditor.Button(r39, R.drawable.iv_math, r4);
        this.mathButton = r312;
        r312.setBackgroundColorKey(r12);
        r312.setPremium();
        r0.add(r312);
        r312.setContentDescription(LocaleController.getString(R.string.AccDescrIVFormula));
        r312.setOnClickListener(new RichEditorToolbar$$ExternalSyntheticLambda1(r40));
        this.formattingLayout3.addView(r312, LayoutHelper.createLinear(38, 38, 16));
        LinearLayout r313 = new LinearLayout(r39);
        this.formattingLayout1 = r313;
        r313.setOrientation(0);
        this.formattingLayout1.setPadding(AndroidUtilities.dp(2.0f), 0, AndroidUtilities.dp(2.0f), 0);
        this.formattingLayout1.setBackground(RichEditor.withShadow(Theme.createRoundRectDrawable(AndroidUtilities.dp(22.0f), color(r12))));
        LinearLayout r314 = this.formattingLayout1;
        if (ExteraConfig.getTelegramAiEditor() == false) goto L20;
        r84 = 0;
    L20:
        r314.setVisibility(r84);
        r04.addView(this.formattingLayout1, 0, LayoutHelper.createFrame(-2, 44.0f, 80, 0.0f, 0.0f, 8.0f, 0.0f));
        RichEditor.Button r05 = new RichEditor.Button(r39, R.drawable.input_ai, r4);
        this.aiStyleButton = r05;
        r05.setImageDrawable(new AiButtonDrawable(r39));
        r05.setBackgroundColorKey(r12);
        r05.setContentDescription(LocaleController.getString(R.string.AIEditor));
        r05.setOnClickListener(new RichEditorToolbar$$ExternalSyntheticLambda2(r40));
        this.formattingLayout1.addView(r05, LayoutHelper.createLinear(38, 38, 16));
        AnonymousClass6 r06 = new AnonymousClass6(this, r39, R.drawable.send_extera_24, r4, true);
        this.sendButton = r06;
        r06.centeredBackground = true;
        r06.setBackground(RichEditor.withShadow(Theme.createRoundRectDrawable(AndroidUtilities.dp(22.0f), color(Theme.key_chat_messagePanelSend))));
        ScaleStateListAnimator.apply(r06);
        r7.addView(r06, LayoutHelper.createLinear(44, 44, 0.0f, 5, 8, 0, 0, 0));
        r06.setContentDescription("Send");
        r06.setOnClickListener(new RichEditorToolbar$$ExternalSyntheticLambda3(r40));
        r06.setOnLongClickListener(new RichEditorToolbar$$ExternalSyntheticLambda4(r40));
        updatePanel(0, false);
        return;
    L16:
        r343 = 2;
        goto L17
    L12:
        r342 = 2;
        goto L13
    L5:
        r42 = 8;
        goto L6
    }

    private RichEditor.Button addBlockButton(int r9, int r10, boolean r11) {
        RichEditor.Button r0 = new RichEditor.Button(this.blocksLayout.getContext(), r9, this.resourcesProvider);
        r0.setBackgroundColorKey(Theme.key_windowBackgroundWhite);
        if (r11 == false) goto L5;
        r0.setPremium();
        this.premiumButtons.add(r0);
    L5:
        r0.setTag(Integer.valueOf(r10));
        r0.setContentDescription(RichEditor.blockButtonContentDescription(r10));
        r0.setOnClickListener(new RichEditorToolbar$$ExternalSyntheticLambda15(this, r10));
        this.blockButtons.add(r0);
        LinearLayout r8 = this.blocksLayout;
        if (r8.getChildCount() != 0) goto L9;
        int r92 = 0;
    L10:
        r8.addView(r0, LayoutHelper.createLinear(38, 38, 16, r92, 0, 0, 0));
        return r0;
    L9:
        r92 = 2;
        goto L10
    }

    private void addFormattingButton(int r2, int r3) {
        addFormattingButton(r2, r3, false);
    }

    private void addFormattingButton(int r9, int r10, boolean r11) {
        RichEditor.Button r0 = new RichEditor.Button(getContext(), r9, this.resourcesProvider);
        r0.setBackgroundColorKey(Theme.key_glass_targetMainTabs);
        if (r11 == false) goto L5;
        r0.setPremium();
        this.premiumButtons.add(r0);
    L5:
        r0.setTag(Integer.valueOf(r10));
        r0.setContentDescription(RichEditor.formattingButtonContentDescription(r10));
        r0.setOnClickListener(new RichEditorToolbar$$ExternalSyntheticLambda14(this, r10));
        this.formattingButtons.add(r0);
        LinearLayout r8 = this.formattingPanelLayout;
        if (r8.getChildCount() != 0) goto L9;
        int r92 = 0;
    L10:
        r8.addView(r0, LayoutHelper.createLinear(38, 38, 16, r92, 0, 0, 0));
        return;
    L9:
        r92 = 2;
        goto L10
    }

    private int color(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private boolean isOverTrash(float r3) {
        FrameLayout r2 = this.trashPanel;
        if (r2 != null) goto L5;
        return false;
    L5:
        r2.getLocationOnScreen(new int[2]);
        if (r3 < r1[1]) goto L8;
        return true;
    L8:
        return false;
    }

    private /* synthetic */ void lambda$addBlockButton$14(int r1, View r2) {
        this.delegate.onBlockButton(r1, r2);
    }

    private /* synthetic */ void lambda$addFormattingButton$15(int r1, View r2) {
        this.delegate.onFormatting(r1);
    }

    private /* synthetic */ void lambda$updatePanel$16() {
        if (this.panelType == 0) goto L6;
        this.bottomPanel.setVisibility(8);
        return;
    }

    private /* synthetic */ void lambda$updatePanel$17() {
        if (this.panelType == 1) goto L6;
        this.formattingPanel.setVisibility(8);
        return;
    }

    private /* synthetic */ void lambda$updatePanel$18() {
        if (this.panelType == 2) goto L6;
        this.trashPanel.setVisibility(8);
        return;
    }

    private void setTrashHovered(boolean r4, boolean r5) {
        if (this.trashHovered != r4) goto L6;
        if (r5 == false) goto L6;
        return;
    L6:
        this.trashHovered = r4;
        if (r4 == false) goto L9;
        float r0 = 1.15f;
    L10:
        RLottieImageView r1 = this.trashPanelIcon;
        if (r5 == false) goto L13;
        r1.animate().scaleX(r0).scaleY(r0).setDuration(180).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
    L14:
        RLottieImageView r52 = this.trashPanelIcon;
        if (r4 == false) goto L17;
        int r12 = Theme.key_text_RedBold;
    L18:
        r52.setColorFilter(new PorterDuffColorFilter(color(r12), PorterDuff.Mode.SRC_IN));
        RLottieDrawable r3 = this.trashPanelIcon.getAnimatedDrawable();
        if (r3 != null) goto L21;
        return;
    L21:
        if (r4 == true) goto L23;
        r3.setCustomEndFrame(0);
    L27:
        r3.start();
        return;
    L23:
        if (r3.getCurrentFrame() <= 34) goto L25;
        r3.setCurrentFrame(0, false);
    L25:
        r3.setCustomEndFrame(33);
        goto L27
    L17:
        r12 = Theme.key_windowBackgroundWhiteBlackText;
        goto L18
    L13:
        r1.animate().cancel();
        this.trashPanelIcon.setScaleX(r0);
        this.trashPanelIcon.setScaleY(r0);
        goto L14
    L9:
        r0 = 1.0f;
        goto L10
    }

    private void updatePanel(int r12, boolean r13) {
        if (this.panelType != r12) goto L5;
        return;
    L5:
        this.panelType = r12;
        LinearLayout r0 = this.bottomPanel;
        int r2 = 0;
        float r5 = 0.0f;
        float r6 = 0.8f;
        if (r13 == false) goto L52;
        r0.setVisibility(0);
        ViewPropertyAnimator r132 = this.bottomPanel.animate();
        if (r12 != 0) goto L10;
        float r02 = 1.0f;
    L11:
        ViewPropertyAnimator r133 = r132.alpha(r02);
        if (r12 != 0) goto L14;
        float r03 = 1.0f;
    L15:
        ViewPropertyAnimator r134 = r133.scaleX(r03);
        if (r12 != 0) goto L18;
        float r04 = 1.0f;
    L19:
        ViewPropertyAnimator r135 = r134.scaleY(r04);
        if (r12 != 0) goto L22;
        float r05 = 0.0f;
    L23:
        ViewPropertyAnimator r136 = r135.translationY(r05).setDuration(420);
        CubicBezierInterpolator r06 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r136.setInterpolator(r06).withEndAction(new RichEditorToolbar$$ExternalSyntheticLambda16(this)).start();
        this.formattingPanel.setVisibility(0);
        ViewPropertyAnimator r137 = this.formattingPanel.animate();
        if (r12 != 1) goto L26;
        float r10 = 1.0f;
    L27:
        ViewPropertyAnimator r138 = r137.alpha(r10);
        if (r12 != 1) goto L30;
        float r102 = 1.0f;
    L31:
        ViewPropertyAnimator r139 = r138.scaleX(r102);
        if (r12 != 1) goto L34;
        float r103 = 1.0f;
    L35:
        ViewPropertyAnimator r1310 = r139.scaleY(r103);
        if (r12 != 1) goto L38;
        float r1 = 0.0f;
    L39:
        r1310.translationY(r1).setDuration(420).setInterpolator(r06).withEndAction(new RichEditorToolbar$$ExternalSyntheticLambda17(this)).start();
        this.trashPanel.setVisibility(0);
        ViewPropertyAnimator r1311 = this.trashPanel.animate();
        if (r12 != 2) goto L42;
        r5 = 1.0f;
    L42:
        ViewPropertyAnimator r1312 = r1311.alpha(r5);
        if (r12 != 2) goto L45;
        float r14 = 1.0f;
    L46:
        ViewPropertyAnimator r1313 = r1312.scaleX(r14);
        if (r12 != 2) goto L49;
        r6 = 1.0f;
    L49:
        r1313.scaleY(r6).setDuration(420).setInterpolator(r06).withEndAction(new RichEditorToolbar$$ExternalSyntheticLambda18(this)).start();
        return;
    L45:
        r14 = 0.8f;
        goto L46
    L38:
        r1 = AndroidUtilities.dp(30.0f);
        goto L39
    L34:
        r103 = 0.8f;
        goto L35
    L30:
        r102 = 0.8f;
        goto L31
    L26:
        r10 = 0.0f;
        goto L27
    L22:
        r05 = AndroidUtilities.dp(30.0f);
        goto L23
    L18:
        r04 = 0.8f;
        goto L19
    L14:
        r03 = 0.8f;
        goto L15
    L10:
        r02 = 0.0f;
        goto L11
    L52:
        if (r12 != 0) goto L54;
        int r8 = 0;
    L55:
        r0.setVisibility(r8);
        LinearLayout r07 = this.bottomPanel;
        if (r12 != 0) goto L58;
        float r82 = 1.0f;
    L59:
        r07.setAlpha(r82);
        LinearLayout r08 = this.bottomPanel;
        if (r12 != 0) goto L62;
        float r83 = 1.0f;
    L63:
        r08.setScaleX(r83);
        LinearLayout r09 = this.bottomPanel;
        if (r12 != 0) goto L66;
        float r84 = 1.0f;
    L67:
        r09.setScaleY(r84);
        LinearLayout r010 = this.bottomPanel;
        if (r12 != 0) goto L70;
        float r85 = 0.0f;
    L71:
        r010.setTranslationY(r85);
        LinearLayout r011 = this.formattingPanel;
        if (r12 != 1) goto L74;
        int r86 = 0;
    L75:
        r011.setVisibility(r86);
        LinearLayout r012 = this.formattingPanel;
        if (r12 != 1) goto L78;
        float r87 = 1.0f;
    L79:
        r012.setAlpha(r87);
        LinearLayout r013 = this.formattingPanel;
        if (r12 != 1) goto L82;
        float r88 = 1.0f;
    L83:
        r013.setScaleX(r88);
        LinearLayout r014 = this.formattingPanel;
        if (r12 != 1) goto L86;
        float r89 = 1.0f;
    L87:
        r014.setScaleY(r89);
        LinearLayout r015 = this.formattingPanel;
        if (r12 != 1) goto L90;
        float r15 = 0.0f;
    L91:
        r015.setTranslationY(r15);
        FrameLayout r016 = this.trashPanel;
        if (r12 == 2) goto L95;
        r2 = 8;
    L95:
        r016.setVisibility(r2);
        FrameLayout r1314 = this.trashPanel;
        if (r12 != 2) goto L98;
        r5 = 1.0f;
    L98:
        r1314.setAlpha(r5);
        FrameLayout r1315 = this.trashPanel;
        if (r12 != 2) goto L101;
        float r017 = 1.0f;
    L102:
        r1315.setScaleX(r017);
        FrameLayout r11 = this.trashPanel;
        if (r12 != 2) goto L105;
        r6 = 1.0f;
    L105:
        r11.setScaleY(r6);
        return;
    L101:
        r017 = 0.8f;
        goto L102
    L90:
        r15 = AndroidUtilities.dp(30.0f);
        goto L91
    L86:
        r89 = 0.8f;
        goto L87
    L82:
        r88 = 0.8f;
        goto L83
    L78:
        r87 = 0.0f;
        goto L79
    L74:
        r86 = 8;
        goto L75
    L70:
        r85 = AndroidUtilities.dp(30.0f);
        goto L71
    L66:
        r84 = 0.8f;
        goto L67
    L62:
        r83 = 0.8f;
        goto L63
    L58:
        r82 = 0.0f;
        goto L59
    L54:
        r8 = 8;
        goto L55
    }

    public ImageView getAddButton() {
        return this.addButton;
    }

    public FrameLayout getBottomContainer() {
        return this.bottomContainer;
    }

    public FrameLayout getBottomInnerContainer() {
        return this.bottomInnerContainer;
    }

    public LinearLayout getBottomPanel() {
        return this.bottomPanel;
    }

    public View getEmojiButton() {
        return this.emojiButton;
    }

    public ChatActivityEnterView.SendButton getSendButton() {
        return this.sendButton;
    }

    public void onReorderEnd() {
        int r0 = 0;
        setTrashHovered(false, true);
        int r2 = this.reorderSavedPanelType;
        if (r2 == 2) goto L6;
        r0 = r2;
    L6:
        updatePanel(r0, true);
    }

    public boolean onReorderMove(float r1, float r2) {
        boolean r12 = isOverTrash(r2);
        setTrashHovered(r12, true);
        return r12;
    }

    public void onReorderStart() {
        int r0 = this.panelType;
        if (r0 != 2) goto L5;
        r0 = 0;
    L5:
        this.reorderSavedPanelType = r0;
        setTrashHovered(false, false);
        updatePanel(2, true);
    }

    public void setBackVisible(boolean r1) {
        ImageView r0 = this.backButton;
        if (r1 == false) goto L5;
        int r12 = 0;
    L6:
        r0.setVisibility(r12);
        return;
    L5:
        r12 = 8;
        goto L6
    }

    public void setBottomGradientTranslationY(float r1) {
        this.bottomGradient.setTranslationY(r1);
    }

    public void setEmojiOpened(boolean r4) {
        ChatActivityEnterViewAnimatedIconView r0 = this.emojiButton;
        if (r4 == false) goto L5;
        ChatActivityEnterViewAnimatedIconView.State r1 = ChatActivityEnterViewAnimatedIconView.State.KEYBOARD;
    L6:
        r0.setState(r1, true);
        ChatActivityEnterViewAnimatedIconView r3 = this.emojiButton;
        if (r4 == false) goto L9;
        String r42 = "Keyboard";
    L10:
        r3.setContentDescription(r42);
        return;
    L9:
        r42 = "Emoji";
        goto L10
    L5:
        r1 = ChatActivityEnterViewAnimatedIconView.State.SMILE;
        goto L6
    }

    public void setFormattingState(int r9, boolean r10, boolean r11, boolean r12, boolean r13, boolean r14) {
        ArrayList<RichEditor.Button> r0 = this.formattingButtons;
        int r1 = r0.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L13;
        RichEditor.Button r4 = r0.get(r3);
        r3 = r3 + 1;
        RichEditor.Button r42 = r4;
        int r5 = ((Integer) r42.getTag()).intValue();
        if ((r9 & r5) == 0) goto L7;
        boolean r6 = true;
    L8:
        r42.setSelected(r6);
        if (r5 == 1) goto L12;
        if (r5 != 2) goto L3;
    L12:
        r42.setEnabled(r14);
        goto L3
    L7:
        r6 = false;
        goto L8
    L13:
        this.linkButton.setSelected(r10);
        this.dateButton.setSelected(r11);
        this.linkButton.setEnabled(r12);
        this.inlineButton.setEnabled(r13);
        this.dateButton.setEnabled(r12);
        this.mathButton.setEnabled(r12);
    }

    public void setHistoryEnabled(boolean r4, boolean r5) {
        this.undoButton.setEnabled(r4);
        ImageView r0 = this.undoButton;
        float r1 = 0.35f;
        if (r4 == false) goto L5;
        float r42 = 1.0f;
    L6:
        r0.setAlpha(r42);
        this.redoButton.setEnabled(r5);
        ImageView r3 = this.redoButton;
        if (r5 == false) goto L9;
        r1 = 1.0f;
    L9:
        r3.setAlpha(r1);
        return;
    L5:
        r42 = 0.35f;
        goto L6
    }

    public void setPremiumLocked(boolean r4) {
        ArrayList<RichEditor.Button> r3 = this.premiumButtons;
        int r0 = r3.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        RichEditor.Button r2 = r3.get(r1);
        r1 = r1 + 1;
        r2.setPremiumLocked(r4);
        goto L3
    }

    public void setQuoteState(boolean r1) {
        this.quoteButton.setSelected(r1);
    }

    public void setSelectedBlockType(int r2) {
        setSelectedBlockType(r2, 0);
    }

    public void setSelectedBlockType(int r6, int r7) {
        ArrayList<RichEditor.Button> r5 = this.blockButtons;
        int r0 = r5.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L13;
        RichEditor.Button r3 = r5.get(r2);
        r2 = r2 + 1;
        RichEditor.Button r32 = r3;
        if (r6 != ((Integer) r32.getTag()).intValue()) goto L7;
        boolean r4 = true;
    L8:
        r32.setSelected(r4);
        if (r4 == false) goto L12;
        if (r7 == 0) goto L12;
        r32.updateIcon(r7);
    L12:
        r32.resetIcon();
        goto L3
    L7:
        r4 = false;
        goto L8
    }

    public void setSendEditing(boolean r1) {
        ChatActivityEnterView.SendButton r0 = this.sendButton;
        if (r1 == false) goto L5;
        int r12 = R.drawable.input_done;
    L6:
        r0.setResourceId(r12);
        return;
    L5:
        r12 = R.drawable.send_extera_24;
        goto L6
    }

    public void setSendEnabled(boolean r3) {
        if (this.sendButton.isEnabled() != r3) goto L5;
        return;
    L5:
        this.sendButton.setEnabled(r3);
        ViewPropertyAnimator r2 = this.sendButton.animate();
        if (r3 == false) goto L8;
        float r32 = 1.0f;
    L9:
        r2.alpha(r32).setDuration(150).start();
        return;
    L8:
        r32 = 0.5f;
        goto L9
    }

    public void setSendLoading(boolean r2) {
        if (this.sendLoading != r2) goto L5;
        return;
    L5:
        this.sendLoading = r2;
        this.sendButton.invalidate();
    }

    public void setTopButtonsOffset(int r3) {
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) this.historyButtons.getLayoutParams();
        if (r0.topMargin == r3) goto L5;
        r0.topMargin = r3;
        this.historyButtons.setLayoutParams(r0);
    L5:
        FrameLayout.LayoutParams r02 = (FrameLayout.LayoutParams) this.backButton.getLayoutParams();
        if (r02.topMargin == r3) goto L9;
        r02.topMargin = r3;
        this.backButton.setLayoutParams(r02);
        return;
    }

    public void setTopGradientVisible(boolean r1) {
        View r0 = this.topGradient;
        if (r1 == false) goto L5;
        int r12 = 0;
    L6:
        r0.setVisibility(r12);
        return;
    L5:
        r12 = 8;
        goto L6
    }

    public void setTopPanelVisible(boolean r5) {
        FrameLayout r0 = this.topPanel;
        int r1 = 8;
        if (r5 == false) goto L5;
        int r3 = 0;
    L6:
        r0.setVisibility(r3);
        View r4 = this.topGradient;
        if (r5 == false) goto L9;
        r1 = 0;
    L9:
        r4.setVisibility(r1);
        return;
    L5:
        r3 = 8;
        goto L6
    }

    public void showFormattingPanel(boolean r3, boolean r4) {
        if (this.panelType != 2) goto L6;
        this.reorderSavedPanelType = r3 ? 1 : 0;
        return;
    L6:
        updatePanel(r3 ? 1 : 0, r4);
    }
}
