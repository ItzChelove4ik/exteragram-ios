package org.telegram.ui.Cells;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.graphics.ColorUtils;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LoadingDrawable;
import org.telegram.ui.Components.Scroller;
import org.telegram.ui.Components.Text;

/* loaded from: classes6.dex */
public class ChannelRecommendationsCell {
    private final RectF backgroundBounds;
    private final Paint backgroundPaint;
    private final Path backgroundPath;
    private int blockWidth;
    private ChatMessageCell cell;
    private final ArrayList<ChannelBlock> channels;
    private float channelsScrollWidth;
    public long chatId;
    private final ButtonBounce closeBounce;
    private final RectF closeBounds;
    private final Paint closePaint;
    private int currentAccount;
    private TLRPC.Chat currentChat;
    private long dialogId;
    private Text headerText;
    private float lastBackgroundPathExpandT;
    private boolean loading;
    private final AnimatedFloat loadingAlpha;
    private LoadingDrawable loadingDrawable;
    private final Path loadingPath;
    private Runnable longPressRunnable;
    private ChannelBlock longPressedBlock;
    private float lx;
    private float ly;
    private boolean maybeScrolling;
    private MessageObject msg;
    private float scrollX;
    private final Scroller scroller;
    private boolean scrolling;
    private StaticLayout serviceText;
    private int serviceTextHeight;
    private float serviceTextLeft;
    private final TextPaint serviceTextPaint;
    private float serviceTextRight;
    private VelocityTracker velocityTracker;

    public static class ChannelBlock {
        public final AvatarDrawable[] avatarDrawable;
        public final ImageReceiver[] avatarImageReceiver;
        public final ButtonBounce bounce;
        private final ChatMessageCell cell;
        public final TLObject chat;
        public final boolean isLock;
        private final CharSequence name;
        private StaticLayout nameText;
        private final TextPaint nameTextPaint;
        private final Paint subscribersBackgroundDimPaint;
        private final Paint subscribersBackgroundPaint;
        private int subscribersBackgroundPaintBitmapHeight;
        private int subscribersBackgroundPaintBitmapWidth;
        private Matrix subscribersBackgroundPaintMatrix;
        private BitmapShader subscribersBackgroundPaintShader;
        private boolean subscribersColorSet;
        private boolean subscribersColorSetFromThumb;
        private final Drawable subscribersDrawable;
        private final Paint subscribersStrokePaint;
        private final Text subscribersText;

        /* renamed from: org.telegram.ui.Cells.ChannelRecommendationsCell$ChannelBlock$1, reason: invalid class name */
        public class AnonymousClass1 extends ButtonBounce {
            final /* synthetic */ ChannelBlock this$0;
            final /* synthetic */ ChatMessageCell val$cell;

            public AnonymousClass1(ChannelBlock r1, View r2, ChatMessageCell r3) {
                this.this$0 = r1;
                this.val$cell = r3;
                super(r2);
            }

            @Override // org.telegram.ui.Components.ButtonBounce
            public void invalidate() {
                this.val$cell.invalidateOutbounds();
            }
        }

        /* renamed from: org.telegram.ui.Cells.ChannelRecommendationsCell$ChannelBlock$2, reason: invalid class name */
        public class AnonymousClass2 extends Drawable {
            final /* synthetic */ ChannelBlock this$0;
            final /* synthetic */ int val$color;
            final /* synthetic */ Paint val$paint;

            public AnonymousClass2(ChannelBlock r1, Paint r2, int r3) {
                this.this$0 = r1;
                this.val$paint = r2;
                this.val$color = r3;
            }

            @Override // android.graphics.drawable.Drawable
            public void draw(Canvas r5) {
                r5.drawCircle(getBounds().centerX(), getBounds().centerY(), getBounds().width() / 2.0f, this.val$paint);
            }

            @Override // android.graphics.drawable.Drawable
            public int getOpacity() {
                return -2;
            }

            @Override // android.graphics.drawable.Drawable
            public void setAlpha(int r3) {
                this.val$paint.setAlpha(Theme.multAlpha(this.val$color, r3 / 255.0f));
            }

            @Override // android.graphics.drawable.Drawable
            public void setColorFilter(ColorFilter r1) {
                this.val$paint.setColorFilter(r1);
            }
        }

        /* renamed from: org.telegram.ui.Cells.ChannelRecommendationsCell$ChannelBlock$3, reason: invalid class name */
        public class AnonymousClass3 extends ButtonBounce {
            final /* synthetic */ ChannelBlock this$0;
            final /* synthetic */ ChatMessageCell val$cell;

            public AnonymousClass3(ChannelBlock r1, View r2, ChatMessageCell r3) {
                this.this$0 = r1;
                this.val$cell = r3;
                super(r2);
            }

            @Override // org.telegram.ui.Components.ButtonBounce
            public void invalidate() {
                this.val$cell.invalidateOutbounds();
            }
        }

