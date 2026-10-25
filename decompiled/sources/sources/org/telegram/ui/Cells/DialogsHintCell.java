package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.PorterDuff;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AvatarsImageView;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;

/* loaded from: classes3.dex */
public class DialogsHintCell extends FrameLayout {
    private final AvatarsImageView avatarsImageView;
    private final ImageView chevronView;
    private final ImageView closeView;
    private final LinearLayout contentView;
    private int height;
    public final BackupImageView imageView;
    public final LinkSpanDrawable.LinksTextView messageView;
    private final LinearLayout parentView;
    public boolean titleIsError;
    public final AnimatedEmojiSpan.TextViewEmojis titleView;

    public static /* synthetic */ void $r8$lambda$gZYGnLbU6pU18Dc52u7CeepBKjg(DialogsHintCell r0, View.OnClickListener r1, View r2) {
        r0.lambda$setOnClickListener$0(r1, r2);
    }

    public DialogsHintCell(Context r25) {
        super(r25);
        setWillNotDraw(false);
        setPadding(AndroidUtilities.dp(9.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(9.0f), AndroidUtilities.dp(7.0f));
        AvatarsImageView r3 = new AvatarsImageView(r25, false);
        this.avatarsImageView = r3;
        r3.setStepFactor(0.56790125f);
        r3.setVisibility(8);
        r3.setCount(0);
        BackupImageView r5 = new BackupImageView(r25);
        this.imageView = r5;
        r5.setRoundRadius(ExteraConfig.getAvatarCorners(36.0f));
        r5.setVisibility(8);
        LinearLayout r6 = new LinearLayout(r25);
        this.contentView = r6;
        r6.setOrientation(1);
        if (LocaleController.isRTL == false) goto L5;
        int r8 = AndroidUtilities.dp(24.0f);
    L7:
        if (LocaleController.isRTL == false) goto L9;
        int r9 = 0;
    L10:
        r6.setPadding(r8, 0, r9, 0);
        AnimatedEmojiSpan.TextViewEmojis r82 = new AnimatedEmojiSpan.TextViewEmojis(r25);
        this.titleView = r82;
        TextUtils.TruncateAt r92 = TextUtils.TruncateAt.END;
        r82.setEllipsize(r92);
        r82.setTextSize(1, 14.0f);
        r82.setTypeface(AndroidUtilities.bold());
        int r10 = 5;
        r82.setMaxLines(5);
        if (LocaleController.isRTL == false) goto L13;
        int r11 = 5;
    L14:
        r6.addView(r82, LayoutHelper.createLinear(-2, -2, 0.0f, r11 | 48));
        LinkSpanDrawable.LinksTextView r112 = new LinkSpanDrawable.LinksTextView(r25);
        this.messageView = r112;
        r112.setTextSize(1, 13.0f);
        r112.setEllipsize(r92);
        r112.setMaxLines(5);
        r6.addView(r112, LayoutHelper.createLinear(-1, -2, 0.0f, 48));
        NotificationCenter.listenEmojiLoading(r82);
        NotificationCenter.listenEmojiLoading(r112);
        LinearLayout r83 = new LinearLayout(r25);
        this.parentView = r83;
        r83.setOrientation(0);
        if (LocaleController.isRTL == false) goto L17;
        r83.addView(r6, LayoutHelper.createFrame(-1, -1.0f, 16, 7.0f, 0.0f, 7.0f, 0.0f));
        r83.addView(r3, LayoutHelper.createFrame(0, -1.0f, 16, 0.0f, 0.0f, -2.0f, 0.0f));
        r83.addView(r5, LayoutHelper.createFrame(36, 36.0f, 21, 0.0f, 0.0f, -2.0f, 0.0f));
    L18:
        addView(r83, LayoutHelper.createFrame(-1, -1.0f));
        r83.setClipChildren(false);
        r83.setClipToPadding(false);
        ImageView r32 = new ImageView(r25);
        this.chevronView = r32;
        r32.setImageResource(R.drawable.arrow_newchat);
        if (LocaleController.isRTL == false) goto L21;
        int r52 = 3;
    L22:
        addView(r32, LayoutHelper.createFrame(16, 16.0f, r52 | 16, 4.0f, 0.0f, 4.0f, 0.0f));
        ImageView r33 = new ImageView(r25);
        this.closeView = r33;
        r33.setImageResource(R.drawable.msg_close);
        r33.setPadding(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f));
        if (LocaleController.isRTL == false) goto L25;
        r10 = 3;
    L25:
        addView(r33, LayoutHelper.createFrame(36, 36.0f, r10 | 16, -4.0f, 0.0f, -4.0f, 0.0f));
        r33.setVisibility(8);
        setClipToPadding(false);
        setClipChildren(false);
        updateColors();
        return;
    L21:
        r52 = 5;
        goto L22
    L17:
        r83.addView(r5, LayoutHelper.createFrame(36, 36.0f, 19, -2.0f, 0.0f, 0.0f, 0.0f));
        r83.addView(r3, LayoutHelper.createFrame(0, -1.0f, 16, -2.0f, 0.0f, 0.0f, 0.0f));
        r83.addView(r6, LayoutHelper.createFrame(-1, -1.0f, 16, 7.0f, 0.0f, 7.0f, 0.0f));
        goto L18
    L13:
        r11 = 3;
        goto L14
    L9:
        r9 = AndroidUtilities.dp(24.0f);
        goto L10
    L5:
        r8 = 0;
        goto L7
    }

