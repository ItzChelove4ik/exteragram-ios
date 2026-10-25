package com.exteragram.messenger.feed;

import com.android.tools.r8.RecordTag;
import com.exteragram.messenger.ai.network.Client$ImagePayload$$ExternalSyntheticRecord1;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public abstract class FeedRequestNormalizer {
    private static final Field[] EMPTY_FIELDS = null;
    private static final ClassMetadata EMPTY_METADATA = null;
    private static final ConcurrentHashMap<Class<?>, ClassMetadata> metadataCache = null;

    public static final class ClassMetadata extends RecordTag {
        private final Field channelField;
        private final Field invoiceField;
        private final Field[] messageIdFields;
        private final Field peerField;
        private final Field requestPeerField;

        private /* synthetic */ boolean $record$equals(Object r3) {
            if ((r3 instanceof ClassMetadata) == false) goto L16;
            ClassMetadata r32 = (ClassMetadata) r3;
            if (Objects.equals(this.requestPeerField, r32.requestPeerField) == true) goto L7;
            return false;
        L7:
            if (Objects.equals(this.peerField, r32.peerField) == true) goto L9;
            return false;
        L9:
            if (Objects.equals(this.channelField, r32.channelField) == true) goto L11;
            return false;
        L11:
            if (Objects.equals(this.invoiceField, r32.invoiceField) == true) goto L13;
            return false;
        L13:
            if (Objects.equals(this.messageIdFields, r32.messageIdFields) == false) goto L22;
            return true;
        L22:
            return false;
        L16:
            return false;
        }

        private /* synthetic */ Object[] $record$getFieldsAsObjects() {
            return new Object[]{this.requestPeerField, this.peerField, this.channelField, this.invoiceField, this.messageIdFields};
        }

        /* renamed from: -$$Nest$fgetchannelField, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Field m1417$$Nest$fgetchannelField(ClassMetadata r0) {
            return r0.channelField;
        }

        /* renamed from: -$$Nest$fgetinvoiceField, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Field m1418$$Nest$fgetinvoiceField(ClassMetadata r0) {
            return r0.invoiceField;
        }

        /* renamed from: -$$Nest$fgetmessageIdFields, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Field[] m1419$$Nest$fgetmessageIdFields(ClassMetadata r0) {
            return r0.messageIdFields;
        }

        /* renamed from: -$$Nest$fgetpeerField, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Field m1420$$Nest$fgetpeerField(ClassMetadata r0) {
            return r0.peerField;
        }

        /* renamed from: -$$Nest$fgetrequestPeerField, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Field m1421$$Nest$fgetrequestPeerField(ClassMetadata r0) {
            return r0.requestPeerField;
        }

        private ClassMetadata(Field r1, Field r2, Field r3, Field r4, Field[] r5) {
            this.requestPeerField = r1;
            this.peerField = r2;
            this.channelField = r3;
            this.invoiceField = r4;
            this.messageIdFields = r5;
        }

        public /* synthetic */ ClassMetadata(Field r1, Field r2, Field r3, Field r4, Field[] r5, FeedRequestNormalizerIA r6) {
            this(r1, r2, r3, r4, r5);
        }

        public final boolean equals(Object r1) {
            return $record$equals(r1);
        }

        public final int hashCode() {
            return FeedRequestNormalizer$ClassMetadata$$ExternalSyntheticRecord0.m(this.requestPeerField, this.peerField, this.channelField, this.invoiceField, this.messageIdFields);
        }

        public final String toString() {
            return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), ClassMetadata.class, "requestPeerField;peerField;channelField;invoiceField;messageIdFields");
        }
    }

    public static /* synthetic */ ClassMetadata $r8$lambda$3v1ZZjmWnHXiJEBcg16a6ZyslPw(Class r0) {
        return buildMetadata(r0);
    }

    static {
        Field[] r6 = new Field[0];
        EMPTY_FIELDS = r6;
        EMPTY_METADATA = new ClassMetadata(null, null, null, null, r6, null);
        metadataCache = new ConcurrentHashMap();
    }

    private static ClassMetadata buildMetadata(Class<?> r11) {
        Field[] r112 = r11.getFields();     // Catch: Exception -> L4
    L6:
        int r0 = r112.length;
        Field r2 = null;
        int r4 = 0;
        ArrayList r3 = null;
        Field r5 = null;
        Field r6 = null;
        Field r7 = null;
    L7:
        if (r4 >= r0) goto L31;
        Field r8 = r112[r4];
        String r9 = r8.getName();
        if ("from_peer".equals(r9) == false) goto L13;
        if (r2 != null) goto L13;
        r2 = r8;
    L25:
        if (isMessageIdField(r8) == false) goto L29;
        if (r3 != null) goto L28;
        r3 = new ArrayList();
    L28:
        r3.add(r8);
    L29:
        r4 = r4 + 1;
    L13:
        if ("peer".equals(r9) == false) goto L17;
        if (r5 != null) goto L17;
        r5 = r8;
    L17:
        if ("channel".equals(r9) == false) goto L21;
        if (r6 != null) goto L21;
        r6 = r8;
    L21:
        if ("invoice".equals(r9) == false) goto L25;
        if (r7 != null) goto L25;
        r7 = r8;
        goto L25
    L31:
        if (r2 == null) goto L33;
        Field r42 = r2;
    L34:
        if (r3 == null) goto L37;
        Field[] r02 = (Field[]) r3.toArray(new Field[0]);
    L39:
        return new ClassMetadata(r42, r5, r6, r7, r02, null);
    L37:
        r02 = EMPTY_FIELDS;
        goto L39
    L33:
        r42 = r5;
    L4:
        e = move-exception;
        reportFailure("fields of ".concat(r11.getName()), e);
        r112 = EMPTY_FIELDS;
        goto L6
    }

    private static long getChannelDialogId(Field r0, Object r1) {
        Object r02 = getFieldValue(r0, r1);
        if ((r02 instanceof TLRPC.InputChannel) == true) goto L5;
        return 0;
    L5:
        return getInputChannelDialogId((TLRPC.InputChannel) r02);
    }

    private static long getDialogId(Field r4, Object r5) {
        if (r4 != null) goto L12;
        return 0;
    L12:
        Object r52 = r4.get(r5);     // Catch: Exception -> L9
        if ((r52 instanceof TLRPC.InputPeer) == false) goto L11;
        return DialogObject.getPeerDialogId((TLRPC.InputPeer) r52);
    L11:
        return 0;
    L9:
        e = move-exception;
        reportFailure("peer field " + r4.getName(), e);
        goto L11
    }

    private static Object getFieldValue(Field r3, Object r4) {
        if (r3 != null) goto L10;
        return null;
    L10:
        return r3.get(r4);
    L7:
        e = move-exception;
        reportFailure("field " + r3.getName(), e);
        return null;
    }

    private static long getInputChannelDialogId(TLRPC.InputChannel r4) {
        if (r4 == null) goto L9;
        long r2 = r4.channel_id;
        if (r2 != 0) goto L8;
        return 0;
    L8:
        return -r2;
    L9:
        return 0;
    }

    private static ClassMetadata getMetadata(Object r2) {
        if (r2 != null) goto L6;
        return EMPTY_METADATA;
    L6:
        return metadataCache.computeIfAbsent(r2.getClass(), new FeedRequestNormalizer$$ExternalSyntheticLambda0());
    }

    private static boolean isMessageIdField(Field r2) {
        if (r2 != null) goto L5;
    L17:
        return false;
    L5:
        if (Modifier.isStatic(r2.getModifiers()) == true) goto L17;
        String r22 = r2.getName();
        if ("id".equals(r22) == false) goto L10;
        return true;
    L10:
        if ("msg_id".equals(r22) == false) goto L12;
        return true;
    L12:
        if (r22.endsWith("_msg_id") == true) goto L19;
        return false;
    L19:
        return true;
    }

    private static long mergeResolvedDialogIds(long r3, long r5) {
        if (r3 != 0) goto L6;
        return r5;
    L6:
        if (r5 != 0) goto L9;
    L10:
        return r3;
    L9:
        if (r3 == r5) goto L10;
        return 0;
    }

    public static TLObject normalize(int r3, TLObject r4) {
        if (r4 != null) goto L4;
        return r4;
    L4:
        FeedController r0 = FeedController.peekInstance(r3);
        if (r0 != null) goto L7;
    L18:
        return r4;
    L7:
        if (r0.hasNoSyntheticIds() == true) goto L18;
        if (r4.getClass().getName().startsWith("org.telegram.tgnet.") == false) goto L18;
        ClassMetadata r1 = getMetadata(r4);
        if (ClassMetadata.m1419$$Nest$fgetmessageIdFields(r1).length == 0) goto L15;
    L17:
        normalizeMessageIds(r3, r0, r4, r1);
        normalizeInvoice(r3, r0, getFieldValue(ClassMetadata.m1418$$Nest$fgetinvoiceField(r1), r4));
        goto L18
    L15:
        if (ClassMetadata.m1418$$Nest$fgetinvoiceField(r1) != null) goto L17;
        goto L17
    }

    private static void normalizeInvoice(int r1, FeedController r2, Object r3) {
        if ((r3 instanceof TLRPC.TL_inputInvoiceMessage) == false) goto L6;
        normalizeMessageIds(r1, r2, r3);
        return;
    }

    private static long normalizeMessageIdField(FeedController r9, Object r10, Field r11) {
        long r0 = 0;
        Object r2 = r11.get(r10);     // Catch: Exception -> L9
        if ((r2 instanceof Integer) == false) goto L12;
        Integer r22 = (Integer) r2;     // Catch: Exception -> L9
        long r3 = r9.resolveRealDialogId(r22.intValue());     // Catch: Exception -> L9
        if (r3 == 0) goto L27;
        r11.setInt(r10, r9.resolveRealMessageId(r3, r22.intValue()));     // Catch: Exception -> L9
        return r3;
    L27:
        return 0;
    L12:
        if ((r2 instanceof ArrayList) == false) goto L27;
        ArrayList r23 = (ArrayList) r2;     // Catch: Exception -> L9
        int r102 = 0;
        long r32 = 0;
    L30:
        if (r102 >= r23.size()) goto L26;
        Object r5 = r23.get(r102);     // Catch: Exception -> L23
        if ((r5 instanceof Integer) == false) goto L25;
        Integer r52 = (Integer) r5;     // Catch: Exception -> L23
        long r6 = r9.resolveRealDialogId(r52.intValue());     // Catch: Exception -> L23
        if (r6 == 0) goto L25;
        setListInteger(r23, r102, r9.resolveRealMessageId(r6, r52.intValue()));     // Catch: Exception -> L23
        r32 = mergeResolvedDialogIds(r32, r6);     // Catch: Exception -> L23
    L25:
        r102 = r102 + 1;
        goto L30
    L26:
        return r32;
    L23:
        e = e;
        r0 = r32;
    L28:
        reportFailure("message id field " + r11.getName(), e);
        return r0;
    L9:
        e = e;
        goto L28
    }

    private static long normalizeMessageIdFields(FeedController r6, Object r7, ClassMetadata r8) {
        long r0 = 0;
        if (r7 != null) goto L5;
        return 0;
    L5:
        Field[] r82 = ClassMetadata.m1419$$Nest$fgetmessageIdFields(r8);
        int r2 = r82.length;
        int r3 = 0;
    L6:
        if (r3 >= r2) goto L8;
        r0 = mergeResolvedDialogIds(r0, normalizeMessageIdField(r6, r7, r82[r3]));
        r3 = r3 + 1;
        goto L6
    L8:
        return r0;
    }

    private static void normalizeMessageIds(int r1, FeedController r2, Object r3) {
        normalizeMessageIds(r1, r2, r3, getMetadata(r3));
    }

    private static void normalizeMessageIds(int r7, FeedController r8, Object r9, ClassMetadata r10) {
        Field r0 = ClassMetadata.m1421$$Nest$fgetrequestPeerField(r10);
        long r1 = getDialogId(r0, r9);
        if (r1 != 0) goto L6;
        r1 = getDialogId(ClassMetadata.m1420$$Nest$fgetpeerField(r10), r9);
    L6:
        if (r1 != 0) goto L8;
        r1 = getChannelDialogId(ClassMetadata.m1417$$Nest$fgetchannelField(r10), r9);
    L8:
        long r5 = normalizeMessageIdFields(r8, r9, r10);
        if (r5 != 0) goto L11;
        return;
    L11:
        if (r5 == r1) goto L20;
        if (r0 == null) goto L16;
        setInputPeer(r7, r0, r9, r5);
        return;
    L16:
        if (ClassMetadata.m1417$$Nest$fgetchannelField(r10) == null) goto L21;
        setInputChannel(r7, ClassMetadata.m1417$$Nest$fgetchannelField(r10), r9, r5);
        return;
    L21:
        return;
    }

    private static void reportFailure(String r2, Exception r3) {
        FileLog.e("FeedRequestNormalizer failed to normalize " + r2, r3);
    }

    private static void setInputChannel(int r2, Field r3, Object r4, long r5) {
        if (r2 >= 0) goto L4;
        return;
    L4:
        if (r5 >= 0) goto L17;
        TLRPC.InputChannel r22 = MessagesController.getInstance(r2).getInputChannel(-r5);     // Catch: Exception -> L10
        if (r22 == null) goto L16;
        r3.set(r4, r22);     // Catch: Exception -> L10
        return;
    L16:
        return;
    L10:
        e = move-exception;
        reportFailure("channel field " + r3.getName(), e);
        return;
    }

    private static void setInputPeer(int r0, Field r1, Object r2, long r3) {
        if (r0 < 0) goto L15;
        TLRPC.InputPeer r02 = MessagesController.getInstance(r0).getInputPeer(r3);     // Catch: Exception -> L8
        if (r02 == null) goto L10;
        r1.set(r2, r02);     // Catch: Exception -> L8
        return;
    L10:
        return;
    L8:
        e = move-exception;
        reportFailure("peer field " + r1.getName(), e);
        return;
    }

    private static void setListInteger(ArrayList r0, int r1, int r2) {
        r0.set(r1, Integer.valueOf(r2));
    }
}
