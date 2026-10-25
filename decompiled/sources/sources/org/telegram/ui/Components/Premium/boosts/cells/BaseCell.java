package org.telegram.ui.Components.Premium.boosts.cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadioButton;
import org.telegram.ui.Components.RecyclerListView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public abstract class BaseCell extends FrameLayout {
    protected final AvatarDrawable avatarDrawable;
    protected View backgroundView;
    protected final Paint dividerPaint;
    protected final BackupImageView imageView;
    protected boolean needDivider;
    protected final RadioButton radioButton;
    protected final Theme.ResourcesProvider resourcesProvider;
    public final SimpleTextView subtitleTextView;
    protected final SimpleTextView titleTextView;

    /* renamed from: org.telegram.ui.Components.Premium.boosts.cells.BaseCell$1, reason: invalid class name */
    public class AnonymousClass1 extends SimpleTextView {
        final /* synthetic */ BaseCell this$0;

        public AnonymousClass1(BaseCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.ActionBar.SimpleTextView
        public boolean setText(CharSequence r3) {
            return super.setText(Emoji.replaceEmoji(r3, getPaint().getFontMetricsInt(), false));
        }
    }

    public BaseCell(Context r7, Theme.ResourcesProvider r8) {
        super(r7);
        AvatarDrawable r0 = new AvatarDrawable();
        this.avatarDrawable = r0;
        this.dividerPaint = new Paint(1);
        this.resourcesProvider = r8;
        View r1 = new View(r7);
        this.backgroundView = r1;
        addView(r1, LayoutHelper.createLinear(-1, -1));
        this.backgroundView.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, r8));
        r0.setRoundRadius(AndroidUtilities.dp(40.0f));
        BackupImageView r02 = new BackupImageView(r7);
        this.imageView = r02;
        r02.setRoundRadius(AndroidUtilities.dp(20.0f));
        addView(r02);
        AnonymousClass1 r2 = new AnonymousClass1(this, r7);
        this.titleTextView = r2;
        NotificationCenter.listenEmojiLoading(r2);
        NotificationCenter.listenEmojiLoading(r02);
        r2.setTextSize(16);
        int r03 = Theme.key_dialogTextBlack;
        r2.setTextColor(Theme.getColor(r03, r8));
        int r4 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r3 = 5;
    L6:
        r2.setGravity(r3);
        addView(r2);
        SimpleTextView r22 = new SimpleTextView(r7);
        this.subtitleTextView = r22;
        r22.setTextSize(14);
        r22.setTextColor(Theme.getColor(r03, r8));
        if (LocaleController.isRTL == false) goto L9;
        r4 = 5;
    L9:
        r22.setGravity(r4);
        addView(r22);
        RadioButton r04 = new RadioButton(r7);
        this.radioButton = r04;
        r04.setSize(AndroidUtilities.dp(20.0f));
        r04.setColor(Theme.getColor(Theme.key_checkboxDisabled, r8), Theme.getColor(Theme.key_dialogRadioBackgroundChecked, r8));
        addView(r04);
        updateLayouts();
        if (needCheck() == true) goto L13;
        r04.setVisibility(8);
        return;
    L13:
        return;
    L5:
        r3 = 3;
        goto L6
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r15) {
        super.dispatchDraw(r15);
        if (this.needDivider == false) goto L17;
        this.dividerPaint.setColor(Theme.getColor(Theme.key_divider, this.resourcesProvider));
        if (needCheck() == false) goto L7;
        int r0 = 105;
    L9:
        if (this.imageView.getVisibility() != 8) goto L11;
        r0 = r0 - 40;
    L11:
        int r02 = r0 + dividerPadding();
        if (LocaleController.isRTL == false) goto L15;
        r15.drawRect(0.0f, getHeight() - 1, getWidth() - AndroidUtilities.dp(r02), getHeight(), this.dividerPaint);
        return;
    L15:
        r15.drawRect(AndroidUtilities.dp(r02), getHeight() - 1, getWidth(), getHeight(), this.dividerPaint);
        return;
    L7:
        r0 = 70;
        goto L9
    }

    public int dividerPadding() {
        return 0;
    }

    public int getFullHeight() {
        return 56;
    }

    public void markChecked(RecyclerListView r6) {
        if (needCheck() == false) goto L16;
        int r1 = 0;
    L7:
        if (r1 >= r6.getChildCount()) goto L20;
        View r2 = r6.getChildAt(r1);
        if (r2.getClass().isInstance(this) == false) goto L15;
        BaseCell r3 = (BaseCell) r2;
        if (r2 != this) goto L13;
        boolean r22 = true;
    L14:
        r3.setChecked(r22, true);
        goto L15
    L13:
        r22 = false;
    L15:
        r1 = r1 + 1;
        goto L7
    L20:
        return;
    }

    public abstract boolean needCheck();

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(getFullHeight()), TLObject.FLAG_30));
    }

    public void setChecked(boolean r2, boolean r3) {
        if (this.radioButton.getVisibility() != 0) goto L6;
        this.radioButton.setChecked(r2, r3);
        return;
    }

    public void setDivider(boolean r1) {
        this.needDivider = r1;
        invalidate();
    }

    public void setSubtitle(CharSequence r4) {
        boolean r0 = TextUtils.isEmpty(r4);
        SimpleTextView r1 = this.titleTextView;
        if (r0 == false) goto L5;
        r1.setTranslationY(0.0f);
        this.subtitleTextView.setVisibility(8);
    L7:
        if (this.imageView.getVisibility() != 8) goto L14;
        boolean r42 = LocaleController.isRTL;
        SimpleTextView r02 = this.titleTextView;
        if (r42 == false) goto L12;
        r02.setTranslationX(AndroidUtilities.dp(40.0f));
        this.subtitleTextView.setTranslationX(AndroidUtilities.dp(40.0f));
        return;
    L12:
        r02.setTranslationX(AndroidUtilities.dp(-40.0f));
        this.subtitleTextView.setTranslationX(AndroidUtilities.dp(-40.0f));
        return;
    L14:
        return;
    L5:
        r1.setTranslationY(AndroidUtilities.dp(-9.0f));
        this.subtitleTextView.setTranslationY(AndroidUtilities.dp(12.0f));
        this.subtitleTextView.setText(r4);
        this.subtitleTextView.setVisibility(0);
        goto L7
    }

    public void updateLayouts() {
        BackupImageView r1 = this.imageView;
        int r3 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r2 = 5;
    L6:
        int r7 = r2 | 16;
        if (needCheck() == false) goto L9;
        float r8 = 53.0f;
    L11:
        if (needCheck() == false) goto L13;
        float r10 = 53.0f;
    L14:
        r1.setLayoutParams(LayoutHelper.createFrame(40, 40.0f, r7, r8, 0.0f, r10, 0.0f));
        SimpleTextView r12 = this.titleTextView;
        boolean r22 = LocaleController.isRTL;
        if (r22 == false) goto L17;
        int r5 = 5;
    L18:
        int r82 = r5 | 16;
        int r52 = 70;
        if (r22 == false) goto L22;
        float r9 = 20.0f;
    L27:
        if (LocaleController.isRTL == true) goto L29;
        float r11 = 20.0f;
    L34:
        r12.setLayoutParams(LayoutHelper.createFrame(-1, -2.0f, r82, r9, 0.0f, r11, 0.0f));
        SimpleTextView r13 = this.subtitleTextView;
        boolean r23 = LocaleController.isRTL;
        if (r23 == false) goto L37;
        int r6 = 5;
    L38:
        int r17 = r6 | 16;
        if (r23 == false) goto L42;
        float r18 = 20.0f;
    L47:
        if (LocaleController.isRTL == true) goto L49;
        float r20 = 20.0f;
    L53:
        r13.setLayoutParams(LayoutHelper.createFrame(-1, -2.0f, r17, r18, 0.0f, r20, 0.0f));
        RadioButton r0 = this.radioButton;
        boolean r14 = LocaleController.isRTL;
        if (r14 == false) goto L56;
        r3 = 5;
    L56:
        int r62 = r3 | 16;
        if (r14 == false) goto L59;
        float r72 = 15.0f;
    L60:
        if (r14 == false) goto L62;
        float r92 = 20.0f;
    L63:
        r0.setLayoutParams(LayoutHelper.createFrame(22, 22.0f, r62, r72, 0.0f, r92, 0.0f));
        return;
    L62:
        r92 = 15.0f;
        goto L63
    L59:
        r72 = 20.0f;
        goto L60
    L49:
        if (needCheck() == false) goto L51;
        r52 = 105;
    L51:
        r20 = r52;
        goto L53
    L42:
        if (needCheck() == false) goto L44;
        int r24 = 105;
    L45:
        r18 = r24;
        goto L47
    L44:
        r24 = 70;
        goto L45
    L37:
        r6 = 3;
        goto L38
    L29:
        if (needCheck() == false) goto L31;
        int r25 = 105;
    L32:
        r11 = r25;
        goto L34
    L31:
        r25 = 70;
        goto L32
    L22:
        if (needCheck() == false) goto L24;
        int r26 = 105;
    L25:
        r9 = r26;
        goto L27
    L24:
        r26 = 70;
        goto L25
    L17:
        r5 = 3;
        goto L18
    L13:
        r10 = 16.0f;
        goto L14
    L9:
        r8 = 16.0f;
        goto L11
    L5:
        r2 = 3;
        goto L6
    }

    public CharSequence withArrow(CharSequence r7) {
        SpannableString r0 = new SpannableString(">");
        Drawable r6 = getContext().getResources().getDrawable(R.drawable.attach_arrow_right);
        ColoredImageSpan r1 = new ColoredImageSpan(r6, 2);
        r6.setBounds(0, AndroidUtilities.dp(1.0f), AndroidUtilities.dp(11.0f), AndroidUtilities.dp(12.0f));
        r0.setSpan(r1, 0, r0.length(), 33);
        SpannableStringBuilder r62 = new SpannableStringBuilder();
        r62.append(r7).append(" ").append(r0);
        return r62;
    }
}
