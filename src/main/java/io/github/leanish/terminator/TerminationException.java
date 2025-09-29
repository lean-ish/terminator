/*
 * Copyright (c) 2025 Leandro Aguiar
 * Licensed under the MIT License.
 * See LICENSE file in the project root for full license information.
 */
package io.github.leanish.terminator;

/**
 * Runtime exception thrown when one or more services fail to terminate cleanly.
 */
public class TerminationException extends RuntimeException {

    public TerminationException(String message, Throwable cause) {
        super(message, cause);
    }
}
