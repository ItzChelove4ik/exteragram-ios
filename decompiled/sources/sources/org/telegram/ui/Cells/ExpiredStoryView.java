package org.telegram.ui.Cells;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextUtils;
import com.exteragram.messenger.utils.ui.TextPaint;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Stories.StoriesUtilities;

/* loaded from: classes6.dex */
public class ExpiredStoryView {
    int height;
    float horizontalPadding;
    StaticLayout subtitleLayout;
    float textX;
    float textY;
    StaticLayout titleLayout;
    float verticalPadding;
    public boolean visible;
    int width;

    public ExpiredStoryView() {
    }

    public void draw(Canvas r9, ChatMessageCell r10) {
        float r0 = AndroidUtilities.dp(8.0f) + this.verticalPadding;
        this.textY = r0;
        if (r10.pinnedTop == false) goto L5;
        this.textY = r0 - AndroidUtilities.dp(2.0f);
    L5:
        RectF r02 = AndroidUtilities.rectTmp;
        if (r10.getMessageObject().isOutOwner() == false) goto L9;
        this.textX = (((((-(r10.timeWidth + AndroidUtilities.dp(12.0f))) + r10.getExtraTextX()) + r10.getMeasuredWidth()) - this.width) + AndroidUtilities.dp(24.0f)) - this.horizontalPadding;
        r02.set((r10.getMeasuredWidth() - this.width) - this.horizontalPadding, this.verticalPadding, r10.getMeasuredWidth() - this.horizontalPadding, r10.getMeasuredHeight() - this.verticalPadding);
    L14:
        if (r10.getMessageObject().isOutOwner() == false) goto L16;
        Theme.chat_replyTextPaint.setColor(r10.getThemedColor(Theme.key_chat_outReplyNameText));
    L17:
        r9.save();
        r9.translate(this.textX, this.textY);
        StaticLayout r102 = this.titleLayout;
        if (r102 == null) goto L20;
        r102.draw(r9);
        r9.translate(0.0f, this.titleLayout.getHeight() + AndroidUtilities.dp(2.0f));
    L20:
        StaticLayout r8 = this.subtitleLayout;
        if (r8 == null) goto L23;
        r8.draw(r9);
    L23:
        r9.restore();
        return;
    L16:
        Theme.chat_replyTextPaint.setColor(r10.getThemedColor(Theme.key_chat_inReplyNameText));
        goto L17
    L9:
        if (r10.isAvatarVisible == false) goto L11;
        float r1 = AndroidUtilities.dp(48.0f);
    L12:
        this.textX = (this.horizontalPadding + r1) + AndroidUtilities.dp(12.0f);
        float r4 = this.horizontalPadding;
        r02.set(r1 + r4, this.verticalPadding, (r1 + r4) + this.width, r10.getMeasuredHeight() - this.verticalPadding);
        goto L14
    L11:
        r1 = 0.0f;
        goto L12
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void measure(ChatMessageCell r22) {
        CharSequence r2 = StoriesUtilities.createExpiredStoryString();
        MessageObject r1 = r22.getMessageObject();
        if (r1 == null) goto L26;
        TLRPC.Message r12 = r1.messageOwner;
        if (r12 == null) goto L26;
        TLRPC.MessageMedia r13 = r12.media;
        if ((r13 instanceof TLRPC.TL_messageMediaStory) == false) goto L26;
        TLRPC.User r14 = MessagesController.getInstance(r22.currentAccount).getUser(Long.valueOf(((TLRPC.TL_messageMediaStory) r13).user_id));
        if (r14 != null) goto L11;
        String r15 = "DELETED";
    L13:
        if (AndroidUtilities.isTablet() == false) goto L16;
        int r3 = AndroidUtilities.getMinTabletSide();
    L15:
        int r32 = (int) (r3 * 0.4f);
        String r4 = LocaleController.getString(R.string.From);
        TextPaint r5 = Theme.chat_forwardNamePaint;
        int r42 = (int) Math.ceil(r5.measureText(r4 + " "));
        if (r15 != null) goto L20;
        r15 = _UrlKt.FRAGMENT_ENCODE_SET;
    L20:
        String r16 = (String) TextUtils.ellipsize(r15.replace('\n', ' '), Theme.chat_replyNamePaint, r32 - r42, TextUtils.TruncateAt.END);
        String r33 = LocaleController.getString(R.string.FromFormatted);
        int r43 = r33.indexOf("%1$s");
        String r34 = String.format(r33, new Object[]{r16});
        if (r43 < 0) goto L23;
        SpannableStringBuilder r52 = new SpannableStringBuilder(r34);
        r52.setSpan(new TypefaceSpan(AndroidUtilities.bold()), r43, r16.length() + r43, 33);
        String r142 = r52;
    L24:
        TextPaint r152 = Theme.chat_replyTextPaint;
        int r44 = AndroidUtilities.dp(10.0f) + ((int) (r152.measureText(r2, 0, r2.length()) + 1.0f));
        Layout.Alignment r17 = Layout.Alignment.ALIGN_NORMAL;
        this.titleLayout = new StaticLayout(r2, r152, r44, r17, 1.0f, 0.0f, false);
        this.subtitleLayout = new StaticLayout(r142, r152, ((int) (r152.measureText(r142, 0, r142.length()) + 1.0f)) + AndroidUtilities.dp(10.0f), r17, 1.0f, 0.0f, false);
        this.height = 0;
        this.verticalPadding = AndroidUtilities.dp(4.0f);
        this.horizontalPadding = AndroidUtilities.dp(12.0f);
        this.height = (int) (this.height + (((((AndroidUtilities.dp(4.0f) + this.titleLayout.getHeight()) + AndroidUtilities.dp(2.0f)) + this.subtitleLayout.getHeight()) + AndroidUtilities.dp(4.0f)) + (this.verticalPadding * 2.0f)));
        this.width = ((Math.max(this.titleLayout.getWidth(), this.subtitleLayout.getWidth()) + AndroidUtilities.dp(12.0f)) + AndroidUtilities.dp(20.0f)) + r22.getExtraTextX();
        return;
    L23:
        r142 = r34;
        goto L24
    L16:
        r3 = r22.getParentWidth();
        goto L15
    L11:
        r15 = r14.first_name;
    L26:
        this.verticalPadding = AndroidUtilities.dp(4.0f);
        this.horizontalPadding = AndroidUtilities.dp(12.0f);
        this.height = 0;
        this.width = 0;
    }
}
