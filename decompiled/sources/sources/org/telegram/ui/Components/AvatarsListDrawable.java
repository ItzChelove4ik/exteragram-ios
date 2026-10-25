package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.vkryl.android.animator.ListAnimator;
import me.vkryl.core.lambda.Destroyable;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class AvatarsListDrawable extends Drawable {
    private int alpha;
    private final ListAnimator<AvatarItem> animator;
    private boolean attached;
    private final ArrayList<AvatarItem> avatarItemsPool;
    private final int avatarOffset;
    private final int avatarSize;
    private final float avatarStroke;
    private final int currentAccount;
    private final View parent;

    /* renamed from: org.telegram.ui.Components.AvatarsListDrawable$1, reason: invalid class name */
    public class AnonymousClass1 implements ListAnimator.Callback {
        final /* synthetic */ AvatarsListDrawable this$0;

        public AnonymousClass1(AvatarsListDrawable r1) {
            this.this$0 = r1;
        }

        @Override // me.vkryl.android.animator.ListAnimator.Callback
        public void onItemsChanged(ListAnimator<?> r1) {
            AvatarsListDrawable.m8982$$Nest$fgetparent(this.this$0).invalidate();
        }
    }

    public class AvatarItem implements ListAnimator.Measurable, Destroyable {
        private boolean attached;
        private final AvatarDrawable avatarDrawable;
        private long dialogId;
        private final ImageReceiver imageReceiver;
        final /* synthetic */ AvatarsListDrawable this$0;

        /* renamed from: -$$Nest$fgetdialogId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m8983$$Nest$fgetdialogId(AvatarItem r2) {
            return r2.dialogId;
        }

        /* renamed from: -$$Nest$fgetimageReceiver, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ImageReceiver m8984$$Nest$fgetimageReceiver(AvatarItem r0) {
            return r0.imageReceiver;
        }

        private AvatarItem(AvatarsListDrawable r2, View r3) {
            this.this$0 = r2;
            ImageReceiver r0 = new ImageReceiver(r3);
            this.imageReceiver = r0;
            r0.setRoundRadius(AvatarsListDrawable.m8981$$Nest$fgetavatarSize(r2) / 2);
            AvatarDrawable r22 = new AvatarDrawable();
            this.avatarDrawable = r22;
            r22.setTextSize(AndroidUtilities.dp(22.0f));
        }

        public /* synthetic */ AvatarItem(AvatarsListDrawable r1, View r2, AvatarsListDrawableIA r3) {
            this(r1, r2);
        }

        public void attach() {
            if (this.attached == true) goto L6;
            this.attached = true;
            this.imageReceiver.onAttachedToWindow();
            return;
        }

        public void detach() {
            if (this.attached == false) goto L6;
            this.attached = false;
            this.imageReceiver.onDetachedFromWindow();
            return;
        }

        public boolean equals(Object r5) {
            if ((r5 instanceof AvatarItem) == true) goto L6;
            return false;
        L6:
            if (this.dialogId != ((AvatarItem) r5).dialogId) goto L9;
            return true;
        L9:
            return false;
        }

        @Override // me.vkryl.android.animator.ListAnimator.Measurable
        public int getHeight() {
            return AvatarsListDrawable.m8981$$Nest$fgetavatarSize(this.this$0);
        }

        @Override // me.vkryl.android.animator.ListAnimator.Measurable
        public int getSpacingStart(boolean r1) {
            if (r1 == false) goto L6;
            return 0;
        L6:
            return -AvatarsListDrawable.m8980$$Nest$fgetavatarOffset(this.this$0);
        }

        @Override // me.vkryl.android.animator.ListAnimator.Measurable
        public int getWidth() {
            return AvatarsListDrawable.m8981$$Nest$fgetavatarSize(this.this$0);
        }

        @Override // me.vkryl.core.lambda.Destroyable
        public void performDestroy() {
            detach();
            this.dialogId = 0;
        }

        public void set(int r3, long r4) {
            if (this.dialogId != r4) goto L5;
            return;
        L5:
            this.dialogId = r4;
            TLObject r0 = MessagesController.getInstance(r3).getUserOrChat(r4);
            AvatarDrawable r1 = this.avatarDrawable;
            if (r0 == null) goto L9;
            r1.setInfo(r3, r0);
            this.imageReceiver.setForUserOrChat(r0, this.avatarDrawable);
            return;
        L9:
            r1.setInfo(r4, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET);
            this.imageReceiver.clearImage();
        }
    }

    /* renamed from: -$$Nest$fgetavatarOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8980$$Nest$fgetavatarOffset(AvatarsListDrawable r0) {
        return r0.avatarOffset;
    }

    /* renamed from: -$$Nest$fgetavatarSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8981$$Nest$fgetavatarSize(AvatarsListDrawable r0) {
        return r0.avatarSize;
    }

    /* renamed from: -$$Nest$fgetparent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m8982$$Nest$fgetparent(AvatarsListDrawable r0) {
        return r0.parent;
    }

    public AvatarsListDrawable(int r6, View r7, int r8, int r9, float r10) {
        this.animator = new ListAnimator(new AnonymousClass1(this), CubicBezierInterpolator.EASE_OUT_QUINT, 380);
        this.avatarItemsPool = new ArrayList();
        this.alpha = 255;
        this.currentAccount = r6;
        this.parent = r7;
        this.avatarSize = r8;
        this.avatarOffset = r9;
        this.avatarStroke = r10;
    }

    private AvatarItem find(long r6) {
        ArrayList<AvatarItem> r5 = this.avatarItemsPool;
        int r0 = r5.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        AvatarItem r2 = r5.get(r1);
        r1 = r1 + 1;
        AvatarItem r22 = r2;
        if (AvatarItem.m8983$$Nest$fgetdialogId(r22) != r6) goto L3;
        return r22;
    L7:
        return null;
    }

    public void attach() {
        if (this.attached == true) goto L9;
        this.attached = true;
        ArrayList<AvatarItem> r7 = this.avatarItemsPool;
        int r0 = r7.size();
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L15;
        AvatarItem r2 = r7.get(r1);
        r1 = r1 + 1;
        AvatarItem r22 = r2;
        if (AvatarItem.m8983$$Nest$fgetdialogId(r22) == 0) goto L5;
        r22.attach();
        goto L5
    L15:
        return;
    }

    public void detach() {
        if (this.attached == false) goto L7;
        int r0 = 0;
        this.attached = false;
        ArrayList<AvatarItem> r3 = this.avatarItemsPool;
        int r1 = r3.size();
    L5:
        if (r0 >= r1) goto L9;
        AvatarItem r2 = r3.get(r0);
        r0 = r0 + 1;
        r2.detach();
        goto L5
    L9:
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r2) {
        draw(r2, null);
    }

    public void draw(Canvas r10, Paint r11) {
        Rect r112 = getBounds();
        if (r112.isEmpty() == false) goto L5;
        return;
    L5:
        if (this.alpha == 0) goto L14;
        float r2 = r112.left;
        float r3 = r112.top;
        r10.saveLayer(r2, r3, r2 + this.animator.getMetadata().getTotalWidth(), r3 + this.avatarSize, null);
        int r102 = this.animator.size() - 1;
    L8:
        if (r102 < 0) goto L10;
        ListAnimator.Entry<AvatarItem> r113 = this.animator.getEntry(r102);
        RectF r0 = r113.getRectF();
        float r4 = r113.getSpacingStart();
        float r5 = r113.getVisibility();
        float r6 = r0.left + r4;
        float r02 = r0.width() - r4;
        float r42 = r2 + r6;
        float r62 = r02 / 2.0f;
        float r7 = r42 + r62;
        float r8 = r3 + r62;
        r10.save();
        r10.scale(r5, r5, r7, r8);
        r10.drawCircle(r7, r8, r62 + this.avatarStroke, Theme.PAINT_CLEAR);
        AvatarItem.m8984$$Nest$fgetimageReceiver(r113.item).setImageCoords(r42, r3, r02, r02);
        AvatarItem.m8984$$Nest$fgetimageReceiver(r113.item).setAlpha(r113.getVisibility() * (this.alpha / 255.0f));
        AvatarItem.m8984$$Nest$fgetimageReceiver(r113.item).draw(r10);
        r10.restore();
        r102 = r102 - 1;
        goto L8
    L10:
        r10.restore();
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.alpha;
    }

    public float getAnimatedWidth() {
        return this.animator.getMetadata().getTotalWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public float getTotalVisibility() {
        return this.animator.getMetadata().getTotalVisibility();
    }

    public void set(List<TLRPC.Peer> r7, boolean r8) {
        if (r7 != null) goto L4;
    L21:
        this.animator.clear(r8);
        return;
    L4:
        if (r7.isEmpty() == true) goto L21;
        if (r8 == true) goto L8;
        this.animator.clear(false);
    L8:
        ArrayList r0 = new ArrayList(r7.size());
        Iterator<TLRPC.Peer> r72 = r7.iterator();
    L10:
        if (r72.hasNext() == false) goto L19;
        long r1 = DialogObject.getPeerDialogId(r72.next());
        AvatarItem r3 = find(r1);
        if (r3 != null) goto L14;
        r3 = find(0);
    L14:
        if (r3 != null) goto L16;
        r3 = new AvatarItem(this, this.parent, null);
        this.avatarItemsPool.add(r3);
    L16:
        r3.set(this.currentAccount, r1);
        r0.add(r3);
        if (this.attached == false) goto L10;
        r3.attach();
        goto L10
    L19:
        this.animator.reset(r0, r8);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.alpha = r1;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }
}
