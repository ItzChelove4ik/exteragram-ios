package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.TextUtils;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.RadiiUtils;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Components.AnimatedEmojiDrawable;

/* loaded from: classes7.dex */
public class ReplyMessageLine {
    public int backgroundColor;
    public final AnimatedColor backgroundColorAnimated;
    private LoadingDrawable backgroundLoadingDrawable;
    public final Paint backgroundPaint;
    private final Path backgroundPath;
    private int cachedBar2;
    private int cachedBar3;
    private int cachedBarHeight;
    private int cachedBg;
    private boolean cachedHasColor3;
    public int color1;
    public final AnimatedColor color1Animated;
    private final Paint color1Paint;
    public int color2;
    public final AnimatedFloat color2Alpha;
    public final AnimatedColor color2Animated;
    public int color3;
    public final AnimatedFloat color3Alpha;
    public final AnimatedColor color3Animated;
    private AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable emoji;
    private float emojiAlpha;
    public int emojiColor;
    private long emojiDocumentId;
    private boolean emojiLoaded;
    public final AnimatedFloat emojiLoadedT;
    private float emojiOffsetX;
    private float emojiOffsetY;
    public boolean hasColor2;
    public boolean hasColor3;
    private IconCoords[] iconCoords;
    private long lastLoadingTTime;
    private boolean loading;
    public final AnimatedFloat loadingStateT;
    private float loadingT;
    private float loadingTranslationT;
    public int nameColor;
    public final AnimatedColor nameColorAnimated;
    private final View parentView;
    private Bitmap patternBitmap;
    private final Paint patternPaint;
    public final float[] radii;
    private final RectF rectF;
    private boolean reversedOut;
    private final Matrix shaderMatrix;
    private boolean sponsored;
    private AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable sticker;
    private long stickerDocumentId;
    public final AnimatedFloat switchStateT;
    private int switchedCount;
    private long wasCollectionId;
    private int wasColorId;
    private int wasMessageId;

    /* renamed from: org.telegram.ui.Components.ReplyMessageLine$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnAttachStateChangeListener {
        final /* synthetic */ ReplyMessageLine this$0;

