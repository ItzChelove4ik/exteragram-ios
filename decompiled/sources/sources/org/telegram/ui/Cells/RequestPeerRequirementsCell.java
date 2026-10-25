package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class RequestPeerRequirementsCell extends LinearLayout {
    private TLRPC.RequestPeerType requestPeerType;
    private ArrayList<Requirement> requirements;

    /* renamed from: org.telegram.ui.Cells.RequestPeerRequirementsCell$1, reason: invalid class name */
    public class AnonymousClass1 extends View {
        final /* synthetic */ RequestPeerRequirementsCell this$0;
        final /* synthetic */ int val$heightDp;

        public AnonymousClass1(RequestPeerRequirementsCell r1, Context r2, int r3) {
            this.this$0 = r1;
            this.val$heightDp = r3;
            super(r2);
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(this.val$heightDp), TLObject.FLAG_30));
        }
    }

    public class RequirementCell extends LinearLayout {
        private ImageView imageView;
        private TextView textView;
        final /* synthetic */ RequestPeerRequirementsCell this$0;

        public RequirementCell(RequestPeerRequirementsCell r11, Context r12, Requirement r13) {
            this.this$0 = r11;
            super(r12);
            setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            setOrientation(0);
            ImageView r0 = new ImageView(r12);
            this.imageView = r0;
            r0.setScaleType(ImageView.ScaleType.CENTER);
            ImageView r02 = this.imageView;
            if (r13.padding > 0) goto L5;
            int r1 = R.drawable.list_check;
        L6:
            r02.setImageResource(r1);
            this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteBlueHeader), PorterDuff.Mode.MULTIPLY));
            addView(this.imageView, LayoutHelper.createLinear(20, 20, 0.0f, 51, (r13.padding * 16) + 17, -1, 0, 0));
            TextView r03 = new TextView(r12);
            this.textView = r03;
            r03.setTextSize(1, 14.0f);
            this.textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2));
            this.textView.setSingleLine(false);
            this.textView.setText(r13.text);
            addView(this.textView, LayoutHelper.createLinear(-1, -2, 1, 6, 4, 24, 4));
            return;
        L5:
            r1 = R.drawable.list_circle;
            goto L6
        }
    }

    public RequestPeerRequirementsCell(Context r1) {
        super(r1);
        this.requirements = new ArrayList();
        setOrientation(1);
        setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
    }

    private void checkAdminRights(TLRPC.TL_chatAdminRights r1, boolean r2, int r3, int r4) {
        checkAdminRights(r1, r2, AndroidUtilities.replaceTags(LocaleController.getString(r3)), AndroidUtilities.replaceTags(LocaleController.getString(r4)));
    }

    private void checkAdminRights(TLRPC.TL_chatAdminRights r4, boolean r5, CharSequence r6, CharSequence r7) {
        if (r4 == null) goto L68;
        ArrayList r0 = new ArrayList();
        if (r4.change_info == false) goto L11;
        if (r5 == false) goto L8;
        String r1 = LocaleController.getString(R.string.EditAdminChangeChannelInfo);
    L9:
        r0.add(Requirement.make(1, r1));
        goto L11
    L8:
        r1 = LocaleController.getString(R.string.EditAdminChangeGroupInfo);
    L11:
        if (r4.post_messages == false) goto L15;
        if (r5 == false) goto L15;
        r0.add(Requirement.make(1, LocaleController.getString(R.string.EditAdminPostMessages)));
    L15:
        if (r4.edit_messages == false) goto L19;
        if (r5 == false) goto L19;
        r0.add(Requirement.make(1, LocaleController.getString(R.string.EditAdminEditMessages)));
    L19:
        if (r4.delete_messages == false) goto L26;
        if (r5 == false) goto L23;
        int r12 = R.string.EditAdminDeleteMessages;
    L24:
        r0.add(Requirement.make(1, LocaleController.getString(r12)));
        goto L26
    L23:
        r12 = R.string.EditAdminGroupDeleteMessages;
    L26:
        if (r4.ban_users == false) goto L30;
        if (r5 == true) goto L30;
        r0.add(Requirement.make(1, LocaleController.getString(R.string.EditAdminBanUsers)));
    L30:
        if (r4.invite_users == false) goto L33;
        r0.add(Requirement.make(1, LocaleController.getString(R.string.EditAdminAddUsers)));
    L33:
        if (r4.pin_messages == false) goto L37;
        if (r5 == true) goto L37;
        r0.add(Requirement.make(1, LocaleController.getString(R.string.EditAdminPinMessages)));
    L37:
        if (r4.add_admins == false) goto L40;
        r0.add(Requirement.make(1, LocaleController.getString(R.string.EditAdminAddAdmins)));
    L40:
        if (r4.anonymous == false) goto L44;
        if (r5 == true) goto L44;
        r0.add(Requirement.make(1, LocaleController.getString(R.string.EditAdminSendAnonymously)));
    L44:
        if (r4.manage_call == false) goto L47;
        r0.add(Requirement.make(1, LocaleController.getString(R.string.StartVoipChatPermission)));
    L47:
        if (r4.manage_topics == false) goto L50;
        if (r5 == true) goto L50;
        r0.add(Requirement.make(1, LocaleController.getString(R.string.ManageTopicsPermission)));
    L50:
        int r52 = 0;
        if (r0.size() != 1) goto L55;
        this.requirements.add(Requirement.make(TextUtils.concat(new CharSequence[]{r7, " ", ((Requirement) r0.get(0)).text})));
        return;
    L55:
        if (r0.isEmpty() == true) goto L67;
        SpannableStringBuilder r42 = SpannableStringBuilder.valueOf(r6);
        r42.append(" ");
    L58:
        if (r52 >= r0.size()) goto L62;
        if (r52 <= 0) goto L61;
        r42.append(", ");
    L61:
        r42.append(((Requirement) r0.get(r52)).text.toString().toLowerCase());
        r52 = r52 + 1;
        goto L58
    L62:
        r42.append(".");
        this.requirements.add(Requirement.make(r42));
        return;
    L67:
        return;
    }

    private void checkRequirement(Boolean r1, int r2, int r3) {
        if (r1 == null) goto L9;
        boolean r12 = r1.booleanValue();
        ArrayList<Requirement> r0 = this.requirements;
        if (r12 == false) goto L7;
        r0.add(Requirement.make(AndroidUtilities.replaceTags(LocaleController.getString(r2))));
        return;
    L7:
        r0.add(Requirement.make(AndroidUtilities.replaceTags(LocaleController.getString(r3))));
        return;
    }

    private View emptyView(int r2, int r3) {
        return emptyView(r2, new ColorDrawable(r3));
    }

    private View emptyView(int r3, Drawable r4) {
        AnonymousClass1 r0 = new AnonymousClass1(this, getContext(), r3);
        r0.setBackground(r4);
        return r0;
    }

    public static CharSequence rightsToString(TLRPC.TL_chatAdminRights r3, boolean r4) {
        ArrayList r0 = new ArrayList();
        if (r3.change_info == false) goto L9;
        if (r4 == false) goto L6;
        String r1 = LocaleController.getString(R.string.EditAdminChangeChannelInfo);
    L7:
        r0.add(Requirement.make(1, r1));
        goto L9
    L6:
        r1 = LocaleController.getString(R.string.EditAdminChangeGroupInfo);
    L9:
        if (r3.post_messages == false) goto L13;
        if (r4 == false) goto L13;
        r0.add(Requirement.make(1, LocaleController.getString(R.string.EditAdminPostMessages)));
    L13:
        if (r3.edit_messages == false) goto L17;
        if (r4 == false) goto L17;
        r0.add(Requirement.make(1, LocaleController.getString(R.string.EditAdminEditMessages)));
    L17:
        if (r3.delete_messages == false) goto L24;
        if (r4 == false) goto L21;
        int r12 = R.string.EditAdminDeleteMessages;
    L22:
        r0.add(Requirement.make(1, LocaleController.getString(r12)));
        goto L24
    L21:
        r12 = R.string.EditAdminGroupDeleteMessages;
    L24:
        if (r3.ban_users == false) goto L28;
        if (r4 == true) goto L28;
        r0.add(Requirement.make(1, LocaleController.getString(R.string.EditAdminBanUsers)));
    L28:
        if (r3.invite_users == false) goto L31;
        r0.add(Requirement.make(1, LocaleController.getString(R.string.EditAdminAddUsers)));
    L31:
        if (r3.pin_messages == false) goto L35;
        if (r4 == true) goto L35;
        r0.add(Requirement.make(1, LocaleController.getString(R.string.EditAdminPinMessages)));
    L35:
        if (r3.add_admins == false) goto L38;
        r0.add(Requirement.make(1, LocaleController.getString(R.string.EditAdminAddAdmins)));
    L38:
        if (r3.anonymous == false) goto L42;
        if (r4 == true) goto L42;
        r0.add(Requirement.make(1, LocaleController.getString(R.string.EditAdminSendAnonymously)));
    L42:
        if (r3.manage_call == false) goto L45;
        r0.add(Requirement.make(1, LocaleController.getString(R.string.StartVoipChatPermission)));
    L45:
        if (r3.manage_topics == false) goto L48;
        if (r4 == true) goto L48;
        r0.add(Requirement.make(1, LocaleController.getString(R.string.ManageTopicsPermission)));
    L48:
        int r42 = 0;
        if (r0.size() != 1) goto L53;
        return ((Requirement) r0.get(0)).text.toString().toLowerCase();
    L53:
        if (r0.isEmpty() == true) goto L61;
        SpannableStringBuilder r32 = new SpannableStringBuilder();
    L56:
        if (r42 >= r0.size()) goto L60;
        if (r42 <= 0) goto L59;
        r32.append(", ");
    L59:
        r32.append(((Requirement) r0.get(r42)).text.toString().toLowerCase());
        r42 = r42 + 1;
        goto L56
    L60:
        return r32;
    L61:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public void set(TLRPC.RequestPeerType r8) {
        if (this.requestPeerType == r8) goto L42;
        this.requestPeerType = r8;
        removeAllViews();
        this.requirements.clear();
        if ((r8 instanceof TLRPC.TL_requestPeerTypeUser) == false) goto L7;
        checkRequirement(((TLRPC.TL_requestPeerTypeUser) r8).premium, R.string.PeerRequirementPremiumTrue, R.string.PeerRequirementPremiumFalse);
    L35:
        if (this.requirements.isEmpty() == true) goto L43;
        HeaderCell r82 = new HeaderCell(getContext(), 20);
        r82.setText(LocaleController.getString(R.string.PeerRequirements));
        int r0 = Theme.key_windowBackgroundWhite;
        r82.setBackgroundColor(Theme.getColor(r0));
        addView(r82, LayoutHelper.createLinear(-1, -2));
        addView(emptyView(9, Theme.getColor(r0)), LayoutHelper.createLinear(-1, -2));
        ArrayList<Requirement> r83 = this.requirements;
        int r02 = r83.size();
        int r3 = 0;
    L37:
        if (r3 >= r02) goto L39;
        Requirement r4 = r83.get(r3);
        r3 = r3 + 1;
        Context r6 = getContext();
        addView(new RequirementCell(this, r6, r4), LayoutHelper.createLinear(-1, -2));
        goto L37
    L39:
        addView(emptyView(12, Theme.getColor(Theme.key_windowBackgroundWhite)), LayoutHelper.createLinear(-1, -2));
        addView(emptyView(12, Theme.getThemedDrawableByKey(getContext(), R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow)), LayoutHelper.createLinear(-1, -2));
        return;
    L43:
        return;
    L7:
        boolean r03 = r8 instanceof TLRPC.TL_requestPeerTypeBroadcast;
        if (r03 == false) goto L19;
        checkRequirement(r8.has_username, R.string.PeerRequirementChannelPublicTrue, R.string.PeerRequirementChannelPublicFalse);
        Boolean r1 = r8.bot_participant;
        if (r1 != null) goto L12;
    L14:
        Boolean r12 = r8.creator;
        if (r12 != null) goto L17;
    L29:
        Boolean r13 = r8.creator;
        if (r13 != null) goto L32;
    L33:
        checkAdminRights(r8.user_admin_rights, r03, R.string.PeerRequirementUserRights, R.string.PeerRequirementUserRight);
        goto L35
    L32:
        if (r13.booleanValue() == true) goto L35;
    L17:
        if (r12.booleanValue() == false) goto L29;
        this.requirements.add(Requirement.make(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PeerRequirementChannelCreatorTrue))));
        goto L29
    L12:
        if (r1.booleanValue() == false) goto L14;
        this.requirements.add(Requirement.make(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PeerRequirementChannelBotParticipant))));
        goto L14
    L19:
        checkRequirement(r8.has_username, R.string.PeerRequirementGroupPublicTrue, R.string.PeerRequirementGroupPublicFalse);
        checkRequirement(r8.forum, R.string.PeerRequirementForumTrue, R.string.PeerRequirementForumFalse);
        Boolean r14 = r8.bot_participant;
        if (r14 != null) goto L22;
    L24:
        Boolean r15 = r8.creator;
        if (r15 == null) goto L29;
        if (r15.booleanValue() == false) goto L29;
        this.requirements.add(Requirement.make(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PeerRequirementGroupCreatorTrue))));
        goto L29
    L22:
        if (r14.booleanValue() == false) goto L24;
        this.requirements.add(Requirement.make(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PeerRequirementGroupBotParticipant))));
        goto L24
    }
}
