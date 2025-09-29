/*
 * Copyright (c) 2025 Leandro Aguiar
 * Licensed under the MIT License.
 * See LICENSE file in the project root for full license information.
 */
package io.github.leanish.terminator;

/**
 * Represents a service that can be terminated.
 */
interface Terminable {

    /**
     * Initiates termination of the service. Implementations may block until the service is fully stopped
     * or return immediately while shutting-down completes in the background.
     *
     * @throws InterruptedException if the current thread is interrupted while blocking for shutdown
     */
    void terminate() throws InterruptedException;
}
