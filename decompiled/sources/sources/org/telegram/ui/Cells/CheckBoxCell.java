package org.telegram.ui.Cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.navigation.NavigationBarView;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.CheckBoxSquare;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;

/* loaded from: classes3.dex */
public class CheckBoxCell extends FrameLayout {
    private AnimatedTextView animatedTextView;
    private AvatarDrawable avatarDrawable;
    private BackupImageView avatarImageView;
    private final View checkBox;
    private CheckBox2 checkBoxRound;
    private final int checkBoxSize;
    private CheckBoxSquare checkBoxSquare;
    private View click1Container;
    private View click2Container;
    private CollapseButton collapseButton;
    private View collapsedArrow;
    private final int currentType;
    private boolean isMultiline;
    public int itemId;
    private LinkSpanDrawable.LinksTextView linksTextView;
    private boolean needDivider;
    private final Theme.ResourcesProvider resourcesProvider;
    private boolean textAnimated;
    private View textView;
    private final TextView valueTextView;

    /* renamed from: org.telegram.ui.Cells.CheckBoxCell$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 extends AnimatedTextView {
        final /* synthetic */ CheckBoxCell this$0;

        public AnonymousClass1(CheckBoxCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.AnimatedTextView, android.view.View
        public void onDraw(Canvas r1) {
            super.onDraw(r1);
            CheckBoxCell.m6874$$Nest$mupdateCollapseArrowTranslation(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Cells.CheckBoxCell$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 extends LinkSpanDrawable.LinksTextView {
        final /* synthetic */ CheckBoxCell this$0;

        public AnonymousClass2(CheckBoxCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.LinkSpanDrawable.LinksTextView, android.widget.TextView, android.view.View
        public void onDraw(Canvas r1) {
            super.onDraw(r1);
            CheckBoxCell.m6874$$Nest$mupdateCollapseArrowTranslation(this.this$0);
        }

        @Override // org.telegram.ui.Components.LinkSpanDrawable.LinksTextView, android.widget.TextView
        public void setText(CharSequence r3, TextView.BufferType r4) {
            super.setText(Emoji.replaceEmoji(r3, getPaint().getFontMetricsInt(), false), r4);
        }
    }

    /* loaded from: classes6.dex */
    public class CollapseButton extends LinearLayout {
        private final View collapsedArrow;
        private ImageView iconView;
        private final AnimatedTextView textView;
        final /* synthetic */ CheckBoxCell this$0;

        @SuppressLint({"UseCompatLoadingForDrawables"})
        public CollapseButton(CheckBoxCell r12, Context r13, int r14) {
            this.this$0 = r12;
            super(r13);
            int r0 = CheckBoxCell.m6873$$Nest$mgetThemedColor(r12, Theme.key_windowBackgroundWhiteBlackText);
            if (r14 == 0) goto L5;
            ImageView r1 = new ImageView(r13);
            this.iconView = r1;
            r1.setColorFilter(new PorterDuffColorFilter(r0, PorterDuff.Mode.MULTIPLY));
            this.iconView.setImageResource(r14);
        L5:
            AnimatedTextView r142 = new AnimatedTextView(r13, false, true, false);
            this.textView = r142;
            r142.setTextSize(AndroidUtilities.dp(13.0f));
            r142.setTextColor(r0);
            r142.setIncludeFontPadding(false);
            r142.setTypeface(AndroidUtilities.bold());
            View r3 = new View(r13);
            this.collapsedArrow = r3;
            Drawable r132 = getContext().getResources().getDrawable(R.drawable.arrow_more).mutate();
            r132.setColorFilter(new PorterDuffColorFilter(r0, PorterDuff.Mode.MULTIPLY));
            r3.setBackground(r132);
            int r02 = 11;
            if (LocaleController.isRTL == false) goto L14;
            addView(r3, LayoutHelper.createLinear(16, 16, 16, 11, 0, 3, 0));
            if (this.iconView == null) goto L11;
            r02 = 3;
        L11:
            addView(r142, LayoutHelper.createLinear(-2, 16, 16, 0, 0, r02, 0));
            View r133 = this.iconView;
            if (r133 == null) goto L22;
            addView(r133, LayoutHelper.createLinear(16, 16, 16, 0, 0, 11, 0));
        L22:
            setBackground(Theme.createRadSelectorDrawable(CheckBoxCell.m6873$$Nest$mgetThemedColor(r12, Theme.key_listSelector), 16, 16));
            setClickable(true);
            return;
        L14:
            View r134 = this.iconView;
            if (r134 == null) goto L18;
            addView(r134, LayoutHelper.createLinear(16, 16, 16, 11, 0, 3, 0));
        L18:
            if (this.iconView != null) goto L20;
            int r7 = 11;
        L21:
            addView(r142, LayoutHelper.createLinear(-2, 16, 16, r7, 0, 3, 0));
            addView(r3, LayoutHelper.createLinear(16, 16, 16, 0, 0, 11, 0));
            goto L22
        L20:
            r7 = 0;
            goto L21
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(32.0f), TLObject.FLAG_30));
        }

        public void set(boolean r2, CharSequence r3) {
            this.textView.cancelAnimation();
            this.textView.setText(r3);
            this.collapsedArrow.animate().cancel();
            ViewPropertyAnimator r1 = this.collapsedArrow.animate();
            if (r2 == false) goto L5;
            float r22 = 0.0f;
        L6:
            r1.rotation(r22).setDuration(340).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
            return;
        L5:
            r22 = 180.0f;
            goto L6
        }
    }

    /* renamed from: -$$Nest$mgetThemedColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6873$$Nest$mgetThemedColor(CheckBoxCell r0, int r1) {
        return r0.getThemedColor(r1);
    }

    /* renamed from: -$$Nest$mupdateCollapseArrowTranslation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6874$$Nest$mupdateCollapseArrowTranslation(CheckBoxCell r0) {
        r0.updateCollapseArrowTranslation();
    }

    public CheckBoxCell(Context r3, int r4) {
        this(r3, r4, 17, null);
    }

    public CheckBoxCell(Context r7, int r8, int r9, Theme.ResourcesProvider r10) {
        this(r7, r8, r9, false, r10);
    }

    public CheckBoxCell(Context r31, int r32, int r33, boolean r34, Theme.ResourcesProvider r35) {
        super(r31);
        this.resourcesProvider = r35;
        this.currentType = r32;
        this.textAnimated = r34;
        int r15 = 3;
        boolean r8 = true;
        if (r34 == false) goto L55;
        AnonymousClass1 r4 = new AnonymousClass1(this, r31);
        this.animatedTextView = r4;
        NotificationCenter.listenEmojiLoading(r4);
        this.animatedTextView.setEllipsizeByGradient(true);
        this.animatedTextView.setRightPadding(AndroidUtilities.dp(8.0f));
        this.animatedTextView.getDrawable().setHacks(true, true, false);
        AnimatedTextView r42 = this.animatedTextView;
        if (r32 == 1) goto L9;
        if (r32 == 5) goto L9;
        int r12 = Theme.key_windowBackgroundWhiteBlackText;
    L10:
        r42.setTag(Integer.valueOf(getThemedColor(r12)));
        this.animatedTextView.setTextSize(AndroidUtilities.dp(16.0f));
        if (r32 != 7) goto L13;
        this.animatedTextView.setTypeface(AndroidUtilities.bold());
    L13:
        AnimatedTextView r43 = this.animatedTextView;
        if (r32 != 3) goto L16;
        r43.setGravity(19);
        addView(this.animatedTextView, LayoutHelper.createFrame(-1, -2.0f, 19, 29.0f, 0.0f, 0.0f, 0.0f));
        this.animatedTextView.setPadding(0, 0, 0, AndroidUtilities.dp(3.0f));
    L54:
        this.textView = this.animatedTextView;
    L111:
        TextView r44 = new TextView(r31);
        this.valueTextView = r44;
        if (r32 == 1) goto L116;
        if (r32 == 5) goto L116;
        int r9 = Theme.key_windowBackgroundWhiteValueText;
    L117:
        r44.setTag(Integer.valueOf(r9));
        r44.setTextSize(1, 16.0f);
        r44.setLines(1);
        r44.setMaxLines(1);
        r44.setSingleLine(true);
        r44.setEllipsize(TextUtils.TruncateAt.END);
        if (LocaleController.isRTL == false) goto L120;
        int r92 = 3;
    L121:
        r44.setGravity(r92 | 16);
        if (LocaleController.isRTL == false) goto L124;
        int r93 = 3;
    L125:
        int r18 = r93 | 48;
        float r94 = r33;
        addView(r44, LayoutHelper.createFrame(-2, -1.0f, r18, r94, 0.0f, r94, 0.0f));
        if (isCheckboxRound() == false) goto L140;
        CheckBox2 r45 = new CheckBox2(r31, 21, r35);
        this.checkBoxRound = r45;
        this.checkBox = r45;
        r45.setDrawUnchecked(true);
        this.checkBoxRound.setChecked(true, false);
        this.checkBoxRound.setDrawBackgroundAsArc(10);
        this.checkBoxSize = 21;
        float r82 = 21;
        boolean r95 = LocaleController.isRTL;
        if (r95 == false) goto L130;
        r15 = 5;
    L130:
        int r25 = r15 | 48;
        if (r95 == false) goto L133;
        int r7 = 0;
    L134:
        float r72 = r7;
        if (r95 == false) goto L137;
        int r96 = r33;
    L138:
        addView(r45, LayoutHelper.createFrame(21, r82, r25, r72, 16.0f, r96, 0.0f));
    L178:
        if (r32 != 6) goto L180;
        CollapseButton r2 = new CollapseButton(this, r31, R.drawable.msg_folders_groups);
        this.collapseButton = r2;
        addView(r2, LayoutHelper.createFrameRelatively(-2.0f, -2.0f, 8388629, r94, 0.0f, r33 - 11, 0.0f));
    L184:
        updateTextColor();
        return;
    L180:
        if (r32 != 8) goto L182;
        CollapseButton r22 = new CollapseButton(this, r31, 0);
        this.collapseButton = r22;
        addView(r22, LayoutHelper.createFrameRelatively(-2.0f, -2.0f, 8388629, r94, 0.0f, r33 - 11, 0.0f));
        goto L184
    L182:
        if (r32 != 7) goto L184;
        this.avatarDrawable = new AvatarDrawable();
        BackupImageView r23 = new BackupImageView(r31);
        this.avatarImageView = r23;
        r23.setRoundRadius(AndroidUtilities.dp(17.0f));
        addView(this.avatarImageView, LayoutHelper.createFrameRelatively(34.0f, 34.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 56.0f, 0.0f, 0.0f, 0.0f));
        goto L184
    L137:
        r96 = 0;
        goto L138
    L133:
        r7 = r33;
        goto L134
    L140:
        if (r32 == 1) goto L144;
        if (r32 == 5) goto L144;
        r8 = false;
    L144:
        CheckBoxSquare r46 = new CheckBoxSquare(r31, r8, r35);
        this.checkBoxSquare = r46;
        this.checkBox = r46;
        this.checkBoxSize = 18;
        if (r32 != 5) goto L158;
        float r83 = 18;
        boolean r97 = LocaleController.isRTL;
        if (r97 == false) goto L149;
        r15 = 5;
    L149:
        int r252 = r15 | 16;
        if (r97 == false) goto L152;
        int r73 = 0;
    L153:
        float r74 = r73;
        if (r97 == false) goto L156;
        int r98 = r33;
    L157:
        addView(r46, LayoutHelper.createFrame(18, r83, r252, r74, 0.0f, r98, 0.0f));
        goto L178
    L156:
        r98 = 0;
        goto L157
    L152:
        r73 = r33;
        goto L153
    L158:
        if (r32 != 3) goto L160;
        addView(r46, LayoutHelper.createFrame(18, 18, 51, 0.0f, 15.0f, 0.0f, 0.0f));
        goto L178
    L160:
        if (r32 != 2) goto L165;
        float r84 = 18;
        if (LocaleController.isRTL == false) goto L164;
        r15 = 5;
    L164:
        addView(r46, LayoutHelper.createFrame(18, r84, r15 | 48, 0.0f, 15.0f, 0.0f, 0.0f));
        goto L178
    L165:
        float r85 = 18;
        boolean r99 = LocaleController.isRTL;
        if (r99 == false) goto L168;
        r15 = 5;
    L168:
        int r253 = r15 | 48;
        if (r99 == false) goto L171;
        int r75 = 0;
    L172:
        float r76 = r75;
        if (r99 == false) goto L175;
        int r910 = r33;
    L176:
        addView(r46, LayoutHelper.createFrame(18, r85, r253, r76, 16.0f, r910, 0.0f));
        goto L178
    L175:
        r910 = 0;
        goto L176
    L171:
        r75 = r33;
        goto L172
    L124:
        r93 = 5;
        goto L125
    L120:
        r92 = 5;
    L116:
        r9 = Theme.key_dialogTextBlue;
        goto L117
    L16:
        r43.setRightPadding(AndroidUtilities.dp(r33));
        AnimatedTextView r47 = this.animatedTextView;
        if (LocaleController.isRTL == false) goto L19;
        int r911 = 5;
    L20:
        r47.setGravity(r911 | 16);
        if (r32 != 2) goto L36;
        View r48 = this.animatedTextView;
        boolean r912 = LocaleController.isRTL;
        if (r912 == false) goto L25;
        int r122 = 5;
    L26:
        int r232 = r122 | 16;
        if (r912 == false) goto L29;
        int r123 = 8;
    L30:
        float r124 = r123;
        if (r912 == false) goto L33;
        int r913 = 29;
    L34:
        addView(r48, LayoutHelper.createFrame(-1, -2.0f, r232, r124, 0.0f, r913, 0.0f));
        goto L54
    L33:
        r913 = 8;
        goto L34
    L29:
        r123 = 29;
        goto L30
    L25:
        r122 = 3;
        goto L26
    L36:
        if (isCheckboxRound() == false) goto L38;
        int r16 = 56;
    L39:
        if (r32 != 7) goto L41;
        r16 = r16 + 39;
    L41:
        View r49 = this.animatedTextView;
        boolean r914 = LocaleController.isRTL;
        if (r914 == false) goto L44;
        int r125 = 5;
    L45:
        int r19 = r125 | 16;
        if (r914 == false) goto L48;
        int r126 = r33;
    L49:
        float r127 = r126;
        if (r914 == false) goto L52;
        int r915 = r16 + (r33 - 17);
    L53:
        addView(r49, LayoutHelper.createFrame(-1, -2.0f, r19, r127, 0.0f, r915, 0.0f));
        goto L54
    L52:
        r915 = r33;
        goto L53
    L48:
        r126 = r16 + (r33 - 17);
        goto L49
    L44:
        r125 = 3;
        goto L45
    L38:
        r16 = 46;
        goto L39
    L19:
        r911 = 3;
    L9:
        r12 = Theme.key_dialogTextBlack;
        goto L10
    L55:
        AnonymousClass2 r410 = new AnonymousClass2(this, r31);
        this.linksTextView = r410;
        NotificationCenter.listenEmojiLoading(r410);
        LinkSpanDrawable.LinksTextView r411 = this.linksTextView;
        if (r32 == 1) goto L60;
        if (r32 == 5) goto L60;
        int r128 = Theme.key_windowBackgroundWhiteBlackText;
    L61:
        r411.setTag(Integer.valueOf(getThemedColor(r128)));
        this.linksTextView.setTextSize(1, 16.0f);
        this.linksTextView.setLines(1);
        this.linksTextView.setMaxLines(1);
        this.linksTextView.setSingleLine(true);
        this.linksTextView.setEllipsize(TextUtils.TruncateAt.END);
        if (r32 != 7) goto L64;
        this.linksTextView.setTypeface(AndroidUtilities.bold());
    L64:
        LinkSpanDrawable.LinksTextView r412 = this.linksTextView;
        if (r32 != 3) goto L68;
        r412.setGravity(19);
        addView(this.linksTextView, LayoutHelper.createFrame(-1, -2.0f, 19, 29.0f, 0.0f, 0.0f, 0.0f));
        this.linksTextView.setPadding(0, 0, 0, AndroidUtilities.dp(3.0f));
    L110:
        this.textView = this.linksTextView;
        goto L111
    L68:
        if (LocaleController.isRTL == false) goto L70;
        int r916 = 5;
    L71:
        r412.setGravity(r916 | 16);
        if (r32 != 2) goto L87;
        View r413 = this.linksTextView;
        boolean r917 = LocaleController.isRTL;
        if (r917 == false) goto L76;
        int r129 = 5;
    L77:
        int r233 = r129 | 16;
        if (r917 == false) goto L80;
        int r1210 = 8;
    L81:
        float r1211 = r1210;
        if (r917 == false) goto L84;
        int r918 = 29;
    L85:
        addView(r413, LayoutHelper.createFrame(-1, -2.0f, r233, r1211, 0.0f, r918, 0.0f));
        goto L110
    L84:
        r918 = 8;
        goto L85
    L80:
        r1210 = 29;
        goto L81
    L76:
        r129 = 3;
        goto L77
    L87:
        if (isCheckboxRound() == false) goto L89;
        int r162 = 56;
    L90:
        if (r32 != 7) goto L92;
        r162 = r162 + 39;
    L92:
        View r414 = this.linksTextView;
        if (isCheckboxRound() == false) goto L96;
        int r919 = -2;
    L95:
        int r17 = r919;
        boolean r920 = LocaleController.isRTL;
        if (r920 == false) goto L100;
        int r1212 = 5;
    L101:
        int r192 = r1212 | 16;
        if (r920 == false) goto L104;
        int r1213 = r33;
    L105:
        float r1214 = r1213;
        if (r920 == false) goto L108;
        int r921 = r162 + (r33 - 17);
    L109:
        addView(r414, LayoutHelper.createFrame(r17, -2.0f, r192, r1214, 0.0f, r921, 0.0f));
        goto L110
    L108:
        r921 = r33;
        goto L109
    L104:
        r1213 = r162 + (r33 - 17);
        goto L105
    L100:
        r1212 = 3;
        goto L101
    L96:
        r919 = -1;
        goto L95
    L89:
        r162 = 46;
        goto L90
    L70:
        r916 = 3;
    L60:
        r128 = Theme.key_dialogTextBlack;
        goto L61
    }

    public CheckBoxCell(Context r2, int r3, Theme.ResourcesProvider r4) {
        this(r2, r3, 17, r4);
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private void updateCollapseArrowTranslation() {
        if (this.collapsedArrow != null) goto L14;
        return;
    L14:
        float r0 = this.textView.getMeasuredWidth();     // Catch: Exception -> L7
    L8:
        boolean r1 = LocaleController.isRTL;
        View r2 = this.textView;
        if (r1 == false) goto L11;
        float r12 = (r2.getRight() - r0) - AndroidUtilities.dp(20.0f);
    L12:
        this.collapsedArrow.setTranslationX(r12);
        return;
    L11:
        r12 = (r2.getLeft() + r0) + AndroidUtilities.dp(4.0f);
    L7:
        r0 = 0.0f;
        goto L8
    }

    public void allowMultiline() {
        if (this.textAnimated == false) goto L5;
        return;
    L5:
        this.linksTextView.setLines(3);
        this.linksTextView.setMaxLines(3);
        this.linksTextView.setSingleLine(false);
    }

    public AnimatedTextView getAnimatedTextView() {
        return this.animatedTextView;
    }

    public CheckBox2 getCheckBoxRound() {
        return this.checkBoxRound;
    }

    public View getCheckBoxView() {
        return this.checkBox;
    }

    public TextView getTextView() {
        return this.linksTextView;
    }

    public TextView getValueTextView() {
        return this.valueTextView;
    }

    public boolean hasIcon() {
        return this.checkBoxRound.hasIcon();
    }

    public boolean isCheckboxRound() {
        int r1 = this.currentType;
        if (r1 != 4) goto L5;
        return true;
    L5:
        if (r1 != 8) goto L7;
        return true;
    L7:
        if (r1 != 6) goto L9;
        return true;
    L9:
        if (r1 == 7) goto L17;
        return false;
    L17:
        return true;
    }

    public boolean isChecked() {
        CheckBox2 r0 = this.checkBoxRound;
        if (r0 == null) goto L7;
        return r0.isChecked();
    L7:
        return this.checkBoxSquare.isChecked();
    }

    @Override // android.view.View
    public void onDraw(Canvas r9) {
        if (this.needDivider == true) goto L5;
        return;
    L5:
        if (isCheckboxRound() == false) goto L7;
        float r0 = 60.0f;
    L8:
        int r02 = AndroidUtilities.dp(r0) + ((int) Math.abs(this.textView.getTranslationX()));
        if (this.currentType != 7) goto L11;
        r02 = r02 + AndroidUtilities.dp(39.0f);
    L11:
        Theme.ResourcesProvider r1 = this.resourcesProvider;
        if (r1 == null) goto L14;
        Paint r12 = r1.getPaint("paintDivider");
    L15:
        if (r12 != null) goto L17;
        r12 = Theme.dividerPaint;
    L17:
        Paint r7 = r12;
        if (LocaleController.isRTL == false) goto L21;
        float r13 = 0.0f;
    L20:
        float r3 = r13;
        float r4 = getMeasuredHeight() - 1;
        int r14 = getMeasuredWidth();
        if (LocaleController.isRTL == true) goto L26;
        r02 = 0;
    L26:
        r9.drawLine(r3, r4, r14 - r02, getMeasuredHeight() - 1, r7);
        return;
    L21:
        r13 = r02;
        goto L20
    L14:
        r12 = null;
        goto L15
    L7:
        r0 = 20.0f;
        goto L8
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
        super.onInitializeAccessibilityNodeInfo(r2);
        r2.setClassName("android.widget.CheckBox");
        r2.setCheckable(true);
        AnimatedTextView r0 = this.animatedTextView;
        if (r0 == null) goto L5;
        r2.setText(r0.getText());
    L8:
        r2.setChecked(isChecked());
        return;
    L5:
        LinkSpanDrawable.LinksTextView r02 = this.linksTextView;
        if (r02 == null) goto L8;
        r2.setText(r02.getText());
        goto L8
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r10, int r11) {
        int r112 = View.MeasureSpec.getSize(r10);
        if (this.currentType != 3) goto L6;
        this.valueTextView.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(10.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f), TLObject.FLAG_30));
        this.textView.measure(View.MeasureSpec.makeMeasureSpec(r112 - AndroidUtilities.dp(34.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f), Integer.MIN_VALUE));
        this.checkBox.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(this.checkBoxSize), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(this.checkBoxSize), TLObject.FLAG_30));
        setMeasuredDimension(this.textView.getMeasuredWidth() + AndroidUtilities.dp(29.0f), AndroidUtilities.dp(50.0f));
    L32:
        View r102 = this.click1Container;
        if (r102 == null) goto L35;
        ViewGroup.MarginLayoutParams r103 = (ViewGroup.MarginLayoutParams) r102.getLayoutParams();
        this.click1Container.measure(View.MeasureSpec.makeMeasureSpec((r112 - r103.leftMargin) - r103.rightMargin, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f), TLObject.FLAG_30));
    L35:
        View r104 = this.click2Container;
        if (r104 == null) goto L38;
        r104.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f), TLObject.FLAG_30));
    L38:
        View r9 = this.collapsedArrow;
        if (r9 == null) goto L42;
        r9.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(16.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(16.0f), TLObject.FLAG_30));
        return;
    L42:
        return;
    L6:
        if (this.isMultiline == false) goto L8;
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r10), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(0, 0));
        goto L32
    L8:
        setMeasuredDimension(View.MeasureSpec.getSize(r10), AndroidUtilities.dp(50.0f) + (this.needDivider ? 1 : 0));
        int r105 = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        if (isCheckboxRound() == false) goto L11;
        float r0 = 60.0f;
    L12:
        int r106 = r105 - AndroidUtilities.dp(r0);
        if (this.textAnimated == false) goto L16;
        r106 = r106 + ((int) this.animatedTextView.getRightPadding());
    L16:
        if (this.currentType != 7) goto L19;
        r106 = r106 - AndroidUtilities.dp(34.0f);
    L19:
        if ((this.valueTextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) == false) goto L21;
        r106 = r106 - ((ViewGroup.MarginLayoutParams) this.valueTextView.getLayoutParams()).rightMargin;
    L21:
        int r1 = r106 / 2;
        this.valueTextView.measure(View.MeasureSpec.makeMeasureSpec(r1, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TLObject.FLAG_30));
        int r02 = this.valueTextView.getMeasuredWidth();
        CollapseButton r6 = this.collapseButton;
        if (r6 == null) goto L24;
        r6.measure(View.MeasureSpec.makeMeasureSpec(r1, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TLObject.FLAG_30));
        r02 = r02 + (this.collapseButton.getMeasuredWidth() - AndroidUtilities.dp(11.0f));
    L24:
        int r12 = this.textView.getLayoutParams().width;
        View r62 = this.textView;
        if (r12 != (-1)) goto L27;
        r62.measure(View.MeasureSpec.makeMeasureSpec(((r106 - ((int) Math.abs(r62.getTranslationX()))) - r02) - AndroidUtilities.dp(8.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), Integer.MIN_VALUE));
    L28:
        BackupImageView r107 = this.avatarImageView;
        if (r107 == null) goto L31;
        r107.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(34.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(34.0f), TLObject.FLAG_30));
    L31:
        this.checkBox.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(this.checkBoxSize), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(this.checkBoxSize), TLObject.FLAG_30));
        goto L32
    L27:
        r62.measure(View.MeasureSpec.makeMeasureSpec(((r106 - ((int) Math.abs(r62.getTranslationX()))) - r02) - AndroidUtilities.dp(8.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), Integer.MIN_VALUE));
        goto L28
    L11:
        r0 = 34.0f;
        goto L12
    }

    public void reset() {
        if (this.textAnimated == false) goto L5;
        this.animatedTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
    L6:
        BackupImageView r0 = this.avatarImageView;
        if (r0 == null) goto L9;
        r0.setVisibility(8);
        this.avatarImageView.setImage(null, null, null);
    L9:
        CollapseButton r02 = this.collapseButton;
        if (r02 == null) goto L12;
        r02.setVisibility(8);
        this.collapseButton.setOnClickListener(null);
    L12:
        View r03 = this.collapsedArrow;
        if (r03 == null) goto L15;
        r03.setVisibility(8);
    L15:
        setPad(0);
        setMultiline(false);
        return;
    L5:
        this.linksTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        goto L6
    }

    public void setCheckBoxColor(int r1, int r2, int r3) {
        CheckBox2 r0 = this.checkBoxRound;
        if (r0 == null) goto L6;
        r0.setColor(r1, r1, r3);
        return;
    }

    public void setChecked(boolean r2, boolean r3) {
        CheckBox2 r0 = this.checkBoxRound;
        if (r0 == null) goto L6;
        r0.setChecked(r2, r3);
        return;
    L6:
        this.checkBoxSquare.setChecked(r2, r3);
    }

    public void setCollapseButton(boolean r3, CharSequence r4, View.OnClickListener r5) {
        CollapseButton r0 = this.collapseButton;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getVisibility() != 8) goto L7;
        this.collapseButton.setVisibility(0);
    L7:
        this.collapseButton.set(r3, r4);
        if (r5 == null) goto L12;
        this.collapseButton.setOnClickListener(r5);
        return;
    }

    public void setCollapsed(Boolean r5) {
        View r0 = this.collapsedArrow;
        if (r5 != null) goto L7;
        if (r0 == null) goto L18;
        removeView(r0);
        this.collapsedArrow = null;
        return;
    L18:
        return;
    L7:
        if (r0 != null) goto L10;
        this.collapsedArrow = new View(getContext());
        Drawable r02 = getContext().getResources().getDrawable(R.drawable.arrow_more).mutate();
        r02.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_windowBackgroundWhiteBlackText), PorterDuff.Mode.MULTIPLY));
        this.collapsedArrow.setBackground(r02);
        addView(this.collapsedArrow, LayoutHelper.createFrame(16, 16, 16));
    L10:
        if (this.collapsedArrow.getVisibility() != 8) goto L12;
        this.collapsedArrow.setVisibility(0);
    L12:
        updateCollapseArrowTranslation();
        this.collapsedArrow.animate().cancel();
        ViewPropertyAnimator r4 = this.collapsedArrow.animate();
        if (r5.booleanValue() == false) goto L15;
        float r52 = 0.0f;
    L16:
        r4.rotation(r52).setDuration(340).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
        return;
    L15:
        r52 = 180.0f;
        goto L16
    }

    @Override // android.view.View
    public void setEnabled(boolean r5) {
        super.setEnabled(r5);
        View r0 = this.textView;
        float r1 = 0.5f;
        if (r5 == false) goto L5;
        float r3 = 1.0f;
    L6:
        r0.setAlpha(r3);
        TextView r02 = this.valueTextView;
        if (r5 == false) goto L9;
        float r32 = 1.0f;
    L10:
        r02.setAlpha(r32);
        View r4 = this.checkBox;
        if (r5 == false) goto L13;
        r1 = 1.0f;
    L13:
        r4.setAlpha(r1);
        return;
    L9:
        r32 = 0.5f;
        goto L10
    L5:
        r3 = 0.5f;
        goto L6
    }

    public void setIcon(int r1) {
        this.checkBoxRound.setIcon(r1);
    }

    public void setMultiline(boolean r5) {
        if (this.textAnimated == false) goto L5;
        return;
    L5:
        this.isMultiline = r5;
        FrameLayout.LayoutParams r52 = (FrameLayout.LayoutParams) this.textView.getLayoutParams();
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) this.checkBox.getLayoutParams();
        boolean r1 = this.isMultiline;
        LinkSpanDrawable.LinksTextView r2 = this.linksTextView;
        if (r1 == false) goto L8;
        r2.setLines(0);
        this.linksTextView.setMaxLines(0);
        this.linksTextView.setSingleLine(false);
        this.linksTextView.setEllipsize(null);
        r0.gravity = (r0.gravity & 7) | 16;
        r0.topMargin = 0;
    L9:
        this.textView.setLayoutParams(r52);
        this.checkBox.setLayoutParams(r0);
        return;
    L8:
        r2.setLines(1);
        this.linksTextView.setMaxLines(1);
        this.linksTextView.setSingleLine(true);
        this.linksTextView.setEllipsize(TextUtils.TruncateAt.END);
        this.textView.setPadding(0, 0, 0, 0);
        r52.height = -1;
        r52.topMargin = 0;
        r0.gravity = (r0.gravity & 7) | 48;
        r0.topMargin = AndroidUtilities.dp(15.0f);
        goto L9
    }

    public void setNeedDivider(boolean r1) {
        this.needDivider = r1;
    }

    public void setOnSectionsClickListener(View.OnClickListener r6, View.OnClickListener r7) {
        View r0 = this.click1Container;
        if (r6 != null) goto L6;
        if (r0 == null) goto L9;
        removeView(r0);
        this.click1Container = null;
    L9:
        View r62 = this.click2Container;
        if (r7 != null) goto L14;
        if (r62 == null) goto L22;
        removeView(r62);
        this.click2Container = null;
        return;
    L22:
        return;
    L14:
        if (r62 != null) goto L20;
        View r63 = new View(getContext());
        this.click2Container = r63;
        if (LocaleController.isRTL == false) goto L18;
        int r02 = 5;
    L19:
        addView(r63, LayoutHelper.createFrame(56, -1, r02));
        goto L20
    L18:
        r02 = 3;
    L20:
        this.click2Container.setOnClickListener(r7);
        return;
    L6:
        if (r0 != null) goto L8;
        View r03 = new View(getContext());
        this.click1Container = r03;
        r03.setBackground(Theme.createSelectorDrawable(getThemedColor(Theme.key_listSelector), 2));
        addView(this.click1Container, LayoutHelper.createFrame(-1, -1, 119));
    L8:
        this.click1Container.setOnClickListener(r6);
        goto L9
    }

    public void setPad(int r3) {
        int r32 = r3 * 40;
        if (LocaleController.isRTL == false) goto L5;
        int r0 = -1;
    L6:
        int r33 = AndroidUtilities.dp(r32 * r0);
        View r02 = this.checkBox;
        if (r02 == null) goto L9;
        r02.setTranslationX(r33);
    L9:
        float r34 = r33;
        this.textView.setTranslationX(r34);
        BackupImageView r03 = this.avatarImageView;
        if (r03 == null) goto L12;
        r03.setTranslationX(r34);
    L12:
        View r04 = this.click1Container;
        if (r04 == null) goto L15;
        r04.setTranslationX(r34);
    L15:
        View r2 = this.click2Container;
        if (r2 == null) goto L19;
        r2.setTranslationX(r34);
        return;
    L19:
        return;
    L5:
        r0 = 1;
        goto L6
    }

    public void setSquareCheckBoxColor(int r1, int r2, int r3) {
        CheckBoxSquare r0 = this.checkBoxSquare;
        if (r0 == null) goto L6;
        r0.setColors(r1, r2, r3);
        return;
    }

    public void setText(CharSequence r3, String r4, boolean r5) {
        if (this.textAnimated == false) goto L5;
        CharSequence r32 = Emoji.replaceEmoji(r3, this.animatedTextView.getPaint().getFontMetricsInt(), false);
        this.animatedTextView.setText(r32, r5);
    L6:
        boolean r33 = TextUtils.isEmpty(r4);
        TextView r52 = this.valueTextView;
        if (r33 == true) goto L10;
        r52.setText(r4);
        this.valueTextView.setVisibility(0);
        return;
    L10:
        r52.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.valueTextView.setVisibility(8);
        return;
    L5:
        this.linksTextView.setText(r3);
        goto L6
    }

    public void setText(CharSequence r7, String r8, boolean r9, boolean r10) {
        setText(r7, r8, r9, r10, false);
    }

    public void setText(CharSequence r3, String r4, boolean r5, boolean r6, boolean r7) {
        if (this.textAnimated == false) goto L5;
        this.animatedTextView.setText(Emoji.replaceEmoji(r3, this.animatedTextView.getPaint().getFontMetricsInt(), false), r7);
    L6:
        CheckBox2 r32 = this.checkBoxRound;
        if (r32 == null) goto L9;
        r32.setChecked(r5, r7);
    L10:
        boolean r33 = TextUtils.isEmpty(r4);
        TextView r52 = this.valueTextView;
        if (r33 == true) goto L13;
        r52.setText(r4);
        this.valueTextView.setVisibility(0);
    L14:
        this.needDivider = r6;
        setWillNotDraw(!r6);
        return;
    L13:
        r52.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.valueTextView.setVisibility(8);
        goto L14
    L9:
        this.checkBoxSquare.setChecked(r5, r7);
        goto L10
    L5:
        this.linksTextView.setText(r3);
        goto L6
    }

    public void setTextColor(int r2) {
        if (this.textAnimated == false) goto L6;
        this.animatedTextView.setTextColor(r2);
        return;
    L6:
        this.linksTextView.setTextColor(r2);
    }

    public void setUserOrChat(TLObject r2) {
        setUserOrChat(r2, true);
    }

    public void setUserOrChat(TLObject r8, boolean r9) {
        if (this.avatarImageView.getVisibility() != 8) goto L5;
        this.avatarImageView.setVisibility(0);
    L5:
        this.avatarDrawable.setInfo(r8);
        this.avatarImageView.setForUserOrChat(r8, this.avatarDrawable);
        boolean r0 = r8 instanceof TLRPC.User;
        if (r0 == false) goto L8;
        String r1 = UserObject.getUserName((TLRPC.User) r8);
    L9:
        if (r0 == false) goto L14;
        if (((TLRPC.User) r8).id != MessagesController.getInstance(UserConfig.selectedAccount).telegramAntispamUserId) goto L14;
        r1 = LocaleController.getString(R.string.ChannelAntiSpamUser);
    L14:
        if (this.textAnimated == false) goto L17;
        CharSequence r82 = Emoji.replaceEmoji(r1, this.animatedTextView.getPaint().getFontMetricsInt(), false);
        this.animatedTextView.setText(r82, r9);
        return;
    L17:
        this.linksTextView.setText(r1);
        return;
    L8:
        r1 = ContactsController.formatName(r8);
        goto L9
    }

    public void updateTextColor() {
        if (this.textAnimated == false) goto L11;
        AnimatedTextView r0 = this.animatedTextView;
        int r3 = this.currentType;
        if (r3 == 1) goto L9;
        if (r3 == 5) goto L9;
        int r32 = Theme.key_windowBackgroundWhiteBlackText;
    L10:
        r0.setTextColor(getThemedColor(r32));
    L24:
        TextView r02 = this.valueTextView;
        int r33 = this.currentType;
        if (r33 == 1) goto L29;
        if (r33 == 5) goto L29;
        int r1 = Theme.key_windowBackgroundWhiteValueText;
    L30:
        r02.setTextColor(getThemedColor(r1));
        return;
    L29:
        r1 = Theme.key_dialogTextBlue;
    L9:
        r32 = Theme.key_dialogTextBlack;
        goto L10
    L11:
        LinkSpanDrawable.LinksTextView r03 = this.linksTextView;
        int r34 = this.currentType;
        if (r34 == 1) goto L16;
        if (r34 == 5) goto L16;
        int r35 = Theme.key_windowBackgroundWhiteBlackText;
    L17:
        r03.setTextColor(getThemedColor(r35));
        LinkSpanDrawable.LinksTextView r04 = this.linksTextView;
        int r36 = this.currentType;
        if (r36 == 1) goto L22;
        if (r36 == 5) goto L22;
        int r37 = Theme.key_windowBackgroundWhiteLinkText;
    L23:
        r04.setLinkTextColor(getThemedColor(r37));
    L22:
        r37 = Theme.key_dialogTextLink;
    L16:
        r35 = Theme.key_dialogTextBlack;
        goto L17
    }
}