        public ChannelBlock(int r7, ChatMessageCell r8, TLObject r9) {
            TextPaint r0 = new TextPaint(1);
            this.nameTextPaint = r0;
            this.subscribersStrokePaint = new Paint(1);
            this.subscribersBackgroundPaint = new Paint(1);
            this.subscribersBackgroundDimPaint = new Paint(1);
            this.cell = r8;
            this.chat = r9;
            this.bounce = new AnonymousClass3(this, r8, r8);
            ImageReceiver[] r2 = {r3};
            this.avatarImageReceiver = r2;
            ImageReceiver r3 = new ImageReceiver(r8);
            r3.setParentView(r8);
            r2[0].setRoundRadius(avatarSize());
            if (r8.isCellAttachedToWindow() == false) goto L5;
            attach();
        L5:
            AvatarDrawable[] r1 = {r3};
            this.avatarDrawable = r1;
            AvatarDrawable r32 = new AvatarDrawable();
            r32.setInfo(r7, r9);
            r2[0].setForUserOrChat(r9, r1[0]);
            r0.setTextSize(AndroidUtilities.dp(11.0f));
            if ((r9 instanceof TLRPC.Chat) == false) goto L9;
            CharSequence r72 = ((TLRPC.Chat) r9).title;
        L19:
            r72 = Emoji.replaceEmoji(r72, r0.getFontMetricsInt(), false);     // Catch: Exception -> L18
        L13:
            this.name = r72;
            this.subscribersStrokePaint.setStyle(Paint.Style.STROKE);
            this.isLock = false;
            this.subscribersDrawable = r8.getContext().getResources().getDrawable(R.drawable.mini_reply_user).mutate();
            if (getSubscribersCount(r9) != null) goto L16;
            this.subscribersText = null;
            return;
        L16:
            this.subscribersText = new Text(getSubscribersCount(r9), 9.33f, AndroidUtilities.bold());
            return;
        L9:
            if ((r9 instanceof TLRPC.User) == false) goto L11;
            r72 = UserObject.getUserName((TLRPC.User) r9);
            goto L19
        L11:
            r72 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L19
        }

        public ChannelBlock(int r8, ChatMessageCell r9, TLObject[] r10, int r11) {
            this.nameTextPaint = new TextPaint(1);
            this.subscribersStrokePaint = new Paint(1);
            this.subscribersBackgroundPaint = new Paint(1);
            this.subscribersBackgroundDimPaint = new Paint(1);
            this.cell = r9;
            int r0 = 0;
            this.chat = r10[0];
            this.bounce = new AnonymousClass1(this, r9, r9);
            this.avatarImageReceiver = new ImageReceiver[3];
            this.avatarDrawable = new AvatarDrawable[3];
        L3:
            if (r0 >= 3) goto L12;
            this.avatarImageReceiver[r0] = new ImageReceiver(r9);
            this.avatarImageReceiver[r0].setParentView(r9);
            this.avatarImageReceiver[r0].setRoundRadius(avatarSize());
            this.avatarDrawable[r0] = new AvatarDrawable();
            if (r0 >= r10.length) goto L9;
            TLObject r3 = r10[r0];
            if (r3 == null) goto L9;
            this.avatarDrawable[r0].setInfo(r8, r3);
            this.avatarImageReceiver[r0].setForUserOrChat(r10[r0], this.avatarDrawable[r0]);
        L10:
            r0 = r0 + 1;
        L9:
            Paint r32 = new Paint(1);
            int r4 = Theme.blendOver(r9.getThemedColor(Theme.key_chat_inBubble), Theme.multAlpha(r9.getThemedColor(Theme.key_windowBackgroundWhiteGrayText), 0.5f));
            r32.setColor(r4);
            this.avatarImageReceiver[r0].setImageBitmap(new AnonymousClass2(this, r32, r4));
            goto L10
        L12:
            if (r9.isCellAttachedToWindow() == false) goto L14;
            attach();
        L14:
            this.nameTextPaint.setTextSize(AndroidUtilities.dp(11.0f));
            boolean r82 = UserConfig.getInstance(r9.currentAccount).isPremium();
            if (r82 == false) goto L17;
            int r102 = R.string.MoreSimilar;
        L18:
            this.name = LocaleController.getString(r102);
            this.subscribersStrokePaint.setStyle(Paint.Style.STROKE);
            this.isLock = true;
            if (r82 == false) goto L21;
            Drawable r83 = null;
        L22:
            this.subscribersDrawable = r83;
            if (getSubscribersCount(this.chat) != null) goto L26;
            this.subscribersText = null;
            return;
        L26:
            this.subscribersText = new Text("+" + r11, 9.33f, AndroidUtilities.bold());
            return;
        L21:
            r83 = r9.getContext().getResources().getDrawable(R.drawable.mini_switch_lock).mutate();
            goto L22
        L17:
            r102 = R.string.UnlockSimilar;
            goto L18
        }

        public static int avatarSize() {
            return AndroidUtilities.dp(54.0f);
        }

        private void checkNameText(int r5) {
            StaticLayout r0 = this.nameText;
            if (r0 != null) goto L5;
        L7:
            CharSequence r02 = this.name;
            this.nameText = StaticLayout.Builder.obtain(r02, 0, r02.length(), this.nameTextPaint, r5).setMaxLines(2).setEllipsize(TextUtils.TruncateAt.END).setBreakStrategy(0).setAlignment(Layout.Alignment.ALIGN_CENTER).build();
            return;
        L5:
            if (r0.getWidth() != r5) goto L7;
        }

        public static void fillPath(Path r7, int r8, float r9) {
            float r82 = r8;
            Path.Direction r4 = Path.Direction.CW;
            r7.addCircle((r82 / 2.0f) + r9, AndroidUtilities.dp(10.0f) + (avatarSize() / 2.0f), avatarSize() / 2.0f, r4);
            float r1 = 0.4f * r82;
            RectF r2 = AndroidUtilities.rectTmp;
            r2.set(((r82 - r1) / 2.0f) + r9, AndroidUtilities.dp(69.0f), ((r1 + r82) / 2.0f) + r9, AndroidUtilities.dp(79.0f));
            r7.addRoundRect(r2, AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), r4);
            float r12 = 0.35f * r82;
            r2.set(((r82 - r12) / 2.0f) + r9, AndroidUtilities.dp(83.0f), r9 + ((r82 + r12) / 2.0f), AndroidUtilities.dp(91.0f));
            r7.addRoundRect(r2, AndroidUtilities.dp(2.5f), AndroidUtilities.dp(2.5f), r4);
        }

        private String getSubscribersCount(TLObject r3) {
            if ((r3 instanceof TLRPC.Chat) == false) goto L10;
            int r2 = ((TLRPC.Chat) r3).participants_count;
            if (r2 > 1) goto L8;
            return null;
        L8:
            return LocaleController.formatShortNumber(r2, null);
        L10:
            if ((r3 instanceof TLRPC.User) == false) goto L16;
            int r22 = ((TLRPC.User) r3).bot_active_users;
            if (r22 > 1) goto L15;
            return null;
        L15:
            return LocaleController.formatShortNumber(r22, null);
        L16:
            return null;
        }

