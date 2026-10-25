package com.exteragram.messenger.icons;

import com.exteragram.messenger.icons.IconPackStorageResult;
import java.io.File;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.BulletinFactory;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$handleIconPack$1$1$bottomSheet$1$1", f = "IconManager.kt", i = {1}, l = {980, 981}, m = "invokeSuspend", n = {"installResult"}, s = {"L$0"})
/* loaded from: classes4.dex */
public final class IconManager$handleIconPack$1$1$bottomSheet$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ BaseFragment $baseFragment;
    final /* synthetic */ boolean $enable;
    final /* synthetic */ File $file;
    final /* synthetic */ IconPack $pack;
    final /* synthetic */ boolean $update;
    Object L$0;
    int label;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$handleIconPack$1$1$bottomSheet$1$1$1", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.exteragram.messenger.icons.IconManager$handleIconPack$1$1$bottomSheet$1$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ BaseFragment $baseFragment;
        final /* synthetic */ boolean $enable;
        final /* synthetic */ IconPackStorageResult<Unit> $installResult;
        final /* synthetic */ IconPack $pack;
        final /* synthetic */ boolean $update;
        int label;

        public AnonymousClass1(IconPackStorageResult<Unit> r1, BaseFragment r2, boolean r3, IconPack r4, boolean r5, Continuation<? super AnonymousClass1> r6) {
            this.$installResult = r1;
            this.$baseFragment = r2;
            this.$update = r3;
            this.$pack = r4;
            this.$enable = r5;
            super(2, r6);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r8, Continuation<?> r9) {
            return new AnonymousClass1(this.$installResult, this.$baseFragment, this.$update, this.$pack, this.$enable, r9);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r4) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L22;
            ResultKt.throwOnFailure(r4);
            IconPackStorageResult<Unit> r42 = this.$installResult;
            if ((r42 instanceof IconPackStorageResult.Success) == false) goto L15;
            BulletinFactory r43 = BulletinFactory.of(this.$baseFragment);
            int r0 = R.raw.contact_check;
            if (this.$update == false) goto L9;
            int r1 = R.string.PluginUpdated;
        L10:
            r43.createSimpleBulletin(r0, LocaleController.formatString(r1, new Object[]{this.$pack.getName()})).show();
            if (this.$enable == false) goto L13;
            IconManager.INSTANCE.setActiveCustomPack(this.$pack.getId());
        L18:
            return Unit.INSTANCE;
        L13:
            IconManager r44 = IconManager.INSTANCE;
            IconManager.access$syncInstalledCustomPacks(r44, CollectionsKt.listOf(this.$pack));
            r44.initialize(true);
            goto L18
        L9:
            r1 = R.string.PluginInstalled;
            goto L10
        L15:
            if ((r42 instanceof IconPackStorageResult.Failure) == false) goto L19;
            IconManager.access$showIconPackError(IconManager.INSTANCE, this.$baseFragment, ((IconPackStorageResult.Failure) r42).getError());
            goto L18
        L19:
            LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
            return null;
        L22:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    public IconManager$handleIconPack$1$1$bottomSheet$1$1(File r1, BaseFragment r2, boolean r3, IconPack r4, boolean r5, Continuation<? super IconManager$handleIconPack$1$1$bottomSheet$1$1> r6) {
        this.$file = r1;
        this.$baseFragment = r2;
        this.$update = r3;
        this.$pack = r4;
        this.$enable = r5;
        super(2, r6);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r8, Continuation<?> r9) {
        return new IconManager$handleIconPack$1$1$bottomSheet$1$1(this.$file, this.$baseFragment, this.$update, this.$pack, this.$enable, r9);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
        return ((IconManager$handleIconPack$1$1$bottomSheet$1$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r11) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L10;
        if (r1 == 1) goto L9;
        if (r1 != 2) goto L7;
        IconPackStorageResult r10 = (IconPackStorageResult) this.L$0;
        ResultKt.throwOnFailure(r11);
    L17:
        return Unit.INSTANCE;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L9:
        ResultKt.throwOnFailure(r11);
    L13:
        IconPackStorageResult r4 = (IconPackStorageResult) r11;
        MainCoroutineDispatcher r112 = Dispatchers.getMain();
        AnonymousClass1 r3 = new AnonymousClass1(r4, this.$baseFragment, this.$update, this.$pack, this.$enable, null);
        this.L$0 = SpillingKt.nullOutSpilledVariable(r4);
        this.label = 2;
        if (BuildersKt.withContext(r112, r3, this) != r0) goto L17;
    L15:
        return r0;
    L10:
        ResultKt.throwOnFailure(r11);
        IconPackStorage r113 = IconPackStorage.INSTANCE;
        File r12 = this.$file;
        this.label = 1;
        r11 = r113.installPack(r12, this);
        if (r11 != r0) goto L13;
        goto L13
    }
}
