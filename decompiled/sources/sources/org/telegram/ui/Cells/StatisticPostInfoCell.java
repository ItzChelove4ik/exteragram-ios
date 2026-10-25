package org.telegram.ui.Cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import java.util.ArrayList;
import java.util.Date;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.StatisticActivity;
import org.telegram.ui.Stories.StoriesUtilities;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes6.dex */
public abstract class StatisticPostInfoCell extends FrameLayout {
    private final AvatarDrawable avatarDrawable;
    private final TLRPC.ChatFull chat;
    private final TextView date;
    private final Paint dividerPaint;
    private final BackupImageView imageView;
    private final TextView likes;
    private final SimpleTextView message;
    private boolean needDivider;
    private StatisticActivity.RecentPostInfo postInfo;
    private final Theme.ResourcesProvider resourcesProvider;
    private final TextView shares;
    private final StoriesUtilities.AvatarStoryParams storyAvatarParams;
    private final TextView views;

    /* renamed from: org.telegram.ui.Cells.StatisticPostInfoCell$1, reason: invalid class name */
    public class AnonymousClass1 extends BackupImageView {
        final /* synthetic */ StatisticPostInfoCell this$0;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public AnonymousClass1(StatisticPostInfoCell r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            this.val$resourcesProvider = r3;
            super(r2);
        }

        @Override // org.telegram.ui.Components.BackupImageView, android.view.View
        public void onDraw(Canvas r6) {
            if (StatisticPostInfoCell.m7046$$Nest$fgetpostInfo(this.this$0) != null) goto L5;
        L8:
            super.onDraw(r6);
            return;
        L5:
            if (StatisticPostInfoCell.m7046$$Nest$fgetpostInfo(this.this$0).isStory() == false) goto L8;
            float r2 = AndroidUtilities.dp(1.0f);
            StatisticPostInfoCell.m7047$$Nest$fgetstoryAvatarParams(this.this$0).originalAvatarRect.set(r2, r2, getMeasuredWidth() - r0, getMeasuredHeight() - r0);
            StatisticPostInfoCell.m7047$$Nest$fgetstoryAvatarParams(this.this$0).drawSegments = false;
            StatisticPostInfoCell.m7047$$Nest$fgetstoryAvatarParams(this.this$0).animate = false;
            StatisticPostInfoCell.m7047$$Nest$fgetstoryAvatarParams(this.this$0).drawInside = true;
            StatisticPostInfoCell.m7047$$Nest$fgetstoryAvatarParams(this.this$0).isArchive = false;
            StatisticPostInfoCell.m7047$$Nest$fgetstoryAvatarParams(this.this$0).forceState = 1;
            StatisticPostInfoCell.m7047$$Nest$fgetstoryAvatarParams(this.this$0).resourcesProvider = this.val$resourcesProvider;
            StoriesUtilities.drawAvatarWithStory(0, r6, this.imageReceiver, StatisticPostInfoCell.m7047$$Nest$fgetstoryAvatarParams(this.this$0));
        }
    }

    /* renamed from: org.telegram.ui.Cells.StatisticPostInfoCell$2, reason: invalid class name */
    public class AnonymousClass2 extends SimpleTextView {
        final /* synthetic */ StatisticPostInfoCell this$0;

        public AnonymousClass2(StatisticPostInfoCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.ActionBar.SimpleTextView
        public boolean setText(CharSequence r3) {
            return super.setText(Emoji.replaceEmoji(r3, getPaint().getFontMetricsInt(), false));
        }
    }

