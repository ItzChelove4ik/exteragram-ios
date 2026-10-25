package com.exteragram.messenger.icons;

import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/exteragram/messenger/icons/IconPack;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$saveIconPackMetadata$savedPack$1", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
@SourceDebugExtension({"SMAP\nIconManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$saveIconPackMetadata$savedPack$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1058:1\n1#2:1059\n*E\n"})
/* loaded from: classes4.dex */
public final class IconManager$saveIconPackMetadata$savedPack$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super IconPack>, Object> {
    final /* synthetic */ IconPack $iconPack;
    int label;

    public IconManager$saveIconPackMetadata$savedPack$1(IconPack r1, Continuation<? super IconManager$saveIconPackMetadata$savedPack$1> r2) {
        this.$iconPack = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
        return new IconManager$saveIconPackMetadata$savedPack$1(this.$iconPack, r2);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super IconPack> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super IconPack> r2) {
        return ((IconManager$saveIconPackMetadata$savedPack$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r13) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) goto L16;
        ResultKt.throwOnFailure(r13);
        IconPackStorage r132 = IconPackStorage.INSTANCE;
        IconPack r0 = r132.findPackById(this.$iconPack.getId());
        if (r0 == null) goto L7;
        Map<String, String> r7 = r0.getIcons();
    L8:
        if (r7 == null) goto L11;
        IconPack r02 = IconPack.copy$default(this.$iconPack, null, null, null, null, r7, null, null, 111, null);
        if (r02 == null) goto L11;
    L13:
        if (r132.saveIconPackMetadata(r02) == false) goto L15;
        return r02;
    L15:
        return null;
    L11:
        r02 = this.$iconPack;
        goto L13
    L7:
        r7 = null;
        goto L8
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
