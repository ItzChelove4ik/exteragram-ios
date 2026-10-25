package com.exteragram.messenger.export.api;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public class ApiWrap$DialogInfo {
    public int colorIndex;
    public TLRPC.InputPeer input;
    public boolean isLeftChannel;
    public boolean isMonoforum;
    public String lastName;
    public ArrayList<Integer> messagesCountPerSplit;
    public TLRPC.InputPeer migratedFromInput;
    public long migratedToChannelId;
    public TLRPC.InputPeer monoforumBroadcastInput;
    public String name;
    public boolean onlyMyMessages;
    public long peerId;
    public String relativePath;
    public ArrayList<Integer> splits;
    public int topMessageDate;
    public int topMessageId;
    public Type type;

    public enum Type extends Enum<Type> {
        private static final /* synthetic */ Type[] $VALUES = null;
        public static final Type Bot = null;
        public static final Type Personal = null;
        public static final Type PrivateChannel = null;
        public static final Type PrivateGroup = null;
        public static final Type PrivateSupergroup = null;
        public static final Type PublicChannel = null;
        public static final Type PublicSupergroup = null;
        public static final Type Replies = null;
        public static final Type Self = null;
        public static final Type Unknown = null;
        public static final Type VerifyCodes = null;

        private static /* synthetic */ Type[] $values() {
            return new Type[]{Unknown, Self, Replies, VerifyCodes, Personal, Bot, PrivateGroup, PrivateSupergroup, PublicSupergroup, PrivateChannel, PublicChannel};
        }

        static {
            Unknown = new Type("Unknown", 0);
            Self = new Type("Self", 1);
            Replies = new Type("Replies", 2);
            VerifyCodes = new Type("VerifyCodes", 3);
            Personal = new Type("Personal", 4);
            Bot = new Type("Bot", 5);
            PrivateGroup = new Type("PrivateGroup", 6);
            PrivateSupergroup = new Type("PrivateSupergroup", 7);
            PublicSupergroup = new Type("PublicSupergroup", 8);
            PrivateChannel = new Type("PrivateChannel", 9);
            PublicChannel = new Type("PublicChannel", 10);
            $VALUES = $values();
        }

        Type(String r1, int r2) {
        }

        public static Type valueOf(String r1) {
            return (Type) Enum.valueOf(Type.class, r1);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }
    }

    public ApiWrap$DialogInfo() {
        this.type = Type.Unknown;
        this.input = new TLRPC.TL_inputPeerEmpty();
        this.topMessageId = 0;
        this.topMessageDate = -1337;
        this.peerId = 0;
        this.colorIndex = 0;
        this.migratedFromInput = new TLRPC.TL_inputPeerEmpty();
        this.migratedToChannelId = 0;
        this.monoforumBroadcastInput = new TLRPC.TL_inputPeerEmpty();
        this.splits = new ArrayList();
        this.onlyMyMessages = false;
        this.isLeftChannel = false;
        this.isMonoforum = false;
        this.messagesCountPerSplit = new ArrayList();
    }
}
