.class final Lcom/google/android/recaptcha/internal/zzaha;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzahn;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;",
        "Lcom/google/android/recaptcha/internal/zzahn<",
        "TT;>;"
    }
.end annotation


# static fields
.field private static final zza:[I

.field private static final zzb:Lsun/misc/Unsafe;


# instance fields
.field private final zzc:[I

.field private final zzd:[Ljava/lang/Object;

.field private final zze:I

.field private final zzf:I

.field private final zzg:Lcom/google/android/recaptcha/internal/zzagx;

.field private final zzh:Z

.field private final zzi:Z

.field private final zzj:[I

.field private final zzk:I

.field private final zzl:I

.field private final zzm:Lcom/google/android/recaptcha/internal/zzahz;

.field private final zzn:Lcom/google/android/recaptcha/internal/zzafi;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    const/4 v0, 0x0

    .line 1
    new-array v0, v0, [I

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaha;->zza:[I

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaig;->zzn()Lsun/misc/Unsafe;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaha;->zzb:Lsun/misc/Unsafe;

    return-void
.end method

.method private constructor <init>([I[Ljava/lang/Object;IILcom/google/android/recaptcha/internal/zzagx;Z[IIILcom/google/android/recaptcha/internal/zzahc;Lcom/google/android/recaptcha/internal/zzago;Lcom/google/android/recaptcha/internal/zzahz;Lcom/google/android/recaptcha/internal/zzafi;Lcom/google/android/recaptcha/internal/zzagt;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzc:[I

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzd:[Ljava/lang/Object;

    iput p3, p0, Lcom/google/android/recaptcha/internal/zzaha;->zze:I

    iput p4, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzf:I

    instance-of p1, p5, Lcom/google/android/recaptcha/internal/zzafv;

    iput-boolean p1, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzi:Z

    const/4 p1, 0x0

    iput-boolean p1, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzh:Z

    iput-object p7, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzj:[I

    iput p8, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzk:I

    iput p9, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzl:I

    iput-object p12, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzm:Lcom/google/android/recaptcha/internal/zzahz;

    iput-object p13, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzn:Lcom/google/android/recaptcha/internal/zzafi;

    iput-object p5, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzg:Lcom/google/android/recaptcha/internal/zzagx;

    return-void
.end method

.method private final zzA(I)I
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzc:[I

    add-int/lit8 p1, p1, 0x1

    aget p0, p0, p1

    return p0
.end method

.method private final zzB(I)I
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzc:[I

    add-int/lit8 p1, p1, 0x2

    aget p0, p0, p1

    return p0
.end method

.method private static zzC(I)I
    .locals 0

    ushr-int/lit8 p0, p0, 0x14

    and-int/lit16 p0, p0, 0xff

    return p0
.end method

.method private static zzD(I)Z
    .locals 1

    const/high16 v0, 0x20000000

    and-int/2addr p0, v0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private static zzE(Ljava/lang/Object;)Z
    .locals 1

    if-nez p0, :cond_0

    const/4 p0, 0x0

    return p0

    .line 1
    :cond_0
    instance-of v0, p0, Lcom/google/android/recaptcha/internal/zzafv;

    if-eqz v0, :cond_1

    check-cast p0, Lcom/google/android/recaptcha/internal/zzafv;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafv;->zzz()Z

    move-result p0

    return p0

    :cond_1
    const/4 p0, 0x1

    return p0
.end method

.method private static zzF(Ljava/lang/Object;)V
    .locals 1

    .line 1
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaha;->zzE(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    return-void

    .line 2
    :cond_0
    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    const-string v0, "Mutating immutable message: "

    invoke-virtual {v0, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method

.method private static zzG(Ljava/lang/Object;J)I
    .locals 0

    .line 1
    invoke-static {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Integer;

    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    return p0
.end method

.method private static zzH(Ljava/lang/Object;J)J
    .locals 0

    .line 1
    invoke-static {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Long;

    invoke-virtual {p0}, Ljava/lang/Long;->longValue()J

    move-result-wide p0

    return-wide p0
.end method

.method private final zzI(Ljava/lang/Object;Ljava/lang/Object;I)Z
    .locals 0

    .line 1
    invoke-direct {p0, p1, p3}, Lcom/google/android/recaptcha/internal/zzaha;->zzK(Ljava/lang/Object;I)Z

    move-result p1

    invoke-direct {p0, p2, p3}, Lcom/google/android/recaptcha/internal/zzaha;->zzK(Ljava/lang/Object;I)Z

    move-result p0

    if-ne p1, p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private final zzJ(Ljava/lang/Object;IIII)Z
    .locals 1

    const v0, 0xfffff

    if-ne p3, v0, :cond_0

    .line 1
    invoke-direct {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzaha;->zzK(Ljava/lang/Object;I)Z

    move-result p0

    return p0

    :cond_0
    and-int p0, p4, p5

    if-eqz p0, :cond_1

    const/4 p0, 0x1

    return p0

    :cond_1
    const/4 p0, 0x0

    return p0
.end method

.method private final zzK(Ljava/lang/Object;I)Z
    .locals 7

    .line 1
    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzaha;->zzB(I)I

    move-result v0

    const v1, 0xfffff

    and-int v2, v0, v1

    int-to-long v2, v2

    const-wide/32 v4, 0xfffff

    cmp-long v4, v2, v4

    const/4 v5, 0x0

    const/4 v6, 0x1

    if-nez v4, :cond_14

    .line 2
    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzaha;->zzA(I)I

    move-result p2

    and-int v0, p2, v1

    invoke-static {p2}, Lcom/google/android/recaptcha/internal/zzaha;->zzC(I)I

    move-result p2

    int-to-long v0, v0

    const-wide/16 v2, 0x0

    packed-switch p2, :pswitch_data_0

    .line 25
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzaha;->zzR()Z

    move-result p0

    return p0

    .line 3
    :pswitch_0
    invoke-static {p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object p0

    if-eqz p0, :cond_0

    return v6

    :cond_0
    return v5

    .line 4
    :pswitch_1
    invoke-static {p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzd(Ljava/lang/Object;J)J

    move-result-wide p0

    cmp-long p0, p0, v2

    if-eqz p0, :cond_1

    return v6

    :cond_1
    return v5

    .line 5
    :pswitch_2
    invoke-static {p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result p0

    if-eqz p0, :cond_2

    return v6

    :cond_2
    return v5

    .line 6
    :pswitch_3
    invoke-static {p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzd(Ljava/lang/Object;J)J

    move-result-wide p0

    cmp-long p0, p0, v2

    if-eqz p0, :cond_3

    return v6

    :cond_3
    return v5

    .line 7
    :pswitch_4
    invoke-static {p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result p0

    if-eqz p0, :cond_4

    return v6

    :cond_4
    return v5

    .line 8
    :pswitch_5
    invoke-static {p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result p0

    if-eqz p0, :cond_5

    return v6

    :cond_5
    return v5

    .line 9
    :pswitch_6
    invoke-static {p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result p0

    if-eqz p0, :cond_6

    return v6

    :cond_6
    return v5

    .line 10
    :pswitch_7
    sget-object p0, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    invoke-static {p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzaer;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_7

    return v6

    :cond_7
    return v5

    .line 11
    :pswitch_8
    invoke-static {p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object p0

    if-eqz p0, :cond_8

    return v6

    :cond_8
    return v5

    .line 12
    :pswitch_9
    invoke-static {p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object p1

    .line 13
    instance-of p2, p1, Ljava/lang/String;

    if-eqz p2, :cond_a

    .line 14
    check-cast p1, Ljava/lang/String;

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result p0

    if-nez p0, :cond_9

    return v6

    :cond_9
    return v5

    :cond_a
    instance-of p2, p1, Lcom/google/android/recaptcha/internal/zzaer;

    if-eqz p2, :cond_c

    .line 15
    sget-object p0, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzaer;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_b

    return v6

    :cond_b
    return v5

    .line 16
    :cond_c
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzaha;->zzR()Z

    move-result p0

    return p0

    .line 17
    :pswitch_a
    invoke-static {p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzf(Ljava/lang/Object;J)Z

    move-result p0

    return p0

    .line 18
    :pswitch_b
    invoke-static {p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result p0

    if-eqz p0, :cond_d

    return v6

    :cond_d
    return v5

    .line 19
    :pswitch_c
    invoke-static {p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzd(Ljava/lang/Object;J)J

    move-result-wide p0

    cmp-long p0, p0, v2

    if-eqz p0, :cond_e

    return v6

    :cond_e
    return v5

    .line 20
    :pswitch_d
    invoke-static {p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result p0

    if-eqz p0, :cond_f

    return v6

    :cond_f
    return v5

    .line 21
    :pswitch_e
    invoke-static {p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzd(Ljava/lang/Object;J)J

    move-result-wide p0

    cmp-long p0, p0, v2

    if-eqz p0, :cond_10

    return v6

    :cond_10
    return v5

    .line 22
    :pswitch_f
    invoke-static {p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzd(Ljava/lang/Object;J)J

    move-result-wide p0

    cmp-long p0, p0, v2

    if-eqz p0, :cond_11

    return v6

    :cond_11
    return v5

    .line 23
    :pswitch_10
    invoke-static {p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzh(Ljava/lang/Object;J)F

    move-result p0

    invoke-static {p0}, Ljava/lang/Float;->floatToRawIntBits(F)I

    move-result p0

    if-eqz p0, :cond_12

    return v6

    :cond_12
    return v5

    .line 24
    :pswitch_11
    invoke-static {p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzj(Ljava/lang/Object;J)D

    move-result-wide p0

    invoke-static {p0, p1}, Ljava/lang/Double;->doubleToRawLongBits(D)J

    move-result-wide p0

    cmp-long p0, p0, v2

    if-eqz p0, :cond_13

    return v6

    :cond_13
    return v5

    :cond_14
    ushr-int/lit8 p0, v0, 0x14

    shl-int p0, v6, p0

    .line 26
    invoke-static {p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result p1

    and-int/2addr p0, p1

    if-eqz p0, :cond_15

    return v6

    :cond_15
    return v5

    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_11
        :pswitch_10
        :pswitch_f
        :pswitch_e
        :pswitch_d
        :pswitch_c
        :pswitch_b
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private final zzL(Ljava/lang/Object;I)V
    .locals 4

    .line 1
    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzaha;->zzB(I)I

    move-result p0

    const p2, 0xfffff

    and-int/2addr p2, p0

    int-to-long v0, p2

    const-wide/32 v2, 0xfffff

    cmp-long p2, v0, v2

    if-nez p2, :cond_0

    return-void

    :cond_0
    ushr-int/lit8 p0, p0, 0x14

    .line 2
    invoke-static {p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result p2

    const/4 v2, 0x1

    shl-int p0, v2, p0

    or-int/2addr p0, p2

    .line 3
    invoke-static {p1, v0, v1, p0}, Lcom/google/android/recaptcha/internal/zzaig;->zzc(Ljava/lang/Object;JI)V

    return-void
.end method

.method private final zzM(Ljava/lang/Object;II)Z
    .locals 2

    .line 1
    invoke-direct {p0, p3}, Lcom/google/android/recaptcha/internal/zzaha;->zzB(I)I

    move-result p0

    const p3, 0xfffff

    and-int/2addr p0, p3

    int-to-long v0, p0

    .line 2
    invoke-static {p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result p0

    if-ne p0, p2, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private final zzN(Ljava/lang/Object;Ljava/lang/Object;I)Z
    .locals 2

    .line 1
    invoke-direct {p0, p3}, Lcom/google/android/recaptcha/internal/zzaha;->zzB(I)I

    move-result p0

    const p3, 0xfffff

    and-int/2addr p0, p3

    int-to-long v0, p0

    .line 2
    invoke-static {p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result p0

    .line 3
    invoke-static {p2, v0, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result p1

    if-ne p0, p1, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private final zzO(Ljava/lang/Object;II)V
    .locals 2

    .line 1
    invoke-direct {p0, p3}, Lcom/google/android/recaptcha/internal/zzaha;->zzB(I)I

    move-result p0

    const p3, 0xfffff

    and-int/2addr p0, p3

    int-to-long v0, p0

    invoke-static {p1, v0, v1, p2}, Lcom/google/android/recaptcha/internal/zzaig;->zzc(Ljava/lang/Object;JI)V

    return-void
.end method

.method private final zzP(I)I
    .locals 1

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaha;->zze:I

    if-lt p1, v0, :cond_0

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzf:I

    if-gt p1, v0, :cond_0

    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzQ(II)I

    move-result p0

    return p0

    :cond_0
    const/4 p0, -0x1

    return p0
.end method

.method private final zzQ(II)I
    .locals 5

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzc:[I

    array-length v0, p0

    div-int/lit8 v0, v0, 0x3

    const/4 v1, -0x1

    add-int/2addr v0, v1

    :goto_0
    if-gt p2, v0, :cond_2

    add-int v2, v0, p2

    ushr-int/lit8 v2, v2, 0x1

    mul-int/lit8 v3, v2, 0x3

    aget v4, p0, v3

    if-ne p1, v4, :cond_0

    return v3

    :cond_0
    if-ge p1, v4, :cond_1

    add-int/lit8 v0, v2, -0x1

    goto :goto_0

    :cond_1
    add-int/lit8 p2, v2, 0x1

    goto :goto_0

    :cond_2
    return v1
.end method

.method private zzR()Z
    .locals 0

    .line 1
    new-instance p0, Ljava/lang/IllegalArgumentException;

    invoke-direct {p0}, Ljava/lang/IllegalArgumentException;-><init>()V

    throw p0
.end method

.method private static final zzS(ILjava/lang/Object;Lcom/google/android/recaptcha/internal/zzafb;)V
    .locals 1

    .line 1
    instance-of v0, p1, Ljava/lang/String;

    if-eqz v0, :cond_0

    .line 2
    check-cast p1, Ljava/lang/String;

    invoke-virtual {p2, p0, p1}, Lcom/google/android/recaptcha/internal/zzafb;->zzm(ILjava/lang/String;)V

    return-void

    .line 3
    :cond_0
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaer;

    invoke-virtual {p2, p0, p1}, Lcom/google/android/recaptcha/internal/zzafb;->zzn(ILcom/google/android/recaptcha/internal/zzaer;)V

    return-void
.end method

.method public static zzh(Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zzaia;
    .locals 2

    .line 1
    check-cast p0, Lcom/google/android/recaptcha/internal/zzafv;

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzafv;->zzc:Lcom/google/android/recaptcha/internal/zzaia;

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaia;->zza()Lcom/google/android/recaptcha/internal/zzaia;

    move-result-object v1

    if-ne v0, v1, :cond_0

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaia;->zzb()Lcom/google/android/recaptcha/internal/zzaia;

    move-result-object v0

    .line 2
    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzafv;->zzc:Lcom/google/android/recaptcha/internal/zzaia;

    :cond_0
    return-object v0
.end method

.method public static zzm(Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzagv;Lcom/google/android/recaptcha/internal/zzahc;Lcom/google/android/recaptcha/internal/zzago;Lcom/google/android/recaptcha/internal/zzahz;Lcom/google/android/recaptcha/internal/zzafi;Lcom/google/android/recaptcha/internal/zzagt;)Lcom/google/android/recaptcha/internal/zzaha;
    .locals 32

    move-object/from16 v0, p1

    .line 1
    sget-object v1, Lcom/google/android/recaptcha/internal/zzaha;->zzb:Lsun/misc/Unsafe;

    const/4 v2, 0x0

    if-eqz v1, :cond_37

    instance-of v3, v0, Lcom/google/android/recaptcha/internal/zzahi;

    if-eqz v3, :cond_36

    .line 2
    check-cast v0, Lcom/google/android/recaptcha/internal/zzahi;

    .line 3
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzahi;->zzd()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v3

    const/4 v4, 0x0

    .line 4
    invoke-virtual {v2, v4}, Ljava/lang/String;->charAt(I)C

    move-result v5

    const v6, 0xd800

    if-lt v5, v6, :cond_0

    const/4 v5, 0x1

    :goto_0
    add-int/lit8 v8, v5, 0x1

    .line 5
    invoke-virtual {v2, v5}, Ljava/lang/String;->charAt(I)C

    move-result v5

    if-lt v5, v6, :cond_1

    move v5, v8

    goto :goto_0

    :cond_0
    const/4 v8, 0x1

    :cond_1
    add-int/lit8 v5, v8, 0x1

    .line 6
    invoke-virtual {v2, v8}, Ljava/lang/String;->charAt(I)C

    move-result v8

    if-lt v8, v6, :cond_3

    and-int/lit16 v8, v8, 0x1fff

    const/16 v10, 0xd

    :goto_1
    add-int/lit8 v11, v5, 0x1

    .line 7
    invoke-virtual {v2, v5}, Ljava/lang/String;->charAt(I)C

    move-result v5

    if-lt v5, v6, :cond_2

    and-int/lit16 v5, v5, 0x1fff

    shl-int/2addr v5, v10

    or-int/2addr v8, v5

    add-int/lit8 v10, v10, 0xd

    move v5, v11

    goto :goto_1

    :cond_2
    shl-int/2addr v5, v10

    or-int/2addr v8, v5

    move v5, v11

    :cond_3
    if-nez v8, :cond_4

    sget-object v8, Lcom/google/android/recaptcha/internal/zzaha;->zza:[I

    move v12, v4

    move v13, v12

    move v14, v13

    move/from16 v16, v14

    move/from16 v18, v16

    move-object/from16 v17, v8

    move/from16 v8, v18

    goto/16 :goto_a

    :cond_4
    add-int/lit8 v8, v5, 0x1

    .line 8
    invoke-virtual {v2, v5}, Ljava/lang/String;->charAt(I)C

    move-result v5

    if-lt v5, v6, :cond_6

    and-int/lit16 v5, v5, 0x1fff

    const/16 v10, 0xd

    :goto_2
    add-int/lit8 v11, v8, 0x1

    .line 9
    invoke-virtual {v2, v8}, Ljava/lang/String;->charAt(I)C

    move-result v8

    if-lt v8, v6, :cond_5

    and-int/lit16 v8, v8, 0x1fff

    shl-int/2addr v8, v10

    or-int/2addr v5, v8

    add-int/lit8 v10, v10, 0xd

    move v8, v11

    goto :goto_2

    :cond_5
    shl-int/2addr v8, v10

    or-int/2addr v5, v8

    move v8, v11

    :cond_6
    add-int/lit8 v10, v8, 0x1

    .line 10
    invoke-virtual {v2, v8}, Ljava/lang/String;->charAt(I)C

    move-result v8

    if-lt v8, v6, :cond_8

    and-int/lit16 v8, v8, 0x1fff

    const/16 v11, 0xd

    :goto_3
    add-int/lit8 v12, v10, 0x1

    .line 11
    invoke-virtual {v2, v10}, Ljava/lang/String;->charAt(I)C

    move-result v10

    if-lt v10, v6, :cond_7

    and-int/lit16 v10, v10, 0x1fff

    shl-int/2addr v10, v11

    or-int/2addr v8, v10

    add-int/lit8 v11, v11, 0xd

    move v10, v12

    goto :goto_3

    :cond_7
    shl-int/2addr v10, v11

    or-int/2addr v8, v10

    move v10, v12

    :cond_8
    add-int/lit8 v11, v10, 0x1

    .line 12
    invoke-virtual {v2, v10}, Ljava/lang/String;->charAt(I)C

    move-result v10

    if-lt v10, v6, :cond_a

    and-int/lit16 v10, v10, 0x1fff

    const/16 v12, 0xd

    :goto_4
    add-int/lit8 v13, v11, 0x1

    .line 13
    invoke-virtual {v2, v11}, Ljava/lang/String;->charAt(I)C

    move-result v11

    if-lt v11, v6, :cond_9

    and-int/lit16 v11, v11, 0x1fff

    shl-int/2addr v11, v12

    or-int/2addr v10, v11

    add-int/lit8 v12, v12, 0xd

    move v11, v13

    goto :goto_4

    :cond_9
    shl-int/2addr v11, v12

    or-int/2addr v10, v11

    move v11, v13

    :cond_a
    add-int/lit8 v12, v11, 0x1

    .line 14
    invoke-virtual {v2, v11}, Ljava/lang/String;->charAt(I)C

    move-result v11

    if-lt v11, v6, :cond_c

    and-int/lit16 v11, v11, 0x1fff

    const/16 v13, 0xd

    :goto_5
    add-int/lit8 v14, v12, 0x1

    .line 15
    invoke-virtual {v2, v12}, Ljava/lang/String;->charAt(I)C

    move-result v12

    if-lt v12, v6, :cond_b

    and-int/lit16 v12, v12, 0x1fff

    shl-int/2addr v12, v13

    or-int/2addr v11, v12

    add-int/lit8 v13, v13, 0xd

    move v12, v14

    goto :goto_5

    :cond_b
    shl-int/2addr v12, v13

    or-int/2addr v11, v12

    move v12, v14

    :cond_c
    add-int/lit8 v13, v12, 0x1

    .line 16
    invoke-virtual {v2, v12}, Ljava/lang/String;->charAt(I)C

    move-result v12

    if-lt v12, v6, :cond_e

    and-int/lit16 v12, v12, 0x1fff

    const/16 v14, 0xd

    :goto_6
    add-int/lit8 v15, v13, 0x1

    .line 17
    invoke-virtual {v2, v13}, Ljava/lang/String;->charAt(I)C

    move-result v13

    if-lt v13, v6, :cond_d

    and-int/lit16 v13, v13, 0x1fff

    shl-int/2addr v13, v14

    or-int/2addr v12, v13

    add-int/lit8 v14, v14, 0xd

    move v13, v15

    goto :goto_6

    :cond_d
    shl-int/2addr v13, v14

    or-int/2addr v12, v13

    move v13, v15

    :cond_e
    add-int/lit8 v14, v13, 0x1

    .line 18
    invoke-virtual {v2, v13}, Ljava/lang/String;->charAt(I)C

    move-result v13

    if-lt v13, v6, :cond_10

    and-int/lit16 v13, v13, 0x1fff

    const/16 v15, 0xd

    :goto_7
    add-int/lit8 v16, v14, 0x1

    .line 19
    invoke-virtual {v2, v14}, Ljava/lang/String;->charAt(I)C

    move-result v14

    if-lt v14, v6, :cond_f

    and-int/lit16 v14, v14, 0x1fff

    shl-int/2addr v14, v15

    or-int/2addr v13, v14

    add-int/lit8 v15, v15, 0xd

    move/from16 v14, v16

    goto :goto_7

    :cond_f
    shl-int/2addr v14, v15

    or-int/2addr v13, v14

    move/from16 v14, v16

    :cond_10
    add-int/lit8 v15, v14, 0x1

    .line 20
    invoke-virtual {v2, v14}, Ljava/lang/String;->charAt(I)C

    move-result v14

    if-lt v14, v6, :cond_12

    :goto_8
    add-int/lit8 v14, v15, 0x1

    .line 21
    invoke-virtual {v2, v15}, Ljava/lang/String;->charAt(I)C

    move-result v15

    if-lt v15, v6, :cond_11

    move v15, v14

    goto :goto_8

    :cond_11
    move v15, v14

    :cond_12
    add-int/lit8 v14, v15, 0x1

    .line 22
    invoke-virtual {v2, v15}, Ljava/lang/String;->charAt(I)C

    move-result v15

    if-lt v15, v6, :cond_14

    and-int/lit16 v15, v15, 0x1fff

    const/16 v16, 0xd

    :goto_9
    add-int/lit8 v17, v14, 0x1

    .line 23
    invoke-virtual {v2, v14}, Ljava/lang/String;->charAt(I)C

    move-result v14

    if-lt v14, v6, :cond_13

    and-int/lit16 v14, v14, 0x1fff

    shl-int v14, v14, v16

    or-int/2addr v15, v14

    add-int/lit8 v16, v16, 0xd

    move/from16 v14, v17

    goto :goto_9

    :cond_13
    shl-int v14, v14, v16

    or-int/2addr v15, v14

    move/from16 v14, v17

    :cond_14
    add-int v16, v15, v13

    add-int v4, v16, v5

    add-int v16, v5, v5

    add-int v16, v16, v8

    .line 24
    new-array v8, v4, [I

    move v4, v5

    move-object/from16 v17, v8

    move v8, v13

    move v5, v14

    move/from16 v18, v15

    move v13, v10

    move v14, v11

    .line 25
    :goto_a
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzahi;->zze()[Ljava/lang/Object;

    move-result-object v10

    .line 26
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzahi;->zzb()Lcom/google/android/recaptcha/internal/zzagx;

    move-result-object v11

    invoke-virtual {v11}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v11

    add-int v19, v18, v8

    add-int v8, v12, v12

    mul-int/lit8 v12, v12, 0x3

    .line 27
    new-array v12, v12, [I

    .line 28
    new-array v8, v8, [Ljava/lang/Object;

    move/from16 v22, v18

    move/from16 v21, v19

    const/4 v15, 0x0

    const/16 v20, 0x0

    :goto_b
    if-ge v5, v3, :cond_35

    add-int/lit8 v23, v5, 0x1

    .line 29
    invoke-virtual {v2, v5}, Ljava/lang/String;->charAt(I)C

    move-result v5

    if-lt v5, v6, :cond_16

    and-int/lit16 v5, v5, 0x1fff

    move/from16 v9, v23

    const/16 v23, 0xd

    :goto_c
    add-int/lit8 v24, v9, 0x1

    .line 30
    invoke-virtual {v2, v9}, Ljava/lang/String;->charAt(I)C

    move-result v9

    if-lt v9, v6, :cond_15

    and-int/lit16 v9, v9, 0x1fff

    shl-int v9, v9, v23

    or-int/2addr v5, v9

    add-int/lit8 v23, v23, 0xd

    move/from16 v9, v24

    goto :goto_c

    :cond_15
    shl-int v9, v9, v23

    or-int/2addr v5, v9

    move/from16 v9, v24

    goto :goto_d

    :cond_16
    move/from16 v9, v23

    :goto_d
    add-int/lit8 v23, v9, 0x1

    .line 31
    invoke-virtual {v2, v9}, Ljava/lang/String;->charAt(I)C

    move-result v9

    if-lt v9, v6, :cond_18

    and-int/lit16 v9, v9, 0x1fff

    move/from16 v7, v23

    const/16 v23, 0xd

    :goto_e
    add-int/lit8 v25, v7, 0x1

    .line 32
    invoke-virtual {v2, v7}, Ljava/lang/String;->charAt(I)C

    move-result v7

    if-lt v7, v6, :cond_17

    and-int/lit16 v7, v7, 0x1fff

    shl-int v7, v7, v23

    or-int/2addr v9, v7

    add-int/lit8 v23, v23, 0xd

    move/from16 v7, v25

    goto :goto_e

    :cond_17
    shl-int v7, v7, v23

    or-int/2addr v9, v7

    move/from16 v7, v25

    goto :goto_f

    :cond_18
    move/from16 v7, v23

    :goto_f
    and-int/lit16 v6, v9, 0x400

    if-eqz v6, :cond_19

    add-int/lit8 v6, v15, 0x1

    .line 33
    aput v20, v17, v15

    move v15, v6

    :cond_19
    and-int/lit16 v6, v9, 0xff

    move-object/from16 v25, v0

    and-int/lit16 v0, v9, 0x800

    move/from16 v26, v0

    const/16 v0, 0x33

    if-lt v6, v0, :cond_23

    add-int/lit8 v0, v7, 0x1

    .line 34
    invoke-virtual {v2, v7}, Ljava/lang/String;->charAt(I)C

    move-result v7

    move/from16 v27, v0

    const v0, 0xd800

    if-lt v7, v0, :cond_1b

    and-int/lit16 v7, v7, 0x1fff

    move/from16 v30, v27

    move/from16 v27, v7

    move/from16 v7, v30

    const/16 v30, 0xd

    :goto_10
    add-int/lit8 v31, v7, 0x1

    .line 35
    invoke-virtual {v2, v7}, Ljava/lang/String;->charAt(I)C

    move-result v7

    if-lt v7, v0, :cond_1a

    and-int/lit16 v0, v7, 0x1fff

    shl-int v0, v0, v30

    or-int v27, v27, v0

    add-int/lit8 v30, v30, 0xd

    move/from16 v7, v31

    const v0, 0xd800

    goto :goto_10

    :cond_1a
    shl-int v0, v7, v30

    or-int v7, v27, v0

    move/from16 v0, v31

    goto :goto_11

    :cond_1b
    move/from16 v0, v27

    :goto_11
    move/from16 v27, v0

    add-int/lit8 v0, v6, -0x33

    move/from16 v30, v3

    const/16 v3, 0x9

    if-eq v0, v3, :cond_1c

    const/16 v3, 0x11

    if-ne v0, v3, :cond_1d

    :cond_1c
    const/4 v3, 0x1

    goto :goto_14

    :cond_1d
    const/16 v3, 0xc

    if-ne v0, v3, :cond_20

    .line 37
    invoke-virtual/range {v25 .. v25}, Lcom/google/android/recaptcha/internal/zzahi;->zzc()I

    move-result v0

    const/4 v3, 0x1

    if-eq v0, v3, :cond_1f

    if-eqz v26, :cond_1e

    goto :goto_12

    :cond_1e
    const/4 v0, 0x0

    goto :goto_15

    :cond_1f
    :goto_12
    add-int/lit8 v0, v16, 0x1

    div-int/lit8 v24, v20, 0x3

    add-int v24, v24, v24

    add-int/lit8 v24, v24, 0x1

    .line 38
    aget-object v16, v10, v16

    aput-object v16, v8, v24

    :goto_13
    move/from16 v16, v0

    :cond_20
    move/from16 v0, v26

    goto :goto_15

    :goto_14
    add-int/lit8 v0, v16, 0x1

    .line 35
    div-int/lit8 v24, v20, 0x3

    add-int v24, v24, v24

    add-int/lit8 v28, v24, 0x1

    .line 36
    aget-object v3, v10, v16

    aput-object v3, v8, v28

    goto :goto_13

    :goto_15
    add-int/2addr v7, v7

    .line 39
    aget-object v3, v10, v7

    move/from16 v26, v0

    .line 40
    instance-of v0, v3, Ljava/lang/reflect/Field;

    if-eqz v0, :cond_21

    .line 41
    check-cast v3, Ljava/lang/reflect/Field;

    :goto_16
    move v0, v4

    goto :goto_17

    .line 42
    :cond_21
    check-cast v3, Ljava/lang/String;

    invoke-static {v11, v3}, Lcom/google/android/recaptcha/internal/zzaha;->zzn(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v3

    .line 43
    aput-object v3, v10, v7

    add-int/lit8 v0, v21, 0x1

    .line 44
    aput v20, v17, v21

    move/from16 v21, v0

    goto :goto_16

    .line 45
    :goto_17
    invoke-virtual {v1, v3}, Lsun/misc/Unsafe;->objectFieldOffset(Ljava/lang/reflect/Field;)J

    move-result-wide v3

    long-to-int v3, v3

    add-int/lit8 v7, v7, 0x1

    .line 46
    aget-object v4, v10, v7

    move/from16 v31, v0

    .line 47
    instance-of v0, v4, Ljava/lang/reflect/Field;

    if-eqz v0, :cond_22

    .line 48
    check-cast v4, Ljava/lang/reflect/Field;

    :goto_18
    move v0, v3

    goto :goto_19

    .line 49
    :cond_22
    check-cast v4, Ljava/lang/String;

    invoke-static {v11, v4}, Lcom/google/android/recaptcha/internal/zzaha;->zzn(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v4

    .line 50
    aput-object v4, v10, v7

    goto :goto_18

    .line 51
    :goto_19
    invoke-virtual {v1, v4}, Lsun/misc/Unsafe;->objectFieldOffset(Ljava/lang/reflect/Field;)J

    move-result-wide v3

    long-to-int v3, v3

    move-object/from16 v28, v2

    move/from16 v7, v27

    const/4 v2, 0x0

    move/from16 v27, v16

    move/from16 v16, v3

    move v3, v0

    move/from16 v0, v26

    goto/16 :goto_23

    :cond_23
    move/from16 v30, v3

    move/from16 v31, v4

    add-int/lit8 v0, v16, 0x1

    .line 52
    aget-object v3, v10, v16

    check-cast v3, Ljava/lang/String;

    invoke-static {v11, v3}, Lcom/google/android/recaptcha/internal/zzaha;->zzn(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v3

    const/16 v4, 0x9

    if-eq v6, v4, :cond_24

    const/16 v4, 0x11

    if-ne v6, v4, :cond_25

    :cond_24
    move/from16 v27, v0

    const/4 v0, 0x1

    goto/16 :goto_1e

    :cond_25
    const/16 v4, 0x1b

    if-eq v6, v4, :cond_2d

    const/16 v4, 0x31

    if-ne v6, v4, :cond_26

    add-int/lit8 v16, v16, 0x2

    move/from16 v27, v0

    const/4 v0, 0x1

    goto :goto_1d

    :cond_26
    const/16 v4, 0xc

    if-eq v6, v4, :cond_2a

    const/16 v4, 0x1e

    if-eq v6, v4, :cond_2a

    const/16 v4, 0x2c

    if-ne v6, v4, :cond_27

    goto :goto_1a

    :cond_27
    const/16 v4, 0x32

    if-ne v6, v4, :cond_28

    add-int/lit8 v4, v16, 0x2

    add-int/lit8 v27, v22, 0x1

    .line 57
    aput v20, v17, v22

    div-int/lit8 v22, v20, 0x3

    .line 58
    aget-object v0, v10, v0

    add-int v22, v22, v22

    aput-object v0, v8, v22

    if-eqz v26, :cond_29

    add-int/lit8 v22, v22, 0x1

    add-int/lit8 v0, v16, 0x3

    .line 59
    aget-object v4, v10, v4

    aput-object v4, v8, v22

    move/from16 v22, v27

    :cond_28
    move/from16 v27, v0

    const/4 v0, 0x1

    goto :goto_1f

    :cond_29
    move/from16 v22, v27

    const/4 v0, 0x1

    const/16 v26, 0x0

    move/from16 v27, v4

    goto :goto_1f

    .line 55
    :cond_2a
    :goto_1a
    invoke-virtual/range {v25 .. v25}, Lcom/google/android/recaptcha/internal/zzahi;->zzc()I

    move-result v4

    move/from16 v27, v0

    const/4 v0, 0x1

    if-eq v4, v0, :cond_2c

    if-eqz v26, :cond_2b

    goto :goto_1b

    :cond_2b
    const/16 v26, 0x0

    goto :goto_1f

    :cond_2c
    :goto_1b
    add-int/lit8 v16, v16, 0x2

    div-int/lit8 v4, v20, 0x3

    add-int/2addr v4, v4

    add-int/2addr v4, v0

    .line 56
    aget-object v24, v10, v27

    aput-object v24, v8, v4

    :goto_1c
    move/from16 v27, v16

    goto :goto_1f

    :cond_2d
    move/from16 v27, v0

    const/4 v0, 0x1

    add-int/lit8 v16, v16, 0x2

    .line 67
    :goto_1d
    div-int/lit8 v4, v20, 0x3

    add-int/2addr v4, v4

    add-int/2addr v4, v0

    .line 54
    aget-object v24, v10, v27

    aput-object v24, v8, v4

    goto :goto_1c

    .line 52
    :goto_1e
    div-int/lit8 v4, v20, 0x3

    add-int/2addr v4, v4

    add-int/2addr v4, v0

    .line 53
    invoke-virtual {v3}, Ljava/lang/reflect/Field;->getType()Ljava/lang/Class;

    move-result-object v16

    aput-object v16, v8, v4

    .line 60
    :goto_1f
    invoke-virtual {v1, v3}, Lsun/misc/Unsafe;->objectFieldOffset(Ljava/lang/reflect/Field;)J

    move-result-wide v3

    long-to-int v3, v3

    and-int/lit16 v4, v9, 0x1000

    const v16, 0xfffff

    if-eqz v4, :cond_31

    const/16 v4, 0x11

    if-gt v6, v4, :cond_31

    add-int/lit8 v4, v7, 0x1

    .line 61
    invoke-virtual {v2, v7}, Ljava/lang/String;->charAt(I)C

    move-result v7

    const v0, 0xd800

    if-lt v7, v0, :cond_2f

    and-int/lit16 v7, v7, 0x1fff

    const/16 v16, 0xd

    :goto_20
    add-int/lit8 v23, v4, 0x1

    .line 62
    invoke-virtual {v2, v4}, Ljava/lang/String;->charAt(I)C

    move-result v4

    if-lt v4, v0, :cond_2e

    and-int/lit16 v4, v4, 0x1fff

    shl-int v4, v4, v16

    or-int/2addr v7, v4

    add-int/lit8 v16, v16, 0xd

    move/from16 v4, v23

    goto :goto_20

    :cond_2e
    shl-int v4, v4, v16

    or-int/2addr v7, v4

    move/from16 v4, v23

    :cond_2f
    add-int v16, v31, v31

    div-int/lit8 v23, v7, 0x20

    add-int v16, v16, v23

    .line 63
    aget-object v0, v10, v16

    move-object/from16 v28, v2

    .line 64
    instance-of v2, v0, Ljava/lang/reflect/Field;

    if-eqz v2, :cond_30

    .line 65
    check-cast v0, Ljava/lang/reflect/Field;

    :goto_21
    move/from16 v29, v3

    goto :goto_22

    .line 66
    :cond_30
    check-cast v0, Ljava/lang/String;

    invoke-static {v11, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzn(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v0

    .line 67
    aput-object v0, v10, v16

    goto :goto_21

    .line 68
    :goto_22
    invoke-virtual {v1, v0}, Lsun/misc/Unsafe;->objectFieldOffset(Ljava/lang/reflect/Field;)J

    move-result-wide v2

    long-to-int v3, v2

    rem-int/lit8 v7, v7, 0x20

    move/from16 v16, v3

    move v2, v7

    move/from16 v0, v26

    move/from16 v3, v29

    move v7, v4

    goto :goto_23

    :cond_31
    move-object/from16 v28, v2

    move/from16 v29, v3

    move/from16 v0, v26

    const/4 v2, 0x0

    :goto_23
    add-int/lit8 v4, v20, 0x1

    .line 69
    aput v5, v12, v20

    add-int/lit8 v5, v20, 0x2

    move/from16 v26, v0

    and-int/lit16 v0, v9, 0x200

    if-eqz v0, :cond_32

    const/high16 v0, 0x20000000

    goto :goto_24

    :cond_32
    const/4 v0, 0x0

    :goto_24
    and-int/lit16 v9, v9, 0x100

    if-eqz v9, :cond_33

    const/high16 v9, 0x10000000

    goto :goto_25

    :cond_33
    const/4 v9, 0x0

    :goto_25
    if-eqz v26, :cond_34

    const/high16 v26, -0x80000000

    goto :goto_26

    :cond_34
    const/16 v26, 0x0

    :goto_26
    shl-int/lit8 v6, v6, 0x14

    or-int/2addr v0, v9

    or-int v0, v0, v26

    or-int/2addr v0, v6

    or-int/2addr v0, v3

    .line 70
    aput v0, v12, v4

    add-int/lit8 v20, v20, 0x3

    shl-int/lit8 v0, v2, 0x14

    or-int v0, v0, v16

    .line 71
    aput v0, v12, v5

    move v5, v7

    move-object/from16 v0, v25

    move/from16 v16, v27

    move-object/from16 v2, v28

    move/from16 v3, v30

    move/from16 v4, v31

    const v6, 0xd800

    goto/16 :goto_b

    :cond_35
    move-object/from16 v25, v0

    .line 59
    new-instance v10, Lcom/google/android/recaptcha/internal/zzaha;

    .line 72
    invoke-virtual/range {v25 .. v25}, Lcom/google/android/recaptcha/internal/zzahi;->zzb()Lcom/google/android/recaptcha/internal/zzagx;

    move-result-object v15

    const/16 v16, 0x0

    move-object/from16 v20, p2

    move-object/from16 v21, p3

    move-object/from16 v22, p4

    move-object/from16 v23, p5

    move-object/from16 v24, p6

    move-object v11, v12

    move-object v12, v8

    invoke-direct/range {v10 .. v24}, Lcom/google/android/recaptcha/internal/zzaha;-><init>([I[Ljava/lang/Object;IILcom/google/android/recaptcha/internal/zzagx;Z[IIILcom/google/android/recaptcha/internal/zzahc;Lcom/google/android/recaptcha/internal/zzago;Lcom/google/android/recaptcha/internal/zzahz;Lcom/google/android/recaptcha/internal/zzafi;Lcom/google/android/recaptcha/internal/zzagt;)V

    return-object v10

    .line 73
    :cond_36
    invoke-static {v0}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 74
    throw v2

    .line 1
    :cond_37
    const-string v0, "Lite gencode is primarily intended for Android use and uses sun.misc.Unsafe which is not available in the current environment. To run in this environment, you may need to switch to standard gencode."

    invoke-static {v0}, Lorg/webrtc/GlShader$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v2
.end method

.method private static zzn(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;
    .locals 6

    .line 1
    :try_start_0
    invoke-virtual {p0, p1}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/NoSuchFieldException; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception v0

    .line 2
    invoke-virtual {p0}, Ljava/lang/Class;->getDeclaredFields()[Ljava/lang/reflect/Field;

    move-result-object v1

    .line 3
    array-length v2, v1

    const/4 v3, 0x0

    :goto_0
    if-ge v3, v2, :cond_1

    aget-object v4, v1, v3

    .line 4
    invoke-virtual {v4}, Ljava/lang/reflect/Field;->getName()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {p1, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_0

    return-object v4

    :cond_0
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 5
    :cond_1
    invoke-virtual {p0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object p0

    .line 6
    invoke-static {v1}, Ljava/util/Arrays;->toString([Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-static {p1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v2

    add-int/lit8 v2, v2, 0xb

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v3

    invoke-static {v1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v4

    add-int/2addr v2, v3

    add-int/lit8 v2, v2, 0x1d

    invoke-virtual {v4}, Ljava/lang/String;->length()I

    move-result v3

    add-int/2addr v2, v3

    invoke-static {v2, p1, p0, v1, v0}, Lcom/google/android/gms/internal/cast/zzzl$$ExternalSyntheticBUOutline1;->m(ILjava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Throwable;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method private final zzo(Ljava/lang/Object;Ljava/lang/Object;I)V
    .locals 5

    .line 1
    invoke-direct {p0, p2, p3}, Lcom/google/android/recaptcha/internal/zzaha;->zzK(Ljava/lang/Object;I)Z

    move-result v0

    if-nez v0, :cond_0

    return-void

    .line 2
    :cond_0
    invoke-direct {p0, p3}, Lcom/google/android/recaptcha/internal/zzaha;->zzA(I)I

    move-result v0

    const v1, 0xfffff

    and-int/2addr v0, v1

    sget-object v1, Lcom/google/android/recaptcha/internal/zzaha;->zzb:Lsun/misc/Unsafe;

    int-to-long v2, v0

    .line 3
    invoke-virtual {v1, p2, v2, v3}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v0

    if-eqz v0, :cond_4

    .line 6
    invoke-direct {p0, p3}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object p2

    .line 7
    invoke-direct {p0, p1, p3}, Lcom/google/android/recaptcha/internal/zzaha;->zzK(Ljava/lang/Object;I)Z

    move-result v4

    if-nez v4, :cond_2

    .line 8
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzE(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_1

    .line 9
    invoke-virtual {v1, p1, v2, v3, v0}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    goto :goto_0

    .line 10
    :cond_1
    invoke-interface {p2}, Lcom/google/android/recaptcha/internal/zzahn;->zza()Ljava/lang/Object;

    move-result-object v4

    .line 11
    invoke-interface {p2, v4, v0}, Lcom/google/android/recaptcha/internal/zzahn;->zzd(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 12
    invoke-virtual {v1, p1, v2, v3, v4}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 13
    :goto_0
    invoke-direct {p0, p1, p3}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    return-void

    .line 14
    :cond_2
    invoke-virtual {v1, p1, v2, v3}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object p0

    .line 15
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaha;->zzE(Ljava/lang/Object;)Z

    move-result p3

    if-nez p3, :cond_3

    .line 16
    invoke-interface {p2}, Lcom/google/android/recaptcha/internal/zzahn;->zza()Ljava/lang/Object;

    move-result-object p3

    .line 17
    invoke-interface {p2, p3, p0}, Lcom/google/android/recaptcha/internal/zzahn;->zzd(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 18
    invoke-virtual {v1, p1, v2, v3, p3}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    move-object p0, p3

    .line 19
    :cond_3
    invoke-interface {p2, p0, v0}, Lcom/google/android/recaptcha/internal/zzahn;->zzd(Ljava/lang/Object;Ljava/lang/Object;)V

    return-void

    .line 3
    :cond_4
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzc:[I

    .line 4
    aget p0, p0, p3

    .line 5
    invoke-virtual {p2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/String;->length()I

    move-result p2

    add-int/lit8 p2, p2, 0x26

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result p3

    add-int/2addr p2, p3

    invoke-static {p2, p0, p1}, Lcom/google/android/gms/internal/cast/zzzl$$ExternalSyntheticBUOutline0;->m(IILjava/lang/Object;)V

    return-void
.end method

.method private final zzp(Ljava/lang/Object;Ljava/lang/Object;I)V
    .locals 6

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzc:[I

    aget v1, v0, p3

    .line 2
    invoke-direct {p0, p2, v1, p3}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v2

    if-nez v2, :cond_0

    return-void

    .line 3
    :cond_0
    invoke-direct {p0, p3}, Lcom/google/android/recaptcha/internal/zzaha;->zzA(I)I

    move-result v2

    const v3, 0xfffff

    and-int/2addr v2, v3

    sget-object v3, Lcom/google/android/recaptcha/internal/zzaha;->zzb:Lsun/misc/Unsafe;

    int-to-long v4, v2

    .line 4
    invoke-virtual {v3, p2, v4, v5}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    if-eqz v2, :cond_4

    .line 7
    invoke-direct {p0, p3}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object p2

    .line 8
    invoke-direct {p0, p1, v1, p3}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v0

    if-nez v0, :cond_2

    .line 9
    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzE(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 10
    invoke-virtual {v3, p1, v4, v5, v2}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    goto :goto_0

    .line 11
    :cond_1
    invoke-interface {p2}, Lcom/google/android/recaptcha/internal/zzahn;->zza()Ljava/lang/Object;

    move-result-object v0

    .line 12
    invoke-interface {p2, v0, v2}, Lcom/google/android/recaptcha/internal/zzahn;->zzd(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 13
    invoke-virtual {v3, p1, v4, v5, v0}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 14
    :goto_0
    invoke-direct {p0, p1, v1, p3}, Lcom/google/android/recaptcha/internal/zzaha;->zzO(Ljava/lang/Object;II)V

    return-void

    .line 15
    :cond_2
    invoke-virtual {v3, p1, v4, v5}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object p0

    .line 16
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaha;->zzE(Ljava/lang/Object;)Z

    move-result p3

    if-nez p3, :cond_3

    .line 17
    invoke-interface {p2}, Lcom/google/android/recaptcha/internal/zzahn;->zza()Ljava/lang/Object;

    move-result-object p3

    .line 18
    invoke-interface {p2, p3, p0}, Lcom/google/android/recaptcha/internal/zzahn;->zzd(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 19
    invoke-virtual {v3, p1, v4, v5, p3}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    move-object p0, p3

    .line 20
    :cond_3
    invoke-interface {p2, p0, v2}, Lcom/google/android/recaptcha/internal/zzahn;->zzd(Ljava/lang/Object;Ljava/lang/Object;)V

    return-void

    .line 5
    :cond_4
    aget p0, v0, p3

    .line 6
    invoke-virtual {p2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/String;->length()I

    move-result p2

    add-int/lit8 p2, p2, 0x26

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result p3

    add-int/2addr p2, p3

    invoke-static {p2, p0, p1}, Lcom/google/android/gms/internal/cast/zzzl$$ExternalSyntheticBUOutline0;->m(IILjava/lang/Object;)V

    return-void
.end method

.method private final zzq(I)Lcom/google/android/recaptcha/internal/zzahn;
    .locals 2

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzd:[Ljava/lang/Object;

    div-int/lit8 p1, p1, 0x3

    add-int/2addr p1, p1

    aget-object v0, p0, p1

    check-cast v0, Lcom/google/android/recaptcha/internal/zzahn;

    if-eqz v0, :cond_0

    return-object v0

    :cond_0
    add-int/lit8 v0, p1, 0x1

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzahg;->zza()Lcom/google/android/recaptcha/internal/zzahg;

    move-result-object v1

    aget-object v0, p0, v0

    check-cast v0, Ljava/lang/Class;

    invoke-virtual {v1, v0}, Lcom/google/android/recaptcha/internal/zzahg;->zzb(Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v0

    .line 3
    aput-object v0, p0, p1

    return-object v0
.end method

.method private final zzr(I)Ljava/lang/Object;
    .locals 0

    .line 1
    div-int/lit8 p1, p1, 0x3

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzd:[Ljava/lang/Object;

    add-int/2addr p1, p1

    aget-object p0, p0, p1

    return-object p0
.end method

.method private final zzs(I)Lcom/google/android/recaptcha/internal/zzaga;
    .locals 0

    .line 1
    div-int/lit8 p1, p1, 0x3

    add-int/2addr p1, p1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzd:[Ljava/lang/Object;

    add-int/lit8 p1, p1, 0x1

    aget-object p0, p0, p1

    check-cast p0, Lcom/google/android/recaptcha/internal/zzaga;

    return-object p0
.end method

.method private final zzt(Ljava/lang/Object;I)Ljava/lang/Object;
    .locals 3

    .line 1
    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v0

    .line 2
    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzaha;->zzA(I)I

    move-result v1

    const v2, 0xfffff

    and-int/2addr v1, v2

    .line 3
    invoke-direct {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzaha;->zzK(Ljava/lang/Object;I)Z

    move-result p0

    if-nez p0, :cond_0

    .line 4
    invoke-interface {v0}, Lcom/google/android/recaptcha/internal/zzahn;->zza()Ljava/lang/Object;

    move-result-object p0

    return-object p0

    :cond_0
    int-to-long v1, v1

    sget-object p0, Lcom/google/android/recaptcha/internal/zzaha;->zzb:Lsun/misc/Unsafe;

    .line 5
    invoke-virtual {p0, p1, v1, v2}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object p0

    .line 6
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaha;->zzE(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_1

    return-object p0

    .line 7
    :cond_1
    invoke-interface {v0}, Lcom/google/android/recaptcha/internal/zzahn;->zza()Ljava/lang/Object;

    move-result-object p1

    if-eqz p0, :cond_2

    .line 8
    invoke-interface {v0, p1, p0}, Lcom/google/android/recaptcha/internal/zzahn;->zzd(Ljava/lang/Object;Ljava/lang/Object;)V

    :cond_2
    return-object p1
.end method

.method private final zzu(Ljava/lang/Object;ILjava/lang/Object;)V
    .locals 3

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzaha;->zzb:Lsun/misc/Unsafe;

    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzaha;->zzA(I)I

    move-result v1

    const v2, 0xfffff

    and-int/2addr v1, v2

    int-to-long v1, v1

    invoke-virtual {v0, p1, v1, v2, p3}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 2
    invoke-direct {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    return-void
.end method

.method private final zzv(Ljava/lang/Object;II)Ljava/lang/Object;
    .locals 3

    .line 1
    invoke-direct {p0, p3}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v0

    .line 2
    invoke-direct {p0, p1, p2, p3}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result p2

    if-nez p2, :cond_0

    .line 3
    invoke-interface {v0}, Lcom/google/android/recaptcha/internal/zzahn;->zza()Ljava/lang/Object;

    move-result-object p0

    return-object p0

    :cond_0
    sget-object p2, Lcom/google/android/recaptcha/internal/zzaha;->zzb:Lsun/misc/Unsafe;

    .line 4
    invoke-direct {p0, p3}, Lcom/google/android/recaptcha/internal/zzaha;->zzA(I)I

    move-result p0

    const p3, 0xfffff

    and-int/2addr p0, p3

    int-to-long v1, p0

    invoke-virtual {p2, p1, v1, v2}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object p0

    .line 5
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaha;->zzE(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_1

    return-object p0

    .line 6
    :cond_1
    invoke-interface {v0}, Lcom/google/android/recaptcha/internal/zzahn;->zza()Ljava/lang/Object;

    move-result-object p1

    if-eqz p0, :cond_2

    .line 7
    invoke-interface {v0, p1, p0}, Lcom/google/android/recaptcha/internal/zzahn;->zzd(Ljava/lang/Object;Ljava/lang/Object;)V

    :cond_2
    return-object p1
.end method

.method private final zzw(Ljava/lang/Object;IILjava/lang/Object;)V
    .locals 3

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzaha;->zzb:Lsun/misc/Unsafe;

    invoke-direct {p0, p3}, Lcom/google/android/recaptcha/internal/zzaha;->zzA(I)I

    move-result v1

    const v2, 0xfffff

    and-int/2addr v1, v2

    int-to-long v1, v1

    invoke-virtual {v0, p1, v1, v2, p4}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 2
    invoke-direct {p0, p1, p2, p3}, Lcom/google/android/recaptcha/internal/zzaha;->zzO(Ljava/lang/Object;II)V

    return-void
.end method

.method private final zzx(Ljava/lang/Object;ILjava/lang/Object;Lcom/google/android/recaptcha/internal/zzahz;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 1
    iget-object p4, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzc:[I

    aget p4, p4, p2

    .line 2
    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzaha;->zzA(I)I

    move-result p4

    const p5, 0xfffff

    and-int/2addr p4, p5

    int-to-long p4, p4

    .line 3
    invoke-static {p1, p4, p5}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object p1

    if-nez p1, :cond_0

    goto :goto_0

    .line 4
    :cond_0
    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzaha;->zzs(I)Lcom/google/android/recaptcha/internal/zzaga;

    move-result-object p4

    if-nez p4, :cond_1

    :goto_0
    return-object p3

    .line 5
    :cond_1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzags;

    .line 6
    invoke-direct {p0, p2}, Lcom/google/android/recaptcha/internal/zzaha;->zzr(I)Ljava/lang/Object;

    move-result-object p0

    .line 7
    invoke-static {p0}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    .line 8
    throw p0
.end method

.method private static zzy(Ljava/lang/Object;ILcom/google/android/recaptcha/internal/zzahn;)Z
    .locals 2

    const v0, 0xfffff

    and-int/2addr p1, v0

    int-to-long v0, p1

    .line 1
    invoke-static {p0, v0, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object p0

    .line 2
    invoke-interface {p2, p0}, Lcom/google/android/recaptcha/internal/zzahn;->zzl(Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method private final zzz(Ljava/lang/Object;ILcom/google/android/recaptcha/internal/zzaew;)V
    .locals 3

    .line 1
    invoke-static {p2}, Lcom/google/android/recaptcha/internal/zzaha;->zzD(I)Z

    move-result v0

    const v1, 0xfffff

    and-int/2addr p2, v1

    int-to-long v1, p2

    if-eqz v0, :cond_0

    invoke-virtual {p3}, Lcom/google/android/recaptcha/internal/zzaew;->zzm()Ljava/lang/String;

    move-result-object p0

    invoke-static {p1, v1, v2, p0}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    return-void

    :cond_0
    iget-boolean p0, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzi:Z

    if-eqz p0, :cond_1

    .line 3
    invoke-virtual {p3}, Lcom/google/android/recaptcha/internal/zzaew;->zzl()Ljava/lang/String;

    move-result-object p0

    invoke-static {p1, v1, v2, p0}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    return-void

    .line 2
    :cond_1
    invoke-virtual {p3}, Lcom/google/android/recaptcha/internal/zzaew;->zzp()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    invoke-static {p1, v1, v2, p0}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    return-void
.end method


# virtual methods
.method public final zza()Ljava/lang/Object;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzg:Lcom/google/android/recaptcha/internal/zzagx;

    check-cast p0, Lcom/google/android/recaptcha/internal/zzafv;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafv;->zzC()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    return-object p0
.end method

.method public final zzb(Ljava/lang/Object;Ljava/lang/Object;)Z
    .locals 6

    const/4 v0, 0x0

    move v1, v0

    .line 1
    :goto_0
    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzc:[I

    array-length v2, v2

    const v3, 0xfffff

    if-ge v1, v2, :cond_3

    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzA(I)I

    move-result v2

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzC(I)I

    move-result v4

    const/16 v5, 0x32

    if-le v4, v5, :cond_0

    const/16 v5, 0x45

    if-ge v4, v5, :cond_0

    goto/16 :goto_2

    :cond_0
    and-int/2addr v2, v3

    int-to-long v2, v2

    packed-switch v4, :pswitch_data_0

    goto/16 :goto_2

    .line 2
    :pswitch_0
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzN(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 3
    invoke-static {p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v4

    invoke-static {p2, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    .line 4
    invoke-static {v4, v2}, Lcom/google/android/recaptcha/internal/zzaho;->zzB(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_2

    goto/16 :goto_2

    .line 5
    :pswitch_1
    invoke-static {p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v4

    invoke-static {p2, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    .line 6
    invoke-static {v4, v2}, Lcom/google/android/recaptcha/internal/zzaho;->zzB(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    goto :goto_1

    .line 7
    :pswitch_2
    invoke-static {p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v4

    invoke-static {p2, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    .line 8
    invoke-static {v4, v2}, Lcom/google/android/recaptcha/internal/zzaho;->zzB(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    :goto_1
    if-nez v2, :cond_1

    goto/16 :goto_3

    .line 9
    :pswitch_3
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzI(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 10
    invoke-static {p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v4

    invoke-static {p2, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    .line 11
    invoke-static {v4, v2}, Lcom/google/android/recaptcha/internal/zzaho;->zzB(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_2

    goto/16 :goto_2

    .line 12
    :pswitch_4
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzI(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 13
    invoke-static {p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzd(Ljava/lang/Object;J)J

    move-result-wide v4

    invoke-static {p2, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzd(Ljava/lang/Object;J)J

    move-result-wide v2

    cmp-long v2, v4, v2

    if-nez v2, :cond_2

    goto/16 :goto_2

    .line 14
    :pswitch_5
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzI(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 15
    invoke-static {p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result v4

    invoke-static {p2, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result v2

    if-ne v4, v2, :cond_2

    goto/16 :goto_2

    .line 16
    :pswitch_6
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzI(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 17
    invoke-static {p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzd(Ljava/lang/Object;J)J

    move-result-wide v4

    invoke-static {p2, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzd(Ljava/lang/Object;J)J

    move-result-wide v2

    cmp-long v2, v4, v2

    if-nez v2, :cond_2

    goto/16 :goto_2

    .line 18
    :pswitch_7
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzI(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 19
    invoke-static {p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result v4

    invoke-static {p2, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result v2

    if-ne v4, v2, :cond_2

    goto/16 :goto_2

    .line 20
    :pswitch_8
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzI(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 21
    invoke-static {p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result v4

    invoke-static {p2, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result v2

    if-ne v4, v2, :cond_2

    goto/16 :goto_2

    .line 22
    :pswitch_9
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzI(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 23
    invoke-static {p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result v4

    invoke-static {p2, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result v2

    if-ne v4, v2, :cond_2

    goto/16 :goto_2

    .line 24
    :pswitch_a
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzI(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 25
    invoke-static {p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v4

    invoke-static {p2, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    .line 26
    invoke-static {v4, v2}, Lcom/google/android/recaptcha/internal/zzaho;->zzB(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_2

    goto/16 :goto_2

    .line 27
    :pswitch_b
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzI(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 28
    invoke-static {p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v4

    invoke-static {p2, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    .line 29
    invoke-static {v4, v2}, Lcom/google/android/recaptcha/internal/zzaho;->zzB(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_2

    goto/16 :goto_2

    .line 30
    :pswitch_c
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzI(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 31
    invoke-static {p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v4

    invoke-static {p2, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    .line 32
    invoke-static {v4, v2}, Lcom/google/android/recaptcha/internal/zzaho;->zzB(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_2

    goto/16 :goto_2

    .line 33
    :pswitch_d
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzI(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 34
    invoke-static {p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzf(Ljava/lang/Object;J)Z

    move-result v4

    invoke-static {p2, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzf(Ljava/lang/Object;J)Z

    move-result v2

    if-ne v4, v2, :cond_2

    goto/16 :goto_2

    .line 35
    :pswitch_e
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzI(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 36
    invoke-static {p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result v4

    invoke-static {p2, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result v2

    if-ne v4, v2, :cond_2

    goto/16 :goto_2

    .line 37
    :pswitch_f
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzI(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 38
    invoke-static {p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzd(Ljava/lang/Object;J)J

    move-result-wide v4

    invoke-static {p2, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzd(Ljava/lang/Object;J)J

    move-result-wide v2

    cmp-long v2, v4, v2

    if-nez v2, :cond_2

    goto :goto_2

    .line 39
    :pswitch_10
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzI(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 40
    invoke-static {p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result v4

    invoke-static {p2, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result v2

    if-ne v4, v2, :cond_2

    goto :goto_2

    .line 41
    :pswitch_11
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzI(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 42
    invoke-static {p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzd(Ljava/lang/Object;J)J

    move-result-wide v4

    invoke-static {p2, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzd(Ljava/lang/Object;J)J

    move-result-wide v2

    cmp-long v2, v4, v2

    if-nez v2, :cond_2

    goto :goto_2

    .line 43
    :pswitch_12
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzI(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 44
    invoke-static {p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzd(Ljava/lang/Object;J)J

    move-result-wide v4

    invoke-static {p2, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzd(Ljava/lang/Object;J)J

    move-result-wide v2

    cmp-long v2, v4, v2

    if-nez v2, :cond_2

    goto :goto_2

    .line 45
    :pswitch_13
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzI(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 46
    invoke-static {p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzh(Ljava/lang/Object;J)F

    move-result v4

    invoke-static {v4}, Ljava/lang/Float;->floatToIntBits(F)I

    move-result v4

    .line 47
    invoke-static {p2, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzh(Ljava/lang/Object;J)F

    move-result v2

    invoke-static {v2}, Ljava/lang/Float;->floatToIntBits(F)I

    move-result v2

    if-ne v4, v2, :cond_2

    goto :goto_2

    .line 48
    :pswitch_14
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzI(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 49
    invoke-static {p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzj(Ljava/lang/Object;J)D

    move-result-wide v4

    invoke-static {v4, v5}, Ljava/lang/Double;->doubleToLongBits(D)J

    move-result-wide v4

    .line 50
    invoke-static {p2, v2, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzj(Ljava/lang/Object;J)D

    move-result-wide v2

    invoke-static {v2, v3}, Ljava/lang/Double;->doubleToLongBits(D)J

    move-result-wide v2

    cmp-long v2, v4, v2

    if-nez v2, :cond_2

    :cond_1
    :goto_2
    add-int/lit8 v1, v1, 0x3

    goto/16 :goto_0

    :cond_2
    :goto_3
    return v0

    .line 47
    :cond_3
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzl:I

    :goto_4
    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzj:[I

    array-length v4, v2

    if-ge v1, v4, :cond_7

    .line 51
    aget v2, v2, v1

    .line 52
    invoke-direct {p0, p1, p2, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzN(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-nez v4, :cond_4

    return v0

    .line 53
    :cond_4
    invoke-direct {p0, p1, v0, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v4

    if-eqz v4, :cond_5

    goto :goto_5

    .line 54
    :cond_5
    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzA(I)I

    move-result v2

    and-int/2addr v2, v3

    int-to-long v4, v2

    .line 55
    invoke-static {p1, v4, v5}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    invoke-static {p2, v4, v5}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v4

    .line 56
    invoke-static {v2, v4}, Lcom/google/android/recaptcha/internal/zzaho;->zzB(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_6

    return v0

    :cond_6
    :goto_5
    add-int/lit8 v1, v1, 0x1

    goto :goto_4

    .line 57
    :cond_7
    move-object v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzafv;

    iget-object v1, v1, Lcom/google/android/recaptcha/internal/zzafv;->zzc:Lcom/google/android/recaptcha/internal/zzaia;

    .line 58
    check-cast p2, Lcom/google/android/recaptcha/internal/zzafv;

    iget-object p2, p2, Lcom/google/android/recaptcha/internal/zzafv;->zzc:Lcom/google/android/recaptcha/internal/zzaia;

    .line 59
    invoke-virtual {v1, p2}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_8

    return v0

    :cond_8
    iget-boolean p0, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzh:Z

    if-nez p0, :cond_9

    const/4 p0, 0x1

    return p0

    .line 60
    :cond_9
    invoke-static {p1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    throw p0

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_14
        :pswitch_13
        :pswitch_12
        :pswitch_11
        :pswitch_10
        :pswitch_f
        :pswitch_e
        :pswitch_d
        :pswitch_c
        :pswitch_b
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_1
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
    .end packed-switch
.end method

.method public final zzc(Ljava/lang/Object;)I
    .locals 8

    const/4 v0, 0x0

    move v1, v0

    move v2, v1

    .line 1
    :goto_0
    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzc:[I

    array-length v3, v3

    const v4, 0xfffff

    if-ge v1, v3, :cond_3

    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzA(I)I

    move-result v3

    invoke-static {v3}, Lcom/google/android/recaptcha/internal/zzaha;->zzC(I)I

    move-result v5

    const/16 v6, 0x32

    if-le v5, v6, :cond_0

    const/16 v6, 0x45

    if-lt v5, v6, :cond_2

    :cond_0
    and-int/2addr v3, v4

    int-to-long v3, v3

    const/16 v6, 0x25

    const/16 v7, 0x20

    packed-switch v5, :pswitch_data_0

    goto/16 :goto_4

    :pswitch_0
    mul-int/lit8 v2, v2, 0x35

    .line 2
    invoke-static {p1, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/Object;->hashCode()I

    move-result v3

    :goto_1
    add-int/2addr v2, v3

    goto/16 :goto_4

    :pswitch_1
    mul-int/lit8 v2, v2, 0x35

    .line 3
    invoke-static {p1, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/Object;->hashCode()I

    move-result v3

    goto :goto_1

    :pswitch_2
    mul-int/lit8 v2, v2, 0x35

    .line 4
    invoke-static {p1, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v3

    if-eqz v3, :cond_1

    .line 5
    invoke-virtual {v3}, Ljava/lang/Object;->hashCode()I

    move-result v6

    :cond_1
    :goto_2
    add-int/2addr v2, v6

    goto/16 :goto_4

    :pswitch_3
    mul-int/lit8 v2, v2, 0x35

    .line 6
    invoke-static {p1, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzd(Ljava/lang/Object;J)J

    move-result-wide v3

    sget-object v5, Lcom/google/android/recaptcha/internal/zzagf;->zza:[B

    :goto_3
    ushr-long v5, v3, v7

    xor-long/2addr v3, v5

    long-to-int v3, v3

    goto :goto_1

    :pswitch_4
    mul-int/lit8 v2, v2, 0x35

    .line 7
    invoke-static {p1, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result v3

    goto :goto_1

    :pswitch_5
    mul-int/lit8 v2, v2, 0x35

    .line 8
    invoke-static {p1, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzd(Ljava/lang/Object;J)J

    move-result-wide v3

    sget-object v5, Lcom/google/android/recaptcha/internal/zzagf;->zza:[B

    goto :goto_3

    :pswitch_6
    mul-int/lit8 v2, v2, 0x35

    .line 9
    invoke-static {p1, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result v3

    goto :goto_1

    :pswitch_7
    mul-int/lit8 v2, v2, 0x35

    .line 10
    invoke-static {p1, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result v3

    goto :goto_1

    :pswitch_8
    mul-int/lit8 v2, v2, 0x35

    .line 11
    invoke-static {p1, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result v3

    goto :goto_1

    :pswitch_9
    mul-int/lit8 v2, v2, 0x35

    .line 12
    invoke-static {p1, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/Object;->hashCode()I

    move-result v3

    goto :goto_1

    :pswitch_a
    mul-int/lit8 v2, v2, 0x35

    .line 13
    invoke-static {p1, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v3

    if-eqz v3, :cond_1

    .line 14
    invoke-virtual {v3}, Ljava/lang/Object;->hashCode()I

    move-result v6

    goto :goto_2

    :pswitch_b
    mul-int/lit8 v2, v2, 0x35

    .line 15
    invoke-static {p1, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    invoke-virtual {v3}, Ljava/lang/String;->hashCode()I

    move-result v3

    goto :goto_1

    :pswitch_c
    mul-int/lit8 v2, v2, 0x35

    .line 16
    invoke-static {p1, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzf(Ljava/lang/Object;J)Z

    move-result v3

    invoke-static {v3}, Lcom/google/android/recaptcha/internal/zzagf;->zzb(Z)I

    move-result v3

    goto :goto_1

    :pswitch_d
    mul-int/lit8 v2, v2, 0x35

    .line 17
    invoke-static {p1, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result v3

    goto/16 :goto_1

    :pswitch_e
    mul-int/lit8 v2, v2, 0x35

    .line 18
    invoke-static {p1, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzd(Ljava/lang/Object;J)J

    move-result-wide v3

    sget-object v5, Lcom/google/android/recaptcha/internal/zzagf;->zza:[B

    goto :goto_3

    :pswitch_f
    mul-int/lit8 v2, v2, 0x35

    .line 19
    invoke-static {p1, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result v3

    goto/16 :goto_1

    :pswitch_10
    mul-int/lit8 v2, v2, 0x35

    .line 20
    invoke-static {p1, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzd(Ljava/lang/Object;J)J

    move-result-wide v3

    sget-object v5, Lcom/google/android/recaptcha/internal/zzagf;->zza:[B

    goto :goto_3

    :pswitch_11
    mul-int/lit8 v2, v2, 0x35

    .line 21
    invoke-static {p1, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzd(Ljava/lang/Object;J)J

    move-result-wide v3

    sget-object v5, Lcom/google/android/recaptcha/internal/zzagf;->zza:[B

    goto/16 :goto_3

    :pswitch_12
    mul-int/lit8 v2, v2, 0x35

    .line 22
    invoke-static {p1, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzh(Ljava/lang/Object;J)F

    move-result v3

    invoke-static {v3}, Ljava/lang/Float;->floatToIntBits(F)I

    move-result v3

    goto/16 :goto_1

    :pswitch_13
    mul-int/lit8 v2, v2, 0x35

    .line 23
    invoke-static {p1, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzj(Ljava/lang/Object;J)D

    move-result-wide v3

    invoke-static {v3, v4}, Ljava/lang/Double;->doubleToLongBits(D)J

    move-result-wide v3

    .line 24
    sget-object v5, Lcom/google/android/recaptcha/internal/zzagf;->zza:[B

    goto/16 :goto_3

    :cond_2
    :goto_4
    add-int/lit8 v1, v1, 0x3

    goto/16 :goto_0

    .line 22
    :cond_3
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzl:I

    :goto_5
    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzj:[I

    array-length v5, v3

    if-ge v1, v5, :cond_5

    .line 25
    aget v3, v3, v1

    .line 26
    invoke-direct {p0, p1, v0, v3}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-nez v5, :cond_4

    mul-int/lit8 v2, v2, 0x35

    .line 27
    invoke-direct {p0, v3}, Lcom/google/android/recaptcha/internal/zzaha;->zzA(I)I

    move-result v3

    and-int/2addr v3, v4

    int-to-long v5, v3

    invoke-static {p1, v5, v6}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/Object;->hashCode()I

    move-result v3

    add-int/2addr v2, v3

    :cond_4
    add-int/lit8 v1, v1, 0x1

    goto :goto_5

    :cond_5
    mul-int/lit8 v2, v2, 0x35

    .line 28
    move-object v0, p1

    check-cast v0, Lcom/google/android/recaptcha/internal/zzafv;

    iget-object v0, v0, Lcom/google/android/recaptcha/internal/zzafv;->zzc:Lcom/google/android/recaptcha/internal/zzaia;

    .line 29
    invoke-virtual {v0}, Ljava/lang/Object;->hashCode()I

    move-result v0

    add-int/2addr v2, v0

    iget-boolean p0, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzh:Z

    if-nez p0, :cond_6

    return v2

    .line 30
    :cond_6
    invoke-static {p1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    throw p0

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_13
        :pswitch_12
        :pswitch_11
        :pswitch_10
        :pswitch_f
        :pswitch_e
        :pswitch_d
        :pswitch_c
        :pswitch_b
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public final zzd(Ljava/lang/Object;Ljava/lang/Object;)V
    .locals 8

    .line 1
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzaha;->zzF(Ljava/lang/Object;)V

    .line 2
    invoke-virtual {p2}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    const/4 v0, 0x0

    :goto_0
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzc:[I

    array-length v2, v1

    if-ge v0, v2, :cond_4

    .line 3
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzA(I)I

    move-result v2

    const v3, 0xfffff

    and-int/2addr v3, v2

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzC(I)I

    move-result v2

    .line 4
    aget v1, v1, v0

    int-to-long v3, v3

    packed-switch v2, :pswitch_data_0

    goto/16 :goto_2

    .line 13
    :pswitch_0
    invoke-direct {p0, p1, p2, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzp(Ljava/lang/Object;Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 14
    :pswitch_1
    invoke-direct {p0, p2, v1, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v2

    if-eqz v2, :cond_3

    .line 15
    invoke-static {p2, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    invoke-static {p1, v3, v4, v2}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 16
    invoke-direct {p0, p1, v1, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzO(Ljava/lang/Object;II)V

    goto/16 :goto_2

    .line 17
    :pswitch_2
    invoke-direct {p0, p1, p2, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzp(Ljava/lang/Object;Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 18
    :pswitch_3
    invoke-direct {p0, p2, v1, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v2

    if-eqz v2, :cond_3

    .line 19
    invoke-static {p2, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    invoke-static {p1, v3, v4, v2}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 20
    invoke-direct {p0, p1, v1, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzO(Ljava/lang/Object;II)V

    goto/16 :goto_2

    .line 21
    :pswitch_4
    sget v1, Lcom/google/android/recaptcha/internal/zzaho;->$r8$clinit:I

    .line 22
    invoke-static {p1, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v1

    invoke-static {p2, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    .line 23
    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zzagt;->zzb(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    .line 24
    invoke-static {p1, v3, v4, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    goto/16 :goto_2

    .line 5
    :pswitch_5
    invoke-static {p1, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzage;

    .line 6
    invoke-static {p2, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/android/recaptcha/internal/zzage;

    .line 7
    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v5

    .line 8
    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v6

    if-lez v5, :cond_1

    if-lez v6, :cond_1

    .line 9
    invoke-interface {v1}, Lcom/google/android/recaptcha/internal/zzage;->zza()Z

    move-result v7

    if-nez v7, :cond_0

    add-int/2addr v6, v5

    .line 10
    invoke-interface {v1, v6}, Lcom/google/android/recaptcha/internal/zzage;->zzg(I)Lcom/google/android/recaptcha/internal/zzage;

    move-result-object v1

    .line 11
    :cond_0
    invoke-interface {v1, v2}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    :cond_1
    if-gtz v5, :cond_2

    goto :goto_1

    :cond_2
    move-object v2, v1

    .line 12
    :goto_1
    invoke-static {p1, v3, v4, v2}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    goto/16 :goto_2

    .line 25
    :pswitch_6
    invoke-direct {p0, p1, p2, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzo(Ljava/lang/Object;Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 26
    :pswitch_7
    invoke-direct {p0, p2, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzK(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 27
    invoke-static {p2, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzd(Ljava/lang/Object;J)J

    move-result-wide v1

    invoke-static {p1, v3, v4, v1, v2}, Lcom/google/android/recaptcha/internal/zzaig;->zze(Ljava/lang/Object;JJ)V

    .line 28
    invoke-direct {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 29
    :pswitch_8
    invoke-direct {p0, p2, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzK(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 30
    invoke-static {p2, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result v1

    invoke-static {p1, v3, v4, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzc(Ljava/lang/Object;JI)V

    .line 31
    invoke-direct {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 32
    :pswitch_9
    invoke-direct {p0, p2, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzK(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 33
    invoke-static {p2, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzd(Ljava/lang/Object;J)J

    move-result-wide v1

    invoke-static {p1, v3, v4, v1, v2}, Lcom/google/android/recaptcha/internal/zzaig;->zze(Ljava/lang/Object;JJ)V

    .line 34
    invoke-direct {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 35
    :pswitch_a
    invoke-direct {p0, p2, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzK(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 36
    invoke-static {p2, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result v1

    invoke-static {p1, v3, v4, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzc(Ljava/lang/Object;JI)V

    .line 37
    invoke-direct {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 38
    :pswitch_b
    invoke-direct {p0, p2, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzK(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 39
    invoke-static {p2, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result v1

    invoke-static {p1, v3, v4, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzc(Ljava/lang/Object;JI)V

    .line 40
    invoke-direct {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 41
    :pswitch_c
    invoke-direct {p0, p2, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzK(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 42
    invoke-static {p2, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result v1

    invoke-static {p1, v3, v4, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzc(Ljava/lang/Object;JI)V

    .line 43
    invoke-direct {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 44
    :pswitch_d
    invoke-direct {p0, p2, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzK(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 45
    invoke-static {p2, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v1

    invoke-static {p1, v3, v4, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 46
    invoke-direct {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 47
    :pswitch_e
    invoke-direct {p0, p1, p2, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzo(Ljava/lang/Object;Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 48
    :pswitch_f
    invoke-direct {p0, p2, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzK(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 49
    invoke-static {p2, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v1

    invoke-static {p1, v3, v4, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 50
    invoke-direct {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 51
    :pswitch_10
    invoke-direct {p0, p2, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzK(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 52
    invoke-static {p2, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzf(Ljava/lang/Object;J)Z

    move-result v1

    invoke-static {p1, v3, v4, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzg(Ljava/lang/Object;JZ)V

    .line 53
    invoke-direct {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 54
    :pswitch_11
    invoke-direct {p0, p2, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzK(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 55
    invoke-static {p2, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result v1

    invoke-static {p1, v3, v4, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzc(Ljava/lang/Object;JI)V

    .line 56
    invoke-direct {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto :goto_2

    .line 57
    :pswitch_12
    invoke-direct {p0, p2, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzK(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 58
    invoke-static {p2, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzd(Ljava/lang/Object;J)J

    move-result-wide v1

    invoke-static {p1, v3, v4, v1, v2}, Lcom/google/android/recaptcha/internal/zzaig;->zze(Ljava/lang/Object;JJ)V

    .line 59
    invoke-direct {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto :goto_2

    .line 60
    :pswitch_13
    invoke-direct {p0, p2, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzK(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 61
    invoke-static {p2, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzb(Ljava/lang/Object;J)I

    move-result v1

    invoke-static {p1, v3, v4, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzc(Ljava/lang/Object;JI)V

    .line 62
    invoke-direct {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto :goto_2

    .line 63
    :pswitch_14
    invoke-direct {p0, p2, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzK(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 64
    invoke-static {p2, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzd(Ljava/lang/Object;J)J

    move-result-wide v1

    invoke-static {p1, v3, v4, v1, v2}, Lcom/google/android/recaptcha/internal/zzaig;->zze(Ljava/lang/Object;JJ)V

    .line 65
    invoke-direct {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto :goto_2

    .line 66
    :pswitch_15
    invoke-direct {p0, p2, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzK(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 67
    invoke-static {p2, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzd(Ljava/lang/Object;J)J

    move-result-wide v1

    invoke-static {p1, v3, v4, v1, v2}, Lcom/google/android/recaptcha/internal/zzaig;->zze(Ljava/lang/Object;JJ)V

    .line 68
    invoke-direct {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto :goto_2

    .line 69
    :pswitch_16
    invoke-direct {p0, p2, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzK(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 70
    invoke-static {p2, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzh(Ljava/lang/Object;J)F

    move-result v1

    invoke-static {p1, v3, v4, v1}, Lcom/google/android/recaptcha/internal/zzaig;->zzi(Ljava/lang/Object;JF)V

    .line 71
    invoke-direct {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto :goto_2

    .line 72
    :pswitch_17
    invoke-direct {p0, p2, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzK(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 73
    invoke-static {p2, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzj(Ljava/lang/Object;J)D

    move-result-wide v1

    invoke-static {p1, v3, v4, v1, v2}, Lcom/google/android/recaptcha/internal/zzaig;->zzk(Ljava/lang/Object;JD)V

    .line 74
    invoke-direct {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    :cond_3
    :goto_2
    add-int/lit8 v0, v0, 0x3

    goto/16 :goto_0

    :cond_4
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzm:Lcom/google/android/recaptcha/internal/zzahz;

    .line 75
    invoke-static {v0, p1, p2}, Lcom/google/android/recaptcha/internal/zzaho;->zzD(Lcom/google/android/recaptcha/internal/zzahz;Ljava/lang/Object;Ljava/lang/Object;)V

    iget-boolean v0, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzh:Z

    if-eqz v0, :cond_5

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzn:Lcom/google/android/recaptcha/internal/zzafi;

    .line 76
    invoke-static {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzaho;->zzC(Lcom/google/android/recaptcha/internal/zzafi;Ljava/lang/Object;Ljava/lang/Object;)V

    :cond_5
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_17
        :pswitch_16
        :pswitch_15
        :pswitch_14
        :pswitch_13
        :pswitch_12
        :pswitch_11
        :pswitch_10
        :pswitch_f
        :pswitch_e
        :pswitch_d
        :pswitch_c
        :pswitch_b
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_3
        :pswitch_3
        :pswitch_3
        :pswitch_3
        :pswitch_3
        :pswitch_3
        :pswitch_3
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public final zze(Ljava/lang/Object;)I
    .locals 17

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    .line 1
    sget-object v6, Lcom/google/android/recaptcha/internal/zzaha;->zzb:Lsun/misc/Unsafe;

    const/4 v7, 0x0

    const v8, 0xfffff

    move v2, v7

    move v4, v2

    move v9, v4

    move v3, v8

    :goto_0
    iget-object v5, v0, Lcom/google/android/recaptcha/internal/zzaha;->zzc:[I

    array-length v10, v5

    const/4 v11, 0x0

    if-ge v2, v10, :cond_1c

    invoke-direct {v0, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzA(I)I

    move-result v10

    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzaha;->zzC(I)I

    move-result v12

    .line 2
    aget v13, v5, v2

    add-int/lit8 v14, v2, 0x2

    .line 3
    aget v5, v5, v14

    and-int v14, v5, v8

    const/16 v15, 0x11

    const/16 v16, 0x1

    if-gt v12, v15, :cond_2

    if-eq v14, v3, :cond_1

    if-ne v14, v8, :cond_0

    move v4, v7

    goto :goto_1

    :cond_0
    int-to-long v3, v14

    .line 4
    invoke-virtual {v6, v1, v3, v4}, Lsun/misc/Unsafe;->getInt(Ljava/lang/Object;J)I

    move-result v3

    move v4, v3

    :goto_1
    move v3, v14

    :cond_1
    ushr-int/lit8 v5, v5, 0x14

    shl-int v5, v16, v5

    goto :goto_2

    :cond_2
    move v5, v7

    :goto_2
    and-int/2addr v10, v8

    .line 5
    sget-object v14, Lcom/google/android/recaptcha/internal/zzafn;->zzJ:Lcom/google/android/recaptcha/internal/zzafn;

    .line 6
    invoke-virtual {v14}, Lcom/google/android/recaptcha/internal/zzafn;->zza()I

    move-result v14

    if-lt v12, v14, :cond_3

    sget-object v14, Lcom/google/android/recaptcha/internal/zzafn;->zzW:Lcom/google/android/recaptcha/internal/zzafn;

    .line 5
    invoke-virtual {v14}, Lcom/google/android/recaptcha/internal/zzafn;->zza()I

    :cond_3
    int-to-long v14, v10

    const/16 v10, 0x3f

    packed-switch v12, :pswitch_data_0

    goto/16 :goto_1e

    .line 7
    :pswitch_0
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_1b

    .line 8
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lcom/google/android/recaptcha/internal/zzagx;

    .line 9
    invoke-direct {v0, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v10

    .line 10
    invoke-static {v13, v5, v10}, Lcom/google/android/recaptcha/internal/zzaho;->zzz(ILcom/google/android/recaptcha/internal/zzagx;Lcom/google/android/recaptcha/internal/zzahn;)I

    move-result v5

    :goto_3
    add-int/2addr v9, v5

    goto/16 :goto_1e

    .line 11
    :pswitch_1
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_1b

    shl-int/lit8 v5, v13, 0x3

    .line 12
    invoke-static {v1, v14, v15}, Lcom/google/android/recaptcha/internal/zzaha;->zzH(Ljava/lang/Object;J)J

    move-result-wide v11

    add-long v13, v11, v11

    shr-long v10, v11, v10

    .line 13
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v5

    xor-long/2addr v10, v13

    .line 14
    invoke-static {v10, v11}, Lcom/google/android/recaptcha/internal/zzafa;->zzv(J)I

    move-result v10

    :goto_4
    add-int/2addr v5, v10

    goto :goto_3

    .line 15
    :pswitch_2
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_1b

    shl-int/lit8 v5, v13, 0x3

    .line 16
    invoke-static {v1, v14, v15}, Lcom/google/android/recaptcha/internal/zzaha;->zzG(Ljava/lang/Object;J)I

    move-result v10

    add-int v11, v10, v10

    shr-int/lit8 v10, v10, 0x1f

    .line 17
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v5

    xor-int/2addr v10, v11

    .line 18
    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v10

    goto :goto_4

    .line 19
    :pswitch_3
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_1b

    shl-int/lit8 v5, v13, 0x3

    .line 20
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v5

    :goto_5
    add-int/lit8 v5, v5, 0x8

    goto :goto_3

    .line 21
    :pswitch_4
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_1b

    shl-int/lit8 v5, v13, 0x3

    .line 22
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v5

    :goto_6
    add-int/lit8 v5, v5, 0x4

    goto :goto_3

    .line 23
    :pswitch_5
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_1b

    shl-int/lit8 v5, v13, 0x3

    .line 24
    invoke-static {v1, v14, v15}, Lcom/google/android/recaptcha/internal/zzaha;->zzG(Ljava/lang/Object;J)I

    move-result v10

    int-to-long v10, v10

    .line 25
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v5

    .line 26
    invoke-static {v10, v11}, Lcom/google/android/recaptcha/internal/zzafa;->zzv(J)I

    move-result v10

    goto :goto_4

    .line 27
    :pswitch_6
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_1b

    shl-int/lit8 v5, v13, 0x3

    .line 28
    invoke-static {v1, v14, v15}, Lcom/google/android/recaptcha/internal/zzaha;->zzG(Ljava/lang/Object;J)I

    move-result v10

    .line 29
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v5

    .line 30
    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v10

    goto :goto_4

    .line 31
    :pswitch_7
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_1b

    shl-int/lit8 v5, v13, 0x3

    .line 32
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v10

    check-cast v10, Lcom/google/android/recaptcha/internal/zzaer;

    .line 33
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v5

    .line 34
    invoke-virtual {v10}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v10

    .line 35
    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    :goto_7
    add-int/2addr v11, v10

    add-int/2addr v5, v11

    goto/16 :goto_3

    .line 36
    :pswitch_8
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_1b

    shl-int/lit8 v5, v13, 0x3

    .line 37
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v10

    .line 38
    invoke-direct {v0, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v11

    sget v12, Lcom/google/android/recaptcha/internal/zzaho;->$r8$clinit:I

    .line 39
    check-cast v10, Lcom/google/android/recaptcha/internal/zzaec;

    .line 40
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v5

    .line 41
    invoke-virtual {v10, v11}, Lcom/google/android/recaptcha/internal/zzaec;->zzx(Lcom/google/android/recaptcha/internal/zzahn;)I

    move-result v10

    .line 42
    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    goto :goto_7

    .line 43
    :pswitch_9
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_1b

    shl-int/lit8 v5, v13, 0x3

    .line 44
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v10

    instance-of v11, v10, Lcom/google/android/recaptcha/internal/zzaer;

    if-eqz v11, :cond_4

    .line 45
    check-cast v10, Lcom/google/android/recaptcha/internal/zzaer;

    .line 46
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v5

    .line 47
    invoke-virtual {v10}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v10

    .line 48
    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    goto :goto_7

    .line 49
    :cond_4
    check-cast v10, Ljava/lang/String;

    .line 50
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v5

    .line 51
    sget v11, Lcom/google/android/recaptcha/internal/zzail;->$r8$clinit:I

    .line 52
    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzaij;->zzb(Ljava/lang/String;)I

    move-result v10

    .line 53
    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    goto :goto_7

    .line 54
    :pswitch_a
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_1b

    shl-int/lit8 v5, v13, 0x3

    .line 55
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v5

    add-int/lit8 v5, v5, 0x1

    goto/16 :goto_3

    .line 56
    :pswitch_b
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_1b

    shl-int/lit8 v5, v13, 0x3

    .line 57
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v5

    goto/16 :goto_6

    .line 58
    :pswitch_c
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_1b

    shl-int/lit8 v5, v13, 0x3

    .line 59
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v5

    goto/16 :goto_5

    .line 60
    :pswitch_d
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_1b

    shl-int/lit8 v5, v13, 0x3

    .line 61
    invoke-static {v1, v14, v15}, Lcom/google/android/recaptcha/internal/zzaha;->zzG(Ljava/lang/Object;J)I

    move-result v10

    int-to-long v10, v10

    .line 62
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v5

    .line 63
    invoke-static {v10, v11}, Lcom/google/android/recaptcha/internal/zzafa;->zzv(J)I

    move-result v10

    goto/16 :goto_4

    .line 64
    :pswitch_e
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_1b

    shl-int/lit8 v5, v13, 0x3

    .line 65
    invoke-static {v1, v14, v15}, Lcom/google/android/recaptcha/internal/zzaha;->zzH(Ljava/lang/Object;J)J

    move-result-wide v10

    .line 66
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v5

    .line 67
    invoke-static {v10, v11}, Lcom/google/android/recaptcha/internal/zzafa;->zzv(J)I

    move-result v10

    goto/16 :goto_4

    .line 68
    :pswitch_f
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_1b

    shl-int/lit8 v5, v13, 0x3

    .line 69
    invoke-static {v1, v14, v15}, Lcom/google/android/recaptcha/internal/zzaha;->zzH(Ljava/lang/Object;J)J

    move-result-wide v10

    .line 70
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v5

    .line 71
    invoke-static {v10, v11}, Lcom/google/android/recaptcha/internal/zzafa;->zzv(J)I

    move-result v10

    goto/16 :goto_4

    .line 72
    :pswitch_10
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_1b

    shl-int/lit8 v5, v13, 0x3

    .line 73
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v5

    goto/16 :goto_6

    .line 74
    :pswitch_11
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_1b

    shl-int/lit8 v5, v13, 0x3

    .line 75
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v5

    goto/16 :goto_5

    .line 76
    :pswitch_12
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    invoke-direct {v0, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzr(I)Ljava/lang/Object;

    move-result-object v10

    .line 77
    check-cast v5, Lcom/google/android/recaptcha/internal/zzags;

    .line 78
    invoke-static {v10}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 79
    invoke-virtual {v5}, Ljava/util/AbstractMap;->isEmpty()Z

    move-result v10

    if-nez v10, :cond_1b

    .line 80
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzags;->entrySet()Ljava/util/Set;

    move-result-object v5

    invoke-interface {v5}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v5

    invoke-interface {v5}, Ljava/util/Iterator;->hasNext()Z

    move-result v10

    if-nez v10, :cond_5

    goto/16 :goto_1e

    :cond_5
    invoke-interface {v5}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/Map$Entry;

    .line 308
    invoke-interface {v0}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    invoke-interface {v0}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    .line 309
    throw v11

    .line 81
    :pswitch_13
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 82
    invoke-direct {v0, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v10

    .line 83
    sget v11, Lcom/google/android/recaptcha/internal/zzaho;->$r8$clinit:I

    .line 84
    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v11

    if-nez v11, :cond_6

    move v14, v7

    goto :goto_9

    :cond_6
    move v12, v7

    move v14, v12

    :goto_8
    if-ge v12, v11, :cond_7

    .line 85
    invoke-interface {v5, v12}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v15

    check-cast v15, Lcom/google/android/recaptcha/internal/zzagx;

    invoke-static {v13, v15, v10}, Lcom/google/android/recaptcha/internal/zzaho;->zzz(ILcom/google/android/recaptcha/internal/zzagx;Lcom/google/android/recaptcha/internal/zzahn;)I

    move-result v15

    add-int/2addr v14, v15

    add-int/lit8 v12, v12, 0x1

    goto :goto_8

    :cond_7
    :goto_9
    add-int/2addr v9, v14

    goto/16 :goto_1e

    .line 86
    :pswitch_14
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 87
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzaho;->zzq(Ljava/util/List;)I

    move-result v5

    if-lez v5, :cond_1b

    shl-int/lit8 v10, v13, 0x3

    .line 88
    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v10

    .line 89
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    :goto_a
    add-int/2addr v10, v11

    add-int/2addr v10, v5

    :cond_8
    :goto_b
    add-int/2addr v9, v10

    goto/16 :goto_1e

    .line 90
    :pswitch_15
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 91
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzaho;->zzu(Ljava/util/List;)I

    move-result v5

    if-lez v5, :cond_1b

    shl-int/lit8 v10, v13, 0x3

    .line 92
    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v10

    .line 93
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    goto :goto_a

    .line 94
    :pswitch_16
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 95
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzaho;->zzx(Ljava/util/List;)I

    move-result v5

    if-lez v5, :cond_1b

    shl-int/lit8 v10, v13, 0x3

    .line 96
    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v10

    .line 97
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    goto :goto_a

    .line 98
    :pswitch_17
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 99
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzaho;->zzv(Ljava/util/List;)I

    move-result v5

    if-lez v5, :cond_1b

    shl-int/lit8 v10, v13, 0x3

    .line 100
    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v10

    .line 101
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    goto :goto_a

    .line 102
    :pswitch_18
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 103
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzaho;->zzr(Ljava/util/List;)I

    move-result v5

    if-lez v5, :cond_1b

    shl-int/lit8 v10, v13, 0x3

    .line 104
    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v10

    .line 105
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    goto :goto_a

    .line 106
    :pswitch_19
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 107
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzaho;->zzt(Ljava/util/List;)I

    move-result v5

    if-lez v5, :cond_1b

    shl-int/lit8 v10, v13, 0x3

    .line 108
    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v10

    .line 109
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    goto :goto_a

    .line 110
    :pswitch_1a
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 111
    sget v10, Lcom/google/android/recaptcha/internal/zzaho;->$r8$clinit:I

    .line 112
    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v5

    if-lez v5, :cond_1b

    shl-int/lit8 v10, v13, 0x3

    .line 113
    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v10

    .line 114
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    goto/16 :goto_a

    .line 115
    :pswitch_1b
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 116
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzaho;->zzv(Ljava/util/List;)I

    move-result v5

    if-lez v5, :cond_1b

    shl-int/lit8 v10, v13, 0x3

    .line 117
    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v10

    .line 118
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    goto/16 :goto_a

    .line 119
    :pswitch_1c
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 120
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzaho;->zzx(Ljava/util/List;)I

    move-result v5

    if-lez v5, :cond_1b

    shl-int/lit8 v10, v13, 0x3

    .line 121
    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v10

    .line 122
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    goto/16 :goto_a

    .line 123
    :pswitch_1d
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 124
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzaho;->zzs(Ljava/util/List;)I

    move-result v5

    if-lez v5, :cond_1b

    shl-int/lit8 v10, v13, 0x3

    .line 125
    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v10

    .line 126
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    goto/16 :goto_a

    .line 127
    :pswitch_1e
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 128
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzaho;->zzp(Ljava/util/List;)I

    move-result v5

    if-lez v5, :cond_1b

    shl-int/lit8 v10, v13, 0x3

    .line 129
    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v10

    .line 130
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    goto/16 :goto_a

    .line 131
    :pswitch_1f
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 132
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzaho;->zzo(Ljava/util/List;)I

    move-result v5

    if-lez v5, :cond_1b

    shl-int/lit8 v10, v13, 0x3

    .line 133
    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v10

    .line 134
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    goto/16 :goto_a

    .line 135
    :pswitch_20
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 136
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzaho;->zzv(Ljava/util/List;)I

    move-result v5

    if-lez v5, :cond_1b

    shl-int/lit8 v10, v13, 0x3

    .line 137
    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v10

    .line 138
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    goto/16 :goto_a

    .line 139
    :pswitch_21
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 140
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzaho;->zzx(Ljava/util/List;)I

    move-result v5

    if-lez v5, :cond_1b

    shl-int/lit8 v10, v13, 0x3

    .line 141
    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v10

    .line 142
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    goto/16 :goto_a

    .line 143
    :pswitch_22
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 144
    sget v10, Lcom/google/android/recaptcha/internal/zzaho;->$r8$clinit:I

    .line 145
    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v10

    if-nez v10, :cond_9

    :goto_c
    move v5, v7

    goto/16 :goto_3

    :cond_9
    shl-int/lit8 v11, v13, 0x3

    .line 146
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzaho;->zzq(Ljava/util/List;)I

    move-result v5

    .line 147
    invoke-static {v11}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    :goto_d
    mul-int/2addr v10, v11

    goto/16 :goto_4

    .line 148
    :pswitch_23
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 149
    sget v10, Lcom/google/android/recaptcha/internal/zzaho;->$r8$clinit:I

    .line 150
    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v10

    if-nez v10, :cond_a

    goto :goto_c

    :cond_a
    shl-int/lit8 v11, v13, 0x3

    .line 151
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzaho;->zzu(Ljava/util/List;)I

    move-result v5

    .line 152
    invoke-static {v11}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    goto :goto_d

    .line 153
    :pswitch_24
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 154
    invoke-static {v13, v5, v7}, Lcom/google/android/recaptcha/internal/zzaho;->zzy(ILjava/util/List;Z)I

    move-result v5

    goto/16 :goto_3

    .line 155
    :pswitch_25
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 156
    invoke-static {v13, v5, v7}, Lcom/google/android/recaptcha/internal/zzaho;->zzw(ILjava/util/List;Z)I

    move-result v5

    goto/16 :goto_3

    .line 157
    :pswitch_26
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 158
    sget v10, Lcom/google/android/recaptcha/internal/zzaho;->$r8$clinit:I

    .line 159
    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v10

    if-nez v10, :cond_b

    goto :goto_c

    :cond_b
    shl-int/lit8 v11, v13, 0x3

    .line 160
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzaho;->zzr(Ljava/util/List;)I

    move-result v5

    .line 161
    invoke-static {v11}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    goto :goto_d

    .line 162
    :pswitch_27
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 163
    sget v10, Lcom/google/android/recaptcha/internal/zzaho;->$r8$clinit:I

    .line 164
    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v10

    if-nez v10, :cond_c

    goto :goto_c

    :cond_c
    shl-int/lit8 v11, v13, 0x3

    .line 165
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzaho;->zzt(Ljava/util/List;)I

    move-result v5

    .line 166
    invoke-static {v11}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    goto :goto_d

    .line 167
    :pswitch_28
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 168
    sget v10, Lcom/google/android/recaptcha/internal/zzaho;->$r8$clinit:I

    .line 169
    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v10

    if-nez v10, :cond_d

    move v10, v7

    goto/16 :goto_b

    :cond_d
    shl-int/lit8 v11, v13, 0x3

    .line 170
    invoke-static {v11}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    mul-int/2addr v10, v11

    move v11, v7

    .line 171
    :goto_e
    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v12

    if-ge v11, v12, :cond_8

    .line 172
    invoke-interface {v5, v11}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v12

    check-cast v12, Lcom/google/android/recaptcha/internal/zzaer;

    .line 173
    invoke-virtual {v12}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v12

    .line 174
    invoke-static {v12}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v13

    add-int/2addr v13, v12

    add-int/2addr v10, v13

    add-int/lit8 v11, v11, 0x1

    goto :goto_e

    .line 175
    :pswitch_29
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    invoke-direct {v0, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v10

    .line 176
    sget v11, Lcom/google/android/recaptcha/internal/zzaho;->$r8$clinit:I

    .line 177
    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v11

    if-nez v11, :cond_e

    move v12, v7

    goto :goto_10

    :cond_e
    shl-int/lit8 v12, v13, 0x3

    .line 178
    invoke-static {v12}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v12

    mul-int/2addr v12, v11

    move v13, v7

    :goto_f
    if-ge v13, v11, :cond_f

    .line 179
    invoke-interface {v5, v13}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v14

    .line 180
    check-cast v14, Lcom/google/android/recaptcha/internal/zzaec;

    .line 181
    invoke-virtual {v14, v10}, Lcom/google/android/recaptcha/internal/zzaec;->zzx(Lcom/google/android/recaptcha/internal/zzahn;)I

    move-result v14

    .line 182
    invoke-static {v14}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v15

    add-int/2addr v15, v14

    add-int/2addr v12, v15

    add-int/lit8 v13, v13, 0x1

    goto :goto_f

    :cond_f
    :goto_10
    add-int/2addr v9, v12

    goto/16 :goto_1e

    .line 183
    :pswitch_2a
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    sget v10, Lcom/google/android/recaptcha/internal/zzaho;->$r8$clinit:I

    .line 184
    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v10

    if-nez v10, :cond_10

    :goto_11
    move v11, v7

    goto :goto_18

    :cond_10
    shl-int/lit8 v11, v13, 0x3

    .line 185
    invoke-static {v11}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    mul-int/2addr v11, v10

    instance-of v12, v5, Lcom/google/android/recaptcha/internal/zzagn;

    if-eqz v12, :cond_12

    .line 194
    check-cast v5, Lcom/google/android/recaptcha/internal/zzagn;

    move v12, v7

    :goto_12
    if-ge v12, v10, :cond_14

    .line 195
    invoke-interface {v5}, Lcom/google/android/recaptcha/internal/zzagn;->zzc()Ljava/lang/Object;

    move-result-object v13

    instance-of v14, v13, Lcom/google/android/recaptcha/internal/zzaer;

    if-eqz v14, :cond_11

    .line 196
    check-cast v13, Lcom/google/android/recaptcha/internal/zzaer;

    .line 197
    invoke-virtual {v13}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v13

    .line 198
    invoke-static {v13}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v14

    :goto_13
    add-int/2addr v14, v13

    add-int/2addr v11, v14

    goto :goto_14

    .line 199
    :cond_11
    check-cast v13, Ljava/lang/String;

    .line 200
    sget v14, Lcom/google/android/recaptcha/internal/zzail;->$r8$clinit:I

    .line 201
    invoke-static {v13}, Lcom/google/android/recaptcha/internal/zzaij;->zzb(Ljava/lang/String;)I

    move-result v13

    .line 202
    invoke-static {v13}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v14

    goto :goto_13

    :goto_14
    add-int/lit8 v12, v12, 0x1

    goto :goto_12

    :cond_12
    move v12, v7

    :goto_15
    if-ge v12, v10, :cond_14

    .line 186
    invoke-interface {v5, v12}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v13

    instance-of v14, v13, Lcom/google/android/recaptcha/internal/zzaer;

    if-eqz v14, :cond_13

    .line 187
    check-cast v13, Lcom/google/android/recaptcha/internal/zzaer;

    .line 188
    invoke-virtual {v13}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v13

    .line 189
    invoke-static {v13}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v14

    :goto_16
    add-int/2addr v14, v13

    add-int/2addr v11, v14

    goto :goto_17

    .line 190
    :cond_13
    check-cast v13, Ljava/lang/String;

    .line 191
    sget v14, Lcom/google/android/recaptcha/internal/zzail;->$r8$clinit:I

    .line 192
    invoke-static {v13}, Lcom/google/android/recaptcha/internal/zzaij;->zzb(Ljava/lang/String;)I

    move-result v13

    .line 193
    invoke-static {v13}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v14

    goto :goto_16

    :goto_17
    add-int/lit8 v12, v12, 0x1

    goto :goto_15

    :cond_14
    :goto_18
    add-int/2addr v9, v11

    goto/16 :goto_1e

    .line 203
    :pswitch_2b
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 204
    sget v10, Lcom/google/android/recaptcha/internal/zzaho;->$r8$clinit:I

    .line 205
    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v5

    if-nez v5, :cond_15

    goto/16 :goto_c

    :cond_15
    shl-int/lit8 v10, v13, 0x3

    .line 206
    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v10

    add-int/lit8 v10, v10, 0x1

    mul-int/2addr v5, v10

    goto/16 :goto_3

    .line 207
    :pswitch_2c
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 208
    invoke-static {v13, v5, v7}, Lcom/google/android/recaptcha/internal/zzaho;->zzw(ILjava/util/List;Z)I

    move-result v5

    goto/16 :goto_3

    .line 209
    :pswitch_2d
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 210
    invoke-static {v13, v5, v7}, Lcom/google/android/recaptcha/internal/zzaho;->zzy(ILjava/util/List;Z)I

    move-result v5

    goto/16 :goto_3

    .line 211
    :pswitch_2e
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 212
    sget v10, Lcom/google/android/recaptcha/internal/zzaho;->$r8$clinit:I

    .line 213
    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v10

    if-nez v10, :cond_16

    goto/16 :goto_c

    :cond_16
    shl-int/lit8 v11, v13, 0x3

    .line 214
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzaho;->zzs(Ljava/util/List;)I

    move-result v5

    .line 215
    invoke-static {v11}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    goto/16 :goto_d

    .line 216
    :pswitch_2f
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 217
    sget v10, Lcom/google/android/recaptcha/internal/zzaho;->$r8$clinit:I

    .line 218
    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v10

    if-nez v10, :cond_17

    goto/16 :goto_c

    :cond_17
    shl-int/lit8 v11, v13, 0x3

    .line 219
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzaho;->zzp(Ljava/util/List;)I

    move-result v5

    .line 220
    invoke-static {v11}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    goto/16 :goto_d

    .line 221
    :pswitch_30
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 222
    sget v10, Lcom/google/android/recaptcha/internal/zzaho;->$r8$clinit:I

    .line 223
    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v10

    if-nez v10, :cond_18

    goto/16 :goto_11

    :cond_18
    shl-int/lit8 v10, v13, 0x3

    .line 224
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzaho;->zzo(Ljava/util/List;)I

    move-result v11

    .line 225
    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v5

    .line 226
    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v10

    mul-int/2addr v5, v10

    add-int/2addr v11, v5

    goto/16 :goto_18

    .line 227
    :pswitch_31
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 228
    invoke-static {v13, v5, v7}, Lcom/google/android/recaptcha/internal/zzaho;->zzw(ILjava/util/List;Z)I

    move-result v5

    goto/16 :goto_3

    .line 229
    :pswitch_32
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 230
    invoke-static {v13, v5, v7}, Lcom/google/android/recaptcha/internal/zzaho;->zzy(ILjava/util/List;Z)I

    move-result v5

    goto/16 :goto_3

    .line 231
    :pswitch_33
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_1b

    .line 232
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lcom/google/android/recaptcha/internal/zzagx;

    .line 233
    invoke-direct {v0, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v10

    .line 234
    invoke-static {v13, v5, v10}, Lcom/google/android/recaptcha/internal/zzaho;->zzz(ILcom/google/android/recaptcha/internal/zzagx;Lcom/google/android/recaptcha/internal/zzahn;)I

    move-result v5

    goto/16 :goto_3

    .line 235
    :pswitch_34
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_19

    shl-int/lit8 v0, v13, 0x3

    .line 236
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getLong(Ljava/lang/Object;J)J

    move-result-wide v11

    add-long v13, v11, v11

    shr-long v10, v11, v10

    .line 237
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v0

    xor-long/2addr v10, v13

    .line 238
    invoke-static {v10, v11}, Lcom/google/android/recaptcha/internal/zzafa;->zzv(J)I

    move-result v5

    :goto_19
    add-int/2addr v0, v5

    :goto_1a
    add-int/2addr v9, v0

    :cond_19
    move-object/from16 v0, p0

    goto/16 :goto_1e

    .line 239
    :pswitch_35
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_19

    shl-int/lit8 v0, v13, 0x3

    .line 240
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getInt(Ljava/lang/Object;J)I

    move-result v5

    add-int v10, v5, v5

    shr-int/lit8 v5, v5, 0x1f

    .line 241
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v0

    xor-int/2addr v5, v10

    .line 242
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v5

    goto :goto_19

    .line 243
    :pswitch_36
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_19

    shl-int/lit8 v0, v13, 0x3

    .line 244
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v0

    :goto_1b
    add-int/lit8 v0, v0, 0x8

    goto :goto_1a

    .line 245
    :pswitch_37
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_19

    shl-int/lit8 v0, v13, 0x3

    .line 246
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v0

    :goto_1c
    add-int/lit8 v0, v0, 0x4

    goto :goto_1a

    .line 247
    :pswitch_38
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_19

    shl-int/lit8 v0, v13, 0x3

    .line 248
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getInt(Ljava/lang/Object;J)I

    move-result v5

    int-to-long v10, v5

    .line 249
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v0

    .line 250
    invoke-static {v10, v11}, Lcom/google/android/recaptcha/internal/zzafa;->zzv(J)I

    move-result v5

    goto :goto_19

    .line 251
    :pswitch_39
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_19

    shl-int/lit8 v0, v13, 0x3

    .line 252
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getInt(Ljava/lang/Object;J)I

    move-result v5

    .line 253
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v0

    .line 254
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v5

    goto :goto_19

    .line 255
    :pswitch_3a
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_19

    shl-int/lit8 v0, v13, 0x3

    .line 256
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lcom/google/android/recaptcha/internal/zzaer;

    .line 257
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v0

    .line 258
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v5

    .line 259
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v10

    :goto_1d
    add-int/2addr v10, v5

    add-int/2addr v0, v10

    goto/16 :goto_1a

    .line 260
    :pswitch_3b
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_1b

    shl-int/lit8 v5, v13, 0x3

    .line 261
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v10

    .line 262
    invoke-direct {v0, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v11

    sget v12, Lcom/google/android/recaptcha/internal/zzaho;->$r8$clinit:I

    .line 263
    check-cast v10, Lcom/google/android/recaptcha/internal/zzaec;

    .line 264
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v5

    .line 265
    invoke-virtual {v10, v11}, Lcom/google/android/recaptcha/internal/zzaec;->zzx(Lcom/google/android/recaptcha/internal/zzahn;)I

    move-result v10

    .line 266
    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v11

    goto/16 :goto_7

    .line 267
    :pswitch_3c
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_19

    shl-int/lit8 v0, v13, 0x3

    .line 268
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    instance-of v10, v5, Lcom/google/android/recaptcha/internal/zzaer;

    if-eqz v10, :cond_1a

    .line 269
    check-cast v5, Lcom/google/android/recaptcha/internal/zzaer;

    .line 270
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v0

    .line 271
    invoke-virtual {v5}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v5

    .line 272
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v10

    goto :goto_1d

    .line 273
    :cond_1a
    check-cast v5, Ljava/lang/String;

    .line 274
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v0

    .line 275
    sget v10, Lcom/google/android/recaptcha/internal/zzail;->$r8$clinit:I

    .line 276
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzaij;->zzb(Ljava/lang/String;)I

    move-result v5

    .line 277
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v10

    goto :goto_1d

    .line 278
    :pswitch_3d
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_19

    shl-int/lit8 v0, v13, 0x3

    .line 279
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v0

    add-int/lit8 v0, v0, 0x1

    goto/16 :goto_1a

    .line 280
    :pswitch_3e
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_19

    shl-int/lit8 v0, v13, 0x3

    .line 281
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v0

    goto/16 :goto_1c

    .line 282
    :pswitch_3f
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_19

    shl-int/lit8 v0, v13, 0x3

    .line 283
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v0

    goto/16 :goto_1b

    .line 284
    :pswitch_40
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_19

    shl-int/lit8 v0, v13, 0x3

    .line 285
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getInt(Ljava/lang/Object;J)I

    move-result v5

    int-to-long v10, v5

    .line 286
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v0

    .line 287
    invoke-static {v10, v11}, Lcom/google/android/recaptcha/internal/zzafa;->zzv(J)I

    move-result v5

    goto/16 :goto_19

    .line 288
    :pswitch_41
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_19

    shl-int/lit8 v0, v13, 0x3

    .line 289
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getLong(Ljava/lang/Object;J)J

    move-result-wide v10

    .line 290
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v0

    .line 291
    invoke-static {v10, v11}, Lcom/google/android/recaptcha/internal/zzafa;->zzv(J)I

    move-result v5

    goto/16 :goto_19

    .line 292
    :pswitch_42
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_19

    shl-int/lit8 v0, v13, 0x3

    .line 293
    invoke-virtual {v6, v1, v14, v15}, Lsun/misc/Unsafe;->getLong(Ljava/lang/Object;J)J

    move-result-wide v10

    .line 294
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v0

    .line 295
    invoke-static {v10, v11}, Lcom/google/android/recaptcha/internal/zzafa;->zzv(J)I

    move-result v5

    goto/16 :goto_19

    .line 296
    :pswitch_43
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_19

    shl-int/lit8 v0, v13, 0x3

    .line 297
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v0

    goto/16 :goto_1c

    .line 298
    :pswitch_44
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_1b

    shl-int/lit8 v1, v13, 0x3

    .line 299
    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzafa;->zzu(I)I

    move-result v1

    add-int/lit8 v1, v1, 0x8

    add-int/2addr v9, v1

    :cond_1b
    :goto_1e
    add-int/lit8 v2, v2, 0x3

    move-object/from16 v1, p1

    goto/16 :goto_0

    .line 300
    :cond_1c
    move-object/from16 v1, p1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzafv;

    iget-object v1, v1, Lcom/google/android/recaptcha/internal/zzafv;->zzc:Lcom/google/android/recaptcha/internal/zzaia;

    .line 302
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzaia;->zzi()I

    move-result v1

    add-int/2addr v9, v1

    iget-boolean v0, v0, Lcom/google/android/recaptcha/internal/zzaha;->zzh:Z

    if-nez v0, :cond_1d

    return v9

    .line 303
    :cond_1d
    invoke-static/range {p1 .. p1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    throw v11

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_44
        :pswitch_43
        :pswitch_42
        :pswitch_41
        :pswitch_40
        :pswitch_3f
        :pswitch_3e
        :pswitch_3d
        :pswitch_3c
        :pswitch_3b
        :pswitch_3a
        :pswitch_39
        :pswitch_38
        :pswitch_37
        :pswitch_36
        :pswitch_35
        :pswitch_34
        :pswitch_33
        :pswitch_32
        :pswitch_31
        :pswitch_30
        :pswitch_2f
        :pswitch_2e
        :pswitch_2d
        :pswitch_2c
        :pswitch_2b
        :pswitch_2a
        :pswitch_29
        :pswitch_28
        :pswitch_27
        :pswitch_26
        :pswitch_25
        :pswitch_24
        :pswitch_23
        :pswitch_22
        :pswitch_21
        :pswitch_20
        :pswitch_1f
        :pswitch_1e
        :pswitch_1d
        :pswitch_1c
        :pswitch_1b
        :pswitch_1a
        :pswitch_19
        :pswitch_18
        :pswitch_17
        :pswitch_16
        :pswitch_15
        :pswitch_14
        :pswitch_13
        :pswitch_12
        :pswitch_11
        :pswitch_10
        :pswitch_f
        :pswitch_e
        :pswitch_d
        :pswitch_c
        :pswitch_b
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public final zzf(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzafb;)V
    .locals 17

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v6, p2

    .line 1
    iget-boolean v2, v0, Lcom/google/android/recaptcha/internal/zzaha;->zzh:Z

    if-nez v2, :cond_7

    .line 4
    iget-object v8, v0, Lcom/google/android/recaptcha/internal/zzaha;->zzc:[I

    sget-object v9, Lcom/google/android/recaptcha/internal/zzaha;->zzb:Lsun/misc/Unsafe;

    const v10, 0xfffff

    move v3, v10

    const/4 v2, 0x0

    const/4 v4, 0x0

    :goto_0
    array-length v5, v8

    if-ge v2, v5, :cond_6

    .line 5
    invoke-direct {v0, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzA(I)I

    move-result v5

    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzC(I)I

    move-result v12

    .line 6
    aget v13, v8, v2

    const/16 v14, 0x11

    const/4 v15, 0x1

    if-gt v12, v14, :cond_2

    add-int/lit8 v14, v2, 0x2

    .line 7
    aget v14, v8, v14

    const/16 v16, 0x0

    and-int v7, v14, v10

    if-eq v7, v3, :cond_1

    if-ne v7, v10, :cond_0

    const/4 v4, 0x0

    goto :goto_1

    :cond_0
    int-to-long v3, v7

    .line 8
    invoke-virtual {v9, v1, v3, v4}, Lsun/misc/Unsafe;->getInt(Ljava/lang/Object;J)I

    move-result v3

    move v4, v3

    :goto_1
    move v3, v7

    :cond_1
    ushr-int/lit8 v7, v14, 0x14

    shl-int v7, v15, v7

    goto :goto_2

    :cond_2
    const/16 v16, 0x0

    const/4 v7, 0x0

    :goto_2
    and-int/2addr v5, v10

    int-to-long v10, v5

    packed-switch v12, :pswitch_data_0

    :cond_3
    :goto_3
    const/4 v12, 0x0

    goto/16 :goto_6

    .line 117
    :pswitch_0
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_3

    .line 118
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    invoke-direct {v0, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v7

    .line 119
    invoke-virtual {v6, v13, v5, v7}, Lcom/google/android/recaptcha/internal/zzafb;->zzs(ILjava/lang/Object;Lcom/google/android/recaptcha/internal/zzahn;)V

    goto :goto_3

    .line 120
    :pswitch_1
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_3

    .line 121
    invoke-static {v1, v10, v11}, Lcom/google/android/recaptcha/internal/zzaha;->zzH(Ljava/lang/Object;J)J

    move-result-wide v10

    invoke-virtual {v6, v13, v10, v11}, Lcom/google/android/recaptcha/internal/zzafb;->zzq(IJ)V

    goto :goto_3

    .line 122
    :pswitch_2
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_3

    .line 123
    invoke-static {v1, v10, v11}, Lcom/google/android/recaptcha/internal/zzaha;->zzG(Ljava/lang/Object;J)I

    move-result v5

    invoke-virtual {v6, v13, v5}, Lcom/google/android/recaptcha/internal/zzafb;->zzp(II)V

    goto :goto_3

    .line 124
    :pswitch_3
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_3

    .line 125
    invoke-static {v1, v10, v11}, Lcom/google/android/recaptcha/internal/zzaha;->zzH(Ljava/lang/Object;J)J

    move-result-wide v10

    invoke-virtual {v6, v13, v10, v11}, Lcom/google/android/recaptcha/internal/zzafb;->zzd(IJ)V

    goto :goto_3

    .line 126
    :pswitch_4
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_3

    .line 127
    invoke-static {v1, v10, v11}, Lcom/google/android/recaptcha/internal/zzaha;->zzG(Ljava/lang/Object;J)I

    move-result v5

    invoke-virtual {v6, v13, v5}, Lcom/google/android/recaptcha/internal/zzafb;->zzb(II)V

    goto :goto_3

    .line 128
    :pswitch_5
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_3

    .line 129
    invoke-static {v1, v10, v11}, Lcom/google/android/recaptcha/internal/zzaha;->zzG(Ljava/lang/Object;J)I

    move-result v5

    invoke-virtual {v6, v13, v5}, Lcom/google/android/recaptcha/internal/zzafb;->zzg(II)V

    goto :goto_3

    .line 130
    :pswitch_6
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_3

    .line 131
    invoke-static {v1, v10, v11}, Lcom/google/android/recaptcha/internal/zzaha;->zzG(Ljava/lang/Object;J)I

    move-result v5

    invoke-virtual {v6, v13, v5}, Lcom/google/android/recaptcha/internal/zzafb;->zzo(II)V

    goto :goto_3

    .line 132
    :pswitch_7
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_3

    .line 133
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lcom/google/android/recaptcha/internal/zzaer;

    invoke-virtual {v6, v13, v5}, Lcom/google/android/recaptcha/internal/zzafb;->zzn(ILcom/google/android/recaptcha/internal/zzaer;)V

    goto :goto_3

    .line 134
    :pswitch_8
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_3

    .line 135
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    .line 136
    invoke-direct {v0, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v7

    invoke-virtual {v6, v13, v5, v7}, Lcom/google/android/recaptcha/internal/zzafb;->zzr(ILjava/lang/Object;Lcom/google/android/recaptcha/internal/zzahn;)V

    goto/16 :goto_3

    .line 137
    :pswitch_9
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_3

    .line 138
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    invoke-static {v13, v5, v6}, Lcom/google/android/recaptcha/internal/zzaha;->zzS(ILjava/lang/Object;Lcom/google/android/recaptcha/internal/zzafb;)V

    goto/16 :goto_3

    .line 139
    :pswitch_a
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_3

    .line 140
    invoke-static {v1, v10, v11}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/lang/Boolean;

    invoke-virtual {v5}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v5

    .line 141
    invoke-virtual {v6, v13, v5}, Lcom/google/android/recaptcha/internal/zzafb;->zzl(IZ)V

    goto/16 :goto_3

    .line 142
    :pswitch_b
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_3

    .line 143
    invoke-static {v1, v10, v11}, Lcom/google/android/recaptcha/internal/zzaha;->zzG(Ljava/lang/Object;J)I

    move-result v5

    invoke-virtual {v6, v13, v5}, Lcom/google/android/recaptcha/internal/zzafb;->zzk(II)V

    goto/16 :goto_3

    .line 144
    :pswitch_c
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_3

    .line 145
    invoke-static {v1, v10, v11}, Lcom/google/android/recaptcha/internal/zzaha;->zzH(Ljava/lang/Object;J)J

    move-result-wide v10

    invoke-virtual {v6, v13, v10, v11}, Lcom/google/android/recaptcha/internal/zzafb;->zzj(IJ)V

    goto/16 :goto_3

    .line 146
    :pswitch_d
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_3

    .line 147
    invoke-static {v1, v10, v11}, Lcom/google/android/recaptcha/internal/zzaha;->zzG(Ljava/lang/Object;J)I

    move-result v5

    invoke-virtual {v6, v13, v5}, Lcom/google/android/recaptcha/internal/zzafb;->zzi(II)V

    goto/16 :goto_3

    .line 148
    :pswitch_e
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_3

    .line 149
    invoke-static {v1, v10, v11}, Lcom/google/android/recaptcha/internal/zzaha;->zzH(Ljava/lang/Object;J)J

    move-result-wide v10

    invoke-virtual {v6, v13, v10, v11}, Lcom/google/android/recaptcha/internal/zzafb;->zzh(IJ)V

    goto/16 :goto_3

    .line 150
    :pswitch_f
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_3

    .line 151
    invoke-static {v1, v10, v11}, Lcom/google/android/recaptcha/internal/zzaha;->zzH(Ljava/lang/Object;J)J

    move-result-wide v10

    invoke-virtual {v6, v13, v10, v11}, Lcom/google/android/recaptcha/internal/zzafb;->zzc(IJ)V

    goto/16 :goto_3

    .line 152
    :pswitch_10
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_3

    .line 153
    invoke-static {v1, v10, v11}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/lang/Float;

    invoke-virtual {v5}, Ljava/lang/Float;->floatValue()F

    move-result v5

    .line 154
    invoke-virtual {v6, v13, v5}, Lcom/google/android/recaptcha/internal/zzafb;->zze(IF)V

    goto/16 :goto_3

    .line 155
    :pswitch_11
    invoke-direct {v0, v1, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_3

    .line 156
    invoke-static {v1, v10, v11}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/lang/Double;

    invoke-virtual {v5}, Ljava/lang/Double;->doubleValue()D

    move-result-wide v10

    .line 157
    invoke-virtual {v6, v13, v10, v11}, Lcom/google/android/recaptcha/internal/zzafb;->zzf(ID)V

    goto/16 :goto_3

    .line 158
    :pswitch_12
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    if-nez v5, :cond_4

    goto/16 :goto_3

    .line 205
    :cond_4
    invoke-direct {v0, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzr(I)Ljava/lang/Object;

    move-result-object v0

    .line 206
    invoke-static {v0}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 207
    throw v16

    .line 110
    :pswitch_13
    aget v5, v8, v2

    .line 111
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 112
    invoke-direct {v0, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v10

    .line 113
    sget v11, Lcom/google/android/recaptcha/internal/zzaho;->$r8$clinit:I

    if-eqz v7, :cond_3

    .line 114
    invoke-interface {v7}, Ljava/util/List;->isEmpty()Z

    move-result v11

    if-nez v11, :cond_3

    const/4 v11, 0x0

    .line 115
    :goto_4
    invoke-interface {v7}, Ljava/util/List;->size()I

    move-result v12

    if-ge v11, v12, :cond_3

    .line 116
    invoke-interface {v7, v11}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v12

    invoke-virtual {v6, v5, v12, v10}, Lcom/google/android/recaptcha/internal/zzafb;->zzs(ILjava/lang/Object;Lcom/google/android/recaptcha/internal/zzahn;)V

    add-int/lit8 v11, v11, 0x1

    goto :goto_4

    .line 107
    :pswitch_14
    aget v5, v8, v2

    .line 108
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 109
    invoke-static {v5, v7, v6, v15}, Lcom/google/android/recaptcha/internal/zzaho;->zze(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_3

    .line 104
    :pswitch_15
    aget v5, v8, v2

    .line 105
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 106
    invoke-static {v5, v7, v6, v15}, Lcom/google/android/recaptcha/internal/zzaho;->zzj(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_3

    .line 101
    :pswitch_16
    aget v5, v8, v2

    .line 102
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 103
    invoke-static {v5, v7, v6, v15}, Lcom/google/android/recaptcha/internal/zzaho;->zzg(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_3

    .line 98
    :pswitch_17
    aget v5, v8, v2

    .line 99
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 100
    invoke-static {v5, v7, v6, v15}, Lcom/google/android/recaptcha/internal/zzaho;->zzl(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_3

    .line 95
    :pswitch_18
    aget v5, v8, v2

    .line 96
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 97
    invoke-static {v5, v7, v6, v15}, Lcom/google/android/recaptcha/internal/zzaho;->zzm(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_3

    .line 92
    :pswitch_19
    aget v5, v8, v2

    .line 93
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 94
    invoke-static {v5, v7, v6, v15}, Lcom/google/android/recaptcha/internal/zzaho;->zzi(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_3

    .line 89
    :pswitch_1a
    aget v5, v8, v2

    .line 90
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 91
    invoke-static {v5, v7, v6, v15}, Lcom/google/android/recaptcha/internal/zzaho;->zzn(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_3

    .line 86
    :pswitch_1b
    aget v5, v8, v2

    .line 87
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 88
    invoke-static {v5, v7, v6, v15}, Lcom/google/android/recaptcha/internal/zzaho;->zzk(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_3

    .line 83
    :pswitch_1c
    aget v5, v8, v2

    .line 84
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 85
    invoke-static {v5, v7, v6, v15}, Lcom/google/android/recaptcha/internal/zzaho;->zzf(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_3

    .line 80
    :pswitch_1d
    aget v5, v8, v2

    .line 81
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 82
    invoke-static {v5, v7, v6, v15}, Lcom/google/android/recaptcha/internal/zzaho;->zzh(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_3

    .line 77
    :pswitch_1e
    aget v5, v8, v2

    .line 78
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 79
    invoke-static {v5, v7, v6, v15}, Lcom/google/android/recaptcha/internal/zzaho;->zzd(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_3

    .line 74
    :pswitch_1f
    aget v5, v8, v2

    .line 75
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 76
    invoke-static {v5, v7, v6, v15}, Lcom/google/android/recaptcha/internal/zzaho;->zzc(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_3

    .line 71
    :pswitch_20
    aget v5, v8, v2

    .line 72
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 73
    invoke-static {v5, v7, v6, v15}, Lcom/google/android/recaptcha/internal/zzaho;->zzb(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_3

    .line 68
    :pswitch_21
    aget v5, v8, v2

    .line 69
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 70
    invoke-static {v5, v7, v6, v15}, Lcom/google/android/recaptcha/internal/zzaho;->zza(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_3

    .line 65
    :pswitch_22
    aget v5, v8, v2

    .line 66
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    const/4 v12, 0x0

    .line 67
    invoke-static {v5, v7, v6, v12}, Lcom/google/android/recaptcha/internal/zzaho;->zze(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_6

    :pswitch_23
    const/4 v12, 0x0

    .line 62
    aget v5, v8, v2

    .line 63
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 64
    invoke-static {v5, v7, v6, v12}, Lcom/google/android/recaptcha/internal/zzaho;->zzj(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_6

    :pswitch_24
    const/4 v12, 0x0

    .line 59
    aget v5, v8, v2

    .line 60
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 61
    invoke-static {v5, v7, v6, v12}, Lcom/google/android/recaptcha/internal/zzaho;->zzg(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_6

    :pswitch_25
    const/4 v12, 0x0

    .line 56
    aget v5, v8, v2

    .line 57
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 58
    invoke-static {v5, v7, v6, v12}, Lcom/google/android/recaptcha/internal/zzaho;->zzl(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_6

    :pswitch_26
    const/4 v12, 0x0

    .line 53
    aget v5, v8, v2

    .line 54
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 55
    invoke-static {v5, v7, v6, v12}, Lcom/google/android/recaptcha/internal/zzaho;->zzm(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_6

    :pswitch_27
    const/4 v12, 0x0

    .line 50
    aget v5, v8, v2

    .line 51
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 52
    invoke-static {v5, v7, v6, v12}, Lcom/google/android/recaptcha/internal/zzaho;->zzi(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_6

    .line 45
    :pswitch_28
    aget v5, v8, v2

    .line 46
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 47
    sget v10, Lcom/google/android/recaptcha/internal/zzaho;->$r8$clinit:I

    if-eqz v7, :cond_3

    .line 48
    invoke-interface {v7}, Ljava/util/List;->isEmpty()Z

    move-result v10

    if-nez v10, :cond_3

    .line 49
    invoke-virtual {v6, v5, v7}, Lcom/google/android/recaptcha/internal/zzafb;->zzG(ILjava/util/List;)V

    goto/16 :goto_3

    .line 38
    :pswitch_29
    aget v5, v8, v2

    .line 39
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 40
    invoke-direct {v0, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v10

    .line 41
    sget v11, Lcom/google/android/recaptcha/internal/zzaho;->$r8$clinit:I

    if-eqz v7, :cond_3

    .line 42
    invoke-interface {v7}, Ljava/util/List;->isEmpty()Z

    move-result v11

    if-nez v11, :cond_3

    const/4 v12, 0x0

    .line 43
    :goto_5
    invoke-interface {v7}, Ljava/util/List;->size()I

    move-result v11

    if-ge v12, v11, :cond_3

    .line 44
    invoke-interface {v7, v12}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v11

    invoke-virtual {v6, v5, v11, v10}, Lcom/google/android/recaptcha/internal/zzafb;->zzr(ILjava/lang/Object;Lcom/google/android/recaptcha/internal/zzahn;)V

    add-int/lit8 v12, v12, 0x1

    goto :goto_5

    .line 33
    :pswitch_2a
    aget v5, v8, v2

    .line 34
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 35
    sget v10, Lcom/google/android/recaptcha/internal/zzaho;->$r8$clinit:I

    if-eqz v7, :cond_3

    .line 36
    invoke-interface {v7}, Ljava/util/List;->isEmpty()Z

    move-result v10

    if-nez v10, :cond_3

    .line 37
    invoke-virtual {v6, v5, v7}, Lcom/google/android/recaptcha/internal/zzafb;->zzF(ILjava/util/List;)V

    goto/16 :goto_3

    .line 30
    :pswitch_2b
    aget v5, v8, v2

    .line 31
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    const/4 v12, 0x0

    .line 32
    invoke-static {v5, v7, v6, v12}, Lcom/google/android/recaptcha/internal/zzaho;->zzn(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_6

    :pswitch_2c
    const/4 v12, 0x0

    .line 27
    aget v5, v8, v2

    .line 28
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 29
    invoke-static {v5, v7, v6, v12}, Lcom/google/android/recaptcha/internal/zzaho;->zzk(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_6

    :pswitch_2d
    const/4 v12, 0x0

    .line 24
    aget v5, v8, v2

    .line 25
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 26
    invoke-static {v5, v7, v6, v12}, Lcom/google/android/recaptcha/internal/zzaho;->zzf(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_6

    :pswitch_2e
    const/4 v12, 0x0

    .line 21
    aget v5, v8, v2

    .line 22
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 23
    invoke-static {v5, v7, v6, v12}, Lcom/google/android/recaptcha/internal/zzaho;->zzh(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_6

    :pswitch_2f
    const/4 v12, 0x0

    .line 18
    aget v5, v8, v2

    .line 19
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 20
    invoke-static {v5, v7, v6, v12}, Lcom/google/android/recaptcha/internal/zzaho;->zzd(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_6

    :pswitch_30
    const/4 v12, 0x0

    .line 15
    aget v5, v8, v2

    .line 16
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 17
    invoke-static {v5, v7, v6, v12}, Lcom/google/android/recaptcha/internal/zzaho;->zzc(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_6

    :pswitch_31
    const/4 v12, 0x0

    .line 12
    aget v5, v8, v2

    .line 13
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 14
    invoke-static {v5, v7, v6, v12}, Lcom/google/android/recaptcha/internal/zzaho;->zzb(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_6

    :pswitch_32
    const/4 v12, 0x0

    .line 9
    aget v5, v8, v2

    .line 10
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/List;

    .line 11
    invoke-static {v5, v7, v6, v12}, Lcom/google/android/recaptcha/internal/zzaho;->zza(ILjava/util/List;Lcom/google/android/recaptcha/internal/zzafb;Z)V

    goto/16 :goto_6

    :pswitch_33
    move v5, v7

    const/4 v12, 0x0

    .line 159
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_5

    .line 160
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    invoke-direct {v0, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v7

    .line 161
    invoke-virtual {v6, v13, v5, v7}, Lcom/google/android/recaptcha/internal/zzafb;->zzs(ILjava/lang/Object;Lcom/google/android/recaptcha/internal/zzahn;)V

    goto/16 :goto_6

    :pswitch_34
    move v5, v7

    const/4 v12, 0x0

    .line 162
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_5

    .line 163
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getLong(Ljava/lang/Object;J)J

    move-result-wide v10

    invoke-virtual {v6, v13, v10, v11}, Lcom/google/android/recaptcha/internal/zzafb;->zzq(IJ)V

    goto/16 :goto_6

    :pswitch_35
    move v5, v7

    const/4 v12, 0x0

    .line 164
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_5

    .line 165
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getInt(Ljava/lang/Object;J)I

    move-result v0

    invoke-virtual {v6, v13, v0}, Lcom/google/android/recaptcha/internal/zzafb;->zzp(II)V

    goto/16 :goto_6

    :pswitch_36
    move v5, v7

    const/4 v12, 0x0

    .line 166
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_5

    .line 167
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getLong(Ljava/lang/Object;J)J

    move-result-wide v10

    invoke-virtual {v6, v13, v10, v11}, Lcom/google/android/recaptcha/internal/zzafb;->zzd(IJ)V

    goto/16 :goto_6

    :pswitch_37
    move v5, v7

    const/4 v12, 0x0

    .line 168
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_5

    .line 169
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getInt(Ljava/lang/Object;J)I

    move-result v0

    invoke-virtual {v6, v13, v0}, Lcom/google/android/recaptcha/internal/zzafb;->zzb(II)V

    goto/16 :goto_6

    :pswitch_38
    move v5, v7

    const/4 v12, 0x0

    .line 170
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_5

    .line 171
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getInt(Ljava/lang/Object;J)I

    move-result v0

    invoke-virtual {v6, v13, v0}, Lcom/google/android/recaptcha/internal/zzafb;->zzg(II)V

    goto/16 :goto_6

    :pswitch_39
    move v5, v7

    const/4 v12, 0x0

    .line 172
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_5

    .line 173
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getInt(Ljava/lang/Object;J)I

    move-result v0

    invoke-virtual {v6, v13, v0}, Lcom/google/android/recaptcha/internal/zzafb;->zzo(II)V

    goto/16 :goto_6

    :pswitch_3a
    move v5, v7

    const/4 v12, 0x0

    .line 174
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_5

    .line 175
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzaer;

    invoke-virtual {v6, v13, v0}, Lcom/google/android/recaptcha/internal/zzafb;->zzn(ILcom/google/android/recaptcha/internal/zzaer;)V

    goto/16 :goto_6

    :pswitch_3b
    move v5, v7

    const/4 v12, 0x0

    .line 176
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_5

    .line 177
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    .line 178
    invoke-direct {v0, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v7

    invoke-virtual {v6, v13, v5, v7}, Lcom/google/android/recaptcha/internal/zzafb;->zzr(ILjava/lang/Object;Lcom/google/android/recaptcha/internal/zzahn;)V

    goto/16 :goto_6

    :pswitch_3c
    move v5, v7

    const/4 v12, 0x0

    .line 179
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_5

    .line 180
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v0

    invoke-static {v13, v0, v6}, Lcom/google/android/recaptcha/internal/zzaha;->zzS(ILjava/lang/Object;Lcom/google/android/recaptcha/internal/zzafb;)V

    goto/16 :goto_6

    :pswitch_3d
    move v5, v7

    const/4 v12, 0x0

    .line 181
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_5

    .line 182
    invoke-static {v1, v10, v11}, Lcom/google/android/recaptcha/internal/zzaig;->zzf(Ljava/lang/Object;J)Z

    move-result v0

    .line 183
    invoke-virtual {v6, v13, v0}, Lcom/google/android/recaptcha/internal/zzafb;->zzl(IZ)V

    goto/16 :goto_6

    :pswitch_3e
    move v5, v7

    const/4 v12, 0x0

    .line 184
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_5

    .line 185
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getInt(Ljava/lang/Object;J)I

    move-result v0

    invoke-virtual {v6, v13, v0}, Lcom/google/android/recaptcha/internal/zzafb;->zzk(II)V

    goto :goto_6

    :pswitch_3f
    move v5, v7

    const/4 v12, 0x0

    .line 186
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_5

    .line 187
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getLong(Ljava/lang/Object;J)J

    move-result-wide v10

    invoke-virtual {v6, v13, v10, v11}, Lcom/google/android/recaptcha/internal/zzafb;->zzj(IJ)V

    goto :goto_6

    :pswitch_40
    move v5, v7

    const/4 v12, 0x0

    .line 188
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_5

    .line 189
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getInt(Ljava/lang/Object;J)I

    move-result v0

    invoke-virtual {v6, v13, v0}, Lcom/google/android/recaptcha/internal/zzafb;->zzi(II)V

    goto :goto_6

    :pswitch_41
    move v5, v7

    const/4 v12, 0x0

    .line 190
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_5

    .line 191
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getLong(Ljava/lang/Object;J)J

    move-result-wide v10

    invoke-virtual {v6, v13, v10, v11}, Lcom/google/android/recaptcha/internal/zzafb;->zzh(IJ)V

    goto :goto_6

    :pswitch_42
    move v5, v7

    const/4 v12, 0x0

    .line 192
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_5

    .line 193
    invoke-virtual {v9, v1, v10, v11}, Lsun/misc/Unsafe;->getLong(Ljava/lang/Object;J)J

    move-result-wide v10

    invoke-virtual {v6, v13, v10, v11}, Lcom/google/android/recaptcha/internal/zzafb;->zzc(IJ)V

    goto :goto_6

    :pswitch_43
    move v5, v7

    const/4 v12, 0x0

    .line 194
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_5

    .line 195
    invoke-static {v1, v10, v11}, Lcom/google/android/recaptcha/internal/zzaig;->zzh(Ljava/lang/Object;J)F

    move-result v0

    .line 196
    invoke-virtual {v6, v13, v0}, Lcom/google/android/recaptcha/internal/zzafb;->zze(IF)V

    goto :goto_6

    :pswitch_44
    move v5, v7

    const/4 v12, 0x0

    .line 197
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_5

    .line 198
    invoke-static {v1, v10, v11}, Lcom/google/android/recaptcha/internal/zzaig;->zzj(Ljava/lang/Object;J)D

    move-result-wide v10

    .line 199
    invoke-virtual {v6, v13, v10, v11}, Lcom/google/android/recaptcha/internal/zzafb;->zzf(ID)V

    :cond_5
    :goto_6
    add-int/lit8 v2, v2, 0x3

    const v10, 0xfffff

    move-object/from16 v0, p0

    goto/16 :goto_0

    .line 200
    :cond_6
    move-object v0, v1

    check-cast v0, Lcom/google/android/recaptcha/internal/zzafv;

    iget-object v0, v0, Lcom/google/android/recaptcha/internal/zzafv;->zzc:Lcom/google/android/recaptcha/internal/zzaia;

    .line 202
    invoke-virtual {v0, v6}, Lcom/google/android/recaptcha/internal/zzaia;->zzg(Lcom/google/android/recaptcha/internal/zzafb;)V

    return-void

    :cond_7
    const/16 v16, 0x0

    .line 1
    invoke-static {v1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    throw v16

    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_44
        :pswitch_43
        :pswitch_42
        :pswitch_41
        :pswitch_40
        :pswitch_3f
        :pswitch_3e
        :pswitch_3d
        :pswitch_3c
        :pswitch_3b
        :pswitch_3a
        :pswitch_39
        :pswitch_38
        :pswitch_37
        :pswitch_36
        :pswitch_35
        :pswitch_34
        :pswitch_33
        :pswitch_32
        :pswitch_31
        :pswitch_30
        :pswitch_2f
        :pswitch_2e
        :pswitch_2d
        :pswitch_2c
        :pswitch_2b
        :pswitch_2a
        :pswitch_29
        :pswitch_28
        :pswitch_27
        :pswitch_26
        :pswitch_25
        :pswitch_24
        :pswitch_23
        :pswitch_22
        :pswitch_21
        :pswitch_20
        :pswitch_1f
        :pswitch_1e
        :pswitch_1d
        :pswitch_1c
        :pswitch_1b
        :pswitch_1a
        :pswitch_19
        :pswitch_18
        :pswitch_17
        :pswitch_16
        :pswitch_15
        :pswitch_14
        :pswitch_13
        :pswitch_12
        :pswitch_11
        :pswitch_10
        :pswitch_f
        :pswitch_e
        :pswitch_d
        :pswitch_c
        :pswitch_b
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public final zzg(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzaew;Lcom/google/android/recaptcha/internal/zzafh;)V
    .locals 12

    .line 1
    invoke-virtual {p3}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 2
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzaha;->zzF(Ljava/lang/Object;)V

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzm:Lcom/google/android/recaptcha/internal/zzahz;

    const/4 v0, 0x0

    move-object v4, v0

    .line 3
    :goto_0
    :try_start_0
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzb()I

    move-result v2

    .line 4
    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzP(I)I

    move-result v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_b

    const/4 v7, 0x0

    if-gez v1, :cond_7

    const v1, 0x7fffffff

    if-ne v2, v1, :cond_1

    iget p2, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzk:I

    :goto_1
    iget p3, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzl:I

    if-ge p2, p3, :cond_0

    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzj:[I

    .line 196
    aget v3, p3, p2

    move-object v6, p1

    move-object v1, p0

    move-object v2, p1

    .line 197
    invoke-direct/range {v1 .. v6}, Lcom/google/android/recaptcha/internal/zzaha;->zzx(Ljava/lang/Object;ILjava/lang/Object;Lcom/google/android/recaptcha/internal/zzahz;Ljava/lang/Object;)Ljava/lang/Object;

    move-object p0, v2

    move-object v6, v5

    move-object v5, v4

    add-int/lit8 p2, p2, 0x1

    move-object p1, p0

    move-object p0, v1

    move-object v5, v6

    goto :goto_1

    :cond_0
    move-object v5, v4

    move-object v2, p1

    goto/16 :goto_18

    :cond_1
    move-object v1, p0

    move-object p0, p1

    move-object v6, v5

    move-object v5, v4

    .line 201
    :try_start_1
    iget-boolean p1, v1, Lcom/google/android/recaptcha/internal/zzaha;->zzh:Z

    if-nez p1, :cond_2

    goto :goto_2

    .line 197
    :cond_2
    iget-object p1, v1, Lcom/google/android/recaptcha/internal/zzaha;->zzg:Lcom/google/android/recaptcha/internal/zzagx;

    .line 5
    invoke-virtual {p3, p1, v2}, Lcom/google/android/recaptcha/internal/zzafh;->zzc(Lcom/google/android/recaptcha/internal/zzagx;I)Lcom/google/android/recaptcha/internal/zzafu;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_2

    :goto_2
    if-nez v5, :cond_3

    .line 6
    :try_start_2
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaib;->zzj(Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zzaia;

    move-result-object v4
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    goto :goto_4

    :catchall_0
    move-exception v0

    move-object p1, v0

    move-object v2, p0

    :goto_3
    move-object v4, v5

    goto :goto_8

    :cond_3
    move-object v4, v5

    .line 7
    :goto_4
    :try_start_3
    invoke-virtual {v6, v4, p2, v7}, Lcom/google/android/recaptcha/internal/zzahz;->zzh(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzaew;I)Z

    move-result p1
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    if-nez p1, :cond_5

    iget p1, v1, Lcom/google/android/recaptcha/internal/zzaha;->zzk:I

    :goto_5
    iget p2, v1, Lcom/google/android/recaptcha/internal/zzaha;->zzl:I

    if-ge p1, p2, :cond_4

    iget-object p2, v1, Lcom/google/android/recaptcha/internal/zzaha;->zzj:[I

    .line 196
    aget v3, p2, p1

    move-object v5, v6

    move-object v6, p0

    move-object v2, p0

    .line 197
    invoke-direct/range {v1 .. v6}, Lcom/google/android/recaptcha/internal/zzaha;->zzx(Ljava/lang/Object;ILjava/lang/Object;Lcom/google/android/recaptcha/internal/zzahz;Ljava/lang/Object;)Ljava/lang/Object;

    move-object p0, v1

    move-object v3, v2

    move-object v6, v5

    add-int/lit8 p1, p1, 0x1

    move-object p0, v3

    goto :goto_5

    :cond_4
    move-object v2, p0

    goto/16 :goto_18

    :cond_5
    move-object v3, p0

    move-object p0, v1

    :cond_6
    :goto_6
    move-object p1, v3

    move-object v5, v6

    goto :goto_0

    :catchall_1
    move-exception v0

    move-object v3, p0

    move-object p0, v1

    move-object p1, v0

    :goto_7
    move-object v2, v3

    :goto_8
    move-object v5, v6

    goto/16 :goto_1a

    :catchall_2
    move-exception v0

    move-object v3, p0

    move-object p0, v1

    move-object p1, v0

    :goto_9
    move-object v2, v3

    goto :goto_3

    :cond_7
    move-object v3, p1

    move-object v6, v5

    move-object v5, v4

    .line 8
    :try_start_4
    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzA(I)I

    move-result p1
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_a

    :try_start_5
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzaha;->zzC(I)I

    move-result v4
    :try_end_5
    .catch Lcom/google/android/recaptcha/internal/zzagj; {:try_start_5 .. :try_end_5} :catch_0
    .catchall {:try_start_5 .. :try_end_5} :catchall_6

    const v8, 0xfffff

    packed-switch v4, :pswitch_data_0

    if-nez v5, :cond_8

    .line 192
    :try_start_6
    invoke-static {v3}, Lcom/google/android/recaptcha/internal/zzaib;->zzj(Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zzaia;

    move-result-object v4
    :try_end_6
    .catch Lcom/google/android/recaptcha/internal/zzagj; {:try_start_6 .. :try_end_6} :catch_0
    .catchall {:try_start_6 .. :try_end_6} :catchall_3

    goto :goto_b

    :catchall_3
    move-exception v0

    move-object p1, v0

    move-object v1, p0

    goto :goto_9

    :catch_0
    move-object v2, v3

    :goto_a
    move-object v4, v5

    goto :goto_d

    :cond_8
    move-object v4, v5

    .line 193
    :goto_b
    :try_start_7
    invoke-virtual {v6, v4, p2, v7}, Lcom/google/android/recaptcha/internal/zzahz;->zzh(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzaew;I)Z

    move-result p1
    :try_end_7
    .catch Lcom/google/android/recaptcha/internal/zzagj; {:try_start_7 .. :try_end_7} :catch_1
    .catchall {:try_start_7 .. :try_end_7} :catchall_4

    if-nez p1, :cond_6

    iget p1, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzk:I

    :goto_c
    iget p2, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzl:I

    if-ge p1, p2, :cond_9

    iget-object p2, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzj:[I

    .line 196
    aget p2, p2, p1

    move-object v5, v6

    move-object v6, v3

    move-object v1, p0

    move-object v2, v3

    move v3, p2

    .line 197
    invoke-direct/range {v1 .. v6}, Lcom/google/android/recaptcha/internal/zzaha;->zzx(Ljava/lang/Object;ILjava/lang/Object;Lcom/google/android/recaptcha/internal/zzahz;Ljava/lang/Object;)Ljava/lang/Object;

    move-object v3, v2

    move-object v6, v5

    add-int/lit8 p1, p1, 0x1

    goto :goto_c

    :cond_9
    move-object v2, v3

    goto/16 :goto_18

    :catchall_4
    move-exception v0

    move-object p1, v0

    move-object v1, p0

    goto :goto_7

    :catch_1
    move-object v2, v3

    :goto_d
    move-object v5, v6

    goto/16 :goto_14

    .line 147
    :pswitch_0
    :try_start_8
    invoke-direct {p0, v3, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzv(Ljava/lang/Object;II)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/google/android/recaptcha/internal/zzagx;

    .line 148
    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v4

    .line 149
    invoke-virtual {p2, p1, v4, p3}, Lcom/google/android/recaptcha/internal/zzaew;->zzo(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzahn;Lcom/google/android/recaptcha/internal/zzafh;)V

    .line 150
    invoke-direct {p0, v3, v2, v1, p1}, Lcom/google/android/recaptcha/internal/zzaha;->zzw(Ljava/lang/Object;IILjava/lang/Object;)V

    :goto_e
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    goto/16 :goto_11

    :pswitch_1
    and-int/2addr p1, v8

    .line 144
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzv()J

    move-result-wide v8

    invoke-static {v8, v9}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    int-to-long v8, p1

    .line 145
    invoke-static {v3, v8, v9, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 146
    invoke-direct {p0, v3, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzO(Ljava/lang/Object;II)V

    goto :goto_e

    :pswitch_2
    and-int/2addr p1, v8

    .line 141
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzu()I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    int-to-long v8, p1

    .line 142
    invoke-static {v3, v8, v9, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 143
    invoke-direct {p0, v3, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzO(Ljava/lang/Object;II)V

    goto :goto_e

    :pswitch_3
    and-int/2addr p1, v8

    .line 138
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzt()J

    move-result-wide v8

    invoke-static {v8, v9}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    int-to-long v8, p1

    .line 139
    invoke-static {v3, v8, v9, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 140
    invoke-direct {p0, v3, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzO(Ljava/lang/Object;II)V

    goto :goto_e

    :pswitch_4
    and-int/2addr p1, v8

    .line 135
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzs()I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    int-to-long v8, p1

    .line 136
    invoke-static {v3, v8, v9, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 137
    invoke-direct {p0, v3, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzO(Ljava/lang/Object;II)V

    goto :goto_e

    .line 151
    :pswitch_5
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzr()I

    move-result v4

    .line 152
    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzs(I)Lcom/google/android/recaptcha/internal/zzaga;

    move-result-object v9

    if-eqz v9, :cond_b

    invoke-interface {v9, v4}, Lcom/google/android/recaptcha/internal/zzaga;->zza(I)Z

    move-result v9

    if-eqz v9, :cond_a

    goto :goto_f

    .line 155
    :cond_a
    invoke-static {v3, v2, v4, v5, v6}, Lcom/google/android/recaptcha/internal/zzaho;->zzF(Ljava/lang/Object;IILjava/lang/Object;Lcom/google/android/recaptcha/internal/zzahz;)Ljava/lang/Object;

    move-result-object v4

    goto/16 :goto_6

    :cond_b
    :goto_f
    and-int/2addr p1, v8

    .line 153
    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    int-to-long v8, p1

    invoke-static {v3, v8, v9, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 154
    invoke-direct {p0, v3, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzO(Ljava/lang/Object;II)V

    goto :goto_e

    :pswitch_6
    and-int/2addr p1, v8

    .line 132
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzq()I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    int-to-long v8, p1

    .line 133
    invoke-static {v3, v8, v9, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 134
    invoke-direct {p0, v3, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzO(Ljava/lang/Object;II)V

    goto :goto_e

    :pswitch_7
    and-int/2addr p1, v8

    .line 130
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzp()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v4

    int-to-long v8, p1

    invoke-static {v3, v8, v9, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 131
    invoke-direct {p0, v3, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzO(Ljava/lang/Object;II)V

    goto/16 :goto_e

    .line 156
    :pswitch_8
    invoke-direct {p0, v3, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzv(Ljava/lang/Object;II)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/google/android/recaptcha/internal/zzagx;

    .line 157
    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v4

    .line 158
    invoke-virtual {p2, p1, v4, p3}, Lcom/google/android/recaptcha/internal/zzaew;->zzn(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzahn;Lcom/google/android/recaptcha/internal/zzafh;)V

    .line 159
    invoke-direct {p0, v3, v2, v1, p1}, Lcom/google/android/recaptcha/internal/zzaha;->zzw(Ljava/lang/Object;IILjava/lang/Object;)V

    goto/16 :goto_e

    .line 160
    :pswitch_9
    invoke-direct {p0, v3, p1, p2}, Lcom/google/android/recaptcha/internal/zzaha;->zzz(Ljava/lang/Object;ILcom/google/android/recaptcha/internal/zzaew;)V

    .line 161
    invoke-direct {p0, v3, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzO(Ljava/lang/Object;II)V

    goto/16 :goto_e

    :pswitch_a
    and-int/2addr p1, v8

    .line 127
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzk()Z

    move-result v4

    invoke-static {v4}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v4

    int-to-long v8, p1

    .line 128
    invoke-static {v3, v8, v9, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 129
    invoke-direct {p0, v3, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzO(Ljava/lang/Object;II)V

    goto/16 :goto_e

    :pswitch_b
    and-int/2addr p1, v8

    .line 124
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzj()I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    int-to-long v8, p1

    .line 125
    invoke-static {v3, v8, v9, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 126
    invoke-direct {p0, v3, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzO(Ljava/lang/Object;II)V

    goto/16 :goto_e

    :pswitch_c
    and-int/2addr p1, v8

    .line 121
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzi()J

    move-result-wide v8

    invoke-static {v8, v9}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    int-to-long v8, p1

    .line 122
    invoke-static {v3, v8, v9, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 123
    invoke-direct {p0, v3, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzO(Ljava/lang/Object;II)V

    goto/16 :goto_e

    :pswitch_d
    and-int/2addr p1, v8

    .line 118
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzh()I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    int-to-long v8, p1

    .line 119
    invoke-static {v3, v8, v9, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 120
    invoke-direct {p0, v3, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzO(Ljava/lang/Object;II)V

    goto/16 :goto_e

    :pswitch_e
    and-int/2addr p1, v8

    .line 115
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzf()J

    move-result-wide v8

    invoke-static {v8, v9}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    int-to-long v8, p1

    .line 116
    invoke-static {v3, v8, v9, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 117
    invoke-direct {p0, v3, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzO(Ljava/lang/Object;II)V

    goto/16 :goto_e

    :pswitch_f
    and-int/2addr p1, v8

    .line 112
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzg()J

    move-result-wide v8

    invoke-static {v8, v9}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    int-to-long v8, p1

    .line 113
    invoke-static {v3, v8, v9, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 114
    invoke-direct {p0, v3, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzO(Ljava/lang/Object;II)V

    goto/16 :goto_e

    :pswitch_10
    and-int/2addr p1, v8

    .line 109
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zze()F

    move-result v4

    invoke-static {v4}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v4

    int-to-long v8, p1

    .line 110
    invoke-static {v3, v8, v9, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 111
    invoke-direct {p0, v3, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzO(Ljava/lang/Object;II)V

    goto/16 :goto_e

    :pswitch_11
    and-int/2addr p1, v8

    .line 106
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzd()D

    move-result-wide v8

    invoke-static {v8, v9}, Ljava/lang/Double;->valueOf(D)Ljava/lang/Double;

    move-result-object v4

    int-to-long v8, p1

    .line 107
    invoke-static {v3, v8, v9, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 108
    invoke-direct {p0, v3, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzO(Ljava/lang/Object;II)V

    goto/16 :goto_e

    .line 162
    :pswitch_12
    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzr(I)Ljava/lang/Object;

    move-result-object p1

    .line 163
    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzA(I)I

    move-result v1

    and-int/2addr v1, v8

    int-to-long v1, v1

    .line 164
    invoke-static {v3, v1, v2}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v4

    if-eqz v4, :cond_c

    .line 165
    invoke-static {v4}, Lcom/google/android/recaptcha/internal/zzagt;->zza(Ljava/lang/Object;)Z

    move-result v8

    if-eqz v8, :cond_d

    .line 166
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzags;->zza()Lcom/google/android/recaptcha/internal/zzags;

    move-result-object v8

    invoke-virtual {v8}, Lcom/google/android/recaptcha/internal/zzags;->zzc()Lcom/google/android/recaptcha/internal/zzags;

    move-result-object v8

    .line 167
    invoke-static {v8, v4}, Lcom/google/android/recaptcha/internal/zzagt;->zzb(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 168
    invoke-static {v3, v1, v2, v8}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    move-object v4, v8

    goto :goto_10

    .line 169
    :cond_c
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzags;->zza()Lcom/google/android/recaptcha/internal/zzags;

    move-result-object v4

    invoke-virtual {v4}, Lcom/google/android/recaptcha/internal/zzags;->zzc()Lcom/google/android/recaptcha/internal/zzags;

    move-result-object v4

    .line 170
    invoke-static {v3, v1, v2, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 171
    :cond_d
    :goto_10
    check-cast v4, Lcom/google/android/recaptcha/internal/zzags;

    .line 172
    invoke-static {p1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 173
    throw v0

    :pswitch_13
    and-int/2addr p1, v8

    .line 103
    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v1

    int-to-long v8, p1

    .line 104
    invoke-static {v3, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 105
    invoke-virtual {p2, p1, v1, p3}, Lcom/google/android/recaptcha/internal/zzaew;->zzG(Ljava/util/List;Lcom/google/android/recaptcha/internal/zzahn;Lcom/google/android/recaptcha/internal/zzafh;)V

    goto/16 :goto_e

    :pswitch_14
    and-int/2addr p1, v8

    int-to-long v1, p1

    .line 69
    invoke-static {v3, v1, v2}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 70
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzN(Ljava/util/List;)V

    goto/16 :goto_e

    :pswitch_15
    and-int/2addr p1, v8

    int-to-long v1, p1

    .line 67
    invoke-static {v3, v1, v2}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 68
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzM(Ljava/util/List;)V

    goto/16 :goto_e

    :pswitch_16
    and-int/2addr p1, v8

    int-to-long v1, p1

    .line 65
    invoke-static {v3, v1, v2}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 66
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzL(Ljava/util/List;)V

    goto/16 :goto_e

    :pswitch_17
    and-int/2addr p1, v8

    int-to-long v1, p1

    .line 63
    invoke-static {v3, v1, v2}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 64
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzK(Ljava/util/List;)V
    :try_end_8
    .catch Lcom/google/android/recaptcha/internal/zzagj; {:try_start_8 .. :try_end_8} :catch_0
    .catchall {:try_start_8 .. :try_end_8} :catchall_3

    goto/16 :goto_e

    :pswitch_18
    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 59
    :try_start_9
    invoke-static {v3, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 60
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzJ(Ljava/util/List;)V

    .line 61
    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzs(I)Lcom/google/android/recaptcha/internal/zzaga;

    move-result-object v4
    :try_end_9
    .catch Lcom/google/android/recaptcha/internal/zzagj; {:try_start_9 .. :try_end_9} :catch_0
    .catchall {:try_start_9 .. :try_end_9} :catchall_6

    move-object v1, v3

    move-object v3, p1

    .line 62
    :try_start_a
    invoke-static/range {v1 .. v6}, Lcom/google/android/recaptcha/internal/zzaho;->zzE(Ljava/lang/Object;ILjava/util/List;Lcom/google/android/recaptcha/internal/zzaga;Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzahz;)Ljava/lang/Object;

    move-result-object v4
    :try_end_a
    .catch Lcom/google/android/recaptcha/internal/zzagj; {:try_start_a .. :try_end_a} :catch_2
    .catchall {:try_start_a .. :try_end_a} :catchall_5

    move-object v2, v1

    move-object v5, v6

    :cond_e
    :goto_11
    move-object p1, v2

    goto/16 :goto_0

    :catchall_5
    move-exception v0

    move-object v2, v1

    :goto_12
    move-object v4, v5

    move-object v5, v6

    goto/16 :goto_15

    :catch_2
    move-object v2, v1

    goto/16 :goto_a

    :catchall_6
    move-exception v0

    move-object v2, v3

    goto :goto_12

    :pswitch_19
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 57
    :try_start_b
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 58
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzI(Ljava/util/List;)V

    goto :goto_11

    :pswitch_1a
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 55
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 56
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzD(Ljava/util/List;)V

    goto :goto_11

    :pswitch_1b
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 53
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 54
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzC(Ljava/util/List;)V

    goto :goto_11

    :pswitch_1c
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 51
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 52
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzB(Ljava/util/List;)V

    goto :goto_11

    :pswitch_1d
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 49
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 50
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzA(Ljava/util/List;)V

    goto :goto_11

    :pswitch_1e
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 47
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 48
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzy(Ljava/util/List;)V

    goto :goto_11

    :pswitch_1f
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 45
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 46
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzz(Ljava/util/List;)V

    goto :goto_11

    :pswitch_20
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 43
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 44
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzx(Ljava/util/List;)V

    goto :goto_11

    :pswitch_21
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 41
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 42
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzw(Ljava/util/List;)V

    goto/16 :goto_11

    :pswitch_22
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 39
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 40
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzN(Ljava/util/List;)V

    goto/16 :goto_11

    :pswitch_23
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 37
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 38
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzM(Ljava/util/List;)V

    goto/16 :goto_11

    :pswitch_24
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 35
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 36
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzL(Ljava/util/List;)V

    goto/16 :goto_11

    :pswitch_25
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 33
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 34
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzK(Ljava/util/List;)V
    :try_end_b
    .catch Lcom/google/android/recaptcha/internal/zzagj; {:try_start_b .. :try_end_b} :catch_4
    .catchall {:try_start_b .. :try_end_b} :catchall_8

    goto/16 :goto_11

    :pswitch_26
    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 29
    :try_start_c
    invoke-static {v3, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 30
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzJ(Ljava/util/List;)V
    :try_end_c
    .catch Lcom/google/android/recaptcha/internal/zzagj; {:try_start_c .. :try_end_c} :catch_3
    .catchall {:try_start_c .. :try_end_c} :catchall_7

    move-object v6, v5

    move-object v5, v4

    .line 31
    :try_start_d
    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzs(I)Lcom/google/android/recaptcha/internal/zzaga;

    move-result-object v4
    :try_end_d
    .catch Lcom/google/android/recaptcha/internal/zzagj; {:try_start_d .. :try_end_d} :catch_0
    .catchall {:try_start_d .. :try_end_d} :catchall_6

    move-object v1, v3

    move-object v3, p1

    .line 32
    :try_start_e
    invoke-static/range {v1 .. v6}, Lcom/google/android/recaptcha/internal/zzaho;->zzE(Ljava/lang/Object;ILjava/util/List;Lcom/google/android/recaptcha/internal/zzaga;Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzahz;)Ljava/lang/Object;

    move-result-object v4
    :try_end_e
    .catch Lcom/google/android/recaptcha/internal/zzagj; {:try_start_e .. :try_end_e} :catch_2
    .catchall {:try_start_e .. :try_end_e} :catchall_5

    move-object v2, v1

    move-object v5, v6

    goto/16 :goto_11

    :catchall_7
    move-exception v0

    move-object v2, v3

    goto/16 :goto_15

    :catch_3
    move-object v2, v3

    goto/16 :goto_14

    :pswitch_27
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 27
    :try_start_f
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 28
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzI(Ljava/util/List;)V

    goto/16 :goto_11

    :pswitch_28
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 25
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 26
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzH(Ljava/util/List;)V

    goto/16 :goto_11

    :pswitch_29
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    .line 174
    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v1

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 175
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 176
    invoke-virtual {p2, p1, v1, p3}, Lcom/google/android/recaptcha/internal/zzaew;->zzF(Ljava/util/List;Lcom/google/android/recaptcha/internal/zzahn;Lcom/google/android/recaptcha/internal/zzafh;)V

    goto/16 :goto_11

    :pswitch_2a
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    .line 98
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzaha;->zzD(I)Z

    move-result v1

    if-eqz v1, :cond_f

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 101
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    const/4 v1, 0x1

    .line 102
    invoke-virtual {p2, p1, v1}, Lcom/google/android/recaptcha/internal/zzaew;->zzE(Ljava/util/List;Z)V

    goto/16 :goto_11

    :cond_f
    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 99
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 100
    invoke-virtual {p2, p1, v7}, Lcom/google/android/recaptcha/internal/zzaew;->zzE(Ljava/util/List;Z)V

    goto/16 :goto_11

    :pswitch_2b
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 23
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 24
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzD(Ljava/util/List;)V

    goto/16 :goto_11

    :pswitch_2c
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 21
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 22
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzC(Ljava/util/List;)V

    goto/16 :goto_11

    :pswitch_2d
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 19
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 20
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzB(Ljava/util/List;)V

    goto/16 :goto_11

    :pswitch_2e
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 17
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 18
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzA(Ljava/util/List;)V

    goto/16 :goto_11

    :pswitch_2f
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 15
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 16
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzy(Ljava/util/List;)V

    goto/16 :goto_11

    :pswitch_30
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 13
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 14
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzz(Ljava/util/List;)V

    goto/16 :goto_11

    :pswitch_31
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 11
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 12
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzx(Ljava/util/List;)V

    goto/16 :goto_11

    :pswitch_32
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 9
    invoke-static {v2, v8, v9}, Lcom/google/android/recaptcha/internal/zzago;->zza(Ljava/lang/Object;J)Ljava/util/List;

    move-result-object p1

    .line 10
    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzaew;->zzw(Ljava/util/List;)V

    goto/16 :goto_11

    :pswitch_33
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    .line 177
    invoke-direct {p0, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzt(Ljava/lang/Object;I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/google/android/recaptcha/internal/zzagx;

    .line 178
    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v3

    .line 179
    invoke-virtual {p2, p1, v3, p3}, Lcom/google/android/recaptcha/internal/zzaew;->zzo(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzahn;Lcom/google/android/recaptcha/internal/zzafh;)V

    .line 180
    invoke-direct {p0, v2, v1, p1}, Lcom/google/android/recaptcha/internal/zzaha;->zzu(Ljava/lang/Object;ILjava/lang/Object;)V

    goto/16 :goto_11

    :pswitch_34
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    .line 97
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzv()J

    move-result-wide v8

    int-to-long v10, p1

    invoke-static {v2, v10, v11, v8, v9}, Lcom/google/android/recaptcha/internal/zzaig;->zze(Ljava/lang/Object;JJ)V

    .line 98
    invoke-direct {p0, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto/16 :goto_11

    :pswitch_35
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    .line 95
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzu()I

    move-result v3

    int-to-long v8, p1

    invoke-static {v2, v8, v9, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzc(Ljava/lang/Object;JI)V

    .line 96
    invoke-direct {p0, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto/16 :goto_11

    :pswitch_36
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    .line 93
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzt()J

    move-result-wide v8

    int-to-long v10, p1

    invoke-static {v2, v10, v11, v8, v9}, Lcom/google/android/recaptcha/internal/zzaig;->zze(Ljava/lang/Object;JJ)V

    .line 94
    invoke-direct {p0, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto/16 :goto_11

    :pswitch_37
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    .line 91
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzs()I

    move-result v3

    int-to-long v8, p1

    invoke-static {v2, v8, v9, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzc(Ljava/lang/Object;JI)V

    .line 92
    invoke-direct {p0, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto/16 :goto_11

    :pswitch_38
    move-object v4, v3

    move v3, v2

    move-object v2, v4

    move-object v4, v5

    move-object v5, v6

    .line 181
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzr()I

    move-result v6

    .line 182
    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzs(I)Lcom/google/android/recaptcha/internal/zzaga;

    move-result-object v9

    if-eqz v9, :cond_11

    invoke-interface {v9, v6}, Lcom/google/android/recaptcha/internal/zzaga;->zza(I)Z

    move-result v9

    if-eqz v9, :cond_10

    goto :goto_13

    .line 185
    :cond_10
    invoke-static {v2, v3, v6, v4, v5}, Lcom/google/android/recaptcha/internal/zzaho;->zzF(Ljava/lang/Object;IILjava/lang/Object;Lcom/google/android/recaptcha/internal/zzahz;)Ljava/lang/Object;

    move-result-object v4

    goto/16 :goto_11

    :cond_11
    :goto_13
    and-int/2addr p1, v8

    int-to-long v8, p1

    .line 183
    invoke-static {v2, v8, v9, v6}, Lcom/google/android/recaptcha/internal/zzaig;->zzc(Ljava/lang/Object;JI)V

    .line 184
    invoke-direct {p0, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto/16 :goto_11

    :pswitch_39
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    .line 89
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzq()I

    move-result v3

    int-to-long v8, p1

    invoke-static {v2, v8, v9, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzc(Ljava/lang/Object;JI)V

    .line 90
    invoke-direct {p0, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto/16 :goto_11

    :pswitch_3a
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    .line 87
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzp()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v3

    int-to-long v8, p1

    invoke-static {v2, v8, v9, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 88
    invoke-direct {p0, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto/16 :goto_11

    :pswitch_3b
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    .line 186
    invoke-direct {p0, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzt(Ljava/lang/Object;I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/google/android/recaptcha/internal/zzagx;

    .line 187
    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v3

    .line 188
    invoke-virtual {p2, p1, v3, p3}, Lcom/google/android/recaptcha/internal/zzaew;->zzn(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzahn;Lcom/google/android/recaptcha/internal/zzafh;)V

    .line 189
    invoke-direct {p0, v2, v1, p1}, Lcom/google/android/recaptcha/internal/zzaha;->zzu(Ljava/lang/Object;ILjava/lang/Object;)V

    goto/16 :goto_11

    :pswitch_3c
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    .line 190
    invoke-direct {p0, v2, p1, p2}, Lcom/google/android/recaptcha/internal/zzaha;->zzz(Ljava/lang/Object;ILcom/google/android/recaptcha/internal/zzaew;)V

    .line 191
    invoke-direct {p0, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto/16 :goto_11

    :pswitch_3d
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    .line 85
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzk()Z

    move-result v3

    int-to-long v8, p1

    invoke-static {v2, v8, v9, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzg(Ljava/lang/Object;JZ)V

    .line 86
    invoke-direct {p0, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto/16 :goto_11

    :pswitch_3e
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    .line 83
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzj()I

    move-result v3

    int-to-long v8, p1

    invoke-static {v2, v8, v9, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzc(Ljava/lang/Object;JI)V

    .line 84
    invoke-direct {p0, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto/16 :goto_11

    :pswitch_3f
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    .line 81
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzi()J

    move-result-wide v8

    int-to-long v10, p1

    invoke-static {v2, v10, v11, v8, v9}, Lcom/google/android/recaptcha/internal/zzaig;->zze(Ljava/lang/Object;JJ)V

    .line 82
    invoke-direct {p0, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto/16 :goto_11

    :pswitch_40
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    .line 79
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzh()I

    move-result v3

    int-to-long v8, p1

    invoke-static {v2, v8, v9, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzc(Ljava/lang/Object;JI)V

    .line 80
    invoke-direct {p0, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto/16 :goto_11

    :pswitch_41
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    .line 77
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzf()J

    move-result-wide v8

    int-to-long v10, p1

    invoke-static {v2, v10, v11, v8, v9}, Lcom/google/android/recaptcha/internal/zzaig;->zze(Ljava/lang/Object;JJ)V

    .line 78
    invoke-direct {p0, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto/16 :goto_11

    :pswitch_42
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    .line 75
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzg()J

    move-result-wide v8

    int-to-long v10, p1

    invoke-static {v2, v10, v11, v8, v9}, Lcom/google/android/recaptcha/internal/zzaig;->zze(Ljava/lang/Object;JJ)V

    .line 76
    invoke-direct {p0, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto/16 :goto_11

    :pswitch_43
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    .line 73
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zze()F

    move-result v3

    int-to-long v8, p1

    invoke-static {v2, v8, v9, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzi(Ljava/lang/Object;JF)V

    .line 74
    invoke-direct {p0, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V

    goto/16 :goto_11

    :pswitch_44
    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    and-int/2addr p1, v8

    .line 71
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaew;->zzd()D

    move-result-wide v8

    int-to-long v10, p1

    invoke-static {v2, v10, v11, v8, v9}, Lcom/google/android/recaptcha/internal/zzaig;->zzk(Ljava/lang/Object;JD)V

    .line 72
    invoke-direct {p0, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzL(Ljava/lang/Object;I)V
    :try_end_f
    .catch Lcom/google/android/recaptcha/internal/zzagj; {:try_start_f .. :try_end_f} :catch_4
    .catchall {:try_start_f .. :try_end_f} :catchall_8

    goto/16 :goto_11

    :catch_4
    :goto_14
    if-nez v4, :cond_12

    .line 194
    :try_start_10
    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzaib;->zzj(Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zzaia;

    move-result-object v4
    :try_end_10
    .catchall {:try_start_10 .. :try_end_10} :catchall_8

    goto :goto_16

    :catchall_8
    move-exception v0

    :goto_15
    move-object p1, v0

    move-object v1, p0

    goto :goto_1a

    .line 195
    :cond_12
    :goto_16
    :try_start_11
    invoke-virtual {v5, v4, p2, v7}, Lcom/google/android/recaptcha/internal/zzahz;->zzh(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzaew;I)Z

    move-result p1
    :try_end_11
    .catchall {:try_start_11 .. :try_end_11} :catchall_9

    if-nez p1, :cond_e

    iget p1, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzk:I

    :goto_17
    iget p2, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzl:I

    if-ge p1, p2, :cond_13

    iget-object p2, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzj:[I

    .line 196
    aget v3, p2, p1

    move-object v6, v2

    move-object v1, p0

    .line 197
    invoke-direct/range {v1 .. v6}, Lcom/google/android/recaptcha/internal/zzaha;->zzx(Ljava/lang/Object;ILjava/lang/Object;Lcom/google/android/recaptcha/internal/zzahz;Ljava/lang/Object;)Ljava/lang/Object;

    add-int/lit8 p1, p1, 0x1

    goto :goto_17

    :cond_13
    :goto_18
    if-eqz v4, :cond_14

    .line 200
    check-cast v4, Lcom/google/android/recaptcha/internal/zzaia;

    .line 201
    move-object p1, v2

    check-cast p1, Lcom/google/android/recaptcha/internal/zzafv;

    iput-object v4, p1, Lcom/google/android/recaptcha/internal/zzafv;->zzc:Lcom/google/android/recaptcha/internal/zzaia;

    :cond_14
    return-void

    :catchall_9
    move-exception v0

    move-object v1, p0

    :goto_19
    move-object p1, v0

    goto :goto_1a

    :catchall_a
    move-exception v0

    move-object v1, p0

    move-object v2, v3

    move-object v4, v5

    move-object v5, v6

    goto :goto_19

    :catchall_b
    move-exception v0

    move-object v1, p0

    move-object v2, p1

    goto :goto_19

    .line 191
    :goto_1a
    iget p0, v1, Lcom/google/android/recaptcha/internal/zzaha;->zzk:I

    :goto_1b
    iget p2, v1, Lcom/google/android/recaptcha/internal/zzaha;->zzl:I

    if-ge p0, p2, :cond_15

    iget-object p2, v1, Lcom/google/android/recaptcha/internal/zzaha;->zzj:[I

    .line 196
    aget v3, p2, p0

    move-object v6, v2

    .line 197
    invoke-direct/range {v1 .. v6}, Lcom/google/android/recaptcha/internal/zzaha;->zzx(Ljava/lang/Object;ILjava/lang/Object;Lcom/google/android/recaptcha/internal/zzahz;Ljava/lang/Object;)Ljava/lang/Object;

    add-int/lit8 p0, p0, 0x1

    goto :goto_1b

    :cond_15
    if-eqz v4, :cond_16

    .line 200
    check-cast v4, Lcom/google/android/recaptcha/internal/zzaia;

    .line 201
    move-object p0, v2

    check-cast p0, Lcom/google/android/recaptcha/internal/zzafv;

    iput-object v4, p0, Lcom/google/android/recaptcha/internal/zzafv;->zzc:Lcom/google/android/recaptcha/internal/zzaia;

    .line 202
    :cond_16
    throw p1

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_44
        :pswitch_43
        :pswitch_42
        :pswitch_41
        :pswitch_40
        :pswitch_3f
        :pswitch_3e
        :pswitch_3d
        :pswitch_3c
        :pswitch_3b
        :pswitch_3a
        :pswitch_39
        :pswitch_38
        :pswitch_37
        :pswitch_36
        :pswitch_35
        :pswitch_34
        :pswitch_33
        :pswitch_32
        :pswitch_31
        :pswitch_30
        :pswitch_2f
        :pswitch_2e
        :pswitch_2d
        :pswitch_2c
        :pswitch_2b
        :pswitch_2a
        :pswitch_29
        :pswitch_28
        :pswitch_27
        :pswitch_26
        :pswitch_25
        :pswitch_24
        :pswitch_23
        :pswitch_22
        :pswitch_21
        :pswitch_20
        :pswitch_1f
        :pswitch_1e
        :pswitch_1d
        :pswitch_1c
        :pswitch_1b
        :pswitch_1a
        :pswitch_19
        :pswitch_18
        :pswitch_17
        :pswitch_16
        :pswitch_15
        :pswitch_14
        :pswitch_13
        :pswitch_12
        :pswitch_11
        :pswitch_10
        :pswitch_f
        :pswitch_e
        :pswitch_d
        :pswitch_c
        :pswitch_b
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public final zzi(Ljava/lang/Object;[BIIILcom/google/android/recaptcha/internal/zzaeg;)I
    .locals 31

    move-object/from16 v0, p0

    move-object/from16 v2, p1

    move-object/from16 v3, p2

    move/from16 v4, p4

    move-object/from16 v6, p6

    .line 1
    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzF(Ljava/lang/Object;)V

    sget-object v1, Lcom/google/android/recaptcha/internal/zzaha;->zzb:Lsun/misc/Unsafe;

    const/4 v12, -0x1

    move/from16 v5, p3

    move v7, v12

    const/4 v8, 0x0

    const v9, 0xfffff

    const/4 v14, 0x0

    const/4 v15, 0x0

    :goto_0
    if-ge v5, v4, :cond_79

    add-int/lit8 v15, v5, 0x1

    .line 2
    aget-byte v5, v3, v5

    if-gez v5, :cond_0

    .line 3
    invoke-static {v5, v3, v15, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zzb(I[BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v15

    iget v5, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    :cond_0
    move v6, v15

    move v15, v5

    ushr-int/lit8 v5, v15, 0x3

    const/16 v16, 0x0

    const/4 v11, 0x3

    if-le v5, v7, :cond_2

    div-int/2addr v8, v11

    iget v7, v0, Lcom/google/android/recaptcha/internal/zzaha;->zze:I

    if-lt v5, v7, :cond_1

    iget v7, v0, Lcom/google/android/recaptcha/internal/zzaha;->zzf:I

    if-gt v5, v7, :cond_1

    .line 4
    invoke-direct {v0, v5, v8}, Lcom/google/android/recaptcha/internal/zzaha;->zzQ(II)I

    move-result v7

    goto :goto_1

    :cond_1
    move v7, v12

    goto :goto_1

    .line 5
    :cond_2
    invoke-direct {v0, v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzP(I)I

    move-result v7

    :goto_1
    if-ne v7, v12, :cond_3

    move/from16 v10, p5

    move-object/from16 v7, p6

    move-object v12, v2

    move v13, v5

    move v5, v6

    move/from16 v23, v14

    move v14, v15

    move/from16 v8, v16

    move-object v15, v1

    goto/16 :goto_41

    :cond_3
    and-int/lit8 v8, v15, 0x7

    .line 259
    iget-object v12, v0, Lcom/google/android/recaptcha/internal/zzaha;->zzc:[I

    add-int/lit8 v17, v7, 0x1

    .line 6
    aget v11, v12, v17

    const v17, 0xfffff

    invoke-static {v11}, Lcom/google/android/recaptcha/internal/zzaha;->zzC(I)I

    move-result v13

    and-int v3, v11, v17

    int-to-long v3, v3

    move-wide/from16 v18, v3

    const-wide/16 v20, 0x0

    const-string v4, ""

    const-string v24, "CodedInputStream encountered an embedded string or message which claimed to have negative size."

    const/16 v25, 0x1

    const/16 v3, 0x11

    if-gt v13, v3, :cond_17

    add-int/lit8 v3, v7, 0x2

    .line 7
    aget v3, v12, v3

    ushr-int/lit8 v12, v3, 0x14

    shl-int v12, v25, v12

    and-int v3, v3, v17

    move/from16 v23, v5

    if-eq v3, v9, :cond_6

    move/from16 v5, v17

    move/from16 v26, v6

    if-eq v9, v5, :cond_4

    int-to-long v5, v9

    .line 8
    invoke-virtual {v1, v2, v5, v6, v14}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    const v5, 0xfffff

    :cond_4
    if-ne v3, v5, :cond_5

    move/from16 v5, v16

    goto :goto_2

    :cond_5
    int-to-long v5, v3

    .line 9
    invoke-virtual {v1, v2, v5, v6}, Lsun/misc/Unsafe;->getInt(Ljava/lang/Object;J)I

    move-result v5

    :goto_2
    move v14, v3

    goto :goto_3

    :cond_6
    move/from16 v26, v6

    move v5, v14

    move v14, v9

    :goto_3
    packed-switch v13, :pswitch_data_0

    const/4 v3, 0x3

    if-ne v8, v3, :cond_7

    or-int v11, v5, v12

    .line 10
    invoke-direct {v0, v2, v7}, Lcom/google/android/recaptcha/internal/zzaha;->zzt(Ljava/lang/Object;I)Ljava/lang/Object;

    move-result-object v3

    shl-int/lit8 v4, v23, 0x3

    or-int/lit8 v8, v4, 0x4

    .line 11
    invoke-direct {v0, v7}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v4

    move-object/from16 v5, p2

    move-object/from16 v9, p6

    move v13, v7

    move/from16 v6, v26

    move/from16 v7, p4

    .line 12
    invoke-static/range {v3 .. v9}, Lcom/google/android/recaptcha/internal/zzaeh;->zzj(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzahn;[BIIILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v4

    move-object v7, v5

    .line 13
    invoke-direct {v0, v2, v13, v3}, Lcom/google/android/recaptcha/internal/zzaha;->zzu(Ljava/lang/Object;ILjava/lang/Object;)V

    move v5, v4

    move-object v3, v7

    move-object v6, v9

    move v8, v13

    move v9, v14

    move/from16 v7, v23

    const/4 v12, -0x1

    move/from16 v4, p4

    :goto_4
    move v14, v11

    goto/16 :goto_0

    :cond_7
    move v13, v7

    move-object/from16 v7, p2

    move-object/from16 v12, p6

    move-object v3, v1

    move-object v1, v2

    move/from16 p3, v5

    move/from16 v2, v26

    goto/16 :goto_13

    :pswitch_0
    move-object/from16 v9, p6

    move v13, v7

    move/from16 v4, v26

    move-object/from16 v7, p2

    if-nez v8, :cond_8

    or-int v8, v5, v12

    .line 14
    invoke-static {v7, v4, v9}, Lcom/google/android/recaptcha/internal/zzaeh;->zzc([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v11

    iget-wide v3, v9, Lcom/google/android/recaptcha/internal/zzaeg;->zzb:J

    .line 15
    invoke-static {v3, v4}, Lcom/google/android/recaptcha/internal/zzaev;->zzH(J)J

    move-result-wide v5

    move-wide/from16 v3, v18

    .line 16
    invoke-virtual/range {v1 .. v6}, Lsun/misc/Unsafe;->putLong(Ljava/lang/Object;JJ)V

    move-object/from16 v30, v2

    move-object v2, v1

    move-object/from16 v1, v30

    move-object v3, v2

    move-object v2, v1

    move-object v1, v3

    move/from16 v4, p4

    move-object v3, v7

    move-object v6, v9

    move v5, v11

    :goto_5
    move v9, v14

    move/from16 v7, v23

    const/4 v12, -0x1

    :goto_6
    move v14, v8

    move v8, v13

    goto/16 :goto_0

    :cond_8
    move-object/from16 v30, v2

    move-object v2, v1

    move-object/from16 v1, v30

    move-object v3, v2

    move v2, v4

    move/from16 p3, v5

    :goto_7
    move-object v12, v9

    goto/16 :goto_13

    :pswitch_1
    move-object/from16 p3, v2

    move-object v2, v1

    move-object/from16 v1, p3

    move-object/from16 v9, p6

    move/from16 p3, v5

    move v13, v7

    move-wide/from16 v5, v18

    move/from16 v4, v26

    move-object/from16 v7, p2

    if-nez v8, :cond_9

    or-int v3, p3, v12

    .line 17
    invoke-static {v7, v4, v9}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v4

    iget v8, v9, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    .line 18
    invoke-static {v8}, Lcom/google/android/recaptcha/internal/zzaev;->zzG(I)I

    move-result v8

    .line 19
    invoke-virtual {v2, v1, v5, v6, v8}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    :goto_8
    move-object v5, v2

    move-object v2, v1

    move-object v1, v5

    move v5, v4

    move-object v6, v9

    move v8, v13

    move v9, v14

    const/4 v12, -0x1

    move/from16 v4, p4

    move v14, v3

    move-object v3, v7

    :goto_9
    move/from16 v7, v23

    goto/16 :goto_0

    :cond_9
    move-object v3, v2

    move v2, v4

    goto :goto_7

    :pswitch_2
    move-object/from16 p3, v2

    move-object v2, v1

    move-object/from16 v1, p3

    move-object/from16 v9, p6

    move/from16 p3, v5

    move v13, v7

    move-wide/from16 v5, v18

    move/from16 v4, v26

    move-object/from16 v7, p2

    if-nez v8, :cond_9

    .line 20
    invoke-static {v7, v4, v9}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v3

    iget v4, v9, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    .line 21
    invoke-direct {v0, v13}, Lcom/google/android/recaptcha/internal/zzaha;->zzs(I)Lcom/google/android/recaptcha/internal/zzaga;

    move-result-object v8

    const/high16 v18, -0x80000000

    and-int v11, v11, v18

    if-eqz v11, :cond_b

    if-eqz v8, :cond_b

    invoke-interface {v8, v4}, Lcom/google/android/recaptcha/internal/zzaga;->zza(I)Z

    move-result v8

    if-eqz v8, :cond_a

    goto :goto_a

    .line 23
    :cond_a
    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzh(Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zzaia;

    move-result-object v5

    int-to-long v11, v4

    invoke-static {v11, v12}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    invoke-virtual {v5, v15, v4}, Lcom/google/android/recaptcha/internal/zzaia;->zzk(ILjava/lang/Object;)V

    move-object v4, v2

    move-object v2, v1

    move-object v1, v4

    move/from16 v4, p4

    move v5, v3

    move-object v3, v7

    move-object v6, v9

    move v8, v13

    move v9, v14

    move/from16 v7, v23

    const/4 v12, -0x1

    move/from16 v14, p3

    goto/16 :goto_0

    :cond_b
    :goto_a
    or-int v8, p3, v12

    .line 22
    invoke-virtual {v2, v1, v5, v6, v4}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    move-object v4, v2

    move-object v2, v1

    move-object v1, v4

    move/from16 v4, p4

    move v5, v3

    move-object v3, v7

    move-object v6, v9

    goto/16 :goto_5

    :pswitch_3
    move-object/from16 p3, v2

    move-object v2, v1

    move-object/from16 v1, p3

    move-object/from16 v9, p6

    move/from16 p3, v5

    move v13, v7

    move-wide/from16 v5, v18

    move/from16 v4, v26

    const/4 v3, 0x2

    move-object/from16 v7, p2

    if-ne v8, v3, :cond_9

    or-int v3, p3, v12

    .line 24
    invoke-static {v7, v4, v9}, Lcom/google/android/recaptcha/internal/zzaeh;->zzf([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v4

    iget-object v8, v9, Lcom/google/android/recaptcha/internal/zzaeg;->zzc:Ljava/lang/Object;

    .line 25
    invoke-virtual {v2, v1, v5, v6, v8}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    goto/16 :goto_8

    :pswitch_4
    move-object/from16 p3, v2

    move-object v2, v1

    move-object/from16 v1, p3

    move-object/from16 v9, p6

    move/from16 p3, v5

    move v13, v7

    move/from16 v4, v26

    const/4 v3, 0x2

    move-object/from16 v7, p2

    if-ne v8, v3, :cond_c

    or-int v8, p3, v12

    move-object v3, v1

    .line 26
    invoke-direct {v0, v3, v13}, Lcom/google/android/recaptcha/internal/zzaha;->zzt(Ljava/lang/Object;I)Ljava/lang/Object;

    move-result-object v1

    move-object v5, v2

    .line 27
    invoke-direct {v0, v13}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v2

    move-object v6, v7

    move-object v7, v3

    move-object v3, v6

    move-object v6, v9

    move-object v9, v5

    move/from16 v5, p4

    .line 28
    invoke-static/range {v1 .. v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zzi(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzahn;[BIILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v2

    move-object/from16 v30, v3

    move-object v3, v1

    move-object/from16 v1, v30

    .line 29
    invoke-direct {v0, v7, v13, v3}, Lcom/google/android/recaptcha/internal/zzaha;->zzu(Ljava/lang/Object;ILjava/lang/Object;)V

    move/from16 v4, p4

    move-object/from16 v6, p6

    move-object v3, v1

    move v5, v2

    move-object v2, v7

    move-object v1, v9

    goto/16 :goto_5

    :cond_c
    move-object v9, v7

    move-object v7, v1

    move-object v1, v9

    move-object v9, v2

    move v2, v4

    move-object/from16 v12, p6

    :cond_d
    move-object v1, v7

    :cond_e
    :goto_b
    move-object v3, v9

    goto/16 :goto_13

    :pswitch_5
    move-object v9, v1

    move/from16 p3, v5

    move v13, v7

    move-wide/from16 v5, v18

    const/4 v3, 0x2

    move-object/from16 v1, p2

    move-object v7, v2

    move/from16 v18, v12

    move/from16 v2, v26

    move-object/from16 v12, p6

    if-ne v8, v3, :cond_d

    invoke-static {v11}, Lcom/google/android/recaptcha/internal/zzaha;->zzD(I)Z

    move-result v3

    if-eqz v3, :cond_11

    .line 30
    invoke-static {v1, v2, v12}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v2

    iget v3, v12, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-ltz v3, :cond_10

    or-int v8, p3, v18

    if-nez v3, :cond_f

    .line 261
    iput-object v4, v12, Lcom/google/android/recaptcha/internal/zzaeg;->zzc:Ljava/lang/Object;

    goto :goto_d

    .line 31
    :cond_f
    invoke-static {v1, v2, v3}, Lcom/google/android/recaptcha/internal/zzail;->zzc([BII)Ljava/lang/String;

    move-result-object v4

    iput-object v4, v12, Lcom/google/android/recaptcha/internal/zzaeg;->zzc:Ljava/lang/Object;

    :goto_c
    add-int/2addr v2, v3

    goto :goto_d

    .line 261
    :cond_10
    invoke-static/range {v24 .. v24}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    .line 32
    :cond_11
    invoke-static {v1, v2, v12}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v2

    iget v3, v12, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-ltz v3, :cond_13

    or-int v8, p3, v18

    if-nez v3, :cond_12

    .line 263
    iput-object v4, v12, Lcom/google/android/recaptcha/internal/zzaeg;->zzc:Ljava/lang/Object;

    goto :goto_d

    :cond_12
    new-instance v4, Ljava/lang/String;

    .line 33
    sget-object v11, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v4, v1, v2, v3, v11}, Ljava/lang/String;-><init>([BIILjava/nio/charset/Charset;)V

    iput-object v4, v12, Lcom/google/android/recaptcha/internal/zzaeg;->zzc:Ljava/lang/Object;

    goto :goto_c

    .line 261
    :goto_d
    iget-object v3, v12, Lcom/google/android/recaptcha/internal/zzaeg;->zzc:Ljava/lang/Object;

    .line 34
    invoke-virtual {v9, v7, v5, v6, v3}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    move/from16 v4, p4

    move-object v3, v1

    move v5, v2

    move-object v2, v7

    move-object v1, v9

    :goto_e
    move-object v6, v12

    goto/16 :goto_5

    .line 263
    :cond_13
    invoke-static/range {v24 .. v24}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    :pswitch_6
    move-object v9, v1

    move/from16 p3, v5

    move v13, v7

    move-wide/from16 v5, v18

    move-object/from16 v1, p2

    move-object v7, v2

    move/from16 v18, v12

    move/from16 v2, v26

    move-object/from16 v12, p6

    if-nez v8, :cond_d

    or-int v3, p3, v18

    .line 35
    invoke-static {v1, v2, v12}, Lcom/google/android/recaptcha/internal/zzaeh;->zzc([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v2

    move v4, v2

    move/from16 p3, v3

    iget-wide v2, v12, Lcom/google/android/recaptcha/internal/zzaeg;->zzb:J

    cmp-long v2, v2, v20

    if-eqz v2, :cond_14

    move/from16 v3, v25

    goto :goto_f

    :cond_14
    move/from16 v3, v16

    .line 36
    :goto_f
    invoke-static {v7, v5, v6, v3}, Lcom/google/android/recaptcha/internal/zzaig;->zzg(Ljava/lang/Object;JZ)V

    move-object v3, v1

    move v5, v4

    move-object v2, v7

    move-object v1, v9

    move-object v6, v12

    move v8, v13

    move v9, v14

    move/from16 v7, v23

    const/4 v12, -0x1

    :goto_10
    move/from16 v14, p3

    :goto_11
    move/from16 v4, p4

    goto/16 :goto_0

    :pswitch_7
    move-object v9, v1

    move/from16 p3, v5

    move v13, v7

    move-wide/from16 v5, v18

    const/4 v3, 0x5

    move-object/from16 v1, p2

    move-object v7, v2

    move/from16 v18, v12

    move/from16 v2, v26

    move-object/from16 v12, p6

    if-ne v8, v3, :cond_d

    add-int/lit8 v3, v2, 0x4

    or-int v4, p3, v18

    .line 37
    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zzaeh;->zzd([BI)I

    move-result v2

    invoke-virtual {v9, v7, v5, v6, v2}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    move v5, v3

    move-object v2, v7

    move-object v6, v12

    move v8, v13

    move/from16 v7, v23

    const/4 v12, -0x1

    move-object v3, v1

    move-object v1, v9

    move v9, v14

    move v14, v4

    goto :goto_11

    :pswitch_8
    move-object v9, v1

    move/from16 p3, v5

    move v13, v7

    move-wide/from16 v5, v18

    move/from16 v3, v25

    move-object/from16 v1, p2

    move-object v7, v2

    move/from16 v18, v12

    move/from16 v2, v26

    move-object/from16 v12, p6

    if-ne v8, v3, :cond_15

    add-int/lit8 v8, v2, 0x8

    or-int v11, p3, v18

    move-wide v3, v5

    .line 38
    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zzaeh;->zze([BI)J

    move-result-wide v5

    move-object v2, v7

    move-object v7, v1

    move-object v1, v9

    invoke-virtual/range {v1 .. v6}, Lsun/misc/Unsafe;->putLong(Ljava/lang/Object;JJ)V

    move/from16 v4, p4

    move-object v3, v7

    move v5, v8

    move-object v6, v12

    move v8, v13

    move v9, v14

    move/from16 v7, v23

    const/4 v12, -0x1

    goto/16 :goto_4

    :cond_15
    move-object/from16 v30, v7

    move-object v7, v1

    move-object/from16 v1, v30

    goto/16 :goto_b

    :pswitch_9
    move-object v9, v1

    move-object v1, v2

    move/from16 p3, v5

    move v13, v7

    move-wide/from16 v3, v18

    move/from16 v2, v26

    move-object/from16 v7, p2

    move/from16 v18, v12

    move-object/from16 v12, p6

    if-nez v8, :cond_e

    or-int v5, p3, v18

    .line 39
    invoke-static {v7, v2, v12}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v2

    iget v6, v12, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    .line 40
    invoke-virtual {v9, v1, v3, v4, v6}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    move v3, v2

    move-object v2, v1

    move-object v1, v9

    move v9, v14

    move v14, v5

    move v5, v3

    move/from16 v4, p4

    move-object v3, v7

    move-object v6, v12

    move v8, v13

    move/from16 v7, v23

    :goto_12
    const/4 v12, -0x1

    goto/16 :goto_0

    :pswitch_a
    move-object v9, v1

    move-object v1, v2

    move/from16 p3, v5

    move v13, v7

    move-wide/from16 v3, v18

    move/from16 v2, v26

    move-object/from16 v7, p2

    move/from16 v18, v12

    move-object/from16 v12, p6

    if-nez v8, :cond_e

    or-int v8, p3, v18

    .line 41
    invoke-static {v7, v2, v12}, Lcom/google/android/recaptcha/internal/zzaeh;->zzc([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v11

    iget-wide v5, v12, Lcom/google/android/recaptcha/internal/zzaeg;->zzb:J

    move-object v2, v1

    move-object v1, v9

    .line 42
    invoke-virtual/range {v1 .. v6}, Lsun/misc/Unsafe;->putLong(Ljava/lang/Object;JJ)V

    move/from16 v4, p4

    move-object v3, v7

    move v5, v11

    goto/16 :goto_e

    :pswitch_b
    move-object v3, v1

    move-object v1, v2

    move/from16 p3, v5

    move v13, v7

    move-wide/from16 v5, v18

    move/from16 v2, v26

    const/4 v4, 0x5

    move-object/from16 v7, p2

    move/from16 v18, v12

    move-object/from16 v12, p6

    if-ne v8, v4, :cond_16

    add-int/lit8 v4, v2, 0x4

    or-int v8, p3, v18

    .line 43
    invoke-static {v7, v2}, Lcom/google/android/recaptcha/internal/zzaeh;->zzd([BI)I

    move-result v2

    invoke-static {v2}, Ljava/lang/Float;->intBitsToFloat(I)F

    move-result v2

    .line 44
    invoke-static {v1, v5, v6, v2}, Lcom/google/android/recaptcha/internal/zzaig;->zzi(Ljava/lang/Object;JF)V

    move-object v2, v1

    move-object v1, v3

    move v5, v4

    move-object v3, v7

    move-object v6, v12

    move v9, v14

    move/from16 v7, v23

    const/4 v12, -0x1

    move/from16 v4, p4

    goto/16 :goto_6

    :pswitch_c
    move-object v3, v1

    move-object v1, v2

    move/from16 p3, v5

    move v13, v7

    move-wide/from16 v5, v18

    move/from16 v4, v25

    move/from16 v2, v26

    move-object/from16 v7, p2

    move/from16 v18, v12

    move-object/from16 v12, p6

    if-ne v8, v4, :cond_16

    add-int/lit8 v4, v2, 0x8

    or-int v8, p3, v18

    .line 45
    invoke-static {v7, v2}, Lcom/google/android/recaptcha/internal/zzaeh;->zze([BI)J

    move-result-wide v18

    move/from16 p3, v8

    invoke-static/range {v18 .. v19}, Ljava/lang/Double;->longBitsToDouble(J)D

    move-result-wide v7

    .line 46
    invoke-static {v1, v5, v6, v7, v8}, Lcom/google/android/recaptcha/internal/zzaig;->zzk(Ljava/lang/Object;JD)V

    move-object v2, v1

    move-object v1, v3

    move v5, v4

    move-object v6, v12

    move v8, v13

    move v9, v14

    move/from16 v7, v23

    const/4 v12, -0x1

    move-object/from16 v3, p2

    goto/16 :goto_10

    :cond_16
    :goto_13
    move/from16 v10, p5

    move v5, v2

    move-object v7, v12

    move v8, v13

    move v9, v14

    move v14, v15

    move/from16 v13, v23

    move/from16 v23, p3

    move-object v12, v1

    move-object v15, v3

    move-object/from16 v3, p2

    goto/16 :goto_41

    :cond_17
    move-object v3, v1

    move-object v1, v2

    move/from16 v23, v5

    move/from16 v26, v6

    move-wide/from16 v5, v18

    move-object/from16 v18, v12

    move-object/from16 v12, p6

    const/16 v2, 0x1b

    if-ne v13, v2, :cond_1b

    const/4 v2, 0x2

    if-ne v8, v2, :cond_1a

    .line 47
    invoke-virtual {v3, v1, v5, v6}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/android/recaptcha/internal/zzage;

    .line 48
    invoke-interface {v2}, Lcom/google/android/recaptcha/internal/zzage;->zza()Z

    move-result v4

    if-nez v4, :cond_19

    .line 49
    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v4

    if-nez v4, :cond_18

    const/16 v4, 0xa

    goto :goto_14

    :cond_18
    add-int/2addr v4, v4

    .line 50
    :goto_14
    invoke-interface {v2, v4}, Lcom/google/android/recaptcha/internal/zzage;->zzg(I)Lcom/google/android/recaptcha/internal/zzage;

    move-result-object v2

    .line 51
    invoke-virtual {v3, v1, v5, v6, v2}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    :cond_19
    move-object v6, v2

    .line 52
    invoke-direct {v0, v7}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v1

    move/from16 v5, p4

    move v13, v7

    move-object v7, v12

    move v2, v15

    move/from16 v4, v26

    move-object/from16 v12, p1

    move-object v15, v3

    move-object/from16 v3, p2

    .line 53
    invoke-static/range {v1 .. v7}, Lcom/google/android/recaptcha/internal/zzaeh;->zzm(Lcom/google/android/recaptcha/internal/zzahn;I[BIILcom/google/android/recaptcha/internal/zzage;Lcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v1

    move v7, v2

    move/from16 v4, p4

    move-object/from16 v6, p6

    move v5, v1

    move-object v2, v12

    move v8, v13

    move-object v1, v15

    const/4 v12, -0x1

    move v15, v7

    goto/16 :goto_9

    :cond_1a
    move v13, v7

    move v7, v15

    move-object v15, v3

    move-object/from16 v3, p2

    move-object v12, v1

    move/from16 v27, v9

    move v2, v13

    move/from16 v28, v23

    move/from16 v9, v26

    move/from16 v23, v14

    move v14, v7

    move/from16 v7, p4

    goto/16 :goto_36

    :cond_1b
    move-object v12, v1

    move v1, v7

    move v7, v15

    move-object v15, v3

    move/from16 v3, v26

    const/16 v2, 0x31

    const-string v19, "Protocol message had invalid UTF-8."

    const/16 v26, 0x0

    if-gt v13, v2, :cond_67

    move/from16 v27, v3

    int-to-long v2, v11

    .line 54
    invoke-virtual {v15, v12, v5, v6}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v11

    check-cast v11, Lcom/google/android/recaptcha/internal/zzage;

    .line 55
    invoke-interface {v11}, Lcom/google/android/recaptcha/internal/zzage;->zza()Z

    move-result v18

    if-nez v18, :cond_1c

    .line 56
    invoke-interface {v11}, Ljava/util/List;->size()I

    move-result v18

    move-wide/from16 v28, v2

    add-int v2, v18, v18

    .line 57
    invoke-interface {v11, v2}, Lcom/google/android/recaptcha/internal/zzage;->zzg(I)Lcom/google/android/recaptcha/internal/zzage;

    move-result-object v11

    .line 58
    invoke-virtual {v15, v12, v5, v6, v11}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    goto :goto_15

    :cond_1c
    move-wide/from16 v28, v2

    :goto_15
    const-string v2, "While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length."

    packed-switch v13, :pswitch_data_1

    const/4 v3, 0x3

    if-ne v8, v3, :cond_1f

    and-int/lit8 v2, v7, -0x8

    or-int/lit8 v5, v2, 0x4

    move v13, v1

    .line 59
    invoke-direct {v0, v13}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v1

    move-object/from16 v2, p2

    move/from16 v4, p4

    move-object/from16 v6, p6

    move/from16 v3, v27

    .line 60
    invoke-static/range {v1 .. v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zzh(Lcom/google/android/recaptcha/internal/zzahn;[BIIILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v8

    move-object/from16 p3, v1

    iget-object v1, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zzc:Ljava/lang/Object;

    .line 61
    invoke-interface {v11, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    :goto_16
    if-ge v8, v4, :cond_1e

    move/from16 v26, v3

    .line 62
    invoke-static {v2, v8, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v3

    iget v1, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-ne v7, v1, :cond_1d

    move-object/from16 v1, p3

    move/from16 v27, v9

    move/from16 v9, v26

    .line 63
    invoke-static/range {v1 .. v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zzh(Lcom/google/android/recaptcha/internal/zzahn;[BIIILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v8

    move-object v3, v2

    iget-object v2, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zzc:Ljava/lang/Object;

    .line 64
    invoke-interface {v11, v2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    move-object v2, v3

    move v3, v9

    move/from16 v9, v27

    goto :goto_16

    :cond_1d
    move/from16 v27, v9

    move/from16 v9, v26

    :goto_17
    move-object v3, v2

    goto :goto_18

    :cond_1e
    move/from16 v27, v9

    move v9, v3

    goto :goto_17

    :goto_18
    move-object v1, v6

    move v5, v8

    :goto_19
    move/from16 v18, v13

    move/from16 v28, v23

    move/from16 v23, v14

    move v14, v7

    move v7, v4

    move v4, v9

    goto/16 :goto_33

    :cond_1f
    move/from16 v30, v27

    move/from16 v27, v9

    move/from16 v9, v30

    move-object/from16 v3, p2

    move/from16 v18, v1

    move v4, v9

    move/from16 v28, v23

    move-object/from16 v1, p6

    move/from16 v23, v14

    move v14, v7

    move/from16 v7, p4

    goto/16 :goto_32

    :pswitch_d
    move/from16 v3, v27

    move/from16 v27, v9

    move v9, v3

    move-object/from16 v3, p2

    move/from16 v4, p4

    move-object/from16 v6, p6

    move v13, v1

    const/4 v1, 0x2

    if-ne v8, v1, :cond_25

    .line 65
    sget v1, Lcom/google/android/recaptcha/internal/zzaeh;->$r8$clinit:I

    .line 66
    check-cast v11, Lcom/google/android/recaptcha/internal/zzagq;

    .line 67
    invoke-static {v3, v9, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v1

    iget v5, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-ltz v5, :cond_24

    .line 68
    array-length v8, v3

    sub-int/2addr v8, v1

    if-gt v5, v8, :cond_23

    add-int/2addr v5, v1

    :goto_1a
    if-ge v1, v5, :cond_20

    .line 69
    invoke-static {v3, v1, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zzc([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v1

    move v8, v1

    move-object/from16 p3, v2

    iget-wide v1, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zzb:J

    .line 70
    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zzaev;->zzH(J)J

    move-result-wide v1

    invoke-virtual {v11, v1, v2}, Lcom/google/android/recaptcha/internal/zzagq;->zzf(J)V

    move-object/from16 v2, p3

    move v1, v8

    goto :goto_1a

    :cond_20
    move-object/from16 p3, v2

    if-ne v1, v5, :cond_22

    :cond_21
    :goto_1b
    move v5, v1

    move-object v1, v6

    goto :goto_19

    .line 269
    :cond_22
    invoke-static/range {p3 .. p3}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    :cond_23
    move-object/from16 p3, v2

    .line 267
    invoke-static/range {p3 .. p3}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    .line 265
    :cond_24
    invoke-static/range {v24 .. v24}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    :cond_25
    if-nez v8, :cond_26

    .line 71
    sget v1, Lcom/google/android/recaptcha/internal/zzaeh;->$r8$clinit:I

    .line 72
    check-cast v11, Lcom/google/android/recaptcha/internal/zzagq;

    .line 73
    invoke-static {v3, v9, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zzc([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v1

    move/from16 p3, v1

    iget-wide v1, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zzb:J

    .line 74
    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zzaev;->zzH(J)J

    move-result-wide v1

    invoke-virtual {v11, v1, v2}, Lcom/google/android/recaptcha/internal/zzagq;->zzf(J)V

    :goto_1c
    move/from16 v1, p3

    if-ge v1, v4, :cond_21

    .line 75
    invoke-static {v3, v1, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v2

    iget v5, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-ne v7, v5, :cond_21

    .line 76
    invoke-static {v3, v2, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zzc([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v1

    move/from16 p3, v1

    iget-wide v1, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zzb:J

    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zzaev;->zzH(J)J

    move-result-wide v1

    .line 77
    invoke-virtual {v11, v1, v2}, Lcom/google/android/recaptcha/internal/zzagq;->zzf(J)V

    goto :goto_1c

    :cond_26
    move-object v1, v6

    move/from16 v18, v13

    move/from16 v28, v23

    move/from16 v23, v14

    move v14, v7

    move v7, v4

    move v4, v9

    goto/16 :goto_32

    :pswitch_e
    move/from16 p3, v27

    move/from16 v27, v9

    move/from16 v9, p3

    move-object/from16 v3, p2

    move/from16 v4, p4

    move-object/from16 v6, p6

    move v13, v1

    move-object/from16 p3, v2

    const/4 v1, 0x2

    if-ne v8, v1, :cond_2b

    .line 78
    sget v1, Lcom/google/android/recaptcha/internal/zzaeh;->$r8$clinit:I

    .line 79
    check-cast v11, Lcom/google/android/recaptcha/internal/zzafw;

    .line 80
    invoke-static {v3, v9, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v1

    iget v2, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-ltz v2, :cond_2a

    .line 81
    array-length v5, v3

    sub-int/2addr v5, v1

    if-gt v2, v5, :cond_29

    add-int/2addr v2, v1

    :goto_1d
    if-ge v1, v2, :cond_27

    .line 82
    invoke-static {v3, v1, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v1

    iget v5, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    .line 83
    invoke-static {v5}, Lcom/google/android/recaptcha/internal/zzaev;->zzG(I)I

    move-result v5

    invoke-virtual {v11, v5}, Lcom/google/android/recaptcha/internal/zzafw;->zzh(I)V

    goto :goto_1d

    :cond_27
    if-ne v1, v2, :cond_28

    goto/16 :goto_1b

    .line 275
    :cond_28
    invoke-static/range {p3 .. p3}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    .line 273
    :cond_29
    invoke-static/range {p3 .. p3}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    .line 271
    :cond_2a
    invoke-static/range {v24 .. v24}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    :cond_2b
    if-nez v8, :cond_26

    .line 84
    sget v1, Lcom/google/android/recaptcha/internal/zzaeh;->$r8$clinit:I

    .line 85
    check-cast v11, Lcom/google/android/recaptcha/internal/zzafw;

    .line 86
    invoke-static {v3, v9, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v1

    iget v2, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    .line 87
    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzaev;->zzG(I)I

    move-result v2

    invoke-virtual {v11, v2}, Lcom/google/android/recaptcha/internal/zzafw;->zzh(I)V

    :goto_1e
    if-ge v1, v4, :cond_21

    .line 88
    invoke-static {v3, v1, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v2

    iget v5, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-ne v7, v5, :cond_21

    .line 89
    invoke-static {v3, v2, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v1

    iget v2, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzaev;->zzG(I)I

    move-result v2

    .line 90
    invoke-virtual {v11, v2}, Lcom/google/android/recaptcha/internal/zzafw;->zzh(I)V

    goto :goto_1e

    :pswitch_f
    move/from16 v3, v27

    move/from16 v27, v9

    move v9, v3

    move-object/from16 v3, p2

    move/from16 v4, p4

    move-object/from16 v6, p6

    move v13, v1

    const/4 v1, 0x2

    if-ne v8, v1, :cond_2c

    .line 91
    invoke-static {v3, v9, v11, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zzl([BILcom/google/android/recaptcha/internal/zzage;Lcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v1

    move v5, v9

    move-object v9, v3

    move v3, v5

    move v8, v7

    move-object v5, v11

    move/from16 v18, v1

    move v11, v4

    move-object v7, v6

    goto :goto_1f

    :cond_2c
    if-nez v8, :cond_2d

    move-object v2, v3

    move v1, v7

    move v3, v9

    move-object v5, v11

    .line 92
    invoke-static/range {v1 .. v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zzk(I[BIILcom/google/android/recaptcha/internal/zzage;Lcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v7

    move v8, v1

    move-object v9, v2

    move v1, v7

    move v11, v4

    move-object v7, v6

    move/from16 v18, v1

    .line 93
    :goto_1f
    invoke-direct {v0, v13}, Lcom/google/android/recaptcha/internal/zzaha;->zzs(I)Lcom/google/android/recaptcha/internal/zzaga;

    move-result-object v4

    move-object v6, v5

    const/4 v5, 0x0

    move/from16 v26, v3

    move-object v3, v6

    iget-object v6, v0, Lcom/google/android/recaptcha/internal/zzaha;->zzm:Lcom/google/android/recaptcha/internal/zzahz;

    move-object v1, v12

    move/from16 v2, v23

    move/from16 v12, v26

    .line 94
    invoke-static/range {v1 .. v6}, Lcom/google/android/recaptcha/internal/zzaho;->zzE(Ljava/lang/Object;ILjava/util/List;Lcom/google/android/recaptcha/internal/zzaga;Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzahz;)Ljava/lang/Object;

    move/from16 v28, v2

    move-object v1, v7

    move-object v3, v9

    move v7, v11

    move v4, v12

    move/from16 v23, v14

    move/from16 v5, v18

    move-object/from16 v12, p1

    move v14, v8

    move/from16 v18, v13

    goto/16 :goto_33

    :cond_2d
    move v8, v7

    move-object/from16 v12, p1

    move v7, v4

    move-object v1, v6

    move v4, v9

    move/from16 v18, v13

    move/from16 v28, v23

    move/from16 v23, v14

    move v14, v8

    goto/16 :goto_32

    :pswitch_10
    move v13, v1

    move-object/from16 p3, v2

    move v1, v7

    move-object v5, v11

    move/from16 v12, v27

    const/4 v2, 0x2

    move/from16 v11, p4

    move-object/from16 v7, p6

    move/from16 v27, v9

    move-object/from16 v9, p2

    if-ne v8, v2, :cond_35

    .line 95
    invoke-static {v9, v12, v7}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v2

    iget v3, v7, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-ltz v3, :cond_34

    .line 96
    array-length v4, v9

    sub-int/2addr v4, v2

    if-gt v3, v4, :cond_33

    if-nez v3, :cond_2e

    .line 97
    sget-object v3, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    invoke-interface {v5, v3}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_21

    .line 98
    :cond_2e
    invoke-static {v9, v2, v3}, Lcom/google/android/recaptcha/internal/zzaer;->zzs([BII)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v4

    invoke-interface {v5, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    :goto_20
    add-int/2addr v2, v3

    :goto_21
    if-ge v2, v11, :cond_32

    .line 99
    invoke-static {v9, v2, v7}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v3

    iget v4, v7, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-ne v1, v4, :cond_32

    .line 100
    invoke-static {v9, v3, v7}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v2

    iget v3, v7, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-ltz v3, :cond_31

    .line 101
    array-length v4, v9

    sub-int/2addr v4, v2

    if-gt v3, v4, :cond_30

    if-nez v3, :cond_2f

    .line 283
    sget-object v3, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    .line 102
    invoke-interface {v5, v3}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_21

    .line 103
    :cond_2f
    invoke-static {v9, v2, v3}, Lcom/google/android/recaptcha/internal/zzaer;->zzs([BII)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v4

    invoke-interface {v5, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_20

    .line 283
    :cond_30
    invoke-static/range {p3 .. p3}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    .line 281
    :cond_31
    invoke-static/range {v24 .. v24}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    :cond_32
    move v5, v2

    move-object v3, v9

    move v4, v12

    move/from16 v18, v13

    move/from16 v28, v23

    move-object/from16 v12, p1

    move/from16 v23, v14

    move v14, v1

    move-object v1, v7

    move v7, v11

    goto/16 :goto_33

    .line 279
    :cond_33
    invoke-static/range {p3 .. p3}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    .line 277
    :cond_34
    invoke-static/range {v24 .. v24}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    :cond_35
    move-object v3, v9

    move v4, v12

    move/from16 v18, v13

    move/from16 v28, v23

    move-object/from16 v12, p1

    move/from16 v23, v14

    move v14, v1

    move-object v1, v7

    move v7, v11

    goto/16 :goto_32

    :pswitch_11
    move v13, v1

    move v1, v7

    move-object v5, v11

    move/from16 v12, v27

    const/4 v2, 0x2

    move/from16 v11, p4

    move-object/from16 v7, p6

    move/from16 v27, v9

    move-object/from16 v9, p2

    if-ne v8, v2, :cond_36

    move v2, v1

    .line 104
    invoke-direct {v0, v13}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v1

    move-object v6, v5

    move-object v3, v9

    move v5, v11

    move v4, v12

    move/from16 v9, v23

    move-object/from16 v12, p1

    .line 105
    invoke-static/range {v1 .. v7}, Lcom/google/android/recaptcha/internal/zzaeh;->zzm(Lcom/google/android/recaptcha/internal/zzahn;I[BIILcom/google/android/recaptcha/internal/zzage;Lcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v1

    move-object v6, v7

    move v7, v5

    move/from16 v28, v9

    move/from16 v18, v13

    move/from16 v23, v14

    move v5, v1

    move v14, v2

    :goto_22
    move-object v1, v6

    goto/16 :goto_33

    :cond_36
    move v5, v11

    move v11, v12

    move-object/from16 v12, p1

    move-object v3, v9

    move v4, v11

    move/from16 v18, v13

    move/from16 v28, v23

    move/from16 v23, v14

    move v14, v1

    move-object v1, v7

    :goto_23
    move v7, v5

    goto/16 :goto_32

    :pswitch_12
    move-object/from16 v3, p2

    move/from16 v5, p4

    move-object/from16 v6, p6

    move v13, v1

    move-object v1, v11

    move/from16 v11, v27

    const/4 v2, 0x2

    move/from16 v27, v9

    move/from16 v9, v23

    if-ne v8, v2, :cond_44

    const-wide/32 v22, 0x20000000

    and-long v22, v28, v22

    cmp-long v2, v22, v20

    if-nez v2, :cond_3d

    .line 106
    invoke-static {v3, v11, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v2

    iget v8, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-ltz v8, :cond_3c

    if-nez v8, :cond_37

    .line 107
    invoke-interface {v1, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    move/from16 v18, v13

    move/from16 v23, v14

    goto :goto_25

    :cond_37
    move/from16 v18, v13

    .line 114
    new-instance v13, Ljava/lang/String;

    move/from16 v23, v14

    .line 108
    sget-object v14, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v13, v3, v2, v8, v14}, Ljava/lang/String;-><init>([BIILjava/nio/charset/Charset;)V

    .line 109
    invoke-interface {v1, v13}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    :goto_24
    add-int/2addr v2, v8

    :goto_25
    if-ge v2, v5, :cond_3a

    .line 110
    invoke-static {v3, v2, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v8

    iget v13, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-ne v7, v13, :cond_3a

    .line 111
    invoke-static {v3, v8, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v2

    iget v8, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-ltz v8, :cond_39

    if-nez v8, :cond_38

    .line 112
    invoke-interface {v1, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_25

    :cond_38
    new-instance v13, Ljava/lang/String;

    .line 113
    sget-object v14, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v13, v3, v2, v8, v14}, Ljava/lang/String;-><init>([BIILjava/nio/charset/Charset;)V

    .line 114
    invoke-interface {v1, v13}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_24

    .line 287
    :cond_39
    invoke-static/range {v24 .. v24}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    :cond_3a
    :goto_26
    move-object v1, v6

    move v14, v7

    move/from16 v28, v9

    move v4, v11

    move v7, v5

    :cond_3b
    :goto_27
    move v5, v2

    goto/16 :goto_33

    .line 285
    :cond_3c
    invoke-static/range {v24 .. v24}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    :cond_3d
    move/from16 v18, v13

    move/from16 v23, v14

    .line 115
    invoke-static {v3, v11, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v2

    iget v8, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-ltz v8, :cond_43

    if-nez v8, :cond_3e

    .line 116
    invoke-interface {v1, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_29

    :cond_3e
    add-int v13, v2, v8

    .line 117
    invoke-static {v3, v2, v13}, Lcom/google/android/recaptcha/internal/zzail;->zza([BII)Z

    move-result v14

    if-eqz v14, :cond_42

    .line 291
    new-instance v14, Ljava/lang/String;

    move/from16 p3, v13

    .line 118
    sget-object v13, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v14, v3, v2, v8, v13}, Ljava/lang/String;-><init>([BIILjava/nio/charset/Charset;)V

    .line 119
    invoke-interface {v1, v14}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    :goto_28
    move/from16 v2, p3

    :goto_29
    if-ge v2, v5, :cond_3a

    .line 120
    invoke-static {v3, v2, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v8

    iget v13, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-ne v7, v13, :cond_3a

    .line 121
    invoke-static {v3, v8, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v2

    iget v8, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-ltz v8, :cond_41

    if-nez v8, :cond_3f

    .line 122
    invoke-interface {v1, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_29

    :cond_3f
    add-int v13, v2, v8

    .line 123
    invoke-static {v3, v2, v13}, Lcom/google/android/recaptcha/internal/zzail;->zza([BII)Z

    move-result v14

    if-eqz v14, :cond_40

    .line 295
    new-instance v14, Ljava/lang/String;

    move/from16 p3, v13

    .line 124
    sget-object v13, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v14, v3, v2, v8, v13}, Ljava/lang/String;-><init>([BIILjava/nio/charset/Charset;)V

    .line 125
    invoke-interface {v1, v14}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_28

    .line 295
    :cond_40
    invoke-static/range {v19 .. v19}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    .line 293
    :cond_41
    invoke-static/range {v24 .. v24}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    .line 291
    :cond_42
    invoke-static/range {v19 .. v19}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    .line 289
    :cond_43
    invoke-static/range {v24 .. v24}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    :cond_44
    move/from16 v18, v13

    move/from16 v23, v14

    :cond_45
    :goto_2a
    move-object v1, v6

    move v14, v7

    move/from16 v28, v9

    move v4, v11

    goto/16 :goto_23

    :pswitch_13
    move-object/from16 v3, p2

    move/from16 v5, p4

    move-object/from16 v6, p6

    move/from16 v18, v1

    move-object/from16 p3, v2

    move-object v1, v11

    move/from16 v11, v27

    const/4 v2, 0x2

    move/from16 v27, v9

    move/from16 v9, v23

    move/from16 v23, v14

    if-ne v8, v2, :cond_4a

    .line 126
    sget v2, Lcom/google/android/recaptcha/internal/zzaeh;->$r8$clinit:I

    .line 127
    invoke-static {v1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 128
    invoke-static {v3, v11, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v1

    iget v2, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-ltz v2, :cond_49

    .line 129
    array-length v4, v3

    sub-int/2addr v4, v1

    if-gt v2, v4, :cond_48

    add-int/2addr v2, v1

    if-lt v1, v2, :cond_47

    if-ne v1, v2, :cond_46

    :goto_2b
    move v14, v7

    move/from16 v28, v9

    move v4, v11

    move v7, v5

    move v5, v1

    goto/16 :goto_22

    .line 301
    :cond_46
    invoke-static/range {p3 .. p3}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    .line 130
    :cond_47
    invoke-static {v3, v1, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zzc([BILcom/google/android/recaptcha/internal/zzaeg;)I

    .line 131
    throw v26

    .line 299
    :cond_48
    invoke-static/range {p3 .. p3}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    .line 297
    :cond_49
    invoke-static/range {v24 .. v24}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    :cond_4a
    if-eqz v8, :cond_4b

    goto :goto_2a

    .line 132
    :cond_4b
    sget v0, Lcom/google/android/recaptcha/internal/zzaeh;->$r8$clinit:I

    .line 133
    invoke-static {v1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 134
    invoke-static {v3, v11, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zzc([BILcom/google/android/recaptcha/internal/zzaeg;)I

    iget-wide v0, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zzb:J

    .line 135
    throw v26

    :pswitch_14
    move-object/from16 v3, p2

    move/from16 v5, p4

    move-object/from16 v6, p6

    move/from16 v18, v1

    move-object/from16 p3, v2

    move-object v1, v11

    move/from16 v11, v27

    const/4 v2, 0x2

    move/from16 v27, v9

    move/from16 v9, v23

    move/from16 v23, v14

    if-ne v8, v2, :cond_50

    .line 139
    sget v2, Lcom/google/android/recaptcha/internal/zzaeh;->$r8$clinit:I

    .line 140
    check-cast v1, Lcom/google/android/recaptcha/internal/zzafw;

    .line 141
    invoke-static {v3, v11, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v2

    iget v4, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-ltz v4, :cond_4f

    .line 142
    array-length v8, v3

    sub-int/2addr v8, v2

    if-gt v4, v8, :cond_4e

    add-int v8, v2, v4

    .line 143
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzafw;->size()I

    move-result v13

    shr-int/lit8 v4, v4, 0x2

    add-int/2addr v13, v4

    invoke-virtual {v1, v13}, Lcom/google/android/recaptcha/internal/zzafw;->zzi(I)V

    :goto_2c
    if-ge v2, v8, :cond_4c

    .line 144
    invoke-static {v3, v2}, Lcom/google/android/recaptcha/internal/zzaeh;->zzd([BI)I

    move-result v4

    invoke-virtual {v1, v4}, Lcom/google/android/recaptcha/internal/zzafw;->zzh(I)V

    add-int/lit8 v2, v2, 0x4

    goto :goto_2c

    :cond_4c
    if-ne v2, v8, :cond_4d

    goto/16 :goto_26

    .line 307
    :cond_4d
    invoke-static/range {p3 .. p3}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    .line 305
    :cond_4e
    invoke-static/range {p3 .. p3}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    .line 303
    :cond_4f
    invoke-static/range {v24 .. v24}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    :cond_50
    const/4 v4, 0x5

    if-ne v8, v4, :cond_45

    add-int/lit8 v2, v11, 0x4

    .line 145
    sget v4, Lcom/google/android/recaptcha/internal/zzaeh;->$r8$clinit:I

    .line 146
    check-cast v1, Lcom/google/android/recaptcha/internal/zzafw;

    .line 147
    invoke-static {v3, v11}, Lcom/google/android/recaptcha/internal/zzaeh;->zzd([BI)I

    move-result v4

    invoke-virtual {v1, v4}, Lcom/google/android/recaptcha/internal/zzafw;->zzh(I)V

    :goto_2d
    if-ge v2, v5, :cond_3a

    .line 148
    invoke-static {v3, v2, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v4

    iget v8, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-ne v7, v8, :cond_3a

    .line 149
    invoke-static {v3, v4}, Lcom/google/android/recaptcha/internal/zzaeh;->zzd([BI)I

    move-result v2

    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzafw;->zzh(I)V

    add-int/lit8 v2, v4, 0x4

    goto :goto_2d

    :pswitch_15
    move-object/from16 v3, p2

    move/from16 v5, p4

    move-object/from16 v6, p6

    move/from16 v18, v1

    move-object/from16 p3, v2

    move-object v1, v11

    move/from16 v11, v27

    const/4 v2, 0x2

    move/from16 v27, v9

    move/from16 v9, v23

    move/from16 v23, v14

    if-ne v8, v2, :cond_55

    .line 150
    sget v2, Lcom/google/android/recaptcha/internal/zzaeh;->$r8$clinit:I

    .line 151
    check-cast v1, Lcom/google/android/recaptcha/internal/zzagq;

    .line 152
    invoke-static {v3, v11, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v2

    iget v4, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-ltz v4, :cond_54

    .line 153
    array-length v8, v3

    sub-int/2addr v8, v2

    if-gt v4, v8, :cond_53

    add-int v8, v2, v4

    .line 154
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzagq;->size()I

    move-result v13

    shr-int/lit8 v4, v4, 0x3

    add-int/2addr v13, v4

    invoke-virtual {v1, v13}, Lcom/google/android/recaptcha/internal/zzagq;->zzh(I)V

    :goto_2e
    if-ge v2, v8, :cond_51

    .line 155
    invoke-static {v3, v2}, Lcom/google/android/recaptcha/internal/zzaeh;->zze([BI)J

    move-result-wide v13

    invoke-virtual {v1, v13, v14}, Lcom/google/android/recaptcha/internal/zzagq;->zzf(J)V

    add-int/lit8 v2, v2, 0x8

    goto :goto_2e

    :cond_51
    if-ne v2, v8, :cond_52

    goto/16 :goto_26

    .line 313
    :cond_52
    invoke-static/range {p3 .. p3}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    .line 311
    :cond_53
    invoke-static/range {p3 .. p3}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    .line 309
    :cond_54
    invoke-static/range {v24 .. v24}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    :cond_55
    const/4 v4, 0x1

    if-ne v8, v4, :cond_45

    add-int/lit8 v2, v11, 0x8

    .line 156
    sget v4, Lcom/google/android/recaptcha/internal/zzaeh;->$r8$clinit:I

    .line 157
    check-cast v1, Lcom/google/android/recaptcha/internal/zzagq;

    .line 158
    invoke-static {v3, v11}, Lcom/google/android/recaptcha/internal/zzaeh;->zze([BI)J

    move-result-wide v13

    invoke-virtual {v1, v13, v14}, Lcom/google/android/recaptcha/internal/zzagq;->zzf(J)V

    :goto_2f
    if-ge v2, v5, :cond_3a

    .line 159
    invoke-static {v3, v2, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v4

    iget v8, v6, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-ne v7, v8, :cond_3a

    .line 160
    invoke-static {v3, v4}, Lcom/google/android/recaptcha/internal/zzaeh;->zze([BI)J

    move-result-wide v13

    invoke-virtual {v1, v13, v14}, Lcom/google/android/recaptcha/internal/zzagq;->zzf(J)V

    add-int/lit8 v2, v4, 0x8

    goto :goto_2f

    :pswitch_16
    move-object/from16 v3, p2

    move/from16 v5, p4

    move-object/from16 v6, p6

    move/from16 v18, v1

    move-object v1, v11

    move/from16 v11, v27

    const/4 v2, 0x2

    move/from16 v27, v9

    move/from16 v9, v23

    move/from16 v23, v14

    if-ne v8, v2, :cond_56

    .line 161
    invoke-static {v3, v11, v1, v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zzl([BILcom/google/android/recaptcha/internal/zzage;Lcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v1

    goto/16 :goto_2b

    :cond_56
    if-nez v8, :cond_57

    move-object v2, v3

    move v4, v5

    move v3, v11

    move-object v5, v1

    move v1, v7

    .line 162
    invoke-static/range {v1 .. v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zzk(I[BIILcom/google/android/recaptcha/internal/zzage;Lcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v5

    move v14, v1

    move v7, v4

    move-object v1, v6

    move v4, v3

    move-object v3, v2

    move/from16 v28, v9

    goto/16 :goto_33

    :cond_57
    move-object v1, v6

    move v14, v7

    move v4, v11

    move v7, v5

    move/from16 v28, v9

    goto/16 :goto_32

    :pswitch_17
    move-object/from16 v3, p2

    move/from16 v18, v1

    move-object/from16 p3, v2

    move-object v5, v11

    move/from16 v4, v27

    const/4 v2, 0x2

    move-object/from16 v1, p6

    move/from16 v27, v9

    move/from16 v9, v23

    move/from16 v23, v14

    move v14, v7

    move/from16 v7, p4

    if-ne v8, v2, :cond_5c

    .line 163
    sget v2, Lcom/google/android/recaptcha/internal/zzaeh;->$r8$clinit:I

    .line 164
    move-object v11, v5

    check-cast v11, Lcom/google/android/recaptcha/internal/zzagq;

    .line 165
    invoke-static {v3, v4, v1}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v2

    iget v5, v1, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-ltz v5, :cond_5b

    .line 166
    array-length v6, v3

    sub-int/2addr v6, v2

    if-gt v5, v6, :cond_5a

    add-int/2addr v5, v2

    :goto_30
    if-ge v2, v5, :cond_58

    .line 167
    invoke-static {v3, v2, v1}, Lcom/google/android/recaptcha/internal/zzaeh;->zzc([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v2

    move/from16 v28, v9

    iget-wide v8, v1, Lcom/google/android/recaptcha/internal/zzaeg;->zzb:J

    .line 168
    invoke-virtual {v11, v8, v9}, Lcom/google/android/recaptcha/internal/zzagq;->zzf(J)V

    move/from16 v9, v28

    goto :goto_30

    :cond_58
    move/from16 v28, v9

    if-ne v2, v5, :cond_59

    goto/16 :goto_27

    .line 319
    :cond_59
    invoke-static/range {p3 .. p3}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    .line 317
    :cond_5a
    invoke-static/range {p3 .. p3}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    .line 315
    :cond_5b
    invoke-static/range {v24 .. v24}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    :cond_5c
    move/from16 v28, v9

    if-nez v8, :cond_64

    .line 169
    sget v2, Lcom/google/android/recaptcha/internal/zzaeh;->$r8$clinit:I

    .line 170
    move-object v11, v5

    check-cast v11, Lcom/google/android/recaptcha/internal/zzagq;

    .line 171
    invoke-static {v3, v4, v1}, Lcom/google/android/recaptcha/internal/zzaeh;->zzc([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v2

    iget-wide v5, v1, Lcom/google/android/recaptcha/internal/zzaeg;->zzb:J

    .line 172
    invoke-virtual {v11, v5, v6}, Lcom/google/android/recaptcha/internal/zzagq;->zzf(J)V

    :goto_31
    if-ge v2, v7, :cond_3b

    .line 173
    invoke-static {v3, v2, v1}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v5

    iget v6, v1, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-ne v14, v6, :cond_3b

    .line 174
    invoke-static {v3, v5, v1}, Lcom/google/android/recaptcha/internal/zzaeh;->zzc([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v2

    iget-wide v5, v1, Lcom/google/android/recaptcha/internal/zzaeg;->zzb:J

    .line 175
    invoke-virtual {v11, v5, v6}, Lcom/google/android/recaptcha/internal/zzagq;->zzf(J)V

    goto :goto_31

    :pswitch_18
    move-object/from16 v3, p2

    move/from16 v18, v1

    move-object/from16 p3, v2

    move-object v5, v11

    move/from16 v28, v23

    move/from16 v4, v27

    const/4 v2, 0x2

    move-object/from16 v1, p6

    move/from16 v27, v9

    move/from16 v23, v14

    move v14, v7

    move/from16 v7, p4

    if-ne v8, v2, :cond_5f

    .line 176
    sget v0, Lcom/google/android/recaptcha/internal/zzaeh;->$r8$clinit:I

    .line 177
    invoke-static {v5}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 178
    invoke-static {v3, v4, v1}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v0

    iget v1, v1, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-ltz v1, :cond_5e

    .line 179
    array-length v2, v3

    sub-int/2addr v2, v0

    if-le v1, v2, :cond_5d

    .line 323
    invoke-static/range {p3 .. p3}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    .line 180
    :cond_5d
    throw v26

    .line 321
    :cond_5e
    invoke-static/range {v24 .. v24}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    :cond_5f
    const/4 v2, 0x5

    if-eq v8, v2, :cond_60

    goto :goto_32

    .line 183
    :cond_60
    sget v0, Lcom/google/android/recaptcha/internal/zzaeh;->$r8$clinit:I

    .line 184
    invoke-static {v5}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 185
    invoke-static {v3, v4}, Lcom/google/android/recaptcha/internal/zzaeh;->zzd([BI)I

    move-result v0

    invoke-static {v0}, Ljava/lang/Float;->intBitsToFloat(I)F

    .line 186
    throw v26

    :pswitch_19
    move-object/from16 v3, p2

    move/from16 v18, v1

    move-object/from16 p3, v2

    move-object v5, v11

    move/from16 v28, v23

    move/from16 v4, v27

    const/4 v2, 0x2

    move-object/from16 v1, p6

    move/from16 v27, v9

    move/from16 v23, v14

    move v14, v7

    move/from16 v7, p4

    if-ne v8, v2, :cond_63

    .line 190
    sget v0, Lcom/google/android/recaptcha/internal/zzaeh;->$r8$clinit:I

    .line 191
    invoke-static {v5}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 192
    invoke-static {v3, v4, v1}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v0

    iget v1, v1, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-ltz v1, :cond_62

    .line 193
    array-length v2, v3

    sub-int/2addr v2, v0

    if-le v1, v2, :cond_61

    .line 329
    invoke-static/range {p3 .. p3}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    .line 194
    :cond_61
    throw v26

    .line 327
    :cond_62
    invoke-static/range {v24 .. v24}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    :cond_63
    const/4 v2, 0x1

    if-eq v8, v2, :cond_66

    :cond_64
    :goto_32
    move v5, v4

    :goto_33
    if-eq v5, v4, :cond_65

    move-object v6, v1

    move v4, v7

    move-object v2, v12

    move-object v1, v15

    move/from16 v8, v18

    move/from16 v9, v27

    move/from16 v7, v28

    :goto_34
    const/4 v12, -0x1

    move v15, v14

    move/from16 v14, v23

    goto/16 :goto_0

    :cond_65
    move/from16 v10, p5

    move-object v7, v1

    move/from16 v8, v18

    :goto_35
    move/from16 v9, v27

    move/from16 v13, v28

    goto/16 :goto_41

    .line 197
    :cond_66
    sget v0, Lcom/google/android/recaptcha/internal/zzaeh;->$r8$clinit:I

    .line 198
    invoke-static {v5}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 199
    invoke-static {v3, v4}, Lcom/google/android/recaptcha/internal/zzaeh;->zze([BI)J

    move-result-wide v0

    invoke-static {v0, v1}, Ljava/lang/Double;->longBitsToDouble(J)D

    .line 200
    throw v26

    :cond_67
    move v2, v1

    move/from16 v27, v9

    move/from16 v28, v23

    move v9, v3

    move/from16 v23, v14

    move-object/from16 v3, p2

    move v14, v7

    move/from16 v7, p4

    const/16 v1, 0x32

    if-ne v13, v1, :cond_6a

    const/4 v1, 0x2

    if-ne v8, v1, :cond_69

    .line 332
    invoke-direct {v0, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzr(I)Ljava/lang/Object;

    move-result-object v0

    .line 333
    invoke-virtual {v15, v12, v5, v6}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v1

    .line 334
    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzagt;->zza(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_68

    .line 335
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzags;->zza()Lcom/google/android/recaptcha/internal/zzags;

    move-result-object v2

    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzags;->zzc()Lcom/google/android/recaptcha/internal/zzags;

    move-result-object v2

    .line 336
    invoke-static {v2, v1}, Lcom/google/android/recaptcha/internal/zzagt;->zzb(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 337
    invoke-virtual {v15, v12, v5, v6, v2}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 338
    :cond_68
    invoke-static {v0}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 339
    throw v26

    :cond_69
    :goto_36
    move/from16 v10, p5

    move-object/from16 v7, p6

    move v8, v2

    move v5, v9

    goto :goto_35

    :cond_6a
    add-int/lit8 v1, v2, 0x2

    .line 204
    aget v1, v18, v1

    const v17, 0xfffff

    and-int v1, v1, v17

    move/from16 v26, v9

    int-to-long v9, v1

    packed-switch v13, :pswitch_data_2

    move-object/from16 v7, p6

    move/from16 v18, v2

    move/from16 v4, v26

    move/from16 v13, v28

    goto/16 :goto_3f

    :pswitch_1a
    const/4 v1, 0x3

    if-ne v8, v1, :cond_6b

    and-int/lit8 v1, v14, -0x8

    or-int/lit8 v6, v1, 0x4

    move/from16 v13, v28

    .line 205
    invoke-direct {v0, v12, v13, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzv(Ljava/lang/Object;II)Ljava/lang/Object;

    move-result-object v1

    move v4, v2

    .line 206
    invoke-direct {v0, v4}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v2

    move v11, v4

    move v5, v7

    move/from16 v4, v26

    move-object/from16 v7, p6

    .line 207
    invoke-static/range {v1 .. v7}, Lcom/google/android/recaptcha/internal/zzaeh;->zzj(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzahn;[BIIILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v2

    .line 208
    invoke-direct {v0, v12, v13, v11, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzw(Ljava/lang/Object;IILjava/lang/Object;)V

    :goto_37
    move v5, v2

    :goto_38
    move/from16 v18, v11

    goto/16 :goto_40

    :cond_6b
    move/from16 v13, v28

    move-object/from16 v7, p6

    move/from16 v18, v2

    :cond_6c
    move/from16 v4, v26

    goto/16 :goto_3f

    :pswitch_1b
    move-object/from16 v7, p6

    move v11, v2

    move/from16 v4, v26

    move/from16 v13, v28

    if-nez v8, :cond_6d

    .line 209
    invoke-static {v3, v4, v7}, Lcom/google/android/recaptcha/internal/zzaeh;->zzc([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v1

    move/from16 p3, v1

    iget-wide v1, v7, Lcom/google/android/recaptcha/internal/zzaeg;->zzb:J

    .line 210
    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zzaev;->zzH(J)J

    move-result-wide v1

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-virtual {v15, v12, v5, v6, v1}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 211
    invoke-virtual {v15, v12, v9, v10, v13}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    move/from16 v5, p3

    goto :goto_38

    :cond_6d
    move/from16 v18, v11

    goto/16 :goto_3f

    :pswitch_1c
    move-object/from16 v7, p6

    move v11, v2

    move/from16 v4, v26

    move/from16 v13, v28

    if-nez v8, :cond_6d

    .line 212
    invoke-static {v3, v4, v7}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v1

    iget v2, v7, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    .line 213
    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzaev;->zzG(I)I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v15, v12, v5, v6, v2}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 214
    invoke-virtual {v15, v12, v9, v10, v13}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    :goto_39
    move v5, v1

    goto :goto_38

    :pswitch_1d
    move-object/from16 v7, p6

    move v11, v2

    move/from16 v4, v26

    move/from16 v13, v28

    if-nez v8, :cond_6d

    .line 215
    invoke-static {v3, v4, v7}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v1

    iget v2, v7, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    .line 216
    invoke-direct {v0, v11}, Lcom/google/android/recaptcha/internal/zzaha;->zzs(I)Lcom/google/android/recaptcha/internal/zzaga;

    move-result-object v8

    if-eqz v8, :cond_6f

    invoke-interface {v8, v2}, Lcom/google/android/recaptcha/internal/zzaga;->zza(I)Z

    move-result v8

    if-eqz v8, :cond_6e

    goto :goto_3a

    .line 219
    :cond_6e
    invoke-static {v12}, Lcom/google/android/recaptcha/internal/zzaha;->zzh(Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zzaia;

    move-result-object v5

    int-to-long v8, v2

    invoke-static {v8, v9}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-virtual {v5, v14, v2}, Lcom/google/android/recaptcha/internal/zzaia;->zzk(ILjava/lang/Object;)V

    goto :goto_39

    .line 217
    :cond_6f
    :goto_3a
    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v15, v12, v5, v6, v2}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 218
    invoke-virtual {v15, v12, v9, v10, v13}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    goto :goto_39

    :pswitch_1e
    move-object/from16 v7, p6

    move v11, v2

    move/from16 v4, v26

    move/from16 v13, v28

    const/4 v2, 0x2

    if-ne v8, v2, :cond_6d

    .line 220
    invoke-static {v3, v4, v7}, Lcom/google/android/recaptcha/internal/zzaeh;->zzf([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v1

    iget-object v2, v7, Lcom/google/android/recaptcha/internal/zzaeg;->zzc:Ljava/lang/Object;

    .line 221
    invoke-virtual {v15, v12, v5, v6, v2}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 222
    invoke-virtual {v15, v12, v9, v10, v13}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    goto :goto_39

    :pswitch_1f
    move-object/from16 v7, p6

    move v11, v2

    move/from16 v4, v26

    move/from16 v13, v28

    const/4 v2, 0x2

    if-ne v8, v2, :cond_6d

    .line 223
    invoke-direct {v0, v12, v13, v11}, Lcom/google/android/recaptcha/internal/zzaha;->zzv(Ljava/lang/Object;II)Ljava/lang/Object;

    move-result-object v1

    .line 224
    invoke-direct {v0, v11}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v2

    move/from16 v5, p4

    move-object v6, v7

    .line 225
    invoke-static/range {v1 .. v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zzi(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzahn;[BIILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v2

    move v7, v4

    move-object v4, v1

    move v1, v7

    move-object v7, v6

    .line 226
    invoke-direct {v0, v12, v13, v11, v4}, Lcom/google/android/recaptcha/internal/zzaha;->zzw(Ljava/lang/Object;IILjava/lang/Object;)V

    move v4, v1

    goto/16 :goto_37

    :pswitch_20
    move-object/from16 v7, p6

    move/from16 v18, v2

    move/from16 v1, v26

    move/from16 v13, v28

    const/4 v2, 0x2

    if-ne v8, v2, :cond_73

    .line 227
    invoke-static {v3, v1, v7}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v2

    iget v8, v7, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    if-nez v8, :cond_70

    .line 228
    invoke-virtual {v15, v12, v5, v6, v4}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    goto :goto_3c

    :cond_70
    add-int v4, v2, v8

    const/high16 v20, 0x20000000

    and-int v11, v11, v20

    if-eqz v11, :cond_72

    .line 229
    invoke-static {v3, v2, v4}, Lcom/google/android/recaptcha/internal/zzail;->zza([BII)Z

    move-result v11

    if-eqz v11, :cond_71

    goto :goto_3b

    .line 341
    :cond_71
    invoke-static/range {v19 .. v19}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    :cond_72
    :goto_3b
    new-instance v11, Ljava/lang/String;

    move/from16 p3, v4

    .line 230
    sget-object v4, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v11, v3, v2, v8, v4}, Ljava/lang/String;-><init>([BIILjava/nio/charset/Charset;)V

    .line 231
    invoke-virtual {v15, v12, v5, v6, v11}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    move/from16 v2, p3

    .line 232
    :goto_3c
    invoke-virtual {v15, v12, v9, v10, v13}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    move v4, v1

    move v5, v2

    goto/16 :goto_40

    :cond_73
    move v4, v1

    goto/16 :goto_3f

    :pswitch_21
    move-object/from16 v7, p6

    move/from16 v18, v2

    move/from16 v1, v26

    move/from16 v13, v28

    if-nez v8, :cond_73

    .line 233
    invoke-static {v3, v1, v7}, Lcom/google/android/recaptcha/internal/zzaeh;->zzc([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v2

    move/from16 v26, v1

    iget-wide v0, v7, Lcom/google/android/recaptcha/internal/zzaeg;->zzb:J

    cmp-long v0, v0, v20

    if-eqz v0, :cond_74

    const/16 v25, 0x1

    goto :goto_3d

    :cond_74
    move/from16 v25, v16

    .line 234
    :goto_3d
    invoke-static/range {v25 .. v25}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    invoke-virtual {v15, v12, v5, v6, v0}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 235
    invoke-virtual {v15, v12, v9, v10, v13}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    move v5, v2

    move/from16 v4, v26

    goto/16 :goto_40

    :pswitch_22
    move-object/from16 v7, p6

    move/from16 v18, v2

    move/from16 v13, v28

    const/4 v4, 0x5

    if-ne v8, v4, :cond_6c

    add-int/lit8 v0, v26, 0x4

    move/from16 v4, v26

    .line 236
    invoke-static {v3, v4}, Lcom/google/android/recaptcha/internal/zzaeh;->zzd([BI)I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v15, v12, v5, v6, v1}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 237
    invoke-virtual {v15, v12, v9, v10, v13}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    :goto_3e
    move v5, v0

    goto/16 :goto_40

    :pswitch_23
    move-object/from16 v7, p6

    move/from16 v18, v2

    move/from16 v4, v26

    move/from16 v13, v28

    const/4 v2, 0x1

    if-ne v8, v2, :cond_75

    add-int/lit8 v0, v4, 0x8

    .line 238
    invoke-static {v3, v4}, Lcom/google/android/recaptcha/internal/zzaeh;->zze([BI)J

    move-result-wide v1

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-virtual {v15, v12, v5, v6, v1}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 239
    invoke-virtual {v15, v12, v9, v10, v13}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    goto :goto_3e

    :pswitch_24
    move-object/from16 v7, p6

    move/from16 v18, v2

    move/from16 v4, v26

    move/from16 v13, v28

    if-nez v8, :cond_75

    .line 240
    invoke-static {v3, v4, v7}, Lcom/google/android/recaptcha/internal/zzaeh;->zza([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v0

    iget v1, v7, Lcom/google/android/recaptcha/internal/zzaeg;->zza:I

    .line 241
    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v15, v12, v5, v6, v1}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 242
    invoke-virtual {v15, v12, v9, v10, v13}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    goto :goto_3e

    :pswitch_25
    move-object/from16 v7, p6

    move/from16 v18, v2

    move/from16 v4, v26

    move/from16 v13, v28

    if-nez v8, :cond_75

    .line 243
    invoke-static {v3, v4, v7}, Lcom/google/android/recaptcha/internal/zzaeh;->zzc([BILcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v0

    iget-wide v1, v7, Lcom/google/android/recaptcha/internal/zzaeg;->zzb:J

    .line 244
    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-virtual {v15, v12, v5, v6, v1}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 245
    invoke-virtual {v15, v12, v9, v10, v13}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    goto :goto_3e

    :pswitch_26
    move-object/from16 v7, p6

    move/from16 v18, v2

    move/from16 v4, v26

    move/from16 v13, v28

    const/4 v2, 0x5

    if-ne v8, v2, :cond_75

    add-int/lit8 v0, v4, 0x4

    .line 246
    invoke-static {v3, v4}, Lcom/google/android/recaptcha/internal/zzaeh;->zzd([BI)I

    move-result v1

    invoke-static {v1}, Ljava/lang/Float;->intBitsToFloat(I)F

    move-result v1

    .line 247
    invoke-static {v1}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v1

    invoke-virtual {v15, v12, v5, v6, v1}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 248
    invoke-virtual {v15, v12, v9, v10, v13}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    goto :goto_3e

    :pswitch_27
    move-object/from16 v7, p6

    move/from16 v18, v2

    move/from16 v4, v26

    move/from16 v13, v28

    const/4 v2, 0x1

    if-ne v8, v2, :cond_75

    add-int/lit8 v0, v4, 0x8

    .line 249
    invoke-static {v3, v4}, Lcom/google/android/recaptcha/internal/zzaeh;->zze([BI)J

    move-result-wide v1

    invoke-static {v1, v2}, Ljava/lang/Double;->longBitsToDouble(J)D

    move-result-wide v1

    .line 250
    invoke-static {v1, v2}, Ljava/lang/Double;->valueOf(D)Ljava/lang/Double;

    move-result-object v1

    invoke-virtual {v15, v12, v5, v6, v1}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 251
    invoke-virtual {v15, v12, v9, v10, v13}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    goto/16 :goto_3e

    :cond_75
    :goto_3f
    move v5, v4

    :goto_40
    if-eq v5, v4, :cond_76

    move-object/from16 v0, p0

    move/from16 v4, p4

    move-object v6, v7

    move-object v2, v12

    move v7, v13

    move-object v1, v15

    move/from16 v8, v18

    move/from16 v9, v27

    goto/16 :goto_34

    :cond_76
    move/from16 v10, p5

    move/from16 v8, v18

    move/from16 v9, v27

    :goto_41
    if-ne v14, v10, :cond_77

    if-eqz v10, :cond_77

    move-object/from16 v0, p0

    move/from16 v7, p4

    move-object v1, v15

    move v15, v14

    move v6, v5

    const v5, 0xfffff

    move/from16 v14, v23

    goto/16 :goto_44

    :cond_77
    move-object/from16 v0, p0

    .line 350
    iget-boolean v1, v0, Lcom/google/android/recaptcha/internal/zzaha;->zzh:Z

    if-eqz v1, :cond_78

    iget-object v1, v7, Lcom/google/android/recaptcha/internal/zzaeg;->zzd:Lcom/google/android/recaptcha/internal/zzafh;

    .line 252
    sget-object v2, Lcom/google/android/recaptcha/internal/zzafh;->zza:Lcom/google/android/recaptcha/internal/zzafh;

    .line 253
    sget v2, Lcom/google/android/recaptcha/internal/zzaef;->$r8$clinit:I

    sget-object v2, Lcom/google/android/recaptcha/internal/zzafh;->zza:Lcom/google/android/recaptcha/internal/zzafh;

    if-eq v1, v2, :cond_78

    iget-object v2, v0, Lcom/google/android/recaptcha/internal/zzaha;->zzg:Lcom/google/android/recaptcha/internal/zzagx;

    .line 254
    sget v4, Lcom/google/android/recaptcha/internal/zzaeh;->$r8$clinit:I

    .line 255
    invoke-virtual {v1, v2, v13}, Lcom/google/android/recaptcha/internal/zzafh;->zzc(Lcom/google/android/recaptcha/internal/zzagx;I)Lcom/google/android/recaptcha/internal/zzafu;

    move v3, v5

    .line 256
    invoke-static {v12}, Lcom/google/android/recaptcha/internal/zzaha;->zzh(Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zzaia;

    move-result-object v5

    move-object/from16 v2, p2

    move/from16 v4, p4

    move-object v6, v7

    move v1, v14

    .line 257
    invoke-static/range {v1 .. v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zzn(I[BIILcom/google/android/recaptcha/internal/zzaia;Lcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v3

    move/from16 v7, p4

    :goto_42
    move v5, v3

    goto :goto_43

    :cond_78
    move v3, v5

    move v1, v14

    .line 258
    invoke-static {v12}, Lcom/google/android/recaptcha/internal/zzaha;->zzh(Ljava/lang/Object;)Lcom/google/android/recaptcha/internal/zzaia;

    move-result-object v5

    move-object/from16 v2, p2

    move/from16 v4, p4

    move-object/from16 v6, p6

    .line 259
    invoke-static/range {v1 .. v6}, Lcom/google/android/recaptcha/internal/zzaeh;->zzn(I[BIILcom/google/android/recaptcha/internal/zzaia;Lcom/google/android/recaptcha/internal/zzaeg;)I

    move-result v3

    move v7, v4

    goto :goto_42

    :goto_43
    move-object v2, v15

    move v15, v1

    move-object v1, v2

    move-object/from16 v3, p2

    move-object/from16 v6, p6

    move v4, v7

    move-object v2, v12

    move v7, v13

    move/from16 v14, v23

    goto/16 :goto_12

    :cond_79
    move/from16 v10, p5

    move-object v12, v2

    move v7, v4

    move/from16 v27, v9

    move/from16 v23, v14

    const/16 v16, 0x0

    move v6, v5

    const v5, 0xfffff

    :goto_44
    if-eq v9, v5, :cond_7a

    int-to-long v2, v9

    .line 342
    invoke-virtual {v1, v12, v2, v3, v14}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    :cond_7a
    iget v1, v0, Lcom/google/android/recaptcha/internal/zzaha;->zzk:I

    move v8, v1

    :goto_45
    iget v1, v0, Lcom/google/android/recaptcha/internal/zzaha;->zzl:I

    if-ge v8, v1, :cond_7b

    iget-object v1, v0, Lcom/google/android/recaptcha/internal/zzaha;->zzj:[I

    iget-object v4, v0, Lcom/google/android/recaptcha/internal/zzaha;->zzm:Lcom/google/android/recaptcha/internal/zzahz;

    .line 343
    aget v2, v1, v8

    const/4 v3, 0x0

    move-object/from16 v5, p1

    move-object v1, v12

    .line 344
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzx(Ljava/lang/Object;ILjava/lang/Object;Lcom/google/android/recaptcha/internal/zzahz;Ljava/lang/Object;)Ljava/lang/Object;

    add-int/lit8 v8, v8, 0x1

    move-object/from16 v0, p0

    move-object/from16 v12, p1

    goto :goto_45

    .line 346
    :cond_7b
    const-string v0, "Failed to parse the message."

    if-nez v10, :cond_7d

    if-ne v6, v7, :cond_7c

    goto :goto_46

    .line 348
    :cond_7c
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    :cond_7d
    if-gt v6, v7, :cond_7e

    if-ne v15, v10, :cond_7e

    :goto_46
    return v6

    .line 350
    :cond_7e
    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v16

    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_c
        :pswitch_b
        :pswitch_a
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_9
        :pswitch_2
        :pswitch_7
        :pswitch_8
        :pswitch_1
        :pswitch_0
    .end packed-switch

    :pswitch_data_1
    .packed-switch 0x12
        :pswitch_19
        :pswitch_18
        :pswitch_17
        :pswitch_17
        :pswitch_16
        :pswitch_15
        :pswitch_14
        :pswitch_13
        :pswitch_12
        :pswitch_11
        :pswitch_10
        :pswitch_16
        :pswitch_f
        :pswitch_14
        :pswitch_15
        :pswitch_e
        :pswitch_d
        :pswitch_19
        :pswitch_18
        :pswitch_17
        :pswitch_17
        :pswitch_16
        :pswitch_15
        :pswitch_14
        :pswitch_13
        :pswitch_16
        :pswitch_f
        :pswitch_14
        :pswitch_15
        :pswitch_e
        :pswitch_d
    .end packed-switch

    :pswitch_data_2
    .packed-switch 0x33
        :pswitch_27
        :pswitch_26
        :pswitch_25
        :pswitch_25
        :pswitch_24
        :pswitch_23
        :pswitch_22
        :pswitch_21
        :pswitch_20
        :pswitch_1f
        :pswitch_1e
        :pswitch_24
        :pswitch_1d
        :pswitch_22
        :pswitch_23
        :pswitch_1c
        :pswitch_1b
        :pswitch_1a
    .end packed-switch
.end method

.method public final zzj(Ljava/lang/Object;[BIILcom/google/android/recaptcha/internal/zzaeg;)V
    .locals 7

    const/4 v5, 0x0

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move v3, p3

    move v4, p4

    move-object v6, p5

    .line 1
    invoke-virtual/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzaha;->zzi(Ljava/lang/Object;[BIIILcom/google/android/recaptcha/internal/zzaeg;)I

    return-void
.end method

.method public final zzk(Ljava/lang/Object;)V
    .locals 7

    .line 1
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzaha;->zzE(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_0

    goto/16 :goto_2

    :cond_0
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzafv;

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    .line 2
    move-object v0, p1

    check-cast v0, Lcom/google/android/recaptcha/internal/zzafv;

    const v2, 0x7fffffff

    .line 3
    invoke-virtual {v0, v2}, Lcom/google/android/recaptcha/internal/zzafv;->zzH(I)V

    iput v1, v0, Lcom/google/android/recaptcha/internal/zzaec;->zzb:I

    .line 4
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzA()V

    :cond_1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzc:[I

    :goto_0
    array-length v2, v0

    if-ge v1, v2, :cond_5

    .line 5
    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzA(I)I

    move-result v2

    const v3, 0xfffff

    and-int/2addr v3, v2

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzC(I)I

    move-result v2

    int-to-long v3, v3

    const/16 v5, 0x9

    if-eq v2, v5, :cond_3

    const/16 v5, 0x3c

    if-eq v2, v5, :cond_2

    const/16 v5, 0x44

    if-eq v2, v5, :cond_2

    packed-switch v2, :pswitch_data_0

    goto :goto_1

    .line 10
    :pswitch_0
    sget-object v2, Lcom/google/android/recaptcha/internal/zzaha;->zzb:Lsun/misc/Unsafe;

    .line 11
    invoke-virtual {v2, p1, v3, v4}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    if-eqz v5, :cond_4

    .line 12
    move-object v6, v5

    check-cast v6, Lcom/google/android/recaptcha/internal/zzags;

    invoke-virtual {v6}, Lcom/google/android/recaptcha/internal/zzags;->zzd()V

    .line 13
    invoke-virtual {v2, p1, v3, v4, v5}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    goto :goto_1

    .line 6
    :pswitch_1
    invoke-static {p1, v3, v4}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/android/recaptcha/internal/zzage;

    .line 7
    invoke-interface {v2}, Lcom/google/android/recaptcha/internal/zzage;->zzb()V

    goto :goto_1

    .line 8
    :cond_2
    aget v2, v0, v1

    .line 9
    invoke-direct {p0, p1, v2, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v2

    if-eqz v2, :cond_4

    .line 10
    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v2

    sget-object v5, Lcom/google/android/recaptcha/internal/zzaha;->zzb:Lsun/misc/Unsafe;

    invoke-virtual {v5, p1, v3, v4}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v3

    invoke-interface {v2, v3}, Lcom/google/android/recaptcha/internal/zzahn;->zzk(Ljava/lang/Object;)V

    goto :goto_1

    .line 14
    :cond_3
    :pswitch_2
    invoke-direct {p0, p1, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzK(Ljava/lang/Object;I)Z

    move-result v2

    if-eqz v2, :cond_4

    .line 15
    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v2

    sget-object v5, Lcom/google/android/recaptcha/internal/zzaha;->zzb:Lsun/misc/Unsafe;

    invoke-virtual {v5, p1, v3, v4}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v3

    invoke-interface {v2, v3}, Lcom/google/android/recaptcha/internal/zzahn;->zzk(Ljava/lang/Object;)V

    :cond_4
    :goto_1
    add-int/lit8 v1, v1, 0x3

    goto :goto_0

    .line 16
    :cond_5
    move-object v0, p1

    check-cast v0, Lcom/google/android/recaptcha/internal/zzafv;

    iget-object v0, v0, Lcom/google/android/recaptcha/internal/zzafv;->zzc:Lcom/google/android/recaptcha/internal/zzaia;

    .line 17
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaia;->zzd()V

    iget-boolean p0, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzh:Z

    if-nez p0, :cond_6

    :goto_2
    return-void

    .line 18
    :cond_6
    invoke-static {p1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    throw p0

    nop

    :pswitch_data_0
    .packed-switch 0x11
        :pswitch_2
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public final zzl(Ljava/lang/Object;)Z
    .locals 15

    move-object/from16 v1, p1

    const/4 v6, 0x0

    const v7, 0xfffff

    move v2, v6

    move v8, v2

    move v0, v7

    .line 1
    :goto_0
    iget v3, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzk:I

    const/4 v9, 0x0

    const/4 v4, 0x1

    if-ge v8, v3, :cond_b

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzj:[I

    aget v3, v3, v8

    .line 2
    invoke-direct {p0, v3}, Lcom/google/android/recaptcha/internal/zzaha;->zzA(I)I

    move-result v10

    iget-object v11, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzc:[I

    add-int/lit8 v5, v3, 0x2

    .line 3
    aget v5, v11, v5

    and-int v12, v5, v7

    ushr-int/lit8 v5, v5, 0x14

    shl-int v5, v4, v5

    if-eq v12, v0, :cond_1

    if-eq v12, v7, :cond_0

    int-to-long v13, v12

    sget-object v0, Lcom/google/android/recaptcha/internal/zzaha;->zzb:Lsun/misc/Unsafe;

    .line 4
    invoke-virtual {v0, v1, v13, v14}, Lsun/misc/Unsafe;->getInt(Ljava/lang/Object;J)I

    move-result v2

    :cond_0
    move v4, v2

    move v0, v12

    goto :goto_1

    :cond_1
    move v4, v2

    :goto_1
    const/high16 v2, 0x10000000

    and-int/2addr v2, v10

    if-eqz v2, :cond_2

    move v2, v3

    move v3, v0

    move-object v0, p0

    .line 5
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v12

    if-nez v12, :cond_3

    return v6

    :cond_2
    move v2, v3

    move v3, v0

    :cond_3
    invoke-static {v10}, Lcom/google/android/recaptcha/internal/zzaha;->zzC(I)I

    move-result v12

    const/16 v13, 0x9

    if-eq v12, v13, :cond_9

    const/16 v13, 0x11

    if-eq v12, v13, :cond_9

    const/16 v5, 0x1b

    if-eq v12, v5, :cond_7

    const/16 v5, 0x3c

    if-eq v12, v5, :cond_6

    const/16 v5, 0x44

    if-eq v12, v5, :cond_6

    const/16 v5, 0x31

    if-eq v12, v5, :cond_7

    const/16 v5, 0x32

    if-eq v12, v5, :cond_4

    goto :goto_3

    :cond_4
    and-int v5, v10, v7

    int-to-long v10, v5

    .line 12
    invoke-static {v1, v10, v11}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    .line 13
    check-cast v5, Lcom/google/android/recaptcha/internal/zzags;

    .line 14
    invoke-interface {v5}, Ljava/util/Map;->isEmpty()Z

    move-result v5

    if-eqz v5, :cond_5

    goto :goto_3

    .line 20
    :cond_5
    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzr(I)Ljava/lang/Object;

    move-result-object p0

    .line 21
    invoke-static {p0}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 22
    throw v9

    .line 15
    :cond_6
    aget v5, v11, v2

    .line 16
    invoke-direct {p0, v1, v5, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzM(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_a

    .line 17
    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v2

    invoke-static {v1, v10, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzy(Ljava/lang/Object;ILcom/google/android/recaptcha/internal/zzahn;)Z

    move-result v2

    if-nez v2, :cond_a

    return v6

    :cond_7
    and-int v5, v10, v7

    int-to-long v9, v5

    .line 6
    invoke-static {v1, v9, v10}, Lcom/google/android/recaptcha/internal/zzaig;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 7
    invoke-interface {v5}, Ljava/util/List;->isEmpty()Z

    move-result v9

    if-nez v9, :cond_a

    .line 8
    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v2

    move v9, v6

    .line 9
    :goto_2
    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v10

    if-ge v9, v10, :cond_a

    .line 10
    invoke-interface {v5, v9}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v10

    .line 11
    invoke-interface {v2, v10}, Lcom/google/android/recaptcha/internal/zzahn;->zzl(Ljava/lang/Object;)Z

    move-result v10

    if-nez v10, :cond_8

    return v6

    :cond_8
    add-int/lit8 v9, v9, 0x1

    goto :goto_2

    :cond_9
    move-object v0, p0

    .line 18
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaha;->zzJ(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_a

    .line 19
    invoke-direct {p0, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzq(I)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v2

    invoke-static {v1, v10, v2}, Lcom/google/android/recaptcha/internal/zzaha;->zzy(Ljava/lang/Object;ILcom/google/android/recaptcha/internal/zzahn;)Z

    move-result v2

    if-nez v2, :cond_a

    return v6

    :cond_a
    :goto_3
    add-int/lit8 v8, v8, 0x1

    move v0, v3

    move v2, v4

    goto/16 :goto_0

    :cond_b
    iget-boolean p0, p0, Lcom/google/android/recaptcha/internal/zzaha;->zzh:Z

    if-nez p0, :cond_c

    return v4

    .line 23
    :cond_c
    invoke-static {v1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    throw v9
.end method
