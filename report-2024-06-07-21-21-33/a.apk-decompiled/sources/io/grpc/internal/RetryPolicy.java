package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import io.grpc.Status;
import java.util.Collection;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class RetryPolicy {
    final double backoffMultiplier;
    final long initialBackoffNanos;
    final int maxAttempts;
    final long maxBackoffNanos;

    @Nullable
    final Long perAttemptRecvTimeoutNanos;
    final Set<Status.Code> retryableStatusCodes;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RetryPolicy(int i, long j, long j2, double d, @Nullable Long l, @Nonnull Set<Status.Code> set) {
        this.maxAttempts = i;
        this.initialBackoffNanos = j;
        this.maxBackoffNanos = j2;
        this.backoffMultiplier = d;
        this.perAttemptRecvTimeoutNanos = l;
        this.retryableStatusCodes = ImmutableSet.copyOf((Collection) set);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.maxAttempts), Long.valueOf(this.initialBackoffNanos), Long.valueOf(this.maxBackoffNanos), Double.valueOf(this.backoffMultiplier), this.perAttemptRecvTimeoutNanos, this.retryableStatusCodes);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RetryPolicy)) {
            return false;
        }
        RetryPolicy retryPolicy = (RetryPolicy) obj;
        return this.maxAttempts == retryPolicy.maxAttempts && this.initialBackoffNanos == retryPolicy.initialBackoffNanos && this.maxBackoffNanos == retryPolicy.maxBackoffNanos && Double.compare(this.backoffMultiplier, retryPolicy.backoffMultiplier) == 0 && Objects.equal(this.perAttemptRecvTimeoutNanos, retryPolicy.perAttemptRecvTimeoutNanos) && Objects.equal(this.retryableStatusCodes, retryPolicy.retryableStatusCodes);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("maxAttempts", this.maxAttempts).add("initialBackoffNanos", this.initialBackoffNanos).add("maxBackoffNanos", this.maxBackoffNanos).add("backoffMultiplier", this.backoffMultiplier).add("perAttemptRecvTimeoutNanos", this.perAttemptRecvTimeoutNanos).add("retryableStatusCodes", this.retryableStatusCodes).toString();
    }
}
