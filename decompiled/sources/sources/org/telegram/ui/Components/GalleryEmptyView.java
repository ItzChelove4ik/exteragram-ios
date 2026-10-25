package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class GalleryEmptyView extends LinearLayout {
    private final ButtonWithCounterView cameraAccessButton;
    private final long emojiDocumentId;
    private final ButtonWithCounterView galleryAccessButton;
    private final BackupImageView stickerView;
    private final TextView subtitleTextView;
    private final TextView titleTextView;
    private final ButtonWithCounterView useAnEmojiButton;

    public static /* synthetic */ void $r8$lambda$2tdOi9PEc0YFb0uP7DaDRicl6OM(Runnable r0, View r1) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$Srl4TdjxKfKbHCxRJELb6gP2pMo(GalleryEmptyView r0, Utilities.Callback r1, View r2) {
        r0.lambda$doOnEmojiButton$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$mqu4eaBktkL0DqIT2GoTaxSAVII(Runnable r0, View r1) {
        r0.run();
    }

    public GalleryEmptyView(Context r21, int r22, boolean r23) {
        super(r21);
        TLRPC.TL_emojiList r2 = AvatarConstructorPreviewCell.getOrCreateEmojiList(r22, r23);
        setOrientation(1);
        BackupImageView r4 = new BackupImageView(r21);
        this.stickerView = r4;
        r4.setImageDrawable(new RLottieDrawable(R.raw.utyan_gallery, AndroidUtilities.dp(110.0f), AndroidUtilities.dp(110.0f)));
        if (AndroidUtilities.isTablet() == true) goto L5;
        addView(r4, LayoutHelper.createLinear(110, 110, 49));
    L5:
        TextView r42 = new TextView(r21);
        this.titleTextView = r42;
        r42.setTextSize(1, 20.0f);
        r42.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        r42.setGravity(1);
        r42.setText(LocaleController.getString(R.string.GalleryAccessAllowAccess));
        r42.setTypeface(AndroidUtilities.bold());
        addView(r42, LayoutHelper.createLinear(-2, -2, 49, 0, 15, 0, 7));
        TextView r43 = new TextView(r21);
        this.subtitleTextView = r43;
        r43.setTextSize(1, 14.0f);
        r43.setTextColor(Theme.getColor(Theme.key_emptyListPlaceholder));
        r43.setGravity(1);
        if (UserConfig.getInstance(r22).isPremium() == false) goto L8;
        int r5 = R.string.GalleryAccessAllowAccessTextPremium;
    L9:
        r43.setText(LocaleController.getString(r5));
        r43.setMaxWidth(AndroidUtilities.dp(260.0f));
        r43.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        addView(r43, LayoutHelper.createLinear(-2, -2, 49, 0, 0, 0, 14));
        ButtonWithCounterView r44 = new ButtonWithCounterView(r21, null);
        this.galleryAccessButton = r44;
        r44.setRound();
        r44.setText(LocaleController.getString(R.string.GalleryAccessAllowAccessButton), false);
        addView(r44, LayoutHelper.createLinear(-2, 44, 49));
        ButtonWithCounterView r45 = new ButtonWithCounterView(r21, false, null);
        this.cameraAccessButton = r45;
        r45.setRound();
        SpannableStringBuilder r6 = new SpannableStringBuilder("c");
        r6.setSpan(new ColoredImageSpan(R.drawable.outline_attach_camera_24), 0, 1, 33);
        r6.append("  ").append(LocaleController.getString(R.string.GalleryAccessAllowAccessOpenCamera));
        r45.setText(r6, false);
        addView(r45, LayoutHelper.createLinear(-2, 44, 49, 0, 8, 0, 0));
        ButtonWithCounterView r46 = new ButtonWithCounterView(r21, false, null);
        this.useAnEmojiButton = r46;
        r46.setRound();
        r46.setVisibility(8);
        SpannableStringBuilder r1 = new SpannableStringBuilder("c");
        ArrayList<Long> r62 = r2.document_id;
        if (r62 != null) goto L12;
    L14:
        this.emojiDocumentId = 0;
    L15:
        r1.append(LocaleController.getString(R.string.UseEmoji));
        r46.setText(r1, false);
        addView(r46, LayoutHelper.createLinear(-2, 44, 49, 0, 1, 0, 0));
        return;
    L12:
        if (r62.isEmpty() == true) goto L14;
        long r63 = r2.document_id.get(0).longValue();
        this.emojiDocumentId = r63;
        r1.setSpan(new AnimatedEmojiSpan(r63, null), 0, 1, 33);
        r1.append("  ");
        goto L15
    L8:
        r5 = R.string.GalleryAccessAllowAccessTextNonPremium;
        goto L9
    }

    private /* synthetic */ void lambda$doOnEmojiButton$2(Utilities.Callback r3, View r4) {
        r3.run(Long.valueOf(this.emojiDocumentId));
    }

    public void doOnCameraAccess(Runnable r2) {
        this.cameraAccessButton.setOnClickListener(new GalleryEmptyView$$ExternalSyntheticLambda1(r2));
    }

    public void doOnEmojiButton(Utilities.Callback<Long> r3) {
        this.useAnEmojiButton.setOnClickListener(new GalleryEmptyView$$ExternalSyntheticLambda0(this, r3));
    }

    public void doOnGalleryAccessClick(Runnable r2) {
        this.galleryAccessButton.setOnClickListener(new GalleryEmptyView$$ExternalSyntheticLambda2(r2));
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r5, int r6) {
        int r0 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r5), Integer.MIN_VALUE);
        int r1 = View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(44.0f), TLObject.FLAG_30);
        this.galleryAccessButton.setUseWrapContent(true);
        this.cameraAccessButton.setUseWrapContent(true);
        this.useAnEmojiButton.setUseWrapContent(true);
        this.galleryAccessButton.measure(r0, r1);
        this.cameraAccessButton.measure(r0, r1);
        this.useAnEmojiButton.measure(r0, r1);
        this.galleryAccessButton.setUseWrapContent(false);
        this.cameraAccessButton.setUseWrapContent(false);
        this.useAnEmojiButton.setUseWrapContent(false);
        int r02 = Math.max(Math.max(this.galleryAccessButton.getMeasuredWidth(), this.cameraAccessButton.getMeasuredWidth()), this.useAnEmojiButton.getMeasuredWidth());
        ViewGroup.LayoutParams r12 = this.galleryAccessButton.getLayoutParams();
        r12.width = AndroidUtilities.dp(80.0f) + r02;
        ViewGroup.LayoutParams r13 = this.cameraAccessButton.getLayoutParams();
        r13.width = AndroidUtilities.dp(80.0f) + r02;
        ViewGroup.LayoutParams r14 = this.useAnEmojiButton.getLayoutParams();
        r14.width = r02 + AndroidUtilities.dp(80.0f);
        super.onMeasure(r5, r6);
    }

    public void setUseAnEmojiVisible(boolean r1) {
        ButtonWithCounterView r0 = this.useAnEmojiButton;
        if (r1 == false) goto L5;
        int r12 = 0;
    L6:
        r0.setVisibility(r12);
        return;
    L5:
        r12 = 8;
        goto L6
    }
}
