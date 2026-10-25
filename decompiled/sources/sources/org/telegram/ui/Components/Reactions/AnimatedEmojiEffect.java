package org.telegram.ui.Components.Reactions;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.EmojiAnimationsOverlay;

/* loaded from: classes7.dex */
public class AnimatedEmojiEffect {
    private static int currentIndex;
    public AnimatedEmojiDrawable animatedEmojiDrawable;
    int animationIndex;
    Rect bounds;
    int currentAccount;
    ImageReceiver effectImageReceiver;
    boolean firsDraw;
    long lastGenerateTime;
    boolean longAnimation;
    View parentView;
    ArrayList<Particle> particles;
    boolean showGeneric;
    long startTime;

    public class Particle {
        long duration;
        float fromSize;
        float fromX;
        float fromY;
        boolean mirror;
        float progress;
        float randomRotation;
        final /* synthetic */ AnimatedEmojiEffect this$0;
        float toSize;
        float toX;
        float toY1;
        float toY2;

        private Particle(AnimatedEmojiEffect r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Particle(AnimatedEmojiEffect r1, AnimatedEmojiEffectIA r2) {
            this(r1);
        }

        private float randX() {
            if (this.this$0.longAnimation == false) goto L7;
            return (r0.bounds.width() * (-0.25f)) + ((this.this$0.bounds.width() * 1.5f) * (Math.abs(Utilities.fastRandom.nextInt() % 100) / 100.0f));
        L7:
            return r0.bounds.width() * (Math.abs(Utilities.fastRandom.nextInt() % 100) / 100.0f);
        }

        private float randY() {
            return (this.this$0.bounds.height() * 0.5f) * (Math.abs(Utilities.fastRandom.nextInt() % 100) / 100.0f);
        }

        public void draw(Canvas r10) {
            float r0 = this.progress + (Math.min(40.0f, 1000.0f / AndroidUtilities.screenRefreshRate) / this.duration);
            this.progress = r0;
            float r02 = Utilities.clamp(r0, 1.0f, 0.0f);
            this.progress = r02;
            CubicBezierInterpolator r3 = CubicBezierInterpolator.EASE_OUT;
            float r03 = r3.getInterpolation(r02);
            float r4 = AndroidUtilities.lerp(this.fromX, this.toX, r03);
            boolean r5 = this.this$0.longAnimation;
            float r52 = this.progress;
            if (r52 >= 0.3f) goto L5;
            float r32 = AndroidUtilities.lerp(this.fromY, this.toY1, r3.getInterpolation(r52 / 0.3f));
        L6:
            float r04 = AndroidUtilities.lerp(this.fromSize, this.toSize, r03);
            if (this.this$0.longAnimation == true) goto L11;
            float r53 = r5.bounds.height() * 0.8f;
            if (r32 <= r53) goto L11;
            float r54 = 1.0f - Utilities.clamp((r32 - r53) / AndroidUtilities.dp(16.0f), 1.0f, 0.0f);
        L12:
            float r05 = (r04 / 2.0f) * r54;
            r10.save();
            if (this.mirror == false) goto L15;
            r10.scale(-1.0f, 1.0f, r4, r32);
        L15:
            r10.rotate(this.randomRotation, r4, r32);
            this.this$0.animatedEmojiDrawable.setAlpha((int) ((r54 * 255.0f) * Utilities.clamp(this.progress / 0.2f, 1.0f, 0.0f)));
            this.this$0.animatedEmojiDrawable.setBounds((int) (r4 - r05), (int) (r32 - r05), (int) (r4 + r05), (int) (r32 + r05));
            this.this$0.animatedEmojiDrawable.draw(r10);
            this.this$0.animatedEmojiDrawable.setAlpha(255);
            r10.restore();
            return;
        L11:
            r54 = 1.0f;
            goto L12
        L5:
            r32 = AndroidUtilities.lerp(this.toY1, this.toY2, CubicBezierInterpolator.EASE_IN.getInterpolation((r52 - 0.3f) / 0.7f));
            goto L6
        }

        public void generate() {
            float r0 = 0.0f;
            this.progress = 0.0f;
            float r1 = randX();
            float r2 = randY();
            int r4 = 0;
        L4:
            if (r4 >= 20) goto L17;
            float r5 = randX();
            float r6 = randY();
            float r7 = 2.1474836E9f;
            int r8 = 0;
        L7:
            if (r8 >= this.this$0.particles.size()) goto L13;
            float r9 = this.this$0.particles.get(r8).toX - r5;
            float r10 = this.this$0.particles.get(r8).toY1 - r6;
            float r92 = (r9 * r9) + (r10 * r10);
            if (r92 >= r7) goto L11;
            r7 = r92;
        L11:
            r8 = r8 + 1;
            goto L7
        L13:
            if (r7 <= r0) goto L15;
            r1 = r5;
            r2 = r6;
            r0 = r7;
        L15:
            r4 = r4 + 1;
            goto L4
        L17:
            if (this.this$0.longAnimation == false) goto L19;
            float r3 = 0.8f;
        L20:
            this.toX = r1;
            float r02 = r0.bounds.width() * r3;
            AnimatedEmojiEffect r12 = this.this$0;
            if (r1 <= r02) goto L23;
            this.fromX = r12.bounds.width() * r3;
        L26:
            this.fromY = (this.this$0.bounds.height() * 0.45f) + ((this.this$0.bounds.height() * 0.1f) * (Math.abs(Utilities.fastRandom.nextInt() % 100) / 100.0f));
            if (this.this$0.longAnimation == false) goto L29;
            float r03 = (r0.bounds.width() * 0.05f) + ((this.this$0.bounds.width() * 0.1f) * (Math.abs(Utilities.fastRandom.nextInt() % 100) / 100.0f));
            this.fromSize = r03;
            this.toSize = r03 * (((Math.abs(Utilities.fastRandom.nextInt() % 100) / 100.0f) * 1.5f) + 1.5f);
            this.toY1 = (this.fromSize / 2.0f) + ((this.this$0.bounds.height() * 0.1f) * (Math.abs(Utilities.fastRandom.nextInt() % 100) / 100.0f));
            this.toY2 = this.this$0.bounds.height() + this.fromSize;
            this.duration = Math.abs(Utilities.fastRandom.nextInt() % MediaError.DetailedErrorCode.TEXT_UNKNOWN) + 1000;
        L30:
            this.duration = (long) (this.duration / 1.75f);
            this.mirror = Utilities.fastRandom.nextBoolean();
            this.randomRotation = ((Utilities.fastRandom.nextInt() % 100) / 100.0f) * 20.0f;
            return;
        L29:
            float r04 = (r0.bounds.width() * 0.05f) + ((this.this$0.bounds.width() * 0.1f) * (Math.abs(Utilities.fastRandom.nextInt() % 100) / 100.0f));
            this.fromSize = r04;
            this.toSize = r04 * (((Math.abs(Utilities.fastRandom.nextInt() % 100) / 100.0f) * 0.5f) + 1.5f);
            this.toY1 = r2;
            this.toY2 = r2 + this.this$0.bounds.height();
            this.duration = 1800;
            goto L30
        L23:
            float r05 = r12.bounds.width() * r3;
            this.fromX = r05;
            if (this.toX <= r05) goto L26;
            this.toX = r05 - 0.1f;
            goto L26
        L19:
            r3 = 0.5f;
            goto L20
        }
    }

