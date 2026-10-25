package org.telegram.ui.Stories.recorder;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.AbstractSerializedData;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes7.dex */
public abstract class StoryPrivacySelector extends View {
    public static /* synthetic */ void $r8$lambda$1GeR3B71u4SYlnnRN3ZTI6oCWXk(MessagesStorage r1, HashSet r2, int r3) {
        AndroidUtilities.runOnUIThread(new StoryPrivacySelector$$ExternalSyntheticLambda1(r3, r1.getUsers(new ArrayList(r2))));
    }

    public static /* synthetic */ void $r8$lambda$k5CM3c1zGJp4KBD_8d49PdmunSg(int r1, ArrayList r2) {
        MessagesController.getInstance(r1).putUsers(r2, true);
    }

    public static void applySaved(int r2, StoryEntry r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        r3.privacy = getSaved(r2);
        r3.privacyRules.clear();
        r3.privacyRules.addAll(r3.privacy.rules);
        if (UserConfig.getInstance(r2).isPremium() == false) goto L8;
        r3.period = MessagesController.getInstance(r2).getMainSettings().getInt("story_period", 86400);
        return;
    L8:
        r3.period = 86400;
    }

    private static StoryPrivacyBottomSheet.StoryPrivacy getSaved(int r5) {
        String r0 = MessagesController.getInstance(r5).getMainSettings().getString("story_privacy2", null);     // Catch: Exception -> L18
        if (r0 == null) goto L5;
        SerializedData r1 = new SerializedData(Utilities.hexToBytes(r0));     // Catch: Exception -> L18
        StoryPrivacyBottomSheet.StoryPrivacy r02 = read(r1);     // Catch: Exception -> L18
        r1.cleanup();     // Catch: Exception -> L18
        if (r02.isNone() == true) goto L9;
        HashSet r12 = new HashSet();     // Catch: Exception -> L18
        r12.addAll(r02.selectedUserIds);     // Catch: Exception -> L18
        Iterator<ArrayList<Long>> r2 = r02.selectedUserIdsByGroup.values().iterator();     // Catch: Exception -> L18
    L12:
        if (r2.hasNext() == false) goto L15;
        r12.addAll(r2.next());     // Catch: Exception -> L18
        goto L12
    L15:
        if (r12.isEmpty() == true) goto L17;
        MessagesStorage r22 = MessagesStorage.getInstance(r5);     // Catch: Exception -> L18
        r22.getStorageQueue().postRunnable(new StoryPrivacySelector$$ExternalSyntheticLambda0(r22, r12, r5));     // Catch: Exception -> L18
    L17:
        return r02;
    L9:
        return new StoryPrivacyBottomSheet.StoryPrivacy();
    L5:
        return new StoryPrivacyBottomSheet.StoryPrivacy();
    L18:
        e = move-exception;
        FileLog.e(e);
        return new StoryPrivacyBottomSheet.StoryPrivacy();
    }

