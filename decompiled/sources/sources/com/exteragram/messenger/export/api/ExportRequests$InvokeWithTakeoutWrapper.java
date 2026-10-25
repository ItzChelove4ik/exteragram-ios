package com.exteragram.messenger.export.api;

import com.android.dx.dex.code.CstInsn$$ExternalSyntheticBUOutline0;
import okio.Buffer$$ExternalSyntheticBUOutline4;
import org.telegram.messenger.BuildVars;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes4.dex */
public class ExportRequests$InvokeWithTakeoutWrapper extends TLObject {
    public TLObject query;
    public long takeout_id;

    public ExportRequests$InvokeWithTakeoutWrapper() {
    }

    @Override // org.telegram.tgnet.TLObject
    public TLObject deserializeResponse(InputSerializedData r3, int r4, boolean r5) {
        switch(r4) {
            case -1938715001: goto L48;
            case -1916114267: goto L46;
            case -1720552011: goto L44;
            case -1663561404: goto L42;
            case -1255369827: goto L40;
            case -1132882121: goto L44;
            case -948520370: goto L48;
            case -567906571: goto L40;
            case -313079300: goto L38;
            case 157948117: goto L36;
            case 352657236: goto L46;
            case 364538944: goto L34;
            case 481674261: goto L12;
            case 997004590: goto L11;
            case 1275039392: goto L9;
            case 1595959062: goto L48;
            case 1673780490: goto L7;
            case 1694474197: goto L42;
            case 1891070632: goto L40;
            case 1910543603: goto L34;
            default: goto L4;
        };
    L4:
        Buffer$$ExternalSyntheticBUOutline4.m("cannot deserialize response with constructor: 0x", Integer.toHexString(r4));
        return null;
    L12:
        TLObject r42 = this.query;
        if ((r42 instanceof ExportRequests$getSplitRanges) == false) goto L16;
        Vector r43 = new Vector(new ExportRequests$InvokeWithTakeoutWrapper$$ExternalSyntheticLambda0());
    L22:
        TLObject r1 = this.query;
        if (r1 == null) goto L25;
        r1.freeResources();
    L25:
        if (r43 == null) goto L27;
        r43.readParams(r3, r5);
        return r43;
    L27:
        if (BuildVars.DEBUG_VERSION == true) goto L29;
        return null;
    L29:
        CstInsn$$ExternalSyntheticBUOutline0.m("unable to deserialize vector with query: ", this.query);
        return null;
    L16:
        if ((r42 instanceof ExportRequests$TL_contacts_getSaved) == false) goto L19;
        r43 = new Vector(new ExportRequests$InvokeWithTakeoutWrapper$$ExternalSyntheticLambda1());
        goto L22
    L19:
        if ((r42 instanceof TLRPC.TL_messages_getCustomEmojiDocuments) == false) goto L21;
        r43 = new Vector(new ExportRequests$InvokeWithTakeoutWrapper$$ExternalSyntheticLambda2());
        goto L22
    L21:
        r43 = null;
        goto L22
    L7:
        return TL_stories.TL_stories_stories.TLdeserialize(r3, r4, r5);
    L9:
        return TL_account.authorizations.TLdeserialize(r3, r4, r5);
    L11:
        return TLRPC.TL_users_userFull.TLdeserialize(r3, r4, r5);
    L34:
        return TLRPC.messages_Dialogs.TLdeserialize(r3, r4, r5);
    L36:
        return TLRPC.upload_File.TLdeserialize(r3, r4, r5);
    L38:
        return TL_account.webAuthorizations.TLdeserialize(r3, r4, r5);
    L40:
        return TLRPC.contacts_TopPeers.TLdeserialize(r3, r4, r5);
    L42:
        return TLRPC.messages_Chats.TLdeserialize(r3, r4, r5);
    L44:
        return TLRPC.Bool.TLdeserialize(r3, r4, r5);
    L46:
        return TLRPC.photos_Photos.TLdeserialize(r3, r4, r5);
    L48:
        return TLRPC.messages_Messages.TLdeserialize(r3, r4, r5);
    }

    @Override // org.telegram.tgnet.TLObject
    public void serializeToStream(OutputSerializedData r3) {
        r3.writeInt32(-1398145746);
        r3.writeInt64(this.takeout_id);
        TLObject r2 = this.query;
        if ((r2 instanceof ExportRequests$TL_contacts_getSaved) == false) goto L5;
    L8:
        r2.disableFree = true;
    L9:
        r2.serializeToStream(r3);
        return;
    L5:
        if ((r2 instanceof ExportRequests$getSplitRanges) == true) goto L8;
        if ((r2 instanceof TLRPC.TL_messages_getCustomEmojiDocuments) == false) goto L9;
        goto L8
    }
}
