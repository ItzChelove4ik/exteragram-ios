package org.telegram.ui.bots;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.tgnet.tl.TL_payments;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Premium.GLIcon.GLIconRenderer;
import org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView;
import org.telegram.ui.Components.Premium.StarParticlesView;
import org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorUserCell;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.TableView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.GradientHeaderActivity;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public class AffiliateProgramFragment extends GradientHeaderActivity implements NotificationCenter.NotificationCenterDelegate {
    private FrameLayout aboveTitleView;
    private UniversalAdapter adapter;
    private boolean attached;
    private final long bot_id;
    private ButtonWithCounterView button;
    private LinearLayout buttonLayout;
    private LinkSpanDrawable.LinksTextView buttonSubtext;
    private String[] durationTexts;
    private final List<Integer> durationValues;
    private View emptyLayout;
    private GLIconTextureView iconTextureView;
    private TL_payments.starRefProgram initialProgram;
    private boolean new_program;
    private TL_payments.starRefProgram program;
    private final Runnable updateTimerRunnable;

    /* renamed from: org.telegram.ui.bots.AffiliateProgramFragment$1, reason: invalid class name */
    public class AnonymousClass1 extends View {
        final /* synthetic */ AffiliateProgramFragment this$0;

        public AnonymousClass1(AffiliateProgramFragment r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onMeasure(int r4, int r5) {
            AffiliateProgramFragment r52 = this.this$0;
            if (r52.isLandscapeMode == false) goto L5;
            int r0 = (r52.statusBarHeight + AffiliateProgramFragment.access$000(r52).getMeasuredHeight()) - AndroidUtilities.dp(16.0f);
        L9:
            super.onMeasure(r4, View.MeasureSpec.makeMeasureSpec((int) (r0 - (AffiliateProgramFragment.access$100(this.this$0) * 2.5f)), TLObject.FLAG_30));
            return;
        L5:
            int r53 = AndroidUtilities.dp(140.0f);
            AffiliateProgramFragment r02 = this.this$0;
            int r54 = r53 + r02.statusBarHeight;
            if ((r02.backgroundView.getMeasuredHeight() + AndroidUtilities.dp(24.0f)) <= r54) goto L8;
            r54 = this.this$0.backgroundView.getMeasuredHeight() + AndroidUtilities.dp(24.0f);
        L8:
            r0 = r54;
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.bots.AffiliateProgramFragment$2, reason: invalid class name */
    public class AnonymousClass2 extends ButtonWithCounterView {
        final /* synthetic */ AffiliateProgramFragment this$0;

        public AnonymousClass2(AffiliateProgramFragment r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Stories.recorder.ButtonWithCounterView
        public boolean subTextSplitToWords() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.bots.AffiliateProgramFragment$3, reason: invalid class name */
    public class AnonymousClass3 extends StarParticlesView {
        public AnonymousClass3(Context r1) {
            super(r1);
            setClipWithGradient();
        }

        @Override // org.telegram.ui.Components.Premium.StarParticlesView
        public void configure() {
            super.configure();
            StarParticlesView.Drawable r2 = this.drawable;
            r2.useGradient = true;
            r2.useBlur = false;
            r2.forceMaxAlpha = true;
            r2.checkBounds = true;
            r2.init();
        }

        @Override // org.telegram.ui.Components.Premium.StarParticlesView
        public int getStarsRectWidth() {
            return getMeasuredWidth();
        }
    }

    /* renamed from: org.telegram.ui.bots.AffiliateProgramFragment$4, reason: invalid class name */
    public class AnonymousClass4 extends UniversalAdapter {
        final /* synthetic */ AffiliateProgramFragment this$0;

        public AnonymousClass4(AffiliateProgramFragment r1, RecyclerListView r2, Context r3, int r4, int r5, boolean r6, Utilities.Callback2 r7, Theme.ResourcesProvider r8) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8);
        }

        @Override // org.telegram.ui.Components.UniversalAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r9, int r10) {
            if (r10 != 42) goto L7;
            HeaderCell r1 = new HeaderCell(this.this$0.getContext(), Theme.key_windowBackgroundWhiteBlueHeader, 21, 0, false, AffiliateProgramFragment.access$200(this.this$0));
            r1.setHeight(25);
            return new RecyclerListView.Holder(r1);
        L7:
            return super.onCreateViewHolder(r9, r10);
        }
    }

    public class BulletinTextView extends TextView {
        final /* synthetic */ AffiliateProgramFragment this$0;

        public BulletinTextView(AffiliateProgramFragment r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r4) {
            super.dispatchDraw(r4);
            r4.drawCircle(AndroidUtilities.dp(3.5f), AndroidUtilities.dp(11.5f), AndroidUtilities.dp(2.5f), getPaint());
        }
    }

    public static class ColorfulTextCell extends FrameLayout {
        private final ImageView arrowView;
        private final ImageView imageView;
        private final FrameLayout.LayoutParams imageViewLayoutParams;
        private final TextView percentView;
        private final Theme.ResourcesProvider resourcesProvider;
        private final LinearLayout textLayout;
        private final FrameLayout.LayoutParams textLayoutLayoutParams;
        private final TextView textView;
        private final TextView titleView;

        public static class Factory extends UItem.UItemFactory<ColorfulTextCell> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem as(int r1, int r2, int r3, CharSequence r4, CharSequence r5) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.id = r1;
                r0.intValue = r2;
                r0.iconResId = r3;
                r0.text = r4;
                r0.subtext = r5;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                ((ColorfulTextCell) r1).set(r2.intValue, r2.iconResId, r2.text, r2.subtext);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public ColorfulTextCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new ColorfulTextCell(r1, r5);
            }
        }

        public ColorfulTextCell(Context r18, Theme.ResourcesProvider r19) {
            super(r18);
            this.resourcesProvider = r19;
            ImageView r3 = new ImageView(r18);
            this.imageView = r3;
            PorterDuff.Mode r5 = PorterDuff.Mode.SRC_IN;
            int r6 = -1;
            r3.setColorFilter(new PorterDuffColorFilter(-1, r5));
            ImageView.ScaleType r4 = ImageView.ScaleType.CENTER;
            r3.setScaleType(r4);
            FrameLayout.LayoutParams r7 = LayoutHelper.createFrame(28, 28.0f, 51, 17.0f, 14.33f, 0.0f, 0.0f);
            this.imageViewLayoutParams = r7;
            addView(r3, r7);
            LinearLayout r32 = new LinearLayout(r18);
            this.textLayout = r32;
            r32.setOrientation(1);
            FrameLayout.LayoutParams r8 = LayoutHelper.createFrame(-1, -2.0f, 55, 62.0f, 10.0f, 40.0f, 8.66f);
            this.textLayoutLayoutParams = r8;
            addView(r32, r8);
            TextView r82 = new TextView(r18);
            this.titleView = r82;
            r82.setTypeface(AndroidUtilities.bold());
            r82.setTextSize(1, 15.0f);
            r82.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r19));
            r32.addView(r82, LayoutHelper.createLinear(-1, -2, 55, 0, 0, 0, 0));
            TextView r83 = new TextView(r18);
            this.textView = r83;
            r83.setTextSize(1, 14.0f);
            r83.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r19));
            r32.addView(r83, LayoutHelper.createLinear(-1, -2, 55, 0, 3, 0, 0));
            ImageView r33 = new ImageView(r18);
            this.arrowView = r33;
            r33.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_switchTrack, r19), r5));
            r33.setImageResource(R.drawable.msg_arrowright);
            r33.setScaleType(r4);
            addView(r33, LayoutHelper.createFrame(24, 24.0f, 21, 0.0f, 0.0f, 10.0f, 0.0f));
            boolean r34 = Theme.isCurrentThemeMonet();
            int r42 = Theme.getColor(Theme.key_chats_actionIcon);
            int r52 = Theme.getColor(Theme.key_chats_actionBackground);
            TextView r84 = new TextView(r18);
            this.percentView = r84;
            if (r34 == false) goto L5;
            r6 = r42;
        L5:
            r84.setTextColor(r6);
            int r43 = AndroidUtilities.dp(4.0f);
            if (r34 == true) goto L9;
            r52 = Theme.getColor(Theme.key_color_green, r19);
        L9:
            r84.setBackground(Theme.createRoundRectDrawable(r43, r52));
            r84.setTextSize(1, 13.0f);
            r84.setTypeface(AndroidUtilities.bold());
            r84.setPadding(AndroidUtilities.dp(5.0f), 0, AndroidUtilities.dp(4.0f), 0);
            r84.setGravity(17);
            r84.setVisibility(8);
            addView(r84, LayoutHelper.createFrame(-2, 18.0f, 21, 0.0f, 0.0f, 35.33f, 0.0f));
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }

        public void set(int r4, int r5, CharSequence r6, CharSequence r7) {
            this.imageView.setImageResource(r5);
            boolean r52 = Theme.isCurrentThemeMonet();
            int r0 = Theme.getColor(Theme.key_chats_actionIcon);
            this.imageView.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(9.0f), r4));
            ImageView r42 = this.imageView;
            if (r52 == true) goto L6;
            r0 = -1;
        L6:
            r42.setColorFilter(r0, PorterDuff.Mode.SRC_IN);
            this.titleView.setText(r6);
            boolean r43 = TextUtils.isEmpty(r7);
            FrameLayout.LayoutParams r53 = this.imageViewLayoutParams;
            if (r43 == false) goto L10;
            r53.topMargin = AndroidUtilities.dp(10.0f);
            FrameLayout.LayoutParams r44 = this.imageViewLayoutParams;
            r44.bottomMargin = AndroidUtilities.dp(10.0f);
            this.titleView.setTypeface(null);
            this.titleView.setTextSize(1, 16.0f);
            FrameLayout.LayoutParams r45 = this.textLayoutLayoutParams;
            r45.topMargin = 0;
            r45.bottomMargin = 0;
            r45.gravity = 23;
            this.textView.setVisibility(8);
            return;
        L10:
            r53.topMargin = AndroidUtilities.dp(14.33f);
            FrameLayout.LayoutParams r46 = this.imageViewLayoutParams;
            r46.bottomMargin = AndroidUtilities.dp(10.0f);
            this.titleView.setTypeface(AndroidUtilities.bold());
            this.titleView.setTextSize(1, 15.0f);
            FrameLayout.LayoutParams r47 = this.textLayoutLayoutParams;
            r47.topMargin = AndroidUtilities.dp(10.0f);
            FrameLayout.LayoutParams r48 = this.textLayoutLayoutParams;
            r48.bottomMargin = AndroidUtilities.dp(8.66f);
            this.textLayoutLayoutParams.gravity = 55;
            this.textView.setText(r7);
            this.textView.setVisibility(0);
        }

        public void setPercent(CharSequence r3) {
            boolean r0 = TextUtils.isEmpty(r3);
            TextView r1 = this.percentView;
            if (r0 == false) goto L6;
            r1.setVisibility(8);
            return;
        L6:
            r1.setVisibility(0);
            this.percentView.setText(r3);
        }
    }

    public static class FeatureCell extends FrameLayout {
        private ImageView imageView;
        private final Theme.ResourcesProvider resourcesProvider;
        private LinearLayout textLayout;
        private TextView textView;
        private TextView titleView;

        public static class Factory extends UItem.UItemFactory<FeatureCell> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem as(int r1, CharSequence r2, CharSequence r3) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.iconResId = r1;
                r0.text = r2;
                r0.subtext = r3;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                ((FeatureCell) r1).set(r2.iconResId, r2.text, r2.subtext);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public FeatureCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new FeatureCell(r1, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean isClickable() {
                return false;
            }
        }

        public FeatureCell(Context r2, Theme.ResourcesProvider r3) {
            this(r2, false, r3);
        }

        public FeatureCell(Context r13, boolean r14, Theme.ResourcesProvider r15) {
            super(r13);
            this.resourcesProvider = r15;
            ImageView r0 = new ImageView(r13);
            this.imageView = r0;
            r0.setScaleType(ImageView.ScaleType.CENTER);
            ImageView r02 = this.imageView;
            int r2 = Theme.key_windowBackgroundWhiteBlackText;
            r02.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r2, r15), PorterDuff.Mode.SRC_IN));
            addView(this.imageView, LayoutHelper.createFrame(24, 24.0f, 51, 20.0f, 11.46f, 0.0f, 0.0f));
            LinearLayout r03 = new LinearLayout(r13);
            this.textLayout = r03;
            r03.setOrientation(1);
            LinearLayout r04 = this.textLayout;
            float r3 = 9.8f;
            if (r14 == false) goto L5;
            float r9 = 2.0f;
        L6:
            if (r14 == false) goto L8;
            r3 = 4.0f;
        L8:
            addView(r04, LayoutHelper.createFrame(-1, -2.0f, 23, 64.0f, r9, 24.0f, r3));
            TextView r142 = new TextView(r13);
            this.titleView = r142;
            r142.setTextColor(Theme.getColor(r2, r15));
            this.titleView.setTypeface(AndroidUtilities.bold());
            this.titleView.setTextSize(1, 14.0f);
            this.textLayout.addView(this.titleView, LayoutHelper.createLinear(-1, -2, 55, 0, 0, 0, 1));
            TextView r143 = new TextView(r13);
            this.textView = r143;
            r143.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r15));
            this.textView.setTextSize(1, 14.0f);
            this.textLayout.addView(this.textView, LayoutHelper.createLinear(-1, -2, 55, 0, 0, 0, 0));
            return;
        L5:
            r9 = 9.8f;
            goto L6
        }

        public void set(int r2, CharSequence r3, CharSequence r4) {
            this.imageView.setImageResource(r2);
            this.titleView.setText(r3);
            this.textView.setText(r4);
        }

        public void setText(CharSequence r1) {
            this.textView.setText(r1);
        }
    }

    /* renamed from: $r8$lambda$0L_Wx0ashEDzerfkGtSBK-Spk4E, reason: not valid java name */
    public static /* synthetic */ void m21300$r8$lambda$0L_Wx0ashEDzerfkGtSBKSpk4E(AffiliateProgramFragment r0, TLRPC.UserFull r1) {
        r0.lambda$onFragmentCreate$15(r1);
    }

    public static /* synthetic */ void $r8$lambda$2zXIgiXKV_ZEi449ty0tTmiBpgQ(AffiliateProgramFragment r0, AlertDialog r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$createView$0(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$4Tkuf1sw0FMC_4gFjDXeyLpVM1s(AffiliateProgramFragment r0, AlertDialog r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$createView$1(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$6Zupnv74SeVKEyE-A2BXtE0JMV8, reason: not valid java name */
    public static /* synthetic */ void m21301$r8$lambda$6Zupnv74SeVKEyEA2BXtE0JMV8(AffiliateProgramFragment r0) {
        r0.lambda$createView$2();
    }

    /* renamed from: $r8$lambda$JVgEaE02f5JpEkBNEtbfNI-s6QQ, reason: not valid java name */
    public static /* synthetic */ void m21302$r8$lambda$JVgEaE02f5JpEkBNEtbfNIs6QQ(AffiliateProgramFragment r0, Integer r1) {
        r0.lambda$fillItems$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$P9FMVL3EYo_O0aKeS4XLeOSeJZg(AffiliateProgramFragment r0, Context r1, View r2) {
        r0.lambda$createView$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$UtToCSER5sY5y6RoZPWJo1MHDYI(AffiliateProgramFragment r0) {
        r0.lambda$new$7();
    }

    /* renamed from: $r8$lambda$Yl-Dx33kBV6UK6bGPvu9d4mpwjY, reason: not valid java name */
    public static /* synthetic */ void m21303$r8$lambda$YlDx33kBV6UK6bGPvu9d4mpwjY(Runnable r0, AlertDialog r1, int r2) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$c4xFmu777ciICrUvQ9W41EybRI4(AffiliateProgramFragment r0, TLRPC.UserFull r1) {
        r0.lambda$onFragmentCreate$14(r1);
    }

    public static /* synthetic */ CharSequence $r8$lambda$eUFssXj_CmByVKFWXvUia8iqiFw(Integer r2) {
        return String.format(Locale.US, "%.1f%%", new Object[]{Float.valueOf(r2.intValue() / 10.0f)});
    }

    /* renamed from: $r8$lambda$eVurmWr0yw1-ajrONMjnVroWtU0, reason: not valid java name */
    public static /* synthetic */ void m21304$r8$lambda$eVurmWr0yw1ajrONMjnVroWtU0(AffiliateProgramFragment r0) {
        r0.lambda$update$6();
    }

    public static /* synthetic */ void $r8$lambda$ju81j2qVtMMMDWlM94dZrcNVL08(AffiliateProgramFragment r0, Integer r1) {
        r0.lambda$fillItems$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$tvtiZEX3tNBYhOMQS1Cn0R2da1s(AffiliateProgramFragment r0, AlertDialog r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$end$8(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$w1FHiiNrqoHzKCzmyCMqrmY5XD0(AffiliateProgramFragment r0, View r1, int r2) {
        r0.lambda$createView$5(r1, r2);
    }

    /* renamed from: $r8$lambda$wZvur-ZXLNvcWCG_sCvwR1aWyfI, reason: not valid java name */
    public static /* synthetic */ void m21305$r8$lambda$wZvurZXLNvcWCG_sCvwR1aWyfI(AffiliateProgramFragment r0, AlertDialog r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$end$9(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$x3y2ltOM2bJuQiB8p5PRh53Ez2Y(AffiliateProgramFragment r0, AlertDialog r1, int r2) {
        r0.lambda$end$10(r1, r2);
    }

    public AffiliateProgramFragment(long r9) {
        this.updateTimerRunnable = new AffiliateProgramFragment$$ExternalSyntheticLambda3(this);
        this.durationTexts = null;
        this.durationValues = Arrays.asList(new Integer[]{1, 3, 6, 12, 24, 36, 0});
        this.bot_id = r9;
        setWhiteBackground(true);
        setMinusHeaderHeight(AndroidUtilities.dp(60.0f));
    }

    public static /* synthetic */ ActionBar access$000(AffiliateProgramFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$100(AffiliateProgramFragment r0) {
        return r0.yOffset;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$200(AffiliateProgramFragment r0) {
        return r0.resourceProvider;
    }

    private void closeToProfile(boolean r10) {
        BaseFragment r1 = null;
        if (getParentLayout() != null) goto L5;
    L21:
        finishFragment();
    L22:
        if (r1 == null) goto L33;
        if (r10 == false) goto L26;
        BulletinFactory.of(r1).createSimpleBulletin(R.raw.linkbroken, LocaleController.getString(R.string.AffiliateProgramEndedTitle), AndroidUtilities.replaceTags(LocaleController.getString(R.string.AffiliateProgramEndedText))).show();
        return;
    L26:
        BulletinFactory.of(r1).createSimpleBulletin(R.raw.contact_check, LocaleController.getString(R.string.AffiliateProgramStartedTitle), LocaleController.getString(R.string.AffiliateProgramStartedText)).show();
        return;
    L33:
        return;
    L5:
        if (getParentLayout().getFragmentStack() == null) goto L21;
        INavigationLayout r0 = getParentLayout();
        List<BaseFragment> r2 = r0.getFragmentStack();
        int r3 = r2.size() - 1;
    L7:
        if (r3 <= 0) goto L14;
        BaseFragment r4 = r2.get(r3);
        if ((r4 instanceof ProfileActivity) == false) goto L13;
        if (((ProfileActivity) r4).getDialogId() != this.bot_id) goto L13;
        r1 = r4;
    L15:
        if (r1 == null) goto L20;
        int r42 = r2.size() - 1;
    L17:
        if (r42 <= r3) goto L19;
        r0.removeFragmentFromStack(r2.get(r42));
        r42 = r42 - 1;
        goto L17
    L19:
        finishFragment();
        goto L22
    L20:
        finishFragment();
        r1 = r0.getBackgroundFragment();
    L13:
        r3 = r3 - 1;
        goto L7
    L14:
        r3 = -1;
        goto L15
    }

    private void end() {
        LinearLayout r0 = new LinearLayout(getContext());
        r0.setOrientation(1);
        r0.setPadding(AndroidUtilities.dp(24.0f), 0, AndroidUtilities.dp(24.0f), 0);
        TextView r2 = new TextView(getContext());
        r2.setTextSize(1, 16.0f);
        int r5 = Theme.key_windowBackgroundWhiteBlackText;
        r2.setTextColor(Theme.getColor(r5, this.resourceProvider));
        r2.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.AffiliateProgramStopText)));
        r0.addView(r2, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 17.0f));
        BulletinTextView r22 = new BulletinTextView(this, getContext());
        r22.setPadding(AndroidUtilities.dp(15.0f), 0, 0, 0);
        r22.setTextSize(1, 16.0f);
        r22.setTextColor(Theme.getColor(r5, this.resourceProvider));
        r22.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.AffiliateProgramStopText1)));
        r0.addView(r22, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 17.0f));
        BulletinTextView r23 = new BulletinTextView(this, getContext());
        r23.setPadding(AndroidUtilities.dp(15.0f), 0, 0, 0);
        r23.setTextSize(1, 16.0f);
        r23.setTextColor(Theme.getColor(r5, this.resourceProvider));
        r23.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.AffiliateProgramStopText2)));
        r0.addView(r23, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 17.0f));
        BulletinTextView r24 = new BulletinTextView(this, getContext());
        r24.setPadding(AndroidUtilities.dp(15.0f), 0, 0, 0);
        r24.setTextSize(1, 16.0f);
        r24.setTextColor(Theme.getColor(r5, this.resourceProvider));
        r24.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.AffiliateProgramStopText3)));
        r0.addView(r24, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 10.0f));
        new AlertDialog.Builder(getContext(), this.resourceProvider).setTitle(LocaleController.getString(R.string.AffiliateProgramAlert)).setView(r0).setPositiveButton(LocaleController.getString(R.string.AffiliateProgramStopButton), new AffiliateProgramFragment$$ExternalSyntheticLambda9(this)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).makeRed(-1).show();
    }

    private /* synthetic */ void lambda$createView$0(AlertDialog r4, TLObject r5, TLRPC.TL_error r6) {
        r4.dismiss();
        if ((r5 instanceof TL_payments.starRefProgram) == false) goto L9;
        TL_payments.starRefProgram r52 = (TL_payments.starRefProgram) r5;
        TLRPC.UserFull r42 = getMessagesController().getUserFull(this.bot_id);
        if (r42 == null) goto L7;
        r42.starref_program = r52;
        getMessagesStorage().updateUserInfo(r42, false);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.userInfoDidLoad, new Object[]{Long.valueOf(this.bot_id), r42});
    L7:
        closeToProfile(false);
        return;
    L9:
        if (r6 == null) goto L12;
        BulletinFactory.showError(r6);
        return;
    }

    private /* synthetic */ void lambda$createView$1(AlertDialog r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new AffiliateProgramFragment$$ExternalSyntheticLambda15(this, r2, r3, r4));
    }

    private /* synthetic */ void lambda$createView$2() {
        TL_bots.updateStarRefProgram r0 = new TL_bots.updateStarRefProgram();
        r0.bot = getMessagesController().getInputUser(this.bot_id);
        TL_payments.starRefProgram r1 = this.program;
        r0.commission_permille = r1.commission_permille;
        int r2 = r1.duration_months;
        r0.duration_months = r2;
        int r3 = r0.flags;
        if (r2 <= 0) goto L5;
        r0.flags = r3 | 1;
        r1.duration_months = r2 | 1;
    L6:
        AlertDialog r12 = new AlertDialog(getContext(), 3);
        r12.showDelayed(150);
        getConnectionsManager().sendRequest(r0, new AffiliateProgramFragment$$ExternalSyntheticLambda14(this, r12));
        return;
    L5:
        r0.flags = r3 & (-2);
        r1.duration_months = r2 & (-2);
        goto L6
    }

    private /* synthetic */ void lambda$createView$4(Context r12, View r13) {
        if (this.button.isEnabled() == true) goto L5;
        return;
    L5:
        FrameLayout r132 = new FrameLayout(r12);
        TableView r0 = new TableView(r12, this.resourceProvider);
        AffiliateProgramFragment$$ExternalSyntheticLambda4 r1 = new AffiliateProgramFragment$$ExternalSyntheticLambda4(this);
        r0.addRow(LocaleController.getString(R.string.AffiliateProgramCommission), percents(this.program.commission_permille));
        String r2 = LocaleController.getString(R.string.AffiliateProgramDuration);
        int r3 = this.program.duration_months;
        if (r3 > 0) goto L9;
        String r32 = LocaleController.getString(R.string.Infinity);
    L15:
        r0.addRow(r2, r32);
        r132.addView(r0, LayoutHelper.createFrame(-1, -2.0f, 119, 24.0f, 0.0f, 24.0f, 0.0f));
        AlertDialog.Builder r122 = new AlertDialog.Builder(r12, this.resourceProvider).setTitle(LocaleController.getString(R.string.AffiliateProgramAlert));
        if (this.new_program == false) goto L18;
        int r02 = R.string.AffiliateProgramStartAlertText;
    L19:
        AlertDialog.Builder r123 = r122.setMessage(LocaleController.getString(r02)).setView(r132);
        if (this.new_program == false) goto L22;
        int r11 = R.string.AffiliateProgramStartAlertButton;
    L23:
        r123.setPositiveButton(LocaleController.getString(r11), new AffiliateProgramFragment$$ExternalSyntheticLambda5(r1)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).show();
        return;
    L22:
        r11 = R.string.AffiliateProgramUpdateAlertButton;
        goto L23
    L18:
        r02 = R.string.AffiliateProgramUpdateAlertText;
        goto L19
    L9:
        if (r3 >= 12) goto L11;
    L14:
        r32 = LocaleController.formatPluralString("Months", r3, new Object[0]);
        goto L15
    L11:
        if ((r3 % 12) != 0) goto L14;
        r32 = LocaleController.formatPluralString("Years", r3 / 12, new Object[0]);
        goto L15
    }

    private /* synthetic */ void lambda$createView$5(View r3, int r4) {
        UniversalAdapter r32 = this.adapter;
        if (r32 == null) goto L14;
        int r33 = r32.getItem(r4).id;
        if (r33 != 4) goto L10;
        end();
        return;
    L10:
        if (r33 != 2) goto L13;
        presentFragment(new SuggestedAffiliateProgramsFragment(this.bot_id));
        return;
    L13:
        return;
    }

    private /* synthetic */ void lambda$end$10(AlertDialog r3, int r4) {
        TL_bots.updateStarRefProgram r32 = new TL_bots.updateStarRefProgram();
        r32.bot = getMessagesController().getInputUser(this.bot_id);
        r32.commission_permille = 0;
        AlertDialog r42 = new AlertDialog(getContext(), 3);
        r42.showDelayed(150);
        getConnectionsManager().sendRequest(r32, new AffiliateProgramFragment$$ExternalSyntheticLambda10(this, r42));
    }

    private /* synthetic */ void lambda$end$8(AlertDialog r3, TLObject r4, TLRPC.TL_error r5) {
        r3.dismiss();
        if ((r4 instanceof TL_payments.starRefProgram) == false) goto L13;
        TL_payments.starRefProgram r42 = (TL_payments.starRefProgram) r4;
        TLRPC.UserFull r32 = getMessagesController().getUserFull(this.bot_id);
        if (r32 == null) goto L11;
        TL_payments.starRefProgram r52 = this.program;
        r52.flags |= 2;
        int r0 = getConnectionsManager().getCurrentTime();
        if (getConnectionsManager().isTestBackend() == false) goto L9;
        int r1 = 300;
    L10:
        r52.end_date = r0 + r1;
        r32.starref_program = r42;
        getMessagesStorage().updateUserInfo(r32, false);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.userInfoDidLoad, new Object[]{Long.valueOf(this.bot_id), r32});
        goto L11
    L9:
        r1 = 86400;
    L11:
        closeToProfile(true);
        return;
    L13:
        if (r5 == null) goto L16;
        BulletinFactory.showError(r5);
        return;
    }

    private /* synthetic */ void lambda$end$9(AlertDialog r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new AffiliateProgramFragment$$ExternalSyntheticLambda16(this, r2, r3, r4));
    }

    private /* synthetic */ void lambda$fillItems$12(Integer r2) {
        TL_payments.starRefProgram r0 = this.program;
        r0.commission_permille = r2.intValue();
        updateEnabled();
    }

    private /* synthetic */ void lambda$fillItems$13(Integer r3) {
        TL_payments.starRefProgram r0 = this.program;
        r0.duration_months = this.durationValues.get(r3.intValue()).intValue();
        updateEnabled();
    }

    private /* synthetic */ void lambda$new$7() {
        ButtonWithCounterView r0 = this.button;
        if (this.program.end_date != 0) goto L5;
        String r1 = null;
    L6:
        r0.setSubText(r1, true);
        if (this.program.end_date != 0) goto L9;
        return;
    L9:
        if (this.attached == false) goto L13;
        AndroidUtilities.runOnUIThread(this.updateTimerRunnable, 1000);
        return;
    L13:
        return;
    L5:
        r1 = SelectorUserCell.buildCountDownTime((r1 - getConnectionsManager().getCurrentTime()) * 1000);
        goto L6
    }

    private /* synthetic */ void lambda$onFragmentCreate$14(TLRPC.UserFull r4) {
        if (r4 == null) goto L8;
        this.new_program = false;
        TL_payments.starRefProgram r42 = r4.starref_program;
        this.program = r42;
        if (r42 != null) goto L7;
        this.new_program = true;
        this.program = getDefaultProgram();
        this.initialProgram = null;
        goto L8
    L7:
        TL_payments.starRefProgram r43 = new TL_payments.starRefProgram();
        this.initialProgram = r43;
        TL_payments.starRefProgram r1 = this.program;
        r43.commission_permille = r1.commission_permille;
        r43.duration_months = r1.duration_months;
    L8:
        update(true);
    }

    private /* synthetic */ void lambda$onFragmentCreate$15(TLRPC.UserFull r2) {
        AndroidUtilities.runOnUIThread(new AffiliateProgramFragment$$ExternalSyntheticLambda7(this, r2));
    }

    private /* synthetic */ void lambda$update$6() {
        Context r0 = getContext();
        if (this.new_program == false) goto L5;
    L8:
        int r2 = R.string.AffiliateProgramUpdateInfoLink;
    L9:
        Browser.openUrl(r0, LocaleController.getString(r2));
        return;
    L5:
        if (this.program.end_date != 0) goto L8;
        r2 = R.string.AffiliateProgramStartInfoLink;
        goto L9
    }

    public static StarParticlesView makeParticlesView(Context r0, int r1, int r2) {
        return new AnonymousClass3(r0);
    }

    public static CharSequence percents(int r2) {
        float r0 = r2 / 10.0f;
        if (((int) r0) != r0) goto L7;
        return String.format(Locale.US, "%d%%", new Object[]{Integer.valueOf(r2 / 10)});
    L7:
        return String.format(Locale.US, "%.1f%%", new Object[]{Float.valueOf(r0)});
    }

    private void update(boolean r4) {
        ButtonWithCounterView r0 = this.button;
        if (this.new_program == false) goto L5;
    L8:
        int r1 = R.string.AffiliateProgramStart;
    L9:
        r0.setText(LocaleController.getString(r1), r4);
        AndroidUtilities.cancelRunOnUIThread(this.updateTimerRunnable);
        this.updateTimerRunnable.run();
        LinkSpanDrawable.LinksTextView r02 = this.buttonSubtext;
        if (this.new_program == false) goto L12;
    L15:
        int r12 = R.string.AffiliateProgramStartInfo;
    L16:
        r02.setText(AndroidUtilities.replaceSingleTag(LocaleController.getString(r12), new AffiliateProgramFragment$$ExternalSyntheticLambda8(this)));
        updateEnabled();
        UniversalAdapter r3 = this.adapter;
        if (r3 == null) goto L20;
        r3.update(r4);
        return;
    L20:
        return;
    L12:
        if (this.program.end_date != 0) goto L15;
        r12 = R.string.AffiliateProgramUpdateInfo;
        goto L16
    L5:
        if (this.program.end_date != 0) goto L8;
        r1 = R.string.AffiliateProgramUpdate;
        goto L9
    }

    private void updateEnabled() {
        ButtonWithCounterView r0 = this.button;
        TL_payments.starRefProgram r1 = this.program;
        if (r1.end_date != 0) goto L11;
        TL_payments.starRefProgram r4 = this.initialProgram;
        if (r4 != null) goto L7;
    L10:
        boolean r42 = true;
    L12:
        r0.setEnabled(r42);
        return;
    L7:
        if (r4.commission_permille != r1.commission_permille) goto L10;
        if (r4.duration_months != r1.duration_months) goto L10;
    L11:
        r42 = false;
        goto L12
    }

    @Override // org.telegram.ui.GradientHeaderActivity
    public RecyclerView.Adapter<?> createAdapter() {
        AnonymousClass4 r0 = new AnonymousClass4(this, this.listView, getContext(), this.currentAccount, this.classGuid, true, new AffiliateProgramFragment$$ExternalSyntheticLambda6(this), getResourceProvider());
        this.adapter = r0;
        return r0;
    }

    @Override // org.telegram.ui.GradientHeaderActivity
    public StarParticlesView createParticlesView() {
        return makeParticlesView(getContext(), 75, 1);
    }

    @Override // org.telegram.ui.GradientHeaderActivity, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r12) {
        this.useFillLastLayoutManager = false;
        this.particlesViewHeight = AndroidUtilities.dp(238.0f);
        AnonymousClass1 r1 = new AnonymousClass1(this, r12);
        this.emptyLayout = r1;
        r1.setBackgroundColor(Theme.getColor(Theme.key_dialogBackgroundGray));
        super.createView(r12);
        FrameLayout r13 = new FrameLayout(r12);
        this.aboveTitleView = r13;
        r13.setClickable(true);
        GLIconTextureView r14 = new GLIconTextureView(r12, 1, 3);
        this.iconTextureView = r14;
        GLIconRenderer r15 = r14.mRenderer;
        r15.colorKey1 = Theme.key_starsGradient1;
        r15.colorKey2 = Theme.key_starsGradient2;
        r15.updateColors();
        this.iconTextureView.setStarParticlesView(this.particlesView);
        this.aboveTitleView.addView(this.iconTextureView, LayoutHelper.createFrame(190, 190.0f, 17, 0.0f, 32.0f, 0.0f, 24.0f));
        configureHeader(LocaleController.getString(R.string.BotAffiliateProgramTitle), LocaleController.getString(R.string.BotAffiliateProgramText), this.aboveTitleView, null);
        LinearLayout r16 = new LinearLayout(r12);
        this.buttonLayout = r16;
        r16.setOrientation(1);
        this.buttonLayout.setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
        View r17 = new View(r12);
        r17.setBackgroundColor(getThemedColor(Theme.key_divider));
        this.buttonLayout.addView(r17, LayoutHelper.createLinear(-1.0f, 1.0f / AndroidUtilities.density));
        ButtonWithCounterView r18 = new AnonymousClass2(this, r12, this.resourceProvider).setRound();
        this.button = r18;
        r18.setText(LocaleController.getString(R.string.AffiliateProgramStart), false);
        this.button.setOnClickListener(new AffiliateProgramFragment$$ExternalSyntheticLambda1(this, r12));
        this.buttonLayout.addView(this.button, LayoutHelper.createLinear(-1, 48, 10.0f, 10.0f, 10.0f, 7.0f));
        LinkSpanDrawable.LinksTextView r19 = new LinkSpanDrawable.LinksTextView(r12, this.resourceProvider);
        this.buttonSubtext = r19;
        r19.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText2));
        this.buttonSubtext.setLinkTextColor(getThemedColor(Theme.key_chat_messageLinkIn));
        this.buttonSubtext.setTextSize(1, 12.0f);
        this.buttonSubtext.setGravity(17);
        this.buttonLayout.addView(this.buttonSubtext, LayoutHelper.createLinear(-1, -2, 32.0f, 1.0f, 32.0f, 8.0f));
        update(false);
        ((FrameLayout) this.fragmentView).addView(this.buttonLayout, LayoutHelper.createFrame(-1, -2, 87));
        this.listView.setPadding(0, 0, 0, AndroidUtilities.dp(84.0f));
        this.listView.setOnItemClickListener(new AffiliateProgramFragment$$ExternalSyntheticLambda2(this));
        DefaultItemAnimator r122 = new DefaultItemAnimator();
        r122.setSupportsChangeAnimations(false);
        r122.setDelayAnimations(false);
        r122.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r122.setDurations(350);
        this.listView.setItemAnimator(r122);
        return this.fragmentView;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
    }

    public void fillItems(ArrayList<UItem> r9, UniversalAdapter r10) {
        if (getContext() != null) goto L5;
        return;
    L5:
        r9.add(UItem.asFullyCustom(getHeader(getContext())));
        r9.add(FeatureCell.Factory.as(R.drawable.menu_feature_premium, LocaleController.getString(R.string.BotAffiliateProgramFeature1Title), LocaleController.getString(R.string.BotAffiliateProgramFeature1)));
        r9.add(FeatureCell.Factory.as(R.drawable.msg_channel, LocaleController.getString(R.string.BotAffiliateProgramFeature2Title), LocaleController.getString(R.string.BotAffiliateProgramFeature2)));
        r9.add(FeatureCell.Factory.as(R.drawable.menu_feature_links2, LocaleController.getString(R.string.BotAffiliateProgramFeature3Title), LocaleController.getString(R.string.BotAffiliateProgramFeature3)));
        r9.add(UItem.asShadow(1, null));
        r9.add(UItem.asHeader(LocaleController.getString(R.string.AffiliateProgramCommission)));
        UItem r1 = UItem.asIntSlideView(1, getMessagesController().starrefMinCommissionPermille, this.program.commission_permille, getMessagesController().starrefMaxCommissionPermille, new AffiliateProgramFragment$$ExternalSyntheticLambda11(), new AffiliateProgramFragment$$ExternalSyntheticLambda12(this));
        TL_payments.starRefProgram r2 = this.initialProgram;
        if (r2 != null) goto L8;
        int r22 = -1;
    L9:
        r9.add(r1.setMinSliderValue(r22));
        r9.add(UItem.asShadow(LocaleController.getString(R.string.AffiliateProgramCommissionInfo)));
        r9.add(UItem.asHeader(LocaleController.getString(R.string.AffiliateProgramDuration)));
        if (this.durationTexts != null) goto L25;
        this.durationTexts = new String[this.durationValues.size()];
        int r23 = 0;
    L13:
        if (r23 >= this.durationValues.size()) goto L25;
        int r3 = this.durationValues.get(r23).intValue();
        if (r3 != 0) goto L18;
        this.durationTexts[r23] = LocaleController.getString(R.string.Infinity);
    L24:
        r23 = r23 + 1;
        goto L13
    L18:
        if (r3 >= 12) goto L20;
    L23:
        this.durationTexts[r23] = LocaleController.formatPluralString("MonthsShort", r3, new Object[0]);
        goto L24
    L20:
        if ((r3 % 12) != 0) goto L23;
        this.durationTexts[r23] = LocaleController.formatPluralString("YearsShort", r3 / 12, new Object[0]);
    L25:
        UItem r12 = UItem.asSlideView(this.durationTexts, this.durationValues.indexOf(Integer.valueOf(this.program.duration_months)), new AffiliateProgramFragment$$ExternalSyntheticLambda13(this));
        TL_payments.starRefProgram r24 = this.initialProgram;
        if (r24 == null) goto L38;
        int r25 = r24.duration_months;
        List<Integer> r32 = this.durationValues;
        if (r25 > 0) goto L30;
        r12.setMinSliderValue(r32.size() - 1);
        goto L38
    L30:
        int r26 = r32.size() - 1;
    L31:
        if (r26 < 0) goto L38;
        if (this.durationValues.get(r26).intValue() <= 0) goto L37;
        if (this.durationValues.get(r26).intValue() > this.initialProgram.duration_months) goto L37;
        r12.setMinSliderValue(r26);
    L37:
        r26 = r26 - 1;
    L38:
        r9.add(r12);
        r9.add(UItem.asShadow(LocaleController.getString(R.string.AffiliateProgramDurationInfo)));
        r9.add(ColorfulTextCell.Factory.as(2, getThemedColor(Theme.key_color_green), R.drawable.filled_earn_stars, LocaleController.getString(R.string.AffiliateProgramExistingProgramsTitle), LocaleController.getString(R.string.AffiliateProgramExistingProgramsText)));
        r9.add(UItem.asShadow(3, null));
        if (this.new_program == false) goto L41;
    L43:
        r9.add(UItem.asShadow(6, null));
        r9.add(UItem.asShadow(7, null));
        return;
    L41:
        if (this.program.end_date != 0) goto L43;
        r9.add(UItem.asButton(4, LocaleController.getString(R.string.AffiliateProgramStop)).red());
        r9.add(UItem.asShadow(5, null));
        goto L43
    L8:
        r22 = r2.commission_permille;
        goto L9
    }

    public TL_payments.starRefProgram getDefaultProgram() {
        TL_payments.starRefProgram r0 = new TL_payments.starRefProgram();
        r0.commission_permille = Utilities.clamp(50, getMessagesController().starrefMaxCommissionPermille, getMessagesController().starrefMinCommissionPermille);
        r0.duration_months = 1;
        return r0;
    }

    @Override // org.telegram.ui.GradientHeaderActivity
    public View getHeader(Context r1) {
        return super.getHeader(r1);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public int getNavigationBarColor() {
        return getThemedColor(Theme.key_windowBackgroundWhite);
    }

    @Override // org.telegram.ui.GradientHeaderActivity, org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        this.attached = true;
        this.new_program = true;
        this.program = getDefaultProgram();
        this.initialProgram = null;
        TLRPC.UserFull r2 = getMessagesController().getUserFull(this.bot_id);
        if (r2 == null) goto L8;
        this.new_program = false;
        TL_payments.starRefProgram r22 = r2.starref_program;
        this.program = r22;
        if (r22 != null) goto L7;
        this.new_program = true;
        this.program = getDefaultProgram();
        this.initialProgram = null;
    L12:
        return super.onFragmentCreate();
    L7:
        TL_payments.starRefProgram r0 = new TL_payments.starRefProgram();
        this.initialProgram = r0;
        TL_payments.starRefProgram r1 = this.program;
        r0.commission_permille = r1.commission_permille;
        r0.duration_months = r1.duration_months;
        goto L12
    L8:
        TLRPC.User r12 = getMessagesController().getUser(Long.valueOf(this.bot_id));
        if (r12 == null) goto L12;
        getMessagesController().loadFullUser(r12, getClassGuid(), true, new AffiliateProgramFragment$$ExternalSyntheticLambda0(this));
        goto L12
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        this.attached = false;
        AndroidUtilities.cancelRunOnUIThread(this.updateTimerRunnable);
        super.onFragmentDestroy();
    }

    @Override // org.telegram.ui.GradientHeaderActivity, org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, AndroidUtilities.dp(84.0f) + r4);
        this.listView.setClipToPadding(false);
        this.buttonLayout.setPadding(0, 0, 0, r4);
    }

    @Override // org.telegram.ui.GradientHeaderActivity, org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        GLIconTextureView r0 = this.iconTextureView;
        if (r0 == null) goto L6;
        r0.setPaused(true);
        this.iconTextureView.setDialogVisible(true);
        return;
    }

    @Override // org.telegram.ui.GradientHeaderActivity, org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        GLIconTextureView r0 = this.iconTextureView;
        if (r0 == null) goto L6;
        r0.setPaused(false);
        this.iconTextureView.setDialogVisible(false);
        return;
    }
}