    /* renamed from: -$$Nest$fgetpostInfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StatisticActivity.RecentPostInfo m7046$$Nest$fgetpostInfo(StatisticPostInfoCell r0) {
        return r0.postInfo;
    }

    /* renamed from: -$$Nest$fgetstoryAvatarParams, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoriesUtilities.AvatarStoryParams m7047$$Nest$fgetstoryAvatarParams(StatisticPostInfoCell r0) {
        return r0.storyAvatarParams;
    }

    public StatisticPostInfoCell(Context r23, TLRPC.ChatFull r24, Theme.ResourcesProvider r25) {
        super(r23);
        this.dividerPaint = new Paint(1);
        this.avatarDrawable = new AvatarDrawable();
        this.storyAvatarParams = new StoriesUtilities.AvatarStoryParams(false);
        this.chat = r24;
        this.resourcesProvider = r25;
        AnonymousClass1 r3 = new AnonymousClass1(this, r23, r25);
        this.imageView = r3;
        setClipChildren(false);
        boolean r2 = LocaleController.isRTL;
        if (r2 == true) goto L5;
        int r6 = PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES;
    L6:
        int r10 = r6 | 16;
        if (r2 == true) goto L9;
        float r11 = 12.0f;
    L10:
        if (r2 == true) goto L12;
        float r13 = 16.0f;
    L13:
        addView(r3, LayoutHelper.createFrame(46, 46.0f, r10, r11, 0.0f, r13, 0.0f));
        LinearLayout r22 = new LinearLayout(r23);
        r22.setOrientation(1);
        LinearLayout r32 = new LinearLayout(r23);
        r32.setOrientation(0);
        AnonymousClass2 r62 = new AnonymousClass2(this, r23);
        this.message = r62;
        NotificationCenter.listenEmojiLoading(r62);
        r62.setTypeface(AndroidUtilities.bold());
        r62.setTextSize(16);
        r62.setMaxLines(1);
        r62.setTextColor(-16777216);
        if (LocaleController.isRTL == false) goto L16;
        int r9 = 5;
    L17:
        r62.setGravity(r9);
        TextView r92 = new TextView(r23);
        this.views = r92;
        r92.setTextSize(1, 14.0f);
        r92.setTextColor(-16777216);
        if (LocaleController.isRTL == true) goto L20;
        r32.addView(r62, LayoutHelper.createLinear(0, -2, 1.0f, 0, 0, 0, 16, 0));
        r32.addView(r92, LayoutHelper.createLinear(-2, -2, 80));
    L21:
        r22.addView(r32, LayoutHelper.createFrame(-1, -2.0f, 8388659, 0.0f, 7.0f, 0.0f, 0.0f));
        TextView r33 = new TextView(r23);
        this.date = r33;
        r33.setTextSize(1, 13.0f);
        r33.setTextColor(-16777216);
        r33.setLines(1);
        r33.setEllipsize(TextUtils.TruncateAt.END);
        TextView r112 = new TextView(r23);
        this.shares = r112;
        r112.setTextSize(1, 13.0f);
        r112.setTextColor(-16777216);
        r112.setGravity(16);
        TextView r132 = new TextView(r23);
        this.likes = r132;
        r132.setTextSize(1, 13.0f);
        r132.setTextColor(-16777216);
        r132.setGravity(16);
        LinearLayout r4 = new LinearLayout(r23);
        r4.setOrientation(0);
        if (LocaleController.isRTL == true) goto L24;
        r4.addView(r33, LayoutHelper.createLinear(0, -2, 1.0f, 0, 0, 0, 8, 0));
        r4.addView(r132, LayoutHelper.createLinear(-2, -2, 16));
        r4.addView(r112, LayoutHelper.createLinear(-2, -2, 16, 10, 0, 0, 0));
    L25:
        r22.addView(r4, LayoutHelper.createFrame(-1, -2.0f, 8388659, 0.0f, 3.0f, 0.0f, 9.0f));
        boolean r42 = LocaleController.isRTL;
        if (r42 == true) goto L28;
        float r17 = 72.0f;
    L29:
        if (r42 == true) goto L31;
        float r19 = 18.0f;
    L32:
        addView(r22, LayoutHelper.createFrame(-1, -2.0f, 0, r17, 0.0f, r19, 0.0f));
        int r26 = Theme.key_dialogTextBlack;
        r62.setTextColor(Theme.getColor(r26));
        r92.setTextColor(Theme.getColor(r26));
        int r27 = Theme.key_windowBackgroundWhiteGrayText3;
        r33.setTextColor(Theme.getColor(r27));
        r112.setTextColor(Theme.getColor(r27));
        r132.setTextColor(Theme.getColor(r27));
        Drawable r34 = ContextCompat.getDrawable(r23, R.drawable.mini_stats_likes).mutate();
        DrawableCompat.setTint(r34, Theme.getColor(r27));
        Drawable r1 = ContextCompat.getDrawable(r23, R.drawable.mini_stats_shares).mutate();
        DrawableCompat.setTint(r1, Theme.getColor(r27));
        CombinedDrawable r28 = new CombinedDrawable(null, r34, 0, AndroidUtilities.dp(1.0f));
        r28.setCustomSize(r1.getIntrinsicWidth(), r1.getIntrinsicHeight());
        r132.setCompoundDrawablesWithIntrinsicBounds(r28, null, null, null);
        r132.setCompoundDrawablePadding(AndroidUtilities.dp(2.0f));
        CombinedDrawable r35 = new CombinedDrawable(null, r1, 0, AndroidUtilities.dp(1.0f));
        r35.setCustomSize(r1.getIntrinsicWidth(), r1.getIntrinsicHeight());
        r112.setCompoundDrawablesWithIntrinsicBounds(r35, null, null, null);
        r112.setCompoundDrawablePadding(AndroidUtilities.dp(2.0f));
        setWillNotDraw(false);
        return;
    L31:
        r19 = 72.0f;
        goto L32
    L28:
        r17 = 18.0f;
        goto L29
    L24:
        r4.addView(r112, LayoutHelper.createLinear(-2, -2, 16, 0, 0, 10, 0));
        r4.addView(r132, LayoutHelper.createLinear(-2, -2, 16));
        r4.addView(r33, LayoutHelper.createLinear(0, -2, 1.0f, 0, 8, 0, 0, 0));
        goto L25
    L20:
        r32.addView(r92, LayoutHelper.createLinear(-2, -2, 80));
        r32.addView(r62, LayoutHelper.createLinear(0, -2, 1.0f, 0, 16, 0, 0, 0));
        goto L21
    L16:
        r9 = 3;
        goto L17
    L12:
        r13 = 12.0f;
        goto L13
    L9:
        r11 = 16.0f;
        goto L10
    L5:
        r6 = 8388613;
        goto L6
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r15) {
        super.dispatchDraw(r15);
        if (this.needDivider == false) goto L10;
        this.dividerPaint.setColor(Theme.getColor(Theme.key_divider, this.resourcesProvider));
        if (LocaleController.isRTL == false) goto L8;
        r15.drawRect(0.0f, getHeight() - 1, getWidth() - AndroidUtilities.dp(72.0f), getHeight(), this.dividerPaint);
        return;
    L8:
        r15.drawRect(AndroidUtilities.dp(72.0f), getHeight() - 1, getWidth(), getHeight(), this.dividerPaint);
        return;
    }

    public BackupImageView getImageView() {
        return this.imageView;
    }

    public StatisticActivity.RecentPostInfo getPostInfo() {
        return this.postInfo;
    }

    public StoriesUtilities.AvatarStoryParams getStoryAvatarParams() {
        return this.storyAvatarParams;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.storyAvatarParams.onDetachFromWindow();
    }

    public void setData(StatisticActivity.MemberData r4) {
        this.avatarDrawable.setInfo(r4.user);
        this.imageView.setForUserOrChat(r4.user, this.avatarDrawable);
        this.imageView.setRoundRadius(AndroidUtilities.dp(46.0f) >> 1);
        this.message.setText(r4.user.first_name);
        this.date.setText(r4.description);
        this.views.setVisibility(8);
        this.shares.setVisibility(8);
        this.likes.setVisibility(8);
    }

    public void setData(StatisticActivity.RecentPostInfo r12, boolean r13) {
        this.postInfo = r12;
        this.needDivider = !r13;
        MessageObject r6 = r12.message;
        ArrayList<TLRPC.PhotoSize> r132 = r6.photoThumbs;
        int r10 = 0;
        if (r132 == null) goto L6;
        this.imageView.setImage(ImageLocation.getForObject(FileLoader.getClosestPhotoSizeWithSize(r132, AndroidUtilities.getPhotoSize()), r6.photoThumbsObject), "50_50", ImageLocation.getForObject(FileLoader.getClosestPhotoSizeWithSize(r6.photoThumbs, 50), r6.photoThumbsObject), "b1", 0, r6);
        this.imageView.setRoundRadius(AndroidUtilities.dp(9.0f));
        this.imageView.setScaleX(0.96f);
        this.imageView.setScaleY(0.96f);
    L10:
        if (r6.isStory() == false) goto L13;
        this.imageView.setScaleX(1.0f);
        this.imageView.setScaleY(1.0f);
        this.imageView.setRoundRadius(AndroidUtilities.dp(46.0f) >> 1);
    L13:
        if (r6.isMusic() == false) goto L16;
        CharSequence r133 = String.format("%s, %s", new Object[]{r6.getMusicTitle().trim(), r6.getMusicAuthor().trim()});
    L23:
        if (r133 != null) goto L25;
        r133 = _UrlKt.FRAGMENT_ENCODE_SET;
    L25:
        SpannableStringBuilder r0 = new SpannableStringBuilder(r133);
        URLSpan[] r134 = (URLSpan[]) r0.getSpans(0, r0.length(), URLSpan.class);
        int r1 = r134.length;
        int r2 = 0;
    L26:
        if (r2 >= r1) goto L28;
        r0.removeSpan(r134[r2]);
        r2 = r2 + 1;
        goto L26
    L28:
        this.message.setText(AndroidUtilities.trim(AndroidUtilities.replaceNewLines(r0), null));
        this.views.setText(String.format(LocaleController.getPluralString("Views", r12.getViews()), new Object[]{AndroidUtilities.formatWholeNumber(r12.getViews(), 0)}));
        Date r135 = new Date(r12.getDate() * 1000);
        this.date.setText(LocaleController.formatString(R.string.formatDateAtTime, new Object[]{LocaleController.getInstance().getFormatterYear().format(r135), LocaleController.getInstance().getFormatterDay().format(r135)}));
        this.shares.setText(AndroidUtilities.formatWholeNumber(r12.getForwards(), 0));
        this.likes.setText(AndroidUtilities.formatWholeNumber(r12.getReactions(), 0));
        TextView r136 = this.shares;
        if (r12.getForwards() == 0) goto L31;
        int r02 = 0;
    L32:
        r136.setVisibility(r02);
        TextView r137 = this.likes;
        if (r12.getReactions() != 0) goto L36;
        r10 = 8;
    L36:
        r137.setVisibility(r10);
        invalidate();
        return;
    L31:
        r02 = 8;
        goto L32
    L16:
        if (r6.isStory() == false) goto L18;
        r133 = LocaleController.getString(R.string.Story);
        goto L23
    L18:
        r133 = r6.caption;
        if (r133 != null) goto L23;
        r133 = r6.messageText;
        goto L23
    L6:
        if (this.chat.chat_photo.sizes.size() <= 0) goto L8;
        this.imageView.setImage(ImageLocation.getForPhoto(this.chat.chat_photo.sizes.get(0), this.chat.chat_photo), "50_50", null, null, this.chat);
        this.imageView.setRoundRadius(AndroidUtilities.dp(46.0f) >> 1);
        this.imageView.setScaleX(0.96f);
        this.imageView.setScaleY(0.96f);
        goto L10
    L8:
        TLRPC.Chat r138 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(this.chat.id));
        this.avatarDrawable.setInfo(r138);
        this.imageView.setForUserOrChat(r138, this.avatarDrawable);
        this.imageView.setRoundRadius(AndroidUtilities.dp(46.0f) >> 1);
        this.imageView.setScaleX(1.0f);
        this.imageView.setScaleY(1.0f);
        goto L10
    }

    public void setImageViewAction(View.OnClickListener r1) {
        this.imageView.setOnClickListener(r1);
    }
}
