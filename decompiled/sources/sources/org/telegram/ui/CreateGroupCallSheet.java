package org.telegram.ui;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorUserCell;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.TextHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.voip.VoIPHelper;
import org.telegram.ui.Stories.DarkThemeResourceProvider;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.Stories.recorder.HintView2;

/* loaded from: classes6.dex */
public class CreateGroupCallSheet extends BottomSheetWithRecyclerListView {
    private UniversalAdapter adapter;
    private final FrameLayout buttonsContainer;
    private final LinearLayout buttonsLayout;
    private final ImageView closeButton;
    private boolean creatingCall;
    private final ArrayList<Long> participants;
    private final HashSet<Long> selectedParticipants;
    private final FrameLayout topView;
    private final LinearLayout topViewLayout;
    private final ButtonWithCounterView videoButton;
    private final ButtonWithCounterView voiceButton;

    /* renamed from: $r8$lambda$KChtuqrkmQfJjZ2bgkU1u4-eh6g, reason: not valid java name */
    public static /* synthetic */ void m14168$r8$lambda$KChtuqrkmQfJjZ2bgkU1u4eh6g(CreateGroupCallSheet r0, View r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$MV3X0s4fq2ms96e_adWERWfHp7g(CreateGroupCallSheet r0, TLRPC.Updates r1) {
        r0.lambda$createCall$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$ODBfPipUnGkozfK0kU3dWsvjRdI(CreateGroupCallSheet r0, View r1) {
        r0.lambda$new$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$ONPVqOJJYf1kySVuExtcYAU3Lo4(CreateGroupCallSheet r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$Tnp89g3AUQtMy8I5cEEpqRsJf7E(CreateGroupCallSheet r0, View r1, int r2, float r3, float r4) {
        r0.lambda$new$1(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$k9ZDZxAEKQ6AZA4BrETRtbw9iRM(CreateGroupCallSheet r0, TLObject r1, ButtonWithCounterView r2, boolean r3, HashSet r4, TLRPC.TL_error r5) {
        r0.lambda$createCall$5(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$oIxomhAdCB5mtfOC84QAB38ekrE(CreateGroupCallSheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$pf2ILFXW9gL8WVhAse1BGaGfmL8(CreateGroupCallSheet r0, ButtonWithCounterView r1, boolean r2, HashSet r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$createCall$6(r1, r2, r3, r4, r5);
    }

    public CreateGroupCallSheet(Context r23, Collection<Long> r24) {
        super(r23, null, false, false, false, new DarkThemeResourceProvider());
        ArrayList<Long> r2 = new ArrayList();
        this.participants = r2;
        HashSet<Long> r3 = new HashSet();
        this.selectedParticipants = r3;
        r2.addAll(r24);
        r3.addAll(r24);
        fixNavigationBar(getThemedColor(Theme.key_dialogBackground));
        this.drawDoubleNavigationBar = false;
        FrameLayout r32 = new FrameLayout(r23);
        this.topView = r32;
        LinearLayout r4 = new LinearLayout(r23);
        this.topViewLayout = r4;
        r4.setOrientation(1);
        r32.addView(r4, LayoutHelper.createFrame(-1, -1, 119));
        ImageView r6 = new ImageView(r23);
        this.closeButton = r6;
        r6.setImageResource(R.drawable.ic_close_white);
        r6.setColorFilter(new PorterDuffColorFilter(-8090220, PorterDuff.Mode.SRC_IN));
        r32.addView(r6, LayoutHelper.createFrame(24, 24.0f, 53, 0.0f, 14.0f, 14.0f, 0.0f));
        ScaleStateListAnimator.apply(r6);
        r6.setOnClickListener(new CreateGroupCallSheet$$ExternalSyntheticLambda1(this));
        FrameLayout r33 = new FrameLayout(r23);
        r33.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(80.0f), Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider)));
        ImageView r62 = new ImageView(r23);
        r62.setImageResource(R.drawable.filled_calls_users);
        r33.addView(r62, LayoutHelper.createFrame(56, 56, 17));
        r4.addView(r33, LayoutHelper.createLinear(80, 80, 1, 2, 21, 2, 13));
        int r34 = Theme.key_windowBackgroundWhiteBlackText;
        LinkSpanDrawable.LinksTextView r63 = TextHelper.makeLinkTextView(r23, 20.0f, r34, true, this.resourcesProvider);
        r63.setText(LocaleController.getString(R.string.GroupCallCreateTitle));
        r63.setGravity(17);
        r4.addView(r63, LayoutHelper.createLinear(-1, -2, 1, 2, 0, 2, 4));
        LinkSpanDrawable.LinksTextView r35 = TextHelper.makeLinkTextView(r23, 14.0f, r34, false, this.resourcesProvider);
        r35.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.GroupCallCreateText)));
        r35.setGravity(17);
        r35.setMaxWidth(HintView2.cutInFancyHalf(r35.getText(), r35.getPaint()));
        r4.addView(r35, LayoutHelper.createLinear(-1, -2, 1, 2, 0, 2, 23));
        UniversalAdapter r36 = this.adapter;
        if (r36 == null) goto L5;
        r36.update(false);
    L5:
        DefaultItemAnimator r37 = new DefaultItemAnimator();
        r37.setSupportsChangeAnimations(false);
        r37.setDelayAnimations(false);
        r37.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r37.setDurations(350);
        this.recyclerListView.setItemAnimator(r37);
        this.recyclerListView.setOnItemClickListener(new CreateGroupCallSheet$$ExternalSyntheticLambda2(this));
        FrameLayout r38 = new FrameLayout(r23);
        this.buttonsContainer = r38;
        LinearLayout r42 = new LinearLayout(r23);
        this.buttonsLayout = r42;
        r42.setOrientation(0);
        r42.setPadding(this.backgroundPaddingLeft + AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f), this.backgroundPaddingLeft + AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f));
        r38.addView(r42, LayoutHelper.createFrame(-1, -2, 87));
        ButtonWithCounterView r9 = new ButtonWithCounterView(r23, this.resourcesProvider);
        this.voiceButton = r9;
        SpannableStringBuilder r10 = new SpannableStringBuilder();
        r10.append("x  ");
        r10.setSpan(new ColoredImageSpan(R.drawable.profile_phone), 0, 1, 33);
        r10.append(LocaleController.getString(R.string.GroupCallCreateVoice));
        r9.setText(r10, false);
        r42.addView(r9, LayoutHelper.createLinear(-1, 48, 1.0f, 119, 0, 0, 6, 0));
        r9.setOnClickListener(new CreateGroupCallSheet$$ExternalSyntheticLambda3(this));
        ButtonWithCounterView r92 = new ButtonWithCounterView(r23, this.resourcesProvider);
        this.videoButton = r92;
        SpannableStringBuilder r1 = new SpannableStringBuilder();
        r1.append("x  ");
        r1.setSpan(new ColoredImageSpan(R.drawable.profile_video), 0, 1, 33);
        r1.append(LocaleController.getString(R.string.GroupCallCreateVideo));
        r92.setText(r1, false);
        r42.addView(r92, LayoutHelper.createLinear(-1, 48, 1.0f, 119, 6, 0, 0, 0));
        r92.setOnClickListener(new CreateGroupCallSheet$$ExternalSyntheticLambda4(this));
        this.containerView.addView(r38, LayoutHelper.createFrame(-1, -2, 87));
        RecyclerListView r12 = this.recyclerListView;
        int r0 = this.backgroundPaddingLeft;
        r12.setPadding(r0, 0, r0, AndroidUtilities.dp(76.0f));
    }

    private void createCall(boolean r6) {
        if (this.creatingCall == false) goto L5;
        return;
    L5:
        this.creatingCall = true;
        if (r6 == false) goto L8;
        ButtonWithCounterView r1 = this.videoButton;
    L9:
        r1.setLoading(true);
        HashSet r0 = new HashSet();
        r0.addAll(this.selectedParticipants);
        TL_phone.createConferenceCall r2 = new TL_phone.createConferenceCall();
        r2.random_id = Utilities.random.nextInt();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r2, new CreateGroupCallSheet$$ExternalSyntheticLambda5(this, r1, r6, r0));
        return;
    L8:
        r1 = this.voiceButton;
        goto L9
    }

