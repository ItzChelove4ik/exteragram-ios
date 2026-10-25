package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ProgressButton;
import org.telegram.ui.Components.ViewHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes6.dex */
public class ArchivedStickerSetCell extends FrameLayout implements Checkable {
    private final ProgressButton addButton;
    private AnimatorSet animatorSet;
    private final boolean checkable;
    private boolean checked;
    private Button currentButton;
    private final Button deleteButton;
    private final BackupImageView imageView;
    private boolean needDivider;
    private OnCheckedChangeListener onCheckedChangeListener;
    private TLRPC.StickerSetCovered stickersSet;
    private final TextView textView;
    private final TextView valueTextView;

    /* renamed from: org.telegram.ui.Cells.ArchivedStickerSetCell$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ ArchivedStickerSetCell this$0;

        public AnonymousClass1(ArchivedStickerSetCell r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            Button r32 = ArchivedStickerSetCell.m6616$$Nest$fgetcurrentButton(this.this$0);
            ProgressButton r0 = ArchivedStickerSetCell.m6615$$Nest$fgetaddButton(this.this$0);
            ArchivedStickerSetCell r2 = this.this$0;
            if (r32 != r0) goto L6;
            ArchivedStickerSetCell.m6617$$Nest$fgetdeleteButton(r2).setVisibility(4);
            return;
        L6:
            ArchivedStickerSetCell.m6615$$Nest$fgetaddButton(r2).setVisibility(4);
        }
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(ArchivedStickerSetCell r1, boolean r2);
    }

    /* renamed from: $r8$lambda$gBuO6zSdCuSfP2pOTizbGP-MfBo, reason: not valid java name */
    public static /* synthetic */ void m6614$r8$lambda$gBuO6zSdCuSfP2pOTizbGPMfBo(ArchivedStickerSetCell r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: -$$Nest$fgetaddButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ProgressButton m6615$$Nest$fgetaddButton(ArchivedStickerSetCell r0) {
        return r0.addButton;
    }

    /* renamed from: -$$Nest$fgetcurrentButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Button m6616$$Nest$fgetcurrentButton(ArchivedStickerSetCell r0) {
        return r0.currentButton;
    }

    /* renamed from: -$$Nest$fgetdeleteButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Button m6617$$Nest$fgetdeleteButton(ArchivedStickerSetCell r0) {
        return r0.deleteButton;
    }

    public ArchivedStickerSetCell(Context r14, boolean r15) {
        super(r14);
        this.checkable = r15;
        if (r15 == false) goto L5;
        ProgressButton r152 = new ProgressButton(r14);
        this.addButton = r152;
        this.currentButton = r152;
        r152.setText(LocaleController.getString(R.string.Add));
        r152.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
        r152.setProgressColor(Theme.getColor(Theme.key_featuredStickers_buttonProgress));
        r152.setBackgroundRoundRect(Theme.getColor(Theme.key_featuredStickers_addButton), Theme.getColor(Theme.key_featuredStickers_addButtonPressed));
        addView(r152, LayoutHelper.createFrameRelatively(-2.0f, 28.0f, 8388661, 0.0f, 18.0f, 14.0f, 0.0f));
        int r2 = AndroidUtilities.dp(60.0f);
        ProgressButton r3 = new ProgressButton(r14);
        this.deleteButton = r3;
        r3.setAllCaps(false);
        r3.setMinWidth(r2);
        r3.setMinimumWidth(r2);
        r3.setTextSize(1, 14.0f);
        int r22 = Theme.key_featuredStickers_removeButtonText;
        r3.setTextColor(Theme.getColor(r22));
        r3.setText(LocaleController.getString(R.string.StickersRemove));
        r3.setBackground(Theme.getRoundRectSelectorDrawable(Theme.getColor(r22)));
        r3.setTypeface(AndroidUtilities.bold());
        ViewHelper.setPadding(r3, 8.0f, 0.0f, 8.0f, 0.0f);
        r3.setOutlineProvider(null);
        addView(r3, LayoutHelper.createFrameRelatively(-2.0f, 28.0f, 8388661, 0.0f, 18.0f, 14.0f, 0.0f));
        ArchivedStickerSetCell$$ExternalSyntheticLambda0 r0 = new ArchivedStickerSetCell$$ExternalSyntheticLambda0(this);
        r152.setOnClickListener(r0);
        r3.setOnClickListener(r0);
        syncButtons(false);
    L6:
        TextView r153 = new TextView(r14);
        this.textView = r153;
        r153.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        r153.setTextSize(1, 16.0f);
        r153.setLines(1);
        r153.setMaxLines(1);
        r153.setSingleLine(true);
        r153.setEllipsize(TextUtils.TruncateAt.END);
        r153.setGravity(LayoutHelper.getAbsoluteGravityStart());
        addView(r153, LayoutHelper.createFrameRelatively(-2.0f, -2.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 71.0f, 10.0f, 21.0f, 0.0f));
        TextView r154 = new TextView(r14);
        this.valueTextView = r154;
        r154.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2));
        r154.setTextSize(1, 13.0f);
        r154.setLines(1);
        r154.setMaxLines(1);
        r154.setSingleLine(true);
        r154.setGravity(LayoutHelper.getAbsoluteGravityStart());
        addView(r154, LayoutHelper.createFrameRelatively(-2.0f, -2.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 71.0f, 35.0f, 21.0f, 0.0f));
        BackupImageView r155 = new BackupImageView(r14);
        this.imageView = r155;
        r155.setAspectFit(true);
        r155.setLayerNum(1);
        addView(r155, LayoutHelper.createFrameRelatively(48.0f, 48.0f, 8388659, 12.0f, 8.0f, 0.0f, 0.0f));
        return;
    L5:
        this.addButton = null;
        this.deleteButton = null;
        goto L6
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        toggle();
    }

    private void syncButtons(boolean r13) {
        if (this.checkable == false) goto L31;
        AnimatorSet r0 = this.animatorSet;
        if (r0 == null) goto L7;
        r0.cancel();
    L7:
        boolean r02 = this.checked;
        float r1 = 0.0f;
        if (r02 == false) goto L10;
        float r3 = 1.0f;
    L11:
        if (r02 == true) goto L14;
        r1 = 1.0f;
    L14:
        int r2 = 4;
        if (r13 == false) goto L21;
        if (r02 == false) goto L18;
        Button r132 = this.deleteButton;
    L19:
        this.currentButton = r132;
        this.addButton.setVisibility(0);
        this.deleteButton.setVisibility(0);
        AnimatorSet r133 = new AnimatorSet();
        this.animatorSet = r133;
        r133.setDuration(250);
        AnimatorSet r134 = this.animatorSet;
        Property r7 = View.ALPHA;
        ObjectAnimator r03 = ObjectAnimator.ofFloat(this.deleteButton, r7, new float[]{r3});
        Property r9 = View.SCALE_X;
        ObjectAnimator r6 = ObjectAnimator.ofFloat(this.deleteButton, r9, new float[]{r3});
        Button r8 = this.deleteButton;
        float[] r10 = {r3};
        Property r32 = View.SCALE_Y;
        r134.playTogether(new Animator[]{r03, r6, ObjectAnimator.ofFloat(r8, r32, r10), ObjectAnimator.ofFloat(this.addButton, r7, new float[]{r1}), ObjectAnimator.ofFloat(this.addButton, r9, new float[]{r1}), ObjectAnimator.ofFloat(this.addButton, r32, new float[]{r1})});
        this.animatorSet.addListener(new AnonymousClass1(this));
        this.animatorSet.setInterpolator(new OvershootInterpolator(1.02f));
        this.animatorSet.start();
        return;
    L18:
        r132 = this.addButton;
        goto L19
    L21:
        Button r135 = this.deleteButton;
        if (r02 == false) goto L24;
        int r04 = 0;
    L25:
        r135.setVisibility(r04);
        this.deleteButton.setAlpha(r3);
        this.deleteButton.setScaleX(r3);
        this.deleteButton.setScaleY(r3);
        ProgressButton r136 = this.addButton;
        if (this.checked == true) goto L29;
        r2 = 0;
    L29:
        r136.setVisibility(r2);
        this.addButton.setAlpha(r1);
        this.addButton.setScaleX(r1);
        this.addButton.setScaleY(r1);
        return;
    L24:
        r04 = 4;
        goto L25
    L10:
        r3 = 0.0f;
        goto L11
    }

    public TLRPC.StickerSetCovered getStickersSet() {
        return this.stickersSet;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.checked;
    }

    @Override // android.view.ViewGroup
    public void measureChildWithMargins(View r7, int r8, int r9, int r10, int r11) {
        if (this.checkable == true) goto L5;
    L7:
        super.measureChildWithMargins(r7, r8, r9, r10, r11);
        return;
    L5:
        if (r7 != this.textView) goto L7;
        r9 = r9 + Math.max(this.addButton.getMeasuredWidth(), this.deleteButton.getMeasuredWidth());
        goto L7
    }

    @Override // android.view.View
    public void onDraw(Canvas r8) {
        if (this.needDivider == false) goto L6;
        r8.drawLine(0.0f, getHeight() - 1, getWidth() - getPaddingRight(), getHeight() - 1, Theme.dividerPaint);
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64.0f) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean r2) {
        setChecked(r2, true);
    }

    public void setChecked(boolean r2, boolean r3) {
        setChecked(r2, r3, true);
    }

    public void setChecked(boolean r2, boolean r3, boolean r4) {
        if (this.checkable == true) goto L5;
        return;
    L5:
        if (this.checked == r2) goto L13;
        this.checked = r2;
        syncButtons(r3);
        if (r4 == false) goto L14;
        OnCheckedChangeListener r32 = this.onCheckedChangeListener;
        if (r32 == null) goto L15;
        r32.onCheckedChanged(this, r2);
        return;
    L15:
        return;
    L14:
        return;
    }

    public void setDrawProgress(boolean r1, boolean r2) {
        ProgressButton r0 = this.addButton;
        if (r0 == null) goto L6;
        r0.setDrawProgress(r1, r2);
        return;
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener r1) {
        this.onCheckedChangeListener = r1;
    }

    public void setStickersSet(TLRPC.StickerSetCovered r13, boolean r14) {
        this.needDivider = r14;
        this.stickersSet = r13;
        setWillNotDraw(!r14);
        this.textView.setText(this.stickersSet.set.title);
        TLRPC.StickerSet r1 = r13.set;
        boolean r3 = r1.emojis;
        TextView r4 = this.valueTextView;
        if (r3 == false) goto L5;
        r4.setText(LocaleController.formatPluralString("EmojiCount", r1.count, new Object[0]));
    L6:
        TLRPC.Document r32 = null;
        if ((r13 instanceof TLRPC.TL_stickerSetFullCovered) == false) goto L24;
        ArrayList<TLRPC.Document> r12 = ((TLRPC.TL_stickerSetFullCovered) r13).documents;
        if (r12 != null) goto L11;
        return;
    L11:
        long r7 = r13.set.thumb_document_id;
        int r42 = 0;
    L13:
        if (r42 >= r12.size()) goto L20;
        TLRPC.Document r9 = r12.get(r42);
        if (r9 == null) goto L19;
        if (r9.id != r7) goto L19;
        r32 = r9;
    L19:
        r42 = r42 + 1;
    L20:
        if (r32 == null) goto L22;
    L30:
        if (r32 == null) goto L58;
        TLObject r15 = FileLoader.getClosestPhotoSizeWithSize(r13.set.thumbs, 90);
        if (r15 != null) goto L34;
        r15 = r32;
    L34:
        SvgHelper.SvgDrawable r6 = DocumentObject.getSvgThumb(r13.set.thumbs, Theme.key_windowBackgroundGray, 1.0f);
        boolean r72 = r15 instanceof TLRPC.Document;
        if (r72 == false) goto L37;
        ImageLocation r16 = ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r32.thumbs, 90), r32);
    L38:
        if (r72 == false) goto L51;
        if (MessageObject.isAnimatedStickerDocument(r32, true) == false) goto L42;
    L45:
        BackupImageView r0 = this.imageView;
        if (r6 == null) goto L49;
        r0.setImage(ImageLocation.getForDocument(r32), "50_50", r6, 0, r13);
        return;
    L49:
        r0.setImage(ImageLocation.getForDocument(r32), "50_50", r16, null, 0, r13);
        return;
    L42:
        if (MessageObject.isVideoSticker(r32) == true) goto L45;
    L51:
        if (r16 != null) goto L53;
    L56:
        this.imageView.setImage(r16, "50_50", "webp", r6, r13);
        return;
    L53:
        if (r16.imageType != 1) goto L56;
        this.imageView.setImage(r16, "50_50", "tgs", r6, r13);
        return;
    L37:
        r16 = ImageLocation.getForSticker((TLRPC.PhotoSize) r15, r32, r13.set.thumb_version);
        goto L38
    L58:
        this.imageView.setImage(null, null, "webp", null, r13);
        return;
    L22:
        if (r12.isEmpty() == true) goto L30;
        TLRPC.Document r17 = r12.get(0);
    L26:
        r32 = r17;
        goto L30
    L24:
        r17 = r13.cover;
        if (r17 != null) goto L26;
        if (r13.covers.isEmpty() == true) goto L30;
        r32 = r13.covers.get(0);
        goto L30
    L5:
        r4.setText(LocaleController.formatPluralString("Stickers", r1.count, new Object[0]));
        goto L6
    }

    @Override // android.widget.Checkable
    public void toggle() {
        if (this.checkable == false) goto L6;
        setChecked(!isChecked());
        return;
    }
}
