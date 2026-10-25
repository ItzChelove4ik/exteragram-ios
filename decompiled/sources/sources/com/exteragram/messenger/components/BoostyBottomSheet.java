package com.exteragram.messenger.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.api.dto.BoostySubscriberDTO;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Premium.StarParticlesView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes4.dex */
public abstract class BoostyBottomSheet extends BottomSheet {
    private final int ITEM_HEIGHT;
    private final int PRIMARY_COLOR;
    private final Runnable autoScrollRunnable;
    public ButtonWithCounterView buttonView;
    private int contentHeight;
    private int currentAutoScrollPosition;
    public LinkSpanDrawable.LinksTextView descriptionView;
    private boolean isUserScrolling;
    private final RecyclerListView listView;
    private Runnable resumeScrollRunnable;
    private final LinearSnapHelper snapHelper;
    private final StarParticlesView.Drawable starDrawable;
    public TextView titleView;
    public FrameLayout topView;

    /* renamed from: com.exteragram.messenger.components.BoostyBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ BoostyBottomSheet this$0;

        /* renamed from: com.exteragram.messenger.components.BoostyBottomSheet$1$1, reason: invalid class name and collision with other inner class name */
        public class C00171 extends LinearSmoothScroller {
            final /* synthetic */ AnonymousClass1 this$1;
            final /* synthetic */ boolean val$finalWrap;

            public C00171(AnonymousClass1 r1, Context r2, boolean r3) {
                this.this$1 = r1;
                this.val$finalWrap = r3;
                super(r2);
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public int calculateDtToFit(int r1, int r2, int r3, int r4, int r5) {
                return (r3 + ((r4 - r3) / 2)) - (r1 + ((r2 - r1) / 2));
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public float calculateSpeedPerPixel(DisplayMetrics r2) {
                if (this.val$finalWrap == false) goto L5;
                float r0 = 0.6f;
            L7:
                return super.calculateSpeedPerPixel(r2) * r0;
            L5:
                r0 = 12.5f;
                goto L7
            }
        }

        public AnonymousClass1(BoostyBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (BoostyBottomSheet.m1186$$Nest$fgetlistView(this.this$0) != null) goto L5;
            return;
        L5:
            if (BoostyBottomSheet.m1185$$Nest$fgetisUserScrolling(this.this$0) == false) goto L7;
            return;
        L7:
            if (BoostyBottomSheet.m1186$$Nest$fgetlistView(this.this$0).getAdapter() == null) goto L26;
            boolean r1 = true;
            if (BoostyBottomSheet.m1186$$Nest$fgetlistView(this.this$0).getAdapter().getItemCount() <= 1) goto L27;
            LinearLayoutManager r0 = (LinearLayoutManager) BoostyBottomSheet.m1186$$Nest$fgetlistView(this.this$0).getLayoutManager();
            if (r0 == null) goto L28;
            BoostyBottomSheet r2 = this.this$0;
            BoostyBottomSheet.m1191$$Nest$fputcurrentAutoScrollPosition(r2, BoostyBottomSheet.m1184$$Nest$fgetcurrentAutoScrollPosition(r2) + 1);
            if (BoostyBottomSheet.m1184$$Nest$fgetcurrentAutoScrollPosition(this.this$0) < BoostyBottomSheet.m1186$$Nest$fgetlistView(this.this$0).getAdapter().getItemCount()) goto L17;
            BoostyBottomSheet.m1191$$Nest$fputcurrentAutoScrollPosition(this.this$0, 0);
        L18:
            C00171 r22 = new C00171(this, BoostyBottomSheet.m1186$$Nest$fgetlistView(this.this$0).getContext(), r1);
            r22.setTargetPosition(BoostyBottomSheet.m1184$$Nest$fgetcurrentAutoScrollPosition(this.this$0));
            r0.startSmoothScroll(r22);
            BoostyBottomSheet.m1186$$Nest$fgetlistView(this.this$0).removeCallbacks(this);
            RecyclerListView r02 = BoostyBottomSheet.m1186$$Nest$fgetlistView(this.this$0);
            if (r1 == false) goto L21;
            long r12 = 3000;
        L22:
            r02.postDelayed(this, r12);
            return;
        L21:
            r12 = 1500;
            goto L22
        L17:
            r1 = false;
            goto L18
        L28:
            return;
        L27:
            return;
        }
    }

