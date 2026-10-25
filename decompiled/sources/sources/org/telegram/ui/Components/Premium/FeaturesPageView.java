package org.telegram.ui.Components.Premium;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.FixedHeightEmptyCell;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.GradientTools;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.GLIcon.GLIconRenderer;
import org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView;
import org.telegram.ui.Components.Premium.StarParticlesView;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public class FeaturesPageView extends BaseListPageView {
    RecyclerListView.SelectionAdapter adapter;
    Bitmap bitmap;
    ArrayList<Item> items;
    public final int type;

    /* renamed from: org.telegram.ui.Components.Premium.FeaturesPageView$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ FeaturesPageView this$0;

        public AnonymousClass1(FeaturesPageView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.this$0.items.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return this.this$0.items.get(r1).viewType;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r5, int r6) {
            if (this.this$0.items.get(r6).viewType != 1) goto L6;
            ItemCell r52 = (ItemCell) r5.itemView;
            r52.imageView.setColorFilter(new PorterDuffColorFilter(this.this$0.bitmap.getPixel(r6, 0), PorterDuff.Mode.MULTIPLY));
            r52.imageView.setImageDrawable(ContextCompat.getDrawable(this.this$0.getContext(), this.this$0.items.get(r6).iconRes));
            r52.textView.setText(this.this$0.items.get(r6).text);
            r52.description.setText(this.this$0.items.get(r6).description);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            if (r3 != 0) goto L5;
            FeaturesPageView r1 = this.this$0;
            View r22 = new HeaderView(r1, r1.getContext());
        L8:
            r22.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r22);
        L5:
            if (r3 != 2) goto L7;
            r22 = new FixedHeightEmptyCell(this.this$0.getContext(), 16);
            goto L8
        L7:
            FeaturesPageView r12 = this.this$0;
            r22 = new ItemCell(r12, r12.getContext());
            goto L8
        }
    }

    public class HeaderView extends FrameLayout {
        GradientTools gradientTools;
        int height;
        GLIconTextureView iconTextureView;
        BackupImageView imageView;
        StarParticlesView starParticlesView;
        final /* synthetic */ FeaturesPageView this$0;

        /* renamed from: org.telegram.ui.Components.Premium.FeaturesPageView$HeaderView$1, reason: invalid class name */
        public class AnonymousClass1 extends StarParticlesView {
            final /* synthetic */ HeaderView this$1;
            final /* synthetic */ FeaturesPageView val$this$0;

            public AnonymousClass1(HeaderView r1, Context r2, FeaturesPageView r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // org.telegram.ui.Components.Premium.StarParticlesView
            public void configure() {
                StarParticlesView.Drawable r0 = this.drawable;
                r0.useGradient = true;
                r0.useBlur = false;
                r0.checkBounds = true;
                r0.isCircle = true;
                r0.centerOffsetY = AndroidUtilities.dp(-14.0f);
                StarParticlesView.Drawable r3 = this.drawable;
                r3.minLifeTime = 2000;
                r3.randLifeTime = 3000;
                r3.size1 = 16;
                r3.useRotate = false;
                r3.type = 28;
                r3.colorKey = Theme.key_premiumGradient2;
                r3.init();
            }

            @Override // org.telegram.ui.Components.Premium.StarParticlesView, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, r3);
                this.drawable.rect2.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight() - AndroidUtilities.dp(52.0f));
            }
        }

        /* renamed from: org.telegram.ui.Components.Premium.FeaturesPageView$HeaderView$2, reason: invalid class name */
        public class AnonymousClass2 extends GLIconTextureView {
            final /* synthetic */ HeaderView this$1;
            final /* synthetic */ FeaturesPageView val$this$0;

            public AnonymousClass2(HeaderView r1, Context r2, int r3, int r4, FeaturesPageView r5) {
                this.this$1 = r1;
                this.val$this$0 = r5;
                super(r2, r3, r4);
            }

            @Override // org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView, android.view.TextureView, android.view.View
            public void onAttachedToWindow() {
                super.onAttachedToWindow();
                setPaused(false);
            }

            @Override // org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView, android.view.View
            public void onDetachedFromWindow() {
                super.onDetachedFromWindow();
                setPaused(true);
            }
        }

        public HeaderView(FeaturesPageView r16, Context r17) {
            this.this$0 = r16;
            super(r17);
            this.gradientTools = new GradientTools();
            int r0 = r16.type;
            if (r0 != 0) goto L6;
            this.height = AndroidUtilities.dp(150.0f);
            BackupImageView r02 = new BackupImageView(r17);
            this.imageView = r02;
            r02.setRoundRadius((int) (AndroidUtilities.dp(65.0f) / 2.0f));
            addView(this.imageView, LayoutHelper.createFrame(65, 65.0f, 1, 0.0f, 32.0f, 0.0f, 0.0f));
            TLRPC.User r03 = UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser();
            AvatarDrawable r1 = new AvatarDrawable();
            r1.setInfo(r03);
            this.imageView.getImageReceiver().setForUserOrChat(r03, r1);
            TextView r04 = new TextView(r17);
            r04.setTextSize(1, 20.0f);
            r04.setTypeface(AndroidUtilities.bold());
            r04.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r16.resourcesProvider));
            r04.setText(LocaleController.getString(R.string.UpgradedStories));
            addView(r04, LayoutHelper.createFrame(-2, -2.0f, 1, 0.0f, 111.0f, 0.0f, 0.0f));
            GradientTools r05 = this.gradientTools;
            r05.isLinear = true;
            r05.isDiagonal = true;
            r05.setColors(Theme.getColor(Theme.key_premiumGradient2), Theme.getColor(Theme.key_premiumGradient1));
            this.gradientTools.paint.setStyle(Paint.Style.STROKE);
            this.gradientTools.paint.setStrokeCap(Paint.Cap.ROUND);
            this.gradientTools.paint.setStrokeWidth(AndroidUtilities.dpf2(3.3f));
            return;
        L6:
            if (r0 != 1) goto L12;
            AnonymousClass1 r06 = new AnonymousClass1(this, r17, r16);
            this.starParticlesView = r06;
            addView(r06, LayoutHelper.createFrame(-1, 190, 55));
            AnonymousClass2 r07 = new AnonymousClass2(this, r17, 1, 1, r16);
            this.iconTextureView = r07;
            r07.setStarParticlesView(this.starParticlesView);
            Bitmap r08 = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
            Canvas r3 = new Canvas(r08);
            int r4 = Theme.key_premiumGradient2;
            r3.drawColor(ColorUtils.blendARGB(Theme.getColor(r4, r16.resourcesProvider), Theme.getColor(Theme.key_dialogBackground, r16.resourcesProvider), 0.5f));
            this.iconTextureView.setBackgroundBitmap(r08);
            GLIconRenderer r09 = this.iconTextureView.mRenderer;
            r09.colorKey1 = r4;
            r09.colorKey2 = Theme.key_premiumGradient1;
            r09.updateColors();
            addView(this.iconTextureView, LayoutHelper.createFrame(160, 160, 1));
            GLIconTextureView r010 = this.iconTextureView;
            if (r010 == null) goto L10;
            r010.startEnterAnimation(-360, 100);
        L10:
            TextView r011 = new TextView(r17);
            r011.setTextSize(1, 20.0f);
            r011.setTypeface(AndroidUtilities.bold());
            r011.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r16.resourcesProvider));
            r011.setText(LocaleController.getString(R.string.TelegramBusiness));
            r011.setGravity(17);
            addView(r011, LayoutHelper.createFrame(-2, -2.0f, 1, 33.0f, 150.0f, 33.0f, 0.0f));
            TextView r012 = new TextView(r17);
            r012.setTextSize(1, 14.0f);
            r012.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r16.resourcesProvider));
            r012.setText(LocaleController.getString(R.string.TelegramBusinessSubtitle2));
            r012.setGravity(17);
            addView(r012, LayoutHelper.createFrame(-2, -2.0f, 1, 33.0f, 183.0f, 33.0f, 20.0f));
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r11) {
            if (this.this$0.type != 0) goto L8;
            BackupImageView r0 = this.imageView;
            Rect r1 = AndroidUtilities.rectTmp2;
            r0.getHitRect(r1);
            RectF r02 = AndroidUtilities.rectTmp;
            r02.set(r1);
            r02.inset(-AndroidUtilities.dp(5.0f), -AndroidUtilities.dp(5.0f));
            this.gradientTools.setBounds(r02);
            int r03 = 0;
        L6:
            if (r03 >= 7) goto L8;
            float r2 = (r03 * 51.42857f) - 90.0f;
            float r6 = r2 + 5.0f;
            r11.drawArc(AndroidUtilities.rectTmp, r6, ((51.42857f + r2) - 5.0f) - r6, false, this.gradientTools.paint);
            r03 = r03 + 1;
        L8:
            super.dispatchDraw(r11);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            int r0 = this.height;
            if (r0 <= 0) goto L6;
            r3 = View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30);
        L6:
            super.onMeasure(r2, r3);
        }
    }

    public class Item {
        String description;
        int iconRes;
        int order;
        String text;
        final /* synthetic */ FeaturesPageView this$0;
        final int viewType;

        private Item(FeaturesPageView r1, int r2) {
            this.this$0 = r1;
            this.viewType = r2;
        }

        public Item(FeaturesPageView r1, int r2, int r3, String r4, String r5, int r6) {
            this.this$0 = r1;
            this.viewType = r2;
            this.iconRes = r3;
            this.text = r4;
            this.description = r5;
            this.order = r6;
        }

        public /* synthetic */ Item(FeaturesPageView r1, int r2, FeaturesPageViewIA r3) {
            this(r1, r2);
        }
    }

    public class ItemCell extends FrameLayout {
        TextView description;
        ImageView imageView;
        TextView textView;
        final /* synthetic */ FeaturesPageView this$0;

        public ItemCell(FeaturesPageView r11, Context r12) {
            this.this$0 = r11;
            super(r12);
            ImageView r0 = new ImageView(r12);
            this.imageView = r0;
            r0.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            addView(this.imageView, LayoutHelper.createFrame(28, 28.0f, 0, 25.0f, 12.0f, 16.0f, 0.0f));
            TextView r02 = new TextView(r12);
            this.textView = r02;
            r02.setTypeface(AndroidUtilities.bold());
            this.textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r11.resourcesProvider));
            this.textView.setTextSize(1, 14.0f);
            addView(this.textView, LayoutHelper.createFrame(-1, -2.0f, 0, 68.0f, 8.0f, 16.0f, 0.0f));
            TextView r03 = new TextView(r12);
            this.description = r03;
            r03.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, r11.resourcesProvider));
            this.description.setTextSize(1, 14.0f);
            addView(this.description, LayoutHelper.createFrame(-1, -2.0f, 0, 68.0f, 28.0f, 16.0f, 8.0f));
        }
    }

    /* renamed from: $r8$lambda$dXX_8n-C6-cQoBeyITZRw1xIN6w, reason: not valid java name */
    public static /* synthetic */ int m11929$r8$lambda$dXX_8nC6cQoBeyITZRw1xIN6w(SparseIntArray r1, Item r2, Item r3) {
        return r1.get(r2.order, Integer.MAX_VALUE) - r1.get(r3.order, Integer.MAX_VALUE);
    }

    public FeaturesPageView(Context r16, int r17, Theme.ResourcesProvider r18) {
        super(r16, r18);
        this.items = new ArrayList();
        this.type = r17;
        ArrayList r7 = new ArrayList();
        MessagesController r2 = MessagesController.getInstance(UserConfig.selectedAccount);
        if (r17 != 0) goto L5;
        r7.add(new Item(this, 1, R.drawable.msg_stories_order, LocaleController.getString(R.string.PremiumStoriesPriority), LocaleController.getString(R.string.PremiumStoriesPriorityDescription), 20));
        r7.add(new Item(this, 1, R.drawable.msg_stories_stealth, LocaleController.getString(R.string.PremiumStoriesStealth), LocaleController.getString(R.string.PremiumStoriesStealthDescription), 15));
        r7.add(new Item(this, 1, R.drawable.menu_quality_hd, LocaleController.getString(R.string.PremiumStoriesQuality), LocaleController.getString(R.string.PremiumStoriesQualityDescription), 25));
        r7.add(new Item(this, 1, R.drawable.msg_stories_views, LocaleController.getString(R.string.PremiumStoriesViews), LocaleController.getString(R.string.PremiumStoriesViewsDescription), 16));
        r7.add(new Item(this, 1, R.drawable.msg_stories_timer, LocaleController.getString(R.string.PremiumStoriesExpiration), LocaleController.getString(R.string.PremiumStoriesExpirationDescription), 17));
        r7.add(new Item(this, 1, R.drawable.msg_stories_save, LocaleController.getString(R.string.PremiumStoriesSaveToGallery), LocaleController.getString(R.string.PremiumStoriesSaveToGalleryDescription), 18));
        r7.add(new Item(this, 1, R.drawable.msg_stories_caption, LocaleController.getString(R.string.PremiumStoriesCaption), LocaleController.getString(R.string.PremiumStoriesCaptionDescription), 21));
        r7.add(new Item(this, 1, R.drawable.msg_stories_link, LocaleController.getString(R.string.PremiumStoriesFormatting), LocaleController.getString(R.string.PremiumStoriesFormattingDescription), 19));
    L7:
        SparseIntArray r10 = null;
    L8:
        if (r10 == null) goto L10;
        Collections.sort(r7, new FeaturesPageView$$ExternalSyntheticLambda0(r10));
    L10:
        this.items.add(new Item(this, 0, null));
        this.items.addAll(r7);
        this.items.add(new Item(this, 2, null));
        this.bitmap = Bitmap.createBitmap(this.items.size(), 1, Bitmap.Config.ARGB_8888);
        Canvas r0 = new Canvas(this.bitmap);
        Paint r6 = new Paint();
        r6.setShader(new LinearGradient(0.0f, 0.0f, this.bitmap.getWidth(), 0.0f, new int[]{Theme.getColor(Theme.key_premiumGradient1), Theme.getColor(Theme.key_premiumGradient2), Theme.getColor(Theme.key_premiumGradient3), Theme.getColor(Theme.key_premiumGradient4)}, null, Shader.TileMode.CLAMP));
        r0.drawRect(0.0f, 0.0f, this.bitmap.getWidth(), this.bitmap.getHeight(), r6);
        return;
    L5:
        if (r17 != 1) goto L7;
        r10 = r2.businessFeaturesTypesToPosition;
        r7.add(new Item(this, 1, R.drawable.menu_premium_location, LocaleController.getString(R.string.PremiumBusinessLocation), LocaleController.getString(R.string.PremiumBusinessLocationDescription), 29));
        r7.add(new Item(this, 1, R.drawable.menu_premium_clock, LocaleController.getString(R.string.PremiumBusinessOpeningHours), LocaleController.getString(R.string.PremiumBusinessOpeningHoursDescription), 30));
        r7.add(new Item(this, 1, R.drawable.menu_quickreply, LocaleController.getString(R.string.PremiumBusinessQuickReplies), LocaleController.getString(R.string.PremiumBusinessQuickRepliesDescription), 31));
        r7.add(new Item(this, 1, R.drawable.menu_feature_status, LocaleController.getString(R.string.PremiumBusinessGreetingMessages), LocaleController.getString(R.string.PremiumBusinessGreetingMessagesDescription), 32));
        r7.add(new Item(this, 1, R.drawable.menu_premium_away, LocaleController.getString(R.string.PremiumBusinessAwayMessages), LocaleController.getString(R.string.PremiumBusinessAwayMessagesDescription), 33));
        r7.add(new Item(this, 1, R.drawable.menu_premium_chatbot, LocaleController.getString(R.string.PremiumBusinessChatbots2), LocaleController.getString(R.string.PremiumBusinessChatbotsDescription), 34));
        r7.add(new Item(this, 1, R.drawable.menu_feature_intro, LocaleController.getString(R.string.PremiumBusinessIntro), LocaleController.getString(R.string.PremiumBusinessIntroDescription), 36));
        r7.add(new Item(this, 1, R.drawable.menu_premium_chatlink, LocaleController.getString(R.string.PremiumBusinessChatLinks), LocaleController.getString(R.string.PremiumBusinessChatLinksDescription), 37));
        goto L8
    }

    @Override // org.telegram.ui.Components.Premium.BaseListPageView
    public RecyclerView.Adapter createAdapter() {
        AnonymousClass1 r0 = new AnonymousClass1(this);
        this.adapter = r0;
        return r0;
    }
}
