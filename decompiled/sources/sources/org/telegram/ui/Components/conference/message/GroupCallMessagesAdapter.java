package org.telegram.ui.Components.conference.message;

import android.annotation.SuppressLint;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.voip.GroupCallMessage;
import org.telegram.messenger.voip.GroupCallMessagesController;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.conference.message.GroupCallMessageCell;

/* loaded from: classes7.dex */
public abstract class GroupCallMessagesAdapter extends RecyclerView.Adapter<GroupCallMessageCell.VH> implements GroupCallMessagesController.CallMessageListener {
    private int currentAccount;
    private TLRPC.InputGroupCall inputGroupCall;
    private boolean isAttachedToRecyclerView;
    private List<GroupCallMessage> messages;

    public GroupCallMessagesAdapter() {
        this.currentAccount = -1;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public void attach() {
        this.isAttachedToRecyclerView = true;
        int r0 = this.currentAccount;
        if (r0 != (-1)) goto L5;
        return;
    L5:
        if (this.inputGroupCall == null) goto L9;
        this.messages = GroupCallMessagesController.getInstance(r0).getCallMessages(this.inputGroupCall.id);
        notifyDataSetChanged();
        GroupCallMessagesController.getInstance(this.currentAccount).subscribeToCallMessages(this.inputGroupCall.id, this);
        return;
    }

    public void detach() {
        this.isAttachedToRecyclerView = false;
        int r0 = this.currentAccount;
        if (r0 != (-1)) goto L5;
        return;
    L5:
        if (this.inputGroupCall == null) goto L9;
        GroupCallMessagesController.getInstance(r0).unsubscribeFromCallMessages(this.inputGroupCall.id, this);
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<GroupCallMessage> r0 = this.messages;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.size();
    }

    public GroupCallMessage getMessage(int r3) {
        List<GroupCallMessage> r0 = this.messages;
        if (r0 != null) goto L5;
        return null;
    L5:
        if (r3 >= 0) goto L7;
    L10:
        return null;
    L7:
        if (r3 >= r0.size()) goto L10;
        return this.messages.get(r3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder r1, int r2) {
        onBindViewHolder((GroupCallMessageCell.VH) r1, r2);
    }

    public void onBindViewHolder(GroupCallMessageCell.VH r2, int r3) {
        List<GroupCallMessage> r0 = this.messages;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.size() <= r3) goto L10;
        GroupCallMessage r1 = this.messages.get(r3);
        ((GroupCallMessageCell) r2.itemView).set(r1);
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public GroupCallMessageCell.VH onCreateViewHolder(ViewGroup r2, int r3) {
        GroupCallMessageCell r1 = new GroupCallMessageCell(r2.getContext());
        r1.setPadding(AndroidUtilities.dp(22.0f), 0, AndroidUtilities.dp(22.0f), 0);
        return new GroupCallMessageCell.VH(r1);
    }

    @Override // org.telegram.messenger.voip.GroupCallMessagesController.CallMessageListener
    public void onNewGroupCallMessage(long r1, GroupCallMessage r3) {
        if (this.messages != null) goto L5;
        this.messages = new ArrayList();
    L5:
        this.messages.add(0, r3);
        notifyItemInserted(0);
    }

    @Override // org.telegram.messenger.voip.GroupCallMessagesController.CallMessageListener
    public void onPopGroupCallMessage() {
        List<GroupCallMessage> r0 = this.messages;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isEmpty() == true) goto L9;
        int r02 = this.messages.size() - 1;
        this.messages.remove(r02);
        notifyItemRemoved(r02);
        return;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public void setGroupCall(int r4, TLRPC.InputGroupCall r5) {
        if (this.isAttachedToRecyclerView == false) goto L9;
        int r0 = this.currentAccount;
        if (r0 == (-1)) goto L9;
        if (this.inputGroupCall == null) goto L9;
        GroupCallMessagesController.getInstance(r0).unsubscribeFromCallMessages(this.inputGroupCall.id, this);
    L9:
        this.currentAccount = r4;
        this.inputGroupCall = r5;
        if (this.isAttachedToRecyclerView == false) goto L13;
        this.messages = GroupCallMessagesController.getInstance(r4).getCallMessages(this.inputGroupCall.id);
        notifyDataSetChanged();
        GroupCallMessagesController.getInstance(r4).subscribeToCallMessages(this.inputGroupCall.id, this);
        return;
    }
}
