package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import com.android.dx.io.Opcodes;
import java.util.ArrayList;
import java.util.Iterator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.TextHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

/* loaded from: classes6.dex */
public class EnableTopicsActivity extends BaseFragment {
    private TLRPC.Chat currentChat;
    private long dialogId;
    private boolean forum;
    private boolean isTabs;
    private UniversalRecyclerView listView;
    private Utilities.Callback2<Boolean, Boolean> onForumChanged;

    /* renamed from: org.telegram.ui.EnableTopicsActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ EnableTopicsActivity this$0;

        public AnonymousClass1(EnableTopicsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.EnableTopicsActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends UniversalRecyclerView {
        final /* synthetic */ EnableTopicsActivity this$0;

        public AnonymousClass2(EnableTopicsActivity r1, BaseFragment r2, Utilities.Callback2 r3, Utilities.Callback5 r4, Utilities.Callback5Return r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public Integer getSelectorColor(int r3) {
            UItem r0 = this.adapter.getItem(r3);
            if (r0 == null) goto L9;
            if (r0.id != 2) goto L9;
            return 0;
        L9:
            return super.getSelectorColor(r3);
        }
    }

    public static class TopicsLayoutSwitcher extends LinearLayout {
        private ValueAnimator animator;
        private final BackupImageView leftImageView;
        private final FrameLayout leftLayout;
        private final FrameLayout leftTitleBackground;
        private final FrameLayout leftTitleLayout;
        private final TextView leftTitleSelected;
        private final TextView leftTitleUnselected;
        private final Theme.ResourcesProvider resourcesProvider;
        private final BackupImageView rightImageView;
        private final FrameLayout rightLayout;
        private final FrameLayout rightTitleBackground;
        private final FrameLayout rightTitleLayout;
        private final TextView rightTitleSelected;
        private final TextView rightTitleUnselected;
        private float tabsAlpha;

        /* renamed from: org.telegram.ui.EnableTopicsActivity$TopicsLayoutSwitcher$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ TopicsLayoutSwitcher this$0;
            final /* synthetic */ boolean val$checked;

            public AnonymousClass1(TopicsLayoutSwitcher r1, boolean r2) {
                this.this$0 = r1;
                this.val$checked = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r8) {
                TopicsLayoutSwitcher r82 = this.this$0;
                if (this.val$checked == false) goto L5;
                float r0 = 1.0f;
            L6:
                TopicsLayoutSwitcher.m14709$$Nest$fputtabsAlpha(r82, r0);
                BackupImageView r83 = TopicsLayoutSwitcher.m14703$$Nest$fgetleftImageView(this.this$0);
                int r2 = Theme.key_windowBackgroundWhiteGrayText5;
                int r3 = Theme.getColor(r2, TopicsLayoutSwitcher.m14705$$Nest$fgetresourcesProvider(this.this$0));
                int r4 = Theme.key_featuredStickers_addButton;
                int r32 = ColorUtils.blendARGB(r3, Theme.getColor(r4, TopicsLayoutSwitcher.m14705$$Nest$fgetresourcesProvider(this.this$0)), TopicsLayoutSwitcher.m14708$$Nest$fgettabsAlpha(this.this$0));
                PorterDuff.Mode r5 = PorterDuff.Mode.SRC_IN;
                r83.setColorFilter(new PorterDuffColorFilter(r32, r5));
                TopicsLayoutSwitcher.m14703$$Nest$fgetleftImageView(this.this$0).invalidate();
                TopicsLayoutSwitcher.m14706$$Nest$fgetrightImageView(this.this$0).setColorFilter(new PorterDuffColorFilter(ColorUtils.blendARGB(Theme.getColor(r2, TopicsLayoutSwitcher.m14705$$Nest$fgetresourcesProvider(this.this$0)), Theme.getColor(r4, TopicsLayoutSwitcher.m14705$$Nest$fgetresourcesProvider(this.this$0)), 1.0f - TopicsLayoutSwitcher.m14708$$Nest$fgettabsAlpha(this.this$0)), r5));
                TopicsLayoutSwitcher.m14706$$Nest$fgetrightImageView(this.this$0).invalidate();
                return;
            L5:
                r0 = 0.0f;
                goto L6
            }
        }

