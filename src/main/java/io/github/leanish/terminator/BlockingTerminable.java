/*
 * Copyright (c) 2025 Leandro Aguiar
 * Licensed under the MIT License.
 * See LICENSE file in the project root for full license information.
 */
package io.github.leanish.terminator;

/**
 * Marker interface for services whose {@link #terminate()} method blocks until shutting-down completes.
 */
public interface BlockingTerminable extends Terminable {

    /**
     * Initiates shutdown and blocks until the service has fully terminated before returning.
     */
    @Override
    void terminate() throws InterruptedException;
}
