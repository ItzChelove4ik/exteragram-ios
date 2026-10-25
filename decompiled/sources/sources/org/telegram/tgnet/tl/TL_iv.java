package org.telegram.tgnet.tl;

import android.graphics.Bitmap;
import com.exteragram.messenger.export.api.ExportRequests$InvokeWithTakeoutWrapper$$ExternalSyntheticLambda2;
import java.io.File;
import java.util.ArrayList;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLMethod;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.TLRPC$TL_inputMediaUploadedDocument$$ExternalSyntheticLambda0;
import org.telegram.tgnet.TLRPC$TL_inputPrivacyValueAllowUsers$$ExternalSyntheticLambda0;
import org.telegram.tgnet.TLRPC$TL_photos_photos$$ExternalSyntheticLambda0;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_keyboard;

/* loaded from: classes6.dex */
public class TL_iv {

    /* loaded from: classes.dex */
    public static abstract class Page extends TLObject {
        public ArrayList<PageBlock> blocks;
        public ArrayList<TLRPC.Document> documents;
        public int flags;
        public File local;
        public boolean part;
        public ArrayList<TLRPC.Photo> photos;
        public boolean rtl;
        public String url;
        public boolean v2;
        public int views;
        public boolean web;

        public Page() {
            this.blocks = new ArrayList();
            this.photos = new ArrayList();
            this.documents = new ArrayList();
        }

        public static Page TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            return (Page) TLObject.TLdeserialize(Page.class, fromConstructor(r3), r2, r3, r4);
        }

