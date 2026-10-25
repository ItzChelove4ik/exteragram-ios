package com.exteragram.messenger.feed.ads;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.telegram.messenger.FileLog;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
abstract class FeedAdParser {
    public static ArrayList<FeedAd> parse(TLRPC.messages_Messages r9) {
        ArrayList<FeedAd> r0 = new ArrayList();
        if (r9 != null) goto L5;
    L59:
        return r0;
    L5:
        if (r9.messages == null) goto L59;
        HashSet r1 = new HashSet();
        ArrayList<TLRPC.Message> r2 = r9.messages;
        int r3 = r2.size();
        int r4 = 0;
        int r5 = 0;
    L8:
        if (r5 >= r3) goto L22;
        TLRPC.Message r6 = r2.get(r5);
        r5 = r5 + 1;
        TLRPC.Message r62 = r6;
        if ((r62 instanceof TLRPC.TL_message) == false) goto L8;
        String r7 = r62.message;
        if (r7 == null) goto L8;
        if (r7.startsWith("feed_ad") == false) goto L8;
        FeedAd r63 = parseManifest(r62.message);
        if (r63 == null) goto L8;
        if (r1.add(r63.id) == false) goto L8;
        r0.add(r63);
        goto L8
    L22:
        if (r0.isEmpty() == true) goto L59;
        ArrayList<TLRPC.Message> r92 = r9.messages;
        int r12 = r92.size();
        int r22 = 0;
    L25:
        if (r22 >= r12) goto L51;
        TLRPC.Message r32 = r92.get(r22);
        r22 = r22 + 1;
        TLRPC.Message r33 = r32;
        if ((r33 instanceof TLRPC.TL_message) == false) goto L25;
        int r52 = 0;
    L31:
        if (r52 >= r0.size()) goto L25;
        FeedAd r64 = r0.get(r52);
        int r72 = r64.bodyMessageId;
        if (r72 != 0) goto L35;
    L43:
        int r73 = r64.mediaMessageId;
        if (r73 == 0) goto L50;
        if (r33.id != r73) goto L50;
        TLRPC.MessageMedia r74 = r33.media;
        if (r74 == null) goto L50;
        r64.media = r74;
    L50:
        r52 = r52 + 1;
        goto L31
    L35:
        if (r33.id != r72) goto L43;
        r64.bodyText = r33.message;
        ArrayList<TLRPC.MessageEntity> r75 = r33.entities;
        if (r75 != null) goto L39;
    L41:
        ArrayList<TLRPC.MessageEntity> r76 = null;
    L42:
        r64.entities = r76;
        goto L43
    L39:
        if (r75.isEmpty() == true) goto L41;
        r76 = new ArrayList(r33.entities);
        goto L42
    L51:
        ArrayList<FeedAd> r93 = new ArrayList(r0.size());
    L53:
        if (r4 >= r0.size()) goto L58;
        if (r0.get(r4).isDisplayable() == false) goto L57;
        r93.add(r0.get(r4));
    L57:
        r4 = r4 + 1;
        goto L53
    L58:
        return r93;
    }

    private static int parseInt(String r0, int r1) {
        return Integer.parseInt(r0);
    L4:
        return r1;
    }

    private static Set<String> parseLocales(String r5) {
        HashSet r0 = new HashSet();
        String[] r52 = r5.split(",");
        int r1 = r52.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L9;
        String r3 = r52[r2].trim().toLowerCase();
        if (r3.isEmpty() == true) goto L7;
        r0.add(r3);
    L7:
        r2 = r2 + 1;
        goto L3
    L9:
        if (r0.isEmpty() == false) goto L12;
        return null;
    L12:
        return r0;
    }

