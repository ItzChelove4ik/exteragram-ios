package com.exteragram.messenger.adblock.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.adblock.WebAdBlocker;
import com.exteragram.messenger.adblock.backend.AdBlockManager;
import com.exteragram.messenger.utils.ui.SwitchUiHelper;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Switch;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes4.dex */
public class AdBlockMenuItem extends ActionBarMenuSubItem {
    private static final int[] BLOCKED_COUNT_SAMPLES = null;
    private final WebAdBlocker adBlocker;
    private final ItemOptions options;
    private CharSequence subtext;
    private final Switch switchView;
    private boolean toggled;
    private final Runnable updateRunnable;

    /* renamed from: com.exteragram.messenger.adblock.ui.AdBlockMenuItem$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ AdBlockMenuItem this$0;

        public AnonymousClass1(AdBlockMenuItem r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (AdBlockMenuItem.m1011$$Nest$fgettoggled(this.this$0) == true) goto L6;
            AdBlockMenuItem.m1012$$Nest$mupdate(this.this$0);
            AndroidUtilities.runOnUIThread(this, 500);
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$Ad8TY09JwGm62uEFw2JZaqQzXJA(AdBlockMenuItem r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: -$$Nest$fgettoggled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1011$$Nest$fgettoggled(AdBlockMenuItem r0) {
        return r0.toggled;
    }

    /* renamed from: -$$Nest$mupdate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1012$$Nest$mupdate(AdBlockMenuItem r0) {
        r0.update();
    }

    static {
        BLOCKED_COUNT_SAMPLES = new int[]{991, 992, 999};
    }

    private AdBlockMenuItem(Context r3, WebAdBlocker r4, ItemOptions r5) {
        super(r3, false, false, null);
        this.updateRunnable = new AnonymousClass1(this);
        this.adBlocker = r4;
        this.options = r5;
        setTextAndIcon(LocaleController.getString(R.string.BlockAds), R.drawable.msg_policy);
        setClipToPadding(false);
        Switch r42 = new Switch(r3);
        this.switchView = r42;
        int r32 = Theme.key_switchTrack;
        int r52 = Theme.key_switchTrackChecked;
        int r0 = Theme.key_windowBackgroundWhite;
        r42.setColors(r32, r52, r0, r0);
        r42.setImportantForAccessibility(2);
        r42.setChecked(ExteraConfig.getEnableAdBlock(), false);
        if (SwitchUiHelper.isMaterial3SwitchStyle() == false) goto L6;
        r42.setScaleX(0.85f);
        r42.setScaleY(0.85f);
    L6:
        if (LocaleController.isRTL == false) goto L8;
        int r33 = 3;
    L9:
        addView(r42, LayoutHelper.createFrame(44, 28, r33 | 16));
        setOnClickListener(new AdBlockMenuItem$$ExternalSyntheticLambda0(this));
        update();
        return;
    L8:
        r33 = 5;
        goto L9
    }

    public static void addTo(ItemOptions r2, WebAdBlocker r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (AdBlockManager.isAvailable() == true) goto L7;
        return;
    L7:
        if (ExteraConfig.getEnableAdBlock() == true) goto L9;
    L11:
        AdBlockMenuItem r0 = new AdBlockMenuItem(r2.getContext(), r3, r2);
        r2.add(r0);
        r0.alignSwitch();
        r0.fitWidth();
        r2.addGap();
        return;
    L9:
        if (AdBlockManager.isActive() == true) goto L11;
        AdBlockManager.initialize();
        goto L11
    }

    private void alignSwitch() {
        if (SwitchUiHelper.isMaterial3SwitchStyle() == false) goto L5;
        RectF r0 = new RectF();
        SwitchUiHelper.setTrackBounds(r0, 0, 0);
        float r2 = r0.width() * 0.85f;
        float r02 = r0.height() * 0.85f;
    L7:
        if (this.subtext == null) goto L9;
        float r3 = 56.0f;
    L10:
        float r32 = (AndroidUtilities.dp(r3) - r02) / 2.0f;
        if (LocaleController.isRTL == false) goto L13;
        int r4 = getPaddingLeft();
    L14:
        float r03 = r4;
        int r42 = Math.round((((r2 - AndroidUtilities.dp(44.0f)) / 2.0f) + r32) - r03);
        int r04 = Math.round(((r32 + r2) + AndroidUtilities.dp(12.0f)) - r03);
        FrameLayout.LayoutParams r22 = (FrameLayout.LayoutParams) this.switchView.getLayoutParams();
        if (LocaleController.isRTL == false) goto L17;
        r22.leftMargin = r42;
        AnimatedEmojiSpan.TextViewEmojis r23 = this.textView;
        r23.setPadding(r04, 0, r23.getPaddingRight(), 0);
    L18:
        TextView r05 = this.subtextView;
        if (r05 == null) goto L22;
        r05.setPadding(this.textView.getPaddingLeft(), 0, this.textView.getPaddingRight(), 0);
        return;
    L22:
        return;
    L17:
        r22.rightMargin = r42;
        AnimatedEmojiSpan.TextViewEmojis r24 = this.textView;
        r24.setPadding(r24.getPaddingLeft(), 0, r04, 0);
        goto L18
    L13:
        r4 = getPaddingRight();
        goto L14
    L9:
        r3 = 48.0f;
        goto L10
    L5:
        r2 = AndroidUtilities.dp(37.0f);
        r02 = AndroidUtilities.dp(20.0f);
        goto L7
    }

    private void fitWidth() {
        int r0 = getMinimumWidth();
        if (r0 <= 0) goto L22;
        TextPaint r1 = new TextPaint(1);
        r1.setTextSize(AndroidUtilities.dp(13.0f));
        float r2 = r1.measureText(LocaleController.getString(R.string.AdBlockFiltersLoading));
        int[] r3 = BLOCKED_COUNT_SAMPLES;
        int r4 = r3.length;
        int r5 = 0;
        int r6 = 0;
    L6:
        if (r6 >= r4) goto L9;
        r2 = Math.max(r2, r1.measureText(LocaleController.formatPluralString("BlockedRequests", r3[r6], new Object[0])));
        r6 = r6 + 1;
        goto L6
    L9:
        if (Math.min((((getPaddingLeft() + getPaddingRight()) + this.textView.getPaddingLeft()) + this.textView.getPaddingRight()) + ((int) Math.ceil(Math.max(this.textView.getPaint().measureText(this.textView.getText().toString()), r2))), AndroidUtilities.displaySize.x - AndroidUtilities.dp(32.0f)) <= r0) goto L17;
        int r02 = (int) Math.ceil(r1 / AndroidUtilities.density);
        this.options.setMinWidth(r02);
    L12:
        if (r5 >= this.options.getItemsCount()) goto L23;
        View r12 = this.options.getItemAt(r5);
        if ((r12 instanceof ActionBarMenuSubItem) == false) goto L16;
        float r22 = r02;
        r12.setMinimumWidth(AndroidUtilities.dp(r22));
        r12.getLayoutParams().width = AndroidUtilities.dp(r22);
    L16:
        r5 = r5 + 1;
        goto L12
    L23:
        return;
    L17:
        return;
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        toggle();
    }

    private void toggle() {
        if (this.toggled == false) goto L5;
        return;
    L5:
        this.toggled = true;
        boolean r1 = ExteraConfig.getEnableAdBlock();
        this.switchView.setChecked(!r1, true);
        if (r1 == true) goto L8;
        WebAdBlocker r12 = this.adBlocker;
        Objects.requireNonNull(r12);
        AdBlockManager.setEnabled(true, new AdBlockMenuItem$$ExternalSyntheticLambda1(r12));
    L9:
        ItemOptions r4 = this.options;
        Objects.requireNonNull(r4);
        AndroidUtilities.runOnUIThread(new AdBlockMenuItem$$ExternalSyntheticLambda2(r4), 200);
        return;
    L8:
        AdBlockManager.setEnabled(false, null);
        this.adBlocker.reload();
        goto L9
    }

    private void update() {
        if (ExteraConfig.getEnableAdBlock() == true) goto L5;
        String r0 = null;
    L10:
        if (this.subtextView != null) goto L12;
    L14:
        this.subtext = r0;
        if (r0 == null) goto L17;
        int r02 = 56;
    L18:
        setItemHeight(r02);
        setSubtext(this.subtext);
        return;
    L17:
        r02 = 48;
        goto L18
    L12:
        if (TextUtils.equals(this.subtext, r0) == false) goto L14;
        return;
    L5:
        if (AdBlockManager.isActive() == false) goto L7;
        r0 = LocaleController.formatPluralString("BlockedRequests", this.adBlocker.getBlockedCount(), new Object[0]);
        goto L10
    L7:
        r0 = LocaleController.getString(R.string.AdBlockFiltersLoading);
        goto L10
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        AndroidUtilities.runOnUIThread(this.updateRunnable, 500);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AndroidUtilities.cancelRunOnUIThread(this.updateRunnable);
    }

    @Override // org.telegram.ui.ActionBar.ActionBarMenuSubItem, android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
        super.onInitializeAccessibilityNodeInfo(r2);
        r2.setClassName("android.widget.Switch");
        r2.setCheckable(true);
        r2.setChecked(this.switchView.isChecked());
    }
}
