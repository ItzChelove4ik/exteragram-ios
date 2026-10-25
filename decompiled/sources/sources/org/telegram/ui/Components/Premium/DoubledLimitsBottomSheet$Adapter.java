package org.telegram.ui.Components.Premium;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.FixedHeightEmptyCell;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.PremiumGradient;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public class DoubledLimitsBottomSheet$Adapter extends RecyclerListView.SelectionAdapter {
    ViewGroup containerView;
    boolean drawHeader;
    PremiumGradient.PremiumGradientTools gradientTools;
    int headerRow;
    int lastViewRow;
    final ArrayList<DoubledLimitsBottomSheet$Limit> limits;
    int limitsStartEnd;
    int limitsStartRow;
    private final Theme.ResourcesProvider resourcesProvider;
    int rowCount;
    private int totalGradientHeight;

    /* renamed from: org.telegram.ui.Components.Premium.DoubledLimitsBottomSheet$Adapter$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ DoubledLimitsBottomSheet$Adapter this$0;

        public AnonymousClass1(DoubledLimitsBottomSheet$Adapter r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(86.0f), TLObject.FLAG_30));
        }
    }

    public DoubledLimitsBottomSheet$Adapter(int r14, boolean r15, Theme.ResourcesProvider r16) {
        ArrayList<DoubledLimitsBottomSheet$Limit> r0 = new ArrayList();
        this.limits = r0;
        this.drawHeader = r15;
        this.resourcesProvider = r16;
        PremiumGradient.PremiumGradientTools r1 = new PremiumGradient.PremiumGradientTools(Theme.key_premiumGradient1, Theme.key_premiumGradient2, Theme.key_premiumGradient3, Theme.key_premiumGradient4, -1, r16);
        this.gradientTools = r1;
        r1.x1 = 0.0f;
        r1.y1 = 0.0f;
        r1.x2 = 0.0f;
        r1.y2 = 1.0f;
        MessagesController r142 = MessagesController.getInstance(r14);
        r0.add(new DoubledLimitsBottomSheet$Limit(LocaleController.getString(R.string.GroupsAndChannelsLimitTitle), LocaleController.formatString(R.string.GroupsAndChannelsLimitSubtitle, new Object[]{Integer.valueOf(r142.channelsLimitPremium)}), r142.channelsLimitDefault, r142.channelsLimitPremium, null));
        r0.add(new DoubledLimitsBottomSheet$Limit(LocaleController.getString(R.string.PinChatsLimitTitle), LocaleController.formatString(R.string.PinChatsLimitSubtitle, new Object[]{Integer.valueOf(r142.dialogFiltersPinnedLimitPremium)}), r142.dialogFiltersPinnedLimitDefault, r142.dialogFiltersPinnedLimitPremium, null));
        r0.add(new DoubledLimitsBottomSheet$Limit(LocaleController.getString(R.string.PublicLinksLimitTitle), LocaleController.formatString(R.string.PublicLinksLimitSubtitle, new Object[]{Integer.valueOf(r142.publicLinksLimitPremium)}), r142.publicLinksLimitDefault, r142.publicLinksLimitPremium, null));
        r0.add(new DoubledLimitsBottomSheet$Limit(LocaleController.getString(R.string.SavedGifsLimitTitle), LocaleController.formatString(R.string.SavedGifsLimitSubtitle, new Object[]{Integer.valueOf(r142.savedGifsLimitPremium)}), r142.savedGifsLimitDefault, r142.savedGifsLimitPremium, null));
        r0.add(new DoubledLimitsBottomSheet$Limit(LocaleController.getString(R.string.FavoriteStickersLimitTitle), LocaleController.formatString(R.string.FavoriteStickersLimitSubtitle, new Object[]{Integer.valueOf(r142.stickersFavedLimitPremium)}), r142.stickersFavedLimitDefault, r142.stickersFavedLimitPremium, null));
        r0.add(new DoubledLimitsBottomSheet$Limit(LocaleController.getString(R.string.BioLimitTitle), LocaleController.formatString(R.string.BioLimitSubtitle, new Object[]{Integer.valueOf(r142.stickersFavedLimitPremium)}), r142.aboutLengthLimitDefault, r142.aboutLengthLimitPremium, null));
        r0.add(new DoubledLimitsBottomSheet$Limit(LocaleController.getString(R.string.CaptionsLimitTitle), LocaleController.formatString(R.string.CaptionsLimitSubtitle, new Object[]{Integer.valueOf(r142.stickersFavedLimitPremium)}), r142.captionLengthLimitDefault, r142.captionLengthLimitPremium, null));
        r0.add(new DoubledLimitsBottomSheet$Limit(LocaleController.getString(R.string.FoldersLimitTitle), LocaleController.formatString(R.string.FoldersLimitSubtitle, new Object[]{Integer.valueOf(r142.dialogFiltersLimitPremium)}), r142.dialogFiltersLimitDefault, r142.dialogFiltersLimitPremium, null));
        r0.add(new DoubledLimitsBottomSheet$Limit(LocaleController.getString(R.string.ChatPerFolderLimitTitle), LocaleController.formatString(R.string.ChatPerFolderLimitSubtitle, new Object[]{Integer.valueOf(r142.dialogFiltersChatsLimitPremium)}), r142.dialogFiltersChatsLimitDefault, r142.dialogFiltersChatsLimitPremium, null));
        r0.add(new DoubledLimitsBottomSheet$Limit(LocaleController.getString(R.string.ConnectedAccountsLimitTitle), LocaleController.formatString(R.string.ConnectedAccountsLimitSubtitle, new Object[]{4}), 8, 16, null));
        r0.add(new DoubledLimitsBottomSheet$Limit(LocaleController.getString(R.string.SimilarChannelsLimitTitle), LocaleController.formatString(R.string.SimilarChannelsLimitSubtitle, new Object[]{Integer.valueOf(r142.recommendedChannelsLimitPremium)}), r142.recommendedChannelsLimitDefault, r142.recommendedChannelsLimitPremium, null));
        this.rowCount = 1;
        this.headerRow = 0;
        this.limitsStartRow = 1;
        int r143 = 1 + r0.size();
        this.rowCount = r143;
        this.limitsStartEnd = r143;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.rowCount;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int r2) {
        if (r2 != this.headerRow) goto L7;
        return 1;
    L7:
        if (r2 != this.lastViewRow) goto L10;
        return 2;
    L10:
        return 0;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
    public boolean isEnabled(RecyclerView.ViewHolder r1) {
        return false;
    }

    public void measureGradient(Context r5, int r6, int r7) {
        DoubledLimitsBottomSheet$LimitCell r0 = new DoubledLimitsBottomSheet$LimitCell(r5, this.resourcesProvider);
        int r52 = 0;
        int r1 = 0;
    L4:
        if (r52 >= this.limits.size()) goto L6;
        r0.setData(this.limits.get(r52));
        r0.measure(View.MeasureSpec.makeMeasureSpec(r6, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r7, Integer.MIN_VALUE));
        this.limits.get(r52).yOffset = r1;
        r1 = r1 + r0.getMeasuredHeight();
        r52 = r52 + 1;
        goto L4
    L6:
        this.totalGradientHeight = r1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder r4, int r5) {
        if (r4.getItemViewType() != 0) goto L6;
        DoubledLimitsBottomSheet$LimitCell r42 = (DoubledLimitsBottomSheet$LimitCell) r4.itemView;
        r42.setData(this.limits.get(r5 - this.limitsStartRow));
        LimitPreviewView r0 = r42.previewView;
        r0.gradientYOffset = this.limits.get(r5 - this.limitsStartRow).yOffset;
        LimitPreviewView r43 = r42.previewView;
        r43.gradientTotalHeight = this.totalGradientHeight;
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v3, types: [android.view.ViewGroup, org.telegram.ui.Components.Premium.DoubledLimitsBottomSheet$Adapter$1] */
    /* JADX WARN: Type inference failed for: r15v6, types: [org.telegram.ui.Components.Premium.DoubledLimitsBottomSheet$LimitCell] */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r14, int r15) {
        Context r142 = r14.getContext();
        if (r15 == 1) goto L9;
        if (r15 == 2) goto L7;
        ?? r152 = new DoubledLimitsBottomSheet$LimitCell(r142, this.resourcesProvider);
        r152.previewView.setParentViewForGradien(this.containerView);
        r152.previewView.setStaticGradinet(this.gradientTools);
        FixedHeightEmptyCell r153 = r152;
    L12:
        r153.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
        return new RecyclerListView.Holder(r153);
    L7:
        r153 = new FixedHeightEmptyCell(r142, 16);
        goto L12
    L9:
        if (this.drawHeader == false) goto L11;
        ?? r154 = new AnonymousClass1(this, r142);
        LinearLayout r3 = new LinearLayout(r142);
        r3.setOrientation(0);
        ImageView r4 = new ImageView(r142);
        r4.setImageDrawable(PremiumGradient.getInstance().createGradientDrawable(ContextCompat.getDrawable(r142, R.drawable.other_2x_large)));
        r3.addView(r4, LayoutHelper.createFrame(40, 28.0f, 16, 0.0f, 0.0f, 8.0f, 0.0f));
        TextView r42 = new TextView(r142);
        r42.setText(LocaleController.getString(R.string.DoubledLimits));
        r42.setGravity(17);
        r42.setTextSize(1, 20.0f);
        r42.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
        r42.setTypeface(AndroidUtilities.bold());
        r3.addView(r42, LayoutHelper.createFrame(-2, -2, 16));
        r154.addView(r3, LayoutHelper.createFrame(-2, -2, 17));
        r153 = r154;
        goto L12
    L11:
        r153 = new FixedHeightEmptyCell(r142, 64);
        goto L12
    }
}
