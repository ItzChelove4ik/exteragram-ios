package org.telegram.ui.Cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.IconBackgroundColors;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Switch;
import org.telegram.ui.SettingsActivity;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes6.dex */
public class PollCreateCheckCell extends FrameLayout {
    private boolean animationsEnabled;
    private final Switch checkBox;
    private boolean divider;
    private final ImageView imageView;
    private final TextView multilineValueTextView;
    private final Theme.ResourcesProvider resourcesProvider;
    private final TextView textView;

    public PollCreateCheckCell(Context r19, Theme.ResourcesProvider r20) {
        super(r19);
        this.resourcesProvider = r20;
        ImageView r3 = new ImageView(r19);
        this.imageView = r3;
        r3.setFocusable(false);
        r3.setScaleType(ImageView.ScaleType.CENTER);
        int r6 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r5 = 5;
    L6:
        addView(r3, LayoutHelper.createFrame(28, 28.0f, r5 | 48, 18.0f, 16.0f, 18.0f, 9.0f));
        TextView r32 = new TextView(r19);
        this.textView = r32;
        r32.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r20));
        r32.setTextSize(1, 16.0f);
        r32.setLines(1);
        r32.setMaxLines(1);
        r32.setSingleLine(true);
        if (LocaleController.isRTL == false) goto L9;
        int r52 = 5;
    L10:
        r32.setGravity(r52 | 16);
        r32.setEllipsize(TextUtils.TruncateAt.END);
        boolean r53 = LocaleController.isRTL;
        if (r53 == false) goto L13;
        int r9 = 5;
    L14:
        int r12 = r9 | 48;
        if (r53 == false) goto L17;
        float r13 = 66.0f;
    L18:
        if (r53 == false) goto L20;
        float r15 = 64.0f;
    L21:
        addView(r32, LayoutHelper.createFrame(-1, -2.0f, r12, r13, 8.0f, r15, 0.0f));
        TextView r33 = new TextView(r19);
        this.multilineValueTextView = r33;
        r33.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r20));
        r33.setTextSize(1, 13.0f);
        if (LocaleController.isRTL == false) goto L24;
        int r54 = 5;
    L25:
        r33.setGravity(r54);
        r33.setLines(0);
        r33.setMaxLines(0);
        r33.setSingleLine(false);
        r33.setEllipsize(null);
        r33.setLineSpacing(AndroidUtilities.dp(1.66f), 1.0f);
        boolean r55 = LocaleController.isRTL;
        if (r55 == false) goto L28;
        int r8 = 5;
    L29:
        int r122 = r8 | 48;
        if (r55 == false) goto L32;
        float r132 = 66.0f;
    L33:
        if (r55 == false) goto L35;
        float r152 = 64.0f;
    L36:
        addView(r33, LayoutHelper.createFrame(-2, -2.0f, r122, r132, 31.0f, r152, 10.0f));
        Switch r34 = new Switch(r19, r20);
        this.checkBox = r34;
        int r1 = Theme.key_switchTrack;
        int r2 = Theme.key_switchTrackChecked;
        int r56 = Theme.key_windowBackgroundWhite;
        r34.setColors(r1, r2, r56, r56);
        if (LocaleController.isRTL == true) goto L40;
        r6 = 5;
    L40:
        addView(r34, LayoutHelper.createFrame(37, 40.0f, r6 | 48, 21.0f, 10.0f, 19.0f, 0.0f));
        r34.setFocusable(false);
        setClipChildren(false);
        return;
    L35:
        r152 = 66.0f;
        goto L36
    L32:
        r132 = 64.0f;
        goto L33
    L28:
        r8 = 3;
        goto L29
    L24:
        r54 = 3;
        goto L25
    L20:
        r15 = 66.0f;
        goto L21
    L17:
        r13 = 64.0f;
        goto L18
    L13:
        r9 = 3;
        goto L14
    L9:
        r52 = 3;
        goto L10
    L5:
        r5 = 3;
        goto L6
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r8) {
        super.dispatchDraw(r8);
        if (this.divider == false) goto L23;
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L7;
        Paint r02 = r0.getPaint("paintDivider");
    L8:
        if (r02 != null) goto L10;
        r02 = Theme.dividerPaint;
    L10:
        Paint r6 = r02;
        if (r6 != null) goto L13;
        return;
    L13:
        if (LocaleController.isRTL == false) goto L16;
        float r03 = 0.0f;
    L15:
        float r2 = r03;
        float r3 = getMeasuredHeight() - 1;
        int r04 = getMeasuredWidth();
        if (LocaleController.isRTL == false) goto L20;
        int r1 = AndroidUtilities.dp(19.0f);
    L21:
        r8.drawLine(r2, r3, r04 - r1, getMeasuredHeight() - 1, r6);
        return;
    L20:
        r1 = 0;
        goto L21
    L16:
        r03 = AndroidUtilities.dp(19.0f);
        goto L15
    L7:
        r02 = Theme.dividerPaint;
        goto L8
    }

    public Switch getCheckBox() {
        return this.checkBox;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
        super.onInitializeAccessibilityNodeInfo(r3);
        r3.setClassName("android.widget.Switch");
        StringBuilder r0 = new StringBuilder();
        r0.append(this.textView.getText());
        TextView r1 = this.multilineValueTextView;
        if (r1 != null) goto L5;
    L7:
        r3.setContentDescription(r0);
        r3.setCheckable(true);
        r3.setChecked(this.checkBox.isChecked());
        return;
    L5:
        if (TextUtils.isEmpty(r1.getText()) == true) goto L7;
        r0.append("\n");
        r0.append(this.multilineValueTextView.getText());
        goto L7
    }

    public void setAnimationsEnabled(boolean r1) {
        this.animationsEnabled = r1;
    }

    public void setChecked(boolean r3) {
        this.checkBox.setChecked(r3, 0, true);
    }

    public void setDivider(boolean r1) {
        this.divider = r1;
        invalidate();
    }

    public void setTextAndValueAndIconAndCheck(CharSequence r4, CharSequence r5, IconBackgroundColors r6, int r7, boolean r8) {
        this.textView.setText(r4);
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L5;
        boolean r02 = r0.isDark();
    L6:
        SettingsActivity.SettingCell.Background r1 = new SettingsActivity.SettingCell.Background();
        r1.setColor(r6.top, r6.bottom);
        r1.setDrawBorder(r02);
        this.imageView.setBackground(r1);
        this.imageView.setImageResource(r7);
        ImageView r62 = this.imageView;
        if (Theme.isCurrentThemeMonet() == false) goto L9;
        int r72 = Theme.getColor(Theme.key_chats_actionIcon);
    L10:
        r62.setColorFilter(r72, PorterDuff.Mode.SRC_IN);
        this.checkBox.setChecked(r8, 0, this.animationsEnabled);
        this.multilineValueTextView.setText(r5);
        this.checkBox.setContentDescription(r4);
        return;
    L9:
        r72 = -1;
        goto L10
    L5:
        r02 = Theme.isCurrentThemeDark();
        goto L6
    }

    public void setValue(CharSequence r1) {
        this.multilineValueTextView.setText(r1);
    }
}
