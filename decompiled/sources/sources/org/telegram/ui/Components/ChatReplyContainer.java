package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.spoilers.SpoilerEffect;

/* loaded from: classes7.dex */
public class ChatReplyContainer extends FrameLayout {
    public Layout[] layouts;
    private final Theme.ResourcesProvider resourcesProvider;

    public class Layout extends FrameLayout implements Theme.Colorable {
        public boolean active;
        public boolean hasSpoiler;
        public ImageView icon;
        public BackupImageView image;
        public SimpleTextView name;
        public SimpleTextView obj;
        public SimpleTextView objHint;
        private final Theme.ResourcesProvider resourcesProvider;
        final /* synthetic */ ChatReplyContainer this$0;

        /* renamed from: org.telegram.ui.Components.ChatReplyContainer$Layout$1, reason: invalid class name */
        public class AnonymousClass1 extends BackupImageView {
            Path path;
            final /* synthetic */ Layout this$1;
            final /* synthetic */ SpoilerEffect val$replySpoilerEffect;
            final /* synthetic */ ChatReplyContainer val$this$0;

            public AnonymousClass1(Layout r1, Context r2, ChatReplyContainer r3, SpoilerEffect r4) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                this.val$replySpoilerEffect = r4;
                super(r2);
                this.path = new Path();
            }

            @Override // android.view.View
            public void draw(Canvas r6) {
                super.draw(r6);
                if (this.this$1.hasSpoiler == false) goto L6;
                this.path.rewind();
                RectF r0 = AndroidUtilities.rectTmp;
                r0.set(this.imageReceiver.getImageX(), this.imageReceiver.getImageY(), this.imageReceiver.getImageX2(), this.imageReceiver.getImageY2());
                this.path.addRoundRect(r0, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), Path.Direction.CW);
                r6.save();
                r6.clipPath(this.path);
                this.val$replySpoilerEffect.setColor(ColorUtils.setAlphaComponent(-1, (int) (Color.alpha(-1) * 0.325f)));
                this.val$replySpoilerEffect.setBounds((int) this.imageReceiver.getImageX(), (int) this.imageReceiver.getImageY(), (int) this.imageReceiver.getImageX2(), (int) this.imageReceiver.getImageY2());
                this.val$replySpoilerEffect.draw(r6);
                invalidate();
                r6.restore();
                return;
            }
        }

        public Layout(ChatReplyContainer r9, Context r10, Theme.ResourcesProvider r11) {
            this.this$0 = r9;
            super(r10);
            this.resourcesProvider = r11;
            ImageView r112 = new ImageView(r10);
            this.icon = r112;
            r112.setScaleType(ImageView.ScaleType.CENTER);
            addView(this.icon, LayoutHelper.createFrame(52, 46, 51));
            SimpleTextView r113 = new SimpleTextView(r10);
            this.name = r113;
            r113.setTextSize(14);
            this.name.setTypeface(AndroidUtilities.bold());
            addView(this.name, LayoutHelper.createFrame(-1, 18.0f, 51, 52.0f, 6.0f, 0.0f, 0.0f));
            SimpleTextView r114 = new SimpleTextView(r10);
            this.obj = r114;
            r114.setTextSize(14);
            NotificationCenter.listenEmojiLoading(this.obj);
            addView(this.obj, LayoutHelper.createFrame(-1, 18.0f, 51, 52.0f, 24.0f, 0.0f, 0.0f));
            SimpleTextView r115 = new SimpleTextView(r10);
            this.objHint = r115;
            r115.setTextSize(14);
            this.objHint.setText(LocaleController.getString(R.string.TapForForwardingOptions));
            this.objHint.setAlpha(0.0f);
            addView(this.objHint, LayoutHelper.createFrame(-1, 18.0f, 51, 52.0f, 24.0f, 0.0f, 0.0f));
            AnonymousClass1 r0 = new AnonymousClass1(this, r10, r9, new SpoilerEffect());
            this.image = r0;
            r0.setRoundRadius(AndroidUtilities.dp(6.0f));
            addView(this.image, LayoutHelper.createFrame(34, 34.0f, 51, 52.0f, 6.0f, 0.0f, 0.0f));
            updateColors();
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r2) {
            if (this.active == true) goto L7;
            return false;
        L7:
            return super.dispatchTouchEvent(r2);
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public /* bridge */ /* synthetic */ int[] getColorKeys() {
            return super.getColorKeys();
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public void updateColors() {
            this.icon.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_replyPanelIcons, this.resourcesProvider), PorterDuff.Mode.MULTIPLY));
            this.name.setTextColor(Theme.getColor(Theme.key_chat_replyPanelName, this.resourcesProvider));
            SimpleTextView r0 = this.obj;
            int r1 = Theme.key_glass_defaultText;
            r0.setTextColor(Theme.getColor(r1, this.resourcesProvider));
            this.obj.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, this.resourcesProvider));
            this.objHint.setTextColor(Theme.getColor(r1, this.resourcesProvider));
        }
    }

    public static /* synthetic */ void $r8$lambda$o0K3dd9a9COZXAe3qQg_1CuAP_Q(Layout r1) {
        r1.setVisibility(8);
    }

    public ChatReplyContainer(Context r6, Theme.ResourcesProvider r7) {
        super(r6);
        this.layouts = new Layout[2];
        this.resourcesProvider = r7;
        int r1 = 0;
    L3:
        Layout[] r2 = this.layouts;
        if (r1 >= r2.length) goto L6;
        r2[r1] = new Layout(this, r6, r7);
        addView(this.layouts[r1], LayoutHelper.createFrame(-1, -1, 119));
        r1 = r1 + 1;
        goto L3
    L6:
        r2[0].setVisibility(0);
        this.layouts[1].setVisibility(8);
    }

    public Layout current() {
        return this.layouts[0];
    }

    public void switchLayouts() {
        switchLayouts(true);
    }

    public void switchLayouts(boolean r9) {
        Layout[] r0 = this.layouts;
        Layout r2 = r0[0];
        Layout r4 = r0[1];
        r0[0] = r4;
        r0[1] = r2;
        if (r9 == false) goto L6;
        r4.active = true;
        r4.setVisibility(0);
        this.layouts[0].setScaleX(0.8f);
        this.layouts[0].setScaleY(0.8f);
        this.layouts[0].setAlpha(0.0f);
        this.layouts[0].setTranslationY(AndroidUtilities.dp(20.0f));
        ViewPropertyAnimator r92 = this.layouts[0].animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).translationY(0.0f);
        CubicBezierInterpolator r5 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r92.setInterpolator(r5).setDuration(320).start();
        Layout r93 = this.layouts[1];
        r93.active = false;
        r93.setVisibility(0);
        this.layouts[1].animate().scaleX(0.8f).scaleY(0.8f).alpha(0.0f).translationY(-AndroidUtilities.dp(20.0f)).setInterpolator(r5).setDuration(320).withEndAction(new ChatReplyContainer$$ExternalSyntheticLambda0(r93)).start();
        return;
    L6:
        r2.setVisibility(8);
        Layout[] r94 = this.layouts;
        r94[1].active = false;
        r94[0].setVisibility(0);
        this.layouts[0].setScaleX(1.0f);
        this.layouts[0].setScaleY(1.0f);
        this.layouts[0].setAlpha(1.0f);
        this.layouts[0].setTranslationY(0.0f);
        this.layouts[0].active = true;
    }
}
