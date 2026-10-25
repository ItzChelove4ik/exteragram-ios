package org.telegram.ui.community.cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RecordingCanvas;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.utils.RenderNodeEffects;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceRenderNode;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class CommunityPendingRequestCell extends FrameLayout implements Theme.Colorable {
    private final ButtonWithCounterView addButton;
    public final BackupImageView avatarView;
    private BlurredBackgroundDrawable blurredBackgroundDrawable;
    private final int currentAccount;
    private final ButtonWithCounterView declineButton;
    ClickDelegate delegate;
    long groupDialogId;
    public final TextView hiddenLabelView;
    public final TextView membersCountView;
    private boolean needDivider;
    public final BackupImageView requesterAvatarView;
    private final Theme.ResourcesProvider resourcesProvider;
    private final BlurredBackgroundSourceRenderNode sourceRenderNode;
    private final ColoredImageSpan span;
    public final TextView subtitleView;
    public final TextView titleView;
    long userDialogId;

    public interface ClickDelegate {
        void onClickApprove(long r1);

        void onClickDecline(long r1);

        void onClickGroupOwner(long r1);
    }

    public static class Data {
        public final long dialogToAdd;
        public final boolean isHidden;
        public final TLRPC.User requestFromUser;

        private Data(long r1, TLRPC.User r3, boolean r4) {
            this.dialogToAdd = r1;
            this.requestFromUser = r3;
            this.isHidden = r4;
        }

        public /* synthetic */ Data(long r1, TLRPC.User r3, boolean r4, CommunityPendingRequestCellIA r5) {
            this(r1, r3, r4);
        }
    }

    public static class Factory extends UItem.UItemFactory<CommunityPendingRequestCell> {
        static {
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        public static UItem asPendingRequest(long r7, TLRPC.User r9, boolean r10, ClickDelegate r11, boolean r12) {
            UItem r0 = UItem.ofFactory(Factory.class);
            r0.object = new Data(r7, r9, r10, null);
            r0.object2 = r11;
            r0.hideDivider = !r12;
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r8, UItem r9, boolean r10, UniversalAdapter r11, UniversalRecyclerView r12) {
            Data r7 = (Data) r9.object;
            CommunityPendingRequestCell.m21569$$Nest$mset((CommunityPendingRequestCell) r8, r7.dialogToAdd, r7.requestFromUser, (ClickDelegate) r9.object2, r7.isHidden, !r9.hideDivider);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public CommunityPendingRequestCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            CommunityPendingRequestCell r0 = new CommunityPendingRequestCell(r1, r5, r3);
            r0.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            r0.setClickable(false);
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean equals(UItem r5, UItem r6) {
            Data r4 = (Data) r5.object;
            Data r52 = (Data) r6.object;
            if (r4.dialogToAdd == r52.dialogToAdd) goto L5;
            return false;
        L5:
            if (DialogObject.getDialogId(r4.requestFromUser) != DialogObject.getDialogId(r52.requestFromUser)) goto L10;
            return true;
        L10:
            return false;
        }
    }

    /* renamed from: $r8$lambda$-iKAnBjVDjZkfG3zWlAG-UhGTF0, reason: not valid java name */
    public static /* synthetic */ void m21567$r8$lambda$iKAnBjVDjZkfG3zWlAGUhGTF0() {
    }

    public static /* synthetic */ void $r8$lambda$8uDyjIQpYLQyYx4h6uL1AIIfwfY(CommunityPendingRequestCell r0, View r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$V5YMZEJlMW3S6QhJAnbdFsr9YEQ(CommunityPendingRequestCell r0, View r1) {
        r0.lambda$new$3(r1);
    }

    /* renamed from: $r8$lambda$t2Vk6CpEVCv0FMS-Cl4ujGTsQwE, reason: not valid java name */
    public static /* synthetic */ void m21568$r8$lambda$t2Vk6CpEVCv0FMSCl4ujGTsQwE(CommunityPendingRequestCell r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$vYhwy5Tktn7DalPzD0OrpQCoDWg(CommunityPendingRequestCell r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: -$$Nest$mset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21569$$Nest$mset(CommunityPendingRequestCell r0, long r1, TLRPC.User r3, ClickDelegate r4, boolean r5, boolean r6) {
        r0.set(r1, r3, r4, r5, r6);
    }

    public CommunityPendingRequestCell(Context r12, Theme.ResourcesProvider r13, int r14) {
        super(r12);
        this.resourcesProvider = r13;
        this.currentAccount = r14;
        if (Build.VERSION.SDK_INT < 31) goto L5;
        BlurredBackgroundSourceRenderNode r142 = new BlurredBackgroundSourceRenderNode(null);
        this.sourceRenderNode = r142;
        r142.setBlur(AndroidUtilities.dp(7.0f), RenderNodeEffects.createSaturationXRenderEffect(1.125f));
        r142.noClip();
        BlurredBackgroundDrawableViewFactory r0 = new BlurredBackgroundDrawableViewFactory(r142);
    L6:
        FrameLayout r143 = new FrameLayout(r12);
        BackupImageView r2 = new BackupImageView(r12);
        this.avatarView = r2;
        r2.setRoundRadius(AndroidUtilities.dp(52.0f) / 2);
        addView(r2, LayoutHelper.createFrame(52, 52.0f, 51, 11.0f, 9.0f, 0.0f, 0.0f));
        ColoredImageSpan r22 = new ColoredImageSpan(R.drawable.mini_user_channels_10);
        this.span = r22;
        r22.setTranslateX(AndroidUtilities.dp(2.0f));
        TextView r23 = new TextView(r12);
        this.membersCountView = r23;
        r23.setTypeface(AndroidUtilities.bold());
        r23.setVisibility(8);
        r23.setTextSize(1, 9.33f);
        r23.setTextColor(-1);
        r23.setGravity(17);
        r23.setPadding(AndroidUtilities.dp(1.0f), 0, AndroidUtilities.dp(5.0f), 0);
        r143.addView(r23, LayoutHelper.createFrame(-2, -1, 81));
        addView(r143, LayoutHelper.createLinear(52, 14.33f, 48, 11, 48, 0, 0));
        BlurredBackgroundDrawable r144 = r0.create(r23).setColorProvider(BlurredBackgroundProviderImpl.counterMini(r13)).setRadius(AndroidUtilities.dp(7.0f));
        this.blurredBackgroundDrawable = r144;
        r23.setBackground(r144);
        LinearLayout r145 = new LinearLayout(r12);
        r145.setOrientation(1);
        r145.setClipChildren(false);
        TextView r02 = new TextView(r12);
        this.titleView = r02;
        r02.setTypeface(AndroidUtilities.bold());
        r02.setTextSize(1, 16.0f);
        r02.setSingleLine(true);
        TextUtils.TruncateAt r1 = TextUtils.TruncateAt.END;
        r02.setEllipsize(r1);
        r145.addView(r02, LayoutHelper.createLinear(-1, -2, 0.0f, 10.0f, 0.0f, 1.33f));
        BackupImageView r03 = new BackupImageView(r12);
        this.requesterAvatarView = r03;
        r03.setRoundRadius(AndroidUtilities.dp(8.0f));
        r03.setOnClickListener(new CommunityPendingRequestCell$$ExternalSyntheticLambda0(this));
        addView(r03, LayoutHelper.createFrame(16, 16.0f, 51, 75.0f, 35.0f, 0.0f, 0.0f));
        TextView r04 = new TextView(r12);
        this.subtitleView = r04;
        r04.setTextSize(1, 13.0f);
        r04.setSingleLine(true);
        r04.setEllipsize(r1);
        r04.setOnClickListener(new CommunityPendingRequestCell$$ExternalSyntheticLambda1(this));
        r145.addView(r04, LayoutHelper.createLinear(-1, -2, 20.0f, 0.0f, 0.0f, 1.33f));
        TextView r05 = new TextView(r12);
        this.hiddenLabelView = r05;
        r05.setTextSize(1, 13.0f);
        r05.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(12.0f), Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText6, r13), 0.14f)));
        r05.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(1.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(1.66f));
        r05.setSingleLine(true);
        SpannableStringBuilder r15 = new SpannableStringBuilder();
        r15.append("* ");
        r15.setSpan(new ColoredImageSpan(R.drawable.mini_ephemeral_hidden_14), 0, 1, 33);
        r15.append(LocaleController.getString(R.string.CommunityPendingRequestOnlyVisibleToMembers));
        r05.setText(r15);
        r05.setVisibility(8);
        r145.addView(r05, LayoutHelper.createLinear(-2, -2, 0.0f, 7.0f, 0.0f, 1.33f));
        LinearLayout r06 = new LinearLayout(r12);
        r06.setOrientation(0);
        r06.setClipChildren(false);
        ButtonWithCounterView r16 = new ButtonWithCounterView(r12, r13);
        this.declineButton = r16;
        r16.setUseWrapContent(true);
        r16.setPadding(AndroidUtilities.dp(15.0f), 0, AndroidUtilities.dp(15.0f), 0);
        r16.setRound();
        r16.setNeutral();
        r16.setColor(Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r13), 0.14f));
        r16.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        r16.setText(LocaleController.getString(R.string.Decline), false);
        r16.setOnClickListener(new CommunityPendingRequestCell$$ExternalSyntheticLambda2(this));
        r06.addView(r16, LayoutHelper.createLinear(-2, 30, 0.0f, 16, 0, 0, 4, 0));
        ButtonWithCounterView r17 = new ButtonWithCounterView(r12, r13);
        this.addButton = r17;
        r17.setUseWrapContent(true);
        r17.setPadding(AndroidUtilities.dp(15.0f), 0, AndroidUtilities.dp(15.0f), 0);
        r17.setRound();
        r17.setText(LocaleController.getString(R.string.Add), false);
        r17.setOnClickListener(new CommunityPendingRequestCell$$ExternalSyntheticLambda3(this));
        r06.addView(r17, LayoutHelper.createLinear(-2, 30, 0.0f, 16, 4, 0, 0, 0));
        r145.addView(r06, LayoutHelper.createLinear(-1, -2, 0, 0, 10, 0, 0));
        addView(r145, LayoutHelper.createFrame(-1, -2.0f, 48, 75.0f, 0.0f, 0.0f, 13.0f));
        updateColors();
        return;
    L5:
        this.sourceRenderNode = null;
        BlurredBackgroundSourceColor r146 = new BlurredBackgroundSourceColor();
        r146.setColor(-16777216);
        r0 = new BlurredBackgroundDrawableViewFactory(r146);
        goto L6
    }

    private /* synthetic */ void lambda$new$0(View r3) {
        ClickDelegate r32 = this.delegate;
        if (r32 == null) goto L6;
        r32.onClickGroupOwner(this.userDialogId);
        return;
    }

    private /* synthetic */ void lambda$new$1(View r3) {
        ClickDelegate r32 = this.delegate;
        if (r32 == null) goto L6;
        r32.onClickGroupOwner(this.userDialogId);
        return;
    }

    private /* synthetic */ void lambda$new$2(View r3) {
        ClickDelegate r32 = this.delegate;
        if (r32 == null) goto L6;
        r32.onClickDecline(this.groupDialogId);
        return;
    }

    private /* synthetic */ void lambda$new$3(View r3) {
        ClickDelegate r32 = this.delegate;
        if (r32 == null) goto L6;
        r32.onClickApprove(this.groupDialogId);
        return;
    }

    private void set(long r6, TLRPC.User r8, ClickDelegate r9, boolean r10, boolean r11) {
        this.delegate = r9;
        this.groupDialogId = r6;
        this.userDialogId = r8.id;
        TLRPC.Chat r92 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r6));
        TLRPC.User r0 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r6));
        this.titleView.setText(DialogObject.getName(r6));
        TextView r62 = this.subtitleView;
        if (r0 == null) goto L6;
        int r7 = R.string.CommunityPendingRequestSuggestedBot;
    L9:
        r62.setText(AndroidUtilities.replaceSingleLink(LocaleController.formatString(r7, new Object[]{DialogObject.getShortName(r8)}), Theme.getColor(Theme.key_telegram_color_text), new CommunityPendingRequestCell$$ExternalSyntheticLambda4()));
        if (r0 == null) goto L12;
        this.membersCountView.setVisibility(8);
    L17:
        TextView r1 = this.hiddenLabelView;
        if (r10 == false) goto L20;
        r1.setVisibility(0);
    L21:
        this.needDivider = r11;
        BackupImageView r63 = this.avatarView;
        if (r0 == null) goto L24;
        r63.setForUserOrChat(r0, new AvatarDrawable(r0));
    L25:
        this.requesterAvatarView.setForUserOrChat(r8, new AvatarDrawable(r8));
        return;
    L24:
        r63.setForUserOrChat(r92, new AvatarDrawable(r92));
        goto L25
    L20:
        r1.setVisibility(8);
        goto L21
    L12:
        if (r92 != null) goto L14;
    L16:
        this.membersCountView.setVisibility(8);
        goto L17
    L14:
        if (r92.participants_count <= 0) goto L16;
        SpannableStringBuilder r12 = new SpannableStringBuilder("* ");
        r12.setSpan(this.span, 0, 1, 33);
        r12.append(LocaleController.formatNumberWithMillion(r92.participants_count, ','));
        this.membersCountView.setText(r12);
        this.membersCountView.setVisibility(0);
        goto L17
    L6:
        if (ChatObject.isChannelAndNotMegaGroup(r92) == false) goto L8;
        r7 = R.string.CommunityPendingRequestSuggestedChannel;
        goto L9
    L8:
        r7 = R.string.CommunityPendingRequestSuggestedGroup;
        goto L9
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r8) {
        if (this.needDivider == false) goto L5;
        Canvas r1 = r8;
        r1.drawLine(AndroidUtilities.dp(76.0f), getHeight() - 1, getMeasuredWidth(), getHeight() - 1, Theme.dividerPaint);
    L6:
        super.dispatchDraw(r1);
        return;
    L5:
        r1 = r8;
        goto L6
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r5, View r6, long r7) {
        if (Build.VERSION.SDK_INT < 31) goto L10;
        if (this.sourceRenderNode == null) goto L10;
        if (r6 != this.avatarView) goto L10;
        int r0 = AndroidUtilities.dp(9.0f);
        int r1 = this.avatarView.getLeft() - r0;
        int r2 = this.avatarView.getTop() - r0;
        int r3 = AndroidUtilities.dp(52.0f) + (r0 * 2);
        RecordingCanvas r02 = this.sourceRenderNode.beginRecording(r3, r3);
        r02.translate(-r1, -r2);
        r02.drawColor(Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider));
        r02.save();
        float r12 = r3 / 2.0f;
        r02.scale(1.125f, 1.125f, r12, r12);
        super.drawChild(r02, r6, r7);
        r02.restore();
        r02.drawColor(536870912);
        this.sourceRenderNode.endRecording();
    L10:
        return super.drawChild(r5, r6, r7);
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        this.blurredBackgroundDrawable.setSourceOffset(this.membersCountView.getLeft() + AndroidUtilities.dp(9.0f), AndroidUtilities.dp(48.0f));
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        this.titleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
        TextView r0 = this.subtitleView;
        int r1 = Theme.key_windowBackgroundWhiteGrayText2;
        r0.setTextColor(Theme.getColor(r1, this.resourcesProvider));
        this.hiddenLabelView.setTextColor(Theme.getColor(r1, this.resourcesProvider));
    }
}
