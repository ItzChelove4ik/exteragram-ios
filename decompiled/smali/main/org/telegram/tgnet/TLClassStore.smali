.class public Lorg/telegram/tgnet/TLClassStore;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/tgnet/TLClassStore$TLObjectFactory;
    }
.end annotation


# static fields
.field static store:Lorg/telegram/tgnet/TLClassStore;


# instance fields
.field private classStore:Landroid/util/SparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/SparseArray<",
            "Lorg/telegram/tgnet/TLClassStore$TLObjectFactory;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method public constructor <init>()V
    .locals 3

    .line 22
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 23
    new-instance v0, Landroid/util/SparseArray;

    invoke-direct {v0}, Landroid/util/SparseArray;-><init>()V

    iput-object v0, p0, Lorg/telegram/tgnet/TLClassStore;->classStore:Landroid/util/SparseArray;

    .line 25
    new-instance v1, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda0;

    invoke-direct {v1}, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda0;-><init>()V

    const v2, -0x3b460645

    invoke-virtual {v0, v2, v1}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 26
    iget-object v0, p0, Lorg/telegram/tgnet/TLClassStore;->classStore:Landroid/util/SparseArray;

    new-instance v1, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda11;

    invoke-direct {v1}, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda11;-><init>()V

    const v2, 0x73164160

    invoke-virtual {v0, v2, v1}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 27
    iget-object v0, p0, Lorg/telegram/tgnet/TLClassStore;->classStore:Landroid/util/SparseArray;

    new-instance v1, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda12;

    invoke-direct {v1}, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda12;-><init>()V

    const v2, -0x6e33b98c

    invoke-virtual {v0, v2, v1}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 28
    iget-object v0, p0, Lorg/telegram/tgnet/TLClassStore;->classStore:Landroid/util/SparseArray;

    new-instance v1, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda13;

    invoke-direct {v1}, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda13;-><init>()V

    const v2, 0x1be31789

    invoke-virtual {v0, v2, v1}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 29
    iget-object v0, p0, Lorg/telegram/tgnet/TLClassStore;->classStore:Landroid/util/SparseArray;

    new-instance v1, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda12;

    invoke-direct {v1}, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda12;-><init>()V

    const v2, 0x204d3878

    invoke-virtual {v0, v2, v1}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 30
    iget-object v0, p0, Lorg/telegram/tgnet/TLClassStore;->classStore:Landroid/util/SparseArray;

    new-instance v1, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda14;

    invoke-direct {v1}, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda14;-><init>()V

    const v2, 0x36b091de

    invoke-virtual {v0, v2, v1}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 31
    iget-object v0, p0, Lorg/telegram/tgnet/TLClassStore;->classStore:Landroid/util/SparseArray;

    new-instance v1, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda15;

    invoke-direct {v1}, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda15;-><init>()V

    const v2, -0x55b7cd83

    invoke-virtual {v0, v2, v1}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 32
    iget-object v0, p0, Lorg/telegram/tgnet/TLClassStore;->classStore:Landroid/util/SparseArray;

    new-instance v1, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda16;

    invoke-direct {v1}, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda16;-><init>()V

    const v2, 0x1f814f1f

    invoke-virtual {v0, v2, v1}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 33
    iget-object v0, p0, Lorg/telegram/tgnet/TLClassStore;->classStore:Landroid/util/SparseArray;

    new-instance v1, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda17;

    invoke-direct {v1}, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda17;-><init>()V

    const v2, 0x555555fa

    invoke-virtual {v0, v2, v1}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 34
    iget-object v0, p0, Lorg/telegram/tgnet/TLClassStore;->classStore:Landroid/util/SparseArray;

    new-instance v1, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda18;

    invoke-direct {v1}, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda18;-><init>()V

    const v2, 0x555555f9

    invoke-virtual {v0, v2, v1}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 35
    iget-object v0, p0, Lorg/telegram/tgnet/TLClassStore;->classStore:Landroid/util/SparseArray;

    new-instance v1, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda1;

    invoke-direct {v1}, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda1;-><init>()V

    const v2, 0x555555f8

    invoke-virtual {v0, v2, v1}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 36
    iget-object v0, p0, Lorg/telegram/tgnet/TLClassStore;->classStore:Landroid/util/SparseArray;

    new-instance v1, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda2;

    invoke-direct {v1}, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda2;-><init>()V

    const v2, 0x555555f7

    invoke-virtual {v0, v2, v1}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 37
    iget-object v0, p0, Lorg/telegram/tgnet/TLClassStore;->classStore:Landroid/util/SparseArray;

    new-instance v1, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda3;

    invoke-direct {v1}, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda3;-><init>()V

    const v2, 0x56730bcc

    invoke-virtual {v0, v2, v1}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 39
    iget-object v0, p0, Lorg/telegram/tgnet/TLClassStore;->classStore:Landroid/util/SparseArray;

    new-instance v1, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda4;

    invoke-direct {v1}, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda4;-><init>()V

    const v2, 0x4d6deea5    # 2.4949E8f

    invoke-virtual {v0, v2, v1}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 40
    iget-object v0, p0, Lorg/telegram/tgnet/TLClassStore;->classStore:Landroid/util/SparseArray;

    new-instance v1, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda5;

    invoke-direct {v1}, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda5;-><init>()V

    const v2, 0x74ae4240

    invoke-virtual {v0, v2, v1}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 41
    iget-object v0, p0, Lorg/telegram/tgnet/TLClassStore;->classStore:Landroid/util/SparseArray;

    new-instance v1, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda6;

    invoke-direct {v1}, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda6;-><init>()V

    const v2, 0x313bc7f8

    invoke-virtual {v0, v2, v1}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 42
    iget-object v0, p0, Lorg/telegram/tgnet/TLClassStore;->classStore:Landroid/util/SparseArray;

    new-instance v1, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda7;

    invoke-direct {v1}, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda7;-><init>()V

    const v2, 0x78d4dec1

    invoke-virtual {v0, v2, v1}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 43
    iget-object v0, p0, Lorg/telegram/tgnet/TLClassStore;->classStore:Landroid/util/SparseArray;

    new-instance v1, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda8;

    invoke-direct {v1}, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda8;-><init>()V

    const v2, 0x725b04c3

    invoke-virtual {v0, v2, v1}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 44
    iget-object v0, p0, Lorg/telegram/tgnet/TLClassStore;->classStore:Landroid/util/SparseArray;

    new-instance v1, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda9;

    invoke-direct {v1}, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda9;-><init>()V

    const v2, -0x6fea1eff

    invoke-virtual {v0, v2, v1}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 45
    iget-object p0, p0, Lorg/telegram/tgnet/TLClassStore;->classStore:Landroid/util/SparseArray;

    new-instance v0, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda10;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLClassStore$$ExternalSyntheticLambda10;-><init>()V

    const v1, -0x1ce85082

    invoke-virtual {p0, v1, v0}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    return-void