        public static final class Factory extends UItem.UItemFactory<TopicsLayoutSwitcher> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem asSwitcher(int r1, View.OnClickListener r2, View.OnClickListener r3) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.id = r1;
                r0.object = r2;
                r0.object2 = r3;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                TopicsLayoutSwitcher r12 = (TopicsLayoutSwitcher) r1;
                TopicsLayoutSwitcher.m14704$$Nest$fgetleftLayout(r12).setOnClickListener((View.OnClickListener) r2.object);
                TopicsLayoutSwitcher.m14707$$Nest$fgetrightLayout(r12).setOnClickListener((View.OnClickListener) r2.object2);
                r12.setChecked(r2.checked, false);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public TopicsLayoutSwitcher createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new TopicsLayoutSwitcher(r1, r5);
            }
        }

        /* renamed from: $r8$lambda$JWBpb9-Y57IdfB1COIZrbg7lMW0, reason: not valid java name */
        public static /* synthetic */ void m14702$r8$lambda$JWBpb9Y57IdfB1COIZrbg7lMW0(TopicsLayoutSwitcher r0, ValueAnimator r1) {
            r0.lambda$setChecked$0(r1);
        }

        /* renamed from: -$$Nest$fgetleftImageView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BackupImageView m14703$$Nest$fgetleftImageView(TopicsLayoutSwitcher r0) {
            return r0.leftImageView;
        }

        /* renamed from: -$$Nest$fgetleftLayout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FrameLayout m14704$$Nest$fgetleftLayout(TopicsLayoutSwitcher r0) {
            return r0.leftLayout;
        }

        /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m14705$$Nest$fgetresourcesProvider(TopicsLayoutSwitcher r0) {
            return r0.resourcesProvider;
        }

        /* renamed from: -$$Nest$fgetrightImageView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BackupImageView m14706$$Nest$fgetrightImageView(TopicsLayoutSwitcher r0) {
            return r0.rightImageView;
        }

        /* renamed from: -$$Nest$fgetrightLayout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FrameLayout m14707$$Nest$fgetrightLayout(TopicsLayoutSwitcher r0) {
            return r0.rightLayout;
        }

        /* renamed from: -$$Nest$fgettabsAlpha, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m14708$$Nest$fgettabsAlpha(TopicsLayoutSwitcher r0) {
            return r0.tabsAlpha;
        }

        /* renamed from: -$$Nest$fputtabsAlpha, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14709$$Nest$fputtabsAlpha(TopicsLayoutSwitcher r0, float r1) {
            r0.tabsAlpha = r1;
        }

        public TopicsLayoutSwitcher(Context r33, Theme.ResourcesProvider r34) {
            super(r33);
            this.resourcesProvider = r34;
            setOrientation(0);
            setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
            boolean r4 = Theme.isCurrentThemeMonet();
            FrameLayout r5 = new FrameLayout(r33);
            this.leftLayout = r5;
            ScaleStateListAnimator.apply(r5, 0.05f, 1.25f);
            addView(r5, LayoutHelper.createLinear(-1, Opcodes.USHR_INT_LIT8, 1.0f, 119));
            BackupImageView r12 = new BackupImageView(r33);
            this.leftImageView = r12;
            r12.setImageDrawable(new RLottieDrawable(R.raw.topics_tabs, AndroidUtilities.dp(160.0f), AndroidUtilities.dp(160.0f)));
            r5.addView(r12, LayoutHelper.createFrame(160, 160.0f, 49, 0.0f, 12.33f, 0.0f, 0.0f));
            FrameLayout r8 = new FrameLayout(r33);
            this.leftTitleLayout = r8;
            int r122 = Theme.key_windowBackgroundWhiteGrayText2;
            TextView r15 = TextHelper.makeTextView(r33, 14.0f, r122, true);
            this.leftTitleUnselected = r15;
            r15.setText(LocaleController.getString(R.string.TopicsLayoutTabs));
            r15.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
            r8.addView(r15, LayoutHelper.createFrame(-2, -2, 17));
            FrameLayout r11 = new FrameLayout(r33);
            this.leftTitleBackground = r11;
            r11.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
            int r152 = AndroidUtilities.dp(13.0f);
            int r6 = Theme.key_featuredStickers_addButton;
            r11.setBackground(Theme.createRoundRectDrawable(r152, Theme.getColor(r6, r34)));
            r8.addView(r11, LayoutHelper.createFrame(-2, 26, 17));
            if (r4 == false) goto L5;
            int r153 = Theme.key_chats_actionIcon;
        L6:
            TextView r154 = TextHelper.makeTextView(r33, 14.0f, r153, true);
            this.leftTitleSelected = r154;
            r154.setText(LocaleController.getString(R.string.TopicsLayoutTabs));
            r11.addView(r154, LayoutHelper.createFrame(-2, -2, 17));
            r5.addView(r8, LayoutHelper.createFrame(-2, 26.0f, 49, 0.0f, 182.0f, 0.0f, 0.0f));
            FrameLayout r3 = new FrameLayout(r33);
            this.rightLayout = r3;
            ScaleStateListAnimator.apply(r3, 0.05f, 1.25f);
            addView(r3, LayoutHelper.createLinear(-1, Opcodes.USHR_INT_LIT8, 1.0f, 119));
            BackupImageView r52 = new BackupImageView(r33);
            this.rightImageView = r52;
            r52.setImageDrawable(new RLottieDrawable(R.raw.topics_list, AndroidUtilities.dp(160.0f), AndroidUtilities.dp(160.0f)));
            r3.addView(r52, LayoutHelper.createFrame(160, 160.0f, 49, 0.0f, 12.33f, 0.0f, 0.0f));
            FrameLayout r53 = new FrameLayout(r33);
            this.rightTitleLayout = r53;
            TextView r7 = TextHelper.makeTextView(r33, 14.0f, r122, true);
            this.rightTitleUnselected = r7;
            r7.setText(LocaleController.getString(R.string.TopicsLayoutList));
            r7.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
            r53.addView(r7, LayoutHelper.createFrame(-2, -2, 17));
            FrameLayout r72 = new FrameLayout(r33);
            this.rightTitleBackground = r72;
            r72.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
            r72.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(13.0f), Theme.getColor(r6, r34)));
            r53.addView(r72, LayoutHelper.createFrame(-2, 26, 17));
            if (r4 == false) goto L9;
            int r2 = Theme.key_chats_actionIcon;
        L10:
            TextView r1 = TextHelper.makeTextView(r33, 14.0f, r2, true);
            this.rightTitleSelected = r1;
            r1.setText(LocaleController.getString(R.string.TopicsLayoutList));
            r72.addView(r1, LayoutHelper.createFrame(-2, -2, 17));
            r3.addView(r53, LayoutHelper.createFrame(-2, 26.0f, 49, 0.0f, 182.0f, 0.0f, 0.0f));
            setChecked(false, false);
            return;
        L9:
            r2 = Theme.key_windowBackgroundCheckText;
            goto L10
        L5:
            r153 = Theme.key_windowBackgroundCheckText;
            goto L6
        }

        private /* synthetic */ void lambda$setChecked$0(ValueAnimator r7) {
            this.tabsAlpha = ((Float) r7.getAnimatedValue()).floatValue();
            BackupImageView r72 = this.leftImageView;
            int r1 = Theme.key_windowBackgroundWhiteGrayText5;
            int r2 = Theme.getColor(r1, this.resourcesProvider);
            int r3 = Theme.key_featuredStickers_addButton;
            int r22 = ColorUtils.blendARGB(r2, Theme.getColor(r3, this.resourcesProvider), this.tabsAlpha);
            PorterDuff.Mode r4 = PorterDuff.Mode.SRC_IN;
            r72.setColorFilter(new PorterDuffColorFilter(r22, r4));
            this.leftImageView.invalidate();
            this.rightImageView.setColorFilter(new PorterDuffColorFilter(ColorUtils.blendARGB(Theme.getColor(r1, this.resourcesProvider), Theme.getColor(r3, this.resourcesProvider), 1.0f - this.tabsAlpha), r4));
            this.rightImageView.invalidate();
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }

        public void setChecked(boolean r11, boolean r12) {
            ValueAnimator r0 = this.animator;
            if (r0 == null) goto L5;
            r0.cancel();
            this.animator = null;
        L5:
            FrameLayout r02 = this.leftTitleBackground;
            float r3 = 1.0f;
            if (r12 == false) goto L36;
            ViewPropertyAnimator r122 = r02.animate();
            if (r11 == true) goto L10;
            float r03 = 0.0f;
        L11:
            ViewPropertyAnimator r123 = r122.scaleX(r03);
            if (r11 == true) goto L14;
            float r04 = 0.0f;
        L15:
            ViewPropertyAnimator r124 = r123.scaleY(r04);
            if (r11 == true) goto L18;
            float r05 = 0.0f;
        L19:
            ViewPropertyAnimator r125 = r124.alpha(r05);
            CubicBezierInterpolator r06 = CubicBezierInterpolator.EASE_OUT_QUINT;
            r125.setInterpolator(r06).setDuration(320).start();
            ViewPropertyAnimator r126 = this.rightTitleBackground.animate();
            if (r11 == false) goto L22;
            float r7 = 0.0f;
        L23:
            ViewPropertyAnimator r127 = r126.scaleX(r7);
            if (r11 == false) goto L26;
            float r72 = 0.0f;
        L27:
            ViewPropertyAnimator r128 = r127.scaleY(r72);
            if (r11 == false) goto L30;
            float r73 = 0.0f;
        L31:
            r128.alpha(r73).setInterpolator(r06).setDuration(320).start();
            float r129 = this.tabsAlpha;
            if (r11 == true) goto L35;
            r3 = 0.0f;
        L35:
            ValueAnimator r1210 = ValueAnimator.ofFloat(new float[]{r129, r3});
            this.animator = r1210;
            r1210.addUpdateListener(new EnableTopicsActivity$TopicsLayoutSwitcher$$ExternalSyntheticLambda0(this));
            this.animator.addListener(new AnonymousClass1(this, r11));
            this.animator.setInterpolator(r06);
            this.animator.setDuration(320);
            this.animator.start();
        L65:
            if (r11 == false) goto L67;
            BackupImageView r10 = this.leftImageView;
        L68:
            RLottieDrawable r102 = r10.getImageReceiver().getLottieAnimation();
            if (r102 == null) goto L79;
            float r1211 = r102.getProgress();
            if (r11 == false) goto L73;
            float r112 = 0.85f;
        L75:
            if (r1211 <= r112) goto L77;
            r102.setProgress(0.0f, false);
        L77:
            r102.restart(true);
            return;
        L73:
            r112 = 0.8f;
            goto L75
        L79:
            return;
        L67:
            r10 = this.rightImageView;
            goto L68
        L30:
            r73 = 1.0f;
            goto L31
        L26:
            r72 = 1.0f;
            goto L27
        L22:
            r7 = 1.0f;
            goto L23
        L18:
            r05 = 1.0f;
            goto L19
        L14:
            r04 = 1.0f;
            goto L15
        L10:
            r03 = 1.0f;
            goto L11
        L36:
            r02.animate().cancel();
            this.rightTitleBackground.animate().cancel();
            FrameLayout r1212 = this.leftTitleBackground;
            if (r11 == true) goto L39;
            float r07 = 0.0f;
        L40:
            r1212.setScaleX(r07);
            FrameLayout r1213 = this.leftTitleBackground;
            if (r11 == true) goto L43;
            float r08 = 0.0f;
        L44:
            r1213.setScaleY(r08);
            FrameLayout r1214 = this.leftTitleBackground;
            if (r11 == true) goto L47;
            float r09 = 0.0f;
        L48:
            r1214.setAlpha(r09);
            FrameLayout r1215 = this.rightTitleBackground;
            if (r11 == false) goto L51;
            float r010 = 0.0f;
        L52:
            r1215.setScaleX(r010);
            FrameLayout r1216 = this.rightTitleBackground;
            if (r11 == false) goto L55;
            float r011 = 0.0f;
        L56:
            r1216.setScaleY(r011);
            FrameLayout r1217 = this.rightTitleBackground;
            if (r11 == false) goto L59;
            float r012 = 0.0f;
        L60:
            r1217.setAlpha(r012);
            if (r11 == false) goto L63;
            float r1218 = 1.0f;
        L64:
            this.tabsAlpha = r1218;
            BackupImageView r1219 = this.leftImageView;
            int r5 = Theme.key_windowBackgroundWhiteGrayText5;
            int r6 = Theme.getColor(r5, this.resourcesProvider);
            int r74 = Theme.key_featuredStickers_addButton;
            int r62 = ColorUtils.blendARGB(r6, Theme.getColor(r74, this.resourcesProvider), this.tabsAlpha);
            PorterDuff.Mode r8 = PorterDuff.Mode.SRC_IN;
            r1219.setColorFilter(new PorterDuffColorFilter(r62, r8));
            this.leftImageView.invalidate();
            this.rightImageView.setColorFilter(new PorterDuffColorFilter(ColorUtils.blendARGB(Theme.getColor(r5, this.resourcesProvider), Theme.getColor(r74, this.resourcesProvider), 1.0f - this.tabsAlpha), r8));
            this.rightImageView.invalidate();
            goto L65
        L63:
            r1218 = 0.0f;
            goto L64
        L59:
            r012 = 1.0f;
            goto L60
        L55:
            r011 = 1.0f;
            goto L56
        L51:
            r010 = 1.0f;
            goto L52
        L47:
            r09 = 1.0f;
            goto L48
        L43:
            r08 = 1.0f;
            goto L44
        L39:
            r07 = 1.0f;
            goto L40
        }
    }

    public static /* synthetic */ void $r8$lambda$25M3RVRUTqaHCwOLmjmgJU2qso0(EnableTopicsActivity r0, View r1) {
        r0.lambda$fillItems$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$2EG7icP6pQAUcpwoeODdkUlEOv4(EnableTopicsActivity r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$9O_7YPsjQ_eqJP7hlN8YHIKl2GQ(EnableTopicsActivity r0, View r1) {
        r0.lambda$fillItems$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$u4JEwYMdahjPQxs2K9iREXb4eSw(EnableTopicsActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onItemClick(r1, r2, r3, r4, r5);
    }

    public EnableTopicsActivity(long r1) {
        this.dialogId = r1;
    }

    private void fillItems(ArrayList<UItem> r3, UniversalAdapter r4) {
        r3.add(UItem.asTopView(LocaleController.getString(R.string.TopicsInfo), R.raw.topics_top));
        r3.add(UItem.asCheck(1, LocaleController.getString(R.string.TopicsEnable)).setChecked(this.forum));
        if (this.forum == false) goto L6;
        r3.add(UItem.asShadow(null));
        r3.add(UItem.asHeader(LocaleController.getString(R.string.TopicsLayout)));
        r3.add(TopicsLayoutSwitcher.Factory.asSwitcher(2, new EnableTopicsActivity$$ExternalSyntheticLambda2(this), new EnableTopicsActivity$$ExternalSyntheticLambda3(this)).setChecked(this.isTabs));
        r3.add(UItem.asShadow(LocaleController.getString(R.string.TopicsLayoutInfo)));
        return;
    }

    private /* synthetic */ void lambda$fillItems$0(View r3) {
        TopicsLayoutSwitcher r32 = (TopicsLayoutSwitcher) r3.getParent();
        this.isTabs = true;
        r32.setChecked(true, true);
        Utilities.Callback2<Boolean, Boolean> r33 = this.onForumChanged;
        if (r33 == null) goto L5;
        r33.run(Boolean.valueOf(this.forum), Boolean.valueOf(this.isTabs));
    L5:
        topicsLayoutChanged();
    }

    private /* synthetic */ void lambda$fillItems$1(View r3) {
        TopicsLayoutSwitcher r32 = (TopicsLayoutSwitcher) r3.getParent();
        this.isTabs = false;
        r32.setChecked(false, true);
        Utilities.Callback2<Boolean, Boolean> r33 = this.onForumChanged;
        if (r33 == null) goto L5;
        r33.run(Boolean.valueOf(this.forum), Boolean.valueOf(this.isTabs));
    L5:
        topicsLayoutChanged();
    }

    private void onItemClick(UItem r1, View r2, int r3, float r4, float r5) {
        if (r1.id == 1) goto L5;
        return;
    L5:
        if (this.currentChat == null) goto L13;
        boolean r12 = !this.forum;
        this.forum = r12;
        Utilities.Callback2<Boolean, Boolean> r42 = this.onForumChanged;
        if (r42 == null) goto L10;
        r42.run(Boolean.valueOf(r12), Boolean.valueOf(this.isTabs));
    L10:
        ((TextCheckCell) r2).setChecked(this.forum);
        this.listView.adapter.update(true);
        return;
    }

    private void topicsLayoutChanged() {
        if (this.isTabs == true) goto L5;
        return;
    L5:
        if (getParentLayout() == null) goto L22;
        Iterator<BaseFragment> r2 = getParentLayout().getFragmentStack().iterator();
    L8:
        if (r2.hasNext() == false) goto L23;
        BaseFragment r0 = r2.next();
        if ((r0 instanceof DialogsActivity) == false) goto L8;
        RightSlidingDialogContainer r02 = ((DialogsActivity) r0).rightSlidingDialogContainer;
        if (r02.hasFragment() == false) goto L8;
        r02.finishPreview();
        goto L8
    L23:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r8) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.actionBar.setTitle(LocaleController.getString(R.string.TopicsTitle));
        FrameLayout r0 = new FrameLayout(r8);
        AnonymousClass2 r1 = new AnonymousClass2(this, this, new EnableTopicsActivity$$ExternalSyntheticLambda0(this), new EnableTopicsActivity$$ExternalSyntheticLambda1(this), null);
        this.listView = r1;
        r1.setSections();
        this.listView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray, this.resourceProvider));
        r0.addView(this.listView, LayoutHelper.createFrame(-1, -1, 119));
        this.actionBar.setAdaptiveBackground(this.listView);
        this.fragmentView = r0;
        return r0;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        this.currentChat = getMessagesController().getChat(Long.valueOf(-this.dialogId));
        return super.onFragmentCreate();
    }

    public void setOnForumChanged(boolean r1, boolean r2, Utilities.Callback2<Boolean, Boolean> r3) {
        this.forum = r1;
        this.isTabs = r2;
        this.onForumChanged = r3;
    }
}
