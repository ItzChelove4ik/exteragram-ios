package org.telegram.ui.Stories.recorder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.text.SpannableString;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Stories.PeerStoriesView;
import org.telegram.ui.Stories.StoryCaptionView;

/* loaded from: classes7.dex */
public class PreviewHighlightView extends FrameLayout {
    private final FrameLayout bottom;
    private int currentAccount;
    private boolean shownBottom;
    private boolean shownTop;
    private int storiesCount;
    private final StoryCaptionView storyCaptionView;
    private final FrameLayout top;

    /* renamed from: org.telegram.ui.Stories.recorder.PreviewHighlightView$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        private Paint barPaint;
        private RectF rectF;
        final /* synthetic */ PreviewHighlightView this$0;

        public AnonymousClass1(PreviewHighlightView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.rectF = new RectF();
            this.barPaint = new Paint(1);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r9) {
            super.dispatchDraw(r9);
            this.barPaint.setColor(-1);
            float r0 = ((getWidth() - (AndroidUtilities.dpf2(5.0f) * 2.0f)) - AndroidUtilities.dpf2((PreviewHighlightView.m20148$$Nest$fgetstoriesCount(this.this$0) - 1) * 2)) / PreviewHighlightView.m20148$$Nest$fgetstoriesCount(this.this$0);
            float r1 = AndroidUtilities.dpf2(5.0f);
            int r2 = 0;
        L4:
            if (r2 >= PreviewHighlightView.m20148$$Nest$fgetstoriesCount(this.this$0)) goto L10;
            this.rectF.set(r1, AndroidUtilities.dpf2(8.0f), r1 + r0, AndroidUtilities.dpf2(10.0f));
            Paint r4 = this.barPaint;
            if (r2 >= (PreviewHighlightView.m20148$$Nest$fgetstoriesCount(this.this$0) - 1)) goto L8;
            int r5 = 255;
        L9:
            r4.setAlpha(r5);
            r9.drawRoundRect(this.rectF, AndroidUtilities.dpf2(1.0f), AndroidUtilities.dpf2(1.0f), this.barPaint);
            r1 = r1 + (AndroidUtilities.dpf2(2.0f) + r0);
            r2 = r2 + 1;
            goto L4
        L8:
            r5 = 133;
            goto L9
        }
    }

    /* renamed from: -$$Nest$fgetstoriesCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20148$$Nest$fgetstoriesCount(PreviewHighlightView r0) {
        return r0.storiesCount;
    }

    public PreviewHighlightView(Context r18, int r19, Theme.ResourcesProvider r20) {
        super(r18);
        this.storiesCount = 1;
        this.shownTop = false;
        this.shownBottom = false;
        this.currentAccount = r19;
        TLRPC.User r5 = UserConfig.getInstance(r19).getCurrentUser();
        AnonymousClass1 r6 = new AnonymousClass1(this, getContext());
        this.top = r6;
        PeerStoriesView.PeerHeaderView r7 = new PeerStoriesView.PeerHeaderView(getContext(), null);
        r7.backupImageView.getAvatarDrawable().setInfo(r19, r5);
        BackupImageView r2 = r7.backupImageView;
        r2.setForUserOrChat(r5, r2.getAvatarDrawable());
        r7.titleView.setText(Emoji.replaceEmoji(UserObject.getUserName(r5), r7.titleView.getPaint().getFontMetricsInt(), false));
        r7.setSubtitle(LocaleController.getString(R.string.RightNow), false);
        r6.addView(r7, LayoutHelper.createFrame(-1, -2.0f, 55, 0.0f, 17.0f, 0.0f, 0.0f));
        ImageView r22 = new ImageView(r18);
        r22.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_close_white).mutate());
        r22.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        r6.addView(r22, LayoutHelper.createFrame(40, 40.0f, 53, 12.0f, 15.0f, 12.0f, 0.0f));
        addView(r6, LayoutHelper.createFrame(-1, -2.0f));
        FrameLayout r23 = new FrameLayout(getContext());
        this.bottom = r23;
        StoryCaptionView r72 = new StoryCaptionView(getContext(), r20);
        this.storyCaptionView = r72;
        r72.disableTouches = true;
        r72.setTranslationY(AndroidUtilities.dp(8.0f));
        r23.addView(r72, LayoutHelper.createFrame(-1, -1.0f, 87, 0.0f, 0.0f, 0.0f, 64.0f));
        ImageView r4 = new ImageView(r18);
        r4.setImageResource(R.drawable.msg_share);
        PorterDuff.Mode r8 = PorterDuff.Mode.MULTIPLY;
        r4.setColorFilter(new PorterDuffColorFilter(-1, r8));
        r23.addView(r4, LayoutHelper.createFrame(28, 28.0f, 85, 0.0f, 0.0f, 12.0f, 16.0f));
        FrameLayout r42 = new FrameLayout(r18);
        r42.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(22.0f), ColorUtils.setAlphaComponent(-16777216, 122)));
        TextView r73 = new TextView(r18);
        r73.setTextSize(1, 18.0f);
        r73.setTextColor(1694498815);
        r73.setText(LocaleController.getString(R.string.ReplyPrivately));
        r42.addView(r73, LayoutHelper.createFrame(-2, -2.0f, 19, 24.0f, 0.0f, 24.0f, 0.0f));
        ImageView r3 = new ImageView(r18);
        r3.setImageResource(R.drawable.input_attach);
        r3.setColorFilter(new PorterDuffColorFilter(-1, r8));
        r42.addView(r3, LayoutHelper.createFrame(28, 28.0f, 21, 0.0f, 0.0f, 9.0f, 0.0f));
        r23.addView(r42, LayoutHelper.createFrame(-1, 44.0f, 87, 9.0f, 8.0f, 55.0f, 8.0f));
        addView(r23, LayoutHelper.createFrame(-1, -1.0f));
        r6.setAlpha(0.0f);
        r23.setAlpha(0.0f);
        setImportantForAccessibility(4);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r1) {
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r1) {
        return false;
    }

    public void show(boolean r2, boolean r3, View r4) {
        if (r2 == false) goto L8;
        if (this.shownTop == r3) goto L29;
        this.shownTop = r3;
    L11:
        if (r2 == false) goto L13;
        FrameLayout r1 = this.top;
    L14:
        r1.clearAnimation();
        ViewPropertyAnimator r12 = r1.animate();
        float r0 = 0.0f;
        if (r3 == false) goto L19;
        if (r2 == false) goto L18;
        float r22 = 0.5f;
    L20:
        r12.alpha(r22).start();
        if (r4 == null) goto L28;
        r4.clearAnimation();
        ViewPropertyAnimator r13 = r4.animate();
        if (r3 == true) goto L26;
        r0 = 1.0f;
    L26:
        r13.alpha(r0).start();
        return;
    L28:
        return;
    L18:
        r22 = 0.2f;
        goto L20
    L19:
        r22 = 0.0f;
        goto L20
    L13:
        r1 = this.bottom;
        goto L14
    L29:
        return;
    L8:
        if (this.shownBottom == r3) goto L30;
        this.shownBottom = r3;
        goto L11
    }

    public void updateCaption(CharSequence r8) {
        CharSequence r2 = AnimatedEmojiSpan.cloneSpans(new SpannableString(r8));
        this.storyCaptionView.captionTextview.setText(r2, null, null, false, false);
    }

    public void updateCount() {
        this.storiesCount = MessagesController.getInstance(this.currentAccount).getStoriesController().getSelfStoriesCount() + 1;
        this.top.invalidate();
    }
}
