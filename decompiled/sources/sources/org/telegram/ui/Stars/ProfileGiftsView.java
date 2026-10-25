package org.telegram.ui.Stars;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import java.util.ArrayList;
import java.util.HashSet;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stars.StarsReactionsSheet;

/* loaded from: classes7.dex */
public class ProfileGiftsView extends View implements NotificationCenter.NotificationCenterDelegate {
    private float actionBarProgress;
    private boolean active;
    private final View avatarContainer;
    private final ProfileActivity.AvatarImageView avatarImage;
    public float collapseProgress;
    private final int currentAccount;
    private float cy;
    private final long dialogId;
    public float expandProgress;
    private float expandY;
    private final TimeInterpolator giftCollapseXInterpolator;
    private final TimeInterpolator giftCollapseYInterpolator;
    public final HashSet<Long> giftIds;
    public final ArrayList<Gift> gifts;
    public boolean isOpening;
    private float left;
    private StarsController.GiftsList list;
    public int maxCount;
    private float maxExpandY;
    public final ArrayList<Gift> oldGifts;
    private Gift pressedGift;
    private float progressToInsets;
    private final Theme.ResourcesProvider resourcesProvider;
    private float right;
    private final AnimatedFloat rightAnimated;

    public final class Gift {
        public AnimatedFloat animatedFloat;
        public final ButtonBounce bounce;
        public final RectF bounds;
        public final int color;
        public final TLRPC.Document document;
        public final long documentId;
        public AnimatedEmojiDrawable emojiDrawable;
        public RadialGradient gradient;
        public final Matrix gradientMatrix;
        public Paint gradientPaint;
        public final long id;
        private StarsReactionsSheet.Particles particles;
        public int position;
        public final String slug;
        final /* synthetic */ ProfileGiftsView this$0;

        public Gift(ProfileGiftsView r3, TL_stars.TL_starGiftUnique r4) {
            this.this$0 = r3;
            this.position = -1;
            this.gradientMatrix = new Matrix();
            this.bounds = new RectF();
            this.bounce = new ButtonBounce(r3);
            this.id = r4.id;
            TLRPC.Document r32 = r4.getDocument();
            this.document = r32;
            if (r32 != null) goto L5;
            long r0 = 0;
        L6:
            this.documentId = r0;
            this.color = ((TL_stars.starGiftAttributeBackdrop) StarsController.findAttribute(r4.attributes, TL_stars.starGiftAttributeBackdrop.class)).center_color | (-16777216);
            this.slug = r4.slug;
            initParticles();
            return;
        L5:
            r0 = r32.id;
            goto L6
        }

        private void initParticles() {
            this.particles = new StarsReactionsSheet.Particles(1, 6);
            float r0 = AndroidUtilities.dp(36.0f);
            float r1 = (-r0) / 2.0f;
            float r02 = r0 / 2.0f;
            this.particles.bounds.set(r1, r1, r02, r02);
        }

        public void copy(Gift r2) {
            this.gradient = r2.gradient;
            this.emojiDrawable = r2.emojiDrawable;
            this.gradientPaint = r2.gradientPaint;
            this.animatedFloat = r2.animatedFloat;
            this.particles = r2.particles;
            this.position = r2.position;
        }

        public void draw(Canvas r11, float r12, float r13, float r14, float r15, float r16, float r17) {
            if (r16 > 0.0f) goto L5;
            return;
        L5:
            float r1 = AndroidUtilities.dp(45.0f);
            float r7 = r1 / 2.0f;
            this.bounds.set(r12 - r7, r13 - r7, r12 + r7, r13 + r7);
            r11.save();
            r11.translate(r12, r13);
            r11.rotate(r15);
            float r142 = r14 * this.bounce.getScale(0.1f);
            r11.scale(r142, r142);
            this.particles.process();
            this.particles.draw(r11, this.color, r16);
            Paint r122 = this.gradientPaint;
            if (r122 == null) goto L9;
            r122.setAlpha((int) ((r16 * 255.0f) * r17));
            float r5 = (-r1) / 2.0f;
            r11.drawRect(r5, r5, r7, r7, this.gradientPaint);
        L9:
            if (this.emojiDrawable == null) goto L11;
            int r123 = AndroidUtilities.dp(24.0f);
            int r18 = (-r123) / 2;
            int r124 = r123 / 2;
            this.emojiDrawable.setBounds(r18, r18, r124, r124);
            this.emojiDrawable.setAlpha((int) (255.0f * r16));
            this.emojiDrawable.draw(r11);
        L11:
            r11.restore();
        }