    /* renamed from: com.exteragram.messenger.components.BoostyBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        private final Path path;
        private final RectF rectF;
        final /* synthetic */ BoostyBottomSheet this$0;
        final /* synthetic */ Paint val$topViewBgPaint;

        public AnonymousClass2(BoostyBottomSheet r1, Context r2, Paint r3) {
            this.this$0 = r1;
            this.val$topViewBgPaint = r3;
            super(r2);
            this.path = new Path();
            this.rectF = new RectF();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r11) {
            float r0 = AndroidUtilities.dp(12.0f);
            this.rectF.set(0.0f, AndroidUtilities.dp(2.0f), getMeasuredWidth(), getMeasuredHeight() + AndroidUtilities.dp(18.0f));
            this.val$topViewBgPaint.setShader(new RadialGradient(this.rectF.centerX(), this.rectF.centerY() - AndroidUtilities.dp(9.0f), Math.max(this.rectF.width(), this.rectF.height()) / 2.0f, -830418, -2536663, Shader.TileMode.CLAMP));
            r11.save();
            r11.clipRect(0, AndroidUtilities.dp(2.0f), getMeasuredWidth(), getMeasuredHeight());
            float r02 = r0 - 1.0f;
            r11.drawRoundRect(this.rectF, r02, r02, this.val$topViewBgPaint);
            BoostyBottomSheet.m1189$$Nest$fgetstarDrawable(this.this$0).onDraw(r11);
            invalidate();
            r11.restore();
            this.path.reset();
            this.path.addRoundRect(this.rectF, r02, r02, Path.Direction.CW);
            r11.save();
            r11.clipPath(this.path);
            super.dispatchDraw(r11);
            r11.restore();
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            boolean r0 = AndroidUtilities.isTablet();
            BoostyBottomSheet r1 = this.this$0;
            if (r0 == false) goto L5;
            BoostyBottomSheet.m1190$$Nest$fputcontentHeight(r1, (int) (View.MeasureSpec.getSize(r6) * 0.45f));
        L9:
            super.onMeasure(r5, View.MeasureSpec.makeMeasureSpec(BoostyBottomSheet.m1183$$Nest$fgetcontentHeight(this.this$0) + AndroidUtilities.dp(2.0f), TLObject.FLAG_30));
            this.rectF.set(0.0f, AndroidUtilities.dp(2.0f), getMeasuredWidth(), getMeasuredHeight() + AndroidUtilities.dp(18.0f));
            BoostyBottomSheet.m1189$$Nest$fgetstarDrawable(this.this$0).rect.set(0.0f, AndroidUtilities.dp(2.0f), getMeasuredWidth(), getMeasuredHeight());
            BoostyBottomSheet.m1189$$Nest$fgetstarDrawable(this.this$0).rect2.set(-AndroidUtilities.dp(15.0f), -AndroidUtilities.dp(15.0f), getMeasuredWidth() + AndroidUtilities.dp(15.0f), getMeasuredHeight() + AndroidUtilities.dp(15.0f));
            BoostyBottomSheet.m1189$$Nest$fgetstarDrawable(this.this$0).resetPositions();
            return;
        L5:
            boolean r02 = BoostyBottomSheet.access$000(r1);
            BoostyBottomSheet r12 = this.this$0;
            if (r02 == false) goto L8;
            BoostyBottomSheet.m1190$$Nest$fputcontentHeight(r12, (int) (View.MeasureSpec.getSize(r5) * 0.8f));
            goto L9
        L8:
            BoostyBottomSheet.m1190$$Nest$fputcontentHeight(r12, (int) (View.MeasureSpec.getSize(r6) * 0.65f));
            goto L9
        }
    }

