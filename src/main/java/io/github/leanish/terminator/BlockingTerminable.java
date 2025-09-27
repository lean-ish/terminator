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
