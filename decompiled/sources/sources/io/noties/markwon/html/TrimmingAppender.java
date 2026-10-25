package io.noties.markwon.html;

/* loaded from: classes5.dex */
abstract class TrimmingAppender {

    public static class Impl extends TrimmingAppender {
        public Impl() {
        }

        @Override // io.noties.markwon.html.TrimmingAppender
        public <T extends Appendable & CharSequence> void append(T r9, String r10) {
            T r8 = r9;
            int r0 = r8.length();
            int r1 = r10.length();
            int r3 = 0;
            boolean r4 = false;
        L4:
            if (r3 >= r1) goto L16;
            char r6 = r10.charAt(r3);
            if (Character.isWhitespace(r6) == false) goto L8;
            r4 = true;
        L15:
            r3 = r3 + 1;
            goto L4
        L8:
            if (r4 == false) goto L14;
            int r42 = r8.length();
            if (r42 <= 0) goto L14;
            if (Character.isWhitespace(r8.charAt(r42 - 1)) == true) goto L14;
            AppendableUtils.appendQuietly(r9, ' ');
        L14:
            AppendableUtils.appendQuietly(r9, r6);
            r4 = false;
            goto L15
        L16:
            if (r4 == true) goto L18;
            return;
        L18:
            if (r0 >= r8.length()) goto L25;
            AppendableUtils.appendQuietly(r9, ' ');
            return;
        }
    }

    public TrimmingAppender() {
    }

    public static TrimmingAppender create() {
        return new Impl();
    }

    public abstract <T extends Appendable & CharSequence> void append(T r1, String r2);
}
