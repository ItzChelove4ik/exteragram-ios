package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import com.google.android.material.navigation.NavigationBarView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Switch;

/* loaded from: classes6.dex */
public class AvailableReactionCell extends FrameLayout {
    private boolean canLock;
    private CheckBox2 checkBox;
    private BackupImageView imageView;
    public boolean locked;
    private View overlaySelectorView;
    public TLRPC.TL_availableReaction react;
    private Switch switchView;
    private SimpleTextView textView;

    public AvailableReactionCell(Context r10, boolean r11, boolean r12) {
        super(r10);
        this.canLock = r12;
        SimpleTextView r122 = new SimpleTextView(r10);
        this.textView = r122;
        NotificationCenter.listenEmojiLoading(r122);
        this.textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.textView.setTextSize(16);
        this.textView.setTypeface(AndroidUtilities.bold());
        this.textView.setMaxLines(1);
        this.textView.setMaxLines(1);
        this.textView.setGravity(16 | LayoutHelper.getAbsoluteGravityStart());
        addView(this.textView, LayoutHelper.createFrameRelatively(-2.0f, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 81.0f, 0.0f, 61.0f, 0.0f));
        BackupImageView r123 = new BackupImageView(r10);
        this.imageView = r123;
        r123.setAspectFit(true);
        this.imageView.setLayerNum(1);
        addView(this.imageView, LayoutHelper.createFrameRelatively(32.0f, 32.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 23.0f, 0.0f, 0.0f, 0.0f));
        if (r11 == false) goto L5;
        CheckBox2 r112 = new CheckBox2(r10, 26, null);
        this.checkBox = r112;
        r112.setDrawUnchecked(false);
        this.checkBox.setColor(-1, -1, Theme.key_radioBackgroundChecked);
        this.checkBox.setDrawBackgroundAsArc(-1);
        addView(this.checkBox, LayoutHelper.createFrameRelatively(26.0f, 26.0f, 8388629, 0.0f, 0.0f, 22.0f, 0.0f));
    L6:
        View r113 = new View(r10);
        this.overlaySelectorView = r113;
        r113.setBackground(Theme.getSelectorDrawable(false));
        addView(this.overlaySelectorView, LayoutHelper.createFrame(-1, -1.0f));
        setWillNotDraw(false);
        setClipChildren(false);
        return;
    L5:
        Switch r114 = new Switch(r10);
        this.switchView = r114;
        int r1 = Theme.key_switchTrack;
        int r2 = Theme.key_switchTrackChecked;
        int r3 = Theme.key_windowBackgroundWhite;
        r114.setColors(r1, r2, r3, r3);
        addView(this.switchView, LayoutHelper.createFrameRelatively(37.0f, 20.0f, 8388629, 0.0f, 0.0f, 22.0f, 0.0f));
        goto L6
    }

    public void bind(TLRPC.TL_availableReaction r13, boolean r14, int r15) {
        boolean r0 = true;
        if (r13 == null) goto L9;
        TLRPC.TL_availableReaction r2 = this.react;
        if (r2 == null) goto L9;
        if (r13.reaction.equals(r2.reaction) == false) goto L9;
        boolean r22 = true;
    L10:
        this.react = r13;
        SimpleTextView r3 = this.textView;
        r3.setText(Emoji.replaceEmoji(r13.title, r3.getPaint().getFontMetricsInt(), false));
        this.imageView.setImage(ImageLocation.getForDocument(r13.activate_animation), "30_30_pcache", "tgs", DocumentObject.getSvgThumb(r13.static_icon, Theme.key_windowBackgroundGray, 1.0f), r13);
        if (this.canLock == true) goto L13;
    L17:
        r0 = false;
    L18:
        this.locked = r0;
        if (r0 == false) goto L21;
        Drawable r132 = ContextCompat.getDrawable(getContext(), R.drawable.other_lockedfolders2);
        r132.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_stickers_menu), PorterDuff.Mode.MULTIPLY));
        this.textView.setRightDrawable(r132);
    L22:
        setChecked(r14, r22);
        return;
    L21:
        this.textView.setRightDrawable(null);
        goto L22
    L13:
        if (r13.premium == false) goto L17;
        if (UserConfig.getInstance(r15).isPremium() == true) goto L17;
    L9:
        r22 = false;
        goto L10
    }

    public boolean isChecked() {
        Switch r0 = this.switchView;
        if (r0 != null) goto L5;
        CheckBox2 r1 = this.checkBox;
        if (r1 != null) goto L9;
        return false;
    L9:
        return r1.isChecked();
    L5:
        return r0.isChecked();
    }

    @Override // android.view.View
    public void onDraw(Canvas r12) {
        r12.drawColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        float r0 = Theme.dividerPaint.getStrokeWidth();
        int r1 = AndroidUtilities.dp(81.0f);
        int r3 = 0;
        if (LocaleController.isRTL == false) goto L5;
        r3 = r1;
        r1 = 0;
    L5:
        r12.drawLine(getPaddingLeft() + r1, getHeight() - r0, (getWidth() - getPaddingRight()) - r3, getHeight() - r0, Theme.dividerPaint);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
        super.onInitializeAccessibilityNodeInfo(r3);
        r3.setEnabled(true);
        r3.setClickable(true);
        if (this.switchView == null) goto L6;
        r3.setCheckable(true);
        r3.setChecked(isChecked());
        r3.setClassName("android.widget.Switch");
    L8:
        r3.setContentDescription(this.textView.getText());
        return;
    L6:
        if (isChecked() == false) goto L8;
        r3.setSelected(true);
        goto L8
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) (AndroidUtilities.dp(58.0f) + Theme.dividerPaint.getStrokeWidth()), TLObject.FLAG_30));
    }

    public void setChecked(boolean r2) {
        setChecked(r2, false);
    }

    public void setChecked(boolean r2, boolean r3) {
        Switch r0 = this.switchView;
        if (r0 == null) goto L5;
        r0.setChecked(r2, r3);
    L5:
        CheckBox2 r1 = this.checkBox;
        if (r1 == null) goto L9;
        r1.setChecked(r2, r3);
        return;
    }
}
