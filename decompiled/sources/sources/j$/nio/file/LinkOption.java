package j$.nio.file;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum LinkOption extends Enum<LinkOption> implements OpenOption, CopyOption {
    public static final LinkOption NOFOLLOW_LINKS = null;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ LinkOption[] f182a = null;

    static {
        LinkOption r0 = new LinkOption("NOFOLLOW_LINKS", 0);
        NOFOLLOW_LINKS = r0;
        f182a = new LinkOption[]{r0};
    }

    public static LinkOption valueOf(String r1) {
        return (LinkOption) Enum.valueOf(LinkOption.class, r1);
    }

    public static LinkOption[] values() {
        return (LinkOption[]) f182a.clone();
    }
}
