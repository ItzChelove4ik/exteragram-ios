package org.telegram.ui.iv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.RichMessageLayout;
import org.telegram.messenger.SharedConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.iv.RichEditor;

/* loaded from: classes7.dex */
public class RichButtonRowCell extends RichBlockCell implements Theme.Colorable {
    private final RichEditor.Button addButton;
    private final ArrayList<ButtonView> buttonViews;
    private final LinearLayout buttonsLayout;
    private final int currentAccount;
    private Delegate delegate;
    private final TextView emptyAddButton;
    private final Theme.ResourcesProvider resourcesProvider;
    private final HorizontalScrollView scrollView;

    public class ButtonView extends View {
        private final RichMessageLayout.RichButton button;
        private final int index;
        private final Runnable longPressRunnable;
        private boolean longPressed;
        private boolean pressed;
        final /* synthetic */ RichButtonRowCell this$0;

        public static /* synthetic */ void $r8$lambda$MUrx9B8Ec6EXDyrIkpuzWO2RcwM(ButtonView r0, int r1) {
            r0.lambda$new$0(r1);
        }

        public ButtonView(RichButtonRowCell r4, Context r5, TL_keyboard.PageButton r6, int r7) {
            this.this$0 = r4;
            super(r5);
            this.index = r7;
            RichMessageLayout.RichButton r42 = RichMessageLayout.createEditorPageButton(RichButtonRowCell.m21630$$Nest$fgetcurrentAccount(r4), Math.max(AndroidUtilities.dp(240.0f), AndroidUtilities.displaySize.x - AndroidUtilities.dp(32.0f)), RichButtonRowCell.m21632$$Nest$fgetresourcesProvider(r4), r6, new RichButtonRowCell$ButtonView$$ExternalSyntheticLambda0(this));
            this.button = r42;
            this.longPressRunnable = new RichButtonRowCell$ButtonView$$ExternalSyntheticLambda1(this, r7);
            r42.width = r42.getPreferredWidth();
            setContentDescription(RichTextStyle.plainOf(r6.text));
            setClickable(true);
            setLongClickable(true);
        }

        private /* synthetic */ void lambda$new$0(int r3) {
            if (this.pressed == true) goto L5;
            return;
        L5:
            if (RichButtonRowCell.m21631$$Nest$fgetdelegate(this.this$0) != null) goto L7;
            return;
        L7:
            if (this.this$0.currentRow == null) goto L18;
            this.longPressed = true;
            this.button.setPressed(false);
            performHapticFeedback(0);     // Catch: Exception -> L13
        L11:
            RichButtonRowCell.m21631$$Nest$fgetdelegate(this.this$0).onEditButton(this.this$0.currentRow, r3, this);
            return;
        }

        public int getContentHeight() {
            return this.button.getHeight() + AndroidUtilities.dp(8.0f);
        }

        public int getMinWidth() {
            return Math.max(AndroidUtilities.dp(34.0f), this.button.getMinWidth());
        }

        public int getPreferredWidth() {
            return Math.max(AndroidUtilities.dp(34.0f), this.button.getPreferredWidth());
        }

        @Override // android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.button.attach(this);
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            AndroidUtilities.cancelRunOnUIThread(this.longPressRunnable);
            this.button.detach(this);
            super.onDetachedFromWindow();
        }

        @Override // android.view.View
        public void onDraw(Canvas r3) {
            super.onDraw(r3);
            r3.save();
            r3.translate(0.0f, (getHeight() - this.button.getHeight()) / 2.0f);
            this.button.draw(r3);
            r3.restore();
        }

        @Override // android.view.View
        public void onMeasure(int r1, int r2) {
            setMeasuredDimension(this.button.width, getContentHeight());
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r5) {
            int r0 = r5.getActionMasked();
            if (r0 == 0) goto L37;
            if (r0 == 1) goto L24;
            if (r0 == 2) goto L14;
            if (r0 != 3) goto L10;
            this.pressed = false;
            this.button.setPressed(false);
            AndroidUtilities.cancelRunOnUIThread(this.longPressRunnable);
            return true;
        L10:
            return super.onTouchEvent(r5);
        L14:
            if (r5.getX() >= 0.0f) goto L16;
        L21:
            this.pressed = false;
            this.button.setPressed(false);
            AndroidUtilities.cancelRunOnUIThread(this.longPressRunnable);
        L22:
            return true;
        L16:
            if (r5.getY() < 0.0f) goto L21;
            if (r5.getX() > getWidth()) goto L21;
            if (r5.getY() <= getHeight()) goto L22;
        L24:
            if (this.pressed == true) goto L26;
        L28:
            boolean r52 = false;
        L29:
            this.pressed = false;
            this.button.setPressed(false);
            AndroidUtilities.cancelRunOnUIThread(this.longPressRunnable);
            if (r52 == true) goto L32;
        L36:
            return true;
        L32:
            if (RichButtonRowCell.m21631$$Nest$fgetdelegate(this.this$0) == null) goto L36;
            RichButtonRowCell r53 = this.this$0;
            if (r53.currentRow == null) goto L36;
            RichButtonRowCell.m21631$$Nest$fgetdelegate(r53).onCycleButtonStyle(this.this$0.currentRow, this.index);
            goto L36
        L26:
            if (this.longPressed == true) goto L28;
            r52 = true;
            goto L29
        L37:
            this.pressed = true;
            this.longPressed = false;
            this.button.setPressed(true);
            AndroidUtilities.runOnUIThread(this.longPressRunnable, ViewConfiguration.getLongPressTimeout());
            return true;
        }

