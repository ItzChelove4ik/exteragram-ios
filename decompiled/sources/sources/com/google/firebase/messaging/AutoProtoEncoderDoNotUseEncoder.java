package com.google.firebase.messaging;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import com.google.firebase.encoders.proto.AtProtobuf;
import com.google.firebase.messaging.reporting.MessagingClientEvent;
import com.google.firebase.messaging.reporting.MessagingClientEventExtension;

/* loaded from: classes5.dex */
public final class AutoProtoEncoderDoNotUseEncoder implements Configurator {
    public static final Configurator CONFIG = null;

    public static final class MessagingClientEventEncoder implements ObjectEncoder<MessagingClientEvent> {
        private static final FieldDescriptor ANALYTICSLABEL_DESCRIPTOR = null;
        private static final FieldDescriptor BULKID_DESCRIPTOR = null;
        private static final FieldDescriptor CAMPAIGNID_DESCRIPTOR = null;
        private static final FieldDescriptor COLLAPSEKEY_DESCRIPTOR = null;
        private static final FieldDescriptor COMPOSERLABEL_DESCRIPTOR = null;
        private static final FieldDescriptor EVENT_DESCRIPTOR = null;
        static final MessagingClientEventEncoder INSTANCE = null;
        private static final FieldDescriptor INSTANCEID_DESCRIPTOR = null;
        private static final FieldDescriptor MESSAGEID_DESCRIPTOR = null;
        private static final FieldDescriptor MESSAGETYPE_DESCRIPTOR = null;
        private static final FieldDescriptor PACKAGENAME_DESCRIPTOR = null;
        private static final FieldDescriptor PRIORITY_DESCRIPTOR = null;
        private static final FieldDescriptor PROJECTNUMBER_DESCRIPTOR = null;
        private static final FieldDescriptor SDKPLATFORM_DESCRIPTOR = null;
        private static final FieldDescriptor TOPIC_DESCRIPTOR = null;
        private static final FieldDescriptor TTL_DESCRIPTOR = null;

        static {
            INSTANCE = new MessagingClientEventEncoder();
            PROJECTNUMBER_DESCRIPTOR = FieldDescriptor.builder("projectNumber").withProperty(AtProtobuf.builder().tag(1).build()).build();
            MESSAGEID_DESCRIPTOR = FieldDescriptor.builder("messageId").withProperty(AtProtobuf.builder().tag(2).build()).build();
            INSTANCEID_DESCRIPTOR = FieldDescriptor.builder("instanceId").withProperty(AtProtobuf.builder().tag(3).build()).build();
            MESSAGETYPE_DESCRIPTOR = FieldDescriptor.builder("messageType").withProperty(AtProtobuf.builder().tag(4).build()).build();
            SDKPLATFORM_DESCRIPTOR = FieldDescriptor.builder("sdkPlatform").withProperty(AtProtobuf.builder().tag(5).build()).build();
            PACKAGENAME_DESCRIPTOR = FieldDescriptor.builder("packageName").withProperty(AtProtobuf.builder().tag(6).build()).build();
            COLLAPSEKEY_DESCRIPTOR = FieldDescriptor.builder("collapseKey").withProperty(AtProtobuf.builder().tag(7).build()).build();
            PRIORITY_DESCRIPTOR = FieldDescriptor.builder(PluginsConstants.MenuItemProperties.PRIORITY).withProperty(AtProtobuf.builder().tag(8).build()).build();
            TTL_DESCRIPTOR = FieldDescriptor.builder("ttl").withProperty(AtProtobuf.builder().tag(9).build()).build();
            TOPIC_DESCRIPTOR = FieldDescriptor.builder("topic").withProperty(AtProtobuf.builder().tag(10).build()).build();
            BULKID_DESCRIPTOR = FieldDescriptor.builder("bulkId").withProperty(AtProtobuf.builder().tag(11).build()).build();
            EVENT_DESCRIPTOR = FieldDescriptor.builder("event").withProperty(AtProtobuf.builder().tag(12).build()).build();
            ANALYTICSLABEL_DESCRIPTOR = FieldDescriptor.builder("analyticsLabel").withProperty(AtProtobuf.builder().tag(13).build()).build();
            CAMPAIGNID_DESCRIPTOR = FieldDescriptor.builder("campaignId").withProperty(AtProtobuf.builder().tag(14).build()).build();
            COMPOSERLABEL_DESCRIPTOR = FieldDescriptor.builder("composerLabel").withProperty(AtProtobuf.builder().tag(15).build()).build();
        }