        private static Page fromConstructor(int r0) {
            switch(r0) {
                case -1913754556: goto L18;
                case -1908433218: goto L16;
                case -1738178803: goto L14;
                case -1366746132: goto L12;
                case -677274263: goto L10;
                case -241590104: goto L8;
                case 1433323434: goto L6;
                default: goto L3;
            };
        L3:
            return null;
        L6:
            return new TL_pageFull_layer82();
        L8:
            return new TL_page_layer88();
        L10:
            return new TL_pageFull_layer67();
        L12:
            return new TL_page_layer110();
        L14:
            return new TL_page();
        L16:
            return new TL_pagePart_layer82();
        L18:
            return new TL_pagePart_layer67();
        }
    }

    /* loaded from: classes.dex */
    public static abstract class PageBlock extends TLObject {
        public boolean bottom;
        public int cachedHeight;
        public int cachedWidth;
        public PageCaption caption;
        public boolean first;
        public int groupId;
        public int level;
        public int mid;
        public int quoteLevels;
        public RichText text;
        public TLRPC.PhotoSize thumb;
        public TLObject thumbObject;

        public PageBlock() {
        }

        public static PageBlock TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            return (PageBlock) TLObject.TLdeserialize(PageBlock.class, fromConstructor(r3), r2, r3, r4);
        }

        private static PageBlock fromConstructor(int r0) {
            switch(r0) {
                case -2143067670: goto L110;
                case -1879401953: goto L108;
                case -1702174239: goto L106;
                case -1538310410: goto L104;
                case -1468953147: goto L102;
                case -1254983893: goto L100;
                case -1162877472: goto L98;
                case -1157691601: goto L96;
                case -1085412734: goto L94;
                case -1076861716: goto L92;
                case -1066346178: goto L90;
                case -840826671: goto L88;
                case -837994576: goto L86;
                case -650782469: goto L84;
                case -640214938: goto L82;
                case -618614392: goto L80;
                case -608277398: goto L78;
                case -454524911: goto L76;
                case -372860542: goto L74;
                case -283684427: goto L72;
                case -248793375: goto L70;
                case -229005301: goto L68;
                case 52401552: goto L66;
                case 145955919: goto L64;
                case 158018284: goto L62;
                case 242108356: goto L60;
                case 319588707: goto L58;
                case 324435594: goto L56;
                case 370236054: goto L54;
                case 391759200: goto L52;
                case 504660880: goto L50;
                case 534181569: goto L48;
                case 641563686: goto L46;
                case 690781161: goto L44;
                case 834148991: goto L42;
                case 955923363: goto L40;
                case 972174080: goto L38;
                case 978896884: goto L36;
                case 1009361890: goto L34;
                case 1029399794: goto L32;
                case 1182402406: goto L30;
                case 1216809369: goto L28;
                case 1329878739: goto L26;
                case 1464557951: goto L24;
                case 1493699616: goto L22;
                case 1705048653: goto L20;
                case 1724999435: goto L18;
                case 1743204781: goto L16;
                case 1747599785: goto L14;
                case 1835270936: goto L12;
                case 1890305021: goto L10;
                case 1987480557: goto L8;
                case 2089805750: goto L6;
                default: goto L3;
            };
        L3:
            return null;
        L6:
            return new pageBlockVideo();
        L8:
            return new pageBlockDetails();
        L10:
            return new pageBlockTitle();
        L12:
            return new pageBlockButtonRow();
        L14:
            return new pageBlockHeading6();
        L16:
            return new pageBlockHeading3();
        L18:
            return new pageBlockBlockquote();
        L20:
            return new pageBlockCollage();
        L22:
            return new pageBlockMath();
        L24:
            return new inputPageBlockMap();
        L26:
            return new pageBlockPullquote();
        L28:
            return new pageBlockFooter();
        L30:
            return new pageBlockParagraph();
        L32:
            return new pageBlockAuthorDate_layer60();
        L34:
            return new pageBlockThinking();
        L36:
            return new pageBlockList_layer82();
        L38:
            return new pageBlockCover();
        L40:
            return new pageBlockDocument();
        L42:
            return new pageBlockAudio_layer82();
        L44:
            return new pageBlockEmbedPost_layer82();
        L46:
            return new pageBlockBlockquote_layer228();
        L48:
            return new pageBlockOrderedList();
        L50:
            return new pageBlockKicker();
        L52:
            return new pageBlockPhoto();
        L54:
            return new pageBlockRelatedArticles();
        L56:
            return new pageBlockUnsupported();
        L58:
            return new pageBlockSlideshow_layer82();
        L60:
            return new pageBlockBlockquoteBlocks();
        L62:
            return new pageBlockHeading2();
        L64:
            return new pageBlockCollage_layer82();
        L66:
            return new pageBlockSlideshow();
        L68:
            return new pageBlockEmbedPost();
        L70:
            return new pageBlockSubheader();
        L72:
            return new pageBlockChannel();
        L74:
            return new pageBlockPhoto_layer82();
        L76:
            return new pageBlockList();
        L78:
            return new pageBlockHeading5();
        L80:
            return new pageBlockDivider();
        L82:
            return new pageBlockVideo_layer82();
        L84:
            return new pageBlockEmbed_layer60();
        L86:
            return new pageBlockAnchor();
        L88:
            return new pageBlockEmbed_layer82();
        L90:
            return new pageBlockPreformatted();
        L92:
            return new pageBlockHeader();
        L94:
            return new pageBlockTable();
        L96:
            return new pageBlockHeading1();
        L98:
            return new pageBlockAuthorDate();
        L100:
            return new pageBlockHeading4();
        L102:
            return new pageBlockEmbed();
        L104:
            return new pageBlockMap();
        L106:
            return new pageBlockOrderedList_layer226();
        L108:
            return new pageBlockSubtitle();
        L110:
            return new pageBlockAudio();
        }
    }

    /* loaded from: classes.dex */
    public static class PageCaption extends TLObject {
        public static final int constructor = 1869903447;
        public RichText credit;
        public RichText text;

        public PageCaption() {
        }

        public static PageCaption TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (1869903447 == r3) goto L5;
            PageCaption r0 = null;
        L7:
            return (PageCaption) TLObject.TLdeserialize(PageCaption.class, r0, r2, r3, r4);
        L5:
            r0 = new PageCaption();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.credit = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
            this.credit.serializeToStream(r2);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class PageListItem extends TLObject {
        public boolean checkbox;
        public boolean checked;
        public int flags;

        public PageListItem() {
        }

        public static PageListItem TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            switch(r3) {
                case -1188055347: goto L7;
                case 635466748: goto L6;
                case 794323004: goto L5;
                case 1674209194: goto L4;
                default: goto L3;
            };
        L3:
            TLObject r0 = null;
        L9:
            return (PageListItem) TLObject.TLdeserialize(PageListItem.class, r0, r2, r3, r4);
        L4:
            r0 = new TL_pageListItemBlocks();
            goto L9
        L5:
            r0 = new TL_pageListItemText();
            goto L9
        L6:
            r0 = new TL_pageListItemBlocks_226();
            goto L9
        L7:
            r0 = new TL_pageListItemText_layer226();
            goto L9
        }
    }

    public static abstract class PageListOrderedItem extends TLObject {
        public boolean checkbox;
        public boolean checked;
        public int flags;
        public String num;
        public String type;
        public int value;

        public PageListOrderedItem() {
        }

        public static PageListOrderedItem TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            switch(r3) {
                case -1879910928: goto L7;
                case -1730311882: goto L6;
                case 352522633: goto L5;
                case 1577484359: goto L4;
                default: goto L3;
            };
        L3:
            TLObject r0 = null;
        L9:
            return (PageListOrderedItem) TLObject.TLdeserialize(PageListOrderedItem.class, r0, r2, r3, r4);
        L4:
            r0 = new TL_pageListOrderedItemText_layer226();
            goto L9
        L5:
            r0 = new TL_pageListOrderedItemText();
            goto L9
        L6:
            r0 = new TL_pageListOrderedItemBlocks_layer226();
            goto L9
        L7:
            r0 = new TL_pageListOrderedItemBlocks();
            goto L9
        }
    }

    /* loaded from: classes.dex */
    public static class RichMessage extends TLObject {
        public static final int constructor = -1158439541;
        public ArrayList<PageBlock> blocks;
        public ArrayList<TLRPC.Document> documents;
        public int flags;
        public boolean part;
        public ArrayList<TLRPC.Photo> photos;
        public boolean rtl;

        public RichMessage() {
            this.blocks = new ArrayList();
            this.photos = new ArrayList();
            this.documents = new ArrayList();
        }

        public static RichMessage TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == (-1158439541)) goto L5;
            RichMessage r0 = null;
        L7:
            return (RichMessage) TLObject.TLdeserialize(RichMessage.class, r0, r2, r3, r4);
        L5:
            r0 = new RichMessage();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.rtl = TLObject.hasFlag(r0, 1);
            this.part = TLObject.hasFlag(this.flags, 2);
            this.blocks = Vector.deserialize(r3, new TL_iv$RichMessage$$ExternalSyntheticLambda0(), r4);
            this.photos = Vector.deserialize(r3, new TLRPC$TL_photos_photos$$ExternalSyntheticLambda0(), r4);
            this.documents = Vector.deserialize(r3, new ExportRequests$InvokeWithTakeoutWrapper$$ExternalSyntheticLambda2(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.rtl);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.part);
            this.flags = r02;
            r4.writeInt32(r02);
            Vector.serialize(r4, this.blocks);
            Vector.serialize(r4, this.photos);
            Vector.serialize(r4, this.documents);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class RichText extends TLObject {
        public String email;
        public RichText parentRichText;
        public RichText text;
        public ArrayList<RichText> texts;
        public String url;
        public long webpage_id;

        public RichText() {
            this.texts = new ArrayList();
        }

        public static RichText TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            return (RichText) TLObject.TLdeserialize(RichText.class, fromConstructor(r3), r2, r3, r4);
        }

        private static RichText fromConstructor(int r0) {
            switch(r0) {
                case -1769551024: goto L66;
                case -1678197867: goto L64;
                case -1657885545: goto L62;
                case -1570679104: goto L60;
                case -1514906069: goto L58;
                case -1402305622: goto L56;
                case -1345872682: goto L54;
                case -1185513171: goto L52;
                case -1054465340: goto L50;
                case -984177571: goto L48;
                case -939827711: goto L46;
                case -853225660: goto L44;
                case -653089380: goto L42;
                case -599948721: goto L40;
                case -564523562: goto L38;
                case -311786236: goto L36;
                case 27917308: goto L34;
                case 50276819: goto L32;
                case 55281185: goto L30;
                case 136105807: goto L28;
                case 483104362: goto L26;
                case 616720265: goto L24;
                case 894777186: goto L22;
                case 1009288385: goto L20;
                case 1277844834: goto L18;
                case 1368728810: goto L16;
                case 1730456516: goto L14;
                case 1816074681: goto L12;
                case 1950782688: goto L10;
                case 2073958401: goto L8;
                case 2120376535: goto L6;
                default: goto L3;
            };
        L3:
            return null;
        L6:
            return new textConcat();
        L8:
            return new textCashtag();
        L10:
            return new textPlain();
        L12:
            return new textFixed();
        L14:
            return new textBold();
        L16:
            return new textHashtag();
        L18:
            return new textSpoiler();
        L20:
            return new textUrl();
        L22:
            return new textAnchor();
        L24:
            return new textAutoPhone();
        L26:
            return new textPhone();
        L28:
            return new textImage();
        L30:
            return new textMarked();
        L32:
            return new textBotCommand();
        L34:
            return new textMentionName();
        L36:
            return new textSubscript();
        L38:
            return new textEmail();
        L40:
            return new textEmpty();
        L42:
            return new textItalic();
        L44:
            return new textMention();
        L46:
            return new textSuperscript();
        L48:
            return new textAutoEmail();
        L50:
            return new textUnderline();
        L52:
            return new textBankCard();
        L54:
            return new textButton();
        L56:
            return new textAutoUrl();
        L58:
            return new textDate();
        L60:
            return new textCustomEmoji();
        L62:
            return new textMath();
        L64:
            return new textStrike();
        L66:
            return new textDiff();
        }
    }

    /* loaded from: classes.dex */
    public static class TL_inputRichMessage extends TLObject {
        public static final int constructor = -456898052;
        public ArrayList<PageBlock> blocks;
        public ArrayList<TLRPC.InputDocument> documents;
        public int flags;
        public boolean noautolink;
        public ArrayList<TLRPC.InputPhoto> photos;
        public boolean rtl;
        public ArrayList<TLRPC.InputUser> users;

        public TL_inputRichMessage() {
            this.blocks = new ArrayList();
            this.photos = new ArrayList();
            this.documents = new ArrayList();
            this.users = new ArrayList();
        }

        public static TL_inputRichMessage TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == (-456898052)) goto L5;
            TL_inputRichMessage r0 = null;
        L7:
            return (TL_inputRichMessage) TLObject.TLdeserialize(TL_inputRichMessage.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_inputRichMessage();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.rtl = TLObject.hasFlag(r0, 1);
            this.noautolink = TLObject.hasFlag(this.flags, 2);
            this.blocks = Vector.deserialize(r3, new TL_iv$RichMessage$$ExternalSyntheticLambda0(), r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L6;
            this.photos = Vector.deserialize(r3, new TL_iv$TL_inputRichMessage$$ExternalSyntheticLambda0(), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L9;
            this.documents = Vector.deserialize(r3, new TLRPC$TL_inputMediaUploadedDocument$$ExternalSyntheticLambda0(), r4);
        L9:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L12;
            this.users = Vector.deserialize(r3, new TLRPC$TL_inputPrivacyValueAllowUsers$$ExternalSyntheticLambda0(), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.rtl);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.noautolink);
            this.flags = r02;
            r4.writeInt32(r02);
            Vector.serialize(r4, this.blocks);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L6;
            Vector.serialize(r4, this.photos);
        L6:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L9;
            Vector.serialize(r4, this.documents);
        L9:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L12;
            Vector.serialize(r4, this.users);
            return;
        }
    }

    /* loaded from: classes.dex */
    public static class TL_page extends Page {
        public static final int constructor = -1738178803;

        public TL_page() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.part = TLObject.hasFlag(r0, 1);
            this.rtl = TLObject.hasFlag(this.flags, 2);
            this.v2 = TLObject.hasFlag(this.flags, 4);
            this.url = r3.readString(r4);
            this.blocks = Vector.deserialize(r3, new TL_iv$RichMessage$$ExternalSyntheticLambda0(), r4);
            this.photos = Vector.deserialize(r3, new TLRPC$TL_photos_photos$$ExternalSyntheticLambda0(), r4);
            this.documents = Vector.deserialize(r3, new ExportRequests$InvokeWithTakeoutWrapper$$ExternalSyntheticLambda2(), r4);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L6;
            this.views = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.part);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.rtl);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.v2);
            this.flags = r03;
            r4.writeInt32(r03);
            r4.writeString(this.url);
            Vector.serialize(r4, this.blocks);
            Vector.serialize(r4, this.photos);
            Vector.serialize(r4, this.documents);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L6;
            r4.writeInt32(this.views);
            return;
        }
    }

    public static class TL_pageFull_layer67 extends TL_page {
        public static final int constructor = -677274263;

        public TL_pageFull_layer67() {
        }

        @Override // org.telegram.tgnet.tl.TL_iv.TL_page, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.blocks = Vector.deserialize(r2, new TL_iv$RichMessage$$ExternalSyntheticLambda0(), r3);
            this.photos = Vector.deserialize(r2, new TLRPC$TL_photos_photos$$ExternalSyntheticLambda0(), r3);
            this.documents = Vector.deserialize(r2, new ExportRequests$InvokeWithTakeoutWrapper$$ExternalSyntheticLambda2(), r3);
        }

        @Override // org.telegram.tgnet.tl.TL_iv.TL_page, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.blocks);
            Vector.serialize(r2, this.photos);
            Vector.serialize(r2, this.documents);
        }
    }

    public static class TL_pageFull_layer82 extends TL_page {
        public static final int constructor = 1433323434;

        public TL_pageFull_layer82() {
        }

        @Override // org.telegram.tgnet.tl.TL_iv.TL_page, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.blocks = Vector.deserialize(r2, new TL_iv$RichMessage$$ExternalSyntheticLambda0(), r3);
            this.photos = Vector.deserialize(r2, new TLRPC$TL_photos_photos$$ExternalSyntheticLambda0(), r3);
            this.documents = Vector.deserialize(r2, new ExportRequests$InvokeWithTakeoutWrapper$$ExternalSyntheticLambda2(), r3);
        }

        @Override // org.telegram.tgnet.tl.TL_iv.TL_page, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.blocks);
            Vector.serialize(r2, this.photos);
            Vector.serialize(r2, this.documents);
        }
    }

    /* loaded from: classes.dex */
    public static class TL_pageListItemBlocks extends PageListItem {
        public static final int constructor = 1674209194;
        public ArrayList<PageBlock> blocks;

        public TL_pageListItemBlocks() {
            this.blocks = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.checkbox = TLObject.hasFlag(r0, 1);
            this.checked = TLObject.hasFlag(this.flags, 2);
            this.blocks = Vector.deserialize(r3, new TL_iv$RichMessage$$ExternalSyntheticLambda0(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.checkbox);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.checked);
            this.flags = r02;
            r4.writeInt32(r02);
            Vector.serialize(r4, this.blocks);
        }
    }

    public static class TL_pageListItemBlocks_226 extends TL_pageListItemBlocks {
        public static final int constructor = 635466748;

        public TL_pageListItemBlocks_226() {
        }

        @Override // org.telegram.tgnet.tl.TL_iv.TL_pageListItemBlocks, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.blocks = Vector.deserialize(r2, new TL_iv$RichMessage$$ExternalSyntheticLambda0(), r3);
        }

        @Override // org.telegram.tgnet.tl.TL_iv.TL_pageListItemBlocks, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.blocks);
        }
    }

    /* loaded from: classes.dex */
    public static class TL_pageListItemText extends PageListItem {
        public static final int constructor = 794323004;
        public RichText text;

        public TL_pageListItemText() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.checkbox = TLObject.hasFlag(r0, 1);
            this.checked = TLObject.hasFlag(this.flags, 2);
            this.text = RichText.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.checkbox);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.checked);
            this.flags = r02;
            r4.writeInt32(r02);
            this.text.serializeToStream(r4);
        }
    }

    public static class TL_pageListItemText_layer226 extends TL_pageListItemText {
        public static final int constructor = -1188055347;

        public TL_pageListItemText_layer226() {
        }

        @Override // org.telegram.tgnet.tl.TL_iv.TL_pageListItemText, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.tl.TL_iv.TL_pageListItemText, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    public static class TL_pageListOrderedItemBlocks extends PageListOrderedItem {
        public static final int constructor = -1879910928;
        public ArrayList<PageBlock> blocks;

        public TL_pageListOrderedItemBlocks() {
            this.blocks = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.checkbox = TLObject.hasFlag(r0, 1);
            this.checked = TLObject.hasFlag(this.flags, 2);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L5;
            this.num = r3.readString(r4);
        L5:
            this.blocks = Vector.deserialize(r3, new TL_iv$RichMessage$$ExternalSyntheticLambda0(), r4);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L9;
            this.value = r3.readInt32(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L12;
            this.type = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r6) {
            r6.writeInt32(constructor);
            boolean r2 = true;
            int r0 = TLObject.setFlag(this.flags, 1, this.checkbox);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.checked);
            this.flags = r02;
            if (this.num == null) goto L5;
            boolean r1 = true;
        L6:
            int r03 = TLObject.setFlag(r02, 4, r1);
            this.flags = r03;
            if (this.type != null) goto L10;
            r2 = false;
        L10:
            int r04 = TLObject.setFlag(r03, 16, r2);
            this.flags = r04;
            r6.writeInt32(r04);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L13;
            r6.writeString(this.num);
        L13:
            Vector.serialize(r6, this.blocks);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L17;
            r6.writeInt32(this.value);
        L17:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L20;
            r6.writeString(this.type);
            return;
        L20:
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_pageListOrderedItemBlocks_layer226 extends TL_pageListOrderedItemBlocks {
        public static final int constructor = -1730311882;

        public TL_pageListOrderedItemBlocks_layer226() {
        }

        @Override // org.telegram.tgnet.tl.TL_iv.TL_pageListOrderedItemBlocks, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.num = r2.readString(r3);
            this.blocks = Vector.deserialize(r2, new TL_iv$RichMessage$$ExternalSyntheticLambda0(), r3);
        }

        @Override // org.telegram.tgnet.tl.TL_iv.TL_pageListOrderedItemBlocks, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.num);
            Vector.serialize(r2, this.blocks);
        }
    }

    public static class TL_pageListOrderedItemText extends PageListOrderedItem {
        public static final int constructor = 352522633;
        public RichText text;

        public TL_pageListOrderedItemText() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.checkbox = TLObject.hasFlag(r0, 1);
            this.checked = TLObject.hasFlag(this.flags, 2);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L5;
            this.num = r3.readString(r4);
        L5:
            this.text = RichText.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L9;
            this.value = r3.readInt32(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L12;
            this.type = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r6) {
            r6.writeInt32(constructor);
            boolean r2 = true;
            int r0 = TLObject.setFlag(this.flags, 1, this.checkbox);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.checked);
            this.flags = r02;
            if (this.num == null) goto L5;
            boolean r1 = true;
        L6:
            int r03 = TLObject.setFlag(r02, 4, r1);
            this.flags = r03;
            if (this.type != null) goto L10;
            r2 = false;
        L10:
            int r04 = TLObject.setFlag(r03, 16, r2);
            this.flags = r04;
            r6.writeInt32(r04);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L13;
            r6.writeString(this.num);
        L13:
            this.text.serializeToStream(r6);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L17;
            r6.writeInt32(this.value);
        L17:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L20;
            r6.writeString(this.type);
            return;
        L20:
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_pageListOrderedItemText_layer226 extends TL_pageListOrderedItemText {
        public static final int constructor = 1577484359;

        public TL_pageListOrderedItemText_layer226() {
        }

        @Override // org.telegram.tgnet.tl.TL_iv.TL_pageListOrderedItemText, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.num = r2.readString(r3);
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.tl.TL_iv.TL_pageListOrderedItemText, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.num);
            this.text.serializeToStream(r2);
        }
    }

    public static class TL_pagePart_layer67 extends TL_pagePart_layer82 {
        public static final int constructor = -1913754556;

        public TL_pagePart_layer67() {
        }

        @Override // org.telegram.tgnet.tl.TL_iv.TL_pagePart_layer82, org.telegram.tgnet.tl.TL_iv.TL_page, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.part = true;
            this.blocks = Vector.deserialize(r2, new TL_iv$RichMessage$$ExternalSyntheticLambda0(), r3);
            this.photos = Vector.deserialize(r2, new TLRPC$TL_photos_photos$$ExternalSyntheticLambda0(), r3);
            this.documents = Vector.deserialize(r2, new ExportRequests$InvokeWithTakeoutWrapper$$ExternalSyntheticLambda2(), r3);
        }

        @Override // org.telegram.tgnet.tl.TL_iv.TL_pagePart_layer82, org.telegram.tgnet.tl.TL_iv.TL_page, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.blocks);
            Vector.serialize(r2, this.photos);
            Vector.serialize(r2, this.documents);
        }
    }

    public static class TL_pagePart_layer82 extends TL_page {
        public static final int constructor = -1908433218;

        public TL_pagePart_layer82() {
        }

        @Override // org.telegram.tgnet.tl.TL_iv.TL_page, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.part = true;
            this.blocks = Vector.deserialize(r2, new TL_iv$RichMessage$$ExternalSyntheticLambda0(), r3);
            this.photos = Vector.deserialize(r2, new TLRPC$TL_photos_photos$$ExternalSyntheticLambda0(), r3);
            this.documents = Vector.deserialize(r2, new ExportRequests$InvokeWithTakeoutWrapper$$ExternalSyntheticLambda2(), r3);
        }

        @Override // org.telegram.tgnet.tl.TL_iv.TL_page, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.blocks);
            Vector.serialize(r2, this.photos);
            Vector.serialize(r2, this.documents);
        }
    }

    public static class TL_page_layer110 extends TL_page {
        public static final int constructor = -1366746132;

        public TL_page_layer110() {
        }

        @Override // org.telegram.tgnet.tl.TL_iv.TL_page, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.part = TLObject.hasFlag(r0, 1);
            this.rtl = TLObject.hasFlag(this.flags, 2);
            this.url = r3.readString(r4);
            this.blocks = Vector.deserialize(r3, new TL_iv$RichMessage$$ExternalSyntheticLambda0(), r4);
            this.photos = Vector.deserialize(r3, new TLRPC$TL_photos_photos$$ExternalSyntheticLambda0(), r4);
            this.documents = Vector.deserialize(r3, new ExportRequests$InvokeWithTakeoutWrapper$$ExternalSyntheticLambda2(), r4);
        }

        @Override // org.telegram.tgnet.tl.TL_iv.TL_page, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.part);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.rtl);
            this.flags = r02;
            r4.writeInt32(r02);
            r4.writeString(this.url);
            Vector.serialize(r4, this.blocks);
            Vector.serialize(r4, this.photos);
            Vector.serialize(r4, this.documents);
        }
    }

    public static class TL_page_layer88 extends TL_page {
        public static final int constructor = -241590104;

        public TL_page_layer88() {
        }

        @Override // org.telegram.tgnet.tl.TL_iv.TL_page, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.part = TLObject.hasFlag(r0, 1);
            this.rtl = TLObject.hasFlag(this.flags, 2);
            this.v2 = TLObject.hasFlag(this.flags, 4);
            this.blocks = Vector.deserialize(r3, new TL_iv$RichMessage$$ExternalSyntheticLambda0(), r4);
            this.photos = Vector.deserialize(r3, new TLRPC$TL_photos_photos$$ExternalSyntheticLambda0(), r4);
            this.documents = Vector.deserialize(r3, new ExportRequests$InvokeWithTakeoutWrapper$$ExternalSyntheticLambda2(), r4);
        }

        @Override // org.telegram.tgnet.tl.TL_iv.TL_page, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.part);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.rtl);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.v2);
            this.flags = r03;
            r4.writeInt32(r03);
            Vector.serialize(r4, this.blocks);
            Vector.serialize(r4, this.photos);
            Vector.serialize(r4, this.documents);
        }
    }

    public static class getRichMessage extends TLMethod<TLRPC.messages_Messages> {
        public static final int constructor = 1343580623;
        public int id;
        public TLRPC.InputPeer peer;

        public getRichMessage() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TLRPC.messages_Messages deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.messages_Messages.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeInt32(this.id);
        }
    }

    public static class inputPageBlockMap extends PageBlock {
        public static final int constructor = 1464557951;
        public TLRPC.InputGeoPoint geo;
        public int h;
        public int w;
        public int zoom;

        public inputPageBlockMap() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.geo = TLRPC.InputGeoPoint.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.zoom = r2.readInt32(r3);
            this.w = r2.readInt32(r3);
            this.h = r2.readInt32(r3);
            this.caption = PageCaption.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.geo.serializeToStream(r2);
            r2.writeInt32(this.zoom);
            r2.writeInt32(this.w);
            r2.writeInt32(this.h);
            this.caption.serializeToStream(r2);
        }
    }

    /* loaded from: classes.dex */
    public static class pageBlockAnchor extends PageBlock {
        public static final int constructor = -837994576;
        public String name;

        public pageBlockAnchor() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.name = r1.readString(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.name);
        }
    }

    public static class pageBlockAudio extends PageBlock {
        public static final int constructor = -2143067670;
        public long audio_id;

        public pageBlockAudio() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.audio_id = r3.readInt64(r4);
            this.caption = PageCaption.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.audio_id);
            this.caption.serializeToStream(r3);
        }
    }

    public static class pageBlockAudio_layer82 extends pageBlockAudio {
        public static final int constructor = 834148991;

        public pageBlockAudio_layer82() {
        }

        @Override // org.telegram.tgnet.tl.TL_iv.pageBlockAudio, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.audio_id = r3.readInt64(r4);
            PageCaption r0 = new PageCaption();
            this.caption = r0;
            r0.text = RichText.TLdeserialize(r3, r3.readInt32(r4), r4);
            PageCaption r2 = this.caption;
            r2.credit = new textEmpty();
        }

        @Override // org.telegram.tgnet.tl.TL_iv.pageBlockAudio, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.audio_id);
            this.caption.text.serializeToStream(r3);
        }
    }

    /* loaded from: classes.dex */
    public static class pageBlockAuthorDate extends PageBlock {
        public static final int constructor = -1162877472;
        public RichText author;
        public int published_date;

        public pageBlockAuthorDate() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.author = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.published_date = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.author.serializeToStream(r2);
            r2.writeInt32(this.published_date);
        }
    }

    public static class pageBlockAuthorDate_layer60 extends pageBlockAuthorDate {
        public static final int constructor = 1029399794;

        public pageBlockAuthorDate_layer60() {
        }

        @Override // org.telegram.tgnet.tl.TL_iv.pageBlockAuthorDate, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            String r0 = r3.readString(r4);
            textPlain r1 = new textPlain();
            this.author = r1;
            r1.text = r0;
            this.published_date = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.tl.TL_iv.pageBlockAuthorDate, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(((textPlain) this.author).text);
            r2.writeInt32(this.published_date);
        }
    }

    /* loaded from: classes.dex */
    public static class pageBlockBlockquote extends PageBlock {
        public static final int constructor = 1724999435;
        public RichText caption;
        public boolean collapsed;
        public int flags;

        public pageBlockBlockquote() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.collapsed = TLObject.hasFlag(r0, 1);
            this.text = RichText.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.caption = RichText.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.collapsed);
            this.flags = r0;
            r4.writeInt32(r0);
            this.text.serializeToStream(r4);
            this.caption.serializeToStream(r4);
        }
    }

    public static class pageBlockBlockquoteBlocks extends PageBlock {
        public static final int constructor = 242108356;
        public ArrayList<PageBlock> blocks;
        public RichText caption;

        public pageBlockBlockquoteBlocks() {
            this.blocks = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.blocks = Vector.deserialize(r2, new TL_iv$RichMessage$$ExternalSyntheticLambda0(), r3);
            this.caption = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.blocks);
            this.caption.serializeToStream(r2);
        }
    }

    public static class pageBlockBlockquote_layer228 extends pageBlockBlockquote {
        public static final int constructor = 641563686;

        public pageBlockBlockquote_layer228() {
        }

        @Override // org.telegram.tgnet.tl.TL_iv.pageBlockBlockquote, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.caption = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.tl.TL_iv.pageBlockBlockquote, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
            this.caption.serializeToStream(r2);
        }
    }

    public static class pageBlockButtonRow extends PageBlock {
        public static final int constructor = 1835270936;
        public boolean align_center;
        public boolean align_left;
        public boolean align_right;
        public ArrayList<TL_keyboard.PageButton> buttons;
        public int flags;

        public pageBlockButtonRow() {
            this.buttons = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.align_left = TLObject.hasFlag(r0, 1);
            this.align_center = TLObject.hasFlag(this.flags, 2);
            this.align_right = TLObject.hasFlag(this.flags, 4);
            this.buttons = Vector.deserialize(r3, new TL_iv$pageBlockButtonRow$$ExternalSyntheticLambda0(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.align_left);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.align_center);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.align_right);
            this.flags = r03;
            r4.writeInt32(r03);
            Vector.serialize(r4, this.buttons);
        }
    }

    /* loaded from: classes.dex */
    public static class pageBlockChannel extends PageBlock {
        public static final int constructor = -283684427;
        public TLRPC.Chat channel;

        public pageBlockChannel() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.channel = TLRPC.Chat.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.channel.serializeToStream(r2);
        }
    }

    public static class pageBlockCollage extends PageBlock {
        public static final int constructor = 1705048653;
        public ArrayList<PageBlock> items;

        public pageBlockCollage() {
            this.items = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.items = Vector.deserialize(r2, new TL_iv$RichMessage$$ExternalSyntheticLambda0(), r3);
            this.caption = PageCaption.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.items);
            this.caption.serializeToStream(r2);
        }
    }

    public static class pageBlockCollage_layer82 extends pageBlockCollage {
        public static final int constructor = 145955919;

        public pageBlockCollage_layer82() {
        }

        @Override // org.telegram.tgnet.tl.TL_iv.pageBlockCollage, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.items = Vector.deserialize(r3, new TL_iv$RichMessage$$ExternalSyntheticLambda0(), r4);
            PageCaption r0 = new PageCaption();
            this.caption = r0;
            r0.text = RichText.TLdeserialize(r3, r3.readInt32(r4), r4);
            PageCaption r2 = this.caption;
            r2.credit = new textEmpty();
        }

        @Override // org.telegram.tgnet.tl.TL_iv.pageBlockCollage, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.items);
            this.caption.text.serializeToStream(r2);
        }
    }

    /* loaded from: classes.dex */
    public static class pageBlockCover extends PageBlock {
        public static final int constructor = 972174080;
        public PageBlock cover;

        public pageBlockCover() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.cover = PageBlock.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.cover.serializeToStream(r2);
        }
    }

    public static class pageBlockDetails extends PageBlock {
        public static final int constructor = 1987480557;
        public ArrayList<PageBlock> blocks;
        public int flags;
        public boolean open;
        public RichText title;

        public pageBlockDetails() {
            this.blocks = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.open = TLObject.hasFlag(r0, 1);
            this.blocks = Vector.deserialize(r3, new TL_iv$RichMessage$$ExternalSyntheticLambda0(), r4);
            this.title = RichText.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.open);
            this.flags = r0;
            r4.writeInt32(r0);
            Vector.serialize(r4, this.blocks);
            this.title.serializeToStream(r4);
        }
    }

    /* loaded from: classes.dex */
    public static class pageBlockDivider extends PageBlock {
        public static final int constructor = -618614392;

        public pageBlockDivider() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class pageBlockDocument extends PageBlock {
        public static final int constructor = 955923363;
        public long document_id;

        public pageBlockDocument() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.document_id = r3.readInt64(r4);
            this.caption = PageCaption.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.document_id);
            this.caption.serializeToStream(r3);
        }
    }

    public static class pageBlockEmbed extends PageBlock {
        public static final int constructor = -1468953147;
        public boolean allow_scrolling;
        public int flags;
        public boolean full_width;
        public int h;
        public String html;
        public long poster_photo_id;
        public String url;
        public int w;

        public pageBlockEmbed() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.full_width = TLObject.hasFlag(r0, 1);
            this.allow_scrolling = TLObject.hasFlag(this.flags, 8);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L6;
            this.url = r3.readString(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.html = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L12;
            this.poster_photo_id = r3.readInt64(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            this.w = r3.readInt32(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L17;
            this.h = r3.readInt32(r4);
        L17:
            this.caption = PageCaption.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.full_width);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 8, this.allow_scrolling);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L6;
            r4.writeString(this.url);
        L6:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            r4.writeString(this.html);
        L9:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L12;
            r4.writeInt64(this.poster_photo_id);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            r4.writeInt32(this.w);
        L15:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L17;
            r4.writeInt32(this.h);
        L17:
            this.caption.serializeToStream(r4);
        }
    }

    public static class pageBlockEmbedPost extends PageBlock {
        public static final int constructor = -229005301;
        public String author;
        public long author_photo_id;
        public ArrayList<PageBlock> blocks;
        public int date;
        public String url;
        public long webpage_id;

        public pageBlockEmbedPost() {
            this.blocks = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.url = r3.readString(r4);
            this.webpage_id = r3.readInt64(r4);
            this.author_photo_id = r3.readInt64(r4);
            this.author = r3.readString(r4);
            this.date = r3.readInt32(r4);
            this.blocks = Vector.deserialize(r3, new TL_iv$RichMessage$$ExternalSyntheticLambda0(), r4);
            this.caption = PageCaption.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeString(this.url);
            r3.writeInt64(this.webpage_id);
            r3.writeInt64(this.author_photo_id);
            r3.writeString(this.author);
            r3.writeInt32(this.date);
            Vector.serialize(r3, this.blocks);
            this.caption.serializeToStream(r3);
        }
    }

    public static class pageBlockEmbedPost_layer82 extends pageBlockEmbedPost {
        public static final int constructor = 690781161;

        public pageBlockEmbedPost_layer82() {
        }

        @Override // org.telegram.tgnet.tl.TL_iv.pageBlockEmbedPost, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.url = r3.readString(r4);
            this.webpage_id = r3.readInt64(r4);
            this.author_photo_id = r3.readInt64(r4);
            this.author = r3.readString(r4);
            this.date = r3.readInt32(r4);
            this.blocks = Vector.deserialize(r3, new TL_iv$RichMessage$$ExternalSyntheticLambda0(), r4);
            PageCaption r0 = new PageCaption();
            this.caption = r0;
            r0.text = RichText.TLdeserialize(r3, r3.readInt32(r4), r4);
            PageCaption r2 = this.caption;
            r2.credit = new textEmpty();
        }

        @Override // org.telegram.tgnet.tl.TL_iv.pageBlockEmbedPost, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeString(this.url);
            r3.writeInt64(this.webpage_id);
            r3.writeInt64(this.author_photo_id);
            r3.writeString(this.author);
            r3.writeInt32(this.date);
            Vector.serialize(r3, this.blocks);
            this.caption.text.serializeToStream(r3);
        }
    }

    public static class pageBlockEmbed_layer60 extends pageBlockEmbed {
        public static final int constructor = -650782469;

        public pageBlockEmbed_layer60() {
        }

        @Override // org.telegram.tgnet.tl.TL_iv.pageBlockEmbed, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.full_width = TLObject.hasFlag(r0, 1);
            this.allow_scrolling = TLObject.hasFlag(this.flags, 8);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L6;
            this.url = r3.readString(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L8;
            this.html = r3.readString(r4);
        L8:
            this.w = r3.readInt32(r4);
            this.h = r3.readInt32(r4);
            PageCaption r02 = new PageCaption();
            this.caption = r02;
            r02.text = RichText.TLdeserialize(r3, r3.readInt32(r4), r4);
            PageCaption r2 = this.caption;
            r2.credit = new textEmpty();
        }

        @Override // org.telegram.tgnet.tl.TL_iv.pageBlockEmbed, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.full_width);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 8, this.allow_scrolling);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L6;
            r4.writeString(this.url);
        L6:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L8;
            r4.writeString(this.html);
        L8:
            r4.writeInt32(this.w);
            r4.writeInt32(this.h);
            this.caption.text.serializeToStream(r4);
        }
    }

    public static class pageBlockEmbed_layer82 extends pageBlockEmbed {
        public static final int constructor = -840826671;

        public pageBlockEmbed_layer82() {
        }

        @Override // org.telegram.tgnet.tl.TL_iv.pageBlockEmbed, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.full_width = TLObject.hasFlag(r0, 1);
            this.allow_scrolling = TLObject.hasFlag(this.flags, 8);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L6;
            this.url = r3.readString(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.html = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L11;
            this.poster_photo_id = r3.readInt64(r4);
        L11:
            this.w = r3.readInt32(r4);
            this.h = r3.readInt32(r4);
            PageCaption r02 = new PageCaption();
            this.caption = r02;
            r02.text = RichText.TLdeserialize(r3, r3.readInt32(r4), r4);
            PageCaption r2 = this.caption;
            r2.credit = new textEmpty();
        }

        @Override // org.telegram.tgnet.tl.TL_iv.pageBlockEmbed, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.full_width);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 8, this.allow_scrolling);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L6;
            r4.writeString(this.url);
        L6:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            r4.writeString(this.html);
        L9:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L11;
            r4.writeInt64(this.poster_photo_id);
        L11:
            r4.writeInt32(this.w);
            r4.writeInt32(this.h);
            this.caption.text.serializeToStream(r4);
        }
    }

    public static class pageBlockFooter extends PageBlock {
        public static final int constructor = 1216809369;

        public pageBlockFooter() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    /* loaded from: classes.dex */
    public static class pageBlockHeader extends PageBlock {
        public static final int constructor = -1076861716;

        public pageBlockHeader() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    public static class pageBlockHeading1 extends PageBlock {
        public static final int constructor = -1157691601;

        public pageBlockHeading1() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    public static class pageBlockHeading2 extends PageBlock {
        public static final int constructor = 158018284;

        public pageBlockHeading2() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    public static class pageBlockHeading3 extends PageBlock {
        public static final int constructor = 1743204781;

        public pageBlockHeading3() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    public static class pageBlockHeading4 extends PageBlock {
        public static final int constructor = -1254983893;

        public pageBlockHeading4() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    public static class pageBlockHeading5 extends PageBlock {
        public static final int constructor = -608277398;

        public pageBlockHeading5() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    public static class pageBlockHeading6 extends PageBlock {
        public static final int constructor = 1747599785;

        public pageBlockHeading6() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    public static class pageBlockKicker extends PageBlock {
        public static final int constructor = 504660880;

        public pageBlockKicker() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    /* loaded from: classes.dex */
    public static class pageBlockList extends PageBlock {
        public static final int constructor = -454524911;
        public ArrayList<PageListItem> items;
        public boolean ordered;

        public pageBlockList() {
            this.items = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.items = Vector.deserialize(r2, new TL_iv$pageBlockList$$ExternalSyntheticLambda0(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.items);
        }
    }

    public static class pageBlockList_layer82 extends pageBlockList {
        public static final int constructor = 978896884;

        public pageBlockList_layer82() {
        }

        @Override // org.telegram.tgnet.tl.TL_iv.pageBlockList, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r4, boolean r5) {
            this.ordered = r4.readBool(r5);
            ArrayList r42 = Vector.deserialize(r4, new TL_iv$pageBlockList_layer82$$ExternalSyntheticLambda0(), r5);
            int r52 = r42.size();
            int r0 = 0;
        L3:
            if (r0 >= r52) goto L5;
            Object r1 = r42.get(r0);
            r0 = r0 + 1;
            TL_pageListItemText r2 = new TL_pageListItemText();
            r2.text = (RichText) r1;
            this.items.add(r2);
            goto L3
        }

        @Override // org.telegram.tgnet.tl.TL_iv.pageBlockList, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r6) {
            r6.writeInt32(constructor);
            r6.writeBool(this.ordered);
            ArrayList r0 = new ArrayList(this.items.size());
            ArrayList<PageListItem> r5 = this.items;
            int r1 = r5.size();
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L7;
            PageListItem r3 = r5.get(r2);
            r2 = r2 + 1;
            PageListItem r32 = r3;
            if ((r32 instanceof TL_pageListItemText) == false) goto L3;
            r0.add(((TL_pageListItemText) r32).text);
            goto L3
        L7:
            Vector.serialize(r6, r0);
        }
    }

    public static class pageBlockMap extends PageBlock {
        public static final int constructor = -1538310410;
        public TLRPC.GeoPoint geo;
        public int h;
        public int w;
        public int zoom;

        public pageBlockMap() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.geo = TLRPC.GeoPoint.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.zoom = r2.readInt32(r3);
            this.w = r2.readInt32(r3);
            this.h = r2.readInt32(r3);
            this.caption = PageCaption.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.geo.serializeToStream(r2);
            r2.writeInt32(this.zoom);
            r2.writeInt32(this.w);
            r2.writeInt32(this.h);
            this.caption.serializeToStream(r2);
        }
    }

    public static class pageBlockMath extends PageBlock {
        public static final int constructor = 1493699616;
        public String source;

        public pageBlockMath() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.source = r1.readString(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.source);
        }
    }

    public static class pageBlockOrderedList extends PageBlock {
        public static final int constructor = 534181569;
        public int flags;
        public ArrayList<PageListOrderedItem> items;
        public boolean reversed;
        public int start;
        public String type;

        public pageBlockOrderedList() {
            this.items = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.reversed = TLObject.hasFlag(r0, 4);
            this.items = Vector.deserialize(r3, new TL_iv$pageBlockOrderedList$$ExternalSyntheticLambda0(), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.start = r3.readInt32(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.type = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 4, this.reversed);
            this.flags = r0;
            r4.writeInt32(r0);
            Vector.serialize(r4, this.items);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r4.writeInt32(this.start);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r4.writeString(this.type);
            return;
        }
    }

    public static class pageBlockOrderedList_layer226 extends pageBlockOrderedList {
        public static final int constructor = -1702174239;

        public pageBlockOrderedList_layer226() {
        }

        @Override // org.telegram.tgnet.tl.TL_iv.pageBlockOrderedList, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.items = Vector.deserialize(r2, new TL_iv$pageBlockOrderedList$$ExternalSyntheticLambda0(), r3);
        }

        @Override // org.telegram.tgnet.tl.TL_iv.pageBlockOrderedList, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.items);
        }
    }

    /* loaded from: classes.dex */
    public static class pageBlockParagraph extends PageBlock {
        public static final int constructor = 1182402406;

        public pageBlockParagraph() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    /* loaded from: classes.dex */
    public static class pageBlockPhoto extends PageBlock {
        public static final int constructor = 391759200;
        public int flags;
        public long photo_id;
        public boolean spoiler;
        public String url;
        public long webpage_id;

        public pageBlockPhoto() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.spoiler = TLObject.hasFlag(r0, 2);
            this.photo_id = r3.readInt64(r4);
            this.caption = PageCaption.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.url = r3.readString(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 1) == false) goto L9;
            this.webpage_id = r3.readInt64(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.spoiler);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeInt64(this.photo_id);
            this.caption.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r4.writeString(this.url);
        L6:
            if (TLObject.hasFlag(this.flags, 1) == false) goto L9;
            r4.writeInt64(this.webpage_id);
            return;
        }
    }

    public static class pageBlockPhoto_layer82 extends pageBlockPhoto {
        public static final int constructor = -372860542;

        public pageBlockPhoto_layer82() {
        }

        @Override // org.telegram.tgnet.tl.TL_iv.pageBlockPhoto, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.photo_id = r3.readInt64(r4);
            PageCaption r0 = new PageCaption();
            this.caption = r0;
            r0.text = RichText.TLdeserialize(r3, r3.readInt32(r4), r4);
            PageCaption r2 = this.caption;
            r2.credit = new textEmpty();
        }

        @Override // org.telegram.tgnet.tl.TL_iv.pageBlockPhoto, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.photo_id);
            this.caption.text.serializeToStream(r3);
        }
    }

    public static class pageBlockPreformatted extends PageBlock {
        public static final int constructor = -1066346178;
        public String language;

        public pageBlockPreformatted() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.language = r2.readString(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
            r2.writeString(this.language);
        }
    }

    public static class pageBlockPullquote extends PageBlock {
        public static final int constructor = 1329878739;
        public RichText caption;

        public pageBlockPullquote() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.caption = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
            this.caption.serializeToStream(r2);
        }
    }

    /* loaded from: classes.dex */
    public static class pageBlockRelatedArticles extends PageBlock {
        public static final int constructor = 370236054;
        public ArrayList<pageRelatedArticle> articles;
        public RichText title;

        public pageBlockRelatedArticles() {
            this.articles = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.title = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.articles = Vector.deserialize(r2, new TL_iv$pageBlockRelatedArticles$$ExternalSyntheticLambda0(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.title.serializeToStream(r2);
            Vector.serialize(r2, this.articles);
        }
    }

    public static class pageBlockSlideshow extends PageBlock {
        public static final int constructor = 52401552;
        public ArrayList<PageBlock> items;

        public pageBlockSlideshow() {
            this.items = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.items = Vector.deserialize(r2, new TL_iv$RichMessage$$ExternalSyntheticLambda0(), r3);
            this.caption = PageCaption.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.items);
            this.caption.serializeToStream(r2);
        }
    }

    public static class pageBlockSlideshow_layer82 extends pageBlockSlideshow {
        public static final int constructor = 319588707;

        public pageBlockSlideshow_layer82() {
        }

        @Override // org.telegram.tgnet.tl.TL_iv.pageBlockSlideshow, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.items = Vector.deserialize(r3, new TL_iv$RichMessage$$ExternalSyntheticLambda0(), r4);
            PageCaption r0 = new PageCaption();
            this.caption = r0;
            r0.text = RichText.TLdeserialize(r3, r3.readInt32(r4), r4);
            PageCaption r2 = this.caption;
            r2.credit = new textEmpty();
        }

        @Override // org.telegram.tgnet.tl.TL_iv.pageBlockSlideshow, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.items);
            this.caption.text.serializeToStream(r2);
        }
    }

    public static class pageBlockSubheader extends PageBlock {
        public static final int constructor = -248793375;

        public pageBlockSubheader() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    public static class pageBlockSubtitle extends PageBlock {
        public static final int constructor = -1879401953;

        public pageBlockSubtitle() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    public static class pageBlockTable extends PageBlock {
        public static final int constructor = -1085412734;
        public boolean bordered;
        public boolean compact;
        public int flags;
        public ArrayList<pageTableRow> rows;
        public boolean striped;
        public RichText title;

        public pageBlockTable() {
            this.rows = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.bordered = TLObject.hasFlag(r0, 1);
            this.striped = TLObject.hasFlag(this.flags, 2);
            this.compact = TLObject.hasFlag(this.flags, 4);
            this.title = RichText.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.rows = Vector.deserialize(r3, new TL_iv$pageBlockTable$$ExternalSyntheticLambda0(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.bordered);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.striped);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.compact);
            this.flags = r03;
            r4.writeInt32(r03);
            this.title.serializeToStream(r4);
            Vector.serialize(r4, this.rows);
        }
    }

    public static class pageBlockThinking extends PageBlock {
        public static final int constructor = 1009361890;

        public pageBlockThinking() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    /* loaded from: classes.dex */
    public static class pageBlockTitle extends PageBlock {
        public static final int constructor = 1890305021;

        public pageBlockTitle() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    public static class pageBlockUnsupported extends PageBlock {
        public static final int constructor = 324435594;

        public pageBlockUnsupported() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes.dex */
    public static class pageBlockVideo extends PageBlock {
        public static final int constructor = 2089805750;
        public boolean autoplay;
        public int flags;
        public boolean loop;
        public boolean spoiler;
        public long video_id;

        public pageBlockVideo() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.autoplay = TLObject.hasFlag(r0, 1);
            this.loop = TLObject.hasFlag(this.flags, 2);
            this.spoiler = TLObject.hasFlag(this.flags, 4);
            this.video_id = r3.readInt64(r4);
            this.caption = PageCaption.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.autoplay);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.loop);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.spoiler);
            this.flags = r03;
            r4.writeInt32(r03);
            r4.writeInt64(this.video_id);
            this.caption.serializeToStream(r4);
        }
    }

    public static class pageBlockVideo_layer82 extends pageBlockVideo {
        public static final int constructor = -640214938;

        public pageBlockVideo_layer82() {
        }

        @Override // org.telegram.tgnet.tl.TL_iv.pageBlockVideo, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.autoplay = TLObject.hasFlag(r0, 1);
            this.loop = TLObject.hasFlag(this.flags, 2);
            this.video_id = r3.readInt64(r4);
            PageCaption r02 = new PageCaption();
            this.caption = r02;
            r02.text = RichText.TLdeserialize(r3, r3.readInt32(r4), r4);
            PageCaption r2 = this.caption;
            r2.credit = new textEmpty();
        }

        @Override // org.telegram.tgnet.tl.TL_iv.pageBlockVideo, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.autoplay);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.loop);
            this.flags = r02;
            r4.writeInt32(r02);
            r4.writeInt64(this.video_id);
            this.caption.text.serializeToStream(r4);
        }
    }

    /* loaded from: classes.dex */
    public static class pageRelatedArticle extends TLObject {
        public static final int constructor = -1282352120;
        public String author;
        public String description;
        public int flags;
        public long photo_id;
        public int published_date;
        public String title;
        public String url;
        public long webpage_id;

        public pageRelatedArticle() {
        }

        public static pageRelatedArticle TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-1282352120) == r3) goto L5;
            pageRelatedArticle r0 = null;
        L7:
            return (pageRelatedArticle) TLObject.TLdeserialize(pageRelatedArticle.class, r0, r2, r3, r4);
        L5:
            r0 = new pageRelatedArticle();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.url = r3.readString(r4);
            this.webpage_id = r3.readInt64(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.title = r3.readString(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.description = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.photo_id = r3.readInt64(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.author = r3.readString(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            this.published_date = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeString(this.url);
            r3.writeInt64(this.webpage_id);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeString(this.title);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r3.writeString(this.description);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            r3.writeInt64(this.photo_id);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            r3.writeString(this.author);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            r3.writeInt32(this.published_date);
            return;
        }
    }

    public static class pageTableCell extends TLObject {
        public static final int constructor = 878078826;
        public boolean align_center;
        public boolean align_right;
        public int colspan;
        public int flags;
        public boolean header;
        public int rowspan;
        public RichText text;
        public boolean valign_bottom;
        public boolean valign_middle;

        public pageTableCell() {
        }

        public static pageTableCell TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (878078826 == r3) goto L5;
            pageTableCell r0 = null;
        L7:
            return (pageTableCell) TLObject.TLdeserialize(pageTableCell.class, r0, r2, r3, r4);
        L5:
            r0 = new pageTableCell();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.header = TLObject.hasFlag(r0, 1);
            this.align_center = TLObject.hasFlag(this.flags, 8);
            this.align_right = TLObject.hasFlag(this.flags, 16);
            this.valign_middle = TLObject.hasFlag(this.flags, 32);
            this.valign_bottom = TLObject.hasFlag(this.flags, 64);
            if (TLObject.hasFlag(this.flags, 128) == false) goto L6;
            this.text = RichText.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.colspan = r3.readInt32(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.rowspan = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.header);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 8, this.align_center);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 16, this.align_right);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 32, this.valign_middle);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 64, this.valign_bottom);
            this.flags = r05;
            r4.writeInt32(r05);
            if (TLObject.hasFlag(this.flags, 128) == false) goto L6;
            this.text.serializeToStream(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r4.writeInt32(this.colspan);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            r4.writeInt32(this.rowspan);
            return;
        }
    }

    public static class pageTableRow extends TLObject {
        public static final int constructor = -524237339;
        public ArrayList<pageTableCell> cells;

        public pageTableRow() {
            this.cells = new ArrayList();
        }

        public static pageTableRow TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-524237339) == r3) goto L5;
            pageTableRow r0 = null;
        L7:
            return (pageTableRow) TLObject.TLdeserialize(pageTableRow.class, r0, r2, r3, r4);
        L5:
            r0 = new pageTableRow();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.cells = Vector.deserialize(r2, new TL_iv$pageTableRow$$ExternalSyntheticLambda0(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.cells);
        }
    }

    public static class textAnchor extends RichText {
        public static final int constructor = 894777186;
        public String name;

        public textAnchor() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.name = r2.readString(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
            r2.writeString(this.name);
        }
    }

    public static class textAutoEmail extends RichText {
        public static final int constructor = -984177571;

        public textAutoEmail() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    public static class textAutoPhone extends RichText {
        public static final int constructor = 616720265;

        public textAutoPhone() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    public static class textAutoUrl extends RichText {
        public static final int constructor = -1402305622;

        public textAutoUrl() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    public static class textBankCard extends RichText {
        public static final int constructor = -1185513171;

        public textBankCard() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    /* loaded from: classes.dex */
    public static class textBold extends RichText {
        public static final int constructor = 1730456516;

        public textBold() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    public static class textBotCommand extends RichText {
        public static final int constructor = 50276819;

        public textBotCommand() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    public static class textButton extends RichText implements TL_keyboard.KeyboardButtonProto {
        public static final int constructor = -1345872682;
        public int flags;
        public TL_keyboard.RichButtonStyle style;
        public TL_keyboard.InlineButtonType type;

        public textButton() {
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.KeyboardButtonProto
        public String getText() {
            RichText r1 = this.text;
            if ((r1 instanceof textPlain) == true) goto L5;
            return null;
        L5:
            return ((textPlain) r1).text;
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.KeyboardButtonProto
        public TL_keyboard.ButtonTypeProto getType() {
            return this.type;
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.text = RichText.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.type = TL_keyboard.InlineButtonType.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.style = TL_keyboard.RichButtonStyle.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = this.flags;
            if (this.style == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            this.text.serializeToStream(r4);
            this.type.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L10;
            this.style.serializeToStream(r4);
            return;
        L10:
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class textCashtag extends RichText {
        public static final int constructor = 2073958401;

        public textCashtag() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    /* loaded from: classes.dex */
    public static class textConcat extends RichText {
        public static final int constructor = 2120376535;

        public textConcat() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.texts = Vector.deserialize(r2, new TL_iv$pageBlockList_layer82$$ExternalSyntheticLambda0(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.texts);
        }
    }

    public static class textCustomEmoji extends RichText {
        public static final int constructor = -1570679104;
        public String alt;
        public long document_id;

        public textCustomEmoji() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.document_id = r3.readInt64(r4);
            this.alt = r3.readString(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.document_id);
            r3.writeString(this.alt);
        }
    }

    public static class textDate extends RichText {
        public static final int constructor = -1514906069;
        public int date;
        public boolean day_of_week;
        public int flags;
        public boolean long_date;
        public boolean long_time;
        public boolean relative;
        public boolean short_date;
        public boolean short_time;

        public textDate() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.relative = TLObject.hasFlag(r0, 1);
            this.short_time = TLObject.hasFlag(this.flags, 2);
            this.long_time = TLObject.hasFlag(this.flags, 4);
            this.short_date = TLObject.hasFlag(this.flags, 8);
            this.long_date = TLObject.hasFlag(this.flags, 16);
            this.day_of_week = TLObject.hasFlag(this.flags, 32);
            this.text = RichText.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.date = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.relative);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.short_time);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.long_time);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8, this.short_date);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 16, this.long_date);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 32, this.day_of_week);
            this.flags = r06;
            r4.writeInt32(r06);
            this.text.serializeToStream(r4);
            r4.writeInt32(this.date);
        }
    }

    public static class textDiff extends RichText {
        public static final int constructor = -1769551024;
        public RichText old_text;
        public RichText text;

        public textDiff() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.old_text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
            this.old_text.serializeToStream(r2);
        }
    }

    public static class textEmail extends RichText {
        public static final int constructor = -564523562;

        public textEmail() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.email = r2.readString(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
            r2.writeString(this.email);
        }
    }

    /* loaded from: classes.dex */
    public static class textEmpty extends RichText {
        public static final int constructor = -599948721;

        public textEmpty() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class textFixed extends RichText {
        public static final int constructor = 1816074681;

        public textFixed() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    public static class textHashtag extends RichText {
        public static final int constructor = 1368728810;

        public textHashtag() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    public static class textImage extends RichText {
        public static final int constructor = 136105807;
        public long document_id;
        public int h;
        public long photo_id;
        public int w;

        public textImage() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.document_id = r3.readInt64(r4);
            this.w = r3.readInt32(r4);
            this.h = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.document_id);
            r3.writeInt32(this.w);
            r3.writeInt32(this.h);
        }
    }

    /* loaded from: classes.dex */
    public static class textItalic extends RichText {
        public static final int constructor = -653089380;

        public textItalic() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    public static class textMarked extends RichText {
        public static final int constructor = 55281185;

        public textMarked() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    public static class textMath extends RichText {
        public static final int constructor = -1657885545;
        public Bitmap bitmap;
        public int depth;
        public int h;
        public String source;
        public boolean tried;
        public int w;

        public textMath() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.source = r1.readString(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.source);
        }
    }

    public static class textMention extends RichText {
        public static final int constructor = -853225660;

        public textMention() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    public static class textMentionName extends RichText {
        public static final int constructor = 27917308;
        public long user_id;

        public textMentionName() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.user_id = r2.readInt64(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            this.text.serializeToStream(r3);
            r3.writeInt64(this.user_id);
        }
    }

    public static class textPhone extends RichText {
        public static final int constructor = 483104362;
        public String phone;

        public textPhone() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.phone = r2.readString(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
            r2.writeString(this.phone);
        }
    }

    /* loaded from: classes.dex */
    public static class textPlain extends RichText {
        public static final int constructor = 1950782688;
        public String text;

        public textPlain() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.text = r1.readString(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.text);
        }
    }

    public static class textSpoiler extends RichText {
        public static final int constructor = 1277844834;

        public textSpoiler() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    /* loaded from: classes.dex */
    public static class textStrike extends RichText {
        public static final int constructor = -1678197867;

        public textStrike() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    public static class textSubscript extends RichText {
        public static final int constructor = -311786236;

        public textSubscript() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    public static class textSuperscript extends RichText {
        public static final int constructor = -939827711;

        public textSuperscript() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    public static class textUnderline extends RichText {
        public static final int constructor = -1054465340;

        public textUnderline() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.text.serializeToStream(r2);
        }
    }

    /* loaded from: classes.dex */
    public static class textUrl extends RichText {
        public static final int constructor = 1009288385;

        public textUrl() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = RichText.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.url = r2.readString(r3);
            this.webpage_id = r2.readInt64(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            this.text.serializeToStream(r3);
            r3.writeString(this.url);
            r3.writeInt64(this.webpage_id);
        }
    }

    public TL_iv() {
    }
}