    private void fillItems(ArrayList<UItem> r4, UniversalAdapter r5) {
        r4.add(UItem.asCustom(this.topView));
        r4.add(UItem.asShadow(null));
        ArrayList<Long> r52 = this.participants;
        if (r52 != null) goto L5;
        return;
    L5:
        if (r52.isEmpty() == true) goto L16;
        r4.add(UItem.asHeader(LocaleController.getString(R.string.GroupCallCreateAddMembers)));
        int r53 = 0;
    L8:
        if (r53 >= this.participants.size()) goto L17;
        Long r0 = this.participants.get(r53);
        r0.longValue();
        TLRPC.User r1 = MessagesController.getInstance(this.currentAccount).getUser(r0);
        if (r1 == null) goto L18;
        r4.add(SelectorUserCell.Factory.make(r1).setChecked(this.selectedParticipants.contains(r0)));
        r53 = r53 + 1;
        goto L8
    L18:
        return;
    L17:
        return;
    }

    private /* synthetic */ void lambda$createCall$4(TLRPC.Updates r2) {
        MessagesController.getInstance(this.currentAccount).processUpdates(r2, false);
    }

    private /* synthetic */ void lambda$createCall$5(TLObject r10, ButtonWithCounterView r11, boolean r12, HashSet r13, TLRPC.TL_error r14) {
        if ((r10 instanceof TLRPC.Updates) == false) goto L17;
        TLRPC.Updates r102 = (TLRPC.Updates) r10;
        MessagesController.getInstance(this.currentAccount).putUsers(r102.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r102.chats, false);
        ArrayList r142 = MessagesController.findUpdates(r102, TL_update.TL_updateGroupCall.class);
        int r0 = r142.size();
        TLRPC.GroupCall r7 = null;
        int r2 = 0;
    L5:
        if (r2 >= r0) goto L7;
        Object r3 = r142.get(r2);
        r2 = r2 + 1;
        r7 = ((TL_update.TL_updateGroupCall) r3).call;
        goto L5
    L7:
        Utilities.stageQueue.postRunnable(new CreateGroupCallSheet$$ExternalSyntheticLambda7(this, r102));
        if (r7 != null) goto L10;
    L14:
        this.creatingCall = false;
        r11.setLoading(false);
        return;
    L10:
        if (LaunchActivity.instance == null) goto L14;
        TLRPC.TL_inputGroupCall r5 = new TLRPC.TL_inputGroupCall();
        r5.id = r7.id;
        r5.access_hash = r7.access_hash;
        dismiss();
        VoIPHelper.joinConference(LaunchActivity.instance, this.currentAccount, r5, r12, r7, r13);
        return;
    L17:
        if ((r10 instanceof TL_phone.groupCall) == false) goto L25;
        TL_phone.groupCall r103 = (TL_phone.groupCall) r10;
        MessagesController.getInstance(this.currentAccount).putUsers(r103.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r103.chats, false);
        if (LaunchActivity.instance != null) goto L22;
        this.creatingCall = false;
        r11.setLoading(false);
        return;
    L22:
        TLRPC.TL_inputGroupCall r112 = new TLRPC.TL_inputGroupCall();
        TLRPC.GroupCall r132 = r103.call;
        r112.id = r132.id;
        r112.access_hash = r132.access_hash;
        dismiss();
        VoIPHelper.joinConference(LaunchActivity.instance, this.currentAccount, r112, r12, r103.call, r13);
        return;
    L25:
        if (r14 == null) goto L29;
        BulletinFactory.of(this.topBulletinContainer, this.resourcesProvider).showForError(r14);
        return;
    }