    private static FeedAd parseManifest(String r8) {
        FeedAd r1 = new FeedAd();     // Catch: Exception -> L17
        String[] r82 = r8.split("\n");     // Catch: Exception -> L17
        int r2 = r82.length;     // Catch: Exception -> L17
        int r4 = 0;
    L4:
        if (r4 >= r2) goto L59;
        String r5 = r82[r4];     // Catch: Exception -> L17
        int r6 = r5.indexOf(61);     // Catch: Exception -> L17
        if (r6 < 0) goto L58;
        String r7 = r5.substring(0, r6).trim();     // Catch: Exception -> L17
        String r52 = r5.substring(r6 + 1).trim();     // Catch: Exception -> L17
        if (r52.isEmpty() == true) goto L58;
        switch(r7.hashCode()) {
            case -1677022541: goto L56;
            case -1377687758: goto L53;
            case -1097462182: goto L50;
            case -791592328: goto L47;
            case -318452137: goto L44;
            case -118282810: goto L41;
            case 3355: goto L38;
            case 116079: goto L35;
            case 3029410: goto L32;
            case 93494179: goto L29;
            case 94842723: goto L26;
            case 103772132: goto L23;
            case 110371416: goto L20;
            case 1437916763: goto L15;
            default: goto L58;
        };     // Catch: Exception -> L17
    L15:
        if (r7.equals("recommended") == false) goto L58;
        r1.recommended = Boolean.parseBoolean(r52);     // Catch: Exception -> L17
        goto L58
    L20:
        if (r7.equals("title") == false) goto L58;
        r1.title = r52;     // Catch: Exception -> L17
        goto L58
    L23:
        if (r7.equals("media") == false) goto L58;
        r1.mediaMessageId = parseInt(r52, 0);     // Catch: Exception -> L17
        goto L58
    L26:
        if (r7.equals("color") == false) goto L58;
        r1.colorId = parseInt(r52, -1);     // Catch: Exception -> L17
        goto L58
    L29:
        if (r7.equals("badge") == false) goto L58;
        r1.badge = parseMatch(r52);     // Catch: Exception -> L17
        goto L58
    L32:
        if (r7.equals("body") == false) goto L58;
        r1.bodyMessageId = parseInt(r52, 0);     // Catch: Exception -> L17
        goto L58
    L35:
        if (r7.equals("url") == false) goto L58;
        r1.url = r52;     // Catch: Exception -> L17
        goto L58
    L38:
        if (r7.equals("id") == false) goto L58;
        r1.id = r52;     // Catch: Exception -> L17
        goto L58
    L41:
        if (r7.equals("additional_info") == false) goto L58;
        r1.additionalInfo = r52;     // Catch: Exception -> L17
        goto L58
    L44:
        if (r7.equals("premium") == false) goto L58;
        r1.premium = parseMatch(r52);     // Catch: Exception -> L17
        goto L58
    L47:
        if (r7.equals("weight") == false) goto L58;
        r1.weight = Math.max(1, parseInt(r52, 1));     // Catch: Exception -> L17
        goto L58
    L50:
        if (r7.equals("locale") == false) goto L58;
        r1.locales = parseLocales(r52);     // Catch: Exception -> L17
        goto L58
    L53:
        if (r7.equals("button") == false) goto L58;
        r1.buttonText = r52;     // Catch: Exception -> L17
        goto L58
    L56:
        if (r7.equals("sponsor_info") == false) goto L58;
        r1.sponsorInfo = r52;     // Catch: Exception -> L17
    L58:
        r4 = r4 + 1;     // Catch: Exception -> L17
        goto L4
    L59:
        String r83 = r1.id;     // Catch: Exception -> L17
        if (r83 == null) goto L64;
        if (r83.isEmpty() == true) goto L64;
        return r1;
    L64:
        return null;
    L17:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    private static int parseMatch(String r1) {
        if (TextUtils.equals(r1, "true") == false) goto L5;
        return 1;
    L5:
        if (TextUtils.equals(r1, "has") == false) goto L8;
        return 1;
    L8:
        if (TextUtils.equals(r1, "false") == false) goto L10;
        return 2;
    L10:
        if (TextUtils.equals(r1, "none") == true) goto L18;
        return 0;
    L18:
        return 2;
    }
}
