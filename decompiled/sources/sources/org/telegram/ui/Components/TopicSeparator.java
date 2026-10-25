package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.Forum.ForumUtilities;

/* loaded from: classes7.dex */
public class TopicSeparator {
    private final Paint arrowPaint;
    private final Path arrowPath;
    public final AvatarDrawable avatarDrawable;
    private final ButtonBounce bounce;
    private final View cell;
    private final RectF clickBounds;
    private final int currentAccount;
    public AnimatedEmojiDrawable emojiImage;
    public final ImageReceiver image;
    private Runnable onClickListener;
    private final Path path;
    private int pathParentWidth;
    private int pathWidth;
    private boolean pathWithCenter;
    private boolean pathWithDots;
    private final Theme.ResourcesProvider resourcesProvider;
    public Text text;
    public long topicId;
    private final boolean withDots;

    public static class Cell extends View {
        private int backgroundHeight;
        private Utilities.Callback<Long> onClickListener;
        private Theme.ResourcesProvider resourceProvider;
        public final TopicSeparator separator;

        public static /* synthetic */ void $r8$lambda$F8v7rcmfIfWuRl3SvIB2yHH_E9A(Cell r0) {
            r0.lambda$new$0();
        }

        public Cell(Context r2, int r3, Theme.ResourcesProvider r4) {
            super(r2);
            this.resourceProvider = r4;
            TopicSeparator r22 = new TopicSeparator(r3, this, r4, false);
            this.separator = r22;
            r22.setOnClickListener(new TopicSeparator$Cell$$ExternalSyntheticLambda0(this));
        }

        private /* synthetic */ void lambda$new$0() {
            Utilities.Callback<Long> r0 = this.onClickListener;
            if (r0 == null) goto L6;
            r0.run(Long.valueOf(this.separator.topicId));
            return;
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r12) {
            super.dispatchDraw(r12);
            Theme.ResourcesProvider r0 = this.resourceProvider;
            if (r0 == null) goto L5;
            r0.applyServiceShaderMatrix(getMeasuredWidth(), this.backgroundHeight, 0.0f, 0.0f);
        L6:
            this.separator.draw(r12, getWidth(), 0.0f, 0.0f, 0.75f, 1.0f, true);
            return;
        L5:
            Theme.applyServiceShaderMatrix(getMeasuredWidth(), this.backgroundHeight, 0.0f, 0.0f);
            goto L6
        }