    private /* synthetic */ void lambda$createCall$6(ButtonWithCounterView r8, boolean r9, HashSet r10, TLObject r11, TLRPC.TL_error r12) {
        AndroidUtilities.runOnUIThread(new CreateGroupCallSheet$$ExternalSyntheticLambda6(this, r11, r8, r9, r10, r12));
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$new$1(View r3, int r4, float r5, float r6) {
        if (this.creatingCall == true) goto L27;
        UItem r42 = this.adapter.getItem(r4 - 1);
        if (r42 == null) goto L23;
        Object r43 = r42.object;
        if (r43 != null) goto L10;
        return;
    L10:
        if ((r43 instanceof TLRPC.User) == false) goto L13;
        long r44 = ((TLRPC.User) r43).id;
    L15:
        boolean r0 = this.selectedParticipants.contains(Long.valueOf(r44));
        HashSet<Long> r1 = this.selectedParticipants;
        if (r0 == false) goto L18;
        r1.remove(Long.valueOf(r44));
    L20:
        if ((r3 instanceof SelectorUserCell) == false) goto L26;
        ((SelectorUserCell) r3).setChecked(this.selectedParticipants.contains(Long.valueOf(r44)), true);
        return;
    L26:
        return;
    L18:
        r1.add(Long.valueOf(r44));
        goto L20
    L13:
        if ((r43 instanceof TLRPC.Chat) == false) goto L25;
        r44 = ((TLRPC.Chat) r43).id;
        goto L15
    L25:
        return;
    L23:
        return;
    }

    private /* synthetic */ void lambda$new$2(View r1) {
        createCall(false);
    }

    private /* synthetic */ void lambda$new$3(View r1) {
        createCall(true);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
        UniversalAdapter r0 = new UniversalAdapter(r9, getContext(), this.currentAccount, 0, true, new CreateGroupCallSheet$$ExternalSyntheticLambda0(this), this.resourcesProvider);
        this.adapter = r0;
        return r0;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.GroupCallCreateTitle);
    }
}
