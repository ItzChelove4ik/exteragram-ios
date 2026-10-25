package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.DefaultItemAnimator;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextDetailCell;
import org.telegram.ui.Components.JoinCallAlert;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.TextStyleSpan;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public class CreateRtmpStreamBottomSheet extends BottomSheetWithRecyclerListView {
    private UniversalAdapter adapter;
    private boolean hasButton;
    private final boolean hasFewPeers;
    private boolean hasRevokeButton;
    private final JoinCallAlert.JoinCallAlertDelegate joinCallDelegate;
    private String rtmpKey;
    private SpannableStringBuilder rtmpKeySpoiled;
    private String rtmpUrl;
    private TLRPC.InputPeer selectAfterDismiss;
    private final boolean story;
    private TopCell topCell;

    public static class TextDetailCellFactory extends UItem.UItemFactory<TextDetailCell> {
        public static /* synthetic */ void $r8$lambda$vtNikZcKjrYf_9IoVzu79hjCgUw(TextDetailCellFactory r0, Context r1, TextDetailCell r2, View r3) {
            r0.lambda$createView$0(r1, r2, r3);
        }

        static {
            UItem.UItemFactory.setup(new TextDetailCellFactory());
        }

        public TextDetailCellFactory() {
        }

        private void copyRtmpValue(Context r1, String r2) {
            AndroidUtilities.addToClipboard(r2);
            if (AndroidUtilities.shouldShowClipboardToast() == false) goto L6;
            Toast.makeText(r1, LocaleController.getString(R.string.TextCopied), 0).show();
            return;
        }

        private /* synthetic */ void lambda$createView$0(Context r1, TextDetailCell r2, View r3) {
            copyRtmpValue(r1, r2.textView.getText().toString());
        }

        public static UItem of(CharSequence r1, CharSequence r2, boolean r3) {
            UItem r0 = UItem.ofFactory(TextDetailCellFactory.class);
            r0.text = r1;
            r0.textValue = r2;
            r0.hideDivider = !r3;
            r0.enabled = false;
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            TextDetailCell r12 = (TextDetailCell) r1;
            r12.setTextAndValue(r2.text, r2.textValue, !r2.hideDivider);
            if ((r2.text instanceof SpannableStringBuilder) == false) goto L6;
            r12.textView.setTextSize(1, 13.0f);
            r12.textView.setTranslationY(AndroidUtilities.dp(2.0f));
            r12.textView.setTypeface(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_MONO));
            return;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public TextDetailCell createView(Context r2, RecyclerListView r3, int r4, int r5, Theme.ResourcesProvider r6) {
            TextDetailCell r32 = new TextDetailCell(r2, r6, true, false);
            r32.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite, r6));
            Drawable r42 = ContextCompat.getDrawable(r2, R.drawable.msg_copy).mutate();
            r42.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteBlueHeader, r6), PorterDuff.Mode.MULTIPLY));
            r32.setImage(r42);
            r32.setImageClickListener(new CreateRtmpStreamBottomSheet$TextDetailCellFactory$$ExternalSyntheticLambda0(this, r2, r32));
            return r32;
        }
    }

    public static class TopCell extends LinearLayout {
        public TopCell(Context r12, Theme.ResourcesProvider r13) {
            super(r12);
            setOrientation(1);
            RLottieImageView r1 = new RLottieImageView(r12);
            r1.setAutoRepeat(true);
            r1.setAnimation(R.raw.utyan_streaming, 112, 112);
            r1.playAnimation();
            addView(r1, LayoutHelper.createLinear(112, 112, 49, 0, 24, 0, 0));
            TextView r14 = new TextView(r12);
            r14.setTypeface(AndroidUtilities.bold());
            r14.setText(LocaleController.formatString(R.string.Streaming, new Object[0]));
            r14.setTextSize(1, 20.0f);
            r14.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r13));
            addView(r14, LayoutHelper.createLinear(-2, -2, 1, 0, 14, 0, 7));
            TextView r15 = new TextView(r12);
            r15.setTextSize(1, 14.0f);
            r15.setGravity(1);
            r15.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r13));
            r15.setText(LocaleController.formatString(R.string.VoipStreamStart, new Object[0]));
            r15.setLineSpacing(r15.getLineSpacingExtra(), r15.getLineSpacingMultiplier() * 1.1f);
            addView(r15, LayoutHelper.createLinear(-2, -2, 1, 28, 0, 28, 17));
        }
    }

    /* renamed from: $r8$lambda$GgUqod3ZRTeu-zXkEj8PWSewxhM, reason: not valid java name */
    public static /* synthetic */ void m10061$r8$lambda$GgUqod3ZRTeuzXkEj8PWSewxhM(CreateRtmpStreamBottomSheet r0, ButtonWithCounterView r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$new$4(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$IGFpUrVmL5zuwLH0V_z7I4Yqetc(CreateRtmpStreamBottomSheet r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$new$13(r1, r2);
    }

    /* renamed from: $r8$lambda$Kq4NUJP-r4gYqHwFsxOBN0g02NY, reason: not valid java name */
    public static /* synthetic */ void m10062$r8$lambda$Kq4NUJPr4gYqHwFsxOBN0g02NY(CreateRtmpStreamBottomSheet r0, TLObject r1) {
        r0.lambda$new$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$LM6PhvwV2Iicyw7wCG0Pz8FUDcw(CreateRtmpStreamBottomSheet r0, Context r1, Theme.ResourcesProvider r2, ButtonWithCounterView r3, TL_phone.getGroupCallStreamRtmpUrl r4, int r5, View r6) {
        r0.lambda$new$6(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$PY4Xk5TbQj5XtGlIDgsoKesgqbg(CreateRtmpStreamBottomSheet r0, ButtonWithCounterView r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$QwlepziNYSoq2zy6Y3LWL5xAfn8(CreateRtmpStreamBottomSheet r0, ButtonWithCounterView r1, TL_phone.getGroupCallStreamRtmpUrl r2, int r3, AlertDialog r4, int r5) {
        r0.lambda$new$5(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$Tzv3IRMhPf3pWMIpom6Zi3Jr4PY(CreateRtmpStreamBottomSheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$WgE6sbVE5zW2TDKzeyLHe1OAYmA(CreateRtmpStreamBottomSheet r0, Utilities.Callback r1, ButtonWithCounterView r2, View r3) {
        r0.lambda$new$2(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$aEN9O-HM3oPgVW6pMt_pHMST588, reason: not valid java name */
    public static /* synthetic */ void m10063$r8$lambda$aEN9OHM3oPgVW6pMt_pHMST588(CreateRtmpStreamBottomSheet r0, Context r1, ButtonWithCounterView r2, long r3, View r5) {
        r0.lambda$new$11(r1, r2, r3, r5);
    }

    public static /* synthetic */ void $r8$lambda$bWcld5koVbQ_SzyUf5IWQ5KaVuA(CreateRtmpStreamBottomSheet r0, ButtonWithCounterView r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$new$9(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$cgklOAwQlsH8j45jcre12C6aduM(CreateRtmpStreamBottomSheet r0, ButtonWithCounterView r1, long r2, AlertDialog r4, int r5) {
        r0.lambda$new$10(r1, r2, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$dYYFJHgjZ5sfrG9rfnEemMENhKU(CreateRtmpStreamBottomSheet r0, ButtonWithCounterView r1, TLObject r2) {
        r0.lambda$new$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$kptzDJwvjSmssIWXKcvauWTnGC4(CreateRtmpStreamBottomSheet r0, ButtonWithCounterView r1, TLObject r2) {
        r0.lambda$new$8(r1, r2);
    }

    /* renamed from: $r8$lambda$st8zq3eP3NrR8TR-XLY2kml99_0, reason: not valid java name */
    public static /* synthetic */ void m10064$r8$lambda$st8zq3eP3NrR8TRXLY2kml99_0(ButtonWithCounterView r1) {
        r1.setLoading(true);
    }

    /* renamed from: $r8$lambda$t-HJISmUje3SPs2uqlHH1QWGqZs, reason: not valid java name */
    public static /* synthetic */ void m10065$r8$lambda$tHJISmUje3SPs2uqlHH1QWGqZs(CreateRtmpStreamBottomSheet r0, TLRPC.Peer r1, View r2) {
        r0.lambda$new$7(r1, r2);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [org.telegram.ui.ActionBar.BottomSheet, org.telegram.ui.Components.BottomSheetWithRecyclerListView, org.telegram.ui.Components.CreateRtmpStreamBottomSheet] */
    public CreateRtmpStreamBottomSheet(Context r19, int r20, TL_phone.getGroupCallStreamRtmpUrl r21, TL_phone.groupCallStreamRtmpUrl r22, Utilities.Callback<Browser.Progress> r23, Theme.ResourcesProvider r24) {
        ?? r0 = new BottomSheetWithRecyclerListView(r19, null, false, false, false, r24);
        r0.story = true;
        r0.topPadding = 0.126f;
        r0.joinCallDelegate = null;
        r0.hasFewPeers = false;
        long r10 = DialogObject.getPeerDialogId(r21.peer);
        if (r23 != null) goto L5;
    L9:
        boolean r4 = false;
    L10:
        r0.hasRevokeButton = r4;
        CreateRtmpStreamBottomSheet r02 = r0;
        if (r23 == null) goto L19;
        r0.hasButton = true;
        ButtonWithCounterView r2 = new ButtonWithCounterView(r19, r24);
        r2.setText(LocaleController.getString(R.string.LiveStoryRTMPEnable), false);
        ViewGroup r42 = r0.containerView;
        if (r0.hasRevokeButton == false) goto L15;
        int r6 = 52;
    L16:
        r42.addView(r2, LayoutHelper.createFrame(-1, 48.0f, 80, 16.0f, 0.0f, 16.0f, r6 + 12));
        r2.setOnClickListener(new CreateRtmpStreamBottomSheet$$ExternalSyntheticLambda13(r0, r23, r2));
        boolean r25 = r0.hasRevokeButton;
        r02 = r0;
        if (r25 == false) goto L19;
        ButtonWithCounterView r43 = new ButtonWithCounterView(r19, false, r24);
        r43.setColor(Theme.getColor(Theme.key_fill_RedNormal));
        r43.text.setTypeface(AndroidUtilities.bold());
        r43.setText(LocaleController.getString(R.string.LiveStoryRTMPRevoke), false);
        CreateRtmpStreamBottomSheet r03 = this;
        r43.setOnClickListener(new CreateRtmpStreamBottomSheet$$ExternalSyntheticLambda14(this, r19, r24, r43, r21, r20));
        r03.containerView.addView(r43, LayoutHelper.createFrame(-1, 48.0f, 80, 16.0f, 0.0f, 16.0f, 12.0f));
        r02 = r03;
        goto L19
    L15:
        r6 = 0;
    L19:
        DefaultItemAnimator r1 = new DefaultItemAnimator();
        r1.setSupportsChangeAnimations(false);
        r1.setDelayAnimations(false);
        r1.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r1.setDurations(350);
        r02.recyclerListView.setItemAnimator(r1);
        RecyclerListView r12 = r02.recyclerListView;
        int r26 = r02.backgroundPaddingLeft;
        if (r02.hasButton == true) goto L22;
        int r3 = 0;
    L27:
        r12.setPadding(r26, 0, r26, r3);
        r02.fixNavigationBar();
        r02.updateTitle();
        r02.rtmpUrl = r22.url;
        r02.rtmpKey = r22.key;
        r02.rtmpKeySpoiled = new SpannableStringBuilder(r02.rtmpKey);
        TextStyleSpan.TextStyleRun r13 = new TextStyleSpan.TextStyleRun();
        r13.flags |= 256;
        r13.start = 0;
        r13.end = r02.rtmpKeySpoiled.length();
        r02.rtmpKeySpoiled.setSpan(new TextStyleSpan(r13), 0, r02.rtmpKeySpoiled.length(), 0);
        r02.adapter.update(false);
        return;
    L22:
        if (r02.hasRevokeButton == false) goto L24;
        float r32 = 124.0f;
    L25:
        r3 = AndroidUtilities.dp(r32);
        goto L27
    L24:
        r32 = 72.0f;
        goto L25
    L5:
        if (r10 < 0) goto L7;
    L8:
        r4 = true;
        goto L10
    L7:
        if (ChatObject.isCreator(MessagesController.getInstance(r20).getChat(Long.valueOf(-r10))) == false) goto L9;
        goto L8
    }

    public CreateRtmpStreamBottomSheet(BaseFragment r18, TLRPC.Peer r19, long r20, boolean r22, JoinCallAlert.JoinCallAlertDelegate r23) {
        super(r18, false, false);
        this.story = false;
        this.topPadding = 0.26f;
        this.joinCallDelegate = r23;
        this.hasFewPeers = r22;
        Context r2 = this.containerView.getContext();
        boolean r7 = ChatObject.isCreator(MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r20)));
        this.hasButton = true;
        TextView r3 = new TextView(r2);
        r3.setGravity(17);
        r3.setEllipsize(TextUtils.TruncateAt.END);
        r3.setSingleLine(true);
        r3.setTextSize(1, 14.0f);
        r3.setTypeface(AndroidUtilities.bold());
        r3.setText(LocaleController.getString(R.string.VoipChannelStartStreaming));
        r3.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText, this.resourcesProvider));
        r3.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(8.0f), Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider), ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_windowBackgroundWhite), 120)));
        ViewGroup r0 = this.containerView;
        int r8 = 52;
        if (r7 == false) goto L5;
        int r9 = 52;
    L6:
        r0.addView(r3, LayoutHelper.createFrame(-1, 48.0f, 80, 16.0f, 0.0f, 16.0f, r9 + 12));
        r3.setOnClickListener(new CreateRtmpStreamBottomSheet$$ExternalSyntheticLambda7(this, r19));
        if (r7 == false) goto L9;
        ButtonWithCounterView r32 = new ButtonWithCounterView(r2, false, this.resourcesProvider);
        r32.setColor(Theme.getColor(Theme.key_fill_RedNormal));
        r32.text.setTypeface(AndroidUtilities.bold());
        r32.setText(LocaleController.getString(R.string.LiveStoryRTMPRevoke), false);
        r32.setOnClickListener(new CreateRtmpStreamBottomSheet$$ExternalSyntheticLambda8(this, r2, r32, r20));
        this.containerView.addView(r32, LayoutHelper.createFrame(-1, 48.0f, 80, 16.0f, 0.0f, 16.0f, 12.0f));
    L9:
        RecyclerListView r02 = this.recyclerListView;
        int r24 = this.backgroundPaddingLeft;
        if (r7 == true) goto L13;
        r8 = 0;
    L13:
        r02.setPadding(r24, 0, r24, AndroidUtilities.dp(r8 + 72));
        DefaultItemAnimator r03 = new DefaultItemAnimator();
        r03.setSupportsChangeAnimations(false);
        r03.setDelayAnimations(false);
        r03.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r03.setDurations(350);
        this.recyclerListView.setItemAnimator(r03);
        fixNavigationBar();
        updateTitle();
        TL_phone.getGroupCallStreamRtmpUrl r04 = new TL_phone.getGroupCallStreamRtmpUrl();
        r04.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r20);
        r04.revoke = false;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r04, new CreateRtmpStreamBottomSheet$$ExternalSyntheticLambda9(this));
        return;
    L5:
        r9 = 0;
        goto L6
    }

    private void fillItems(ArrayList<UItem> r4, UniversalAdapter r5) {
        if (this.topCell != null) goto L5;
        this.topCell = new TopCell(getContext(), this.resourcesProvider);
    L5:
        r4.add(UItem.asCustom(this.topCell));
        String r52 = null;
        r4.add(UItem.asShadow(null));
        r4.add(UItem.asHeader(LocaleController.getString(R.string.VoipChatStreamSettings)));
        r4.add(TextDetailCellFactory.of(this.rtmpUrl, LocaleController.getString(R.string.VoipChatStreamServerUrl), true));
        r4.add(TextDetailCellFactory.of(this.rtmpKeySpoiled, LocaleController.getString(R.string.VoipChatStreamKey), false));
        if (this.hasButton == true) goto L8;
    L12:
        r4.add(UItem.asShadow(r52));
        return;
    L8:
        if (this.story == false) goto L10;
        int r3 = R.string.VoipChatStreamWithAnotherAppDescriptionStory;
    L11:
        r52 = LocaleController.getString(r3);
        goto L12
    L10:
        r3 = R.string.VoipChatStreamWithAnotherAppDescription;
        goto L11
    }

    private /* synthetic */ void lambda$new$1(ButtonWithCounterView r2) {
        r2.setLoading(false);
        dismiss();
    }

    private /* synthetic */ void lambda$new$10(ButtonWithCounterView r2, long r3, AlertDialog r5, int r6) {
        if (r2.isLoading() == false) goto L5;
        return;
    L5:
        r2.setLoading(true);
        TL_phone.getGroupCallStreamRtmpUrl r62 = new TL_phone.getGroupCallStreamRtmpUrl();
        r62.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r3);
        r62.revoke = true;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r62, new CreateRtmpStreamBottomSheet$$ExternalSyntheticLambda12(this, r2));
    }

    private /* synthetic */ void lambda$new$11(Context r2, ButtonWithCounterView r3, long r4, View r6) {
        new AlertDialog.Builder(r2, this.resourcesProvider).setTitle(LocaleController.getString(R.string.LiveStoryRTMPRevokeTitle)).setMessage(LocaleController.getString(R.string.LiveStoryRTMPRevokeText)).setPositiveButton(LocaleController.getString(R.string.RevokeButton), new CreateRtmpStreamBottomSheet$$ExternalSyntheticLambda11(this, r3, r4)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).makeRed(-1).show();
    }

    private /* synthetic */ void lambda$new$12(TLObject r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        if ((r4 instanceof TL_phone.groupCallStreamRtmpUrl) == false) goto L8;
        TL_phone.groupCallStreamRtmpUrl r42 = (TL_phone.groupCallStreamRtmpUrl) r4;
        this.rtmpUrl = r42.url;
        this.rtmpKey = r42.key;
        this.rtmpKeySpoiled = new SpannableStringBuilder(this.rtmpKey);
        TextStyleSpan.TextStyleRun r43 = new TextStyleSpan.TextStyleRun();
        r43.flags |= 256;
        r43.start = 0;
        r43.end = this.rtmpKeySpoiled.length();
        this.rtmpKeySpoiled.setSpan(new TextStyleSpan(r43), 0, this.rtmpKeySpoiled.length(), 0);
        this.adapter.update(false);
        return;
    }

    private /* synthetic */ void lambda$new$13(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new CreateRtmpStreamBottomSheet$$ExternalSyntheticLambda10(this, r1));
    }

    private /* synthetic */ void lambda$new$2(Utilities.Callback r3, ButtonWithCounterView r4, View r5) {
        r3.run(new Browser.Progress(new CreateRtmpStreamBottomSheet$$ExternalSyntheticLambda2(r4), new CreateRtmpStreamBottomSheet$$ExternalSyntheticLambda3(this, r4)));
    }

    private /* synthetic */ void lambda$new$3(ButtonWithCounterView r2, TLObject r3) {
        r2.setLoading(false);
        if ((r3 instanceof TL_phone.groupCallStreamRtmpUrl) == false) goto L6;
        TL_phone.groupCallStreamRtmpUrl r32 = (TL_phone.groupCallStreamRtmpUrl) r3;
        this.rtmpUrl = r32.url;
        this.rtmpKey = r32.key;
        this.rtmpKeySpoiled = new SpannableStringBuilder(this.rtmpKey);
        this.adapter.update(true);
        return;
    }

    private /* synthetic */ void lambda$new$4(ButtonWithCounterView r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new CreateRtmpStreamBottomSheet$$ExternalSyntheticLambda5(this, r1, r2));
    }

    private /* synthetic */ void lambda$new$5(ButtonWithCounterView r1, TL_phone.getGroupCallStreamRtmpUrl r2, int r3, AlertDialog r4, int r5) {
        if (r1.isLoading() == false) goto L5;
        return;
    L5:
        r1.setLoading(true);
        r2.revoke = true;
        ConnectionsManager.getInstance(r3).sendRequest(r2, new CreateRtmpStreamBottomSheet$$ExternalSyntheticLambda4(this, r1));
    }

    private /* synthetic */ void lambda$new$6(Context r1, Theme.ResourcesProvider r2, ButtonWithCounterView r3, TL_phone.getGroupCallStreamRtmpUrl r4, int r5, View r6) {
        new AlertDialog.Builder(r1, r2).setTitle(LocaleController.getString(R.string.LiveStoryRTMPRevokeTitle)).setMessage(LocaleController.getString(R.string.LiveStoryRTMPRevokeText)).setPositiveButton(LocaleController.getString(R.string.RevokeButton), new CreateRtmpStreamBottomSheet$$ExternalSyntheticLambda1(this, r3, r4, r5)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).makeRed(-1).show();
    }

    private /* synthetic */ void lambda$new$7(TLRPC.Peer r3, View r4) {
        this.selectAfterDismiss = MessagesController.getInstance(this.currentAccount).getInputPeer(MessageObject.getPeerId(r3));
        dismiss();
    }

    private /* synthetic */ void lambda$new$8(ButtonWithCounterView r2, TLObject r3) {
        r2.setLoading(false);
        if (r3 != null) goto L5;
        return;
    L5:
        if ((r3 instanceof TL_phone.groupCallStreamRtmpUrl) == false) goto L9;
        TL_phone.groupCallStreamRtmpUrl r32 = (TL_phone.groupCallStreamRtmpUrl) r3;
        this.rtmpUrl = r32.url;
        this.rtmpKey = r32.key;
        this.rtmpKeySpoiled = new SpannableStringBuilder(this.rtmpKey);
        this.adapter.update(true);
        return;
    }

    private /* synthetic */ void lambda$new$9(ButtonWithCounterView r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new CreateRtmpStreamBottomSheet$$ExternalSyntheticLambda0(this, r1, r2));
    }

    public static void show(TLRPC.Peer r7, BaseFragment r8, long r9, boolean r11, JoinCallAlert.JoinCallAlertDelegate r12) {
        CreateRtmpStreamBottomSheet r0 = new CreateRtmpStreamBottomSheet(r8, r7, r9, r11, r12);
        if (r8 != null) goto L5;
    L8:
        r0.show();
        return;
    L5:
        if (r8.getParentActivity() == null) goto L8;
        r8.showDialog(r0);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
        UniversalAdapter r0 = new UniversalAdapter(r9, getContext(), this.currentAccount, 0, true, new CreateRtmpStreamBottomSheet$$ExternalSyntheticLambda6(this), this.resourcesProvider);
        this.adapter = r0;
        return r0;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissInternal() {
        super.dismissInternal();
        JoinCallAlert.JoinCallAlertDelegate r0 = this.joinCallDelegate;
        if (r0 == null) goto L8;
        TLRPC.InputPeer r1 = this.selectAfterDismiss;
        if (r1 == null) goto L9;
        r0.didSelectChat(r1, this.hasFewPeers, false, true);
        return;
    L9:
        return;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.Streaming);
    }
}
