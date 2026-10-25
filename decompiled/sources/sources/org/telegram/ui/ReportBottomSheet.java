package org.telegram.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.Collections;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_ephemeral;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.EditTextCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.ViewPagerFixed;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes6.dex */
public class ReportBottomSheet extends BottomSheet {
    private final Paint backgroundPaint;
    private final long dialogId;
    private final boolean ephemeral;
    private Listener listener;
    private final ArrayList<Integer> messageIds;
    private final boolean sponsored;
    private final byte[] sponsoredId;
    private final boolean stories;
    private final ViewPagerFixed viewPager;

    /* renamed from: org.telegram.ui.ReportBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends ViewPagerFixed {
        final /* synthetic */ ReportBottomSheet this$0;

        public AnonymousClass1(ReportBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public boolean canScrollForward(MotionEvent r1) {
            return false;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onStartTracking() {
            if ((getCurrentView() instanceof Page) == false) goto L8;
            Page r1 = (Page) getCurrentView();
            if (Page.m18487$$Nest$fgeteditTextCell(r1) == null) goto L9;
            AndroidUtilities.hideKeyboard(Page.m18487$$Nest$fgeteditTextCell(r1));
            return;
        L9:
            return;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onTabAnimationUpdate(boolean r1) {
            super.onTabAnimationUpdate(r1);
            ReportBottomSheet.access$000(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.ReportBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends ViewPagerFixed.Adapter {
        final /* synthetic */ ReportBottomSheet this$0;
        final /* synthetic */ Context val$context;

        public AnonymousClass2(ReportBottomSheet r1, Context r2) {
            this.this$0 = r1;
            this.val$context = r2;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public void bindView(View r1, int r2, int r3) {
            ((Page) r1).bind(r3);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public View createView(int r2) {
            return new Page(this.this$0, this.val$context);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemCount() {
            return 5;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemViewType(int r1) {
            if (r1 != 0) goto L5;
            return 0;
        L5:
            return 1;
        }
    }

    /* renamed from: org.telegram.ui.ReportBottomSheet$3, reason: invalid class name */
    public class AnonymousClass3 implements Listener {
        final /* synthetic */ BulletinFactory val$bulletinFactory;
        final /* synthetic */ boolean[] val$done;
        final /* synthetic */ Utilities.Callback val$whenDone;

        public static /* synthetic */ void $r8$lambda$BigYiaLE7uVWxqGsnUXTnyV2DyI(BulletinFactory r3) {
            if (LaunchActivity.getSafeLastFragment() == null) goto L11;
            if (r3 != null) goto L7;
            r3 = BulletinFactory.of(LaunchActivity.getSafeLastFragment());
        L7:
            if (r3 != null) goto L9;
            return;
        L9:
            r3.createSimpleBulletin(R.raw.msg_antispam, LocaleController.getString(R.string.ReportChatSent), LocaleController.getString(R.string.Reported2)).setDuration(5000).show();
            return;
        }

        public AnonymousClass3(boolean[] r1, Utilities.Callback r2, BulletinFactory r3) {
            this.val$done = r1;
            this.val$whenDone = r2;
            this.val$bulletinFactory = r3;
        }

        @Override // org.telegram.ui.ReportBottomSheet.Listener
        public void onReported() {
            boolean[] r0 = this.val$done;
            if (r0[0] == true) goto L7;
            Utilities.Callback r2 = this.val$whenDone;
            if (r2 == null) goto L7;
            r0[0] = true;
            r2.run(Boolean.TRUE);
        L7:
            AndroidUtilities.runOnUIThread(new ReportBottomSheet$3$$ExternalSyntheticLambda0(this.val$bulletinFactory), 200);
        }
    }

    /* renamed from: org.telegram.ui.ReportBottomSheet$4, reason: invalid class name */
    public class AnonymousClass4 implements Listener {
        final /* synthetic */ Context val$context;
        final /* synthetic */ ChatActivity val$fragment;
        final /* synthetic */ MessageObject val$message;
        final /* synthetic */ Theme.ResourcesProvider val$resourceProvider;

        public static /* synthetic */ void $r8$lambda$NA6yB0znHotrf1ac4VaDyBpaUE4(ChatActivity r2, MessageObject r3) {
            BulletinFactory.of(r2).createAdReportedBulletin(LocaleController.getString(R.string.AdHidden)).show();
            r2.removeFromSponsored(r3);
            r2.removeMessageWithThanos(r3);
        }

        public static /* synthetic */ void $r8$lambda$o7YJQajG1ZXWrikg12x_57K64do(ChatActivity r4, Context r5, Theme.ResourcesProvider r6, MessageObject r7) {
            BulletinFactory.of(r4).createAdReportedBulletin(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.AdReported), -1, 2, new ReportBottomSheet$4$$ExternalSyntheticLambda0(r5), r6)).show();
            r4.removeFromSponsored(r7);
            r4.removeMessageWithThanos(r7);
        }

        /* renamed from: $r8$lambda$tT8aRlhNdwnb-dq4XEU3edKhQ_U, reason: not valid java name */
        public static /* synthetic */ void m18482$r8$lambda$tT8aRlhNdwnbdq4XEU3edKhQ_U(Context r1) {
            Browser.openUrl(r1, "https://promote.telegram.org/guidelines");
        }

        public AnonymousClass4(ChatActivity r1, Context r2, Theme.ResourcesProvider r3, MessageObject r4) {
            this.val$fragment = r1;
            this.val$context = r2;
            this.val$resourceProvider = r3;
            this.val$message = r4;
        }

        @Override // org.telegram.ui.ReportBottomSheet.Listener
        public void onHidden() {
            AndroidUtilities.runOnUIThread(new ReportBottomSheet$4$$ExternalSyntheticLambda1(this.val$fragment, this.val$message), 200);
        }

        @Override // org.telegram.ui.ReportBottomSheet.Listener
        public void onPremiumRequired() {
            this.val$fragment.showDialog(new PremiumFeatureBottomSheet(this.val$fragment, 3, true));
        }

        @Override // org.telegram.ui.ReportBottomSheet.Listener
        public void onReported() {
            AndroidUtilities.runOnUIThread(new ReportBottomSheet$4$$ExternalSyntheticLambda2(this.val$fragment, this.val$context, this.val$resourceProvider, this.val$message), 200);
        }
    }

    /* renamed from: org.telegram.ui.ReportBottomSheet$5, reason: invalid class name */
    public class AnonymousClass5 implements Listener {
        final /* synthetic */ BulletinFactory val$bulletinFactory;
        final /* synthetic */ Context val$context;
        final /* synthetic */ Runnable val$done;
        final /* synthetic */ Theme.ResourcesProvider val$resourceProvider;
        final /* synthetic */ Runnable val$showPremium;

        /* renamed from: $r8$lambda$H1FLXYhbApWbLaXPsE-7oTlVdbY, reason: not valid java name */
        public static /* synthetic */ void m18483$r8$lambda$H1FLXYhbApWbLaXPsE7oTlVdbY(Runnable r2, BulletinFactory r3, Context r4, Theme.ResourcesProvider r5) {
            if (r2 == null) goto L4;
            r2.run();
        L4:
            r3.createAdReportedBulletin(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.AdReported), -1, 2, new ReportBottomSheet$5$$ExternalSyntheticLambda0(r4), r5)).show();
        }

        public static /* synthetic */ void $r8$lambda$SMieQ2J2V2XrVdL9OXrJEEZDtV0(Runnable r0, BulletinFactory r1) {
            if (r0 == null) goto L4;
            r0.run();
        L4:
            r1.createAdReportedBulletin(LocaleController.getString(R.string.AdHidden)).show();
        }

        public static /* synthetic */ void $r8$lambda$Yjiql7Y_qwIMTVRHRRLr3ir7FfU(Context r1) {
            Browser.openUrl(r1, "https://promote.telegram.org/guidelines");
        }

        public AnonymousClass5(Runnable r1, BulletinFactory r2, Context r3, Theme.ResourcesProvider r4, Runnable r5) {
            this.val$done = r1;
            this.val$bulletinFactory = r2;
            this.val$context = r3;
            this.val$resourceProvider = r4;
            this.val$showPremium = r5;
        }

        @Override // org.telegram.ui.ReportBottomSheet.Listener
        public void onHidden() {
            AndroidUtilities.runOnUIThread(new ReportBottomSheet$5$$ExternalSyntheticLambda1(this.val$done, this.val$bulletinFactory), 200);
        }

        @Override // org.telegram.ui.ReportBottomSheet.Listener
        public void onPremiumRequired() {
            this.val$showPremium.run();
        }

        @Override // org.telegram.ui.ReportBottomSheet.Listener
        public void onReported() {
            AndroidUtilities.runOnUIThread(new ReportBottomSheet$5$$ExternalSyntheticLambda2(this.val$done, this.val$bulletinFactory, this.val$context, this.val$resourceProvider), 200);
        }
    }

    /* renamed from: org.telegram.ui.ReportBottomSheet$6, reason: invalid class name */
    public class AnonymousClass6 implements Listener {
        final /* synthetic */ Context val$context;
        final /* synthetic */ BaseFragment val$fragment;
        final /* synthetic */ Runnable val$remove;
        final /* synthetic */ Theme.ResourcesProvider val$resourceProvider;

        public static /* synthetic */ void $r8$lambda$WnkSMxLgeAYEbetp7tOy7z9Ac5w(BaseFragment r3, Context r4, Theme.ResourcesProvider r5, Runnable r6) {
            BulletinFactory.of(r3).createAdReportedBulletin(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.AdReported), -1, 2, new ReportBottomSheet$6$$ExternalSyntheticLambda2(r4), r5)).show();
            AndroidUtilities.runOnUIThread(r6);
        }

        public static /* synthetic */ void $r8$lambda$h1OS3GR2m2PbJb2TEDujYJI5gr8(BaseFragment r1, Runnable r2) {
            BulletinFactory.of(r1).createAdReportedBulletin(LocaleController.getString(R.string.AdHidden)).show();
            AndroidUtilities.runOnUIThread(r2);
        }

        public static /* synthetic */ void $r8$lambda$yXMCSeOf2fTdzB4tHhChq2Ojoc0(Context r1) {
            Browser.openUrl(r1, "https://promote.telegram.org/guidelines");
        }

        public AnonymousClass6(BaseFragment r1, Context r2, Theme.ResourcesProvider r3, Runnable r4) {
            this.val$fragment = r1;
            this.val$context = r2;
            this.val$resourceProvider = r3;
            this.val$remove = r4;
        }

        @Override // org.telegram.ui.ReportBottomSheet.Listener
        public void onHidden() {
            AndroidUtilities.runOnUIThread(new ReportBottomSheet$6$$ExternalSyntheticLambda1(this.val$fragment, this.val$remove), 200);
        }

        @Override // org.telegram.ui.ReportBottomSheet.Listener
        public void onPremiumRequired() {
            this.val$fragment.showDialog(new PremiumFeatureBottomSheet(this.val$fragment, 3, true));
        }

        @Override // org.telegram.ui.ReportBottomSheet.Listener
        public void onReported() {
            AndroidUtilities.runOnUIThread(new ReportBottomSheet$6$$ExternalSyntheticLambda0(this.val$fragment, this.val$context, this.val$resourceProvider, this.val$remove), 200);
        }
    }

    public class ContainerView extends FrameLayout {
        private final AnimatedFloat isActionBar;
        private final Path path;
        private Boolean statusBarOpen;
        final /* synthetic */ ReportBottomSheet this$0;
        private float top;

        public ContainerView(ReportBottomSheet r3, Context r4) {
            this.this$0 = r3;
            super(r4);
            this.isActionBar = new AnimatedFloat(this, 250, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.path = new Path();
        }

        private void updateLightStatusBar(boolean r7) {
            Boolean r0 = this.statusBarOpen;
            if (r0 != null) goto L5;
        L7:
            boolean r2 = false;
            if (AndroidUtilities.computePerceivedBrightness(ReportBottomSheet.access$300(this.this$0, Theme.key_dialogBackground)) <= 0.721f) goto L10;
            boolean r02 = true;
        L12:
            if (AndroidUtilities.computePerceivedBrightness(Theme.blendOver(ReportBottomSheet.access$400(this.this$0, Theme.key_actionBarDefault), 855638016)) <= 0.721f) goto L14;
            r2 = true;
        L14:
            this.statusBarOpen = Boolean.valueOf(r7);
            if (r7 == true) goto L18;
            r02 = r2;
        L18:
            AndroidUtilities.setLightStatusBar(this.this$0.getWindow(), r02);
            return;
        L10:
            r02 = false;
            goto L12
        L5:
            if (r0.booleanValue() != r7) goto L7;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r10) {
            View[] r0 = ReportBottomSheet.m18480$$Nest$fgetviewPager(this.this$0).getViewPages();
            float r1 = 0.0f;
            this.top = 0.0f;
            int r2 = r0.length;
            boolean r3 = false;
            int r4 = 0;
        L4:
            if (r4 >= r2) goto L12;
            View r6 = r0[r4];
            if (r6 == null) goto L11;
            Page r62 = (Page) r6;
            this.top += r62.top() * Utilities.clamp(1.0f - Math.abs(r62.getTranslationX() / r62.getMeasuredWidth()), 1.0f, 0.0f);
            if (r62.getVisibility() != 0) goto L11;
            r62.updateTops();
        L11:
            r4 = r4 + 1;
            goto L4
        L12:
            AnimatedFloat r02 = this.isActionBar;
            if (this.top > AndroidUtilities.statusBarHeight) goto L15;
            r1 = 1.0f;
        L15:
            float r03 = r02.set(r1);
            int r12 = AndroidUtilities.statusBarHeight;
            float r22 = r12 * r03;
            this.top = Math.max(r12, this.top) - (AndroidUtilities.statusBarHeight * r03);
            RectF r13 = AndroidUtilities.rectTmp;
            r13.set(ReportBottomSheet.access$100(this.this$0), this.top, getWidth() - ReportBottomSheet.access$200(this.this$0), getHeight() + AndroidUtilities.dp(8.0f));
            float r04 = AndroidUtilities.lerp(AndroidUtilities.dp(14.0f), 0, r03);
            r10.drawRoundRect(r13, r04, r04, ReportBottomSheet.m18475$$Nest$fgetbackgroundPaint(this.this$0));
            r10.save();
            this.path.rewind();
            this.path.addRoundRect(r13, r04, r04, Path.Direction.CW);
            r10.clipPath(this.path);
            super.dispatchDraw(r10);
            r10.restore();
            if (r22 <= (AndroidUtilities.statusBarHeight / 2.0f)) goto L18;
            r3 = true;
        L18:
            updateLightStatusBar(r3);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L9;
            if (r3.getY() >= this.top) goto L9;
            this.this$0.dismiss();
            return true;
        L9:
            return super.dispatchTouchEvent(r3);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r1, View r2, long r3) {
            return super.drawChild(r1, r2, r3);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30));
        }
    }

    public interface Listener {
        default void onHidden() {
        }

        default void onPremiumRequired() {
        }

        void onReported();
    }

    public class Page extends FrameLayout {
        private ButtonWithCounterView button;
        private FrameLayout buttonContainer;
        TLRPC.TL_reportResultAddComment commentOption;
        private final FrameLayout contentView;
        private EditTextCell editTextCell;
        private final BigHeaderCell headerView;
        private final UniversalRecyclerView listView;
        TLRPC.TL_reportResultChooseOption option;
        int pageType;
        TLRPC.TL_channels_sponsoredMessageReportResultChooseOption sponsoredOption;
        final /* synthetic */ ReportBottomSheet this$0;

        /* renamed from: org.telegram.ui.ReportBottomSheet$Page$1, reason: invalid class name */
        public class AnonymousClass1 extends RecyclerView.OnScrollListener {
            final /* synthetic */ Page this$1;
            final /* synthetic */ ReportBottomSheet val$this$0;

            public AnonymousClass1(Page r1, ReportBottomSheet r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView r1, int r2, int r3) {
                Page.m18486$$Nest$fgetcontentView(this.this$1).invalidate();
                ReportBottomSheet.access$1000(this.this$1.this$0).invalidate();
            }
        }

        /* renamed from: org.telegram.ui.ReportBottomSheet$Page$2, reason: invalid class name */
        public class AnonymousClass2 extends EditTextCell {
            final /* synthetic */ Page this$1;

            public AnonymousClass2(Page r1, Context r2, String r3, boolean r4, boolean r5, int r6, Theme.ResourcesProvider r7) {
                this.this$1 = r1;
                super(r2, r3, r4, r5, r6, r7);
            }

            @Override // org.telegram.ui.Cells.EditTextCell
            public void onTextChanged(CharSequence r2) {
                super.onTextChanged(r2);
                if (Page.m18485$$Nest$fgetbutton(this.this$1) == null) goto L13;
                ButtonWithCounterView r22 = Page.m18485$$Nest$fgetbutton(this.this$1);
                Page r1 = this.this$1;
                if (r1.commentOption.optional == false) goto L7;
            L10:
                boolean r12 = true;
            L11:
                r22.setEnabled(r12);
                return;
            L7:
                if (TextUtils.isEmpty(Page.m18487$$Nest$fgeteditTextCell(r1).getText()) == false) goto L10;
                r12 = false;
                goto L11
            }
        }

        public class BigHeaderCell extends FrameLayout {
            public BackDrawable backDrawable;
            private final ImageView btnBack;
            private Runnable onBackClickListener;
            private final TextView textView;
            final /* synthetic */ Page this$1;

            public static /* synthetic */ void $r8$lambda$UpPSpwJEOsf7uh6ijXd5AsFTUtk(BigHeaderCell r0, View r1) {
                r0.lambda$new$0(r1);
            }

            public BigHeaderCell(Page r11, Context r12, Theme.ResourcesProvider r13) {
                this.this$1 = r11;
                super(r12);
                TextView r112 = new TextView(r12);
                this.textView = r112;
                r112.setTypeface(AndroidUtilities.bold());
                r112.setTextSize(1, 20.0f);
                int r2 = 3;
                if (LocaleController.isRTL == false) goto L5;
                int r0 = 5;
            L6:
                r112.setGravity(r0);
                r112.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r13));
                addView(r112);
                ImageView r113 = new ImageView(r12);
                this.btnBack = r113;
                BackDrawable r122 = new BackDrawable(false);
                this.backDrawable = r122;
                r113.setImageDrawable(r122);
                this.backDrawable.setColor(-1);
                if (LocaleController.isRTL == false) goto L9;
                r2 = 5;
            L9:
                addView(r113, LayoutHelper.createFrame(24, 24.0f, r2 | 48, 16.0f, 16.0f, 16.0f, 0.0f));
                r113.setOnClickListener(new ReportBottomSheet$Page$BigHeaderCell$$ExternalSyntheticLambda0(this));
                setCloseImageVisible(true);
                setMinimumHeight(AndroidUtilities.dp(56.0f));
                return;
            L5:
                r0 = 3;
                goto L6
            }

            private /* synthetic */ void lambda$new$0(View r1) {
                Runnable r0 = this.onBackClickListener;
                if (r0 == null) goto L6;
                r0.run();
                return;
            }

            public CharSequence getText() {
                return this.textView.getText();
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
            }

            public void setCloseImageVisible(boolean r11) {
                ImageView r0 = this.btnBack;
                if (r11 == false) goto L5;
                int r1 = 0;
            L6:
                r0.setVisibility(r1);
                TextView r10 = this.textView;
                boolean r02 = LocaleController.isRTL;
                if (r02 == true) goto L11;
                if (r11 == false) goto L11;
                float r6 = 53.0f;
            L12:
                if (r02 == false) goto L15;
                if (r11 == false) goto L15;
                float r8 = 53.0f;
            L16:
                r10.setLayoutParams(LayoutHelper.createFrame(-1, -2.0f, 55, r6, 14.0f, r8, 12.0f));
                return;
            L15:
                r8 = 22.0f;
            L11:
                r6 = 22.0f;
                goto L12
            L5:
                r1 = 8;
                goto L6
            }

            public void setOnBackClickListener(Runnable r1) {
                this.onBackClickListener = r1;
            }

            public void setText(CharSequence r1) {
                this.textView.setText(r1);
            }
        }

        /* renamed from: $r8$lambda$_OcShQbZPWhSUTVsgGQ15x3c-AA, reason: not valid java name */
        public static /* synthetic */ void m18484$r8$lambda$_OcShQbZPWhSUTVsgGQ15x3cAA(Page r0, View r1) {
            r0.lambda$fillItems$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$fB5yPcIA3WpBnZoSrEovLTnwkYI(Page r0) {
            r0.lambda$setOption$1();
        }

        public static /* synthetic */ void $r8$lambda$hq2tLqyaCB41MMwDlfmNTN57R7g(Page r0) {
            r0.lambda$new$0();
        }

        public static /* synthetic */ void $r8$lambda$szBFtkshiIDsEqDf8UGilHkDjL0(Page r0, UItem r1, View r2, int r3, float r4, float r5) {
            r0.onClick(r1, r2, r3, r4, r5);
        }

        /* renamed from: -$$Nest$fgetbutton, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ButtonWithCounterView m18485$$Nest$fgetbutton(Page r0) {
            return r0.button;
        }

        /* renamed from: -$$Nest$fgetcontentView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FrameLayout m18486$$Nest$fgetcontentView(Page r0) {
            return r0.contentView;
        }

        /* renamed from: -$$Nest$fgeteditTextCell, reason: not valid java name */
        public static /* bridge */ /* synthetic */ EditTextCell m18487$$Nest$fgeteditTextCell(Page r0) {
            return r0.editTextCell;
        }

        public Page(ReportBottomSheet r14, Context r15) {
            this.this$0 = r14;
            super(r15);
            FrameLayout r9 = new FrameLayout(r15);
            this.contentView = r9;
            r9.setPadding(0, AndroidUtilities.statusBarHeight, 0, 0);
            r9.setClipToPadding(true);
            addView(r9, LayoutHelper.createFrame(-1, -1, 119));
            BigHeaderCell r0 = new BigHeaderCell(this, r15, ReportBottomSheet.access$500(r14));
            this.headerView = r0;
            r0.setOnBackClickListener(new ReportBottomSheet$Page$$ExternalSyntheticLambda0(this));
            if (ReportBottomSheet.m18478$$Nest$fgetsponsored(r14) == false) goto L6;
            r0.setText(LocaleController.getString(R.string.ReportAd));
        L9:
            r0.backDrawable.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, ReportBottomSheet.access$600(r14)));
            r0.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, ReportBottomSheet.access$700(r14)));
            addView(r0, LayoutHelper.createFrame(-1, -2, 55));
            UniversalRecyclerView r02 = new UniversalRecyclerView(r15, ReportBottomSheet.access$800(r14), 0, true, new ReportBottomSheet$Page$$ExternalSyntheticLambda1(this), new ReportBottomSheet$Page$$ExternalSyntheticLambda2(this), null, ReportBottomSheet.access$900(r14));
            this.listView = r02;
            r02.setClipToPadding(false);
            r02.layoutManager.setReverseLayout(true);
            r02.setOnScrollListener(new AnonymousClass1(this, r14));
            r9.addView(r02, LayoutHelper.createFrame(-1, -1.0f));
            return;
        L6:
            if (ReportBottomSheet.m18479$$Nest$fgetstories(r14) == false) goto L8;
            r0.setText(LocaleController.getString(R.string.ReportStory));
            goto L9
        L8:
            r0.setText(LocaleController.getString(R.string.Report2));
            goto L9
        }

        private /* synthetic */ void lambda$fillItems$2(View r3) {
            if (this.button.isEnabled() == true) goto L5;
            return;
        L5:
            if (this.button.isLoading() == true) goto L10;
            this.button.setLoading(true);
            ReportBottomSheet.m18481$$Nest$msubmitOption(this.this$0, this.headerView.getText(), this.commentOption.option, this.editTextCell.getText().toString());
            return;
        }

        private /* synthetic */ void lambda$new$0() {
            int r0 = this.pageType;
            ReportBottomSheet r1 = this.this$0;
            if (r0 != 0) goto L6;
            r1.dismiss();
            return;
        L6:
            r1.onBackPressed();
        }

        private /* synthetic */ void lambda$setOption$1() {
            AndroidUtilities.showKeyboard(this.editTextCell.editText);
        }

        private void onClick(UItem r1, View r2, int r3, float r4, float r5) {
            if (r1.viewType != 30) goto L24;
            TLRPC.TL_channels_sponsoredMessageReportResultChooseOption r22 = this.sponsoredOption;
            if (r22 == null) goto L10;
            TLRPC.TL_sponsoredMessageReportOption r12 = r22.options.get(r1.id);
            if (r12 == null) goto L25;
            ReportBottomSheet.m18481$$Nest$msubmitOption(this.this$0, r12.text, r12.option, null);
            return;
        L25:
            return;
        L10:
            TLRPC.TL_reportResultChooseOption r23 = this.option;
            if (r23 == null) goto L16;
            TLRPC.TL_messageReportOption r13 = r23.options.get(r1.id);
            if (r13 == null) goto L26;
            ReportBottomSheet.m18481$$Nest$msubmitOption(this.this$0, r13.text, r13.option, null);
            return;
        L26:
            return;
        L16:
            TLRPC.TL_reportResultAddComment r24 = this.commentOption;
            if (r24 == null) goto L22;
            byte[] r14 = r24.option;
            if (r14 == null) goto L27;
            ReportBottomSheet.m18481$$Nest$msubmitOption(this.this$0, null, r14, null);
            return;
        L27:
            return;
        L22:
            ReportBottomSheet.m18481$$Nest$msubmitOption(this.this$0, r1.text, null, null);
            return;
        }

        public boolean atTop() {
            return !this.listView.canScrollVertically(-1);
        }

        public void bind(int r3) {
            this.pageType = r3;
            BigHeaderCell r0 = this.headerView;
            if (r3 == 0) goto L5;
            boolean r32 = true;
        L6:
            r0.setCloseImageVisible(r32);
            UniversalRecyclerView r2 = this.listView;
            if (r2 == null) goto L10;
            r2.adapter.update(true);
            return;
        L10:
            return;
        L5:
            r32 = false;
            goto L6
        }

        public void fillItems(ArrayList<UItem> r14, UniversalAdapter r15) {
            if (this.headerView.getMeasuredHeight() > 0) goto L5;
            this.headerView.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.displaySize.x, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(120.0f), Integer.MIN_VALUE));
        L5:
            UItem r152 = UItem.asSpace(this.headerView.getMeasuredHeight());
            r152.id = -1;
            r152.transparent = true;
            r14.add(r152);
            int r153 = (int) (0.0f + (this.headerView.getMeasuredHeight() / AndroidUtilities.density));
            TLRPC.TL_channels_sponsoredMessageReportResultChooseOption r1 = this.sponsoredOption;
            if (r1 == null) goto L8;
        L13:
            if (r1 == null) goto L15;
        L16:
            HeaderCell r3 = new HeaderCell(getContext(), Theme.key_windowBackgroundWhiteBlueHeader, 21, 0, 0, false, ReportBottomSheet.access$1100(this.this$0));
            TLRPC.TL_channels_sponsoredMessageReportResultChooseOption r12 = this.sponsoredOption;
            if (r12 == null) goto L19;
            r3.setText(r12.title);
        L22:
            r3.setBackgroundColor(ReportBottomSheet.access$1200(this.this$0, Theme.key_dialogBackground));
            UItem r13 = UItem.asCustom(r3);
            r13.id = -2;
            r14.add(r13);
            r153 = r153 + 40;
        L24:
            if (this.sponsoredOption == null) goto L31;
            int r16 = 0;
        L27:
            if (r16 >= this.sponsoredOption.options.size()) goto L29;
            UItem r5 = new UItem(30, false);
            r5.text = this.sponsoredOption.options.get(r16).text;
            r5.iconResId = R.drawable.msg_arrowright;
            r5.id = r16;
            r14.add(r5);
            r153 = r153 + 50;
            r16 = r16 + 1;
        L29:
            Page r52 = this;
        L73:
            r14.get(r14.size() - 1).hideDivider = true;
            if (ReportBottomSheet.m18478$$Nest$fgetsponsored(r52.this$0) == true) goto L76;
        L79:
            if (r52.listView != null) goto L81;
            return;
        L81:
            if ((ReportBottomSheet.access$2200(r52.this$0).getMeasuredHeight() - AndroidUtilities.statusBarHeight) >= AndroidUtilities.dp(r153)) goto L84;
            r52.listView.layoutManager.setReverseLayout(false);
            return;
        L84:
            Collections.reverse(r14);
            r52.listView.layoutManager.setReverseLayout(true);
            return;
        L76:
            if (r52.pageType != 0) goto L79;
            FrameLayout r132 = new FrameLayout(r52.getContext());
            CombinedDrawable r6 = new CombinedDrawable(new ColorDrawable(ReportBottomSheet.access$1900(r52.this$0, Theme.key_windowBackgroundGray)), Theme.getThemedDrawable(r52.getContext(), R.drawable.greydivider, Theme.getColor(Theme.key_windowBackgroundGrayShadow, ReportBottomSheet.access$1800(r52.this$0))), 0, 0);
            r6.setFullsize(true);
            r132.setBackground(r6);
            LinkSpanDrawable.LinksTextView r17 = new LinkSpanDrawable.LinksTextView(r52.getContext());
            r17.setTextSize(1, 14.0f);
            r17.setText(AndroidUtilities.replaceLinks(LocaleController.getString(R.string.ReportAdLearnMore), ReportBottomSheet.access$2000(r52.this$0)));
            r17.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3, ReportBottomSheet.access$2100(r52.this$0)));
            r17.setGravity(17);
            r132.addView(r17, LayoutHelper.createFrame(-1, -2.0f, 17, 16.0f, 16.0f, 16.0f, 16.0f));
            UItem r133 = UItem.asCustom(r132);
            r133.id = -3;
            r14.add(r133);
            r153 = r153 + 46;
            goto L79
        L31:
            if (this.option == null) goto L37;
            int r18 = 0;
        L34:
            if (r18 >= this.option.options.size()) goto L29;
            UItem r53 = new UItem(30, false);
            r53.text = this.option.options.get(r18).text;
            r53.iconResId = R.drawable.msg_arrowright;
            r53.id = r18;
            r14.add(r53);
            r153 = r153 + 50;
            r18 = r18 + 1;
            goto L34
        L37:
            if (this.commentOption == null) goto L29;
            if (this.editTextCell != null) goto L41;
            r52 = this;
            AnonymousClass2 r4 = new AnonymousClass2(r52, getContext(), _UrlKt.FRAGMENT_ENCODE_SET, true, false, 1024, ReportBottomSheet.access$1300(this.this$0));
            r52.editTextCell = r4;
            r4.setShowLimitWhenNear(100);
        L42:
            EditTextCaption r134 = r52.editTextCell.editText;
            if (r52.commentOption.optional == false) goto L45;
            int r19 = R.string.Report2CommentOptional;
        L46:
            r134.setHint(LocaleController.getString(r19));
            UItem r135 = UItem.asCustom(r52.editTextCell);
            r135.id = -3;
            r14.add(r135);
            if (ReportBottomSheet.m18477$$Nest$fgetmessageIds(r52.this$0) == null) goto L56;
            if (ReportBottomSheet.m18477$$Nest$fgetmessageIds(r52.this$0).isEmpty() == true) goto L56;
            if (ReportBottomSheet.m18477$$Nest$fgetmessageIds(r52.this$0).size() <= 1) goto L53;
            int r136 = R.string.Report2CommentInfoMany;
        L54:
            r14.add(UItem.asShadow(LocaleController.getString(r136)));
        L63:
            if (r52.buttonContainer != null) goto L65;
            ButtonWithCounterView r137 = new ButtonWithCounterView(r52.getContext(), ReportBottomSheet.access$1500(r52.this$0));
            r52.button = r137;
            r137.setText(LocaleController.getString(R.string.Report2Send), false);
            FrameLayout r138 = new FrameLayout(r52.getContext());
            r52.buttonContainer = r138;
            r138.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, ReportBottomSheet.access$1600(r52.this$0)));
            r52.buttonContainer.addView(r52.button, LayoutHelper.createFrame(-1, 48.0f, 119, 12.0f, 12.0f, 12.0f, 12.0f));
            View r139 = new View(r52.getContext());
            r139.setBackgroundColor(Theme.getColor(Theme.key_divider, ReportBottomSheet.access$1700(r52.this$0)));
            r52.buttonContainer.addView(r139, LayoutHelper.createFrame(-1.0f, 1.0f / AndroidUtilities.density, 48));
        L65:
            ButtonWithCounterView r1310 = r52.button;
            if (r52.commentOption.optional == false) goto L68;
        L71:
            boolean r110 = true;
        L72:
            r1310.setEnabled(r110);
            r52.button.setOnClickListener(new ReportBottomSheet$Page$$ExternalSyntheticLambda3(r52));
            UItem r1311 = UItem.asCustom(r52.buttonContainer);
            r1311.id = -4;
            r14.add(r1311);
            r153 = r153 + 112;
            goto L73
        L68:
            if (TextUtils.isEmpty(r52.editTextCell.getText()) == false) goto L71;
            r110 = false;
            goto L72
        L53:
            r136 = R.string.Report2CommentInfo;
        L56:
            if (DialogObject.isUserDialog(ReportBottomSheet.m18476$$Nest$fgetdialogId(r52.this$0)) == false) goto L59;
            r14.add(UItem.asShadow(LocaleController.getString(R.string.Report2CommentInfoUser)));
            goto L63
        L59:
            if (ChatObject.isChannelAndNotMegaGroup(MessagesController.getInstance(ReportBottomSheet.access$1400(r52.this$0)).getChat(Long.valueOf(-ReportBottomSheet.m18476$$Nest$fgetdialogId(r52.this$0)))) == false) goto L61;
            r14.add(UItem.asShadow(LocaleController.getString(R.string.Report2CommentInfoChannel)));
            goto L63
        L61:
            r14.add(UItem.asShadow(LocaleController.getString(R.string.Report2CommentInfoGroup)));
            goto L63
        L45:
            r19 = R.string.Report2Comment;
            goto L46
        L41:
            r52 = this;
            goto L42
        L19:
            TLRPC.TL_reportResultChooseOption r111 = this.option;
            if (r111 == null) goto L22;
            r3.setText(r111.title);
            goto L22
        L15:
            if (this.option == null) goto L24;
        L8:
            if (this.option != null) goto L13;
            if (this.commentOption != null) goto L13;
            r52 = this;
            goto L79
        }

        public void setHeaderText(CharSequence r4) {
            this.headerView.setText(r4);
            this.headerView.getText();
            this.headerView.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.displaySize.x, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(120.0f), Integer.MIN_VALUE));
            UniversalRecyclerView r3 = this.listView;
            if (r3 == null) goto L6;
            r3.adapter.update(true);
            return;
        }

        public void setOption(TLRPC.TL_channels_sponsoredMessageReportResultChooseOption r1) {
            this.sponsoredOption = r1;
            this.option = null;
            this.commentOption = null;
            this.listView.adapter.update(false);
        }

        public void setOption(TLRPC.TL_reportResultAddComment r3) {
            this.sponsoredOption = null;
            this.option = null;
            this.commentOption = r3;
            this.listView.adapter.update(false);
            if (this.editTextCell == null) goto L6;
            AndroidUtilities.runOnUIThread(new ReportBottomSheet$Page$$ExternalSyntheticLambda4(this), 120);
            return;
        }

        public void setOption(TLRPC.TL_reportResultChooseOption r2) {
            this.sponsoredOption = null;
            this.option = r2;
            this.commentOption = null;
            this.listView.adapter.update(false);
        }

        public float top() {
            float r0 = this.contentView.getPaddingTop();
            int r1 = 0;
        L4:
            if (r1 >= this.listView.getChildCount()) goto L16;
            View r2 = this.listView.getChildAt(r1);
            int r3 = this.listView.layoutManager.getPosition(r2);
            if (r3 < 0) goto L15;
            if (r3 >= this.listView.adapter.getItemCount()) goto L15;
            UItem r32 = this.listView.adapter.getItem(r3);
            if (r32 == null) goto L15;
            if (r32.viewType != 28) goto L15;
            r0 = this.contentView.getPaddingTop() + r2.getY();
        L15:
            r1 = r1 + 1;
            goto L4
        L16:
            return r0;
        }

        public void updateTops() {
            float r0 = -this.headerView.getHeight();
            int r1 = 0;
        L4:
            if (r1 >= this.listView.getChildCount()) goto L9;
            View r2 = this.listView.getChildAt(r1);
            if (this.listView.adapter.getItem(this.listView.layoutManager.getPosition(r2)).viewType == 28) goto L7;
            r1 = r1 + 1;
            goto L4
        L7:
            r0 = this.contentView.getPaddingTop() + r2.getY();
        L9:
            this.headerView.setTranslationY(Math.max(AndroidUtilities.statusBarHeight, r0));
        }
    }

    public static /* synthetic */ void $r8$lambda$0aw8SqWr6se4bNPMIbh62CWZg6E(BaseFragment r3, Context r4, Theme.ResourcesProvider r5, Runnable r6) {
        BulletinFactory.of(r3).createAdReportedBulletin(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.AdReported), -1, 2, new ReportBottomSheet$$ExternalSyntheticLambda1(r4), r5)).show();
        AndroidUtilities.runOnUIThread(r6);
    }

    public static /* synthetic */ void $r8$lambda$4dP6GcZQU6PjDemQ6CNeJ9bRhzI(ReportBottomSheet r0, CharSequence r1, byte[] r2, String r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$submitOption$4(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$8haaZv15Y15mI0zOTs3LBnsjpBA(Context r1) {
        Browser.openUrl(r1, "https://promote.telegram.org/guidelines");
    }

    /* renamed from: $r8$lambda$92oRY-a4mCXnLDlDWt2AN7RURpw, reason: not valid java name */
    public static /* synthetic */ void m18467$r8$lambda$92oRYa4mCXnLDlDWt2AN7RURpw(BaseFragment r3, Context r4, Theme.ResourcesProvider r5, Runnable r6) {
        BulletinFactory.of(r3).createAdReportedBulletin(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.AdReported), -1, 2, new ReportBottomSheet$$ExternalSyntheticLambda0(r4), r5)).show();
        AndroidUtilities.runOnUIThread(r6);
    }

    public static /* synthetic */ void $r8$lambda$9uQCA3prmXbd8k4AqaaI_7WqJZs(View[] r1, TLRPC.TL_reportResultChooseOption r2) {
        ((Page) r1[0]).setOption(r2);
    }

    /* renamed from: $r8$lambda$A-Lfgc_nTRpY5Nos12Z3z6rPK9Q, reason: not valid java name */
    public static /* synthetic */ void m18468$r8$lambda$ALfgc_nTRpY5Nos12Z3z6rPK9Q(Runnable r2, BulletinFactory r3, Context r4, Theme.ResourcesProvider r5) {
        if (r2 == null) goto L4;
        r2.run();
    L4:
        r3.createAdReportedBulletin(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.AdReported), -1, 2, new ReportBottomSheet$$ExternalSyntheticLambda13(r4), r5)).show();
    }

    /* renamed from: $r8$lambda$AOQxIR-_uVOsxpTYabOEs-GgrUU, reason: not valid java name */
    public static /* synthetic */ void m18469$r8$lambda$AOQxIR_uVOsxpTYabOEsGgrUU(Context r1) {
        Browser.openUrl(r1, "https://promote.telegram.org/guidelines");
    }

    public static /* synthetic */ void $r8$lambda$AQ5MNSBPftcFtQalPgZYIk7T_Aw(ReportBottomSheet r0, TLObject r1, CharSequence r2, TLRPC.TL_error r3, byte[] r4, String r5) {
        r0.lambda$submitOption$3(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$DdbvQ1AdVDFEvfY4VDSDQYMcg7I(TLObject r6, Context r7, Theme.ResourcesProvider r8, long r9, byte[] r11, ChatActivity r12, MessageObject r13) {
        ReportBottomSheet r0 = new ReportBottomSheet(r7, r8, r9, r11);
        r0.setReportChooseOption((TLRPC.TL_channels_sponsoredMessageReportResultChooseOption) r6).setListener(new AnonymousClass4(r12, r7, r8, r13)).show();
    }

    public static /* synthetic */ void $r8$lambda$E8gLE8NDPDAmCdIxkyetrnDw60s(View[] r1, TLRPC.TL_channels_sponsoredMessageReportResultChooseOption r2) {
        ((Page) r1[0]).setOption(r2);
    }

    public static /* synthetic */ void $r8$lambda$ETelEKv7CkBWtzTNzC102Hg34KI(boolean[] r2, Utilities.Callback r3) {
        if (r2[0] == true) goto L7;
        if (r3 == null) goto L8;
        r2[0] = true;
        r3.run(Boolean.FALSE);
        return;
    L8:
        return;
    }

    /* renamed from: $r8$lambda$GIqWoatFNfFEZD_4-OrXm0RVSIg, reason: not valid java name */
    public static /* synthetic */ void m18470$r8$lambda$GIqWoatFNfFEZD_4OrXm0RVSIg(boolean[] r2, Utilities.Callback r3) {
        if (r2[0] == true) goto L6;
        if (r3 == null) goto L6;
        r2[0] = true;
        r3.run(Boolean.TRUE);
    L6:
        AndroidUtilities.runOnUIThread(new ReportBottomSheet$$ExternalSyntheticLambda20(), 220);
    }

    /* renamed from: $r8$lambda$LTnGfEHDzi-3dyaeztujUYNjGqQ, reason: not valid java name */
    public static /* synthetic */ void m18471$r8$lambda$LTnGfEHDzi3dyaeztujUYNjGqQ(Context r1) {
        Browser.openUrl(r1, "https://promote.telegram.org/guidelines");
    }

    public static /* synthetic */ void $r8$lambda$M33PR0JD7PwlA2Pa4KZy6QZ0tv8(Context r2, Theme.ResourcesProvider r3, byte[] r4, BaseFragment r5, Runnable r6, int r7, TLObject r8, TLRPC.TL_error r9) {
        if (r8 == null) goto L17;
        if ((r8 instanceof TLRPC.TL_channels_sponsoredMessageReportResultChooseOption) == false) goto L9;
        AndroidUtilities.runOnUIThread(new ReportBottomSheet$$ExternalSyntheticLambda26(r8, r2, r3, r4, r5, r6));
        return;
    L9:
        if ((r8 instanceof TLRPC.TL_channels_sponsoredMessageReportResultReported) == false) goto L13;
        AndroidUtilities.runOnUIThread(new ReportBottomSheet$$ExternalSyntheticLambda27(r5, r2, r3, r6), 200);
        return;
    L13:
        if ((r8 instanceof TLRPC.TL_channels_sponsoredMessageReportResultAdsHidden) == false) goto L22;
        AndroidUtilities.runOnUIThread(new ReportBottomSheet$$ExternalSyntheticLambda28(r5, r7, r6), 200);
        return;
    L22:
        return;
    L17:
        if (r9 != null) goto L19;
        return;
    L19:
        if ("AD_EXPIRED".equalsIgnoreCase(r9.text) == false) goto L24;
        AndroidUtilities.runOnUIThread(new ReportBottomSheet$$ExternalSyntheticLambda29(r5, r2, r3, r6), 200);
        return;
    }

    /* renamed from: $r8$lambda$OSdbB-aCg9lugoxvZep62SoMazY, reason: not valid java name */
    public static /* synthetic */ void m18472$r8$lambda$OSdbBaCg9lugoxvZep62SoMazY() {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 == null) goto L10;
        BulletinFactory r02 = BulletinFactory.of(r0);
        if (r02 != null) goto L8;
        return;
    L8:
        r02.createSimpleBulletin(R.raw.msg_antispam, LocaleController.getString(R.string.ReportChatSent), LocaleController.getString(R.string.Reported2)).setDuration(5000).show();
        return;
    }

    public static /* synthetic */ void $r8$lambda$PPSjjvmWMonRZbPSMqlQPPL9uOM(View[] r1, TLRPC.TL_reportResultAddComment r2) {
        ((Page) r1[0]).setOption(r2);
    }

    public static /* synthetic */ void $r8$lambda$TGUlWaKzqIodsbi8UQkYGcaKfDQ(Context r1) {
        Browser.openUrl(r1, "https://promote.telegram.org/guidelines");
    }

    public static /* synthetic */ void $r8$lambda$Vx4tQF5swlp5mjAxW9SZy7xSmpw(ChatActivity r2, int r3, MessageObject r4) {
        BulletinFactory.of(r2).createAdReportedBulletin(LocaleController.getString(R.string.AdHidden)).show();
        MessagesController.getInstance(r3).disableAds(false);
        r2.removeFromSponsored(r4);
        r2.removeMessageWithThanos(r4);
    }

    public static /* synthetic */ void $r8$lambda$ZEABfxfLjnp8iOagj6PEZ6xgh8g(Runnable r2, BulletinFactory r3, Context r4, Theme.ResourcesProvider r5) {
        if (r2 == null) goto L4;
        r2.run();
    L4:
        r3.createAdReportedBulletin(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.AdReported), -1, 2, new ReportBottomSheet$$ExternalSyntheticLambda9(r4), r5)).show();
    }

    public static /* synthetic */ void $r8$lambda$cOuaTRJJuuTCTNveFEw6k_AysGI(ChatActivity r4, Context r5, Theme.ResourcesProvider r6, MessageObject r7) {
        BulletinFactory.of(r4).createAdReportedBulletin(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.AdReported), -1, 2, new ReportBottomSheet$$ExternalSyntheticLambda21(r5), r6)).show();
        r4.removeFromSponsored(r7);
        r4.removeMessageWithThanos(r7);
    }

    public static /* synthetic */ void $r8$lambda$cVCd8JcyjbXBr7LbGe5qY3Z8oEU(Context r4, Theme.ResourcesProvider r5, long r6, byte[] r8, ChatActivity r9, MessageObject r10, int r11, TLObject r12, TLRPC.TL_error r13) {
        if (r12 == null) goto L17;
        if ((r12 instanceof TLRPC.TL_channels_sponsoredMessageReportResultChooseOption) == false) goto L9;
        AndroidUtilities.runOnUIThread(new ReportBottomSheet$$ExternalSyntheticLambda16(r12, r4, r5, r6, r8, r9, r10));
        return;
    L9:
        if ((r12 instanceof TLRPC.TL_channels_sponsoredMessageReportResultReported) == false) goto L13;
        AndroidUtilities.runOnUIThread(new ReportBottomSheet$$ExternalSyntheticLambda17(r9, r4, r5, r10), 200);
        return;
    L13:
        if ((r12 instanceof TLRPC.TL_channels_sponsoredMessageReportResultAdsHidden) == false) goto L22;
        AndroidUtilities.runOnUIThread(new ReportBottomSheet$$ExternalSyntheticLambda18(r9, r11, r10), 200);
        return;
    L22:
        return;
    L17:
        if (r13 != null) goto L19;
        return;
    L19:
        if ("AD_EXPIRED".equalsIgnoreCase(r13.text) == false) goto L24;
        AndroidUtilities.runOnUIThread(new ReportBottomSheet$$ExternalSyntheticLambda19(r9, r4, r5, r10), 200);
        return;
    }

    public static /* synthetic */ void $r8$lambda$kG5kLr86n9bzWf8S5R7AWvhoFPY(TLObject r6, Context r7, Theme.ResourcesProvider r8, byte[] r9, BaseFragment r10, Runnable r11) {
        ReportBottomSheet r0 = new ReportBottomSheet(r7, r8, 0, r9);
        r0.setReportChooseOption((TLRPC.TL_channels_sponsoredMessageReportResultChooseOption) r6).setListener(new AnonymousClass6(r10, r7, r8, r11)).show();
    }

    public static /* synthetic */ void $r8$lambda$nRzlwNRI7_41hfGrnqV_4T8NfTI(Context r12, Theme.ResourcesProvider r13, boolean r14, boolean r15, long r16, ArrayList r18, boolean[] r19, Utilities.Callback r20, BulletinFactory r21, TLRPC.ReportResult r22, TLRPC.TL_error r23) {
        boolean r3 = r22 instanceof TLRPC.TL_reportResultChooseOption;
        if (r3 == false) goto L5;
    L9:
        ReportBottomSheet r4 = new ReportBottomSheet(r12, r13, r14, r15, r16, r18);
        if (r3 == false) goto L13;
        r4.setReportChooseOption((TLRPC.TL_reportResultChooseOption) r22);
    L15:
        r4.setListener(new AnonymousClass3(r19, r20, r21));
        r4.setOnDismissListener(new ReportBottomSheet$$ExternalSyntheticLambda11(r19, r20));
        r4.show();
        return;
    L13:
        if ((r22 instanceof TLRPC.TL_reportResultAddComment) == false) goto L15;
        r4.setReportChooseOption((TLRPC.TL_reportResultAddComment) r22);
        goto L15
    L5:
        if ((r22 instanceof TLRPC.TL_reportResultAddComment) == true) goto L9;
        AndroidUtilities.runOnUIThread(new ReportBottomSheet$$ExternalSyntheticLambda12(r19, r20), 200);
    }

    public static /* synthetic */ void $r8$lambda$oofvxxPPggtX70pnF4kkpw6bKNM(BaseFragment r1, int r2, Runnable r3) {
        BulletinFactory.of(r1).createAdReportedBulletin(LocaleController.getString(R.string.AdHidden)).show();
        MessagesController.getInstance(r2).disableAds(false);
        AndroidUtilities.runOnUIThread(r3);
    }

    public static /* synthetic */ void $r8$lambda$qYFyF_D3yo988X5NIFslYZgbrtY(Context r1) {
        Browser.openUrl(r1, "https://promote.telegram.org/guidelines");
    }

    public static /* synthetic */ void $r8$lambda$qkcZbGd9XrjuAyWbw0rpZ_kL7Yk(Context r1) {
        Browser.openUrl(r1, "https://promote.telegram.org/guidelines");
    }

    /* renamed from: $r8$lambda$sQvvE51m0RJrZHA1-pxtA_hk61A, reason: not valid java name */
    public static /* synthetic */ void m18473$r8$lambda$sQvvE51m0RJrZHA1pxtA_hk61A(ChatActivity r4, Context r5, Theme.ResourcesProvider r6, MessageObject r7) {
        BulletinFactory.of(r4).createAdReportedBulletin(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.AdReported), -1, 2, new ReportBottomSheet$$ExternalSyntheticLambda22(r5), r6)).show();
        r4.removeFromSponsored(r7);
        r4.removeMessageWithThanos(r7);
    }

    /* renamed from: $r8$lambda$sk9k1CwXA-o34W_LMyeQhzN2BzA, reason: not valid java name */
    public static /* synthetic */ void m18474$r8$lambda$sk9k1CwXAo34W_LMyeQhzN2BzA(Context r4, Theme.ResourcesProvider r5, long r6, byte[] r8, Runnable r9, BulletinFactory r10, Runnable r11, int r12, TLObject r13, TLRPC.TL_error r14) {
        if (r13 == null) goto L17;
        if ((r13 instanceof TLRPC.TL_channels_sponsoredMessageReportResultChooseOption) == false) goto L9;
        AndroidUtilities.runOnUIThread(new ReportBottomSheet$$ExternalSyntheticLambda3(r13, r4, r5, r6, r8, r9, r10, r11));
        return;
    L9:
        if ((r13 instanceof TLRPC.TL_channels_sponsoredMessageReportResultReported) == false) goto L13;
        AndroidUtilities.runOnUIThread(new ReportBottomSheet$$ExternalSyntheticLambda4(r9, r10, r4, r5), 200);
        return;
    L13:
        if ((r13 instanceof TLRPC.TL_channels_sponsoredMessageReportResultAdsHidden) == false) goto L22;
        AndroidUtilities.runOnUIThread(new ReportBottomSheet$$ExternalSyntheticLambda5(r9, r10, r12), 200);
        return;
    L22:
        return;
    L17:
        if (r14 != null) goto L19;
        return;
    L19:
        if ("AD_EXPIRED".equalsIgnoreCase(r14.text) == false) goto L24;
        AndroidUtilities.runOnUIThread(new ReportBottomSheet$$ExternalSyntheticLambda6(r9, r10, r4, r5), 200);
        return;
    }

    public static /* synthetic */ void $r8$lambda$wQuWH8mAKGtm9Fwelk27OlkOrGI(Runnable r0, BulletinFactory r1, int r2) {
        if (r0 == null) goto L4;
        r0.run();
    L4:
        r1.createAdReportedBulletin(LocaleController.getString(R.string.AdHidden)).show();
        MessagesController.getInstance(r2).disableAds(false);
    }

    public static /* synthetic */ void $r8$lambda$x775AWah1XJjx3WPqEtFs93jXEA(TLObject r6, Context r7, Theme.ResourcesProvider r8, long r9, byte[] r11, Runnable r12, BulletinFactory r13, Runnable r14) {
        ReportBottomSheet r0 = new ReportBottomSheet(r7, r8, r9, r11);
        r0.setReportChooseOption((TLRPC.TL_channels_sponsoredMessageReportResultChooseOption) r6).setListener(new AnonymousClass5(r12, r13, r7, r8, r14)).show();
    }

    /* renamed from: -$$Nest$fgetbackgroundPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m18475$$Nest$fgetbackgroundPaint(ReportBottomSheet r0) {
        return r0.backgroundPaint;
    }

    /* renamed from: -$$Nest$fgetdialogId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m18476$$Nest$fgetdialogId(ReportBottomSheet r2) {
        return r2.dialogId;
    }

    /* renamed from: -$$Nest$fgetmessageIds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m18477$$Nest$fgetmessageIds(ReportBottomSheet r0) {
        return r0.messageIds;
    }

    /* renamed from: -$$Nest$fgetsponsored, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18478$$Nest$fgetsponsored(ReportBottomSheet r0) {
        return r0.sponsored;
    }

    /* renamed from: -$$Nest$fgetstories, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18479$$Nest$fgetstories(ReportBottomSheet r0) {
        return r0.stories;
    }

    /* renamed from: -$$Nest$fgetviewPager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewPagerFixed m18480$$Nest$fgetviewPager(ReportBottomSheet r0) {
        return r0.viewPager;
    }

    /* renamed from: -$$Nest$msubmitOption, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18481$$Nest$msubmitOption(ReportBottomSheet r0, CharSequence r1, byte[] r2, String r3) {
        r0.submitOption(r1, r2, r3);
    }

    private ReportBottomSheet(Context r11, Theme.ResourcesProvider r12, long r13, byte[] r15) {
        this(true, r11, r12, r13, false, false, null, r15);
    }

    private ReportBottomSheet(Context r11, Theme.ResourcesProvider r12, boolean r13, boolean r14, long r15, ArrayList<Integer> r17) {
        this(false, r11, r12, r15, r13, r14, r17, null);
    }

    private ReportBottomSheet(boolean r3, Context r4, Theme.ResourcesProvider r5, long r6, boolean r8, boolean r9, ArrayList<Integer> r10, byte[] r11) {
        super(r4, true, r5);
        Paint r1 = new Paint(1);
        this.backgroundPaint = r1;
        this.sponsored = r3;
        this.messageIds = r10;
        this.stories = r8;
        this.ephemeral = r9;
        this.sponsoredId = r11;
        this.dialogId = r6;
        int r62 = Theme.key_dialogBackground;
        r1.setColor(Theme.getColor(r62, r5));
        fixNavigationBar(Theme.getColor(r62, r5));
        this.smoothKeyboardAnimationEnabled = true;
        this.smoothKeyboardByBottom = true;
        this.containerView = new ContainerView(this, r4);
        AnonymousClass1 r52 = new AnonymousClass1(this, r4);
        this.viewPager = r52;
        int r63 = this.backgroundPaddingLeft;
        r52.setPadding(r63, 0, r63, 0);
        this.containerView.addView(r52, LayoutHelper.createFrame(-1, -1, 119));
        r52.setAdapter(new AnonymousClass2(this, r4));
        if (r10 != null) goto L11;
        if (r11 == null) goto L6;
        return;
    L6:
        if (r3 == false) goto L9;
        setReportChooseOption(null);
        return;
    L9:
        setReportChooseOption(null);
        return;
    }

    public static /* synthetic */ ViewGroup access$000(ReportBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$100(ReportBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ ViewGroup access$1000(ReportBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1100(ReportBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$1200(ReportBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1300(ReportBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$1400(ReportBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1500(ReportBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1600(ReportBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1700(ReportBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1800(ReportBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$1900(ReportBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$200(ReportBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2000(ReportBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2100(ReportBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ ViewGroup access$2200(ReportBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$300(ReportBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$400(ReportBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$500(ReportBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$600(ReportBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$700(ReportBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$800(ReportBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$900(ReportBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static void continueReport(ChatActivity r12, byte[] r13, String r14, ArrayList<Integer> r15, Utilities.Callback<Boolean> r16) {
        if (r12 == null) goto L9;
        int r0 = r12.getCurrentAccount();
        Context r1 = r12.getContext();
        long r2 = r12.getDialogId();
        if (r1 != null) goto L7;
        return;
    L7:
        open(r0, r1, r2, false, false, r15, BulletinFactory.of(r12), r12.getResourceProvider(), r13, r14, r16);
        return;
    }

    private /* synthetic */ void lambda$submitOption$3(TLObject r4, CharSequence r5, TLRPC.TL_error r6, byte[] r7, String r8) {
        if ((this.viewPager.getCurrentView() instanceof Page) == false) goto L7;
        Page r0 = (Page) this.viewPager.getCurrentView();
        if (Page.m18485$$Nest$fgetbutton(r0) == null) goto L7;
        Page.m18485$$Nest$fgetbutton(r0).setLoading(false);
    L7:
        if (r4 == null) goto L42;
        boolean r62 = r4 instanceof TLRPC.TL_channels_sponsoredMessageReportResultChooseOption;
        if (r62 == false) goto L11;
    L29:
        ViewPagerFixed r72 = this.viewPager;
        r72.scrollToPosition(r72.currentPosition + 1);
        Page r3 = (Page) this.viewPager.getViewPages()[1];
        if (r3 != null) goto L32;
        return;
    L32:
        if ((r4 instanceof TLRPC.TL_reportResultChooseOption) == false) goto L35;
        r3.setOption((TLRPC.TL_reportResultChooseOption) r4);
    L39:
        if (r5 == null) goto L64;
        r3.setHeaderText(r5);
        return;
    L64:
        return;
    L35:
        if ((r4 instanceof TLRPC.TL_reportResultAddComment) == false) goto L37;
        r3.setOption((TLRPC.TL_reportResultAddComment) r4);
        goto L39
    L37:
        if (r62 == false) goto L39;
        r3.setOption((TLRPC.TL_channels_sponsoredMessageReportResultChooseOption) r4);
        goto L39
    L11:
        if ((r4 instanceof TLRPC.TL_reportResultChooseOption) == true) goto L29;
        if ((r4 instanceof TLRPC.TL_reportResultAddComment) == true) goto L29;
        if ((r4 instanceof TLRPC.TL_channels_sponsoredMessageReportResultAdsHidden) == false) goto L22;
        MessagesController.getInstance(this.currentAccount).disableAds(false);
        Listener r42 = this.listener;
        if (r42 == null) goto L60;
        r42.onHidden();
        dismiss();
        return;
    L60:
        return;
    L22:
        if ((r4 instanceof TLRPC.TL_channels_sponsoredMessageReportResultReported) == false) goto L24;
    L25:
        Listener r43 = this.listener;
        if (r43 == null) goto L62;
        r43.onReported();
        dismiss();
        return;
    L62:
        return;
    L24:
        if ((r4 instanceof TLRPC.TL_reportResultReported) == true) goto L25;
        return;
    L42:
        if (r6 != null) goto L44;
        return;
    L44:
        if (this.sponsored == true) goto L49;
        if ("MESSAGE_ID_REQUIRED".equals(r6.text) == false) goto L49;
        ChatActivity.openReportChat(this.dialogId, r5.toString(), r7, r8);
    L58:
        dismiss();
        return;
    L49:
        if (MediaError.ERROR_REASON_PREMIUM_ACCOUNT_REQUIRED.equals(r6.text) == false) goto L54;
        Listener r44 = this.listener;
        if (r44 == null) goto L58;
        r44.onPremiumRequired();
        goto L58
    L54:
        if ("AD_EXPIRED".equals(r6.text) == false) goto L58;
        Listener r45 = this.listener;
        if (r45 == null) goto L58;
        r45.onReported();
        goto L58
    }

    private /* synthetic */ void lambda$submitOption$4(CharSequence r8, byte[] r9, String r10, TLObject r11, TLRPC.TL_error r12) {
        AndroidUtilities.runOnUIThread(new ReportBottomSheet$$ExternalSyntheticLambda24(this, r11, r8, r12, r9, r10));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void open(int r13, Context r14, long r15, boolean r17, boolean r18, ArrayList<Integer> r19, BulletinFactory r20, Theme.ResourcesProvider r21, byte[] r22, String r23, Utilities.Callback<Boolean> r24) {
        if (r14 == null) goto L30;
        if (r19 == null) goto L31;
        boolean[] r8 = {false};
        String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r17 == false) goto L14;
        TL_stories.TL_stories_report r1 = new TL_stories.TL_stories_report();
        r1.peer = MessagesController.getInstance(r13).getInputPeer(r15);
        r1.id.addAll(r19);
        r1.option = r22;
        if (TextUtils.isEmpty(r23) == true) goto L12;
        r2 = r23;
    L12:
        r1.message = r2;
        TLRPC.TL_messages_report r12 = r1;
    L13:
        TLRPC.TL_messages_report r11 = r12;
    L28:
        ConnectionsManager.getInstance(r13).sendRequestTyped(r11, new AiTonesController$$ExternalSyntheticLambda0(), new ReportBottomSheet$$ExternalSyntheticLambda7(r14, r21, r17, r18, r15, r19, r8, r24, r20));
        return;
    L14:
        if (r18 == false) goto L23;
        TL_ephemeral.TL_reportMessage r3 = new TL_ephemeral.TL_reportMessage();
        r3.peer = MessagesController.getInstance(r13).getInputPeer(r15);
        if (r19.isEmpty() == true) goto L19;
        r3.id = r19.get(0).intValue();
    L19:
        if (TextUtils.isEmpty(r23) == true) goto L22;
        r2 = r23;
    L22:
        r3.message = r2;
        r3.option = r22;
        r11 = r3;
        goto L28
    L23:
        TLRPC.TL_messages_report r16 = new TLRPC.TL_messages_report();
        r16.peer = MessagesController.getInstance(r13).getInputPeer(r15);
        r16.id.addAll(r19);
        r16.option = r22;
        if (TextUtils.isEmpty(r23) == true) goto L27;
        r2 = r23;
    L27:
        r16.message = r2;
        r12 = r16;
        goto L13
    L31:
        return;
    }

    public static void openChat(int r12, Context r13, BulletinFactory r14, long r15) {
        open(r12, r13, r15, false, false, new ArrayList(), r14, null, new byte[0], null, null);
    }

    public static void openChat(BaseFragment r12, long r13) {
        if (r12 == null) goto L9;
        int r0 = r12.getCurrentAccount();
        Context r1 = r12.getContext();
        if (r1 != null) goto L7;
        return;
    L7:
        open(r0, r1, r13, false, false, new ArrayList(), null, null, new byte[0], null, null);
        return;
    }

    public static void openChat(ChatActivity r12) {
        if (r12 == null) goto L9;
        int r0 = r12.getCurrentAccount();
        Context r1 = r12.getContext();
        long r2 = r12.getDialogId();
        if (r1 != null) goto L7;
        return;
    L7:
        open(r0, r1, r2, false, false, new ArrayList(), null, null, new byte[0], null, null);
        return;
    }

    public static void openMessage(BaseFragment r12, MessageObject r13) {
        if (r12 == null) goto L13;
        int r0 = r12.getCurrentAccount();
        Context r1 = r12.getContext();
        if (r1 != null) goto L8;
        return;
    L8:
        if (r13.isEphemeral() == false) goto L10;
        int r2 = r13.getEphemeralId();
    L11:
        ArrayList r6 = new ArrayList(Collections.singleton(Integer.valueOf(r2)));
        open(r0, r1, r13.getDialogId(), false, r13.isEphemeral(), r6, BulletinFactory.of(r12), r12.getResourceProvider(), new byte[0], null, null);
        return;
    L10:
        r2 = r13.getId();
        goto L11
    }

    public static void openSponsored(int r12, Context r13, long r14, TLRPC.TL_sponsoredMessage r16, BulletinFactory r17, Theme.ResourcesProvider r18, Runnable r19, Runnable r20) {
        if (r13 != null) goto L4;
        return;
    L4:
        TLRPC.TL_messages_reportSponsoredMessage r0 = new TLRPC.TL_messages_reportSponsoredMessage();
        byte[] r6 = r16.random_id;
        r0.random_id = r6;
        r0.option = new byte[0];
        ConnectionsManager.getInstance(r12).sendRequest(r0, new ReportBottomSheet$$ExternalSyntheticLambda2(r13, r18, r14, r6, r20, r17, r19, r12));
    }

    public static void openSponsored(ChatActivity r11, MessageObject r12, Theme.ResourcesProvider r13) {
        if (r11 == null) goto L9;
        int r8 = r11.getCurrentAccount();
        Context r1 = r11.getContext();
        long r3 = r11.getDialogId();
        if (r1 != null) goto L7;
        return;
    L7:
        TLRPC.TL_messages_reportSponsoredMessage r9 = new TLRPC.TL_messages_reportSponsoredMessage();
        byte[] r5 = r12.sponsoredId;
        r9.random_id = r5;
        r9.option = new byte[0];
        ConnectionsManager.getInstance(r8).sendRequest(r9, new ReportBottomSheet$$ExternalSyntheticLambda8(r1, r13, r3, r5, r11, r12, r8));
        return;
    }

    public static void openSponsoredPeer(BaseFragment r9, byte[] r10, Theme.ResourcesProvider r11, Runnable r12) {
        if (r9 == null) goto L9;
        int r6 = r9.getCurrentAccount();
        Context r1 = r9.getContext();
        if (r1 != null) goto L7;
        return;
    L7:
        TLRPC.TL_messages_reportSponsoredMessage r7 = new TLRPC.TL_messages_reportSponsoredMessage();
        r7.random_id = r10;
        r7.option = new byte[0];
        ConnectionsManager.getInstance(r6).sendRequest(r7, new ReportBottomSheet$$ExternalSyntheticLambda25(r1, r11, r10, r9, r12, r6));
        return;
    }

    public static void openStory(int r12, Context r13, TL_stories.StoryItem r14, BulletinFactory r15, Theme.ResourcesProvider r16, Utilities.Callback<Boolean> r17) {
        ArrayList r6 = new ArrayList(Collections.singleton(Integer.valueOf(r14.id)));
        open(r12, r13, r14.dialogId, true, false, r6, r15, r16, new byte[0], null, r17);
    }

    private ReportBottomSheet setListener(Listener r1) {
        this.listener = r1;
        return this;
    }

    private ReportBottomSheet setReportChooseOption(TLRPC.TL_channels_sponsoredMessageReportResultChooseOption r5) {
        View[] r0 = this.viewPager.getViewPages();
        View r2 = r0[0];
        if ((r2 instanceof Page) == false) goto L5;
        ((Page) r2).bind(0);
        this.containerView.post(new ReportBottomSheet$$ExternalSyntheticLambda14(r0, r5));
    L5:
        View r02 = r0[1];
        if ((r02 instanceof Page) == false) goto L8;
        ((Page) r02).bind(1);
    L8:
        return this;
    }

    private ReportBottomSheet setReportChooseOption(TLRPC.TL_reportResultAddComment r5) {
        View[] r0 = this.viewPager.getViewPages();
        View r2 = r0[0];
        if ((r2 instanceof Page) == false) goto L5;
        ((Page) r2).bind(0);
        this.containerView.post(new ReportBottomSheet$$ExternalSyntheticLambda15(r0, r5));
    L5:
        View r02 = r0[1];
        if ((r02 instanceof Page) == false) goto L8;
        ((Page) r02).bind(1);
    L8:
        return this;
    }

    private ReportBottomSheet setReportChooseOption(TLRPC.TL_reportResultChooseOption r5) {
        View[] r0 = this.viewPager.getViewPages();
        View r2 = r0[0];
        if ((r2 instanceof Page) == false) goto L5;
        ((Page) r2).bind(0);
        this.containerView.post(new ReportBottomSheet$$ExternalSyntheticLambda10(r0, r5));
    L5:
        View r02 = r0[1];
        if ((r02 instanceof Page) == false) goto L8;
        ((Page) r02).bind(1);
    L8:
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void submitOption(CharSequence r6, byte[] r7, String r8) {
        if (this.sponsored == false) goto L5;
        TLRPC.TL_messages_reportSponsoredMessage r0 = new TLRPC.TL_messages_reportSponsoredMessage();
        r0.random_id = this.sponsoredId;
        r0.option = r7;
        TLRPC.TL_messages_report r02 = r0;
    L32:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r02, new ReportBottomSheet$$ExternalSyntheticLambda23(this, r6, r7, r8));
        return;
    L5:
        boolean r03 = this.stories;
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r03 == false) goto L15;
        TL_stories.TL_stories_report r04 = new TL_stories.TL_stories_report();
        r04.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        ArrayList<Integer> r2 = this.messageIds;
        if (r2 == null) goto L10;
        r04.id.addAll(r2);
    L10:
        if (r8 == null) goto L13;
        r1 = r8;
    L13:
        r04.message = r1;
        r04.option = r7;
        r02 = r04;
        goto L32
    L15:
        if (this.ephemeral == false) goto L25;
        TL_ephemeral.TL_reportMessage r05 = new TL_ephemeral.TL_reportMessage();
        r05.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        ArrayList<Integer> r22 = this.messageIds;
        if (r22 != null) goto L19;
    L21:
        if (r8 == null) goto L24;
        r1 = r8;
    L24:
        r05.message = r1;
        r05.option = r7;
        r02 = r05;
        goto L32
    L19:
        if (r22.isEmpty() == true) goto L21;
        r05.id = this.messageIds.get(0).intValue();
        goto L21
    L25:
        TLRPC.TL_messages_report r06 = new TLRPC.TL_messages_report();
        r06.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        ArrayList<Integer> r23 = this.messageIds;
        if (r23 == null) goto L28;
        r06.id.addAll(r23);
    L28:
        if (r8 == null) goto L31;
        r1 = r8;
    L31:
        r06.message = r1;
        r06.option = r7;
        r02 = r06;
        goto L32
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        View r1 = this.viewPager.getCurrentView();
        if ((r1 instanceof Page) == true) goto L5;
        return true;
    L5:
        return ((Page) r1).atTop();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void onBackPressed() {
        if ((this.viewPager.getCurrentView() instanceof Page) == false) goto L8;
        Page r0 = (Page) this.viewPager.getCurrentView();
        if (Page.m18487$$Nest$fgeteditTextCell(r0) == null) goto L8;
        AndroidUtilities.hideKeyboard(Page.m18487$$Nest$fgeteditTextCell(r0));
    L8:
        if (this.viewPager.getCurrentPosition() <= 0) goto L11;
        this.viewPager.scrollToPosition(r2.getCurrentPosition() - 1);
        return;
    L11:
        super.onBackPressed();
    }
}
