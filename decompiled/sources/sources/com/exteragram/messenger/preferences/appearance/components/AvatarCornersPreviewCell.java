package com.exteragram.messenger.preferences.appearance.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.preferences.components.AltSeekbar;
import com.exteragram.messenger.preferences.components.CustomPreferenceCell;
import com.exteragram.messenger.preferences.components.PreviewBackgroundDrawable;
import com.exteragram.messenger.preferences.components.PreviewColors;
import java.util.ArrayList;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.Cells.ProfileChannelCell;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LoadingDrawable;
import org.telegram.ui.Components.ScaleStateListAnimator;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes4.dex */
public class AvatarCornersPreviewCell extends FrameLayout implements CustomPreferenceCell {
    private Paint brightMockPaint;
    private AnimatedFloat channelLoadingAlpha;
    private long currentDialogId;
    private final Mode currentMode;
    private DialogCell dialogCell;
    private ProfileChannelCell.ChannelMessageFetcher fetcher;
    private int lastWidth;
    private boolean loadingChannel;
    private LoadingDrawable loadingDrawable;
    private boolean loadingMessages;
    private AnimatedFloat messagesLoadingAlpha;
    private Paint mockPaint;
    private final FrameLayout preview;
    private final AltSeekbar seekBar;
    private boolean set;

    /* renamed from: com.exteragram.messenger.preferences.appearance.components.AvatarCornersPreviewCell$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ AvatarCornersPreviewCell this$0;

        public AnonymousClass1(AvatarCornersPreviewCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r3) {
            super.dispatchDraw(r3);
            Mode r0 = AvatarCornersPreviewCell.m1664$$Nest$fgetcurrentMode(this.this$0);
            Mode r1 = Mode.REAL;
            AvatarCornersPreviewCell r2 = this.this$0;
            if (r0 != r1) goto L6;
            AvatarCornersPreviewCell.m1665$$Nest$mdrawLoading(r2, r3);
            return;
        L6:
            AvatarCornersPreviewCell.m1666$$Nest$mdrawMock(r2, r3);
        }
    }

    /* renamed from: com.exteragram.messenger.preferences.appearance.components.AvatarCornersPreviewCell$2, reason: invalid class name */
    public class AnonymousClass2 implements DialogCell.DialogCellDelegate {
        final /* synthetic */ AvatarCornersPreviewCell this$0;

        public AnonymousClass2(AvatarCornersPreviewCell r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
        public boolean canClickButtonInside() {
            return false;
        }

        @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
        public void onButtonClicked(DialogCell r1) {
        }

        @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
        public void onButtonLongPress(DialogCell r1) {
        }

        @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
        public void openHiddenStories() {
        }

        @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
        public void openStory(DialogCell r1, Runnable r2) {
        }

        @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
        public void showChatPreview(DialogCell r1) {
        }
    }

    public enum Mode extends Enum<Mode> {
        private static final /* synthetic */ Mode[] $VALUES = null;
        public static final Mode MOCK = null;
        public static final Mode REAL = null;

        private static /* synthetic */ Mode[] $values() {
            return new Mode[]{REAL, MOCK};
        }

        static {
            REAL = new Mode("REAL", 0);
            MOCK = new Mode("MOCK", 1);
            $VALUES = $values();
        }

        Mode(String r1, int r2) {
        }

        public static Mode valueOf(String r1) {
            return (Mode) Enum.valueOf(Mode.class, r1);
        }

        public static Mode[] values() {
            return (Mode[]) $VALUES.clone();
        }
    }

    public static /* synthetic */ void $r8$lambda$Fwb6bGsHdTxPqExWcBe_wqe__5Y(AvatarCornersPreviewCell r0, Long r1) {
        r0.lambda$initRealMode$2(r1);
    }

    /* renamed from: $r8$lambda$eeNtJrEwGFLk4YRlOs3dGfI4I-s, reason: not valid java name */
    public static /* synthetic */ void m1663$r8$lambda$eeNtJrEwGFLk4YRlOs3dGfI4Is(MessagesController r1, BaseFragment r2, View r3) {
        r1.openByUserName("exteraGram", r2, 1);
    }

