package com.google.firebase.encoders.proto;

import com.google.firebase.encoders.proto.Protobuf;
import java.lang.annotation.Annotation;

/* loaded from: classes5.dex */
public final class AtProtobuf {
    private Protobuf.IntEncoding intEncoding;
    private int tag;

    public static final class ProtobufImpl implements Protobuf {
        private final Protobuf.IntEncoding intEncoding;
        private final int tag;

        public ProtobufImpl(int r1, Protobuf.IntEncoding r2) {
            this.tag = r1;
            this.intEncoding = r2;
        }

        @Override // java.lang.annotation.Annotation
        public Class<? extends Annotation> annotationType() {
            return Protobuf.class;
        }

        @Override // java.lang.annotation.Annotation
        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof Protobuf) == true) goto L8;
            return false;
        L8:
            Protobuf r52 = (Protobuf) r5;
            if (this.tag == r52.tag()) goto L11;
        L13:
            return false;
        L11:
            if (this.intEncoding.equals(r52.intEncoding()) == false) goto L13;
            return true;
        }

        @Override // java.lang.annotation.Annotation
        public int hashCode() {
            return (14552422 ^ this.tag) + (this.intEncoding.hashCode() ^ 2041407134);
        }

        @Override // com.google.firebase.encoders.proto.Protobuf
        public Protobuf.IntEncoding intEncoding() {
            return this.intEncoding;
        }

        @Override // com.google.firebase.encoders.proto.Protobuf
        public int tag() {
            return this.tag;
        }

        @Override // java.lang.annotation.Annotation
        public String toString() {
            return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.tag + "intEncoding=" + this.intEncoding + ')';
        }
    }

    public AtProtobuf() {
        this.intEncoding = Protobuf.IntEncoding.DEFAULT;
    }

    public static AtProtobuf builder() {
        return new AtProtobuf();
    }

    public Protobuf build() {
        return new ProtobufImpl(this.tag, this.intEncoding);
    }

    public AtProtobuf tag(int r1) {
        this.tag = r1;
        return this;
    }
}
