package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarConstructorFragment;

/* loaded from: classes7.dex */
public abstract class AvatarConstructorPreviewCell extends FrameLayout {
    private AnimatedEmojiDrawable animatedEmojiDrawable;
    int backgroundIndex;
    private final int currentAccount;
    GradientTools currentBackgroundDrawable;
    BackupImageView currentImage;
    int emojiIndex;
    TLRPC.TL_emojiList emojiList;
    public final boolean forUser;
    private boolean isAllEmojiDrawablesLoaded;
    private AnimatedEmojiDrawable nextAnimatedEmojiDrawable;
    GradientTools nextBackgroundDrawable;
    BackupImageView nextImage;
    float progressToNext;
    Runnable scheduleSwitchToNextRunnable;
    TextView textView;

    /* renamed from: org.telegram.ui.Components.AvatarConstructorPreviewCell$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ AvatarConstructorPreviewCell this$0;

        public AnonymousClass1(AvatarConstructorPreviewCell r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            AndroidUtilities.runOnUIThread(this.this$0.scheduleSwitchToNextRunnable, 1000);
            TLRPC.TL_emojiList r0 = this.this$0.emojiList;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.document_id.isEmpty() == true) goto L25;
            AvatarConstructorPreviewCell r02 = this.this$0;
            if (r02.progressToNext == 1.0f) goto L10;
            return;
        L10:
            if (AvatarConstructorPreviewCell.m8958$$Nest$fgetisAllEmojiDrawablesLoaded(r02) == false) goto L12;
        L16:
            AvatarConstructorPreviewCell r03 = this.this$0;
            int r1 = r03.emojiIndex + 1;
            r03.emojiIndex = r1;
            r03.backgroundIndex++;
            if (r1 <= (r03.emojiList.document_id.size() - 1)) goto L19;
            this.this$0.emojiIndex = 0;
        L19:
            AvatarConstructorPreviewCell r04 = this.this$0;
            int r12 = r04.backgroundIndex;
            int[][] r4 = AvatarConstructorFragment.defaultColors;
            if (r12 <= (r4.length - 1)) goto L22;
            r04.backgroundIndex = 0;
        L22:
            int r5 = AvatarConstructorPreviewCell.m8957$$Nest$fgetcurrentAccount(this.this$0);
            AvatarConstructorPreviewCell r6 = this.this$0;
            AvatarConstructorPreviewCell.m8960$$Nest$fputanimatedEmojiDrawable(r04, new AnimatedEmojiDrawable(4, r5, r6.emojiList.document_id.get(r6.emojiIndex).longValue()));
            AvatarConstructorPreviewCell r05 = this.this$0;
            r05.nextImage.setAnimatedEmojiDrawable(AvatarConstructorPreviewCell.m8956$$Nest$fgetanimatedEmojiDrawable(r05));
            AvatarConstructorPreviewCell r06 = this.this$0;
            int[] r13 = r4[r06.backgroundIndex];
            int r3 = r13[0];
            int r2 = r13[1];
            int r42 = r13[2];
            int r14 = r13[3];
            r06.nextBackgroundDrawable = new GradientTools();
            this.this$0.nextBackgroundDrawable.setColors(r3, r2, r42, r14);
            AvatarConstructorPreviewCell r07 = this.this$0;
            r07.progressToNext = 0.0f;
            AvatarConstructorPreviewCell.m8961$$Nest$mpreloadNextEmojiDrawable(r07);
            this.this$0.invalidate();
            return;
        L12:
            if (AvatarConstructorPreviewCell.m8959$$Nest$fgetnextAnimatedEmojiDrawable(this.this$0).getImageReceiver() != null) goto L14;
            return;
        L14:
            if (AvatarConstructorPreviewCell.m8959$$Nest$fgetnextAnimatedEmojiDrawable(this.this$0).getImageReceiver().hasImageLoaded() == true) goto L16;
            return;
        }
    }

    /* renamed from: -$$Nest$fgetanimatedEmojiDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedEmojiDrawable m8956$$Nest$fgetanimatedEmojiDrawable(AvatarConstructorPreviewCell r0) {
        return r0.animatedEmojiDrawable;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8957$$Nest$fgetcurrentAccount(AvatarConstructorPreviewCell r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetisAllEmojiDrawablesLoaded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8958$$Nest$fgetisAllEmojiDrawablesLoaded(AvatarConstructorPreviewCell r0) {
        return r0.isAllEmojiDrawablesLoaded;
    }

    /* renamed from: -$$Nest$fgetnextAnimatedEmojiDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedEmojiDrawable m8959$$Nest$fgetnextAnimatedEmojiDrawable(AvatarConstructorPreviewCell r0) {
        return r0.nextAnimatedEmojiDrawable;
    }

    /* renamed from: -$$Nest$fputanimatedEmojiDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8960$$Nest$fputanimatedEmojiDrawable(AvatarConstructorPreviewCell r0, AnimatedEmojiDrawable r1) {
        r0.animatedEmojiDrawable = r1;
    }

    /* renamed from: -$$Nest$mpreloadNextEmojiDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8961$$Nest$mpreloadNextEmojiDrawable(AvatarConstructorPreviewCell r0) {
        r0.preloadNextEmojiDrawable();
    }

    public AvatarConstructorPreviewCell(Context r8, boolean r9) {
        super(r8);
        int r0 = UserConfig.selectedAccount;
        this.currentAccount = r0;
        this.backgroundIndex = 0;
        this.emojiIndex = 0;
        this.progressToNext = 1.0f;
        this.scheduleSwitchToNextRunnable = new AnonymousClass1(this);
        this.forUser = r9;
        this.emojiList = getOrCreateEmojiList(r0, r9);
        this.currentImage = new BackupImageView(r8);
        this.nextImage = new BackupImageView(r8);
        addView(this.currentImage, LayoutHelper.createFrame(50, 50, 1));
        addView(this.nextImage, LayoutHelper.createFrame(50, 50, 1));
        TLRPC.TL_emojiList r92 = this.emojiList;
        if (r92 != null) goto L5;
    L7:
        int[] r93 = AvatarConstructorFragment.defaultColors[this.backgroundIndex];
        int r02 = r93[0];
        int r1 = r93[1];
        int r2 = r93[2];
        int r94 = r93[3];
        GradientTools r4 = new GradientTools();
        this.currentBackgroundDrawable = r4;
        r4.setColors(r02, r1, r2, r94);
        TextView r95 = new TextView(r8);
        this.textView = r95;
        r95.setTextSize(1, 12.0f);
        this.textView.setTextColor(Theme.getColor(Theme.key_avatar_text));
        this.textView.setTypeface(AndroidUtilities.bold());
        this.textView.setGravity(17);
        this.textView.setText(LocaleController.getString(R.string.UseEmoji));
        addView(this.textView, LayoutHelper.createFrame(-1, 28.0f, 80, 10.0f, 10.0f, 10.0f, 10.0f));
        return;
    L5:
        if (r92.document_id.isEmpty() == true) goto L7;
        AnimatedEmojiDrawable r96 = new AnimatedEmojiDrawable(4, r0, this.emojiList.document_id.get(0).longValue());
        this.animatedEmojiDrawable = r96;
        this.currentImage.setAnimatedEmojiDrawable(r96);
        preloadNextEmojiDrawable();
        goto L7
    }

    public static TLRPC.TL_emojiList getOrCreateEmojiList(int r6, boolean r7) {
        if (r7 == false) goto L4;
        TLRPC.TL_emojiList r72 = MediaDataController.getInstance(r6).profileAvatarConstructorDefault;
    L5:
        if (r72 != null) goto L7;
    L10:
        ArrayList<TLRPC.TL_messages_stickerSet> r73 = MediaDataController.getInstance(r6).getStickerSets(5);
        TLRPC.TL_emojiList r0 = new TLRPC.TL_emojiList();
        int r2 = 0;
        if (r73.isEmpty() == false) goto L25;
        ArrayList<TLRPC.StickerSetCovered> r62 = MediaDataController.getInstance(r6).getFeaturedEmojiSets();
        int r74 = 0;
    L14:
        if (r74 >= r62.size()) goto L30;
        TLRPC.StickerSetCovered r1 = r62.get(r74);
        TLRPC.Document r3 = r1.cover;
        if (r3 == null) goto L19;
        r0.document_id.add(Long.valueOf(r3.id));
    L23:
        r74 = r74 + 1;
        goto L14
    L19:
        if ((r1 instanceof TLRPC.TL_stickerSetFullCovered) == false) goto L23;
        TLRPC.TL_stickerSetFullCovered r12 = (TLRPC.TL_stickerSetFullCovered) r1;
        if (r12.documents.isEmpty() == true) goto L23;
        r0.document_id.add(Long.valueOf(r12.documents.get(0).id));
    L30:
        return r0;
    L25:
        if (r2 >= r73.size()) goto L30;
        TLRPC.TL_messages_stickerSet r63 = r73.get(r2);
        if (r63.documents.isEmpty() == true) goto L29;
        r0.document_id.add(Long.valueOf(r63.documents.get(Math.abs(Utilities.fastRandom.nextInt() % r63.documents.size())).id));
    L29:
        r2 = r2 + 1;
        goto L25
    L7:
        if (r72.document_id.isEmpty() == true) goto L10;
        return r72;
    L4:
        r72 = MediaDataController.getInstance(r6).groupAvatarConstructorDefault;
        goto L5
    }

    private void preloadNextEmojiDrawable() {
        if (this.isAllEmojiDrawablesLoaded == false) goto L5;
        return;
    L5:
        int r0 = this.emojiIndex + 1;
        if (r0 <= (this.emojiList.document_id.size() - 1)) goto L9;
        this.isAllEmojiDrawablesLoaded = true;
        return;
    L9:
        AnimatedEmojiDrawable r1 = new AnimatedEmojiDrawable(4, this.currentAccount, this.emojiList.document_id.get(r0).longValue());
        this.nextAnimatedEmojiDrawable = r1;
        r1.preload();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r17) {
        GradientTools r1 = this.currentBackgroundDrawable;
        if (r1 == null) goto L5;
        r1.setBounds(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
    L5:
        GradientTools r12 = this.nextBackgroundDrawable;
        if (r12 == null) goto L8;
        r12.setBounds(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
    L8:
        float r13 = this.progressToNext;
        if (r13 != 1.0f) goto L11;
        this.currentBackgroundDrawable.paint.setAlpha(255);
        r17.drawRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), this.currentBackgroundDrawable.paint);
        this.currentImage.setAlpha(1.0f);
        this.currentImage.setScaleX(1.0f);
        this.currentImage.setScaleY(1.0f);
        this.nextImage.setAlpha(0.0f);
    L15:
        super.dispatchDraw(r17);
        return;
    L11:
        float r14 = CubicBezierInterpolator.DEFAULT.getInterpolation(r13);
        this.currentBackgroundDrawable.paint.setAlpha(255);
        r17.drawRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), this.currentBackgroundDrawable.paint);
        this.nextBackgroundDrawable.paint.setAlpha((int) (255.0f * r14));
        r17.drawRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), this.nextBackgroundDrawable.paint);
        this.progressToNext += 0.064f;
        float r5 = 1.0f - r14;
        this.currentImage.setAlpha(r5);
        this.currentImage.setScaleX(r5);
        this.currentImage.setScaleY(r5);
        this.currentImage.setPivotY(0.0f);
        this.nextImage.setAlpha(r14);
        this.nextImage.setScaleX(r14);
        this.nextImage.setScaleY(r14);
        this.nextImage.setPivotY(r1.getMeasuredHeight());
        if (this.progressToNext <= 1.0f) goto L14;
        this.progressToNext = 1.0f;
        this.currentBackgroundDrawable = this.nextBackgroundDrawable;
        BackupImageView r15 = this.currentImage;
        this.currentImage = this.nextImage;
        this.nextImage = r15;
    L14:
        invalidate();
        goto L15
    }

    public AnimatedEmojiDrawable getAnimatedEmoji() {
        return this.animatedEmojiDrawable;
    }

    public AvatarConstructorFragment.BackgroundGradient getBackgroundGradient() {
        AvatarConstructorFragment.BackgroundGradient r0 = new AvatarConstructorFragment.BackgroundGradient();
        int[] r2 = AvatarConstructorFragment.defaultColors[this.backgroundIndex];
        r0.color1 = r2[0];
        r0.color2 = r2[1];
        r0.color3 = r2[2];
        r0.color4 = r2[3];
        return r0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        AndroidUtilities.runOnUIThread(this.scheduleSwitchToNextRunnable, 1000);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AndroidUtilities.cancelRunOnUIThread(this.scheduleSwitchToNextRunnable);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        super.onMeasure(r3, r4);
        int r42 = (int) (this.textView.getTop() * 0.7f);
        int r32 = (int) ((r3 - r42) * 0.7f);
        ViewGroup.LayoutParams r0 = this.currentImage.getLayoutParams();
        this.currentImage.getLayoutParams().height = r42;
        r0.width = r42;
        ViewGroup.LayoutParams r02 = this.nextImage.getLayoutParams();
        this.nextImage.getLayoutParams().height = r42;
        r02.width = r42;
        ((FrameLayout.LayoutParams) this.currentImage.getLayoutParams()).topMargin = r32;
        ((FrameLayout.LayoutParams) this.nextImage.getLayoutParams()).topMargin = r32;
    }
}
