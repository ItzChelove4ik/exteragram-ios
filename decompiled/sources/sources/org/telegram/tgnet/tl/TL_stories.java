package org.telegram.tgnet.tl;

import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.MessagesStorage$$ExternalSyntheticLambda117;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLMethod;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1;
import org.telegram.tgnet.TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1;
import org.telegram.tgnet.TLRPC$TL_messageReactions$$ExternalSyntheticLambda0;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.Vector$$ExternalSyntheticLambda3;
import org.telegram.tgnet.tl.TL_stats;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;

/* loaded from: classes.dex */
public class TL_stories {

    /* loaded from: classes6.dex */
    public static class Albums extends TLObject {
        public ArrayList<TL_storyAlbum> albums;
        public long hash;

        public Albums() {
        }

        public static Albums TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-1013417414)) goto L5;
            TLObject r0 = new TL_albums();
        L10:
            return (Albums) TLObject.TLdeserialize(Albums.class, r0, r2, r3, r4);
        L5:
            if (r3 == 1448008427) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new TL_albumsNotModified();
            goto L10
        }
    }

    /* loaded from: classes6.dex */
    public static class Boost extends TLObject {
        public static final long NO_USER_ID = -1;
        public int date;
        public int expires;
        public int flags;
        public boolean gift;
        public boolean giveaway;
        public int giveaway_msg_id;
        public String id;
        public int multiplier;
        public long stars;
        public boolean unclaimed;
        public String used_gift_slug;
        public long user_id;

        public Boost() {
            this.user_id = -1;
        }

        public static Boost TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != 706514033) goto L5;
            TLObject r0 = new TL_boost_layer186();
        L10:
            return (Boost) TLObject.TLdeserialize(Boost.class, r0, r2, r3, r4);
        L5:
            if (r3 == 1262359766) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new TL_boost();
            goto L10
        }
    }

    public static class MediaArea extends TLObject {
        public MediaAreaCoordinates coordinates;
        public boolean dark;
        public int flags;
        public boolean flipped;
        public TLRPC.Reaction reaction;

        public MediaArea() {
        }

        public static MediaArea TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            switch(r3) {
                case -2057362882: goto L15;
                case -1300094593: goto L14;
                case -1098720356: goto L13;
                case -891992787: goto L12;
                case -544523486: goto L11;
                case 340088945: goto L10;
                case 577893055: goto L9;
                case 926421125: goto L8;
                case 1132918857: goto L7;
                case 1235637404: goto L6;
                case 1468491885: goto L5;
                case 1996756655: goto L4;
                default: goto L3;
            };
        L3:
            TLObject r0 = null;
        L17:
            return (MediaArea) TLObject.TLdeserialize(MediaArea.class, r0, r2, r3, r4);
        L4:
            r0 = new TL_mediaAreaChannelPost();
            goto L17
        L5:
            r0 = new TL_mediaAreaStarGift();
            goto L17
        L6:
            r0 = new TL_mediaAreaWeather();
            goto L17
        L7:
            r0 = new TL_mediaAreaWeatherOld();
            goto L17
        L8:
            r0 = new TL_mediaAreaUrl();
            goto L17
        L9:
            r0 = new TL_inputMediaAreaChannelPost();
            goto L17
        L10:
            r0 = new TL_mediaAreaSuggestedReaction();
            goto L17
        L11:
            r0 = new TL_mediaAreaGeoPoint_layer181();
            goto L17
        L12:
            r0 = new TL_mediaAreaGeoPoint();
            goto L17
        L13:
            r0 = new TL_mediaAreaVenue();
            goto L17
        L14:
            r0 = new TL_inputMediaAreaVenue();
            goto L17
        L15:
            r0 = new TL_mediaAreaWeather2();
            goto L17
        }
    }

    /* loaded from: classes6.dex */
    public static class MediaAreaCoordinates extends TLObject {
        public int flags;
        public double h;
        public double radius;
        public double rotation;
        public double w;
        public double x;
        public double y;

        public MediaAreaCoordinates() {
        }

        public static MediaAreaCoordinates TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-808853502)) goto L5;
            TLObject r0 = new TL_mediaAreaCoordinates();
        L10:
            return (MediaAreaCoordinates) TLObject.TLdeserialize(MediaAreaCoordinates.class, r0, r2, r3, r4);
        L5:
            if (r3 == 64088654) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new TL_mediaAreaCoordinates_layer181();
            goto L10
        }
    }

    public static abstract class PeerStories extends TLObject {
        public boolean checkedExpired;
        public int flags;
        public int max_read_id;
        public TLRPC.Peer peer;
        public ArrayList<StoryItem> stories;

        public PeerStories() {
            this.stories = new ArrayList();
        }

        public static PeerStories TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-2045664768)) goto L5;
            TLObject r0 = new TL_peerStories_layer162();
        L10:
            return (PeerStories) TLObject.TLdeserialize(PeerStories.class, r0, r2, r3, r4);
        L5:
            if (r3 == (-1707742823)) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new TL_peerStories();
            goto L10
        }
    }

    /* loaded from: classes6.dex */
    public static class PrepaidGiveaway extends TLObject {
        public int boosts;
        public int date;
        public long id;
        public int quantity;

        public PrepaidGiveaway() {
        }

        public static PrepaidGiveaway TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-1700956192)) goto L5;
            TLObject r0 = new TL_prepaidStarsGiveaway();
        L10:
            return (PrepaidGiveaway) TLObject.TLdeserialize(PrepaidGiveaway.class, r0, r2, r3, r4);
        L5:
            if (r3 == (-1303143084)) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new TL_prepaidGiveaway();
            goto L10
        }
    }

    public static class StoryFwdHeader extends TLObject {
        public int flags;
        public TLRPC.Peer from;
        public String from_name;
        public boolean modified;
        public int story_id;

        public StoryFwdHeader() {
        }

        public static StoryFwdHeader TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == (-1205411504)) goto L5;
            TL_storyFwdHeader r0 = null;
        L7:
            return (StoryFwdHeader) TLObject.TLdeserialize(StoryFwdHeader.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_storyFwdHeader();
            goto L7
        }
    }

    public static abstract class StoryItem extends TLObject {
        public ArrayList<Integer> albums;
        public String attachPath;
        public String caption;
        public boolean close_friends;
        public boolean contacts;
        public int date;
        public String detectedLng;
        public long dialogId;
        public boolean edited;
        public ArrayList<TLRPC.MessageEntity> entities;
        public int expire_date;
        public int fileReference;
        public String firstFramePath;
        public int flags;
        public TLRPC.Peer from_id;
        public StoryFwdHeader fwd_from;
        public int id;
        public boolean isPublic;
        public boolean justUploaded;
        public long lastUpdateTime;
        public TLRPC.MessageMedia media;
        public ArrayList<MediaArea> media_areas;
        public int messageId;
        public int messageType;
        public boolean min;
        public TLRPC.Document music;
        public boolean noforwards;
        public boolean out;
        public StoryPrivacyBottomSheet.StoryPrivacy parsedPrivacy;
        public boolean pinned;
        public ArrayList<TLRPC.PrivacyRule> privacy;
        public boolean selected_contacts;
        public TLRPC.Reaction sent_reaction;
        public boolean translated;
        public String translatedLng;
        public TLRPC.TL_textWithEntities translatedText;
        public StoryViews views;

        public StoryItem() {
            this.entities = new ArrayList();
            this.media_areas = new ArrayList();
            this.privacy = new ArrayList();
        }

        public static StoryItem TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            switch(r3) {
                case -1352440415: goto L11;
                case -302947087: goto L10;
                case -5388013: goto L9;
                case 379894076: goto L8;
                case 1153718222: goto L7;
                case 1374088783: goto L6;
                case 1445635639: goto L5;
                case 2041735716: goto L4;
                default: goto L3;
            };
        L3:
            TLObject r0 = null;
        L13:
            return (StoryItem) TLObject.TLdeserialize(StoryItem.class, r0, r2, r3, r4);
        L4:
            r0 = new TL_storyItem_layer210();
            goto L13
        L5:
            r0 = new TL_storyItem_layer160();
            goto L13
        L6:
            r0 = new TL_storyItemDeleted();
            goto L13
        L7:
            r0 = new TL_storyItem_layer166();
            goto L13
        L8:
            r0 = new TL_storyItem();
            goto L13
        L9:
            r0 = new TL_storyItemSkipped();
            goto L13
        L10:
            r0 = new TL_storyItem_layer223();
            goto L13
        L11:
            r0 = new TL_storyItem_layer174();
            goto L13
        }
    }

    /* loaded from: classes6.dex */
    public static class StoryReaction extends TLObject {
        public TLRPC.Message message;
        public TLRPC.Peer peer_id;
        public StoryItem story;

        public StoryReaction() {
        }

        public static StoryReaction TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-1146411453)) goto L5;
            TLObject r0 = new TL_storyReactionPublicForward();
        L13:
            return (StoryReaction) TLObject.TLdeserialize(StoryReaction.class, r0, r2, r3, r4);
        L5:
            if (r3 != (-808644845)) goto L7;
            r0 = new TL_storyReactionPublicRepost();
            goto L13
        L7:
            if (r3 == 1620104917) goto L9;
            r0 = null;
            goto L13
        L9:
            r0 = new TL_storyReaction();
            goto L13
        }
    }

    /* loaded from: classes6.dex */
    public static class StoryView extends TLObject {
        public boolean blocked;
        public boolean blocked_my_stories_from;
        public int date;
        public int flags;
        public TLRPC.Message message;
        public TLRPC.Peer peer_id;
        public TLRPC.Reaction reaction;
        public StoryItem story;
        public long user_id;

        public StoryView() {
        }

        public static StoryView TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-1870436597)) goto L5;
            TLObject r0 = new TL_storyViewPublicForward();
        L13:
            return (StoryView) TLObject.TLdeserialize(StoryView.class, r0, r2, r3, r4);
        L5:
            if (r3 != (-1329730875)) goto L7;
            r0 = new TL_storyView();
            goto L13
        L7:
            if (r3 == (-1116418231)) goto L9;
            r0 = null;
            goto L13
        L9:
            r0 = new TL_storyViewPublicRepost();
            goto L13
        }
    }

    /* loaded from: classes6.dex */
    public static abstract class StoryViews extends TLObject {
        public int flags;
        public int forwards_count;
        public boolean has_viewers;
        public ArrayList<TLRPC.ReactionCount> reactions;
        public int reactions_count;
        public ArrayList<Long> recent_viewers;
        public int views_count;

        public StoryViews() {
            this.recent_viewers = new ArrayList();
            this.reactions = new ArrayList();
        }

        public static StoryViews TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-1923523370)) goto L5;
            TLObject r0 = new TL_storyViews();
        L13:
            return (StoryViews) TLObject.TLdeserialize(StoryViews.class, r0, r2, r3, r4);
        L5:
            if (r3 != (-968094825)) goto L7;
            r0 = new TL_storyViews_layer161();
            goto L13
        L7:
            if (r3 == (-748199729)) goto L9;
            r0 = null;
            goto L13
        L9:
            r0 = new TL_storyViews_layer160();
            goto L13
        }
    }

    /* loaded from: classes6.dex */
    public static class StoryViewsList extends TLObject {
        public ArrayList<TLRPC.Chat> chats;
        public int count;
        public int flags;
        public int forwards_count;
        public String next_offset;
        public int reactions_count;
        public ArrayList<TLRPC.User> users;
        public ArrayList<StoryView> views;
        public int views_count;

        public StoryViewsList() {
            this.views = new ArrayList();
            this.chats = new ArrayList();
            this.users = new ArrayList();
            this.next_offset = _UrlKt.FRAGMENT_ENCODE_SET;
        }

        public static StoryViewsList TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != 1189722604) goto L5;
            TLObject r0 = new TL_storyViewsList_layer167();
        L10:
            return (StoryViewsList) TLObject.TLdeserialize(StoryViewsList.class, r0, r2, r3, r4);
        L5:
            if (r3 == 1507299269) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new TL_storyViewsList();
            goto L10
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_albums extends Albums {
        public static final int constructor = -1013417414;

        public TL_albums() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.hash = r3.readInt64(r4);
            this.albums = Vector.deserialize(r3, new TL_stories$TL_albums$$ExternalSyntheticLambda0(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.hash);
            Vector.serialize(r3, this.albums);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_albumsNotModified extends Albums {
        public static final int constructor = 1448008427;

        public TL_albumsNotModified() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_boost extends Boost {
        public static final int constructor = 1262359766;

        public TL_boost() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r5, boolean r6) {
            int r0 = r5.readInt32(r6);
            this.flags = r0;
            this.gift = TLObject.hasFlag(r0, 2);
            this.giveaway = TLObject.hasFlag(this.flags, 4);
            this.unclaimed = TLObject.hasFlag(this.flags, 8);
            this.id = r5.readString(r6);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.user_id = r5.readInt64(r6);
        L6:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L8;
            this.giveaway_msg_id = r5.readInt32(r6);
        L8:
            this.date = r5.readInt32(r6);
            this.expires = r5.readInt32(r6);
            if (TLObject.hasFlag(this.flags, 16) == false) goto L12;
            this.used_gift_slug = r5.readString(r6);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            this.multiplier = r5.readInt32(r6);
        L15:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L18;
            this.stars = r5.readInt64(r6);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r5) {
            r5.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.gift);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 4, this.giveaway);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 8, this.unclaimed);
            this.flags = r03;
            r5.writeInt32(r03);
            r5.writeString(this.id);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r5.writeInt64(this.user_id);
        L6:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L8;
            r5.writeInt32(this.giveaway_msg_id);
        L8:
            r5.writeInt32(this.date);
            r5.writeInt32(this.expires);
            if (TLObject.hasFlag(this.flags, 16) == false) goto L12;
            r5.writeString(this.used_gift_slug);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            r5.writeInt32(this.multiplier);
        L15:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L18;
            r5.writeInt64(this.stars);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_boost_layer186 extends TL_boost {
        public static final int constructor = 706514033;

        public TL_boost_layer186() {
        }

        @Override // org.telegram.tgnet.tl.TL_stories.TL_boost, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r5, boolean r6) {
            int r0 = r5.readInt32(r6);
            this.flags = r0;
            this.gift = TLObject.hasFlag(r0, 2);
            this.giveaway = TLObject.hasFlag(this.flags, 4);
            this.unclaimed = TLObject.hasFlag(this.flags, 8);
            this.id = r5.readString(r6);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.user_id = r5.readInt64(r6);
        L6:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L8;
            this.giveaway_msg_id = r5.readInt32(r6);
        L8:
            this.date = r5.readInt32(r6);
            this.expires = r5.readInt32(r6);
            if (TLObject.hasFlag(this.flags, 16) == false) goto L12;
            this.used_gift_slug = r5.readString(r6);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            this.multiplier = r5.readInt32(r6);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stories.TL_boost, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r5) {
            r5.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.gift);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 4, this.giveaway);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 8, this.unclaimed);
            this.flags = r03;
            r5.writeInt32(r03);
            r5.writeString(this.id);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r5.writeInt64(this.user_id);
        L6:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L8;
            r5.writeInt32(this.giveaway_msg_id);
        L8:
            r5.writeInt32(this.date);
            r5.writeInt32(this.expires);
            if (TLObject.hasFlag(this.flags, 16) == false) goto L12;
            r5.writeString(this.used_gift_slug);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            r5.writeInt32(this.multiplier);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_createAlbum extends TLMethod<TL_storyAlbum> {
        public static final int constructor = -1553754395;
        public TLRPC.InputPeer peer;
        public ArrayList<Integer> stories;
        public String title;

        public TL_createAlbum() {
            this.stories = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TL_storyAlbum deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TL_storyAlbum.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeString(this.title);
            Vector.serializeInt(r2, this.stories);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_deleteAlbum extends TLMethod<TLRPC.Bool> {
        public static final int constructor = -1925949744;
        public int album_id;
        public TLRPC.InputPeer peer;

        public TL_deleteAlbum() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TLRPC.Bool deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeInt32(this.album_id);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_exportedStoryLink extends TLObject {
        public static final int constructor = 1070138683;
        public String link;

        public TL_exportedStoryLink() {
        }

        public static TL_exportedStoryLink TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (1070138683 == r3) goto L5;
            TL_exportedStoryLink r0 = null;
        L7:
            return (TL_exportedStoryLink) TLObject.TLdeserialize(TL_exportedStoryLink.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_exportedStoryLink();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.link = r1.readString(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.link);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_foundStories extends TLObject {
        public static final int constructor = -488736969;
        public ArrayList<TLRPC.Chat> chats;
        public int count;
        public int flags;
        public String next_offset;
        public ArrayList<TL_foundStory> stories;
        public ArrayList<TLRPC.User> users;

        public TL_foundStories() {
            this.stories = new ArrayList();
            this.chats = new ArrayList();
            this.users = new ArrayList();
        }

        public static TL_foundStories TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-488736969) == r3) goto L5;
            TL_foundStories r0 = null;
        L7:
            return (TL_foundStories) TLObject.TLdeserialize(TL_foundStories.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_foundStories();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.count = r3.readInt32(r4);
            this.stories = Vector.deserialize(r3, new TL_stories$TL_foundStories$$ExternalSyntheticLambda0(), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.next_offset = r3.readString(r4);
        L5:
            this.chats = Vector.deserialize(r3, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r4);
            this.users = Vector.deserialize(r3, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt32(this.count);
            Vector.serialize(r3, this.stories);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            r3.writeString(this.next_offset);
        L5:
            Vector.serialize(r3, this.chats);
            Vector.serialize(r3, this.users);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_foundStory extends TLObject {
        public static final int constructor = -394605632;
        public TLRPC.Peer peer;
        public StoryItem storyItem;

        public TL_foundStory() {
        }

        public static TL_foundStory TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-394605632) == r3) goto L5;
            TL_foundStory r0 = null;
        L7:
            return (TL_foundStory) TLObject.TLdeserialize(TL_foundStory.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_foundStory();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.peer = TLRPC.Peer.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.storyItem = StoryItem.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            this.storyItem.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_geoPointAddress extends TLObject {
        public static final int constructor = -565420653;
        public String city;
        public String country_iso2;
        public int flags;
        public String state;
        public String street;

        public TL_geoPointAddress() {
        }

        public static TL_geoPointAddress TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-565420653) == r3) goto L5;
            TL_geoPointAddress r0 = null;
        L7:
            return (TL_geoPointAddress) TLObject.TLdeserialize(TL_geoPointAddress.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_geoPointAddress();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.country_iso2 = r3.readString(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.state = r3.readString(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.city = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.street = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeString(this.country_iso2);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeString(this.state);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r3.writeString(this.city);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            r3.writeString(this.street);
            return;
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder("geo{country=");
            r0.append(this.country_iso2);
            r0.append(", ");
            String r2 = this.state;
            String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r2 == null) goto L5;
            String r22 = "state=" + this.state + ", ";
        L6:
            r0.append(r22);
            if (this.city == null) goto L9;
            String r1 = "city=" + this.city + ", ";
        L10:
            r0.append(r1);
            if (this.street == null) goto L13;
            r3 = "street=" + this.street;
        L13:
            r0.append(r3);
            r0.append("}");
            return r0.toString();
        L9:
            r1 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L10
        L5:
            r22 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L6
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_getAlbums extends TLMethod<Albums> {
        public static final int constructor = 632548039;
        public long hash;
        public TLRPC.InputPeer peer;

        public TL_getAlbums() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public Albums deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return Albums.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            this.peer.serializeToStream(r3);
            r3.writeInt64(this.hash);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_getStoryReactionsList extends TLObject {
        public static final int constructor = -1179482081;
        public int flags;
        public boolean forwards_first;
        public int id;
        public int limit;
        public String offset;
        public TLRPC.InputPeer peer;
        public TLRPC.Reaction reaction;

        public TL_getStoryReactionsList() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_storyReactionsList.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 4, this.forwards_first);
            this.flags = r0;
            r4.writeInt32(r0);
            this.peer.serializeToStream(r4);
            r4.writeInt32(this.id);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.reaction.serializeToStream(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L8;
            r4.writeString(this.offset);
        L8:
            r4.writeInt32(this.limit);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_inputMediaAreaChannelPost extends MediaArea {
        public static final int constructor = 577893055;
        public TLRPC.InputChannel channel;
        public int msg_id;

        public TL_inputMediaAreaChannelPost() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.coordinates = MediaAreaCoordinates.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.channel = TLRPC.InputChannel.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.msg_id = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.coordinates.serializeToStream(r2);
            this.channel.serializeToStream(r2);
            r2.writeInt32(this.msg_id);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_inputMediaAreaVenue extends MediaArea {
        public static final int constructor = -1300094593;
        public long query_id;
        public String result_id;

        public TL_inputMediaAreaVenue() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.coordinates = MediaAreaCoordinates.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.query_id = r3.readInt64(r4);
            this.result_id = r3.readString(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            this.coordinates.serializeToStream(r3);
            r3.writeInt64(this.query_id);
            r3.writeString(this.result_id);
        }
    }

    public static class TL_mediaAreaChannelPost extends MediaArea {
        public static final int constructor = 1996756655;
        public long channel_id;
        public int msg_id;

        public TL_mediaAreaChannelPost() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.coordinates = MediaAreaCoordinates.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.channel_id = r3.readInt64(r4);
            this.msg_id = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            this.coordinates.serializeToStream(r3);
            r3.writeInt64(this.channel_id);
            r3.writeInt32(this.msg_id);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_mediaAreaCoordinates extends MediaAreaCoordinates {
        public static final int constructor = -808853502;

        public TL_mediaAreaCoordinates() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.x = r3.readDouble(r4);
            this.y = r3.readDouble(r4);
            this.w = r3.readDouble(r4);
            this.h = r3.readDouble(r4);
            this.rotation = r3.readDouble(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.radius = r3.readDouble(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeDouble(this.x);
            r3.writeDouble(this.y);
            r3.writeDouble(this.w);
            r3.writeDouble(this.h);
            r3.writeDouble(this.rotation);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeDouble(this.radius);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_mediaAreaCoordinates_layer181 extends MediaAreaCoordinates {
        public static final int constructor = 64088654;

        public TL_mediaAreaCoordinates_layer181() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.x = r3.readDouble(r4);
            this.y = r3.readDouble(r4);
            this.w = r3.readDouble(r4);
            this.h = r3.readDouble(r4);
            this.rotation = r3.readDouble(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeDouble(this.x);
            r3.writeDouble(this.y);
            r3.writeDouble(this.w);
            r3.writeDouble(this.h);
            r3.writeDouble(this.rotation);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_mediaAreaGeoPoint extends MediaArea {
        public static final int constructor = -891992787;
        public TL_geoPointAddress address;
        public TLRPC.GeoPoint geo;

        public TL_mediaAreaGeoPoint() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.coordinates = MediaAreaCoordinates.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.geo = TLRPC.GeoPoint.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.address = TL_geoPointAddress.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            this.coordinates.serializeToStream(r3);
            this.geo.serializeToStream(r3);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.address.serializeToStream(r3);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_mediaAreaGeoPoint_layer181 extends TL_mediaAreaGeoPoint {
        public static final int constructor = -544523486;

        public TL_mediaAreaGeoPoint_layer181() {
        }

        @Override // org.telegram.tgnet.tl.TL_stories.TL_mediaAreaGeoPoint, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.coordinates = MediaAreaCoordinates.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.geo = TLRPC.GeoPoint.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.tl.TL_stories.TL_mediaAreaGeoPoint, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.coordinates.serializeToStream(r2);
            this.geo.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_mediaAreaStarGift extends MediaArea {
        public static final int constructor = 1468491885;
        public String slug;

        public TL_mediaAreaStarGift() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.coordinates = MediaAreaCoordinates.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.slug = r2.readString(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.coordinates.serializeToStream(r2);
            r2.writeString(this.slug);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_mediaAreaSuggestedReaction extends MediaArea {
        public static final int constructor = 340088945;

        public TL_mediaAreaSuggestedReaction() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.dark = TLObject.hasFlag(r0, 1);
            this.flipped = TLObject.hasFlag(this.flags, 2);
            this.coordinates = MediaAreaCoordinates.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.reaction = TLRPC.Reaction.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.dark);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.flipped);
            this.flags = r02;
            r4.writeInt32(r02);
            this.coordinates.serializeToStream(r4);
            this.reaction.serializeToStream(r4);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_mediaAreaUrl extends MediaArea {
        public static final int constructor = 926421125;
        public String url;

        public TL_mediaAreaUrl() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.coordinates = MediaAreaCoordinates.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.url = r2.readString(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.coordinates.serializeToStream(r2);
            r2.writeString(this.url);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_mediaAreaVenue extends MediaArea {
        public static final int constructor = -1098720356;
        public String address;
        public TLRPC.GeoPoint geo;
        public String provider;
        public String title;
        public String venue_id;
        public String venue_type;

        public TL_mediaAreaVenue() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.coordinates = MediaAreaCoordinates.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.geo = TLRPC.GeoPoint.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.title = r2.readString(r3);
            this.address = r2.readString(r3);
            this.provider = r2.readString(r3);
            this.venue_id = r2.readString(r3);
            this.venue_type = r2.readString(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.coordinates.serializeToStream(r2);
            this.geo.serializeToStream(r2);
            r2.writeString(this.title);
            r2.writeString(this.address);
            r2.writeString(this.provider);
            r2.writeString(this.venue_id);
            r2.writeString(this.venue_type);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_mediaAreaWeather extends MediaArea {
        public static final int constructor = 1235637404;
        public int color;
        public String emoji;
        public double temperature_c;

        public TL_mediaAreaWeather() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.coordinates = MediaAreaCoordinates.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.emoji = r3.readString(r4);
            this.temperature_c = r3.readDouble(r4);
            this.color = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            this.coordinates.serializeToStream(r3);
            r3.writeString(this.emoji);
            r3.writeDouble(this.temperature_c);
            r3.writeInt32(this.color);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_mediaAreaWeather2 extends MediaArea {
        public static final int constructor = -2057362882;
        public String emoji;
        public int temperature_c;

        public TL_mediaAreaWeather2() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.dark = TLObject.hasFlag(r0, 1);
            this.coordinates = MediaAreaCoordinates.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.emoji = r3.readString(r4);
            this.temperature_c = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.dark);
            this.flags = r0;
            r4.writeInt32(r0);
            this.coordinates.serializeToStream(r4);
            r4.writeString(this.emoji);
            r4.writeInt32(this.temperature_c);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_mediaAreaWeatherOld extends MediaArea {
        public static final int constructor = 1132918857;
        public String emoji;
        public double temperature_c;

        public TL_mediaAreaWeatherOld() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.dark = TLObject.hasFlag(r0, 1);
            this.coordinates = MediaAreaCoordinates.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.emoji = r3.readString(r4);
            this.temperature_c = r3.readDouble(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.dark);
            this.flags = r0;
            r4.writeInt32(r0);
            this.coordinates.serializeToStream(r4);
            r4.writeString(this.emoji);
            r4.writeDouble(this.temperature_c);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_myBoost extends TLObject {
        public static int constructor = -1001897636;
        public int cooldown_until_date;
        public int date;
        public int expires;
        public int flags;
        public TLRPC.Peer peer;
        public int slot;

        static {
        }

        public TL_myBoost() {
        }

        public static TL_myBoost TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (constructor == r3) goto L5;
            TL_myBoost r0 = null;
        L7:
            return (TL_myBoost) TLObject.TLdeserialize(TL_myBoost.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_myBoost();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.slot = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.date = r3.readInt32(r4);
            this.expires = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.cooldown_until_date = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt32(this.slot);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.peer.serializeToStream(r3);
        L5:
            r3.writeInt32(this.date);
            r3.writeInt32(this.expires);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r3.writeInt32(this.cooldown_until_date);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_peerStories extends PeerStories {
        public static final int constructor = -1707742823;

        public TL_peerStories() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.max_read_id = r3.readInt32(r4);
        L5:
            this.stories = Vector.deserialize(r3, new TL_stories$TL_peerStories$$ExternalSyntheticLambda0(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            this.peer.serializeToStream(r3);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            r3.writeInt32(this.max_read_id);
        L5:
            Vector.serialize(r3, this.stories);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_peerStories_layer162 extends TL_peerStories {
        public static final int constructor = -2045664768;

        public TL_peerStories_layer162() {
        }

        @Override // org.telegram.tgnet.tl.TL_stories.TL_peerStories, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r4, boolean r5) {
            this.flags = r4.readInt32(r5);
            long r0 = r4.readInt64(r5);
            TLRPC.TL_peerUser r2 = new TLRPC.TL_peerUser();
            this.peer = r2;
            r2.user_id = r0;
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.max_read_id = r4.readInt32(r5);
        L5:
            this.stories = Vector.deserialize(r4, new TL_stories$TL_peerStories$$ExternalSyntheticLambda0(), r5);
        }

        @Override // org.telegram.tgnet.tl.TL_stories.TL_peerStories, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt64(this.peer.user_id);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            r3.writeInt32(this.max_read_id);
        L5:
            Vector.serialize(r3, this.stories);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_premium_applyBoost extends TLObject {
        public static int constructor = 1803396934;
        public int flags;
        public TLRPC.InputPeer peer;
        public ArrayList<Integer> slots;

        static {
        }

        public TL_premium_applyBoost() {
            this.slots = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_premium_myBoosts.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            Vector.serializeInt(r3, this.slots);
        L5:
            this.peer.serializeToStream(r3);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_premium_boostsList extends TLObject {
        public static int constructor = -2030542532;
        public ArrayList<Boost> boosts;
        public int count;
        public int flags;
        public String next_offset;
        public ArrayList<TLRPC.User> users;

        static {
        }

        public TL_premium_boostsList() {
            this.boosts = new ArrayList();
            this.users = new ArrayList();
        }

        public static TL_premium_boostsList TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (constructor == r3) goto L5;
            TL_premium_boostsList r0 = null;
        L7:
            return (TL_premium_boostsList) TLObject.TLdeserialize(TL_premium_boostsList.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_premium_boostsList();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.count = r3.readInt32(r4);
            this.boosts = Vector.deserialize(r3, new TL_stories$TL_premium_boostsList$$ExternalSyntheticLambda0(), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.next_offset = r3.readString(r4);
        L5:
            this.users = Vector.deserialize(r3, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt32(this.count);
            Vector.serialize(r3, this.boosts);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            r3.writeString(this.next_offset);
        L5:
            Vector.serialize(r3, this.users);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_premium_boostsStatus extends TLObject {
        public static int constructor = 1230586490;
        public String boost_url;
        public int boosts;
        public int current_level_boosts;
        public int flags;
        public int gift_boosts;
        public int level;
        public boolean my_boost;
        public ArrayList<Integer> my_boost_slots;
        public int next_level_boosts;
        public TL_stats.TL_statsPercentValue premium_audience;
        public ArrayList<PrepaidGiveaway> prepaid_giveaways;

        static {
        }

        public TL_premium_boostsStatus() {
            this.prepaid_giveaways = new ArrayList();
            this.my_boost_slots = new ArrayList();
        }

        public static TL_premium_boostsStatus TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (constructor == r3) goto L5;
            TL_premium_boostsStatus r0 = null;
        L7:
            return (TL_premium_boostsStatus) TLObject.TLdeserialize(TL_premium_boostsStatus.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_premium_boostsStatus();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r4, boolean r5) {
            int r0 = r4.readInt32(r5);
            this.flags = r0;
            this.my_boost = TLObject.hasFlag(r0, 4);
            this.level = r4.readInt32(r5);
            this.current_level_boosts = r4.readInt32(r5);
            this.boosts = r4.readInt32(r5);
            if (TLObject.hasFlag(this.flags, 16) == false) goto L6;
            this.gift_boosts = r4.readInt32(r5);
        L6:
            if (TLObject.hasFlag(this.flags, 1) == false) goto L9;
            this.next_level_boosts = r4.readInt32(r5);
        L9:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L11;
            this.premium_audience = TL_stats.TL_statsPercentValue.TLdeserialize(r4, r4.readInt32(r5), r5);
        L11:
            this.boost_url = r4.readString(r5);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.prepaid_giveaways = Vector.deserialize(r4, new TL_stories$TL_premium_boostsStatus$$ExternalSyntheticLambda0(), r5);
        L15:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L18;
            this.my_boost_slots = Vector.deserializeInt(r4, r5);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 4, this.my_boost);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeInt32(this.level);
            r4.writeInt32(this.current_level_boosts);
            r4.writeInt32(this.boosts);
            if (TLObject.hasFlag(this.flags, 16) == false) goto L6;
            r4.writeInt32(this.gift_boosts);
        L6:
            if (TLObject.hasFlag(this.flags, 1) == false) goto L9;
            r4.writeInt32(this.next_level_boosts);
        L9:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L11;
            this.premium_audience.serializeToStream(r4);
        L11:
            r4.writeString(this.boost_url);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            Vector.serialize(r4, this.prepaid_giveaways);
        L15:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L18;
            Vector.serializeInt(r4, this.my_boost_slots);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_premium_getBoostsList extends TLObject {
        public static int constructor = 1626764896;
        public int flags;
        public boolean gifts;
        public int limit;
        public String offset;
        public TLRPC.InputPeer peer;

        static {
        }

        public TL_premium_getBoostsList() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_premium_boostsList.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.gifts);
            this.flags = r0;
            r4.writeInt32(r0);
            this.peer.serializeToStream(r4);
            r4.writeString(this.offset);
            r4.writeInt32(this.limit);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_premium_getBoostsStatus extends TLObject {
        public static int constructor = 70197089;
        public TLRPC.InputPeer peer;

        static {
        }

        public TL_premium_getBoostsStatus() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_premium_boostsStatus.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_premium_getMyBoosts extends TLObject {
        public static int constructor = 199719754;

        static {
        }

        public TL_premium_getMyBoosts() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_premium_myBoosts.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_premium_myBoosts extends TLObject {
        public static int constructor = -1696454430;
        public ArrayList<TLRPC.Chat> chats;
        public ArrayList<TL_myBoost> my_boosts;
        public ArrayList<TLRPC.User> users;

        static {
        }

        public TL_premium_myBoosts() {
            this.my_boosts = new ArrayList();
            this.chats = new ArrayList();
            this.users = new ArrayList();
        }

        public static TL_premium_myBoosts TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (constructor == r3) goto L5;
            TL_premium_myBoosts r0 = null;
        L7:
            return (TL_premium_myBoosts) TLObject.TLdeserialize(TL_premium_myBoosts.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_premium_myBoosts();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.my_boosts = Vector.deserialize(r2, new TL_stories$TL_premium_myBoosts$$ExternalSyntheticLambda0(), r3);
            this.chats = Vector.deserialize(r2, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r3);
            this.users = Vector.deserialize(r2, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.my_boosts);
            Vector.serialize(r2, this.chats);
            Vector.serialize(r2, this.users);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_prepaidGiveaway extends PrepaidGiveaway {
        public static final int constructor = -1303143084;
        public int months;

        public TL_prepaidGiveaway() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.id = r3.readInt64(r4);
            this.months = r3.readInt32(r4);
            this.quantity = r3.readInt32(r4);
            this.date = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.id);
            r3.writeInt32(this.months);
            r3.writeInt32(this.quantity);
            r3.writeInt32(this.date);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_prepaidStarsGiveaway extends PrepaidGiveaway {
        public static final int constructor = -1700956192;
        public long stars;

        public TL_prepaidStarsGiveaway() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.id = r3.readInt64(r4);
            this.stars = r3.readInt64(r4);
            this.quantity = r3.readInt32(r4);
            this.boosts = r3.readInt32(r4);
            this.date = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.id);
            r3.writeInt64(this.stars);
            r3.writeInt32(this.quantity);
            r3.writeInt32(this.boosts);
            r3.writeInt32(this.date);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_publicForwardStory extends TL_stats.PublicForward {
        public static final int constructor = -302797360;
        public TLRPC.Peer peer;
        public StoryItem story;

        public TL_publicForwardStory() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.peer = TLRPC.Peer.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.story = StoryItem.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            this.story.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_reorderAlbums extends TLMethod<TLRPC.Bool> {
        public static final int constructor = -2060059687;
        public ArrayList<Integer> order;
        public TLRPC.InputPeer peer;

        public TL_reorderAlbums() {
            this.order = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TLRPC.Bool deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            Vector.serializeInt(r2, this.order);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_startLive extends TLMethod<TLRPC.Updates> {
        public static final int constructor = -798372642;
        public String caption;
        public ArrayList<TLRPC.MessageEntity> entities;
        public int flags;
        public Boolean messages_enabled;
        public boolean noforwards;
        public TLRPC.InputPeer peer;
        public boolean pinned;
        public ArrayList<TLRPC.InputPrivacyRule> privacy_rules;
        public long random_id;
        public boolean rtmp_stream;
        public Long send_paid_messages_stars;

        public TL_startLive() {
            this.entities = new ArrayList();
            this.privacy_rules = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TLRPC.Updates deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r6) {
            r6.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 4, this.pinned);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.noforwards);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 32, this.rtmp_stream);
            this.flags = r03;
            boolean r2 = false;
            if (this.messages_enabled == null) goto L5;
            boolean r1 = true;
        L6:
            int r04 = TLObject.setFlag(r03, 64, r1);
            this.flags = r04;
            if (this.send_paid_messages_stars == null) goto L9;
            r2 = true;
        L9:
            int r05 = TLObject.setFlag(r04, 128, r2);
            this.flags = r05;
            r6.writeInt32(r05);
            this.peer.serializeToStream(r6);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L13;
            r6.writeString(this.caption);
        L13:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L15;
            Vector.serialize(r6, this.entities);
        L15:
            Vector.serialize(r6, this.privacy_rules);
            r6.writeInt64(this.random_id);
            Boolean r06 = this.messages_enabled;
            if (r06 == null) goto L18;
            r6.writeBool(r06.booleanValue());
        L18:
            Long r5 = this.send_paid_messages_stars;
            if (r5 == null) goto L22;
            r6.writeInt64(r5.longValue());
            return;
        L22:
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stats_getStoryStats extends TLObject {
        public static final int constructor = 927985472;
        public boolean dark;
        public int flags;
        public int id;
        public TLRPC.InputPeer peer;

        public TL_stats_getStoryStats() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_stats_storyStats.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            boolean r0 = this.dark;
            int r1 = this.flags;
            if (r0 == false) goto L5;
            int r02 = r1 | 1;
        L6:
            this.flags = r02;
            r3.writeInt32(r02);
            this.peer.serializeToStream(r3);
            r3.writeInt32(this.id);
            return;
        L5:
            r02 = r1 & (-2);
            goto L6
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stats_storyStats extends TLObject {
        public static final int constructor = 1355613820;
        public TL_stats.StatsGraph reactions_by_emotion_graph;
        public TL_stats.StatsGraph views_graph;

        public TL_stats_storyStats() {
        }

        public static TL_stats_storyStats TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (1355613820 == r3) goto L5;
            TL_stats_storyStats r0 = null;
        L7:
            return (TL_stats_storyStats) TLObject.TLdeserialize(TL_stats_storyStats.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_stats_storyStats();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.views_graph = TL_stats.StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.reactions_by_emotion_graph = TL_stats.StatsGraph.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.views_graph.serializeToStream(r2);
            this.reactions_by_emotion_graph.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_storiesStealthMode extends TLObject {
        public static final int constructor = 1898850301;
        public int active_until_date;
        public int cooldown_until_date;
        public int flags;

        public TL_storiesStealthMode() {
        }

        public static TL_storiesStealthMode TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (1898850301 == r3) goto L5;
            TL_storiesStealthMode r0 = null;
        L7:
            return (TL_storiesStealthMode) TLObject.TLdeserialize(TL_storiesStealthMode.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_storiesStealthMode();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1) == false) goto L6;
            this.active_until_date = r3.readInt32(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.cooldown_until_date = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeInt32(this.active_until_date);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r3.writeInt32(this.cooldown_until_date);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_activateStealthMode extends TLObject {
        public static final int constructor = 1471926630;
        public int flags;
        public boolean future;
        public boolean past;

        public TL_stories_activateStealthMode() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.past);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.future);
            this.flags = r02;
            r4.writeInt32(r02);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_allStories extends stories_AllStories {
        public static final int constructor = 1862033025;
        public ArrayList<TLRPC.Chat> chats;
        public int count;
        public int flags;
        public boolean has_more;
        public ArrayList<PeerStories> peer_stories;
        public String state;
        public TL_storiesStealthMode stealth_mode;
        public ArrayList<TLRPC.User> users;

        public TL_stories_allStories() {
            this.peer_stories = new ArrayList();
            this.chats = new ArrayList();
            this.users = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.has_more = TLObject.hasFlag(r0, 1);
            this.count = r3.readInt32(r4);
            this.state = r3.readString(r4);
            this.peer_stories = Vector.deserialize(r3, new TL_stories$TL_stories_allStories$$ExternalSyntheticLambda0(), r4);
            this.chats = Vector.deserialize(r3, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r4);
            this.users = Vector.deserialize(r3, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r4);
            this.stealth_mode = TL_storiesStealthMode.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.has_more);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeInt32(this.count);
            r4.writeString(this.state);
            Vector.serialize(r4, this.peer_stories);
            Vector.serialize(r4, this.chats);
            Vector.serialize(r4, this.users);
            this.stealth_mode.serializeToStream(r4);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_allStoriesNotModified extends stories_AllStories {
        public static final int constructor = 291044926;
        public int flags;
        public String state;
        public TL_storiesStealthMode stealth_mode;

        public TL_stories_allStoriesNotModified() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.flags = r2.readInt32(r3);
            this.state = r2.readString(r3);
            this.stealth_mode = TL_storiesStealthMode.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.flags);
            r2.writeString(this.state);
            this.stealth_mode.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_canSendStory extends TLObject {
        public static final int constructor = 820732912;
        public TLRPC.InputPeer peer;

        public TL_stories_canSendStory() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return canSendStoryCount.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_deleteStories extends TLObject {
        public static final int constructor = -1369842849;
        public ArrayList<Integer> id;
        public TLRPC.InputPeer peer;

        public TL_stories_deleteStories() {
            this.id = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return Vector.TLDeserializeInt(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            Vector.serializeInt(r2, this.id);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_editStory extends TLObject {
        public static final int constructor = 744728363;
        public String caption;
        public ArrayList<TLRPC.MessageEntity> entities;
        public int flags;
        public int id;
        public TLRPC.InputMedia media;
        public ArrayList<MediaArea> media_areas;
        public TLRPC.InputDocument music;
        public TLRPC.InputPeer peer;
        public ArrayList<TLRPC.InputPrivacyRule> privacy_rules;

        public TL_stories_editStory() {
            this.media_areas = new ArrayList();
            this.entities = new ArrayList();
            this.privacy_rules = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            this.peer.serializeToStream(r3);
            r3.writeInt32(this.id);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.media.serializeToStream(r3);
        L6:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L9;
            Vector.serialize(r3, this.media_areas);
        L9:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L12;
            r3.writeString(this.caption);
        L12:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L15;
            Vector.serialize(r3, this.entities);
        L15:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L18;
            Vector.serialize(r3, this.privacy_rules);
        L18:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L21;
            this.music.serializeToStream(r3);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_exportStoryLink extends TLObject {
        public static final int constructor = 2072899360;
        public int id;
        public TLRPC.InputPeer peer;

        public TL_stories_exportStoryLink() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_exportedStoryLink.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeInt32(this.id);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_getAlbumStories extends TLMethod<TL_stories_stories> {
        public static final int constructor = -1400869535;
        public int album_id;
        public int limit;
        public int offset;
        public TLRPC.InputPeer peer;

        public TL_stories_getAlbumStories() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TL_stories_stories deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TL_stories_stories.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeInt32(this.album_id);
            r2.writeInt32(this.offset);
            r2.writeInt32(this.limit);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_getAllReadPeerStories extends TLObject {
        public static final int constructor = -1688541191;

        public TL_stories_getAllReadPeerStories() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_getAllStories extends TLObject {
        public static final int constructor = -290400731;
        public int flags;
        public boolean include_hidden;
        public boolean next;
        public String state;

        public TL_stories_getAllStories() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return stories_AllStories.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.next);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 4, this.include_hidden);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r4.writeString(this.state);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_getChatsToSend extends TLObject {
        public static final int constructor = -1519744160;

        public TL_stories_getChatsToSend() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.messages_Chats.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_stories_getPeerMaxIDs extends TLMethod<Vector<TLRPC.TL_recentStory>> {
        public static final int constructor = 2018087280;
        public ArrayList<TLRPC.InputPeer> id;

        public TL_stories_getPeerMaxIDs() {
            this.id = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public Vector<TLRPC.TL_recentStory> deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return Vector.TLDeserialize(r1, r2, r3, new TL_stories$TL_stories_getPeerMaxIDs$$ExternalSyntheticLambda0());
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.id);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_getPeerStories extends TLObject {
        public static final int constructor = 743103056;
        public TLRPC.InputPeer peer;

        public TL_stories_getPeerStories() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_stories_peerStories.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_getPinnedStories extends TLObject {
        public static final int constructor = 1478600156;
        public int limit;
        public int offset_id;
        public TLRPC.InputPeer peer;

        public TL_stories_getPinnedStories() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_stories_stories.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeInt32(this.offset_id);
            r2.writeInt32(this.limit);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_getStoriesArchive extends TLObject {
        public static final int constructor = -1271586794;
        public int limit;
        public int offset_id;
        public TLRPC.InputPeer peer;

        public TL_stories_getStoriesArchive() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_stories_stories.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeInt32(this.offset_id);
            r2.writeInt32(this.limit);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_getStoriesByID extends TLObject {
        public static final int constructor = 1467271796;
        public ArrayList<Integer> id;
        public TLRPC.InputPeer peer;

        public TL_stories_getStoriesByID() {
            this.id = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_stories_stories.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            Vector.serializeInt(r2, this.id);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_getStoriesViews extends TLObject {
        public static final int constructor = 685862088;
        public ArrayList<Integer> id;
        public TLRPC.InputPeer peer;

        public TL_stories_getStoriesViews() {
            this.id = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_stories_storyViews.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            Vector.serializeInt(r2, this.id);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_getStoryViewsList extends TLObject {
        public static final int constructor = 2127707223;
        public int flags;
        public boolean forwards_first;
        public int id;
        public boolean just_contacts;
        public int limit;
        public String offset;
        public TLRPC.InputPeer peer;
        public String q;
        public boolean reactions_first;

        public TL_stories_getStoryViewsList() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return StoryViewsList.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.just_contacts);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 4, this.reactions_first);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 8, this.forwards_first);
            this.flags = r03;
            r4.writeInt32(r03);
            this.peer.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            r4.writeString(this.q);
        L5:
            r4.writeInt32(this.id);
            r4.writeString(this.offset);
            r4.writeInt32(this.limit);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_incrementStoryViews extends TLObject {
        public static final int constructor = -1308456197;
        public ArrayList<Integer> id;
        public TLRPC.InputPeer peer;

        public TL_stories_incrementStoryViews() {
            this.id = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            Vector.serializeInt(r2, this.id);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_peerStories extends TLObject {
        public static final int constructor = -890861720;
        public ArrayList<TLRPC.Chat> chats;
        public PeerStories stories;
        public ArrayList<TLRPC.User> users;

        public TL_stories_peerStories() {
            this.chats = new ArrayList();
            this.users = new ArrayList();
        }

        public static TL_stories_peerStories TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-890861720) == r3) goto L5;
            TL_stories_peerStories r0 = null;
        L7:
            return (TL_stories_peerStories) TLObject.TLdeserialize(TL_stories_peerStories.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_stories_peerStories();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.stories = PeerStories.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.chats = Vector.deserialize(r2, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r3);
            this.users = Vector.deserialize(r2, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.stories.serializeToStream(r2);
            Vector.serialize(r2, this.chats);
            Vector.serialize(r2, this.users);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_readStories extends TLObject {
        public static final int constructor = -1521034552;
        public int max_id;
        public TLRPC.InputPeer peer;

        public TL_stories_readStories() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return Vector.TLDeserializeInt(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeInt32(this.max_id);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_report extends TLMethod<TLRPC.ReportResult> {
        public static final int constructor = 433646405;
        public ArrayList<Integer> id;
        public String message;
        public byte[] option;
        public TLRPC.InputPeer peer;

        public TL_stories_report() {
            this.id = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TLRPC.ReportResult deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.ReportResult.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            this.peer.serializeToStream(r3);
            Vector.serialize(r3, new Vector$$ExternalSyntheticLambda3(r3), this.id);
            r3.writeByteArray(this.option);
            r3.writeString(this.message);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_searchPosts extends TLObject {
        public static final int constructor = -780072697;
        public MediaArea area;
        public int flags;
        public String hashtag;
        public int limit;
        public String offset;
        public TLRPC.InputPeer peer;

        public TL_stories_searchPosts() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_foundStories.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeString(this.hashtag);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.area.serializeToStream(r3);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L11;
            this.peer.serializeToStream(r3);
        L11:
            r3.writeString(this.offset);
            r3.writeInt32(this.limit);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_sendReaction extends TLObject {
        public static final int constructor = 2144810674;
        public boolean add_to_recent;
        public int flags;
        public TLRPC.InputPeer peer;
        public TLRPC.Reaction reaction;
        public int story_id;

        public TL_stories_sendReaction() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.add_to_recent);
            this.flags = r0;
            r4.writeInt32(r0);
            this.peer.serializeToStream(r4);
            r4.writeInt32(this.story_id);
            this.reaction.serializeToStream(r4);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_sendStory extends TLObject {
        public static final int constructor = -1885443944;
        public ArrayList<Integer> albums;
        public String caption;
        public ArrayList<TLRPC.MessageEntity> entities;
        public int flags;
        public TLRPC.InputPeer fwd_from_id;
        public int fwd_from_story;
        public boolean fwd_modified;
        public TLRPC.InputMedia media;
        public ArrayList<MediaArea> media_areas;
        public TLRPC.InputDocument music;
        public boolean noforwards;
        public TLRPC.InputPeer peer;
        public int period;
        public boolean pinned;
        public ArrayList<TLRPC.InputPrivacyRule> privacy_rules;
        public long random_id;

        public TL_stories_sendStory() {
            this.media_areas = new ArrayList();
            this.entities = new ArrayList();
            this.privacy_rules = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r5) {
            r5.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 4, this.pinned);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 16, this.noforwards);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 128, this.fwd_modified);
            this.flags = r03;
            if (this.albums == null) goto L5;
            boolean r1 = true;
        L6:
            int r04 = TLObject.setFlag(r03, 256, r1);
            this.flags = r04;
            r5.writeInt32(r04);
            this.peer.serializeToStream(r5);
            this.media.serializeToStream(r5);
            if (TLObject.hasFlag(this.flags, 32) == false) goto L10;
            Vector.serialize(r5, this.media_areas);
        L10:
            if (TLObject.hasFlag(this.flags, 1) == false) goto L13;
            r5.writeString(this.caption);
        L13:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L15;
            Vector.serialize(r5, this.entities);
        L15:
            Vector.serialize(r5, this.privacy_rules);
            r5.writeInt64(this.random_id);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L19;
            r5.writeInt32(this.period);
        L19:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L22;
            this.fwd_from_id.serializeToStream(r5);
        L22:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L25;
            r5.writeInt32(this.fwd_from_story);
        L25:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L28;
            Vector.serializeInt(r5, this.albums);
        L28:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L31;
            this.music.serializeToStream(r5);
            return;
        L31:
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_stories extends TLObject {
        public static final int constructor = 1673780490;
        public ArrayList<TLRPC.Chat> chats;
        public int count;
        public int flags;
        public ArrayList<Integer> pinned_to_top;
        public ArrayList<StoryItem> stories;
        public ArrayList<TLRPC.User> users;

        public TL_stories_stories() {
            this.stories = new ArrayList();
            this.pinned_to_top = new ArrayList();
            this.chats = new ArrayList();
            this.users = new ArrayList();
        }

        public static TL_stories_stories TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (1673780490 == r3) goto L5;
            TL_stories_stories r0 = null;
        L7:
            return (TL_stories_stories) TLObject.TLdeserialize(TL_stories_stories.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_stories_stories();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.count = r3.readInt32(r4);
            this.stories = Vector.deserialize(r3, new TL_stories$TL_peerStories$$ExternalSyntheticLambda0(), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.pinned_to_top = Vector.deserializeInt(r3, r4);
        L5:
            this.chats = Vector.deserialize(r3, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r4);
            this.users = Vector.deserialize(r3, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt32(this.count);
            Vector.serialize(r3, this.stories);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            Vector.serializeInt(r3, this.pinned_to_top);
        L5:
            Vector.serialize(r3, this.chats);
            Vector.serialize(r3, this.users);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_storyViews extends TLObject {
        public static final int constructor = -560009955;
        public ArrayList<TLRPC.User> users;
        public ArrayList<StoryViews> views;

        public TL_stories_storyViews() {
            this.views = new ArrayList();
            this.users = new ArrayList();
        }

        public static TL_stories_storyViews TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-560009955) == r3) goto L5;
            TL_stories_storyViews r0 = null;
        L7:
            return (TL_stories_storyViews) TLObject.TLdeserialize(TL_stories_storyViews.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_stories_storyViews();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.views = Vector.deserialize(r2, new TL_stories$TL_stories_storyViews$$ExternalSyntheticLambda0(), r3);
            this.users = Vector.deserialize(r2, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.views);
            Vector.serialize(r2, this.users);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_stories_togglePeerStoriesHidden extends TLObject {
        public static final int constructor = -1123805756;
        public boolean hidden;
        public TLRPC.InputPeer peer;

        public TL_stories_togglePeerStoriesHidden() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeBool(this.hidden);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_storyAlbum extends TLObject {
        public static final int constructor = -1826262950;
        public int album_id;
        public TLRPC.Photo icon_photo;
        public TLRPC.Document icon_video;
        public String title;

        public TL_storyAlbum() {
        }

        public static TL_storyAlbum TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == (-1826262950)) goto L5;
            TL_storyAlbum r0 = null;
        L7:
            return (TL_storyAlbum) TLObject.TLdeserialize(TL_storyAlbum.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_storyAlbum();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.album_id = r3.readInt32(r4);
            this.title = r3.readString(r4);
            if (TLObject.hasFlag(r0, 1) == false) goto L6;
            this.icon_photo = TLRPC.Photo.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if (TLObject.hasFlag(r0, 2) == false) goto L9;
            this.icon_video = TLRPC.Document.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r5) {
            r5.writeInt32(constructor);
            boolean r1 = false;
            if (this.icon_photo == null) goto L5;
            boolean r0 = true;
        L6:
            int r02 = TLObject.setFlag(0, 1, r0);
            if (this.icon_video == null) goto L9;
            r1 = true;
        L9:
            int r03 = TLObject.setFlag(r02, 2, r1);
            r5.writeInt32(r03);
            r5.writeInt32(this.album_id);
            r5.writeString(this.title);
            if (TLObject.hasFlag(r03, 1) == false) goto L13;
            this.icon_photo.serializeToStream(r5);
        L13:
            if (TLObject.hasFlag(r03, 2) == false) goto L16;
            this.icon_video.serializeToStream(r5);
            return;
        L16:
            return;
        L5:
            r0 = false;
            goto L6
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_storyFwdHeader extends StoryFwdHeader {
        public static final int constructor = -1205411504;

        public TL_storyFwdHeader() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.modified = TLObject.hasFlag(r0, 8);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.from = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.from_name = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.story_id = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 8, this.modified);
            this.flags = r0;
            r4.writeInt32(r0);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.from.serializeToStream(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r4.writeString(this.from_name);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            r4.writeInt32(this.story_id);
            return;
        }
    }

    public static class TL_storyItem extends StoryItem {
        public static final int constructor = 379894076;

        public TL_storyItem() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.pinned = TLObject.hasFlag(r0, 32);
            this.isPublic = TLObject.hasFlag(this.flags, 128);
            this.close_friends = TLObject.hasFlag(this.flags, 256);
            this.min = TLObject.hasFlag(this.flags, 512);
            this.noforwards = TLObject.hasFlag(this.flags, 1024);
            this.edited = TLObject.hasFlag(this.flags, 2048);
            this.contacts = TLObject.hasFlag(this.flags, 4096);
            this.selected_contacts = TLObject.hasFlag(this.flags, 8192);
            this.out = TLObject.hasFlag(this.flags, 65536);
            this.id = r3.readInt32(r4);
            this.date = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 262144) == false) goto L6;
            this.from_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L8;
            this.fwd_from = StoryFwdHeader.TLdeserialize(r3, r3.readInt32(r4), r4);
        L8:
            this.expire_date = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L12;
            this.caption = r3.readString(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L14;
            this.entities = Vector.deserialize(r3, new MessagesStorage$$ExternalSyntheticLambda117(), r4);
        L14:
            this.media = TLRPC.MessageMedia.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L18;
            this.media_areas = Vector.deserialize(r3, new TL_stories$TL_storyItem$$ExternalSyntheticLambda0(), r4);
        L18:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L21;
            this.privacy = Vector.deserialize(r3, new TL_account$privacyRules$$ExternalSyntheticLambda0(), r4);
        L21:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L24;
            this.views = StoryViews.TLdeserialize(r3, r3.readInt32(r4), r4);
        L24:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L27;
            this.sent_reaction = TLRPC.Reaction.TLdeserialize(r3, r3.readInt32(r4), r4);
        L27:
            if (TLObject.hasFlag(this.flags, 524288) == false) goto L30;
            this.albums = Vector.deserializeInt(r3, r4);
        L30:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L33;
            this.music = TLRPC.Document.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r5) {
            r5.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 32, this.pinned);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 128, this.isPublic);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 256, this.close_friends);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 512, this.min);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 1024, this.noforwards);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 2048, this.edited);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 4096, this.contacts);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, 8192, this.selected_contacts);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 65536, this.out);
            this.flags = r09;
            if (this.albums == null) goto L5;
            boolean r1 = true;
        L6:
            int r010 = TLObject.setFlag(r09, 524288, r1);
            this.flags = r010;
            r5.writeInt32(r010);
            r5.writeInt32(this.id);
            r5.writeInt32(this.date);
            if (TLObject.hasFlag(this.flags, 262144) == false) goto L10;
            this.from_id.serializeToStream(r5);
        L10:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L12;
            this.fwd_from.serializeToStream(r5);
        L12:
            r5.writeInt32(this.expire_date);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L16;
            r5.writeString(this.caption);
        L16:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L18;
            Vector.serialize(r5, this.entities);
        L18:
            this.media.serializeToStream(r5);
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L22;
            Vector.serialize(r5, this.media_areas);
        L22:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L25;
            Vector.serialize(r5, this.privacy);
        L25:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L28;
            this.views.serializeToStream(r5);
        L28:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L31;
            this.sent_reaction.serializeToStream(r5);
        L31:
            if (TLObject.hasFlag(this.flags, 524288) == false) goto L34;
            Vector.serializeInt(r5, this.albums);
        L34:
            if (TLObject.hasFlag(this.flags, 1048576) == false) goto L37;
            this.music.serializeToStream(r5);
            return;
        L37:
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_storyItemDeleted extends StoryItem {
        public static final int constructor = 1374088783;

        public TL_storyItemDeleted() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.id = r1.readInt32(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.id);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_storyItemSkipped extends StoryItem {
        public static final int constructor = -5388013;
        public boolean live;

        public TL_storyItemSkipped() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.close_friends = TLObject.hasFlag(r0, 256);
            this.live = TLObject.hasFlag(this.flags, 512);
            this.id = r3.readInt32(r4);
            this.date = r3.readInt32(r4);
            this.expire_date = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 256, this.close_friends);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 512, this.live);
            this.flags = r02;
            r4.writeInt32(r02);
            r4.writeInt32(this.id);
            r4.writeInt32(this.date);
            r4.writeInt32(this.expire_date);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_storyItem_layer160 extends TL_storyItem {
        public static final int constructor = 1445635639;

        public TL_storyItem_layer160() {
        }

        @Override // org.telegram.tgnet.tl.TL_stories.TL_storyItem, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.pinned = TLObject.hasFlag(r0, 32);
            this.isPublic = TLObject.hasFlag(this.flags, 128);
            this.close_friends = TLObject.hasFlag(this.flags, 256);
            this.min = TLObject.hasFlag(this.flags, 512);
            this.noforwards = TLObject.hasFlag(this.flags, 1024);
            this.edited = TLObject.hasFlag(this.flags, 2048);
            this.contacts = TLObject.hasFlag(this.flags, 4096);
            this.selected_contacts = TLObject.hasFlag(this.flags, 8192);
            this.id = r3.readInt32(r4);
            this.date = r3.readInt32(r4);
            this.expire_date = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.caption = r3.readString(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L8;
            this.entities = Vector.deserialize(r3, new MessagesStorage$$ExternalSyntheticLambda117(), r4);
        L8:
            this.media = TLRPC.MessageMedia.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.privacy = Vector.deserialize(r3, new TL_account$privacyRules$$ExternalSyntheticLambda0(), r4);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.views = StoryViews.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stories.TL_storyItem, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 32, this.pinned);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 128, this.isPublic);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 256, this.close_friends);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 512, this.min);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 1024, this.noforwards);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 2048, this.edited);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 4096, this.contacts);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, 8192, this.selected_contacts);
            this.flags = r08;
            r4.writeInt32(r08);
            r4.writeInt32(this.id);
            r4.writeInt32(this.date);
            r4.writeInt32(this.expire_date);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r4.writeString(this.caption);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L8;
            Vector.serialize(r4, this.entities);
        L8:
            this.media.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            Vector.serialize(r4, this.privacy);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.views.serializeToStream(r4);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_storyItem_layer166 extends TL_storyItem {
        public static final int constructor = 1153718222;

        public TL_storyItem_layer166() {
        }

        @Override // org.telegram.tgnet.tl.TL_stories.TL_storyItem, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.pinned = TLObject.hasFlag(r0, 32);
            this.isPublic = TLObject.hasFlag(this.flags, 128);
            this.close_friends = TLObject.hasFlag(this.flags, 256);
            this.min = TLObject.hasFlag(this.flags, 512);
            this.noforwards = TLObject.hasFlag(this.flags, 1024);
            this.edited = TLObject.hasFlag(this.flags, 2048);
            this.contacts = TLObject.hasFlag(this.flags, 4096);
            this.selected_contacts = TLObject.hasFlag(this.flags, 8192);
            this.out = TLObject.hasFlag(this.flags, 65536);
            this.id = r3.readInt32(r4);
            this.date = r3.readInt32(r4);
            this.expire_date = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.caption = r3.readString(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L8;
            this.entities = Vector.deserialize(r3, new MessagesStorage$$ExternalSyntheticLambda117(), r4);
        L8:
            this.media = TLRPC.MessageMedia.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L12;
            this.media_areas = Vector.deserialize(r3, new TL_stories$TL_storyItem$$ExternalSyntheticLambda0(), r4);
        L12:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L15;
            this.privacy = Vector.deserialize(r3, new TL_account$privacyRules$$ExternalSyntheticLambda0(), r4);
        L15:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L18;
            this.views = StoryViews.TLdeserialize(r3, r3.readInt32(r4), r4);
        L18:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L21;
            this.sent_reaction = TLRPC.Reaction.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stories.TL_storyItem, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 32, this.pinned);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 128, this.isPublic);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 256, this.close_friends);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 512, this.min);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 1024, this.noforwards);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 2048, this.edited);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 4096, this.contacts);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, 8192, this.selected_contacts);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 65536, this.out);
            this.flags = r09;
            r4.writeInt32(r09);
            r4.writeInt32(this.id);
            r4.writeInt32(this.date);
            r4.writeInt32(this.expire_date);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r4.writeString(this.caption);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L8;
            Vector.serialize(r4, this.entities);
        L8:
            this.media.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L12;
            Vector.serialize(r4, this.media_areas);
        L12:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L15;
            Vector.serialize(r4, this.privacy);
        L15:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L18;
            this.views.serializeToStream(r4);
        L18:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L21;
            this.sent_reaction.serializeToStream(r4);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_storyItem_layer174 extends TL_storyItem {
        public static final int constructor = -1352440415;

        public TL_storyItem_layer174() {
        }

        @Override // org.telegram.tgnet.tl.TL_stories.TL_storyItem, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.pinned = TLObject.hasFlag(r0, 32);
            this.isPublic = TLObject.hasFlag(this.flags, 128);
            this.close_friends = TLObject.hasFlag(this.flags, 256);
            this.min = TLObject.hasFlag(this.flags, 512);
            this.noforwards = TLObject.hasFlag(this.flags, 1024);
            this.edited = TLObject.hasFlag(this.flags, 2048);
            this.contacts = TLObject.hasFlag(this.flags, 4096);
            this.selected_contacts = TLObject.hasFlag(this.flags, 8192);
            this.out = TLObject.hasFlag(this.flags, 65536);
            this.id = r3.readInt32(r4);
            this.date = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L5;
            this.fwd_from = StoryFwdHeader.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.expire_date = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L9;
            this.caption = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L11;
            this.entities = Vector.deserialize(r3, new MessagesStorage$$ExternalSyntheticLambda117(), r4);
        L11:
            this.media = TLRPC.MessageMedia.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L15;
            this.media_areas = Vector.deserialize(r3, new TL_stories$TL_storyItem$$ExternalSyntheticLambda0(), r4);
        L15:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L18;
            this.privacy = Vector.deserialize(r3, new TL_account$privacyRules$$ExternalSyntheticLambda0(), r4);
        L18:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L21;
            this.views = StoryViews.TLdeserialize(r3, r3.readInt32(r4), r4);
        L21:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L24;
            this.sent_reaction = TLRPC.Reaction.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stories.TL_storyItem, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 32, this.pinned);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 128, this.isPublic);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 256, this.close_friends);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 512, this.min);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 1024, this.noforwards);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 2048, this.edited);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 4096, this.contacts);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, 8192, this.selected_contacts);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 65536, this.out);
            this.flags = r09;
            r4.writeInt32(r09);
            r4.writeInt32(this.id);
            r4.writeInt32(this.date);
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L5;
            this.fwd_from.serializeToStream(r4);
        L5:
            r4.writeInt32(this.expire_date);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L9;
            r4.writeString(this.caption);
        L9:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L11;
            Vector.serialize(r4, this.entities);
        L11:
            this.media.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L15;
            Vector.serialize(r4, this.media_areas);
        L15:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L18;
            Vector.serialize(r4, this.privacy);
        L18:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L21;
            this.views.serializeToStream(r4);
        L21:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L24;
            this.sent_reaction.serializeToStream(r4);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_storyItem_layer210 extends TL_storyItem {
        public static final int constructor = 2041735716;

        public TL_storyItem_layer210() {
        }

        @Override // org.telegram.tgnet.tl.TL_stories.TL_storyItem, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.pinned = TLObject.hasFlag(r0, 32);
            this.isPublic = TLObject.hasFlag(this.flags, 128);
            this.close_friends = TLObject.hasFlag(this.flags, 256);
            this.min = TLObject.hasFlag(this.flags, 512);
            this.noforwards = TLObject.hasFlag(this.flags, 1024);
            this.edited = TLObject.hasFlag(this.flags, 2048);
            this.contacts = TLObject.hasFlag(this.flags, 4096);
            this.selected_contacts = TLObject.hasFlag(this.flags, 8192);
            this.out = TLObject.hasFlag(this.flags, 65536);
            this.id = r3.readInt32(r4);
            this.date = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 262144) == false) goto L6;
            this.from_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L8;
            this.fwd_from = StoryFwdHeader.TLdeserialize(r3, r3.readInt32(r4), r4);
        L8:
            this.expire_date = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L12;
            this.caption = r3.readString(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L14;
            this.entities = Vector.deserialize(r3, new MessagesStorage$$ExternalSyntheticLambda117(), r4);
        L14:
            this.media = TLRPC.MessageMedia.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L18;
            this.media_areas = Vector.deserialize(r3, new TL_stories$TL_storyItem$$ExternalSyntheticLambda0(), r4);
        L18:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L21;
            this.privacy = Vector.deserialize(r3, new TL_account$privacyRules$$ExternalSyntheticLambda0(), r4);
        L21:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L24;
            this.views = StoryViews.TLdeserialize(r3, r3.readInt32(r4), r4);
        L24:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L27;
            this.sent_reaction = TLRPC.Reaction.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stories.TL_storyItem, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 32, this.pinned);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 128, this.isPublic);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 256, this.close_friends);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 512, this.min);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 1024, this.noforwards);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 2048, this.edited);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 4096, this.contacts);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, 8192, this.selected_contacts);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 65536, this.out);
            this.flags = r09;
            r4.writeInt32(r09);
            r4.writeInt32(this.id);
            r4.writeInt32(this.date);
            if (TLObject.hasFlag(this.flags, 262144) == false) goto L6;
            this.from_id.serializeToStream(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L8;
            this.fwd_from.serializeToStream(r4);
        L8:
            r4.writeInt32(this.expire_date);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L12;
            r4.writeString(this.caption);
        L12:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L14;
            Vector.serialize(r4, this.entities);
        L14:
            this.media.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L18;
            Vector.serialize(r4, this.media_areas);
        L18:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L21;
            Vector.serialize(r4, this.privacy);
        L21:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L24;
            this.views.serializeToStream(r4);
        L24:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L27;
            this.sent_reaction.serializeToStream(r4);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_storyItem_layer223 extends TL_storyItem {
        public static final int constructor = -302947087;

        public TL_storyItem_layer223() {
        }

        @Override // org.telegram.tgnet.tl.TL_stories.TL_storyItem, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.pinned = TLObject.hasFlag(r0, 32);
            this.isPublic = TLObject.hasFlag(this.flags, 128);
            this.close_friends = TLObject.hasFlag(this.flags, 256);
            this.min = TLObject.hasFlag(this.flags, 512);
            this.noforwards = TLObject.hasFlag(this.flags, 1024);
            this.edited = TLObject.hasFlag(this.flags, 2048);
            this.contacts = TLObject.hasFlag(this.flags, 4096);
            this.selected_contacts = TLObject.hasFlag(this.flags, 8192);
            this.out = TLObject.hasFlag(this.flags, 65536);
            this.id = r3.readInt32(r4);
            this.date = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 262144) == false) goto L6;
            this.from_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L8;
            this.fwd_from = StoryFwdHeader.TLdeserialize(r3, r3.readInt32(r4), r4);
        L8:
            this.expire_date = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L12;
            this.caption = r3.readString(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L14;
            this.entities = Vector.deserialize(r3, new MessagesStorage$$ExternalSyntheticLambda117(), r4);
        L14:
            this.media = TLRPC.MessageMedia.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L18;
            this.media_areas = Vector.deserialize(r3, new TL_stories$TL_storyItem$$ExternalSyntheticLambda0(), r4);
        L18:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L21;
            this.privacy = Vector.deserialize(r3, new TL_account$privacyRules$$ExternalSyntheticLambda0(), r4);
        L21:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L24;
            this.views = StoryViews.TLdeserialize(r3, r3.readInt32(r4), r4);
        L24:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L27;
            this.sent_reaction = TLRPC.Reaction.TLdeserialize(r3, r3.readInt32(r4), r4);
        L27:
            if (TLObject.hasFlag(this.flags, 524288) == false) goto L30;
            this.albums = Vector.deserializeInt(r3, r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_stories.TL_storyItem, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r5) {
            r5.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 32, this.pinned);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 128, this.isPublic);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 256, this.close_friends);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 512, this.min);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 1024, this.noforwards);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 2048, this.edited);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 4096, this.contacts);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, 8192, this.selected_contacts);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 65536, this.out);
            this.flags = r09;
            if (this.albums == null) goto L5;
            boolean r1 = true;
        L6:
            int r010 = TLObject.setFlag(r09, 524288, r1);
            this.flags = r010;
            r5.writeInt32(r010);
            r5.writeInt32(this.id);
            r5.writeInt32(this.date);
            if (TLObject.hasFlag(this.flags, 262144) == false) goto L10;
            this.from_id.serializeToStream(r5);
        L10:
            if (TLObject.hasFlag(this.flags, 131072) == false) goto L12;
            this.fwd_from.serializeToStream(r5);
        L12:
            r5.writeInt32(this.expire_date);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L16;
            r5.writeString(this.caption);
        L16:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L18;
            Vector.serialize(r5, this.entities);
        L18:
            this.media.serializeToStream(r5);
            if (TLObject.hasFlag(this.flags, 16384) == false) goto L22;
            Vector.serialize(r5, this.media_areas);
        L22:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L25;
            Vector.serialize(r5, this.privacy);
        L25:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L28;
            this.views.serializeToStream(r5);
        L28:
            if (TLObject.hasFlag(this.flags, 32768) == false) goto L31;
            this.sent_reaction.serializeToStream(r5);
        L31:
            if (TLObject.hasFlag(this.flags, 524288) == false) goto L34;
            Vector.serializeInt(r5, this.albums);
            return;
        L34:
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_storyReaction extends StoryReaction {
        public static final int constructor = 1620104917;
        public int date;
        public TLRPC.Reaction reaction;

        public TL_storyReaction() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.peer_id = TLRPC.Peer.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.date = r2.readInt32(r3);
            this.reaction = TLRPC.Reaction.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer_id.serializeToStream(r2);
            r2.writeInt32(this.date);
            this.reaction.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_storyReactionPublicForward extends StoryReaction {
        public static final int constructor = -1146411453;

        public TL_storyReactionPublicForward() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.message = TLRPC.Message.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.message.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_storyReactionPublicRepost extends StoryReaction {
        public static final int constructor = -808644845;

        public TL_storyReactionPublicRepost() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.peer_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            StoryItem r32 = StoryItem.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.story = r32;
            if (r32 == null) goto L6;
            r32.dialogId = DialogObject.getPeerDialogId(this.peer_id);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer_id.serializeToStream(r2);
            this.story.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_storyReactionsList extends TLObject {
        public static final int constructor = -1436583780;
        public ArrayList<TLRPC.Chat> chats;
        public int count;
        public int flags;
        public String next_offset;
        public ArrayList<StoryReaction> reactions;
        public ArrayList<TLRPC.User> users;

        public TL_storyReactionsList() {
            this.reactions = new ArrayList();
            this.chats = new ArrayList();
            this.users = new ArrayList();
        }

        public static TL_storyReactionsList TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-1436583780) == r3) goto L5;
            TL_storyReactionsList r0 = null;
        L7:
            return (TL_storyReactionsList) TLObject.TLdeserialize(TL_storyReactionsList.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_storyReactionsList();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.count = r3.readInt32(r4);
            this.reactions = Vector.deserialize(r3, new TL_stories$TL_storyReactionsList$$ExternalSyntheticLambda0(), r4);
            this.chats = Vector.deserialize(r3, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r4);
            this.users = Vector.deserialize(r3, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.next_offset = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt32(this.count);
            Vector.serialize(r3, this.reactions);
            Vector.serialize(r3, this.chats);
            Vector.serialize(r3, this.users);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeString(this.next_offset);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_storyView extends StoryView {
        public static final int constructor = -1329730875;

        public TL_storyView() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.blocked = TLObject.hasFlag(r0, 1);
            this.blocked_my_stories_from = TLObject.hasFlag(this.flags, 2);
            this.user_id = r3.readInt64(r4);
            this.date = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L6;
            this.reaction = TLRPC.Reaction.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.blocked);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.blocked_my_stories_from);
            this.flags = r02;
            r4.writeInt32(r02);
            r4.writeInt64(this.user_id);
            r4.writeInt32(this.date);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L6;
            this.reaction.serializeToStream(r4);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_storyViewPublicForward extends StoryView {
        public static final int constructor = -1870436597;

        public TL_storyViewPublicForward() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.blocked = TLObject.hasFlag(r0, 1);
            this.blocked_my_stories_from = TLObject.hasFlag(this.flags, 2);
            this.message = TLRPC.Message.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.blocked);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.blocked_my_stories_from);
            this.flags = r02;
            r4.writeInt32(r02);
            this.message.serializeToStream(r4);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_storyViewPublicRepost extends StoryView {
        public static final int constructor = -1116418231;

        public TL_storyViewPublicRepost() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.blocked = TLObject.hasFlag(r0, 1);
            this.blocked_my_stories_from = TLObject.hasFlag(this.flags, 2);
            this.peer_id = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.story = StoryItem.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.blocked);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.blocked_my_stories_from);
            this.flags = r02;
            r4.writeInt32(r02);
            this.peer_id.serializeToStream(r4);
            this.story.serializeToStream(r4);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_storyViews extends StoryViews {
        public static final int constructor = -1923523370;

        public TL_storyViews() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.has_viewers = TLObject.hasFlag(r0, 2);
            this.views_count = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L6;
            this.forwards_count = r3.readInt32(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L9;
            this.reactions = Vector.deserialize(r3, new TLRPC$TL_messageReactions$$ExternalSyntheticLambda0(), r4);
        L9:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L12;
            this.reactions_count = r3.readInt32(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 1) == false) goto L15;
            this.recent_viewers = Vector.deserializeLong(r3, r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            boolean r0 = this.has_viewers;
            int r1 = this.flags;
            if (r0 == false) goto L5;
            int r02 = r1 | 2;
        L6:
            this.flags = r02;
            r3.writeInt32(r02);
            r3.writeInt32(this.views_count);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L10;
            r3.writeInt32(this.forwards_count);
        L10:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L13;
            Vector.serialize(r3, this.reactions);
        L13:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L16;
            r3.writeInt32(this.reactions_count);
        L16:
            if (TLObject.hasFlag(this.flags, 1) == false) goto L19;
            Vector.serializeLong(r3, this.recent_viewers);
            return;
        L19:
            return;
        L5:
            r02 = r1 & (-3);
            goto L6
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_storyViewsList extends StoryViewsList {
        public static final int constructor = 1507299269;

        public TL_storyViewsList() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.count = r3.readInt32(r4);
            this.views_count = r3.readInt32(r4);
            this.forwards_count = r3.readInt32(r4);
            this.reactions_count = r3.readInt32(r4);
            this.views = Vector.deserialize(r3, new TL_stories$TL_storyViewsList$$ExternalSyntheticLambda0(), r4);
            this.chats = Vector.deserialize(r3, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r4);
            this.users = Vector.deserialize(r3, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.next_offset = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt32(this.count);
            r3.writeInt32(this.views_count);
            r3.writeInt32(this.forwards_count);
            r3.writeInt32(this.reactions_count);
            Vector.serialize(r3, this.views);
            Vector.serialize(r3, this.chats);
            Vector.serialize(r3, this.users);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeString(this.next_offset);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_storyViewsList_layer167 extends StoryViewsList {
        public static final int constructor = 1189722604;

        public TL_storyViewsList_layer167() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.count = r3.readInt32(r4);
            this.reactions_count = r3.readInt32(r4);
            this.views = Vector.deserialize(r3, new TL_stories$TL_storyViewsList$$ExternalSyntheticLambda0(), r4);
            this.users = Vector.deserialize(r3, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.next_offset = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt32(this.count);
            r3.writeInt32(this.reactions_count);
            Vector.serialize(r3, this.views);
            Vector.serialize(r3, this.users);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeString(this.next_offset);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_storyViews_layer160 extends StoryViews {
        public static final int constructor = -748199729;

        public TL_storyViews_layer160() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.views_count = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.recent_viewers = Vector.deserializeLong(r3, r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt32(this.views_count);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            Vector.serializeLong(r3, this.recent_viewers);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_storyViews_layer161 extends StoryViews {
        public static final int constructor = -968094825;

        public TL_storyViews_layer161() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.views_count = r3.readInt32(r4);
            this.reactions_count = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.recent_viewers = Vector.deserializeLong(r3, r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt32(this.views_count);
            r3.writeInt32(this.reactions_count);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            Vector.serializeLong(r3, this.recent_viewers);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_togglePinnedToTop extends TLObject {
        public static final int constructor = 187268763;
        public ArrayList<Integer> id;
        public TLRPC.InputPeer peer;

        public TL_togglePinnedToTop() {
            this.id = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            Vector.serializeInt(r2, this.id);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_updateAlbum extends TLMethod<TL_storyAlbum> {
        public static final int constructor = 1582455222;
        public ArrayList<Integer> add_stories;
        public int album_id;
        public ArrayList<Integer> delete_stories;
        public ArrayList<Integer> order;
        public TLRPC.InputPeer peer;
        public String title;

        public TL_updateAlbum() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TL_storyAlbum deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TL_storyAlbum.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r7) {
            r7.writeInt32(constructor);
            boolean r1 = false;
            if (this.title == null) goto L5;
            boolean r0 = true;
        L6:
            int r02 = TLObject.setFlag(0, 1, r0);
            if (this.delete_stories == null) goto L9;
            boolean r3 = true;
        L10:
            int r03 = TLObject.setFlag(r02, 2, r3);
            if (this.add_stories == null) goto L13;
            boolean r32 = true;
        L14:
            int r04 = TLObject.setFlag(r03, 4, r32);
            if (this.order == null) goto L17;
            r1 = true;
        L17:
            int r05 = TLObject.setFlag(r04, 8, r1);
            r7.writeInt32(r05);
            this.peer.serializeToStream(r7);
            r7.writeInt32(this.album_id);
            if (TLObject.hasFlag(r05, 1) == false) goto L21;
            r7.writeString(this.title);
        L21:
            if (TLObject.hasFlag(r05, 2) == false) goto L24;
            Vector.serializeInt(r7, this.delete_stories);
        L24:
            if (TLObject.hasFlag(r05, 4) == false) goto L27;
            Vector.serializeInt(r7, this.add_stories);
        L27:
            if (TLObject.hasFlag(r05, 8) == false) goto L30;
            Vector.serializeInt(r7, this.order);
            return;
        L30:
            return;
        L13:
            r32 = false;
            goto L14
        L9:
            r3 = false;
            goto L10
        L5:
            r0 = false;
            goto L6
        }
    }

    public static class TL_updateReadStories extends TLRPC.Update {
        public static final int constructor = -145845461;
        public int max_id;
        public TLRPC.Peer peer;

        public TL_updateReadStories() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.peer = TLRPC.Peer.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.max_id = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeInt32(this.max_id);
        }
    }

    public static class TL_updateStoriesStealthMode extends TLRPC.Update {
        public static final int constructor = 738741697;
        public TL_storiesStealthMode stealth_mode;

        public TL_updateStoriesStealthMode() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.stealth_mode = TL_storiesStealthMode.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.stealth_mode.serializeToStream(r2);
        }
    }

    public static class TL_updateStory extends TLRPC.Update {
        public static final int constructor = 1974712216;
        public TLRPC.Peer peer;
        public StoryItem story;

        public TL_updateStory() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.peer = TLRPC.Peer.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.story = StoryItem.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            this.story.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class canSendStoryCount extends TLObject {
        public static final int constructor = -1014513586;
        public int count_remains;

        public canSendStoryCount() {
        }

        public static canSendStoryCount TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-1014513586) == r3) goto L5;
            canSendStoryCount r0 = null;
        L7:
            return (canSendStoryCount) TLObject.TLdeserialize(canSendStoryCount.class, r0, r2, r3, r4);
        L5:
            r0 = new canSendStoryCount();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.count_remains = r1.readInt32(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.count_remains);
        }
    }

    /* loaded from: classes6.dex */
    public static abstract class stories_AllStories extends TLObject {
        public stories_AllStories() {
        }

        public static stories_AllStories TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != 291044926) goto L5;
            TLObject r0 = new TL_stories_allStoriesNotModified();
        L10:
            return (stories_AllStories) TLObject.TLdeserialize(stories_AllStories.class, r0, r2, r3, r4);
        L5:
            if (r3 == 1862033025) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new TL_stories_allStories();
            goto L10
        }
    }

    /* loaded from: classes6.dex */
    public static class togglePinned extends TLObject {
        public static final int constructor = -1703566865;
        public ArrayList<Integer> id;
        public TLRPC.InputPeer peer;
        public boolean pinned;

        public togglePinned() {
            this.id = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return Vector.TLDeserializeInt(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            Vector.serializeInt(r2, this.id);
            r2.writeBool(this.pinned);
        }
    }

    public TL_stories() {
    }
}