    private AnimatedEmojiEffect(AnimatedEmojiDrawable r3, int r4, boolean r5, boolean r6) {
        this.bounds = new Rect();
        this.particles = new ArrayList();
        this.firsDraw = true;
        this.animationIndex = -1;
        this.animatedEmojiDrawable = r3;
        this.longAnimation = r5;
        this.currentAccount = r4;
        this.showGeneric = r6;
        this.startTime = System.currentTimeMillis();
        if (r6 == true) goto L5;
        return;
    L5:
        if (LiteMode.isEnabled(LiteMode.FLAG_ANIMATED_EMOJI_CHAT) == false) goto L11;
        ImageReceiver r32 = new ImageReceiver();
        this.effectImageReceiver = r32;
        if (r5 == false) goto L12;
        r32.setAllowDrawWhileCacheGenerating(true);
        return;
    L12:
        return;
    }

    public static AnimatedEmojiEffect createFrom(AnimatedEmojiDrawable r2, boolean r3, boolean r4) {
        return new AnimatedEmojiEffect(r2, UserConfig.selectedAccount, r3, r4);
    }

    public void draw(Canvas r12) {
        if (this.longAnimation == false) goto L5;
        long r3 = System.currentTimeMillis();
        if (this.particles.size() >= 12) goto L21;
        long r5 = this.startTime;
        if ((r3 - r5) >= 1500) goto L21;
        if ((r3 - r5) <= 200) goto L21;
        if ((r3 - this.lastGenerateTime) <= 50) goto L21;
        if ((Utilities.fastRandom.nextInt() % 6) != 0) goto L21;
        Particle r0 = new Particle(this, null);
        r0.generate();
        this.particles.add(r0);
        this.lastGenerateTime = r3;
    L21:
        ImageReceiver r02 = this.effectImageReceiver;
        if (r02 != null) goto L24;
    L34:
        r12.save();
        Rect r03 = this.bounds;
        r12.translate(r03.left, r03.top);
        int r04 = 0;
    L36:
        if (r04 >= this.particles.size()) goto L41;
        this.particles.get(r04).draw(r12);
        if (this.particles.get(r04).progress < 1.0f) goto L40;
        this.particles.remove(r04);
        r04 = r04 - 1;
    L40:
        r04 = r04 + 1;
        goto L36
    L41:
        r12.restore();
        View r122 = this.parentView;
        if (r122 == null) goto L44;
        r122.invalidate();
    L44:
        this.firsDraw = false;
        return;
    L24:
        if (this.showGeneric == false) goto L34;
        if (r02.getLottieAnimation() == null) goto L31;
        if (this.effectImageReceiver.getLottieAnimation().isLastFrame() == true) goto L34;
    L31:
        if (this.longAnimation == false) goto L33;
        r12.save();
        r12.translate(this.bounds.width() / 3.0f, 0.0f);
        this.effectImageReceiver.draw(r12);
        r12.restore();
        goto L34
    L33:
        this.effectImageReceiver.draw(r12);
        goto L34
    L5:
        if (this.firsDraw == false) goto L21;
        int r05 = 0;
    L8:
        if (r05 >= 7) goto L21;
        Particle r32 = new Particle(this, null);
        r32.generate();
        this.particles.add(r32);
        r05 = r05 + 1;
        goto L8
    }