    public static /* synthetic */ void $r8$lambda$r_fhIKKEX_RIUqUxSH0mcbGjqpU(AvatarCornersPreviewCell r0, long r1, boolean r3) {
        r0.lambda$setDialogId$3(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$xzsXXukpiCh5J3ODZoDCevgAVN8(AvatarCornersPreviewCell r0, BaseFragment r1, float r2) {
        r0.lambda$new$1(r1, r2);
    }

    /* renamed from: -$$Nest$fgetcurrentMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Mode m1664$$Nest$fgetcurrentMode(AvatarCornersPreviewCell r0) {
        return r0.currentMode;
    }

    /* renamed from: -$$Nest$mdrawLoading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1665$$Nest$mdrawLoading(AvatarCornersPreviewCell r0, Canvas r1) {
        r0.drawLoading(r1);
    }

    /* renamed from: -$$Nest$mdrawMock, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1666$$Nest$mdrawMock(AvatarCornersPreviewCell r0, Canvas r1) {
        r0.drawMock(r1);
    }

    public AvatarCornersPreviewCell(Context r14, BaseFragment r15, Theme.ResourcesProvider r16, int r17) {
        super(r14);
        this.dialogCell = null;
        this.set = false;
        Mode r8 = Mode.values()[r17];
        this.currentMode = r8;
        setWillNotDraw(false);
        setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        int r9 = UserConfig.selectedAccount;
        MessagesController r10 = MessagesController.getInstance(r9);
        AnonymousClass1 r11 = new AnonymousClass1(this, r14);
        this.preview = r11;
        Mode r12 = Mode.REAL;
        if (r8 != r12) goto L5;
        ScaleStateListAnimator.apply(r11, 0.03f, 1.5f);
        r11.setOnClickListener(new AvatarCornersPreviewCell$$ExternalSyntheticLambda0(r10, r15));
    L5:
        r11.setWillNotDraw(false);
        r11.setBackground(new PreviewBackgroundDrawable());
        AltSeekbar r2 = new AltSeekbar(r14, new AvatarCornersPreviewCell$$ExternalSyntheticLambda1(this, r15), 0, 28, LocaleController.getString(R.string.AvatarCorners), LocaleController.getString(R.string.AvatarCornersLeft), LocaleController.getString(R.string.AvatarCornersRight));
        this.seekBar = r2;
        r2.setProgress(ExteraConfig.getAvatarCorners());
        addView(r2, LayoutHelper.createFrame(-1, -2.0f));
        if (r8 != r12) goto L8;
        initRealMode(r14, r16, r9, r10);
        r11.addView(this.dialogCell, LayoutHelper.createFrame(-1, -2.0f, 17, 0.0f, 3.0f, 0.0f, 4.0f));
    L9:
        addView(r11, LayoutHelper.createFrame(-1, -2.0f, 49, 21.0f, 114.0f, 21.0f, 21.0f));
        return;
    L8:
        initMockMode();
        r11.setMinimumHeight(AndroidUtilities.dp(83.0f));
        goto L9
    }

    private void drawLoading(Canvas r14) {
        if (this.loadingDrawable == null) goto L17;
        float r0 = this.channelLoadingAlpha.set(this.loadingChannel);
        float r1 = this.messagesLoadingAlpha.set(this.loadingMessages);
        boolean r4 = true;
        if (r0 <= 0.0f) goto L8;
        this.loadingDrawable.setAlpha((int) (r0 * 255.0f));
        int r02 = AndroidUtilities.dp(56.0f);
        float r3 = this.dialogCell.getX() + AndroidUtilities.dp(this.dialogCell.avatarStart);
        float r9 = this.dialogCell.getY() + AndroidUtilities.dp(12.0f);
        this.loadingDrawable.setRadiiDp((int) ExteraConfig.getAvatarCorners());
        RectF r10 = AndroidUtilities.rectTmp;
        float r03 = r02;
        r10.set(r3, r9, r3 + r03, r03 + r9);
        this.loadingDrawable.setBounds(r10);
        this.loadingDrawable.draw(r14);
        this.loadingDrawable.setRadiiDp(4.0f);
        r10.set(this.dialogCell.getX() + AndroidUtilities.dp(this.dialogCell.messagePaddingStart + 6), this.dialogCell.getY() + AndroidUtilities.dp(16.0f), (this.dialogCell.getX() + AndroidUtilities.dp(this.dialogCell.messagePaddingStart + 6)) + (getWidth() * 0.4f), this.dialogCell.getY() + AndroidUtilities.dp(24.33f));
        this.loadingDrawable.setBounds(r10);
        this.loadingDrawable.draw(r14);
        boolean r04 = true;
    L10:
        if (r1 <= 0.0f) goto L12;
        this.loadingDrawable.setAlpha((int) (r1 * 255.0f));
        RectF r05 = AndroidUtilities.rectTmp;
        r05.set(this.dialogCell.getX() + AndroidUtilities.dp(this.dialogCell.messagePaddingStart + 6), this.dialogCell.getY() + AndroidUtilities.dp(38.0f), (this.dialogCell.getX() + AndroidUtilities.dp(this.dialogCell.messagePaddingStart + 6)) + (getWidth() * 0.5f), this.dialogCell.getY() + AndroidUtilities.dp(46.33f));
        this.loadingDrawable.setBounds(r05);
        this.loadingDrawable.draw(r14);
        r05.set(this.dialogCell.getX() + AndroidUtilities.dp(this.dialogCell.messagePaddingStart + 6), this.dialogCell.getY() + AndroidUtilities.dp(56.0f), (this.dialogCell.getX() + AndroidUtilities.dp(this.dialogCell.messagePaddingStart + 6)) + (getWidth() * 0.36f), this.dialogCell.getY() + AndroidUtilities.dp(64.33f));
        this.loadingDrawable.setBounds(r05);
        this.loadingDrawable.draw(r14);
        r05.set(((this.dialogCell.getX() + this.dialogCell.getWidth()) - AndroidUtilities.dp(16.0f)) - AndroidUtilities.dp(43.0f), this.dialogCell.getY() + AndroidUtilities.dp(12.0f), (this.dialogCell.getX() + this.dialogCell.getWidth()) - AndroidUtilities.dp(16.0f), this.dialogCell.getY() + AndroidUtilities.dp(20.33f));
        this.loadingDrawable.setBounds(r05);
        this.loadingDrawable.draw(r14);
    L13:
        if (r4 == false) goto L16;
        invalidate();
        return;
    L16:
        return;
    L12:
        r4 = r04;
        goto L13
    L8:
        r04 = false;
        goto L10
    }

    private void drawMock(Canvas r14) {
        float r1 = AndroidUtilities.dp(1.0f);
        float r2 = AndroidUtilities.dp(15.0f);
        float r3 = AndroidUtilities.dp(83.0f);
        int r5 = AndroidUtilities.dp(56.0f);
        float r6 = this.preview.getWidth();
        RectF r7 = AndroidUtilities.rectTmp;
        float r8 = AndroidUtilities.dp(12.0f) + r1;
        float r52 = r5;
        float r9 = r2 + r52;
        float r53 = r52 + r8;
        r7.set(r2, r8, r9, r53);
        float r0 = Math.max(0.0f, Math.min(1.0f, 1.0f - (ExteraConfig.getAvatarCorners() / 28.0f)));
        float r22 = AndroidUtilities.dpf2((2.0f * r0) + 7.0f);
        float r02 = AndroidUtilities.dpf2(r0 + 5.0f);
        float r92 = r9 - ExteraConfig.getOnlineDotOffset(AndroidUtilities.dpf2(8.0f), r22);
        float r54 = r53 - ExteraConfig.getOnlineDotOffset(AndroidUtilities.dpf2(7.5f), r22);
        r14.save();
        Path r82 = new Path();
        r82.addCircle(r92, r54, r22, Path.Direction.CCW);
        r14.clipPath(r82, Region.Op.DIFFERENCE);
        r14.drawRoundRect(r7, ExteraConfig.getAvatarCorners(56.0f), ExteraConfig.getAvatarCorners(56.0f), this.brightMockPaint);
        r14.restore();
        Theme.dialogs_onlineCirclePaint.setColor(Theme.getColor(Theme.key_chats_onlineCircle));
        r14.drawCircle(r92, r54, r02, Theme.dialogs_onlineCirclePaint);
        float r03 = AndroidUtilities.dp(4.0f);
        r7.set(AndroidUtilities.dp(6.0f) + r3, AndroidUtilities.dp(16.0f) + r1, (AndroidUtilities.dp(6.0f) + r3) + (0.4f * r6), AndroidUtilities.dp(24.33f) + r1);
        r14.drawRoundRect(r7, r03, r03, this.brightMockPaint);
        r7.set(AndroidUtilities.dp(6.0f) + r3, AndroidUtilities.dp(38.0f) + r1, (AndroidUtilities.dp(6.0f) + r3) + (0.5f * r6), AndroidUtilities.dp(46.33f) + r1);
        r14.drawRoundRect(r7, r03, r03, this.mockPaint);
        r7.set(AndroidUtilities.dp(6.0f) + r3, AndroidUtilities.dp(56.0f) + r1, (r3 + AndroidUtilities.dp(6.0f)) + (0.36f * r6), AndroidUtilities.dp(64.33f) + r1);
        r14.drawRoundRect(r7, r03, r03, this.mockPaint);
        r7.set((r6 - AndroidUtilities.dp(16.0f)) - AndroidUtilities.dp(43.0f), AndroidUtilities.dp(16.0f) + r1, r6 - AndroidUtilities.dp(16.0f), r1 + AndroidUtilities.dp(24.33f));
        r14.drawRoundRect(r7, r03, r03, this.mockPaint);
    }

    private void initMockMode() {
        Paint r0 = new Paint(1);
        this.mockPaint = r0;
        r0.setColor(PreviewColors.getMockColor(false));
        Paint r02 = new Paint(1);
        this.brightMockPaint = r02;
        r02.setColor(PreviewColors.getMockColor(true));
    }

    private void initRealMode(Context r10, Theme.ResourcesProvider r11, int r12, MessagesController r13) {
        this.fetcher = new ProfileChannelCell.ChannelMessageFetcher(r12);
        CubicBezierInterpolator r1 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.channelLoadingAlpha = new AnimatedFloat(this, 320, r1);
        this.messagesLoadingAlpha = new AnimatedFloat(this, 320, r1);
        LoadingDrawable r0 = new LoadingDrawable();
        this.loadingDrawable = r0;
        int r14 = Theme.key_listSelector;
        r0.setColors(Theme.multAlpha(Theme.getColor(r14), 1.3f), Theme.multAlpha(Theme.getColor(r14), 0.85f));
        this.loadingDrawable.setRadiiDp(8.0f);
        DialogCell r2 = new DialogCell(null, r10, false, true, r12, r11);
        this.dialogCell = r2;
        r2.isForChannelSubscriberCell = true;
        r2.setDialogCellDelegate(new AnonymousClass2(this));
        DialogCell r112 = this.dialogCell;
        r112.avatarStart = 15;
        r112.messagePaddingStart = 83;
        if (r13.getChat(1571726392L) == null) goto L6;
        setDialogId(1571726392);
        return;
    L6:
        this.loadingChannel = true;
        this.loadingMessages = true;
        r13.getUserNameResolver().resolve("exteraGram", new AvatarCornersPreviewCell$$ExternalSyntheticLambda3(this));
    }

    private /* synthetic */ void lambda$initRealMode$2(Long r3) {
        this.loadingChannel = false;
        if (r3 == null) goto L5;
        setDialogId(-r3.longValue());
    L5:
        invalidate();
    }

    private /* synthetic */ void lambda$new$1(BaseFragment r1, float r2) {
        ExteraConfig.setAvatarCorners(r2);
        invalidate();
        this.preview.invalidate();
        DialogCell r0 = this.dialogCell;
        if (r0 == null) goto L5;
        r0.update(0);
    L5:
        r1.getParentLayout().rebuildFragments(0);
    }

    private /* synthetic */ void lambda$setDialogId$3(long r11, boolean r13) {
        if (this.dialogCell != null) goto L5;
    L16:
        if (r13 == true) goto L18;
        this.messagesLoadingAlpha.set(this.loadingMessages, true);
    L18:
        invalidate();
        this.set = true;
        return;
    L5:
        if (this.currentDialogId != r11) goto L16;
        ArrayList<MessageObject> r0 = this.fetcher.messageObjects;
        if (r0 != null) goto L9;
    L12:
        if (r13 == true) goto L14;
        this.dialogCell.setDialog(-r11, null, 0, false, true);
    L14:
        this.loadingMessages = true;
    L15:
        this.dialogCell.invalidate();
        goto L16
    L9:
        if (r0.isEmpty() == true) goto L12;
        ArrayList<MessageObject> r02 = this.fetcher.messageObjects;
        MessageObject r5 = r02.get(r02.size() - 1);
        this.dialogCell.setDialog(-r11, r5, this.fetcher.messageObjects, r5.messageOwner.date, false, !r13);
        this.loadingMessages = false;
        goto L15
    }

    @Override // com.exteragram.messenger.preferences.components.CustomPreferenceCell
    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof AvatarCornersPreviewCell) == true) goto L8;
        return false;
    L8:
        return Objects.equals(this.seekBar, ((AvatarCornersPreviewCell) r2).seekBar);
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        this.preview.invalidate();
        this.seekBar.invalidate();
        DialogCell r0 = this.dialogCell;
        if (r0 == null) goto L5;
        r0.invalidate();
    L5:
        this.lastWidth = -1;
    }

    @Override // android.view.View
    public void onDraw(Canvas r8) {
        super.onDraw(r8);
        r8.drawLine(0.0f, getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight() - 1, Theme.dividerPaint);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        int r32 = View.MeasureSpec.getSize(r2);
        super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(0, 0));
        setMeasuredDimension(r32, getMeasuredHeight());
        if (this.lastWidth == r32) goto L6;
        this.lastWidth = r32;
        return;
    }

    public void setDialogId(long r5) {
        if (this.currentMode == Mode.REAL) goto L5;
        return;
    L5:
        boolean r0 = !this.set;
        this.currentDialogId = r5;
        this.messagesLoadingAlpha.set(1.0f, true);
        this.fetcher.fetch(r5, 0);
        this.fetcher.subscribe(new AvatarCornersPreviewCell$$ExternalSyntheticLambda2(this, r5, r0));
    }

    public void updateSliderStyle() {
        this.seekBar.updateStyle();
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (this.loadingDrawable != r2) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
