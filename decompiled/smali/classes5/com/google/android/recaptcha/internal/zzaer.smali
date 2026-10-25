.class public abstract Lcom/google/android/recaptcha/internal/zzaer;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Iterable;
.implements Ljava/io/Serializable;


# static fields
.field public static final zza:Lcom/google/android/recaptcha/internal/zzaer;


# instance fields
.field private zzb:I


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzaep;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzagf;->zza:[B

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzaep;-><init>([B)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    .line 2
    sget v0, Lcom/google/android/recaptcha/internal/zzaef;->$r8$clinit:I

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x0

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaer;->zzb:I

    return-void
.end method

.method public static zzA()Lcom/google/android/recaptcha/internal/zzaeq;
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzaeq;

    const/16 v1, 0x80

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzaeq;-><init>(I)V

    return-object v0
.end method

.method public static zzB(III)I
    .locals 3

    or-int v0, p0, p1

    sub-int v1, p1, p0

    or-int/2addr v0, v1

    sub-int v2, p2, p1

    or-int/2addr v0, v2

    if-gez v0, :cond_2

    if-ltz p0, :cond_1

    if-ge p1, p0, :cond_0

    .line 2
    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/String;->length()I

    move-result p2

    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v0

    add-int/lit8 p2, p2, 0x2c

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v0

    add-int/2addr p2, v0

    const-string v0, "Beginning index larger than ending index: "

    const-string v1, ", "

    invoke-static {p2, v0, p0, v1, p1}, Lcom/google/android/gms/internal/cast/zzxk$$ExternalSyntheticBUOutline0;->m(ILjava/lang/Object;ILjava/lang/Object;I)V

    :goto_0
    const/4 p0, 0x0

    return p0

    .line 3
    :cond_0
    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result p0

    invoke-static {p2}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v0

    add-int/lit8 p0, p0, 0xf

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v0

    add-int/2addr p0, v0

    const-string v0, "End index: "

    const-string v1, " >= "

    invoke-static {p0, v0, p1, v1, p2}, Lcom/google/android/gms/internal/cast/zzxk$$ExternalSyntheticBUOutline0;->m(ILjava/lang/Object;ILjava/lang/Object;I)V

    goto :goto_0

    .line 1
    :cond_1
    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result p1

    add-int/lit8 p1, p1, 0x15

    invoke-static {p1, p0}, Lcom/google/android/gms/internal/cast/zzxk$$ExternalSyntheticBUOutline1;->m(II)V

    goto :goto_0

    :cond_2
    return v1
.end method

.method public static synthetic zzC([BI[BII)Z
    .locals 2

    add-int v0, p1, p4

    .line 1
    array-length v1, p0

    invoke-static {p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzaer;->zzB(III)I

    add-int/2addr p4, p3

    array-length v1, p2

    .line 2
    invoke-static {p3, p4, v1}, Lcom/google/android/recaptcha/internal/zzaer;->zzB(III)I

    :goto_0
    if-ge p1, v0, :cond_1

    .line 3
    aget-byte p4, p0, p1

    aget-byte v1, p2, p3

    if-eq p4, v1, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    add-int/lit8 p1, p1, 0x1

    add-int/lit8 p3, p3, 0x1

    goto :goto_0

    :cond_1
    const/4 p0, 0x1

    return p0
.end method

.method private static zzj(Ljava/util/Iterator;I)Lcom/google/android/recaptcha/internal/zzaer;
    .locals 4

    const/4 v0, 0x0

    if-lez p1, :cond_2

    const/4 v1, 0x1

    if-ne p1, v1, :cond_0

    .line 2
    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzaer;

    return-object p0

    :cond_0
    ushr-int/lit8 v1, p1, 0x1

    .line 3
    invoke-static {p0, v1}, Lcom/google/android/recaptcha/internal/zzaer;->zzj(Ljava/util/Iterator;I)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object v2

    sub-int/2addr p1, v1

    .line 4
    invoke-static {p0, p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzj(Ljava/util/Iterator;I)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    const p1, 0x7fffffff

    .line 5
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v1

    sub-int/2addr p1, v1

    .line 6
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v1

    if-lt p1, v1, :cond_1

    .line 8
    invoke-static {v2, p0}, Lcom/google/android/recaptcha/internal/zzahm;->zzj(Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    return-object p0

    .line 5
    :cond_1
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result p1

    .line 7
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result p0

    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v1

    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v2

    add-int/lit8 v1, v1, 0x1f

    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v2

    add-int/2addr v1, v2

    const-string v2, "ByteString would be too long: "

    const-string v3, "+"

    invoke-static {v1, v2, p1, v3, p0}, Lcom/google/android/gms/internal/cast/zzxg$$ExternalSyntheticBUOutline0;->m(ILjava/lang/Object;ILjava/lang/Object;I)V

    return-object v0

    .line 1
    :cond_2
    sget-object p0, Ljava/util/Locale;->ROOT:Ljava/util/Locale;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object p1

    const-string v1, "length (%s) must be >= 1"

    invoke-static {p0, v1, p1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v0
.end method

.method public static zzs([BII)Lcom/google/android/recaptcha/internal/zzaer;
    .locals 1

    const/4 v0, 0x0

    .line 1
    :try_start_0
    invoke-static {p0, p1, p2, v0}, Lcom/google/android/recaptcha/internal/zzaer;->zzt([BIIZ)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzagk; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception p0

    new-instance p1, Ljava/lang/AssertionError;

    const-string p2, "Expected no InvalidProtocolBufferException as data UTF8 validity is not checked."

    .line 2
    invoke-direct {p1, p2, p0}, Ljava/lang/AssertionError;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    throw p1
.end method

.method public static zzt([BIIZ)Lcom/google/android/recaptcha/internal/zzaer;
    .locals 1

    if-nez p2, :cond_0

    .line 1
    sget-object p0, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    return-object p0

    :cond_0
    add-int p3, p1, p2

    array-length v0, p0

    invoke-static {p1, p3, v0}, Lcom/google/android/recaptcha/internal/zzaer;->zzB(III)I

    new-array p3, p2, [B

    const/4 v0, 0x0

    .line 2
    invoke-static {p0, p1, p3, v0, p2}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 3
    new-instance p0, Lcom/google/android/recaptcha/internal/zzaep;

    invoke-direct {p0, p3}, Lcom/google/android/recaptcha/internal/zzaep;-><init>([B)V

    return-object p0
.end method

.method public static zzu([B)Lcom/google/android/recaptcha/internal/zzaer;
    .locals 2

    const/4 v0, 0x0

    .line 1
    :try_start_0
    invoke-static {p0, v0}, Lcom/google/android/recaptcha/internal/zzaer;->zzv([BZ)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzagk; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception p0

    new-instance v0, Ljava/lang/AssertionError;

    const-string v1, "Expected no InvalidProtocolBufferException as data UTF8 validity is not checked."

    .line 2
    invoke-direct {v0, v1, p0}, Ljava/lang/AssertionError;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    throw v0
.end method

.method public static zzv([BZ)Lcom/google/android/recaptcha/internal/zzaer;
    .locals 0

    .line 1
    array-length p1, p0

    if-nez p1, :cond_0

    sget-object p0, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    return-object p0

    .line 2
    :cond_0
    new-instance p1, Lcom/google/android/recaptcha/internal/zzaep;

    invoke-direct {p1, p0}, Lcom/google/android/recaptcha/internal/zzaep;-><init>([B)V

    return-object p1
.end method

.method public static zzw(Ljava/lang/Iterable;)Lcom/google/android/recaptcha/internal/zzaer;
    .locals 3

    .line 1
    instance-of v0, p0, Ljava/util/Collection;

    if-nez v0, :cond_0

    .line 2
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v0

    const/4 v1, 0x0

    .line 3
    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    .line 4
    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 5
    :cond_0
    move-object v0, p0

    check-cast v0, Ljava/util/Collection;

    invoke-interface {v0}, Ljava/util/Collection;->size()I

    move-result v1

    :cond_1
    if-nez v1, :cond_2

    .line 4
    sget-object p0, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    return-object p0

    .line 6
    :cond_2
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    invoke-static {p0, v1}, Lcom/google/android/recaptcha/internal/zzaer;->zzj(Ljava/util/Iterator;I)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public final equals(Ljava/lang/Object;)Z
    .locals 4

    const/4 v0, 0x1

    if-ne p1, p0, :cond_0

    return v0

    .line 1
    :cond_0
    instance-of v1, p1, Lcom/google/android/recaptcha/internal/zzaer;

    const/4 v2, 0x0

    if-nez v1, :cond_1

    return v2

    :cond_1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzaer;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v1

    .line 2
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v3

    if-eq v1, v3, :cond_2

    return v2

    :cond_2
    if-eqz v1, :cond_4

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaer;->zzb:I

    iget v1, p1, Lcom/google/android/recaptcha/internal/zzaer;->zzb:I

    if-eqz v0, :cond_3

    if-eqz v1, :cond_3

    if-eq v0, v1, :cond_3

    return v2

    .line 3
    :cond_3
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzi(Lcom/google/android/recaptcha/internal/zzaer;)Z

    move-result p0

    return p0

    :cond_4
    return v0
.end method

.method public final hashCode()I
    .locals 2

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaer;->zzb:I

    if-nez v0, :cond_1

    const/4 v0, 0x0

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v1

    invoke-virtual {p0, v1, v0, v1}, Lcom/google/android/recaptcha/internal/zzaer;->zzk(III)I

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    :cond_0
    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaer;->zzb:I

    :cond_1
    return v0
.end method

.method public bridge synthetic iterator()Ljava/util/Iterator;
    .locals 0

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzq()Lcom/google/android/recaptcha/internal/zzaen;

    move-result-object p0

    return-object p0
.end method

.method public final toString()Ljava/lang/String;
    .locals 5

    .line 1
    sget-object v0, Ljava/util/Locale;->ROOT:Ljava/util/Locale;

    invoke-static {p0}, Ljava/lang/System;->identityHashCode(Ljava/lang/Object;)I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->toHexString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v2

    .line 2
    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v3

    const/16 v4, 0x32

    if-gt v3, v4, :cond_0

    .line 3
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzy()[B

    move-result-object p0

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzahv;->zza([B)Ljava/lang/String;

    move-result-object p0

    goto :goto_0

    :cond_0
    const/4 v3, 0x0

    const/16 v4, 0x2f

    .line 4
    invoke-virtual {p0, v3, v4}, Lcom/google/android/recaptcha/internal/zzaer;->zzd(II)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    .line 3
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzy()[B

    move-result-object p0

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzahv;->zza([B)Ljava/lang/String;

    move-result-object p0

    const-string v3, "..."

    invoke-virtual {p0, v3}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    :goto_0
    filled-new-array {v1, v2, p0}, [Ljava/lang/Object;

    move-result-object p0

    const-string v1, "<ByteString@%s size=%d contents=\"%s\">"

    .line 5
    invoke-static {v0, v1, p0}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public abstract zza(I)B
.end method

.method public abstract zzb()I
.end method

.method public abstract zzc(II)Lcom/google/android/recaptcha/internal/zzaer;
.end method

.method public abstract zzd(II)Lcom/google/android/recaptcha/internal/zzaer;
.end method

.method public abstract zze([BIII)V
.end method

.method public abstract zzf()Ljava/nio/ByteBuffer;
.end method

.method public abstract zzg(Lcom/google/android/recaptcha/internal/zzaej;)V
.end method

.method public abstract zzh(Ljava/nio/charset/Charset;)Ljava/lang/String;
.end method

.method public abstract zzi(Lcom/google/android/recaptcha/internal/zzaer;)Z
.end method

.method public abstract zzk(III)I
.end method

.method public abstract zzl()Lcom/google/android/recaptcha/internal/zzaev;
.end method

.method public abstract zzo()I
.end method

.method public abstract zzp()Z
.end method

.method public zzq()Lcom/google/android/recaptcha/internal/zzaen;
    .locals 1

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzaek;

    invoke-direct {v0, p0}, Lcom/google/android/recaptcha/internal/zzaek;-><init>(Lcom/google/android/recaptcha/internal/zzaer;)V

    return-object v0
.end method

.method public final zzr()Z
    .locals 0

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result p0

    if-nez p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public final zzx([BIII)V
    .locals 2
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result p2

    const/4 v0, 0x0

    invoke-static {v0, p4, p2}, Lcom/google/android/recaptcha/internal/zzaer;->zzB(III)I

    add-int p2, p3, p4

    array-length v1, p1

    .line 2
    invoke-static {p3, p2, v1}, Lcom/google/android/recaptcha/internal/zzaer;->zzB(III)I

    if-lez p4, :cond_0

    .line 3
    invoke-virtual {p0, p1, v0, p3, p4}, Lcom/google/android/recaptcha/internal/zzaer;->zze([BIII)V

    :cond_0
    return-void
.end method

.method public final zzy()[B
    .locals 3

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v0

    if-nez v0, :cond_0

    sget-object p0, Lcom/google/android/recaptcha/internal/zzagf;->zza:[B

    return-object p0

    .line 2
    :cond_0
    new-array v1, v0, [B

    const/4 v2, 0x0

    .line 3
    invoke-virtual {p0, v1, v2, v2, v0}, Lcom/google/android/recaptcha/internal/zzaer;->zze([BIII)V

    return-object v1
.end method

.method public final zzz(Ljava/nio/charset/Charset;)Ljava/lang/String;
    .locals 1

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzr()Z

    move-result v0

    if-eqz v0, :cond_0

    const-string p0, ""

    return-object p0

    :cond_0
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzh(Ljava/nio/charset/Charset;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
