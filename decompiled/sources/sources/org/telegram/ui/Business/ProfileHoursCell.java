package org.telegram.ui.Business;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import j$.time.DayOfWeek;
import j$.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Business.OpeningHoursActivity;
import org.telegram.ui.Components.ClickableAnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

/* loaded from: classes6.dex */
public class ProfileHoursCell extends LinearLayout {
    private ImageView arrowView;
    private boolean expanded;
    private boolean firstAfterAttach;
    private final TextView[] labelText;
    private TextView[] labelTimeText;
    private final ViewGroup[] lines;
    private boolean needDivider;
    private final Theme.ResourcesProvider resourcesProvider;
    private ClickableAnimatedTextView switchText;
    private TextView textView;
    private final TextView[][] timeText;
    private int todayLinesCount;
    private int todayLinesHeight;
    private FrameLayout todayTimeContainer;
    private FrameLayout todayTimeTextContainer;
    private LinearLayout todayTimeTextContainer2;

    /* renamed from: org.telegram.ui.Business.ProfileHoursCell$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ ProfileHoursCell this$0;

        public AnonymousClass1(ProfileHoursCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(r3, View.MeasureSpec.makeMeasureSpec(Math.max(View.MeasureSpec.getSize(r4), AndroidUtilities.dp(60.0f)), View.MeasureSpec.getMode(r4)));
        }
    }

    public static final class Factory extends UItem.UItemFactory<ProfileHoursCell> {
        static {
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            ProfileHoursCell r12 = (ProfileHoursCell) r1;
            r12.setId(r2.id);
            r12.setOnTimezoneSwitchClick(r2.clickCallback);
            r12.set((TL_account.TL_businessWorkHours) r2.object, r2.locked, r2.accent, r3);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean contentsEquals(UItem r2, UItem r3) {
            if (r2.accent == r3.accent) goto L5;
            return false;
        L5:
            if (r2.locked != r3.locked) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public ProfileHoursCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return new ProfileHoursCell(r1, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean equals(UItem r1, UItem r2) {
            if (r1.id != r2.id) goto L6;
            return true;
        L6:
            return false;
        }
    }

    public ProfileHoursCell(Context r25, Theme.ResourcesProvider r26) {
        super(r25);
        this.labelTimeText = new TextView[2];
        int r4 = 7;
        this.lines = new ViewGroup[7];
        this.labelText = new TextView[7];
        this.timeText = new TextView[7][];
        this.todayLinesCount = 1;
        this.todayLinesHeight = 0;
        this.firstAfterAttach = true;
        this.resourcesProvider = r26;
        setOrientation(1);
        setClipChildren(false);
        int r7 = 0;
    L3:
        if (r7 >= r4) goto L61;
        if (r7 != 0) goto L33;
        AnonymousClass1 r12 = new AnonymousClass1(this, r25);
        r12.setMinimumHeight(AndroidUtilities.dp(60.0f));
        TextView r13 = new TextView(r25);
        this.textView = r13;
        if (LocaleController.isRTL == false) goto L9;
        int r14 = 5;
    L10:
        r13.setGravity(r14);
        this.textView.setTextSize(1, 16.0f);
        r12.addView(this.textView, LayoutHelper.createFrameRelatively(-1.0f, -2.0f, 8388659, 0.0f, 9.33f, 0.0f, 0.0f));
        this.labelText[r7] = new TextView(r25);
        TextView r132 = this.labelText[r7];
        if (LocaleController.isRTL == false) goto L13;
        int r142 = 5;
    L14:
        r132.setGravity(r142);
        this.labelText[r7].setTextSize(1, 13.0f);
        this.labelText[r7].setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r26));
        r12.addView(this.labelText[r7], LayoutHelper.createFrameRelatively(-2.0f, -2.0f, 8388659, 0.0f, 33.0f, 0.0f, 10.0f));
        LinearLayout r133 = new LinearLayout(r25);
        this.todayTimeTextContainer2 = r133;
        r133.setOrientation(1);
        this.todayTimeTextContainer = new FrameLayout(r25);
        this.timeText[r7] = new TextView[2];
        int r134 = 0;
    L15:
        if (r134 >= 2) goto L21;
        this.timeText[r7][r134] = new TextView(r25);
        this.timeText[r7][r134].setTextSize(1, 14.0f);
        this.timeText[r7][r134].setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r26));
        TextView r42 = this.timeText[r7][r134];
        if (LocaleController.isRTL == false) goto L19;
        int r15 = 3;
    L20:
        r42.setGravity(r15);
        this.todayTimeTextContainer.addView(this.timeText[r7][r134], LayoutHelper.createFrameRelatively(-1.0f, -1.0f, 119, 0.0f, 0.0f, 20.0f, 0.0f));
        r134 = r134 + 1;
        goto L15
    L19:
        r15 = 5;
        goto L20
    L21:
        int r43 = 0;
    L22:
        if (r43 >= 2) goto L28;
        this.labelTimeText[r43] = new TextView(r25);
        this.labelTimeText[r43].setTextSize(1, 14.0f);
        this.labelTimeText[r43].setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r26));
        TextView r10 = this.labelTimeText[r43];
        if (LocaleController.isRTL == false) goto L26;
        int r135 = 3;
    L27:
        r10.setGravity(r135);
        this.todayTimeTextContainer.addView(this.labelTimeText[r43], LayoutHelper.createFrameRelatively(-1.0f, -1.0f, 119, 0.0f, 0.0f, 20.0f, 0.0f));
        r43 = r43 + 1;
        goto L22
    L26:
        r135 = 5;
        goto L27
    L28:
        ImageView r44 = new ImageView(r25);
        this.arrowView = r44;
        r44.setScaleType(ImageView.ScaleType.CENTER);
        this.arrowView.setScaleX(0.6f);
        this.arrowView.setScaleY(0.6f);
        this.arrowView.setImageResource(R.drawable.arrow_more);
        this.arrowView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r26), PorterDuff.Mode.SRC_IN));
        this.todayTimeTextContainer.addView(this.arrowView, LayoutHelper.createFrameRelatively(20.0f, 20.0f, 8388629));
        this.todayTimeTextContainer2.addView(this.todayTimeTextContainer, LayoutHelper.createLinearRelatively(-1.0f, -1.0f, 119));
        ClickableAnimatedTextView r45 = new ClickableAnimatedTextView(r25);
        this.switchText = r45;
        r45.getDrawable().updateAll = true;
        this.switchText.setTextSize(AndroidUtilities.dp(13.0f));
        this.switchText.setPadding(AndroidUtilities.dp(6.0f), 0, AndroidUtilities.dp(6.0f), 0);
        ClickableAnimatedTextView r46 = this.switchText;
        if (LocaleController.isRTL == false) goto L31;
        int r102 = 3;
    L32:
        r46.setGravity(r102);
        ClickableAnimatedTextView r47 = this.switchText;
        int r8 = AndroidUtilities.dp(8.0f);
        int r103 = Theme.key_windowBackgroundWhiteBlueText2;
        r47.setBackground(Theme.createSimpleSelectorRoundRectDrawable(r8, Theme.multAlpha(processColor(Theme.getColor(r103, r26)), 0.1f), Theme.multAlpha(processColor(Theme.getColor(r103, r26)), 0.22f)));
        this.switchText.setTextColor(processColor(Theme.getColor(r103, r26)));
        this.switchText.getDrawable().setScaleProperty(0.6f);
        this.switchText.setVisibility(8);
        this.todayTimeTextContainer2.addView(this.switchText, LayoutHelper.createLinearRelatively(-1.0f, 17.0f, 8388613, 0.0f, 4.0f, 18.0f, 0.0f));
        FrameLayout r48 = new FrameLayout(r25);
        this.todayTimeContainer = r48;
        r48.addView(this.todayTimeTextContainer2, LayoutHelper.createFrameRelatively(-1.0f, -2.0f, 8388693, 0.0f, 0.0f, 0.0f, 0.0f));
        r12.addView(this.todayTimeContainer, LayoutHelper.createFrameRelatively(-1.0f, -2.0f, 8388693, 0.0f, 0.0f, 0.0f, 12.0f));
        this.lines[r7] = r12;
        addView(r12, LayoutHelper.createFrameRelatively(-1.0f, -2.0f, 51, 18.0f, 0.0f, 8.0f, 0.0f));
    L60:
        r7 = r7 + 1;
        r4 = 7;
        goto L3
    L31:
        r102 = 5;
        goto L32
    L13:
        r142 = 3;
        goto L14
    L9:
        r14 = 3;
        goto L10
    L33:
        LinearLayout r49 = new LinearLayout(r25);
        r49.setOrientation(0);
        this.labelText[r7] = new TextView(r25);
        this.labelText[r7].setTextSize(1, 14.0f);
        this.labelText[r7].setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r26));
        TextView r104 = this.labelText[r7];
        if (LocaleController.isRTL == false) goto L36;
        int r122 = 5;
    L37:
        r104.setGravity(r122);
        FrameLayout r105 = new FrameLayout(r25);
        this.timeText[r7] = new TextView[2];
        int r123 = 0;
    L39:
        if (r123 >= 2) goto L46;
        this.timeText[r7][r123] = new TextView(r25);
        this.timeText[r7][r123].setTextSize(1, 14.0f);
        this.timeText[r7][r123].setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r26));
        TextView r143 = this.timeText[r7][r123];
        if (LocaleController.isRTL == false) goto L43;
        int r152 = 3;
    L44:
        r143.setGravity(r152);
        r105.addView(this.timeText[r7][r123], LayoutHelper.createFrame(-1, -1, 119));
        r123 = r123 + 1;
        goto L39
    L43:
        r152 = 5;
        goto L44
    L46:
        if (LocaleController.isRTL == false) goto L48;
        r49.addView(r105, LayoutHelper.createLinear(-2, -1, 51));
        r49.addView(this.labelText[r7], LayoutHelper.createLinear(-1, -1, 53));
    L49:
        this.lines[r7] = r49;
        if (r7 != 1) goto L53;
        float r82 = 1.0f;
    L52:
        float r136 = r82;
        if (r7 != 6) goto L58;
        float r83 = 16.66f;
    L59:
        addView(r49, LayoutHelper.createLinearRelatively(-1.0f, -2.0f, 51, 18.0f, r136, 28.0f, r83));
        goto L60
    L58:
        r83 = 0.0f;
        goto L59
    L53:
        r82 = 11.66f;
        goto L52
    L48:
        r49.addView(this.labelText[r7], LayoutHelper.createLinear(-2, -1, 51));
        r49.addView(r105, LayoutHelper.createLinear(-1, -1, 53));
        goto L49
    L36:
        r122 = 3;
        goto L37
    L61:
        setWillNotDraw(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r1) {
        return super.dispatchTouchEvent(r1);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas r8) {
        super.onDraw(r8);
        if (this.needDivider == false) goto L17;
        Paint r0 = Theme.getThemePaint("paintDivider", this.resourcesProvider);
        if (r0 != null) goto L7;
        r0 = Theme.dividerPaint;
    L7:
        Paint r6 = r0;
        float r1 = 21.33f;
        if (LocaleController.isRTL == false) goto L10;
        float r02 = 0.0f;
    L11:
        float r03 = AndroidUtilities.dp(r02);
        float r3 = getMeasuredHeight() - 1;
        int r4 = getWidth();
        if (LocaleController.isRTL == true) goto L15;
        r1 = 0.0f;
    L15:
        r8.drawRect(r03, r3, r4 - AndroidUtilities.dp(r1), getMeasuredHeight(), r6);
        return;
    L10:
        r02 = 21.33f;
        goto L11
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r4, int r5) {
        int r42 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r4), TLObject.FLAG_30);
        if (this.expanded == true) goto L17;
        int r52 = AndroidUtilities.dp(60.0f);
        if (this.todayLinesCount <= 2) goto L8;
    L11:
        int r1 = this.todayLinesHeight + AndroidUtilities.dp(15.0f);
        if (this.switchText.getVisibility() != 0) goto L14;
        float r2 = 21.0f;
    L15:
        int r12 = r1 + AndroidUtilities.dp(r2);
    L16:
        r5 = View.MeasureSpec.makeMeasureSpec(Math.max(r52, r12) + (this.needDivider ? 1 : 0), TLObject.FLAG_30);
        goto L17
    L14:
        r2 = 0.0f;
        goto L15
    L8:
        if (this.switchText.getVisibility() == 0) goto L11;
        r12 = 0;
    L17:
        super.onMeasure(r42, r5);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r4) {
        ClickableAnimatedTextView r0 = this.switchText;
        if (r0 == null) goto L9;
        if (r0.getVisibility() != 0) goto L9;
        float r02 = (((r4.getX() - this.lines[0].getX()) - this.todayTimeContainer.getX()) - this.todayTimeTextContainer.getX()) - this.switchText.getX();
        float r42 = (((r4.getY() - this.lines[0].getY()) - this.todayTimeContainer.getY()) - this.todayTimeTextContainer.getY()) - this.switchText.getY();
        return this.switchText.getClickBounds().contains((int) r02, (int) r42);
    L9:
        return super.onTouchEvent(r4);
    }

    public int processColor(int r1) {
        return r1;
    }

    public void set(TL_account.TL_businessWorkHours r24, boolean r25, boolean r26, boolean r27) {
        boolean r2 = r25;
        this.expanded = r2;
        this.needDivider = r27;
        if (r24 == null) goto L265;
        boolean r3 = OpeningHoursActivity.is24x7(r24);
        int r4 = 0;
        if (r3 == false) goto L8;
        this.expanded = false;
        r2 = false;
    L8:
        ImageView r5 = this.arrowView;
        int r6 = 8;
        if (r3 == false) goto L11;
        int r7 = 8;
    L12:
        r5.setVisibility(r7);
        LinearLayout r52 = this.todayTimeTextContainer2;
        if (r3 == false) goto L15;
        float r8 = AndroidUtilities.dp(11.0f);
    L16:
        r52.setTranslationX(r8);
        TLRPC.TL_timezone r53 = TimezonesController.getInstance(UserConfig.selectedAccount).findTimezone(r24.timezone_id);
        Calendar r82 = Calendar.getInstance();
        int r9 = r82.getTimeZone().getOffset(System.currentTimeMillis()) / 1000;
        if (r53 != null) goto L19;
        int r54 = 0;
    L20:
        int r92 = r9 - r54;
        int r55 = 60;
        int r93 = r92 / 60;
        ClickableAnimatedTextView r10 = this.switchText;
        if (r93 == 0) goto L24;
        if (r3 == true) goto L24;
        r6 = 0;
    L24:
        r10.setVisibility(r6);
        if (r93 != 0) goto L27;
        boolean r62 = false;
    L28:
        invalidate();
        boolean r102 = this.firstAfterAttach;
        TextView[] r11 = this.labelTimeText;
        if (r102 == false) goto L45;
        TextView r103 = r11[0];
        if (r2 == true) goto L34;
        if (r62 == true) goto L34;
        float r112 = 1.0f;
    L35:
        r103.setAlpha(r112);
        TextView r104 = this.labelTimeText[1];
        if (r2 == true) goto L39;
        if (r62 == false) goto L39;
        float r113 = 1.0f;
    L40:
        r104.setAlpha(r113);
        ImageView r105 = this.arrowView;
        if (r2 == false) goto L43;
        float r12 = 180.0f;
    L44:
        r105.setRotation(r12);
    L68:
        int r106 = 0;
    L70:
        if (r106 >= this.timeText.length) goto L89;
        int r114 = 0;
    L72:
        TextView[] r122 = this.timeText[r106];
        if (r114 >= r122.length) goto L88;
        if (r106 != 0) goto L76;
        if (r2 == true) goto L76;
    L82:
        float r15 = 0.0f;
    L81:
        int r272 = r55;
        if (this.firstAfterAttach == false) goto L86;
        r122[r114].setAlpha(r15);
    L87:
        r114 = r114 + 1;
        r55 = r272;
        goto L72
    L86:
        r122[r114].animate().alpha(r15).setDuration(320).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
    L76:
        if (r114 != 1) goto L78;
        boolean r152 = true;
    L79:
        if (r152 != r62) goto L82;
        r15 = 1.0f;
        goto L81
    L78:
        r152 = false;
        goto L79
    L88:
        r106 = r106 + 1;
        goto L70
    L89:
        int r273 = r55;
        ClickableAnimatedTextView r56 = this.switchText;
        if (r56 == null) goto L101;
        if (r62 == false) goto L93;
        int r63 = R.string.BusinessHoursProfileSwitchMy;
    L94:
        String r64 = LocaleController.getString(r63);
        if (LocaleController.isRTL == false) goto L97;
    L99:
        boolean r107 = false;
    L100:
        r56.setText(r64, r107);
        goto L101
    L97:
        if (this.firstAfterAttach == true) goto L99;
        r107 = true;
        goto L100
    L93:
        r63 = R.string.BusinessHoursProfileSwitchLocal;
    L101:
        this.firstAfterAttach = false;
        ArrayList<OpeningHoursActivity.Period>[] r57 = OpeningHoursActivity.getDaysHours(new ArrayList(r24.weekly_open));
        int r65 = 7;
        int r108 = (r82.get(7) + 5) % 7;
        int r123 = r82.get(11);
        int r83 = r82.get(12);
        ArrayList<TL_account.TL_businessWeeklyOpen> r1 = OpeningHoursActivity.adaptWeeklyOpen(r24.weekly_open, r93);
        int r84 = (r83 + (r123 * 60)) + (r108 * 1440);
        int r94 = 0;
    L103:
        if (r94 >= r1.size()) goto L118;
        TL_account.TL_businessWeeklyOpen r124 = r1.get(r94);
        int r13 = r124.start_minute;
        if (r84 >= r13) goto L107;
    L108:
        int r14 = r84 + 10080;
        if (r14 >= r13) goto L111;
    L112:
        int r142 = r84 - 10080;
        if (r142 < r13) goto L117;
        if (r142 > r124.end_minute) goto L117;
    L116:
        boolean r95 = true;
    L119:
        ArrayList<OpeningHoursActivity.Period>[] r125 = OpeningHoursActivity.getDaysHours(r1);
        TextView r132 = this.textView;
        if (r95 == false) goto L122;
        int r143 = R.string.BusinessHoursProfileNowOpen;
    L123:
        r132.setText(LocaleController.getString(r143));
        TextView r133 = this.textView;
        if (r95 == false) goto L126;
        int r144 = Theme.key_avatar_nameInMessageGreen;
    L127:
        r133.setTextColor(Theme.getColor(r144, this.resourcesProvider));
        int r134 = this.todayLinesHeight;
        int r145 = this.todayLinesCount;
        this.todayLinesCount = 1;
        this.todayLinesHeight = 0;
        int r153 = 0;
    L129:
        if (r153 >= 2) goto L208;
        if (r153 != 0) goto L132;
        ArrayList<OpeningHoursActivity.Period>[] r16 = r57;
    L133:
        int r115 = r4;
    L134:
        if (r115 >= r65) goto L207;
        int r17 = (r108 + r115) % 7;
        if (r115 != 0) goto L138;
        this.labelText[r115].setText(LocaleController.getString(R.string.BusinessHoursProfile));
        boolean r18 = r2;
    L150:
        int r22 = 0;
    L151:
        if (r115 != 0) goto L153;
        int r72 = 2;
    L154:
        if (r22 >= r72) goto L206;
        if (r22 != 0) goto L157;
        TextView r42 = this.timeText[r115][r153];
    L158:
        if (r115 != 0) goto L187;
        if (r95 == true) goto L187;
        if (r22 != 1) goto L187;
        int r66 = 0;
    L163:
        int r21 = r22;
        if (r66 >= r1.size()) goto L169;
        int r73 = r1.get(r66).start_minute;
        if (r84 < r73) goto L170;
        r66 = r66 + 1;
        r22 = r21;
    L170:
        if (r73 == (-1)) goto L172;
    L174:
        if (r73 != (-1)) goto L177;
        r42.setText(LocaleController.getString(R.string.BusinessHoursProfileClose));
        ArrayList<TL_account.TL_businessWeeklyOpen> r222 = r1;
    L205:
        r22 = r21 + 1;
        r1 = r222;
        r273 = 60;
        goto L151
    L177:
        if (r73 >= r84) goto L180;
        int r74 = r73 + (10080 - r84);
    L181:
        if (r74 >= r273) goto L183;
        r222 = r1;
        r42.setText(LocaleController.formatPluralString("BusinessHoursProfileOpensInMinutes", r74, new Object[0]));
        goto L205
    L183:
        r222 = r1;
        if (r74 >= 1440) goto L186;
        r42.setText(LocaleController.formatPluralString("BusinessHoursProfileOpensInHours", (int) Math.ceil(r74 / 60.0f), new Object[0]));
        goto L205
    L186:
        r42.setText(LocaleController.formatPluralString("BusinessHoursProfileOpensInDays", (int) Math.ceil((r74 / 60.0f) / 24.0f), new Object[0]));
        goto L205
    L180:
        r74 = r73 - r84;
        goto L181
    L172:
        if (r1.isEmpty() == true) goto L174;
        r73 = r1.get(0).start_minute;
        goto L174
    L169:
        r73 = -1;
    L187:
        r222 = r1;
        r21 = r22;
        if (r3 == false) goto L191;
        r42.setText(LocaleController.getString(R.string.BusinessHoursProfileFullOpen));
        goto L205
    L191:
        if (r16[r17].isEmpty() == false) goto L194;
        r42.setText(LocaleController.getString(R.string.BusinessHoursProfileClose));
        goto L205
    L194:
        if (OpeningHoursActivity.isFull(r16[r17]) == false) goto L196;
        r42.setText(LocaleController.getString(R.string.BusinessHoursProfileOpen));
        goto L205
    L196:
        StringBuilder r19 = new StringBuilder();
        int r67 = 0;
    L198:
        if (r67 >= r16[r17].size()) goto L202;
        if (r67 <= 0) goto L201;
        r19.append("\n");
    L201:
        r19.append(r16[r17].get(r67));
        r67 = r67 + 1;
        goto L198
    L202:
        int r68 = r16[r17].size();
        r42.setText(r19);
        if (r115 != 0) goto L205;
        this.todayLinesCount = Math.max(this.todayLinesCount, r68);
        this.todayLinesHeight = Math.max(this.todayLinesHeight, r42.getLineHeight() * r68);
        goto L205
    L157:
        r42 = this.labelTimeText[r153];
        goto L158
    L206:
        r115 = r115 + 1;
        r4 = 0;
        r2 = r18;
        r273 = 60;
        r65 = 7;
        goto L134
    L153:
        r72 = 1;
        goto L154
    L138:
        String r43 = DayOfWeek.values()[r17].getDisplayName(TextStyle.FULL, LocaleController.getInstance().getCurrentLocale());
        StringBuilder r69 = new StringBuilder();
        r18 = r2;
        r69.append(r43.substring(0, 1).toUpperCase());
        r69.append(r43.substring(1));
        this.labelText[r115].setText(r69.toString());
        TextView r23 = this.timeText[r115][0];
        int r44 = 4;
        if (r18 == false) goto L141;
        int r610 = 0;
    L142:
        r23.setVisibility(r610);
        TextView r28 = this.timeText[r115][1];
        if (r18 == false) goto L145;
        int r611 = 0;
    L146:
        r28.setVisibility(r611);
        TextView r29 = this.labelText[r115];
        if (r18 == false) goto L149;
        r44 = 0;
    L149:
        r29.setVisibility(r44);
        goto L150
    L145:
        r611 = 4;
        goto L146
    L141:
        r610 = 4;
        goto L142
    L207:
        r153 = r153 + 1;
        r2 = r2;
        r273 = 60;
        r65 = 7;
        goto L129
    L132:
        r16 = r125;
        goto L133
    L208:
        FrameLayout.LayoutParams r110 = (FrameLayout.LayoutParams) this.todayTimeContainer.getLayoutParams();
        float r32 = 6.0f;
        if (this.todayLinesCount <= 2) goto L211;
    L214:
        float r210 = 6.0f;
    L215:
        r110.topMargin = AndroidUtilities.dp(r210);
        if (this.todayLinesCount <= 2) goto L218;
    L221:
        r110.bottomMargin = AndroidUtilities.dp(r32);
        if (this.todayLinesCount <= 2) goto L224;
    L227:
        int r211 = 16;
    L229:
        if (LocaleController.isRTL == false) goto L231;
        int r116 = 3;
    L232:
        r110.gravity = r211 | r116;
        if (r145 == this.todayLinesCount) goto L235;
    L238:
        requestLayout();
        return;
    L235:
        if (r134 != this.todayLinesHeight) goto L238;
        return;
    L231:
        r116 = 5;
        goto L232
    L224:
        if (this.switchText.getVisibility() == 0) goto L227;
        r211 = 80;
        goto L229
    L218:
        if (this.switchText.getVisibility() == 0) goto L221;
        r32 = 12.0f;
        goto L221
    L211:
        if (this.switchText.getVisibility() == 0) goto L214;
        r210 = 12.0f;
        goto L215
    L126:
        r144 = Theme.key_text_RedRegular;
        goto L127
    L122:
        r143 = R.string.BusinessHoursProfileNowClosed;
    L117:
        r94 = r94 + 1;
        goto L103
    L111:
        if (r14 <= r124.end_minute) goto L116;
    L107:
        if (r84 <= r124.end_minute) goto L116;
    L118:
        r95 = false;
        goto L119
    L43:
        r12 = 0.0f;
    L39:
        r113 = 0.0f;
    L34:
        r112 = 0.0f;
        goto L35
    L45:
        ViewPropertyAnimator r109 = r11[0].animate();
        if (r2 == true) goto L49;
        if (r62 == true) goto L49;
        float r117 = 1.0f;
    L50:
        ViewPropertyAnimator r1010 = r109.alpha(r117).setDuration(320);
        CubicBezierInterpolator r118 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r1010.setInterpolator(r118).start();
        ViewPropertyAnimator r1011 = this.labelTimeText[1].animate();
        if (r2 == true) goto L54;
        if (r62 == false) goto L54;
        float r126 = 1.0f;
    L55:
        r1011.alpha(r126).setDuration(320).setInterpolator(r118).start();
        ViewPropertyAnimator r1012 = this.timeText[0][0].animate();
        if (r2 == false) goto L58;
        float r127 = 1.0f;
    L59:
        r1012.alpha(r127).setDuration(320).setInterpolator(r118).start();
        ViewPropertyAnimator r1013 = this.timeText[0][1].animate();
        if (r2 == false) goto L62;
        float r128 = 1.0f;
    L63:
        r1013.alpha(r128).setDuration(320).setInterpolator(r118).start();
        ViewPropertyAnimator r1014 = this.arrowView.animate();
        if (r2 == false) goto L66;
        float r129 = 180.0f;
    L67:
        r1014.rotation(r129).setDuration(320).setInterpolator(r118).start();
        goto L68
    L66:
        r129 = 0.0f;
        goto L67
    L62:
        r128 = 0.0f;
        goto L63
    L58:
        r127 = 0.0f;
    L54:
        r126 = 0.0f;
    L49:
        r117 = 0.0f;
        goto L50
    L27:
        r62 = r26;
        goto L28
    L19:
        r54 = r53.utc_offset;
        goto L20
    L15:
        r8 = 0.0f;
        goto L16
    L11:
        r7 = 0;
        goto L12
    }

    public void setOnTimezoneSwitchClick(View.OnClickListener r1) {
        ClickableAnimatedTextView r0 = this.switchText;
        if (r0 == null) goto L6;
        r0.setOnClickListener(r1);
        return;
    }

    public void updateColors() {
        ClickableAnimatedTextView r0 = this.switchText;
        int r1 = AndroidUtilities.dp(8.0f);
        int r2 = Theme.key_windowBackgroundWhiteBlueText2;
        r0.setBackground(Theme.createSimpleSelectorRoundRectDrawable(r1, Theme.multAlpha(processColor(Theme.getColor(r2, this.resourcesProvider)), 0.1f), Theme.multAlpha(processColor(Theme.getColor(r2, this.resourcesProvider)), 0.22f)));
        this.switchText.setTextColor(processColor(Theme.getColor(r2, this.resourcesProvider)));
    }
}
