package org.telegram.ui.Components.Reactions;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Stories.RoundRectOutlineProvider;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class BackSpaceButtonView extends FrameLayout {
    private final ImageView backspaceButton;
    private boolean backspaceOnce;
    private boolean backspacePressed;
    private Utilities.Callback<Boolean> onBackspace;
    private final Theme.ResourcesProvider resourcesProvider;

    /* renamed from: org.telegram.ui.Components.Reactions.BackSpaceButtonView$1, reason: invalid class name */
    public class AnonymousClass1 extends ImageView {
        private long lastClick;
        final /* synthetic */ BackSpaceButtonView this$0;

        public AnonymousClass1(BackSpaceButtonView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.lastClick = 0;
        }

        @Override // android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent r10) {
            if (r10.getAction() != 0) goto L9;
            if (System.currentTimeMillis() >= (this.lastClick + 350)) goto L7;
            return false;
        L7:
            this.lastClick = System.currentTimeMillis();
            BackSpaceButtonView.m12309$$Nest$fputbackspacePressed(this.this$0, true);
            BackSpaceButtonView.m12308$$Nest$fputbackspaceOnce(this.this$0, false);
            BackSpaceButtonView.m12310$$Nest$mpostBackspaceRunnable(this.this$0, 350);
        L18:
            super.onTouchEvent(r10);
            return true;
        L9:
            if (r10.getAction() != 3) goto L11;
        L12:
            BackSpaceButtonView.m12309$$Nest$fputbackspacePressed(this.this$0, false);
            if (BackSpaceButtonView.m12306$$Nest$fgetbackspaceOnce(this.this$0) == true) goto L18;
            if (BackSpaceButtonView.m12307$$Nest$fgetonBackspace(this.this$0) == null) goto L18;
            BackSpaceButtonView.m12307$$Nest$fgetonBackspace(this.this$0).run(Boolean.FALSE);
            BackSpaceButtonView.m12305$$Nest$fgetbackspaceButton(this.this$0).performHapticFeedback(3);     // Catch: Exception -> L20
            goto L18
        L11:
            if (r10.getAction() != 1) goto L18;
            goto L12
        }
    }

    public static /* synthetic */ void $r8$lambda$fzY2D_3TtdN909QK9dTb1llnbDo(View r0) {
    }

    public static /* synthetic */ void $r8$lambda$q7tfSbfHCIRAIvBLja0xsxKnUZE(BackSpaceButtonView r0, int r1) {
        r0.lambda$postBackspaceRunnable$1(r1);
    }

    /* renamed from: -$$Nest$fgetbackspaceButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m12305$$Nest$fgetbackspaceButton(BackSpaceButtonView r0) {
        return r0.backspaceButton;
    }

    /* renamed from: -$$Nest$fgetbackspaceOnce, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12306$$Nest$fgetbackspaceOnce(BackSpaceButtonView r0) {
        return r0.backspaceOnce;
    }

    /* renamed from: -$$Nest$fgetonBackspace, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Utilities.Callback m12307$$Nest$fgetonBackspace(BackSpaceButtonView r0) {
        return r0.onBackspace;
    }

    /* renamed from: -$$Nest$fputbackspaceOnce, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12308$$Nest$fputbackspaceOnce(BackSpaceButtonView r0, boolean r1) {
        r0.backspaceOnce = r1;
    }

    /* renamed from: -$$Nest$fputbackspacePressed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12309$$Nest$fputbackspacePressed(BackSpaceButtonView r0, boolean r1) {
        r0.backspacePressed = r1;
    }

    /* renamed from: -$$Nest$mpostBackspaceRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12310$$Nest$mpostBackspaceRunnable(BackSpaceButtonView r0, int r1) {
        r0.postBackspaceRunnable(r1);
    }

    public BackSpaceButtonView(Context r4, Theme.ResourcesProvider r5) {
        super(r4);
        this.resourcesProvider = r5;
        AnonymousClass1 r52 = new AnonymousClass1(this, r4);
        this.backspaceButton = r52;
        r52.setHapticFeedbackEnabled(true);
        r52.setImageResource(R.drawable.smiles_tab_clear);
        r52.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_chat_emojiPanelBackspace), PorterDuff.Mode.MULTIPLY));
        r52.setScaleType(ImageView.ScaleType.CENTER);
        r52.setContentDescription(LocaleController.getString(R.string.AccDescrBackspace));
        r52.setFocusable(true);
        r52.setOnClickListener(new BackSpaceButtonView$$ExternalSyntheticLambda0());
        addView(r52, LayoutHelper.createFrame(36, 36, 17));
        r52.setBackground(Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(36.0f), getThemedColor(Theme.key_windowBackgroundWhite), Theme.getColor(Theme.key_listSelector)));
        r52.setOutlineProvider(new RoundRectOutlineProvider(18));
        r52.setElevation(AndroidUtilities.dp(1.0f));
        r52.setClipToOutline(true);
        setClickable(true);
    }

    private int getThemedColor(int r1) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L7;
        return r0.getColor(r1);
    L7:
        return Theme.getColor(r1);
    }

    private /* synthetic */ void lambda$postBackspaceRunnable$1(int r4) {
        if (this.backspacePressed == true) goto L5;
        return;
    L5:
        Utilities.Callback<Boolean> r0 = this.onBackspace;
        if (r0 != null) goto L8;
    L13:
        this.backspaceOnce = true;
        postBackspaceRunnable(Math.max(50, r4 - 100));
        return;
    L8:
        if (r4 >= 300) goto L10;
        boolean r2 = true;
    L11:
        r0.run(Boolean.valueOf(r2));
        this.backspaceButton.performHapticFeedback(3);     // Catch: Exception -> L15
        goto L13
    L10:
        r2 = false;
        goto L11
    }

    private void postBackspaceRunnable(int r2) {
        AndroidUtilities.runOnUIThread(new BackSpaceButtonView$$ExternalSyntheticLambda1(this, r2), r2);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(42.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(42.0f), TLObject.FLAG_30));
    }

    public void setOnBackspace(Utilities.Callback<Boolean> r1) {
        this.onBackspace = r1;
    }
}
