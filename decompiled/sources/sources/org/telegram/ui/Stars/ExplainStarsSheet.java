package org.telegram.ui.Stars;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Premium.GLIcon.GLIconRenderer;
import org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView;
import org.telegram.ui.Components.Premium.StarParticlesView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.StarAppsSheet;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public class ExplainStarsSheet extends BottomSheetWithRecyclerListView {
    private UniversalAdapter adapter;
    private FrameLayout buttonContainer;
    private LinearLayout headerView;

    /* renamed from: org.telegram.ui.Stars.ExplainStarsSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends UniversalAdapter {
        final /* synthetic */ ExplainStarsSheet this$0;

        public AnonymousClass1(ExplainStarsSheet r1, RecyclerListView r2, Context r3, int r4, int r5, boolean r6, Utilities.Callback2 r7, Theme.ResourcesProvider r8) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8);
        }

        @Override // org.telegram.ui.Components.UniversalAdapter, org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return false;
        }
    }

    public static class FeatureCell extends LinearLayout {
        public final ImageView imageView;
        public final LinkSpanDrawable.LinksTextView subtitleView;
        public final LinearLayout textLayout;
        public final TextView titleView;

        public static class Factory extends UItem.UItemFactory<FeatureCell> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem of(int r2, CharSequence r3, CharSequence r4) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.selectable = false;
                r0.intValue = r2;
                r0.text = r3;
                r0.subtext = r4;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                ((FeatureCell) r1).set(r2.intValue, r2.text, r2.subtext);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public FeatureCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new FeatureCell(r1, 0, r5);
            }
        }

        public FeatureCell(Context r13, int r14, Theme.ResourcesProvider r15) {
            super(r13);
            setOrientation(0);
            float r1 = 32.0f;
            if (r14 != 1) goto L5;
            float r4 = 11.0f;
        L6:
            int r42 = AndroidUtilities.dp(r4);
            if (r14 != 1) goto L9;
            r1 = 11.0f;
        L9:
            int r12 = AndroidUtilities.dp(r1);
            if (r14 != 1) goto L12;
            float r142 = 8.0f;
        L13:
            setPadding(r42, 0, r12, AndroidUtilities.dp(r142));
            ImageView r143 = new ImageView(r13);
            this.imageView = r143;
            int r16 = Theme.key_windowBackgroundWhiteBlackText;
            r143.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r16), PorterDuff.Mode.SRC_IN));
            r143.setScaleType(ImageView.ScaleType.CENTER);
            addView(r143, LayoutHelper.createLinear(24, 24, 51, 0, 6, 16, 0));
            LinearLayout r144 = new LinearLayout(r13);
            this.textLayout = r144;
            r144.setOrientation(1);
            LinkSpanDrawable.LinksTextView r0 = new LinkSpanDrawable.LinksTextView(r13);
            this.titleView = r0;
            r0.setTypeface(AndroidUtilities.bold());
            r0.setTextSize(1, 14.0f);
            r0.setTextColor(Theme.getColor(r16, r15));
            int r17 = Theme.key_chat_messageLinkIn;
            r0.setLinkTextColor(Theme.getColor(r17, r15));
            r144.addView(r0, LayoutHelper.createLinear(-1, -2, 7, 0, 0, 0, 3));
            LinkSpanDrawable.LinksTextView r02 = new LinkSpanDrawable.LinksTextView(r13);
            this.subtitleView = r02;
            r02.setTextSize(1, 14.0f);
            r02.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r15));
            r02.setLinkTextColor(Theme.getColor(r17, r15));
            r144.addView(r02, LayoutHelper.createLinear(-1, -2, 7));
            addView(r144, LayoutHelper.createLinear(-1, -2, 1.0f, 55, 0, 0, 0, 0));
            return;
        L12:
            r142 = 12.0f;
            goto L13
        L5:
            r4 = 32.0f;
            goto L6
        }

        public void set(int r2, CharSequence r3, CharSequence r4) {
            this.imageView.setImageResource(r2);
            this.titleView.setText(r3);
            this.subtitleView.setText(r4);
        }

        public void setSubtitle(CharSequence r1) {
            this.subtitleView.setText(r1);
        }

        public void setTitle(CharSequence r1) {
            this.titleView.setText(r1);
        }
    }

    public static /* synthetic */ void $r8$lambda$vMIqz8xIDm_apgjEjJ4mdIqZhxQ(ExplainStarsSheet r0) {
        r0.lambda$fillItems$1();
    }

    /* renamed from: $r8$lambda$zbHT8OLATyJyfUfmZiTR-bZXLN8, reason: not valid java name */
    public static /* synthetic */ void m18890$r8$lambda$zbHT8OLATyJyfUfmZiTRbZXLN8(ExplainStarsSheet r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public ExplainStarsSheet(Context r18) {
        super(r18, null, false, false, false, null);
        this.topPadding = 0.1f;
        fixNavigationBar();
        RecyclerListView r2 = this.recyclerListView;
        int r3 = this.backgroundPaddingLeft;
        r2.setPadding(r3, 0, r3, 0);
        LinearLayout r22 = new LinearLayout(r18);
        this.headerView = r22;
        r22.setOrientation(1);
        FrameLayout r23 = new FrameLayout(r18);
        r23.setClipChildren(false);
        r23.setClipToPadding(false);
        StarParticlesView r5 = StarsIntroActivity.makeParticlesView(r18, 70, 0);
        r23.addView(r5, LayoutHelper.createFrame(-1, -1.0f));
        GLIconTextureView r6 = new GLIconTextureView(r18, 1, 2);
        GLIconRenderer r8 = r6.mRenderer;
        r8.colorKey1 = Theme.key_starsGradient1;
        r8.colorKey2 = Theme.key_starsGradient2;
        r8.updateColors();
        r6.setStarParticlesView(r5);
        r23.addView(r6, LayoutHelper.createFrame(170, 170.0f, 17, 0.0f, 32.0f, 0.0f, 24.0f));
        r6.setPaused(false);
        this.headerView.addView(r23, LayoutHelper.createFrame(-1, 150.0f));
        TextView r24 = new TextView(r18);
        r24.setTextSize(1, 20.0f);
        r24.setTypeface(AndroidUtilities.bold());
        int r52 = Theme.key_dialogTextBlack;
        r24.setTextColor(Theme.getColor(r52, this.resourcesProvider));
        r24.setGravity(17);
        r24.setText(LocaleController.getString(R.string.ExplainStarsTitle));
        this.headerView.addView(r24, LayoutHelper.createLinear(-2, -2, 1, 0, 2, 0, 0));
        TextView r25 = new TextView(r18);
        r25.setTextSize(1, 14.0f);
        r25.setTextColor(Theme.getColor(r52, this.resourcesProvider));
        r25.setGravity(17);
        r25.setText(LocaleController.getString(R.string.ExplainStarsTitle2));
        this.headerView.addView(r25, LayoutHelper.createLinear(-1, -2, 1, 16, 9, 16, 18));
        this.buttonContainer = new FrameLayout(r18);
        ButtonWithCounterView r26 = new ButtonWithCounterView(r18, this.resourcesProvider);
        r26.setText(LocaleController.getString(R.string.ExplainStarsButton), false);
        r26.setOnClickListener(new ExplainStarsSheet$$ExternalSyntheticLambda0(this));
        this.buttonContainer.addView(r26, LayoutHelper.createFrame(-1, 48.0f, 119, 10.0f, 10.0f, 10.0f, 10.0f));
        FrameLayout r1 = this.buttonContainer;
        int r27 = this.backgroundPaddingLeft;
        r1.setPadding(r27, 0, r27, 0);
        this.buttonContainer.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground));
        this.containerView.addView(this.buttonContainer, LayoutHelper.createFrame(-1, -2, 87));
        this.adapter.update(false);
    }

    private /* synthetic */ void lambda$fillItems$1() {
        new StarAppsSheet(getContext()).show();
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        dismiss();
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r10) {
        AnonymousClass1 r0 = new AnonymousClass1(this, r10, getContext(), this.currentAccount, 0, true, new ExplainStarsSheet$$ExternalSyntheticLambda1(this), this.resourcesProvider);
        this.adapter = r0;
        return r0;
    }

    public void fillItems(ArrayList<UItem> r4, UniversalAdapter r5) {
        r4.add(UItem.asCustom(this.headerView));
        r4.add(FeatureCell.Factory.of(R.drawable.msg_gift_premium, LocaleController.getString(R.string.ExplainStarsFeature1Title), LocaleController.getString(R.string.ExplainStarsFeature1Text)));
        r4.add(FeatureCell.Factory.of(R.drawable.msg_bot, LocaleController.getString(R.string.ExplainStarsFeature2Title), AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.ExplainStarsFeature2Text), new ExplainStarsSheet$$ExternalSyntheticLambda2(this)), true)));
        r4.add(FeatureCell.Factory.of(R.drawable.menu_unlock, LocaleController.getString(R.string.ExplainStarsFeature3Title), LocaleController.getString(R.string.ExplainStarsFeature3Text)));
        r4.add(FeatureCell.Factory.of(R.drawable.menu_feature_paid, LocaleController.getString(R.string.ExplainStarsFeature4Title), LocaleController.getString(R.string.ExplainStarsFeature4Text)));
        r4.add(UItem.asSpace(AndroidUtilities.dp(68.0f)));
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.ExplainStarsTitle);
    }
}
