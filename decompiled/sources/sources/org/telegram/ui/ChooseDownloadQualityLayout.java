package org.telegram.ui;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.PopupSwipeBackLayout;
import org.telegram.ui.Components.VideoPlayer;

/* loaded from: classes6.dex */
public class ChooseDownloadQualityLayout {
    public final LinearLayout buttonsLayout;
    private final Callback callback;
    public final ActionBarPopupWindow.ActionBarPopupWindowLayout layout;

    /* renamed from: org.telegram.ui.ChooseDownloadQualityLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ ChooseDownloadQualityLayout this$0;

        public AnonymousClass1(ChooseDownloadQualityLayout r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
        }
    }

    public interface Callback {
        void onQualitySelected(MessageObject r1, VideoPlayer.Quality r2);
    }

    /* renamed from: $r8$lambda$2S_jPWr-yZ00Re1D0b8WpTq5gSM, reason: not valid java name */
    public static /* synthetic */ void m8650$r8$lambda$2S_jPWryZ00Re1D0b8WpTq5gSM(ChooseDownloadQualityLayout r0, MessageObject r1, VideoPlayer.Quality r2, View r3) {
        r0.lambda$update$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$GcvUeUkxaa3jJeTLnkqEjDg_1jI(PopupSwipeBackLayout r0, View r1) {
        r0.closeForeground();
    }

    public ChooseDownloadQualityLayout(Context r6, PopupSwipeBackLayout r7, Callback r8) {
        this.callback = r8;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r82 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(r6, 0, null);
        this.layout = r82;
        r82.setFitItems(true);
        ActionBarMenuSubItem r0 = ActionBarMenuItem.addItem(r82, R.drawable.msg_arrow_back, LocaleController.getString(R.string.Back), false, null);
        r0.setOnClickListener(new ChooseDownloadQualityLayout$$ExternalSyntheticLambda0(r7));
        r0.setColors(-328966, -328966);
        r0.setSelectorColor(268435455);
        View r72 = new AnonymousClass1(this, r6);
        r72.setMinimumWidth(AndroidUtilities.dp(196.0f));
        r72.setBackgroundColor(-15198184);
        r82.addView(r72);
        LinearLayout.LayoutParams r02 = (LinearLayout.LayoutParams) r72.getLayoutParams();
        if (LocaleController.isRTL == false) goto L5;
        r02.gravity = 5;
    L5:
        r02.width = -1;
        r02.height = AndroidUtilities.dp(8.0f);
        r72.setLayoutParams(r02);
        LinearLayout r73 = new LinearLayout(r6);
        this.buttonsLayout = r73;
        r73.setOrientation(1);
        r82.addView(r73);
    }

    private /* synthetic */ void lambda$update$1(MessageObject r1, VideoPlayer.Quality r2, View r3) {
        this.callback.onQualitySelected(r1, r2);
    }

    public boolean update(MessageObject r13) {
        if (r13 == null) goto L25;
        TLRPC.Message r1 = r13.messageOwner;
        if (r1 == null) goto L25;
        if (r1.media == null) goto L25;
        if (r13.hasVideoQualities() == true) goto L12;
        return false;
    L12:
        int r12 = r13.currentAccount;
        TLRPC.MessageMedia r2 = r13.messageOwner.media;
        ArrayList<VideoPlayer.Quality> r14 = VideoPlayer.getQualities(r12, r2.document, r2.alt_documents, 0, false);
        this.buttonsLayout.removeAllViews();
        int r22 = 0;
    L14:
        if (r22 >= r14.size()) goto L24;
        VideoPlayer.Quality r3 = r14.get(r22);
        VideoPlayer.VideoUri r5 = r3.getDownloadUri();
        StringBuilder r6 = new StringBuilder();
        r6.append(LocaleController.formatString(R.string.QualitySaveIn, new Object[]{Integer.valueOf(r3.p())}));
        if (r3.original == false) goto L18;
        String r7 = " (" + LocaleController.getString(R.string.QualitySource) + ")";
    L19:
        r6.append(r7);
        String r62 = r6.toString();
        SpannableStringBuilder r72 = new SpannableStringBuilder();
        if (r5.isCached() == false) goto L22;
        r72.append(AndroidUtilities.formatFileSize(r5.document.size));
        r72.append(LocaleController.getString(R.string.QualityCached));
    L23:
        ActionBarMenuSubItem r4 = ActionBarMenuItem.addItem(this.buttonsLayout, 0, r62, false, null);
        r4.setSubtext(r72);
        r4.setColors(-328966, -328966);
        r4.subtextView.setPadding(0, 0, 0, 0);
        r4.setOnClickListener(new ChooseDownloadQualityLayout$$ExternalSyntheticLambda1(this, r13, r3));
        r4.setSelectorColor(268435455);
        r22 = r22 + 1;
        goto L14
    L22:
        SpannableString r8 = new SpannableString("s ");
        ColoredImageSpan r9 = new ColoredImageSpan(R.drawable.msg_mini_arrow_mediabold);
        r9.rotate(90.0f);
        r9.translate(0.0f, AndroidUtilities.dp(1.0f));
        r9.spaceScaleX = 0.85f;
        r8.setSpan(r9, 0, 1, 33);
        r72.append(r8);
        r72.append(AndroidUtilities.formatFileSize(r5.document.size));
        goto L23
    L18:
        r7 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L19
    L24:
        return true;
    L25:
        return false;
    }
}
