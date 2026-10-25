package org.webrtc;

/* loaded from: classes6.dex */
public interface Predicate<T> {

    /* renamed from: org.webrtc.Predicate$1, reason: invalid class name */
    public class AnonymousClass1 implements Predicate<T> {
        final /* synthetic */ Predicate this$0;
        final /* synthetic */ Predicate val$other;

        public AnonymousClass1(Predicate r1, Predicate r2) {
            this.this$0 = r1;
            this.val$other = r2;
        }

        @Override // org.webrtc.Predicate
        public boolean test(T r2) {
            if (this.this$0.test(r2) == false) goto L5;
            return true;
        L5:
            if (this.val$other.test(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    /* renamed from: org.webrtc.Predicate$2, reason: invalid class name */
    public class AnonymousClass2 implements Predicate<T> {
        final /* synthetic */ Predicate this$0;
        final /* synthetic */ Predicate val$other;

        public AnonymousClass2(Predicate r1, Predicate r2) {
            this.this$0 = r1;
            this.val$other = r2;
        }

        @Override // org.webrtc.Predicate
        public boolean test(T r2) {
            if (this.this$0.test(r2) == true) goto L5;
            return false;
        L5:
            if (this.val$other.test(r2) == false) goto L10;
            return true;
        L10:
            return false;
        }
    }

    /* renamed from: org.webrtc.Predicate$3, reason: invalid class name */
    public class AnonymousClass3 implements Predicate<T> {
        final /* synthetic */ Predicate this$0;

        public AnonymousClass3(Predicate r1) {
            this.this$0 = r1;
        }

        @Override // org.webrtc.Predicate
        public boolean test(T r1) {
            return !this.this$0.test(r1);
        }
    }

    default Predicate<T> and(Predicate<? super T> r2) {
        return new AnonymousClass2(this, r2);
    }

    default Predicate<T> negate() {
        return new AnonymousClass3(this);
    }

    default Predicate<T> or(Predicate<? super T> r2) {
        return new AnonymousClass1(this, r2);
    }

    boolean test(T r1);
}
