package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.PhotoEditorSeekBar;

/* loaded from: classes6.dex */
public class PhotoEditToolCell extends FrameLayout {
    private Runnable hideValueRunnable;
    private TextView nameTextView;
    private final Theme.ResourcesProvider resourcesProvider;
    private PhotoEditorSeekBar seekBar;
    private AnimatorSet valueAnimation;
    private TextView valueTextView;

    /* renamed from: org.telegram.ui.Cells.PhotoEditToolCell$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ PhotoEditToolCell this$0;

        /* renamed from: org.telegram.ui.Cells.PhotoEditToolCell$1$1, reason: invalid class name and collision with other inner class name */
        public class C00391 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass1 this$1;

            public C00391(AnonymousClass1 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                if (r2.equals(PhotoEditToolCell.m6993$$Nest$fgetvalueAnimation(this.this$1.this$0)) == false) goto L6;
                PhotoEditToolCell.m6995$$Nest$fputvalueAnimation(this.this$1.this$0, null);
                return;
            }
        }

        public AnonymousClass1(PhotoEditToolCell r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            PhotoEditToolCell.m6994$$Nest$fgetvalueTextView(this.this$0).setTag(null);
            PhotoEditToolCell.m6995$$Nest$fputvalueAnimation(this.this$0, new AnimatorSet());
            AnimatorSet r0 = PhotoEditToolCell.m6993$$Nest$fgetvalueAnimation(this.this$0);
            TextView r1 = PhotoEditToolCell.m6994$$Nest$fgetvalueTextView(this.this$0);
            Property r2 = View.ALPHA;
            r0.playTogether(new Animator[]{ObjectAnimator.ofFloat(r1, r2, new float[]{0.0f}), ObjectAnimator.ofFloat(PhotoEditToolCell.m6992$$Nest$fgetnameTextView(this.this$0), r2, new float[]{1.0f})});
            PhotoEditToolCell.m6993$$Nest$fgetvalueAnimation(this.this$0).setDuration(250);
            PhotoEditToolCell.m6993$$Nest$fgetvalueAnimation(this.this$0).setInterpolator(new DecelerateInterpolator());
            PhotoEditToolCell.m6993$$Nest$fgetvalueAnimation(this.this$0).addListener(new C00391(this));
            PhotoEditToolCell.m6993$$Nest$fgetvalueAnimation(this.this$0).start();
        }
    }

    /* renamed from: org.telegram.ui.Cells.PhotoEditToolCell$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ PhotoEditToolCell this$0;

        public AnonymousClass2(PhotoEditToolCell r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            AndroidUtilities.runOnUIThread(PhotoEditToolCell.m6991$$Nest$fgethideValueRunnable(this.this$0), 1000);
        }
    }

    /* renamed from: $r8$lambda$-X4FZLGN707U4CwxXIs7ftjBF0U, reason: not valid java name */
    public static /* synthetic */ void m6990$r8$lambda$X4FZLGN707U4CwxXIs7ftjBF0U(PhotoEditToolCell r0, PhotoEditorSeekBar.PhotoEditorSeekBarDelegate r1, int r2, int r3) {
        r0.lambda$setSeekBarDelegate$0(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgethideValueRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m6991$$Nest$fgethideValueRunnable(PhotoEditToolCell r0) {
        return r0.hideValueRunnable;
    }

    /* renamed from: -$$Nest$fgetnameTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m6992$$Nest$fgetnameTextView(PhotoEditToolCell r0) {
        return r0.nameTextView;
    }

    /* renamed from: -$$Nest$fgetvalueAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m6993$$Nest$fgetvalueAnimation(PhotoEditToolCell r0) {
        return r0.valueAnimation;
    }

    /* renamed from: -$$Nest$fgetvalueTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m6994$$Nest$fgetvalueTextView(PhotoEditToolCell r0) {
        return r0.valueTextView;
    }

    /* renamed from: -$$Nest$fputvalueAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6995$$Nest$fputvalueAnimation(PhotoEditToolCell r0, AnimatorSet r1) {
        r0.valueAnimation = r1;
    }

    public PhotoEditToolCell(Context r11, Theme.ResourcesProvider r12) {
        super(r11);
        this.hideValueRunnable = new AnonymousClass1(this);
        this.resourcesProvider = r12;
        TextView r122 = new TextView(r11);
        this.nameTextView = r122;
        r122.setGravity(5);
        this.nameTextView.setTextColor(-1);
        this.nameTextView.setTextSize(1, 12.0f);
        this.nameTextView.setMaxLines(1);
        this.nameTextView.setSingleLine(true);
        this.nameTextView.setEllipsize(TextUtils.TruncateAt.END);
        addView(this.nameTextView, LayoutHelper.createFrame(80, -2.0f, 19, 0.0f, 0.0f, 0.0f, 0.0f));
        TextView r123 = new TextView(r11);
        this.valueTextView = r123;
        r123.setTextColor(getThemedColor(Theme.key_chat_editMediaButton));
        this.valueTextView.setTextSize(1, 12.0f);
        this.valueTextView.setGravity(5);
        this.valueTextView.setSingleLine(true);
        addView(this.valueTextView, LayoutHelper.createFrame(80, -2.0f, 19, 0.0f, 0.0f, 0.0f, 0.0f));
        PhotoEditorSeekBar r124 = new PhotoEditorSeekBar(r11);
        this.seekBar = r124;
        addView(r124, LayoutHelper.createFrame(-1, 40.0f, 19, 96.0f, 0.0f, 24.0f, 0.0f));
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private /* synthetic */ void lambda$setSeekBarDelegate$0(PhotoEditorSeekBar.PhotoEditorSeekBarDelegate r6, int r7, int r8) {
        r6.onProgressChanged(r7, r8);
        TextView r62 = this.valueTextView;
        if (r8 <= 0) goto L5;
        r62.setText("+" + r8);
    L7:
        if (this.valueTextView.getTag() != null) goto L13;
        AnimatorSet r63 = this.valueAnimation;
        if (r63 == null) goto L11;
        r63.cancel();
    L11:
        this.valueTextView.setTag(1);
        AnimatorSet r64 = new AnimatorSet();
        this.valueAnimation = r64;
        Property r1 = View.ALPHA;
        r64.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.valueTextView, r1, new float[]{1.0f}), ObjectAnimator.ofFloat(this.nameTextView, r1, new float[]{0.0f})});
        this.valueAnimation.setDuration(250);
        this.valueAnimation.setInterpolator(new DecelerateInterpolator());
        this.valueAnimation.addListener(new AnonymousClass2(this));
        this.valueAnimation.start();
        return;
    L13:
        AndroidUtilities.cancelRunOnUIThread(this.hideValueRunnable);
        AndroidUtilities.runOnUIThread(this.hideValueRunnable, 1000);
        return;
    L5:
        r62.setText(_UrlKt.FRAGMENT_ENCODE_SET + r8);
        goto L7
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(40.0f), TLObject.FLAG_30));
    }

    public void setIconAndTextAndValue(String r6, float r7, int r8, int r9) {
        AnimatorSet r0 = this.valueAnimation;
        if (r0 == null) goto L5;
        r0.cancel();
        this.valueAnimation = null;
    L5:
        AndroidUtilities.cancelRunOnUIThread(this.hideValueRunnable);
        this.valueTextView.setTag(null);
        this.nameTextView.setText(r6.substring(0, 1).toUpperCase() + r6.substring(1).toLowerCase());
        TextView r1 = this.valueTextView;
        if (r7 <= 0.0f) goto L8;
        r1.setText("+" + ((int) r7));
    L9:
        this.valueTextView.setAlpha(0.0f);
        this.nameTextView.setAlpha(1.0f);
        this.seekBar.setMinMax(r8, r9);
        this.seekBar.setProgress((int) r7, false);
        return;
    L8:
        r1.setText(_UrlKt.FRAGMENT_ENCODE_SET + ((int) r7));
        goto L9
    }

    public void setSeekBarDelegate(PhotoEditorSeekBar.PhotoEditorSeekBarDelegate r3) {
        this.seekBar.setDelegate(new PhotoEditToolCell$$ExternalSyntheticLambda0(this, r3));
    }

    @Override // android.view.View
    public void setTag(Object r1) {
        super.setTag(r1);
        this.seekBar.setTag(r1);
    }
}
