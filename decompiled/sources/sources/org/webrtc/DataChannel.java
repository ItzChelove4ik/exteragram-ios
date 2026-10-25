package org.webrtc;

import java.nio.ByteBuffer;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes6.dex */
public class DataChannel {
    private long nativeDataChannel;
    private long nativeObserver;

    public static class Buffer {
        public final boolean binary;
        public final ByteBuffer data;

        @CalledByNative("Buffer")
        public Buffer(ByteBuffer r1, boolean r2) {
            this.data = r1;
            this.binary = r2;
        }
    }

    public static class Init {
        public int id;
        public int maxRetransmitTimeMs;
        public int maxRetransmits;
        public boolean negotiated;
        public boolean ordered;
        public String protocol;

        public Init() {
            this.ordered = true;
            this.maxRetransmitTimeMs = -1;
            this.maxRetransmits = -1;
            this.protocol = _UrlKt.FRAGMENT_ENCODE_SET;
            this.id = -1;
        }

        @CalledByNative("Init")
        public int getId() {
            return this.id;
        }

        @CalledByNative("Init")
        public int getMaxRetransmitTimeMs() {
            return this.maxRetransmitTimeMs;
        }

        @CalledByNative("Init")
        public int getMaxRetransmits() {
            return this.maxRetransmits;
        }

        @CalledByNative("Init")
        public boolean getNegotiated() {
            return this.negotiated;
        }

        @CalledByNative("Init")
        public boolean getOrdered() {
            return this.ordered;
        }

        @CalledByNative("Init")
        public String getProtocol() {
            return this.protocol;
        }
    }

    public interface Observer {
        @CalledByNative("Observer")
        void onBufferedAmountChange(long r1);

        @CalledByNative("Observer")
        void onMessage(Buffer r1);

        @CalledByNative("Observer")
        void onStateChange();
    }

    public enum State extends Enum<State> {
        private static final /* synthetic */ State[] $VALUES = null;
        public static final State CLOSED = null;
        public static final State CLOSING = null;
        public static final State CONNECTING = null;
        public static final State OPEN = null;

        private static /* synthetic */ State[] $values() {
            return new State[]{CONNECTING, OPEN, CLOSING, CLOSED};
        }

        static {
            CONNECTING = new State("CONNECTING", 0);
            OPEN = new State("OPEN", 1);
            CLOSING = new State("CLOSING", 2);
            CLOSED = new State("CLOSED", 3);
            $VALUES = $values();
        }

        State(String r1, int r2) {
        }

        @CalledByNative("State")
        public static State fromNativeIndex(int r1) {
            return values()[r1];
        }

        public static State valueOf(String r1) {
            return (State) Enum.valueOf(State.class, r1);
        }

        public static State[] values() {
            return (State[]) $VALUES.clone();
        }
    }

    @CalledByNative
    public DataChannel(long r1) {
        this.nativeDataChannel = r1;
    }

    private void checkDataChannelExists() {
        if (this.nativeDataChannel == 0) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("DataChannel has been disposed.");
    }

    private native long nativeBufferedAmount();

    private native void nativeClose();

    private native int nativeId();

    private native String nativeLabel();

    private native long nativeRegisterObserver(Observer r1);

    private native boolean nativeSend(byte[] r1, boolean r2);

    private native State nativeState();

    private native void nativeUnregisterObserver(long r1);

    public long bufferedAmount() {
        checkDataChannelExists();
        return nativeBufferedAmount();
    }

    public void close() {
        checkDataChannelExists();
        nativeClose();
    }

    public void dispose() {
        checkDataChannelExists();
        JniCommon.nativeReleaseRef(this.nativeDataChannel);
        this.nativeDataChannel = 0;
    }

    @CalledByNative
    public long getNativeDataChannel() {
        return this.nativeDataChannel;
    }

    public int id() {
        checkDataChannelExists();
        return nativeId();
    }

    public String label() {
        checkDataChannelExists();
        return nativeLabel();
    }

    public void registerObserver(Observer r5) {
        checkDataChannelExists();
        long r0 = this.nativeObserver;
        if (r0 == 0) goto L5;
        nativeUnregisterObserver(r0);
    L5:
        this.nativeObserver = nativeRegisterObserver(r5);
    }

    public boolean send(Buffer r3) {
        checkDataChannelExists();
        byte[] r0 = new byte[r3.data.remaining()];
        r3.data.get(r0);
        return nativeSend(r0, r3.binary);
    }

    public State state() {
        checkDataChannelExists();
        return nativeState();
    }

    public void unregisterObserver() {
        checkDataChannelExists();
        nativeUnregisterObserver(this.nativeObserver);
        this.nativeObserver = 0;
    }
}