        public boolean equals(Gift r3) {
            if (r3 != null) goto L4;
            return false;
        L4:
            if (r3.id != this.id) goto L9;
            return true;
        L9:
            return false;
        }
    }

    public ProfileGiftsView(Context r8, int r9, long r10, View r12, ProfileActivity.AvatarImageView r13, Theme.ResourcesProvider r14) {
        super(r8);
        this.active = true;
        this.rightAnimated = new AnimatedFloat(this, 0, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.progressToInsets = 1.0f;
        this.oldGifts = new ArrayList();
        this.gifts = new ArrayList();
        this.giftIds = new HashSet();
        this.giftCollapseXInterpolator = new DecelerateInterpolator();
        this.giftCollapseYInterpolator = new LinearInterpolator();
        this.currentAccount = r9;
        this.dialogId = r10;
        this.avatarContainer = r12;
        this.avatarImage = r13;
        this.resourcesProvider = r14;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 == NotificationCenter.starUserGiftsLoaded) goto L5;
        return;
    L5:
        if (((Long) r5[0]).longValue() != this.dialogId) goto L9;
        update();
        return;
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas r28) {
        if (this.gifts.isEmpty() == true) goto L50;
        float r2 = 1.0f;
        if (this.expandProgress < 1.0f) goto L7;
        return;
    L7:
        if (this.collapseProgress <= 0.0f) goto L52;
        float r1 = this.avatarContainer.getX();
        float r4 = this.avatarContainer.getY();
        float r5 = this.avatarContainer.getWidth() * this.avatarContainer.getScaleX();
        float r6 = this.avatarContainer.getHeight() * this.avatarContainer.getScaleY();
        float r7 = AndroidUtilities.dpf2(96.0f);
        float r9 = 2.0f;
        float r8 = Math.min(r1, (getWidth() - r7) / 2.0f);
        float r10 = Math.max(r4, (this.maxExpandY - r7) / 2.0f);
        float r11 = Math.max(r5, r7);
        float r72 = Math.max(r6, r7);
        r28.save();
        Canvas r15 = r28;
        r15.clipRect(0.0f, 0.0f, getWidth(), this.expandY);
        float r82 = r8 + (r11 / 2.0f);
        float r112 = (r72 / 2.0f) + r10;
        float r12 = r1 + (r5 / 2.0f);
        float r42 = r4 + (r6 / 2.0f);
        float r52 = this.expandY;
        float r62 = r52 / this.maxExpandY;
        float r53 = Utilities.clamp01((r52 - (AndroidUtilities.statusBarHeight + ActionBar.getCurrentActionBarHeight())) / AndroidUtilities.dp(50.0f));
        int r122 = 0;
    L11:
        if (r122 >= this.gifts.size()) goto L45;
        Gift r14 = this.gifts.get(r122);
        float r13 = r14.animatedFloat.set(r2);
        float r3 = AndroidUtilities.lerp(0.5f, r2, r13);
        float r22 = r2;
        float r23 = r9;
        float r20 = (((r22 - this.expandProgress) * r13) * (r22 - this.actionBarProgress)) * r53;
        int r24 = r14.position;
        if (r24 != 0) goto L15;
        float r21 = (r82 / r23) - (AndroidUtilities.dp(20.0f) * r62);
        int r25 = AndroidUtilities.dp(13.0f);
    L26:
        float r26 = r112 - r25;
        float r242 = r53;
        float r252 = r62;
        float r54 = r21;
        float r92 = 1.6f;
    L31:
        if (this.isOpening == false) goto L33;
    L36:
        float r63 = this.collapseProgress;
    L37:
        float r93 = r92 * 0.2f;
        if (r63 < (r22 - r93)) goto L40;
        float r64 = r22;
    L42:
        if (r64 >= r22) goto L44;
        r54 = AndroidUtilities.lerp(r12, r54, this.giftCollapseXInterpolator.getInterpolation(r64));
        r26 = AndroidUtilities.lerp(r42, r26, this.giftCollapseYInterpolator.getInterpolation(r64));
        r3 = AndroidUtilities.lerp(r3 / r23, r3, r64);
    L44:
        r14.draw(r15, r54, r26, r3, 0.0f, r20, 1.0f);
        r122 = r122 + 1;
        r15 = r28;
        r2 = r22;
        r9 = r23;
        r53 = r242;
        r62 = r252;
        goto L11
    L40:
        r64 = Utilities.clamp01(((r63 - 0.32000002f) + r93) / 0.67999995f);
        goto L42
    L33:
        if (r13 >= r22) goto L36;
        r63 = Math.min(r13, this.collapseProgress);
        goto L37
    L15:
        if (r24 != 1) goto L17;
        r242 = r53;
        r252 = r62;
        r54 = ((r82 * r23) / 3.0f) - (AndroidUtilities.dp(6.0f) * r62);
        r26 = r10 - AndroidUtilities.dp(4.0f);
    L23:
        r92 = 0.0f;
        goto L31
    L17:
        if (r24 != 2) goto L19;
        r26 = (r10 + r72) - AndroidUtilities.dp(16.0f);
        r252 = r62;
        r92 = 0.9f;
        r242 = r53;
        r54 = ((r82 * r23) / 3.0f) - (AndroidUtilities.dp(12.0f) * r62);
        goto L31
    L19:
        if (r24 != 3) goto L21;
        r21 = (1.5f * r82) + (AndroidUtilities.dp(20.0f) * r62);
        r25 = AndroidUtilities.dp(13.0f);
        goto L26
    L21:
        if (r24 == 4) goto L24;
        r26 = (r10 + r72) - AndroidUtilities.dp(16.0f);
        r242 = r53;
        r252 = r62;
        r54 = ((4.0f * r82) / 3.0f) + (AndroidUtilities.dp(12.0f) * r62);
        goto L23
    L24:
        float r253 = r53;
        r54 = ((r82 * 4.0f) / 3.0f) + (AndroidUtilities.dp(12.0f) * r62);
        r26 = r10 - AndroidUtilities.dp(4.0f);
        r92 = 0.9f;
        r242 = r253;
        r252 = r62;
        goto L31
    L45:
        r28.restore();
        return;
    L52:
        return;
    }