        public static int height() {
            return AndroidUtilities.dp(99.0f);
        }

        public void attach() {
            int r0 = 0;
        L3:
            ImageReceiver[] r1 = this.avatarImageReceiver;
            if (r0 >= r1.length) goto L6;
            r1[r0].onAttachedToWindow();
            r0 = r0 + 1;
            goto L3
        }

        public void detach() {
            int r0 = 0;
        L3:
            ImageReceiver[] r1 = this.avatarImageReceiver;
            if (r0 >= r1.length) goto L6;
            r1[r0].onDetachedFromWindow();
            r0 = r0 + 1;
            goto L3
        }

        public void draw(Canvas r12, int r13, float r14) {
            r12.save();
            float r0 = this.bounce.getScale(0.075f);
            float r1 = r13;
            float r3 = r1 / 2.0f;
            r12.scale(r0, r0, r3, height() / 2.0f);
            this.subscribersStrokePaint.setStrokeWidth(AndroidUtilities.dp(2.66f));
            this.subscribersStrokePaint.setColor(this.cell.getThemedColor(Theme.key_chat_inBubble));
            int r02 = this.avatarImageReceiver.length - 1;
        L4:
            if (r02 < 0) goto L9;
            float r7 = (r3 - ((AndroidUtilities.dp(7.0f) * (this.avatarImageReceiver.length - 1)) / 2.0f)) + (AndroidUtilities.dp(7.0f) * r02);
            float r5 = AndroidUtilities.dp(10.0f) + (avatarSize() / 2.0f);
            if (this.avatarImageReceiver.length <= 1) goto L8;
            r12.drawCircle(r7, r5, avatarSize() / 2.0f, this.subscribersStrokePaint);
        L8:
            this.avatarImageReceiver[r02].setImageCoords(r7 - (avatarSize() / 2.0f), r5 - (avatarSize() / 2.0f), avatarSize(), avatarSize());
            this.avatarImageReceiver[r02].setAlpha(r14);
            this.avatarImageReceiver[r02].draw(r12);
            r02 = r02 - 1;
            goto L4
        L9:
            Text r142 = this.subscribersText;
            if (r142 == null) goto L72;
            r142.ellipsize(r13 - AndroidUtilities.dp(32.0f));
            if (this.subscribersDrawable == null) goto L14;
            float r132 = 17.0f;
        L15:
            float r133 = AndroidUtilities.dp(r132) + this.subscribersText.getWidth();
            float r03 = (AndroidUtilities.dp(10.0f) + avatarSize()) + AndroidUtilities.dp(1.0f);
            AndroidUtilities.rectTmp.set((r1 - r133) / 2.0f, r03 - AndroidUtilities.dp(14.33f), (r1 + r133) / 2.0f, r03);
            boolean r134 = this.subscribersColorSet;
            if (r134 == true) goto L21;
            if (this.isLock == false) goto L21;
            this.subscribersBackgroundPaint.setColor(Theme.blendOver(this.cell.getThemedColor(Theme.key_chat_inBubble), Theme.multAlpha(this.cell.getThemedColor(Theme.key_windowBackgroundWhiteGrayText), 0.85f)));
            this.subscribersColorSet = true;
        L68:
            if (this.subscribersBackgroundPaintShader == null) goto L70;
            this.subscribersBackgroundPaintMatrix.reset();
            this.subscribersBackgroundPaintMatrix.postScale(avatarSize() / this.subscribersBackgroundPaintBitmapWidth, avatarSize() / this.subscribersBackgroundPaintBitmapHeight);
            RectF r04 = AndroidUtilities.rectTmp;
            this.subscribersBackgroundPaintMatrix.postTranslate(r3 - (avatarSize() / 2.0f), r04.bottom - avatarSize());
            this.subscribersBackgroundPaintShader.setLocalMatrix(this.subscribersBackgroundPaintMatrix);
            r12.drawRoundRect(r04, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), this.subscribersBackgroundPaint);
            r12.drawRoundRect(r04, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), this.subscribersBackgroundDimPaint);
        L71:
            RectF r135 = AndroidUtilities.rectTmp;
            r135.inset((-AndroidUtilities.dp(1.0f)) / 2.0f, (-AndroidUtilities.dp(1.0f)) / 2.0f);
            this.subscribersStrokePaint.setStrokeWidth(AndroidUtilities.dp(1.0f));
            r12.drawRoundRect(r135, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), this.subscribersStrokePaint);
            goto L72
        L70:
            r12.drawRoundRect(AndroidUtilities.rectTmp, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), this.subscribersBackgroundPaint);
        L21:
            if (r134 == true) goto L51;
            if ((this.avatarImageReceiver[0].getStaticThumb() instanceof BitmapDrawable) == false) goto L51;
            Bitmap r136 = ((BitmapDrawable) this.avatarImageReceiver[0].getStaticThumb()).getBitmap();
            float[] r72 = new float[3];     // Catch: Exception -> L39
            ColorUtils.colorToHSL(r136.getPixel(r136.getWidth() / 2, r136.getHeight() - 2), r72);     // Catch: Exception -> L39
            float r137 = r72[1];     // Catch: Exception -> L39
            if (r137 > 0.05f) goto L28;
        L41:
            r72[1] = 0.0f;     // Catch: Exception -> L39
            if (Theme.isCurrentThemeDark() == false) goto L44;
            float r138 = 0.38f;
        L45:
            r72[2] = r138;     // Catch: Exception -> L39
        L46:
            this.subscribersBackgroundPaint.setColor(ColorUtils.HSLToColor(r72));     // Catch: Exception -> L39
        L49:
            this.subscribersColorSet = true;
            goto L68
        L44:
            r138 = 0.7f;
            goto L45
        L28:
            if (r137 >= 0.95f) goto L41;
            float r139 = r72[2];     // Catch: Exception -> L39
            if (r139 <= 0.02f) goto L41;
            if (r139 >= 0.98f) goto L41;
            r72[1] = 0.25f;     // Catch: Exception -> L39
            if (Theme.isCurrentThemeDark() == false) goto L37;
            float r1310 = 0.35f;
        L38:
            r72[2] = r1310;     // Catch: Exception -> L39
            goto L46
        L37:
            r1310 = 0.65f;
        L39:
            e = move-exception;
            FileLog.e(e);
        L51:
            if (this.subscribersColorSet == true) goto L68;
            if (this.subscribersColorSetFromThumb == true) goto L68;
            float[] r73 = new float[3];     // Catch: Exception -> L60
            ColorUtils.colorToHSL(ColorUtils.blendARGB(this.avatarDrawable[0].getColor(), this.avatarDrawable[0].getColor2(), 0.5f), r73);     // Catch: Exception -> L60
            float r1311 = r73[1];     // Catch: Exception -> L60
            if (r1311 > 0.05f) goto L57;
        L62:
            r73[2] = Utilities.clamp(r73[2] - 0.1f, 0.6f, 0.3f);     // Catch: Exception -> L60
        L63:
            this.subscribersBackgroundPaint.setColor(ColorUtils.HSLToColor(r73));     // Catch: Exception -> L60
        L66:
            this.subscribersColorSetFromThumb = true;
            goto L68
        L57:
            if (r1311 >= 0.95f) goto L62;
            r73[1] = Utilities.clamp(r1311 - 0.06f, 0.4f, 0.0f);     // Catch: Exception -> L60
            r73[2] = Utilities.clamp(r73[2] - 0.08f, 0.5f, 0.2f);     // Catch: Exception -> L60
        L60:
            e = move-exception;
            FileLog.e(e);
            goto L66
        L14:
            r132 = 8.0f;
        L72:
            r12.restore();
        }

        public void drawText(Canvas r11, int r12, float r13) {
            r11.save();
            float r0 = this.bounce.getScale(0.075f);
            float r1 = r12;
            r11.scale(r0, r0, r1 / 2.0f, height() / 2.0f);
            checkNameText(r12);
            if (this.nameText == null) goto L9;
            r11.save();
            r11.translate((r12 - this.nameText.getWidth()) / 2.0f, AndroidUtilities.dp(66.33f));
            int r02 = this.avatarImageReceiver.length;
            TextPaint r3 = this.nameTextPaint;
            if (r02 > 1) goto L7;
            r3.setColor(this.cell.getThemedColor(Theme.key_chat_messageTextIn));
        L8:
            this.nameTextPaint.setAlpha((int) (r0.getAlpha() * r13));
            this.nameText.draw(r11);
            r11.restore();
            goto L9
        L7:
            r3.setColor(this.cell.getThemedColor(Theme.key_windowBackgroundWhiteGrayText));
        L9:
            Text r03 = this.subscribersText;
            if (r03 == null) goto L30;
            r03.ellipsize(r12 - AndroidUtilities.dp(32.0f));
            if (this.subscribersDrawable == null) goto L14;
            float r122 = 17.0f;
        L15:
            float r14 = (r1 - (AndroidUtilities.dp(r122) + this.subscribersText.getWidth())) / 2.0f;
            float r6 = AndroidUtilities.dp(4.165f) + avatarSize();
            Drawable r123 = this.subscribersDrawable;
            if (r123 == null) goto L25;
            float r32 = 0.0f;
            if (this.isLock == false) goto L20;
            float r04 = this.subscribersText.getWidth() + AndroidUtilities.dp(1.33f);
        L21:
            int r05 = (int) ((r04 + r14) + AndroidUtilities.dp(3.0f));
            int r7 = (int) (r6 - ((this.subscribersDrawable.getIntrinsicHeight() / 2.0f) * 0.625f));
            if (this.isLock == false) goto L24;
            r32 = this.subscribersText.getWidth() + AndroidUtilities.dp(1.33f);
        L24:
            r123.setBounds(r05, r7, (int) (((r32 + r14) + AndroidUtilities.dp(3.0f)) + (this.subscribersDrawable.getIntrinsicWidth() * 0.625f)), (int) (((this.subscribersDrawable.getIntrinsicHeight() / 2.0f) * 0.625f) + r6));
            this.subscribersDrawable.draw(r11);
            goto L25
        L20:
            r04 = 0.0f;
        L25:
            Text r33 = this.subscribersText;
            if (this.isLock == true) goto L28;
            float r10 = 12.66f;
        L29:
            Canvas r4 = r11;
            r33.draw(r4, r14 + AndroidUtilities.dp(r10), r6, -1, r13);
        L31:
            r4.restore();
            return;
        L28:
            r10 = 4.0f;
            goto L29
        L14:
            r122 = 8.0f;
            goto L15
        L30:
            r4 = r11;
            goto L31
        }
    }

    public static /* synthetic */ void $r8$lambda$dWepEwHkaSmz6sxts0x5Is_qtsY(ChannelRecommendationsCell r0, ChannelBlock r1) {
        r0.lambda$checkTouchEvent$0(r1);
    }

    public ChannelRecommendationsCell(ChatMessageCell r5) {
        this.serviceTextPaint = new TextPaint(1);
        this.backgroundPaint = new Paint(1);
        this.backgroundPath = new Path();
        this.lastBackgroundPathExpandT = -1.0f;
        this.blockWidth = AndroidUtilities.dp(66.0f);
        this.channels = new ArrayList();
        this.loadingPath = new Path();
        this.backgroundBounds = new RectF();
        this.closeBounds = new RectF();
        this.closePaint = new Paint(1);
        this.cell = r5;
        this.scroller = new Scroller(r5.getContext());
        this.closeBounce = new ButtonBounce(r5);
        this.loading = true;
        this.loadingAlpha = new AnimatedFloat(r5, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
    }

    private void checkBackgroundPath(float r7) {
        if (Math.abs(r7 - this.lastBackgroundPathExpandT) >= 0.001f) goto L5;
        return;
    L5:
        float r72 = AndroidUtilities.dp(16.66f) * 2.0f;
        float r0 = this.backgroundBounds.bottom;
        this.backgroundPath.rewind();
        RectF r1 = AndroidUtilities.rectTmp;
        RectF r2 = this.backgroundBounds;
        float r3 = r2.left;
        float r22 = r2.top;
        r1.set(r3, r22, r3 + r72, r22 + r72);
        this.backgroundPath.arcTo(r1, -90.0f, -90.0f);
        float r23 = this.backgroundBounds.left;
        float r4 = r0 - r72;
        r1.set(r23, r4, r23 + r72, r0);
        this.backgroundPath.arcTo(r1, -180.0f, -90.0f);
        float r24 = this.backgroundBounds.right;
        r1.set(r24 - r72, r4, r24, r0);
        this.backgroundPath.arcTo(r1, -270.0f, -90.0f);
        RectF r02 = this.backgroundBounds;
        float r25 = r02.right;
        float r03 = r02.top;
        r1.set(r25 - r72, r03, r25, r72 + r03);
        this.backgroundPath.arcTo(r1, 0.0f, -90.0f);
        this.backgroundPath.lineTo(this.backgroundBounds.centerX() + AndroidUtilities.dp(8.0f), this.backgroundBounds.top);
        this.backgroundPath.lineTo(this.backgroundBounds.centerX(), this.backgroundBounds.top - AndroidUtilities.dp(6.0f));
        this.backgroundPath.lineTo(this.backgroundBounds.centerX() - AndroidUtilities.dp(8.0f), this.backgroundBounds.top);
        this.backgroundPath.close();
    }

    private /* synthetic */ void lambda$checkTouchEvent$0(ChannelBlock r3) {
        ChannelBlock r0 = this.longPressedBlock;
        if (r3 != r0) goto L10;
        r0.bounce.setPressed(false);
        ChannelBlock r32 = this.longPressedBlock;
        if (r32.isLock == true) goto L7;
        didClickChannel(r32.chat, true);
        goto L10
    L7:
        if (this.cell.getDelegate() == null) goto L10;
        this.cell.getDelegate().didPressMoreChannelRecommendations(this.cell);
    L10:
        this.longPressedBlock = null;
        this.longPressRunnable = null;
        this.scrolling = false;
        this.maybeScrolling = false;
        this.closeBounce.setPressed(false);
        VelocityTracker r02 = this.velocityTracker;
        if (r02 == null) goto L14;
        r02.recycle();
        this.velocityTracker = null;
        return;
    }

    private void scroll(float r4) {
        this.scrollX = Utilities.clamp(this.scrollX + r4, this.channelsScrollWidth - (this.backgroundBounds.width() - AndroidUtilities.dp(14.0f)), 0.0f);
        this.cell.invalidateOutbounds();
    }

    private void unselectBlocks() {
        int r1 = 0;
    L4:
        if (r1 >= this.channels.size()) goto L6;
        this.channels.get(r1).bounce.setPressed(false);
        r1 = r1 + 1;
        goto L4
    }

    public boolean checkTouchEvent(MotionEvent r18) {
        if (this.msg != null) goto L5;
    L101:
        return false;
    L5:
        if (this.cell == null) goto L101;
        int r2 = r18.getAction();
        float r4 = (this.backgroundBounds.left + AndroidUtilities.dp(7.0f)) - this.scrollX;
        int r5 = 0;
    L9:
        if (r5 >= this.channels.size()) goto L20;
        ChannelBlock r6 = this.channels.get(r5);
        if (r18.getX() < r4) goto L19;
        if (r18.getX() > (this.blockWidth + r4)) goto L19;
        if (r18.getY() < (this.backgroundBounds.bottom - ChannelBlock.height())) goto L19;
        if (r18.getY() >= this.backgroundBounds.bottom) goto L19;
    L21:
        boolean r42 = this.closeBounds.contains(r18.getX(), r18.getY());
        if (r2 != 0) goto L51;
        this.scroller.abortAnimation();
        if (this.loading == true) goto L28;
        RectF r22 = this.backgroundBounds;
        float r8 = r18.getX();
        this.lx = r8;
        float r1 = r18.getY();
        this.ly = r1;
        if (r22.contains(r8, r1) == false) goto L28;
        boolean r12 = true;
    L29:
        this.maybeScrolling = r12;
        if (r12 == true) goto L32;
    L34:
        this.scrolling = false;
        VelocityTracker r13 = this.velocityTracker;
        if (r13 == null) goto L37;
        r13.recycle();
        this.velocityTracker = null;
    L37:
        this.velocityTracker = VelocityTracker.obtain();
        if (r6 == null) goto L40;
        r6.bounce.setPressed(true);
    L40:
        if (r42 == false) goto L42;
        this.closeBounce.setPressed(true);
    L42:
        Runnable r14 = this.longPressRunnable;
        if (r14 == null) goto L45;
        AndroidUtilities.cancelRunOnUIThread(r14);
        this.longPressRunnable = null;
    L45:
        this.longPressedBlock = r6;
        if (r6 == null) goto L49;
        ChannelRecommendationsCell$$ExternalSyntheticLambda0 r15 = new ChannelRecommendationsCell$$ExternalSyntheticLambda0(this, r6);
        this.longPressRunnable = r15;
        AndroidUtilities.runOnUIThread(r15, ViewConfiguration.getLongPressTimeout());
    L49:
        return this.maybeScrolling;
    L32:
        if (this.cell.getParent() == null) goto L34;
        this.cell.getParent().requestDisallowInterceptTouchEvent(true);
    L28:
        r12 = false;
        goto L29
    L51:
        if (r2 != 2) goto L66;
        VelocityTracker r23 = this.velocityTracker;
        if (r23 == null) goto L56;
        r23.addMovement(r18);
    L56:
        if (this.maybeScrolling == false) goto L60;
        if (Math.abs(r18.getX() - this.lx) < AndroidUtilities.touchSlop) goto L60;
    L61:
        Runnable r24 = this.longPressRunnable;
        if (r24 == null) goto L64;
        AndroidUtilities.cancelRunOnUIThread(r24);
        this.longPressRunnable = null;
    L64:
        this.scrolling = true;
        scroll(this.lx - r18.getX());
        this.lx = r18.getX();
        unselectBlocks();
        return true;
    L60:
        if (this.scrolling == true) goto L61;
    L70:
        return false;
    L66:
        if (r2 != 1) goto L68;
    L71:
        Runnable r43 = this.longPressRunnable;
        if (r43 == null) goto L74;
        AndroidUtilities.cancelRunOnUIThread(r43);
        this.longPressRunnable = null;
    L74:
        VelocityTracker r44 = this.velocityTracker;
        if (r44 == null) goto L77;
        r44.addMovement(r18);
    L77:
        boolean r16 = this.scrolling;
        this.scrolling = false;
        if (r2 != 1) goto L96;
        if (r16 == true) goto L89;
        if (r6 == null) goto L89;
        if (r6.bounce.isPressed() == false) goto L89;
        if (r6.isLock == true) goto L86;
        didClickChannel(r6.chat, false);
        goto L96
    L86:
        if (this.cell.getDelegate() == null) goto L96;
        this.cell.getDelegate().didPressMoreChannelRecommendations(this.cell);
    L89:
        if (r16 == false) goto L94;
        VelocityTracker r25 = this.velocityTracker;
        if (r25 == null) goto L94;
        r25.computeCurrentVelocity(MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);
        this.scroller.fling((int) this.scrollX, 0, (int) (-this.velocityTracker.getXVelocity()), 0, -2147483647, Integer.MAX_VALUE, 0, 0);
    L94:
        if (this.closeBounce.isPressed() == false) goto L96;
        didClickClose();
    L96:
        this.closeBounce.setPressed(false);
        this.maybeScrolling = false;
        VelocityTracker r26 = this.velocityTracker;
        if (r26 == null) goto L99;
        r26.recycle();
        this.velocityTracker = null;
    L99:
        unselectBlocks();
        return r16;
    L68:
        if (r2 != 3) goto L70;
    L19:
        r4 = r4 + (this.blockWidth + AndroidUtilities.dp(9.0f));
        r5 = r5 + 1;
        goto L9
    L20:
        r6 = null;
        goto L21
    }

    public void computeScroll() {
        if (this.scroller.computeScrollOffset() == false) goto L6;
        float r0 = this.scroller.getCurrX();
        this.scrollX = r0;
        this.scrollX = Utilities.clamp(r0, this.channelsScrollWidth - (this.backgroundBounds.width() - AndroidUtilities.dp(14.0f)), 0.0f);
        this.cell.invalidateOutbounds();
        return;
    }

    public void didClickChannel(TLObject r2, boolean r3) {
        if (this.cell.getDelegate() == null) goto L6;
        this.cell.getDelegate().didPressChannelRecommendation(this.cell, r2, r3);
        return;
    }

    public void didClickClose() {
        if (this.cell.getDelegate() == null) goto L6;
        this.cell.getDelegate().didPressChannelRecommendationsClose(this.cell);
        return;
    }

    public void draw(Canvas r21) {
        if (this.msg != null) goto L5;
        return;
    L5:
        if (this.cell == null) goto L54;
        computeScroll();
        float r7 = 1.0f;
        float r9 = 4.0f;
        if (this.serviceText == null) goto L10;
        r21.save();
        float r2 = (this.cell.getWidth() - this.serviceText.getWidth()) / 2.0f;
        RectF r4 = AndroidUtilities.rectTmp;
        r4.set((this.serviceTextLeft + r2) - AndroidUtilities.dp(8.66f), AndroidUtilities.dp(4.0f), (this.serviceTextRight + r2) + AndroidUtilities.dp(8.66f), AndroidUtilities.dp(10.66f) + this.serviceTextHeight);
        this.cell.drawServiceBackground(r21, r4, AndroidUtilities.dp(11.0f), 1.0f);
        r21.translate(r2, AndroidUtilities.dp(7.33f));
        this.serviceText.draw(r21);
        r21.restore();
        float r22 = (AndroidUtilities.dp(10.66f) + this.serviceTextHeight) + 0.0f;
    L12:
        if (this.cell.transitionParams.animateRecommendationsExpanded == false) goto L18;
        boolean r42 = isExpanded();
        ChatMessageCell r5 = this.cell;
        if (r42 == false) goto L16;
        float r43 = r5.transitionParams.animateChangeProgress;
    L21:
        float r6 = Utilities.clamp((r43 - 0.3f) / 0.7f, 1.0f, 0.0f);
        if (r6 <= 0.0f) goto L53;
        int r44 = this.cell.getWidth() - AndroidUtilities.dp(18.0f);
        if (r44 <= AndroidUtilities.dp(441.0f)) goto L26;
        float r52 = AndroidUtilities.dp(66.0f);
    L27:
        this.blockWidth = (int) r52;
        this.channelsScrollWidth = (r5 * this.channels.size()) + (AndroidUtilities.dp(9.0f) * (this.channels.size() - 1));
        int r11 = (int) Math.min(r44, this.blockWidth * 6.5f);
        this.backgroundBounds.set((this.cell.getWidth() - r11) / 2.0f, AndroidUtilities.dp(10.0f) + r22, (this.cell.getWidth() + r11) / 2.0f, r22 + AndroidUtilities.dp(138.0f));
        this.scrollX = Utilities.clamp(this.scrollX, this.channelsScrollWidth - (this.backgroundBounds.width() - AndroidUtilities.dp(14.0f)), 0.0f);
        checkBackgroundPath(r6);
        r21.save();
        float r23 = (0.6f * r6) + 0.4f;
        r21.scale(r23, r23, this.backgroundBounds.centerX(), this.backgroundBounds.top - AndroidUtilities.dp(6.0f));
        float r13 = 255.0f;
        this.backgroundPaint.setAlpha((int) (r6 * 255.0f));
        this.backgroundPaint.setShadowLayer(AndroidUtilities.dpf2(1.0f), 0.0f, AndroidUtilities.dpf2(0.33f), ColorUtils.setAlphaComponent(-16777216, (int) (27.0f * r6)));
        r21.drawPath(this.backgroundPath, this.backgroundPaint);
        r21.clipPath(this.backgroundPath);
        Text r1 = this.headerText;
        float r14 = 20.0f;
        if (r1 == null) goto L30;
        r1.draw(r21, AndroidUtilities.dp(17.0f) + this.backgroundBounds.left, AndroidUtilities.dp(20.0f) + this.backgroundBounds.top, this.cell.getThemedColor(Theme.key_windowBackgroundWhiteBlackText), r6);
        Canvas r12 = r21;
    L31:
        float r24 = this.loadingAlpha.set(this.loading);
        float r3 = (this.backgroundBounds.left + AndroidUtilities.dp(7.0f)) - this.scrollX;
        float r45 = this.blockWidth + AndroidUtilities.dp(9.0f);
        int r53 = (int) Math.floor(((this.backgroundBounds.left - r11) - r3) / r45);
        int r112 = (int) Math.ceil((this.backgroundBounds.right - r3) / r45);
        if (r24 >= 1.0f) goto L38;
        int r122 = Math.max(0, r53);
    L34:
        float r16 = r7;
        float r17 = r9;
        if (r122 >= Math.min(r112 + 1, this.channels.size())) goto L37;
        ChannelBlock r72 = this.channels.get(r122);
        r12.save();
        float r18 = r13;
        r12.translate((r122 * r45) + r3, this.backgroundBounds.bottom - ChannelBlock.height());
        float r132 = (r16 - r24) * r6;
        r72.draw(r12, this.blockWidth, r132);
        r72.drawText(r12, this.blockWidth, r132);
        r12.restore();
        r122 = r122 + 1;
        r7 = r16;
        r9 = r17;
        r13 = r18;
        r14 = r14;
    L37:
        float r182 = r13;
        float r19 = r14;
        if (r24 <= 0.0f) goto L48;
        this.loadingPath.rewind();
        int r54 = Math.max(0, r53);
    L42:
        if (r54 >= r112) goto L45;
        ChannelBlock.fillPath(this.loadingPath, this.blockWidth, (r54 * r45) + r3);
        r54 = r54 + 1;
        goto L42
    L45:
        if (this.loadingDrawable != null) goto L47;
        LoadingDrawable r32 = new LoadingDrawable();
        this.loadingDrawable = r32;
        r32.usePath(this.loadingPath);
        this.loadingDrawable.setAppearByGradient(false);
    L47:
        int r33 = this.cell.getThemedColor(Theme.key_windowBackgroundWhiteBlackText);
        this.loadingDrawable.setColors(Theme.multAlpha(r33, 0.05f), Theme.multAlpha(r33, 0.15f), Theme.multAlpha(r33, 0.1f), Theme.multAlpha(r33, 0.3f));
        this.loadingDrawable.setGradientScale(1.5f);
        this.loadingDrawable.setAlpha((int) (r24 * r182));
        r12.save();
        r12.translate(0.0f, this.backgroundBounds.bottom - ChannelBlock.height());
        this.loadingDrawable.draw(r12);
        r12.restore();
    L48:
        float r25 = this.closeBounce.getScale(0.02f);
        float r73 = this.backgroundBounds.right - AndroidUtilities.dp(r19);
        float r8 = this.backgroundBounds.top + AndroidUtilities.dp(r19);
        r12.save();
        r12.scale(r25, r25, r73, r8);
        this.closePaint.setStrokeWidth(AndroidUtilities.dp(1.33f));
        r12.drawLine(r73 - AndroidUtilities.dp(r17), r8 - AndroidUtilities.dp(r17), AndroidUtilities.dp(r17) + r73, AndroidUtilities.dp(r17) + r8, this.closePaint);
        r21.drawLine(r73 - AndroidUtilities.dp(r17), r8 + AndroidUtilities.dp(r17), r73 + AndroidUtilities.dp(r17), r8 - AndroidUtilities.dp(r17), this.closePaint);
        this.closeBounds.set(r73 - AndroidUtilities.dp(12.0f), r8 - AndroidUtilities.dp(12.0f), r73 + AndroidUtilities.dp(12.0f), r8 + AndroidUtilities.dp(12.0f));
        r21.restore();
        r21.restore();
        return;
    L38:
        r17 = 4.0f;
        goto L37
    L30:
        r12 = r21;
        goto L31
    L26:
        r52 = Math.max((r44 / 4.5f) - AndroidUtilities.dp(9.0f), AndroidUtilities.dp(66.0f));
        goto L27
    L53:
        return;
    L16:
        r43 = 1.0f - r5.transitionParams.animateChangeProgress;
        goto L21
    L18:
        if (isExpanded() == false) goto L20;
        r43 = 1.0f;
        goto L21
    L20:
        r43 = 0.0f;
        goto L21
    L10:
        r22 = 0.0f;
        goto L12
    }

    public boolean isExpanded() {
        if (this.msg.channelJoinedExpanded == true) goto L5;
        return false;
    L5:
        if (this.channels.size() <= 0) goto L10;
        return true;
    L10:
        return false;
    }

    public void onAttachedToWindow() {
        int r0 = 0;
    L4:
        if (r0 >= this.channels.size()) goto L6;
        this.channels.get(r0).attach();
        r0 = r0 + 1;
        goto L4
    }

    public void onDetachedFromWindow() {
        int r0 = 0;
    L4:
        if (r0 >= this.channels.size()) goto L6;
        this.channels.get(r0).detach();
        r0 = r0 + 1;
        goto L4
    }

    public void setMessageObject(MessageObject r12) {
        this.currentAccount = r12.currentAccount;
        this.msg = r12;
        this.dialogId = r12.getDialogId();
        this.currentChat = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId));
        this.chatId = -this.dialogId;
        this.serviceTextPaint.setTypeface(AndroidUtilities.bold());
        this.serviceTextPaint.setTextSize(AndroidUtilities.dp(14.0f));
        this.serviceTextPaint.setColor(this.cell.getThemedColor(Theme.key_chat_serviceText));
        this.serviceText = new StaticLayout(LocaleController.getString(R.string.ChannelJoined), this.serviceTextPaint, this.msg.getMaxMessageTextWidth(), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        this.serviceTextLeft = r2.getWidth();
        this.serviceTextRight = 0.0f;
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r2 >= this.serviceText.getLineCount()) goto L6;
        this.serviceTextLeft = Math.min(this.serviceTextLeft, this.serviceText.getLineLeft(r2));
        this.serviceTextRight = Math.max(this.serviceTextRight, this.serviceText.getLineRight(r2));
        r2 = r2 + 1;
        goto L4
    L6:
        this.serviceTextHeight = this.serviceText.getHeight();
        this.closePaint.setStyle(Paint.Style.STROKE);
        this.closePaint.setStrokeCap(Paint.Cap.ROUND);
        this.closePaint.setStrokeJoin(Paint.Join.ROUND);
        this.closePaint.setColor(this.cell.getThemedColor(Theme.key_dialogEmptyImage));
        this.cell.totalHeight = AndroidUtilities.dp(14.66f) + this.serviceTextHeight;
        int r22 = 0;
    L7:
        int r3 = this.channels.size();
        ArrayList<ChannelBlock> r4 = this.channels;
        if (r22 >= r3) goto L10;
        r4.get(r22).detach();
        r22 = r22 + 1;
        goto L7
    L10:
        r4.clear();
        MessagesController.ChannelRecommendations r23 = MessagesController.getInstance(this.currentAccount).getChannelRecommendations(this.dialogId);
        if (r23 != null) goto L13;
    L16:
        ArrayList r32 = new ArrayList();
    L17:
        int r42 = 0;
    L19:
        if (r42 >= r32.size()) goto L27;
        TLObject r5 = (TLObject) r32.get(r42);
        if ((r5 instanceof TLRPC.Chat) == false) goto L25;
        if (ChatObject.isNotInChat((TLRPC.Chat) r5) == true) goto L25;
        r32.remove(r42);
        r42 = r42 - 1;
    L25:
        r42 = r42 + 1;
        goto L19
    L27:
        if (r32.isEmpty() == false) goto L29;
    L34:
        boolean r43 = true;
    L35:
        this.loading = r43;
        if (r43 == true) goto L64;
        int r44 = r32.size();
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L40;
    L42:
        int r45 = Math.min(r44, 10);
    L43:
        if (r1 >= r45) goto L46;
        this.channels.add(new ChannelBlock(this.currentAccount, this.cell, (TLObject) r32.get(r1)));
        r1 = r1 + 1;
        goto L43
    L46:
        if (r45 >= r32.size()) goto L64;
        TLObject r13 = null;
        if (r45 >= 0) goto L50;
    L52:
        TLObject r52 = null;
    L53:
        if (r45 < 0) goto L57;
        int r7 = r45 + 1;
        if (r7 >= r32.size()) goto L57;
        TLObject r72 = (TLObject) r32.get(r7);
    L58:
        if (r45 < 0) goto L62;
        int r8 = r45 + 2;
        if (r8 >= r32.size()) goto L62;
        r13 = (TLObject) r32.get(r8);
    L62:
        this.channels.add(new ChannelBlock(this.currentAccount, this.cell, new TLObject[]{r52, r72, r13}, (r32.size() + r23.more) - r45));
    L57:
        r72 = null;
        goto L58
    L50:
        if (r45 >= r32.size()) goto L52;
        r52 = (TLObject) r32.get(r45);
        goto L53
    L40:
        if (r23.more <= 0) goto L42;
        r44 = Math.min(r44 - 1, MessagesController.getInstance(this.currentAccount).recommendedChannelsLimitDefault);
    L64:
        if (this.headerText != null) goto L71;
        if (this.dialogId <= 0) goto L68;
        int r24 = R.string.SimilarBots;
    L69:
        this.headerText = new Text(LocaleController.getString(r24), 14.0f, AndroidUtilities.bold()).hackClipBounds();
        goto L71
    L68:
        r24 = R.string.SimilarChannels;
    L71:
        if (isExpanded() == false) goto L73;
        this.cell.totalHeight += AndroidUtilities.dp(144.0f);
        this.backgroundPaint.setColor(this.cell.getThemedColor(Theme.key_chat_inBubble));
    L73:
        float r0 = (this.blockWidth * this.channels.size()) + (AndroidUtilities.dp(9.0f) * (this.channels.size() - 1));
        this.channelsScrollWidth = r0;
        this.scrollX = Utilities.clamp(this.scrollX, r0, 0.0f);
        return;
    L29:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L31;
    L33:
        r43 = false;
        goto L35
    L31:
        if (r32.size() != 1) goto L33;
    L13:
        if (r23.chats == null) goto L16;
        r32 = new ArrayList(r23.chats);
        goto L17
    }

    public void update() {
        MessageObject r0 = this.msg;
        if (r0 != null) goto L5;
        return;
    L5:
        setMessageObject(r0);
        this.cell.invalidateOutbounds();
    }
}