    public boolean isDone() {
        if ((System.currentTimeMillis() - this.startTime) <= 2500) goto L6;
        return true;
    L6:
        return false;
    }

    public void removeView(View r2) {
        this.animatedEmojiDrawable.removeView(r2);
        ImageReceiver r22 = this.effectImageReceiver;
        if (r22 == null) goto L6;
        r22.onDetachedFromWindow();
        this.effectImageReceiver.clearImage();
        return;
    }

    public void setBounds(int r2, int r3, int r4, int r5) {
        this.bounds.set(r2, r3, r4, r5);
        ImageReceiver r22 = this.effectImageReceiver;
        if (r22 == null) goto L6;
        r22.setImageCoords(this.bounds);
        return;
    }

    public void setView(View r18) {
        this.animatedEmojiDrawable.addView(r18);
        this.parentView = r18;
        ImageReceiver r1 = this.effectImageReceiver;
        if (r1 != null) goto L5;
        return;
    L5:
        if (this.showGeneric == false) goto L42;
        r1.onAttachedToWindow();
        TLRPC.TL_messages_stickerSet r2 = null;
        String r12 = MessageObject.findAnimatedEmojiEmoticon(this.animatedEmojiDrawable.getDocument(), null);
        if (r12 == null) goto L17;
        TLRPC.TL_availableReaction r13 = MediaDataController.getInstance(this.currentAccount).getReactionsMap().get(r12);
        if (r13 == null) goto L17;
        TLRPC.Document r8 = r13.around_animation;
        if (r8 == null) goto L17;
        boolean r9 = this.longAnimation;
        ImageReceiver r10 = this.effectImageReceiver;
        if (r9 == false) goto L15;
        StringBuilder r82 = new StringBuilder();
        int r92 = currentIndex;
        currentIndex = r92 + 1;
        r82.append(r92);
        r82.append(" ");
        r10.setUniqKeyPrefix(r82.toString());
        int r83 = EmojiAnimationsOverlay.getFilterWidth();
        this.effectImageReceiver.setImage(ImageLocation.getForDocument(r13.around_animation), r83 + "_" + r83 + "_pcache_compress", null, null, r13.around_animation, 0);
    L16:
        boolean r14 = true;
    L18:
        if (r14 == true) goto L33;
        String r84 = UserConfig.getInstance(this.currentAccount).genericAnimationsStickerPack;
        if (r84 == null) goto L24;
        r2 = MediaDataController.getInstance(this.currentAccount).getStickerSetByName(r84);
        if (r2 != null) goto L24;
        r2 = MediaDataController.getInstance(this.currentAccount).getStickerSetByEmojiOrName(r84);
    L24:
        if (r2 == null) goto L33;
        if (this.animationIndex >= 0) goto L28;
        this.animationIndex = Math.abs(Utilities.fastRandom.nextInt() % r2.documents.size());
    L28:
        boolean r15 = this.longAnimation;
        ImageReceiver r85 = this.effectImageReceiver;
        if (r15 == false) goto L31;
        StringBuilder r16 = new StringBuilder();
        int r93 = currentIndex;
        currentIndex = r93 + 1;
        r16.append(r93);
        r16.append(" ");
        r85.setUniqKeyPrefix(r16.toString());
        int r17 = EmojiAnimationsOverlay.getFilterWidth();
        this.effectImageReceiver.setImage(ImageLocation.getForDocument(r2.documents.get(this.animationIndex)), r17 + "_" + r17 + "_pcache_compress", null, null, r2.documents.get(this.animationIndex), 0);
    L32:
        r14 = true;
        goto L33
    L31:
        r85.setImage(ImageLocation.getForDocument(r2.documents.get(this.animationIndex)), "60_60", null, null, r2.documents.get(this.animationIndex), 0);
    L33:
        if (r14 == true) goto L35;
        this.effectImageReceiver.setImageBitmap(new RLottieDrawable(R.raw.custom_emoji_reaction, AndroidUtilities.dp(60.0f), AndroidUtilities.dp(60.0f), false, null));
        return;
    L35:
        if (this.effectImageReceiver.getLottieAnimation() == null) goto L37;
        this.effectImageReceiver.getLottieAnimation().setCurrentFrame(0, false, true);
    L37:
        this.effectImageReceiver.setAutoRepeat(0);
        return;
    L15:
        r10.setImage(ImageLocation.getForDocument(r8), ReactionsEffectOverlay.getFilterForAroundAnimation(), null, null, r13.around_animation, 0);
    L17:
        r14 = false;
        goto L18
    }
}
