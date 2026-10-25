package okhttp3.internal;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.sequences.SequencesKt;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.signature.SignatureVisitor;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B%\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0010\u0006\u001a\u00028\u0000\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ/\u0010\u000b\u001a\u00020\u0003\"\b\b\u0001\u0010\f*\u00020\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\f0\u00052\b\u0010\u0006\u001a\u0004\u0018\u0001H\fH\u0016¢\u0006\u0002\u0010\rJ(\u0010\u000e\u001a\u0004\u0018\u0001H\f\"\b\b\u0001\u0010\f*\u00020\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\f0\u0005H\u0096\u0002¢\u0006\u0002\u0010\u000fJ\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00028\u0000X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lokhttp3/internal/LinkedTags;", "K", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/internal/Tags;", PluginsConstants.Settings.KEY, "Lkotlin/reflect/KClass;", "value", "next", "<init>", "(Lkotlin/reflect/KClass;Ljava/lang/Object;Lokhttp3/internal/Tags;)V", "Ljava/lang/Object;", "plus", "T", "(Lkotlin/reflect/KClass;Ljava/lang/Object;)Lokhttp3/internal/Tags;", "get", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
final class LinkedTags<K> extends Tags {
    private final KClass<K> key;
    private final Tags next;
    private final K value;

    public static LinkedTags $r8$lambda$Oa1fjbqL7x3c7crSS257QCt1kFE(LinkedTags r1) {
        Tags r12 = r1.next;
        if ((r12 instanceof LinkedTags) == true) goto L5;
        return null;
    L5:
        return (LinkedTags) r12;
    }

    /* renamed from: $r8$lambda$uVZpiaickqd0YIDc2k2ZzaQL-74, reason: not valid java name */
    public static CharSequence m3743$r8$lambda$uVZpiaickqd0YIDc2k2ZzaQL74(LinkedTags r2) {
        StringBuilder r0 = new StringBuilder();
        r0.append(r2.key);
        r0.append(SignatureVisitor.INSTANCEOF);
        r0.append(r2.value);
        return r0.toString();
    }

    public LinkedTags(KClass<K> r2, K r3, Tags r4) {
        super(null);
        this.key = r2;
        this.value = r3;
        this.next = r4;
    }

    @Override // okhttp3.internal.Tags
    public <T> T get(KClass<T> r2) {
        if (Intrinsics.areEqual(r2, this.key) == false) goto L7;
        return (T) JvmClassMappingKt.getJavaClass(r2).cast(this.value);
    L7:
        return (T) this.next.get(r2);
    }

    @Override // okhttp3.internal.Tags
    public <T> Tags plus(KClass<T> r4, T r5) {
        boolean r0 = Intrinsics.areEqual(r4, this.key);
        Tags r1 = this.next;
        if (r0 == true) goto L10;
        Tags r02 = r1.plus(r4, null);
        if (r02 == this.next) goto L9;
        LinkedTags<K> this = new LinkedTags(this.key, this.value, r02);
    L9:
        r1 = this;
    L10:
        if (r5 != null) goto L12;
        return r1;
    L12:
        return new LinkedTags(r4, r5, r1);
    }

    public String toString() {
        return CollectionsKt.joinToString$default(CollectionsKt.reversed(SequencesKt.toList(SequencesKt.generateSequence(this, new LinkedTags$$ExternalSyntheticLambda0()))), null, "{", "}", 0, null, new LinkedTags$$ExternalSyntheticLambda1(), 25, null);
    }
}
