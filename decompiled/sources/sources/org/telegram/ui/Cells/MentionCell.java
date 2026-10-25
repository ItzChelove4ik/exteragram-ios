package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.exteragram.messenger.ExteraConfig;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class MentionCell extends LinearLayout {
    private boolean attached;
    private final AvatarDrawable avatarDrawable;
    private Drawable emojiDrawable;
    private final BackupImageView imageView;
    private final TextView nameTextView;
    private boolean needsDivider;
    private final Theme.ResourcesProvider resourcesProvider;
    private final TextView usernameTextView;

    /* renamed from: org.telegram.ui.Cells.MentionCell$1, reason: invalid class name */
    public class AnonymousClass1 extends TextView {
        final /* synthetic */ MentionCell this$0;

        public AnonymousClass1(MentionCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.TextView
        public void setText(CharSequence r3, TextView.BufferType r4) {
            super.setText(Emoji.replaceEmoji(r3, getPaint().getFontMetricsInt(), false), r4);
        }
    }

    public MentionCell(Context r12, Theme.ResourcesProvider r13) {
        super(r12);
        this.needsDivider = false;
        this.resourcesProvider = r13;
        setOrientation(0);
        AvatarDrawable r132 = new AvatarDrawable();
        this.avatarDrawable = r132;
        r132.setTextSize(AndroidUtilities.dp(18.0f));
        BackupImageView r133 = new BackupImageView(r12);
        this.imageView = r133;
        r133.setRoundRadius(ExteraConfig.getAvatarCorners(28.0f));
        addView(r133, LayoutHelper.createLinear(28, 28, 8.0f, 4.0f, 0.0f, 0.0f));
        AnonymousClass1 r134 = new AnonymousClass1(this, r12);
        this.nameTextView = r134;
        r134.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
        r134.setTextSize(1, 15.0f);
        r134.setSingleLine(true);
        r134.setGravity(3);
        TextUtils.TruncateAt r3 = TextUtils.TruncateAt.END;
        r134.setEllipsize(r3);
        addView(r134, LayoutHelper.createLinear(-2, -2, 16, 12, 0, 0, 0));
        TextView r135 = new TextView(r12);
        this.usernameTextView = r135;
        r135.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText3));
        r135.setTextSize(1, 15.0f);
        r135.setSingleLine(true);
        r135.setGravity(3);
        r135.setEllipsize(r3);
        addView(r135, LayoutHelper.createLinear(-2, -2, 16, 12, 0, 8, 0));
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r8) {
        super.dispatchDraw(r8);
        Drawable r0 = this.emojiDrawable;
        if (r0 != null) goto L5;
        return;
    L5:
        if ((r0 instanceof AnimatedEmojiDrawable) == false) goto L7;
        float r02 = 24.0f;
    L8:
        int r03 = AndroidUtilities.dp(r02);
        if ((this.emojiDrawable instanceof AnimatedEmojiDrawable) == false) goto L11;
        float r1 = -2.0f;
    L12:
        int r12 = AndroidUtilities.dp(r1);
        this.emojiDrawable.setBounds(this.nameTextView.getLeft() + r12, ((this.nameTextView.getTop() + this.nameTextView.getBottom()) - r03) / 2, (this.nameTextView.getLeft() + r12) + r03, ((this.nameTextView.getTop() + this.nameTextView.getBottom()) + r03) / 2);
        Drawable r04 = this.emojiDrawable;
        if ((r04 instanceof AnimatedEmojiDrawable) == false) goto L15;
        ((AnimatedEmojiDrawable) r04).setTime(System.currentTimeMillis());
    L15:
        this.emojiDrawable.draw(r8);
        return;
    L11:
        r1 = 0.0f;
        goto L12
    L7:
        r02 = 20.0f;
        goto L8
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        this.nameTextView.invalidate();
    }

    public void invalidateEmojis() {
        this.nameTextView.invalidate();
        this.usernameTextView.invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attached = true;
        Drawable r0 = this.emojiDrawable;
        if ((r0 instanceof AnimatedEmojiDrawable) == false) goto L6;
        ((AnimatedEmojiDrawable) r0).addView(this);
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attached = false;
        Drawable r0 = this.emojiDrawable;
        if ((r0 instanceof AnimatedEmojiDrawable) == false) goto L6;
        ((AnimatedEmojiDrawable) r0).removeView(this);
        return;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas r8) {
        super.onDraw(r8);
        if (this.needsDivider == false) goto L6;
        r8.drawLine(AndroidUtilities.dp(52.0f), getHeight() - 1, getWidth() - AndroidUtilities.dp(8.0f), getHeight() - 1, Theme.dividerPaint);
        return;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(36.0f), TLObject.FLAG_30));
    }

    public void resetEmojiSuggestion() {
        this.nameTextView.setPadding(0, 0, 0, 0);
        Drawable r0 = this.emojiDrawable;
        if (r0 != null) goto L5;
        return;
    L5:
        if ((r0 instanceof AnimatedEmojiDrawable) == false) goto L7;
        ((AnimatedEmojiDrawable) r0).removeView(this);
    L7:
        this.emojiDrawable = null;
        invalidate();
    }

    public void setBotCommand(String r4, String r5, TLRPC.User r6, boolean r7) {
        resetEmojiSuggestion();
        BackupImageView r0 = this.imageView;
        if (r6 == null) goto L10;
        r0.setVisibility(0);
        this.avatarDrawable.setInfo(r6);
        TLRPC.UserProfilePhoto r02 = r6.photo;
        if (r02 != null) goto L7;
    L9:
        this.imageView.setImageDrawable(this.avatarDrawable);
    L11:
        this.usernameTextView.setVisibility(0);
        if (r7 == false) goto L14;
        ColoredImageSpan r62 = new ColoredImageSpan(R.drawable.mini_ephemeral_hidden_14);
        r62.setColorKey(Theme.key_windowBackgroundWhiteGrayText3);
        r62.setTopOffset(1);
        SpannableStringBuilder r03 = new SpannableStringBuilder(r4);
        r03.append(" *");
        r03.setSpan(r62, r03.length() - 1, r03.length(), 33);
        this.nameTextView.setText(r03);
    L15:
        TextView r3 = this.usernameTextView;
        r3.setText(Emoji.replaceEmoji(r5, r3.getPaint().getFontMetricsInt(), false));
        return;
    L14:
        this.nameTextView.setText(r4);
        goto L15
    L7:
        if (r02.photo_small == null) goto L9;
        this.imageView.setForUserOrChat(r6, this.avatarDrawable);
        goto L11
    L10:
        r0.setVisibility(4);
        goto L11
    }

    public void setChat(TLRPC.Chat r4) {
        resetEmojiSuggestion();
        if (r4 != null) goto L6;
        this.nameTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.usernameTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.imageView.setImageDrawable(null);
        return;
    L6:
        this.avatarDrawable.setInfo(r4);
        TLRPC.ChatPhoto r1 = r4.photo;
        if (r1 != null) goto L9;
    L11:
        this.imageView.setImageDrawable(this.avatarDrawable);
    L12:
        this.nameTextView.setText(r4.title);
        String r42 = ChatObject.getPublicUsername(r4);
        TextView r12 = this.usernameTextView;
        if (r42 == null) goto L15;
        r12.setText("@".concat(r42));
    L16:
        this.imageView.setVisibility(0);
        this.usernameTextView.setVisibility(0);
        return;
    L15:
        r12.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        goto L16
    L9:
        if (r1.photo_small == null) goto L11;
        this.imageView.setForUserOrChat(r4, this.avatarDrawable);
        goto L12
    }

    public void setDivider(boolean r2) {
        if (r2 == this.needsDivider) goto L6;
        this.needsDivider = r2;
        setWillNotDraw(!r2);
        invalidate();
        return;
    }

    public void setEmojiSuggestion(MediaDataController.KeywordResult r5) {
        this.imageView.setVisibility(4);
        this.usernameTextView.setVisibility(4);
        String r0 = r5.emoji;
        if (r0 != null) goto L5;
    L14:
        this.emojiDrawable = Emoji.getEmojiDrawable(r5.emoji);
    L15:
        Drawable r02 = this.emojiDrawable;
        TextView r2 = this.nameTextView;
        if (r02 != null) goto L18;
        r2.setPadding(0, 0, 0, 0);
        TextView r4 = this.nameTextView;
        StringBuilder r03 = new StringBuilder();
        r03.append(r5.emoji);
        r03.append(":  ");
        r03.append(r5.keyword);
        r4.setText(r03);
        return;
    L18:
        r2.setPadding(AndroidUtilities.dp(22.0f), 0, 0, 0);
        TextView r42 = this.nameTextView;
        StringBuilder r04 = new StringBuilder();
        r04.append(":  ");
        r04.append(r5.keyword);
        r42.setText(r04);
        return;
    L5:
        if (r0.startsWith("animated_") == false) goto L14;
        Drawable r05 = this.emojiDrawable;     // Catch: Exception -> L13
        if ((r05 instanceof AnimatedEmojiDrawable) == false) goto L9;
        ((AnimatedEmojiDrawable) r05).removeView(this);     // Catch: Exception -> L13
        this.emojiDrawable = null;     // Catch: Exception -> L13
    L9:
        long r22 = Long.parseLong(r5.emoji.substring(9));     // Catch: Exception -> L13
        AnimatedEmojiDrawable r06 = AnimatedEmojiDrawable.make(UserConfig.selectedAccount, 0, r22);     // Catch: Exception -> L13
        this.emojiDrawable = r06;     // Catch: Exception -> L13
        if (this.attached == false) goto L15;
        r06.addView(this);     // Catch: Exception -> L13
    L13:
        this.emojiDrawable = Emoji.getEmojiDrawable(r5.emoji);
        goto L15
    }

    public void setIsDarkTheme(boolean r2) {
        TextView r0 = this.nameTextView;
        if (r2 == false) goto L6;
        r0.setTextColor(-1);
        this.usernameTextView.setTextColor(-4473925);
        return;
    L6:
        r0.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
        this.usernameTextView.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText3));
    }

    public void setText(String r3) {
        resetEmojiSuggestion();
        this.imageView.setVisibility(4);
        this.usernameTextView.setVisibility(4);
        this.nameTextView.setText(r3);
    }

    public void setUser(TLRPC.User r4) {
        resetEmojiSuggestion();
        if (r4 != null) goto L6;
        this.nameTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.usernameTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.imageView.setImageDrawable(null);
        return;
    L6:
        this.avatarDrawable.setInfo(r4);
        TLRPC.UserProfilePhoto r1 = r4.photo;
        if (r1 != null) goto L9;
    L11:
        this.imageView.setImageDrawable(this.avatarDrawable);
    L12:
        this.nameTextView.setText(UserObject.getUserName(r4));
        String r12 = UserObject.getPublicUsername(r4);
        TextView r2 = this.usernameTextView;
        if (r12 == null) goto L15;
        r2.setText("@" + UserObject.getPublicUsername(r4));
    L16:
        this.imageView.setVisibility(0);
        this.usernameTextView.setVisibility(0);
        return;
    L15:
        r2.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        goto L16
    L9:
        if (r1.photo_small == null) goto L11;
        this.imageView.setForUserOrChat(r4, this.avatarDrawable);
        goto L12
    }
}