        @Override // android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.separator.attach();
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.separator.detach();
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(33.0f), TLObject.FLAG_30));
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r3) {
            if (this.separator.onTouchEvent(r3, false) == false) goto L5;
            return true;
        L5:
            if (super.onTouchEvent(r3) == true) goto L10;
            return false;
        L10:
            return true;
        }

        public void set(MessageObject r2) {
            this.separator.update(r2);
            if (isAttachedToWindow() == false) goto L6;
            this.separator.attach();
            return;
        }

        public void setBackgroundHeight(int r1) {
            this.backgroundHeight = r1;
        }

        public void setOnTopicClickListener(Utilities.Callback<Long> r1) {
            this.onClickListener = r1;
        }
    }

    public TopicSeparator(int r4, View r5, Theme.ResourcesProvider r6, boolean r7) {
        this.avatarDrawable = new AvatarDrawable();
        this.path = new Path();
        Paint r0 = new Paint(1);
        this.arrowPaint = r0;
        Path r1 = new Path();
        this.arrowPath = r1;
        this.clickBounds = new RectF();
        this.currentAccount = r4;
        this.cell = r5;
        this.resourcesProvider = r6;
        this.withDots = r7;
        this.bounce = new ButtonBounce(r5);
        this.image = new ImageReceiver(r5);
        r1.rewind();
        r1.moveTo(-AndroidUtilities.dp(1.75f), -AndroidUtilities.dp(4.0f));
        r1.lineTo(AndroidUtilities.dp(1.75f), 0.0f);
        r1.lineTo(-AndroidUtilities.dp(1.75f), AndroidUtilities.dp(4.0f));
        r0.setStyle(Paint.Style.STROKE);
        r0.setStrokeJoin(Paint.Join.ROUND);
        r0.setStrokeCap(Paint.Cap.ROUND);
    }

    public void attach() {
        this.image.onAttachedToWindow();
        AnimatedEmojiDrawable r0 = this.emojiImage;
        if (r0 == null) goto L6;
        r0.addView(this.cell);
        return;
    }

    public void detach() {
        this.image.onDetachedFromWindow();
        AnimatedEmojiDrawable r0 = this.emojiImage;
        if (r0 == null) goto L6;
        r0.removeView(this.cell);
        return;
    }

    public void draw(Canvas r25, int r26, float r27, float r28, float r29, float r30, boolean r31) {
        Text r4 = this.text;
        if (r4 == null) goto L49;
        r4.ellipsize(r26 - AndroidUtilities.dp(144.66f));
        float r42 = AndroidUtilities.dp(48.66f) + this.text.getWidth();
        float r5 = r26;
        float r9 = 2.0f;
        float r8 = (r5 - r42) / 2.0f;
        int r11 = (int) r42;
        if (this.pathWidth == r11) goto L8;
    L15:
        this.path.rewind();
        RectF r10 = AndroidUtilities.rectTmp;
        r10.set(r8, AndroidUtilities.dp(4.5f), r8 + r42, AndroidUtilities.dp(28.5f));
        if (r31 == false) goto L19;
        this.path.addRoundRect(r10, AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), Path.Direction.CW);
    L19:
        if (this.withDots == false) goto L28;
        float r102 = r5 / 2.0f;
        float r12 = 1.833f;
        float r13 = r102 - AndroidUtilities.dp(1.833f);
    L22:
        if (r13 <= 0.0f) goto L24;
        RectF r14 = AndroidUtilities.rectTmp;
        r14.set(r13 - AndroidUtilities.dp(3.66f), AndroidUtilities.dp(15.5f), r13, AndroidUtilities.dp(17.5f));
        this.path.addRoundRect(r14, AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), Path.Direction.CW);
        r13 = r13 - AndroidUtilities.dp(8.33f);
        r9 = r9;
        r12 = r12;
        r42 = r42;
        goto L22
    L24:
        float r23 = r42;
        float r20 = r9;
        int r43 = AndroidUtilities.dp(r12);
    L25:
        r102 = r102 + r43;
        if (r102 >= r5) goto L29;
        RectF r44 = AndroidUtilities.rectTmp;
        r44.set(r102, AndroidUtilities.dp(15.5f), AndroidUtilities.dp(3.66f) + r102, AndroidUtilities.dp(17.5f));
        this.path.addRoundRect(r44, AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), Path.Direction.CW);
        r43 = AndroidUtilities.dp(8.33f);
    L29:
        this.pathWidth = r11;
        this.pathParentWidth = r26;
        this.pathWithDots = this.withDots;
        this.pathWithCenter = r31;
    L30:
        r25.save();
        float r1 = r27 / r20;
        r25.translate(r1, r28);
        Paint r45 = Theme.getThemePaint("paintChatActionBackground", this.resourcesProvider);
        int r52 = r45.getAlpha();
        r45.setAlpha((int) ((r52 * r30) * r29));
        r25.drawPath(this.path, r45);
        r45.setAlpha(r52);
        Theme.ResourcesProvider r46 = this.resourcesProvider;
        if (r46 != null) goto L36;
        if (Theme.hasGradientService() == true) goto L37;
    L38:
        r25.restore();
        float r15 = r1 + r8;
        float r103 = r15 + r23;
        this.clickBounds.set(r15 - AndroidUtilities.dp(4.0f), r28 - AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f) + r103, AndroidUtilities.dp(32.0f) + r28);
        if (r31 == false) goto L48;
        AnimatedEmojiDrawable r3 = this.emojiImage;
        if (r3 == null) goto L43;
        r3.setBounds((int) (AndroidUtilities.dp(2.66f) + r15), (int) (AndroidUtilities.dp(6.5f) + r28), (int) (AndroidUtilities.dp(22.66f) + r15), (int) (AndroidUtilities.dp(26.5f) + r28));
        this.emojiImage.setAlpha((int) (255.0f * r30));
        this.emojiImage.draw(r25);
    L44:
        int r53 = Theme.getColor(Theme.key_chat_serviceText, this.resourcesProvider);
        this.text.draw(r25, r15 + AndroidUtilities.dp(27.66f), AndroidUtilities.dp(16.5f) + r28, r53, r30);
        r25.save();
        r25.translate(r103 - AndroidUtilities.dp(11.25f), AndroidUtilities.dp(16.5f) + r28);
        this.arrowPaint.setColor(Theme.multAlpha(r53, 0.75f * r30));
        this.arrowPaint.setStrokeWidth(AndroidUtilities.dp(1.66f));
        r25.drawPath(this.arrowPath, this.arrowPaint);
        r25.restore();
        return;
    L43:
        this.image.setImageCoords(AndroidUtilities.dp(2.66f) + r15, AndroidUtilities.dp(6.5f) + r28, AndroidUtilities.dp(20.0f), AndroidUtilities.dp(20.0f));
        this.image.setAlpha(r30);
        this.image.draw(r25);
        goto L44
    L48:
        return;
    L37:
        Paint r47 = Theme.getThemePaint("paintChatActionBackgroundDarken", this.resourcesProvider);
        int r54 = r47.getAlpha();
        r47.setAlpha((int) ((r54 * r30) * r29));
        r25.drawPath(this.path, r47);
        r47.setAlpha(r54);
        goto L38
    L36:
        if (r46.hasGradientService() == false) goto L38;
    L28:
        r23 = r42;
        r20 = 2.0f;
        goto L29
    L8:
        if (this.pathParentWidth != r26) goto L15;
        if (this.pathWithCenter != r31) goto L15;
        if (this.pathWithDots != this.withDots) goto L15;
        r23 = r42;
        r20 = 2.0f;
        goto L30
    }

    public boolean onTouchEvent(MotionEvent r6, boolean r7) {
        if (this.text == null) goto L11;
        RectF r0 = this.clickBounds;
        float r3 = r6.getX();
        float r4 = r6.getY();
        if (r7 == false) goto L7;
        int r72 = this.cell.getPaddingTop();
    L9:
        if (r0.contains(r3, r4 - r72) == false) goto L11;
        boolean r73 = true;
    L13:
        if (r6.getAction() != 0) goto L16;
        this.bounce.setPressed(r73);
    L33:
        return this.bounce.isPressed();
    L16:
        if (r6.getAction() != 2) goto L22;
        if (this.bounce.isPressed() == false) goto L33;
        if (r73 == true) goto L33;
        this.bounce.setPressed(false);
        goto L33
    L22:
        if (r6.getAction() != 1) goto L30;
        if (this.bounce.isPressed() == false) goto L28;
        Runnable r62 = this.onClickListener;
        if (r62 == null) goto L28;
        r62.run();
    L28:
        this.bounce.setPressed(false);
        goto L33
    L30:
        if (r6.getAction() != 3) goto L33;
        this.bounce.setPressed(false);
        goto L33
    L7:
        r72 = 0;
    L11:
        r73 = false;
        goto L13
    }

    public void setOnClickListener(Runnable r1) {
        this.onClickListener = r1;
    }

    public void setText(String r4) {
        this.text = new Text(r4, 14.0f, AndroidUtilities.bold());
    }

    public boolean update(MessageObject r11) {
        AnimatedEmojiDrawable r0 = this.emojiImage;
        if (r0 == null) goto L5;
        r0.removeView(this.cell);
        this.emojiImage = null;
    L5:
        this.pathWidth = 0;
        this.topicId = 0;
        if (r11 != null) goto L8;
        this.text = null;
        this.topicId = 0;
    L28:
        if (this.text == null) goto L31;
        return true;
    L31:
        return false;
    L8:
        boolean r4 = ChatObject.isMonoForum(MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r11.getDialogId())));
        ImageReceiver r5 = this.image;
        if (r4 == false) goto L15;
        r5.setRoundRadius(AndroidUtilities.dp(10.0f));
        long r2 = r11.getMonoForumTopicId();
        TLObject r112 = MessagesController.getInstance(this.currentAccount).getUserOrChat(r2);
        this.topicId = r2;
        if (r112 != null) goto L14;
        this.text = null;
        return false;
    L14:
        this.avatarDrawable.setInfo(r112);
        this.image.setForUserOrChat(r112, this.avatarDrawable);
        this.text = new Text(DialogObject.getName(r112), 14.0f, AndroidUtilities.bold());
        goto L28
    L15:
        r5.setRoundRadius(0);
        long r42 = r11.getTopicId();
        this.topicId = r42;
        TLRPC.TL_forumTopic r113 = MessagesController.getInstance(this.currentAccount).getTopicsController().findTopic(-r11.getDialogId(), r42);
        if (r113 != null) goto L20;
        this.text = null;
        return false;
    L20:
        if (r42 != 1) goto L23;
        this.image.setImageBitmap(ForumUtilities.createGeneralTopicDrawable(this.cell.getContext(), 0.75f, Theme.getColor(Theme.key_actionBarDefaultIcon, this.resourcesProvider), false, false));
    L26:
        this.text = new Text(r113.title, 14.0f, AndroidUtilities.bold());
        goto L28
    L23:
        if (r113.icon_emoji_id == 0) goto L25;
        this.emojiImage = new AnimatedEmojiDrawable(0, this.currentAccount, r113.icon_emoji_id);
        this.image.onDetachedFromWindow();
        this.emojiImage.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
        goto L26
    L25:
        this.image.setImageBitmap(ForumUtilities.createTopicDrawable(r113, false));
        goto L26
    }
}