    /* renamed from: com.exteragram.messenger.components.BoostyBottomSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerListView {
        final /* synthetic */ BoostyBottomSheet this$0;

        public AnonymousClass3(BoostyBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L6;
            getParent().requestDisallowInterceptTouchEvent(true);
        L6:
            return super.onInterceptTouchEvent(r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public void onMeasure(int r3, int r4) {
            int r0 = View.MeasureSpec.getSize(r4);
            if (r0 <= 0) goto L7;
            int r02 = (r0 - BoostyBottomSheet.m1181$$Nest$fgetITEM_HEIGHT(this.this$0)) / 2;
            if (getPaddingTop() == r02) goto L7;
            setPadding(0, r02, 0, r02);
        L7:
            super.onMeasure(r3, r4);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent r5) {
            if (r5.getAction() != 0) goto L5;
            getParent().requestDisallowInterceptTouchEvent(true);
        L5:
            boolean r0 = super.onTouchEvent(r5);
            if (r5.getAction() != 0) goto L8;
        L9:
            getParent().requestDisallowInterceptTouchEvent(true);
        L10:
            if (r0 == false) goto L12;
        L16:
            return true;
        L12:
            if (r5.getAction() == 0) goto L16;
            return false;
        L8:
            if (r5.getAction() != 2) goto L10;
            goto L9
        }
    }

    /* renamed from: com.exteragram.messenger.components.BoostyBottomSheet$4, reason: invalid class name */
    public class AnonymousClass4 extends RecyclerView.Adapter<ViewHolder> {
        final /* synthetic */ BoostyBottomSheet this$0;
        final /* synthetic */ Context val$context;
        final /* synthetic */ List val$subscribers;

        public AnonymousClass4(BoostyBottomSheet r1, Context r2, List r3) {
            this.this$0 = r1;
            this.val$context = r2;
            this.val$subscribers = r3;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.val$subscribers.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder r1, int r2) {
            onBindViewHolder((ViewHolder) r1, r2);
        }

        public void onBindViewHolder(ViewHolder r1, int r2) {
            r1.textView.setText(((BoostySubscriberDTO) this.val$subscribers.get(r2)).getName());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public /* bridge */ /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
            return onCreateViewHolder(r1, r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup r4, int r5) {
            TextView r42 = new TextView(this.val$context);
            r42.setTextSize(1, 22.0f);
            r42.setGravity(17);
            r42.setTextColor(-1);
            r42.setTypeface(AndroidUtilities.bold());
            r42.setMaxLines(2);
            r42.setEllipsize(TextUtils.TruncateAt.END);
            r42.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
            r42.setMinHeight(BoostyBottomSheet.m1181$$Nest$fgetITEM_HEIGHT(this.this$0));
            r42.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new ViewHolder(r42);
        }
    }

    /* renamed from: com.exteragram.messenger.components.BoostyBottomSheet$5, reason: invalid class name */
    public class AnonymousClass5 extends RecyclerView.OnScrollListener {
        final /* synthetic */ BoostyBottomSheet this$0;

        public static /* synthetic */ void $r8$lambda$KwAX5BT6R0FDtkD2_Oqb6WwlsKE(AnonymousClass5 r0) {
            r0.lambda$onScrollStateChanged$0();
        }

        public AnonymousClass5(BoostyBottomSheet r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onScrollStateChanged$0() {
            BoostyBottomSheet.m1192$$Nest$fputisUserScrolling(this.this$0, false);
            BoostyBottomSheet.m1186$$Nest$fgetlistView(this.this$0).postDelayed(BoostyBottomSheet.m1182$$Nest$fgetautoScrollRunnable(this.this$0), 1500);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r3, int r4) {
            if (r4 != 1) goto L8;
            BoostyBottomSheet.m1192$$Nest$fputisUserScrolling(this.this$0, true);
            BoostyBottomSheet.m1186$$Nest$fgetlistView(this.this$0).removeCallbacks(BoostyBottomSheet.m1182$$Nest$fgetautoScrollRunnable(this.this$0));
            if (BoostyBottomSheet.m1187$$Nest$fgetresumeScrollRunnable(this.this$0) == null) goto L19;
            BoostyBottomSheet.m1186$$Nest$fgetlistView(this.this$0).removeCallbacks(BoostyBottomSheet.m1187$$Nest$fgetresumeScrollRunnable(this.this$0));
            return;
        L19:
            return;
        L8:
            if (r4 == 0) goto L10;
            return;
        L10:
            if (BoostyBottomSheet.m1185$$Nest$fgetisUserScrolling(this.this$0) == false) goto L21;
            LinearLayoutManager r32 = (LinearLayoutManager) BoostyBottomSheet.m1186$$Nest$fgetlistView(this.this$0).getLayoutManager();
            if (r32 == null) goto L17;
            View r42 = BoostyBottomSheet.m1188$$Nest$fgetsnapHelper(this.this$0).findSnapView(r32);
            BoostyBottomSheet r0 = this.this$0;
            if (r42 == null) goto L16;
            BoostyBottomSheet.m1191$$Nest$fputcurrentAutoScrollPosition(r0, r32.getPosition(r42));
            goto L17
        L16:
            BoostyBottomSheet.m1191$$Nest$fputcurrentAutoScrollPosition(r0, r32.findFirstVisibleItemPosition());
        L17:
            BoostyBottomSheet.m1193$$Nest$fputresumeScrollRunnable(this.this$0, new BoostyBottomSheet$5$$ExternalSyntheticLambda0(this));
            BoostyBottomSheet.m1186$$Nest$fgetlistView(this.this$0).postDelayed(BoostyBottomSheet.m1187$$Nest$fgetresumeScrollRunnable(this.this$0), 3000);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            BoostyBottomSheet.m1194$$Nest$mupdateScales(this.this$0);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View r1) {
            super(r1);
            this.textView = (TextView) r1;
        }
    }

    public static /* synthetic */ void $r8$lambda$2j0uuJ8QOgjYQHaeGxNimySco44(BoostyBottomSheet r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$Is_eVJp1Q92vLK271lchN82UFPU(BoostyBottomSheet r0, View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
        r0.lambda$new$0(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    /* renamed from: -$$Nest$fgetITEM_HEIGHT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1181$$Nest$fgetITEM_HEIGHT(BoostyBottomSheet r0) {
        return r0.ITEM_HEIGHT;
    }

    /* renamed from: -$$Nest$fgetautoScrollRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m1182$$Nest$fgetautoScrollRunnable(BoostyBottomSheet r0) {
        return r0.autoScrollRunnable;
    }

    /* renamed from: -$$Nest$fgetcontentHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1183$$Nest$fgetcontentHeight(BoostyBottomSheet r0) {
        return r0.contentHeight;
    }

    /* renamed from: -$$Nest$fgetcurrentAutoScrollPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1184$$Nest$fgetcurrentAutoScrollPosition(BoostyBottomSheet r0) {
        return r0.currentAutoScrollPosition;
    }

    /* renamed from: -$$Nest$fgetisUserScrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1185$$Nest$fgetisUserScrolling(BoostyBottomSheet r0) {
        return r0.isUserScrolling;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m1186$$Nest$fgetlistView(BoostyBottomSheet r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetresumeScrollRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m1187$$Nest$fgetresumeScrollRunnable(BoostyBottomSheet r0) {
        return r0.resumeScrollRunnable;
    }

    /* renamed from: -$$Nest$fgetsnapHelper, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearSnapHelper m1188$$Nest$fgetsnapHelper(BoostyBottomSheet r0) {
        return r0.snapHelper;
    }

    /* renamed from: -$$Nest$fgetstarDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StarParticlesView.Drawable m1189$$Nest$fgetstarDrawable(BoostyBottomSheet r0) {
        return r0.starDrawable;
    }

    /* renamed from: -$$Nest$fputcontentHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1190$$Nest$fputcontentHeight(BoostyBottomSheet r0, int r1) {
        r0.contentHeight = r1;
    }

    /* renamed from: -$$Nest$fputcurrentAutoScrollPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1191$$Nest$fputcurrentAutoScrollPosition(BoostyBottomSheet r0, int r1) {
        r0.currentAutoScrollPosition = r1;
    }

    /* renamed from: -$$Nest$fputisUserScrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1192$$Nest$fputisUserScrolling(BoostyBottomSheet r0, boolean r1) {
        r0.isUserScrolling = r1;
    }

    /* renamed from: -$$Nest$fputresumeScrollRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1193$$Nest$fputresumeScrollRunnable(BoostyBottomSheet r0, Runnable r1) {
        r0.resumeScrollRunnable = r1;
    }

    /* renamed from: -$$Nest$mupdateScales, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1194$$Nest$mupdateScales(BoostyBottomSheet r0) {
        r0.updateScales();
    }

    public BoostyBottomSheet(Context r22, List<BoostySubscriberDTO> r23) {
        super(r22, false);
        this.ITEM_HEIGHT = AndroidUtilities.dp(48.0f);
        this.PRIMARY_COLOR = -830418;
        LinearSnapHelper r4 = new LinearSnapHelper();
        this.snapHelper = r4;
        AnonymousClass1 r5 = new AnonymousClass1(this);
        this.autoScrollRunnable = r5;
        if (Theme.isCurrentThemeDark() == false) goto L5;
        int r6 = -15198184;
    L6:
        setBackgroundColor(r6);
        fixNavigationBar(r6);
        setApplyTopPadding(false);
        setApplyBottomPadding(false);
        this.useBackgroundTopPadding = false;
        Paint r62 = new Paint(1);
        r62.setColor(-830418);
        StarParticlesView.Drawable r8 = new StarParticlesView.Drawable(300);
        this.starDrawable = r8;
        r8.color = 1627389951;
        r8.size1 = 8;
        r8.size2 = 6;
        r8.size3 = 4;
        r8.k3 = 0.98f;
        r8.k2 = 0.98f;
        r8.k1 = 0.98f;
        r8.useRotate = true;
        r8.speedScale = 2.0f;
        r8.checkBounds = true;
        r8.checkTime = true;
        r8.useBlur = true;
        r8.roundEffect = false;
        r8.init();
        AnonymousClass2 r82 = new AnonymousClass2(this, r22, r62);
        LinearLayout r63 = new LinearLayout(r22);
        r63.setOrientation(1);
        FrameLayout r10 = new FrameLayout(r22);
        this.topView = r10;
        r82.addView(r10, LayoutHelper.createFrame(-1, -1.0f, 48, 0.0f, 2.0f, 0.0f, 0.0f));
        AnonymousClass3 r102 = new AnonymousClass3(this, r22);
        this.listView = r102;
        r102.setLayoutManager(new LinearLayoutManager(r22));
        r102.setAdapter(new AnonymousClass4(this, r22, r23));
        r102.setOnScrollListener(new AnonymousClass5(this));
        r102.addOnLayoutChangeListener(new BoostyBottomSheet$$ExternalSyntheticLambda0(this));
        r102.setOverScrollMode(2);
        r102.setVerticalScrollBarEnabled(false);
        r102.setClipToPadding(false);
        r102.scrollToPosition(0);
        r102.postDelayed(r5, 1500);
        r4.attachToRecyclerView(r102);
        this.topView.addView(r102, LayoutHelper.createFrame(-1, -1.0f));
        r63.addView(r82, LayoutHelper.createLinear(-1, -2, 1, 0, 0, 0, 0));
        if (Theme.isCurrentThemeDark() == false) goto L9;
        int r42 = -1;
    L10:
        TextView r52 = new TextView(r22);
        this.titleView = r52;
        r52.setGravity(1);
        this.titleView.setTextColor(r42);
        this.titleView.setTextSize(1, 20.0f);
        this.titleView.setTypeface(AndroidUtilities.bold());
        this.titleView.setText(LocaleController.formatPluralString("BoostyPeopleCount", r23.size(), new Object[0]));
        r63.addView(this.titleView, LayoutHelper.createLinear(-2, -2, 1, 20, 24, 20, 0));
        LinkSpanDrawable.LinksTextView r53 = new LinkSpanDrawable.LinksTextView(r22);
        this.descriptionView = r53;
        r53.setGravity(1);
        this.descriptionView.setTextSize(1, 14.0f);
        this.descriptionView.setTextColor(r42);
        this.descriptionView.setText(AndroidUtilities.replaceTags(new SpannableStringBuilder(LocaleController.getString(R.string.BoostyInfo)).append("\n").append(LocaleController.getString(R.string.BoostyInfo2))));
        r63.addView(this.descriptionView, LayoutHelper.createLinear(-2, -2, 1, 20, 6, 20, 0));
        ButtonWithCounterView r43 = new ButtonWithCounterView(r22, this.resourcesProvider);
        this.buttonView = r43;
        r43.setRound();
        SpannableStringBuilder r44 = new SpannableStringBuilder(LocaleController.getString(R.string.Open)).append("  Boosty");
        ColoredImageSpan r54 = new ColoredImageSpan(R.drawable.boosty_icon, 2);
        r54.setSize(AndroidUtilities.dp(20.0f));
        r44.setSpan(r54, r44.length() - 7, r44.length() - 6, 33);
        this.buttonView.setText(r44, false);
        this.buttonView.setTextColor(-1);
        this.buttonView.setOnClickListener(new BoostyBottomSheet$$ExternalSyntheticLambda1(this));
        this.buttonView.setColor(-830418, ColorUtils.setAlphaComponent(ColorUtils.blendARGB(-830418, 0, 0.2f), 51));
        r63.addView(this.buttonView, LayoutHelper.createLinear(-1, 48, 0, 14, 24, 14, 14));
        ScrollView r2 = new ScrollView(r22);
        r2.addView(r63);
        setCustomView(r2);
        return;
    L9:
        r42 = -16777216;
        goto L10
    L5:
        r6 = -657931;
        goto L6
    }

    public static /* synthetic */ boolean access$000(BoostyBottomSheet r0) {
        return r0.isPortrait;
    }

    private /* synthetic */ void lambda$new$0(View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
        updateScales();
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        dismiss();
        onButtonClick();
    }

    private void updateScales() {
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L9;
        int r02 = r0.getHeight() / 2;
        int r1 = 0;
    L7:
        if (r1 >= this.listView.getChildCount()) goto L11;
        View r2 = this.listView.getChildAt(r1);
        float r3 = Math.min(Math.abs(r02 - ((r2.getTop() + r2.getBottom()) / 2)) / (this.listView.getHeight() / 2.0f), 1.0f);
        float r6 = 1.0f - (r3 * 0.3f);
        r2.setScaleX(r6);
        r2.setScaleY(r6);
        r2.setAlpha(((1.0f - r3) * 0.7f) + 0.3f);
        r1 = r1 + 1;
        goto L7
    L11:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        super.dismiss();
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L8;
        r0.removeCallbacks(this.autoScrollRunnable);
        Runnable r02 = this.resumeScrollRunnable;
        if (r02 == null) goto L9;
        this.listView.removeCallbacks(r02);
        return;
    L9:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean isTouchOutside(float r3, float r4) {
        if (r4 < (this.containerView.getTop() - this.backgroundPaddingTop)) goto L9;
        if (r4 > this.containerView.getBottom()) goto L9;
        return false;
    L9:
        return super.isTouchOutside(r3, r4);
    }

    public abstract void onButtonClick();
}
