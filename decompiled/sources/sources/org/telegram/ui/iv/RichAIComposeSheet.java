package org.telegram.ui.iv;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.RichMessageLayout;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_aicompose;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.EditTextCell;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public class RichAIComposeSheet extends BottomSheetWithRecyclerListView {
    private UniversalAdapter adapter;
    private final ButtonWithCounterView button;
    private final int currentAccount;
    private boolean loading;
    private final Utilities.Callback<TL_iv.RichMessage> onAddToPage;
    private final FrameLayout previewBox;
    private final RichMessageLayout.PreviewView previewView;
    private final FrameLayout promptBox;
    private final EditTextCell promptCell;
    private int reqId;
    private TL_iv.RichMessage result;
    private final FrameLayout topView;

    /* renamed from: org.telegram.ui.iv.RichAIComposeSheet$1, reason: invalid class name */
    public class AnonymousClass1 implements TextWatcher {
        final /* synthetic */ RichAIComposeSheet this$0;

        public AnonymousClass1(RichAIComposeSheet r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r1) {
            if (RichAIComposeSheet.m21624$$Nest$fgetresult(this.this$0) == null) goto L5;
            RichAIComposeSheet.m21625$$Nest$mresetResult(this.this$0);
        L5:
            RichAIComposeSheet.m21626$$Nest$mupdateButtonEnabled(this.this$0);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: $r8$lambda$4JTVutVIhpuRgQj75-79VyVdi7Q, reason: not valid java name */
    public static /* synthetic */ void m21621$r8$lambda$4JTVutVIhpuRgQj7579VyVdi7Q(RichAIComposeSheet r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$onButtonClick$4(r1, r2);
    }

    /* renamed from: $r8$lambda$9Wr-uWjBgNhqAUie69RPht1D9B0, reason: not valid java name */
    public static /* synthetic */ void m21622$r8$lambda$9WruWjBgNhqAUie69RPht1D9B0(RichAIComposeSheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$CdoSVd1Qu43FQCQmAtv7G4Rfqe8(RichAIComposeSheet r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$dnbmB3XF1EumMTXVJrMTHpf9oso(RichAIComposeSheet r0, View r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: $r8$lambda$e73sXriFrhdL4z-A2r702r9zm1A, reason: not valid java name */
    public static /* synthetic */ void m21623$r8$lambda$e73sXriFrhdL4zA2r702r9zm1A(RichAIComposeSheet r0) {
        r0.lambda$show$2();
    }

    public static /* synthetic */ void $r8$lambda$whc6xROlAj0JHYJynUsSsE1d_4Q(RichAIComposeSheet r0, TLObject r1) {
        r0.lambda$onButtonClick$3(r1);
    }

    /* renamed from: -$$Nest$fgetresult, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_iv.RichMessage m21624$$Nest$fgetresult(RichAIComposeSheet r0) {
        return r0.result;
    }

    /* renamed from: -$$Nest$mresetResult, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21625$$Nest$mresetResult(RichAIComposeSheet r0) {
        r0.resetResult();
    }

    /* renamed from: -$$Nest$mupdateButtonEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21626$$Nest$mupdateButtonEnabled(RichAIComposeSheet r0) {
        r0.updateButtonEnabled();
    }

    public RichAIComposeSheet(Context r23, int r24, Theme.ResourcesProvider r25, Utilities.Callback<TL_iv.RichMessage> r26) {
        super(r23, null, true, false, false, r25);
        this.currentAccount = r24;
        this.onAddToPage = r26;
        int r0 = Theme.key_windowBackgroundGray;
        setBackgroundColor(Theme.getColor(r0, r25));
        fixNavigationBar(Theme.getColor(r0, r25));
        FrameLayout r02 = new FrameLayout(r23);
        this.topView = r02;
        TextView r2 = new TextView(r23);
        r2.setText(LocaleController.getString(R.string.ArticleAICreate));
        r2.setTextSize(1, 20.0f);
        r2.setTypeface(AndroidUtilities.bold());
        int r3 = Theme.key_windowBackgroundWhiteBlackText;
        r2.setTextColor(Theme.getColor(r3, r25));
        r2.setGravity(19);
        r02.addView(r2, LayoutHelper.createFrame(-1, 56.0f, 51, 22.0f, 6.0f, 56.0f, 0.0f));
        this.headerPaddingTop = AndroidUtilities.dp(-8.0f);
        ImageView r22 = new ImageView(r23);
        r22.setImageResource(R.drawable.ic_close_white);
        r22.setScaleType(ImageView.ScaleType.CENTER);
        r22.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r3, r25), PorterDuff.Mode.SRC_IN));
        ScaleStateListAnimator.apply(r22);
        r22.setOnClickListener(new RichAIComposeSheet$$ExternalSyntheticLambda2(this));
        r02.addView(r22, LayoutHelper.createFrame(48, 48.0f, 53, 0.0f, 10.0f, 12.0f, 0.0f));
        FrameLayout r03 = new FrameLayout(r23);
        this.previewBox = r03;
        RichMessageLayout.PreviewView r27 = new RichMessageLayout.PreviewView(r23, r24, r25);
        this.previewView = r27;
        r27.setPadding(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(12.0f));
        int r32 = AndroidUtilities.dp(12.0f);
        int r11 = Theme.key_windowBackgroundWhite;
        r27.setBackground(Theme.createRoundRectDrawable(r32, Theme.getColor(r11, r25)));
        r03.addView(r27, LayoutHelper.createFrame(-1, -2.0f));
        r03.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), 0);
        FrameLayout r15 = new FrameLayout(r23);
        this.promptBox = r15;
        EditTextCell r04 = new EditTextCell(r23, LocaleController.getString(R.string.ArticleAIPrompt), true, false, MessagesController.getInstance(r24).config.aicomposeTonePromptLengthMax.get(), r25);
        this.promptCell = r04;
        r04.editText.setImeOptions(6);
        r04.editText.setMaxLines(5);
        r04.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(20.0f), Theme.getColor(r11, r25)));
        r04.editText.addTextChangedListener(new AnonymousClass1(this));
        r15.addView(r04, LayoutHelper.createFrame(-1, -2.0f));
        r15.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
        ButtonWithCounterView r05 = new ButtonWithCounterView(r23, r25).setRound();
        this.button = r05;
        r05.setText(LocaleController.getString(R.string.ArticleAIGenerate), false);
        r05.setOnClickListener(new RichAIComposeSheet$$ExternalSyntheticLambda3(this));
        this.containerView.addView(r05, LayoutHelper.createFrame(-1, 48.0f, 87, 12.0f, 12.0f, 12.0f, 12.0f));
        ((ViewGroup.MarginLayoutParams) r05.getLayoutParams()).leftMargin += this.backgroundPaddingLeft;
        ((ViewGroup.MarginLayoutParams) r05.getLayoutParams()).rightMargin += this.backgroundPaddingLeft;
        DefaultItemAnimator r06 = new DefaultItemAnimator();
        r06.setSupportsChangeAnimations(false);
        r06.setDelayAnimations(false);
        r06.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r06.setDurations(350);
        this.recyclerListView.setItemAnimator(r06);
        RecyclerListView r07 = this.recyclerListView;
        int r1 = this.backgroundPaddingLeft;
        r07.setPadding(r1, 0, r1, AndroidUtilities.dp(72.0f));
        this.recyclerListView.setClipToPadding(false);
        this.adapter.update(false);
        updateButtonEnabled();
    }

    private void fillItems(ArrayList<UItem> r2, UniversalAdapter r3) {
        r2.add(UItem.asCustom(1, this.topView));
        r2.add(UItem.asCustom(3, this.promptBox));
        if (this.result == null) goto L6;
        r2.add(UItem.asCustom(2, this.previewBox));
        return;
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        onButtonClick();
    }

    private /* synthetic */ void lambda$onButtonClick$3(TLObject r3) {
        this.loading = false;
        this.reqId = 0;
        this.button.setLoading(false);
        if ((r3 instanceof TLRPC.TL_composedRichMessageWithAI) == false) goto L6;
        showResult(((TLRPC.TL_composedRichMessageWithAI) r3).result);
        return;
    L6:
        AndroidUtilities.shakeViewSpring(this.button, 4.0f);
    }

    private /* synthetic */ void lambda$onButtonClick$4(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new RichAIComposeSheet$$ExternalSyntheticLambda5(this, r1));
    }

    private /* synthetic */ void lambda$show$2() {
        AndroidUtilities.showKeyboard(this.promptCell.editText);
    }

    private void onButtonClick() {
        if (this.loading == true) goto L17;
        TL_iv.RichMessage r0 = this.result;
        if (r0 == null) goto L12;
        Utilities.Callback<TL_iv.RichMessage> r1 = this.onAddToPage;
        if (r1 == null) goto L10;
        r1.run(r0);
    L10:
        dismiss();
        return;
    L12:
        String r02 = this.promptCell.editText.getText().toString().trim();
        if (TextUtils.isEmpty(r02) == false) goto L15;
        return;
    L15:
        this.loading = true;
        this.button.setLoading(true);
        TLRPC.TL_messages_composeRichMessageWithAI r12 = new TLRPC.TL_messages_composeRichMessageWithAI();
        TL_aicompose.inputAiComposeToneSingleUse r2 = new TL_aicompose.inputAiComposeToneSingleUse();
        r2.custom_prompt = r02;
        r12.tone = r2;
        this.reqId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r12, new RichAIComposeSheet$$ExternalSyntheticLambda4(this));
        AndroidUtilities.hideKeyboard(this.promptCell.editText);
        return;
    }

    private void resetResult() {
        if (this.result == null) goto L10;
        this.result = null;
        this.button.setText(LocaleController.getString(R.string.ArticleAIGenerate), true);
        UniversalAdapter r3 = this.adapter;
        if (r3 == null) goto L9;
        r3.update(true);
        return;
    L9:
        return;
    }

    private void showResult(TL_iv.RichMessage r3) {
        if (r3 != null) goto L5;
        AndroidUtilities.shakeViewSpring(this.button, 4.0f);
        return;
    L5:
        this.result = r3;
        this.previewView.set(r3);
        this.button.setText(LocaleController.getString(R.string.ArticleAIAddToPage), true);
        updateButtonEnabled();
        UniversalAdapter r2 = this.adapter;
        if (r2 == null) goto L9;
        r2.update(true);
        return;
    }

    private void updateButtonEnabled() {
        TL_iv.RichMessage r0 = this.result;
        ButtonWithCounterView r1 = this.button;
        if (r0 == null) goto L6;
        r1.setEnabled(true);
        return;
    L6:
        r1.setEnabled(!TextUtils.isEmpty(this.promptCell.editText.getText().toString().trim()));
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
        UniversalAdapter r0 = new UniversalAdapter(r9, getContext(), this.currentAccount, 0, true, new RichAIComposeSheet$$ExternalSyntheticLambda1(this), this.resourcesProvider);
        this.adapter = r0;
        return r0;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        if (this.reqId == 0) goto L5;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.reqId, true);
        this.reqId = 0;
    L5:
        AndroidUtilities.hideKeyboard(this.promptCell.editText);
        super.dismiss();
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.ArticleAICreate);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        super.show();
        AndroidUtilities.runOnUIThread(new RichAIComposeSheet$$ExternalSyntheticLambda0(this), 200);
    }
}