    public Gift getGiftUnder(float r3, float r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.gifts.size()) goto L10;
        if (this.gifts.get(r0).bounds.contains(r3, r4) == true) goto L8;
        r0 = r0 + 1;
        goto L4
    L8:
        return this.gifts.get(r0);
    L10:
        return null;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starUserGiftsLoaded);
        ArrayList<Gift> r0 = this.gifts;
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        Gift r3 = r0.get(r2);
        r2 = r2 + 1;
        r3.emojiDrawable.addView(this);
        goto L3
    L5:
        update();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starUserGiftsLoaded);
        ArrayList<Gift> r0 = this.gifts;
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        Gift r3 = r0.get(r2);
        r2 = r2 + 1;
        r3.emojiDrawable.removeView(this);
        goto L3
    }

    public void onGiftClick(Gift r3) {
        Browser.openUrl(getContext(), "https://t.me/nft/" + r3.slug);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r7) {
        if (this.active == true) goto L5;
        return false;
    L5:
        Gift r0 = getGiftUnder(r7.getX(), r7.getY());
        if (r7.getAction() != 0) goto L11;
        this.pressedGift = r0;
        if (r0 == null) goto L27;
        r0.bounce.setPressed(true);
    L27:
        if (this.pressedGift == null) goto L29;
        return true;
    L29:
        return false;
    L11:
        if (r7.getAction() != 2) goto L17;
        Gift r72 = this.pressedGift;
        if (r72 == r0) goto L27;
        if (r72 == null) goto L27;
        r72.bounce.setPressed(false);
        this.pressedGift = null;
        goto L27
    L17:
        if (r7.getAction() != 1) goto L22;
        Gift r73 = this.pressedGift;
        if (r73 == null) goto L27;
        onGiftClick(r73);
        this.pressedGift.bounce.setPressed(false);
        this.pressedGift = null;
        goto L27
    L22:
        if (r7.getAction() != 3) goto L27;
        Gift r74 = this.pressedGift;
        if (r74 == null) goto L27;
        r74.bounce.setPressed(false);
        this.pressedGift = null;
        goto L27
    }

    public void setActionBarActionMode(float r1) {
        this.actionBarProgress = r1;
        invalidate();
    }

    public void setActive(boolean r1) {
        this.active = r1;
    }

    public void setBounds(float r4, float r5, float r6, boolean r7, int r8) {
        if (Math.abs(r4 - this.left) <= 0.1f) goto L5;
    L10:
        boolean r0 = true;
    L11:
        this.left = r4;
        this.right = r5;
        if (r7 == true) goto L14;
        this.rightAnimated.set(r5, true);
    L14:
        this.cy = r6;
        this.maxExpandY = r8 + r6;
        if (r0 == false) goto L18;
        invalidate();
        return;
    L18:
        return;
    L5:
        if (Math.abs(r5 - this.right) > 0.1f) goto L10;
        if (Math.abs(r6 - this.cy) > 0.1f) goto L10;
        r0 = false;
        goto L11
    }

    public void setCollapseProgress(float r1, boolean r2) {
        this.isOpening = r2;
        float r12 = Utilities.clamp01((r1 - 0.3f) / 0.7f);
        if (this.collapseProgress == r12) goto L6;
        this.collapseProgress = r12;
        invalidate();
        return;
    }

    public void setExpandCoords(float r1) {
        this.expandY = r1;
        invalidate();
    }

    public void setExpandProgress(float r2) {
        if (this.expandProgress == r2) goto L6;
        this.expandProgress = r2;
        invalidate();
        return;
    }

    public void setProgressToStoriesInsets(float r2) {
        if (this.progressToInsets != r2) goto L5;
        return;
    L5:
        this.progressToInsets = r2;
        invalidate();
    }

    public void update() {
        if (MessagesController.getInstance(this.currentAccount).enableGiftsInProfile == false) goto L120;
        this.maxCount = MessagesController.getInstance(this.currentAccount).stargiftsPinnedToTopLimit;
        this.oldGifts.clear();
        this.oldGifts.addAll(this.gifts);
        this.gifts.clear();
        this.giftIds.clear();
        long r0 = this.dialogId;
        int r1 = this.currentAccount;
        if (r0 < 0) goto L11;
        TLRPC.User r02 = MessagesController.getInstance(r1).getUser(Long.valueOf(this.dialogId));
        if (r02 != null) goto L10;
    L9:
        TLRPC.EmojiStatus r03 = null;
    L16:
        if ((r03 instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L18;
        this.giftIds.add(Long.valueOf(((TLRPC.TL_emojiStatusCollectible) r03).collectible_id));
    L18:
        StarsController.GiftsList r04 = StarsController.getInstance(this.currentAccount).getProfileGiftsList(this.dialogId);
        this.list = r04;
        int r12 = 0;
        if (r04 == null) goto L34;
        int r05 = 0;
    L22:
        if (r05 >= this.list.gifts.size()) goto L34;
        TL_stars.SavedStarGift r3 = this.list.gifts.get(r05);
        if (r3.unsaved == true) goto L32;
        if (r3.pinned_to_top == false) goto L32;
        TL_stars.StarGift r32 = r3.gift;
        if ((r32 instanceof TL_stars.TL_starGiftUnique) == false) goto L32;
        Gift r4 = new Gift(this, (TL_stars.TL_starGiftUnique) r32);
        if (this.giftIds.contains(Long.valueOf(r4.id)) == true) goto L32;
        this.gifts.add(r4);
        this.giftIds.add(Long.valueOf(r4.id));
    L32:
        r05 = r05 + 1;
    L34:
        if (this.gifts.size() == this.oldGifts.size()) goto L36;
    L35:
        boolean r06 = true;
    L44:
        int r33 = 0;
    L46:
        if (r33 >= this.gifts.size()) goto L65;
        Gift r5 = this.gifts.get(r33);
        int r6 = 0;
    L49:
        if (r6 >= this.oldGifts.size()) goto L54;
        if (this.oldGifts.get(r6).id == r5.id) goto L52;
        r6 = r6 + 1;
        goto L49
    L52:
        Gift r62 = this.oldGifts.get(r6);
    L55:
        if (r62 == null) goto L57;
        r5.copy(r62);
        ProfileGiftsView r8 = this;
    L64:
        r33 = r33 + 1;
        ProfileGiftsView this = r8;
        goto L46
    L57:
        float r10 = AndroidUtilities.dp(22.5f);
        int r63 = r5.color;
        r5.gradient = new RadialGradient(0.0f, 0.0f, r10, new int[]{r63, Theme.multAlpha(r63, 0.0f)}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
        Paint r64 = new Paint(1);
        r5.gradientPaint = r64;
        r64.setShader(r5.gradient);
        TLRPC.Document r65 = r5.document;
        int r7 = this.currentAccount;
        if (r65 == null) goto L60;
        r5.emojiDrawable = AnimatedEmojiDrawable.make(r7, 0, r65);
    L61:
        r8 = this;
        AnimatedFloat r72 = new AnimatedFloat(r8, 0, 320, null);
        r5.animatedFloat = r72;
        r72.force(0.0f);
        if (r8.isAttachedToWindow() == false) goto L64;
        r5.emojiDrawable.addView(r8);
        goto L64
    L60:
        r5.emojiDrawable = AnimatedEmojiDrawable.make(r7, 0, r5.documentId);
        goto L61
    L54:
        r62 = null;
        goto L55
    L65:
        ProfileGiftsView r82 = this;
        ArrayList r15 = new ArrayList();
        int r34 = 0;
    L67:
        if (r34 >= r82.maxCount) goto L69;
        r15.add(Integer.valueOf(r34));
        r34 = r34 + 1;
        goto L67
    L69:
        int r35 = 0;
    L71:
        if (r35 >= r82.oldGifts.size()) goto L85;
        Gift r42 = r82.oldGifts.get(r35);
        int r52 = 0;
    L74:
        if (r52 >= r82.gifts.size()) goto L79;
        if (r82.gifts.get(r52).id == r42.id) goto L77;
        r52 = r52 + 1;
        goto L74
    L77:
        Gift r53 = r82.gifts.get(r52);
    L80:
        if (r53 != null) goto L82;
        r42.emojiDrawable.removeView(r82);
        r42.emojiDrawable = null;
        r42.gradient = null;
    L83:
        r35 = r35 + 1;
        goto L71
    L82:
        r15.remove(Integer.valueOf(r42.position));
        goto L83
    L79:
        r53 = null;
        goto L80
    L85:
        if (r15.isEmpty() == true) goto L93;
        BagRandomizer r2 = new BagRandomizer(r15);
    L88:
        if (r12 >= r82.gifts.size()) goto L93;
        Gift r152 = r82.gifts.get(r12);
        if (r152.position != (-1)) goto L92;
        r152.position = ((Integer) r2.next()).intValue();
    L92:
        r12 = r12 + 1;
    L93:
        if (r06 == false) goto L119;
        r82.invalidate();
        return;
    L119:
        return;
    L36:
        int r07 = 0;
    L38:
        if (r07 >= this.gifts.size()) goto L43;
        if (this.gifts.get(r07).equals(this.oldGifts.get(r07)) == false) goto L35;
        r07 = r07 + 1;
        goto L38
    L43:
        r06 = false;
        goto L44
    L10:
        r03 = r02.emoji_status;
        goto L16
    L11:
        TLRPC.User r08 = MessagesController.getInstance(r1).getUser(Long.valueOf(-this.dialogId));
        if (r08 == null) goto L9;
        r03 = r08.emoji_status;
        goto L16
    }
}