    private /* synthetic */ void lambda$setOnClickListener$0(View.OnClickListener r2, View r3) {
        if (getAlpha() <= 0.5f) goto L7;
        if (r2 == null) goto L8;
        r2.onClick(r3);
        return;
    L8:
        return;
    }

    public void clear() {
        setCompact(false);
        setAvatars(UserConfig.selectedAccount, null);
        this.imageView.setVisibility(8);
        this.imageView.clearImage();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r5, int r6) {
        super.onMeasure(r5, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.displaySize.y, Integer.MIN_VALUE));
        LinearLayout r62 = this.contentView;
        r62.measure(View.MeasureSpec.makeMeasureSpec(r62.getMeasuredWidth(), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.displaySize.y, Integer.MIN_VALUE));
        int r63 = (this.contentView.getMeasuredHeight() + getPaddingTop()) + getPaddingBottom();
        this.height = r63;
        super.onMeasure(r5, View.MeasureSpec.makeMeasureSpec(r63, TLObject.FLAG_30));
        this.closeView.setTranslationY((getPaddingBottom() - getPaddingTop()) / 2.0f);
        this.avatarsImageView.setTranslationY((getPaddingBottom() - getPaddingTop()) / 2.0f);
        this.imageView.setTranslationY((getPaddingBottom() - getPaddingTop()) / 2.0f);
        this.chevronView.setTranslationY((getPaddingBottom() - getPaddingTop()) / 2.0f);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r3) {
        if (getAlpha() >= 0.5f) goto L7;
        return false;
    L7:
        return super.onTouchEvent(r3);
    }

    public void setAvatars(int r9, ArrayList<TLRPC.User> r10) {
        if (r10 != null) goto L5;
        int r1 = 0;
    L6:
        int r12 = Math.min(3, r1);
        AvatarsImageView r3 = this.avatarsImageView;
        if (r12 == r3.avatarsDrawable.count) goto L9;
        boolean r4 = true;
    L11:
        if (r12 > 1) goto L13;
        r3.setAvatarsTextSize(AndroidUtilities.dp(22.0f));
        this.avatarsImageView.setSize(AndroidUtilities.dp(36.0f));
    L14:
        this.avatarsImageView.setCount(r12);
        AvatarsImageView r32 = this.avatarsImageView;
        if (r12 > 0) goto L17;
        int r7 = 8;
    L18:
        r32.setVisibility(r7);
        ViewGroup.LayoutParams r33 = this.avatarsImageView.getLayoutParams();
        if (r12 > 1) goto L21;
        int r13 = AndroidUtilities.dp(36.0f);
    L22:
        r33.width = r13;
        if (r4 == false) goto L25;
        this.parentView.requestLayout();
    L25:
        if (r10 == null) goto L33;
        int r14 = 0;
    L27:
        if (r14 >= 3) goto L33;
        AvatarsImageView r34 = this.avatarsImageView;
        if (r14 < r10.size()) goto L31;
        TLRPC.User r42 = null;
    L32:
        r34.setObject(r14, r9, r42);
        r14 = r14 + 1;
        goto L27
    L31:
        r42 = r10.get(r14);
    L33:
        this.avatarsImageView.commitTransition(false);
        return;
    L21:
        r13 = AndroidUtilities.dp(((r12 - 1) * 18) + 30);
        goto L22
    L17:
        r7 = 0;
        goto L18
    L13:
        r3.setAvatarsTextSize(AndroidUtilities.dp(20.0f));
        this.avatarsImageView.setSize(AndroidUtilities.dp(30.0f));
        goto L14
    L9:
        r4 = false;
        goto L11
    L5:
        r1 = r10.size();
        goto L6
    }

    public void setCompact(boolean r1) {
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener r2) {
        super.setOnClickListener(new DialogsHintCell$$ExternalSyntheticLambda0(this, r2));
    }

    public void setOnCloseListener(View.OnClickListener r3) {
        this.chevronView.setVisibility(4);
        this.closeView.setVisibility(0);
        this.closeView.setOnClickListener(r3);
    }

    public void setText(CharSequence r3, CharSequence r4) {
        setText(r3, r4, true, false);
    }

    public void setText(CharSequence r4, CharSequence r5, boolean r6, boolean r7) {
        this.titleIsError = r7;
        AnimatedEmojiSpan.TextViewEmojis r72 = this.titleView;
        if (TextUtils.isEmpty(r4) == false) goto L5;
        int r0 = 8;
    L6:
        r72.setVisibility(r0);
        this.titleView.setText(r4);
        this.titleView.setCompoundDrawables(null, null, null, null);
        this.messageView.setText(r5);
        ImageView r42 = this.chevronView;
        if (r6 == false) goto L9;
        int r52 = 0;
    L10:
        r42.setVisibility(r52);
        this.closeView.setVisibility(8);
        if (r6 == false) goto L13;
        int r43 = AndroidUtilities.dp(24.0f);
    L14:
        LinearLayout r53 = this.contentView;
        boolean r62 = LocaleController.isRTL;
        if (r62 == false) goto L17;
        int r73 = r43;
    L18:
        if (r62 == false) goto L20;
        r43 = 0;
    L20:
        r53.setPadding(r73, 0, r43, 0);
        updateColors();
        return;
    L17:
        r73 = 0;
        goto L18
    L13:
        r43 = 0;
        goto L14
    L9:
        r52 = 8;
        goto L10
    L5:
        r0 = 0;
        goto L6
    }

    public void showImage() {
        this.imageView.setVisibility(0);
    }

    public void updateColors() {
        AnimatedEmojiSpan.TextViewEmojis r0 = this.titleView;
        if (this.titleIsError == false) goto L5;
        int r1 = Theme.key_text_RedBold;
    L6:
        r0.setTextColor(Theme.getColor(r1));
        LinkSpanDrawable.LinksTextView r02 = this.messageView;
        int r12 = Theme.key_windowBackgroundWhiteGrayText;
        r02.setTextColor(Theme.getColor(r12));
        this.messageView.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn));
        ImageView r03 = this.chevronView;
        int r2 = Theme.getColor(r12);
        PorterDuff.Mode r3 = PorterDuff.Mode.SRC_IN;
        r03.setColorFilter(r2, r3);
        this.closeView.setColorFilter(Theme.getColor(r12), r3);
        this.closeView.setBackground(Theme.AdaptiveRipple.filledCircle());
        return;
    L5:
        r1 = Theme.key_windowBackgroundWhiteBlackText;
        goto L6
    }
}
