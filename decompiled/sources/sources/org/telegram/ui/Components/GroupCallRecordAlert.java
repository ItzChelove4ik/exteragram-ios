package org.telegram.ui.Components;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.GradientDrawable;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public abstract class GroupCallRecordAlert extends BottomSheet {
    private int currentPage;
    private float pageOffset;
    private TextView positiveButton;
    private TextView[] titles;
    private LinearLayout titlesLayout;
    private ViewPager viewPager;

    /* renamed from: org.telegram.ui.Components.GroupCallRecordAlert$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        boolean ignoreLayout;
        final /* synthetic */ GroupCallRecordAlert this$0;

        public AnonymousClass1(GroupCallRecordAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            GroupCallRecordAlert.m10863$$Nest$mupdateTitlesLayout(this.this$0);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r9, int r10) {
            if (View.MeasureSpec.getSize(r9) <= View.MeasureSpec.getSize(r10)) goto L5;
            boolean r102 = true;
        L6:
            ViewGroup.MarginLayoutParams r0 = (ViewGroup.MarginLayoutParams) GroupCallRecordAlert.m10857$$Nest$fgetpositiveButton(this.this$0).getLayoutParams();
            if (r102 == false) goto L9;
            int r103 = AndroidUtilities.dp(80.0f);
            r0.leftMargin = r103;
            r0.rightMargin = r103;
        L10:
            int r104 = (View.MeasureSpec.getSize(r9) - AndroidUtilities.dp(200.0f)) / 2;
            GroupCallRecordAlert.m10860$$Nest$fgetviewPager(this.this$0).setPadding(r104, 0, r104, 0);
            super.onMeasure(r9, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(370.0f), TLObject.FLAG_30));
            measureChildWithMargins(GroupCallRecordAlert.m10859$$Nest$fgettitlesLayout(this.this$0), View.MeasureSpec.makeMeasureSpec(0, 0), 0, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64.0f), TLObject.FLAG_30), 0);
            return;
        L9:
            int r105 = AndroidUtilities.dp(16.0f);
            r0.leftMargin = r105;
            r0.rightMargin = r105;
            goto L10
        L5:
            r102 = false;
            goto L6
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.GroupCallRecordAlert$2, reason: invalid class name */
    public class AnonymousClass2 implements ViewPager.OnPageChangeListener {
        final /* synthetic */ GroupCallRecordAlert this$0;

        public AnonymousClass2(GroupCallRecordAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int r1) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int r1, float r2, int r3) {
            GroupCallRecordAlert.m10861$$Nest$fputcurrentPage(this.this$0, r1);
            GroupCallRecordAlert.m10862$$Nest$fputpageOffset(this.this$0, r2);
            GroupCallRecordAlert.m10863$$Nest$mupdateTitlesLayout(this.this$0);
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int r1) {
        }
    }

    /* renamed from: org.telegram.ui.Components.GroupCallRecordAlert$3, reason: invalid class name */
    public class AnonymousClass3 extends TextView {
        private Paint[] gradientPaint;
        final /* synthetic */ GroupCallRecordAlert this$0;

        public AnonymousClass3(GroupCallRecordAlert r3, Context r4) {
            this.this$0 = r3;
            super(r4);
            this.gradientPaint = new Paint[GroupCallRecordAlert.m10858$$Nest$fgettitles(r3).length];
            int r32 = 0;
        L3:
            Paint[] r42 = this.gradientPaint;
            if (r32 >= r42.length) goto L6;
            r42[r32] = new Paint(1);
            r32 = r32 + 1;
            goto L3
        }

        @Override // android.widget.TextView, android.view.View
        public void onDraw(Canvas r8) {
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            this.gradientPaint[GroupCallRecordAlert.m10855$$Nest$fgetcurrentPage(this.this$0)].setAlpha(255);
            r8.drawRoundRect(r0, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.gradientPaint[GroupCallRecordAlert.m10855$$Nest$fgetcurrentPage(this.this$0)]);
            if (GroupCallRecordAlert.m10856$$Nest$fgetpageOffset(this.this$0) <= 0.0f) goto L7;
            int r2 = GroupCallRecordAlert.m10855$$Nest$fgetcurrentPage(this.this$0) + 1;
            Paint[] r3 = this.gradientPaint;
            if (r2 >= r3.length) goto L7;
            r3[GroupCallRecordAlert.m10855$$Nest$fgetcurrentPage(this.this$0) + 1].setAlpha((int) (GroupCallRecordAlert.m10856$$Nest$fgetpageOffset(this.this$0) * 255.0f));
            r8.drawRoundRect(r0, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.gradientPaint[GroupCallRecordAlert.m10855$$Nest$fgetcurrentPage(this.this$0) + 1]);
        L7:
            super.onDraw(r8);
        }

        @Override // android.view.View
        public void onSizeChanged(int r11, int r12, int r13, int r14) {
            super.onSizeChanged(r11, r12, r13, r14);
            int r122 = 0;
        L4:
            if (r122 >= this.gradientPaint.length) goto L17;
            int r132 = -9015575;
            if (r122 != 0) goto L10;
            int r142 = -9015575;
            r132 = -11033346;
        L8:
            int r0 = 0;
        L13:
            if (r0 == 0) goto L15;
            LinearGradient r1 = new LinearGradient(0.0f, 0.0f, getMeasuredWidth(), 0.0f, new int[]{r132, r142, r0}, null, Shader.TileMode.CLAMP);
        L16:
            this.gradientPaint[r122].setShader(r1);
            r122 = r122 + 1;
            goto L4
        L15:
            r1 = new LinearGradient(0.0f, 0.0f, getMeasuredWidth(), 0.0f, new int[]{r132, r142}, null, Shader.TileMode.CLAMP);
            goto L16
        L10:
            if (r122 != 1) goto L12;
            r132 = -8919716;
            r142 = -11089922;
            goto L8
        L12:
            r142 = -1026983;
            r0 = -1792170;
            goto L13
        }
    }

    public class Adapter extends PagerAdapter {
        final /* synthetic */ GroupCallRecordAlert this$0;

        /* renamed from: org.telegram.ui.Components.GroupCallRecordAlert$Adapter$1, reason: invalid class name */
        public class AnonymousClass1 extends ImageView {
            final /* synthetic */ Adapter this$1;
            final /* synthetic */ int val$position;

            public AnonymousClass1(Adapter r1, Context r2, int r3) {
                this.this$1 = r1;
                this.val$position = r3;
                super(r2);
            }

            @Override // android.view.View
            public void onInitializeAccessibilityEvent(AccessibilityEvent r2) {
                super.onInitializeAccessibilityEvent(r2);
                if (r2.getEventType() != 32768) goto L6;
                GroupCallRecordAlert.m10860$$Nest$fgetviewPager(this.this$1.this$0).setCurrentItem(this.val$position, true);
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$A2zmTQlnSQF0KQg9WPgz5qCImaI(Adapter r0, int r1, View r2) {
            r0.lambda$instantiateItem$0(r1, r2);
        }

        private Adapter(GroupCallRecordAlert r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Adapter(GroupCallRecordAlert r1, GroupCallRecordAlertIA r2) {
            this(r1);
        }

        private /* synthetic */ void lambda$instantiateItem$0(int r1, View r2) {
            this.this$0.onStartRecord(r1);
            this.this$0.dismiss();
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup r1, int r2, Object r3) {
            r1.removeView((View) r3);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return GroupCallRecordAlert.m10858$$Nest$fgettitles(this.this$0).length;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup r6, int r7) {
            AnonymousClass1 r0 = new AnonymousClass1(this, this.this$0.getContext(), r7);
            r0.setOnClickListener(new GroupCallRecordAlert$Adapter$$ExternalSyntheticLambda0(this, r7));
            r0.setFocusable(true);
            r0.setTag(Integer.valueOf(r7));
            r0.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
            r0.setScaleType(ImageView.ScaleType.FIT_XY);
            r0.setLayoutParams(new ViewGroup.LayoutParams(AndroidUtilities.dp(200.0f), -1));
            if (r7 != 0) goto L5;
            r0.setContentDescription(LocaleController.getString(R.string.VoipRecordAudio));
        L8:
            if (r7 != 0) goto L10;
            int r5 = R.raw.record_audio;
        L13:
            SvgHelper.SvgDrawable r52 = SvgHelper.getDrawable(AndroidUtilities.readRes(r5));
            r52.setAspectFill(false);
            r0.setImageDrawable(r52);
            if (r0.getParent() == null) goto L16;
            ((ViewGroup) r0.getParent()).removeView(r0);
        L16:
            r6.addView(r0, 0);
            return r0;
        L10:
            if (r7 != 1) goto L12;
            r5 = R.raw.record_video_p;
            goto L13
        L12:
            r5 = R.raw.record_video_l;
            goto L13
        L5:
            if (r7 != 1) goto L7;
            r0.setContentDescription(LocaleController.getString(R.string.VoipRecordPortrait));
            goto L8
        L7:
            r0.setContentDescription(LocaleController.getString(R.string.VoipRecordLandscape));
            goto L8
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View r1, Object r2) {
            return r1.equals(r2);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void restoreState(Parcelable r1, ClassLoader r2) {
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Parcelable saveState() {
            return null;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void setPrimaryItem(ViewGroup r1, int r2, Object r3) {
            super.setPrimaryItem(r1, r2, r3);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void unregisterDataSetObserver(DataSetObserver r1) {
            if (r1 == null) goto L5;
            super.unregisterDataSetObserver(r1);
            return;
        }
    }

    /* renamed from: $r8$lambda$N-tRfnByPC00LZr_m82Z0Bc5ThE, reason: not valid java name */
    public static /* synthetic */ void m10854$r8$lambda$NtRfnByPC00LZr_m82Z0Bc5ThE(GroupCallRecordAlert r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$rZ1EbhjBFmcbztvzTefzhogdIxA(GroupCallRecordAlert r0, int r1, View r2) {
        r0.lambda$new$1(r1, r2);
    }

    /* renamed from: -$$Nest$fgetcurrentPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10855$$Nest$fgetcurrentPage(GroupCallRecordAlert r0) {
        return r0.currentPage;
    }

    /* renamed from: -$$Nest$fgetpageOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m10856$$Nest$fgetpageOffset(GroupCallRecordAlert r0) {
        return r0.pageOffset;
    }

    /* renamed from: -$$Nest$fgetpositiveButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m10857$$Nest$fgetpositiveButton(GroupCallRecordAlert r0) {
        return r0.positiveButton;
    }

    /* renamed from: -$$Nest$fgettitles, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView[] m10858$$Nest$fgettitles(GroupCallRecordAlert r0) {
        return r0.titles;
    }

    /* renamed from: -$$Nest$fgettitlesLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m10859$$Nest$fgettitlesLayout(GroupCallRecordAlert r0) {
        return r0.titlesLayout;
    }

    /* renamed from: -$$Nest$fgetviewPager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewPager m10860$$Nest$fgetviewPager(GroupCallRecordAlert r0) {
        return r0.viewPager;
    }

    /* renamed from: -$$Nest$fputcurrentPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10861$$Nest$fputcurrentPage(GroupCallRecordAlert r0, int r1) {
        r0.currentPage = r1;
    }

    /* renamed from: -$$Nest$fputpageOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10862$$Nest$fputpageOffset(GroupCallRecordAlert r0, float r1) {
        r0.pageOffset = r1;
    }

    /* renamed from: -$$Nest$mupdateTitlesLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10863$$Nest$mupdateTitlesLayout(GroupCallRecordAlert r0) {
        r0.updateTitlesLayout();
    }

    public GroupCallRecordAlert(Context r19, TLRPC.Chat r20, boolean r21) {
        super(r19, false);
        int r3 = Theme.getColor(Theme.key_voipgroup_inviteMembersBackground);
        this.shadowDrawable.setColorFilter(new PorterDuffColorFilter(r3, PorterDuff.Mode.MULTIPLY));
        AnonymousClass1 r4 = new AnonymousClass1(this, r19);
        this.containerView = r4;
        r4.setWillNotDraw(false);
        this.containerView.setClipChildren(false);
        this.containerView.setBackgroundDrawable(this.shadowDrawable);
        ViewGroup r42 = this.containerView;
        int r5 = this.backgroundPaddingLeft;
        r42.setPadding(r5, 0, r5, 0);
        TextView r43 = new TextView(getContext());
        if (ChatObject.isChannelOrGiga(r20) == false) goto L5;
        r43.setText(LocaleController.getString(R.string.VoipChannelRecordVoiceChat));
    L6:
        r43.setTextColor(-1);
        r43.setTextSize(1, 20.0f);
        r43.setTypeface(AndroidUtilities.bold());
        int r8 = 5;
        if (LocaleController.isRTL == false) goto L9;
        int r6 = 5;
    L10:
        r43.setGravity(r6 | 48);
        ViewGroup r62 = this.containerView;
        if (LocaleController.isRTL == false) goto L13;
        int r10 = 5;
    L14:
        r62.addView(r43, LayoutHelper.createFrame(-2, -2.0f, r10 | 48, 24.0f, 29.0f, 24.0f, 0.0f));
        TextView r44 = new TextView(getContext());
        r44.setText(LocaleController.getString(R.string.VoipRecordVoiceChatInfo));
        r44.setTextColor(-1);
        r44.setTextSize(1, 14.0f);
        if (LocaleController.isRTL == false) goto L17;
        int r102 = 5;
    L18:
        r44.setGravity(r102 | 48);
        ViewGroup r103 = this.containerView;
        if (LocaleController.isRTL == true) goto L22;
        r8 = 3;
    L22:
        r103.addView(r44, LayoutHelper.createFrame(-2, -2.0f, r8 | 48, 24.0f, 62.0f, 24.0f, 0.0f));
        this.titles = new TextView[3];
        ViewPager r45 = new ViewPager(r19);
        this.viewPager = r45;
        r45.setClipChildren(false);
        this.viewPager.setOffscreenPageLimit(4);
        this.viewPager.setClipToPadding(false);
        AndroidUtilities.setViewPagerEdgeEffectColor(this.viewPager, 2130706432);
        this.viewPager.setAdapter(new Adapter(this, null));
        this.viewPager.setPageMargin(0);
        this.containerView.addView(this.viewPager, LayoutHelper.createFrame(-1, -1.0f, 1, 0.0f, 100.0f, 0.0f, 130.0f));
        this.viewPager.addOnPageChangeListener(new AnonymousClass2(this));
        View r46 = new View(getContext());
        GradientDrawable.Orientation r9 = GradientDrawable.Orientation.LEFT_RIGHT;
        r46.setBackground(new GradientDrawable(r9, new int[]{r3, 0}));
        this.containerView.addView(r46, LayoutHelper.createFrame(120, -1.0f, 51, 0.0f, 100.0f, 0.0f, 130.0f));
        View r47 = new View(getContext());
        r47.setBackground(new GradientDrawable(r9, new int[]{0, r3}));
        this.containerView.addView(r47, LayoutHelper.createFrame(120, -1.0f, 53, 0.0f, 100.0f, 0.0f, 130.0f));
        AnonymousClass3 r32 = new AnonymousClass3(this, getContext());
        this.positiveButton = r32;
        r32.setMinWidth(AndroidUtilities.dp(64.0f));
        this.positiveButton.setTag(-1);
        this.positiveButton.setTextSize(1, 14.0f);
        TextView r33 = this.positiveButton;
        int r48 = Theme.key_voipgroup_nameText;
        r33.setTextColor(Theme.getColor(r48));
        this.positiveButton.setGravity(17);
        this.positiveButton.setTypeface(AndroidUtilities.bold());
        this.positiveButton.setText(LocaleController.getString(R.string.VoipRecordStart));
        this.positiveButton.setForeground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(6.0f), 0, ColorUtils.setAlphaComponent(Theme.getColor(r48), 76)));
        this.positiveButton.setPadding(0, AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f));
        this.positiveButton.setOnClickListener(new GroupCallRecordAlert$$ExternalSyntheticLambda0(this));
        this.containerView.addView(this.positiveButton, LayoutHelper.createFrame(-1, 48.0f, 80, 0.0f, 0.0f, 0.0f, 64.0f));
        LinearLayout r34 = new LinearLayout(r19);
        this.titlesLayout = r34;
        this.containerView.addView(r34, LayoutHelper.createFrame(-2, 64, 80));
        int r35 = 0;
    L23:
        TextView[] r63 = this.titles;
        if (r35 >= r63.length) goto L33;
        r63[r35] = new TextView(r19);
        this.titles[r35].setTextSize(1, 12.0f);
        this.titles[r35].setTextColor(-1);
        this.titles[r35].setTypeface(AndroidUtilities.bold());
        this.titles[r35].setPadding(AndroidUtilities.dp(10.0f), 0, AndroidUtilities.dp(10.0f), 0);
        this.titles[r35].setGravity(16);
        this.titles[r35].setSingleLine(true);
        this.titlesLayout.addView(this.titles[r35], LayoutHelper.createLinear(-2, -1));
        if (r35 != 0) goto L28;
        this.titles[r35].setText(LocaleController.getString(R.string.VoipRecordAudio));
    L32:
        this.titles[r35].setOnClickListener(new GroupCallRecordAlert$$ExternalSyntheticLambda1(this, r35));
        r35 = r35 + 1;
        goto L23
    L28:
        TextView[] r64 = this.titles;
        if (r35 != 1) goto L31;
        r64[r35].setText(LocaleController.getString(R.string.VoipRecordPortrait));
        goto L32
    L31:
        r64[r35].setText(LocaleController.getString(R.string.VoipRecordLandscape));
        goto L32
    L33:
        if (r21 == false) goto L40;
        this.viewPager.setCurrentItem(1);
        return;
    L40:
        return;
    L17:
        r102 = 3;
        goto L18
    L13:
        r10 = 3;
        goto L14
    L9:
        r6 = 3;
        goto L10
    L5:
        r43.setText(LocaleController.getString(R.string.VoipRecordVoiceChat));
        goto L6
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        onStartRecord(this.currentPage);
        dismiss();
    }

    private /* synthetic */ void lambda$new$1(int r1, View r2) {
        this.viewPager.setCurrentItem(r1, true);
    }

    private void updateTitlesLayout() {
        TextView[] r0 = this.titles;
        int r1 = this.currentPage;
        TextView r2 = r0[r1];
        if (r1 >= (r0.length - 1)) goto L5;
        TextView r02 = r0[r1 + 1];
    L6:
        this.containerView.getMeasuredWidth();
        float r12 = r2.getLeft() + (r2.getMeasuredWidth() / 2);
        float r22 = (this.containerView.getMeasuredWidth() / 2) - r12;
        if (r02 == null) goto L9;
        r22 = r22 - (((r02.getLeft() + (r02.getMeasuredWidth() / 2)) - r12) * this.pageOffset);
    L9:
        int r03 = 0;
    L10:
        TextView[] r13 = this.titles;
        if (r03 >= r13.length) goto L22;
        int r3 = this.currentPage;
        float r4 = 0.9f;
        float r5 = 0.7f;
        if (r03 < r3) goto L21;
        if (r03 > (r3 + 1)) goto L21;
        float r6 = this.pageOffset;
        if (r03 != r3) goto L20;
        r5 = 1.0f - (0.3f * r6);
        r4 = 1.0f - (r6 * 0.1f);
        goto L21
    L20:
        r5 = 0.7f + (0.3f * r6);
        r4 = 0.9f + (r6 * 0.1f);
    L21:
        r13[r03].setAlpha(r5);
        this.titles[r03].setScaleX(r4);
        this.titles[r03].setScaleY(r4);
        r03 = r03 + 1;
        goto L10
    L22:
        this.titlesLayout.setTranslationX(r22);
        this.positiveButton.invalidate();
        return;
    L5:
        r02 = null;
        goto L6
    }

    public abstract void onStartRecord(int r1);
}