        public AnonymousClass1(ReplyMessageLine r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r1) {
            if (ReplyMessageLine.m12494$$Nest$fgetemoji(this.this$0) == null) goto L6;
            ReplyMessageLine.m12494$$Nest$fgetemoji(this.this$0).attach();
        L6:
            if (ReplyMessageLine.m12495$$Nest$fgetsticker(this.this$0) == null) goto L9;
            ReplyMessageLine.m12495$$Nest$fgetsticker(this.this$0).attach();
            return;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r1) {
            if (ReplyMessageLine.m12494$$Nest$fgetemoji(this.this$0) == null) goto L6;
            ReplyMessageLine.m12494$$Nest$fgetemoji(this.this$0).detach();
        L6:
            if (ReplyMessageLine.m12495$$Nest$fgetsticker(this.this$0) == null) goto L9;
            ReplyMessageLine.m12495$$Nest$fgetsticker(this.this$0).detach();
            return;
        }
    }

    public static class IconCoords {

        /* renamed from: a, reason: collision with root package name */
        public float f510a;
        public boolean q;
        public float s;
        public float x;
        public float y;

        public IconCoords(float r1, float r2, float r3, float r4) {
            this.x = r1;
            this.y = r2;
            this.s = r3;
            this.f510a = r4;
        }

        public IconCoords(float r1, float r2, float r3, float r4, boolean r5) {
            this(r1, r2, r3, r4);
            this.q = r5;
        }
    }

    /* renamed from: -$$Nest$fgetemoji, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable m12494$$Nest$fgetemoji(ReplyMessageLine r0) {
        return r0.emoji;
    }

    /* renamed from: -$$Nest$fgetsticker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable m12495$$Nest$fgetsticker(ReplyMessageLine r0) {
        return r0.sticker;
    }

    public ReplyMessageLine(View r10) {
        this.rectF = new RectF();
        this.color1Paint = new Paint(1);
        this.patternPaint = new Paint(3);
        this.shaderMatrix = new Matrix();
        this.radii = new float[8];
        this.backgroundPath = new Path();
        this.backgroundPaint = new Paint();
        this.switchedCount = 0;
        this.emojiAlpha = 1.0f;
        this.parentView = r10;
        if (r10 == null) goto L5;
        r10.addOnAttachStateChangeListener(new AnonymousClass1(this));
    L5:
        CubicBezierInterpolator r8 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.backgroundColorAnimated = new AnimatedColor(r10, 0, 400, r8);
        this.color1Animated = new AnimatedColor(r10, 0, 400, r8);
        this.color2Animated = new AnimatedColor(r10, 0, 400, r8);
        this.color3Animated = new AnimatedColor(r10, 0, 400, r8);
        this.nameColorAnimated = new AnimatedColor(r10, 0, 400, r8);
        this.color2Alpha = new AnimatedFloat(r10, 0, 400, r8);
        this.color3Alpha = new AnimatedFloat(r10, 0, 400, r8);
        this.emojiLoadedT = new AnimatedFloat(r10, 0, 440, r8);
        this.loadingStateT = new AnimatedFloat(r10, 0, 320, r8);
        this.switchStateT = new AnimatedFloat(r10, 0, 320, r8);
    }

    private void checkPatternBitmap(int r4, int r5, int r6, float r7, float r8, float r9) {
        int r42 = Theme.multAlpha(r4, r9);
        int r52 = ColorUtils.compositeColors(Theme.multAlpha(r5, r7 * r9), r42);
        if (this.hasColor3 == false) goto L5;
        int r62 = ColorUtils.compositeColors(Theme.multAlpha(r6, r9 * r8), r42);
    L6:
        int r72 = Math.round(AndroidUtilities.dpf2(6.33f));
        int r82 = Math.max(1, AndroidUtilities.dp(3.0f));
        if (this.hasColor3 == false) goto L9;
        int r92 = AndroidUtilities.dp(18.99f);
    L10:
        Bitmap r0 = this.patternBitmap;
        if (r0 != null) goto L13;
    L27:
        this.cachedBg = r42;
        this.cachedBar2 = r52;
        this.cachedBar3 = r62;
        this.cachedHasColor3 = this.hasColor3;
        this.cachedBarHeight = r72;
        Bitmap r02 = this.patternBitmap;
        if (r02 != null) goto L30;
    L35:
        Bitmap r03 = this.patternBitmap;
        if (r03 == null) goto L38;
        r03.recycle();
    L38:
        this.patternBitmap = Bitmap.createBitmap(r82, r92, Bitmap.Config.ARGB_8888);
        this.patternPaint.setShader(new BitmapShader(this.patternBitmap, Shader.TileMode.CLAMP, Shader.TileMode.REPEAT));
    L39:
        Utilities.drawReplyLinePattern(this.patternBitmap, r42, r52, r62, r72, this.hasColor3);
        return;
    L30:
        if (r02.getWidth() != r82) goto L35;
        if (this.patternBitmap.getHeight() == r92) goto L39;
    L13:
        if (this.cachedBg != r42) goto L27;
        if (this.cachedBar2 != r52) goto L27;
        if (this.cachedBar3 != r62) goto L27;
        if (this.cachedHasColor3 != this.hasColor3) goto L27;
        if (this.cachedBarHeight != r72) goto L27;
        if (r0.getWidth() != r82) goto L27;
        if (this.patternBitmap.getHeight() != r92) goto L27;
        return;
    L9:
        r92 = AndroidUtilities.dp(12.66f);
        goto L10
    L5:
        r62 = 0;
        goto L6
    }

    private void incrementLoadingT() {
        long r0 = System.currentTimeMillis();
        float r2 = this.loadingStateT.set(this.loading);
        this.loadingT += Math.min(30, r0 - this.lastLoadingTTime) * r2;
        this.loadingTranslationT += Math.min(30, r0 - this.lastLoadingTTime) * r2;
        this.lastLoadingTTime = r0;
    }

    private boolean isEmojiLoaded() {
        if (this.emojiLoaded == false) goto L5;
        return true;
    L5:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r0 = this.emoji;
        if (r0 != null) goto L8;
        return false;
    L8:
        if ((r0.getDrawable() instanceof AnimatedEmojiDrawable) == false) goto L17;
        AnimatedEmojiDrawable r02 = (AnimatedEmojiDrawable) this.emoji.getDrawable();
        if (r02.getImageReceiver() != null) goto L12;
        return false;
    L12:
        if (r02.getImageReceiver().hasImageLoaded() == false) goto L19;
        this.emojiLoaded = true;
        return true;
    L19:
        return false;
    L17:
        return false;
    }

    private int resolveCollectionColor(MessageObject r10, TLRPC.TL_peerColorCollectible r11, Theme.ResourcesProvider r12) {
        if (r12 == null) goto L4;
        boolean r0 = r12.isDark();
    L6:
        if (r0 == true) goto L8;
    L10:
        int r2 = r11.accent_color;
    L12:
        if (r0 == true) goto L14;
    L16:
        ArrayList<Integer> r02 = r11.colors;
    L18:
        if (r02 != null) goto L20;
    L99:
        return 0;
    L20:
        if (r02.isEmpty() == true) goto L99;
        if (this.wasCollectionId == r11.collectible_id) goto L31;
        if (r10 == null) goto L26;
        int r102 = r10.getId();
    L28:
        if (r102 != this.wasMessageId) goto L30;
        this.switchedCount++;
    L30:
        this.wasColorId = 0;
        this.wasCollectionId = r11.collectible_id;
        this.wasMessageId = r102;
        goto L31
    L26:
        r102 = 0;
    L31:
        this.reversedOut = false;
        if (ExteraConfig.getReplyColors() == true) goto L34;
        this.hasColor2 = false;
        this.hasColor3 = false;
        int r103 = Theme.getColor(Theme.key_chat_inReplyLine, r12);
        this.color3 = r103;
        this.color2 = r103;
        this.color1 = r103;
        this.nameColor = Theme.getColor(Theme.key_chat_inReplyNameText, r12);
    L50:
        if (ExteraConfig.getReplyBackground() == true) goto L53;
        this.backgroundColor = 0;
    L58:
        if (ExteraConfig.getReplyEmoji() == false) goto L60;
        this.emojiDocumentId = r11.background_emoji_id;
        this.stickerDocumentId = r11.gift_emoji_id;
    L62:
        if (this.emojiDocumentId != 0) goto L64;
    L75:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r104 = this.emoji;
        if (r104 != null) goto L78;
    L80:
        this.emojiColor = this.nameColor;
        if (this.stickerDocumentId != 0) goto L83;
    L94:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r105 = this.sticker;
        if (r105 == null) goto L98;
        r105.set(this.stickerDocumentId, true);
    L98:
        return this.nameColorAnimated.set(this.nameColor);
    L83:
        if (this.sticker != null) goto L94;
        if (this.parentView == null) goto L94;
        this.sticker = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this.parentView, false, AndroidUtilities.dp(20.0f), 13);
        View r106 = this.parentView;
        if ((r106 instanceof ChatMessageCell) == false) goto L92;
        if (((ChatMessageCell) r106).isCellAttachedToWindow() == false) goto L94;
    L93:
        this.sticker.attach();
        goto L94
    L92:
        if (r106.isAttachedToWindow() == false) goto L94;
    L78:
        if (r104.set(this.emojiDocumentId, true) == false) goto L80;
        this.emojiLoaded = false;
        goto L80
    L64:
        if (this.emoji != null) goto L75;
        if (this.parentView == null) goto L75;
        this.emoji = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this.parentView, false, AndroidUtilities.dp(20.0f), 13);
        View r107 = this.parentView;
        if ((r107 instanceof ChatMessageCell) == false) goto L73;
        if (((ChatMessageCell) r107).isCellAttachedToWindow() == false) goto L75;
    L74:
        this.emoji.attach();
        goto L75
    L73:
        if (r107.isAttachedToWindow() == false) goto L75;
    L60:
        this.emojiDocumentId = 0;
        this.stickerDocumentId = 0;
        goto L62
    L53:
        if (ExteraConfig.getReplyColors() == false) goto L55;
        int r108 = this.nameColor;
    L56:
        this.backgroundColor = Theme.multAlpha(r108, 0.1f);
        goto L58
    L55:
        r108 = this.color1;
        goto L56
    L34:
        this.color1 = r02.get(0).intValue() | (-16777216);
        if (r02.size() < 2) goto L37;
        boolean r109 = true;
    L38:
        this.hasColor2 = r109;
        if (r109 == false) goto L42;
        this.color2 = r02.get(1).intValue() | (-16777216);
    L42:
        if (r02.size() < 3) goto L44;
        boolean r1010 = true;
    L45:
        this.hasColor3 = r1010;
        if (r1010 == false) goto L48;
        this.color3 = r02.get(2).intValue() | (-16777216);
    L48:
        this.nameColor = r2 | (-16777216);
        goto L50
    L44:
        r1010 = false;
        goto L45
    L37:
        r109 = false;
        goto L38
    L14:
        if ((r11.flags & 2) == 0) goto L16;
        r02 = r11.dark_colors;
        goto L18
    L8:
        if ((r11.flags & 1) == 0) goto L10;
        r2 = r11.dark_accent_color;
        goto L12
    L4:
        r0 = Theme.isCurrentThemeDark();
        goto L6
    }

    private void resolveColor(MessageObject r6, int r7, Theme.ResourcesProvider r8) {
        if (r8 == null) goto L4;
        r8.isDark();
    L5:
        boolean r1 = true;
        if (this.wasColorId == r7) goto L15;
        if (r6 == null) goto L9;
        int r0 = r6.getId();
    L11:
        if (r0 != this.wasMessageId) goto L13;
        this.switchedCount++;
    L13:
        this.wasCollectionId = 0;
        this.wasColorId = r7;
        this.wasMessageId = r0;
        goto L15
    L9:
        r0 = 0;
    L15:
        if (r7 >= 7) goto L18;
        int r62 = Theme.getColor(Theme.keys_avatar_nameInMessage[r7], r8);
        this.color3 = r62;
        this.color2 = r62;
        this.color1 = r62;
        this.hasColor3 = false;
        this.hasColor2 = false;
        return;
    L18:
        if (r6 == null) goto L20;
        int r02 = r6.currentAccount;
    L21:
        MessagesController.PeerColors r03 = MessagesController.getInstance(r02).peerColors;
        if (r03 == null) goto L24;
        MessagesController.PeerColor r72 = r03.getColor(r7);
    L25:
        if (r72 != null) goto L33;
        if (r6 != null) goto L28;
    L30:
        int r63 = Theme.key_chat_inReplyLine;
    L31:
        int r64 = Theme.getColor(r63, r8);
        this.color3 = r64;
        this.color2 = r64;
        this.color1 = r64;
        this.hasColor3 = false;
        this.hasColor2 = false;
        return;
    L28:
        if (r6.isOutOwner() == false) goto L30;
        r63 = Theme.key_chat_outReplyLine;
        goto L31
    L33:
        this.color1 = r72.getColor(0, r8);
        this.color2 = r72.getColor(1, r8);
        int r65 = r72.getColor(2, r8);
        this.color3 = r65;
        int r73 = this.color2;
        int r82 = this.color1;
        if (r73 == r82) goto L36;
        boolean r04 = true;
    L37:
        this.hasColor2 = r04;
        if (r65 != r82) goto L41;
        r1 = false;
    L41:
        this.hasColor3 = r1;
        if (r1 == false) goto L45;
        this.color3 = r73;
        this.color2 = r65;
        return;
    L45:
        return;
    L36:
        r04 = false;
        goto L37
    L24:
        r72 = null;
        goto L25
    L20:
        r02 = UserConfig.selectedAccount;
        goto L21
    L4:
        Theme.isCurrentThemeDark();
        goto L5
    }

    public int check(MessageObject r20, TLRPC.User r21, TLRPC.Chat r22, Theme.ResourcesProvider r23, int r24) {
        TLRPC.User r2 = r21;
        if (r23 == null) goto L5;
        boolean r6 = r23.isDark();
    L7:
        if (r20 != null) goto L9;
    L15:
        this.reversedOut = false;
        this.emojiDocumentId = 0;
        this.stickerDocumentId = 0;
        if (r20 != null) goto L18;
    L20:
        boolean r12 = false;
    L21:
        this.sponsored = r12;
        float r13 = 0.1f;
        if (r20 != null) goto L29;
        this.hasColor3 = false;
        this.hasColor2 = false;
        int r1 = Theme.getColor(Theme.key_chat_inReplyLine, r23);
        this.color3 = r1;
        this.color2 = r1;
        this.color1 = r1;
        if (r6 == false) goto L26;
        float r122 = 0.12f;
    L27:
        this.backgroundColor = Theme.multAlpha(r1, r122);
        this.emojiColor = getColor();
        AnimatedColor r14 = this.nameColorAnimated;
        int r25 = Theme.getColor(Theme.key_chat_inReplyNameText, r23);
        this.nameColor = r25;
        return r14.set(r25);
    L26:
        r122 = 0.1f;
        goto L27
    L29:
        int r15 = -1;
        if (r24 != 4) goto L53;
        TLRPC.Message r9 = r20.messageOwner;
        if (r9 == null) goto L53;
        if (MessageObject.getMedia(r9) == null) goto L53;
        if ((MessageObject.getMedia(r20.messageOwner) instanceof TLRPC.TL_messageMediaContact) == false) goto L53;
        long r26 = MessageObject.getMedia(r20.messageOwner).user_id;
        if (r26 == 0) goto L40;
        TLRPC.User r27 = MessagesController.getInstance(r20.currentAccount).getUser(Long.valueOf(r26));
    L42:
        if (r20.isOutOwner() == true) goto L49;
        if (r24 == 2) goto L49;
        if (r27 == null) goto L49;
        TLRPC.PeerColor r3 = r27.color;
        if ((r3 instanceof TLRPC.TL_peerColorCollectible) == false) goto L49;
        return resolveCollectionColor(r20, (TLRPC.TL_peerColorCollectible) r3, r23);
    L49:
        if (r27 == null) goto L51;
        int r32 = UserObject.getColorId(r27);
        this.emojiDocumentId = UserObject.getEmojiId(r27);
    L52:
        resolveColor(r20, r32, r23);
        this.backgroundColor = Theme.multAlpha(this.color1, 0.1f);
        this.nameColor = this.color1;
    L292:
        if (r20.shouldDrawWithoutBackground() == false) goto L298;
        this.hasColor2 = false;
        this.hasColor3 = false;
        if (Theme.isCurrentThemeMonet(r23) == false) goto L296;
        r15 = Theme.getColor(Theme.key_chat_inReplyLine, r23);
    L296:
        this.color3 = r15;
        this.color2 = r15;
        this.color1 = r15;
        this.backgroundColor = 0;
        this.nameColor = Theme.getColor(Theme.key_chat_stickerReplyNameText, r23);
    L327:
        if (r24 == 0) goto L330;
        if (r24 == 3) goto L330;
        if (r24 == 4) goto L330;
    L334:
        if (ExteraConfig.getReplyEmoji() == true) goto L336;
        long r16 = 0;
        this.emojiDocumentId = 0;
    L338:
        if (this.emojiDocumentId != r16) goto L340;
    L351:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r17 = this.emoji;
        if (r17 != null) goto L354;
    L356:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r18 = this.sticker;
        if (r18 == null) goto L359;
        r18.set(this.stickerDocumentId, true);
    L359:
        this.emojiColor = getColor();
        return this.nameColorAnimated.set(this.nameColor);
    L354:
        if (r17.set(this.emojiDocumentId, true) == false) goto L356;
        this.emojiLoaded = false;
        goto L356
    L340:
        if (this.emoji != null) goto L351;
        if (this.parentView == null) goto L351;
        this.emoji = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this.parentView, false, AndroidUtilities.dp(20.0f), 13);
        View r19 = this.parentView;
        if ((r19 instanceof ChatMessageCell) == false) goto L349;
        if (((ChatMessageCell) r19).isCellAttachedToWindow() == false) goto L351;
    L350:
        this.emoji.attach();
        goto L351
    L349:
        if (r19.isAttachedToWindow() == false) goto L351;
    L336:
        r16 = 0;
    L330:
        long r110 = r20.overrideLinkEmoji;
        if (r110 == (-1)) goto L334;
        this.emojiDocumentId = r110;
        goto L334
    L298:
        if (r20.isOutOwner() == true) goto L300;
        if (r24 != 2) goto L327;
    L300:
        if (r24 != 2) goto L305;
        if (r20.isOutOwner() == true) goto L305;
        int r28 = Theme.getColor(Theme.key_chat_inCodeBackground, r23);
        this.color3 = r28;
        this.color2 = r28;
        this.color1 = r28;
    L313:
        if (this.hasColor3 == false) goto L316;
        this.reversedOut = true;
        this.color1 = Theme.multAlpha(this.color1, 0.2f);
        this.color2 = Theme.multAlpha(this.color2, 0.5f);
    L319:
        if (ExteraConfig.getReplyBackground() == true) goto L322;
        if (r24 != 0) goto L322;
        int r29 = 0;
    L326:
        this.backgroundColor = r29;
        this.nameColor = Theme.getColor(Theme.key_chat_outReplyNameText, r23);
    L322:
        int r210 = this.color3;
        if (r6 == false) goto L325;
        r13 = 0.12f;
    L325:
        r29 = Theme.multAlpha(r210, r13);
        goto L326
    L316:
        if (this.hasColor2 == false) goto L319;
        this.reversedOut = true;
        this.color1 = Theme.multAlpha(this.color1, 0.35f);
    L305:
        if (this.hasColor2 == false) goto L307;
    L310:
        int r211 = Theme.key_chat_outReplyLine2;
    L311:
        int r212 = Theme.getColor(r211, r23);
        this.color3 = r212;
        this.color2 = r212;
        this.color1 = r212;
        goto L313
    L307:
        if (this.hasColor3 == true) goto L310;
        r211 = Theme.key_chat_outReplyLine;
        goto L311
    L51:
        r32 = 0;
        goto L52
    L40:
        r27 = null;
    L53:
        if (r24 != 0) goto L55;
    L206:
        if (r24 == 0) goto L208;
    L285:
        this.hasColor2 = false;
        this.hasColor3 = false;
        int r213 = Theme.getColor(Theme.key_chat_inReplyLine, r23);
        this.color3 = r213;
        this.color2 = r213;
        this.color1 = r213;
        if (ExteraConfig.getReplyBackground() == true) goto L289;
        if (r24 == 1) goto L289;
        int r214 = 0;
    L290:
        this.backgroundColor = r214;
        this.nameColor = Theme.getColor(Theme.key_chat_inReplyNameText, r23);
    L289:
        r214 = Theme.multAlpha(this.color1, 0.1f);
        goto L290
    L208:
        if (r20.overrideLinkColor >= 0) goto L231;
        TLRPC.Message r33 = r20.messageOwner;
        if (r33 == null) goto L285;
        MessageObject r92 = r20.replyMessageObject;
        if (r92 == null) goto L285;
        if ((r92.messageOwner instanceof TLRPC.TL_messageEmpty) == true) goto L285;
        TLRPC.MessageReplyHeader r34 = r33.reply_to;
        if (r34 == null) goto L285;
        TLRPC.MessageFwdHeader r35 = r34.reply_from;
        if (r35 != null) goto L220;
    L221:
        MessageObject r36 = r20.replyMessageObject;
        TLRPC.Message r93 = r36.messageOwner;
        if (r93 == null) goto L285;
        if (r93.from_id == null) goto L285;
        if (r36.isFromUser() == true) goto L231;
        if (DialogObject.isEncryptedDialog(r20.getDialogId()) == true) goto L231;
        if (r20.replyMessageObject.isFromChannel() == false) goto L285;
    L220:
        if (TextUtils.isEmpty(r35.from_name) == false) goto L285;
    L231:
        int r37 = r20.overrideLinkColor;
        if (r37 >= 0) goto L277;
        boolean r38 = DialogObject.isEncryptedDialog(r20.replyMessageObject.getDialogId());
        MessageObject r94 = r20.replyMessageObject;
        if (r38 == false) goto L242;
        if (r94.isOutOwner() == false) goto L239;
        r2 = UserConfig.getInstance(r20.replyMessageObject.currentAccount).getCurrentUser();
    L239:
        if (r2 == null) goto L275;
        r37 = UserObject.getColorId(r2);
        this.emojiDocumentId = UserObject.getEmojiId(r2);
    L275:
        r37 = 0;
        goto L277
    L242:
        if (r94.isFromUser() == false) goto L254;
        TLRPC.User r215 = MessagesController.getInstance(r20.currentAccount).getUser(Long.valueOf(r20.replyMessageObject.messageOwner.from_id.user_id));
        if (r20.isOutOwner() == true) goto L251;
        if (r24 == 2) goto L251;
        if (r215 == null) goto L251;
        TLRPC.PeerColor r39 = r215.color;
        if ((r39 instanceof TLRPC.TL_peerColorCollectible) == false) goto L251;
        return resolveCollectionColor(r20, (TLRPC.TL_peerColorCollectible) r39, r23);
    L251:
        if (r215 == null) goto L275;
        r37 = UserObject.getColorId(r215);
        this.emojiDocumentId = UserObject.getEmojiId(r215);
        goto L277
    L254:
        if (r20.replyMessageObject.isFromChannel() == false) goto L275;
        TLObject r216 = r20.replyMessageObject.getFromPeerObject();
        if ((r216 instanceof TLRPC.User) == false) goto L266;
        TLRPC.User r217 = (TLRPC.User) r216;
        if (r20.isOutOwner() == true) goto L264;
        if (r24 == 2) goto L264;
        TLRPC.PeerColor r310 = r217.color;
        if ((r310 instanceof TLRPC.TL_peerColorCollectible) == false) goto L264;
        return resolveCollectionColor(r20, (TLRPC.TL_peerColorCollectible) r310, r23);
    L264:
        r37 = UserObject.getColorId(r217);
        this.emojiDocumentId = UserObject.getEmojiId(r217);
        goto L277
    L266:
        if ((r216 instanceof TLRPC.Chat) == false) goto L275;
        TLRPC.Chat r218 = (TLRPC.Chat) r216;
        if (r20.isOutOwner() == true) goto L274;
        if (r24 == 2) goto L274;
        TLRPC.PeerColor r311 = r218.color;
        if ((r311 instanceof TLRPC.TL_peerColorCollectible) == false) goto L274;
        return resolveCollectionColor(r20, (TLRPC.TL_peerColorCollectible) r311, r23);
    L274:
        r37 = ChatObject.getColorId(r218);
        this.emojiDocumentId = ChatObject.getEmojiId(r218);
    L277:
        if (ExteraConfig.getReplyColors() == true) goto L279;
        this.hasColor3 = false;
        this.hasColor2 = false;
        int r219 = Theme.getColor(Theme.key_chat_inReplyLine, r23);
        this.color3 = r219;
        this.color2 = r219;
        this.color1 = r219;
        this.nameColor = Theme.getColor(Theme.key_chat_inReplyNameText, r23);
    L281:
        if (ExteraConfig.getReplyBackground() == true) goto L283;
        int r220 = 0;
    L284:
        this.backgroundColor = r220;
        goto L292
    L283:
        r220 = Theme.multAlpha(this.color1, 0.1f);
        goto L284
    L279:
        resolveColor(r20.replyMessageObject, r37, r23);
        this.nameColor = this.color1;
        goto L281
    L55:
        if (r20.overrideLinkColor < 0) goto L57;
    L78:
        int r95 = r20.overrideLinkColor;
        if (r95 >= 0) goto L198;
        if (r20.isSponsored() == false) goto L90;
        TLRPC.PeerColor r96 = r20.sponsoredColor;
        if (r96 == null) goto L90;
        int r10 = r96.color;
        if (r10 == (-1)) goto L90;
        if (r24 != 3) goto L89;
        this.emojiDocumentId = r96.background_emoji_id;
    L89:
        r95 = r10;
    L90:
        TLRPC.Message r97 = r20.messageOwner;
        if (r97 == null) goto L124;
        TLRPC.MessageFwdHeader r98 = r97.fwd_from;
        if (r98 == null) goto L124;
        TLRPC.Peer r99 = r98.from_id;
        if (r99 == null) goto L124;
        long r221 = DialogObject.getPeerDialogId(r99);
        int r102 = r20.currentAccount;
        int r182 = 5;
        if (r221 >= 0) goto L111;
        TLRPC.Chat r222 = MessagesController.getInstance(r102).getChat(Long.valueOf(-r221));
        if (r20.isOutOwner() == true) goto L106;
        if (r24 == 2) goto L106;
        if (r222 == null) goto L106;
        TLRPC.PeerColor r312 = r222.color;
        if ((r312 instanceof TLRPC.TL_peerColorCollectible) == false) goto L106;
        return resolveCollectionColor(r20, (TLRPC.TL_peerColorCollectible) r312, r23);
    L106:
        if (r222 == null) goto L108;
        r182 = ChatObject.getColorId(r222);
    L108:
        if (r24 != 3) goto L110;
        this.emojiDocumentId = ChatObject.getEmojiId(r222);
    L110:
        r95 = r182;
        goto L198
    L111:
        TLRPC.User r223 = MessagesController.getInstance(r102).getUser(Long.valueOf(r221));
        if (r20.isOutOwner() == true) goto L119;
        if (r24 == 2) goto L119;
        if (r223 == null) goto L119;
        TLRPC.PeerColor r313 = r223.color;
        if ((r313 instanceof TLRPC.TL_peerColorCollectible) == false) goto L119;
        return resolveCollectionColor(r20, (TLRPC.TL_peerColorCollectible) r313, r23);
    L119:
        if (r223 == null) goto L121;
        r182 = UserObject.getColorId(r223);
    L121:
        if (r24 != 3) goto L110;
        this.emojiDocumentId = UserObject.getEmojiId(r223);
    L124:
        if (DialogObject.isEncryptedDialog(r20.getDialogId()) == false) goto L144;
        if (r2 == null) goto L144;
        if (r20.isOutOwner() == false) goto L129;
        TLRPC.User r314 = UserConfig.getInstance(r20.currentAccount).getCurrentUser();
    L130:
        if (r314 == null) goto L134;
        r2 = r314;
    L134:
        if (r20.isOutOwner() == true) goto L140;
        if (r24 == 2) goto L140;
        TLRPC.PeerColor r315 = r2.color;
        if ((r315 instanceof TLRPC.TL_peerColorCollectible) == false) goto L140;
        return resolveCollectionColor(r20, (TLRPC.TL_peerColorCollectible) r315, r23);
    L140:
        r95 = UserObject.getColorId(r2);
        if (r24 != 3) goto L198;
        this.emojiDocumentId = UserObject.getEmojiId(r2);
        goto L198
    L129:
        r314 = r2;
    L144:
        if (r20.isFromUser() == false) goto L157;
        if (r2 == null) goto L157;
        if (r20.isOutOwner() == true) goto L153;
        if (r24 == 2) goto L153;
        TLRPC.PeerColor r316 = r2.color;
        if ((r316 instanceof TLRPC.TL_peerColorCollectible) == false) goto L153;
        return resolveCollectionColor(r20, (TLRPC.TL_peerColorCollectible) r316, r23);
    L153:
        r95 = UserObject.getColorId(r2);
        if (r24 != 3) goto L198;
        this.emojiDocumentId = UserObject.getEmojiId(r2);
    L157:
        if (r20.isFromChannel() == false) goto L196;
        if (r22 == null) goto L196;
        if (r22.signature_profiles == false) goto L187;
        TLObject r224 = r20.getFromPeerObject();
        if ((r224 instanceof TLRPC.User) == false) goto L175;
        TLRPC.User r225 = (TLRPC.User) r224;
        if (r20.isOutOwner() == true) goto L170;
        if (r24 == 2) goto L170;
        TLRPC.PeerColor r317 = r225.color;
        if ((r317 instanceof TLRPC.TL_peerColorCollectible) == false) goto L170;
        return resolveCollectionColor(r20, (TLRPC.TL_peerColorCollectible) r317, r23);
    L170:
        int r318 = UserObject.getColorId(r225);
        if (r24 != 3) goto L173;
        this.emojiDocumentId = UserObject.getEmojiId(r225);
    L173:
        r95 = r318;
        goto L198
    L175:
        if ((r224 instanceof TLRPC.Chat) == false) goto L196;
        TLRPC.Chat r226 = (TLRPC.Chat) r224;
        if (r20.isOutOwner() == true) goto L183;
        if (r24 == 2) goto L183;
        TLRPC.PeerColor r319 = r226.color;
        if ((r319 instanceof TLRPC.TL_peerColorCollectible) == false) goto L183;
        return resolveCollectionColor(r20, (TLRPC.TL_peerColorCollectible) r319, r23);
    L183:
        r318 = ChatObject.getColorId(r226);
        if (r24 != 3) goto L173;
        this.emojiDocumentId = ChatObject.getEmojiId(r226);
        goto L173
    L187:
        if (r20.isOutOwner() == true) goto L193;
        if (r24 == 2) goto L193;
        TLRPC.PeerColor r227 = r22.color;
        if ((r227 instanceof TLRPC.TL_peerColorCollectible) == false) goto L193;
        return resolveCollectionColor(r20, (TLRPC.TL_peerColorCollectible) r227, r23);
    L193:
        r95 = ChatObject.getColorId(r22);
        if (r24 != 3) goto L198;
        this.emojiDocumentId = ChatObject.getEmojiId(r22);
    L196:
        r95 = 0;
    L198:
        if (ExteraConfig.getReplyColors() == true) goto L200;
        this.hasColor3 = false;
        this.hasColor2 = false;
        int r228 = Theme.getColor(Theme.key_chat_inReplyLine, r23);
        this.color3 = r228;
        this.color2 = r228;
        this.color1 = r228;
        this.nameColor = Theme.getColor(Theme.key_chat_inReplyNameText, r23);
    L202:
        if (ExteraConfig.getReplyBackground() == true) goto L204;
        int r229 = 0;
    L205:
        this.backgroundColor = r229;
        goto L292
    L204:
        r229 = Theme.multAlpha(this.color1, 0.1f);
        goto L205
    L200:
        resolveColor(r20, r95, r23);
        this.nameColor = this.color1;
        goto L202
    L57:
        if (r20.messageOwner == null) goto L206;
        if (r20.isFromUser() == false) goto L61;
    L62:
        if (r2 != null) goto L78;
    L64:
        if (r20.isFromChannel() == false) goto L66;
        if (r22 != null) goto L78;
    L66:
        TLRPC.Message r910 = r20.messageOwner;
        if (r910 == null) goto L73;
        TLRPC.MessageFwdHeader r911 = r910.fwd_from;
        if (r911 == null) goto L73;
        if (r911.from_id != null) goto L78;
    L73:
        if (r20.isSponsored() == false) goto L206;
        TLRPC.PeerColor r912 = r20.sponsoredColor;
        if (r912 == null) goto L206;
        if (r912.color == (-1)) goto L206;
    L61:
        if (DialogObject.isEncryptedDialog(r20.getDialogId()) == false) goto L64;
    L18:
        if (r20.isSponsored() == false) goto L20;
        r12 = true;
        goto L21
    L9:
        if (r20.isOutOwner() == true) goto L15;
        if (r24 == 2) goto L15;
        TLRPC.TL_peerColorCollectible r8 = r20.overrideLinkPeerColor;
        if (r8 == null) goto L15;
        return resolveCollectionColor(r20, r8, r23);
    L5:
        r6 = Theme.isCurrentThemeDark();
        goto L7
    }

    public void drawBackground(Canvas r7, RectF r8, float r9) {
        drawBackground(r7, r8, r9, false, false);
    }

    public void drawBackground(Canvas r10, RectF r11, float r12, float r13, float r14, float r15) {
        drawBackground(r10, r11, r12, r13, r14, r15, false, false);
    }

    public void drawBackground(Canvas r7, RectF r8, float r9, float r10, float r11, float r12, boolean r13, boolean r14) {
        float[] r0 = this.radii;
        float r92 = Math.max(AndroidUtilities.dp((int) Math.floor(SharedConfig.bubbleRadius / 3.0f)), AndroidUtilities.dp(r9));
        r0[1] = r92;
        r0[0] = r92;
        float[] r93 = this.radii;
        float r102 = AndroidUtilities.dp(r10);
        r93[3] = r102;
        r93[2] = r102;
        float[] r94 = this.radii;
        float r103 = AndroidUtilities.dp(r11);
        r94[5] = r103;
        r94[4] = r103;
        float[] r95 = this.radii;
        float r104 = Math.max(AndroidUtilities.dp((int) Math.floor(SharedConfig.bubbleRadius / 3.0f)), AndroidUtilities.dp(r11));
        r95[7] = r104;
        r95[6] = r104;
        drawBackground(r7, r8, r12, r13, r14);
    }

    public void drawBackground(Canvas r25, RectF r26, float r27, boolean r28, boolean r29) {
        if (r29 == true) goto L9;
        this.backgroundPaint.setColor(Theme.multAlpha(this.backgroundColorAnimated.set(this.backgroundColor), r27));
        if (RadiiUtils.radiiAreSame(this.radii) == false) goto L7;
        float r5 = this.radii[0];
        r25.drawRoundRect(r26, r5, r5, this.backgroundPaint);
        goto L9
    L7:
        this.backgroundPath.rewind();
        this.backgroundPath.addRoundRect(r26, this.radii, Path.Direction.CW);
        r25.drawPath(this.backgroundPath, this.backgroundPaint);
    L9:
        if (this.emoji == null) goto L48;
        float r52 = this.emojiLoadedT.set(isEmojiLoaded());
        if (r52 > 0.0f) goto L13;
        return;
    L13:
        if (this.emojiAlpha > 0.0f) goto L15;
        return;
    L15:
        if (this.iconCoords != null) goto L17;
        this.iconCoords = new IconCoords[]{new IconCoords(4.0f, -6.33f, 1.0f, 1.0f), new IconCoords(30.0f, 3.0f, 0.78f, 0.9f), new IconCoords(46.0f, -17.0f, 0.6f, 0.6f), new IconCoords(69.66f, -0.666f, 0.87f, 0.7f), new IconCoords(98.0f, -12.6f, 1.03f, 0.3f), new IconCoords(51.0f, 24.0f, 1.0f, 0.5f), new IconCoords(6.33f, 20.0f, 0.77f, 0.7f), new IconCoords(-19.0f, 12.0f, 0.8f, 0.6f, true), new IconCoords(-22.0f, 36.0f, 0.7f, 0.5f, true)};
    L17:
        r25.save();
        r25.clipRect(r26);
        r25.translate(this.emojiOffsetX, this.emojiOffsetY);
        float r4 = Math.max(r26.right - AndroidUtilities.dp(15.0f), r26.centerX());
        if (r28 == false) goto L20;
        r4 = r4 - AndroidUtilities.dp(12.0f);
    L20:
        float r2 = Math.min(r26.centerY(), r26.top + AndroidUtilities.dp(21.0f));
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r6 = this.sticker;
        if (r6 == null) goto L23;
        r6.setAlpha((int) (r27 * 255.0f));
    L23:
        this.emoji.setColor(Integer.valueOf(this.emojiColor));
        int r3 = 0;
    L24:
        IconCoords[] r62 = this.iconCoords;
        if (r3 >= r62.length) goto L43;
        if (r3 != 0) goto L32;
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r8 = this.sticker;
        if (r8 == null) goto L32;
        if (this.stickerDocumentId == 0) goto L32;
    L33:
        IconCoords r63 = r62[r3];
        if (r63.q == false) goto L38;
        if (r28 == true) goto L38;
    L42:
        r3 = r3 + 1;
    L38:
        if (r8 != this.sticker) goto L40;
        float r9 = 1.0f;
    L41:
        r8.setAlpha((int) (((r9 * 255.0f) * r63.f510a) * this.emojiAlpha));
        float r92 = r4 - AndroidUtilities.dp(r63.x);
        float r10 = AndroidUtilities.dp(r63.y) + r2;
        float r11 = (AndroidUtilities.dp(10.0f) * r63.s) * r52;
        r8.setBounds((int) (r92 - r11), (int) (r10 - r11), (int) (r92 + r11), (int) (r10 + r11));
        r8.draw(r25);
        goto L42
    L40:
        r9 = 0.3f;
    L32:
        r8 = this.emoji;
        goto L33
    L43:
        r25.restore();
        return;
    }

    public void drawLine(Canvas r2, RectF r3) {
        drawLine(r2, r3, 1.0f);
    }

    public void drawLine(Canvas r13, RectF r14, float r15) {
        float r0 = SharedConfig.bubbleRadius;
        if (this.sponsored == false) goto L5;
        float r1 = 2.0f;
    L6:
        int r02 = (int) Math.floor(r0 / r1);
        float r12 = r14.left + Math.max(AndroidUtilities.dp(3.0f), AndroidUtilities.dp(r02 * 2));
        int r6 = this.color1Animated.set(this.color1);
        this.color1Paint.setColor(Theme.multAlpha(r6, r15));
        float r3 = this.loadingStateT.set(this.loading);
        float r9 = this.color2Alpha.set(this.hasColor2);
        float r10 = this.color3Alpha.set(this.hasColor3);
        if (r3 <= 0.0f) goto L15;
        if (this.hasColor2 == true) goto L15;
        int r152 = this.color1Paint.getAlpha();
        this.color1Paint.setAlpha((int) (r152 * 0.3f));
        this.rectF.set(r14.left, r14.top, r12, r14.bottom);
        r13.save();
        RectF r16 = this.rectF;
        float r5 = r16.left;
        r13.clipRect(r5, r16.top, AndroidUtilities.dp(3.0f) + r5, this.rectF.bottom);
        float r03 = r02;
        r13.drawRoundRect(this.rectF, AndroidUtilities.dp(r03), AndroidUtilities.dp(r03), this.color1Paint);
        this.color1Paint.setAlpha(r152);
        incrementLoadingT();
        float r153 = ((float) Math.pow((this.loadingT / 240.0f) / 4.0f, 0.8500000238418579d)) * 4.0f;
        this.rectF.set(r14.left, r14.top + (r14.height() * AndroidUtilities.lerp(0.0f, 1.0f - CubicBezierInterpolator.EASE_IN.getInterpolation(MathUtils.clamp(((Math.max(r153, 0.5f) + 1.5f) % 3.5f) * 0.5f, 0.0f, 1.0f)), r3)), r14.left + AndroidUtilities.dp(6.0f), r14.top + (r14.height() * AndroidUtilities.lerp(1.0f, 1.0f - CubicBezierInterpolator.EASE_OUT.getInterpolation(MathUtils.clamp((((r153 + 1.5f) % 3.5f) - 1.5f) * 0.5f, 0.0f, 1.0f)), r3)));
        r13.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), this.color1Paint);
        r13.restore();
        View r122 = this.parentView;
        if (r122 == null) goto L29;
        r122.invalidate();
        return;
    L29:
        return;
    L15:
        if (r9 > 0.0f) goto L18;
        this.rectF.set(r14.left, r14.top, r12, r14.bottom);
        r13.save();
        RectF r142 = this.rectF;
        float r154 = r142.left;
        r13.clipRect(r154, r142.top, AndroidUtilities.dp(3.0f) + r154, this.rectF.bottom);
        float r155 = r02;
        r13.drawRoundRect(this.rectF, AndroidUtilities.dp(r155), AndroidUtilities.dp(r155), this.color1Paint);
        r13.restore();
        return;
    L18:
        r13.save();
        r13.translate(r14.left, r14.top);
        incrementLoadingT();
        if (this.hasColor3 == false) goto L22;
        float r32 = r14.height();
        int r52 = Math.floorMod((int) r14.height(), AndroidUtilities.dp(18.99f));
    L21:
        float r33 = r32 - r52;
        float r53 = this.loadingTranslationT + this.switchStateT.set(this.switchedCount * 425);
        if (this.reversedOut == false) goto L26;
        int r7 = 100;
    L27:
        checkPatternBitmap(r6, this.color2Animated.set(this.color2), this.color3Animated.set(this.color3), r9, r10, r15);
        this.shaderMatrix.setTranslate(0.0f, -((((r53 + r7) / 1000.0f) * AndroidUtilities.dp(30.0f)) % r33));
        this.patternPaint.getShader().setLocalMatrix(this.shaderMatrix);
        this.patternPaint.setAlpha(255);
        this.rectF.set(0.0f, 0.0f, r12 - r14.left, r14.bottom - r14.top);
        RectF r123 = this.rectF;
        float r143 = r123.left;
        r13.clipRect(r143, r123.top, AndroidUtilities.dp(3.0f) + r143, this.rectF.bottom);
        float r144 = r02;
        r13.drawRoundRect(this.rectF, AndroidUtilities.dp(r144), AndroidUtilities.dp(r144), this.patternPaint);
        r13.restore();
        return;
    L26:
        r7 = 0;
        goto L27
    L22:
        r32 = r14.height();
        r52 = Math.floorMod((int) r14.height(), AndroidUtilities.dp(12.66f));
        goto L21
    L5:
        r1 = 3.0f;
        goto L6
    }

    public void drawLoadingBackground(Canvas r6, RectF r7, float r8, float r9, float r10, float r11) {
        float[] r0 = this.radii;
        float r82 = Math.max(AndroidUtilities.dp((int) Math.floor(SharedConfig.bubbleRadius / 3.0f)), AndroidUtilities.dp(r8));
        r0[1] = r82;
        r0[0] = r82;
        float[] r83 = this.radii;
        float r92 = AndroidUtilities.dp(r9);
        r83[3] = r92;
        r83[2] = r92;
        float[] r84 = this.radii;
        float r93 = AndroidUtilities.dp(r10);
        r84[5] = r93;
        r84[4] = r93;
        float[] r85 = this.radii;
        float r94 = Math.max(AndroidUtilities.dp((int) Math.floor(SharedConfig.bubbleRadius / 3.0f)), AndroidUtilities.dp(r10));
        r85[7] = r94;
        r85[6] = r94;
        if (this.loading == true) goto L14;
        LoadingDrawable r86 = this.backgroundLoadingDrawable;
        if (r86 != null) goto L7;
    L9:
        LoadingDrawable r5 = this.backgroundLoadingDrawable;
        if (r5 == null) goto L20;
        r5.reset();
        return;
    L20:
        return;
    L7:
        if (r86.isDisappearing() == false) goto L9;
    L14:
        if (this.backgroundLoadingDrawable != null) goto L16;
        LoadingDrawable r87 = new LoadingDrawable();
        this.backgroundLoadingDrawable = r87;
        r87.setAppearByGradient(true);
        this.backgroundLoadingDrawable.setGradientScale(3.5f);
        this.backgroundLoadingDrawable.setSpeed(0.5f);
    L16:
        this.backgroundLoadingDrawable.setColors(Theme.multAlpha(this.color1, 0.1f), Theme.multAlpha(this.color1, 0.3f), Theme.multAlpha(this.color1, 0.3f), Theme.multAlpha(this.color1, 1.25f));
        this.backgroundLoadingDrawable.setBounds(r7);
        this.backgroundLoadingDrawable.setRadii(this.radii);
        this.backgroundLoadingDrawable.strokePaint.setStrokeWidth(AndroidUtilities.dp(1.0f));
        this.backgroundLoadingDrawable.setAlpha((int) (r11 * 255.0f));
        this.backgroundLoadingDrawable.draw(r6);
        View r52 = this.parentView;
        if (r52 == null) goto L21;
        r52.invalidate();
        return;
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public int getColor() {
        if (this.reversedOut == false) goto L7;
        return this.color2;
    L7:
        return this.color1;
    }

    public boolean hasSticker() {
        if (this.stickerDocumentId == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public ReplyMessageLine offsetEmoji(float r1, float r2) {
        this.emojiOffsetX = r1;
        this.emojiOffsetY = r2;
        return this;
    }

    public void resetAnimation() {
        this.color1Animated.set(this.color1, true);
        this.color2Animated.set(this.color2, true);
        this.color2Alpha.set(this.hasColor2, true);
        this.nameColorAnimated.set(this.nameColor, true);
        this.backgroundColorAnimated.set(this.backgroundColor, true);
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r3 = this.emoji;
        if (r3 == null) goto L6;
        r3.resetAnimation();
        return;
    }

    public void setBackgroundColor(int r1) {
        this.backgroundColor = r1;
    }

    public void setEmojiAlpha(float r1) {
        this.emojiAlpha = r1;
    }

    public int setFactCheck(Theme.ResourcesProvider r7) {
        int r0 = Theme.key_text_RedBold;
        this.nameColor = Theme.getColor(r0, r7);
        this.color1 = Theme.getColor(r0, r7);
        this.hasColor2 = false;
        this.hasColor3 = false;
        this.backgroundColor = Theme.multAlpha(Theme.getColor(r0, r7), 0.1f);
        if (this.emojiDocumentId != 0) goto L5;
    L16:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r72 = this.emoji;
        if (r72 != null) goto L19;
    L21:
        this.emojiColor = getColor();
        return this.nameColorAnimated.set(this.nameColor);
    L19:
        if (r72.set(this.emojiDocumentId, true) == false) goto L21;
        this.emojiLoaded = false;
        goto L21
    L5:
        if (this.emoji != null) goto L16;
        if (this.parentView == null) goto L16;
        this.emoji = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this.parentView, false, AndroidUtilities.dp(20.0f), 13);
        View r73 = this.parentView;
        if ((r73 instanceof ChatMessageCell) == false) goto L14;
        if (((ChatMessageCell) r73).isCellAttachedToWindow() == false) goto L16;
    L15:
        this.emoji.attach();
        goto L16
    L14:
        if (r73.isAttachedToWindow() == false) goto L16;
        goto L15
    }

    public void setLoading(boolean r2) {
        if (r2 == false) goto L4;
    L8:
        if (r2 == true) goto L10;
    L14:
        this.loading = r2;
        return;
    L10:
        if (this.loading == true) goto L14;
        LoadingDrawable r0 = this.backgroundLoadingDrawable;
        if (r0 == null) goto L14;
        r0.resetDisappear();
        this.backgroundLoadingDrawable.reset();
        goto L14
    L4:
        if (this.loading == false) goto L8;
        this.loadingT = 0.0f;
        LoadingDrawable r02 = this.backgroundLoadingDrawable;
        if (r02 == null) goto L14;
        r02.disappear();
        goto L14
    }

    public void setSimpleColor(int r2, boolean r3) {
        this.reversedOut = false;
        this.hasColor3 = false;
        this.hasColor2 = false;
        this.color3 = r2;
        this.color2 = r2;
        this.color1 = r2;
        if (r3 == false) goto L5;
        float r32 = 0.12f;
    L6:
        this.backgroundColor = Theme.multAlpha(r2, r32);
        this.emojiColor = r2;
        return;
    L5:
        r32 = 0.1f;
        goto L6
    }
}
