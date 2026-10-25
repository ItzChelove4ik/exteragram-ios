.class final Lcom/google/android/recaptcha/internal/zzqf;
.super Lcom/google/android/recaptcha/internal/zzow;
.source "SourceFile"


# static fields
.field static final zza:Lcom/google/android/recaptcha/internal/zzqv;


# instance fields
.field private final zzb:[Ljava/lang/Object;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzqf;

    const/4 v1, 0x0

    new-array v2, v1, [Ljava/lang/Object;

    invoke-direct {v0, v2, v1}, Lcom/google/android/recaptcha/internal/zzqf;-><init>([Ljava/lang/Object;I)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzqf;->zza:Lcom/google/android/recaptcha/internal/zzqv;

    return-void
.end method

.method public constructor <init>([Ljava/lang/Object;I)V
    .locals 0

    const/4 p2, 0x0

    .line 1
    invoke-direct {p0, p2, p2}, Lcom/google/android/recaptcha/internal/zzow;-><init>(II)V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzqf;->zzb:[Ljava/lang/Object;

    return-void
.end method


# virtual methods
.method public final zza(I)Ljava/lang/Object;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzqf;->zzb:[Ljava/lang/Object;

    aget-object p0, p0, p1

    return-object p0
.end method
