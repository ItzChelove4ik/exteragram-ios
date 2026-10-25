package com.exteragram.messenger.export;

import com.exteragram.messenger.export.output.AbstractWriter;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public class ExportSettings {
    public AbstractWriter.Format format;
    public MediaSettings media;
    public String path;
    public TLRPC.InputPeer singlePeer;
    public int singlePeerFrom;
    public int singlePeerTill;
    public int types;

    public static class MediaSettings {
        public long sizeLimit;
        public int type;

        public MediaSettings() {
            this.type = 1;
            this.sizeLimit = 8388608;
        }

        public boolean isEnabled() {
            int r1 = this.type;
            if ((r1 & 1) == 0) goto L5;
            return true;
        L5:
            if ((r1 & 2) == 0) goto L7;
            return true;
        L7:
            if ((r1 & 4) == 0) goto L9;
            return true;
        L9:
            if ((r1 & 8) == 0) goto L11;
            return true;
        L11:
            if ((r1 & 16) == 0) goto L13;
            return true;
        L13:
            if ((r1 & 32) == 0) goto L15;
            return true;
        L15:
            if ((r1 & 64) != 0) goto L26;
            return false;
        L26:
            return true;
        }
    }

    public ExportSettings() {
        this.singlePeer = new TLRPC.TL_inputPeerEmpty();
        this.format = AbstractWriter.Format.Json;
        this.singlePeerFrom = 0;
        this.singlePeerTill = 0;
        this.media = new MediaSettings();
        this.types = 32;
    }

    public boolean onlySinglePeer() {
        return !(this.singlePeer instanceof TLRPC.TL_inputPeerEmpty);
    }
}
