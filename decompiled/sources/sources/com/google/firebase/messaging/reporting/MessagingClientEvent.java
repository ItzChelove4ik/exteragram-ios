package com.google.firebase.messaging.reporting;

import com.google.firebase.encoders.proto.ProtoEnum;
import com.google.firebase.encoders.proto.Protobuf;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class MessagingClientEvent {
    private static final MessagingClientEvent DEFAULT_INSTANCE = null;
    private final String analytics_label_;
    private final long bulk_id_;
    private final long campaign_id_;
    private final String collapse_key_;
    private final String composer_label_;
    private final Event event_;
    private final String instance_id_;
    private final String message_id_;
    private final MessageType message_type_;
    private final String package_name_;
    private final int priority_;
    private final long project_number_;
    private final SDKPlatform sdk_platform_;
    private final String topic_;
    private final int ttl_;

    public static final class Builder {
        private String analytics_label_;
        private long bulk_id_;
        private long campaign_id_;
        private String collapse_key_;
        private String composer_label_;
        private Event event_;
        private String instance_id_;
        private String message_id_;
        private MessageType message_type_;
        private String package_name_;
        private int priority_;
        private long project_number_;
        private SDKPlatform sdk_platform_;
        private String topic_;
        private int ttl_;

        public Builder() {
            this.project_number_ = 0;
            this.message_id_ = _UrlKt.FRAGMENT_ENCODE_SET;
            this.instance_id_ = _UrlKt.FRAGMENT_ENCODE_SET;
            this.message_type_ = MessageType.UNKNOWN;
            this.sdk_platform_ = SDKPlatform.UNKNOWN_OS;
            this.package_name_ = _UrlKt.FRAGMENT_ENCODE_SET;
            this.collapse_key_ = _UrlKt.FRAGMENT_ENCODE_SET;
            this.priority_ = 0;
            this.ttl_ = 0;
            this.topic_ = _UrlKt.FRAGMENT_ENCODE_SET;
            this.bulk_id_ = 0;
            this.event_ = Event.UNKNOWN_EVENT;
            this.analytics_label_ = _UrlKt.FRAGMENT_ENCODE_SET;
            this.campaign_id_ = 0;
            this.composer_label_ = _UrlKt.FRAGMENT_ENCODE_SET;
        }

        public MessagingClientEvent build() {
            return new MessagingClientEvent(this.project_number_, this.message_id_, this.instance_id_, this.message_type_, this.sdk_platform_, this.package_name_, this.collapse_key_, this.priority_, this.ttl_, this.topic_, this.bulk_id_, this.event_, this.analytics_label_, this.campaign_id_, this.composer_label_);
        }

        public Builder setAnalyticsLabel(String r1) {
            this.analytics_label_ = r1;
            return this;
        }

        public Builder setCollapseKey(String r1) {
            this.collapse_key_ = r1;
            return this;
        }

        public Builder setComposerLabel(String r1) {
            this.composer_label_ = r1;
            return this;
        }

        public Builder setEvent(Event r1) {
            this.event_ = r1;
            return this;
        }

        public Builder setInstanceId(String r1) {
            this.instance_id_ = r1;
            return this;
        }

        public Builder setMessageId(String r1) {
            this.message_id_ = r1;
            return this;
        }

        public Builder setMessageType(MessageType r1) {
            this.message_type_ = r1;
            return this;
        }

        public Builder setPackageName(String r1) {
            this.package_name_ = r1;
            return this;
        }

        public Builder setPriority(int r1) {
            this.priority_ = r1;
            return this;
        }

        public Builder setProjectNumber(long r1) {
            this.project_number_ = r1;
            return this;
        }

        public Builder setSdkPlatform(SDKPlatform r1) {
            this.sdk_platform_ = r1;
            return this;
        }

        public Builder setTopic(String r1) {
            this.topic_ = r1;
            return this;
        }

        public Builder setTtl(int r1) {
            this.ttl_ = r1;
            return this;
        }
    }

    public enum Event extends Enum<Event> implements ProtoEnum {
        private static final /* synthetic */ Event[] $VALUES = null;
        public static final Event MESSAGE_DELIVERED = null;
        public static final Event MESSAGE_OPEN = null;
        public static final Event UNKNOWN_EVENT = null;
        private final int number_;

        private static /* synthetic */ Event[] $values() {
            return new Event[]{UNKNOWN_EVENT, MESSAGE_DELIVERED, MESSAGE_OPEN};
        }

        static {
            UNKNOWN_EVENT = new Event("UNKNOWN_EVENT", 0, 0);
            MESSAGE_DELIVERED = new Event("MESSAGE_DELIVERED", 1, 1);
            MESSAGE_OPEN = new Event("MESSAGE_OPEN", 2, 2);
            $VALUES = $values();
        }

        Event(String r1, int r2, int r3) {
            this.number_ = r3;
        }

        public static Event valueOf(String r1) {
            return (Event) Enum.valueOf(Event.class, r1);
        }

        public static Event[] values() {
            return (Event[]) $VALUES.clone();
        }

        @Override // com.google.firebase.encoders.proto.ProtoEnum
        public int getNumber() {
            return this.number_;
        }
    }

    public enum MessageType extends Enum<MessageType> implements ProtoEnum {
        private static final /* synthetic */ MessageType[] $VALUES = null;
        public static final MessageType DATA_MESSAGE = null;
        public static final MessageType DISPLAY_NOTIFICATION = null;
        public static final MessageType TOPIC = null;
        public static final MessageType UNKNOWN = null;
        private final int number_;

        private static /* synthetic */ MessageType[] $values() {
            return new MessageType[]{UNKNOWN, DATA_MESSAGE, TOPIC, DISPLAY_NOTIFICATION};
        }

        static {
            UNKNOWN = new MessageType("UNKNOWN", 0, 0);
            DATA_MESSAGE = new MessageType("DATA_MESSAGE", 1, 1);
            TOPIC = new MessageType("TOPIC", 2, 2);
            DISPLAY_NOTIFICATION = new MessageType("DISPLAY_NOTIFICATION", 3, 3);
            $VALUES = $values();
        }

        MessageType(String r1, int r2, int r3) {
            this.number_ = r3;
        }

        public static MessageType valueOf(String r1) {
            return (MessageType) Enum.valueOf(MessageType.class, r1);
        }

        public static MessageType[] values() {
            return (MessageType[]) $VALUES.clone();
        }

        @Override // com.google.firebase.encoders.proto.ProtoEnum
        public int getNumber() {
            return this.number_;
        }
    }

    public enum SDKPlatform extends Enum<SDKPlatform> implements ProtoEnum {
        private static final /* synthetic */ SDKPlatform[] $VALUES = null;
        public static final SDKPlatform ANDROID = null;
        public static final SDKPlatform IOS = null;
        public static final SDKPlatform UNKNOWN_OS = null;
        public static final SDKPlatform WEB = null;
        private final int number_;

        private static /* synthetic */ SDKPlatform[] $values() {
            return new SDKPlatform[]{UNKNOWN_OS, ANDROID, IOS, WEB};
        }

        static {
            UNKNOWN_OS = new SDKPlatform("UNKNOWN_OS", 0, 0);
            ANDROID = new SDKPlatform("ANDROID", 1, 1);
            IOS = new SDKPlatform("IOS", 2, 2);
            WEB = new SDKPlatform("WEB", 3, 3);
            $VALUES = $values();
        }

        SDKPlatform(String r1, int r2, int r3) {
            this.number_ = r3;
        }

        public static SDKPlatform valueOf(String r1) {
            return (SDKPlatform) Enum.valueOf(SDKPlatform.class, r1);
        }

        public static SDKPlatform[] values() {
            return (SDKPlatform[]) $VALUES.clone();
        }

        @Override // com.google.firebase.encoders.proto.ProtoEnum
        public int getNumber() {
            return this.number_;
        }
    }

    static {
        DEFAULT_INSTANCE = new Builder().build();
    }

    public MessagingClientEvent(long r1, String r3, String r4, MessageType r5, SDKPlatform r6, String r7, String r8, int r9, int r10, String r11, long r12, Event r14, String r15, long r16, String r18) {
        this.project_number_ = r1;
        this.message_id_ = r3;
        this.instance_id_ = r4;
        this.message_type_ = r5;
        this.sdk_platform_ = r6;
        this.package_name_ = r7;
        this.collapse_key_ = r8;
        this.priority_ = r9;
        this.ttl_ = r10;
        this.topic_ = r11;
        this.bulk_id_ = r12;
        this.event_ = r14;
        this.analytics_label_ = r15;
        this.campaign_id_ = r16;
        this.composer_label_ = r18;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Protobuf(tag = 13)
    public String getAnalyticsLabel() {
        return this.analytics_label_;
    }

    @Protobuf(tag = 11)
    public long getBulkId() {
        return this.bulk_id_;
    }

    @Protobuf(tag = 14)
    public long getCampaignId() {
        return this.campaign_id_;
    }

    @Protobuf(tag = 7)
    public String getCollapseKey() {
        return this.collapse_key_;
    }

    @Protobuf(tag = 15)
    public String getComposerLabel() {
        return this.composer_label_;
    }

    @Protobuf(tag = 12)
    public Event getEvent() {
        return this.event_;
    }

    @Protobuf(tag = 3)
    public String getInstanceId() {
        return this.instance_id_;
    }

    @Protobuf(tag = 2)
    public String getMessageId() {
        return this.message_id_;
    }

    @Protobuf(tag = 4)
    public MessageType getMessageType() {
        return this.message_type_;
    }

    @Protobuf(tag = 6)
    public String getPackageName() {
        return this.package_name_;
    }

    @Protobuf(tag = 8)
    public int getPriority() {
        return this.priority_;
    }

    @Protobuf(tag = 1)
    public long getProjectNumber() {
        return this.project_number_;
    }

    @Protobuf(tag = 5)
    public SDKPlatform getSdkPlatform() {
        return this.sdk_platform_;
    }

    @Protobuf(tag = 10)
    public String getTopic() {
        return this.topic_;
    }

    @Protobuf(tag = 9)
    public int getTtl() {
        return this.ttl_;
    }
}