        public void setButtonWidth(int r3) {
            RichMessageLayout.RichButton r0 = this.button;
            r0.width = Math.max(AndroidUtilities.dp(34.0f), r3);
            LinearLayout.LayoutParams r32 = (LinearLayout.LayoutParams) getLayoutParams();
            if (r32 == null) goto L8;
            int r02 = r32.width;
            int r1 = this.button.width;
            if (r02 == r1) goto L9;
            r32.width = r1;
            setLayoutParams(r32);
            return;
        L9:
            return;
        }
    }

    public interface Delegate {
        void onAddButton(BlockRow r1, View r2);

        void onCycleButtonStyle(BlockRow r1, int r2);

        void onEditButton(BlockRow r1, int r2, View r3);
    }

    public static final class Factory extends UItem.UItemFactory<RichButtonRowCell> {
        static {
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        public static UItem of(BlockRow r1, Delegate r2) {
            UItem r0 = UItem.ofFactory(Factory.class);
            r0.object = r1;
            r0.object2 = r2;
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            ((RichButtonRowCell) r1).bind((BlockRow) r2.object, (Delegate) r2.object2);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public RichButtonRowCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            RichButtonRowCell r0 = new RichButtonRowCell(r1, r3, r5);
            r0.setBackground(new RichEditor.DraggingDrawable(Theme.getColor(Theme.key_windowBackgroundWhite, r5)));
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean isClickable() {
            return false;
        }
    }

    public static /* synthetic */ void $r8$lambda$8q8X_mL5B9HO3yx8DhbKSbfTBP8(RichButtonRowCell r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$U5RNt-3IW4BDwzv06ZHsmeBPFmM, reason: not valid java name */
    public static /* synthetic */ void m21629$r8$lambda$U5RNt3IW4BDwzv06ZHsmeBPFmM(RichButtonRowCell r0, View r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21630$$Nest$fgetcurrentAccount(RichButtonRowCell r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Delegate m21631$$Nest$fgetdelegate(RichButtonRowCell r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m21632$$Nest$fgetresourcesProvider(RichButtonRowCell r0) {
        return r0.resourcesProvider;
    }

    public RichButtonRowCell(Context r6, int r7, Theme.ResourcesProvider r8) {
        super(r6);
        this.buttonViews = new ArrayList();
        this.currentAccount = r7;
        this.resourcesProvider = r8;
        setBlockPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(4.0f));
        HorizontalScrollView r72 = new HorizontalScrollView(r6);
        this.scrollView = r72;
        r72.setHorizontalScrollBarEnabled(false);
        LinearLayout r1 = new LinearLayout(r6);
        this.buttonsLayout = r1;
        r1.setOrientation(0);
        r72.addView(r1, new FrameLayout.LayoutParams(-2, -1));
        addView(r72, LayoutHelper.createFrame(-1, -1, 23));
        RichEditor.Button r73 = new RichEditor.Button(r6, R.drawable.msg_add, r8).setRoundRadius(19);
        this.addButton = r73;
        r73.setSelected(true);
        r73.setContentDescription(LocaleController.getString(R.string.Add));
        r73.setOnClickListener(new RichButtonRowCell$$ExternalSyntheticLambda0(this));
        addView(r73, LayoutHelper.createFrame(38, 38, 21));
        TextView r74 = new TextView(r6);
        this.emptyAddButton = r74;
        r74.setText(LocaleController.getString(R.string.RichEditorAddButton));
        r74.setTextSize(1, 14.0f);
        r74.setTypeface(AndroidUtilities.bold());
        r74.setGravity(17);
        r74.setCompoundDrawablePadding(AndroidUtilities.dp(7.0f));
        r74.setPadding(AndroidUtilities.dp(15.0f), 0, AndroidUtilities.dp(15.0f), 0);
        r74.setOnClickListener(new RichButtonRowCell$$ExternalSyntheticLambda1(this));
        addView(r74, LayoutHelper.createFrame(-2, 38, 17));
        updateAddButtonColors();
    }

    private static boolean isPointInside(View r4, float r5, float r6) {
        if (r4.getVisibility() == 0) goto L5;
        return false;
    L5:
        int[] r0 = new int[2];
        r4.getLocationOnScreen(r0);
        if (r5 >= r0[0]) goto L8;
    L14:
        return false;
    L8:
        if (r5 > (r2 + r4.getWidth())) goto L14;
        if (r6 < r0[1]) goto L14;
        if (r6 > (r0 + r4.getHeight())) goto L14;
        return true;
    }

    private /* synthetic */ void lambda$new$0(View r2) {
        Delegate r0 = this.delegate;
        if (r0 == null) goto L8;
        BlockRow r1 = this.currentRow;
        if (r1 == null) goto L9;
        r0.onAddButton(r1, r2);
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$new$1(View r2) {
        Delegate r0 = this.delegate;
        if (r0 == null) goto L8;
        BlockRow r1 = this.currentRow;
        if (r1 == null) goto L9;
        r0.onAddButton(r1, r2);
        return;
    L9:
        return;
    }

    private void layoutButtonWidths(int r7) {
        int r0 = this.buttonViews.size();
        if (r0 == 0) goto L13;
        int r1 = 0;
        int r72 = Math.max(0, r7 - (AndroidUtilities.dp(7.0f) * (r0 - 1)));
        int[] r2 = new int[r0];
        int r3 = 0;
        int r4 = 0;
    L6:
        if (r3 >= r0) goto L8;
        int r5 = this.buttonViews.get(r3).getPreferredWidth();
        r2[r3] = r5;
        r4 = r4 + r5;
        r3 = r3 + 1;
        goto L6
    L8:
        if (r4 > r72) goto L10;
        stretchWidths(r2, r72);
    L11:
        if (r1 >= r0) goto L17;
        this.buttonViews.get(r1).setButtonWidth(r2[r1]);
        r1 = r1 + 1;
        goto L11
    L17:
        return;
    L10:
        squeezeWidths(r2, r72, r4);
        goto L11
    }

    private void rebuildButtons() {
        this.buttonsLayout.removeAllViews();
        this.buttonViews.clear();
        BlockRow r0 = this.currentRow;
        if (r0 == null) goto L7;
        TL_iv.PageBlock r02 = r0.block;
        if ((r02 instanceof TL_iv.pageBlockButtonRow) == false) goto L7;
        TL_iv.pageBlockButtonRow r03 = (TL_iv.pageBlockButtonRow) r02;
    L8:
        int r1 = 0;
        if (r03 == null) goto L14;
        ArrayList<TL_keyboard.PageButton> r2 = r03.buttons;
        if (r2 == null) goto L14;
        int r22 = r2.size();
    L15:
        int r3 = 0;
    L16:
        if (r3 >= r22) goto L23;
        ButtonView r4 = new ButtonView(this, getContext(), r03.buttons.get(r3), r3);
        this.buttonViews.add(r4);
        LinearLayout r5 = this.buttonsLayout;
        if (r3 != 0) goto L20;
        int r10 = 0;
    L21:
        r5.addView(r4, LayoutHelper.createLinear(-2, -1, 16, r10, 0, 0, 0));
        r3 = r3 + 1;
        goto L16
    L20:
        r10 = 7;
        goto L21
    L23:
        if (r22 >= 8) goto L25;
        boolean r32 = true;
    L26:
        HorizontalScrollView r42 = this.scrollView;
        if (r22 <= 0) goto L29;
        int r52 = 0;
    L30:
        r42.setVisibility(r52);
        TextView r43 = this.emptyAddButton;
        if (r22 != 0) goto L33;
        int r53 = 0;
    L34:
        r43.setVisibility(r53);
        RichEditor.Button r44 = this.addButton;
        if (r22 <= 0) goto L38;
        if (r32 == false) goto L38;
    L39:
        r44.setVisibility(r1);
        requestLayout();
        return;
    L38:
        r1 = 8;
        goto L39
    L33:
        r53 = 8;
        goto L34
    L29:
        r52 = 8;
        goto L30
    L25:
        r32 = false;
    L14:
        r22 = 0;
    L7:
        r03 = null;
        goto L8
    }

    private void squeezeWidths(int[] r8, int r9, int r10) {
        int r0 = 0;
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= r8.length) goto L6;
        r2 = r2 + (r8[r1] - this.buttonViews.get(r1).getMinWidth());
        r1 = r1 + 1;
        goto L4
    L6:
        if (r2 <= 0) goto L8;
        int r92 = Math.min(r10 - r9, r2);
        int r102 = 0;
    L12:
        if (r0 >= r8.length) goto L24;
        int r12 = r8[r0] - this.buttonViews.get(r0).getMinWidth();
        if (r0 != (r8.length - 1)) goto L16;
        int r3 = r92 - r102;
    L17:
        int r13 = Math.min(r3, r12);
        r8[r0] = r8[r0] - r13;
        r102 = r102 + r13;
        r0 = r0 + 1;
        goto L12
    L16:
        r3 = (int) ((r92 * r12) / r2);
        goto L17
    L24:
        return;
    L8:
        if (r0 >= r8.length) goto L18;
        r8[r0] = this.buttonViews.get(r0).getMinWidth();
        r0 = r0 + 1;
        goto L8
    }

    private void stretchWidths(int[] r7, int r8) {
        boolean[] r6 = new boolean[r7.length];
        int r0 = r7.length;
        boolean r2 = true;
    L3:
        boolean r3 = false;
        if (r2 == false) goto L16;
        if (r0 <= 0) goto L16;
        int r22 = r8 / r0;
        int r4 = 0;
    L8:
        if (r4 >= r7.length) goto L15;
        if (r6[r4] == true) goto L14;
        int r5 = r7[r4];
        if (r5 <= r22) goto L14;
        r6[r4] = true;
        r8 = r8 - r5;
        r0 = r0 - 1;
        r3 = true;
    L14:
        r4 = r4 + 1;
        goto L8
    L15:
        r2 = r3;
    L16:
        if (r0 <= 0) goto L29;
        int r23 = r8 / r0;
        int r82 = r8 - (r0 * r23);
        int r02 = 0;
    L20:
        if (r02 >= r7.length) goto L39;
        if (r6[r02] == true) goto L28;
        int r42 = r82 - 1;
        if (r82 <= 0) goto L26;
        int r83 = 1;
    L27:
        r7[r02] = r83 + r23;
        r82 = r42;
        goto L28
    L26:
        r83 = 0;
    L28:
        r02 = r02 + 1;
        goto L20
    L39:
        return;
    }

    private void updateAddButtonColors() {
        int r0 = Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider);
        int r1 = Theme.blendOver(Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider), Theme.multAlpha(r0, 0.1f));
        this.emptyAddButton.setTextColor(r0);
        this.emptyAddButton.setBackground(Theme.createRadSelectorDrawable(r1, Theme.multAlpha(r0, 0.16f), AndroidUtilities.dp(19.0f), AndroidUtilities.dp(19.0f)));
        Drawable r12 = getContext().getResources().getDrawable(R.drawable.msg_add).mutate();
        r12.setColorFilter(new PorterDuffColorFilter(r0, PorterDuff.Mode.SRC_IN));
        this.emptyAddButton.setCompoundDrawablesRelativeWithIntrinsicBounds(r12, null, null, null);
    }

    public void bind(BlockRow r1, Delegate r2) {
        this.currentRow = r1;
        this.delegate = r2;
        bindBlockInset(r1);
        rebuildButtons();
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    public boolean isPressOnButton(float r6, float r7) {
        getLocationOnScreen(new int[2]);
        float r2 = r0[0] + r6;
        float r0 = r0[1] + r7;
        if (isPointInside(this.addButton, r2, r0) == false) goto L5;
    L13:
        return true;
    L5:
        if (isPointInside(this.emptyAddButton, r2, r0) == true) goto L13;
        ArrayList<ButtonView> r5 = this.buttonViews;
        int r72 = r5.size();
        int r3 = 0;
    L8:
        if (r3 >= r72) goto L12;
        ButtonView r4 = r5.get(r3);
        r3 = r3 + 1;
        if (isPointInside(r4, r2, r0) == false) goto L8;
        return true;
    L12:
        return false;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r5, int r6) {
        int r52 = View.MeasureSpec.getSize(r5);
        int r62 = this.buttonViews.size();
        if (r62 > 0) goto L5;
    L7:
        int r1 = 0;
    L8:
        FrameLayout.LayoutParams r2 = (FrameLayout.LayoutParams) this.scrollView.getLayoutParams();
        if (r2.rightMargin == r1) goto L11;
        r2.rightMargin = r1;
        this.scrollView.setLayoutParams(r2);
    L11:
        if (r62 <= 0) goto L13;
        layoutButtonWidths(Math.max(0, ((r52 - getPaddingLeft()) - getPaddingRight()) - r1));
    L13:
        if (r62 <= 0) goto L15;
        int r63 = this.buttonViews.get(0).getContentHeight();
    L16:
        int r0 = (getPaddingTop() + r63) + getPaddingBottom();
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(r52, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30));
        setMeasuredDimension(r52, r0);
        return;
    L15:
        r63 = AndroidUtilities.dp(SharedConfig.fontSize + 18) + AndroidUtilities.dp(8.0f);
        goto L16
    L5:
        if (r62 >= 8) goto L7;
        r1 = AndroidUtilities.dp(45.0f);
        goto L8
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        this.addButton.updateColors();
        updateAddButtonColors();
        rebuildButtons();
    }
}