    private static StoryPrivacyBottomSheet.StoryPrivacy read(AbstractSerializedData r18) {
        boolean r1 = true;
        int r2 = r18.readInt32(true);
        if (r18.readInt32(true) != 481674261) goto L34;
        int r3 = r18.readInt32(true);
        ArrayList r6 = new ArrayList(r3);
        int r8 = 0;
    L5:
        if (r8 >= r3) goto L8;
        r6.add(TLRPC.InputUser.TLdeserialize(r18, r18.readInt32(true), true));
        r8 = r8 + 1;
        goto L5
    L8:
        if (r18.readInt32(true) != 481674261) goto L32;
        int r32 = r18.readInt32(true);
        ArrayList r82 = new ArrayList(r32);
        int r9 = 0;
    L10:
        if (r9 >= r32) goto L13;
        r82.add(Long.valueOf(r18.readInt64(true)));
        r9 = r9 + 1;
        goto L10
    L13:
        if (r18.readInt32(true) != 481674261) goto L30;
        int r33 = r18.readInt32(true);
        HashMap r92 = new HashMap();
        int r10 = 0;
    L15:
        if (r10 >= r33) goto L24;
        long r11 = r18.readInt64(r1);
        if (r18.readInt32(r1) != 481674261) goto L22;
        int r13 = r18.readInt32(r1);
        ArrayList r14 = new ArrayList(r13);
        int r15 = 0;
    L19:
        if (r15 >= r13) goto L21;
        r14.add(Long.valueOf(r18.readInt64(r1)));
        r15 = r15 + 1;
        r1 = true;
        goto L19
    L21:
        r92.put(Long.valueOf(r11), r14);
        r10 = r10 + 1;
        r1 = true;
        goto L15
    L22:
        GlShader$$ExternalSyntheticBUOutline1.m("wrong Vector magic in TL_StoryPrivacy (4)");
        return null;
    L24:
        HashSet r0 = new HashSet();
        r0.addAll(r82);
        Iterator r12 = r92.values().iterator();
    L26:
        if (r12.hasNext() == false) goto L28;
        r0.addAll((ArrayList) r12.next());
        goto L26
    L28:
        StoryPrivacyBottomSheet.StoryPrivacy r02 = new StoryPrivacyBottomSheet.StoryPrivacy(r2, r6, 0);
        r02.selectedUserIds.clear();
        r02.selectedUserIds.addAll(r82);
        r02.selectedUserIdsByGroup.clear();
        r02.selectedUserIdsByGroup.putAll(r92);
        return r02;
    L30:
        GlShader$$ExternalSyntheticBUOutline1.m("wrong Vector magic in TL_StoryPrivacy (3)");
        return null;
    L32:
        GlShader$$ExternalSyntheticBUOutline1.m("wrong Vector magic in TL_StoryPrivacy (2)");
        return null;
    L34:
        GlShader$$ExternalSyntheticBUOutline1.m("wrong Vector magic in TL_StoryPrivacy");
        return null;
    }

    public static void save(int r4, StoryPrivacyBottomSheet.StoryPrivacy r5) {
        if (r5 != null) goto L6;
        MessagesController.getInstance(r4).getMainSettings().edit().remove("story_privacy2").apply();
        return;
    L6:
        SerializedData r1 = new SerializedData(true);
        write(r1, r5);
        SerializedData r2 = new SerializedData(r1.length());
        r1.cleanup();
        write(r2, r5);
        MessagesController.getInstance(r4).getMainSettings().edit().putString("story_privacy2", Utilities.bytesToHex(r2.toByteArray())).apply();
        r2.cleanup();
    }

    private static void write(AbstractSerializedData r7, StoryPrivacyBottomSheet.StoryPrivacy r8) {
        r7.writeInt32(r8.type);
        r7.writeInt32(Vector.constructor);
        r7.writeInt32(r8.selectedInputUsers.size());
        ArrayList<TLRPC.InputUser> r1 = r8.selectedInputUsers;
        int r2 = r1.size();
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L5;
        TLRPC.InputUser r5 = r1.get(r4);
        r4 = r4 + 1;
        r5.serializeToStream(r7);
        goto L3
    L5:
        r7.writeInt32(Vector.constructor);
        r7.writeInt32(r8.selectedUserIds.size());
        ArrayList<Long> r12 = r8.selectedUserIds;
        int r22 = r12.size();
        int r42 = 0;
    L6:
        if (r42 >= r22) goto L8;
        Long r52 = r12.get(r42);
        r42 = r42 + 1;
        r7.writeInt64(r52.longValue());
        goto L6
    L8:
        r7.writeInt32(Vector.constructor);
        r7.writeInt32(r8.selectedUserIdsByGroup.size());
        Iterator<Map.Entry<Long, ArrayList<Long>>> r82 = r8.selectedUserIdsByGroup.entrySet().iterator();
    L10:
        if (r82.hasNext() == false) goto L14;
        Map.Entry<Long, ArrayList<Long>> r13 = r82.next();
        r7.writeInt64(r13.getKey().longValue());
        r7.writeInt32(Vector.constructor);
        r7.writeInt32(r13.getValue().size());
        ArrayList<Long> r14 = r13.getValue();
        int r23 = r14.size();
        int r43 = 0;
    L12:
        if (r43 >= r23) goto L10;
        Long r53 = r14.get(r43);
        r43 = r43 + 1;
        r7.writeInt64(r53.longValue());
        goto L12
    }
}