        private MessagingClientEventEncoder() {
        }

        public void encode(MessagingClientEvent r3, ObjectEncoderContext r4) {
            r4.add(PROJECTNUMBER_DESCRIPTOR, r3.getProjectNumber());
            r4.add(MESSAGEID_DESCRIPTOR, r3.getMessageId());
            r4.add(INSTANCEID_DESCRIPTOR, r3.getInstanceId());
            r4.add(MESSAGETYPE_DESCRIPTOR, r3.getMessageType());
            r4.add(SDKPLATFORM_DESCRIPTOR, r3.getSdkPlatform());
            r4.add(PACKAGENAME_DESCRIPTOR, r3.getPackageName());
            r4.add(COLLAPSEKEY_DESCRIPTOR, r3.getCollapseKey());
            r4.add(PRIORITY_DESCRIPTOR, r3.getPriority());
            r4.add(TTL_DESCRIPTOR, r3.getTtl());
            r4.add(TOPIC_DESCRIPTOR, r3.getTopic());
            r4.add(BULKID_DESCRIPTOR, r3.getBulkId());
            r4.add(EVENT_DESCRIPTOR, r3.getEvent());
            r4.add(ANALYTICSLABEL_DESCRIPTOR, r3.getAnalyticsLabel());
            r4.add(CAMPAIGNID_DESCRIPTOR, r3.getCampaignId());
            r4.add(COMPOSERLABEL_DESCRIPTOR, r3.getComposerLabel());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((MessagingClientEvent) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class MessagingClientEventExtensionEncoder implements ObjectEncoder<MessagingClientEventExtension> {
        static final MessagingClientEventExtensionEncoder INSTANCE = null;
        private static final FieldDescriptor MESSAGINGCLIENTEVENT_DESCRIPTOR = null;

        static {
            INSTANCE = new MessagingClientEventExtensionEncoder();
            MESSAGINGCLIENTEVENT_DESCRIPTOR = FieldDescriptor.builder("messagingClientEvent").withProperty(AtProtobuf.builder().tag(1).build()).build();
        }

        private MessagingClientEventExtensionEncoder() {
        }

        public void encode(MessagingClientEventExtension r1, ObjectEncoderContext r2) {
            r2.add(MESSAGINGCLIENTEVENT_DESCRIPTOR, r1.getMessagingClientEventInternal());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((MessagingClientEventExtension) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class ProtoEncoderDoNotUseEncoder implements ObjectEncoder<ProtoEncoderDoNotUse> {
        static final ProtoEncoderDoNotUseEncoder INSTANCE = null;
        private static final FieldDescriptor MESSAGINGCLIENTEVENTEXTENSION_DESCRIPTOR = null;

        static {
            INSTANCE = new ProtoEncoderDoNotUseEncoder();
            MESSAGINGCLIENTEVENTEXTENSION_DESCRIPTOR = FieldDescriptor.of("messagingClientEventExtension");
        }

        private ProtoEncoderDoNotUseEncoder() {
        }

        public void encode(ProtoEncoderDoNotUse r1, ObjectEncoderContext r2) {
            throw null;
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
            encode(null, (ObjectEncoderContext) r2);
        }
    }

    static {
        CONFIG = new AutoProtoEncoderDoNotUseEncoder();
    }

    private AutoProtoEncoderDoNotUseEncoder() {
    }

    @Override // com.google.firebase.encoders.config.Configurator
    public void configure(EncoderConfig<?> r2) {
        r2.registerEncoder(ProtoEncoderDoNotUse.class, ProtoEncoderDoNotUseEncoder.INSTANCE);
        r2.registerEncoder(MessagingClientEventExtension.class, MessagingClientEventExtensionEncoder.INSTANCE);
        r2.registerEncoder(MessagingClientEvent.class, MessagingClientEventEncoder.INSTANCE);
    }
}
