.class public final Lcom/google/android/recaptcha/internal/zzlj;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final zzd:Ljava/lang/String;


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzlr;

.field private final zzb:Lcom/google/android/recaptcha/internal/zzli;

.field private final zzc:Lcom/google/android/recaptcha/internal/zzhd;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 1
    invoke-static {}, Ljava/util/UUID;->randomUUID()Ljava/util/UUID;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/UUID;->toString()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzlj;->zzd:Ljava/lang/String;

    return-void
.end method

.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzlr;Lcom/google/android/recaptcha/internal/zzli;Lcom/google/android/recaptcha/internal/zzhd;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzlj;->zza:Lcom/google/android/recaptcha/internal/zzlr;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzlj;->zzb:Lcom/google/android/recaptcha/internal/zzli;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzlj;->zzc:Lcom/google/android/recaptcha/internal/zzhd;

    return-void
.end method

.method public static final synthetic zzb()Ljava/lang/String;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzlj;->zzd:Ljava/lang/String;

    return-object v0
.end method


# virtual methods
.method public final zza(Lcom/google/android/recaptcha/internal/zzajh;Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzaja;)V
    .locals 4

    if-eqz p3, :cond_0

    .line 1
    invoke-virtual {p1, p3}, Lcom/google/android/recaptcha/internal/zzajh;->zzf(Lcom/google/android/recaptcha/internal/zzaja;)Lcom/google/android/recaptcha/internal/zzajh;

    :cond_0
    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzlj;->zzc:Lcom/google/android/recaptcha/internal/zzhd;

    invoke-virtual {p3}, Lcom/google/android/recaptcha/internal/zzhd;->zza()Ljava/util/List;

    move-result-object p3

    .line 2
    invoke-interface {p3}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p3

    :goto_0
    invoke-interface {p3}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {p3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    invoke-static {v0}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 v0, 0x0

    .line 3
    invoke-virtual {p1, v0}, Lcom/google/android/recaptcha/internal/zzajh;->zzt(I)Lcom/google/android/recaptcha/internal/zzajh;

    goto :goto_0

    .line 4
    :cond_1
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzajh;->zzu()Z

    move-result p3

    const-wide/16 v0, 0x3e8

    if-eqz p3, :cond_2

    .line 5
    sget p3, Lcom/google/android/recaptcha/internal/zzhj;->$r8$clinit:I

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzajh;->zzv()I

    move-result p3

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzajh;->zzd()Lcom/google/android/recaptcha/internal/zzafe;

    move-result-object v2

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzaio;->zzb(Lcom/google/android/recaptcha/internal/zzafe;)J

    move-result-wide v2

    mul-long/2addr v2, v0

    add-int/lit16 p3, p3, 0x4e20

    .line 6
    invoke-static {p3, v2, v3}, Lcom/google/android/recaptcha/internal/zzhj;->zza(IJ)V

    goto :goto_1

    .line 7
    :cond_2
    sget p3, Lcom/google/android/recaptcha/internal/zzhj;->$r8$clinit:I

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzajh;->zzx()I

    move-result p3

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzajh;->zzd()Lcom/google/android/recaptcha/internal/zzafe;

    move-result-object v2

    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzaio;->zzb(Lcom/google/android/recaptcha/internal/zzafe;)J

    move-result-wide v2

    mul-long/2addr v2, v0

    .line 8
    invoke-static {p3}, Lcom/google/android/recaptcha/internal/zzaji;->zza(I)I

    move-result p3

    add-int/lit16 p3, p3, 0x2710

    invoke-static {p3, v2, v3}, Lcom/google/android/recaptcha/internal/zzhj;->zza(IJ)V

    .line 6
    :goto_1
    iget-object p3, p0, Lcom/google/android/recaptcha/internal/zzlj;->zzb:Lcom/google/android/recaptcha/internal/zzli;

    .line 9
    invoke-virtual {p3, p2}, Lcom/google/android/recaptcha/internal/zzli;->zza(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzajr;

    move-result-object p2

    invoke-virtual {p1, p2}, Lcom/google/android/recaptcha/internal/zzajh;->zzg(Lcom/google/android/recaptcha/internal/zzajr;)Lcom/google/android/recaptcha/internal/zzajh;

    .line 10
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzamk;->zzd()Lcom/google/android/recaptcha/internal/zzamj;

    move-result-object p2

    invoke-virtual {p2, p1}, Lcom/google/android/recaptcha/internal/zzamj;->zza(Lcom/google/android/recaptcha/internal/zzajh;)Lcom/google/android/recaptcha/internal/zzamj;

    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p1

    check-cast p1, Lcom/google/android/recaptcha/internal/zzamk;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzlj;->zza:Lcom/google/android/recaptcha/internal/zzlr;

    .line 11
    invoke-interface {p0, p1}, Lcom/google/android/recaptcha/internal/zzlr;->zza(Lcom/google/android/recaptcha/internal/zzamk;)V

    return-void
.end method