.end method

.method public static Instance()Lorg/telegram/tgnet/TLClassStore;
    .locals 1

    .line 51
    sget-object v0, Lorg/telegram/tgnet/TLClassStore;->store:Lorg/telegram/tgnet/TLClassStore;

    if-nez v0, :cond_0

    .line 52
    new-instance v0, Lorg/telegram/tgnet/TLClassStore;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLClassStore;-><init>()V

    sput-object v0, Lorg/telegram/tgnet/TLClassStore;->store:Lorg/telegram/tgnet/TLClassStore;

    .line 54
    :cond_0
    sget-object v0, Lorg/telegram/tgnet/TLClassStore;->store:Lorg/telegram/tgnet/TLClassStore;

    return-object v0
.end method


# virtual methods
.method public TLdeserialize(Lorg/telegram/tgnet/NativeByteBuffer;IZ)Lorg/telegram/tgnet/TLObject;
    .locals 0

    .line 58
    iget-object p0, p0, Lorg/telegram/tgnet/TLClassStore;->classStore:Landroid/util/SparseArray;

    invoke-virtual {p0, p2}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/telegram/tgnet/TLClassStore$TLObjectFactory;

    if-eqz p0, :cond_0

    .line 60
    invoke-interface {p0}, Lorg/telegram/tgnet/TLClassStore$TLObjectFactory;->create()Lorg/telegram/tgnet/TLObject;

    move-result-object p0

    .line 61
    invoke-virtual {p0, p1, p3}, Lorg/telegram/tgnet/TLObject;->readParams(Lorg/telegram/tgnet/InputSerializedData;Z)V

    return-object p0

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method
