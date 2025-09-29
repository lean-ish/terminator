/*
 * Copyright (c) 2025 Leandro Aguiar
 * Licensed under the MIT License.
 * See LICENSE file in the project root for full license information.
 */
package io.github.leanish.terminator;

import java.time.Duration;

/**
 * Represents a service whose {@link #terminate()} method returns immediately while shutting-down continues asynchronously.
 */
public interface NonBlockingTerminable extends Terminable {

    /**
     * Initiates shutdown and returns immediately while the service completes termination asynchronously.
     */
    @Override
    void terminate();

    /**
     * Waits for the asynchronous shutdown to complete for up to the provided timeout. Negative values are treated as
     * zero.
     *
     * @param timeout maximum time to wait before giving up
     * @return {@code true} if shutdown finished in time, {@code false} if the timeout elapsed
     * @throws InterruptedException if interrupted while waiting
     */
    boolean awaitTermination(Duration timeout) throws InterruptedException;
}
